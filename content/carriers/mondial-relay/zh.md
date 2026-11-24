---
title: Mondial Relay 物流查询：单号、法语状态与取件点
description: 查询 Mondial Relay 包裹：8 位单号与收件邮编查找、法语物流状态中文说明、Point Relais 与自提柜保管期限以及客服联系方式。
slug: mondial-relay-tracking
published: 2026-11-24
updated: 2026-11-24
---

Mondial Relay 是隶属于 InPost 集团的法国包裹快递公司，负责将包裹派送至 Point Relais（作为取件点的本地合作商户）、智能自提柜以及收件人住址。你可以在 [Mondial Relay 比利时查询页面](https://www.mondialrelay.be/fr-be/suivi-de-colis/)输入 8 位发货编号和收件邮编查询，或直接将单号粘贴到上方输入框中。包裹发出后你会通过邮件或短信收到单号，寄件人同样掌握该单号。

## Mondial Relay 运单号：在哪里查找

Mondial Relay 将单号称为“numéro d'expédition”：8 位数字，位于物流邮件或短信中。没有收到单号？请询问寄件人（寄件人也可以查看包裹进度），或参阅[如何查找运单号](guide:find-tracking-number)（英文）。其比利时站和荷兰站同时也支持输入 10 位和 12 位数字查询。

| 单号（示例） | 说明 |
| --- | --- |
| `48291736` | 8 位发货编号。Colisweb 和 Heppner 也使用相同位数。 |
| `0048291736` | 10 位数字：品牌代码加发货编号。DHL、Relais Colis 等快递也使用该长度。 |
| `004829173602` | 12 位数字：上述号码加包裹序号。DPD France、Colis Privé、BRT 等也使用 12 位数字。 |
| `00482917360101` | 14 位数字：条形码前段，包含品牌代码、发货编号、包裹序号和总件数。DPD、Chronopost 等也使用该长度。 |
| `00482917360101748291736058` | 26 位数字：面单完整条码，为 Mondial Relay 独有格式，第 3 至第 10 位为发货编号。 |

如果商家邮件中写明了其他快递公司，[该单号可能属于那家承运商](guide:tracking-number-formats)（英文）。

## Mondial Relay 物流状态中文说明

Mondial Relay 官方查询页面仅提供法语（比利时站提供法语或荷兰语），没有英文版。以下按常见顺序列出主要状态：

| Mondial Relay 原文 | 对你的含义 |
| --- | --- |
| `Colis en préparation chez l'expéditeur` | 已生成面单；Mondial Relay 尚未收到包裹。 |
| `Prise en charge` | 寄件人已将包裹投递到 Point Relais 或自提柜。 |
| `Prise en charge de votre colis sur notre site logistique`、`Colis expédié depuis le site` | 包裹已到达所标物流站点，随后从该站点发出。 |
| `Colis en route vers le point de livraison` | 正在发往你的 Point Relais、自提柜或收件地址途中。 |
| `Sollicitation Client pour replace colis` | 你选定的取件点无法接收包裹，系统请你改选另一个取件点。 |
| `Relais de substitution choisi` | 已选定替代取件点来存放包裹：请留意新地址。 |
| `En cours de livraison` | 正在派送前往你的住址。 |
| `Disponible dans votre Point Relais`、`Disponible dans votre locker` | 包裹已到店或入柜，待取件。 |
| `Retiré par le destinataire`、`Colis livré au destinataire` | 你已取件，或已送达你的住址。 |
| `Retour à l'expéditeur` | 包裹正在退回寄件人。 |

如果是寄往取件点，请等到出现 `Disponible…` 再前往领取。`Échec de livraison` 表示上门派送失败，`Votre colis a été livré à l'enseigne.` 表示包裹已送达合作商户。如果物流历史中先前已出现退回记录（`Retour à l'expéditeur`），Peek 会将其显示为“已退回寄件人”；如果是你自己使用独立单号寄回商家的退货包裹，则会显示为“已签收”。

## Mondial Relay 合作取件点、自提柜与派送未成功

**取件方式。** 在比利时，前往 Point Relais 取件需出示有效身份证件，或提供 Mondial Relay 通过邮件或短信发送的 PIN 码；在自提柜取件则需输入邮件、短信或应用中的 6 位取件码。他人也可代领：凭你的取件码，或在 Point Relais 同时出示代领人本人和你的有效身份证件原件。

**保管期限。** 在比利时为 5 个自然日（含周日和法定节假日），不可延期：周三送达的包裹保管至周一，逾期退回寄件人。荷兰的 Point Relais 同样保管 5 天。InPost 页面说明西班牙和葡萄牙的境内包裹保管 5 天，不过葡萄牙有一处页面也写了 8 天。法国的具体保管期限请查看 Mondial Relay 法国官网。

**更改取件点。** 在比利时，包裹交由 Mondial Relay 揽收后无法更改取件点。如果原取件点歇业或满仓无法接收，你会收到邮件或短信让你等待或另选网点；若未回复，Mondial Relay 可能会为你指定附近的替代网点。

**上门派送未成功。** 根据 Mondial Relay 荷兰站面向个人寄件人的条款，派送员会将包裹投入信箱、当面交给你或地址内的任何人，或按你的派送说明处理。如果首次上门失败，包裹会转存至 Point Relais 或自提柜并通过邮件通知你。拒收的包裹会退回寄件人。

## Mondial Relay 包裹丢失、停滞或破损

在比利时，Mondial Relay 仅向寄件人（商家或个人卖家）理赔，因此包裹丢失和破损均需通过寄件人处理。

- **物流停滞：** 超过比利时与荷兰通常的 3 至 6 个工作日仍未到达 Point Relais？在比利时可发送邮件至 customerservice@mondialrelay.be 或拨打 +32 2 340 06 10（提供单号）。另见[物流为什么停止更新](guide:tracking-not-updating)（英文）。
- **丢失：** 请联系寄件人；Mondial Relay 会在收到报失后 10 天内联系寄件人，并按所投保额度赔付。如果是通过 Vinted 或其他合作平台寄出的包裹，待调查确认丢失后，由对应平台按平台规则赔付。
- **破损：** 请在 Point Relais 当场拒收，因为一旦签收取走，Mondial Relay 不再受理破损索赔。

## Mondial Relay 客服与联系电话

各国客服联系渠道如下：

- **法国：** 请查看 Mondial Relay 法国官网的联系页面。
- **比利时：** 拨打 +32 2 340 06 10（按市话标准计费），周一至周五 8:00–19:00、周六 8:00–18:00，或发邮件至 customerservice@mondialrelay.be。
- **西班牙和葡萄牙：** 现已并入 InPost，西班牙免费电话 900 900 137，葡萄牙电话 211 245 624。

> Mondial Relay 比利时官网声明，公司绝不会要求你拨打高额付费电话、支付额外费用或进行银行转账。官方邮件仅来自 noreply@mondialrelay.fr 或 noreply@mondialrelay.com。可疑邮件可举报至 abuse@mondialrelay.fr。

## 使用 Peek 查询 Mondial Relay 包裹

[Peek](/) 会直接识别 26 位完整面单条码。若是 8 位、10 位、12 位或 14 位数字，由于多家快递共用这些长度，Peek 可能不会自动给出 Mondial Relay；Peek 只会对 InPost 西班牙的 `FV` 单号（`FV` 加 8 位数字）主动向 Mondial Relay 核验。如果没有其他快递公司或物流查询服务认领一个 8 位单号，且你位于法国、比利时、卢森堡、西班牙、葡萄牙或荷兰，Peek 会直接推荐 Mondial Relay 并弹出收件邮编输入框。其他情况下，可点击“更换”手动选择 Mondial Relay，或直接粘贴 mondialrelay.fr、.be、.nl 或 .com 的查询链接（西班牙和葡萄牙站点的链接暂不支持解析）。

使用 8 位单号查询时还需要填写“收件邮编”：先只输入单号，再在对应栏位填写邮编。Peek 会按你输入的原文与收件人邮编比对，支持各国邮编格式，但葡萄牙邮编必须填满全部七位数字（例如 `4820-195`，连字符可省略）。邮编错误会显示为查无此件。邮编会发送给 Mondial Relay，必要时也会发送给物流查询服务。

Peek 会按原文显示 Mondial Relay 的法语状态，并在地图上标出每条扫描对应的物流站点，同时在官方提供时显示预计送达日、签收时间和目的国。包裹待取期间会显示 Point Relais 或自提柜的名称和地址，但绝不会显示你的取件码或姓名。只要 Mondial Relay 官方查询正常响应，Peek 最高每 10 分钟检查一次，在 Mondial Relay 扫描包裹进入派送中的当天自巴黎时间早上 6 点起每隔几分钟检查一次（通过物流查询服务时，在派送当天打开页面或开启提醒时最高每 15 分钟检查一次），并在每次状态变化时发送通知。若连续两天无新动态（包裹仍处于“已生成面单”或“已揽收”状态时为 12 小时），Peek 会转为每小时检查一次。Peek 不会把 Mondial Relay 包裹转交其他快递继续追踪；它可以在 inpost.es 上读取 InPost 西班牙的 `FV` 单号，但不保证能查到 InPost 西班牙或葡萄牙的其他单号。

## Mondial Relay 物流查询常见问题

### 我的 Mondial Relay 包裹现在在哪里？

最新一条扫描记录会告诉你位置：出现物流站点名称表示正在转运中，出现 `Disponible dans votre Point Relais` 表示已经到达取件点等你领取。如果超过比利时和荷兰常见的 3 至 6 个工作日仍未到达取件点，请联系寄件人或 Mondial Relay 客服。

### Mondial Relay 派送需要多久？

在比利时，Mondial Relay 的目标是从寄件人投递到送达 Point Relais 平均用时 3 个工作日，当地 Point Relais 周二至周六接收包裹派送。

### Mondial Relay 和 InPost 是同一家公司吗？

它属于 InPost 集团。2021 年 InPost 在西班牙和葡萄牙接管了 Mondial Relay，如今 mondialrelay.es 和 mondialrelay.pt 均跳转至 InPost 网站。在荷兰，Mondial Relay 正陆续切换为 InPost 品牌；切换完成前仍继续通过 Mondial Relay 运营。另见 [InPost 物流查询](carrier:inpost)。

### Mondial Relay 正规吗？

正规：Mondial Relay SASU 是一家总部位于法国 Villeneuve-d'Ascq 的正规公司。不过诈骗信息常冒用其名义，请对照上文防骗提醒仔细甄别。

:::sources
- [Mondial Relay Belgique: Suivi](https://www.mondialrelay.be/fr-be/suivi-de-colis/) – 8/10/12 位单号、收件邮编、邮件或短信与询问寄件人
- [Mondial Relay Belgique: Suivre l'acheminement](https://www.mondialrelay.be/fr-be/faq/recevoir-un-colis/comment-suivre-l-acheminement-de-mon-colis/) – numéro d'expédition 与寄件人查询
- [Mondial Relay: Mijn Mondial Relay pakket volgen](https://www.mondialrelay.nl/mijn-mondial-relay-pakket-volgen/) – 荷兰 8、10、12 位单号
- [Mondial Relay Belgique: Retirer mon colis](https://www.mondialrelay.be/fr-be/faq/recevoir-un-colis/comment-retirer-mon-colis/) – 身份证件或 PIN 码及他人凭 PIN 代领
- [Mondial Relay Belgique: Retrait en Locker](https://www.mondialrelay.be/fr-be/faq/recevoir-un-colis/comment-retirer-un-colis-dans-un-lockers-mondial-relay/) – 6 位自提柜取件码
- [Mondial Relay Belgique: Lockers](https://www.mondialrelay.be/fr-be/consignes-lockers/) – 分享取件码代领
- [Mondial Relay Belgique: Une autre personne](https://www.mondialrelay.be/fr-be/faq/recevoir-un-colis/une-autre-personne-peut-elle-venir-chercher-mon-colis/) – 代领需出示双方身份证件原件
- [Mondial Relay Belgique: Délai de garde](https://www.mondialrelay.be/fr-be/faq/recevoir-un-colis/quel-est-le-d%C3%A9lai-de-garde-dun-colis-en-locker-ou-point-relais/) – 比利时 5 天保管期且不可延期
- [Mondial Relay: Hoe lang wordt mijn pakket bewaard?](https://www.mondialrelay.nl/faq/een-pakket-ontvangen/hoe-lang-wordt-mijn-pakje-bewaard-door-de-afhaalpunt/) – 荷兰 5 天保管期
- [Mondial Relay Belgique: Modifier le Point Relais](https://www.mondialrelay.be/fr-be/faq/recevoir-un-colis/je-souhaite-modifier-le-point-relais-de-livraison-est-ce-possible/) – 不可更改取件点
- [Mondial Relay Belgique: Point Relais fermé](https://www.mondialrelay.be/fr-be/faq/recevoir-un-colis/mon-point-relais-est-ferme-ou-mon-colis-va-t-il-etre-livre/) – 原取件点关闭时改选替代网点
- [Mondial Relay: Algemene verkoopvoorwaarden](https://www.mondialrelay.nl/algemene-verkoopvoorwaarden/) – 荷兰站上门派送与拒收条款
- [Mondial Relay Belgique: Délai moyen](https://www.mondialrelay.be/fr-be/faq/envoyer-un-colis/quel-est-le-delai-moyen-de-livraison/) – 平均派送时效
- [Mondial Relay Belgique: Jours de livraison](https://www.mondialrelay.be/fr-be/faq/recevoir-un-colis/quels-sont-les-jours-ou-le-livreur-passe-au-point-relais-pour-y-deposer-les-colis/) – Point Relais 派送日
- [Mondial Relay Belgique: Retard](https://www.mondialrelay.be/fr-be/faq/recevoir-un-colis/que-faire-en-cas-de-retard-de-livraison/) – 3 至 6 个工作日、邮箱与电话
- [Mondial Relay Belgique: Colis pas au Point Relais](https://www.mondialrelay.be/fr-be/faq/recevoir-un-colis/mon-colis-n-est-pas-au-point-relais-que-dois-je-faire/) – 联系寄件人处理
- [Mondial Relay Belgique: Colis perdu](https://www.mondialrelay.be/fr-be/faq/recevoir-un-colis/que-faire-si-mon-colis-est-perdu/) – 联系寄件人、10 天答复与保险赔付
- [Mondial Relay Belgique: Site partenaire](https://www.mondialrelay.be/fr-be/faq/envoyer-un-colis/un-colis-envoye-via-un-site-partenaire-vinted-est-d%C3%A9clare-perdu-que-faire/) – Vinted 等合作平台赔付流程
- [Mondial Relay Belgique: Colis abîmé](https://www.mondialrelay.be/fr-be/faq/recevoir-un-colis/mon-colis-est-abime-que-faire/) – 破损须当场拒收
- [Mondial Relay Belgique: Contact](https://www.mondialrelay.be/fr-be/nous-contacter/) – 电话、资费与服务时间
- [Mondial Relay Belgique: Information fraude](https://www.mondialrelay.be/fr-be/informations-fraude/) – 防诈骗声明与官方发件邮箱
- [Mondial Relay: Fraude-informatie](https://www.mondialrelay.nl/fraude-informatie/) – 诈骗举报邮箱
- [Mondial Relay Belgique: Mentions légales](https://www.mondialrelay.be/fr-be/mentions-legales/) – 公司主体与总部地点
- [InPost: Mondial Relay Nederland maakt plaats voor InPost](https://inpost.nl/) – 荷兰市场向 InPost 品牌过渡
- [InPost: Quiénes somos](https://www.inpost.es/quienes-somos) – 西班牙业务整合
- [InPost: Quem somos](https://www.inpost.pt/quem-somos) – 葡萄牙业务整合
- [InPost: Empresa de Paquetería Europea](https://www.mondialrelay.es/) – 西班牙网站跳转
- [InPost: Empresa de Encomendas](https://www.mondialrelay.pt/) – 葡萄牙网站跳转
- [InPost: Contacto](https://www.inpost.es/contacta-con-nosotros) – 西班牙热线与 5 天保管期
- [InPost: Fala connosco](https://www.inpost.pt/fala-connosco) – 葡萄牙热线与 5 天保管期
- [InPost: Como recolher](https://www.inpost.pt/recebe-a-tua-encomenda/como-recolher) – 5 天或 8 天保管期说明
:::
