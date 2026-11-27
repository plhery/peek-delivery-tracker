---
title: 丹麦 DAO 物流查询：状态含义、daoSHOP 取件与派送未成功
description: 查询丹麦 DAO 包裹：20 位、13 位与 9 位单号查找方法、物流状态说明、夜间上门投递、daoSHOP 取件与 7 天保管期，以及联系 DAO 客服方式。
slug: dao-tracking
published: 2026-11-26
updated: 2026-11-26
---

DAO（Dansk Avis Omdeling）是丹麦快递公司，自 1921 年起从事报纸投递，如今也负责在夜间将网购包裹送至家门口，或投递至 daoSHOP 自提点。你可以在 DAO 的“Track your parcel”页面、dao 应用中输入包裹号，或直接将单号粘贴到上方输入框。包裹号来自商家的发货邮件，或 DAO 发出的短信与电子邮件。

## 在哪里查找 DAO 运单号

商家会在发货确认邮件中提供运单号。包裹送达 daoSHOP 等待领取时，DAO 会通过短信和邮件向你发送包裹号及 5 位取件码。dao 应用则会在运单创建后立即自动添加你的包裹。

DAO 说明其包裹号为 9 位、13 位或 20 位数字（以下数字为虚构）：

| 单号 | 含义 |
| --- | --- |
| `00057126964829173646` | 20 位包裹号，以 `00057` 开头。这是面单上的条码编号，也是联系 DAO 客服时需要提供的编号。 |
| `7482917364058` | 以 `7` 开头的 13 位编号。 |
| `482917364` | 9 位电子面单码（e-label）：在 DAO 购邮寄件的个人寄件人可将其直接写在包裹上，无需打印面单。 |

输入时空格不影响识别。丹麦其他快递公司的单号[格式不同](guide:tracking-number-formats)（英文）：[PostNord](carrier:postnord) 的邮政单号以 `DK` 结尾且其 20 位单号前缀不同，GLS 单号为 11 或 12 位数字，DPD 单号为 14 位数字。如果 DAO 页面查不到，请先核对数字，再向商家确认。若完全没收到单号，请参阅[如何查找运单号](guide:find-tracking-number)（英文）。

## DAO 物流状态说明

DAO 查询页面在英文版显示英文状态，在 dao.as 丹麦语版显示丹麦语状态。以下按顺序列出英文状态；分拨站或转运中心名称常出现在冒号后面，例如 `Parcel arrived at dao depot: dao Erritsø`。

| 状态 | 对你的含义 |
| --- | --- |
| `Shipment created, awaiting registration` | 商家已[生成面单](guide:tracking-statuses)（英文），DAO 尚未收到包裹。 |
| `Handed in at daoSHOP` | 寄件人已将包裹交至 daoSHOP。 |
| `On the way to terminal` | 已从该自提点揽收并运往转运中心。 |
| `Parcel has arrived at terminal` | 已抵达 DAO 分拣转运中心，仍在运输途中。 |
| `Sorted at: Erritsø terminal` | 已在指定转运中心（如 Erritsø 或 Sjælland）完成分拣。 |
| `Parcel arrived at dao depot` | 已到达本地派送站，准备上门投递。 |
| `On the way to daoSHOP` | 正运往自提点。 |
| `To be delivered today` | 已安排在下一班次派送，通常在当晚夜间或次日清晨送达。 |
| `Not delivered - other reason` | 快递员未能将包裹投递到你的地址。 |
| `Delivery not possible. Redirected to daoSHOP` | 与上一条同时显示：包裹改投至 daoSHOP 自提点。 |
| `Ready for pickup` | 已在 daoSHOP 等待领取，取件码会通过短信和邮件发送。 |
| `Parcel delivered` | 已投递至你的住址，或已在 daoSHOP 签收领取。 |

有两点容易让人误解：`Parcel has arrived at terminal` 只是分拣环节，并非最终送达；此外，上门派送的 `Parcel delivered` 通常发生在夜间，早上请检查信箱或门把手。目前未记录到 DAO 的退件状态文案。

## DAO 夜间送货上门、daoSHOP 取件与派送未成功

**送货上门。** DAO 全年 365 天在夜间与清晨投递，无需签收；其条款指出大多数情况下在早上 7 点前完成。如果信箱放得下，快递员会放进信箱；放不下则装入再生塑料袋挂在门把上，或放在门垫等家门附近位置。投递不提供纸质回执，DAO 以 GPS、时间及现场照片记录作为妥投凭证。

**为何转投 daoSHOP。** 可能是寄件人原本就选择了 daoSHOP；否则，当包裹无法安全放置、重量超过 5 公斤、尺寸超过上门投递限制（长度超过 55 厘米，或长度加周长超过 120 厘米）或地址有误时，DAO 会将其转投至 daoSHOP。公寓楼住户需确保 DAO 能进入大门，住户可通过 DAO 的“Grant access”表单授予门禁权限。

