---
title: NACEX 快递查询：单号格式、西班牙语状态与客服
description: 查询西班牙、葡萄牙和安道尔 NACEX 包裹：4 位加斜杠加 8 位运单号格式、西班牙语状态中文解读、NACEX.shop 自提点及客服电话。
slug: nacex-kuaidi-chaxun
published: 2026-11-24
updated: 2026-11-24
---

NACEX 是 Logista 集团旗下的加急快递公司，派送范围覆盖西班牙、葡萄牙、安道尔和荷兰。你可以把运单号（4 位数字 + 斜杠 + 8 位数字）输入 [NACEX 官网](https://www.nacex.es/)查件框，或直接粘贴到上方输入框查询。运单号对应寄件人（通常是商家）下单创建的快件；NACEX 不能按订单号或邮编查件。

## NACEX 运单号是什么样的？

NACEX 查件框标示的标准格式为 4 位数字、斜杠再加 8 位数字，[Tracking and Tracing 页面](https://www.nacex.es/irSeguimiento.do)也会分两段输入。以下为虚构示例：

:::anatomy 4829 / 10573649
- 4829 | 网点代码：发件的 NACEX 特许经营网点编号
- / | 分隔两部分的斜杠
- 10573649 | 运单号：该快件本身的流水编号
:::

NACEX 未说明收件人具体在何处查找单号，也未说明其短信显示的发件人名称；一般可以在哪里找到单号，见[如何查找运单号](guide:find-tracking-number)（英文）。

| 你手头的编号（虚构） | 说明 |
| --- | --- |
| `4829/10573649` | 标准 NACEX 运单号，直接按原样查询即可。 |
| `482910573649` | 可能是漏掉斜杠的 NACEX 单号（斜杠位于第 4 位数字之后）；[单纯 12 位数字同样匹配其他多家快递](guide:tracking-number-formats)（英文）。 |
| `4829105736` | 不超过 10 位数字：可能是印在面单上的 NACEX “备用参考号（alternative reference）”，但官网未说明在哪里输入。 |

若官网提示 “There are no delivery notes entered into the system…”，请核对数字与斜杠位置，再按提示联系就近 NACEX 网点。

## NACEX 西班牙语物流状态中文解读

NACEX 官网未公布完整状态列表。下表列出 Peek 抓取到的西班牙语状态原文（NACEX 表单可切换英语，但状态行是否随之翻译尚未确认），按通常顺序排列：

| NACEX 状态原文 | 中文含义 |
| --- | --- |
| `Notificado` | 已通知：揽收相关的记录说明，不是实物扫描。 |
| `Aceptada` | 已受理：NACEX 已揽收包裹。 |
| `En tránsito` | 运输中：正在 NACEX 各网点与分拨中心之间转运。 |
| `En reparto` | 派送中：快递员已带件出发派送。 |
| `Ausente` | 无人签收：快递员上门时无人在家。 |
| `Gestión de servicio, posible demora` | 服务处理中、可能延误：网点正在处理异常（如当日未能完成派送），提示包裹可能晚点。 |
| `Solución de entrega concertada` | 异常后已重新约定派送方案（如另约日期）。 |
| `Solución de entrega en punto` | 已改投至 NACEX 自提点。 |
| `Cambio de dirección` | 更改地址：正按新地址转派。 |
| `Disponible en punto NACEX` | 已到达 NACEX 自提点，可以前往领取。 |
| `Contacta con agencia` | 请联系网点：派送网点请你主动与其取得联系。 |
| `Solucionado sin OK` | 非妥投结案：未完成签收即结案（例如已退回）。 |
| `Entregado` | 已签收送达。 |

以 `Solución de entrega…` 开头的记录代表已安排处理措施而非终点，包裹随后会继续流转。最需要留意的是 `Solucionado sin OK`：请立即向 NACEX 或商家核实情况。`Sin estado`（无状态）仅为行政备注（如已发送通知或达到尝试次数上限），实际进度以上一条记录为准。各物流阶段含义另见[物流状态说明](guide:tracking-statuses)（英文）。

## NACEX 自提点与派送未成功

**上门无人：** 按 NACEX 2026 年服务条款，住宅地址派送失败时，快递员会留下通知单以安排第二次（亦即最后一次）派送。常见问题补充说“在可能的情况下”，且当地网点若有你的电话会致电联系。近期外出请联系客服或就近网点。

**预通知（Pre-Alert）：** 若寄件人服务包含此项，NACEX 会通过短信或邮件告知预计派送日期与时段，其中的链接可进入 My Preferred Delivery，供你改选日期、时段、NACEX 网点或 NACEX.shop 自提点。

**NACEX.shop 自提点：** 在西班牙和葡萄牙有 4,000 多家店铺与快递柜，许多周末照常营业。发往自提点的包裹在次个工作日 09:00 起或 12:00 前可取（视自提点而定），到件后 NACEX 会通知你。可通过 [NACEX 网点查找器](https://www.nacex.es/irCerca.do)搜索网点与自提点。NACEX 未公布自提点或快递柜的保管天数。

**NACEX 营业网点自提：** 包裹最长保管 **15 天**，逾期退回寄件人。请提前告知网点，并于周一至周五营业时间内（各网点不同）前往领取。委托他人代领需提供你的签字授权书及身份证件复印件。

周六派送（09:00 至 13:00）属加价服务，仅限设有 NACEX 特许网点的城镇及周边 15 公里内；加那利群岛周六仅派送文件。周日不计为工作日。当面收货时可直接告知快递员拒收包裹。

## 包裹未到、延误、破损或退回怎么办？

NACEX 理赔仅面向支付运费的合同客户（通常是商家），赔付款项亦通过该客户所属特许网点结算。

- **未送达或轨迹停滞：** NACEX 常见问题建议写信至 atencion.cliente@nacex.com 或致电 900 100 000（未限定需先等待多久）。另见[物流停止更新怎么办](guide:tracking-not-updating)（英文）。
- **延误：** 若超出所购服务时效，合同客户可在 31 天内提出索赔。
- **丢失或被盗：** 须在 7 天内附内件发票提出索赔。
- **破损或短少：** NACEX 建议交接当场注明；破损索赔须自签收起 7 天内附照片提交。西班牙运输法（Ley 15/2009 第 60 条）要求可见破损在签收时书面保留异议，隐蔽破损须在 7 个自然日内提出。
- **已退回：** NACEX 未对收件人给出退件指引，请联系商家重新发货或退款。

轨迹显示 `Entregado` 却没收到包裹？参阅[显示已签收但未收到](guide:delivered-not-received)（英文）。

## NACEX 客服电话与联系渠道

NACEX 官网列出的联系渠道（均未注明电话、邮件或在线聊天的具体服务时段）：

- **西班牙：** 900 100 000（免费；英文页面标有 +34 但未说明境外是否可拨）、邮箱 atencion.cliente@nacex.com，以及[联系页面](https://www.nacex.es/irContactenosContacto.do)上的在线聊天。
- **安道尔：** 无独立电话或邮箱，请使用西班牙渠道并在网点查找器中选择安道尔标签。
- **葡萄牙：** 800 200 077（免费）、邮箱 atencao.cliente@nacex.com，以及 [nacex.pt 联系页面](https://www.nacex.pt/irContactenosContacto.do)上的在线聊天。
- **当地营业网点：** 请在上述 NACEX 网点查找器中查询。
- **理赔入口：** 访问 [NACEX 理赔页面](https://www.nacex.es/irReclamaciones.do)。

NACEX 官网未列出 WhatsApp 客服号码。

## 用 Peek 查询和跟踪 NACEX 包裹

[Peek](/) 根据单号结构识别 NACEX：正好 4 位数字、斜杠和 8 位数字。粘贴带 `intcli=` 的 nacex.es 查询链接或整封发货邮件也可以。

Peek 最高每 10 分钟检查一次；在 NACEX 扫描包裹进入派送的当天，只要 NACEX 自己的查询系统正常响应，自当地时间早上 6 点起最高每 2 分钟检查一次；通过物流查询服务时，派送当天在打开包裹页面或开启通知时最高每 15 分钟检查一次。可以设置“每次扫描”、“重要环节”或“仅派送当天”提醒。若连续两天无新动态（或在“已生成面单”、“已揽收”状态下满 12 小时），Peek 改为每小时检查一次。Peek 会显示物流状态、完整历史并在地图上标出各扫描网点位置。NACEX 官网仅为签收一步提供具体时刻，其余步骤仅显示日期。Peek 会隐去收件人详情、签收凭证、派送备注、联系方式和商家参考号，也无法代你催件或联系 NACEX。

## NACEX 查询常见问题

### NACEX 是什么快递？

NACEX 创立于 1995 年，隶属 Logista 集团，是一家覆盖西班牙本土、巴利阿里群岛、加那利群岛、安道尔、葡萄牙和荷兰的加急快递公司。

### NACEX 是正规快递公司吗？

是的，NACEX 是西班牙成熟的快递品牌。其官方查询网站为 nacex.es 与 nacex.pt，官方客服邮箱均以 @nacex.com 结尾。

### NACEX 派送时效是多久？

取决于寄件人所购服务：NACEX 19:00 H 在西班牙本土、安道尔、葡萄牙和巴利阿里群岛于次个工作日 19:00 前送达（若收件城镇距派送特许网点超过 50 公里，则为第二个工作日 13:00 前）。发往加那利群岛需 24 至 48 小时，亚速尔和马德拉群岛需 2 至 5 天。

### 可以更改 NACEX 包裹的收件地址吗？

更改地址需得到支付运费的客户（通常是商家）授权：在同一特许网点覆盖区内改址免费，跨区会产生额外费用。此外也可通过 Pre-Alert 通知里的链接把包裹改投至 NACEX 自提点。

:::sources
- [NACEX: Tracking and Tracing](https://www.nacex.es/irSeguimiento.do) – 单号结构与其他参考号说明
- [NACEX: Track and trace form](https://www.nacex.es/seguimientoFormularioExterno.do?intcli=0000/00000000) – 查询链接与未找到提示
- [NACEX: Home](https://www.nacex.es/) – 查件框、Logista 集团与 4,000 个自提点
- [NACEX: FAQs](https://www.nacex.es/irFaqs.do) – 联系方式、无人签收、15 天保管、改址与拒收
- [NACEX: FAQ by category](https://www.nacex.es/visualizarfaq.do?category=900) – 网点致电与网点自提
- [NACEX: FAQ by category](https://www.nacex.es/visualizarfaq.do?category=901) – 周六加价服务
- [NACEX: Contact](https://www.nacex.es/irContactenosContacto.do) – 西班牙电话、邮箱与在线聊天
- [NACEX Portugal: Contacto](https://www.nacex.pt/irContactenosContacto.do) – 葡萄牙免费电话 800 200 077
- [NACEX: Claims](https://www.nacex.es/irReclamaciones.do) – 理赔主体与时限
- [NACEX: NACEX.shop](https://www.nacex.es/irEnvioSostenible.do) – 09:00 起自提说明
- [NACEX: NACEX Finder](https://www.nacex.es/irCerca.do) – 网点与自提点查找器
- [NACEX: My Preferred Delivery](https://www.nacex.es/irServicioNacional.do?seccion=mep) – 改派与次工作日自提
- [NACEX: Alertas de información](https://www.nacex.es/pages/img/servicios/valor/Alertas_informacion_ES.pdf) – Pre-Alert 预通知
- [NACEX: Urgentes](https://www.nacex.es/irServicioNacional.do?seccion=urgentes) – 19:00 H、周六与加那利群岛时效
- [NACEX: Portugal](https://www.nacex.es/irServicioNacional.do?seccion=portugal) – 亚速尔与马德拉时效
- [NACEX: General terms and conditions 2026](https://www.nacex.es/pages/img/servicios/condiciones_tramitaciones/condiciones-generales-2026-EN.pdf) – 二次派送、工作日与周六时段
- [NACEX: Servicios y condiciones de transporte 2026](https://www.nacex.es/pages/img/servicios/condiciones_tramitaciones/condiciones-generales-2026-ES.pdf) – 50 公里规则与周六 15 公里范围
- [NACEX: ¿Quiénes somos?](https://www.nacex.es/irEmpresaQuienes.do) – 1995 年成立、Logista、荷兰与快递柜
- [BOE: Ley 15/2009](https://www.boe.es/buscar/act.php?id=BOE-A-2009-18004) – 破损书面异议与 7 天时限
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Peek 读取和显示的信息
:::
