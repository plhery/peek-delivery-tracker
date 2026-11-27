-- 17TRACK copies Quick Fort's rows as the tracker's whole sentence, and a delivery's ends with the recipient's street
-- and house number ("Delivered <street> 8/1"). Since 0.1434.1 the scraper reads them as Quick Fort's own result does,
-- without the street; a delivery row stored before keeps it, in its words and in its raw copy. Drop what follows
-- "Delivered" there. The place written before it stays, and the row keeps its identity, time and stage.
update public.tracking_events
set description = regexp_replace(description, '(\mDelivered)\s+\S.*$', '\1', 'i'),
    raw_data = case when raw_data ->> 'description' ~* '\mDelivered\M\s+\S.*\d'
      then jsonb_set(raw_data, '{description}',
        to_jsonb(regexp_replace(raw_data ->> 'description', '(\mDelivered)\s+\S.*$', '\1', 'i')))
      else raw_data end
where raw_data ->> 'universal_provider' = '17TRACK'
  and raw_data ->> 'reporting_carrier_key' = '192084'
  and description ~* '\mDelivered\M\s+\S.*\d';

insert into public.applied_migrations (name) values ('20261127210000_quick_fort_delivery_street') on conflict do nothing;
