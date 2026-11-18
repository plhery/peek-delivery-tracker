-- A grant may also hold parcels:edit: renaming a parcel, changing its carrier or notifications
-- and restoring an archived one. parcels:write stays what its owners allowed, adding and
-- archiving parcels, so a grant made before editing never gains it: the owner allows it in a
-- new consent, which opens a new grant.

alter table private.assistant_grants drop constraint assistant_grants_scopes_check;
alter table private.assistant_grants add constraint assistant_grants_scopes_check check (
  cardinality(scopes) between 1 and 3 and scopes <@ array['parcels:read', 'parcels:write', 'parcels:edit']::text[]
);

alter table private.assistant_codes drop constraint assistant_codes_scopes_check;
alter table private.assistant_codes add constraint assistant_codes_scopes_check check (
  scopes <@ array['parcels:read', 'parcels:write', 'parcels:edit']::text[]
);

alter table private.assistant_tokens drop constraint assistant_tokens_scopes_check;
alter table private.assistant_tokens add constraint assistant_tokens_scopes_check check (
  cardinality(scopes) between 1 and 3 and scopes <@ array['parcels:read', 'parcels:write', 'parcels:edit']::text[]
);

insert into public.applied_migrations (name) values ('20261118190000_assistant_edit_scope') on conflict do nothing;
