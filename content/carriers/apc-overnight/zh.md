---
title: APC Overnight 物流查询：单号格式、状态与派送未成功的处理
description: 查询英国 APC Overnight（The APC）包裹：7 位与 22 位单号、Carded 等状态含义、改派选项与本地分拨站联系。
slug: apc-overnight-tracking
published: 2026-11-26
updated: 2026-11-26
---

APC Overnight（现自称 The APC）是英国规模最大的独立包裹快递网络，由超过 90 家本地分拨站组成，多数包裹于次工作日送达。你可以凭托运单号（consignment number）和收件邮编在 APC Choice 查询，也可以直接将 22 位长单号或查询链接粘贴到上方输入框。单号位于寄件人的发货确认、APC 或网店发送的邮件/短信，以及 APC 留下的 “Sorry we missed you” 通知卡上。

## APC 运单号格式与结构

APC 提到的是一个编号：“托运单号（consignment number）是分配给包裹的 7 位数字追踪参考号。”其网页表单要求同时输入该 7 位单号和收件邮编。

不过网店常常会发给你一个更长的编号：包含在 APC Choice 链接（`apcchoice.apc-overnight.com/track-parcel?id=…`）里的 22 位数字。它以订舱日期开头，末尾则是同一个 7 位托运单号。

:::anatomy 20260715 4829173 7364058
- 20260715 | 订舱日期（20YYMMDD），此处为 2026 年 7 月 15 日
- 4829173 | 中间 7 位数字
- 7364058 | APC 官网表单要求填写的 7 位托运单号
:::

凭借链接里的 22 位长单号，无需邮编即可在 APC Choice 打开物流轨迹；仅查看签收证明或修改派送时才需要邮编。

以下两点值得留意：

- 开头不是有效日期的 22 位数字（例如 `4829173640582917364058`）不属于 APC；同等长度也可能属于 [USPS](carrier:usps)、[Austrian Post](carrier:austrian-post) 或 Estafeta。
- APC 单号与[英国皇家邮政（Royal Mail）](carrier:royal-mail)（以 `GB` 结尾）、[Parcelforce](carrier:parcelforce)、[Evri](carrier:evri) 或 [DPD UK](carrier:dpd-uk) 均不相同。

如果查不到任何记录，APC 常见问题列出常见原因：寄件人尚未交寄、尚未扫描入网或单号输错。请稍后再试或向寄件人核实；完全找不到单号请参阅[如何查找运单号](guide:find-tracking-number)（英文）。

## APC 物流状态含义

APC Choice 仅提供英文界面，先展示整票概要（发货日期、重量、次工作日等服务级别），再列出每件包裹的 “Parcel journey” 与 “Delivery summary”，每行一条扫描并在括号内标注日期与时间。

| APC 显示 | 对你的含义 |
| --- | --- |
| `At sending depot` | 寄件人所在地分拨站已揽收并扫描入库。 |
| `At Hub` | 位于 APC 的中央枢纽，即 Cannock 的国家分拣中心（National Sortation Centre）。 |
| `At delivery depot` | 已抵达负责为你派送的当地分拨站。 |
| `Out for delivery` | 已装车今日派送，APC 会提供 2 小时预计时段。 |
| `Carded` | 司机上门但家中无人，已留下 “Sorry we missed you” 通知卡。 |
| `Delivery Rearranged By Customer` | 你（或同住人）已在 APC Choice 更改了派送日期或选项。 |
| `Delivered` | 已签收送达；输入邮编即可查看签收证明。 |
| `Returned To Sender` | 正在退回商家，例如派送未成功后一直没有回复。 |

`Delivery Rearranged By Customer` 不代表包裹移动，包裹会留在原地等待新日期。如果扫描之间暂时无更新，APC 说明包裹可能在分拨站转运途中、等待当地分拨站分拣，或司机扫码器出现故障。何时需要跟进可参阅[物流停止更新的原因](guide:tracking-not-updating)（英文）。

## APC 派送时效、派送未成功与分拨站自提

APC 表示大多数英国包裹视地区于发货次工作日送达。寄件人可选次日 10:30 前、12:00 前（英国时间）或标准次工作日；很多地区如由寄件人提前安排也支持周六派送。若网店向 APC 提供了你的联系方式，排班后你可能会通过邮件或短信收到 2 小时送达窗口，APC Choice 还会显示司机距离有多近。

在送达前（即使已留通知卡），只要服务及寄件人允许，你都可以在 APC Choice 更改安排：

- 重新预约合适的送货日期；
- 改至当地 APC 分拨站自提；
- 指定安全放置点或邻居代收；
- 转投至全英超过 2,500 家 Parcelly 门店自提。

**安全放置点：** 仅在获得寄件人许可，或在允许情况下由你在 APC Choice 授权后，APC 才可无签收留放。商业地址不提供安全放置点或邻居代收选项，且商业地址自英国时间早上 8 点开始派送。

**留卡之后：** 司机当天无法二次上门，当天也不能去分拨站自提。首次派送未成功后你可以再改约一次；重约沿用原服务等级，如需定时达或周六升级须由寄件人办理。

**分拨站自提：** 须在英国时间 19:00 前预约次日自提，前往时携带身份证明与住址证明，并提前核对分拨站营业时间。

**三个工作日：** 若派送未成功后 **3 个工作日**内未收到你的回复，APC 可能会将包裹退回寄件人。

## APC 包裹延误、破损或遗失

由于 APC 的运输合同是与寄件人签订的，理赔需由网店处理。针对破损，APC 常见问题明确指引：“请直接联系你的寄件人。”请务必尽早通知网店，因为网店向 APC 报损的时限很短——破损或部分短少须在签收后 **3 个工作日**内书面通知 APC。务必保留原包装箱与填充物，否则 APC 可能拒赔。APC 对寄件人的赔付上限为普通包裹托运 £100、小件包 £50。

