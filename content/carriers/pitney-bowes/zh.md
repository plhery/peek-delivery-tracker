---
title: Pitney Bowes 物流查询：UPAA、UPBE 与 PBX 单号详解
description: 查询 Pitney Bowes 跨境及 eBay 国际包裹：UPAA/UPBE/PBX 单号格式、各阶段状态与 Interpost 清关含义、末端派送及常见问题。
slug: pitney-bowes-tracking
published: 2026-11-27
updated: 2026-11-27
---

Pitney Bowes 从事国际跨境包裹转运：在 Peek 的代码中，其单号对应从美国和英国发往海外的 eBay 订单，以及其他跨境发运包裹。根据 Peek 代码中的轨迹记录，包裹先经过 Pitney Bowes 转运中心处理，再发往买家所在国并交由当地合作伙伴（例如目的国邮政）派送。单号以 `UPAA`、`UPBE` 或 `PBX` 开头，由卖家或交易平台提供：你可以直接粘贴到上方输入框查询，Pitney Bowes 官方查询页面位于 trackpb.shipment.co。

## Pitney Bowes 快递单号是什么格式的

Peek 识别 Pitney Bowes 的三种单号格式，长度均为 14 至 20 位字符。以下以虚构数字举例：

| 单号（虚构示例） | 含义 |
| --- | --- |
| `UPAAK482917364058291` | `UPAA`、1 位字母加 15 位数字：在 Peek 代码中代表从美国或英国发出的 eBay 国际包裹。 |
| `UPBE48291736A4F02819` | `UPBE` 加 16 位字符（数字或字母 A 至 F）：从英国发出的 eBay 包裹。 |
| `PBXHK482917365` | `PBX`、2 位字母加 9 位数字：其他 Pitney Bowes 跨境包裹。 |
| `PBXHK48291736A4F0281` | `PBX`、2 位字母加 15 位字符（数字或字母 A 至 F）：同类的较长格式。 |
| `UPAA482917364058291` | `UPAA` 后缺少字母：不符合 Pitney Bowes 格式，Peek 无法识别承运商；即便手动选定 Pitney Bowes，Peek 也会提示其“不会签发这种格式的单号”。请核对是否漏输字符。 |

在 Peek 中粘贴时，空格、连字符和小写字母均不影响识别。Peek 目录同时将 Pitney Bowes 收录为 Pitney Bowes Global Ecommerce、Pitney Bowes Expedited Parcel Service、eBay Global Shipping Program 和 PBI。

**单号在哪里找。** 请查看卖家的发货邮件或订单页面。eBay 订单可参阅 [eBay 订单查询](guide:ebay-orders)（英文）；其他订单见[如何查找快递单号](guide:find-tracking-number)（英文）。

## Pitney Bowes 物流状态详解

Pitney Bowes 查询页按时间倒序列出每条记录，附带日期、时间以及多数情况下的地点。由于其记录不带状态代码，Peek 直接根据英文措辞判断阶段。按流转顺序如下：

**在转运中心（Shipping Center）。** Pitney Bowes 自有轨迹：

| Pitney Bowes 显示状态 | 对你的含义 | Peek 显示 |
| --- | --- | --- |
| `Tracking details uploaded`、`Label Created` | 卖家已向 Pitney Bowes 上传包裹信息，Pitney Bowes 尚未收到包裹。 | “已创建运单” |
| `Package not received by Hub or First Mile Carrier yet` | 面单已生成，但转运中心和头程快递均尚未扫描揽收。 | “已创建运单” |
| `Processing at Shipping Center` | Pitney Bowes 转运中心已收到包裹并正在处理。 | “运输途中” |
| `First Dimension Event`、`Second Dimension Event` | 转运中心正在测量包裹尺寸。 | “运输途中” |
| `Parcel Documentation and Labeling`、`Customs Documentation and Labeling` | 转运中心正在准备出口单证和贴标，尚未进入实际海关查验。 | “运输途中” |
| `Container Assignment`、`Container Closed` | 已配载装箱并封箱准备发运。 | “运输途中” |
| `Shipped from Global Shipping Center to Destination`、`Departed for Destination` | 已离开转运中心发往目的国。 | “运输途中” |
| `International Carrier` | 已交由国际承运商运往海外。 | “运输途中” |
| `In Transit` | 正在运输途中（有时不附地点）。 | “运输途中” |

**后续转运、清关与交接。** 在 Peek 代码记录的英国出发轨迹中，离开 Pitney Bowes 英国转运中心的包裹由合作伙伴 Interpost 接续办理进口申报并移交目的国邮政。该阶段常见状态（前两条往往在英国中心处理时生成，不附地点）：

