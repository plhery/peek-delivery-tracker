---
title: DHL eCommerce UK 物流查询：14 位单号、状态、ServicePoint 与客服
description: 查询英国 DHL eCommerce UK（原 DHL Parcel UK）包裹：14 位单号、状态含义、ServicePoint 自提与客服电话。
slug: dhl-ecommerce-uk-tracking
published: 2026-11-26
updated: 2026-11-26
---

DHL eCommerce UK（原称 DHL Parcel UK 及 UK Mail）是 DHL 在英国的本土包裹快递网络，面向网店和企业客户，提供含次日达在内的多种派送服务。你可以凭 14 位运单号（最好同时输入邮编）在 [DHL eCommerce UK 查件页面](https://track.dhlecommerce.co.uk/)追踪包裹，或直接将单号粘贴到上方输入框。运单号位于 DHL 通知消息或司机留下的投递通知卡（calling card）上；如无单号，查询页建议直接联系寄件人。

## DHL eCommerce UK 运单号格式

DHL eCommerce UK 运单号为 14 位数字。查件页面显示的单号大多以 `6012` 开头，其余以 `31`、`41` 或 `55` 开头。完全找不到单号？请向寄件人索取，或参阅[如何查找运单号](guide:find-tracking-number)（英文）。

| 单号（虚构） | 含义 |
| --- | --- |
| `60124829173648` | 英国境内运单号：14 位数字，此处以 `6012` 开头。 |
| `4829173` | 司机通知卡上的 7 位数字，输入查询页的 “Card number” 栏。 |
| `948291736` | 退货包裹的 9 位数字编号。 |

查件页面也接受客户参考号（customer reference）；使用通知卡号或参考号时，必须同时输入收件邮编才能查到包裹。

14 位数字并非 DHL 独有：[DPD UK](carrier:dpd-uk) 同样使用 14 位数字（以 `1550` 开头的单号指向 DPD UK），SEUR 与 BRT 亦然。此外，DHL eCommerce UK 与 [DHL Express](carrier:dhl-express) 彼此独立，DHL eCommerce UK 明确说明无法查询或协助处理 DHL Express 快递。2025 年 9 月，Evri 宣布 DHL eCommerce UK 将于 2026 年更名为 Evri Premium，仍作为独立于 [Evri](carrier:evri) 的 DHL 网络运营；目前查询页与联系页仍沿用 DHL eCommerce UK 名称。

## DHL eCommerce UK 物流状态含义

查件页面为英文，最新消息排在最前。以下是 Peek 收到的消息，大致按先后顺序排列：

| DHL eCommerce UK 显示 | 对你的含义 |
| --- | --- |
| `Your shipment has been collected` | DHL 已从寄件人处揽收包裹。 |
| `Your shipment is due to be delivered on Monday 02nd November 2026` | DHL 已排定派送日期，页面顶部也会同步显示为预计送达日。 |
| `Your shipment is at the delivery depot` | 已抵达负责派送的本地分拨站。 |
| `Your shipment is at the delivery location` | 处于分拨站与派送线路之间，尚未实际交付。 |
| `We're sorry but the delivery of your shipment has been delayed. We expect to deliver on the next working day` | 出现延误，DHL 预计顺延至下一个工作日派送。 |
| `We're sorry but we haven't been able to locate the delivery address from the information provided by the sender.` | DHL 凭寄件人提供的信息找不到收件地址，包裹正等待补正地址，请与寄件人核实。 |
| `Your shipment is out for delivery` | 已装车，今天由司机派送。 |
| `Your shipment is delivered` | 已签收送达，查询页有时会显示签收人姓名。 |
| `Delivery Attempted` | 司机尝试派送但未成功。 |
| `We're sorry but we haven't been able to deliver your shipment as our driver was unable to gain access to the delivery address` | 司机无法进入收件地址，派送未成功。 |
| `Your shipment is due to be delivered to your ServicePoint on Wednesday 04th November 2026` | 派送未成功后，DHL 计划于该日将包裹转送至 ServicePoint 自提点，目前尚未到达。 |
| `Delayed` | 派送未成功后出现延误，包裹仍在转运中。 |
| `Collected` | 你已亲自取走包裹。 |
| `We're sorry but your shipment was refused on delivery. Please contact your sender for further information` | 包裹在派送现场被拒收，随后页面主标题会提示已退回寄件人。 |

有两条描述容易混淆：单独的 `Collected` 表示你已自提取走包裹，而开头的 `Your shipment has been collected` 表示 DHL 从寄件人处揽收。此外，`Your shipment is at the delivery depot` 可能在派送尝试或拒收后再次出现（表示包裹返回分拨站），判断进度以最新一条为准。

## DHL eCommerce UK 派送、ServicePoint 自提与派送未成功

**改派与改期。** 收到通知或投递通知卡后，你可以在查询页选择更方便的派送日期，或将包裹改投至附近的 DHL eCommerce UK ServicePoint 自提点。页面 Help 菜单提供 ServicePoint Finder 与 Depot Finder 网点搜索工具。

**分拨站自提。** 在查询页将包裹改派至分拨站后，包裹会在修改后的 **1 个工作日**后备妥待领。

**海关清关。** 对于从英国境外寄入、应付增值税、关税或派送费的包裹，GOV.UK 说明承运商会向你发出账单并通常保管约 **3 周**，逾期未缴则退回寄件人。详见[海关滞留包裹指南](guide:customs)（英文）。

物流显示已签收却没收到？请看[显示已签收却未收到](guide:delivered-not-received)（英文）。

## DHL eCommerce UK 延误、遗失或破损

**服务延误。** 可在查询页的 Help 菜单查看“Live service updates”。

**未收到商品。** 英国公民咨询局（Citizens Advice）指出，向商家购物时，确保商品送达属于卖家的责任；即使卖家委托了快递公司，也应由卖家向快递追查。若未约定具体送达日期，卖家通常须在 30 天内送达。

**遗失或破损。** 请尽快通知网店。如果包裹没有按约定放妥，Citizens Advice 说明由卖家承担法定责任，你可以要求重新派送，部分情况下可要求全额退款。

**拒收。** 遇拒收时，DHL 提示直接联系寄件人。

## DHL eCommerce UK 客服电话与联系方式

DHL eCommerce UK 联系页面公布的渠道如下：

- **电话：** `02476 937770`，周一至周五 07:00–20:00，周六 08:00–18:00。
- **在线留言：** 在[联系页面](https://track.dhlecommerce.co.uk/contact-us?nav=1)使用“Send us a message”表单并选择“Help with my parcel delivery”；非工作时间提交的咨询由客服于次工作日回复。
- **DHL Express 包裹：** 请联系 DHL Express，DHL eCommerce UK 无法提供协助。

> Citizens Advice 提醒，要求你付费才能取件或重新安排派送的短信通常属于诈骗。请勿点击其中链接，自行在 DHL 查件页面或 Peek 核查单号，并按 GOV.UK 建议将诈骗短信免费转发至 `7726`。

## 使用 Peek 跟踪 DHL eCommerce UK 包裹

[Peek](/) 会识别 14 位数字单号，但因多家快递共用该长度，Peek 会同时向可能的承运商查询。以 `6012` 开头的单号在全球任何地区都会优先询问 DHL eCommerce UK；位于英国的用户输入以 `31`、`41` 或 `55` 开头的单号时，Peek 会先问 [DPD UK](carrier:dpd-uk)，再问 DHL eCommerce UK（在英国以外地区，Peek 不会就该单号询问 DHL eCommerce UK，请手动选择）。若 DPD 网络同样查有记录，请点击“更换”挑选正确快递。粘贴带 `con=` 的 track.dhlecommerce.co.uk 链接或带 `consignmentnumber=` 的 track.dhlparcel.co.uk 链接也可以直接粘贴。9 位退货单号可通过点击“更换”选择 DHL eCommerce UK 跟踪；Peek 不跟踪通知卡号和客户参考号，因为它们需要邮编。

当你正在 Peek 中查看该包裹，或派送当天已开启通知时，Peek 最多每 10 分钟查询一次 DHL 的查件系统；在 DHL 扫描包裹开始派送的当天，只要 DHL 自己的查件系统正常响应，自英国时间早上 6 点起每 2 分钟查询一次；其余时候每小时查询一次。仅通过物流查询服务跟踪时，每小时查询一次，静默一天后每 3 小时一次，派送当天在同样条件下最多每 15 分钟一次。Peek 可以在状态变化时通知你。连续两天无新动态（或在“已生成面单”/“已揽收”停留满 12 小时）后改为每小时一次。Peek 会展示状态、完整轨迹、预计送达日（包裹开始派送前）和签收时间，保留 DHL 原版英文表述，时间按英国时间解析并换算为你设备的本地时间。签收人姓名与寄件人参考号会被剔除；由于 DHL 扫描不含地点，地图上不会标点。签收一段时间后记录会从 DHL 的查件系统消失，且 Peek 不会将其转接至其他快递。

## DHL eCommerce UK 查询常见问题

### DHL eCommerce UK 和 DHL Parcel UK 是一样的吗？

是的。DHL eCommerce UK 原名为 DHL Parcel UK（更早称 UK Mail），仍是同一个英国快递网络。2026 年该网络计划更名为 Evri Premium，继续作为 DHL 旗下独立网络运营。

### 可以去 DHL eCommerce UK 分拨站自提包裹吗？

可以。在 DHL 查询页将派送方式改为分拨站自提后，1 个工作日后即可前往领取。

### DHL eCommerce UK 能协助查询 DHL Express 包裹吗？

不能。DHL eCommerce UK 明确表示无法追踪或处理 DHL Express 快件，请直接联系 DHL Express。

### 收到 DHL eCommerce UK 短信可信吗？

请提高警惕。Citizens Advice 指出，要求付费取件或付费改派的短信通常是诈骗。切勿点击链接，请自行在 DHL 查询页或 Peek 核实单号，并将可疑短信免费转发至 `7726`。

:::sources
- [DHL eCommerce UK: Track & Trace](https://track.dhlecommerce.co.uk/) – 查件页面的字段、通知卡、改到其他日期或 ServicePoint、1 个工作日后到分拨站自取、联系寄件人、ServicePoint Finder 与 Depot Finder、Live service updates
- [DHL eCommerce UK: Contact Us](https://track.dhlecommerce.co.uk/contact-us?nav=1) – 电话与服务时间、留言表单、次工作日回复、不处理 DHL Express
- [Evri: DHL eCommerce UK and Evri secure unconditional CMA approval](https://www.evri.com/press/dhl-ecommerce-uk-and-evri-secure-unconditional-cma-approval) – DHL 集团的电商物流专业公司、B2B 包裹服务、2026 年更名为 Evri Premium、独立的 DHL 网络（2025 年 9 月 4 日）
- [Citizens Advice: If something you ordered hasn't been delivered](https://www.citizensadvice.org.uk/consumer/somethings-gone-wrong-with-a-purchase/if-something-you-ordered-hasnt-been-delivered/) – 卖家的责任、向快递追查、30 天、约定的放置地点、诈骗短信
- [GOV.UK: Tax and customs for goods sent from abroad](https://www.gov.uk/goods-sent-from-abroad/tax-and-duty) – 承运商的账单、约 3 周、退回寄件人
- [GOV.UK: Report internet scams and phishing](https://www.gov.uk/report-suspicious-emails-websites-phishing) – 将诈骗短信转发至 7726
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Peek 读取的内容：14 位单号及其号段、7 位通知卡号、9 位退货单号、状态消息、最新的在前、原 DHL Parcel UK 与 UK Mail
:::
