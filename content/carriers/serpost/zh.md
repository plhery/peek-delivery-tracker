---
title: 秘鲁邮政（Serpost）物流查询：S10 单号、海关状态与客服
description: 查询秘鲁邮政（Serpost）包裹：了解 13 位 S10 PE 单号、SUNAT 海关清关状态、邮局自提、索赔期限以及利马客服热线。
slug: serpost-tracking
published: 2026-11-26
updated: 2026-11-26
---

**秘鲁邮政（Serpost**，全称 *Servicios Postales del Perú S.A.*）是秘鲁交通通信部（MTC）下属的国有国家邮政运营商（[gob.pe/serpost](https://www.gob.pe/serpost)）。它负责秘鲁全境的国内信函与包裹业务，同时承运国际出境服务（包括 EMS 和 *Exporta Fácil*）以及从海外寄往秘鲁的万国邮政联盟（UPU）入境邮件。你可以在 Serpost 的在线追踪页 [serpost.com.pe/Cliente/SegumientoLinea](https://www.serpost.com.pe/Cliente/SegumientoLinea) 查询秘鲁寄出或发往秘鲁的国际 S10 邮政单号，或直接将 13 位单号粘贴到上方输入框。

## 秘鲁邮政追踪单号（13 位 UPU S10 格式）

Serpost 追踪标准的 13 位 UPU S10 条码：2 位服务字母、8 位序列数字、1 位 S10 校验数字，以及 2 位原发国代码（从秘鲁寄出为 `PE`，从海外寄往秘鲁则为原发国代码，如 `US`、`CN`、`ES` 或 `JP`）：

| 单号格式（虚构示例） | 说明 |
| --- | --- |
| `RR482917367PE` | 从秘鲁寄出的 13 位挂号邮件（`RR` 加带有效 S10 校验位的 9 位数字，结尾为 `PE`）。 |
| `EE739184627PE` | 从秘鲁寄出的 13 位国际 EMS 特快包裹（`EE` 加带有效 S10 校验位的 9 位数字，结尾为 `PE`）。 |

没有 13 位 S10 条码的不可追踪经济信函不会出现在 Serpost 在线追踪系统中。对于海外寄往秘鲁的入境国际包裹（例如以 `CN`、`US` 或 `GB` 结尾），一旦邮件抵达秘鲁，选择 Serpost 即可凭同一 13 位 S10 单号查看秘鲁本地海关（*Aduana*）和邮局网点扫描。详见 [追踪单号格式](guide:tracking-number-formats)（英文）。

## 秘鲁邮政物流状态含义解析

Serpost 的西班牙语节点描述会写明执行扫描的具体秘鲁邮局管理处（*Administración Postal*）：

| Serpost 原文 | 对你的含义 | Peek 显示 |
| --- | --- | --- |
| `ENVÍO ADMITIDO EN ...` | 已在所列秘鲁 Serpost 邮局柜台收寄。 | “已揽收” |
| `ENVÍO LLEGÓ A SERPOST`、`ENVÍO EN PROCESAMIENTO`、`ENVÍO EN TRÁNSITO` | 入境国际包裹已抵达秘鲁 Serpost，正在总部处理或国内转运。 | “运输中” |
| `ENVÍO SUJETO A TRÁMITE ANTE ADUANA` | 已被秘鲁海关（SUNAT）抽中办理清关、估价或进口税审。 | “清关中” |
| `ENVÍO SIN OBSERVACIÓN POR LA ADUANA` | 秘鲁海关无异议放行，交还 Serpost 继续处理。 | “运输中” |
| `ENVÍO SALIÓ DE PERÚ`、`ENVÍO RUMBO AL PAÍS DE DESTINO` | 国际出境邮件已离开秘鲁，发往目的国邮政。 | “运输中” |
| `ENVÍO LLEGÓ A LA ADMINISTRACIÓN POSTAL ...` | 已抵达负责派送的当地秘鲁邮局并准备排线。 | “运输中” |
| `ENVÍO EN PROCESO DE DISTRIBUCIÓN`、`SALIÓ A DISTRIBUCIÓN` | 已交由 Serpost 邮递员外出上门投递。 | “派送中” |
| `ENVÍO DISPONIBLE PARA ENTREGA O RECOJO` | 已在指定 Serpost 邮局备妥，可前往领取。 | “待取件” |
| `INTENTO FALLIDO DE ENTREGA`、`SE INTENTÓ ENTREGAR PERO FUE IMPOSIBLE` | 投递未能完成（收件人不在或无法进入），请留意网点自提通知。 | “派送未成功” |
| `ENTREGADO`、`ENVÍO ENTREGADO`、`FUE ENTREGADO` | 已送达收件人或已在邮局领取。 | “已签收” |

## 派送时效、海关清关与官方索赔期限

根据 [Serpost 寄递服务说明](https://www.gob.pe/institucion/serpost/pages/20800-enviar-cartas-dentro-y-fuera-del-pais-con-serpost) 与 [Serpost 投诉与查询说明](https://www.gob.pe/institucion/serpost/pages/33693-reclamos-y-consultas-servicios-postales-del-peru)：

- **公布的信函参考时效：** 国内 **Expreso** 最长 **48–72 小时**；国内 **Estándar** **5–7 个工作日**；国内 **Económico** **7–10 个工作日**；国际 **Expreso** **7–10 个工作日**；国际 **Económico** **10–30 个工作日**。只有 Expreso 和 Estándar 信函支持在线查询。
- **秘鲁海关（`ENVÍO SUJETO A TRÁMITE ANTE ADUANA`）：** 邮件正留置办理海关手续，在海关放行（`ENVÍO SIN OBSERVACIÓN POR LA ADUANA`）之前不会安排投递。Serpost 追踪页可能显示到货通知（*Aviso de llegada*）。详见 [包裹被海关扣留指南](guide:customs)（英文）。
- **官方索赔窗口与答复期限：** **EMS** 邮件可自预计投递期限次日起至交寄后 **4 个月**内申请索赔（**30 个自然日**内答复）；国际挂号信满 **20 个自然日**后受理，国际包裹满 **30 个自然日**后受理；国内索赔在 **16 个工作日**内答复（国内赔偿标准为**已付邮资的 3 倍**，国际赔偿按 UPU 规则执行）。

## 秘鲁邮政包裹延误、滞留海关或丢失

- **国际邮件静默期：** 出现 `ENVÍO SALIÓ DE PERÚ` 后的出境包裹或海外发往秘鲁的入境包裹，在航空发运与海关入库之间常有数日甚至数周空白。出境 `...PE` 邮件离开秘鲁后，可到目的国国家邮政（如 [USPS](carrier:usps)）继续查询。参阅 [物流为何停止更新](guide:tracking-not-updating)（英文）。
- **提交 Serpost 索赔（*reclamo*）：** 可将索赔表发至 `servicioalcliente@serpost.com.pe`、致电 `(01) 511-5110` 或前往就近 Serpost 邮局现场提交；受理后你会通过邮件收到案件编号（*número de expediente*）用于跟进进度。详见 [包裹丢失](guide:lost-parcel)（英文）、[包裹破损](guide:damaged-parcel)（英文）、[显示已送达却未收到](guide:delivered-not-received)（英文） 和 [退回寄件人](guide:returned-to-sender)（英文）。

## 秘鲁邮政客服与利马联系方式

以下官方渠道公布于 [Serpost 联系方式（gob.pe）](https://www.gob.pe/institucion/serpost/contacto-y-numeros-de-emergencias)：

- **客服热线（秘鲁）：** `(01) 511-5110`。
- **总机：** `511-5000`（总部电话 `(511) 533-2001` / `533-2002` / `533-2003` / `533-2004`）。
- **客服与索赔邮箱：** `servicioalcliente@serpost.com.pe`（站长邮箱：`webmaster@serpost.com.pe`）。
- **总部地址（*Sede Central*）：** Av. Tomás Valle cdra. 7 s/n, Los Olivos, Lima 15302, Peru。
- **在线追踪与政府门户：** [serpost.com.pe/Cliente/SegumientoLinea](https://www.serpost.com.pe/Cliente/SegumientoLinea) 及 [gob.pe/serpost](https://www.gob.pe/serpost)。

> 请警惕谎称 Serpost 包裹被扣、要求点击陌生链接支付小额费用的诈骗短信。任何清关通知请务必直接通过 [serpost.com.pe/Cliente/SegumientoLinea](https://www.serpost.com.pe/Cliente/SegumientoLinea) 或官方 `gob.pe` / SUNAT 渠道核实。参阅 [防范快递诈骗短信](guide:delivery-scam-texts)（英文）。

## 使用 Peek 追踪秘鲁邮政包裹

在 [Peek](/) 中输入 13 位秘鲁 S10 单号（如虚构示例 `RR482917367PE` 或 `EE739184627PE`）后，Peek 会校验 S10 校验位并依次询问 Serpost 和 Chronopost。若要追踪发往秘鲁的境外 S10 单号或手动切换为 Serpost，请在添加时点击“更换”或在包裹页面点击“更换快递公司”选择 **Serpost**（搜索 “SERPOST”、“Peru Post” 或 “Servicios Postales del Perú” 同样可找到）。一旦 Serpost 返回轨迹，Peek 会显示“Serpost 有这个包裹的记录。”

Peek 读取 Serpost 的追踪页，展示对应阶段（“已揽收”、“运输中”、“清关中”、“派送中”、“待取件”、“派送未成功”、“已签收”）、去掉网点 WhatsApp 邀请语后的 Serpost 西班牙语原文、邮局名称以及送达时间。当 Serpost 附带秘鲁境内邮局地图坐标时，Peek 会将该扫描标注在地图上；一旦进入派送中、待取件或已送达，Peek 会将秘鲁设为目的地。Serpost 按利马时间记录扫描，Peek 据此解析并换算为你设备的本地时钟。Serpost 追踪页不提供预计送达日、重量或签收人姓名。

当你在 Peek 中打开包裹页面，或在派送当日开启通知时，Peek 最多每 10 分钟检查一次；在 Serpost 显示 `ENVÍO EN PROCESO DE DISTRIBUCIÓN` 当天，只要 Serpost 的追踪器有响应，会从利马时间早上 6 点起每 2 分钟检查一次。其余情况下每小时检查一次。若包裹停留在“已生成面单”或“已揽收”超过 12 小时，或连续两天没有新动态，无论你以何种方式关注，均改为每小时检查一次；若停留在“已生成面单”超过七天，则每 6 小时检查一次。当 Serpost 的追踪器无响应、Peek 仅通过追踪服务跟进时，每小时检查一次，静默一天后每 3 小时一次，派送当天最多每 15 分钟一次。

当最新扫描距今已满四天，且包裹仍处于“已生成面单”、“已揽收”或“运输中”时，Peek 会用该次扫描的日期加以标记，例如“自10月12日周一起没有物流更新”。包裹页面随后会显示“包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 Serpost 查询。”，并附上指向 Serpost 在 gob.pe 上联系页面的“向 Serpost 寻求帮助”链接。如果三周没有新扫描，页面会显示“已超过三周没有新动态。如果还没收到，请联系卖家。Peek 会持续向 Serpost 查询。”

你可以选择“每次扫描”、“重要环节”或“仅派送当天”通知。Peek 仅负责追踪物流更新，无法替你办理 SUNAT 清关、提交索赔或联系 Serpost。

## 关于秘鲁邮政物流查询的问题

### 秘鲁邮政（Serpost）的追踪单号长什么样？

Serpost 追踪 13 位 UPU S10 单号——2 位字母、9 位数字（末位为校验位）加 2 位原发国代码（如秘鲁的 `PE`，虚构示例 `RR482917367PE` 或 `EE739184627PE`），同时也追踪寄往秘鲁的境外 S10 单号。

### `ENVÍO SUJETO A TRÁMITE ANTE ADUANA` 是什么意思？

表示秘鲁海关（SUNAT）已抽查该国际包裹并要求办理清关或进口税审核，须待海关放行后 Serpost 才能安排当地派送或邮局自提。

### 秘鲁邮政（Serpost）一般多久送达？

根据 [gob.pe](https://www.gob.pe/institucion/serpost/pages/20800-enviar-cartas-dentro-y-fuera-del-pais-con-serpost) 公布的信函时效：国内 Expreso 为 **48–72 小时**，国内 Estándar 为 **5–7 个工作日**，国内 Económico 及国际 Expreso 为 **7–10 个工作日**，国际 Económico 为 **10–30 个工作日**。

### 如何联系秘鲁邮政客服或提交索赔？

可致电 Serpost 客服热线 `(01) 511-5110`、发邮件至 `servicioalcliente@serpost.com.pe` 或前往 Serpost 邮局网点；完整联系渠道见 [gob.pe/institucion/serpost/contacto-y-numeros-de-emergencias](https://www.gob.pe/institucion/serpost/contacto-y-numeros-de-emergencias)。

:::sources
- [Serpost: Seguimiento en Línea](https://www.serpost.com.pe/Cliente/SegumientoLinea) – 官方在线追踪门户与客服热线 `(01) 511-5110`
- [Serpost: Portal Institucional (gob.pe)](https://www.gob.pe/serpost) – *Servicios Postales del Perú S.A.*（MTC）官方政府门户
- [Serpost: Contacto y Números de Atención](https://www.gob.pe/institucion/serpost/contacto-y-numeros-de-emergencias) – 热线 `511-5110`、总机 `511-5000`、`servicioalcliente@serpost.com.pe` 与 Los Olivos 总部（Peek 帮助链接）
- [Serpost: Reclamos y Consultas](https://www.gob.pe/institucion/serpost/pages/33693-reclamos-y-consultas-servicios-postales-del-peru) – 索赔窗口（挂号信 20 天、包裹 30 天、EMS 4 个月）、答复时限（国内 16 个工作日 / EMS 30 个自然日）及赔偿规则
- [Serpost: Envíos Nacionales e Internacionales](https://www.gob.pe/institucion/serpost/pages/20800-enviar-cartas-dentro-y-fuera-del-pais-con-serpost) – 按服务类型公布的信函寄递时效
- [Universal Parcel Scraper: serpost](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/serpost) – UPU S10 校验位验证、`SegumientoLinea` 查询、西班牙语状态规则、网点名与秘鲁地图坐标解析及 `America/Lima` 时间戳
:::
