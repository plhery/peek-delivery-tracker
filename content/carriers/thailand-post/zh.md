---
title: 泰国邮政（Thailand Post）物流查询：TH 单号、EMS 状态与客服
description: 查询泰国邮政（Thailand Post）包裹：了解 13 位 TH S10 单号、国内与 EMS 状态码、货到付款、清关及 1545 客服热线。
slug: thailand-post-tracking
published: 2026-11-27
updated: 2026-11-27
---

**泰国邮政（Thailand Post**，全称 *Thailand Post Co., Ltd.*，泰文 *บริษัท ไปรษณีย์ไทย จำกัด*）是泰国国家邮政运营商（[thailandpost.co.th](https://www.thailandpost.co.th/index.php?page=index&language=en)）。它在泰国境内提供平邮、挂号包裹、EMS、当日达（Same Day）、大件 LogisPost 及货到付款（COD）服务，国际业务则涵盖 EMS World、挂号邮件（Registered）、ePacket、Courier Post（最高 200 公斤）及 Courier Post Plus。公开追踪页面位于 [track.thailandpost.co.th](https://track.thailandpost.co.th/)，国际业务公告见 [international.thailandpost.com](https://sites.google.com/thailandpost.com/interthp/update)。你可在那里查询，或直接把 13 位 `TH` 追踪单号粘贴到上方输入框。

## 泰国邮政追踪单号（13 位 `TH` S10 格式）

可追踪的泰国国内及国际邮政邮件采用万国邮政联盟（UPU）13 位 S10 编码：2 位大写服务字母、8 位序列数字、1 位 S10 校验数字，以及国家代码 `TH`：

| 格式（虚构示例） | 说明 |
| --- | --- |
| `EE482917367TH` | 13 位泰国邮政 EMS 特快邮件（`EE` 前缀 + 带有效 S10 校验位的 9 位数字 + `TH`）。 |
| `RR739184627TH` | 13 位泰国邮政挂号信或挂号小包（`RR` 前缀 + 带有效 S10 校验位的 9 位数字 + `TH`）。 |

国内特快/EMS、挂号件（`R...TH`）、ePacket（`L...TH`）以及各类国内包裹前缀（以 `E`、`R`、`C`、`W` 等开头且以 `TH` 结尾）均遵循相同的 13 位 S10 校验位结构。没有 13 位条码的普通平信无法在线追踪。详见 [追踪单号格式](guide:tracking-number-formats)（英文）。

## 泰国邮政物流状态含义解析

泰国邮政追踪器将符合 UPU 规范的数字 `statusID` 代码与英文说明（及泰语邮局名称）配对展示，同时涵盖泰国本地扫描及目的国邮政回传的国际 EDI 更新：

| 泰国邮政代码与原文 | 对你的含义 | Peek 显示 |
| --- | --- | --- |
| `3` `Posting/Collection` | 已在泰国邮政柜台收寄或从寄件人处揽收。 | “已揽收” |
| `9` `Arrival at outward office of exchange`、`15` `Departure From Outward OE`、`18` `Arrival at inward office of exchange` | 正在国际互换局办理出口或进口交换处理。 | “运输中” |
| `23`–`27` `In transit`、`Item into sorting center`、`Arrival at post office` | 正在各分拣中心间转运并抵达负责投递的当地邮局。 | “运输中” |
| `31` `Item out for physical delivery` | 已交由泰国邮政投递员今天外出上门派送。 | “派送中” |
| `57` `Contact recipient` | 投递员尝试致电收件人（非位移操作，保持当前状态）。 | 保持原状态 |
| `32` `Item arrival at collection point for pick-up (by recipient)` | 已存入泰国邮政快递柜或自提点等待领取。 | “待取件” |
| `34` `Scheduled for further delivery attempt today` / `Item held, addressee notified`（第 4 组） | 投递未成功（例如收件人不在，或需支付关税/货到付款费用）。 | “派送未成功” |
| `28` `Return to the origin post office` | 出现异常，邮件正退回始发邮局。 | “需要留意” |
| `35/L` `The sender received the returned item` | 退回的包裹已交还原始寄件人。 | “已退回寄件人” |
| `35/S` `Final delivery` | 已送达收件人（货到付款订单之后可能出现 `36` `Successfully transfer money to seller`，不影响实际送达时间）。 | “已签收” |

## 泰国境内货到付款、国际交接与清关

- **国际出境邮件（`...TH` 寄往海外）：** 出现 `15`（`Departure From Outward OE`）后，由目的国国家邮政——例如 [USPS](carrier:usps)、[英国皇家邮政（Royal Mail）](carrier:royal-mail)、[法国邮政（La Poste）](carrier:la-poste)、[DHL](carrier:dhl) / 德国邮政或 [澳大利亚邮政（Australia Post）](carrier:australia-post)——负责进口清关与本地派送，并通过 EDI 将扫描回传给泰国邮政。
- **关税与进口增值税：** 从泰国出口或进口至泰国的包裹须遵守目的地海关法规、进口增值税与清关费用（含欧盟对非欧盟订单的收费）。若轨迹显示 `34`（`Item held, addressee notified due to Payment of charges`），请查看信箱或联系当地邮局获取缴费/清关通知。详见 [包裹被海关扣留指南](guide:customs)（英文）。
- **泰国境内货到付款（COD）：** 国内 COD 订单在收件人签收付款后显示 `35/S`（`Final delivery`），货款划转给卖家后再记录 `36`（`Successfully transfer money to seller`）。

## 泰国邮政包裹延误、暂扣或丢失

- **离开泰国后的静默期：** 国际航空邮件（尤其是挂号件 `R...TH` 和经济包裹）在 `Departure From Outward OE` 与 `Arrival at inward office of exchange` 之间常暂停更新数天甚至数周。到目的国邮政的网站用同一 13 位 `TH` 单号查询，通常能看到更多清关或派送细节。参阅 [物流为何停止更新](guide:tracking-not-updating)（英文）。
- **丢失、破损或退回：** 请先联系寄件人或电商平台，并妥善保存 13 位单号及收据。详见 [包裹丢失](guide:lost-parcel)（英文）、[包裹破损](guide:damaged-parcel)（英文）、[显示已送达却未收到](guide:delivered-not-received)（英文） 和 [退回寄件人](guide:returned-to-sender)（英文）。

## 泰国邮政客服与 1545 联络中心

以下官方支持渠道公布于 [泰国邮政联系我们](https://www.thailandpost.co.th/un/contact_thp/un/41) 与 [thailandpost.co.th](https://www.thailandpost.co.th/index.php?page=index&language=en)：

- **THP 联络中心（泰国境内）：** **`1545`**（一般咨询、查单与投诉）。
- **官方联系与咨询门户：** [thailandpost.co.th/un/contact_thp/un/41](https://www.thailandpost.co.th/un/contact_thp/un/41)。
- **在线追踪与国际公告：** [track.thailandpost.co.th](https://track.thailandpost.co.th/) 及 [international.thailandpost.com](https://sites.google.com/thailandpost.com/interthp/update)。

> 请警惕要求点击陌生链接支付小额费用的虚假短信或 LINE 消息。任何追踪信息或关税费用请直接通过 [track.thailandpost.co.th](https://track.thailandpost.co.th/) 或 THP 联络中心 `1545` 核实。参阅 [防范快递诈骗短信](guide:delivery-scam-texts)（英文）。

## 使用 Peek 追踪泰国邮政包裹

输入任意校验位有效的 13 位 `TH` S10 单号（如虚构示例 `EE482917367TH` 或 `RR739184627TH`）时，[Peek](/) 会将泰国邮政自动识别为唯一候选，同时直接识别粘贴的 `track.thailandpost.co.th/?trackNumber=` 追踪链接。如需手动切换承运商（例如包裹抵达海外后对照目的国邮政自己的扫描），可在添加时点“更换”或在详情页点“更换快递公司”。

Peek 读取泰国邮政的追踪器（第一台主机失败时改用另一台），显示阶段（“已揽收”、“运输中”、“派送中”、“待取件”、“派送未成功”、“需要留意”、“已退回寄件人”、“已签收”）、英文描述、邮局名称与送达时间。数字状态码用于判定阶段，不会显示在 Peek 界面中；收件人姓名仅显示在你自己的详情页“收件人”栏下、绝不出现在分享链接中，且任何应用都不展示实际代签人姓名。泰国邮政自有扫描带曼谷时区；海外邮政 EDI 扫描保留当地墙钟但泰国邮政误存为曼谷时间，因此 Peek 会按邮局所在时区重新解析（如 `TOKYO INTERNATIONAL` 用东京时间；无地点则沿用相邻节点），再统一换算为你设备的本地时间。Peek 不展示投递员姓名、电话号码、签名或脱敏联系备注；泰国邮政不提供预计送达日。

当你在 Peek 中打开包裹页面，或在派送当日开启通知时，Peek 最多每 10 分钟检查一次；在泰国邮政显示 `31`（`Item out for physical delivery`）当天，只要泰国邮政的追踪器有响应，会从最新扫描所在时钟的早上 6 点起每 2 分钟检查一次。其余情况下每小时检查一次。若包裹停留在“已生成面单”或“已揽收”超过 12 小时，或连续两天没有新动态，无论你以何种方式关注，均改为每小时检查一次；若停留在“已生成面单”超过七天，则每 6 小时检查一次。当泰国邮政的追踪器无响应、Peek 仅通过追踪服务跟进时，每小时检查一次，静默一天后每 3 小时一次，派送当天最多每 15 分钟一次。

当最新扫描距今已满四天，且包裹仍处于“已生成面单”、“已揽收”或“运输中”时，Peek 会用该次扫描的日期加以标记，例如“自10月12日周一起没有物流更新”。包裹页面随后会显示“包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 Thailand Post 查询。”，并附上指向泰国邮政联系页面的“向 Thailand Post 寻求帮助”链接。如果三周没有新扫描，页面会显示“已超过三周没有新动态。如果还没收到，请联系卖家。Peek 会持续向 Thailand Post 查询。”

你可选择“每次扫描”、“重要环节”或“仅派送当天”通知。Peek 仅监控轨迹更新，无法代办清关、支付货到付款费用或替你联系泰国邮政。

## 关于泰国邮政物流查询的问题

### 泰国邮政（Thailand Post）追踪单号长什么样？

可追踪的泰国邮政邮件采用 13 位 UPU S10 编码：2 位服务字母 + 9 位数字（末位为校验位）+ `TH`，例如 EMS 的 `EE482917367TH` 或挂号件的 `RR739184627TH`（均为虚构示例）。

### 状态码 `36`（`Successfully transfer money to seller`）是什么意思？

在泰国国内货到付款（COD）订单中，代码 `36` 确认泰国邮政已在最终妥投（`35/S`）后将代收的货款转给卖家，属于资金结算事件而非包裹位移。

### 为什么泰国邮政包裹在 `Departure From Outward OE` 之后不再更新？

说明包裹已离开泰国国际出口互换局，正在国际空运途中，或正在等待目的国邮政接收并录入进口扫描。

### 如何联系泰国邮政客服？

泰国境内可拨打 THP 联络中心 **`1545`**，或访问官方联系页面 [thailandpost.co.th/un/contact_thp/un/41](https://www.thailandpost.co.th/un/contact_thp/un/41)。

:::sources
- [Thailand Post: Official Website](https://www.thailandpost.co.th/index.php?page=index&language=en) – *Thailand Post Co., Ltd.*、国内与国际 EMS、挂号、Courier Post、LogisPost 与 COD 服务
- [Thailand Post: Contact Us & THP Contact Center 1545](https://www.thailandpost.co.th/un/contact_thp/un/41) – 官方客服支持、投诉入口与 `1545` 热线（Peek 帮助链接）
- [Thailand Post: Public Tracker](https://track.thailandpost.co.th/) – 官方在线追踪查询
- [Thailand Post: International Service Advisories](https://sites.google.com/thailandpost.com/interthp/update) – 国际追踪状态与服务公告
- [Universal Parcel Scraper: thailand-post](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/thailand-post) – `TH` S10 校验位验证、双主机故障切换、UPU `statusID` 映射（`3`–`36`、`57`）、曼谷与海外 EDI 时区处理及签收人提取
:::
