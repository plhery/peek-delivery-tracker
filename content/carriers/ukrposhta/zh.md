---
title: 乌克兰邮政（Ukrposhta）物流查询：单号格式与状态含义
description: 查询乌克兰邮政（Ukrposhta）包裹：13 位数字与 UA 国际单号格式、英文物流状态中文说明、网点自提、国际理赔规则及 Peek 跟踪方式。
slug: ukrposhta-tracking
published: 2026-11-26
updated: 2026-11-26
---

乌克兰邮政（Ukrposhta，*Укрпошта*）是乌克兰国家邮政。你可以在其查询页 track.ukrposhta.ua、Ukrposhta 应用中追踪包裹，或直接将运单号粘贴到上方输入框。运单号由寄件人提供：乌克兰境内邮件为 13 位数字，国际进出境邮件为两位字母、九位数字加两位字母。

## 乌克兰邮政运单号格式

Ukrposhta 运单号有两种形态，其中 13 位纯数字单号末位为校验位。

| 格式（虚构） | 含义 |
| --- | --- |
| `0548291736543` | 乌克兰境内邮件：13 位数字。 |
| `RR482917367UA` | 从乌克兰寄出的国际邮件：两位字母 + 九位数字 + `UA`。 |
| `RR482917367US` | 寄往乌克兰的入境邮件沿用寄出地邮政的单号，以该国代码结尾。 |

没有单号？请向寄件人索取，或参阅[如何查找运单号](guide:find-tracking-number)（英文）。

[注意不要混淆](guide:tracking-number-formats)（英文）：乌克兰境内的 [Nova Poshta](carrier:nova-poshta) 运单号为 14 位数字，许多 [Meest](carrier:meest) 单号以 `G` 结尾。

## 乌克兰邮政物流状态含义

Peek 向 Ukrposhta 请求英文物流记录。常见状态大致按顺序排列如下：

| Ukrposhta 显示 | 对你的含义 |
| --- | --- |
| `Created online. Waiting acceptance` | 寄件人已[在线生成面单](guide:tracking-statuses)（英文），Ukrposhta 尚未收寄实物。 |
| `Registered` | Ukrposhta 已收寄登记。 |
| `Departed from the Branch` | 已离开寄出网点。 |
| `Arrived to the Logistics Center` | 正在物流中心分拣，发往乌克兰境内目的地。 |
| `Arrival at inward office of exchange` | 境外入境邮件已抵达乌克兰国际互换局。 |
| `Held at inward office of exchange` | 抵达时暂留，等待海关处理。 |
| `Item presented to customs` | 已提交海关查验。 |
| `Released from customs for further delivery` | 海关已放行，继续后续派送。 |
| `Departure from inward office of exchange` | 已完成清关并离开互换局，发往你所在地区。 |
| `Item out for physical delivery` | 正在派送中。 |
| `Arrived to the Branch` | 已抵达当地邮局网点，等待自提。 |
| `Final delivery` | 已签收送达，交付至收件人。 |
| `Returned to Sender (Due to Recipient's Refusal)` | 收件人拒收，正在退回寄件人。 |
| `Return: Delivered to Sender` | 已退回并交还寄件人，并非送达给你。 |

退回途中的步骤均以 `Return:` 开头。只要出现退回记录，Peek 就会将后续签收归类为“已退回寄件人”，而不是“已签收”。

## Ukrposhta 网点自提与派送

物流记录会明确说明包裹是上门派送（`Item out for physical delivery`）还是留在网点自提（`Arrived to the Branch`，Peek 显示为“待取件”）。Ukrposhta 应用可在地图上查找各网点及其邮编、地址与营业时间。具体保管期限和所需身份证件请咨询当地网点。

## Ukrposhta 包裹延误、遗失或破损

若连续数日无更新，请参阅[物流停止更新的原因](guide:tracking-not-updating)（英文）；若滞留海关，请见[海关滞留包裹指南](guide:customs)（英文）。

**进出境邮件遗失或延误。** 根据万国邮政联盟（UPU）公约规则，对于国际包裹、挂号件或保价件，寄件人或收件人均可在交寄次日起六个月内向国家邮政提出查单申请。公约规定查单免费，但乌克兰保留收费权利。遗失或破损赔款支付给寄件人；若寄件人书面放弃索赔权，则可支付给收件人。网购商品未到货时，请优先联系卖家。

**收货时发现破损。** 请在签收前当场指出破损。按同一 UPU 规则，邮政对交付前或交付时发现的破损承担责任，投递方须出具破损记录单供你共同签署，并留给你一份副本。

## Ukrposhta 客服

如遇应用问题，Ukrposhta 在 Google Play 商店页面提供的邮箱为 `mobile-app@ukrposhta.ua`。如遇进出境包裹遗失或延误，请按上述流程提交查单：依据 UPU 规则，Ukrposhta 须同时受理寄件人与收件人提出的查单申请。

## 使用 Peek 跟踪 Ukrposhta 包裹

