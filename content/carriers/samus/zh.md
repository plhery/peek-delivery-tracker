---
title: SAMUS 快递查询：单号、立陶宛语状态与维尔纽斯自提
description: 查询立陶宛 SAMUS 信件与包裹：13 位 LT 单号格式、立陶宛语物流状态翻译、维尔纽斯自提点位置、理赔时限及客服电话。
slug: samus-tracking
published: 2026-11-27
updated: 2026-11-27
---

SAMUS（UAB „SAMUS“）是一家成立于 2011 年的立陶宛信件与包裹快递公司，专门为签约企业客户派送挂号信、特快信函、需本人签收的信件以及立陶宛境内和发往海外的特快包裹。你可以在 [samus.lt 的“Parcel tracking”页面](https://www.samus.lt/en/parcel-tracking/)查询，或将单号粘贴到上方输入框。

## SAMUS 快递单号是什么格式的

SAMUS FAQ 说明，包裹单号印在企业客户通过其自助系统生成的面单上，因此请向发件公司索取单号。

SAMUS 面向企业客户的文档展示了面单单号与条码的标准格式：2 位字母、9 位数字加 `LT`，共 13 位字符，与立陶宛邮政的国际单号格式一致。以下以虚构数字举例：

| 单号（虚构示例） | 含义 |
| --- | --- |
| `RD482917367LT` | 2 位字母、9 位数字加 `LT`：SAMUS 技术文档中给出的格式。Peek 按邮政单号规则将第 9 位数字作为校验位。 |
| `RD482917360LT` | 格式相同但校验位有误：Peek 识别不出任何快递公司。请核对单号或自己选择 SAMUS（见下文）。 |

粘贴到 Peek 时，空格、连字符和小写字母不影响识别。在 samus.lt 上，查询结果会显示“Parcel number”和“Parcel code”；若页面提示 `Your letter with number "…" is not found`，请向寄件方核实单号。

如果单号为其他格式，实际承运方可能是 [Lietuvos paštas](carrier:lietuvos-pastas)、[Omniva](carrier:omniva) 或 Venipak。可参阅[快递单号格式指南](guide:tracking-number-formats)（英文）加以区分。

## SAMUS 物流状态含义

SAMUS 在追踪页仅提供立陶宛语状态，其英文页面同样显示立陶宛语原文。主要状态如下：

| samus.lt 显示状态 | 对你的含义 |
| --- | --- |
| `Siunta įvesta savitarnoje` | 寄件人已在 SAMUS 自助系统录入货件，SAMUS 尚未拿到实物。 |
| `Siunta paruošta išsiuntimui` | 货件已准备发运，仍在寄件人处。 |
| `Siunta neperduota kurjeriui surinkimo metu` | 快递员上门揽收时，寄件人未交出货件。 |
| `Siunta surinkta iš siuntėjo` | 快递员已从寄件人处揽收。 |
| `Siunta rūšiavimo centre` | 位于 SAMUS 分拣中心。 |
| `Siunta pas kurjerį` | 快递员已取件准备派送。 |
| `Gavėjui paliktas pranešimas` | 快递员未见到你并留下了通知单。 |
| `Gavėjas atvyks atsiimti į terminalą` | 将由你本人前往站点自提。 |
| `Siunta palikta UAB Samus Žalgirio g. 92 (vidinis kiemas), Vilnius` | 已存放在维尔纽斯 SAMUS 自提点待取（克莱佩达另有类似记录，地址为 Minijos g. 49）。 |
| `Siunta sėkmingai įteikta` | 已送达。 |
| `Siunta sėkmingai įteikta, įteikimo pranešimas grąžinamas siuntėjui` | 已送达，签收回执正寄回寄件人。 |
| `Siunta neįteikta, neatsiliepė į pranešimą` | 未送达：无人回应通知单。 |
| `Siunta neįteikta (…)` | 未送达，括号内注明原因（拒收、地址有误、查无此人、寄件人取消、收件人搬迁、仅注册地址、监狱、信箱损坏等）。 |
| `Siunta išsiųsta iš Lietuvos` | 已离境立陶宛，后续记录将跟进目的国分拣中心、海关及邮局。 |
| `Siunta perduota Lietuvos paštui` | SAMUS 已将货件转交立陶宛邮政（Lietuvos paštas）。 |
| `Siunta perduota į tranzitinį paštą` | SAMUS 已转交中转邮政继续运送。 |
| `Siunta peradresuota` | 已改派至其他地址。 |
| `Siunta neįteikta. Grąžinama siuntėjui` | 未送达，正在退回寄件人。`Siunta grąžinta siuntėjui` 表示已退回。 |
| `Siunta pamesta` | 货件已丢失。 |

Peek 无法直接读取 SAMUS 追踪页，因此不会显示上述立陶宛语原文，详见下文“使用 Peek 追踪 SAMUS 包裹”。通用物流状态含义见[物流状态详解](guide:tracking-statuses)（英文）。

## SAMUS 派送与自提规则

**派送时效。** SAMUS FAQ 说明，立陶宛境内标准快递为次一工作日送达，并可加购当日达或上午 10:00、12:00 前送达。其服务条款规定信件及 S、M、L 规格货件在立陶宛境内需 2 至 6 个工作日，欧盟境内最长 10 个工作日，欧盟以外最长 20 个工作日；立陶宛境内特快 S 件于次一工作日 17:00 前送达。

**投递信箱或签收。** 无追踪信件和 S 件直接投入信箱。可追踪货件须由你本人或你授权的人签收。寄往公司的货件可由任一员工代签；若无法当面交收件人，可交由前台、值班人员或保安。M 和 L 规格货件一律需签收。

**派送时不在场。** 每次尝试后快递员都会留通知单；若面单留有手机号，快递员可能致电约定时间地点。服务条款规定快递员等候不超过 10 分钟。通知单会写明自提地点，请与快递员确认取件时间。信件自留单起由快递员保管 14 个工作日，逾期退回寄件人；特快包裹在站点免费保管 3 天。

**SAMUS 维尔纽斯自提点。** 地址为 Žalgirio g. 92, LT-09303 Vilnius，位于内院一楼。请认准橙色外墙建筑，穿过楼下通道进入内院，找到标有“Siuntų išdavimo vieta”（包裹自提点）的指示牌和带 SAMUS 标志的门。从市中心或火车站可乘 1G 或 53 路公交至 Kalvarijų g. 上的“Žalgirio st.”站，或乘 10、34 路步行几分钟到达。

**当面验货。** 请当着快递员面查验货件状态，如有破损务必写在运单上并填写破损报告；无异议签收即视为完好送达。务必保留原包装，否则可能丧失索赔权利。

**更改收件地址。** 交寄后，寄件人可尽早付费申请由 SAMUS 修改收件地址，请联系寄件人办理。

**发往海外的包裹。** SAMUS 通达欧洲多数国家及全球其他地区，特快包裹通常需 1 至 5 个工作日，轨迹可能显示目的国海关环节。除非运单另行指定由寄件人或第三方支付，否则关税和税费由收件人承担。标准包裹最大重量为 30 公斤。

## SAMUS 包裹延误、未送达或丢失怎么办

**物流无更新。** 请先在 samus.lt 查询；若状态不明或查无记录，SAMUS 建议发送邮件至 info@samus.lt 或致电 +370 602 33354 协助排查。如立陶宛境内派送延误，按条款 SAMUS 向其签约客户（寄件人）退还运费。更多见[物流为何停止更新](guide:tracking-not-updating)（英文）。

**丢失或破损。** 只有寄件人或其授权人可在寄出后 6 个月内向 SAMUS 索赔。立陶宛境内件在 30 天内赔付，国际件在 90 天内赔付；特快包裹丢失或破损最高赔付 200 欧元；无追踪邮件不受理索赔。因此订单出问题时请联系发货公司。另见[包裹丢失](guide:lost-parcel)（英文）。

**退回寄件人。** 无法派送且无其他指示的货件会退回寄件人。参阅[包裹被退回寄件人](guide:returned-to-sender)（英文）。

**显示已送达但没收到。** 寄往公司地址的货件可能已由同事、前台或保安代签；否则请联系寄件人。参阅[显示已签收但未收到](guide:delivered-not-received)（英文）。

**争议处理。** SAMUS 条款规定所有争议依据立陶宛法律解决。

## SAMUS 客服与联系方式

信息来自 samus.lt：

- **主客服电话：** +370 602 33354。
- **电子邮箱：** 一般咨询 info@samus.lt；包裹查询 paieska@samus.lt。
- **维尔纽斯办公室与自提点：** Žalgirio g. 92, LT-09303 Vilnius。
- **其他办事处：** 考纳斯 Partizanų g. 19、克莱佩达 Minijos g. 49-10（klaipeda@samus.lt）、希奥利艾 Trakų g. 20。
- **服务时间：** 服务条款注明立陶宛时间周一至周五 8:00 至 17:00（联系页面本身未标注时间）。

来信请附上单号；如涉及订单本身、退货或退款，请联系发货公司。

## 使用 Peek 追踪 SAMUS 包裹

[Peek](/) 无法单凭号码识别 SAMUS：2 位字母、9 位数字加 `LT` 在 Peek 中属于立陶宛邮政格式。添加单号时（显示“正在查询物流服务…”），Peek 会先查询识别该格式的立陶宛邮政（Lietuvos paštas）和 Chronopost，再查询追踪服务。若其中一家查到记录，列表会标出对应快递公司；若追踪服务标明为 SAMUS 且含真实流转记录，Peek 会选定 SAMUS；若暂未查到，则会显示“物流查询服务暂未找到。添加后 Peek 会继续查询。”

如需直接归为 SAMUS，请点击“更换”并在“搜索快递公司”中输入“SAMUS”（输入“UAB SAMUS”也可找到；它不会出现在“符合这个单号”下）。对已添加货件请点击“更换快递公司”。

你也可以直接粘贴 SAMUS 立陶宛语追踪页链接（以 `/siuntu-sekimas/?id=` 加单号结尾），Peek 会提取单号并直接识别为 SAMUS（“识别出的快递公司”）。英文追踪页链接 `/en/parcel-tracking/?id=` 不会让 Peek 识别出 SAMUS（仅在单号满足邮政格式时将其视为邮政单号），建议直接粘贴单号或立陶宛语链接。

归入 SAMUS 名下后，Peek 会依次查询 ParcelsApp、Ship24 和 17TRACK 并保留最先查到记录的一家；若单号校验位有效，还会最后查询万国邮联（UPU）。在添加后的前 30 天运输期内，Peek 还会先询问立陶宛邮政和 Chronopost，一旦其中之一出现真实进展，Peek 会把货件转到那家快递公司（提示“已自动从 SAMUS 切换”）。Peek 按追踪服务原文展示状态和历史，可能落后于 samus.lt，部分货件也可能暂无数据。如果你让 Peek 来判断快递公司，而追踪服务标明为 SAMUS 且有显示货件在流转的扫描，Peek 会为你选定 SAMUS。在追踪服务查到前，Peek 的外链打开带单号的 SAMUS 查询页，查到后改为打开该追踪服务页面。Peek 不会向追踪服务传递你的 IP 地址或所在国家。

在追踪服务收录单号前，归入 SAMUS 的货件显示“等待 SAMUS 更新”，未查到的服务隔天重试。一旦查到记录，Peek 每小时检查一次；连续一天无新扫描后改为每 3 小时一次；停留在“已生成面单”满七天后改为每 6 小时一次。出库派送当天只要你打开页面或开启通知，最高每 15 分钟检查一次。

当货件在“已生成面单”、“已揽收”或“运输中”状态且最新记录满四天时，Peek 会标出日期（如“自10月12日周一起没有物流更新”），显示“包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 SAMUS 查询。”及跳转至 SAMUS 联系页的“向 SAMUS 寻求帮助”链接。超过三周无新记录时则显示“已超过三周没有新动态。如果还没收到，请联系卖家。Peek 会持续向 SAMUS 查询。”

Peek 通知可选“每次扫描”、“重要环节”或“仅派送当天”，但无法代你联系 SAMUS 或寄件人。

## SAMUS 快递查询常见问题

### 为什么 Peek 会用我的 SAMUS 单号去查立陶宛邮政（Lietuvos paštas）？

因为 SAMUS 单号采用与立陶宛邮政相同的 13 位格式，Peek 无法单凭外形区分。请点击“更换”搜索“SAMUS”，或粘贴 SAMUS 立陶宛语查询页链接。

### 维尔纽斯的 SAMUS 自提点在哪里？

位于 Žalgirio g. 92, LT-09303 Vilnius 的内院中，请认准“Siuntų išdavimo vieta”指示牌。乘坐 1G、53、10 或 34 路公交下车步行几分钟即可到达。

### 错过的 SAMUS 信件会保管多久？

留单后快递员会保管信件 14 个工作日，逾期退回寄件人；特快包裹则在站点免费保管 3 天。

### 收件人可以直接向 SAMUS 索赔吗？

不可以。只有寄件人或其授权人可以在寄出后 6 个月内提出索赔，请联系发货公司处理。

### 如何联系 SAMUS？

请在立陶宛时间周一至周五 8:00 至 17:00 拨打 +370 602 33354，或发邮件至 info@samus.lt（包裹专项查询发送至 paieska@samus.lt）。

:::sources
- [SAMUS: Laiškai ir siuntos](https://www.samus.lt/) – letters and parcels, self-service
- [SAMUS: Kompanija](https://www.samus.lt/kompanija/) – in the Lithuanian market since 2011
- [SAMUS: Kurjerių paslaugos](https://www.samus.lt/paslaugos/kurjeriu-paslaugos/) – letters, signed delivery, express parcels
- [SAMUS: Parcel tracking](https://www.samus.lt/en/parcel-tracking/) – tracking form, not-found message, Lithuanian statuses
- [SAMUS: Siuntų sekimas](https://www.samus.lt/siuntu-sekimas/) – Lithuanian tracker, link with `id`, status list
- [SAMUS: Frequently asked questions](https://www.samus.lt/en/information/faq/) – number on the label, delivery times, extras, address change, damage, abroad, late items
- [SAMUS: Contacts](https://www.samus.lt/en/contacts/) – phone, emails, Vilnius pickup point, offices
- [SAMUS: Paslaugų teikimo taisyklės](https://www.samus.lt/informacija/taisykles/) – hours, delivery times, signature, notices, storage, damage, duties, claims, disputes (in Lithuanian)
- [SAMUS: SAMUS API v2.12](https://api-documentation.samus.lt/) – number and barcode form, barcode printed on the label
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – no SAMUS number shape, postal reading of S10 numbers, tracker links, names, tracking services
:::
