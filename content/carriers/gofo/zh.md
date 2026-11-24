---
title: GOFO Express 美国快递查询：GFUS 单号、签收照片与客服
description: 查询美国 GOFO Express 快递包裹：GFUS/GF/CR/MGP 单号格式、英文扫描状态含义、签收照片查看、未妥投重派及美国客服热线。
slug: gofo-express-meiguo-chaxun
published: 2026-11-24
updated: 2026-11-24
---

GOFO Express 是一家美国本土包裹派送快递公司，负责从发货仓库揽收包裹并经由区域转运枢纽送至你所在片区的派送站点。你可以直接将运单号粘贴到上方查询框中追踪，也可以在 GOFO 官网查件。运单号由发货商家随发货确认邮件或订单页面提供；若找不到，请参阅[如何查找快递单号](guide:find-tracking-number)（英文）。

## GOFO Express 单号格式：GFUS、GF、CR 与 MGP

GOFO 美国网络常见以下四种单号形态（均为虚构示例）：

| 单号（虚构） | 归属与说明 |
| --- | --- |
| `GFUS48291736058291` | `GFUS` 加 14 位数字：GOFO 美国标准运单号；Peek 会把紧跟 `GFUS` 后误输的字母 `O` 自动识别为数字 `0`。 |
| `GF4829173605829` | `GF` 加 13 位数字：GOFO 仍在追踪的早期号段。 |
| `CR482917360582` | `CR` 加 12 位数字：另一组早期号段。 |
| `MGP4829173605829` | `MGP` 加 13 位数字：部分包裹在 `GFUS` 之外同时携带的追踪编号。 |

部分包裹同时带有 `GFUS` 运单号与 `CR` 或 `MGP` 编号，任选其一均可查到同一包裹。对于 GOFO 替 [DHLink](carrier:dhlink) 派送的 DHgate 订单，追踪号采用 DHLink 自有的 `FC` 加 13 位数字，GOFO 官网同样可查。

**其他国家的 GOFO 号段与关联单号。** GOFO 在各国使用独立网络与前缀：`GFCA`（+14 位）属 GOFO Canada，`GFFR`/`CIFR` 属 GOFO France，`GFIT`/`CIIT` 属 GOFO Italy，`GFES`/`CIES` 属 GOFO Spain，`GFNL`/`CINL` 属 GOFO Netherlands，Peek 会自动识别并交由对应国家网络查询。以 `YT` 开头的单号属于[云途物流（YunExpress）](carrier:yunexpress)，即使末端由 GOFO 美国派送，云途与 GOFO 官网均可追踪同一 `YT` 单号。

## GOFO Express 英文物流状态含义解析

GOFO 扫描均为英文原文，大致按流转顺序排列（并非每票都会出现全部节点）：

| GOFO 英文状态 | 对你的含义 | Peek 显示 |
| --- | --- | --- |
| `Shipping Label Created` | 寄件人已创建面单，GOFO 尚未收到实物包裹。 | “已生成面单” |
| `Pick Up Start` | GOFO 已出发前往发件方揽收，尚未拿到包裹。 | “已生成面单” |
| `Pickup Exception, GOFO has not Received the Package from the Warehouse yet.` | GOFO 尚未从发货仓库收到该包裹。 | “已生成面单” |
| `Pick Up Success` | GOFO 已从发件方成功揽收。 | “已揽收” |
| `Arrived at GOFO Regional Origin Hub` | 已抵达 GOFO 始发区域枢纽。 | “已揽收” |
| `Arrived at Sorting Center` | 已抵达分拣中心（在进入区域枢纽之前）。 | “运输中” |
| `Arrived at GOFO Regional Hub` | 已抵达发往你所在地区的区域枢纽。 | “运输中” |
| `Processing at GOFO Facility` | 正在 GOFO 站点分拣处理。 | “运输中” |
| `Departed GOFO Regional Hub` | 已离开该区域枢纽。 | “运输中” |
| `Loaded on Vehicle` | 已装车发往下一转运节点。 | “运输中” |
| `In Transit to Next Facility` | 正在 GOFO 各站点之间转运。 | “运输中” |
| `Arrived at GOFO Delivery Station` | 已抵达本地派送站；GOFO 说明此后通常 1–2 个自然日内送达。 | “运输中” |
| `Departed GOFO Delivery Station` | 已离开本地派送站。 | “运输中” |
| `Out for Delivery` | 司机今天正在派送途中。 | “派送中” |
| `Delivered` | 司机已送达包裹。 | “已签收” |
| `Delivery Exception, Business Closed` | 因商铺关门未能完成投递。 | “派送未成功” |
| `Incorrect/Insufficient Address, Additional Details Required for Delivery.` | 收件地址有误或不全（有时在未妥投次日出现），包裹暂存等待补充地址。 | “需要留意” |
| `Label Cancelled` | 面单已作废，该单号不会再有包裹发出。 | “需要留意” |

