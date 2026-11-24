---
title: 印度 Ekart 快递查询：14 位单号、状态与 RTO 退件说明
description: 查询印度 Ekart Logistics 包裹：14 位运单号前缀与格式、InscannedAtDH 与 RTO 退件等状态中文解读及客服渠道。
slug: ekart-yindu-kuaidi-chaxun
published: 2026-11-24
updated: 2026-11-24
---

Ekart（又称 eKart Logistics）是印度一家快递公司，负责向卖家揽收电商订单，经旗下分拨枢纽转运并派送至收件人手中。运单号（tracking ID）由卖家或网店提供：请在发货通知里查找或向卖家索取，然后粘贴到上方输入框即可查询；Ekart 官方查件网站为 ekartlogistics.in。

## Ekart 运单号是什么样的？

Peek 代码识别的 Ekart 运单号由 3 个字母、接着 `C`、`P` 或 `R`、再加 10 位数字组成，共 14 个字符。以下数字均为虚构：

| 虚构运单号示例 | 说明 |
| --- | --- |
| `FMPP4829105736` | Peek 绑定至 Ekart 的字母前缀（见下方列表）+ `C`/`P`/`R` + 10 位数字。Peek 仅凭格式就能立刻识别为 Ekart。 |
| `ABCP4829105736` | 其他三位字母前缀但结构相同：可能属于 Ekart，但 Peek 无法单凭格式确认，会向 Ekart 官网查询核实（见下文）。 |

Peek 直接绑定为 Ekart 的三位字母前缀共有：`AMI`、`BAL`、`BGS`、`BSI`、`DEL`、`FLO`、`FMP`、`GLA`、`LUA`、`MYE`、`MYN`、`MYS`、`SDL`、`SHO`、`SPO`、`SRT` 和 `TEL`。往 Peek 粘贴时空格和大小写均不影响识别。有两个例外：同样结构但以 `PB` 开头的编号会被识别为英国快递 Parcelforce；以 `HRBC` 开头的编号因符合速卖通（AliExpress）格式不会自动推荐 Ekart。如果你的包裹来自印度网店，请点击“更换”手动选择 Ekart。

查询请使用运单号（tracking ID），不要填网店订单号。如果找不到，请在商家通知里找 Ekart 查件链接，或参考[如何查找运单号](guide:find-tracking-number)（英文）。

## Ekart 物流状态中文解读

Ekart 查件页为每次扫描列出日期、时间与城市。部分记录是直白英语（如 `Shipment Created`），另一些则是状态代码加短横线再加枢纽名称（如 `InscannedAtDH` 接枢纽名）。Peek 代码收录的常见记录大致按流转顺序如下：

| Ekart 原文 | 中文含义 | Peek 显示阶段 |
| --- | --- | --- |
| `Pickup Requested`、`Shipment Created` | 卖家已预约 Ekart 下单，但 Ekart 尚未拿到包裹。 | 已生成面单 |
| `OutForPickupEvent` | Ekart 揽收车正前往卖家取件，包裹仍在卖家手里。 | 已生成面单 |
| `NotPickedEvent` | 揽收未成功，包裹仍在卖家手里。 | 已生成面单 |
| `Pickup From Seller`、`PickupReceived` | Ekart 已从卖家处揽收包裹。 | 已揽收 |
| `ShipmentGrouped`、`ShipmentInTransit`、`dispatched_to_tc`、`dispatched`、`Dispatched to` + 下一枢纽 | 正在 Ekart 各分拨枢纽之间转运。 | 运输中 |
| `Expected` | 下一站枢纽已收到包裹发往通知。 | 运输中 |
| `Received`、`Received at` + 枢纽名 | 已抵达某处 Ekart 分拨枢纽。 | 运输中 |
| `InscannedAtFacility` | 已在 Ekart 处理设施扫描入库。 | 运输中 |
| `InscannedAtDH`、`PrimaryDHInscan` | 已到达负责你片区的派送枢纽（DH，Delivery Hub），接下来将安排派送。 | 运输中 |
| `Out For Delivery` | 今天正在派送途中。 | 派送中 |
| `Delivered` | 已送达给你；若出现在 RTO 退件记录之后，则表示已退回到卖家手中（见下文）。 | 已签收 |
| `undelivered_attempted`、`ShipmentUndeliveredAttempted` | 曾尝试派送但未成功；同时间枢纽旁可能伴随 `undelivered_update`。 | 派送未成功 |
| `undelivered_unattempted` | 当日派送班次结束，但未实际尝试投递。 | 派送未成功 |
| `Unsuccessful` + 原因（如 `Unsuccessful Unable to attempt delivery due to operational issues` 或 `Unsuccessful Undelivered. (Rescheduled to: …)`） | 当日派送未送达的文字说明；`Rescheduled to:` 后面的日期为下次计划派送日。涉及卖家揽收失败的 `Unsuccessful` 不按此归类。 | 派送未成功 |
| `PickupCancel` | 揽收在包裹进入 Ekart 网络前已取消。 | 需要留意 |
| `ShipmentRtoConfirmed` | 已确认退回寄件人（RTO，Return to Origin），包裹正准备退回卖家。 | 需要留意 |
| `Marked RTO: ORDER_REJECT` | 多次未妥投后的文字标记，包裹被标注退回卖家，冒号后为原因；后续记录均为退件回程。 | 需要留意 |
| `RtoExpected`、`RtoReceived`、`ReturnInscanned` | 正在退回卖家途中。 | 运输中 |

