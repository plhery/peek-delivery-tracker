---
title: 安骏物流（Anjun Logistics）查询：中巴专线与巴西清关
description: 查询安骏物流（Anjun Logistics/Anjun Express）中巴跨境与巴西本地包裹：AJ17位单号格式、中英葡语状态、巴西合规清关与客服。
slug: anjun-wuliu-chaxun
published: 2026-11-23
updated: 2026-11-23
---

安骏物流（Anjun Logistics，在巴西以 Anjun Express 品牌运营）是一家 2011 年创立于中国的跨境与巴西本土电商物流企业，2019 年进入巴西市场并于 2025 年实现巴西全国网络覆盖（见 [关于安骏](https://anjunexpress.com/aboutUs)）。安骏负责中国头程揽收、航空出口、巴西进口清关以及巴西境内派送——清关放行后既可通过安骏自营巴西派送网络与代收点投递，也会将部分包裹移交给巴西邮政（Correios）完成最后派送。国际中巴专线可在 `anjuntrack.com/trajectory` 查询，巴西本土派送可在 [Anjun Express 巴西查件页](https://anjunexpress.com/trackPackage) 查询，也可以直接把单号粘贴到上方查询框。

## 安骏物流单号格式：17 位 `AJ` 编码

安骏运单号会出现在中国跨境电商平台发往巴西订单的发货通知中，标准格式为 17 位字符：`AJ` + 6 位日期（`YYMMDD`）+ 9 位数字（示例数字为虚构）：

| 单号（虚构） | 格式说明 |
| --- | --- |
| `AJ261015482917364` | 安骏物流 / Anjun Express 专属单号：`AJ` + 6 位日期 + 9 位数字。 |

安骏运行两套查件入口：`anjuntrack.com` 侧重中国至巴西的跨境全链路，[anjunexpress.com/trackPackage](https://anjunexpress.com/trackPackage) 侧重巴西境内尾程与本土件。注意如果订单给的是 15 位 `BR` 开头编码，属于 SPX Express Brazil；15 位 `8880` 开头属于 J&T Express Brazil；13 位以 `BR` 结尾的邮政编号属于巴西邮政 Correios。更多前缀请看 [快递单号格式指南](guide:tracking-number-formats)（英文） 与 [如何找到你的快递单号](guide:find-tracking-number)（英文）。

## 安骏物流中英葡语状态是什么意思

中巴跨境包裹通常在中国大陆和香港出口段显示英文扫描，抵达巴西后转为葡萄牙语扫描：

| 安骏物流原文 | 对你的实际含义 | Peek 显示阶段 |
| --- | --- | --- |
| `Objeto postado` | 电子预报或交寄登记，仓库尚未实物入库。 | 「已生成面单」 |
| `The goods have arrived at Anjun`；`Objeto coletado`（巴西境内寄出包裹的第一条扫描） | 安骏已在中国始发仓收货，或已从巴西寄件人处揽收。 | 「已揽收」 |
| `The goods have been issued`、`Departed Facility in SHENZHEN`、`Arrived at Sort Facility HONG KONG INTERNATIONAL AIRPORT`、`Departed Facility in HONG KONG INTERNATIONAL AIRPORT`、`Arrived at Sort Facility Destination Airport` | 深圳出库、经香港国际机场空运起飞并抵达巴西目的机场。 | 「运输中」 |
| `Recebimento de cargas no armazém courier` | 已到达巴西的快件货站，尚未清关。 | 「运输中」 |
| `Entrada no armazém alfândegado de TFK`、`Informações enviadas para análise da autoridade aduaneira/órgãos anuentes`、`Análise concluída - importação autorizada` | 进入巴西保税监管仓（TFK）、提交海关与监管部门审核及进口核准放行。 | 「清关中」 |
| `Pacote Liberado Aguadando a Coleta de Last Mile`、`Saída do Centro Internacional`、`Coletado por Last Mile`、`Objeto coletado`、`Objeto recebido pelos Correios do Brasil` | 清关放行，离开国际中心交由安骏巴西尾程网络或巴西邮政 Correios 承运。 | 「运输中」 |
| `O pacote chegou ao centro de trânsito`、`O pacote saiu do centro de trânsito`、`Objeto chegou ao ponto de entrega` | 经巴西中转中心发往并抵达当地派送站点（尚未外出派送）。 | 「运输中」 |
| `Objeto saiu para entrega ao destinatário` | 当地快递员今天已出发为你派送。 | 「派送中」 |
| `A entrega falhou`（如 `Cliente ausente` 或 `Endereço fora da rota`） | 派送未成功（如收件人不在或地址偏离路线）。 | 「派送未成功」 |
| `A entrega falhou: Pacote não pertence à Base` | 包裹误分到错误的巴西本地网点，正在内部重转而非已上门失败。 | 「运输中」 |
| `O pacote está a caminho do centro de trânsito para devolução.` | 多次派送未成功后返回中转中心，之后仍可能再次外出派送。 | 「需要留意」 |
| `O pacote foi assinado`、`Objeto entregue ao destinatário` | 安骏已记录包裹签收送达。 | 「已签收」 |

特别提醒：`Objeto chegou ao ponto de entrega` 指包裹到达了安骏的本地派送站（并非收件人自提柜台），需等待出现 `Objeto saiu para entrega ao destinatário` 才表示快递员当天上门。

## 安骏巴西本土派送网络与巴西合规清关（Remessa Conforme）

根据 [Anjun Express 巴西官网首页](https://anjunexpress.com/home)、[尾程派送页](https://anjunexpress.com/lastMile) 与 [代收点页](https://anjunexpress.com/collectionPoint)，安骏在巴西运营头程、尾程、B2B 物流、仓储、直发（Envio direto）及 Super Express 业务，官网列明拥有超 1,000 个代收点（合作页列超 500 个）、超 350 辆车及超 2,000 名员工。安骏官网未公开统一派送尝试次数、固定时效或保管期，具体期限请查看购物平台的订单页面。

从中国发往巴西的电商件普遍适用 [巴西联邦税务局合规计划（Programa Remessa Conforme，PRC）](https://www.gov.br/receitafederal/pt-br/assuntos/aduana-e-comercio-exterior/manuais/remessas-postal-e-expressa/programa-remessa-conforme-o-que-e-como-funciona)：

- **已认证跨境平台或网店：** 结账时直接代收税费。个人收件且货值加运费与保费不超过 50 美元的包裹，联邦进口关税为 0%，另缴 17%–20% 州级流转税（ICMS）；超过 50 美元则按 60% 计征进口关税并减免 30 美元税额，再加 17%–20% ICMS。
- **未认证网店：** 无论货值是否超过 50 美元，均按 60% 计征联邦进口关税（无减免）另加 17%–20% ICMS，入境巴西清关前补缴放行。

当轨迹停在 `Entrada no armazém alfândegado de TFK` 或 `Informações enviadas para análise da autoridade aduaneira/órgãos anuentes` 时，巴西海关或检验机构正在审单；如要求补交资料或缴税，请通过购物平台消息或安骏官方海关邮箱核实，切勿点击陌生短信链接。详见 [包裹海关扣留与清关指南](guide:customs)（英文）。

## 安骏包裹停更、分拣错站或没收到怎么办

网购包裹的退款、补发和清关争议均由购物平台或卖家负责：

- **中巴飞行与保税仓空窗：** 从 `Departed Facility in HONG KONG INTERNATIONAL AIRPORT` 到 `Recebimento de cargas no armazém courier` 期间，受航班排期和机场保税入库排队影响常有数日无扫描。见 [物流为什么停止更新](guide:tracking-not-updating)（英文）。
- **显示 `A entrega falhou: Pacote não pertence à Base`：** 虽然字面带「entrega falhou（派送失败）」，但实际只是包裹分错到隔壁派送网点，正在调拨回正确站点。
- **已签收未收到、破损、丢失或退件：** 先核对家人、邻居与小区门房（portaria），随后联系平台与安骏 SAC 客服；破损件请第一时间拍外箱和内物照联系卖家。见 [显示已签收但未收到](guide:delivered-not-received)（英文）、[包裹破损怎么办](guide:damaged-parcel)（英文）、[包裹丢失怎么办](guide:lost-parcel)（英文） 和 [包裹退回寄件人](guide:returned-to-sender)（英文）。

## 安骏巴西客服（SAC）与最新邮箱公告

[Anjun Express 巴西官网首页](https://anjunexpress.com/home) 明确公告旧邮箱 `sac@anjunexpress.com.br` 已于 2026 年 8 月 12 日停用，现行有效联系方式为：

- **巴西全国客服电话（SAC）：** `(11) 5026-7008`，周一至周五 `08:00–17:30`（节假日除外）。
- **现行 SAC 客服邮箱：** `sac@anjun.com.br`。
- **在线客服聊天：** 位于 [anjunexpress.com/home](https://anjunexpress.com/home)。
- **国际清关咨询邮箱：** `atendimento.aduaneiro@anjun.com.br`。
- **国际清关申诉专员（Ouvidoria）：** `ouvidoria.aduaneira@anjun.com.br`。
- **巴西消费者调解平台：** 若争议久拖未决，持有银级或金级 `gov.br` 账号的巴西消费者可通过 [Consumidor.gov.br](https://www.consumidor.gov.br/pages/conteudo/publico/1) 发起调解（入驻企业承诺 10 天内回复）。

> 请警惕冒充安骏物流发送「包裹扣关需缴税」的诈骗短信或 WhatsApp 链接。缴费前务必在下单平台或直接向 `atendimento.aduaneiro@anjun.com.br` 核实。参见 [谨防快递诈骗短信](guide:delivery-scam-texts)（英文）。

## 用 Peek 跟踪安骏物流包裹

[Peek](/) 会通过 17 位 `AJ` + 合法 `YYMMDD` 日期 + 9 位数字的专属格式立即认出安骏物流（如虚构单号 `AJ261015482917364`），无需先向安骏确认；带 `t=`/`orderNum=` 的 `anjuntrack.com` 链接或带 `tracking=` 的 `anjunexpress.com/trackPackage` 链接也可直接粘贴识别。如需手动切换，点添加页的「更换」或详情页的「更换快递公司」即可。

Peek 会先查询 `anjuntrack.com` 获取中巴跨境全程，若暂无记录则自动再查 `anjunexpress.com` 覆盖巴西本土件。Peek 展示规范化阶段（「已生成面单」「已揽收」「运输中」「清关中」「派送中」「派送未成功」「需要留意」「已签收」等）、中英葡语扫描原文、派送失败原因、巴西城市与州（自动隐藏街道门牌）、目的国及自动区分中国/巴西当地时钟换算的设备本地时间，同时自动剔除 GPS 坐标、司机姓名、扫描附带电话邮箱、签名与照片。安骏追踪系统不返回预计送达日期、重量或下游转单号。

只要你打开着这个包裹，或在派送当天开启了通知，Peek 最快每 10 分钟检查一次；同样条件下，安骏显示 `Objeto saiu para entrega ao destinatário` 的当天从当地时间早上 6 点起最快每 2 分钟一次（仅在安骏官方查询正常响应时）；其他时候每小时一次。连续两天无新扫描（或前段处于「已生成面单」「已揽收」满 12 小时）也降为每小时一次。停留「已生成面单」满 7 天后每 6 小时一次（你打开包裹或预计当天送达时除外）。安骏官方查询无响应、只有第三方查询服务有结果时，Peek 每小时检查一次，最新扫描超过一天后每 3 小时一次，派送当天在你打开包裹或开启通知时最快每 15 分钟一次。最新扫描已满四天、包裹仍处于「已生成面单」「已揽收」或「运输中」时，Peek 会用这条扫描的日期标出包裹，如「自10月12日周一起没有物流更新」，包裹页随后提示「包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 Anjun Logistics 查询。」，超过三周无动静则提示「已超过三周没有新动态。如果还没收到，请联系卖家。Peek 会持续向 Anjun Logistics 查询。」。

通知偏好可选「每次扫描」「重要环节」或「仅派送当天」。Peek 仅负责监控轨迹，无法替你清关或联系安骏客服。

## 关于安骏物流查询的常见问题

### 为什么安骏物流的轨迹同时出现英文和葡萄牙语？

中巴跨境件在中国始发仓和香港机场出口阶段由系统记录英文轨迹，进入巴西办理进口清关与本土派送后自动切换为葡萄牙语扫描。

### `A entrega falhou: Pacote não pertence à Base` 是派送失败了吗？

不是司机上门没人。它指包裹分拣时误发到了错误的巴西本地派送网点，安骏正在内部调拨至正确网点，因此 Peek 仍将其保持在「运输中」。

### `sac@anjunexpress.com.br` 邮箱还能用吗？

不能。安骏巴西官网公告该旧邮箱已于 2026 年 8 月 12 日停用，请改用新客服邮箱 `sac@anjun.com.br`、致电 `(11) 5026-7008`（周一至周五 `08:00–17:30`），或将清关问题发至 `atendimento.aduaneiro@anjun.com.br`。

### 安骏物流会把包裹交给巴西邮政（Correios）派送吗？

会的。部分跨境包裹在巴西清关放行后会出现 `Objeto recebido pelos Correios do Brasil`，说明已由巴西邮政接手最终投递，安骏查件系统仍会同步这一交接记录。

:::sources
- [Anjun Express：巴西官网首页](https://anjunexpress.com/home) – 巴西物流业务、网点规模、现行 SAC 电话/邮箱、海关申诉邮箱及旧邮箱停用公告
- [Anjun Express：关于我们](https://anjunexpress.com/aboutUs) – 2011 年中国创立、2019 年进入巴西、2025 年巴西全国覆盖
- [Anjun Express：巴西本土查件页](https://anjunexpress.com/trackPackage) – 巴西境内包裹追踪入口
- [Anjun Express：尾程派送](https://anjunexpress.com/lastMile) – 巴西尾程派送服务
- [Anjun Express：代收点网络](https://anjunexpress.com/collectionPoint) – 巴西合作代收点网络
- [巴西联邦税务局：Programa Remessa Conforme 合规计划](https://www.gov.br/receitafederal/pt-br/assuntos/aduana-e-comercio-exterior/manuais/remessas-postal-e-expressa/programa-remessa-conforme-o-que-e-como-funciona) – 巴西 50 美元内外进口关税与 ICMS 州税规则
- [Consumidor.gov.br：巴西消费者公共调解平台](https://www.consumidor.gov.br/pages/conteudo/publico/1) – 10 天回复机制与 gov.br 账号要求
- [Universal Parcel Scraper: anjun](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/anjun) – AJ 17 位日期校验、`anjuntrack.com` 与 `anjunexpress.com` 双入口查询、英葡状态映射与分错网点处理、隐私清理与刷新节奏
:::
