---
title: 今日通（JRT）物流查询：JRT…MM单号、航班轨迹与尾程
description: 查询厦门今日通（JRT）跨境包裹：JRT…MM单号格式、空运陆运航班与清关状态含义、欧美墨尾程由DHL、UPS及FedEx派送的衔接与排查。
slug: jinritong-jrt-wuliu-chaxun
published: 2026-11-23
updated: 2026-11-23
---

今日通（JRT，全称厦门今日通商贸有限公司，英文 Jinritong）是一家总部位于福建厦门的中国跨境货运代理公司。如果网购卖家通过今日通发货，通常会提供一个以 `JRT` 开头、以 `MM` 结尾的物流单号——把它粘贴到上方查询框即可追踪。今日通的查询后台会全程记录包裹从中国出库、国际空运或中欧陆运、清关一直到目的国尾程派送的进度：发往欧洲多数线路的尾程交给 DHL 派送，发往美国交给 UPS 派送（并直接同步它们的轨迹）；此外，爱尔兰专线可由 [联邦快递（FedEx）](carrier:fedex) 派送，西班牙专线交给 CTT Express，波兰专线交给 InPost，墨西哥专线交给 J&T Express Mexico。

## 今日通（JRT）单号格式：怎么看、怎么查

今日通自有运单号固定为 `JRT`（今日通拼音首字母）+ 10 位数字 + `MM`，总共 15 位字符。以下示例数字均为虚构：

| 单号（虚构） | 格式说明 |
| --- | --- |
| `JRT4829173605MM` | `JRT` 加 10 位数字再加 `MM`：标准今日通运单号。 |
| `JRT482917360MM` | 只有 9 位数字：不符合今日通格式，请检查是否漏输了一位数字。 |
| `JRTO4829173605MM` | `JRT` 后面多了一个字母 `O`：Peek 会自动纠正并按 `JRT4829173605MM` 查询。 |
| `UP4829173605142839` | `UP` 加 16 位数字：部分发件人使用的参考号，有时能在今日通后台查到订单，但并非只有今日通发件人使用这一格式。 |
| `RR482917367CN` | 2 位字母 + 9 位数字 + `CN`：中国邮政国际小包单号，不是今日通运单号。 |

在 Peek 中粘贴单号无需在意空格和大小写。找不到单号时，请先看 [如何找到你的快递单号](guide:find-tracking-number) 和 [快递单号格式指南](guide:tracking-number-formats)。今日通后台还支持用卖家的自定义参考号查件，但 Peek 会去掉连字符，带连字符的参考号在 Peek 中查不到（见下文）。

**纯数字 IP 地址的查件链接。** 今日通的查询系统直接部署在纯 HTTP 的裸 IP 服务器上，没有公开域名，Peek 目前也不掌握今日通的其他官方网站。如果卖家发给你的查件链接是以一串数字 IP 开头、包含 `trackIndex.htm` 和 `documentCode=` 参数的网址，你可以把整条链接直接粘贴进 Peek，Peek 会自动提取里面的单号或参考号。本页不提供这个纯 HTTP 查件页的链接。

## 今日通（JRT）物流状态是什么意思

今日通的许多轨迹采用「中文 + 自译英文」并列显示，Peek 会完整保留这些双语原文；纯中文节点（如 `到达收货点`）和同步来的中文 UPS 节点，Peek 显示为对应的英文。先看最常见的主流程节点：

