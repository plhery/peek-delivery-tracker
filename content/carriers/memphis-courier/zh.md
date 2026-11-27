---
title: Memphis Courier 巴西专线追踪：M-MIA 单号、清关与客服
description: 追踪从美国发往巴西的 Memphis Courier 进口包裹：M-MIA 单号格式、GRU 机场与 ANVISA 查验状态、CPF 核验规则及联系渠道。
slug: memphis-courier-tracking
published: 2026-11-26
updated: 2026-11-26
---

Memphis Courier 是一家拥有超过 25 年历史的纯巴西本土物流公司，承接进出口快件及正式报关货件。公司依托位于圣保罗瓜鲁柳斯国际机场（GRU）的快件货站和圣保罗办事处，提供空运运输、巴西联邦税务局（Receita Federal）清关、巴西国家卫生监督局（ANVISA）合规审批（包括依据 RDC 660 与 RDC 327 进口自美国、欧洲、哥伦比亚及乌拉圭的医用大麻等药品）以及覆盖全巴西的派送服务。

其物流追踪全程覆盖包裹飞抵瓜鲁柳斯机场、接受 Receita Federal 与 ANVISA 查验直至末端送达的各个阶段。

## Memphis Courier 运单号格式

Memphis Courier 运单号采用极具辨识度的 11 位字母数字组合：

- **`M` + 7 位数字 + `MIA`**（共 11 位，例如 `M4829173MIA`）——这是 Peek 识别为 Memphis Courier 单号的格式。

Peek 接受 6 至 30 位字母与数字组成的 Memphis Courier 单号，会去除空格并将字母转为大写。Memphis Courier 的追踪门户（`clients.memphiscourier.com.br`）无需登录客户账号即可直接查询。只要输入 `M` + 7 位数字 + `MIA` 格式，Peek 会单独向 Memphis Courier 发起查询，并在对方返回数据后显示“Memphis Courier 有这个包裹的记录。”关于发往巴西的常见跨境单号格式，请参阅[运单号格式指南](guide:tracking-number-formats)（英文）。

## Memphis Courier 追踪状态说明

Memphis Courier 追踪接口会返回 3 位字母状态码、葡萄牙语说明以及地点列（州名或国家名）。其追踪页顶部进度条分为 Criado（`FLT`）、海关（`BRP`）、Liberado 和 Entregue 四步；后两步对应的 `CLA` 和 `DLV` 仅通过进度条标签体现。

| 代码 | Memphis Courier 原文 | 含义 |
| --- | --- | --- |
| `FLT` | Seu pedido foi criado com sucesso | 运单已在 Memphis Courier 系统中创建，尚未空运起飞。 |
| `CHK` | Entrada registrada no Centro de Distribuição | 已登记进入 Memphis Courier 分拨中心。 |
| `ENO` | Encomenda em trânsito | 正在 Memphis Courier 网络中运输。 |
| `TRA` | Encomenda em transferência | 正在各操作网点之间转运。 |
| `DEP` | A encomenda saiu da origem e está a caminho do aeroporto de destino (Guarulhos - SP) | 已离开始发枢纽，乘航班飞往瓜鲁柳斯国际机场（GRU）。 |
| `ARR` | Seu pedido chegou ao aeroporto de destino (Guarulhos - SP) | 已降落圣保罗瓜鲁柳斯机场。 |
| `AAZ` | Pedido recebido pela GRU Airport | 已由 GRU 机场货运站接收，准备正式报关。 |
| `BRP` | Processo de desembaraço iniciado | 巴西海关清关流程已启动。 |
| `HOC` | Encomenda sendo inspecionada pela Alfândega | 正在接受巴西联邦税务局（Receita Federal）海关查验。 |
| `ANV` | Em inspeção pela ANVISA | 清关期间正在接受巴西国家卫生监督局（ANVISA）监管查验。 |
| `CLA` | Liberado（进度条标签） | 海关及相关监管部门已放行，进入巴西国内派送环节。 |
| `DLV` | Entregue（进度条标签） | 已送达巴西收件地址。 |

