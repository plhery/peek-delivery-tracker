---
title: 新加坡 SPX Express（Shopee Xpress）单号查询与状态说明
description: 查询新加坡 SPX Express（Shopee Xpress）包裹：SPXSG 与 SG 单号区别、中国发往新加坡轨迹状态、400 新元 GST 消费税规则及追踪指南。
slug: spx-express-singapore-chaxun
published: 2026-11-24
updated: 2026-11-24
---

SPX Express Singapore（亦称 Shopee Xpress 或 Shopee Express）负责新加坡境内的包裹派送，主要承运 Shopee 订单，包括由中国卖家发出、经 Shopee 香港分拣中心中转后运往新加坡的跨境包裹。SPX 官方查询网站为 `spx.sg`，你也可以直接把运单号粘贴到上方查询框中追踪。如果是 Shopee 订单，请在 Shopee App 中打开对应订单，从物流信息栏复制运单号。

## 新加坡 SPX Express 单号格式：SPXSG 与 SG 的区别

Peek 收录了新加坡 SPX Express 的两种单号形态（以下均为虚构示例）：

| 单号（虚构） | 归属与说明 |
| --- | --- |
| `SPXSG48291736482K` | `SPXSG` 加 11 位数字加 1 位数字或字母（共 17 位）：SPX 自有运单号，Peek 会立刻识别为 SPX Express Singapore。 |
| `SG482917364829K` | `SG` 加 12 位数字加 1 位数字或字母（共 15 位）：SPX 系统中与该包裹关联的 Shopee 编号，Peek 仅凭格式无法直接断定为 SPX（见下文）。 |
| `SPXSG4829173648` | `SPXSG` 后仅 10 位数字：Peek 无法识别，请检查是否漏了一位数字。 |
| `RA482917367SG` | 2 位字母加 9 位数字加 `SG` 结尾：属于新加坡邮政（Singapore Post），并非 SPX 单号。 |

粘贴到 Peek 时，空格与小写字母均不影响识别。

**订单号不是运单号。** 如果粘贴的内容符合 Shopee 订单号格式，Peek 会提示“这看起来像 Shopee 订单号”，请改从订单物流详情中复制运单号。找不到单号时，请参阅[如何查找快递单号](guide:find-tracking-number)（英文）。

## 新加坡 SPX Express 物流状态含义解析

Peek 读取到的 SPX 追踪记录均为英文原文。以下是 Peek 识别的常见扫描文案，大致按跨境包裹自中国发往新加坡的先后顺序排列：

| SPX 英文轨迹 | 对你的含义 | Peek 显示 |
| --- | --- | --- |
| `[China]Sender is preparing to ship your parcel` | 中国卖家正在备货打包，SPX 尚未拿到实物包裹。 | “已生成面单” |
| `[China] Parcel has arrived at Hong Kong sorting centre` | 包裹已抵达 Shopee 香港分拣中心。 | “运输中” |
| `[China] Parcel has departed from Hong Kong sorting centre` | 已离开香港分拣中心。 | “运输中” |
| `[China] Parcel has been handed over to delivery service provider` | 已移交航空货运服务商安排空运。 | “运输中” |
| `[China] Parcel has cleared export customs` | 已办结中国出口清关，并非新加坡进口清关。 | “运输中” |
| `Parcel has departed from the origin port: Hongkong` | 包裹已从始发口岸香港起运离境。 | “运输中” |
| `Parcel has arrived at the destination port Singapore` | 已抵达新加坡目的口岸：仅为到港记录，并非海关扣留。 | “运输中” |
| `Parcel has cleared import customs` | 已通过新加坡进口海关放行。 | “运输中” |
| `Parcel has been picked up by our local logistics partner for further sorting. You will be notified when the parcel is ready for delivery.` | 已进入新加坡本地派送网络进行末端分拣，就绪后会通知你。 | “运输中” |
| `[Singapore] Parcel has arrived at sorting facility` | 已到达新加坡分拣中心。 | “运输中” |
| `[SPX Express] Parcel is out for delivery` | 快递员正在派送途中。 | “派送中” |
| `Parcel has been delivered` | 包裹已送达签收。 | “已签收” |

**其他状态。** Peek 也能识别 SPX 的其他节点，例如从卖家揽收或投放寄件点（“已揽收”）、投递未成功（“派送未成功”）以及退回卖家（“已退回寄件人”，若退回停滞则显示“需要留意”）。详见[物流状态含义总览](guide:tracking-statuses)（英文）。

## 新加坡 SPX Express 派送说明

