---
title: 守务国际（Shouwu）物流查询：SWECN单号、状态与尾程
description: 查询上海守务国际（Shouwu International）跨境包裹：SWECN…YQ单号格式、中英文状态与海关含义、英美欧尾程派送商及异常排查。
slug: shouwu-guoji-wuliu-chaxun
published: 2026-11-23
updated: 2026-11-23
---

守务国际（Shouwu International，全称上海守务国际物流有限公司，简称 SWE）是一家总部位于上海的跨境物流企业，为中国网店将包裹运往欧洲和美国并交由当地邮政或快递公司派送。如果卖家通过守务国际发货，运单号以 `SWECN` 开头、以 `YQ` 结尾——可以直接粘贴到上方查询框，或前往守务国际追踪页 `admin.shshouwu.com/track.html` 查询。单号由发货卖家随订单物流信息提供。

## 守务国际单号格式：怎么看、怎么查

守务国际的标准运单号由 `SWECN` + 12 位数字 + `YQ` 组成，共 19 位字符。以下示例数字均为虚构：

| 单号（虚构） | 格式说明 |
| --- | --- |
| `SWECN482910573649YQ` | `SWECN` 加 12 位数字再加 `YQ`：守务国际标准运单号。 |
| `SWECN48291057364YQ` | 中间只有 11 位数字：不符合守务国际格式，请检查是否漏输了一位数字。 |
| `JSEKP4829105736YQ` | 同样以 `YQ` 结尾，但开头是 `JSE` 且中间为 10 位数字：这是 [急速国际（JS Express）](carrier:js-express) 的单号。 |

虽然很多中国跨境专线都以 `YQ` 结尾，但大多数中间只有 10 位数字，在 Peek 的目录中，`SWECN` 配 12 位数字是守务国际的专属格式。在 Peek 中粘贴时，空格、连字符和字母大小写都不影响识别。找不到单号请参阅 [如何找到你的快递单号](guide:find-tracking-number) 和 [快递单号格式指南](guide:tracking-number-formats)。

**卖家有时会直接给尾程单号。** 守务国际的专线允许卖家自行选择向买家展示 `SWECN` 全程单号，或者直接展示当地尾程快递单号——因此守务承运的包裹也可能以英国 Royal Mail、Yodel、Evri 单号或美国 USPS 单号的形式交给你。直接把该尾程单号粘贴到 Peek 即可，Peek 跟踪 4,500 多家快递公司。

**查件链接与批量查询。** 守务国际查件页的「Copy Link」按钮会生成带有 `?q=` 参数的单件直达链接，整条链接也可以直接粘贴到 Peek 中识别；官网查件框一次最多支持每行输入一个、共 40 个单号。

## 守务国际物流状态是什么意思

守务国际查件页采用中文标题配英文界面，每票包裹顶部有一个汇总状态（系统自动判定、仅供参考）和目的国，下方按时间列出轨迹但不展示城市地点。先看汇总状态：

| 守务国际汇总状态 | 对你的实际含义 | Peek 显示阶段 |
| --- | --- | --- |
| `Not Found` | 守务系统里还查不到这个单号（提示「We seem can't identify the number.」）。 | 等待守务国际更新 |
| `Preparing` | 守务解释为包裹已交中国仓库或正在称重封装，但在首次实扫前就可能显示。 | 「已生成面单」，待合作方揽收或入仓后转为「已揽收」 |
| `In Transit` | 涵盖交给干线承运商、清关到目的国境内转运的全过程。 | 「运输中」，或按最新扫描归为「清关中」等具体阶段；若仅有预报和 `Carrier received the shipment` 则保持「已生成面单」 |
| `Undelivered` | 派送尝试未成功。 | 「派送未成功」，若最新扫描另有说明则显示「待取件」或「已退回寄件人」 |
| `Delivered` | 已送达收件人。 | 「已签收」 |

以下是明细轨迹中常见的英文节点及其含义：

