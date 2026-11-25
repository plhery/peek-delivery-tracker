---
title: TNT Italy 意大利快递查询：运单号、状态与派送失败处理
description: 在 tnt.it 查询意大利 TNT Italy 包裹：运单号规则、意大利语物流状态含义、派送失败与重派、网点自提、理赔与客服电话。
slug: tnt-italy-tracking
published: 2026-11-24
updated: 2026-11-24
---

TNT Italy 是 FedEx Express Italy S.r.l. 旗下的 TNT 品牌；2022 年 12 月 1 日，TNT 与 FedEx 在意大利的业务合并为这家单一公司。意大利境内包裹使用两位字母加八位数字的运单号（*lettera di vettura*），例如 `NA48291736`。你可以在标有 FedEx 名称的 [tnt.it 查询页](https://www.tnt.it/tracking/Tracking.do)查询。运单号位于寄件人的发货确认通知中，以及家中无人时快递员留下的“Lasciato avviso”通知卡上。

## TNT Italy 运单号格式与查找方式

tnt.it 要求输入带有开头两位识别字母的运单号。如果末尾带有一个短横线和额外一位数字，请去掉短横线和那位数字。

| 你拿到的号码 | 实际应输入 | 虚构示例 |
| --- | --- | --- |
| 运单号：2 位字母加 8 位数字 | 原样输入 | `NA48291736` |
| 带短横线和 1 位尾数的运单号 | 仅输入 2 位字母加 8 位数字 | `NA48291736-3` 填 `NA48291736` |
| 国际托运单（*Connote*）：9 位数字 | 输入 9 位数字：tnt.it 可查，但属于 TNT 国际件 | 无（TNT 会循环复用这类号码） |

寄件人可在发货确认邮件、发货单据或交接清单上找到运单号；收件人可向寄件人索要，或查看快递员留下的“Lasciato avviso”通知卡（卡上印有运单号）。tnt.it 支持用逗号分隔一次查询多个单号。另见[如何查找运单号](guide:find-tracking-number)（英文）。

## TNT Italy 意大利语物流状态说明

tnt.it 的扫描记录为意大利语。许多提示会提到 FedEx——因为 TNT Italy 属于 FedEx Express Italy，这并不表示包裹更换了快递公司。

| TNT 原文 | 中文含义 |
| --- | --- |
| `Spedizione non ancora affidata a FedEx` | 寄件人已开单，TNT 尚未收到实物包裹。 |
| `Spedizione affidata a una FedEx Location in attesa di ritiro del corriere` | 寄件人已投寄至 FedEx Location 门店，等待快递员取件。 |
| `La spedizione e' regolarmente partita` | 已按计划发运。 |
| `La spedizione e' in transito` | 正在 TNT 网络中转运。 |
| `Spedizione non consegnabile causa rallentamenti operativi` | TNT 作业滞后（原文意为“运营放缓”），与你的地址无关。 |
| `Spedizione non consegnabile a causa di rallentamenti operativi a destino` | 同样是运营延误，发生在目的地派送网点。 |
| `Ci scusiamo per il disagio. La spedizione sara' consegnata al piu' presto` | 已延误，TNT 表示将尽快安排派送。 |
| `La spedizione verrà consegnata come da accordi presi con il destinatario` | 将按与收件人的约定派送，例如新的日期或地点。 |
| `La spedizione e' in consegna in data odierna` | 今日正在派送中。 |
| `La spedizione e' in fermo deposito presso la filiale di competenza` | 已暂存在当地网点，等待你前往自提。 |
| `Spedizione consegnata` | 已签收。 |

tnt.it 还会显示预计送达日期（`Data prevista consegna`），Express 服务还会注明时间，例如 `entro 19:00`（19:00 前）。签收后页面会列出签收人（`Ricevuta e firmata da`）。部分货件还带有“Vuoi modificare data o luogo di consegna?”按钮，可在线修改派送日期或地点。

多票货件可能共用同一运单号。如果 tnt.it 提示 `Alcune di queste spedizioni hanno la stessa lettera di vettura`，请选择日期匹配的那一票。如果出现 `Emessa nuova Lettera di Vettura`，说明已改签新运单号，请改用新单号跟踪。

## TNT Italy 派送、派送失败、重派与网点自提

TNT Italy 的 Express 服务在意大利大部分市镇提供次工作日送达；往返卡拉布里亚、撒丁岛和西西里岛的 Economy Express 则按 TNT 称作“最优日（best-day）”的时效派送。

**派送未成功。** 家中无人时，快递员会留下“Lasciato avviso”通知卡。通过 tnt.it 的 [Lasciato avviso 表单](https://www.tnt.it/lasciatiAvvisi/Law.do)可预约再次派送，填写运单号、收件邮编（CAP）和省份即可。TNT 跟踪常见问题说明重派仅限周一至周五；如果没有收到通知卡，请携带运单号和收件地址联系 TNT。

**后续处理由寄件人决定。** 当 TNT 无法完成派送时，会通知寄件人向网点发送书面处理指令。若未收到指令，TNT 可在发出滞留通知（*avviso di giacenza*）后 30 天内退回包裹。如果寄件人要求重派后再次失败，TNT 最多报告两次派送尝试，随后等待寄件人关于退件的书面指示。因此派送失败后包裹若迟迟不动，请同时请卖家给 TNT 发指令。

**前往网点自提。** 看到 `La spedizione e' in fermo deposito presso la filiale di competenza` 说明包裹正在目的地网点待取。tnt.it 会显示网点地址，[网点查询工具](https://www.tnt.it/dovesiamo/dovesiamo.do)则列出了营业时间与放行滞留件专线（*Telefono svincolo giacenze*）。主要网点在周六上午也开放自提。

**FedEx Location 门店与 ProntoPacco 自提点。** 寄件人可选择寄往 FedEx Location 门店或 ProntoPacco 自提点（限重 20 公斤、尺寸不超过 50 × 50 × 50 厘米）。FedEx Location 最多保管 5 个工作日，可在门店营业时间内领取。在 ProntoPacco 自提点，TNT 一旦放妥即计为已签收，因此显示签收可能代表包裹正在该点等待你领取。

## TNT Italy 包裹延误、丢失、破损或未收到

**没有更新。** 在寄件人交寄前，包裹可能一直停在 `Spedizione non ancora affidata a FedEx`。如果后续状态连续几天不动，请联系 TNT。更多见[物流停止更新怎么办](guide:tracking-not-updating)（英文）。

**显示已签收但没收到。** 先在 tnt.it 查看签收人姓名。若可下载签收单，页面会出现“Scarica prova di consegna”按钮；否则请通过在线表单或客服索取，并同时联系 TNT 和卖家。详见[显示已签收但未收到](guide:delivered-not-received)（英文）。

**破损或丢失。** 按照 TNT 条款，你须在 21 天内提出索赔通知——破损或延误自签收日起算，丢失或未送达自预计送达日起算。签收时如有任何破损，务必在签收单上注明；无异议签收会被视作完好交付。索赔可通过 tnt.com 或 TNT 客服提交，请保留外箱和缓冲材料以备核查。意大利境内标准赔偿上限为每公斤 1 欧元，除非寄件人另购了保价。参考[包裹破损处理](guide:damaged-parcel)（英文）和[包裹丢失处理](guide:lost-parcel)（英文）。

**欧盟以外来件。** 欧盟委员会规定，自 2026 年 7 月 1 日起，从欧盟以外网购、货值不超过 150 欧元的包裹按商品类别每类征收 3 欧元临时固定关税，首先由清关申报方缴纳；自 2026 年 11 月 1 日起，欧盟还按商品类别每类收取 2 欧元处理费，不论包裹货值高低，均按同样方式征收。TNT 承运这类跨境件时会使用国际托运单号，另见 TNT 及[海关指南](guide:customs)（英文）。

## TNT Italy 客服电话与投诉渠道

TNT Italy 公开的官方渠道如下：

- **+39 02 3601 7467：** 受理各类咨询，意大利境内及境外均可拨打，按运营商正常资费计费，由意大利本土客服接听，服务时间为意大利时间周一至周五 8:30 至 18:30。
- **800 123 868：** 意大利境内免费热线，受理正式投诉、运费报价与时效咨询。
- **[在线联系表单（Contattaci online）](https://www.tnt.it/CustomerOnline/)：** 24 小时开放，可用于查件、申请签收证明、投诉或重新安排未成功的派送。
- **网页在线聊天：** 位于 tnt.it，适合需要人工介入的查件问题。

**正式投诉。** 按照 TNT Italy 服务章程，其须在 45 天内回复投诉。如对结果不满或未获回复，你可通过消费者协会提交 tnt.it 上的调解申请表，调解期不超过 60 天，申请会送达 TNT Italy 的认证电子邮箱（PEC）fedexexpressitalysrl@legalmail.it。如果调解仍未解决，你可在调解结束后 90 天内使用 Formulario CP 向意大利通信与邮政监管机构 AGCOM 申诉。

## 使用 Peek 跟踪 TNT Italy 包裹

直接粘贴两位字母加八位数字（`NA48291736`），或带短横线与尾数的格式（`NA48291736-3`）即可。[Peek](/) 会向 TNT Italy 查询这两种格式；若八位数字以 0 结尾，还会一并查询 DHL eCommerce Iberia，带短横线的则会同时查询 GLS Italy；当 TNT Italy 确认有记录时，界面显示“TNT Italy 有这个包裹的记录”。若没有出现 TNT Italy，可点击“更换”或在包裹页点“更换快递公司”选择它。粘贴带 `consigNos=` 参数的 tnt.it 查询链接会直接识别为 TNT Italy。

Peek 直接读取 tnt.it 数据，因此扫描记录保留 TNT 的意大利语原文，同时显示状态、tnt.it 注明的网点轨迹以及签收时间。在你于 Peek 中打开该包裹时，或在派送当天且已开启提醒时，只要 TNT 官网正常响应，Peek 最高每 10 分钟查询一次，并在 TNT 扫描进入派送当天自意大利时间早上 6 点起最高每 2 分钟查询一次；其余时候每小时检查一次。连续两天无新动态后（若仍处于“已生成面单”或“已揽收”，则为 12 小时后），改为每小时检查一次；若在“已生成面单”停留满七天无新扫描，则改为每 6 小时检查一次。若包裹处于“已生成面单”、“已揽收”或“运输中”且连续四天没有新扫描，Peek 会标出最后扫描日期（“自…起没有物流更新”），提示“包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 TNT Italy 查询。”并提供“向 TNT Italy 寻求帮助”链接；满三周无更新后提示变为“已超过三周没有新动态。如果还没收到，请联系卖家。Peek 会持续向 TNT Italy 查询。”支持“每次扫描”、“重要环节”或“仅派送当天”提醒。预计送达日、起止地、件数、寄件人参考号与签收人姓名仅在 tnt.it 上显示。Peek 无法跟进 tnt.it 转交至 FedEx 官网的货件，也不会切换至改签后的新运单号（请添加新单号），也无法代你联系 TNT 或寄件人。

## TNT Italy 快递查询常见问题

### TNT 意大利周六派送吗？

派送失败的包裹不在周六重派：TNT 跟踪常见问题写明重派仅限周一至周五，且 TNT Italy 的国内服务页面均未提及周六派送。不过周六上午支持网点自提：主要网点周六上午办理滞留件领取，FedEx Location 门店则按各自营业时间开放（tnt.it 注明其中许多门店周六、周日均营业）。

### TNT Italy 属于 FedEx 吗？

是的。tnt.it 明确说明 TNT 与 FedEx 的意大利公司已于 2022 年 12 月 1 日合并为单一实体 FedEx Express Italy S.r.l.，总部位于米兰附近的佩斯基耶拉博罗梅奥（Peschiera Borromeo）。TNT 品牌服务仍与 FedEx 并行运营，因此 tnt.it 带有 FedEx 的名称，状态中也会提到 FedEx。

### 为什么 tnt.it 会把我跳转到 FedEx 官网？

因为该票货件由 FedEx 派送。此时 tnt.it 会提示 `La tua spedizione sarà consegnata da FedEx` 并引导前往 fedex.com 查询。更多见 [FedEx 快递查询](carrier:fedex)。

### 为什么 tnt.it 查不到我的运单号？

tnt.it 说明货件交寄后可能需要几小时才会出现在查询系统中。同时请核对输入的是两位字母加八位数字，不要包含短横线和末尾那一位数字。

:::sources
- [TNT Italy: Tracking spedizione](https://www.tnt.it/tracking/Tracking.do) – 查询入口、运单示例、批量查询、共用运单与转交 FedEx 提示
- [TNT Italy: Istruzioni tracking online](https://www.tnt.it/tracking/instructions/tracking_online.pdf) – 短横线尾数规则与国际托运单
- [TNT Italy: tracking page script](https://www.tnt.it/tracking/_assets/javascript/index.js?v=85316) – 运单号位置、查无记录、预计送达、下载签收证明与改签新单号
- [TNT Italy: Gestione "Lasciato avviso" e mancata consegna](https://www.tnt.it/lasciatiAvvisi/Law.do) – 派送失败后预约重派与通知卡上的运单号
- [TNT Italy: Carta dei Servizi](https://www.tnt.it/download/Carta_Servizi.pdf) – FedEx Express Italy、服务种类、FedEx Location 与 ProntoPacco、联系方式、投诉与 PEC
- [TNT Italy: Condizioni Particolari di Trasporto](https://www.tnt.it/download/Addendum_TNT_TC_EU_ITversion.pdf) – 寄件人指令、30 天退件、两次尝试与 ProntoPacco
- [TNT Italy: FedEx Location](https://www.tnt.it/contents/proximity/fedex-location.html) – 5 个工作日保管期与 20 公斤上限
- [TNT Italy: Trova il punto FedEx Express più vicino a te](https://www.tnt.it/contents/trova-fedex.html) – FedEx Location 周末营业时间
- [TNT Italy: Dove siamo](https://www.tnt.it/dovesiamo/dovesiamo.do) – 网点查询、营业时间与放行滞留件电话
- [TNT Italy: Contattaci online](https://www.tnt.it/CustomerOnline/) – 24 小时在线表单与受理事项
- [TNT Italy: Modulo di conciliazione](https://www.tnt.it/download/Modulo_conciliazione.pdf) – 通过消费者协会申请调解与 60 天期限
- [TNT Italy: Modulo di contatto](https://www.tnt.com/express/it_it/site/support/numero-unico.html) – 客服电话、服务时间与资费
- [TNT Italy: Tracking](https://www.tnt.com/express/it_it/site/shipping-tools/tracking.html) – 周一至周五重派、无通知卡处理与号码复用
- [TNT Italy: Termini e condizioni](https://www.tnt.com/express/it_it/site/terms-conditions.html) – 21 天索赔时限与核查要求
- [TNT Italy: Servizi addizionali](https://www.tnt.com/express/it_it/site/shipping-services/optional-extras/accessori-nazionali.html) – 周六上午网点自提、每公斤 1 欧元赔偿上限与签收证明
- [AGCOM: FAQ contenzioso tra utenti e operatori postali](https://www.agcom.it/faq-contenzioso-tra-utenti-e-operatori-postali) – 投诉期限与 Formulario CP
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 自 2026 年 7 月 1 日起 150 欧元以内每类商品 3 欧元关税与自 2026 年 11 月 1 日起 2 欧元处理费
- [European Commission: Customs Guidance on EUR 3 customs duty](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 商品类别定义、申报方先缴与 2 欧元处理费不限货值
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Peek 显示的内容
:::
