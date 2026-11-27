---
title: 驼飞侠（DHLink）物流查询：敦煌网单号、状态与尾程
description: 查询敦煌网驼飞侠（DHLink）跨境包裹：FC与各合作专线单号规则、英文轨迹及转交UPS/USPS/GOFO尾程说明、赔付规则与客服邮箱。
slug: tuofeixia-dhlink-wuliu-chaxun
published: 2026-11-23
updated: 2026-11-23
---

驼飞侠（DHLink，运营主体为驼飞侠科技（北京）有限公司）是敦煌网（DHgate）旗下的跨境综合电商物流平台，创立于 2014 年，负责为敦煌网卖家及其他跨境客户承运从中国直发或海外仓发出的包裹，并移交目的国当地快递商完成最后一公里派送。你可以把运单号粘贴到上方查询框，或前往驼飞侠网站轨迹查询页 `dhlink.com/trackQuery`（加 `?lang=en` 为英文界面，不加默认为中文）查件。单号请从你的敦煌网订单页面获取或向卖家索取。

## 驼飞侠（DHLink）单号格式：哪些号码能查

驼飞侠网站未统一规定单一单号格式，单次最多支持查 40 个单号，并接受「全球跟踪号（Global Tracking No.）」「物流派送号（Logistics Delivery No.）」和「DHLink 揽收号（DHLink pickup number）」。常见号段如下（数字均为虚构）：

| 单号（虚构） | 格式与用途说明 |
| --- | --- |
| `FC4829173605142` | `FC` 加 13 位数字：DHLink 自有编号（DHLink No.），部分渠道同时用作跟踪号。 |
| `XZG482917365WL` | `XZG` + 9 位数字 + `WL`：专线合作方单号，可在 DHLink 后台查到。 |
| `YLUS4829173605` | `YLUS` 加 10 位数字：美国专线，末端常由 GOFO 派送。 |
| `QH482917360514FS` | `QH` + 12 位数字 + `FS`：另一条末端由 GOFO 派送的美国专线。 |
| `EYT4829173605HZ` | `EYT` + 10 位数字 + `HZ`：末端由 SwiftX 派送的美国专线。 |
| `CYS48291736051` | `CYS` 加 11 位数字：末端由 UniUni 派送的美国专线。 |
| `JXCYI4829173605YQ` | `JXC` 后接 `YI`、`ZO`、`US` 或 `ZI` + 10 位数字 + `YQ`：也是驹隙物流（Juxi Express）的格式，但部分美国专线由 DHLink 后台应答；Peek 对这几个前缀只问 DHLink，其他 `JXC` 号段归驹隙物流。 |
| `RR482917367CN` | 2 位字母 + 9 位数字 + `CN`：[中国邮政](carrier:china-post) 单号，部分 DHLink 订单走中国邮政。 |
| `GFUS48291736051428` | 不是 DHLink 主单号，而是美国尾程 GOFO Express 的独立单号。 |

查询结果通常会同时列出「DHLink No.」「Tracking No.」，以及交由当地快递后的「Last Leg No.」（尾程单号）。如果 Tracking No. 与 DHLink No. 不同，务必使用 Tracking No. 查询——只输入 DHLink No. 有时会查不到结果。

## 驼飞侠（DHLink）物流状态是什么意思

结果页顶部显示状态（`In transit`、`Delivered`）、起运国与目的国代码、渠道代码以及四步进度条（`Original Leaving`、`Destination Arrived`、`Pending delivery`、`Delivered`），签收件还会标注「Signed for - Signed for time」。由于 DHLink 汇聚多路数据源，同一条扫描有时会重复显示。按时间顺序，常见英文节点含义如下：

| DHLink 原文 | 对你的实际含义 |
| --- | --- |
| `Data Received; Data transferred and received by carrier` | 东南欧专线中经由匈牙利物流平台 Pactic 传回的数据记录：出现在中国仓库扫码前归「已生成面单」，航班后再次出现则保持「运输中」。 |
| `Arrive at the collection warehouse` | 已到达 DHLink 中国集货仓。 |
| `The collection warehouse has completed sorting` | 已在集货仓完成分拣。 |
| `Package received & scanned at warehouse` | 已在 DHLink 仓库扫描入库。 |
| `Parcel handed over to carrier,waiting for transportation` | 揽收点已接货，等待安排干线运输。 |
| `Package departed from the warehouse`、`The package has sent from the warehouse` | 包裹已离开中国仓库。 |
| `Warehouse scanning bagging`、`Master airway bill number received`、`Leave the warehouse` | 部分专线在中国仓库完成装袋、获取航空总运单并出库（「运输中」）。 |
| `Customs export clearance completed`、`Package handed over to airline` | 中国出口报关完成并交付航空公司。 |
| `Flight departure`、`The flight has taken off`、`Shipment Departed From Original Country at …`、`Departed from airport of origin` | 国际航班已起飞离开中国。 |
| `The flight has arrived and the board is being dismantled`、`Dismantling the board` | 航班已落地，机场正在拆板理货。 |
| `Shipment has arriveved at destination Country at …` | 已抵达目的国（`arriveved` 为系统原拼写）。 |
| `Arrived to … hub`、`Linehaul transit; Parcel has entered the linehaul network.` | 落地后进入欧洲合作方枢纽（如东南欧线 Pactic 匈牙利 `HU` 枢纽）及干线网络。 |
| `Customs clearance completed`、`Customs import clearance completed, awaiting pickup by local carrier` | 目的国进口清关已放行，等待当地尾程快递提货。 |
| `UPS/USPS/Fedex` | 即将移交美国本地快递商。 |
| `货物已交UPS/USPS/Fedex,The goods have been delivered to UPS/USPS/Fedex` | 货物已移交给美国本地快递（不是送到你手中）。 |
| `Package picked up by last-mile carrier` | 目的国尾程快递已接手包裹。 |
| `Scheduled for Delivery` | 当地快递外出派送中。 |
| `Shipment has delivered at …` | 已签收送达。 |

