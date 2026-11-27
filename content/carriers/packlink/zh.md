---
title: Packlink 快递查询：参考号、状态与客服联系
description: 查询 Packlink 平台寄出的包裹：ES…COM/PRO 参考号格式、西英物流状态含义、派送未成功、破损理赔及在线联系渠道。
slug: packlink-tracking
published: 2026-11-27
updated: 2026-11-27
---

Packlink 并非快递公司本身，而是总部位于西班牙马德里的在线比价寄件平台：个人或商家在平台上比较 SEUR、Correos、Correos Express 或 UPS 等承运商的报价、在线付款并打印面单，再由所选承运商负责揽收和派送。网店商家使用其企业版 Packlink PRO，与 eBay、Amazon 等店铺和平台对接。查询包裹时，可在 [Packlink 官方查询页](https://www.packlink.es/seguimiento-envios/)或上方输入框中粘贴 Packlink 参考号或承运商自有单号。

## Packlink 追踪号是什么格式的

Packlink 会为每票货件分配一个独立参考号：两位国家/市场代码、年份、`COM` 或 `PRO`，后接 10 位数字。Packlink 西班牙官网说明其参考号以 `ES` 开头。实际承运商也会给出自己的快递单号，Packlink 查询页支持任一单号查询。以下为虚构示例：

| 单号（虚构示例） | 含义 |
| --- | --- |
| `ES2026COM4829173650` | `ES`、年份、`COM` 加 10 位数字：Packlink 个人订单参考号。 |
| `ES2026PRO6194028375` | 将 `COM` 换为 `PRO`：同样是 Packlink 参考号（企业版）。 |
| `48291736405829` | 14 位数字：SEUR 常见单号格式之一，DPD 等承运商也共用此长度。 |
| `1Z9X482W0348291737` | `1Z` 后接 16 位字母数字：UPS 快递单号。 |

**单号在哪里找。** 寄件下单后，Packlink 会在确认邮件中发送该参考号。如果你在二手平台或网店购物并由对方通过 Packlink 发货，这封邮件在卖家手中，请向卖家索取 Packlink 参考号或承运商单号。更多方法见[如何查找快递单号](guide:find-tracking-number)（英文）和[二手交易订单查询](guide:second-hand-orders)（英文）。

## Packlink 物流状态详解

Packlink 会读取实际承运商的每次扫描，并归入自家状态代码，同时在查询页提供英文说明；许多扫描还会保留承运商原始文本（例如 SEUR 或 Correos Express 的西班牙语全大写状态）。常见状态按顺序如下：

| 轨迹状态文本 | 对你的含义 |
| --- | --- |
| `Waiting for carrier to collect the order` | 寄件人已下单并生成面单，承运商尚未揽收包裹。 |
| `SIN RECEPCION` | Correos Express 的同类表述：已收到订单预报，尚未收到包裹。 |
| `RECIBIDO EN ORIGEN` | SEUR 始发站已收到包裹，但 Packlink 仍将其归类在等待揽收阶段。 |
| `Shipment accepted by carrier (scanned)` | 承运商已扫描收件，包裹开始发运。 |
| `EN ARRASTRE` | 正在承运商各分拨中心之间转运。 |
| `DELEGACION DESTINO` | 已抵达负责你所在区域派送的目的地站点。 |
| `TU ENVÍO ESTÁ PENDIENTE DE LLEGAR A DESTINO` | SEUR 状态：尚未到达你当地网点。虽然 Packlink 将其归为派送异常，实际只是仍在运输途中。 |
| `EN REPARTO` | 正在派送途中。 |
| `REPARTO FALLIDO` | 一次派送尝试未成功。 |
| `Package at pick-up point and ready for collection` | 包裹已到自提点等你领取。Packlink 将其归入已送达代码类别，但你仍须自行前往取件。 |
| `ENTREGADO` | 已送达签收。 |
| `Package returned to sender` | 包裹已退回寄件人。 |

其他常见纯英文状态：`Recipient not present at indicated address` 和 `Unsuccessful delivery and final attempt` 均表示派送未成功；`Packet delivered to customs for clearance` 表示跨境包裹正在清关。

另外有两条与包裹实体无关的系统提示：`Pending, there is no information available on the carrier’s page or the tracking number has not yet been activated` 说明 Packlink 尚未在承运商处查到数据；`Expired, there is no tracking information for the last 30 days` 表示承运商已连续 30 天无新动态，Packlink 已停止自动跟进。

## 谁负责派送 Packlink 包裹

由寄件人下单时选定的承运商派送。Packlink 查询页列出的承运商包括 [SEUR](carrier:seur)、[Correos](carrier:correos-spain)、[Correos Express](carrier:correos-express)、[UPS](carrier:ups)、GLS、DHL、[TNT](carrier:tnt)、[InPost](carrier:inpost)、[Mondial Relay](carrier:mondial-relay)、[NACEX](carrier:nacex) 和 Zeleris 等。Packlink 服务条款规定：

- **送货上门或自提点。** 寄件人在下单时决定派送至收件地址还是自提点。
- **派送未成功。** 承运商可能留下通知方便重新预约派送，并将包裹带回网点；并非所有承运商都会留卡，因此 Packlink 要求寄件人关注物流并通知收件人。
- **派送次数。** 多数情况下包含首次派送和一次免费二次派送；之后每次额外派送会向寄件人收取 2 至 15 欧元（不含增值税），视承运商及车型而定。在部分国家，承运商可能直接交由邻居代收或送至自提点。
- **网点领取期限。** 存放在承运商网点的包裹须在对应承运商规定的期限内领取，逾期可能由寄件人承担仓储费、退件费或销毁费。
- **拒收或无法投递。** Packlink 会通过物流或邮件通知寄件人，寄件人须在 2 个工作日内决定退回还是改寄他处。

另见[错过快递派送](guide:missed-delivery)（英文）和[未领取包裹处理](guide:uncollected-parcel)（英文）。

**海关税费。** 如果你的包裹来自欧盟境外且内含网购商品，自 2026 年 7 月 1 日起，货值不超过 150 欧元的订单按商品种类每类征收 3 欧元欧盟关税；自 2026 年 11 月 1 日起，无论货值多少，另按商品种类每类征收 2 欧元欧盟处理费。Packlink 条款规定进口关税和税费由收件人承担，但未说明这两项费用如何向收件人收取。参阅[包裹卡在海关怎么办](guide:customs)（英文）。

## Packlink 包裹未送达、破损或退回怎么办

**谁有权处理。** Packlink 条款明确规定，只有下单并付款的人才能使用下单邮箱发起咨询和理赔；第三方仅在提供寄件人签署的宣誓授权声明后才可介入。因此如果你是买家，必须由卖家联系 Packlink。

**物流不更新。** 若停留在 `Waiting for carrier to collect the order`，说明承运商尚未取件，请向寄件人确认是否已交寄。遇到延误或停更，同样由寄件人向 Packlink 反馈。更多见[物流为何停止更新](guide:tracking-not-updating)（英文）。

**包裹丢失。** Packlink 须等承运商排查各站点并正式确认丢失后才受理赔付，该核查过程通常自预计送达日起最长需 30 天。参阅[包裹丢失](guide:lost-parcel)（英文）。

**包裹破损。** 如果收货时外包装已有肉眼可见的破损，务必按 Packlink 要求在承运商送货单或手持终端上注明 *Con Daños*（有破损）。Packlink 强调，缺少该备注将导致理赔无法顺利处理。请保留商品及全部内外包装以备检验：如果物品被拆修或包装被丢弃，Packlink 会拒绝理赔。对于内件缺失或隐蔽破损，寄件人须自送达次日起算，西班牙境内及国际公路运输在 7 天内、国内空运在 10 天内、国际空运在 14 天内提交索赔。参阅[包裹破损](guide:damaged-parcel)（英文）。

**退回寄件人。** `Package returned to sender` 表示包裹已退回寄件人，请要求对方重发或退款。参阅[包裹被退回寄件人](guide:returned-to-sender)（英文）。

**显示已送达但没收到。** 请先向邻居和附近自提点核实，然后立即通知寄件人。参阅[显示已签收但未收到](guide:delivered-not-received)（英文）。

## Packlink 客服电话与联系方式

Packlink 官方公布的联系渠道如下：

- **客服电话：** 无。Packlink 声明仅提供线上支持，不设电话客服，并提醒其他网站上标注的所谓 Packlink 客服电话均未经其核实，切勿拨打。
- **联系表单与在线客服：** 位于 Packlink 帮助中心（从其电话说明页面可进入）。选择对应问题分类，已寄出订单请准备好确认邮件中的参考号。Packlink 也提供人工在线聊天。
- **理赔入口：** 同样在帮助中心提交，且仅限下单寄件人办理。

## 使用 Peek 追踪 Packlink 包裹

将 Packlink 参考号直接粘贴到 [Peek](/)。该格式为 Packlink 独有，添加页会立即将其显示为“识别出的快递公司”。你也可以直接粘贴实际承运商的单号：Peek 支持超过 4,500 家快递公司。若承运商单号格式同时符合多家公司（例如 SEUR 的 14 位数字），Peek 会先显示“正在查询物流服务…”并逐一询问；你也可以点击“更改”手动选择承运商。

当你打开包裹页面或在派送日开启通知时，只要 Packlink 官网正常响应，Peek 最高每 10 分钟读取一次，出库派送当天则自马德里时间早上 6 点起最高每 2 分钟读取一次；其余时间每小时一次。连续两天无新动态后（处于“已创建运单”或“快递公司已收件”状态时为 12 小时）改为每小时一次；在“已创建运单”满七天无新动态后改为每 6 小时一次。若包裹在“已创建运单”、“快递公司已收件”或“运输途中”状态连续四天无新扫描，Peek 会标注该条扫描日期、“自……以来无物流更新”、提示“包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 Packlink 查询。”并提供“联系 Packlink 获取帮助”链接；满三周无新记录后则提示“已超过三周无新动态。如果仍未收到，请联系卖家。Peek 会持续向 Packlink 查询。”Peek 优先展示承运商原始文本（若 Packlink 未附原文则展示 Packlink 英文状态），时间按马德里时间解读并换算为你设备的本地时区。遇到自提点扫描时，Peek 会归类为“待取件”；遇到 `RECIBIDO EN ORIGEN` 时归类为“快递公司已收件”。运输途中会展示 Packlink 给出的预计送达日期，多件同票包裹合并显示为一条完整轨迹。Peek 不展示扫描地点（因此没有地图）及件数；Packlink 查询本身也不提供重量、寄件人或收件人。

Packlink 追踪数据中还包含实际承运商单号。当该单号格式能明确指向唯一承运商（例如 `1Z` 开头的 UPS 单号）时，Peek 也会向该承运商查询；一旦对方出现不早于 Packlink 最新扫描的记录，Peek 便自动接续跟进并保留 Packlink 原有轨迹，卡片显示“由 UPS 派送”且详情中同时列出两个单号。由于 SEUR 和 Correos Express 的单号格式无法唯一确定，这类包裹会继续留在 Packlink 轨迹上更新。

Peek 可按“每次扫描”、“重要节点”或“仅在送达时”推送通知，但无法代你提交理赔、更改派送或联系任何一方。

## Packlink 快递查询常见问题

### Packlink 是快递公司吗？

不是。Packlink 是供个人和商家比价与预约承运商的发货平台，实际揽收和派送由下单时选定的承运商（如 SEUR、Correos Express 或 UPS）完成。

### Packlink 的客服电话是多少？

没有客服电话。Packlink 仅提供线上支持，并提醒第三方网站上出现的号码均未经认证。请通过其帮助中心联系表单或在线客服沟通。

### 我的 Packlink 追踪单号在哪里？

在下单预订时 Packlink 发送的确认邮件里，格式为两位字母（如 `ES`）、年份、`COM` 或 `PRO` 再加 10 位数字。如果你是买家，请向寄件人索取。

### 为什么 Packlink 物流显示 Expired？

`Expired, there is no tracking information for the last 30 days` 表示承运商连续一个月没有返回新动态，Packlink 已停止跟踪，并不直接代表包裹丢失。请联系寄件人向 Packlink 核实。

:::sources
- [Packlink: Seguimiento envios](https://www.packlink.es/seguimiento-envios/) – tracking by the Packlink reference or the carrier's number, references starting with ES, carriers listed, Packlink PRO
- [Packlink: ¿Y cómo funciona esto de Packlink?](https://www.packlink.es/como-funciona/) – comparing and booking carriers, labels, Packlink PRO integrations with eBay and Amazon
- [Packlink: Teléfono Packlink](https://www.packlink.es/telefono-packlink/) – comparison site, no phone support, numbers on other websites not verified, contact form, live chat, reference in the confirmation email
- [Packlink: Términos y Condiciones](https://www.packlink.es/terminos-condiciones/) – company in Madrid, only the person who booked can claim, home or pickup point, notices, attempts and their cost, collection deadline, refused parcels, import duties, claim deadlines, damage reservations, 30-day search
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – €3 customs duty per kind of product up to €150 since 1 July 2026, €2 Union handling fee since 1 November 2026
- [European Commission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – €2 per kind of product whatever the value, for goods sold at a distance
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – reference shape, carriers' number shapes, status wordings and codes, Madrid time, what Peek reads from Packlink's tracking, hand-off to the carrier
:::
