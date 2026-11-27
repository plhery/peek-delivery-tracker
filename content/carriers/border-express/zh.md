---
title: Border Express 物流查询：运单号格式、状态含义与追踪指南
description: 查询澳大利亚 Border Express（BEX）包裹：了解运单号与标签条码格式、英文状态含义，以及如何在 Peek 中持续跟踪物流进度。
slug: border-express-tracking
published: 2026-11-27
updated: 2026-11-27
---

Border Express（简称 BEX）在 Peek 的目录中仅列为澳大利亚境内的承运商。如果网店通过 Border Express 发出你的订单，用于追踪的编号就是其运单号（consignment number）：将其粘贴到上方输入框即可查询。该编号在商家的发货邮件或订单页面中提供。Border Express 自有的追踪工具是 BEX Online 上的 Quick Track（网址为 bexonline.borderexpress.com.au）。制作本页面时 Peek 未能访问 Border Express 网站，因此以下说明均基于 Peek 代码对该追踪接口的已知行为。

## Border Express 追踪单号是什么样子

Border Express 通过运单号追踪整批货物。Peek 代码已知三种格式，以下数字均为虚构示例：

| 单号（虚构示例） | 格式说明 |
| --- | --- |
| `48291736` | 8 位数字：运单号。 |
| `KLMN482917` | 4 位字母（寄件人前缀）后接数字：同样是运单号。Peek 接受字母后接 5 至 8 位数字。 |
| `KLMNBX00000482917001` | 20 位字符：单件包裹标签上的条码。由 4 位寄件人字母、BX、前置补零至 11 位的运单数字以及 3 位件数序号组成。 |

一票货运（consignment）可能包含多件包裹，每件包裹都有各自的标签条码。同一票货的所有标签开头字母和数字都相同，而运单号将它们关联在一起：Border Express 的追踪页只认运单号，不接受标签条码。但 Peek 接受标签条码，并会从中算出运单号（详见下文）。

仅凭单号外观，Peek 无法直接确定它属于 Border Express。Peek 目录中没有为 Border Express 运单号设置识别规则，而标签条码的规则也仅属于弱匹配。其他承运商也使用相同格式：8 位数字时，Peek 会建议欧洲的 [Mondial Relay](carrier:mondial-relay)、Colisweb 和 Heppner，若末位数字通过校验也会建议北美货运商 TForce Freight；4 位字母加 8 位数字也符合 [Colis Privé](carrier:colis-prive) 的 12 位单号格式。如果商家写的是 Border Express，请改选 Border Express（见下文）。粘贴时带空格或小写字母都没有关系。完全找不到单号？请参阅 [如何查找追踪单号](guide:find-tracking-number)（英文） 和 [追踪单号格式说明](guide:tracking-number-formats)（英文）。

## Border Express 物流状态含义解析

Border Express 追踪页会显示一条进度条以及按最新时间倒序排列的轨迹节点，每条记录附带郊区名和州名。按包裹经过的顺序，Peek 代码已知的节点如下：

| Border Express 原文 | 对你的含义 | Peek 显示 |
| --- | --- | --- |
| `Shipment Data Received` | 寄件人已将包裹信息发送给 Border Express，承运商尚未拿到实物。进度条上显示为 `Data Received`。 | “已生成面单” |
| `Picked Up` | Border Express 已从寄件人处揽收包裹。追踪页通常不给该节点标注具体时间，而 Peek 会略过没有时间的节点。 | “已揽收”（仅在节点带有时间时显示） |
| `In Transit` | 正在 Border Express 运输网络中转运。 | “运输中” |
| `On Board For Delivery` | 包裹已装上派送车辆。进度条上显示为 `On Board`。 | “派送中” |
| `Partially Delivered` | 同一票货运中的部分包裹已送达，其余包裹仍在运输途中。 | “运输中” |
| `Partially Delivered - Final` | 派送流程已结束：整票运单按已送达的件数结案。如有缺件，请联系寄件人。 | “已签收” |
| `Delivered` | 已送达。 | “已签收” |

Peek 会保留 Border Express 的英文轨迹描述，并在上方显示对应的统一状态。未列入上表的其他节点会按原文展示，Peek 会尽量根据措辞归类其状态。

