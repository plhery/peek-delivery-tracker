---
title: 波兰 DHL eCommerce 快递查询：11 位单号、状态与自提
description: 查询波兰 DHL eCommerce（原 DHL Parcel Polska）包裹：11 位运单号格式、物流状态中文解读、DHL POP 自提点与快递柜。
slug: dhl-ecommerce-bolan-kuaidi-chaxun
published: 2026-11-24
updated: 2026-11-24
---

波兰 DHL eCommerce（前身为 DHL Parcel Polska）是 DHL 在波兰的国内电商包裹网络。你可以凭 11 位运单号在 Mój DHL 网站、Mój DHL 手机应用中查件，或直接粘贴到上方输入框。包裹寄出后，DHL 会通过邮件或短信通知你派送日期、地点及预计送达时段。

## 波兰 DHL 运单号在哪里找？

DHL 将运单号称为 *numer listu przewozowego*（运单编号）。波兰国内包裹使用 11 位纯数字，最后一位是根据前 10 位算出的校验位——Peek 正是借此区分有效单号与输错的数字。

| 虚构单号示例 | 说明 |
| --- | --- |
| `14829173640` | 末尾带校验位的 11 位数字：标准波兰 DHL 运单号。 |
| `34829173642` | 同样是有效运单号；首位数字不固定，1、2、3 均常见。 |

单号位于商家的发货邮件中。Mój DHL 可同时跟踪多个包裹，其菜单还提供 “Return your parcel”，方便支持退货的商家让买家通过应用寄回包裹。

找不到单号请联系商家，或参考[如何查找运单号](guide:find-tracking-number)（英文）。

11 位纯数字同样匹配 GLS France、[GLS Germany](carrier:gls-de)、GLS Switzerland 以及[瑞士邮政（Swiss Post）](carrier:swiss-post)包裹单号。如果 DHL 提示单号无效，请核对商家邮件中写明的是否确实为 DHL。

## DHL eCommerce 波兰物流状态中文解读

如果 Peek 界面设为波兰语，Peek 会向 Mój DHL 请求波兰语原文状态；其他语言则请求英文状态。以下为包裹常见的英文当前状态（大致按流转顺序排列）：

| DHL 状态原文 | 中文含义 |
| --- | --- |
| `Shipment preparation` | 商家已创建面单，DHL 尚未拿到包裹实物。 |
| `Posted` | 寄件人已向 DHL 交寄包裹。 |
| `On its way` | 正在 DHL 网络中转运。 |
| `Parcel redirected` | 包裹已改派，目前仍在运输途中。 |
| `For delivery` | 快递员已领件准备派送。 |
| `Parcel has been delivered` | 已签收送达（部分返回仅显示 `Delivered`）。 |
| `Return to the Sender` | 正在退回寄件人。 |
| `The parcel has returned to Sender` | 退件已送达寄件人（有时写作 `Delivered to the Sender`）。 |
| `The sender has requested the withdrawal of the shipment` | 寄件人已申请撤回该快件。 |
| `Shipment disposed of` | DHL 已对该包裹作销毁处理。 |
| `The shipment is lost` | DHL 已判定该包裹丢失。 |

在完整扫描历史中，DHL 会以波兰语或英语列出每一步：交寄时为 `Przesyłka została nadana przez nadawcę w oddziale DHL.`（`The shipment has been dropped off at depot by the sender.`），干线中转为 `Przesyłka w transporcie do oddziału doręczającego.`（`The shipment is in transit to the delivery depot.`），派送当天为 `Przesyłka przekazana kurierowi do doręczenia`（`With delivery courier`），签收时为 `Przesyłka doręczona do Odbiorcy.`（`Delivered to customer.`）。发往 DHL POP 自提点或 DHL BOX 快递柜的包裹会先显示 `Delivery in the way to the DHL Point`，随后显示 `Shipment is waiting in …`（附带网点地址与营业时间），Peek 归为“待取件”。

