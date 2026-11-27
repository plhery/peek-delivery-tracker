---
title: Colisweb 物流查询：法语状态含义与预约时段说明
description: 查询法国 Colisweb 家具与家电配送：了解 8 位追踪单号位置、常见法语消息含义、如何修改预约时间段以及在线客服联系渠道。
slug: colisweb-tracking
published: 2026-11-27
updated: 2026-11-27
---

Colisweb 在法国本土全境提供按预约时间段送货上门的服务，负责配送法国零售商售出的家具、家电及其他订单。你可以在 [colisweb.com/suivi-livraison](https://www.colisweb.com/suivi-livraison)（仅法语页面）输入 Colisweb 在通知派送日期和时段时通过短信或邮件发给你的追踪单号进行查询。

## Colisweb 追踪单号在哪里找

Colisweb 表示单号位于它发给你的短信和邮件中，通常随派送日期与时间段通知一同发送。如果尚未收到，可能是商家还未把订单交给 Colisweb；商家自己的订单号与追踪单号不同（请参阅 [如何查找追踪单号](guide:find-tracking-number)（英文））。

追踪单号由纯数字组成，至少 8 位。Colisweb 追踪框也接受 “numéro de colis”（包裹号），官网未对其作进一步说明。

| 格式 | 虚构示例 |
| --- | --- |
| 8 位数字 | `48291736` |
| 更长，最多 32 位数字 | `4829173654` |

8 位纯数字也符合 [Mondial Relay](carrier:mondial-relay) 运单号与 Heppner 单号（两者都需要填写邮编）；更长的纯数字则与许多承运商重合。请核对短信或邮件里写的承运商名称：家具和大家电也可能由 [C Chez Vous](carrier:c-chez-vous) 承运（按订单号追踪）。此外，Colisweb 并非 [Colis Privé](carrier:colis-prive)，两者是名称相近的不同公司。

## Colisweb 法语物流状态含义解析

Colisweb 追踪页会用一句法语显示当前步骤，在预约有效期间下方还会列出你的派送日期和时段。按配送通常经历的顺序如下：

| Colisweb 原文 | 对你的含义 |
| --- | --- |
| `Votre commande est confirmée. Elle nous a été confiée et partira bientôt en livraison.` | 商家已将订单交给 Colisweb，你的派送时段已预约，目前尚未启程。 |
| `Votre commande est confirmée.` | 配送已确认、时段已预约，并保留为历史记录的第一行。 |
| `Votre livraison est prévue le :` | 后接你的派送日期与时间段。 |
| `Votre commande est en route !` | 配送团队已揽收订单，正在送货途中。 |
| `Votre commande a été livrée !` | 已送达。 |
| `Votre commande va devoir être décalée.` | 派送需改期至另一天，Colisweb 会联系你重新预约。 |
| `Vous êtes invité(e) à reprogrammer votre livraison.` | 请你自行选择新的派送时间段。 |
| `Une nouvelle livraison est prête.` | 已为你安排好新的派送。 |
| `Votre commande n'a pas pu être livrée par nos ColisDrivers` | 配送团队未能完成送达，或订单正在退回。 |
| `Votre commande n'a pas pu être retirée par nos ColisDrivers.` | 配送团队未能从门店或仓库取到货。 |
| `Votre commande a été annulée.` | 配送已取消。 |

容易误解的几条提示：

- 这里的 `retirée` 指配送团队从门店取货，而不是让你去自提。Colisweb 把自己的司机称为 “ColisDrivers”。
- `Votre commande n'a pas pu être livrée par nos ColisDrivers` 会附带第二行说明来区分两种情况：“Nous vous recontacterons prochainement pour une reprogrammation” 表示 Colisweb 会回电重新预约；“Nous vous invitons à contacter” 后接商家名称则表示订单正在退回，请直接联系商家。
- 出现 `Votre commande est en route !` 时，页面会补充说明送达时须本人签收，因此届时须有人在家。
- `Vous êtes invité(e) à reprogrammer votre livraison.` 和 `Une nouvelle livraison est prête.` 都会要求输入收货邮编：前者打开自选新时段页面，后者跳转至新配送的追踪页面。

更多通用状态说明见 [物流状态含义指南](guide:tracking-statuses)（英文）。

## Colisweb 预约时段：预订、修改与错过派送

Colisweb 按预约制派送。仓库发货订单提供 2 小时时间段，你可通过短信在多个日期和时段中选择，并在最终截止时间前不限次数修改。从附近门店发货的订单可在 2 小时内送达或按预约送达。

如需修改地址或时段，请在 Colisweb 追踪页输入收货地址邮编，进入后续页面即可操作。Colisweb 说明仅在某一截止时间前允许修改，但未公布具体时限，因此请尽早更改。

Colisweb 不设自提点或快递柜，而是直接送货上门并凭签字交接。官网未说明派送尝试次数。若派送失败或需要推迟，追踪页会提示由 Colisweb 联系你改期，或邀请你输入邮编自助改约。

送货员到场后具体包含哪些服务（搬运楼层、拆箱、安装、回收旧家电）取决于商家向你售出的服务内容，请核对商家订单。

## Colisweb 配送延误、取消或破损

你的购销合同是与商家签订的。当订单取消、退回或未能从仓库取货时，Colisweb 追踪页都会直接点名让你联系商家，并未公布面向收件人的索赔流程或时限。

根据法国法律（如 Service Public 所述），在卖家指定承运商的情况下，卖家承担你实际收货前的遗失或破损风险，并对订单的正常履约承担全部责任：

- **到货破损：** 你可以当场拒收并要求卖家提供完好商品；如选择签收，请在送货单或发票上写明破损备注，并尽快联系卖家客服。
- **配送延误：** 你可以要求卖家在合理的额外期限内交货，若仍未送达则可取消订单，卖家须在最多 14 天内退款。

如果追踪页仍显示 `Votre commande est confirmée.`，请查看下方的派送日期：只有当配送团队完成取货后，才会出现下一步 `Votre commande est en route !`。另见 [物流为何停止更新](guide:tracking-not-updating)（英文）。

## 如何联系 Colisweb

Colisweb 不向收件人提供联系电话或电子邮箱。其追踪页常见问题给出的联系途径如下：

- **在线聊天：** 在 [colisweb.com/suivi-livraison](https://www.colisweb.com/suivi-livraison) 输入你的收货邮编，后续页面提供在线聊天，服务时间为巴黎时间周一至周六 8:00–20:00。
- **联系表单：** 常见问题也提到 colisweb.com/contact，但该表单面向企业客户（要求填写公司邮箱），因此请优先使用在线聊天。
- **联系商家：** 涉及取消订单、退货、破损或退款，请联系购买商品的零售商。

其隐私政策页面列出的邮箱仅受理个人数据请求。

## 使用 Peek 追踪 Colisweb 配送

8 位数字同时符合 Colisweb、Mondial Relay 和 Heppner（部分也符合北美的 TForce Freight），且这些承运商均无法在不指定的情况下自动探明归属，因此 [Peek](/) 不会自动选定承运商：请点击“更改”并选择 Colisweb；更长的数字同样如此。你也可以直接粘贴带有单号的 colisweb.com 追踪链接，Peek 会无论长度直接识别为 Colisweb。Peek 追踪 Colisweb 时无需输入邮编。在法国及其他 Mondial Relay 覆盖的国家，若 8 位数字没有承运商自动返回结果，Peek 可能会提示 Mondial Relay 可能持有此包裹并建议添加邮编：如果短信或邮件来自 Colisweb，请不要填写邮编，直接改选 Colisweb 即可。

由于 Colisweb 接口仅返回步骤代码，Peek 会以你的界面语言展示每一步，显示当前状态、三个关键节点（配送已确认、团队已取货、已送达）及对应时间（不含地点）、零售商名称和签收时间。在已预约、已取货或派送中且未处于改期状态时，Peek 还会按巴黎时间展示预约日期与时段。已取消的配送会显示为“已退回寄件人”。Peek 不显示你的姓名和地址。

当你打开订单，或在开启通知的情况下进入派送日时，Peek 最高每 10 分钟检查一次。一旦 Peek 在派送日显示“派送中”，将从巴黎时间早上 6 点或预约时段开始前半小时（以较晚者为准）起提高至每 2 分钟检查一次。通知支持“每次扫描”、“重要节点”或“仅派送和送达”。连续两天无新动态时（处于“已创建运单”或“承运商已揽收”时为 12 小时）改为每小时检查一次，在“已创建运单”下满七天无新动态则改为每 6 小时检查一次（预约派送日当天除外）。若在“已创建运单”或“运输中”连续四天无新记录，即使预约日期尚未到来，Peek 也会显示“自……起无物流更新”：预约在较后日期的订单在配送员取货前通常会一直停留在“已创建运单”。对于 Colisweb 未收录的单号，系统不会主动报“未找到”，因此请对照短信仔细核对数字。Peek 无法代替你修改时段或地址、使用 Colisweb 在线聊天或联系商家。

## Colisweb 追踪常见问题

### Colisweb 有客服电话吗？

没有。Colisweb 不为收件人公布电话号码；请在官网追踪页输入邮编后使用在线聊天，或联系发货商家。

### 哪些商家使用 Colisweb 配送？

Colisweb 首页将 IKEA、Leroy Merlin、Castorama、Darty、Boulanger、Decathlon 和 Auchan 等零售商列为合作品牌。不过商家标志并不代表所有订单都走 Colisweb：如果由它承运，你会收到 Colisweb 通知日期和时段的短信或邮件。

### 为什么 Colisweb 要我输入邮编？

Colisweb 追踪页仅凭单号就能显示基本状态，但需要验证收货地址邮编后，才会显示详细信息、司机进度、在线聊天以及修改时段入口。

### Colisweb 隶属哪家集团？

Colisweb 隶属于 ID Logistics 集团（于 2022 年 1 月加入），创立于法国里尔（Lille）。

:::sources
- [Colisweb: Suivi de votre livraison](https://www.colisweb.com/suivi-livraison) – 单号、状态、邮编验证、时段修改、签收要求与在线聊天时间
- [Colisweb: La livraison sur mesure](https://www.colisweb.com/) – 预约派送、法国本土、合作零售商、ID Logistics
- [Colisweb: Ship from warehouse](https://www.colisweb.com/ship-from-warehouse) – 短信预约、2 小时时段、截止时间
- [Colisweb: Ship from store](https://www.colisweb.com/ship-from-store) – 2 小时内或预约配送
- [Colisweb: Qui sommes-nous ?](https://www.colisweb.com/a-propos) – 里尔创立、2022 年起隶属 ID Logistics
- [Colisweb: Contact](https://www.colisweb.com/contact) – 商业客户联系表单
- [Colisweb: Typologie de colis](https://www.colisweb.com/typologie-colis) – 大件与重物
- [Colisweb: Politique de confidentialité](https://www.colisweb.com/privacy) – 个人数据请求邮箱
- [Service Public: Livraison du bien](https://www.service-public.gouv.fr/particuliers/vosdroits/F10037) – 卖家风险责任、破损与延迟交付
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Peek 展示的信息
:::
