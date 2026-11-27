---
title: SkyPostal 物流查询：SKAMZUS 与 SP 单号、状态与客服
description: 查询美国发往拉美的 SkyPostal 包裹：了解 SKAMZUS 与 SP…BR 单号、迈阿密枢纽与海关状态码及各国客服邮箱。
slug: skypostal-tracking
published: 2026-11-27
updated: 2026-11-27
---

**SkyPostal** 是一家总部位于美国迈阿密的美国本土及美拉跨境包裹派送网络，由 Albert P. 与 A.J. Hernandez 创立（[skypostal.com](https://www.skypostal.com/)）。SkyPostal 依托位于佛罗里达州迈阿密的主要美国出口枢纽，为包括 Amazon、Temu 和 eBay 在内的大型零售商与电商平台承运发往巴西、墨西哥、哥伦比亚、秘鲁、厄瓜多尔、智利和哥斯达黎加的电商包裹，负责出口与进口清关，并将末端派送交由拉美当地合作承运商完成。你可以在 [tracking.skypostal.com](https://tracking.skypostal.com/) 查询，或直接将 SkyPostal 单号或追踪链接粘贴到上方输入框。

## SkyPostal 追踪单号（`SKAMZUS` 与 `SP...BR` 格式）

SkyPostal 会根据发货商户和目的国签发多种跨境单号，最常见的是 17 位 `SKAMZUS` 单号（大量用于亚马逊美国站出口至拉美）以及发往巴西的 15 位 `SP...BR` 单号：

| 格式（虚构示例） | 说明 |
| --- | --- |
| `SKAMZUS0482917365` | 17 位 SkyPostal 亚马逊美国出口单号（`SKAMZUS` 加 10 位数字）。 |
| `SP04829173605BR` | 15 位 SkyPostal 巴西跨境包裹单号（`SP` 加 11 位数字加 `BR`）。 |

手动选择 SkyPostal 时，其追踪系统也支持其他 8 至 40 位、至少包含 1 位数字的字母数字商户运单号及合作方参考号。详见 [追踪单号格式](guide:tracking-number-formats)（英文）。

## SkyPostal 物流状态含义解析

SkyPostal 为每个节点附带 3 位字母事件代码，涵盖电子预清关、迈阿密枢纽入库、国际航班、目的国海关以及当地承运商回传扫描：

| SkyPostal 代码与原文 | 对你的含义 | Peek 显示 |
| --- | --- | --- |
| `DRC`（`DATA RECEIVED`）、`HCV`（`HS_CODE VALIDATION`）、`TIC`（`TAX ID COLLECTED`）、`DCO`（`DOCUMENTATION COMPLETE`） | 包裹到达 SkyPostal 枢纽前已收到订单数据、HS 税号校验及买家税号信息。 | “已创建运单” |
| `RUT`（`RECEPTACLE RECEIVED IN US TRANSPORT FACILITY`）、`REC`（`RECEIVED`） | 包裹实物已抵达 SkyPostal 迈阿密出口设施。 | “承运商已揽收” |
| `XRS`（`X-RAY SCAN`）、`PLP`（`PROVIDER LABEL PRINTED`）、`INC`（`NESTED TO RECEPTACLE`）、`PRO`、`MNF`、`FDP`（`FLIGHT DEPARTED`）、`ATT`、`ATC`（`ARRIVED IN COUNTRY`）、`DTP`（`DELIVERED FROM AIRLINE TO OPERATOR`） | 已在迈阿密处理、装机起飞并在目的国由航空公司移交地面操作商（并非送达收件人门口）。 | “运输中” |
| `CEN`（`CUSTOMS ENTRY`）、`CHE`（`CUSTOMS HOLD AT CONTROL ENTITY`） | 已进入目的国清关流程，或在机场保税仓等待海关放行。 | “清关中” |
| `CCH`（`CUSTOMS CLEARED`）、`TTC`（`TENDERED TO LOCAL CARRIER`）、`TDP`、`PES`（`PROVIDER ENTRY SCAN`）、`INT`、`PRH`、`PDH`、`SLH`（`HOLD RESOLVED`） | 已清关放行、移交拉美本地承运商并在当地分拨中心转运。 | “运输中” |
| `OFD`（`OUT FOR DELIVERY`） | 今天已由目的地合作快递员外出派送。 | “派送中” |
| `PIK`（`AVAILABLE FOR PICKUP`） | 已在当地自提点或邮局备妥，可前往领取。 | “待取件” |
| `CNA`（`CONSIGNEE NOT AVAILABLE`） | 投递未能完成，因为现场无人签收。 | “派送未成功” |
| `HTI`（`HOLD FOR PROBLEMATIC TAX ID`）、`ICA`（`INCORRECT / INCOMPLETE ADDRESS`）、`DRF`（`DELIVERY REFUSED`）、`PLR`（`PROVIDER LABEL REJECTED`）、`INH`（`ON HOLD`） | 因税号问题、地址错误或不完整、标签被拒或拒收而被暂扣。 | “需关注” |
| `DEL`（`DELIVERED`） | 目的地合作商已将包裹送达收件人。 | “已送达” |

需注意 `DTP`（*Delivered from airline to operator*）仅表示航空公司把航空邮袋交给了目的地地面代理，并非妥投，因此 Peek 会将其保持在“运输中”。内部计费事件（`CUT`、`PAY`、`FSC`）不会改变阶段。

## 拉美清关、税号（`HTI`）与当地交接

经由 SkyPostal 从美国发往拉美的跨境包裹对海关与税号合规要求严格：

- **买家税号（`TIC` / `HTI`）：** SkyPostal 会记录收到买家税号（`TAX ID COLLECTED`）及因税号异常导致的暂扣（`HOLD FOR PROBLEMATIC TAX ID`）。若轨迹显示 `HTI`，请立即联系卖家，或附完整追踪单号发送邮件至对应国家的 SkyPostal 客服邮箱。详见 [包裹被海关扣留指南](guide:customs)（英文）。
- **当地末端派送：** 一旦出现 `CCH`（清关放行）和 `TTC`（移交当地承运商），即由当地承运商负责投递，SkyPostal 追踪器也会同步列出合作商的扫描节点及城镇名称。

## SkyPostal 包裹延误、暂扣或丢失

- **迈阿密与目的国海关之间的静默期：** 集运包裹在 `FDP`（航班起飞）、`ATC`（抵达目的国）与 `CCH`（清关放行）之间常会暂停更新数日以等待清关查验。参阅 [物流为何停止更新](guide:tracking-not-updating)（英文）。
- **地址有误（`ICA`）或拒收（`DRF`）：** 请先联系下单平台（Amazon、Temu、eBay 等）申请补发或退款；如果包裹仍在当地暂扣，可附完整追踪单号给对应国家的 SkyPostal 客服邮箱发信。另见 [显示已送达却未收到](guide:delivered-not-received)（英文）、[包裹丢失](guide:lost-parcel)（英文）、[包裹破损](guide:damaged-parcel)（英文） 和 [退回寄件人](guide:returned-to-sender)（英文）。

## SkyPostal 客服与各国支持邮箱

在 [SkyPostal 客户服务](https://www.skypostal.com/customer-service) 页面中，SkyPostal 要求终端收件人**通过邮件**联系团队，并务必附上完整追踪单号及咨询详情（公布回复时效为 **3–5 个工作日**）：

- **巴西：** `Brazilcs@skypostal.com`
- **墨西哥：** `Mexicocs@skypostal.com`
- **哥伦比亚：** `Colombiacs@skypostal.com`
- **秘鲁：** `Perucs@skypostal.com`
- **厄瓜多尔：** `Ecuadorcs@skypostal.com`
- **其他国家：** `customerservice@skypostal.com`
- **美国总部：** *SkyPostal*, 2990 NW 75th Ave, Miami, FL 33122, United States（[skypostal.com/about-us](https://www.skypostal.com/about-us)）。

> 请警惕要求点击陌生网站紧急缴税以放行拉美进口包裹的虚假 WhatsApp 或短信。任何真实税费请务必通过电商平台订单页或官方国家海关/邮政门户核实。参阅 [防范快递诈骗短信](guide:delivery-scam-texts)（英文）。

## 使用 Peek 追踪 SkyPostal 包裹

输入 `SKAMZUS` 单号（虚构示例 `SKAMZUS0482917365`）或 `SP...BR` 单号（虚构示例 `SP04829173605BR`）时，[Peek](/) 会将 SkyPostal 自动识别为唯一候选，并在 SkyPostal 返回历史后确认承运；直接粘贴 `tracking.skypostal.com/?trck_number=` 或 `tracking.skypostal.com/single-tracking.aspx?trck_number=` 链接也能直接识别。其他商户运单号请在添加时点“更改”或在详情页点“更换承运商”选择 **SkyPostal**（搜 “Sky Postal” 同样可找到）。

Peek 读取 SkyPostal 官方追踪器，展示对应阶段（“已创建运单”、“承运商已揽收”、“运输中”、“清关中”、“派送中”、“待取件”、“派送未成功”、“需关注”、“已送达”）、英文扫描原文，以及合作商城镇名或 SkyPostal 机场代码。3 位字母代码用于判定阶段，不会在 Peek 界面展示。SkyPostal 扫描时间不带时区偏移：Peek 按地点所在时区解析（`MIA` 用纽约时间、`GRU` 用圣保罗时间，波哥大或利马亦然；无地点的沿用相邻节点时区）并换算为你设备的本地时钟。由于合作商记录可能从不同数据源分时段重复回传，送达之后补传的早期节点不会把已送达状态回退。Peek 不保存签收凭证、备注或合作商单号，也不会显示签收人姓名；SkyPostal 追踪器不提供预计送达日、重量或签收时刻。

当你打开包裹，或在开启通知的情况下进入派送日时，只要 SkyPostal 官网正常响应，Peek 最高每 10 分钟检查一次；在出现 `OFD`（`OUT FOR DELIVERY`）当天，自最新扫描所在时钟早上 6 点起每 2 分钟检查一次。其余时间每小时一次；在“已创建运单”或“承运商已揽收”下满 12 小时或连续两天无新动态时改为每小时一次，在“已创建运单”下满七天改为每 6 小时一次。若 SkyPostal 追踪器无响应而改由综合追踪服务跟进，Peek 每小时检查一次，静默满一天后每 3 小时一次，派送当天最高每 15 分钟一次。

若包裹处于“已创建运单”、“承运商已揽收”或“运输中”且最新扫描满四天，Peek 会按该扫描日期标示并提示“包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 SkyPostal 查询。”，同时提供指向 SkyPostal 客服页面的帮助链接；超过三周无更新时会建议联系卖家并继续查询。

你可设置“每次扫描”、“重要节点”或“仅派送和送达”通知。Peek 仅监控物流进度，无法代交税号、办理拉美清关或替你联系 SkyPostal。

## SkyPostal 追踪常见问题

### SkyPostal 追踪单号是什么格式？

常见格式包括用于亚马逊美国站出口的 17 位 `SKAMZUS` 单号（虚构示例 `SKAMZUS0482917365`）以及发往巴西的 15 位 `SP...BR` 单号（虚构示例 `SP04829173605BR`）；SkyPostal 同时也接受其他 8 至 40 位商户参考号。

### SkyPostal 中的 `DTP`（`DELIVERED FROM AIRLINE TO OPERATOR`）是什么意思？

这并不表示包裹已经送到你家。`DTP` 意思是航空公司已把集运总包移交给目的机场地面代理，接下来才会进入清关（`CEN`）及移交当地快递（`TTC`）流程。

### `HTI`（`HOLD FOR PROBLEMATIC TAX ID`）是什么意思？

表示目的国海关发现收件人税号缺失或不匹配（例如巴西的 CPF 或墨西哥的 RFC）。请联系发货商家，并致信 [skypostal.com/customer-service](https://www.skypostal.com/customer-service) 所列的对应国家 SkyPostal 客服邮箱。

### 如何联系 SkyPostal 客服？

请附完整追踪单号发送邮件至目的国对应邮箱（`Brazilcs@`、`Mexicocs@`、`Colombiacs@`、`Perucs@`、`Ecuadorcs@` 或 `customerservice@skypostal.com`）；SkyPostal 公布回复时间为 3–5 个工作日。

:::sources
- [SkyPostal: Official Website](https://www.skypostal.com/) – 美国本土与拉美跨境物流、迈阿密总部
- [SkyPostal: About Us](https://www.skypostal.com/about-us) – 公司历程与拉美私营派送网络
- [SkyPostal: Customer Service](https://www.skypostal.com/customer-service) – 各国收件人客服邮箱（巴西、墨西哥、哥伦比亚、秘鲁、厄瓜多尔与通用）、Amazon/Temu/eBay 合作说明与 3–5 个工作日回复时效（Peek 帮助链接）
- [SkyPostal: Tracking Portal](https://tracking.skypostal.com/) – 公开单票追踪页面
- [Universal Parcel Scraper: skypostal](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/skypostal) – `SKAMZUS` 与 `SP...BR` 识别规则、`tracking.skypostal.com` 链接规则、3 位事件代码映射（`DRC`–`DEL`、`DTP`、`HTI`）与扫描时区处理
:::
