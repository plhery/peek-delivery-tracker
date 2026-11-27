---
title: Post Haste 新西兰快递追踪：单号、乡村派送与重派指南
description: 追踪新西兰 Post Haste Couriers 包裹：单号与链接格式、Freightways 扫描状态、乡村转派说明、在线重派及网点联系方式。
slug: post-haste-tracking
published: 2026-11-26
updated: 2026-11-26
---

**Post Haste**（*Post Haste Couriers*）是一家于 1977 年加入 **Freightways 集团** 并与 Castle Parcels 合作运营的新西兰本土快递网络（[posthaste.co.nz](https://posthaste.co.nz/)）。公司通过遍布北岛和南岛的 20 余家区域网点及 Delivery Link 合作伙伴提供企业与住宅派送，涵盖同城、区域隔夜达、跨岛隔夜达、两日经济件以及乡村派送区域服务。你可以在 [posthaste.co.nz/tools/track/](https://posthaste.co.nz/tools/track/) 查询包裹，或将 Post Haste 追踪链接或运单号粘贴到上方输入框中。

## Post Haste 运单号（快递券、标签与邮件链接）

Post Haste 依据寄件人所用的预付快递券/快件袋、8 位记账标签、20 位 SSCC 条码或客户自打印标签（如 Despatch IT）而使用多种 Freightways 单号格式：

| 格式（虚构） | 含义 |
| --- | --- |
| `PH48291736` | 快递券或 Express Pack 快件袋：2–4 位字母产品代码（如 `PE` 或 `PH`）+ 5–8 位票号（输入时不含空格）。 |
| `73918462` | 8 位 Post Haste 记账标签号（charge label）。 |
| `KLM4829173` | 客户自打印运单号（字母 + 6–8 位数字）；部分寄件人自打标签还需要发货邮件追踪链接中携带的寄件人账号（`receiver.posthaste.co.nz/?consignmentid=CU..…`）。 |

如果商家通过邮件或短信发来 `receiver.posthaste.co.nz` 或 `posthaste.co.nz` 链接，**请直接将完整链接粘贴到 Peek 中**——需要时系统会自动同时提取运单号和寄件人账号。另见[运单号格式说明](guide:tracking-number-formats)（英文）。

## Post Haste 追踪状态含义

Post Haste 使用共享的 Freightways 追踪后台（同时服务于 Post Haste、Castle Parcels、New Zealand Couriers 和 Now Couriers），在汇报扫描主类与子类代码的同时附带郊区/地区位置：

| Post Haste 代码或扫描 | 对你的含义 | Peek 显示 |
| --- | --- | --- |
| `Label created` | 寄件人发货系统已创建电子面单，派送员尚未揽收。 | “已生成面单” |
| `P`（`Picked Up`） | Post Haste 派送员已向寄件人完成揽收。 | “已揽收” |
| `STAT/RZDEL` | 乡村或非城区派送提示；交由乡村合作伙伴转派通常额外增加 2–3 个工作日。 | “运输中” |
| `STAT/REDEL` | 先前派送未成功后已重新安排并发出派送。 | “运输中” |
| `D`（`Delivery Handed Over` / `Delivered by courier or branch` 给乡村合作方） | 已交接给乡村派送承包商，仍在送往你住址途中，尚未送达家门。 | “运输中” |
| `ONBD` | 已装上当地快递员车辆，今日派送。 | “派送中” |
| `NOND` / `NOND/*` | 未能投递事件（如家中无人并留卡片、场所关门或门禁受阻）。 | “派送未成功” |
| `D`、`3P/D` | 已送达收件地址（其中 `3P/D` 为乡村派送合作伙伴完成的最终交付）。 | “已签收” |

`STAT/HLDFR8` 表示派送未成功后包裹正暂存在网点货运处，但并未说明是等待自提还是安排二次派送：请在线预约重派或联系当地网点确认。

## 新西兰派送时效、乡村区域与重新派送

根据 [Post Haste 寄收件服务](https://posthaste.co.nz/services/sending-and-receiving/)、[重新派送](https://posthaste.co.nz/tools/redelivery/)和[收件帮助中心](https://help.posthaste.co.nz/category/receiving-parcels/)：

- **公布时效：** **同城**企业间市内派送；**区域隔夜达**（通常次工作日午饭前送达）；**全国隔夜达**覆盖北岛和南岛内部及跨岛（商业地址通常次工作日中午前，住宅地址当天结束前）；**两日跨岛经济件**；主要大城市另支持**周六派送**。
- **非城区与乡村地址：** Post Haste 通过乡村派送合作伙伴覆盖新西兰大多数乡村地址（`STAT/RZDEL`），会在城区参考时效基础上额外增加 2 至 3 个工作日。
- **无人值守地址与重派：** 除非提前签具授权免签放置（authority to leave），Post Haste 的标准政策是**不将包裹留在无人地址**。若派送未成功，请凭 16 位派送参考号在 [posthaste.co.nz/tools/redelivery/](https://posthaste.co.nz/tools/redelivery/) 在线申请重派；如没有该参考号，请联系当地网点。前往网点自提须携带有效身份证件。

## Post Haste 包裹延误、暂存或遗失

- **乡村交接在部分追踪页看似“已送达”：** 如果记录显示 `Delivered by courier or branch` 或 `Delivery Handed Over`，通常代表包裹已转交乡村派送承包商（`3P`），而非已送到你家门口——请预留 2–3 个工作日等待乡村派送完成（`3P/D`）。Peek 会将乡村交接保持在“运输中”，不会提前误判为已送达。
- **建单后无扫描或卡在网点：** 若客户自打印运单仅凭单号查不到，请粘贴发货邮件中的完整 `receiver.posthaste.co.nz` 链接以便 Peek 附带寄件人账号查询。如包裹滞留网点，请联系当地 Post Haste 网点或寄件人。
- **遗失或破损理赔：** 请参阅 [help.posthaste.co.nz/category/receiving-parcels/](https://help.posthaste.co.nz/category/receiving-parcels/) 上的遗失与破损指引，并优先联系下单的零售商。另见[显示已签收但未收到](guide:delivered-not-received)（英文）、[包裹遗失](guide:lost-parcel)（英文）、[包裹破损](guide:damaged-parcel)（英文）、[包裹退回寄件人](guide:returned-to-sender)（英文）和[为什么物流停止更新](guide:tracking-not-updating)（英文）。

## Post Haste 客服与新西兰网点目录

[posthaste.co.nz/about-us/contact/](https://posthaste.co.nz/about-us/contact/) 和 [help.posthaste.co.nz/category/receiving-parcels/](https://help.posthaste.co.nz/category/receiving-parcels/) 公布的官方服务渠道：

- **各地网点目录（北岛与南岛）：** [posthaste.co.nz/about-us/contact/](https://posthaste.co.nz/about-us/contact/) 列出北岛网点（Whangārei、North Harbour、Auckland、Hamilton、Tauranga、Rotorua、New Plymouth、Taupō、Gisborne、Napier、Hastings、Whanganui、Palmerston North、Wellington）及南岛网点（Nelson、Blenheim、West Coast、Christchurch、Timaru、Dunedin、Queenstown、Invercargill）的电话与地址，涵盖与 Castle Parcels 共用的网点。
- **在线重新派送工具：** [posthaste.co.nz/tools/redelivery/](https://posthaste.co.nz/tools/redelivery/)。
- **收件人帮助中心与政策：** [help.posthaste.co.nz/category/receiving-parcels/](https://help.posthaste.co.nz/category/receiving-parcels/)。

> 请警惕谎称新西兰快递需要在线刷卡支付小额重派费的诈骗短信。如需重新安排派送，请一律使用 [posthaste.co.nz/tools/redelivery/](https://posthaste.co.nz/tools/redelivery/) 或直接向当地 Post Haste 网点核实。详见[警惕快递诈骗短信](guide:delivery-scam-texts)（英文）。

## 使用 Peek 追踪 Post Haste 包裹

在 [Peek](/) 中添加 Post Haste 包裹最便捷的方式，是直接粘贴发货邮件中的完整追踪链接（`receiver.posthaste.co.nz/?consignmentid=CU..…` 或 `posthaste.co.nz/track?query=`），Peek 会自动识别为 Post Haste，并在需要时提取寄件人账号。如果仅输入裸快递券号、记账标签号或运单号（例如虚构单号 `PH48291736`、`73918462` 或 `KLM4829173`），Peek 无法单凭编号自动锁定 Post Haste（对类似 `PH48291736` 的票号会询问 TNT Italy，对类似 `KLM4829173` 的运单号会询问 Australia Post），因此请在添加时点击“更换”（或在包裹页点击“更换快递公司”）并选择 **Post Haste**（搜索“Post Haste Couriers”或“Posthaste”也可找到）。随后 Peek 会提供可选的“完整物流链接”栏位，方便填入发货邮件链接以携带某些包裹必需的寄件人账号。

Peek 以 Post Haste 名义读取 Freightways 查询接口，并展示阶段（“已生成面单”、“已揽收”、“运输中”、“派送中”、“派送未成功”、“已签收”）、扫描文案、郊区与地区、Post Haste 提供的预计送达日以及签收时间。扫描主类与子类用于判定阶段，不会直接显示在 Peek 客户端中。扫描时间以 UTC 时刻传入并按你的设备本地时区展示。若最新扫描由 Castle Parcels 等另一家 Freightways 网络产生，页面会显示“由……派送”及该网络名称。Peek 不保留签收签名与签收人姓名、送达照片、授权放门标签、邮编或司机信息。

当你在 Peek 中打开包裹页面，或在派送当日开启通知时，Peek 最多每 10 分钟检查一次；在 Post Haste 显示 `ONBD` 当天，只要 Post Haste 自己的追踪系统有响应，会从新西兰时间早上 6 点起每 2 分钟检查一次。其余情况下每小时检查一次。若包裹停留在“已生成面单”或“已揽收”超过 12 小时，或连续两天没有新动态，无论你以何种方式关注，均改为每小时检查一次；若停留在“已生成面单”超过七天，则每 6 小时检查一次。当Post Haste 追踪系统无响应、Peek 仅通过追踪服务跟进时，每小时检查一次，静默一天后每 3 小时一次，派送当天最多每 15 分钟一次。

当最新扫描距今已满四天，且包裹仍处于“已生成面单”、“已揽收”或“运输中”时，Peek 会用该次扫描的日期加以标记，例如“自10月12日周一起没有物流更新”。包裹页面随后会显示“包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 Post Haste 查询。”，并附上指向 Post Haste 收件帮助页面的“向 Post Haste 寻求帮助”链接。如果三周没有新扫描，页面会显示“已超过三周没有新动态。如果还没收到，请联系卖家。Peek 会持续向 Post Haste 查询。”

你可以自由选择“每次扫描”、“重要环节”或“仅派送当天”提醒预设。Peek 仅监控物流更新，无法代你预约重派、授予免签放置授权或联系当地 Post Haste 网点。

## 关于 Post Haste 物流查询的问题

### Post Haste 支持哪些追踪单号格式？

Post Haste 支持追踪快递券/Express Pack 快件袋（2–4 位字母加 5–8 位数字，如虚构单号 `PH48291736`）、8 位记账标签（如虚构单号 `73918462`）、20 位 SSCC 条码以及客户自打印运单号（如虚构单号 `KLM4829173`）——也可直接粘贴发货邮件中的完整 `receiver.posthaste.co.nz` 链接。

### 为什么我的 Post Haste 客户自打印运单号查不到？

部分客户自打印标签（如 Despatch IT 运单）绑定了寄件人公司账号。请直接粘贴发货通知邮件中完整的 `receiver.posthaste.co.nz` 链接，以便查询时附带账号代码。

### Post Haste 派送未成功怎么办？

除非提前授权免签放置，Post Haste 通常要求有人签收。请凭 16 位派送参考号通过 [posthaste.co.nz/tools/redelivery/](https://posthaste.co.nz/tools/redelivery/) 在线申请重派，或致电 [posthaste.co.nz/about-us/contact/](https://posthaste.co.nz/about-us/contact/) 列出的当地网点。

### 为什么 Post Haste 追踪显示 `Delivery Handed Over` 或 `STAT/RZDEL`？

`STAT/RZDEL` 表示收件地址属于乡村或非城区范围，而交接扫描代表包裹已移交乡村派送承包商——通常需在城区时效外再增加 2–3 个工作日才会完成最终投递（`3P/D`）。

:::sources
- [Post Haste: Official Website](https://posthaste.co.nz/) – 新西兰全国快递网络（Freightways 集团）
- [Post Haste: Our Story](https://posthaste.co.nz/about-us/our-story/) – 1977 年加入 Freightways 及与 Castle Parcels 的合作
- [Post Haste: Sending & Receiving Services](https://posthaste.co.nz/services/sending-and-receiving/) – 同城、区域隔夜达、全国隔夜达、两日跨岛经济、周六及乡村派送服务
- [Post Haste: Advanced Track & Trace](https://posthaste.co.nz/tools/track/) – 客户自打标签（含公司账号）、快递券/Express Pack、条码与合约袋说明
- [Post Haste: Redelivery](https://posthaste.co.nz/tools/redelivery/) – 在线重派申请及网点自提身份证件要求
- [Post Haste: Contact & Branches](https://posthaste.co.nz/about-us/contact/) – 北岛与南岛网点目录
- [Post Haste: Receiving Parcels Help](https://help.posthaste.co.nz/category/receiving-parcels/) – 无人签收政策、包裹通知及遗失/破损指引（Peek 链接的帮助页）
- [Universal Parcel Scraper: post-haste](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/post-haste) – Freightways 票号/标签/SSCC/运单查询、`receiver.posthaste.co.nz` 账号链接规则、状态码映射（`P`、`ONBD`、`NOND`、`STAT/RZDEL`、`D`、`3P/D`）与预计到达日解析
:::
