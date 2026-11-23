---
title: 华翰物流（HuaHan）单号查询：HHW…YQ状态与尾程
description: 查询深圳华翰物流（HuaHan Logistics）包裹：HHW…YQ单号格式、中英文与邮政及Landmark轨迹含义、各国尾程衔接与热线。
slug: huahan-wuliu-chaxun
published: 2026-11-23
updated: 2026-11-23
---

华翰物流（HuaHan Logistics，又称华翰速递或 HuaHan Express）是一家总部位于深圳的中国跨境物流承运商，负责把跨境电商包裹空运出境并移交给目的国本地快递完成最后一公里派送。如果网店使用华翰发货，单号以 `HHW` 开头、以 `YQ` 结尾——把它粘贴到上方查询框即可追踪。华翰自有查件入口位于 `hh-exp.cn`，查件结果中可能会一并给出当地尾程快递商的名称和尾程单号。

## 华翰物流单号格式：怎么看、怎么查

Peek 识别的华翰物流标准单号为 `HHW` + 2 位字母 + 10 位数字 + `YQ`，总共 17 位字符。以下示例数字均为虚构：

| 单号（虚构） | 格式说明 |
| --- | --- |
| `HHWCN4829105736YQ` | `HHW` + 2 位字母 + 10 位数字 + `YQ`：标准华翰物流运单号。 |
| `HHWCN482910573YQ` | 中间只有 9 位数字：不符合华翰格式，请检查是否漏输了一位数字。 |
| `WNBKP4829105736YQ` | 同样以 `YQ` 结尾，但开头是 `WNB`：这是 [万邦速达（WanbExpress）](carrier:wanbexpress) 的单号。 |
| `JXCKP4829105736YQ` | 开头是 `JXC`：这是驹隙物流（Juxi Express）的单号。 |

虽然很多中国跨境专线都以 `YQ` 结尾，但 `HHW` 前缀为华翰物流专属，Peek 目录中没有其他快递使用这一组合。粘贴到 Peek 时，空格、连字符和字母大小写都不影响识别。单号由卖家在发货邮件或订单页提供；找不到请参阅 [如何找到你的快递单号](guide:find-tracking-number) 和 [快递单号格式指南](guide:tracking-number-formats)。

**尾程转单号与下划线后缀。** 华翰查件结果可能同时列出目的国尾程快递的单号；注意华翰有时会在尾程单号后面附加一个下划线及后缀，去当地快递官网单独查询时需把下划线及其后的后缀去掉。

## 华翰物流状态是什么意思

华翰查件页按时间倒序列出每条扫描的时间、文本和地点；自有扫描用英文，部分附带中文。按包裹常规流转顺序：

| 华翰原文 | 对你的实际含义 | Peek 显示阶段 |
| --- | --- | --- |
| `快件电子信息已经收到 Shipment information received`、`Shipment information received` | 卖家已发送电子订单信息，华翰尚未拿到实物包裹。 | 「已生成面单」 |
| `快件到达作业中心 Shipment arrived at facility`、`Has entered the warehouse` | 华翰在中国的作业中心已实收包裹。 | 「已揽收」 |
| `快件操作完成 Shipment departed from facility` | 已完成分拣操作并离开华翰作业中心。 | 「运输中」 |
| `Transfer to Origin airport` | 正在发往起运机场。 | 「运输中」 |
| `Export customs clearance completed` | 中国出口报关已完成（不是收件国进口海关）。 | 「运输中」 |
| `Departed from airport of Origin` | 国际航班已从起运机场起飞。 | 「运输中」 |
| `Import Customs clearance started` | 已进入目的国进口海关清关。 | 「清关中」 |
| `Arrived at destination airport` | 航班已降落在目的国机场。 | 「运输中」 |
| `Import Customs clearance completed` | 目的国进口海关已放行。 | 「运输中」 |
| `Has been given to local carrier` | 华翰已将包裹移交给目的国本地快递商，随后出现当地快递扫描。 | 「运输中」 |

**移交后同步转发的当地快递轨迹。** 华翰会按自有状态码或原文转发尾程快递的进度：

