lock table private.inbound_mail_kept, private.inbound_mailboxes in share row exclusive mode;

-- Remove mail left behind by a concurrent close before enforcing mailbox ownership.
delete from private.inbound_mail_kept k
where not exists (select 1 from private.inbound_mailboxes m where m.user_id = k.user_id);

alter table private.inbound_mail_kept add constraint inbound_mail_kept_mailbox_fk
  foreign key (user_id) references private.inbound_mailboxes(user_id) on delete cascade;

insert into public.applied_migrations (name) values ('20261118190100_mailbox_retained_mail') on conflict do nothing;

