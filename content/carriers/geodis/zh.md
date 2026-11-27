---
title: GEODIS 快递追踪：1G 单号、物流状态与法国派送选项
description: 追踪法国 GEODIS 包裹与托盘：1G 单号格式与查询位置、法语状态对照、改期与网点自提、包裹延误或破损索赔流程，以及收件人客服联系渠道。
slug: geodis-tracking
published: 2026-11-26
updated: 2026-11-26
---

GEODIS 是一家隶属于法国国家铁路公司（SNCF）的法国运输集团，其包裹与托盘网络在法国全境配送网购订单。你可以使用以 `1G` 开头的 12 位编号在 GEODIS 收件人专区（“Espace destinataire”）查询物流，或直接将该单号粘贴到上方输入框中。单号由商家或寄件人提供，通常见于订单确认邮件或发货通知邮件。

## GEODIS 运单号：格式与查找位置

GEODIS 说明追踪单号由寄件人或网店提供，通常位于订单确认邮件或发货通知中。若未收到邮件，请联系商家，或参阅[如何查找运单号](guide:find-tracking-number)（英文）。如果寄件人开通了通知服务，GEODIS 也会在派送前或派送当天通过邮件或短信提醒你。

收件人专区接受两种编号，以下字符均为虚构：

| 格式（虚构） | 含义 |
| --- | --- |
| `1G4829173605` | “1G 追踪单号”：共 12 位，以 `1G` 开头，后接 10 位大写字母或数字。仅凭此单号即可查询。 |
| `19482917360580` | 14 位货件编号（“numéro d'envoi”）。GEODIS 仅在同时提供收件邮编时才接受该编号。 |

部分外观相似的单号并非 GEODIS 单号。类似 `KMPQ48TR29` 的编号属于在法国配送大件订单的 C Chez Vous。8 位数字在 Peek 中也不是 GEODIS 运单号，它可能属于 Heppner、Colisweb 或 Mondial Relay。请核对商家邮件中注明的承运商名称。

## GEODIS 追踪状态含义说明

收件人专区默认以法语打开，并可切换为英语、西班牙语或意大利语。页面会展示四步时间线（高亮当前节点），有日期扫描记录时还会列出每条扫描的日期及 GEODIS 网点名称。以下是包裹通常会依次经过的 GEODIS 法语状态措辞，以及其英文站显示的四步时间线对应文案：

| GEODIS 法语原文 | GEODIS 英文站文案 | 对你的含义 |
| --- | --- | --- |
| `En préparation chez l'expéditeur` | `In preparation at the shipper's` | 寄件人已登记货件，GEODIS 尚未拿到包裹。 |
| `En cours d’acheminement` | `In transit` | 包裹正在 GEODIS 网络中转运。 |
| `Mise en livraison` | `Delivery in progress` | 包裹已进入派送环节（派送中）。 |
| `En cours de livraison` | – | 包裹正随司机执行派送路线。 |
| `Expédition livrée` | `Delivered shipment` | 已送达，并附送达日期。 |
| `Livraison effectuée` | – | 已送达。 |

`Delivery in progress` 是 GEODIS 的派送中节点：包裹正在司机车上，尚未完成签收。在商家实际交寄包裹前，状态会一直停留在 `In preparation at the shipper's`，此时请联系商家而非 GEODIS。包裹抵达 GEODIS 网点等待你自提时，法语界面会显示“Disponible pour retrait en agence”。

派送当天，页面可能额外显示司机实时地图，并附带“The driver is on the way”或“The driver is coming !”等提示，也可能提醒你尚未预约派送。包裹待自提时，页面会出现剩余天数倒计时；另一项提示则会说明距离货件退回寄件人还剩几天。GEODIS 未对这两项期限公布统一固定天数。更多阶段说明请参阅[物流追踪状态指南](guide:tracking-statuses)（英文）。

## GEODIS 派送服务：改期、网点自提与邻里代收点

收件人专区仅显示当前货件允许使用的选项，GEODIS 可能提供以下选择：

