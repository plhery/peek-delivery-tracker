---
title: 塞浦路斯邮政（Cyprus Post）物流查询：单号、状态与关税
description: 查询塞浦路斯邮政（Cyprus Post）包裹：解读 13 位 UPU S10 单号结构、英文国际邮政状态含义、欧盟关税与处理费以及投诉渠道。
slug: cyprus-post-tracking
published: 2026-11-27
updated: 2026-11-27
---

塞浦路斯邮政（Cyprus Post / Κυπριακά Ταχυδρομεία，隶属塞浦路斯邮政服务局 Department of Postal Services）是塞浦路斯共和国的国家邮政。你可以在塞浦路斯邮政官网 cypruspost.post 上查询邮件，或直接把单号粘贴到上方输入框中。完全找不到单号？请参阅 [如何查找追踪单号](guide:find-tracking-number)（英文）。

## 塞浦路斯邮政追踪单号

塞浦路斯邮政追踪页支持万国邮政联盟（UPU）S10 标准的 13 位单号，用于挂号信、EMS 邮件、包裹与可追踪信函：[2 位字母、9 位数字加 2 位字母](guide:tracking-number-formats)（英文）。

:::anatomy RR 482917367 CY
- RR | 服务代码：首位字母表明邮件类别
- 482917367 | 8 位序列号加 1 位校验码
- CY | 国家代码：CY 表示在塞浦路斯授权下签发的单号
:::

| 首位字母（虚构示例） | 邮件类别 |
| --- | --- |
| `RR482917367CY` | R：挂号邮件 |
| `CP482917367CY` | C：邮政包裹 |
| `EE482917367CY` | E：EMS 国际特快专递 |
| `LA482917367CY` | L：可追踪信函 |
| `UA482917367CY` | U：含商品的函件，不对客户开放全程追踪 |

从海外寄出的邮件保留原发国邮政签发的单号，以该国代码结尾（例如 `…GB`、`…FR`、`…GR`）；塞浦路斯邮政官网同样可查这类单号，并显示境外邮政的扫描记录。若页面提示 `There is no information for this item`，说明系统尚未查到该单号，请向寄件人核对。

## 塞浦路斯邮政物流状态含义解析

在 Peek 读取的塞浦路斯邮政英文追踪页面中，每条记录都包含时间、国家、地点、事件、下一邮局（next office）和补充信息（extra information）。事件采用 [国际邮政（UPU）标准名称](guide:tracking-statuses)（英文）：`(Otb)` 表示原寄国环节，`(Inb)` 表示目的国环节。“下一邮局”指邮件即将发往的站点，而非当前所在位置。对于寄往你的入境邮件：

| 塞浦路斯邮政原文 | 对你的含义 |
| --- | --- |
| `Receive item from customer (Otb)` | 邮件已在塞浦路斯或海外寄出并收寄。 |
| `Insert item into bag (Otb)` | 已在互换局装袋，准备发往境外。 |
| `Receive item at office of exchange (Inb)` | 邮件已抵达目的国互换局。 |
| `Send items to delivery point/ collection point` | 正在发往负责派送或代收的邮局网点。 |
| `Receive item at delivery office (Inb)` | 已抵达负责派送的投递局。 |
| `Hold item at point of delivery (Inb)` | 正在投递局待派，等待排入投递班次。 |
| `Handover item to delivery agent (Inb)` | 已交由邮递员外出派送。 |
| `Item not delivered/ Pending delivery` | 未能投递：具体原因见官网的 Extra Information 栏。 |
| `Receive item at collection point for pick-up (Inb)` | 已到达邮局自提点，可前往领取。 |
| `Deliver item (Inb)` | 已送达，或已在邮局领取。 |

## 来自欧盟以外商品的海关费用

自 2026 年 7 月 1 日起，从欧盟以外网购的商品（包含邮政寄递物品）即使整票货值不超过 150 欧元也不再免征关税：欧盟按每项商品征收 3 欧元临时关税。所谓“一项商品”指整票货物中关税税则归类、品名描述及申报原产地（如有申报）相同的全部货物，无论件数多少。自 2026 年 11 月 1 日起，欧盟还对所有来自欧盟以外的网购商品（不限货值）加收每项 2 欧元的欧盟处理费（Union handling fee），且该处理费本身也需缴纳增值税。上述费用首先由向海关申报的一方（平台、卖家、承运商或报关代理）负责缴纳。在塞浦路斯，海关与消费税局（Department of Customs and Excise）负责对非欧盟进口商品征收进口关税和增值税。详见 [包裹被海关扣留指南](guide:customs)（英文）。

## 塞浦路斯邮政包裹延误或未到货

如果轨迹停止更新，请参阅 [物流停止更新怎么办](guide:tracking-not-updating)（英文）。如果 [显示已送达](guide:delivered-not-received)（英文） 却没收到，请向寄件人核对单号。遇到包裹丢失或到货破损，请参阅 [包裹丢失](guide:lost-parcel)（英文） 和 [包裹破损](guide:damaged-parcel)（英文）。

**网购迟迟未到？** 塞浦路斯消费者保护局（Consumer Protection Service）指出，除非另有约定，商家通常须在 30 天内交货；逾期未交付时，你可通知商家并给予合理的宽限期，若仍未送达即可解除合同。其免费消费者热线为 1429。

**收到要求付款放行包裹的短信？** 点击任何链接前请先阅读 [防范快递诈骗短信](guide:delivery-scam-texts)（英文）。

## 使用 Peek 追踪塞浦路斯邮政包裹