| 移交尾程后的常见原文 | 对你的实际含义 | Peek 显示阶段 |
| --- | --- | --- |
| `Ready to Ship, Parcel received and processed` | 当地尾程快递已收货并完成处理。 | 「运输中」 |
| `The parcel is in the central warehouse waiting to be processed.`、`In Transit, Shipment arrived to sorting centre / Shipment in the sorting centre`、`In Transit, Shipment remained at sorting hub` | 包裹在当地快递的中心仓或分拣枢纽等待处理。 | 「运输中」 |
| `In Transit, Arrived to HU HUB`、`In Transit, Parcel has entered the Linehaul network.` | 到达匈牙利（HU）枢纽或在当地快递的干线网络内中转。 | 「运输中」 |
| `In Transit, Picked Up`（后接 DPD 德语 `Einrollung`）、`In Transit, Scanned By DPD At parcel delivery centre.`、`In Transit, At parcel delivery centre.` | 到达 DPD 派送中心（到站入库及滞留日扫，并非收件人自提）。 | 「运输中」 |
| `Out for Delivery, Parcel is at courier, delivery expected soon.` | 当地快递员已装车外出派送。 | 「派送中」 |
| `The courier will deliver the parcel soon. Additional details are available in the SAMEDAY app, email or SMS.` | Sameday 快递员正在派送途中。 | 「派送中」 |
| `Delivery Attempted`、`Delivery Attempted, Unfortunately we have not been able to deliver your parcel. Pickup parcelshop delivery after first delivery attempt.` | DPD 派送尝试未成功；长句表示首次未送达后将转送附近代收点。 | 「派送未成功」 |
| `Awaiting Pickup, Parcel was deposited in Pickup parcel station` | 派送未成功后 DPD 已将包裹存入自提点等待领取。 | 「待取件」 |
| `The parcel has been successfully delivered.` | 已妥投签收。 | 「已签收」 |

注意华翰有时会把 USPS 离开分拨中心的记录也错归入其「派送中」代码下，Peek 会结合具体英文措辞予以修正。

**容易误解的通知行、数据行与重复行。** `We will pick up your parcel from the sender soon.` 可能在华翰中国收货前就出现，只是尾程快递收到预报的提示（「已生成面单」）；`The parcel was redirected. As soon as it is in the couriers possession, it will be processed as requested.` 是收件人提前设置改址的备注，不改变阶段；`The instruction data for this shipment have been provided by the sender to DHL electronically.`（DHL 电子预报）与中文 `货物电子信息已经收到` 都只是数据行，不代表实物移动。另外，华翰有时会在同一分钟把尾程扫描重复写两条（一条带具体地点，一条为通用文本如 `Delivered, Parcel was delivered successfully`），Peek 会保留带地点的那一条。

**转交中国邮政发运的线路。** 华翰部分渠道会交给 [中国邮政](carrier:china-post) 承运，并原样转发带【】邮局名的中文扫描：

| 华翰转发的邮政中文原文 | 对你的实际含义 | Peek 显示阶段 |
| --- | --- | --- |
| `中国邮政已收取邮件` | 华翰已把包裹交给中国邮政，改走邮政网络。 | 「运输中」 |
| `邮件已在【…】完成分拣，准备发出`、`邮件到达【…】`、`邮件离开【…】，正在发往【…】`、`邮件离开【…】，正在发往下一站` | 在国内各邮政网点分拣、到达与发运。 | 「运输中」 |
| `送交出口海关` | 提交中国出口海关（并非目的国海关）。 | 「清关中」 |
| `出口海关/放行` | 中国出口海关已放行。 | 「运输中」 |
| `已交承运商运输` | 已交付国际航空承运商。 | 「运输中」 |
| `到达寄达地`、`到达寄达地处理中心`、`到达境外经转局` | 到达目的国、目的国邮政中心或境外中转局。 | 「运输中」 |
| `境外进口海关留存待验(等待提交给边境机构/安全部门)`、`境外进口互换局留存`、`送交境外进口海关` | 到达目的国互换局并送交进口海关查验。 | 「清关中」 |
| `寄达地海关允许进口（无需缴税）` | 进口海关免税放行。 | 「运输中」 |
| `离开【…】处理中心`、`已到达【…】投递局` | 离开目的国处理中心并到达当地投递邮局。 | 「运输中」 |

