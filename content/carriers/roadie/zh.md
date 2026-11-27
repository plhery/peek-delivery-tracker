---
title: Roadie 物流查询：1R 单号格式、状态与送达照片
description: 查询美国 Roadie（UPS 旗下）当日达与次日达配送：了解 1R 单号格式、追踪状态含义、邮编解锁送达照片以及客服联系方式。
slug: roadie-tracking
published: 2026-11-26
updated: 2026-11-26
---

Roadie 是 UPS 旗下的物流公司，依托独立司机网络在全美提供当日达及同城配送服务。订单预订后 Roadie 会向你发送邮件；一旦司机揽收包裹，Roadie 会通过邮件、短信或两者同时发送追踪链接。你可以将单号或该链接粘贴到上方输入框，或在 Roadie 的追踪页 track.roadie.com 上查看。

## Roadie 追踪单号格式与查找方式

请查看 Roadie 发送的邮件和短信，追踪链接里就包含你的单号。许多商家也会给出自家的订单/追踪号，而 Roadie 说明其他格式的单号无法在 track.roadie.com 上查询：此类单号需向商家查询对应物流。更多方法见 [如何查找追踪单号](guide:find-tracking-number)（英文）。

Roadie 单号均以 `1R` 开头，以下数字为虚构示例：

| 单号（虚构示例） | 格式说明 |
| --- | --- |
| `1R00000000000000` | 16 位字符：`1R` 后接 14 位数字或字母 a 至 f。Roadie 帮助中心称之为数字与小写字母的组合（`1r…`）。 |
| `1R00000000000000000000000000` | 28 位字符：`1R` 后接 26 位字母或数字。Roadie 追踪页同样支持该长度。 |
| `1R00-0000-0000-0000` | 将 16 位单号按每 4 位分组，即 Roadie 追踪页展示的排版形式。 |

Roadie 追踪页仅接受 16 位或 28 位单号，若查无记录会提示 “Delivery not found. Please confirm that your tracking number is correct.”。在 Peek 中粘贴时，空格、连字符和大小写均不影响查询。

Roadie 虽隶属于 [UPS](carrier:ups)，但单号并非 UPS 格式：UPS 单号以 `1Z` 开头，而 Roadie 单号以 `1R` 开头。

## Roadie 物流状态含义解析

Roadie 追踪页按最新时间倒序排列各步骤，每步附带时间，配送进行中还会显示预计送达时间：

| Roadie 步骤原文 | 对你的含义 |
| --- | --- |
| `Delivery Created` | 商家已向 Roadie 预约配送，尚未有司机接单取货。 |
| `Pickup Confirmed` | 司机已完成取货并启程送货，此时 Roadie 会向你发送追踪链接。 |
| `Delivered` | 司机已完成送达，追踪页可查看送达照片（见下文）。 |
| `Delivery Attempted` | 司机尝试派送但未能完成。页面可能注明原因，如无法进入地址、无人签收、无人接收或没有安全放置点。 |
| `Transferred to Carrier` | Roadie 已将货物转交给其他承运商，页面会显示该承运商的新单号（若转交 UPS 还会附带 UPS 追踪链接）。 |
| `Delayed` | 配送延误。Roadie 追踪页提示稍后会更新新的送达日期。 |
| `Canceled` | 本次配送已取消。页面可能说明原因（订单取消、商品缺货、已经送达或订单丢失），请联系商家。 |
| `Expired` | 与取消一样，Roadie 页面会显示提示且不列步骤，请联系商家了解情况。 |

**邮编锁定（ZIP code lock）。** Roadie 追踪页默认隐藏收货地址、送达照片和下一程承运商单号，直到你在 “Unlock Delivery Details” 下输入收货邮编（ZIP code）才会解锁。送达照片在签收后保留 14 天。

## Roadie 配送方式：司机、拍照与签收

**配送人员。** 由 Roadie 独立司机网络承运；Roadie 称其拥有超过 31 万名司机，覆盖全美 50 个州 97% 的家庭。

**时效。** Roadie Same Day 当日达在取件点 100 英里范围内需 2 至 12 小时送达；面向企业履约中心批量发货的 RoadieXD 需 6 至 24 小时。Roadie 在取货当天完成派送，因此本地次日达订单最晚可能在派送当天早上才由司机取货。派送时段由商家选定，分为 2 小时、4 小时或当日结束前。

**照片凭证。** 每次送达都会拍摄一张照片，展示货物外观以及前门或放置点，以证实送达了正确的住宅或楼宇。司机严禁将收件人拍入画面。

**签收要求。** 除非商家要求签收，否则你不必在家等候。若需签收，你或该地址的任意人员均可签字，应用会记录签收人姓名。处方药订单必须由当面接收者签收，部分订单（如 CVS 处方配送）可能需要有人在场。

