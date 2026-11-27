---
title: Chronodiali 物流查询：CHPU 单号、状态与摩洛哥派送
description: 查询摩洛哥 Chronodiali 包裹：了解 CHPU 单号格式、法语物流状态含义、货到付款、三次派送尝试、网点信息与客服联系方式。
slug: chronodiali-tracking
published: 2026-11-27
updated: 2026-11-27
---

Chronodiali（也写作 Chrono Diali）是摩洛哥面向网店的快递公司，由摩洛哥邮政（Barid Al-Maghrib / Poste Maroc）与 Geopost 合资成立。它在摩洛哥全境提供电商包裹送货上门或网点自提服务。如果商家选择了货到付款，你可以在包裹送达时以现金或银行卡向快递员付款。你可在 [chronodiali.ma](https://chronodiali.ma/suivi_colis.html) 的 “Suivre mon colis” 下查询，或直接粘贴单号到上方输入框。追踪单号由发货商家提供，Chronodiali 自己的示例以 `CHPU` 开头。

## 你的 Chronodiali 追踪单号

以下是 Peek 已知的单号格式（数字为虚构示例）：

| 单号 | 说明 |
| --- | --- |
| `CHPU4829103756182947305` | `CHPU` 加 19 位数字：Chronodiali 专用单号，Peek 会立即识别。 |
| `CD4829103756` | `CD` 加 10 位数字。Peek 也能用 Chronodiali 追踪，但该格式也符合 [Colis Privé](carrier:colis-prive)、[Aramex Australia](carrier:aramex-au) 和 [Aramex New Zealand](carrier:aramex-nz)，因此 Peek 会同时向这四家查询归属（详见下文）。 |
| `chronodiali.ma/suivi_colis.html?id=…` | Chronodiali 追踪页链接。无论其中是什么单号，Peek 都会直接按 Chronodiali 读取。 |

Chronodiali 并非法国快递 [Chronopost](carrier:chronopost)，两者的单号和追踪系统互不相同。完全没有单号？请联系商家，或参阅 [如何查找追踪单号](guide:find-tracking-number)（英文）。

## Chronodiali 法语物流状态含义解析

Chronodiali 的追踪记录以法语书写，按包裹常见流程排列如下：

| Chronodiali 原文 | Peek 显示 | 对你的含义 |
| --- | --- | --- |
| `Les données du colis ont été intégrées dans nos systèmes.` | 已生成面单 | 商家已将包裹信息传给 Chronodiali，但 Chronodiali 尚未收到实物。 |
| `Le ramassage a été programmé.` | 已生成面单 | 已预约到商家处揽收。 |
| `Le ramassage est en attente, le livreur se présentera sous peu.` | 已生成面单 | 正在等待快递员前往寄件人处取件。 |
| `Le colis a été collecté par le livreur.` | 已揽收 | 快递员已完成揽收。 |
| `Le colis est arrivé au hub d'origine.` | 运输中 | 已到达 Chronodiali 始发分拨中心。 |
| `Le colis a quitté notre hub.`、`Le colis est arrivé au hub.` | 运输中 | 正在 Chronodiali 各分拨中心之间转运。 |
| `Delivery Scheduled` | 运输中 | 已在目的地分拨中心排入派送路线。 |
| `Out For Delivery`、`Le colis est en cours de livraison.` | 派送中 | 快递员今天正在派送。若为货到付款，请提前准备好款项。 |
| `Le colis a été livré.` | 已签收 | 已送达。 |
| `La livraison a échoué : le client n'a pas répondu ou était injoignable.` | 派送未成功 | 快递员电话无人接听或无法联系到你。 |
| `La livraison a échoué : le lieu de livraison est incorrect ou introuvable.` | 派送未成功 | 地址有误或快递员找不到收货地点。 |
| `La livraison a échoué : le client est indisponible.` | 派送未成功 | 你当时不在场或无法签收。 |
| `La livraison a échoué : le client n'a pas le montant COD requis.` | 派送未成功 | 收件人未备齐货到付款（COD）金额。 |
| `La livraison a échoué : le client a refusé le colis.` | 派送未成功 | 包裹被拒收。 |
| `La livraison a échoué.` | 派送未成功 | 派送失败，未说明具体原因。 |
| `Le colis est retenu au hub.` | 需要留意 | 包裹暂留在分拨中心。 |
| `La livraison a été annulée : le colis est endommagé et ne peut pas être livré.` | 需要留意 | 包裹破损，无法继续派送。 |
| `Le colis a été annulé.` | 需要留意 | 该运单已被取消。 |
| `Le colis va être renvoyé à l'expéditeur.` | 已退回寄件人 | 包裹即将退回商家。 |
| `Le colis a été retourné à l'adresse de l'expéditeur.` | 已退回寄件人 | 包裹已退回商家地址。 |

**chronodiali.ma 上不会显示的记录。** Chronodiali 自己的追踪页会隐藏部分节点，包括 `Delivery Scheduled`、`Out For Delivery`、派送失败、取消或改期记录，以及 `Le colis a été confié à un partenaire tiers.`。Peek 会把这些记录全部显示出来，因此 Peek 中的轨迹可能比 Chronodiali 的更长。

**其他记录。** `La livraison a été reprogrammée : une nouvelle date a été définie.` 表示已重新排定新的派送日期；`La livraison a été reprogrammée.` 则未给出具体细节。`La livraison a été annulée à la demande du client.` 表示应客户要求取消派送。`Le colis est en attente au hub.` 表示包裹正在分拨中心等候。至于涉及 `partenaire tiers` 的两条记录，请参阅下文的问题，Peek 均将其显示为“运输中”。

**跨境入境包裹。** 部分包裹从海外寄往摩洛哥，你可能会看到 `arrivé dans le pays.`（已抵达摩洛哥，Peek 显示“运输中”）、`dédouanement en cours`（正在清关，显示“清关中”）以及 `dédouanement terminé`（清关完成，显示“运输中”）。Chronodiali 的网站未说明关税或由谁承担，请向商家确认，并参阅 [包裹被海关扣留指南](guide:customs)（英文）。

## Chronodiali 派送规则：货到付款、三次尝试与网点

Chronodiali 表示其派送覆盖摩洛哥全境 300 多个目的地，可送货上门或送至网点。

- **货到付款：** Chronodiali 追踪页会以迪拉姆（DH）显示应付金额 `Montant à payer`。Chronodiali 说明可在送达时用现金或银行卡支付。
- **三次派送尝试：** Chronodiali 在服务列表中写明提供 “3 Tentatives de livraison”（三次派送尝试）。在 Peek 中，每次失败都会显示以 `La livraison a échoué` 开头的记录（可能附带原因，也可能不带）。
- **保持电话畅通：** 派送未成功的原因之一是你未接电话或无法联系。
- **消息通知：** 其服务页面写明提供短信与邮件通知，并在运输的每个环节通知客户。
- **营业网点：** 阿加迪尔（Agadir）、卡萨布兰卡（Casablanca）、非斯（Fès）、马拉喀什（Marrakech）、拉巴特（Rabat）和丹吉尔（Tangier），营业时间为摩洛哥时间周一至周五 9:00–18:00、周六 9:00–13:00。具体地址见其 “Points de livraison” 页面。

Chronodiali 的网站未说明网点保管包裹的天数，也未说明第三次派送失败后的处理方式，请咨询 Chronodiali 或商家。错过快递员？请参阅 [派送未成功怎么办](guide:missed-delivery)（英文）。

## Chronodiali 包裹延误、拒收、退回或未收到

如需退款或补发，请联系商家。商家才是 Chronodiali 的签约客户，通过合约平台预约揽收。

- **停留在 `Les données du colis ont été intégrées dans nos systèmes.`：** 商家已生成运单，但 Chronodiali 尚未取件，请向商家询问交寄时间。如果 chronodiali.ma 显示 `En cours de traitement.`（处理中），说明 Chronodiali 已知晓该单号，但尚无扫描记录。
- **延误：** Chronodiali 未公布派送时效，其条款称时效随订单给出并因目的地而异。当最新一条记录已满四天，且包裹仍处于“已生成面单”、“已揽收”或“运输中”时，Peek 会用该记录的日期加以标记（“自10月12日周一起没有物流更新”），并附上“向 Chronodiali 寻求帮助”链接。详见 [物流停止更新](guide:tracking-not-updating)（英文）。
- **拒收或未付款：** 若你拒收包裹或无法支付货到付款，快递员会登记派送失败，包裹随后可能退回商家：`Le colis va être renvoyé à l'expéditeur.`，直至 `Le colis a été retourné à l'adresse de l'expéditeur.`。参阅 [退回寄件人](guide:returned-to-sender)（英文）。
- **显示已送达但未收到：** Chronodiali 会在商户后台向商家提供电子签收凭证，请要求商家核查。更多信息见 [显示已送达却未收到](guide:delivered-not-received)（英文）。
- **破损或丢失：** 先告知商家。你也可以在 Chronodiali 联系表单中勾选 “Réclamation ?”，或附单号致电、发邮件反馈。请参阅 [包裹破损](guide:damaged-parcel)（英文） 和 [包裹丢失](guide:lost-parcel)（英文）。
- **要求付款的短信或邮件：** Chronodiali 在其网站上说明，付款是在送达时以现金或银行卡完成。如果短信或邮件要求你点链接付款，请先查物流并阅读 [防范快递诈骗短信](guide:delivery-scam-texts)（英文）。

## Chronodiali 联系电话与客服

以下联系方式公布于 chronodiali.ma：

- **电话：** +212 5 20 61 62 63（摩洛哥境内拨打 05 20 61 62 63）。
- **电子邮箱：** service-client@chronodiali.ma。
- **联系表单：** 在 [Contact](https://chronodiali.ma/contact_us.html) 页面选择 “Réclamation ?”（投诉）或 “Demande d'information ?”（咨询）。表单仅要求填写姓名、邮箱、电话和商家名称，没有留言栏或追踪单号栏，Chronodiali 表示随后会主动联系你。
- **网点：** 上述六处网点均共用同一电话与邮箱。

其网站未另行公布电话热线的服务时间，也未列出 WhatsApp 号码。

## 使用 Peek 追踪 Chronodiali 包裹

粘贴 `CHPU` 单号后，[Peek](/) 会立即将其识别为 Chronodiali（显示在“识别出的快递公司”下），小写和空格均无影响。直接粘贴 chronodiali.ma 追踪页链接同样适用。

10 位数字的 `CD` 单号无法让 Peek 确定快递公司。你仍可直接粘贴：Peek 跟踪 4,500 多家快递公司。Peek 在向 Chronodiali 及单号格式相同的其他快递公司查询时会显示“正在查询物流服务…”；如果只有 Chronodiali 有这个包裹，它的名称旁会显示“查到了这个包裹”。你也可以点“更换”，在“符合这个单号”下选择 “Chronodiali”。若为其他格式的单号选择 Chronodiali，Peek 会提示 Chronodiali“不使用这种格式的单号”，包裹页面会显示“请检查运单号”。

Peek 读取 Chronodiali 自己的追踪：状态、每条原始节点（法语，偶有前述两条英文）、节点中的分拨中心名称、目的国与签收时间，并将时间换算为你设备的本地时区。Peek 不展示寄件人姓名与城镇、应付金额、签收凭证以及快递员通话记录。若 Chronodiali 返回了相关信息，你还会在“收件人”下看到收件人姓名；包裹在途时，如果 Peek 能将目的地城镇定位在摩洛哥境内，也会在地图上显示；你分享给他人查看时，对方看不到这两项信息，且签收人姓名始终保密，任何应用均不会显示。Chronodiali 追踪不提供预计送达日期或重量。涉及合作承运商（`partenaire tiers`）的记录不会写明合作方名称，Peek 也无法接力追踪合作方系统。

如果 Chronodiali 尚未收录该单号，Peek 会显示“等待 Chronodiali 更新”并持续查询；两天后仍查不到时，Peek 会提示你核对单号、更换快递公司或删除包裹。当你打开包裹，或在开启通知的情况下进入派送日时，Peek 最高每 10 分钟检查一次；在 Chronodiali 扫描出库派送当天，自摩洛哥时间早上 6 点起，只要 Chronodiali 自己的追踪正常响应，Peek 会提高至最高每 2 分钟检查一次。连续两天无新动态时（处于“已生成面单”或“已揽收”时为 12 小时），改为每小时检查一次；若在“已生成面单”下七天无更新，则每 6 小时检查一次。你可选择接收“每次扫描”、“重要环节”或“仅派送当天”通知。Peek 无法代为付款、改约派送或代替你联系 Chronodiali 与商家。

## 关于 Chronodiali 物流查询的问题

### Chronodiali 属于摩洛哥邮政（Poste Maroc）吗？

部分属于。Chronodiali 新闻页说明它是一家合资企业：摩洛哥邮政（Barid Al-Maghrib / Poste Maroc）持股 51%，Geopost 持股 49%。它拥有独立的追踪网站 chronodiali.ma 和独立的客户服务。

### Chronodiali 会尝试派送几次？

Chronodiali 表示会尝试三次。在 Peek 中，每次派送失败都会显示一条以 `La livraison a échoué` 开头的记录（可能附带原因，也可能不带）。

### Chronodiali 包裹可以刷卡付款吗？

可以。Chronodiali 表示货到付款既可以付现金，也可以刷银行卡。

### Chronodiali 追踪里的 “partenaire tiers” 是什么意思？

`Le colis a été assigné à un partenaire tiers.` 和 `Le colis a été confié à un partenaire tiers.` 表示 Chronodiali 已将包裹分配或移交给第三方合作伙伴。轨迹不会列出合作方名称，请等待下一条更新，或携带单号询问 Chronodiali。

### 怎么联系 Chronodiali 查询我的包裹？

请提供追踪单号致电 +212 5 20 61 62 63 或发邮件至 service-client@chronodiali.ma。如需退款或重发，请联系商家。

:::sources
- [Chronodiali: Les experts de la livraison e-commerce](https://chronodiali.ma/) – Barid Al-Maghrib 与 Geopost、货到现金或刷卡付款、送货上门或网点自提、300 多个目的地、网点与营业时间、电话、邮箱
- [Chronodiali: Suivre mon colis](https://chronodiali.ma/suivi_colis.html) – 追踪页、`CHPU` 示例、应付金额、法语状态行、页面筛选展示规则与脚本中的 “En cours de traitement.”
- [Chronodiali: Nos services](https://chronodiali.ma/nos_service.html) – 三次派送尝试、短信与邮件通知、现金或刷卡、电子签收凭证
- [Chronodiali: Points de livraison](https://chronodiali.ma/point_de_livraison.html) – 摩洛哥全境派送、网点与营业时间
- [Chronodiali: Envoyer un colis](https://chronodiali.ma/envoyercolis.html) – 商家账户与揽收、商户端签收凭证
- [Chronodiali: Contact](https://chronodiali.ma/contact_us.html) – 含 “Réclamation ?” 的联系表单与字段、电话、邮箱
- [Chronodiali: Gouvernance](https://chronodiali.ma/aboutus.html) – Barid Al-Maghrib（Poste Maroc）
- [Chronodiali: Actualités](https://chronodiali.ma/actualites.html) – 合资企业、Barid Al-Maghrib 占 51%、Geopost 占 49%
- [Chronodiali: Mention légale](https://chronodiali.ma/mentions_legal.html) – Chrono Diali S.A.（卡萨布兰卡）；条款说明商家合同与揽收、随订单给出的交付时效
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – 单号格式、状态措辞与阶段对应、Chronodiali 网页与 Peek 展示差异
:::
