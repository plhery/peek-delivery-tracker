---
title: UPS 国际快递单号查询：1Z 运单、Exception 状态与清关
description: 查询 UPS 国际快递包裹：1Z 18 位运单号规则、Exception 等英文扫描状态含义、Access Point 自提点、海关税费及丢件处理指南。
slug: ups-guoji-kuaidi-chaxun
published: 2026-11-24
updated: 2026-11-24
---

UPS（United Parcel Service，联合包裹）运单号通常以 `1Z` 开头。你可以把运单号或 UPS 追踪链接粘贴到上方查询框中跟踪包裹，也可以在 `ups.com` 的官方追踪页面查询。

## UPS 单号格式与查找方式

发货商家会在发货邮件或订单详情页提供运单号；`ups.com` 查件链接中 `tracknum=` 后面同样带有该单号。如果仍未找到，请参阅[如何查找快递单号](guide:find-tracking-number)（英文）。

| 单号（虚构） | 归属与说明 |
| --- | --- |
| `1Z4829173605829143` | UPS 标准包裹单号：`1Z` 加 16 位字母和数字，共 18 位。最后一位是根据前 15 位字符算出的校验位。 |

## UPS 物流状态与 Exception 含义解析

UPS 官网追踪页顶部有进度条（`Label Created`、`We Have Your Package`、`On the Way`、`Out for Delivery`、`Delivered`），下方展示逐条扫描明细。以下是 Peek 从 UPS 读取到的常见英文扫描及对应阶段（大致按包裹流转顺序排列）：

| UPS 英文扫描 | Peek 显示 | 对你的含义 |
| --- | --- | --- |
| `Shipper created a label, UPS has not received the package yet.` | “已生成面单” | 寄件人已创建运单，UPS 尚未收到实物包裹。 |
| `Drop-Off`、`Pickup Scan` | “已揽收” | UPS 已拿到包裹（寄件人投递至网点或 UPS 上门取件）；始发地 `Arrived at Facility` 同样归入此阶段。 |
| `Arrived at Facility`、`Departed from Facility`、`Processing at UPS Facility` | “运输中” | 正在 UPS 转运网络中流转；`Export Scan` 与 `Import Scan` 标记出入境口岸。 |
| `Your package is prepared for clearance.` | “清关中” | 正在办理海关清关手续。 |
| `Your package is pending release from a Government Agency. We'll notify the receiver or sender if information is needed.` | “清关中” | 包裹在出口与进口扫描之间等待政府机构放行；如需补充资料 UPS 会联系收件人或寄件人。 |
| `Your package has been released by a government agency.` | “运输中” | 相关政府机构已放行包裹。 |
| `Duties or taxes are due on this package.` | “清关中” | 派送前需先缴纳进口关税或税费（见下文）。 |
| `Loaded on Delivery Vehicle`、`Out For Delivery Today` | “派送中” | 派送员已装车，正在今日派送途中。 |
| `Delivery instructions have been provided by the receiver.` | 维持原阶段 | 收件人已向 UPS 提交派送指示，仅为提醒而非实体扫描，Peek 保持先前阶段（如“派送中”）。 |
| `The receiver was not available at the time of the final delivery attempt.` | “派送未成功” | 最后一次派送尝试未成功。 |
| `Delivered to UPS Access Point™`、`UPS Access Point™ possession` | “待取件” | 包裹已存放在 UPS Access Point 自提点等待领取。 |
| `DELIVERED` | “已签收” | 已妥投送达，或已由你在自提点取走。 |
| `Return to Sender` | “已退回寄件人” | 包裹正在退回寄件人。 |

`Exception` 是 UPS 的通用异常总标签，有时仅代表短暂延误，务必查看下方的具体扫描句：例如 `We're sorry this package may experience a temporary delay.` 仅表示临时延迟，而 `The company or receiver name is incorrect. This will delay delivery.` 说明需要补充或更正收件信息（Peek 会显示“需要留意”）。Peek 依据最新一条具体扫描而非泛化的总状态判断阶段。

## 错过 UPS 派送与 Access Point 自提点

