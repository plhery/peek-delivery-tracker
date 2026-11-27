---
title: Paack 快递查询：订单号、邮编与 PaackGo 自提点
description: 查询 Paack 包裹：输入订单号与收件邮编、物流状态含义、预计时段、PaackGo 自提点以及 Paack 如何与 Colis Privé 联手。
slug: paack-tracking
published: 2026-11-24
updated: 2026-11-24
---

Paack 是一家为网店提供派送服务的快递公司。查询包裹时，需要在 Paack 查询页同时输入订单号和收件邮编，或将号码粘贴到上方查询框后手动选择 Paack。Paack 会把订单号与邮编配对验证：只要其中一项有误，页面就不会显示任何记录。

## Paack 运单号在哪里找

Paack 查询页要求输入订单号（或包裹面单上的条码号）以及收件邮编。Paack 的多数单号直接沿用商家自己的订单号或面单条码，因此没有固定格式：请查看商家的订单确认和发货通知，或面单标签。找不到号码？请联系商家，或查看[如何查找运单号](guide:find-tracking-number)（英文）。

正因为没有固定格式，订单号可能与其他快递的单号长得一样（以下号码均为虚构）：

| 订单号 | 外观特征 |
| --- | --- |
| `482917369AB` | 字母加数字：不对应任何特定快递。 |
| `48291736` | 8 位数字：Mondial Relay、[Colisweb](carrier:colisweb) 和 Heppner 也采用此长度。 |
| `482917360584` | 12 位数字：[FedEx](carrier:fedex)、MRW、BRT 等多家快递同样使用。 |

只要商家写明由 Paack 派送，无论号码格式如何都属于 Paack。

号码或邮编任一有误时，页面会返回 `Order not found. Incorrect order number or postal code.`。

## Paack 物流状态说明

Paack 页面用四个阶段概括全程（收到订单信息、到达 Paack 分拨中心、派送中、已签收），下方轨迹还会补充中间步骤。Peek 会以统一状态表述展示每一步：

| Peek 显示 | 对你意味着什么 |
| --- | --- |
| `已收到快件信息` | 商家已将你的订单信息发给 Paack，Paack 尚未收到实物包裹。 |
| `快件已揽收` | Paack 在其分拨中心对包裹的首次扫描。 |
| `运输中` | Paack 已拿到包裹，但尚未外出派送。 |
| `Courier assigned` | 已指派快递员负责派送，每次重新派送前都会再次出现。 |
| `派送中` | 快递员已带件外出，将按所示时段派送。 |
| `Delivery attempt failed`、`派送出现问题` | 本次未能送达：快递员无法投递，或你选择的 PaackGo Point 自提点关门、已满或拒收。 |
| `New delivery attempt scheduled` | Paack 将在所示新时段重新派送。若 Paack 在派送失败的同一时刻立即排期，该动作会归入失败记录中，Peek 不单独列出。 |
| `待取件` | 已送达 PaackGo Point 等待自提；Paack 官网会标明最晚取件日期。 |
| `已签收` | 已送达，或已在 PaackGo Point 取走。 |
| `正在退回寄件人`、`已退回寄件人` | 正在退回或已退回商家（例如逾期未取、拒收或在 PaackGo Point 未付款）。 |
| `快件异常` | 包裹出现异常（例如在 PaackGo Point 破损或丢失），请联系商家。 |

## Paack 派送时段与 PaackGo Point 自提点

**预计派送时段。** Paack 页面会给出按收件地址当地时间计算的预计派送日期和时间段。派送未成功后，显示的时段会更新为下一次尝试的时段。

**PaackGo Point** 是 Paack 的自提点。包裹到达后，Paack 页面会显示自提点名称与地址、最晚取件日期、取件码以及二维码。若未在期限内取走，包裹会退回商家。

## Paack 包裹延误、未送达或破损怎么办

你是向商家购买商品而非直接向 Paack 购买，因此退款、退货和取消订单均须联系商家：

- **延误或无更新：** 先看[为什么物流停止更新](guide:tracking-not-updating)（英文），再联系商家。
- **已退回商家或没有单号：** 请联系商家。
- **丢失或破损：** 请联系商家。
- **显示已签收但未收到：** 先询问家人和邻居，再联系商家。更多见[显示已签收但未收到](guide:delivered-not-received)（英文）。

## 使用 Peek 跟踪 Paack 包裹

