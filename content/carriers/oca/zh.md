---
title: OCA 阿根廷快递追踪：19 位单号、状态说明、网点自提与客服
description: 追踪阿根廷 OCA 及 e-Pak 包裹：19 位单号格式、西班牙语状态含义、两次上门派送、5 个工作日网点自提、智能柜及客服支持。
slug: oca-tracking
published: 2026-11-26
updated: 2026-11-26
---

OCA 是阿根廷本土包裹与邮件承运商。其 ePak 平台面向全阿根廷的电商平台、大型企业及中小企业提供配送服务，并与 Mercado Envíos 合作。收件人可通过 [OCA 包裹查询页](https://www.oca.com.ar/Busquedas/Envios)追踪货件，前往遍布阿根廷各省的 Puntos OCA 网点自提暂存包裹，或在 OCA SmartLockers 智能柜取件。

OCA 的追踪系统以西班牙语记录每次分拨中心扫描、网点转运、上门派送尝试及自提窗口信息。

## OCA 追踪单号格式

标准 OCA e-Pak 包裹单号采用 19 位数字结构：

- **第 6 至第 9 位为 `0000` 的 19 位数字**（例如 `3654800004829173605`）——OCA 帮助中心指明用于查询的运单号或收寄订单号（`número de guía` / `orden de admisión`）。
- **末尾分开打印的 `-1`**——面单可能在单号末尾加印 `-1`（例如 `3654800004829173605-1`）。[OCA 帮助中心](https://www.oca.com.ar/CentroDeAyuda)提示不要输入该后缀。在 Peek 中同样请省略：Peek 仅去除短横线，导致 `...-1` 变成 OCA 无法识别的 20 位数字。
- **贴票包裹与信封**——OCA 还签发由 3 位大写字母（`YAE`、`YAA`、`MDA`、`PAA`）加 8 位数字组成的贴票包裹单号，以及 2 位字母加 8 位数字组成的贴票信封单号，在 OCA 官网查询时需去除空格和括号。普通平信（`postales simples`）不提供追踪，直接投递至信箱或门缝。

在 OCA 官网上，您可以用 `//` 代替中间连续的零；但 Peek 需要完整的 19 位数字，或直接粘贴 `oca.com.ar/Seguimiento/Paquetes/...` 及旧版 e-Pak 链接（Peek 会自动识别）。在阿根廷地区查询时，Peek 会将此类 19 位单号发送给 OCA；在其他地区则仅在最后顺位尝试 OCA。如需了解其他地区单号格式，请参阅[单号格式指南](guide:tracking-number-formats)（英文）。

## OCA 追踪状态含义

OCA 的 e-Pak 追踪服务以西班牙语播报状态，通常在短横线后附带经办网点名称（`- Suc: ...`），或在括号中标明原因。

| OCA 状态原文 | 实际含义 |
| --- | --- |
| `En proceso de Retiro` | 已安排从发件人处上门揽收，OCA 尚未取走包裹。 |
| `Retirado` / `Retirado en Origen` | OCA 已从发件人处揽收包裹。 |
| `En Proceso en OCA` / `Procesado en OCA` | 正在 OCA 分拣中心或营业网点内处理。 |
| `En viaje a Centro de Distribución de Destino` / `En viaje a Sucursal de Destino` / `En Tránsito a Suc. de Destino` | 正在运往目的地分拨中心或派送网点。 |
| `Arribado a Centro de Distribución de Destino` / `Llegó a nuestra sucursal` | 已抵达目的地分拨中心或当地 OCA 网点。 |
| `Programado para visita a domicilio` | 已在当地网点进入上门派送排班（`en programación`）。 |
| `Visita a Domicilio en Curso` | 快递员今日正在上门派送途中。 |
| `No entregado (No Visitado)` / `No entregado (...)` | 未能送达；括号内注明原因（如未能上门或应发件人要求中止）。 |
| `Reprogramado para nueva visita` | 已重新排期进行下一次派送尝试。 |
| `En Espera de Retiro por Sucursal` / `Disponible para que lo retires en tu Sucursal` | 已寄存在指定 OCA 网点等待自提。 |
| `Estado de Visita ONLINE (Entregado)` / `Entregado` | 已交由收件人签收或在网点完成领取。 |
| `Acuse digitalizado` | 送达数日后在 OCA 中心扫描归档的签收凭证——仅属行政归档，不代表包裹发生移动。 |
| `Devolución en Proceso` / `Devuelto al Remitente` | 正在退回或已退回发件人。 |

## 上门派送、网点自提与 SmartLockers 智能柜

[OCA 帮助中心](https://www.oca.com.ar/CentroDeAyuda)明确了阿根廷全境的配送规则：

- **两次上门派送尝试：** OCA 在周一至周六 09:00–18:00 进行两次非预约上门派送。收货地址内任意年满 18 周岁的人员出示阿根廷国民身份证（DNI）即可代收。雨天可能会限制派送班次。
- **网点自提（5 个工作日）：** 若两次上门均未成功，包裹将在指定的 Punto OCA 网点保留 **5 个工作日**（可通过 [Buscador de Puntos OCA](https://www.oca.com.ar/Busquedas/Sucursales) 查找网点）。收件人须携带 DNI 和追踪单号；年满 18 周岁的被委托人须携带收件人 DNI 复印件、手写授权书及追踪单号。逾期未领将退回发件人。
- **SmartLockers 智能柜（48 小时 + 可选延期）：** 包裹存入 SmartLocker 后，您会收到包含二维码和字母数字 PIN 码的邮件和/或短信。您拥有连续 **48 小时**的免费取件期；收到提醒后，OCA 会发送银行卡支付链接，您最多可付费延长 **3 天**保管期，逾期退回发件人。
- **地址有误或包裹退回：** OCA 不允许在运输途中拦截货件或修改收货地址。请等待派送尝试结束后前往网点自提（或待其退回发件人后由发件人重寄）。更多错过派送的处理建议请参阅[错过派送处理指南](guide:missed-delivery)（英文）。
- **特殊证件与信封（DNI、护照、OCA Geo 卡、信封）：** DNI 身份证仅限上门本人凭证（RENAPER 回执联）领取，不可网点自提；护照在两次上门未果后转存网点；OCA Geo 卡须交由持卡人本人签收；贴票信封安排两次上门，未送达直接退回而不在网点留存。
- **首次扫描需最长 48 个工作小时：** OCA 说明新收寄的货件从网点交寄到官网显示记录最多可能需要 **48 个工作小时**。若超过 48 个工作小时仍无记录，仅发件人有权发起查单。通用排查请参阅[为何物流停止更新](guide:tracking-not-updating)（英文）。

如需对比其他阿根廷本土快递，可查阅 [Andreani](carrier:andreani)。

## 使用 Peek 追踪 OCA

将去掉 `-1` 的 19 位 OCA 单号或 `oca.com.ar/Seguimiento/Paquetes/...` 链接粘贴到 [Peek](/) 中。若 Peek 未自动锁定 OCA，可在添加包裹时点击**更改**，或在追踪页面点击**更改承运商**手动选择 OCA。

Peek 通过开源的 [universal-parcel-scraper OCA 适配器](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/oca)直接读取 OCA e-Pak 的 `Tracking_Pieza` 接口。每条扫描的阿根廷时间均按 `America/Argentina/Buenos_Aires`（`-03:00`）精准记录，网点名称会自动提取至地点栏，且行政性的 `Acuse digitalizado` 记录绝不会覆盖或干扰已完成的送达状态。OCA 的 e-Pak 数据不提供预计送达日期或包裹重量。

Peek 将 OCA 状态映射为清晰阶段：**已创建面单**（`En proceso de Retiro`）、**承运商已揽收**（`Retirado`）、**运输中**（分拣中心及网点转运）、**派送中**（`Programado para visita a domicilio` 与 `Visita a Domicilio en Curso`）、**派送未成功**（`No entregado` 与 `Reprogramado para nueva visita`）、**待取件**（`En Espera de Retiro por Sucursal`）、**已送达**（`Entregado`）以及**已退回寄件人**（`Devolución en Proceso` / `Devuelto al Remitente`）。

当您在 Peek 中打开包裹页面，或在派送当日开启通知时，Peek 最多每 10 分钟检查一次；在 OCA 显示 `Programado para visita a domicilio` 或 `Visita a Domicilio en Curso` 当天，若 OCA 官网正常响应，Peek 会从阿根廷时间早上 6 点起每 2 分钟检查一次。其余情况每小时检查一次。若停留在“已创建面单”或“承运商已揽收”超过 12 小时，或连续两天无新动态，无论关注方式如何均改为每小时检查一次；若停留在“已创建面单”超过七天，则每 6 小时检查一次。当 OCA 无响应、Peek 仅通过追踪服务跟进时，默认每小时检查一次，静默一天后每 3 小时一次，派送中当天最多每 15 分钟一次。

当最新扫描距今已满四天且包裹仍停留在“已创建面单”、“承运商已揽收”或“运输中”时，Peek 会按该次扫描日期加以标记，例如“自 10 月 12 日（周一）起无物流更新”。包裹页面随即会提示“包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 OCA 查询。”并提供指向 OCA 帮助中心的“从 OCA 获取帮助”链接。如果超过三周仍无新扫描，页面会提示“超过三周没有新动态。如果包裹仍未送达，请联系卖家。Peek 会持续向 OCA 查询。”

您可在 Peek 提醒设置中选择**每次扫描**、**重要节点**或**仅送达通知**以控制通知频率。

## 联系 OCA

- **帮助中心与在线工单：** [OCA Centro de Ayuda](https://www.oca.com.ar/CentroDeAyuda)（含常见问题分类及页面底部的直接留言表单）
- **官方运单查询：** [OCA Búsqueda de envío](https://www.oca.com.ar/Busquedas/Envios)
- **网点查询器：** [Buscador de Puntos OCA](https://www.oca.com.ar/Busquedas/Sucursales)
- **官方网站：** [oca.com.ar](https://www.oca.com.ar/)
- **查无记录或退件重寄须先找发件人：** OCA 规定，若交寄满 48 个工作小时后仍无物流记录，或包裹退回后需安排重发，仅发件人有权提出调查或重寄申请。

## 常见问题

### 为什么我的 OCA 追踪单号末尾有 `-1`？

OCA 帮助中心说明查询时应省略末尾单独印出的 `-1`，只输入 19 位运单或收寄编号。在 Peek 中也请这样做：Peek 不会自动去掉 `-1`，保留它会导致 OCA 无法识别单号。

### OCA 在网点或 SmartLocker 智能柜最多保留包裹多久？

两次派送未成功或抵达自提网点后，OCA 在 Puntos OCA 网点为包裹保留 **5 个工作日**。存入 SmartLockers 智能柜后有连续 **48 小时**免费取件时间，之后可在线付费最多延长 **3 天**，逾期退回发件人。

### OCA 揽收后可以更改收货地址吗？

不可以。OCA 帮助中心明确说明，包裹一旦进入运输流程便无法更改配送流程或收货地址。您可在上门派送结束后携带 DNI 前往指定 OCA 网点自提，或待包裹退回发件人后再由其重寄。

### 为什么我的 OCA 单号还没有任何更新？

OCA 提示货件从网点收寄到上线显示最长可能需要 **48 个工作小时**。若超过 48 个工作小时仍无记录，请联系发件人，只有发件人有权向 OCA 发起缺失记录核查。

:::sources
- [OCA 阿根廷官网](https://www.oca.com.ar/)
- [OCA 帮助中心（Centro de Ayuda）](https://www.oca.com.ar/CentroDeAyuda)
- [OCA 运单查询（Búsqueda de envío）](https://www.oca.com.ar/Busquedas/Envios)
- [OCA 网点查询（Buscador de Puntos OCA）](https://www.oca.com.ar/Busquedas/Sucursales)
- [universal-parcel-scraper OCA 适配器](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/oca)
:::
