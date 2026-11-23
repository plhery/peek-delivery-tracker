---
title: eBay SpeedPAK 物流查询：28位单号、状态与尾程
description: 查询eBay与橙联SpeedPAK跨境包裹：ES/EE/EX28位单号规则、中英文状态与LM尾程单号含义、欧美时效、售后及客服电话。
slug: ebay-speedpak-wuliu-chaxun
published: 2026-11-23
updated: 2026-11-23
---

SpeedPAK 是 eBay 联合橙联（Orange Connex）专为中国发往海外的跨境订单打造的官方物流服务（参见 [eBay SpeedPAK 官方介绍](https://export.ebay.com/en/ebay-speedpak/)）。SpeedPAK 运单号固定为 28 位字符，以 `ES`、`EE` 或 `EX` 开头——你可以直接粘贴到上方查询框，或前往橙联追踪页 `www.orangeconnex.com/tracking` 查询。在 eBay 买家端，单号直接显示在「Purchase history（购买记录）」对应商品旁边的链接中。

## SpeedPAK 单号格式：28 位 `ES`/`EE`/`EX` 编码

橙联查件系统上读取的 SpeedPAK 运单号均为 28 位字母和数字组合（以下示例字符均为虚构）：

| 单号（虚构） | 格式与归属说明 |
| --- | --- |
| `ES4829173605142839605142839N` | `ES` 加 26 位字母数字（共 28 位）：标准 SpeedPAK 运单号。 |
| `EE4829173605142839605142839X` | `EE` 加 26 位字母数字（共 28 位）：SpeedPAK 运单号。 |
| `EE482917367CN` | 2 位字母 + 9 位数字 + 2 位国别代码：国际邮政 EMS 单号，不是 SpeedPAK。 |

eBay 卖家页面还提到以 `EX` 或 `EM` 开头的 SpeedPAK 编号：Peek 会将 28 位 `EX` 编号按 SpeedPAK 跟踪（历史案例中全程由 DHL Express 承运），但暂不将 `EM` 编号按 SpeedPAK 自动归类。

**如何找到单号及尾程单号。** 按 [eBay 买家查件帮助](https://www.ebay.co.uk/help/buying/postage-delivery/tracking-item?id=4027)，登录买家在 Purchase history 点击单号即可；游客下单可打开标题以「Order Confirmed」开头的邮件，依次点击 View order details 和 Show delivery details；若尚未上传单号请直接联系卖家。找不到单号也可参考 [如何找到你的快递单号](guide:find-tracking-number)。包裹抵达收件国后，SpeedPAK 轨迹往往会附带当地尾程快递商名称及其独立尾程单号。

## SpeedPAK 物流状态是什么意思

橙联查件页支持中英文切换，常见英文核心节点按时间顺序如下：

| SpeedPAK 英文原文 | 对你的实际含义 |
| --- | --- |
| `Shipment Data Received` | 卖家已生成面单，SpeedPAK 收到订单数据但尚未拿到实物。 |
| `Package Received` | 已从卖家处揽收包裹。 |
| `Arrived at Regional Distribution Center` | 已到达区域分拣中心（通常仍在国内）。 |
| `Export Customs Declaration Completed` | 起运国出口报关已完成。 |
| `Departed from Origin Country` | 国际航班已离开起运国。 |
| `Arrived at Destination Hub` | 航班已降落在目的国枢纽，等待进口清关。 |
| `Import Customs Clearance Completed` | 目的国进口海关已放行。 |
| `Destination DC arrival` | 已到达目的国当地分拨/派送中心。 |
| `Out for delivery` | 当地快递员今天正在外出派送。 |
| `Delivered` | 已妥投签收（如 `Delivered. (Recipient's front door)` 会注明投放位置）。 |

不同批次措辞偶有差异：`Arrived at destination country` 与 `The consignment has arrived in the country of destination` 与 `Arrived at Destination Hub` 同义；`Shipment Picked Up (A-SCAN)` 是揽收后的首次实物扫描；有时英文界面也会夹杂中文 `货物运输中`。

**`LM Carrier` 尾程交接行。** 出现 `LM Carrier: [快递名] , LM tracking No.: [尾程单号]` 说明包裹已移交目的国尾程快递（LM 即 Last Mile），随后轨迹会转发当地快递原文（例如 `The item is received by PostNL`，以及经 PostNL 国际线转交目的国邮政后的 `Item is cleared by customs`、`Arrival at Delivery Post Office` 与未送达后转自提待取的 `The item is being held, addressee being notified`）。包裹在 `Arrived at Destination Hub` 或清关节点停顿数日属于常见现象，海关相关说明见 [包裹海关扣留与清关指南](guide:customs)。

## SpeedPAK 包裹在各国由谁派送与时效

SpeedPAK 不直接上门投递，而是在目的国移交给合作快递商：历史记录中美国常见 USPS、UniUni 或 DHL eCommerce（末端转 USPS），瑞士与葡萄牙常见 PostNL（记作 `NL Post`）。针对英国、德国、法国、意大利和西班牙，eBay 与橙联均未公开固定单一尾程商，请以轨迹中的 `LM Carrier` 行为准或询问卖家。

派送尝试、通知卡、自提点、快递柜与保管天数全由当地尾程快递规定。另外请注意，橙联经济服务（Economy）仅提供半程跟踪，eBay 泛欧次级线路也仅跟踪到「揽收至抵达目的国」，落地后可能不再更新。根据 [eBay SpeedPAK 服务时效页](https://export.ebay.com/sea-en/speedpak/speedpak-shipping-services/)，标准服务发往美国及英爱等 17 个欧洲国家平均参考 8–12 天（泛欧次级线路 10–15 天），橙联标准服务标称 8–12 个工作日，均为平均参考而非单票保证日期。

## SpeedPAK 物流不更新、延误或丢件怎么办

- **超过五天无动静：** 橙联建议买家先联系卖家核实发货，若连续超过 5 天没有任何移动也应第一时间联系卖家。另见 [物流为什么停止更新](guide:tracking-not-updating) 和 [eBay 订单追踪指南](guide:ebay-orders)。
- **延误、未收到或已签收没拿到：** eBay 英国站等站点允许买家在预计送达日期后的 30 个自然日内提交「未收到物品」申请，卖家需在 3 个工作日内答复；若未解决可在开启申请后 21 个工作日内请 eBay 客服介入，退款通常在 3–5 个工作日内到账。显示已签收但没收到请先询问邻居再提交申请。按 [橙联理赔说明页](https://help.orangeconnex.com/helpCentre/claims.html)，橙联的理赔通道仅面向发货卖家而非个人买家，收件人退款一律通过卖家和 eBay 平台办理。

## SpeedPAK（橙联）客服电话与邮箱

橙联（Orange Connex）未在英国或爱尔兰设立面向个人收件人的专线，建议买家通过 eBay 联系卖家；其官网公布的客服联络方式（周一至周六北京时间 `09:00–18:00`，周日及法定节假日休息）：

- **中国大陆电话**：`400 126 0008` 或 `86 21 60662670`。
- **中国香港电话**：`852 30183458`。
- **客服邮箱**：`CS@orangeconnex.com`。

一旦轨迹出现当地尾程快递名称和单号，预约重派或自提请直接联系当地快递商。

## 用 Peek 跟踪 SpeedPAK 包裹

粘贴 28 位 `ES`、`EE` 或 `EX` 开头的单号（如虚构单号 `ES4829173605142839605142839N`）或含单号的 `orangeconnex.com`/`orangeconnex.cn` 链接时，[Peek](/) 会立刻唯一自动识别为 SpeedPAK，确认记录后提示「SpeedPAK 有这个包裹的记录。」；详情页也附有「向 SpeedPAK 寻求帮助」链接直达 eBay 未收到物品帮助页。如需手动切换，点击「更换」或「更换快递公司」即可。

Peek 展示规范化阶段（「已生成面单」「已揽收」「运输中」「清关中」「派送中」「待取件」「已签收」等）、每条扫描的城市/国家（标在地图上）、目的国、签收时间以及尾程快递商与单号（SpeedPAK 不提供预计送达日期和自提点详情）。当 SpeedPAK 公布尾程快递与单号且在 Peek 支持范围内时，Peek 会同步查询该尾程商，待其出现更新扫描后自动接力切换并保留原单号与前期历史。

在 SpeedPAK 官方接口正常响应时，Peek 最快每 10 分钟检查一次，进入派送当天从当地时间早上 6 点起最快每 2 分钟检查一次（第三方查询服务每 15 分钟一次；若已接力给尾程快递则遵循其频率，如 Royal Mail/Poste Italiane 每小时、PostNL/USPS 每 30 分钟）；连续两天无新扫描（或前段处于「已生成面单」「已揽收」满 12 小时）转为每小时一次。静默满四天时 Peek 会提示「包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 SpeedPAK 查询。」，超过三周无动静则提示「已超过三周没有新动态。如果还没收到，请联系卖家。Peek 会持续向 SpeedPAK 查询。」。

通知偏好可选「每次扫描」「重要环节」或「仅派送当天」。Peek 无法替你联系 SpeedPAK、卖家或在 eBay 上发起申诉。

## 关于 eBay SpeedPAK 物流查询的常见问题

### eBay SpeedPAK 是什么物流？

是 eBay 与橙联（Orange Connex）合作推出的跨境官方物流，主要服务从中国发往海外的 eBay 订单（同时也支持从德国发往美国、英国和澳大利亚等少数始发地）。

### SpeedPAK 是正规可靠的渠道吗？

是的。它是 eBay 官方主推并对接的自有物流品牌；如果包裹未按期送达，买家享有完整的 eBay 买家保障，直接向卖家和 eBay 申诉即可。

### 英国地区的 SpeedPAK 包裹是由皇家邮政（Royal Mail）派送吗？

eBay 与橙联没有公开英国固定合作尾程商，请等待轨迹里出现 `LM Carrier` 交接行查看实际派送公司与单号。

### 为什么我打开橙联 SpeedPAK 查件页默认显示中文？

橙联追踪页支持中英文，除非你此前手动选过英文或浏览器语言正好设为通用 `en`，否则有时会默认打开中文界面；点击页面右上角语言切换即可，但个别原始扫描行仍可能保留中文。

:::sources
- [eBay：SpeedPAK 跨境物流服务与时效（英文）](https://export.ebay.com/sea-en/speedpak/speedpak-shipping-services/) – 中国发往欧美标准/经济时效、覆盖国家与橙联客服邮箱时段
- [eBay：eBay SpeedPAK 介绍（英文）](https://export.ebay.com/en/ebay-speedpak/) – 与橙联合作及 EX/EM/EE 号段说明
- [Orange Connex：理赔政策（英文）](https://help.orangeconnex.com/helpCentre/claims.html) – 理赔通道仅面向签约卖家
- [eBay UK：如何追踪你的商品（英文）](https://www.ebay.co.uk/help/buying/postage-delivery/tracking-item?id=4027) – 购买记录与游客订单找单号方法
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – 28 位 ES/EE/EX 单号与 `orangeconnex.com`/`.cn` 链接规则、中英文及 PostNL/DHL Express 状态映射、LM Carrier 尾程提取与自动接力机制
:::
