---
title: Transpress（Транспрес）快递查询：保加利亚包裹指南
description: 查询保加利亚 Transpress（Транспрес）快递包裹：如何在 Peek 添加单号、派送尝试次数、退件与赔付规则及客服联系方式。
slug: transpress-tracking
published: 2026-11-27
updated: 2026-11-27
---

Transpress（Транспрес）是一家保加利亚快递与物流公司，总部位于索非亚—博茹里什泰（Sofia-Bozhurishte）工业园。其快递业务由两家适用相同通用条款的注册邮政运营商运营：Транспрес ООД 与 Транспрес Деливъри ЕООД。要追踪包裹，可将 Transpress 查询页链接粘贴到上方输入框，或粘贴单号后手动选择 Transpress——Peek 无法仅凭格式识别 Transpress 单号。

## Transpress 单号是什么格式的

Peek 目录未收录 Transpress 的单号规则，而 Transpress 官网查询页也只要求填写“Shipment number”（保加利亚语“Номер на пратката”），未说明具体长度或样式。因此 Transpress 不会出现在“符合这个单号”下，本页也不列出格式示例。

| 你粘贴的内容 | Peek 的处理方式 |
| --- | --- |
| 仅粘贴 Transpress 单号 | Peek 可能在“符合这个单号”下显示其他承运商，但这仅因长度相近。请手动选择 Transpress（见下文）。 |
| 带单号的 Transpress 查询链接：`transpress.bg/bg/prosledjavane?track-number=` 或 `/en/prosledjavane?track-number=` | Peek 会提取链接中的单号并立即识别为 Transpress（“识别出的快递公司”）。 |
| 商家注明属于其他快递公司的单号 | 请直接用该承运商追踪：商家发货邮件是判断承运方的最可靠线索。 |

**单号在哪里找。** 请在商家发货邮件或订单页面查看；若为个人寄件，请向寄件人索取。完全找不到单号？参阅[如何查找快递单号](guide:find-tracking-number)（英文）。

## Peek 中 Transpress 物流状态的含义

Peek 不直接抓取 Transpress 官网，因此代码中不含 Transpress 原始措辞。Peek 会依次向 ParcelsApp、Ship24 和 17TRACK 等追踪服务查询，并展示最先查到记录的那一家提供的轨迹（保留其原文）。第三方追踪服务有时会落后于 Transpress 官网。Peek 会把每条记录归入以下标准阶段：

| Peek 显示 | 对你的含义 |
| --- | --- |
| “已创建运单” | 寄件人已创建货件，Transpress 可能尚未拿到包裹。 |
| “快递公司已收件” | Transpress 已揽收包裹。 |
| “运输途中” | 包裹正在转运途中。 |
| “清关中” | 国际包裹正在办理清关，清关耗时会计入总时效。 |
| “正在派送” | 今日正在派送路线途中。 |
| “派送未成功” | 快递员尝试派送但未能送达。 |
| “待取件” | 包裹正在某处等你领取（例如 Transpress 网点）。请在 Transpress 官网查看地点或询问商家。 |
| “已送达” | 已交付给你或代收人。 |
| “已退回寄件人” | 包裹已退回寄件人。 |
| “需要关注” | 追踪服务回报了异常，请阅读该行下方的说明。 |

在任何追踪服务返回记录前，Peek 会先显示“正在查询更新”，随后显示“等待 Transpress 更新”。若连续两次查询失败，包裹仍显示“等待 Transpress 更新”，页面会提示“暂时无法连接 Transpress。Peek 会继续重试。”如果你交由 Peek 自动判定承运商，同等等待状态通常显示“暂无物流记录”，“无法获取更新”表示连续两次查询失败，Peek 会持续重试。更多见[物流状态详解](guide:tracking-statuses)（英文）。

## 保加利亚境内 Transpress 派送规则

根据 Transpress 快递服务介绍页及其自 2026 年 11 月 1 日起生效的快递通用条款：

**派送速度。** Transpress 覆盖保加利亚全境，每日发车，次一工作日或按当地班期送达。各城镇班期可在 transpress.bg 的“Schedule for services in settlements in Bulgaria”页面查询。工作日每日收件，周六派送属增值服务。

**上门签收。** 快递员按运单地址派送，需凭本人签名及身份证件（身份证或驾照）签收。如果你不在家，同住家庭成员或该地址处同意代为转交的其他人员，凭你的书面授权或电话确认也可代收；公司地址可由同事、前台或保安代收。

**两次派送尝试。** 基础运费包含最多两次派送尝试，第二次尝试在首次派送截止日期后的 3 个工作日内进行。

