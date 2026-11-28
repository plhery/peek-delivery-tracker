\set ON_ERROR_STOP on
-- A parcel's private note and files: read and written by its owner alone, within their limits, gone with the parcel
-- or the account, joined when two legs merge, taken along when a link's parcel is kept, and never on a link.
begin;

insert into auth.users (id, email, is_anonymous) values
  ('e1000000-0000-4000-8000-000000000001', 'note-owner@example.test', false),
  ('e1000000-0000-4000-8000-000000000002', 'note-other@example.test', false);
insert into public.packages (id, user_id, tracking_number, carrier, label) values
  ('e1000000-0000-4000-8000-000000000011', 'e1000000-0000-4000-8000-000000000001', 'NOTETEST0001', 'unknown', 'Shoes'),
  ('e1000000-0000-4000-8000-000000000012', 'e1000000-0000-4000-8000-000000000001', 'NOTETEST0002', 'unknown', 'Lamp'),
  ('e1000000-0000-4000-8000-000000000013', 'e1000000-0000-4000-8000-000000000001', 'NOTETEST0003', 'unknown', ''),
  ('e1000000-0000-4000-8000-000000000021', 'e1000000-0000-4000-8000-000000000002', 'NOTETEST0004', 'unknown', '');

-- Client roles reach the bytes never, the rows only through RLS and the functions.
do $$
declare
  relation text;
  privilege text;
begin
  foreach relation in array array['public.package_notes', 'public.package_files', 'public.package_file_contents'] loop
    if not (select relrowsecurity from pg_class where oid = relation::regclass) then
      raise exception '% is not under row-level security', relation;
    end if;
    foreach privilege in array array['SELECT', 'INSERT', 'UPDATE', 'DELETE', 'TRUNCATE'] loop
      if has_table_privilege('anon', relation, privilege)
          or (has_table_privilege('authenticated', relation, privilege)
            and not (privilege = 'SELECT' and relation <> 'public.package_file_contents')) then
        raise exception 'A client role may % %', privilege, relation;
      end if;
    end loop;
  end loop;
  if exists (select 1 from pg_policies where tablename = 'package_file_contents') then
    raise exception 'The files'' bytes have a policy';
  end if;
  if has_function_privilege('authenticated', 'public.package_file_content(uuid,uuid,boolean)', 'EXECUTE')
      or has_function_privilege('anon', 'public.package_file_content(uuid,uuid,boolean)', 'EXECUTE')
      or not has_function_privilege('service_role', 'public.package_file_content(uuid,uuid,boolean)', 'EXECUTE') then
    raise exception 'Only the service role reads a file''s bytes';
  end if;
  if has_function_privilege('anon', 'public.set_package_note(uuid,text)', 'EXECUTE')
      or has_function_privilege('anon', 'public.add_package_file(uuid,text,text,text,text,integer,integer)', 'EXECUTE')
      or has_function_privilege('anon', 'public.delete_package_file(uuid)', 'EXECUTE')
      or has_function_privilege('service_role', 'public.set_package_note(uuid,text)', 'EXECUTE') then
    raise exception 'The note''s functions answer someone else than a signed-in account';
  end if;
end;
$$;

-- The owner writes its note and files; another account reads, writes and deletes neither.
set local role authenticated;
select set_config('request.jwt.claim.sub', 'e1000000-0000-4000-8000-000000000001', true);
do $$
declare
  parcel constant uuid := 'e1000000-0000-4000-8000-000000000011';
  jpeg constant text := encode('\xffd8ffe000104a464946'::bytea, 'base64');
  thumb constant text := encode('\xffd8ffdb'::bytea, 'base64');
  added jsonb;