**关于 RTO 退件：** 一旦出现 `ShipmentRtoConfirmed`、`Marked RTO` 或任何以 `Rto`、`Return` 开头的状态码，Peek 就会判定包裹已进入退回寄件人流程，此后所有轨迹均属回程：此时再出现 `InscannedAtDH` 指的是卖家所在地的派送枢纽而非你的，后续出现 `Delivered` 也表示已退还卖家，因此 Peek 会显示为“已退回寄件人”而非“已签收”。详见[物流状态说明](guide:tracking-statuses)（英文）。

## Ekart 在印度的派送方式

Peek 代码仅覆盖印度境内的 Ekart 派送。从上述状态可以看出其流转路径：从卖家揽收、途经各级枢纽、抵达你附近的派送枢纽，然后安排当日派送；Peek 不会把 Ekart 包裹交接给其他承运商。

本页未提供具体送达时效、派送尝试次数或改期入口，Peek 代码中也不包含 `undelivered_attempted` 后还会重派几次或何时退回卖家的规则，请联系预订 Ekart 的网店确认。另见[错过派送怎么办](guide:missed-delivery)（英文）。

## 轨迹停滞、RTO 退件或未收到怎么办？

**停在 `Shipment Created` 或 `Pickup Requested`：** 卖家已下单但还没把包裹交给 Ekart；`NotPickedEvent` 表示上门取件失败。请联系卖家询问何时发出。

**多日无新扫描：** 若已下单、已揽收或在途包裹连续四天无新动态，Peek 会标出最新扫描日期（例如“自 10 月 12 日起没有物流更新”）。请联系商家排查原因，另见[物流停止更新怎么办](guide:tracking-not-updating)（英文）。

**出现 `undelivered_attempted`、`undelivered_unattempted` 或 `Unsuccessful`：** 本次未能送达；`Rescheduled to:` 注明下次计划派送日期。随后轨迹会显示包裹是再次出车派送还是退回。请向商家核对你的收件地址与电话是否无误。

**出现 `ShipmentRtoConfirmed` 或 `Marked RTO: ORDER_REJECT`：** 包裹正在退回卖家。待 Ekart 官网显示退回 `Delivered` 后，Peek 会显示“已退回寄件人”。请联系商家退款或重新发货，详见[包裹退回寄件人](guide:returned-to-sender)（英文）。

**出现 `PickupCancel`：** 向卖家揽收已取消，包裹从未进入 Ekart 网络，请向商家确认订单是否仍会发货。

**显示已签收但没收到：** 先询问家人、邻居以及小区门卫或前台，再联系网店处理。另见[显示已签收但未收到](guide:delivered-not-received)（英文）。

**丢失或破损：** 请直接联系下单网店或电商平台，由其对接 Ekart 处理。另见[包裹丢失怎么办](guide:lost-parcel)（英文）和[包裹破损怎么办](guide:damaged-parcel)（英文）。

## Ekart 客服与联系方式

