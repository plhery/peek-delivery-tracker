---
title: Correos de Chile 智利邮政追踪：单号、状态与关税说明
description: 追踪智利邮政（Correos de Chile）包裹：CL 结尾与 13 位单号格式、西班牙语状态含义、智利 19% 增值税清关及海外联运。
slug: correos-chile-tracking
published: 2026-11-26
updated: 2026-11-26
---

Correos de Chile 是智利的国家邮政服务机构。智利海关说明，通过邮政渠道寄往智利的网购订单由 Correos de Chile 负责入境递送，其余则由私营快递公司承运。Correos de Chile 自有追踪页面位于 www.correos.cl/seguimiento-en-linea，你也可直接将单号粘贴到上方输入框中并参考本页说明。网购订单单号通常位于商家的发货通知邮件或订单详情页。

## Correos de Chile 追踪单号长什么样

Peek 识别 Correos de Chile 的两种单号格式（数字均为虚构）：

| 单号（虚构） | 说明 |
| --- | --- |
| `RR482910372CL` | 2 位字母 + 9 位数字 + 智利代码 `CL`：由 Correos de Chile 编发的[国际邮政通用格式](guide:tracking-number-formats)（英文）邮件。前两位字母不固定，9 位数字末位为校验位。 |
| `7394028156342` | 13 位纯数字：Peek 识别的另一类 Correos de Chile 格式，但匹配度较弱，DPD France、Yunda 等多家承运商也使用 13 位数字。 |
| `RR482910372CN` | 以其他国家代码（如 `CN`）结尾：由发件国邮政编发，此处为[中国邮政（China Post）](carrier:china-post)，Peek 会通过该发件邮政跟进。 |

在 Peek 中粘贴单号时，空格和大小写均不影响查询。找不到单号？请参阅[如何找到你的追踪单号](guide:find-tracking-number)（英文）。

## Correos de Chile 追踪状态含义

Correos de Chile 追踪页以西班牙语大写字母倒序列出各条扫描，并在有记录时附带处理邮局名称。Peek 从该追踪页识别的状态按包裹经历的顺序如下：

| Correos de Chile 显示状态 | 对你的含义 | Peek 显示阶段 |
| --- | --- | --- |
| `ENVÍO ADMITIDO` | Correos de Chile 已收寄该邮件。对于境外来件，这是入境前的第一条记录。 | “已揽收” |
| `ENVÍO EN PROCESO DE INTERNACIÓN AL PAÍS` | 正在办理入境智利清关手续，由智利海关审核。 | “清关中” |
| `ENVÍO EN REPARTO` | 正在派送中。 | “派送中” |
| `ENVÍO ENTREGADO` | 已送达。 | “已签收” |

对于未收录的其他状态文本，Peek 会统一显示为 `Actualización del envío`（货件动态更新）而不展示原文，以防原文包含个人隐私或投递细节。如需查看完整原文，请前往 www.correos.cl 查询。详见[追踪状态含义指南](guide:tracking-statuses)（英文）。

## 寄往智利的国际包裹：海关查验与 19% 增值税（IVA）

寄往智利的海外网购包裹须经过智利海关（Servicio Nacional de Aduanas）审核。如从中国网店下单，另请参阅[中国跨境包裹追踪指南](guide:tracking-from-china)（英文）。

**网购订单增值税（IVA）。** 自 2025 年 10 月 25 日起，价值不超过 **500 美元**的海外网购订单须缴纳 **19% 增值税（IVA）**且免征关税。若商家或电商平台已在智利税务局（SII）登记，结账时即会代收 IVA；若未登记，则由快递公司或邮政包裹的 Correos de Chile 在进口环节代收。付款前请核对价格明细。部分商品（如二手衣物、烟草、酒类和珠宝）需缴纳其他或额外税费，部分物品需进口许可。超过 500 美元的订单须在进口时缴税。

**海关查验。** 包裹抵达 Correos de Chile 处理中心后，海关可能在仓库内抽检。若海关要求提供购买凭证、证书或进口许可（*visto bueno*），Correos de Chile 会通知你如何办理；未提交前海关无法放行。若涉及关税或 IVA，Correos de Chile 会请你提供收据或发票等付款证明，否则海关将按市场价核定税基。海关放行后即由 Correos de Chile 派送。

**派送与自提规则。** 由于 Peek 无法读取 Correos de Chile 自己的规则，本页未列出尝试次数、自提方式或保留天数。详见[派送未成功处理指南](guide:missed-delivery)（英文）和[未领取包裹指南](guide:uncollected-parcel)（英文）。

## 从智利寄往海外的 Correos de Chile 包裹

