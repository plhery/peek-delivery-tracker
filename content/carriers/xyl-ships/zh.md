---
title: XYL-Ships（FXYL）物流查询：NUDE与XLWL单号及状态
description: 查询 XYL-Ships（FXYL）从中国发出的跨境小包：NUDE 与 XLWL 单号格式、各运输与派送节点含义、静默期原因及联系卖家建议。
slug: xyl-ships-fxyl-wuliu-chaxun
published: 2026-11-23
updated: 2026-11-23
---

XYL-Ships（XYL-ships International Logistics，常写作 FXYL）是一家持有中国民用航空局（CAAC）与国际航空运输协会（IATA）航空运输销售代理资质的中国跨境物流服务商。除商业空运、海运、铁路和公路货运外，它还承接从中国发往海外的跨境电商单件小包业务，并在目的国交由当地邮政或快递合作伙伴完成末端派送。你可以通过 [xylships.com](https://xylships.com/) 上的“Track Order”入口查询包裹，或将单号粘贴至上方查询框。

## XYL-Ships 单号格式：NUDE 与 XLWL 规则

请先在网购店铺的发货邮件或订单历史页面中查找物流单号。XYL-Ships 的跨境小包通常使用 16 位或 17 位字母数字组合，以 `NUDE` 或 `XLWL` 开头，并以 `EX` 或 `EXP` 结尾（以下均为虚构示例）：

| 虚构示例 | 格式说明 |
| --- | --- |
| `NUDE4829173640EX` | `NUDE` 加 10 位数字再加 `EX`。 |
| `XLWL4829173640EX` | `XLWL` 加 10 位数字再加 `EX`。 |
| `XLWL4829173640EXP` | `XLWL` 加 10 位数字再加 `EXP`。 |

由于这些前缀具有很强的辨识度，不会与各国邮政条码冲突。如果店铺给你的实际上是 S10 万国邮联单号（两位字母加 9 位数字加两位国家代码，如 `CN`）或其他快递公司的专属编号，可参阅[快递单号格式对照](guide:tracking-number-formats)（英文）确认归属承运商。

## XYL-Ships 物流状态含义解析

XYL-Ships 的追踪轨迹以英文发布，记录包裹从中国始发分拣中心出发、经干线运输直至交付目的国本地配送商的全过程：

| XYL-Ships 英文状态 | 对你的含义 | Peek 显示阶段 |
| --- | --- | --- |
| `Shipment inbound in sorting center` | XYL-Ships 已在始发分拣中心收货并入库扫描。 | “运输中” |
| `Departed from sorting center`、`Arrived at sorting warehouse`、`Leaving warehouse` | 包裹正在始发地各分拣仓流转，准备交接国际干线。 | “运输中” |
| `Hand over to transportation company`、`Departed from origin transportation center`、`Arrived at destination transportation center` | 已交由国际干线承运商，离开中国并抵达目的国进境转运中心。 | “运输中” |
| `Released and the package is being transferred`、`Package is being transferred locally` | 已放行进入目的国并开始当地国内转运。 | “运输中” |
| `Transfer again, send to the transportation partner department`、`Arrival at transportation partner department, waiting for processing`、`Leaving transportation partner department, sent to the transit hub` | 已转交当地派送合作伙伴的处理部门，并正发往其转运枢纽。 | “运输中” |
| `Arriving at the transit hub, waiting for distribution`、`Leave the transit hub, transport to the destination city`、`Arriving at the destination city, about to distribute to the post office` | 在区域转运枢纽完成分拣并运往你的目的城市，准备移交当地网点。 | “运输中” |
| `The courier has received this parcel for delivery and will set a delivery time shortly` | 当地派送员已接单准备安排派送，但实际投递行程尚未正式开始。 | “运输中” |
| `Parcel currently being delivered` | 当地派送员今天正在派送途中。 | “派送中” |
| `Delivered` | 系统已记录包裹妥投签收。 | “已签收” |

接近尾声时有两条状态容易引起误解。其一是 `Arriving at the destination city, about to distribute to the post office`，它的意思是包裹正在移交当地邮政或合作派送网络，并不代表包裹已经摆在邮局柜台等你自提。其二是 `The courier has received this parcel for delivery and will set a delivery time shortly`，由于派送员尚未发车，该状态仍属于“运输中”，看到 `Parcel currently being delivered` 才意味着当天正在投递。

## XYL-Ships 跨境派送如何运作

在 [xylships.com](https://xylships.com/) 上，XYL-Ships 展示了商业空运（含国内 6 小时和 12 小时加急空运以及 FBA 门到门拼箱）、海运整拼箱、铁路与公路货运，以及跨境电商单件小包服务，并引用了一个单件小包客户案例称 20 天内完成率达 99%。

**当地末端派送。** 正如其轨迹所示，包裹抵达目的国后会转交给当地运输合作伙伴或邮政系统负责最后一公里派送。

**未公开的派送细则。** `xylships.com` 没有公布收件人派送尝试次数、快递柜投递规则、代保管天数、时效保证或理赔时限。包裹进入你所在国家后，是否可放门口、由邻居代收或凭通知单领取的保管天数，完全取决于实际承接派送的本地邮政或快递公司。

**进口关税与海关。** 由于 XYL-Ships 包裹从中国发出，进入欧盟、英国、美国或其他地区时须遵守当地进口增值税和海关规定。对于寄往欧盟的网购包裹，价值不超过 150 欧元的货物每类商品征收 3 欧元关税；此外，无论价值高低，每类商品还有 2 欧元的欧盟处理费。更多清关影响可参阅[包裹被海关扣留怎么办](guide:customs)（英文）。

## XYL-Ships 包裹卡住、长时间无更新或未收到

如果网购订单通过 XYL-Ships 发出，购物店铺才是你的交易合同方，出现延误、退款或补发需求时应优先联系卖家。

**国际转运期间长时间静默。** 在 `Departed from origin transportation center` 与 `Arrived at destination transportation center` 之间出现数天无更新属于常见现象，包裹通常在等待配载航班、清关以及目的国进境入库扫描。详见[为什么快递物流停止更新](guide:tracking-not-updating)（英文）。

**不显示本地转单号。** XYL-Ships 的系统会在全程沿用你的原始 `NUDE` 或 `XLWL` 单号来同步本地合作伙伴的进度，但不会公开单独的本地快递条码。如果派送失败导致你急需获知本地派送商名称或条码，请联系卖家向其货代查询。

**显示 `Delivered` 却找不到包裹。** 请先检查信箱、门廊、大楼前台并询问邻居。确认找不到后，请立即通知卖家并索要妥投凭证，详见[显示已签收但未收到包裹](guide:delivered-not-received)（英文）。

**破损、丢失或退件。** 请保留破损外包装照片并第一时间联系商家，或在买方保护期内发起平台或支付渠道争议。可参阅[包裹破损](guide:damaged-parcel)（英文）、[包裹丢失](guide:lost-parcel)（英文）及[包裹退回寄件人](guide:returned-to-sender)（英文）。

## 联系 XYL-Ships（以及为何应先找卖家）

`xylships.com` 是一个面向企业客户的货运代理官网，面向个人消费者的支持渠道非常有限：

- **电子邮箱**：`fzxyl9527@163.com`（[xylships.com](https://xylships.com/) 页脚列出的唯一联系方式）。
- **客服电话、办公地址与理赔门户**：`xylships.com` 上均未公布。

由于 XYL-Ships 仅与企业发货人建立合作关系，不直接对接终端收件人，涉及丢件、地址更正或退款时请直接联系网购店铺或电商平台，商家才能通过其货运账户推进核查或执行买家保护。

> 若收到陌生短信或邮件，要求你点击链接缴费以“解锁”或“重新安排” XYL-Ships 包裹，请务必高度警惕。任何真实的关税缴费都应直接通过本国邮政官网或海关渠道核实，切勿通过未经证实的短信链接付款。详见[防范快递诈骗短信](guide:delivery-scam-texts)（英文）。

## 使用 Peek 追踪 XYL-Ships 包裹

[Peek](/) 能高置信度自动识别 `NUDE`（`NUDE` + 10 位数字 + `EX`）与 `XLWL`（`XLWL` + 10 位数字 + `EX` 或 `EXP`）两种 XYL-Ships 单号。由于 Peek 不支持解析 XYL-Ships 的网页链接，请直接粘贴纯单号；如需手动指定承运商，可在添加包裹时点击“更换”，或在包裹详情页点击“更换快递公司”，选择 XYL-Ships（搜索“FXYL”同样可以找到）。

Peek 直接读取 XYL-Ships 的 JSON 追踪接口，展示映射后的物流阶段（“运输中”、“派送中”、“已签收”）、XYL-Ships 的英文轨迹原文、接口提供的扫描地点，以及带明确时区偏移的时间戳（会自动换算成你的设备时区显示）。Peek 会自动剔除寄件人与收件人地址及内部客户参考号；此外，Peek 不显示预计送达日期、包裹重量、本地转单号或签收照片。

在打开包裹页面或派送当日开启提醒时，Peek 最高每 10 分钟刷新一次；在 XYL-Ships 官方追踪正常响应时，出现 `Parcel currently being delivered` 当天，按最新扫描所在时区早上 6:00 起，最高每 2 分钟检查一次。其余时间通常按每小时查询；若包裹停留在“已生成面单”或“已揽收”满 12 小时，或连续 2 天无新扫描，打开页面时也保持每小时检查。当由第三方聚合追踪服务兜底查询时，默认每小时检查，无变化满一天后降为每 3 小时一次，派送当天在打开页面或开启通知时可提升至每 15 分钟一次。若最新扫描已过去四天，且包裹仍处于“已生成面单”、“已揽收”或“运输中”，Peek 会标出该扫描的日期，例如“自10月12日周一起没有物流更新”，包裹页面随后显示“包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 XYL-Ships 查询。”；若超过三周仍无任何更新，则显示“已超过三周没有新动态。如果还没收到，请联系卖家。Peek 会持续向 XYL-Ships 查询。”

你可以自由选择“每次扫描”、“重要环节”或“仅派送当天”通知预设。Peek 仅负责状态监控，无法查出未公开的当地派送员信息，也不能替你联系 XYL-Ships。

## 关于 XYL-Ships 物流查询的常见问题

### XYL-Ships（FXYL）是什么物流公司？

XYL-Ships International Logistics 是一家拥有中国民航（CAAC）和 IATA 航空运输销售代理资质的中国跨境货代和电商物流商，主要帮商家把小包从中国运出并交由目的国本地承运商完成派送。

### `NUDE...EX` 和 `XLWL...EX` 单号代表什么？

这两个前缀都是 XYL-Ships 的专用单号格式，由 `NUDE` 或 `XLWL` 加上 10 位数字再加 `EX`（部分 `XLWL` 单号以 `EXP` 结尾）组成。将完整单号粘贴进 Peek 或 [xylships.com](https://xylships.com/) 的 Track Order 查询框即可查看轨迹。

### 为什么轨迹显示 `about to distribute to the post office`？

这表示包裹已经到达你所在的城市，正准备移交当地邮政或末端合作商进入最后派送阶段，并不意味着要你自己去邮局取件。请等待出现 `Parcel currently being delivered` 再预期当天送达。

### 在目的国是谁负责派送 XYL-Ships 包裹？

包裹进入目的国后，XYL-Ships 会将其交给当地运输合作伙伴或邮政网络进行最终投递，但其追踪系统不公布该本地承运商名称，也不返回第二个转单号。如果投递失败，请联系卖家获取当地派送商信息。

:::sources
- [XYL-Ships International Logistics 官网](https://xylships.com/) – CAAC 与 IATA 航空运输销售代理资质、空海铁陆运及跨境电商单件小包业务、Track Order 查询入口与页脚联系邮箱 `fzxyl9527@163.com`
- [Universal Parcel Scraper: xyl-ships](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/xyl-ships) – `NUDE` 与 `XLWL` 格式识别、FXYL 别名、JSON 追踪接口对接、英文轨迹阶段映射、敏感字段过滤及刷新频率规则
:::
