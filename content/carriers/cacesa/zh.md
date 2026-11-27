---
title: CACESA 物流与清关查询：状态含义、关税缴纳与派送商
description: 查询经由 CACESA 发往西班牙或葡萄牙的包裹：了解追踪单号、英文状态含义、清关与税费缴纳流程、末端派送说明以及官方联系方式。
slug: cacesa-tracking
published: 2026-11-27
updated: 2026-11-27
---

CACESA（Compañía Auxiliar al Cargo Express）是一家位于马德里的报关代理机构，负责处理从中国发往西班牙和葡萄牙的网购包裹：它在多个机场接收包裹、办理清关，然后交由末端派送公司投递。CACESA 本身不负责派送。你可以在 CACESA 追踪页上用商家提供的运单号（shipment number）查询，或直接粘贴到上方输入框中。

## CACESA 追踪单号该用哪一个

CACESA 未公布固定的单号格式。其追踪页要求输入 “Shipment number”（运单号），也就是商家或其中国合作物流随包裹提供的编号，通常位于商家的发货邮件或订单页面中。CACESA 与你下单的购物网站没有直接往来。

CACESA 追踪页上常见的格式（以下数字均为虚构示例）：

| 格式 | 说明 |
| --- | --- |
| `CNES48291736548` | `CNES` 后接 11 位数字。 |
| `CNESP48291736548291736548` | `CNESP` 后接 20 位数字，发往西班牙的包裹。 |
| `CNPRT48291736548291736548` | `CNPRT` 后接 20 位数字，发往葡萄牙的包裹。 |

Peek 目录没有将上述任何格式直接绑定给 CACESA。`CNESP` 和 `CNPRT` 单号同时也属于菜鸟在西班牙和葡萄牙的派送服务 [Ecoscooting](carrier:ecoscooting)；以 `LP` 开头加 14 位数字则是速卖通物流参考号，可通过 [菜鸟（Cainiao）](carrier:cainiao) 追踪。如果 CACESA 官网查不到你的单号，请尝试商家邮件里标明的承运商。更多格式说明请参阅 [追踪单号格式](guide:tracking-number-formats)（英文）。

你可以在 CACESA 追踪页一次输入多个单号。如果提示未找到，CACESA 表示请先核对单号，刚发出的订单可能尚未同步到其系统，较早期的包裹也会从系统中清除。

如果你收到西班牙税务局（AEAT）关于暂扣包裹的信函，信上的 “HB” 编号仅供 CACESA 核查使用，不能用于物流追踪。

## CACESA 物流状态含义解析

CACESA 西班牙语页面显示西班牙语状态，英文页面显示如下英文措辞。每条记录都带有日期和时间，并归类在 Data registered、Warehouse 或 Custom Clearance 标题下：

| CACESA 状态原文 | 对你的含义 |
| --- | --- |
| `Electronic notification of shipment received` | CACESA 已收到包裹电子数据，包裹实物尚未到达。 |
| `Arrival Notification` | 已预报包裹即将抵达 CACESA。 |
| `Inbound warehouse` | 包裹已进入 CACESA 仓库。 |
| `Start Custom clearance Process` | 已开始办理清关手续。 |
| `Customs cleared-Green channel` | 海关已放行，可以安排后续派送。 |
| `Parcel is going out from Cacesa facilities - More delivery information check with transport company.` | CACESA 已将包裹交由派送公司，后续派送详情请查询派送公司。 |
| `Shipment not found` | CACESA 查不到该单号，或该记录已不再显示。 |

绿色通道（Green channel）代表顺利放行。CACESA 常见问题还提到另外两种通道：橙色通道（海关审核单证）和红色通道（海关查验货物与单证）。若海关需要你补充材料，CACESA 会通过邮件或短信联系你（见下文）。

“going out” 通常是 CACESA 显示的最后一条记录。此后包裹交由末端运输公司处理，CACESA 页面也会提示你向该运输公司查询派送信息。

## CACESA 末端派送、错过派送与退货

