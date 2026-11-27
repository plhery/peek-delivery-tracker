---
title: A Tu Hora Express 物流查询：单号、邮编要求与客服
description: 查询西班牙 A Tu Hora Express 包裹：10/16/24 位运单号格式、为何需要 5 位西班牙收件邮编、西语状态含义、异常件处理与联系方式。
slug: a-tu-hora-express-tracking
published: 2026-11-26
updated: 2026-11-26
---

A Tu Hora Express（*A TU HORA EXPRESS, S.L.*）是西班牙本土末端派送快递公司，总部位于马德里大区的圣费尔南多-德埃纳雷斯（San Fernando de Henares），在西班牙本土多地设有分支机构，涵盖马德里、巴塞罗那、瓦伦西亚、塞维利亚、马拉加、阿利坎特、穆尔西亚、阿尔梅里亚、加的斯、卡斯特利翁、科尔多瓦、赫罗纳、格拉纳达、韦尔瓦、哈恩、莱里达和塔拉戈纳。该公司提供 *Express 24h*、*Economy 48h*、*Masivo 72h*、逆向物流（*Reverse Logistics* / *Logística Inversa*）及 *B2B Premium* 等电商与企业配送服务。你可以在 [A Tu Hora Express 查询门户](https://mydelivery.atuhoraexpress.com/#/login-form-cli)查件，或直接将运单号粘贴到上方输入框。

## A Tu Hora Express 运单号与收件邮编

请在网店发货确认邮件或订单页面查找服务单号/运单号。A Tu Hora Express 运单号有三种纯数字长度：

| 格式（虚构） | 含义 |
| --- | --- |
| `0000004829173640` | 16 位 A Tu Hora Express 服务单号（`000000` 加 10 位数字）。在官网查询时须配合 5 位西班牙收件邮编。 |
| `4829173640` | 10 位短单号（前方补齐六个 0 即为 16 位服务单号）。同样需要配合 5 位收件邮编。 |
| `000000482917364000128013` | 24 位完整条码：16 位服务单号 + `001` + 5 位西班牙收件邮编（此处为 `28013`）。与 [Ciblex](carrier:ciblex)、[InPost](carrier:inpost)、[bpost](carrier:bpost)、[Austrian Post](carrier:austrian-post) 和 Express One 共用 24 位长度。 |

**为什么必须填写邮编。** 在 [mydelivery.atuhoraexpress.com](https://mydelivery.atuhoraexpress.com/#/login-form-cli) 上，10 位和 16 位单号只有在配对输入 **5 位西班牙收件邮编**时才会返回包裹详情；24 位条码末五位已内嵌该邮编，因此无需再单独输入邮编。其他西班牙与国际条码形态请参阅[运单号格式说明](guide:tracking-number-formats)（英文）。

## A Tu Hora Express 物流状态含义

A Tu Hora Express 每条扫描均以西班牙语提供，附带状态码（`cod`）及马德里当地时间：

| A Tu Hora Express 显示（状态码） | 对你的含义 | Peek 显示 |
| --- | --- | --- |
| `Documentado`（`0`） | 寄件人已在网点交寄前完成电子录单。 | “已生成面单” |
| `En tránsito`（`1`） | 正在 A Tu Hora Express 西班牙各网点之间运输。 | “运输中” |
| `En agencia distribución`（`11`） | 已抵达负责你所在邮编的西班牙当地派送网点。 | “运输中” |
| `En reparto`（`2`） / `En realización` | 已交由当地派送员，安排今天派送。 | “派送中” |
| `Incidencia - Pendiente nuevo reparto`（`3`）、`Ausente`、`Dirección incorrecta o incompleta`、`Rechazado` | 派送未能完成（收件人不在、地址错误或不完整、拒收或待重新派送）。 | “派送未成功” |
| `Incidencia - Falta parcial`（`3`）、`Incidencia - No cumplimiento agencia` / `Incumplimiento de la agencia` | 运营异常，例如多件货物缺少部分包裹或网点未能履约派送。 | “需要留意” |
| `Pendiente de realizar`（`5`） | 在同一时间戳与异常记录并列出现，标明等待再次派送；实际阶段由异常记录决定。 | *（保持异常阶段）* |
| `Realizado con éxito`（`4`） / `Entregado con éxito` | A Tu Hora Express 已签收送达。 | “已签收” |
| `Devuelto` | 正在退回寄件人。 | “已退回寄件人” |

## 派送服务与官网公开信息

在 [atuhoraexpress.com/home/](https://atuhoraexpress.com/home/) 和 [atuhoraexpress.com](https://atuhoraexpress.com/) 上，A Tu Hora Express 列出了覆盖其西班牙网点的五项核心服务——*Express 24h*、*Economy 48h*、*Masivo 72h*、*Reverse Logistics* 与 *B2B Premium*——并通过 [atuhoraexpress.com/tracking/](https://atuhoraexpress.com/tracking/) 和 [mydelivery.atuhoraexpress.com](https://mydelivery.atuhoraexpress.com/#/login-form-cli) 提供物流查询。

A Tu Hora Express 官网未公布派送尝试次数、重量或尺寸限制以及保管期限。若你的订单来自欧盟境外，除非商家在结账时已代收，否则货值不超过 €150 时每项商品需缴纳 €3 关税，且每项商品均需缴纳 €2 欧盟处理费；详见[海关滞留包裹指南](guide:customs)（英文）。

## A Tu Hora Express 包裹延误、停滞或遗失

由于与 A Tu Hora Express 签订运输合同的是网店，如果包裹反复停留在 `Incidencia`、出现地址问题（`Dirección incorrecta o incompleta`）或错过预计送达日，请先联系商家处理。

- **反复出现异常或 `Pendiente nuevo reparto`：** 向商家核实你的街道地址、楼层门牌号及联系电话，并附 16 位或 24 位单号与收件邮编发邮件至 `info@atuhoraexpress.com`。
- **显示 `Realizado con éxito` 但未收到：** 请先向同住家人、邻居以及大楼门房（*portería*）确认，随后立即联系商家和 A Tu Hora Express。
- 详细排查步骤请见[物流停止更新的原因](guide:tracking-not-updating)（英文）、[显示已签收却未收到](guide:delivered-not-received)（英文）、[包裹破损处理](guide:damaged-parcel)（英文）、[包裹遗失处理](guide:lost-parcel)（英文）和[包裹被退回寄件人](guide:returned-to-sender)（英文）。

## A Tu Hora Express 客服与联系方式

以下官方联系方式公布于 [atuhoraexpress.com/home/](https://atuhoraexpress.com/home/)、[atuhoraexpress.com/aviso-legal/](https://atuhoraexpress.com/aviso-legal/) 和 [atuhoraexpress.com/ayudanos-a-mejorar/](https://atuhoraexpress.com/ayudanos-a-mejorar/)：

- **电话：** `911 631 356`（西班牙）。
- **客服邮箱：** `info@atuhoraexpress.com`。
- **意见反馈表单：** [Ayúdanos a mejorar](https://atuhoraexpress.com/ayudanos-a-mejorar/)。
- **数据保护邮箱：** `protecciondedatos@atuhoraexpress.com`。
- **马德里总部：** Calle Artesanos 10, 28830 San Fernando de Henares (Madrid), Spain（*A TU HORA EXPRESS, S.L.* 法定注册地址，NIF `B19300748`：C/ Espliego 16, 19208 Alovera, Guadalajara）。

> 请警惕要求你刷卡支付小额费用以重新安排 A Tu Hora Express 派送的短信或 WhatsApp 消息。请直接在 [mydelivery.atuhoraexpress.com](https://mydelivery.atuhoraexpress.com/#/login-form-cli) 查看包裹，或发邮件至 `info@atuhoraexpress.com` 核实。另见[快递诈骗短信识别](guide:delivery-scam-texts)（英文）。

## 使用 Peek 跟踪 A Tu Hora Express 包裹

[Peek](/) 会以高置信度自动将任意以六个 0 开头的 16 位单号（如虚构单号 `0000004829173640`）识别为 A Tu Hora Express。24 位条码（`000000482917364000128013`）符合多家快递公司格式：Peek 会带条码内嵌邮编向 A Tu Hora Express 查询，并同时询问 [Austrian Post](carrier:austrian-post)、[InPost](carrier:inpost) 和 [Ciblex](carrier:ciblex)（若均无记录再询问 Express One），在 A Tu Hora Express 查到记录时显示“A Tu Hora Express 有这个包裹的记录。”对于 10 位单号（`4829173640`），请在添加时点击“更换”或在包裹页点击“更换快递公司”选择 A Tu Hora Express（搜索“A Tu Hora”同样可找到）。

对于 10 位与 16 位单号，Peek 会提供可选的“收件邮编”字段，并提示“A Tu Hora Express 仅在填写收件邮编后才显示包裹。”在填写邮编前，包裹页面会显示“添加邮编”，Peek 会改由物流查询服务跟进，并在满一天无响应后改为每天检查一次；输入邮编后 Peek 会立刻重新查询。24 位条码末五位已自带邮编，因此无需另填。

当 A Tu Hora Express 返回结果时，Peek 会展示对应阶段（“已生成面单”、“运输中”、“派送中”、“派送未成功”、“需要留意”、“已退回寄件人”、“已签收”）、附带城镇名称的西班牙语扫描描述、在途期间对方给出的预计送达日以及包裹重量；状态码仅用于判定阶段，不直接显示。扫描时间按马德里时间解析并换算为你设备的本地时间。扫描附带的 GPS 坐标、进度条、出发日期、客户参考号、收寄件人姓名、收件地址、件数、体积、备注、签收证明及派送选项均会被过滤；A Tu Hora Express 查询系统本身也不展示自提点或服务名称。

当你正在 Peek 中查看该包裹，或派送当天已开启通知时，Peek 最多每 10 分钟查询一次；在 A Tu Hora Express 显示 `En reparto` 或 `En realización` 的当天，只要 A Tu Hora Express 自己的查件系统正常响应，自马德里时间早上 6 点起每 2 分钟查询一次。其余情况下每小时检查一次。若包裹在“已生成面单”或“已揽收”停留超过 12 小时，或连续两天无新动态，无论如何关注均改为每小时一次；在“已生成面单”停留满 7 天则改为每 6 小时一次。当 A Tu Hora Express 自己的查件系统无响应、Peek 仅通过物流查询服务跟踪时，会每小时查询一次，静默一天后每 3 小时一次，派送当天最多每 15 分钟一次。

若最新扫描已过 4 天，且包裹仍处于“已生成面单”、“已揽收”或“运输中”，Peek 会按该扫描日期做出提示，例如“自10月12日周一起没有物流更新”。包裹页面会显示“包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 A Tu Hora Express 查询。”若超过三周没有新扫描，则会提示“已超过三周没有新动态。如果还没收到，请联系卖家。Peek 会持续向 A Tu Hora Express 查询。”

Peek 可以按“每次扫描”、“重要环节”或“仅派送当天”通知你。Peek 仅监控物流状态更新，无法代你预约改派、修改地址或联系 A Tu Hora Express。

## A Tu Hora Express 查询常见问题

### 为什么 A Tu Hora Express 要我输入收件邮编？

无论是 [mydelivery.atuhoraexpress.com](https://mydelivery.atuhoraexpress.com/#/login-form-cli) 还是 Peek，A Tu Hora Express 都必须核对 5 位西班牙收件邮编才显示 10 位或 16 位单号的详情（Peek 中该项虽标为可选，但不填邮编时对方不返回数据）。如果商家给的是 24 位条码，最后五位已包含邮编。

### A Tu Hora Express 显示 `Realizado con éxito` 是什么意思？

`Realizado con éxito`（状态码 `4`）表示 A Tu Hora Express 已将包裹登记为签收送达。如果你尚未收到，请向同住人员、邻居和大楼门房核实，然后联系网店与 `info@atuhoraexpress.com`。

### `Incidencia - Pendiente nuevo reparto` 是什么意思？

表示本次派送未能完成，包裹已排入下一次派送队列（常在同一时间附带 `Pendiente de realizar`）。如果反复出现，请联系商家确认收件地址和电话无误。

### 在西班牙如何联系 A Tu Hora Express？

致电 `911 631 356`、发送邮件至 `info@atuhoraexpress.com`，或在 [Ayúdanos a mejorar](https://atuhoraexpress.com/ayudanos-a-mejorar/) 表单中附上你的运单号和 5 位收件邮编。

:::sources
- [A Tu Hora Express: Home (Spanish)](https://atuhoraexpress.com/) – 西班牙末端派送快递、网点、马德里总部、`911 631 356` 与 `info@atuhoraexpress.com`
- [A Tu Hora Express: Home (English)](https://atuhoraexpress.com/home/) – Express 24h、Economy 48h、Masivo 72h、Reverse Logistics、B2B Premium 与网点列表
- [A Tu Hora Express: Tracking Page](https://atuhoraexpress.com/tracking/) – 官网查件入口
- [A Tu Hora Express: MyDelivery Portal](https://mydelivery.atuhoraexpress.com/#/login-form-cli) – 直接查件门户
- [A Tu Hora Express: Legal Notice](https://atuhoraexpress.com/aviso-legal/) – *A TU HORA EXPRESS, S.L.*、NIF `B19300748`、位于 Alovera（Guadalajara）的注册地址与 `protecciondedatos@atuhoraexpress.com`
- [A Tu Hora Express: Feedback Form](https://atuhoraexpress.com/ayudanos-a-mejorar/) – 客户意见反馈表单
- [Universal Parcel Scraper: a-tu-hora-express](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/a-tu-hora-express) – 10/16/24 位单号识别、可选的 5 位邮编要求、西班牙语状态码（`0`、`1`、`11`、`2`、`3`、`4`、`5`）、`Europe/Madrid` 当地时间、预计送达日、重量与被过滤的字段
:::