begin
  if not public.set_package_note(parcel, E'  Gate code 1234\n') then
    raise exception 'The owner could not write its note';
  end if;
  if (select body from public.package_notes where package_id = parcel) <> 'Gate code 1234' then
    raise exception 'The note was stored as %', (select body from public.package_notes where package_id = parcel);
  end if;
  perform public.set_package_note(parcel, 'Leave it with the neighbour');
  if (select count(*) from public.package_notes where package_id = parcel) <> 1
      or (select body from public.package_notes where package_id = parcel) <> 'Leave it with the neighbour' then
    raise exception 'A second note did not replace the first';
  end if;
  begin
    perform public.set_package_note(parcel, repeat('é', 1001));
    raise exception 'A note of 1,001 characters was stored';
  exception when invalid_parameter_value then null;
  end;
  perform public.set_package_note(parcel, repeat('é', 1000));
  perform public.set_package_note(parcel, E' \n\t ');
  if exists (select 1 from public.package_notes where package_id = parcel) then
    raise exception 'A blank note did not delete the note';
  end if;
  perform public.set_package_note(parcel, 'Leave it with the neighbour');

  added := public.add_package_file(parcel, ' Receipt.jpg ', 'image/jpeg', jpeg, thumb, 2560, 1920);
  if added->>'name' <> 'Receipt.jpg' or (added->>'size')::integer <> 10 or (added->>'width')::integer <> 2560 then
    raise exception 'The file was stored as %', added;
  end if;
  perform set_config('note_test.file', added->>'id', true);
  begin
    perform public.add_package_file(parcel, 'Label.pdf', 'application/pdf', encode('%PDF-1.7'::bytea, 'base64'), thumb);
    raise exception 'A PDF was stored with a thumbnail';
  exception when invalid_parameter_value then null;
  end;
  begin
    perform public.add_package_file(parcel, 'Fake.png', 'image/png', jpeg);
    raise exception 'A JPEG was stored as a PNG';
  exception when sqlstate 'PF004' then null;
  end;
  begin
    perform public.add_package_file(parcel, 'Page.html', 'text/html', encode('<html>'::bytea, 'base64'));
    raise exception 'A page was stored';
  exception when sqlstate 'PF004' then null;
  end;
  begin
    perform public.add_package_file(parcel, '', 'image/jpeg', jpeg);
    raise exception 'A file without a name was stored';
  exception when invalid_parameter_value then null;
  end;
  begin
    insert into public.package_notes (package_id, user_id, body)
    values ('e1000000-0000-4000-8000-000000000012', 'e1000000-0000-4000-8000-000000000001', 'Direct');
    raise exception 'The owner wrote a note around the functions';
  exception when insufficient_privilege then null;
  end;
  begin
    perform 1 from public.package_file_contents;
    raise exception 'The owner read the bytes around the server';
  exception when insufficient_privilege then null;
  end;
  if public.set_package_note('e1000000-0000-4000-8000-000000000021', 'Mine now') then
    raise exception 'An account wrote a note on another account''s parcel';
  end if;
  begin
    perform public.add_package_file('e1000000-0000-4000-8000-000000000021', 'Receipt.jpg', 'image/jpeg', jpeg);
    raise exception 'An account added a file to another account''s parcel';
  exception when no_data_found then null;
  end;
end;
$$;

select set_config('request.jwt.claim.sub', 'e1000000-0000-4000-8000-000000000002', true);
do $$
declare
  parcel constant uuid := 'e1000000-0000-4000-8000-000000000011';
  file_id constant uuid := current_setting('note_test.file')::uuid;
begin
  if exists (select 1 from public.package_notes) or exists (select 1 from public.package_files) then
    raise exception 'An account read another account''s note or files';
  end if;
  if public.set_package_note(parcel, 'Overwritten') or public.set_package_note(parcel, null) then
    raise exception 'An account changed another account''s note';
  end if;
  if public.delete_package_file(file_id) then
    raise exception 'An account deleted another account''s file';
  end if;
  begin
    update public.package_files set package_id = 'e1000000-0000-4000-8000-000000000021' where id = file_id;
    raise exception 'An account updated another account''s file';
  exception when insufficient_privilege then null;
  end;
end;
$$;

set local role anon;
select set_config('request.jwt.claim.sub', '', true);
do $$
begin
  perform public.set_package_note('e1000000-0000-4000-8000-000000000011', 'Anonymous');
  raise exception 'A visitor who is not signed in wrote a note';
exception when insufficient_privilege then null;
end;
$$;
reset role;

do $$
begin
  if (select body from public.package_notes where package_id = 'e1000000-0000-4000-8000-000000000011') <> 'Leave it with the neighbour'
      or (select count(*) from public.package_files where package_id = 'e1000000-0000-4000-8000-000000000011') <> 1 then
    raise exception 'Another account changed the owner''s note or files';
  end if;
