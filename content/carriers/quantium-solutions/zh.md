---
title: Quantium Solutions 快递查询：单号、状态与客服
description: 查询亚太电商物流 Quantium Solutions 包裹：10 位与邮政单号格式、中转清关状态含义、各国末端派送、关税规则与联系方式。
slug: quantium-solutions-tracking
published: 2026-11-27
updated: 2026-11-27
---

Quantium Solutions 是一家业务覆盖亚太区 10 个国家/地区的电商物流公司，承运进出亚太及区域内的网购包裹。你可以在 Quantium 官方 [Track Your Shipments](https://track.quantiumworldwide.com/) 页面查询，或将单号粘贴到上方输入框。单号由商家在发货邮件或订单页面提供。

## Quantium Solutions 快递单号是什么格式的

Quantium 查询页要求填写“Tracking/Reference number”，支持一次输入多个单号（每行一个）。Peek 代码收录了两种 Quantium 单号格式，但这两种格式都不是 Quantium 独有的。以下以虚构数字举例：

| 单号（虚构示例） | 含义 |
| --- | --- |
| `4829173654` | 10 位数字：在 Peek 代码中用于马来西亚货件。由于 DHL 等多家承运商也用 10 位数字，Peek 无法仅凭格式识别为 Quantium。 |
| `UQ482917367TH` | `UQ`、9 位数字加 `TH`：在 Peek 代码中用于泰国货件。因符合国际邮政单号结构，Peek 会将其识别为 Thailand Post。 |
| `RA482917367SG` | 2 位字母、9 位数字加 `SG`：[新加坡邮政（Singapore Post）](carrier:singapore-post)单号。Peek 代码注明 Quantium 也用新邮政单号承运部分包裹，Peek 会优先识别为 Singapore Post。 |

Peek 的 Quantium 查询功能仅支持上述两种格式，粘贴时空格与小写字母不影响识别。若为其他格式的参考号，请直接使用 Quantium 官网查询页。找不到单号？请参阅[如何查找快递单号](guide:find-tracking-number)（英文）和[快递单号格式指南](guide:tracking-number-formats)（英文）。

## Quantium Solutions 物流状态详解

Quantium 查询页的每条轨迹都包含里程碑（milestone），并常附有一段备注（remark，即当地处理伙伴的节点描述）。若备注包含更多信息，Peek 会在里程碑后加冒号合并显示。Peek 已知状态按常见顺序如下：

| Quantium 显示状态 | 对你的含义 | Peek 显示 |
| --- | --- | --- |
| `Shipment created` | 商家已预订货件，Quantium 尚未收到包裹。 | “已创建运单” |
| `Shipment received` | Quantium 已揽收包裹。 | “快递公司已收件” |
| `Shipment under processing at origin hub` | 正在发件国枢纽分拣处理。 | “运输途中” |
| `Shipment despatched to transshipment hub` | 正发往中转枢纽衔接下一程。 | “运输途中” |
| `DEPART ORIGIN` | 已离开始发地。 | “运输途中” |
| `Shipment received at transshipment hub: Arrive Hub` | 已抵达中转枢纽。 | “运输途中” |
| `Shipment despatched to overseas: Depart Hub` | 已离开枢纽发往你的国家。 | “运输途中” |
| `READY FOR DEPARTURE`、`FLIGHT DEPARTURE TO DESTINATION` | 等待航班起飞，随后飞往目的国。 | “运输途中” |
| `Shipment arrived at destination port: Arrive Destination` | 已抵达目的国口岸。 | “运输途中” |
| `IN TRANSIT: Customs Clearance Begins` | 目的国海关开始办理清关。 | “清关中” |
| `Shipment released from customs/ government check point: Customs Cleared` | 海关已放行。 | “运输途中” |
| `Shipment received at delivery branch: Handed Over for Delivery` | 已移交当地派送网点。 | “运输途中” |
| `IN TRANSIT` | 运输途中，目的国合作方的分拣扫描常以此形式出现。 | “运输途中” |
| `Shipment out for delivery` | 正在派送途中。 | “正在派送” |
| `Shipment delivered` | 已送达。如备注写明签收人姓名，Peek 会按 Quantium 原文保留在该条记录内。 | “已送达” |

未收录的记录会按 Quantium 原文显示，并由 Peek 尽量根据措辞归类阶段。若单号查无记录，Quantium 页面会显示“No record!”。更多见[物流状态详解](guide:tracking-statuses)（英文）。

**时间显示。** Quantium 查询页每条记录只有日期和时间、不含时区，按实际扫描方的当地时钟记录：前期为发件国时间，后期为目的国合作方时间，因此不同国家的两条记录不能直接按小时相减。

## 谁负责派送 Quantium Solutions 包裹

Quantium 向商家提供四种跨境产品：

| 服务产品 | 是否可追踪 | 派送渠道 | 参考时效 | 关税与税费承担方 |
| --- | --- | --- | --- | --- |
| Q-Simple | 不可追踪 | 邮政渠道 | 4 至 17 天 | 收件人（DDU） |
| Q-Smart | 可追踪 | 邮政渠道 | 4 至 10 天 | 收件人（DDU） |
| Q-Speed | 可追踪、优先 | 邮政渠道 | 3 至 7 天 | 收件人（DDU） |
| Q-Sure | 可追踪 | 商业快递 | 2 至 5 天 | 寄件人或收件人（DDP 或 DDU） |

Peek 不会显示包裹选用的是哪种服务，请向商家确认。

**亚洲地区。** Quantium 自有末端派送支持送货上门、邮局自提、包裹柜及亚洲 3,000 多个自提点，并支持货到付现或电子支付；其 Regular 常规派送时效为 2 至 4 天。

**澳大利亚。** Quantium 澳大利亚子公司 Quantium Solutions (Australia) Pty Ltd 的燃油附加费页面显示，其在澳境内通过快递公司（包括 CouriersPlease）以及 [Australia Post](carrier:australia-post) eParcel 和 express eParcel 派送，官网也将 CouriersPlease 列为官方合作伙伴。具体派送尝试次数和未领包裹存放点均按实际派送商规则执行。

若上门时无人在家，参阅[错过快递派送](guide:missed-delivery)（英文）和[未领取包裹处理](guide:uncollected-parcel)（英文）。

## Quantium 包裹海关与关税说明

在 Q-Simple、Q-Smart 和 Q-Speed 下，Quantium 标注关税和税费由收件人支付（完税前交货，DDU）；Q-Sure 可选由寄件人包税（DDP）或收件人自付（DDU）。请向商家确认订单条款；具体是否起征视目的国规定，另见[包裹卡在海关怎么办](guide:customs)（英文）。

**寄往欧盟。** 自 2026 年 7 月 1 日起，从欧盟境外网购且货值不超过 150 欧元的包裹，按商品种类每项征收 3 欧元临时欧盟关税；自 2026 年 11 月 1 日起，无论货值多少，另按每项征收 2 欧元欧盟处理费（“项”指包裹中税则归类、品名及必要时的原产地相同的商品）。Quantium 在其商户公告中说明，无论采用 IOSS 还是 DAP 清关、无论货值高低，每条报关品项均收取该 2 欧元费用，目的国另有要求时还需加收增值税。请向商家确认售价是否已包含此费用。

## Quantium Solutions 停更、丢失或退回怎么办

**仅有 `Shipment created`。** 商家已建单但 Quantium 尚未扫收包裹。如多日未变，请向商家确认是否已真正寄出。

**途中无更新。** 包裹停在“已创建运单”、“快递公司已收件”或“运输途中”满四天无新扫描时，Peek 会标出最新扫描日期（“自……以来无物流更新”），提示“包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 Quantium Solutions 查询。”并提供指向 Quantium 客户支持表单的“联系 Quantium Solutions 获取帮助”链接。Quantium 会在 News 页面发布延误公告（例如 2026 年 10 月 7 日关于马来西亚与新加坡严重雾霾的通告）。此外，官网“Tracking No.”栏有时会列出合作方的换单号，也可去对应承运商官网查看。另见[物流为何停止更新](guide:tracking-not-updating)（英文）。

**显示已送达但没收到。** 先查看家门口附近并询问邻居，再联系商家和 Quantium。参阅[显示已签收但未收到](guide:delivered-not-received)（英文）。

**丢失或破损。** Quantium 官网未向收件人提供理赔流程。商家是 Quantium 的客户，请联系商家申请退款或补发。参阅[包裹丢失](guide:lost-parcel)（英文）和[包裹破损](guide:damaged-parcel)（英文）。

**退回寄件人。** Quantium 为商家提供退回始发地服务。参阅[包裹被退回寄件人](guide:returned-to-sender)（英文）。

## Quantium Solutions 客服与联系方式

Quantium 官方渠道如下：

- **在线表单：** [客户支持表单](https://www.quantiumsolutions.com/contact-support/)，可用于查询包裹、一般咨询和反馈。需填写姓名、邮箱、发件国和追踪参考号（Customer ID 一栏注明“仅限企业客户”）。Quantium 承诺由当地客服在下一个工作日内回复。
- **马来西亚：** (603) 5569 6901、customerservice.my@quantiumworldwide.com。
- **泰国：** (662) 712 2999、customerservice.th@quantiumworldwide.com。
- **其他地区：** [Contact Us](https://www.quantiumsolutions.com/contact-us/) 页面列出了中国香港、印尼、中国台湾和越南的支持邮箱；新加坡仅提供销售邮箱，未列出澳大利亚联系方式。均未注明办公时间。

如为网购订单，请先联系下单订运的商家。

## 使用 Peek 追踪 Quantium Solutions 包裹

[Peek](/) 无法单凭格式自动判定 Quantium 单号，但仍请直接粘贴：Peek 支持超过 4,500 家快递公司。遇到 10 位数字时，Peek 会先询问相近格式的承运商，再查询追踪服务（显示“正在查询物流服务…”）；遇到校验位有效、以 `SG` 或 `TH` 结尾的国际邮政单号时，Peek 会直接标为 Singapore Post 或 Thailand Post（“识别出的快递公司”）。如需查看 Quantium 自家轨迹，点击“更改”选择 Quantium Solutions 即可（已添加包裹使用“更改快递公司”）。若对其他格式单号强行选定 Quantium，Peek 会提示其“不会签发这种格式的单号”。

带有 `trackno=` 的 Quantium 查询页链接也可直接粘贴，Peek 会提取单号并立即识别为 Quantium Solutions。

Peek 展示 Quantium 当前阶段、上表所示原话轨迹和目的国，略去客户参考号、合作方单号和官网显示的始发地。由于 Quantium 不提供单条扫描地点、预计送达日期、重量或签收证明，Peek 均不显示。“打开 Quantium Solutions 官网”可直接跳转官方页面查看该单号。

因 Quantium 时间不含时区，发往新加坡、马来西亚、泰国或澳大利亚（澳大利亚按悉尼时间）的包裹，Peek 按目的国时钟解读全部时间；发往其他国家时，在抵达目的国口岸（`Shipment arrived at destination port: Arrive Destination`）之前按新加坡时间解读，之后按目的国时间（每国单一时区，美国按纽约时间）解读，并全部换算为你设备本地时区，因此跨国扫描可能存在数小时误差。

当单号属于校验位有效的国际邮政格式且 Quantium 给出了目的国时，Peek 还会用同一单号向目的国邮政查询，例如 [Australia Post](carrier:australia-post)、[USPS](carrier:usps)、[Royal Mail](carrier:royal-mail)、An Post、[Canada Post](carrier:canada-post) 或 [NZ Post](carrier:nz-post)，一旦对方出现最新扫描即自动接续跟进并保留 Quantium 原轨迹。但并非所有国家都适用（例如新加坡、马来西亚、泰国、中国香港或越南不触发），且 10 位纯数字单号绝不触发。

当你打开包裹页面或在派送日开启通知时，只要 Quantium 官网正常响应，Peek 最高每 10 分钟直接查询一次，在出库派送当天自新加坡时间早上 6 点起最高每 2 分钟查询一次；其余时间及连续两天无新动态后（处于“已创建运单”或“快递公司已收件”状态时为 12 小时）改为每小时一次，在“已创建运单”满七天无新动态后改为每 6 小时一次。通知支持“每次扫描”、“重要节点”或“仅在送达时”。Peek 无法代你联系 Quantium 或商家，请使用上方联系方式。

## Quantium Solutions 快递查询常见问题

### Quantium Solutions 和 Quantium Worldwide 是一家公司吗？

是的。它的查询系统和支持邮箱都使用 quantiumworldwide.com 域名，Peek 也将其识别为 Quantium 或 Quantium Solutions International。

### 为什么 Peek 把我的 Quantium 单号识别成了 Singapore Post 或 Thailand Post？

因为你的单号采用国际邮政编号格式，尾缀分别指向对应国家邮政（`SG` 为新加坡邮政，`TH` 为泰国邮政）。若想查看 Quantium 自家轨迹，点击“更改”手动选择 Quantium Solutions 即可。

### “Customs Clearance Begins” 是什么意思？

表示目的国海关已开始为包裹办理清关，Peek 会将其显示为“清关中”。放行后会出现 `Shipment released from customs/ government check point: Customs Cleared`。如果是 DDU 服务，相关税费需由收件人支付，另见[包裹卡在海关怎么办](guide:customs)（英文）。

### Quantium Solutions 派送需要多久？

Quantium 给出的参考时效为 Q-Sure 2 至 5 天，最慢的 Q-Simple 可达 17 天（见上表）；亚洲区内 Regular 派送为 2 至 4 天。

### Q-Simple 包裹可以全程追踪吗？

不可以。Quantium 将 Q-Simple 定位为经济型无追踪服务，运输途中通常不会有扫描更新，请向商家确认发货时间。

:::sources
- [Quantium Solutions: Track Your Shipments](https://track.quantiumworldwide.com/) – the tracker, its "Tracking No." column, "No record!"
- [Quantium Solutions: Home](https://www.quantiumsolutions.com/) – presence in 10 countries in Asia Pacific
- [Quantium Solutions: About Us](https://www.quantiumsolutions.com/about-us/) – what Quantium does, CouriersPlease among its partners
- [Quantium Solutions: Cross Border Shipping](https://www.quantiumsolutions.com/cross-border-shipping/) – the four services, return to origin
- [Quantium Solutions: Last Mile Delivery](https://www.quantiumsolutions.com/last-mile-delivery/) – delivery options in Asia
- [Quantium Solutions: Australia Fuel Surcharges](https://www.quantiumsolutions.com/australia-fuel-surcharges/) – CouriersPlease, Australia Post eParcel
- [Quantium Solutions: Service Impact Notice (haze)](https://www.quantiumsolutions.com/news/13/) – haze delays
- [Quantium Solutions: Service Update (EU customs handling fee)](https://www.quantiumsolutions.com/news/14/) – €2 fee per declaration line
- [Quantium Solutions: Customer Support Form](https://www.quantiumsolutions.com/contact-support/) – what the form asks, reply time
- [Quantium Solutions: Contact Us](https://www.quantiumsolutions.com/contact-us/) – phones and mailboxes
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – €3 duty, €2 handling fee
- [European Commission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – what an item is
- [Universal Parcel Scraper: Quantium Solutions](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/quantium-solutions) – number shapes, links, wordings, clocks, fields
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – detection, the clock Quantium's times are read on, hand-off to the destination post
:::
