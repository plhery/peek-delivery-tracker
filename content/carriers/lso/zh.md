---
title: LSO 快递查询：空运单号、状态与派送协助
description: 查询美国 LSO（Lone Star Overnight）快递包裹：ZY0 开头空运单号格式、物流状态含义、派送时效、理赔期限与客服电话。
slug: lso-tracking
published: 2026-11-27
updated: 2026-11-27
---

LSO（Lone Star Overnight 简称）是一家总部位于美国得克萨斯州的区域包裹快递公司，派送范围覆盖得克萨斯州和俄克拉荷马州全境，以及路易斯安那州、阿肯色州、堪萨斯州和密苏里州的主要都会区。要追踪包裹，请在 [LSO 查询页](https://www.lso.com/tracking)输入单号，或直接粘贴到上方输入框。LSO 将此单号称为空运单号（airbill number），由委托 LSO 发货的商家或寄件人提供。

## LSO 快递单号格式及查找位置

LSO 查询页要求输入“Airbill No”，每张面单都配有唯一的单号。LSO 未说明收件人从何处获取该单号：请查看商家的发货通知或订单页面，如未列出请向寄件人索取。更多查找方法请参阅[如何查找快递单号](guide:find-tracking-number)（英文）。

LSO 未公布单号格式。目前见到的 LSO 空运单号均为 8 位字符：`ZY0` 后接 5 位字母或数字。

| 单号（虚构示例） | 含义 |
| --- | --- |
| `ZY048291` | LSO 空运单号：`ZY`、数字零加 5 位字符。 |
| `ZY0K7M4P` | 同样是 LSO：后 5 位可以包含字母。 |
| `ZYO48291` | 输入错误：把数字零打成了字母 `O`，已不符合 LSO 格式。 |

第三位必须是数字零（`0`）。若错输为字母 `O`，该单号将不再匹配 LSO，Peek 可能会转而推荐 GLS。粘贴到 Peek 时，空格、连字符和小写字母均不影响识别。LSO 自己的查询页单次最多可同时查 10 个单号，每行一个。

## LSO 物流状态详解

LSO 网站仅提供英文版。查询页上方是一条包含五个阶段的进度条，下方列出空运单号、自由文本状态行、送达日期、签收人和收件地址。五个阶段按包裹经历的先后顺序排列如下：

| LSO 阶段 | 对你的含义 |
| --- | --- |
| `Label created` | 寄件人已创建运单，LSO 可能尚未收到包裹。 |
| `Picked Up` | LSO 已从寄件人处揽收包裹。 |
| `Inbound scan at destination` | 已在负责你所在区域的 LSO 站点完成入站扫描。 |
| `Out for Delivery` | 今日已装车派送。 |
| `Delivered` | 已送达，查询页可能列出签收人姓名。 |

`Label created` 最容易引起误解：它只说明寄件人打好了面单，并不表示包裹已开始运输。如果连续多日停留在此状态，通常是商家还没把货交给 LSO，请联系商家。`Inbound scan at destination` 听起来像已送达，实际只表示包裹抵达了你附近的站点，接下来才会安排派送。

自由文本状态行有时会给出比五个阶段更具体的信息。LSO 未公布这些文本的对照表，请按原文阅读；如有疑问可直接联系 LSO。

## LSO 派送日期、时效与签收要求

**派送工作日。** 在多数地区，LSO 于周一至周五揽收和派送。LSO Ground、Early Overnight 和 Economy Next Day 均不提供周六派送。法定假日揽派安排公布在 LSO 的节假日页面。

**派送时效。** 取决于寄件人所选服务：LSO Ground 为 1 至 3 个工作日；次日达服务中，Early Overnight 于上午 8:30 前送达，Priority Next Day 于上午 10:30 前送达（部分偏远农村地区为中午或当天结束前），Economy Next Day 于下午 3:00 前送达；另有 2nd Day 次次日达服务。LSO 的 Ground 时效保障不适用于其公布的高峰季期间交寄的包裹。

**签收要求。** 上午 8:30 送达的 Early Overnight 不收集签收签名。LSO Ground 服务中，寄件人可额外付费要求成人签收。

**自提点与改派。** LSO 未向收件人提供自提点或自提柜服务；其网点查询页按邮编列出的服务中心和投递箱仅供寄件使用。LSO 也不提供收件人自助改期或改派入口，如需调整请使用下方联系表单。

## LSO 包裹未送达、延误或破损怎么办

**派送延误。** LSO 查询页写明，如果包裹延误超过 4 天，可在工作时间点击该页的“Text with a live agent!”请人工客服发短信告知进展。物流停滞的一般情况见[物流长期不更新](guide:tracking-not-updating)（英文）。

**超出 LSO 派送区域。** 美国其余地区由 LSO 与其他快递公司合作派送。

**包裹破损。** 破损索赔必须在规定期限内提交，LSO 须在 15 天内收到申请。理赔结案前请妥善保留全部包装材料以备查验。

**延误或丢失。** 丢失或延误索赔须在 30 天内提交。

**由谁发起理赔。** 只有 LSO 账号持有人可以提交理赔。如果你是收件人，LSO 要求你联系供货方；网购订单请直接联系商家。如物流显示已签收却未收到货，请阅读[显示已签收但未收到](guide:delivered-not-received)（英文）。

## LSO 客服电话与联系方式

根据 lso.com 公布的信息：

- **客服电话：** (888) 688-6909（列于 lso.com 各页页脚，未公布接听时间）。
- **电子邮箱：** customerservice@lso.com。
- **收件人表单：** 前往[联系页面](https://www.lso.com/contact-us)选择收件人表单，LSO 表示会在 24 小时内回复。
- **短信人工客服：** 包裹延误超过 4 天时，可在常规工作时间通过[查询页面](https://www.lso.com/tracking)发起短信咨询。

联系前请准备好空运单号。LSO 节假日页面上的另一号码 866.215.8009 仅用于物料申领和上门取件预约。

## 使用 Peek 追踪 LSO 包裹

遇到 `ZY0` 格式的单号时，[Peek](/) 会直接识别为 LSO。带有 `airbillnos=` 的 lso.com/tracking 链接或带有 `barcodes=` 的 app.lso.com/tracking 链接也可直接粘贴，无需账号、邮编或手机号。

Peek 无法读取 LSO 自己的查询页，而是通过物流追踪服务跟进 LSO 包裹，并按这些服务的原文展示状态与轨迹。这类轨迹常常比 LSO 的页面晚数小时，部分包裹可能暂时查不到记录。Peek 对 LSO 不显示签收证明或自提点，仅当其中之一的 17TRACK 转发了 LSO 的预计日期时才会显示预计送达时间。

Peek 默认每小时查询一次；连续一天无新扫描后改为每 3 小时一次；在“已生成面单”状态连续七天无新动态后改为每 6 小时一次。在包裹出库派送当天，只要你打开包裹页面或开启通知，Peek 最高每 15 分钟检查一次。

当包裹仍处于“已生成面单”、“已揽收”或“运输中”且最新扫描已满四天时，Peek 会以该扫描日期标记提示（例如“自10月12日周一起没有物流更新”），并在详情页显示“包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 LSO 查询。”以及跳转至 LSO 联系页面的“向 LSO 寻求帮助”链接。超过三周无新扫描时，则会提示“已超过三周没有新动态。如果还没收到，请联系卖家。Peek 会持续向 LSO 查询。”

Peek 可按“每次扫描”、“重要环节”或“仅派送当天”发送通知。如需查看签收人、提交理赔或处理派送问题，请联系 LSO 或商家。

## LSO 快递查询常见问题

### 谁负责派送 LSO 包裹？

在 LSO 自有网络覆盖区域（得克萨斯州、俄克拉荷马州，以及路易斯安那、阿肯色、堪萨斯和密苏里州的主要都会区）由 LSO 自行派送；覆盖区以外则与合作快递公司联合派送。

### LSO 周六派送吗？

LSO Ground、Early Overnight 和 Economy Next Day 均不提供周六派送，其服务页面已明确说明。其他服务请向 LSO 确认。

### LSO 的客服电话是多少？

lso.com 公布的客服电话为 (888) 688-6909。你也可以发邮件至 customerservice@lso.com，或在联系页面填写收件人表单。

### LSO 派送需要签收吗？

上午 8:30 送达的 Early Overnight 不需要签收签名。LSO Ground 方面，寄件人可付费加选成人签收服务。

:::sources
- [LSO: Lone Star Overnight home page](https://www.lso.com/) – who LSO is, coverage, partner carriers, services
- [LSO: Online Package Tracking](https://www.lso.com/tracking) – tracker, up to 10 numbers, text with an agent after 4 days, phone and email
- [LSO: Contact Us](https://www.lso.com/contact-us) – recipient form, reply within 24 hours, phone, email
- [LSO: Frequently Asked Questions](https://www.lso.com/faq) – one unique tracking number per label
- [LSO: Claim Form](https://www.lso.com/lso-claim-form) – 30 and 15 days, packaging kept for inspection, account holders only, recipients go to the vendor
- [LSO: Express Shipping](https://www.lso.com/express-services) – next-day delivery times, no signature at 8:30 am, no Saturday delivery for Early or Economy, Monday to Friday pickup
- [LSO: Ground Shipping](https://www.lso.com/lso-ground-services) – 1 to 3 business days, no Saturday delivery, peak-season exception, adult signature fee
- [LSO: Holidays](https://www.lso.com/holidays) – holiday schedule, the supplies and pickups line
- [LSO: Dropbox Locations](https://www.lso.com/branch-locator) – service centres and drop boxes by ZIP code
- [LSO: Tracking app](https://app.lso.com/tracking) – progress steps and result fields
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – how Peek recognises and follows LSO parcels
:::
