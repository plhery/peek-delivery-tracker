---
title: Whistl 英国包裹追踪：末端派送承运商、状态与客服
description: 追踪英国 Whistl（Parcelhub）包裹：参考号来源、英文状态含义、皇家邮政/Evri/DPD 等末端承运商交接规则及客服联系。
slug: whistl-tracking
published: 2026-11-26
updated: 2026-11-26
---

Whistl（原名 TNT Post UK）是一家英国物流公司，替线上商家发运包裹并将其交由 [Royal Mail](carrier:royal-mail)、Evri 或 DPD 等承运商执行末端派送。你可使用商家发货通知邮件中的参考编号或追踪链接，在 Whistl 的 Parcelhub 追踪页面查询进度，或直接将追踪链接粘贴到上方输入框中。包裹移交后，实际执行派送的承运商自有单号和追踪页面通常能提供更详尽的信息。

## Whistl 追踪单号：在哪里查找

Whistl 未公布统一的单号格式。其指南指出，追踪器支持“按 UPI（通常为单件条码）或 Consignment Number（托运单号）”检索：UPI 标识单件包裹，Consignment 代表同批寄给你的全部包裹。具体参考号随商家与承运商而异，既可能是字母加多位数字，也可能是末端派送承运商的自有条码。

该参考号通常位于商家的发货邮件中，往往附带直达 Whistl 追踪页面的链接。对于全程追踪包裹，末端承运商也会在派送当天以自身品牌向你发送邮件和短信，并附上其自有单号和追踪入口。

Whistl 追踪页面要求输入“Tracking Reference”（追踪参考号）及可选邮编，随后展示每件包裹的“Tracking Number:”、扫描历史，以及下单所选的“Carrier:”和“Service:”。

参考号可能直接采用其他承运商（例如末端派送承运商）的格式：

| 单号格式（虚构） | 对应承运商样式 |
| --- | --- |
| `RR482917367GB` | Royal Mail（2 位字母 + 9 位数字 + `GB`） |
| `H00KLM0048291708` | Evri 16 位条码，详见 [Evri 追踪](carrier:evri) |
| `48291736482917` | 14 位数字：DPD UK 等多家承运商格式，详见 [DPD UK](carrier:dpd-uk) |
| `ZDBKLM482917` | XDP，详见 [XDP 追踪](carrier:xdp) |

如需辨认单号归属，请参阅[单号格式指南](guide:tracking-number-formats)（英文）。

## Whistl 追踪状态含义

Whistl 追踪页面仅提供英文，并将各承运商的扫描统一转换为标准化的“normalised events”；全程追踪件还会附带承运商原始备注。按包裹通常经历的顺序如下：

| Whistl 显示状态 | 对你的含义 |
| --- | --- |
| `Shipment Created` | 商家已预约发货，包裹尚未移动。 |
| `Item receipted and processed by Whistl` | Whistl 已在其分拨中心接收并处理该包裹。 |
| `Handed over to Delivery Carrier` | 页面中列出的末端承运商已接手负责派送。 |
| `Handed over to Royal Mail` | Royal Mail 已接手执行末端派送。 |
| `Arrived At Depot` | 已抵达途经的分拨站点。 |
| `Shipment is Out for Delivery` | 今日正在派送中。 |
| `DELIVERED - Left as authorised` | 已按你的授权放置指定位置，无需签收。 |
| `Left with Neighbour` | 已交由邻居代收。 |
| `Delivered col. point` | 已送达自提点，等待你前往领取。 |
| `No-one to receive` | 派送时无人签收：请查看承运商留下的通知卡或消息。 |
| `Held by customs` | 正由[海关](guide:customs)（英文）暂扣查验。 |
| `Return - Delivered` | 包裹已退回并送达发件人。 |

以下三类状态最易引起误解：

- **仅显示 `Shipment Created`：** 只表示商家已预约建单；若多日无后续，说明商家可能尚未交寄。
- **`Handed over to …`：** 往往是 Whistl 页面显示的最后一条记录。针对部分追踪（part-tracked）货件，Whistl 说明“交接后无法继续追踪”，请改用末端承运商单号跟进。
- **异常与备注行：** `Delayed`、`Bad Address`、`Refused`、`Lost`、`Item damaged` 等表示运输出现异常；`Shipment Deleted` 表示发件人已取消该货件；而 `Item Weight Changed` 或 `Label reprinted` 仅属备注记录，不代表包裹发生移动。

