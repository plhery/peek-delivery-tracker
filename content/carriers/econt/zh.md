---
title: Econt 物流查询：运单号、状态与保加利亚网点
description: 查询保加利亚 Econt Express 包裹：13 位运单号查找方法、英文物流状态含义、门店与 Econtomat 快递柜、理赔及客服联系方式。
slug: econt-wuliu-chaxun
published: 2026-11-26
updated: 2026-11-26
---

Econt Express 是一家成立于 1993 年的保加利亚快递公司，提供送货上门、门店自提和 Econtomat 快递柜服务。你可以在 [econt.com](https://www.econt.com/en/services/track-shipment) 的“Track a shipment”查件，点击 Econt 邮件或短信中的链接，或直接把运单号粘贴到上方输入框。单号来自 Econt 通知或商家的发货邮件。

## Econt 运单号在哪里找

Econt 称之为货件号（shipment number）或运单号（waybill number）。Econt 的邮件和短信附带直接打开查询页面的链接；短信属于寄件人付费选项，因此不是每个包裹都会发短信。没收到消息？请询问商家，或查看[如何查找运单号](guide:find-tracking-number)（英文）。

Econt 没有公开规定单号格式，但其 XML 接入文档中的示例运单号为 13 位数字，Peek 读取的 Econt 单号也是 13 位数字，例如虚构单号 `1048291057364`。

13 位数字同样符合其他快递，包括 [DPD France](carrier:dpd-fr)、乌克兰的 Ukrposhta 和罗马尼亚的 [FAN Courier](carrier:fan-courier)，因此请核对商家邮件或短信里写的承运商名称。

## Econt 物流状态含义

econt.com 默认打开为保加利亚语，可切换为英语。以下是 Peek 读取的 Econt 英文扫描标签，按包裹流转顺序排列。

| Econt 状态 | 对你的含义 |
| --- | --- |
| `Awaiting delivery to Econt` | 寄件人已[生成面单](guide:tracking-statuses)（英文），但尚未把包裹交给 Econt。 |
| `Submitted to courier` | 出现在记录最前面时表示快递员刚从寄件人处揽收；出现在后面时表示正在派送中。 |
| `Arrival at an office` | 已到达旁边写明的 Econt 门店或物流中心，可能是你的自提门店。 |
| `Received by line-haul` | 正在枢纽间的干线运输途中，地点栏显示城际干线路线。 |
| `Change delivery terms` | 有人更改了派送条件（例如更换门店），包裹本身并未移动。 |
| `Shipment forwarding` | 正在转寄往另一地点。 |
| `Failed delivery` | 快递员未能送达，并将包裹带回门店。 |
| `Delivered` | 已送达给你，或已自提。 |
| `Delivery return` | 正在退回寄件人。 |
| `Shipment destruction` | 包裹正在被销毁，Peek 显示“需要留意”，请联系商家或 Econt。 |
| `Return receipt: Delivered` | 签收的回执已送回寄件人，不代表你的包裹有新进展。 |

`Submitted to courier` 最容易混淆：开头出现表示揽收，末尾出现表示末端派送，要结合前面的记录判断。`Failed delivery` 会在括号里附带原因。`Change delivery terms` 会写明具体变更内容。

在保加利亚语网页上，包裹在门店待取时汇总显示 `В офис`（当 Econt 标明存放门店后显示为待取件），签收后显示 `Доставена`。`Information` 只是备注信息，不算物流步骤。

## Econt 派送方式、门店与 Econtomat 快递柜

**送货上门：** 在城镇内，Econt 于下一个工作日 18:00 前送货上门；其他地区按既定班期派送。邮件或短信会告知包裹位置以及负责的门店或快递员。寄件人可以预约派送时段，或付费选择周六派送。

**门店自提：** 次工作日送达的包裹会在门店一早开门时就位。包裹在门店存放超过七天后，Econt 会按天收取仓储费。

**Econtomat 快递柜：** 全天候 24/7 开放：选择“Receive a shipment”，扫描二维码或输入两组 PIN 码，如有应付费用可在 Econt 应用中刷卡支付，随后从打开的格口取出包裹。Econtomat 可存放最大 61 x 44 x 37 厘米的包裹。

**更改派送。** Econt 表示你可以随时通过电话、邮件通知或 e-Econt 个人账号更改收件人、地点和时间，可选改投其他门店、地址、城镇或日期，或交由邻居代收。

**验货选项。** Econt 价目表提供三项寄件人可免费为你加选的服务：开箱验货（Review）、验货并测试（Review and Test）以及验货、测试并选留（Review, Test and Select）。

## Econt 包裹延误、丢失或破损

Econt 公布的参考时效为：设有 Econt 门店的地区次工作日送达，无门店地区最多三个工作日，从 Econtomat 寄出视交寄时间需一至两个工作日。多天没更新？先看[物流为什么停止更新](guide:tracking-not-updating)（英文），再联系 Econt 或商家。

**破损、少件或丢失。** 在 Econt 客服页面通过“I want to file a claim”提交理赔申请，并告知商家。

## Econt 客服电话与联系方式

Econt 在其[客户服务中心页面](https://www.econt.com/en/client-center)公布的联系方式：

- **保加利亚或希腊拨打：** 0 7001 7300，按市话或手机套餐资费计费。
- **其他国家拨打：** +359 88 565 43 21，按手机套餐资费计费。
- **在线客服：** 与“Ask Ena”对话。
- **门店查询：** 同一页面的门店查询工具可查各门店地址与营业时间。

## 用 Peek 跟踪 Econt 包裹

[Peek](/) 无法仅凭 13 位数字识别出 Econt 包裹：它会询问 Delhivery 等其他承运商；只有当你在保加利亚访问网页，或在手机地区设为保加利亚的应用中添加单号时，才会先询问 Econt。在其他地区，Peek 会在其他快递公司和物流查询服务都查不到这个单号后，最后询问 Econt。如果都查不到，页面会显示“暂未找到 · Peek 会继续查询”：此时点击“更换”选择“Econt”。或者直接粘贴带有 `/services/track-shipment/`、`/tracking/` 或 `/tracking/?num=` 及单号的 econt.com 链接，Peek 会立刻识别为 Econt。

随后 Peek 会展示自己的阶段（Econt 汇总能确定阶段时以汇总为准）、历史记录以及每次门店扫描对应的门店地图位置（Econt 用保加利亚语写的门店，只要 Peek 能定位就显示所在城镇）和签收时间。包裹待取期间，页面会显示存放它的 Econt 门店名称。Econt 不提供预计送达日期或重量。包裹件数、快递员姓名、寄件人与订单参考号会被略去；收件人姓名仅对添加该包裹的人显示在“收件人”一栏，分享链接会隐藏它。

扫描记录保留上表的英文标签；若 Econt 在签收记录中附带了签收人姓名，对添加者显示为 `Delivered: <name>`，无姓名或分享链接中则按 Peek 的文案显示；`Return receipt: …` 记录不改变状态。时间均按索非亚时间读取。在 Econt 自己的查件系统正常响应期间，当你打开包裹页，或在派送当天开启通知时，Peek 最多每 10 分钟查询一次，在 Econt 扫描派送当天的索非亚时间早上 6 点起最多每 2 分钟查询一次（仅通过物流查询服务时，为每小时查询一次，静默一天后每 3 小时一次，派送当天最多每 15 分钟一次）；其余时间每小时查询一次。你可以选择接收每次扫描、重要环节或仅派送当天提醒。连续两天无新动态（在“已生成面单”或“已揽收”状态下为 12 小时）后，Peek 改为每小时查询一次。

## 关于 Econt 物流查询的常见问题

### 可以用英文查看 Econt 物流吗？

在 Peek 里可以：Peek 会用上表的英文标签展示 Econt 扫描。econt.com 也提供英文版和“Track a shipment”查询页。

### Econt 派送需要多久？

根据 Econt 说明，在保加利亚境内，有 Econt 门店的城镇为次工作日送达，无门店地区最多三个工作日，从 Econtomat 寄出视寄件时间需一至两个工作日。

### 怎么用运单号查询 Econt 包裹？

把单号粘贴到上方输入框，再点“更换”选择“Econt”：Peek 无法仅凭数字识别 Econt。或者直接在 econt.com 的“Track a shipment”中输入单号。

:::sources
- [Econt: Track a shipment](https://www.econt.com/en/services/track-shipment) – 英文站“Track a shipment”
- [Econt: Проследяване на пратка](https://www.econt.com/services/track-shipment) – 保加利亚语站与状态标签
- [Econt: About Econt Express](https://www.econt.com/en/econt-express) – 1993 年成立、保加利亚
- [Econt: Customer service center](https://www.econt.com/en/client-center) – 电话及资费、Ask Ena、门店、“I want to file a claim”
- [Econt: Courier services](https://www.econt.com/en/services/courier-services) – 次工作日、派送时间、时段、周六派送、仓储费、变更、通知、验货选项
- [Econt: Econtomat](https://www.econt.com/en/services/econtomat) – 24/7 开放、尺寸、取件步骤
- [Econt: Имейл или SMS известяване](https://www.econt.com/developers/29-sms-izvestyavane.html) – 通知中的查询链接
- [Econt: Instructions form](https://instruction.econt.com/5cZyLrYsBZH) – 邻居代收、转寄、改选门店
- [Econt: Integration of e-shops with the information system of Econt Express](https://www.econt.com/e-econt/doc_templates/XML_manual_engl.pdf) – 13 位示例运单号
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Peek 读取范围
:::
