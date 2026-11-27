---
title: DAI Post（Parcel Right）物流查询：单号、状态与清关说明
description: 查询澳大利亚、新西兰及新加坡的 DAI Post（Parcel Right）包裹：了解单号格式、状态含义、免签放门与自提规则及官方客服渠道。
slug: dai-post-tracking
published: 2026-11-27
updated: 2026-11-27
---

DAI Post 是一家面向网店的澳大利亚物流公司，于 2000 年创立于墨尔本，负责将来自美国、英国、欧洲和亚洲的包裹运送给澳大利亚、新西兰和新加坡的买家。其自有末端派送服务名为 Parcel Right。你可以在 DAI Post 的 [追踪页面](https://daipost.com/track) 查询，或直接把单号粘贴到上方输入框。单号位于商家的订单确认信息中；找不到请联系商家。

## DAI Post 追踪单号是什么样子

DAI Post 直接沿用商家贴在包裹上的编号，因此单号有多种格式，官网一次最多可查 10 个单号。Peek 代码已知的格式如下（数字均为虚构示例）：

| 单号（虚构示例） | 格式说明 |
| --- | --- |
| `NZC482910375` | `NZC` 加 9 位数字：在 Peek 代码中对应 DAI Post 发往新西兰的包裹，Peek 会立即识别为 DAI Post。 |
| `00393580944829103754` | 以 `0039358094` 开头的 20 位数字（末位为校验位）：即标签条码上的 SSCC 编码，标签上可能印作 `(00) 3 9358094 482910375 4`。其他承运商也使用 20 位数字，因此 Peek 无法仅凭形状确定。 |
| `KPR48291037561` | 商家自有参考号，字母后接数字。Peek 无法凭形状识别。 |

空格、括号和大小写均不影响查询。若 20 位数字输错任何一位，校验位就会失效，Peek 将不会将其关联至 DAI Post，请仔细核对单号。更多说明见 [如何查找追踪单号](guide:find-tracking-number)（英文） 和 [追踪单号格式](guide:tracking-number-formats)（英文）。

## DAI Post 物流状态含义解析

DAI Post 追踪页通常以轻松口语化的英文句子展示每个步骤，部分包裹则会显示同一节点的简短写法（如 `Checked in at origin hub`）。由于 Peek 读取底层节点代码，两种写法含义相同。按流转顺序，Peek 已知的状态如下：

| DAI Post 原文 | 含义 | Peek 显示 |
| --- | --- | --- |
| `We’ve received your delivery details. We’ll let you know as soon as your parcel is in our hands.` 或 `Customer label information received` | 商家已提交你的派送信息，DAI Post 尚未收到实物包裹。 | “已创建运单” |
| `Your parcel’s checked in and being prepared for its journey.` 或 `Checked in at origin hub` | 包裹已进入 DAI Post 始发集散中心。 | “承运商已揽收” |
| `We’ve processed your parcel at our origin facility. Next, on the move to you!` 或 `Parcel processed at origin facility` | 已完成分拣，准备启运。 | “运输中” |
| `There’s a departure delay. Thanks for your patience, we’ll update you soon.` 或 `There’s a transit delay. Thanks for your patience, we’ll update you soon.` | 航班起飞延误，或运输途中出现延误。 | “运输中” |
| `Your parcel is in the air and is heading your way.` 或 `Parcel departed origin country` | 已乘航班离开始发国。 | “运输中” |
| `Parcel arrived in trans-shipment country.`，随后 `Your parcel’s left the transfer country and is back on the move.` | 途中在第三国中转经停。 | “运输中” |
| `Touchdown! Your parcel has landed.` 或 `Parcel arrived in destination country` | 已抵达目的国。 | “运输中” |
| `We’ve collected your parcel from the airline and it’s being processed.` 或 `Collected from the airline for processing` | DAI Post 已从航空公司提货并开始处理。 | “运输中” |
| `Your parcel is being checked by customs. We’ll let you know when it’s cleared.` 或 `Parcel processed by clearance facility` | 正在接受海关查验。 | “清关中” |
| `Customs clearance delay` | 仍滞留在海关查验中。 | “清关中” |
| `All clear! Your parcel’s passed customs and is ready for the next leg.` 或 `Customs cleared` | 海关已放行，准备进入下一程。 | “运输中” |
| `We’ve got your parcel and it’s been sorted for delivery.` 或 `Parcel received and sorted` | 已收货并分拣待派。 | “运输中” |
| `Your parcel’s on an interstate journey. We’ll keep you updated as it travels.` | 正在跨州转运途中。 | “运输中” |
| `Your parcel’s arrived at our local facility, it's on the way.` | 已抵达你附近的本地分拨点。 | “运输中” |
| `Your parcel’s on its way to you, almost there!` | 正在前往派送站，尚未装车派送。 | “运输中” |
| `It’s happening, your parcel will be delivered today. Get ready!` 或 `Parcel out for final mile carrier delivery` | 今天正在派送途中。 | “派送中” |
| `Parcel unsafe to leave at address` | 司机认为现场不安全，未将包裹放下（见下文）。 | “派送未成功” |
| `Parcel available for collection` | 已送至自提点等待领取。 | “待取件” |
| `Delivered! Your parcel has arrived. Enjoy!` | 已送达。 | “已送达” |
| `Delivered, left where you can find it.` 或 `Parcel delivered - authority to leave` | 已按免签授权放在你的地址处。 | “已送达” |

若查询不存在的单号，DAI Post 官网会提示 `Sorry, we couldn't find your parcel.`。更多状态说明请参阅 [物流状态含义详解](guide:tracking-statuses)（英文）。

**时间戳。** DAI Post 按扫描所在地的本地时钟给出时间但不附带时区，Peek 会根据扫描地点推算时区并换算为你设备的本地时间，首条记录的小时数可能存在偏差。

## DAI Post 派送：免签放门、自提与退件

DAI Post 表示其网络覆盖澳大利亚 100% 的人口。具体送达时刻取决于司机当天的路线、货量及路况。

- **免签放门（Authority to leave）。** 无需签名。若家中无人且司机判断存放地点安全，会直接放下包裹。
- **不适合放门。** 如果地址过于暴露、不安全，包裹会送往附近最近的自提点，DAI Post 会向你发送取件指引。DAI Post 官网的 `Parcel available for collection` 节点附有地图链接，Peek 则仅显示城镇名称。
- **7 天取件期限。** 超过 7 天未领取的包裹会退回 DAI Post 仓库，再退回商家；地址信息不完整的包裹同样如此。
- **状态通知。** 只有商家向 DAI Post 提供了你的电子邮箱和手机号时，你才会收到关键节点通知。

DAI Post 条款补充称，如果你不在场，包裹可能被放在安全位置或转交自提点（Collection Point），并随即视为已送达。官网未说明新西兰或新加坡由哪家末端合作商派送。参阅 [错过派送](guide:missed-delivery)（英文） 和 [无人领取的包裹](guide:uncollected-parcel)（英文）。

## DAI Post 包裹清关说明

**海关暂扣（Customs held）。** DAI Post 帮助页说明，出现海关暂扣通常意味着澳大利亚边防局（ABF）或农业、渔业和林业部（DAFF）在放行前需要补充信息。请通过 DAI Post 客服确认所需资料，其咨询表单中设有 “My parcel is Customs held.” 选项。

**关税与费用。** 按 DAI Post 条款，运费及税费（包括税金和关税）由寄件人承担。如果收件人拒绝在交付时支付费用，寄件人仍负支付义务，DAI Post 可扣留包裹直至缴清或将其退回寄件人。是否需缴税取决于所在国法规，详见 [包裹被海关扣留指南](guide:customs)（英文）。如果收到可疑的催缴短信，请参阅 [防范快递诈骗短信](guide:delivery-scam-texts)（英文）。

## DAI Post 物流不更新、丢失或退回

**多日无新节点。** DAI Post 仅在关键枢纽扫描包裹，并表示包裹在两次扫描之间仍在继续移动；跨州或偏远地区派送可能连续数日看似静止。若包裹在“已创建运单”、“承运商已揽收”或“运输中”连续四天无更新，Peek 会标出最后一条记录日期并提供“向 DAI Post 获取帮助”链接。详见 [物流为何停止更新](guide:tracking-not-updating)（英文）。

**一直停在“已创建运单”。** 说明 DAI Post 尚未收到包裹。如持续多日，请询问商家是否确已寄出。

**显示已送达但没找到。** 启用免签放门时请先在住所周边仔细寻找。你可通过 DAI Post 咨询表单申请将免签投递证明发到你的邮箱，随后联系商家。详见 [显示已送达却未收到包裹](guide:delivered-not-received)（英文）。

**丢失或退件。** 包裹始终未到可在 DAI Post 咨询表单中选择 “I did not receive my parcel.”。无论哪种情况，运输均由商家预订，申请退款或补发请联系商家。详见 [包裹丢失](guide:lost-parcel)（英文） 和 [退回寄件人](guide:returned-to-sender)（英文）。

## DAI Post 客服与联系方式

DAI Post 官方渠道如下：

- **电话：** 澳大利亚 +61 3 8586 1200，新西兰 +64 800 451 718，新加坡 +65 800 321 1587。官网未标注服务时间。
- **电子邮箱：** consumercare@daipost.com。
- **在线表单：** [咨询表单](https://daipost.com/contact)，需填写姓名、电话、邮箱、追踪单号和咨询事由。

## 使用 Peek 追踪 DAI Post 包裹

在 [Peek](/) 中只需单号即可追踪 DAI Post，无需账号或邮编：

- **`NZC` 单号。** Peek 会立即识别为 DAI Post（“识别出的快递公司”）。如果首次查询两天后 DAI Post 仍不识别该单号，Peek 会提示你核对单号、更换承运商或删除包裹。
- **以 `0039358094` 开头的 20 位单号。** 照常粘贴即可（Peek 支持超过 4,500 家承运商）。查询时会显示“正在查询物流服务…”，DAI Post 会位列首批查询对象；查到后即显示 DAI Post，若尚未有扫描则会显示“等待 DAI Post 的首次扫描”及“可能是 DAI Post”，满一周后建议向寄件人核对单号。如需手动选择，点击“更改”可在“符合这个单号”下找到它。
- **其他任意单号。** 粘贴后点击“更改”并选择 DAI Post（搜索 DAI Post 或 Parcel Right）。已添加的包裹使用“更换承运商”。
- **追踪链接。** 粘贴来自 daipost.com、parcelright.com.au 或 parcelrig.ht 的追踪链接，Peek 会立即提取单号并识别为 DAI Post。

Peek 会展示当前状态、DAI Post 原文轨迹、每次扫描的城镇与国家、预计送达日（官网提供时）以及签收时间；不显示自提点名称、服务名、重量或签收凭证。点击“打开 DAI Post 官网”可直达官方追踪页。在 Peek 代码中，部分 [Asendia](carrier:asendia) 包裹以及部分发往新西兰的 [ePost Global](carrier:epost-global) 包裹会经由 DAI Post 转运，其轨迹也会包含 DAI Post 的扫描记录。

当你打开包裹，或在开启通知的情况下进入派送日时，Peek 最高每 10 分钟检查一次；在 DAI Post 扫描进入派送当天，自当地时间早上 6 点起提高至每 2 分钟一次。连续两天无新动态时（处于“已创建运单”或“承运商已揽收”时为 12 小时）改为每小时检查一次；在“已创建运单”下满七天无新动态则每 6 小时检查一次。通知可按“每次扫描”、“重要节点”或“仅派送和送达”设置。Peek 无法代替你联系 DAI Post 或商家。

## DAI Post 追踪常见问题

### Parcel Right 和 DAI Post 是一回事吗？

Parcel Right 是 DAI Post 自有的末端派送服务。在 Peek 中搜索这两个名称中的任意一个都能找到。

### “Parcel unsafe to leave at address” 是什么意思？

说明司机上门时无人应答，且现场环境不适合安全放置包裹。包裹会被送往附近最近的自提点，请在 7 天内领取，否则将退回商家。

### DAI Post 派送一般需要多久？

DAI Post 条款写明标准时效仅为预估而非保证。官网追踪页有时会显示预计送达日期，Peek 也会同步展示。

:::sources
- [DAI Post: Home](https://daipost.com/) – 墨尔本、2000 年创立、欧美亚发往澳大利亚/新西兰/新加坡包裹、Parcel Right
- [DAI Post: Help and support](https://daipost.com/help-and-support) – 电话、覆盖率、时效、无近期更新、通知、免签放门、自提、退件、海关暂扣、投递证明
- [DAI Post: Delivery tracking](https://daipost.com/track) – 官方追踪页、最多同时查 10 个单号
- [DAI Post: Contact](https://daipost.com/contact) – 咨询表单与事由、电话、consumercare@daipost.com
- [DAI Post: Terms & conditions](https://daipost.com/terms) – 安全放置或自提点、预估时效、税费责任
- [Universal Parcel Scraper: DAI Post](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/dai-post) – 名称、单号格式、链接、状态措辞与阶段、时区、预计送达日
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – 识别规则、Asendia 与 ePost Global 复用 DAI Post 扫描记录
:::
