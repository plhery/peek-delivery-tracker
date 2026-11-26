---
title: DSV 物流查询：运单号、状态与 DB Schenker
description: 查询 DSV 陆运、空运、海运及原 DB Schenker 货运：如何选单号、myDSV 与 Schenker 状态含义、派送、清关、理赔与联系方式。
slug: dsv-wuliu-chaxun
published: 2026-11-26
updated: 2026-11-26
---

DSV 是一家丹麦运输与物流集团，提供陆运、空运和海运服务，并于 2025 年从德国铁路（Deutsche Bahn）收购了 Schenker（DB Schenker）。在 dsv.com 的“Track your shipment”查询框里有两个入口：“Track DSV shipment”会打开 DSV 自己的查询系统 myDSV，“Track Schenker shipment”则打开原 DB Schenker 的查询系统，均无需账号。单号由订舱或下单运输的一方提供，通常是寄件人或商家；把它粘贴到上方输入框即可查询。

## DSV 运单号长什么样、在哪里找

预订 DSV 运输的企业掌握单号：查看商家的发货邮件或订单页，或直接向寄件人索取。更多方法见[如何查找运单号](guide:find-tracking-number)（英文）。

在 DSV 将 Schenker 并入自家系统期间，仍保留两套查询入口：通过 Schenker 预订的货运可能只出现在 Schenker 查询系统中。如果其中一个查不到，换另一个试试。

| 单号（虚构） | 含义 |
| --- | --- |
| `DEKLM482917365` | 14 位字符：欧洲国家代码（如 DE、SE、DK 或 PL）加 3 位字母和 9 位数字。这是原 DB Schenker 陆运单号，也是 Peek 目录为 DSV 收录的格式。 |
| `KLM4829173` | 3 位字母加 7 或 8 位数字：另一种原 DB Schenker 陆运单号。Peek 不会仅凭该格式识别为 DSV。 |
| `094827361549283711` | 18 位数字（前面带 `00` 时为 20 位）：原 DB Schenker 陆运单件货物的条码号。 |
| 字母与数字组合 | myDSV 货运号、订舱号或分运单号。只要是 5 到 40 位、且至少包含一位数字的字母数字组合，Peek 都会向 myDSV 查询。 |
| `mydsv.com/track-shipment?query=DEKLM482917365` | myDSV 查询链接，也就是 dsv.com 上“Track DSV shipment”打开的地址。 |

客户参考号、订单参考号或航空主运单号（master air waybill）在 myDSV 的搜索框里能查到货物，但 Peek 不跟进这类号码：请使用 DSV 的货运号、订舱号或分运单号。如果一个单号对应多票货物，Peek 也无法替你挑选其中一票。

## DSV 物流状态含义

myDSV 会记录货物从订舱到签收凭证的每一次扫描。公开页面提供概要信息；经申请后的私密视图会展示货运详情。以下是 Peek 读取的 myDSV 环节：

| DSV 显示 | 对你的含义 | Peek 状态 |
| --- | --- | --- |
| `Booking` | DSV 已收到订舱信息，仅带日期不含具体时间。 | “已生成面单” |
| `Creation` | 货物已在 DSV 陆运系统中创建。 | “已生成面单” |
| `Actual pickup` | DSV 已从寄件人处提货。 | “已揽收” |
| `Cargo received` | 货物已到达 DSV，可能是到达场站或完成提货。 | “已揽收” |
| `Actual Departure`、`Actual departure - Origin Port` | 已从干线上的港口或机场发出。 | “运输中” |
| `Actual arrival`、`Actual arrival- Destination Port` | 已抵达干线上的港口或机场。 | “运输中” |
| `Handover responsibilty of cargo`、`Handover of Cargo` | DSV 已在服务终点移交货物，运输流程结束（前者保留了 DSV 原文拼写）。 | “已签收” |
| `Actual delivery` | 已送达。 | “已签收” |

`Estimated Departure`、`Estimated arrival`、`Estimated pickup`、`Estimated delivery`、`Requested pickup` 和 `Requested delivery` 属于计划安排而非实际扫描：Peek 不会把它们放进物流记录，只在签收前把 `Estimated delivery` 显示为预计送达日期或日期区间。

