---
title: Slovak Parcel Service（SPS）追踪：状态、Balíkovo 自提柜与客服
description: 追踪斯洛伐克 SPS（Slovak Parcel Service）包裹：703 单号格式、斯洛伐克语状态含义、Balíkovo 自提柜、理赔及客服电话。
slug: slovak-parcel-service-tracking
published: 2026-11-26
updated: 2026-11-26
---

Slovak Parcel Service（简称 SPS）是一家斯洛伐克本土快递公司，自 2002 年起隶属[奥地利邮政（Austrian Post）](carrier:austrian-post)，在斯洛伐克全境为网购商家派送包裹，支持送货上门、Balíkovo 自提柜或合作门店自提。你的追踪单号位于商家的发货通知邮件或 SPS 的短信/邮件中：可在 sps-sro.sk 输入查询，或粘贴到上方输入框中。

## Slovak Parcel Service 追踪单号格式

SPS 包裹单号为以 `703` 开头的 14 位数字。面单上会分段印刷并附带两位额外数字，如 `703-482-91736405 01`（数字为虚构）。这 16 位数字是面单条码；去掉最后两位即为 14 位包裹单号。

| 单号（虚构） | 所属承运商 |
| --- | --- |
| `70348291736405` | SPS：以 `703` 开头的 14 位数字。 |
| `703-482-91736405 01` | 同一件包裹在面单上的印刷样式（末尾多出 2 位数字）。 |
| `48291736405829` | 非 `703` 开头的 14 位数字：可能属于 DPD、[SEUR](carrier:seur)、[BRT](carrier:brt) 等，而非 SPS。 |
| `RR482917367SK` | 以 `SK` 结尾的邮政单号：属于[斯洛伐克邮政（Slovenská pošta）](carrier:slovenska-posta)，而非 SPS。 |

若 sps-sro.sk 提示“Please check that you entered the shipment number correctly (no spaces)”，请去除空格和短横线后重新输入。SPS 指出准确格式见于你的确认邮件或短信。其查询框有时还会要求输入目的地邮编或客户编号以展示更多详情。完全没有收到邮件？请询问商家，并参阅[如何找到你的追踪单号](guide:find-tracking-number)（英文）。

## SPS 追踪状态含义

SPS 追踪页面（sps-sro.sk，支持斯洛伐克语和英语）列出每次扫描的日期、时间、状态与中心名称。即使在英文版网站上，扫描原文也保持为斯洛伐克语：

| SPS 状态原文 | 对你的含义 |
| --- | --- |
| `Definícia dát` | [已生成面单](guide:tracking-statuses)（英文）：商家已将包裹电子数据传给 SPS，SPS 尚未拿到实物。 |
| `Odnáška` | 快递员已从发件人处揽收包裹。 |
| `Prvá registrácia` | 包裹首次在 SPS 分拨中心扫描登记。 |
| `Vstup` | 抵达分拨中心，运输中。 |
| `Doručovací hárok` | 已列入当日派送清单：正在派送中。 |
| `Uložený v balíkomate` | 已存入 Balíkovo 自提柜，等待取件。 |
| `Informácia o doručení` | 签收前数分钟录入的派送详情，并非物理移动。 |
| `Doručenie` | 已送达。 |
| `Balík odmietnutý` | 包裹已被拒收。 |
| `Uskladnené` | 暂存于分拨中心仓库。 |
| `Návrat odosielateľovi` | 正在退回发件人。 |

两条与货到付款相关的记录（`Editor hotovosti` 和 `Dobierka odoslaná zákaznikoví`）仅属财务记账，不代表包裹发生移动。

随后网站会跳转至 mySPS（mysps.sk），以英语或斯洛伐克语显示进度条：`Received Info`、`Taken by carrier`、`In transit`、`Delivering`、`In the collection point`（仅限自提点件），以及最终的 `Delivery` 或 `Undelivered`。请注意：`Delivering` 表示今日正在派送，而 `Delivery` 表示已送达。

## 派送方式、Balíkovo 自提柜与派送未成功

**送货上门。** 面向发件人时，SPS 斯洛伐克国内标准派送为揽收后的下一个工作日。快递员将包裹送至建筑物的第一道可上锁大门，仅在双方另行约定情况下送货上楼。若发件人购买了通知服务，SPS 最晚会在派送当天通过短信、邮件或电话告知派送日期、货到付款金额及改寄方式；邮件通知会在派送前一天发出。

> 短信与电话通知仅支持斯洛伐克手机号码。使用外国号码将收不到短信，因此请同时向商家提供你的电子邮箱。

**一次派送尝试。** 根据 SPS 条款，快递员进行一次上门派送尝试。派送未成功时，SPS 可将包裹改寄至最近的空闲 Balíkovo 自提柜或合作门店，并通过短信或邮件通知你取件地点。

