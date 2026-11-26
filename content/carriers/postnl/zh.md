---
title: 荷兰邮政（PostNL）查询：NL 与 3S 单号、状态与派送
description: 查询境外荷兰邮政（PostNL）与 Spring GDS 包裹：NL 和 3S 单号格式、postnl.post 英文状态对照、当地末端派送与客服途径。
slug: postnl-helan-kuaidi-chaxun
published: 2026-11-26
updated: 2026-11-26
---

荷兰邮政（PostNL）是荷兰的国家邮政机构。它不仅将荷兰网店的包裹和信件寄往海外，还通过其国际业务部门 Spring GDS 为全球电商卖家承运包裹。两类包裹均可在荷兰邮政国际查件网站 [postnl.post](https://postnl.post/) 查询，或直接粘贴到上方查询框中。包裹进入你所在国家后，通常由当地快递公司或国家邮政完成末端派送。运单号位于商家的发货邮件或订单页面里。

## 荷兰邮政单号格式

根据荷兰邮政开发者文档，其使用两类条码单号（以下数字为虚构示例）：

| 单号 | 说明 |
| --- | --- |
| `CW482910576NL` | 2 位字母、9 位数字加 NL：荷兰邮政国际邮政单号。C 开头为发往欧盟以外国家（如英国、美国、加拿大或澳大利亚）的包裹。 |
| `LA482910576NL` | L 开头：可追踪小包或信件；`LW` 对应同项服务。 |
| `RI482910576NL` | R 开头：挂号信；`RF` 对应同项服务。 |
| `UL482910576NL` | U 开头：无追踪的小包。到达你所在国家后，不要指望会有扫描记录。 |
| `3SABCD482910573` | 3S、字母客户代码加数字，共 15 位字符：PostNL 包裹条码。 |
| `3SABCD4829105` | 同类条码的 13 位形态。PostNL 将其用于发往爱尔兰等其他欧盟国家的 Parcel EU 包裹服务。 |

NL 单号 9 位数字的最后一位是校验位，输错一位通常就会导致单号无效。

postnl.post 一次最多可查询 150 个单号。它接受 NL 单号和 15 位 3S 条码，但会拒绝 13 位 3S 条码并提示 “The shipment barcode was incorrect.”。遇到这类情况（例如发往爱尔兰的 Parcel EU 条码），请向商家索要境外派送方的单号。商家预订 Parcel EU 面单时，PostNL 会提供境外末端派送方的条码，可直接在对方网站追踪；但在包裹仍处于荷兰境内期间，该条码仍可能变更。

## 荷兰邮政物流状态含义

postnl.post 顶部设有进度条（`Pre-advised`、`Preparing`、`Departed`、`Transit`、`Arrived`、`Customs`、`Processing`、`Delivered`），下方以英文列出扫描记录，大多数节点会标明发生所在国家。包裹会遇到的文案大致按顺序如下：

| postnl.post 显示状态 | 对你的含义 |
| --- | --- |
| `Pre-advised`、`Preparing` | 寄件人已预订并向 PostNL 上传数据，PostNL 可能尚未拿到包裹。 |
| `Consignment received at the PostNL Acceptance Centre`、`The shipment is handed over in bulk final acceptance of the item to be confirmed` | PostNL 已接收寄件人的整批交货，单件包裹本身的扫描可能还未出现。 |
| `The item is received by PostNL` | PostNL 已完成你的单件扫描；可能出现在收件中心节点几天之后。 |
| `Departed`、`Transit` | 正在跨国运输途中，通常为空运。 |
| `Arrived` | 已抵达途经国或目的国，通常为你所在国家。 |
| `Customs` | 正在目的国海关处理。 |
| `The item has arrived at the domestic sorting centre`、`The item is at the local sorting centre` | 正在节点旁标注国家的分拣中心分拣。 |
| `The item is at the local delivery office` | 已抵达负责派送的当地网点。 |
| `The item is out for delivery` | 正在派送途中。 |
| `Driver is en route to the pickup location` | 正送往自提点，而非送货上门。 |
| `Pick-up point` | 已在自提点等待领取。 |
| `Unsuccesfull`、`Undelivered` | 派送尝试失败（`Unsuccesfull` 为 PostNL 原文拼写）。 |
| `Undeliverable item, has been returned to shipper` | 无法投递，正退回寄件人。 |
| `The item has been returned and arrived at PostNL` | 退件已回到 PostNL。 |
| `Delivered` | 已签收送达。 |

`The shipment barcode was not found.` 表示 postnl.post 暂未收录该单号，或某个字符输入有误。结果页显示 “Destination: unknown” 表示尚无扫描记录注明目的国。如果你在 Peek 中为 3S 包裹填写了收件邮编，Peek 还会读取 PostNL 自己的查件系统，其英文节点文案不同：境外派送包裹会出现 `Your parcel has crossed the border`、`Your parcel's at a sorting centre in the destination country`、`Your shipment is on its way to the recipient`（派送中）和 `We missed you; we’ll try again`（派送未成功）。

## 荷兰邮政在你所在国家由谁派送

PostNL 或 Spring GDS 负责把包裹运到你所在国家。Spring GDS 表示其“负责国际干线，当地末端派送交由本地伙伴完成”，包裹入境后“通常转交当地国家邮政（如 USPS、Royal Mail、Correos）”。

- **美国：** [USPS](carrier:usps)。境外邮件的查单需由寄件人通过其本国邮政发起。发往美国本土及波多黎各的商业包裹，商家可签署补充协议由 PostNL 代办清关并预缴进口关税。
- **英国：** [Royal Mail](carrier:royal-mail)（Spring GDS 点名的当地邮政之一）。
- **爱尔兰：** 邮政邮件由 An Post 派送；Parcel EU 条码则交由 PostNL 在爱尔兰的派送服务商派送，PostNL 没有说明是哪家。
- **加拿大：** [Canada Post](carrier:canada-post)。如果其 Track 工具查不到记录，Canada Post 要求由寄件人联系原寄国邮政发起调查。
- **澳大利亚：** [Australia Post](carrier:australia-post)。

NL 单号遵循万国邮政联盟（UPU）S10 标准（各国邮政互换邮件共用的编号规则），因此包裹入境后你可以直接用同一单号在当地邮政官网查询；不过无追踪的 `UL` 平邮小包在当地可能查不到任何记录。

派送尝试次数、纸质通知单和保管天数均按当地承运商规则执行，见[派送未成功怎么办](guide:missed-delivery)（英文）和[无人认领包裹](guide:uncollected-parcel)（英文）；关税和税费见[包裹卡在海关怎么办](guide:customs)（英文）。

## 荷兰邮政包裹不更新、丢失或退回

**暂无扫描。** `Pre-advised` 或 `Preparing` 表示卖家已预报但可能尚未交寄。Spring GDS 建议拿到单号后预留 24 至 48 小时等待首次扫描。

**运输途中停滞。** `Departed` 或 `Transit` 之后，空运或海运途中不会产生扫描，直到落地到达；海关也常停留数日。之后可用同一单号在当地国家邮政查询。当包裹在“已生成面单”、“已揽收”或“运输中”连续四天无新扫描时，Peek 会在列表标出最新扫描日期（“自10月12日周一起没有物流更新”），并在详情页提示：“包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 PostNL 查询。”并附上直达客服的“向 PostNL 寻求帮助”链接。超过三周无扫描时，提示变为：“已超过三周没有新动态。如果还没收到，请联系卖家。Peek 会持续向 PostNL 查询。”更多见[物流长时间不更新怎么办](guide:tracking-not-updating)（英文）。

**显示已签收但未收到。** Spring GDS 建议检查邻居处、门廊或车库等安全放置点及大楼前台，同时指出承运商有时会提前最多 24 小时标记已签收。排查后仍未找到请联系卖家。见[显示已签收但未收到包裹](guide:delivered-not-received)（英文）。

**丢失或破损。** 立即通知卖家，并拍下破损包装照片：卖家需要照片才能向 Spring GDS 提出索赔。见[包裹丢失怎么办](guide:lost-parcel)（英文）。

**退回寄件人。** 联系卖家重发或退款。Spring GDS 列出常见退件原因为 “Incorrect Address”（地址错误）和超保管期 “Unclaimed”（无人认领），并说明退件一旦启动便无法逆转。见[包裹退回寄件人](guide:returned-to-sender)（英文）。

## 荷兰邮政客服与联系方式

以下为 PostNL 与 Spring GDS 官网说明的咨询途径：

- **postnl.post：** 页面提示“荷兰境外的派送问题请联系你的网店”；荷兰境内派送则引导至 PostNL 客服。
- **Spring GDS：** 出于数据隐私及与卖家的合同限制，不直接与个人消费者讨论快件；卖家持有账号并负责向 Spring GDS 发起调查。
- **当地承运商：** 派送未成功或预约新派送日请直接联系当地派送方，Spring GDS 无法安排当地重派。

其他任何问题，直接联系下单的网店或电商平台都是最快途径。

## 使用 Peek 追踪荷兰邮政包裹

[Peek](/) 会立刻识别校验位有效的 NL 单号和带 4 位字母客户代码的 3S 条码。带 3 位字母的 3S 条码会归入 [DHL eCommerce 荷兰](carrier:dhl-ecommerce-nl)（尽管 PostNL 自身文档也展示了 3 位字母代码）。客户代码更短时，Peek 无法单凭形态确定承运商（2 位字母时会同时询问 DHL eCommerce 荷兰）：照常粘贴即可，Peek 跟踪 4,500 多家快递公司，查询期间会显示“正在查询物流服务…”；如果匹配了其他快递，点“更换”手动选 PostNL 即可。来自 postnl.post（`/track?barcodes=`）和 Spring GDS 查件页 mailingtechnology.com（`/tracking?tn=`）的链接都会直接识别为 PostNL。输入 3S 条码时，表单还会提供可选的“收件邮编”栏，并提示：“PostNL 仅在填写收件邮编后才显示荷兰境内寄送的包裹。”填写邮编（荷兰以外邮编还需选择国家）后，Peek 会优先询问 PostNL 自己的追踪系统；未填邮编则改查其他来源。

Peek 会读取 postnl.post，并在你提供邮编时读取 PostNL 自己的追踪系统，展示当前状态、带所在国家的轨迹记录、签收时间与目的国。时间按你设备的时区显示（在 peektracker.com 及 iPhone、Android 应用中，轨迹底部会写明“时间按你所在的时区显示：”及对应时区名称）；在跨多个时区的国家（如美国、加拿大、澳大利亚），Peek 只能假定其中一个时区，因此部分扫描可能相差数小时。PostNL 不提供预计送达日和寄件人信息。对于 NL 单号，一旦 PostNL 标出目的国，Peek 也会用同一单号询问当地国家邮政（如 Royal Mail 或 USPS），并在对方出现更新进展后切换过去。由于 postnl.post 会把 13 位 3S 条码判为无效，未填邮编时 Peek 会经由[通用物流查询服务](guide:universal-tracker)（英文）查找该包裹。

Peek 最高每 30 分钟查询一次 PostNL（派送当天同样如此），手动刷新则可更早检查；当其他承运商接手派送时，Peek 会改按派送方的节奏查询。Peek 可按每次扫描、重要环节或仅派送当天发送通知，但无法代你联系 PostNL 或商家。

## 荷兰邮政查询常见问题

### 我的 PostNL 包裹现在在哪里？

最新一条扫描会告诉你包裹当前所处阶段和所在国家。一旦轨迹显示已进入你的国家，建议用同一单号去当地国家邮政网站查询，那里通常有更详细的本地节点。

### “The shipment barcode was incorrect” 是什么意思？

表示 postnl.post 不接受该条码。这通常是 PostNL Parcel EU 服务使用的 13 位 3S 条码，请向商家索要末端派送方的单号并在对方网站查询。

### PostNL 寄往美国或英国的包裹由谁派送？

通常由当地国家邮政派送。PostNL 国际业务部门 Spring GDS 说明，包裹入境后常转交当地邮政服务（如美国的 USPS 或英国的 Royal Mail）。轨迹显示到达你的国家后，可用同一个 NL 单号在当地邮政网站查询。

:::sources
- [PostNL: International Tracking](https://postnl.post/) – 150 个单号批量查询、进度条、扫描文案、“not found”与“incorrect”提示、目的国及联系网店指引
- [PostNL Developer: Barcode guidelines](https://developer.postnl.nl/docs/#/http/api-endpoints/send-track/barcode/guidelines) – 13 位与 15 位 3S 条码、字母客户代码及 S10 NL 号段
- [PostNL Developer: Product codes International](https://developer.postnl.nl/docs/#/http/reference-data/product-codes-international) – Parcel EU 13 位 3S 及目的地、境外派送方条码、非欧盟包裹 C/CW、LA/LW、RI/RF、UL 及美国清关预缴关税补充协议
- [Spring GDS: We are Spring GDS](https://www.spring-gds.com/about-us/) – 隶属 PostNL 集团及跨境电商业务
- [Spring GDS: Track and Trace](https://www.spring-gds.com/track/) – 当地邮政、当地派送方、单号格式、首次扫描、运输与海关停留、已签收未收到、索赔、退件、重派及不直接对接个人收件人
- [Spring GDS: Contact us](https://www.spring-gds.com/contact-us/) – 查件问题转交网店处理
- [Canada Post: Tracking international packages](https://www.canadapost-postescanada.ca/cpc/en/support/kb/tracking/tracking-international-packages.page) – 寄件人通过本国邮政发起调查
- [USPS: International Inquiries – The Basics](https://faq.usps.com/s/article/International-Inquiries-The-Basics) – 非美国寄件人须联系本国邮政查单
- [UPU: S10 standard, Identification of postal items](https://www.upu.int/UPU/media/upu/files/postalSolutions/programmesAndServices/standards/S10-12.pdf) – 各国邮政共用编号标准与校验位
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Peek 读取和显示的信息
:::