原 DB Schenker 查询系统覆盖陆运货物，文案有所不同：

| DB Schenker 查询系统显示 | 对你的含义 | Peek 状态 |
| --- | --- | --- |
| `Booked` | 寄件人已预订运输。 | “已生成面单” |
| `Out for collection` | 司机正在前往寄件人处提货。 | “已生成面单” |
| `Collected` | 已从寄件人处揽收。 | “已揽收” |
| `Delivered to terminal by shipper` | 寄件人已自行将货物送至场站。 | “已揽收” |
| `Arrived`、`Departed` | 已到达或离开某座场站。 | “运输中” |
| `Customs clearance initiated` | 清关已开始。 | “清关中” |
| `Customs clearance finalised` | 已放行，继续运输。 | “运输中” |
| `Out for Delivery` | 已装上派送车辆。 | “派送中” |
| `Not delivered` | 派送未成功，后面可能附带原因。 | “派送未成功” |
| `To consignee's disposal`、`Ready for pick up` | 货物正在等待你前往提取。 | “待取件” |
| `Delivered` | 已送达。 | “已签收” |
| `Picked up by consignee`、`Picked up at agent` | 收件人已自提，或已从代理处取走。 | “已签收” |
| `ePOD available` | 电子签收凭证已生成。 | “已签收” |

途中还可能出现 `Linehaul departed`、`Linehaul arrived`、`Terminal inventory`、`Vessel Loaded`、`Vessel Unloaded`、`Train Loaded`、`Train Unloaded`、`Vessel/Train Loaded` 和 `Vessel/Train Unloaded`：在 Peek 中均显示为“运输中”。`Not Collected`（提货未成功）和 `Not loaded`（未赶上原定装车/装船，并附带原因）会显示为“需要留意”。当查询系统在某个环节旁写出原因（例如派送未成功的原因）时，Peek 会把它附在原文之后。各阶段通用说明见[物流状态含义](guide:tracking-statuses)（英文）。

## DSV 如何派送

**托盘与零担货运。** DSV 称其公路拼箱网络为欧洲最大，覆盖 36 个以上国家和地区、400 多个场站，可承运最重 2,500 公斤的货物，因此托盘或大件商品可能由卡车从 DSV 场站直接派送。

**网购订单。** DSV 的 Parcel E-commerce 服务负责揽收商家包裹、跨境集运，再交由当地末端网络派送，所以最终上门的可能是另一家快递公司。如果商家的邮件里写明了另一家快递或给了第二个单号，也请一并查询：Peek 查询 DSV 时不会返回下一家承运商。

**动态通知与签收凭证。** 在 myDSV 中，用户可以设置提货或清关等环节的通知，查看陆运货物的预计到达时间，并在运输完成后下载签收凭证。如果你不是向 DSV 订舱的一方，请联系寄件人。DSV 网页未说明会尝试派送几次，也未写明场站保管货物多久：请直接向负责派送的 DSV 办事处确认。

**清关。** 在设有办事处的大多数国家，DSV 具备报关行资质；在其他地区则通过代理办理。如需清关协助，DSV 建议联系该国的 DSV 办事处。在欧盟，自 2026 年 7 月 1 日起，从欧盟境外网购、订单金额不超过 150 欧元的商品，按商品种类每类征收 3 欧元欧盟关税；自 2026 年 11 月 1 日起，无论订单金额多少，按商品种类每类另收 2 欧元欧盟处理费。其他国家适用当地规定，详见[包裹卡在海关怎么办](guide:customs)（英文）。

## DSV 货物延迟、停更或破损

**没有动静。** 如果一套查询系统停更，试试 dsv.com 上的另一套：通过 Schenker 预订的货物可能尚未同步到 myDSV。随后联系寄件人，他们向 DSV 订了舱并有对应对接人。详见[物流为什么不更新](guide:tracking-not-updating)（英文）。