**拒收包裹。** 拒收须在 Transpress 单据上书面确认。

**网点自提。** 包裹也可寄至 Transpress 位于博茹里什泰（Bozhurishte）、布拉戈耶夫格勒（Blagoevgrad）、布尔加斯（Burgas）、瓦尔纳（Varna）、大特尔诺沃（Veliko Tarnovo）、弗拉察（Vratsa）、普列文（Pleven）、普罗夫迪夫（Plovdiv）、鲁塞（Ruse）、旧扎戈拉（Stara Zagora）和舒门（Shumen）的网点。在网点存放满 3 个工作日后，寄件人须支付仓储费。

**付款与通知。** 货到付款为付费增值服务；POS 机刷卡支付以及短信或 Viber 通知属于免费服务。

**当面验货。** 请当着快递员面查验内件；若发现破损，须与快递员共同签署一式两份的破损报告或在派送单据上注明。

如遇派送失败或待自提，参阅[错过快递派送](guide:missed-delivery)（英文）和[未领取包裹处理](guide:uncollected-parcel)（英文）；非欧盟入境包裹参阅[海关清关指南](guide:customs)（英文）。

## Transpress 包裹无更新、丢失或退回怎么办

**暂未查到。** 追踪服务可能尚未收录该单号，或暂不支持 Transpress 的该类货件。Peek 会持续自动查询，对未查到的服务最多隔一天重试一次。若添加两天后仍无任何轨迹，Peek 会提示“仍在等待快递公司的首次更新”。请对照商家邮件核对单号；若数日仍无结果，可点击“打开 Transpress 官网”自行查询，并向商家确认是否已真实寄出。

**多日无更新。** 当包裹停在“已创建运单”、“快递公司已收件”或“运输途中”满四天无新记录时，Peek 会标出最新记录日期（如“自 10 月 12 日周一以来无物流更新”），并显示“包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 Transpress 查询。”及指向 Transpress 联系页的“联系 Transpress 获取帮助”链接。超过三周无新记录时则显示“已超过三周无新动态。如果仍未收到，请联系卖家。Peek 会持续向 Transpress 查询。”参阅[物流为何停止更新](guide:tracking-not-updating)（英文）。

**退回寄件人。** 若因拒收、收件人长期不在、查无此人或地址有误/不全而无法派送，Transpress 会通知寄件人并在通知后 14 天内退件，双程运费由寄件人承担。如寄件人在 3 个工作日内未给指示或无法联系，包裹将视为无人认领：Transpress 再保管 14 天后归其所有，可自行变卖或销毁。参阅[包裹被退回寄件人](guide:returned-to-sender)（英文）。

**显示已送达但没收到。** 请先检查门口周围并询问邻居，再联系商家。参阅[显示已签收但未收到](guide:delivered-not-received)（英文）。

**丢失或破损。** 若 Transpress 在 30 天内无法找到包裹即视为丢失。自 Transpress 揽收之日起 6 个月内可书面提出索赔，涵盖丢失、损毁、被盗、破损、派送延误、无故退件及代收货款延迟打款。Transpress 须在 30 天内答复保加利亚境内索赔（国际件 90 天内），并在同意理赔后 30 天内赔付。

未保价包裹在提供货值与破损证明后按快递运费赔付，上限为保加利亚境内 7.67 欧元、国际件 25.56 欧元；无故退件赔付 3.00 欧元。保价包裹按实际损失赔付，上限为保价金额。延误超过 2 个工作日时，保加利亚境内每日按运费 10%、国际件每日按 2% 赔付，上限 50%。协商不成时，可依条款向保加利亚通信监管委员会（КРС）求助或诉诸保加利亚法院。

网购订单请先联系商家，由订运商家出面处理或为你重发。参阅[包裹丢失](guide:lost-parcel)（英文）和[包裹破损](guide:damaged-parcel)（英文）。

## Transpress 客服与联系方式

来自 transpress.bg 联系页面：

- **电话：** \*5015、0700 150 50 或 02 948 07 00，办公时间为保加利亚时间周一至周五 09:00 至 18:00。
- **物流追踪咨询**（2,500 公斤以内货件）：保加利亚境内发送至 aftersales@transpress.bg，国际件发送至 international@transpress.bg。
- **通用邮箱：** transpress@transpress.bg。
- **在线聊天：** 在 transpress.bg 网站每日保加利亚时间 7:00 至 23:00 开放，或通过聊天窗口表单留言。
- **理赔申诉：** 使用官网“Complaints”（保加利亚语版“Рекламации”）表单。

## 使用 Peek 追踪 Transpress 包裹

