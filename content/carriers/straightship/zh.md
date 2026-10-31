---
title: Straightship（迅联捷航）物流查询：STRTD 单号、状态与派送
description: Straightship（迅联捷航）物流查询：STRTD、STRCA、STSH 单号怎么查，各物流状态什么意思，派送失败、延误或退回时怎么办，以及客服电话和邮箱。
slug: straightship-wuliu-chaxun
published: 2026-11-03
updated: 2026-11-03
---

Straightship（中文名迅联捷航，公司全称 Straightship Solutions Limited）是一家总部在加拿大不列颠哥伦比亚省本拿比（Burnaby）的物流公司，把货物从亚洲运到北美，并用自己的司机在加拿大各地派送包裹。它的官网把 SHEIN 列为合作伙伴之一。如果网购包裹的快递单号以 `STRTD` 或 `STRCA` 开头，就是 Straightship 在送；以 `STSH` 开头的也可能是它。你可以在 [Straightship 中文官网](https://www.straightship.com/new/cn/)首页的“跟踪您的货物”一栏输入单号查询，也可以把单号粘贴到上方的查询框。单号在商家的发货邮件或订单页面里。

## Straightship 快递单号长什么样

Straightship 没有公布单号格式。Peek 认得的单号有以下几种（数字为虚构）：

| 单号 | 说明 |
| --- | --- |
| `STRTD000000000` | STRTD 加 9 位数字。 |
| `STRTD0000000000000000` | STRTD 加 16 位数字（也有 17 位的）。 |
| `STRCA0000000000000` | STRCA 加 13 位数字。 |
| `STSHYW00000000000` | STSH 加两个字母和 11 位数字。 |

在 Peek 里，大小写和空格都不影响查询。Straightship 的查询页面查不到某个单号时，会显示 `No results found!`：请对照商家的邮件核对单号。SHEIN 订单的单号在哪里、包裹怎么跟踪，见 [SHEIN 订单跟踪指南](guide:shein-orders)（英文）。

## Straightship 物流状态是什么意思

Straightship 的查询页面是英文的。上方是摘要，其中一栏是“Package Status”（包裹状态）；下方是物流记录，每条有时间、状态和所在城市。常见的几条，按包裹通常经过的顺序排列：

| Straightship 的原文 | 是什么意思 |
| --- | --- |
| `Package information is created in the system` | 商家已经生成面单。Straightship 还没拿到包裹：它还在卖家那里，或者正从亚洲运过来。 |
| `Package Received with Origin Scan warehouse` | Straightship 在加拿大的第一次扫描，后面附有城市和省份。 |
| `Package is received at a Straightship station` | 包裹到了 Straightship 的一个网点。较早的记录写作 `Arrived at Facility`。 |
| `Package arrived at transit station` | 到了中转站，正运往离你最近的网点。 |
| `Loading` | 派送前在网点装车，有时几秒内出现两次。 |
| `Package will be delivered before 9:00pm` | 今天派送。当天会送达；送不成的话，会记一次派送失败。 |
| `Attempted Delivery` | 司机上门了，但没能送达。 |
| `Shipment delayed` | 包裹延误了。这一行不写原因。 |
| `Delivered` | 已送达，后面附有城市和省份。 |
| `Package is being returned to shipper` | 包裹正在退回卖家。 |

有两条派送失败的记录会直接告诉你该怎么做。`An attempt was made to deliver your package-consignee closed (please e-mail SS@straightship.com to make arrangement)` 表示收件地点当时关门；另一条把 `consignee closed` 换成了 `unable to leave package at safe location`，表示司机找不到能安全放包裹的地方。两条都请你发邮件给 Straightship，约下一次派送。

三个容易看错的地方：

- **摘要更新得慢。** 包裹已经在网点之间运输了，“Package Status”还可能停在 `ReadyForShipping`（或 `Ready For Shipping`）。请看下方的物流记录，以最新一条为准。摘要之后可能变成 `Shipped`、`Delivered` 或 `ReturnedToSender`。
- **“in progress”不是派送中。** `Delivery is in progress` 和 `Your order is in Progress` 出现在一次没送成的派送之后，有时隔了好几天，随后包裹被退回。看到这两行，请发邮件给 Straightship。
- **时间是 UTC。** 查询页面在物流记录下方注明，所有时间都是协调世界时（UTC）。所以加拿大晚上的扫描，在网站上会显示成第二天的日期。

退回途中的包裹仍可能在网点被扫描，然后重新派送：`Package is being returned to shipper` 不一定是最后一条记录。

## Straightship 在加拿大怎么派送

Straightship 的查询页面只针对加拿大：标题是“Canada B2C Shipment Tracking”。Straightship 说，它的自营派送网络覆盖加拿大 95% 的人口，有 85 个以上的派送站点；派送当天的记录写明 21:00 前送达。

对从美国运进加拿大的货物（中文官网叫“美转加跨境集运”），Straightship 提供几种尾程派送方式：自营网络、加拿大邮政（Canada Post）或 UPS，英文页面另写了 Purolator，中文页面写的则是 FedEx。用哪一家要看具体货件。它的查询页面只显示 Straightship 自己的网点，不写其他快递公司，也不给第二个单号。

Straightship 没有公布会上门几次、会不会留取件通知单、没送达的包裹在网点放多久。它写明的有：

- **派送失败后**：按记录里的要求，发邮件到 SS@straightship.com，写上你的快递单号。
- **要改地址或电话**：用它的联系表单，联系原因选“地址和/或联系方式更新”。
- **送达照片**：中文官网的查询栏写着可以查看最新包裹交付状态“和POD图像 (如有)”，POD 图像就是送达时拍的照片。

## 从亚洲寄来的 Straightship 包裹要交关税吗

Straightship 为用它发货的企业办理清关，它的页面没有说会不会向收件人收费。如果有人要你为 Straightship 的包裹付钱，先问卖家。

快递寄来的包裹，加拿大边境服务局（CBSA）允许快递公司代你申报，并代缴关税和税款。按 CBSA 的说法，快递公司会在派送前联系你，安排缴纳关税和税款，在你付清之前由快递公司扣着包裹；快递公司收的清关手续费和关税、税款是两回事，不归 CBSA 管。CBSA 从不通过电话、短信或电子邮件主动索要你的社会保险号（SIN）和信用卡号：收到这样的消息就是诈骗。要交多少、免税额和退税，见 [海关扣留包裹指南](guide:customs)（英文）。

## Straightship 包裹没到、丢了或被退回怎么办

**该找谁。** Straightship 的客户是用它发货的卖家和平台，它没有公布收件人索赔的途径。要退款或补发，请找商家；SHEIN 的订单就找 SHEIN。

**还没到加拿大。** 只有 `Package information is created in the system`、后面再没有记录，说明包裹还没到 Straightship 在加拿大的网点，可能还在从亚洲运来的路上。问问卖家是什么时候发的货。

**在加拿大不更新了。** 网点扫描之后好几天没有动静，或者出现了 `Shipment delayed`，请用 Straightship 的联系表单，联系原因选“交货状态查询”。

**显示已送达，但没收到。** 先在 Straightship 的查询页面看有没有送达照片（不一定有），再问问邻居，看看楼里的收发室和门口附近能放包裹的地方。然后在联系表单里选“未收到已交付订单”，同时告诉卖家。

**被退回。** `Package is being returned to shipper` 表示包裹正在退回卖家。请卖家退款或重新发货。

## Straightship 客服电话和联系方式

截至 2026 年 10 月 31 日，Straightship 公布的联系方式：

- **电话**：+1 (800) 452-3304，加拿大东部时间周一至周五 08:00–21:00，周六 08:00–16:00。
- **邮箱**：SS@straightship.com，也就是派送失败记录里给出的地址。
- **联系表单**：在它的[中文联系页面](https://www.straightship.com/new/cn/contact.html)，运单号必填。Straightship 说会在两个工作日内回复。

订单本身、退款和退货，请联系卖家。

## 用 Peek 跟踪 Straightship 包裹

[Peek](/) 看到上面那几种 `STRTD` 或 `STRCA` 单号，会马上认出 Straightship，查询框里显示“识别出的快递公司”：Peek 收录的快递公司里，没有别家用这种单号。`STSH` 单号就不一定了，有些也符合 Colis Privé 的单号格式：Peek 会先问 Straightship 有没有这个包裹（显示“正在查询物流服务…”），有的话就选它（显示“查到了这个包裹”）。如果没出现 Straightship，点“更换”，在“符合这个单号”下面选它。straightship.com 的查询链接（`/track-your-shipment/tracking-id/` 加单号）也可以直接粘贴。Peek 只按这几种格式向 Straightship 查询：如果你用“更换”给别的单号选了 Straightship，Peek 会提示它“不使用这种格式的单号”。其他格式的 Straightship 单号，请到 Straightship 官网查。

Peek 读取的是 Straightship 自己的查询页面，显示状态、物流记录（含每次扫描的城市和省份）和送达时间。页面注明时间是 UTC，Peek 就按 UTC 读取，再换算成你设备所在的时区显示。`Package will be delivered before 9:00pm` 算作“派送中”，`Attempted Delivery` 和上面两条派送失败记录算作“派送未成功”，`Delivery is in progress` 算作“运输中”；`Delivered` 这一行在 Peek 里显示为“已签收”。Straightship 不提供预计送达日期，Peek 也就不显示；客户参考号和送达照片 Peek 不显示，请到 Straightship 的查询页面看。Straightship 不写其他快递公司，所以 Peek 一直用 Straightship 的单号跟踪到底。

Straightship 还查不到这个单号时，Peek 显示“等待快递公司更新”；有了面单信息，就显示“已生成面单”。Peek 全天候每 10 分钟查询一次，包裹派送中时每 2 分钟一次。“通知范围”可以选“每次扫描”“重要环节”或“仅派送当天”。Peek 不能替你联系 Straightship 或卖家。

## 关于 Straightship 物流查询的常见问题

### Straightship 是什么快递，正规吗？

是正规的物流公司。Straightship Solutions Limited 位于不列颠哥伦比亚省本拿比，中文名迅联捷航，把货物从亚洲运到北美，并在加拿大自己派送。官网列出的合作伙伴有 SHEIN、加拿大邮政、FedEx、中远海运（COSCO）和圆通（YTO），还说它有 85 个以上的派送站点。

### “Package will be delivered before 9:00pm”是什么意思？

包裹今天派送，司机当天会上门。如果没人能收，接着会出现一条派送失败记录，请你发邮件到 SS@straightship.com 约下一次派送。

### Straightship 为什么一直显示 ReadyForShipping？

包裹在 Straightship 的网点之间运输时，摘要里的“Package Status”可能一直停在 `ReadyForShipping`。请以下方物流记录的最新一条为准。

### Straightship 多久能送到？

Straightship 没有公布从亚洲寄来的包裹要多久。请以商家给你的订单预计时间为准。一旦出现 `Package will be delivered before 9:00pm`，司机当天就会上门。

### Straightship 的包裹由谁派送？

从查询页面看，是 Straightship 自己的司机，从它在加拿大各地的网点出发：页面上不写别的快递公司。从美国运来的货物，Straightship 还列出加拿大邮政和 UPS 做尾程派送，英文页面另写了 Purolator，中文页面写的是 FedEx。

:::sources
- [Straightship：首页（英文）](https://www.straightship.com/new/) – 从亚洲到北美的门到门运输、自有车队、查询框、电话、邮箱、服务时间、合作伙伴
- [迅联捷航（Straightship）：中文首页](https://www.straightship.com/new/cn/) – 中文名、“跟踪您的货物”查询框、“和POD图像 (如有)”、加拿大东部时间的服务时间
- [Straightship：服务（英文）](https://www.straightship.com/new/services.html) – 自营网络覆盖加拿大 95% 的人口；美转加服务的尾程派送：自营网络、Canada Post、Purolator 或 UPS；为企业清关
- [迅联捷航：服务](https://www.straightship.com/new/cn/services.html) – 美转加跨境集运，尾程派送：自营、加邮、FedEx 或 UPS
- [Straightship：资源（英文）](https://www.straightship.com/new/resources.html) – 85 个以上的派送站点
- [迅联捷航：联系我们](https://www.straightship.com/new/cn/contact.html) – 联系原因、运单号必填、两个工作日内回复、电话、邮箱
- [Straightship：隐私政策（英文）](https://www.straightship.com/new/privacy.html) – 公司全称 Straightship Solutions Limited，位于本拿比
- [加拿大边境服务局（CBSA）：Importing casual goods by courier](https://www.cbsa-asfc.gc.ca/import/courier/lvs-efv/prsn-eng.html) – 快递公司代为申报、代缴关税和税款；清关手续费不归 CBSA 管
- [加拿大边境服务局（CBSA）：Protect yourself against fraud](https://www.cbsa-asfc.gc.ca/security-securite/pyaf-pvcf-eng.html) – 快递公司在派送前联系收件人付款，付清前扣着包裹；CBSA 不会主动索要 SIN 和信用卡号
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Peek 显示的内容、单号格式、扫描记录原文、UTC 说明、摘要状态值、查询页面标题和 No results found!
:::
