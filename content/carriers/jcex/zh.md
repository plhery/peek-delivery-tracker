---
title: JCEX（佳成国际）物流查询：JCZ 单号、状态与派送
description: JCEX（佳成国际）物流查询：JCZ 运单号怎么查，各条物流状态什么意思，到了美国、法国等地由谁派送，显示已签收却没收到时该找谁。
slug: jcex-wuliu-chaxun
published: 2026-11-03
updated: 2026-11-03
---

JCEX（佳成国际，英文名 Jiacheng International Logistics）是总部在浙江杭州的物流公司，替网店卖家把包裹从中国寄往海外，到了你所在的国家，再交给当地的邮政或快递公司完成最后一段派送：比如在美国交给 USPS，在法国交给 Colissimo。你可以在 JCEX 的查询页面 [track.jcex.com](https://track.jcex.com/) 查询，也可以把单号粘贴到上方的查询框。

## JCEX 运单号长什么样

JCEX 没有公布单号格式。它的运单号以 JCZ 开头、以 DH 结尾。下表的数字为虚构：

| 单号 | 说明 |
| --- | --- |
| `JCZ0000000000DH` | JCZ 加 10 位数字再加 DH：JCEX 运单号。 |
| `JCZ000000000DH` | 只有 9 位数字：单号抄错了，JCEX 的单号不是这样。 |

在 Peek 里，大小写和空格都不影响查询。JCEX 的查询页面一次最多能查 100 个单号，用逗号隔开。

**单号在哪里找。** 包裹是卖家交给 JCEX 寄出的，单号在卖家的发货邮件或订单页面里；找不到就问卖家。

## JCEX 物流状态是什么意思

JCEX 的查询页面只有中文界面，每一行依次是 UTC 偏移、时间、地点和描述。JCEX 给每一行存了英文和中文两种描述：查询页面显示中文的，Peek 优先显示英文的，没有英文才显示中文，所以同一行在两边的文字可能不同。Peek 里的一行可能是英文、中文，或中英文混写。下面是常见的几行，按包裹通常经过的顺序排列：

| 记录原文 | 是什么意思 |
| --- | --- |
| `Shipment order info received` | 卖家把订单信息发给了 JCEX。JCEX 还没收到包裹。 |
| `Arrive at the delivery point` | JCEX 在中国的仓库收到了包裹。不是你附近的派送点。 |
| `Item outbound in sorting center` | 包裹离开了 JCEX 的分拣中心。 |
| `Departed from origin facility to airport` | 包裹正在送往机场。 |
| `Flight dispatched（航班已飞）` | 航班已起飞。 |
| `Flight arrived（航班已落地）` | 航班已在目的国落地。 |
| `Customs held（清关中）` | 正在办理进口清关，不一定是出了问题。 |
| `Clearance processing complete（清关完成）` | 清关完成。 |
| `Handed over to the trucking company, waiting for transport（已交卡车公司 待运输）` | 已交给卡车公司，等着运往派送公司。 |
| `Delivered to warehouse, waiting to be scanned（已交仓，待扫描）` | 已交到派送公司的仓库，等对方扫描。包裹还没到你手里。 |
| `CONSIGNMENT RECEIVED BY OVERSEAS FORWARDER` | 派送公司收到了包裹。 |
| `out for delivery` | 正在派送。 |
| `Delivered` | 已经送到你手里。 |

其他常见的行：`The order information has been sent` 和 `货物电子信息已经收到` 与第一行意思相同；`到达收货点(包裹已入库)` 是 JCEX 在中国的收货点收下了包裹，`货物离开操作中心(包裹已出库)` 是包裹离开了 JCEX 的操作中心，`China, Departure from the starting port` 是从中国起运。`under clearance` 是进口清关中，`CUSTOMS CLEARANCE COMPLETE` 是清关完成。`InTransit_Arrival` 和 `InTransit_Departure` 是到达和离开香港的中转站。航班落地后，`COURIER DISPATCH INFORMATION RECEIVED（收到提货通知）` 和 `Packages have been picked up （包裹已陆续提取）` 表示卡车公司接到通知、提走了这一航班的包裹，`turck transferring（卡车运输中）`（turck 是 JCEX 原文的拼写）是包裹在卡车上。交接之后是派送公司自己的记录：USPS 的是英文，比如 `ARRIVAL AT POST OFFICE`（负责派送的邮局收到了包裹）；Colissimo 的是法文。

在这些记录上方，JCEX 的查询页面还会给每个包裹一个汇总标签：查询不到、收到信息（等待运输商揽收）、运输中、到达待取（已到目的地的投递点）、派送途中、投递失败、成功签收（单个包裹上显示“已签收”）、可能异常或运输过久（运输了很长时间仍未投递成功）。

最容易看错的是签收：JCEX 把包裹交到派送公司仓库这一步也记作签收，所以它的页面可能已经标着“已签收”，包裹其实还在派送公司手里。JCEX 还会把这类交接记录放在最上面：请按时间看最新的几行，别只看最上面一行。

## JCEX 的包裹由谁派送

JCEX 自己不在你所在的国家派送。按它的产品页面，国际小包（2 公斤以内，空运）由邮政派送，国际快递（0.5–20 公斤）由邮政或商业快递派送。从 JCEX 的物流记录看，美国的包裹由 USPS 派送，法国的由 Colissimo（法国邮政 La Poste 的包裹业务）派送。

JCEX 没有公布派送几次、取件通知、自提点或包裹保留多久的规定：这些按派送公司的规定办，派送公司做了什么，会写在 JCEX 的记录里。比如在一条法国的记录里，Colissimo 写的是 `Votre colis n'a pas pu vous être remis. Il sera mis en livraison demain (hors dimanche et jours fériés).`，意思是包裹没能交到你手里，第二天（周日和节假日除外）再派送。需要改约派送时，请向卖家要派送公司自己的单号。

## JCEX 包裹没到或物流不更新怎么办

**该找谁。** JCEX 的客户是交给它寄件的卖家。JCEX 没有公布收件人索赔的途径，所以包裹延误、丢失、损坏或被退回时，请联系卖家或你下单的平台。

**查不到物流。** 新的运单号在 JCEX 扫描包裹之前，查询页面会显示“查询不到”，下面写着“暂无轨迹详情”；单号输错时也是这样。如果一直如此，请找卖家核对单号。

**物流不更新。** JCEX 没说包裹运输多久算“运输过久”。如果很久没有新记录，请问卖家：包裹是卖家寄的，卖家可以去问 JCEX。从中国寄出的包裹为什么常常停很久，见 [中国包裹跟踪指南](guide:tracking-from-china)（英文）。包裹停在清关环节时，要交哪些费用、怎么办，见 [海关扣留包裹指南](guide:customs)（英文）。

**显示签收却没收到。** 先看签收的那一行是派送公司写的 `Delivered`，还是 JCEX 的交接记录（见上文）。如果确实是派送公司写的，请联系派送公司或卖家。

## JCEX 客服电话和联系方式

截至 2026 年 10 月 23 日，JCEX 官网公布的联系方式都在中国：

- **客服热线**：400-887-1083，北京时间周一至周五 09:00–18:00，周末及公共假期休息。
- **邮箱**：cs@jcex.com.cn，官网页脚称之为投诉邮箱。
- **微信**：官网页脚列有“微信客服”，服务时间相同。
- **总公司地址**：浙江省杭州市上城区九和路 520 号铁汇发展中心 T4 幢 8 楼。

联系页面还列出了 JCEX 在中国各地的分公司，它们服务的是卖家，页面上没有给海外收件人的联系方式。订单的问题请找卖家，最后一段派送的问题请找派送公司。

## 用 Peek 跟踪 JCEX 包裹

[Peek](/) 看到 `JCZ…DH` 格式的单号，会马上认出 JCEX，查询框里显示“识别出的快递公司”：Peek 收录的快递公司里，没有别家用这种单号。带 `?nums=` 和单号的 track.jcex.com 查询链接也可以直接粘贴。Peek 只跟踪 JCEX 的 JCZ 运单号：如果你手里只有订单号，请向卖家要运单号。如果你用“更换”给别的单号选了 JCEX，Peek 会提示它“不使用这种格式的单号”。

Peek 读取的是 JCEX 自己的物流数据，显示状态、物流记录和每次扫描的地点、目的国，签收后还显示签收时间。每一行都按原文显示，只有一字不差的 `Delivered` 在 Peek 里显示为“已签收”。`Arrive at the delivery point` 和交到派送公司仓库的交接记录在 Peek 里算作“运输中”，既不算“待取件”，也不算“已签收”。

JCEX 自己的记录用北京时间（UTC+8），派送公司转来的记录多用当地时间。Peek 把每一行换算成你设备所在的时区显示，比如北京时间早上 09:00 的扫描，在北美会显示为前一天的下午或晚上。

JCEX 收下包裹后，有时会提前写好一串直到交给派送公司的记录。这些提前写好的行和日期晚于查询时间的行，Peek 都不算作扫描，也不显示；JCEX 重复列出的同一次扫描，Peek 只显示一次。所以 Peek 的物流记录可能比 JCEX 页面上少几行，并且按时间先后排列。

JCEX 的数据没有单独标出派送公司；JCEX 查询页面有时会在运单号旁边显示一个“跟踪单号”，Peek 也不读取它。所以 Peek 一直用 JCEX 运单号跟踪到底，不会转去 USPS 或 Colissimo：它们的扫描本来就出现在 JCEX 的记录里。Peek 不显示预计送达日期、服务类型、签收证明和派送公司自己的单号。

JCEX 还查不到这个单号时，Peek 显示“等待快递公司更新”；JCEX 收到卖家的订单信息后，显示“已生成面单”。Peek 每 10 分钟查询一次，包裹派送中时每 2 分钟一次。两天没有新记录（状态还是“已生成面单”或“已揽收”时为 12 小时），Peek 就改为每小时查询一次。“通知范围”可以选“每次扫描”“重要环节”或“仅派送当天”。Peek 不能替你联系 JCEX 或卖家。

## 关于 JCEX 物流查询的常见问题

### JCEX（佳成国际）是什么快递，正规吗？

是正规的物流公司。JCEX 是佳成国际（Jiacheng International Logistics）的品牌，2000 年成立，总部在杭州。2021 年起，在上海证券交易所上市的华贸物流持有它的股份。它说自己是阿里巴巴国际站、菜鸟网络、敦煌网等多家平台指定的国际物流供应商。

### JCEX 显示“Arrive at the delivery point”是什么意思？

意思是 JCEX 在中国的仓库收到了包裹。这一行出现在物流记录的开头、航班起飞之前，和你附近的派送点或自提点没有关系。

### JCEX 显示已签收，为什么我没收到包裹？

JCEX 把交到派送公司仓库这一步也记作签收：`Delivered to warehouse, waiting to be scanned（已交仓，待扫描）` 这一行表示当地的派送公司接手了包裹。请等派送公司自己的记录，直到出现 `Delivered`。

### JCEX 的包裹在美国由谁派送？

从 JCEX 的美国物流记录看，是 USPS。USPS 的记录（比如 `ARRIVAL AT POST OFFICE` 和 `out for delivery`）会转到 JCEX 的物流记录里。

### JCEX 多久能送到？

JCEX 告诉卖家，它的国际小包寄到美国要 13 到 16 个工作日，寄到英国 8 到 11 个工作日，寄到欧洲 9 到 13 个工作日。你的订单以商家给的预计时间为准。

:::sources
- [JCEX：物流查询](https://track.jcex.com/) – 只有中文界面，一次最多 100 个单号，运单号和跟踪单号，汇总标签及说明，每行的 UTC 偏移、时间、地点和中文描述，“暂无轨迹详情”
- [JCEX：联系我们](https://www.jcex.com/bangzhuzhongxin/lianxiwomen/index.html) – 客服热线、邮箱、北京时间的服务时间、总公司地址、国内分公司
- [佳成国际物流：首页](https://www.jcex.com/) – 投诉邮箱、微信客服、服务时间、华贸物流参股
- [JCEX：佳成介绍](https://www.jcex.com/guanyujiacheng/jiachengjieshao/index.html) – 2000 年成立，为跨境电商提供物流方案
- [JCEX：英文网站](https://enjc.jcex.com/) – Jiacheng International Logistics Co., Ltd，阿里巴巴国际站、菜鸟网络、敦煌网等平台的指定国际物流供应商
- [JCEX：国际小包](https://www.jcex.com/chanpinyufuwu/guojixiaobaoyukuaidi/index.html) – 2 公斤以内、空运、邮政派送、各地时效
- [JCEX：国际快递与快运](https://www.jcex.com/chanpinyufuwu/guojikuaiyun/1438101270410715138.html) – 国际快递 0.5–20 公斤，由邮政或商业快递派送
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Peek 显示的内容、单号格式、扫描记录原文、交接记作签收、北京时间、提前写好的记录、派送公司
:::
