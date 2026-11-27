---
title: A. Duie Pyle 物流查询：PRO 单号、零担货运状态与客服
description: 查询美国东北部 A. Duie Pyle 零担货运（LTL）：了解 8 位与 9 位 PRO 单号、分拨站扫描、预计时段、合作交接及 800 热线。
slug: a-duie-pyle-tracking
published: 2026-11-27
updated: 2026-11-27
---

**A. Duie Pyle**（*A. Duie Pyle, Inc.*）是一家第四代家族经营的零担货运（LTL）与综合供应链承运商，由 Alexander Duie Pyle 于 1924 年创立，总部位于宾夕法尼亚州西切斯特（West Chester）（[aduiepyle.com](https://aduiepyle.com/)）。依托分布于宾夕法尼亚、新泽西、纽约、马里兰、缅因、佛蒙特、新罕布什尔、西弗吉尼亚、俄亥俄及周边市场的 36 座越库零担服务中心，A. Duie Pyle 提供美国东北部次日达零担货运，以及专车合约、仓储、整车货运、港口物流与防冻温控运输。你可以在 [aduiepyle.com/resources/shipment-status/](https://aduiepyle.com/resources/shipment-status/) 查询运单，或直接将 PRO 单号或追踪链接粘贴到上方输入框。

## A. Duie Pyle 追踪单号（8 位与 9 位 PRO 单号）

A. Duie Pyle 通过 **PRO 单号**（印于提单 BOL 与货运标签上的 8 位或 9 位纯数字编号）追踪零担货运：

| 格式（虚构示例） | 说明 |
| --- | --- |
| `748291736` | 标准 9 位 A. Duie Pyle PRO 单号。 |
| `48291736` | 8 位 A. Duie Pyle PRO 单号。 |

输入 PRO 数字时无需连字符或空格。由于纯 8 位与 9 位数字与多家其他货运和包裹承运商重叠，直接粘贴 `aduiepyle.com/resources/shipment-status/?tracking=` 链接或手动选择 **A. Duie Pyle** 可确保 Peek 立即向 Pyle 查询。详见 [追踪单号格式](guide:tracking-number-formats)（英文）。

## A. Duie Pyle 物流状态含义解析

A. Duie Pyle 货运状态历史展示简明的英文分拨站与派送节点（不带数字事件码），同时列出预计送达（ETA）或预约派送时段：

| A. Duie Pyle 原文 | 对你的含义 | Peek 显示 |
| --- | --- | --- |
| `PICKED UP` | 已从寄件方月台揽收货物并扫描入 A. Duie Pyle 网络。 | “已揽收” |
| `DEPARTED TERMINAL`、`ARRIVED AT TERMINAL` | 正在各越库服务中心（如 West Chester、Carteret、Camp Hill、Albany、Buffalo、Baltimore 或 Cleveland）之间中转。 | “运输中” |
| `OUT FOR DELIVERY` | 今天已从目的服务中心装上当地派送卡车外出派送。 | “派送中” |
| `DELIVERED` | 已送达收货人地址并完成签收。 | “已签收” |

注意 A. Duie Pyle 历史记录中还会出现非位移条目（`PICKUP WINDOW`、`ETA WINDOW`、`DELIVERY APPOINTMENT WINDOW`）：Peek 会将它们从扫描列表中排除，并优先读取 `ETA WINDOW`（无则取 `DELIVERY APPOINTMENT WINDOW`）作为你的预计送达时间。送达后的开票记录 `INVOICED` 不会触发状态变化。

## 美东次日达时效、预约派送与联运合作伙伴

根据 [A. Duie Pyle LTL 解决方案](https://aduiepyle.com/solutions/ltl-solutions/) 与 [aduiepyle.com](https://aduiepyle.com/)：

- **美东次日达：** A. Duie Pyle 按小时而非按天衡量时效，**98.6%** 的货物次日送达，揽收到签收平均耗时 **20 小时**。
- **住宅大件与升降尾板：** Pyle 承接住宅货运，超过 85% 的派送车辆配备液压升降尾板（liftgate）；追踪记录中可能出现标明预约时段的 `DELIVERY APPOINTMENT WINDOW`。
- **美东以外联运：** Pyle 通过合作货运商通达美国其他地区与加拿大。当货物移交 Dayton Freight（`DAFG`）、Southeastern Freight Lines（`SEFL`）或 Oak Harbor Freight Lines（`OAKH`）时，其追踪页会以 SCAC 代码标出合作商并提供其 PRO 单号。

## A. Duie Pyle 货物延误、破损或缺失

- **滞留目的分拨站：** 若轨迹显示已到达当地服务中心的 `ARRIVED AT TERMINAL` 却一直未出现 `OUT FOR DELIVERY`，请携带 8 位或 9 位 PRO 单号致电客服 `800-523-5020` 询问派送安排。详见 [物流为何停止更新](guide:tracking-not-updating)（英文）。
- **货运破损、短少或理赔：** 签署收货单**前**务必当面核查托盘货物，将任何肉眼可见的缠绕膜破损、纸箱压损或少件直接批注在司机单据上，随后通知发货方并通过 A. Duie Pyle 理赔门户或客服报案。详见 [包裹破损](guide:damaged-parcel)（英文）、[包裹丢失](guide:lost-parcel)（英文）、[显示已送达却未收到](guide:delivered-not-received)（英文） 和 [退回寄件人](guide:returned-to-sender)（英文）。

## A. Duie Pyle 客服与西切斯特总部

以下官方渠道公布于 [A. Duie Pyle 联系页面](https://aduiepyle.com/contact/)：

- **客服与企业免费热线：** `800-523-5020`（本地电话 `610-696-5800`；客服传真 `610-350-0406`）。
- **在线联系表单与网点列表：** [aduiepyle.com/contact/](https://aduiepyle.com/contact/)。
- **公开货运状态追踪页：** [aduiepyle.com/resources/shipment-status/](https://aduiepyle.com/resources/shipment-status/)。
- **公司总部：** *A. Duie Pyle, Inc.*, 650 Westtown Road, P.O. Box 564, West Chester, PA 19381-0564, United States。

> 请警惕要求线上刷卡付款以放行货运配送的诈骗短信。任何派送预约或尾板服务请直接与零售商或 A. Duie Pyle 客服 `800-523-5020` 核实。参阅 [防范快递诈骗短信](guide:delivery-scam-texts)（英文）。

## 使用 Peek 追踪 A. Duie Pyle 货运

将 `aduiepyle.com/resources/shipment-status/?tracking=` 链接粘贴到 [Peek](/) 中，Peek 会识别为 A. Duie Pyle。由于 8 位和 9 位纯数字与其他承运商重合（9 位数字会先问 [TNT](carrier:tnt)、[TForce Freight](carrier:tforce-freight) 和 XPO），Peek 从不仅凭裸 PRO 单号（虚构示例 `748291736` 或 `48291736`）查询 A. Duie Pyle：输入 PRO 单号后，在添加时点“更换”或在包裹页点“更换快递公司”，选择 **A. Duie Pyle**（搜 “Pyle” 或 “A Duie Pyle” 也可找到）；Peek 随后向 A. Duie Pyle 查询该 PRO 单号，确认后显示“A. Duie Pyle 有这个包裹的记录。”

Peek 读取 A. Duie Pyle 状态，展示阶段（“已揽收”、“运输中”、“派送中”、“已签收”）、英文原文、分拨站所在城镇与州、预计送达日或时段（`ETA WINDOW`，无则取 `DELIVERY APPOINTMENT WINDOW`）、送达时间以及由磅换算成公斤的重量。Pyle 自带时区偏移：若与扫描地所在州一致则保留，否则按扫描地时区解析（安大略省用多伦多时间、得州埃尔帕索用山地时间；无地点则沿用相邻节点）并换算为你设备时钟；午夜零点条目仅保留日期不标具体时刻。当 Pyle 附新 PRO 号交接给 Dayton Freight、Southeastern Freight Lines 或 Oak Harbor Freight Lines 时，Peek 会查验并显示“由……派送”及双单号。在你自己的包裹页上，“收件人”栏显示收货人姓名且在途地图终点可标至目的城镇；分享链接不显示这两项，街道地址亦不显示。发货人、提单/采购单号、件数、备注、签名和签收单据均不展示。

当你在 Peek 中打开包裹页面，或在派送当日开启通知时，Peek 最多每 10 分钟检查一次；在 A. Duie Pyle 显示 `OUT FOR DELIVERY` 当天，只要 A. Duie Pyle 的追踪器有响应，会从最新扫描所在时钟的早上 6 点起每 2 分钟检查一次。其余情况下每小时检查一次。若包裹停留在“已生成面单”或“已揽收”超过 12 小时，或连续两天没有新动态，无论你以何种方式关注，均改为每小时检查一次；若停留在“已生成面单”超过七天，则每 6 小时检查一次。当 A. Duie Pyle 的追踪器无响应、Peek 仅通过追踪服务跟进时，每小时检查一次，静默一天后每 3 小时一次，派送当天最多每 15 分钟一次。

当最新扫描距今已满四天，且包裹仍处于“已生成面单”、“已揽收”或“运输中”时，Peek 会用该次扫描的日期加以标记，例如“自10月12日周一起没有物流更新”。包裹页面随后会显示“包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 A. Duie Pyle 查询。”，并附上指向 A. Duie Pyle 联系页面的“向 A. Duie Pyle 寻求帮助”链接。如果三周没有新扫描，页面会显示“已超过三周没有新动态。如果还没收到，请联系卖家。Peek 会持续向 A. Duie Pyle 查询。”

你可以选择“每次扫描”、“重要环节”或“仅派送当天”通知。Peek 仅监控物流更新，无法替你预约货运、申请升降尾板或联系 A. Duie Pyle。

## 关于 A. Duie Pyle 物流查询的问题

### A. Duie Pyle PRO 单号长什么样？

A. Duie Pyle PRO 单号为不含字母的 8 位或 9 位纯数字（例如虚构示例 `748291736` 或 `48291736`）。

### A. Duie Pyle 追踪里的 `INVOICED` 是什么意思？

`INVOICED` 是货物签收之后的财务开票结算状态，不代表货物再次移动。

### 为什么我的 A. Duie Pyle 货物一直在目的分拨站等待？

Pyle 追踪页不会写明滞留站点的具体原因，请携带 PRO 单号致电 `800-523-5020` 询问派送日期。

### 如何联系 A. Duie Pyle 客服？

可拨打免费客服热线 `800-523-5020`（本地电话 `610-696-5800`），或在 [aduiepyle.com/contact/](https://aduiepyle.com/contact/) 提交在线咨询。

:::sources
- [A. Duie Pyle: Official Website](https://aduiepyle.com/) – 1924 年创立、家族经营美东零担货运、专车、仓储、整车与港口服务
- [A. Duie Pyle: LTL Solutions](https://aduiepyle.com/solutions/ltl-solutions/) – 36 座美东越库服务中心、98.6% 次日达与平均 20 小时揽收到签收时效
- [A. Duie Pyle: Shipment Status](https://aduiepyle.com/resources/shipment-status/) – 公开 PRO 货运状态查询页
- [A. Duie Pyle: Contact & Locations](https://aduiepyle.com/contact/) – `800-523-5020`、`610-696-5800`、传真 `610-350-0406`、宾州西切斯特总部与服务中心名录（Peek 帮助链接）
- [Universal Parcel Scraper: a-duie-pyle](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/a-duie-pyle) – 8/9 位 PRO 格式、`aduiepyle.com/resources/shipment-status/` 链接规则、扫描文案映射、ETA/预约时段、磅转公斤重量与 DAFG/SEFL/OAKH 联运交接
:::