GOFO 妥投扫描逗号后常附带放置细节，其官网追踪页同时展示预计送达日期。

## GOFO 签收照片与未妥投处理

**查看签收照片。** 在 GOFO 官网查询页输入下单时填写的收件人姓名后，可查看签收证明（绝大多数包含包裹放置位置照片）；出于隐私保护，Peek 不展示这些照片。

**错过派送或地址异常。** `Delivery Exception` 表示司机尝试投递未果，逗号后写明具体原因（如 `Business Closed`）。Peek 会显示“派送未成功”并附带 GOFO 原文。GOFO 说明通常会在 **24 小时内**再次派送。若因缺少公寓号等地址问题出现 `Incorrect/Insufficient Address…`，包裹可能退回当地仓库，请立即通知发货商家更正。GOFO 不支持收件人自行到站点自提。

## GOFO 包裹延误、未收到或丢件怎么办

- **长时间停在建单阶段：** 若只有 `Shipping Label Created`、`Pick Up Start` 或 `Pickup Exception…`，说明 GOFO 尚未拿到实物，请联系网店确认发货。满一天无变化时 Peek 会提示寄件方尚未交件；未派送前连续四天无新扫描时，Peek 会附上指向 GOFO 客服页面的“向 GOFO Express 寻求帮助”链接。详见[为什么物流停止更新](guide:tracking-not-updating)（英文）。
- **显示已签收但未收到：** 先到 GOFO 官网查看签收照片，排查信箱周边、邻居与楼宇前台，随后同时反馈给 GOFO 与网店卖家。详见[显示已签收但未收到](guide:delivered-not-received)（英文）。
- **丢失或退回：** 请先联系购物商家——GOFO 说明由发件方向 GOFO 发起调查，并向卖家申请退款或重发。若包裹运输受损、地址不全或为军事地址等无法派送区域，GOFO 会直接退回且中途不可截停。详见[包裹丢失](guide:lost-parcel)（英文）与[包裹退回寄件人](guide:returned-to-sender)（英文）。

## GOFO Express 美国官方客服