## Whistl 派送与派送未成功

实际执行上门派送的是末端承运商而非 Whistl，因此派送与改派均适用末端承运商规则。派送未成功时，该承运商会留下通知卡或发送电子通知，说明由哪家派送及下次尝试时间。

Whistl 的 Parcelhub 承运商指南（2025 年 8 月版）按承运商列出如下：

| 承运商 | 派送尝试与后续处理 |
| --- | --- |
| Royal Mail | 尝试 1 次并留下“something for you”通知卡；前往投递局或邮局领取，逾期 18 天退回。自 2023 年 5 月起，无法投入信箱或需签收而你不在家时，会尝试放安全处或交邻居代收。 |
| Evri | 最多尝试 3 次；可在 Evri 应用设置偏好；邮件或短信告知 2 小时派送窗口。 |
| DPD | 留卡并通过短信/邮件供选择邻居、安全位置、改期或自提点；1 小时派送窗口。 |
| Yodel | 最多尝试 3 次；离岛/偏远地址可能仅尝试 1 次，之后需前往邮局自提。 |
| [Amazon Shipping](carrier:amazon-shipping) | 连续 3 天最多尝试 3 次。 |
| DHL Parcel UK | 最多尝试 2 次。 |
| [Parcelforce](carrier:parcelforce) | 尝试 1 次，支持安全放置与改寄选项。 |
| UPS | 尝试 1 次，随后发送电子通知告知二次派送方式。 |

发往海外的部分追踪包裹会移交国际合作方，适用当地邮政规则。Whistl 本身没有自有自提点：`Delivered col. point` 指的是末端承运商的自提门店或快递柜。拒收包裹会直接退回发件人，无法重新派送。

显示已签收但没找到？Whistl 指南建议先检查通知卡、安全放置处并询问邻居，再参阅[显示已签收但未收到包裹](guide:delivered-not-received)（英文）。

## Whistl 包裹未送达、延误或丢失怎么办

Whistl 联系页面指引公众“联系向你售出商品的零售商”。运输合同由商家签订，须由商家向 Whistl 发起“Where Is My Item”查单并提交索赔。

Whistl 指南还列明：

- 无法投递的英国境内包裹会在进入 Whistl 网络后的 **5 个工作日**内退回发件人。
- 部分追踪服务不对延误提供赔偿，且包裹移交末端承运商后若未送达，Whistl 无法介入处理。
- 发件人的索赔时限因末端承运商而异（例如 Evri 为发出后 28 天内；Royal Mail 需至少等待 10 个自然日且须在 80 天内提交）。请尽早联系商家。

如追踪长期停滞，请先参阅[为何物流停止更新](guide:tracking-not-updating)（英文）。

## Whistl 客服电话与联系方式

Whistl 联系页面公布的信息如下：

- **包裹具体问题：** 请联系购买商品的商家。
- **在线追踪：** 通过联系页面上的“Whistl Tracking Portal”按钮查询。
- **一般咨询电话：** `01628 702 965`（英国时间 8:30–17:30）。
- **客户服务邮箱：** `packagescustomerservices@whistl.co.uk`（Whistl 注明该邮箱无法协助公众处理包裹追踪查询）。
- **总部地址：** Network House, Third Avenue, Marlow, Buckinghamshire, SL7 1EY。

请优先联系商家；包裹移交后，可联系追踪页面列出的末端承运商或当地邮政。

## 使用 Peek 追踪 Whistl 包裹

对于 `L` 后接 17 位数字的参考号（如虚构的 `L48291736482917364`），Peek 会直接询问 Whistl，并在确认后显示“Whistl 有这个包裹的记录。”其他 Whistl 参考号缺乏固定格式，请直接粘贴 Whistl 追踪链接（web.parcelhub.net 或 despatch.whistl.co.uk 的 `/Tracking/reference/…`，或 trackmyitem.whistl.co.uk 的 `/tracking/…`），或添加参考号后点击“更换”并选择“Whistl”，无需输入邮编。若参考号本身符合其他承运商格式（如 Royal Mail 的 `…GB` 单号），则会按该承运商追踪而非 Whistl。

