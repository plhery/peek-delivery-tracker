---
title: InPost 物流查询：单号格式、状态说明与自提柜取件
description: 查询英国、波兰、意大利和葡萄牙的 InPost 包裹：单号格式与查找、物流状态中文说明、自提柜取件码与保管期限，以及各国客服联系方式。
slug: inpost-tracking
published: 2026-11-24
updated: 2026-11-24
---

InPost 是一家起源于波兰的自提柜网络，目前在英国、波兰、意大利、葡萄牙及其他国家提供自提柜、合作门店及上门派送服务。你可以在对应国家或地区的 InPost 官网查询包裹（例如英国站 [inpost.co.uk/tracking](https://inpost.co.uk/tracking)），或直接把单号粘贴到上方输入框中。单号由商家或寄件人提供；自提柜取件码则是另外一组码。

## InPost 运单号：格式与查找位置

InPost 意大利表示运单号位于商家的订单邮件和 InPost 的取件通知邮件中；InPost 葡萄牙则建议直接询问寄件人。如果找不到邮件，请参阅[如何查找运单号](guide:find-tracking-number)（英文）。

| 单号（示例） | 说明 |
| --- | --- |
| `JD4829173605841926` | `JD` 加 16 位数字：英国。 |
| `JJD4829173605841926` | `JJD` 加 16 位数字：英国。 |
| `8YDR482917360` | `8YDR` 加 9 位数字：英国。 |
| `48KLT2917360A582` | 2 位数字、3 位字母、7 位数字、`A` 加 3 位数字：英国 Yodel 面单号。 |
| `648291736058419263748591` | 24 位数字：波兰和意大利。bpost 和 Ciblex 也使用这种长度。 |
| `48291736` | 8 位数字，与 inpost.pt 查询页面上的示例一致。同样符合 Mondial Relay 的格式。 |

InPost 英国表示其单号通常以 `JD`、`JJD` 或 `8` 开头。`JJD` 或 `JD` 后跟 18 位数字属于 DHL 单号。取件码、PIN 码、二维码以及波兰的“kod nadania”（按 Allegro Lokalnie 规则，是 InPost 提供给卖家、用于免打印面单寄件的 9 位寄件码）均不能用于物流查询。如果商家邮件写明了其他快递公司，[该单号可能属于那家承运商](guide:tracking-number-formats)（英文）。

## InPost 物流阶段：每条更新的含义

InPost 英国 2024 年 7 月的官方博客按顺序列出了其网站显示的主要状态标题；实际文案此后可能有所调整。

| InPost 英国显示 | 对你的含义 |
| --- | --- |
| `Sit tight, your parcel’s loading` | 寄件人已打包，InPost 尚未拿到包裹。 |
| `We’re on it` | 寄件人已投递，正等待 InPost 揽收。 |
| `It’s on the road` | 正在 InPost 网络内转运，该状态可能会重复出现。 |
| `Not long now` | 已到达你所在地的派送中心。 |
| `It’s getting close` | InPost 将在今天尝试派送。 |
| `Delivery day, yay!` | 包裹已存入自提柜或门店，请在 3 天内取件。 |
| `Tick tock...` | 距离取件截止还剩最后一天。 |
| `Uh oh` | 正在退回寄件人：逾期未取，或 InPost 无法送达取件点。 |
| `It's back` | 已退回给寄件人。 |

需要注意的是，`Delivery day, yay!` 表示包裹已经存入网点、3 天取件倒计时已经开始，而不是司机正在赶来派送。

在英文界面下，InPost 的物流查询服务（即 Peek 读取的服务）还会使用以下状态表述：`Sent through Parcel Locker/Parcel Point`（寄件人通过自提柜或网点寄出）、`Collected by the Courier`（由 InPost 快递员揽收，并非由你取走）、`Handed over for delivery`（正在派送中，送往你家或自提柜）、`Failed delivery attempt`（快递员未能成功派送）以及 `Acceptance refusal`（收件人拒收）。

## InPost 自提柜取件：取件码、保管期限与派送未成功

**取件码。** InPost 英国会通过邮件发送 9 位取件码、发短信至你留给商家的手机号，并在 InPost 应用中显示。到达自提柜后，扫描二维码，或输入去掉首位 `0` 的手机号再加上取件码即可开柜。在意大利和葡萄牙，通知中会包含取件码或 PIN 码以及二维码。没收到取件码？在 InPost 英国应用的 Tracking → Receiving 下可以查看，也可使用 Remote Opening 远程开柜，无需输入取件码；在意大利，应用中的“Apri a distanza”功能可在距自提柜约 70 米范围内远程开锁。

| 国家/地区 | 取件保管期限 |
| --- | --- |
| 英国自提柜或门店 | 3 天，截止前 24 小时发送提醒，不可延期。 |
| 波兰 | 请在 InPost Mobile 应用或 InPost 波兰官网查看。InPost 账户条款将延长 Paczkomat 取件期限列为其提供的服务之一。 |
| 意大利 | 通常约为 3 天；具体截止日期见 InPost 邮件，很少提供延期。 |
| 葡萄牙 | 境内包裹为 5 个自然日，不过 InPost 部分页面也写为 8 天。 |

逾期未取的包裹将退回寄件人；InPost 意大利不就此向你收费，在英国则由商家处理退款。本人不便前往？InPost 英国和意大利都建议将取件码分享给可代取的人。

**自提柜已满或故障。** InPost 英国可能会将包裹改投至附近的 InPost 自提柜或合作门店并通知你具体位置；InPost 葡萄牙会发邮件和短信让你等待原网点恢复或另选其他网点，若你未回复，可能会为你分配家附近的网点。InPost 物流服务可能会显示 `Stored Parcel - Temporary Parcel Locker`：包裹暂时存放在附近自提柜，仍使用同一取件码；若未在期限内领取（`The storage period has expired`），才会转投至你原先选择的柜机。

**英国上门派送**时间为周一至周六 7:00 至 21:00，司机出发后应用内会显示 2 小时派送时段。若想将符合条件的包裹改送自提柜，可在 InPost 应用中点击“Manage my parcel”，再选择“Deliver to Locker”。错过派送？请先查看物流记录是否已交给邻居或放在安全位置；否则 InPost 会把包裹放入附近的 InPost 自提柜（如有），或在下一个工作日再次尝试派送。

**英国以外更改取件点。** 在波兰，InPost 账户条款将更改与改派列为其应用的服务功能。InPost 葡萄牙说明包裹一旦进入网络便无法更改取件点，不过其查询页面或 InPost Iberia 应用可能会视包裹状态提供该选项。InPost 意大利的常见问题一方面指出可通过邮件中的链接将上门派送转为自提柜或 InPost Point，另一方面又在另一条回答中说明无法更改。

## InPost 物流不更新、延误或丢失

**英国。** 国际包裹可能需要长达一周才会交接给 InPost，在此之前物流可能不会更新。如果 InPost 收到包裹后连续 72 小时无更新，请联系其客服团队。InPost 表示退款由零售商或电商平台处理。另请参阅[物流为什么停止更新](guide:tracking-not-updating)（英文）。

**波兰。** 波兰邮政监管机构 UKE 对所有邮政运营商制定了通用规则，InPost 已登记在其邮政运营商名录中。寄件人可以提出投诉（reklamacja），一旦包裹送达你手中或寄件人将索赔权转让给你，你也可以投诉。投诉须在寄出后 12 个月内提出，隐蔽破损须在 7 天内提出；明显破损应在取件时当场提出。运营商须在 30 天内答复，逾期未复则视为接受投诉。

**意大利和葡萄牙。** InPost 意大利通过电话受理包裹丢失或延误问题，如属内件破损则会让你联系商家；其条款注明争议可提交意大利通信监管机构 AGCOM 处理。InPost 葡萄牙在发货通知发出后超过 5 天可受理延误申诉，超过 30 天可受理丢失申诉。

## InPost 客服与联系电话

各国官方客服联系方式如下：

- **英国：** 拨打 0330 335 0950（按运营商本地通话费计费），周一至周五 7:00 至 22:00、周末 8:00 至 20:00，或在 [InPost 帮助页面](https://inpost.co.uk/yodel-help)使用“Leave a message”留言。
- **波兰：** 在线联系表单、InPost Mobile 应用内的 AI 聊天助手、电话 +48 722 444 000 或 +48 746 600 000，或致信 InPost sp. z o.o., ul. Pana Tadeusza 4, 30-727 Kraków。
- **意大利：** 拨打 +39 0238582894，周一至周六 8:00–20:00，或发邮件至 servizioclienti@inpost24.it。
- **葡萄牙：** 拨打 211 245 624，工作日 9:00–18:00。

## 使用 Peek 查询 InPost 包裹

[Peek](/) 会直接识别 `8YDR` 开头的单号。对于 24 位纯数字、`JJD`、`JD` 以及 Yodel 面单号，Peek 会向 InPost 查询其是否收录该单号（24 位数字还会同时查询奥地利邮政和 Ciblex，以 `3232` 或 `3299` 开头时则优先查询 bpost）。其他格式（包括 8 位数字）即使你手动选择 InPost，也不会通过 InPost 直连接口查询，而是仅通过通用物流查询服务获取信息。你可以直接粘贴 inpost.pl 查询页面的链接；inpost.co.uk 链接仅在包含 `8YDR` 单号时可识别；inpost.it 和 inpost.pt 的链接不会被读取，直接粘贴单号即可。

在西班牙，Peek 将 InPost 归在 Mondial Relay 条目下追踪（搜索“InPost Spain”），并在 inpost.es 上读取 `FV` 单号（`FV` 加 8 位数字）。对于 8 位、10 位或 12 位西班牙单号，点击“更换”并选择 Mondial Relay 即可；但此时 Peek 会查询 Mondial Relay 的法国站，未必能查到 InPost 西班牙的包裹。

Peek 读取的是 inposteasy.com 上的 InPost 服务，目前尚未确认该接口能返回英国单号。页面会展示当前状态、最新 20 条扫描记录（并在地图上标出每条记录的城镇或枢纽及国家）、签收时间以及目的国；InPost 不提供预计送达日期。对于等待在波兰 Paczkomat 自提柜或取件点的 24 位单号包裹，待 InPost 波兰系统确认后会显示网点名称和地址，但绝不会显示你的取件码。Peek 向 InPost 接口请求英文文案，但停留在意大利取件点的包裹可能不会显示“待取件”。缺少时区信息的扫描记录会被跳过，Peek 也绝不会读取你的姓名、地址和电话。

Peek 不会将 InPost 包裹继续转交至其他快递公司追踪。只要 InPost 官方查询正常响应，Peek 最高每 10 分钟检查一次，在 InPost 扫描包裹进入派送中的当天早上 6 点（当地时间）起最高每 2 分钟检查一次（通过物流查询服务时，在派送当天打开页面或开启提醒时最高每 15 分钟检查一次），并在每次状态变化时发送通知。当包裹由其他承运商接手派送（如同 InPost 承接部分跨境包裹那样）时，Peek 会按实际派送承运商的频率更新。若连续两天无新动态（包裹仍处于“已生成面单”或“已揽收”状态时为 12 小时），Peek 会转为每小时检查一次。

## InPost 物流查询常见问题

### InPost 和 Yodel 是同一家公司吗？

Yodel 现已归属 InPost。InPost 英国于 2025 年 4 月收购了 Yodel，Yodel by InPost 的包裹均可通过 InPost 应用和官网查询。

### InPost 是波兰公司吗？

它创立于波兰，波兰运营主体 InPost sp. z o.o. 总部位于克拉科夫；集团母公司 InPost S.A. 注册于卢森堡。

### 我的 InPost 6 位取件码在哪里？

InPost 英国的取件码为 9 位，会通过邮件、短信和 InPost 应用发送；InPost 葡萄牙的 6 位 PIN 码通过邮件或短信发送；在波兰请查看 InPost Mobile 应用。

### InPost 派送需要多久？

InPost 英国的查询页面告知寄件人：寄至自提柜或合作门店不超过 3 个工作日，送货上门为 4 个工作日。

:::sources
- [InPost UK: Tracking a parcel page](https://inpost.co.uk/tracking) – 72 小时规则、国际包裹、取件码、Tracking → Receiving、Remote Opening 与派送时效
- [InPost UK: Yodel by InPost help](https://inpost.co.uk/yodel-help) – Yodel 收购、单号格式、取件、满柜处理、Deliver to Locker、上门派送、次工作日再派、退款与电话
- [InPost UK: Track to the Future](https://inpost.co.uk/blog/tracking-explained) – 官网物流状态标题及其写法
- [InPost: Who we are](https://inpost.eu/who-we-are) – 波兰起源与卢森堡注册信息
- [InPost: Home](https://inpost.eu/) – 服务国家列表
- [InPost: Regulamin Konta InPost](https://terms.inpost-group.com/pl/regulamin) – InPost Mobile、Paczkomat 延长取件期、改派服务、波兰表单、AI 聊天、电话、地址与 AGCOM
- [UKE: Rejestr operatorów pocztowych, InPost Sp. z o.o.](https://bip.uke.gov.pl/rop/rejestr-operatorow-pocztowych/?p=2&page=1&rejestr=B-00562) – 注册邮政运营商及克拉科夫总部
- [UKE: Reklamacje usług pocztowych](https://cik.uke.gov.pl/uslugi-pocztowe/dochodzenie-roszczen/reklamacje/) – 波兰投诉规则：申诉主体、12 个月期限、30 天答复及逾期视为接受
- [UKE: Reklamacje i odszkodowania](https://cik.uke.gov.pl/uslugi-pocztowe/pytania-i-odpowiedzi/reklamacje-i-odszkodowania-faq/2.html) – 签收时当场提出明显破损与隐蔽破损 7 天期限
- [Allegro: Regulamin Allegro, Załącznik 16A](https://policies.allegro.com/doc/f86a1a793dc4f5bc4b695b0ff0d54d974badab4169f5aaaddad89585c2c90690) – Allegro Lokalnie 中的 kod nadania 说明
- [InPost Italia: Contatti e assistenza](https://inpost.it/contatti-e-assistenza) – 单号、PIN 码、取件码、Apri a distanza、改派链接、电话与异常处理
- [InPost Italia: Giacenza](https://inpost.it/news-giacenza-come-funziona-con-inpost) – 约 3 天保管期与免费退回
- [InPost Italia: Pacco InPost, tempi di ritiro](https://inpost.it/news-pacco-inpost-quanto-tempo-hai-il-ritiro) – 极少延期说明
- [InPost Portugal: Rastrear Encomenda](https://www.inpost.pt/seguimento-do-envio) – 8 位单号示例
- [InPost Portugal: Como recolher uma encomenda](https://www.inpost.pt/recebe-a-tua-encomenda/como-recolher) – PIN 码、身份证件与 5 或 8 天保管期
- [InPost Portugal: Fala connosco](https://www.inpost.pt/fala-connosco) – 联系寄件人、5 天延误线、网点停用、更改取件点、理赔、电话与服务时间
- [InPost Easy: Parcel form](https://inposteasy.com/en) – Peek 读取的官方查询服务
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Peek 显示的内容、波兰 24 位单号、InPost 英文文案、临时自提柜与转交
:::