| 今日通轨迹原文 | 对你的实际含义 | Peek 显示阶段 |
| --- | --- | --- |
| `货物电子信息已经收到` | 卖家已建单，由今日通背后的华磊（Hualei）货代查询平台自动写入，尚未实物扫描。 | 「已生成面单」 |
| `Shipment information received` | 订单电子信息已传给今日通，还没有扫码收货。 | 「已生成面单」 |
| `到达收货点` | 包裹已到达今日通收货点，是货代实收的第一条扫描。 | 「已揽收」 |
| `货物离开操作中心` | 包裹已离开今日通操作中心。 | 「运输中」 |
| `深圳集散中心 已收货` | 离开操作中心后、登机前，深圳集散中心完成收货入仓。 | 「运输中」 |
| `货物打包完成，已发往航司` | 打包完成，正在送往航空公司货站。 | 「运输中」 |
| `抵达航司已起飞` | 已交付航空公司并且航班从所列机场起飞。 | 「运输中」 |
| `货物已交机场,等待航班安排中 Cargo delivered to airport, pending flight arrangement` | 货物已送抵机场等待排舱（这里的 Delivered 是交到机场，不是送到你家）。 | 「运输中」 |
| `报关已放行等待…号航班起飞 Customs released,Awaiting departure on flight of the …` | 中国出口报关已放行，等待省略号标注日期的航班起飞。 | 「运输中」 |
| `航班起飞，以实际抵达为准 Flight departure subject to actual arrival time` | 国际航班已起飞。 | 「运输中」 |
| `航班落地，拆板中 Flight landing, dismantling board in progress` | 航班已降落，机场正在拆板理货准备清关。 | 「运输中」 |
| `机场内等待提货，等待清关 Waiting for cargo pickup and customs clearance at the airport.` | 货物在目的国机场等待提货并办理进口清关。 | 「清关中」 |
| `理货较慢，导致提货缓慢，已催促尽快处理 Slow cargo sorting has resulted in delayed pickup. We have followed up for prompt handling.` | 目的国机场理货排队较慢、货代已在催办，属于时效延误而非丢件。 | 维持上一阶段不变 |
| `已提货，清关中 The goods have been picked up and are currently undergoing customs clearance` | 货物已提出，正在进行进口清关。 | 「清关中」 |
| `清关完成，卡车发运 Customs clearance completed; truck dispatched.` | 清关放行，已装上卡车转运。 | 「运输中」 |
| `已交服务商 Delivered to service provider` | 已移交给目的国尾程快递公司（不是送到你手中）。 | 「运输中」 |

**其他纯中文节点（空运与欧洲线）。** 登机前还常见 `快件操作完成`、`分拣包裹已出库`、`货物已交机场,预计…号的航班`、`货物到达口岸，预计…号航班起飞` 和 `预计…号航班飞`（标注预计起飞的月/日，均属「运输中」）。欧洲空运线落地后还常出现 `航班已抵达`、`清关完成，中转德国中`（在落地国清关后转运往德国交接点）和 `抵达德国，已交服务商`（在德国移交 DHL 等尾程服务商，均属「运输中」）。

**经哈萨克斯坦的中欧卡航陆运线。** 部分发往欧洲的包裹不走空运而是走新疆出境陆运，全线纯中文节点均属「运输中」：`已装柜发车，待抵达新疆`（离开深圳后装柜西行）、`已抵达新疆，换装/报关，待出境`、`因哈萨克斯坦拥堵，外车入境新疆拥堵缓慢，在等待外车倒装后报关出境。`（口岸排队等待倒装，属于正常边境滞留而非丢件）、`已出境，哈萨克斯坦阿拉木图`（已出境到达阿拉木图）以及 `哈俄边境，待办理手续进入俄罗斯`（过境中转国口岸，并非最终目的国进口清关）。

**纯英文与斜杠/逗号双语节点。** 部分渠道只写英文或用斜杠/逗号拼接中英文（除注明外均属「运输中」）：

- 出运与订舱：`Shipment arrived at facility`、`Sending to the airport`、`The goods have been packed and sent to the airline`、`Package has been packed and delivered to airport`、`Cargo handed over to the airline`、`Domestic customs clearance completed`（中国出口清关，不是目的国清关）、`国内清关完成`、`Arriving at the airport - arranging flights`、`Arriving at the airline, in the process of booking a flight`。
- 航班排期与起飞：`Flight is expected to take off on …`、`The flight is expected to depart on … and land on …`、`Estimated departure is …`、`Estimated arrival date is …`、`预计…航班/Flight scheduled for …`、`预计配载航班…月…日,Expected flight on …`（表示待配载的航班日期，Peek 不会误当成预计送达日）、`Flight has flown`、`The flight has departed and is expected to … land`、`The flight has taken off`、`航班已启运/Package in transit to destination country`、`航班已起飞,The flight has taken off from the origin`（英文句里的数字日期如 `11.12` 为月.日，即 11 月 12 日）；如遇联程晚点会出现 `Sorry, the second flight has been delayed and is expected to … land`；另有 `During transportation to …` 表示正发往目的国。
- 落地、清关与交接：`Flight has arrived`、`The flight has arrived and is awaiting dismantling`、`Arrived at Destinated Airport`、`The NOA has been received`（航司到货通知）、`Dismantling and picking up goods in progress`、`航班已落地，拆板中/Package arrived at destination country`；进入清关显示 `Customs clearance of the cargo is in progress` 或 `Arrived at customs clearance warehouse`（「清关中」），放行后显示 `Customs clearance completed`、`Customs clearance in destination country completed` 或 `货物已清关完成/Import customs clearance complete`；交接尾程显示 `In transit to final service provider`、`Delivery to final service provider`、`The goods have been handed over to the carrier`、`The cargo has been picked up and arrived at the warehouse，awaiting outbound shipment`、`It has been handed over to the service provider.` 或西班牙空运线的 `It has been transferred to the provider`（均非送达收件人）。若出现 `returned shipment has been received by processing center`，表示退件已回到今日通中心（「已退回寄件人」）。

