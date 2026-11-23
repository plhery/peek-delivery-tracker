---
title: 通邮（TopYou）国际物流查询：TYZR单号、状态与尾程
description: 查询深圳通邮集团（TopYou/SZTY56）跨境包裹：TYZR…YQ单号规则、多版本英文状态与清关含义、美法意德奥尾程派送商及客服电话。
slug: tongyou-topyou-wuliu-chaxun
published: 2026-11-23
updated: 2026-11-23
---

通邮（TopYou，全称深圳市通邮物流科技（集团）股份有限公司，又称通邮集团或 SZTY56）是一家总部位于中国深圳的跨境电商物流服务商，创立于 2016 年，在深圳、广州、上海等地设有分拨中心，日均处理超过 80 万票跨境订单。如果网店通过通邮发运你的订单，单号通常以 `TYZ` 开头、以 `YQ` 结尾——直接粘贴到上方查询框即可跟踪。通邮的轨迹记录涵盖从中国仓库揽收、国际航班、清关，到美国 USPS、欧洲多国本地快递派送的各阶段进度。

## 通邮（TopYou）单号格式：怎么看、怎么查

通邮的标准运单号由 `TYZR` + 1 位字母 + 10 位数字 + `YQ` 组成，共 17 位字符（部分运单第四位可能是 `R` 以外的字母）。以下示例数字均为虚构：

| 单号（虚构） | 格式说明 |
| --- | --- |
| `TYZRK4829173605YQ` | `TYZR` + 1 位字母 + 10 位数字 + `YQ`：标准通邮运单号。 |
| `TYZRK482917360YQ` | 中间只有 9 位数字：不符合通邮格式，请检查是否漏输了一位数字。 |
| `JXCKP4829173605YQ` | 同样以 `YQ` 结尾，但开头是 `JXC`：这是驹隙物流（Juxi Express）的单号，不是通邮。 |

在 Peek 中粘贴单号无需在意空格和大小写；但注意通邮自己的查件页 `track.szty56.com` 区分大小写，在官网查询时请全部输入大写字母。如果找不到单号，请参阅 [如何找到你的快递单号](guide:find-tracking-number) 和 [快递单号格式指南](guide:tracking-number-formats)。

**尾程转单号与复制单号。** 通邮记录中常常同时带有目的国尾程快递的独立单号，官网查件结果页也会展示出来。注意 Peek 不解析通邮查件页的网址链接，请直接复制运单号本身进行查询。

## 通邮（TopYou）物流状态是什么意思

通邮的轨迹全部使用英文书写，不同年代或渠道模板的措辞略有差别。先看经典模板按顺序出现的节点：

| 通邮英文原文 | 对你的实际含义 | Peek 显示阶段 |
| --- | --- | --- |
| `Parcel Data Received` | 卖家已向通邮发送电子包裹信息，但通邮还没拿到实件。 | 「已生成面单」 |
| `Arrived at the first mile sorting center` | 包裹已进入通邮中国头程分拣中心，是首次实收扫描。 | 「已揽收」 |
| `Departed from the first mile sorting center` | 已离开中国头程分拣中心。 | 「运输中」 |
| `Arrived at airport of the original country` | 已到达起运国机场，等待装机起飞。 | 「运输中」 |
| `Departed from Airport to destination country` | 飞往目的国的航班已起飞。 | 「运输中」 |
| `Arrived at destination airport` | 航班已降落在目的国机场。 | 「运输中」 |
| `Departed from customs of` | 目的国海关已放行（通邮有时漏填 `of` 后的国家名，Peek 会自动修整文字）。 | 「运输中」 |
| `At U.S. Postal Service facility, Accepted by U.S. Postal Service` | 美国专线中表示 USPS 已在航班落地后接手包裹，并非重新寄出。 | 「运输中」 |

**新版专线运单措辞。** 通邮较新的专线运单常使用以下表述：

