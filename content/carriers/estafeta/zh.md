---
title: Estafeta 快递追踪：10 位与 22 位单号、两次派送与客服
description: 追踪墨西哥 Estafeta 包裹：10 位追踪码与 22 位运单号格式、状态含义、两次派送与 10 天网点寄存、24 小时理赔及客服电话。
slug: estafeta-tracking
published: 2026-11-26
updated: 2026-11-26
---

Estafeta（*La Fuerza Roja*）是墨西哥规模最大的国内及国际物流运营商之一，成立于 1979 年 8 月 8 日，也是墨西哥首家提供门到门快递与包裹派送服务的公司。Estafeta 服务覆盖墨西哥全境，国际寄递通达 220 多个国家和地区，提供 *Terrestre*（陆运）、*Día Siguiente*（次日达）、*Garantía 12:30*、零担/整车货运（LTL/FTL）、逆向物流以及覆盖全国的零售服务网点。你可以在 [Estafeta 追踪页面](https://www.estafeta.com/rastrear-envio?rastreo=true) 或 [cs.estafeta.com](https://cs.estafeta.com/) 查询包裹，也可以直接将单号粘贴到上方输入框中。

## Estafeta 追踪单号：10 位追踪码与 22 位运单号

每件 Estafeta 货件同时拥有一个简短的 10 位**追踪码**（*código de rastreo*）和一个完整的 22 位**运单号**（*número de guía*）。两者都能在 Estafeta 的查件页面查询：

| 格式（虚构） | 说明 |
| --- | --- |
| `482917364058E704829173` | 22 位完整 *guía* 运单号，第 13 位为字母（例如 `E`、`A` 或 `C`）。 |
| `4829173640580D48291736` | 22 位两日达完整 *guía* 运单号，第 14 位带有服务字母（例如 `D`）。 |
| `4829173640582917364058` | 22 位纯数字完整 *guía* 运单号。与 [Austrian Post](carrier:austrian-post) 和 [USPS](carrier:usps) 共享 22 位数字长度。 |
| `4829173640` | 10 位简短 *código de rastreo* 追踪码。与 [DHL](carrier:dhl)、[Mondial Relay](carrier:mondial-relay)、Relais Colis、[Packeta](carrier:packeta) 等多家承运商共享 10 位数字长度。 |

如果你的墨西哥包裹带有 `JMX` 运单号或按网点前缀编码的 *guía*（如 `MEX...`），请查看 [J&T Express Mexico](carrier:j-and-t-mx) 或 [Paquetexpress](carrier:paquetexpress)；如需了解其他快递格式，请参阅[单号格式指南](guide:tracking-number-formats)（英文）。

## Estafeta 追踪状态含义

Estafeta 追踪页面会显示西班牙语状态说明，并附带扫描城市或分拨中心及当地扫描时间：

| Estafeta 显示状态 | 对你的含义 | Peek 显示阶段 |
| --- | --- | --- |
| `La guía ha sido generada sin embargo el envío aún no es depositado en Estafeta` | 发件人已在线生成电子运单，但包裹尚未交寄或上门揽收。 | “已生成面单” |
| `Recibido por estafeta` | Estafeta 已在营业网点或揽收时接收实物包裹。 | “已揽收” |
| `En tránsito`、`[City] Llegada a centro Operativo plaza Destino`、`Pendiente programacion para salida a ruta` | 包裹正在 Estafeta 全国分拨枢纽流转、已抵达目的地城市运营中心，或正排队安排出车路线。 | “运输中” |
| `En proceso de entrega a domicilio` | 包裹已装上当地 Estafeta 派送车，预计今日送货上门。 | “派送中” |
| `CARGA DEMORADA POR BLOQUEO O ACCIDENTE EXTERNO EN VIA FEDERAL` | 公路货运因联邦公路外部封路或交通事故出现延误。 | “需要留意” |
| `Entregado` | 包裹已送达收件人或在网点完成自提。 | “已签收” |

## Estafeta 派送时间、两次尝试与 10 天网点寄存

根据 Estafeta [常见问题](https://www.estafeta.com/preguntas-frecuentes)与[服务合同](https://www.estafeta.com/contrato-de-servicios)：

- **派送时段：** *Terrestre*（陆运）货件采用开放时段派送，时间为 **09:00 至 18:00**；*Garantía 12:30* 服务于次日 12:30 前送达。计费重量取实际重量与体积重量中的较大值（`长 × 宽 × 高（厘米）÷ 5000`，第 2 条）。
- **两次派送尝试与 10 天寄存（第 7 条）：** 若首次派送未能送达，派送员会留下通知单，并按照你所在邮编的服务频次**免费安排第二次派送**。若第二次派送仍未成功，Estafeta 会留下通知并将包裹寄存在**最近的 Estafeta 营业网点（*punto de venta*）**，最长保留 **10 个自然日**（*10 días naturales*），逾期退回发件人。
- **延误补偿（第 7 条）：** 若因 Estafeta 自身原因未能在承诺时效内送达，寄件人可在 **20 个工作日**内以书面形式申请一次同等服务的免费寄件补偿，有效期为 12 个月。

## Estafeta 包裹延误、损坏或丢失怎么办

如果是网购订单，运输服务由商家向 Estafeta 预订。根据 Estafeta [条款与条件](https://www.estafeta.com/terminos-y-condiciones)，对于墨西哥城及其都会区、瓜达拉哈拉和蒙特雷的派送件，发件人或收件人在满足条件下可在同城内更改派送地址、改为 Estafeta 网点自提、更改派送日期或申请延长派送时段（18:00 至 22:00），发件人也可申请拦截包裹；可在通过 Estafeta 的渠道跟踪包裹时提出申请。如需补发或退款，请直接联系商家。

- **严格的 24 个工作小时理赔时限（第 10 条）：** 根据 Estafeta [服务合同](https://www.estafeta.com/contrato-de-servicios)，针对包裹损坏、部分遗失或确认全部丢失的书面索赔，必须在签收后或确认丢失后的 **24 个工作小时**（*24 horas hábiles*）内提交，收件方为墨西哥城的 Estafeta 客户服务部门。
- **赔偿上限（第 6 条）：** 未购买保价（Declared Value）时，Estafeta 作为承运商的最高赔偿限额为 **30 UMA**（需提供商业价值证明或维修费用凭证）。
- **外部延误与包裹异常：** 可在 [estafeta.com](https://www.estafeta.com/) 查看墨西哥公路或节假日运营公告。如遇物流停滞或包裹问题，请参阅[为何物流停止更新](guide:tracking-not-updating)（英文）、[显示已签收但未收到包裹](guide:delivered-not-received)（英文）、[包裹损坏处理指南](guide:damaged-parcel)（英文）、[包裹丢失处理指南](guide:lost-parcel)（英文）和[包裹退回发件人](guide:returned-to-sender)（英文）。

## Estafeta 客服与联系方式

以下官方渠道均发布于 [Estafeta 联系页面](https://www.estafeta.com/contacto)、[常见问题](https://www.estafeta.com/preguntas-frecuentes)、[关于 Estafeta](https://www.estafeta.com/acerca-de-estafeta)及[条款与条件](https://www.estafeta.com/terminos-y-condiciones)：

- **墨西哥客服电话：** `800 3782338`。
- **24/7 全年无休虚拟助手：** [estafeta.com](https://www.estafeta.com/) 上的 **Beatriz**（*Bety*），可协助预约揽收及查询包裹。
- **在线联系表单与移动应用：** [estafeta.com/contacto](https://www.estafeta.com/contacto) 以及 **Estafeta** 手机应用（iOS / Android）。
- **墨西哥消费者纠纷渠道：** 如与网购商家或快递公司的争议仍未解决，消费者可联系墨西哥联邦消费者保护署（[PROFECO](https://www.gob.mx/profeco)）。

> **Estafeta 官方防诈骗警示：** Estafeta 在 [estafeta.com](https://www.estafeta.com/) 明确提醒，其**绝不会**通过短信、WhatsApp、电子邮件或电话发送链接，要求你支付费用或提供个人信息以完成派送。请务必直接在 [estafeta.com/rastrear-envio?rastreo=true](https://www.estafeta.com/rastrear-envio?rastreo=true) 核验运单，或致电 `800 3782338`。另请参阅[快递诈骗短信防范指南](guide:delivery-scam-texts)（英文）。

## 使用 Peek 追踪 Estafeta 包裹

[Peek](/) 可高置信度自动识别带字母的 22 位 Estafeta 运单号（`482917364058E704829173` 与 `4829173640580D48291736`，虚构），也能识别粘贴的包含 `wayBill=` 的 `cs.estafeta.com` 追踪链接。对于 10 位追踪码（`4829173640`）或 22 位纯数字运单号（`4829173640582917364058`），你在墨西哥跟踪时，Peek 会先询问 Estafeta；在其他地区，则要等其他更可能的快递公司都查不到这个单号后才询问 Estafeta。Estafeta 确认后，Peek 会显示“Estafeta 有这个包裹的记录。”你也可以在添加包裹时点“更换”，或在包裹页面点“更换快递公司”，自己选择 Estafeta。

Peek 直接读取 Estafeta 的查件系统，显示包裹所处阶段（“已生成面单”、“已揽收”、“运输中”、“派送中”、“需要留意”、“已签收”）以及带有城市或枢纽名称的西班牙语扫描说明。Estafeta 给出的扫描时间不带时区：Peek 按扫描所在城市的时区确定时间，并按你设备的时钟显示。服务名称不会出现在任何 Peek 应用中。如果你输入的是 10 位追踪码，Peek 会在“Estafeta 记录的单号为”下方显示完整的 22 位运单号；只生成了面单的运单，在首次扫描前会显示 Estafeta 的登记提示。Peek 不显示收件人姓名、邮编、签名、签收照片、可下载的运单报告和预定派送日期。

当你在 Peek 中打开包裹页面，或在派送当日开着通知时，Peek 最多每 10 分钟检查一次；在 Estafeta 显示 `En proceso de entrega a domicilio` 的当天，只要 Estafeta 自己的查件系统正常响应，自最新扫描所在城市当地时间早上 6 点起每 2 分钟检查一次。其余情况下每小时检查一次。若包裹停留在“已生成面单”或“已揽收”超过 12 小时，或连续两天没有新动态，无论你以何种方式关注，都改为每小时检查一次；在“已生成面单”停留超过七天后，每 6 小时检查一次。当 Estafeta 的查件系统没有响应、Peek 只能通过物流查询服务跟踪时，每小时检查一次，静默一天后每 3 小时一次，派送当天最多每 15 分钟一次。

当最新扫描距今已满四天，且包裹仍处于“已生成面单”、“已揽收”或“运输中”时，Peek 会用该次扫描的日期加以标记，例如“自10月12日周一起没有物流更新”。包裹页面随后会显示“包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 Estafeta 查询。”，并提供指向 Estafeta 联系页面的“向 Estafeta 寻求帮助”链接。如果超过三周没有新扫描，页面会显示“已超过三周没有新动态。如果还没收到，请联系卖家。Peek 会持续向 Estafeta 查询。”

Peek 可以按“每次扫描”、“重要环节”或“仅派送当天”通知你。Peek 只监控物流更新，无法代你重新预约 Estafeta 派送，也无法代你提交 24 小时书面理赔。

## 关于 Estafeta 物流查询的问题

### Estafeta 的 10 位追踪码与 22 位运单号有什么区别？

10 位数字是简短追踪码（*código de rastreo*），而 22 位字符串（通常在第 13 或第 14 位含有一个字母，例如虚构单号 `482917364058E704829173`）是完整运单号（*número de guía*）。两者在 Estafeta 和 Peek 中都能追踪同一件货件。

### Estafeta 会尝试派送几次？

根据 Estafeta [服务合同](https://www.estafeta.com/contrato-de-servicios)第 7 条，Estafeta 会进行**两次派送尝试**。若第二次派送仍未成功，包裹会寄存在最近的 *punto de venta* 营业网点，最长保留 **10 个自然日**，之后退回发件人。

### Estafeta 的派送时间是什么时候？

Estafeta [常见问题](https://www.estafeta.com/preguntas-frecuentes)说明，*Terrestre*（陆运）采用开放派送时段，时间为 **09:00 至 18:00**；*Garantía 12:30* 则在次日 12:30 前送达。

### 如何联系 Estafeta 或提交理赔？

你可拨打 `800 3782338`、填写 [estafeta.com/contacto](https://www.estafeta.com/contacto) 在线表单，或在 [estafeta.com](https://www.estafeta.com/) 与 24/7 虚拟助手 Beatriz 对话。针对损坏或遗失的书面索赔必须在签收后或确认丢失后的 **24 个工作小时**内提交。

:::sources
- [Estafeta：首页](https://www.estafeta.com/) – 覆盖墨西哥全境与 220 多个国家和地区、Beatriz 24/7 助手、公路延误公告及官方防诈骗警示
- [Estafeta：货件追踪](https://www.estafeta.com/rastrear-envio?rastreo=true) – 公开运单号与追踪码查询入口
- [Estafeta：追踪门户](https://cs.estafeta.com/) – Estafeta 直连查询门户
- [Estafeta：关于 Estafeta](https://www.estafeta.com/acerca-de-estafeta) – 由 Gerd P. Grimm 于 1979 年 8 月 8 日创立，墨西哥首家门到门包裹承运商
- [Estafeta：常见问题](https://www.estafeta.com/preguntas-frecuentes) – 客服电话 `800 3782338`、Terrestre `09:00–18:00` 派送时段与 Garantía 12:30
- [Estafeta：服务合同](https://www.estafeta.com/contrato-de-servicios) – 第 2 条体积重（`÷ 5000`）、第 6 条 30 UMA 赔偿限额、第 7 条两次派送与 10 天网点寄存及 20 工作日延误补偿、第 10 条 24 个工作小时理赔期限
- [Estafeta：条款与条件](https://www.estafeta.com/terminos-y-condiciones) – 网站与配送服务通用条款
- [Estafeta：联系我们](https://www.estafeta.com/contacto) – 官方客服联系表单（Peek 帮助按钮链接地址）
- [PROFECO](https://www.gob.mx/profeco) – 墨西哥联邦消费者保护署
- [Universal Parcel Scraper: estafeta](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/estafeta) – 10 位/22 位运单识别规则、`cs.estafeta.com?wayBill=` 链接规则、西班牙语状态文案、按城市时区确定时间、剔除的字段及帮助链接
:::
