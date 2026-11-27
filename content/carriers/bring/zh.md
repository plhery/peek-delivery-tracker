---
title: Bring（Posten）物流查询：单号、状态与取件期限
description: 查询挪威、瑞典和丹麦的 Bring 与 Posten 包裹：各类单号格式、英文状态含义、快递柜与自提网点保管期限、挪威清关、理赔与电话。
slug: bring-wuliu-chaxun
published: 2026-11-26
updated: 2026-11-26
---

Bring 是挪威国有邮政集团 Posten Bring 旗下的包裹品牌；Posten 则是其在挪威面向个人用户的品牌。两者共用同一套查询系统：可在 sporing.bring.no、sporing.posten.no、Posten 应用或上方输入框查件。单号由商家提供，或通过 Bring/Posten 的短信和邮件发送。

## Bring 运单号格式

Bring 说明，邮件或包裹单号通常为 13 位（两位字母、九位数字加两位字母），或是以 3 开头的 18 位数字，或是以 7 开头的 17 位数字。请输入完整组合。

| 单号（虚构） | 含义 |
| --- | --- |
| `RR482917367NO` | 两位字母、九位数字加 `NO`：邮件单号。 |
| `370748291736482919` | 以 `3` 开头的 18 位数字。 |
| `00370748291736482919` | `00` 加上以 `370` 开头的 18 位数字，共 20 位。 |
| `70482917364829174` | 以 `7` 开头的 17 位数字。 |

**在哪里找单号。** 查看商家的发货邮件、Bring 或 Posten 的短信和邮件，或 Posten 留在信箱通知单上的条码。Bring 自己的查询也可以按寄件人参考号或你的手机号查。

**Sendingsnummer 还是 kollinummer？** 一票货件（sending）可能包含多个包裹（kolli）：sendingsnummer 对应整票货件，每个 kollinummer 对应单个包裹，Bring 查询页会列出全部包裹。两者都属于 sporingsnummer（运单号），Bring 的查询两种都能查。

**查不到记录？** 确认包裹确实由 Posten 或 Bring 承运，且输入的不是商店订单号。相似单号：以 `SE` 或 `DK` 结尾的邮政单号属于 [PostNord](carrier:postnord)，以 `FI` 结尾的属于芬兰 [Posti](carrier:posti)。

## Bring 物流状态含义

sporing.posten.no 和 sporing.bring.no 默认显示挪威语，可切换为英语；tracking.bring.se 和 tracking.bring.dk 显示瑞典语和丹麦语，无芬兰语。英文原文大致按包裹流转顺序如下：

| Bring 显示 | 对你的含义 |
| --- | --- |
| `The sender has let us know that a parcel is on its way.` | 商家已预订配送，Bring 尚未收到包裹。 |
| `The shipment has been handed in to Bring.` | Bring 已收到包裹。 |
| `The shipment was delivered after the deadline and will be sent the next working day.` | 交寄时间已过当日截单点，将于次工作日发出，一切正常。 |
| `The shipment has arrived at the terminal.` | 已到达 Bring 分拣场站。 |
| `The shipment being processed for import.` | 正在办理进口清关（保留 Bring 原文语法）。 |
| `The shipment has been temporarily placed in warehouse.` | 已到达当地分拨站，等待安排派送班次。 |
| `The shipment is  unfortunately delayed and will be forwarded by first possible dispatch.` | 出现延误，将随最早一班运输转发。 |
| `The parcel is now on its way to the delivery address.` | 正在派送中（utkjøring）：已装上派送车。 |
| `The parcel is ready for pickup` | 已到达物流记录所列取件点，等你去取。 |
| `The parcel has been delivered in your mailbox or outside your door.` | 已送达（投进信箱或放在门外）。 |
| `The shipment has been returned.` | 正在退回寄件人，例如超过取件期限。 |

`The shipment has been temporarily placed in warehouse.` 听起来像被长期入库，但挪威语原意只是指负责派出派送车的当地分拨站点。像 `Home delivery date is changed.` 或 `The recipient has selected a desired delivery location.` 这类记录仅表示更改了派送安排，包裹本身尚未移动。

**中间几天不更新属正常。** Bring 和 Posten 说明，各场站之间转运途中的 1 至 4 天不会有扫描记录。一旦在本地场站登记，通常当天派送上门，或次日送达取件点。详见[物流为什么停止更新](guide:tracking-not-updating)（英文）。

## Bring 取件点、快递柜与送货上门

