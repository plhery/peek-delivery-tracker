---
title: 极兔巴西（J&T Express Brazil）查询：单号、状态与清关
description: 查询巴西极兔（J&T Express Brazil）包裹：8880 开头 15 位单号、葡语状态说明、Remessa Conforme 税费与客服。
slug: jt-express-brazil-tracking
published: 2026-11-24
updated: 2026-11-24
---

极兔速递巴西（J&T Express Brazil）是成立于 2015 年的国际快递网络 J&T Express 的巴西分支，其发展历程页面列出了其开展业务的 13 个国家。在巴西，它通过 50 多个转运中心、1,600 多条区域干线以及 6,000 多个 Pontos J&T 合作寄件网点，覆盖全巴西全部 5,571 个市镇，承接电商包裹、个人寄件和跨境直邮。你可以在 [J&T Express Brazil 官网查询页](https://www.jtexpress.com.br/trajectoryQuery)查件，或直接把运单号粘贴到上方查询框。

## 极兔巴西运单号格式与查找方式

运单号（葡萄牙语 *código de rastreio*）通常在网店的发货邮件或购物平台订单页面上；J&T 查询页称之为运单（*carta de porte*）。J&T Express Brazil 使用以 `8880` 开头的 15 位纯数字运单号。

| 格式（示例） | 说明 |
| --- | --- |
| `888048291736405` | 极兔巴西运单号：`8880` 后接 11 位数字。 |

如果你的发货通知里是以 `BR` 开头的 15 位单号（`BR`、12 位数字加 1 位数字或字母），那属于 [SPX Express Brazil](carrier:spx-br) 而非极兔；两位字母、九位数字加 `BR` 结尾的 13 位单号属于巴西邮政 [Correios](carrier:correios-br)；以 `AJ` 开头的 17 位单号属于[安骏物流（Anjun Logistics）](carrier:anjun)；不带 `8880` 前缀的 14 位数字则多属于 Jadlog。如果不确定单号归属哪家快递，请看[运单号格式指南](guide:tracking-number-formats)（英文）。

## 极兔巴西物流状态说明

J&T Express Brazil 的扫描描述均为葡萄牙语。其中 `Em transferencia` 对应两个不同阶段：在极兔揽收之前，表示寄件人仅完成电子下单；揽收之后，则表示包裹正在转运中心之间运输。Peek 会依据极兔内部状态码加以区分。

| 极兔巴西显示 | 中文含义 | Peek 显示 |
| --- | --- | --- |
| `Em transferencia`（揽收前） | 寄件人已完成电子运单登记，或包裹正等待实际揽收。 | “已生成面单” |
| `Coletado`、`... retirou a encomenda` | 极兔快递员或网点已从寄件人或寄件点收件。 | “已揽收” |
| `Em transferencia`、`Chegada da encomenda expressa`、`Saída da encomenda expressa` | 包裹已到达或离开转运中心/分拨站，正在发往你所在的州。 | “运输中” |
| `Em Rota de Entrega`、`... enviando a encomenda` | 当地快递员已装车，准备派送至你的地址。 | “派送中” |
| `Pedido Entregue` | 极兔已记录该包裹签收。 | “已签收” |

在揽收与 `Em Rota de Entrega` 之间，跨州公路干线往往会行驶数日而不产生中间扫描；下一条动态通常会在货车抵达区域分拨中心或目的地派送站卸货后出现。

## 极兔巴西派送服务、Pontos J&T 与个人寄件

J&T Express Brazil 提供面向网店和电商平台的 [B2C 电商物流](https://www.jtexpress.com.br/service/b2c)、[C2C 个人寄件](https://www.jtexpress.com.br/clients/c2c)以及国际跨境配送。

**重量与尺寸限制。** 根据[极兔巴西官网首页](https://www.jtexpress.com.br/)的运费计算器，单个包裹最重 30 公斤，单边长度不超过 120 厘米，长宽高之和不超过 240 厘米。

**Pontos J&T 网点。** 极兔在巴西共有 6,000 多家合作门店（[Pontos J&T](https://www.jtexpress.com.br/joinUs/pudo)），供寄件人交寄包裹。极兔官网页面并未将其作为收件人自提点介绍。

**派送尝试与预约。** 极兔巴西公开页面未说明派送尝试次数、承诺时效或保管期限。请在下单商家或电商平台 App 内查看预计送达日期和派送备注。

## 跨境包裹与巴西 Remessa Conforme 清关规则

J&T Express Brazil 还提供接入巴西税务合规计划 *Programa Remessa Conforme*（PRC）的[跨境物流与清关服务](https://www.jtexpress.com.br/service/cbt)。按照[巴西联邦税务局（Receita Federal）的 Remessa Conforme 规定](https://www.gov.br/receitafederal/pt-br/assuntos/aduana-e-comercio-exterior/manuais/remessas-postal-e-expressa/programa-remessa-conforme-o-que-e-como-funciona)，进口税缴纳方式取决于境外商家是否获得该计划认证：

- **已认证商家（PRC）：** 税费在结账时预先代收。货值（含货款、运费和保费）不超过 50 美元的个人包裹免征联邦进口关税（*Imposto de Importação* 0%），仅缴纳目的地州 17% 至 20% 的 ICMS 州税。超过 50 美元的包裹，联邦进口关税为 60% 并可享 30 美元税额减免，另加 17% 至 20% 的 ICMS。
- **未认证商家：** 即使订单不超过 50 美元，也一律按 60% 征收联邦进口关税且无 30 美元减免，另加 17% 至 20% 的 ICMS，且并非结账代收，而是在包裹入境巴西时征收。

由于跨境包裹入境时须接受海关查验，物流记录在转入极兔巴西国内干线前可能会停滞数天。一般清关说明见[包裹清关指南](guide:customs)（英文）。

## 极兔巴西包裹停滞、延误或丢失怎么办

网购订单由卖家与 J&T Express Brazil 签订运输合同，因此退款或补发应由卖家负责。

**连续数天无更新。** 刚创建的运单在卖家实际交件前会一直停在揽收前的 `Em transferencia`；揽收之后，巴西国内长途公路转运和周末也常造成多日无扫描。详见[为什么物流停止更新](guide:tracking-not-updating)（英文）。

**显示 `Pedido Entregue` 但没收到。** 先向家人、邻居或大楼门卫/前台（*portaria*）确认是否代收。若确认无人收到，请尽快同时联系卖家和极兔巴西核查签收细节。更多排查见[显示已签收但未收到](guide:delivered-not-received)（英文）。

**包裹破损或丢失。** 在丢弃任何包装前先拍摄外箱与面单照片，并第一时间告知商家。参见[包裹破损处理](guide:damaged-parcel)（英文）和[包裹丢失处理](guide:lost-parcel)（英文）。若包裹因多次派送未果被退回，请联系卖家安排退款或重发，另见[包裹退回寄件人](guide:returned-to-sender)（英文）。

## 极兔巴西客服与防诈骗提醒

订单退款或修改收件地址请先联系卖家。如需就运输中的包裹直接联系极兔巴西：

- **免费客服热线（SAC）：** `0800-055-0050`（见 [jtexpress.com.br](https://www.jtexpress.com.br/) 各页面）。
- **客服电子邮箱：** `meajuda@jtexpress.com.br`。
- **订单反馈表单：** [J&T Express Brazil 订单反馈](https://www.jtexpress.com.br/contactus/orderFeedback)。
- **升级投诉邮箱：** `escalations@jtexpress.com.br`。
- **监察部门（*Ouvidoria*）：** 极兔[监察页面](https://www.jtexpress.com.br/contactus/supervise)说明该渠道仅受理职业道德与内部合规举报，派送与订单问题请使用上述客服渠道。
- **消费者调解平台：** 如与已入驻的商家或快递公司发生争议且协商未果，拥有银牌或金牌 gov.br 账号的巴西消费者可在 [Consumidor.gov.br](https://www.consumidor.gov.br/pages/conteudo/publico/1) 免费提交投诉，入驻企业承诺在 10 天内回复。

> **极兔查询页安全警示：** J&T Express Brazil 明确声明**绝不会**通过 WhatsApp、短信或第三方链接要求收件人缴纳关税以放行或派送包裹。凡涉及费用或派送进度，请只信任极兔官方渠道。详见[快递诈骗短信指南](guide:delivery-scam-texts)（英文）。

## 使用 Peek 跟踪极兔巴西包裹

输入以 `8880` 开头的 15 位运单号时，[Peek](/) 会以高置信度自动识别为 J&T Express Brazil；同时也支持粘贴包含 `waybillNo=` 的 `jtexpress.com.br/trajectoryQuery` 或 `/mobile/expressTracking` 链接。如果遇到其他格式而 Peek 猜成别家，可在添加时点击“更换”或在包裹页点击“更换快递公司”选择 J&T Express Brazil（搜索“J&T Express Brasil”或“J&T Brazil”也能找到）。

Peek 直接查询 J&T Express Brazil 官网访客跟踪接口，显示当前阶段（“已生成面单”、“已揽收”、“运输中”、“派送中”、“已签收”）、极兔的葡萄牙语扫描描述，以及按巴西本地时间（`America/Sao_Paulo`）解析并换算为你设备时区的时间戳。Peek 不显示预计送达日期、自提点或签收照片；当极兔记录里出现快递员姓名时，会按极兔原文以脱敏方式显示。

无论页面是否打开或在派送当天开启提醒，Peek 最多每 30 分钟向 J&T Express Brazil 查询一次，手动刷新也不会提前触发；若查询失败则等待一小时再试。其余情况每小时检查一次。若停留在“已生成面单”或“已揽收”满 12 小时，或连续两天无新动态，不论如何跟踪都改为每小时检查一次；若在“已生成面单”停留满七天，则每 6 小时检查一次。Peek 不会通过第三方跟踪服务查询极兔巴西，所有更新均直接来自极兔官网。

当包裹停留在“已生成面单”、“已揽收”或“运输中”且最新一条扫描已过去四天时，Peek 会标出该扫描日期（例如“自 10 月 12 日起没有物流更新”）。页面随后提示：“包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 J&T Express Brazil 查询。”并附上指向极兔订单反馈表单的“向 J&T Express Brazil 寻求帮助”链接。如果超过三周仍无新扫描，则显示：“已超过三周没有新动态。如果还没收到，请联系卖家。Peek 会持续向 J&T Express Brazil 查询。”

你可以选择“每次扫描”、“重要环节”或“仅派送当天”通知。Peek 仅监控物流更新，无法代你改派包裹或联系极兔巴西。

## 极兔巴西快递查询常见问题

### 极兔巴西运单号长什么样？

J&T Express Brazil 运单号是以 `8880` 开头的 15 位数字（例如示例单号 `888048291736405`），直接粘贴到 Peek 或极兔查询页即可查询。

### 为什么我的极兔巴西包裹连续几天都显示 `Em transferencia`？

极兔在揽收前和跨区域转运途中都会使用 `Em transferencia`。巴西境内的长途公路干线运输以及周末常导致几天内没有新站点扫描。

### 极兔巴西会通过 WhatsApp 或短信收取关税吗？

不会。极兔官网查询页明确警告，绝不会通过 WhatsApp、短信或第三方链接要求支付关税；任何催促点击链接付款的消息都应视为诈骗。

### 如何联系极兔巴西客服？

可拨打免费热线 `0800-055-0050`、发邮件至 `meajuda@jtexpress.com.br`（升级投诉发至 `escalations@jtexpress.com.br`），或提交[订单反馈表单](https://www.jtexpress.com.br/contactus/orderFeedback)。网购退款请同时联系卖家。

:::sources
- [J&T Express Brazil: Home](https://www.jtexpress.com.br/) – 覆盖全巴西 5,571 个市镇、6,000+ Pontos J&T、1,600+ 路线、50+ 转运中心、30 公斤/120 厘米/240 厘米限制、SAC 电话与邮箱
- [J&T Express Brazil: Tracking](https://www.jtexpress.com.br/trajectoryQuery) – 运单查询及防范 WhatsApp/短信/第三方链接关税诈骗官方警示
- [J&T Express Brazil: History](https://www.jtexpress.com.br/aboutus/history) – 成立于 2015 年，列举 13 个业务国家
- [J&T Express Brazil: B2C Services](https://www.jtexpress.com.br/service/b2c) – 巴西电商物流服务
- [J&T Express Brazil: C2C Personal Shipping](https://www.jtexpress.com.br/clients/c2c) – 个人寄件服务
- [J&T Express Brazil: Cross-Border (CBT)](https://www.jtexpress.com.br/service/cbt) – 国际跨境运输与 Programa Remessa Conforme 清关
- [J&T Express Brazil: Pontos J&T (PUDO)](https://www.jtexpress.com.br/joinUs/pudo) – 供寄件人交件的合作门店
- [J&T Express Brazil: Order Feedback](https://www.jtexpress.com.br/contactus/orderFeedback) – 收件人反馈表单与联系方式
- [J&T Express Brazil: Ouvidoria](https://www.jtexpress.com.br/contactus/supervise) – 监察渠道受理范围与客服区分
- [Receita Federal: Programa Remessa Conforme](https://www.gov.br/receitafederal/pt-br/assuntos/aduana-e-comercio-exterior/manuais/remessas-postal-e-expressa/programa-remessa-conforme-o-que-e-como-funciona) – 认证与非认证商家的进口关税及 ICMS 规则
- [Consumidor.gov.br](https://www.consumidor.gov.br/pages/conteudo/publico/1) – 公共消费者调解平台（10 天回复期、银牌/金牌 gov.br 账号）
:::
