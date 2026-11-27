---
title: Parcelforce Worldwide 物流查询：单号、状态、清关与客服
description: 查询 Parcelforce Worldwide 包裹：单号格式、物流状态含义、派送选项、英国进口税单及 Royal Mail 客服联系。
slug: parcelforce-worldwide-tracking
published: 2026-11-26
updated: 2026-11-26
---

Parcelforce Worldwide 隶属于[英国皇家邮政（Royal Mail）](carrier:royal-mail)集团。你可以在皇家邮政的“Track your item”页面查询 Parcelforce 包裹，或直接将单号粘贴到上方输入框。运单号由商家或寄件人提供，通常在订单确认邮件里。

## Parcelforce 运单号：在哪里查找

请查看商家的订单确认邮件，或直接向卖家索取。找不到单号？请参阅[如何查找运单号](guide:find-tracking-number)（英文）。你可能见到以下三种单号形态：

| 单号（虚构） | 含义 |
| --- | --- |
| `EA482917367GB` | 两位字母（`E` 加另一字母，或 `CP`、`GI`）、九位数字加 `GB`，`GB` 前一位为校验位。 |
| `PBTR4829173654` | 14 位单件包裹号：由 `PB`、托运单号及该包裹在整票货物中的序号组成。 |
| `TR4829173` | 单独的整票托运号（consignment number）：皇家邮政查询页要求输入每件包裹各自的独立单号。 |

13 位邮政格式同时也是皇家邮政的编码（例如 `RR482917367GB` 属于 Royal Mail 而非 Parcelforce），部分 express48 包裹也会使用 Royal Mail 单号。由于两者都在同一查询页追踪，这对你影响不大。

## Parcelforce 物流状态含义

皇家邮政查询页以英文显示以下记录，按包裹通常经过的顺序排列：

| 查询页显示 | 对你的含义 |
| --- | --- |
| `Sender has despatched item` | 寄件人已下单预约寄件，Parcelforce 尚未收到实物包裹。 |
| `Item Collected` | 已从寄件人处揽收。 |
| `Accepted at Parcelshop` | 寄件人已在邮局或 Parcelshop 自投点交寄。 |
| `Item received` | 包裹已进入转运网络。 |
| `Customs charges raised` | 英国境外入境包裹需在派送前缴纳增值税、关税或清关费。 |
| `Despatched to delivery office` | 正发往你所在地区的分拨站或投递局。 |
| `Received at Delivery Depot` | 已抵达当地派送站，尚未开放自提。 |
| `Out for delivery` | 已装车，今天由司机派送。 |
| `Delivery attempted` | 司机尝试派送但未成功。 |
| `Delivered by` | 已签收送达，记录中可能注明投递网点名称。 |
| `Delivered to Neighbour/Reception by` | 已交由邻居或前台代收，记录中会写明代收人或地点。 |
| `Returned to sender` | 正在退回寄件人途中。 |

对于从英国发往境外的包裹，轨迹还会继续显示 `Item Leaving the UK` 以及目的地合作承运商的 `Due to be delivered today` 等记录。

## Parcelforce 派送选项与派送未成功

**派送前调整。** 通过 Royal Mail 应用或 Royal Mail 客户服务，你可以为 Parcelforce 包裹改选送货日期、指定邻居代收，或备注请司机敲门更响亮、多留一些开门时间。安全放置点（safe place）同样在 Royal Mail 中设置，且对 Parcelforce 包裹生效。

**派送未成功。** 物流会显示 `Delivery attempted`，或显示 `Delivered to Neighbour/Reception by` 并标出代收人姓名。若仍无法确认包裹去向，请致电下方 Royal Mail 客服热线。

## Parcelforce 物流不更新、延误或遗失

**暂无扫描记录。** 寄件人可能在交寄前就生成了单号：`Sender has despatched item` 表示 Parcelforce 尚未拿到包裹。请核对单号并稍后再试。详见[物流停止更新的原因](guide:tracking-not-updating)（英文）。

**延误、遗失或破损。** 如为网购订单，请优先联系卖家：英国公民咨询局（Citizens Advice）指出通常找卖家处理更有效，你可以要求退款或补发。如需申请承运商赔偿，请在 Parcelforce 官网查看索赔流程。

**海关税费。** Parcelforce 负责将境外入境英国的包裹送交海关清关；如有增值税、关税或其他费用应付，Parcelforce 会向你发出账单列明应付金额（详见[海关滞留包裹指南](guide:customs)（英文））。包裹通常保管约 **3 周**，逾期未缴则退回寄件人。关于货物的清关咨询也请直接联系 Parcelforce。

> 不确定要求你为包裹付款的邮件或短信是否真实？切勿点击链接或填写银行信息。可将诈骗邮件免费转发至 `report@phishing.gov.uk`，诈骗短信免费转发至 `7726`。

## Parcelforce 客服电话与联系方式

英国公民咨询局建议 Parcelforce 收件人通过 Royal Mail 办理派送需求与安全放置设置：

