---
title: Royal Mail 物流查询：单号格式、状态与重新派送
description: 查询英国皇家邮政 Royal Mail 包裹：单号格式说明、英文物流状态中文对照、未签收红卡与改派、海关税单、丢件理赔及客服联系方式。
slug: royal-mail-tracking
published: 2026-11-24
updated: 2026-11-24
---

Royal Mail（英国皇家邮政）是英国的国家邮政机构，其官网“Track your item”页面同时也支持查询 Parcelforce 包裹。你可以在官网输入单号，或直接粘贴到上方输入框中查询。运单号由寄件人提供，通常位于商家的订单确认邮件、发货邮件或发票中。

## Royal Mail 运单号：格式与查找位置

Royal Mail 运单号常见有三种格式。如果商家的邮件里找不到，请联系商家；更多查找方式见[如何查找运单号](guide:find-tracking-number)（英文）。

| 格式（示例） | 说明 |
| --- | --- |
| `AB482917367GB` | 最常见的追踪单号：2 位字母、9 位数字加 `GB`。最后一位数字是校验位。 |
| `0A4829173605841926KLT` | 来自英国国内面单二维码的 21 位参考号。 |
| `482917360584CDEF` | 由数字和字母 `A` 至 `F` 组成的 16 位参考号。 |

容易混淆的单号：

- 以 `E` 加任意字母（如 `EA`、`EB`…）、`CP` 或 `GI` 开头，后接 9 位数字和 `GB` 的单号属于 Parcelforce，在同一个页面即可查询。
- 相同格式但以其他国家代码结尾（如 `RR482917367FR`）的单号是发件国邮政的编号。按万国邮政联盟（UPU）规定，每件邮件只使用一个此类编号，并一直沿用至送达你的住址。
- 不含字母的 16 位纯数字不属于 Royal Mail 格式。Peek 会尝试在 DHL eCommerce、TNT 等其他承运商中查询。

## Royal Mail 物流状态中文说明

Royal Mail 的官方物流信息均为英文。以下按包裹常见流转顺序列出主要状态：

| Royal Mail 状态原文 | 对你的含义 |
| --- | --- |
| `Sender preparing item` | 寄件人已创建预报，Royal Mail 尚未收到包裹。 |
| `We're expecting it` | 已预报，但 Royal Mail 尚未实际揽收。 |
| `Item Received` | Royal Mail 已收到包裹。在各邮件处理中心、枢纽以及抵达目的国时都会再次出现这条状态。 |
| `In transit` | 正在邮政网络中转运。 |
| `Item Leaving the UK` | 跨境发件：包裹已离开英国。 |
| `Your item is currently with Customs in the destination country.` | 包裹正处于目的国海关查验中。 |
| `Released from Customs` | 目的国海关已放行。 |
| `Customs charges raised` | 派送前须缴纳增值税、关税或相关费用（见下文）。 |
| `Despatched to delivery office` | 正在发往负责你所在区域的派送邮局。 |
| `Ready for Delivery` | 已到达负责派送的邮局，或已排入当天派送路线。 |
| `Out for delivery` | 今天已交由邮递员外出派送。 |
| `Delivery attempted` | 派送未成功，无人签收。 |
| `Due for Redelivery the Next Working Day` | Royal Mail 将在下一个工作日再次派送；新一轮派送会单独出现一条派送中记录。 |
| `Available for Redelivery or Collection` | 包裹正在客户服务点（Customer Service Point）等待你自提或预约重新派送。 |
| `Delivered` | 已签收。记录可能会写明签收人姓名，`Delivered to Neighbour/Reception by` 则会标明代收的邻居或前台。 |
| `Returned to sender` | 包裹正在退回寄件人。 |

Royal Mail 的顶部摘要有时还会显示 `Pending`（例如在海关扫描旁边）：此时 Peek 会显示最新一条带有具体阶段的扫描状态，例如“清关中”。

## 派送未成功：红卡与重新派送

