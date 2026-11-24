---
title: La Poste / Colissimo 物流查询：单号格式与状态说明
description: 查询法国邮政 La Poste 与 Colissimo 包裹：运单号格式、法语物流状态中文说明、派送未成功与取件点、欧盟关税以及客服联系方式。
slug: la-poste-colissimo-tracking
published: 2026-11-24
updated: 2026-11-24
---

Colissimo 是法国国家邮政 La Poste 旗下的包裹服务。将 Colissimo 或 La Poste 的运单号，或 laposte.fr 的查询链接粘贴到上方输入框中即可追踪包裹。La Poste 官方查询页面位于 laposte.fr。运单号由寄件人提供，通常可在商家的发货确认邮件中找到。

## Colissimo 运单号格式

| 格式（示例） | 说明 |
| --- | --- |
| `6A48291736055` | 1 位数字、1 位字母加 11 位数字：Colissimo 包裹，或可追踪信件、挂号信。最后一位是校验码。 |
| `LA482917367FR` | 2 位字母、9 位数字加 `FR`：从法国寄出的国际邮政单号，用于 Colissimo 包裹以及可追踪和挂号信件。`FR` 前面的那位数字是校验码。 |
| `87048291736058D` | 以 `863` 至 `866`、`870`、`875`、`880` 或 `885` 开头的 15 位字符：Smart Data 单号，La Poste 用于可追踪和挂号信件。最后一位是校验字符；去掉最后一位，La Poste 官网同样能查到。 |

以 `XU`、`XR`、`XT`、`XW`、`XY` 或 `PZ` 开头并以 `FR` 结尾的邮政单号属于 Chronopost。12 位单号通常属于 Colis Privé。普通 14 位纯数字单号也符合 DPD 等其他快递公司，因此请核对商家邮件。找不到单号？请参阅[如何查找运单号](guide:find-tracking-number)（英文）。

## Colissimo 物流状态含义

Peek 会逐字保留 La Poste 的法语扫描原文，并根据最新一条记录以你的语言显示对应的物流阶段。以下按包裹常见经过顺序列出 La Poste 的常见状态文案及 Peek 对应显示的阶段：

| La Poste 扫描原文 | Peek 显示 | 含义 |
| --- | --- | --- |
| `Votre Colissimo va bientôt nous être confié ! Il est en cours de préparation chez votre expéditeur.` | 已生成面单 | 寄件人正在打包准备，La Poste 尚未收到包裹。 |
| `Votre colis a été déposé dans un point postal.` | 已揽收 | 寄件人已将包裹投递至邮政网点。 |
| `Votre colis est en transit sur nos plateformes logistiques.` | 运输中 | 正在各分拣平台之间转运。 |
| `Les formalités import/export sont en cours sur votre colis.` | 清关中 | 正在办理海关查验和清关手续。 |
| `Votre Colissimo est en cours de traitement sur le site de tri local.` | 运输中 | 已到达当地分拣站点处理。 |
| `Votre colis est sur son site de distribution. Nous le préparons pour le mettre en livraison.` | 派送中 | 正在分拣装车，准备当天派送。 |
| `Votre colis n'a pas pu vous être remis. Il sera mis en livraison demain (hors dimanche et jours fériés).` | 派送未成功 | 未能成功送达，将在次日重新派送（周日和法定节假日除外）。 |
| `Nous sommes passés mais nous n'avons pu vous remettre votre colis. Il va être acheminé vers votre point de retrait.` | 派送未成功 | 错过派送，包裹正转运至你的取件点。 |
| `Votre colis est disponible dans votre point de retrait pendant un délai de 15 jours calendaires.` | 待取件 | 已在取件点等待你领取，截止时间以消息中写明的期限为准。 |
| `Votre colis est livré dans votre boite aux lettres.` | 已签收 | 已投递至你的信箱。 |
| `Votre colis vous a attendu dans votre point de retrait jusqu'à la date limite. Nous sommes contraints de le renvoyer à l'expéditeur.` | 需要留意 | 超过取件期限未领取，包裹正退回寄件人。 |
| `Votre colis est livré à son expéditeur à la suite d'un retour.` | 已退回寄件人 | 包裹已退回到寄件人手中。 |

最容易引起误解的一条是 `Votre colis est sur son site de distribution…`。字面看起来像是“还停在网点”，实际上这是早晨分拣装入当天派送路线的步骤：通常当天就会送达或出现一次派送尝试。

## 派送未成功与取件点