- **Royal Mail 客服热线：** `03457 740 740`，英国时间周一至周五 08:00–18:30，周六 08:00–15:00，周日 09:00–14:00。**Relay UK：** 先拨 `18001` 再拨 `0345 266 0075`。
- **Royal Mail 应用：** 同样支持为 Parcelforce 包裹更改派送日期、设置邻居代收、安全放置点及特殊派送需求。

如需提交理赔或正式投诉，请通过 Parcelforce 自己的网站办理。

## 使用 Peek 跟踪 Parcelforce 包裹

对于校验位有效的 `EA482917367GB` 类型单号以及 14 位 `PB` 单号，[Peek](/) 会直接识别为 Parcelforce；单独的托运单号无法追踪。包含 `trackNumber=` 的 Parcelforce 查询链接，以及结尾为 `#/tracking-results/` 加 Parcelforce 单号的 Royal Mail 链接均可直接粘贴识别。

Peek 会在服务器端用真实浏览器打开 Royal Mail 查询页面读取 Parcelforce 信息，因此单次刷新耗时稍长，且当网站拦截自动访问时可能失败；此时 Peek 会尝试通用物流查询服务，但这些服务对 Parcelforce 收录很少。

Peek 会展示物流状态、附带地点的每次扫描（不含邮编）、预计送达日、签收时间及目的地国家。当你正在查看包裹，或派送当天已开启通知时，Peek 最多每 10 分钟查询一次（在 Parcelforce 扫描包裹开始派送的当天，只要 Parcelforce 自己的查件系统正常响应，自英国时间早上 6 点起——若 Parcelforce 给出了派送时间段，则自该时段开始前半小时起——每 5 分钟查询一次）。其余情况下，以及连续两天无新动态（或在“已生成面单”/“已揽收”停留满 12 小时）后，Peek 每小时检查一次；若在“已生成面单”停留满 7 天无新动态，则每 6 小时一次。Peek 会在地图上标出每次扫描，并可以在状态变化时通知你。物流状态保留 Royal Mail 英文原文，不带时区的境外扫描则不标具体时刻。对于 `EA482917367GB` 类型的英国出口包裹，Peek 还可以询问 Parcelforce 所报告目的地国家的国家邮政（若未报告目的地，则询问你在英国以外的所在国家邮政），一旦对方出现不早于 Parcelforce 最新记录的扫描，即转由对方继续跟踪。

## Parcelforce 物流查询常见问题

### Parcelforce 属于 Royal Mail 吗？

是的。英国政府官网（GOV.UK）将 Parcelforce 归入 Royal Mail 集团，其包裹也在 Royal Mail 的“Track your item”页面追踪。

### 为什么我的 Parcelforce 单号无法识别？

可能是包裹尚未移交 Parcelforce，或单号输入有误。此外，单独的整票托运单号无法追踪：请向寄件人索取每件包裹独立的 14 位 `PB` 单号后重试。

### 带有关税账单的 Parcelforce 包裹会保管多久？

大约 3 周。如果到期仍未缴清税单，包裹将退回寄件人。

### 可以更改 Parcelforce 的送货日期吗？

可以，可通过 Royal Mail 应用或致电 Royal Mail 客服改选送货日期，也可以指定由邻居代收。

:::sources
- [GOV.UK: Check when you can account for import VAT on your VAT Return](https://www.gov.uk/guidance/check-when-you-can-account-for-import-vat-on-your-vat-return) – Parcelforce 属于 Royal Mail 集团
- [Citizens Advice: Telling delivery companies about your delivery or collection needs](https://www.citizensadvice.org.uk/consumer/post-and-parcels/telling-delivery-companies-about-your-delivery-or-collection-needs/) – 为 Parcelforce 包裹使用 Royal Mail 应用与客服、改期、邻居代收、安全放置点、敲门、电话与服务时间
- [Citizens Advice: Claiming compensation from Royal Mail](https://www.citizensadvice.org.uk/consumer/post-and-parcels/claiming-compensation-from-royal-mail/) – Relay UK 号码
- [Citizens Advice: Damaged, lost or delayed post or parcel](https://www.citizensadvice.org.uk/consumer/post-and-parcels/damaged-lost-delayed-post-or-parcel/) – 先联系卖家、在快递公司网站上索赔
- [GOV.UK: Tax and customs for goods sent from abroad: Overview](https://www.gov.uk/goods-sent-from-abroad) – Parcelforce 办理货物清关、货物相关咨询
- [GOV.UK: Tax and customs for goods sent from abroad: Tax and duty](https://www.gov.uk/goods-sent-from-abroad/tax-and-duty) – 账单、约 3 周、退回寄件人
- [GOV.UK: Avoid and report internet scams and phishing](https://www.gov.uk/report-suspicious-emails-websites-phishing) – 7726, report@phishing.gov.uk
- [UPU: S10 standard, Identification of postal items](https://www.upu.int/UPU/media/upu/files/postalSolutions/programmesAndServices/standards/S10-12.pdf) – 国家代码前的校验位
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Peek 读取的内容、状态、单号格式
:::
