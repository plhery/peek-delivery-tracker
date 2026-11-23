---
title: 京东物流（JD Logistics）国际查询：单号、状态与客服
description: 查询京东物流（JD Logistics/JoyLogistics）国内外与海外仓包裹：JD与VG单号格式、状态含义、美国GOFO衔接与各地客服。
slug: jingdong-wuliu-jd-logistics-chaxun
published: 2026-11-23
updated: 2026-11-23
---

京东物流（JD Logistics，港交所股票代码 `2618`）是京东集团旗下的综合供应链物流企业，2007 年起自建全国物流网络、2017 年独立运营、2021 年 5 月在香港联交所上市。在海外市场，京东物流以 JoyLogistics 和 JoyExpress 品牌开展业务，既从美国、英国、澳大利亚等地的海外仓发货，也为跨境卖家承运中国直邮国际小包。国际件可在 [京东物流国际追踪页](https://www.jingdonglogistics.com/Tracking) 查询，中国国内件可在 `jdl.com/orderSearch` 查询，也可以直接把单号粘贴到上方查询框。单号由发货网店提供。

## 京东物流单号格式：JD 与 VG 号段区分

Peek 识别的京东物流常见单号及易混格式如下（数字均为虚构）：

| 单号（虚构） | 格式与归属说明 |
| --- | --- |
| `JD4829105736491` | `JD` 加 13 位数字（共 15 位）：京东物流运单号。 |
| `JDA482910573649` | `JD` + 1 位字母 + 12 位数字：京东物流运单号。 |
| `JDAP48291057364` | `JD` + 2 位字母 + 11 位数字：京东物流运单号。 |
| `VG48291057364` | `VG` 加 11 位数字：同样用于京东物流，但法国邮政（La Poste）也使用此格式，Peek 不会直接锁定为京东（见下文）。 |
| `JD482910573649182736` | `JD` 加 18 位数字：不是京东物流，这是德国 DHL Paket 的单号格式。 |

在 Peek 中粘贴单号时，字母大小写、空格和连字符均不影响识别。[京东物流国际追踪页](https://www.jingdonglogistics.com/Tracking) 单次支持最多 100 个空格分隔的单号；中国境内段（如寄往货代仓的国内件）可在 `jdl.com/orderSearch` 的「运单查询」输入框里输入多个单号查询。找不到单号请参阅 [如何找到你的快递单号](guide:find-tracking-number) 和 [快递单号格式指南](guide:tracking-number-formats)。

## 京东物流国际与国内状态是什么意思

海外仓直发包裹的英文节点通常按以下顺序出现：

| 京东国际原文 | Peek 显示阶段 | 对你的实际含义 |
| --- | --- | --- |
| `Picking Completed.` | 「已揽收」 | 京东海外仓已完成商品拣货。 |
| `Weighed at Warehouse.` | 「已揽收」 | 包裹在京东海外仓完成出库称重。 |
| `Departed from Warehouse.` | 「运输中」 | 包裹已离开京东海外仓。 |
| `Handed over to Carrier` | 「运输中」 | 京东已将包裹移交给目的国本地尾程快递。 |
| `Shipping Label Created` | 保持原阶段不变 | 转发自尾程快递的电子面单记录，不改变包裹阶段。 |
| `Arrived at GOFO Regional Hub` | 「运输中」 | 美国专线中，尾程快递 GOFO Express 已在其区域枢纽扫入。 |

**中国直发海外包裹的英文节点。** 从中国发出的跨境小包顺序为：`Shipment Picked up`、`Arrived at Warehouse`、`Weighed at Warehouse`、`Consolidation Completed`（集包拼箱等待登机，并非签收，均属「已揽收」）；随后是 `Departed from Warehouse.`、`Arrive at airport`、`Customs released from origin port`（中国出口清关放行）、`Departure from origin port`、`Arrive at destination port`、`Cleanrance agency picked`（目的国清关代理提货，原系统拼写如此）与 `Handover to local carrier`（均属「运输中」）。美国线随后在京东代码下同步 GOFO 的 `Arrived at GOFO Regional Hub`、`In Transit to Next Facility`、`Loaded on Vehicle`、`Arrived at GOFO Delivery Station`（「运输中」）与 `Out for Delivery`（「派送中」）。

**尾程转单号与京东国内中文节点。** 中国直发件的 `Departed from Warehouse.` 末尾常附带「Tracking no」和当地尾程单号（如美国 `GFUS` 开头的 GOFO 单号）；Peek 会清理行尾冗长文字，并自动拿该尾程单号去查询对应尾程快递，待其出现更新扫描后自动接力切换。京东国际还用状态码区分建单（「已生成面单」）、开始清关（「清关中」）、清关完成/中转卸装（「运输中」）、派送（「派送中」，Peek 会隐去快递员姓名和电话）与签收（「已签收」）。中国国内查件页则显示中文：`京东快件已取件`、`已到达【…】`、`正在派送中` 与 `已送达`。

## 京东物流在欧美澳的海外仓、直邮与清关规则

- **中国跨境小包：** 据 [京东国际跨境小包页面](https://www.jingdonglogistics.com/ser/CrossExport)，服务覆盖美国、英国、澳大利亚、加拿大、墨西哥和俄罗斯，提供标准、特快和经济选项，空运出境后由当地合作伙伴完成尾程（官网称平均 3–6 个工作日；美国线常见 GOFO Express）。
- **海外仓本地发货：** 京东在美国（新泽西、洛杉矶、亚特兰大）、英国及悉尼等地运营海外仓（截至 2026 年 6 月 30 日全球自有仓超 1,600 个、第三方合作仓超 2,000 个，见 [关于京东物流](https://www.jingdonglogistics.com/about)）。海外仓现货发出的订单首条通常是 `Picking Completed.`，而非中国直发的 `Shipment Picked up`。
- **派送规则与关税：** 派送次数与自提点由当地尾程快递决定（见 [错过派送怎么办](guide:missed-delivery)）。按 [京东物流服务细则](https://www.jdl.com/serviceDetails)，中国发往海外的国际快递运费不含目的国关税，税费可能由寄件人或收件人承担，下单前请向卖家确认是否包税。详见 [包裹海关扣留与清关指南](guide:customs)。

## 京东物流不更新、延误、丢件或退件怎么办

- **暂时查不到或几天没更新。** 如果京东后台暂无记录，请向卖家核对单号；若连续多日无更新，可能处于航班中转、清关或当地快递未回传阶段。超过四天无新扫描时，Peek 会提示联系卖家或通过官方联系页咨询。参见 [物流为什么停止更新](guide:tracking-not-updating) 和 [中国直邮包裹跟踪指南](guide:tracking-from-china)。
- **延误、丢失与退件。** 依据 [京东物流服务细则](https://www.jdl.com/serviceDetails)，国际件运单上的预计到达时间仅供参考，延误不退运费也不赔付；且包裹离开中国大陆后京东不提供国际退回服务。因此延误、丢件、破损、已签收未收到或需退货时，请第一时间找发货网店处理。参见 [包裹丢失怎么办](guide:lost-parcel)、[包裹退回寄件人](guide:returned-to-sender) 和 [显示已签收但未收到](guide:delivered-not-received)。

## 京东物流国内外客服电话与邮箱

根据 [京东物流国际联系页面](https://www.jingdonglogistics.com/contact) 与 [JDL 联系我们](https://www.jdl.com/en/contactUs)（节假日休息，未注明时区，加拿大与爱尔兰未单列）：

- **美国**：`org.cs.us1@jd.com`，周一至周五 `08:00–17:00`。
- **英国及欧洲（德、荷、法）**：`org.cs.eu1@jd.com`，周一至周五 `09:00–18:00`。
- **澳大利亚**：电话 `+61 (02) 87239755`、邮箱 `org.cs.intl1@jd.com`，周一至周五 `09:00–18:00`。
- **中国大陆**：全国热线 `950616`（海外拨打 `0086 950616`），官网另设「Online Consultation」在线客服。

## 用 Peek 跟踪京东物流包裹

粘贴上述 `JD` 开头号段（如虚构单号 `JD4829105736491`、`JDA482910573649`、`JDAP48291057364`）或含 `waybillCodes=` 的 `jingdonglogistics.com` 链接时，[Peek](/) 会立刻自动识别京东物流并提示「JD Logistics 有这个包裹的记录。」。`VG` 加 11 位数字因与法国邮政重合，Peek 会先询问法国邮政等服务，未找到时可点添加页的「更换」或详情页的「更换快递公司」，搜索 `JD Logistics`、`Jingdong` 或「京东物流」手动指定；详情页亦提供「向 JD Logistics 寻求帮助」链接直达官方联系页。

对于国内单号号段（`JD0…` 共 15 位、`JDJ/JDK/JDV/JDX` + 12 位、`JDVA/JDVB/JDVC/JDAZ` + 11 位），Peek 直接请求 `jdl.com` 中文查件接口（其余号段优先查国际版，查不到再查国内版）。注意 `jdl.com` 国内接口有时会触发无法自动通过的反爬校验，遇此情况 Peek 会暂停该单查询 6 小时至一天，国内件若暂时空白可直接去 `jdl.com` 核对。Peek 展示规范化阶段（「已生成面单」「已揽收」「运输中」「清关中」「派送中」「已签收」等）、城市/国家地点及按当地时钟换算的设备本地时间，隐去收件人隐私、派送照、快递员姓名电话及行尾冗长转单号，不显示预计送达日期。

页面打开或派送当天开启通知时，Peek 最快每 10 分钟检查一次，进入派送当天 06:00 起最快每 2 分钟检查一次（需启用中文页浏览器抓取时每 5 分钟一次，第三方服务每 15 分钟一次；若已自动接力给美国 GOFO，则按 GOFO 频率最多每 30 分钟一次）；平时及两天无新扫描（或前段 12 小时无变化）降为每小时一次。静默满四天时 Peek 会提示「包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 JD Logistics 查询。」，超过三周无动静则提示「已超过三周没有新动态。如果还没收到，请联系卖家。Peek 会持续向 JD Logistics 查询。」。

通知偏好可选「每次扫描」「重要环节」或「仅派送当天」。Peek 无法替你联系京东物流或卖家。

## 关于京东物流国际查询的常见问题

### 京东物流（JD Logistics）是正规公司吗？

是的。京东 2007 年起自建物流，2017 年成立京东物流集团，2021 年 5 月在香港联交所主板上市（代码 `2618`），在全球 26 个市场运营超 1,600 个自营仓与 2,000 多个第三方合作仓。

### 在哪里可以查询京东物流包裹？

国际跨境与海外仓件可在 [jingdonglogistics.com/Tracking](https://www.jingdonglogistics.com/Tracking) 查询，中国国内件可在 `jdl.com/orderSearch` 查询，或直接粘贴单号到上方 Peek 查询框。

### 京东物流发往美国的包裹由哪家快递派送？

京东官网不统一公开合作尾程名，但美国历史轨迹常见由 GOFO Express 派送（出现 `Arrived at GOFO Regional Hub` 等节点）；只要出库行带出 GOFO 的 `GFUS` 单号，Peek 也会自动接力跟踪 GOFO。

### 为什么京东物流轨迹一直不更新？

常见原因包括京东尚未扫描收货、处于国际航班或海关清关阶段、尾程尚未回传，或中国国内件在 `jdl.com` 触发了安全校验。超 4 天无新动态请联系卖家或对应地区的京东客服。

### 怎么联系京东物流客服？

海外件可通过 [京东物流国际联系页面](https://www.jingdonglogistics.com/contact) 按国家发邮件（美 `org.cs.us1@jd.com`、英欧 `org.cs.eu1@jd.com`、澳 `+61 (02) 87239755`）；中国大陆可拨打 `950616` 或使用 `jdl.com` 在线客服。订单问题建议先问网店。

:::sources
- [JD Logistics：国际运单追踪](https://www.jingdonglogistics.com/Tracking) – 英文国际查件页、单次 100 单查询
- [JD Logistics：关于京东物流](https://www.jingdonglogistics.com/about) – 港交所 2618、JoyLogistics/JoyExpress 品牌与全球仓库规模
- [JD Logistics：联系我们](https://www.jingdonglogistics.com/contact) – 美、英欧、澳客服邮箱与电话及工作时间
- [JD Logistics：跨境小包服务](https://www.jingdonglogistics.com/ser/CrossExport) – 覆盖六国、空运尾程与 3–6 个工作日参考时效
- [京东物流：服务细则](https://www.jdl.com/serviceDetails) – 国际快递关税责任、时效仅供参考及出境后不提供退回
- [JDL：Contact Us](https://www.jdl.com/en/contactUs) – 全国热线 950616 与在线咨询入口
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – JD/VG 号段规则、中英文及国内/国际双查件接口、GOFO 尾程转单号自动接力、隐私过滤与刷新频率
:::