对于符合 Ukrposhta 校验位的 13 位数字单号，[Peek](/) 会向 Ukrposhta 发起查询（若同时符合 FAN Courier 或 iMile 格式也会一并询问），当仅 Ukrposhta 查有记录时显示“Ukrposhta 有这个包裹的记录。”如果另一家快递公司也查得到，则由你自行选择。校验位不正确的 13 位数字不会归到 Ukrposhta，可在添加时点击“更换”手动选择 Ukrposhta。以 `C`、`E`、`L` 或 `R` 开头、以 `UA` 结尾且校验位有效的单号会直接识别为 Ukrposhta；校验位有效但以其他字母开头的 `UA` 单号，Peek 会同时查询 Ukrposhta 和 [Chronopost](carrier:chronopost)，若暂均无记录，则在添加后继续查询。粘贴含单号的 track.ukrposhta.ua 链接也会直接识别为 Ukrposhta。

其他国家邮政的单号不会直接分配给 Ukrposhta。当包裹发往乌克兰时，Peek 可以接着在 Ukrposhta 跟踪，你也可以点击“更换”手动选择“Ukrposhta”。

Peek 读取 Ukrposhta 应用背后的服务；当该服务失败或查不到国际进出境邮件时，Peek 会在服务器端用浏览器打开 Ukrposhta 查询网页。Peek 会在地图上标注每条扫描的地点与国家，并显示海关步骤和送达时间。Ukrposhta 返回的时间不带时区：Peek 按基辅时间解析乌克兰境内扫描，并根据地点或相邻扫描推算境外扫描的时区（因此境外时间可能存在偏差），最后统一换算为你的设备本地时间。Peek 不显示预计送达日，也会过滤 Ukrposhta 的计划日期与邮编；唯一展示的自提点信息是 `Arrived to the Branch` 对应的邮局网点。状态保留 Ukrposhta 英文原文（含 `Final delivery`）。

当你正在 Peek 中查看该包裹，或派送当天已开启通知时，Peek 最快每 10 分钟检查一次。包裹开始派送的当天，只要 Ukrposhta 自己的服务或网页正常响应，从最新扫描所在时区的早上 6 点（乌克兰境内为基辅时间）起最快每 2 分钟检查一次（若上次查询使用了服务器浏览器，则每 5 分钟一次）。其余情况下，以及连续两天无新动态（或在“已生成面单”/“已揽收”停留满 12 小时）后，均改为每小时检查一次。状态变化时，Peek 可以通知你。

## 乌克兰邮政查询常见问题

### 在英国如何查询 Ukrposhta 包裹？

在英国添加 Ukrposhta 邮件时，Peek 也会用同一单号向[英国皇家邮政（Royal Mail）](carrier:royal-mail)查询；以 `E` 开头的 EMS 单号则改向 [Parcelforce](carrier:parcelforce) 查询。一旦当地邮政出现不早于 Ukrposhta 最新记录的扫描，Peek 即转由对方继续跟踪，并保留 Ukrposhta 先前的全部历史。

### 为什么我的 Ukrposhta 运单号查不到？

请与寄件人提供的单号逐字核对。13 位纯数字单号末位为校验位，校验位不对时 Peek 不会归到 Ukrposhta。若单号无误，可能是 Ukrposhta 尚未录入：归属 Ukrposhta 的包裹会显示“等待 Ukrposhta 更新”，同时 Peek 会继续查询。如果两天后 Ukrposhta 仍查不到且无其他来源有记录，Peek 会显示“两天过去了，快递公司仍查不到这个单号。请检查单号、更换快递公司，或删除这个包裹。”此后改为每天查询一次，添加满一周后改为每周一次。若数日毫无变化，请向寄件人确认。

### 如何查询从海外寄往乌克兰的包裹？

直接粘贴寄件人提供的单号即可。Peek 会先在寄出地邮政跟踪；当寄出地邮政注明发往乌克兰或你在乌克兰添加该单号时，Peek 也会询问 Ukrposhta，待 Ukrposhta 出现不早于寄出地最新扫描的记录后即转入 Ukrposhta 接续追踪并保留早期历史。若想一开始就查 Ukrposhta，可点击“更换”选择“Ukrposhta”。注意：从德国寄出且以 `C` 开头的 DHL 包裹改由 [Meest](carrier:meest) 接续跟踪。

:::sources
- [Universal Parcel Scraper: Ukrposhta](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/ukrposhta) – 单号格式与校验位、track.ukrposhta.ua 链接、Peek 读取的应用服务与查询页、英文记录及其状态、退回记录、基辅时间与境外扫描、Peek 省略的内容
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Peek 按单号询问的快递公司、乌克兰国家邮政、转交 Ukrposhta（DHL 的 C 开头单号转交 Meest）以及由其转交 Royal Mail 或 Parcelforce、Nova Poshta 与 Meest 单号
- [Universal Postal Union: Convention Manual](https://www.upu.int/UPU/media/upu/files/aboutUpu/acts/06-manualsInThreeVolumes/actInThreeVolumesConventionManual202609En.pdf) – 六个月内的查单、乌克兰对查单收费的权利、赔款付给寄件人或经书面放弃后付给你、交付时发现的破损及其记录单
- [Google Play: Ukrposhta](https://play.google.com/store/apps/details?id=ua.ukrposhta.android.app) – 应用可查询包裹并查找网点及其邮编、地址与营业时间；应用问题的联系邮箱
:::