CACESA 明确说明：“执行派送的不是 Cacesa，而是你的网店卖家选择的运输公司。”CACESA 不会列出这些派送公司的名称，因此需查看商家的发货邮件或订单页面。

派送日期、自提点、快递柜和周六派送均由末端派送公司决定，CACESA 对此不做说明，相关问题请直接询问派送公司。CACESA 表示，派送公司在完成其规定次数的派送尝试后会将包裹退回卖家，但未公布具体尝试次数。

如果包裹曾被海关扣留，CACESA 会在海关放行后 3 至 4 天内完成交接，再加上送达你所在邮编所需的派送时间。

CACESA 不受理个人买家的退货寄递。如需退货，请与商家或派送公司安排。

## CACESA 清关：缴税、提交材料与常见问题

**收到 CACESA 邮件或短信。** 如果通知称包裹正在等待清关，说明海关需要补充资料，或需要先缴纳税费才能放行。CACESA 要求将以下材料发送至 atc@cacesa.com：

- 发票或购买凭证（例如包含包裹单号、商品明细和价格的订单截图，以及商品图片）；
- 付款凭证（例如银行卡扣款截图）；
- 如为个体经营者，需附上说明。

**在线缴税。** 在 cacesa.com 选择 “Tax payment”，输入运单号和以欧元计价的购买金额，页面会计算出关税、增值税及总额。支持银行卡、PayPal 或 Bizum 付款。CACESA 会代你向税务局缴款，因此支付后不可撤回；如有多缴，由客服退还。逾期未缴税的包裹将退回原发地。

**不想要该包裹？** 将 CACESA 的弃货声明表发送至 atc@cacesa.com，CACESA 会协同税务局安排将包裹退回始发地。

**收到税务局信函。** 若信函写着 “Decomiso - Depósito temporal” 或 “Validación de datos”，请扫描后发送至 atc@cacesa.com，CACESA 会告知该包裹是否仍可取回。

具体应缴税费遵循 [包裹被海关扣留指南](guide:customs)（英文） 中的相关规定。CACESA 常见问题指出，自 2026 年 7 月 1 日起，对 150 欧元及以下的订单按商品类别征收每类 3 欧元的海关费用，同一 6 位 HS 编码下的商品只收一次；如果电商平台在结账时已代收增值税和关税，派送时无需重复缴纳。

**延误、丢失或破损。** 除清关税费支付外，其他所有问题 CACESA 均指引你联系约定了交付条款的卖家。包裹已清关放行却始终未送达？如果包裹从未被扣留且 CACESA 从未联系过你，请直接联系卖家。轨迹长期无变化时的排查建议可参阅 [物流停止更新](guide:tracking-not-updating)（英文）。

> CACESA 提醒：无论是 CACESA 还是任何获授权的派送方，都不会要求你再次支付购买时已经付过的增值税、关税或其他费用。遇到此类要求请勿付款，先向卖家或 CACESA 核实。

## CACESA 联系电话与电子邮箱

以下渠道公布于 cacesa.com。CACESA 没有葡萄牙语官网，在葡萄牙也无办公室，葡萄牙收件人使用相同的联系方式。

