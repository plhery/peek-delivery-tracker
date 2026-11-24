---
title: 纬狮物流（360Lion）单号查询：WSH 与 AR 格式、轨迹与客服
description: 查询纬狮物流（360Lion）中国发往拉美和美国的跨境包裹：WSH 与 AR 单号规则、WSO/WSD 轨迹含义、末端派送交接、清关停滞及官方客服指南。
slug: 360lion-wuliu-chaxun
published: 2026-11-24
updated: 2026-11-24
---

360Lion（*360Lion Express*，中文名*纬狮物流* / *纬狮物联网*）是一家创立于 2015 年、总部位于深圳的中国跨境电商物流服务商，在上海、广州、东莞、义乌和香港设有分支机构与操作中心。公司主营从中国发往拉丁美洲及美国（覆盖墨西哥、智利、秘鲁、巴西、哥伦比亚、厄瓜多尔、阿根廷、乌拉圭、玻利维亚、巴拉圭、萨尔瓦多、哥斯达黎加、危地马拉、委内瑞拉、巴拿马和美国）的空运专线小包、FBA/FBM 头程货运及海外仓业务，服务于 Mercado Libre、Amazon、AliExpress、Shopee、SHEIN、Temu、TikTok Shop、Walmart 和 Falabella 等平台的卖家。你可以在 [360Lion 官网查询入口](https://toms.360lion.com/track) 或 [360lion.com/en/](https://www.360lion.com/en/) 一次查询最多 50 个单号，也可以直接把运单号粘贴到上方查询框中。

## 纬狮物流（360Lion）单号格式：WSH 与 AR

运单号可在电商平台订单详情页或发货确认邮件中找到。360Lion 包裹常见以下两种单号形态（以下均为虚构示例）：

| 单号（虚构） | 归属与说明 |
| --- | --- |
| `WSHMX4829173640YQ` | 360Lion 跨境专线运单号：`WSH` 加两位目的国代码（例如 `MX` 代表墨西哥）、10 位数字并以 `YQ` 结尾。 |
| `AR4829173640` | `AR` 加 10 位数字（用于发往阿根廷的包裹）。该形态与 Colis Privé、Aramex Australia 和 Aramex New Zealand 共享。 |

如果你的订单发往巴西或墨西哥并带有其他承运商的条码——例如[安骏物流（Anjun Logistics）](carrier:anjun)的 `AJ`、J&T Express Brazil 的 `8880` 或 J&T Express Mexico 的 `JMX`——请参阅[运单号格式说明](guide:tracking-number-formats)（英文）。

## 纬狮物流（360Lion）轨迹状态与代码含义

360Lion 用顶层状态（`Processing`、`Pre-alert`、`In transit.`、`Returned`、`Delivered`）对包裹归类，并为每条扫描附上节点代码（中国始发段以 `WSO*` 开头，目的国段以 `WSD*` 开头）。包裹交给目的国本地快递后，追踪流中还会直接透传当地承运商的原始轨迹文本。

| 360Lion 显示（代码 / 分组） | 对你的含义 | Peek 显示 |
| --- | --- | --- |
| `Processing`（`PRECESSING`） | 卖家已在 360Lion 创建发货订单，干线扫描尚未开始。 | “已生成面单” |
| `HUB` 进度节点 | 包裹已进入 360Lion 位于中国的始发分拨中心。 | “已揽收” |
| `departed from airport`（`WSO04`）、`arrived at airport`（`WSD00`）、`In transit.`（`IN_TRANSIT`） | 已从中国机场（如 PVG）起飞并落地目的国机场。 | “运输中” |
| `CUSTOMS` 进度节点 | 正在办理出口或目的国进口清关手续。 | “清关中” |
| `handover to lastmile`（`WSD02`）、`Arrived at sorting center`（`WSD051`）、`package on the way to delivery office`（`WSD050`） | 已移交目的国末端派送伙伴，经分拣后发往当地派送站点。 | “运输中” |
| `Returned`（`RETURNED_BACK`） | 包裹正在退回寄件人或始发仓库。 | “已退回寄件人” |
| `Delivered`（`DELIVERED`） | 已由当地末端合作快递投递签收。 | “已签收” |

需要注意的是，360Lion 的 `Pre-alert`（`WARNING` / *预警*）状态既可能是提前预报，也可能是预警标记，因此 Peek 会依据具体的扫描条目判断阶段，而不会仅凭 `Pre-alert` 就判定包裹异常。

## 纬狮物流（360Lion）跨境派送与清关流程

根据 [360lion.com/en/](https://www.360lion.com/en/) 和 [360lion.com](https://www.360lion.com/) 的介绍，360Lion 定位为跨境干线承运商及航空货运销售总代理（GSSA），运营从中国飞往拉美的定期包机，并在墨西哥、智利、巴西、哥伦比亚和美国设有海外仓。

**末端派送。** 包裹抵达目的国机场（`WSD00`）并完成进口清关后，360Lion 会将其移交（`WSD02`）给收件国本地的邮政或快递合作伙伴负责最终上门投递。

**收件规则与关税。** 由于 360Lion 面向商业发件卖家而非个人零售消费者，其官网并未公布个人收件人的派送尝试次数、自提点保管天数或小包时效承诺（[常见问题与条款页面](https://www.360lion.com/?m=home&c=Lists&a=index&tid=82)主要针对商业卖家的 SEND 空运、禁运品及 30 天卖家索赔时限）。进口增值税和关税依照目的国海关规定执行，详见[包裹被海关扣留怎么办](guide:customs)（英文）。

## 360Lion 包裹停滞、无更新或未收到怎么办

因为与 360Lion 签订运输合同的是网店卖家或平台而非收件买家，遇到延误、地址错误或需要退款时，请务必先联系卖家或购物平台。

**中国至目的国途中多日无更新。** 中国发往拉丁美洲的长途航班及目的国海关排队处理，常使 `departed from airport`（`WSO04`）到 `arrived at airport`（`WSD00`）之间出现数天空白。请参阅[为什么物流停止更新](guide:tracking-not-updating)（英文）。

**移交当地快递但未显示第二单号。** Peek 会通过 360Lion 追踪接口继续跟进末端承运商回传的记录，且不会显示单独的本地运单号。若派送失败需要当地合作方的联系方式或本地单号，请向卖家索取，或致信 360Lion 查件客服邮箱。

**显示已签收、破损或丢失。** 若轨迹显示 `Delivered` 却未收到包裹，请先核查家人、邻居、门卫或大楼前台；如确认丢失或破损，请保留凭证并在平台买家保护期截止前向卖家发起申诉。详见[显示已签收但未收到](guide:delivered-not-received)（英文）、[包裹破损](guide:damaged-parcel)（英文）、[包裹丢失](guide:lost-parcel)（英文）和[包裹退回寄件人](guide:returned-to-sender)（英文）。

## 纬狮物流（360Lion）官方客服与联系方式

以下联系方式公布于 [360Lion 联系我们页面](https://www.360lion.com/?m=home&c=Lists&a=index&tid=79) 和 [360lion.com/en/](https://www.360lion.com/en/)：

- **查件业务邮箱：** `customerservice@360lion.com`。
- **综合与商务邮箱：** `info@360lion.com`。
- **深圳总部电话：** `+86 755 2360 1325`（上海 `+86 21 6142 1360`，广州 `+86 20 8653 4133`）。
- **总部地址：** 中国广东省深圳市龙岗区坂田街道雅宝路 1 号星河 WORLD A 栋 7 层。

> 请警惕要求点击陌生链接紧急缴纳“海关放行费”的虚假短信或 WhatsApp 消息。任何合法税费请先通过购物平台订单页或本国官方邮政、海关网站核实。详见[快递诈骗短信识别指南](guide:delivery-scam-texts)（英文）。

## 使用 Peek 追踪 360Lion 包裹

在 [Peek](/) 输入 `WSH…YQ` 单号（如虚构示例 `WSHMX4829173640YQ`）时，360Lion 是唯一匹配候选，Peek 会自动查询；输入 `AR` 加 10 位数字（如 `AR4829173640`）时，Peek 会同时向 360Lion 以及 Colis Privé、Aramex Australia 和 Aramex New Zealand 核实，并在 360Lion 返回结果时显示“360Lion 有这个包裹的记录。”。直接粘贴 `toms.360lion.com/track?trackNumber=` 链接也会立刻选中 360Lion；如需手动选择，可在添加时点击“更换”或在包裹页点击“更换快递公司”（搜索“360Lion Express”、“WSH”或“纬狮物流”均可找到）。

Peek 直接读取 360Lion 的追踪接口，展示标准中文阶段（“已生成面单”、“已揽收”、“运输中”、“清关中”、“已退回寄件人”或“已签收”）、每条扫描文案（若 360Lion 透传末端快递原文则显示该原文，否则显示 360Lion 英文）、国家名称或机场三字码形式的地点、目的地国家及签收时间。每条扫描均带有当地时区偏移（例如中国为 `+8`、阿根廷为 `-3`），Peek 会自动换算成你设备的本地时间。Peek 不展示网站进度条、始发国、在途天数统计及签收凭证；360Lion 追踪系统本身不提供预计送达日期、服务名称或重量信息。

在打开包裹页面或预计派送当天开启提醒时，只要 360Lion 官网追踪接口正常响应，Peek 最高每 10 分钟查询一次；其余时间每小时查询一次。若停留在“已生成面单”或“已揽收”满 12 小时，或连续两天无新动态，无论你怎么关注都会转为每小时查询一次；停留在“已生成面单”满七天后降为每 6 小时一次。当 360Lion 官网无响应、Peek 仅通过第三方聚合追踪服务跟进时，Peek 每小时查询一次，静默满一天后改为每 3 小时一次，在派送当天打开页面或开启提醒时最高每 15 分钟查询一次。

当最新扫描满四天且包裹仍停留在“已生成面单”、“已揽收”或“运输中”时，Peek 会标出该次扫描日期（如“自10月12日周一起没有物流更新”），并在包裹页提示“包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 360Lion 查询。”，同时提供跳转至 360Lion 联系页面的“向 360Lion 寻求帮助”链接。若超过三周仍无新扫描，则提示“已超过三周没有新动态。如果还没收到，请联系卖家。Peek 会持续向 360Lion 查询。”

你可以自由选择“每次扫描”、“重要环节”或“仅派送当天”通知预设。Peek 仅负责监控物流更新，无法代你清关，也无法代为联系 360Lion 或目的国末端快递。

## 关于纬狮物流（360Lion）追踪的常见问题

### 360Lion 纬狮物流是什么公司？

360Lion（2015 年创立于中国深圳）是一家专注中国至拉丁美洲与美国空运小包专线的跨境电商物流公司，广泛服务于 Mercado Libre、Amazon、AliExpress、Shopee、SHEIN 和 Temu 等平台卖家。

### `WSH…YQ` 和 `AR…` 单号分别代表什么？

`WSH` 加两位国家代码、10 位数字和 `YQ`（例如虚构单号 `WSHMX4829173640YQ`）是 360Lion 的跨境专线单号格式；`AR` 加 10 位数字（如 `AR4829173640`）则用于发往阿根廷的 360Lion 包裹。

### 360Lion 包裹在目的国由谁派送？

360Lion 负责将包裹从中国空运至目的国机场，清关后通过 `handover to lastmile` 移交给当地邮政或合作快递完成上门派送，末端承运商的扫描记录也会同步显示在 360Lion 的追踪动态中。

### 包裹丢失或异常时如何联系 360Lion？

请先联系网店卖家或电商平台，以保障退款与买家保护权益。如需直接向承运商查件，可发邮件至 `customerservice@360lion.com` 或致电深圳总部 `+86 755 2360 1325`。

:::sources
- [360Lion Express 英文官网](https://www.360lion.com/en/) – 2015 年成立、深圳/上海/广州/义乌/东莞/香港网点、50 单批量查询、合作平台与总部地址
- [纬狮物流中文官网](https://www.360lion.com/) – 覆盖 15 个拉美国家及美国的专线小包、包机运力与海外仓布局
- [360Lion 联系我们](https://www.360lion.com/?m=home&c=Lists&a=index&tid=79) – `customerservice@360lion.com`、`info@360lion.com`、深圳/广州/上海电话及办公地址（Peek 帮助链接指向此页）
- [360Lion 关于我们](https://www.360lion.com/?m=home&c=Lists&a=index&tid=78) – 公司简介
- [360Lion 专线小包服务](https://www.360lion.com/?m=home&c=Lists&a=index&tid=90) – 跨境 B2C 小包业务介绍
- [360Lion 常见问题与条款](https://www.360lion.com/?m=home&c=Lists&a=index&tid=82) – 商业卖家承运规则、禁运品与 30 天卖家索赔流程
- [360Lion 运单查询系统](https://toms.360lion.com/track) – 公开运单追踪查询页
- [Universal Parcel Scraper: 360lion](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/360lion) – `WSH` 与 `AR` 格式识别、`toms.360lion.com` 链接规则、`WSO`/`WSD` 代码映射、时区偏移、过滤字段及帮助链接
:::