如果你的包裹从智利寄出且带有 `CL` 单号，离开智利后建议同时用同一单号在收件国邮政的追踪页查询，例如 [USPS](carrier:usps)、[Royal Mail](carrier:royal-mail)、[Parcelforce](carrier:parcelforce)、An Post、[Canada Post](carrier:canada-post)、[Australia Post](carrier:australia-post) 或 NZ Post（Peek 也会替你查询，见下文）。包裹进入本国邮政后，请向本国邮政咨询派送与自提事宜。

**欧盟海关规定。** 智利位于欧盟之外，因此寄往欧盟国家的包裹需办理清关。自 2026 年 7 月 1 日起，来自欧盟以外、价值不超过 150 欧元的网购订单按每项商品征收 3 欧元欧盟关税；自 2026 年 11 月 1 日起，无论货值高低，另行适用每项商品 2 欧元的欧盟处理费（“一项商品”指同一包裹内税则归类、品名及原产地要求一致的货物）。其他国家请按当地规则办理，详见[包裹滞留海关指南](guide:customs)（英文）。

## Correos de Chile 无更新、丢失或退回怎么办

**尚无扫描。** 发件人可能尚未交寄。归类为 Correos de Chile 后，Peek 会显示“等待 Correos de Chile 更新”并持续查询；若数日仍无记录，请与发件人核对单号。详见[为何物流停止更新](guide:tracking-not-updating)（英文）。

**停留在清关环节。** 当最新状态为 `ENVÍO EN PROCESO DE INTERNACIÓN AL PAÍS` 时，Peek 会标记为“清关”。海关可能正在查验或等待单证/缴税，如需配合，Correos de Chile 会通知你。

**连续多日静默。** 当处于“已生成面单”、“已揽收”或“运输中”的包裹满四天没有新步骤时，Peek 会用该步骤的日期加以标记（“自10月12日周一起没有物流更新”），并附上“向 Correos de Chile 寻求帮助”链接。包裹在长途航班上或遇到周末时常常暂时没有动静。满三周仍没有新步骤时，Peek 会改为显示“已超过三周没有新动态。如果还没收到，请联系卖家。”

**显示已签收但没收到。** 请先询问邻居和当地投递邮局，再联系发件人。详见[显示已签收但未收到包裹](guide:delivered-not-received)（英文）。

**丢失或退回。** 请先联系发件人或商家处理，参阅[包裹丢失处理指南](guide:lost-parcel)（英文）与[包裹退回发件人](guide:returned-to-sender)（英文）。

## Correos de Chile 客服与联系方式

由于 Peek 无法读取 Correos de Chile 的网站，本页未列出电话号码或营业时间：Peek 仅收录核自官方页面的联系方式。对于邮政清关包裹，智利海关指引用户前往 www.correos.cl 查询进度，或致信 `sac@correos.cl`。

网购订单请优先联系为你预订寄递的商家。

## 使用 Peek 追踪 Correos de Chile 包裹

只要校验位正确，[Peek](/) 会立刻将“2 位字母 + 9 位数字 + `CL`”识别为 Correos de Chile 并显示“识别出的快递公司”；可点击“更换”选择其他快递公司。若校验位有误，会提示“这个单号的校验位对不上。”，你可核对发货邮件或选择“按输入的单号跟踪”。

13 位纯数字无法仅靠长度确定属于 Correos de Chile，但仍可直接粘贴（Peek 跟踪 4,500 多家快递公司）。若未指定承运商，Peek 会先询问 FAN Courier（在墨西哥同时询问 Paquetexpress），再询问追踪服务（“正在查询物流服务…”），查无结果后再尝试 Delhivery、Econt 或 Ozon 等 13 位承运商，但从不询问 Correos de Chile；只有在追踪服务唯一指明 Correos de Chile 且附带日期步骤时，才会将包裹归入 Correos de Chile。因此若商家写明 Correos de Chile，请点击“更换”并在“符合这个单号”下选择它，或在已添加的包裹页使用“更换快递公司”。其他国家邮政签发的单号（如 `CN` 结尾）不属于 Correos de Chile，若你为它选择 Correos de Chile，Peek 会提示它“不使用这种格式的单号”。

归入 Correos de Chile 后，首条扫描前会显示“等待 Correos de Chile 更新”。随后 Peek 直接读取 Correos de Chile 追踪器，以西班牙语原文及邮局名称展示已识别的扫描，并归入 Peek 自己的阶段（如“已揽收”或“清关中”），同时隐藏收件人姓名且不显示预计送达日期。由于时间不带时区，Peek 按扫描邮局所在当地时钟或相邻步骤时区解析。Correos de Chile 的追踪系统无响应时，Peek 会改为查询追踪服务（优先 17TRACK）并按其原文展示。

