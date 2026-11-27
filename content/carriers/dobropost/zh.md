---
title: DobroPost 集运单号查询：DBRPST 轨迹、清关与自提点
description: 查询 DobroPost（ДоброПост）中国至俄罗斯与白俄罗斯集运包裹：DBRPST 单号格式、鸡西仓入库与清关状态、自提点时效及客服说明。
slug: dobropost-jiyun-chaxun
published: 2026-11-24
updated: 2026-11-24
---

DobroPost（*ДоброПост*，由莫斯科的 *OOO "Dobropost Logistika"* 与亚美尼亚的 *OOO ICOM DP SOLUTIONS* 共同运营）是一家中国发往俄罗斯与白俄罗斯的跨境集运包裹转运服务商，常用于淘宝、得物（Poizon）、1688、拼多多、天猫、95 分、闲鱼（Goofish）和京东等中国电商平台的海外购物订单。买家先将网购商品寄往 DobroPost 位于中国黑龙江鸡西的集运仓完成核验、打包与称重；包裹清关后送达 DobroPost 自营自提点（PVZ），或移交 CDEK、DPD 及俄罗斯邮政（Russian Post）派送，DobroPost 追踪页面也会同步转发 Boxberry 的扫描记录。你可以在 [tracking.dobropost.com](https://tracking.dobropost.com/) 查询，或直接将 `DBRPST` 单号粘贴到上方查询框中。

## DobroPost 单号格式（`DBRPST` 格式）

DobroPost 国际集运包裹单号以 `DBRPST` 开头，后接 10 位或 11 位数字（以下均为虚构示例）：

| 单号（虚构） | 归属与说明 |
| --- | --- |
| `DBRPST6482917304` | 16 位 DobroPost 国际包裹单号（`DBRPST` + 10 位数字）。 |
| `DBRPST73918462051` | 17 位 DobroPost 国际包裹单号（`DBRPST` + 11 位数字）。 |

商品寄往 DobroPost 鸡西仓途中使用的中国国内快递单号属于各中国快递公司，并非 DobroPost 单号：[tracking.dobropost.com](https://tracking.dobropost.com/) 和 Peek 均通过 `DBRPST` 单号追踪国际段包裹。详见[运单号格式说明](guide:tracking-number-formats)（英文）。

## DobroPost 物流状态与代码含义

DobroPost 结合内部状态码与俄文节点描述发布轨迹——前段来自中国集运仓及清关环节，后段直接透传俄罗斯或白俄罗斯本土承运商（CDEK、Boxberry 或俄罗斯邮政）的扫描：

| DobroPost 代码或文案 | 对你的含义 | Peek 显示 |
| --- | --- | --- |
| `VIRTUAL_PARCEL` | 系统已建立包裹记录；中国仓库将在几天内完成合箱打包。 | “已生成面单” |
| `ON_ASSEMBLY`、`ON_WEIGHING`、`AWAITING_PAYMENT`、`READY_FOR_SENT`、`ADDED_TO_THE_REGISTRY` | 正在中国仓打包称重、等待支付运费，或已加入发运清单。 | “已揽收” |
| `LEFT_THE_DP_WERHOUSE_IN_CHINA`、`FLEW_OUT_FROM_CHINA` | 已离开 DobroPost 中国仓库并启程发往目的国。 | “运输中” |
| `AT_CUSTOMS_IN_CHINA`、`ARRIVED_AT_CUSTOMS_IN_THE_COUNTRY_OFF_DESTINATION`、`START_OF_CUSTOMS_CLEARANCE`、`Выпуск с таможни` | 正在中国出口报关或俄罗斯、白俄罗斯进口清关。 | “清关中” |
| `RELEASE_OF_GOODS_WITHOUT_PAYMENT_OF_CUSTOMS_DUTIES`、`LEFT_CUSTOMS_AND_HEADING_TO_LAST_MILE_CARRIER'S_WAREHOUSE`、`READY_FOR_SHIPMENT_FOR_LAST_MILE_DELIVERY` | 已从海关放行，正转入目的国末端派送网络。 | “运输中” |
| `REFUSAL_TO_RELEASE_GOODS_WITH_INDICATION_OF_THE_CODE_OF_THE_REASON_FOR_REFUSAL` | 海关拒绝放行；轨迹会附带拒放原因（例如不符个人自用标准），请查看 DobroPost 账号。 | “需要留意” |
| `RECEIVED_AT_SHIPMENT_WAREHOUSE`、`SEND_TO_TRANSIT_OFFICE`、`SEND_TO_RECIPIENT_OFFICE`、`ACCEPTED_FOR_DELIVERY`、`SENT_TO_DESTINATION_CITY`、`Прием`、`Обработка` | CDEK、Boxberry 或俄罗斯邮政回传的国内转运扫描，正在运往收件城市。 | “运输中” |
| `ACCEPTED_AT_WAREHOUSE_ON_DEMAND`（CDEK）、`RECEIVED_AT_THE_POINT_OF_ISSUE`（Boxberry） | 已到达选定的自提网点或代收点，可前往领取。 | “待取件” |
| `GETTING_READY_TO_RETURN`、`SENT_TO_COLLECTION_POINT`、`RETURNED_TO_COLLECTION_POINT`、`RETURNED`、`Возврат` | 自提点逾期未取或末端退回，正在返回 DobroPost，可联系客服重新安排发运。 | “已退回寄件人” |
| `RELEASE_OF_GOODS_CUSTOMS_PAYMENTS_PAID`（`Возврат в Китай разрешен`） | 海关拒绝放行并已批准包裹退回中国。 | “已退回寄件人” |
| `PARCEL_DELIVERED_TO_RECIPIENT`、`DELIVERED`、`Вручение` | 已在自提点领取或已妥投签收。 | “已签收” |

## 时效资费与 DobroPost 自营自提点（PVZ）

根据 [DobroPost 资费与时效页面](https://dobropost.com/tariffs)，运输时效从包裹离开 DobroPost 中国仓库起算，至抵达自提点（PVZ）或移交目的国本土快递为止：

- **Express 特快渠道**（按体积重计费，不含敏感品）：至莫斯科通常 **9–13 天**，至圣彼得堡 **10–14 天**，至新西伯利亚、克拉斯诺达尔、叶卡捷琳堡、喀山、伊尔库茨克和乌兰乌德 **9–12 天**，至白俄罗斯明斯克 **11–15 天**。
- **Economy 经济渠道**（按实际重量计费，适合较重或大件包裹）：至莫斯科通常 **19–25 天**，至圣彼得堡 **20–27 天**，至伊尔库茨克 **15–23 天**，至明斯克 **22–29 天**。
- **远东与重货渠道：** 至符拉迪沃斯托克 **6–9 天**，至布拉戈维申斯克 **8–12 天**，5 至 30 公斤单件重货 **6–9 天**。
- **DobroPost 自营自提点（PVZ）：** [dobropost.com/pvz](https://dobropost.com/pvz) 列出了莫斯科、奥金佐沃、圣彼得堡、喀山、克拉斯诺达尔、新西伯利亚、叶卡捷琳堡、伊尔库茨克、乌兰乌德、布拉戈维申斯克、符拉迪沃斯托克、乌苏里斯克及明斯克的自提点。公布的自提取件费为 `100 RUB`（5 kg 以内）、`200 RUB`（10 kg 以内）和 `300 RUB`（10 kg 以上）；无自营 PVZ 的城市通过合作自提点与快递派送。包裹在 DobroPost PVZ 免费保管 7 天，之后按每日 50 ₽ 计费，到店满 30 天将作销毁处理。

## 清关、停滞与包裹异常处理

- **出库前需先支付运费：** 如果轨迹停在 `AWAITING_PAYMENT`，包裹会留在鸡西中国仓，直到你在 DobroPost 客户后台付清核算后的运费账单。
- **跨境运输与清关静默期：** 在 `FLEW_OUT_FROM_CHINA` 与 `ARRIVED_AT_CUSTOMS_IN_THE_COUNTRY_OFF_DESTINATION` 之间或海关审单期间，常有数天无新扫描。请参阅[包裹被海关扣留怎么办](guide:customs)（英文）和[为什么物流停止更新](guide:tracking-not-updating)（英文）。
- **破损、缺件或退回：** [DobroPost 服务条款](https://dobropost.com/rules)要求签收时如有破损须在签收单上注明，并保留内装物与原外包装用于理赔；[知识库](https://dobropost.com/baza_main)也说明了包裹缺件的处理步骤。自提点逾期未取的包裹会退回 DobroPost（`RETURNED`），可联系客服再次发运。详见[包裹丢失](guide:lost-parcel)（英文）、[包裹破损](guide:damaged-parcel)（英文）、[显示已签收但未收到](guide:delivered-not-received)（英文）和[包裹退回寄件人](guide:returned-to-sender)（英文）。

## DobroPost 官方客服与链接

DobroPost 网站设有“Напишите в поддержку”（联系客服）按钮、客户社区与 Telegram 频道，并开放向首席执行官 Sergey Serbin 直接提交建议与投诉的通道。其[知识库](https://dobropost.com/baza_main)详细介绍了如何提交客服工单、更改已创建包裹的资费渠道及办理清关。

- **俄罗斯运营主体：** *OOO "Dobropost Logistika"*（*ООО "Добропост Логистика"*，税号 INN `9722023780`），莫斯科（`119192, Lomonosovsky Prospekt, 43 bldg. 2, office 4/1/1`；其网站同时列出 `Michurinsky Prospekt, 54 bldg. 2`）。
- **亚美尼亚运营主体：** *OOO ICOM DP SOLUTIONS*（*ООО ИКОМ ДП СОЛУШНС*），亚美尼亚洛里州瓦纳佐尔。
- **服务条款、资费与自提点：** [dobropost.com/rules](https://dobropost.com/rules)、[dobropost.com/tariffs](https://dobropost.com/tariffs) 与 [dobropost.com/pvz](https://dobropost.com/pvz)。

> 请警惕要求在官方 DobroPost 客户后台或正规报关行门户之外缴纳额外“清关费”的虚假 Telegram 消息或短信链接。任何费用请务必先在 DobroPost 个人账户内确认。详见[快递诈骗短信识别指南](guide:delivery-scam-texts)（英文）。

## 使用 Peek 追踪 DobroPost 包裹

在 [Peek](/) 输入 `DBRPST` 单号（如虚构示例 `DBRPST6482917304` 或 `DBRPST73918462051`）时，DobroPost 是唯一匹配候选，Peek 会立刻自动识别并标注“识别出的快递公司”；Peek 也能自动识别粘贴的 `tracking.dobropost.com/?trackNumber=` 链接。如需手动指定，可在添加时点击“更换”或在包裹页点击“更换快递公司”（搜索“ДоброПост”或“Dobropost”同样能找到）。

Peek 直接读取 DobroPost 追踪接口，展示对应阶段（“已生成面单”、“已揽收”、“运输中”、“清关中”、“待取件”、“需要留意”、“已退回寄件人”、“已签收”）、DobroPost 及回传合作方（CDEK、Boxberry、俄罗斯邮政）的俄文扫描文案（自动剔除电话号码）以及签收后的送达时间。CDEK 扫描末尾括号中标明的城镇会被提取为该条扫描的地点。所有 Peek 应用均不显示内部状态码。DobroPost 为全部扫描（含中国仓库扫描）均附上莫斯科时区偏移，Peek 会自动换算成你设备的本地时间。DobroPost 追踪接口本身不提供预计送达日期、重量、签收人姓名或末端本地单号。

在打开包裹页面或预计派送当天开启提醒时，只要 DobroPost 网站追踪接口正常响应，Peek 最高每 10 分钟检查一次；其余时间每小时检查一次。由于 DobroPost 追踪流中不含“派送中”节点，Peek 不会切换到 2 分钟一次的派送日高频刷新。若停留在“已生成面单”或“已揽收”满 12 小时，或连续两天无新动态，无论如何关注都会转为每小时检查一次；停留在“已生成面单”满七天后降为每 6 小时一次。当 DobroPost 网站无响应、Peek 仅通过第三方追踪服务跟进时，Peek 每小时检查一次，静默满一天后改为每 3 小时一次，在派送当天打开页面或开启提醒时最高每 15 分钟检查一次。

当最新扫描满四天且包裹仍停留在“已生成面单”、“已揽收”或“运输中”时，Peek 会标出该扫描日期（如“自10月12日周一起没有物流更新”），并在页面提示“包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 DobroPost 查询。”。若超过三周没有新扫描，则显示“已超过三周没有新动态。如果还没收到，请联系卖家。Peek 会持续向 DobroPost 查询。”

你可以自由选择“每次扫描”、“重要环节”或“仅派送当天”通知预设。Peek 仅监控物流更新，无法代你支付运费账单、提交清关护照信息，也无法代为联系 DobroPost 或当地派送商。

## 关于 DobroPost 追踪的常见问题

### DobroPost 的运单号是什么格式？

DobroPost 国际追踪单号以 `DBRPST` 开头，后接 10 位或 11 位数字（例如虚构示例 `DBRPST6482917304` 或 `DBRPST73918462051`）。

### DobroPost 从中国发运通常需要多久？

根据 [dobropost.com/tariffs](https://dobropost.com/tariffs) 公布的估算时效（自离开中国仓起算），至符拉迪沃斯托克约 **6–9 天**，至莫斯科 Express 约 **9–13 天**、Economy 约 **19–25 天**，至明斯克 Express 约 **11–15 天**、Economy 约 **22–29 天**。

### DobroPost 在俄罗斯和白俄罗斯由哪些本土快递派送？

DobroPost 可送至俄罗斯与明斯克的自营 PVZ 自提点；按资费页面说明，合作本土承运商包括 CDEK、DPD 和俄罗斯邮政（明斯克仅通过 CDEK）。其追踪系统同时转发 CDEK、Boxberry 和俄罗斯邮政的扫描记录。

### 为什么我的 DobroPost 包裹停在 `AWAITING_PAYMENT` 或海关？

`AWAITING_PAYMENT` 表示包裹已在中国仓库完成合箱称重，正等待你在客户后台支付运费。若停留在清关环节，可查阅 [DobroPost 知识库](https://dobropost.com/baza_main)了解清关流程，并联系客服核查当前进度。

:::sources
- [DobroPost 网站](https://dobropost.com/) – 中国至俄罗斯/白俄罗斯电商集运、鸡西仓库、客服与 CEO 直达渠道、莫斯科与亚美尼亚运营实体（*OOO "Dobropost Logistika"*，INN 9722023780）
- [DobroPost 运单查询页](https://tracking.dobropost.com/) – 公开 `DBRPST` 追踪页面
- [DobroPost 资费与时效](https://dobropost.com/tariffs) – Express、Economy、远东与重货时效、重量规则及合作承运商
- [DobroPost 自提点（PVZ）](https://dobropost.com/pvz) – 俄罗斯与明斯克自营 PVZ 网点、取件费与保管期
- [DobroPost 知识库](https://dobropost.com/baza_main) – 平台下单指南、客服工单、修改渠道、清关及缺件处理
- [DobroPost 承运条款](https://dobropost.com/rules) – 运输与清关代理规则
- [Universal Parcel Scraper: dobropost](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/dobropost) – `DBRPST` 格式、`tracking.dobropost.com` 链接规则、DobroPost/CDEK/Boxberry/俄罗斯邮政状态映射、括号城镇提取及莫斯科时区时间戳
:::
