---
title: Poczta Polska 波兰邮政查询：单号、状态与取件
description: 查询波兰邮政 Poczta Polska 及 Pocztex 包裹：单号格式、英语状态中文说明、取件通知单、自提点与自提柜、清关税费与客服联系。
slug: poczta-polska-tracking
published: 2026-11-24
updated: 2026-11-24
---

Poczta Polska 是波兰国有邮政公司，Pocztex 是其旗下的快递业务。你可以在 Poczta Polska 的 eMonitoring 查询页面（提供英文版）查询包裹或挂号信，或直接将单号粘贴到上方输入框中。单号印在寄件人的交寄凭证上，因此发货商家掌握该单号，Poczta Polska 发送的短信和通知单上也会再次写明。

## Poczta Polska 运单号及查找位置

输入时请去掉空格和括号。除保价信件外，所有带交寄凭证的邮件均支持追踪；挂号信仅显示交寄日期和送达日期。

| 格式（虚构） | 说明 |
| --- | --- |
| `PX4829105734` | `PX` 加 10 位数字。 |
| `0015900773482917360` | 19 位数字。 |
| `00159007734829173604` | 20 位数字，前三位后紧跟 `5900773`。 |
| `RR482910576PL` | 国际邮政单号格式：2 位字母、9 位数字加 `PL`。 |

单号会出现在：

- **寄件人交寄凭证：** 如果订单邮件里没有单号，请直接联系商家。
- **取件通知消息**（Pocztex 自提点或自提柜）：包含单号、取件地点、截止日期以及一次性取件码。
- **未妥投通知短信、邮件或应用消息：** 包含单号、保管截止日和邮局地址。

如果 eMonitoring 提示“The given package number is incorrect”，请逐位核对单号并确认商家写明的承运商。完全找不到单号？请参阅[如何查找运单号](guide:find-tracking-number)（英文）。

## Poczta Polska 物流状态中文说明

eMonitoring 默认以波兰语打开；英文版会显示以下文案，并附每一步的日期、时间和邮局名称。

| Poczta Polska 状态原文 | 对你的含义 |
| --- | --- |
| `Electronic item data received` | 寄件人已录入预报信息，Poczta Polska 尚未收到包裹。 |
| `Posting/collection`、`Item arrived at the shipping point` | 已在邮局或网点交寄，或已从寄件人处揽收。 |
| `In transport` | 正在各分拣中心之间转运。 |
| `Departure from Poland` | 已离开波兰发往其他国家。 |
| `Arrival at inward office of exchange (Poland)` | 海外寄来的包裹已抵达波兰国际互换局。 |
| `Customs service` | 正在办理海关手续。 |
| `Item returned from import customs` | 进口海关已放行。 |
| `In delivery` | 正在派送中，或代表一次派送未成功（见下文）。 |
| `Unsuccessful (physical) delivery` | 无人签收。已留通知单，包裹将送往邮局或再次尝试派送。 |
| `Attempted delivery - redirected` | 尝试派送后，包裹正被转寄改派。 |
| `Delivery at sender's request - delivery attempt` | 按寄件人要求安排的一次派送尝试。 |
| `Unsuccessful (physical) delivery - lost` | 派送途中遗失。 |
| `Ready for pick-up at the Post Office`、`Added to postal notice book - Item can be collected`、`Ready for pick up at the point` | 包裹正在扫描记录所标邮局或网点等待你领取。 |
| `Collected at the post office`、`Collected at the delivery point` | 已在邮局或网点取走。 |
| `Final delivery`、`Delivered to the PO box`、`Delivery at sender's request - delivered` | 已签收。 |
| `Returned`、`Returned to sender`、`Not collected at the delivery point` | 包裹正在退回寄件人。 |