以下规则适用于挪威；瑞典和丹麦请查看 bring.se 或 bring.dk。

**存放在哪里。** 邮局、店内邮局（Post i Butikk）或 Pakkeboks 快递柜，物流通常写明具体地点；货运场站不是取件点。快递柜承重上限为 10 公斤。通知通过 Posten 应用、短信或邮件发出，最多发送两次提醒。

**保管期限。** Posten 现行取件期限：

- Pakkeboks 快递柜：4 天，可免费延长至 7 天。
- 其他取件点：7 天，在 Posten 应用或通过短信可限时免费延长至 14 天。
- 仅发纸质通知单的包裹（如部分海外包裹）：14 天。

部分寄件人不允许延期，具体截止日以查询页显示为准。逾期未取的包裹会退回寄件人。取件请带身份证件，部分包裹需核验身份。

**送货上门。** 若结账时选了送货上门，Posten 会在你选定的工作日派送：城镇地区为 17:00–21:00（奥斯陆、Stokke 和 Drammen 周边为 15:00–22:00），其他地区为白天；晚间派送会发短信告知 1 小时时段。如需把取件点包裹改送上门，可通过通知中的链接办理：送达取件点前收费 75 挪威克朗，送达后 100 挪威克朗；快递柜包裹和清关包裹不能改送。

**信箱包裹。** 以 Pakke i postkassen 寄出的小包裹随平邮派送。放不进信箱时会放在门外（请在门上标明姓名），或者在你收到 Posten 预告时选择改投取件点；送往取件点后无法再改送上门。

**大件商品。** Bring 的 Home Delivery 承运 35 公斤以上的家具、家电等商品，按网店所选档位交付：路边（Curbside，送至路边或地块边界）、入户（Indoor，搬至你指定位置）或场站自提（PickUp Point，前往 Home Delivery 场站自取）。未预约时段的订单，Bring 会发短信与你约定日期和时段，司机到达前会联系你，通常需要签收。

**瑞典与丹麦。** 在瑞典，包裹在代理点（ombud）通常保管 7 天，首次提醒可再延 7 天；快递柜（paketbox）保管 4 天，可延至 7 天，用 Bring 应用或短信 PIN 码开箱。在丹麦，上门派送未成功的包裹通常转送最近的 pakkeshop 取件点，Bring 会发短信或邮件通知；未转送时可通过 Bring 在线表单或电话预约重派。

## Bring 包裹延误、丢失或破损

**没有更新。** 超过预期时间仍无动态时，Bring 建议联系寄件人，确认包裹是否已交寄。

**破损或少件。** Bring 规定须由寄件人或支付运费的一方提交理赔；作为收件人请尽快通知商家。Bring 的申报时限：可见破损或少件须签收当场申报；隐蔽破损须在 7 个自然日内；延误等其他问题须在签收后 14 天内。所有理赔权均在一年后失效。请拍照留证并保留商品。显示已签收但没收到？参见[显示已签收但未收到](guide:delivered-not-received)（英文）。

**挪威清关。** 在加入挪威 VOEC 计划的网店购买（单件低于 3,000 挪威克朗）入境无需另缴费用。否则 Posten 收取清关代办费：货值不超过 500 挪威克朗收 46 挪威克朗，不超过 3,000 收 78 挪威克朗，以上收 278 挪威克朗，可通过 Posten 应用、Digipost 或信件缴费。更多见[包裹卡在海关怎么办](guide:customs)（英文）。

## Bring 客服电话与联系方式

Bring 与 Posten 官网公布的信息（均为挪威、瑞典、丹麦当地时间）：

- **挪威 Posten：** 22 03 00 00，工作日 08:00–17:00；全天候在线聊天。
- **挪威 Bring：** 04045（海外拨打 +47 22030045），工作日 08:00–17:00；全天候在线聊天。
- **书面投诉：** Posten and Bring customer service, Kjøita 19, 4630 Kristiansand。
- **瑞典：** 0770-11 33 00，工作日 08:00–17:00；全天候在线聊天。
- **丹麦：** 70 28 60 70（+45 70 28 60 70），工作日 08:00–17:00；全天候在线聊天。

> Posten 绝不会通过短信或邮件要求你为包裹付款：缴费只走 Posten 应用、Digipost 和纸质信件。收到此类链接请直接删除。

## 用 Peek 跟踪 Bring 包裹

