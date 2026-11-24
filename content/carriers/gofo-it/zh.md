---
title: GOFO Italy 意大利快递查询：GFIT 单号、状态与投诉指南
description: 查询意大利 GOFO Italy（CIRRO Parcel）包裹：GFIT 与 CIIT 单号格式、意语物流状态含义、延误及 AGCOM 投诉流程。
slug: gofo-it-tracking
published: 2026-11-24
updated: 2026-11-24
---

GOFO Italy 是多国快递网络 GOFO 在意大利的分支，负责意大利境内派送。在 Peek 的快递目录中，它也称为 CIRRO Parcel Italy。其单号以 `GFIT` 或 `CIIT` 开头：可在 gofo.com 的意大利查询页查询，或直接把单号粘贴到上方查询框。还没有单号？请看[如何查找运单号](guide:find-tracking-number)（英文）。

## GOFO Italy 运单号格式

以下格式（不区分大小写）会被 Peek 归入 GOFO Italy：

| 格式 | 示例单号 |
| --- | --- |
| `GFIT` 加 13 位数字 | `GFIT4829105736284` |
| `GFIT` 加 14 位数字 | `GFIT48291057362848` |
| `CIIT` 加 13 位数字 | `CIIT4829105736284` |
| `CIIT` 加 14 位数字 | `CIIT48291057362841` |

GOFO 的其他国家网络采用相似规则，在 `GF` 或 `CI` 后接对应国家代码。`GFFR` 和 `CIFR` 属于 [GOFO France](carrier:gofo-fr)，`GFNL` 属于 GOFO 荷兰，`GFES` 属于 GOFO 西班牙；`GFUS` 加 14 位数字、`GF` 加 13 位或 `CR` 加 12 位属于美国的独立网络 [GOFO Express](carrier:gofo)；`GFCA` 加 14 位数字属于 [GOFO Canada](carrier:gofo-ca)。以 `GFIT` 开头但后面是 12 位或 15 位数字的号码不会被 Peek 识别为 GOFO Italy，请检查是否输错。更多格式区分见[运单号格式指南](guide:tracking-number-formats)（英文）。

## GOFO Italy 意大利语物流状态说明

Peek 读取的是 GOFO 意大利语查询数据，因此不论你使用何种界面语言，扫描记录都会保留 GOFO 的意大利语原文。常见状态按先后顺序如下：

| GOFO 意大利语状态 | 中文含义 |
| --- | --- |
| `Il pacco è in fase di preparazione da parte del mittente e ci sarà presto consegnato` | 寄件人正在备货，很快会交给 GOFO。 |
| `Registrato presso il centro di trasferimento` | 已在 GOFO 中转中心扫码入库。 |
| `Il pacco ha lasciato il centro di smistamento` | 已离开 GOFO 分拨中心。 |
| `Arrivato alla struttura di destinazione regionale GOFO` | 已抵达负责你所在区域的 GOFO 区域站点。 |
| `Preparazione alla consegna` | 正在网点分拣准备，待快递员出发。 |
| `Il corriere è fuori per la consegna` | 快递员已带件外出派送。 |
| `Consegna fallita, tipo:Destinatario assente` | 派送失败：家中无人。 |
| `Consegna fallita, tipo:L'indirizzo è errato` | 派送失败：收件地址有误。 |
| `Il pacco è stato restituito al centro di smistamento` | 派送未果后退回 GOFO 分拨中心。 |
| `Consegnato` | 已签收。 |

有两条状态容易产生误解。在第一条状态之后出现新扫描前，GOFO 尚未拿到实物包裹，Peek 会显示为“已生成面单”。另外，`Il pacco è stato restituito al centro di smistamento` 并不代表包裹退回商家，而是退回了 GOFO 分拨中心，Peek 会将其显示为“需要留意”。所有语言界面中，签收均只显示简洁的 `Consegnato`：GOFO 原文的签收行可能带有收件人相关信息，Peek 会将其隐去。

GOFO 查询系统还会把包裹归入大类状态：`Avviso` 表示 GOFO 标记了异常，Peek 显示为“需要留意”，单独出现并不代表已尝试派送；`Restituito` 表示正在退回寄件人，显示为“已退回寄件人”。

