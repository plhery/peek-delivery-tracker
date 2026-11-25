---
title: 奥地利邮政（Austrian Post）查询：单号、状态与取件
description: 查询奥地利邮政（Austrian Post）包裹：22 位与 AT 单号格式、德英状态对照、黄色取件通知单、Poststation 自提柜与客服联系。
slug: austrian-post-kuaidi-chaxun
published: 2026-11-25
updated: 2026-11-25
---

奥地利邮政（Österreichische Post AG，总部位于维也纳）是奥地利的国家邮政公司。你可以在提供英文界面的 post.at、Post App 中查件，或直接将单号粘贴到上方查询框。单号由发货商家、奥地利邮政电子通知（e-notification）或未妥投后留下的黄色通知单提供。

## 奥地利邮政单号格式与查找位置

奥地利邮政帮助页面给出了两种包裹单号形态（以下数字为虚构示例）：

| 单号形态 | 说明 |
| --- | --- |
| `1048291736548291736482` | 奥地利邮政自有单号：以 10 开头的 22 位数字。 |
| `RR482917367AT` | 国际邮政单号：2 位字母、9 位数字、2 位字母；以 AT 结尾即属奥地利邮政。 |

**常见位置。** 商家发货邮件中；如果你注册了免费的 Post Account 且寄件人上传了发货数据，还会出现在奥地利邮政的电子通知（邮件或 Post App）里；未妥投时也会印在黄色取件通知单上。

海关标签上的 6 位 CRN 编码并不是快递单号。

**容易混淆的单号。** 不少快递公司都使用 22 位数字：`0281450281454829105736` 是 TIPSA 的形态，`0048290048291057364918` 属于 [CTT Express](carrier:ctt-express)；`RR482917367DE` 则是其他国家邮政的国际单号。如果 post.at 查不到你的单号，请核对商家邮件中写明的承运商。

## 奥地利邮政物流状态含义

post.at 同时支持德语与英语（切换 EN 即可查看英文）。下表每行先列出英文文案，再给出对应的德文原文：

| 奥地利邮政状态 | 对你的含义 |
| --- | --- |
| `Sender is preparing the shipment for shipping`、`Versender*in bereitet die Sendung für den Versand vor` | 商家已预报包裹，奥地利邮政尚未收到实物。 |
| `Item accepted`、`Zum Transport erhalten` | 奥地利邮政已揽收包裹准备运输。 |
| `Shipment being processed at the logistics center`、`Sendung im Logistikzentrum in Bearbeitung` | 正在物流中心分拣处理。 |
| `Item in distribution`、`Sendung in Verteilung` | 已在奥地利境内，预计未来几个工作日内派送。 |
| `Delay due to incorrect distribution`、`Verzögerung wegen Fehlverteilung` | 分拣错区导致轻微延误，你无需采取任何操作。 |
| `Shipment out for delivery`、`Sendung in Zustellung` | 正在派送，预计今天送达。 |
| `Item could not be delivered - consignee notified`、`Empfänger nicht angetroffen - brieflich benachrichtigt` | 派送时无人收件，通知单已放入你的信箱。 |
| `Item ready for pick-up`、`Sendung abholbereit` | 已到达网点或自提柜，可在通知单注明的截止日前领取。 |
| `Item arrived in Austrian Post pick-up box`、`Sendung in Post Empfangsbox eingelangt` | 已投递至你所在楼宇的邮政收件箱。 |
| `Item handed over to delivery partner`、`Übergabe an Zustellpartner` | 已移交合作快递公司负责末端派送。 |
| `Shipment delivered`、`Sendung wurde zugestellt` | 已签收送达。 |
| `Sendung an Empfänger∗in übergeben` | 已当面交予收件人：已签收。 |
| `Return/addressing error`、`Rücksendung/Adressierungsfehler` | 地址错误或不全，包裹正在退回寄件人。 |

帮助页面常给出 `Item in distribution` 这类汇总状态，而轨迹详情多为 `Shipment being processed at the logistics center`，因此并非每条都会出现。`Electronic order information was provided by the sender` 表示仅生成了面单，商家发出的“已发货”邮件并不代表包裹已交寄；如果几个工作日仍无变化，请联系寄件人。跨境包裹出现 `Item received` 则表示寄出国承运商已收件。

**海关扫描。** 境外寄入包裹抵达奥地利后可能出现海关节点。Peek 会原样保留奥地利邮政接口返回的德文文案并标记为“清关中”：`Sendung im Zollanmeldungsprozess. Weitere Info folgt.`（正在办理清关申报）、`Lagereingang - Zollanmeldungshindernis`（因清关申报受阻已暂存，奥地利邮政查件页会提示你补交资料）、`Sendung ist im Zollprozess verzögert. Grund: unplausibler Warenwert.`（清关延误，此处原因为申报货值存疑，“Grund:”后的具体原因会随情况变化），以及 `Zollanmeldungshindernis - Rückmeldung vom Empfänger eingelangt`（已收到你的回复，包裹继续留在海关等待放行）。