| Pitney Bowes 显示状态 | 对你的含义 | Peek 显示 |
| --- | --- | --- |
| `Item processed at Interpost` | 英国转运中心正在为 Interpost 处理包裹。 | “运输途中” |
| `Interpost handed item over to postal company` | Interpost 将包裹移交目的国邮政派送。该记录常与上一条同时生成，此时包裹可能尚未离开转运中心。 | “运输途中” |
| `Customs Clearance` | 已在目的国提交进口报关单证。属于电子申报，可能在离开英国数小时内即出现，早于包裹实际入境。 | “清关中” |
| `B2C ClearanceStatus4` | 进口清关已完成。Peek 参照 Interpost 官方译法显示为 `Customs import cleared`。 | “运输途中” |
| `B2C ClearanceStatus` 加其他数字 | Peek 代码未知含义的清关代码，Peek 显示为 `Customs clearance update`。 | “清关中” |
| `Cleared customs` | 已放行通过海关。 | “运输途中” |
| `Package is created in the system` | 下一承运商在系统里建档，不代表实物移动。 | 状态不变 |

**目的国邮政轨迹。** 交接后，查询页会转发派送邮政的扫描记录：

| Pitney Bowes 显示状态 | 对你的含义 | Peek 显示 |
| --- | --- | --- |
| `Item in transit by postal company` | 目的国邮政正在转运包裹。 | “运输途中” |
| `Parcel is out for delivery` | 正在派送途中。 | “正在派送” |
| `Item is waiting for addressee at local post office` | 已在当地邮局等你领取。 | “待取件” |
| `Delivered by Consignee Pickup` | 已由收件人本人自提签收。 | “已送达” |
| `Item has been delivered` | 已送达。 | “已送达” |

`Departed carrier facility` 和 `Processed by Carrier` 同样归为“运输途中”。其余记录按 Peek 通用规则归类；未识别文本原样展示并归为“运输途中”，但如果包裹已进入派送或后续阶段，则维持当前阶段不变。

**整体货件状态。** 查询页还会给出一个总状态。当总状态为已送达时，即使最新单条记录未写明，Peek 也会显示“已送达”；其他总状态仅在没有任何单条记录提供阶段时才起作用。总状态中的异常标记（曾在收件人正常自提的包裹上误现）绝不会覆盖单条记录的判断。

## 谁负责派送 Pitney Bowes 包裹

由于 Peek 无法抓取 Pitney Bowes 官网，本页不列出其合作伙伴或国家清单。Peek 代码显示由 Interpost 承接离开英国转运中心的包裹并转交目的国邮政，其中一例发往意大利的包裹由 [Poste Italiane](carrier:poste-italiane) 派送。Pitney Bowes 查询页会标明接手承运商名称，有时还会在对应记录上附上跳转至该承运商查询页及对方单号的链接。

**派送尝试与自提。** 派送次数、通知卡和保管期均遵循末端派送邮政的规则，请向当地邮政确认或在 Peek 中查阅该邮政专题页。另见[错过快递派送](guide:missed-delivery)（英文）和[未领取包裹处理](guide:uncollected-parcel)（英文）。

**海关税费。** 入境时是否需要另缴费用取决于你所在国家的规定以及卖家或平台在结账时是否已代收，请先向卖家或平台核实。Peek 无法查阅 Pitney Bowes 的税费处理规则。另见[包裹卡在海关怎么办](guide:customs)（英文）。

## Pitney Bowes 包裹不更新、丢失或退回怎么办

**暂未查到。** 面对系统未知的单号或已过期的历史记录，Pitney Bowes 查询页返回的提示与页面故障相同，Peek 无法区分两者：此时 Peek 会转而向追踪服务查询，若各方均无记录，则显示“等待 Pitney Bowes 更新”，并自第二次查询起提示“暂时无法连接 Pitney Bowes。Peek 会继续重试。”同时继续自动尝试。请对照上表检查单号；若数日仍无结果，请联系卖家确认是否已真正发货。

**停在 `Package not received by Hub or First Mile Carrier yet`。** 卖家已打单，但 Pitney Bowes 转运中心和头程快递都尚未扫描，请询问卖家发货时间。

**停在 `Customs Clearance`。** 这表示进口电子申报已提交，包裹可能尚未实际飞抵目的国。请耐心等待清关完成记录（Peek 中显示为 `Customs import cleared`）；若停滞过久，请通知卖家。

**交邮后无更新。** 目的国邮政收件后，其自家官网的更新可能快于 Pitney Bowes 转发，而 Peek 不会自动切换至该邮政。你可以点击 Pitney Bowes 页面上对应记录的链接前往当地邮政查询，或把邮政单号作为独立包裹添加到 Peek 中追踪。

**多日无更新。** 若包裹停留在“已创建运单”、“快递公司已收件”或“运输途中”满四天无新记录，Peek 会显示最新记录日期（如“自 10 月 12 日周一以来无物流更新”）及提示“包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 Pitney Bowes 查询。”超过三周无新记录时则显示“已超过三周无新动态。如果仍未收到，请联系卖家。Peek 会持续向 Pitney Bowes 查询。”另见[物流为何停止更新](guide:tracking-not-updating)（英文）。

