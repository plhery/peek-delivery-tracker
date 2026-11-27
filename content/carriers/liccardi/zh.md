---
title: Liccardi Express Courier 追踪：660 标签、LDV 单号与客服
description: 追踪意大利 Liccardi Express Courier 包裹：660 标签与 LDV 单号格式、网点与自提柜状态码说明、滞留暂存处理流程及客服联系方式。
slug: liccardi-tracking
published: 2026-11-26
updated: 2026-11-26
---

**Liccardi Express Courier**（*F.lli Liccardi S.r.l.*）是一家总部位于那不勒斯附近卡桑德里诺（Casandrino）的意大利全国性快递公司（[liccarditrasporti.com](https://www.liccarditrasporti.com/)）。公司以 Giovanni Liccardi 于三十年前创立的家族运输业务为基础，于 1992 年以现代形式正式启动，从 20 世纪 90 年代的银行文件专递逐步发展为覆盖全意大利的 B2C 与电商包裹配送网络，每年通过包括那不勒斯、罗马、米兰、博洛尼亚、都灵、佛罗伦萨、维罗纳、巴里和萨莱诺在内的 20 多个意大利分支与枢纽处理逾 590 万件货件。你可以通过 Liccardi 网站追踪包裹，或将 13 位 `660` 标签码或 7 至 8 位 *lettera di vettura*（LDV）单号粘贴到上方输入框。

## Liccardi Express Courier 运单号（`660` 标签与 LDV）

Liccardi 为每票货件分配两个纯数字参考编号——以 `66`（通常为 `660`）开头的 13 位包裹标签码，以及更短的 7 位或 8 位运单号（*lettera di vettura* / LDV）：

| 格式（虚构） | 含义 |
| --- | --- |
| `6604829173605` | 13 位 Liccardi 包裹条码（`660` + 8 位运单号 + 2 位校验/包裹序号位）。 |
| `48291736` | 7 位或 8 位 Liccardi 运单号（*LDV* / *lettera di vettura*）。 |

两个编号均可在零售商的发货邮件或 Liccardi 的派送通知中找到，在 Liccardi 系统内指向同一票货件。另见[运单号格式说明](guide:tracking-number-formats)（英文）。

## Liccardi Express Courier 追踪状态含义

Liccardi 的追踪系统会同时发布数字结果码（*codici esito*）和意大利语网点描述。对于一票多件货件（*colli*），Liccardi 会按单件记录扫描；Peek 会一直保持该货件为进行中状态，直到整票货件的所有包裹均已送达：

| Liccardi 显示文案（结果码） | 对你的含义 | Peek 显示 |
| --- | --- | --- |
| `DATI SPEDIZIONE TRASMESSI A LICCARDI`（`50`）、`SERVIZIO PROGRAMMATO`（`58`）、`RITIRO ASSEGNATO AD AUTISTA`（`36`）、`IN RITIRO`（`92`） | 寄件人已传输货件数据，或已安排/正在派司机上门揽件，包裹尚未入库。 | “已生成面单” |
| `SPEDIZIONE RITIRATA`（`37`）、`COLLO PRESO IN CARICO`（`3`）、`SPEDIZIONE PRESA IN CARICO`（`4`） | 已从寄件人处揽收并正式接入 Liccardi 网络。 | “已揽收” |
| `ARRIVATO IN FILIALE`（`47`）、`IN TRANSITO`（`48`）、`SPEDIZIONE PARTITA`（`91`）、`ARRIVO SU FILIALE DI DESTINO`（`72`）、`IN GESTIONE`（`49`）、`GIACENZA SVINCOLATA`（`70`）、`IN PRENOTAZIONE`（`33`） | 正在枢纽间转运、已抵达目的地网点、正在预约派送或已解除暂存滞留。 | “运输中” |
| `CARICO VETTURA`（`51`）、`CARICO VETTURA CONFERMATO`（`95`）、`IN CONSEGNA`（`52`） | 已装上当地派送车，今天正在派送。 | “派送中” |
| `CONSEGNATA AL PUDO`（`6`）、`CHECK-IN AL PUDO`（`80`）、`SPEDIZIONE DEPOSITATA NEL LOCKER`（`11`）、`FERMO DEPOSITO`（`32`） | 已到达 PUDO 自提点、快递自提柜或当地 Liccardi 网点（*fermo deposito*），可供领取。 | “待取件” |
| `ASSENTE`（`28`）、`INDIRIZZO ERRATO`（`40`）、`CONSEGNA NON ESEGUITA`（`43`）、`MANCA CONTRASSEGNO`（`44`）、`INDIRIZZO NON TROVATO`（`45`）、`CHIUSO`（`46`）、`GIRO DI CONSEGNA NON ULTIMATO`（`67`） | 未能完成派送（收件人不在、地址错误、未备妥货到付款金额、商家关门或派送路线未完成）。 | “派送未成功” |
| `IN GIACENZA`（`35`）、`COLLO DANNEGGIATO`（`30`）、`MERCE RESPINTA`（`42`）、`SPEDIZIONE SMARRITA`（`54`）、`COLLO DISGUIDATO`（`68`）、`ORDINE SOSPESO: IN ATTESA DI ISTRUZIONI MITTENTE`（`76`）、`DISCONOSCIMENTO FIRMA`（`103`）、`CONTATTARE ASSISTENZA`（`107`） | 包裹处于滞留暂存状态等待寄件人放行指令（*giacenza*），或出现破损、拒收、错分、遗失或需联系客服处理。 | “需要留意” |
| `RIENTRO AL MITTENTE`（`31`）、`IN CONSEGNA AL MITTENTE`（`53`）、`RESO A MITTENTE`（`8`） | 正在退回或已退回寄件人。 | “已退回寄件人” |
| `SPEDIZIONE CONSEGNATA`（`1`）、`SPEDIZIONE CONSEGNATA A COMMITTENTE`（`5`）、`SPEDIZIONE RITIRATA DA LOCKER DAL DESTINATARIO`（`12`） | 已由收件人签收或从快递自提柜取出。 | “已签收” |

请注意，Liccardi 自身的进度条会将投入自提柜（`11`）视为已送达，而 Peek 会将 PUDO、自提柜和网点自提（*fermo deposito*）统一归为 **“待取件”**，直至收件人实际取走包裹（`12`）。

## 派送时效、服务类型与意大利网点网络

根据 [Liccardi 服务介绍](https://www.liccarditrasporti.com/servizi/)、[关于我们](https://www.liccarditrasporti.com/azienda/)和[网点列表](https://www.liccarditrasporti.com/filiali/)：

- **转运时效：** Liccardi 在 48 小时内上门向商家揽收订单，在分拣枢纽对每件包裹称重、测量并拍照，并宣传全国派送时效为 **24–48 小时**（统计上可在 **72 小时**内覆盖整个全国服务范围；偏远地区 *località disagiate* 耗时更长，旺季也会影响常规时效）。
- **派送选项：** 标准 B2C 快递、送货上楼（*consegne al piano*）、指定时间预约（*consegne tassative*）、当日达、货到付款（*contrassegno*）、网点自提（*fermo deposito*）、换货/逆向取件以及可选附加保险。
- **网点网络：** 在意大利全境设有 20 多个运营枢纽和分拨点，包括卡桑德里诺/那不勒斯中心枢纽（`NA1`）、罗马（`RM1`、`RM3`）、米兰（`MI1`）、博洛尼亚（`BO1`）、都灵（`TO2`）、佛罗伦萨（`FI1`）、维罗纳（`VR1`）、巴里（`BA1`）、萨莱诺（`SA1`）、阿韦利诺（`AV1`）、弗罗西诺内（`FR1`）、拉蒂纳（`LT1`）、摩德纳（`MO1`）、帕尔马（`PR1`）、福贾（`FG1`）、莱切（`LE2`）、马泰拉（`MT1`）、波坦察（`PZ1`）、科森扎（`CS1`）、卡坦扎罗（`CZ1`）及雷焦卡拉布里亚（`RC1`）。若由其他快递承接转派，Liccardi 追踪页面可能会显示该快递的运单号（LDV）。

## 派送未成功、*giacenza* 滞留暂存与申诉

- **`IN GIACENZA`（`35`）或派送未成功（`28`、`40`、`45`）：** 派送未成功或遇到地址问题后，Liccardi 会将货件转入 *giacenza*（滞留暂存），并等待**寄件人**下达放行指令（*svincolo giacenza*）。请先联系网购商家以便其发送放行指令，也可前往收件人服务页 [Assistenza / Reclami Destinatari](https://www.liccarditrasporti.com/assistenza-reclami-destinatari/) 更新派送指示、更正地址错误或申请网点自提（*ritiro in filiale*）。
- **对派送结果提出异议（`Contestazione Esito`）或包裹遗失：** Liccardi 的收件人投诉指引允许你反馈状态错误、人在家中却被标记“收件人不在”，或显示已签收却未收到包裹的情况，并提供可下载的 *Modulo reclamo*、*Modulo conciliazione* 和 *Formulario CP* 表格。同时请务必通知卖家。另见[显示已签收但未收到](guide:delivered-not-received)（英文）、[包裹破损](guide:damaged-parcel)（英文）、[包裹遗失](guide:lost-parcel)（英文）、[包裹退回寄件人](guide:returned-to-sender)（英文）和[为什么物流停止更新](guide:tracking-not-updating)（英文）。

## Liccardi Express Courier 客服与联系方式

[Liccardi 联系页面](https://www.liccarditrasporti.com/contatti/)和 [Assistenza / Reclami Destinatari](https://www.liccarditrasporti.com/assistenza-reclami-destinatari/) 公布的官方联系方式：

- **意大利境内免费热线（*Numero Verde*）：** `800 029 764`（`800029764`）。
- **电话：** `+39 081 5053642`（传真 `081 5053071`）。
- **客服邮箱：** `assistenzaclienti@liccarditrasporti.com`（一般咨询：`info@liccarditrasporti.com`；法定认证邮箱 PEC：`flliliccardi@legalmail.it`）。
- **收件人支持与投诉入口：** [liccarditrasporti.com/assistenza-reclami-destinatari/](https://www.liccarditrasporti.com/assistenza-reclami-destinatari/)。
- **总部与中心枢纽：** *F.lli Liccardi S.r.l.*（税号 P.IVA `06588280633`），Via Paolo Borsellino 133（注册地址 123），80025 Casandrino (NA), Italy。

> 请警惕要求线上支付小额费用以重新安排意大利快递派送的诈骗短信。任何派送变更请直接与商家核实，或通过 Liccardi 网站及官方客服渠道确认。详见[警惕快递诈骗短信](guide:delivery-scam-texts)（英文）。

## 使用 Peek 追踪 Liccardi Express Courier 包裹

输入 13 位 `660` 条码（例如虚构单号 `6604829173605`）时，除俄罗斯地区（会先询问 Ozon）外，[Peek](/) 会优先向 Liccardi Express Courier 查询，并在对方系统确认后显示“Liccardi Express Courier 有这个包裹的记录。”Peek 也会识别粘贴的 `my.liccarditrasporti.com/segui?ldv=` 链接。7 位或 8 位 LDV 运单号（例如虚构单号 `48291736`）本身无法指向特定承运商：请直接粘贴追踪链接，或在添加包裹时点击“更换”（或在包裹详情页点击“更换快递公司”）并选择 **Liccardi Express Courier**（搜索“Liccardi Trasporti”、“Liccardi Express”或“F.lli Liccardi”也能找到）。

Peek 会读取 Liccardi 的追踪系统，并展示当前阶段（“已生成面单”、“已揽收”、“运输中”、“派送中”、“待取件”、“派送未成功”、“需要留意”、“已退回寄件人”、“已签收”）、Liccardi 的意大利语状态文案以及对应网点。具体阶段由 `codEsito` 结果码决定，所有 Peek 客户端均不直接显示这些内部数字代码。Peek 按意大利时间解析扫描时刻，并以你的设备本地时区展示；同时在 Liccardi 返回数据时显示预计送达日期（不含具体时段）、签收时间，以及首次包裹扫描前的汇总状态。当 Liccardi 给出另一家快递的运单号且 Peek 识别该快递时，Peek 会核验其承运状态，随后在页面中显示“由……派送”及该快递名称，并同时列出两个单号。对于一票多件货件，Peek 会合并一分钟内重复的网点扫描，并保持货件处于追踪状态直至每件包裹均已送达。Peek 不展示收件人所在城镇或邮编、司机姓名或签收签名。

在 Liccardi 自有追踪系统正常响应期间，当你打开包裹页面或在派送当天开启通知时，Peek 最高每 10 分钟检查一次；在 Liccardi 出现 `CARICO VETTURA`（`51`）、`CARICO VETTURA CONFERMATO`（`95`）或 `IN CONSEGNA`（`52`）当天的意大利时间早上 6 点起，改为每 2 分钟检查一次。其余时间每小时检查一次。若停留在“已生成面单”或“已揽收”超过 12 小时，或连续两天无新动态，则无论你如何关注都改为每小时检查一次；停留在“已生成面单”满七天后改为每 6 小时一次。当 Liccardi 自有追踪系统无响应而 Peek 仅通过追踪服务跟进时，每小时检查一次，静默一天后每 3 小时一次，派送中当天最高每 15 分钟一次。

当最新扫描距今已满四天，且包裹仍处于“已生成面单”、“已揽收”或“运输中”时，Peek 会用该次扫描的日期加以标记，例如“自10月12日周一起没有物流更新”。包裹页面随后会显示“包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 Liccardi Express Courier 查询。”，并附上指向 Liccardi 收件人支持与投诉页面的“向 Liccardi Express Courier 寻求帮助”链接。如果三周没有新扫描，页面会显示“已超过三周没有新动态。如果还没收到，请联系卖家。Peek 会持续向 Liccardi Express Courier 查询。”

你可以选择“每次扫描”、“重要环节”或“仅派送当天”提醒预设。Peek 仅监控物流更新，无法代你解除 *giacenza* 暂存、修改派送地址或代为联系 Liccardi Express Courier。

## 关于 Liccardi Express Courier 物流查询的问题

### Liccardi 运单号是什么格式？

Liccardi 使用两种单号：以 `66`（通常为 `660`）开头的 13 位包裹标签条码（例如虚构单号 `6604829173605`），或 7 至 8 位运单号（*LDV*，例如虚构单号 `48291736`）。

### Liccardi 追踪里的 `IN GIACENZA` 是什么意思？

`IN GIACENZA`（结果码 `35`）表示包裹因派送未成功或地址问题被暂存在 Liccardi 网点，需要寄件人下达放行指令（*svincolo*）。请联系卖家，同时也可通过 [Assistenza / Reclami Destinatari](https://www.liccarditrasporti.com/assistenza-reclami-destinatari/) 提交更新后的派送信息。

### Liccardi Express Courier 在意大利境内派送需要多久？

Liccardi 宣传揽收后全国派送时效为 **24–48 小时**（统计上 **72 小时**内覆盖其全意大利网络），偏远地区（*località disagiate*）及旺季可能需要更长时间。

### 如何联系 Liccardi Express Courier 客服？

可拨打意大利免费热线 `800 029 764` 或电话 `+39 081 5053642`，发邮件至 `assistenzaclienti@liccarditrasporti.com`，或在 [liccarditrasporti.com/assistenza-reclami-destinatari/](https://www.liccarditrasporti.com/assistenza-reclami-destinatari/) 提交收件人咨询或投诉。

:::sources
- [Liccardi Express Courier: Official Website](https://www.liccarditrasporti.com/) – *F.lli Liccardi S.r.l.*、20 余家网点、年处理 590 万+票货件、卡桑德里诺（NA）总部与联系方式
- [Liccardi Express Courier: Azienda](https://www.liccarditrasporti.com/azienda/) – 1992 年成立至今历程（源自 1960 年代）、2004 年起拓展 B2C 业务及 Unione Industriali 会员资格
- [Liccardi Express Courier: Servizi](https://www.liccarditrasporti.com/servizi/) – 24/48 小时（最长 72 小时）派送、48 小时揽收、货到付款（*contrassegno*）、网点自提（*fermo deposito*）与自动拍照分拣
- [Liccardi Express Courier: Filiali](https://www.liccarditrasporti.com/filiali/) – 20 余家意大利枢纽与分支完整清单（NA1、RM1、MI1、BO1、TO2、FI1、VR1、BA1 等）
- [Liccardi Express Courier: Contatti](https://www.liccarditrasporti.com/contatti/) – `800 029 764`、`+39 081 5053642`、客服邮箱、PEC 及卡桑德里诺地址
- [Liccardi Express Courier: Assistenza / Reclami Destinatari](https://www.liccarditrasporti.com/assistenza-reclami-destinatari/) – 收件人支持分类、异议申诉选项及投诉/调解表格（Peek 链接的帮助页）
- [Universal Parcel Scraper: liccardi](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/liccardi) – `660`/LDV 单号规则、`my.liccarditrasporti.com` 链接规则、完整 `codEsito` 状态映射、多件货件处理、预计送达日及合作快递 LDV 提取
:::