出现 `[SPX Express] Parcel is out for delivery` 后，下一条通常就是 `Parcel has been delivered`。如果 SPX 将包裹送往自提点而非上门投递，在运往自提点途中 Peek 也会显示“派送中”。

由于 Peek 无法抓取新加坡 SPX Express 网站页面，本页不列举官方派送时效、派送尝试次数或自提点保管期限。如有疑问请联系卖家或通过 Shopee 订单客服咨询。另请参阅[错过派送怎么办](guide:missed-delivery)（英文）与[逾期未取包裹](guide:uncollected-parcel)（英文）。

## 海外包裹入境新加坡：GST 消费税与海关规定

根据新加坡关税局（Singapore Customs）对空运或邮寄入境海外网购商品的规定：

- **结账时已缴 GST：** 如果每件商品价格不超过 400 新元（S$400），且海外卖家或电商平台已注册新加坡 GST，则售价已包含 GST；卖家会将缴税凭据传给快递商，入境时不再重复征税。这里的价格仅指商品本身，不含运费和保险。
- **入境时征收 GST：** 如果卖家未注册 GST，且整票快件的总 CIF 价值（货价 + 保险 + 运费）超过 400 新元，则在入境新加坡时收取 GST；总值不超过 400 新元则免征进口 GST（烟酒类商品除外）。
- **被重复收取 GST 怎么办：** 若结账时已付 GST 却在入境时被再次收取，需先缴纳进口 GST 以提取包裹，随后凭已缴税凭证向卖家申请退款。

**包裹被海关扣留。** 含有应税品、受管制或违禁品，或总 CIF 价值超过 400 新元且随货缺少发票的快递包裹，可能被新加坡移民与关卡局（ICA）扣留。ICA 会向快递公司出具仓库寄存收据（warehouse deposit receipt）：如需查询处理，请向快递公司索要快件详情和该收据，再联系收据上列明的主管机构。

**防范诈骗。** 新加坡关税局明确表示不会通过短信、邮件或聊天软件索要 GST 付款或个人信息。详见[快递诈骗短信识别指南](guide:delivery-scam-texts)（英文）与[包裹被海关扣留怎么办](guide:customs)（英文）。

## SPX 包裹不更新、丢件或退回怎么办

**单号尚未录入。** 当 SPX 暂时查不到 `SPXSG` 单号时，Peek 会显示“等待 SPX Express Singapore 更新”并持续查询。可能是卖家尚未交件，也请核对单号是否无误。

**停在 `[China]Sender is preparing to ship your parcel`。** 卖家仍在备货尚未发货，请直接询问卖家预计发货时间。

**连续多日无更新。** 跨境包裹在航班途中、海关清关或周末常暂时没有动静。当处于“已生成面单”、“已揽收”或“运输中”的包裹满四天没有新记录时，Peek 会标出最新扫描日期（“自10月12日周一起没有物流更新”）并附上“向 SPX Express Singapore 寻求帮助”链接，同时继续向 SPX 查询；超过三周仍无新动态时会提示联系卖家。详见[为什么物流停止更新](guide:tracking-not-updating)（英文）与[中国跨境包裹追踪指南](guide:tracking-from-china)（英文）。

**显示已签收但未收到。** 请先检查门口周边并询问家人与邻居，随后联系卖家或 Shopee 客服。详见[显示已签收但未收到](guide:delivered-not-received)（英文）。

**遗失或退回。** 物流由发货商家委托承运，请向卖家或 Shopee 申请退款或补发。详见[包裹丢失](guide:lost-parcel)（英文）与[包裹退回寄件人](guide:returned-to-sender)（英文）。

## 新加坡 SPX Express 客服与联系渠道

由于 Peek 无法读取新加坡 SPX Express 网站页面，本页未列出电话号码或邮箱地址——Peek 仅刊登在承运商网站实测读到的联系方式。

Shopee 订单请直接通过 Shopee App 客服处理；其他网店订单请先联系下单商家。新加坡关税局无法查询快递物流进度，明确指引收件人联系承运快递公司。

## 使用 Peek 追踪新加坡 SPX Express 包裹

在 [Peek](/) 追踪 SPX 包裹仅需运单号，不需要账号、手机号或邮编：

- **输入 `SPXSG` 单号：** Peek 会立刻识别为 SPX Express Singapore（“识别出的快递公司”）并直连 SPX 查询；点击“更换”可切换其他承运商。
- **粘贴追踪链接：** 粘贴包含 `SPXSG` 单号的 `spx.sg` 链接（含 `/track?`、`/m/track?` 或 `/detail/`），Peek 会自动提取单号。
- **输入 `SG` 单号：** 仅凭 15 位 `SG` 形态 Peek 无法直接确定承运商，但照常粘贴即可——Peek 跟踪 4,500 多家快递公司，查询时会显示“正在查询物流服务…”。如需手动选定，点击“更换”并在“符合这个单号”下选择 SPX Express Singapore。如有 `SPXSG` 单号建议优先使用，因为 SPX 网站往往仅凭 `SPXSG` 单号才能查到 Shopee 包裹。

