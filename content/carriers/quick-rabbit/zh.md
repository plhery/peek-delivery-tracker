---
title: Quick Rabbit 物流查询：PKG 单号、状态与 Citibox
description: 查询西班牙 Quick Rabbit 包裹：PKG 单号格式、西班牙语物流状态中文解释、Citibox 快递柜、修改派送信息与理赔。
slug: quick-rabbit-wuliu-chaxun
published: 2026-11-26
updated: 2026-11-26
---

Quick Rabbit 是一家总部位于马德里、面向网店与零售商的西班牙配送公司，在西班牙各地提供送货上门、自提点和 Citibox 快递柜服务。你可以在 [Quick Rabbit 查件页](https://web.quickrabbit.es/es/tracking) 输入 `PKG` 编码查件，或直接粘贴到上方输入框。Quick Rabbit 说明该编码位于你的确认邮件或发货回执中。

## Quick Rabbit 运单号：PKG 编码

Quick Rabbit 将其称为物流追踪码（`código de seguimiento`）。它有两种形态，查询页同时也接受寄件人参考号（以下数字为虚构）：

| 单号 | 含义 |
| --- | --- |
| `PKG48220260715482917365` | `PKG`、三位数字、八位日期（年月日）加九位数字：常见的 Quick Rabbit 编码。 |
| `PKG482260715ES4829173640582K` | `PKG`、九位数字、`ES`、13 位数字加一位字母：较长格式。 |
| `WT4829173640582917` | `WT` 加 16 位数字：寄件人参考号，在 Quick Rabbit 页面显示为 `Referencia`。 |

查询系统查不到的编码会提示 `Paquete no encontrado`（“未找到包裹”）：请对照商家邮件核对。完全没有编码？请联系商家，或参见[如何查找运单号](guide:find-tracking-number)（英文）。

## Quick Rabbit 物流状态含义

Quick Rabbit 查询页默认以西班牙语打开（也有英语和中文版）。以下是其西班牙语原文，附带 Peek 显示的英文行和阶段，大致按流转顺序排列：

| Quick Rabbit 原文 | Peek 显示（行文、状态） | 对你的含义 |
| --- | --- | --- |
| `Documentado`、`Creado` | `Documented`、`Created`：已生成面单 | 商家已登记包裹，Quick Rabbit 尚未收到实物。 |
| `Ingresó a Centro de Clasificación`、`Salió de Centro de Clasificación` | `Entered Sorting Center`、`Left Sorting Center`：运输中 | 进出 Quick Rabbit 分拣中心。 |
| `Tránsito a almacén local`、`Recibido en Almacén`、`En Almacén Local` | `Transit to local warehouse`、`Received at Warehouse`、`Local Warehouse`：运输中 | 发往并到达负责你所在区域的本地仓库。 |
| `Listo para Asignación de Ruta` | `Ready for Route Assignment`：运输中 | 已进入派送箱，等待分配到快递员路线。 |
| `Repartidor Tiene el Paquete`、`En Reparto` | `Driver Has Package`、`In Delivery`：派送中 | 快递员已拿到包裹并正在派送。 |
| `Entregado al Destinatario`、`Entregado a Vecino`、`Entregado al Portero`、`Entregado en Buzón`、`Entregado en Recepción`、`Entregado a Compañero de Piso` | `Delivered to Recipient`、`Delivered to Neighbor` 等：已签收 | 已交付给你本人、邻居、门卫、信箱、前台或室友。 |
| `Entrega Citibox Iniciada` | `Citibox Delivery Initiated`：派送中 | 快递员正送往 Citibox 快递柜。 |
| `Depositado en Citibox` | `Deposited in Citibox`：待取件 | 已放入快递柜等你领取。 |
| `Retirado de Citibox` | `Retrieved from Citibox`：已签收 | 你已从快递柜取走。 |
| `Almacenamiento Citibox Expirado` | `Citibox Storage Expired`：需要留意 | 快递柜保管时间已到期。 |
| `En Camino a Punto de Recogida`，之后 `En Punto de Recogida` 或 `Listo para Recoger` | `En Route to Pickup Store`（运输中），之后 `At Pickup Store` 或 `Ready for Pickup`：待取件 | 正在发往自提点，随后到达自提点待取。 |
| `Destinatario Ausente`、`Primera Ausencia`、`Segunda Ausencia`、`Tiempo Insuficiente`、`Fuera de Horario` | `Recipient Absent`、`First Absence`、`Second Absence`、`Insufficient Time`、`Outside Delivery Hours`：派送未成功 | 家中无人，或快递员时间不够/超出服务时段。 |
| `Dirección Incorrecta`、`Dirección Incompleta`、`Destinatario Desconocido` | `Incorrect Address`、`Incomplete Address`、`Unknown Recipient`：派送未成功 | 地址无法投递，请按下方步骤修改地址。 |
| `Entrega Rechazada` | `Delivery Rejected`：派送未成功 | 上门时包裹被拒收。 |
| `Potencialmente perdido` | `Under investigation`：需要留意 | Quick Rabbit 正在核查包裹位置，可能仍在途中。 |
| `Paquete perdido`、`No localizado` | `Lost Package`、`Not located`：需要留意 | 丢失，或不在应有位置。 |
| `Incidencia logística` | `Logistics incident`：需要留意 | 派送异常。Quick Rabbit 备注写明“Puede solicitar el reembolso”（你可以申请退款），同时其团队会复核该件。 |
| `Preparado para Devolución`、`Devolución tras intentos estándar`、`Devuelto al Remitente` | `Prepared for Return`、`Return after standard attempts`、`Returned to Sender`：已退回寄件人 | 例如经过标准派送尝试后退回商家，直至退达商家。 |

部分记录仅作备注：`Cliente Solicitó Cambio de Dirección`（你申请了改址）、`No Se Pudo Contactar al Cliente`（Quick Rabbit 联系不上你）、`Almacén - Esperando Gestión de Cliente`（留在仓库等待客服处理异常）。`Primer Intento de Entrega desde Almacén` 仅表示排定了尝试，不代表结果。Peek 会列出这些记录，但不会改变包裹状态。

## Quick Rabbit 派送：快递员、Citibox 与自提点

Quick Rabbit 表示其服务覆盖西班牙全部 50 个省，其网站服务页向商家提供短信与邮件通知及失败重派。派送没成功？参见[派送未成功](guide:missed-delivery)（英文）。

**Citibox 快递柜与自提点。** 快递员除了送货上门，也可把包裹放进 Citibox 快递柜或送至自提点。Quick Rabbit 网页未提供开箱码、未列出自提点名录，也未写明各自保管期限。`Retirado de Citibox por Repartidor`（“快递员已从 Citibox 取回”）表示快递员已将包裹从柜中取出。更多见[快递柜取件](guide:parcel-lockers)（英文）和[未领取的包裹](guide:uncollected-parcel)（英文）。

## 修改 Quick Rabbit 派送信息：地址、电话或日期

包裹在途期间，Quick Rabbit 查询页会显示 `Actualizar datos de entrega`（“更新派送信息”）区域。输入邮箱后，Quick Rabbit 会向你发送安全链接，你可在其中修改手机号、收件地址（仅限同城）或期望派送日期。修改会在“接下来几小时内”生效。每个链接有修改次数上限且会过期。

切勿拖延太久。按 Quick Rabbit 原话，一旦“自接收包裹起已超过 5 天”，链接会提示“Ya no es posible realizar cambios en este paquete”（无法再对此包裹做任何更改），包裹“comenzará el proceso de devolución a origen”，即启动退回寄件人流程。更多见[修改收件地址](guide:change-delivery-address)（英文）。

## Quick Rabbit 包裹延误、丢失或显示已签收但没收到

任何包裹问题，Quick Rabbit 联系页面都引导你通过查询页上的理赔流程提交。

**显示已签收但没收到。** 先看签收记录：可能由邻居或门卫代收。如果仍未找到，在已签收包裹的查询页上点击“¿Quieres informar sobre un problema?”（“要报告问题吗？”）下方的 `Click aquí`。确认下单所用邮箱后，Quick Rabbit 会发邮件告知操作指引。理赔表单会询问情况、可能要求上传照片，并让你选择通过邮件、电话或短信接收回复。详见[显示已签收但未收到](guide:delivered-not-received)（英文）。

**停更、丢失或破损。** 多天没有进展参见[物流为什么停止更新](guide:tracking-not-updating)（英文）。对于显示丢失、破损或被盗的包裹，查询页的帮助框会变为 `Soporte Urgente`（紧急支持），链接至 Quick Rabbit 联系页。按照欧盟法规，在你签收商品前商家须对订单负责，因此也请向商家申请退款或补发：详见[包裹丢失](guide:lost-parcel)（英文）、[包裹破损](guide:damaged-parcel)（英文）和[退回寄件人](guide:returned-to-sender)（英文）。

## Quick Rabbit 联系方式

- **邮箱：** customer@quickrabbit.es（主要渠道）。紧急异常请在邮件主题注明“URGENT”。
- **工作时间：** 马德里时间周一至周五 8:00–20:00、周六 9:00–14:00、周日休息（其常见问题注明这是销售团队工作时间）。
- **地址：** Avenida de Aragón, 404, 28022 Madrid。

联系页面上的表单仅供企业客户询价使用。

## 用 Peek 跟踪 Quick Rabbit 包裹

[Peek](/) 会按形态直接识别 Quick Rabbit 单号：`PKG` 加三位数字、八位日期和九位数字，或更长的 `PKG…ES…` 编码（不区分大小写，允许空格）。16 位 `WT` 参考号仅凭形态无法唯一确定：Peek 会询问 Quick Rabbit，查到时显示“Quick Rabbit 有这个包裹的记录。”查不到时还会询问物流查询服务，并在你添加后继续查询。其他格式的参考号也照样可以粘贴（Peek 跟踪 4,500 多家快递公司），点“更换”选择 Quick Rabbit 即可。对于参考号，Peek 会在“Quick Rabbit 记录的单号为”下显示对应的 `PKG` 编码。带 `ref=` 的 Quick Rabbit 查询链接同样可用。

Peek 读取 Quick Rabbit 自己的查件系统：展示状态、上表英文历史记录及签收时间（按马德里时间读取，换算为你设备的时区）。Quick Rabbit 不提供地点名称，因此不显示地图。Peek 会略去预计送达日、目的城市、寄件人参考号以及快递员坐标（可能指向你的住址门牌）。不含签收凭证或快递柜取件码，Peek 也不会将包裹转交 Citibox：快递柜步骤来自 Quick Rabbit 自身记录。

当你打开包裹页，或在派送当天开启通知时，Peek 最多每 10 分钟查询一次；在 Quick Rabbit 扫描包裹进入派送的当天，只要 Quick Rabbit 自己的查件系统正常响应，自马德里时间早上 6 点起最多每 2 分钟查询一次；其余时间每小时一次。连续两天无新动态（在“已生成面单”或“已揽收”状态下为 12 小时）后改为每小时一次，在“已生成面单”下连续七天无新动态后改为每 6 小时一次。

如果处于“已生成面单”、“已揽收”或“运输中”的包裹连续四天没有新扫描，Peek 会以最新扫描日期标出提醒（“自……起没有物流更新”），并提示“包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 Quick Rabbit 查询。”及“向 Quick Rabbit 寻求帮助”链接。三周无新扫描时提示：“已超过三周没有新动态。如果还没收到，请联系卖家。Peek 会持续向 Quick Rabbit 查询。”

Peek 可以按“每次扫描”、“重要环节”或“仅派送当天”通知你，但无法替你修改派送、提交理赔或联系 Quick Rabbit 和商家。

## 关于 Quick Rabbit 物流查询的常见问题

### Quick Rabbit 是正规公司吗？

是的。这是一家西班牙物流公司，自称拥有超过 5 年面向西班牙各地网店和零售商的配送经验，并将 AliExpress、Amazon、[菜鸟（Cainiao）](carrier:cainiao)、DHL、[Paack](carrier:paack)、[CTT Express](carrier:ctt-express)、[MRW](carrier:mrw) 和 Celeritas 列为合作伙伴。

### 怎么修改 Quick Rabbit 的收件地址？

在查询页点击 `Actualizar datos de entrega` 并输入你的邮箱。收到的链接可修改同城地址、电话或派送日期。按 Quick Rabbit 说明，自收到包裹起超过 5 天后便无法再更改，包裹会退回寄件人。

### “Potencialmente perdido”是什么意思？

意为“可能丢失”：Quick Rabbit 正在调查包裹位置，并表示它可能仍在运输或派送中。Peek 显示为 `Under investigation`。如果后续变为 `Paquete perdido`（“Lost Package”），请向商家申请退款或补发。

### 怎么联系 Quick Rabbit？

发送邮件至 customer@quickrabbit.es，紧急情况请在主题写上“URGENT”。具体包裹问题请按联系页要求在查询页面发起理赔。

:::sources
- [Quick Rabbit: Seguimiento de Paquetes](https://web.quickrabbit.es/es/tracking) – 追踪码与查找位置、未找到提示、参考号、状态、更新派送信息、理赔、紧急支持
- [Quick Rabbit: Package Tracking](https://web.quickrabbit.es/en/tracking) – 英文查询页
- [Quick Rabbit: Contacto](https://web.quickrabbit.es/es/contact) – 通过查询页理赔、邮箱、紧急异常、工作时间（常见问题注明为销售团队）、地址
- [Quick Rabbit: Contact](https://web.quickrabbit.es/en/contact) – 英文联系页
- [Quick Rabbit: E-commerce & retail logistics in Spain](https://web.quickrabbit.es/en) – 覆盖 50 省、在查询页提交理赔
- [Quick Rabbit: About Us](https://web.quickrabbit.es/en/about) – 5 年以上经验、合作伙伴
- [Quick Rabbit: Our Services](https://web.quickrabbit.es/en/services) – 短信与邮件通知、失败重派服务
- [Your Europe: Shipping and delivery](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_en.htm) – 签收前由商家承担责任及退款规定
- [Universal Parcel Scraper: Quick Rabbit](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/quick-rabbit) – 单号形态、寄件人参考号查询、状态与阶段、Peek 保留字段
:::
