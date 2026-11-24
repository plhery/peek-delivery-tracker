---
title: 出口易（Chukou1 / CK1）单号查询：EC-FirstClass 轨迹与客服
description: 查询出口易（Chukou1 / CK1）EC-FirstClass 跨境包裹：18 至 20 位处理号结构、英文扫描状态、末端快递交接及中国官方客服指南。
slug: chukou1-chukouyi-chaxun
published: 2026-11-24
updated: 2026-11-24
---

Chukou1（*CK1*，中文名*出口易*，运营主体为*广州市贝法易信息科技有限公司* / *BFE*）是中国大型跨境电商物流与海外仓服务商，总部位于广州，在深圳、上海和武汉设有分公司，在东莞和义乌设有处理中心。出口易在美国、英国、德国、法国、波兰、加拿大、澳大利亚、秘鲁和阿根廷运营海外履约仓，同时面向 Amazon、eBay、AliExpress、SHEIN、TikTok Shop、Walmart 和 Wayfair 等平台卖家提供欧美超级专线、全球特惠专线、拉美专线及 TEMU-Y2 专线等国际小包线路，并通过 **EC-FirstClass** 平台（[ec.chukou1.com](https://ec.chukou1.com/)，单次最多查询 100 个单号）提供包裹追踪。你可以在该平台查询，或直接把处理号粘贴到上方查询框中。

## 出口易（Chukou1）单号格式（EC-FirstClass 处理号）

出口易处理号可在电商订单详情或发货邮件中查看。Chukou1 自有处理号总长 18 至 20 位，依次由 3 位字母服务前缀、6 位 `YYMMDD` 下单日期、3 或 4 位商家代码和 6 或 7 位流水号构成（以下均为虚构示例）：

| 单号（虚构） | 归属与说明 |
| --- | --- |
| `CZN261015ABCD482917` | 19 位出口易处理号（`CZN` + 日期 `261015` + 4 位客户码 `ABCD` + 6 位流水号）。 |
| `CUE261015KLM4829173` | 19 位出口易处理号（`CUE` + 日期 `261015` + 3 位客户码 `KLM` + 7 位流水号）。 |

完成出口清关后，出口易会将包裹移交给后续承运商——EC-FirstClass 官网列举的合作承运商包括[中国邮政](carrier:china-post)、USPS、DHL、[FedEx](carrier:fedex) 和 UPS 等——并在你的处理号下同时展示出口易首程轨迹与合作承运商回传的扫描记录。详见[运单号格式说明](guide:tracking-number-formats)（英文）。

## 出口易（EC-FirstClass）物流状态含义解析

Chukou1 将整体状态（`InProcess`、`InTransit`、`Delivered`）与首程英文节点及目的国承运商的回传扫描结合展示，每条记录使用对应扫描地点的本地时间：

| Chukou1 显示文案 | 对你的含义 | Peek 显示 |
| --- | --- | --- |
| `Shipment information received`、`Carrier received the manifest` | 已收到卖家电子订单预报，出口易仓库尚未收到实物包裹。 | “已生成面单” |
| `Item received by Logistics Centre`、`Carrier received the shipment` | 包裹已送达并扫描进入出口易的中国始发仓库。 | “已揽收” |
| `Item was sent out from Logistics Centre`、`Transfer to Airport of Origin`、`Departed from Airport of Origin`、`Arrived at Airport of destination`、`Shipment started the customs clearance process at origin` | 已离开中国仓库、运往始发机场完成出口并抵达目的国机场。 | “运输中” |
| `Customs clearance in progress`、`Shipment started the customs clearance process at destination`、`Shipment is awaiting customs clearance at destination` | 正在目的国办理进口海关查验与清关。 | “清关中” |
| `Customs declaration completed`、`Turned over to last mile facility`、`Last mile Ascan`、`Arrived at Post Office` | 已完成进口清关，移交当地末端快递并完成对方入网扫描。 | “运输中” |
| `Shipment is out for delivery` / `Out for Delivery` | 当地末端承运商已装车开始派送。 | “派送中” |
| `Shipment is available for pickup` | 包裹已到达当地邮局或自提点等待领取。 | “待取件” |
| `Wrong Address`、`Refused`、`Cannot be delivered` | 因地址有误、拒收或无法投递导致派送失败。 | “派送未成功” |
| `Return To Sender`、`Delivered To Sender` | 正在退回寄件人或已退回并由寄件人签收。 | “已退回寄件人” |
| `Lost` | 轨迹标记为在途丢失异常，请立即联系卖家。 | “需要留意” |
| `Shipment delivered to customer` / `Delivered` | 已由目的国合作快递投递签收（如有记录会附签收人）。 | “已签收” |

## 跨境运输、末端交接与清关说明

根据 [chukou1.com](https://www.chukou1.com/) 和 [ec.chukou1.com](https://ec.chukou1.com/)，出口易是面向商家的 B2B 跨境集运与海外仓服务商，并不在海外自建面向个人消费者的末端派送车队。

- **末端快递主导最终派送：** 当轨迹出现 `Turned over to last mile facility` 与 `Last mile Ascan` 后，派送尝试次数、改派、安全放置点及自提点保管天数均由接手的当地邮政或快递商决定。
- **清关与进口税费：** 发往欧盟、美国、英国、加拿大或澳大利亚的跨境包裹需经目的国清关（`Customs clearance in progress`），并按当地法规缴纳进口增值税和关税（包括欧盟针对非欧盟网购订单、货值不超过 150 欧元的 3 欧元关税以及 2 欧元欧盟处理费），除非商家在结账时已代缴。详见[包裹被海关扣留怎么办](guide:customs)（英文）。

## 出口易包裹停滞、无更新或遗失怎么办

因为发件账号由网店商家在出口易开立，遇到退款、补发或修改收件地址时，请始终优先联系卖家或购物平台。

- **中国至目的国途中静默数日：** 国际航空中转与海关批量查验常导致 `Departed from Airport of Origin`、`Arrived at Airport of destination` 与 `Last mile Ascan` 之间数天无新记录。请参阅[为什么物流停止更新](guide:tracking-not-updating)（英文）。
- **使用交接后的本地单号追踪：** 当 EC-FirstClass 给出目的国合作方的条码单号后，直接到该本地快递官网查询往往能看到更详细的派送时间窗或改约选项。
- **已签收未收到、破损或丢件：** 请先核查家人、邻居及当地派送快递，并在平台买家保护期到期前向卖家提起争议。详见[显示已签收但未收到](guide:delivered-not-received)（英文）、[包裹破损](guide:damaged-parcel)（英文）、[包裹丢失](guide:lost-parcel)（英文）和[包裹退回寄件人](guide:returned-to-sender)（英文）。

## 出口易（Chukou1）官方客服与联系方式

以下联系方式公布于 [出口易联系我们](https://www.chukou1.com/About/Contact.aspx) 和 [chukou1.com](https://www.chukou1.com/)：

- **中国全国统一服务热线：** `4006 908 223`。
- **投诉专线（周一至周五）：** `13360562736`。
- **各分公司电话与总部地址：** 广州总部 `020-23380666`（广州市黄埔区黄埔大道东 840 号行信中心 A1 栋 2403–04 单元）、深圳 `0755-28263408`、上海 `021-59793099`、武汉 `027-59083347`。

> 请警惕要求通过陌生网页支付小额“海关放行费”的虚假短信。任何真实进口税费请先通过购物平台订单页面或当地邮政官网确认。详见[快递诈骗短信识别指南](guide:delivery-scam-texts)（英文）。

## 使用 Peek 追踪出口易（Chukou1）包裹

在 [Peek](/) 中输入出口易处理号（例如虚构示例 `CZN261015ABCD482917` 或 `CUE261015KLM4829173`）时，Chukou1 是唯一匹配候选，Peek 会自动查询并在 EC-FirstClass 确认后显示“Chukou1 有这个包裹的记录。”；Peek 也能自动识别粘贴的 `ec.chukou1.com/track?track_number=` 和 `chukou1.com/LogistictsTrack.aspx?trackNo=` 链接（限出口易自有处理号）。如需手动选择，可在添加时点击“更换”或在包裹页点击“更换快递公司”（搜索“出口易”或“CK1”均可找到）。

Peek 直接读取出口易的 EC-FirstClass 追踪接口，展示标准阶段（“已生成面单”、“已揽收”、“运输中”、“清关中”、“派送中”、“待取件”、“派送未成功”、“需要留意”、“已退回寄件人”、“已签收”）、出口易自有扫描及末端承运商回传轨迹（会自动剔除电话号码与派送员姓名）、扫描地点和目的地国家。收件地邮编与签收人姓名均作隐私保护，任何 Peek 应用都不会显示。EC-FirstClass 返回的扫描时间不带时区标识，Peek 会按每条扫描所在地时区解析并换算为你的设备本地时间。当 EC-FirstClass 给出目的国派送承运商名称及单号且 Peek 识别出该承运商时，Peek 会核实对方是否已收录包裹：确认后页面会显示“由 <承运商> 派送”，并同时列出两个单号。Peek 不会展示内嵌的 PDF 签收凭证；EC-FirstClass 本身不提供预计送达日期、重量、服务名称或单独的签收时间字段。

在打开包裹页面或预计派送当天开启提醒时，只要 EC-FirstClass 正常响应，Peek 最高每 10 分钟检查一次；在出口易扫出派送中记录的当天，自最新扫描所在地早上 6 点起最高每 2 分钟检查一次；其余时间每小时检查一次。若停留在“已生成面单”或“已揽收”满 12 小时，或连续两天无新动态，无论如何关注都会转为每小时检查一次；停留在“已生成面单”满七天后降为每 6 小时一次。当 EC-FirstClass 无响应、Peek 仅通过第三方追踪服务跟进时，Peek 每小时检查一次，静默满一天后改为每 3 小时一次，在派送当天打开页面或开启提醒时最高每 15 分钟检查一次。

当最新扫描满四天且包裹仍停留在“已生成面单”、“已揽收”或“运输中”时，Peek 会标出该扫描日期（如“自10月12日周一起没有物流更新”），并在页面提示“包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 Chukou1 查询。”，同时提供跳转至出口易联系页面的“向 Chukou1 寻求帮助”链接。若超过三周没有新扫描，则显示“已超过三周没有新动态。如果还没收到，请联系卖家。Peek 会持续向 Chukou1 查询。”

你可以自由选择“每次扫描”、“重要环节”或“仅派送当天”通知预设。Peek 仅监控物流更新，无法代你清关、查看内嵌 PDF 签收单，也无法代为联系出口易或当地派送快递。

## 关于出口易（Chukou1）追踪的常见问题

### 出口易（Chukou1 / CK1 / EC-FirstClass）是什么？

出口易（*Chukou1*，运营方为*广州市贝法易信息科技有限公司*）是一家中国跨境电商物流与海外仓服务商，其官方物流追踪查询平台名为 **EC-FirstClass**（[ec.chukou1.com](https://ec.chukou1.com/)）。

### 出口易运单号是什么结构？

出口易处理号依次包含 3 位字母前缀、6 位 `YYMMDD` 日期、3 或 4 位商户代码以及 6 或 7 位流水号（例如虚构示例 `CZN261015ABCD482917`）。

### 出口易轨迹里的 `Last mile Ascan` 是什么意思？

`Last mile Ascan` 表示目的国当地邮政或合作快递在从出口易跨境干线接收包裹后，已经完成了首次入网揽收扫描。

### 如何联系出口易客服？

需要退款或更改收件地址请先联系网店卖家。出口易官网 [chukou1.com/About/Contact.aspx](https://www.chukou1.com/About/Contact.aspx) 公布的中国客服热线为 `4006 908 223`（周一至周五投诉专线 `13360562736`，广州总部 `020-23380666`）。

:::sources
- [出口易（Chukou1）官网](https://www.chukou1.com/) – 跨境小包专线、海外仓（美、英、德、法、波兰、加、澳、秘鲁、阿根廷）、FBA 头程及广州总部（*广州市贝法易信息科技有限公司*）
- [出口易联系我们](https://www.chukou1.com/About/Contact.aspx) – 服务热线 `4006 908 223`、投诉电话 `13360562736` 及广州/深圳/上海/武汉分公司电话与地址（Peek 帮助链接指向此页）
- [EC-FirstClass（出口易追踪平台）](https://ec.chukou1.com/) – 支持 100 单查询的全球追踪平台及合作承运商列表（中国邮政、USPS、DHL、FedEx、UPS）
- [Universal Parcel Scraper: chukou1](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/chukou1) – 处理号格式、`ec.chukou1.com`/`chukou1.com` 链接规则、首程与末端扫描解析、签收人/邮编/交接提取、过滤 PDF 签收单及帮助链接
:::
