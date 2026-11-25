---
title: DPD France 快递查询：单号、法语状态与 Pickup 自提点
description: 查询法国 DPD France 包裹：运单号格式与查找位置、法语物流状态中文解读、Predict 预约派送、Pickup 自提点与快递柜、索赔及客服电话。
slug: dpd-france-kuaidi-chaxun
published: 2026-11-24
updated: 2026-11-24
---

DPD France（2015 年前名为 Exapaq）是 Geopost 在法国的包裹派送网络，隶属于[法国邮政（La Poste）](carrier:la-poste)集团。你可以通过 trace.dpd.fr、DPD 发送的短信或邮件里的链接查询，或直接把运单号粘贴到上方输入框。运单号在商家的发货确认中，也会出现在 DPD France 自己发送的邮件或短信里。

## DPD France 运单号在哪里找？

DPD France 表示其包裹单号以 250、1 或 0 开头，长度为 12 至 15 位数字。如果完全找不到单号，DPD 说明只有寄件人掌握单号，请联系商家查询，或参考[如何查找运单号](guide:find-tracking-number)（英文）。

| 单号（虚构） | 说明 |
| --- | --- |
| `250482917364058` | 250 开头的 15 位数字：最常见的 DPD France 包裹单号。 |
| `2504829173640586` | 16 位数字：上述单号末尾加上一位校验位，即面单上印刷的形式。 |
| `10482917364058` | 1 开头的 14 位数字：另一种 DPD France 单号格式。 |
| `048291736405` | 0 开头的 12 位数字：较短的 DPD France 单号格式。 |

DPD 查件系统也接受投递通知单编号（*avis de passage*）或 Predict 短信码，且不需要输入邮编。真正的 DPD France 短信发件人名称显示为 “DPD France”。

14 位数字后跟一个字母的单号（如 `48291736405826B`）属于其他 DPD 分支网络或 [Chronopost](carrier:chronopost)，并非 DPD France。来自 [DPD UK](carrier:dpd-uk)、[DPD Germany](carrier:dpd-de) 及其他 DPD 子公司的跨境包裹到达法国后，有时会交由 Chronopost 派送；此时 DPD France 页面会提示 `Numéro de colis inconnu`（未知包裹单号），并引导你去 Chronopost 查询。

## DPD France 法语物流状态中文解读

以下为 DPD 的法语原文状态，也是 Peek 直接展示的文本。trace.dpd.fr 另提供英文版页面，其进度条显示 `Handed to DPD`、`In transit`、`At parcel delivery center`、`Out for delivery` 和 `Delivered`，个别步骤措辞可能有所不同。

| DPD 状态原文 | 中文含义 |
| --- | --- |
| `Colis en préparation chez l'expéditeur` | 面单已生成，但商家尚未把包裹交给 DPD。 |
| `Colis pris en charge par DPD France` | DPD France 已揽收包裹。 |
| `Colis en cours d'acheminement` | 包裹正在 DPD 分拣中心与各地网点之间转运。 |
| `Colis en agence DPD France` | 已到达负责派送的当地网点。 |
| `Colis en livraison` | 快递员今天正在派送途中。 |
| `Livraison reportée, prise de rendez-vous nécessaire` | 派送已推迟，需要重新预约派送时段。 |
| `Colis à disposition du destinataire au point relais Pickup` | 包裹已在 Pickup 自提点等待领取。 |
| `Colis à disposition à l'agence DPD` | 包裹已在 DPD 网点等待自提。 |
| `Colis refusé, en attente de vos instructions` | 包裹被拒收，DPD 正等待寄件人指示。 |
| `Colis livré` | 已签收（送达收件地址或已从自提点取走）。 |
| `Colis en retour vers l'adresse d'expédition, en attente de livraison chez l'expéditeur` | 正在退回寄件人地址。 |

DPD 表示，在包裹交接给 DPD 前，只有寄件人能解答相关问题。包裹停留在网点期间，`Colis en agence DPD France` 可能会重复出现。若看到 `Numéro de colis inconnu`，通常说明 DPD 尚未收到实物：等待几小时后再查，并核对单号是否有误。

## DPD 包裹未送达：Predict 预约、Pickup 自提点与快递柜