Royal Mail 周一至周六派送，通常在下午 4:30 前送达，部分地区周日也派送。有指定送达时限的包裹可能会晚于下午 4:30，公共节假日（bank holidays）不派送。二等信件（2nd Class letters）正按各邮局逐步过渡到隔个工作日派送，预计 2026 年 12 月前完成；但包裹（包括 Tracked 24）仍维持周一至周六派送。

如果你未应门，Royal Mail 会将包裹放在你的安全放置点或交给邻居代收。你可以在 Royal Mail 应用中或通过客服设置安全放置点，或要求不得留置包裹；相关设置有效期为一年，到期后请重新确认。若不想交给邻居代收，可填写 Royal Mail 表格申请一张贴在信箱旁的拒代收贴纸。

未能妥投时，邮递员会留下一张红色通知卡（red card），上面印有[取件地点](guide:missed-delivery)（英文）的地址和营业时间——通常是当地派送邮局内的客户服务点（Customer Service Point）。前往前请先在 Royal Mail 官网核对营业时间，或直接在官网预约重新送货上门。

如果境外寄来的包裹需要缴纳增值税（VAT）、关税或其他费用，Royal Mail 会寄送账单写明应缴金额，并通常保留包裹约 3 周；逾期未付将退回寄件人。价值不超过 39 英镑的礼品免征增值税，购买的 135 英镑及以下商品通常已在结账时由卖家代收增值税。如果你退回了商品或认为多缴了税费，可使用 BOR 286 表格申请退还增值税或关税（该表格适用于 Royal Mail 或 Parcelforce 派送的包裹）。

在北爱尔兰，来自英国及欧盟以外的商品可能被判定存在进入欧盟的“风险”（at risk），承运公司会告知你是否适用。如果你不是企业用户，则须缴纳关税以及按英镑折算的每件 2 欧元处理费。自 2026 年 11 月 1 日起，从大不列颠发往欧盟国家的跨境网购商品同样适用欧盟每件 2 欧元的处理费（相同商品合并计为一件）。该费用由商家或派送公司缴纳并可能转嫁给收件人；在爱尔兰，如果商家结账时未收取，邮政或快递可在派送前向你收取。另请参阅[清关中的包裹指南](guide:customs)（英文）。

## Royal Mail 包裹停滞、延误或丢失

**正常时效参考。** 自 2026 年 4 月 1 日起，英国通信管理局 Ofcom 设定的目标为：90% 的一等邮件（1st Class）次工作日送达，95% 的二等邮件（2nd Class）三天内送达。英国公民咨询局（Citizens Advice）指出，Royal Mail 在 2026 年 4 月至 6 月未能达成这两项指标；Royal Mail 计划在 2027 年春季前达标。

**暂无物流记录。** 在首次实体扫描前，Royal Mail 可能查不到该单号；出现 `Sender preparing item` 则表示 Royal Mail 尚未拿到包裹。如果一直无动态，请询问寄件人是否已经交寄。更多原因见[物流为什么停止更新](guide:tracking-not-updating)（英文）。

**延误或丢失。** 若是网购商品，请先联系卖家：Citizens Advice 指出直接找卖家通常能获得更好结果，你可以要求退款或补发。英国境内寄出的邮件，寄件人与收件人均可向 Royal Mail 申请赔偿，但 Tracked 24 和 Tracked 48 仅限寄件人索赔。索赔需提供寄件凭证（作为收件人需保留带邮戳的外包装），且须满足等待期：

- **英国境内丢失：** 1st Class 寄出后满 11 个工作日，2nd Class 满 14 个工作日，Special Delivery 超过预计送达日满 5 个工作日；均须在寄出后 80 天内提交。对于在 12 月第一个星期一至次年 1 月第一个工作日之间寄出的邮件，上述等待期各多加一天。
- **寄往海外丢失：** 欧洲地区超过预计送达日满 20 个工作日，其他地区满 25 个工作日；须在寄出后 6 个月内提交。

