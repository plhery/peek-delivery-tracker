---
title: ePost Global 快递查询：EPG 单号、物流状态与末端派送
description: 查询 ePost Global 包裹：EPG 运单号格式、官方与合作承运商物流状态含义、欧盟关税规则、客服联系方式以及 Peek 自动衔接末端派送说明。
slug: epost-global-kuaidi-chaxun
published: 2026-11-24
updated: 2026-11-24
---

ePost Global 是一家美国跨境物流公司，其运单号以 `EPG` 开头。从 Peek 掌握的历史轨迹来看，包裹先在美国境内的一处 ePost Global 处理中心扫描入库，随后交由各国邮政或快递合作伙伴继续运输和派送。运单号由商家或寄件人提供，直接粘贴到上方输入框即可查询；ePost Global 自家官网查件入口在 epgtrack.com。Peek 快递目录同时将 ePost Global 关联至 PPX Track，以及 RR Donnelley、RRD Donnelley 和 RRD International Logistics 等别称，在 Peek 里搜索任意一个名称都能找到 ePost Global。

## ePost Global 运单号是什么样的？

Peek 收录了 ePost Global 的一种标准格式：`EPG` 后跟 10 至 15 位数字。以下数字均为虚构：

| 单号示例（虚构） | 说明 |
| --- | --- |
| `EPG482910573649182` | `EPG` 加 15 位数字：标准 ePost Global 单号。 |
| `EPG4829105736` | `EPG` 加 10 位数字：同样属于 ePost Global，是 Peek 接受的最短长度。 |
| `EPG482910573` | 只有 9 位数字：不符合 ePost Global 格式。3 个字母加 9 位数字匹配 Purolator 和 TNT Australia 等承运商，因此 Peek 会向 Purolator 与 TNT Australia 核实谁有记录；若手动选定 ePost Global，Peek 会提示“不使用这种格式的单号”。请检查是否漏输一位数字。 |
| `EPG4829105736491827` | 16 位数字，多了一位：Peek 无法识别任何承运商，请检查是否多输了数字。 |
| `LZ482910576US` | 两位字母加 9 位数字再加 `US`：美国邮政单号。大多数以 `C`、`E`、`L` 或 `R` 开头的此类单号会被 Peek 识别为[美国邮政（USPS）](carrier:usps)。若想同时查看 ePost Global 早期轨迹，请添加 `EPG` 单号。 |

往 Peek 粘贴单号时，空格、连字符和字母大小写均不影响识别。

**单号在哪里找：** 请查看商家的发货确认邮件或订单详情页。如果找不到，可参阅[如何查找运单号](guide:find-tracking-number)（英文）和[运单号格式说明](guide:tracking-number-formats)（英文）。

## ePost Global 物流状态详解

ePost Global 查询页每条轨迹均带有时间、状态描述以及大多数情况下的地点。其自有环节通常按以下顺序出现：

| ePost Global 原文 | 中文含义 | Peek 显示阶段 |
| --- | --- | --- |
| `Data Received` | 商家已向 ePost Global 发送预报信息，但对方尚未收到包裹。 | 已生成面单 |
| `Shipment in Transit to the ePost Global Processing Center` | 每次都会在预报后一分钟自动生成，属于建单环节，并不表示包裹已实际发运。 | 已生成面单 |
| `Parcel Received and Processing` | 包裹已送达 ePost Global 美国处理中心并开始处理，轨迹会写明所在城市和州。 | 已揽收 |
| `Parcel Shipped` | 包裹已离开处理中心发出。 | 运输中 |
| `In transit to … tracking #: …` | 发出后一分钟即交接给后续合作伙伴，原文会写明合作服务名称及其新单号，有时也写作 `In transit via …` 或 `tracking#:`。 | 运输中 |
| `In transit` | 同样表示交接，但未注明合作服务名称或新单号。 | 运输中 |

**交接后的合作伙伴轨迹：** 完成交接后，ePost Global 查询页会转发后续承运商的扫描记录。Peek 代码已识别的常见合作轨迹包括：