**货损或少件。** 网购订单请先联系下单给 DSV 的商家。DSV 的 eClaims 平台可以提交理赔申请与材料、查看处理进度，并为你对接当地理赔专员；该页面未标明时限，建议尽快提交。另见[包裹破损](guide:damaged-parcel)（英文）与[包裹丢失](guide:lost-parcel)（英文）。

**冒用 DSV 名义。** DSV 提醒称，有些与 DSV 无关的公司或个人会冒用其名义招揽货运业务。如果你不确定某个网站或联系人是否真的属于 DSV，请向当地 DSV 办事处核实；涉及包裹诈骗短信，参见[快递诈骗短信识别](guide:delivery-scam-texts)（英文）。

## DSV 联系方式与客服

在 dsv.com 上可以找到：

- **联系表单：** 位于 DSV 的[帮助与联系页面](https://www.dsv.com/en/support/help-and-contact)。
- **当地办事处：** DSV 在 90 多个国家和地区设有网点，可按国家[查找当地 DSV 办事处](https://www.dsv.com/en/support/find-dsv)。
- **理赔：** 通过 DSV [理赔页面](https://www.dsv.com/en/support/self-services/claims)进入 eClaims。
- **DSV A/S：** 法律声明中列出的地址为丹麦 Hedehusene，电话 +45 43 20 30 40。这不是查件专线。

DSV 的帮助页面未提供用于查件的客服电话。

## 用 Peek 跟踪 DSV 货运

粘贴类似 `DEKLM482917365` 的 14 位单号，Peek 会在添加时向 DSV 查询（显示“正在查询物流服务…”），DSV 查到记录后会显示“DSV 有这个包裹的记录。”如果 DSV 尚未录入，页面会显示“等待 DSV 的首次扫描”，并标为“可能是 DSV”，同时 Peek 会持续查询；一周后仍无记录时，Peek 会提示你核对单号或换选其他快递公司。

其他格式的单号，Peek 无法仅凭形态识别为 DSV。仍然可以粘贴：Peek 跟踪 4,500 多家快递公司。如需手动指定 DSV，点击“更换”并搜索 DSV；搜“Schenker”也能找到（显示“又名 DB Schenker”）。已添加的包裹则使用“更换快递公司”。粘贴 mydsv.com 查询链接，或 dsv.com 上带 `refNumber=` 的 Schenker 查询链接，都会直接归入 DSV。

Peek 会先查询 myDSV。如果 myDSV 里查不到某个原 DB Schenker 陆运单号或包装条码，Peek 再转查 Schenker 查询系统；该入口在 Peek 中仅读取陆运货物——只出现在那里的空运或海运货物，请直接在 dsv.com 上使用“Track Schenker shipment”。

Peek 会显示当前状态、DSV 原文的每个环节及其城市和国家，以及预计送达日期。DSV 提供的每个扫描时间都是当地时间且不带时区；Peek 会根据扫描地点（城市、港口、机场代码）或前后扫描推断时区，推断不出时默认按哥本哈根时间读取，偶尔可能存在误差。DSV 返回收件人姓名时，仅在你自己的包裹页“收件人”一栏显示，收件城市也会在运输途中作为你个人地图路线的终点；分享链接里两者都不会出现。Peek 不会显示服务名称、寄件城市、街道地址、邮编和 DSV 进度条，来自 Schenker 查询系统的寄件人、参考号、货品信息和签收人姓名也会略去。

如果瑞典配送公司 Early Bird 把你的包裹转交给 DSV 或 DB Schenker，Peek 会把 DSV 标为派送承运商，并在 DSV 出现新扫描后转为跟进 DSV。

当你打开包裹页，或在派送当天开启通知时，Peek 最多每 10 分钟查询一次；在 DSV 扫描派送当天的当地时间早上 6 点起，最多每 2 分钟查询一次。你可以选择接收“每次扫描”、“重要环节”或“仅派送当天”提醒。连续两天无新动态（在“已生成面单”或“已揽收”状态下为 12 小时）后，改为每小时查询一次；在“已生成面单”下连续七天无动态后，改为每 6 小时一次。如果货物在“已生成面单”、“已揽收”或“运输中”状态下连续四天没有新记录，Peek 会显示“自……起没有物流更新”（附最后一条记录的日期）以及“向 DSV 寻求帮助”链接。Peek 无法替你联系 DSV 或寄件人、预约派送或提交理赔。

## 关于 DSV 物流查询的常见问题

### DSV 和 DB Schenker 是同一家公司吗？

是的。成立于 1976 年的 DSV 已于 2025 年 4 月 30 日完成对德国铁路旗下 Schenker 的收购，dbschenker.com 也已并入 dsv.com。目前 Schenker 货物仍保留独立查询入口。

### 现在怎么查 DB Schenker 运单？

在 dsv.com 的“Track your shipment”查询框里选“Track Schenker shipment”输入单号，或者粘贴到上方输入框中；除非单号是以国家代码开头的 14 位字符，否则需手动把承运商选为 DSV。Peek 会先查 myDSV，若是陆运货物再查 Schenker 查询系统。

### DSV 物流里的 handover of cargo 是什么意思？

表示 DSV 已在其服务终点移交货物，本次运输结束，Peek 会显示“已签收”。如果你还没收到货，请问寄件人 DSV 把货物移交到了哪里，并查看[显示已签收但没收到](guide:delivered-not-received)（英文）。

### DSV 有客服电话吗？

其帮助页面没有提供查件电话。请使用 dsv.com 上的联系表单或查找当地 DSV 办事处。DSV A/S 法律声明中的丹麦电话 +45 43 20 30 40 并非查件专线。

:::sources
- [DSV: Book and track shipments with myDSV](https://www.dsv.com/en/digital-solutions/book-and-track) – 无需账号查询、两个查询入口、dbschenker.com 并入 dsv.com
- [DSV: myDSV](https://www.dsv.com/en/digital-solutions/mydsv) – 公开与私密视图、扫描记录、通知、签收凭证
- [DSV: Self-services](https://www.dsv.com/en/support/self-services) – DSV 与 Schenker 查询系统、eClaims、整合前保留 Schenker 系统
- [DSV: Frequently asked questions](https://www.dsv.com/en/support/faq) – 联系表单、过渡期两套 myDSV 版本
- [DSV: Help and contact](https://www.dsv.com/en/support/help-and-contact) – 覆盖 90 多个国家、办事处查询
- [DSV: Find your local DSV office](https://www.dsv.com/en/support/find-dsv) – 按国家查找办事处
- [DSV: Claims](https://www.dsv.com/en/support/self-services/claims) – eClaims 功能说明
- [DSV: Groupage transport](https://www.dsv.com/en/our-solutions/modes-of-transport/road-transport/groupage) – 36 个以上国家、400 多个场站、最高 2,500 公斤
- [DSV: E-commerce delivery solutions](https://www.dsv.com/en/our-solutions/contract-logistics/ecommerce-solutions/last-mile-delivery) – 跨境集运、本地末端网络
- [DSV: Customs clearance](https://www.dsv.com/en/our-solutions/modes-of-transport/value-added-services/customs-clearance) – 报关行资质、代理、联系当地 DSV
- [DSV: History](https://www.dsv.com/en/about-dsv/history) – 1976 年成立、陆空海运、2025 年收购德国铁路旗下 Schenker
- [DSV: DSV completes acquisition of Schenker](https://www.dsv.com/en/about-dsv/press/news/dsv-completes-acquisition-of-schenker-2628130) – 2025 年 4 月 30 日完成收购与整合
- [DSV: Legal notice](https://www.dsv.com/en/about-dsv/legal-notice) – 丹麦 DSV A/S、电话、冒用 DSV 名义提醒
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 150 欧元以内每类商品 3 欧元关税、2 欧元欧盟处理费
- [European Commission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 不分订单金额每类商品 2 欧元
- [Universal Parcel Scraper: DSV](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/dsv) – 单号格式与链接、两套查询系统、DSV 文案与阶段、当地时间处理、Peek 读取范围
:::
