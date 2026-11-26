---
title: Shipter 物流查询：韩国 H3 出口单号与追踪状态说明
description: 查询韩国 Shipter（H3 Networks）国际空运包裹：了解 H3…KR 单号格式、各国末端交接、英文物流状态含义以及追踪链接用法。
slug: shipter-tracking
published: 2026-11-26
updated: 2026-11-26
---

Shipter 由韩国 **H3 NETWORKS Co., Ltd.** 运营，依托 40 余条物流渠道及每周连接 25 座以上机场的航空网络，将 B2B 与 B2C 包裹从韩国寄往超过 **100 个国家**（[shipter.kr](https://www.shipter.kr/)）。其 B2B 服务涵盖运输、清关与仓储，B2C 服务涵盖海外运输、清关与末端派送。它是亚马逊韩国官方 SPN（服务商网络）物流伙伴，同时在 eBay、Kogan 和 Allegro 注册为承运商，追踪数据已接入 AfterShip 与 17TRACK，并承接网红寄样（influencer seeding）物流。

你可以在 [shipter.kr/tracking](https://www.shipter.kr/tracking) 上查询包裹，或直接把 `H3...KR` 单号粘贴到上方输入框。

## Shipter 追踪单号格式

Shipter 追踪单号均以 `H3` 开头、以 `KR` 结尾，中间为 2 位字母加 5 或 6 位数字：

| 格式（虚构示例） | 说明 |
| --- | --- |
| `H3AU482917KR` | 12 位字符：`H3`、2 位字母、6 位数字加 `KR`。Peek 会查询 [Colis Privé](carrier:colis-prive) 与 Shipter（在韩国优先查询 Shipter）。 |
| `H3FR48291KR` | 11 位字符：`H3`、2 位字母、5 位数字加 `KR`。Peek 会直接单独查询 Shipter。 |

直接粘贴 `shipter.kr/tracking/<单号>` 或 `shiptertracking.com/trackingResult/<单号>` 链接会立即归入 Shipter。如果包裹由韩国邮政寄出，请参阅 [韩国邮政（Korea Post）](carrier:korea-post)；其他单号格式见 [追踪单号格式指南](guide:tracking-number-formats)（英文）。

## Shipter 物流状态含义解析

Shipter 追踪时间线以英文整合了韩国本土出口与国际空运节点，以及目的国当地末端快递回传的扫描记录。

| Shipter / 合作方扫描原文 | 含义说明 |
| --- | --- |
| `The shipment information has been received by shipter, but the package has not been picked up yet.` | 已收到韩国卖家的预报信息，但 Shipter 尚未扫描到包裹实物。 |
| `The parcel has been scanned by shipter and ready to be shipped.` | Shipter 已在韩国设施收货扫描，准备出运。 |
| `The parcel has been shipped.` / `The flight has been departed.` / `Depart origin airport` | 已装机离开韩国始发机场。 |
| `Arrived at destination airport` / `Collected from airport terminal` | 航班已抵达目的国机场，货物已从机场货站提走。 |
| `Import customs cleared` | 目的国进口海关已放行，进入本地派送流程。 |
| `Arrived at destination` / `In transit` | 已进入目的国分拣流程，等待当地快递扫描。 |
| `Parcel received in processing centre (...)` / `Parcel depart from ... depot` / `The item is ready for shipment...` | 正在目的国当地承运商网络中分拣与转运。 |
| `Your parcel has been received at ... depot..` / `Your parcel is currently in transit to ...` | 由目的国末端合作方回传的站点及在途记录（附站点与城镇名）。 |
| `Out for delivery` / `Your parcel is with your local courier and ready for delivery..` | 包裹今天已随当地派送员外出派送。 |
| `Your parcel was delivered` / `Delivered.` | 已送达收件人地址。 |

有两条由末端合作方回传的预报提示——`Your parcel will be entrusted to last mile .it is being prepared by your sender` 与 `Shipping information received`——可能出现在 Shipter 韩国揽收之后，这并不代表包裹进度倒退。

## 国际空运、清关与目的国交接

Shipter 负责将包裹从韩国空运出境，并在目的国移交当地快递公司：

- **航班与机场环节：** 出现 `Depart origin airport` 后，落地会显示 `Arrived at destination airport`，提离机场货站显示 `Collected from airport terminal`，进口清关放行显示 `Import customs cleared`。可参阅 [清关指南](guide:customs)（英文） 和 [物流停止更新怎么办](guide:tracking-not-updating)（英文）。
- **当地末端派送：** 由目的国合作快递负责投递，Shipter 会把该合作方的网点、`Out for delivery` 与送达扫描同步回传到同一条时间线中。
- **无预计送达日期：** Shipter 公开追踪接口不提供预计送达日期，请通过关注 `Import customs cleared`、本地站点签收和 `Out for delivery` 来判断派送节奏。

## 使用 Peek 追踪 Shipter

将 `H3...KR` 单号，或 `shipter.kr/tracking/...`、`shiptertracking.com/trackingResult/...` 链接直接粘贴到 Peek 中。对于 12 位单号，Peek 会询问 [Colis Privé](carrier:colis-prive) 和 Shipter（韩国优先 Shipter）；对于 11 位单号则仅问 Shipter，一旦返回结果即确认包裹由 Shipter 承运。若 Peek 识别为其他承运商，可在添加时点“更改”或在详情页点“更换承运商”改选 Shipter。

Peek 通过开源的 [universal-parcel-scraper Shipter 适配器](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/shipter) 读取 Shipter 轨迹，遇到网络波动会自动重试。界面会显示每条英文扫描原文、站点或地点名称以及送达时间；Shipter 附带 GMT 时区偏移的记录会换算为你设备的本地时间，无偏移的则按原时间展示。

Peek 将 Shipter 节点映射为清晰阶段：**已创建运单**（揽收前预报）、**承运商已揽收**（Shipter 在韩国完成收货扫描）、**运输中**（航班起飞、机场抵达、清关放行及当地快递枢纽转运）、**派送中**（当地快递开始派送）和**已送达**（妥投）。滞后回传的末端预报提示（`Shipping information received` 与 `Your parcel will be entrusted to last mile...`）会保留在时间线中，但不会让状态回退。

若包裹处于“已创建运单”、“承运商已揽收”或“运输中”且最新扫描满四天，Peek 会按该扫描日期标示（如“自 10 月 12 日（周一）起无物流更新”），并提示“包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 Shipter 查询。”；超过三周无更新时会建议联系卖家，同时继续查询。

当你打开包裹，或在开启通知的情况下进入派送日时，只要 Shipter 追踪接口正常响应，Peek 最高每 10 分钟检查一次；在 Shipter 出现 `Out for delivery` 或 `Your parcel is with your local courier and ready for delivery..` 当天，自最新扫描所在时钟早上 6 点起每 2 分钟检查一次。其余时间每小时检查一次；在“已创建运单”或“承运商已揽收”下满 12 小时或连续两天无新动态时改为每小时一次，在“已创建运单”下满七天改为每 6 小时一次。当 Shipter 接口无响应而改由综合追踪服务跟进时，Peek 每小时检查一次，静默满一天后每 3 小时一次，派送当天最高每 15 分钟一次。通知支持“每次扫描”、“重要节点”或“仅派送和送达”。

## 联系 Shipter

Shipter 主要直接对接发货企业客户：

- **官网与业务咨询：** [shipter.kr](https://www.shipter.kr/)（H3 NETWORKS Co., Ltd.；服务咨询入口 *이용문의*）
- **官方在线追踪：** [shipter.kr/tracking](https://www.shipter.kr/tracking)
- **先联系卖家：** Shipter 为每家合作企业配备专属客服经理，异常、退货和理赔均通过企业客户处理。若包裹延误、破损或丢失，请携带 `H3...KR` 单号联系发货商家。

## 常见问题

### Shipter 追踪单号是什么格式？

以 `H3` 开头、`KR` 结尾，中间为 2 位字母加 5 或 6 位数字，总长 11 或 12 位（如虚构示例 `H3AU482917KR`）。Shipter 官网未说明中间两位字母的含义。

### 为什么包裹已发出好几天后才出现 “Shipping information received”？

这是目的国末端合作快递回传的预报信息，晚于 Shipter 在韩国的揽收扫描。这并不表示包裹进度倒退，Peek 会维持前一条扫描所处的阶段。

### 为什么 Shipter 轨迹里会出现其他快递公司的更新？

Shipter 负责韩国出口与国际空运，随后在目的国交给当地末端快递派送，并把该合作方的站点和投递扫描直接汇总展示在 Shipter 时间线里。

### Shipter 会提供预计送达日期吗？

不会。Shipter 公开追踪接口仅提供带 GMT 时区偏移的出口、航班、清关及当地快递节点，不包含预计送达日期。

:::sources
- [Shipter (H3 NETWORKS Co., Ltd.)](https://www.shipter.kr/)
- [Shipter Tracking](https://www.shipter.kr/tracking)
- [universal-parcel-scraper Shipter adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/shipter)
:::