错过派送后，物流记录通常会写明下一步安排：次日重新派送，或转交取件点（见上表扫描记录）。通知包裹已到达取件点的扫描记录会写明保管期限，例如 `Le délai de retrait est de 5 jours.`（5 天）或 15 个自然日。Peek 会显示保管该包裹的邮局、合作门店或自提柜及其地址。如果逾期无人领取，包裹会退回寄件人。更改派送安排需在 laposte.fr 上直接向 La Poste 办理，Peek 无法代为操作。更多内容见[派送未成功指南](guide:missed-delivery)（英文）和[逾期未取包裹指南](guide:uncollected-parcel)（英文）。

## Colissimo 包裹延误、丢失、破损或清关

**物流不更新。** 请先参阅[物流不更新怎么办](guide:tracking-not-updating)（英文），然后联系寄件人。

**丢失或破损。** 如果是向商家网购，请先联系商家。在欧盟范围内，Your Europe 说明在商品送达你手中之前，运输破损责任由商家承担。若未约定具体送达日期，商家须在 30 天内送达；如未按期送达，你可以催促并给予合理的额外宽限期，期满仍未送达即可取消订单并获退款。另请参阅[包裹丢失指南](guide:lost-parcel)（英文）和[包裹破损指南](guide:damaged-parcel)（英文）。

**向 La Poste 投诉。** 法国邮政监管机构 ARCEP 表示，提交投诉免费，寄件人和收件人均可发起投诉（但双方权利不同），可通过下方渠道提交。若对回复不满意，可通过同样渠道向 La Poste 申诉部门（service recours）申请复核。若复核结果仍不满意，或在提交首次书面投诉满两个月后仍无回复，你可以在首次书面投诉后一年内免费向法国邮政集团调解员（Médiateur du Groupe La Poste）申请调解。

**海关与税费。** 从欧盟以外进入法国的包裹可能需要缴纳增值税（VAT），且对于货值不超过 150 欧元的订单，自 2026 年 7 月 1 日起按商品类别每类适用 3 欧元欧盟固定关税，由卖家或承运商代收。自 2026 年 11 月 1 日起，无论包裹货值多少，还需按商品类别每类缴纳 2 欧元欧盟处理费。法国海关（Douane）说明，承运商（例如 La Poste）可能会在派送时向你收取这些税费，并可能加收其自身的清关服务费——这与海关征收的税费无关。详情请参阅[清关中的包裹指南](guide:customs)（英文）。

## Colissimo 客服电话与联系方式

ARCEP 与法国官方公共服务目录列出的 La Poste 联系渠道如下：

- **从法国境外拨打：** +33 810 821 821（国际客户服务），周一至周五法国时间 8:00 至 19:00。
- **从法国境内拨打：** 3631，周一至周五 8:30 至 19:00，周六 8:30 至 13:00。服务本身免费，仅按普通固话通话计费。
- **在线办理：** 通过 La Poste 官网 laposte.fr。
- **邮寄信件：** Service consommateurs, 99 999 La Poste；也可直接前往邮局咨询。

> 法国政府网络犯罪援助平台 Cybermalveillance.gouv.fr 援引 La Poste 的声明称：La Poste “绝不会通过短信或电子邮件要求你付款以领取包裹”。如有疑问，请勿点击链接，直接前往 laposte.fr 或在 Peek 中核对真实物流。如收到冒充 La Poste 或 Colissimo 的钓鱼邮件，请举报至 phishing@laposte.fr；在法国境内收到诈骗短信可免费转发至 33700 举报。更多防范方法见[快递诈骗短信指南](guide:delivery-scam-texts)（英文）。

## 使用 Peek 查询 Colissimo 包裹

对于校验码有效的 13 位 Colissimo 单号、14 位 `870` 开头单号，以及校验字符有效的 15 位 Smart Data 单号和 `FR` 结尾国际邮政单号，[Peek](/) 会直接识别为 La Poste / Colissimo。其他格式或校验失败的单号，Peek 会仅向 La Poste 查询；与 DPD 等共用的 14 位数字，则会向可能的快递公司查询。如果你为一个 La Poste 不接受的单号（例如校验位错误）手动选择了 La Poste，Peek 会在首次查询时提示：“这家快递公司不使用这种格式的运单号。请检查单号，或选择其他快递公司。”带 `code=` 或 `idShip=` 参数的 laposte.fr 链接可直接粘贴查询；colissimo.fr 链接仅在其中包含 Peek 能立即识别的单号时生效。

