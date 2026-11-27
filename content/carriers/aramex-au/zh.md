---
title: Aramex Australia（原 Fastway）物流查询：单号、状态与网点
description: 查询澳大利亚 Aramex（原 Fastway）包裹：了解 12 位标签单号格式、常见英文状态含义、错过派送处理、理赔时限及网点电话。
slug: aramex-australia-tracking
published: 2026-11-27
updated: 2026-11-27
---

Aramex Australia（原名 Fastway Couriers）是一家特许经营快递网络，负责为澳大利亚商家派送包裹。你可以凭标签单号在 [aramex.com.au](https://www.aramex.com.au/tools/track/) 查询，或直接把单号粘贴到上方输入框。单号由发货商家提供，请查看商家的发货邮件或订单页面。

## Aramex Australia 追踪单号格式与查找位置

Aramex 将单号称为追踪单号（tracking number）或标签号（label number）。面单由寄件人创建，因此单号由商家在发货邮件或订单页面中提供；如果错过了派送，快递员留下的通知卡（calling card）上也会写明该编号。Aramex 表示没有单号无法查询：如果你不知道单号，甚至不确定商家用了哪家快递，请直接询问商家。仍然找不到？请参阅 [如何查找追踪单号](guide:find-tracking-number)（英文）。

Aramex 未公布官方格式规范，其追踪系统实际使用的标签均为 12 位：前 2 位字符后接 10 位数字（以下均为虚构示例）：

| 格式（虚构示例） | 说明 |
| --- | --- |
| `KP4829103756` | 2 位字母加 10 位数字。 |
| `2X4829103756` | 1 位数字、1 位字母加 10 位数字。 |
| `A94829103756` | 1 位字母、1 位数字加 10 位数字。 |

Aramex 绝不复用单号。

外观相近的单号：[Aramex New Zealand](carrier:aramex-nz) 采用完全相同的 12 位格式，但属于独立网络；法国的 Colis Privé 同样使用 12 位单号。形如 `RR482910372AU`（2 位字母、9 位数字加 `AU`）的属于邮政件，应通过 [澳大利亚邮政（Australia Post）](carrier:australia-post) 查询。而 aramex.com 上的 Aramex 国际服务使用 11 位运单号，属于另一套追踪系统。

## Aramex Australia 物流状态含义解析

Aramex Australia 官网仅提供英文界面，每条扫描包含简短标题和下方详细说明。按包裹常见流转顺序如下：

| Aramex 状态原文 | 对你的含义 |
| --- | --- |
| `Tracking number allocated` | 商家已创建面单，Aramex 尚未拿到包裹实物。 |
| `Sender preparing parcel for pick-up` | 商家正在备货，等待快递员上门揽收。 |
| `Picked up from sender` | Aramex 快递员已从寄件人处揽收包裹。 |
| `In transit` | 已离开一个分拨站前往下一站，所示地点为离站的分拨站。 |
| `Your parcel is in our care` | 已到达某一 Aramex 分拨站。 |
| `Onboard for delivery` | 已交由当地快递员装车，预计 24 小时内送达。 |
| `Delivery delayed` | 派送出现延误。 |
| `Calling card left` | 无人签收，快递员留下了告知存放位置的通知卡。 |
| `Ready for collection` | 已到达当地自提点等待领取。 |
| `Delivered` | 已按派送指示完成投递。 |
| `Authority to Leave` | 无需签收，已放在收货地址的安全位置。 |
| `Return to sender` | Aramex 无法完成投递，正在退回寄件人。 |

当最新扫描为 `Delivery delayed` 时，追踪页会显示 `Out of time today`，表示快递员当天路线超时未送完；名为 `Your delivery has been delayed` 的弹窗会提示延误最长可达 48 小时。

`Authority to Leave` 同时也是寄件人可选的一种免签派送服务。较早期包裹还可能显示旧版措辞，如 `Consignment Information Submitted` 或 `Received into Depot`。

## Aramex 派送、安全放门与错过派送

Aramex 在周一至周五工作时间派送，周末及公共假日不派送。时效均为预估，Aramex 无法承诺具体送达时刻；追踪页会显示预计送达日。

具体派送方式由商家选定：Authority to Leave（免签安全放门）、Signature（签收）、Standard（有人在时签收，否则安全放门）、Secure PIN（邮件或短信发送安全 PIN 码）或 Click & Collect（自提点领取）。

**错过派送。** 如果包裹无法安全放置，会转交附近自提点或退回当地分拨站，快递员会留下通知卡写明存放点；Signature 与 Standard 包裹还会收到邮件或短信通知。取件时请携带载有收货地址的带照片证件、标签单号及取件通知；他人凭相同证明亦可代领。

**修改派送。** 通过通知卡或追踪页可申请重派至原地址（Aramex 说明可能产生费用）；据 Aramex 常见问题，也可付费 5.50 澳元改投至同区域新地址。你也可以前往分拨站自提，但须先联系分拨站备货。若 7 天内既未安排重派也未自提，按 Aramex 条款包裹将退回寄件人。

## Aramex 查无结果、延误或包裹丢失

**无查询结果。** 请先核对单号；Aramex 表示扫描记录最长可能需要 48 小时才会显示。

**停滞不动。** Aramex 说明受服务延误影响包裹可能看似停滞，并在 Network Service Updates 页面公布受影响的郊区。详见 [物流停止更新](guide:tracking-not-updating)（英文）。

**迟迟未到。** 先查询单号，然后在查询结果页点击 “Submit online enquiry” 提交工单。

**显示已送达但没收到。** Aramex 建议先询问邻居或楼宇管理员。送达照片或签名凭证仅可通过使用寄件人面单登记邮箱的 aramexConnect 账户查看。详见 [显示已送达却未收到](guide:delivered-not-received)（英文）。

**破损。** 请尽快告知商家，并在 24 小时内向 Aramex 提交在线查询。

由于索赔需提供发票，实务中均由商家发起理赔。Aramex 条款规定的时限如下：破损照片须在送达后 24 小时内提交，书面索赔须在 7 个工作日内提交；丢失索赔须自 Aramex 揽收起 30 个工作日内提交。每票最高赔付 1,500 澳元，且选择 Authority to Leave 并留有照片与 GPS 定位凭证时将免除丢失赔付。上述条款不减损你在《澳大利亚消费者法》下的法定权利。

**退回寄件人。** 地址错误、多次派送失败或应要求退件均会导致退回，请联系商家处理。

## Aramex Australia 联系电话与在线咨询

Aramex 联系页面未设全国收件人客服电话、收件人邮箱或 24 小时热线，而是指引通过以下方式联系：

- **在线咨询表单：** 也可在追踪结果页通过 “Submit online enquiry” 打开。
- **当地分拨站：** 周一至周五（不含公共假日，按各站点当地时间）：悉尼、墨尔本、布里斯班与霍巴特拨打 1300 327 892；珀斯 (08) 9356 5309；阿德莱德 (08) 8345 2300；堪培拉 (02) 6260 1718。多数站点营业时间为 8:00–17:00，堪培拉 16:00 关闭，霍巴特 9:00 开门。

> Aramex 提醒防范谎称因无人签收而无法派送的诈骗短信：官方链接仅以 https://aramex.com.au 或 https://aramex.co.nz 开头，绝不收取取件费用；可疑诈骗可举报至 scams@aramex.com.au。

## 使用 Peek 追踪 Aramex Australia 包裹

[Peek](/) 直接读取 Aramex Australia 官方追踪接口。由于 12 位格式与 Aramex New Zealand 及 Colis Privé 共用，Peek 会同时向这三家查询（澳大利亚用户优先查 Aramex Australia），并显示实际持有包裹的承运商；若澳新两家 Aramex 均有记录，你可通过“更改”自行切换。以 1 位字母加 1 位数字开头的标签不会被自动建议为 Aramex：请直接粘贴 aramex.com.au 追踪链接，或点“更改”手动选择 “Aramex Australia”。旧版 fastway.com.au 链接同样适用。对于寄件人的长 SSCC 条码，Peek 不会主动建议 Aramex，但若是以 `00` 开头的 20 位数字，你可以点“更改”选择 “Aramex Australia”，随后 Peek 会自动追踪并在“Aramex Australia 记录的单号为”下展示其内部标签号。

Peek 显示当前状态与预计送达日；`Delivered` 与 `In transit` 以 Peek 统一文案展示，其余节点保留 Aramex 原文。Peek 省略自提点名称与地址、营业时间、取件码、签收人、照片或签名凭证、派送备注及参考号。

Aramex 扫描时间不带时区：Peek 按扫描地点的本地时钟解析（无地点时沿用相邻扫描或悉尼时间）并在地图上呈现 Aramex 历史轨迹，还可在状态变化时通知你。

## Aramex Australia 追踪常见问题

### Aramex 单号以什么开头？

以 2 位字母、1 位数字加 1 位字母，或 1 位字母加 1 位数字开头，后接 10 位数字，例如虚构示例 `KP4829103756`。

### Aramex 在澳大利亚周末派送吗？

不派送。Aramex Australia 仅在周一至周五的工作日派送，周末与公共假日不派送。

### Aramex 派送需要多久？

取决于路线与所选服务。Aramex 的 delivery times 页面提供参考时效，追踪页面也会显示你的预计送达日。

### Aramex 和 Fastway 是同一家公司吗？

在澳大利亚是的：Fastway Couriers 已更名为 Aramex Australia，旧的 fastway.com.au 追踪链接现在会直接跳转到 Aramex 追踪页。

### Aramex 覆盖全澳大利亚吗？

是的。Aramex Australia 表示其业务覆盖除北领地（Northern Territory）外的所有州与领地。

:::sources
- [Aramex Australia: Track](https://www.aramex.com.au/tools/track/) – 状态、面板、派送方式、48 小时、签收凭证、破损、退件与自提
- [Aramex Australia: tracking page script](https://www.aramex.com.au/scripts/track/track21_AUv5.js) – Out of time today、Priority 与 Economy 标签
- [Aramex Australia: tracking service](https://www.aramex.com.au/umbraco/api/TrackingApi/GetTrackingData) – 扫描标题与说明、Delivery delayed
- [Aramex Australia: About Aramex Australia](https://www.aramex.com.au/why-aramex/about-us/) – Fastway 历史、品牌更名与加盟网络
- [Aramex Australia: How do I track my parcel?](https://www.aramex.com.au/contact-us/faqs/receiving-a-parcel/how-do-i-track-my-parcel/) – 标签单号
- [Aramex Australia: What should I do if my parcel hasn't arrived?](https://www.aramex.com.au/contact-us/faqs/receiving-a-parcel/what-should-i-do-if-my-parcel-hasnt-arrived/) – 在线咨询
- [Aramex Australia: Delivery to a Safe place](https://www.aramex.com.au/contact-us/faqs/receiving-a-parcel/delivery-to-a-safe-place/) – 安全放门规则
- [Aramex Australia: How do I arrange a redelivery?](https://www.aramex.com.au/contact-us/faqs/receiving-a-parcel/how-do-i-arrange-a-redelivery/) – 重派、5.50 澳元改投、分拨站自提
- [Aramex Australia: Can I pick my parcel up from the depot?](https://www.aramex.com.au/contact-us/faqs/receiving-a-parcel/can-i-pick-my-parcel-up-from-the-depot/) – 自提前先联系分拨站
- [Aramex Australia: Can I arrange a redelivery for a specific time?](https://www.aramex.com.au/contact-us/faqs/receiving-a-parcel/can-i-arrange-a-redelivery-for-a-specific-time/) – 不承诺具体时刻
- [Aramex Australia: What evidence is required to make a claim?](https://www.aramex.com.au/contact-us/faqs/receiving-a-parcel/what-evidence-is-required-to-make-a-claim/) – 发票要求
- [Aramex Australia: Where do you operate in Australia?](https://www.aramex.com.au/contact-us/faqs/joining-aramex/where-do-you-operate-in-australia/) – 覆盖北领地以外全澳各州
- [Aramex Australia: Who is my contact at Aramex Australia?](https://www.aramex.com.au/contact-us/faqs/find-us/who-is-my-contact-at-aramex-australia/) – 联系当地分拨站
- [Aramex Australia: Domestic conditions of carriage](https://www.aramex.com.au/terms-and-conditions/conditions-of-carriage/domestic-conditions-of-carriage/) – 7 天保管、证件、1,500 澳元赔付上限与澳大利亚消费者法
- [Aramex Australia: Limited liability](https://www.aramex.com.au/terms-and-conditions/limited-liability/) – 理赔时限与 Authority to Leave 免责
- [Aramex Australia: Contact Aramex Australia](https://www.aramex.com.au/contact-us/) – 在线表单、无全国统一热线
- [Aramex Australia: Sydney](https://www.aramex.com.au/sydney/) – 分拨站电话与营业时间（及墨尔本、布里斯班、珀斯、阿德莱德、堪培拉与霍巴特页面）
- [Aramex Australia: Online security](https://www.aramex.com.au/tools/online-security/) – 防诈骗提示、官方链接域名与举报邮箱
- [Aramex Australia: Service updates](https://www.aramex.com.au/our-services/service-updates/) – 单号不复用、郊区延误公告
- [Aramex Australia: Delivery times](https://www.aramex.com.au/tools/delivery-times/) – 工作日派送与参考时效
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Peek 从 Aramex Australia 读取的数据
:::