end;
$$;

-- The service role reads a file's bytes for its owner only.
set local role service_role;
do $$
declare
  file_id constant uuid := current_setting('note_test.file')::uuid;
begin
  if decode(public.package_file_content(file_id, 'e1000000-0000-4000-8000-000000000001'), 'base64') <> '\xffd8ffe000104a464946'::bytea
      or decode(public.package_file_content(file_id, 'e1000000-0000-4000-8000-000000000001', true), 'base64') <> '\xffd8ffdb'::bytea then
    raise exception 'The service role could not read the owner''s file';
  end if;
  if public.package_file_content(file_id, 'e1000000-0000-4000-8000-000000000002') is not null then
    raise exception 'A file was read for another account';
  end if;
end;
$$;
reset role;

-- Five files a parcel, 10 MB a file, 200 MB an account.
set local role authenticated;
select set_config('request.jwt.claim.sub', 'e1000000-0000-4000-8000-000000000001', true);
do $$
declare
  parcel constant uuid := 'e1000000-0000-4000-8000-000000000011';
  pdf constant text := encode('%PDF-1.7 synthetic'::bytea, 'base64');
begin
  for n in 2..5 loop
    perform public.add_package_file(parcel, 'Page ' || n || '.pdf', 'application/pdf', pdf);
  end loop;
  begin
    perform public.add_package_file(parcel, 'Page 6.pdf', 'application/pdf', pdf);
    raise exception 'A sixth file was stored';
  exception when sqlstate 'PF001' then null;
  end;
  begin
    perform public.add_package_file('e1000000-0000-4000-8000-000000000012', 'Big.jpg', 'image/jpeg',
      encode('\xffd8ff'::bytea || decode(repeat('00', 10485758), 'hex'), 'base64'));
    raise exception 'A file over 10 MB was stored';
  exception when sqlstate 'PF003' then null;
  end;
  perform public.add_package_file('e1000000-0000-4000-8000-000000000012', 'Largest.jpg', 'image/jpeg',
    encode('\xffd8ff'::bytea || decode(repeat('00', 10485757), 'hex'), 'base64'));
end;
$$;
reset role;

-- The account's other files fill its 200 MB but for one byte: rows alone, their size is what counts.
insert into public.package_files (package_id, user_id, name, content_type, size)
select 'e1000000-0000-4000-8000-000000000013', 'e1000000-0000-4000-8000-000000000001', 'Filler ' || n || '.pdf', 'application/pdf',
  case when n = 1 then 10485760 - (19 * 10485760 - 209715199
    + (select sum(size) from public.package_files where user_id = 'e1000000-0000-4000-8000-000000000001'))
  else 10485760 end
from generate_series(1, 19) as n;

set local role authenticated;
select set_config('request.jwt.claim.sub', 'e1000000-0000-4000-8000-000000000001', true);
do $$
declare
  pdf constant text := encode('%PDF-'::bytea, 'base64');
  filler uuid;
begin
  if (select sum(size) from public.package_files) <> 209715199 then
    raise exception 'The test fills % bytes', (select sum(size) from public.package_files);
  end if;
  begin
    perform public.add_package_file('e1000000-0000-4000-8000-000000000012', 'Over.pdf', 'application/pdf', pdf);
    raise exception 'A file was stored past the account''s 200 MB';
  exception when sqlstate 'PF002' then null;
  end;
  select id into filler from public.package_files where name = 'Filler 2.pdf';
  if not public.delete_package_file(filler) then
    raise exception 'The owner could not delete its file';
  end if;
  perform public.add_package_file('e1000000-0000-4000-8000-000000000012', 'Fits.pdf', 'application/pdf', pdf);
end;
$$;
reset role;

-- Deleting a parcel deletes its note, its files and their bytes.
do $$
declare
  parcel constant uuid := 'e1000000-0000-4000-8000-000000000011';
  files uuid[] := array(select id from public.package_files where package_id = parcel);
begin
  if cardinality(files) <> 5 or (select count(*) from public.package_file_contents where file_id = any (files)) <> 5 then
    raise exception 'The parcel holds % files', cardinality(files);
  end if;
  delete from public.packages where id = parcel;
  if exists (select 1 from public.package_notes where package_id = parcel)
      or exists (select 1 from public.package_files where id = any (files))
      or exists (select 1 from public.package_file_contents where file_id = any (files)) then
    raise exception 'A deleted parcel kept its note or files';
  end if;