[Peek](/) 支持超过 4,500 家快递公司，但无法仅凭格式识别 Transpress 单号。粘贴 Transpress 追踪链接可直接识别；仅凭单号请按以下步骤手动选择：

1. 将单号粘贴到上方输入框。
2. 点击承运商旁的“更改”并搜索 Transpress（搜索“Транспрес”带“又称……”提示或搜索“Bulgaria”均可找到）。
3. 选择 Transpress，该行显示“由你选择”后开始追踪。

对已添加包裹请使用“更改快递公司”。选择前，承运商一行可能显示“追踪时由 Peek 查找”或“暂未查到 · Peek 会继续查询”，这对新单号均属正常。

如果交由 Peek 自动判定，Peek 会查询追踪服务；一旦对方轨迹中出现实际流转并标明承运商为 Transpress，Peek 即会自动归入 Transpress 名下。

**显示内容。** 展示当前阶段以及追踪服务提供的每条记录（附带对方给出的日期与地点）。在追踪服务尚未查到前，详情页的“打开 Transpress 官网”会直接以你的单号打开保加利亚语版官网追踪页（网站也提供英文版）；一旦追踪服务有了记录，该链接改为打开追踪服务页面。

**自动切换承运商。** 在添加后的前 30 天内，Peek 还会询问与该单号格式相符的其他承运商；若其中某一家出现真实流转记录，Peek 会自动切换过去并提示“已自动从 Transpress 切换”。

**查询频率。** Peek 每小时查询一次；连续一天无新消息后改为每 3 小时一次；停留在“已创建运单”满七天后改为每 6 小时一次。出库派送当天只要打开页面或开启通知，最高每 15 分钟检查一次。通知支持“每次扫描”、“重要节点”或“仅在送达时”。

Peek 无法代你联系 Transpress、商家或寄件人，也无法更改派送或发起索赔。

## Transpress 快递查询常见问题

### 如何查询 Transpress 包裹？

将 Transpress 官网查询链接粘贴到上方输入框，Peek 会自动识别；如果只有单号，粘贴后点击“更改”手动选择 Transpress 即可（Peek 无法凭格式识别），随后通过追踪服务跟进。Transpress 自家查询入口位于 transpress.bg 的“Shipment tracking”。

### Transpress 会尝试派送几次？

最多两次：运费已包含两次派送尝试，第二次会在首次派送截止日后的 3 个工作日内进行。若仍无法送达，Transpress 会通知寄件人并安排退件。

### 如何向 Transpress 提交理赔？

自 Transpress 收件之日起 6 个月内，在 transpress.bg 的“Complaints”表单中以书面方式提交。保加利亚境内件 30 天内答复，国际件 90 天内答复。网购订单请先联系商家。

### Transpress 的客服电话是多少？

\*5015、0700 150 50 或 02 948 07 00，办公时间为保加利亚时间周一至周五 09:00 至 18:00。物流追踪邮箱为 aftersales@transpress.bg（境内）及 international@transpress.bg（国际）。

:::sources
- [Transpress: Shipment tracking](https://transpress.bg/en/for-clien/shipment-tracking) – English tracker, "Shipment number", "Complaints" form
- [Transpress: Проследяване на пратка](https://transpress.bg/bg/prosledjavane) – Bulgarian tracker, "Номер на пратката", link with the number
- [Transpress: Куриерски услуги](https://transpress.bg/bg/kurierski-uslugi) – coverage, daily departures, next working day or by schedule, weekdays, Saturday delivery, extra and free services
- [Transpress: Общи условия за куриерски услуги на Транспрес Деливъри ЕООД](https://www.transpress.bg/web/files/richeditor/obshtiusloviyatranspressdelivery20261101.pdf) – delivery, identity, attempts, refusal, returns, storage, customs, claims, compensation, КРС
- [Transpress: Общи условия за куриерски услуги на ТРАНСПРЕС ООД](https://www.transpress.bg/web/files/richeditor/obshtiusloviyatranspressood20261101.pdf) – the same terms for Транспрес ООД
- [Transpress: Общи условия](https://transpress.bg/bg/obshti-uslovija) – the two companies' courier terms
- [Transpress: Контакти](https://transpress.bg/bg/kontakti) – phones, hours, emails, offices, chat hours
- [Transpress: График за обслужване](https://transpress.bg/bg/za-klienti/grafik-za-obslujvane) – schedule by town
- [Universal Parcel Scraper: Transpress](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/transpress) – names and other names, Bulgaria, tracker address, link rule, no number shape, no wordings, tracking services only
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – tracking services, a service's carrier name matched to Transpress, links to the carrier's or the service's page
:::