| 交接后状态原文 | 中文含义 | Peek 显示阶段 |
| --- | --- | --- |
| `Shipping label created - OSM awaiting item` | 合作方 OSM Worldwide 已收到包裹预报信息。 | 阶段不变 |
| `Arrived at OSM facility`、`Departed OSM facility` | 到达或离开 OSM Worldwide 处理设施。 | 运输中 |
| `Package accepted at courier partner`、`Package processed by courier partner` | OSM Worldwide 的末端派送伙伴已接收或处理包裹。 | 运输中 |
| `Parcel has arrived at export distribution warehouse` | 合作方 [ECMS Express](carrier:ecms-express) 已在出口分拨仓收到包裹。 | 运输中 |
| `Customer Information Manifested` | 新西兰合作方 DAI Post 已收到预报信息。 | 阶段不变 |
| `Checked in at origin hub` | 已抵达 DAI Post 美国起运枢纽。 | 运输中 |
| `Airline Delay` | 航班延误，属于航空排班延迟，不代表包裹本身受损。 | 运输中 |
| `Collected from the airline for processing` | 已下机并进入目的国处理流程。 | 运输中 |
| `Consignment received at the PostNL Acceptance Centre`、`The item has been manifested and ready to transport` | PostNL 经由 Spring 接收包裹并列入待运清单。 | 运输中 |
| `The item is tied to a MAWB and ready for shipment` | PostNL 经由 Spring 将包裹配载至航空总运单，准备起运。 | 运输中 |
| `Your item has been dropped off by the sender with our postal partner in the country of origin.`、`Your item is ready to be forwarded from its foreign territory. It will be given to the carrier for forwarding.` | 法国邮政针对 PostNL 运往法国包裹的提示，表示包裹仍在发往法国途中。 | 运输中 |
| `The parcel has left the parcel center.`、`The parcel has reached the parcel center.` | GLS 欧洲网络：包裹离开中转枢纽，随后抵达目的国包裹中心。 | 运输中 |
| `The parcel is expected to be delivered during the day.` | GLS 欧洲网络：快递员今天正在派送。 | 派送中 |
| `The parcel has been delivered.` | GLS 欧洲网络：已签收送达。 | 已签收 |
| `Departed Hub`、`Shipment Received in Depot`、`Shipment Departed Depot` | 英国[皇家邮政（Royal Mail）](carrier:royal-mail)派送前的英国合作方轨迹：离开枢纽、清关前后入出网点、发往投递局。 | 运输中 |
| `Arrived in customs` | 同一英国合作方：进入英国海关（与网点入库同分钟记录）。 | 清关中 |
| `Ready for Collection` | 同一合作方：包裹已到达自提点待领取。 | 待取件 |

`Parcel Shipped` 之后出现合作方收件扫描时，Peek 会归类为“运输中”而非重新开始。其他合作方轨迹（如签收记录）会按 Peek 通用措辞规则判别阶段；遇到未收录的描述时保留原文并归为“运输中”，但如果包裹已处于“派送中”或更靠后阶段，则保持原阶段不变。

## 谁负责 ePost Global 的末端派送？

ePost Global 称其邮政与末端快递合作网络覆盖六大洲。在 Peek 掌握的轨迹记录中，其合作方包括 OSM Worldwide、负责新西兰的 DAI Post、ECMS Express、GLS 欧洲网络、英国的皇家邮政，以及经由 Spring 承运的 PostNL（进入法国后由[法国邮政（La Poste）](carrier:la-poste)接手）。当 ePost Global 未写明合作方名称但给出国际邮政单号（两位字母 + 九位数字 + 两位字母）时，Peek 会向目的国邮政查询，例如[美国邮政（USPS）](carrier:usps)、[英国皇家邮政（Royal Mail）](carrier:royal-mail)、爱尔兰邮政（An Post）、加拿大邮政（Canada Post）或[澳大利亚邮政（Australia Post）](carrier:australia-post)，详见下文。

**派送尝试与自提：** 派送次数以及错过派送后的存放地点完全遵从末端承运商的规则，请持合作方单号直接联系该承运商。另见[错过派送怎么办](guide:missed-delivery)（英文）和[逾期未取包裹](guide:uncollected-parcel)（英文）。

