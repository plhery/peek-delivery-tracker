---
title: TForce Freight 物流查询：PRO 单号、状态与货运理赔
description: 查询美国与加拿大 TForce Freight 零担货运：9 位 PRO 单号与 PARS 编号格式、物流状态含义、住宅送货规则、货损索赔与客服电话。
slug: tforce-freight-tracking
published: 2026-11-26
updated: 2026-11-26
---

TForce Freight 是一家零担货运（LTL，less-than-truckload）承运商，在美国全境、加拿大全境的各服务中心之间以及墨西哥超过 600 个目的地承运托盘货与重型大件货物。如果有大件网购订单或其他货运经由 TForce 运来，请使用网店或订舱企业提供的 PRO 单号追踪：直接将单号粘贴到上方输入框，或前往 www.tforcefreight.com 的 Tracking 页面查询。

## TForce Freight 运单号：PRO 单号

TForce 官方查询页支持按 PRO 单号查询，也支持按提单号（BOL）或采购订单号（PO）配合发货日期范围查询；Peek 按 PRO 单号追踪。以下数字均为虚构：

| 单号（虚构） | 含义 |
| --- | --- |
| `482917363` | PRO 单号：9 位数字，末位为校验位（前 8 位除以 7 的余数，因此必定是 0 至 6）。 |
| `48291736` | 省略前导 `0` 的 PRO 单号。TForce 查询页会补上 `0`；在 Peek 中选择 TForce Freight 后，Peek 也会补上。 |
| `2205482917363` | 发往加拿大时供报关行使用的 PARS 编号：`2205` 后接 PRO 单号。整串粘贴即可，Peek 会提取其中的 PRO 向 TForce 查询。 |

以 `7`、`8` 或 `9` 结尾的 9 位数字 PRO 单号必然存在输入错误。Peek 目录中也将 TForce Freight 识别为 UPS Freight 和 TForce。

**在哪里查找。** 在网店发货邮件或订单页面中查找；企业间货运请直接联系发货方。另见[如何查找运单号](guide:find-tracking-number)（英文）。

**同形态的其他承运商。** 带同样校验规则的 9 位数字同时也符合 [TNT](carrier:tnt) 和 XPO。单号以 `TF` 开头的 TForce Logistics（TForce Final Mile）在 Peek 中属于另一家独立承运商。若邮件写明由它们承运，请选择对应公司。详见[运单号格式说明](guide:tracking-number-formats)（英文）。

## TForce Freight 物流状态含义

TForce 查询页在 PRO 单号旁显示总体状态（如 `In Transit` 或 `Delivered`），下方按最新置顶列出历史轨迹，每条包含服务中心所在城镇与州/省、日期和时间。由于记录不带状态码，Peek 按文字内容解析阶段。以下按货运经过顺序列出：

| TForce Freight 显示 | 对你的含义 | Peek 显示 |
| --- | --- | --- |
| `SHIPMENT HAS BEEN PICKED-UP.` | 司机已从发货方处揽收货物。 | “已揽收” |
| `SHIPMENT IS AT A TFORCE FREIGHT LOCATION` | 货物位于发货地一侧的 TForce Freight 服务中心。 | “运输中” |
| `DEPARTURE` | 已离开某一服务中心。 | “运输中” |
| `SHIPMENT HAS ARRIVED AT A SERVICE CENTER.` | 已抵达中转服务中心，或已到达负责为你派送的服务中心。 | “运输中” |
| `SENT EMAIL. TFORCE FREIGHT IS ATTEMPTING TO OBTAIN APPOINTMENT INFORMATION` | TForce 已向收货人（consignee）发送邮件预约送货时间，请及时回复。 | “运输中” |
| `A DELIVERY APPOINTMENT HAS BEEN SCHEDULED WITH THE CONSIGNEE.` | 已约妥送货日期（该行有时在送达后才补录）。 | “运输中” |
| `OUT FOR DELIVERY` | 已装上送货卡车外出派送。 | “派送中” |
| `Shipment not delivered. Delivery attempted after normal business hours. Tforce will attempt delivery on next business day we service this area.` | 本趟未能完成派送。所有以 `Shipment not delivered.` 开头的记录均按此处理，后附具体原因。 | “派送未成功” |
| `SHIPMENT HAS BEEN DELIVERED TO THE CONSIGNEE.` | 已交付签收；TForce 页面会列出签收人姓名。 | “已签收” |

只要 PRO 旁显示 `Delivered`，Peek 必定归为“已签收”；其余情况由 Peek 能理解的最新一行决定，无法识别的行按 TForce 原文展示。TForce 查询页也支持邮件订阅更新。详见[物流状态含义指南](guide:tracking-statuses)（英文）。