**欧洲线同步的 DHL 与联邦快递（FedEx）轨迹。** 发往欧洲的运单随后会抄录 DHL 原文（常为全小写）：`the instruction data for this shipment have been provided by the sender to dhl electronically`（「已生成面单」）；`shipment in transit to dhl`、`A preferred location was designated as a reception option for this shipment.`（你设置的偏好投放点，不改阶段）、`The shipment has been posted by the sender at the retail outlet`（今日通在德国邮政网点交寄）、`pick-up was successful.`（DHL 收件，不是收件人自提）、`the shipment has been processed in the parcel center of origin`、`loaded to movement / tour vehicle`、`The shipment has arrived in the destination country/destination area.`、`The shipment has left the import parcel center in the destination country/destination area.`、`The shipment is being prepared for delivery in the delivery depot` 均为「运输中」；`The shipment has been loaded onto the delivery vehicle` 为「派送中」；`not delivered-consignee could not be reached`、`The shipment could not be delivered. Please find more information here.`、`The recipient was not present. Please find more information here.` 与 `not delivered-bad address` 均为「派送未成功」；`held for consignee collection` 与 `The shipment is ready for collection from a ParcelShop/retail outlet. Please find more information here.` 为「待取件」；`shipment will be returned to origin-not ordered` 与 `shipment will be returned to origin-bad address` 为「已退回寄件人」；`the shipment has been successfully delivered` 为「已签收」（若出现在退件之后，则是退回寄件人签收，Peek 显示「已退回寄件人」）。走 [联邦快递（FedEx）](carrier:fedex) 的包裹则会先出 `Shipment information sent to FedEx`（「已生成面单」），随后是 `Arrived at FedEx hub`、`Departed FedEx hub`、`On the way` 以及表示收件人申请暂存的 `Delivery option requested, hold request received - check back later for shipment status`（均为「运输中」）。

**美国线同步的今日通与 UPS 轨迹。** 美国专线的今日通干线为简短中文（均「运输中」）：`…号航班`（如 `10/22号航班`）、`航班已飞`、`航班抵达`、`提货中`、`清关完成，已提货，待交仓` 和 `已交仓`（交到当地快递仓）。接着系统会同步中文版 UPS 轨迹：`发件人已创建标签，但是 UPS 尚未收到包裹。`（「已生成面单」）、`等待递送至 UPS Access Point? 地点。`（转投自提点备注，系统把注册商标符号显示成问号，不改阶段）、`抵达设施`、`离开设施`、`UPS 设施处理`、`已提取`（UPS 网点间交接，不是收件人取件，均为「运输中」）、`已装上递送车辆` 与 `准备今天递送`（「派送中」），以及 `已递送`（「已签收」）。

**墨西哥线同步的 J&T Express Mexico 西语轨迹。** 墨西哥清关后，系统会同步极兔墨西哥（J&T Express Mexico）带【城市】【站点】的西语扫描：`【…】【…】¡Recolección exitosa!. El mensajero de J&T asignado … recolectó tu envío.`（J&T 揽收已清关包裹，有时 `¡` 会乱码成 `?`）、`【…】Tu envío ha llegado a【…】`、`【…】Tu envío ha salido de【…】con destino a【…】` 均为「运输中」；进入派送后出现 `【…】【…】El mensajero de J&T Express … está en camino a tu domicilio.`（「派送中」）。

**不同线路的时间基准。** 今日通自身轨迹均按北京时间读取（冬季北京时间 17:00 为伦敦 09:00、柏林 10:00）。而抄录的 DHL 节点按德国或目的国当地城市时间读取；抄录的 UPS 及美国境内节点按美国当地时间读取；墨西哥 J&T 节点按墨西哥城时间（比北京慢 14 小时）读取，再统一换算为你设备所在时区。

## 今日通（JRT）包裹在各国由谁派送