[Peek](/) 直接读取 Whistl 的 Parcelhub 追踪器。其时间不带时区：若 Whistl 标明地点则按当地时钟解析，否则按英国时间解析，并列出 Whistl 自己的记录。Peek 会展示末端派送承运商及其单号，并在状态变更时推送通知。没有预计送达日期、重量或自提点信息。状态保留 Whistl 英文原文（纯 `Delivered`、`In transit` 与 `Returned to sender` 除外，会按 Peek 阶段文案展示）。

当 Whistl 指明末端承运商为 Royal Mail、Parcelforce Worldwide、DPD UK、DHL Parcel UK、Amazon Shipping、[InPost](carrier:inpost)（含 Yodel，Peek 归入 InPost 追踪）、[UPS](carrier:ups)、[FedEx](carrier:fedex) 或 Evri 时，Peek 可以在该承运商处继续跟进。仅标注为“DPD”的货件不会被跟进，请添加该承运商自己的单号；对应多件包裹的总参考号无法追踪，请逐件添加单个包裹单号。

## 关于 Whistl 物流查询的问题

### Whistl 是 Royal Mail（英国皇家邮政）的一部分吗？

不是。Whistl UK Ltd 是一家独立公司（前身为 TNT Post UK），只是将许多货件交给 Royal Mail 负责末端派送，因此追踪中常出现 `Handed over to Royal Mail`。

### 实际由谁派送 Whistl 包裹？

通常由其他承运商派送：英国境内为 DPD、Evri、Yodel、Royal Mail、Amazon Shipping、DHL Parcel UK、Parcelforce 或 UPS，海外则交由当地合作邮政或快递。

### Whistl 追踪网站正规吗？

是的。Whistl 自己的追踪运行在 web.parcelhub.net、despatch.whistl.co.uk 与 trackmyitem.whistl.co.uk，Parcelhub 隶属于 Whistl 集团。

### 为什么我的 Whistl 单号查不到？

查无记录时追踪页会提示“Unable to find shipment”。请向商家核对参考号，或直接改用末端承运商在短信或邮件中发给你的自有单号查询。

:::sources
- [Whistl: Contact Us](https://www.whistl.co.uk/contact) – 公众指引联系零售商、咨询电话与工作时间、客服邮箱限制、追踪门户与总部地址
- [Whistl: 首页](https://www.whistl.co.uk/) – Whistl UK Ltd、商业服务及 Parcelhub 隶属 Whistl 集团说明
- [Whistl: Domestic Fully Tracked Customer Guide (October 2025)](https://www.whistl.co.uk/1817/6062/1711/Whistl_Domestic_Fully_Tracked_Customer_Guide_-_V2.2_October_2025.pdf) – UPI 与托运单号、标准化事件、承运商短信与邮件、错过派送通知、拒收、丢失、5 个工作日退件及发件人查单理赔
- [Whistl: Part Tracked Services Customer Guide v1.7](https://www.whistl.co.uk/download_file/force/3171/250) – 交接 Royal Mail 与海外伙伴、18 天保管、延误无赔偿、追踪链接与品牌邮件
- [Parcelhub: Domestic Carrier Guide (August 2025)](https://www.whistlparcels.co.uk/hubfs/Parcels/Documents/Parcelhub_Carrier_Guide_Domestic_-_V1.1_Aug_2025.pdf) – 合作承运商清单、各承运商派送尝试与选项、发件人索赔期限
- [Parcelhub: Part Tracked Services Customer Guide V1.0](https://www.whistlparcels.co.uk/hubfs/Parcels/Documents/Parcelhub_Part_Tracked_Customer_Guide_-_V1_September_2024.pdf) – 追踪门户、按 UPI 或托运单号检索及交接后无法协助说明
- [Parcelhub: Tracking](https://web.parcelhub.net/Tracking) – 查询表单字段、结果布局与仅英文显示
- [Whistl Despatch: Tracking](https://despatch.whistl.co.uk/tracking) – Whistl 品牌下的同一追踪门户
- [Wikipedia: Whistl](https://en.wikipedia.org/wiki/Whistl) – 由 TNT Post UK 更名及 Royal Mail 末端派送合作
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Peek 读取字段、状态记录与单号识别规则
:::
