---
title: DHL Express 物流查询：10 位运单号与状态说明
description: 查询 DHL Express 国际快递包裹：10 位运单号与 JD01 件号查找、英文物流状态中文对照、海关清关税费、派送未成功与丢件处理。
slug: dhl-express-tracking
published: 2026-11-24
updated: 2026-11-24
---

将 DHL Express 运单号（waybill number）或 MyDHL+ 查询链接粘贴到上方输入框中，即可追踪包裹。DHL Express 的官方查询位于其网站（MyDHL+）和 DHL Express 手机应用中。

## DHL Express 运单号：在哪里查找

DHL Express 将追踪单号称为运单号（waybill number）。你可以在商家的发货邮件或订单详情页中找到它；MyDHL+ 查询链接中 `id=` 后面的数字同样是运单号。如果仍找不到，请参阅[如何查找运单号](guide:find-tracking-number)（英文）。

| 格式（示例） | 说明 |
| --- | --- |
| `4829173602` | 运单号：10 位数字，用于追踪整票货件，最后一位是校验位。 |
| `JD014600004829173605` | 单件编号（piece number）：`JD01` 加 16 位数字，在面单下方条形码处印作 `(J)JD…`。一票多件货件中的每个包裹都有各自件号，并可关联回主运单号。 |

10 位纯数字格式也适用于 [Mondial Relay](carrier:mondial-relay)、Relais Colis、Estafeta、Packeta、[DHL eCommerce Iberia](carrier:dhl-ecommerce-es) 和 [DHL Paket](carrier:dhl)，因此请先核对商家发货邮件中写明的承运商。

DHL 会循环复用旧运单号：部分单号可能附带先前旧货件的扫描记录，Peek 会自动忽略这些历史旧记录。

## DHL Express 物流状态中文说明

以下是 Peek 从 DHL Express 读取的常见英文扫描文案，大致按包裹经过顺序排列，并附 Peek 对应显示的状态。很多记录末尾会带有处理设施名称，例如 `Processed at` 后接城镇和国家。

| DHL Express 扫描原文 | Peek 显示 | 含义 |
| --- | --- | --- |
| `Shipment information received`、`Supplementary information has been received and merged with applicable shipment data` | 已生成面单 | 寄件人已将货件信息发送给 DHL。 |
| `Shipment picked up`、`Shipment accepted` | 已揽收 | DHL Express 已收到包裹。 |
| `Processed at`、`Arrived at DHL Sort Facility`、`Shipment has departed from a DHL facility`、`Shipment is in transit to destination` | 运输中 | 正在 DHL 网络中转运。 |
| `Scheduled to depart on the next planned movement` | 运输中 | 正在站点等待下一班卡车或航班发运。 |
| `Customs clearance status updated. Note - The Customs clearance process` …、`Processed for clearance at`、`Clearance event` | 清关中 | 正在办理海关清关手续（见下文）。 |
| `On hold awaiting for payment of shipment related fees` | 清关中 | 需要缴纳关税或清关费用，DHL 暂扣包裹直至付清，并非包裹本身出问题。 |
| `Payment is received and recorded for shipment related fees` | 清关中 | 已收到并登记税费付款，包裹在清关完成前仍处于清关阶段。 |
| `Clearance processing complete at` | 运输中 | 海关已放行，包裹恢复转运。 |
| `Arrived at DHL Delivery Facility` | 运输中 | 已到达负责当地派送的 DHL 网点。 |
| `Shipment is out with courier for delivery` | 派送中 | 快递员正携带包裹为你派送。 |
| `Delivery attempted but no response at Consignee address`、`Delivery attempt could not be completed` | 派送未成功 | 本次派送未能完成。Consignee 即指收件人本人。 |
| `Shipment is scheduled for delivery` | 运输中 | 已安排在后续班次派送，例如派送未成功后的重新派送。 |
| `Awaiting collection by the consignee` | 待取件 | 包裹暂存在 DHL 网点，等待你前去自提。 |
| `Delivered` | 已签收 | 包裹已送达。 |
| `Shipment is on hold` | 需要留意 | DHL 暂时中止了该货件的流转。 |
| `Shipment arrived at incorrect facility. It will be sent to the correct destination` | 需要留意 | 包裹被误分拣至错误站点，将转往正确目的地，因此会晚到。 |
| `Returned to shipper` | 已退回寄件人 | 包裹正在退回发件人。 |

## DHL Express 派送未成功处理