end;
$$;
rollback;

-- Merging two legs joins their notes, the original's first, cut at 1,000 characters, and keeps every file.
begin;
insert into auth.users (id, email, is_anonymous) values
  ('e2000000-0000-4000-8000-000000000001', 'note-merge@example.test', false);
insert into public.packages (id, user_id, tracking_number, carrier, label) values
  ('e2000000-0000-4000-8000-000000000011', 'e2000000-0000-4000-8000-000000000001', 'NOTETEST0101', 'unknown', 'First leg'),
  ('e2000000-0000-4000-8000-000000000012', 'e2000000-0000-4000-8000-000000000001', 'NOTETEST0102', 'unknown', 'Last leg'),
  ('e2000000-0000-4000-8000-000000000013', 'e2000000-0000-4000-8000-000000000001', 'NOTETEST0103', 'unknown', ''),
  ('e2000000-0000-4000-8000-000000000014', 'e2000000-0000-4000-8000-000000000001', 'NOTETEST0104', 'unknown', '');
set local role authenticated;
select set_config('request.jwt.claim.sub', 'e2000000-0000-4000-8000-000000000001', true);
do $$
declare
  pdf constant text := encode('%PDF-'::bytea, 'base64');
begin
  perform public.set_package_note('e2000000-0000-4000-8000-000000000011', 'Ordered in May');
  perform public.set_package_note('e2000000-0000-4000-8000-000000000012', 'Signed for by the shop');
  perform public.add_package_file('e2000000-0000-4000-8000-000000000011', 'Order.pdf', 'application/pdf', pdf);
  perform public.add_package_file('e2000000-0000-4000-8000-000000000012', 'Label.pdf', 'application/pdf', pdf);
  perform public.set_package_note('e2000000-0000-4000-8000-000000000013', repeat('a', 600));
  perform public.set_package_note('e2000000-0000-4000-8000-000000000014', repeat('b', 600));
end;
$$;
reset role;
set local role service_role;
do $$
begin
  perform public.link_package_tracking('e2000000-0000-4000-8000-000000000011', 'e2000000-0000-4000-8000-000000000012');
  if (select body from public.package_notes where package_id = 'e2000000-0000-4000-8000-000000000012')
      <> E'Ordered in May\n\nSigned for by the shop' then
    raise exception 'The merged note reads %', (select body from public.package_notes where package_id = 'e2000000-0000-4000-8000-000000000012');
  end if;
  if (select array_agg(name order by name) from public.package_files where package_id = 'e2000000-0000-4000-8000-000000000012')
      <> array['Label.pdf', 'Order.pdf']
      or (select count(*) from public.package_file_contents) <> 2 then
    raise exception 'The merge lost a file';
  end if;
  perform public.link_package_tracking('e2000000-0000-4000-8000-000000000013', 'e2000000-0000-4000-8000-000000000014');
  if (select body from public.package_notes where package_id = 'e2000000-0000-4000-8000-000000000014')
      <> repeat('a', 600) || E'\n\n' || repeat('b', 398) then
    raise exception 'The merged note was not cut at 1,000 characters';
  end if;
  if (select count(*) from public.package_notes) <> 2 then
    raise exception 'A merged leg kept its own note';
  end if;
end;
$$;
reset role;

-- Deleting the account deletes its notes and files.
delete from auth.users where id = 'e2000000-0000-4000-8000-000000000001';
do $$
begin
  if exists (select 1 from public.package_notes) or exists (select 1 from public.package_files)
      or exists (select 1 from public.package_file_contents) then
    raise exception 'A deleted account kept its notes or files';
  end if;
end;
$$;
rollback;

-- Keeping a link's parcel takes the device's note; keeping it again adds a note it lacks, once.
begin;
insert into auth.users (id, email, is_anonymous) values
  ('e3000000-0000-4000-8000-000000000001', 'note-keeper@example.test', false);