美国专线还常出现 `Arrived at Origin Hub`、`Departed Destination Hub`、`Shipping Order Created.`（入库前一小时生成的订单记录，「已生成面单」），以及尾程站点编码记录 `Parcel received by ABC01.` / `Parcel departed from Facility.`（均属「运输中」）。交接后紧跟尾程快递自有扫描，如 GOFO 的 `Arrived at GOFO Delivery Station`、USPS 的 `No Access to Delivery Location`（派送失败）、UniUni 的 `Parcel received at UniUni sorting hub.` / `Parcel processed.` / `Parcel scanned and in transit.`（均「运输中」）。走 [中国邮政](carrier:china-post) 的线路会同步中文扫描：`中国邮政 已收取邮件`、`送交出口海关`、国别代码后的 `已妥投`。查无记录时页面提示 `There is no track for this waybill number`。

## 驼飞侠（DHLink）包裹在各国由谁派送

DHLink 网站面向卖家，不按国家公布固定合作快递表，具体派送商以轨迹结果为准。美国线常见先交接给 UPS、USPS 或 [联邦快递（FedEx）](carrier:fedex)，也常见末端由 GOFO Express、SwiftX 或 UniUni 派送并给出「Last Leg No.」（GOFO 网站也能直接用 15 位 `FC` 单号查到它承运的敦煌网包裹）；瑞士线有时转交 DPD Switzerland；走中国邮政的包裹则全程沿用 `CN` 结尾的 S10 单号由各国国家邮政派送。

