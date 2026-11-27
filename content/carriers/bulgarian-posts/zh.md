---
title: 保加利亚邮政（Bulgarian Posts）物流查询：PS 与 BG 单号、清关和理赔
description: 查询保加利亚邮政（Bulgarian Posts）包裹：PS 与 S10 BG 单号格式、英文与保语状态含义、20 天邮局代管、欧盟清关费用、理赔与客服电话。
slug: bulgarian-posts-tracking
published: 2026-11-26
updated: 2026-11-26
---

保加利亚邮政（Bulgarian Posts，*Български пощи ЕАД*）是保加利亚国家普遍邮政运营商，负责处理国内信函、电子包裹（e-package）与普通包裹，以及国际万国邮政联盟（UPU）邮件和 EMS / Bulpost 快递派送。你可以在[保加利亚邮政查询页面](https://www.bgpost.bg/en/postal-services/track-package)跟踪邮件，或直接将运单号粘贴到上方输入框中。

## 保加利亚邮政运单号：PS 与 S10 格式

你可以在寄件收据、网店发货邮件或纸质派送通知单（*служебно известие*）上找到 13 位条码单号。在 [bgpost.bg/en/postal-services/track-package](https://www.bgpost.bg/en/postal-services/track-package) 上，保加利亚邮政列出了两种可追踪格式：

| 格式（示例编号） | 含义 |
| --- | --- |
| `PS48291736405` | 国内邮件或电子包裹：`PS` 后接 11 位字母或数字（共 13 位）。 |
| `RR482917367BG` | 国际挂号信函/小包（`R`、`C`、`E`、`L` 或 `V` + 第二个字母 + 9 位数字 + `BG`）。 |
| `CP482917367BG` | 从保加利亚寄出的国际邮政包裹（`CP` + 9 位数字 + `BG`）。 |

从海外寄往保加利亚的入境包裹沿用寄出地邮政的 UPU S10 编码（以该国两位字母代码结尾，如 `DE`、`CN` 或 `US`）。保加利亚邮政说明，出境国际邮件在其网站上可追踪至离开保加利亚，之后需在目的地邮政网站查询；入境邮件则在抵达保加利亚后显示于保加利亚邮政网站。如遇保加利亚其他快递条码，可查看 Sameday、[BOX NOW](carrier:box-now) 或[运单号格式说明](guide:tracking-number-formats)（英文）。

## 保加利亚邮政物流状态含义

保加利亚邮政的查询结果会在国际运输段显示英文 UPU IPS 扫描名称——标注 `(Otb)` 表示出境、`(Inb)` 表示入境——并在国内扫描中同时显示保加利亚语描述（即使在英文页面浏览也是如此）。

| 保加利亚邮政显示 | 对你的含义 | Peek 显示 |
| --- | --- | --- |
| `Receive item from customer (Otb)`、`Получаване пратка от клиент (А10)`、首次 `Приемане` | 邮局已从寄件人处收寄。 | “已揽收” |
| `Insert item into bag (Otb) and Send item abroad`、`Send item abroad (EDI-received)`、`Receive item at office of exchange (Otb)` / `(Inb)` | 已装袋准备出口、已发往国外或已抵达保加利亚国际邮件互换局。 | “运输中” |
| `Receive item at sorting centre (Inb)`、`Insert item into domestic bag`、`Send item to domestic location (Inb)`、`Receive item at location (Inb)`、后续 `Приемане` | 正在保加利亚国内分拣中心流转，发往当地邮局。 | “运输中” |
| `Send item to customs (Inb)`、`Record item customs information (Inb)` | 已提交保加利亚海关，或正在等待清关文件/核税（常附带等待提交海关专员等说明）。 | “清关中” |
| `Return item from customs (Inb)` | 海关已放行并交回保加利亚邮政网络（并非退回寄件人）。 | “运输中” |
| `Handover item to delivery agent` | 已交由邮递员，今天安排上门派送。 | “派送中” |
| `Unsuccessful item delivery attempt (Inb)`、`Първо посещение` | 首次上门派送未成功，已留下书面通知单，邮件将转至你的当地邮局。 | “派送未成功” |
| `Receive item at collection point for pick-up (Inb)`、`За получаване в ПС`、`Второ посещение` | 正在当地邮局等待领取（`Второ посещение` 表示满 10 个自然日后发出的第二次书面通知）。 | “待取件” |
| `Deliver item (Inb)`、`Доставена`、`Доставена пратка` | 已交付并签收。 | “已签收” |

注意：`Създаване`（保加利亚邮政在入境互换局扫描后立即创建国内记录）属于行政登记而非实际移动，因此 Peek 会沿用上一条扫描所处的阶段。同样，`Второ посещение` 并不表示上门派送未成功，而是记录包裹在邮局等待领取期间发出的第二次取件通知。

## 邮局取件、保管期限与仓储费

根据保加利亚邮政[通用条款与条件（ОУ 2023）](https://www.bgpost.bg/cms/static/%D0%9E%D0%A3_2023.pdf)第 43、45、46 条（入口见[通用条款](https://www.bgpost.bg/en/about_us/conditions)）：

- **挂号邮件与包裹：** 由收件人本人或受托人凭带照片身份证件签收领取。除非另行支付送货上门费用，否则邮政包裹（*пощенски колети*）通常在保加利亚邮政投递书面通知单（*писмено служебно известие*）后，于投递邮局窗口领取。
- **标准保管期——20 个自然日：** 无论是派送未成功后留存的挂号邮件，还是存放在邮局的包裹，自抵达投递邮局起均保管 **20 个自然日**，逾期退回寄件人。
- **满 10 天发第二次通知：** 若你在 **10 个自然日**内未领取，保加利亚邮政会发出第二次书面通知（`Второ посещение`）。
- **超过 9 天收取仓储费：** 邮政包裹自通知起超过 **9 个自然日**未领取的，保加利亚邮政按现行资费收取仓储费（*магазинаж*）。
- **清关邮件——45 个自然日：** 根据[保加利亚邮政清关规定](https://www.bgpost.bg/en/customs)，**所有经过海关清关的邮件仅可在指定邮局领取**（不提供送货上门），并在退回寄件人前保管 **45 个自然日**。

## 非欧盟包裹、清关费用以及 €3 关税 / €2 欧盟处理费

欧盟境内寄送的包裹无需清关。对于从欧盟境外寄往保加利亚的网购商品和礼品，[bgpost.bg/en/customs](https://www.bgpost.bg/en/customs) 与[欧盟委员会](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en)列出了现行收费标准：

- **欧盟固定关税与处理费：** 货值不超过 €150 的跨境网购 B2C 邮件每项商品征收固定 **€3 关税**（自 2026 年 7 月 1 日起实施）；无论货值高低，来自欧盟境外的网购商品每项还需缴纳 **€2 欧盟处理费（UHF）**（自 2026 年 11 月 1 日起实施，计入增值税完税价格；私人 C2C 礼品免征）。上述 €3 关税（适用于 €150 以内 B2C 商品）和 €2 欧盟处理费**即使退回瑕疵商品也不予退还**。
- **保加利亚邮政清关代理费（含增值税，自 2026 年 11 月 2 日起生效）：**
  - 不超过 €150 的 IOSS 渠道 B2C 邮件（H7 申报）：**€2.70**（如商家结账时未代收关税，则另加关税）。
  - 不超过 €150 的非 IOSS 渠道 B2C 邮件（特别安排，H7）：**€3.80**（另加增值税和关税）。
  - €150 至 €1,000 的 B2C 邮件（H6）：**€12.00**（另加增值税和关税）。
  - 超过 €1,000 的 B2C 邮件（H1）：首类商品 **€20.00**，每增加一类商品加收 **€5.00**。
  - 私人 C2C 礼品：€45 以下 **€2.70**（免增值税与关税）；€45 至 €1,000 为 **€3.80**；超过 €1,000 为 **€20.00**（每增加一类加收 €5.00，需提供 EORI 号）。
- **所需文件：** 同意书（Declaration of Consent）、价值声明（Declaration of Value），网购订单还需提供与运单号一致的订单/发票以及付款凭证（银行/信用卡/PayPal 对账单）。

更多欧盟通用清关信息，请参阅[海关滞留包裹指南](guide:customs)（英文）。

## 包裹遗失、延误或破损与消费者权益

如果你在网店购物，交付和退款责任由卖家承担。根据保加利亚消费者保护委员会（[KZP 网购规则](https://kzp.bg/bg/potrebiteli/good-to-know/online-shopping)），除非另有约定，网购订单须在 30 天内送达，消费者自收货起享有 14 天无理由退货权。

**向保加利亚邮政提交理赔。** 根据 [bgpost.bg/en/complaints](https://www.bgpost.bg/en/complaints) 和通用条款第 56–59 条，邮件交付前应由**寄件人**提交理赔；收件人仅在获得寄件人书面同意后才可在收货前提出理赔。理赔须在交寄日起 **6 个月**内提交（可在任意邮局办理或通过 RQMS 理赔门户在线提交），并附交寄收据复印件。保加利亚邮政须在 **1 个月**内答复国内邮件理赔、**3 个月**内答复国际邮件理赔，并在通知批准后 **1 个月**内支付赔款。

**监管申诉。** 未能解决的邮政服务争议可向保加利亚通信监管委员会（[CRC / КРС 投诉页面](https://crc.bg/bg/rubriki/271/jalbi)）申诉，该机构在 30 天内回复（如需补充证据最长 2 个月）。如需排查延误或扫描中断，可查看[物流停止更新的原因](guide:tracking-not-updating)（英文）、[显示已签收却未收到](guide:delivered-not-received)（英文）、[包裹破损处理](guide:damaged-parcel)（英文）、[包裹遗失处理](guide:lost-parcel)（英文）和[包裹被退回寄件人](guide:returned-to-sender)（英文）。

## 保加利亚邮政客服与联系方式

以下联系方式公布于 [bgpost.bg/en/contacts](https://www.bgpost.bg/en/contacts)、[bgpost.bg/en/complaints](https://www.bgpost.bg/en/complaints) 和 [bgpost.bg/en/customs](https://www.bgpost.bg/en/customs)：

- **综合客服：** `*7678` 或 `+359 2 962 50 50`（工作日 08:00–17:30），邮箱 `info@bgpost.bg`。
- **EMS / Bulpost 快递：** `*6666` 或 `0700 19 666`（工作日 07:00–19:00）。
- **清关服务（1900 Sofia BESC）：** `customerservice@bgpost.bg`（周一至周五 08:00–16:00）；`E` 开头的 EMS 邮件：`emscustomerservice@bgpost.bg`（周一至周五 08:30–16:00）；瓦尔纳清关邮局 `psm.varna@bgpost.bg` / `052 604 143`。
- **理赔热线（工作日 09:00–17:30）：** 国内 `+359 2 949 3326`，国际入境 `+359 2 949 3121`，国际出境 `+359 2 949 3343`，RQMS 门户协助 `02 949 31 90`。
- **总部地址：** 1, Akademik Stefan Mladenov Str., Bl. 31, 1700 Sofia, Bulgaria。

> 请警惕要求你点击链接在线刷卡缴费以“安排保加利亚邮政派送”的陌生短信或邮件：清关包裹均在指定邮局付款并领取。请直接在 [bgpost.bg](https://www.bgpost.bg/en/postal-services/track-package) 核对条码。另见[快递诈骗短信识别](guide:delivery-scam-texts)（英文）。

## 使用 Peek 跟踪保加利亚邮政包裹

[Peek](/) 可高置信度直接识别 13 位 `PS` 单号（如示例编号 `PS48291736405`）为保加利亚邮政，也能识别粘贴的 `bgpost.bg/postal-services/track-package?itemid=`（含 `/en/`）及旧版 `IPSWeb_item_events.asp?itemid=` 链接。对于以 `BG` 结尾的国际 UPU S10 单号（如 `RR482917367BG` 或 `CP482917367BG`），Peek 会自动首选保加利亚邮政作为寄出地邮政进行追踪；对于海外寄来的入境 S10 单号，你也可以在添加时点击“更换”或在包裹页点击“更换快递公司”手动选择保加利亚邮政（搜索“Български пощи”“Bulgaria Post”或“BG Post”同样可以找到）。注意：若单号属于 Bulpost（`EMS/Bulpost`），保加利亚邮政会将其跳转至 Bulpost 独立页面。

Peek 直接查询保加利亚邮政公开的查件系统，展示对应阶段（“已揽收”、“运输中”、“清关中”、“派送中”、“派送未成功”、“待取件”、“已签收”）、英文 IPS 记录（含保加利亚语国内记录与海关状态备注）、邮局/国家位置（去除邮编）以及保加利亚邮局的送达时间。保加利亚邮局的扫描按索非亚时间记录，境外邮政的扫描按当地时区记录（例如法兰克福按德国时间），Peek 会统一换算为你设备的本地时间。保加利亚邮政查询系统不提供预计送达日期、重量、服务名称、收寄件人姓名或签收照片。

当你打开包裹页，或在派送当天开启通知时，Peek 最多每 10 分钟查询一次；在保加利亚邮政显示 `Handover item to delivery agent` 的当天，只要保加利亚邮政自己的查件系统正常响应，自保加利亚时间早上 6 点起每 2 分钟查询一次。其余情况下每小时检查一次。若包裹停留在“已生成面单”或“已揽收”超过 12 小时，或连续两天无新动态，无论以何种方式关注均改为每小时检查一次；若在“已生成面单”停留满 7 天，则改为每 6 小时一次。当保加利亚邮政自己的查件系统无响应、Peek 仅通过物流查询服务跟踪时，会每小时查询一次，静默一天后每 3 小时一次，派送当天最多每 15 分钟一次。

若最新扫描已过 4 天，且包裹仍处于“已生成面单”、“已揽收”或“运输中”，Peek 会按该扫描日期做出提示，例如“自10月12日周一起没有物流更新”。包裹页面会显示“包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 Bulgarian Posts 查询。”并提供前往保加利亚邮政联系页面的“向 Bulgarian Posts 寻求帮助”链接。若超过三周没有新扫描，则会提示“已超过三周没有新动态。如果还没收到，请联系卖家。Peek 会持续向 Bulgarian Posts 查询。”

Peek 可以按“每次扫描”、“重要环节”或“仅派送当天”通知你。Peek 仅提供物流状态监控，无法代你提交清关申报或向保加利亚邮政发起理赔。

## 保加利亚邮政查询常见问题

### 保加利亚邮政在邮局替我保管包裹多久？

普通包裹和挂号邮件自抵达投递邮局起保管 **20 个自然日**（满 10 天发出第二次通知，普通包裹超过 9 天开始收取仓储费）。经海关清关的邮件会在指定邮局保管 **45 个自然日**。

### 保加利亚邮政状态中的 `Второ посещение` 是什么意思？

这表示包裹在邮局满 10 个自然日未领取后，保加利亚邮政发出了第二次书面取件通知。你的包裹仍在邮局等待领取——Peek 会将其保持在“待取件”状态。

### `Return item from customs (Inb)` 是退回寄件人的意思吗？

不是。它表示保加利亚海关已清关放行，将邮件交回保加利亚邮政网络继续送往你的邮局，并非退回给寄件人。

### 保加利亚邮政的清关代理费是多少？

自 2026 年 11 月 2 日起，保加利亚邮政清关代理费（含增值税）为：€150 以下 IOSS 订单（或 €45 以下 C2C 礼品）收 €2.70；€150 以下非 IOSS 订单（或 €45–€1,000 礼品）收 €3.80；€150–€1,000 订单收 €12.00；超过 €1,000 收 €20.00（每增加一类商品加收 €5.00）。此外还需按规定缴纳欧盟关税（€150 以下为 €3）、€2 欧盟处理费及增值税。

:::sources
- [Bulgarian Posts: Home](https://www.bgpost.bg/en) – 国家邮政运营商概况
- [Bulgarian Posts: Item Tracking](https://www.bgpost.bg/en/postal-services/track-package) – 13 位 S10 与 `PS` 单号规则、出入境邮件的查询范围
- [Bulgarian Posts: Customs](https://www.bgpost.bg/en/customs) – €3 关税、€2 欧盟处理费、2026 年 11 月 2 日起的清关代理费（€2.70 / €3.80 / €12 / €20）、仅限邮局领取及 45 个自然日的清关保管期
- [Bulgarian Posts: General Terms (ОУ 2023 PDF)](https://www.bgpost.bg/cms/static/%D0%9E%D0%A3_2023.pdf) – 第 43、45、46 条（证件与签收、20 个自然日保管、10 天第二次通知、9 天后仓储费）及第 56–59 条（理赔）
- [Bulgarian Posts: General Terms Overview](https://www.bgpost.bg/en/about_us/conditions) – 普遍邮政服务及 EMS/Bulpost 条款
- [Bulgarian Posts: Claims and Inquiries](https://www.bgpost.bg/en/complaints) – 由寄件人理赔的规则、6 个月时限、1 个月/3 个月答复、RQMS 门户与理赔电话
- [Bulgarian Posts: Contacts](https://www.bgpost.bg/en/contacts) – `*7678`、`+359 2 962 50 50`、`info@bgpost.bg`、EMS `*6666` / `0700 19 666`、索非亚总部
- [Bulgarian Posts: FAQ](https://www.bgpost.bg/en/faq) – 收件人一般问题
- [European Commission: EU Customs Reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – €3 关税与 €2 欧盟处理费
- [KZP: Online Shopping](https://kzp.bg/bg/potrebiteli/good-to-know/online-shopping) – 30 天送达规定与 14 天撤销权
- [CRC (КРС): Complaints](https://crc.bg/bg/rubriki/271/jalbi) – 向邮政监管机构投诉及 30 天答复时限
- [Universal Parcel Scraper: bulgarian-posts](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/bulgarian-posts) – `PS` 与 S10 `BG` 单号识别、`bgpost.bg` 链接规则、英文与保加利亚语 IPS 状态对应、Bulpost 跳转、索非亚时间与帮助链接
:::
