---
title: 芬兰邮政（Posti）查询：JJFI 单号、状态与自提柜
description: 查询芬兰邮政（Posti）包裹：了解 JJFI 与 FI 国际单号格式、英文物流状态对照、自提柜保管与延期费用、清关手续及客服电话。
slug: posti-fenlan-kuaidi-chaxun
published: 2026-11-25
updated: 2026-11-25
---

芬兰邮政（Posti）是芬兰的国家邮政和包裹承运公司。你可以在 [Posti 查件页面](https://www.posti.fi/en/tracking)或官方 OmaPosti 应用中输入邮件编号（item ID）查件，也可以直接把单号粘贴到上方查询框中。单号由寄件人提供，也会随 Posti 的到件通知出现在 OmaPosti、短信或邮件里。

## Posti 快递单号：JJFI 邮件编号

Posti 将运单号称为 “item ID”（有时写作 “shipment ID”），自有包裹以 `JJFI` 开头。Posti 网站顶部提供英文切换（“EN”）。

| 单号（虚构示例） | 说明 |
| --- | --- |
| `JJFI63482917465283914` | Posti 自有包裹：`JJFI` 后接 17 位数字，共 21 位字符。 |
| `RR482917367FI` | 在芬兰交寄的挂号或可追踪邮件，采用国际邮政格式：2 位字母、9 位数字加 `FI`。 |
| `RR482917367CN` | 从境外寄来的包裹沿用原寄国单号（此例以 `CN` 结尾即中国邮政），Posti 用同一单号继续追踪。 |
| `00064382917465283912` | 20 位 SSCC 条码，前三位之后的 `64` 是芬兰国家前缀。 |

如果寄件人向 Posti 提供了你的手机号，包裹会自己出现在 OmaPosti 应用中；否则需要手动添加。Posti 查件系统只认精确单号，不能按姓名或地址查询。

取件码和自提柜开柜码（6 位或 9 位字符）都不是快递单号。以 `JJEE` 开头的单号属于爱沙尼亚邮政 [Omniva](carrier:omniva)，以 `SE` 结尾则属于瑞典 [PostNord](carrier:postnord)。不确定属于哪家快递？参见[快递单号格式说明](guide:tracking-number-formats)（英文）。

## Posti 英文物流状态含义

Posti 查件页会根据所选语言以英语、芬兰语或瑞典语显示状态，并常附一行步骤说明。以下为包裹通常会遇到的英文文案，大致按出现顺序排列：

| Posti 英文状态 | 对你的含义 |
| --- | --- |
| `We have received information about an upcoming delivery from the sender` | 寄件人已预报包裹，Posti 尚未拿到实物。 |
| `Item received for transport` | Posti 已收到包裹并开始承运。 |
| `Item is in transport in country of origin.` | 已在原寄国发运，暂无预计送达时间。 |
| `Item arrived in the destination country.` | 已抵达芬兰，接下来由 Posti 派送。 |
| `The item is in sorting` | 正在分拣中心处理；包裹通常会经过一到两次分拣，长途件次数更多。 |
| `Item accepted from transport` | 仍在转运途中；尽管措辞如此，但并未签收。 |
| `Item is routed to an outlet because the locker was full.` | 所选快递柜已满，已改送至 Posti 服务网点。 |
| `Item is in delivery transportation.` | 已装上派送车发往你的地址，Peek 显示为“派送中”。 |
| `Item is ready for a pick up` | 已待取件，到件通知中写有地点与取件码。 |
| `We sent the recipient a text message about the item` | Posti 向你发送了短信，包裹本身未发生移动。 |
| `The item has been delivered` | 已签收送达（包含自提取走）。 |
| `Item delivered to the recipient.` | 已送达收件人。 |

`Item accepted from transport` 常让人误解：Posti 说明它只是运输标记，派送仍在进行，并未交到收件人手中。境外节点在所有语言版本下地点均显示芬兰语 `ULKOMAILLA`（意为“在境外”）。

## Posti 自提柜、取件与派送未成功

**到件通知。** 包裹送达 Parcel Locker 自提柜或服务点后，Posti 会通过 OmaPosti、短信和邮件发送地点与开柜码。请在 5 天内领取。部分包裹保管期更长，例如境外入境件通常为 14 天、纸质信函通知的包裹为 9 天，请以通知上的最晚取件日期为准。

**延长取件期。** 在 OmaPosti 中使用 “Extend pickup time” 可自原截止日起最多延长 14 天；Posti 英文页写费用为 1.90 欧元，芬兰语页写为每天 1.90 欧元。

**自提柜取件。** 输入开柜码即可取件（键盘式柜机先按 C、再输密码、最后按 OK）。任何持有开柜码的人都可代取，无需身份证件；挂号件和保价件则需授权委托书。无取件码的邮件存放在 Posti 网点，取件需携带邮件编号和身份证件。

**改送网点原因。** 尺寸超出柜格、需要本人当面签收或手机号有误的包裹会被改送至 Posti 服务网点。

**上门派送。** 若 Posti 无法送货上门，会将包裹送往最近的自提点并通过 OmaPosti、短信或邮件通知你。小包裹（Small parcel）无人签收时会放在门口或投入信箱。在 OmaPosti 中仅当包裹仍在寄件人手中时才能改选上门派送。

**变更地址或网点。** 只有寄件人可以修改收件地址；客服可收费将包裹转至另一自提点。超期无人领取的包裹将退回寄件人。

## Posti 包裹延误、丢失、破损或清关

**无更新。** 境外包裹抵达芬兰之前，Posti 无法预估送达时间。参见[物流长时间不更新怎么办](guide:tracking-not-updating)（英文）。

**丢失或延误。** 如果轨迹查不到包裹下落，可联系 Posti 客服发起查单请求（寄件人或收件人均可提交）。若确认找不回，则由寄件人申请赔付，因为 Posti 只赔付支付运费的一方（几乎总是寄件人）。根据芬兰法律，网店对运输过程担责，通常会为你补发。芬兰消费者保护机构 KKV 将国内包裹在约定交货期满 14 天后（若未约定交货期则为揽收满 28 天后）认定为丢失。

**显示已签收但未收到。** 小包裹请先检查信箱，随后联系客服。参考[显示已签收但未收到包裹](guide:delivered-not-received)（英文）。

**破损。** 交接时立即告知司机，拍照留证，填写 Posti 破损申报表单并通知寄件人。

**清关。** 来自欧盟以外（或奥兰群岛、加那利群岛等欧盟税区以外领地）的包裹可能需要在芬兰海关网站或 OmaPosti 完成清关后才能派送，Posti 仓库最多保管 20 天，超期退回。如需在清关时缴税，Posti 会加收 3.10 欧元手续费（网点办理为 3.30 欧元），须在 Posti 网站支付。自 2026 年 11 月 1 日起，芬兰海关对网购商品还按每类商品收取 2 欧元费用。货值低于 150 欧元且结账时已由商家代收增值税（IOSS）的网购包裹由寄件人完成清关，收件人到货无需再付费；但如果缺少关税和手续费的支付信息，Posti 会将包裹退回寄件人。最新收费标准见 [Posti 清关页面](https://www.posti.fi/en/receiving/customs-clearance)。

> Posti 绝不会发送付费链接要求缴纳关税或手续费。诈骗信息甚至可能夹带真实单号，请直接删除，切勿点开链接。

## Posti 客服电话与联系方式

以下渠道来自 posti.fi：

- **电话：** 芬兰境内 0100 5577，境外拨打 +358 100 5577（按本地市话或移动资费计费）。营业时间见 [Posti 客服页面](https://www.posti.fi/en/customer-service)。
- **在线聊天：** 在 OmaPosti 应用内使用。
- **在线表单：** 赔付申请、破损申报，以及无法投递包裹表单（当 Posti 既无法送达给你也无法退回寄件人时）。

Posti 不对外提供客服邮箱。

## 使用 Peek 追踪 Posti 包裹

[Peek](/) 会立刻把 `JJFI` 单号识别为 Posti。包含 `/tracking/`、`/seuranta/` 或 `/uppfoljning/` 后接单号的 posti.fi 查件链接（即使前面带有 `/en/` 等语言路径）也会对任意单号直接指定 Posti，全程无需邮编。

对于以 `FI` 结尾的邮政单号，Peek 会先询问 Posti 及 Chronopost，Posti 有记录即认定为 Posti；暂未查到时显示“…… 暂未找到。添加后 Peek 会继续查询。”登录 Peek 账号添加后，前 6 小时每小时查询一次，随后逐步拉长间隔；未登录账号则在 6 小时后停止查询，除非你开启“找到后通知我”，之后会每天查询一次直至添加满 30 天。对于上述 20 位 SSCC 条码，Peek 会同时查询 Posti、Matkahuolto、Swiss Post Cargo、PostNord 和 NZ Post。校验位有误时可点“更换”手动选择 Posti。境外单号可在“更换”或粘贴 posti.fi 链接后交由 Posti 追踪；否则 Peek 会先在原寄国邮政追踪，待原寄国邮政标明发往芬兰（或未标明目的地而你在芬兰追踪）时，Peek 用同一单号同步询问 Posti，只要 Posti 出现不早于原寄国最新节点的扫描便转到 Posti 跟踪，并保留先前轨迹。

在爱沙尼亚、拉脱维亚和立陶宛，Posti 以 SmartPosti 品牌运营：在 Peek 快递列表中搜索该名称也能找到 Posti（标注“又名 SmartPosti”），粘贴 smartposti.ee 或 itella.ee（及其 .lv、.lt 域名）查件链接同样直接识别为 Posti。Peek 仅在 Posti 精确返回一票与你输入单号完全匹配的快件时才采纳结果，因此某些 SmartPosti 单号可能查不到，此时请直接前往 smartposti.ee 查询。

Peek 以英文读取 Posti 的查件信息，展示每条状态及 Posti 的解释行、扫描所在城镇、重量、尺寸，以及自提点名称与地址。在你打开包裹页面期间，或在派送当天开启提醒时，Peek 最高每 10 分钟检查一次（在 Posti 扫描包裹进入派送的当天，只要 Posti 自己的查件系统正常响应，自当地时间早上 6 点起每 2 分钟一次）；其余时间每小时检查一次。Peek 会将每个节点标注在地图上，并可以在状态变化时通知你。若有其他承运商接手派送，Peek 会按实际派送方的节奏查询。时间按你设备的时区显示；在 peektracker.com 及 iPhone、Android 应用中，轨迹底部会写明“时间按你所在的时区显示：”及对应时区名称。连续两天没有新动态后（仍停留在“已生成面单”或“已揽收”时为 12 小时后），Peek 改为每小时检查一次。Peek 会略去取件码与你的收件地址，且不显示预计送达时间或寄件人。Peek 不追踪对应多个包裹的总单号，过旧的包裹也会显示查不到。

## Posti 快递查询常见问题

### 如何用英文查询 Posti 包裹？

打开 posti.fi/en/tracking 并输入邮件编号，物流状态便会以英文显示。

### Posti 在自提柜里会替我保管包裹多久？

Posti 要求在 5 天内取走，具体截止日期以到件通知为准。在 OmaPosti 中可付费最多延长 14 天（英文页写 1.90 欧元，芬兰语页写每天 1.90 欧元）。

### Posti 派送一般需要多久？

小包裹（Small parcel）在 Posti 收件后 2 至 5 个工作日送达；其他包裹请查看 Posti 查件页给出的预计送达时间。

### Posti 客服有在线聊天吗？

有，可在 OmaPosti 应用内使用在线聊天。

:::sources
- [Posti: Picking up an item](https://www.posti.fi/en/receiving/picking-up-an-item) – JJFI 单号、到件通知、保管期（境外 14 天、信函通知 9 天）、延期、自提柜、取件码、授权书、退件、电话与在线聊天
- [Posti: Lähetyksen noutaminen](https://www.posti.fi/vastaanottaminen/lahetyksen-noutaminen) – 芬兰语说明：延期每天 1.90 欧元
- [Posti: Track item](https://www.posti.fi/en/tracking) – 状态文案、步骤解释行、ULKOMAILLA 与多语言切换
- [Posti: Home delivery](https://www.posti.fi/en/receiving/home-delivery) – 派送未成功、小包裹、派送时效与已签收未收到
- [Posti: Customs clearance](https://www.posti.fi/en/receiving/customs-clearance) – 清关手续费、芬兰海关、自 2026 年 11 月 1 日起 2 欧元费用、IOSS 寄件人代清关及缺少付款信息退回
- [Posti: Ordering from abroad and customs clearance](https://www.posti.fi/en/receiving/customs-clearance/ordering-from-abroard-and-custom-clearance) – 仓库保管 20 天与 OmaPosti
- [Posti: What does Posti compensate for and to whom](https://www.posti.fi/en/customer-service/damage-situations/what-does-posti-compensate-for-and-to-whom) – 查单请求、赔付对象与破损处理
- [Posti: Claim for compensation](https://www.posti.fi/en/customer-service/damage-situations/claim-for-compensation) – 赔付申请表
- [Posti: Report a damaged item](https://www.posti.fi/en/customer-service/damage-situations/report-damage) – 破损申报表与通知寄件人
- [Posti: Undeliverable parcel](https://www.posti.fi/en/customer-service/damage-situations/undeliverable-parcel) – 无法投递包裹表单
- [Posti: Security and phishing messages](https://www.posti.fi/en/customer-service/security-and-phishing-messages) – 防范诈骗信息与无付款链接声明
- [Posti: Privacy statement for Item Tracking](https://www.posti.fi/en/customer-service/terms-and-statements/data-protection/privacy-statements/privacy-statement-for-item-tracking) – 仅凭精确单号查询及通话资费
- [Posti: Changes to parcel storage periods](https://www.posti.fi/en/latest-news-at-posti/changes-to-parcel-storage-periods-to-improve-availability-at-posti-parcel-lockers-11-8-2025) – 5 天保管期说明
- [Posti: Customer Service](https://www.posti.fi/en/customer-service) – 客服页面与营业时间
- [SmartPosti: Meist (About us)](https://www.smartposti.ee/ariklient/meist) – 隶属 Posti 集团及波罗的海三国 SmartPosti 品牌
- [SmartPosti: Saadetise jälgimine](https://www.smartposti.ee/saadetise-jalgimine) – SmartPosti 查件页
- [KKV: Postal and parcel services](https://www.kkv.fi/en/consumer-affairs/telephone-and-internet-subscriptions-postal-and-parcel-services/postal-and-parcel-services/) – 芬兰消费者保护局丢失认定天数
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Peek 读取和显示的信息、识别规则
:::
