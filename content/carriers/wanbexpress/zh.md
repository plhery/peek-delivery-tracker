---
title: 万邦速达（WanbExpress）查询：WNB…YQ单号与状态
description: 查询万邦速达（WanbExpress）跨境包裹：WNB…YQ单号格式、深圳分拣与清关状态含义、德意英美尾程轨迹解析及无法派送排查。
slug: wanbang-suda-wanbexpress-wuliu-chaxun
published: 2026-11-23
updated: 2026-11-23
---

万邦速达（WanbExpress，亦写作 Wanb Express、WanBang Express 或 WANB）是一家中国跨境电商物流承运商，负责把网购包裹从中国分拣空运出境并移交给目的国本地快递派送。如果网店通过万邦速达发货，物流单号以 `WNB` 开头、以 `YQ` 结尾——把它粘贴到上方查询框即可追踪。万邦速达自有查件页位于 `tracking.wanbexpress.com`，那里除了显示轨迹外，还可能标注目的国尾程快递公司名称、尾程单号和派送签收照片。

## 万邦速达单号格式：怎么看、怎么查

万邦速达的标准运单号由 `WNB` + 2 位字母 + 10 位数字 + `YQ` 组成，共 17 位字符。以下示例数字均为虚构：

| 单号（虚构） | 格式说明 |
| --- | --- |
| `WNBKP4829173605YQ` | `WNB` + 2 位字母 + 10 位数字 + `YQ`：标准万邦速达运单号。 |
| `WNBKP482917360YQ` | 中间只有 9 位数字：不符合万邦速达格式，请核对是否漏输了一位数字。 |
| `JSEKP4829173605YQ` | 同样以 `YQ` 结尾，但开头是 `JSE`：这是 [急速国际（JS Express）](carrier:js-express) 的单号。 |
| `JXCKP4829173605YQ` | 开头是 `JXC`：这是驹隙物流（Juxi Express）的单号。 |

多家中国跨境货代都以 `YQ` 结尾，靠前三位字母区分公司，`WNB` 即代表万邦速达。在 Peek 中粘贴时，空格、连字符和大小写都不影响识别。找不到单号请先查订单邮件或参考 [如何找到你的快递单号](guide:find-tracking-number) 和 [快递单号格式指南](guide:tracking-number-formats)。

**查件链接与第二单号。** 如果卖家发来带 `?trackingNumbers=` 参数的万邦查件链接，等号后面的 `WNB…YQ` 就是运单号，直接复制该单号粘贴进 Peek 即可。若万邦网站额外展示了目的国尾程快递的单号，而 Peek 没有自动跟到该快递，可以把该尾程单号作为独立包裹添加到 Peek 里同步关注。

## 万邦速达物流状态是什么意思

万邦速达的轨迹以英文记录，每条带有时间和（部分）地点。按常见流转顺序：

| 万邦速达英文原文 | 对你的实际含义 | Peek 显示阶段 |
| --- | --- | --- |
| `Shipping Order Created.`、`Parcel Data Received` | 卖家已下单并上传包裹电子信息，万邦速达尚未拿到实物。 | 「已生成面单」 |
| `Parcel received at Shenzhen Sorting Centre.` | 万邦速达深圳分拣中心已实收包裹，这是第一条实物扫描。 | 「已揽收」 |
| `Parcel processed at Shenzhen Sorting Centre.`、`Parcel departure in Shenzhen Sorting Centre.` | 在深圳分拣中心完成操作并已发出。 | 「运输中」 |
| `Customs declaration completed` | 海关报关申报已完成（未注明起运国还是目的国，且不等于已放行，清关放行是后面的 `Custom clearance completed`）。 | 「运输中」 |
| `Parcel departure in Transit Facility, Parcel Departure` | 已离开中转设施。 | 「运输中」 |
| `Parcel is leaving Airport, Parcel Leaving Port` | 国际航班已离开起运机场/口岸。 | 「运输中」 |
| `Parcel arrives at Airport, Parcel Arrival` | 航班已降落在目的国或邻近枢纽机场，接下来进入进口清关。 | 「运输中」 |
| `Custom clearance completed` | 目的国进口清关已放行。 | 「运输中」 |
| `Parcel received by in JFK01.` | 万邦归档为尾程交接入库（原文漏写了接手公司名称）。 | 「运输中」 |
| `Your package is being prepared by the sender and will be handed over to us soon`、`Your parcel will soon be handed over to us! It is being prepared by the sender.`、`Order information received. We're expecting your parcel to arrive with us.` | 尾程合作方提前转发的待交接通知，表示包裹还没交到合作方手中。 | 「已生成面单」，或维持已有的更后阶段 |
| `In transit along Poste Italiane’s logistic network` | 正通过尾程快递（此处为意大利邮政）网络转运。 | 「运输中」 |
| `Parcel is out for delivery.` | 尾程快递员正在外出派送。 | 「派送中」 |
| `Consignee absent, Consignee could not be found.` | 派送尝试时收件人不在或找不到地址。 | 「派送未成功」 |
| `Placed in Parcelshop/Parcel locker` | 派送未成功后已存放至附近包裹代收点或快递柜等你自提。 | 「待取件」 |
| `Parcel has been delivered.(Front door)` | 已妥投送达（括号说明投放位置，如前门）。 | 「已签收」 |