| 通邮新版原文 | 对你的实际含义 | Peek 显示阶段 |
| --- | --- | --- |
| `Carrier received the shipment` | 通邮已在中国收件入库。 | 「已揽收」 |
| `Shipment arrived at a carrier facility` | 从通邮首站继续流转到下一设施（不是二次收件）。 | 「运输中」 |
| `Shipment started the customs clearance process at origin` | 中国出口报关流程，属于离境前常规步骤，不是目的国清关。 | 「运输中」 |
| `Shipment arrived in the destination country` | 包裹已抵达目的国。 | 「运输中」 |
| `Shipment started the customs clearance process at destination` | 已进入目的国进口海关清关。 | 「清关中」 |
| `Shipment completed the customs clearance process at destination` | 进口清关已完成，正发往当地尾程快递。 | 「运输中」 |
| `Shipment is out for delivery` | 切勿被字面误导：在法国和意大利专线中，这条记录只是包裹交到 GOFO 首个分拣中心的交接提示，距离司机真正上门派送通常还有好几天。 | 「运输中」 |

**邮政渠道专线措辞。** 尾程由目的国国家邮政派送的线路，清关前常用下列英文节点：

| 邮政专线原文 | 对你的实际含义 | Peek 显示阶段 |
| --- | --- | --- |
| `Accepted by carrier` | 通邮已在中国揽收包裹。 | 「已揽收」 |
| `Inbound in sorting center` | 数分钟后入仓登记，尚未发离首站。 | 「已揽收」 |
| `Outbound in sorting center` | 已离开首站仓库；原文末尾常附带 `, Lastmile Number:` 和尾程邮政单号（Peek 会清理冗余后缀并提取转单号）。 | 「运输中」 |
| `Received by line-haul`、`Hand over to airline` | 干线收货并移交航空公司。 | 「运输中」 |
| `Airline arrived at transit country or district.`、`Airline departed from transit or district country.` | 航班在中转国家或地区经停。 | 「运输中」 |
| `Airline arrived at destination country`、`Arrive at destination country` | 已到达目的国。 | 「运输中」 |
| `Export clearance success` | 中国出口清关放行。 | 「运输中」 |
| `Import clearance success` | 目的国进口清关放行（当地邮政随后仍可能显示自己的清关扫描）。 | 「运输中」 |
| `Customs Clearance Completed Successfully` | 移交当地尾程快递数分钟后完成进口清关放行。 | 「运输中」 |

**同步显示的尾程快递轨迹。** 移交尾程后，通邮后台常常直接续播当地快递的扫描：

- **美国（USPS）与法意（GOFO）：** 发往美国会同步 USPS 扫描；法国和意大利常见 GOFO 的 `Arrived at sorting center`、`The package left sorting center`、`Package departed from sorting center`、`Arrived at GOFO Regional Destination Facility`（「运输中」）以及司机真正出门派送的 `The driver is out for delivery`（「派送中」）。
- **德国（iMile）：** 格式为「阶段名 + 逗号 + 完整英文句」：`In Transit, Your order has been shipped from 【iMile Köln DC Center】.`、`Delivery, Your order has arrived at 【Dortmund01】` 和 `Delivery, Your order has assigned delivery drvier.`（仅分配司机尚未出发，`drvier` 为 iMile 原拼写）全都是「运输中」——所以看到 `Delivery,` 开头并不等于已经出门派送；真正的派送中是 `Delivery, Our delivery associate is out for delivery.`（「派送中」）。盘点类无移动记录不会改变阶段。以 `Arrived, The parcel has been delivered to 【】. Please collect your parcel promptly at 【】 using your pickup code.` 开头的记录表示已到达自提点，请凭取件码领取（「待取件」）。
- **奥地利（DPD）与西班牙：** 奥地利 DPD 轨迹包括 `In parcel delivery center`（「运输中」）、`In delivery`（「派送中」）、`Unfortunately, consignee not present. Notification was left.`（「派送未成功」）、`Parcel was handed over to Pickup parcel shop` / `Takeover by Pickup parcel shop`（「待取件」）以及超期未取退件的 `Parcel was not picked up`（「需要留意」）。西班牙清关后常见 `Parcel in transit`（「运输中」）。

## 通邮（TopYou）包裹在各国由谁派送