**显示已送达但没收到。** 先查看放件处并询问邻居，再联系派送邮政和卖家。参阅[显示已签收但未收到](guide:delivered-not-received)（英文）。

**丢失或退回。** 请联系卖家或交易平台申请退款或补发，因为运输由对方预订。参阅[包裹丢失](guide:lost-parcel)（英文）和[包裹被退回寄件人](guide:returned-to-sender)（英文）。

## Pitney Bowes 客服与联系方式

由于 Peek 无法读取 Pitney Bowes 官网页面，本页不列出客服电话或邮箱：Peek 仅展示直接从承运商官网核实的联系方式。

Pitney Bowes 受卖家或交易平台委托承运，因此请先联系卖家或平台（eBay 订单直接通过 eBay 沟通）；一旦包裹进入目的国邮政，有关具体派送事宜可直接联系当地邮政。

## 使用 Peek 追踪 Pitney Bowes 包裹

粘贴 `UPAA`、`UPBE` 或 `PBX` 单号后，[Peek](/) 会直接识别为 Pitney Bowes（“识别出的快递公司”），仅凭单号即可查询，无需邮编或账号。来自 trackpb.shipment.co 或 parceltracking.pb.com 的查询链接也可直接使用。如果你只有卖家的头程快递单号或目的国邮政单号，也请直接粘贴：Peek 支持超过 4,500 家快递公司。若对不符合上述格式的单号强行选定 Pitney Bowes，Peek 会提示其“不会签发这种格式的单号”。

Peek 展示当前阶段、带地点的每条轨迹以及自动换算为你常用单位的重量（原查询页按磅和盎司计），但略去下一承运商的链接和换单号。Pitney Bowes 不提供预计送达日期或签收证明，Peek 也不显示这两项。详情页中的“打开 Pitney Bowes 官网”可直接跳转至官方查询页查看该单号。

Peek 不会将包裹自动转交给目的国邮政：邮政扫描通过 Pitney Bowes 查询页同步过来。当 Pitney Bowes 查询页无法访问或无法解析时，Peek 会转而向追踪服务查询并按对方原文展示。

Pitney Bowes 打印的时间采用各扫描方的本地时钟且不含时区。Peek 按每条记录所在地点或所处运输段推断时区；对于无地点的记录，`In Transit` 和 `Item processed at Interpost` 按 UTC，`Interpost handed item over to postal company` 按 UTC+2，`Customs Clearance` 按文中标明国家的时钟解读，并全部换算为你设备的本地时区显示。

当你打开包裹页面或在派送日开启通知时，只要查询页正常响应，Peek 最高每 10 分钟查询一次，出库派送当天则自最新扫描所在时钟早上 6 点起最高每 2 分钟查询一次；其余时间每小时一次。连续两天无新动态后（处于“已创建运单”或“快递公司已收件”状态时为 12 小时）改为每小时一次；在“已创建运单”满七天无新动态后改为每 6 小时一次。通知支持“每次扫描”、“重要节点”或“仅在送达时”。Peek 无法代你联系 Pitney Bowes、卖家或邮政。

## Pitney Bowes 快递查询常见问题

### UPAA 开头的单号是什么快递？

这是 Pitney Bowes 单号，格式为 `UPAA`、1 位字母加 15 位数字。在 Peek 代码中，`UPAA` 对应美国和英国发出的 eBay 国际包裹，`UPBE` 对应英国发出的 eBay 包裹，`PBX` 对应 Pitney Bowes 其他国际包裹。Peek 目录也将 eBay Global Shipping Program 列为 Pitney Bowes 的别名。

### “Interpost handed item over to postal company” 是什么意思？

表示承接 Pitney Bowes 英国转运中心包裹的合作伙伴 Interpost 已对接移交目的国邮政派送。该记录通常在英国中心处理包裹时即同步写入，因此可能出现在包裹尚未离境之前，目的国邮政的后续扫描会在之后陆续更新到 Pitney Bowes 查询页上。

### 为什么 Pitney Bowes 物流卡在 “Customs Clearance”？

这代表目的国进口报关电子申报已提交，常常在包裹离开英国数小时内、甚至尚未落地时就已生成。请等待清关放行记录（在 Peek 中显示为 `Customs import cleared`）；如果长时间无进展，请联系卖家。

### 怎么联系 Pitney Bowes 查询包裹？

请先联系卖家或交易平台，因为是他们预订了该运输服务。由于 Peek 无法读取 Pitney Bowes 官网，本页未列出电话或电子邮箱。

:::sources
- [Universal Parcel Scraper: Pitney Bowes](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/pitney-bowes) – names, the three number shapes and what they cover, tracking links, the tracker, its wordings and their stages, Interpost's lines and clearance code, the post's relayed lines, Poste Italiane on a parcel to Italy, the overall status, clocks, weight, what Peek leaves out
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – no hand-off to the delivering post, tracking services as a fallback
:::
