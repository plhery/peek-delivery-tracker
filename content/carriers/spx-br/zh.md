---
title: 巴西 SPX Express（Shopee Xpress）单号查询、状态与关税
description: 查询巴西 SPX Express（Shopee）包裹：BR 单号格式、葡语轨迹状态、自提点取件、Remessa Conforme 税费及退款。
slug: spx-express-baxi-chaxun
published: 2026-11-24
updated: 2026-11-24
---

SPX Express（Shopee Xpress）是 Shopee 在巴西的自营官方物流网络，负责从卖家处揽收包裹并派送到收件人门口或合作自提点 Agência Shopee。Shopee 将该服务称为 *Entrega Padrão*（标准派送），你可以在 Shopee App 内查看订单、预计送达日及退款入口，也可以在 `spx.com.br` 输入运单号查询，或直接把单号粘贴到上方查询框中。

## 巴西 SPX Express 单号格式与查找方式

在 Shopee App 中依次进入“Eu”（我）>“Minhas Compras”（我的订单），在“A Caminho”（运输中，部分帮助文章写作“A Receber”）中找到订单并点击“Status de Envio”（物流状态），即可看到系统自动填入的运单号和预计送达日。

Peek 收录的巴西 SPX 单号格式如下（以下均为虚构示例）：

| 单号（虚构） | 归属与说明 |
| --- | --- |
| `BR4829105736491` | SPX Express：`BR` 加 12 位数字加 1 位数字结尾。 |
| `BR482910573649X` | SPX Express：`BR` 加 12 位数字加 1 位字母结尾。 |
| `BR482910573649XSPXLM48291057` | SPX Express：上述单号后接 `SPXLM` 及 8 位数字（常见于平台面单）。 |
| `EA482910576BR` | 2 位字母、9 位数字加 `BR`：属于巴西邮政（Correios），并非 SPX 单号。 |

订单编号（`ID do pedido`）不是运单号，但联系 Shopee 客服时，Shopee 可能会要求你提供。

并非所有 Shopee 巴西订单都由 SPX 承运：除 *Entrega Padrão*（Shopee Xpress）外，还有 *Entrega Correios*（单号可在巴西邮政查询）与 *Entrega do Vendedor*（卖家自派）。跨境国际订单在巴西境内可能交由巴西邮政（Correios）或[安骏物流（Anjun）](carrier:anjun)派送，但部分跨境包裹也会在 SPX 轨迹中直接出现中国段与清关段记录（见下表）。

## 巴西 SPX Express 葡语物流状态含义解析

SPX 巴西轨迹以葡萄牙语输出，部分节点在冒号后标注分拨中心或城市名：

| SPX 葡语轨迹 | 对你的含义 | Peek 显示 |
| --- | --- | --- |
| `Pedido em preparação` | 卖家正在备货打包。 | “已生成面单” |
| `Pedido postado …` | 卖家已将包裹送至寄件点（`spx.com.br` 会附城市名），待 SPX 揽收。 | “已揽收” |
| `Seu pedido foi coletado` | SPX 已揽收包裹。 | “已揽收” |
| `Seu pedido chegou ao centro logístico: …` | 已抵达 SPX 物流中心。 | “运输中” |
| `Seu pedido saiu do centro logístico: …` | 已离开该物流中心。 | “运输中” |
| `O pedido está em separação no centro logístico, novas atualizações em até 1 dia.` | 正在物流中心分拣，SPX 提示通常 1 天内更新。 | “运输中” |
| `Pedido em trânsito` | 正在物流中心之间转运。Shopee 说，跨州或去较远的城市时，包裹会在转运卡车上待几天，卸车并在你所在州的分拨中心扫描后才有下一条记录。 | “运输中” |
| `Pedido em rota de entrega para seu endereço, aguarde pelas próximas horas.` | 派送员正前往你的地址派送，预计数小时内送达。 | “派送中” |
| `Houve um imprevisto durante a entrega do seu pedido …`、`Um imprevisto ocorreu …` | 当日投递遇到突发情况未能完成，SPX 会尽快重新安排派送。 | “派送未成功” |
| `Pedido entregue` | 包裹已送达签收。 | “已签收” |
| `Pedido será devolvido ao vendedor`、`Pedido será devolvido ao vendedor. Por favor, aguarde.` | 包裹正在退回卖家。 | “已退回寄件人” |
| `Pedido devolvido` | 已退回给卖家。 | “已退回寄件人” |
| `Pedido descartado` | 派送失败后包裹已被作废处理，既未送达也未退回卖家，请联系 Shopee。 | “需要留意” |

