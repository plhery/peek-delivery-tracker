---
title: 克罗地亚邮政（Hrvatska pošta）物流查询：状态与快递柜
description: 查询克罗地亚邮政（Hrvatska pošta）包裹：HR 运单号、英文状态含义、paketomat 快递柜、邮局自提、理赔与联系。
slug: hrvatska-posta-wuliu-chaxun
published: 2026-11-26
updated: 2026-11-26
---

Hrvatska pošta（克罗地亚邮政，Croatian Post）是克罗地亚的国家邮政，并运营 Paket24 包裹服务。你可以在其[英文查询页面](https://posiljka.posta.hr/en/tracking)查件（直接打开 posiljka.posta.hr 默认为克罗地亚语），或把单号粘贴到上方输入框。单号由寄件人提供：见商家的发货邮件、订单页，或 Hrvatska pošta 发送的短信与通知单。

## Hrvatska pošta 运单号

Hrvatska pošta 将单号称为邮件编号（*prijamni broj pošiljke*）。从克罗地亚寄出的邮件采用[国际邮政格式](guide:tracking-number-formats)（英文）：两位字母、九位数字加 `HR`。只有附带跟踪服务的邮件才有单号。

| 格式（虚构） | 含义 |
| --- | --- |
| `RR482917367HR` | 从克罗地亚寄出的邮政邮件：以 `HR` 结尾的 13 位字符，空格不影响（如 `RR 482 917 367 HR`）。 |
| `RR482917367CN` | 来自海外的邮件保留外国邮政的原单号，以寄出国代码结尾。 |
| 其他条码 | 查询框也支持至少 5 位字符的其他条码，例如 `3S…` 编码、12 位数字以及 20 位 SSCC 条码。 |

单号可在寄件人消息中找到，Hrvatska pošta 的官方通知也会注明：信箱里的到达通知单（如有）以及快递柜的短信和邮件。找不到单号？查看[如何查找运单号](guide:find-tracking-number)（英文）。

其他国家字母代表其他邮政：`…SI` 属于[斯洛文尼亚邮政](carrier:posta-slovenije)，`…RS` 属于 Pošta Srbije（塞尔维亚邮政）。海外邮政包裹交由 Hrvatska pošta 派送时仍沿用原外国单号。

## Hrvatska pošta 物流状态含义

查询系统以克罗地亚语显示每一步，点 EN 按钮可切为英语，附带日期、时间，通常还有邮局或快递柜名称。以下是英文原文，按包裹流转顺序排列。

| Hrvatska pošta 状态 | 对你的含义 |
| --- | --- |
| `Item announced` | 寄件人已[录入包裹信息](guide:tracking-statuses)（英文），尚未交寄。 |
| `Item received` | 该步骤所列邮局已收寄。 |
| `Item sent` | 已发出。 |
| `Item sent to Customs` | 已送交海关：由海外邮政传回的步骤。 |
| `Item returned from Customs` | 海关已放行，邮政继续处理，并非退回寄件人。 |
| `Item held at Post Office` | 由海外邮政传回：派送邮局在安排派送前暂存，并非自提通知。 |
| `Item is out for delivery` | 已交由邮递员，正在送往你的地址。 |
| `Delivery attempt` | 邮递员已上门，但无人签收。 |
| `Delivery was redirected to the parcelstation` | 已改投至快递柜，而非你的收件地址。 |
| `The shipment has been handed over to the parcelstation` | 已放入快递柜，等你领取。 |
| `Item can be picked up at the Post Office` | 已到达该步骤所列邮局，等待自提。 |
| `Item not delivered` | 超期无人领取：正在离开邮局。 |
| `Item delivered` | 已交付给你或有权代收的人。 |
| `Item returned to sender` | 正在退回寄件人。 |
| `Item delivered to sender` | 已退件：寄件人已收回包裹。 |

`Delivery attempt` 表示邮递员留下了含取件说明的通知单，或预告再次派送；多次派送尝试都会逐条记录。`Item not delivered` 容易误解：它通常出现在无人领取的邮件离开邮局准备退回之际，紧接在 `Item returned to sender` 之前。

有个特殊提示：对于格式正确但暂无数据的单号，英文页面会显示 `Invalid barcode format`（克罗地亚语页面则提示该单号暂无数据）。只有 `Invalid shipment number format` 才代表单号输错。如果一直查不到，页面会引导联系客服中心。

## 派送、paketomat 快递柜与邮局自提

如果你不在家，或邮件放不进信箱，邮递员会留下通知单写明领取地点和截止日期。保管期限因服务而异：

- **邮局（一般情况）：** Hrvatska pošta 帮助页写明为 5 个工作日，超期退回寄件人。
- **海外包裹**（普遍服务）与普通 Paket：在邮局保管 15 个工作日；普通 Paket 在此之前会先派送一次。
- **Paket 24：** 在邮局保管 5 个工作日，随后 Hrvatska pošta 会联系你或寄件人确认处理方式。
- **快递柜（paketomati）：** 自短信或邮件通知起 3 天，到期前 12 小时发送提醒。

准确截止日以通知单所载日期为准。需要外出一段时间？邮局可付费代为保管邮件最长 30 天。重量超过 500 克的邮件若在通知后一天内未取，需缴仓储费；被通知办理海关查验的邮件，自通知起 10 天后开始计收仓储费。

**快递柜。** 英文官网称共有约 300 组快递柜，全天候 24 小时开放。凭通知里的 PIN 码或条码开箱，如有货到付款金额可在柜机刷卡支付。Hrvatska pošta 收到包裹后可能会发短信和邮件附改投链接，让你改投至自选快递柜；邮递员未能送达的包裹也可能转投快递柜。没收到取件码？先检查 Viber 商业收件箱和垃圾邮件箱，或准备好邮件号、姓名和联系电话请客服中心重发。柜门打不开请拨打 072 303 304。

**更改派送。** 可在邮局或致电 072 303 304 付费申请重派。

**谁能代领：** 需签收的邮件交给收件人本人、法定代表人或持授权委托书的人；如无法交给本人，可交给 15 岁以上的同住家人，或住宅/工作场所的雇员。邮局柜台取件须核验身份证件；持到达通知单前往柜台出示本人证件即可领取普通包裹、Paket 24 或挂号信（法院及官方公文信函除外）。

## 克罗地亚邮政包裹延误、丢失或破损

Hrvatska pošta 的目标是在次工作日送达 85% 的国内最快档邮件；Paket 24 需 1 至 3 个工作日（海岛 3 个工作日）。如果多天没有动静，参见[物流为什么停止更新](guide:tracking-not-updating)（英文）。

**清关。** 对于欧盟以外的商品，Hrvatska pošta 作为你的报关代理，在派送时收取增值税（及应缴关税）与代理费：无 IOSS 编号且不超过 150 欧元的物品为 2.46 欧元，150 至 1,000 欧元为 4.91 欧元。带有 IOSS 编号的订单商家已代收增值税，不会重复收取增值税或 Hrvatska pošta 代理费。但自 2026 年 7 月 1 日起，无论是否走 IOSS，价值不超过 150 欧元包裹中的每项商品均需缴纳 3 欧元统一关税；自 2026 年 11 月 1 日起，无论订单金额多少，网购商品按种类每类还需缴纳 2 欧元欧盟处理费。详见[包裹卡在海关怎么办](guide:customs)（英文）。如果已经被重复收取商家收过的增值税，可携带付款凭证、Hrvatska pošta 收据和订单发票到邮局或通过网页表单投诉退款，客服中心会在 15 天内答复。

**投诉与索赔**（丢失、延误、破损、内件短少）：国内邮件须在交寄后 3 个月内、国际邮件在 6 个月内，到邮局或通过网页表单提出。破损须在签收当场申报以便出具记录；快递柜包裹须在取件后 1 小时内申报。Hrvatska pošta 国内 30 天内（国际 60 天内）答复，之后可向消费者投诉委员会和监管机构 HAKOM 申诉。赔偿支付给寄件人，因此网购订单须由商家申请理赔。

## Hrvatska pošta 客服电话与邮箱

posta.hr 公布的联系方式：

- **客服中心：** 克罗地亚境内拨打 072 303 304，周一至周五 7:00 至 19:00、周六 7:00 至 15:00，按运营商固话费率计费。请提前备好邮件号。
- **海外拨打：** +385 1 6626 870。
- **邮箱：** kontakt.szk@posta.hr。
- **网页表单：** [kontakt.posta.hr](https://obrasci.posta.hr/kontakt)（克罗地亚语），官方推荐用于投诉。
- **信件地址：** HP - Hrvatska pošta d.d., Poštanska ulica 9, 10410 Velika Gorica。
- **线下网点：** 任意邮局。

不能直接致电邮局或邮递员。

> Hrvatska pošta 绝不会通过短信要求补缴费用，也绝不会通过短信或 Viber 索要银行卡号。诈骗信息会链接到 posta.hr 以外的网站，要求“更新”包裹信息或谎称无法派送，随后套取卡号和 CVC 安全码。官方通知一定带有可在查询页核实的邮件号；任何费用只会在邮递员上门、邮局柜台或快递柜现场支付，切勿点击可疑链接。

## 用 Peek 跟踪 Hrvatska pošta 包裹

对于以 C、E、L 或 R 开头、以 `HR` 结尾且校验位正确的邮政单号，[Peek](/) 会立刻识别为 Hrvatska pošta。对于其他以 `HR` 结尾的有效单号（如 `UA…HR` 或 `VV…HR`），Peek 会立即询问 Hrvatska pošta（及 [Chronopost](carrier:chronopost)），查到后显示“Hrvatska pošta 有这个包裹的记录。”，未查到则显示“……暂未找到。添加后 Peek 会继续查询。”。校验位不对时 Peek 不会查询 Hrvatska pošta，手动指定也无效（会提示 Hrvatska pošta “不使用这种格式的单号”）：请向寄件人核对单号。带有单号的 posiljka.posta.hr 查询链接或 posta.hr 追踪链接总能直接识别为 Hrvatska pošta；其他条码手动输入时不会自动识别，请点“更换”选择。

来自海外的邮件先在寄出国邮政跟进。当寄出国邮政显示目的地为克罗地亚（或未写明目的地而你在克罗地亚查询）时，Peek 也会用同一单号询问 Hrvatska pošta，并在出现新扫描后切换过去，同时保留前期记录。若想一开始就在 Hrvatska pošta 查询，点“更换”选择“Hrvatska pošta”即可。

Peek 会列出 Hrvatska pošta 的自有历史记录，采用其英文站的英文文案。Hrvatska pošta 的时间不带时区，因此 Peek 将克罗地亚邮局和快递柜的扫描按克罗地亚时间读取，海外扫描在网点代码可辨识国家时按该国当地时间读取（多时区国家若能定位地点则按该地点时区，否则按克罗地亚时间）。Peek 可显示包裹待取的邮局或快递柜名称以及重量，但没有预计送达日期，邮编也会略去。在 Hrvatska pošta 自有查询正常响应期间，当你打开包裹页或在派送当天开启通知时，Peek 最多每 10 分钟查询一次（进入派送后每 2 分钟一次，需用浏览器打开官网时每 5 分钟一次），其余时间每小时查询一次，并在状态变化时通知你。更快的查询仅在 Hrvatska pošta 扫描派送当天从克罗地亚时间早上 6 点起运行。连续两天无新动态（在“已生成面单”或“已揽收”状态下为 12 小时）后，改为每小时查询一次。移交其他承运商派送后，则按派送承运商的节奏查询。

## 关于克罗地亚邮政物流查询的常见问题

### 可以用英文查 Hrvatska pošta 吗？

可以。posiljka.posta.hr 默认打开克罗地亚语，但点击 EN 开关或直接访问 /en/tracking 即可全程以英文查件和查看状态。

### 可以查寄往克罗地亚的国际包裹吗？

可以，使用寄件人提供的以寄出国代码结尾的邮政单号即可，Hrvatska pošta 查询页支持查询。Peek 会先在海外邮政跟进，当对方标明发往克罗地亚（或未标明目的地而你在克罗地亚）并在 Hrvatska pošta 出现新扫描后自动切换，也可点“更换”立即选 Hrvatska pošta。

### Hrvatska pošta 快递柜（paketomat）能保管多久？

自短信或邮件通知起 3 天，到期前 12 小时提醒。逾期无人领取会退回寄件人。

:::sources
- [Croatian Post: Track your item](https://posiljka.posta.hr/en/tracking) – 英文查询页、邮件编号、至少 5 位字符、格式报错、HR/EN 切换
- [Hrvatska pošta: Praćenje pošiljaka](https://posiljka.posta.hr/hr/tracking) – 克罗地亚语查询页、字段名称、示例单号
- [Croatian Post: Tracking results](https://posiljka.posta.hr/en/tracking/trackingdata) – 状态文案、标题、重量、无数据提示、客服热线
- [Croatian Post: Contact us](https://hrvatska.posta.hr/en/contact-us) – 072 303 304、海外电话、网页表单、地址
- [Croatian Post: About us](https://hrvatska.posta.hr/en/about-us) – 国家邮政、约 300 组快递柜
- [Croatian Post: Core business](https://hrvatska.posta.hr/en/our-business/core-business) – 邮局、Paket24、快递柜 0–24 开放
- [Hrvatska pošta: Kontakt centar](https://www.posta.hr/kontakt-centar) – 客服中心时间、话费、邮件号
- [Hrvatska pošta: Paketomati](https://www.posta.hr/paketomati-9743) – 3 天、提醒、PIN 或条码、改投链接、刷卡支付、重发码、Viber 与垃圾箱、柜门打不开（第 1–2 页）
- [Hrvatska pošta: Uvjeti korištenja Paketomata](https://www.posta.hr/UserDocsImages/Dokumenti_2024/Uvjeti%20kori%C5%A1tenja%20Paketomata%20-%201.1.2025%20.pdf) – 未投递转快递柜、1 小时内报损、刷卡支付
- [Hrvatska pošta: Unutarnji promet](https://www.posta.hr/unutarnji-promet-2153?pojam=&page=1) – 通知单、5 个工作日、派送尝试、重派、保管最长 30 天、仓储费、代领资格、持通知单领取、不可直拨邮局（第 1–3 页）
- [Hrvatska pošta: Međunarodni promet](https://www.posta.hr/medjunarodni-promet?pojam=&page=1) – 清关费、IOSS、增值税退费申请与材料、15 天答复、滞留海外件（第 1–3 页）
- [Croatian Customs Administration: Nova pravila za robu koja se nabavlja putem prodaje na daljinu – e-trgovina](https://carina.gov.hr/pristup-informacijama/propisi-i-sporazumi/carinsko-postupanje/nova-pravila-za-robu-koja-se-nabavlja-putem-prodaje-na-daljinu-e-trgovina/2718) – 自 2026 年 7 月 1 日起不分增值税路径每类商品统一 3 欧元关税
- [Hrvatska pošta: Opći uvjeti za obavljanje univerzalne usluge](https://www.posta.hr/UserDocsImages/Dokumenti_2026/Op%C4%87i_uvjeti_za_obavljanje_univerzalne%20usluge_1.8.2026.pdf) – 到达通知、5 与 15 个工作日、邮箱、向寄件人理赔
- [Hrvatska pošta: Opći uvjeti za obavljanje ostalih poštanskih usluga](https://www.posta.hr/UserDocsImages/Dokumenti_2026/Opci_uvjeti_za_obavljanje_ostalih_postanskih_usluga_1.11.2026.pdf) – 2026 年 11 月 1 日版：Paket 15 个工作日与一次派送、Paket 24 5 个工作日与后续联系、快递柜 3 天
- [Hrvatska pošta: Rokovi uručenja pošiljaka](https://www.posta.hr/UserDocsImages/Dokumenti_2025/Rokovi_urucenja_posiljaka_20251120.pdf) – 派送时效、Paket 24
- [Hrvatska pošta: Reklamacije / prigovor](https://www.posta.hr/reklamacije/275) – 投诉时限、渠道、答复时间、投诉委员会、HAKOM
- [Hrvatska pošta: Kontakt](https://obrasci.posta.hr/kontakt) – 网页表单、增值税退费申请
- [Hrvatska pošta: Ne nasjedajte na prijevaru](https://hrvatska.posta.hr/hr/ne-nasjedajte-na-prijevaru-hrvatska-posta-sms-om-ne-trazi-novac) – 不通过短信收费、虚假更新信息请求、官方通知带邮件号
- [Hrvatska pošta: Kako prepoznati pokušaje prijevare](https://hrvatska.posta.hr/hr/kako-prepoznati-pokusaje-prijevare-uime-hrvatske-poste) – 虚假短信特征、无法派送诱饵、银行卡号与 CVC 提醒
- [European Commission: EU Customs Reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 自 2026 年 11 月 1 日起每类商品 2 欧元欧盟处理费
- [European Commission: Guidance on the 3 EUR customs duty and Union handling fee](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 不分订单金额每项处理费
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Peek 读取内容、状态文案与含义、拒收格式、链接、从海外邮政切换至 Hrvatska pošta
:::
