---
title: SEKO Logistics 物流查询：单号格式、OmniParcel 与派送
description: 查询 SEKO Logistics 与 SEKO OmniParcel 货运与跨境包裹：了解常规单号与 GX 格式、状态含义、美英客服热线及预约派送。
slug: seko-logistics-tracking
published: 2026-11-26
updated: 2026-11-26
---

SEKO Logistics 始创于 1976 年芝加哥的一间办公室，如今在 60 多个国家设有 150 余个网点及 15 座主要枢纽。其业务涵盖空运、海运和陆运货运、跨境电商、白手套及特种派送（White Glove & Specialty Delivery）以及送货上门与末端派送（Home Delivery & Last Mile）。常规货运可在 [sekologistics.com/en/track](https://www.sekologistics.com/en/track/) 查询，跨境电商包裹则在 SEKO 的 [OmniParcel 追踪页](https://track.omniparcel.com/) 查询。

由于 SEKO 主追踪门户与 OmniParcel 属于两套独立系统，各自采用不同的单号格式和轨迹措辞，分清手里是哪一类单号有助于准确读懂物流时间线。

## SEKO Logistics 与 OmniParcel 追踪单号格式

SEKO Logistics 运单没有单一专属前缀，而是按所属门户分为两大类：

- **SEKO Logistics 货运单号：** Peek 接受 8 至 20 位字母和数字且包含连续至少 7 位数字的编号。常见样式包括 3 位字母加 7 位数字（例如 `WFY4829173`）、5 位字母加 7 位数字，或纯 9 位数字（例如 `482917360`）。Peek 不会仅凭裸单号自动识别为 SEKO：遇到 3 位字母加 7 位数字会先问 [澳大利亚邮政（Australia Post）](carrier:australia-post)，5 位字母加 7 位数字会问 [Colis Privé](carrier:colis-prive)，纯 9 位数字会问 [DPD Ireland](carrier:dpd-ie)（爱尔兰以外仅作兜底）。请手动选择 SEKO Logistics 或直接粘贴追踪链接。
- **SEKO OmniParcel 单号（`GX` 加 17 或 18 位数字）：** `track.omniparcel.com` 上的跨境电商包裹使用 `GX` 加 17 或 18 位数字（例如 `GX482917360514829173`）。SEKO 主追踪页不识别 `GX` 单号，仅 OmniParcel 追踪器返回数据。

如果粘贴 `sekologistics.com/en/track/?trackingNumbers=...` 或 `track.omniparcel.com/...` 的链接，Peek 会自动提取单号并直接归入 SEKO Logistics。欲了解货运单号与包裹单号的差异，请参阅 [追踪单号格式指南](guide:tracking-number-formats)（英文）。

## SEKO Logistics 与 OmniParcel 物流状态含义

SEKO 主追踪页常将节点写成 `Status Changed to [...]`（有时用末尾连字符代替方括号），而 OmniParcel 会详细列出国际枢纽、航班、清关和末端快递说明。

| SEKO / OmniParcel 原文 | 含义说明 |
| --- | --- |
| `Shipment Data Received` / `Shipment Created` / `Tracking number allocated & order ready` | 货运信息已传至 SEKO 或 OmniParcel，尚未实物揽收或入库。 |
| `Arrived at PU location` | 揽收司机已到达寄件地，但尚未登记完成揽收。 |
| `Picked Up From Customer` / `At Pickup Terminal` / `Received at Origin Warehouse` | SEKO 已从寄件方揽收货物，或已登记进入始发站/仓库。 |
| `In Transit` / `In Transit to Delivery Terminal` / `At Transfer Terminal` / `Processed through Export Hub` / `Flight - Freight departed to destination` | 正在国内干线或国际空运途中，发往目的国或派送站。 |
| `At Delivery Terminal` / `At Delivery Terminal Delivery Appt Set` / `On Hand Destination Station` / `Delivered To Warehouse` | 已抵达 SEKO 目的分拨站或当地仓库等待派送；`Delivery Appt Set` 表示送货上门预约已确认。 |
| `Received by Destination Customs` / `Customs held for inspection and clearance` | OmniParcel 跨境包裹正在目的国办理清关或接受查验。 |
| `Customs cleared` / `Received by Last Mile Provider` / `Received by carrier` | 已清关放行并交由目的地本地枢纽或末端派送商处理。 |
| `Shipment is Out for Delivery` / `Driver on site Delivery Location` / `The order is on the way to the recipient.` | 货物今天正由司机外出派送，或司机已抵达收货现场。 |
| `Awaiting collection` / `Customer to collect from carrier` / `Delivered to locker/collection point` | 正在承运商网点、自提点或快递柜等待收件人领取。 |
| `Attempted delivery` / `Attempted Delivery - Refused by customer` | 一次派送尝试未能成功。 |
| `Temporary Origin Hold` / `Temporary Destination Hold` | 货物在始发站或目的站被临时暂扣。 |
| `Shipment Delivered` / `Delivered - Authority to Leave / Safe Drop` | 已送达收件人地址（或按免签授权放置）。 |
| `Return to sender` | 正在退回寄件人。 |

SEKO 主门户上有两种写法值得特别留意：

- **`Delivered To Warehouse`** 表示货物到达了 SEKO 目的地仓库而非你家，尚未真正送达。
- **`POD Image Attached` 与 `POD IMAGED`** 仅记录签收凭证扫描归档（有时在送达数日后出现），而 **`Return Data Received`** 仅说明已登记退件指令、并不代表货物已启程退回；它们都不会改变实际的物流阶段。

## 白手套送货上门、跨境清关与延误

SEKO 官网在其服务列表中同时涵盖白手套与特种派送、送货上门与末端派送、电子及硬件安全物流以及 OmniParcel 跨境订单。

- **送货上门预约：** `At Delivery Terminal Delivery Appt Set` 和 `On Hand DEST Delv Appt Set` 表示货物在目的站点待派且已敲定派送预约。在途期间 SEKO 页面通常会显示 `Expected Arrival` 预计到达日期或 `Estimated delivery between` 日期区间。
- **跨境 OmniParcel 交接：** 国际电商订单（`GX...`）的轨迹会从始发仓依次经过出口处理、空运航班与目的国清关，最终移交当地末端承运商（`Received by Last Mile Provider`）。航班飞行与清关期间轨迹常暂停更新，可参阅 [清关指南](guide:customs)（英文） 和 [物流停止更新怎么办](guide:tracking-not-updating)（英文）。
- **时间戳与重复记录：** SEKO 主页时间精确到分钟但不带时区，有时还会在同一分钟内以不同大小写重复列出同一状态；Peek 会去重保留一条，并将不带地点的 SEKO 节点按芝加哥时间解析。OmniParcel 页面默认按 UTC+10:00 显示（除非手动切换时区下拉框），Peek 直接以 UTC 向 OmniParcel 查询，因此每条扫描保持精确时刻，并全部换算为你设备的本地时钟展示。

如果同一跨境订单在另一段行程中使用了其他大型国际快递，可对照查看 [DHL Express](carrier:dhl-express) 或 [FedEx](carrier:fedex)。

## 使用 Peek 追踪 SEKO Logistics

由于 SEKO 单号与其他承运商重叠且没有统一前缀，有完整链接时请优先粘贴 `sekologistics.com/en/track/?trackingNumbers=...` 或 `track.omniparcel.com/...`。若只粘贴裸单号（无论是 `WFY4829173`、`482917360` 还是 OmniParcel `GX...`），请在添加时点击**更改**或在追踪页点击**更换承运商**手动选择 SEKO Logistics。

Peek 通过开源的 [universal-parcel-scraper SEKO 适配器](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/seko) 直接查询两套门户：`GX` 加 17–18 位数字自动走 OmniParcel（以 UTC 查询，读取扫描地点、服务名称、公斤重量及目的国）；其余 SEKO 货运单号走 SEKO 主追踪页（读取预计到达日或送达日期区间、服务名称、目的国及去重后的节点，同时过滤 `POD Image Attached` / `POD IMAGED` 归档条目并舍去签收人姓名与邮编）。

Peek 将 SEKO 与 OmniParcel 节点统一归类为：**已创建运单**（数据接收、创建订单、司机到达取货点）、**承运商已揽收**（已揽收及始发仓入库）、**运输中**（枢纽、航班、中转、目的站、入仓、清关放行与末端交接）、**清关中**（目的国海关查验）、**派送中**（外出派送及司机到达现场）、**待取件**（网点、自提点或快递柜待领）、**派送未成功**（投递失败）、**需关注**（始发站或目的站暂扣）、**已送达**（送达与免签放门）以及**已退回寄件人**（退件）。

当你在 Peek 中打开包裹，或在开启通知的情况下进入派送日时，只要 SEKO 追踪器正常响应，Peek 最高每 10 分钟检查一次；在 SEKO 主页显示 `Shipment is Out for Delivery`、`Out for Delivery` 或 `Driver on site Delivery Location`，或 OmniParcel 显示 `Out for delivery` 或 `The order is on the way to the recipient.` 当天，自最新扫描所在时钟早上 6 点起每 2 分钟检查一次。其余时间每小时检查一次；在“已创建运单”或“承运商已揽收”下满 12 小时或连续两天无新动态时改为每小时一次，在“已创建运单”下满七天改为每 6 小时一次。当 SEKO 追踪页无响应而改由综合追踪服务跟进时，Peek 每小时检查一次，静默满一天后每 3 小时一次，派送当天最高每 15 分钟一次。

若包裹处于“已创建运单”、“承运商已揽收”或“运输中”且最新扫描已满四天，Peek 会按该扫描日期标示（如“自 10 月 12 日（周一）起无物流更新”），并提示“包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 SEKO Logistics 查询。”；超过三周无新动态时会建议联系卖家，同时继续查询。

你可以在 Peek 通知设置中选择**每次扫描**、**重要节点**或**仅派送和送达**。

## 联系 SEKO Logistics

SEKO 在其 [追踪页面](https://www.sekologistics.com/en/track/) 与 [联系我们页面](https://www.sekologistics.com/en/contact-us/) 上公布了地区客服热线：

- **美国送货上门热线：** (866) 268-5263
- **英国客户服务：** +44 1784 417120
- **官方在线追踪：** [SEKO Track My Shipment](https://www.sekologistics.com/en/track/)（一次最多查 30 个单号）与 [OmniParcel Tracker](https://track.omniparcel.com/)
- **在线运单咨询：** [联系 SEKO Logistics](https://www.sekologistics.com/en/contact-us/)（选择 “Track My Shipment” 表单）
- **公司概况：** [关于 SEKO Logistics](https://www.sekologistics.com/en/about/) 与 [sekologistics.com](https://www.sekologistics.com/en/)

致电或填写 “Track My Shipment” 表单时请准备好你的 SEKO 单号。

## 常见问题

### 为什么我的 `GX` 追踪单号在 `sekologistics.com/en/track/` 上查不到？

以 `GX` 开头后接 17 或 18 位数字的单号属于 SEKO 的 OmniParcel 平台，仅能在 [track.omniparcel.com](https://track.omniparcel.com/) 查询。在 Peek 中选择 SEKO Logistics 后，系统会自动将 `GX` 单号发往 OmniParcel、将常规单号发往 SEKO 主追踪页。

### “Delivered To Warehouse” 是不是表示包裹送错了地址？

不是。在 SEKO Logistics 轨迹中，`Delivered To Warehouse` 表示货物已抵达 SEKO 目的地仓库准备安排末端派送，并非误投。请等待 `Out for delivery` 与 `Shipment Delivered` 来确认最终送达。

### 为什么 Peek 要我为 SEKO 单号手动选择承运商？

SEKO 单号（如 3 位字母加 7 位数字或纯 9 位数字）与其他承运商重叠，且 Peek 不会仅凭裸单号自动识别 SEKO（包括 `GX` 单号）。通过**更改**选择 SEKO Logistics，或直接粘贴官方 SEKO / OmniParcel 追踪链接即可准确查询。

### 如何预约 SEKO Logistics 的送货上门时间？

可拨打美国送货上门热线 `(866) 268-5263` 或英国客服 `+44 1784 417120`，也可在 [sekologistics.com/en/contact-us](https://www.sekologistics.com/en/contact-us/) 提交 “Track My Shipment” 表单。

:::sources
- [SEKO Logistics](https://www.sekologistics.com/en/)
- [SEKO Logistics — Track My Shipment](https://www.sekologistics.com/en/track/)
- [SEKO Logistics — About](https://www.sekologistics.com/en/about/)
- [SEKO Logistics — Contact Us](https://www.sekologistics.com/en/contact-us/)
- [SEKO OmniParcel Tracker](https://track.omniparcel.com/)
- [universal-parcel-scraper SEKO adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/seko)
:::
