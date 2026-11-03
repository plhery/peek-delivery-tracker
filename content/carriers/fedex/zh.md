---
title: FedEx 联邦快递物流查询：单号格式、状态含义与派送失败
description: FedEx（联邦快递）包裹跟踪：12 位快递单号长什么样，Delivery exception 等物流状态是什么意思，清关要交哪些费用，丢件或显示已签收却没收到怎么办。
slug: fedex-wuliu-chaxun
published: 2026-11-02
updated: 2026-11-02
---

把 FedEx（联邦快递）的快递单号或 FedEx 查询链接粘贴到上方的查询框，就能跟踪包裹。FedEx 自己的物流查询在 fedex.com 官网上。

## FedEx 快递单号在哪里找，有几位数

单号一般在商家的发货邮件或订单页面上。fedex.com 的查询链接里也有单号，就在 `trknbr=` 或 `tracknumbers=` 后面。还是找不到，就向商家要。

| 格式（数字为虚构） | 说明 |
| --- | --- |
| `123456789012` | 12 位数字：FedEx 快递单号。最后一位是校验位，由前 11 位算出。 |
| `123456789012343` | 15 位数字：Peek 也把这种格式归到 FedEx。 |
| `61290000000000000014` | 20 位数字，以 `6129` 或 `7489` 开头：美国的 FedEx Ground Economy（原 SmartPost）。 |

12 位数字也可能是 DPD France、Mondial Relay、BRT 的单号，或者顺丰（SF Express）、申通（STO Express）、中通（ZTO Express）等亚洲快递公司的单号；15 位数字也可能属于 DPD France、BRT 或韵达（Yunda Express）。如果在 fedex.com 上查不到，请看商家邮件里写的是哪家快递公司。

## FedEx 物流状态是什么意思（含 Delivery exception）

下表是 Peek 从 FedEx 读到的扫描记录（FedEx 的英文原文），大致按包裹走过的先后排列，旁边是 Peek 对应显示的状态。

| FedEx 的扫描记录 | Peek 显示的状态 | 意思 |
| --- | --- | --- |
| `Shipment information sent to FedEx` | “已生成面单” | 寄件人已把这票货的信息发给 FedEx。 |
| `Picked up`、`Tendered at FedEx location`、`In FedEx possession` | “已揽收” | FedEx 拿到了包裹：快递员上门取走，或寄件人送到了 FedEx 网点。 |
| `At FedEx origin facility`、`Left FedEx origin facility` | “运输中” | 包裹到达或离开寄出地的 FedEx 分拣中心。 |
| `International shipment release - Export` | “运输中” | 寄出国的海关已放行出口。 |
| `Arrived at FedEx location`、`Departed FedEx location`、`At local FedEx facility`、`On the way` | “运输中” | 包裹在 FedEx 的网络里运输。 |
| `Clearance in progress`、`Clearance delay` | “清关中” | 包裹正在清关（见下文）。 |
| `International shipment release - Import` | “运输中” | 目的国海关已放行。 |
| `On FedEx vehicle for delivery` | “派送中” | 包裹已装上派送车。 |
| `Delivery exception`、`Customer not available or business closed` | “派送未成功” | 这次没能派送。 |
| `Ready for pickup` | “待取件” | 包裹在取件点等你去取。 |
| `Delivered` | “已签收” | 包裹已送达。 |
| `Shipment exception` | “需要留意” | FedEx 报告这票货出了问题。 |
| `Returning to shipper` | “已退回寄件人” | 包裹正在退回寄件人。 |

`Delivery exception` 在 Peek 里算作“派送未成功”。扫描记录里可能会写明原因：`Customer not available or business closed` 是没人收件或收件公司已关门，`Local delivery restriction - Delivery not attempted` 是受当地限制，快递员没有派送。FedEx 把包裹标为延误时，Peek 显示“需要留意”；只有最新一条记录是 `Package received after FedEx cutoff` 时例外：这表示 FedEx 过了当天的截止时间才收到包裹，包裹要下一个工作日才发出。

## FedEx 派送失败与取件点