## TForce Freight 派送方式

TForce 将货物运至服务你所在区域的服务中心，再安排卡车送货。其查询页提供预计送达日，Peek 会在签收前或该日期过期前保留显示。Peek 不会将 TForce 货件转交其他承运商跟进。

TForce 的《零担运价规则》（LTL Rules Tariff UPGF 102-T，自 2026 年 8 月 17 日起生效）明确了派送细节：

- **住宅送货。** 在卡车合法可通行的住宅区域，TForce 须先与你或发货人约好日期与大致时间（居家经营的企业也按住宅计）。货物送至一楼入口、车库入口或车棚，或者你也可去其货运站自提。住宅送货属于额外收费项目，由下单方或运费支付方承担。
- **车边卸货。** 标准送货仅卸至卡车旁；入户搬运（inside delivery）与尾板卸货（lift gate）均需额外付费，请向订舱方确认是否已包含。
- **到货通知。** 若运单要求到货通知，TForce 会在货物到达末端服务中心后的次一个工作日内通知收货人，通常通过电话或邮件。
- **未能派送。** 若被拒收或联系不到收货人，TForce 会通知发货人货物已入库暂存，可能产生仓储费。除非你到服务中心自提，否则重新派送会收费（住宅地址仅在错过已预约日期时收费）。

**发往加拿大。** 据 TForce 加拿大页面说明，美国发往加拿大的货物在边境经由报关行清关。若未能在边境放行，货物将暂存于目的地保税仓并产生仓储费；报关行代理费、关税及税费由登记进口商（importer of record）支付。

## TForce Freight 查不到、延误、破损或遗失

**查不到记录。** 对未知的 PRO 单号，TForce 查询页显示 `Not Available` 和 `Please check ProNumber` 加单号。归属 TForce Freight 时 Peek 会显示“等待 TForce Freight 更新”（否则显示“暂无物流记录”）并稍后重试。请逐位核对单号，并询问发货方是否已将货交给 TForce。

**数字输错。** 末位通不过除 7 取余校验的 PRO 单号不可能属于 TForce，Peek 绝不会向 TForce 查询。若手动强选 TForce Freight，Peek 会提示其“不使用这种格式的单号”，页面并显示“请检查运单号”。

**不更新。** 处于“已揽收”或“运输中”且连续 4 天无新记录时，Peek 会以最后一条扫描的日期标出提示（如“自10月12日周一起没有物流更新”），附带“包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 TForce Freight 查询。”以及“向 TForce Freight 寻求帮助”链接。请联系网店或发货方；详见[物流停止更新的原因](guide:tracking-not-updating)（英文）。

**派送未成功。** 出现 `Shipment not delivered.` 后，Peek 会提示“查看快递公司的后续安排”。请致电 TForce 或联系订舱方重新安排送货。另见[派送未成功怎么办](guide:missed-delivery)（英文）。

**收货时少件或破损。** TForce 货运理赔指南强调每次收货都要当场检查并立即告知司机：

- 对照送货回单清点件数，并在司机在场时拆开破损外箱；
- 在你的留存联与 TForce 联上准确写明缺失或破损细节；
- 除非破损导致货物完全报废，否则不要直接拒收；
- 请当地服务中心的 OS&D（Over, Short & Damage）专员追查缺失件数。

**收货后才发现破损。** 须在签收后 **5 个工作日**内报告并申请查验，同时保留原包装不变（查验报告本身不等同于正式理赔）。

**提交理赔。** 理赔是货主提出的书面赔付要求；网购订单请先找持有运单的网店。TForce 理赔规则如下：

- 须在签收后（或未到货时的发货日起）**9 个月**内提交；
- 使用 TForce 标准理赔表（Standard Claim Form）或书面信函列明货件与金额，附供应商发票、运费单、提单（BOL）及维修发票（如适用），邮寄或传真提交（见下文，勿重复提交）；
- TForce 在 **30 天**内确认受理。除非发货人在提单上购买额外保价，否则承运赔偿上限为每件每磅 $25.00（部分品类更低）。

若显示已签收却未收到，请看[显示已签收却未收到](guide:delivered-not-received)（英文）。另见[包裹破损处理](guide:damaged-parcel)（英文）和[包裹遗失处理](guide:lost-parcel)（英文）。

## TForce Freight 客服与联系方式

TForce 官网公布的联系渠道如下：

