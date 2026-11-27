---
title: GOFO France 物流查询：GFFR 单号、状态与客服
description: 查询法国 GOFO France 包裹：GFFR 与 CIFR 单号格式、法语状态说明、三次派送与改地址规则，以及法国客服电话和邮箱。
slug: gofo-france-tracking
published: 2026-11-24
updated: 2026-11-24
---

GOFO France 是跨国快递品牌 GOFO 在法国的派送网络，负责法国境内的包裹派送。Peek 的快递公司列表中也将其称为 CIRRO Parcel France。它的运单号以 `GFFR` 或 `CIFR` 开头：你可以在 gofo.com 的法国查询页面查件，或直接粘贴到上方输入框中。还没找到单号？请参阅[如何查找运单号](guide:find-tracking-number)（英文）。

## GOFO 运单号格式

Peek 将以下格式归入 GOFO France（不区分大小写）：

| 格式 | 示例（虚构） |
| --- | --- |
| `GFFR` 加 13 位数字 | `GFFR4829173605841` |
| `GFFR` 加 14 位数字 | `GFFR48291736058418` |
| `CIFR` 加 13 位数字 | `CIFR4829173605841` |
| `CIFR` 加 14 位数字 | `CIFR48291736058419` |

商家自身的参考号也可以代替运单号查询：以 `PK` 开头加 20 位数字，有时中间带连字符，例如 `PK-4829173605841926374-8`（虚构示例）。GOFO France 官方查询同样支持这类参考号，并会在系统中将其与自有的 `GFFR` 或 `CIFR` 运单号关联保存。

GOFO 的其他国家网络也遵循相同命名规则，在 `GF` 或 `CI` 后加上对应国家代码：`GFIT` 或 `CIIT` 属于 GOFO 意大利，`GFNL` 或 `CINL` 属于 GOFO 荷兰，`GFES` 或 `CIES` 属于 GOFO 西班牙；`GFUS` 加 14 位数字、`GF` 加 13 位数字或 `CR` 加 12 位数字属于美国的 [GOFO Express](carrier:gofo)（独立网络）；`GFCA` 加 14 位数字属于 GOFO 加拿大。这些都不是 GOFO France 的单号。更多单号格式区分见[运单号格式说明](guide:tracking-number-formats)（英文）。

## GOFO 物流状态：法语原文含义

Peek 直接读取 GOFO 的法语物流接口，因此无论界面语言是什么，扫描记录都以 GOFO 的法语原文显示。以下按包裹常见流转顺序列出主要状态：

| GOFO 原文 | 对你的含义 |
| --- | --- |
| `Votre colis est en cours de préparation par l'expéditeur` | 商家正在打包准备包裹，GOFO 尚未收到。 |
| `Expédition en transit vers GOFO` | 正在发往 GOFO 途中，GOFO 尚未签收揽件。 |
| `Arrivé au site de prise en charge` | 已抵达 GOFO 揽收站点：这是 GOFO 的第一条实物扫描记录。 |
| `Votre colis a été pris en charge au centre de tri` | 已在 GOFO 分拣中心扫描入库。 |
| `Colis en cours d'acheminement` | 正在 GOFO 各站点之间转运。 |
| `Votre colis est arrivé à l’agence régionale GOFO de destination` | 已到达负责你所在区域的 GOFO 地区派送网点。 |
| `Le chauffeur est en route pour la livraison` | 司机已出发，正在派送途中。 |
| `Échec de la livraison, motif : Le destinataire n'est pas chez lui` | 派送未成功：收件人不在家。 |
| `Livré` | 已签收。 |

前两条状态最容易引起误解：在出现 `Arrivé au site de prise en charge` 或分拣中心扫描之前，GOFO 尚未拿到包裹，Peek 会将其显示为“已生成面单”。签收状态在所有语言下都只显示 `Livré`：GOFO 网站的签收记录有时会包含收件人等更多细节，Peek 会自动省略这些信息。

GOFO 查询系统还会给包裹标注大类状态。`Alerte` 表示 GOFO 标记了异常，Peek 显示为“需要留意”，它本身并不一定意味着已尝试过派送。`Retourné` 表示包裹正在退回寄件人，显示为“已退回寄件人”。

