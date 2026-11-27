---
title: Andreani 物流查询：阿根廷运单号与状态说明
description: 查询阿根廷 Andreani 快递包裹：15 位运单号形态、西班牙语物流状态中文解释、停更或丢失后的处理方式及 Peek 跟踪方法。
slug: andreani-wuliu-chaxun
published: 2026-11-26
updated: 2026-11-26
---

Andreani 是一家阿根廷快递公司。Peek 目录在阿根廷和巴西收录了它，也以 Correo Andreani 和 Grupo Logístico Andreani 的名称收录。网购订单可能由 Andreani 承运：运单号在商家的发货邮件或订单页里。将单号或 andreani.com 查询链接粘贴到上方输入框即可。Andreani 自己的查询页在 andreani.com，Peek 读取的就是它，本页为你逐条解释各项状态。

## Andreani 运单号长什么样

Peek 识别的 Andreani 单号为 15 位数字，以 36000、31000 或 30000 开头。以下数字为虚构：

| 单号（虚构） | 含义 |
| --- | --- |
| `360004829173654` | 36000 加 10 位数字：属于 Andreani 号段，但仅为弱匹配。[BRT](carrier:brt) 和韵达（Yunda）也使用 15 位数字，因此 Peek 会同时询问 Andreani 和 BRT。 |
| `310004829173654` | 31000 加 10 位数字：同样属于 Andreani 号段。 |
| `300004829173654` | 30000 加 10 位数字：同样属于 Andreani 号段。 |
| `007394028156348` | 其他开头的 15 位数字。Peek 不会把它关联到 Andreani。[FedEx](carrier:fedex)、[DPD France](carrier:dpd-fr)、[Hermes](carrier:hermes-de)、BRT 和韵达也使用 15 位数字。 |

粘贴到 Peek 时空格不影响识别。

**其他参考号。** Andreani 查询系统也接受带字母的 Andreani 单号和寄件人自有参考号。Peek 无法仅凭形态判断它们属于 Andreani，请手动选择 Andreani（见下方“用 Peek 跟踪 Andreani 包裹”）。

**查询链接。** 包含 `/envio/` 和单号的 andreani.com 链接同样可用，Peek 会从链接里读取单号。

找不到单号？查看[如何查找运单号](guide:find-tracking-number)（英文）。

## Andreani 物流状态含义

Andreani 查询系统用西班牙语标题为记录分组。Peek 已知的四组是 `Pendiente de ingreso`（待入网）、`Ingresado`（已入网）、`En camino`（运输中）和 `Entregado`（已签收）。每条记录是一句面向收件人的短句，必要时标明网点（`sucursal`）和城镇。Peek 认识的一段常规行程中的记录，按包裹经历的顺序如下：

| Andreani 显示 | 对你的含义 | Peek 状态 |
| --- | --- | --- |
| `Tu envío se encuentra pendiente de ingreso.` | 寄件人已向 Andreani 预报包裹，但 Andreani 尚未收到实物。 | “已生成面单” |
| `¡Ya tenemos tu envío! El circuito comienza en C.a.b.a., Buenos Aires. Pronto lo enviaremos a la sucursal encargada de la entrega.` | Andreani 已收到你的包裹，行程从所写城镇和省份开始，随后发往负责派送的网点。 | “已揽收” |
| `Tu envío salió de la sucursal Monserrat (av Belgrano), en la localidad C.a.b.a. de la provincia Buenos Aires.` | 包裹已离开所写城镇和省份的该网点。 | “运输中” |
| `Tu envío se encuentra en camino a la sucursal La Plata (calle 2).` | 正在发往所写网点，该网点负责最终派送。 | “运输中” |
| `La sucursal La Plata (calle 2) ya está procesando tu envío. ¡Pronto tendrás novedades!` | 派送网点已收到包裹并正在处理，很快会有新动态。 | “运输中” |
| `Hoy te visitaremos para entregarte tu envío.` | Andreani 今天会上门派送你的包裹。 | “派送中” |
| `Ya entregamos tu envío. ¡Esperamos volver a verte pronto!` | 已送达。 | “已签收” |

表中的网点与城镇仅为示例，你的记录会显示实际途经的网点。`C.a.b.a.` 指布宜诺斯艾利斯自治市（Ciudad Autónoma de Buenos Aires）。

