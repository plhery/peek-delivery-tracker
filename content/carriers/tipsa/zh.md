---
title: TIPSA 快递查询：22 位单号、西班牙语状态与派送未成功
description: 查询西班牙和葡萄牙 TIPSA 包裹：22 位运单号结构、西班牙语物流状态中文解读、派送未成功与营业网点、Pickup 自提点及客服联系方式。
slug: tipsa-kuaidi-chaxun
published: 2026-11-24
updated: 2026-11-24
---

TIPSA 是西班牙一家加急包裹快递公司，隶属于 Geopost 集团，在西班牙、葡萄牙和安道尔均设有营业网点。你可以在 TIPSA 的[英文包裹查询页](https://www.tip-sa.com/en/delivery-locator)输入 22 位运单号，或直接粘贴到上方输入框查询。运单号由寄件的商家或个人提供，例如在发货邮件或短信中。如果没有单号，请联系商家，或联系负责你所在地区的 TIPSA 营业网点（可按邮编在 TIPSA [网点查询页](https://www.tip-sa.com/es/agencias-de-transporte)查找）。出现 `Ausente` 无人签收后，你也是直接与该派送网点约定派送或自提；若包裹丢失或破损，请通知商家处理。

## TIPSA 运单号是什么样的？

TIPSA 官网查询框要求输入 22 位运单号，并将收件邮编标为必填项。22 位单号由两个网点代码加运单流水号（*albarán*）组成：

:::anatomy 028145 028145 4829105736
- 028145 | 第一个网点代码：受理该包裹的 TIPSA 营业网点
- 028145 | 第二个网点代码：该包裹流转涉及的另一处网点
- 4829105736 | 运单号（albarán）：包裹本身的编号
:::

| 单号（虚构） | 说明 |
| --- | --- |
| `0281450281454829105736` | 完整的 22 位 TIPSA 运单号，请使用此格式。 |
| `028145 028145 4829105736` | 带空格的同一单号。Peek 可直接识别，但 TIPSA 官网输入框最多只容 22 个字符，输入时需去掉空格。 |
| `4829105736` | 仅 10 位运单流水号。TIPSA 官网查询框不接受这 10 位，Peek 也不会把它当作 TIPSA 单号。 |

商家可开通 TIPSA 通知，在包裹发出、到达网点、出现异常及签收时向你发送邮件和短信，其中的链接会直接打开 TIPSA 包裹详情页。如果没有单号，TIPSA 官网也支持按参考号查询，但需要同时填写包含网点与客户编号的识别码（需向寄件人索取）。更多查找方式见[如何查找运单号](guide:find-tracking-number)（英文）。

22 位数字同样可能属于奥地利邮政（Austrian Post）、[美国邮政（USPS）](carrier:usps) 或 Estafeta。如果两个网点代码均以 `00` 开头，其格式与另一家西班牙快递 [CTT Express](carrier:ctt-express) 相同，请核对商家邮件中写明的承运商。不少人会搜“TIPSA 10 位单号”，但只有完整的 22 位单号才有效，请从发货邮件里找全或向寄件人索要。

## TIPSA 西班牙语物流状态中文解读

即使从英文版官网进入查询，TIPSA 的运单详情页仍只显示西班牙语。页面顶部有一条简短进度条，标示 `Documentado`、`En camino`、`En reparto` 和 `Entregado` 等阶段；下方表格列出每次扫描的日期、时间与城镇。各扫描状态含义如下：

| TIPSA 状态原文 | 中文含义 |
| --- | --- |
| `PENDIENTE DE ENTREGAR A TIPSA` | 商家已登记运单，但 TIPSA 尚未拿到实物，属于[“已生成面单”状态](guide:tracking-statuses)（英文）。 |
| `TRANSITO` | 正于 TIPSA 各网点与分拨中心之间运输。 |
| `LECTURA EN HUB MADRID` | 已在 TIPSA 分拨中心扫描；中心名称会随地点变化（如 `LECTURA EN HUB BARCELONA`、`LECTURA EN HUB BARBERA`）。 |
| `Pendiente de llegada` | 尚未抵达负责最终派送的营业网点。 |
| `LEIDO EN DESTINO` | 已在目的地完成扫描。 |
| `LECTURA EN AGENCIA DESTINO` | 已在派送网点扫描，后接网点名称。 |
| `REPARTO` | 正在派送中。 |
| `Ausente` | 快递员联系不上任何人，因此未能派送。 |
| `Faltan Datos / Datos incorrectos` | 地址信息缺失或有误。在派送途中记录，之后会再次派送。 |
| `Cerrado` | 派送员到达时收件地点已关门。在派送途中记录，出现在 `REPARTO` 之后。 |
| `Solicitada en otra dirección` | 已申请改派到另一地址，包裹正在转派中。 |
| `DISPONIBLE` 或 `Pendiente de recoger en Tipsa` | 派送失败后，包裹存放在网点等你领取。 |
| `Devolver a Origen` | 正在退回寄件人。 |
| `ENTREGADO` | 已签收送达。 |

出现 `Ausente` 时，TIPSA 页面会附带提示：未能联系到你，将于下一个工作日再次派送，也可致电派送网点另约时间。该网点名称与地址会显示在页面的 “AGENCIA DESTINO” 栏下。

## TIPSA 派送失败、营业网点与 Pickup 自提点

TIPSA 通过自有网络覆盖西班牙本土、葡萄牙本土、巴利阿里群岛、加那利群岛、休达和梅利利亚，跨境件则依托 DPDgroup 派送。首次出现 `Ausente` 后，下一个工作日会再次派送；商家支付的标准运费包含两次派送尝试，面向电商的 Premium 服务包含三次。

**网点自提：** 如果你不在家或希望自取，TIPSA 常见问题写明，包裹会存放在离你邮编最近的网点，保管期为**自发件日起 15 天**（并非从到达网点算起），逾期退回寄件人。取件需出示西班牙身份证（DNI）或护照；委托他人代取需携带你的证件复印件。可在 TIPSA [网点查询页](https://www.tip-sa.com/es/agencias-de-transporte)按省或邮编查找网点。

**修改收件地址：** TIPSA 规定只有寄件人才能更改收件地址或收件信息，请联系商家处理。

**Pickup 自提点与快递柜：** TIPSA 与 [SEUR](carrier:seur) 共享 Pickup 自提网络，涵盖商铺与全天候开放的快递柜。多数自提点每周至少营业六天，你有 7 天时间取件。派送到自提点是商家提供的选项；自提点可在 TIPSA [Pickup 自提点查询页](https://aplicaciones.tip-sa.com/puntospickup/)搜索。

部分商家还支持周六派送或预通知（通过短信和邮件告知包裹到达目的地的日期）。使用 Premium 服务还可自选派送时段（前后浮动一小时），但 TIPSA 未说明具体预约入口，请直接咨询派送网点。

**货到付款：** 选用代收货款（*contrarreembolso*）时，TIPSA 在送货上门时收取商家设定的金额，每票最高 1,000 欧元；选用运费到付（*portes debidos*）时，在目的地收取运费。TIPSA 未说明是付现金还是刷卡，请向商家或派送网点确认。这两种费用都在包裹送达当面支付，因此任何自称“来自 TIPSA”要求点击链接在线付款的短信或邮件与这两种方式都不符：切勿付款，应直接在 tip-sa.com 官方查件页核实。另见[防范快递诈骗短信](guide:delivery-scam-texts)（英文）。

## 包裹延误、丢失或退回怎么办？

TIPSA 官网没有针对轨迹长期停滞的专门说明。建议直接致电运单详情页显示的派送网点，或联系商家核查；也可参阅[物流停止更新怎么办](guide:tracking-not-updating)（英文）。

若发生丢失或破损，TIPSA 常见问题说明很简短：请直接通知寄件人。商家与 TIPSA 签订运输合同并负责发起理赔；TIPSA 并未向收件人公布理赔流程或截止期限。如果状态显示 `ENTREGADO` 但实际未收到，官网同样无专页说明，也请通知寄件人。

自发件起 15 天内无人领取的包裹会退回寄件人，请联系商家协商退款或重发。

## TIPSA 客服电话与联系渠道

TIPSA 英文联系页与常见问题列出的官方渠道：

- **客服电话：** 902 10 10 47 或 900 10 10 47（西班牙语常见问题仅列出 900 10 10 47）。TIPSA 未注明服务时间或通话资费。
- **在线联系表单：** 访问 [tip-sa.com/en/contact](https://www.tip-sa.com/en/contact)，选择事由 “I have a problem with my shipment” 并填写运单号。
- **电子邮件：** info@tip-sa.com（常见问题建议在缺失运单号时写信咨询）。
- **当地派送网点：** 查看运单详情页列出的派送网点，用于预约派送。

葡萄牙未设单独热线，TIPSA 葡萄牙联系页列出的是同一组电话号码。TIPSA 官网仍保留应用介绍页，但其在应用商店的上架页面已无法打开。

## 用 Peek 查询和跟踪 TIPSA 包裹

[Peek](/) 接受带空格或不带空格的 22 位单号。由于多家快递共用 22 位数字格式，Peek 会同时询问几家可能匹配的快递公司并优先询问 TIPSA；界面先显示“正在询问…”，如果 TIPSA 有这个单号的记录，随后显示“TIPSA 有这个包裹的记录。”若两个网点代码均以 `00` 开头且第二个代码在 CTT Express 已知网点列表中，Peek 会直接识别为 CTT Express；其余两个代码均以 `00` 开头的单号，Peek 会同时询问 TIPSA、CTT Express 和奥地利邮政，且优先询问 TIPSA。如果商家已说明是 TIPSA，可点击“更换”手动选定，无需填写邮编。

10 位运单流水号无法通过 TIPSA 查询，即便点击“更换”或从链接导入也不行。Peek 不会为 10 位数字推荐或询问 TIPSA；若手动选择 TIPSA，Peek 会提示 TIPSA“不使用这种格式的单号”，并改用物流查询服务。请在商家发货邮件或短信中查找完整的 22 位单号或查询链接，或联系商家。商家 TIPSA 邮件中的链接可以直接粘贴：Peek 能读取 tip-sa.com 上带 `id=` 参数的链接，适用于以 `datos_env.php`、`datos_prestashop.php` 或 `datos.php` 结尾的页面以及 tip-sa.com 根地址，但不会读取 TIPSA 官网查询页链接或 tipsa-dinapaq.com 的详情页链接。

Peek 会显示包裹状态、带各扫描城镇位置的地图轨迹、寄件人名称（除非 TIPSA 做了隐藏处理）以及重量。状态原文保留 TIPSA 的西班牙语文本，同时 Peek 会将其归纳为对应的中文阶段：`PENDIENTE DE ENTREGAR A TIPSA` 显示为“已生成面单”；`REPARTO` 显示为“派送中”；`Ausente`、`Cerrado` 和 `Faltan Datos / Datos incorrectos` 显示为“派送未成功”；`DISPONIBLE` 和 `Pendiente de recoger en Tipsa` 显示为“待取件”；`Devolver a Origen` 显示为“需要留意”；`ENTREGADO` 显示为“已签收”；其余扫描显示为“运输中”。Peek 将所有扫描时间（包括葡萄牙网点）统一按马德里时间解析，并换算为你设备的本地时区；在 peektracker.com 及 iPhone、Android 应用中，物流历史底部会注明“时间按你所在的时区显示：”及你的时区名称。Peek 会隐去收件人、地址、邮编、参考号、件数、体积和签收凭证，不显示预计送达日期，也不会跨接追踪其他快递公司。在你打开包裹页面时，或在派送当天开启了通知时，只要 TIPSA 自己的查询系统正常响应，Peek 最高每 10 分钟检查一次，并在 TIPSA 扫描进入派送的当天自马德里时间早上 6 点起最高每 2 分钟检查一次；其余情况每小时检查一次。若只有物流查询服务响应，Peek 每小时检查一次，最新扫描满一天后改为每 3 小时一次，在派送当天打开页面或开启提醒时最高每 15 分钟检查一次。状态变动时可推送通知。若连续两天无新动态（或在“已生成面单”、“已揽收”状态下满 12 小时），Peek 会改为每小时检查一次。

## TIPSA 查询常见问题

### 西班牙的 TIPSA 是什么快递？

TIPSA 全称 Transporte Integral de Paquetería，是 Geopost 集团旗下的西班牙加急快递公司。其关于页面称拥有 330 多个网点（葡萄牙首页称 300 多个），设有包括马德里、巴塞罗那和瓦伦西亚在内的 13 个分拨中心，跨境件依托 DPDgroup 派送。

### TIPSA 派送需要多久？

取决于商家购买的服务。TIPSA 面向商家提供 24 小时送达（次个工作日尝试派送一次）或 48 小时内送达服务；葡萄牙主要城镇为 24 小时，其余地区为 24 至 48 小时。

### 只用 10 位数字能查 TIPSA 包裹吗？

不能。TIPSA 官网查询页要求完整 22 位运单号，Peek 也不会把 10 位数字当作 TIPSA 单号。这 10 位只是末尾的流水号，如果手头只有这 10 位，请向寄件人索取完整的 22 位单号。

### TIPSA 有自提点吗？

有。TIPSA 与 SEUR 共享商铺 Pickup 自提点和 24 小时快递柜，包裹可保管 7 天，但前提是商家下单时支持配送至自提点。

:::sources
- [TIPSA: Delivery locator](https://www.tip-sa.com/en/delivery-locator) – 22 位运单号、查件表单与按参考号查询
- [TIPSA: Frequently Asked Questions](https://www.tip-sa.com/en/recursos/faqs) – 缺单号处理、15 天保管期、证件要求、改址、丢失破损、电话与邮箱
- [TIPSA: Contact](https://www.tip-sa.com/en/contact) – 客服电话与表单事由
- [TIPSA: Collection points](https://www.tip-sa.com/en/services/collection-points) – Pickup 自提点网络
- [TIPSA: Pre-alert](https://www.tip-sa.com/en/services/pre-alert) – 短信与邮件预通知、葡萄牙派送时效
- [TIPSA: TIPSA APP](https://www.tip-sa.com/en/recursos/app) – 应用介绍页
- [TIPSA: Consulta de Envíos](https://dinapaqweb.tipsa-dinapaq.com/dinapaqweb/detalle_envio.php) – 西班牙语运单详情页、状态措辞与派送网点信息
- [TIPSA: Localizar mi envío](https://www.tip-sa.com/es/localizacion-envios) – 西班牙语查件页与页脚电话
- [TIPSA: Preguntas frecuentes](https://www.tip-sa.com/es/recursos/faqs) – 覆盖区域与 900 电话
- [TIPSA: Empresa de transporte urgente](https://www.tip-sa.com/es/tipsa) – Geopost 背景、330 多个网点、分拨中心与快递柜
- [TIPSA: Página inicial (Portugal)](https://www.tip-sa.com/pt) – 300 多个网点说明
- [TIPSA: FAQs eTIPSA](https://www.tip-sa.com/es/faqs-etipsa) – 两次派送、24/48 小时时效、eTIPSA Premium 时段、周六派送与商家通知
- [TIPSA: 360º eCommerce](https://www.tip-sa.com/es/envios-de-paquetes/360o-ecommerce) – Premium 三次派送
- [TIPSA: Recogida en puntos de recogida](https://www.tip-sa.com/es/envios-de-paquetes/puntos-de-recogidas) – 与 SEUR 共享自提点、7 天保管期与营业天数
- [TIPSA: Agencias de transporte](https://www.tip-sa.com/es/agencias-de-transporte) – 营业网点查询页
- [TIPSA: Puntos Pickup](https://aplicaciones.tip-sa.com/puntospickup/) – Pickup 自提点查询页
- [TIPSA: Contrarreembolso](https://www.tip-sa.com/es/envios-de-paquetes/reembolso) – 货到付款与每票 1,000 欧元上限
- [TIPSA: Portes debidos](https://www.tip-sa.com/es/envios-de-paquetes/portes-debidos) – 运费到付说明
- [TIPSA: Aviso legal](https://www.tip-sa.com/es/tipsa-aviso-legal) – 公司全称信息
- [TIPSA: Contacto (Portugal)](https://www.tip-sa.com/pt/contacto) – 葡萄牙共用联系电话
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Peek 读取的信息、`Cerrado`、各状态对应的阶段
:::
