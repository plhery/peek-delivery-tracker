/**
 * The day the site was built, in UTC: `next.config.ts` sets it as the build starts and the build writes it
 * into the server. Where nothing built the site (tests, `next dev`), the day the server started.
 */
export const BUILD_DAY: string = process.env.PEEK_BUILD_DAY || new Date().toISOString().slice(0, 10);

/**
 * A page's day as the site shows it, in its text, its structured data and the sitemap: the build's day at
 * the latest. A page committed ahead of the day its fields name would otherwise tell readers and search
 * engines a day still to come.
 */
export function shownDay(day: string, built: string = BUILD_DAY): string {
  return day > built ? built : day;
}

/** A page's fields with its days as the site shows them. Both move together, so it is never updated before it is published. */
export function shownDays<Text extends { published: string; updated: string }>(text: Text, built: string = BUILD_DAY): Text {
  const published = shownDay(text.published, built);
  const updated = shownDay(text.updated, built);
  return published === text.published && updated === text.updated ? text : { ...text, published, updated };
}
