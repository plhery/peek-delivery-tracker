---
title: DHL eCommerce Iberia 物流查询：西班牙与葡萄牙状态和自提点
description: 查询西班牙与葡萄牙 DHL eCommerce 包裹：单号格式、状态含义、ServicePoint 自提点、CTT Express 及售后。
slug: dhl-ecommerce-iberia-tracking
published: 2026-11-26
updated: 2026-11-26
---

DHL eCommerce Iberia（原名 DHL Parcel Iberia，旧名称仍偶有出现）是 DHL 在西班牙和葡萄牙的包裹配送网络。直接将运单号粘贴到上方输入框即可跟踪；在包裹页面点击“打开 DHL eCommerce Iberia 官网”可直达 DHL 网站同一单号的查询页。运单号由发货网店提供。

## DHL eCommerce 运单号：格式与查找位置

没有单号？请向网店索取，或参阅[如何查找运单号](guide:find-tracking-number)（英文）。

以下是 Peek 目录识别的格式（示例均为虚构）：

| 格式 | 示例 | 可能所属的快递 |
| --- | --- | --- |
| 10 位数字 | `4829173649` | DHL eCommerce Iberia，但也可能属于 [DHL Express](carrier:dhl-express)、Mondial Relay、Relais Colis 和 GLS Spain |
| 两位字母 + 7 位数字 + `0` | `KL48291730` | DHL eCommerce Iberia |
| `JJD000061`、`JJD000268`、`JJD000269` 或 `JJD000368` + 14 位数字 | `JJD00006148291736482917` | DHL eCommerce Iberia |

如果网店邮件对 10 位单号写明了 [Mondial Relay](carrier:mondial-relay) 或 [GLS Spain](carrier:gls-es)，请前往对应承运商查询。两位字母 + 九位数字 + `ES` 属于西班牙邮政 [Correos](carrier:correos-spain)。

## DHL eCommerce 物流状态含义

Peek 会向 DHL 请求英文状态文案，不过少数扫描仍以西班牙语返回，例如 `Llegada a`、`Tránsito en`、`Salida de` 和 `Entregado`。常见英文状态按包裹收到它们的顺序如下：

| DHL 显示 | 对你的含义 |
| --- | --- |
| `Shipment data received` | 寄件人已登记包裹电子信息，DHL 尚未收到实物。 |
| `Picked up` | DHL 已从寄件人处揽收，后附城镇名。 |
| `Shipment received at DHL Depot` | 包裹已抵达 DHL 分拨站。 |
| `Arrived at` | 正在各分拨站之间中转，后附城镇名（`In transit in` 与 `Departed from` 同理）。 |
| `Out for delivery` | 已装车今日派送。 |
| `The delivery could not be made due to consignee absent` | 派送时无人应门，后续处理见下文。 |
| `Delivered to DHL ServicePoint, ready for collection by the recipient` | 已送达 DHL ServicePoint 自提点，等待取件。 |
| `Parcel picked up by recipient` | 收件人已从 ServicePoint 取走包裹。 |
| `Delivered` | 已送达收件地址。 |
| `Shipment being returned to sender` | 正在退回寄件人。 |

以下几条需特别留意：

- `Delivered, POD pending`：已标记签收但签收凭证尚未回传；该状态有时会提前出现，后续仍可能有派送或派送未成功记录。
- `Shipment with incidence If you are the sender, please contact DHL Parcel, if you are the recipient please contact the sender`：包裹出现异常；正如提示所写，收件人请直接联系网店卖家。
- `The expected delivery date might be postponed one working day` 和 `Not delivered today: delivery expected for the next working day` 仅属短期顺延，并非包裹遗失。

## 派送未成功与 ServicePoint 自提点

**家中无人。** 派送未成功会显示 `The delivery could not be made due to consignee absent`，场所关闭则显示 `Receiver closed: delivery not possible`。随后若出现 `New delivery date requested` 或 `Delivery agreed with recipient`，说明已申请或约妥新日期。若看到 `Incorrect address details: delivery not possible`，表示地址信息有误导致无法投递：Peek 会显示“需要留意”，需由你或寄件人补正地址后包裹才会继续推进。

**ServicePoint 自提点。** `Parcel will be delivered to a DHL ServicePoint` 与 `Parcel is on it's way to our DHL ServicePoint` 表示包裹正转往自提点。备妥待取后，Peek 会展示“取件点”卡片，包含门店名称、地址、导航及复制地址按钮；营业时间与保管期限未随接口提供，请直接向 DHL 查询。

## 包裹延误、遗失或破损

**物流无更新。** 详见[物流停止更新的原因](guide:tracking-not-updating)（英文）。

**遗失或破损。** 请先联系网店：DHL 自身的 `Shipment with incidence` 状态也指引收件人联系寄件人。在欧盟，Your Europe 说明收货前的货损由卖家负责；未约定送达日期时卖家须在 30 天内送达，逾期后可催告并给予合理宽限期，届满仍未送达即可取消订单并要求退款。详见[包裹遗失处理](guide:lost-parcel)（英文）和[包裹破损处理](guide:damaged-parcel)（英文）。

