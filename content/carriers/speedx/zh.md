---
title: SpeedX 物流查询：单号格式、状态说明与客服联系
description: 查询美国 SpeedX 包裹：SPX 单号格式与查找方式、英文物流状态中文说明、派送未成功与签收照片查看，以及客服聊天与联系方式。
slug: speedx-tracking
published: 2026-11-24
updated: 2026-11-24
---

SpeedX（Speed Xpress Inc.，位于纽约州 New Hyde Park）是一家美国末端派送公司，负责将网购订单直接送货上门。你可以在 [tracking.speedx.io](https://tracking.speedx.io/) 查询包裹，或直接把单号粘贴到上方输入框中。单号来自你下单的商家：SpeedX 会先为商家生成面单，因此商家在 SpeedX 实际揽收包裹之前就已经拿到单号。

## SpeedX 运单号：格式与查找位置

SpeedX 表示其所有运单号均以 `SPX` 开头。实际格式为 `SPX` 加 3 位字母，再加 12 位或 18 位数字。以下为虚构示例：

| 格式（示例） | 说明 |
| --- | --- |
| `SPXLGA482910573649` | SPX、3 位字母加 12 位数字（共 18 位） |
| `SPXLGA482910573649182736` | SPX、3 位字母加 18 位数字（共 24 位） |

Peek 会自动忽略大小写和空格。

**在哪里查找单号。** 请查看商家的发货确认邮件或订单详情页。如果找不到，SpeedX 建议直接询问零售商；[如何查找运单号](guide:find-tracking-number)（英文）也列出了其他排查位置。此外，你可以在 SpeedX 官网订阅短信通知，包裹进入派送中时会收到提醒。

**容易混淆的单号。** `SPX` 后面只跟数字的单号不符合上述两种格式。以 `SPXPH` 加数字组成的单号同样不是 SpeedX——那是菲律宾的 SPX Express，属于另一家公司。如果一个 `SPX…` 单号多了一位或少了一位数字，请对照商家邮件重新核对。SpeedX 的单号与 UniUni、[GOFO](carrier:gofo) 或 [USPS](carrier:usps) 从不重叠。如果单号依然查不到记录，SpeedX 建议向商家核实，因为包裹可能由其他末端派送公司承运。

## SpeedX 物流状态含义

SpeedX 的官方查询页面仅提供英文。以下按常见顺序列出包裹会经过的各条状态。

| SpeedX 状态原文 | 对你的含义 | Peek 显示 |
| --- | --- | --- |
| `Shipping Label Created` | 商家已生成面单，SpeedX 尚未拿到包裹。 | “已生成面单” |
| `Pickup Requested` | 商家已预约 SpeedX 上门揽收，SpeedX 尚未拿到包裹。 | “已生成面单” |
| `Parcel Picked Up` | SpeedX 司机已从商家处揽收包裹。 | “已揽收” |
| `Processing at Origin Facility` | SpeedX 已收到包裹，正在始发分拣中心处理。 | “运输中” |
| `In transit to destination facility` | 正在 SpeedX 各分拣中心之间转运。 | “运输中” |
| `Processing at Transit Facility` | 正在途中转运中心分拣。 | “运输中” |
| `Arrival at Destination Facility` | 已抵达负责你所在区域的目的地站点。 | “运输中” |
| `Processing at Designated Facility` | 正在当地站点分拣并分配给派送司机。 | “运输中” |
| `Out for Delivery` | 司机已取件，预计今天为你派送。 | “派送中” |
| `Attempted Delivery: No access` | 司机无法进入送达位置，如门禁、公寓楼、施工或恶劣天气。 | “派送未成功” |
| `Delivered` | 已签收。官网页面会注明放置地点（如收发室）并附送达照片。 | “已签收” |
| `Waiting for collection from Parcelshop` | 当次派送结束后，包裹被存放在合作包裹点等待你前去领取。 | “待取件” |
| `Self picked up from Parcelshop` | 包裹已从合作包裹点取走。 | “已签收” |
| `Parcel Void` | SpeedX 标记该包裹存在异常，请联系商家。 | “需要留意” |
| `Exception- Parcel not found at the sort facility` | 分拣中心未能找到该包裹，请联系商家。 | “需要留意” |
| `Parcel Disposal` | SpeedX 报告包裹出现异常但未说明原因，请向商家询问后续处理。 | “需要留意” |

只要物流仅显示 `Shipping Label Created`，就表示 SpeedX 尚未收到包裹，物品仍在商家或其仓库中。

派送尝试也可能附带其他原因，例如针对歇业商铺的 `Attempted Delivery:Building Closed/Weekend/Holiday`，Peek 同样会将其归类为“派送未成功”。在派送未成功记录下方，SpeedX 会附上后续说明：`The package is undeliverable and schedule to redelivery` 表示会重新安排派送，`The package is undeliverable and return to facility` 表示包裹退回站点。Peek 会在状态文案后保留该附注。

当最新记录为异常状态（如 `Parcel Void`、`Parcel Disposal` 或分拣中心未找到包裹）时，SpeedX 会将其归入 `Last Mile Intercept` 或 `Last Mile Undelivered` 等类别。SpeedX 未公开解释这些分类，请直接联系商家确认。

## SpeedX 派送规则、派送未成功与取件

**海外跨境包裹。** SpeedX 也负责派送从美国境外寄出的订单。其跨境服务可在美国主要航空枢纽接收货物，先通过自有报关团队完成美国清关，再分拣进入本地派送网络。有关关税及支付责任，请参阅[清关中的包裹指南](guide:customs)（英文）。

**周末派送。** SpeedX 在全部覆盖区域提供周六派送（当地时间上午 8 点至晚上 8 点），部分地区也在周日同一时段派送。公司不提供预约送达时间段，也不会在司机临近时发送提醒。

**签收与送达照片。** 大多数 SpeedX 包裹无需本人签收；含酒精等年龄限制商品可能需要年满 21 周岁的成年人签收。如果家中无人，司机会放下包裹并拍照留证。如需查看照片，请在 speedx.io 查询单号并输入你的收件邮编（ZIP code）。

**派送说明与改地址。** 你可以通过 SpeedX 的聊天机器人 Miles 提供运单号来提交派送说明（如门禁密码、放置位置、营业时间、联系电话），司机会同步看到这些备注，但你无法直接联系司机本人。只要及时通知，SpeedX 可以修改街道地址或公寓门牌号；若需更改城市、州或邮编，请联系商家。

**派送未成功。** 如果记录显示派送未成功而你认为不应失败，请先核对商家订单上的收件地址，再通过在线聊天提交更正信息或派送说明。SpeedX 表示随后会在 1 至 2 个工作日内重新派送。SpeedX 未公布最多尝试派送几次，也未公布包裹保管天数。

**自提。** SpeedX 说明收件人不能前往其运营站点自提包裹，包裹必须由 SpeedX 派送。不过部分包裹在一轮派送后可能会被转存至合作包裹点：此时物流会显示 `Waiting for collection from Parcelshop`，你可前往该包裹点领取。

## SpeedX 包裹停滞、未收到或丢失

**物流不更新。** SpeedX 建议在状态连续三个工作日没有变化时联系零售商。更多排查办法见[物流不更新怎么办](guide:tracking-not-updating)（英文）。

**显示已签收但没拿到包裹。** SpeedX 自己也说明，包裹有时会在实际送达前就被标记为已签收，或被放在住宅周围不太显眼的位置。请先查看送达照片，再检查前门廊、后门廊、收发室或信箱、车库、房屋侧面及遮蔽处，并询问同住人或楼内人员。先通过 Miles 告知 SpeedX，随后联系商家处理后续事宜。另可参阅[显示已签收但未收到](guide:delivered-not-received)（英文）。

**丢失、破损或地址错误。** SpeedX 不受理收件人提交的理赔申请。请携带订单号、运单号及相关沟通记录联系商家申请理赔、退款或补发；如属破损，商家可能会要求提供照片。SpeedX 未就此公布受理期限。

**退货。** 包裹一旦签收，退货须通过原购买商家办理，SpeedX 不提供上门取件退货服务。如果包裹尚未送达，你可以联系 Miles 申请拦截派送，由 SpeedX 将包裹退回商家。

## SpeedX 客服电话与在线聊天

SpeedX 在其[客服支持页面](https://speedx.io/customer-service-and-support/)列出的美国收件人联系渠道如下：

- **在线聊天：** 聊天机器人 Miles，每天美东时间 8:00 至 20:00 提供服务。打开查询页并输入单号后，屏幕右下角会出现聊天入口。
- **电话：** 1-949-667-6769，周一至周五美东标准时间 9:00 至 21:00，周末 11:00 至 20:00。同一页面上也同时写着 SpeedX 不设电话客服并引导使用 Miles，因此致电后大概率会被转到在线聊天。
- **电子邮件：** speedxops@speedx.io。

## 使用 Peek 查询 SpeedX 包裹

只要输入上述任一格式的 SPX 单号，或包含单号的 tracking.speedx.io 链接，[Peek](/) 就会立即识别为 SpeedX。

Peek 直接读取 SpeedX 官方查询页面，显示最新状态、每条扫描记录及其所在城镇（并在地图上标出，时间按 SpeedX 记录显示），同时展示送达时间以及 SpeedX 页面提供的预计派送日期。Peek 不会显示你的详细收件地址或送达照片，如需查看照片请前往 SpeedX 官网。如果 SpeedX 在送达记录中注明了放置位置，签收记录会保留该说明；在分享链接中则仅显示“已签收”。其他扫描记录（包括 `Out for Delivery`）按 SpeedX 原文展示。由于 SpeedX 自行完成末端派送，因此不涉及交接给其他快递公司的后续追踪。

当你打开包裹页面查看，或在派送当天开启通知时，只要 SpeedX 官方查询页正常响应，Peek 最高每 10 分钟检查一次，而在 SpeedX 扫描包裹进入派送中的当天早上 6 点（当地时间）起最高每 2 分钟检查一次（通过物流查询服务时，在派送当天打开页面或开启提醒时最高每 15 分钟检查一次）；其余时间每小时检查一次，并在状态变化时发送通知。若连续两天没有新动态（包裹仍处于“已生成面单”或“已揽收”时为 12 小时），Peek 会转为每小时检查一次。若包裹处于“已生成面单”、“已揽收”或“运输中”且连续四天没有新扫描记录，Peek 会提示物流已停滞，并提供前往 SpeedX 支持页的入口（“向 SpeedX 寻求帮助”）。如果 SpeedX 官网暂时无响应，其他物流查询源很少收录 SpeedX 包裹信息，因此可能需要等其官网恢复后才能看到更新。

## SpeedX 物流查询常见问题

### SpeedX 物流查询正规吗？

是的。SpeedX 是 Speed Xpress Inc. 旗下品牌，这家末端派送公司于 2022 年在纽约创立，负责为全美范围内的电商平台和线上商家派送订单。

### 我可以自己去取 SpeedX 包裹吗？

不能前往 SpeedX 站点自提：SpeedX 不允许收件人到其设施取件，只能由司机派送。不过，如果物流显示 `Waiting for collection from Parcelshop`，说明包裹在派送后被存放到了合作包裹点，你可以前往该包裹点领取。

### SpeedX 派送需要多久？

SpeedX 没有面向收件人公布统一时效。它向发货商家提供次日达和隔日达服务，页面或订单上的预计送达日期均由商家提供，SpeedX 说明该日期不一定等同于实际送达日。

### 为什么我的 SpeedX 运单号查不到信息？

SpeedX 查询页提示，这可能是因为寄件人尚未向 SpeedX 传回包裹数据，或者单号输入有误。SpeedX 建议向商家核对单号，因为你的包裹也有可能由另一家派送公司承运。

:::sources
- [SpeedX: Customer Service and Support](https://speedx.io/customer-service-and-support/) – SPX 前缀、状态词汇、短信提醒、周末派送、签收与照片、派送说明、改地址、不支持站点自提、延误、提前标为签收、理赔、退货、电话、邮箱与聊天机器人服务时间
- [SpeedX: Tracking](https://tracking.speedx.io/) – 状态原文、附注、异常类别、查无单号提示与仅英文界面
- [SpeedX: Tech-Enabled Last Mile Delivery](https://speedx.io/) – 末端派送商定位、由 SpeedX 生成面单、美国邮编覆盖、次日达与隔日达服务及送达照片凭证
- [SpeedX: Cross-Border Shipping](https://speedx.io/cross-border-shipping/) – 美国航空枢纽接收海外包裹、自有报关清关及分拣入网说明
- [SpeedX: About Us](https://speedx.io/about-us/) – 2022 年成立于纽约及美国服务城市
- [SpeedX: Privacy Policy](https://speedx.io/privacy-policy/) – Speed Xpress Inc. 主体名称与纽约州 New Hyde Park 地址
:::
