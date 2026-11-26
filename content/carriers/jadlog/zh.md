---
title: Jadlog 物流查询：14 位单号、Pickup、Predict 与状态
description: 查询巴西 Jadlog 包裹：14 位运单号、葡语与 Geopost 状态含义、Jadlog Pickup 网点、Predict 短信时段与客服说明。
slug: jadlog-wuliu-chaxun
published: 2026-11-26
updated: 2026-11-26
---

Jadlog 是巴西最大的快递包裹与电商物流运营商之一，也是欧洲 Geopost 集团的巴西子公司（与 DPD、Chronopost、SEUR、BRT 和 Speedy 同属该集团）。它在巴西各地运营 500 多家加盟网点，覆盖约 5,000 个市镇，拥有 7,000 多辆运输车和 4,000 多个 Jadlog Pickup 合作门店。你可以在 [Jadlog 首页](https://www.jadlog.com.br/jadlog/home) 的 `RASTREIE SUA ENCOMENDA` 查询框中查件，或直接将 14 位单号粘贴到上方输入框。

## Jadlog 运单号：14 位格式与查找方法

在网店的发货邮件、电商平台订单页或包裹面单上查找运单号（*código de rastreio* 或 *remessa*）。Jadlog 运单号由 14 位连续数字组成：

| 格式（虚构） | 含义 |
| --- | --- |
| `48291736405821` | Jadlog 14 位包裹单号。与欧洲 Geopost/DPD 系承运商共用 14 位数字形态，例如 [DPD Germany](carrier:dpd-de)、[Chronopost](carrier:chronopost)、[SEUR](carrier:seur) 和 [BRT](carrier:brt)。 |

Peek 仅凭 14 位包裹单号即可查询，从不索要你的 CPF。如果单号是以 `8880` 开头的 15 位数字，它属于 [J&T Express Brazil](carrier:j-and-t-br)；以 `BR` 开头的 15 位字符属于 [SPX Express Brazil](carrier:spx-br)；以 `AJ` 开头的 17 位字符属于 [Anjun Logistics](carrier:anjun)；以 `BR` 结尾的 13 位字符属于 [Correios](carrier:correios-br)。其他格式参见[运单号格式](guide:tracking-number-formats)（英文）。

## Jadlog 物流状态含义

由于 Jadlog 隶属 Geopost，物流记录在 Jadlog 官网显示为葡萄牙语，经由 Geopost 的查件系统读取时则显示为英语。

| Jadlog / Geopost 显示 | 对你的含义 | Peek 显示 |
| --- | --- | --- |
| `Order created`、`COLETA SOLICITADA` | 卖家已在线建单并预约揽收；Jadlog 尚未扫描实物包裹。 | “已生成面单” |
| `EMISSAO` | Jadlog 已签发运单并将包裹收入网络。 | “已揽收” |
| `Em transferência` | 包裹正在巴西各地的 Jadlog 加盟网点、分拣中心或干线节点之间转运。 | “运输中” |
| `Em rota de entrega` | 当地派送员已装车，正在前往你的地址派送。 | “派送中” |
| `ENTREGUE` | Jadlog 已将包裹登记为送达。 | “已签收” |

当商家批量打印面单而 Jadlog 揽收车尚未上门取件时，`Order created` 或 `COLETA SOLICITADA` 可能会停留一两个工作日。进入 `Em transferência` 后，巴西跨州长途陆运也可能在目的网点扫描前出现多天没有更新的情况。

## Jadlog 服务：Predict、Pickup 网点与国际出口

Jadlog 在 [jadlog.com.br/jadlog/servicos](https://www.jadlog.com.br/jadlog/servicos)、[Quem Somos](https://www.jadlog.com.br/jadlog/quemsomos) 及各专项产品页列出了主要服务：

- **Jadlog.com 与 Jadlog Package：** 电商包裹派送（Jadlog.com 承运最高 30 公斤的包裹；Jadlog Package 覆盖 B2C 和 B2B 陆运货物）。
- **Jadlog Rodo、RodoEconômico、Cargo 与 DOC：** 最高 50 公斤的较重陆运货物（Rodo）、经济型陆运、B2B 空运货物及文件寄递。
- **[Jadlog Predict](https://www.jadlog.com.br/jadlog/jadlogPredict)：** 面向大都市和高单量地区，通过自动短信发送 1 小时派送时段提醒，方便你预估司机上门时间。
- **[Jadlog Pickup](https://www.jadlog.com.br/jadlog/pickup)：** 全巴西超过 4,000 家合作门店（覆盖 90% 以上人口），可用于包裹自提、寄件与电商逆向退货（*Reversa*），并通过短信和邮件通知进度。
- **[Jadlog Internacional](https://www.jadlog.com.br/jadlog/internacional)：** 依托 Geopost 国际网络，从巴西出口寄送至 154 个国家和地区（300 个目的地）。

**Jadlog 未公开说明的规则。** Jadlog 官网没有为所有网店公布统一的派送尝试次数上限、保证送达时效或 Pickup 网点固定保管天数。请查看 Pickup 包裹发来的短信或邮件通知，或查看网店订单页上的具体取件截止日期与重派规则。

## Jadlog 包裹停更、延误或未收到

对于网购订单，卖家是 Jadlog 的合同客户，负责处理退款、改址和补发。

**停留在 `Order created` 或 `COLETA SOLICITADA`。** 卖家已生成发货数据，但 Jadlog 尚未揽收或开单入网。请问网店包裹是否已经出库。

**运输途中静默（`Em transferência`）。** 巴西跨州陆运、周末以及区域分拣中心积压，常常会让两次网点扫描之间出现空档。参见[物流为什么停止更新](guide:tracking-not-updating)（英文）。

**显示 `ENTREGUE` 但没收到。** 先询问家人、邻居以及小区或大楼门房（*portaria*），并确认下单时是否选了 Jadlog Pickup 门店自提。如果无人代收，请立刻联系网店，由网店向 Jadlog 发起调查。详见[显示已签收但未收到](guide:delivered-not-received)（英文）。

**破损、丢失或退回。** 联系卖家前请保留外包装，并清晰拍摄破损部位与面单照片；详见[包裹破损](guide:damaged-parcel)（英文）、[包裹丢失](guide:lost-parcel)（英文）和[退回寄件人](guide:returned-to-sender)（英文）。

## 联系 Jadlog 与消费者维权渠道

Jadlog 的 [Atendimento 页面](https://www.jadlog.com.br/jadlog/atendimento) 没有公布面向收件人的客服电话或通用客服邮箱。实际可行的途径包括：

- **先联系网店：** 网店持有托运合同（`CT-e` / 发票），才能发起派送调查、修改地址或批准退款。
- **查找当地加盟网点：** 如需了解当地站点信息，可在 [Jadlog Unidades e Franquias](https://www.jadlog.com.br/jadlog/unidades) 查询负责服务的分部。
- **隐私与个人数据请求（LGPD）：** Jadlog 在 [jadlog.com.br/jadlog/lgpd](https://www.jadlog.com.br/jadlog/lgpd) 提供专门的数据主体入口。
- **消费者调解：** 如与参与平台的商家或承运商无法直接解决纠纷，拥有银级或金级 gov.br 账号的巴西消费者可在 [Consumidor.gov.br](https://www.consumidor.gov.br/pages/conteudo/publico/1) 免费登记投诉，参与企业承诺在 10 天内回复。

> 警惕通过短信、WhatsApp 或邮件发来的链接，谎称巴西国内 Jadlog 包裹需要缴纳小额“放行费”或“重派税”。遇到任何意外收费，请先向购物网店核实。详见[快递诈骗短信识别](guide:delivery-scam-texts)（英文）。

## 用 Peek 跟踪 Jadlog 包裹

由于全球 Geopost/DPD 承运商共用 14 位数字单号，[Peek](/) 会根据你的所在地区决定先问哪一家。在巴西，Peek 会在所有 14 位候选承运商中优先查询 Jadlog（排在欧洲各 DPD 分公司、Chronopost、SEUR 和 BRT 之前），Jadlog 确认后即显示“Jadlog 有这个包裹的记录。”。在巴西以外地区，Peek 会把 Jadlog 作为备选——先问欧洲 Geopost 承运商，都查不到再问 Jadlog。无论身在何处，粘贴 `jadlog.com.br/jadlog/captcha?cte=` 链接都会直接识别为 Jadlog；否则可在添加时点击“更换”，或在包裹页点“更换快递公司”后选择 Jadlog（搜索“DPD Brazil”也能找到）。

Peek 以访客身份查询 Geopost 的查件系统，无需邮编或 CPF。页面显示当前阶段（“已生成面单”、“已揽收”、“运输中”、“派送中”、“已签收”）、摘要扫描记录（来自 Geopost 时为英语，转自 Jadlog 本地查件系统时为葡萄牙语）以及签收时间戳。Peek 不展示预计送达日期、包裹重量、Jadlog Pickup 门店地址及收件人姓名或签名。

在 Jadlog 自己的查件系统正常响应期间，当你打开包裹页，或在派送当天开启通知时，Peek 最多每 10 分钟查询一次；在包裹进入派送当天，从当地时间早上 6 点起每 2 分钟查询一次。其余时间每小时查询一次。在“已生成面单”或“已揽收”状态下满 12 小时，或连续两天无新动态后，无论是否打开页面均改为每小时查询一次；在“已生成面单”下满七天后改为每 6 小时一次。当 Jadlog 查询无响应、Peek 仅通过物流查询服务跟进时，会每小时查询一次，静默一天后每 3 小时一次，派送当天最多每 15 分钟一次。

如果最新扫描距今已满四天，且包裹仍停留在“已生成面单”、“已揽收”或“运输中”，Peek 会标出最后一次扫描的日期，例如“自10月12日周一起没有物流更新”。包裹页随后会提示：“包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 Jadlog 查询。”超过三周无新扫描时，则会显示：“已超过三周没有新动态。如果还没收到，请联系卖家。Peek 会持续向 Jadlog 查询。”

你可以选择“每次扫描”、“重要环节”或“仅派送当天”通知模式。Peek 仅监控物流动态，无法替你预约重派或联系 Jadlog 网点。

## 关于 Jadlog 物流查询的常见问题

### Jadlog 运单号长什么样？

Jadlog 包裹单号为 14 位数字（例如 `48291736405821`，数字为虚构）。你可以用这 14 位数字在 Peek 或 [Jadlog 首页](https://www.jadlog.com.br/jadlog/home) 查询。

### Jadlog Predict 是什么？

[Jadlog Predict](https://www.jadlog.com.br/jadlog/jadlogPredict) 是在大都市和高单量区域提供的自动短信通知服务，会告知收件人包裹预计的 1 小时派送时段。

### Jadlog Pickup 怎么使用？

[Jadlog Pickup](https://www.jadlog.com.br/jadlog/pickup) 在全巴西拥有超过 4,000 家合作商业门店，收件人可前往取件或投递逆向退货。Jadlog 会发送短信和邮件通知，方便你跟踪包裹。

### 为什么 Jadlog 和 DPD、Geopost 有关联？

Jadlog 与 DPD、Chronopost、SEUR、BRT、Speedy 同属欧洲 Geopost 集团，因此沿用 14 位 Geopost 包裹单号，并接入 Geopost 覆盖 154 个国家和地区的国际网络。

:::sources
- [Jadlog: Home](https://www.jadlog.com.br/jadlog/home) – `RASTREIE SUA ENCOMENDA` 查询框与巴西网络概览
- [Jadlog: Quem Somos](https://www.jadlog.com.br/jadlog/quemsomos) – 隶属 Geopost 集团、500+ 加盟网点、4,000+ Pickup 合作门店、7,000+ 车辆、7,000 名直接与间接员工、154 个国家
- [Jadlog: Produtos e Serviços](https://www.jadlog.com.br/jadlog/servicos) – Jadlog.com（最高 30 公斤）、Package、Rodo（最高 50 公斤）、RodoEconômico、Cargo、DOC
- [Jadlog: Pickup](https://www.jadlog.com.br/jadlog/pickup) – 4,000+ 合作门店、覆盖 90% 以上人口、短信/邮件通知与 Reversa 退货
- [Jadlog: Predict](https://www.jadlog.com.br/jadlog/jadlogPredict) – 含 1 小时派送时段的自动短信提醒
- [Jadlog: Internacional](https://www.jadlog.com.br/jadlog/internacional) – 经 Geopost 出口至 154 个国家 / 300 个目的地
- [Jadlog: Unidades e Franquias](https://www.jadlog.com.br/jadlog/unidades) – 巴西加盟网点与分部查询
- [Jadlog: Atendimento](https://www.jadlog.com.br/jadlog/atendimento) – 公开客服页面范围
- [Jadlog: LGPD](https://www.jadlog.com.br/jadlog/lgpd) – 数据主体隐私门户
- [Consumidor.gov.br](https://www.consumidor.gov.br/pages/conteudo/publico/1) – 公共消费者调解平台（10 天回复、银级/金级 gov.br 账号）
- [Universal Parcel Scraper: jadlog](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/jadlog) – 14 位单号识别与巴西优先（`DPD Brazil` 别名）、`captcha?cte=` 链接规则、Geopost 访客接口、状态映射与刷新频率
:::
