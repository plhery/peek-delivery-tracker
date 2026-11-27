---
title: Correos Express 快递查询：单号、西班牙语状态与客服
description: 查询西班牙和葡萄牙 Correos Express 包裹：16 位或 23 位运单号格式、西班牙语物流状态中文说明、派送未成功的处理及客服联系方式。
slug: correos-express-kuaidi-chaxun
published: 2026-11-24
updated: 2026-11-24
---

Correos Express 是西班牙邮政集团（Correos）旗下的快递公司，负责西班牙和葡萄牙全境的速递包裹派送；它不同于提供普通邮政服务的[西班牙邮政（Correos）](carrier:correos-spain)。你可以在其西班牙语[网站查询页面](https://s.correosexpress.com/)输入单号，或直接粘贴到上方输入框查询。包裹由寄件人（通常是商家）向 Correos Express 登记，如果没有单号请联系商家索取。

## Correos 还是 Correos Express：你拿的是哪种单号？

Correos（西班牙邮政）是 Correos Express 的母公司，但两家使用各自独立的单号体系与查件系统。看单号构成就能分清包裹在谁手里：

- **纯数字，共 16 位或 23 位：** 属于 Correos Express，不过也有其他快递公司共用这两种位数（见下表）。可直接粘贴到上方输入框查询。
- **字母加数字：** 属于西班牙邮政 Correos。其单号为以字母开头和结尾的 23 位或 16 位单号、`PR` 加 15 位数字再加 `C`，或以 `ES` 结尾的 13 位单号。Correos 提供[英文查询页面](https://www.correos.es/es/en/tools/tracker/items)，我们的[西班牙邮政（Correos）页面](carrier:correos-spain)也详细解释了其物流状态。

## Correos Express 运单号格式

Correos Express 网站并未公布固定编号规则，查件框只要求输入 *número de envío*（运单号）。Peek 的快递目录收录了两种全数字格式，末尾均带校验位（以下均为虚构示例）：

| 单号示例 | 所属快递公司 |
| --- | --- |
| `6314 8291 0573 6490` | 16 位数字：可能是 Correos Express，也可能是 DHL eCommerce、Canada Post、TNT 或 Evri UK。 |
| `32348291057364918273641` | 23 位数字：属于 Correos Express；但若第 15 至 17 位为 `000`，Peek 目录会将其归为[澳大利亚邮政（Australia Post）](carrier:australia-post)。 |
| `RR482910576ES` | 符合[国际邮政格式](guide:tracking-number-formats)（英文）的字母加数字单号：属于西班牙邮政 Correos，而非 Correos Express。 |
| `4829/10573649` | 带斜杠的单号：属于另一家快递公司 NACEX。 |

Correos Express 没有说明单号具体出现在哪里，也没有公布官方短信发件人名称，因此请查看商家的发货邮件或订单详情页（更多查找方法见[如何查找运单号](guide:find-tracking-number)（英文））。

如果网站提示 *no se ha encontrado ningún envío*（未找到运单），Correos Express 建议先核对这是否确为 Correos Express 单号，并等待几小时让新登记的包裹同步上网；若仍查不到，请联系寄件人。

## Correos Express 西班牙语物流状态中文说明

Correos Express 不提供英文或中文查件界面。西班牙网站以西班牙语显示以下状态；葡萄牙网站菜单为葡萄牙语，但其状态措辞未经核实。

| 官方状态 | 中文含义 |
| --- | --- |
| `INFORMADO` | 寄件人已登记包裹信息，但 Correos Express 尚未揽收实物。 |
| `ADMITIDO` | 已揽收并开始发运。 |
| `EN RUTA A LOCALIDAD DE DESTINO` | 正运往收件地分拨网点，此时通常还来得及申请改派。 |
| `EN DESTINO` | “已到达目的地”。 |
| `EN REPARTO` | 正在派送中，此时已来不及修改。 |
| `Su envío no ha podido ser entregado` | 派送未能成功（本次尝试派送失败）。 |
| `NUEVO REPARTO` | 已安排再次派送。 |
| `ESTACIONADO` | 未能送达，目前暂存在 Correos Express 网点或仓库中。 |
| `ENTREGADO EN PUNTO DE CONVENIENCIA` | 已到达你的自提点，但还不能领取。 |
| `DISPONIBLE EN PUNTO DE CONVENIENCIA` | 已在自提点备妥，可凭 PIN 取件码领取。 |
| `ENTREGADO` | 已由本人或授权代收人签收。 |
| `DEVUELTO` | 已退回寄件人。 |

`ENTREGADO EN PUNTO DE CONVENIENCIA` 最容易让人误解：虽然 *entregado* 字面意思是“已送达”，但这里指交给了自提点而不是你本人。请等状态变为 `DISPONIBLE EN PUNTO DE CONVENIENCIA`，并备好 PIN 码再去取件。

Correos Express 仅在葡萄牙网站常见问题里解释了 `ESTACIONADO`，而未官方注释 `EN DESTINO`、`Su envío no ha podido ser entregado`、`NUEVO REPARTO`、`ENTREGADO EN PUNTO DE CONVENIENCIA`、`DEVUELTO`、`ADMITIDO EN OFICINA DE CORREOS`（已在 Correos 邮局交寄）或 `EN ALMACÉN`（在仓库中）。这些状态的含义是字面直译或 Peek 的解读；若想了解各物流阶段定义，请参见[物流状态说明](guide:tracking-statuses)（英文）。

## 更改派送日期、地址或改投自提点

**西班牙境内：** 在包裹进入派送前，若寄件人与所选服务允许修改，查询页面可能会显示 **Gestionar entrega**（管理派送）按钮：你可以改投至 Punto Correos 自提点、授权他人（如邻居或门房）代收、修改收件地址或另选派送日期。如果错过派送，Correos Express 会在服务时限内再次尝试派送。如需重新预约，可在其网站的 **Solicita nueva entrega**（申请重新派送）表单中选择新日期与上午/下午时段，或改送至 Correos 邮局或自提点。地址有误需联系寄件人更正。若是货到付款（*contra reembolso*），快递员上门收款，Correos Express 建议提前备好准确金额的现金。

**葡萄牙境内：** 首次派送失败后会自动安排第二次派送，并发送通知供你改选派送日期、同一行政区内的其他地址或可用的 Ponto Correos 自提点。一旦包裹已进入派送环节，需等收到该通知后再改地址。按运输条款，派送失败后满 3 个工作日无人认领的包裹将退回寄件人。

**在 Punto Correos（葡萄牙称 Ponto Correos）自提：** 取件需出示通过短信和/或邮件收到的 PIN 码。找不到的话请检查垃圾邮件箱，或在查询页面点击 **Visualizar PIN** 查看。西班牙自提点保管 7 个自然日，逾期退回寄件人；葡萄牙自提点最长保管 7 天，CitiBox 或 CityPaq 快递柜保管 3 天，且持有 PIN 码的任何人都可代取。

## 包裹延误、丢失、破损或退回怎么办？

寄件人（通常是商家）才是运输合同方，因此遇到大多数异常时，Correos Express 会要求收件人先联系商家处理。

- **物流不更新：** 分拨中心之间转运时，轨迹可能延迟数小时才更新。若连续 24 至 48 小时无任何变动，Correos Express 建议联系寄件人。另见[物流停止更新怎么办](guide:tracking-not-updating)（英文）。
- **人在家却显示派送失败：** 先查看轨迹详情。Correos Express 表示无法进入或信息不全可能导致无法派送，确认后可按上文步骤重新预约。
- **显示已签收但没收到：** 西班牙收件人先在 Correos Express 网站核对签收详情，再联系寄件人；葡萄牙收件人可发邮件至 destinatarioscep@correosexpress.com。另见[显示已签收但未收到](guide:delivered-not-received)（英文）。
- **包裹破损：** 西班牙境内运输条款要求签收时书面注明异议，隐蔽性破损或短少需在 7 个自然日内提出；葡萄牙收件人应拍摄外包装与内物照片，并在 7 天内联系寄件人。
- **恶劣天气受限：** Correos Express 可能因天气限制部分地区收派，当前受限地区列在[特别情况公告页](https://www.correosexpress.es/es/atencion-al-cliente/situaciones-especiales)。
- **包裹已退回：** 状态显示 `DEVUELTO` 后，请联系寄件人。

## Correos Express 客服电话与联系渠道

Correos Express 网站列出的联系渠道（均不提供中文或英文服务）：

- **西班牙：** 900 122 333（未公布服务时间；西班牙通信监管机构 CNMC 表示拨打 900 号段手机与固话均免费）。其网站查件页还列有 913 277 020。也可填写[客户服务表单](https://www.correosexpress.es/es/atencion-al-cliente/contacto/atencion-al-cliente)（需填运单号和邮编），或在 X 上联系 [@Cex_responde](https://x.com/Cex_responde)。
- **葡萄牙：** 229 762 810（按固话资费计费，周一至周五 08:30 至 19:00）。收件人也可发送邮件至 destinatarioscep@correosexpress.com。

> Correos Express 声明绝不会索要银行账户信息或网站登录密码，也未开设 807 付费高价热线；如有疑问请拨打 900 122 333 核实。西班牙网络安全机构 INCIBE 曾于 2022 年发布预警，提醒防范冒充“CORREOS EXPRESS”的诈骗短信，这类短信以派送失败为由要求补缴小额关税。切勿打开其中的链接。

## 用 Peek 查询和跟踪 Correos Express 包裹

16 位数字单号由多家快递共用，因此 [Peek](/) 会询问几家可能匹配的快递公司；23 位数字单号则直接指向 Correos Express（第 15 至 17 位为 `000` 的澳大利亚邮政单号除外）。如果 Peek 没有列出 Correos Express，请核对数字是否输错，并直接粘贴纯单号，不要粘贴网页链接。

Peek 最高每 10 分钟检查一次；在 Correos Express 扫描包裹外出派送的当天，从当地时间早上 6 点起，只要 Correos Express 自己的查询系统正常响应，最高每 2 分钟检查一次（通过物流查询服务时，每 15 分钟一次）。若连续两天无新动态（或在“已生成面单”、“已揽收”状态下超过 12 小时无新动态），Peek 会改为每小时检查一次。你可以按需开启“每次扫描”、“重要环节”或“仅派送当天”通知。Peek 会展示物流状态、预计送达时间（仅在途期间显示）和带扫描地点地图的完整历史，但不会显示签收凭证、联系方式或派送备注，也无法代你联系 Correos Express。

## Correos Express 查询常见问题

### Correos Express 是什么快递？

它是西班牙 Correos 集团旗下的加急快递公司，母公司 Correos 则承担普遍邮政服务。Correos Express 表示其网络可在 24 小时内送达伊比利亚半岛任意地点，从西班牙发往葡萄牙本土通常为 24 至 48 小时。

### Correos Express 能寄到英国吗？

可以。通过西班牙发出的 Internacional Express 服务可通达 180 多个国家和地区，提供最多两次派送尝试，其网站全程可查直至签收。Correos Express 提醒，自英国 2020 年底退出欧盟单一市场与关税同盟后，发往英格兰、威尔士、苏格兰和北爱尔兰的包裹面临额外要求和费用；发往非欧盟国家时，Internacional Express 会负责清关手续。

### Correos Express 几点派送？

取决于寄件人所选服务：Paq 10 会在 10:00 前送达，Paq 14 会在 14:00 前送达。其网站显示的预计送达时间根据该邮编历史派送记录推算，可能随路线情况变动。

### 可以用英文或中文联系 Correos Express 吗？

不可以。Correos Express 未提供英文或中文客服渠道，其查询页面和帮助中心目前仅支持西班牙语与葡萄牙语。

:::sources
- [Correos Express: Seguimiento de envíos y gestión de entrega](https://www.correosexpress.es/es/atencion-al-cliente/seguimiento-envio) – 状态说明、轨迹更新、查无单号与派送失败处理
- [Correos Express: Gestiona la entrega de tu envío](https://www.correosexpress.es/es/atencion-al-cliente/seguimiento-envio/gestiona-tu-entrega) – 更改派送选项与二次派送
- [Correos Express: Recogida de envíos en Punto Correos](https://www.correosexpress.es/es/atencion-al-cliente/seguimiento-envio/recogida-en-punto-correos) – PIN 码与 7 天保管期
- [Correos Express: Otras consultas sobre la entrega](https://www.correosexpress.es/es/atencion-al-cliente/seguimiento-envio/otras-consultas-entrega) – 官方渠道与货到付款
- [Correos Express: Sigue tu envío](https://s.correosexpress.com/) – 西班牙语查件页与电话 913 277 020
- [Correos Express: Solicita nueva entrega](https://s.correosexpress.com/SeguimientoSinCP/saveNewDelivery) – 重新预约派送表单
- [Correos Express: Formulario de Atención al Cliente](https://www.correosexpress.es/es/atencion-al-cliente/contacto/atencion-al-cliente) – 客服表单
- [Correos Express: Envío rápido y seguro a España y Portugal](https://www.correosexpress.es/) – 时效服务与二次派送
- [Correos Express: Quiénes somos](https://www.correosexpress.es/es/sobre-correos-express/quienes-somos) – 公司定位、与 Correos 区别及 24 小时时效
- [Correos Express: Envíos a España y Portugal](https://www.correosexpress.es/es/atencion-al-cliente/envios-en-espana-y-portugal) – 西葡 24 至 48 小时时效
- [Correos Express: Envíos internacionales](https://www.correosexpress.es/es/atencion-al-cliente/envios-internacionales1) – 发往英国的通关要求与费用
- [Correos Express: Internacional Express](https://www.correosexpress.es/es/particulares/enviar/internacionales/internacional-express) – 覆盖 180 国及全程跟踪
- [Correos Express: Internacional Express para empresas](https://www.correosexpress.es/es/empresas/productos/internacional-express) – 两次派送与非欧盟清关
- [Correos: Correos delivery tracker](https://www.correos.es/es/en/tools/tracker/items) – Correos 英文查件页
- [Correos Express: Términos y condiciones del transporte](https://www.correosexpress.es/es/atencion-al-cliente/tyc-transporte) – 签收保留意见
- [Correos Express: Restricciones y avisos](https://www.correosexpress.es/es/atencion-al-cliente/situaciones-especiales) – 天气受限公告
- [Correos Express: Alerta de seguridad](https://www.correosexpress.es/es/legales/alerta-de-seguridad) – 防诈骗提醒
- [Correos Express Portugal: Siga seu envio](https://s.correosexpress.com/SeguimientoSinCP/search-pt) – 葡萄牙语查件页
- [Correos Express Portugal: Perguntas frequentes](https://www.correosexpress.pt/pt/apoio-ao-cliente/perguntas-frequentes) – 葡萄牙派送规则、ESTACIONADO 与破损处理
- [Correos Express Portugal: Apoio ao Cliente](https://www.correosexpress.pt/pt/apoio-ao-cliente/contacto/apoio-cliente) – 葡萄牙电话、服务时间与邮箱
- [Correos Express Portugal: Termos e Condições de Transporte](https://www.correosexpress.pt/pt/apoio-ao-cliente/tec-transporte) – 3 个工作日退回与 PIN 码规则
- [Correos Express Portugal: Paq Ponto](https://www.correosexpress.pt/pt/empresa/enviar/peninsulares/paq-ponto) – 葡萄牙快递柜保管期
- [CNMC: Números 800 y 900](https://blog.cnmc.es/2019/04/24/llamadas-a-numeros-800-y-900-gratis-para-el-consumidor-y-quien-paga/) – 900 免费号码说明
- [INCIBE: SMS fraudulentos](https://www.incibe.es/ciudadania/avisos/en-circulacion-sms-fraudulentos-que-suplantan-correos-y-otras-empresas-de) – 仿冒短信安全提醒
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Peek 读取的内容
:::