Ekart 官网并未向收件人公布客服电话或邮箱；其常见问题页面只引导卖家在商家后台提交工单（承诺 24 小时内回复）。网购订单请优先联系商家或电商平台，因为运费由其预订，退款或补发也由商家处理。

收到要求付款的快递短信前，请先阅读[防范快递诈骗短信](guide:delivery-scam-texts)（英文）。

## 用 Peek 查询和跟踪 Ekart 包裹

把运单号直接粘贴到 [Peek](/) 即可。若以本文列出的 17 组前缀加 `C`、`P` 或 `R` 开头，Peek 会立刻识别为 Ekart（显示“识别出的快递公司”），点击“更换”可切换其他快递。若是其他三位字母前缀，Peek 会先显示“正在查询物流服务…”并向 Ekart 官方查件页核实：只要查到扫描记录，就显示“Ekart 有这个包裹的记录”并持续直连跟进；如果 Ekart 官网暂查不到，则改问综合跟踪服务——如需强制直连 Ekart，点“更换”在“符合这个单号”下手动选定 Ekart 即可。

支持直接粘贴 Ekart 查件链接（ekartlogistics.com 路径含 shipmenttrack 与运单号），无论前缀是什么字母，Peek 都会提取单号并归为 Ekart。如果卖家发来的是 shiprocket.co 查件链接，也可以粘贴，但 Peek 会通过 Shiprocket 查询页而不是 Ekart 官网跟进。若为非“4 位字母 + 10 位数字”的单号强行选定 Ekart，界面会提示“不使用这种格式的单号”。

归入 Ekart 的包裹在首次扫描前会显示“等待 Ekart 更新”；若 Ekart 首次回复查无此号满两天仍无记录，Peek 会提示你核对单号、更换快递公司或删除包裹。未指定承运商添加的包裹则显示“暂无物流记录”。Peek 无需账号或手机号即可直连读取 Ekart 官方轨迹，按时间倒序、去重保留原文措辞与状态码、精确时间及城市名。所有时间均换算为你设备的本地时区（在 peektracker.com 及 iPhone、Android 应用中，物流历史底部注明“时间按你所在的时区显示：”及你的时区名称）。在途期间还会展示 Ekart 给出的预计送达日期，签收或进入退件后则不再显示。

当你打开包裹页面或在派送当天开启提醒，且 Ekart 官方查询页正常响应时，Peek 最高每 10 分钟检查一次，在包裹扫描进入派送当天自印度时间早上 6 点起每 2 分钟检查一次；若仅通过综合跟踪服务获取更新，则在派送当天打开页面或开启提醒时最高每 15 分钟检查一次；其余情况每小时一次。连续两天无新动态（或在“已生成面单”、“已揽收”状态下满 12 小时），改为每小时检查一次。支持设置“每次扫描”、“重要环节”或“仅派送当天”提醒。Peek 无法代你联系 Ekart 或商家。

## Ekart 查询常见问题

### 怎么查询我的 Ekart 包裹？

把网店发货通知里的运单号（tracking ID）粘贴到 Peek，或输入 ekartlogistics.in 的官方查件框即可。注意填写的是运单号而非订单号。

### Ekart 运单号是什么格式？

3 个字母 + `C`、`P` 或 `R` + 10 位数字，例如虚构单号 `FMPP4829105736`。

### Ekart 轨迹里的 RTO 是什么意思？

RTO 指退回寄件人（Return to Origin）。出现 `ShipmentRtoConfirmed` 或 `Marked RTO: ORDER_REJECT` 表示退件开始，之后的 `Delivered` 表示已退回卖家手中。请联系网店申请退款或重发。

### Ekart 轨迹里的 InscannedAtDH 是什么意思？

表示包裹已到达 Ekart 派送枢纽（Delivery Hub）：发往你途中指负责你所在片区的派送枢纽；进入 RTO 退件流程后则指卖家所在地的枢纽。

### Ekart 包裹丢了该联系谁？

请联系下单的网店或电商平台，因为是由商家预订的 Ekart；Ekart 官网没有面向收件人的客服电话。

:::sources
- [Ekart Logistics: FAQ](https://www.ekartlogistics.in/faq) – 面向卖家的商家后台工单与 24 小时回复说明
:::