## 海关清关、CPF 核验与 ANVISA 审批

每一票以个人名义发往巴西的快件进口均须提供收件人 CPF（巴西个人税号），方可办理 Receita Federal 清关。正如 Memphis Courier 在其[附加服务页面](https://memphiscourier.com.br/servicos-adicionais/)所指出的，许多海外网店在结账时并未设置 CPF 填写栏；遇到此类情况时，Memphis Courier 客服团队会主动联系收件人核验 CPF，以免清关受阻。

对于 Memphis Courier 从美国、欧洲、哥伦比亚或乌拉圭承运入境的医用大麻及其他药品，包裹在瓜鲁柳斯机场可能要经过 Receita Federal（`HOC`）和 ANVISA（`ANV`）两项查验，随后才会出现 `CLA`（已放行）。如果你的包裹连续多个工作日停留在 `BRP`、`HOC` 或 `ANV`，请检查邮箱与 WhatsApp：Memphis Courier 会通过邮件、WhatsApp、在线聊天或电话与客户沟通，可能需要你确认 CPF 或补充文件。更多国际进口清关说明见[海关清关指南](guide:customs)（英文）和[物流停止更新怎么办](guide:tracking-not-updating)（英文）。

一旦海关放行（`CLA`），国内派送覆盖巴西所有州。如果你还有其他经由别家快递发往巴西的订单，可参考 [Anjun Express](carrier:anjun)、[Jadlog](carrier:jadlog) 或 [J&T Express Brazil](carrier:j-and-t-br) 的追踪说明。

## 使用 Peek 追踪 Memphis Courier 包裹

将 `M...MIA` 单号粘贴到 Peek 中，对方接口返回数据后 Peek 会自动匹配至 Memphis Courier。如需手动指定或切换承运商，可在添加包裹时点击“更换”，或在追踪页点击“更换快递公司”。

Peek 通过开源的 [universal-parcel-scraper Memphis Courier 适配器](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/memphis-courier)直接查询 Memphis Courier 的公开追踪服务，读取每条扫描的状态码、葡萄牙语说明、地点标签以及总重量（千克，如有）。Memphis Courier 的时间以本地墙钟形式给出，仅在末尾附带偏移量时才标明时区。Peek 会保留已有偏移量；若无偏移量，则按扫描地时区解析（`Estados Unidos` 按纽约时间，`SP` 或 `Brasil` 按圣保罗时间），无地点条目沿用相邻条目时区，否则统一按圣保罗时间解析，并以你的设备本地时区展示。客户门户中的内部备注、件数、邮编、目的地及清关附件等字段仅保留在 Memphis Courier 自己的网站上。

Peek 将 Memphis Courier 的 12 个扫描代码清晰归类：`FLT` 对应 **已生成面单**；`CHK` 至 `AAZ` 以及清关放行后的 `CLA` 对应 **运输中**（Memphis Courier 代码体系直接从建单进入分拨中心转运，未单独设置 **已揽收** 代码）；`BRP`、`HOC` 与 `ANV` 对应 **清关中**；出现 `DLV` 后归为 **已签收**。

在 Memphis Courier 的追踪器有响应时，只要你在 Peek 中打开包裹页面，或在派送当日开启通知，Peek 最多每 10 分钟检查一次；其余情况下每小时检查一次。Memphis Courier 的追踪器没有派送中步骤，因此 Peek 不会切换到派送当天每 2 分钟一次的检查。若包裹停留在“已生成面单”或“已揽收”超过 12 小时，或连续两天没有新动态，无论你以何种方式关注，均改为每小时检查一次；若停留在“已生成面单”超过七天，则每 6 小时检查一次。当 Memphis Courier 的追踪器无响应、Peek 仅通过追踪服务跟进时，每小时检查一次，静默一天后每 3 小时一次，派送当天最多每 15 分钟一次。

当最新扫描距今已满四天，且包裹仍处于“已生成面单”、“已揽收”或“运输中”时，Peek 会用该次扫描的日期加以标记，例如“自10月12日周一起没有物流更新”。包裹页面随后会显示“包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 Memphis Courier 查询。”，并附上指向 Memphis Courier Fale Conosco 页面的“向 Memphis Courier 寻求帮助”链接。如果三周没有新扫描，页面会显示“已超过三周没有新动态。如果还没收到，请联系卖家。Peek 会持续向 Memphis Courier 查询。”

你可以在 Peek 通知偏好中选择 **每次扫描**、**重要环节** 或 **仅派送当天**，按需接收提醒。

## 联系 Memphis Courier 客服

Memphis Courier 在其网站公布了官方支持渠道，并在官方免责声明中明确指出，不将 Reclame Aqui 等第三方投诉网站视作官方客服或工单渠道。

- **官方联系页面：** [Fale Conosco](https://memphiscourier.com.br/fale-conosco/)（在线联系表单）
- **电话/客户服务：** +55 11 94264-7550（同时适用于 GRU 快件货站与圣保罗办事处）
- **邮箱：** `atendimento@memphiscourier.com.br`、`atendimento1@memphiscourier.com.br` 与 `info@memphiscourier.com.br`
- **瓜鲁柳斯快件货站：** Aeroporto Internacional de Guarulhos — Terminal Courier, CEP 07190-100, São Paulo, Brazil
- **圣保罗办事处：** Av. Marques de São Vicente, 230, cj 1216 — Barra Funda, CEP 01139-000, São Paulo, Brazil（[Quem Somos](https://memphiscourier.com.br/quem-somos/)）

联系客服时请提前准备好 `M...MIA` 运单号与收件人 CPF，以便团队第一时间调取你的清关档案。

## 关于 Memphis Courier 物流查询的问题

### Memphis Courier 追踪里的 `ANV`（“Em inspeção pela ANVISA”）是什么意思？

`ANV` 表示巴西国家卫生监督局（ANVISA）正在作为进口清关环节的一部分对货件进行查验。Memphis Courier 承接须经 ANVISA 审批的医用大麻（RDC 660 / RDC 327）进口清关，包裹会一直停留在清关阶段直至出现 `CLA`（已放行）。

### 为什么 Memphis Courier 会联系我索要 CPF？

巴西联邦税务局（Receita Federal）要求每一票个人进口快件都必须提供收件人 CPF。若海外电商结账页未采集 CPF，Memphis Courier 客服团队会在清关前联系你完成核验，否则无法继续清关。

### 为什么 `DEP` 和 `ARR` 之间没有任何扫描记录？

`DEP` 记录包裹从始发地起飞前往瓜鲁柳斯机场，`ARR` 记录航班落地。Memphis Courier 的代码表未设置飞行途中节点，因此下一条扫描就是落地 `ARR` 或机场货站接收 `AAZ`。

### Memphis Courier 会回复发布在 Reclame Aqui 上的求助吗？

不会。Memphis Courier 网站声明指出，为确保可追溯性与数据保密，公司仅通过 [memphiscourier.com.br](https://memphiscourier.com.br/) 公布的官方渠道（电话、邮箱及联系表单）受理投诉、咨询与说明。

:::sources
- [Memphis Courier](https://memphiscourier.com.br/)
- [Memphis Courier — Quem Somos](https://memphiscourier.com.br/quem-somos/)
- [Memphis Courier — Serviços Adicionais](https://memphiscourier.com.br/servicos-adicionais/)
- [Memphis Courier — Fale Conosco](https://memphiscourier.com.br/fale-conosco/)
- [universal-parcel-scraper Memphis Courier 适配器](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/memphis-courier)
:::
