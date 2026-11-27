---
title: Swiss Post Cargo 货运查询：单号、状态与派送规则
description: 查询瑞士邮政货运 Swiss Post Cargo（原 PostLogistics）托盘与大件货件：20 位 SSCC 条码及 PL 参考号、状态含义与派送规则。
slug: swiss-post-cargo-tracking
published: 2026-11-27
updated: 2026-11-27
---

Swiss Post Cargo 是瑞士邮政（Swiss Post）旗下的货运品牌，专门通过卡车承运托盘及其他大件货物（从单件货到整车货运），并非普通小包裹服务。你可以凭寄件人提供的条码或参考号在官方 Track & Trace 页面（tt.swisspost-cargo.com）或上方输入框中查询。如果你拿的是瑞士邮政普通包裹单号，请改看 [Swiss Post（瑞士邮政）页面](carrier:swiss-post)。

## Swiss Post Cargo 货运单号

Swiss Post Cargo 查询页接受单件货物的条码或寄件人参考号；使用参考号会一并调出整票货物下的所有条码。Peek 关联到 Swiss Post Cargo 的格式如下（虚构示例）：

| 单号 | 含义 |
| --- | --- |
| `00482917365428193648` | 以 `00` 开头的 20 位数字：托盘或单件货物条码（SSCC），末位为校验位。 |
| `PL-48291736` | `PL-` 加 8 位数字：客户参考号。 |

请先查看商家邮件，如找不到再向商家索取；另见[如何查找快递单号](guide:find-tracking-number)（英文）。如果系统查无记录，请核对数字并联系寄件人。

以下两类号码不按 Swiss Post Cargo 查询：

- **瑞士邮政普通包裹：** 以 `99` 开头的 18 位数字（印刷为 `99.00.482917.36542819`），或 2 位字母、9 位数字加 `CH`（`RR482917367CH`）。
- **PostLogistics 旧格式参考号**（如 `48291736-542`）：PostLogistics 现已更名为 Swiss Post Cargo，其旧查询页 tracking.postlogistics.ch 已停用；Peek 仍会将这类参考号归在 PostLogistics 名下查询。

## Swiss Post Cargo 物流状态含义

Swiss Post Cargo 提供英语、德语、法语和意大利语扫描文本。英文界面下，一票已送达货件的常见节点如下：

| Swiss Post Cargo 显示状态 | 对你的含义 |
| --- | --- |
| `Data entry POST` | 寄件人数据已录入系统，货物尚未交给 Swiss Post Cargo。 |
| `Received warehouse POST` | 货物已到达 Swiss Post Cargo 枢纽或仓库（旁边会注明站点名）。 |
| `Loaded for Delivery` | 已装上派送卡车，正在派送途中。 |
| `IMAGE` | 送达照片记录，紧邻在送达扫描之前。 |
| `DELIVERED SCANNED` | 已在所示城镇送达。 |
| `SIGNATURE` | 送达签收记录，时间戳与送达扫描一致。 |

`Data entry POST` 仅表示收到电子数据，“POST”只是固定措辞，货物此时尚未发出；`IMAGE` 和 `SIGNATURE` 则是附属于送达的签收凭证，而非新的运输事件。

## Swiss Post Cargo 派送：时间、位置与未送达

**派送时效。** 瑞士邮政货运手册说明，货物通常在次一工作日送达：在瑞士和列支敦士登境内重型卡车可通达区域，于次一工作日瑞士时间 17:00 前送达；较偏远的阿尔卑斯山谷及山区（瓦莱州、提契诺州和格劳宾登州）则于第二个工作日 17:00 前送达。

**送达位置。** 标准派送仅在工作时间内送至一楼第一道门、卸货平台或路边，不送上楼。司机默认现场可供托盘车通行，卸货由收件人负责（若司机协助卸货系代收件人操作），并会请你签字确认无误。

**寄件人加购的增值服务。** 电话/邮件/短信提前通知、预约派送、送货上楼以及二次派送均属付费附加项，只有寄件人下单购买时才会提供。

**错过派送与自提。** 瑞士邮政货运手册未提及任何自提点或自提柜，且二次派送属于上述附加服务；手册也未说明仓库保管天数或收件人改约流程，请联系订舱发货的商家协调。

## Swiss Post Cargo 货件延误、丢失或破损怎么办

Swiss Post Cargo 的签约客户是商家和企业，因此遇到延误、破损或短少时请第一时间联系商家，并立即拍照留证发送给商家。

**多日无更新。** 若一直停在 `Data entry POST`，说明货物可能仍在寄件人处，请向商家确认交货时间。常规停滞排查见[物流长期不更新](guide:tracking-not-updating)（英文）。

**显示已送达但没收到。** 先在官方追踪页查看 `SIGNATURE` 和 `IMAGE` 记录，随后联系商家。参阅[显示已签收但未收到](guide:delivered-not-received)（英文）。

**警惕诈骗信息。** 瑞士邮政防诈骗页面提醒，冒充瑞士邮政的虚假信息会通过短信、邮件、WhatsApp、钓鱼网站、电话及社交媒体传播，常借口包裹、补费、追踪或核实个人资料行骗。

## Swiss Post Cargo 客服联系方式