如果你请 FedEx 把包裹留在取件点，包裹在送往那里的途中，记录可能显示 `Delivery option requested`，到了以后显示 `Ready for pickup`。这时如果 FedEx 给出了取件点的名称和地址，Peek 会显示出来。改派送方式要找 FedEx 办理，在 Peek 里改不了。

## FedEx 包裹被海关扣留

从英国以外寄到英国的包裹，或从欧盟以外寄到爱尔兰的包裹，可能要交进口税费。清关期间，物流显示 `Clearance in progress` 或 `Clearance delay`；海关放行后显示 `International shipment release - Import`。

- **英国**：GOV.UK 说，快递公司会联系你，发来增值税（VAT）、关税或派送费用的账单，包裹一般保留约 3 周。不付款，包裹会退回寄件人。
- **爱尔兰**：自 2026 年 7 月 1 日起，从欧盟以外寄来、价值 150 欧元及以下的包裹，大多每件商品要交 3 欧元关税；按商品种类算，两件一样的 T 恤算一件。如果商家结账时没有代收，爱尔兰税务局（Revenue）说，快递公司会在派送前向你收取，还会另收自己的手续费。此外，自 2026 年 11 月 1 日起，从欧盟以外网购的商品不论价值高低，每件还要另交 2 欧元的欧盟处理费（Union handling fee）。

要交多少、怎么办，见 [海关扣留包裹指南](guide:customs)（英文）。

## FedEx 包裹延误、丢失或显示已签收

如果你住在英格兰、苏格兰、威尔士或爱尔兰，网购的东西即使由 FedEx 运送，把包裹送到你手上仍是商家的责任：

- **英格兰、苏格兰和威尔士**：Citizens Advice（英国公民咨询局）说，应该由商家去催快递公司，而不是你。下单后 30 天内没有送到，你可以取消订单并要求退款。如果包裹被放在你没同意的地方，之后丢了，商家必须负责解决。
- **爱尔兰**：CCPC（爱尔兰竞争与消费者保护委员会）说，商家没给具体送达日期的，最迟要在下单后 30 天内送到。没按时送到，就和商家约一个新的送达日期；新日期过了还没收到，你有权全额退款。包裹在运输途中损坏或丢失，商家应当换新；在你或你指定的人拿到包裹之前，都由商家负责。

显示 `Delivered` 却没收到？FedEx 的签收记录写着签收人，Peek 不保存这一项。请先在 fedex.com 上查看物流记录，再联系商家。更多见 [显示已签收却没收到](guide:delivered-not-received)（英文）。

## 用 Peek 跟踪 FedEx 包裹

[Peek](/) 能认出校验位正确的 12 位和 15 位 FedEx 单号。不过别的快递公司也用这些格式，所以 Peek 往往要稍等片刻，或者等第三方物流查询服务确认，才能定下是 FedEx。校验位不对的单号，Peek 不会自动归到 FedEx，请点“更换”自己选快递公司。Ground Economy 的 20 位单号，Peek 马上就能认作 FedEx；扫描面单条形码得到的一长串数字，Peek 会从中读出单号；带 `trknbr=` 或 `tracknumbers=` 的 fedex.com 链接也可以直接粘贴。FedEx 在波兰国内用的 13 位单号，Peek 按 FedEx Poland 跟踪。

Peek 在自己的服务器上用浏览器打开 fedex.com 的查询页面。FedEx 拒绝查询或要求验证收件人身份时，改由第三方物流查询服务补上。

Peek 显示状态、每次扫描的地点和 FedEx 给出的准确时间、这些地点在地图上连成的路线，还有预计送达日期、签收时间，以及待取件时的取件点。FedEx 一般给每条记录标上扫描地的当地时间和 UTC 偏移（揽收那一步常常只有日期），在中国扫描的就是北京时间（UTC+8）；Peek 换算成你设备所在的时区显示。扫描记录按 FedEx 的英文原文显示，只有一字不差的 `Delivered`、`Ready for pickup` 和 `Shipment exception` 在 Peek 里显示为“已签收”“待取件”和“快件异常”。FedEx 的签收记录会写出签收人，所以 Peek 只显示“已签收”。Peek 不保存你的姓名和地址、寄件人信息、签名、重量和尺寸。

