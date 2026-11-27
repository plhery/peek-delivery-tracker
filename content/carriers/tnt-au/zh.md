---
title: TNT 澳大利亚（TNT Australia）物流查询：国内单号、状态与客服
description: 查询澳大利亚 TNT 国内快递运单：了解 3 字母加 9 数字单号、网点扫描含义、周一至周五再派送规则及 13 11 50 热线。
slug: tnt-australia-tracking
published: 2026-11-27
updated: 2026-11-27
---

TNT 澳大利亚（*TNT Express Australia*，正与 FedEx Express 整合）通过遍布全澳的分拨站承运包裹、文件和托盘（[tnt.com/express/en_au](https://www.tnt.com/express/en_au/site/home.html)）。国际件使用单独的追踪系统（参见 [TNT](carrier:tnt)）；澳大利亚国内运单则在 [tntexpress.com.au](https://www.tntexpress.com.au/interaction/Trackntrace.aspx) 提供专用查询。

你可以在 [TNT Australia Track & Trace](https://www.tntexpress.com.au/interaction/Trackntrace.aspx) 查询澳大利亚国内运单，或直接把 12 位运单号粘贴到上方输入框。

## TNT Australia 追踪单号格式

Peek 通过 12 位单号追踪 TNT Australia 的澳大利亚国内运单：

| 格式（虚构示例） | 说明 |
| --- | --- |
| `SYD482917360` | **3 位大写字母 + 9 位数字**（共 12 位）——在 `tntexpress.com.au` 查询的标准 TNT Australia 国内运单号。 |

由于 3 位字母加 9 位数字同样符合 [Colis Privé](carrier:colis-prive) 与 Purolator 的单号格式，Peek 会按地区顺序轮询这三家承运商：澳大利亚优先查 TNT Australia，加拿大优先查 Purolator，其余地区优先查 Colis Privé；一旦 TNT Australia 返回记录即确认由其承运。部分前缀（如德迅 Kuehne+Nagel 的 `RWM`）已排除在 TNT Australia 规则之外。粘贴来自 `tntexpress.com.au/interaction/ASPs/trackref_detail.asp?con=...` 或 `trackref.aspx?con=...` 的直达链接会立即指向 TNT Australia。

TNT Australia 的 [帮助中心](https://www.tnt.com/express/en_au/site/support.html) 将发票左侧、客户编号下方约 3 厘米处的 **9 位数字**称为货运（运单）号；但 Peek 的 TNT Australia 追踪接口仅接受 3 位字母加 9 位数字。其他澳大利亚包裹格式见 [追踪单号格式指南](guide:tracking-number-formats)（英文）。

## TNT Australia 物流状态含义解析

TNT Australia 国内运单详情页展示四列信息——`Status`、`Date`、`Time` 和 `Depot`——有预计日期时还会在顶部显示 `Estimated Delivery by:`。

| TNT Australia 原文 | 含义说明 |
| --- | --- |
| `Your shipment data is lodged` | 寄件人已在线提交运单数据，TNT Australia 尚未揽收包裹实物。 |
| `We've collected your shipment` | TNT Australia 已从寄件人处揽收运单，正等待首次站点扫描。 |
| `We're processing your shipment` | 已在始发、中转或派送端 TNT Australia 分拨站完成扫描。 |
| `Your shipment's with a driver` | 今天已装上派送车，正前往住宅或商业地址派送。 |
| `New delivery address required` | 派送车无法投递到原登记地址，包裹暂存当地站点等待更新地址或自提。 |
| `We've delivered your shipment` | 已送达收件人地址。 |
| `Your shipment was collected` | 收件人已亲自到暂存的 TNT 分拨站领走包裹（注意区别于 `We've collected your shipment`，后者指承运商从寄件人揽收）。 |
| `Book-In Delivery Requested` / `Book-In Delivery Arranged` | 收件人申请了预约派送且 TNT 已确认。此类条目属于通知而非扫描，不改变包裹阶段，且确认记录有时会在送达之后才入账。 |

## 重新派送、站点自提与延误

[TNT Australia 帮助中心](https://www.tnt.com/express/en_au/site/support.html) 说明了错过派送或需要改期时的处理方式：

- **若司机留有通知单：** 通过 TNT 的 Re-delivery Service 在线预约新的派送日期。
- **若未留通知单（或尚未首次派送）：** 携带运单号、完整收货地址与期望再派送日期联系 TNT Australia。
- **仅限周一至周五再派送：** TNT Australia 明确说明改期再派送仅安排在周一至周五进行。
- **`New delivery address required`：** 包裹将一直保存在 `Depot` 栏所列分拨站，直到你提供新地址或前往自提，请联系 TNT Australia。另见 [错过派送指南](guide:missed-delivery)（英文） 和 [物流停止更新怎么办](guide:tracking-not-updating)（英文）。

如果你的澳大利亚订单由其他国内快递承运，可对照查询 [澳大利亚邮政（Australia Post）](carrier:australia-post)、Aramex Australia、CouriersPlease 或 Border Express。

## 使用 Peek 追踪 TNT Australia

将 3 位字母 + 9 位数字的国内运单号，或 `tntexpress.com.au` 追踪链接直接粘贴到 Peek 中。若 Peek 误归为其他承运商，请在添加时点**更改**或在包裹页点**更换承运商**。

Peek 通过开源的 [universal-parcel-scraper TNT Australia 适配器](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/tnt-au) 直接读取 TNT Australia 国内详情页，遇网络超时会自动重试。界面会显示每条扫描措辞、分拨站名称与时间，并在签收前显示 `Estimated Delivery by:` 预计送达日。TNT Australia 的站点时间不带时区偏移：Peek 按站点所在州时区解析（悉尼用悉尼时间、珀斯用珀斯时间等；无站点名则沿用相邻节点，否则按悉尼时间）并换算为你设备的本地时钟。TNT Australia 国内详情表不提供包裹重量、服务名称或状态代码。

Peek 将节点映射为明确阶段：**已创建运单**（`Your shipment data is lodged`）、**承运商已揽收**（`We've collected your shipment`）、**运输中**（`We're processing your shipment`）、**派送中**（`Your shipment's with a driver`）、**需关注**（`New delivery address required`）与**已送达**（`We've delivered your shipment` 或 `Your shipment was collected`）。预约通知（`Book-In Delivery Requested` 和 `Book-In Delivery Arranged`）会留在时间线中，不会回退已完成送达或改变当前阶段。

当你打开包裹，或在开启通知的情况下进入派送日时，只要 TNT Australia 官网正常响应，Peek 最高每 10 分钟检查一次；在显示 `Your shipment's with a driver` 当天，自最新扫描所在时钟早上 6 点起每 2 分钟检查一次。其余时间每小时一次；在“已创建运单”或“承运商已揽收”下满 12 小时或连续两天无新动态时改为每小时一次，在“已创建运单”下满七天改为每 6 小时一次。若官网无响应而改由综合追踪服务跟进，Peek 每小时检查一次，静默满一天后每 3 小时一次，派送当天最高每 15 分钟一次。

若包裹处于“已创建运单”、“承运商已揽收”或“运输中”且最新扫描满四天，Peek 会按该扫描日期标示并提示“包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 TNT Australia 查询。”，同时附上指向帮助中心的链接；超过三周无更新时会建议联系卖家并继续查询。

你可在 Peek 通知设置中选择**每次扫描**、**重要节点**或**仅派送和送达**。

## 联系 TNT Australia

- **电话（澳大利亚境内）：** `13 11 50`（公布于 TNT Australia 官网各页面）
- **帮助中心与再派送信息：** [TNT Australia 客户支持](https://www.tnt.com/express/en_au/site/support.html)
- **国内追踪门户：** [tntexpress.com.au Track & Trace](https://www.tntexpress.com.au/interaction/Trackntrace.aspx)（一次最多查 10 票国内运单、当日达本地件或 1 个电子参考号）
- **官网与国际追踪入口：** [tnt.com/express/en_au](https://www.tnt.com/express/en_au/site/home.html) 与 [Track & Trace 概览](https://www.tnt.com/express/en_au/site/shipping-tools/tracking.html)

致电 `13 11 50` 或预约工作日再派送时，请准备好 12 位国内运单号和收货地址。

## 常见问题

### `We've collected your shipment` 和 `Your shipment was collected` 有什么区别？

`We've collected your shipment` 出现在运输起点，表示 TNT Australia 已从寄件人处揽收包裹；`Your shipment was collected` 出现在运输终点，表示收件人已到 TNT 分拨站亲自领走暂存包裹，因此 Peek 将其标为**已送达**。

### 为什么包裹签收后又出现 `Book-In Delivery Arranged`？

`Book-In Delivery Arranged` 是 TNT Australia 对预约派送的确认通知，而非实物扫描，有时会在送达之后才入账。Peek 会保持**已送达**状态，不会回退。

### TNT Australia 周末派送或安排再派送吗？

TNT Australia [帮助中心](https://www.tnt.com/express/en_au/site/support.html) 明确表示，改期重新派送仅能在**周一至周五**进行。

### 我的 TNT 单号是纯 9 位数字没有字母，为什么在 Peek 里不能按 TNT Australia 查询？

Peek 的 TNT Australia 追踪接口仅接受 3 位字母加 9 位数字，对应 `tntexpress.com.au` 查询的国内运单。TNT 帮助中心称发票上的 9 位数字也是运单号：请直接在 [TNT Australia Track & Trace](https://www.tnt.com/express/en_au/site/shipping-tools/tracking.html) 上查询，或致电 `13 11 50`。

:::sources
- [TNT Australia](https://www.tnt.com/express/en_au/site/home.html)
- [TNT Australia Help Center](https://www.tnt.com/express/en_au/site/support.html)
- [TNT Australia Track & Trace](https://www.tnt.com/express/en_au/site/shipping-tools/tracking.html)
- [TNT Express Australia Domestic Tracker](https://www.tntexpress.com.au/interaction/Trackntrace.aspx)
- [universal-parcel-scraper TNT Australia adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/tnt-au)
:::
