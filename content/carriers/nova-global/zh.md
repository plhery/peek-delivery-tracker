---
title: Nova Global 快递追踪：NPG 与 NGUS 单号、清关状态与客服
description: 追踪 Nova Global 跨境包裹：NPG 与 NGUS 运单号格式、数字状态码含义、海关清关、本地合作承运商交接及客服联系方式。
slug: nova-global-tracking
published: 2026-11-26
updated: 2026-11-26
---

Nova Global（法定名称 *LLC "Nova Poshta Global"* / *LLC NP GLOBAL*，隶属 Nova Poshta 集团）是一家国际快递与跨境电商物流承运商，在基辅和新泽西设有办事处，网络覆盖全球 160 多个国家和地区，并连接全球 506,690 个包裹自提点。通过 *Global E-Commerce*、*Global Forwarding*（在包括美国、英国和意大利在内的 16 个国家提供虚拟收货地址）、*Global Sellers* 及 *Global Freight* 四大板块，该公司承运往返乌克兰、美国、加拿大、欧盟及亚洲的国际包裹。你可以在 [Nova Global 追踪页面](https://nova.global/track/) 查询运单，或将单号粘贴到上方输入框中。

## Nova Global 追踪单号：NPG 与 NGUS 格式

请在零售商的发货通知邮件、转运账户或 Nova Post 应用中查找国际运单号。Nova Global 运单号主要有以下三种格式：

| 格式（虚构） | 说明 |
| --- | --- |
| `NP482917364058NPG` | 标准 Nova Global 运单号：2 位字母（如 `NP`、`WB`、`OG` 或 `YW`）+ 9 至 16 位数字 + `NPG`。 |
| `ABCDF4829173640NPG` | 商家订单运单号：5 位字母 + 10 位数字 + `NPG`。 |
| `TE4829173640582917NGUS` | 从中国发出的 Nova Global 运单号：`TE` + 16 位数字 + `NGUS`。 |

抵达目的地国家后，Nova Global 可能会将包裹移交给当地承运商（例如乌克兰境内的 Nova Poshta），并在 `NPG` 或 `NGUS` 运单号旁列出该本地承运商的单号。如需了解其他跨境条码格式，请参阅[单号格式指南](guide:tracking-number-formats)（英文）。

## Nova Global 追踪状态与代码含义

Nova Global 追踪器使用统一的数字状态代码，搭配英文或乌克兰语说明，并标注扫描发生所在国家：

| Nova Global 代码与说明 | 对你的含义 | Peek 显示阶段 |
| --- | --- | --- |
| `1-0`（`Order is processed for delivery to the country of destination`） | 发件人或转运账户已创建国际订单，包裹尚未入库。 | “已生成面单” |
| `1-2`（`Processing an unregistered shipment`）、`14`（`Accepted for delivery`） | 已接收并扫描进入 Nova Global 始发分拨中心或仓库。 | “已揽收” |
| `247`、`76`、`78`、`23`–`26`、`54`、`55`、`30`、`301` | 正在安排空运起飞、航班转运、经由中转枢纽（如华沙或维也纳）转关，或发往目的地城市枢纽。 | “运输中” |
| `20`（`Delivered to the customs terminal`）、`400`、`402`（`Parcel require customs clearance`）、`407` / `407-1`（`documents submitted`）、`410`（`Accompanying documents received`） | 正在接受海关查验，或等待提交清关文件与关税核算。 | “清关中” |
| `401`（`Customs declaration finished`）、`22`（`Left the customs terminal`） | 已完成清关放行并恢复后续转运。 | “运输中” |
| `37`（`On courier delivery`） | 已交由目的地国家当地派送员安排送货上门。 | “派送中” |
| `303`（`Delivered to the locker`）、`38`（`At the post office`） | 已送达自提柜（*поштомат*）或 Nova Poshta 营业网点等待领取。 | “待取件” |
| `553`（`Shipment lost`） | 货件出现遗失异常，请立即联系客服与卖家。 | “需要留意” |
| `3-1`（`Delivered`） | 收件人已签收或完成自提。 | “已签收” |

## 跨境配送、转运与清关说明

[nova.global](https://nova.global/) 介绍了四大业务板块：*Global E-Commerce*（覆盖北美/拉美、欧洲/中东/非洲及亚太的跨境零售配送）、*Global Forwarding*（16 国虚拟购物地址直邮转运到门）、*Global Sellers*（通过 Etsy、Amazon、eBay、Vinted、StockX、Depop 与 TikTok Shop 面向 160 多个国家和地区的跨境卖家出口）以及 *Global Freight*（空运、陆运与海运货运）。

**清关与单证要求。** 进入乌克兰、欧盟、美国或加拿大的国际货件需经海关查验（代码 `20`、`400`、`402`）。Nova Global 的[隐私政策与公开合同](https://nova.global/public-contract/)说明，如寄件国或收件国海关要求，公司可能会请你提供货件相关扫描文件（例如货值证明及其他清关单证）。关于常见海关费用规则（包括欧盟进口增值税、150 欧元以下每件 3 欧元关税及非欧盟订单每件 2 欧元欧盟处理费），请参阅[包裹滞留海关指南](guide:customs)（英文）。

**本地派送规则。** 当 Nova Global 将包裹移交 Nova Poshta 或其他本地承运商时，派送尝试次数、网点保管天数及营业时间均以该本地承运商的规定为准。

## Nova Global 包裹滞留海关、停滞或丢失怎么办

- **跨国中转期间暂无更新：** 空运干线和跨境陆运中转（例如经波兰入境乌克兰）在 `78`（机场起飞）或 `25`（离开中转国）与 `20`（抵达海关监管库）之间往往会出现数天静默期。详见[为何物流停止更新](guide:tracking-not-updating)（英文）。
- **查询交接后的本地单号：** 若 Nova Global 在清关后分配了当地派送承运商及对应单号，请直接前往该合作商网站查看派送时段或自提点信息。
- **丢失（`553`）、损坏或显示已签收但未收到：** 请先联系网购商家（或转运服务方）申请理赔，并携带 `NPG`/`NGUS` 运单号及商业发票联系 Nova Global 客服。另请参阅[显示已签收但未收到包裹](guide:delivered-not-received)（英文）、[包裹损坏处理指南](guide:damaged-parcel)（英文）、[包裹丢失处理指南](guide:lost-parcel)（英文）与[包裹退回发件人](guide:returned-to-sender)（英文）。

## Nova Global 客服与联系方式

以下官方信息发布于 [nova.global/contact/](https://nova.global/contact/)、[nova.global/track/](https://nova.global/track/) 与 [nova.global/public-contract/](https://nova.global/public-contract/)：

- **电子邮箱：** `info@nova.global`。
- **追踪页客服入口：** [nova.global/track/](https://nova.global/track/) 页面提供 *Call us*（致电）、*Send e-mail*（发送邮件）和 *Chat with us*（在线聊天）组件，同时也可在 **Nova Post** 手机应用中查询与求助。
- **公司主体：** LLC "Nova Poshta Global"（*LLC NP GLOBAL*，识别码 `38130410`，税号 `381304126502`），地址 103 Stolychne Hwy, Kyiv, 03026, Ukraine；Nova Global 在美国新泽西亦设有办事处。

> 请勿轻信声称需紧急刷卡支付费用才能“清关放行” Nova Global 包裹的陌生短信或聊天链接。任何正规关税通知请在官方 Nova Post 应用内，或通过 [nova.global/track/](https://nova.global/track/) 与 `info@nova.global` 核实。另请参阅[快递诈骗短信防范指南](guide:delivery-scam-texts)（英文）。

## 使用 Peek 追踪 Nova Global 包裹

[Peek](/) 可高置信度自动识别 `NPG` 运单号（虚构单号 `NP482917364058NPG` 或 `ABCDF4829173640NPG`）及 `NGUS` 运单号（`TE4829173640582917NGUS`）为 Nova Global，也能识别粘贴的包含 `?Tracking_ID=` 的 `nova.global/track/` 或 `novaposhtaglobal.ua/track/` 链接。如需手动选择 Nova Global，可在添加包裹时点击“更换”，或在包裹页面点击“更换快递公司”（搜索“Nova Poshta Global”、“NP Global”或“Нова Пошта Глобал”均可找到）。

Peek 直接读取 Nova Global 的英文追踪数据，展示所处阶段（“已生成面单”、“已揽收”、“运输中”、“清关中”、“派送中”、“待取件”、“需要留意”、“已签收”）、每条扫描说明及其所在国家、目的地国家，以及 Nova Global 提供时的签收时间。`402` 或 `553` 等数字代码用于判定阶段，Peek 不会展示代码本身。Nova Global 位于乌克兰的扫描按基辅时间解析，境外扫描按扫描发生地所在时区解析，并统一转换为你设备的本地时钟显示。当 Nova Global 列出当地承运商及其单号且 Peek 识别该承运商时，Peek 会核实其是否已收到包裹，随后在页面显示“由……派送”及承运商名称，并同时列出两个单号。Peek 不会显示始发国家或包裹的其他关联编号。Nova Global 的追踪器不提供预计送达日期、重量、服务名称、自提点地址或签收照片。

当你在 Peek 中打开包裹页面，或在派送当日开启通知时，Peek 最多每 10 分钟检查一次；在 Nova Global 显示 `37`（`On courier delivery`）当天，只要 Nova Global 自己的追踪系统有响应，Peek 会从最新扫描所在地早上 6 点起每 2 分钟检查一次。其余情况下每小时检查一次。若包裹停留在“已生成面单”或“已揽收”超过 12 小时，或连续两天没有新动态，无论你以何种方式关注，均改为每小时检查一次；若停留在“已生成面单”超过七天，则每 6 小时检查一次。当Nova Global 自己的追踪系统无响应、Peek 仅通过追踪服务跟进时，每小时检查一次，静默一天后每 3 小时一次，派送当天最多每 15 分钟一次。

当最新扫描距今已满四天，且包裹仍处于“已生成面单”、“已揽收”或“运输中”时，Peek 会用该次扫描的日期加以标记，例如“自10月12日周一起没有物流更新”。包裹页面随后会显示“包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 Nova Global 查询。”，并附上指向 Nova Global 联系页面的“向 Nova Global 寻求帮助”链接。如果三周没有新扫描，页面会显示“已超过三周没有新动态。如果还没收到，请联系卖家。Peek 会持续向 Nova Global 查询。”

你可以选择按“每次扫描”、“重要环节”或“仅派送当天”提醒。Peek 仅监控物流更新，无法代你提交清关单证或联系 Nova Global。

## 关于 Nova Global 物流查询的问题

### 追踪单号末尾的 `NPG` 和 `NGUS` 是什么意思？

`NPG` 代表 Nova Global（*Nova Poshta Global*）国际运单号（如虚构单号 `NP482917364058NPG` 或 `ABCDF4829173640NPG`），而 `TE...NGUS`（`TE4829173640582917NGUS`）代表从中国发出的 Nova Global 运单号。

### `Parcel require customs clearance`（`402`）是什么意思？

代码 `402` 表示包裹需要办理海关清关。若海关有要求，Nova Global 可能会联系你提供货值证明等扫描文件；清关完成后状态会更新为 `401`（`Customs declaration finished`）。

### 在乌克兰哪里领取 Nova Global 包裹？

当追踪显示 `303`（`Delivered to the locker`）或 `38`（`At the post office`）时，说明包裹已送达你选定的 Nova Poshta 自提柜（*поштомат*）或营业网点，可以前往取件。

### 如何联系 Nova Global 客服？

你可以发送邮件至 `info@nova.global`、访问 [nova.global/contact/](https://nova.global/contact/)，或使用 [nova.global/track/](https://nova.global/track/) 页面上的电话、邮件及在线聊天入口。

:::sources
- [Nova Global：官方网站](https://nova.global/) – 覆盖 160 多个国家和地区、基辅与新泽西办事处、Global E-Commerce、Global Forwarding（16 国虚拟地址）、Global Sellers 及 Global Freight
- [Nova Global：包裹追踪](https://nova.global/track/) – 公开追踪门户、506,690 个自提点及客服联系入口
- [Nova Global：联系我们](https://www.nova.global/contact/) – `info@nova.global`、LLC NP GLOBAL（税号 `381304126502`，103 Stolychne Hwy, Kyiv）（Peek 帮助按钮链接地址）
- [Nova Global：公开合同与隐私政策](https://nova.global/public-contract/) – *LLC "Nova Poshta Global"*（识别码 `38130410`）、基辅地址及清关单证与数据声明
- [Universal Parcel Scraper: nova-global](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/nova-global) – `NPG`/`NGUS` 识别、`nova.global`/`novaposhtaglobal.ua` 链接规则、状态代码（`1-0` 至 `553`）、乌克兰扫描基辅时间及境外地点时区、本地合作承运商交接提取与帮助链接
:::
