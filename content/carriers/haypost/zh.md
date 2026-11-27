---
title: 亚美尼亚邮政（HayPost）物流查询：AM 单号、状态与联系方式
description: 查询亚美尼亚邮政（HayPost）包裹：S10 AM 运单号格式、IPS 国际与国内物流状态含义、寄递服务、埃里温联系方式，以及使用 Peek 跟踪包裹的方法。
slug: haypost-tracking
published: 2026-11-26
updated: 2026-11-26
---

亚美尼亚邮政（HayPost，*"Haypost" Closed Joint-Stock Company*，亚美尼亚语 *Հայփոստ*）是亚美尼亚共和国的国家邮政运营商。该公司成立于 1994 年，并于 1998 年改制为国有封闭式股份公司，在亚美尼亚全国运营约 777 家邮局、23 个区域分拨中心和一座国际邮件互换中心，在万国邮政联盟（UPU）及欧洲邮政协会（PostEurop）框架下运营。你可以在[亚美尼亚邮政查询页面](https://www.haypost.am/en/track-trace)查询邮件，或直接将运单号粘贴到上方输入框中。

## 亚美尼亚邮政运单号：S10 格式及查找方式

运单号印在邮局寄件收据上，或位于网店的发货确认邮件中。HayPost 追踪带有校验位的标准 13 位 UPU S10 条码：

| 格式（虚构） | 含义 |
| --- | --- |
| `RR482917367AM` | 从亚美尼亚寄出的国际挂号信函或小包（`R` + 第二个字母 + 9 位数字 + `AM`）。 |
| `CP482917367AM` | 从亚美尼亚寄出的邮政包裹（`CP` + 9 位数字 + `AM`）。 |

对于从其他国家寄往亚美尼亚的入境国际 UPU S10 邮件（以寄出地两位 ISO 国家代码结尾，如 `US`、`DE` 或 `CN`），只要寄出地邮政传回电子发运数据或邮件进入亚美尼亚，HayPost 查询页同样可以查到。普通平邮不带 S10 条码，无法在线追踪。如果包裹使用的是商业快递单号，请查阅[运单号格式说明](guide:tracking-number-formats)（英文）。

## 亚美尼亚邮政物流状态含义

HayPost 返回英文万国邮政联盟（IPS）扫描记录，并标注运输方向：`(Otb)` 表示寄出地出境段，`(Inb)` 表示目的地入境段，`(EDI-received)` 表示寄出地邮政传输的电子发运数据。

| HayPost 显示 | 对你的含义 | Peek 显示 |
| --- | --- | --- |
| `Receive item from customer (Otb)` | 邮局已从寄件人处收寄。 | “已揽收” |
| `Insert item into bag (Otb)`、`Send item abroad (EDI-received)`、`Receive item at office of exchange (Otb)` / `(Inb)` | 已装袋准备出口、已通过电子数据预报发往国外，或已抵达国际邮件互换局。 | “运输中” |
| `Send item to customs (Otb)` | 离开寄出地前在出境互换局办理出口报关。 | “运输中” |
| `Send item to customs (Inb)`、`Record item customs information (Inb)` | 由目的地进口海关留存或查验。 | “清关中” |
| `Return item from customs (Inb)` | 海关已放行并交回邮政网络（并非退回寄件人）。 | “运输中” |
| `Send item to domestic location (Inb)`、`Receive item at location (Inb)`、`Receive item at sorting centre (Inb)`、`Item was delivered to the postal office (Inb)` | 经国内分拣中心流转，并送达负责投递的当地邮局。 | “运输中” |
| `Send item out for physical delivery (Inb)` | 已交由邮递员，今天安排派送。 | “派送中” |
| `Unsuccessful item delivery attempt (Inb)`、`The notification was sent to the customer: ...` | 派送未完成；HayPost 常附带原因与下一步安排（例如 `Addressee not available; Scheduled for further action/processing today`）。 | “派送未成功” |
| `Receive item at collection point for pick-up (Inb)` | 已在邮局备妥，等待领取。 | “待取件” |
| `Deliver item (Inb)` | 已交付并登记签收。 | “已签收” |

有两条描述最容易引起误解。第一，`Return item from customs (Inb)` 是好消息，表示海关查验结束并将包裹交回邮政继续派送。第二，`Item was delivered to the postal office (Inb)`（常由海外目的地邮政回传）表示当地邮局已签收接管该邮件，并不代表已经交到你本人手中。

## 亚美尼亚邮政国内、国际与特快服务

依托约 777 家邮局构成的网点（见[关于 HayPost](https://www.haypost.am/en/about-us)），HayPost 提供以下服务：

- **国内邮政服务（[haypost.am/en/postal-services/domestic-postal-services](https://www.haypost.am/en/postal-services/domestic-postal-services)）：** 明信片、信函、2 公斤以内小包、20 公斤以内包裹、埃里温市内可追踪局到局或送货上门的 **Yerevan Express Service（YES）**（09:00–16:00 下单当日处理，16:00 后次工作日处理，周六订单顺延至周一；预约电话 `+374 10 514 513`），以及面向 2 公斤以内可追踪国内电商小包的 **HayPost Sprint**。
- **国际邮政服务（[haypost.am/en/postal-services/international-delivery](https://www.haypost.am/en/postal-services/international-delivery)）：** 国际信函、2 公斤以内小包（长+宽+厚 ≤ 0.9 米，最长边 ≤ 0.6 米）、20 公斤以内包裹（任一边 ≤ 1.05 米）、30 公斤以内印刷品专袋（M-bag）及 EMS 特快专递。
- **寄往美国的 HayPost Express（[haypost.am/en/postal-services/Haypost-Express](https://www.haypost.am/en/postal-services/Haypost-Express)）：** 从亚美尼亚任意邮局空运至洛杉矶（在格伦代尔、范奈斯和西尔马设有取件点），最高 30 公斤，平均运输时效 7 至 10 个工作日。

**HayPost 常见问题。** [HayPost FAQ](https://www.haypost.am/en/faq) 收录了国内投递参考时限、清关费用、赔偿规则及 EMS 保管期限等问题；如需确认具体适用于你邮件的数字，请向当地邮局或 HayPost 咨询。

## 亚美尼亚邮政包裹延误、滞留海关或遗失

涉及经由邮政寄往或寄出亚美尼亚的网购订单，请先联系卖家处理退款或补发。

**跨境传输静默期。** 在出现 `Send item abroad (EDI-received)` 后，国际邮件需等待空运、抵达分拣及目的地互换局进口清关，期间可能连续几天没有新扫描。详见[物流停止更新的原因](guide:tracking-not-updating)（英文）。

**海关清关。** 寄入亚美尼亚的入境包裹以及发往欧盟或美国等地的 `AM` 邮件，均需经目的地进口清关（`Send item to customs (Inb)`）。如海关要求核验货值，请准备好购买发票和运单号。详见[海关滞留包裹指南](guide:customs)（英文）。

**已签收未收到、破损或退回。** 如果状态显示 `Deliver item (Inb)` 但你未拿到包裹，请先向同住人员及当地邮局核实（可在[HayPost 网点目录](https://www.haypost.am/en/our-network)查询网点），再联系卖家和 HayPost 客服。另见[显示已签收却未收到](guide:delivered-not-received)（英文）、[包裹破损处理](guide:damaged-parcel)（英文）、[包裹遗失处理](guide:lost-parcel)（英文）和[包裹被退回寄件人](guide:returned-to-sender)（英文）。

## 亚美尼亚邮政客服与联系方式

以下联系方式公布于 [haypost.am/en/contact-us](https://www.haypost.am/en/contact-us)（及 [haypost.am/am/contact-us](https://www.haypost.am/am/contact-us)）：

- **客服热线：** `+374 10 514-514`。
- **企业销售与服务：** `+374 10 544-444`。
- **YES 埃里温特快预约电话：** `+374 10 514 513`。
- **邮箱：** `info@haypost.am`。
- **总部地址：** 65/5 Arshakunyats Ave., Yerevan 0061, Republic of Armenia。
- **在线联系表单：** 见 [haypost.am/en/contact-us](https://www.haypost.am/en/contact-us)，其网站 [haypost.am](https://www.haypost.am/en) 同时提供在线客服聊天。

> 请警惕通过短信、WhatsApp 或邮件发来的陌生链接，诱导你填写银行卡信息支付小额“重新派送费”或“地址确认费”。请直接在 [haypost.am/en/track-trace](https://www.haypost.am/en/track-trace) 核验你的 S10 单号，或致电 `+374 10 514-514`。另见[快递诈骗短信识别](guide:delivery-scam-texts)（英文）。

## 使用 Peek 跟踪亚美尼亚邮政包裹

对于以 `AM` 结尾的 UPU S10 单号（如 `RR482917367AM` 或 `CP482917367AM`，数字为虚构），[Peek](/) 会自动首选 HayPost 作为寄出地邮政进行追踪；对于寄往亚美尼亚的入境 S10 邮件，选中 HayPost 后同样可以追踪。由于 Peek 没有专门解析 HayPost 网页链接的规则，请直接粘贴 13 位 S10 运单号；如需手动选择 HayPost，可在添加时点击“更换”或在包裹页点击“更换快递公司”后选择 HayPost（搜索“Armenia Post”或“Հայփոստ”同样能找到）。

Peek 直接读取 HayPost 公开的英文 JSON 查询接口，展示对应阶段（“已揽收”、“运输中”、“清关中”、“派送中”、“派送未成功”、“待取件”、“已签收”）、IPS 扫描原文（含失败原因与后续处理备注，如 `Addressee not available`）、邮局/国家位置（去除数字邮编）、目的地国家及送达时间。亚美尼亚境内扫描及 `Send item abroad (EDI-received)` 记录按埃里温时间解析，境外邮政扫描按当地时区解析（例如巴黎按法国时间），Peek 会统一转换为你的设备本地时间。如果 HayPost 预先发布带有未来时间的 `Send item abroad (EDI-received)` 计划记录（常提前数小时排班），Peek 会等到实际时间到达后再显示。HayPost 接口不提供预计送达日期、重量、服务名称、收寄件人信息或签收照片。

当你打开包裹页，或在派送当天开启通知时，Peek 最多每 10 分钟查询一次；在 HayPost 显示 `Send item out for physical delivery (Inb)` 的当天，只要 HayPost 自己的查件系统正常响应，自 HayPost 最新扫描所在地时间的早上 6 点起每 2 分钟查询一次。其余情况下每小时检查一次。若包裹停留在“已生成面单”或“已揽收”超过 12 小时，或连续两天无新动态，无论以何种方式关注均改为每小时检查一次；若在“已生成面单”停留满 7 天，则改为每 6 小时一次。当 HayPost 自己的查件系统无响应、Peek 仅通过物流查询服务跟踪时，会每小时查询一次，静默一天后每 3 小时一次，派送当天最多每 15 分钟一次。

若最新扫描已过 4 天，且包裹仍处于“已生成面单”、“已揽收”或“运输中”，Peek 会按该扫描日期做出提示，例如“自10月12日周一起没有物流更新”。包裹页面会显示“包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 HayPost 查询。”并提供前往 HayPost 联系页面的“向 HayPost 寻求帮助”链接。若超过三周没有新扫描，则会提示“已超过三周没有新动态。如果还没收到，请联系卖家。Peek 会持续向 HayPost 查询。”

Peek 可以按“每次扫描”、“重要环节”或“仅派送当天”通知你。Peek 仅监控物流动态，无法代你办理清关或向 HayPost 发起查单。

## 亚美尼亚邮政查询常见问题

### 亚美尼亚邮政的运单号长什么样？

HayPost 使用标准 13 位 UPU S10 邮政单号：从亚美尼亚寄出的邮件由两位字母、九位数字和结尾 `AM` 组成（例如 `RR482917367AM` 或 `CP482917367AM`，数字为虚构）。入境国际邮件保留寄出地国家的两位字母后缀。

### 为什么我的 HayPost 包裹显示 `Return item from customs (Inb)`？

这条 IPS 状态表示海关已清关放行，并将邮件交回邮政继续运输——并不是将包裹退回寄件人。

### HayPost 能查询从海外寄往亚美尼亚的包裹吗？

可以。只要寄出地邮政传输了电子发运数据，或 HayPost 在亚美尼亚境内完成了扫描，任何寄往亚美尼亚的国际可追踪 UPU S10 单号都可以在 [haypost.am/en/track-trace](https://www.haypost.am/en/track-trace) 或 Peek 中查询。

### 如何联系 HayPost 客服？

拨打 `+374 10 514-514`（企业服务拨打 `+374 10 544-444`）、发送邮件至 `info@haypost.am`，或使用 [haypost.am/en/contact-us](https://www.haypost.am/en/contact-us) 的联系表单及 HayPost 网站的在线客服聊天。

:::sources
- [HayPost: Home](https://www.haypost.am/en) – 国家邮政运营商门户
- [HayPost: Track & Trace](https://www.haypost.am/en/track-trace) – 在线邮件查询
- [HayPost: About Us](https://www.haypost.am/en/about-us) – 1994 年成立、1998 年起为封闭式股份公司、约 777 家邮局、23 个区域分拨中心、UPU 与 PostEurop 成员
- [HayPost: Domestic Postal Services](https://www.haypost.am/en/postal-services/domestic-postal-services) – 2 公斤小包、20 公斤包裹、YES 埃里温特快（`+374 10 514 513`）与 HayPost Sprint
- [HayPost: International Delivery](https://www.haypost.am/en/postal-services/international-delivery) – 国际小包（2 公斤）、包裹（20 公斤）、M-bag（30 公斤）与 EMS 的限制
- [HayPost: HayPost Express](https://www.haypost.am/en/postal-services/Haypost-Express) – 空运包裹至洛杉矶（30 公斤，7–10 个工作日）
- [HayPost: Our Network](https://www.haypost.am/en/our-network) – 亚美尼亚邮局目录
- [HayPost: Contact Us](https://www.haypost.am/en/contact-us) – `+374 10 514-514`、`+374 10 544-444`、`info@haypost.am`、埃里温 65/5 Arshakunyats Ave.
- [HayPost: Contact Us (Armenian)](https://www.haypost.am/am/contact-us) – Peek 链接的帮助页面
- [HayPost: FAQ](https://www.haypost.am/en/faq) – 常见问题目录
- [Universal Parcel Scraper: haypost](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/haypost) – S10 `AM` 单号识别、`api.haypost.am` JSON 查询、IPS 状态对应、埃里温时间、过滤未来时间的 EDI 记录与帮助链接
:::
