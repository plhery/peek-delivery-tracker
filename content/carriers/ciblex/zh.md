---
title: Ciblex Express 物流查询：运单号、法语状态与联系方式
description: 查询法国与比利时 Ciblex Express 包裹：14/24 位条码格式、法语状态中文说明、C Dispo 自提点、理赔时限及联系方式。
slug: ciblex-tracking
published: 2026-11-26
updated: 2026-11-26
---

Ciblex（又称 Ciblex Express）是一家成立于 1977 年的法国快递公司，主要面向法国、比利时及跨境企业客户，常在清晨时段完成派送。你可以凭面单上的条码在 [Ciblex 官方查询页面](https://ciblex.eu/en/fast-tracking-of-your-parcels/)免费查询，或直接将单号粘贴到上方输入框。单号由寄件人提供；如果手上没有，请联系发货的网店或供应商。

## Ciblex 运单号格式与查找位置

Ciblex 查询系统要求输入印在面单上的包裹条码。官网未公布条码长度，也未说明收件人还可从何处获取，因此包裹抵达前请直接向寄件人索要。另见[如何查找运单号](guide:find-tracking-number)（英文）。

实际使用的两种格式如下：

| 格式 | 示例编号 |
| --- | --- |
| 14 位数字 | `14829173640582` |
| 24 位数字（完整面单条码） | `482917365482917365482917` |

在 Ciblex 查询结果页的轨迹上方，有时会额外显示一个较短的编号以及括号中的寄件人参考号，这两者都不是运单号。

14 位数字同时也符合 [Chronopost](carrier:chronopost)、DPD、SEUR、BRT 和 DHL eCommerce UK；24 位数字同样符合 InPost、[Austrian Post](carrier:austrian-post) 和 [bpost](carrier:bpost)。如果单号来自 Relais Colis 或 DPD 的邮件或短信，则属于对方承运商。Ciblex 的电商服务页面也明确写着个人消费者订单通过 Relais Colis 派送，因此网购包裹可能需要在 Relais Colis 追踪。更多辨别方法见[运单号格式指南](guide:tracking-number-formats)（英文）。

## Ciblex 法语物流状态含义

Ciblex 的查询结果仅提供法语：ciblex.eu 上的英文和荷兰语表单同样打开这份法语结果页，且无语言切换按钮。结果包含 `Date`（日期）、`Heure livraison`（派送时间）、`Action`（操作）和 `Lieu`（地点，通常为分拨站城镇加省份代码）四列。以下按流转顺序说明常见法语状态：

| Ciblex 显示 | 对你的含义 |
| --- | --- |
| `COLIS NON REMIS` | 常出现在第一行，表示包裹尚未送抵 Ciblex 分拨站。 |
| `Colis pris en charge` | Ciblex 已从寄件人处揽收包裹。 |
| `Colis Contrôle` | 包裹已在 `Lieu` 栏所示的 Ciblex 分拨站或网点扫描。 |
| `Acheminement contractuel du colis en 48h00` | 寄件人所订服务的合同参考时效，并非新的运输节点。 |
| `Mis en livraison` | 今日派送中。 |
| `PROBLEME DESTINATAIRE` | 派送途中遇到与收件人有关的问题，包裹随后返回分拨站。Peek 会显示为“Shipment exception”。 |
| `COMPLEMENT ADRESSE` | 需要补充地址细节后 Ciblex 才能继续派送。 |
| `Disponible au relais` | 已送达自提点等待领取。 |
| `Colis Livré` | 已签收送达。 |
| `Retour expéditeur` | 正在退回寄件人。 |

`COLIS NON REMIS` 字面看似令人紧张（“包裹未交寄”），但 Ciblex 并未另行解释；它出现在首位时通常只是早于首次分拨站扫描的预登记状态，待 Ciblex 扫描后轨迹即会推进。`Colis Contrôle` 是分拨站扫描而非海关查验，一趟运输经过多个分拨站时会多次出现。48 小时文字与分拨站扫描并列出现，仅说明合同服务级别而非包裹所在位置。`Colis pris en charge`、`COMPLEMENT ADRESSE`、`Disponible au relais` 与 `Retour expéditeur` 见于较早期的 Ciblex 查询记录，而非当前官网页面直接展示的状态。

## Ciblex 派送方式：签收、安全存放与 C Dispo 自提点

Ciblex 主要服务企业寄件人。其 H8、H9 和 H12 服务在法国本土提供次日早上 8 点、9 点或中午 12 点前当面签收派送（周一至周五，可选周六上午）。在常规覆盖城镇，Ciblex 将货物送至工业或商业场所的一楼地面或庭院，条款认可电子化签名作为签收凭证。

SP8 与 SP9 服务则在周二至周六早上 8 点或 9 点前，于无人值守时段将包裹投递至 Ciblex 备案的安全存放处（如专用箱或气闸间）。

**C Dispo 自提点。** Ciblex 面向专业客户设有 300 个自提点，由寄件人指定。包裹自早上 8 点起可凭身份证件或公司工牌领取；到件时会发送短信，3 天未取会发送短信或邮件提醒。

**错过派送或拒收。** Ciblex 条款规定，留有通知单未领或被拒收的包裹在 **7 个自然日**内退回寄件人，费用由寄件人承担。遇地址错误、无人留条或假期歇业等情况时，Ciblex 会通知寄件人，由寄件人在客户后台下达新的派送指示。Ciblex 没有收件人自助改期页面，请直接联系寄件人。官网未公布派送尝试次数、是否留纸质通知单以及 C Dispo 自提点的最长保管期限。

## Ciblex 包裹延误、遗失或破损

Ciblex 的服务条款面向持有运输合同的寄件人。由于官网未提供收件人处理物流停滞的指引，请先联系寄件人；另见[物流停止更新的原因](guide:tracking-not-updating)（英文）。

自 2026 年 1 月 1 日起生效的条款规定如下时限：

- **破损：** 收货时须在运单上写明具体的破损保留意见。法国境内若仅写笼统保留意见，须在签收后 **3 天**内（不含公众假期）寄送带回执挂号信确认；国际包裹的隐藏破损须在 **7 天**内书面申报。
- **遗失：** 索赔须在预计送达日起 **60 天**内送达 Ciblex。
- **延误：** 因延误造成的损失须在法国境内签收后 **3 天**内、国际包裹 **21 天**内提出。

Ciblex 在法国境内的赔偿上限为每公斤 €23、每件包裹 €750；国际运输为每公斤 8.33 特别提款权（SDR）。无人认领或无法识别的包裹保管 4 个月。涉及清关时，关税、税费及报关代理费由收件人承担并在派送时支付。

## Ciblex 联系方式：电话、邮箱与网点

ciblex.eu 公布的联系渠道如下：

- **电话：** `08 26 82 69 99`（见于 Ciblex 法律声明）。
- **电子邮箱：** `ciblex@ciblex.fr`。
- **网点分布：** [网点地图页面](https://ciblex.eu/agences/)列出法国 23 家网点与比利时扎芬特姆（Zaventem）1 家网点及其营业时间。多数法国网点营业时间为 07:30–12:00 及 13:00/14:00–18:00、周六 07:30–12:00；扎芬特姆网点为周一至周五 06:00–18:00、周六 06:00–12:00。
- **个人数据事务：** `dpo@ciblex.fr`。

ciblex.eu 上的联系表单仅用于企业销售咨询，不处理查件问题。Ciblex 未设专门的收件人客服，遇派送问题请先联系寄件人。

## 使用 Peek 跟踪 Ciblex 包裹

[Peek](/) 支持识别 Ciblex 的 14 位和 24 位单号，但由于这两种长度与其他快递公司重叠，Peek 会同时向可能的承运商查询。对于法国地区的 14 位单号，Peek 会优先询问 Chronopost 和 Ciblex（仅当单号前缀明确指向 DPD、DHL 或其他网络时才会先问对方）；在法国以外地区，Ciblex 排在第 11 位或更后，超出保存前同时查询的 10 家上限，因此需要保存包裹后才能查到。唯一的例外是以 `10` 开头的 14 位单号（无论是否在法国）：Peek 会先问 Ciblex、SEUR 和 BRT（不问 Chronopost），只有三者及随后的物流查询服务都查不到时，才会打开浏览器查询 [DPD France](carrier:dpd-fr)。对于 24 位单号，Peek 会询问 Ciblex、Austrian Post 和 InPost（仅在单号以 `3232` 或 `3299` 开头时优先问 bpost）。如果两家承运商均查有记录，Peek 会让你手动选择。粘贴带 `colis=` 参数的 `secure.extranet.ciblex.fr` 链接同样有效，但 ciblex.eu 网页链接不支持解析。

当你正在 Peek 中查看包裹，或派送当天已开启通知且 Ciblex 官网查询正常响应时，Peek 最快每 10 分钟检查一次，并在 Ciblex 扫描出派送中当天从巴黎时间早上 6 点起最快每 2 分钟一次；其余情况下每小时检查一次。连续两天无新动态（或在“已生成面单”/“已揽收”停留满 12 小时）后也改为每小时一次。Peek 会展示状态、在地图上标出每次扫描的分拨站城镇以及签收时间，时间按巴黎时间解析并换算为你设备的本地时间。Peek 能识别的常见步骤会转为清晰英文说明（如 Delivered、Out for delivery、Ready for pickup、Delivery issue、针对 `PROBLEME DESTINATAIRE` 或 `COMPLEMENT ADRESSE` 的 `Shipment exception`，或 `Parcel processed at a Ciblex facility`）；包括 `COLIS NON REMIS` 和 48 小时合同说明在内的其他行则保留 Ciblex 法语原文。你也可以选择“每次扫描”、“重要环节”或“仅派送当天”通知。

页面不显示预计送达日期、自提点名称/地址或重量；当 Ciblex 将收件地址写入地点栏时，Peek 也会将其过滤。

## Ciblex 物流查询常见问题

### Ciblex 物流里的 `Colis Contrôle` 是什么意思？

表示包裹在 `Lieu` 栏所列城镇的 Ciblex 分拨站或网点完成了扫描。一趟运输过程中通常会出现多次。

### Ciblex 周六派送吗？

可按需提供：Ciblex 面向专业客户提供周六上午 8 点、9 点或中午 12 点前的派送服务。

### Ciblex 和 Relais Colis 是同一家快递吗？

不是，两家是各自独立追踪的快递公司。不过 Ciblex 电商页面明确说明面向个人消费者的包裹经由 Relais Colis 派送，如果网店写明由 Ciblex 发货，建议两家都查一下。

### Ciblex 在比利时派送吗？

是的。Ciblex 在比利时扎芬特姆（Zaventem）设有网点，并在斯哈尔贝克（Schaerbeek）、沙勒罗瓦（Charleroi）和那慕尔（Namur）设有分拨站，同时宣称承运发往全球超过 166 个国家的货物。

:::sources
- [Ciblex: Fast tracking of your parcels](https://ciblex.eu/en/fast-tracking-of-your-parcels/) – barcode, free tracking, customer area, French results
- [Ciblex: Suivi rapide de vos colis](https://ciblex.eu/suivi-colis-express/) – same in French, problems reported to the sender
- [Ciblex: Suivi de vos colis](https://secure.extranet.ciblex.fr/extranet/client/corps.php?module=colis) – French wordings, columns
- [Ciblex: Mentions légales](https://ciblex.eu/mentions-legales/) – terms from 1 January 2026, delivery level, returns, reserves, claims, liability, customs, phone, email
- [Ciblex: Legal notice](https://ciblex.eu/en/legal-notice/) – the same terms in English
- [Ciblex: Entreprise](https://ciblex.eu/entreprise/) – B2B express carrier since 1977
- [Ciblex: C Dispo – Point relais](https://ciblex.eu/offres-de-livraison-express/cdispo-point-relais/) – 300 relays, ID, SMS, 3-day reminder
- [Ciblex: E-Commerce – The Express Service for Private Individuals](https://ciblex.eu/en/services/e-commerce-the-express-service-for-private-individuals/) – private deliveries via Relais Colis
- [Ciblex: Agences](https://ciblex.eu/agences/) – agencies, depots, hours, 166 countries
- [Ciblex: Contact](https://ciblex.eu/en/contact/) – sales form
- [Ciblex: SP9 – Livraison en SAS avant 9h](https://ciblex.eu/offres-de-livraison-express/sp9-livraison-sas-avant-9h/) – safe-place delivery
- [Ciblex: H12 – L'express avant midi](https://ciblex.eu/offres-de-livraison-express/h12-livraison-24h/) – before noon, signature
- [Ciblex: Livraison samedi matin](https://ciblex.eu/offres-de-livraison-express/livraison-samedi-matin/) – Saturday mornings
- [Ciblex: Le groupe Walden a finalisé le rachat de Relais Colis](https://ciblex.eu/le-groupe-walden-a-finalise-le-rachat-de-relais-colis/) – link with Relais Colis
- [Relais Colis: Accueil](https://relaiscolis.com/) – Relais Colis's own site
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – what Peek reads and shows
:::