派送未成功后，物流会显示 `Delivery attempted but no response at Consignee address` 或 `Delivery attempt could not be completed`。若随后出现 `Shipment is scheduled for delivery`，表示已预约新一轮派送；出现 `Awaiting collection by the consignee` 则表示包裹正在 DHL 网点等待你自提。如需重新安排派送，请通过 DHL Express 官网或官方应用联系 DHL：Peek 无法代为更改派送。更多指南见[派送未成功指南](guide:missed-delivery)（英文）和[逾期未取包裹指南](guide:uncollected-parcel)（英文）。

## DHL Express 包裹清关与关税

寄往英国的非英国本土包裹，或寄往爱尔兰的欧盟外包裹，可能需要缴纳进口税费。清关期间物流会显示 `Customs clearance status updated…`、`Processed for clearance at` 或 `Clearance event`；缴清费用后显示 `Payment is received and recorded for shipment related fees`；海关放行后显示 `Clearance processing complete at`。

- **英国：** 据 GOV.UK 说明，快递公司会向你发送增值税（VAT）、关税或派送相关费用的账单，并通常保管包裹约 3 周；逾期未付将退回寄件人。
- **爱尔兰：** 自 2026 年 7 月 1 日起，大多数来自欧盟以外、货值 150 欧元及以下的包裹需按商品类别每类缴纳 3 欧元关税。如果商家结账时未代收，爱尔兰税务局（Revenue）说明快递公司会在派送前向你收取，快递公司还可能加收自己的手续费（administration fee），请向其核实金额。

更多说明见[清关中的包裹指南](guide:customs)（英文）。

## DHL Express 包裹延误、丢失或显示已签收

如果物流停止更新或停留在 `Shipment is on hold`，请参阅[物流不更新怎么办](guide:tracking-not-updating)（英文）。

如果你居住在大不列颠或爱尔兰且从商家网购，即使由 DHL Express 承运，将包裹安全送达你的责任仍由商家承担：

- **英格兰、苏格兰和威尔士：** 英国公民咨询局（Citizens Advice）说明，应由卖家而非你去催促快递公司。如果下单后 30 天内未收到商品，你有权取消订单并要求退款。若快递将包裹放在未经你同意的位置并丢失，须由卖家负责解决。
- **爱尔兰：** 爱尔兰竞争与消费者保护委员会（CCPC）说明，未另行约定送达日时，订单必须在购买后 30 天内送达；如未按期送达，应与商家重新约定日期，若再次逾期则有权全额退款。运输途中破损或丢失的包裹应由商家补发，在你或你指定的人亲手收到包裹之前，风险均由商家承担。

显示 `Delivered` 但没收到包裹？DHL 官网查询页面有时会显示签收人签名，Peek 不会保留签名信息。请先在 DHL 官网核查记录，再联系商家处理。另见[显示已签收但未收到](guide:delivered-not-received)（英文）和[包裹丢失指南](guide:lost-parcel)（英文）。

## 使用 Peek 查询 DHL Express 包裹

对于通过 DHL Express 校验位的 10 位运单号，[Peek](/) 会把 DHL Express 排在第一位，并同时向它以及共用该格式的其他承运商查询，例如 ACS Courier、Geniki Taxydromiki（这两家仅在不带空格和连字符的纯数字时触发）以及 DHL eCommerce Iberia，最终以查到包裹的承运商为准。由于 ACS 和 Geniki 使用相同校验规则，如果其中一家也查到了该单号，系统会让你自行选择。未通过校验的 10 位数字不会自动发给 DHL Express，请点击“更换”手动选择。`JD01` 单件编号和 `JJD0146` 面单号（如 `JJD014648291736058419`）会直接识别为 DHL Express 并关联至主运单号。mydhl.express.dhl 的链接同样可直接粘贴查询；若是 dhl.com 链接，请直接粘贴运单号本身——如果包裹被分到了“DHL”名下，请点击“更换”切换为“DHL Express”。

Peek 通过 DHL 移动应用背后的接口读取数据；若不可用，则在自有服务器的浏览器中打开 dhl.com 查询页（速度较慢且可能被拦截），此时再由物流查询服务补充。只要 DHL 官方查询正常响应，Peek 最高每 10 分钟检查一次，在 DHL 扫描包裹进入派送中的当天早上 6 点（当地时间）起最高每 2 至 5 分钟检查一次（需用浏览器访问 dhl.com 时为每 5 分钟）（通过物流查询服务时，在派送当天打开页面或开启提醒时最高每 15 分钟检查一次），并可按每次扫描、重要环节或仅派送当天向你推送通知。若连续两天无新动态（包裹仍处于“已生成面单”或“已揽收”状态时为 12 小时），Peek 会转为每小时检查一次。