## Border Express 澳大利亚境内派送说明

Border Express 的追踪信息中不会标注其他承运商，因此 Peek 会从第一个节点一直通过 Border Express 跟踪至签收，不会将其转交给其他承运商。

由于 Peek 未能查阅 Border Express 网站，本页不提供有关派送次数、未投递通知单、无接触放门（safe drop）、仓库自提或站点保管期限的规则。相关事宜请直接询问发货商家或 Border Express。更多通用处理方式可参考 [派送未成功怎么办](guide:missed-delivery)（英文） 和 [无人领取的包裹](guide:uncollected-parcel)（英文）。

## Border Express 包裹不更新、丢失或显示已送达却未收到

**查无此单号。** 在 Border Express 追踪接口收录该单号之前，归类在 Border Express 下的包裹会显示“等待 Border Express 更新”，Peek 会持续查询。如果两天后 Border Express 仍不识别该单号且没有其他物流服务返回信息，Peek 会显示“两天过去了，快递公司仍查不到这个单号。请检查单号、更换快递公司，或删除这个包裹。”此后 Peek 会改为每天查询一次，添加满一周后改为每周查询一次。请对照商家邮件检查单号：应为 8 位数字，或 4 位字母加 5 至 8 位数字。如果几天后仍无任何记录，请联系商家确认包裹是否真正发出。

**停留在“已生成面单”。** 这说明 Border Express 已有包裹信息，但还没有带日期的扫描。它的追踪页可能显示不带时间的 `Picked Up`，Peek 会略去这一步，所以先去那里看看。如果仍只显示 `Shipment Data Received`，请向商家询问何时交寄。

**多日没有更新。** 当包裹处于“已生成面单”、“已揽收”或“运输中”且连续四天没有新节点时，Peek 会用该节点的日期标示：“自10月12日周一起没有物流更新”。Peek 会继续向 Border Express 查询，一有新节点便显示出来。原因分析见 [物流为何停止更新](guide:tracking-not-updating)（英文）。

**部分送达（Partially Delivered）。** 请对照订单清点已收到的物品。该票货运的其余包裹仍在路上；Border Express 的追踪页会逐件展示每件包裹的扫描记录，而 Peek 不会显示分件明细。

**显示已送达但没收到。** 先查看收货地址周边并询问邻居。Border Express 的追踪页会显示签收人姓名，Peek 则不显示此信息。确认未找到后请联系商家处理。详见 [显示已送达却未收到包裹](guide:delivered-not-received)（英文）。

**丢失、破损或退件。** 是商家向 Border Express 下单委托运输，因此退款或补发需由商家负责。可参阅 [包裹丢失](guide:lost-parcel)（英文）、[包裹破损](guide:damaged-parcel)（英文） 和 [退回寄件人](guide:returned-to-sender)（英文）。

## Border Express 联系方式与客服

由于 Peek 未能查阅 Border Express 网站，本页面不列出电话号码或电子邮箱：Peek 只列出在快递公司自己的页面上读到的联系方式。运输由商家预订，所以请先联系商家。

在 Peek 的包裹详情页中，点击“打开 Border Express 官网”即可直接在 Border Express 追踪页上打开你的单号。若收到要求付款的包裹短信，在支付任何费用前请先阅读 [防范快递诈骗短信](guide:delivery-scam-texts)（英文）。

## 使用 Peek 追踪 Border Express 包裹

将单号粘贴到 [Peek](/)，Peek 跟踪 4,500 多家快递公司。查询期间可能会显示“正在查询物流服务…”。然后点“更换”并选择 Border Express：在搜索框中输入 Border Express。输入 BEX 也能搜到，但排在最前面的是塞尔维亚的快递公司 BEX Express。若你输入的是标签条码，Border Express 会出现在“符合这个单号”下。对于已添加的包裹，请使用“更换快递公司”。无需账号、邮编或手机号。

如果你添加单号时没有选择快递公司，Peek 不会查询 Border Express 的追踪页：它会向综合追踪服务查询（4 位字母加 8 位数字时还会询问 Colis Privé），在其中一方查到包裹前会显示“暂无物流记录”。只有在综合追踪服务指出 Border Express 时，Peek 才会接着查询 Border Express。自己选择 Border Express，才能获得其追踪页上的节点。

