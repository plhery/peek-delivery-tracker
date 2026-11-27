---
title: Swiss Post 瑞士邮政查询：单号、状态说明与取件通知
description: 查询 Swiss Post 瑞士邮政包裹：单号的样子、每个英文状态的含义、收到取件通知单后怎么办、海关以及如何联系瑞士邮政。
slug: swiss-post-tracking
published: 2026-11-24
updated: 2026-11-24
---

Swiss Post（瑞士邮政，德语 Die Post、法语 La Poste、意大利语 La Posta）是瑞士国家邮政，负责瑞士和列支敦士登境内的包裹派送。你可以在 [post.ch](https://www.post.ch/en/receiving-mail/track-consignments) 输入运单号免费查询，也可以直接把单号粘贴到上方查询框。单号由寄件人提供，请先查看商家的发货邮件。

## Swiss Post 运单号格式

单号由寄件人生成，通常在交寄之前。瑞士邮政未公布格式清单，以下是 Peek 识别的瑞士邮政专属格式：

| 单号（虚构） | 说明 |
| --- | --- |
| `RR482917367CH` | 两位字母、九位数字加 `CH`：瑞士签发的国际邮政单号。`CH` 前的最后一位是校验位。 |
| `99.48.291736.05841926` | 以 `98` 或 `99` 开头的 18 位数字，带或不带小数点与空格：瑞士邮政境内条码号。 |

建议直接复制粘贴而非手打：错一位、少一位或多一位都会让它看起来像其他快递的单号。以 `44` 开头的 18 位数字属于 Quickpac。托盘和其他货运件由瑞士邮政旗下的货运品牌 [Swiss Post Cargo](carrier:swiss-post-cargo) 承运，并有独立查询系统。来自境外的包裹进入瑞士后仍保留原国外单号（例如以 `DE`、`FR` 结尾）：比如来自法国商家的 [Delivengo](carrier:delivengo) 包裹仍使用以 `FR` 结尾的法国邮政单号。找不到单号？请看[如何查找运单号](guide:find-tracking-number)（英文）。

## Swiss Post 物流状态说明

post.ch 会按你选用的网页语言（英文、德文、法文或意大利文）显示每条扫描记录。以下是包裹通常会依次遇到的英文状态：

| Swiss Post 显示 | 中文含义 |
| --- | --- |
| `Your shipment will shortly be handed over to the Swiss Post` | 寄件人已预报包裹，瑞士邮政尚未收到实物。 |
| `Time at which your consignment was mailed` | 已交寄，附带交寄邮局信息。 |
| `Customs clearance process underway` | 境外来件正在瑞士邮政海关清关。 |
| `Completion of customs clearance process` | 已放行并转交瑞士境内派送。 |
| `Sorting - forwarding` | 在分拨中心分拣并转运至下一站。 |
| `Arrival at the delivery point` | 已到达负责你片区的派送网点。 |
| `Loading into delivery vehicle` | 已装车外出派送。 |
| `Delivered` | 已签收。 |
| `Delivered to the mailbox/letter box` | 已投递至信箱或其包裹格口。 |
| `Delivery failed: Recipient absent` | 无人签收。 |
| `Registered for collection` | 已留取件通知单，包裹正在网点等待你领取。 |
| `Deposited in the MyPost24 machine` | 已放入 My Post 24 自提柜等待取件。 |

`Registered for collection` 听起来像某种登记手续，实际意思是派送未成功并留下了取件通知单。`Consignment recorded by the foreign sender (data delivered)` 只表示境外寄件人发送了电子数据，包裹并未送达。`Returned item` 附带如 `Reason: Not collected` 等原因时，说明包裹正在退回寄件人。

## Swiss Post 派送未成功：取件通知单与自提

派送前一天，瑞士邮政会通过短信、邮件或 Post-App 推送发出通知。使用“My consignments”服务通常还会看到 40 至 120 分钟的预计送达时段。

**错过派送？** 你会收到取件通知单。根据瑞士邮政的规定：

- 次日起即可凭通知单或 Post-App 内的二维码，到通知单上注明的地点取件。
- 境内包裹通常保留 7 天，国际邮件保留 15 天。延长保管期限免费，且无需登录账号。
- 登录“My consignments”后，可以免费签发一次性代领授权让他人取件，也可以预约二次派送：指定安全放置点或交邻居代收均免费，周六派送收费 7 瑞士法郎。转投至 My Post 24 自提柜、邮政网点或瑞士及列支敦士登境内其他地址，工作日收费 8 瑞士法郎。

**派送之前，** “My consignments”支持免费改寄地址、免费改约其他工作日（改约周六收 3.80 瑞士法郎）或指定安全放置点。修改时间和地点的截止时间为午夜；超过该时间瑞士邮政不保证能生效。

**自提网络。** 瑞士邮政拥有超过 2,700 个 PickPost 自提点和超过 320 台全天候开放的 My Post 24 自提柜，均免费使用。

## Swiss Post 包裹延误、丢失或破损

**没有更新。** 在海关环节，瑞士邮政表示清关处理可能需要数天。其他情况可参考[为什么物流停止更新](guide:tracking-not-updating)（英文）。

**丢失。** 当缺少签收记录且已超出公布时效两天以上，或收件人确认未收到时，寄件人或收件人均可提交查件申请。瑞士邮政的受理期限为：境内交寄后一年内、国际邮件六个月内。对于国际包裹，其帮助页面明确由寄件人（通常是商家）负责发起查件。

**破损。** 须在签收后 8 天内在线申报、在 Post-App 中通过“Receive”>“Report damage”提交，或携带包裹及原包装前往邮政网点办理。

**进口费用。** 境外寄入的包裹需缴纳增值税、关税以及瑞士邮政的清关服务费，可在 Post-App 中在线支付。收费标准会调整，请查看[瑞士邮政进口清关说明](https://www.post.ch/en/receiving-mail/import-customs-clearance)及 Peek 的[海关指南](guide:customs)（英文）。

## Swiss Post 客服电话、在线聊天与 WhatsApp

post.ch 公布的联系方式如下：

- **电话：** +41 848 888 888（瑞士固话拨打每分钟 0.08 瑞士法郎），周一至周五 7:30 至 18:00，周六 8:00 至 12:00。
- **在线聊天：** 聊天机器人，背后有专员支持，入口位于德文、法文和意大利文联系页面。
- **WhatsApp：** 与电话、在线聊天并列，可用于修改派送指令。
- **查件表单：** 在[收件帮助页面](https://www.post.ch/en/help-and-contact/receiving-mail)可选择“Start a domestic enquiry”或“Start an international enquiry”。
- **网点办理：** 修改派送指令也可携带有效身份证件前往邮局网点办理。

> 瑞士邮政提醒警惕虚假包裹通知，例如声称包裹被“扣留”或要求点击链接支付小额关税/邮费。瑞士邮政绝不会通过短信或邮件索要密码、验证码或完整银行卡信息。查询包裹请仅使用 swisspost.ch 或官方 Post-App。

## 使用 Peek 跟踪 Swiss Post 包裹

[Peek](/) 会立刻识别上述两种单号格式，带点号或空格均可。也支持直接粘贴链接：以 `/entry/search/` 或 `/external/view/` 加单号结尾的 service.post.ch 链接，或带有 `formattedParcelCodes` 参数的 www.post.ch / mys.post.ch `/swisspost-tracking`、`/my-consignments/deeplink-search` 链接；其他 post.ch 链接仅在链接内包含上述格式单号时有效。无需邮编。在包裹详情页点击“打开 Swiss Post 官网”会直接在属于“My consignments”的 www.post.ch/swisspost-tracking 页面打开该单号。

Peek 会读取瑞士邮政的英文状态文本，如果你在 Peek 中使用德语、法语或意大利语界面，则会读取对应语言文本。页面会展示包裹状态、每次扫描的网点地图位置、预计派送日（如有，会附带时段）或预计日期区间、重量、尺寸、签收时间和目的地国家。Peek 不会显示包裹暂存的具体邮局或 My Post 24 自提柜名称，也不显示取件截止日，并会隐去你的姓名、地址、签名及派送说明。

`Delivered` 会显示为 Peek 的“已签收”。`Registered for collection` 和 `Deposited in the MyPost24 machine` 会显示为“待取件”。

在瑞士邮政官网正常响应期间，Peek 最高每 10 分钟检查一次；包裹派送当天，自瑞士时间早上 6 点起（若瑞士邮政给出了派送时段，则最早从该时段开始前半小时起）最高每 2 分钟检查一次（通过物流跟踪服务查询时，在派送当天打开页面或开启提醒时最高每 15 分钟检查一次），并支持“每次扫描”、“重要环节”或“仅派送当天”提醒。连续两天无新动态后（若仍处于“已生成面单”或“已揽收”，则为 12 小时后），Peek 改为每小时检查一次。[GLS Switzerland](carrier:gls-ch) 包裹和寄往瑞士的外国邮政单号可以延续到瑞士邮政跟踪；从瑞士寄往境外的 `CH` 单号，当 Peek 支持目的地国家邮政（例如德国、法国或意大利）且对方出现更新扫描时，也会继续跟踪。

## Swiss Post 快递查询常见问题

### Swiss Post 周六派送吗？

是的，前提是在“My consignments”中预约：事先选择收费 3.80 瑞士法郎，错过派送后作为二次派送预约收费 7 瑞士法郎。

### Swiss Post 显示“delivery date unknown”是什么意思？

说明瑞士邮政暂时无法确定送达日期，因为包裹来自境外、寄往境外，或寄件人已预报但尚未实际交寄。

### 什么是 Swiss Post 的派送授权？

共有两种：一是免费的存放指示（deposit order），告知瑞士邮政可将包裹安全放置在哪里；二是免费的一次性代领授权，允许他人代你领取指定包裹。两者都需要登录“My consignments”。

### 哪里可以找到最近的 Swiss Post 自提柜？

瑞士邮政的自提柜叫 My Post 24，全国共有 320 多台且全天 24 小时开放，可在 swisspost.ch/locations 查找附近点位。

:::sources
- [Swiss Post: Track consignments](https://www.post.ch/en/receiving-mail/track-consignments) – 查询入口、通知、派送时段与境外不保证说明
- [Swiss Post: Track consignments events](https://www.post.ch/en/receiving-mail/track-consignments/track-consignments-events) – 状态含义与 delivery date unknown 说明
- [Swiss Post: My consignments](https://mys.post.ch/my-consignments/) – 英文、德文、法文、意大利文扫描记录
- [Swiss Post: Collection note](https://www.post.ch/en/receiving-mail/collection-note) – 保管期限、代领授权与二次派送费用
- [Swiss Post: Manage and receive parcels](https://www.post.ch/en/receiving-mail/manage-consignment/manage-one-off) – 改寄、安全放置点、周六派送与午夜截止
- [Swiss Post: Questions and answers on receiving](https://www.post.ch/en/help-and-contact/receiving-mail) – 前一日通知、次日取件、WhatsApp 与查件表单
- [Swiss Post: Damage or loss](https://www.post.ch/en/sending-parcels/damage-or-loss) – 查件、时限与破损申报
- [Swiss Post: Consignments from abroad](https://www.post.ch/en/receiving-mail/import-customs-clearance) – 进口费用说明
- [Swiss Post: Help and contact](https://www.post.ch/en/help-and-contact) – 电话、服务时间与地址
- [Swiss Post: Hilfe und Kontakt](https://www.post.ch/de/hilfe-und-kontakt) – 在线聊天入口
- [Swiss Post: Recognize phishing and fraud](https://site.post.ch/en/about-us/security/phishing-and-fraud) – 虚假通知防骗提示
- [Swiss Post: Receipt locations](https://www.post.ch/en/receiving-mail/receipt-locations) – PickPost 自提点与 My Post 24 自提柜
- [Universal Parcel Scraper: Swiss Post](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/swiss-post) – Peek 读取的扫描文案
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Peek 读取的内容
:::