| 守务国际轨迹原文 | 对你的实际含义 | Peek 显示阶段 |
| --- | --- | --- |
| `Shipment information received`、`Order information received. We're expecting your parcel to arrive with us.` | 卖家已下单建单，守务国际尚未拿到实物。 | 「已生成面单」 |
| `Carrier received the shipment` | 守务系统往往在收到订单数据两小时后就自动写入此行（比首次入仓早好几天），并不代表已经收到实物。 | 「已生成面单」 |
| `Picked up by Shipping Partner` | 守务合作的揽收伙伴已从卖家处收货，是订单数据后的第一条真实扫描。 | 「已揽收」 |
| `Arrival processing center` | 包裹已进入守务国际的中国仓库。 | 「已揽收」 |
| `Package processing completed` | 中国仓库处理完毕，准备装运发往机场。 | 「已揽收」 |
| `Shipment started the customs clearance process at origin` | 中国起运地出口清关开始，属于出境常规步骤而非扣关。 | 「运输中」 |
| `The package is shipped from the transfer warehouse to the airport Export customs clearance in progress` | 已从中转仓发往机场并同步办理中国出口清关（不是目的国清关）。 | 「运输中」 |
| `Export customs declaration completed`、`Export customs clearance completed`、`Export Released` | 中国出口报关已放行。 | 「运输中」 |
| `The parcel has arrived at the airport and is currently being loaded onto a flight` | 出口放行后已到达起运机场并正在装机。 | 「运输中」 |
| `Arrived at Shanghai Pudong Airport`、`Departed from export airport` | 到达上海浦东机场并起飞离境。 | 「运输中」 |
| `The ship has already set sail` | 海运专线中表示货轮已启航离华。 | 「运输中」 |
| `Arrived at airport of Destination`、`Shipment arrived in the destination country` | 航班已降落在目的国。 | 「运输中」 |
| `NOA received` | 目的国代理已收到航空公司到货通知，即将开始进口清关。 | 「运输中」 |
| `Shipment started the customs clearance process at destination`、`Customs clearance in progress`、`Currently undergoing customs clearance in the destination country`、`Currently processing import customs clearance`、`Customs clearance for imports` | 目的国正在办理进口清关。 | 「清关中」 |
| `Custom clearance completed`、`Shipment completed the customs clearance process at destination`、`Customs clearance has been completed`、`Customs clearance in the destination country has been completed` | 目的国进口海关已放行。 | 「运输中」 |
| `Scheduled For Delivery` | 守务在清关完成后写入的常规行，通常比当地快递真正装车派送早好几天，不代表当天派送。 | 「运输中」 |
| `Delivery to local courier`、`The goods have been deliverey to the final delivery service provider`、`The goods have been delivered to the carrier` | 已移交给目的国尾程快递商，不是送到你手中（`deliverey` 为原系统拼写）。 | 「运输中」 |
| `Your consignment has been picked up` | 尾程快递在清关后从守务海外仓提货（既不是从卖家揽收，也不是收件人自提）。 | 「运输中」 |
| `Picked up` | 在美国常表示 [联邦快递（FedEx）](carrier:fedex) 从守务美国仓提走已清关包裹（附带城市和邮编），不是收件人自提。 | 「运输中」 |
| `Arrived at FedEx location`、`Departed FedEx location` | FedEx 在美国各站点内的转运扫描。 | 「运输中」 |
| `The shipment has been processed in the destination parcel center` | 德国 DHL 已在覆盖你地址的包裹中心完成分拣，准备派送。 | 「运输中」 |
| `Mis routed` | 德国尾程快递误分到了错误网点，正在重转（轻微延误，有时当天仍会派送）。 | 「运输中」 |
| `Out for delivery` | 当地尾程快递员正在外出派送。 | 「派送中」 |
| `Abnormal delivery` | 派送未成功，原因通常在下一条用尖括号标出（如 `<Incorrect address.>`）。 | 「派送未成功」 |
| `The recipient is unknown` | 德国 DHL 上门未找到该收件人姓名，包裹将退回。 | 「派送未成功」 |
| `Shipment will be returned to origin-Bad address` | DHL 因地址无效启动退回流程（正在退回途中、尚未回到发件人），请联系卖家。 | 「需要留意」 |
| `Delivered by post man`、`This parcel has been delivered` | 当地邮政或快递已投递，当守务汇总状态同步为 `Delivered` 后即确认签收。 | 「已签收」 |
| `Shipment sent directly from parcel center to business customer` | DHL 从包裹中心直接派送至企业地址；若跟在退件之后则代表退件已送回发件人。 | 「已签收」，退件后为「已退回寄件人」 |

