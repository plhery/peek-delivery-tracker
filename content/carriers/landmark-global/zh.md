---
title: Landmark Global 物流查询：LTN 单号、状态与派送商
description: 查询 Landmark Global 跨境包裹：LTN 运单号来源与格式、各英文状态中文含义、目的国末端派送合作商及停更后的联系方法。
slug: landmark-global-wuliu-chaxun
published: 2026-11-26
updated: 2026-11-26
---

Landmark Global 为网店提供跨境订单运输服务。它隶属总部位于布鲁塞尔的 Bnode 集团（比利时邮政 bpost 也属该集团），通常会在最后一程把包裹转交给收件人所在国家的本地配送公司派送。你可以用商家确认邮件中的 Landmark 运单号（LTN）在 [Landmark 网站查询页](https://track.landmarkglobal.com/) 查件，或直接粘贴到上方输入框。

## Landmark Global 运单号长什么样

Landmark 将其称为 LTN，即“为每个包裹生成的唯一编号，用于全程监控运输”。Landmark 表示你通常可以从商家处获得它，往往写在订单确认邮件里。

| 格式（虚构） | 含义 |
| --- | --- |
| `LTN48291736` | LTN 加 8 位数字：Landmark 运单号。 |
| `LTN482917365` | LTN 加 9 位数字：同样是 Landmark 运单号。 |
| `LTN482917365N1` | LTN 加 9 位数字再加 N1：带有 Landmark 后缀的运单号。 |
| `AB48291736` | 商家的订单参考号或包裹参考号，Landmark 查询页同样接受。 |
| 其他承运商单号 | 末端派送合作方为同一包裹分配的自有单号（Evri、UPS、bpost 等）。 |

Landmark 查询页一次最多可查 25 个单号，用逗号分隔。确定末端派送方后，页面会在“Delivery Partner”下显示合作方名称、对方自有单号以及对方网站链接，因此同一个包裹可能有两个单号。

LTN 后跟 7 位或 10 位数字，或后缀不是 N1，都不属于 Landmark 的标准格式：请对照邮件检查是否输错。更多单号规则见[运单号格式](guide:tracking-number-formats)（英文）。

## Landmark Global 物流状态含义

Landmark 查询页提供语言切换菜单，但 Peek 读取的是其英文页面，因此 Peek 显示的是以下英文原文。Landmark 其他语言页面的措辞可能略有不同，末端派送合作方的扫描则保留合作方自己的原文。按包裹经过的顺序排列：

| Landmark 显示 | 对你的含义 |
| --- | --- |
| `Shipment Data Uploaded` | 商家已把包裹信息上传给 Landmark，但 Landmark 尚未收到实物包裹。 |
| `Processed` | Landmark 已完成包裹处理。 |
| `Scanned at Landmark crossdock facility` | 包裹正在通过 Landmark 中转仓。 |
| `Grouped when pallet scanned to crossing (… #…)` | Landmark 已将包裹打托盘准备发往目的国；括号里标明过境通道（如 DDU 或 SLC INTERPOST）及托盘编号。 |
| `Transmitted customs information` | 清关数据已提前发送。 |
| `Customs cleared` | 海关已放行。 |
| `Departure to country of destination` | 正在国际干线运输途中。 |
| `Crossing border and in transit to carrier hub` | 已跨境，正发往末端派送合作方的分拣中心。 |
| `Departure from office of exchange` | 已从寄出国的国际互换局出口发出。 |
| `Received in destination country` | 已抵达目的国。 |
| `Out for Delivery` | 今天已交由派送员派送。 |
| `Item arrival at collection point for pick-up` | 已到达当地邮局柜台，等你去取。 |
| `Delivered` | 已签收。 |

包裹交给末端派送合作方后，扫描会以合作方自己的文案显示。Evri 的 `Out For Delivery To Courier` 表示网点已交给派送员，派送员确认揽收后才会开始派送。`Item deposited to local postal operator` 表示 Landmark 已把包裹交给你所在国家的邮政负责最后一程，并非已经送达。

## Landmark Global 派送：谁负责送达与派送未成功

在大多数国家，Landmark 不亲自派送。其网站列出了部分合作方：比利时为 bpost，加拿大为同集团的姐妹公司 Apple Express，美国为 DHL eCommerce 和 UPS。其英国页面只提到“多家物流合作伙伴”，未具体点名。在 Peek 查询过的包裹中，英国由 Evri 接手并显示其分拣中心与网点扫描，德国由 Hermes 派送，意大利、摩洛哥和哥伦比亚则由当地国家邮政派送。这些仅是实际案例而非固定规则，请以你包裹页面显示的合作方为准。

未投递通知单、取件点、保管期限和重派均遵循末端合作方的规则，而非 Landmark。如果派送时你不在家，Landmark 建议按派送员留下的说明操作，或联系客服重新预约派送或安排自提，同时说明并非所有服务都提供重派或自提。

修改收件地址：Landmark 欧洲区常见问题写明，包裹一旦生成面单便无法再改地址；美国和加拿大页面则表示能否修改取决于运输阶段。

合作方各自规则可查看 [Evri](carrier:evri)、[Hermes](carrier:hermes-de)、[UPS](carrier:ups) 或 [bpost](carrier:bpost) 页面。

## Landmark Global 包裹延误、丢失或退回

**停在 `Shipment Data Uploaded`。** 商家已把包裹信息传给 Landmark，但 Landmark 尚未收到包裹。请向商家确认发货时间。

**交接后不再更新。** 后续扫描可能只出现在末端合作方网站上，可从 Landmark 页面的“Delivery Partner”链接进入查看。详见[物流为什么停止更新](guide:tracking-not-updating)（英文）。

**丢失、破损或发错货。** Landmark 要求收件人联系卖家，由卖家发起调查并与 Landmark 沟通。Landmark 仅向其直接客户（即商家）提供保险。请尽快处理：Landmark 欧盟条款规定，商家须在 Landmark“已接收”扫描起 30 个自然日内提交索赔投诉。

**清关。** Landmark 未面向收件人发布关税说明；一般规定可参考[包裹卡在海关怎么办](guide:customs)（英文）。

**退回寄件人。** 退件会使用新的参考号运输：Landmark 页面会链接到退件自身的查询记录，Peek 也会将包裹标为“已退回寄件人”。请联系商家办理退款或补发。

## Landmark Global 客服与联系方式

Landmark 网页建议先联系商家或查阅其常见问题。

- **英国、爱尔兰与欧洲：** [客服页面](https://landmarkglobal.com/eu/en/customer-support/) 提供常见问题和“Send Email”按钮，邮箱为 ClientservicesEU@landmarkglobal-group.com，请附上运单号与货运详情。页面未列出电话、在线客服或服务时间。英国地址：Unit 3, Heathrow Logistics Park, Bedfont Rd, Feltham TW14 8EE。
- **美国与加拿大：** [客服表单页面](https://landmarkglobal.com/us/en/customer-support-form/) 列出电话 1-888-205-2251（美国东部时间周一至周五 8:00 至 20:00）以及邮箱 clientservices@landmarkglobal.com。

Landmark 的通用联系页面仅受理商务咨询，发往该处的查件请求不会被处理。一旦显示末端派送合作方，派送未成功和自提问题请直接联系该合作方。

## 用 Peek 跟踪 Landmark Global 包裹

对于 LTN 单号，[Peek](/) 会立刻识别为 Landmark Global。粘贴来自 track.landmarkglobal.com 或 landmarkglobal.com（带 `search=` 或 `trck=` 参数）的链接也能直接识别，即使里面填的是商家参考号。如果只输入商家的订单或包裹参考号，请点“更换”手动选择 Landmark Global。

Peek 读取的是 Landmark 英文页面，因此 Landmark 扫描以英文显示，合作方扫描保留各自原文，Landmark 的签收记录则显示为“已签收”。Peek 会展示状态、每个环节的地点、目的国以及末端派送合作方与对应单号，但不显示预计送达日期，并略去订单与包裹参考号以及付款提示。

Landmark 所有扫描时间均按美国中部时间记录。Peek 会按此读取并列出 Landmark 自身历史，再换算为你所在的时区显示。

当末端合作方属于 Peek 直接跟踪的承运商（如 Evri、UPS、bpost 或 Apple Express）时，包裹可转到对方继续跟进；包裹页会显示“由……派送”并列出合作方单号及链接，但并非每个包裹都会触发交接。每次扫描都会标在地图上，Peek 可按你的选择发送“每次扫描”、“重要环节”或“仅派送当天”通知。

## 关于 Landmark Global 物流查询的常见问题

### Landmark Global 是一家快递公司吗？

它是一家面向网店的跨境物流公司，通过与各地邮政和快递合作覆盖超过 220 个目的地。最后一程通常由物流记录中列出的本地合作方派送。

### Landmark Global 属于加拿大邮政吗？

不是。Landmark 自 2016 年起隶属比利时邮政 bpost 所在的 Bnode 集团。在加拿大，它把包裹交给同属 Bnode 集团的姐妹公司 Apple Express 派送，Landmark 称其覆盖加拿大 60% 的人口。

### Landmark Global 派送需要多久？

Landmark 表示时效取决于商家所选的服务和目的地。其面向寄往美国商家的页面给出的标准参考时效为 3 至 6 天。具体请查看商家的发货说明或物流记录。

:::sources
- [Landmark Global: Customer Support](https://landmarkglobal.com/eu/en/customer-support/) – LTN、先联系商家、派送未成功、改址、丢失或破损、保险、时效、邮箱与地址
- [Landmark Global: Customer Support Form (US and Canada)](https://landmarkglobal.com/us/en/customer-support-form/) – 电话、服务时间、邮箱、改址
- [Landmark Global: Contact](https://landmarkglobal.com/eu/en/contact/) – 仅限商务咨询
- [Landmark Global: Landmark Tracking](https://track.landmarkglobal.com/) – 查件、单次最多 25 个单号、语言菜单
- [Landmark Global: About us](https://landmarkglobal.com/eu/en/about-us/) – 自 2016 年隶属 Bnode
- [Landmark Global: Home](https://landmarkglobal.com/eu/en/) – 业务定位、合作方、220+ 目的地
- [Landmark Global: Parcel delivery to Belgium](https://landmarkglobal.com/eu/en/destinations/parcel-delivery-to-belgium/) – bpost
- [Landmark Global: Parcel delivery to Canada](https://landmarkglobal.com/eu/en/destinations/parcel-delivery-to-canada/) – Apple Express、隶属 Bnode、覆盖 60% 人口
- [Landmark Global: Parcel delivery to the US](https://landmarkglobal.com/eu/en/destinations/parcel-delivery-to-united-states/) – DHL E-Com、UPS、SurePost 与 UPS Ground；标准 3 至 6 天
- [Landmark Global: Parcel delivery to the UK](https://landmarkglobal.com/eu/en/destinations/parcel-delivery-to-uk/) – “多家物流合作伙伴”，未具体点名
- [Landmark Global: EU General Terms and Conditions 2026](https://landmarkglobal.com/uploads/no-index-files/2026-GTC-_-LG-EN-EU.pdf) – 商家索赔 30 个自然日时限
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – 状态、Evri 扫描、实测包裹中的合作方、末端派送方、退件、扫描时间、Peek 读取与展示内容
:::
