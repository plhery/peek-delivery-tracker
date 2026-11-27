---
title: 加拿大邮政（Canada Post）查询：单号、状态与通知单
description: 查询加拿大邮政（Canada Post）包裹：16 位与 CA 国际单号格式、英文物流状态对照、取件通知卡期限、关税清关与客服电话。
slug: canada-post-kuaidi-chaxun
published: 2026-11-25
updated: 2026-11-25
---

加拿大邮政（Canada Post / Canada Post Corporation）是负责运营加拿大全国邮政服务的联邦国有企业。你可以在加拿大邮政网站的 [Track 页面](https://www.canadapost-postescanada.ca/track-reperage/en)或免费官方应用中查询包裹，也可以直接把单号粘贴到上方查询框中。单号由寄件人提供，通常在商家的发货邮件里：加拿大邮政无法按你的姓名或地址查找包裹。如果单号以 `CA` 结尾且你在加拿大境外追踪，Peek 还可以用同一单号向你所在国家的邮政查询。

## 加拿大邮政单号格式

Peek 会将以下两种单号形态匹配给加拿大邮政（以下数字为虚构示例）：

| 单号（虚构示例） | 说明 |
| --- | --- |
| `4018 2930 5746 1826` | 16 位数字，最后一位是校验位。[DHL eCommerce](carrier:dhl-ecommerce)、[Correos Express](carrier:correos-express)、[TNT](carrier:tnt) 与 Evri 也使用 16 位数字。 |
| `RR482910576CA` | 国际邮政单号：2 位字母、9 位数字加 `CA`，`CA` 前最后一位数字是校验位。 |

加拿大邮政也可以凭 15 位取件通知卡号，或纯数字参考号（至少 11 位）查件；这类号码请按下文说明手动选择加拿大邮政。

另一家加拿大快递 Purolator 使用自有单号（12 位数字，或 3 位字母加 9 位数字），Peek 不会主动拿这类单号去问加拿大邮政。如果加拿大邮政查不到你的单号，请核对商家发货邮件里写明的承运商。更多内容见[如何查找快递单号](guide:find-tracking-number)（英文）和[快递单号格式说明](guide:tracking-number-formats)（英文）。

## 加拿大邮政物流状态含义

Peek 以英文读取加拿大邮政的扫描记录。以下为最常见的原文节点（大致按出现顺序）及 Peek 对应的阶段：

**加拿大境内**

| 加拿大邮政原文 | 对你的含义 | Peek 显示阶段 |
| --- | --- | --- |
| `Electronic information submitted by shipper` | 寄件人已制作面单，加拿大邮政尚未拿到包裹。 | “已生成面单” |
| `Item accepted at the Post Office` | 已在邮局交寄。 | “已揽收” |
| `Item picked up by Canada Post` | 加拿大邮政已从寄件人处揽收。 | “已揽收” |
| `Item processed` | 正在加拿大邮政网络中处理流转，可能多次出现。 | “运输中” |
| `Item out for delivery` | 今日正在派送途中。 | “派送中” |
| `Delivered to your community mailbox, parcel locker or apt./condo mailbox` | 已投入社区信箱、包裹自提柜或公寓信箱。 | “已签收” |
| `Delivered to recipient's safe drop location` | 已放在你指定的安全放置点。 | “已签收” |
| `Notice card left indicating where and when to pick up item` | 已尝试派送，包裹将转至邮局，通知卡写明取件地点与时间。 | “派送未成功” |
| `Item available for pickup at Post Office` | 已在邮局等待领取。 | “待取件” |
| `Final Notice; Item will be returned to sender if not collected within 10 days` | 最后提醒：10 天内不领将退回寄件人。 | “待取件” |
| `Recipient not located at address provided. Item being returned to sender.` | 所留地址查无此人，包裹正退回寄件人。 | “需要留意” |
| `Item has been returned and is enroute to the Sender` | 包裹正在退回寄件人途中。 | “运输中” |

`Delivered to your concierge or building manager` 与 `Delivered to mailroom` 同样显示为“已签收”。`Item re-routed due to processing error; Possible delay` 和 `Delivery may be delayed due to transportation delay` 提示可能延误，显示为“需要留意”。`Expected delivery date updated` 仅更新了预计送达日，包裹本身未移动。

**境外寄往加拿大**

| 加拿大邮政原文 | 对你的含义 | Peek 显示阶段 |
| --- | --- | --- |
| `International item mailed in originating country` | 已在境外交寄；加拿大邮政沿用原寄国邮政单号跟进。 | “已揽收” |
| `International item has left originating country and is en route to Canada` | 已离开原寄国，正在发往加拿大途中。 | “运输中” |
| `Item has arrived in Canada and will be presented for review` | 已抵达加拿大，等待海关审核。 | “清关中” |
| `Item was released by Customs and is now with Canada Post for processing` | 海关已放行，现由加拿大邮政接手处理。 | “运输中” |

**加拿大寄往境外：** `International item being forwarded to destination country` 与 `Item has arrived in foreign country` 显示为“运输中”，`Item presented to customs` 显示为“清关中”。

在轨迹列表中，妥投扫描显示为“已签收”，其余节点保留加拿大邮政英文原文。

## 加拿大派送未成功、邮局自提与入境清关

**派送未成功。** 需要签名或因尺寸太大放不进信箱/包裹格的包裹会送至你家门口。如果无人应门，加拿大邮政会留下取件通知卡（Delivery Notice Card）；无需签名时则可能放在安全放置点。出现 `Delivery Attempt Made / Rescheduled for delivery next business day` 后，通常会于下一个工作日再次派送。在装有门禁对讲机的楼宇里，呼叫对讲机即算作一次派送尝试。

**取件通知卡。** 留下通知卡后，Track 页面会先显示 “Notice left”，确认网点后列出附近邮局与最晚取件日期（“Pick up by”）。满 5 个日历日后会发最后通知卡（Final Notice Card）；累计满 15 个日历日仍未领取，包裹将退回寄件人。详见[派送未成功怎么办](guide:missed-delivery)（英文）和[无人认领包裹](guide:uncollected-parcel)（英文）。

**前往取件。** 请携带政府颁发的带照片身份证件（Track 要求证件上印有包裹收件地址），以及通知卡、派送通知邮件或应用里的取件条码。他人代领需携带代领人本人的带照片身份证件，以及经你签字并写明代领人姓名的通知卡（或授权信）。

**包裹自提柜。** 加拿大邮政会把自提柜钥匙放入你的信箱。包裹在柜内满 5 天未取会转至邮局，同时给你留取件通知卡。

**境外包裹清关。** 加拿大邮政会将所有国际邮件移交加拿大边境服务局（CBSA）查验。在海关审核期间，加拿大邮政说明其无法介入或代为查询进度。

**关税与税费。** 邮寄入境货值不超过 20 加元的商品免征关税和税费，来自境外亲友的礼品在 60 加元以内免征 GST。超过上述免税额时，需缴纳 5% GST（或所在省适用的 HST）；不列颠哥伦比亚省、曼尼托巴省、魁北克省和萨斯喀彻温省还加收省销售税，关税视商品品类而定。具体金额印在附于包裹的 CBSA 邮寄进口单 Form E14 上，由加拿大邮政代收（包含 9.95 加元手续费）。在包裹进入派送前，可通过 Track 页面的 “Pay” 按钮或官方应用在线缴税（可用信用卡，应用内还可用 Apple Pay 或 Google Pay）；在线缴费窗口可能较短，加拿大邮政建议开启应缴税费提醒。否则也可在收货时刷信用卡，或在邮局使用现金、银行卡、支票或加拿大邮政汇票支付。

**对税额有异议？** 你可以拒收包裹并申请重估，或先缴款再通过 Form E14 背面的 Form B2G 申请退税调整。详见[包裹卡在海关怎么办](guide:customs)（英文）。

## 加拿大寄往境外的包裹由谁派送

**末端派送方。** 对于以 `CA` 结尾且校验位有效的国际邮政单号，Peek 还会用同一单号向你所在国家的邮政查询：例如美国的 [USPS](carrier:usps)、英国的 [Royal Mail](carrier:royal-mail)（`E` 开头为 Parcelforce）、爱尔兰的 An Post、澳大利亚的 [Australia Post](carrier:australia-post)、新西兰的 [NZ Post](carrier:nz-post)、法国的 [La Poste](carrier:la-poste) 以及德国的 [DHL](carrier:dhl)。

由于加拿大邮政不向 Peek 返回目的国信息，实际衔接由你所在国家决定：在 iPhone 和 Android 应用中取决于手机地区设置，在浏览器中取决于你添加包裹时的所在国家。一旦当地邮政出现不早于加拿大邮政最新节点的扫描，Peek 就会转至当地邮政追踪并保留加拿大邮政的历史轨迹；此后一天内仍会最多每 55 分钟顺带查一次加拿大邮政。如果在加拿大境内追踪，或使用的是 16 位数字单号，包裹将始终保留在加拿大邮政名下。目的国清关规则见[包裹卡在海关怎么办](guide:customs)（英文）。

## 加拿大邮政包裹延误、丢失、退回或未收到

**无新扫描。** 当包裹在“已生成面单”、“已揽收”或“运输中”连续四天没有新扫描时，Peek 会在列表里标出最近一次扫描日期（“自10月12日周一起没有物流更新”），并在详情页提示：“包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 Canada Post 查询。”超过三周没有扫描时，提示变为：“已超过三周没有新动态。如果还没收到，请联系卖家。Peek 会持续向 Canada Post 查询。”同时附上当前承运商的求助链接，例如“向 Canada Post 寻求帮助”。CBSA 也说明，处于加拿大海关查验中的邮件无法精确追踪。另见[物流长时间不更新怎么办](guide:tracking-not-updating)（英文）。

**延误或丢失。** 加拿大邮政表示大多数延误包裹会在预计日期后 5 个工作日内送达。若超期仍未到，请先核对地址并在住处周围排查，随后在加拿大邮政的[丢失包裹页面](https://www.canadapost-postescanada.ca/cpc/en/support/kb/claims/missing-packages.page)提交客服工单申请调查（先登录以便跟进进度），时限为寄出后 90 天内（寄往美国或境外为 6 个月内）。寄件人也可申请调查，且只有寄件人能获得理赔款：若几天内未能找到包裹，加拿大邮政会关闭收件人的查询单，由你联系寄件人发起索赔。网购订单请优先联系商家。另见[包裹丢失怎么办](guide:lost-parcel)（英文）。

**显示已签收但未收到。** 根据《加拿大邮政公司法》（Canada Post Corporation Act），按惯例投递到你家或办公场所、放入信箱或交予合理代收人的邮件均视为已送达。Peek 仅显示“已签收”，但加拿大邮政自己的查件页面会标明具体投放位置（如 `Delivered to mailroom`），请先查看该处，再排查自提柜、门卫/前台及同住人员，并在 Track 查看签名，之后再申请调查。若已由目的国邮政派送，请查当地邮政记录。另见[显示已签收但未收到包裹](guide:delivered-not-received)（英文）。

**退回寄件人。** 退件送达寄件人后，Peek 会显示“已退回寄件人”。参考[包裹退回寄件人](guide:returned-to-sender)（英文）。

## 加拿大邮政客服与联系方式

- **电话：** 加拿大境内 1-866-607-6301，加拿大境外 1-416-979-3033，TTY 听障专线 1-800-267-2797。周一至周五东部时间 7:00 至 23:00，周末东部时间 9:00 至 21:00。
- **在线客服：** 在加拿大邮政[帮助页面](https://www.canadapost-postescanada.ca/cpc/en/support.page)使用在线聊天或提交工单报告问题。

一旦从加拿大寄出的包裹已交由你所在国家的邮政，请向当地邮政咨询派送与取件，可参考 [USPS](carrier:usps)、[Royal Mail](carrier:royal-mail) 和 [Australia Post](carrier:australia-post) 页面。Peek 无法代你联系加拿大邮政或商家。

> 凡声称包裹因欠费被扣并附带付款链接的短信，都不是加拿大邮政发的。你订阅后收到的官方短信来自 272727 或 55555，或来自 613-734-1002 和 613-734-1004，链接均指向 canadapost-postescanada.ca。请直接在 Track 核对税费，并将诈骗短信转发至 7726 举报。

## 使用 Peek 追踪加拿大邮政包裹

对于以 `CA` 结尾且校验位有效的国际邮政单号，[Peek](/) 会立刻识别为加拿大邮政。带有效校验位的 16 位数字适用于多家快递：Peek 会显示“正在查询物流服务…”，同时向加拿大邮政、DHL eCommerce、TNT、Correos Express 和 Evri 查询，并归入查到记录的那一家。如果是通知卡号或参考号，点“更换”并在“所有快递公司”或搜索中选择 Canada Post。包含 `searchFor=` 的加拿大邮政查件链接也可直接粘贴；包含 `#/search` 的链接仅适用于 `CA` 结尾单号，16 位单号请直接粘贴纯数字。

Peek 不经浏览器，直接向加拿大邮政查询，展示当前状态、带地点的扫描历史（在加拿大境内为城镇和省份）、预计送达日、包裹在加拿大境内进入派送且加拿大邮政给出时的派送时间窗、签收时间，以及加拿大邮政完整显示时的寄件人名称；不显示派送选项、地址、签名和签收照片。时间均转换为你设备的时区；在 peektracker.com 及 iPhone、Android 应用中，轨迹底部会写明“时间按你所在的时区显示：”及对应时区名称。

在你打开包裹页面期间，或在派送当天开启提醒时，Peek 最高每 10 分钟检查一次；在加拿大邮政自己报告包裹进入派送的当天，自当地时间早上 6 点起（若给出了派送时间窗，则从时间窗开始前半小时起）最高每 2 分钟检查一次；仅由物流查询服务提供更新时，每小时检查一次，超过一天无新动态后每 3 小时一次，在派送当天打开页面或开启提醒时最高每 15 分钟检查一次；其余时间每小时检查一次。若由其他承运商接手派送，Peek 会按实际派送方的节奏查询。当“已生成面单”或“已揽收”连续 12 小时无新扫描，或其他阶段连续 48 小时无新动态时，改为每小时检查一次。提醒可选“每次扫描”、“重要环节”或“仅派送当天”。

**境外寄往加拿大。** 境外寄出且带国际邮政单号的包裹，一旦原寄国邮政写明发往加拿大（或未标明目的地而你在加拿大追踪），Peek 会用同一单号同步询问加拿大邮政；待加拿大邮政出现不早于原寄国最新节点的扫描后即转至加拿大邮政跟进，并保留先前轨迹。由 Peek 在瑞士邮政（Swiss Post）名下追踪的 `L` 开头、`CH` 结尾单号绝不会转交加拿大邮政。

## 加拿大邮政查询常见问题

### 为什么我的加拿大邮政物流没有更新？

长途航班、海关查验和周末常会导致扫描间隔拉长。连续两天无新动态后 Peek 仍会每小时查询。如果超过预计送达日 5 个工作日仍未收到，请联系加拿大邮政申请调查。参见[物流长时间不更新怎么办](guide:tracking-not-updating)（英文）。

### 为什么 Peek 查不到我的加拿大邮政单号？

无论单号不存在、尚未扫描还是已过期，加拿大邮政都会返回相同响应，因此 Peek 在等待首次扫描前会显示“等待 Canada Post 更新”。若两天后加拿大邮政仍无记录且无其他服务应答，Peek 会显示“两天过去了，快递公司仍查不到这个单号。请检查单号、更换快递公司，或删除这个包裹。”，此后改为每天查询，添加满一周后改为每周查询。Track 列出的常见原因包括：包裹尚未交给加拿大邮政或仍在境外、寄出已超过一年、或所选邮寄服务不含追踪。请逐位核对数字：16 位单号的最后一位和邮政单号 `CA` 前的最后一位均为校验位。

### 可以用取件通知卡上的号码查件吗？

可以。粘贴卡片上的 15 位数字，点击“更换”并在“所有快递公司”中选择 Canada Post，Peek 即可用该卡号向加拿大邮政查询。

### 加拿大邮政寄往美国或英国的包裹由谁派送？

如果单号以 `CA` 结尾，则由当地国家邮政派送：美国为 USPS，英国为 Royal Mail（`E` 开头为 Parcelforce），Peek 会用同一单号查询对方。16 位数字单号则始终由加拿大邮政追踪。

:::sources
- [Canada Post: Track a package by tracking number](https://www.canadapost-postescanada.ca/track-reperage/en) – 免费应用、“Notice left”、取件截止日与证件及查无单号原因
- [Canada Post: Tracking - How to use Track](https://www.canadapost-postescanada.ca/cpc/en/support/kb/tracking/how-to-use-track.page) – 单号仅寄件人持有、无法按姓名/地址查询及通知卡号
- [Canada Post: Tracking - Tracking statuses](https://www.canadapost-postescanada.ca/cpc/en/support/kb/tracking/tracking-statuses.page) – “Notice left”、取件证件、代领、次日重派与海关说明
- [Canada Post: Parcel Services shipping in Canada - Delivery options](https://www.canadapost-postescanada.ca/cpc/en/support/articles/parcel-services-shipping-in-canada/delivery-options.page) – 上门投递、对讲机计入尝试、安全放置、5 天最后通知与 15 天保管期
- [Canada Post Support: Why wasn't my parcel delivered to my parcel locker?](https://www.canadapost-postescanada.ca/cpc/en/support/kb/receiving/parcel-lockers/why-wasnt-my-parcel-delivered.page) – 自提柜 5 天保管期
- [Canada Post Support: How to pay duty and taxes](https://www.canadapost-postescanada.ca/cpc/en/support/kb/receiving/delivery-faq/how-to-pay-duty-and-taxes-online-canada-post.page) – 9.95 加元手续费、缴费方式与时间、应用内 Apple Pay/Google Pay 及税费提醒
- [Canada Post: Claims - Missing packages](https://www.canadapost-postescanada.ca/cpc/en/support/kb/claims/missing-packages.page) – 5 个工作日、排查位置、自提柜钥匙、调查工单与申报时限
- [Canada Post Support: Find answers, contact us or report a problem](https://www.canadapost-postescanada.ca/cpc/en/support.page) – 客服电话、时间、在线聊天与客服工单
- [Canada Post Support: Recognizing spam, fraudulent emails and text messages](https://www.canadapost-postescanada.ca/cpc/en/support/kb/company-policies/privacy-security/what-do-to-if-you-get-suspicious-email.page) – 欠费诈骗短信、官方发信号码与 7726 举报
- [Canada Post: Responsible business practices](https://www.canadapost-postescanada.ca/cpc/en/our-company/corporate-sustainability/responsible-business-practices.page) – 联邦国有企业定位
- [CBSA: Importing by mail](https://www.cbsa-asfc.gc.ca/import/personal-personnel/postal-postale-eng.html) – 邮寄海关审核、20 加元免税额、Form E14、手续费及海关无法精确追踪
- [CBSA: Importing by mail or courier - Paying duty and/or taxes on imported goods](https://www.cbsa-asfc.gc.ca/import/postal-postale/dtytx-drttx-eng.html) – GST/HST/PST、20 加元与 60 加元免税限额及关税
- [CBSA: Importing by mail or courier - Disputing duty and taxes](https://www.cbsa-asfc.gc.ca/import/postal-postale/dispute-contestation-eng.html) – 拒收重估与 Form B2G 退税申请
- [Justice Laws Website: Canada Post Corporation Act](https://laws-lois.justice.gc.ca/eng/acts/C-10/page-1.html) – 邮政法主体与邮件视同送达条款
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – 单号形态、状态文案及其阶段、Peek 读取和显示的信息、国际邮政交接
:::