- **更改上门派送日期：** 若支持，可同时选择时间段；派送当天 GEODIS 会给出司机的预计上门时段。
- **网点自提：** 前提是你所在地区开放此选项。GEODIS 表示旗下所有网点均设有自提柜台，页面会列出网点营业时间。取件时请携带身份证件及 GEODIS 发送的邮件或短信。若由他人代领，须提供 GEODIS 委托书、代领人身份证原件、你的身份证原件以及通知信息。
- **指定邻居或门卫代收：** 由你指定人选，在你外出时代为签收。
- **指定安全放置点：** 例如楼梯平台、车库或庭院，司机放置后会拍照留证。
- **Pickme 邻里自提点：** 由附近居民运营，包裹最快自下一个工作日转至该点，到件后由自提点通知你领取。GEODIS 于 2024 年 12 月推出这类自提点时说明，收件人有 14 天时间凭取件密码前往领取。

页面会标明你更改派送选项的截止时间。

更精细的时间段或送货上楼仅在寄件人开通对应服务时方可提供；托盘货物默认送至大门或楼栋入口。

派送当天早上，若寄件人启用了通知功能，GEODIS 会通过短信或邮件告知司机的送达时段。地址或电话有误时，请通过联系表单提交正确信息，或联系商家代为转达。

派送未成功后，GEODIS 会在当地网点保管包裹 8 个自然日；如你未主动联系处理，包裹将被退回寄件人。GEODIS 未公开固定尝试派送的次数。

## GEODIS 包裹延误、破损或未送达

**延误或停滞：** GEODIS 对法国境内标准服务给出的参考时效为 24 至 48 小时，特快服务为次日中午前送达，但其条款说明派送时效不作保证。如多日无更新，请参阅[物流停止更新怎么办](guide:tracking-not-updating)（英文），随后联系商家。

**单号无效：** GEODIS 建议先核对单号格式，再联系寄件人或商家确认。

**等待指示：** 包裹无法投递时，GEODIS 条款说明其会向寄件人征询后续处理意见，由商家决定如何处置，因此请联系商家。

**包裹破损或短少：** 请当着司机的面验货，并在纸质或电子签收单上写明具体异议批注（réserves）。GEODIS 收件人专区提示，依运输法规可在签收后 3 天内书面报告货损或短少。按 GEODIS 条款，若未留下合规异议记录，将无法对其提出索赔。赔偿流程依据寄件人合同办理，请先联系商家。

**对派送结果不满：** GEODIS 条款要求消费者先联系卖家，再联系负责派送的承运商客服或发送邮件至 dpm@geodis.com。若 2 个月内未获回复或对回复结果不满，可向法国消费者调解机构 AME 申诉。

## GEODIS 客服与联系渠道

GEODIS 为法国收件人公布的联系渠道（2026 年 10 月 12 日查阅）：

