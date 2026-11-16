\set ON_ERROR_STOP on
begin;
insert into auth.users (id, email) values ('19000000-0000-0000-0000-000000000051', 'amazon-order-links-test@example.test');
select set_config('request.jwt.claim.sub', '19000000-0000-0000-0000-000000000051', true);
set local role authenticated;
do $$
declare
  parcel public.packages;
  -- Synthetic share tokens.
  shared text := 'https://www.amazon.co.uk/progress-tracker/package/share/?unauthenticated=1&vt=SHARED_PACKAGE_TRACKING&shareToken=0f1e2d3c-4b5a-4968-8776-a5b4c3d2e1f0';
  other text := 'https://www.amazon.fr/progress-tracker/package/share/?unauthenticated=1&vt=SHARED_PACKAGE_TRACKING&shareToken=1a2b3c4d-5e6f-4a7b-8c9d-0e1f2a3b4c5d';
begin
  parcel := test_support.add_parcel('0F1E2D3C4B5A49688776A5B4C3D2E1F0', 'Amazon order fixture', 'amazon-order', shared);
  if parcel.tracking_url <> shared then raise exception 'The Amazon link was not saved'; end if;
  perform public.change_owned_package_carrier(parcel.id, 'amazon-order',
    replace(shared, 'www.amazon.co.uk', 'www.amazon.de'));
  begin
    perform test_support.add_parcel('0F1E2D3C4B5A49688776A5B4C3D2E1F1', 'Without a link', 'amazon-order');
    raise exception 'An Amazon order was saved without its link';
  exception when sqlstate '22023' then null;
  end;
  begin
    perform test_support.add_parcel('0F1E2D3C4B5A49688776A5B4C3D2E1F2', 'Another token', 'amazon-order', other);
    raise exception 'A link for another token was accepted';
  exception when sqlstate '22023' then null;
  end;
  begin
    perform public.change_owned_package_carrier(parcel.id, 'amazon-order', shared || '&ref_=synthetic');
    raise exception 'A link the scraper does not keep was accepted';
  exception when sqlstate '22023' then null;
  end;
  begin
    perform public.change_owned_package_carrier(parcel.id, 'amazon-order',
      replace(shared, 'www.amazon.co.uk', 'www.amazon.example'));
    raise exception 'A link off Amazon''s stores was accepted';
  exception when sqlstate '22023' then null;
  end;
  begin
    perform public.change_owned_package_carrier(parcel.id, 'amazon-order');
    raise exception 'An Amazon order lost its link';
  exception when sqlstate '22023' then null;
  end;
  begin
    perform public.change_owned_package_carrier(parcel.id, 'instabox', shared);
    raise exception 'Another carrier took an Amazon link';
  exception when sqlstate '22023' then null;
  end;
  perform public.change_owned_package_carrier(parcel.id, 'unknown');
  if (select tracking_url from public.packages where id = parcel.id) is not null then
    raise exception 'The Amazon link followed the parcel to another carrier';
  end if;
end;
$$;
reset role;
-- The table refuses a link the functions refuse, and an Amazon order without one.
do $$
begin
  update public.packages set carrier = 'amazon-order', tracking_url = null
  where tracking_number = '0F1E2D3C4B5A49688776A5B4C3D2E1F0';
  raise exception 'The table accepted an Amazon order without its link';
exception when check_violation then null;
end;
$$;
do $$
begin
  update public.packages set carrier = 'amazon-order',
    tracking_url = 'https://www.amazon.example/progress-tracker/package/share/?unauthenticated=1&vt=SHARED_PACKAGE_TRACKING&shareToken=0f1e2d3c-4b5a-4968-8776-a5b4c3d2e1f0'
  where tracking_number = '0F1E2D3C4B5A49688776A5B4C3D2E1F0';
  raise exception 'The table accepted a link off Amazon''s stores';
exception when check_violation then null;
end;
$$;
rollback;
