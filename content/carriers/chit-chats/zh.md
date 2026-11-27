---
title: Chit Chats 加拿大跨境追踪：10 位单号、USPS 交接与客服
description: 追踪从加拿大发往美国、加拿大及全球的 Chit Chats 包裹：10 位单号、USPS/Canada Post 交接扫描及 1-844-842-8777。
slug: chit-chats-tracking
published: 2026-11-26
updated: 2026-11-26
---

**Chit Chats**（*Chit Chats Express*）是一家由 Derek Nolan 于 2001 年创立的加拿大电商寄递公司，在加拿大全境设有 66 个网点，也支持邮寄投递（SmartDrop）与上门揽收，已为超过 100,000 名寄件人运送逾 5,000 万件包裹（[chitchats.com](https://chitchats.com/)）。包裹的末端派送由合作承运商完成：其追踪页面会列出合作承运商名称并提供其单号，例如美国的 [USPS](carrier:usps) 或加拿大的 [Canada Post](carrier:canada-post)。你可以在 [chitchats.com/tracking](https://chitchats.com/tracking) 查询货件，或将 10 位 Chit Chats 货件编号粘贴到上方输入框中。

## Chit Chats 追踪单号（10 位货件编号）

每件 Chit Chats 订单都会获得一个 10 位大写字母数字**货件编号（shipment ID）**（由字母与数字组合而成，通常以一个字母和非零数字开头）：

| 格式（虚构） | 说明 |
| --- | --- |
| `K4N8V3T6R2` | 标准 10 位 Chit Chats 货件编号。 |
| `P7M3X9D5Q8` | 用于美国、加拿大或国际邮资的另一种 10 位 Chit Chats 货件编号。 |

Chit Chats 将包裹移交末端合作承运商后，追踪页还会显示该合作伙伴的自有单号——例如 [USPS](carrier:usps) 条码（有时带有 `420` + 邮编路由前缀，Peek 会去掉该前缀）或 [Canada Post](carrier:canada-post) 单号。详见[单号格式指南](guide:tracking-number-formats)（英文）。

## Chit Chats 追踪状态含义

Chit Chats 追踪系统先展示其自身设施的扫描，随后展示合作承运商（如 [USPS](carrier:usps)、[Canada Post](carrier:canada-post) 或其他承运商）的扫描：

| Chit Chats 或合作伙伴显示状态 | 对你的含义 | Peek 显示阶段 |
| --- | --- | --- |
| `Shipment created, Chit Chats awaiting item`、`Label Created`、`Electronic information submitted by shipper` | 商家已在线购买邮资面单，但尚未将包裹交至 Chit Chats 网点。 | “已生成面单” |
| `Received by Chit Chats` | 实物包裹已在加拿大 Chit Chats 网点或投递点扫描揽收。 | “已揽收” |
| `Arrived at Chit Chats facility`、`Departed from Chit Chats facility`、`In transit` | 已在 Chit Chats 站点分拣；`In transit` 表示正在向合作承运商移交。 | “运输中” |
| `Received from Chit Chats`、`Accepted at USPS Facility`、`Item accepted at the Post Office`、`Received at sort hub` | 已录入合作承运商（USPS、Canada Post 或国际合作伙伴）的分拣网络。 | “运输中” / “已揽收” |
| `Arrived at USPS Facility`、`Departed USPS Facility`、`In Transit to Next Facility`、`Item processed` | 正在合作承运商国内或国际分拣设施间流转。 | “运输中” |
| `Out for Delivery`、`Item out for delivery` | 今日已交由当地合作承运商派送上门。 | “派送中” |
| `Notice card left indicating where and when to pick up item` | 合作承运商派送时无人签收并留下了取件通知卡。 | “派送未成功” |
| `Item available for pickup at Post Office`、`Final Notice; Item will be returned to sender if not collected within 10 days` | 正在邮局等待收件人领取（即使是最终提醒也表示包裹仍在待领状态，尚未退回）。 | “待取件” |
| `Delivered`、`Delivered, Front Door/Porch`、`Delivered, In/At Mailbox`、`Shipment Delivered` | 合作承运商已将包裹送达收件地址。 | “已签收” |

## 跨境美国入境、清关与合作承运商交接

- **发往美国：** Chit Chats 提供附带清关、完税交货（DDP）及 CUSMA 单证协助的对美寄递服务，参考时效为 2–6 个工作日，并在 [chitchats.com](https://chitchats.com/) 发布 2026 年美国关税动态。详见[包裹滞留海关指南](guide:customs)（英文）。
- **在合作承运商的追踪页查询：** Chit Chats 完成交接后，其联系页面建议使用合作承运商的追踪单号，在该承运商自己的追踪页上跟进。

## Chit Chats 包裹延误、无更新或丢失怎么办

- **优先联系卖家：** Chit Chats [联系页面](https://chitchats.com/contact)说明，其客服团队看到的追踪信息与你完全一致，且仅能就账号相关事宜向发件人提供咨询，因此收件人应直接联系卖家。
- **包裹逾期：** 若已超过预计送达时间，Chit Chats 追踪页面建议联系卖家。详见[为何物流停止更新](guide:tracking-not-updating)（英文）。
- **显示已签收或丢失：** 请检查信箱、门廊或当地邮局（尤其是 Canada Post 留有取件通知卡时），随后尽快联系卖家。另见[显示已签收但未收到包裹](guide:delivered-not-received)（英文）、[包裹丢失处理指南](guide:lost-parcel)（英文）、[包裹损坏处理指南](guide:damaged-parcel)（英文）与[包裹退回发件人](guide:returned-to-sender)（英文）。

## Chit Chats 客服与加拿大联系方式

以下官方渠道发布于 [Chit Chats 联系我们](https://chitchats.com/contact)及 [Chit Chats 帮助中心](https://support.chitchats.com/en/support/home)：

- **免费电话（英语与法语——账单与网点咨询）：** `1-844-842-8777`（全加拿大 66 个网点亦在营业时段提供各自电话）。
- **帮助与支持门户：** [support.chitchats.com/en/support/home](https://support.chitchats.com/en/support/home) 与 [chitchats.com/contact](https://chitchats.com/contact)。
- **公开货件追踪：** [chitchats.com/tracking](https://chitchats.com/tracking)。

> 请防范要求在线支付小额费用以“清关放行”美加跨境包裹的虚假短信。任何关税或邮资通知，请先直接通过卖家或 USPS / Canada Post 的官方追踪核实。详见[快递诈骗短信防范指南](guide:delivery-scam-texts)（英文）。

## 使用 Peek 追踪 Chit Chats 包裹

当你输入 10 位 Chit Chats 货件编号（`K4N8V3T6R2` 或 `P7M3X9D5Q8`，均为虚构）时，[Peek](/) 会将其作为唯一候选自动查询 Chit Chats，并在确认后显示“Chit Chats 有这个包裹的记录。”；Peek 也能直接识别粘贴的 `chitchats.com/tracking/<10位单号>` 链接。如需手动选择，可在添加时点击“更换”或在包裹页点击“更换快递公司”（搜索“ChitChats”或“Chit Chats Express”均可找到）。

Peek 直接读取 Chit Chats 追踪器，展示阶段（“已生成面单”、“已揽收”、“运输中”、“派送中”、“待取件”、“派送未成功”、“已签收”）、Chit Chats 及合作承运商的扫描原文以及不含邮编的城市名称（含街道门牌号的地址会被过滤）。页面同时显示预计送达日期、重量（千克）和尺寸。收件人姓名仅在你本人的包裹页“收件人”栏显示，在途期间你本人的地图路线末端可能标出目的地城镇，分享链接则两者均不展示。当 Chit Chats 指明 Peek 识别的合作承运商（如 USPS 或 Canada Post）并附带其单号（USPS 单号已剥离邮编路由前缀）时，Peek 会核实对方是否已收到包裹：包裹随后会显示“由 <快递公司> 派送”，并同时列出两个单号。Chit Chats 自身扫描带有准确时间戳；合作承运商扫描虽被 Chit Chats 标成太平洋时间，但可能偏差数小时，因此 Peek 会按扫描地所在时区解析（如 `NEW YORK, NY` 按纽约时间，`TORONTO, ON` 按多伦多时间），无地点的沿用相邻扫描时区，其余默认温哥华时间，并全部以你设备的本地时钟展示。Peek 会省略服务名称、状态代码及发件人城镇。

当你在 Peek 中打开包裹页面，或在派送当日开启通知时，Peek 最多每 10 分钟检查一次；在追踪显示 `Out for Delivery` 或 `Item out for delivery` 当天，只要 Chit Chats 自己的追踪系统有响应，会从最新扫描所在地早上 6 点起每 2 分钟检查一次。其余情况下每小时检查一次。若包裹停留在“已生成面单”或“已揽收”超过 12 小时，或连续两天没有新动态，无论你以何种方式关注，均改为每小时检查一次；若停留在“已生成面单”超过七天，则每 6 小时检查一次。当Chit Chats 追踪系统无响应、Peek 仅通过追踪服务跟进时，每小时检查一次，静默一天后每 3 小时一次，派送当天最多每 15 分钟一次。

当最新扫描距今已满四天，且包裹仍处于“已生成面单”、“已揽收”或“运输中”时，Peek 会用该次扫描的日期加以标记，例如“自10月12日周一起没有物流更新”。包裹页面随后会显示“包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 Chit Chats 查询。”，并附上指向 Chit Chats 联系页面的“向 Chit Chats 寻求帮助”链接。如果三周没有新扫描，页面会显示“已超过三周没有新动态。如果还没收到，请联系卖家。Peek 会持续向 Chit Chats 查询。”

你可以选择“每次扫描”、“重要环节”或“仅派送当天”提醒。Peek 仅监控物流更新，无法代你提交理赔、修改地址或联系 Chit Chats 及其合作承运商。

## 关于 Chit Chats 物流查询的问题

### Chit Chats 追踪单号长什么样？

Chit Chats 货件编号由 10 位大写字母和数字组成（例如 `K4N8V3T6R2` 或 `P7M3X9D5Q8`，均为虚构）。交接后，包裹还会获得合作承运商单号（例如 USPS 或 Canada Post）。

### 究竟是谁把 Chit Chats 包裹送到我家门口？

由合作承运商执行末端派送，例如在美国是 [USPS](carrier:usps)，在加拿大是 [Canada Post](carrier:canada-post)。Chit Chats 追踪页会注明合作伙伴名称及其追踪单号。

### 为什么包裹在 `Departed from Chit Chats facility` 后暂停更新？

Chit Chats 追踪器未列出具体原因。其 `In transit` 代表正在向合作承运商移交，待对方扫描接收后便会出现后续记录。若已超过预计送达时间，请联系卖家。

### Chit Chats 包裹丢失应该联系谁？

请联系寄出包裹的卖家。根据 [chitchats.com/contact](https://chitchats.com/contact)，Chit Chats 团队仅能就账号相关事宜向发件人提供协助。其免费热线 `1-844-842-8777` 主要受理账单、网点咨询及法语服务。

:::sources
- [Chit Chats：官方网站](https://chitchats.com/) – 加拿大电商跨境集运商，覆盖美国、加拿大及国际邮寄
- [Chit Chats：关于我们](https://chitchats.com/about) – 由 Derek Nolan 于 2001 年创立、5,000 万+ 包裹量、66 个加拿大网点与 10 万+ 寄件客户
- [Chit Chats：追踪门户](https://chitchats.com/tracking) – 10 位货件编号查询页面
- [Chit Chats：联系我们](https://chitchats.com/contact) – 免费热线 `1-844-842-8777`、收件人联系卖家政策及网点目录（Peek 帮助按钮链接地址）
- [Chit Chats：帮助与支持门户](https://support.chitchats.com/en/support/home) – 官方帮助中心
- [Universal Parcel Scraper: chit-chats](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/chit-chats) – 10 位编号识别、`chitchats.com/tracking/` 链接规则、自有与中继扫描状态映射、预计送达日/重量/尺寸提取及 USPS 420 前缀剥离
:::