如果包裹由[云途物流](carrier:yunexpress)交接给 GOFO Italy，云途官网轨迹有时会用英文转发 GOFO 扫描，例如 `Preparing for delivery`（网点派前准备）以及与意大利语 `Il pacco ha lasciato il centro di smistamento` 并列出现的 `Package departed from sorting center`。

## GOFO Italy 包裹延误、派送未成功、丢失或破损

**没有新动态。** Peek 会持续向 GOFO 查询。如果包裹在进入派送前连续四天没有新扫描，Peek 包裹页面会给出提示，并提供指向 GOFO Italy 帮助页的“向 GOFO Italy 寻求帮助”链接。另见[为什么物流停止更新](guide:tracking-not-updating)（英文）。

**派送未成功。** 出现 `Consegna fallita, tipo:Destinatario assente`、`Consegna fallita, tipo:L'indirizzo è errato` 或 `tipo:` 后带其他原因的失败记录时，Peek 均显示为“派送未成功”。后续安排由 GOFO 决定，排查建议见[错过派送怎么办](guide:missed-delivery)（英文）。

**延误。** 根据欧盟消费者规定，除非双方另行约定了送达时间，商家必须在 30 天内交货。若逾期未到，可给商家一段合理的宽限期；宽限期满仍未送达，你有权取消订单并要求全额退款。

**显示 `Consegnato` 但未收到。** 商家对订单送达负有责任：应由商家联系快递核实并提供签收证明，若无法证明已妥投，则须补发或退款。先在住所周边自查的步骤见[显示已签收但未收到](guide:delivered-not-received)（英文）。

**破损。** 从发货到你签收期间的运输破损责任均由商家承担，建议尽量当面验货。如果外箱有明显破损，意大利邮政监管机构 AGCOM 表示你可以拒收或附保留意见签收（accept with reservation）。更多见[包裹破损处理](guide:damaged-parcel)（英文）。

## 如何就 GOFO Italy 派送提起投诉

请先联系发货商家，因为商家对派送承担责任。如需直接针对快递运营商投诉，意大利通信与邮政监管机构 AGCOM 对邮政运营商（31.5 公斤以下包裹均属邮政范畴）规定了统一流程：

1. 通过运营商官网表单或发邮件索取的表格提交书面投诉。运营商须在 45 天内回复。
2. 如对回复不满或 45 天内未收到答复，向运营商申请调解（conciliation），调解应在 60 天内完成。
3. 若调解仍未解决，你有 90 天时间通过 AGCOM 的 Formulario CP 申请裁决。AGCOM 须在 150 天内作出对双方均有约束力的决定。

AGCOM 可裁定按规退款或赔偿，但不裁定额外损害赔偿；如需主张损害赔偿，或在任何阶段，你都可以直接诉诸法院。如果运营商不答复投诉或调解申请，可在 AGCOM 的 Portale delle segnalazioni 举报。AGCOM 咨询中心服务时间为周一至周五 10:00 至 14:00，意大利固话拨打 800.185060，手机或境外拨打 081.750750。

## GOFO Italy 客服与公司信息

GOFO Italy 的帮助页面位于其意大利官网 gofo.com；包裹在派送前连续四天无新扫描时，Peek 会通过“向 GOFO Italy 寻求帮助”直接链接到该页。背后运营主体为 GOFO Italia s.r.l.，注册地址为 Via Juan Manuel Fangio 11, 20045 Lainate (MI)。联系前请准备好 `GFIT` 或 `CIIT` 单号以及订单号。

## 使用 Peek 跟踪 GOFO Italy 包裹

[Peek](/) 会自动识别 `GFIT` 和 `CIIT` 单号，也支持直接粘贴 GOFO 意大利查询页链接（gofo.com 或 gofoexpress.it 上带有 `id=` 及单号的链接）。

