---
title: GLS 波兰（GLS Poland）物流查询：状态、自提点、快递柜与客服
description: 查询 GLS 波兰（GLS Poland）包裹：11/12 位单号与 Track-ID、收件邮编查询、物流状态、GLS Point 与 ORLEN 快递柜及客服。
slug: gls-poland-tracking
published: 2026-11-26
updated: 2026-11-26
---

GLS 波兰（GLS Poland，*General Logistics Systems Poland Sp. z o.o.*，总部位于波兹南附近的格乌霍沃 Głuchowo，中央分拣枢纽位于罗兹附近的斯特雷库夫 Stryków）在波兰运营约 50 家分支机构以及超过 26,000 个户外自提网点——其中包括超过 18,000 家 GLS Point 自提点（涵盖全部 11,000 家 Żabka 便利店）和约 8,000 座共享 [ORLEN Paczka](carrier:orlen-paczka) 24 小时快递柜。你可以使用包裹号或 Track-ID 在 [GLS Poland 查询页面](https://gls-group.com/PL/en/parcel-tracking/)追踪，或直接将单号粘贴到上方输入框。

## GLS Poland 运单号与 Track-ID 格式

包裹号位于网店发货确认邮件、GLS 邮件/短信提醒或派送未成通知单（*awizo*）上。GLS 查询框同样支持通知单上的 Track-ID。

| 格式（示例编号） | 含义 |
| --- | --- |
| `48291736405` | 11 位 GLS 包裹号。同样由 [GLS Germany](carrier:gls-de)、[GLS France](carrier:gls-fr)、GLS Switzerland、[GLS Austria](carrier:gls-at) 及 [PPL](carrier:ppl) 使用。 |
| `482917364052` | 12 位包裹号，末位为校验位。同样由 [BRT](carrier:brt)、[Colis Privé](carrier:colis-prive) 等快递使用。 |

在 [GLS Poland 查询页面](https://gls-group.com/PL/en/parcel-tracking/)输入收件邮编可解锁完整扫描记录及派送改派选项。其他单号形态请参阅[运单号格式说明](guide:tracking-number-formats)（英文）。

## GLS Poland 物流状态含义

GLS 波兰在波兰语网站显示波兰语扫描说明，并通过 Peek 调用的 GLS 国际集团接口返回英文描述。

| GLS Poland / GLS 集团显示 | 对你的含义 | Peek 显示 |
| --- | --- | --- |
| `Preadvice`、`Nadawca nadał numer paczce - paczka nie została jeszcze przekazana kurierowi GLS` | 寄件人已电子生成面单，GLS 尚未收到实物包裹。 | “已生成面单” |
| `Paczka została przekazana GLS` | GLS 已揽收或将包裹纳入网络。 | “已揽收” |
| `In transit`、`Final parcel center`、`Paczka zarejestrowana w filii GLS`、`Paczka wyjechała z filii GLS` | 正经由 GLS 分拣枢纽及波兰各地分部运往派送站。 | “运输中” |
| `In delivery`、`Paczka w doręczeniu` | 快递员已装车，安排今天派送。 | “派送中” |
| 到达 GLS Point 自提点或快递柜（GLS 进度条会显示为已送达） | 已在 GLS Point 自提点或 24 小时快递柜等待领取。 | “待取件” |
| `The parcel has been delivered.`、`Paczka została doręczona`、`Handing over the parcel to the recipient at the GLS ParcelShop.` | 已交予你本人、受托人或邻居签收，或已在 GLS Point 领取。 | “已签收” |
| `Paczka niedoreczona - odmowa przyjecia przez Odbiorce` | 收件人拒收包裹。 | “派送未成功” |
| `Paczka pozostawiona w filii doreczajacej - Odbiorca odmowil przyjecia` | 因拒收暂留在派送分部。 | “需要留意” |

派送当天，GLS Poland 的 [Live Tracking](https://gls-group.com/PL/en/live-tracking/) 会通过邮件发送查询链接，提供精确至 15 分钟范围内的预计送达时间，并在剩余不超过 20 站时在地图上显示快递员位置。GLS 官方进度条会在包裹投入 GLS Point 或快递柜等待领取时即显示“已送达”；Peek 会将该阶段显示为“待取件”，待你取走后再显示为“已签收”。

## GLS Poland 派送时效、GLS Point、ORLEN 快递柜与 FlexDeliveryService

**派送日期与时效。** GLS Poland 周一至周五派送（仅在寄件人专门订购时提供周六派送）。波兰境内标准件通常次工作日送达（不保证时效；寄件人可付费订购 Express 10:00 或 12:00）。从波兰发往欧盟其他国家通常需要 2 至 7 个工作日，视目的地而定（见 [GLS Points](https://gls-group.com/PL/en/shipping-receiving-points/gls-points/)）。

**重量、尺寸与货到付款（CashService）。** 波兰境内上门包裹限重 31.5 公斤（国际上门包裹 40 公斤；部分指定 GLS Point 31.5 公斤；其他 GLS Point、快递柜及 myGLS 限重 20 公斤），尺寸上限为 200 × 80 × 60 厘米且长度加周长不超过 300 厘米。货到付款（`CashService`，每日上限 6,000 波兰兹罗提）可在收货时以现金、银行卡或 BLIK 支付给快递员。

**派送未成与二次尝试。** 根据 [GLS Poland 收件指南](https://gls-group.com/PL/en/receiving/)，若你不在家，快递员可在征得你同意后交由邻居代收，或将符合条件的非货到付款包裹转投附近的 GLS Point。否则包裹返回分部，于次工作日再次派送；若仍未送达，将在退回寄件人前暂留分拨站（通过 FlexDeliveryService 预约分拨站自提保管 5 个工作日，收件指南另注明 6 个工作日后退回）。

**GLS Point 保管期限。** 根据 GLS Poland 自 2026 年 11 月 9 日起生效的 GLS Point 与快递柜[服务条款](https://gls-group.com/PL/pl/regulamin/)及[常见问题](https://gls-group.com/PL/en/faq/)，包裹在 GLS Point 保管 **7 个自然日**，逾期退回寄件人（[收件指南](https://gls-group.com/PL/en/receiving/)与 GLS Points 页面仍沿用“7 个工作日”旧表述）。取件时请出示短信或邮件收到的 PIN 码，GLS 可能会核验身份证件。

**24/7 ORLEN Paczka 快递柜。** 投递至[共享 ORLEN Paczka 快递柜](https://gls-group.com/PL/en/shipping-receiving-points/parcel-lockers/)的包裹保管 **72 小时**，逾期自动退回寄件人。可通过短信/邮件 PIN 码、二维码或在 myGLS 应用中远程开柜——无需出示身份证件，持有取件码的任何人均可代取。快递柜 24 小时技术支持热线：`801 167 536` 或 `502 167 536`。

**FlexDeliveryService 改派服务。** 点击 GLS 通知邮件中的链接（无需注册账号）即可通过[包裹改派页面](https://gls-group.com/PL/en/parcel-redirection/)改投 GLS Point、ORLEN 快递柜、新地址、更改派送日期、改至分拨站自提、授予无接触放置许可或直接拒收。

## 清关、破损、投诉与 GLS Poland 遗失包裹

网购订单由卖家与 GLS Poland 签订运输合同，退款或补发请先联系卖家。

**欧盟境外包裹。** 欧盟境内包裹无需清关。对于从欧盟境外寄入的网购包裹，根据[欧盟委员会海关规定](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en)，货值不超过 €150 的包裹按商品类别每项征收固定 €3 关税（自 2026 年 7 月 1 日起实施），并同时适用 €2 欧盟处理费（自 2026 年 11 月 1 日起实施），另需按规定缴纳波兰进口增值税。详见[海关滞留包裹指南](guide:customs)（英文）。

**外观破损与隐藏破损。** 如包裹外箱有明显破损，收货时可直接拒收，由快递员登记并退回寄件人。根据 GLS Poland [服务条款](https://gls-group.com/PL/pl/regulamin/)，签收后发现的隐藏破损须在 **7 天**内申报（书面寄至 GLS 总部或通过[消费者投诉表单](https://gls-group.com/PL/pl/reklamacje-konsumenckie/)提交）。一般消费者投诉时限为 GLS 收寄后 30 天内（遗失申报为 45 天，须在发运后 12 个月内），GLS 在 30 天内答复。另见[包裹破损处理](guide:damaged-parcel)（英文）和[包裹遗失处理](guide:lost-parcel)（英文）。

**物流停滞或显示已签收未收到。** 若连续数日无更新，请参阅[物流停止更新的原因](guide:tracking-not-updating)（英文）。若显示已签收但你未收到，请先确认包裹是否在 GLS Point、询问邻居并联系商家；另见[显示已签收却未收到](guide:delivered-not-received)（英文）和[包裹被退回寄件人](guide:returned-to-sender)（英文）。

## GLS Poland 客服与防诈骗提醒

订单退款请先联系网店。如需直接联系 GLS Poland（详见 [GLS 联系页面](https://gls-group.com/PL/en/gls-contact/)及[公司信息](https://gls-group.com/PL/en/corporate-information/)）：

- **客服热线：** `+48 46 814 82 20`（周一至周五 07:00–19:00，周六 07:00–15:00）。
- **电子邮箱：** `gls@gls-poland.com`（公司办公室 `office@gls-poland.com`）。
- **在线表单：** [GLS Poland 联系表单](https://gls-group.com/PL/en/gls-contact/)及[消费者投诉表单](https://gls-group.com/PL/pl/reklamacje-konsumenckie/)。
- **24/7 快递柜技术热线：** `801 167 536` / `502 167 536`。
- **总部地址：** General Logistics Systems Poland Sp. z o.o., ul. Tęczowa 10, Głuchowo, 62-052 Komorniki, Poland。

> **[GLS Poland 安全提示页面](https://gls-group.com/PL/en/security-advices/)防诈骗警告：** GLS 绝不会通过邮件发送付款链接要求你使用银行卡或银行转账支付货款（CashService / ExWorksService 一律在派送现场以现金、刷卡或 BLIK 向快递员付款），也绝不会使用 Gmail 或 Outlook 等免费个人邮箱发送业务通知。如收到可疑信息，请转发至 `security@gls-poland.com`（或 `dataprotection@gls-poland.com`），并向波兰 CSIRT NASK CERT（`incydent.cert.pl`、`cert@cert.pl` 或发送短信至 `8080`）举报钓鱼链接。另见[快递诈骗短信识别](guide:delivery-scam-texts)（英文）。

## 使用 Peek 跟踪 GLS Poland 包裹

将运单号粘贴到 [Peek](/)。由于 11 位与 12 位数字单号由多家快递共用，Peek 会同时向可能的承运商查询并结合你的国家（手机地区设置或网页访问所在地）排序。在波兰，Peek 会优先查询 GLS Poland，早于 [GLS France](carrier:gls-fr)、[GLS Germany](carrier:gls-de)、GLS Switzerland 以及 [PPL](carrier:ppl)（11 位）或 [BRT](carrier:brt)、[Colis Privé](carrier:colis-prive)（12 位），通常会直接显示“GLS Poland 有这个包裹的记录。”在波兰以外地区，Peek 会在其他可能的承运商均未查到时再向 GLS Poland 查询。粘贴路径包含 `/PL/` 且带有 `match=`、`parcelNumber=` 或 `matchParcelNumber=` 参数的 `gls-group.com` / `gls-group.eu` 链接会直接按 GLS Poland 追踪。其他情况下，可在添加时点击“更换”或在已添加的包裹页面点击“更换快递公司”选择 GLS Poland。

添加时的“收件邮编”字段标注为“可选”，但 GLS 仅在核对邮编后才返回详细扫描，正如字段下方提示所示：“GLS Poland 仅在填写收件邮编后才显示扫描记录。”不填邮编时，Peek 只能获取 GLS 主状态而无扫描明细，期间会向其他物流查询服务请求轨迹，并在包裹页面提供“添加邮编”。登录账号后，Peek 会自动尝试一次你上次为 GLS Poland 包裹填写的邮编，若无记录则尝试你最近一个包裹的邮编。若 GLS 用该邮编未查到包裹，Peek 会提示并提供“编辑邮编”。当 GLS 等待邮编或邮编不匹配、且已满一天无其他来源返回时，Peek 改为每天查询一次；添加或更改邮编后会立即触发查询。收件邮编仅发给 GLS 及必要时的物流查询服务，绝不会出现在分享链接中。

填写邮编后，你可以看到物流状态、每次扫描的地图位置、预计送达日、重量、签收时间以及送达 GLS Point 时的网点名称与地址；你的姓名、住址、联系方式、签名和寄件人参考号都会被剔除。Peek 读取的是 GLS 英文接口而非波兰语网页，因此 `W doręczeniu` 会显示为 `In delivery`，`Paczka została doręczona` 显示为 `The parcel has been delivered.`；送达 GLS Point 或快递柜会映射为“待取件”。时间按波兰时间解析。

Peek 大约每小时向 GLS 查询一次（含派送当天），手动刷新不会提前触发（查询失败后等待 4 小时）；包裹停留在“已生成面单”满 7 天无新动态时，改为每 6 小时查询一次。若仅通过物流查询服务跟踪，则每小时查询一次，静默一天后每 3 小时一次。

若最新扫描已过 4 天，且包裹仍处于“已生成面单”、“已揽收”或“运输中”，Peek 会按该扫描日期做出提示，例如“自10月12日周一起没有物流更新”。包裹页面会显示“包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 GLS Poland 查询。”，并提供前往 GLS Poland 联系页面的“向 GLS Poland 寻求帮助”链接。若超过三周没有新扫描，则会提示“已超过三周没有新动态。如果还没收到，请联系卖家。Peek 会持续向 GLS Poland 查询。”

你可以自由选择“每次扫描”、“重要环节”或“仅派送当天”通知预设。Peek 仅监控物流状态更新，无法代你改派包裹或向 GLS Poland 提交投诉。

## GLS Poland 查询常见问题

### GLS Poland 在 GLS Point 或 ORLEN 快递柜替我保管包裹多久？

根据 GLS Poland 现行[服务条款](https://gls-group.com/PL/pl/regulamin/)与[常见问题](https://gls-group.com/PL/en/faq/)，GLS Point 保管 **7 个自然日**（收件指南与 GLS Points 页面仍写为 7 个工作日），而 24 小时 ORLEN Paczka 快递柜保管 **72 小时**，逾期自动退回寄件人。

### 为什么 GLS Poland 需要我输入收件邮编才能看到完整物流？

GLS 为保护货运隐私，仅在运单号与收件邮编一致时返回完整扫描记录、派送选项及自提点详情。在 Peek 中输入收件邮编即可获取 GLS 官方直连轨迹。

### GLS Poland 的 CashService 货到付款包裹能刷卡或用 BLIK 支付吗？

可以。GLS Poland 明确说明 CashService 货到付款（每日上限 6,000 波兰兹罗提）可在收货时向快递员以现金、银行卡或 BLIK 支付——但绝不会通过陌生邮件链接收款。

### 如何将 GLS Poland 包裹改派到 Żabka 便利店或快递柜？

打开 GLS 通知邮件中的 FlexDeliveryService 链接（无需账号），或前往[包裹改派页面](https://gls-group.com/PL/en/parcel-redirection/)，即可将包裹改派至 GLS Point（含 Żabka 门店）、ORLEN Paczka 快递柜、其他地址或稍后的日期。

:::sources
- [GLS Poland: Parcel Tracking](https://gls-group.com/PL/en/parcel-tracking/) – parcel number, Track-ID and postcode lookup
- [GLS Poland: About GLS](https://gls-group.com/PL/en/about-gls/) – Stryków central hub, branches and network overview
- [GLS Poland: Corporate Information](https://gls-group.com/PL/en/corporate-information/) – legal entity, Głuchowo address and `office@gls-poland.com`
- [GLS Poland: Receiving Parcels](https://gls-group.com/PL/en/receiving/) – weekday delivery, neighbour drop-off, second attempt, depot holding and CashService
- [GLS Poland: Parcel Redirection](https://gls-group.com/PL/en/parcel-redirection/) – FlexDeliveryService options without an account
- [GLS Poland: GLS Points](https://gls-group.com/PL/en/shipping-receiving-points/gls-points/) – 18,000+ GLS Points (including 11,000 Żabka stores), 26,000+ OOH points, weight/size limits and EU transit times
- [GLS Poland: Parcel Lockers](https://gls-group.com/PL/en/shipping-receiving-points/parcel-lockers/) – ~8,000 shared ORLEN Paczka 24/7 lockers, 72-hour pickup window, PIN/QR/myGLS app, 20 kg limit and locker hotline
- [GLS Poland: Live Tracking](https://gls-group.com/PL/en/live-tracking/) – map view and an expected time to within 15 minutes
- [GLS Poland: FAQ](https://gls-group.com/PL/en/faq/) – 7-calendar-day GLS Point hold, CashService cash/card/BLIK, missed delivery answers
- [GLS Poland: Terms of Service (Regulamin)](https://gls-group.com/PL/pl/regulamin/) – terms, those for GLS Points and lockers in force from 9 November 2026: 7-calendar-day pickup window, 7-day hidden-damage notice, 30/45-day complaint windows and 30-day resolution
- [GLS Poland: Contact](https://gls-group.com/PL/en/gls-contact/) – hotline `+48 46 814 82 20` (Mon–Fri 07:00–19:00, Sat 07:00–15:00), `gls@gls-poland.com` and contact form
- [GLS Poland: Consumer Complaints](https://gls-group.com/PL/pl/reklamacje-konsumenckie/) – online consumer claim submission
- [GLS Poland: Security Advices](https://gls-group.com/PL/en/security-advices/) – phishing warnings, `security@gls-poland.com` and CERT NASK reporting
- [GLS Group: Security Advice](https://gls-group.com/GROUP/en/security-advice/) – official group domains and CashService rules
- [European Commission: EU Customs Reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – €3 customs duty up to €150 (since 1 July 2026) and €2 EU handling fee (since 1 November 2026)
- [Universal Parcel Scraper: gls-pl](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/gls-pl) – 11/12-digit detection, Poland priority, optional postcode, status mapping, hourly/4-hour refresh cadence and help URL
:::