涉及订单售后请先联系卖家；涉及美国派送问题，[GOFO 客户服务页面](https://www.gofo.com/us/customer-service)（Peek“向 GOFO Express 寻求帮助”链接指向该页）公布了以下美东时间联系渠道：

- **收件人客服电话：** `+1 (949) 688-6032`（周一至周五 `09:00–21:00`，周六与周日 `11:00–19:00`）。
- **WhatsApp：** `+1 (787) 469-5811`（周一至周五 `09:00–21:00`，周六与周日 `11:00–20:00`）。
- **电子邮箱与表单：** `cs@mail.gofo.com`，同页亦提供在线工单表单（需填写运单号）与在线聊天。

## 使用 Peek 追踪 GOFO Express 包裹

在 [Peek](/) 输入 `GFUS` 单号时会立刻识别为 GOFO Express（“识别出的快递公司”）；输入 `GF`、`CR` 或 `MGP` 单号时，Peek 会向 GOFO 核实并在查到记录后自动归入 GOFO Express（空格不影响识别）。在 GOFO 尚未收录该单号前，已归入 GOFO Express 的包裹会显示“等待 GOFO Express 更新”；若两天后仍查不到且无其他追踪服务返回记录，Peek 会提示“两天过去了，快递公司仍查不到这个单号。请检查单号、更换快递公司，或删除这个包裹。”，此后改为每天查询，添加满一周后改为每周查询。部分 AliExpress 包裹在前期由[菜鸟（Cainiao）](carrier:cainiao)使用 GOFO 的 `GFUS` 单号追踪：若 GOFO 连续两天仅有面单记录，Peek 也会自动向菜鸟查询前程轨迹。你也可以点击“更换”手动选择 GOFO Express，或直接粘贴包含 `searchID=` 的 GOFO 美国查件链接。

Peek 每 30 分钟内检查一次 GOFO 美国追踪（派送中亦同，查询失败后间隔 1 小时重试）；连续两天无新动态（或停留在“已生成面单”、“已揽收”满 12 小时）后改为每小时检查一次；若由聚合追踪服务跟进，则每小时检查一次、静默满一天后改为每 3 小时一次，在派送当天打开页面或开启提醒时最高每 15 分钟一次。Peek 会展示标准阶段、带城镇与州名及地图标注的每条扫描、精确时间、签收时间、目的地，以及在派送中/签收/退回之前的预计送达日，同时自动剔除扫描中的客服热线，不展示签收照片、坐标或重量。通知预设可选“每次扫描”、“重要环节”或“仅派送当天”。Peek 无法代你联系 GOFO 或商家。

## 关于 GOFO Express 追踪的常见问题

### GOFO Express 是正规美国快递公司吗？

是的。GOFO Express 是拥有自有追踪系统的美国包裹承运商，官方介绍其美国网络覆盖超过 12,000 个邮编、触达 75% 人口，并在加拿大、法国、意大利、西班牙和荷兰运营独立网络。

### GOFO 轨迹中的 `Shipping Label Created` 是什么意思？

表示卖家已生成面单，但 GOFO 尚未拿到包裹。之后会依次出现 `Pick Up Start`（出发揽收）与 `Pick Up Success`（揽收成功）。

### 为什么我的 GOFO 单号查不到或提示取消？

请确认属于美国号段（`GFUS`、`GF`、`CR` 或 `MGP`）：`GFCA`、`GFFR` 等属于他国网络，`YT` 属云途物流，DHgate 订单可用 `FC` 单号查询。新单号可能尚未录入，Peek 会持续跟进；若显示 `Label Cancelled`，表示该面单已作废，请向卖家索取正确单号。

### 为什么我的 AliExpress 订单给的是 GOFO 单号？

部分发往美国的 AliExpress 订单由 GOFO 承担美国末端派送，菜鸟在前期也沿用该 `GFUS` 单号。在 Peek 中添加 `GFUS` 单号后，若 GOFO 仅显示面单，Peek 会同时向菜鸟同步前程轨迹。详见[速卖通订单追踪指南](guide:aliexpress-orders)（英文）。

:::sources
- [GOFO 美国客户服务](https://www.gofo.com/us/customer-service) – 电话 `+1 (949) 688-6032`、WhatsApp `+1 (787) 469-5811`、邮箱 `cs@mail.gofo.com`、美东时段、不支持自提、24 小时重派与 1–2 天站点派送说明
- [GOFO 美国官网](https://www.gofo.com/us/) – 覆盖超过 12,000 个美国邮编与 75% 人口
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – GOFO 美国与他国号段、`GFUS`/`CR`/`MGP`/`FC`/`YT` 规则、状态映射、菜鸟前程衔接与 Peek 展示字段
:::