对于以 `NO` 结尾且校验位正确的邮政单号，[Peek](/) 会立刻识别为 Bring。以 `370`/`373` 开头的 18 位数字、以 `00370` 或 `00373` 开头的 20 位条码以及以 `70` 开头的 17 位数字与其他承运商共用，因此在 GS1 校验位有效时，Peek 会优先询问 Bring，并同时询问对应候选：`373` 附问 [Matkahuolto](carrier:matkahuolto)，`00370` 和 `00373` 附问 Swiss Post Cargo、PostNord 和 NZ Post，`70` 附问 DHL eCommerce（在法国也同时询问 Colis Privé，在其他地方则最后才问 Colis Privé）。若只有 Bring 查到，即归入 Bring；若多家同时查到，由你选择。但对于以 `00373402216` 开头的条码，Peek 不会问 Bring——那是瑞典承运商 [Early Bird](carrier:early-bird) 的号段。如果 Bring 没有出现，点“更换”在“符合这个单号”下选择 Bring 即可。有一个例外：以 `370` 或 `373` 开头且第 6 到第 9 位全为 0 的 18 位数字会被 Peek 直接归入 [Ecoscooting](carrier:ecoscooting) 而不问 Bring，此时需点“更换”搜索 Bring。来自 sporing.bring.no、sporing.posten.no 以及 tracking.bring.com/.se/.dk 的链接无论单号如何都会识别为 Bring，但 Peek 在 Bring 处只能跟踪 17 或 18 位数字、以 `00` 开头的 20 位数字，以及校验位正确的各国邮政单号（两位字母、九位数字、两位字母）。包含多个包裹的 sendingsnummer 无法整体跟踪，请分别添加每个 kollinummer。

Peek 会显示当前状态、每次扫描所在城镇的地图位置、寄件人、重量、尺寸、Bring 给出时的预计送达日，以及待取期间的取件点名称。Peek 会用“Handed in”、“At terminal”或“Ready for pickup”等简短标签替代 Bring 的整句描述，略去详细地址、照片和取件码，并可在状态变化时发通知。当你打开包裹页，或在派送当天开启通知时，Peek 最多每 10 分钟查询一次；在 Bring 扫描包裹进入派送的当天，只要 Bring 自己的查件系统正常响应，自最新扫描地时间（挪威境内为挪威时间）早上 6 点起最多每 2 分钟查询一次。其余时间，以及连续两天无新动态（在“已生成面单”或“已揽收”状态下为 12 小时）后，改为每小时查询一次；在“已生成面单”下连续七天无新动态后改为每 6 小时一次。

## 关于 Bring 物流查询的常见问题

### Bring 和 Posten 是一样的吗？

不完全一样：两者同属 Posten Bring 集团并共用一套查询系统。Posten 服务挪威个人客户，Bring 服务北欧企业客户以及挪威以外的个人客户。企业用户可在 Bring 登录门户 Mybring 中一次查最多 50 个单号。

### Posten 派送需要多久？

Posten 说明，挪威境内的 Norgespakke 和取件点包裹通常需 2 至 5 个工作日。Bring Denmark 大多数包裹在 1 至 2 天内送达。

### Posten 和 Bring 几点派送？

平邮派送时间为 08:00 至 17:00，每天具体时间略有浮动，信件隔工作日派送。上门包裹在工作日派送，城镇为 17:00–21:00，其他地区为白天。

### Bring 快递柜能保管包裹多久？

在挪威，Pakkeboks 快递柜保管 4 天，可免费延至 7 天；超期退回寄件人。

### Bring 包裹丢失了怎么办？

联系网店：理赔由寄件人向 Bring 提交，且所有理赔权满一年即失效。