DPD France 的派送时间为法国时间周一至周五 8:00 至 18:00，部分邮编地区周六 8:00 至 15:00 也会派送。

**Predict 预约派送：** 若寄件人选用该服务，你会先收到短信和邮件告知预计派送日期，派送当天再收到一个 3 小时时段。在寄件人允许的前提下，你可以在收到通知当天的法国时间 23:00 前，通过消息里的链接或 myDPD 应用改选派送日期、修改地址、改投 Pickup 自提点或 DPD 网点，或选择邻居或安全存放处。

**派送失败：** 快递员会在信箱留下一张投递通知单（*avis de passage*），你可在线重新预约；使用 Predict 服务时，包裹也可能直接转存附近的 Pickup 自提点。DPD France 没有说明派送次数，其条款写明会在次日或你或寄件人指定的日期再次派送。

**Pickup 自提点：** 包裹保管 9 个自然日。取件务必携带身份证件：DPD 明确表示仅凭 Pickup Pass 二维码或运单号不能取件。委托他人代领需提供授权委托书、你的身份证件及代领人本人证件。

**Pickup 快递柜：** 多数全天候 24 小时开放，可存放 20 公斤以内的包裹，凭 DPD 发送的取件码或二维码开柜。保管期为 5 个自然日。

**DPD 网点自提：** DPD 常见问题写明保管 6 个工作日（条款写明 5 个工作日），取件需携带身份证件以及包裹单号或投递通知单号。逾期未领的包裹均退回寄件人。

## DPD 包裹延误、丢失或破损怎么处理？

对于延误或经客服确认丢失的包裹，DPD France 会要求你联系寄件人，因为运输合同由寄件人签订，且 DPD 的送达时效并不作强制保证。另见[物流停止更新怎么办](guide:tracking-not-updating)（英文）。

若轨迹显示 `Colis livré` 但实际没收到，或到自提点找不到包裹，可联系客服发起核查；另见[显示已签收但未收到](guide:delivered-not-received)（英文）。

若包裹破损或内物缺失，可当场拒收并写明原因（包裹会退回寄件人），或签收时写下具体、注明日期并签字的书面异议，随后向 DPD France 寄送带回执的挂号信并附 4 张照片。DPD 常见问题给法国个人收件人的时限为 10 个工作日，而其条款只给 3 个工作日，建议尽早提交，并保留受损商品 15 天备查。

## DPD France 客服电话与联系渠道