在智利境外追踪 `CL` 单号时，Peek 还会用同一单号同步询问收件国邮政：美国 USPS、英国 Royal Mail（`E` 开头为 Parcelforce）、爱尔兰 An Post、Canada Post、Australia Post、NZ Post、德国 [DHL](carrier:dhl)、法国 [La Poste](carrier:la-poste)、西班牙 [Correos](carrier:correos-spain)、巴西 [Correios](carrier:correios-br) 以及除荷兰外的所有欧盟国家邮政、阿根廷和秘鲁等。一旦当地邮政出现不早于 Correos de Chile 最新扫描的记录，Peek 即切换并保留智利段历史。从墨西哥或中国查询时不询问其他邮政。反之，对于持他国单号寄往智利的包裹，Peek 无法在 Correos de Chile 的追踪系统上查询，会继续通过发件国邮政跟进。

当你在 Peek 中打开包裹页面，或在派送当日开启通知时，Peek 最多每 10 分钟询问一次 Correos de Chile；在包裹派送当天，只要 Correos de Chile 自己的追踪系统有响应，会从最新扫描所在地早上 6 点起（在智利境内为智利时间）最多每 2 分钟检查一次，需要打开浏览器查询时则每 5 分钟一次。连续两天没有新动态后（包裹仍显示“已生成面单”或“已揽收”时为 12 小时），Peek 改为每小时检查一次；停留在“已生成面单”且七天没有新动态，或七天完全没有步骤时，每 6 小时一次。提醒可选“每次扫描”、“重要环节”或“仅派送当天”。Peek 无法代你联系 Correos de Chile、你所在地的邮政或商家。

## 关于 Correos de Chile 物流查询的问题

### 如何用中文或英文看懂 Correos de Chile 追踪状态？

将单号粘贴到 Peek 中即可。扫描原文保留西班牙语，但 Peek 会将已识别状态归入统一阶段，并可对照上表查看中文解释。

### “ENVÍO EN PROCESO DE INTERNACIÓN AL PAÍS”是什么意思？

表示包裹正在办理入境智利的进口手续并由海关审核。若海关需要单证或缴税，Correos de Chile 会通知你。

### 从海外寄到智利的包裹需要缴税吗？

自 2025 年 10 月 25 日起，价值不超过 500 美元的海外网购订单须缴纳 19% 增值税（IVA），免征关税。已在智利税务局登记的平台会在结账时代收，否则由 Correos de Chile 或快递公司在进口时收取。

### 为什么我的 Correos de Chile 追踪没有更新？

包裹可能正在清关、处于国际航班中或尚未交寄。如寄往海外，请用同一 `CL` 单号在收件国邮政的追踪页查询。

### Correos de Chile 追踪单号长什么样？

常见为 2 位字母 + 9 位数字 + `CL`（如 `RR482910372CL`），也有 13 位纯数字。Peek 可按格式识别前者；后者请点击“更换”选择 Correos de Chile。

:::sources
- [Servicio Nacional de Aduanas: Compras online](https://www.aduana.cl/aduana/site/edic/base/port/comercio_electronico.html) – 邮政网购经由 Correos de Chile 入境，其余由快递承运
- [Servicio Nacional de Aduanas: IVA digital](https://www.aduana.cl/aduana/site/edic/base/port/especial_iva_digital.html) – 500 美元以下 19% IVA、免关税、征收方、其他税种及 500 美元以上规则
- [Servicio Nacional de Aduanas: ¿Cuándo debo pagar impuestos?](https://www.aduana.cl/cuando-debo-pagar-impuestos/aduana/2018-12-12/233227.html) – 自 2025 年 10 月 25 日起实施数字 IVA
- [Servicio Nacional de Aduanas: Revisiones aduaneras despachos vía postal](https://www.aduana.cl/revisiones-aduaneras-despachos-via-postal/aduana/2018-12-13/161233.html) – Correos de Chile 仓库海关查验、单证与许可、付款证明、放行及 www.correos.cl 与 `sac@correos.cl`
- [欧盟委员会：欧盟海关改革](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 3 欧元关税与 2 欧元处理费
- [欧盟委员会：3 欧元关税与欧盟处理费指南（PDF）](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 无论货值按项计费及商品项定义
- [Universal Parcel Scraper: Correos de Chile](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/correos-chile) – 单号格式、校验位、追踪器、西语状态与阶段、中性提示、Peek 保留字段与时区
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – 共享 13 位格式的其他承运商、查询顺序、追踪服务及联动邮政列表
:::