Peek 会保留 Andreani 的西班牙语原文，并将其归入 Peek 的状态阶段。Peek 尚未单独映射的句子会沿用其所在分组的阶段：例如 `En camino` 下的任意记录均显示为“运输中”。Peek 会去掉记录中的电话号码和身份证件号。更多见[物流状态含义](guide:tracking-statuses)（英文）。

## Andreani 在阿根廷如何派送

上面的记录展示了常规派送流程：

1. **揽收。** Andreani 在始发地接收包裹。
2. **发往派送网点。** 将包裹运往负责派送的网点（`sucursal encargada de la entrega`）。以 `Tu envío se encuentra en camino a la sucursal` 开头的记录会写明该网点。
3. **抵达派送网点。** 出现该网点 `ya está procesando tu envío`（正在处理你的包裹）时，说明包裹已到该网点。
4. **派送当天。** `Hoy te visitaremos para entregarte tu envío.` 表示当天有人送货上门。
5. **签收。** `Ya entregamos tu envío.` 标志行程完成。

由于 Peek 无法读取 Andreani 网站说明页，本页不列出派送时效、派送未成功的规则，也不涉及网点自提和保管天数。相关问题请询问商家或 Andreani。另见[派送未成功](guide:missed-delivery)（英文）和[未领取的包裹](guide:uncollected-parcel)（英文）。

## Andreani 物流停更、丢失或退回

**只有 `Pendiente de ingreso`。** 寄件人已创建运单但尚未把包裹交给 Andreani，Peek 显示“已生成面单”。请问商家是否已经寄出。

**完全查不到记录。** Andreani 查询系统可能尚未录入单号。归入 Andreani 后页面显示“等待 Andreani 更新”，Peek 会继续查询。两天后 Andreani 仍查不到该单号时，Peek 会提示你检查单号、更换快递公司或删除包裹：请向商家核对。详见[物流为什么停止更新](guide:tracking-not-updating)（英文）。

**多天没有动静。** 如果最新记录距今满四天及以上，且包裹仍处于“已生成面单”、“已揽收”或“运输中”，Peek 会标出那条记录的日期：“自10月12日周一起没有物流更新”，附带提示“包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 Andreani 查询。”以及“向 Andreani 寻求帮助”链接。如仍无变化，请联系商家。超过三周没有新记录时，Peek 也会提示：“已超过三周没有新动态。如果还没收到，请联系卖家。”

**显示已签收但没收到。** 最后一条是 `Ya entregamos tu envío. ¡Esperamos volver a verte pronto!`，但你没有收到包裹。先询问同地址人员、邻居和大楼工作人员，再联系商家。参见[显示已签收但未收到](guide:delivered-not-received)（英文）。

**丢失或破损。** 请先联系商家：是商家向 Andreani 下单寄件的。详见[包裹丢失](guide:lost-parcel)（英文）和[包裹破损](guide:damaged-parcel)（英文）。

**退回寄件人。** Peek 的 Andreani 状态表暂未收录专门的退件文案。如果包裹退回寄件人，记录仍会以 Andreani 原文展示。请联系商家协商退款或补发。参见[退回寄件人](guide:returned-to-sender)（英文）。

**商家不配合处理。** 如果你购物的公司未履行承诺，可以向阿根廷消费者保护机构 Defensa del Consumidor 投诉。投诉免费且可在线通过 Ventanilla Federal Única de Defensa del Consumidor 提交，需准备你的 DNI 或护照、用于接收回复的电话或邮箱、商家名称和所在城镇，以及基本购买信息。Defensa del Consumidor 会把投诉转交主管办公室，并通过邮件发给你投诉编号。

## Andreani 客服与联系方式

由于 Peek 无法读取 Andreani 网站，本页不列电话、邮箱或营业时间：Peek 仅提供从官方页面核实过的联系方式。Andreani 网站为 andreani.com。

网购订单请先联系商家：商家负责订购配送并掌握运单详情。

## 用 Peek 跟踪 Andreani 包裹

仅看 15 位数字，[Peek](/) 无法确定包裹属于 Andreani。仍然可以粘贴：Peek 跟踪 4,500 多家快递公司。