Tracked 24、Tracked 48 及发往海外的邮件不提供延误赔偿。针对丢失或破损，Royal Mail 对 1st/2nd Class 最高按物品价值赔付 20 英镑，Tracked 24/48 最高赔付 75 英镑，国际追踪或签收件最高赔付 50 英镑，Special Delivery Guaranteed by 1pm 最高赔付 750 英镑；后两类服务若寄件人购买了额外保价可提高赔付上限。你可以在 Royal Mail 官网在线提交索赔，或在邮局领取纸质表格办理；Royal Mail 将在 30 天内答复（跨境寄出件为 90 天内）。更多内容见[包裹丢失指南](guide:lost-parcel)（英文）。

**显示已签收但未收到。** 签收记录可能会写明签收人姓名，或显示 `Delivered to Neighbour/Reception by`：请先询问对应人员及同住人、检查安全放置点，再联系寄件人。怀疑被盗的包裹除索赔外也可向警方报案。另见[显示已签收但未收到](guide:delivered-not-received)（英文）。

## Royal Mail 客服电话与联系方式

Citizens Advice 列出的 Royal Mail 联系方式如下：

- **客服热线：** 03457 740 740，周一至周五 8:00 至 18:30，周六 8:00 至 15:00，周日 9:00 至 14:00。文字电话（Textphone）03456 000 606；Relay UK 请先拨 18001 再拨 0345 266 0075。
- **在线办理：** 索赔、投诉和预约重新派送均可在 Royal Mail 官网办理；安全放置点和派送偏好可在 Royal Mail 应用中设置。
- **如果投诉陷入停滞：** 邮政复核小组（Postal Review Panel）会重新审核并应在 30 天内答复。此后，或如果 Royal Mail 超过 90 天未处理或跳过投诉流程，独立的邮政申诉服务机构（POSTRS）可作出裁决：电话 020 7520 3766，邮箱 postrs@cedr.com。

> 不确定收到的包裹短信或邮件是否真实？切勿点击其中链接或提供银行卡信息。英国境内可将诈骗短信免费转发至 7726，诈骗邮件转发至 report@phishing.gov.uk。更多防范技巧见[快递诈骗短信指南](guide:delivery-scam-texts)（英文）。

## 使用 Peek 查询 Royal Mail 包裹

对于校验位有效的 `AB482917367GB` 格式单号，[Peek](/) 会直接识别为 Royal Mail 并显示“识别出的快递公司”。如果校验位不对，Peek 会提示“这个单号的校验位对不上。”：请对照发货信息核对，或点击“按输入的单号跟踪”再通过“更换”选择 Royal Mail。如果某个字母疑似与数字混淆（例如把 `0` 输成 `O`），Peek 会给出校验正确的建议单号。对于 21 位和 16 位参考号，Peek 会先查询物流查询服务，再查询 Royal Mail 官网，因此可能稍需片刻；如果查无结果，或少数参考号被识别为 [Australia Post](carrier:australia-post)，请点击“更换”手动选择 Royal Mail。带有 `trackNumber=` 的 Royal Mail 查询链接对以上所有单号均可立即识别为 Royal Mail；而 `#/tracking-results/` 链接仅在包含 `GB` 结尾单号时生效。

Peek 会在自有服务器的浏览器中访问 Royal Mail 官网。由于 Royal Mail 经常拒绝自动化访问，无论是平时还是派送当天（即使 Royal Mail 给出了送达时间段），Peek 均大约每小时自动检查一次；手动刷新可以更早触发查询。其他物流查询服务通常只收录少量 Royal Mail 记录，因此状态有时会停留在上一条数小时。

页面会显示最新状态、每条扫描及其地点（自动隐去邮编并标在地图上）、预计送达日（如 Royal Mail 提供送达时段也会一并显示）、签收时间及目的国。英国境内扫描均带有精确时间。签收记录统一显示为“已签收”，隐去签收人姓名、收件人、详细地址及照片；其他状态保留 Royal Mail 英文原文。你可以选择按“每次扫描”、“重要环节”或“仅派送当天”接收通知。

从海外寄往英国、带有国际邮政单号的包裹在 Royal Mail 出现进展后可继续在 Royal Mail 下追踪；以 `E` 开头的 EMS 特快专递在英国由 Parcelforce 负责派送，Peek 会转往 Parcelforce 查询。从英国寄往海外的邮件全程沿用 `GB` 单号：一旦 Royal Mail 标出目的国，Peek 还会同步查询全球 60 多个国家对应的目的国邮政，并在对方出现更新扫描后自动切换。当其他承运商接手派送时，Peek 会按照实际派送承运商的频率刷新。预约重新派送、申请理赔和缴纳海关税单仍需在 Royal Mail 官网办理。