**欧盟海关与税费：** 从欧盟境外网购寄往欧盟的商品，自 2026 年 7 月 1 日起对货值不超过 150 欧元的包裹按每项商品征收 3 欧元关税，自 2026 年 11 月 1 日起无论货值高低均按每项商品加征 2 欧元欧盟统一处理费。“一项商品”指共享同一税则分类、品名描述及（如需）原产地的货品。在欧盟增值税区内，如结账时未通过 IOSS 预缴增值税，还需缴纳进口增值税。ePost Global 说明无论是 DDP 还是 DDU 渠道均适用该关税与处理费：DDP 下由发件方在抵境前付清，收件时无需再缴；DDU 下由收件人在收货时支付。德国已不再接收 DDU 包裹，ePost 也已于 10 月 26 日停用发往德国的 DDU 路由；其他国家需缴金额视当地规定而定。另见[包裹滞留海关怎么办](guide:customs)（英文）。

## 包裹无更新、丢失或退回怎么处理？

**暂时查不到：** 当 ePost Global 官方查询页尚未显示任何更新时，Peek 会显示“等待 ePost Global 更新”并持续查询。请核对单号是否为 `EPG` 加 10 至 15 位数字。如果两天后 ePost Global 仍查不到记录且综合跟踪服务也无结果，Peek 会提示：“两天过去了，快递公司仍查不到这个单号。请检查单号、更换快递公司，或删除这个包裹。”若几天后依旧无动静，请向商家确认是否已实际交寄。

**交接后没新动态：** 包裹此时已移交末端合作方，合作方官网轨迹往往比 ePost Global 更新更及时。Peek 会在条件支持时自动衔接合作方查询（见下文）；否则请从交接记录里找到合作方名称与转单号前往其官网查询。

**连续多日无更新：** 若连续四天无新轨迹，Peek 会标出最新扫描日期（如“自 10 月 12 日起没有物流更新”），提示包裹在长途航班、海关或周末时常暂停更新（超过三周无更新则建议联系卖家），并附上“向 ePost Global 寻求帮助”链接（若 Peek 已切换至已知帮助页的末端合作方，则显示该合作方的帮助链接）。另见[物流停止更新怎么办](guide:tracking-not-updating)（英文）。

**显示已签收但没收到：** 先联系末端合作承运商核实，再联系商家。另见[显示已签收但未收到](guide:delivered-not-received)（英文）。

**丢失或退回：** 请直接联系商家或购物平台申请退款或重发。另见[包裹丢失怎么办](guide:lost-parcel)（英文）和[包裹退回寄件人](guide:returned-to-sender)（英文）。

## ePost Global 客服与联系方式

ePost Global 官网 epostglobalshipping.com 列有免费电话 +1 866-784-8444 和本地电话 +1 310-784-8485，未注明服务时间，也没有公布客服邮箱。在线客服助手 ePost Assist 位于 epostassist.epgtrack.com，其支持页面（contact-support）也提供在线工单表单。当包裹长时间无动态时，Peek 的“向 ePost Global 寻求帮助”按钮会直接打开 ePost Global 的在线支持表单页。

网购订单建议优先联系商家，因为运输服务由商家预订；包裹交接后涉及具体派送事宜，则直接联系末端承运商。

## 用 Peek 查询和跟踪 ePost Global 包裹

只要输入 `EPG` 加 10 至 15 位数字，[Peek](/) 会立即识别出 ePost Global（显示“识别出的快递公司”），并仅凭单号向其官方查件系统拉取数据，无需邮编或账号。也可以直接粘贴 ePost Global 或 PPX Track 的查件链接，Peek 会自动提取其中的单号（若含多个单号则取第一个）。如果手头只有末端合作方的单号，也照样可以粘贴：Peek 支持查询 4,500 多家快递公司。

Peek 会展示物流状态、每条扫描记录及其地点、目的国家，以及 ePost Global 给出的合作承运商名称与转单号，略去官网进度条、邮编和航空总运单号。由于 ePost Global 官网本身不提供预计送达日期、重量和签收凭证，Peek 也不显示这些字段。包裹页面里的“打开 ePost Global 官网”链接可直接带着你的单号跳转官方查件页。

