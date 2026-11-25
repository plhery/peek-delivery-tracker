---
title: 法国 Colis Privé 快递查询：12 位单号、邮编与状态
description: 查询法国、比利时和卢森堡 Colis Privé 包裹：12 位单号与邮编输入规则、法语物流状态中文解读、自提点改派、理赔及客服联系方式。
slug: colis-prive-kuaidi-chaxun
published: 2026-11-24
updated: 2026-11-24
---

Colis Privé 是法国面向电商的包裹快递公司，在法国、比利时和卢森堡提供送货上门、自提点以及（法国境内）快递柜派送服务。你可以凭 12 位运单号和收件邮编在法国官网 [colisprive.fr](https://colisprive.fr/en/) 或比利时/卢森堡官网 [colisprive.be](https://colisprive.be/en/) 查件。运单号可在商家的发货邮件或 Colis Privé 的通知里找到。2026 年 10 月 16 日，Colis Privé 宣布将与 [Paack](carrier:paack) 统一使用 Paack 一个品牌，自 2027 年 1 月起逐步推行；在此之前其服务（包括派送与查询）照常运行。

## Colis Privé 运单号在哪里找？

Colis Privé 帮助中心列出四个查找位置：商家订单确认邮件、Colis Privé 官方通知（仅在商家开通后发送）、商家网站的个人账号页面以及投递通知单。商家给的订单参考号可能[不同于快递运单号](guide:find-tracking-number)（英文）。Colis Privé 官方通知邮件仅来自 livraison@notification.colisprive.com。

运单号为 12 位字母与数字组合：

| 格式 | 虚构示例 |
| --- | --- |
| 两位字母 + 十位数字 | `KL4829173648` |
| 一位字母 + 十一位数字 | `K48291736482` |
| 十二位纯数字 | `847291736482` |

官方查询时还必须填写收件邮编；若系统未识别单号，页面会直接跳回空白表单。12 位纯数字同样匹配 [FedEx](carrier:fedex)、Mondial Relay、[BRT](carrier:brt) 等快递；`CC` 加十位数字属于 Relais Colis，`FR` 加十位数字属于 Amazon Logistics。请核对邮件中的承运商名称，或参考[运单号格式说明](guide:tracking-number-formats)（英文）。Colis Privé Store 订单的参考号通常以 `HS` 或 `2R` 开头，只能在 colisprive-store.com 按参考号查询。

## Colis Privé 法语物流状态中文解读

Peek 直接抓取 Colis Privé 的法语轨迹（每一步为一句带日期的法语描述），其英文帮助页则使用 `in transit` 等简化标签（详见[物流状态说明](guide:tracking-statuses)（英文））。Peek 识别的法语状态，按包裹依次经历的顺序如下：

| Colis Privé 状态原文 | 中文含义 |
| --- | --- |
| `arrivé chez le partenaire d'expédition` | 包裹在另一家发运商手中，正转交给 Colis Privé。 |
| `en transit vers` | 正运往 Colis Privé 分拨网络。 |
| `pris en charge` | Colis Privé 已揽收包裹。 |
| `arrivé sur notre agence` | 已抵达当地派送网点。 |
| `rendez-vous est confirmé` | 派送日期已确认（行尾注明日期）。 |
| `en cours de distribution par le livreur` | 快递员正在派送中。 |
| `nous avons tenté de livrer` | 快递员上门尝试派送但未能送达。 |
| `vous attend au relais` | 已存放在自提点等你领取。 |
| `a été livré` | 已签收（投入信箱或当面交付）。 |
| `incident` | 出现异常，Colis Privé 正在核查。 |
| `retourné à l'expéditeur` | 已退回给商家。 |

Colis Privé 帮助页还提到 `Colis attendu par Colis Privé`（英文帮助写作 `Package awaiting Colis Privé`），Peek 不将其归入具体阶段，意为 Colis Privé 尚未从寄件人处拿到包裹（仍在商家手中，比利时站补充称也可能正在国际运输或清关中），建议咨询商家。`pris en charge` 并非“已解决”，只是表示 Colis Privé 已揽收；`va être prochainement déposé` 预告包裹将在指定日期存入自提点，`subi un retard` 表示出现延误。

## 派送时段、派送未成功与自提点规则

**法国：** 派送时间为每天 8:00 至 19:00（官网未标明具体星期）。尺寸合适会直接投入信箱，需签收的当面交付，或交由邻居、门房代收；否则留下通知单。若商家允许改派，可在查询页点击 “Modify my delivery”（“Convenir d'une date de présentation”），选择家中 12:00 至 21:00 的新日期、同邮编内其他收件人地址或改投自提点；否则包裹会存放在最近的自提点。重新预约时限为 10 个工作日，自提点保管期为 8 天，逾期退回商家。

Colis Privé 称在法国本土拥有超过 5,500 家自提点（可在 client.colisprive-store.com/relais 查询网点），户外快递柜凭邮件和短信发送的取件码开启。

**比利时与卢森堡：** 派送时间为周一至周六 12:00 至 22:00。如果家中无人，包裹可能交由通知单上注明的邻居代收。只有在 colisprive.be 查件页预约才会安排二次派送（可改日期、自提点或同邮编地址），请尽早预约，官网不同页面写明超 8 天或 10 天将退件。其 800 家自提点（[网点查询页](https://colisprive.be/trouver-un-relais/)）保管期为 8 个自然日。

## 包裹延误、丢失或破损怎么处理？

Colis Privé 负责受理派送申诉，但退款或补发需遵循商家条款。

**法国：** 先查看 colisprive.fr 顶部横幅是否有全网延误通告。如包裹延误、破损或未收到，查件页面会出现 “File a claim”（“Déposer une réclamation”）按钮。不同帮助页给出的回复时限略有差异：理赔页写明 2 至 7 个工作日（邮件或电话答复），延误与已签收未收到页写明 48 个工作小时。没有按钮，说明已有申诉在处理（可能由商家提交），或已过时限。显示已签收但没收到时，先核查信箱、门房和邻居，再提交申诉；另见[显示已签收但未收到](guide:delivered-not-received)（英文）。

**比利时与卢森堡：** 揽收后一般 48 小时送达。延误超过 4 天可在 colisprive.be 申诉，回复至少需 4 个工作日。申诉按钮仅在送达后一个月内显示。包裹一旦退回寄件人，只有商家能处理。在比利时若对处理结果不满，可免费向邮政调解机构（ombudsmanposte.be）申诉。

若轨迹多日不动，参见[物流停止更新怎么办](guide:tracking-not-updating)（英文）。

## Colis Privé 客服电话与联系渠道

Colis Privé 官网列出的联系方式：

- **法国：** 0 806 000 250（免服务费、仅收普通通话费），法语页面注明法国时间周一至周五 9:00–19:00、周六 9:00–13:00。不设公开客服邮箱，申诉均通过查件页提交。
- **比利时与卢森堡：** 无对外接听电话。colisprive.be 英文联系页给出邮箱 suividecolis@colisprive.com，荷兰语页面给出 opvolgenvanpakketten@colisprive.com。来信需附姓名、邮编与运单号，邮件理赔需 4 至 7 天。

> Colis Privé 声明绝不会收取查件费、通过邮件或短信索要银行信息，也不会要求拨打高价付费电话。在法国可将可疑短信转发至 33700 举报。

## 用 Peek 查询和跟踪 Colis Privé 包裹

[Peek](/) 需要 12 位运单号加上收件邮编。输入单号并选定 Colis Privé 后，页面会出现邮编栏：法国填 5 位数字、比利时填 4 位数字、卢森堡填 `L` 加 4 位数字。你也可以直接把单号和邮编一起输入（以下均为虚构示例）：`KL4829173648 75001`（法国）、`KL4829173648 B1000`（比利时）或 `KL4829173648 L4829`（卢森堡）；保留 `B` 或 `L` 前缀，Peek 就能自行认出 Colis Privé。也可以直接粘贴 colisprive.com 或 colisprive.fr 查件链接。

当你打开包裹页面或在派送当天开启提醒时，Peek 最高每 10 分钟检查一次。由于 Colis Privé 轨迹只含日期不含具体时刻，派送当天最高每 2 分钟一次的高频检查仅在派送当日自法国时间早上 6 点起、且 Colis Privé 自己的查询系统正常响应时运行。其余时间，以及连续两天无新动态（或在“已生成面单”、“已揽收”状态下满 12 小时），Peek 改为每小时检查一次；“已生成面单”满七天无动态则每 6 小时一次。可以设置“每次扫描”、“重要环节”或“仅派送当天”提醒。Peek 会显示包裹状态、法语历史轨迹和预计送达日期，并在“收件人”一栏仅向你本人显示收件人姓名。Peek 无法代你发起理赔或联系 Colis Privé。

## Colis Privé 查询常见问题

### Colis Privé 是什么快递？

一家创立于 1993 年的法国电商包裹快递公司，自 2021 年起同步在比利时和卢森堡开展派送业务。

### Colis Privé 周六派送吗？

在比利时和卢森堡周六派送（12:00 至 22:00）；法国官网仅列出了每日 8:00 至 19:00 的派送时段，未注明具体派送星期。

### Colis Privé 派送需要几天？

Colis Privé 官方宣传法国、比利时和卢森堡境内时效为 24 至 48 小时。

### Colis Privé 在比利时有客服电话吗？

没有。Colis Privé BeLux 不设呼入电话，仅通过电子邮件受理咨询；0 806 000 250 仅服务发往法国的包裹。

:::sources
- [Colis Privé: How do I track my package?](https://colisprive.fr/en/aide/how-do-i-track-my-package/) – 单号与状态说明
- [Colis Privé BeLux: Where can you find your tracking number?](https://colisprive.be/en/aide/where-can-you-find-your-colis-prive-tracking-number/) – 单号查找位置
- [Colis Privé: Delivery hours](https://colisprive.fr/en/help/delivery-hours-2/) – 派送时段与通知单
- [Colis Privé: Reschedule a delivery](https://colisprive.fr/en/reschedule-a-delivery/) – 改派与保管期限
- [Colis Privé: How does a locker work?](https://colisprive.fr/en/help/how-does-a-locker-work/) – 户外快递柜取件
- [Colis Privé: How to file a complaint?](https://colisprive.fr/en/aide/how-to-file-a-complaint/) – 理赔流程与 2 至 7 个工作日回复
- [Colis Privé: My parcel is delayed](https://colisprive.fr/en/aide/my-parcel-is-delayed/) – 延误横幅与 48 工作小时回复
- [Colis Privé BeLux: What time will I receive my package?](https://colisprive.be/en/aide/onze-levertijden/) – 比卢派送时段
- [Colis Privé BeLux: Delivery notice](https://colisprive.be/en/aide/i-received-a-delivery-notice-in-my-mailbox/) – 二次预约派送
- [Colis Privé BeLux: Returned to the sender](https://colisprive.be/en/aide/my-package-was-returned-to-the-sender-without-my-authorization/) – 8 天退回规则
- [Colis Privé BeLux: My package is delayed](https://colisprive.be/en/aide/delay-in-delivery-of-my-package/) – 比卢延误申诉
- [Colis Privé BeLux: Submit a claim](https://colisprive.be/en/aide/the-file-a-complaint-button-does-not-appear/) – 一个月申诉时限
- [Colis Privé BeLux: Contact customer service](https://colisprive.be/en/aide/i-didnt-find-an-answer-to-my-question-and-would-like-to-contact-colis-prive-customer-service-directly/) – 邮箱与 4 至 7 天回复
- [Colis Privé BeLux: Klantendienst](https://colisprive.be/nl/aide/ik-heb-geen-antwoord-op-mijn-vraag-gevonden-en-wil-rechtstreeks-contact-opnemen-met-de-klantendienst-van-colis-prive/) – 荷兰语页面客服邮箱
- [Colis Privé BeLux: Colis Privé en Paack verenigen zich onder één merk](https://colisprive.be/nl/colis-prive-en-paack-verenigen-zich-onder-een-merk-paack/) – 2027 年 1 月起逐步统一为 Paack 品牌
- [Colis Privé: Reprogrammer une livraison](https://colisprive.fr/reprogrammer-une-livraison/) – 12:00 至 21:00 改约时段
- [Colis Privé: Déclaré livré mais pas reçu](https://colisprive.fr/aide/mon-colis-est-declare-livre-alors-que-je-ne-lai-pas-recu/) – 已签收未收到核查与 48 工作小时
- [Colis Privé: Service Client France](https://colisprive.fr/aide/comment-contacter-le-service-client-colis-prive/) – 法国客服电话与时间
- [Colis Privé: Phishing](https://colisprive.fr/aide/comment-eviter-les-attaques-de-phishing/) – 防钓鱼提醒
- [Colis Privé: Accueil](https://colisprive.fr/) – 24/48 小时时效与 5,500+ 自提点
- [Colis Privé BeLux: Trouver un relais](https://colisprive.be/trouver-un-relais/) – 800 家自提点查询
- [Colis Privé BeLux: Qui sommes-nous ?](https://colisprive.be/qui-sommes-nous/) – 公司历史与比卢拓展
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Peek 读取和显示的信息
:::