**Balíkovo 自提网络。** SPS 的自提网络（原 SPS Parcel Shops）由合作门店与自助柜组成。SPS 的“What is balíkovo?”页面称拥有超过 2,500 个网点和 1,000 台自提柜，首页标注 1,200 台自提柜，而合作招商页仍写着超过 2,400 个网点。包裹到站后你会收到含 PIN 取件码的短信或邮件，取件人须凭该 PIN 码开柜或取件。自提柜保管 **48 小时**（到期前 24 小时发送提醒），合作门店最长保管其 **7 个工作日**。旺季期间 SPS 可调整保管时长，mySPS 提供“Extend storage period”延期服务（可能收费）。自提柜内逾期未取的包裹将退回发件人。

**更改派送安排。** mySPS 提供“Flexible Delivery”功能，每天可修改一次预计日期或派送方式。改寄至自提点须确保包裹登记了你的邮箱或手机号。

**货到付款。** 上门签收时可向快递员支付现金（除非快递员同意，上限 500 欧元）或刷卡。对于 Balíkovo 自提件，SPS 条款说明你通过短信或邮件中的链接在线支付，付款后才会收到自提柜 PIN 码；但 SPS 的“How balíkovo works”页面仍称仅限现金、不支持刷卡。包裹当面拆封后不可退还给快递员，须直接联系商家处理。

## SPS 包裹延误、丢失或损坏怎么办

**多日无更新。** SPS 对此没有公布规定。由于合同由商家与 SPS 签订，请先联系商家，并参阅[为何物流停止更新](guide:tracking-not-updating)（英文）。在 SPS 的“Where is my parcel”栏目输入包裹信息后可打开联系表单。

**包裹损坏。** 签收时请当场查验并报告可见破损。请保留原始外箱与填充物且不要使用商品：根据 SPS 理赔规则，缺少原包装会导致索赔失效。你或发件人均可通过 sps-sro.sk 上的“Report parcel damage”表单提交理赔，并附上单号、邮编或客户号、照片、情况说明、索赔金额以及内件与货值证明。

**理赔时限**（2026 年 5 月 1 日起施行的理赔规则）：损坏或部分遗失须在签收后 **2 个工作日**内提交；整件丢失、签收争议或保时效服务延误须在 SPS 揽收后 **30 个自然日**内提交。虽然 SPS 常见问题页面写着签收后 7 天，但具有法律约束力的是《理赔规则》，因此请在 2 个工作日内行动。SPS 会在 30 个自然日内作出裁定，赔款支付给发件人，或在发件人书面授权下支付给你。

**显示已签收但未收到。** 签收争议适用上述 30 个自然日时限；请同时通知商家，并参阅[显示已签收但未收到包裹](guide:delivered-not-received)（英文）。

**拒收或退回。** 拒收包裹会立即退回发件人，状态出现 `Návrat odosielateľovi` 即予以确认。

## Slovak Parcel Service 客服电话与联系方式

来自 sps-sro.sk：

- **电话：** 拨打当地区号加 `16877`；其网点列表给出布拉迪斯拉发、班斯卡-比斯特里察、特伦钦、特尔纳瓦和日利纳为 `02/16877`，科希策为 `055/16877`，尼特拉为 `037/16877`。海外致电：`+421 2 168 77`。
- **服务时间：** 每个工作日斯洛伐克时间 `8:00–16:30`。
- **mySPS：** SPS 推荐使用 mysps.sk 以获最快处理，支持全天候追踪与提交客服请求。
- **邮箱或在线聊天：** 未面向收件人公布。

> SPS 提醒称假冒包裹派送短信和邮件再次出现。请谨慎对待要求输入银行卡或个人信息的链接；若未网购请直接删除。

## 使用 Peek 追踪 SPS 包裹

[Peek](/) 识别 SPS 的单号格式（`703` 开头的 14 位数字），但该长度与 DPD、SEUR、BRT、[Hermes Germany](carrier:hermes-de) 等共享。在斯洛伐克地区，Peek 会优先询问 SPS；在其他国家，SPS 排在同长度承运商较后顺位，因此在添加单号或首次查询时不会询问它，包裹首月内的后续检查可能会查到它。如果其他承运商认领了该单号或暂未匹配，请点击“更换”并选择“Slovak Parcel Service”（搜索“SPS”即可）。请输入 14 位单号，16 位面单条码无法识别为 SPS。粘贴带 `shipment_id=` 的 sps-sro.sk 链接无论单号格式如何都会直接指定 SPS，而 mysps.sk 链接则不会。

SPS 的扫描时间不带时区：Peek 按斯洛伐克时间解析并展示 SPS 自己的历史记录，包括状态、扫描网点及重量，扫描文本保持 SPS 斯洛伐克语原文，`Doručenie` 算作已签收。没有预计送达日期；存入 Balíkovo 自提柜时显示 `Uložený v balíkomate` 及 SPS 附带的地点信息。

