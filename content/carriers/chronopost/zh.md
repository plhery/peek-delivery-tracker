---
title: Chronopost 物流查询：单号格式、法语状态与取件点
description: 查询法国 Chronopost 快递包裹：单号格式说明、法语物流状态中文对照、Pickup 自提点与自提柜保管期限，以及派送未成功和客服联系。
slug: chronopost-tracking
published: 2026-11-24
updated: 2026-11-24
---

Chronopost 是法国邮政集团（La Poste Groupe）旗下的特快包裹快递服务。你可以在 Chronopost 官方查询页面查件，或直接将单号粘贴到上方输入框中。

## Chronopost 运单号格式

Chronopost 运单号通常为 13 位字符（2 位字母、9 位数字、2 位字母），或采用 DPD 格式的 15 位字符。如果找不到单号，请向商家询问，或参阅[如何查找运单号](guide:find-tracking-number)（英文）。

| 格式（示例） | 说明 |
| --- | --- |
| `XY482917367FR` | 2 位字母、9 位数字、2 位字母：最常见的 Chronopost 单号。 |
| `AB482917365JB` | 相同格式，以 `JB`、`JF` 或 `TS` 结尾。 |
| `XN482917360584` | `XN` 加 12 位数字：Chronopost 同样支持此类单号。 |
| `48291736058419T` | 15 位字符（14 位数字加 1 位字母或数字校验字符）：DPD 包裹单号格式，Chronopost 同样可查。 |
| `24829173605841` | 不以 `10` 开头的 14 位数字：可能是 Chronopost，但 DPD 等其他快递公司也使用这一格式。 |

13 位格式属于国际邮政单号标准：以 `FR` 结尾的单号也可能是 [La Poste](carrier:la-poste) 的 Colissimo，以 `CH`、`GB` 或 `DE` 结尾的则可能属于对应国家的邮政。15 位格式与 DPD 相同，因此也可能是 DPD 瑞士、DPD 德国、DPD 葡萄牙或 DPD 英国的包裹。如果 Chronopost 查不到记录，请核对商家发货邮件中写明的承运商。

## Chronopost 物流状态中文说明

Chronopost 官方物流状态全部使用法语。按包裹经过的先后顺序如下：

| Chronopost 状态原文 | 对你的含义 |
| --- | --- |
| `Colis en cours de préparation chez l'expéditeur` | 已生成面单；商家尚未把包裹交给 Chronopost。 |
| `Colis déposé par l'expéditeur` | 商家已将包裹交寄给 Chronopost。 |
| `Tri effectué dans l'agence de départ` | 已在始发网点完成分拣。 |
| `Colis en cours d'acheminement` | 正在 Chronopost 网络内转运。 |
| `Tri effectué dans l'agence de distribution` | 已在负责你所在区域的派送网点完成分拣。 |
| `Livraison reportée de 24h` | 网点暂缓一天，当天未安排派送；后续会自动恢复派送。 |
| `Colis en cours de livraison` | 今天正在派送中。 |
| `Echec de livraison suite à l'absence du destinataire.` | 派送未成功：收件人不在家。 |
| `Colis mis à disposition au point de retrait` | 已到达取件点，等待你前去领取。 |
| `Colis en retour expéditeur` | 正在退回寄件商家。 |
| `Livraison effectuée` | 已签收。 |

`Livraison reportée de 24h`（可能因误分拣、地址核对或收件商铺歇业）并不表示包裹丢失。`Destinataire informé par SMS ou mail` 和 `Instruction de livraison reçue` 仅表示系统发出了通知或收到了你的派送指令，包裹本身并未移动。如果 `Livraison effectuée` 出现在 `Colis en retour expéditeur` 之后，表示包裹已经退回到商家手中。

## Chronopost 派送、自提点与派送未成功

如果你不在家，包裹可以转存至取件点：在法国，Chronopost 使用 Pickup 合作自提点与自提柜、邮局以及其自有网点。包裹送达 Pickup 网点后，你会收到含取件截止日期的邮件或短信。Pickup 表示保管期限最长不超过：

