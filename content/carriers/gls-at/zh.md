---
title: GLS 奥地利（GLS Austria）物流查询：状态、自提点与联系方式
description: 查询 GLS 奥地利（GLS Austria）包裹：运单号与 Track ID 查找方法、德语状态中文对照、Parcel Shop 与快递柜保管期限，以及客服联系渠道。
slug: gls-austria-tracking
published: 2026-11-26
updated: 2026-11-26
---

GLS 奥地利（GLS Austria，即 GLS Österreich）总部位于安斯费尔登（Ansfelden），设有 11 个分拨站。GLS 周一至周五派送，奥地利境内寄送的包裹通常在 24 小时内送达。你可以使用包裹号或 GLS 通知单上的 Track ID 在 [GLS Austria 查询页面](https://gls-group.com/AT/en/parcel-tracking/)查询，或直接将单号粘贴到上方输入框。单号由寄件人提供，请先查看商家的发货确认邮件。GLS 德语查询页显示的是德语状态，下文对照表列出了中文含义。

## GLS Austria 包裹号与 Track ID

GLS 奥地利查询页面可以查询包裹号和 Track ID，用空格、逗号或分号分隔即可一次查询多个单号。Track ID 印在 GLS 派送未成功后留下的纸质或邮件通知单上，该通知单同时注明包裹当前所在位置。GLS 未公开官方编号规则。以下单号形态被 Peek 认作 GLS Austria 的：

| 单号（虚构） | 含义 |
| --- | --- |
| `48291736504` | 11 位数字。同样符合 [GLS Germany](carrier:gls-de)、GLS Switzerland、[GLS France](carrier:gls-fr)、GLS Poland、PostLogistics 等承运商。 |
| `482917365042` | 12 位数字，末位为校验位。同样符合 GLS Germany、GLS Switzerland、GLS France、GLS Poland、BRT 等承运商。 |
| Track ID | 印在 GLS 通知单上。GLS 的查询页可以查询此编号；在 Peek 中请使用包裹号。 |

如果商家邮件中写明由[奥地利邮政（Austrian Post）](carrier:austrian-post)或其他快递公司承运，则该单号归属对方。完全找不到单号？请见[如何查找运单号](guide:find-tracking-number)（英文）。

## GLS Austria 德语物流状态对照

[GLS Austria 德语查询页面](https://gls-group.com/AT/de/paket-verfolgen/)会显示主状态、进度条（`Datenerfassung`、`Unterwegs`、`Ziel-Paketzentrum`、`In Zustellung`、`Zugestellt`），输入收件邮编后还会列出详细扫描记录。各项含义如下：

| GLS 显示 | 对你的含义 |
| --- | --- |
| `Daten übermittelt`、`Die Paketdaten wurden im GLS IT-System erfasst; das Paket wurde noch nicht an GLS übergeben.` | 寄件人已录入包裹信息，GLS 尚未收到实物包裹。 |
| `Das Paket wurde vom Versender zur Abholung durch GLS bereitgestellt.` | 寄件人已备妥包裹，等待 GLS 上门揽收。 |
| `Das Paket wurde durch GLS abgeholt.`、`Das Paket wurde durch GLS übernommen.` | GLS 已揽收并接收包裹。 |
| `Unterwegs`、`Das Paket hat das Paketzentrum verlassen.`、`Das Paket ist im Paketzentrum eingetroffen.` | 正在各 GLS 包裹分拣中心之间中转。 |
| `Ziel-Paketzentrum` | 已抵达末端派送站，下一步将安排派送。 |
| `In Zustellung`、`Das Paket wird voraussichtlich im Laufe des Tages zugestellt.` | 已装车外出，预计今天送达。 |
| `Nicht zugestellt`、`Das Paket konnte nicht zugestellt werden, da der Empfänger nicht angetroffen wurde.` | 未能送达：家中无人。 |
| `Der Empfänger wurde mittels E-Mail über den Zustell-/Abholversuch informiert.` | GLS 已通过邮件通知你此次派送/取件尝试。 |
| `Eingelagert`、`Das Paket befindet sich im Ziel-Paketzentrum. Es konnte nicht zugestellt werden, da der Empfänger nicht angetroffen wurde.` | 派送未成功后已回到派送站并暂存在那里。 |
| `Das Paket ist im GLS PaketShop eingetroffen.`、`Zugestellt im PaketShop` | 已送达 GLS 自提点等待领取，查询页会列出具体网点名称。 |
| `Zugestellt`、`Das Paket wurde erfolgreich zugestellt.` | 已签收送达。 |

注意：`Zugestellt im PaketShop` 虽然字面带有“已送达”，但仍需你本人前往自提点取件。

## GLS 奥地利派送：邻居代收、自提点与快递柜

**派送时间。** 周一至周五工作时间派送。GLS 条款中的工作日不含周六，不保证 24 小时必达，且无法指定精确时段。若寄件人开通了 GLS FlexDeliveryService，GLS 会发送附带查询链接的邮件：包裹交寄后显示预计送达日，派送当天还会显示派送车距离你还剩几站。

**家中无人时。** 快递员会先尝试请邻居签收，否则转投附近的 GLS Parcel Shop 自提点或快递柜（Parcel Locker），并留下通知单或发送邮件告知去向。商业件、特快件等不能转投的包裹，会在下一个工作日再次尝试派送；若仍未送达则送回分拨站。GLS 最多尝试两次派送。部分需核验带照片身份证件的包裹仅限本人签收。

**取件期限。**
- Parcel Shop 自提点：自派送尝试次日起保管最多 **8 个工作日**，凭通知单和带照片身份证件领取。
- Parcel Locker 快递柜：保管最多 **3 个工作日**，按通知指引开柜。
- 分拨站（Depot）：自次日起 **4 个工作日**内领取；前往前须先通过 GLS 收件人表单联系，并携带带照片身份证件。

逾期包裹将自动退回寄件人。如需他人代取，受托人须携带你的书面授权（写在通知单背面或使用 GLS PDF 授权表）以及其本人的带照片身份证件。GLS 在奥地利设有 1,000 家 Parcel Shop 和 1,500 座全天候开放的快递柜。

**更改派送方式。** 收到通知单或邮件后，查询页会免费列出该包裹可用的选项：改投 Parcel Shop 或快递柜、改期至寄出后 6 个自然日内的另一天（相同时段）、改至分拨站自提，或指定门前、花园等安全放置点。仅当寄件人选用 FlexDeliveryService 时才能修改收件地址；中午 12 点前送达则需寄件人订购 ExpressService。

**无接触放置授权（Drop-off permission）。** 可在通知单上、通过 GLS PDF 表格或在 GLS-ONE 账号中免费设置单次或长期放置授权；撤销时须向最近的分拨站寄送签名信函或发送邮件。包裹按授权放置后，GLS 不再承担遗失或破损责任。该授权不适用于货到付款或危险品等包裹。

## GLS 包裹延误、遗失、破损或滞留海关

如需退款或补发，请先联系网店卖家。

**物流无更新。** 连续数日停留在 `Daten übermittelt` 表示商家尚未将包裹交给 GLS，请询问寄件人。显示 `Eingelagert` 表示包裹暂存在分拨站：如该包裹允许，可在查询页的“Not at home?”区域另选派送方式，或在前往自提前填写 GLS 收件人表单。其他情况请见[物流停止更新的原因](guide:tracking-not-updating)（英文）。

**显示已签收却未收到。** 先在查询页确认是否送至 Parcel Shop，检查信箱和邮件中是否留有邻居代收通知单，并排查已授权的放置位置。随后按 GLS 要求通过收件人表单提交咨询（选择“Where was my parcel delivered / handed over?”）。详见[显示已签收却未收到](guide:delivered-not-received)（英文）。

**包裹破损。** 外箱有明显破损时可以拒收，GLS 会带回并通知寄件人。若仅内件破损，请联系寄件人。GLS 核查时需提供寄收双方地址、内件说明及包含外包装和面单在内的照片。详见[包裹破损处理](guide:damaged-parcel)（英文）。

**海关清关。** 从欧盟境外网购入境的商品，自 2026 年 7 月 1 日起对货值不超过 €150 的包裹每项商品征收 €3 关税；自 2026 年 11 月 1 日起无论货值高低每项加收 €2 欧盟处理费。“一项商品”指共享同一税则分类、商品描述及（如有要求）原产地的货品。GLS Austria 官网未说明是否或如何向收件人代收这些费用，请向商家确认商品价格是否已含税费。详见[海关滞留包裹指南](guide:customs)（英文）。

## GLS Austria 客服与联系方式

Peek 无法代你联系 GLS 或卖家。GLS Austria 官网公布的联系渠道如下：

- **收件人表单：** [GLS Austria 收件人咨询表单](https://gls-group.com/AT/en/contact-form/recipient-request/)，客服处理时间为奥地利时间周一至周五 08:00–18:00；需填写包裹号或 Track ID、邮编、电话与邮箱。
- **电话：** 公司信息页列明 `+43 5 9876 3000`；`+43 059876 1000` 为寄件服务 GLS-ONE 的呼叫中心。GLS 未单独公布收件人客服电话。
- **邮寄地址：** General Logistics Systems Austria GmbH, Traunuferstraße 105a, 4052 Ansfelden。

> GLS 绝不会通过邮件要求收件人点击在线付款链接、刷卡或银行转账支付货款：货到付款包裹一律在派送时向快递员支付现金，切勿为未知包裹付款。gls-group.com 及其站内链接才是官方渠道。如遇可疑邮件，请将其作为附件转发至 `security@gls-group.com`（诈骗短信请附截图及发件号码）；若已付款请报警。另见[快递诈骗短信识别](guide:delivery-scam-texts)（英文）。

## 使用 Peek 跟踪 GLS Austria 包裹

将运单号粘贴到 [Peek](/)。由于多家 GLS 分公司共用上述单号形态，Peek 会同时查询可能的分公司，并结合你所在的国家（手机地区设置或网页访问所在地）判断。在奥地利通常会直接显示“GLS Austria 有这个包裹的记录。”；在其他国家则可能先匹配到 GLS Germany 等其他分公司（它们查询同一个 GLS 系统，同样需要邮编）：你可以直接保留并填写邮编，也可以点击“更换”改为 GLS Austria。若暂时没有 GLS 分公司识别出该单号，仍可先添加或从快递列表中手动选择 GLS Austria——Peek 跟踪 4,500 多家快递公司。粘贴路径中包含 `/AT/` 的 gls-group.com 链接也会直接识别为 GLS Austria。

添加时的“收件邮编”字段标注为“可选”，但 GLS 必须核对邮编才会返回扫描历史，正如输入框下方提示所示：“GLS Austria 仅在填写收件邮编后才显示扫描记录。”不填邮编时，Peek 只能拿到 GLS 的主状态而没有详细轨迹，期间会向其他物流查询服务补充轨迹，包裹页面也会显示“添加邮编”按钮。如果你登录了账号，Peek 会先尝试一次你上次给 GLS Austria 包裹填写的邮编，若无记录则尝试你最近一个包裹的邮编。如果 GLS 用该邮编查不到包裹，Peek 会明确提示并提供“编辑邮编”。当 GLS 等待邮编或邮编不匹配、且已满一天无其他来源返回时，Peek 改为每天检查一次；一旦你添加或修改邮编，Peek 会立即重新查询。收件邮编仅发送给 GLS 及必要时的物流查询服务，绝不会出现在分享链接中。

填写邮编后，你可以在 Peek 中看到物流状态、每次扫描的地图位置、预计送达日、重量、签收时间，以及包裹送至 Parcel Shop 时的自提点名称与地址；你的姓名、住址、联系方式、签名及寄件人参考号都不会显示。Peek 读取的是 GLS 的英文服务而非德语网页，因此 `In Zustellung` 会显示为 `In delivery`，`Das Paket wurde erfolgreich zugestellt.` 显示为 `The parcel has been delivered.`；抵达自提点则映射为“待取件”。扫描时间按奥地利时间解析。Peek 大约每小时向 GLS 查询一次（包含派送当天；查询失败后间隔 4 小时），若包裹停留在“已生成面单”满 7 天无新动态，则改为每 6 小时检查一次，并在状态变化时发送通知。若包裹在“已生成面单”、“已揽收”或“运输中”连续 4 天没有新记录，Peek 会以最后一条扫描的日期显示“自……起没有物流更新”，并提供“向 GLS Austria 寻求帮助”链接。

## GLS 奥地利查询常见问题

### GLS 在奥地利送货需要多久？

奥地利境内通常 24 小时送达（周一至周五），但不作时效保证。GLS Austria 仅公布从奥地利发往海外的参考时效，入境包裹请以商家的预计时间为准。

### GLS 奥地利周六派送吗？

标准服务不派送，GLS 的常规派送时间为周一至周五。根据条款，寄件人可为特快件另付附加费订购周六派送。

### GLS 会帮我保管包裹多久？

Parcel Shop 自提点最多保管 8 个工作日，快递柜最多 3 个工作日，分拨站最多 4 个工作日；逾期将退回寄件人。

### 什么是 GLS Track ID？

印在 GLS 通知单上的编号。GLS 官方查询页可以像包裹号一样输入 Track ID，并查看剩余可用的派送调整选项。

### 我可以指定 GLS 的精确送达时间吗？

不可以指定精确时间。你可以将派送改期至另一天的相同时段；如需中午 12 点前送达，须由寄件人提前订购 GLS ExpressService。

:::sources
- [GLS Austria: Parcel tracking](https://gls-group.com/AT/en/parcel-tracking/) – 包裹号与 Track ID、放置地点
- [GLS Austria: Paket verfolgen](https://gls-group.com/AT/de/paket-verfolgen/) – 德语查询页、须填收件邮编才显示扫描记录
- [GLS Austria: Receiving parcels](https://gls-group.com/AT/en/receiving-parcels/) – 24 小时、邻居代收、第二次派送、取件期限
- [GLS Austria: FAQ receiving parcels](https://gls-group.com/AT/en/faq/receiving-parcels/) – 多个单号、不能指定精确时间、身份证件、破损
- [GLS Austria: Parcel Shops and Parcel Lockers](https://gls-group.com/AT/en/receiving-parcels/parcelshops-parcel-lockers/) – 网点数量、身份证件、逾期自动退回
- [GLS Austria: Redirect parcel](https://gls-group.com/AT/en/receiving-parcels/redirect-parcel/) – 可选方式、工作日、改期、ExpressService
- [GLS Austria: FlexDeliveryService](https://gls-group.com/AT/en/receiving-parcels/redirect-parcel/flexdeliveryservice/) – 邮件、实时查询
- [GLS Austria: Drop-off permission](https://gls-group.com/AT/en/receiving-parcels/drop-off-permission/) – 单次或长期授权、例外、责任
- [GLS Austria: Recipient request](https://gls-group.com/AT/en/contact-form/recipient-request/) – 服务时间、需填信息、显示已签收却未收到
- [GLS Austria: Corporate information](https://gls-group.com/AT/en/corporate-information/) – 公司、地址、电话
- [GLS Austria: Customer information](https://gls-group.com/AT/en/customer-information/) – GLS-ONE 呼叫中心
- [GLS Austria: Special terms and conditions](https://gls-group.com/AT/en/terms-and-conditions/) – 工作日不含周六、不保证时效、最多两次派送、特快件可付附加费周六派送
- [GLS Austria: Standard delivery times](https://gls-group.com/AT/en/standard-delivery-time/) – 24 小时、仅公布从奥地利寄出的时效
- [GLS Austria: Security advice](https://gls-group.com/AT/en/press/gls-informs/security-advice/) – 诈骗、举报
- [GLS Austria: Home](https://gls-group.com/AT/de/home/) – 11 个分拨站、安斯费尔登
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 2026 年 7 月 1 日起货值 €150 以内每项 €3 关税、2026 年 11 月 1 日起 €2 欧盟处理费
- [European Commission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 不论货值每项 €2、“一项商品”的定义
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – 单号形态、Peek 读取的内容、GLS Austria 的德语与英语状态文字、收件邮编
- [Peek: Routing](https://github.com/plhery/peek-delivery-tracker/blob/main/docs/ROUTING.md) – GLS 等待邮编或用邮编查不到包裹时每天查询一次、添加或修改邮编后立即查询
:::