**经由 Landmark Global 与 bpost 发运的线路。** 部分欧洲件通过 Landmark Global 转交比利时邮政 bpost 派送，轨迹为英文：`Shipment Data Uploaded`（「已生成面单」）、同分钟生成的 `Processed`（只是一条处理记录，不改变阶段）、`Incoming scan at facility` 与 `Outgoing scan at facility`（尽管华翰把后者归在派送代码下，但实为离站转运，Peek 正确显示「运输中」）、`Out for delivery (On the way)`（「派送中」）以及 `Delivery attempt (Delivery not possible)`（「派送未成功」）。

## 华翰物流包裹在目的国由谁派送

出现 `Has been given to local carrier` 之后即由当地快递派送。历史运单中出现过多种合作尾程：美国见 USPS（华翰有时传回带前缀的长条码，Peek 会自动截取有效 USPS 单号）；欧洲有 Sameday、DHL、DPD、西班牙和葡萄牙的 CTT Express（华翰在中国入库后几分钟就可能出现 `Pendiente de recepción en CTT Express` 预报，按马德里时间记录）以及 Landmark Global + bpost。实际派送商以运单结果为准。

派送次数、通知卡和自提保管期限全部遵循当地尾程快递规定，请凭尾程单号直接联系当地快递。参见 [错过派送怎么办](guide:missed-delivery) 和 [未领取包裹退件说明](guide:uncollected-parcel)。

**欧盟进口关税与处理费。** 根据 [欧盟委员会海关改革说明](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en)，自 2026 年 7 月 1 日起，欧盟境外网购且价值不超过 150 欧元的包裹按每类商品征收 3 欧元关税；自 2026 年 11 月 1 日起，无论价值多少均须按每类商品另缴 2 欧元欧盟统一处理费。由于无法确认华翰是否已向卖家预收，请向网店核实订单价格是否包税。详见 [包裹海关扣留与清关指南](guide:customs)。

## 华翰物流包裹不更新、查不到或丢失怎么办

- **提示「没有找到记录」。** 说明华翰系统尚未收录该单号，Peek 会显示「等待 HuaHan Logistics 更新」并继续查询。若两天后仍查不到，Peek 会提示「两天过去了，快递公司仍查不到这个单号。请检查单号、更换快递公司，或删除这个包裹。」，请核对 17 位 `HHW…YQ` 单号。
- **一直停在 `Shipment information received`。** 卖家已下单但华翰还未扫入实件，请询问卖家交货日期。
- **卡在 `Import Customs clearance started`。** 包裹正在目的国海关清关，收到缴税通知见 [包裹海关扣留与清关指南](guide:customs)，长期滞留请通知卖家。
- **停在 `Has been given to local carrier` 后没更新。** 包裹已交给当地快递，请查看华翰追踪页或 Peek 卡片里提取出的尾程单号去当地快递查件，或向卖家索取尾程信息。更多见 [物流为什么停止更新](guide:tracking-not-updating) 和 [中国直邮包裹跟踪指南](guide:tracking-from-china)。
- **显示已签收却没收到，或丢失/退件。** 先检查投放位置并询问邻居，随后依次联系当地尾程快递与卖家。发货合同由卖家与华翰签署，退款或重发请找卖家或购物平台。见 [显示已签收但未收到](guide:delivered-not-received)、[包裹丢失怎么办](guide:lost-parcel) 和 [包裹退回寄件人](guide:returned-to-sender)。

## 华翰物流客服电话与联系方式

