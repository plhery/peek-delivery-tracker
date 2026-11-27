---
title: GLS US（GSO）快递查询：单号、状态与派送指南
description: 查询 GLS US（原 GSO / Golden State Overnight）美国包裹：SH 开头 20 位单号、状态含义、派送时间、签收规则、二次派送及客服电话。
slug: gls-us-tracking
published: 2026-11-27
updated: 2026-11-27
---

GLS US（General Logistics Systems US, Inc.）是 GLS 集团在美国的包裹快递公司，主要在美国西部地区派送。Peek 的快递目录同时将其收录为 GLS USA、GLS United States、General Logistics Systems US、GSO 以及 Golden State Overnight（GLS 于 2016 年收购该公司）。要查询 GLS US 包裹，请将单号粘贴到上方输入框，或前往 [GLS 的美国查询页](https://gls-group.com/US/en/send-and-receive/track-a-parcel/)输入查询。

## GLS US 快递单号是什么样的

Peek 目录识别一种 GLS US 单号格式。以下以虚构数字举例：

| 粘贴内容（虚构示例） | Peek 的处理方式 |
| --- | --- |
| `SH605182704936815247` | `SH` 加 18 位数字，共 20 位字符。在 Peek 目录中只有 GLS US 使用此格式，但属于弱匹配，因此 Peek 会先向 GLS US 查询确认后再显示其名称。 |
| `sh 6051 8270 4936 8152 47` | 同一个单号。小写字母、空格和连字符不影响识别。 |
| 其他格式的 GLS US 单号 | Peek 不会识别。请自己选择 GLS US（见下文“使用 Peek 追踪 GLS US 包裹”）。 |
| 路径包含 `/US/` 的 gls-group.com 链接，或来自 gls-us.com、gso.com 的查询链接 | Peek 会直接提取链接中的单号并立即识别为 GLS US（显示“识别出的快递公司”）。 |

**单号在哪里找。** 可在商家的发货邮件、订单页面查看；若商家为你登记了通知，也会出现在 GLS 发送的邮件或短信中。每个箱子都有独立面单，因此多箱订单可能对应多个单号。GLS 的无单号查询需要寄件人账号与参考号，请向商家索取。另见[如何查找快递单号](guide:find-tracking-number)（英文）。普通订单号无法用于查询。

**与欧洲 GLS 不同。** GLS US 虽然隶属 GLS 集团，但 Peek 是通过 GLS US 独立查询系统追踪其包裹。路径含 `/EU/` 或 `/DE/` 的 gls-group.com 链接会被 Peek 归入 [GLS Germany](carrier:gls-de)，而非 GLS US。如果商家邮件写的是欧洲 GLS 分公司或其他快递公司，请前往对应公司查询。关于其他快递的单号样式，可参阅[快递单号格式指南](guide:tracking-number-formats)（英文）。

## GLS US 物流状态含义

GLS US 查询页的每条扫描都带有日期和时间，且大多数会标明地点（城镇、州和国家）。Peek 代码识别的扫描文本均为全大写英文，按包裹经过的顺序如下：

| GLS US 显示状态 | 对你的含义 | Peek 显示 |
| --- | --- | --- |
| `LABEL CREATED` | 寄件人已生成运单。GLS US 可能尚未拿到包裹。此条扫描不带地点。 | “已生成面单” |
| `ARRIVAL SCAN - DELIVERY SCHED FOR 07/15/2026` | GLS US 已完成到达扫描。末尾日期（月在前）为 GLS US 排定的预计派送日期。 | “运输中” |
| `SCANNED AT DELIVERY DEPOT` | 已在负责派送的站点完成扫描。 | “运输中” |
| `ON ROUTE FOR DELIVERY` | 今日正在派送路线途中。 | “派送中” |
| `SHIPMENT DELIVERED` | 已送达。 | “已签收” |

**整体货件状态。** 在扫描记录上方，GLS US 页面会用简短词语概括包裹阶段：`label created`、`in transit`、`out for delivery`、`delivered` 或 `returned`。如果该整体状态比单条扫描更靠前，Peek 会以其为准：整体概括为 `delivered` 时，即使最新扫描尚未写明已送达，Peek 也会显示“已签收”；概括为 `returned` 时则显示“已退回寄件人”。

**其他记录。** Peek 代码未收录 GLS US 针对派送未成功、异常、暂存或自提点的专用措辞。遇到未收录的文本时，Peek 会按 GLS US 原文显示，并按通用规则归类阶段。更多内容见[物流状态详解](guide:tracking-statuses)（英文）。

如果在 `LABEL CREATED` 之后停滞满一天以上，Peek 会提示“寄件人还没有把包裹交给 GLS US，交寄后就会开始跟踪。”请联系商家确认。

**GLS 邮件与短信提醒。** 如果商家制单时为你开启了提醒，GLS 会在站点入库扫描（附预计送达日期）、延误或派送失败、派送当日（附预计送达时间）以及送达完成时向你发送通知。

## GLS US 派送时间、签收与派送未成功

**收件地址。** GLS 不派送至邮政信箱（P.O. Box），且按美国邮编（ZIP code）分拣包裹，邮编错误会导致延误。改派地址须经寄件人授权，请联系商家办理。参阅[如何修改快递收件地址](guide:change-delivery-address)（英文）。

**派送日期。** 周一至周五派送；仅部分地区在寄件人指定时提供周六派送；周日不派送。GLS 在美国六个法定假日休息：元旦、阵亡将士纪念日、独立日、劳动节、感恩节和圣诞节。原定于假日送达的包裹顺延至下一工作日派送。

**派送时段。** 取决于寄件人所选服务。Priority 服务在多数地区于次一工作日上午 10:30 前送达，Ground 服务于下午 5:00 前送达（偏远农村地区为下午 6:00 前）。跨远距离路线中，Priority 于排定派送日上午 10:30 前送达，Ground 于当日结束前送达。周六派送在多数地区于上午 10:30 前送达。

**签收要求。** 由寄件人选择。默认情况下，商业地址由司机获取签收；独立住宅在无人签收且环境安全干燥时会直接放件；公寓楼则放在门前，或在司机无法进入楼内时交由物业或租赁办公室。若选了 Signature Required，无人签收不会放件；若选了 Adult Signature Required（酒类、烟草等必填），签收人须年满 21 周岁。

**派送未成功。** 错过派送或收到门卡时，可通过 GLS 邮件或短信中的链接重新预约派送，或拨打下方收件人客服电话。GLS 的页面未公布具体尝试次数或保管天数，且其 GLS Point 仅作为寄件人的交寄点提供。另见[派送未成功](guide:missed-delivery)（英文）和[未领取包裹处理](guide:uncollected-parcel)（英文）。

## GLS US 查不到、不更新、丢失或退回怎么办

**暂未查到。** 当 GLS US 查不到单号时，系统会返回未知提示。如果你把 SH 单号交给 Peek 识别，Peek 会显示“等待 GLS US 的首次扫描”；若由你自己选定 GLS US，则显示“等待 GLS US 更新”，并持续重试。新制运单常需一段时间才会同步。如果交给 Peek 识别的 SH 单号添加两天后仍无记录，Peek 会提示“仍在等待快递公司的首条动态”；如果是你自己选定 GLS US 且两天后对方仍不认识该单号且其他渠道也无响应，Peek 会提示“两天过去了，快递公司仍查不到这个单号。请检查单号、更换快递公司，或删除这个包裹。”此后 Peek 改为每天查一次，添加满一周后改为每周查一次。请对照商家邮件核对单号，并询问商家是否已真正发货。

**多日无更新。** 若包裹停留在“已生成面单”、“已揽收”或“运输中”连续四天无新扫描，Peek 会按该条扫描日期标出“自……起没有物流更新”，并提示包裹在周末常常暂无动静，持续向 GLS US 查询并提供“向 GLS US 寻求帮助”链接。超过三周仍无新记录时，则改为提示“已超过三周没有新动态。如果还没收到，请联系卖家。”另见[物流为什么停止更新](guide:tracking-not-updating)（英文）。

**显示已送达但没收到。** 状态为 `SHIPMENT DELIVERED` 却没看到包裹？先在 GLS 查询页查看签收人姓名，再检查门廊四周、收发室、物业租赁办公室并询问邻居，随后致电 GLS 收件人专线并联系商家。参阅[显示已签收但未收到](guide:delivered-not-received)（英文）。

**丢失或破损。** 请先联系商家：商家负责下单运输，可向 GLS US 交涉或为你补发。只有寄件人可在发货后 90 天内提交索赔，GLS 力争在 45 天内办结。涉及破损时，GLS 会要求寄件人提供商品、内部缓冲包装及外箱照片，请务必在丢弃包装前拍照留存。参阅[包裹丢失](guide:lost-parcel)（英文）和[包裹破损](guide:damaged-parcel)（英文）。

**退回寄件人。** 显示“已退回寄件人”说明包裹已退回商家，请联系商家退款或重新发货。参阅[包裹被退回寄件人](guide:returned-to-sender)（英文）。

## GLS US 客服与联系方式

根据 GLS US [客户支持页面](https://gls-group.com/US/en/support/customer-support/)：

- **电话：** 1-800-322-5555，转按 2（收件人支持：查询、派送问题与派送协助）。
- **服务时间：** 美国太平洋时间周一至周五 7:00 至 19:00，周六 7:00 至 16:00。
- **在线客服：** GLS 的 FAQ 提供在线聊天。GLS 未向收件人提供联系邮箱。

> **防范诈骗。** GLS 绝不会通过电子邮件要求收件人以银行卡、线上支付或银行转账方式支付货款；不过对于跨境入境包裹，GLS 或其代理可能会收取关税和税费。正牌 GLS 网站仅限 gls-group.com 及其直接链接的站点。如遇仿冒信息，请举报至 security@gls-us.com，并参阅[快递诈骗短信识别](guide:delivery-scam-texts)（英文）。

## 使用 Peek 追踪 GLS US 包裹

**SH 开头单号。** 将单号粘贴到上方输入框。在 Peek 向 GLS US 查询期间，快递公司一行会显示“正在查询物流服务…”。如果 GLS US 查到该包裹，这一行会显示 GLS US“查到了这个包裹”，Peek 将包裹归入其名下；如果暂未查到，则显示“暂未找到 · Peek 会继续查询”。你仍可直接添加，在 GLS US 首次扫描前它会显示快递公司为“可能是 GLS US”。

**其他格式单号。** 同样可直接粘贴：Peek 跟踪 4,500 多家快递公司。然后按以下步骤自己选择 GLS US：

1. 点快递公司一行旁的“更换”，搜索 GLS US（搜索“GSO”、“Golden State Overnight”、“General Logistics Systems US”或“United States”均可找到）。
2. 选择 GLS US，该行即显示“你选择的”，随后开始追踪。

对已添加的包裹，可使用“更换快递公司”。Peek 仅就长度为 5 至 20 位字母数字的单号向 GLS US 发起查询。

**显示内容。** Peek 仅凭单号即可查询 GLS US 的查询页，无需账号或邮编。页面展示当前阶段、带城镇与州地图定位的逐条扫描，以及在未送达或未退回前由 GLS US 提供的预计送达日期。在你自己的包裹详情页中，还会于“收件人”栏显示 GLS US 给出的收件人姓名，并将地图路线终点标在收件城镇；通过分享链接查看时，这两项均会被隐藏。Peek 不显示签收人、签名、签收证明及寄件人参考号，也不会把 GLS US 包裹交接给其他快递。如果 GLS US 在同一单号下存在多票货件，Peek 会跟进包含最新扫描的那一票。包裹页面的“打开 GLS US 官网”按钮可直接跳转至 GLS 美国查询页查看该单号。

**时间换算。** GLS US 的扫描时间不含时区信息。Peek 会按扫描所在城镇的当地时间解读；若无法识别城镇，则按所在州的主时区解读。若某条扫描未标注城镇和州，Peek 会沿用最近一条可定位的前序扫描时区，否则取最近一条后续扫描时区；两者皆无时默认按太平洋时间计算。所有时间最终都会转换为你设备所在的时区显示。

**GLS US 无响应时。** 如果 GLS US 查询页暂时无法访问，Peek 会转而向[通用物流追踪服务](guide:universal-tracker)（英文）查询，并按对方原文展示轨迹。

**查询频率。** 当你在 Peek 中打开包裹页面，或在派送日开启通知时，Peek 最高每 10 分钟检查一次，在 GLS US 扫描进入派送路线当天自当地早上 6 点起每 2 分钟检查一次；其他时候每小时一次。连续两天无新动态后（处于“已生成面单”或“已揽收”时为 12 小时）改为每小时一次；在“已生成面单”满七天无新动态后改为每 6 小时一次。通知可选“每次扫描”、“重要环节”或“仅派送当天”。

Peek 无法代你联系 GLS US 或商家，也无法更改派送或提交索赔。

## GLS US 快递查询常见问题

### GSO 和 GLS US 是同一家公司吗？

是的。GLS 于 2016 年收购了 Golden State Overnight（GSO），如今 gso.com 会直接跳转至 GLS US 的网站。GSO 成立于 1995 年，最初在加利福尼亚、亚利桑那、内华达和新墨西哥州提供快递服务。Peek 将两者视为同一家快递公司：目录中把 GSO、Golden State Overnight、GLS USA、GLS United States 和 General Logistics Systems US 均列为 GLS US 的别名，搜索任意一个都能找到，且来自 gso.com 和 gls-us.com 的查询链接也会识别为 GLS US。

### GLS US 单号是什么格式的？

Peek 识别的格式是 `SH` 加 18 位数字，例如 `SH605182704936815247`（虚构示例）。GLS 的页面没有给出单号格式，且可能存在其他格式：照常粘贴并点“更换”选择 GLS US 即可。

### GLS US 的到达扫描（ARRIVAL SCAN）是什么意思？

`ARRIVAL SCAN - DELIVERY SCHED FOR 07/15/2026` 表示 GLS US 已对包裹完成到达扫描，并排定了末尾所示日期（月/日/年）进行派送。Peek 会将其显示为“运输中”。

### 为什么 Peek 显示“等待 GLS US 的首次扫描”？

你的单号符合 GLS US 的格式，且 Peek 已经向 GLS US 查询过，但对方系统中尚未收录该单号。新创建的运单常会出现这种情况，Peek 会继续查询。如果满一周仍未查到，Peek 会提醒你向寄件人核对单号或更换其他快递公司。

### GLS US 和欧洲的 GLS 是一样的吗？

它们同属 GLS 集团，但 Peek 将 GLS US 视为独立的快递公司并查询 GLS US 自己的系统。欧洲各地的 GLS 分公司（如 [GLS Germany](carrier:gls-de)）各有独立系统，请按商家发货邮件中注明的 GLS 公司进行追踪。

:::sources
- [GLS US: Track a parcel](https://gls-group.com/US/en/send-and-receive/track-a-parcel/) – US tracker, search without a number, who signed
- [GLS US: About GLS US](https://gls-group.com/US/en/about-gls-us/) – GLS Group's US company, Western U.S., GSO's history
- [GSO: gso.com](https://www.gso.com/) – redirects to GLS US's site
- [GLS US: Domestic shipping services](https://gls-group.com/US/en/Shipping/shipping-services/domestic/) – delivery times, Saturday in some areas
- [GLS US: Holiday schedule](https://gls-group.com/US/en/resources/holiday-schedule/) – delivery days, holidays
- [GLS US: Parcel Tariff](https://gls-group.com/US/en/parcel-tariff/) – no Sunday, holidays, signatures, ZIP codes, one label per box, claims within 90 days
- [GLS US: FAQ](https://gls-group.com/US/en/support/faq/) – P.O. boxes, rerouting, rescheduling, door tags, notifications, live chat, claims
- [GLS US: Claims](https://gls-group.com/US/en/support/claims/) – shipper files, 45 days, photos
- [GLS US: Customer support](https://gls-group.com/US/en/support/customer-support/) – recipient line, hours
- [GLS US: Find a GLS Point](https://gls-group.com/US/en/Shipping/shipping-tools/find-drop-point/) – drop-off only
- [GLS US: Security advice](https://gls-group.com/US/en/security-advice/) – payment requests, duties and taxes, genuine sites, reporting
- [Universal Parcel Scraper: GLS US](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/gls-us) – names, the SH shape and its weak match, tracking links, the tracker's scans and shipment statuses with their stages, not-found answer, expected delivery day, several shipments under one number, scan clocks, what Peek leaves out
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – GLS's European networks and their links, tracking services as a fallback, scans dated on the clock of their town
:::
