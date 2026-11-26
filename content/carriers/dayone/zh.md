---
title: DayOne（DOT）中国发运追踪：单号、状态与末端派送商
description: 追踪中国发出的 DayOne（DayOneTrack）DOT 包裹：DOT0/DOT1 单号格式、中英文状态含义、燕文与当地邮政交接及延迟排查。
slug: dayone-tracking
published: 2026-11-26
updated: 2026-11-26
---

DayOne 是一家从中国为网店代发订单的履约仓库。它本身并非快递公司——官网自述其为“订单履约仓库，而非快递派送服务商”（"the order fulfillment warehouse, not a courier delivery service provider"）。实际运输由其他物流公司完成，而 DayOne 自家的追踪网站 [DayOneTrack](https://www.dayonetrack.com/)（支持中英文）会汇总展示它们的扫描记录。您可在该网站查询 `DOT` 单号，或将单号粘贴到上方输入框中。单号由商家随订单发货信息提供。

## DayOne 追踪单号长什么样

DayOne 未公开单号格式规则。Peek 识别为 DayOne 的常见格式如下（数字均为虚构示例）：

| 单号 | 说明 |
| --- | --- |
| `DOT0482917364058291` | `DOT` + 16 位数字：DayOne 单号。 |
| `DOT1482917364058291` | `DOT1` + 15 位数字：同属 DayOne 单号；DayOne 追踪页会在另一套系统中查询该类单号。 |
| `DOT048291736405829` | 仅有 15 位数字：不符合 DayOne 格式，Peek 不会识别为 DayOne，请检查是否少了一位数字。 |

在 Peek 中单独粘贴单号时，小写、空格或连字符均不影响识别。但若整段粘贴物流消息，请保持 `DOT` 与数字紧连：若 `DOT` 后紧跟空格，Peek 可能仅读取后面的数字并匹配到其他承运商。

**在哪里找到单号。** 请查看商家发货邮件或订单详情页。找不到单号？请参阅[如何找到您的追踪单号](guide:find-tracking-number)（英文）。

**在 DayOne 官网查询。** 一次最多可同时查询 10 个单号（每行一个）。官网仅支持含 `DOT0` 或 `DOT1` 的单号，其他单号（如当地邮政单号）均显示 `Not found`。查询结果旁有时会显示商家的订单参考号（`Reference Number`）及目的地国家。

## DayOne 追踪状态含义

DayOne 追踪页为每件包裹提供一个中英文总状态，并在 `Package Track` 下按时间倒序列出扫描明细：

| DayOne 总状态 | 对您的实际含义 | Peek 显示阶段 |
| --- | --- | --- |
| `Not found`（查询不到） | DayOne 尚无此单号记录，页面会补充提示“The order information has not been updated yet”。 | “等待 DayOne 更新” |
| `Info received`（收到信息） | 物流数据已录入，包裹可能尚未离开仓库。 | “已创建面单” |
| `In transit`（运输途中） | 运输途中。 | “运输中” |
| `Pick up`（到达待取） | 包裹已送达自提点，等待您领取。 | “待取件” |
| `Out for Delivery`（派送途中） | 正在派送中。 | “派送中” |
| `Undelivered`（投递失败） | 派送尝试未成功。 | “派送未成功” |
| `Delivered`（成功签收） | 已送达。 | “已送达” |
| `Alert`（可能异常） | 可能出现运输异常。 | “需要关注” |
| `Expired`（运输过久） | 运输耗时过长。 | 以最新扫描状态为准 |

Peek 优先依据自身能识别的最新扫描来判定阶段，仅在无法识别任何扫描文本时才采用 DayOne 的总状态。

每条扫描包含时间、地点以及当前经手承运商的原文：中国境内常见为燕文（Yanwen），入境后为您所在国家的邮政。按包裹常见顺序如下：

| DayOne 显示内容 | 对您的实际含义 | Peek 显示阶段 |
| --- | --- | --- |
| `Order processed by shipper` | 燕文已收到订单电子数据，但尚未扫描实物包裹。 | “已创建面单” |
| `Yanwen Pickup Scan`、`Processing information input` | 燕文已在中国揽收并开始处理。 | “承运商已揽收” |
| `Yanwen facility - Outbound`、`Arrived at domestic terminal station` | 已离开燕文分拣中心，抵达中国国内出境场站准备出口报关。 | “运输中” |
| `Port of departure - Received by carrier`、`International shipment release - Export`、`Port of departure - Departure` | 抵达起运口岸、通过中国出口清关（非收件国海关）并起飞离境。 | “运输中” |
| `Port of destination - Arrival`、`ARRIVED AT DESTINATION AIRPORT` | 已飞抵您所在国家或附近枢纽机场，接下来进入进口清关。 | “运输中” |
| `CLEARED BY CUSTOMS`、`COLLECTED FROM AIRPORT TERMINAL`、`ARRIVED AT FACILITY IN DESTINATION COUNTRY` | 收件国海关已放行，包裹已从机场提货送往当地处理设施。 | “运输中” |
| `RECEIVED BY AUSTRALIA POST FOR TRANSPORTATION TO PROCESSING FACILITY` | 澳大利亚包裹示例：已移交澳大利亚邮政。 | “运输中” |
| `ITEM PROCESSED AT POST FACILITY`、`PROCESSED THROUGH AUSTRALIA POST FACILITY`、`TRANSFERRED` | 由当地邮政分拣并继续发运。 | “运输中” |
| `WITH AUSTRALIA POST FOR DELIVERY TODAY` | 今日正在派送中。 | “派送中” |
| `DELIVERED WITH SAFE DROP` | 已送达并放置于收货地址的安全位置。 | “已送达” |

`SHIPPING INFORMATION RECEIVED`、`SHIPPING INFORMATION RECEIVED BY AUSTRALIA POST` 及 `SHIPPING INFORMATION APPROVED BY AUSTRALIA POST` 仅表示当地邮政收到电子预报，有时会晚于其他扫描出现，并不代表进度倒退，Peek 会保留更靠后的进展。

如果唯一一条记录是 `Shipment has begun. In transit to destination city`，说明 DayOne 已将包裹交给[云途物流（YunExpress）](carrier:yunexpress)，后续轨迹需用云途单号（`YT` + 16 位数字）在云途官网查询。`Clearence processing completed - Export` 即云途原样拼写的出口清关放行记录。

## DayOne 包裹究竟由谁派送

DayOne 本身不标明您所在国家的末端派送商，但从扫描中可以看出经手方：中国境内由燕文发运，随后交由收件国邮政（例如寄往澳大利亚时交由 [Australia Post](carrier:australia-post)）。

DayOne 未公布派送尝试、通知卡或自提点规则，这些均遵循末端承运商规定。出现 `Pick up` 或 `Undelivered` 后，请留意当地承运商的通知卡或消息，并参阅[错过派送处理指南](guide:missed-delivery)（英文）和[未领取包裹指南](guide:uncollected-parcel)（英文）。

**欧盟海关规定。** 自 2026 年 7 月 1 日起，从欧盟以外网购且价值不超过 150 欧元的包裹按每项商品征收 3 欧元关税；自 2026 年 11 月 1 日起，无论货值高低，按每项商品加收 2 欧元欧盟处理费（“一项商品”指税则归类、描述及原产地要求一致的商品）。DayOne 官网未就关税、手续费或代收方作出说明。其他国家请遵循当地规则，详见[包裹滞留海关指南](guide:customs)（英文）。

## DayOne 包裹无更新、丢失或退回怎么办

DayOne 明确指引买家联系下单商家，其追踪页提示：“For all package logistics, lost items, refunds, and resending related issues, please contact the customer service of the ordering store.”（一切物流、丢件、退款与补发问题请联系下单网店客服）。

**显示 `Not found`。** DayOne 尚未录入该单号。请核对是否为 `DOT` + 16 位数字；确认无误则可能是尚未发运。Peek 会显示“等待 DayOne 更新”并持续查询。若两天后 DayOne 仍查无记录且其他服务也无结果，Peek 会提示“两天后，快递公司仍查不到这个单号。请核对单号、更换快递公司或删除该包裹。”此后改为每日查询一次，满一周后每周一次。如数日无动静，请向商家确认。

**仅显示 `Info received` 或 `Order processed by shipper`。** 尚未有任何实物扫描；若维持多日，请向商家核实发货时间。

**多日无新扫描。** 当最新扫描距今已满四天且包裹仍处于“已创建面单”、“承运商已揽收”或“运输中”时，Peek 会按该次扫描日期加以标记，例如“自 10 月 12 日（周一）起无物流更新”。包裹页面随即会提示“包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 DayOne 查询。”并提供指向 DayOne 页面的“从 DayOne 获取帮助”链接（一旦 Peek 切换跟进末端邮政，若 Peek 收录了该邮政帮助页，链接会相应改为该邮政）。超过三周仍无新扫描时，页面会提示“超过三周没有新动态。如果包裹仍未送达，请联系卖家。Peek 会持续向 DayOne 查询。”详见[为何物流停止更新](guide:tracking-not-updating)（英文）与[中国跨境包裹追踪指南](guide:tracking-from-china)（英文）。

**显示 `Alert` 或 `Expired`。** 请先查看最新扫描详情，再携带 `DOT` 单号联系商家协助。

**丢失、损坏或退回。** DayOne 不面向收件人提供理赔通道，请直接联系商家申请退款或补发。详见[包裹丢失处理指南](guide:lost-parcel)（英文）和[包裹退回发件人](guide:returned-to-sender)（英文）。

**显示已送达但没收到。** `DELIVERED WITH SAFE DROP` 表示包裹已放置在收货地址附近。请先检查房屋四周并询问邻居，随后联系商家。详见[显示已签收但未收到包裹](guide:delivered-not-received)（英文）。

## DayOne 联系方式

摘自 [DayOne 官方追踪页](https://www.dayonetrack.com/)：

- **包裹相关问题：** 按 DayOne 要求直接联系下单商家。
- **商务合作邮箱：** `info@dayone-shipping.com`（DayOne 注明仅用于商务合作咨询，不受理个人查件）。

DayOne 追踪页面未提供电话号码、在线客服、联系表单或办公地址，并声明仅保存运单号而不存储订单详情。

## 使用 Peek 追踪 DayOne 包裹

[Peek](/) 会根据 `DOT` + 16 位数字立即识别 DayOne（“识别出的快递公司”），也支持粘贴含 `trackNo=` 的 dayonetrack.com 追踪链接。其他单号同样可直接粘贴（Peek 支持超过 4,500 家承运商）；若单号符合多家承运商，查询时会显示“正在查询物流服务…”。如果 Peek 未自动识别为 DayOne，可点击“更改”在“全部快递公司”下选择 DayOne（搜索“Day One”同样能找到），或在已添加的包裹页点击“更改承运商”。

对于 `DOT0` 单号，Peek 直接读取 DayOne 官方追踪页，展示状态、每条带地点的扫描、目的地国家与签收时间；对于 `DOT1` 单号则改为向追踪服务查询。

当 DayOne 为扫描提供时区偏移时，Peek 会直接沿用；未提供时则按扫描地点或相邻记录推断时区（提示已抵达目的国后按收件国时区），并统一转换为您设备的本地时间显示。Peek 会过滤掉邮编、签收人姓名以及商家的 `Reference Number`。DayOne 不提供预计送达日期或重量。

虽然 DayOne 网页不直接显示末端承运商单号，但其底层接口有时会返回该单号。此时 Peek 也会同步查询对应公司——例如对国际邮政单号（2 位字母 + 9 位数字 + 2 位字母）查询本国邮政（如 Royal Mail、USPS、Canada Post、An Post 或 Australia Post），或查询唯一匹配该单号格式的承运商（如 `YT` 单号对应 YunExpress）。一旦对方出现不早于 DayOne 最新记录的扫描，Peek 便切换至该承运商继续跟进，在 `DOT` 单号旁并列展示其单号，并完整保留 DayOne 历史。

对于 `DOT0` 单号，在 Peek 中打开包裹页或派送当日开启通知时，最多每 10 分钟检查一次；在 DayOne 扫描出车派送当天，只要官网正常响应，从当地时间早上 6 点起每 2 分钟检查一次。连续两天无新动态（或停留在“已创建面单”/“承运商已揽收”满 12 小时）后改为每小时一次；停留在“已创建面单”满七天后每 6 小时一次。通过追踪服务跟进时（如 `DOT1` 单号），每小时检查一次，最新扫描满一天后每 3 小时一次，停留在“已创建面单”满七天后每 6 小时一次；派送当天打开页面或开启通知时最多每 15 分钟一次。

Peek 支持“每次扫描”、“重要节点”或“仅送达通知”提醒，但无法代您联系 DayOne 或商家。

## 关于 DayOne 追踪的常见问题

### DayOne 是一家快递公司吗？

不是。DayOne 声明其为“订单履约仓库，而非快递派送服务商”。实际运输由其他物流公司承担——在 DayOne 的扫描中通常先由中国境内的燕文承运，再移交您所在国家的邮政。

### 在哪里可以查询 DOT 单号？

您可以在 DayOne 官网追踪页面 [dayonetrack.com](https://www.dayonetrack.com/) 查询（支持中英文，一次最多查 10 个单号），或直接粘贴到 Peek 中。

### 为什么 DayOne 追踪页面显示 Not found？

说明 DayOne 尚无该单号的记录。请确认输入了 `DOT` 以及全部 16 位数字；若单号无误，请等待一两天后再向商家确认包裹是否已发出。

### 为什么 DayOne 追踪页上的时间看起来顺序错乱？

因为每条扫描仅显示发生地的当地时间且不带时区，而官网直接按字面时间排序。北半球冬季时北京时间比伦敦快 8 小时、比纽约快 13 小时，因此落地当地的扫描反而可能显得比离开中国的最后一条记录更早；而南半球夏季悉尼比北京快 3 小时，当地扫描又会显得更晚。Peek 会将全部时间统一换算到您设备的时区显示。

:::sources
- [DayOne: Dayone Tracking](https://www.dayonetrack.com/) – 履约仓库而非快递商、售后找商家、隐私声明、商务邮箱、中英文界面、一次最多 10 单、DOT0/DOT1 查询、状态列表、结果字段及时间说明
- [欧盟委员会：欧盟海关改革](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 自 2026 年 7 月 1 日起 3 欧元关税、自 2026 年 11 月 1 日起 2 欧元欧盟处理费
- [欧盟委员会：3 欧元关税与欧盟处理费指南（PDF）](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 无论货值每项 2 欧元及商品项定义
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – 单号格式、链接与查询方式、扫描文案与代码、时区推算、末端承运商单号提取与交接逻辑
:::
