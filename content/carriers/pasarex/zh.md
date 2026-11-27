---
title: PasarEX 跨境快递追踪：AMZPSR 亚马逊单号、清关与客服
description: 追踪发往哥伦比亚、智利、巴拿马及巴巴多斯的 PasarEX 包裹：AMZPSR 单号格式、清关状态、PQR 理赔规则与各国客服电话。
slug: pasarex-tracking
published: 2026-11-26
updated: 2026-11-26
---

PasarEX（成立于 1993 年的哥伦比亚公司 *PASAR EXPRESS SAS* 旗下商业品牌）为电商平台提供国际快递、空运货物及末端派送服务。其网站覆盖**哥伦比亚**、**智利**、**巴拿马**和**巴巴多斯**，业务包括迈阿密物流中心及源自中国的多式联运。

PasarEX 同时作为报关代理为包裹办理清关，因此其追踪节点从预报清单一路涵盖至进口拼箱到达、清关处理、仓库入库以及本地派送。

## PasarEX 追踪单号格式

Peek 接受长度为 6 至 30 位、包含字母和数字（至少含 1 位数字）的 PasarEX 单号。其中有一种格式为 PasarEX 独有：

| 格式（虚构） | 说明 |
| --- | --- |
| `AMZPSR482917360` | `AMZPSR` + 9 位数字（共 15 位）——可在 PasarEX 追踪器查询的亚马逊单号，Peek 中没有其他承运商使用该格式。 |

如果你的订单确认信中包含直接追踪链接（`tracking.pasarex.com/?awb=...`），将该链接粘贴到 Peek 后会提取运单号并立即归为 PasarEX。如需了解其他电商单号格式，请参阅[单号格式指南](guide:tracking-number-formats)（英文）与[亚马逊订单追踪指南](guide:amazon-orders)（英文）。

## PasarEX 追踪状态含义

PasarEX 在其追踪页面中将各条扫描按大类分组（`COMMENTS`、`IN CUSTOMS PROCESS`、`RECEIVED FOR DELIVERY`、`IN TRANSIT`、`IN WAREHOUSE`、`OUT FOR DISTRIBUTION`、`DELIVERED`、`SHIPPING WITH DELIVERY ISSUE`），每条具体扫描附带西班牙语或英语说明、城市名称及日期时间。

| PasarEX 扫描原文 | 含义 |
| --- | --- |
| `Orden de pedido recibida - paquete no arribado` / `Information (Manifest) of the shipment received.` | 电子预报清单已传输至 PasarEX，但实物包裹尚未抵达进口枢纽。 |
| `Carga consolidada para importación recibida` / `Reception of consolidated cargo in import.` | 空运进口拼箱货已抵达 PasarEX 口岸站点（如波哥大），准备进入清关环节。 |
| `Entry to station for customs processing.` | 包裹已进入海关站点开始办理进口清关。 |
| `Cleared by customs by PasarEx.` / `Cleared by customs and transferred to OPS.` | PasarEX 已完成清关并将包裹转交国内营运部门。 |
| `Ingresó a bodega` / `Package received in warehouse.` / `Reception of consolidated cargo at a station.` | 已扫描入库 PasarEX 仓库或国内中转/目的地站点。 |
| `Package in transit` / `Package in ground transit` | 正在 PasarEX 各站点之间进行陆运或国内干线运输。 |
| `Applies to cargo that may experience delivery delays due to disturbances or protests` | 服务提示，告知当地骚乱或抗议可能导致派送延误，之后货件将继续流转。 |
| `Shipment left to local route for final delivery` | 今日已装车出发执行本地最终派送。 |
| `Shipment delivered in perfect condition` | 包裹已完好送达收件地址。 |

## 海关清关、PQR 投诉理赔与时效说明

由于 PasarEX 直接以报关代理身份办理跨境进口清关后再转入国内派送：