**同步转发的尾程快递轨迹。** 国际航班落地后，万邦会转发当地快递的英文原文。以德国线的 iMile 为例，网点名放在【】内：

| iMile 原文 | 对你的实际含义 | Peek 显示阶段 |
| --- | --- | --- |
| `Received.` | iMile 在目的国的中心仓已接收包裹。 | 「运输中」 |
| `Arrived.` | 已到达负责你片区的派送站点。 | 「运输中」 |
| `Dispatch task assigned to DA.` | 已分配给派送员（DA），可能比实际发车早好几个小时。 | 「运输中」 |
| `Shipment out for delivery.` | 快递员已装车外出派送。 | 「派送中」 |
| `<Route not completed>`、`Back To DS.` | 当天路线未完成送达，包裹已退回派送站（DS）。 | 「派送未成功」 |
| `Scheduled to <2026-11-02> .` | 退回站点后已排定下一次重派日期。 | 维持「派送未成功」 |

其他国家合作方的轨迹也会以同样方式同步：美国线有 `Parcel received by Transit Facility.`、`In Processing at Transit Facility.`、`In Transit to Next Facility.`、`Parcel received by local Facility.`，以及通用的 `It is currently in transit to the next facility`、`The package is being transferred to the next facility`、`Shipment arrived to sorting centre / Shipment in the sorting centre`，全部归类为「运输中」。英国线有时在深圳扫描之前就会先出现 InPost 的 `Your parcel is on its way to InPost. Please check back for updates`（「已生成面单」），随后是 `We have your parcel. Please check back for updates`（「已揽收」）；InPost 的 `We'll apply your My Account preferences to this parcel delivery` 仅表示套用你的账号偏好，与派送失败同分钟记录的 `Consignee Contacted` 一样不改变包裹阶段。

**时区说明。** 万邦每条扫描都带有明确的 UTC 时区偏移，Peek 会按原偏移准确换算为你设备的本地时间并按最新优先排列。关于中国直邮中转与空窗规律，可参阅 [中国直邮包裹跟踪指南](guide:tracking-from-china)。

## 万邦速达包裹在各国由谁派送

万邦速达负责中国出库、空运和清关，之后交由当地快递派送。`tracking.wanbexpress.com` 可能会展示尾程快递名、尾程单号和派送照。Peek 不显示派送照；当万邦给出尾程单号且 Peek 能认出是哪家快递时，Peek 会显示「由 … 派送」并改向该快递查询。需要查看签收照时，请到万邦官方追踪页核对或直接询问卖家。

从历史轨迹可见：德国线路可见 iMile 派送，有一条万邦扫描提到意大利邮政（Poste Italiane）的网络，英国线路可见 InPost。派送次数、通知卡、改约和自提保留天数全按当地尾程快递规则执行（Peek 未找到万邦关于这些的规则）。拿到尾程快递商与单号后，直接找该当地快递安排重派即可。参见 [错过派送怎么办](guide:missed-delivery)。

## 万邦速达包裹不更新、卡关或没收到怎么办

- **查不到单号。** 如果万邦系统暂无记录，Peek 会显示「等待 WanbExpress 更新」并继续查询；若两天后仍查不到，Peek 会提示「两天过去了，快递公司仍查不到这个单号。请检查单号、更换快递公司，或删除这个包裹。」，请核对 17 位 `WNB…YQ` 单号。
- **一直停在 `Shipping Order Created.` 或 `Parcel Data Received`。** 卖家已下单但万邦还没扫入实件，请向卖家确认交货时间。
- **停在 `Parcel arrives at Airport, Parcel Arrival` 之后。** 可能正在目的国进口清关。根据 [欧盟委员会海关改革说明](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en)，发往德国、意大利等欧盟国家的境外网购包裹，自 2026 年 7 月 1 日起对 150 欧元以内包裹按每类商品收 3 欧元关税，自 2026 年 11 月 1 日起无论价值多少均按每类商品另收 2 欧元欧盟统一处理费。如收到缴税通知，请参阅 [包裹海关扣留与清关指南](guide:customs)；长时间停滞请告知卖家，另见 [物流为什么停止更新](guide:tracking-not-updating)。
- **在 `Custom clearance completed` 后没下文。** 包裹已移交或正移交当地尾程快递，当地快递网站可能更新更快。请去 `tracking.wanbexpress.com` 查尾程快递与单号，或直接问卖家。
- **派送未成功（如 `<Route not completed>`）。** 重派时间与自提点由当地快递安排，请直接联系当地快递商而非万邦。
- **显示已签收但没收到，或丢失/退件。** 先去 `tracking.wanbexpress.com` 查看是否有派送照片，再依次联系当地尾程快递和卖家。由于卖家与万邦签约，所有退款或重发申请必须找卖家或购物平台提出。参见 [显示已签收但未收到](guide:delivered-not-received)、[包裹丢失怎么办](guide:lost-parcel) 和 [包裹退回寄件人](guide:returned-to-sender)。