当你在 Peek 中打开包裹页面，或在派送当日开启通知时，Peek 最多每 10 分钟检查一次；在包裹进入 `Doručovací hárok` 派送清单当天，只要 SPS 自己的追踪系统有响应，便从斯洛伐克时间早上 6 点起最多每 2 分钟检查一次。连续两天没有新动态后（包裹仍显示“已生成面单”或“已揽收”时为 12 小时），Peek 改为每小时检查一次；显示“已生成面单”满七天后每 6 小时检查一次。

当最新扫描距今已满四天，且包裹仍处于“已生成面单”、“已揽收”或“运输中”时，Peek 会用该次扫描的日期加以标记，例如“自10月12日周一起没有物流更新”。包裹页面随后会显示“包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 Slovak Parcel Service 查询。”，并附上指向 SPS 客服页面的“向 Slovak Parcel Service 寻求帮助”链接。如果三周没有新扫描，页面会显示“已超过三周没有新动态。如果还没收到，请联系卖家。Peek 会持续向 Slovak Parcel Service 查询。”

Peek 可以按“每次扫描”、“重要环节”或“仅派送当天”通知你，但无法代你延长自提柜保管期或重发 PIN 码。

## 关于 Slovak Parcel Service 物流查询的问题

### Slovak Parcel Service 的客服电话是多少？

拨打当地区号加 `16877`（例如布拉迪斯拉发拨 `02/16877`），海外致电 `+421 2 168 77`。接线时间为斯洛伐克时间工作日 `8:00–16:30`。

### SPS 会在 Balíkovo 自提柜保留包裹多久？

自存入自提柜起保留 **48 小时**（到期前 24 小时发送提醒）。Balíkovo 合作门店最长保留其 **7 个工作日**。

### Slovak Parcel Service 有手机应用吗？

SPS 的页面指引你使用网页门户 mySPS（mysps.sk），你可以在其中追踪包裹、修改派送、重新获取 PIN 码及在线支付货到付款。

:::sources
- [SPS: Track & Trace](https://www.sps-sro.sk/en/moja-zasielka/) – 扫描原文、列字段、未查到提示及 Balíkovo 说明
- [SPS: Obchodné podmienky účinné od 1.9.2026](https://backend.sps-sro.sk/storage/documents/82/sps-obchodne-podmienky-sk-09-2026-FMFHUp.pdf) – 派送时效、一次尝试、改寄、通知、斯洛伐克手机号限制、保管期、PIN 码、货到付款及海外电话
- [SPS: Rules for claims valid from May 1, 2026](https://backend.sps-sro.sk/storage/documents/64/sps-reklamacny-poriadok-en-2026-aqjOdQ.pdf) – 理赔流程、2 工作日/30 天期限、保留原包装及赔款规则
- [SPS: Do you need to contact customer service?](https://www.sps-sro.sk/en/kontakt/zakaznicky-servis) – 服务时间与当地区号 + 16877
- [SPS: Kontaktné strediská](https://www.sps-sro.sk/kontakt/kontaktne-strediska) – 各中心电话列表及 mySPS 推荐
- [SPS: Ako vygenerovať príjmový doklad](https://www.sps-sro.sk/documents/ako-vygenerovat-prijmovy-doklad_v2.pdf) – 面单上的 16 位条码格式
- [SPS: Where is my parcel](https://www.sps-sro.sk/en/moja-zasielka/kde-je-moj-balik) – 该栏目涵盖范围与表单入口
- [SPS: Report parcel damage](https://www.sps-sro.sk/en/moja-zasielka/nahlasit-poskodenie-balika) – 货损申报表单
- [SPS: FAQ](https://www.sps-sro.sk/en/zasielky/faq) – 常见问答中的 7 天索赔表述
- [SPS: Pozor na podvodné SMS a emaily](https://www.sps-sro.sk/novinky/pozor-na-podvodne-sms-a-emaily-2) – 虚假短信与邮件防骗提醒
- [SPS: Become a Balíkovo Pickup Point](https://www.sps-sro.sk/en/balikovo/ako-sa-stat-balikovom) – 超过 2,400 个自提网点说明
- [SPS: What is balíkovo?](https://www.sps-sro.sk/en/balikovo/co-je-balikovo) – 超过 2,500 个网点与 1,000 台自提柜
- [SPS: Home](https://www.sps-sro.sk/en) – 1,200 台 Balíkovo 自提柜
- [SPS: Ako funguje balíkovo?](https://www.sps-sro.sk/ako-funguje-balikovo/) – 货到付款仅限现金表述
- [mySPS: interface texts](https://rest.mysps.sk/lang/get/en) – 进度条标签、Flexible Delivery、延长保管期与 PIN 码
- [Austrian Post: Subsidiaries international](https://post.at/en/j/c/careers-austrian-post-subsidiaries-international) – 自 2002 年起隶属奥地利邮政
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Peek 读取字段与识别逻辑
:::
