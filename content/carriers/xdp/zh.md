---
title: XDP 物流查询：英国大件快递托运单号、状态与派送说明
description: 查询英国 XDP（XDP Express）大件包裹：托运单号来源、为何需要输入邮编、物流状态含义、两小时派送时段及联系 XDP 客服方式。
slug: xdp-tracking
published: 2026-11-26
updated: 2026-11-26
---

XDP（XDP Express）是英国的包裹与大件货运网络，为网店派送家具、床垫、园艺用品和健身器材等大件重货，常由一名或两名司机上门派送。你可以凭托运单号和收件邮编在 [XDP 查询页面](https://xdp.co.uk/track/)或 XDP 应用中跟踪包裹，也可以直接将单号粘贴到上方输入框。单号由发货商家提供，而非 XDP 发放。

## XDP 运单号：托运单号（consignment number）

XDP 将其称为托运单号（consignment number）。其查询页面明确写着，该编号由“通过 XDP 为你寄送商品的公司”提供，请先查看商家的发货确认邮件或订单页面。XDP 自身的短信和邮件会在包裹进入运输后才发送。

XDP 未公开单号格式；其单号形态如下：

| 格式（虚构） | 含义 |
| --- | --- |
| `ZDBKRT482917` | `ZDB` + 三位字母 + 六位数字：XDP 托运单号。 |
| `0ZDBKRT482917` | 同一单号前带一个 `0`，查询时可省略该前导 `0`。 |

XDP 查询页还会要求输入收件邮编或寄件邮编。邮编错误与单号拼写错误会显示完全相同的提示 `Consignment not found for consignment number`，因此请同时核对两项。

英国没有其他快递公司使用 `ZDB` 开头的单号。[DPD UK](carrier:dpd-uk) 单号为 14 位数字，[APC Overnight](carrier:apc-overnight) 运单号为 22 位数字；若商家邮件写明由它们承运，请前往对应承运商查询。更多内容见[运单号格式说明](guide:tracking-number-formats)（英文）。

## XDP 物流状态含义

XDP 查询网站、应用及常见问题页仅提供英文。线路编号与时间会因具体包裹而异。

| XDP 显示 | 对你的含义 |
| --- | --- |
| `Order received` | 商家已向 XDP 提交订单数据，但 XDP 尚未收到货物。 |
| `At Collection Depot` | XDP 已收到货物，正发往你的末端派送站。 |
| `At Delivery Depot` | 已抵达负责派送的本地分拨站。 |
| `Allocated to Delivery Vehicle - Route 03` | 已分配至派送车辆线路。 |
| `Scheduled for Delivery - Route 11 - ETA: 10:51am to 12:51pm` | 预计今天在该两小时时段内送达。 |
| `Next stop: On time` | 你是司机的下一站。XDP 会对照预计时段显示 `On time`、`Late by …` 或 `Early by …`；Peek 会在前面加上 `Next stop:`。 |
| `Delivered` | 已签收送达。 |
| `Carded` | 司机上门尝试派送，但家中无人。 |
| `Consignment Split` | 分拨站目前仅收到商家发出的部分货件。 |
| `Check Address` | 司机找不到收件地址，需要补充地址信息。 |
| `Service Fail` | 因车辆故障、交通拥堵或恶劣天气等 XDP 不可控原因延误。 |
| `Return` | 已退回寄件人，请联系商家。 |

XDP 应用中的部分表述略有不同（例如 `Delivery scheduled`）。出现 `Consignment Split` 后，XDP 表示其余件数会在“接下来几天内”跟进送达。`RETURN LABEL CREATED` 表示已启动退回流程，`Damaged` 表示货件看似破损（见下文）。

## XDP 派送：时间段、派送未成功与网点自提

派送当天司机装车后，XDP 通常会在中午 12 点前发出一个两小时的派送时间段；高峰期分拨站可能会提前 24 小时或更早排班发送。临近送达时，短信、邮件和应用会推送“You Are Next”提醒。对于双人派送（2Man），XDP 会先联系你确认日期，再于派送当天发送两小时时段。选用 1+ 服务时，在你签署 XDP 提前发送的免责声明后，司机可将货物搬入你指定的一楼房间；2Man 服务可搬上一层室内楼梯（健身器材仅限一楼）。

在 XDP 应用中你可以：

- 选择 **Change Delivery Date** 重新安排送货日期，部分情况可直接自选日期；
- 填写派送备注，例如交给邻居或放在门廊、棚屋、车库或大门后；
- 给司机留言并查看签收凭证（proof of delivery）。

XDP 提醒，一旦你要求“安全放置”（Left Safe），自货物放妥之时起即由你承担全部责任。

**派送未成功。** `Carded` 表示司机上门时家中无人。XDP 表示在退回寄件人前会再尝试派送三次。如果未留通知单，司机当天也无法二次折返，请使用 Change Delivery Date 重新预约。

**不提供分拨站自提。** XDP 应用明确说明不提供分拨站或枢纽自提服务；常见问题页建议如需自提请联系商家。

## XDP 包裹未送达、延误或破损

XDP 的合同对象是寄件商家而非收件人。遇任何派送问题，XDP 均建议先在应用或其网站查看物流，再联系你的供货商（商家）推进后续处理——运输合同在商家手中，索赔也由商家提出。

**延误或无更新。** XDP 不保证次日达，尤其是银行假日、黑色星期五和网络星期一等高峰时段。司机因堵车延误时，若时段有变，不一定会通知你。详见[物流停止更新的原因](guide:tracking-not-updating)（英文）。

**货件破损。** XDP 要求你先签收而非拒收，并立即联系寄件人。签收时务必在签收单（proof of delivery）上写明破损情况：XDP 面向寄件人的操作指南指出，若签收单未注明异常，理赔将被拒，且写“unchecked（未检查）”或“unseen（未见）”不算有效批注。

**遗失。** 寄件人向 XDP 索赔的时限很紧：整票或部分遗失须在发货后 **33 天**内提出，破损须在签收后 **7 天**内提出（均含周末）。请尽早通知卖家。若物流显示 `Delivered` 但你未收到，请参阅[显示已签收却未收到](guide:delivered-not-received)（英文）。

**退回寄件人。** 出现 `Return` 后，XDP 建议直接联系供货商。

## XDP 客服与联系方式

XDP 面向收件人的联系渠道：

- **Delivery Query 表单：** 位于 XDP [联系页面](https://xdp.co.uk/contact/)（同页面的“New Business Enquiry”仅用于商务销售）。
- **电子邮箱：** `trackingupdates@xdp.co.uk`。
- **在线客服（Live chat）：** 见 xdp.co.uk 网站与 XDP 应用；XDP 未公布服务时间。
- **应用内联系表单：** 需填写姓名、邮编、邮箱，并可选填托运单号。

XDP 网站未公布客服电话。其应用中提到拨打电话按每分钟 7 便士的溢价资费计费，但未列出具体号码；第三方网站上的号码并非 XDP 公布。

## 使用 Peek 跟踪 XDP 包裹

[Peek](/) 会直接将 `ZDB` 开头的编号识别为 XDP。粘贴 xdp.co.uk 链接（`/track/` 加单号或 `track.php?c=`）同样可用，但链接里附带的邮编不会被读取。

Peek 会为 XDP 显示可选的“收件或寄件邮编”字段。如果不填邮编，Peek 根本不会向 XDP 查询，只会通过[通用物流查询服务](guide:universal-tracker)（英文）跟踪；填写后，Peek 会直接读取 XDP 查询页，并将邮编发送给 XDP 及必要时的物流查询服务。XDP 对邮编错误和单号未知会给出相同的未找到提示，请两项一并核对。

XDP 的扫描时间不含时区：Peek 按英国时间解析，并展示 XDP 自身轨迹。除 `Delivered` 改用 Peek 自身文案外，其余状态保留 XDP 英文原文；Peek 可以显示重量，并可以在状态变化时通知你。Peek 不显示预计送达日、扫描地点或送货时段，除非写在状态文字中，如 `Scheduled for Delivery - Route 11 - ETA: 10:51am to 12:51pm`。当你正在 Peek 中查看该包裹，或派送当天已开启通知时，Peek 最多每 10 分钟查询一次 XDP（在包裹派送当天，只要 XDP 自己的查件系统正常响应，自英国时间早上 6 点起每 2 分钟查询一次）；其余情况，以及连续两天无新动态（或在“已生成面单”/“已揽收”停留满 12 小时）后，每小时检查一次。未填邮编而仅靠物流查询服务跟踪时，每小时检查一次，静默一天后每 3 小时一次，派送当天查看中或开启通知时最多每 15 分钟一次。无论是否填邮编，在“已生成面单”停留满 7 天无新动态后，均改为每 6 小时检查一次。

## XDP 物流查询常见问题

### 为什么我的 XDP 物流查不到？

常见原因是邮编不符：XDP 查询页需要同时核对托运单号与收件或寄件邮编，邮编不对就会提示 `Consignment not found`。请对照商家邮件核查两项信息。

### XDP 派送需要几天？

XDP 向寄件人公布的参考时效为：1Man 服务 1 至 3 天，1+ 与 2Man 服务 3 至 5 天（离岛地址视船期另计）。派送当天会提供两小时送货窗口。

### 可以去 XDP 分拨站自提包裹吗？

不可以。XDP 应用写明不提供分拨站或枢纽自提，常见问题页也会让你联系商家处理。你可以重新预约送货日期或要求交给邻居代收。

### XDP 是正规快递公司吗？

是的。X D P Limited 自 1995 年起在英国公司注册处（Companies House）登记注册，办公地址位于萨顿科尔菲尔德附近的 Curdworth，派送范围覆盖英国本土、北爱尔兰、海峡群岛及怀特岛。

:::sources
- [XDP: Track](https://xdp.co.uk/track/) – 寄件人提供的托运单号、邮编、查询页提示
- [XDP: FAQs](https://xdp.co.uk/faqs/) – 状态含义、联系供货商、分拨站自提、在应用中改约
- [XDP: Get In Touch](https://xdp.co.uk/contact/) – Delivery Query 表单、邮箱
- [XDP: About Us](https://xdp.co.uk/about-us/) – 1995 年成立、派送范围、两小时时段、签收凭证
- [XDP: Home](https://xdp.co.uk/) – 大件与非标准货物、服务、应用
- [XDP: 1Man Delivery](https://xdp.co.uk/services/1man-delivery/) – 1 至 3 天、通知、中转号码
- [XDP: 1+ Delivery](https://xdp.co.uk/services/1-plus-delivery-service/) – 3 至 5 天、指定房间、You Are Next、在线客服与邮箱
- [XDP: 2Man Delivery](https://xdp.co.uk/services/2-man-delivery/) – 预约日期、两小时时段
- [XDP: App (web version)](https://app.xdp.co.uk/) – 派送时段、三次尝试、Change Delivery Date、安全放置、不提供分拨站自提、破损、退回、电话、联系表单
- [XDP: Terms and Conditions of Carriage](https://xdp.co.uk/wp-content/uploads/2024/02/XDP-TCFinal.2.24.V1.pdf) – 不保证次日达、高峰期、索赔时限
- [XDP: Procedure Guide](https://xdp.co.uk/wp-content/uploads/2026/03/Procedure-Guide-V.5.pdf) – 33 天与 7 天、在签收单上注明破损
- [XDP: Tracking (track.php)](https://www.xdp.co.uk/track.php) – 邮编与未找到提示
- [Companies House: X D P LIMITED](https://find-and-update.company-information.service.gov.uk/company/03139661) – 注册登记、办公地址
- [Apple App Store: XDP](https://apps.apple.com/gb/app/xdp/id1485292140) – XDP 应用
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Peek 读取的内容
:::
