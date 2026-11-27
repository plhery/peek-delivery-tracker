---
title: DHL Freight 物流查询：ActiveTracing 单号、状态与预约
description: 通过 ActiveTracing 查询 DHL Freight 欧洲公路与铁路货运：JND 编码、订单号、托运号、SSCC、派送预约与客服。
slug: dhl-freight-tracking
published: 2026-11-26
updated: 2026-11-26
---

DHL Freight（*DHL Freight GmbH*，隶属 DHL 集团）是 DHL 的欧洲公路与铁路货运事业部，在超过 40 个国家设有子公司，年货运量超过 4,500 万吨，覆盖零担（LTL）、拼箱（PTL）、整车（FTL）、温控、高安保及欧洲铁路货运网络。除了企业托盘货（如 *EuroConnect*），DHL Freight 还负责大件住宅订单（*DHL Home Delivery*）以及瑞典境内 *DHL Service Point* 托运件。你可以在 [DHL ActiveTracing](https://activetracing.dhl.com/DatPublic/datSelection.do) 查询货件，或直接将参考号粘贴到上方输入框。

## DHL Freight 运单号：订单号、托运号与 JND 标签号

DHL ActiveTracing 支持多种参考编号，并将货件数据在线保留 **6 个月**：

| 格式（虚构） | 含义 |
| --- | --- |
| `JND48291736405829173` | 20 位 **DHL 包裹编号 / 标签号（License Plate）**（`JND` + 17 位数字）。 |
| `FRA-EC-4829173` | **DHL 订单号（Order code）**：3 位货运站代码 + 2 位产品代码（如 `EC` 代表 EuroConnect）+ 7 位数字（可带或不带连字符）。 |
| `4829173640` | **托运号（Consignment ID）**：9、10 或 11 位数字（10 位数字常见于瑞典国内 *DHL Service Point* 货件）。与 [DHL](carrier:dhl)、[Mondial Relay](carrier:mondial-relay)、[Relais Colis](carrier:relais-colis) 和 [Packeta](carrier:packeta) 共用 10 位长度。 |
| `00348291736405829177` | 以 `00` 开头、带有效 GS1 校验位的 20 位 **GS1 SSCC** 包装箱编号。与 [USPS](carrier:usps) 和 [NZ Post](carrier:nz-post) 共用 20 位 SSCC 形态。 |

注意：德国标准包裹由 [DHL](carrier:dhl)（*DHL Paket*）承运，国际空运文件/包裹由 [DHL Express](carrier:dhl-express) 承运；托盘、大件家具及欧洲公铁联运则通过 DHL Freight 的 ActiveTracing 查询。另见[运单号格式说明](guide:tracking-number-formats)（英文）。

## DHL Freight ActiveTracing 物流状态含义

ActiveTracing 为每条记录提供英文或德文状态句、货运站名称（例如 `DE-KOBLENZ`）以及该货运站的当地时间（不带时区）：

| DHL Freight 显示（英 / 德） | 对你的含义 | Peek 显示 |
| --- | --- | --- |
| `Consignment created`、`Collection arranged - OK`、`Out for Collection`、`Not Collected - ...` / `Auftrag erfasst`、`In Abholung` | 已录入货运订单或已派车提货，货物仍在发货方处。 | “已生成面单” |
| `Collected`、`Shipment handover by sender` / `Abholung` | 已从发货方处揽收，或已交寄至瑞典 DHL Service Point。 | “已揽收” |
| `Received at terminal`、`Shipped from terminal`、`Arrival at destination`、`Loaded into ferry`、`Direct delivery`、`Receiver notified`、`Shipment in warehouse - Awaiting delivery appointment` / `Delivery appointment booked` / `Empfangen im Terminal`、`Verladen`、`Avisierung` | 正在 DHL Freight 各货运站转运、乘渡轮过境、已完成清关（`Customs cleared`），或在目的地货运站暂存等待约妥派送时段。 | “运输中” |
| `On hold - in customs clearance` | 跨境清关滞留查验（例如进出瑞士、英国或挪威）。 | “清关中” |
| `Out for Delivery` / `In Zustellung` | 已装上当地送货卡车，安排今天派送。 | “派送中” |
| `Shipment in warehouse - ready for self collection by consignee` | 已在当地 DHL Freight 货运站备妥，等待收货人自提。 | “待取件” |
| `Attempted DHL delivery - ...` | 尝试派送但未能完成（例如收货方歇业或拒收）。 | “派送未成功” |
| `Shipment in warehouse - waiting for consignee payment`、`Shipment delivered with exception - partly delivered`、`Exception occurred - Strike`、`New shipping instruction - consignment cancelled by sender` | 等待收货人付款、部分缺件交付、罢工中断或发货人取消托运。 | “需要留意” |
| `New shipping instruction - shipment returned` | 根据新指令将货物退回发货人。 | “已退回寄件人” |
| `Delivered`、`Received by consignee`、`Picked up by receiver`、`Shipment delivered with exception` / `Zugestellt` | 已交付给收货人，或已在货运站 / Service Point 自提。 | “已签收” |

## 派送预约（*Avisierung*）、清关与货运规则

根据 [DHL Freight 德国网站](https://www.dhl.com/de-en/home/freight.html)及 [DHL Freight 帮助中心](https://www.dhl.com/de-en/home/freight/help-center-for-european-road-and-rail.html)：

- **派送预约（*Avisierung*）：** 货件在抵达末端货运站后可能停留在 `Shipment in warehouse - Awaiting delivery appointment`，直至与收货人敲定送货日期；约妥后即更新为 `Delivery appointment booked`。若长时间等待，请联系发货人或末端货运站。
- **ActiveTracing 保留 6 个月：** [activetracing.dhl.com](https://activetracing.dhl.com/DatPublic/datSelection.do) 支持英语、德语、西班牙语、法语、瑞典语、波兰语和意大利语，在线保存最近 **6 个月**的货运历史。
- **欧洲跨境清关：** 往来欧盟与非欧盟邻国（英国、瑞士、挪威等）的公路货运均须办理清关（`On hold - in customs clearance`）。进入欧盟的非欧盟电商包裹依据[欧盟海关改革规定](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en)，对货值不超过 €150 的包裹征收 €3 关税并加收 €2 欧盟处理费；详见[海关滞留包裹指南](guide:customs)（英文）。

## DHL Freight 货件延误、破损或部分交付

由于向 DHL Freight 订舱的是发货方，请优先联系卖家或发货人处理。

- **外观破损或缺件（`partly delivered`）：** 到货时当场核验货品与件数并立即通知发货人。DHL Freight 帮助中心提供 *How to Prepare a Claim* 索赔准备指南。
- **等待预约或付款：** 若 ActiveTracing 显示 `Awaiting delivery appointment` 或 `waiting for consignee payment`，请立刻联系最新扫描注明的末端货运站或发货人。
- 另见[物流停止更新的原因](guide:tracking-not-updating)（英文）、[显示已签收却未收到](guide:delivered-not-received)（英文）、[包裹破损处理](guide:damaged-parcel)（英文）、[包裹遗失处理](guide:lost-parcel)（英文）和[包裹被退回寄件人](guide:returned-to-sender)（英文）。

## DHL Freight 客服与联系方式

以下渠道公布于 [DHL Freight 帮助中心](https://www.dhl.com/de-en/home/freight/help-center-for-european-road-and-rail.html)与 [DHL Freight 德国网站](https://www.dhl.com/de-en/home/freight.html)：

- **欧洲公铁货运帮助中心：** [dhl.com/de-en/home/freight/help-center-for-european-road-and-rail.html](https://www.dhl.com/de-en/home/freight/help-center-for-european-road-and-rail.html) —— 提供常见问题、*How to Prepare a Claim*、Incoterms 2020、服务条款及危险品规则。
- **Viva 与货运站查询：** 帮助中心内置 DHL Freight 的 AI 客服助手 **Viva** 以及按国家查找货运站和办事处的 *Freight Terminals and Locations*。
- **企业门户：** *myDHLFreight*（帮助中心附教学视频与 FAQ）。

> 请忽略声称需点击外部链接在线付款才能“放行” DHL Freight 货件的陌生短信或邮件。务必直接在 [activetracing.dhl.com](https://activetracing.dhl.com/DatPublic/datSelection.do) 或向目的地 DHL Freight 货运站核实状态。另见[快递诈骗短信识别](guide:delivery-scam-texts)（英文）。

## 使用 Peek 跟踪 DHL Freight 货件

对于 `JND` 标签号（`JND48291736405829173`，虚构），[Peek](/) 会向 DHL Freight 查询，也只向它查询；ActiveTracing 有这票货件的记录后，Peek 显示“DHL Freight 有这个包裹的记录。”对于 10 位托运号（`4829173640`），若你在德国、瑞典、奥地利、波兰、荷兰、比利时、法国、意大利、西班牙或英国查件，Peek 会在使用 10 位数字的快递公司中询问 DHL Freight，在其他地区只作为最后的选择。Peek 也能识别粘贴的 `activetracing.dhl.com/DatPublic/datSelection.do` 链接（含 `a=`、`valuePackageField=` 或 `valueShipmentOrderField=`）。如为订单号（`FRA-EC-4829173`）、9 位或 11 位托运号或 SSCC（`00348291736405829177`），请在添加时点击“更换”或在包裹页点击“更换快递公司”手动选择 DHL Freight（搜索“DHL ActiveTracing”、“DHL Frachtverfolgung”或“DHL Freight GmbH”同样可找到）。

Peek 直接读取 ActiveTracing 英文页面，展示对应阶段（“已生成面单”、“已揽收”、“运输中”、“清关中”、“派送中”、“待取件”、“派送未成功”、“需要留意”、“已退回寄件人”、“已签收”）、带货运站代码（如 `KOBLENZ, DE`）的状态句、毛重及目的地国家。Peek 按每个货运站所在时区解析时间并换算为你设备的本地时间。服务名称不会显示在任何 Peek 应用中。收货人所在城镇是 ActiveTracing 显示的唯一地址信息，货件在途时可以作为你自己地图上路线的终点；分享链接不会显示它。Peek 不显示发货地、件数、体积、出发日期、单独的送达时间、其他参考号、签收证明与签名；ActiveTracing 本身不提供预计送达日期、状态码及收发货人姓名。

当你正在 Peek 中查看货件，或派送当天已开启通知时，Peek 最多每 10 分钟查询一次；在 ActiveTracing 显示 `Out for Delivery` 的当天，只要 ActiveTracing 正常响应，自最新扫描所在地的早上 6 点起每 2 分钟查询一次（需要通过浏览器读取时每 5 分钟一次）。其余情况下每小时检查一次。若在“已生成面单”或“已揽收”停留超过 12 小时，或连续两天无新动态，无论如何关注均改为每小时一次；在“已生成面单”停留满 7 天改为每 6 小时一次。当 ActiveTracing 无响应、Peek 仅通过物流查询服务跟踪时，会每小时查询一次，静默一天后每 3 小时一次，派送当天最多每 15 分钟一次。

若最新扫描已过 4 天，且货件仍处于“已生成面单”、“已揽收”或“运输中”，Peek 会按该扫描日期做出提示，例如“自10月12日周一起没有物流更新”。页面会显示“包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 DHL Freight 查询。”，并提供前往帮助中心的“向 DHL Freight 寻求帮助”链接。若超过三周没有新扫描，则会提示“已超过三周没有新动态。如果还没收到，请联系卖家。Peek 会持续向 DHL Freight 查询。”

Peek 可以按“每次扫描”、“重要环节”或“仅派送当天”通知你。Peek 仅监控物流更新，无法代你预约派送时间、缴纳运费或联系 DHL Freight 货运站。

## 关于 DHL Freight 物流查询的问题

### DHL Freight 与 DHL Paket 或 DHL Express 有什么区别？

DHL Freight 专门承运欧洲公路与铁路货运（托盘货、LTL/PTL/FTL、大件 *DHL Home Delivery* 及瑞典国内货运）并在 [ActiveTracing](https://activetracing.dhl.com/DatPublic/datSelection.do) 查询；标准包裹属于 [DHL](carrier:dhl)，限时国际空运快件属于 [DHL Express](carrier:dhl-express)。

### DHL ActiveTracing 支持哪些运单号？

ActiveTracing 接受 DHL 订单号（`FRA-EC-4829173`，虚构）、9 至 11 位托运号（`4829173640`）、20 位 `JND` 标签号（`JND48291736405829173`）以及 20 位 GS1 SSCC 编码（`00348291736405829177`）。

### 为什么我的货件停在 `Awaiting delivery appointment`？

末端货运站会暂存货件直到与收货人约好送货日期。请联系发货方，或直接联系最新记录中显示的末端货运站。

### DHL ActiveTracing 会保留多久的货运记录？

[ActiveTracing](https://activetracing.dhl.com/DatPublic/datSelection.do) 注明货件数据在线保留最近 **6 个月**。

:::sources
- [DHL Freight: ActiveTracing](https://activetracing.dhl.com/DatPublic/datSelection.do) – 托运号、订单号与包裹编号/标签号查询、保留 6 个月、*DHL Freight GmbH*
- [DHL Freight: Germany Home (English)](https://www.dhl.com/de-en/home/freight.html) – 40 多个国家、每年 4,500 多万吨、LTL/PTL/FTL、GoGreen Plus Flex、Rail Freight Europe 与货运站网络
- [DHL Freight: Help Center for European Road and Rail (English)](https://www.dhl.com/de-en/home/freight/help-center-for-european-road-and-rail.html) – Viva AI 助手、Freight Terminals 货运站查询、How to Prepare a Claim、Incoterms 2020 与 myDHLFreight（Peek 链接的帮助网址）
- [European Commission: EU Customs Reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – €150 以下货件 €3 关税与 €2 欧盟处理费
- [Universal Parcel Scraper: dhl-freight](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/dhl-freight) – `JND`、订单号、9–11 位托运号与 SSCC 的识别、`activetracing.dhl.com` 链接规则、英语/德语状态对照、各货运站的当地时间、读取的重量与目的地国家，以及帮助网址
:::