一旦状态为已撤回、已销毁或已丢失，包裹不会再继续派送，Peek 会标示为需要留意。请先联系商家，再参考下方投诉渠道。

## DHL POP 自提点、DHL BOX 快递柜与修改派送

**自提点与快递柜：** DHL 在波兰拥有 24,000 个收发点，包括 DHL POP 合作自提点和 9,000 组全天候 24 小时开放的 DHL BOX 快递柜。POP 自提点遍布加油站、便利店与报刊亭，涵盖 Żabka、ABC 和 Lewiatan 等连锁店。DHL 标准服务还包含周六向自提点与快递柜免费派送。

**修改派送：** DHL 声明收件人可自行免费修改派送日期或地址，无需经过寄件人。在 Mój DHL 中可针对单个包裹改投自提点、更改地址、拒收、联系或查看快递员位置以及找回取件 PIN 码；主菜单亦设有 “Redirect parcel”。

## 包裹延误、丢失或破损怎么办？

**无更新：** `Shipment preparation` 表示商家刚建好面单、DHL 尚未收货，请问商家何时交付。长时间停滞见[物流停止更新怎么办](guide:tracking-not-updating)（英文）。

**延误、丢失或破损：** 网购请先找商家。欧盟消费指引 Your Europe 写明，商家须对商品从发运直至你实际收货期间的一切破损负责，且除另有约定外须在 30 天内交货。若逾期未到，可提醒商家并给出合理宽限期；宽限期满仍未送达可解约退款。

**向 DHL 投诉：** DHL eCommerce 波兰在线门户 DHL24 设有投诉表单（“Zgłoszenie reklamacji / dyspozycji”），提交时需填写面单号或运输订单号。显示签收但未收到见[显示已签收但未收到](guide:delivered-not-received)（英文）。

## 波兰 DHL 客服与投诉渠道

- **Mój DHL 应用/网站：** 菜单内设有波兰语和英语的 “Contact us” 与 “Make a complaint”。
- **DHL24 门户：** 上述在线投诉表单。
- **优先联系商家：** 商家选用了 DHL 并对交货前商品状态负全责。

> 收到要求为包裹补缴额外费用的短信很可能是诈骗。波兰国家网络安全机构 CSIRT NASK 警示冒充快递员的诈骗短信会导向窃取银行卡信息的假支付页面。切勿点击链接，应自行在 Mój DHL 或 Peek 输入单号核实；可将整条短信（含链接）免费转发至 8080 举报，若已泄露卡号请立刻联系银行。

## 用 Peek 查询和跟踪 DHL eCommerce 波兰包裹

面对 11 位纯数字，[Peek](/) 会同时询问可能匹配的承运商（GLS France、GLS Germany、GLS Switzerland、瑞士邮政 PostLogistics 及 DHL eCommerce Poland；若你在波兰查询，会优先询问 DHL eCommerce Poland，并以 GLS Poland 替代 PostLogistics），当只有 DHL 有记录时显示“DHL eCommerce Poland 有这个包裹的记录”。如果 GLS 网络也同时查到，请点击“更换”手动选择 DHL eCommerce Poland；手动选择后，11 至 34 位的其他字母数字单号同样可查。Allegro Delivery 的 `AD` 单号因由 Allegro 自行跟踪而不予受理；`JJD` 编号默认识别为德国 DHL（`JJD000090` 加 14 至 16 位数字识别为 DHL eCommerce 荷兰），除非手动指定波兰 DHL。支持粘贴 mojdhl.pl（`paczki=`）和 sprawdz.dhl.com.pl（`sn=`）查件链接；带 `tracking-id=` 的 dhl.com 链接会识别为德国 DHL，请直接输入纯单号。