添加时若暂未确定承运商，包裹会先显示“暂无物流记录”，同时 Peek 会向聚合追踪服务查询；只要追踪服务唯一返回 SPX Express Singapore 并附带日期节点，Peek 就会自动归入 SPX。

**Peek 展示的内容。** 标准阶段、SPX 英文原文描述，以及仓库、口岸和分拣中心的扫描地点。SPX 每条记录自带精确时间，Peek 会自动换算为你设备的本地时间。Peek 还会保留 SPX 网站页面隐藏的部分步骤（如进口清关与退回途中），同时隐藏卖家所在地、网点办公地址与签收人姓名。SPX 本身不提供预计送达日期、重量或自提点信息，因此 Peek 也不显示。

**从 Janio 交接至 SPX。** 当 Janio 包裹轨迹标明交由 SPX 并给出新加坡 `SPX` 单号时，包裹卡片会显示“由 SPX Express Singapore 派送”并同步向 SPX 查询；若 SPX 仅有预报尚未产生实际扫描，则显示“SPX Express Singapore 已收到预报，尚未收到包裹”。一旦 SPX 出现不早于 Janio 最新记录的扫描，Peek 会改由 SPX 跟进并保留 Janio 前程历史。

当 SPX 网站无法连通时，Peek 会改展示第三方聚合追踪服务返回的内容与原文。

**检查频率与通知。** 在打开包裹页面或预计派送当天开启提醒时，Peek 最高每 10 分钟直连查询 SPX 一次，在 SPX 扫出派送中当天自新加坡时间早上 6 点起最高每 2 分钟查询一次；若仅通过聚合追踪服务跟进，则每小时检查一次、静默满一天后改为每 3 小时一次，在派送当天打开页面或开启提醒时最高每 15 分钟一次。连续两天无新动态（或停留在“已生成面单”、“已揽收”满 12 小时）后转为每小时检查一次；停留在“已生成面单”满七天降为每 6 小时一次。通知预设可选“每次扫描”、“重要环节”或“仅派送当天”。Peek 无法代你联系 SPX、Shopee 或卖家。

## 关于新加坡 SPX Express 追踪的常见问题

### SPX Express 和 Shopee Express 是同一家吗？

是的。Peek 承运商目录同时收录了 Shopee Xpress Singapore 与 Shopee Express Singapore 别名，负责承运 Shopee 订单。SPX 在其他国家运营独立网络（如巴西 SPX Express Brazil），`SPXSG` 单号专属于新加坡网络。

### `SPXSG` 单号和 `SG` 单号有什么区别？

`SPXSG` 是 SPX 自有运单号，Peek 可据此立刻识别；`SG` 则是与之关联的 Shopee 编号。SPX 追踪系统经常只能凭 `SPXSG` 查到 Shopee 包裹，因此优先使用 `SPXSG` 单号。

### `Parcel has been picked up by our local logistics partner` 是什么意思？

表示你的海外包裹已经到达新加坡，并移交给当地末端派送网络准备分拣。SPX 提示就绪后会另行通知，Peek 将其显示为“运输中”。

### 为什么我的 SPX 轨迹迟迟不更新？

如果停留在 `[China]Sender is preparing to ship your parcel`，说明卖家尚未发出；后续在航班、清关或周末也常短暂静默。处于“已生成面单”、“已揽收”或“运输中”满四天无更新时，Peek 会自动标记提醒；若持续无变化，请联系卖家或 Shopee。

:::sources
- [Singapore Customs: 低价值进口商品 GST 消费税规则](https://www.customs.gov.sg/personal-shipment/buying-from-overseas/gst-on-low-value-goods/) – 400 新元门槛、结账或入境缴纳 GST、重复征税退款及防诈骗说明
- [Singapore Customs: 快递与邮政清关程序](https://www.customs.gov.sg/personal-shipment/buying-from-overseas/courier-postal-procedures/) – ICA 扣留处理、仓库寄存收据及快递查询指引
- [Universal Parcel Scraper: SPX Express Singapore](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/spx-sg) – 别名、单号格式、追踪链接、文案与阶段映射
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – 阶段归类逻辑与聚合追踪服务
:::