Peek 读取的是 La Poste 的法语数据，因此在任何界面语言下扫描文案均保持法语原文。页面会在地图上标出每条扫描记录及其精确时间，并显示预计送达时间、商家名称、签收时间，以及包裹待取件时的邮局、代收点或自提柜名称及地址。所有时间均按你设备的时区换算显示；在 peektracker.com 以及 iPhone 和 Android 应用中，物流历史底部会注明“时间按你所在的时区显示：”及具体时区名称。Peek 会自动去除你的姓名和详细收件地址。当你打开包裹页面查看，或在派送当天开启通知时，只要 La Poste 官方查询正常响应，Peek 最高每 10 分钟检查一次，而在 La Poste 扫描包裹进入派送中的当天早上 6 点（当地时间）起最高每 2 分钟检查一次（通过物流查询服务时，在派送当天打开页面或开启提醒时最高每 15 分钟检查一次）。你可以按需选择“每次扫描”、“重要环节”或“仅派送当天”提醒。当其他承运商接手末端派送时，Peek 会按照实际派送承运商的频率检查：Royal Mail 和 Poste Italiane 为每小时一次，PostNL 和 USPS 最高每 30 分钟一次（派送中同样如此）。其余时间，以及连续两天无更新时（包裹仍处于“已生成面单”或“已揽收”状态时为 12 小时），Peek 每小时检查一次。

Chronopost 服务会交由 Chronopost 继续追踪，La Poste 指定的合作承运商也会在对应承运商处继续跟进；发往海外的国际邮政单号一旦在目的国邮政（如 Royal Mail）出现进展，Peek 也可以继续跟踪。Peek 无法代为更改派送或缴纳关税。

## Colissimo 物流查询常见问题

### Colissimo 是什么？

Colissimo 是法国邮政 La Poste 的包裹派送服务。它在 laposte.fr 上的查询系统同时也覆盖可追踪信件和挂号信。Colissimo 包裹既可以直接投递到你的信箱，也可以存放在邮局、合作门店或自提柜待取。

### Colissimo 派送需要多久？

La Poste 集团公布数据显示，2025 年有 41% 的 Colissimo 包裹在寄出次日送达，集团正加快分拣和派送速度以将次日达打造为标准时效。集团未公布其余包裹的具体统计数据。若 La Poste 提供了预计送达时间，Peek 会一并显示。

### La Poste 周六派送吗？

是的。La Poste 集团表示其信件、报刊和包裹每周派送六天，而且派送未成功后的物流记录也会写明包裹将于次日重新派送，“hors dimanche et jours fériés”（周日和法定节假日除外）。

### 为什么我的 La Poste 单号查不到记录？

请先对照上表核对单号格式：以 `FR` 结尾且以 `XU`、`XR`、`XT`、`XW`、`XY` 或 `PZ` 开头的单号属于 Chronopost。包裹离开法国后，La Poste 的物流页面可能会写明境外派送公司及对方单号；一旦对方有了新动态，Peek 会继续为你跟踪。如果物流只是长时间没有更新，请参阅[物流不更新怎么办](guide:tracking-not-updating)（英文）。

:::sources
- [La Poste Groupe: La branche Services-Courrier-Colis](https://www.lapostegroupe.com/fr/services-courrier-colis) – Colissimo 服务、每周六天派送与 2025 年 41% 次日达数据
- [ARCEP: Comment résoudre un litige avec un opérateur postal ?](https://www.arcep.fr/mes-demarches-et-services/consommateurs/fiches-pratiques/la-resolution-des-litiges-postaux.html) – 寄收双方免费投诉、客服电话与时间、官网、邮寄地址、邮局渠道、申诉部门及调解员
- [Le Médiateur du Groupe La Poste: Foire aux questions](https://mediateur.groupelaposte.com/pages/foire-aux-questions) – 免费调解、需先走两级投诉、满两个月及一年时限
- [Service-Public.fr: Service consommateur de La Poste](https://lannuaire.service-public.gouv.fr/centres-contact/R11647) – 3631 服务时间与资费、国际客服电话与邮寄地址
- [Your Europe: Shipping and delivery](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_en.htm) – 30 天送达规则、额外宽限期、退款、商家承担破损责任及由卖家或承运商代收 3 欧元关税
- [Douane: Droit de douane forfaitaire de 3 euros sur les ventes à distance de biens importés](https://www.douane.gouv.fr/fiche/droit-de-douane-forfaitaire-de-3-euros-sur-les-ventes-distance-de-biens-importes) – 自 2026 年 7 月 1 日起货值 150 欧元及以下按商品类别征收 3 欧元固定关税
- [European Commission: EU Customs Reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 自 2026 年 11 月 1 日起按商品类别征收 2 欧元欧盟处理费
- [Douane: Vous achetez sur internet](https://www.douane.gouv.fr/demarche/vous-achetez-sur-internet) – 承运商（点名 La Poste）在派送时收取税费及其自有清关手续费说明
- [Cybermalveillance.gouv.fr: L'hameçonnage à la livraison de colis](https://www.cybermalveillance.gouv.fr/tous-nos-contenus/actualites/escroqueries-livraison-colis) – La Poste 绝不通过短信或邮件索要取件费用、phishing@laposte.fr 与 33700 举报渠道
:::
