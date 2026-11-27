---
title: Stone3PL 国际物流单号查询：S 运单格式、状态与客服
description: 查询深圳 Stone3PL 跨境包裹：S 加 18 位单号规则、英文扫描状态、目的国末端交接单号、欧盟 3 欧元关税与 2 欧元处理费及客服。
slug: stone3pl-kuajing-chaxun
published: 2026-11-24
updated: 2026-11-24
---

Stone3PL 是一家总部位于中国深圳的国际物流公司，隶属于 Stone Group，联合多家全球物流合作伙伴为电商卖家和企业将货物从中国发往世界各地。如果网店通过 Stone3PL 发货，你可以在 [Stone3PL 英文查件页面](https://stone3pl.com/?route=services/track) 查询，也可以直接把运单号粘贴到上方查询框中。运单号由卖家随订单发货信息提供。

## Stone3PL 单号格式：`S` 加 18 位数字

Stone3PL 网站未公布统一单号规则，但 Peek 收录了 Stone3PL 的一种常见形态：字母 `S` 后接 18 位数字（以下两个示例均为虚构）：

| 单号（虚构） | 归属与说明 |
| --- | --- |
| `S482917365428193642` | `S` 加 18 位数字。遇到该形态时 Peek 会向 Stone3PL 查询，但仅凭单号格式本身并不能断定包裹一定属于 Stone3PL。 |
| `S48291736542819364` | 仅有 17 位数字：不符合 Peek 关联至 Stone3PL 的长度，请检查是否漏了一位数字，或照常粘贴查询。 |

在 Peek 中输入小写字母、空格或连字符不影响识别。Stone3PL 网站查件页支持一次输入最多 40 个单号（每行一个），系统会自动只保留字母、数字与连字符并将小写转为大写。

**在哪里找到单号。** 可在卖家发货邮件或订单详情页查看；找不到时请参阅[如何查找快递单号](guide:find-tracking-number)（英文）。

**其他单号。** Stone3PL 查件页列出了其系统可同步追踪的承运商，包括 DHL Express、UPS、USPS、中国邮政和 China EMS（ePacket）。查询结果还可能展示目的国末端派送公司的名称及对应本地单号，你也可以拿着该单号直接到当地快递网站查询。

## Stone3PL 物流状态含义解析

Stone3PL 的扫描轨迹为英文原文，附带日期与时间但不包含具体地点。其网站结果页会先列出目的国段的记录，再列出中国始发段的记录。以下是 Peek 识别的常见扫描文案：

| Stone3PL 显示 | 对你的含义 | Peek 显示 |
| --- | --- | --- |
| `Order Submited.` | 卖家已向 Stone3PL 提交订单，Stone3PL 可能尚未拿到实物包裹（拼写为 Stone3PL 系统自带）。 | “已生成面单” |
| `Shipment information received` | Stone3PL 已收到包裹电子信息。 | “已生成面单” |
| `Processing information input` | Stone3PL 正在录入包裹处理信息。 | “已生成面单”（出现在 `Pickup Scan` 之后则为“运输中”） |
| `Pickup Scan` | 包裹已完成揽收扫描，正式启程。 | “已揽收” |
| `facility - Outbound` | 包裹已离开仓库或分拣中心。 | “运输中” |
| `Arrived at domestic terminal station` | 已抵达转运枢纽站（该文案未标明所在国家）。 | “运输中” |
| `Port of departure` | 已抵达始发口岸或机场，等待离境。 | “运输中” |
| `International shipment release - Export` | 出口海关已放行，继续出运。 | “运输中” |
| `Port of departure - Departure` | 已离开始发口岸或机场。 | “运输中” |
| `Port of destination - Arrival` | 已抵达目的国口岸或机场。 | “运输中” |
| `International shipment release - Import` | 目的国进口海关已放行。 | “运输中” |
| `Pick up by local carrier at destination port` | 目的国本地派送快递已在口岸提走包裹，并非要求收件人自提。 | “运输中” |
| `Batch delivery to carrier` | Stone3PL 已将包裹随批次交接给末端派送公司。 | “运输中” |

目的国段的扫描可能采用其他措辞。Peek 会解析常见关键词（如 delivered 或 customs）；暂未识别的文案会保留在轨迹中，阶段保持在上一个已识别节点。

**整体状态。** Stone3PL 查件接口还会返回整票包裹的总状态。只要总状态表明待取件、已签收或存在异常，即使最新一条明细尚未写明，Peek 也会显示“待取件”、“已签收”或“需要留意”。

**时间换算。** Stone3PL 返回的时间不带时区标识。Peek 会将中国始发段扫描按北京时间（`UTC+8`）读取，将目的国段扫描及 `International shipment release - Import` 按目的国时区读取（若该国跨多时区则取其一），最后统一换算为你设备的本地时间。

## Stone3PL 包裹在目的国由谁派送

Stone3PL 网站称其“与全球知名物流服务商合作”，并在北美（NORAM）、亚太（APAC）以及欧洲、中东和非洲（EMEA）均设有仓库。

其网站页面未逐一列明各国的末端派送商：请在 Stone3PL 查询结果中查看目的国派送公司名称及转单号。具体派送尝试次数、通知单与自提点保管规则均以该末端公司为准。详见[错过派送怎么办](guide:missed-delivery)（英文）。

**关税与 DDP 清关。** Stone3PL 向卖家提供完税后交货（DDP）渠道及“DDP customs clearance”清关服务，但只有发货卖家能确认你的订单是否按 DDP 方式寄出。

**欧盟收件人（以爱尔兰等成员国为例）。** 自 2026 年 7 月 1 日起，从欧盟以外网购寄入欧盟、货值不超过 150 欧元的包裹，每类商品征收 3 欧元关税；自 2026 年 11 月 1 日起，不论货值高低，每类商品另须缴纳 2 欧元欧盟处理费（“一类商品”指共享同一税则归类、品名及适用原产地的货物）。Stone3PL 网站未说明上述税费的缴纳方式。英国、美国、加拿大或澳大利亚的税费则依各国规定执行，详见[包裹被海关扣留怎么办](guide:customs)（英文）。

## Stone3PL 包裹不更新、遗失或退回怎么办

Stone3PL 面向商业发件卖家提供服务，其网站未设个人收件人帮助页；遇到延误、丢件、破损或退回，请先联系发货卖家。

**暂未查到记录。** Peek 会持续查询：如果你手动选择了 Stone3PL，会显示“等待 Stone3PL 更新”；若添加时未确定承运商，则显示“暂无物流记录”。未指定承运商的包裹满两天仍无记录时，Peek 会提示“仍在等待快递公司的首条动态”，请向卖家核对单号。若已选定 Stone3PL 但两天后仍查无此号且无其他追踪服务返回结果，Peek 会提示“两天过去了，快递公司仍查不到这个单号。请检查单号、更换快递公司，或删除这个包裹。”，此后改为每天查询一次，添加满一周后改为每周查询一次。

**停在 `Order Submited.` 或 `Shipment information received`。** Stone3PL 已收到电子订单，但可能尚未拿到实物包裹；若多日无进展请向卖家确认是否已交货。

**多日无新扫描。** 当处于“已生成面单”、“已揽收”或“运输中”的包裹满四天没有新动态时，Peek 会标出最新扫描日期（“自10月12日周一起没有物流更新”），并提示包裹在长途航班、海关或周末常暂时没有动静。详见[为什么物流停止更新](guide:tracking-not-updating)（英文）与[中国跨境包裹追踪指南](guide:tracking-from-china)（英文）。

**显示“需要留意”。** 说明 Stone3PL 标记了异常，请阅读最新一条扫描并联系卖家确认后续处理。

**显示已签收但未收到。** 请先排查家门口周边并询问邻居，随后联系卖家。详见[显示已签收但未收到](guide:delivered-not-received)（英文）。

**丢失、破损或退回。** Stone3PL 网站未向收件人公布索赔或退货规则，首页仅面向卖家提供“退件重发服务”。请直接向卖家申请退款或重寄。详见[包裹丢失](guide:lost-parcel)（英文）、[包裹破损](guide:damaged-parcel)（英文）与[包裹退回寄件人](guide:returned-to-sender)（英文）。

**收到要求付款的短信或邮件。** 付款前请务必先阅读[快递诈骗短信识别指南](guide:delivery-scam-texts)（英文）。

## Stone3PL 官方联系方式

以下为 [Stone3PL 联系我们页面](https://stone3pl.com/index.php?route=services/contact_us) 公布的深圳总部（“STONE3PL Marketing Dpt”）联络信息：

- **电子邮箱：** `support@stone3pl.com`（隐私政策中亦公布此邮箱）。
- **联系电话：** `86-0755-81466633`（中国深圳座机；其网站未注明办公时间及支持语种）。
- **公司地址：** Ground Floor, Building C5, Xin'an Industry Co., No.2 Industrial Zone, Gushu 2nd Rd., Baoan, Shenzhen, Guangdong Province, China。

有关订单退款或配送问题请优先联系发货卖家，因为运输合同由卖家与 Stone3PL 签订。

## 使用 Peek 追踪 Stone3PL 包裹

[Peek](/) 支持追踪 Stone3PL 包裹，但仅凭单号形态无法直接锁定它。粘贴 `S` 加 18 位数字时，Peek 会显示“正在查询物流服务…”并向 Stone3PL 核实；一旦 Stone3PL 确认收录，承运商一行会显示 Stone3PL“查到了这个包裹”并直接跟进。直接粘贴 `stone3pl.com` 查件页且 `nums=` 后仅跟单个单号的链接，则会立刻识别为 Stone3PL（“识别出的快递公司”）。

其他单号照常粘贴即可——Peek 跟踪 4,500 多家快递公司。遇到弱匹配格式时，Peek 会显示“正在查询物流服务…”并依次核实；不匹配任何格式的单号会在添加后查询。若 Peek 未自动识别，点击“更换”并在“所有快递公司”中选择 Stone3PL，或在已添加包裹页点击“更换快递公司”。

Peek 会读取整票总状态、带时间戳的每条扫描、始发与目的国家，以及给出的末端派送公司和本地单号，同时剔除电话号码。Stone3PL 不提供预计送达日期、重量、扫描地点或签收证明。

当 Stone3PL 返回末端派送公司及其本地单号时，Peek 会同步询问该公司。若返回万国邮联标准单号（2 字母 + 9 数字 + 2 字母）但未写明公司名，Peek 会询问目的国邮政，如美国的 USPS、英国的 Royal Mail、爱尔兰的 An Post、Canada Post 或[澳大利亚邮政（Australia Post）](carrier:australia-post)；若是其他单号，则会询问对应格式的唯一承运商。一旦末端承运商出现不早于 Stone3PL 最新记录的扫描，Peek 会切换至该承运商继续跟进并保留 Stone3PL 历史；在此之前，包裹页也会提前标注“由 USPS 派送”等字样，若末端承运商仅有预报尚未产生实际扫描，则显示“<承运商> 已收到预报，尚未收到包裹”。若无对应本地承运商，Peek 会继续在 Stone3PL 上追踪。

在打开包裹页面或预计派送当天开启提醒时，只要 Stone3PL 官方追踪接口正常响应，Peek 最高每 10 分钟检查一次，在派送中当天自目的国时间早上 6 点起最高每 2 分钟检查一次；若由聚合追踪服务跟进，则每小时检查一次、静默满一天后改为每 3 小时一次，在派送当天打开页面或开启提醒时最高每 15 分钟一次。其余时间，以及连续两天无新动态（或停留在“已生成面单”、“已揽收”满 12 小时）后，Peek 每小时检查一次；停留在“已生成面单”满七天无更新后降为每 6 小时一次。由目的国承运商接手后，刷新节奏随该承运商而定。通知预设可选“每次扫描”、“重要环节”或“仅派送当天”。Peek 无法代你联系 Stone3PL 或卖家。

## 关于 Stone3PL 追踪的常见问题

### Stone3PL 是正规物流公司吗？

Stone3PL 介绍其隶属于 Stone Group，总部设在深圳，拥有 10 余年行业经验及 8,000 多家签约客户，并在其网站公开了地址、电话和邮箱。因其服务对象为发货商家，个人订单问题请联系网店卖家。

### 在哪里查询 Stone3PL 包裹？

可在 [Stone3PL 官方追踪页](https://stone3pl.com/?route=services/track) 或 Peek 中查询。如果查询结果显示了目的国本地派送公司与单号，也可以直接去该本地公司网站追踪。

### `Pick up by local carrier at destination port` 是什么意思？

表示你所在国家的末端派送快递已在抵达口岸或机场提走了包裹，并不需要收件人自行前往口岸取件。

### Stone3PL 运输通常需要多久？

Stone3PL 网站未公布参考运输时效，请以网店卖家提供的预计送达时间为准，超期未到请联系卖家。

### 如何联系 Stone3PL？

可发邮件至 `support@stone3pl.com` 或致电中国深圳热线 `86-0755-81466633`。订单售后请先联系下单卖家。

:::sources
- [Stone3PL 网站首页](https://stone3pl.com/) – 中国至全球电商物流、DDP 清关与完税交货、退件重发服务及北美/亚太/欧非中东海外仓
- [Stone3PL 物流追踪页](https://stone3pl.com/?route=services/track) – 英文查询页、单次最多 40 单及支持追踪的合作承运商
- [关于 Stone3PL](https://stone3pl.com/index.php?route=services/about_us) – Stone Group、深圳总部、10 余年经验与 8,000+ 签约客户
- [联系 Stone3PL](https://stone3pl.com/index.php?route=services/contact_us) – 深圳总部地址、邮箱与电话
- [Stone3PL 隐私政策](https://stone3pl.com/index.php?route=services/privacy) – 客服邮箱 `support@stone3pl.com`
- [European Commission: 欧盟海关改革](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 自 2026 年 7 月 1 日起 150 欧元以内每类商品 3 欧元关税、自 2026 年 11 月 1 日起每类商品 2 欧元欧盟处理费
- [European Commission: 3 欧元关税与欧盟处理费指南（PDF）](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 欧元费用按商品类别且不限货值计收、商品类别定义
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – `S` + 18 位单号形态、轨迹文案与阶段映射、总状态、中国时间及目的国末端交接
:::
