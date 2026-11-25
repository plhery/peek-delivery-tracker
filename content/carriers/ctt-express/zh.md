---
title: CTT Express 西班牙物流查询：单号、状态与客服
description: 查询西班牙与葡萄牙 CTT Express 包裹：22 位单号格式、西语状态中文说明、Collectt 自提点、派送未成功与客服联系方式。
slug: ctt-express-tracking
published: 2026-11-24
updated: 2026-11-24
---

CTT Express 是葡萄牙 CTT 集团旗下的西班牙包裹快递公司（前身为 Tourline Express），服务覆盖西班牙和葡萄牙全境。你可以在 cttexpress.com 的 [localizador](https://www.cttexpress.com/localizador-de-envios/) 输入单号查询，或直接把单号粘贴到上方输入框中。单号由寄件人（通常是商家）提供，CTT Express 也会通过短信或邮件发送给你。CTT Express 并非葡萄牙邮政 CTT：带有字母且以 `PT` 结尾的单号属于 CTT。

## CTT Express 运单号格式

CTT Express 查询页面要求输入 *código de envío*（发货代码），通常是一长串纯数字。CTT Express 说明可在其短信或确认邮件中找到。Peek 快递目录识别的格式如下（示例均为虚构）：

| 单号 | 归属承运商 |
| --- | --- |
| `0048290082904829173605` | 22 位数字：`00`、4 位数字、`00` 再加 14 位数字，属于 CTT Express。 |
| `0048290082904829173605001` | 上述 22 位数字加包裹序号（首件为 `001`）：25 位数字，属于 CTT Express。 |
| `0000100048291736058419` | 以 `00001000` 开头的 22 位数字：既可能是 CTT Express，也可能是 TIPSA、奥地利邮政等。 |
| `RR482917367PT` | 2 位字母、9 位数字加 `PT`：葡萄牙邮政 CTT 的邮政单号，在 ctt.pt 上查询。 |

**CTT 还是 CTT Express？** 同属一家集团，但分属两家公司：CTT（Correios de Portugal）是承担普遍邮政服务的葡萄牙邮政，而 CTT Express 是集团在西班牙的特快包裹公司；集团在葡萄牙的特快业务叫 CTT Expresso，使用 CTT 的字母单号（如 `DA`、`EA` 等）。两者的查询系统有所互通：CTT Express 葡萄牙站会引导至 ctt.pt 的“Seguir objeto”（同样支持 22 位和 25 位数字），而 CTT Express 查询页面也将 `PT` 单号列为两个示例之一。如果一个网站查不到，可试着在另一个查询。

完全找不到单号？请联系商家，或参阅[如何查找运单号](guide:find-tracking-number)（英文）。

## CTT Express 物流状态中文说明

官网不提供英文追踪。cttexpress.com 的英文切换仍显示西班牙语页面，查询页仅提供西班牙语，顶部显示四个阶段（`Grabado`、`Enviado`、`En reparto` 以及带日期或 `Sin determinar` 的 `Entrega prevista`），下方为每条扫描记录的原文。

| 状态原文 | 对你的含义 |
| --- | --- |
| `Pendiente de recepción en CTT Express` | 寄件人已登记预报，包裹尚未进入 CTT Express 网络。 |
| `Recogido` | 已向寄件人完成揽收。 |
| `Enviado` 或 `En tránsito` | 正在物流网络中向你转运。 |
| `En tránsito internacional. Pendiente de recepción en CTT Express` | 跨境发来途中，CTT Express 尚未收到包裹。 |
| `En reparto` 或 `Entrega hoy` | 已分配派送路线，今天外出派送。 |
| `Incidencia en el reparto` 或 `Reparto fallido` | 派送受阻未成功，具体原因显示在该条下方。 |
| `Recogerán en Delegación` | 字面意为“将在网点领取”：在葡萄牙由 CTT 派送的包裹发生派送失败并等待你自提。CTT 官网会同时显示派送失败，随后显示待领取。 |
| `Almacenado temporalmente` | “临时存放”：派送失败后退回网点暂存，等待新一轮派送或退回。 |
| `Nuevo reparto` | “重新派送”：再次装车安排派送。 |
| `Disponible en Punto Collectt Express` | 已到达 Collectt 合作自提点待领，取件 PIN 码通过邮件和短信发送。 |
| `El envío fue entregado correctamente` | 已签收。 |
| `En devolución` | 正在退回寄件人途中。 |
| `Devuelto a remitente` | 已退回给寄件人。 |

最容易让人担心的状态是 `Pendiente de recepción en CTT Express`，它仅表示面单已创建。CTT Express 说明，对于从西班牙或葡萄牙寄出的包裹，如果预约揽收超过 24 小时仍停留在这一状态，可使用其联系表单中针对包裹尚未入网的选项。

派送异常下方会列出具体原因，例如 `No pudimos contactar contigo`（无法联系到你）或 `Dirección incorrecta`（地址错误）。CTT Express 常见问题提醒，即使你在家，只要司机无法到达你家门口，系统也可能记录为“收件人不在”。

**海外跨境订单：** CTT Express 说明，境外购买的包裹需要时间在发件国交寄并运往西班牙或葡萄牙，因此 `En tránsito` 可能持续一段时间。对于从欧盟以外（如中国）发货的商家，CTT Express 也提供清关服务（据其称平均耗时 12 小时），随后通过自有网络在 24 或 48 小时内派送。如果海关扣检包裹，物流可能会提示或向你发通知，且放行前可能需缴纳税费或关税。自 2026 年 7 月 1 日起，货值 150 欧元及以下的欧盟外网购订单按商品类别每类缴纳 3 欧元关税；自 2026 年 11 月 1 日起，无论货值多少，还需按商品类别每类缴纳 2 欧元欧盟处理费。由谁收取、何时收取，见[清关中的包裹指南](guide:customs)（英文）。

## CTT Express 派送时间、Collectt 自提点与派送未成功

在西班牙，CTT Express 周一至周五派送，一般为 9:00 至 19:00；具体时段因服务和地区而异，仅圣诞季提供周六派送。司机无法提前通知上门，仅在你不在家或地址难找时致电。包裹签收后 CTT Express 会向你发送邮件。

CTT Express 提供两次派送尝试，第二次在下一个工作日进行。派送失败后你会收到通知及链接，可另选日期、更改地址、交给邻居或改投 Collectt Express 自提点。

你也可以凭完整发货代码、收件邮编以及寄件人登记的手机号或邮箱，在 localizador 自行更改派送方式，包括修改地址、另选日期、改投 Collectt 自提点、选择上午或下午或交给邻居。修改仅在进入 `En reparto` 前有效，并可能顺延 24 小时。同一派送区域内改地址或改投 Collectt 自提点均免费。

**Collectt Express 自提点：** CTT Express 在西班牙和葡萄牙合计拥有超过 20,000 个自提点和 Locky 自提柜。门店保管 10 天，自提柜保管 5 天；凭邮件和/或短信收到的 PIN 码免费取件，逾期按该票运单条件处理。如遇自提柜无法打开，请拨打柜机上或物流邮件中的电话。

**在葡萄牙：** 适用 CTT 帮助页面规则——当地 Collectt 网点包括 Lojas CTT 邮局、Pontos CTT、Payshop 合作商户和 Locky 自提柜。若错过派送，视寄件人指示，包裹可能转存自提点、再次尝试派送或退回。来自西班牙的 Expresso 包裹在网点保管 10 个工作日，Locky 自提柜保管 5 天。

## CTT Express 包裹延误、丢失或破损

如需退款或补发，请联系发货商家。

- **延误：** 超过预计送达时间即算延误；周末、节假日、恶劣天气及圣诞高峰期会顺延天数。见[物流不更新怎么办](guide:tracking-not-updating)（英文）。
- **显示已签收但未收到：** 先核查家人或物业门房是否代收（扫描可能会写 `El envío fue entregado en conserjería` 或 `El envío fue entregado a vecino según las instrucciones indicadas.`），再使用联系表单中针对已显示签收但未收到包裹的选项申报。另见[显示已签收但未收到](guide:delivered-not-received)（英文）。
- **破损：** 拆包前先拍照，保留外箱与面单，并在 7 个自然日内通过表单申报，写明是外箱、内件还是两者均受损。CTT Express 会立案并告知处理方。
- **已取消：** 如果物流显示运单已取消，CTT Express 建议联系商家重新激活。
- **葡萄牙：** 按 CTT Expresso 条款，理赔由订购运输的客户提出：破损须在签收后 8 天内，丢失或延误须在 30 天内。

## CTT Express 客服电话与联系方式

CTT Express 与 CTT 官网列出的客服渠道如下（均无英语服务）：

- **西班牙：** 900 841 352（免费，载于帮助页面；其 2026 年 10 月 27 日的海关指南文章则给出 900 110 898）或 916 602 200（境外拨打 +34 916 602 200），周一至周五 9:00 至 19:00（节假日除外）。邮箱 ccaweb@cttexpress.com，或使用[在线联系表单](https://www.cttexpress.com/contacto-atc)。客服中心位于马德里，仅支持电话和邮件，通常在 24 个工作小时内答复，不设在线聊天或 WhatsApp。
- **葡萄牙：** CTT 热线 21 047 16 16（境外拨打 +351 21 047 16 16），工作日 8:30 至 19:30，及 ctt.pt/ajuda 表单。

> CTT Express 声明绝不会要求你拨打 807 高费率电话，并提醒警惕假借“待派包裹”通过链接索要额外费用的诈骗短信和邮件。在葡萄牙，CTT 也通报过冒充“CTT Expresso”催缴关税的诈骗短信；CTT 真实链接均以 www.ctt.pt 开头，请仅在官网查件。

## 使用 Peek 查询 CTT Express 包裹

粘贴上表前两种格式的单号（含以 `00001000` 开头的 25 位形式），[Peek](/) 会直接识别为 CTT Express。对于以 `00001000` 开头的 22 位数字，Peek 会同时询问 TIPSA、奥地利邮政和 CTT Express；若无法确定，可点击“更换”手动选择。CTT Express 查询页面的链接同样可直接粘贴。单独输入以 `PT` 结尾的单号则会归入 CTT。

Peek 最高每 10 分钟检查一次，在 CTT Express 扫描包裹进入派送中的当天早上 6 点（当地时间）起最高每 2 分钟检查一次，只要 CTT Express 官方查询正常响应（通过物流查询服务时，在派送当天打开页面或开启提醒时最高每 15 分钟检查一次），并可按每次扫描、重要环节或仅派送当天推送通知。若连续两天无新动态（包裹仍处于“已生成面单”或“已揽收”状态时为 12 小时），Peek 会转为每小时检查一次。当其他承运商接手派送时，Peek 会遵循实际派送承运商的频率。页面展示当前状态、物流历史（由于 CTT Express 不返回地点，历史不含地点）、在途期间的预计送达日、重量、签收时间，以及他国邮政转交时的国际单号。当该单号为 `PT` 结尾的 CTT 单号时，表示在葡萄牙由 CTT 派送：卡片会显示“由 CTT Portugal 派送”及单号（当 CTT 仅录入预报尚未扫描时显示“CTT Portugal 已收到预报，尚未收到包裹”），待 CTT 返回不晚于 CTT Express 最新记录的扫描后，Peek 改在 CTT 跟踪该包裹，并保留 CTT Express 的历史。所有时间按你所在的时区显示；在 peektracker.com 及 iPhone、Android 应用中，历史底部会注明“时间按你所在的时区显示：”及具体时区名称。扫描文本保持 CTT Express 西班牙语原文（如 `En reparto`）。包裹送达 Collectt 自提点时会显示 `Disponible en Punto Collectt Express`，但不显示门店名称或地址。一票多件货件通过通用物流查询服务追踪。

## CTT Express 物流查询常见问题

### CTT Express 是什么公司？

它是葡萄牙 CTT 集团旗下的西班牙特快包裹子公司，2019 年由 Tourline Express 更名而来。它与葡萄牙邮政 CTT 虽同属一个集团但分属两家公司，派送范围覆盖西班牙（含巴利阿里群岛、加那利群岛、休达和梅利利亚）及葡萄牙。

### CTT Express 周六派送吗？

在西班牙仅在圣诞旺季提供周六派送，平时为周一至周五派送。

### 可以用英文查询 CTT Express 吗？

不行。其官方查询仅提供西班牙语，可对照上表查看常见状态含义。

### CTT Express 有在线聊天客服吗？

官网不提供在线聊天或 WhatsApp，仅支持电话、邮件和在线联系表单。

### 没有运单号可以查 CTT Express 吗？

不能。CTT Express 查询页只设有一个运单号输入框，联系表单同样要求提供单号。请在短信或确认邮件中查找，或向发货商家索取。

:::sources
- [CTT Express: Localizador de envíos](https://www.cttexpress.com/localizador-de-envios/) – 查询入口、更改派送与送达邮件
- [CTT Express: Encontre a sua encomenda no localizador de envios](https://www.cttexpress.com/localizador-de-envios/?language_code=pt) – 葡萄牙通过 ctt.pt 的 Seguir objeto 查询
- [CTT Express: Seguimiento de envíos](https://shipping-tracking.production.cloud2.cttexpress.com/) – 纯西语追踪、阶段、扫描原文、异常原因、单输入框及 `PT` 单号示例
- [CTT Express: Empresa de Paquetería y Mensajería](https://www.cttexpress.com/) – 服务范围、Collectt 网点与语言切换
- [CTT Express: Quiénes somos](https://www.cttexpress.com/nosotros/quienes-somos) – CTT 集团、Tourline 与 2019 年更名
- [CTT Express: FAQs sobre los estados de envío](https://www.cttexpress.com/preguntas-frecuentes/estados-envio) – 状态说明、已签收未收到与破损申报
- [CTT Express: Preguntas frecuentes de incidencias](https://www.cttexpress.com/preguntas-frecuentes/incidencias) – 两次派送尝试、延误与理赔
- [CTT Express: Horarios de reparto](https://www.cttexpress.com/preguntas-frecuentes/horarios) – 派送时段、周六规则与司机来电
- [CTT Express: Cambiar dirección de entrega](https://www.cttexpress.com/cambiar-direccion-entrega/) – 单号位置与改地址规则
- [CTT Express: Recoge y devuelve tus paquetes en nuestros puntos](https://www.cttexpress.com/red-collectt-express/envio-y-recogida) – Collectt 自提点、10 天与 5 天保管期及 PIN 码
- [CTT Express: Preguntas frecuentes Puntos Collectt Express](https://www.cttexpress.com/preguntas-frecuentes/puntos-collectt-express) – PIN 码与免费改投自提点
- [CTT Express: Qué significa "aceptado por la última milla"](https://www.cttexpress.com/blog/logistica/que-significa-aceptado-ultima-milla) – 末端状态含义
- [CTT Express: Nota de prensa sobre envíos](https://www.cttexpress.com/zona-de-prensa/notas-de-prensa/como-localizo-mi-paquete-con-ctt-express-y-otras-preguntas-frecuentes-sobre-envios) – 跨境运输时效说明
- [CTT Express: Servicio de importación de paquetería para empresas](https://www.cttexpress.com/servicios/importacion/) – 欧盟外进口清关平均 12 小时及 24/48 小时派送
- [CTT Express: ¿Qué hacer si mi paquete ha quedado retenido en aduanas?](https://www.cttexpress.com/blog/expansion-internacional/que-hacer-si-mi-paquete-ha-quedado-retenido-en-aduanas) – 海关滞留、通知、税费及 900 110 898 热线
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 自 2026 年 7 月 1 日起 150 欧元及以下按类征收 3 欧元关税、自 2026 年 11 月 1 日起征收 2 欧元欧盟处理费
- [European Commission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 按商品类别计收 2 欧元说明
- [CTT Express: Cómo contactar con CTT Express](https://www.cttexpress.com/preguntas-frecuentes/como-contactar-con-ctt) – 电话、服务时间、邮箱与 807 警示
- [CTT Express: Contacto y Atención al Cliente](https://www.cttexpress.com/contacto-atc) – 需填写运单号的在线联系表单
- [CTT Express: Qué es Phishing](https://www.cttexpress.com/blog/actualidad/como-evitar-phishing) – 防范虚假短信与邮件
- [CTT: Encontrar o código de envio](https://www.ctt.pt/ajuda/particulares/seguir-ou-alterar-entrega/seguir/encontrar-o-codigo-de-envio) – `PT` 字母单号与 22/25 位数字单号
- [CTT: Condições Gerais de Acesso e Utilização do Serviço Universal](https://www.ctt.pt/home/servico-postal-universal/condicoes-gerais-de-acesso-e-utilizacao-do-servico-universal) – 葡萄牙普遍邮政服务说明
- [CTT: Empresas do grupo](https://www.ctt.pt/grupo-ctt/a-empresa/quem-somos/empresas-do-grupo/) – CTT 集团旗下 CTT Express 与 CTT Expresso
- [CTT: Prazo para levantar](https://www.ctt.pt/ajuda/particulares/receber/levantar-correspondencia-ou-encomendas-/prazo-para-levantar) – Expresso 单号与取件期限
- [CTT: Collectt](https://www.ctt.pt/particulares/receber/locais-de-entrega-alternativos/collectt) – 葡萄牙 Collectt 取件网络
- [CTT: Entrega não conseguida](https://www.ctt.pt/ajuda/particulares/receber/problemas-na-entrega/entrega-nao-conseguida) – 葡萄牙未妥投处理
- [CTT: Condições gerais de transporte de Encomendas Expresso](https://www.ctt.pt/transversais/condicoes-gerais-transporte-encomendas-expresso) – 理赔申诉期限
- [CTT: Linhas de Apoio](https://www.ctt.pt/ajuda/particulares/apoio-ao-cliente/contactos/linhas-de-apoio) – 葡萄牙客服热线与服务时间
- [CTT: Alertas de Phishing](https://www.ctt.pt/transversais/alertas-de-phishing) – 假冒 CTT Expresso 关税诈骗短信警示
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Peek 读取和显示的内容、`Recogerán en Delegación`、`PT` 单号转交 CTT
:::