若司机无法完成投递，扫描会说明收件人不在或商铺关门，随后 UPS 会再次尝试派送。最后一次尝试失败后，后续扫描会说明包裹去向：网点暂存、转交自提点或退回寄件人。详见[错过派送怎么办](guide:missed-delivery)（英文）。

UPS 也可将包裹转交至 UPS Access Point 自提点。送达后会出现 `Delivered to UPS Access Point™` 和 `UPS Access Point™ possession`，Peek 会同时显示该自提点的名称和地址。请尽快前往领取：若超期未取准备退回，扫描会显示 `The UPS Access Point™ location has prepared the package for return to UPS or pickup by UPS.`；若某自提点歇业，UPS 会收回包裹并提示改送另一自提点。详见[逾期未取包裹](guide:uncollected-parcel)（英文）。

## UPS 国际包裹的关税与清关费用

寄往英国（来自英国以外）或欧盟成员国（来自欧盟以外）的国际包裹可能需要先付进口税费再派送，此时轨迹会显示 `Duties or taxes are due on this package.` 及 `Your package is being held until duties and taxes are paid. Awaiting customer action.`，Peek 会显示“清关中”并提示“需缴纳进口关税或税费”。

如果海关需要你的税号才能放行，UPS 会显示 `Importer's consumer or business Identification number is required for clearance. Awaiting information from the customer.`，此时需由收件人提交个人或企业税号，Peek 会显示“清关中”并提示“海关需要你的税号”。

- **英国：** 根据 GOV.UK 说明，快递公司会向收件人寄发增值税、关税及代垫费账单，通常代为保管约 3 周，逾期未付则退回寄件人。
- **爱尔兰及欧盟：** 自 2026 年 7 月 1 日起，从欧盟以外寄入且货值不超过 150 欧元的多数包裹按商品类别征收 3 欧元关税；若商家结账时未代收，根据爱尔兰税务局（Revenue）说明，快递公司会在派送前向你收取并加收自身手续费。此外，自 2026 年 11 月 1 日起，从欧盟以外网购的货物不论货值高低，均须按商品类别另缴 2 欧元欧盟处理费。

详见[包裹被海关扣留怎么办](guide:customs)（英文）。

## UPS 包裹延误、丢失或显示已签收但未收到

如果轨迹长时间停滞，请参阅[为什么物流停止更新](guide:tracking-not-updating)（英文）。

如果你在英国或爱尔兰网购，即使由 UPS 承运，保障包裹送达的法定责任也在卖家：

- **英格兰、苏格兰与威尔士：** 根据 Citizens Advice 指引，应由卖家而非买家联系快递催办；若下单后 30 天内未收到，你有权取消订单并要求退款；若包裹被放在未经你同意的位置后丢失，也应由卖家负责解决。
- **爱尔兰：** 根据 CCPC 说明，未另行约定交货期时商品须在下单后 30 天内送达，逾期可重新约定交货期，仍未送达即有权全额退款；在途丢失或破损须由商家补发，且即便快递声称已妥投，在交到你或你指定代收人手中之前商家仍负全责。

显示 `DELIVERED` 却找不到包裹？UPS 官网有时会标明放置位置（如 `Left at: Front Door`）及签收证明（Peek 不会保存这些隐私信息）。请先查看 `ups.com`，再联系发货商家。详见[显示已签收但未收到](guide:delivered-not-received)（英文）与[包裹丢失](guide:lost-parcel)（英文）。

## 使用 Peek 追踪 UPS 包裹

在 [Peek](/) 输入校验位正确的 `1Z` 单号后，系统会立刻识别为 UPS；若输错导致校验失败，UPS 会按错号拒收。包含 `1Z` 单号的 UPS 追踪链接同样直接有效。Peek 仅就 `1Z` 单号直连查询 UPS，其他格式单号会转交第三方聚合追踪服务核实。

