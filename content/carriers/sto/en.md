---
title: STO Express tracking: 申通快递 numbers, statuses, contact
description: Track an STO Express (申通快递) parcel in China: what its numbers look like, what 签收 and STO's other scans mean, where parcels wait and how to reach STO.
slug: sto-express-tracking
published: 2026-11-03
updated: 2026-11-03
---

STO Express (申通快递, Shentong) is a Chinese express company that started in 1993 and calls itself China's first private express company. An STO number usually belongs to a parcel inside China, often an online order on its way to you, a friend or a forwarder's warehouse. Track it on [STO's site](https://www.sto.cn/pc), in the `查快递` (track a parcel) box, which is in Chinese, or in the box above.

## STO Express tracking number: what it looks like

STO's numbers (`快递单号`) are digits only. Peek's STO lookup takes 12, 13 or 15 digits; the newest waybills have 15. With invented digits:

| Number | What it is |
| --- | --- |
| `770000000000000` | 77 and 13 more digits: an STO Express waybill. [BRT](carrier:brt) and Yunda also use 15 digits, so Peek checks with STO. |
| `000000000000` | 12 digits: an older STO shape, shared by [FedEx](carrier:fedex), [SF Express](carrier:sf-express), [ZTO Express](carrier:zto) and many others. |
| `0000000000000` | 13 digits: an older STO shape too, which Peek doesn't link to STO on its own. |

Spaces don't matter in Peek.

**Where to find it.** The seller gives it with the order's shipping details, and it's on the waybill (`快递面单`) stuck on the parcel. If you can't find it, see [how to find your tracking number](guide:find-tracking-number).

**On STO's site.** Type the number in the `查快递` box on sto.cn and press `查询` (search). STO's service agreement says its website, app and WeChat account give tracking for one year from the day the parcel was sent.

## STO Express tracking statuses explained

Each scan in STO's tracking has a type in Chinese, a time on China's clock (UTC+8) and the STO site that made it. Peek shows these types in English:

| STO's scan | In Peek | Peek's status | What it means for you |
| --- | --- | --- | --- |
| `收件` | "Picked up" | Received by carrier | STO collected the parcel from the sender. |
| `发件` | "Departed", with "for" and the next site when STO names it | In transit | It left an STO site for the next one. |
| `到件` | "Arrived" | In transit | It reached a sorting centre or an outlet. |
| `派件` | "Out for delivery" | Out for delivery | A courier has it on the round. |
| `驿站代收` | "At a parcel station for collection" | Ready for pickup | A parcel station (`驿站`) took it in for you. Go and collect it. |
| `柜机代收` | "In a parcel locker for collection" | Ready for pickup | It's in a parcel locker. Go and collect it. |
| `客户签收` | "Delivered" | Delivered | Someone signed for it. STO also records this when a station or locker signs for the parcel. |
| `签收` | "Delivered" | Delivered | Signed for. |

Other scan types keep STO's Chinese words in Peek. A scan type that names a return (`退回` or `退件`) shows as "Return to the sender started" (Needs attention). Delivery scans after it are the trip back: "Out for delivery back to the sender", "In a parcel locker or station on its way back" and "Returned to the sender".

**Sent to a forwarder?** Then `签收` means the forwarder's warehouse signed for it, and STO's part is over. The forwarder's own number takes over from there.

## STO Express delivery in China: couriers, stations and lockers

STO says its network reaches 99.7% of China's townships. Its service agreement lets it deliver a parcel in one of four ways:

- to you;
- to someone living with you, or a person you named to take it;
- to a collection point (`代收点`) or a pickup locker (`自提柜`) in the area of the address;
- in another way you or the sender asked for.

Once the parcel is left one of these ways with its outer packaging intact, the agreement counts STO's service as done. So "Ready for pickup" in Peek is normal: the parcel waits at a station or in a locker near the address. The pickup code and the station's address are in STO's own tracking, not in Peek.

To change the recipient's details, STO's online customer service has a topic for it (`修改收件人信息`): ask there (contact below), or ask the seller.

## STO parcel not updating, lost or returned

**Nothing yet.** STO's tracking answers nothing for a number it doesn't know yet, and nothing for one past its one-year limit. If it stays empty, check the number with the seller. Meanwhile, a parcel filed under STO Express in Peek shows "Waiting for the carrier".

**No update.** When a parcel still on its way has had no new scan for 4 days, Peek notes that parcels often go quiet, and keeps asking STO. No tracking update (`快递单号没有记录更新`) is one of the topics in STO's online customer service. See [why tracking stops updating](guide:tracking-not-updating) and [tracking parcels from China](guide:tracking-from-china).

**Lost or damaged.** Ask the seller first: the seller booked STO, not you. Under STO's service agreement, the sender, or someone the sender names, claims within 30 days of learning of the loss or damage, with proof. Without insurance (`保价`), STO pays the actual loss up to 500 yuan, or up to 100 yuan for documents. An insured parcel is paid up to its declared value. Without insurance or proof of value, STO pays three times the shipping fee. If STO is late through its own fault, it waives the shipping fee. See [lost parcels](guide:lost-parcel).

**Returned.** STO may send a parcel back when the address is unclear, the recipient refuses it, or two delivery attempts fail through no fault of STO's. The sender pays for it. In Peek, you see "Return to the sender started" and then the trip back. See [returned to sender](guide:returned-to-sender).

**Delivered but not there.** `签收` and `客户签收` mean someone signed: you, someone living with you, a person you named, or a station or locker that took the parcel in for you. Check parcel stations and lockers near the address, your building's reception and, if the parcel went to one, the forwarder. To complain, use the complaint button (`投诉反馈`) in STO's online customer service. See [delivered but not received](guide:delivered-not-received).

## STO Express contact and customer service

As read on 1 November 2026, STO lists:

- **Online customer service** on page.sto.cn, linked from sto.cn as `在线咨询` (online enquiry): a chat assistant, with people answering from 8:30 to 22:00 in China. It has buttons to send a parcel, check prices and complain (`投诉反馈`).
- **WeChat**: a service account and a mini program for sending parcels. STO is on Douyin too. All three are at the foot of sto.cn.

This page lists no phone number: Peek gives only contacts read in the text of STO's own pages, and none of them gives one. For the order itself, ask the seller.

## Track an STO Express parcel with Peek

An STO number's shape alone doesn't tell Peek it's STO's. Paste it all the same: [Peek](/) follows more than 3,500 carriers.

For a 77 number with 15 digits, Peek asks STO Express and BRT at once, and shows "Checking tracking services…" meanwhile. If STO has the parcel, the line under the number names STO Express and says "has this parcel", and Peek files it there. If not, it says "not found yet · Peek keeps checking". Add it all the same: the parcel shows "Probably STO Express" and "Waiting for STO Express’s first scan", and Peek keeps asking STO every 6 hours. After a week with nothing, it asks you to check the number with the sender or choose another carrier.

Peek asks STO about 77 numbers only. For a 12-digit number, it asks some of the other carriers that use the shape (DHL and BRT among them), then tracking services. If they have the parcel and name STO Express, Peek files it there and asks STO at its next check.

To have Peek read STO's own tracking from the start, tap "Change" and pick STO Express under "Fits this number" (12 digits, or 15 starting with 77). For 13 digits, or 15 not starting with 77, pick it under "All carriers" or search for STO or 申通. On a parcel you've already added, use "Change carrier". Peek's STO lookup takes only 12, 13 or 15 digits: pick STO Express for any other number, and the line under the number says STO Express "doesn’t issue numbers like this one".

Peek reads STO's own tracking, the one behind its online customer service. For each scan it shows the step (table above), the city of the STO site that made it (the site's Chinese name where Peek can't place it), and the time, read on China's clock and shown in your device's time zone. It shows a repeated scan once, and the parcel's weight when STO's scales recorded it. It leaves out STO's full line for each scan and, with it, couriers' and stations' names and phone numbers, pickup addresses and codes, and who signed. STO gives no expected delivery date, so Peek shows none. Peek follows the STO number only: a forwarder's number is another parcel to add.

It checks every 10 minutes, and every 2 minutes from 6 a.m. local time on the day STO scans the parcel out for delivery, while STO's own tracker answers (through tracking services, every 15 minutes). After two days without news (12 hours while the parcel still shows "Label created" or "Received by carrier"), Peek checks hourly. It can notify you of "Every scan", "Important steps" or "Delivery only". It can't contact STO, the forwarder or the seller for you.

## Questions about STO Express tracking

### Is STO Express legit?

Yes. STO Express started in 1993, calls itself China's first private express company and says it pioneered franchising in Chinese express delivery. It's listed on China's A-share market and says it has 75 sorting centres, 5,075 independent outlets and more than 98,000 local stores.

### What does 签收 mean on STO tracking?

Signed for: Peek shows `签收` and `客户签收` as "Delivered". You, someone living with you, a person you named or a parcel station or locker signed. `驿站代收` and `柜机代收` mean a station or a locker holds it for you to collect.

### Why does my STO number show no tracking?

STO hasn't scanned the parcel yet, the number is wrong, or the parcel was sent more than a year ago: STO gives tracking for one year. Check the number with the seller, or add it to Peek, which keeps checking.

### Where is the pickup code for my STO parcel?

In STO's own tracking on sto.cn, with the station or locker that holds the parcel. Peek leaves pickup codes and addresses out. It shows "At a parcel station for collection" or "In a parcel locker for collection" once the parcel is waiting for you.

### How do I contact STO Express?

Through the online customer service on STO's site: people answer there from 8:30 to 22:00 in China, and it has a complaint button (`投诉反馈`). For a shop order, ask the seller first.

:::sources
- [STO Express: 申通快递官网 (home)](https://www.sto.cn/pc) – tracking box, WeChat service account and mini program, Douyin
- [STO Express: 关于我们 (about STO)](https://www.sto.cn/pc/about) – started 1993, first private express company, franchise model, A-share listing, sorting centres, outlets, stores, township coverage
- [STO Express: 申通快递服务协议 (express service agreement)](https://www.sto.cn/h5/standard) – tracking for one year, ways of delivering, returns, compensation with and without insurance, claims within 30 days, late delivery
- [STO Express: 在线客服 (online customer service)](https://page.sto.cn/ued-projects/sto-customer-onlinekf?fromSource=pc-stocn&pc=true) – chat, human service 8:30–22:00, complaints, topics for no tracking update and changing the recipient's details
- [Universal Parcel Scraper: STO Express](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/sto) – number shapes, scan types, returns, China clock, weight, what Peek shows and leaves out, no expected date
:::