如果显示已签收但未收到，可输入邮编在 APC Choice 查看签收凭证（照片、GPS 点位、日期与时间），随后联系网店并参阅[显示已签收却未收到](guide:delivered-not-received)（英文）。

## 联系 APC 客服

APC 没有面向收件人的全国统一电话、在线聊天或通用邮箱，其官网列出以下渠道：

- **本地分拨站：** 在 [Depot Finder](https://apc.co.uk/depot-finder/) 输入邮编即可查看联系方式与营业时间；APC Choice 也会在 “APC Pinpoint” 下方显示派送分拨站名称及邮箱。
- **客服咨询表单：** 在 [apc.co.uk/contact-us](https://apc.co.uk/contact-us/) 填写托运单号与收件邮编，APC 会转发给你所在地的分拨站，由其电话或邮件回复。

> APC 声明绝不会为派送要求付款或索取银行卡信息。切勿点击可疑链接：请将诈骗邮件转发至 `phishing@apc-overnight.com` 并向 Action Fraud 举报。

## 使用 Peek 跟踪 APC 包裹

将 22 位数字单号或带 `id=` 参数的 `apcchoice.apc-overnight.com` 链接粘贴到 [Peek](/) 即可，无需邮编。由于 22 位格式与其他快递共用，Peek 会同时询问 APC Overnight 和 Austrian Post，APC 查有记录时显示“APC Overnight 有这个包裹的记录。”开头不含有效日期的 22 位数字不会交给 APC 查询，也不是 APC 单号，请向网店核实。单独的 7 位托运单号无法在此跟踪，Peek 也不读取 `apc.co.uk` 链接。

APC 的时间不带时区：Peek 按英国时间解析并列出 APC 自身轨迹，同时可显示重量。`Delivered` 和 `Out for delivery` 以 Peek 自身文案呈现，其余状态保留 APC 英文原文。页面不提供预计送达日、2 小时时段、自提点或签收证明，改派仍须在 APC Choice 完成。

当你正在 Peek 中查看包裹，或派送当天已开启通知时，Peek 最多每 10 分钟查询一次；在显示 `Out for delivery` 的当天，只要 APC Choice 正常响应，自英国时间早上 6 点起每 2 分钟查询一次（需要通过浏览器读取时每 5 分钟一次；只有物流查询服务响应时每 15 分钟一次）；其余时候每小时查询一次。连续两天无新动态（或在“已生成面单”/“已揽收”停留满 12 小时）后也每小时一次，在“已生成面单”停留满 7 天后每 6 小时一次。

若最新扫描已过 4 天，且包裹仍处于“已生成面单”、“已揽收”或“运输中”，Peek 会按该扫描日期做出提示，例如“自10月12日周一起没有物流更新”。包裹页面会显示“包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 APC Overnight 查询。”，并提供前往 APC 联系页面的“向 APC Overnight 寻求帮助”链接。若超过三周没有新扫描，则会提示“已超过三周没有新动态。如果还没收到，请联系卖家。Peek 会持续向 APC Overnight 查询。”

Peek 可以按“每次扫描”、“重要环节”或“仅派送当天”通知你，但无法代你联系 APC 或网店。

## 关于 APC 物流查询的问题

### 我的 APC 包裹现在在哪里？

查看 APC Choice 的最新一条扫描即可知晓是在寄出分拨站、Cannock 枢纽、本地派送分拨站还是已经装车。如需更多详情，请通过 Depot Finder 联系当地分拨站。

### APC 会替我保管包裹多久？

派送未成功后若 3 个工作日内未收到你的回应，APC 可能会将包裹退回寄件人，请在此之前完成改期或预约自提。

### APC 几点派送？

取决于寄件人订购的服务：次工作日 10:30 前、中午 12:00 前（英国时间）或次工作日全天。包裹派送当天，APC Choice 及 APC 的邮件或短信会提供 2 小时送达窗口。

### APC 和 Parcelforce 是同一家公司吗？

不是。APC 是 The Alternative Parcels Company Limited，是由各地分拨站组成的独立网络；Parcelforce 是另一家快递公司，其单号以 `GB` 结尾或以 `PBAB` 开头。

:::sources
- [APC: Receiving a Parcel](https://apc.co.uk/receiving-a-parcel/) – 次工作日、服务、APC Choice 选项、安全放置点、邻居代收、派送未成功、分拨站自提、三天、防诈骗提示
- [APC: APC Choice tracking and FAQ](https://apcchoice.apc-overnight.com/track-parcel) – 7 位托运单号及其位置、状态、2 小时时段、物流暂停更新、可再改约一次、商业地址、破损经寄件人处理
- [APC: Contact Us](https://apc.co.uk/contact-us/) – 客服咨询表单转交当地分拨站
- [APC: Depot Finder](https://apc.co.uk/depot-finder/) – 按邮编查询分拨站联系方式与营业时间
- [APC: Terms and Conditions of Carriage](https://apc.co.uk/terms-conditions-of-carriage/) – 索赔期限、£100 上限、包装、三个工作日、签收证明
- [APC: Home](https://apc.co.uk/) – 1994 年成立、超过 90 家成员分拨站、公司名称、国家分拣中心
- [APC: Our Products](https://apc.co.uk/our-products/) – Parcelly 门店、司机位置、照片与 GPS 签收凭证
- [APC: Parcelly partnership expands to 2,500 stores](https://apc.co.uk/the-apc-expands-parcelly-partnership-to-2500-stores-across-the-uk/) – Parcelly 门店网络
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – 查件程序从 APC Choice 读取的内容及其识别单号的方式
:::
