---
title: Vinted Go 物流查询：单号、状态与自提柜取件
description: 查询 Vinted Go 包裹：运单号位置与格式、英文状态中文说明、自提柜与合作点 7 天保管期，以及包裹异常与取件问题处理方式。
slug: vinted-go-tracking
published: 2026-11-24
updated: 2026-11-24
---

Vinted Go 是 Vinted 自营的包裹派送服务，专门负责运送在 Vinted 上交易的包裹，从自提柜或合作包裹点发往另一处自提柜、合作包裹点或社区取件点，部分地区也提供送货上门。查件时可在 Vinted 中打开订单聊天记录并点击物流追踪按钮，或前往 [Vinted Go 查询页面](https://vintedgo.com/en/tracking)输入单号。

## 在哪里查找 Vinted Go 运单号

在 Vinted 的订单聊天窗口中，你可以直接点击前往快递查询页面的链接，或复制运单号。卖家发货后，物流信息最长可能需要 48 小时才会在 Vinted 中显示。

Vinted Go 未对外公布运单号格式。Peek 快递目录中已知的格式如下：

| 单号（示例） | 说明 |
| --- | --- |
| `1748291736058413` | Vinted Go 单号的格式：以 `17` 或 `18` 开头的 16 位数字。DHL eCommerce、TNT 和 Evri 也使用 16 位数字（其中 Evri 仅在末位校验通过其自身规则时符合）。 |
| `1748291736058414` | 末位校验未通过的 16 位数字，因此不符合 Vinted Go 的格式。 |
| `VGS48291736058419` | `VGS` 加 13 或 14 位数字：Vinted Go 官方查询支持的独有格式。 |
| `KLT4UW8291736058` | 16 位字符：3 位字母、1 位数字、`UW`、1 位数字再加 9 位字母或数字。Vinted Go 查询系统同样支持此类单号。 |

有两项信息容易混淆：

- **查询链接。** 物流按钮跳转的地址形如 `vintedgo.com/en/tracking/1748291736058413`。看到这个链接就能最确切地确认承运商是 Vinted Go。
- **取件码。** 取件码不是运单号：包裹准备就绪后，取件码会通过邮件（也可能通过短信）发送，且始终以大写字母开头。

完全找不到单号？请参阅[如何查找运单号](guide:find-tracking-number)（英文）。如果是由其他快递公司寄出，[Vinted 物流追踪指南](guide:vinted-orders)（英文）涵盖了 Vinted 使用的各家承运商。

## Vinted Go 物流状态中文说明

Vinted Go 的英文查询页会在顶部以标题形式展示最新阶段及其时间，下方是各阶段的时间线；点击“Show details”可展开每一步的详细扫描记录。按包裹经过的先后顺序如下：

| Vinted Go 显示 | 对你的含义 |
| --- | --- |
| `Shipment created` | 卖家已生成面单，Vinted Go 尚未收到包裹。 |
| `Parcel was dropped off at the locker` | 卖家已将包裹投递至自提柜（或显示 `Parcel was dropped off at the parcel shop` 表示已交至合作包裹点）。 |
| `Shipped` | 对应投递后的阶段：包裹现已交给 Vinted Go。 |
| `In transit` | 正在各分拣中心之间转运，发往你的取件点。 |
| `Parcel is on the way to <country>` | 已离开 Vinted Go 网络，转由其他承运网络继续运送（例如意大利境内由 Poste Italiane 负责）。 |
| `Ready to collect at the locker` | 包裹已在自提柜等你领取（或 `Ready to collect at the parcel shop` 表示在合作包裹点）。取件码正在发送中。 |
| `Ready to collect` | 该步骤对应的阶段名称，7 天取件期已开始计算。 |
| `Parcel was collected by the recipient` | 你或持有你取件码的人已取走包裹。 |
| `Delivered` | 已取件并完成派送。 |
| `Missing from locker` | 柜格中未找到包裹。请在 Vinted 上申报异常。 |
| `Returning to sender` | 包裹正退回卖家，例如因超过 7 天无人领取。 |

需要注意，`Shipped` 只说明卖家已交寄包裹；`Delivered` 是在取件完成后才出现，而不是包裹刚放入自提柜时出现：如果你的取件码无法开柜，Vinted Go 常见问题建议先确认物流是否已经显示 `Ready to collect at the locker`。

Vinted Go 页面还预留了 `Out for delivery`、`Couldn’t be delivered` 和 `Parcel lost` 等标签，仅在包裹实际进入对应状态时才会显示。

## Vinted Go 自提柜与合作包裹点取件指南

**投递网点。** Vinted Go 的网点包括自提柜、合作包裹点和社区取件点。其网站首页列出的覆盖国家包括法国、比利时、荷兰、西班牙、葡萄牙和芬兰。仅当买家与卖家附近均有 Vinted Go 自提柜时，Vinted 下单页面才会提供该选项。如果你选定的网点歇业或满仓，Vinted Go 可能会改投至附近的另一处网点，并在可供领取时通知你具体地址。

**到货通知。** 包裹可供领取后，Vinted Go 会发送电子邮件，Vinted 也会更新订单状态，你可能还会收到短信。按其服务条款，包裹到达 3 天后系统会再发送一次提醒。

**如何取件。** 在自提柜前扫描或输入取件码即可打开对应柜门。在合作包裹点或社区取件点，请向店员出示取件码或有效身份证件。营业时间可在 Vinted Go 地图上查看。

**保管期限。** 你有 7 个自然日的时间取件。对于自提柜或包裹中心里的包裹，Vinted Go 条款另有付费的延长取件服务（Pickup Extension）：在取件期的最后 24 小时内，只要 Vinted Go 显示该服务可用，你就可以加购 24 小时，并可多次购买。包裹开始退回后，Vinted Go 无法拦截。逾期未取的包裹会退回卖家的寄件点；随后 Vinted 会取消订单并为你退款。

**送货上门。** 在法国，部分网点支持通过 Vinted 发送的链接预约 Uber 骑手按约定时间送货上门。在提供送货上门服务的地区，Vinted Go 条款规定：预约时段提供一次派送尝试，未预约时段提供两次尝试。

**派送时效。** Vinted Go 服务条款标明，从投递寄出到送达取件点的目标时效为 3 至 6 个工作日。

## Vinted Go 物流不更新、包裹丢失或破损

售后均由 Vinted 处理，而非直接找 Vinted Go：Vinted 客服负责处理包裹丢失与破损，并在必要时要求承运商介入调查。

**多日无更新。** Vinted Go 表示，包裹在转运途中连续几天没有新记录属于正常现象。另见[物流为什么停止更新](guide:tracking-not-updating)（英文）。

**迟迟未到。** Vinted Go 要求先等待 25 个工作日。如果仍未送达，或者订单聊天中提示包裹可能已丢失，请直接在该订单对话中申报问题。

**破损、错货或空柜。** 须在取件后 2 天内在 Vinted 上申报；Vinted 帮助中心注明专业卖家（Pro sellers）订单的申报期为 14 天。如果在自提柜取出时即发现包裹破损，请先取出并立即拍照留证，因为柜门关闭后无法再次打开。

**显示已签收但未收到。** 请在标记为已送达后的 2 天内在 Vinted 上申报。另请参阅[显示已签收但未收到](guide:delivered-not-received)（英文）。

## Vinted Go 客服电话与联系方式

Vinted Go 不向买家提供客服邮箱或联系表单，其电话热线仅用于处理在自提柜现场遇到的问题：

- **订单问题**（延误、丢失、破损、错货）：请在你的 Vinted 账户中进入对应订单联系 Vinted 客服。
- **在法国、比利时、荷兰或西班牙遇到自提柜故障：** 点击自提柜主屏幕上的“?”按钮，即可查看 Vinted Go 支持电话和服务时间。针对荷兰和比利时，Vinted Go 常见问题还列出了电话 +31 20 793 2587，提供英语服务（比利时亦提供法语服务）。
- **在葡萄牙遇到自提柜故障：** 点击柜机屏幕上的“Obter ajuda”并用手机扫描二维码，即可打开 Vinted Go 在线客服，服务时间为当地时间每天 7:00 至 22:00。

Vinted 提醒，网络钓鱼也会通过短信传播；其法国帮助页面请用户将钓鱼邮件转发至 phishing@vinted.com。

## 使用 Peek 查询 Vinted Go 包裹

[Peek](/) 无需登录账号或输入邮编即可读取 Vinted Go 官方物流：展示每条带时间戳的扫描记录及 Vinted Go 原文（例如 `Ready to collect at the locker`）、各分拣中心所在城镇以及 Peek 的简明状态标题（“待取件”、“已签收”）。页面会标明包裹存放的自提柜或合作包裹点名称，以及你取件的时间和地点，但不显示社区取件点。详细地址、营业时间和取件截止日保留在 Vinted Go 网站，且无预计送达日期。正在退回卖家的包裹会显示为“已退回寄件人”。

对于以 `VGS` 开头的单号，Peek 会立即识别为 Vinted Go。如果只输入 16 位纯数字，Peek 会向 Vinted Go 和 DHL eCommerce（部分单号也会向其他快递）查询其是否收录，Vinted Go 查到后即直接标明，只有当这些承运商均未查到时才会继续询问 TNT；对于 `UW` 格式单号则会直接询问 Vinted Go。其他情况下，可点击“更换”并选择“Vinted Go”，或直接粘贴 vintedgo.com 查询链接（粘贴链接可立即识别为 Vinted Go）。

Peek 最高每 10 分钟检查一次更新；如果连续 48 小时没有新扫描（包裹仍处于“已生成面单”或“已揽收”状态时为 12 小时），则转为每小时检查一次，并可在状态变化时通知你。如果 Vinted Go 将包裹转交至另一个承运网络并生成新单号，只要 Peek 直接支持读取该网络，便会自动继续跟进。当 16 位 `UW` 单号的后 13 位符合 [Poste Italiane](carrier:poste-italiane) 包裹单号格式时，Peek 会向 Poste Italiane 查询，并在对方出现最新扫描后衔接跟踪。Peek 无法代你联系 Vinted 或 Vinted Go，也无法代你提交异常申报。

## Vinted Go 物流查询常见问题

### Vinted Go 正规吗？

正规。Vinted Go 是 Vinted 自营的物流服务，由注册于立陶宛维尔纽斯的 Vinted Go, UAB 运营。

### Vinted Go 派送需要多久？

其服务条款目标为从卖家投递到送达取件点 3 至 6 个工作日（不含周末和法定节假日）。Vinted Go 要求在判定包裹丢失前等待满 25 个工作日。

### Vinted Go 有客服电话吗？

仅限在自提柜现场求助使用。荷兰和比利时电话为 +31 20 793 2587；在法国、比利时、荷兰和西班牙点击柜机屏幕上的“?”按钮即可看到热线号码。涉及订单本身的一切问题均需通过 Vinted 官方客服处理。

### 为什么我的 Vinted Go 单号查不到信息？

卖家发货后物流最长需要 48 小时才会显示；如果单号输错，系统会提示“We can't find a parcel with this information”。此外，Vinted Go 在面单生成六个月后会停止提供该包裹的查询数据。

:::sources
- [Vinted Go: Frequently Asked Questions](https://vintedgo.com/en/faq?country=fr&region=europe) – 取件码、7 天保管期、改投附近网点、25 个工作日、2 天申报期、时间线停顿与自提柜电话
- [Vinted Go: Frequently Asked Questions (Netherlands)](https://vintedgo.com/en/faq?country=nl&region=europe) – 荷兰和比利时自提柜客服热线
- [Vinted Go: Terms and Conditions for Delivery Services](https://vintedgo.com/en/terms-and-conditions?country=fr&region=europe) – 运营公司、网点类型、上门派送次数、3 至 6 个工作日、提醒通知、付费 Pickup Extension、自提柜破损与通过 Vinted 索赔
- [Vinted Go: Shipping service, trusted by millions of Vinted users](https://vintedgo.com/en?country=fr&region=europe) – 覆盖国家与 2022 年启动信息
- [Vinted Go: Track your parcel](https://vintedgo.com/en/tracking) – 查询表单、网站语言、状态文案与查无单号提示
- [Vinted: Comment suivre un colis ?](https://www.vinted.fr/help/100) – 订单对话内单号、48 小时、25 个工作日与已签收未收到
- [Vinted: Envoi avec Vinted Go](https://www.vinted.fr/help/1110) – 线路、Uber Direct 上门派送与可用条件
- [Vinted: Récupérer un colis](https://www.vinted.fr/help/1601) – 逾期未取包裹的取消订单与退款规则
- [Vinted: Comment utiliser les consignes Vinted Go ?](https://www.vinted.fr/help/1109) – 自提柜电话支持及 2 天/14 天申报期
- [Vinted: Hulp met een Vinted Go locker](https://www.vinted.nl/help/1109) – 荷兰柜机屏幕“?”求助按钮
- [Vinted: Ayuda en las taquillas de Vinted Go](https://www.vinted.es/help/1109) – 西班牙自提柜电话支持
- [Vinted: Envio Vinted Go](https://www.vinted.pt/help/1110) – 葡萄牙自提柜每日 7:00–22:00 在线客服
- [Vinted: Le colis n'est pas arrivé](https://www.vinted.fr/help/657) – 包裹未到取件点时由 Vinted 联系承运商核查
- [Vinted: Comment repérer et signaler les tentatives d'hameçonnage et d'escroquerie ?](https://www.vinted.fr/help/628) – 短信钓鱼提醒与 phishing@vinted.com 举报邮箱
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Peek 从 Vinted Go 读取的内容与六个月期限
:::