- **美国：** 国际航班落地后由美国邮政（USPS）接手派送，通邮记录里的第二单号通常就是 USPS 运单号。
- **法国与意大利：** 常见由 GOFO 法国或 GOFO 意大利派送，通邮在交接时先写 `Shipment is out for delivery`，随后同步 GOFO 扫描。
- **德国与奥地利：** 德国常见 iMile 派送，奥地利常见 DPD Austria 派送，并均在通邮系统里同步后续扫描。
- **其他国家：** 通邮记录有时会给出尾程单号但不标明尾程公司名称，而且通邮内部渠道名本身有时也会借用其他快递的名称，不能单凭渠道名判断。邮政专线还会在 `Outbound in sorting center` 末尾的 `Lastmile Number:` 后列出当地邮政单号。如需确认派送商，请直接向卖家核实。

## 通邮（TopYou）包裹延误、查不到或没收到怎么办

- **停在进口清关（`Shipment started the customs clearance process at destination`）。** 包裹正在目的国海关清关。根据 [欧盟委员会海关改革说明](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en)，自 2026 年 11 月 1 日起，从欧盟境外网购的包裹无论价值多少，均须按每类商品缴纳 2 欧元欧盟统一处理费。收到缴税通知请见 [包裹海关扣留与清关指南](guide:customs)；滞留多日请联系卖家，并参考 [物流为什么停止更新](guide:tracking-not-updating)。
- **清关放行后毫无更新。** 出现 `Departed from customs`、`Shipment completed the customs clearance process at destination`、`Customs Clearance Completed Successfully` 或 `Import clearance success` 后，包裹应已移交当地尾程快递。请在通邮查件页查找转单号，或问卖家要尾程快递商与单号。见 [中国直邮包裹跟踪指南](guide:tracking-from-china)。
- **提示「查询不到」或「暂无轨迹信息」。** 通邮查件页对未知单号返回「查询不到」，对已建单未实扫的包裹返回「暂无轨迹信息」。若超过两天通邮仍查不到该单号且第三方查询服务也无记录，Peek 会提示核对单号并转为每天检查一次（满一周后每周检查一次）；若系统确认已建单但超过一天仍停留在 `Parcel Data Received`，说明卖家尚未交货给通邮，请问卖家何时发出。
- **显示已签收却没收到，或丢失/退件。** 美国包裹先联系当地 USPS 邮局，再联系卖家。由于是卖家与通邮签约，所有丢件退款或补发申请必须找卖家或购物平台提出。参见 [显示已签收但未收到](guide:delivered-not-received)、[包裹丢失怎么办](guide:lost-parcel) 和 [包裹退回寄件人](guide:returned-to-sender)。

## 通邮（TopYou）客服电话与联系方式

