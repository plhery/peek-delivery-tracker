-- Accept Trusk's order link and MAX Pakket's track and trace link as optional parcel input. The scraper
-- keeps each in one form: Trusk's as https://track.trusk.com/<order>, whose order id keeps the case the
-- tracking number loses (letters in capitals, hyphens dropped); MAX Pakket's as its Mendrix app's
-- https://emens.mendrix.cloud/TrackAndTrace/tnt?Tracecode=<code>&OrderId=<order>, whose order number the
-- app needs beside the code. The functions are extended in place, after
-- 20261124094500_register_carrier_ctl_group_pl last extended them, and the tracking URL constraint after
-- 20261116204000_amazon_order last defined it.
-- Database gate: inputs added trusk: trackingUrl, validator truskCapabilityUrl, optional
-- Database gate: inputs added max-pakket: trackingUrl, validator mendrixCapabilityUrl, optional

do $migration$
declare
  anchor constant text := 'if normalized_url is not null and not (';
  signature text;
  definition text;
  carrier_name text;
  number_name text;
  expression text;
begin
  foreach signature in array array[
    'public.create_owned_package(text,text,text,text,text)',
    'public.change_owned_package_carrier(uuid,text,text,text)'
  ] loop
    definition := pg_get_functiondef(signature::regprocedure);
    if (length(definition) - length(replace(definition, anchor, ''))) / length(anchor) <> 1 then
      raise exception 'Tracking URL check changed in %', signature;
    end if;
    if position('track[.]trusk[.]com' in definition) <> 0 or position('mendrix' in definition) <> 0 then
      raise exception 'Trusk or MAX Pakket link already in %', signature;
    end if;
    -- create_owned_package names the carrier p_carrier and the number normalized_tracking;
    -- change_owned_package_carrier names them normalized_carrier and tracking_number.
    if signature like 'public.create_owned_package(%' then
      carrier_name := 'p_carrier';
      number_name := 'normalized_tracking';
    else
      carrier_name := 'normalized_carrier';
      number_name := 'tracking_number';
    end if;
    execute replace(definition, anchor, anchor || format($branches$
    (
      %1$s = 'trusk'
      and char_length(normalized_url) <= 4096
      and normalized_url ~ '^https://track[.]trusk[.]com/[A-Za-z0-9_-]{6,32}$'
      and upper(replace(substring(normalized_url from '^https://track[.]trusk[.]com/([A-Za-z0-9_-]{6,32})$'), '-', ''))
        = %2$s
    )
    or (
      %1$s = 'max-pakket'
      and char_length(normalized_url) <= 4096
      and normalized_url ~ ('^https://emens[.]mendrix[.]cloud/TrackAndTrace/tnt[?]Tracecode=' || %2$s
        || '&OrderId=[1-9][0-9]{0,11}$')
    )
    or$branches$, carrier_name, number_name));
  end loop;

  select pg_get_expr(conbin, conrelid) into expression
  from pg_constraint
  where conrelid = 'public.packages'::regclass and conname = 'packages_tracking_url_check';
  if expression is null then
    raise exception 'Tracking URL constraint missing';
  end if;
  if position('trusk' in expression) <> 0 or position('mendrix' in expression) <> 0 then
    raise exception 'Trusk or MAX Pakket link already in the tracking URL constraint';
  end if;
  execute 'alter table public.packages drop constraint packages_tracking_url_check, add constraint packages_tracking_url_check check (('
    || expression
    || $constraint$)
      or (
        carrier = 'trusk'
        and tracking_url is not null
        and char_length(tracking_url) <= 4096
        and tracking_url ~ '^https://track[.]trusk[.]com/[A-Za-z0-9_-]{6,32}$'
        and upper(replace(substring(tracking_url from '^https://track[.]trusk[.]com/([A-Za-z0-9_-]{6,32})$'), '-', ''))
          = tracking_number
      )
      or (
        carrier = 'max-pakket'
        and tracking_url is not null
        and char_length(tracking_url) <= 4096
        and tracking_url ~ ('^https://emens[.]mendrix[.]cloud/TrackAndTrace/tnt[?]Tracecode=' || tracking_number
          || '&OrderId=[1-9][0-9]{0,11}$')
      )) not valid$constraint$;
end;
$migration$;

notify pgrst, 'reload schema';

insert into public.applied_migrations (name) values ('20261124184500_trusk_max_pakket_links') on conflict do nothing;