Peek 从 Mój DHL 读取波兰语或英语当前状态，并统一从 sprawdz.dhl.com.pl 以英文读取完整扫描历史（附 DHL 英文原文如 `The shipment is in transit to the delivery depot.`、地点及当地时间）。若历史暂不可读，Peek 仅展示当前状态（如 `Parcel has been delivered`），除已签收和已退回采用 DHL 时间外，其余记录标记为 Peek 获取时刻。无论哪种情况，Peek 均会补充预计送达日期（含 DHL 给出的时段）、寄件人（通常为网店）和签收时间。绑定多件包裹的总单号不支持此方式查询。Peek 最多每小时检查一次（派送当天亦同），查询失败后等待 2 小时，手动刷新间隔相同；“已生成面单”满七天无新动态则每 6 小时检查一次。

当包裹仍停留在“已生成面单”、“已揽收”或“运输中”且最新扫描已过四天，Peek 会按该次扫描日期提示（例如“自 10 月 12 日起没有物流更新”），并在详情页显示“包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 DHL eCommerce Poland 查询。”及指向 DHL 查件帮助页的“向 DHL eCommerce Poland 寻求帮助”链接。超过三周仍无新扫描时，显示“已超过三周没有新动态。如果还没收到，请联系卖家。Peek 会持续向 DHL eCommerce Poland 查询。”

Peek 支持按“每次扫描”、“重要环节”或“仅派送当天”发送提醒。

## 波兰 DHL 查询常见问题

### 可以自己修改波兰 DHL 包裹的派送地址或日期吗？

可以。DHL 表示收件人无需联系寄件人即可免费自行修改派送日期或地址；在 Mój DHL 中也可将包裹改投至 DHL POP 自提点。

### 波兰哪里可以自提 DHL 包裹？

可在任意 DHL POP 自提点或 DHL BOX 24/7 快递柜领取。DHL 在波兰共设 24,000 个网点，POP 自提点涵盖加油站、商店及 Żabka、ABC、Lewiatan 等便利店。

### 什么是 DHL eCommerce 包裹？

由 DHL eCommerce 波兰（原 DHL Parcel Polska）承运的波兰国内网购包裹，可送货上门或投递至 DHL POP 自提点与 DHL BOX 快递柜。

### 能用 DHL 查 Allegro Delivery 包裹吗？

这里查不到。Allegro Delivery 包裹由 Allegro 自行追踪，因此 Peek 不受理其 `AD` 单号，请前往 Allegro 查询。

:::sources
- [DHL eCommerce Poland: Mój DHL tracking](https://mojdhl.pl/tracking) – 查件页与应用、11 位国内运单号、菜单及单件操作选项
- [DHL24: DHL eCommerce Poland's portal](https://dhl24.com.pl/) – 邮件与短信提醒、免费改期改址、24,000 个网点及 Mój DHL 退货
- [DHL: DHL eCommerce ma już 9 tys. automatów DHL BOX 24/7](https://www.dhl.com/pl-pl/home/prasa/prasa-archiwum/2026/dhl-ecommerce-ma-juz-9-tys-automatow-dhl-box-24-7-paczka-coraz-czesciej-czeka-po-drodze.html) – 9,000 组 DHL BOX 24/7 快递柜与标准服务免费周六投递至自提点/快递柜
- [DHL24: Zgłoszenie reklamacji / dyspozycji](https://dhl24.com.pl/pl/claim/disposition/create.html) – 在线投诉表单
- [DHL24: Wyślij paczkę](https://dhl24.com.pl/pl/paczka.html) – Żabka、ABC、Lewiatan 合作点与 DHL BOX 24/7
- [Your Europe: Shipping and delivery](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_en.htm) – 商家对交货前破损担责、30 天时限、宽限期与退款
- [CSIRT NASK: oszustwo na „przesyłkę”](https://www.gov.pl/web/baza-wiedzy/uwaga-csirt-nask-ostrzega-w-okresie-przedswiatecznej-goraczki-zakupowej-wraca-popularne-oszustwo-na-przesylke) – 仿冒快递短信警示、8080 举报与银行求助
:::