set local role service_role;
select set_config('note_test.first_link', public.create_one_off_parcel('NOTETEST0201', 'unknown', null, null, repeat('c', 64))#>>'{link,id}', true);
reset role;
set local role authenticated;
select set_config('request.jwt.claim.sub', 'e3000000-0000-4000-8000-000000000001', true);
do $$
declare
  answer jsonb;
  kept uuid;
begin
  begin
    perform public.claim_parcel_link(current_setting('note_test.first_link'), repeat('c', 64), 'Boots', repeat('n', 1001));
    raise exception 'A note of 1,001 characters was kept';
  exception when invalid_parameter_value then null;
  end;
  answer := public.claim_parcel_link(current_setting('note_test.first_link'), repeat('c', 64), 'Boots', E' Size 42 \n');
  kept := (answer->>'package_id')::uuid;
  if answer->>'outcome' <> 'kept' or (select body from public.package_notes where package_id = kept) <> 'Size 42' then
    raise exception 'The kept parcel lost the device''s note: %', answer;
  end if;
  perform set_config('note_test.kept', kept::text, true);
end;
$$;
reset role;
set local role service_role;
select set_config('note_test.second_link', public.create_one_off_parcel('NOTETEST0201', 'unknown', null, null, repeat('d', 64))#>>'{link,id}', true);
select set_config('note_test.third_link', public.create_one_off_parcel('NOTETEST0201', 'unknown', null, null, repeat('e', 64))#>>'{link,id}', true);
reset role;
set local role authenticated;
do $$
declare
  kept constant uuid := current_setting('note_test.kept')::uuid;
begin
  if public.claim_parcel_link(current_setting('note_test.second_link'), repeat('d', 64), '', 'Size 42')->>'outcome' <> 'already'
      or (select body from public.package_notes where package_id = kept) <> 'Size 42' then
    raise exception 'The same note was added twice';
  end if;
  perform public.claim_parcel_link(current_setting('note_test.third_link'), repeat('e', 64), '', 'Gift for Sam');
  if (select body from public.package_notes where package_id = kept) <> E'Size 42\n\nGift for Sam' then
    raise exception 'The second device''s note reads %', (select body from public.package_notes where package_id = kept);
  end if;
end;
$$;
reset role;
rollback;

-- A parcel quiet or archived on the device joins the account so, copied or taken over; one the account had already
-- keeps its own settings.
begin;
insert into auth.users (id, email, is_anonymous) values
  ('e3100000-0000-4000-8000-000000000001', 'quiet-keeper@example.test', false);
set local role service_role;
select set_config('quiet_test.copied', public.create_one_off_parcel('QUIETTEST0401', 'unknown', null, null, repeat('7', 64))#>>'{link,id}', true);
select set_config('quiet_test.again', public.create_one_off_parcel('QUIETTEST0401', 'unknown', null, null, repeat('8', 64))#>>'{link,id}', true);
select set_config('quiet_test.taken', public.create_one_off_parcel('QUIETTEST0402', 'unknown', null, null, repeat('9', 64))#>>'{link,id}', true);
reset role;
set local role authenticated;
select set_config('request.jwt.claim.sub', 'e3100000-0000-4000-8000-000000000001', true);
do $$
declare
  answer jsonb;
  kept public.packages;
begin
  -- Its lookup has another link: the account gets a copy.
  answer := public.claim_parcel_link(current_setting('quiet_test.copied'), repeat('7', 64), 'Lamp', p_muted => true);
  select * into kept from public.packages where id = (answer->>'package_id')::uuid;
  if answer->>'outcome' <> 'kept' or kept.one_off or not kept.notifications_muted or kept.archived_at is not null then
    raise exception 'A quiet parcel was copied as %, muted %, archived %', answer, kept.notifications_muted, kept.archived_at;
  end if;
  answer := public.claim_parcel_link(current_setting('quiet_test.again'), repeat('8', 64), 'Lamp', p_archived => true);
  select * into kept from public.packages where id = kept.id;
  if answer->>'outcome' <> 'already' or kept.archived_at is not null then
    raise exception 'A parcel the account had was archived by another device''s claim: %', answer;
  end if;
  -- Its lookup's only link: the account takes the lookup over.
  answer := public.claim_parcel_link(current_setting('quiet_test.taken'), repeat('9', 64), 'Desk', p_archived => true);
  select * into kept from public.packages where id = (answer->>'package_id')::uuid;
  if answer->>'outcome' <> 'kept' or kept.one_off or kept.notifications_muted or kept.archived_at is null then
    raise exception 'An archived parcel was taken over as %, muted %, archived %', answer, kept.notifications_muted, kept.archived_at;
  end if;
end;
$$;
reset role;
rollback;

-- A link shows neither the note nor the files, to its viewers or its owner. A gift's sender may show its note and who
-- it is from before delivery, never its name.
begin;
insert into auth.users (id, email, is_anonymous) values
  ('e4000000-0000-4000-8000-000000000001', 'note-sharer@example.test', false);
insert into public.packages (id, user_id, tracking_number, carrier, label) values
  ('e4000000-0000-4000-8000-000000000011', 'e4000000-0000-4000-8000-000000000001', 'NOTETEST0301', 'unknown', 'Trail shoes');
set local role authenticated;
select set_config('request.jwt.claim.sub', 'e4000000-0000-4000-8000-000000000001', true);
do $$
declare
  parcel constant uuid := 'e4000000-0000-4000-8000-000000000011';
  answer jsonb;
begin
  perform public.set_package_note(parcel, 'Private words 7Q');
  perform set_config('note_test.file', public.add_package_file(
    parcel, 'Private receipt 7Q.pdf', 'application/pdf', encode('%PDF-'::bytea, 'base64'))->>'id', true);
  answer := public.share_owned_package(parcel, p_show_number => true, p_gift => true,
    p_gift_words => '{"name":"Trail shoes","note":"Happy birthday!","from":"Sam","early":true}');
  if answer->'gift_words' <> '{"name":"Trail shoes","note":"Happy birthday!","from":"Sam","early":true}' then
    raise exception 'The share did not keep early: %', answer;
  end if;
  perform set_config('note_test.link', answer->>'id', true);
  begin
    perform public.share_owned_package(parcel, p_gift_words => '{"name":null,"note":null,"from":null,"early":"yes"}');
    raise exception 'early was stored as text';
  exception when check_violation then null;
  end;
  begin
    perform public.share_owned_package(parcel, p_gift_words => '{"name":null,"note":null,"from":null,"later":true}');
    raise exception 'An unknown gift key was stored';
  exception when check_violation then null;
  end;
end;
$$;
reset role;
set local role service_role;
do $$
declare
  link_id constant text := current_setting('note_test.link');
  answer jsonb;
begin
  answer := public.parcel_link_view(link_id);
  if answer::text ~ '7Q' or answer::text like '%' || current_setting('note_test.file') || '%'
      or answer->'package' ?| array['note', 'files', 'label', 'package_notes', 'package_files'] then
    raise exception 'A link carries the private note or its files: %', answer;
  end if;
  if answer#>'{link,gift_words}' <> '{"note":"Happy birthday!","from":"Sam","early":true}' then
    raise exception 'A viewer reads the early gift words as %', answer#>'{link,gift_words}';
  end if;
  update public.parcel_links set gift_words = gift_words - 'early' where id = link_id;
  if public.parcel_link_view(link_id)#>'{link,gift_words}' <> 'null'::jsonb then
    raise exception 'A viewer read the gift words before delivery';
  end if;
  update public.parcel_links set gift_words = gift_words || '{"early":false}' where id = link_id;
  if public.parcel_link_view(link_id)#>'{link,gift_words}' <> 'null'::jsonb then
    raise exception 'A viewer read the gift words with early off';
  end if;
  update public.parcel_links set gift_words = gift_words || '{"early":true}', gift = false where id = link_id;
  if public.parcel_link_view(link_id)#>'{link,gift_words}' <> 'null'::jsonb then
    raise exception 'A link that is no gift gave its words';
  end if;
  update public.parcel_links set gift = true where id = link_id;
  update public.packages set current_stage = 'delivered' where id = 'e4000000-0000-4000-8000-000000000011';
  answer := public.parcel_link_view(link_id);
  if answer#>'{link,gift_words}' <> '{"name":"Trail shoes","note":"Happy birthday!","from":"Sam","early":true}' then
    raise exception 'A delivered gift gives its words as %', answer#>'{link,gift_words}';
  end if;
  if answer::text ~ '7Q' then
    raise exception 'A delivered gift''s link carries the private note or its files';
  end if;
end;
$$;
reset role;
rollback;
