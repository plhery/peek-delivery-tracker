---
title: 新加坡邮政（Singapore Post / SingPost）物流查询
description: 查询新加坡邮政（SingPost）国际与本地包裹：SG 与 CNSGS 单号格式、各状态含义、欧盟与新加坡清关规则、取件与客服电话。
slug: singapore-post-wuliu-chaxun
published: 2026-11-26
updated: 2026-11-26
---

新加坡邮政（Singapore Post，简称 SingPost）脱胎于新加坡国家邮政服务，历史可追溯至 1858 年，如今将信件和电商包裹运往超过 220 个目的地。你可以在 SingPost 的 [Track Your Item](https://www.singpost.com/track-items) 页面、SingPost Mobile 应用或上方输入框中查询邮件。单号印在寄件人交寄时取得的收据或托运单上，网购订单请向商家索取。

## 新加坡邮政运单号长什么样

SingPost 国际单号为 13 位，采用[国际邮政格式](guide:tracking-number-formats)（英文）：两位字母、九位数字加 `SG`，九位数字的末位为校验位。以下数字为虚构：

| 格式（虚构） | 含义 |
| --- | --- |
| `RA482917367SG` | 两位字母、九位数字加 `SG`：由 SingPost 编号的邮件。SingPost 官方示例以 RA 或 SM 开头，关键在于末尾的 `SG`。 |
| `CNSGS4829173640` | `CNSGS` 加十位数字：Peek 关联到新加坡邮政的另一种形态，但不仅凭形态直接判定（见下文）。 |

以其他字母结尾的单号（例如 `DE`）来自其他国家邮政。普通平邮没有运单号。找不到单号？查看[如何查找运单号](guide:find-tracking-number)（英文）。

## 新加坡邮政物流状态含义

SingPost 查询系统按时间倒序列出扫描记录，附带日期、时间以及有时附带地点。以下是 Peek 识别的文案，大致按流转顺序排列：

| SingPost 显示 | 对你的含义 |
| --- | --- |
| `SingPost has received your order information, but not your item yet` | 寄件人已下单预报，SingPost 尚未收到实物。 |
| `Accepted at Drop-Off Point` | 寄件人已在投寄点交寄。 |
| `Item received by SingPost` | SingPost 已收到邮件。 |
| `Item Received at Sorting Facility`、`Item is with SingPost (Singapore) for processing.` | 正在新加坡分拣处理中。 |
| `Further processing in progress` | 继续处理中。 |
| `Despatched to overseas` | 已离开新加坡发往目的国。 |
| `Hold item at office of exchange` | 暂留于邮件互换局（各国邮政交接场所）。 |
| `Arrival at Destination Post` | 已抵达目的国邮政。 |
| `Receive item at Destination Hub` | 目的国分拣中心已接收。 |
| `Arrival at Processing Center` | 已到达途中的处理中心。 |
| `Present to Import Customs` | 已移交进口海关。 |
| `Customs Inspection in Progress` | 海关正在查验。 |
| `Held by Custom` | 海关暂扣：从新加坡寄出时为目的国海关，寄往新加坡时为新加坡海关。 |
| `Out for delivery.`、`Out for Delivery` | 正在派送中。 |
| `Item delivered to letterbox` | 已投递至你的信箱。 |
| `Delivered`、`Item Delivered to Doorstep` | 已送达（后者为送至门前）。 |

其他记录在 Peek 中按 SingPost 原文展示。当 SingPost 查询系统查无记录时会提示“We're unable to find details for this tracking number.”：可能是刚寄出、所选服务不含追踪，或单号属于其他承运商。更多见[物流状态含义](guide:tracking-statuses)（英文）。

## 寄往欧洲的新加坡邮政包裹：末端派送与清关

出现 `Despatched to overseas` 后，由目的国邮政接手：SingPost 表示最终派送由目的国邮政负责，且并非每个国家邮政都实时更新国际邮件动态。请用同一 `SG` 单号查询本国邮政。派送尝试与自提规则均适用当地邮政规定：参见[错过派送](guide:missed-delivery)（英文）。

**欧盟清关。** 新加坡位于欧盟以外，因此包裹须经目的国海关查验。自 2026 年 7 月 1 日起，从欧盟以外网购、订单金额不超过 150 欧元的商品，除增值税外每项（item）征收 3 欧元欧盟关税；自 2026 年 11 月 1 日起，无论金额大小，每项另收 2 欧元欧盟处理费。所谓“一项”是指同一包裹中税则归类、货品描述及必要时原产地相同的商品。

**离开新加坡前已预缴税费。** SingPost 说明，自 2026 年 11 月 1 日起，其从新加坡发往奥地利、比利时、塞浦路斯、丹麦、爱沙尼亚、芬兰、法国、德国、意大利、卢森堡、马耳他、荷兰、葡萄牙、斯洛伐克、西班牙和瑞典的商业货件采用完税后交货（Delivered Duty Paid）：寄件人在下单时已算清并缴付关税、增值税和处理费，因此上门时不应再向收件人催税。对于爱尔兰和波兰等其余 11 个欧盟国家，SingPost 暂维持原有下单与清关流程。

欧盟以外国家适用当地海关规定。更多见[包裹卡在海关怎么办](guide:customs)（英文）。

## 在新加坡接收 SingPost 包裹

**信箱或上门。** 能放进信箱的小件直接投递入信箱；较大包裹、挂号邮件（Registered Service）和需要签收的 Speedpost 会送货上门。放不进信箱或无人签收时，会留下派送通知单（Delivery Notification Card）。

**无人签收。** SingPost 通常会在次工作日再次尝试派送，或将邮件转存附近邮局。无法指定日期或改址，但可致电 1605 授权放在门前。三次派送失败后退回寄件人。

**自提。** 凭通知单可在单上所列邮局领取，保管期 10 个工作日，需带填好的通知单和 NRIC 证件；委托他人代领须在通知单上填写代领人信息。POPStation 快递柜包裹会通过短信和邮件发送取件码，保管 3 个自然日，逾期 SingPost 会再尝试最多两次派送，仍未成功则退回。参见[未领取的包裹](guide:uncollected-parcel)（英文）。

**消费税（GST）与关税。** 经邮政或 Speedpost 入境新加坡的包裹如须缴纳 GST 或关税，SingPost 会代新加坡海关征收并在缴清前扣留包裹，同时寄发缴费通知。须在通知日起 14 天内通过 SingPost Mobile 应用（Pay with SAM）、SAM Online、POPDrop 自助终端或邮局缴费，逾期退件。SingPost 每票加收 10.90 新元处理费，未附发票另收 10.90 新元手续费（发票可发至 g-dps@singpost.com）。缴费后通常 2 至 3 个工作日派送。如果物流显示暂扣但 3 个工作日后仍未收到通知，请联系 SingPost。

**防骗提醒。** 新加坡海关绝不收取清关费，SingPost 也绝不会通过链接收费：参见[快递诈骗短信识别](guide:delivery-scam-texts)（英文）。

## 新加坡邮政物流不更新、丢失或退回

**离开新加坡后没动静。** 出现 `Despatched to overseas` 后，请用同一单号查询目的国邮政，并预留清关时间。详见[物流为什么停止更新](guide:tracking-not-updating)（英文）。

**丢失或破损。** 须自寄出日起 30 天内通过 SingPost 在线咨询表单提交索赔，提供运单号、寄收件人信息、物品描述与价值。SingPost 按申报价值赔付，Speedpost International 上限 150 新元，Speedpost Saver International 上限 68 新元。网购订单请一并联系商家：参见[包裹丢失](guide:lost-parcel)（英文）。

**显示已签收但没收到。** 在新加坡，请检查信箱和门外管道井/门口区域，询问家人，再联系 SingPost（签收类服务可提供签收凭证）。在欧洲由当地邮政投递，请先查当地邮政物流：参见[显示已签收但未收到](guide:delivered-not-received)（英文）。

**退回寄件人。** 连续 3 次派送失败、邮局超 10 个工作日未领，或 14 天未缴 GST/关税，包裹都会退回寄件人。参见[退回寄件人](guide:returned-to-sender)（英文）。

## 新加坡邮政客服电话与联系方式

SingPost 官方渠道：

- **新加坡本地热线：** 1605。
- **海外拨打：** +65 6845 4545（非免费电话）。
- **服务时间：** 新加坡时间周一至周五 8:30–18:00、周六 8:30–13:00，周日及法定节假日休息。
- **在线表单：** [Make an Enquiry](https://www.singpost.com/support/make-enquiry) 表单，受理一般咨询、派送问题、件态查询和重派申请。

## 用 Peek 跟踪新加坡邮政包裹

对于两位字母、九位数字加 `SG` 且校验位正确的单号，[Peek](/) 会立刻识别为新加坡邮政并显示“识别出的快递公司”；点“更换”可改选其他公司。校验位不对时会提示“这个单号的校验位对不上。”：请对照发货信息，或选择“按输入的单号跟踪”。带 `tracknumber=` 或 `trackingid=` 的 SingPost 查询链接同样可用。

`CNSGS` 单号仅凭形态不足以确定属于新加坡邮政，因此 Peek 会先查询：“正在查询物流服务…”，SingPost 查到后显示“Singapore Post 有这个包裹的记录。”；尚未查到时添加后仍会继续查询，并显示“等待 Singapore Post 的首次扫描”。

Peek 会按 SingPost 英文原文显示每条扫描及地点，并将上表文案归入对应阶段（如“运输中”或“清关中”）。SingPost 附在扫描下方的原因说明会被略去，且不展示预计送达日、重量、POPStation 取件码或签收人姓名。Speedpost 扫描不带时区，Peek 按地点或前后扫描的时区读取（到达目的国后按目的国时区），无法判定时按新加坡时间读取。

当 SingPost 标明目的国，或未标明目的国而你在新加坡境外查询时，Peek 也会用同一 `SG` 单号询问目的国邮政：德国 [DHL](carrier:dhl)、法国 [La Poste](carrier:la-poste)、意大利 [Poste Italiane](carrier:poste-italiane)、西班牙 [Correos](carrier:correos-spain)、[PostNL](carrier:postnl)、[bpost](carrier:bpost)、[Austrian Post](carrier:austrian-post)、[Swiss Post](carrier:swiss-post)、[Poczta Polska](carrier:poczta-polska)、葡萄牙 [CTT](carrier:ctt)、瑞典和丹麦 PostNord、[Royal Mail](carrier:royal-mail)、An Post、[USPS](carrier:usps)、[Canada Post](carrier:canada-post) 及 [Australia Post](carrier:australia-post)。对方出现新扫描后 Peek 自动切换跟进，并保留 SingPost 的历史记录。反过来，仅凭目的地为新加坡不会触发自动询问新加坡邮政：只有当运送包裹的承运商（如 Janio）指名由 SingPost 派送时才会询问。

在 SingPost 或你所在国家邮政自有查询正常响应期间，当你打开包裹页，或在派送当天开启通知时，Peek 最多每 10 分钟查询一次，在包裹进入派送当天的当地时间早上 6 点起最多每 2 分钟查询一次（Royal Mail 为每小时一次，Poste Italiane、PostNL 和 USPS 最多每 30 分钟一次；仅通过物流查询服务时，每小时查询一次，静默一天后每 3 小时一次，派送当天最多每 15 分钟一次）。连续两天无新动态（在“已生成面单”或“已揽收”状态下为 12 小时）后改为每小时查询一次，在“已生成面单”下满七天后改为每 6 小时一次。

当最新扫描满四天且包裹仍处于“已生成面单”、“已揽收”或“运输中”时，Peek 会标出该扫描日期（如“自 10 月 12 日周一起没有物流更新”），页面提示：“包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 Singapore Post 查询。”，并附“向 Singapore Post 寻求帮助”链接指向 SingPost 支持页（切换至你所在国家邮政后，链接相应改为该邮政）。三周无新扫描时提示：“已超过三周没有新动态。如果还没收到，请联系卖家。Peek 会持续向 Singapore Post 查询。”

Peek 支持“每次扫描”、“重要环节”或“仅派送当天”通知，但无法替你联系 SingPost、本地邮政或商家。

## 关于新加坡邮政物流查询的常见问题

### SingPost 和 Singapore Post 是同一家吗？

是的。SingPost 是 Singapore Post Limited 的简称，其国际单号以 `SG` 结尾。

### 为什么新加坡邮政物流不更新了？

如果最后一条是 `Despatched to overseas`，说明包裹已离开新加坡：请用同一单号查询目的国邮政并预留清关时间。新寄出的单号在处理完成前也可能暂时查不到记录。

### “Held by Custom”是什么意思？

表示邮件正被海关暂扣。寄往新加坡的包裹通常在等待缴纳 GST 或关税：SingPost 会发通知，你需在 14 天内付款。从新加坡寄出则由你所在国家海关暂扣，参见[包裹卡在海关怎么办](guide:customs)（英文）。

### 新加坡邮政派送需要多久？

SingPost 公布的从新加坡发往主要城市的参考时效：Speedpost Express International 为 2 至 9 个工作日，Speedpost Priority International 为 3 至 14 个工作日，Speedpost Saver International 为 3 至 11 个工作日，Speedpost Economy International 为 3 至 15 周。

### 没有运单号能查 SingPost 邮件吗？

不能。SingPost 仅为 smartpac、Registered Service 和 Speedpost 等带追踪服务分配单号，请向寄件人索取。

:::sources
- [Singapore Post: Track Your Item](https://www.singpost.com/track-items) – 查询页、扫描顺序、原因说明、查无记录提示
- [Singapore Post: Tracking & Receiving Your Items](https://www.singpost.com/support/managing-deliveries/tracking-receiving-your-items) – 单号、带追踪服务、海外追踪、派送与取件
- [Singapore Post: Problems with Delivery](https://www.singpost.com/support/managing-deliveries/problems-delivery) – 理赔、赔付上限、签收凭证
- [Singapore Post: Collection Points](https://www.singpost.com/support/managing-deliveries/collection-points) – POPStation 规则
- [Singapore Post: Customs Clearance & GST Payments](https://www.singpost.com/support/managing-deliveries/customs-clearance-gst-payments) – GST 与关税、手续费、免税与防骗
- [Singapore Post: Help & Support](https://www.singpost.com/support) – 客服电话与服务时间
- [Singapore Post: Make an Enquiry](https://www.singpost.com/support/make-enquiry) – 在线咨询表单
- [Singapore Post: About Us](https://www.singpost.com/corporate/about) – 历史、220 个目的地
- [Singapore Post: Speedpost International Services](https://www.singpost.com/sending-overseas/speedpost-international-services) – 参考派送时效与赔付
- [Singapore Post: EU Customs Updates & DDP Guide](https://www.singpost.com/EU-customs) – 完税后交货（DDP）、下单时核算费用
- [Singapore Post: SingPost Streamlines Shipping to the EU](https://www.singpost.com/corporate/media-centre/singpost-streamlines-shipping-eu-new-customs-requirements-take-effect) – 16 个与 11 个欧盟国家、收件人无需上门缴税
- [Singapore Post: Online Security & You](https://www.singpost.com/online-security-you) – 诈骗短信与链接
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 3 欧元关税、2 欧元处理费
- [European Commission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 按项计费与“一项”的定义
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – 扫描文案、Peek 读取范围与下游邮政查询规则
:::