- **欧洲：** 除爱尔兰 FedEx 专线、西班牙 CTT Express 专线（清关交接前常见 `En tránsito internacional. Pendiente de recepción en CTT Express`）和波兰 InPost 专线外，大多数欧洲线路末端由 DHL 派送。今日通在德国邮政网点交寄后，运单记录里可能会用 DHL 单号替换原来的 JRT 单号；只要 Peek 能识别出 DHL 单号，就会显示由 DHL 派送并同步查询 DHL 官方系统，一旦 DHL 产生同等或更新的扫描便无缝接力切换。
- **欧盟进口税费：** 欧洲空运件通常在落地机场办理进口清关。根据 [欧盟委员会海关改革说明](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en)，自 2026 年 11 月 1 日起欧盟对境外网购商品征收 2 欧元统一处理费；具体由谁代收关税、增值税及处理费，见 [包裹海关扣留与清关指南](guide:customs)。
- **美国：** 末端交给 UPS 派送，在 `已交仓` 之后直接在今日通轨迹里同步 UPS 的全套扫描。
- **墨西哥：** 末端交给 J&T Express Mexico 派送并同步其西语轨迹。
- **爱尔兰 FedEx 专线：** 产品名称带 FedEx 的线路在交付 [联邦快递（FedEx）](carrier:fedex) 后，记录里会换上 FedEx 单号，Peek 识别后也会自动接力切换到 FedEx 官网跟踪；如未自动显示 FedEx 单号，请向卖家索取。
- **其他国家与地区：** Peek 无法判断当地尾程快递商，请向卖家索取尾程快递名和单号，派送次数、自提点与保管期限均按当地快递规定执行。

## 今日通（JRT）包裹延误、查不到或未收到怎么办

- **只有电子预报行。** 仅见 `货物电子信息已经收到`、`Shipment information received` 或 DHL/FedEx 电子面单行时，说明包裹还没被实扫入库。过了几天还没动静请联系卖家确认是否已经发出。
- **等待航班或航班延误。** 干线节点会标明计划起飞日期，遇延误会出现 `航班延误，预计…号飞出 Flight delayed, estimated departure on …`；如果新日期过了仍没起飞，请通知卖家跟进。
- **停在 `已交服务商`、`Delivery to final service provider` 或 `已交仓`。** 今日通干线运输已完成；若轨迹没有继续同步 DHL、UPS、FedEx 或 J&T Express Mexico 的扫描，且 Peek 也没拿到尾程单号，说明后续进度在当地快递系统里，请向卖家索取尾程单号。参见 [物流为什么停止更新](guide:tracking-not-updating) 和 [中国直邮包裹跟踪指南](guide:tracking-from-china)。
- **查无记录。** 当今日通后台查不到某个单号时，会返回一行只回显单号、其余全空的空结果，Peek 把它当作查无此单：先显示「等待 JRT 更新」，两天后仍查不到则提示「请检查运单号」。请逐字符核对卖家提供的单号。
- **地址有误被退回。** 出现 `not delivered-bad address` 时请立刻核对收货地址并联系卖家；如果紧接着出现 `shipment will be returned to origin-bad address`，说明包裹已开始退回，请找卖家协商补发或退款。
- **显示已签收却没收到，或包裹丢失/退件。** 签收扫描来自当地尾程快递，请先问当地快递商，再找卖家或平台处理售后。详见 [显示已签收但未收到](guide:delivered-not-received)、[包裹丢失怎么办](guide:lost-parcel) 和 [包裹退回寄件人](guide:returned-to-sender)。

## 今日通（JRT）客服电话与联系方式

Peek 未能找到今日通面向收件人的公开官网、客服电话、邮箱或投诉地址，仅知晓其部署在裸 IP 上的查询系统。因为发货合同由卖家与今日通签署，关于包裹的任何问题都应优先联系下单的卖家；卖家不回复时，通过购物平台客服介入处理。

## 用 Peek 跟踪今日通（JRT）包裹

[Peek](/) 会通过 `JRT` + 10 位数字 + `MM` 的唯一规则立即识别今日通（如虚构单号 `JRT4829173605MM`），也支持直接粘贴包含 `documentCode=` 的今日通裸 IP 查件链接；确认查到记录后会显示「JRT 有这个包裹的记录。」。遇到 `UP` 开头的 16 位数字参考号时，Peek 也会自动询问今日通后台。若卖家只给了其他格式的参考号，同样可以粘贴（Peek 跟踪 4,500 多家快递公司），然后点「更换」或详情页的「更换快递公司」并搜索 `JRT`、「今日通」或 `Jinritong` 手动指定；查到后 Peek 会自动展示对应的 `JRT…MM` 主单号。但注意 Peek 会自动去掉单号里的连字符，因此带连字符的卖家参考号建议请卖家换发 `JRT…MM` 单号。

