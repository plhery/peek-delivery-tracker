---
title: Geniki Taxydromiki 物流查询：运单号、状态与联系
description: 查询希腊和塞浦路斯的 Geniki Taxydromiki 包裹：10 位运单号、各状态含义、无人签收、理赔与联系方式。
slug: geniki-taxydromiki-wuliu-chaxun
published: 2026-11-26
updated: 2026-11-26
---

Geniki Taxydromiki（Γενική Ταχυδρομική，有时称为 General Post）是一家希腊快递公司，在希腊、塞浦路斯、保加利亚和罗马尼亚拥有自有派送网络。你可以在 taxydromiki.com 的 Track & Trace 页面输入 10 位运单号（voucher）查询包裹，或直接粘贴到上方输入框。单号由下单寄件的一方提供，通常是你购物的商家。

## Geniki Taxydromiki 运单号长什么样

Geniki 将其称为快递运单编号（ΣΥ.ΔΕ.ΤΑ.，投诉表单上写作 SYDETA），官网查询页则称之为 voucher。Geniki 未公布统一格式，但 Peek 读取的运单号均为 10 位数字。

| 格式（虚构） | 含义 |
| --- | --- |
| `4829173650` | Geniki Taxydromiki 运单号：10 位纯数字，不含字母。 |
| `RR482917367GR` | 不是 Geniki：以 `GR` 结尾的邮政单号属于 ELTA Hellenic Post（希腊邮政）。 |

Geniki 没有说明收件人从哪里获取单号：请查看商家的发货邮件或订单页，或直接询问商家。详见[如何查找运单号](guide:find-tracking-number)（英文）。

10 位数字并非 Geniki 独有。ACS Courier 的运单号外观完全一样，[DHL Express](carrier:dhl-express)、[Mondial Relay](carrier:mondial-relay)、Relais Colis 等快递也使用 [10 位数字单号](guide:tracking-number-formats)（英文）。如果商家邮件写的是其他快递公司，请去那家查询。Geniki 官网一次最多支持用逗号分隔同时查 10 个单号。

## Geniki Taxydromiki 物流状态含义

Geniki 查询页在希腊语站显示希腊语，在英语、保加利亚语和罗马尼亚语站显示英语。以下是英文状态原文，大致按包裹流转顺序排列。

| Geniki 状态 | 对你的含义 |
| --- | --- |
| `Shipment label created/printed` | 寄件人已生成运单，Geniki 尚未收到实物包裹。 |
| `Shipment Collected / PickedUp` | Geniki 快递员已从寄件人处揽收包裹。 |
| `Registration of shipment details` | 网点已登记收到的包裹信息。 |
| `Departure from Service Point` | 已离开揽收网点。 |
| `Arrival at hub`、`Departure from hub` | 正在 Geniki 分拣中心处理，发往你的地区。 |
| `Arrival at Service Point` | 已到达负责派送或继续中转的网点。 |
| `Routing to other service point` | 正在转往其他网点。 |
| `Out for Delivery` | 已交给派送员，正在派送。 |
| `Attempted Delivery - Absent recipient` | 派送员上门时你不在，未能送达。 |
| `Attempted Delivery - Delivery Rescheduled` | 派送失败后退回网点，改期另日再派。 |
| `Shipment Delivered` | 已签收。 |
| `Registration of delivery details` | 网点已录入签收信息，同样表示已签收。 |

在希腊语页面上，`Out for Delivery` 写作“Χρέωση σε κούριερ”，字面意思是“已交由快递员负责”。

如果 Geniki 查不到某个单号，即使在英文页面也只会弹出希腊语提示。各阶段通用说明见[物流状态含义](guide:tracking-statuses)（英文）。

## 派送方式、Geniki 门店与无人签收

**谁可以代收。** Geniki 的《消费者义务章程》写明，快递员会将包裹送至你的地址，交给你本人；如你不在，可交给同住人（英文版注明须为成年人，希腊语版未作此限定），或送至寄件人选定的服务网点。

**人不在家时。** Geniki 会留下书面通知或发送电子通知，并将包裹带回负责派送的门店，随后与你或寄件人约定至少再派送一次。

**修改收件地址。** 你可以致电或书面（例如发邮件）要求改派到其他地址；若超出原派送服务区，可能产生额外费用。无签字派送由寄件人在下单时选择：届时你通过 Geniki 提供的 PIN 码或电子消息确认收货。

**门店与快递柜。** 选择 Door 2 Service Point（D2SP）时，包裹会送到 Geniki 门店而非上门；在塞浦路斯则送到 Geniki 办事处，由寄件人在下单时选定。在希腊，Geniki 也可投递至 BOX NOW 快递柜；其快递柜查询工具覆盖希腊和塞浦路斯。投入快递柜的包裹在通知后保管 48 小时。Geniki 官网还提供门店查询工具，但未公布门店保管期限。

