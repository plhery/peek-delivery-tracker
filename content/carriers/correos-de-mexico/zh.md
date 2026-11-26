---
title: Correos de México 墨西哥邮政追踪：MX 单号、理赔与客服
description: 追踪墨西哥邮政（Correos de México / SEPOMEX）包裹：MX 结尾 S10 单号、按年份查询、西语状态、窗口自提与 30 天理赔。
slug: correos-de-mexico-tracking
published: 2026-11-26
updated: 2026-11-26
---

墨西哥邮政（Correos de México，又称 *Servicio Postal Mexicano* / *SEPOMEX*）是墨西哥国家邮政服务机构，承运国内挂号邮件（*Registrados*）、*Mexpost* 特快包裹以及出境和来自全球邮政网络的 UPU 国际邮件。您可以在 [Correos de México 追踪页面](https://www.correosdemexico.gob.mx/SSLServicios/SeguimientoEnvio/Seguimiento.aspx)查询货件（需同时输入追踪单号及交寄年份 *Ejercicio*），或直接将单号粘贴到上方输入框中。

## Correos de México 追踪单号：UPU S10 格式

请查看墨西哥邮局柜台收据、Mexpost 运单或国际卖家的发货邮件。Correos de México 追踪 13 位 UPU S10 国际邮政单号——既包括以 `MX` 结尾的墨西哥出境单号，也包括发往墨西哥的境外邮政 S10 邮件（如 `...US`、`...CN`、`...ES` 或 `...JP`）：

| 格式（虚构示例） | 说明 |
| --- | --- |
| `RR482917367MX` | 从墨西哥寄出的挂号信/挂号件（*Correo Registrado*，以 `MX` 结尾的 UPU S10）。 |
| `MP482917367MX` | *Mexpost* 国内或特快邮政包裹（以 `MX` 结尾的 UPU S10）。 |
| `EE482917367MX` | 从墨西哥寄出的国际 EMS 特快专递（以 `MX` 结尾的 UPU S10）。 |

**SEPOMEX 官网的年份选择（*Ejercicio*）。** 与大多数追踪器不同，[Correos de México 查询门户](https://www.correosdemexico.gob.mx/SSLServicios/SeguimientoEnvio/Seguimiento.aspx)按自然年份（`2026`、`2025`、`2024` 等）归档扫描记录。如果 12 月底寄出的包裹跨年运输至次年 1 月，您可能需要在官网分别查询两年以查看完整轨迹。对于墨西哥私营快递（如 [Estafeta](carrier:estafeta)、[J&T Express Mexico](carrier:j-and-t-mx) 或 [Paquetexpress](carrier:paquetexpress)），请参阅[单号格式指南](guide:tracking-number-formats)（英文）。

## Correos de México 追踪状态含义

Correos de México 以西班牙语展示扫描事件，并附带墨西哥邮局或境外国际互换局名称与时间戳：

| Correos de México 显示状态 | 对您的实际含义 | Peek 显示阶段 |
| --- | --- | --- |
| `Depósito en Oficina de Correos` / `Deposito del Cliente en ...` | 已在墨西哥邮局柜台交寄，或在境外由发件国邮政收寄。 | “承运商已揽收” |
| `En tránsito hacia destino`、`Recepción en Oficina de Correos`、`En zona de intercambio int. hacia el país destino, previo Aduana`、`Pieza depositada en valija y enviada a oficina del País destino` | 正在墨西哥各邮局分拨中转，或已装袋发往目的地国家。 | “运输中” |
| `Arribo a Zona Intercambio México, en espera de ingreso a la Aduana`、`Inicio de la revisión aduanal en país destino` | 已抵达国际互换局，正等待或正在接受海关查验。 | “清关中” |
| `Revisión aduanal concluida` | 海关查验完毕，邮件已放行并恢复邮政运输。 | “运输中” |
| `Con mensajero para entrega` | 当地邮递员今日已外出派送。 | “派送中” |
| `Puesta en ventanilla` | 已存放在注明的当地邮局窗口等待收件人自提。 | “待取件” |
| `Pieza devuelta ...`（如 `por domicilio destino incorrecto`） | 因所列原因正在退回发件人。 | “已退回寄件人” |
| `Entrega` / `Entrega Internacional` | 已在墨西哥送达，或经目的地邮政确认已在境外送达。 | “已送达” |

## 邮局窗口自提（*Puesta en ventanilla*）、清关与理赔

根据 [gob.mx 上的墨西哥邮政门户](https://www.gob.mx/correosdemexico)及官方[货件理赔页面（Reclamo de un Envío）](https://www.gob.mx/correosdemexico/es/acciones-y-programas/reclamo-de-un-envio)：

- **窗口自提（*Puesta en ventanilla*）：** 当追踪显示 `Puesta en ventanilla` 时，您的包裹正存放在扫描记录 *Origen* 栏列出的当地邮局等待领取。
- **入境国际清关：** 寄往墨西哥的国际邮政包裹须经墨西哥海关查验（`Inicio de la revisión aduanal...`）。清关等待时长随淡旺季及海关核税情况而异，详见[包裹滞留海关指南](guide:customs)（英文）。
- **提交正式索赔（*Reclamo de un Envío*）：** 正式查询与赔偿申请**仅适用于挂号邮件（*Registrados*）和 *Mexpost* 服务**（普通平信不在范围内）。当货件超过投递时限或到货状况受损时，您可亲自前往任意 Correos de México 邮局或在线提交申请。SEPOMEX 对挂号邮件与 Mexpost 索赔的最长答复期限为 **30 个工作日**（*30 días hábiles*）。

## Correos de México 包裹停滞、无更新或退回怎么办

- **清关或国际转运长时间停顿：** 进出墨西哥的国际邮政包裹常在 `Arribo a Zona Intercambio México` 或 `Pieza depositada en valija` 与抵达目的国之间停顿数天甚至数周。详见[为何物流停止更新](guide:tracking-not-updating)（英文）。
- **退回件（`Pieza devuelta`）：** 一旦 SEPOMEX 将邮件标记为 `Pieza devuelta`（例如地址错误或窗口逾期未领），其后出现的 `Puesta en ventanilla` 与 `Entrega` 均指将包裹退还给原发件人。
- 网购订单请在发起邮政查询的同时联系卖家。详见[显示已签收但未收到包裹](guide:delivered-not-received)（英文）、[包裹损坏处理指南](guide:damaged-parcel)（英文）、[包裹丢失处理指南](guide:lost-parcel)（英文）与[包裹退回发件人](guide:returned-to-sender)（英文）。

## Correos de México 客服与联系方式

以下官方渠道发布于 [gob.mx/correosdemexico](https://www.gob.mx/correosdemexico)、[Seguimiento.aspx](https://www.correosdemexico.gob.mx/SSLServicios/SeguimientoEnvio/Seguimiento.aspx) 及 [Reclamo de un Envío](https://www.gob.mx/correosdemexico/es/acciones-y-programas/reclamo-de-un-envio)：

- **官方咨询邮箱：** `contacto@correosdemexico.gob.mx`。
- **理赔与查询：** 访问 [Reclamo de un Envío](https://www.gob.mx/correosdemexico/es/acciones-y-programas/reclamo-de-un-envio) 或亲自前往当地墨西哥邮局办理。
- **墨西哥消费者纠纷：** 如与网购商家或服务方的纠纷未获解决，可联系墨西哥联邦消费者保护署（[PROFECO](https://www.gob.mx/profeco)）。

> 请警惕冒充 Correos de México / SEPOMEX 发送的虚假短信或 WhatsApp 消息，切勿点击其中要求在线刷卡缴费以“更正邮编”或“放行包裹”的链接。请务必直接在 [correosdemexico.gob.mx](https://www.correosdemexico.gob.mx/SSLServicios/SeguimientoEnvio/Seguimiento.aspx) 核验 S10 单号或致信 `contacto@correosdemexico.gob.mx`。详见[快递诈骗短信防范指南](guide:delivery-scam-texts)（英文）。

## 使用 Peek 追踪 Correos de México 包裹

[Peek](/) 会直接将粘贴的 `correosdemexico.gob.mx/SSLServicios/SeguimientoEnvio/Seguimiento.aspx?guia=` 链接识别为 Correos de México。输入以 `MX` 结尾的 13 位 UPU S10 单号（虚构示例 `RR482917367MX`、`MP482917367MX` 或 `EE482917367MX`）时，Peek 会优先询问 Correos de México，确认后显示“Correos de México 有这个包裹的记录。”从海外寄往墨西哥的邮件沿用发件国邮政单号（如 `US`、`CN` 结尾），Peek 默认询问发件国邮政；如需改在 Correos de México 追踪，可在添加时点击“更改”或在包裹页面点击“更改承运商”并选择 Correos de México（搜索“SEPOMEX”、“Mexpost”或“Mexico Post”均可找到）。

Peek 直接读取 Correos de México 官网追踪器，并会自动回溯表单支持的往年记录，确保跨年包裹轨迹完整。页面展示映射阶段（“承运商已揽收”、“运输中”、“清关中”、“派送中”、“待取件”、“已退回寄件人”、“已送达”）、西班牙语扫描文案、邮局或互换局名称、出现 `Puesta en ventanilla` 时的自提邮局以及签收时间（如有）。在 `Pieza devuelta` 之后，Peek 会将后续窗口与投递扫描继续保持在“已退回寄件人”阶段，因为此时是退还给发件人而非派送给您。由于官网时间不附带时区，Peek 按各扫描邮局所在时区解析（如蒂华纳用蒂华纳时间、墨西哥城用墨西哥城时间）并转换为您设备的本地时钟。Peek 会省略签收人姓名、查询时间及页面公告；官网不提供预计送达日期、重量、服务名称或签收照片。

当包裹在 Peek 中打开或派送当日开启通知时，Peek 最多每 10 分钟检查一次；在 Correos de México 显示 `Con mensajero para entrega` 当天，若官网正常响应，从最新扫描邮局所在地早上 6 点起每 2 分钟检查一次。其余情况每小时一次。停留在“已创建面单”或“承运商已揽收”满 12 小时或连续两天无新动态后，改为每小时一次；停留在“已创建面单”满七天后每 6 小时一次。当官网无响应、Peek 仅通过追踪服务跟进时，默认每小时一次，静默一天后每 3 小时一次，派送中当天最多每 15 分钟一次。

当最新扫描距今已满四天且包裹仍停留在“已创建面单”、“承运商已揽收”或“运输中”时，Peek 会按该次扫描日期加以标记，例如“自 10 月 12 日（周一）起无物流更新”。包裹页面随即会提示“包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 Correos de México 查询。”并提供指向 *Reclamo de un Envío* 页面的“从 Correos de México 获取帮助”链接。超过三周无新扫描时，会提示“超过三周没有新动态。如果包裹仍未送达，请联系卖家。Peek 会持续向 Correos de México 查询。”

您可按需选择“每次扫描”、“重要节点”或“仅送达通知”提醒。Peek 仅监控物流更新，无法代您办理清关或向 SEPOMEX 提交 *Reclamo de un Envío* 索赔。

## 关于 Correos de México 追踪的常见问题

### Correos de México 支持哪些格式的追踪单号？

支持 13 位 UPU S10 国际邮政单号：2 位字母 + 9 位数字 + 2 位国家字母代码（例如虚构示例 `RR482917367MX` 或 `MP482917367MX`），同时也支持进入墨西哥的境外 S10 邮件。

### `Puesta en ventanilla` 是什么意思？

表示您的包裹目前存放在该扫描记录 *Origen* 一栏注明的当地邮局柜台，等待您亲自前往领取。

### 为什么 Correos de México 官网查不到我的单号？

在 [Seguimiento.aspx](https://www.correosdemexico.gob.mx/SSLServicios/SeguimientoEnvio/Seguimiento.aspx) 查询时，请务必选对交寄年份（*Ejercicio*），尤其是上一年年底寄出的跨年邮件；同时请注意普通平信不提供扫描追踪。

### 如何向 Correos de México 提交理赔？

针对超期未达或受损的挂号件（*Registrados*）与 *Mexpost* 包裹，可在任意邮局或通过 [gob.mx/correosdemexico/es/acciones-y-programas/reclamo-de-un-envio](https://www.gob.mx/correosdemexico/es/acciones-y-programas/reclamo-de-un-envio) 提交 *Reclamo de un Envío*（最长 30 个工作日答复），或致信 `contacto@correosdemexico.gob.mx`。

:::sources
- [Correos de México (SEPOMEX): gob.mx 门户](https://www.gob.mx/correosdemexico) – 墨西哥国家邮政官方门户及 `contacto@correosdemexico.gob.mx`
- [Correos de México: Seguimiento de Envíos](https://www.correosdemexico.gob.mx/SSLServicios/SeguimientoEnvio/Seguimiento.aspx) – 按年份（*Ejercicio*）查询 S10 单号及 `contacto@correosdemexico.gob.mx`
- [Correos de México: Reclamo de un Envío](https://www.gob.mx/correosdemexico/es/acciones-y-programas/reclamo-de-un-envio) – 挂号与 Mexpost 理赔资格、线下/线上提交与 30 个工作日答复期限（Peek 帮助按钮链接地址）
- [PROFECO](https://www.gob.mx/profeco) – 墨西哥联邦消费者保护署
- [Universal Parcel Scraper: correos-de-mexico](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/correos-de-mexico) – S10 格式、`Seguimiento.aspx?guia=` 链接规则、跨年表单检索、西班牙语状态映射、`Puesta en ventanilla` 自提点、退件段处理、邮局时区解析及帮助链接
:::