Peek 直接读取今日通后台，按每一条轨迹记录（而非汇总状态）归纳包裹阶段（「已生成面单」「已揽收」「运输中」「清关中」「派送中」「派送未成功」「待取件」「已签收」「已退回寄件人」等），展示每条扫描的地点和目的国，并按上述规则分别以北京时间或尾程当地时间换算为你设备所在时区。和同用华磊平台的其他货代一样，Peek 会自动拦截提前超过 12 小时写入（或同秒内提前超 6 小时写入）以及日期晚于当前查询时刻的预设节点，确保预设内容不会触发错误阶段或误报通知；同时隐藏收件人姓名、地址、邮编、重量、费用、签名及签收照等敏感信息。由于今日通不提供预计送达日期，Peek 也不显示预计送达时间。

在尾程衔接方面：美国线默认持续在今日通系统里同步 UPS 轨迹，除非运单记录里直接给出了 `1Z…` 等尾程单号，Peek 便会自动转交当地快递追踪；欧洲线一旦运单里出现可识别的 DHL 单号，或爱尔兰专线出现 FedEx 单号，或发往瑞士/列支敦士登出现瑞士邮政信息，甚至出现其他国际邮政 S10 单号或能唯一匹配的当地单号，Peek 都会同时向当地快递查询，待其出现最新扫描后无缝切换并保留全部历史轨迹。

Peek 只在你打开这个包裹时，或在派送当天且开启了通知时，最快每 10 分钟查询一次，其余时间通常每小时一次（不登录账户跟踪、一小时内没有打开且未开启通知时为每 3 小时一次）。包裹进入「派送中」（如欧洲线 DHL 装车、美国线 UPS 装车派送、墨西哥线 J&T 派送员出发）的当天，从快递当地时间早上 6 点起最快每 2 分钟查询一次。连续两天没有新扫描（停在「已生成面单」或「已揽收」时为 12 小时）后改为每小时一次。转交尾程快递后，按该快递自己的节奏查询。静默满四天时 Peek 会提示「包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 JRT 查询。」，超过三周无动静则提示「已超过三周没有新动态。如果还没收到，请联系卖家。Peek 会持续向 JRT 查询。」。

你可以在通知设置里选择「每次扫描」「重要环节」或「仅派送当天」。Peek 仅负责监控轨迹，不能替你联系今日通或卖家。

## 关于今日通（JRT）物流查询的常见问题

### 今日通（JRT）是什么公司？

全称厦门今日通商贸有限公司（Xiamen Jinritong），是一家位于福建厦门的跨境货代。它的运单号格式为 `JRT` + 10 位数字 + `MM`，查询后台运行在华磊（Hualei）货代物流平台上。

### 为什么今日通轨迹里写了 Delivered 却没收到货？

这是英文直译造成的误解。`Cargo delivered to airport` 指货物交到了起运机场，`Delivered to service provider` 或 `Delivery to final service provider` 指货物移交给了尾程快递服务商——它们都不是送到你手里的签收，Peek 会统一归类为「运输中」。

### 今日通包裹在目的国是由哪家快递派送的？

欧洲多数线路交给 DHL 派送，美国交给 UPS 派送（两者轨迹都会同步回今日通后台）；此外爱尔兰专线可由 [联邦快递（FedEx）](carrier:fedex) 派送，西班牙、波兰和墨西哥专线末端分别为 CTT Express、InPost 和 J&T Express Mexico。其他国家请向卖家询问当地尾程快递及单号。

### 今日通包裹丢了或一直没动静该联系谁？

请联系下单的卖家或购物平台：是卖家向今日通订舱发货，且今日通没有公开面向收件人的客服电话或官网联系方式。

:::sources
- [European Commission：欧盟海关改革（英文）](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 自 2026 年 11 月 1 日起欧盟征收 2 欧元统一处理费
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – 今日通公司名称、华磊平台裸 IP 查件链接、JRT…MM 与 UP 参考号及 O 容错规则、全线中英文/空运/中欧陆运/DHL/FedEx/UPS/J&T 墨西哥轨迹与时区解析、预设节点扣留机制及尾程自动接力规则
:::