**自提领取。** 请携带包裹号及 DAO 短信或邮件中的 5 位取件码。你也可以转发该消息让他人代取。DAO 个人客户页面显示其拥有超过 1,500 家 daoSHOP，企业客户页面显示超过 1,600 家；你可以在 dao.as 地图上按邮编、街道或城镇查找网点与营业时间。DAO 有时会改投至另一家自提点或包裹柜。

**保管期限。** 根据 DAO 条款，自提包裹保管 **7 个自然日**，期间会发出一次通知与两次提醒，逾期将退回寄件人。

DAO 未公布收件人改期、改派功能，也未公布派送尝试次数。

## DAO 包裹遗失、延误或破损

虽然与 DAO 签订运输合同的是商家，但 DAO 联系页面也面向收件人列明了处理步骤。

**48 小时无更新。** 如果包裹最近 48 小时内没有任何扫描记录，DAO 请你通过邮件或电话报失，并描述内装物品。你会收到邮件确认，DAO 最多有 10 个工作日进行排查。详见[物流停止更新的原因](guide:tracking-not-updating)（英文）。

**显示已送达却未找到。** 请检查信箱、门把及门前周围角落，随后联系 DAO；若内件缺失，请发送邮件至 `kundeservice@dao.as`。详见[显示已签收却未收到](guide:delivered-not-received)（英文）。

**包裹破损。** DAO 帮助页面要求在送达后 **5 个工作日**内申报（面向寄件人的说明写为 5 天），并附外包装、内包装及破损商品照片；其服务条款允许在送达后 **7 天**内提交书面索赔。由于帮助页上的破损表单链接已失效，请直接发送邮件至 `kundeservice@dao.as`。条款规定每件包裹的赔偿上限为有凭证价值或 2,500 丹麦克朗（以较低者为准）。如快递员损坏你的财物，请联系 `erstatning@dao.as`。

**对处理结果不满意。** 请先向 DAO 投诉，其邮政牌照规定须在一个月内回复。若你对其决定（例如关于延误或赔偿的决定）仍有异议，可向发牌监管机构丹麦交通局（Trafikstyrelsen）申诉。尽管 DAO 条款提及 Nævnenes Hus 旗下的 Center for Klageløsning，但 Nævnenes Hus 会将针对持牌邮政企业的投诉转交 Trafikstyrelsen 处理。

**来自欧盟境外的包裹。** 自 2026 年 7 月 1 日起，货值不超过 €150 的跨境网购订单按商品类别每项征收 €3 关税；自 2026 年 11 月 1 日起，另加收每项 €2 欧盟处理费。详见[海关滞留包裹指南](guide:customs)（英文）。

## DAO 客服与联系方式

以下联系方式公布于 DAO 联系页面：

