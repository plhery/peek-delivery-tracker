---
title: Mainfreight 物流查询：运单号与货运状态详解
description: 查询新西兰、澳大利亚及美国 Mainfreight 陆运、空运与海运货物：11 位运单号及参考号、物流状态含义、海关规则与理赔说明。
slug: mainfreight-tracking
published: 2026-11-27
updated: 2026-11-27
---

Mainfreight 是一家经营公路、航空和海运货运的物流集团。Peek 的快递目录将其归类于新西兰（总部所在地）、澳大利亚和美国；其官方查询系统还支持欧洲公路货运与集装箱动态。如需追踪由 Mainfreight 承运的货运或大件物品，请将订舱方提供的托运单号粘贴到上方输入框，或直接前往官网 www.mainfreight.com 查询。

## Mainfreight 运单号是什么格式的

以下以虚构数字举例说明：

| 单号（虚构示例） | 含义 |
| --- | --- |
| `48291736542` | 11 位数字：新西兰、澳大利亚或美国的公路货运托运单号，也是 Peek 目录中为 Mainfreight 收录的唯一标准格式。 |
| 字母加数字 | 作业编号（job number）、分提单号（house bill）或寄件人自有参考号。手动选定 Mainfreight 后，Peek 支持用 4 至 40 位字母数字查询。 |
| 主提单号（master bill） | 11 位航空主运单号或海运主提单号：只要仅对应唯一一票空运或海运作业，Peek 即可查到。 |

Peek 目录还将 Mainfreight Group、Mainfreight Transport 和 Mainfreight Air & Ocean 识别为 Mainfreight 的别名。

**单号在哪里找。** 请在发货邮件或订单页查看；如为企业间货运，请联系寄件方索取。另见[如何查找快递单号](guide:find-tracking-number)（英文）。

**同样使用 11 位数字的其他承运商。** 11 位数字也符合 [GLS Germany](carrier:gls-de)、[GLS France](carrier:gls-fr)、[DHL eCommerce Poland](carrier:dhl-ecommerce-pl)、PostLogistics 等公司格式。如果邮件写的是其中某一家，请前往对应公司查询。更多见[快递单号格式指南](guide:tracking-number-formats)（英文）。

## Mainfreight 物流状态含义

**新西兰与澳大利亚公路货运：**

| Mainfreight 显示状态 | 对你的含义 | Peek 显示 |
| --- | --- | --- |
| `Information Received` | Mainfreight 已收到订单信息，尚未拿到货物。 | “已创建运单” |
| `Pickup Allocated to Driver` | 已指派司机上门取货，货物仍在寄件人处。 | “已创建运单” |
| `Picked Up` | 已从寄件人处揽收。 | “快递公司已收件” |
| `In Transit` | 正在运输途中。 | “运输途中” |
| `At Delivery Depot` | 已抵达负责派送的站点。 | “运输途中” |
| `On Delivery Vehicle` | 已装上派送货车。 | “正在派送” |
| `Delivered` | 已送达。 | “已送达” |

**美国公路货运。** 美国节点会在状态后附带地点，Peek 会将其显示在状态旁：

| Mainfreight 显示状态 | 对你的含义 | Peek 显示 |
| --- | --- | --- |
| `Booked` | 运输已预订。 | “已创建运单” |
| `Pickup assigned to driver` | 已安排司机取货。 | “已创建运单” |
| `Goods picked up` | 已从发货方取走货物。 | “快递公司已收件” |
| `Departed from Terminal`、`Arrived at Terminal` | 已离开或抵达货运站。 | “运输途中” |
| `On drivers vehicle en-route` | 已装车发往收件地址。 | “正在派送” |
| `Goods delivered` | 已送达。 | “已送达” |
| `Collected by Customer` | 已由客户在货运站自提。 | “已送达” |

**空运与海运货件：**

| Mainfreight 显示状态 | 对你的含义 | Peek 显示 |
| --- | --- | --- |
| `Booking Confirmation Sent` | 订舱已确认。 | “已创建运单” |
| `Pickup Cartage Advised` | 已预约提货卡车。 | “已创建运单” |
| `Pick up Cartage Complete` | 出口货物已完成提货。 | “快递公司已收件” |
| `Origin Customs Cleared` | 出口报关放行（起运前）。 | “运输途中” |
| `Departure from First Load Port`、`Arrival at Final Discharge Port` | 已从首个装货港/机场启程，或抵达最终卸货港/机场。 | “运输途中” |
| `Customs Clearance Commenced` | 进口清关已开始。 | “清关中” |
| `Customs Clearance Finalised` | 清关完成，继续转运。 | “运输途中” |
| `Cargo Available` | 货物在目的地已放行，准备提货或派送。 | “运输途中” |
| `Cargo Delivered`、`Full Container Delivered` | 已交付。 | “已送达” |

