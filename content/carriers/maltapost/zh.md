---
title: MaltaPost 马耳他邮政追踪：单号、状态、自提柜与客服
description: 追踪马耳他邮政（MaltaPost）邮件与包裹：MT 单号格式、状态含义、邮局与 Easipik 自提柜取件、海关清关缴费及官方客服电话。
slug: maltapost-tracking
published: 2026-11-26
updated: 2026-11-26
---

MaltaPost 是马耳他国家邮政，每周六天向马耳他岛与戈佐岛的所有地址投递邮件和包裹。你可以在其[官方追踪页面](https://www.maltapost.com/tracking)输入单号查询，或直接粘贴到上方输入框中。运单号由寄件人提供，可从寄件回执、包裹条码下方标签或你的投递通知单上查到。

## MaltaPost 运单号格式说明

MaltaPost 自身发出的挂号邮件使用 13 位单号：[两位字母、九位数字](guide:tracking-number-formats)（英文）加 `MT`。其重新派送表单中列出的本地前缀包括 `RH`、`RR`、`RT` 和 `RI`。

:::anatomy RR 482917367 MT
- RR | 邮件类型：本地挂号件常见前缀为 RR、RH、RT 或 RI
- 482917367 | 九位数字，末位为校验位
- MT | 马耳他寄出
:::

从海外寄往马耳他的邮件会沿用原寄国邮政分配的单号（如 `RR482917367GB`、`CP482917367CN` 或其他格式），MaltaPost 追踪系统同样支持查询，并可能在页面上同时显示“Foreign Barcode”（境外条码）和“Local Barcode”（本地条码）。SendOn 转运包裹凭任一条码均可追踪。

MaltaPost 常见问题提示请用大写字母输入单号。若页面返回“Tracking number not recognised.”，请与寄件人核对单号。部分单号（例如以 `U` 开头的普通追踪小包）无法在马耳他境内追踪，或实际由其他本地快递派送。未带追踪号的平邮无法查询定位。找不到单号时，请参阅[如何查找运单号](guide:find-tracking-number)（英文）。

## MaltaPost 追踪状态含义

每条扫描记录包含日期、时间、事件与扫描地点，下方有时附带“Reason:”（原因）和“Action:”（后续操作）说明。对于寄往你的邮件，常见状态如下：

| MaltaPost 状态文案 | 对你的含义 |
| --- | --- |
| `Item created` | 已交寄。若为境外邮件，代表原寄国邮政已收寄。 |
| `Item arrived at inbound mail sorting centre` | 邮件已抵达马耳他。 |
| `Item to be submitted for clearance` | 即将提交海关查验。 |
| `Item undergoing border agency clearance` | 海关或其他口岸监管机构正在查验。 |
| `Item held at inbound mail sorting centre` | 邮件暂扣。若附带“Awaiting payment of charges”，须缴清税费后才会放行。 |
| `Item returned from local Customs office` | 海关已放行。 |
| `Item to be forwarded to the delivery centre` | 正转往负责投递的邮局分拨点。 |
| `Item scheduled for physical delivery` | 今日已安排派送（派送中）。 |
| `Item could not be processed further. Unsuccessful attempt-Recipient notified` | 无人签收，邮递员已在你的信箱留下通知单。 |
| `Item arrived at collection point` | 已到达扫描记录所列邮局，可供领取。 |
| `Item delivered/collected by recipient` | 已送达或已由你本人领取。 |
| `Item could not be processed further. Item not collected. Item returned to sender` | 逾期无人领取，正在退回寄件人。 |

页面上的进度条还会以 `Arrived in Country`、`Ready for Collection`、`Attempted Delivery` 等阶段进行汇总。

有三条记录不代表实物移动：`Declaration Submitted`、`Payment Submitted` 和 `Online payment complete` 仅记录你在线完成的申报或缴费操作。`Forwarded to the next processing centre` 表示在 MaltaPost 内部处理中心之间流转。MaltaPost 特别提醒，显示的日期和时间是扫描入录时间，未必等同于实际发生时刻。

## MaltaPost 投递通知单、邮局领取与 Easipik 自提柜

MaltaPost 会尝试将挂号件送货上门。如无人在家，邮递员会在信箱留下通知单，指引你前往指定邮局自提。不同颜色的通知单分别说明适用的领取或重新派送方式。

**邮局领取：** 请携带通知单与本人身份证件原件（身份证、护照、驾照、居留卡或单一工作许可）。如需他人代领，须出示通知单、你的证件原件以及代领人证件原件，或正式授权委托书。邮局通常营业时间为周一至周六 7:30 至 13:00，部分网点时间略有不同。

**错过派送：** 填写[错过派送表单](https://www.maltapost.com/delivery/missed-delivery)时需提供通知单颜色、追踪单号及通知单照片。你可以申请重新派送至指定地址、改寄其他邮局（或分邮局）或 Easipik 自提柜，也可申请在标准 12 个工作日之外延长保管。单件可追踪邮件由快递二次上门派送需收费 €5.50。超过重新派送窗口期后，MaltaPost 最长可代为保管 12 周；逾期未领的邮件将退回寄件人。

**Easipik 快递自提柜：** 遍布马耳他岛与戈佐岛的 24 小时全天候自提柜，可存放最大 40×40×60 厘米、最重 30 千克的包裹。MaltaPost 会通过短信和邮件发送开箱码；收到取件码后包裹在柜内保留 72 小时，超时后转存至当地邮局柜台。MaltaPost 自有投递免费使用 Easipik，其他快递公司包裹收费 €3.50。

## MaltaPost 海关税费、延误与遗失邮件

**因税费暂扣：** 来自欧盟以外地区的邮件需缴纳关税和增值税，依法在缴清前 MaltaPost 不得放行。在追踪页面点击“Declare Items”可上传申报材料，点击“Pay Charges”即可线上缴费；缴清后的邮件会放行并于下一个工作日安排派送。详见[包裹在海关滞留怎么办](guide:customs)（英文）。

**无更新或未收到：** MaltaPost 常见问题说明，境外来件须通过原寄国邮局发起查询，因此请联系网店或寄件人提出查单。MaltaPost 将寄出后满四个工作日以上界定为延误，查询与投诉有效期为寄出起六个月内，国际查询目标处理时限为 90 天内。另见[为什么物流停止更新](guide:tracking-not-updating)（英文）。

**包裹破损：** 领取挂号邮件或包裹时请当着 MaltaPost 工作人员的面查验，否则 MaltaPost 不承担赔偿责任。赔款原则上付给寄件人，寄件人也可声明放弃并转让给你。

## MaltaPost 客服电话与联系方式

MaltaPost 客户服务页面公布的联系渠道：

- **电话：** `(+356) 2122 4421`，或马耳他固话免费热线 `800 722 44`。周一至周五 8:30 至 14:30，周六 7:30 至 12:30，公共假日休息。
- **邮箱：** `info@maltapost.com`（自行清关或委托代理清关亦可通过此邮箱联系）。
- **在线渠道：** 常见问题页下方的联系表单，或 Facebook 私信。
- **总部地址：** 305, Triq Ħal Qormi, Marsa MTP 1001（周一至周五 8:00 至 16:00）。
- **Easipik 自提柜客服**（柜机与 PIN 码问题）：`2124 3324`。

> MaltaPost 警示存在冒充邮政发送虚假短信和邮件、诱导点击链接预付派送费的诈骗行为。请务必确认网址域名为 `maltapost.com`，警惕诸如 `malta-post.com` 之类的仿冒域名，并可向马耳他网络犯罪调查部门（Malta Cyber Crime Unit）举报诈骗。

## 使用 Peek 追踪 MaltaPost 邮件

输入校验位有效的 `MT` 结尾单号时，[Peek](/) 会同时询问 MaltaPost 与 [Chronopost](carrier:chronopost)，查到后显示“MaltaPost 已承运此包裹。”，暂未查到则显示“暂未在……查到。添加后 Peek 会继续查询。”若为五位字母、九位数字加 `MT` 的格式，Peek 仅询问 MaltaPost。校验位有误时 Peek 不会向 MaltaPost 查询，手动指定也无效（会提示其“不签发这种格式的单号”），请与寄件人核对单号。任何带 `barcode=` 参数的 maltapost.com 追踪链接都会直接归为 MaltaPost。

境外来件默认先在原寄国邮政追踪。当原寄国邮政显示发往马耳他（或未标明目的地而你身处马耳他）时，Peek 也会用同一单号向 MaltaPost 查询，并在出现更新扫描后切换至 MaltaPost 继续追踪，同时保留前期历史轨迹。MaltaPost 接口支持此类境外单号及 SendOn 条码：若想直接从 MaltaPost 开始追踪，点击“更改”选择“MaltaPost”，或直接粘贴 maltapost.com 链接即可。

MaltaPost 的扫描时间不含时区：Peek 按扫描地时钟解析（马耳他境内按马耳他时间），并完整展示 MaltaPost 原有轨迹；时间自动换算为你的设备本地时区，在 peektracker.com 及 iPhone/Android 应用中轨迹底部会注明“时间已按你的时区显示：”及时区名。状态沿用 MaltaPost 原文，可展示包裹暂存或已领取的邮局名称，并支持状态变更通知，但不包含预计送达日与重量。被 MaltaPost 屏蔽追踪的邮件无法查询。

在 MaltaPost 官网追踪正常响应期间，当你停留在包裹页面，或开启提醒且预计今日送达时，Peek 最高每 10 分钟检查一次；若 MaltaPost 当天扫描了“Item scheduled for physical delivery”，自承运商时区早上 6 点起在页面打开或开启提醒时最高每 2 分钟检查一次。关闭页面且未开启提醒时通常每小时检查一次。若仅由追踪服务响应，则每小时检查一次，最新扫描满一天后每 3 小时一次，派送当天在页面打开或开启提醒时最高每 15 分钟一次。当另一承运商接手派送时，Peek 会遵循负责派送一方的频率。直连官方追踪时，若连续两天无新动态（停留在“已建单”或“快递公司已揽收”满 12 小时）则改为每小时检查一次。处于“已建单”、“快递公司已揽收”或“运输中”的邮件满四天无新扫描时，列表会显示最新扫描日期（如“自 10月12日 周一 起无物流更新”）。

## MaltaPost 追踪常见问题

### MaltaPost 周六营业吗？

营业。除公共假日外，邮局通常周六 7:30 至 13:00 开放，客服电话周六 7:30 至 12:30 接听。

### MaltaPost 派送需要多长时间？

MaltaPost 未对境外邮件给出统一送达时效。工作日 16:30 前（周六 13:30 前）抵达马耳他的国际邮件会在当天处理并于下一个工作日投递；SendOn 转运包裹从英国发往马耳他约需 7 至 12 天，德国或意大利约 10 至 12 天，美国约 15 天。

### MaltaPost 有 24 小时客服电话吗？

没有。人工客服仅在工作日 8:30 至 14:30 及周六上午接听；但 Easipik 快递自提柜全天候 24 小时开放。

### 马耳他邮政的国际追踪单号是什么格式？

从马耳他寄往境外的邮件采用两位字母、九位数字加 `MT` 的格式，例如虚构单号 `RR482917367MT`。从海外寄入马耳他的邮件则保留原寄国单号，MaltaPost 官网同样支持追踪。

:::sources
- [MaltaPost: Tracking](https://www.maltapost.com/tracking) – 追踪输入框、扫描列项、进度阶段、境外与本地条码、无法识别原因及海关按钮
- [MaltaPost: Frequently Asked Questions](https://www.maltapost.com/faqs?l=1) – 大写字母要求、通知单、邮局营业时间、Easipik 72 小时及热线、清关缴费、次日处理、境外来件及英/德/意/美 SendOn 时效
- [MaltaPost: Missed a Delivery?](https://www.maltapost.com/delivery/missed-delivery) – 重新派送选项、12 个工作日、12 周保管、二次派送费与本地前缀
- [MaltaPost: Packet Collection](https://www.maltapost.com/packets) – 取件证件与通知单要求及代领规定
- [MaltaPost: courier it](https://www.maltapost.com/courier) – 上门投递尝试与通知单
- [MaltaPost: Easipik](https://www.maltapost.com/easipik) – 自提柜、短信与邮件开箱码、尺寸限重与第三方包裹费用
- [MaltaPost: Parcel Lockers](https://www.maltapost.com/lockers) – 马耳他岛与戈佐岛自提柜站点
- [MaltaPost: Post Offices Opening Hours](https://www.maltapost.com/postoffices) – 各邮局营业时间
- [MaltaPost: Customs](https://www.maltapost.com/customs) – 申报、缴费及通过 info@maltapost.com 自行清关
- [MaltaPost: How to lodge an Enquiry](https://www.maltapost.com/how-to-lodge-an-enquiry?l=1) – 延误界定、六个月时限、7 天与 90 天处理期限
- [MaltaPost: Committed to Deliver](https://www.maltapost.com/committed-to-deliver) – 赔偿归寄件人及签收当场验货规定
- [MaltaPost: Customer Service](https://www.maltapost.com/customers-service?l=1) – 电话、免费热线、服务时间、邮箱与总部地址
- [MaltaPost: Scam Protection](https://www.maltapost.com/scamprotection?l=1) – 虚假短信与邮件防骗警示
- [MaltaPost: SendOn](https://www.maltapost.com/sendon) – 转运服务、上门投递或 Easipik 自提
- [MaltaPost: About us](https://www.maltapost.com/about-us) – 马耳他国家邮政运营商，每周六天派送
- [MCA: How can I track and trace registered articles?](https://www.mca.org.mt/content/post-how-can-i-track-and-trace-registered-articles) – 条码下方单号说明
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Peek 对 MaltaPost 的读取规则
:::
