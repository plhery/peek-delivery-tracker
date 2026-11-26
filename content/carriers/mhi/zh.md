---
title: MHI（Trackmail）包裹追踪：状态含义、本地派送商与客服
description: 收到来自英国商家的 MHI 单号？查看 Trackmail 追踪入口、各英文状态含义、当地派送承运商交接、无更新排查与客服联系方式。
slug: mhi-tracking
published: 2026-11-26
updated: 2026-11-26
---

MHI（Mail Handling International）是一家总部位于英国布里斯托尔的跨境物流企业，专门帮助英国网店将订单寄往海外买家。商家将包裹送至其布里斯托尔处理中心，由 MHI 负责出口发运，并移交至您所在国家的快递或邮政公司，由当地承运商使用自有单号完成末端派送。您可在 MHI 官方追踪网站 [Trackmail](https://www.trackmail.co.uk/) 查询 MHI 单号，或将其粘贴到上方输入框中。单号由商家提供（MHI 为其客户分配每件包裹的追踪码），请查看商家的发货通知邮件或订单详情页。

## MHI 追踪单号长什么样

MHI 未面向收件人公布单号格式规则。其常见单号格式如下（数字均为虚构示例）：

| 单号 | 说明 |
| --- | --- |
| `MHI482W736405829173` | `MHI` + 3 位数字 + `W` + 12 位数字：MHI 单号。 |
| `MHI482WBB736405829` | `MHI` + 3 位数字 + `WBB` + 9 位数字：MHI 单号。 |
| `MHI-482-P736405829` | `MHI` + 3 位数字 + `P` + 9 位数字：MHI 单号，面单上印有连字符。 |
| `MHI482736405829173` | `MHI` + 3 位数字 + 12 位数字：MHI 单号。 |
| `RR482917367US` | 2 位字母 + 9 位数字 + 2 位字母：部分平邮/非全程追踪件在 Trackmail 上显示的当地邮政单号。 |
| 其他任意格式 | 末端派送承运商的自有单号，在可全程追踪件的 Trackmail 页面中显示于“Tracking number:”之后。 |

`MHI` 后的三位数字代表商家在 MHI 的客户账号。在 Trackmail 官网查询带 `P` 的单号时，请按面单所示保留连字符，否则 Trackmail 无法查到记录。同一件包裹最终可能拥有两个单号：MHI 单号和当地承运商单号。详见[单号格式指南](guide:tracking-number-formats)（英文）。

## MHI 追踪状态含义

Trackmail 仅提供英文界面。在历史记录上方会显示商家选用的服务级别：“The seller requested this item be shipped via the following service:”，其后为 `TRACKED`（全程追踪）或 `UNTRACKED`（非全程追踪）。下方按时间倒序列出包含日期、时间、说明及大多数带有分类和地点的扫描记录：先是 MHI 自己的英国出口扫描，随后是以 MHI 措辞呈现的当地承运商扫描。按包裹通常经历的顺序如下：

| Trackmail 显示状态 | 对您的实际含义 |
| --- | --- |
| `Your order has been processed at the UK export facility. It is now on route to the receiver.` | MHI 已在英国完成出口处理并发往海外。非全程追踪件往往只有这一条记录。 |
| `Shipment information sent to last mile service provider` | 您所在国家的末端承运商已收到包裹电子预报，但尚未收到实物。 |
| `Notification sent to recipient` | 派送承运商已向您发出包裹即将送达的通知。 |
| `Handed Over to Delivery Partner` | 末端派送承运商已接收包裹。 |
| `Arrived at sorting center` | 承运商网络内的分拣中心到达扫描。 |
| `Dispatched from sorting center` | 已离开该分拣中心。 |
| `Assigned to Transport` | 正在各分拨中心之间的干线车辆上运输。 |
| `Shipment at final delivery center` | 已抵达负责末端派送的当地站点。 |
| `Delivery scheduled in the final delivery depot` | 已排入当日派送班次。 |
| `Delivery attempted` | 派送尝试失败，无人接收包裹。 |
| `Arrived at Pick-up Point` | 已送达自提点，等待您前往领取。 |
| `Successfully delivered` | 已送达或已自提。 |

查看状态时请以具体文字为准，而不要仅看旁边的分类标签。Trackmail 会将 `Shipment at final delivery center` 和 `Arrived at Pick-up Point` 都归类在“Out for delivery”下，但前者处于出车派送之前，后者则需要您自行前往自提点取件；它还会将 `Delivery attempted` 归类在“Delivery”下，与真正送达相同。派送班次与派送尝试可能连续多日重复出现。

Trackmail 显示的时间不含时区：MHI 自己的扫描为英国时间，当地承运商的扫描为其当地时间。

如果输入了系统未收录的单号，Trackmail 会显示一行以 `No details found for Tracking Code, or Tracking Code not recognised.` 开头的提示，并说明若订单刚下不久，MHI 可能尚未收到包裹数据。

## MHI 派送：究竟由谁把包裹送到您手上

MHI 本身不在海外执行末端派送。其官网表示与 100 多家承运商合作，服务覆盖 252 个国家和地区。包裹抵达布里斯托尔处理中心后经条码扫描，MHI 系统会根据收件国自动匹配末端承运商。商家可选择全程追踪（tracked）、非全程追踪（untracked）或特快（express）服务，因此最终由谁派送取决于您的收件国以及商家支付的服务类型。

**全程追踪件（Tracked）。** Trackmail 会在“Tracking number:”之后列出末端承运商单号，并提供跳转至该承运商官网的“View carrier tracking”链接。如有疑问，Trackmail 建议点击该链接或直接联系商家。

**非全程追踪件（Untracked）。** Trackmail 说明此类包裹仅提供有限追踪信息，常常只有 MHI 出口处理这一条记录。部分邮件会附带一个“local poster number”（当地邮政编号），Trackmail 表示您可以拿着该编号咨询当地邮政或邮局，也可以直接在当地邮政官网试查，例如 [USPS](carrier:usps)、[Canada Post](carrier:canada-post)、[Australia Post](carrier:australia-post) 或 An Post。

投递通知、派送次数、改派和自提点规则均以末端派送承运商为准：请通过“View carrier tracking”查看其官网，并参阅[错过派送处理指南](guide:missed-delivery)（英文）。

## MHI 包裹无更新、丢失或退回怎么办

**显示 `No details found…`。** 请核对单号，带 `P` 的单号在 Trackmail 上务必输入连字符。如果刚下单不久，MHI 可能尚未收到数据，请稍后再试。

**只有一条出口扫描。** 若为非全程追踪件，这可能就是 Trackmail 的全部记录：如有当地邮政编号请改查本国邮政，否则咨询商家。若为全程追踪件，可能是当地承运商尚未扫描接收；一旦出现其单号，前往该承运商官网通常能看到更新动态。详见[为何物流停止更新](guide:tracking-not-updating)（英文）。

**显示已送达但没收到。** 实际投递由当地承运商完成，请先通过“View carrier tracking”查看其追踪详情，再联系商家。详见[显示已签收但未收到包裹](guide:delivered-not-received)（英文）。

**丢失或损坏。** 请联系商家。MHI 的合约客户是商家，未面向收件人开设理赔流程，Trackmail 页面本身也指引您联系零售商。详见[包裹丢失处理指南](guide:lost-parcel)（英文）。

**海关税费。** MHI 为商家提供包括 DDP（完税后交货）在内的多种清关条款，因此收件时是否还需缴税取决于商家的选择。可能涉及的费用请见[包裹滞留海关指南](guide:customs)（英文）。

**包裹退回。** 无人领取的包裹保留多久后退回，以当地派送承运商规则为准，请查看其追踪页面。如已退回，请联系商家安排退款或重发。详见[包裹退回发件人](guide:returned-to-sender)（英文）。

## MHI 客服电话与联系方式

MHI 公布的信息如下：

- **追踪咨询邮箱：** `tracking@mhi.co`，列于其电商配送页面及旗下发运平台 Nexae 的联系页面。
- **联系电话：** `+44 (0) 117 977 6655`，与布里斯托尔办公地址（82 Feeder Road, BS2 0TQ）一并列出。MHI 未公布接线时间，也未表明该号码受理收件人查件。
- **在线咨询表单：** 仅用于一般商务咨询。MHI 明确声明通过该表单提交的包裹追踪查询不会被受理或处理。

MHI 给买家的建议是查看 Trackmail 或联系卖家/平台。商家才是 MHI 的签约客户，可直接向 MHI 催办；若涉及错过派送或自提，请直接联系 Trackmail 上显示的末端承运商。

## 使用 Peek 追踪 MHI 包裹

[Peek](/) 可按格式自动识别上述四类 MHI 单号，无论是否包含连字符均可识别。旧版面单中 `MHI` 后仅有两位数字，或末尾仅有八位数字的单号无法在 Peek 中查询，请直接前往 Trackmail 核对。您也可以粘贴包含单号的 Trackmail 链接，只要单号符合 MHI 格式，Peek 便会自动归类为 MHI。

Peek 直接读取 Trackmail，展示当前阶段和完整历史，并在地图上标出每次扫描地点。Trackmail 不提供预计送达日期、重量或签收证明，因此 Peek 也不会从 MHI 获取这些信息，且会过滤掉您的地址以及承运商链接里的邮编。

当 Trackmail 的跳转链接指向 Peek 识别的承运商时，包裹页面会显示“由……派送”及该承运商单号。在该承运商仅收到预报、尚无自身扫描之前，页面会改为显示“……已收到预报，尚未收到包裹”。若 Peek 本身支持直连该承运商，便会用该单号同步查询，并在出现更新进展时自动切换过去。Peek 未收录的承运商不会被列出，此时请使用 Trackmail 页面上的“View carrier tracking”。对于带有当地邮政编号的非全程追踪件，Peek 可能会尝试向您添加包裹时所在国家的邮政查询并按相同方式切换——但并非所有邮政都支持（例如在澳大利亚添加的 `AU` 结尾 `RR…` 单号不会转发给 Australia Post，请自行在 Australia Post 官网查询）。

当您在 Peek 中打开包裹页面，或在派送当日开启通知时，Peek 最多每 10 分钟检查一次；包裹派送当天，只要 Trackmail（或接手派送的承运商官网）正常响应，便从当地时间早上 6 点起每 2 分钟检查一次；若经由追踪服务跟进则为每 15 分钟一次。若连续两天无新动态（或停留在“已创建面单”/“承运商已揽收”满 12 小时），则改为每小时检查一次；停留在“已创建面单”满七天后每 6 小时检查一次。

当最新扫描距今已满四天且包裹仍停留在“已创建面单”、“承运商已揽收”或“运输中”时，Peek 会按该次扫描日期加以标记，例如“自 10 月 12 日（周一）起无物流更新”。包裹页面随即会提示“包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 MHI 查询。”MHI 在 Peek 中没有帮助页链接，因此只有在 Peek 切换跟进末端承运商且该承运商有帮助页时，才会显示“从……获取帮助”链接。如果超过三周仍无新扫描，页面会提示“超过三周没有新动态。如果包裹仍未送达，请联系卖家。Peek 会持续向 MHI 查询。”

Peek 可向您发送“每次扫描”、“重要节点”或“仅送达通知”提醒，但无法代您联系 MHI、当地承运商或商家。

## 关于 MHI 追踪的常见问题

### 我的 MHI 包裹现在在哪里？

Trackmail 上的最新一行显示包裹所处阶段，承运商扫描会注明所在城镇。在 `Handed Over to Delivery Partner` 之前，包裹由 MHI 持有或正在发往您的国家；此后由当地承运商负责，全程追踪件在 Trackmail 的“Tracking number:”后可看到当地承运商单号。

### “processed at the UK export facility”是什么意思？

表示 MHI 已在其英国出口处理中心完成操作并将包裹发往您的方向。这是 MHI 自身的扫描记录，在非全程追踪服务中往往也是唯一的一条记录。

### trackmail.co.uk 是正规网站吗？

是的。Trackmail 是 MHI 的官方追踪网站：MHI 官网上的“Track my Parcel”链接直接指向该站。英国公司注册局（Companies House）登记显示 Mail Handling International Limited 为存续公司，成立于 1996 年，注册地址与 MHI 官网公布的布里斯托尔地址一致。

### MHI 有客服电话吗？

其官网列出了 `+44 (0) 117 977 6655`，但针对包裹追踪建议发邮件至 `tracking@mhi.co` 或联系商家。关于派送本身的问题，应直接咨询 Trackmail 页面上列出的当地承运商。

:::sources
- [MHI：首页](https://www.mhi.co/) – 指向 Trackmail 的 Track my Parcel 链接、引导收件人联系卖家、一般咨询表单不受理追踪、100+ 承运商、252 个国家与地区、全程追踪/非全程追踪/特快服务、DDP、地址与电话
- [MHI：eCommerce Delivery](https://www.mhi.co/ecommerce-delivery) – 布里斯托尔处理中心、按目的国分配承运商、商家追踪码、出口发运与 `tracking@mhi.co`
- [Nexae：Contact Us](https://nexae.co.uk/ContactUsHomepage) – `tracking@mhi.co` 及 Nexae 隶属 MHI 说明
- [MHI：Trackmail](https://www.trackmail.co.uk/) – MHI 官方追踪网站、英文界面与追踪联系说明
- [Companies House：Mail Handling International Limited](https://find-and-update.company-information.service.gov.uk/company/03241562) – 存续状态、1996 年 8 月 23 日成立、注册地址 82 Feeder Road, Bristol
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – 单号格式、Trackmail 文案与分类映射、服务行、承运商链接、当地邮政编号、未查到提示、时区规则、Peek 展示字段及承运商/邮政查询逻辑
:::