DPD France 的官方联系渠道均汇总在[客户服务页面](https://www.dpd.com/fr/fr/contact/service-clients/)：

- **客服电话：** 09 70 80 85 66（法国境外拨打 +33 9 70 80 85 66），按普通市话计费；法国时间周一至周五 8:00 至 20:00、周六 10:00 至 18:00，公共假日除外。DPD 声明这是其唯一官方客服电话。
- **在线联系表单：** 位于上述同一页面，填写时需提供包裹单号或通知单号及收件邮编。DPD France 不提供公开客服邮箱。
- **私信渠道：** 可从该页面跳转通过 WhatsApp、Messenger 或 X 发送私信。

以上渠道负责 DPD France 网络内的包裹；DPD France 未对瑞士或比利时另设电话。

> DPD France 绝不会用 06 或 07 开头的手机号码发送短信，也不会以派送为由要求额外付费。其官方链接以 my.dpd.fr 开头。收到可疑短信请转发至 33700 举报。

## 用 Peek 查询和跟踪 DPD France 包裹

[Peek](/) 会立刻识别 250 开头的 15 位单号，其他格式则与其他快递公司共用。对于以 0 或 11 至 19 开头的 14 位单号（例如 `14829173640582`），Peek 会向 DPD France 查询是否有记录，从本页输入框查询时会优先询问 DPD France。对于以 10 开头的 14 位单号以及 16 位面单格式，Peek 会先询问能快速查询、使用同一格式的快递公司（14 位会先问 SEUR、BRT 和 Ciblex），再询问物流查询服务；若都没查到，Peek 会在浏览器中打开 DPD France 官方查件页核实（可能多耗时最多 20 秒），一旦看到近期带日期的扫描记录即归入 DPD France。在巴西以外地区，在巴西使用 14 位格式的 Jadlog 会排在最后，仅在 DPD France 也查不到时才尝试。Peek 无法向 DPD France 查询 12 位格式的单号，请点击“更换”并手动选择 DPD France，或在已添加包裹页点击“更换快递公司”。trace.dpd.fr 链接可以直接使用（`/fr/trace/` 与 `/trace-particuliers/`）；`/en/trace/` 或 www.dpd.fr 等其他链接仅在包含 15 位 `250` 单号时可解析；带有 `parcelNumber=` 的 dpd.com 或 dpdgroup.com 链接若路径以 `/fr/` 开头会识别为 DPD France，以 `/ch/` 开头则识别为 DPD Switzerland。

Peek 会显示包裹状态、附带网点信息的扫描历史、预计送达日期、重量和签收时间，并在地图上标出派送网点。状态保留法语原文，时间戳按巴黎时间解析后换算为你设备的本地时区。Peek 会隐去收件人姓名与地址、签名和客户参考号，也不显示自提点名称或地址，仅提示“待取件”。如果 DPD 同时返回发件与退件两段行程，Peek 只保留对应你单号的那一段，且不会跨接跟踪其他快递公司。

当你在 Peek 中打开包裹页面，或在派送当天开启通知时，Peek 最高每 10 分钟检查一次；在 DPD France 扫描包裹进入派送的当天，自法国时间早上 6 点起，官方接口正常响应时每 2 分钟检查一次，需用浏览器打开查件页时每 5 分钟检查一次；其余情况每小时检查一次。若在“已生成面单”或“已揽收”状态下超过 12 小时，或连续两天无新动态，无论你以何种方式关注均改为每小时检查一次；“已生成面单”满七天无动态则改为每 6 小时一次。当只有物流查询服务有响应时，Peek 每小时检查一次，满一天无新扫描后每 3 小时一次，在派送当天打开页面或开启提醒时最高每 15 分钟检查一次。

当包裹仍停留在“已生成面单”、“已揽收”或“运输中”且最新扫描已过四天，Peek 会按该次扫描日期标出提醒，例如“自10月12日周一起没有物流更新”，并在包裹页显示“包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 DPD France 查询。”，同时提供指向 DPD France 客服页的“向 DPD France 寻求帮助”链接。若超过三周仍无新扫描，则显示“已超过三周没有新动态。如果还没收到，请联系卖家。Peek 会持续向 DPD France 查询。”

Peek 可以按“每次扫描”、“重要环节”或“仅派送当天”推送通知。

## DPD France 查询常见问题

### 我的 DPD 包裹现在到哪儿了？

看最新一条轨迹即可：`Colis en agence DPD France` 表示已到当地派送网点，`Colis en livraison` 表示已在快递员派送车上。

### DPD 包裹可以保管多久？

Pickup 自提点保管 9 个自然日，Pickup 快递柜保管 5 个自然日，DPD 网点大约保管一周，超期后会退回寄件人。

### DPD 法国境内派送要几天？

DPD France 表示，在法国境内，包裹自寄件人发货起 24 至 48 小时送达。

### 可以联系 DPD France 人工客服吗？

可以。请在服务时间内拨打 09 70 80 85 66，或通过 DPD France 客服页面上的表单及 WhatsApp 联系。

:::sources
- [DPD France: Où puis-je trouver mon numéro de colis ?](https://www.dpd.com/fr/en/faq/ou-puis-je-trouver-mon-numero-de-colis/) – 单号格式与查找位置
- [DPD France: Comment puis-je suivre la livraison de mon colis ?](https://www.dpd.com/fr/en/faq/comment-puis-je-suivre-la-livraison-de-mon-colis/) – 通知单号与 Predict 短信码
- [DPD France: Suivre mon colis](https://trace.dpd.fr/fr/trace) – 法语与英文版本、25 个单号、无需邮编、状态说明、周六派送
- [DPD France: Suivi colis DPD](https://www.dpd.com/fr/fr/recevoir-des-colis/) – 轨迹显示内容
- [DPD France: Pourquoi mon suivi colis indique-t-il « numéro de colis inconnu » ?](https://www.dpd.com/fr/fr/faq/pourquoi-mon-suivi-colis-indique-t-il-numero-de-colis-inconnu/) – 未知单号与 Chronopost 交接
- [DPD France: Quand mon colis sera-t-il livré ?](https://www.dpd.com/fr/en/faq/quand-mon-colis-sera-t-il-livre-2/) – 派送时段、24 至 48 小时时效与 3 小时预约窗口
- [DPD France: Dois-je répondre au sms ?](https://www.dpd.com/fr/en/faq/dois-je-repondre-au-sms-que-jai-recu-pour-ma-livraison-a-domicile/) – Predict 短信说明
- [DPD France: Comment re-planifier la livraison de mon colis ?](https://www.dpd.com/fr/en/faq/comment-re-planifier-la-livraison-de-mon-colis/) – 23:00 改派截止时间、通知单与转存自提点
- [DPD France: myDPD, my way](https://www.dpd.com/fr/en/mydpd-my-way/) – 派送修改选项
- [DPD France: Livraison à domicile DPD Predict](https://www.dpd.com/fr/fr/expedier-des-colis/domicile-predict/) – Predict 选项与 myDPD 应用
- [DPD France: Pendant combien de temps mon colis est-il gardé en relais Pickup ?](https://www.dpd.com/fr/en/faq/pendant-combien-de-temps-mon-colis-est-il-garde-en-relais-pickup/) – 自提点保管 9 天
- [DPD France: Retrait en agence](https://www.dpd.com/fr/en/faq/suite-a-une-demande-de-retrait-en-agence-pendant-combien-de-temps-mon-colis-est-il-garde-en-agence-d/) – 网点保管 6 个工作日
- [DPD France: Carte d'identité en relais Pickup](https://www.dpd.com/fr/en/faq/devrai-je-presenter-ma-carte-didentite-lors-du-retrait-dun-colis-dans-un-relais-pickup/) – 取件须出示身份证件
- [DPD France: Relais et consignes Pickup](https://www.dpd.com/fr/fr/expedier-des-colis/relais-consignes/) – 快递柜与 20 公斤限重
- [DPD France: Conditions Générales de Vente](https://www.dpd.com/fr/fr/conditions-generales-vente/) – 再次派送、保管期、代领与索赔时限
- [DPD France: Retard de livraison](https://www.dpd.com/fr/en/faq/suis-je-indemnise-en-cas-de-retard-de-livraison/) – 延误须联系寄件人
- [DPD France: Perte totale](https://www.dpd.com/fr/en/faq/comment-proceder-en-cas-de-perte-totale-de-ma-commande/) – 丢失索赔由寄件人发起
- [DPD France: Colis endommagé ou incomplet](https://www.dpd.com/fr/fr/faq/que-dois-je-faire-si-mon-colis-arrive-endommage-ou-incomplet/) – 拒收、书面异议、挂号信与时限
- [DPD France: Réserves en relais Pickup](https://www.dpd.com/fr/en/faq/puis-je-emettre-des-reserves-si-mon-colis-est-endommage-au-relais-pickup/) – 自提点破损异议
- [DPD France: Colis absent du relais](https://www.dpd.com/fr/en/faq/comment-faire-si-mon-colis-nest-pas-au-relais-pickup/) – 自提点查无包裹核查
- [DPD France: Pas d'avis de passage](https://www.dpd.com/fr/en/faq/ma-livraison-est-prevue-ce-jour-mais-je-nai-pas-recu-davis-de-passage-que-faire/) – 未收到通知单处理
- [DPD France: Contacter notre Service Clients](https://www.dpd.com/fr/fr/contact/service-clients/) – 服务时间、表单与社交私信
- [DPD France: Comment contacter le Service Clients ?](https://www.dpd.com/fr/en/faq/comment-contacter-le-service-clients-dpd-france/) – 客服电话与无公开邮箱
- [DPD France: Alerte aux fraudes](https://www.dpd.com/fr/fr/alerte-aux-fraudes/) – 诈骗短信提醒与 33700
- [DPD France: Scam alert](https://www.dpd.com/fr/en/scam-alert/) – 境外拨号
- [DPD France: About DPD France](https://www.dpd.com/fr/en/about-dpd-france/) – Geopost、La Poste 与 Exapaq 背景
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Peek 读取的内容
:::