一旦获取了尾程快递商与尾程单号，派送次数、通知卡和自提保管均执行该尾程快递的规定（参见 [错过派送怎么办](guide:missed-delivery)（英文））。根据 [爱尔兰税务局欧盟统一处理费新闻稿](https://www.revenue.ie/en/corporate/press-office/press-releases/2026/pr-092926-handling-fee.aspx)，自 2026 年 11 月 1 日起欧盟对境外网购包裹按每类商品征收 2 欧元统一处理费，由卖家或派送企业缴纳并可能在结账时或派送前向收件人收取；更多关税说明见 [包裹海关扣留与清关指南](guide:customs)（英文）。

## 驼飞侠（DHLink）包裹查不到、停滞或丢失怎么办

- **查无记录（Not Found）。** 先去敦煌网订单核对单号，优先尝试「Tracking No.」而非「DHLink No.」；纯尾程单号在 DHLink 网站查不到，应直接去对应尾程快递查询。如果手动选定 DHLink 而 DHLink 还查不到这个单号，Peek 显示「等待 DHLink 更新」并继续查询；两天后仍查无记录、也没有其他来源回应时，状态改为「请检查运单号」。
- **航班或清关后无更新。** 查看结果里有没有「Last Leg No.」，改用尾程单号直接查当地快递。参见 [物流为什么停止更新](guide:tracking-not-updating)（英文） 和 [中国直邮包裹跟踪指南](guide:tracking-from-china)（英文）。
- **显示已签收未收到、延误或丢件。** 按照 [敦煌网卖家帮助：DHLink 物流异常件赔付规则](https://seller.dhgate.com/help/c7502/516002.html)，异常件理赔由发货卖家在物流下单后 90 天内向 DHLink 提出，买家应直接通过敦煌网联系卖家或发起纠纷；规则同时排除因买家未及时签收/自提或未配合清关导致的延误与退件，因此请及时取件并配合清关。见 [显示已签收但未收到](guide:delivered-not-received)（英文） 和 [包裹丢失怎么办](guide:lost-parcel)（英文）。

## 驼飞侠（DHLink）客服与联系方式

驼飞侠中文联系页面（`dhlink.com/about/connect`）未公开客服电话，仅列出：

- **客服邮箱**：`dhlinkservice@dhlink.com`
- **平台工单与在线助手**：网站提供平台咨询表单和智能客服「AI 小飞」。

敦煌网订单售后请优先在平台内联系卖家；包裹抵达当地后的派送改约请联系轨迹中显示的尾程快递。

## 用 Peek 跟踪驼飞侠（DHLink）包裹

由于 `FC`、`XZG`、`YLUS`、`QH`、`EYT`、`CYS`、`JXCYI`、`JXCZO`、`JXCUS`、`JXCZI` 等号段均为弱匹配，粘贴（如虚构单号 `FC4829173605142` 或 `XZG482917365WL`）后 [Peek](/) 会自动实时向 DHLink 后台核实（`FC` 单号还会问 GOFO Express，提示为美国时先问 GOFO），确认有记录后显示「DHLink 有这个包裹的记录。」；如果暂未查到或需手动指定，点击添加页的「更换」或详情页的「更换快递公司」即可（Peek 跟踪 4,500 多家快递公司）。`JXCYI/JXCZO/JXCUS/JXCZI` 号段在暂无结果时会标记为可能是 DHLink，若卖家实发驹隙物流可手动改选；`CN` 结尾国际邮政单号则默认走中国邮政。

Peek 直接读取 DHLink 轨迹并自动合并去重，展示规范化阶段（「已生成面单」「已揽收」「运输中」「清关中」「派送中」「派送未成功」「已签收」等）、扫描地点和目的国。中国扫描按北京时间解析、USPS 扫描按对应美国州时间解析、匈牙利 Pactic 节点按布达佩斯时间解析，并隐藏邮编和订单参考号；只有当同步到的最新记录是带预计时段的 USPS「Out for Delivery, Expected Delivery …」扫描时才会展示当天预计时段。只要「Last Leg No.」属于 GOFO（`GFUS` + 14 位）、SwiftX（`SWX` + 18 位）或 DPD Switzerland（`060`/`061` 开头 14 位），Peek 就会自动显示该尾程商与单号，待其出现最新扫描后无缝接力切换。

在 DHLink 网站正常响应时，只要你打开着这个包裹，或在派送当天开启了通知，Peek 最快每 10 分钟检查一次；同样条件下，包裹派送当天从承运商所在地时间早上 6 点起最快每 2 分钟一次（若给出派送时段，最早于时段开始前 30 分钟启动）；其他时候，以及连续两天无新动态（前段为 12 小时）时，每小时一次。只有第三方查询服务有结果时，Peek 每小时检查一次，最新扫描超过一天后每 3 小时一次，派送当天在你打开包裹或开启通知时最快每 15 分钟一次。接力给尾程快递后，Peek 按该快递的频率检查。静默满四天时 Peek 会提示「包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 DHLink 查询。」，超过三周无动静则提示「已超过三周没有新动态。如果还没收到，请联系卖家。Peek 会持续向 DHLink 查询。」。

通知偏好可选「每次扫描」「重要环节」或「仅派送当天」。Peek 无法替你联系 DHLink、敦煌网或卖家。

## 关于驼飞侠（DHLink）物流查询的常见问题

### 驼飞侠（DHLink）是什么平台？

敦煌网（DHgate）旗下的跨境电商物流平台，由驼飞侠科技（北京）有限公司于 2014 年创立，负责通过自有专线和合作承运商从中国及海外仓发货。

### 我的 DHLink 包裹在收件国由哪家快递派送？

由当地尾程快递派送：美国线常见 UPS、USPS、[联邦快递（FedEx）](carrier:fedex)、GOFO、SwiftX 或 UniUni；瑞士线有时交 DPD Switzerland；邮政渠道交由各国国家邮政。请查看查询结果中的「Last Leg No.」。

### 「The goods have been delivered to UPS/USPS/Fedex」是送到了吗？

还没有。这句话表示 DHLink 已将货物移交给美国本地快递公司（UPS、USPS 或 FedEx），并不是送到了你的收货地址，接下来会出现当地快递的派送扫描。

:::sources
- [敦煌网卖家帮助：DHLink 物流异常件赔付规则](https://seller.dhgate.com/help/c7502/516002.html) – 卖家理赔主体、90 天索赔期限及买家未取件/未配合清关免责说明
- [Revenue（爱尔兰税务局）：欧盟统一处理费新闻稿（英文）](https://www.revenue.ie/en/corporate/press-office/press-releases/2026/pr-092926-handling-fee.aspx) – 2026 年 11 月 1 日起每类商品 2 欧元欧盟统一处理费
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – 驼飞侠公司主体（驼飞侠科技（北京）有限公司、`dhlinkservice@dhlink.com`）、FC/XZG/YLUS/QH/EYT/CYS/JXC 等弱匹配号段规则、中英文/Pactic/UniUni/中国邮政状态映射、去重与时区换算及 GOFO/SwiftX/DPD Switzerland 自动接力机制
:::