**海关清关。** 欧盟境外包裹请参阅[海关滞留包裹指南](guide:customs)（英文）。

**CTT Express 合作。** DHL eCommerce 与 CTT 于 2026 年 5 月 12 日签署合资协议：在葡萄牙，DHL eCommerce 业务转入 CTT Expresso；在西班牙，面向个人消费者的配送由 CTT Express 承接，DHL 保留企业件配送。部分包裹带有 CTT Express 单号，可在 [CTT Express](carrier:ctt-express) 追踪。

> 收到关于 DHL 包裹的可疑短信或邮件？请参阅[如何识别快递诈骗信息](guide:delivery-scam-texts)（英文）。

## 联系 DHL eCommerce

请前往包裹派送国（西班牙或葡萄牙）的 DHL 网站查询当地 DHL eCommerce 联系方式。如遇包裹遗失或破损，请先联系购买商品的网店。

## 使用 Peek 跟踪 DHL eCommerce Iberia 包裹

对于“两位字母 + 7 位数字 + `0`”的单号，[Peek](/) 会同时询问 DHL eCommerce Iberia 和采用同一形态的 [TNT Italy](carrier:tnt-it)。对于 10 位数字，Peek 会询问 DHL eCommerce Iberia、Packeta 和 BOX NOW，并在葡萄牙或无地区信息且其余均未查到时询问 DHL Freight；若通过 DHL Express 校验位，则会先询问 DHL Express（以及同样通过校验的 ACS Courier 与 Geniki Taxydromiki），且若 DHL Express 查有记录则以 DHL Express 为准。如果不同品牌的多家承运商均有记录或暂均无记录，可在“更换快递公司”中手动挑选。上表所列前缀的 `JJD` 条码会直接识别为 DHL eCommerce Iberia。DHL 的其他编号（12 或 22 位数字、其他 `JJD` 条码、邮政单号）只有在那里手动选择“DHL eCommerce Iberia”后才能查询，且无需邮编。

你也可以直接粘贴西班牙语、葡萄牙语或英语的 DHL eCommerce Iberia 追踪链接（含 `/LiveTracking/ModificarEnvio/`、`ModificarMeuEnvio` 或 `ModifyMyShipment`），无论单号形态如何均直接归属 DHL eCommerce Iberia（链接末尾的邮编会被忽略，因为查询无需邮编）。

DHL 扫描时间不含时区：Peek 按扫描地时钟解析并展示 DHL 自身轨迹。当 DHL 返回的结果中带有 CTT Express 单号时，Peek 也会询问 [CTT Express](carrier:ctt-express)，并在 CTT Express 出现不早于 DHL 最新扫描的记录后转由对方继续跟进。除 `Delivered` 和 `Out for delivery` 改用 Peek 自身文案外，其余状态保留 DHL 英文原文。Peek 可以在状态变化时通知你，但不显示 DHL eCommerce Iberia 的预计送达日或送达时刻。

## 关于 DHL eCommerce 物流查询的问题

### DHL eCommerce 和 DHL Express 是一样的吗？

不一样。DHL Express 运单使用独立的 MyDHL+ 查询页面，Peek 也将两者作为不同快递处理。10 位数字可能属于其中任一方：校验位通过时 Peek 会优先询问 DHL Express，若对方有记录则归属 DHL Express。

### 为什么我的 DHL eCommerce 包裹查不到物流？

请先向网店确认单号与承运商，因为 10 位数字被多家快递共用（见上表）。此外，历史较久的包裹也可能从 DHL 查询系统下线，具体保留时长未公开。

### CTT Express 会派送 DHL eCommerce 包裹吗？

会派送其中一部分。根据 DHL eCommerce 与 CTT 于 2026 年 5 月签署的合资协议，西班牙的个人消费者配送由 CTT Express 承接，葡萄牙的 DHL eCommerce 业务并入 CTT Expresso。若物流中提供了 CTT Express 单号，请在 [CTT Express](carrier:ctt-express) 追踪。

:::sources
- [CTT: CTT e DHL Ecommerce selam parceria estratégica](https://www.ctt.pt/grupo-ctt/media/noticias/ctt-e-dhl-ecommerce-selam-parceria-estrategica-para-criar-rede-de-distribuicao-de-encomendas-mais-completa-da-peninsula-iberica) – 2026 年 5 月 12 日签署合资协议、DHL eCommerce 葡萄牙业务转入 CTT Expresso、西班牙个人消费者配送由 CTT Express 承接、DHL 保留企业件配送
- [Your Europe: Shipping and delivery](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_en.htm) – 送达前由网店承担损坏责任、30 天、额外期限、退款
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Peek 读取的内容、状态文案及对应阶段、ServicePoint 门店、货件链接、原名 DHL Parcel Iberia、CTT Express 单号
:::