- Pickup 合作自提点：8 天；
- Pickup 自提柜：5 天。

超过上述期限后，包裹将自动退回寄件人。

在 Pickup 合作自提点或自提柜取件时，需要出示 Pickup Pass（二维码）或通过邮件、短信收到的取件码；在合作自提点也可直接出示本人有效身份证件原件。自提柜为自助式，部分站点全天 24 小时开放。他人也可凭你的 Pickup Pass 或取件码代领，或在合作自提点同时出示代领人及你的身份证件原件；Pickup 不接受书面委托书或授权书。

## Chronopost 包裹停滞、未收到或丢失

**无更新或延误。** 延误投诉应向发货商家提出，因为运输合同由商家签订：在法国，线上商家须对其选用的承运商造成的交付问题独立承担责任。另见[物流不更新怎么办](guide:tracking-not-updating)（英文）。

**丢失或破损。** 请通过商家申请理赔。如果包裹送达时已破损，可在签收单上注明情况并尽快联系商家客服。在 Pickup 合作自提点取件发现破损时，请当场告知店主做记录；记录后你仍可选择签收或拒收。

**显示已签收但未收到。** 请联系商家：如果商家无法证明你已收到包裹，损失风险由商家承担。另请参阅[显示已签收但未收到](guide:delivered-not-received)（英文）。

## Chronopost 客服联系

Chronopost 官方联系页面位于 chronopost.fr。La Poste 集团及 Pickup 还列出了以下渠道：

- **Léonard**（Chronopost 的聊天机器人），用于解答派送问题；复杂情况会转接人工客服。
- **电话：** 09 69 39 13 91。
- Shop2Shop 包裹请访问 chronoshop2shop.fr。

如果你提交了书面、注明日期并签名的投诉，而 Chronopost 至少两个月未予答复，或你对其回复存有异议，可在投诉后一年内向法国邮政集团调解员（Médiateur du Groupe La Poste）申请调解。

> 诈骗短信和邮件会冒用 Chronopost 的名义。Chronopost 声明，对于法国境内派送的包裹，绝不会要求你提供身份证件或额外支付派送费。可疑邮件请转发至 abuse@chronopost.fr，诈骗短信请举报至 33700。

## 使用 Peek 查询 Chronopost 包裹

对于以 `PZ`、`XR`、`XT`、`XU`、`XW` 或 `XY` 开头且校验位有效并以 `FR` 结尾的 13 位单号，以及以 `JB`、`JF` 或 `TS` 结尾的 13 位单号，[Peek](/) 会直接识别为 Chronopost。对于以 `CH`、`GB` 或部分其他国家代码结尾的有效 13 位单号，Peek 会查询 Chronopost，并在部分国家同时查询当地邮政（例如 `GB` 在 Chronopost 查不到时查询 Royal Mail）。对于前缀相符但校验位无效的单号，以及以 `RV`、`VF` 结尾或以 `XN` 开头的单号，Peek 仅向 Chronopost 查询；以其他前缀开头且 `FR` 结尾的有效单号会交由 La Poste 查询。对于 14 位数字加 1 位校验字母的 15 位单号，Peek 会同时查询 Chronopost 以及共用该格式的 DPD 公司（DPD 瑞士、德国、葡萄牙和英国）；如果两家都查到，你可以通过“选择”确认；如果都查不到，可通过“更换”手动切换。对于不以 `10` 开头的 14 位纯数字，Peek 会询问使用该格式的多家承运商（含 Chronopost 与 DPD）；你也可以点击“更换”，在“符合这个单号”下直接选择 Chronopost。也可以粘贴 Chronopost 和 chronoshop2shop.fr 的查询链接。查询无需邮编。