`Posting/collection` 指寄件人交寄环节，并非你本人取件。Poczta Polska 还将其中一种派送失败事件同样命名为 `In delivery`：Peek 会根据事件代码加以区分，将其显示为“派送未成功”而非“派送中”。`Customs service` 可能会在签收后出现，用于登记上门代收的关税。如果不带“(Poland)”，`Arrival at inward office of exchange` 表示从波兰寄出的包裹已抵达目的国。在波兰语页面上，未妥投显示为 `Awizowana`，在邮局待领显示为 `Oczekuje na odbiór`。Poczta Polska 说明事件名称可能会调整。

## Poczta Polska 派送未成功：通知单、自提点与自提柜

**Pocztex 快递包裹。** 快递员会将包裹交给你本人、同住成年人或经你授权的人。如果你不在家，且寄件人留下了你的联系方式，你会收到短信、邮件或应用通知；否则会在信箱留下纸质通知单；两天后会再发一次提醒。包裹自通知次日起在邮局保管 7 天，逾期退回寄件人。你也可以致电快递员要求将包裹直接留在住址附近（不适用于货到付款、保价、到付或“do rąk własnych”本人亲收件），风险由你自行承担。

**自提点与自提柜。** 若寄件人选择了 Pocztex 自提点（PUNKT），邮局网点保管 7 天，Żabka、ABC、Lewiatan 等合作商店保管 3 天。位于邮局、加油站和 Biedronka 等门店的自提柜（AUTOMAT）保管 72 小时，并在第 24 和 48 小时发送提醒。凭手机号与取件码，或使用 Pocztex Mobile 应用即可开柜。保管期限不可延长，包裹也无法转至其他网点，但任何持有取件码的人均可代取。

**挂号信与普通邮政包裹。** 邮递员会在信箱留下纸质通知单（awizo），若你开通了服务则会收到电子通知。7 天后会发第二次通知，自首次通知起 14 天内未领取则退回寄件人。你可以付费在邮局或 poczta-polska.pl 申请延长最多 14 天保管期。

## Poczta Polska 包裹延误、丢失、破损或清关

Poczta Polska 的物流追踪仅供参考，不能作为索赔依据，且官方未对物流停滞发布指引：请先联系寄件人，并参阅[物流不更新怎么办](guide:tracking-not-updating)（英文）。

**谁可以投诉。** 寄件人可提出投诉；邮件送达你手中后，或寄件人将索赔权转让给你后，你也可以投诉。可在 poczta-polska.pl 在线提交、以纸质形式提交或前往任意邮局现场办理。

**波兰境内申诉期限**（依 Poczta Polska 服务条款）：

- 自交寄之日起 12 个月内。
- 明显外包装破损：须在收货当场出具记录。你可以查验外观破损的包裹内件（自提点和自提柜除外）。
- 隐蔽破损或内件短少：签收后 7 天内。
- 丢失：Pocztex 包裹寄出满 30 天视为丢失；普通邮政包裹可在寄出满 14 天后报失（不含法定节假日和周日）。
- Poczta Polska 在 30 天内答复；如有异议可在 14 天内申诉。

国际邮件须在交寄次日起 6 个月内提出投诉。

**海关与税费。** 自 2026 年 7 月 1 日起，从欧盟以外入境、货值不超过 150 欧元的物品按商品类别每类征收 3 欧元欧盟关税，由 Poczta Polska 代收；个人间价值不超过 45 欧元的礼品免征。自 2026 年 11 月 1 日起，从欧盟外网购的商品还需按件缴纳 2 欧元欧盟处理费（相同商品可合算一件），由 Poczta Polska 向收件人收取并转交波兰税务机关（KAS）。还可能加收尚未缴纳的增值税（VAT）以及 Poczta Polska 的 20 波兰兹罗提清关申报费（自行清关为 25 波兰兹罗提），在派送时或取件前支付。你有权拒收该邮件。详情见[清关中的包裹指南](guide:customs)（英文）。

## Poczta Polska 客服电话与联系方式

根据 Poczta Polska 英文与波兰语官网列出的渠道：