Peek 通过服务器端浏览器读取 `ups.com` 追踪页结果：在你打开包裹页面或预计派送当天开启提醒时，只要 UPS 官网正常响应，最高每 10 分钟检查一次，在 UPS 扫出派送中当天自当地时间早上 6 点起最高每 5 分钟检查一次（若通过聚合追踪服务跟进，则每小时检查一次、静默满一天后改为每 3 小时一次，在派送当天打开页面或开启提醒时最高每 15 分钟一次）；其余时间每小时检查一次。连续两天无新动态（或停留在“已生成面单”、“已揽收”满 12 小时）后转为每小时检查一次。若 UPS 官网拒绝请求或仅返回总状态，聚合追踪服务会自动补全历史轨迹。

Peek 会展示标准阶段、带精确时间与地图标注的每条扫描、预计送达日（不含具体时段）、签收时间，以及包裹待取期间的 Access Point 名称与地址，并支持“每次扫描”、“重要环节”或“仅派送当天”通知预设。Peek 会过滤签收人、你的住址、签收证明与放置位置说明，不显示照片或重量。扫描保持 UPS 英文原文，妥投行显示为 `DELIVERED`。当 UPS 将 Ground Saver 包裹移交给美国邮政（USPS）并给出对应单号时，只要 USPS 出现更新，Peek 就会切过去跟进（显示“由 USPS 派送”）。

## 关于 UPS 快递追踪的常见问题

### 为什么我的 UPS 单号提示无效？

最常见原因是输入有误：`1Z` 单号中只要错了一位字符，末尾校验位通常就会失效而被 UPS 拒绝；另一种可能是单号属于其他快递公司，请核对商家发货邮件中的承运商名称。

### UPS 物流轨迹多久会出现？

卖家创建面单后会出现第一条 `Shipper created a label, UPS has not received the package yet.`，但要等到寄件人送交网点或 UPS 上门揽收（`Drop-Off` / `Pickup Scan`）后才会继续更新。

### 可以在 Peek 里更改 UPS 派送方式吗？

不可以，Peek 仅读取 UPS 追踪信息。但你在 UPS 官方渠道提交的改派会反映在扫描轨迹中，例如 `The package will be delivered to the UPS Access Point™ location requested by the receiver.`。

### 收到 UPS 派送收费短信是诈骗吗？

很可能是。Citizens Advice 提醒，要求点击短信或邮件链接付费才能领取或改约派送的消息多为诈骗——真正的进口税费会直接显示在海关轨迹节点中，请务必通过 `ups.com` 或 Peek 核对单号，切勿轻点陌生链接。英国用户可将诈骗短信免费转发至 `7726`。详见[快递诈骗短信识别指南](guide:delivery-scam-texts)（英文）。

:::sources
- [Citizens Advice: 网购商品未送达的维权指引](https://www.citizensadvice.org.uk/consumer/somethings-gone-wrong-with-a-purchase/if-something-you-ordered-hasnt-been-delivered/) – 卖家责任、30 天期限、未经同意放置与防诈骗提醒
- [GOV.UK: 海外寄入商品的税费与关税](https://www.gov.uk/goods-sent-from-abroad/tax-and-duty) – 快递代收账单、约 3 周保管期与退回寄件人
- [GOV.UK: 举报网络诈骗与钓鱼短信](https://www.gov.uk/report-suspicious-emails-websites-phishing) – 免费转发诈骗短信至 `7726`
- [CCPC: 爱尔兰网购未送达消费者权益](https://www.ccpc.ie/consumer-advice/consumer-rights/buying-goods/non-delivery-of-goods) – 30 天送达义务、退款权与商家交付责任
- [Revenue: 从欧盟以外地区网购](https://www.revenue.ie/en/customs/individuals/buying-online-personal/outside-eu.aspx) – 自 2026 年 7 月 1 日起每类商品 3 欧元关税
- [Revenue: 个人网购进口清关说明](https://www.revenue.ie/en/customs/individuals/buying-online-personal/index.aspx) – 快递商清关手续费说明
- [European Commission: 欧盟海关改革](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 自 2026 年 11 月 1 日起 2 欧元欧盟处理费
- [European Commission: 3 欧元关税与欧盟处理费指南（PDF）](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 欧元费用适用于不限货值的跨境网购每类商品
- [Universal Parcel Scraper: UPS](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/ups) – `1Z` 校验规则、UPS 扫描映射（政府机构暂扣/放行、收件人指示、税号要求）与 Peek 展示字段
:::
