---
title: GLS Switzerland 快递追踪：状态说明、瑞士邮政派送与联系
description: 追踪发往瑞士的 GLS 包裹：单号格式、为何需要输入收件邮编、英文追踪状态含义、瑞士邮政（Swiss Post）清关派送及客服联系方式。
slug: gls-switzerland-tracking
published: 2026-11-26
updated: 2026-11-26
---

GLS 是总部位于阿姆斯特丹的国际包裹网络，但在瑞士没有自营子公司：其合作伙伴瑞士邮政（Swiss Post）负责将 GLS 运入瑞士的包裹完成清关并派送上门。你可以在 [GLS 追踪页面](https://gls-group.com/EU/en/parcel-tracking) 输入包裹单号和收件邮编查询进度，或将单号粘贴到上方输入框中。单号由发件人提供，建议先查看商家的发货通知邮件。

## GLS Switzerland 包裹单号与 Track ID

GLS 追踪支持输入包裹单号（parcel number）或 Track ID。GLS 会将 Track ID 印在投递通知卡上，但在瑞士由瑞士邮政负责派送，且没有任何页面显示瑞士境内会使用 GLS 通知卡。GLS 与瑞士邮政均未公开发布单号规则。Peek 识别为 GLS 的单号格式如下：

| 单号（虚构） | 说明 |
| --- | --- |
| `48291736542` | 11 位数字。同样适用于 [GLS Germany](carrier:gls-de)、[GLS France](carrier:gls-fr)、PostLogistics 和 [DHL eCommerce Poland](carrier:dhl-ecommerce-pl)。 |
| `482917365424` | 12 位数字，最后一位为校验位。同样适用于 BRT、Mondial Relay、Colis Privé 等承运商。 |
| `K7M4P9X2` | 8 位字母与数字组合。同样适用于 GLS Germany 和 GLS France。 |
| 6 位纯字母 | 例如 `HMPQRT`。同样适用于 GLS Germany。 |

请直接复制单号而不是手动输入：12 位单号若最后一位校验码有误，或 11 位单号漏掉一位数字，都将不再匹配 GLS。如果商家发货邮件中注明了其他承运商，该单号即属于对应承运商。完全没有找到单号？请参阅[如何找到你的追踪单号](guide:find-tracking-number)（英文）。

## 瑞士 GLS 追踪：各英文状态含义

发往瑞士的 GLS 包裹追踪信息仅提供英文，且 GLS 未发布专门的瑞士状态列表。以下为其集团追踪系统使用的英文表述，按先后顺序排列：

| GLS 显示状态 | 对你的含义 |
| --- | --- |
| `Preadvice`、`The parcel has not been handed over to GLS.` | 发件人已录入包裹电子信息，但 GLS 尚未收到实物包裹。 |
| `The parcel was handed over to GLS.` | GLS 已揽收包裹。 |
| `In transit`、`The parcel has left the parcel center` | 包裹正在各 GLS 分拨中心之间转运。 |
| `Customs Consignment via Customs Portal`、`The parcel has not been released by customs.` | 包裹处于清关环节，GLS 瑞士包裹的清关由瑞士邮政办理。 |
| `The parcel was released by customs.` | 已放行并恢复运输。 |
| `Final parcel center` | 已抵达末端分拨中心，下一步将安排派送。 |
| `In delivery`、`The parcel is in delivery.` | 正在派送中，预计当日送达。 |
| `The parcel could not be delivered as the consignee was absent` | 因收件人不在家未能投递。 |
| `The parcel is stored in the parcel center` | 派送未成功（例如收件人不在家）后暂存于分拨中心。 |
| `The parcel is ready for collection at the ParcelShop.` | 包裹已存放在自提点（ParcelShop）或快递柜等待取件。 |
| `The parcel has been delivered.`、`Delivered` | 已送达。 |

`Preadvice` 仅表示商家已将包裹信息录入 GLS 系统；如果持续多日未变，请联系发件人。当包裹被送至 ParcelShop 自提点时，GLS 有时也会标注为 `Delivered`，此时你仍需自行前往领取。上述两条状态及说明源自 GLS 荷兰（由 GLS 自行派送）；在瑞士境内实际由瑞士邮政负责派送。

## 瑞士 GLS 派送：瑞士邮政与领取通知单

**由谁派送。** GLS 在瑞士和列支敦士登的合作伙伴是 Swiss Post GLS（瑞士邮政设于巴塞尔的业务部门）。瑞士邮政负责入境 GLS 包裹的清关与派送，并特别说明其清关流程可能与瑞士邮政常规包裹有所不同。

**ParcelShop 自提点。** 没有任何 GLS 页面列出瑞士境内的 GLS ParcelShop 或自提柜。

**派送未成功。** GLS 未就瑞士境内的派送次数、通知卡或周六派送发布任何说明。如果瑞士邮政为你留下了领取通知单（collection note），以下为瑞士邮政对其自有邮件规定的处理方式（没有页面提到 GLS 包裹）：

- 持通知单或 Post-App 中的二维码前往领取，普通件通常保管 7 天，国际件保管 15 天。延长保管期限免费且无需注册账户。
- 登录“My consignments”后，可预约第二次派送、转寄至 My Post 24 自助柜、其他地址或邮局网点（工作日收费 8 瑞士法郎），或免费签发一次性授权书委托他人代领。

## GLS 包裹延误、滞留海关、丢失或损坏

发件人选用了 GLS，且正如瑞士邮政 GLS 页面所述，运费始终由发件人承担，因此遇到问题应优先联系商家。

**物流无更新。** 显示 `Preadvice` 表示包裹仍在商家手中；显示海关状态表示正在等待瑞士邮政完成清关。其他情况请参阅[为何物流停止更新](guide:tracking-not-updating)（英文）。

**海关费用。** 费用由谁承担取决于发件人选用的贸易条款（Incoterm）：选用 GLS 的 Incoterm 20 时，你需支付清关费、关税及增值税；选用 30 或 40 时费用分摊，你至少需支付增值税。瑞士邮政对 GLS 包裹收取其常规[清关费用](https://www.post.ch/en/business-solutions/exports-imports-and-customs-clearance/exports/swiss-post-gls/import-costs)。如对账单有异议，请致电瑞士邮政国际客服 +41 848 48 48 47；海关允许在税款核定通知书发出后 60 天内申请更正报关程序、地址或税额。

**丢失或损坏。** GLS 未针对瑞士发布理赔规则、申请期限或赔偿上限，请联系发件商家处理。

## GLS Switzerland 联系电话与邮箱

GLS 在瑞士没有自营客服热线。其联系页面指引用户访问“当地 GLS 国家公司网站”，在瑞士和列支敦士登即对应 Swiss Post GLS：

- **电话：** +41 848 48 48 47（Swiss Post GLS 进口热线，瑞士固话拨打 0.08 瑞士法郎/分钟，瑞士时间周一至周五 8:00–12:00 及 13:30–17:30）。瑞士邮政在商业与清关页面将其列为“GLS (parcels)”，并非专门的包裹追踪热线。
- **电子邮箱：** infogls@swisspost.ch（英文）、infogls@post.ch、infogls@poste.ch 或 infogls@posta.ch。
- **邮寄地址：** Swiss Post GLS, Post-Passage 11, 4002 Basel。
- **GLS 集团：** 提供英文[在线联系表单](https://gls-group.com/GROUP/en/contact-form/)。

> GLS 警示用户防范虚假邮件、短信、钓鱼网站及冒充人员上门。在欧盟以外地区，GLS 或其授权代理确实可能通过邮件、短信或信函通知缴纳关税，但 GLS 绝不会发邮件要求你通过银行卡、在线支付或银行转账支付货款。其主网站为 gls-group.com，如遇虚假信息请举报至 security@gls-group.com。

## 使用 Peek 追踪 GLS Switzerland 包裹

[Peek](/) 认得上述四种单号格式，并会询问可能的快递公司。由于 GLS Germany 与 GLS Switzerland 认得相同的单号，Peek 会根据你所在的国家（手机的地区设置，或在网页上按你连接的地点）判断。在瑞士或列支敦士登，页面通常会显示“GLS Switzerland 有这个包裹的记录。”；如果包裹寄往德国，请点“更换”并选择 GLS Germany。在其他地方，你会看到 GLS Germany，它同样可以用瑞士邮编跟踪发往瑞士的包裹。粘贴含 `/EU/` 或 `/GROUP/` 的 gls-group 链接时，会按 GLS Germany 跟踪。

GLS 仅在提供收件邮编后才显示扫描记录，因此“收件邮编”为必填项：四位数字，例如 8000。该邮编会发送给 GLS，必要时也会发送给追踪服务；若邮编与包裹不匹配，Peek 会提示“GLS Switzerland 用邮编……没有找到这个包裹。”以便你修改。

之后你可以看到包裹状态、每次扫描所在城镇与国家的地图位置、预计送达日期、包裹重量及送达时间，不会显示你的姓名、地址、联系方式或签名。状态保留 GLS 的英文原文；到达 ParcelShop 的状态，Peek 显示为“待取件”。Peek 大约每小时检查一次 GLS（派送当天也是如此；查询失败后间隔 4 小时），手动刷新不会让它更早检查。当另一家快递公司接手派送后，Peek 会按实际派送快递公司的节奏跟进。Peek 可以按每次扫描、重要环节或仅派送当天通知你。

当 GLS 指明由瑞士邮政派送时，包裹页面会显示“由 Swiss Post 派送”（若瑞士邮政仅收到电子预报、尚无自身扫描，则显示“Swiss Post 已收到预报，尚未收到包裹”）；一旦瑞士邮政出现时间不早于 GLS 最新记录的扫描，Peek 便可用同一单号直接跟进瑞士邮政的追踪动态。

## 关于 GLS Switzerland 物流查询的问题

### GLS 是什么快递公司？

GLS 全称为 General Logistics Systems，总部设于阿姆斯特丹，隶属于 International Distributions Services（原 Royal Mail Group）。在瑞士由其合作伙伴瑞士邮政（Swiss Post）负责包裹业务。

### GLS 寄到瑞士通常需要多久？

GLS 仅公布了从奥地利发往瑞士的参考时效：标准运输时间为 2 个运输日。从其他国家寄出时，请以商家的预计送达时间为准。

### 为什么 GLS 追踪无法查询或没有记录？

请先核对邮编：在输入收件邮编之前，GLS 不会显示任何扫描记录。此外请检查单号是否多输、少输或输错数字；旧版 `gls-group.com/CH/` 网址目前会跳转至错误页面。

### GLS 是正规快递公司吗？

是的，但存在冒用 GLS 名义发送诈骗信息的情况，请参阅上方的安全提醒。

:::sources
- [GLS：包裹追踪](https://gls-group.com/EU/en/parcel-tracking) – 单号查询、收件邮编要求与仅英文显示
- [GLS：瑞士合作伙伴](https://gls-group.com/GROUP/en/gls-partners/?partner=switzerland) – Swiss Post GLS 合作信息
- [GLS：列支敦士登合作伙伴](https://gls-group.com/GROUP/en/gls-partners/?partner=liechtenstein) – 同为 Swiss Post GLS 负责
- [GLS：关于我们](https://gls-group.com/GROUP/en/about-us/) – 公司概况与总部信息
- [GLS：联系我们](https://gls-group.com/GROUP/en/contact-form/) – 在线表单与各国热线指引
- [GLS：安全提示](https://gls-group.com/GROUP/en/security-advice/) – 防诈骗警示与举报邮箱
- [GLS 荷兰：收件人常见问题](https://gls-group.com/NL/en/faq-recipient/) – 状态表述含义及自提点送达标注说明
- [GLS 奥地利：寄往瑞士](https://gls-group.com/AT/de/versand-in-die-schweiz/) – 由瑞士邮政负责瑞士境内派送
- [GLS 奥地利：标准运输时效](https://gls-group.com/AT/de/regellaufzeiten/) – 奥地利至瑞士 2 个运输日标准时效
- [瑞士邮政：Swiss Post GLS](https://www.post.ch/en/business-solutions/exports-imports-and-customs-clearance/exports/swiss-post-gls) – 联系电话与联系邮箱
- [瑞士邮政：进口费用](https://www.post.ch/en/business-solutions/exports-imports-and-customs-clearance/exports/swiss-post-gls/import-costs) – 贸易条款（Incoterm）与费用异议流程
- [瑞士邮政：进口清关](https://www.post.ch/de/empfangen/importverzollung) – GLS 清关说明与费用标准
- [瑞士邮政：领取通知单](https://www.post.ch/en/receiving-mail/collection-note) – 保管期限与改派/代领选项
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Peek 读取字段与 GLS 将自提点到达标记为已送达的规则
:::