## Royal Mail 物流查询常见问题

### Royal Mail 物流多久更新一次？

没有固定间隔：只有在包裹经过下一次扫描时状态才会更新。Peek 会大约每小时自动检查一次 Royal Mail 包裹，派送当天亦然。

### Royal Mail 几点派送？

周一至周六派送，通常在下午 4:30 之前，部分地区周日也派送。带指定送达时限的包裹可能会更晚送达；英国公共节假日不派送。

### “Due for Redelivery the Next Working Day”是什么意思？

表示 Royal Mail 未能妥投，并会在下一个工作日再次尝试派送，届时会出现一条新的派送中记录。多次未投递后，包裹会存放在客户服务点等待领取，物流状态会显示 `Available for Redelivery or Collection`。

### 我的 Royal Mail 运单号是假的吗？

不一定。寄件人可能在交寄包裹之前就已生成单号，而在首次扫描前 Royal Mail 系统可能尚未收录该号码。如果几天后仍查不到任何记录，请联系寄件人核实。

:::sources
- [Citizens Advice: Check Royal Mail collection and delivery times](https://www.citizensadvice.org.uk/consumer/post-and-parcels/post-collection-delivery-times/) – 派送日期与时间、二等信件隔日派送、周日与节假日、客户服务点、电话及服务时间
- [Citizens Advice: Telling delivery companies about your delivery or collection needs](https://www.citizensadvice.org.uk/consumer/post-and-parcels/telling-delivery-companies-about-your-delivery-or-collection-needs/) – 安全放置点或邻居代收、贴纸、Royal Mail 应用、设置保留一年、红卡与重新派送
- [Citizens Advice: Claiming compensation from Royal Mail](https://www.citizensadvice.org.uk/consumer/post-and-parcels/claiming-compensation-from-royal-mail/) – 索赔主体、寄件凭证、邮戳包装、等待期与截止期限、12 月初起寄出多等一天、延误规则、赔付上限、提交方式、30/90 天答复、Textphone 与 Relay UK
- [Citizens Advice: Damaged, lost or delayed post or parcel](https://www.citizensadvice.org.uk/consumer/post-and-parcels/damaged-lost-delayed-post-or-parcel/) – 先找卖家、Postal Review Panel 30 天复核、POSTRS 与被盗处理
- [Citizens Advice: Response to Royal Mail's Quality of Service results, August 2026](https://www.citizensadvice.org.uk/about-us/media-centre/press-releases/citizens-advice-responds-to-royal-mails-latest-quality-of-service-results-august-2026/) – 2026 年 4 月 1 日起 Ofcom 指标、4–6 月未达标、12 个月达标计划及 12 月前二等信件隔日派送推广
- [GOV.UK: Tax and customs for goods sent from abroad: Tax and duty](https://www.gov.uk/goods-sent-from-abroad/tax-and-duty) – Royal Mail 税单、约 3 周保管期、礼品与 135 英镑以内增值税规则、BOR 286 退税表及北爱尔兰风险商品 2 欧元处理费
- [Revenue: Union Handling Fee press release](https://www.revenue.ie/en/corporate/press-office/press-releases/2026/pr-092926-handling-fee.aspx) – 自 2026 年 11 月 1 日起每件 2 欧元（同类商品合算一件）、涵盖大不列颠寄出货物、由商家或派送方缴纳并可转嫁，爱尔兰可在派送前收取
- [GOV.UK: Avoid and report internet scams and phishing](https://www.gov.uk/report-suspicious-emails-websites-phishing) – 7726 短信举报与 report@phishing.gov.uk
- [UPU: S10 standard, Identification of postal items](https://www.upu.int/UPU/media/upu/files/postalSolutions/programmesAndServices/standards/S10-12.pdf) – 校验位、发件国邮政代码与每件邮件唯一编号规则
:::