- **包裹抵达口岸前：** 当追踪仅显示 `Information (Manifest) of the shipment received.` 或 `Orden de pedido recibida - paquete no arribado` 时，表示订单已建立但实物尚未到达 PasarEX。在出现 `Reception of consolidated cargo in import.` 或 `Carga consolidada para importación recibida` 之前，通常会出现一段静默期。详见[包裹滞留海关指南](guide:customs)（英文）和[为何物流停止更新](guide:tracking-not-updating)（英文）。
- **哥伦比亚 PQR 与赔偿规定（CRC 2016 年第 5050 号决议）：** Pasar Express SAS 在 [PasarEX 哥伦比亚支持页面](https://pasarex.com/co/soporte/)公布了依据第 1369/2009 号法律及第 5050/2016 号决议适用的法定索赔时限。收件人必须在签收后 **5 个工作日**内提交内件短少或损坏索赔；发件人针对遗失、短少或损坏的索赔期限为 **10 个自然日**（国内）或 **6 个月**（国际）（企业合约客户可适用专项条款）。提交索赔需附空运单（*Guía Aérea*）、商业发票、Pasar Express 发票、物品清单、包装描述及照片。PasarEX 通常在 **15 个工作日**内答复 PQR（如需深入调查可延期），用户在收到回复通知后有 **10 个工作日**提起复议与上诉（*recurso de reposición y en subsidio de apelación*）。

## 使用 Peek 追踪 PasarEX

将你的单号或 `tracking.pasarex.com/?awb=...` 链接粘贴到 [Peek](/)（Peek 跟踪 4,500 多家快递公司）。粘贴链接会直接交给 PasarEX 查询。对于单纯的单号，Peek 从不向 PasarEX 查询：它可能会询问同格式的其他承运商，再查询追踪服务（显示“正在查询物流服务…”），只有当某个追踪服务指明 PasarEX、没有指明其他承运商，并附有带日期的步骤时，才会把包裹归为 PasarEX。因此请点击“更换”并选择 PasarEX（`AMZPSR` 单号可在“符合这个单号”下找到），或在已添加的包裹页面使用“更换快递公司”。

由于 PasarEX 的追踪页面每次查询都需要勾选 reCAPTCHA 验证，Peek 通过开源的 [universal-parcel-scraper PasarEX 适配器](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/pasarex)配合浏览器辅助组件进行读取。Peek 会提取每条扫描的原文、城市与时间，并按时间倒序排列（而非按 PasarEX 的分组标题）。PasarEX 返回的当地时间不带偏移量：Peek 按所在城市时区解析（迈阿密用纽约时间，波哥大、麦德林和卡利用波哥大时间，圣地亚哥、巴拿马和巴巴多斯按当地时区），无城市名的扫描沿用相邻记录时区，其余默认波哥大时间，并全部以你设备的本地时钟展示。PasarEX 追踪器不提供预计送达日期、重量或签收证明。

Peek 将 PasarEX 的扫描说明映射为清晰阶段：**已生成面单**（实物到达前的清单接收）、**运输中**（从进口拼箱到达、清关放行、入库到陆运中转；PasarEX 从清单直接跳至进口拼箱到达，不单独显示**已揽收**）、**清关中**（正在海关站点清关）、**派送中**（出发执行本地最终派送）以及**已签收**（最终签收）。

由于 PasarEX 规定两次查询之间至少间隔一小时（查询失败后亦同），无论你是否打开包裹页面，Peek 最多每小时检查一次 PasarEX 的追踪页面，派送当天也不会每 2 分钟检查一次。停留在“已生成面单”满七天后改为每 6 小时检查一次。当 PasarEX 的追踪页面无响应、Peek 仅通过追踪服务跟进时，每小时检查一次，静默一天后每 3 小时一次。

当最新扫描距今已满四天，且包裹仍处于“已生成面单”、“已揽收”或“运输中”时，Peek 会用该次扫描的日期加以标记，例如“自10月12日周一起没有物流更新”。包裹页面随后会显示“包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 PasarEX 查询。”如果三周没有新扫描，页面会显示“已超过三周没有新动态。如果还没收到，请联系卖家。Peek 会持续向 PasarEX 查询。”

你可以在 Peek 的提醒设置中选择**每次扫描**、**重要环节**或**仅派送当天**，决定哪些更新会通知你。

## 联系 PasarEX

PasarEX 为各运营国家公布了专属电话与邮箱：

- **哥伦比亚（[pasarex.com/co/soporte](https://pasarex.com/co/soporte/)）：** `+57 601 384 7000`、`+57 601 390 6000`（兼波哥大 PQR 专线）、`+57 601 291 6500`、`+57 300 919 7521`、`+57 300 919 7532`；电子邮箱 `sac@pasar.net`（客服）及 `pqr@pasar.net`（PQR/理赔）
- **智利（[pasarex.com/cl](https://pasarex.com/cl/)）：** `+56 2 2571 7500`；电子邮箱 `clgestionpe@pasar.net`
- **巴拿马（[pasarex.com/pa](https://pasarex.com/pa/)）：** `+507 836 0399`；电子邮箱 `pagestionpe@pasar.net` 与 `pqr@pasar.net`
- **巴巴多斯：** `+1 246 4370277`、`+1 305 7042435`
- **公司简介：** [PasarEX 哥伦比亚首页](https://pasarex.com/co/) 与 [关于我们（Nosotros）](https://pasarex.com/co/nosotros/)

## 常见问题

### `AMZPSR` 追踪单号是什么？

`AMZPSR` 后接 9 位数字（共 15 位，如 `AMZPSR482917360`）是可在 PasarEX 追踪器查询的亚马逊单号。Peek 中没有其他承运商采用此格式，但 Peek 不会自行向 PasarEX 查询：请在“符合这个单号”下手动选择 PasarEX。

### 为什么我的 PasarEX 包裹显示 `Orden de pedido recibida - paquete no arribado`？

这表示 PasarEX 已收到货件的电子预报清单，但实物包裹尚未运抵 PasarEX 的目的地进口站点。待拼箱航班抵达后，扫描动态便会恢复更新。

### 为什么 Peek 刷新 PasarEX 的频率比部分其他承运商低？

PasarEX 限制每小时最多查询其追踪系统一次（失败后亦然），因此 Peek 按每小时一次的频率检查；此外其追踪页面每次查询均要求勾选 reCAPTCHA 验证。

### 在哥伦比亚向 PasarEX 提交货损理赔的期限是多久？

根据 [pasarex.com/co/soporte](https://pasarex.com/co/soporte/) 公布的哥伦比亚 CRC 第 5050/2016 号决议，收件人须在签收后 **5 个工作日**内提交损坏或内件短少索赔，发件人则拥有 10 个自然日（国内）或 6 个月（国际）的遗失或损坏索赔期。

:::sources
- [PasarEX 哥伦比亚](https://pasarex.com/co/)
- [PasarEX 哥伦比亚 — 客服支持与 PQR](https://pasarex.com/co/soporte/)
- [PasarEX 哥伦比亚 — 关于我们](https://pasarex.com/co/nosotros/)
- [PasarEX 智利](https://pasarex.com/cl/)
- [PasarEX 巴拿马](https://pasarex.com/pa/)
- [universal-parcel-scraper PasarEX 适配器](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/pasarex)
:::
