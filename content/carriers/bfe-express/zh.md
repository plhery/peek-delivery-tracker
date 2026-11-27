---
title: BFE Express 国际快递查询：CK 与 WYB 单号、状态与客服
description: 查询 BFE Express 跨境包裹（发往阿根廷、秘鲁、乌拉圭和美国）：CK 与 WYB 单号规则、清关与西语末端状态、派送延迟及各区域支持邮箱。
slug: bfe-express-kuajing-chaxun
published: 2026-11-24
updated: 2026-11-24
---

BFE Express（*BFEexpress*，由成立于 2005 年的 Hongkong Bfe Info Tech Co Limited 运营）是一家跨境电商物流与末端派送网络，连接国际卖家（包括 Temu 阿根廷卖家）与阿根廷、秘鲁、乌拉圭和美国的收件人。公司提供国际货运、清关、海外本地仓储及高密度最后一公里派送服务，并可通过 [bfeexpress.com/tracking](https://www.bfeexpress.com/tracking) 查询物流轨迹。

由于 BFE Express 负责目的国到港与清关段，随后将包裹移交当地末端网点，同一条追踪时间线上通常会同时出现英文机场/清关扫描与西班牙语本地派送扫描。

## BFE Express 单号格式规则

Peek 支持长度在 6 至 50 位之间、由字母和数字组成的 BFE Express 运单号。BFE Express 官方联系表单按目的国区分以下常见格式（以下均为虚构示例）：

| 单号（虚构） | 目的国与说明 |
| --- | --- |
| `CK54829173604518` | `CK54` 加 12 位数字（共 16 位）——发往阿根廷；Peek 中无其他承运商使用该形态。 |
| `CK548291736045182` | `CK54` 加 13 位数字（共 17 位）——另一种阿根廷单号格式（长度与 Colis Privé 重叠）。 |
| `CK512829173604518` | `CK512` 加 12 位数字（共 17 位）——发往秘鲁（长度与 Colis Privé 重叠）。 |
| `CK598829173604518` | `CK598` 加 12 位数字（共 17 位）——发往乌拉圭（长度与 Colis Privé 重叠）。 |
| `WYB482917360` | `WYB` 加 9 位数字（共 12 位）——秘鲁运单格式（形态与 Purolator 和 [TNT Australia](carrier:tnt-au) 重叠）。 |

`CK` 后面的数字对应目的国：`54` 代表阿根廷、`512` 代表秘鲁、`598` 代表乌拉圭。如果你直接粘贴形如 `bfeexpress.com/track?track_number=…` 的链接，Peek 会立即提取单号并选中 BFE Express。如需了解其他跨境单号规则，请参阅[运单号格式说明](guide:tracking-number-formats)（英文）。

## BFE Express 物流状态含义解析

BFE Express 追踪系统把轨迹分成两组——国际干线段与目的国末端段——Peek 会同时读取这两组记录：

| BFE Express 扫描文案 | 实际含义 |
| --- | --- |
| `Arrived at Airport of destination` | 载有包裹的国际航班已降落目的国机场。 |
| `Transport to next facility` | 正在机场货站与当地清关或分拨仓库之间转运。 |
| `Customs declaration completed` | 目的国清关申报已完成，即将移交末端派送。 |
| `Turned over to last mile facility` | 已从 BFE 清关段移交至当地末端派送站点。 |
| `Se ha recibido el paquete.` / `Tu paquete fue recibido por en: ...` | 已到达阿根廷或乌拉圭当地末端网点并签收。 |
| `En embarque` / `En transporte` / `Tu paquete está en viaje a ...` | 已装车并在目的国国内站点之间运往你的城市。 |
| `Tu paquete se encuentra en ...` / `En espera para salir a reparto en ...` | 已抵达当地派送网点，等待分派给派送员上路。 |
| `Reparto en curso` | 当地派送员今天正在上门投递途中。 |
| `Entregado` | 已投递给收件人；BFE 整体状态栏同时会显示 `Delivered`。 |

BFE Express 的英文扫描把地点写在逗号前（如 `<城市>, Customs declaration completed`）；阿根廷的西语扫描把国家放在前面，而乌拉圭的末端扫描在 `en` 或 `a` 之后标注地点。只要 BFE 自身的包裹总状态显示为 `Delivered`，即使最新一条明细文案并非签收，Peek 也会将其标记为“已签收”。

## 清关、末端交接与延误说明

BFE Express 官网提供小包运输、国际货运、清关、本地仓储及末端派送，并积极面向 Temu 阿根廷卖家招商。以下几点有助于理解其轨迹节奏：

- **抵达目的国机场之前：** 如果 BFE Express 官网暂时查不到任何记录，请先查看购物平台的卖家发货页，并参阅[为什么物流停止更新](guide:tracking-not-updating)（英文）。
- **清关与末端交接：** `Customs declaration completed` 与 `Turned over to last mile facility` 常在同一地点间隔约一小时发布，当地网点入库扫描（`Se ha recibido el paquete.` 或 `Tu paquete fue recibido por en: ...`）则往往在次日出现。若包裹在目的国海关停滞，请参阅[包裹被海关扣留怎么办](guide:customs)（英文）。
- **预计派送日期：** 包裹在途期间，BFE Express 有时会给出预计派送日，Peek 会同步显示；一旦包裹签收、退回、待取件或出现晚于该日期的新扫描，Peek 会自动撤下该预计日期。
- **显示已签收未收到或破损：** BFE Express 的[客户支持页面](https://www.bfeexpress.com/contactUS)在常见问题中列出了这两种情况，并提醒联系区域支持团队前先准备好完整运单号。另请参阅[显示已签收但未收到](guide:delivered-not-received)（英文）。

如需对比由阿根廷本土快递直接承运的国内包裹，可参考 [OCA](carrier:oca) 与 [Andreani](carrier:andreani)。

## 使用 Peek 追踪 BFE Express 包裹

你可以将 `CK…` 或 `WYB…` 单号粘贴到 [Peek](/) 中，Peek 跟踪 4,500 多家快递公司。仅凭单号形态，Peek 无法确定它属于 BFE Express，因此添加时 Peek 会询问 BFE Express（“正在查询物流服务…”），BFE Express 查到这个单号时显示“BFE Express 有这个包裹的记录。”对于 16 位 `CK54` 单号，Peek 只询问 BFE Express。对于 17 位 `CK` 单号，Peek 还会询问 [Colis Privé](carrier:colis-prive)：在法国先问 Colis Privé，在其他地区要等 BFE Express 和物流查询服务都查不到这个单号后才问。对于 `WYB` 单号，Peek 会询问 BFE Express、[TNT Australia](carrier:tnt-au) 和 Purolator：在阿根廷、秘鲁、乌拉圭和美国先问 BFE Express，在加拿大先问 Purolator，其他地区先问 TNT Australia。若想从一开始就在 BFE Express 跟踪，添加时点击“更换”，在“符合这个单号”下选择 BFE Express，或在已添加的包裹页面点击“更换快递公司”。如果粘贴 `bfeexpress.com/track?track_number=…` 链接，Peek 会立即选定 BFE Express。

Peek 通过开源的 [universal-parcel-scraper BFE Express 适配器](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/bfe-express)直接请求 BFE Express 追踪接口，按时间顺序合并国际与末端两组记录，提取城市、网点或国家作为地点，并保留预计派送日与目的地国家。自带 GMT 偏移的时间按原始时刻保留；不带偏移的时间按扫描地点时区解析（埃塞萨按布宜诺斯艾利斯时间、利马按利马时间、蒙得维的亚按蒙得维的亚时间），无地点的扫描沿用相邻记录时区，其余默认布宜诺斯艾利斯时间，最后统一换算为你设备的本地时间。BFE 门户上显示的街道详细地址与签收照片不会被 Peek 保留。

Peek 会把 BFE Express 的一般扫描显示为**运输中**，其中 `Reparto en curso` 映射为**派送中**，`Entregado` 映射为**已签收**；当 BFE 整体状态显示 `Delivered` 时也会标记为**已签收**。

在打开包裹页面或预计派送当天开启提醒时，只要 BFE Express 追踪接口正常响应，Peek 最高每 10 分钟检查一次；在 BFE Express 扫出 `Reparto en curso` 的当天，自最新扫描所在地早上 6 点起最高每 2 分钟检查一次；其余时间每小时检查一次。若停留在“已生成面单”或“已揽收”满 12 小时，或连续两天无新动态，无论怎么关注都会转为每小时检查一次；停留在“已生成面单”满七天后降为每 6 小时一次。当 BFE Express 无响应、Peek 仅通过第三方追踪服务跟进时，Peek 每小时检查一次，静默满一天后改为每 3 小时一次，在派送当天打开页面或开启提醒时最高每 15 分钟检查一次。

当最新扫描满四天且包裹仍停留在“已生成面单”、“已揽收”或“运输中”时，Peek 会标出该扫描日期（如“自10月12日周一起没有物流更新”），并在页面提示“包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 BFE Express 查询。”，同时提供跳转至 BFE Express 联系页面的“向 BFE Express 寻求帮助”链接。若超过三周没有新扫描，则显示“已超过三周没有新动态。如果还没收到，请联系卖家。Peek 会持续向 BFE Express 查询。”

你可以在 Peek 中选择**每次扫描**、**重要环节**或**仅派送当天**通知预设，按需接收物流提醒。

## 联系 BFE Express 客服

BFE Express 在其[联系我们页面](https://www.bfeexpress.com/contactUS)公布了各区域客服邮箱，服务时间均为**周一至周五 09:00–18:00**（公众假期除外）：

- **阿根廷：** `info@ar.bfeexpress.com`
- **秘鲁：** `info@pe.bfeexpress.com`
- **乌拉圭：** `info@uy.bfeexpress.com`
- **美国：** `info@us.bfeexpress.com`
- **巴拉圭：** `info_py@bfeexpress.com`
- **其他支持地区（哥伦比亚、厄瓜多尔、塞尔维亚、多米尼克）：** `info@other.bfeexpress.com`
- **官网与查询入口：** [bfeexpress.com](https://www.bfeexpress.com/) 与 [bfeexpress.com/tracking](https://www.bfeexpress.com/tracking)

发邮件联系对应国家客服时，请务必附上完整的 `CK…` 或 `WYB…` 运单号。

## 关于 BFE Express 追踪的常见问题

### 为什么我的 BFE Express 轨迹直接从目的国机场开始、前面没有记录？

BFE Express 官网轨迹有时会从航班抵达目的国机场的 `Arrived at Airport of destination` 开始显示。更早之前的中国端发货进度，请在购物平台的卖家订单页查看。

### 为什么 BFE Express 的扫描同时包含英文和西班牙语？

BFE Express 用英文记录国际到港、清关申报与末端交接节点，而负责最终投递的拉美本地快递网点会以西班牙语发布国内扫描（直至 `Reparto en curso` 派送中和 `Entregado` 已签收）。

### `CK` 单号里的 `54`、`512` 和 `598` 分别代表什么？

它们代表目的地国家代码：`CK54…` 对应阿根廷（16 或 17 位），`CK512…` 对应秘鲁（17 位），`CK598…` 对应乌拉圭（17 位）。发往秘鲁的包裹还可能使用 12 位的 `WYB` 加 9 位数字格式。

### BFE Express 查不到我的单号怎么办？

请核对 `CK` 或 `WYB` 单号是否漏掉字母或数字，并向卖家确认包裹是否已经正式移交给 BFE Express。

:::sources
- [BFE Express 官网](https://www.bfeexpress.com/)
- [BFE Express 运单查询](https://www.bfeexpress.com/tracking)
- [BFE Express 客户支持](https://www.bfeexpress.com/contactUS)
- [universal-parcel-scraper BFE Express 适配器](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/bfe-express)
:::