直接粘贴 Border Express 的追踪链接同样有效，并会立即识别出 Border Express（“识别出的快递公司”）：包括带有 `cn=` 的 borderexpress.com.au/tracking 链接，或 bexonline.borderexpress.com.au/bxo/track 链接。如果你为其他格式的单号选择 Border Express，Peek 会提示它“不使用这种格式的单号”；添加后，除非综合追踪服务查到了该包裹，否则会显示“请检查运单号”：请核对是否漏输或多输了字符。

**标签条码处理。** Border Express 的追踪页不接受标签条码，因此 Peek 会从条码中算出运单号：保留前 4 位字母，去掉 BX 后 11 位数字开头多余的 0（但至少保留 6 位数字），同时舍去最后 3 位件数序号。包裹详情页会在“Border Express 记录的单号为”下方展示提取出的单号，“打开 Border Express 官网”链接也会带上该运单号跳转。如果 Border Express 凭该提取号查不到包裹，请改用商家邮件中的原始运单号。

**Peek 展示的信息。** Peek 会展示当前状态、每条轨迹节点及其郊区和州名、签收前的预计送达日期以及实际送达时间。Peek 不显示进度条、发货日期、总件数及各分件的独立扫描、寄件人城镇与邮编、你的邮编以及签收人姓名；在包裹运输途中，地图会标注目的地城镇。Peek 对 Border Express 不提供重量或签收凭证。每条节点都按所在州的本地时钟读取，因此跨时区的轨迹也能按正确先后顺序排列。

**刷新频率。** 当你在 Peek 中打开该包裹，或在派送当日开启通知时，Peek 最多每 10 分钟检查一次；在 Border Express 扫描包裹出库派送的当天，只要 Border Express 自己的追踪页有响应，会从最新节点所在州时钟的早上 6 点起每 2 分钟检查一次。其余情况下，以及连续两天没有新动态后（包裹仍显示“已生成面单”或“已揽收”时为 12 小时），每小时检查一次；在“已生成面单”停留七天且无新动态后，每 6 小时检查一次。如果无法连上 Border Express 的追踪页，Peek 会改为查询追踪服务，并按其原文显示它们掌握的信息。你可以选择“每次扫描”、“重要环节”或“仅派送当天”通知。Peek 无法替你联系 Border Express 或商家。

## 关于 Border Express 物流查询的问题

### 什么是 Border Express 运单号（consignment number）？

这是 Border Express 用来追踪整票货运的编号，格式为 8 位数字，或 4 位寄件人字母前缀加数字。该票货里的所有包裹都归在这个编号之下，可在商家的发货邮件或订单页面中找到。

### 可以用包裹标签上的条码追踪 Border Express 吗？

在 Peek 中可以：粘贴 20 位字符的条码（4 位字母、BX 与 14 位数字）并选择 Border Express，Peek 会从中算出运单号。Border Express 自己的追踪页只接受运单号。

### Border Express 追踪显示 “On Board For Delivery” 是什么意思？

表示包裹已经装上派送车，正在送货途中。Peek 会将其显示为“派送中”。

### 为什么 Border Express 追踪显示 “Partially Delivered”？

因为你的这票货运包含多件包裹，目前只送达了其中一部分，其余包裹仍在路上，所以 Peek 仍显示为“运输中”。`Partially Delivered - Final` 则不同：意味着 Border Express 已按实际送达的件数结束这票运单，Peek 会显示为“已签收”。

### Border Express 追踪显示 “Shipment Data Received” 是什么意思？

表示寄件人已向 Border Express 传输了包裹电子信息，但 Border Express 还没有扫描到包裹实物。Peek 会将其显示为“已生成面单”。

:::sources
- [Universal Parcel Scraper: Border Express](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/border-express) – 名称、澳大利亚范围、运单号与标签条码格式、追踪链接、追踪接口、状态措辞与阶段对应、无时间节点、各州时区、预计送达日与签收时间、Peek 省略字段及不涉及其他承运商
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – 其他承运商单号格式（Mondial Relay、Colisweb、Heppner、Colis Privé）、BEX Express 名称、无转交其他承运商记录
:::