Peek 每 10 分钟查询一次，包裹派送中时每 5 分钟一次。两天没有新记录（状态还是“已生成面单”或“已揽收”时为 12 小时），Peek 就改为每小时查询一次。“通知范围”可以选“每次扫描”“重要环节”或“仅派送当天”。Peek 不能替你联系 FedEx 或商家。

Ground Economy 的包裹大多由 FedEx 自己派送。FedEx 说包裹已交给美国邮政（USPS）时，Peek 会接着跟踪 USPS 的记录，显示“由 USPS 派送”。

## 关于 FedEx 物流查询的常见问题

### FedEx 单号是 1Z 开头的吗？

不是。`1Z` 开头的是 UPS 的单号。FedEx 的单号只有数字。

### FedEx 物流一直显示 Shipment information sent to FedEx，是什么意思？

这是第一条记录，只表示寄件人已经把这票货的信息发给了 FedEx。等 FedEx 拿到包裹，记录才会更新，比如 `Picked up` 或 `Tendered at FedEx location`。如果寄件人过了当天的截止时间才交件，记录会写 `Package received after FedEx cutoff`，包裹要下一个工作日才发出。

### FedEx 显示 Delivery exception 是什么意思？

这次派送没能完成，Peek 显示“派送未成功”。扫描记录里可能会写明原因，比如 `Customer not available or business closed`（没人收件或收件公司已关门）。要改派送方式或改到取件点自取，请找 FedEx 办理。

### 收到 FedEx 派送短信让付款，是诈骗吗？

有可能。Citizens Advice 说，要你付钱才能拿到包裹或重新安排派送的消息，通常是诈骗；快递公司不会让你通过短信或邮件里的链接付款。不要点链接：请在 fedex.com 或 Peek 里查单号。在英国，可以把短信免费转发到 7726。在美国，FTC（美国联邦贸易委员会）建议通过你确认真实的网站或电话联系快递公司。

:::sources
- [Citizens Advice：If something you ordered hasn't arrived（英文）](https://www.citizensadvice.org.uk/consumer/somethings-gone-wrong-with-a-purchase/if-something-you-ordered-hasnt-been-delivered/) – 商家的责任、30 天、没同意的地点、诈骗消息（英格兰、苏格兰和威尔士适用同样的建议）
- [GOV.UK：Tax and customs for goods sent from abroad（英文）](https://www.gov.uk/goods-sent-from-abroad/tax-and-duty) – 快递公司的账单、约 3 周、退回寄件人
- [GOV.UK：Report internet scams and phishing（英文）](https://www.gov.uk/report-suspicious-emails-websites-phishing) – 诈骗短信转发到 7726
- [CCPC：Your consumer rights if your order is not delivered（英文）](https://www.ccpc.ie/consumer-advice/consumer-rights/buying-goods/non-delivery-of-goods) – 30 天、新日期、退款、拿到包裹前商家负责（爱尔兰）
- [爱尔兰税务局（Revenue）：Buying goods from outside the EU（英文）](https://www.revenue.ie/en/customs/individuals/buying-online-personal/outside-eu.aspx) – 自 2026 年 7 月 1 日起每件商品 3 欧元、按商品种类算件、派送前交给快递公司
- [爱尔兰税务局（Revenue）：Buying goods online for personal use（英文）](https://www.revenue.ie/en/customs/individuals/buying-online-personal/index.aspx) – 快递公司的手续费
- [欧盟委员会：EU customs reform（英文）](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 3 欧元关税自 2026 年 7 月 1 日起，2 欧元欧盟处理费自 2026 年 11 月 1 日起
- [欧盟委员会：Customs Guidance on EUR 3 customs duty（英文，PDF）](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 每件商品 2 欧元，不论价值
- [FTC：Fake shipping notification emails and text messages（英文）](https://consumer.ftc.gov/consumer-alerts/2023/12/fake-shipping-notification-emails-text-messages-what-you-need-know-holiday-season) – 通过确认真实的网站或电话联系快递公司
- [Universal Parcel Scraper：FedEx](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/fedex) – 单号格式、校验位、FedEx 的扫描记录原文、Peek 从 FedEx 读取的内容、当地时间和时区
:::