## 奥地利邮政取件、自提柜与未妥投处理

**派送时效。** 奥地利邮政说明，奥地利境内包裹通常在两个工作日内送达，Post Express 一般于次日工作日 13:00 前送达。

**未妥投。** 投递员会在信箱留下黄色通知单（Gelber Zettel），写明取件地点和最后取件日期。保管期自次日起算，一直持续到随后的第三个星期一，因此至少有 14 天；但网店寄件人可将保管期缩短至 5 个工作日。

**暂存地点。** 邮局网点（在营业时间内取件）或全天 24 小时开放的 Poststation 自提柜（需输入通知单上的取件码）。奥地利邮政在网点、居民区和超市设有 1,500 多组 Poststation。

**取件要求。** 携带带照片的身份证件和单号；即使没带纸质通知单，也能在邮局网点取件。他人持你的黄色通知单可代领；免费的电子通知同样可用于取件，并可转发给他人代取。

**修改投递方式。** 在 Post Account（“Empfangsoptionen”）或 Post App 中可免费单次或永久改投：转寄至邮局网点、Post Partner 合作点、Hermes PaketShop、Poststation 自提柜、家中指定放置点或指定邻居。永久设置最早在 3 个工作日后生效。

**Post Empfangsbox 楼宇收件箱。** 投递员留下的通知单条码仅能打开你所在楼宇的收件箱一次，请务必当场取出包裹。

**清关与税费。** 奥地利邮政代办欧盟以外入境包裹的报关手续，并在派送或网点取件时收取进口增值税、关税及其自身进口手续费。自 2026 年 7 月 1 日起，货值不超过 150 欧元的包裹按每类商品统一征收 3 欧元关税；自 2026 年 11 月 1 日起，无论货值多少，网购包裹还需按每类商品加收 2 欧元欧盟处理费。详见[包裹卡在海关怎么办](guide:customs)（英文）。

## 奥地利邮政包裹延误、丢失或破损

寄件人是奥地利邮政的签约客户，遇到问题应先联系寄件人。

**无更新。** 延误不等于丢件。奥地利邮政建议国内包裹超过 3 至 5 个工作日状态毫无变化时再发起查单。另见[物流长时间不更新怎么办](guide:tracking-not-updating)（英文）。

**显示已签收但未收到。** 若状态显示已送达或待取件，请先检查信箱、指定放置点和邻居处，随后联系寄件人。见[显示已签收但未收到包裹](guide:delivered-not-received)（英文）。

**丢件查单。** 只有寄件人可以发起调查（Nachforschung）；境外包裹则由寄件人或原寄国承运商发起。网购订单请找商家。寄件人提交查单的时限为国内件 3 个月、国际件 6 个月；查单通常至少需要两周。

**破损申报。** 最迟须在签收后 7 天内（不含周日及法定节假日）通过 Post Account 在线或到网点申报，并附上商品及包装照片。在奥地利邮政回复前请保留全部物品与包装，并告知寄件人：赔付款通常付给寄件人。

## 奥地利邮政客服电话与联系方式

以下渠道来自奥地利邮政官网：