遇到 `PK` 加 18 位数字（Paack 为 Evri International 派送包裹使用的单号）时，[Peek](/) 会立刻识别为 Paack 且无需邮编即可跟踪。遇到 Paack 包裹上会出现的 `5340` 加 14 位数字面单号时，点击“更换”会在“符合这个单号”下把 Paack 排在首位，位于其他使用 18 位数字的快递之前（在比利时排在 bpost 之后）。对于 SHEIN 包裹号（`C` 或 `E`、六位年月日加 8 位数字），Peek 也会在候选里列出 Paack。Paack 的其他单号没有专属格式，Peek 无法主动询问 Paack 是否有记录：带字母的号码通常不会匹配到任何快递（提示“开始跟踪时 Peek 会自动识别”），纯数字号码则可能匹配到同长度的其他快递。此时点击“更换”选择 Paack，或直接粘贴来自 mydeliveries.paack.app 或 paack.co/shipments/tracking、带 `tracking_number=` 的查询链接。

接着填写“收件邮编”。该栏标为可选是因为 `PK` 单号不需要邮编，但 Paack 其他订单必须填写邮编才能查出结果，且链接里的邮编不会被使用。不填邮编的 `PK` 单号会通过 Evri International 查询；填写邮编后，Peek 会先查询 Paack 官网，若 Paack 未找到该配对或无响应再回退到 Evri International。如果你之前输入过邮编，添加包裹或点击“更换快递公司”时，输入框下方会给出快捷建议（“使用 …”）。Paack 只在单号与邮编完全匹配时才返回数据，配对错误看起来就像查无此号。邮编会发往 Paack，并在必要时发往物流跟踪服务。Peek 会把单号转为大写并去掉空格、点号和短横线；如果查不到，请同时在 Paack 官网按商家原文输入试一下。

Peek 会显示状态、完整轨迹以及按收件地址当地时间显示的预计派送日和时段（邮编以 35 或 38 开头按加那利群岛时间）。轨迹时间会换算为你设备的时区：在 peektracker.com 以及 iPhone 和 Android App 中，底部会注明“时间按你所在的时区显示：…”。包裹到达 PaackGo Point 时，Peek 应会显示网点名称和地址（尚未在实际包裹上观测到），但绝不显示取件码、二维码或截止日期，查看这些请打开 Paack 官网。各步骤以 Peek 统一表述展示，PaackGo 自提完成显示为“已签收”。Paack 不提供扫描地点也不指向其他快递，因此 Peek 不会转而跟踪其他快递；不过 `PK` 单号的轨迹来自 Evri International，会保留其原文表述和地点。商家名以及你的姓名和地址都会被隐去。Peek 最高每 10 分钟检查一次；在 Paack 扫描包裹外出派送的当天，自当地时间早上 6 点起（若 Paack 给出了派送时段，则从时段开始前半小时起），只要 Paack 或 Evri 自己的查询系统正常响应，最高每 2 分钟检查一次（通过物流查询服务时，每 15 分钟一次）。通知可选“每次扫描”、“重要环节”或“仅派送当天”。连续两天无新动态后（若仍停留在“已生成面单”或“已揽收”，则为 12 小时后），改为每小时检查一次。

## Paack 快递查询常见问题

### Paack 是什么快递公司？

是一家面向网店的尾程派送公司。Paack 与 Colis Privé 已于 2026 年夏季联手，并将自 2027 年 1 月起逐步统一使用 Paack 品牌，业务覆盖法国、比利时、卢森堡、西班牙和葡萄牙。在此之前，Colis Privé 表示派送和查询一切照常，按商家写明的快递查询即可。

### 为什么 Paack 需要我输入邮编？

Paack 只向同时掌握订单号和收件邮编的人展示包裹信息，两者组合相当于密码。只要任意一项不对，页面就会像单号不存在一样返回 `Order not found`。请务必输入包裹的收件地址邮编。

### Paack 重新安排派送是什么意思？

说明 Paack 会再次尝试派送。派送失败后，Peek 显示 `Delivery attempt failed`，Paack 官网会给出新的日期和时段。若 Paack 在失败当刻立刻排好下次派送，它会并入失败记录而不会单独列条；若是稍后排期，则会显示 `New delivery attempt scheduled`。寄往 PaackGo Point 时若因网点关门、满柜或拒收而改期，Peek 显示为 `派送出现问题`。

### Paack 派送要多久？

Paack 查询页会显示你包裹的预计派送日期和时段，Peek 会同步显示同一窗口。具体可选的配送时效请向商家确认。

:::sources
- [Colis Privé: Colis Privé and Paack unite under one brand: Paack](https://colisprive.fr/en/blog/colis-prive-and-paack-unite-under-one-brand-paack/) – 2026 年 10 月 16 日公告：2026 年夏起联手、2027 年 1 月起统一启用 Paack 品牌、覆盖五国、期间派送与查询照常
- [Universal Parcel Scraper: Paack](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/paack) – 订单号或面单条码配收件邮编、查无记录提示、单号格式、各步骤及 Peek 存储的表述、派送时段、PaackGo Point 自提点
:::
