---
title: Shiprocket 物流查询：AWB 单号、状态与退回（RTO）说明
description: 查询印度 Shiprocket 与 ShiprocketX 包裹：了解 AWB 单号格式、各状态含义、未妥投（NDR）与退回（RTO）规则及客服渠道。
slug: shiprocket-tracking
published: 2026-11-26
updated: 2026-11-26
---

总部位于印度古鲁格拉姆（Gurugram）的 Shiprocket 是一个物流聚合平台而非自营快递公司：网店卖家通过它在 Delhivery、Blue Dart 或 Xpressbees 等快递公司下单发货，而 Shiprocket 为买家提供追踪页面。ShiprocketX 则是其面向印度发往海外包裹的跨境服务。你可以在 [Shiprocket 追踪页面](https://www.shiprocket.in/shipment-tracking/) 使用 AWB 单号、订单 ID 或手机号查询，也可打开卖家发来的 shiprocket.co 链接，或直接把单号粘贴到上方输入框。Shiprocket 表示订单确认后会通过邮件和短信向你发送 AWB。

## Shiprocket 追踪单号是什么样子

Shiprocket 将追踪单号称为 AWB（航空运单号，air waybill number）。在印度境内，它直接沿用实际承运快递公司的单号；ShiprocketX 跨境包裹则使用 Shiprocket 自有 AWB，即以 832 开头的 13 位数字。Shiprocket 未公布格式，以下数字为虚构示例：

| 单号（虚构示例） | 格式说明 |
| --- | --- |
| `8324829103756` | 832 后接 10 位数字：ShiprocketX 跨境 AWB。 |
| `7394028156342` | 13 或 14 位数字：印度国内快递的 AWB，例如 Delhivery 或 Xpressbees。 |
| `74829103753` | 11 位数字：Blue Dart AWB 的格式。 |
| `FMPC4829103756` | 3 位字母、C/P/R 加 10 位数字：Ekart 的 AWB。 |

**在哪里找单号。** 请查看订单确认时收到的邮件和短信。Shiprocket 追踪页也支持输入订单 ID 或手机号，经一次性验证码（OTP）验证后进入买家订单页 myShiprocket。更多查找方式见 [如何查找追踪单号](guide:find-tracking-number)（英文）。

**外观相近的单号。** Delhivery 的运单同样可能是 13 位数字，因此以 832 开头的 13 位单号大概率属于 Shiprocket，但并非绝对。Shiprocket 的追踪页可查询通过其预订的任意快递，因此卖家发来的 shiprocket.co/tracking/ 链接无论单号格式如何均可直接使用。

## Shiprocket 物流状态含义解析

追踪页面会在 AWB 旁显示当前状态及对应快递或服务名，随后按时间倒序列出扫描节点，每条含动态说明（通常还有地点）、月日与时刻（不含年份）。多数措辞来自实际承运商：印度境内来自本地快递，海外来自目的国承运商。按常见顺序如下：

| Shiprocket 页面原文 | 对你的含义 |
| --- | --- |
| `Order Received` | 卖家已创建订单，尚无快递公司收到实物包裹。 |
| `Manifested - Out for Pickup` | 快递员正前往卖家处上门揽收。 |
| `In Transit - Shipment picked up` | 快递公司已完成揽收。 |
| `In Transit - Shipment Recieved at Origin Center` | 已抵达承运商始发分拨中心（页面原文拼写如此）。 |
| `Dispatched - Delivering At Shiprocket Warehouse` | ShiprocketX 节点：国内快递正把包裹送往 Shiprocket 出口仓，不是送往你家。 |
| `Received At DC` | Shiprocket 出口集散中心已收货。 |
| `AWB Handed over to Flight` | 已交由航空公司装机。 |
| `CUSTOM CLEARED OVERSEAS` | 已通过海外海关清关。 |
| `Received by carrier` | 目的国本地承运商已接收包裹。 |
| `RTO DELIVERED` | 已退回卖家处。 |

`Out For Pickup` 与 `PICKUP EMPLOYEE IS OUT TO P/U SHIPMENT` 同样表示包裹尚未被揽收；`Reached At Warehouse` 与 `Connection Aligned` 是德里出口仓内的处理环节；`Customer has made payment or Provided ID/Tax information` 属于海外清关步骤，表示已缴纳税费或提交身份/税务信息（见 [包裹被海关扣留指南](guide:customs)（英文））。凡以 `RTO`（return to origin，退回原发地）开头的节点均代表包裹正退回卖家。

**较早期的包裹。** 一段时间后，页面只保留归档视图：仅显示最终状态（`DELIVERED`、`FULFILLED` 表示已送达并结案，或 `RTO DELIVERED`）、日期、服务名及隐藏末几位的 AWB。完整历史需向买家手机发送验证码后才展示；约一年后页面会提示 `AWB Not Found`。

## 谁负责派送 Shiprocket 包裹

**印度境内**由卖家预订的快递公司承运。Shiprocket 称其对接 42 家快递伙伴，包括 Delhivery、Blue Dart、Xpressbees、Ekart、DTDC、Shadowfax、India Post 和 Amazon Shipping，送达时效取决于快递公司、邮编（PIN code）和服务类型。追踪页会列出具体服务名称，例如 `Delhivery Surface`。

**发往海外**时，印度国内快递先将 ShiprocketX 包裹送至 Shiprocket 位于德里的出口枢纽，随后空运出境、完成清关并移交目的国承运商，全程仍沿用 Shiprocket AWB。在 Shiprocket 历史记录中可见美国段由 USPS 接手派送。页面会标注服务名（如 `SRX Economy` 或 `SRX Premium`）。Shiprocket 面向卖家宣传经济线发往美、英、加等地需 12 至 15 个工作日，包税线发往美国需 10 至 12 天，但这属于商业宣传参考而非针对你订单的承诺送达日；其菜单也将 ShiprocketX 简称为 “ShipX”。

## 派送失败（NDR）与退回原发地（RTO）

NDR（non-delivery report，未妥投报告）表示快递员未能成功派送。卖家几分钟内就能在 Shiprocket 后台看到记录，并决定安排再次派送还是退回包裹。

- **买家确认消息。** 若卖家启用了 Shiprocket 的 NDR 买家流程，当订单被标为“客户要求改日派送”或“客户拒收”时，你会收到短信（部分套餐还会拨打语音电话）让你确认是否需要配送；卖家也可让 Shiprocket 向你发送短信链接，供你申请再次派送并更正地址或手机号。
- **追踪页面操作。** Shiprocket 说明买家可直接在追踪页申请再次派送并查看每次尝试失败的原因。
- **遭遇虚假派送。** 若并未有人联系却显示尝试派送，请告知卖家，由卖家凭你的确认向 Shiprocket 举报 “fake attempt”。
- **最多三次尝试。** 快递商最多尝试三次；若仍未妥投，订单将被标记为 RTO 并退回卖家的取件地址或仓库。

包裹一旦进入退回流程，请联系卖家重新发货或退款。详见 [派送未成功](guide:missed-delivery)（英文） 和 [退回寄件人](guide:returned-to-sender)（英文）。

## Shiprocket 包裹未到货或物流不更新

**无更新。** Shiprocket 表示快递员扫描包裹后即同步轨迹，可能延迟数小时；超过一天毫无变化时请联系卖家。如果页面只显示 `Order Received` 或揽收中，说明快递商尚未拿到实物。更多原因见 [物流为何停止更新](guide:tracking-not-updating)（英文）。

**找谁处理。** 卖家才是 Shiprocket 的签约客户。针对“如有疑问该联系谁？”，Shiprocket 答复：“请联系你购买商品的卖家。”包裹丢失、破损或未送达也请找卖家处理：ShiprocketX 最高 5,000 印度卢比的丢损赔付面向的是卖家。参阅 [包裹丢失](guide:lost-parcel)（英文） 和 [显示已送达却未收到](guide:delivered-not-received)（英文）。

## Shiprocket 客服电话与联系方式

- **电话：** 9643887070、9711451060 与 8510042020（印度号码，海外加拨 +91）。Shiprocket 追踪网站将这三个号码及在线聊天列为追踪问题求助渠道并称全天候 24/7 支持；其联系页面则标注相同号码的服务时间为印度时间 9:00–21:00。
- **电子邮箱：** support@shiprocket.in。
- **升级投诉：** 普通客服在 5 个工作日内答复；如未获解决，可依次写信至 escalation@shiprocket.com 和 grievance@shiprocket.com，两者均在 3 个工作日内答复。

以上支持大多面向卖家。Shiprocket 追踪网站也提醒：收到不明来源的付款消息时切勿回复或提供支付信息，应拉黑并举报。参阅 [防范快递诈骗短信](guide:delivery-scam-texts)（英文）。

## 使用 Peek 追踪 Shiprocket 包裹

对于以 832 开头的 13 位数字，[Peek](/) 会率先查询 Shiprocket（若你位于印度还会同时查 Delhivery），仅在 Shiprocket 查不到时才问其他服务或承运商：添加页会先显示“正在查询物流服务…”，随后显示“Shiprocket 有这个包裹的记录。”由于 Delhivery 运单同为 13 位，Peek 不会仅凭单号直接锁定 Shiprocket；若暂无结果会显示“等待 Shiprocket 的首次扫描”并持续查询，你也可点击“更换”选择 Shiprocket。粘贴 shiprocket.co/tracking/ 链接则不论单号格式都会直接识别为 Shiprocket。

当你打开包裹，或在开启通知的情况下进入派送日时，只要 Shiprocket 自己的追踪页有响应，Peek 最多每 10 分钟读取一次，并在派送当天自最新扫描所在时钟（印度境内为印度时间）早上 6 点起提高至每 2 分钟一次。连续两天无新动态时（包裹仍显示“已生成面单”或“已揽收”时为 12 小时）改为每小时检查一次；在“已生成面单”下满七天无新动态则每 6 小时一次。Peek 展示当前状态、带地点的扫描节点、在途预计送达日以及印度境内送达时间。海外节点不带时区，Peek 按节点地点推算时钟（无地点的沿用相邻节点或印度时间）并换算为你设备的本地时间。节点按页面原文显示，仅 `Delivered` 等精确匹配项转为 Peek 自己的措辞。Peek 不显示卖家、订单商品、你的姓名与地址或签收凭证，也不会切换到目的国承运商。

对于已归档的较早包裹，Peek 会显示归档状态与日期，并尝试从综合追踪服务补全历史。Peek 无法替你做买家验证、申请再派或联系 Shiprocket 和卖家。你可设置“每次扫描”、“重要环节”或“仅派送当天”通知。若包裹在“已生成面单”、“已揽收”或“运输中”连续四天无新扫描，Peek 会显示“自…起没有物流更新”，并提示“包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 Shiprocket 查询。”Peek 也按各自单号跟踪 [Delhivery](carrier:delhivery)、[Blue Dart](carrier:blue-dart)、Xpressbees、[Ekart](carrier:ekart)、DTDC、Shadowfax 和 [印度邮政（India Post）](carrier:india-post)。

## 关于 Shiprocket 物流查询的问题

### Shiprocket 是快递公司吗？

不是。Shiprocket 是一家物流聚合平台，卖家通过它向 Delhivery、Blue Dart 或 Xpressbees 等快递公司下单，由快递公司实际承运。ShiprocketX 跨境件则使用 Shiprocket 自有 AWB，末端由目的国承运商派送。

### Shiprocket 追踪里的 RTO 是什么意思？

RTO 即 return to origin（退回原发地）：包裹在最多三次派送未果（或卖家提前要求召回）后正退回给卖家。`RTO DELIVERED` 表示卖家已收回退件。

### Shiprocket 客服电话是多少？

Shiprocket 追踪网站为物流查询问题列出了 9643887070、9711451060 和 8510042020。若是订单本身的问题，Shiprocket 让买家联系卖家。

### 为什么 Shiprocket 显示 “AWB Not Found”？

说明 Shiprocket 系统尚未识别你输入的单号：请检查是否输错或漏位；如果是刚下的订单，请按 Shiprocket 页面的建议稍等几分钟再查。此外，超过约一年的历史运单也会显示查无此单。

:::sources
- [Shiprocket: Track your orders easily](https://www.shiprocket.in/shipment-tracking/) – 通过 AWB、订单 ID 或手机号查询、OTP 与 myShiprocket、邮件短信发送 AWB、联系卖家、扫描后更新与 ShipX
- [Shiprocket: AWB Not Found](https://shiprocket.co/tracking) – 未找到排查、防诈提示、电话号码、在线聊天与 24/7 支持
- [Shiprocket: Contact us](https://www.shiprocket.in/contact/) – 古鲁格拉姆总部、电话时段、邮箱、升级通道与答复时效
- [Shiprocket: FAQs](https://www.shiprocket.in/faq/) – 卖家平台定位、预计时效、RTO 与扫描更新
- [Shiprocket: Native courier integrations](https://www.shiprocket.in/carrier-integrations/) – 42 家合作快递及其名称
- [Shiprocket: ShiprocketX](https://www.shiprocket.in/x/) – 跨境服务、参考时效、包税专线与最高 5,000 卢比赔付
- [Shiprocket: NDR management](https://www.shiprocket.in/ndr-management/) – NDR 与 RTO、NDR 买家确认流程、短信与语音呼叫
- [Shiprocket Support: How to Process an NDR?](https://support.shiprocket.in/support/solutions/articles/43000664210-how-to-process-an-ndr-) – 申请再派、短信链接、虚假派送举报与三次尝试后 RTO
- [Shiprocket: Product highlights from March 2024](https://www.shiprocket.in/blog/product-highlights-from-march-2024/) – 追踪页申请再派与失败原因展示
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – AWB 格式、状态原文、德里出口仓、美国段 USPS、归档视图与 Peek 展示内容
:::
