---
title: 日本邮政（Japan Post）快递查询：EMS 单号与状态说明
description: 查询日本邮政（Japan Post）国际包裹与 EMS：JP 结尾单号格式、物流状态说明、目的地国家邮政派送、清关与查件指南。
slug: japan-post-tracking
published: 2026-11-24
updated: 2026-11-24
---

日本邮政（日本郵便，Japan Post Co., Ltd.）是日本邮政控股集团旗下的全国邮政公司，截至 2026 年 3 月 31 日共设有 24,115 家邮局。它向海外寄送 EMS、国际航空小包（International Air Packet）与国际包裹，在日本境内则运营 Yu-Pack 包裹。你可以在日本邮政的 [Track & Trace 查询服务](https://trackings.post.japanpost.jp/services/srv/search/input?locale=en)一次查最多 10 个单号，或直接把单号粘贴到上方查询框。在日本网店购物？请向卖家索要运单号。

## 日本邮政运单号格式

从日本寄出的国际邮件为 13 位字符：两位字母、九位数字加 `JP`，其中第九位数字是校验位。以下单号均为虚构数字：

| 格式（虚构） | 说明 |
| --- | --- |
| `EN482917367JP` | 从日本寄出的 EMS、国际航空小包、国际包裹或挂号信。 |
| `482917364823` | 12 位数字：日本境内件，例如 Yu-Pack 包裹。 |
| `48291736485` | 11 位数字：同样是日本境内件，日本邮政查询页为邮件和包裹标出的长度。 |

国际邮件方面，日本邮政官网仅接受这种 13 位格式，不支持 `CNG` 或 `LP` 加 14 位数字之类的编码。小型包装物或其海关申报单（CN22）上以 `U` 开头的条码也不是运单号，这类邮件全程无跟踪。以其他字母结尾（例如 `US`）的单号来自其他国家邮政。找不到单号？请看[如何查找运单号](guide:find-tracking-number)（英文）。

## 日本邮政物流状态说明

日本邮政英文查询页按时间从早到晚排列记录，标明日期、时间、邮局及都道府县或国家。Peek 识别的表述大致按顺序如下：

| 日本邮政显示 | 中文含义 |
| --- | --- |
| `Posting/Collection` | 寄件人已交寄，或日本邮政已揽收。 |
| `En route` | 正在日本邮政网点之间转运。 |
| `Arrival at outward office of exchange` | 已到达负责发往境外的国际互换局。 |
| `Dispatch from outward office of exchange` | 已离开日本（进境邮件则表示离开寄出地国家）。 |
| `Arrival at inward office of exchange` | 已抵达目的地国家进口互换局。 |
| `Item presented to import Customs`、`In Customs` | 正在目的地国海关办理清关。 |
| `Item held at inward Office of Exchange` | 滞留在目的地进口互换局。 |
| `Item returned from import Customs` | 海关已将其交回邮政，恢复转运。 |
| `Departure from inward office of exchange` | 离开进口互换局，发往派送网点。 |
| `Item out of sorting centre` | 已离开目的地国分拨中心。 |
| `Arrival`、`Processing at delivery Post Office` | 已到达负责派送的投递邮局。 |
| `Item held at delivery depot/delivery office` | 已在投递局保管；该条目本身不代表你可以直接上门自提。 |
| `Item out for physical delivery` | 正在外出派送。 |
| `Final delivery` | 已投递签收。 |
| `Final delivery - Collected at counter` | 已在日本邮局柜台领取。 |
| `Retention` | 在日本派送未果后存放于邮局，等待自提或等到你预约的重派日期；该状态不区分具体属于哪种。 |
| `Returned to sender` | 正在退回寄件人。 |

寄往日本的进口邮件还会多出三条状态：`Transported in bond` 表示仍在海关监管下转运至负责你地址清关的互换局；`Arrived in bond` 表示已抵达该互换局；在出现 `Dispatched from Exchange Office` 之前，清关仍在进行。更多见[物流状态含义](guide:tracking-statuses)（英文）。

## 日本邮政海外包裹：谁负责派送与清关

出现 `Dispatch from outward office of exchange` 后，由目的地国家邮政接手。对于 EMS，日本邮政建议直接到对方国家邮政官网查询（日本邮政称在那里查询更快），例如美国的 [USPS](carrier:usps)、英国的 Parcelforce、爱尔兰的 An Post、加拿大的 Canada Post、澳大利亚的 [Australia Post](carrier:australia-post) 和新西兰的 [NZ Post](carrier:nz-post)，继续使用同一个 `JP` 单号即可。派送尝试和自提均按当地邮政规则执行，参见[错过派送怎么办](guide:missed-delivery)（英文）。

**显示已签收。** 国际航空小包若无需缴税，会直接投入信箱、无需签收，并显示已签收。日本邮政还提醒，寄往英国或法国的 EMS 若未投递成功，在就近邮局等待自提期间也可能显示已签收；寄往德国的 EMS 在由清关代理或其他快递公司处理时，可能显示为退回或已签收。由于实际由当地邮政投递，请先联系当地邮政；详见[显示已签收但未收到](guide:delivered-not-received)（英文）。

**美国规则。** 自 2026 年 10 月 15 日起，日本邮政几乎所有邮局均重新受理寄往美国的邮件。货值不超过 2,500 美元的商品须先通过日本邮政推荐的 Zonos 或 SafePackage 登记并预缴应付的关税和税费，面单上会标注 DDP 及 13 位申报编号。在 2027 年 1 月 19 日前，私人间不超过 100 美元的礼品可免除此步骤。超过 2,500 美元的物品由进口方在美国缴纳。

**欧盟清关。** 日本位于欧盟之外，因此包裹要在你所在的国家清关。自 2026 年 7 月 1 日起，从欧盟以外网购、订单货值不超过 150 欧元的商品按每类商品征收 3 欧元欧盟关税；自 2026 年 11 月 1 日起，不论货值高低，还按每类商品征收 2 欧元欧盟处理费。所谓“每类商品”指包裹中具有相同税则归类、品名及（如要求）原产地的货品。其他国家按当地规定执行，见[包裹清关指南](guide:customs)（英文）。

## 在日本境内接收日本邮政包裹

**海关。** 境外寄入日本的邮件会在日本六大国际互换局之一办理清关。如须缴税，你可以在送货上门时付款，或凭日本邮政送达的缴税通知单到邮局支付。货值超过 200,000 日元的物品须办理进口报关。

**派送失败。** 境外包裹在投递邮局保留 15 天（如涉及关税则保留一个月），期间可前去自提或预约重派。日本邮政英文重派表单接受通知单上的 6 位或 11 至 13 位单号，但仅支持重派到家庭住址；如需改派其他地点，请致电 0570-046-111。参见[未及时自提包裹](guide:uncollected-parcel)（英文）。

## 日本邮政物流停滞、丢失或退回怎么办

**离开日本后无更新。** 离境后的扫描由目的地国家邮政回传。日本邮政提醒，海外回传信息可能不完整或延迟，且因时差可能导致条目顺序颠倒。请用同一单号在当地邮政官网查询；另见[为什么物流停止更新](guide:tracking-not-updating)（英文）。

**完全查不到轨迹。** 普通小型包装物（Small Packet）不带跟踪，而且自 2025 年 12 月 31 日起，日本邮政不再受理货物类小型包装物的挂号服务。需要跟踪的小包须走国际航空小包（International Air Packet）；海运/水陆路仅国际包裹提供跟踪。

**丢失或破损。** 须由寄件人到原交寄邮局填表申请查件（不可电话办理）：挂号信、保价邮件和国际包裹期限为 6 个月内，EMS 期限取决于目的地国。赔偿覆盖挂号/保价信函、国际包裹和 EMS（免费赔付上限 20,000 日元，保价最高 200 万日元）；国际航空小包不设赔偿。网购订单请联系卖家，参见[包裹丢失处理](guide:lost-parcel)（英文）。如果收到时破损或短少，请保留全部包装并向当地邮局报案，参见[包裹破损处理](guide:damaged-parcel)（英文）。

**退回寄件人。** 日本邮政提示，海外邮局误录退件信息可能导致签收后仍出现 `Returned to sender`，此时记录里显示的是投递国名而非日本都道府县。此外，日本邮政呼吁寄件人不要向奥地利、比利时、丹麦、芬兰、法国、德国、卢森堡或葡萄牙寄送 150 欧元以下的 IOSS 电商订单，因为这些国家的海关或邮政可能会退回。参见[包裹退回寄件人](guide:returned-to-sender)（英文）。

## 日本邮政客服电话与联系方式

以下热线仅限日本境内拨打，无法从海外拨通：

- **客服中心：** 0120-5931-55（免费），手机拨打 0570-046-666（收费）。
- **英语专线：** 0570-046-111（收费），同时受理重派申请。
- **服务时间：** 每天日本时间 8:00 至 21:00。

身在日本境外时，请联系寄件人或商家到原交寄邮局提交查件。日本邮政表示客服能看到的物流信息不会多于官网显示内容。

## 使用 Peek 跟踪日本邮政包裹

当两位字母、九位数字加 `JP` 的单号校验位正确时，[Peek](/) 会识别为日本邮政并显示“识别出的快递公司”，点击“更换”可切换其他快递。若校验位不对，会提示“这个单号的校验位对不上。”，你可以核对发货消息或选择“按输入的单号跟踪”。直接粘贴日本邮政查询链接同样有效。以 `U` 开头的编码不会被识别为日本邮政，Peek 也不会去查——因为日本邮政不跟踪 `U` 开头号码。

12 位日本境内单号光靠格式无法唯一确定为日本邮政，但直接粘贴即可（Peek 跟踪 4,500 多家快递公司）：它会向使用这种格式的快递查询（你在日本时优先问 Yamato 和日本邮政），并在日本邮政确认有记录时显示“Japan Post 有这个包裹的记录。”。粘贴 11 位数字时，Peek 不会向日本邮政查询，请点击“更换”手动选择。归入日本邮政后，在出现首条扫描前会显示“等待 Japan Post 更新”；若两天后日本邮政仍查不到，则会提示“请检查运单号”。

Peek 按日本邮政英文原文显示每条扫描以及邮局和地点，并把上表状态归入“运输中”、“清关中”等阶段，省略可能包含地址不明等原因的 Details 列。Peek 按每条扫描注明的地点（或邻近扫描的地点）当地时间解析时间。对于寄件人指定了派送日的日本境内件，Peek 会在签收或该日过期前展示对应日期和时段作为预计送达时间。

在许多国家，Peek 还会用同一个 `JP` 单号同步查询你所在国家的邮政：美国查 USPS、英国查 [Royal Mail](carrier:royal-mail)（`E` 开头单号查日本邮政为 EMS 指定的 Parcelforce）、爱尔兰查 An Post、加拿大查 Canada Post、澳大利亚查 Australia Post、新西兰查 NZ Post、德国查 [DHL](carrier:dhl)、法国查 [La Poste](carrier:la-poste) 以及除荷兰外的全部其他欧盟国家邮政；一旦当地邮政出现更新扫描，Peek 便切换至该邮政跟进并保留日本邮政原轨迹。在韩国、中国内地、香港或新加坡查询时，Peek 不会再查询其他邮政。反之，当外国邮政轨迹标明目的地为日本（或未标明目的地而你在日本查询）时，Peek 也会拿该外国单号同步向日本邮政查询（`U` 开头除外）。

在你打开包裹页面时，或在派送当天开启了通知时，Peek 最高每 10 分钟检查一次日本邮政；在包裹派送当天，只要日本邮政自己的查询系统有响应，自最新扫描所在地早上 6 点起（日本境内按日本时间；若寄件人指定了时段，则从该时段开始前半小时起）最高每 2 分钟检查一次；一旦你的本国邮政接手，则改按本国邮政的节奏检查。其余时候以及连续两天无新动态后（若仍停留在“已生成面单”或“已揽收”，则为 12 小时后）每小时检查一次。通过物流查询服务时，每小时检查一次，无更新满一天后每 3 小时一次，在派送当天且满足同样条件时最高每 15 分钟检查一次。通知可选“每次扫描”、“重要环节”或“仅派送当天”。Peek 无法代你联系日本邮政、你所在国邮政或商家。

## 日本邮政快递查询常见问题

### 日本邮政 EMS 是什么服务？

EMS 是日本邮政最快的国际快递服务，可向全球 120 多个国家和地区寄送最高 30 公斤的文件与物品，提供跟踪，单号以 `JP` 结尾。

### 为什么我的日本邮政单号一直不更新？

如果最新一条是 `Dispatch from outward office of exchange`，说明包裹已经离开日本：请用同一个单号去你所在国家的邮政官网查询，并预留航班和清关时间。

### 日本邮政小型包装物（Small Packet）能跟踪吗？

只有选择国际航空小包（International Air Packet）才带跟踪。普通 Small Packet 没有跟踪，上面以 `U` 开头的条码也不是运单号，请向寄件人确认具体寄送方式。

### 日本邮政寄送要多长时间？

日本邮政表示国际航空小包大约需要 5 至 21 天；EMS 是其最快服务，具体到各国的参考天数可在 post.japanpost.jp 的运费与天数查询工具查看。

:::sources
- [Japan Post: Track & Trace Service](https://trackings.post.japanpost.jp/services/srv/search/input?locale=en) – 查询工具与单号示例
- [Japan Post: Item numbers on labels](https://trackings.post.japanpost.jp/slipEn.html) – Yu-Pack 12 位单号说明
- [Japan Post: Notices Concerning EMS Track & Trace Service](https://www.post.japanpost.jp/service/send/oversea/information/ems_search_en.html) – 单号、扫描顺序、海外扫描、Details 列、退件与保税转运状态
- [Japan Post: Link to Overseas Post Offices](https://www.post.japanpost.jp/service/send/oversea/list/delivery/ems/link_en.html) – 各国 EMS 派送邮政链接
- [Japan Post: Products and services comparative table](https://www.post.japanpost.jp/service/send/oversea/list/hikaku_en.html) – 各服务跟踪支持对比
- [Japan Post: International Air Packet](https://www.post.japanpost.jp/service/send/oversea/list/delivery/airpacket_en.html) – 信箱投递与参考时效
- [Japan Post: EMS](https://www.post.japanpost.jp/service/send/oversea/list/delivery/ems/index_en.html) – EMS 服务与天数查询工具
- [Japan Post: EMS Insurance Program](https://www.post.japanpost.jp/service/send/oversea/list/delivery/ems/damage_en.html) – 免费赔付与保价上限
- [Japan Post: Mail destined for the United States, 23 October 2026](https://www.post.japanpost.jp/service/send/oversea/information/2026/1023_01_en.html) – 寄美规则、Zonos/SafePackage 与 100 美元礼品过渡期
- [Japan Post: EU duty-free exemption removal, 1 July 2026](https://www.post.japanpost.jp/service/send/oversea/information/2026/0701_01_en.html) – 欧盟 150 欧元以下 IOSS 订单提醒
- [Japan Post: Searches for undelivered items](https://www.post.japanpost.jp/question/oversea/18_en.html) – 邮局查件申请与 6 个月期限
- [Japan Post: Searches for EMS](https://www.post.japanpost.jp/question/oversea/138_en.html) – EMS 查件期限
- [Japan Post: Compensation](https://www.post.japanpost.jp/question/oversea/21_en.html) – 赔偿涵盖的邮件种类
- [Japan Post: Compensation claims](https://www.post.japanpost.jp/question/oversea/17_en.html) – 破损或短少报案说明
- [Japan Post: Holding period for parcels from overseas](https://www.post.japanpost.jp/question/oversea/23_en.html) – 境外来件 15 天与 1 个月保管期
- [Japan Post: Delivery Flow](https://www.post.japanpost.jp/service/send/oversea/use/flow/receive_en.html) – 日本进口清关流程与 20 万日元门槛
- [Japan Post: Application for redelivery](https://www.post.japanpost.jp/receive/redelivery_form/) – 英文重派申请表
- [Japan Post: Yu-Pack](https://www.post.japanpost.jp/service/you_pack/index_en.html) – 指定派送日期与时段
- [Japan Post: Inquiry by Phone](https://www.post.japanpost.jp/question/oversea/tel_en.html) – 客服热线与服务时间
- [Japan Post: Corporate Information](https://www.post.japanpost.jp/about/profile_en.html) – 公司概况与 24,115 家邮局
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 3 欧元关税与 2 欧元欧盟处理费
- [European Commission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 按每类商品征收及定义
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – 扫描表述、Peek 读取的内容以及接下来查询哪家邮政
:::
