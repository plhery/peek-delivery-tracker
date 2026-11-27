---
title: 捷克邮政（Česká pošta）物流查询：单号、状态与 Balíkovna
description: 查询捷克邮政（Česká pošta）与 Balíkovna 包裹：单号格式与查找位置、英文状态含义、邮局与快递柜保管期、清关与客服。
slug: ceska-posta-czech-post-tracking
published: 2026-11-26
updated: 2026-11-26
---

捷克邮政（Česká pošta，Czech Post）是捷克国家邮政，Balíkovna 是其旗下的包裹品牌。你可以在捷克邮政英文 [Track & Trace 页面](https://www.postaonline.cz/en/trackandtrace)、Pošta Online 应用中查件，或直接将单号粘贴到上方输入框。运单号印在包裹面单上，也出现在捷克邮政的短信/邮件或信箱通知单上。

## 捷克邮政运单号：格式与查找位置

捷克邮政说明其单号由前缀（一般为两位表示邮件类型的字母）、9 或 10 位数字，以及后缀（代表寄件类型的字母或寄出地国家代码）构成，不区分大小写。

| 格式（虚构） | 含义 |
| --- | --- |
| `DR4829173654E` | 捷克国内包裹：两位字母 + 10 位数字 + 一位字母。 |
| `RR482917367CZ` | 从捷克寄出的国际邮件：两位字母 + 9 位数字 + `CZ`。 |
| `RR482917367CN` | 从海外寄入捷克的邮件沿用寄出地邮政编号，以寄出国家代码结尾。 |

如果网店提供了你的联系方式，捷克邮政会通过邮件或短信通知你，邮件中附有 Track & Trace 链接。通知消息里的 6 位取件码并非运单号。全都找不到？请看[如何查找运单号](guide:find-tracking-number)（英文）。

Packeta 单号为 `Z` 加 10 位数字，DHL 捷克伙伴 PPL 为 11 位数字，均不属于捷克邮政，请分别前往 [Packeta](carrier:packeta) 或 [PPL](carrier:ppl) 查询。海外寄入包裹在进入捷克前由寄出地邮政追踪，捷克邮政注明入境前的状态取决于寄出地邮政传递的数据。

## 捷克邮政英文物流状态含义

Track & Trace 提供捷克语与英语版本。以下是其英文表述；地点名称保持捷克语，且每条记录仅含日期不含具体时刻。

| 捷克邮政显示 | 对你的含义 |
| --- | --- |
| `Receipt of data about consignment.` | 寄件人已[提交包裹电子数据](guide:tracking-statuses)（英文），捷克邮政尚未收到实物。 |
| `The consignment was sent for transportation.` | 已收寄发运：第一条带邮局或分拨站名称的记录。 |
| `The consignment is transported.` | 正在捷克邮政网络中运输。 |
| `The consignment is being prepared for delivery.` | 已抵达负责投递的邮局或分拨站。 |
| `The consignment is being delivered.` | 今日派送中。 |
| `The consignment was deposited - addressee not at home.` | 派送时家中无人，已暂存于该行写明的邮局。 |
| `The consignment was deposited to Balíkovna.` | 已送达该行写明的 Balíkovna 自提点或快递柜，等待领取。 |
| `The consignment was sent back to the sender - the consignment was not picked up by the addressee in the set Collection Time.` | 逾期未取，正在退回寄件人。 |
| `The consignment was returned to the sender.` | 已退还至寄件人手中。 |

`SMS alert for the addressee - consignment delivery date.`（及邮件版本）仅记录向你发送了提醒，而 `Geographical data from the delivery attempt.` 仅记录派送员所处位置，两者均不代表包裹移动。`There is no record for a consignment with this posting number.` 表示捷克邮政（暂）未查到该单号；`Czech Post does not display information for this consignment type.` 表示查到了编号但不对该类型邮件展示追踪明细。

## 派送未成功、Balíkovna 自提与邮局取件

**邮局自提。** 如派送时无人签收，邮件将存放在邮局，并通过纸质、短信或邮件发出通知。除非寄件人将保管期缩短为 10 天，否则捷克邮政默认保管 **15 天**。通过 postaonline.cz 的 “Change Delivery Online” 或拨打通知单上的邮局电话，你可以免费办理以下操作（前两项也可在 Pošta Online 应用完成，另注明收费者除外）：

- 提前至少 3 个工作日申请在工作日布拉格时间 08:00–16:00 再次派送；
- 延长保管期至就绪之日起最长 1 个月（除非寄件人禁止，且不适用于 Balík Do ruky）；
- 改投至另一家邮局；
- 付费转寄至捷克境内其他地址。

**Balíkovna 自提。** 网点涵盖邮局柜台、合作商店及自助快递柜（如 AlzaBox、OX Point 和 Penguin Box）。在邮局或合作门店保管 **7 个自然日**，在自助快递柜保管至就绪后**第二个工作日早上 07:00**。Balíkovna 保管期不可延长；到期前会发邮件或短信提醒，逾期退回寄件人。若柜门无法打开，请拨打柜身电话或 Balíkovna 热线 `218 218 218`。

**上门派送。** Balíkovna 会在派送当天发送短信且司机提前致电；无人签收则转存最近的自提点。大多数国内包裹可凭 6 位取件码加收件人姓名或单号在任意邮局免证件领取。

## 捷克邮政包裹停滞、延误、遗失或破损

若物流停止更新，请参阅[物流停止更新的原因](guide:tracking-not-updating)（英文）。来自欧盟境外的包裹可能需要清关：捷克邮政会通过邮件、短信或信函要求你在 `postaonline.cz/celni-rizeni` 填表申报，并于派送时收取关税与清关费；详见[海关滞留包裹指南](guide:customs)（英文）。

遗失或延误须由寄件人发起索赔（捷克国内交寄起 1 年内、国际件 6 个月内），请先联系网店。收件人若发现破损或内件缺失，须在收货后 **2 个工作日**内携带包装或照片前往任意邮局申报（捷克邮政提示国际件时限可能更短）。若送达当场即可看出破损，可要求将包裹存放在邮局并在那里申报，或直接拒收——一旦签收即丧失索赔机会。若投诉未获支持或 30 天内未答复，可向捷克电信管理局（ČTÚ）申诉。

## 捷克邮政客服与联系方式

捷克邮政官网公布以下渠道：

- **电话：** `+420 210 123 456`，布拉格时间周一至周五 08:00–18:00；语音助手 Eliška 会先接听，无法解决时转人工坐席。
- **电子邮箱：** `info@cpost.cz`（咨询与投诉）。
- **线下网点：** 前往任意捷克邮政局办理。
- **Balíkovna：** 通过 balikovna.cz 联系表单提交。

> 捷克邮政提醒防范冒用其名义发送“包裹滞留海关需缴费”的诈骗短信与邮件。捷克邮政绝不会通过消息链接要求付款、登录或填写个人信息，请一律自行前往官网或官方应用核对状态。

## 使用 Peek 跟踪捷克邮政包裹

[Peek](/) 会直接识别以 `B`、`D` 或 `NB`、`NP`、`NR` 开头的捷克邮政国内单号；该格式下的其他字母不对应任何承运商，请点击“更换”手动选择“Česká pošta”。对于以 `CZ` 结尾且校验位正确的国际单号，Peek 会同时询问捷克邮政和 [Chronopost](carrier:chronopost)，查有记录时显示“Česká pošta 有这个包裹的记录。”，未查到前显示“……暂未找到”。海外邮政寄入捷克的单号会先在寄出地邮政跟踪，待寄出地标明发往捷克（或未标目的地且你在捷克）且捷克邮政出现不早于寄出地最新扫描的记录后，交由捷克邮政接续跟踪；否则请点击“更换”。也可以直接粘贴含 `/trackandtrace/-/zasilka/` 加单号的 `postaonline.cz` 链接。

由于捷克邮政每条记录只有日期没有时刻，Peek 会同时向捷克邮政和[通用物流查询服务](guide:universal-tracker)（英文）获取带时间戳的完整轨迹：若通用服务补充了更多步骤或更新状态，Peek 会优先展示并每 6 小时回查一次捷克邮政；若通用服务仅补齐了同一步骤的时刻，Peek 会沿用这些时间并继续首选捷克邮政；若无补充来源，则按日期展示捷克邮政自身步骤。状态保留捷克邮政英文原文，并可显示包裹暂存的邮局或 Balíkovna 网点名称（不含详细地址）及重量。捷克邮政不提供预计送达日；邮编、货到付款信息与件数均会被过滤，Peek 可以在状态变化时通知你。

## 关于捷克邮政物流查询的问题

### Česká pošta 是什么？

Česká pošta 是捷克国家邮政（英文 Czech Post），Balíkovna 是其旗下的包裹派送与自提品牌。

### 可以用英文查询捷克邮政吗？

可以。`postaonline.cz` 的 Track & Trace 提供英文版本，且支持用逗号或分号分隔一次查询最多 20 个单号（地点名称仍保持捷克语）。

### 捷克邮政送货需要多长时间？

Balíkovna 表示大多数包裹在寄出后次工作日即可备妥待取，上门包裹通常最快次日送达。

:::sources
- [Česká pošta: Track & Trace](https://www.postaonline.cz/en/trackandtrace) – 范例单号、20 个单号、英语与捷克语
- [Česká pošta: All trackable services and their codes](https://www.ceskaposta.cz/en/rady-a-tipy/pri-podani/seznam-druhu-zasilek-s-moznosti-sledovani) – 单号格式、查找位置、境外寄入的邮件
- [Česká pošta: Notification of parcels](https://www.ceskaposta.cz/en/sluzby/ostatni-sluzby/avizovani-balikovych-zasilek) – 免费的邮件与短信通知
- [Česká pošta: What to do if you are not at home](https://www.ceskaposta.cz/en/rady-a-tipy/pri-dodani/co-delat-kdyz-vas-dorucovatel-nezastihne-doma) – 再次派送、保管、改投其他邮局、转寄
- [Česká pošta: Services at delivery](https://www.ceskaposta.cz/en/sluzby/online-sluzby/online-sluzby-pri-dodani) – 15 天、三个工作日、Balík Do ruky
- [Česká pošta: Parcel pickup based on code](https://www.ceskaposta.cz/en/sluzby/zasilky-cr/ostatni-zasilky/vydej-baliku-na-kod) – 6 位取件码、无需证件
- [Česká pošta: Claims and complaints](https://www.ceskaposta.cz/en/rady-a-tipy/jine/reklamace-a-stiznosti) – 索赔、期限、申诉
- [Česká pošta: FAQs on customs clearance](https://www.ceskaposta.cz/en/sluzby/zasilky-zahranicni/celni-rizeni/faq-caste-otazky-k-celnimu-rizeni) – 清关表单、派送时收费
- [Česká pošta: Client support](https://www.ceskaposta.cz/en/kontakty/pomoc-klientum) – 电话、服务时间、Eliška、邮箱
- [Česká pošta: Contacts](https://www.postaonline.cz/en/kontakty) – +420 210 123 456、服务时间
- [Česká pošta: Security Warning](https://www.ceskaposta.cz/en/o-spolecnosti/bezpecnostni-informace/bezpecnostni-upozorneni) – 绝不通过链接索取信息
- [Česká pošta: Aktuální informace](https://www.ceskaposta.cz/o-spolecnosti/bezpecnostni-informace/aktualni-informace) – 虚假短信与邮件提醒
- [Česká pošta: Poštovní podmínky](https://www.postaonline.cz/documents/10180/282441/zps.pdf) – 通知单、15 天与 30 天
- [Balíkovna: Homepage](https://www.balikovna.cz/en/) – 网点、次日送达、快递柜
- [Balíkovna: FAQ](https://www.balikovna.cz/en/faq) – 7 天、快递柜、派送未成功、218 218 218
- [Balíkovna: Všeobecné podmínky](https://www.balikovna.cz/documents/20124/15302839/Balikovna-VPPS.pdf) – 保管、派送未成功、退回
- [Portál veřejné správy: Objection to a postal complaint](https://portal.gov.cz/sluzby-vs/sluzba-S2130) – ČTÚ、30 天
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Peek 读取的内容
:::