**一直未取件。** 拒收或无法派送的包裹会退回寄件人。Geniki 会把无法派送的物品自最后一次派送失败起保管六个月。

## Geniki 包裹未到、丢失或破损

**没有更新。** Geniki 常见问题建议查看 Track & Trace，或凭运单号联系当地 Geniki 门店。一直停在 `Shipment label created/printed` 说明包裹还没交到 Geniki 手里：请联系商家。等待时间参考[物流不更新怎么办](guide:tracking-not-updating)（英文）。

**包裹破损。** 签收时须在运单上注明保留意见，或通过邮件、电话提交声明：《章程》规定在签收后 1 个工作日内，《通用运输条款》规定为 2 个工作日内，请立即反馈。

**丢失或严重延误。** 包裹丢失、破损或未送达时，Geniki 要求联系负责派送的分部。其《章程》将延误超过 15 个工作日的包裹视为丢失。赔偿归签署合同并付费的一方（通常是寄件人），因此网购订单请联系商家由其申请理赔。《章程》和条款允许自收寄起 6 个月内提出书面索赔，但 Geniki 常见问题写的是揽收后 20 天内，因此切勿拖延。

**投诉。** Geniki 的在线投诉表单（Complaint Submission）需填写姓名、邮箱、电话、事由（丢失、破损、延误、派送问题等）、SYDETA 单号及说明。每份在线投诉都会通过邮件收到受理编号，并在 15 个工作日内获得回复。

## Geniki Taxydromiki 客服与地址

taxydromiki.com 公布的联系方式：

- **客服中心（希腊）：** +30 210 4851100，希腊时间周一至周五 08:00 至 20:00、周六 09:00 至 14:00。Geniki 在英国、卢森堡和马耳他不设办事处，在这些地区也请拨打该号码。
- **邮箱：** info@taxydromiki.gr，或使用联系表单（“Having an issue with your shipment?”）。
- **投诉：** 通过 taxydromiki.com 上的 Complaint Submission 表单提交。
- **总部地址：** 81 Makrigianni Str., Ag. I. Rentis, Attica, 18233；希腊时间周一至周五 09:00 至 20:00、周六 09:00 至 14:00。
- **塞浦路斯办事处：** 72 Digeni Akrita Av., 1061 Nicosia，电话 +357 22002146 或 77770727，未公布办公时间。

> Geniki 提醒警惕仿冒邮件、短信和 Viber 消息，这些诈骗信息会要求支付小额运费、重派费或仓储费，或诱导点击链接修改地址、填写银行卡信息。Geniki 绝不会通过短信、邮件或 Viber 链接要求银行卡付款，其官方邮件仅来自以 @taxydromiki.gr 结尾的地址。

## 用 Peek 跟踪 Geniki Taxydromiki 包裹

[Peek](/) 无法仅凭 10 位数字确定是 Geniki 运单。当末位数字通过 Geniki 与 ACS、DHL Express 共用的校验规则时，Peek 会同时向 Geniki、DHL Express、ACS 以及其他符合该格式的公司——如 DHL eCommerce Iberia，以及首位不为 0 时的 [Packeta](carrier:packeta)——查询是否有此包裹。若只有 Geniki 查到，页面会显示“Geniki Taxydromiki 有这个包裹的记录。”；若多家快递同时查到（包括 DHL Express），则需要你手动选择。否则点击“更换”选择“Geniki Taxydromiki”：搜索 Bulgaria、Cyprus 或 Romania 也能找到，输入保加利亚语名称 Геники Тахидромики 同样可以。你也可以直接粘贴完整查询链接，如 `https://taxydromiki.com/track/4829173650`（或 `/en/track/`、`/bg/track/`、`/ro/track/`），Peek 会立刻识别为 Geniki。在“符合这个单号”列表中，位于希腊、保加利亚、塞浦路斯或罗马尼亚的用户会看到 Geniki 排在 Peek 目录中未覆盖该国的快递前面。包含多个用 `-` 连接单号的链接无法跟踪，请逐个粘贴单号。

Geniki 查询页的时间不带时区：Peek 按希腊时间读取，并列出 Geniki 自身的完整历史记录。扫描记录保留 Geniki 的英文原文，如上表所示。表中未列出的扫描也会保留 Geniki 原文，但不单独归入某一阶段。被 Geniki 撤销的扫描会连同撤销记录一起隐藏。

