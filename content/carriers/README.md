# Carriers' pages

One page per carrier, for people who searched for its tracking: what its numbers look like,
what its statuses mean, and what to do when a parcel gets stuck. They live at
`/carriers/<slug>` in English and `/<language>/carriers/<slug>` in the other languages it
is written in; `/carriers` and `/<language>/carriers` list them in each language a page is
written in. A language of the app with no page yet keeps its readers on the English list: its
own list shows the English pages, out of search engines and the sitemap, and no other page links to it.
It comes back with the language's first page.

A page can also be written in a language the app does not speak: a national post's or a
local courier's page in its country's own language, such as Finnish for Posti. These
languages are `PAGE_ONLY_LANGUAGES` in [`src/lib/locale.ts`](../../src/lib/locale.ts). Such
a page has its title and text in its language and everything around them in English (the
document declares its language, the frame English); its language has no carriers' list, and the carrier's other pages, hreflang and the sitemap
name it among the carrier's languages.

- [`index.json`](index.json) lists the carriers in the order the site shows them, each as
  `{ "id", "catalog", "name", "languages" }`. `catalog` is the carrier's id in Universal
  Parcel Scraper's catalog, which gives the page its colour; `name` is the name the page
  uses; `languages` always holds `en`, and may hold the app's other languages and those
  with pages alone.
- Each id is a folder with one file per listed language, and no other.

After adding or changing a page, run `npm run guides`. It checks every file and rewrites
the list the app links ([`src/generated/carriers.ts`](../../src/generated/carriers.ts)).
`npm run test:scripts` fails while a page is incomplete or that list is stale.

## A page's file

A guide's file without its `picture` ([the guides' README](../guides/README.md)), in the
same Markdown:

```md
---
title: Quickpac tracking: find your parcel
description: One or two sentences for the search result, 110 to 165 characters (60 to 85 in Chinese), saying what the reader gets.
slug: quickpac-tracking
published: 2026-10-09
updated: 2026-10-09
---

The first paragraph is the lead: the answer in two or three sentences.

## Questions about Quickpac tracking

### Where is my Quickpac parcel?

The answer, its first sentence answering the question.

:::sources
- [Quickpac: Tracking](https://…) – what it supports
:::
```

- The page draws its title, its date and a box to track a parcel above the text, and the
  other carriers' pages below it: the text builds none of these.
- Its last `## ` section is its questions: 3 to 5 `### ` headings, each ending with `?` (`;` in Greek, `？` in Chinese),
  each answered below it. Search engines are told them as the page's questions and answers.
- It links another carrier's page by id as `[Planzer](carrier:planzer)`, which leads to
  that page in the page's language. The carrier must have a page in that language; a
  guide can link a carrier's page the same way. A page in a language with pages alone may
  link a carrier written in English only, and leads to its English page.
- `title` at most 60 characters; the English text at least 600 words, each translation
  between 0.6 and 1.6 times as many; it ends with its `:::sources`.
- Every tracking number is made up. A real one is someone's parcel.