## 万邦速达客服与联系方式

由于万邦速达网站无法由 Peek 自动读取核验，本页不列出客服电话或邮箱。发货合同由卖家与万邦签署，关于订单延误、退款或丢件请第一时间联系卖家（卖家不回复则找购物平台介入）；涉及派送改期与自提请联系当地尾程快递商。

## 用 Peek 跟踪万邦速达包裹

[Peek](/) 会通过 `WNB` + 2 位字母 + 10 位数字 + `YQ` 的唯一规则自动识别万邦速达（如虚构单号 `WNBKP4829173605YQ`），添加时显示「识别出的快递公司」，查询时无需账号、邮编或手机号。如需手动调整，可点击添加页的「更换」或详情页的「更换快递公司」（Peek 跟踪 4,500 多家快递公司）。

因为 `tracking.wanbexpress.com` 启用了 Google reCAPTCHA 人机校验，Peek 通过服务端浏览器代为读取官方记录；如果官方页面暂时无法应答，Peek 会自动切换至第三方查询服务并展示其记录（可能略有滞后）。Peek 展示规范化阶段（「已生成面单」「已揽收」「运输中」「派送中」「派送未成功」「待取件」「已签收」等）、每条扫描的地点以及起运国和目的国；当万邦整体状态已标明在途、派送中或签收时，Peek 会以该状态为准。Peek 不显示预计送达日期、签收照片或收件地址。当万邦给出尾程快递的单号且 Peek 能认出是哪家快递时，Peek 会显示「由 … 派送」并改向该快递查询；否则请把尾程单号作为独立包裹添加到 Peek。

只要你打开着这个包裹，或在派送当天开启了通知，Peek 最快每 10 分钟向万邦官方追踪查询一次；同样条件下，包裹「派送中」当天从万邦所用时钟的早上 6 点起每 2 分钟一次（需要打开浏览器查询时每 5 分钟一次）；其他时候每小时一次。连续两天没有新扫描（包裹仍显示「已生成面单」或「已揽收」时为 12 小时）后，也降为每小时一次。只有第三方追踪服务有结果时，Peek 每小时查询一次，安静一天后每 3 小时一次，派送当天在你打开包裹或开启通知时最快每 15 分钟一次。

包裹处于「已生成面单」「已揽收」或「运输中」且 4 天没有新扫描时，Peek 会显示「自10月12日周一起没有物流更新」这样的提醒（日期为最后一条扫描的日期），并附上说明「包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 WanbExpress 查询。」；超过三周后，说明改为「已超过三周没有新动态。如果还没收到，请联系卖家。Peek 会持续向 WanbExpress 查询。」。

通知偏好可选「每次扫描」「重要环节」（「清关中」「派送中」「派送未成功」「待取件」「已签收」「已退回寄件人」和「需要留意」；注意万邦的报关行在 Peek 归为「运输中」，不触发清关提醒）或「仅派送当天」。Peek 无法替你联系万邦速达或卖家。

## 关于万邦速达物流查询的常见问题

### 万邦速达（WanbExpress）是什么公司？

一家中国跨境物流承运商（也称 Wanb Express、WanBang Express 或 WANB），从深圳分拣中心揽收发往欧美等国，国际干线和清关后交由当地尾程快递派送。

### 在哪里可以查询万邦速达包裹？

可以直接粘贴完整的 17 位 `WNB…YQ` 单号到上方 Peek 查询框，或访问万邦官方查件页 `tracking.wanbexpress.com`（需通过 Google reCAPTCHA 验证，同时可查看尾程快递名和签收照）。

### `Customs declaration completed` 是清关放行了吗？

不是。它只表示报关申报资料已提交完成（未指明哪国海关）；真正的进口清关放行会显示为 `Custom clearance completed`。两条在 Peek 里都归类为「运输中」。

### 万邦速达包裹在收件国由哪家快递派送？

由当地合作快递派送：德国线可见 iMile，英国线可见 InPost。请去 `tracking.wanbexpress.com` 查看尾程快递信息，或直接询问卖家。

### 万邦速达包裹丢了该联系谁？

联系向你发货的卖家或购物平台：是卖家向万邦下单发货，退款或重发请找卖家或平台。

:::sources
- [European Commission：欧盟海关改革（英文）](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 自 2026 年 7 月 1 日起 150 欧元以内每类商品 3 欧元关税、自 2026 年 11 月 1 日起 2 欧元欧盟统一处理费
- [欧盟委员会：Guidance on the €3 customs duty and the Union handling fee（PDF，英文）](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 欧元处理费与 3 欧元关税一样按每类商品收取，无论价值多少
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – WNB…YQ 单号规则、`tracking.wanbexpress.com` 浏览器抓取与 reCAPTCHA 机制、万邦与 iMile/InPost/意大利邮政/美国尾程轨迹映射、UTC 偏移换算及字段过滤规则
:::
