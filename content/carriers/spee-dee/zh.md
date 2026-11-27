---
title: Spee-Dee Delivery 物流查询：SP 单号、覆盖州与客服
description: 查询美国中西部 Spee-Dee Delivery 包裹：了解 18 与 20 位 SP 条码、复用单号处理、服务州范围及免费客服热线。
slug: spee-dee-tracking
published: 2026-11-27
updated: 2026-11-27
---

Spee-Dee Delivery（*Spee-Dee Delivery Service, Inc.*）是一家第二代家族经营的美国配送公司，由 Don Weeres 于 1978 年创立，最初提供按需急送服务。它自称美国中西部末端派送的领军者，拥有次日达网络，并提供零担货运（LTL）、仓储、门店自寄点以及 EZR Returns 退货服务（[speedeedelivery.com](https://speedeedelivery.com/)）。

你可以在 [Spee-Dee 追踪页面](https://speedeedelivery.com/track-a-shipment/) 查询运单，或直接将 `SP` 条码粘贴到上方输入框。

## Spee-Dee 追踪单号格式

Peek 识别的 Spee-Dee 条码以 `SP` 开头，后接 16 或 18 位数字：

| 格式（虚构示例） | 说明 |
| --- | --- |
| `SP4829173605148291` | `SP` 加 16 位数字（共 18 位）——标准 Spee-Dee 条码；注意 Spee-Dee 会随时间循环复用 16 位序列号。 |
| `SP482917360514829173` | `SP` 加 18 位数字（共 20 位）——较长格式的 Spee-Dee 条码。 |

这两种 `SP` 格式都能高置信度识别为 Spee-Dee，且不会与 [UPS](carrier:ups)、[FedEx](carrier:fedex) 或 [USPS](carrier:usps) 等美国全国性承运商冲突。粘贴 `speedeedelivery.com/...?tracking=...` 链接时 Peek 会直接提取 `SP` 单号。其他美国地区和全国格式见 [追踪单号格式指南](guide:tracking-number-formats)（英文）。

## Spee-Dee 美国中西部服务范围

根据 [Spee-Dee 服务区域图](https://speedeedelivery.com/services/service-area-maps/)，Spee-Dee 在六个中西部州提供**全州**常规陆运派送，并覆盖周边四个州的部分城市与邮编：

- **全州覆盖：** 伊利诺伊州、爱荷华州、明尼苏达州、北达科他州、南达科他州与威斯康星州。
- **部分城市与邮编：** 堪萨斯州（含扩展邮编段 `660` 和 `666`）、密歇根州、密苏里州（含扩展邮编段 `634–635`、`644–646` 和 `650–653`）以及内布拉斯加州。

查询运输时效时，服务区域图页面为每个寄出邮编提供 “Days in Transit” 地图，其 Zip Transit File 则列出 Spee-Dee 服务范围内每个邮编的具体天数。

## Spee-Dee 物流状态含义解析

Spee-Dee 包裹进度表包含三列——`Date / Time`、`Location` 和 `Activity`——按时间倒序排列；签收后顶部摘要栏还会显示 `Status:`、`Delivered on:`、`Signed by:` 与 `Delivered to:`。

| Spee-Dee 节点原文 | 含义说明 |
| --- | --- |
| `ARRIVAL SCAN` | 已扫描进入中西部陆运网络中的 Spee-Dee 转运场站或分拣中心。 |
| `OUT FOR DELIVERY` *（后常附带括号内的路线号）* | 已装上当地 Spee-Dee 派送车，今天正送往住宅或商业地址。 |
| `Delivered` | 已送达；Spee-Dee 官网摘要还会列出送达时间、地址以及（如有）签收人签名。 |
| `No packages were found matching the barcode supplied.` | 该条码尚未扫描入 Spee-Dee 系统，或输入有误。 |

### 循环复用的 16 位条码

Spee-Dee 会将旧的 16 位 `SP` 条码重新用于新包裹。在 Spee-Dee 原始网页上，你当前包裹的扫描下方仍可能残留几个月前已签收包裹的旧记录。在官网查看复用条码时，请看表格顶部的最新日期，读到前一次货运更早的 `Delivered` 记录即可停止。

## 延误、无扫描与理赔

当追踪结果看似异常时，可留意以下几点：

- **暂无扫描：** 如果官网提示 `No packages were found matching the barcode supplied.`，说明 Spee-Dee 还未扫描包裹或条码有误。核对条码后可询问卖家是否已交寄，或参阅 [物流停止更新怎么办](guide:tracking-not-updating)（英文）。
- **频繁刷新触发限流：** Spee-Dee 追踪主机限制每分钟查询次数，短时间连续刷新可能临时返回 HTTP 503。稍等一分钟或交给 Peek 自动轮询即可避免触发限流。
- **未收到货或破损理赔：** Spee-Dee 的 [客户服务表单](https://speedeedelivery.com/customer-service/) 专门设有 **Claims – Non-Delivery** 和 **Claims – Damage** 选项，并要求提交物流查询时附上**完整收货地址**。若显示已送达却未收到，请参阅 [显示已送达却未收到指南](guide:delivered-not-received)（英文）。

## 使用 Peek 追踪 Spee-Dee

将任意 18 位（`SP` 加 16 位数字）或 20 位（`SP` 加 18 位数字）Spee-Dee 条码，或 `speedeedelivery.com` 链接粘贴到 Peek 中，Peek 会立即识别为 Spee-Dee。如需改选承运商，请在添加时点**更改**或在追踪页点**更换承运商**。

Peek 通过开源的 [universal-parcel-scraper Spee-Dee 适配器](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/spee-dee) 直接读取 Spee-Dee 进度页（若主机拒绝直连则经由远程网络读取）。遇到**复用条码**时，Peek 会自动截断，仅保留距前一次送达至少一天之后的新包裹记录；同时剔除网页调试注释与 `OUT FOR DELIVERY` 后的路线号，并记录每次扫描的城市/场站与时间。Spee-Dee 给出无时区偏移的本地时间：Peek 按地点所在时区解析（St. Cloud、Fargo、Milwaukee、Sioux Falls 用芝加哥时间，Detroit 用底特律时间；无地点则沿用相邻扫描，否则按纽约时间），再换算为你设备的本地时钟。Spee-Dee 官网不提供预计送达日或包裹重量。

Peek 将 `ARRIVAL SCAN` 归为**运输中**、`OUT FOR DELIVERY` 归为**派送中**、`Delivered` 归为**已送达**，其余活动则按通用状态规则归类。

当你打开包裹，或在开启通知的情况下进入派送日时，只要 Spee-Dee 官网正常响应，Peek 最高每 10 分钟检查一次；在显示 `OUT FOR DELIVERY` 当天，自最新扫描所在时钟早上 6 点起每 2 分钟检查一次（若上次查询需经浏览器模式则每 5 分钟一次）。其余时间每小时一次；在“已创建运单”或“承运商已揽收”下满 12 小时或连续两天无新动态时改为每小时一次，在“已创建运单”下满七天改为每 6 小时一次。若 Spee-Dee 无响应而改由综合追踪服务跟进，Peek 每小时检查一次，静默满一天后每 3 小时一次，派送当天最高每 15 分钟一次。

若包裹处于“已创建运单”、“承运商已揽收”或“运输中”且最新扫描满四天，Peek 会按该扫描日期标示并提示“包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 Spee-Dee 查询。”，同时附上指向 Spee-Dee 客服页的帮助链接；超过三周无更新时会建议联系卖家并继续查询。

你可在 Peek 通知偏好中选择**每次扫描**、**重要节点**或**仅派送和送达**。

## 联系 Spee-Dee Delivery

Spee-Dee 在其 [客户服务页面](https://speedeedelivery.com/customer-service/) 特别说明，致电客户将直接由人工接听，而不是自动语音菜单：

- **免费客服热线：** `800-862-5578`
- **本地电话：** `320-251-6697`
- **传真：** `320-251-1846`（零担货运传真：`320-240-7995`）
- **在线联系与理赔表单：** [Spee-Dee 客户服务](https://speedeedelivery.com/customer-service/)（物流查询请附上 `SP` 条码和完整收货地址）
- **办公地址：** Spee-Dee Delivery Service Inc., 4101 Clearwater Road, St. Cloud, MN 56301
- **通讯地址：** P.O. Box 1417, St. Cloud, MN 56302
- **公司概况与追踪：** [关于 Spee-Dee](https://speedeedelivery.com/about-us/) 与 [包裹追踪](https://speedeedelivery.com/track-a-shipment/)

## 常见问题

### 为什么我的 Spee-Dee 追踪页显示几个月前已送达？

Spee-Dee 会随时间复用 16 位 `SP` 条码，因此其原始网页可能在你的新包裹扫描下方仍列着旧包裹的签收记录。Peek 会自动识别分界点并隐藏属于前一次派送的历史行。

### Spee-Dee Delivery 覆盖美国哪些州？

Spee-Dee 全州覆盖伊利诺伊州、爱荷华州、明尼苏达州、北达科他州、南达科他州和威斯康星州，并覆盖堪萨斯州、密歇根州、密苏里州和内布拉斯加州的部分城市与邮编。

### Spee-Dee Delivery 追踪页会显示预计送达日期吗？

不会。Spee-Dee 追踪页只显示场站到达扫描、`OUT FOR DELIVERY` 及最终签收详情，不含预计送达日期。你可以在 [Spee-Dee 服务区域图](https://speedeedelivery.com/services/service-area-maps/) 按邮编查询常规陆运天数。

### 联系 Spee-Dee 查询包裹时需要准备什么信息？

请准备好完整的 18 位或 20 位 `SP` 追踪单号以及完整收货地址——Spee-Dee 客服表单明确要求在追踪咨询中填写完整收货地址。

:::sources
- [Spee-Dee Delivery](https://speedeedelivery.com/)
- [Spee-Dee — About Us](https://speedeedelivery.com/about-us/)
- [Spee-Dee — Track a Shipment](https://speedeedelivery.com/track-a-shipment/)
- [Spee-Dee — Service Area Maps](https://speedeedelivery.com/services/service-area-maps/)
- [Spee-Dee — Customer Service](https://speedeedelivery.com/customer-service/)
- [universal-parcel-scraper Spee-Dee adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/spee-dee)
:::