## GOFO 包裹延误、派送未成功、丢失或破损

**时效多久？** GOFO 说明，包裹抵达法国后通常在 3 至 5 个工作日内送达，在其网站出现“En cours de livraison”（GOFO 的表述，并非 Peek 状态名）后通常 1 至 2 个工作日内送达；GOFO 无法提供精确到小时的送达时间。来自欧盟以外的包裹需先清关，在入库 GOFO 仓库前物流可能暂时不动。关于关税、增值税以及自 2026 年 11 月 1 日起按商品类别每类征收的 2 欧元欧盟处理费，请参阅[清关中的包裹指南](guide:customs)（英文）。

**物流不更新。** Peek 会持续向 GOFO 查询。如果包裹在进入派送前连续四天没有新扫描记录，Peek 的包裹页面会给出提示并提供 GOFO France 帮助页链接（“向 GOFO France 寻求帮助”）。GOFO 建议在法国或欧盟境内超过五个工作日无更新时联系其客服。另请参阅[物流为什么停止更新](guide:tracking-not-updating)（英文）。

**派送未成功。** 当出现 `Échec de la livraison, motif : Le destinataire n'est pas chez lui` 时，Peek 显示“派送未成功”；若 `motif :` 后跟其他失败原因，则显示“需要留意”。GOFO 最多尝试三次派送；若仍未送达，包裹会在仓库保管 14 天，请在此期间联系 GOFO 安排再次派送，否则包裹会退回寄件人。GOFO 不提供取件点自提、不支持更改派送日期，也不接受主动申请退回：若不再需要商品，请通过商家办理退货。你不在家时，司机也可能将包裹投入信箱或交由邻居代收；如不希望如此，请提前通知 GOFO。如需修改地址，请在包裹交给司机前使用 `cs@fr.gofo.com` 发来的表单链接；若未收到或链接已过期，请尽快联系 GOFO。另请参阅[派送未成功指南](guide:missed-delivery)（英文）。

**延误。** 根据欧盟消费者权益规定，除非双方另行约定时间，商家必须在 30 天内送达。若超期未达，你可给予商家合理的额外宽限期；宽限期满仍未送达，你有权取消订单并获退款。

**显示 `Livré` 但未收到。** 请先在 GOFO 官方查询页面查看签收凭证：上面会写明放置位置，有时还附有照片（Peek 不显示照片）。检查信箱，询问邻居或楼栋管理员，随后尽快向 GOFO 报备；提供房屋外观及入口照片有助于加快核查，但并非强制要求。按规定商家对交付结果负责：应由商家联系快递核实并举证包裹已送达，若无法证明，商家须补发或退款。详情请参阅[显示已签收但未收到](guide:delivered-not-received)（英文）。

**破损。** 从发货到你实际收到包裹期间发生的一切运输破损均由商家负责。条件允许时，请在司机面前当场查验包裹。另见[包裹破损指南](guide:damaged-parcel)（英文）。

## GOFO France 客服联系

GOFO France 帮助页面位于其法国网站 gofo.com；当包裹在派送前连续四天无新扫描时，Peek 会通过“向 GOFO France 寻求帮助”直接链接到该页面。其向收件人提供的联系渠道如下：

- **电话：** +33 9 70 70 90 65，周一至周五 9:00 至 18:00（法定节假日除外）；
- **邮箱：** cs@fr.gofo.com。

有关派送本身的问题（签收凭证、安排重新派送、修改地址）可直接联系 GOFO。若订单出现延误、丢失或破损，请先联系发货商家，因为商家对订单交付承担责任。沟通时请备好 `GFFR` 或 `CIFR` 运单号以及订单号。

## 使用 Peek 查询 GOFO France 包裹

[Peek](/) 会自动识别 `GFFR` 和 `CIFR` 运单号。你也可以直接粘贴 GOFO 法国查询页的链接（gofo.com 或 gofoexpress.fr 上带有 `id=` 和单号的链接）。`PK` 参考号同样有效：Peek 会向 GOFO France 核验，一旦 GOFO 查到该记录即会标明承运商。