**意大利线同步的 iMile 轨迹。** 由 iMile 派送意大利包裹时，守务会用【】标出网点名并同步其英文节点：

| 守务国际转发的 iMile 原文 | 对你的实际含义 | Peek 显示阶段 |
| --- | --- | --- |
| `【Milan center】Received.` | iMile 中心仓已收货。 | 「运输中」 |
| `【Milan center】Parcel shipped to【ROM 10】.`、`【ROM 10】Arrived.` | 发往并到达 iMile 末端派送站。 | 「运输中」 |
| `【ROM 10】Shipment out for delivery.` | iMile 快递员外出派送。 | 「派送中」 |
| `<User not at home>`、`【ROM 10】 Back To DS.` | 无人签收，包裹退回派送站点。 | 「派送未成功」 |
| `【ROM 10】Parcel shipped to【Milan Transit Center】.` | 两次派送失败后，站点将包裹退回中转中心。 | 「运输中」 |
| `Back to Sender for reason: wrong recipient address or address related problem` | 多次失败后 iMile 启动退回发件人（冒号后写具体原因，仍在途中），请联系卖家。 | 「需要留意」 |
| `【Milan Transit Center】Order Closed.` | iMile 未妥投即关闭了派送单，请尽快联系卖家。 | 「需要留意」 |

**DPD 尾程轨迹与中文干线节点。** 由 DPD 派送的专线会继续出现带句号的 DPD 英文节点：`In transit.` 和 `At parcel delivery centre.`（「运输中」）、`Out for delivery.`（「派送中」）以及因地址错误失败退回网点的 `Unfortunately we have not been able to deliver your parcel. Consignee address not correct.`（「派送未成功」）。部分包裹也会出现守务自写的中文干线行：`货物电子信息已经收到`（「已生成面单」）；`申报过港中`、`已到港，安排航班中`、`预计23号的航班`（前加 `二程` 时为第二程航班）、`航班已飞`、`二程航班已飞`、`已落地`、`清关完成`、`已交仓扫描`（均属「运输中」）；`已落地，清关提货中`（「清关中」）。

**官网时间偏差数小时的原因。** 守务后台会把所有扫描——包括欧美当地扫描——的当地时间直接按北京时间录入，导致原查件页按你所在时区换算后整整提前了几个小时。请以列表顺序为准；Peek 会结合地点或运输阶段自动识别每条记录真实所属时区，再准确换算到你的设备时区显示。

## 守务国际包裹在各国由谁派送

守务国际官网（`www.sedel.com`）按国家列明了专线尾程：

- **英国：** 覆盖英国全境，尾程为 Royal Mail、Yodel 和 Evri（其页面拼写为 ERVI）；带电专线主要走 Royal Mail。参考时效为英国标快 5–8 个工作日、英国经济 10–12 个工作日。
- **美国：** 覆盖美国全境，主要交由 USPS 派送；在 Peek 读取的历史中也常见 [联邦快递（FedEx）](carrier:fedex) 派送。参考时效为美国标快 6–8 个工作日、美国经济 8–12 个工作日。
- **欧盟主要国家：** 德国标快主要交 DHL（4–7 个工作日）、德国经济主要交 Hermes（4–7 个工作日）、德国带电主要交 DPD（4–7 个工作日）；法国主要交 Colissimo（4–7 个工作日）；荷兰主要交 DHL（4–8 个工作日）；瑞典主要交 Early Bird（4–8 个工作日）；意大利经济专线参考 10–12 个工作日，列明由 UniUni 和 Poste Italiane 派送，实际轨迹中也常见 iMile。
- **爱尔兰、加拿大与澳大利亚：** 守务官网未列明专线及尾程快递，请直接向卖家确认。