[华翰官网联系页](https://www.hh-exp.com/lxwm) 公布深圳总部地址与官方热线 `400-015-6080`（中国国内 400 号码，未标注服务时间，未公布邮箱）。个人买家请优先联系下单卖家或购物平台处理订单售后，涉及末端派送请直接联系当地尾程快递商。

## 用 Peek 跟踪华翰物流包裹

[Peek](/) 会通过 `HHW` + 2 位字母 + 10 位数字 + `YQ` 的唯一规则立刻识别华翰物流（如虚构单号 `HHWCN4829105736YQ`），添加时显示「识别出的快递公司」，查询无需账号、邮编或手机号；如果手里只有当地尾程单号，直接粘贴尾程单号即可（Peek 支持 4,500 多家快递），也可点「更换」或「更换快递公司」手动调整。

Peek 直接读取华翰官方追踪并展示规范化阶段（「已生成面单」「已揽收」「运输中」「清关中」「派送中」「派送未成功」「待取件」「已签收」等）、每条扫描的地点及目的国。华翰页面不带时区，Peek 会自动为中国扫描套用北京时间，为 `Has been given to local carrier` 之后的单时区国家扫描套用当地时区，最后全部换算为你设备的本地时间。华翰不提供预计送达日期或重量。

只要华翰记录里给出 Peek 认识的尾程快递商及匹配单号（如 USPS），或给出有效国际邮政 S10 单号，Peek 都会同步展示尾程单号并同时查询该尾程商；一旦尾程商出现不早于华翰最新扫描的进度，Peek 便自动接力切换并保留华翰前期历史。若华翰官网暂时连不上，Peek 会回退至第三方查询服务。

只要你打开着这个包裹，或在派送当天开启了通知，Peek 最快每 10 分钟向华翰官方追踪查询一次；同样条件下，包裹「派送中」当天从华翰所用时钟的早上 6 点起每 2 分钟一次（需要打开浏览器查询时每 5 分钟一次）；其他时候每小时一次。连续两天没有新扫描（包裹仍显示「已生成面单」或「已揽收」时为 12 小时）后，也降为每小时一次。只有第三方追踪服务有结果时，Peek 每小时查询一次，安静一天后每 3 小时一次，派送当天在你打开包裹或开启通知时最快每 15 分钟一次。包裹交给尾程快递后，Peek 按该快递的频率查询。

包裹处于「已生成面单」「已揽收」或「运输中」且 4 天没有新扫描时，Peek 会显示「自10月12日周一起没有物流更新」这样的提醒（日期为最后一条扫描的日期），并附上说明「包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 HuaHan Logistics 查询。」；超过三周后，说明改为「已超过三周没有新动态。如果还没收到，请联系卖家。Peek 会持续向 HuaHan Logistics 查询。」。

通知偏好可选「每次扫描」「重要环节」（「清关中」「派送中」「派送未成功」「待取件」「已签收」「已退回寄件人」和「需要留意」；华翰起运前的 `Export customs clearance completed` 属「运输中」，不会通知）或「仅派送当天」。Peek 无法替你联系华翰或卖家。

## 关于华翰物流查询的常见问题

### 华翰物流（HuaHan Logistics）是什么快递公司？

一家总部位于中国深圳的跨境物流承运商（也称华翰速递），从深圳作业中心收货并空运出境，清关后交给目的国当地快递商派送。

### 在哪里可以查询华翰物流包裹？

可以直接粘贴完整 17 位 `HHW…YQ` 单号到页面上方的 Peek 查询框，或访问华翰官网追踪页 `hh-exp.cn`（查件前需要先通过验证：按顺序点选，或回答简单算术题）。

### `Has been given to local carrier` 是什么意思？

说明包裹已通过目的国进口清关，华翰已经把包裹移交给收件国当地尾程快递公司，接下来会陆续出现当地快递的派送扫描。

### 华翰物流包裹在收件国由谁派送？

取决于目的国与渠道，历史记录中出现过 USPS、DHL、DPD、Sameday、CTT Express、中国邮政以及 Landmark Global（末端 bpost）。具体请看华翰追踪页或 Peek 提取出的尾程信息，也可询问卖家。

### 华翰物流包裹丢了该找谁？

找下单的卖家或购物平台处理退款或重发；华翰热线 `400-015-6080` 是中国国内 400 号码。

:::sources
- [华翰物流：联系我们](https://www.hh-exp.com/lxwm) – 深圳总部地址与官方热线 400-015-6080
- [European Commission：欧盟海关改革（英文）](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 自 2026 年 7 月 1 日起 150 欧元以内每类商品 3 欧元关税、自 2026 年 11 月 1 日起 2 欧元欧盟统一处理费
- [欧盟委员会：Guidance on the €3 customs duty and the Union handling fee（PDF，英文）](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 欧元处理费与 3 欧元关税一样按每类商品收取，无论价值多少
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – HHW…YQ 单号规则、算术验证码查询后台、中英文/DPD/Sameday/CTT Express/中国邮政/Landmark Global+bpost 状态映射、下划线后缀清理与 USPS 条码提取、时区换算及自动接力尾程机制
:::