只要 GOFO 官方查询正常响应，Peek 最高每 10 分钟读取一次 GOFO 法国物流，在 GOFO 扫描包裹进入派送中的当天早上 6 点（当地时间）起最高每 2 分钟检查一次（通过物流查询服务时，在派送当天打开页面或开启提醒时最高每 15 分钟检查一次）。若连续两天没有新动态（包裹仍处于“已生成面单”或“已揽收”状态时为 12 小时），Peek 会转为每小时检查一次。页面展示当前状态、带 GOFO 所标地点的完整扫描历史、签收时间并在地图上标出各地点，还可按每次扫描、重要环节或仅派送当天向你推送通知。如果 GOFO 某条记录只给出站点代码或国家名称，该条扫描不会显示地点。GOFO 提供每条扫描的当地时间，Peek 会自动转换为你的本地时区；在 peektracker.com 以及 iPhone 和 Android 应用中，物流历史最后一行会注明当前时区（“时间按你所在的时区显示：…”）。在 GOFO 返回第一条扫描记录之前，Peek 显示“等待 GOFO France 更新”并持续查询，而不会立刻判定查不到该单号。若两天后 GOFO 依然查不到该单号且其他渠道也无记录，Peek 会显示“两天过去了，快递公司仍查不到这个单号。请检查单号、更换快递公司，或删除这个包裹。”并改为每天检查一次，满一周后改为每周检查一次。如果 GOFO 系统中已录入该包裹但尚未扫描，则说明它认识这个单号：Peek 会保持“等待 GOFO France 更新”并继续查询，不会提示核对单号。在包裹进入派送中、已签收、退回或到达取件点之前，Peek 会显示 GOFO 的预计送达日期；页面不显示重量，也不展示签收照片和 GOFO 公告。Peek 无法代你联系 GOFO 或商家。

## GOFO 物流查询常见问题

### “Expédition en transit vers GOFO” 是什么意思？

表示包裹正在发往 GOFO 的途中，GOFO 尚未收到。接下来会出现 GOFO 的第一条实物扫描：`Arrivé au site de prise en charge` 或 `Votre colis a été pris en charge au centre de tri`。在此之前，Peek 将包裹显示为“已生成面单”。

### GOFO 正规吗？

正规。GOFO FRANCE 是一家在法国国家统计局（INSEE）Sirene 名录中登记的法国简易股份公司（SAS），Siren 编号 978 559 839，自 2023 年 5 月起运营，总部位于瓦勒德瓦兹省 Marly-la-Ville。

### GOFO 和 CIRRO Parcel 是同一家公司吗？

在法国追踪包裹时是的：Peek 的快递公司列表中将 GOFO France 的别名列为 CIRRO Parcel France。如果其他查询工具只写“Cirro”，它可能指 GOFO 的法国、意大利或荷兰网络，也可能指 Cirro 自己的跨境专线（该专线会将包裹交由各国邮政以其单号派送）。

### 如果我的 GOFO 包裹丢了该怎么办？

请联系商家。根据欧盟规则，在包裹送达你手中之前均由商家承担交付责任；如果商家无法证明已送达，必须为你补发商品或退款。更多建议见[包裹丢失指南](guide:lost-parcel)（英文）。

:::sources
- [GOFO France: Service client](https://www.gofo.com/fr/contact-us/) – 电话、邮箱、工作时间及法语常见问题：时效、“En cours de livraison”、无精确送达时间、清关、物流停滞、派送次数、仓库保管、信箱或邻居代收、无取件点/改期/退货、改地址表单与过期链接、签收凭证及核查照片
- [European Commission: EU Customs Reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 自 2026 年 11 月 1 日起实施的 2 欧元欧盟处理费
- [INSEE: Avis de situation au répertoire Sirene](https://avis-situation-sirene.insee.fr/) – GOFO FRANCE，Siren 978 559 839，SAS，2023 年 5 月 26 日起运营，总部位于 Marly-la-Ville
- [Your Europe: Shipping and delivery](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_en.htm) – 30 天内送达、额外宽限期及收货前破损责任
- [Your Europe: FAQs, shipping and delivery](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/faq/index_en.htm) – 由商家举证送达及签收时查验包裹
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – 单号格式、PK 参考号、链接、法语扫描文案与分类、CIRRO Parcel France、Peek 显示与省略的内容
:::