守务查件页不提供尾程快递商名称或独立尾程单号（除非扫描文本本身提到）。派送次数、通知卡和自提期限完全由当地尾程快递决定；守务英美专线条款注明，无法派送的包裹默认不退回中国而作销毁处理，因此收到派送未成功或通知卡后请第一时间联系当地快递重派或自提。参见 [错过派送怎么办](guide:missed-delivery) 和 [未领取包裹退件说明](guide:uncollected-parcel)。

## 守务国际包裹没收到、卡关或丢失怎么办

- **提示 `Not Found`。** 守务给出四种常见原因：尚未揽收、尚未入仓扫描、单号抄错，或寄出时间过于久远记录已过期；入库处理通常需要几天。若超过两天仍查不到且第三方服务也无记录，Peek 会提示核对单号。
- **停在 `Preparing`、`Shipment information received` 或 `Carrier received the shipment`。** 守务尚未收到实件或尚未发离中国仓库，请问卖家是否已交货。
- **停在进口清关。** 出现进口清关相关英文行或 `已落地，清关提货中` 时，包裹正由目的国海关审核。根据 [欧盟委员会海关改革说明](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en)，欧盟境外网购包裹自 2026 年 7 月 1 日起对 150 欧元以内包裹按每类商品收 3 欧元关税，自 2026 年 11 月 1 日起无论价值多少均另收 2 欧元欧盟处理费；守务专线页未说明谁来承担，收到缴税通知请参阅 [包裹海关扣留与清关指南](guide:customs)。
- **停在 `Delivery to local courier` 后没更新。** 包裹已交给当地快递，但守务页不显示尾程单号，请向卖家索要尾程单号并单独添加到 Peek。详见 [物流为什么停止更新](guide:tracking-not-updating) 和 [中国直邮包裹跟踪指南](guide:tracking-from-china)。
- **显示已签收却没收到。** 守务条款注明，若尾程官网显示已妥投但收件人称未收到，守务仅协助核查、不作赔付。请先查当地尾程快递（美国 USPS 在寄出满 7 天后支持按 [USPS 遗失邮件流程](https://www.usps.com/help/missing-mail.htm) 发起寻件），再找卖家处理。详见 [显示已签收但未收到](guide:delivered-not-received)。
- **丢失或破损。** 卖家才是守务的签约客户，按守务专线条款必须在发货后 30 天内发起索赔（入库后无妥投扫描的丢件按申报价值加运费赔付、最高每票 200 元人民币，途中破损不赔）。因此请尽快联系网店或购物平台申请退款或重发，切勿拖延。见 [包裹丢失怎么办](guide:lost-parcel)。

## 守务国际客服与联系方式

守务国际官网（`www.sedel.com`）全站为中文且仅面向发货卖家，列有上海总部和五个分部的业务手机、QQ 与微信二维码，没有客服邮箱、没有英文页面、也没有面向海外个人收件人的售后渠道；其查件页同样不留任何联系方式。订单售后与理赔请找卖家，具体派送和自提请联系当地尾程快递。

## 用 Peek 跟踪守务国际包裹

[Peek](/) 会通过 `SWECN` + 12 位数字 + `YQ` 的唯一规则立即识别守务国际（如虚构单号 `SWECN482910573649YQ`），查询框里会显示 Shouwu International 和「识别出的快递公司」；带 `?q=` 参数的 `admin.shshouwu.com` 链接也可直接粘贴。无需注册账号、邮编或手机号。需要手动指定时，点击添加页的「更换」或详情页的「更换快递公司」即可搜索守务国际（Peek 跟踪 4,500 多家快递公司）。

Peek 结合守务汇总状态与最新明细扫描判定阶段（「已生成面单」「已揽收」「运输中」「清关中」「派送中」「派送未成功」「待取件」「已签收」「已退回寄件人」「需要留意」等），展示目的国以及守务网页隐藏掉的扫描地点（含数字的邮编字段会自动清理），并修正守务原页面的时区偏差。因为守务不返回预计送达日期，Peek 也不显示预计送达时间；由于守务不提供独立尾程单号，Peek 不会自动切换尾程（拿到尾程单号后请作为新包裹添加到 Peek）。

你打开着这个包裹，或在派送当天开着通知时，Peek 最快每 10 分钟查询一次，包裹派送中时最快每 2 分钟一次（只在派送当天当地时间早上 6 点以后，且结果来自守务官方追踪时）；其他时候每小时一次。只有第三方查询服务有结果时，Peek 每小时查询一次，静默满一天后每 3 小时一次，派送当天你打开着包裹或开着通知时最快每 15 分钟一次。连续两天无新扫描（或处于「已生成面单」「已揽收」满 12 小时）降为每小时查询一次。最新扫描仍是「已生成面单」「已揽收」或「运输中」且静默满四天时，Peek 会提示「包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 Shouwu International 查询。」，超过三周无动静则提示「已超过三周没有新动态。如果还没收到，请联系卖家。Peek 会持续向 Shouwu International 查询。」。

通知范围可选「每次扫描」「重要环节」（清关、派送未成功、待取件、派送中、签收及退件）或「仅派送当天」。Peek 无法替你联系守务国际或卖家。

## 关于守务国际物流查询的常见问题

### 守务国际（Shouwu International）是什么公司？

全称上海守务国际物流有限公司，是一家位于上海的跨境物流企业，经营发往欧美的跨境电商小包专线。

### 在哪里查询守务国际包裹？

可以直接粘贴完整的 19 位 `SWECN…YQ` 单号到页面上方的 Peek 查询框，或者前往守务国际英文查件页 `admin.shshouwu.com/track.html`。

### 守务国际的包裹在英国和美国由谁派送？

由当地邮政或快递商派送：英国专线列明为 Royal Mail、Yodel 和 Evri，美国主要为 USPS（部分线路也见 [联邦快递（FedEx）](carrier:fedex)）。发往爱尔兰、加拿大和澳大利亚的包裹未列明合作快递，请询问卖家。

### 守务国际国际配送一般要多久？

取决于卖家选用的专线。守务公布的参考时效为：英国标快 5–8 个工作日、英国经济 10–12 个工作日；美国标快 6–8 个工作日、美国经济 8–12 个工作日；德国和法国 4–7 个工作日；荷兰和瑞典 4–8 个工作日；意大利 10–12 个工作日。

### 守务国际包裹丢了应该联系谁？

请联系下单的卖家或购物平台：守务仅接受发货卖家在寄出后 30 天内提交索赔，没有面向个人收件人的客服入口。

:::sources
- [守务国际：包裹追踪](https://admin.shshouwu.com/track.html) – 英文查件页、汇总状态说明、Copy Link、一次最多 40 个单号、Not Found 提示
- [守务国际：首页](http://www.sedel.com/) – 公司全称、上海总部与五个分公司热线、在线客服
- [守务国际：主营线路](http://www.sedel.com/main-route.html) – 各国专线尾程与参考时效、可选尾程单号、派送不成功不退回中国、30 天索赔与每票 200 元上限
- [European Commission：欧盟海关改革（英文）](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 自 2026 年 7 月 1 日起 150 欧元以内每类商品 3 欧元关税、自 2026 年 11 月 1 日起每类商品 2 欧元欧盟统一处理费
- [USPS：Missing Mail 遗失邮件说明（英文）](https://www.usps.com/help/missing-mail.htm) – 寄出满 7 天可由寄件人或收件人提交寻件
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – SWECN 12 位数字 + YQ 单号规则、查件链接 `?q=`、守务汇总状态与中英文/iMile/DPD/FedEx/DHL 轨迹映射、时区修正及地点清理规则
:::