ePost Global 官网显示的时间不含时区信息。Peek 会按处理中心所在州的时钟解析 `Parcel Received and Processing` 与 `Parcel Shipped`（前提是该州使用单一时区），GLS 轨迹按中欧时间、英国皇家邮政按英国时间，其余记录按发生地或所属航段时钟换算，并全部转换到你设备的本地时区；在 peektracker.com 及 iPhone、Android 应用中，物流历史下方会标明“时间按你所在的时区显示：”及其时区名称。

当 ePost Global 给出 Peek 已收录的末端合作方时，包裹卡片会加以标示（例如“由 PostNL 派送”，若合作方仅收到预报尚无实物扫描则显示“PostNL 已收到预报，尚未收到包裹”），并在 EPG 单号后列出合作方单号。随后 Peek 也会直接向该合作方查询（有转单号则用转单号，没有则用 EPG 单号）。若未写明合作方，Peek 会对国际邮政单号询问目的国邮政，对其他单号询问格式唯一对应的快递公司（仅限 Peek 支持直连官网的承运商）。一旦末端承运商出现不早于 ePost Global 最新记录的扫描，Peek 便无缝切换至末端承运商继续跟踪并保留 ePost Global 的前期轨迹；否则仍沿用 ePost Global 转发的合作方记录。通过 GLS 欧洲网络派送的包裹亦是如此，因为 ePost Global 不会写明具体由哪一国的 GLS 分公司派送。

当你打开包裹页面或在派送当天开启提醒，且 ePost Global 官方查询页正常响应时，Peek 最高每 10 分钟检查一次，在包裹进入派送当天自当地时间早上 6 点起最高每 2 分钟检查一次；若仅通过综合跟踪服务获取更新，则在派送当天打开页面或开启提醒时最高每 15 分钟检查一次；其余情况每小时检查一次。交由末端承运商接手后，Peek 按该承运商的节奏检查。连续两天无新动态（或在“已生成面单”、“已揽收”状态下满 12 小时），Peek 改为每小时检查一次。你可以设置“每次扫描”、“重要环节”或“仅派送当天”通知。Peek 无法代你联系 ePost Global 或商家。

## ePost Global 查询常见问题

### PPX Track 是什么？

PPX Track 是 Peek 快递目录里对应 ePost Global 的另一个名称。把 PPX Track 的查件链接粘贴到 Peek 时，会自动按 ePost Global 链接解析并提取单号。

### ePost Global 状态中的 “In transit to” 是什么意思？

表示 ePost Global 已将包裹交接给下一程合作承运商。该行会写明合作方服务名称，并在 `tracking #:` 后面给出合作方自己的运单号。

### 为什么 ePost Global 一直停在 “Data Received”？

这说明商家已将包裹信息传给 ePost Global，但 ePost Global 任何一处处理中心都还没扫描收到实物。请向商家核实包裹何时交付发出。

### 包裹到达我的国家后由谁来派送？

由 ePost Global 交接记录中写明的合作承运商负责。在 Peek 收录的轨迹里，常见合作方包括 OSM Worldwide、新西兰的 DAI Post、ECMS Express、GLS 欧洲网络、英国皇家邮政、经由 Spring 承运的 PostNL 以及法国邮政。

### 如何联系 ePost Global 客服？

建议先联系下单商家，因为运输服务由商家预订。如需直接联系 ePost Global，可拨打官网列出的 +1 866-784-8444 或 +1 310-784-8485、使用 epostassist.epgtrack.com 的 ePost Assist，或点击 Peek 在滞留包裹上显示的“向 ePost Global 寻求帮助”打开官方支持表单。

:::sources
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 自 2026 年 7 月 1 日起 150 欧元以内每项 3 欧元关税、自 2026 年 11 月 1 日起每项 2 欧元欧盟统一处理费
- [European Commission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 不分货值每项商品 2 欧元及“项”的认定标准
:::