除 `Livraison effectuée` 显示为“已签收”外，扫描文案均保留法语原文。Peek 会在地图上标出每条扫描对应的网点，并显示目的国、签收时间、德国或葡萄牙的合作伙伴单号，以及包裹待取期间的合作自提点或自提柜名称和地址。只有在预约了改期派送或送达时段，或者 Chronopost 在周五至周日期间显示 `Livraison prévue lundi prochain` 时，才会显示预计送达日。Peek 会省略你的收件地址及取件点联系方式。发往德国或葡萄牙并带有合作单号的包裹可以衔接至当地 DPD 继续追踪。包裹启动退回流程后，退回签收会标记为已退回寄件人。

Peek 最高每 10 分钟检查一次，并可在状态变化时通知你。包裹进入派送中后，只要 Chronopost 官方查询正常响应，Peek 会自法国时间 06:00 起最高每 2 分钟检查一次，但在查询失败后会等待 15 分钟再试；通过物流查询服务时，在派送当天打开页面或开启提醒时最高每 15 分钟检查一次。若 Chronopost 给出了送达时段，该频率会持续至时段开始前 30 分钟；若包裹自前一天起便一直处于派送中，也会维持该频率。当其他承运商接手派送时，Peek 会遵循实际派送承运商的刷新节奏。若连续两天无新动态（包裹仍处于“已生成面单”或“已揽收”状态时为 12 小时），Peek 会转为每小时检查一次。Chronopost 返回的不含时区的扫描记录会按当地网点所在时区解析。

## Chronopost 物流查询常见问题

### Chronopost 属于 La Poste 吗？

是的。Chronopost 隶属于 Geopost，而 Geopost 归属法国邮政集团（La Poste Groupe）。

### Chronopost 和 DPD 是同一家公司吗？

Chronopost 并不以 DPD 的名义介绍自己：它是法国邮政旗下包裹控股公司 Geopost 的成员。它的部分运单号采用 DPD 的 15 位格式。

### Chronopost 在 Pickup 自提点保管包裹多久？

据运营自提点的 Pickup 说明，最长保管 8 天；具体截止日期以邮件或短信通知为准。逾期未取包裹将退回寄件人。

:::sources
- [La Poste Groupe: Chronopost](https://www.lapostegroupe.com/en/focus/la-poste-groupes-subsidiaries/chronopost) – 特快包裹服务及法国取件点（自有网点、邮局、Pickup 网点）
- [La Poste Groupe: 2025 Integrated Report](https://www.lapostegroupe.com/assets/media/le-groupe-laposte/ad4Q_p1ZCF7ETKqb_MEL_LAPO_RI_seul_2025_EN_14042026.pdf) – Chronopost 聊天机器人 Léonard（第 18 页）
- [Pickup: FAQ](https://www.pickup.fr/faq-particulier/) – 到货通知、Chronopost 自提点 8 天与自提柜 5 天保管期、退回寄件人、Pickup Pass 与取件码、代领规则、24/7 自提柜、Chronopost 客服电话与网点破损申报
- [Service-Public: Achat à distance, livraison du bien](https://www.service-public.gouv.fr/particuliers/vosdroits/F10037) – 卖家对交付、破损与丢失承担法律责任
- [Médiateur du groupe La Poste: Conditions de saisine](https://mediateur.groupelaposte.com/pages/conditions-requises-a-la-saisine-du-mediateur-de-la-consommation-du-groupe-la-poste) – Chronopost 争议调解、两个月等待期与一年时限
- [Cybermalveillance.gouv.fr: L'hameçonnage à la livraison de colis](https://www.cybermalveillance.gouv.fr/tous-nos-contenus/actualites/escroqueries-livraison-colis) – 法国境内不要求证件或派送费、abuse@chronopost.fr 与 33700 举报
- [Geopost: leboncoin is teaming up with Chronopost](https://www.geopost.com/en/news/leboncoin-is-teaming-up-with-chronopost/) – Chronopost 隶属 Geopost 与 Shop2Shop 服务
- [Geopost: About us](https://www.geopost.com/en/about-us/) – Geopost 隶属于法国邮政集团（La Poste Groupe）
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Peek 读取的内容
:::