- **个人客户热线：** +43 800 010 100，周一至周五 7:00 至 19:00，周六 8:00 至 13:00。
- **在线联系表单：** 全天候开放的 [post.at 联系表单](https://www.post.at/kontaktformular)。
- **电子邮箱：** 不对收件人提供客服邮箱。

> 奥地利邮政提醒防范仿冒未妥投短信（诱导点击链接确认信息）及带二维码的假黄色通知单。官方邮件均以 @post.at 结尾，且派送普通包裹绝不会索要你的 IBAN 银行账号。

## 使用 Peek 追踪奥地利邮政包裹

对于以 AT 结尾且校验位有效的国际单号，[Peek](/) 会立刻识别为奥地利邮政。对于 22 位数字单号，Peek 会先询问奥地利邮政，有记录即归属之；否则查询追踪服务，再查 [USPS](carrier:usps)。若该单号同时符合 TIPSA 或 CTT Express 的形态，Peek 会同时向它们查询。对于 24 位数字，Peek 会向奥地利邮政、InPost 与 Ciblex 查询，仅当奥地利邮政有记录时才识别为它。若 Peek 未自动识别，可点击“更换”手动选择。粘贴 post.at 链接仅支持内含国际邮政单号（2 位字母、9 位数字、2 位字母）的链接；22 位或 24 位数字请直接粘贴纯单号。

Peek 会展示当前状态、每个物流中心在地图上的位置、重量、尺寸、签收时间，以及揽收或派送流转期间的预计送达时间（具体日期、日期区间或时段）；进入派送当天后仅显示当天的时间窗。时间均换算为你设备的时区；在 peektracker.com 及 iPhone、Android 应用中，轨迹底部会写明“时间按你所在的时区显示：”及对应时区名称。Peek 会标示包裹已待取件，但不显示具体取件地点与寄件人信息。状态文案保留奥地利邮政接口返回的德文，因此签收包裹会显示 `Sendung wurde zugestellt`。

在你打开包裹页面期间，或在派送当天开启提醒时，只要奥地利邮政官网有响应，Peek 最高每 10 分钟检查一次，在奥地利邮政扫描进入派送当天当地时间早上 6 点起（若提供了派送时间窗，则从时间窗开始前半小时起）每 2 分钟检查一次；其余时间，以及连续两天没有新动态后（若包裹仍停留在“已生成面单”或“已揽收”则为 12 小时后），改为每小时检查一次。当仅由追踪服务提供更新时，Peek 每小时检查一次，超过一天无新动态后每 3 小时一次，在派送当天打开页面或开启提醒时最高每 15 分钟检查一次。它可按每次扫描、重要环节（含海关扫描）或仅派送当天推送提醒。22 位单号出现 `Übergabe an Zustellpartner` 后，Peek 不会继续跟进合作派送商。其他国家邮政单号若原寄国标明发往奥地利（或未标目的地而你在奥地利追踪），一旦奥地利邮政出现不早于原寄国最新节点的扫描，便会自动转由奥地利邮政继续追踪；从他国追踪 AT 结尾单号时也会按同样规则转交当地邮政（如德国 DHL、法国 La Poste），随后 Peek 将按实际派送方的节奏刷新。

## 奥地利邮政查询常见问题

### 奥地利邮政可以用英文查件吗？

可以。post.at 提供英文页面，切换至 EN 后查件结果会显示英文状态文案。

### 为什么我的奥地利邮政单号查不到？

包裹可能仅完成了电子预报：在寄件人真正交寄前信息很少。否则请核对数字是否有误，并确认是否属于其他同样使用 22 位数字的快递公司。

### 奥地利邮政有客服邮箱吗？

不向收件人提供客服邮箱。请使用在线联系表单或拨打客服热线。

### Zollanmeldungshindernis 是什么意思？

表示包裹的清关申报遇到阻碍。奥地利邮政会先暂存包裹并显示 `Lagereingang - Zollanmeldungshindernis`，查件页会提示你补交缺失材料。你回复后状态会变为 `Zollanmeldungshindernis - Rückmeldung vom Empfänger eingelangt`，包裹继续留在海关直至放行。

:::sources
- [Austrian Post: FAQ](https://www.post.at/en/p/c/faq) – 单号示例、派送时效、14 天取件期与清关规定
- [Austrian Post: Helpful information about your item status](https://www.post.at/en/p/c/helpful-information-item-status) – 英文状态说明与取件规则
- [Austrian Post: Contact us by phone](https://www.post.at/en/k/c/hotline) – 客服热线、服务时间与状态列表
- [Austrian Post: Contact](https://www.post.at/en/k/c/contact-us) – 热线与在线联系表单
- [Austrian Post: Item inquiry](https://www.post.at/en/p/a/item-inquiry) – 谁可发起查单及申报时限
- [Austrian Post: Damage report](https://www.post.at/en/p/a/damage-report) – 7 天破损申报期限
- [Austrian Post: Online risks](https://www.post.at/en/i/c/online-risks) – 诈骗短信与假通知单警示
- [Österreichische Post: Hilfe zu Ihrem Sendungsstatus](https://www.post.at/p/c/hilfe-sendungsstatus) – 德文状态含义、保管期与破损说明
- [Österreichische Post: Sendungssuche](https://www.post.at/s/sendungssuche) – 查件页、Post Account、EN 切换与状态
- [Österreichische Post: Nachforschung](https://www.post.at/p/a/nachforschung) – 何时发起查单及收件人处理步骤
- [Österreichische Post: Poststation](https://www.post.at/p/c/abholstation) – 自提柜与改投功能
- [Österreichische Post: Wunsch-Poststation oder Wunsch-Postfiliale](https://www.post.at/g/c/paket-services-wunsch-poststation-postfiliale) – 1,500 多组 Poststation 自提柜
- [Österreichische Post: Gelber Zettel](https://www.post.at/p/blog-gelber-zettel) – 黄色通知单与电子通知
- [Österreichische Post: Paketumleitung](https://www.post.at/p/a/paketumleitung) – 改投选项
- [Österreichische Post: Postempfangsbox](https://www.post.at/p/c/empfangsbox) – 楼宇收件箱说明
- [Österreichische Post: FAQ Privatkund\*innen](https://www.post.at/p/c/faq-privat) – CRN 编码与清关说明
- [Österreichische Post: Jahresfinanzbericht 2023](https://assets.post.at/-/media/Dokumente/De/Investor-Relations/Geschaefts--und-Nachhaltigkeitsberichte/Jahresfinanzbericht-Oesterreichische-Post-AG-2023.pdf) – 法定名称与公司所在地
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Peek 读取的数据字段、海关扫描及含义
:::