Swiss Post Cargo 面向商家提供服务，因此请先联系为您安排运输的商家。瑞士邮政 2022 年 4 月版货运手册仅提供一个面向企业客户、不含电话的联系渠道：Customer Service Transport CH, Lagerstrasse 12, 5606 Dintikon，邮箱 sales.ls4@post.ch。

若咨询瑞士邮政普通信件与包裹（非 Cargo 货运），瑞士邮政热线为 +41 848 888 888（瑞士固话每分钟 0.08 瑞士法郎），服务时间为瑞士时间周一至周五 07:30 至 18:00、周六 08:00 至 12:00。

## 使用 Peek 追踪 Swiss Post Cargo 货件

以 `00` 开头的 20 位条码归属各发货企业而非某一家承运商，因此 [Peek](/) 会显示“正在查询物流服务…”，仅在 Swiss Post Cargo 查到记录时才标明为其承运。`PL-48291736` 格式参考号会同时询问 Swiss Post Cargo 和格式相近的 TNT Italy。对于其他 6 至 40 位字母数字参考号，请点击“更改”手动选择 Swiss Post Cargo，或直接粘贴 tt.swisspost-cargo.com、apv.swisspost-cargo.com 的 Track & Trace 链接（含 `/public/trackandtrace/` 加单号）。如果这类链接里是瑞士邮政普通包裹单号，Peek 会按瑞士邮政包裹追踪。

Peek 展示当前阶段、带各扫描城镇地图标记的完整轨迹以及送达时间，时间按苏黎世时间解读并换算为你设备所在时区。文本保留英文原文，并将 `Loaded for Delivery` 归类为正在派送；签名和照片行会合并进送达记录中（不显示照片原图）。Peek 不显示你的姓名、地址、预计送达日期、重量或自提点。

若多个货件共用同一参考号，仅当其中恰有一票在近 60 天内有扫描时系统才能读取，否则 Swiss Post Cargo 查无结果（Peek 会显示“等待 Swiss Post Cargo 更新”，两天后提示核对单号），此时请改用 20 位条码查询。当你打开货件页面或在派送日开启通知时，只要官网正常响应，Peek 最高每 10 分钟检查一次，出库派送当天自瑞士时间早上 6 点起每 2 分钟检查一次，并支持每次扫描、重要节点或仅派送日通知；其余时间及连续两天无新动态后（处于“已创建运单”或“快递公司已收件”状态时为 12 小时）改为每小时一次。

## Swiss Post Cargo 货运查询常见问题

### Swiss Post Cargo 是什么？

它是瑞士邮政货物物流业务自 2025 年 3 月 1 日起启用的统一品牌，面向瑞士及海外企业提供含大件货运在内的物流服务。集团在瑞士的子公司包括瑞士邮政全资拥有的 Swiss Post Cargo CH AG，PostLogistics 是首批更名并入该品牌的子公司之一。

### PostLogistics 和 Swiss Post Cargo 是一回事吗？

是的，PostLogistics 现已以 Swiss Post Cargo 名义运营。其旧查询页 tracking.postlogistics.ch 已停用，新查询页为 tt.swisspost-cargo.com；Peek 仍支持追踪 `48291736-542` 这类旧 PostLogistics 参考号。

### Swiss Post Cargo 有联系邮箱吗？

瑞士邮政 2022 年 4 月版货运手册列出了面向企业客户的邮箱 sales.ls4@post.ch。作为收件人，请先写信联系下单发货的商家。

### 为什么会收到 Swiss Post Cargo 发来的短信？

通常是因为寄件人额外购买了派送通知服务（瑞士邮政货运手册将电话、邮件或短信通知列为附加服务）。但市面上也存在冒充瑞士邮政的诈骗短信，请自行前往官方追踪页输入单号核实。

### 可以实时查看 Swiss Post Cargo 货车地图吗？

不支持实时车辆定位。追踪页会列出每次扫描所在的城镇，瑞士邮政货运手册也仅说明会定期更新货件所在位置。

:::sources
- [Swiss Post: Transport freight brochure](https://www.post.ch/en/-/media/post/gk/dokumente/stueckgut.pdf) – single items to whole truckloads, orders through Swiss Post Cargo Web by business customers, next working day, mountain areas, ground floor, unloading, signature, sender extras including a second delivery, regular location updates, Dintikon contact and email
- [Swiss Post: Logistics market](https://site.post.ch/en/about-us/company/strategy-and-value/business-activities/logistics) – Swiss Post Cargo is Swiss Post's goods logistics, solutions for companies in Switzerland and abroad
- [Swiss Post: Group companies](https://site.post.ch/en/about-us/company/governance/organisation/subsidiaries) – Swiss Post Cargo CH AG, 100% Swiss Post
- [Swiss Post: Help and contact](https://www.post.ch/en/help-and-contact) – letters and parcels line, price, hours
- [Swiss Post: Current fraud attempts](https://site.post.ch/en/about-us/security/phishing-and-fraud/current-fraud-attempts) – fake messages in Swiss Post's name
- [VerkehrsRundschau: Schweizerische Post mit neuem Marktauftritt](https://www.verkehrsrundschau.de/nachrichten/transport-logistik/logistik-schweizerische-post-fuehrt-neue-marke-ein-3626449) – Swiss Post Cargo name from 1 March 2025, PostLogistics among the first group companies to take it
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – what Peek reads: barcodes and references, number shapes, the English scan wordings, Track & Trace addresses, PostLogistics now trading as Swiss Post Cargo
:::