标注为预计（estimated）的计划起降或到离港节点尚未实际发生，Peek 会将其省略。

**其他节点。** 集装箱在场站间的调运（如 `Picked Up (Site Movement)`）显示为“运输途中”，`Final Delivery (Empty to Customer)` 显示为“已送达”。欧洲公路货运中，`Order received` 显示为“已创建运单”，`Out for delivery` 显示为“正在派送”。若暂无具体节点，Peek 会采用查询系统返回的整体状态（如 `Created` 或 `Final Delivery`）。

Peek 代码未收录 Mainfreight 针对派送未成功、异常或退件的专用措辞。其他节点会按 Peek 通用规则归类，或原样展示而不改变当前阶段。详见[物流状态详解](guide:tracking-statuses)（英文）。

## Mainfreight 如何派送

由于 Peek 无法抓取 Mainfreight 官网介绍页，本页不列出派送时效、预约流程、派送尝试次数或网点自提规则。请向订舱发货方确认货物送货上门的具体方式与时间。

Peek 代码不会把 Mainfreight 货件交接给其他承运商。Mainfreight 查询系统可为空运、海运及欧洲陆运提供预计送达日期，Peek 会在货件出库派送前一直展示；系统不向 Peek 提供签收证明或签收人姓名。

**海关规定。** 对于运入新西兰的货物，新西兰海关（NZ Customs）说明：

- 从境外网站购买且货值不超过 1,000 新西兰元的商品免征关税，并由境外卖家代收商品及服务税（GST），除非其属于总值超过 1,000 新西兰元的合并货件。烟草和酒类无论货值高低均须缴纳关税和 GST。
- GST 税率为货值加任何关税、运费及保险费之和的 15%。除非符合豁免条件，所有进口货物均需缴纳海关及初级产业部货物税费（Customs and MPI Goods Levy），由报关行或货代垫付并可能向你收取。
- 货值满 1,000 新西兰元及以上的货件需要海关编号（Customs Number），费用缴清前海关不予放行。

其他国家清关情况请见[包裹卡在海关怎么办](guide:customs)（英文），并向订舱方确认税费由谁承担。

## Mainfreight 货件延误、停滞、破损或丢失怎么办

**暂未查到。** 你归入 Mainfreight 名下但官网尚未收录的货件会显示“等待 Mainfreight 更新”，Peek 会继续重试。粘贴 11 位数字且所有承运商均未查到时，会显示“暂无物流记录”。请核对数字并向寄件方确认 Mainfreight 是否已上门提货。

**长期不动。** 若货件在“已创建运单”、“快递公司已收件”或“运输途中”状态连续四天无新节点，Peek 会按最近一次节点日期给出标记（如“自 10 月 12 日周一以来无物流更新”）。请向商家或寄件方了解原因，并参阅[物流长期不更新](guide:tracking-not-updating)（英文）。

**破损、短少或丢失。** 请先联系订舱的商家或寄件方。在新西兰和澳大利亚，理赔遵循 Mainfreight 条款和当地法律；在美国，涉及卡车公司跨州公路运输的货物适用美国联邦法律：

- 承运人对货物的实际灭失或损坏承担责任，除非托运人申报了较低价值或与承运人书面约定了限额。承运人规定的理赔提交期限不得短于 9 个月，自书面拒绝理赔任何部分之日起的起诉期限不得短于 2 年。
- 正式索赔须采用书面形式，明确识别该票货件、主张承运人责任并提出具体或可确定的赔偿金额；仅在送货回单上注明破损或短少本身不构成正式索赔。承运人可要求提供提单、运费支付凭证和发票。
- 承运人须在 30 天内书面确认收到索赔，并在 120 天内书面赔付、拒绝或提出明确和解方案；未能办结时，须在第 120 天及此后每 60 天书面通报进展。

如显示 `Delivered` 却未收到货，参阅[显示已签收但未收到](guide:delivered-not-received)（英文）；更多见[包裹破损](guide:damaged-parcel)（英文）和[包裹丢失](guide:lost-parcel)（英文）。

## Mainfreight 客服与联系方式

由于 Peek 无法抓取 Mainfreight 官网页面，本页不列出电话、邮箱或分支机构：Peek 仅刊载直接从承运商官网核实到的联系方式。

请先联系为您订舱发货的一方（网购订单联系商家或供应商，其他货运联系发货企业），由合同持有方直接对接 Mainfreight。

## 使用 Peek 追踪 Mainfreight 货件