部分包裹使用 SPX 自有车队文案（以 `Seu pacote` 开头），例如 `Seu pacote foi recebido pelo centro de triagem`（到达分拣中心）、`Seu pacote foi recebido pelo centro de distribuição`（到达末端配送中心）、`Seu pacote está sendo entregue pelo entregador`（正在派送，若与建单同日单独出现则按运输中处理）及 `Seu pacote foi entregue`（已送达），Peek 均按相同阶段展示。

**中国发往巴西的跨境节点。** 以 `[China]` 开头的记录代表中国端扫描（如 `[China]O pedido foi liberado na alfândega de exportação` 表示中国出口清关放行）；之后依次出现 `Pedido chegou no Brasil`（抵达巴西）、`Pedido está na alfândega de importação`（进入巴西进口海关）、`Pedido em inspeção na alfândega de importação`（海关查验中）以及英文 `Import Customs Cleared`（海关放行）。若出现 `O pacote não pôde ser importado, será retornado ou confiscado pela alfândega`，表示巴西海关拒绝进口，包裹将被退运或没收（Peek 显示“需要留意”）。请联系 Shopee：跨境订单的退款按常规步骤申请（见下文）。

## SPX 派送次数、签收口令与 Agência Shopee 自提

- **参考时效：** 无法预约指定日期或时段。巴西本土卖家订单自揽收起通常为 **1–25 个工作日**，跨境国际订单一般为 **30–50 个自然日**，具体以订单页预计日期为准。
- **派送尝试与改址：** 最多尝试派送 **3 次**，三次失败后退回卖家并自动退款。收货地址仅可在付款确认前修改一次；付款后无法修改。
- **签收口令（*palavra-chave*）：** 部分订单会生成随机口令，需在收货时告知派送员（显示在订单页及 Shopee 通知中）。本人外出可交给信任的邻居或门卫（*porteiro*），切勿在拿到实物前提前泄露口令。
- **Agência Shopee 自提（*Retire Fácil*）：** 结账时可在配送方式中选择合作自提点（下单后不可改为送货上门）。到点后 Shopee 通过 WhatsApp、邮件或 App 发送取件 PIN 码，凭 PIN 码和运单号在 **7 个工作日**内领取，逾期由 SPX 退回。可在 [SPX 网点地图](https://spx.com.br/service-point) 查找自提点。

## 巴西跨境税费（Remessa Conforme 合规计划）

巴西联邦税务局（Receita Federal）已将 Shopee 列入 *Programa Remessa Conforme*（PRC）认证平台，合规订单在结账时直接代收进口税与州流转税（ICMS）；如结账未计税，入境时将按非认证标准征税且不享受减免：

- **进口关税（认证平台个人包裹）：** 货值（含货价、运费与保费）不超过 50 美元适用免税档（0%），超过 50 美元按 60% 计征并减免等值 30 美元税额。
- **ICMS 州税：** 依收件州税率通常为 17% 至 20%（不论是否认证）。

Shopee 提示巴西税号（CPF）状态异常会导致跨境订单受阻，且进口税不予退还。若由安骏物流（Anjun）派送的包裹被海关扣留，Shopee 可能要求提交付款证明、证件复印件与巴西税局的货值证明表（RCV）。巴西税局不会通过催款单（boleto）、电话或邮件收税。

## SPX 包裹停滞、延误或未收到怎么办

- **多日无更新：** 跨州卡车干线、周末和分拨中心排队期间常无新扫描，路线看似绕远也属正常分拨安排；进入派送后往往会一次性更新多条记录。详见[为什么物流停止更新](guide:tracking-not-updating)（英文）。
- **单号暂不可查：** 可能尚未揽收或系统延迟，请通过 Shopee 站内聊天联系卖家核对。
- **超期未到：** 超过预计送达日后，可在 Shopee App 路径“Eu”>“A Caminho”> 订单 >“Pedido de Reembolso”以“Não recebi o pedido”发起退款，卖家 2 天内未回复将自动退款。
- **显示已签收但未收到：** 先排查邻居、家人和门卫，标准派送须在签收后 **7 天内**在 App 发起退款，Shopee 可启动快递核查（*acareação*）。详见[显示已签收但未收到](guide:delivered-not-received)（英文）。
- **已退回：** 出现 `Pedido será devolvido ao vendedor` 后，包裹正在退回卖家；派送失败的订单 Shopee 会自动退款，详见[包裹退回寄件人](guide:returned-to-sender)（英文）。

## 联系客服：统一通过 Shopee 处理

Shopee 说明收件人无法就物流查询或派送问题直接联系 SPX，这些问题由 Shopee 在 *Garantia Shopee* 下处理：

- **Chat Shopee 在线客服：** 在 Shopee App 进入“Eu”>“Central de Ajuda”>“Atendimento ao Cliente”>“Chat Shopee”，巴西利亚时间周一至周五 `08:00–22:00`、周末 `08:00–20:00`。
- **在线提交表单：** 可全天候通过 [Shopee 客服时间页面](https://help.shopee.com.br/portal/4/article/141507) 提交（请准备订单号）。
- **政府调解平台：** 如争议未解决，持有银牌或金牌 `gov.br` 账号的巴西消费者可在 [consumidor.gov.br](https://www.consumidor.gov.br) 投诉，Shopee Brasil 承诺 10 天内回复。

> Shopee 仅通过官方 App、认证社媒或以 `shopee.com` 结尾的邮箱联系用户，运费全部在 App 内结算；任何要求站外付运费的消息均为诈骗。详见[快递诈骗短信识别指南](guide:delivery-scam-texts)（英文）。

## 使用 Peek 追踪巴西 SPX Express 包裹

在 [Peek](/) 中粘贴 `BR` 加 12 位数字加 1 位数字或字母的运单号，即可立刻识别为 SPX Express Brazil；粘贴来自 `spx.com.br`（含 `/track?` 或 `/detail/`）的链接同样有效（即使短信中省略了 `https://`）。Peek 的 SPX 查询只接受 `BR` 运单号，不接受 Shopee 订单编号。

Peek 读取与 `spx.com.br` 相同的轨迹源，展示标准阶段、SPX 葡语（及少量英文）扫描原文、中心/枢纽/口岸地点和签收时间，还会保留网页端隐藏的部分节点（如到达最后物流中心或卖家处揽收失败）。Peek 不显示预计送达日、重量、自提点或本地合作商；分享链接中妥投仅显示“已签收”而不公开签收人姓名。口令、自提 PIN 与退款仍需在 Shopee App 查看。

在 SPX 尚未录入单号前，Peek 会显示“等待 SPX Express Brazil 更新”并持续查询；若两天后仍无记录，则提示“两天过去了，快递公司仍查不到这个单号。请检查单号、更换快递公司，或删除这个包裹。”并改为每日查询、满一周后改为每周查询。包裹在途满 4 天无更新时，Peek 会提供指向 Shopee 客服页面的“向 SPX Express Brazil 寻求帮助”链接。

在打开包裹页面或预计派送当天开启提醒时，只要 SPX 官网正常响应，Peek 最高每 10 分钟检查一次，在 SPX 扫出派送中当天自当地时间早上 6 点起最高每 2 分钟检查一次；其余时间每小时检查一次。通过聚合追踪服务跟进时，每小时检查一次，在派送当天打开页面或开启提醒时最高每 15 分钟检查一次。通知预设可选“每次扫描”、“重要环节”或“仅派送当天”。Peek 无法代你联系 SPX 或 Shopee。

## 关于巴西 SPX Express 追踪的常见问题

### SPX Express 和 Shopee 是同一家吗？

它是 Shopee 在巴西的自营官方物流网络，由 *SPX Express Brasil Ltda.* 运营，拥有 100 多个物流枢纽和 11 个分拨中心。

### 如何查询巴西 SPX Express 包裹？

可在 Shopee App（“Eu”>“Minhas Compras”> 订单 > 物流状态）查看，也可直接把 `BR` 单号粘贴到 Peek 或 `spx.com.br` 查询。

### `Pedido em rota de entrega para seu endereço` 是什么意思？

表示包裹当天已装车派送至你的地址，预计数小时内送达。若订单带有签收口令，请提前准备好交给派送员。

### 巴西 SPX Express 派送需要多久？

Shopee 公布的参考时效为：巴西本土卖家订单自揽收起 **1–25 个工作日**，跨境国际订单一般为 **30–50 个自然日**，以订单页预计日期为准。

### 可以直接联系 SPX Express 客服吗？

Shopee 说明不提供直连快递客服，所有物流与派送问题均通过 Shopee App 内的 Chat Shopee 处理（巴西利亚时间工作日 `08:00–22:00`，周末 `08:00–20:00`）。

:::sources
- [SPX Express 巴西官网](https://spx.com.br/br) – Shopee 巴西自营物流、枢纽与分拨中心、查件框及 *SPX Express Brasil Ltda.*
- [SPX Express 关于我们](https://spx.com.br/br/help-center/about-us.html) – Shopee App 内查件说明
- [SPX Express 自提点地图](https://spx.com.br/service-point) – 查找 Agência Shopee 网点
- [Shopee 帮助中心：什么是 SPX Express](https://help.shopee.com.br/portal/4/article/197188) – Shopee 巴西官方自营物流说明
- [Shopee 帮助中心：什么是 Shopee Xpress（SPX）](https://help.shopee.com.br/portal/4/article/76163) – 名称、揽收、分拨中心与合作商
- [Shopee 帮助中心：物流合作商与三次派送](https://help.shopee.com.br/portal/4/article/76264) – 配送方式、不直连快递及 3 次尝试
- [Shopee 帮助中心：如何查看订单状态](https://help.shopee.com.br/portal/4/article/76323) – Correios 单号
- [Shopee 帮助中心：Shopee 的配送方式](https://help.shopee.com.br/portal/4/article/76335) – 运单号自动同步
- [Shopee 帮助中心：查找预计送达日](https://help.shopee.com.br/portal/4/article/81887) – 预计送达日与 Status de Envio
- [Shopee 帮助中心：查找订单编号](https://help.shopee.com.br/portal/4/article/76194) – 订单编号
- [Shopee 帮助中心：卖家自派物流](https://help.shopee.com.br/portal/4/article/132039) – 卖家派送并更新状态
- [Shopee 帮助中心：跨境包裹到巴西后如何派送](https://help.shopee.com.br/portal/4/article/76363) – 跨境订单由 Correios 派送
- [Shopee 帮助中心：能否预约派送日期和时间](https://help.shopee.com.br/portal/4/article/76333) – 不可预约、派送尝试与自动退款
- [Shopee 帮助中心：下单后能否修改地址](https://help.shopee.com.br/portal/4/article/76279) – 改址
- [Shopee 帮助中心：预计送达时效](https://help.shopee.com.br/portal/4/article/76349) – 国内 1–25 个工作日与国际 30–50 个自然日
- [Shopee 帮助中心：签收口令（palavra-chave）](https://help.shopee.com.br/portal/4/article/127204) – 签收口令、3 次尝试与自动退款
- [Shopee 帮助中心：不在家时如何告知口令](https://help.shopee.com.br/portal/4/article/127206) – 交给信任的人
- [Shopee 帮助中心：在 Agência Shopee 取件](https://help.shopee.com.br/portal/4/article/164642) – Retire Fácil、到件通知、PIN 与取件期限
- [Shopee 帮助中心：Agência Shopee 自提规则](https://help.shopee.com.br/portal/4/article/164639) – PIN 与单号、7 个工作日保管期
- [Shopee 帮助中心：轨迹无更新说明](https://help.shopee.com.br/portal/4/article/223973) – 跨州转运静默与超期 5 天参考
- [Shopee 帮助中心：订单停滞或改道](https://help.shopee.com.br/portal/4/article/159363) – 路线变化
- [Shopee 帮助中心：运单号查不到](https://help.shopee.com.br/portal/4/article/76369) – 原因与联系卖家
- [Shopee 帮助中心：未收到包裹退款](https://help.shopee.com.br/portal/4/article/76321) – 退款步骤与卖家 2 天回复期
- [Shopee 帮助中心：已签收未收到与核查](https://help.shopee.com.br/portal/4/article/76207) – 周边排查与 acareação 核查
- [Shopee 帮助中心：申请退货/退款](https://help.shopee.com.br/portal/4/article/76204) – 签收后 7 天内
- [Shopee 帮助中心：联系 Shopee 客服](https://help.shopee.com.br/portal/4/article/78354) – Chat Shopee 路径与时间
- [Shopee 帮助中心：客服工作时间与工单](https://help.shopee.com.br/portal/4/article/141507) – Chat Shopee 时段与在线表单
- [Shopee 帮助中心：防诈骗识别](https://help.shopee.com.br/portal/4/article/76330) – 官方渠道与 App 内结清运费
- [Shopee 帮助中心：海关扣留文件要求](https://help.shopee.com.br/portal/4/article/153683) – 巴西邮政/安骏清关与 RCV 表单
- [Shopee 帮助中心：无法完成跨境购买](https://help.shopee.com.br/portal/4/article/141561) – CPF 状态异常
- [Shopee 帮助中心：跨境订单退货、退款或取消](https://help.shopee.com.br/portal/4/article/134867) – 退款步骤相同、进口税不退
- [Receita Federal: 进口税率与 Remessa Conforme](https://www.gov.br/receitafederal/pt-br/assuntos/aduana-e-comercio-exterior/manuais/remessas-postal-e-expressa/topicos/tributacao) – 50 美元分档关税、ICMS 与防诈骗提示
- [Receita Federal: 何时缴税](https://www.gov.br/receitafederal/pt-br/assuntos/aduana-e-comercio-exterior/manuais/remessas-postal-e-expressa/preciso-pagar-impostos-nas-compras-internacionais/em-que-momento-pagarei-o-imposto) – 认证平台结账时缴税
- [Receita Federal: PRC 认证平台清单](https://www.gov.br/receitafederal/pt-br/assuntos/aduana-e-comercio-exterior/manuais/remessas-postal-e-expressa/empresas-certificadas-no-programa-remessa-conforme-prc) – Shopee 认证状态
- [Consumidor.gov.br](https://www.consumidor.gov.br/pages/principal/) – 10 天回复承诺与 `gov.br` 账号等级
- [Consumidor.gov.br: 参与企业](https://www.consumidor.gov.br/pages/principal/empresas-participantes) – Shopee Brasil
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – SPX 巴西状态映射、单号形态、链接规则与 Peek 展示行为
:::