根据 [通邮集团官网](https://www.szty56.com/) 公布的联系信息：

- **深圳客服电话**：`+86 755 8181 9898`（中国境内拨打 `0755-81819898`），标注工作日 `09:00–18:00`（未标注时区）。

通邮官网没有设立面向海外个人收件人的客服渠道，其服务对象为发货网店与跨境卖家。关于订单延迟、丢件或退件，请先联系卖家；卖家不回复时再找购物平台客服处理。

## 用 Peek 跟踪通邮（TopYou）包裹

[Peek](/) 会通过 `TYZR` + 1 位字母 + 10 位数字 + `YQ` 的唯一规则立即自动识别通邮（如虚构单号 `TYZRK4829173605YQ`），查询框里会显示 TopYou 和「识别出的快递公司」；第四位为其他字母时属于弱匹配，Peek 会实时询问通邮后台确认。Peek 仅对这两类格式向通邮查询；如果手里只有当地尾程快递单号，请直接粘贴尾程单号（Peek 支持 4,500 多家快递），也可点击「更换」或「更换快递公司」手动调整。

Peek 直接读取通邮官方后台，展示规范化阶段（「已生成面单」「已揽收」「运输中」「清关中」「派送中」「派送未成功」「待取件」「已签收」「已退回寄件人」「需要留意」等）、通邮或转发的尾程轨迹原文、目的国及包裹重量。为了避免中国机场扫描误标成目的国，Peek 会过滤通邮自有节点上只写了国家名的地点；如果通邮整体状态已标记妥投或退件，即便最后一条文字未写明，Peek 也会准确标出「已签收」或「已退回寄件人」。通邮自有扫描按北京时间读取，转发的各国尾程扫描则由 Peek 修正时区后统一换算为你设备的本地时间。通邮不提供预计送达日期。

当通邮记录里带有尾程转单号时，如果它是校验位正确的国际邮政 S10 单号，Peek 会同步向收件国国家邮政查询；如果能唯一匹配某家 Peek 支持直连的当地快递，Peek 也会同步查询，待当地快递出现最新扫描后无缝接力切换并保留通邮历史和两个单号。但对于 20 位纯数字等无法唯一确定归属的号码，Peek 会继续在通邮后台同步尾程扫描。

你打开着这个包裹，或在派送当天开着通知时，Peek 最快每 10 分钟查询一次，包裹派送中时最快每 2 分钟一次（需要打开浏览器查询时为 5 分钟；只在派送当天当地时间早上 6 点以后，且结果来自通邮官方后台时）；其他时候每小时一次。连续两天无新扫描（或前段处于「已生成面单」「已揽收」满 12 小时）改为每小时查询一次。只有第三方查询服务有结果时，Peek 每小时查询一次，静默满一天后每 3 小时一次，派送当天你打开着包裹或开着通知时最快每 15 分钟一次；切换给尾程快递后，按实际派送快递自己的频率查询。最新扫描仍是「已生成面单」「已揽收」或「运输中」且连续四天无新扫描时，Peek 会提示「包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 TopYou 查询。」，超过三周无动静则提示「已超过三周没有新动态。如果还没收到，请联系卖家。Peek 会持续向 TopYou 查询。」。

通知偏好可选「每次扫描」「重要环节」（进口清关、GOFO 与 iMile 真正外出派送、待取件、派送未成功、签收与退件，不含中国出口报关、航班、放行和误导性的 `Shipment is out for delivery`）或「仅派送当天」。Peek 无法替你联系通邮或卖家。

## 关于通邮（TopYou）物流查询的常见问题

### 通邮（TopYou）是什么快递公司？

全称深圳市通邮物流科技（集团）股份有限公司（也称通邮集团或 SZTY56），成立于 2016 年、总部在深圳，在深广沪等地设有分拨中心，日均处理超 80 万票跨境电商小包。运单号格式为 `TYZR` + 1 位字母 + 10 位数字 + `YQ`（部分第四位为其他字母）。

### 为什么包裹还没离开中国，通邮轨迹旁就显示我的收件国？

因为通邮后台会直接把目的国国家名贴在它自己的每一条扫描旁边，哪怕包裹还在中国起运机场。请以扫描文字为准，例如 `Arrived at airport of the original country` 表示仍在起运国机场等航班。

### 通邮包裹在收件国由哪家快递派送？

美国由 USPS 在航班落地后接手；法国和意大利常见 GOFO，德国常见 iMile，奥地利常见 DPD Austria。其他国家通邮有时只给尾程单号不写快递名，请直接询问卖家。

### 通邮包裹丢了该联系谁？

联系向你发货的卖家或购物平台。通邮官网虽公布深圳业务电话 `0755-81819898`，但仅面向发货商家，不直接处理个人收件人理赔。

:::sources
- [通邮集团：官网首页](https://www.szty56.com/) – 深圳联系电话（0755-81819898）与工作日服务时间、企业简介
- [通邮集团：关于我们](https://www.szty56.com/view?path=about/about) – 2016 年成立、深圳总部、各地分子公司及操作处理中心、日处理订单量超 80 万件
- [European Commission：欧盟海关改革（英文）](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 自 2026 年 11 月 1 日起每类商品 2 欧元欧盟统一处理费
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – TYZR 单号规则、多版本英文状态与邮政/GOFO/iMile/DPD 尾程节点映射、地点过滤、时区修正、Lastmile Number 提取及自动接力切换尾程机制
:::