在你打开页面或开启派送当天提醒的情况下，只要 GOFO 官网正常响应，Peek 最高每 10 分钟读取一次 GOFO 意大利轨迹，并在 GOFO 扫描进入派送当天自当地时间早上 6 点起最高每 2 分钟检查一次（通过物流跟踪服务查询时，在派送当天打开页面或开启提醒时最高每 15 分钟检查一次）；其余时候每小时检查一次。连续两天无新动态后（若仍停留在“已生成面单”或“已揽收”，则为 12 小时后），改为每小时检查一次。Peek 会显示状态、带地名的完整轨迹、签收时间以及地图位置，并支持“每次扫描”、“重要环节”或“仅派送当天”通知。当 GOFO 仅给出站点代码或国家名时，该条扫描不显示具体地名。在 GOFO 出现首条扫描前，Peek 会显示“等待 GOFO Italy 更新”并继续重试，不会立刻判为无效单号；若两天后 GOFO 依然查无此号且没有其他来源返回记录，Peek 会提示“两天过去了，快递公司仍查不到这个单号。请检查单号、更换快递公司，或删除这个包裹。”并改为每天检查一次，满一周后改为每周检查一次。如果 GOFO 已登记该单但尚未扫描，则说明它认可该单号，Peek 会继续保持“等待 GOFO Italy 更新”而不会提示核对单号。在包裹派送、签收、退回或待自提前，Peek 会显示 GOFO 的预计送达日；不显示重量，也省略签收照片和 GOFO 公告。Peek 无法代你联系 GOFO 或商家。

## GOFO Italy 快递查询常见问题

### 我的 GOFO 包裹现在在哪里？

查看轨迹最新一行即可：`Arrivato alla struttura di destinazione regionale GOFO` 表示已到达负责你所在区域的 GOFO 区域站点，`Preparazione alla consegna` 表示正在网点备派，`Il corriere è fuori per la consegna` 表示快递员正在派送。如果只显示寄件人备货那一行，说明寄件人还没有交件给 GOFO。

### GOFO Italy 是正规公司吗？

GOFO 的意大利运营主体是注册在米兰省莱纳泰（Lainate）的 GOFO Italia s.r.l.。意大利邮政监管机构 AGCOM 在 2026 年 9 月 2 日发布的第 12/26/DSP 号决议（Determina n. 12/26/DSP）中记录：其曾于 2026 年 6 月 17 日就三项涉嫌违规事项向该公司发出通知——在未持有所需个人许可的情况下开展普遍服务范围内的邮政业务、缺少服务章程（Carta dei servizi）以及缺少用户争议解决程序。根据该决议，该公司承认了服务章程与争议程序两项指控并缴纳了减免后的罚款，AGCOM 据此终结了该部分程序；但该公司对其资质不足的指控提出异议，该部分继续按常规程序审理。

### GOFO 和 CIRRO Parcel 是同一家吗？

在意大利用于包裹查询时是的：Peek 的快递列表中也将 GOFO Italy 标为 CIRRO Parcel Italy。如果某个追踪网站只写着“Cirro”，则可能指 GOFO 的意大利、法国或荷兰网络。

### GOFO 包裹丢了怎么办？

请联系下单商家。按照欧盟消费者规则，在包裹交到你手里之前均由商家承担派送责任；若商家无法证明妥投，必须补发或退款。如属快递责任，也可按上文 AGCOM 流程投诉。更多见[包裹丢失处理](guide:lost-parcel)（英文）。

:::sources
- [AGCOM: Determina n. 12/26/DSP](https://www.agcom.it/sites/default/files/provvedimenti/determina/2026/Determina%20arch%20parziale%2012_26_DSP%20-%20Gofo%20pdf.pdf) – GOFO Italia s.r.l. 莱纳泰注册地址、AGCOM 2026 年 9 月 2 日决议及缴纳减免罚款记录
- [AGCOM: FAQ contenzioso tra utenti e operatori postali](https://www.agcom.it/faq-contenzioso-tra-utenti-e-operatori-postali) – 45 天投诉回复、60 天调解、90 天提交 Formulario CP、31.5 公斤上限、破损包裹及咨询中心电话
- [Your Europe: Shipping and delivery](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_en.htm) – 30 天交货期、宽限期及收货前破损责任
- [Your Europe: FAQs, shipping and delivery](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/faq/index_en.htm) – 商家承担妥投举证责任及当面验货
:::
