---
title: DPD 奥地利（DPD Austria）物流查询：状态、自提点与客服
description: 查询奥地利 DPD 包裹：14 位单号与 PIN 码、myDPD 德英双语状态说明、Pickup 自提点与快递柜保管期，以及客服联系方法。
slug: dpd-austria-tracking
published: 2026-11-26
updated: 2026-11-26
---

DPD 奥地利（DPD Austria）是一家总部位于利奥波德斯多夫（Leopoldsdorf）的私营包裹快递公司。在奥地利境内，包裹通常在 DPD 从网店揽收后 1 个工作日内送达。你可以凭商家发货邮件中的 14 位包裹号在 DPD 奥地利查询网站及应用 [myDPD](https://www.mydpd.at/) 查件，或直接将单号粘贴到上方输入框。

## DPD Austria 包裹号与 PIN 码

DPD 包裹号为 14 位数字，由网店发送；当网店向 DPD 提供你的邮箱时，DPD 也会邮件发送该编号（注册 myDPD 账号后亦可在 “My parcels” 查看）。PIN 码（Parcel Information Number）印在 DPD 派送未成功时留下的红白通知卡上。myDPD 同时支持包裹号和 PIN 码，Peek 则需使用 14 位包裹号。Peek 识别的号段如下：

| 单号（虚构） | 含义 |
| --- | --- |
| `06204819273645` | 14 位数字，开头为 `0620`–`0630`、`0632`–`0635`、`0643`–`0645` 或 `0649`：属于 DPD 奥地利分拨站号段。 |
| `01904819273645` | 14 位数字，开头为 `0101`–`0199`：Peek 会先识别为 [DPD Germany](carrier:dpd-de)，但末端仍可能由 DPD Austria 派送（见下文）。 |

如果面单在 14 位数字后多出一个字母或数字，输入时请将其省略。如果商家邮件写明由[奥地利邮政（Austrian Post）](carrier:austrian-post)、[GLS Austria](carrier:gls-at) 或其他快递承运，则单号归属对方。完全找不到单号？请看[如何查找运单号](guide:find-tracking-number)（英文）。

## DPD Austria 物流状态含义

myDPD 会以德语或英语列出每条扫描及经办分拨站。以下按包裹通常经过的顺序列出（*Paketstation* 即快递柜）：

| myDPD 显示 | 对你的含义 |
| --- | --- |
| `Auftragsdaten übermittelt` | 寄件人已向 DPD 发送电子订单数据，尚未移交包裹实物（DPD 英文常见问题写作 “Order data transmitted to DPD”）。 |
| `Paket unterwegs` | DPD 已拿到包裹：已在始发分拨站揽收，或正在中转枢纽扫描推进。 |
| `Im Paketzustellzentrum` | 已抵达末端派送站，下一步将安排派送。 |
| `Unfortunately, parcel was loaded incorrectly and will be delivered soon.` | 装错车辆，将在另一分拨站重新扫描后派送。 |
| `In Zustellung` | 已交由司机，正在派送途中。 |
| `Empfänger leider nicht angetroffen.`、`Unfortunately, consignee not present. Notification was left.` | 家中无人，司机已留下通知卡。 |
| `Unfortunately, parcel was not delivered.` | 本趟未能送达，返回分拨站等待再次派送。 |
| `In Zustellung an Pickup Paketstation`、`In delivery to Pickup parcel shop` | 派送未成功后，司机正将包裹送往 Pickup 快递柜或自提点。 |
| `Paket wurde in der Pickup Paketstation hinterlegt`、`Parcel was handed over to Pickup parcel shop`、`Takeover by Pickup parcel shop` | 已存入 Pickup 快递柜或自提点等待领取。DPD 会通过邮件或短信发来取件码（快递柜附带地址）。 |
| `Paket durch Empfänger abgeholt` | 已从 Pickup 自提点取走。 |
| `Paket wurde erfolgreich abgestellt` | 已按你授权的无接触放置许可（deposit okay）放妥（如车库）。 |
| `Parcel was not picked up` | 逾期无人到 Pickup 自提点领取，通常同一分钟即启动退回寄件人流程。 |
| `Parcel will be returned to the sender.` | 开始退回寄件人。 |
| `Parcel was picked up by driver from Pickup parcel shop` | 司机已从 Pickup 自提点取走：若出现在初期表示寄件人交寄，若出现在暂存待取之后则表示开始退回寄件人。 |

## DPD 奥地利派送：Predict 预报、改派与 Pickup 自提点

**Predict 预报。** 如果网店向 DPD 提供了你的邮箱或手机号，DPD 会提前通知送达日期；派送当天通过 Live-Tracking 显示预计时段与地图（每 15 分钟更新）。该时段不作保证，交通与天气可能导致变动。境外包裹请参考商家预估时效。

**更改派送。** 在 myDPD 注册后，直至派送前不久均可凭包裹号或 PIN 码修改安排：改期或改地址、指定某位邻居代收、改投 Pickup 自提点或快递柜，或设置无接触放置许可（deposit okay，例如放露台）。注册账号还可为所有包裹设置常用自提点/快递柜或长期放置许可。

**家中无人。** DPD 可将包裹交予收件住址或办公地点任何愿意签收的人员（不核验身份证件）。首次派送未成功后，DPD 可将包裹交给邻居；没有邻居时，转投最近的 Pickup 自提点/快递柜，并告知名称和地址；纸质通知卡和 myDPD 均会写明去向。详见[派送未成功怎么办](guide:missed-delivery)（英文）。

**取件期限。**
- Pickup 自提点：保管 **7 个自然日**，3 天和 5 天后各发送一次提醒。凭 DPD 邮件/短信中的取件码，或出示身份证件并签字即可领取（DPD 无法重发取件码，myDPD 也不显示）；各门店营业时间不同。
- Pickup 快递柜：保管 **5 个自然日**，3 天后提醒一次。取件必须使用邮件或短信里的取件码；快递柜全天 24 小时开放。

逾期包裹将退回寄件人，需联系网店重发。另见[未领取包裹](guide:uncollected-parcel)（英文）和[包裹被退回寄件人](guide:returned-to-sender)（英文）。

## DPD 包裹延误、遗失、破损或滞留海关

如需退款或补发，请先联系与 DPD 签约的网店。

**无更新。** 连续数日停在 `Auftragsdaten übermittelt` 表示网店尚未移交包裹，DPD 建议联系寄件人。如果派送当天 Live-Tracking 连续数小时不动，请凭包裹号联系 DPD。详见[物流停止更新的原因](guide:tracking-not-updating)（英文）。

**显示已签收却未收到。** 请按 DPD 要求先检查授权放置点以及通知卡上写明的邻居；仍找不到请填写 DPD 收件人表单（选择 “Where was my parcel delivered/handed over.”）并通知网店。详见[显示已签收却未收到](guide:delivered-not-received)（英文）。

**遗失。** 若各方均找不到包裹，请联系 DPD 客服与网店。详见[包裹遗失处理](guide:lost-parcel)（英文）。

**破损。** DPD 仅面向寄件人受理破损调查，因此请通知网店：由网店向 DPD 索赔，并按其条款为你退款、补发或作类似处理。DPD 对寄件人的条款要求隐藏破损最晚在 **6 个自然日**内书面申报、外观破损须在签收后立即申报，请尽早联系卖家。详见[包裹破损处理](guide:damaged-parcel)（英文）。

**海关清关。** 从欧盟境外网购入境的商品，自 2026 年 7 月 1 日起对货值不超过 €150 的包裹按商品类别每项征收 €3 欧盟关税；自 2026 年 11 月 1 日起无论货值高低每项加收 €2 欧盟处理费。“一项商品”指共享同一税则分类、商品描述及（如有要求）原产地的货品。DPD Austria 网站未说明是否或如何向收件人收取这些费用，请向商家确认售价是否已包含。详见[海关滞留包裹指南](guide:customs)（英文）。

## DPD Austria 客服与联系方式

Peek 无法代你联系 DPD 或网店。DPD Austria 英文页面公布如下：

- **电话：** `05 03 73 420`（境外拨打 `+43 5 03 73 420`），个人客户专线，奥地利时间周一至周五 07:00–18:00。
- **在线表单：** [DPD Austria 收件人联系表单](https://www.dpd.com/at/en/support/contact-form-consignee/)，需填包裹号或 PIN 码、姓名和邮箱。DPD 建议只通过一个渠道提交，多渠道重复咨询反而拖慢处理进度。
- **邮寄地址：** DPD Direct Parcel Distribution Austria GmbH, Arbeitergasse 46, 2333 Leopoldsdorf。

> 冒充 DPD 的诈骗邮件与短信屡见不鲜。DPD 提醒：切勿付款、勿打开附件、勿点击登录链接，直接删除即可；如已蒙受资金损失，DPD 建议报警。另见[快递诈骗短信识别](guide:delivery-scam-texts)（英文）。

## 使用 Peek 跟踪 DPD Austria 包裹

将 14 位包裹号粘贴到 [Peek](/) 即可，无需邮编。

若单号属于 DPD Austria 分拨站号段，无论你身处何地，Peek 都会在最先询问的快递公司中询问 DPD Austria，一旦 myDPD 查有记录且最新扫描位于奥地利即显示“DPD Austria 有这个包裹的记录。”若暂未入网则显示“暂未找到 · Peek 会继续查询”——仍可先添加，包裹会显示“可能是 DPD Austria”及“等待 DPD Austria 的首次扫描”并持续查询。

其他号段的 14 位数字同时符合多家 DPD 分公司和其他快递，Peek 会询问可能的承运商；如实际由 DPD Austria 承运，请点击“更换”选择 DPD Austria（已添加的包裹使用“更换快递公司”）。直接粘贴带单号的 myDPD 链接会立刻归属 DPD Austria。

**来自其他 DPD 分公司的包裹。** 对于来自 [DPD Netherlands](carrier:dpd-nl)、DPD Belgium 或 DPD Hungary 的包裹，一旦 DPD 标明其抵达奥地利派送站或更靠后阶段，Peek 会用同一单号向 DPD Austria 查询，待 myDPD 出现不早于原分公司最新扫描的记录后转由 DPD Austria 跟进（在此之前包裹卡片已会标明由 DPD Austria 派送）。来自 [DPD Germany](carrier:dpd-de) 的包裹则保留在 DPD Germany 名下，但进入奥地利后 Peek 也会读取 myDPD 并在其扫描更完整时予以展示。

**可查看的内容。** 物流状态、保留 myDPD 原文并附带分拨站城镇与国家的历史记录，以及按奥地利时间解析的签收时间（其他时区国家的扫描保留当地时区）。存入 Pickup 自提点的包裹会显示为“待取件”。Peek 会过滤姓名、地址、派送时段、具体自提点信息、签收人及改派选项，且不显示预计送达日（myDPD 也不提供重量）。如需查询自提点请看 myDPD 或通知卡，取件码请看 DPD 邮件或短信。

当你正在 Peek 中查看包裹，或派送当天已开启通知时，Peek 最多每 10 分钟查询一次，并在 DPD 扫描出派送中的当天，自奥地利时间早上 6 点起每 2 分钟查询一次。Peek 可以按“每次扫描”、“重要环节”或“仅派送当天”通知你。连续两天无新动态（或在“已生成面单”/“已揽收”停留满 12 小时）后改为每小时一次；在“已生成面单”停留满 7 天后每 6 小时一次。若包裹在“已生成面单”、“已揽收”或“运输中”连续 4 天无新记录，Peek 会显示“自……起没有物流更新”及“向 DPD Austria 寻求帮助”链接。如需更改派送方式，请使用 myDPD。

## 关于 DPD 奥地利物流查询的问题

### DPD 在奥地利派送需要多长时间？

奥地利境内通常为 DPD 从网店揽收后 1 个工作日；在此之前 myDPD 可能显示 `Auftragsdaten übermittelt`。

### DPD 在 Pickup 自提点或快递柜替我保管包裹多久？

Pickup 自提点保管 7 个自然日，Pickup 快递柜保管 5 个自然日，逾期退回寄件人。

### 什么是 DPD PIN 码？

即 Parcel Information Number，印在 DPD 派送未成功时留下的通知卡上。myDPD 支持用 PIN 码查询，但在 Peek 中请使用 14 位包裹号。

### `Auftragsdaten übermittelt` 是什么意思？

表示网店已将包裹电子信息传给 DPD，但 DPD 尚未收到实物包裹。若连续几天没有变化，请联系网店确认发货情况。

### DPD Austria 的客服电话是多少？

个人客户热线为 `05 03 73 420`，服务时间为奥地利时间周一至周五 07:00–18:00。

:::sources
- [DPD Austria: Receiving parcels](https://www.dpd.com/at/en/receiving-parcels/) – Predict、Live-Tracking、更改派送、deposit okay、快递柜
- [DPD Austria: FAQ receiving parcels](https://www.dpd.com/at/en/support/faq/faq-receiving-parcels/) – 包裹号、PIN 码、1 个工作日、更改派送
- [DPD Austria: FAQ Pickup](https://www.dpd.com/at/en/support/faq/pickup/) – 保管期限、取件码、身份证件
- [DPD Austria: General terms and conditions](https://www.dpd.com/at/en/gtc/) – 保管期限、提醒、退回
- [DPD Austria: General terms and conditions, national (PDF)](https://www.dpd.com/wp-content/uploads/sites/224/2022/01/AGB0447_AGBs_DPDA_national_en.pdf) – 首次派送后交给邻居或 Pickup 自提点、Predict、派送时段、破损申报
- [DPD Austria: What does the status "Order data transmitted to DPD" mean?](https://www.dpd.com/at/en/faq/was-bedeuter-der-status-auftragsdaten-an-dpd-uebermittelt/) – 询问寄件人
- [DPD Austria: Status not changing in live tracking](https://www.dpd.com/at/en/faq/der-paketstatus-meines-pakets-hat-sich-schon-lange-nicht-mehr-veraendert-woran-kann-das-liegen/) – 数小时
- [DPD Austria: Parcel shown as delivered but not delivered](https://www.dpd.com/at/en/faq/ein-paket-das-mir-als-zugestellt-angezeigt-wird-ist-noch-nicht-geliefert-worden-woran-liegt-das/) – 先检查什么
- [DPD Austria: My parcel can't be located](https://www.dpd.com/at/en/faq/mein-paket-ist-unauffindbar-was-soll-ich-tun/) – 客服
- [DPD Austria: Parcel arrives damaged](https://www.dpd.com/at/en/faq/was-ist-zu-tun-wenn-ein-paket-beschadigt-beim-empfanger-ankommt/) – 先找寄件人
- [DPD Austria: Contact](https://www.dpd.com/at/en/support/contact/) – 电话、服务时间
- [DPD Austria: Contact form for consignees](https://www.dpd.com/at/en/support/contact-form-consignee/) – 一次只用一个渠道
- [DPD Austria: Security](https://www.dpd.com/at/en/security/) – 虚假邮件与短信
- [DPD Austria: Legal notice](https://www.dpd.com/at/en/legal-notice/) – 公司、地址、境外拨打的电话
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 自 2026 年 7 月 1 日起的 €3 关税、自 2026 年 11 月 1 日起的 €2 处理费
- [European Commission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – “一项商品”的含义
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Peek 读取的内容、myDPD 的状态文案、分拨站号段、转交
:::
