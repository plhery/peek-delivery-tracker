---
title: GOFO Spain 快递追踪：GFES 与 CIES 单号、三次派送与客服
description: 追踪西班牙 GOFO Spain 包裹：GFES 与 CIES 单号格式、西班牙语状态代码含义、三次派送与两周仓库寄存、签收照片及客服联系方式。
slug: gofo-spain-tracking
published: 2026-11-26
updated: 2026-11-26
---

GOFO Spain 是 GOFO 在西班牙的分支机构。GOFO 是一家创立于 2023 年 8 月的美国末端物流服务商，与美国、加拿大、法国、荷兰、意大利及西班牙的派送服务商合作。在西班牙，GOFO 为电商平台、零售商及物流企业提供派送服务；三次派送失败后，包裹将退回分拨中心并安全保管两周。您可以在 [GOFO Spain 追踪页面](https://www.gofo.com/es/tracking-results/) 查询货件，或将追踪单号粘贴到上方输入框中。

## GOFO Spain 追踪单号：GFES 与 CIES 格式

请在网上商家的发货通知邮件或电商平台订单详情页中查找单号。GOFO Spain 单号以 `GFES` 或 `CIES` 开头，后接 13 或 14 位数字：

| 格式（虚构示例） | 说明 |
| --- | --- |
| `GFES26482917364052` | 标准 18 位 GOFO Spain 包裹单号（`GFES` + 14 位含校验码数字；也存在 17 位 `GFES` + 13 位数字）。 |
| `CIES4829173640582` | 另一种 17 或 18 位 GOFO Spain 包裹单号（`CIES` + 13 或 14 位数字）。 |

如需了解其他欧洲快递单号格式，请参阅[单号格式指南](guide:tracking-number-formats)（英文）。

## GOFO Spain 追踪状态含义

GOFO Spain 追踪数据以数字状态代码配合西班牙语说明呈现，时间采用西班牙当地时间（`Europe/Madrid`）：

| GOFO Spain 显示状态（代码） | 对您的实际含义 | Peek 显示阶段 |
| --- | --- | --- |
| `Pedido recibido`（`100`） | 卖家已在线录入订单，GOFO 正等待接收实物包裹。 | “已创建面单” |
| `Llegada al centro de clasificación`（`202`）、`Salida del centro de clasificación`（`200`）、`Llegada a las instalaciones de destino de GOFO`（`201`）、`En preparación para reparto`（`203`） | 已扫描进入 GOFO 分拣中心、发往目的地站点并准备本地派送。 | “运输中” |
| `El paquete está en reparto`（`208`） | 已交由 GOFO 派送员在西班牙安排上门派送。 | “派送中” |
| `Entrega fallida`（`206`） | 派送尝试失败（例如收件人不在家或地址问题）。 | “派送未成功” |
| `Devuelto al centro de clasificación`（`204`） | 派送失败后已退回 GOFO 分拣/分拨中心——**尚未**退回给发件人。 | “需要关注” |
| `Devuelto`（`257` / `264`） | 包裹正已退回寄件人。 | “已退回寄件人” |
| `Entregado por En persona` / `Entregado por En el buzón`（`205`） | 已当面签收或投入您的信箱。 | “已送达” |

**关于 `Devuelto al centro de clasificación`（`204`）的说明。** 看到 *"Devuelto"* 可能令人担心，但代码 `204` 表示派送员在投递未果后将包裹带回 GOFO 当地分拣中心，而真正已退回寄件人会显示为 `Devuelto`（代码 `257`/`264`）。

## GOFO Spain 派送尝试、两周寄存与海关

根据 [gofo.com/es/en/](https://www.gofo.com/es/en/) 与 [GOFO Spain 客服常见问题](https://www.gofo.com/es/en/contact-us/)：

- **三次派送与两周寄存：** GOFO Spain 明确说明，**三次派送失败**后包裹将退回分拨中心并**安全保管两周**。如需协调重新派送或核实地址问题，请务必在两周期满前联系客服。
- **签收证明（POD）照片：** 对于已送达的包裹，[GOFO Spain 主门户](https://www.gofo.com/es/)提供 POD 签收详情，通常包含包裹放置位置照片及派送说明。
- **非欧盟电商订单与海关：** 许多 GOFO Spain 包裹来自欧盟以外的网店。根据[欧盟委员会欧盟海关改革](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en)规定，来自欧盟以外、价值不超过 150 欧元的订单每件商品需缴纳 3 欧元关税，且每件此类订单均适用每件 2 欧元的欧盟处理费（除非商家在结账时已代收）。详见[包裹滞留海关指南](guide:customs)（英文）。

## GOFO Spain 包裹丢失、损坏或显示已签收

[GOFO Spain 客服常见问题](https://www.gofo.com/es/en/contact-us/)按不同情况列出了具体处理步骤：

- **显示 `Entregado`（“已送达”）但未收到：** 请先在 [gofo.com/es/](https://www.gofo.com/es/) 查看签收证明（POD）照片。检查信箱、小区公共包裹存放区、家门口及附近安全放置处，并询问邻居、家人或物业管理人员。如仍找不到，请尽快发送邮件至 `cs@es.gofo.com`——附上您住所入口或外观照片有助于加快核查（不附照片 GOFO 也会受理）。
- **外包装完好但内件缺失，或确认包裹丢失：** GOFO 要求收件人**直接联系发件人**处理少件或丢件理赔。
- 通用排查指南请参阅[为何物流停止更新](guide:tracking-not-updating)（英文）、[显示已签收但未收到包裹](guide:delivered-not-received)（英文）、[包裹损坏处理指南](guide:damaged-parcel)（英文）、[包裹丢失处理指南](guide:lost-parcel)（英文）与[包裹已退回寄件人](guide:returned-to-sender)（英文）。

## GOFO Spain 客服与联系方式

以下官方联系方式发布于 [GOFO Spain 客服支持](https://www.gofo.com/es/en/contact-us/)及 [Contacto](https://www.gofo.com/es/contact-us/)：

- **客服电子邮箱：** `cs@es.gofo.com`。
- **收件人客服电话：** 页面目前标注为*即将上线*（*Coming soon* / *Próximamente*），规划服务时间为周一至周五 `09:00 – 18:00`（公共节假日除外）。开通前，电子邮箱是 GOFO Spain 面向收件人公布的官方联系渠道。

> 请勿轻信来历不明的短信或 WhatsApp 消息，切勿点击其中要求在线刷卡支付“重新派送费”或“确认地址”的链接。请直接在 [gofo.com/es/tracking-results/](https://www.gofo.com/es/tracking-results/) 核验您的 `GFES` 或 `CIES` 单号，或致信 `cs@es.gofo.com`。另请参阅[快递诈骗短信防范指南](guide:delivery-scam-texts)（英文）。

## 使用 Peek 追踪 GOFO Spain 包裹

[Peek](/) 可高置信度自动识别 `GFES` + 13 或 14 位数字（如通过 GOFO Europe 校验位的虚构单号 `GFES26482917364052`）及 `CIES` + 13 或 14 位数字（`CIES4829173640582`）为 GOFO Spain，也能识别粘贴的包含 `?id=` 的 `gofo.com/es/...` 追踪链接。如需手动选择 GOFO Spain，可在添加包裹时点击“更改”，或在包裹页面点击“更改承运商”（搜索“GOFO ES”同样可以找到）。

Peek 直接读取 GOFO Spain 官方追踪数据，显示包裹阶段（“已创建面单”、“运输中”、“派送中”、“派送未成功”、“需要关注”、“已退回寄件人”、“已送达”）、西班牙语扫描描述与地点、签收时间，以及 GOFO 提供时的预计送达日期。数字操作代码用于判定所处阶段，Peek 不会直接展示这些代码或服务名称。扫描时间按西班牙当地所在地点时区解析，并转换为您设备的本地时钟显示。Peek 不会显示派送详情、签收证明照片（请前往 [gofo.com/es/](https://www.gofo.com/es/) 查看）、个人参考信息、收件人核验信息、未标明单位的重量或门户公告。

当您在 Peek 中打开包裹页面，或在派送当日开启通知时，Peek 最多每 10 分钟检查一次；在 GOFO Spain 显示 `El paquete está en reparto` 当天，若 GOFO Spain 官方追踪正常响应，Peek 会从最新扫描所在地的早上 6 点起每 2 分钟检查一次。其余情况下每小时检查一次。若包裹停留在“已创建面单”或“承运商已揽收”超过 12 小时，或连续两天无新动态，无论您的关注方式如何，均改为每小时检查一次；若停留在“已创建面单”超过七天，则每 6 小时检查一次。当 GOFO Spain 官方追踪无响应、Peek 仅通过追踪服务跟进时，默认每小时检查一次，静默一天后每 3 小时一次，派送中当天最多每 15 分钟一次。

当最新扫描距今已满四天且包裹仍停留在“已创建面单”、“承运商已揽收”或“运输中”时，Peek 会按该次扫描日期加以标记，例如“自 10 月 12 日（周一）起无物流更新”。包裹页面随即会提示“包裹在长途航班、清关期间或周末经常不会更新。Peek 会继续向 GOFO Spain 查询。”并提供指向 GOFO Spain 联系页面的“从 GOFO Spain 获取帮助”链接。如果超过三周仍无新扫描，页面会显示“超过三周没有新动态。如果包裹仍未送达，请联系卖家。Peek 会继续向 GOFO Spain 查询。”

您可选择“每次扫描”、“重要节点”或“仅送达通知”提醒方案。Peek 仅负责监控物流状态更新，无法在应用内查看 POD 照片、重新预约派送或代您向 GOFO Spain 提交理赔。

## 关于 GOFO Spain 追踪的常见问题

### GOFO Spain 会尝试派送几次？

GOFO Spain 会进行**三次派送尝试**。三次派送均失败后，包裹将退回分拨中心并安全保管**两周**。

### `Devuelto al centro de clasificación` 是什么意思？

代码 `204`（`Devuelto al centro de clasificación`）表示派送员在投递未成功后将包裹带回 GOFO 分拣中心——包裹**尚未**退回给原卖家（已退回寄件人会显示为代码 `257`/`264` 的 `Devuelto`）。

### 在哪里可以查看 GOFO Spain 的签收证明（POD）照片？

在 [gofo.com/es/](https://www.gofo.com/es/) 或 [gofo.com/es/tracking-results/](https://www.gofo.com/es/tracking-results/) 输入您的 `GFES` 或 `CIES` 单号，即可直接查看 POD 照片及放置位置说明。

### 如何联系 GOFO Spain 客服？

请发送邮件至 `cs@es.gofo.com`；收件人电话热线目前仍标注为即将上线。若确认包裹丢失或外包装完好但内件缺失，请直接联系线上卖家处理。

:::sources
- [GOFO Spain：英文首页](https://www.gofo.com/es/en/) – 美国创立的末端物流服务商、三次派送失败后退回分拨中心及两周寄存政策
- [GOFO Spain：西班牙语首页](https://www.gofo.com/es/) – 西班牙语追踪门户与签收证明（POD）查询入口
- [GOFO Spain：关于我们](https://www.gofo.com/es/en/who-we-are) – 成立于 2023 年 8 月
- [GOFO Spain：寄件合作](https://www.gofo.com/es/ship-with-us) – 与美国、加拿大、法国、荷兰、意大利及西班牙派送商合作
- [GOFO Spain：追踪结果页](https://www.gofo.com/es/tracking-results/) – 多包裹追踪查询门户
- [GOFO Spain：客服支持（英文）](https://www.gofo.com/es/en/contact-us/) – `cs@es.gofo.com`、收件人热线（即将上线，周一至周五 `09:00–18:00`）、POD/丢件排查步骤及发件人理赔规则
- [GOFO Spain：Contacto（西班牙文）](https://www.gofo.com/es/contact-us/) – 西班牙语联系页面（Peek 帮助按钮链接地址）
- [欧盟委员会：欧盟海关改革](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 自 2026 年 7 月 1 日起 150 欧元以下货件每件征收 3 欧元关税，自 2026 年 11 月 1 日起征收 2 欧元欧盟处理费
- [Universal Parcel Scraper: gofo-es](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/gofo-es) – 含 `gofo-europe` 校验位的 `GFES`/`CIES` 识别、`gofo.com/es` 链接规则、数字操作代码（`100`、`200`–`208`、`257`/`264`）、扫描时区、过滤的 POD/详情字段及帮助链接
:::
