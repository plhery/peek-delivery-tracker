---
title: Paquetexpress 墨西哥快递追踪：运单号、Ocurre 自提与理赔
description: 追踪墨西哥 Paquetexpress 包裹：guía 与 11–13 位单号、西语状态、两次派送、5 天 Ocurre 自提与 15 天理赔。
slug: paquetexpress-tracking
published: 2026-11-26
updated: 2026-11-26
---

Paquetexpress（法定名称 *Operadora de Servicios Paquetexpress, S.A.P.I. de C.V.*，总部位于锡那罗亚州洛斯莫奇斯）是墨西哥最大的国内包裹、快递与货运承运商之一，拥有 40 年运营历史、超过 12,200 名员工、覆盖墨西哥全国的网点以及通达 240 多个国家的国际寄递服务。你无需登录账号即可在 [Paquetexpress 追踪页面](https://www.paquetexpress.com.mx/rastreo)查询货件，或将追踪码或 *guía* 运单号粘贴到上方输入框中。

## Paquetexpress 追踪单号：追踪码与 guía 运单号

Paquetexpress [常见问题](https://www.paquetexpress.com.mx/preguntas-frecuentes/)说明，其查询框支持输入追踪码（*código de rastreo*）、运单号（*número de guía*）或寄件人参考号（Peek 仅查询追踪码和 *guía* 运单号）。请在商家的发货邮件、Paquetexpress 短信链接或面单上查找单号：

| 格式（虚构） | 说明 |
| --- | --- |
| `MEX48WW2917364` | Paquetexpress *guía* 运单号：3 位始发网点字母代码 + 2 位数字 + 2 位字母 + 5 至 7 位数字（涵盖同一运单下的全部包裹）。 |
| `482917364058` | 11 至 13 位纯数字追踪码（*código de rastreo*）。与 [Mondial Relay](carrier:mondial-relay)、[Colis Privé](carrier:colis-prive)、[MRW](carrier:mrw)、[BRT](carrier:brt) 和 [SF Express](carrier:sf-express) 等承运商共享长度。 |

如果你的墨西哥包裹以 `JMX` 开头，请查阅 [J&T Express Mexico](carrier:j-and-t-mx)；如需了解其他单号规则，请参阅[单号格式指南](guide:tracking-number-formats)（英文）。

## Paquetexpress 追踪状态含义

Paquetexpress 追踪记录使用西班牙语，并带有内部事件代码（`SWB`、`BOK`、`TRN`、`WHU`、`HTC`、`HDC` 等）：

| Paquetexpress 显示状态（代码） | 对你的含义 | Peek 显示阶段 |
| --- | --- | --- |
| `Documentación De Servicios Web`（`SWB`）、`Solicitud De Rad`（`HCR`）、`Recolección Planeada`（`HCP`） | 已在线建单或预约揽收，Paquetexpress 尚未收到实物包裹。 | “已生成面单” |
| `Documentada`（`BOK`）、`Recolección Realizada`（`HCM`）、`Confirmación De Recolección En Almacen`（`HCA`） | 已在网点柜台交寄、由司机上门揽收或入库始发网点仓库。 | “已揽收” |
| `Mercancía En Tránsito`（`TRN`）、`Transbordo De Remolque`（`TTM`）、`Recibida En Sucursal de Transbordo`（`WHU`）、`Planeado Para Entrega A Domicilio`（`HLP`） | 正在网点或挂车之间转运，或已在目的地网点备货等待出车。 | “运输中” |
| `Recibida En Sucursal destino`（`WHU`） | 已抵达目的地网点：上门派送件（`ENT_EAD`）仍为“运输中”，*Ocurre* 网点自提件（`ENT_OCU`）则已可前往取件。 | “运输中”或“待取件” |
| `En Ruta De Entrega A Domicilio`（`HTC`） | 今日已交由派送员送货上门。 | “派送中” |
| `Mercancía Entregada A Domicilio`（`HDC`）、`Entregada En Sucursal`（`BDL`）、`Pagada En Destino (Contado)`（`PDC`） | 已送货上门、在网点完成领取或在目的地现金到付签收。 | “已签收” |

请注意，`Rad Cancelación`（`HCC`，取消上门揽收预约）并不意味着货件终止，因为发件人仍可将包裹交至网点，因此 Peek 会保持上一条扫描的阶段不变。

## 运输时效、两次派送、Ocurre 自提与超期仓储费

根据 Paquetexpress [服务介绍页](https://www.paquetexpress.com.mx/servicios/)、[常见问题](https://www.paquetexpress.com.mx/preguntas-frecuentes/)以及在 PROFECO 备案的[服务条款（Clausulado，合同编号 3808-2020）](https://www.paquetexpress.com.mx/clausulado-solicitud-servicios/)：

- **运输时效：** 墨西哥国内包裹与快递服务按距离提供 **24、48、72、96 或 120 小时**时效（另提供当日 12:00 前送达、24 小时、48 小时 Express 服务以及周六派送）。签收时一律记录收件人姓名、签名、日期与时间（*Acuse*）。
- **最多两次上门派送：** 根据第 5 条，上门派送（*Entrega a Domicilio*）最多尝试 **2 次**。如无人签收，司机会留下到访通知单（*aviso de visita*，附网点与客服中心电话），并将包裹存放在当地网点。
- **5 个自然日免费取件（Ocurre 自提或未投递件）：** 根据第 5 条，*Ocurre* 网点自提件及两次派送未果暂存件均须在到达网点或留下通知单后的 **5 个自然日**内领取。超过 5 个自然日后，Paquetexpress 可在不另行通知的情况下将包裹转入无人认领仓库（*bodega de rezagos*），并按运费的 **2%/天**加收仓储费。逾期超过 **90 个自然日**未领取的包裹可能被拍卖或销毁。
- **切换派送方式或修改地址：** Paquetexpress [常见问题](https://www.paquetexpress.com.mx/preguntas-frecuentes/)说明，你可拨打 `(+52) 800 821 0208` 申请在 *Ocurre* 自提与上门派送之间切换，或在包裹离开网点后申请更正收货地址。可通过 [Paquetexpress Sucursales](https://www.paquetexpress.com.mx/sucursales) 查找当地网点。

## 理赔、包裹损坏与 PROFECO 调解

网购订单请先联系商家处理退款或补发。根据 Paquetexpress [服务条款](https://www.paquetexpress.com.mx/clausulado-solicitud-servicios/)（理赔见第 8 条）：

- **签收时当面查验：** 当着派送员检查外包装，在签收单（*acuse de recibo*）上注明任何拆封或损坏痕迹，并要求详细核查。
- **15 个工作日理赔期限：** 记录异常后须在 **15 个工作日**内提交正式索赔，可亲自前往任意网点（周一至周五 08:00–18:00）、发送邮件至 `reclamaciones@paquetexpress.com.mx` 或通过[在线理赔申请页](https://www.paquetexpress.com.mx/contacto/solicitud-de-reclamacion/)提交。Paquetexpress 承诺在 **15 个工作日**内答复。
- **赔偿责任与保险：** 根据第 5 条，未投保货件如发生丢失、损坏或未送达，Paquetexpress 最高按每吨 15 天墨西哥法定最低工资（轻件按比例折算）承担责任；因承运商原因延误的，每延误一天按 5 天最低工资赔付。根据第 6 条，可选运输保险最高保至声明价值（货损无免赔额，武装抢劫免赔额 20%）；保险责任在 *Ocurre* 网点存放满 5 个自然日后终止，上门派送件则在第二次派送未成功时或抵达目的地网点满 10 个自然日后终止。
- **PROFECO 管辖：** 根据第 10 条，行政纠纷受墨西哥联邦消费者保护署（[PROFECO](https://www.gob.mx/profeco)，备案格式合同编号 `3808-2020`）管辖。

如遇扫描缺失或错投他人（Paquetexpress 要求致电 `800 821 0208` 报告以便追回或理赔），请参阅[为何物流停止更新](guide:tracking-not-updating)（英文）、[显示已签收但未收到包裹](guide:delivered-not-received)（英文）、[包裹损坏处理指南](guide:damaged-parcel)（英文）、[包裹丢失处理指南](guide:lost-parcel)（英文）与[包裹退回发件人](guide:returned-to-sender)（英文）。

## Paquetexpress 客服与联系方式

以下联系方式发布于 [Paquetexpress 英文联系页](https://www.paquetexpress.com.mx/en/contacto/)、[西班牙语联系页](https://www.paquetexpress.com.mx/contacto/)及[服务条款](https://www.paquetexpress.com.mx/clausulado-solicitud-servicios/)：

- **免费客服电话：** `+52 800 821 0208`——周一至周五 08:00–21:00、周六 08:00–15:00（服务条款中标注的太平洋时间工作时段为 07:00–20:00 及 07:00–14:00）。
- **WhatsApp：** `+52 668 195 0169`（[paquetexpress.com.mx](https://www.paquetexpress.com.mx/) 上有链接）。
- **理赔邮箱与入口：** `reclamaciones@paquetexpress.com.mx` 与[在线理赔申请页](https://www.paquetexpress.com.mx/contacto/solicitud-de-reclamacion/)。
- **隐私 / ARCO 请求邮箱：** `solicitud.arco@paquetexpress.com.mx`。
- **总部地址：** Operadora de Servicios Paquetexpress, S.A.P.I. de C.V., Blvd. Rosendo G. Castro No. 32 Poniente, Col. Centro, Los Mochis, Sinaloa, C.P. 81200, Mexico。

> 请警惕要求你在线刷卡缴费才能重新安排 Paquetexpress 派送的虚假短信或邮件链接：Paquetexpress 确认你可直接拨打 `800 821 0208` 变更派送指令，或前往 [paquetexpress.com.mx/rastreo](https://www.paquetexpress.com.mx/rastreo) 查询货件。详见[快递诈骗短信防范指南](guide:delivery-scam-texts)（英文）。

## 使用 Peek 追踪 Paquetexpress 包裹

由于 11 至 13 位纯数字追踪码与许多国际快递重叠，[Peek](/) 会根据你的所在地区决定向 Paquetexpress 发起查询的顺位。在墨西哥地区，Peek 会针对匹配的 *guía* 运单号（如虚构单号 `MEX48WW2917364`）与 11 至 13 位追踪码（`482917364058`）在首轮直接询问 Paquetexpress，确认后显示“Paquetexpress 有这个包裹的记录。”在墨西哥以外地区，*guía* 运单号仍直接发往 Paquetexpress，而 11 至 13 位数字码则最后才尝试 Paquetexpress。无论身在何处，粘贴 `paquetexpress.com.mx/rastreo/...`（或 `/en/rastreo/...`）链接均可直接指定 Paquetexpress，也可以在添加时点击“更换”或在包裹页面点击“更换快递公司”（搜索“Paquete Express”同样能找到）。

Peek 直接查询 Paquetexpress 历史接口（支持追踪码与 *guía* 运单号，不支持寄件人参考号），展示映射阶段（“已生成面单”、“已揽收”、“运输中”、“派送中”、“待取件”、“已签收”）、西班牙语状态说明（已剔除 `POR`、`a` 与 `, recibió` 后的个人姓名）、网点/城市位置、有效的预计送达日期、签收时间以及墨西哥各网点当地时区偏移（兼容太平洋、山区、中部及金塔纳罗奥时区，并转换为你设备的本地时钟）。对于一票多件的 *guía*，只要其中任一包裹仍在运输中，即使另一件已送达，Peek 也会保持货件处于跟踪状态；对于 *Ocurre* 自提件（`ENT_OCU`），出现 `Recibida En Sucursal destino` 时会自动标为“待取件”。Peek 会隐藏网站进度条、服务类型、始发/目的地抬头、客户参考号、收发件人姓名、网点街道地址和签名/签收照片；重量与尺寸数据亦不对外展示。

当你在 Peek 中打开包裹页面，或在派送当日开启通知时，Peek 最多每 10 分钟检查一次；在 Paquetexpress 显示 `En Ruta De Entrega A Domicilio` 当天，只要 Paquetexpress 自己的追踪系统有响应，Peek 会从 Paquetexpress 最新扫描所在地早上 6 点起每 2 分钟检查一次。其余情况下每小时检查一次。若包裹停留在“已生成面单”或“已揽收”超过 12 小时，或连续两天没有新动态，无论你以何种方式关注，均改为每小时检查一次；若停留在“已生成面单”超过七天，则每 6 小时检查一次。当Paquetexpress 自己的追踪系统无响应、Peek 仅通过追踪服务跟进时，每小时检查一次，静默一天后每 3 小时一次，派送当天最多每 15 分钟一次。

当最新扫描距今已满四天，且包裹仍处于“已生成面单”、“已揽收”或“运输中”时，Peek 会用该次扫描的日期加以标记，例如“自10月12日周一起没有物流更新”。包裹页面随后会显示“包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 Paquetexpress 查询。”，并附上指向 Paquetexpress 联系页面的“向 Paquetexpress 寻求帮助”链接。如果三周没有新扫描，页面会显示“已超过三周没有新动态。如果还没收到，请联系卖家。Peek 会持续向 Paquetexpress 查询。”

你可以选择按“每次扫描”、“重要环节”或“仅派送当天”提醒。Peek 仅监控物流更新，无法代你切换 *Ocurre* 与上门派送或向 Paquetexpress 提交理赔。

## 关于 Paquetexpress 物流查询的问题

### Paquetexpress 会尝试派送几次？

根据[服务条款](https://www.paquetexpress.com.mx/clausulado-solicitud-servicios/)第 5 条，Paquetexpress 最多进行 **2 次**上门派送。如果无人签收，司机会留下到访通知单（*aviso de visita*）并将包裹存放在当地网点。

### Paquetexpress 为 Ocurre 自提件或未投递件保留多久？

自到达网点或留下到访通知单起，你有 **5 个自然日**的免费取件期。逾期后 Paquetexpress 可将包裹转入无人认领仓库并按运费的 **2%/天**加收仓储费，超过 90 个自然日可能被拍卖或销毁。

### 可以将 Ocurre 网点自提改为上门派送，或修改收货地址吗？

可以。Paquetexpress [常见问题](https://www.paquetexpress.com.mx/preguntas-frecuentes/)表示，你可拨打 `(+52) 800 821 0208` 申请切换 *Ocurre* 与上门派送，或更正收货地址（即使包裹已经从网点发出）。

### 向 Paquetexpress 提交货损或丢件理赔的期限是多久？

签收时请在签收单（*acuse de recibo*）上注明可见破损，并在 **15 个工作日**内前往网点、发邮件至 `reclamaciones@paquetexpress.com.mx` 或通过[在线理赔表单](https://www.paquetexpress.com.mx/contacto/solicitud-de-reclamacion/)提交正式索赔。Paquetexpress 会在 15 个工作日内回复。

:::sources
- [Paquetexpress：首页](https://www.paquetexpress.com.mx/) – 追踪入口、WhatsApp `+52 668 195 0169` 与免费热线 `+52 800 821 0208`
- [Paquetexpress：Rastreo](https://www.paquetexpress.com.mx/rastreo) – 货件追踪页面
- [Paquetexpress：Servicios](https://www.paquetexpress.com.mx/servicios/) – 24–120 小时国内时效、Express 当日/24/48 小时、周六派送、Acuse 签收记录及 240+ 国家
- [Paquetexpress：International](https://www.paquetexpress.com.mx/en/internacional/) – 国际寄递服务
- [Paquetexpress：Conócenos](https://www.paquetexpress.com.mx/conocenos/) – 40 年历史、12,200+ 员工与 240+ 国家覆盖
- [Paquetexpress：Sucursales](https://www.paquetexpress.com.mx/sucursales) – 营业网点查询器
- [Paquetexpress：Preguntas Frecuentes](https://www.paquetexpress.com.mx/preguntas-frecuentes/) – 无需账号追踪、追踪码/guía/参考号说明、Ocurre 与上门派送切换、地址更正、到访通知与开箱报告
- [Paquetexpress：Contact (EN)](https://www.paquetexpress.com.mx/en/contacto/) – `+52 800 821 0208`，周一至周五 08:00–21:00，周六 08:00–15:00
- [Paquetexpress：Contacto (ES)](https://www.paquetexpress.com.mx/contacto/) – 西班牙语联系页面（Peek 帮助按钮链接地址）
- [Paquetexpress：Solicitud de Reclamación](https://www.paquetexpress.com.mx/contacto/solicitud-de-reclamacion/) – 在线正式理赔申请页
- [Paquetexpress：Clausulado de Solicitud de Servicios](https://www.paquetexpress.com.mx/clausulado-solicitud-servicios/) – PROFECO 合同 `3808-2020`：两次派送、5 个自然日免费取件、2%/天仓储费、90 天弃件、15 个工作日理赔（`reclamaciones@paquetexpress.com.mx`）及洛斯莫奇斯总部
- [PROFECO](https://www.gob.mx/profeco) – 墨西哥联邦消费者保护署
- [Universal Parcel Scraper: paquetexpress](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/paquetexpress) – guía 与 11–13 位单号识别、墨西哥优先查询、`/rastreo/` 链接规则、事件代码、ETA、一票多件 guía、`ENT_OCU` 自提识别、个人姓名脱敏与帮助链接
:::