- **电话：** `+45 72 33 08 50`（丹麦时间周一至周五 08:00–19:00，周六、周日 08:00–16:00）。
- **电子邮箱：** `kundeservice@dao.as`（请附上 20 位包裹号）。
- **在线表单：** [DAO 帮助与联系页面](https://dao.as/en/contact-dao/)提供“Need help?”与反馈表单（页面中链接的破损申报表已下线）。
- **快递员投诉：** `kvalitetabo@dao.as` 或 `44 51 75 70`。

> DAO 提醒防范冒充其名义、以“缺少门牌号”“包裹滞留”或“补缴费用”为由发送的虚假短信与邮件。DAO 不会发送付款链接，请一律在 dao 应用或 dao.as 核实。如已提供银行卡信息，请致电你的银行；如批准了不是你发起的 MitID 请求，请冻结 MitID。

## 使用 Peek 跟踪 DAO 包裹

对于以 `0005712696` 或 `0005715127` 开头的 20 位单号，[Peek](/) 会同时向 DAO 及 PostNord 等其他可能的快递公司查询。如果只有 DAO 有记录，页面会显示“DAO 有这个包裹的记录。”；如果 PostNord 同样查得到，Peek 会告知共有几家快递公司识别出该单号，供你选择。13 位或 9 位单号需要在添加时点击“更换”选择 DAO，或者直接粘贴 dao.as 查询链接（适用于任意位数）。随后 DAO 会返回对应的 20 位包裹号，后续追踪即以此编号为准。

DAO 的扫描时间不带时区：Peek 按扫描地时钟解析（丹麦境内按丹麦时间），并完整列出 DAO 自身轨迹，同时可显示包裹发往或暂存的 daoSHOP。物流状态保留 DAO 英文原文。当你打开包裹页，或在派送当天开启通知时，Peek 最多每 10 分钟查询一次；在显示 `To be delivered today` 的当天，只要 DAO 自己的查件系统正常响应，自丹麦时间早上 6 点起每 2 分钟查询一次（需要通过浏览器查询时每 5 分钟一次；仅通过物流查询服务跟踪时，每小时查询一次，静默一天后每 3 小时一次，派送当天最多每 15 分钟一次）；其余情况下每小时检查一次。若连续两天无新动态（或在“已生成面单”/“已揽收”停留满 12 小时），同样改为每小时检查一次；若在“已生成面单”停留满 7 天，则改为每 6 小时一次。

若最新扫描已过 4 天，且包裹仍处于“已生成面单”、“已揽收”或“运输中”，Peek 会按该扫描日期做出提示，例如“自10月12日周一起没有物流更新”。包裹页面会显示“包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 DAO 查询。”并提供前往 DAO 帮助中心的“向 DAO 寻求帮助”链接。若超过三周没有新扫描，则会提示“已超过三周没有新动态。如果还没收到，请联系卖家。Peek 会持续向 DAO 查询。”

Peek 可以按“每次扫描”、“重要环节”或“仅派送当天”通知你。对于发往海外的包裹，页面会展示合作伙伴运单号，且当 DAO 的链接指向 Peek 跟踪的快递公司时，可以继续在对方系统跟踪。DAO 不提供预计送达日期或重量；脱敏姓名、收件地址与邮编，以及自提点的地址、营业时间和地图坐标均不会显示。Peek 无法代你联系 DAO。

## DAO 物流查询常见问题

### “Parcel has arrived at terminal” 是什么意思？

表示包裹已抵达 DAO 的某个分拣转运中心，仍在继续运输途中。

### DAO 配送需要多长时间？

DAO 向个人寄件人说明 daoSHOP 包裹 2 至 4 天内送达；面向网店则承诺送货上门 16 小时内完成、发往 daoSHOP 次日送达。上门包裹全年 365 天在夜间或清晨投递。

### DAO 有官方查询应用吗？

有，提供 iPhone 和 Android 版 dao 应用。它会自动添加寄给你的包裹，并显示包裹号和取件码。

### 收到 DAO 要求付款的短信是真的吗？

不是。DAO 声明不会发送要求付款或转账的链接；请直接打开 dao 应用或访问 dao.as 核对包裹状态。

:::sources
- [DAO: Track your parcel](https://dao.as/en/track-your-parcel/) – 单号格式、查无结果提示、状态页面
- [DAO: Find din pakke](https://dao.as/find-din-pakke/) – 丹麦语查询页与状态文字
- [DAO: Help and contact](https://dao.as/en/contact-dao/) – 电话、服务时间、邮箱、表单、转投、取件码、包裹遗失、破损 5 天内申报
- [DAO: Parcels](https://dao.as/en/private-parcels/) – 夜间投递、信箱与门把、1,500 多家 daoSHOP、取件、电子面单
- [DAO: daoSHOP parcels](https://dao.as/en/private-parcels-daoshop/) – 个人寄件 2 至 4 天
- [DAO: daoSHOP for businesses](https://dao.as/en/business-parcels-daoshop/) – 1,600 多家 daoSHOP、次日送达
- [DAO: daoHOME for businesses](https://dao.as/en/business-parcels-daohome/) – 16 小时内
- [DAO: Hjemmelevering](https://dao.as/privat-pakker-daohome/) – 全年 365 天、上门投递的尺寸与重量限制
- [DAO: Terms and Conditions](https://dao.as/wp-content/uploads/2025/10/Terms-and-Conditions-Dansk-Avis-Omdeling_211025.pdf) – 无需签收、投递凭证、取件期限、7 天内索赔、赔偿责任、投诉
- [DAO: Handelsbetingelser](https://dao.as/wp-content/uploads/2025/10/Handelsbetingelser-for-Dansk-Avis-Omdeling-2025_211025.pdf) – 7 个自然日、以丹麦语版本为准
- [DAO: Find daoSHOP](https://dao.as/en/find-daoshop-en/) – 地图查找、营业时间
- [DAO: Grant access](https://dao.as/en/grant-access/) – 公寓楼门禁权限
- [DAO: Avoid fraud](https://dao.as/en/avoid-fraud/) – 虚假消息及应对方法
- [DAO: dao app](https://dao.as/en/daoapp/) – 自动添加包裹、包裹号与取件码
- [DAO: Om dao](https://dao.as/om-dao/) – 公司简介、1921 年起投递报纸
- [DAO: API documentation](https://api.dao.as/docs/v1) – 20 位 GS1-128 面单条码
- [Trafikstyrelsen: Posttilladelse – DAO – 2024](https://www.trafikstyrelsen.dk/publikationsliste/post-og-koerekort-publikationer/2024/mar/posttilladelse-dao-2024) – 投诉须在一个月内答复
- [Trafikstyrelsen: Klager over postvirksomheder](https://www.trafikstyrelsen.dk/post-og-koerekort/styrelsens-arbejde-paa-postomraadet/klager) – 对企业的决定有异议时的投诉
- [Nævnenes Hus: Til køber](https://naevneneshus.dk/naevnsoversigt/maeglingsteamet-for-forbrugerklager-og-forbrugerklagenaevnet/til-koeber/) – 邮政投诉转交 Trafikstyrelsen
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – €3 关税、€2 处理费
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Peek 读取的内容
:::
