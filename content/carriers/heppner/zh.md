---
title: Heppner 物流查询：单号、邮编、预约时段与客服
description: 查询法国与瑞士 Heppner 货件：8 位单号位置、为何需要收件邮编、物流状态含义、预约派送时段、签收异议及联系方式。
slug: heppner-tracking
published: 2026-11-27
updated: 2026-11-27
---

Heppner 是一家法国公路货运公司，承运从小件包裹到托盘及整车货物的运输，旗下的 Heppner CONNECT 服务专门将网购订单（包括 30 公斤以上大件）派送至私人住宅。该公司在法国本土派送，并在瑞士通过旗下 SAFRAM 公司负责派送。你可以前往 [Heppner 收件人查询页面](https://www.heppner-group.com/en/consignee-track-your-order/)输入 Heppner 通知中的单号与收件邮编进行查询，或将单号粘贴到上方输入框。

## Heppner 单号在哪里找、是什么格式

Heppner 说明追踪单号“可在 Heppner 发送的通知中找到（如适用）”，有时也称为收据号（receipt number）。其表单要求填写单号、收件邮编和收件国家（默认法国，列表中也含瑞士和比利时）。单号栏也接受商家的订单参考号，Heppner 说明该参考号“通常可向供应商获取”。如果两者都没有，Heppner 建议“直接联系寄件人”。另见[如何查找快递单号](guide:find-tracking-number)（英文）。

Heppner 未公布单号规则。Peek 为 Heppner 识别 8 位纯数字单号（以下数字为虚构）：

| 单号 | 可能属于哪家公司 |
| --- | --- |
| `84729163` | Heppner，或同样为 8 位数的 [Mondial Relay](carrier:mondial-relay)、[Colisweb](carrier:colisweb)。 |
| `4829173` | 不是 Heppner：7 位数字指向 SEUR。 |
| `4829173654` | 不是 Heppner：10 位数字符合 [DHL](carrier:dhl)、Mondial Relay、Relais Colis 等。 |

仅凭 8 位数字无法判断是哪家公司在派送你的订单，请核对短信或邮件中的公司名称（见[相似单号格式](guide:tracking-number-formats)（英文））。如果 Heppner 回复“We were unable to locate your order”，页面会提醒你“Check the correspondence between the zip code entered and the tracking number”——邮编填错也会显示成查不到单号。

## Heppner 物流状态及其含义

Heppner 查询页按浏览器语言显示法语、英语或德语（其余显示英语）。其英文状态按出现顺序排列如下：

| Heppner 显示文字 | 对你的含义 |
| --- | --- |
| `Order taken in charge`、`Your order has been collected` | Heppner 已从寄件人处收揽货物。 |
| `Your order has been shipped from the routing centre` | 正在 Heppner 各分拨中心之间运输。 |
| `Your order has arrived at the delivery centre` | 已抵达负责派送的当地网点。 |
| `Waiting for instructions`、`The delivery of your order is awaiting appointment instructions` | Heppner 正在等你选择派送日期或预约网点自提。 |
| `Delivery of your order is scheduled for` | 后面跟着你预约好的派送日期。 |
| `Your order has been sent for delivery` | 今日已装车派送。 |
| `Delivery of your order has been rescheduled, as you were closed or absent` | 司机上门时你不在场或场所未开门，已重新安排。 |
| `Your order is available for collection at the distribution centre` | 货物正在 Heppner 分拨网点等你自提。 |
| `Your order has been delivered`、`Your order has been delivered with qualification(s)` | 已签收；后者表示签收时在回单上写明了破损或缺失保留意见。 |
| `Your order has been returned to the consignor` | 已退回寄件人，请联系商家。 |
| `Issue on inbound scan` | Heppner 入库扫描时发生异常。 |

容易引起误解的措辞：

- `Collection` 和 `Your order has been collected` 指的是 Heppner 从寄件人处揽收货物；由你自己去网点提货时，页面会写 `You have collected your order from the delivery centre`。
- `Shipping` 在 Heppner 英文界面中表示“正在派送”，并不是“商家刚发货”。
- 英文版进度条上，“已送达”一步被误译成 `On delivery`（原文对应法语“Commande livrée”），而“正在派送”一步则保留了法语 `En cours de livraison`。
- `qualification(s)` 指的是签收保留意见，即收货时写在送货单上关于短少或破损的书面备注。

## Heppner 派送：预约、时段与派送未成功

通过 Heppner CONNECT 服务，私人收件人在每个节点都会收到邮件或短信通知，并可随时在查询页提交指令：

- **“I'm having it delivered”（安排送货上门）：** 自行选择派送日期。日间派送时段为 8:30 至 18:00，Heppner 会在派送当日确认一个 2 小时精确时段。仅当寄件人购买了 EXTENT 附加服务时，才提供晚间或周六（8:30 至 18:00）派送。晚间时段于 21:00 结束；查询页写开始时间为 18:00，而 Heppner 的 EXTENT 介绍页写的是 18:30。
- **“I collect my package”（网点自提）：** 前往当地 Heppner 分部自提，查询页会标明最晚保留日期。

在货物出发派送前都可以修改日期；出发后页面会给出派送分部的电话。如果页面没有可选时段，也会提示你致电联系。

**送达位置。** 默认送到建筑入口（楼下大门）。送入指定房间（INHOME）属于寄件人付费附加选项；超过四楼时需配备足以容纳货物的电梯。

**无代收点或自提柜。** 如需自提，唯一地点就是 Heppner 当地分部。

**派送未成功。** 查询页会显示已重新排期。Heppner 未公布具体派送尝试次数。其 EXTENT 页面写明免费仓储期为 5 天（购买 EXTENT 为 7 天），请尽快预约新日期。

## Heppner 货件延误、破损或丢失怎么办

你的买卖合同是与商家签订的，Heppner 的理赔须知和条款均面向寄件人及企业收货方，因此请先联系商家。

**多日无更新。** 请先检查状态是否为 `Waiting for instructions`：在你选择派送日期或自提之前，货物不会继续移动。其他情况参阅[物流长期不更新](guide:tracking-not-updating)（英文）。

**收货时破损或短少。** 务必当着司机面验货，并在送货回单上写明具体保留意见（例如“1 件，货号 X，污损，无法销售”）。Heppner 拒收笼统备注（如“待拆箱查验”）。在法国境内，不够具体的保留意见（如“包裹受潮”）须在收货后 72 小时内书面确认；足够具体的则无需再次确认。国际运输没有 72 小时补确认窗口：Heppner 法文说明要求收货当时即写明具体意见，英文说明则要求在收货时立即通知 Heppner 客服。

**货物丢失。** 法国境内在约定派送日满 30 天后视为推定丢失，国际公路运输为 60 天。理赔需提供收据号、金额及凭证、签收保留意见、装箱单、重量和照片；Heppner 条款规定服务完成一年后不再受理诉讼或索赔。请将上述材料交给商家，由商家向 Heppner 索赔。

## Heppner 客服与联系方式

根据 Heppner 网站公布的信息：

- **法国：** 无面向收件人的全国客服电话、邮箱或在线聊天。请使用 [Contactez-nous 联系表单](https://www.heppner-group.com/contactez-nous/)，主题选择“Suivi d'un transport en cours”并填写你的追踪单号。
- **派送分部：** 追踪页面中的“Your branch”会显示对应分部的营业时间与电话；全部网点也可在 [Heppner 网点查询页](https://www.heppner-group.com/reseau/)查找。
- **瑞士（SAFRAM）：** 日内瓦（Satigny）+41 22 827 03 11、巴塞尔 +41 61 338 93 33、Bardonnex 海关办事处 +41 22 771 43 13，服务时间均为瑞士时间周一至周五 08:00 至 17:00。
- **理赔邮箱：** 法国境内 indemnisations.nationales@heppner-group.com，国际运输 indemnisations.internationales@heppner-group.com（供商家或寄件人使用）。

## 使用 Peek 追踪 Heppner 货件

[Peek](/) 无法主动询问 Heppner、Mondial Relay 或 Colisweb 是否认识某个 8 位数字，因此单凭这个数字无法确定是哪家快递公司：请点“更换”并选择 Heppner。在瑞士地区访问时，Peek 会优先展示 Heppner 的邮编栏；在法国、比利时、卢森堡、西班牙或葡萄牙则优先展示 Mondial Relay 的邮编栏，请先切换为 Heppner。粘贴 Heppner 链接无法提取信息，请直接输入 8 位数字。

选定后 Peek 会要求输入收件邮编，未填邮编无法向 Heppner 查询：5 位邮编按法国查询；4 位邮编先按瑞士查，再依次尝试比利时和卢森堡。填错邮编的结果看起来和查不到单号一样。

Peek 会展示当前阶段以及带精确时间的完整轨迹，每一步均以 Peek 自己的说法呈现：快件已揽收、运输中、快件已转寄、派送中、已签收、已退回寄件人、等待派送指示、快件异常。等待预约指示、入库扫描异常以及货件取消（“Shipment cancelled”）都会标记为异常状态。若 Heppner 提示货物将换用新参考号改寄新地址，Peek 会显示为“Forwarded to a new address under a new reference”，仍归类为运输中。轨迹不包含扫描地点、预计日期或自提点，且不显示寄件人、收件地址及派送指示。

当你打开货件页面或在派送日开启通知时，只要 Heppner 自己的查询页正常响应，Peek 最高每 10 分钟检查一次，在 Heppner 扫描出库派送当天自当地早上 6 点起每 2 分钟检查一次；其余时间每小时一次。通知可选择每次扫描、仅重要环节或仅派送当天。若连续两天无新动态（处于“已生成面单”或“已揽收”状态时为 12 小时），Peek 改为每小时查询一次。Peek 无法代你预约时段，请在 Heppner 的页面上操作。

## Heppner 物流查询常见问题

### Heppner 会送货上门吗？

会的。Heppner CONNECT 专门将包括大件重货在内的网购订单派送给私人收件人和小型企业；除非寄件人额外购买了送货入户服务，否则默认送到建筑入口。

### Heppner 周六或晚间派送吗？

只有当寄件人购买了 Heppner 的 EXTENT 附加服务时才会提供。否则你可以选择工作日 8:30 至 18:00，由 Heppner 在派送当天确认 2 小时时段。

### 可以在比利时查询 Heppner 货件吗？

可以。比利时在 Heppner 自己查询页的国家列表中，Peek 遇到 4 位邮编时也会先查瑞士，再查比利时和卢森堡。

### Heppner 是一家什么公司？

它是一家总部位于法国斯特拉斯堡的家族运输公司，自称保持独立运营已满百年。在瑞士，集团通过成立于 1965 年、现隶属于 Heppner 集团的 SAFRAM 开展业务。

:::sources
- [Heppner: Consignee, track your order](https://www.heppner-group.com/en/consignee-track-your-order/) – form fields, where the number is, errors, countries
- [Heppner: Destinataire, suivez votre marchandise](https://www.heppner-group.com/destinataire-suivez-votre-marchandise/) – same in French, contact link
- [Heppner: tracking portal, English texts](https://myportal.heppner-group.com/assets/i18n/en.json) – English wordings, delivery choice, slots, branch pickup
- [Heppner: tracking portal, French texts](https://myportal.heppner-group.com/assets/i18n/fr.json) – French wordings behind the English ones
- [Heppner: Heppner Connect](https://www.heppner-group.com/en/overland-solutions/heppner-connect/) – private recipients, 30 kg, 24/7 changes, EXTENT, INHOME
- [Heppner: Extent & Inhome](https://www.heppner-group.com/en/overland-solutions/solutions-de-messagerie-b2c/) – evening 18:30 to 21:00, Saturday, free storage 5 or 7 days
- [Heppner: Track your shipments](https://www.heppner-group.com/en/overland-solutions/track-your-shipments/) – email and SMS notifications
- [Heppner: Report a loss or a damage](https://www.heppner-group.com/wp-content/uploads/2024/10/Compensation_Heppner_EN.pdf) – reservations, presumed loss, claim contents, emails
- [Heppner: Déclarer une perte ou une avarie](https://www.heppner-group.com/wp-content/uploads/2024/10/FicheIndemnisation_Heppner_FR-2024.pdf) – 72 hours, vague reservations
- [Heppner: Conditions générales de vente 2025](https://www.heppner-group.com/wp-content/uploads/2025/10/CGV-integrales-2025.pdf) – reservations, refusal costs, one-year limit
- [Heppner: Contactez-nous](https://www.heppner-group.com/contactez-nous/) – contact form, subjects, countries
- [Heppner: Notre réseau](https://www.heppner-group.com/reseau/) – branch finder
- [Heppner: SAFRAM Genève](https://www.heppner-group.com/store/safram-geneve/) – address, phone, hours
- [Heppner: SAFRAM Basel](https://www.heppner-group.com/store/safram-basel/) – address, phone, hours
- [Heppner: SAFRAM Bardonnex](https://www.heppner-group.com/store/safram-bardonnex/) – customs office, phone, hours
- [Heppner: Heppner in Switzerland](https://www.heppner-group.com/en/heppner-group/heppner-in-switzerland/) – SAFRAM, Swiss branches
- [Heppner: home page](https://www.heppner-group.com/) – who Heppner is, family company
- [Heppner: Mentions légales](https://www.heppner-group.com/mentions-legales/) – company, Strasbourg head office
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – what Peek reads and shows
:::