**以 36000、31000 或 30000 开头的单号。** Peek 会同时询问 Andreani 和 BRT（只有两家都查不到时才再查物流查询服务），期间显示“正在查询物流服务…”。如果 Andreani 查到了包裹，单号下方会显示 Andreani 且注明“查到了这个包裹”，Peek 会将其归入 Andreani。如果尚未查到，则显示“暂未找到 · Peek 会继续查询”。照样可以添加：包裹会显示“可能是 Andreani”和“等待 Andreani 的首次扫描”，在 iPhone/Android 应用或登录账号中，前几小时之后 Peek 会每 6 小时继续查询 Andreani。未登录账号的查询链接，Peek 会在最初 6 小时内查询，之后在你打开链接时查（最多每 6 小时一次，前两天后最多每天一次）；若该链接开启了通知，则在查询后 30 天内每天查一次。一周后仍无记录时，会提示你向寄件人核对单号或选择其他快递公司。

**andreani.com 链接。** 粘贴带 `/envio/` 的查询链接，Peek 会立刻识别为 Andreani（“识别出的快递公司”）并跟进链接里的单号，含字母的参考号也适用。

**其他任意单号。** 手动指定 Andreani：点“更换”，在“所有快递公司”中找到或直接搜索 Andreani。以 36000、31000 或 30000 开头的单号也会出现在“符合这个单号”下。已添加的包裹请用“更换快递公司”。Peek 向 Andreani 查询时接受 6 至 30 位且至少含一位数字的字母数字组合；不符合该条件时，单号下方会提示 Andreani “不使用这种格式的单号”。

**Peek 显示哪些内容。** Peek 读取 Andreani 自有查询系统，与 Andreani 查询页发起的请求一致。每条记录会显示 Andreani 的西班牙语原文、对应阶段（见上表）、网点与时间。Andreani 的时间均为阿根廷时间（全国统一时区且无夏令时），Peek 会换算成你设备的时区显示。Andreani 查询页还会显示寄件人姓名、司机姓名、备注、地图位置、进度条和送达预估，Peek 会略去这些内容，因此不显示预计送达日期。

**Andreani 无响应时。** Peek 会改问物流查询服务，并按服务方的原文展示记录。Peek 未收录 Andreani 向其他快递交接的规则，因此会一直按 Andreani 跟进到底。

**刷新频率与通知。** 在 Andreani 自己的查件系统正常响应期间，当你打开包裹页，或在派送当天开启通知时，Peek 最多每 10 分钟查询一次，在 Andreani 扫描派送当天的阿根廷时间早上 6 点起每 2 分钟查询一次。连续两天无新动态，或在“已生成面单”/“已揽收”状态下满 12 小时后改为每小时查询一次；在“已生成面单”下连续七天无动态后改为每 6 小时一次。你可以选择接收“每次扫描”、“重要环节”或“仅派送当天”提醒。Peek 无法替你联系 Andreani 或商家。

## 关于 Andreani 物流查询的常见问题

### Andreani 运单号长什么样？

是以 36000、31000 或 30000 开头的 15 位数字，例如 `360004829173654`。Andreani 查询系统也接受其他参考号。可直接把单号或 andreani.com 查询链接粘贴到 Peek 中。

### Andreani 物流里的“Pendiente de ingreso”是什么意思？

意思是待入网：寄件人已向 Andreani 预报包裹，但 Andreani 还没拿到实物。Peek 会显示“已生成面单”。如果多天不变，请问商家是否已寄出。

### “Hoy te visitaremos para entregarte tu envío.”是什么意思？

表示 Andreani 今天会上门送货。Peek 会显示“派送中”，并在你打开页面或开启通知时提高查询频率，直至签收记录出现。

### 为什么 Andreani 物流不更新？

可能是商家还没交寄包裹，或是 Andreani 暂无新扫描（周末很常见）。Peek 会持续查询 Andreani，并在四天无新动态时做出标记；届时请联系商家。

### 怎么看懂 Andreani 的西班牙语物流？

把单号粘贴到 Peek 即可。Andreani 原文保留西班牙语，但 Peek 会为每条记录标上中文阶段，同时上方表格也解释了这些句子。

:::sources
- [Universal Parcel Scraper: Andreani](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/andreani) – 名称与国家、15 位形态、其他参考号、andreani.com 链接、查询请求、西班牙语记录与分组及其阶段、隐藏电话与证件号、阿根廷时区、其网站展示与 Peek 省略内容、无预计送达日
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – 其他 15 位单号承运商、Peek 询问顺序、物流查询服务、Andreani 无交接规则
- [Argentina.gob.ar: Iniciar un reclamo ante Defensa del Consumidor](https://www.argentina.gob.ar/servicio/iniciar-un-reclamo-ante-defensa-del-consumidor) – 针对未履约企业的投诉、Ventanilla Federal Única、免费在线办理、所需资料与邮件投诉编号
:::