页面会显示当前状态、带网点位置（标在地图上）的各条扫描记录以及目的国。扫描时间按网点所在城镇的当地时钟解析，若信息不足则参考相邻扫描推算。除 `Delivered`、`Shipment picked up` 和 `Shipment information received` 转为 Peek 自有文案外，其他扫描保留 DHL 英文原文。Peek 会去除签收签名、忽略旧复用运单的历史记录，且不显示重量或 Service Point 信息。通过 dhl.com 页面读取时会显示 DHL 的预计送达日，但移动应用接口不提供预计日期。Peek 无法代缴关税或修改派送安排。

## DHL Express 物流查询常见问题

### DHL 运单号（waybill number）和追踪单号一样吗？

一样。对于 DHL Express，10 位运单号用于追踪整票货件；`JD01` 单件编号对应其中的某一个包裹，并可关联回主运单号。

### DHL 和 DHL Express 是同一家吗？

在 Peek 中不是：DHL Express 是独立承运商，与 [DHL Paket](carrier:dhl) 及各 DHL eCommerce 承运商分开，各自拥有独立的单号规则与查询系统。非 10 位运单号或非 `JD01` 件号的包裹通常属于其他 DHL 业务线。

### 为什么我的 DHL 运单号显示无效？

通常是因为该单号属于其他 DHL 业务线：DHL Express 运单号固定为 10 位数字且末位为校验位，输错一位往往就无法通过校验。此外，新建运单在 DHL 实际揽收前可能仅显示 `Shipment information received`。

### 没有运单号可以查 DHL Express 吗？

在 Peek 中不行：必须提供 10 位运单号或面单上的 `JD01` 单件编号。请向商家或寄件人索取运单号。

### 收到要求付款的 DHL Express 短信是诈骗吗？

有可能。英国公民咨询局（Citizens Advice）指出，要求你付款以领取包裹或重新安排派送的短信通常是诈骗。请勿点击链接，而是直接在 DHL 官网或 Peek 中查询运单号。在英国可将诈骗短信免费转发至 7726。在美国，美国联邦贸易委员会（FTC）建议通过你确知真实的网站或电话联系快递公司。更多防骗方法见[快递诈骗短信指南](guide:delivery-scam-texts)（英文）。

:::sources
- [Citizens Advice: If something you ordered hasn't arrived](https://www.citizensadvice.org.uk/consumer/somethings-gone-wrong-with-a-purchase/if-something-you-ordered-hasnt-been-delivered/) – 卖家责任、30 天期限、约定放置位置与诈骗短信提醒（适用于英格兰、苏格兰与威尔士）
- [GOV.UK: Tax and customs for goods sent from abroad](https://www.gov.uk/goods-sent-from-abroad/tax-and-duty) – 承运商税单、约 3 周保管期与退回寄件人
- [GOV.UK: Report internet scams and phishing](https://www.gov.uk/report-suspicious-emails-websites-phishing) – 免费转发诈骗短信至 7726
- [CCPC: Your consumer rights if your order is not delivered](https://www.ccpc.ie/consumer-advice/consumer-rights/buying-goods/non-delivery-of-goods) – 30 天规则、新送达日、全额退款及送达前商家责任（爱尔兰）
- [Revenue: Buying goods from outside the EU](https://www.revenue.ie/en/customs/individuals/buying-online-personal/outside-eu.aspx) – 自 2026 年 7 月 1 日起按商品类别每类征收 3 欧元关税，派送前向承运商缴纳
- [Revenue: Buying goods online for personal use](https://www.revenue.ie/en/customs/individuals/buying-online-personal/index.aspx) – 快递公司可能收取的手续费
- [FTC: Fake shipping notification emails and text messages](https://consumer.ftc.gov/consumer-alerts/2023/12/fake-shipping-notification-emails-text-messages-what-you-need-know-holiday-season) – 通过确知真实的网站或电话联系快递公司
- [Universal Parcel Scraper: DHL Express](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/dhl-express) – 运单号与单件编号格式、校验位、相似的 10 位单号、复用运单号、DHL Express 扫描文案及 Peek 对应状态、DHL 显示的签名、Peek 从 DHL Express 读取的内容
:::
