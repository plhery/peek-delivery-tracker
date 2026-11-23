---
title: 菜鸟（Cainiao）国际物流查询：单号、状态与客服
description: 查询菜鸟（Cainiao）与速卖通跨境包裹：LP、CNG、DOFR单号格式、物流与清关状态含义、自提点与派送失败处理、联系客服与Peek跟踪。
slug: cainiao-wuliu-chaxun
published: 2026-11-23
updated: 2026-11-23
---

菜鸟（Cainiao）是阿里巴巴集团旗下的物流公司，成立于 2013 年、总部位于浙江杭州，为速卖通（AliExpress）等跨境平台卖家承运发往海外的包裹。菜鸟国际官方查件网站 [global.cainiao.com](https://global.cainiao.com/) 专门支持菜鸟及速卖通包裹查询；你可以直接在那里查询，或把快递单号粘贴到上方查询框里。

## 菜鸟（Cainiao）单号格式：怎么看、在哪里找

菜鸟官网页面没有统一公布 `LP`、`CNG`、`DOFR` 或 `CNFR` 等自有单号规则，查件框只接受字母与数字，给出的格式示例是两字母 + 九数字 + `HK` 的国际邮政单号。以下为常见菜鸟及易混单号格式（数字均为虚构）：

| 单号（虚构） | 归属与说明 |
| --- | --- |
| `LP48291736051428` | `LP` 加 14 位数字：常见菜鸟/速卖通物流参考号，但并非唯一专属。 |
| `CNG48291736051428` | `CNG` 加 14 位数字：菜鸟国际专线单号。 |
| `DOFR4829173605142HD` | `DOFR` 或 `CNFR` 加 13 位数字再加 `HD`：菜鸟发往欧洲等地的自营专线单号。 |
| `RR482917367CN` | [国际邮政单号](guide:tracking-number-formats)：菜鸟查件页可查，但同时也是 [中国邮政](carrier:china-post) 等国家邮政的单号。 |
| `YT4829173605142839` | 不是菜鸟，而是 [云途物流（YunExpress）](carrier:yunexpress) 的单号。 |

菜鸟查件页同样能查出 `AP` 加 14 位数字等其他菜鸟号段。如果订单不是来自速卖通，请查看商家的发货通知邮件或订单页面，也可参考 [如何找到你的快递单号](guide:find-tracking-number)。如果页面提示查不到记录，请先核对这是否确实是菜鸟或速卖通渠道的运单号。

## 菜鸟物流状态是什么意思

包裹在菜鸟系统中的常见核心节点，大致按时间顺序如下：

| 菜鸟英文原文 | 对你的实际含义 |
| --- | --- |
| `Shipment information received by warehouse electronically` | 卖家仓库已收到电子订单数据，尚未打包发运。 |
| `Warehouse packaging complete` | 卖家仓库已完成打包，但物流承运商还没揽收。 |
| `Received by logistics company` | 物流公司首次扫描揽收包裹。 |
| `Export customs clearance complete` | 起运地出口报关已完成，包裹正在发运途中。 |
| `Departed from departure country/region` | 包裹已离开起运国家或地区。 |
| `Import customs clearance started` | 已进入收件国进口清关流程（详见 [包裹海关扣留与清关指南](guide:customs)）。 |
| `Received by local delivery company` | 已移交给目的国当地尾程快递商，尚未送达你手中。 |
| `Out for delivery` | 当地快递员正在外出派送最后一公里。 |
| `Delivery attempt unsuccessful.Unable to deliver to mailbox` | 派送尝试失败，第一句后面会说明具体原因（例如无法投入信箱）。 |
| `Arrived at pick-up point. Package available for collection.` | 包裹已送达自提点或快递柜，等待你前往领取。 |
| `Package delivered` | 已签收或已从自提点取走。 |

此外还可能看到几条常见补充记录：`Carrier update` 是菜鸟发出的承运商中转提示（仍在运输中）；`Accepted for transportation by postal service` 表示交接后由目的国邮政接手转运（并非首次揽收）；`Delivery Carrier Accepted` 表示当地尾程快递已收货；到达自提点的记录由网点代签，实际取件后才会变成 `Package delivered`；`Processing delay at sorting center` 说明分拣中心积压延误但并非丢件；`Delivery failed` 表示多次派送失败后终止投递；`Shipment cancelled` 表示卖家的上门揽收单在揽收前就被取消，包裹不会再发出，Peek 会标记为「需要留意」。当地尾程快递的其他常见表述可查阅 [常见物流状态含义指南](guide:tracking-statuses)。

## 菜鸟派送失败、自提点与快递柜规则

菜鸟官网介绍其在 9 个国家和地区提供本地包裹派送服务，并在海外本地化页面点名了西班牙、法国和波兰（设有菜鸟自营快递柜）。针对英国、爱尔兰和美国，菜鸟公开页面未写明固定合作尾程快递或派送次数规则。移交尾程后，如果菜鸟掌握当地快递联系信息，查件页上的「View Contact Information」按钮会显示该尾程快递的联系方式。

菜鸟的经济类物流提示说明，经济小包在移交给目的国承运商后不再提供后续全程轨迹。如需确认当地派送商，可询问卖家或参考 [中国直邮包裹跟踪指南](guide:tracking-from-china)。

看到 `Undelivered` 时，通常意味着派送时无人签收、司机找不到地址，或者按你的要求改期了派送；菜鸟建议尽快联系承运商预约重派或自行领取。看到 `Awaiting Collection` 或 `First Notification to recipient` 同样表示包裹正在等你自提，菜鸟提醒应尽快领取，否则可能被退回寄件人。

在西班牙，菜鸟拥有自营本地网络；当地派送公司 Ecoscooting 的帮助说明指出派送失败后会在 3 个工作日内再次尝试派送，存放在自提点或快递柜的包裹保留 14 天，取件 PIN 码会通过邮件或短信发送。

## 菜鸟物流不更新、延误或丢件怎么办

- **暂时没有任何轨迹。** 菜鸟常见问题说明，发货后通常在 11 天内会出现物流更新；如果超过 12 天毫无变化，可以联系卖家核查。`Not Found` 也可能是单号抄错或承运商尚未扫描录入。
- **到达目的国后停滞不动。** 菜鸟说明通常是目的国当地邮政或快递尚未回传扫描，建议询问当地邮局；地址或电话有误也会卡件；停在海关时菜鸟提示收件人直接联系当地海关。长时间无更新请看 [物流为什么停止更新](guide:tracking-not-updating)。
- **严重延误或丢失。** 菜鸟建议先等到订单承诺的最晚送达日期；超期后，速卖通订单请直接通过速卖通帮助中心的 Customer Service 或 Disputes&Reports 发起争议。菜鸟未向个人收件人开放直接理赔表单，退款和补发统一通过速卖通或卖家处理。
- **显示已签收但没收到。** 菜鸟建议先联系寄件人或当地尾程快递；速卖通包裹页面也可能弹出「Did you receive it?」并引导至帮助中心。另见 [显示已签收但未收到](guide:delivered-not-received)。
- **被退回寄件人。** `Unclaimed` 和 `Storage term expired` 表示超时未领取，`Unsuccessful delivery` 通常是地址或电话不详导致无法投递；请直接联系卖家协商后续处理。参见 [包裹退回寄件人](guide:returned-to-sender)。

## 菜鸟客服邮箱与联系方式

菜鸟英文联系页（`cainiao.com`）未公布收件人客服电话，仅列出：

- **客服邮箱**：`cainiaoglobalcs@service.cainiao.com`（标注为 Customer Service，未写明受理范围）。
- **速卖通订单**：引导至速卖通帮助中心，提供在线客服（Online Service）。

菜鸟中文官网的联系电话面向中国国内和商家客户。个人买家遇到订单问题优先找卖家或速卖通客服，涉及最后派送和自提再联系轨迹中显示的当地快递商。在西班牙，Ecoscooting 列出客服电话 `+34 912 15 93 69`（西班牙免费专线 `+34 800 00 03 69`）及邮箱 `customersupport@ecoscooting.com`（工作日 09:00–18:00、周末 09:00–13:00）；葡萄牙地区 Ecoscooting 电话为 `+351 300 609 006`。

## 用 Peek 跟踪菜鸟（Cainiao）包裹

在 [Peek](/) 中，这类包裹统一显示为「AliExpress / Cainiao」。粘贴 `DOFR`、`CNFR`、`CNG` 或 `AP` 单号（例如虚构单号 `CNG48291736051428`）时，Peek 会直接自动识别，查询框里会显示 AliExpress / Cainiao 和「识别出的快递公司」；遇到 `LP` 单号或 `CNUSUP`、`CNBR`、`CNMEX` 等菜鸟参考号时，Peek 会在候选承运商中一并询问菜鸟。纯国际邮政单号默认交给国家邮政处理，但以 `TY`、`MI`、`XX` 或 `GO` 结尾的也会同步询问菜鸟；其他邮政单号只要直接粘贴带 `mailNoList=` 参数的 `global.cainiao.com` 链接，就能直接走菜鸟查询（注意 `track.cainiao.com` 和速卖通订单链接无法直接识别）。如需手动调整，点击添加页的「更换」或详情页的「更换快递公司」，详情页亦附有「向 AliExpress / Cainiao 寻求帮助」链接直达官方联系页。

有时速卖通在包裹还在菜鸟手中时，就已经把尾程快递单号（例如意大利 SDA 单号）发给了买家。

Peek 会展示标准阶段（「已生成面单」「已揽收」「运输中」「清关中」「派送中」「派送未成功」「待取件」「已签收」「需要留意」等）、完整轨迹、菜鸟提供的扫描地点（标在地图上）、预计送达日期区间、签收时间以及菜鸟公布的当地尾程快递单号。

Peek 针对菜鸟最快每 30 分钟查询一次（包括派送当天；查询失败后间隔一小时）。当尾程快递正式接手后，Peek 遵循实际派送快递商自己的频率。连续两天无新扫描（或处于「已生成面单」「已揽收」满 12 小时）改为每小时检查一次。最新扫描仍是「已生成面单」「已揽收」或「运输中」且连续四天无新动态时，Peek 会提示「包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 AliExpress / Cainiao 查询。」，超过三周无动静则提示「已超过三周没有新动态。如果还没收到，请联系卖家。Peek 会持续向 AliExpress / Cainiao 查询。」。

通知偏好支持「每次扫描」「重要环节」和「仅派送当天」。Peek 无法替你联系菜鸟、速卖通或提交理赔。

## 关于菜鸟物流查询的常见问题

### 我的菜鸟包裹现在走到哪一步了？

查看最新一条轨迹即可判断所在阶段，如果该条扫描带有城市名也会一并显示。出现 `Received by local delivery company` 说明包裹已交到收件国本地快递；如果经济小包到此不再更新，请向卖家确认当地派送商。

### 菜鸟（Cainiao）是正规物流公司吗？

是的。菜鸟是阿里巴巴集团于 2013 年创立、总部设在杭州的全球物流企业，也是速卖通默认引导买家查件的官方物流平台。

### 菜鸟国际配送一般需要几天？

取决于卖家选用的渠道。根据菜鸟向商家公布的标准参考时效（非单票承诺日期）：无忧优先（Premium）发往西班牙、荷兰、英国、比利时等重点国家约 5–10 个自然日；无忧标准（Standard）发往主要国家约 10–18 天（揽收后发往英、法、德最快 7 天）；经济类（Economy）约 20–45 天。

### 菜鸟包裹在自提点能放几天？

菜鸟全局页面没有统一公布保管天数，建议收到通知后尽快领取以免退件。西班牙地区的 Ecoscooting 自提点和快递柜保管期限为 14 天。

:::sources
- [Cainiao：全球快递追踪平台（Global Express Tracking）](https://global.cainiao.com/) – 菜鸟与速卖通专属追踪入口与单号格式说明
- [Cainiao：Logistics Status Explanation（英文）](https://global.cainiao.com/helpDoc.htm?slug=phdx0i) – Undelivered、Awaiting Collection、Unclaimed 等状态说明与领取提醒
- [Cainiao：FAQ（英文）](https://global.cainiao.com/helpDoc.htm?slug=bp5fbp) – 11 天与 12 天、目的国停滞、海关、延误与争议
- [Cainiao：查件页语言文件](https://lang.alicdn.com/mcms/global-track/0.0.10/global-track.json) – 经济类提示、View Contact Information、Did you receive it?
- [Cainiao：About Cainiao（英文）](https://www.cainiao.com/en/about-us-brief-introduction.html) – 2013 年成立、阿里巴巴、9 个国家和地区的本地派送
- [Cainiao：Overseas Local（英文）](https://www.cainiao.com/en/global-local-capabilities.html) – 西班牙、法国、波兰与自营快递柜
- [Cainiao：Cross-border Express Delivery（英文）](https://www.cainiao.com/en/global-express.html) – 各类服务的参考时效
- [Cainiao：Contact Cainiao（英文）](https://www.cainiao.com/en/about-us-contact-us.html) – 客服邮箱、杭州地址、速卖通订单入口
- [Ecoscooting](https://ecoscooting.com/) – 西班牙与葡萄牙客服电话和时间、3 个工作日内再派、自提点保留 14 天
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – 菜鸟号段规则（LP/CNG/DOFR/CNFR/AP/CNUSUP/CNBR/CNMEX）、中英文状态与 `Shipment cancelled` 映射、`global.cainiao.com` 链接解析及与尾程快递合并跟踪机制
:::