- **电子邮箱：** atc@cacesa.com，用于处理暂扣包裹、提交资料、税费缴纳及弃货申请。
- **电话：** +34 91 746 1860（总部，Calle Golf 23, Madrid）或 +34 91 333 54 50（马德里巴拉哈斯航空货运办公室）。CACESA 未公布服务时间，也未设专门的收件人热线。
- **投诉：** 对 CACESA 自身服务的投诉，请使用 [投诉表单](https://www.cacesa.com/en/legal-complaints)，填写运单参考号和投诉事由。
- **查询包裹位置与送达时间：** 联系卖家或末端派送公司。

## 使用 Peek 追踪 CACESA 包裹

由于没有专属于 CACESA 的单号格式，[Peek](/) 不会仅凭单号自动识别为 CACESA。请添加单号后点击“更改”选择 CACESA，或直接粘贴 cacesa.com 追踪链接（带有 `busqueda=` 参数的 `/en/tracking` 或 `/es/seguimiento` 链接），此类链接无论单号格式如何都会直接识别为 CACESA。而 `CNESP` 或 `CNPRT` 单号会立即被识别为 Ecoscooting。

Peek 会按原文展示 CACESA 的英文状态，轨迹时间按马德里时间读取并换算为你设备的本地时区。送达记录会归为“已送达”，`Start Custom clearance Process` 显示为“清关中”，绿色通道放行后显示为继续运输；不过 Peek 不显示具体税费金额，也不能代缴税款。由于 CACESA 不提供地点信息，地图上不会显示定位点，也没有预计送达日、重量、寄件人或收件人信息。

CACESA 不披露末端派送公司，且包裹会沿用同一单号继续运输。只有在单号明确属于另一家承运商时 Peek 才能自动接力追踪：例如 `CNESP` 和 `CNPRT` 转由 Ecoscooting 追踪；邮政单号会匹配 Peek 判断的所在国家邮政（依据手机地区或网页端连接地），例如西班牙的 [Correos](carrier:correos-spain) 或葡萄牙的 [CTT](carrier:ctt)；以及 [CTT Express](carrier:ctt-express) 的自有单号。`CNES` 和 `LP` 单号不会自动接力。当你打开包裹页面，或在开启通知的情况下进入派送日时，Peek 最高每 10 分钟检查一次（其余时间每小时一次），并可在状态变化时通知你。连续两天无新动态时（处于“已创建运单”或“承运商已揽收”时为 12 小时），Peek 改为每小时检查一次。

## CACESA 追踪常见问题

### 什么是 CACESA Postal？

CACESA（又称 Cacesa Postal 或 CACESA Logistics）是总部位于马德里的报关代理，负责将从中国寄往西班牙和葡萄牙的网购订单办理清关。它由伊比利亚航空（Iberia）于 1987 年创立，并于 2025 年被葡萄牙邮政 CTT 收购。

### 谁负责派送 CACESA 包裹？

由卖家指定的运输公司派送，而不是 CACESA。派送公司名称见商家的邮件或订单页，CACESA 不会列出。

### CACESA 处理速卖通（AliExpress）的清关吗？

CACESA 官网不点名具体购物平台。它说明自己是从中国集运公司接收包裹并为买家清关，与买家下单的网站没有直接联系。

### CACESA 清关完成后多久能收到包裹？

对于曾被扣留的包裹，CACESA 会在海关放行后 3 至 4 天内移交派送公司，之后再计入派送至你所在邮编所需的时间。对于未被扣留的包裹，CACESA 未给出具体时效。

:::sources
- [CACESA: Shipment tracking](https://www.cacesa.com/en/tracking) – 追踪页、运单号、派送公司说明、Tax payment
- [CACESA: Seguimiento de envíos](https://www.cacesa.com/es/seguimiento) – 相同内容的西班牙语页面
- [CACESA: Faqs](https://www.cacesa.com/en/faqs) – 角色定位、不负责派送、三色通道、材料要求、3 至 4 天交接、派送尝试、退货、AEAT 信函、弃货、atc@cacesa.com
- [CACESA: Offices](https://www.cacesa.com/en/offices) – 联系电话、葡萄牙无办公室
- [CACESA: Legal Warning](https://www.cacesa.com/en/legal-warning) – 缴税不可撤回、未缴退回、其他索赔找卖家
- [CACESA: Complaints](https://www.cacesa.com/en/legal-complaints) – 投诉表单
- [CACESA: Quiénes somos](https://www.cacesa.com/es/quienes-somos) – 公司全称、1987 年、Iberia
- [CACESA: site texts](https://www.cacesa.com/main.40b62b43302d800a.js) – 追踪页标签、未找到文案、缴税步骤、欧盟规则 FAQ、防诈骗提示、中国发往西班牙和葡萄牙
- [ECO: CTT results to September 2025](https://eco.sapo.pt/2025/10/30/lucro-dos-ctt-sobe-184-para-328-milhoes-ate-setembro-com-integracao-da-espanhola-cacesa/) – CTT 完成收购
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Peek 读取的信息
:::
