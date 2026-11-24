---
title: DHL eCommerce 荷兰快递查询：JVGL 单号、状态与自提点
description: 查询荷兰与比利时 DHL eCommerce（原 DHL Parcel）包裹：JVGL 与 3S 单号格式、物流状态中文解读、ServicePoint 自提点及客服。
slug: dhl-ecommerce-helan-kuaidi-chaxun
published: 2026-11-24
updated: 2026-11-24
---

DHL eCommerce 荷兰（前身为 DHL Parcel）负责荷兰与比利时的电商包裹派送，其比利时站点署名为 DHL eCommerce Benelux。你可以凭运单号在 [dhlecommerce.nl 的 Track and trace 页面](https://www.dhlecommerce.nl/en/consumer/track-and-trace)查件（比利时站点还会要求填写邮编），或直接把商家或 DHL 邮件里的单号粘贴到上方输入框。

## DHL eCommerce 荷兰运单号格式

单号位于 DHL 发货邮件或商家的订单邮件里；包裹面单底部则是以 `JVGL` 开头的编码。如果完全没有单号，DHL 表示寄件人一定持有该号码，请向网店索取；也可参考[如何查找运单号](guide:find-tracking-number)（英文）。

| 单号示例（虚构） | 说明 |
| --- | --- |
| `JVGL4829173648` | `JVGL` 后跟字母与数字：最常见的 DHL eCommerce 包裹单号。 |
| `3SDHL48291736` | `3S` 加三位字母再加数字：一种 DHL 条码格式。 |
| `JJD00009048291736482917` | `JJD000090` 后跟 14 至 16 位数字：DHL 荷兰网络的 `JJD` 面单号。 |
| `MDP48291736` | `MDP` 加 8 位字符（数字 1 至 9 与部分大写字母）：Peek 目录归入 DHL eCommerce 荷兰的格式。 |
| 黄色通知卡编号 | 未妥投黄色卡片上的派送码，需在官网配合邮编查询。 |

有些单号外貌相近：`3S` 后接四位字母通常属于 PostNL；其他 `JJD` 单号多为[德国 DHL（DHL Paket）](carrier:dhl)格式（尽管 DHL 荷兰官网有时也能查到）；[DHL Express](carrier:dhl-express) 的运单号则印在条码下方。如果查不到结果，请先核对商家邮件中写明的承运商。

## DHL eCommerce 物流状态中文解读

DHL 查件页面提供英语、荷兰语，比利时另提供法语。以下为荷兰站常见的英文状态按流转顺序排列：

| DHL 状态原文 | 中文含义 |
| --- | --- |
| `Shipment not yet received or processed` | 商家已生成面单，DHL 尚未扫描收到包裹。 |
| `Processed at the sorting center` | 已在 DHL 分拣枢纽完成分拣。 |
| `Arrived in your region` | 已抵达负责派送的当地网点。 |
| `Delivery planned in the route of the courier` | 已排入派送员的派送路线。 |
| `Out for delivery with the courier` | 快递员今天正在派送中。 |
| `We're almost there` | 快递员就在附近，DHL 表示通常在 15 分钟内到达。 |
| `Delivered` | 已签收送达。 |
| `Delivered at the neighbours` | 已交由邻居代收，DHL 仍会留下黄色通知卡。 |
| `We missed each other, we will forward your parcel to a DHL ServicePoint` | 无人签收，包裹将转送至 DHL 自提点。 |
| `Parcel delivered at DHL ServicePoint and is ready for collection` | 已到达 ServicePoint 自提点，等待领取。 |
| `Collected at DHL ServicePoint by the recipient` | 收件人已从自提点取件，派送完成。 |
| `Returned to the sender` | 正在退回商家。 |

有几条含 “delivered” 的状态并不代表送到了你手中：`Delivered at DHL ServicePoint` 指已在自提点待取；`Delivered at depot` 指在 DHL 中转网点，仍在运输中；`Delivered at the sender` 指已退回商家；`Handed over to the courier` 表示正在派送中。

若出现 `Delivery interrupted, the parcel is delayed for one or two days`，DHL 荷兰帮助中心建议等待一至两天；`Sorted incorrectly, delayed because of resorting and forwarding to correct destination`（分拣错误需重新分拣转运）通常延误两个工作日。

## DHL ServicePoint 自提点、快递柜与错过派送

**荷兰境内：** DHL 周一至周六白天或晚间均有派送。收寄后会先发邮件告知预计送达时间，派送当天再缩小为精准时段。分拣完成后（通常在前一晚或当天早上），你可在查件页改选派送时间、交由邻居代收、改投 ServicePoint 或指定家门口安全存放处，每票包裹最多更改日期或时间三次；需要本人签收或原本就是发往自提点的包裹不可更改。

如果家中无人且邻居无法代收，DHL 会把包裹转存附近的 ServicePoint 并留下带编码的黄色通知卡。你仍可在当晚 23:59 前重新申请送货上门、交由邻居或改投其他 DHL 自提点。

DHL 在荷兰拥有约 5,000 个 ServicePoint，多数快递柜全天候 24 小时开放。自提点最长保管 **5 天**（第 2 天发送提醒），逾期退回寄件人。快递柜取件码通过邮件或短信发送，同样保管 **5 天**；放不进快递柜的大件会改存最近的 ServicePoint。取件须出示身份证、护照或驾照；除非商家邮件注明限本人亲取，否则他人一般可持本人证件代领。

**比利时境内：** 比利时通常由 [bpost](carrier:bpost) 在周一至周五代为派送，DHL 查件页也会给出 bpost 轨迹链接。bpost 网点即作为 DHL 自提点使用：自提点保管 14 个自然日，快递柜保管 5 个自然日。若由 DHL 自有快递员上门但未遇到你，会在下一个工作日再派一次；两次均未成功，包裹会在 DHL 区域中心（Regional Hub）保管 5 个工作日，需凭通知卡与身份证件领取。

## 包裹延误、丢失或破损怎么处理？

如果轨迹停滞不动，即便包裹已经在 DHL 手里，DHL 仍要求你联系寄件人：因为网店才是合同方，掌握完整单据并负责发起调查。另见[物流停止更新怎么办](guide:tracking-not-updating)（英文）。

**显示已签收但没收到：** 先问邻居并检查信箱（包括室外信箱）。DHL 建议先等一个工作日，因为包裹常会在次日送达；若仍未收到再联系寄件人，仍无结果再找 DHL。详见[显示已签收但未收到](guide:delivered-not-received)（英文）。

**丢失或破损：** 尽快通知寄件人，由商家向 DHL 索赔并为你补发或退款。在比利时，DHL 明确说明只有寄件人可以提起理赔。

**包裹退回：** 无论荷兰还是比利时，包裹一旦进入退回流程，DHL 都无法中途截停，请联系商家重新发货。

## DHL eCommerce 荷兰与比利时客服联系方式

DHL 面向消费者的官方联系渠道如下：

- **荷兰：** 通过[在线客服聊天](https://www.dhlecommerce.nl/en/chat)联系；DHL 荷兰个人页面未公布客服电话。
- **比利时：** 访问[比利时客服页面](https://www.dhlecommerce.be/en/consumer/customer-service)开启在线聊天（先由数字助手核查是否有人工在线）；同样未公布客服电话。
- **手机应用：** 从 App Store 或 Google Play 下载 My DHL。

> DHL 警示防范要求付款或点击链接的诈骗短信、WhatsApp 消息和邮件。官方邮件发件域名均以 dhl.com、dhl.nl、dhlparcel.nl 或 dhlecommerce.nl 结尾。请直接在官网查件页手动输入运单号，不要点消息中的链接，并将可疑邮件作为附件举报至 spam.ecommerce@dhl.com。仅通过官方渠道安装 My DHL 应用。

## 用 Peek 查询和跟踪 DHL eCommerce 包裹

[Peek](/) 会立刻把 `JVGL` 单号、`3S` 加三位字母和 8 至 10 位数字、`JJD000090` 加 14 至 16 位数字以及上表所示的 `MDP` 格式识别为 DHL eCommerce Netherlands。其他 `3S` 编码也会向 DHL eCommerce Netherlands 核实；其中部分还可能匹配无法自动询问的 PostNL，而 `3S` 后接四位字母的 DHL 条码会被识别为 PostNL 或无法匹配，此时点击“更换”手动选择 DHL eCommerce Netherlands 即可。其余 `JJD` 单号通常默认识别为德国 DHL，如有需要可手动更换承运商。也支持粘贴 my.dhlecommerce.nl、dhlparcel.nl、dhlecommerce.be 和 dhlparcel.be 以 `/home/tracktrace/` 加单号结尾的查件链接。

Peek 查询时不上传你的邮编，因此 DHL 接口不会返回收件人、地址、签名和自提点信息。Peek 会展示物流状态、历史轨迹、预计送达时间（如 DHL 给出了具体时段）以及签收时间；DHL 本身不提供各扫描地点的地名。当你在 Peek 打开包裹页面，或在派送当天开启通知，且 DHL 官方查件系统正常响应时，Peek 最高每 10 分钟检查一次，在包裹扫描进入派送当天自荷兰时间早上 6 点起（若 DHL 给出了时段窗口，则从窗口开启前半小时起）最高每 2 分钟检查一次；其余情况每小时检查一次。若仅通过综合跟踪服务获取更新，则每小时检查一次，满一天无新扫描后每 3 小时一次，在派送当天打开页面或开启提醒时最高每 15 分钟检查一次。状态变更时可推送提醒。连续两天无新动态（或在“已生成面单”、“已揽收”状态下满 12 小时），Peek 改为每小时检查一次。每条轨迹保留 DHL 英文原文，并归入 Peek 的中文阶段标题（如“运输中”或“待取件”）；Peek 将 `Delivered at DHL ServicePoint` 归为“待取件”。发往比利时的包裹不会自动续接 bpost 轨迹。

## DHL eCommerce 荷兰查询常见问题

### DHL eCommerce 和 DHL Parcel 是同一家吗？

是的。荷兰和比利时的 DHL Parcel 已更名为 DHL eCommerce，DHL 表示除品牌名称外服务本身没有变化。

### DHL eCommerce 和 DHL Express 是同一家吗？

不是，两者是各自独立的网络；DHL Express 国际快递的面单条码下方印的是运单号（waybill number）。

### DHL eCommerce 周末派送吗？

在荷兰，DHL 周一至周六均正常派送，因此周六会送货。在比利时，常规派送为周一至周五，仅当寄件人额外选择周六服务时才会在周六派送。

### DHL ServicePoint 自提点会保管包裹多久？

荷兰最长保管 5 天；比利时在 bpost 自提点保管 14 个自然日，在快递柜保管 5 个自然日。

:::sources
- [DHL eCommerce: Track and trace](https://www.dhlecommerce.nl/en/consumer/track-and-trace) – 邮编要求、派送日、未妥投转自提点与 My DHL
- [DHL eCommerce: Consumer](https://www.dhlecommerce.nl/en/consumer) – 由 DHL Parcel 更名说明
- [DHL eCommerce: Track your parcel](https://www.dhlecommerce.nl/en/consumer/support/receiving/track-your-parcel) – 单号来源
- [DHL eCommerce: Not at home](https://www.dhlecommerce.nl/en/consumer/faq/receiving/not-home) – 黄色通知卡、重新预约与 23:59 截止时间
- [DHL eCommerce: Change delivery](https://www.dhlecommerce.nl/en/consumer/change-delivery) – 黄色卡、自提点与快递柜 5 天保管期
- [DHL eCommerce: Can I still make changes to the delivery?](https://www.dhlecommerce.nl/en/consumer/support/receiving/can-i-still-make-changes-delivery) – 改派选项、限制与最多三次修改
- [DHL eCommerce: DHL ServicePoints](https://www.dhlecommerce.nl/en/consumer/dhl-servicepoints) – 约 5,000 个网点与 24 小时快递柜
- [DHL eCommerce: DHL Locker](https://www.dhlecommerce.nl/en/consumer/faq/receiving/dhl-locker) – 超尺寸件转存自提点
- [DHL eCommerce: Collect parcel](https://www.dhlecommerce.nl/en/consumer/faq/receiving/collect-parcel) – 取件证件与代取规则
- [DHL eCommerce: Delayed or missing parcels](https://www.dhlecommerce.nl/en/consumer/support/receiving/delayed-or-missing-parcels) – 联系寄件人与发起调查
- [DHL eCommerce: Customer Service](https://www.dhlecommerce.nl/en/consumer/customer-service) – 已签收未收到、破损与拒收
- [DHL eCommerce: Chat](https://www.dhlecommerce.nl/en/chat) – 荷兰在线客服聊天
- [DHL eCommerce: Klantenservice](https://www.dhlecommerce.nl/nl/consument/klantenservice) – 异常状态解释与无电话说明
- [DHL eCommerce: Report phishing](https://www.dhlecommerce.nl/en/business/knowledge-platform/news/report-phishing) – 官方发件域名、举报邮箱与 My DHL 应用
- [DHL eCommerce Belgium: Customer service](https://www.dhlecommerce.be/en/consumer/customer-service) – bpost 网点、JVGL 面单、DHL Express 与在线聊天
- [DHL eCommerce Belgium: Heures de livraison](https://www.dhlecommerce.be/fr/particuliers/faq/reception/heures-de-livraison) – bpost 派送与周六选项
- [DHL eCommerce Belgium: Colis dans un DHL ServicePoint](https://www.dhlecommerce.be/fr/particuliers/faq/reception/colis-dans-un-dhl-servicepoint) – 14 天和 5 天保管期
- [DHL eCommerce Belgium: Seconde livraison DHL](https://www.dhlecommerce.be/fr/particuliers/seconde-livraison-dhl) – 二次派送与 Regional Hub 保管
- [DHL eCommerce Belgium: Plainte](https://www.dhlecommerce.be/fr/particuliers/faq/reception/plainte) – 仅寄件人可理赔
- [DHL eCommerce Belgium: Temps de transit](https://www.dhlecommerce.be/fr/particuliers/faq/reception/temps-de-transit) – 关联 bpost 查件链接
- [DHL eCommerce: Tracking translations (en-NL)](https://api-gw.dhlparcel.nl/translations/en_NL.json) – 官方英文状态词表
:::