- **客服热线：** `(800) 333-7400`（美国、加拿大、关岛、波多黎各和美属维尔京群岛免费），受理查件、签收证明及异常处理；其他地区拨打 `(804) 231-8555`。
- **邮件咨询：** 通过 TForce Contact Us 页面进入 “Email TForce Freight Customer Support” 表单，内设 PRO 单号栏位。
- **理赔部门：** `(888) 653-9720`（查询理赔进度）。理赔材料邮寄至 `Claims Processing, TForce Freight, PO Box 1216, Richmond, VA 23218`，或传真至 `(866) 580-1944`。
- **当地服务中心：** 可在 www.tforcefreight.com 的 Service Information → Service Center Directory 查询。

由于网店或发货企业持有运输订单，直接联系它们往往推进更快。

本文引用的 TForce 官方页面均位于 www.tforcefreight.com：Tracking、Fact Sheet、Rules Tariffs（含 LTL Rules Tariff UPGF 102-T）、Canada、Cargo Claims Information、Contact Us（含 Email TForce Freight Customer Support）及 Service Information。

## 使用 Peek 跟踪 TForce Freight 货件

仅凭 9 位数字无法断定属于 TForce Freight（同样符合 TNT 和 XPO）。直接粘贴即可：Peek 可以跟踪超过 4,500 家快递公司。在显示“正在查询物流服务…”期间，Peek 会同时询问这三家（若都查不到再询问 DPD Ireland 及物流查询服务）；若仅 TForce 有记录则显示“TForce Freight 有这个包裹的记录。”，多家均有记录时由你选择。对于 PARS 编号，Peek 会同时向 TForce 与 FAN Courier 查询；对于 8 位 PRO 单号，Peek 只询问物流查询服务。你也可以点击“更换”在“符合这个单号”下选择 TForce Freight，或在添加后使用“更换快递公司”。粘贴 TForce 查询链接（含 `proNumbers=`）或 UPS Freight 链接（含 `TrackProNumber=`）则会立刻识别为 TForce Freight（“识别出的快递公司”）。

Peek 仅凭 PRO 单号查询 TForce 的查件系统，展示状态、每条记录的城镇与州/省、预计送达日、地图上的目的地国家、按你常用单位换算的重量，以及仅对你本人可见的收货人城镇和“收件人”姓名；发货人、预约详情、BOL 与 PO 编号及件数会被过滤，也不获取签收证明。点击“打开 TForce Freight 官网”会在 TForce 的查件页面打开你的单号（PARS 编号则打开其中的 PRO）。如果该查件系统无法访问或要求验证码，Peek 会改向[物流查询服务](guide:universal-tracker)（英文）获取轨迹。

TForce 每条记录给的是当地服务中心时间但不带时区：Peek 按城镇所在时区解析（否则按州/省主时区、相邻记录、目的地，最后默认美东时间），并统一换算为你的设备本地时间。

当你正在 Peek 中查看货件，或派送当天已开启通知时，Peek 最多每 10 分钟直接查询一次 TForce；在 TForce 扫描货件开始派送的当天，自最新扫描所在时区（默认美东时间）的早上 6 点起最多每 2 分钟查询一次。连续两天无新动态（或在“已生成面单”/“已揽收”停留满 12 小时）后改为每小时一次。Peek 可以按“每次扫描”、“重要环节”或“仅派送当天”通知你。Peek 无法代你联系 TForce、商家或发货人，也不能代为发起理赔。

## 关于 TForce Freight 物流查询的问题

### 什么是 TForce Freight PRO 单号？

它是 TForce 的 9 位数字运单号，末位是 0 到 6 之间的校验位，由网店或订舱发货企业提供给你。

### 没有 PRO 单号能查 TForce Freight 吗？

在 TForce 官网查询页可以，支持用提单号（BOL）或采购订单号（PO）加发货日期范围查询；Peek 只按 PRO 单号跟踪。

### TForce Freight 提供住宅送货吗？

在卡车合法可通行的区域可以，但会收取住宅派送附加费。TForce 会先与你或发货人约定日期和大致时间，然后送到一楼入口、车库入口或车棚；入户搬运和尾板卸货另计费。

### 向 TForce Freight 提交理赔的期限是多久？

自签收之日起（未到货则自发货之日起）9 个月内；签收后发现的破损须在 5 个工作日内申报。

### TForce Freight 的客服电话是多少？

美国与加拿大免费热线为 `(800) 333-7400`，其他地区请拨打 `(804) 231-8555`。

:::sources
- [Universal Parcel Scraper: TForce Freight](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/tforce-freight) – 名称、PRO 单号格式、链接、状态措辞与阶段、查无记录时的回应、Peek 保留的内容、时区
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – 同格式的承运商、不转交其他承运商、以物流查询服务作后备
:::