**放置位置。** 无需签收时，司机会把货放在收货地址的安全处：从街道看不见、有遮挡且你会查看的位置（如前门、侧门或包裹区）。若无安全位置，司机会设法当面交给你或交至前台/物业办公室。司机不得投递进美国邮政专用的信箱（mailbox），也不会进门入室：Roadie 表示司机不是搬运工，不会跨入室内。

**谁在使用 Roadie。** Roadie 自己的网站列出的客户企业包括 The Home Depot、Tractor Supply、Walmart、BJ's Wholesale Club、Fanatics 和 Benjamin Moore，同时它也替达美（Delta）等航空公司派送延误行李。

## 修改 Roadie 配送安排

能否修改取决于司机是否已经取货：

- **取货前：** 修改收货地址请联系 Roadie Support；希望推迟配送时间请联系商家重新排期。
- **取货后：** Roadie 无法修改地址、推迟派送或更改时间，请联系商家协助处理。
- **签收设置：** 如果原本要求签收但改变了主意，请联系 Roadie Support。

若订单要求签收而现场无人应答，货物会退回商家，你需要联系商家重新预约配送。详见 [派送未成功](guide:missed-delivery)（英文）。

## Roadie 配送延误、丢失或显示已签收却未收到

**延误或停滞。** 若页面显示 `Delayed`，Roadie 表示很快会提供新日期；如果迟迟无更新请联系商家。更多见 [物流停止更新](guide:tracking-not-updating)（英文）。

**显示已签收但没找到。** 请打开 Roadie 追踪页输入邮编，查看保留 14 天的送达照片以确认放置位置，并检查侧门、门廊、车库附近以及楼宇前台或租赁办公室，随后联系商家。Roadie 说明除非另行同意，否则退款或补发均由商家处理而非 Roadie。若怀疑失窃，Roadie 要求提供警方报案记录，且许多警局要求由寄件人报案。参阅 [显示已送达却未收到](guide:delivered-not-received)（英文） 和 [包裹丢失](guide:lost-parcel)（英文）。

**破损。** 请立即通知商家：只有寄件人能向 Roadie 提交理赔，通常须在送达后 3 个工作日内提出。理赔结案前请原样保留商品及全部包装；若理赔有效，Roadie 会通过邮件联系你跟进。如司机损坏了你的私人财物，Roadie 同样要求你在送达后 3 个工作日内告知寄件人。详见 [包裹破损](guide:damaged-parcel)（英文）。

## Roadie 客服与联系方式

以下渠道公布于 Roadie 支持网站：

- **在线聊天：** 点击 Roadie Support Center 页面右下角的机器人图标即可转接客服专员。
- **电子邮箱/表单：** 通过 Roadie 追踪页上的 “Contact Support” 链接打开联系表单，填写姓名、邮箱和电话，并在 “How can we help?” 下提供 “Track My Delivery” 选项。
- Roadie 说明其客服团队通过在线聊天和邮件同时服务收件人、寄件人和司机。

联系前请备好追踪单号。退款、补发或重新约期请联系预订配送的商家。

## 使用 Peek 追踪 Roadie 配送

在 [Peek](/) 中粘贴上述任一格式的 `1R` 单号（`1R` 加 14 位数字/字母 a–f，或 `1R` 加 26 位字母/数字），Peek 会立即识别为 Roadie。来自 track.roadie.com 的链接（`/id/…`、`/track/…`、`/track?tracking_number=…`，含前置合作商名称的链接）同样直接识别。

Peek 读取 Roadie 自己的追踪页并按上表原文展示步骤，同时可显示司机进度（例如 `Driver at pickup` 或 `Driver approaching delivery`）；一旦司机接近或到达你的地址，状态会切换为“派送中”。由于 Roadie 节点不提供地点，地图不会标注位置，Peek 也不显示预计送达时间。在 Roadie 收录单号前，Peek 会显示“等待 Roadie 更新”；在 Roadie 回复查无此单满两天后，Peek 会提示你核对单号。若 Roadie 的追踪页没有响应，Peek 会改向综合追踪服务查询并按首个查到结果的服务原文展示。

Peek 不会展示送达照片、司机信息或邮编锁后的其他内容：点击包裹页上的“打开 Roadie 官网”即可直接进入 Roadie 的追踪页解锁查看。若当前轨迹来自综合追踪服务，该链接会指向对应服务页面（如“打开 ParcelsApp 官网”），查看送达照片请自行打开 track.roadie.com。

当出现 `Transferred to Carrier` 时，Peek 无法从 Roadie 页面直接获取下游承运商单号。请在 Roadie 的追踪页输入邮编解锁新单号后再添加到 Peek：若是校验位有效的 `1Z` 单号，Peek 会立刻识别为 UPS。

当你在 Peek 中打开包裹页面，或在派送当日开启通知时，Peek 最多每 10 分钟检查一次 Roadie 的追踪页；在配送变为“派送中”当天，只要 Roadie 自己的追踪页有响应，会从 Roadie 最新时间戳所在时区的早上 6 点起每 2 分钟检查一次。其余情况下，以及连续两天没有新动态后（包裹仍显示“已生成面单”或“已揽收”时为 12 小时），Peek 每小时检查一次。Peek 可以按“每次扫描”、“重要环节”或“仅派送当天”提醒你。Peek 无法代你联系 Roadie 或商家。