对于以 `CY` 结尾且校验码正确的邮政单号，[Peek](/) 会查询塞浦路斯邮政与 [Chronopost](carrier:chronopost)；只要塞浦路斯邮政查到记录就会显示由其承运，否则提示暂未查到且添加后 Peek 会持续检查。如果校验码错误，即便通过“更改”手动选择塞浦路斯邮政，Peek 也不会发起查询，此时请向寄件人核对单号。包含单号的 cypruspost.post 链接同样可直接粘贴使用。

对于海外寄出的邮件，Peek 会优先查询签发单号的原寄国邮政（例如 `…GR` 查询 ELTA）或综合追踪服务。塞浦路斯邮政官网同样支持这些单号，你也可以点“更改”手动选择 “Cyprus Post”。如果境外邮政指出邮件发往塞浦路斯，或者未标明目的地但你在塞浦路斯境内添加了该邮件，Peek 也会自动尝试塞浦路斯邮政，并在塞浦路斯邮政出现与境外邮政同样新或更新的扫描时切换过去。Peek 仅向塞浦路斯邮政查询 13 位单号。不确定承运商？照常粘贴即可：Peek 支持超过 4,500 家承运商。

Peek 会按原文显示塞浦路斯邮政状态，在地图上标注每次扫描的邮局/设施与国家，并显示送达时间以及待取或已取件的邮局名称。塞浦路斯本地扫描保持精确时间，海外扫描按原发国时钟读取（若该国跨多个时区，Peek 根据地点推算时区，小时数可能略有偏差）。当你打开包裹，或在开启通知的情况下进入派送日时，只要塞浦路斯邮政官网正常响应，Peek 会直接向其查询（最高每 10 分钟一次；邮递员外出派送当天自塞浦路斯时间早上 6 点起最高每 2 分钟一次），并在状态变化时通知你。连续两天无新动态时（处于“已创建运单”或“承运商已揽收”时为 12 小时）改为每小时检查一次。若包裹在“已创建运单”、“承运商已揽收”或“运输中”连续四天无新扫描，Peek 会标注最后一次扫描日期，并附注“包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 Cyprus Post 查询。”及帮助链接。Peek 不显示预计送达日或重量，也不展示 Extra Information、下一邮局、邮编或签收人姓名：要查看未投递原因，请前往 cypruspost.post 追踪页查看。

## 塞浦路斯邮政追踪常见问题

### 塞浦路斯邮政就是邮政服务局（Department of Postal Services）吗？

是的。万国邮政联盟将塞浦路斯指定邮政运营商列为 Department of Postal Services（Cyprus Post），即主管邮政服务的国家机构；塞浦路斯邮政监管机构 OCECPR 亦认定塞浦路斯邮政为普遍邮政服务提供方。

### 单号末尾的 CY 是什么意思？

表示该单号是在塞浦路斯授权下签发的。按照万国邮联 S10 标准，最后两位字母代表签发单号的授权国家代码，因此不一定能可靠反映邮件的实际交寄地。Peek 会针对这类单号查询塞浦路斯邮政。

### U 开头的单号可以追踪吗？

不能作为可追踪邮件查询。S10 标准将 U 开头单号分配给含商品、但不向客户提供全程追踪的函件，该编号主要用于供应链内部流转及提前发送的电子预报数据。不过对以 `CY` 结尾的 U 单号，Peek 仍会向塞浦路斯邮政查询。

### 如何投诉塞浦路斯邮政？

请先以书面形式向塞浦路斯邮政投诉。如果对方 15 天内未答复，或你对答复不满意，可向邮政监管机构——电子通信与邮政监管专员办公室（OCECPR）在线投诉；其官网为希腊语，但提供英文投诉表格。

:::sources
- [Universal Postal Union: S10, identification of postal items](https://www.upu.int/UPU/media/upu/files/postalSolutions/programmesAndServices/standards/S10-12.pdf) – 13 位格式、首字母 R/C/E/L/U、校验位与签发国代码
- [Universal Postal Union: member countries, Cyprus](https://www.upu.int/en/Universal-Postal-Union/About-UPU/Member-Countries?csid=-1&cid=86) – Cyprus Post 为指定运营商
- [Universal Postal Union: list of designated operators](https://www.upu.int/getmedia/0ff98b1d-9dd4-46e7-bdd1-dda1b654308f/SettlementCircularsEn.pdf) – Department of Postal Services（Cyprus Post）
- [National Open Data Portal: list of post offices](https://www.data.gov.cy/el/dataset/katalogos-kai-theseis-tahydromikon-grafeion) – 邮政服务局为国家邮政主管机构（希腊语）
- [OCECPR: universal postal service](https://ocecpr.ee.cy/katanalotes/katholiki-ypiresia/taxidromeia) – 塞浦路斯邮政为普遍邮政服务提供方（希腊语）
- [OCECPR: complaints](https://ocecpr.ee.cy/katanalotes/parapona) – 先书面投诉承运商、15 天期限、在线投诉、英文投诉表（希腊语）
- [Consumer Protection Service: online purchases](https://consumer.gov.cy/en/consumer-rights/66/59/?ctype=ar) – 30 天交货、宽限期、解除合同（希腊语）
- [Consumer Protection Service: contact us](https://consumer.gov.cy/en/contact-us) – 免费消费者热线 1429（希腊语）
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 自 2026 年 7 月 1 日起每项 3 欧元关税、自 2026 年 11 月 1 日起 2 欧元欧盟处理费
- [European Commission: guidance on the EUR 3 customs duty and the Union handling fee](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – “项”的定义、邮政快件、处理费不限货值、缴纳责任方及处理费增值税
- [Department of Customs and Excise: About us](https://www.mof.gov.cy/mof/customs/customs.nsf/All/289500205CFF33DFC22572AC003A2B02?OpenDocument) – 对进口商品征收进口关税与增值税
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Peek 读取的数据：Cyprus Post 英文追踪页及其栏目与事件名
:::
