---
title: Correios 巴西邮政物流查询：单号、状态与清关指南
description: 查询巴西邮政 Correios 包裹：BR 国际单号格式、葡语状态中文对照、CPF 与 Minhas Importações 缴税流程以及客服电话。
slug: correios-brazil-tracking
published: 2026-11-24
updated: 2026-11-24
---

Correios（全称 Empresa Brasileira de Correios e Telégrafos）是巴西国家邮政。从巴西寄往海外的包裹全程沿用同一个 Correios 单号，由目的国邮政完成末端派送；在海外购买、寄往巴西的包裹则保留发件国邮政单号，入境后由 Correios 派送。你可以在 [Correios 官方查询页面](https://rastreamento.correios.com.br/app/index.php)输入最多 20 个单号或 CPF/CNPJ 号码（需输入图片验证码）查询，或直接把单号粘贴到上方输入框中。

## Correios 运单号格式

Correios 的运单号为 13 位字符：前 2 位字母代表业务类型，中间 9 位数字，最后 2 位字母为发件国代码（巴西为 `BR`），遵循万国邮政联盟（UPU）标准。以下示例数字均为虚构：

| 单号 | 说明 |
| --- | --- |
| `EA482917367BR` | `E`：EMS 特快专递服务。 |
| `CA482917367BR` | `C`：普通邮政包裹（parcel post）。 |
| `LA482917367BR` | `L`：可追踪函件。 |
| `RA482917367BR` | `R`：挂号函件。 |
| `NN482917367BR` | `N`、`B`、`D`、`P` 或 `Z`（以及 `AV` 至 `AZ`、`GA`、`GD`）：UPU 保留用于境内或双边业务的字母前缀。 |
| `RA482917367CN` | [中国邮政（China Post）](carrier:china-post)单号，非 Correios 签发；寄往巴西时由 Correios 沿用该单号派送。 |

第 9 位数字用于校验前 8 位数字，因此只要输错一位通常就无法通过校验。Correios 指出，部分从海外寄来的包裹因使用了特定 Correios 服务也会以 `BR` 结尾。没有单号？Correios 建议向商家索取。官网查询系统会将包裹历史在线保留 180 天。

## Correios 物流状态中文说明

Correios 的扫描状态使用葡萄牙语，并标注每条记录所在的城市与州（UF）。以下各状态的含义为译文：

| Correios 原文 | 对你的含义 |
| --- | --- |
| `Etiqueta emitida` | 已生成面单，Correios 尚未收到包裹。 |
| `Objeto postado` | 已交寄：Correios 已揽收。 |
| `Objeto encaminhado para fiscalização aduaneira de exportação` | 寄往海外的包裹在离开巴西前已送交巴西出口海关查验（见下文），查验期间包裹不在 Correios 手中。 |
| `Objeto em transferência - por favor aguarde` | 正在 Correios 各站点之间转运（“转运中，请稍候”）。 |
| `Objeto recebido pelos Correios do Brasil` | 境外包裹已抵达巴西并由 Correios 接收。 |
| `Informações enviadas para análise da autoridade aduaneira/órgãos anuentes` | 申报信息已发送至巴西海关及相关监管部门审核。 |
| `Aguardando pagamento` | 派送前需缴纳税费或手续费：见下文 Minhas Importações（Peek 显示为“清关中”）。 |
| `Análise concluída - importação autorizada` | 审核完成，允许进口放行。 |
| `Saída do Centro Internacional` | 已离开 Correios 国际处理中心，进入巴西境内派送。 |
| `Objeto saiu para entrega ao destinatário` | 正在派送前往收件人地址。 |
| `Objeto não entregue - carteiro não atendido` | 邮递员上门无人应答。 |
| `Objeto aguardando retirada no endereço indicado` | 包裹正在 Correios 指定的地址（例如其某个网点）等待你自提（Peek 显示为“待取件”）。 |
| `Inconsistências no endereçamento do objeto` | 收件地址信息存在异常。 |
| `Objeto entregue ao destinatário` | 已送达收件人。 |

部分状态代表异常：`Importação não autorizada` 表示海关未批准进口；`Prazo de regularização encerrado` 表示补办手续或缴税期限已过；`Devolução determinada pela autoridade competente` 表示主管部门下令退回寄件人。`Objeto saiu para entrega ao remetente` 表示正在退回派送给寄件人，而非派送给你。

Correios 进口说明页面还提到 `Em fiscalização aduaneira`（海关查验中）；Correios 表示，出现该状态时包裹并不在其手中。

## 从巴西寄往欧洲及其他地区的包裹

Correios 负责办理巴西出口清关并将包裹交予目的国邮政，由对方沿用同一个单号完成派送（UPU 规定每件邮件仅编一个国际单号）。目的国邮政掌握末端物流的一手信息，例如 [Royal Mail](carrier:royal-mail)、[USPS](carrier:usps)、Canada Post、An Post、[Australia Post](carrier:australia-post)、[La Poste](carrier:la-poste)、CTT、Correos、[Poste Italiane](carrier:poste-italiane) 或 [Deutsche Post](carrier:dhl)。

如果目的国海关扣留了包裹，Correios 表示须由你直接与当地海关对接：无论是 Correios 还是目的国邮政都无法介入。在欧盟，自 2026 年 7 月 1 日起，从欧盟外网购且货值不超过 150 欧元的包裹按商品税则分类每项征收 3 欧元关税；自 2026 年 11 月 1 日起，无论货值多少，跨境网购商品还适用每项 2 欧元的欧盟处理费。具体税费与收取方式见[清关中的包裹指南](guide:customs)（英文）。

## 寄往巴西的包裹：CPF、税费与 Minhas Importações

**CPF 税号。** Correios 强调：“每一件从海外寄往巴西的包裹都属于进口，每一件进口包裹都需要 CPF。”CPF 必须状态正常，且收件人姓名须与巴西联邦税务局（Receita Federal）登记一致。

**Minhas Importações 平台。** Correios 通过 [Minhas Importações](https://portalimportador.correios.com.br) 网站和 Correios 应用在线办理清关（需先一次性注册 Meu Correios 账号）。你可以在里面将包裹绑定至你的 CPF、上传海关要求的文件并完成缴税。

**税费标准**（据巴西联邦税务局 Receita Federal 公布）：

- **进口关税（Import duty）：** 海关完税价格（货值、运费与保费合计）的 60%。适用于所有不符合下方优惠规则的包裹，即未加入 Remessa Conforme 的商家的订单，或寄给企业的包裹。
- **在[经 Remessa Conforme（PRC）认证](https://www.gov.br/receitafederal/pt-br/assuntos/aduana-e-comercio-exterior/manuais/remessas-postal-e-expressa/empresas-certificadas-no-programa-remessa-conforme-prc)的网站购买并寄给个人：** 50 美元及以下免关税（0%）；超过 50 美元按 60% 征收并减免 30 美元。该税率适用于自 2026 年 5 月 12 日起登记的申报（此前为 20% 税率并减免 20 美元）。
- **州流转税（ICMS）：** 视所在州而定为 17% 至 20%，无论是否走 PRC 均需缴纳。
- **Correios 邮政清关服务费（Despacho Postal）：** 可能会额外收取，不属于政府税款。

若货值超过 3,000 美元，或因其他原因海关不予放行，Correios 说明必须聘请清关代理（despachante aduaneiro）。

**何时缴税。** 在 PRC 认证平台下单时，税费会在结账时一并收取；如果结账未扣税，入境时将征税，且不再享受优惠减免。其他情况下，须在税单生成后 20 个自然日内通过 Minhas Importações 或应用以 boleto 或 Pix 支付，逾期包裹将退回境外。若认为税额有误，请在生成 boleto 或 Pix 前在线申请税费复核（“Revisão de Tributos”）。补充材料仅能提交一次，且 Correios 无法预估 Receita Federal 的审核耗时。

**派送与自提。** 若 Correios 无法妥投，包裹会转存至最近邮局并向你发通知。普通海外包裹在上门尝试后保管 20 个自然日，EMS 和 Correios Packet 保管 7 天。他人代领需携带你签字的授权书、代领人身份证件原件以及你的身份证件（复印件亦可）。

**防范诈骗。** Correios 绝不会通过邮件、短信或 WhatsApp 发送付款链接或索要个人及银行信息，Receita Federal 也不会发送 boleto、致电或发邮件催税。请直接手动访问 correios.com.br，不要通过搜索引擎点击入口；另见[快递诈骗短信指南](guide:delivery-scam-texts)（英文）。

## Correios 包裹无更新、丢失或退回

**一直查不到单号。** Correios 建议先向寄件人核实单号（含字母）是否正确以及包裹是否已实际交寄。如已交寄，由寄件人发起投诉。

**卡在海关。** Correios 不对海关查验时效作承诺。请留意 Minhas Importações 中是否要求补充材料或缴税；更多排查见[物流为什么停止更新](guide:tracking-not-updating)（英文）。

**已缴税但未派送。** 超过物流页面显示的预计送达日期后，可在 Correios 官网提交申诉（manifestação）。

**丢失或破损。** 需使用 Meu Correios 账号通过 Fale Conosco 提交投诉，仅限寄件人或生成税费 boleto/Pix 的一方提交。跨境邮件的申诉期自交寄之日起算：EMS 及 Correios 特快服务为 120 天，其余服务为 6 个月；Correios Packet 进口件为签收或退回后 90 天内（破损为 30 天内）。Correios 目标在 5 个工作日内答复，且赔偿款仅打入巴西本地银行账户。根据巴西法律，包裹送达前所有权归寄件人，因此身在巴西境外时请联系寄件人或商家索赔；另见[包裹丢失指南](guide:lost-parcel)（英文）。

**已退回。** `Objeto entregue ao remetente` 表示包裹已退回寄件人手中，请联系商家退款或补发；见[包裹退回寄件人指南](guide:returned-to-sender)（英文）。

## Correios 客服电话与联系方式

Correios 公布的葡萄牙语服务渠道如下（均为巴西利亚时间）：

- **客服电话（SAC）：** 各州首府、都市圈及其他地区手机拨打 4003 8210；其他地区固话拨打 0800 881 8210。周一至周五 8:00 至 20:00，周六 8:00 至 14:00（法定节假日除外）。
- **WhatsApp：** (11) 4003-8210，同时支持物流查询。
- **在线客服：** correios.com.br 上的虚拟助手 Carol。
- **投诉入口：** [Fale Conosco](https://faleconosco.correios.com.br/inicio) 门户。

各分拣中心和派送网点不接听外部电话。

## 使用 Peek 查询 Correios 包裹

对于 2 位字母、9 位数字加 `BR` 且校验位正确的单号，[Peek](/) 会直接识别为 Correios。校验位有误时 Peek 不会认出任何快递公司；如果点击“更换”手动选择 Correios，Peek 会提示其“不使用这种格式的单号”，请重新核对单号。带有 `objetos=` 参数的 Correios 查询链接同样可直接粘贴。

Peek 会抓取 Correios 的物流历史，显示最新状态、每条扫描的城镇与州名，以及包裹在途且未逾期时的预计送达日期。巴西境内的扫描按 Correios 标注的时区解析，发件国邮政的扫描按发件国时区解析，统一换算为你设备的本地时区显示；状态原文保留葡萄牙语。

由于 Correios 不会向 Peek 透露包裹的目的国，在巴西以外地区查询时，Peek 还会用同一单号向你所在国家的邮政（如 USPS、Royal Mail、Canada Post、An Post 或 Australia Post）查询，一旦对方出现不早于 Correios 最新记录的扫描即切换追踪，同时保留 Correios 的历史记录。以其他国家邮政单号寄往巴西的包裹会先在发件国邮政下追踪；待对方标出目的地为巴西（或未标目的地而你位于巴西）时，Peek 会同步向 Correios 查询。

当你打开包裹页面查看，或在派送当天开启通知时，只要 Correios 官方查询正常响应，Peek 最高每 10 分钟检查一次，而在 Correios 扫描包裹进入派送中的当天早上 6 点起（按最新扫描所在时区）最高每 2 分钟检查一次（需用浏览器打开 Correios 官网时为每 5 分钟）。其余时间，以及连续两天无新动态时（包裹仍处于“已生成面单”或“已揽收”状态时为 12 小时），Peek 每小时检查一次；停留在“已生成面单”连续七天无更新后改为每 6 小时检查一次。你可以按需选择“每次扫描”、“重要环节”或“仅派送当天”提醒。Peek 无法代你联系 Correios、缴纳税费或提交理赔。

## Correios 巴西邮政查询常见问题

### “Objeto em transferência - por favor aguarde”是什么意思？

意为“包裹正在转运中，请稍候”：包裹正从巴西的一个 Correios 网点运往另一个网点，目前还无需你做任何操作。

### 为什么我的 Correios 包裹显示“Aguardando pagamento”？

表示需要缴纳进口税费或 Correios 清关手续费。请在 20 个自然日内通过 Minhas Importações 或 Correios 应用以 boleto 或 Pix 完成支付，否则包裹将被退回境外。

### Correios 派送需要多久？

具体取决于所选服务以及巴西的收件邮编（CEP）；Correios 物流页面会给出预计送达日期。对于境外入境包裹，境内时效自海关放行并出现 `Saída do Centro Internacional` 起计算。

### 在巴西以外可以查询 Correios 包裹吗？

可以，全程使用同一个单号：发运阶段在 Correios 查询，抵达目的国后在目的国邮政官网继续查询即可。

:::sources
- [Correios: Rastreamento](https://rastreamento.correios.com.br/app/index.php) – 官方查询页、支持最多 20 个单号或 CPF/CNPJ 及图片验证码
- [Correios: Recebimento de Encomendas Internacionais](https://www.correios.com.br/receber/encomenda/internacional) – CPF 要求、Minhas Importações、缴税、复核、清关代理与海关状态
- [Correios: Como importar pelos Correios (PDF)](https://www.correios.com.br/receber/encomenda/arquivos/internacional/como-importar-pelos-correios-2024-1.pdf) – 单号格式、Aguardando pagamento 与派送时效起算
- [Correios: Prazo de Guarda Objetos Internacionais](https://www.correios.com.br/receber/prazo-de-guarda-objetos-internacionais) – 20 天与 7 天自提保管期限
- [Correios: Perguntas frequentes](https://www.correios.com.br/acesso-a-informacao/perguntas-frequentes) – 遗失单号、180 天记录保留与邮局自提规则
- [Correios: Envio Internacional de Encomendas](https://www.correios.com.br/enviar/encomendas/internacional/internacional) – 巴西出口清关与海外目的国海关说明
- [Correios: Fale Conosco, Termo de uso](https://faleconosco.correios.com.br/inicio) – 投诉主体、申诉期限与巴西本地银行赔付规定
- [Correios: Central de Atendimento](https://www.correios.com.br/falecomoscorreios/central-de-atendimento) – 客服电话、服务时间、WhatsApp 与虚拟助手 Carol
- [Correios: Segurança](https://www.correios.com.br/falecomoscorreios/seguranca/seguranca) – 防诈骗声明（不发付款链接）
- [Receita Federal: Tributação](https://www.gov.br/receitafederal/pt-br/assuntos/aduana-e-comercio-exterior/manuais/remessas-postal-e-expressa/topicos/tributacao) – 关税税率、ICMS、服务费与防骗提示
- [Receita Federal: Em que momento vou pagar os impostos?](https://www.gov.br/receitafederal/pt-br/assuntos/aduana-e-comercio-exterior/manuais/remessas-postal-e-expressa/preciso-pagar-impostos-nas-compras-internacionais/em-que-momento-pagarei-o-imposto) – PRC 结账时缴税规则
- [Receita Federal: Como realizar pagamento pelo Minhas Importações dos Correios](https://www.gov.br/receitafederal/pt-br/assuntos/aduana-e-comercio-exterior/manuais/remessas-postal-e-expressa/preciso-pagar-impostos-nas-compras-internacionais/como-realizar-o-pagamento-pelo-minhas-importacoes-dos-correios) – 勿通过搜索引擎进入提示
- [Receita Federal: Empresas certificadas no Programa Remessa Conforme](https://www.gov.br/receitafederal/pt-br/assuntos/aduana-e-comercio-exterior/manuais/remessas-postal-e-expressa/empresas-certificadas-no-programa-remessa-conforme-prc) – PRC 认证商家与 Correios 官方全称
- [UPU: S10 standard, Identification of postal items](https://www.upu.int/UPU/media/upu/files/postalSolutions/programmesAndServices/standards/S10-12.pdf) – 业务字母代码与校验位标准
- [European Commission: EU Customs Reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 3 欧元关税与 2 欧元欧盟处理费
- [European Commission: Guidance on the €3 customs duty and Union handling fee](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 跨境远程销售按项计费说明
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – 状态文案（出口海关查验、在指定地址等待领取、海关待缴费）、Peek 显示的内容
:::