- **收件人专区：** 追踪页面内置帮助菜单。
- **在线联系表单：** 访问 [geodis.com](https://geodis.com/contact-us)，选择“recipient”（收件人）并填入运单号。GEODIS 表示距收件地址最近的客服团队会在 12 个工作小时内回复。
- **电子邮箱：** 依据其服务条款，消费者关于派送的投诉可发至 dpm@geodis.com。
- **网点查询：** 可在[网点列表](https://geodis.com/fr/liste-des-agences?territory=FR)查阅各地网点名称与地址。

GEODIS 未向收件人公布客服电话。商家作为承运合同签署方，也可以直接向 GEODIS 催查。

## 使用 Peek 追踪 GEODIS 包裹

粘贴 `1G` 单号后，[Peek](/) 会立刻识别为 GEODIS——没有其他承运商使用该单号格式。带有 `noSuivi=` 或 `trackingNumber=` 参数的 GEODIS 收件人专区链接（espace-client.geodis.com/services/destinataires/）同样会被识别为 GEODIS，但仅当链接中包含 `1G` 单号时才能追踪。Peek 无法追踪 14 位货件编号，也不会将其识别为 GEODIS，请改用 `1G` 单号。

Peek 会抓取 GEODIS 的法语追踪数据，并按 GEODIS 法语原文展示各步骤（见上表第一列；`Livraison effectuée` 显示为“已签收”），同时在地图上标出每次扫描对应的 GEODIS 网点；时间按巴黎时间解析，并换算为你设备的时区。Peek 还会展示预计送达日期、寄件人及重量。若出现派送未成功、异常问题、退回或待自提状态，这些状态优先于常规时间线步骤展示。当 GEODIS 仅返回步骤而无带日期的扫描记录时，Peek 会以首次监测到该状态的时间作为日期。

Peek 不展示件数、收件人姓名与地址、派送指示及签收单据。超过 12 个月的货件会显示未找到。在 GEODIS 自己的追踪系统有响应期间，Peek 最高每 10 分钟检查一次；在 GEODIS 扫描显示进入派送当天的当地时间早上 6 点起改为每 2 分钟检查一次（通过追踪服务跟进时为每 15 分钟一次），并可以按“每次扫描”、“重要环节”或“仅派送当天”通知你。连续两天无新动态（或停留在“已生成面单”、“已揽收”满 12 小时）后，Peek 会调整为每小时检查一次。Peek 无法代你更改派送安排，请前往 GEODIS 收件人专区操作。

## 关于 GEODIS 物流查询的问题

### GEODIS 是什么公司？

GEODIS 是一家法国运输集团，自 2008 年起成为 SNCF 全资子公司，其渊源可追溯至 Émile Calberson 于 1904 年创立的公司，并于 20 世纪 90 年代启用 GEODIS 名称。

### 我可以更改 GEODIS 的派送日期吗？

通常可以。凭 `1G` 单号登录收件人专区，只要寄件人选用的服务支持改期，即可自行挑选新的派送日期。

### GEODIS 有收件人客服电话吗？

GEODIS 未公布面向收件人的客服电话。请使用 geodis.com 的在线联系表单、收件人专区内的帮助入口，或直接联系卖家。

### GEODIS 派送需要多长时间？

GEODIS 在法国境内标准包裹的参考时效为 24 至 48 小时，特快服务为次日中午前送达，但均不提供时效保证。

:::sources
- [GEODIS: Espace destinataire](https://espace-client.geodis.com/services/destinataires/) – 单号输入框、语言切换、时间线与提示文案、派送选项、网点自提、代领委托、安全放置点及 3 天异议期限
- [GEODIS: Mon espace GEODIS](https://espace-client.geodis.com/index/) – 派送收件人专区入口
- [GEODIS: Track your shipment](https://geodis.com/track-your-shipment) – 单号获取位置及查询失败时的处理步骤
- [GEODIS: Contact us](https://geodis.com/contact-us) – 联系表单及关于通知、时间段、地址修改与网点自提的收件人问答
- [GEODIS: Suivez votre colis](https://geodis.com/fr/suivez-votre-colis) – 法语版追踪指引与陆运查询工具
- [GEODIS: Nous contacter](https://geodis.com/fr/nous-contacter) – 收件人选项说明
- [GEODIS: FAQ, agency hold](https://geodis.com/fr/faq?question=pendant-combien-de-temps-mon-envoi-est-il-conserve-en-agence) – 网点暂存 8 个自然日，逾期退回寄件人
- [GEODIS: FAQ, recipient customer service](https://geodis.com/fr/faq?question=comment-contacter-le-service-relation-client-destinataire) – 就近客服团队 12 个工作小时内回复
- [GEODIS: Votre espace de travail](https://geodis.com/fr/votre-espace-de-travail) – Espace Client 追踪 Distribution & Express 业务
- [GEODIS: Transport de colis et de palettes](https://geodis.com/fr/transport-de-marchandises/transport-de-colis-et-de-palettes) – 法国境内标准与特快参考时效
- [GEODIS: Conditions générales de vente, mai 2025](https://geodis.com/sites/default/files/widen-migration/2glqzqlnw7/CGV_FR-052025.pdf) – 签收异议批注、非保证时效、寄件人指示、第 18 条投诉、dpm@geodis.com 与 AME 调解
- [GEODIS: GEODIS s'appuie sur Pickme](https://geodis.com/fr/newsroom/geodis-sappuie-sur-pickme-pour-elargir-son-offre-de-livraison-en-point-de-retrait-dans) – Pickme 邻里自提点、14 天保管期与取件密码
- [GEODIS: Notre histoire](https://geodis.com/fr/notre-histoire) – 1904 年 Calberson 起源及 2008 年成为 SNCF 子公司
- [GEODIS: Liste des agences](https://geodis.com/fr/liste-des-agences?territory=FR) – 法国各网点名称与地址目录
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Peek 从 GEODIS 读取与展示的追踪内容
:::