在新西兰、澳大利亚或美国添加 11 位数字时，Peek 会同步向 Mainfreight 以及 GLS France、GLS Germany、GLS Switzerland 和 PostLogistics 查询（显示“正在查询物流服务…”）；若只有 Mainfreight 查到该货件便直接显示其名称，若多家均有记录则请你手动选择。在其他国家添加 11 位数字时，Peek 不会自动询问 Mainfreight，请按下方方式手动选定。

其他格式单号无法让 Peek 自动识别为 Mainfreight。尽管粘贴：Peek 支持超过 4,500 家快递公司。随后点击“更改”选择 Mainfreight（11 位数字可在“符合这个单号”下找到，其余格式请按名称搜索）；对已添加货件请用“更改快递公司”。粘贴带单号的 Mainfreight 查询链接（含 `trackingnumber=` 的 mainfreight.com/tracking 或 mainfreight.com/track/…）则会立即识别为 Mainfreight（“识别出的快递公司”）。

Peek 查询无需账号或邮编。当 Mainfreight 统一搜索查不到 11 位数字时，Peek 会同时查询其新西兰、澳大利亚和美国分系统；若多个分系统均匹配同一单号，Peek 将无法判定哪一票属于你。使用分提单号、主提单号或寄件人参考号时，Peek 会在“Mainfreight 记录的单号为”一栏显示 Mainfreight 返回的对应单号。

Peek 展示当前阶段、带地点的 Mainfreight 原文节点，以及系统提供的重量（自动换算为你常用单位），同时略去分支联系方式、邮编、寄件人参考号和单证文件。“打开 Mainfreight 官网”按钮可在 Mainfreight 官方查询页打开该单号。当 Mainfreight 官网无法访问或无法解析时，Peek 会转而向[通用物流追踪服务](guide:universal-tracker)（英文）查询并按对方原文展示。

标注了新西兰、澳大利亚或美国时区（如 `NZDT`、`AEST`、`PST`）的公路节点具有精确时间；其余未带时区的节点，Peek 会尽量按该节点或相邻节点的地点时钟解读，否则默认按新西兰时间解读（例如欧洲公路扫描和空海运里程碑，因此小时数可能存在数小时偏差）。

当你打开货件页面或在派送日开启通知时，只要 Mainfreight 官网正常响应，Peek 最高每 10 分钟检查一次，出库派送当天则自 Mainfreight 最新扫描所在时钟的早上 6 点起每 2 分钟检查一次。其余时间以及连续两天无新动态后（处于“已创建运单”或“快递公司已收件”状态时为 12 小时）改为每小时一次。通知支持“每次扫描”、“重要节点”或“仅在送达时”。Peek 无法代你联系 Mainfreight、预约派送或提起索赔。

## Mainfreight 物流查询常见问题

### Mainfreight 运单号是什么样的？

新西兰、澳大利亚和美国的公路货运使用 11 位数字托运单号。作业编号、分提单号和寄件人参考号通常混用字母和数字，可在 Peek 中手动选择 Mainfreight 查询。

### Mainfreight 物流中的“At Delivery Depot”是什么意思？

表示货物已到达负责最终派送的分拨站点，Peek 会显示“运输途中”。下一步通常是装车派送 `On Delivery Vehicle`。如果停留多日未动，请询问寄件方何时安排派送。

### “Pickup Allocated to Driver”是什么意思？

表示已指派司机前去提货，但货物仍在寄件方处尚未揽收，因此 Peek 显示“已创建运单”。下一步才是实际提货 `Picked Up`。

:::sources
- [Universal Parcel Scraper: Mainfreight](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/mainfreight) – names and countries, the 11-digit shape, other references and master bills, links, the tracker's search and services, wordings and their stages, planned steps, clocks, weight, expected day, what Peek leaves out
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – carriers sharing 11 digits, Mainfreight asked only from New Zealand, Australia and the United States, no hand-off, tracking services as a fallback
- [NZ Customs: Duty and GST](https://www.customs.govt.nz/sending-and-receiving/duty-and-gst) – NZ$1000, GST collected by overseas sellers, larger consignments, tobacco and alcohol, how GST is worked out, Goods Levy, Customs Number, release after payment
- [GovInfo: 49 U.S.C. 14706, Liability of carriers under receipts and bills of lading](https://www.govinfo.gov/content/pkg/USCODE-2024-title49/html/USCODE-2024-title49-subtitleIV-partB-chap147-sec14706.htm) – liability for actual loss or injury, limited value, 9 months to claim and 2 years to sue
- [GovInfo: 49 CFR Part 370, Investigation and voluntary disposition of loss and damage claims and processing salvage](https://www.govinfo.gov/content/pkg/CFR-2025-title49-vol5/xml/CFR-2025-title49-vol5-part370.xml) – what a claim contains, notes on delivery receipts, supporting documents, 30, 120 and 60 days
:::