- **总客服热线：** +48 438 420 600，周一至周五 8:00 至 20:00（按运营商资费计费），也可使用[联系页面](https://www.poczta-polska.pl/en/kontakt/)上的在线咨询表单。
- **Pocztex 专线：** 波兰固话拨打 804 104 104，手机及海外来电拨打 +48 43 842 0 842，周一至周五 8:00 至 20:00，周六 8:00 至 16:00。
- **物流查询邮件：** kontakt@poczta-polska.pl。
- **自提点异常**（找不到包裹、网点歇业）：呼叫中心固话 801 333 444 或 +48 438 420 600。

> Poczta Polska 提醒防范冒充 Pocztex 的派送失败诈骗短信及仿冒链接。Pocztex 绝不会通过短信发送付款链接或索要银行卡信息。可疑短信可转发至 8080 举报。

## 使用 Peek 查询 Poczta Polska 包裹

[Peek](/) 会直接识别校验位有效的 `PX` 单号，以及前三位后紧跟 `5900773` 且校验位有效的 20 位单号。对于以 `00` 开头的 19 位单号，Peek 会像 Poczta Polska 官网一样补上校验位并仅向 Poczta Polska 查询。以 `PL` 结尾且以 `R`、`E`、`L` 或 `C`（`CV` 除外）开头的有效国际邮政单号（如 `RR`、`EE` 或 `CP`）会直接归入 Poczta Polska；其他有效 `PL` 单号（如 `VV` 开头）在 Peek 同时询问 Poczta Polska 和 Chronopost 时会显示为“Unknown postal carrier”，一旦 Poczta Polska 认领即切换过去。带有 `numer=` 的 eMonitoring 查询链接同样适用，且无需填写邮编。

Peek 会保留英文扫描原文（将 Peek 设为波兰语时则显示波兰语原文），在地图上标出对应邮局，并展示重量、目的国及保管包裹的取件邮局。不提供预计送达日期，也会省略邮局详细地址、营业时间、付款凭证及附加服务。从海外转发的扫描记录不含时区，Peek 会按其来源国时钟解析。托盘货物、集运拼箱件和普通平信无法追踪。

当你打开包裹页面查看，或在派送当天开启通知时，只要 Poczta Polska 官方查询正常响应，Peek 最高每 10 分钟检查一次，在 Poczta Polska 扫描包裹进入派送中的当天早上 6 点（当地时间）起最高每 2 分钟检查一次；其余时间每小时检查一次。状态变化时，Peek 可以通知你。若连续两天无新动态（包裹仍处于“已生成面单”或“已揽收”状态时为 12 小时），Peek 会转为每小时检查一次。当仅由物流查询服务返回结果时，Peek 每小时检查一次，最新扫描满一天后每 3 小时检查一次，在派送当天打开页面或开启提醒时最高每 15 分钟检查一次。从海外寄往波兰的包裹也可衔接至 Poczta Polska 继续追踪。

## Poczta Polska 物流查询常见问题

### Pocztex 是什么？

Pocztex 是波兰邮政 Poczta Polska 旗下的快递服务，拥有独立网站 pocztex.pl 和 Pocztex Mobile 应用，但物流查询与波兰邮政共用同一套 eMonitoring 系统。

### 可以用英文查询 Poczta Polska 吗？

可以。eMonitoring 默认显示波兰语，但提供英文版本。

### Pocztex 派送需要多久？

Poczta Polska 保证 Pocztex Kurier 在寄出后的第 5 个工作日内送达，2XL 及非标尺寸包裹为第 7 个工作日内。工作日指周一至周五（不含法定节假日）。

### 收件人可以修改 Poczta Polska 包裹的派送地址吗？

收件人不能。根据 Poczta Polska 服务条款，更改收件地址或收件人只能由寄件人申请，发往 Pocztex 自提点或自提柜的包裹也无法转寄至其他网点。

:::sources
- [Poczta Polska: Track your shipment](https://www.poczta-polska.pl/en/sledzenie-przesylek/) – 单号格式、可追踪范围、取件点与境外运营商
- [Poczta Polska: Śledź przesyłkę](https://www.poczta-polska.pl/sledzenie-przesylek/) – 波兰语同页说明
- [Poczta Polska: eMonitoring (English)](https://emonitoring.poczta-polska.pl/?lang=en) – 单号示例、错误提示、仅供参考声明与 kontakt@ 邮箱
- [Poczta Polska: eMonitoring](https://emonitoring.poczta-polska.pl/) – 波兰语查询页及状态原文
- [Pocztex: Śledzenie przesyłek](https://www.pocztex.pl/sledzenie-przesylek/) – Pocztex 物流查询入口
- [Poczta Polska: Regulamin usługi Pocztex](https://www.poczta-polska.pl/wp-content/uploads/2026/08/Regulamin-swiadczenia-uslugi-Pocztex-w-obrocie-krajowym-dla-klienta-detalicznego-obowiazuje-od-24.08.2026-r.pdf) – 派送、通知单、自提点、自提柜、时效保证、期限与投诉条款
- [Poczta Polska: Regulamin usług powszechnych](https://www.poczta-polska.pl/wp-content/uploads/2026/07/Regulamin-swiadczenia-uslug-powszechnych-tekst-jednolity.pdf) – awizo 通知单、保管期、丢件认定与国际邮件申诉
- [Poczta Polska: Lista zdarzeń / Events list](https://www.poczta-polska.pl/wp-content/uploads/2025/02/PPSA_events_lists.pdf) – 事件名称列表及变动说明
- [Poczta Polska: Pocztex PUNKT and AUTOMAT FAQ](https://odbiorwpunkcie.poczta-polska.pl/en/faq/) – 合作门店、自提柜、不可改派与呼叫中心
- [Pocztex: FAQ](https://www.pocztex.pl/en/faq-en/) – 通知、取件点与破损记录
- [Poczta Polska: eReklamacje, paczka pocztowa](https://ereklamacje.poczta-polska.pl/sub/reklamacje-krajowe/paczka-pocztowa) – 投诉主体、时限与 30 天答复
- [Poczta Polska: Contact](https://www.poczta-polska.pl/en/kontakt/) – 热线电话、服务时间与表单
- [Poczta Polska: Kontakt](https://www.poczta-polska.pl/kontakt/) – 波兰语联系页面与投诉表单
- [Pocztex: Kontakt](https://www.pocztex.pl/kontakt/) – Pocztex 客服热线与时间
- [Poczta Polska: Beware of fake "Pocztex" messages](https://www.poczta-polska.pl/en/news/uwaga-na-falszywe-wiadomosci-pocztex-grozna-kampania-phishingowa/) – 虚假短信提醒与 8080 举报
- [Poczta Polska: Uwaga na fałszywe wiadomości „Pocztex”](https://www.poczta-polska.pl/news/uwaga-na-falszywe-wiadomosci-pocztex-grozna-kampania-phishingowa/) – 波兰语公告及呼叫中心电话
- [Poczta Polska: Changes in fees for shipments from outside the EU](https://www.poczta-polska.pl/en/news/zmiany-w-oplatach-dla-przesylek-spoza-unii-europejskiej-od-1-lipca-2026-r/) – 3 欧元关税、45 欧元礼品免税及拒收规定
- [Poczta Polska: Nowe przepisy Unii Europejskiej. Tak działa nowa opłata za przesyłki spoza UE（波兰语）](https://media.poczta-polska.pl/releases/nowe-przepisy-unii-europejskiej-tak-dziala-nowa-oplata-za-przesylki-spoza-ue) – 自 2026 年 11 月 1 日起向收件人代收 2 欧元欧盟处理费并上缴 KAS
- [Poczta Polska: Cennik opłat dodatkowych](https://www.poczta-polska.pl/wp-content/uploads/2025/04/cennik-oplat-dodatkowych.pdf) – 清关申报服务费价目表
- [Poczta Polska: Poczta Polska SA Group](https://www.poczta-polska.pl/en/grupa-pp/poczta-polska/) – 波兰国有邮政主体说明
- [Pocztex Mobile on the App Store](https://apps.apple.com/pl/app/pocztex-mobile/id1661761150) – 官方 Pocztex Mobile 应用
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Peek 读取的内容
:::