:::sources
- [Bring: Tracking](https://www.bring.no/en/services/tracking) – 单号格式、单号来源、寄件人参考号、当日派送
- [Bring: Sporing av pakker, post og gods](https://www.bring.no/tjenester/spore) – sendingsnummer 与 kollinummer、联系寄件人、1 至 4 天无更新
- [Posten: Help with Posten tracking](https://www.posten.no/en/tracking-help-page) – 信箱通知单、更新间隔、取件点、派送时效
- [Posten: Sporing hjelpeside og kundeservice](https://www.posten.no/sporing-kundeservice) – sendingsnummer 与 kollinummer、当日派送
- [Bring: Slik sporer du flere sendinger i Mybring](https://www.bring.no/radgivning/mybring/sporing-slik-sporer-du-flere-sendinger-i-mybring) – 最多 50 个单号
- [Bring: Spor sendingene dine](https://sporing.bring.no/sporing/) – 状态文案、语言、取件期限
- [Posten: Hentefrister](https://www.posten.no/motta/fleksibelt/hentefrister) – 取件期限、延期、退件、证件
- [Bring: Pakke til hentested](https://www.bring.no/tjenester/pakker-og-gods/private-nasjonalt/pakke-til-hentested) – 取件点、通知、提醒
- [Bring: Pakkeboks](https://www.bring.no/tjenester/pakker-og-gods/private-nasjonalt/pakkeboks) – 快递柜、尺寸与重量
- [Posten: Levering hjem med Posten alle hverdager](https://www.posten.no/motta/fleksibelt/hjemlevering) – 改派上门价格与例外
- [Posten: Home delivery with Posten](https://www.posten.no/en/receive/flexibility/home-delivery) – 时段、奥斯陆周边、1 小时短信窗口、改派例外
- [Posten: Dette bør du vite om levering av post](https://www.posten.no/kundeservice/sendinger-i-norge/sporsmal-om-levering-av-post) – 邮递员时段、信件投递日、选择 Posten
- [Bring: Vilkår for Pakke hjem i postkassen](https://www.bring.no/vilkar/pakke-hjem-postkassen) – 随邮派送、放门外、门牌姓名
- [Bring: Home Delivery](https://www.bring.no/tjenester/pakker-og-gods/private-nasjonalt/hjemlevering-tyngre-gods) – 35 公斤以上大件、服务档位
- [Bring: Tjenesteunike vilkår for Home Delivery](https://www.bring.no/vilkar/tjenesteunike-vilkar-for-home-delivery) – 短信时段、司机联系、签收
- [Bring: Generelle leveringsvilkår for Home Delivery](https://www.bring.no/vilkar/vilkar-for-home-delivery) – sending 与 kolli
- [Posten: Download the Posten app](https://www.posten.no/en/tracking-help-page/tracking-app) – 应用查 Posten 和 Bring、取件码、快递柜格口
- [Posten: Customs clearance of shipments from abroad](https://www.posten.no/en/customs/receiving-from-abroad) – VOEC、清关费、缴费渠道、不发收费短信
- [Bring: Claims and complaints](https://www.bring.no/en/customer-service/claims-and-complaints) – 理赔主体、时限
- [Posten: How to complain or make claims](https://www.posten.no/en/customer-service/complaint-and-claims) – 投诉渠道、地址、处理时间
- [Bring: Contact us](https://www.bring.no/en/customer-service/contact-us) – 电话、时间、在线聊天
- [Posten: Contact us](https://www.posten.no/en/customer-service/contact-us) – 电话、时间、在线聊天
- [Posten: Online fraud and fake inquiries](https://www.posten.no/en/customer-service/online-fraud) – 钓鱼诈骗提醒
- [Bring: Svindelforsøk](https://www.bring.no/kundeservice/svindelforsok) – 绝不通过此类链接收费
- [Bring Sweden: Kontakta oss](https://www.bring.se/privat/kundservice/kontakta-oss) – 电话、时间、在线聊天
- [Bring Sweden: Vanliga frågor](https://www.bring.se/privat/kundservice/vanliga-fragor) – 代理点延期
- [Bring Sweden: Bring Paketbox](https://www.bring.se/privat/ta-emot/paketbox) – 应用或短信 PIN 开箱
- [Bring Sweden: Tjänstevillkor](https://www.bring.se/villkor/Tj%C3%A4nstevillkor-bring-ecommerce-logistics_01102026.pdf) – 代理点 7 天、快递柜 4 天与延期
- [Bring Denmark: Kontakt os](https://www.bring.dk/privat/kundeservice/kontakt-os) – 电话、时间、在线聊天
- [Bring Denmark: Ofte stillede spørgsmål](https://www.bring.dk/privat/kundeservice/ofte-stillede-sporgsmal) – 无人签收、时效、证件、错误单号
- [Bring Denmark: Pakker til genlevering](https://www.bring.dk/privat/kundeservice/genlevering) – 表单或电话预约重派
- [Bring Denmark: Tjenestevilkår](https://www.bring.dk/vilkar/Tjenestevilk%C3%A5r-bring-ecommerce-logistics_01102026.pdf) – 无人签收转取件点
- [Posten Bring: About the group](https://www.postenbring.no/en/about-us/about-the-group) – 归属关系、Posten 与 Bring 品牌
- [Bring: Tracking API](https://developer.bring.com/api/tracking/) – 各事件代码含义、一票多件
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Peek 读取范围
:::
