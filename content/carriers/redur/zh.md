---
title: Redur 快递追踪：14 位单号、西班牙与葡萄牙物流状态
description: 追踪西班牙、葡萄牙及欧洲 Eurodis 线路的 Redur 包裹：14 位纯数字单号、分拨站与派送状态对照、网点自提预约及马德里总部客服联系方式。
slug: redur-tracking
published: 2026-11-26
updated: 2026-11-26
---

**Redur**（*Redur S.A.*）是一家拥有逾 50 年历史的西班牙速运与物流运营商，总部位于马德里阿尔赫特（Algete）（[redur.es](https://redur.es/)）。公司在西班牙本土、西班牙离岛及葡萄牙全境运营 100% 直营网络，拥有超过 61 座分拨网点（*delegaciones*）和 3,000 名员工，同时也是欧洲包裹与托盘联合货运网络 **Eurodis** 的创始成员。除伊比利亚国内加急 B2B/B2C 寄递与每日发往欧洲各国的国际陆运班车外，Redur 还提供符合 GDP 认证的医药温控配送（*Farma 15-25*）与合同物流服务。你可以在 [redur.es/track-trace/](https://redur.es/track-trace/) 查询货件，或将 14 位 Redur 运单号或追踪链接粘贴到上方输入框。

## Redur 运单号（14 位纯数字格式）

Redur 运单号（*N° Tracking*）为 14 位纯数字，不含字母或空格：

| 格式（虚构） | 含义 |
| --- | --- |
| `58492716380419` | 标准 14 位 Redur 包裹追踪单号（*NTRACKING*）。 |
| `61738492051628` | 西班牙、葡萄牙或 Eurodis 线路的另一组 14 位 Redur 货件追踪号。 |

在 [redur.es/track-trace/](https://redur.es/track-trace/) 表单上还可凭发货编号以及寄件人或合作伙伴参考号搜索；Peek 的自动追踪则使用 14 位追踪单号（`NTRACKING`）或完整的 `redur.es/track-trace/?...&valor=` 链接。Peek 不会单凭纯数字识别 Redur：裸 14 位数字会交由 DPD、[SEUR](carrier:seur) 及其他快递查询（西班牙境内优先询问 SEUR），因此请直接粘贴 Redur 链接或选择 Redur。另见[运单号格式说明](guide:tracking-number-formats)（英文）。

## Redur 追踪状态含义

Redur 追踪页展示 7 步进度条以及分拨站级别的扫描流水（`redur.es/track-trace/` 显示西班牙语，英文页显示英语）：

| Redur 原文（西语 / 英语） | 对你的含义 | Peek 显示 |
| --- | --- | --- |
| `DOCUMENTACION` / `DATA ENTERING` | 寄件人已发送电子运单数据，包裹尚未入库始发网点（仅带日期）。 | “已生成面单” |
| `ENTRADA PLAZA ORIGEN` / `INBOUND ORIGIN DEPOT` | 包裹首次在 Redur 始发分拨站完成实物入库扫描。 | “已揽收” |
| `SALIDA PLAZA ORIGEN`（`OUTBOUND ORIGIN DEPOT`）、`ENTRADA/SALIDA PLAZA TRANSITO`（`INBOUND/OUTBOUND TRANSIT DEPOT`）、`ENTRADA PLAZA DESTINO`（`INBOUND FINAL DEPOT`） | 正在西班牙或葡萄牙的始发站、中转站与目的分拨站之间流转。 | “运输中” |
| `CARGA CAMION T. INTERNACIONAL` / `TRUCK LOAD INTERNATIONAL TRAFFIC` | 已装车发往另一国家，末端将由当地合作伙伴派送。 | “运输中” |
| `PENDIENTE DE FECHA CONCERTADA`、`FECHA DE ENTREGA CONCERTADA` / `BOOKING AGREED DATE` | 正在等待与收件人约定派送日期，或已完成派送日期预约。 | “运输中” |
| `SALIDA A REPARTO` / `OUT FOR DELIVERY` | 已装上当地 Redur 派送车辆，今日派送。 | “派送中” |
| `RECOGERÁN EN AGENCIA` | 包裹暂存于目的地 Redur 分拨网点，等待收件人自提。 | “待取件” |
| `DESTINATARIO AUSENTE`、`INCORRECT ADDRESS`、`CERRADO PERMANENTE/TEMPORAL` | 因无人在场、地址有误或收件单位关门导致派送尝试未成功。 | “派送未成功” |
| `DEVOLUCIÓN A ORIGEN` | 因派送失败或拒收正在退回寄件人。 | “已退回寄件人” |
| `ENTREGA` / `DELIVERY` | 已送达收件人（或由 Redur 进度条点亮最终送达步骤予以确认）。 | “已签收” |

## 西班牙、葡萄牙与欧洲 Eurodis 服务

根据 [Redur](https://redur.es/) 与 [Redur 服务页面](https://redur.es/servicios/)：

- **伊比利亚国内服务（*Servicio Doméstico*）：** 依托 Redur 61 座以上直营网点覆盖西班牙本土、离岛与葡萄牙的加急包裹、托盘与组合货运派送，配备 *Redur Connected* 邮件/短信派送通知及 *Maia* 货件管理工具。
- **欧洲国际服务（*Servicio Internacional*）：** 通过 **Eurodis** 合作网络每日发往欧洲各国的 B2B 和 B2C 陆运线路。一旦出现 `CARGA CAMION T. INTERNACIONAL`，目的地的 Eurodis 当地合作快递将负责末端交付。
- **医药温控配送（*Farma 15-25*）：** 面向医疗健康与医药货件的 15–25 °C GDP 合规配送服务。

## 派送未成功、预约暂存与包裹遗失

- **`PENDIENTE DE FECHA CONCERTADA`（等待约定日期）：** Redur 正在等待与你敲定派送日后再安排出车；出现 `FECHA DE ENTREGA CONCERTADA` 或 `BOOKING AGREED DATE` 则代表已约好日期。请留意来电和邮件，或携 14 位单号致电 Redur。
- **`DESTINATARIO AUSENTE`、`INCORRECT ADDRESS` 或 `RECOGERÁN EN AGENCIA`：** 请尽快携 14 位单号联系卖家或 Redur 客服核对地址、安排再次派送或确认网点自提营业时间。
- **延误、破损或遗失：** 请先通知网购商家或寄件人（由签约方发起正式理赔），同时可向 Redur 核实包裹所在网点。另见[显示已签收但未收到](guide:delivered-not-received)（英文）、[包裹破损](guide:damaged-parcel)（英文）、[包裹遗失](guide:lost-parcel)（英文）、[包裹退回寄件人](guide:returned-to-sender)（英文）和[为什么物流停止更新](guide:tracking-not-updating)（英文）。

## Redur 客服与马德里总部联系方式

[Redur Contacto](https://redur.es/contacto/) 和 [Redur 服务页面](https://redur.es/servicios/) 公布的官方联系方式：

- **西班牙总部总机：** `(+34) 916 20 46 10`（传真 `91 628 15 60`）。
- **电子邮箱：** `info@redur.es`。
- **联系表单与网点查询：** [redur.es/contacto/](https://redur.es/contacto/)。
- **总部地址（*Servicios Centrales Redur*）：** *Redur S.A.*, Carretera Algete–Madrid Km 2.5 (P.I. Redur), 28110 Algete, Madrid, Spain。

> 请警惕要求在线支付小额费用以重新安排西班牙或葡萄牙快递派送的虚假短信。任何派送通知请务必先在 [redur.es/track-trace/](https://redur.es/track-trace/) 或向卖家核实。详见[警惕快递诈骗短信](guide:delivery-scam-texts)（英文）。

## 使用 Peek 追踪 Redur 包裹

直接将 `redur.es/track-trace/?...&valor=` 追踪链接粘贴到 [Peek](/)，Peek 会自动识别为 Redur。由于纯 14 位数字（例如虚构单号 `58492716380419` 或 `61738492051628`）被众多欧洲快递共用且 Redur 没有专属前缀，输入裸单号时请在添加页面点击“更换”（或在包裹详情页点击“更换快递公司”）并选择 **Redur**（搜索“Redur Spain”、“Redur Portugal”或“Redur S.A.”也可找到）；随后 Peek 会直接向 Redur 的追踪器核验，确认后显示“Redur 有这个包裹的记录。”

Peek 会读取 Redur 追踪接口，并展示阶段（“已生成面单”、“已揽收”、“运输中”、“派送中”、“待取件”、“派送未成功”、“已退回寄件人”、“已签收”）、Redur 状态原文及分拨站名。若 Redur 进度条最后一步已点亮，Peek 会将包裹标记为已送达。Redur 的扫描时间不含时区：Peek 会按各自网点所在时区解析（`ALGETE` 按马德里时间、`LISBOA` 按里斯本时间、`LAS PALMAS` 按加那利群岛时间），无法定位的网点沿用相邻扫描时区，再统一以你的设备本地时区展示；`DOCUMENTACION` 仅保留日期。Peek 不保留包裹件数；Redur 追踪页本身不返回预计送达日、重量、服务名称、独立签收时间或合作伙伴单号。

当你在 Peek 中打开包裹页面，或在派送当日开启通知时，Peek 最多每 10 分钟检查一次；在 Redur 显示 `SALIDA A REPARTO` 或 `OUT FOR DELIVERY` 当天，只要 Redur 自己的追踪系统有响应，会从最新扫描所在分拨站的当地时间早上 6 点起每 2 分钟检查一次。其余情况下每小时检查一次。若包裹停留在“已生成面单”或“已揽收”超过 12 小时，或连续两天没有新动态，无论你以何种方式关注，均改为每小时检查一次；若停留在“已生成面单”超过七天，则每 6 小时检查一次。当Redur 追踪系统无响应、Peek 仅通过追踪服务跟进时，每小时检查一次，静默一天后每 3 小时一次，派送当天最多每 15 分钟一次。

当最新扫描距今已满四天，且包裹仍处于“已生成面单”、“已揽收”或“运输中”时，Peek 会用该次扫描的日期加以标记，例如“自10月12日周一起没有物流更新”。包裹页面随后会显示“包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 Redur 查询。”，并附上指向 Redur 联系页面的“向 Redur 寻求帮助”链接。如果三周没有新扫描，页面会显示“已超过三周没有新动态。如果还没收到，请联系卖家。Peek 会持续向 Redur 查询。”

你可以自由选择“每次扫描”、“重要环节”或“仅派送当天”提醒预设。Peek 仅监控物流追踪更新，无法代你预约派送日期、修改地址或代为联系 Redur。

## 关于 Redur 物流查询的问题

### Redur 运单号是什么格式？

Redur 追踪单号是 14 位纯数字，不含字母或空格（例如虚构单号 `58492716380419`）。

### Redur 里的 `PENDIENTE DE FECHA CONCERTADA` 是什么意思？

表示 Redur 正在等待与你协商确定派送日期，确认后才会出车派送。

### `RECOGERÁN EN AGENCIA` 是什么意思？

表示你的包裹目前暂存在当地 Redur 分拨网点（*delegación*），等待你前往自提。

### 如何联系 Redur 客服？

可致电 Redur 西班牙总部总机 `(+34) 916 20 46 10`、发邮件至 `info@redur.es`，或通过 [redur.es/contacto/](https://redur.es/contacto/) 联系表单与网点目录查询。

:::sources
- [Redur: Official Website](https://redur.es/) – 50 余年历史、61+ 座西葡网点、3,000 名员工、Eurodis 创始成员、*Maia* 与 *Redur Connected*
- [Redur: Track & Trace](https://redur.es/track-trace/) – 公共货件追踪页面
- [Redur: Servicios](https://redur.es/servicios/) – *Servicio Doméstico*、*Servicio Internacional*、*Farma 15-25*（GDP）及 *Logística integral*
- [Redur: Contacto](https://redur.es/contacto/) – `(+34) 916 20 46 10`、`info@redur.es` 及马德里阿尔赫特总部（Peek 链接的帮助页）
- [Universal Parcel Scraper: redur](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/redur) – 14 位格式、`redur.es/track-trace/` 链接规则、西语/英语状态映射、进度条送达判定与按各网点时区解析时间
:::