Geniki 不提供预计送达日期、取件点或重量信息，因此 Peek 无法显示。只有过去 60 天内有过扫描的运单，Peek 才会自动识别为 Geniki；更早的单号请点“更换”手动选择 Geniki。你可以让 Peek 通知你每次扫描、仅重要环节，或仅派送当天。在 Geniki 官网查询正常响应期间，当你打开包裹页或派送当天开启通知时，Peek 最多每 10 分钟查询一次，在包裹进入 `Out for Delivery` 当天的希腊时间早上 6 点起最多每 2 分钟查询一次；其余时间每小时查询一次。连续两天无新动态（在“已生成面单”或“已揽收”状态下为 12 小时）后，同样改为每小时查询一次。

## 关于 Geniki Taxydromiki 物流查询的常见问题

### Geniki Taxydromiki 是什么公司？

这是一家创立于 1994 年的希腊快递公司，持有希腊邮政监管机构 EETT 颁发的牌照，在希腊、塞浦路斯、保加利亚和罗马尼亚自有网络派送，并通过合作伙伴发往更多地区。

### 可以在塞浦路斯查询 Geniki Taxydromiki 包裹吗？

可以，在 taxydromiki.com 上用同一运单号查询即可：塞浦路斯属于 Geniki 的自有网络。Geniki 塞浦路斯办事处位于尼科西亚（见上方联系方式）。

### Geniki Taxydromiki 派送需要多久？

Geniki《章程》给出的参考时效为希腊本土 1 至 2 个工作日、海岛 2 个工作日、偏远地区最长 3 个工作日；常见问题写明塞浦路斯境内次工作日送达，塞浦路斯服务页写明希腊与塞浦路斯之间 1 至 2 个工作日。这些时效仅供参考，不作保证。

### Geniki Taxydromiki 的官方邮箱是什么？

info@taxydromiki.gr。任何发件地址不以 @taxydromiki.gr 结尾的邮件均应视为诈骗邮件。

:::sources
- [Geniki Taxydromiki: Home Page](https://taxydromiki.com/en/home/) – 网络覆盖国家、D2SP、BOX NOW 快递柜、单次最多查 10 个单号
- [Geniki Taxydromiki: Track](https://taxydromiki.com/en/track/) – voucher 运单号、查询语言、查无记录仅显示希腊语提示
- [Geniki Taxydromiki: Consumer Obligations Charter](https://taxydromiki.com/en/consumer-obligations-charter/) – SYDETA 编号、EETT 牌照、派送与成年同住人代收、派送次数、改址、寄件人免签选项、快递柜 48 小时、破损保留声明、六个月保管、参考时效、理赔与投诉
- [Geniki Taxydromiki: General Terms of Carriage](https://taxydromiki.com/en/genikoi-oroi-analipsis-metaforas/) – 二次派送、六个月索赔期、两个工作日破损申报
- [Geniki Taxydromiki: Frequently Asked Questions](https://taxydromiki.com/en/erotiseis-kai-apantiseis/) – 希腊与塞浦路斯派送天数、联系门店、20 天申请时限
- [Geniki Taxydromiki: Ερωτήσεις και Απαντήσεις](https://taxydromiki.com/eksypiretisi-pelaton/erotiseis-kai-apantiseis/) – 20 天理赔申请
- [Geniki Taxydromiki: Services in Cyprus](https://taxydromiki.com/en/ypiresies-kyprou/) – 塞浦路斯 D2SP、希腊至塞浦路斯时效
- [Geniki Taxydromiki: Basic Greek Services](https://taxydromiki.com/en/ypiresies-elladas/vasikes-ypiresies-elladas/) – D2SP、海外合作伙伴
- [Geniki Taxydromiki: Contact Us](https://taxydromiki.com/en/our-company/epikoinonia/) – 客服中心、办公时间、邮箱、表单、总部与塞浦路斯办事处
- [Geniki Taxydromiki: Complaint Submission](https://taxydromiki.com/en/ypovoli-paraponon/) – 投诉表单字段
- [Geniki Taxydromiki: Attempted Fraud Using the Brand Name Geniki Taxydromiki](https://taxydromiki.com/en/announcements/prospatheia-eksapatisis-me-chrisi-tis-eponymias-geniki-tachydromiki/) – 仿冒短信、邮件与 Viber 消息、官方邮箱域名
- [Geniki Taxydromiki: Office Network](https://taxydromiki.com/en/diktyo-katastimaton/) – 门店查询
- [Geniki Taxydromiki: Automated Lockers Network](https://taxydromiki.com/en/lockers/) – 快递柜查询、BOX NOW 与 Geniki 快递柜
- [Γενική Ταχυδρομική: Χάρτης Υποχρεώσεων Καταναλωτή](https://taxydromiki.com/chartis-ypochreoseon-katanaloti/) – 希腊语版章程：同住人（«σύνοικο πρόσωπο»）
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Peek 读取范围
:::