## 关于 Roadie 物流查询的问题

### Roadie 属于 UPS 吗？

是的。Roadie 自称是 UPS 的全资子公司，其追踪页也标注 “Roadie, a UPS company”。但它的单号以 `1R` 开头，而不是 UPS 的 `1Z`。

### 为什么我的 Roadie 追踪单号查不到？

Roadie 单号为 16 位或 28 位且以 `1R` 开头，Roadie 的追踪页不接受其他长度。Roadie 指出商家自带的其他格式单号在那里无法使用，请向商家查询对应物流。

### 收 Roadie 包裹必须在家等吗？

不需要，除非商家要求签收。免签订单司机会放在住所安全处并拍照留证；需签收时，你或同地址任何人均可签收，若无人应答货物将退回商家。

### Roadie 夜间和周末派送吗？

可以。Roadie 说明企业可通过其平台全天候 24/7 派送，涵盖非工作时间与节假日。具体送达时间取决于商家选择的时段，Roadie 的追踪页会显示预计送达时间。

### Roadie 货物丢失了应该找谁？

联系下单的商家：Roadie 表示收件人的退款和补发由寄件人负责。有关派送本身的问题，Roadie Support 提供在线聊天和邮件支持。

:::sources
- [Roadie: Last-mile delivery solutions (home page)](https://www.roadie.com/) – 独立司机、覆盖率、配送时段与航空行李
- [Roadie: Same-Day Delivery](https://www.roadie.com/solutions/same-day) – Roadie Same Day 与 RoadieXD 时效、100 英里、预计送达与企业客户
- [Roadie: Local Next-Day Delivery](https://www.roadie.com/solutions/next-day-delivery) – 次日达订单当天揽收当天派送
- [Roadie: Roadie for UPS Customers](https://www.roadie.com/solutions/roadie-for-ups) – UPS 子公司、24/7 全天候派送
- [Roadie Support: Recipient: Understanding your Roadie delivery](https://support.roadie.com/support/article/Recipient-Understanding-your-Roadie-delivery) – 邮件与短信、签收、安全放置点与修改规则
- [Roadie Support: Sender order management: Tracking deliveries](https://support.roadie.com/support/article/How-Do-I-Track-a-Delivery) – 16 位单号、商家自有单号说明
- [Roadie Support: Recipient: Where to find Roadie](https://support.roadie.com/support/article/Where-is-Roadie-Available) – 覆盖全美 50 州
- [Roadie Support: Driver Gig logistics: Using Proof of Delivery (POD) during a Gig](https://support.roadie.com/support/article/Driver-Gig-logistics-Taking-photos-during-a-Gig) – 送达照片要求
- [Roadie Support: Driver Gig logistics: Leaving packages unattended](https://support.roadie.com/support/article/Driver-Gig-logistics-Leaving-packages-unattended) – 安全放置位置与前台
- [Roadie Support: Driver Gig Logistics: Issues at Delivery](https://support.roadie.com/support/article/Issues-at-delivery) – 禁止使用邮政信箱
- [Roadie Support: Sender rules: Understanding delivery signatures](https://support.roadie.com/support/article/What-Do-Delivery-Signatures-Mean-for-Senders) – 签收人资格与姓名记录
- [Roadie Support: Driver policies: Understanding important terms](https://support.roadie.com/support/article/Understanding-important-terms) – 处方药签收规定
- [Roadie Support: Sender order management: Get to know the Roadie Protection Plan](https://support.roadie.com/support/article/Roadie-Protection-Plan) – 理赔归属寄件人、不跨入门槛
- [Roadie Support: Claims and insurance: Filing a claim with Roadie](https://support.roadie.com/support/article/How-to-file-a-claim-with-Roadie) – 寄件人理赔、3 个工作日时限、财产损害与包装保留
- [Roadie Support: Sender rules: Learn about theft](https://support.roadie.com/support/article/Theft-FAQs) – 警方报案与寄件人退款
- [Roadie Support: Payments and billing: Understanding Walmart's tip policy](https://support.roadie.com/support/article/Payments-and-billing-Understanding-Walmart-s-tip-policy) – Walmart
- [Roadie Support: App and tech support: Contacting Customer Service](https://support.roadie.com/support/article/How-to-Get-Help-and-Contact-Customer-Service) – 在线聊天与邮件支持
- [Roadie Support: Roadie Customer Support](https://support.roadie.com/support/s/contactsupport) – 联系表单
- [Roadie: Delivery Tracker](https://track.roadie.com/) – 单号长度、步骤、提示文案、邮编锁、14 天照片保留、转交承运商与 “Contact Support” 链接
- [Universal Parcel Scraper: Roadie](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/roadie) – 单号格式、链接规则、步骤映射与 Peek 读取内容
:::
