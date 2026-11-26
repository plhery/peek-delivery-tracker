---
title: Asendia 物流查询：单号格式、状态含义、本地派送与客服
description: 查询 Asendia 跨境包裹：运单号来源与常见格式、A1 与官网状态含义、目的地本地派送说明、包裹延误或未送达处理，以及联系客服和用 Peek 跟踪的方法。
slug: asendia-tracking
published: 2026-11-26
updated: 2026-11-26
---

Asendia 由法国邮政（La Poste）与瑞士邮政（Swiss Post）合资成立，专门替网店承运跨境订单。它通常不负责最后一公里上门派送，而是将包裹从海外仓库运送到你的所在国家，再交由当地熟悉的快递或邮政公司完成投递。你可以使用商家发货邮件中的运单号，在 [Asendia 官方查询页面](https://track.asendia.com/track)查询，若为 Asendia USA 包裹，也可前往 [A1 tracking](https://a1.asendiausa.com/tracking/) 查询。

## Asendia 运单号：在哪里查找

运单号由卖家通过发货确认邮件或短信提供。Asendia 未公布统一的运单号格式、长度或范例。并非所有包裹都有运单号：Asendia 说明，部分商家为控制成本会选用不可追踪的平邮渠道；如果所用服务支持追踪，Asendia 建议直接向商家索取单号。

以下是常见的 Asendia 相关单号形态（数字均为示例）：

| 单号 | 归属说明 |
| --- | --- |
| `ASE48291736` | `ASE` 后接 8 至 37 位字母或数字：Asendia。 |
| `AS482917367US` | `AS` + 9 位数字 + `US`：Asendia。 |
| `AHOY48291736` | `AHOY` 后接 8 位字母或数字：Asendia。 |
| `ESUS482917364` | `ESUS` 后接 9 位数字：Asendia 或 [DHL eCommerce](carrier:dhl-ecommerce)。 |
| `RR482917367CH` | 国际邮政编码：属于签发该单号的邮政机构，此处为[瑞士邮政（Swiss Post）](carrier:swiss-post)。 |
| `LA482917367FR` | 法国国际邮政编码：[法国邮政（La Poste / Colissimo）](carrier:la-poste)。 |
| `EEUS482917364US0` | eBay 国际运输（eBay International Shipping）单号：Asendia 或 DHL eCommerce。 |

许多 Asendia 包裹直接使用末端派送邮政的国际单号：两位字母 + 九位数字 + 两位字母，其中最后两位代表签发邮政（`CH` 为瑞士邮政、`FR` 为法国邮政、`GB` 为[英国皇家邮政（Royal Mail）](carrier:royal-mail)）。请注意区分：`AS…US` 属于 Asendia，而 `AS…CH` 属于瑞士邮政。若单号查不到结果，Asendia 建议确认自己正在访问其官方查询页面。更多说明请见[运单号格式指南](guide:tracking-number-formats)（英文）。

## Asendia 物流状态含义

Asendia USA 的 A1 查询页面仅以英文记录自身扫描，并原样转发末端派送伙伴的状态文字。以下按包裹流转顺序列出最常见的状态：

| Asendia 显示 | 对你的含义 |
| --- | --- |
| `Shipment Information Received` | 商家已向 Asendia 发送包裹电子信息，包裹尚未开始移动。 |
| `Label created` | 商家已打印面单。 |
| `Manifest to Asendia created` | 商家已将包裹列入交接清单，Asendia 尚未处理。 |
| `Check-in Asendia facility` | 首次抵达 Asendia 分拨中心并完成入库扫描。 |
| `Processed by Asendia` | 包裹已在 Asendia 手中并完成处理。 |
| `Sorted by Asendia` | 已完成国际线路分拣。 |
| `Dispatched by Asendia` | 已离开 Asendia，开始国际干线运输。 |
| `Pre-Shipment, USPS Awaiting Item` | 来自 [USPS](carrier:usps) 的扫描：USPS 已收到预报信息，正等待包裹移交。 |
| `Arrived at USPS Regional Facility` | 美国末端派送伙伴已收到包裹。 |
| `airline misroute – under investigation with airline` | 航班错运，航空公司正在调查处理。 |

全球查询站点 track.asendia.com 则显示较为概括的节点：`Information Received`、`Handed to Asendia`、`Departed from Asendia`、`Arrived at Destination`、`Customs`、`In delivery` 和 `Delivered`，出现异常时会标记 `Exception`。

有两点常令收件人困惑：一是包裹可能出现在意料之外的城市或国家，Asendia 表示其包裹会经由多国中转中心路由；二是状态连续几天不变并不代表停滞，Asendia 说明跨境在途期间两次扫描之间可能间隔数天。

## Asendia 派送失败：错过派送与自提点

实际负责上门派送的是你所在国家的本地派送公司，而非 Asendia，具体承运商会在物流记录中显示。Asendia 在官网页面中点名瑞士的派送伙伴为瑞士邮政，法国为 Colissimo 或 Chronopost；对于英国、德国和美国，其页面未列明固定合作伙伴。

Asendia 表示，大多数合作伙伴会尝试派送两到三次，每次都会留下纸质或电子通知单，说明如何重新预约派送或到附近邮局自提（如当地支持）。部分 Asendia 服务允许重新安排时间；如不支持，合作伙伴会再次派送或将包裹存放在就近的邮局或自提点。Asendia USA 说明第二次派送通常在下一个工作日进行，部分伙伴也允许在首次派送失败后修改地址。

包裹的保管期限遵循本地派送伙伴的规则：例如瑞士邮政对国际邮件通常保管 15 天。逾期无人领取的包裹会被退回，具体退件流程因国家而异。

## Asendia 包裹不动、遗失或未送达

**没有更新。** Asendia 建议先查询最新记录，包裹可能已在送达途中。详见[物流停止更新的原因](guide:tracking-not-updating)（英文）。

**已交由本地承运商。** 你可以直接向物流中显示的本地派送公司咨询更多信息。如果对方无法解决，或记录显示包裹仍在 Asendia 手中，Asendia 建议联系卖家。

**遗失、破损或严重延误。** 请直接联系购买商品的网店。Asendia 说明，如有需要，将由商家向 Asendia 发起理赔。Asendia 未面向收件人公布理赔时限。

**显示已签收但未收到。** Asendia 常见问题页面说明，如果你对签收记录有异议，请联系商家客服处理。

**海关清关。** Asendia 说明，清关代理（通常为末端派送的邮政或快递公司）可能会在派送前或派送时要求缴纳税费；如未及时缴纳，包裹将被退回。各国清关规则详见[海关滞留包裹指南](guide:customs)（英文）。

## Asendia 客服与联系方式

Asendia 未向购物者公布客服电话、电子邮箱或服务时间。收件人需通过其购物者支持页面上的“Ask Asendia”虚拟助手联系：打开助手、输入 `contact` 并填写简短表单。Asendia 建议按以下顺序处理：先查询物流，再咨询物流中列出的本地派送公司，最后联系卖家。

- **英国与爱尔兰：** asendia.co.uk 联系页面同样将购物者引导至该支持页面。书面信函可寄往：Asendia UK Head Office, Unit 8-12 The Heathrow Estate, Silver Jubilee Way, Hounslow, TW4 6NF。
- **美国：** asendiausa.com 购物者支持页面提供相同指引，并附有 A1 查询链接。

由于 Asendia 的直接客户是商家而非个人收件人，通常只有商家能够推进后续处理。关于防诈骗，Asendia 声明其邮件仅来自 `@asendia.com` 域名，绝不会通过邮件索要密码或发送付款链接。

## 使用 Peek 跟踪 Asendia 包裹

对于 `ASE`、`AS…US` 和 `AHOY` 单号，[Peek](/) 会直接识别为 Asendia；对于 `ESUS` 单号，Peek 会同时向 Asendia 和 DHL eCommerce 查询。你也可以直接粘贴 track.asendia.com 或 A1 tracking 的链接。国际邮政单号会默认归属签发邮政；如果卖家称其由 Asendia 承运，或提供的是不含快递特征的编号，可在添加时点击“更换”手动选择 Asendia。

Peek 会优先查询 Asendia USA 的 A1 系统；若 A1 无结果，则通过浏览器打开 Asendia 欧洲公司使用的 track.asendia.com。该网站需要填写收件邮编才会显示详细扫描（在 Peek 添加包裹时“收件邮编”为可选字段）；若未填邮编，Peek 会读取该页面显示的当前状态，并在通用物流查询服务有记录时补充历史轨迹。

当 A1 返回结果时，Peek 会展示物流状态、附带城市/地区与国家的完整轨迹、重量、目的地国家以及末端派送公司及其对应单号。系统不会显示预计送达日期，也会过滤收件地址、邮编、订单参考号以及缺少时区信息的扫描记录。若末端派送公司属于 Peek 支持的快递（如 USPS、瑞士邮政或法国邮政），Peek 可接力在对方系统继续追踪，包裹页面会显示“由 <快递公司> 派送”。Peek 最快每 10 分钟检查一次（包裹进入派送当天最快每 2 分钟一次，读取 track.asendia.com 时每 5 分钟一次），并在地图上标注每次扫描，支持选择“每次扫描”、“重要环节”或“仅派送当天”通知。加快查询的节奏自派送当天当地时间早上 6 点开始，前提是 Asendia 官方查询正常响应；仅通过物流查询服务跟踪时，会每小时查询一次，静默一天后每 3 小时一次，派送当天最多每 15 分钟一次。若后续交由 USPS 跟进，则改为每 30 分钟检查一次（含派送当天）。连续两天无新动态（或在“已生成面单”/“已揽收”停留满 12 小时）后，Peek 改为每小时检查一次。Peek 无法代你联系 Asendia 或卖家。

## Asendia 物流查询常见问题

### 我的 Asendia 包裹现在在哪里？

最新一条扫描记录显示了包裹所处的阶段。包裹抵达你的所在国家后，会交由当地派送公司处理：物流记录会标明该公司的名称，Asendia 也建议直接向这家公司查询更多细节。

### Asendia 是正规物流公司吗？

是的。Asendia 是由法国邮政（La Poste）与瑞士邮政（Swiss Post）共同拥有的邮政与包裹物流公司，业务覆盖超过 220 个国家和地区，并在 17 个国家设有办事处。其官方邮件均来自 `@asendia.com` 地址。

### Asendia 配送需要多长时间？

Asendia 不向收件人提供统一时效，建议参考商家的发货邮件。其向商家公布的参考标准并非面向收件人的承诺，例如抵达法国入境口岸后 24 至 48 小时内送达，或洲际发往瑞士目标时效为 4 至 9 天。

### Asendia 有收件人客服电话吗？

没有针对个人购物者的客服电话。请使用其支持页面上的“Ask Asendia”虚拟助手，或联系物流中显示的末端派送公司及卖家。

:::sources
- [Asendia: Shopper Support](https://www.asendia.com/shopper-support) – role, missed delivery, problems, claims via the shop, virtual assistant, customs
- [Asendia: FAQ](https://www.asendia.com/contact/faq) – owners, countries, partners, attempts, rescheduling, address change, delivered dispute, scan gaps, transit times
- [Asendia UK: FAQ](https://www.asendia.co.uk/contact/faq) – attempts, claims process
- [Asendia UK: Contact](https://www.asendia.co.uk/contact) – shopper route, Hounslow address
- [Asendia USA: Shopper Support](https://www.asendiausa.com/shopper-support) – US advice, A1 link
- [Asendia USA: FAQ](https://www.asendiausa.com/contact/faq) – second attempt, proof of delivery
- [Asendia USA: A1 Tracking](https://a1.asendiausa.com/tracking/) – English scans, partner scans
- [Asendia: Track](https://track.asendia.com/track) – global tracking milestones
- [Asendia: Delivery to France](https://www.asendia.com/send-to-france) – Colissimo, Chronopost, transit
- [Asendia: Delivery to Switzerland](https://www.asendia.com/send-to-switzerland) – Swiss Post, transit
- [Asendia: Europe](https://www.asendia.com/destination/europe) – UK partners unnamed
- [Asendia: Fraud Protection](https://www.asendia.com/fraud-protection) – email addresses
- [Swiss Post: Collection note](https://www.post.ch/en/receiving-mail/collection-note) – 15 days for international consignments
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – what Peek shows
:::
