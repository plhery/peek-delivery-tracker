---
title: Allegro One 物流查询：One Box 自提柜与状态
description: 查询波兰 Allegro One 包裹：单号查找与格式、英文状态中文说明、One Box 自提柜与 One Punkt 取件期限、延迟投诉与客服。
slug: allegro-one-tracking
published: 2026-11-24
updated: 2026-11-24
---

Allegro One 是波兰电商平台 Allegro 自营的配送网络，包含 Allegro One Box 自提柜、Allegro One Punkt 取件点和 Allegro One Kurier 送货上门，全部归属 Allegro Delivery 体系。你可以在 Allegro 中的“Moje przesyłki”（我的包裹）或 Allegro 官方物流页面查件，也可以直接把单号粘贴到上方输入框中。单号位于你的购买详情、Allegro 应用以及单号添加后 Allegro 发出的邮件中。

## 在哪里查找 Allegro 运单号

Allegro 会在以下位置显示承运商、包裹单号及最新状态：

- **“Moje przesyłki”**（allegro.pl 网站或应用内），从等待发货直到送达全程可见。
- **“Moje zakupy”**（我的购买），在对应订单的详情页面中。
- **电子邮件：** 卖家录入单号后你会收到通知邮件。

Peek 支持通过 Allegro One 追踪以下单号格式（字符均为虚构）：

| 格式 | 归属服务 |
| --- | --- |
| `A0K4829105` | Allegro One：`A0` 加 8 位字母或数字，共 10 位。 |
| `AD0K4829105736491` | Allegro Delivery：`AD0` 加 14 位数字或辅音字母，共 17 位，同样归在 Allegro One 名下。 |
| `ADK4829105` | 早期 Allegro Delivery 格式，部分退货仍在使用：`AD` 加 8 位字母或数字。 |
| `ALE482910573649` | 旧版 Allegro One 格式：`ALE` 加 12 位数字。 |

还没有单号？由卖家录入订单，除非与 Allegro 系统对接的承运商自动回传。另请参阅[如何查找运单号](guide:find-tracking-number)（英文）。

Allegro Delivery 同时也由 ORLEN Paczka、DHL 和 DPD 合作承运。这些通过 Allegro Delivery 发出的包裹使用 Allegro 自有单号，在 Allegro 系统内统一追踪，合作伙伴的扫描也会以相同文案显示。如果卖家未走 Allegro Delivery，而是使用自有协议通过某快递发货，则会使用该快递公司自己的单号，需在对应快递（例如 ORLEN Paczka 或 DPD Poland）下查询。

## Allegro One 物流状态中文说明

Allegro 的物流系统以波兰语或英语提供每条扫描文案。以下按包裹依次经历的顺序列出英文原文（除非你将 Peek 设为波兰语，否则 Peek 会显示这些英文文案）：

| Allegro One 状态原文 | 对你的含义 |
| --- | --- |
| `Parcel has been prepared by the sender` | 已生成面单。Allegro One 尚未收到包裹。 |
| `Parcel has been accepted at the sorting center` | 包裹已到达分拣中心。 |
| `Parcel has been dispatched to its destination point` | 正在发往你所在地的自提柜、取件点或派送站点。 |
| `Parcel has been released for delivery` | 正在派送中（发往 One Box 自提柜时也会显示此状态）。 |
| `No space in the parcel locker` | 自提柜已满导致投放失败，快递员将包裹带回。 |
| `Parcel is awaiting pick-up` | 包裹已存入 One Box 或 One Punkt，等待你领取。 |
| `Parcel is awaiting pick-up — pick-up deadline is about to expire` | 取件提醒：你的保管期限即将到期。 |
| `Time for parcel pick-up has expired` | 逾期未取：包裹将退回卖家。 |
| `Parcel has been returned to the sender` | 正在退回卖家途中。尽管字面写着已退回，卖家此时尚未收到。 |
| `Parcel has been delivered` | 已送达，或已由你本人取走（见下文）。 |

你从 One Box 自提柜取走包裹后，同样会显示 `Parcel has been delivered`，没有单独的“已取件”记录。

部分提示看起来比实际情况更严重：`Parcel may be delivered with delay` 和 `Parcel delivery time has been arranged with the recipient` 仅属于提醒通知，包裹仍在正常流转；`Parcel has been redirected to a different point` 是你自行更改取件点后的记录；即便出现 `No parcel` 甚至 `Parcel has been lost`，后续仍可能出现重新找到包裹的新扫描。

## One Box、One Punkt 与 One Kurier 配送规则

**取件通知。** 包裹送达自提柜或取件点后，Allegro 或其合作伙伴会通过邮件、短信或 Allegro 应用通知你具体取件地点和截止时间。

**在 One Box 自提柜：** 输入取件码（单独输入或搭配手机号）、扫描邮件/短信里的二维码，或直接用 Allegro 应用开柜。**在 One Punkt 取件点：** 向店员提供包裹单号和取件码。

**保管期限。** One Box 自提柜保管 2 个自然日（48 小时），One Punkt 取件点保管 2 个工作日。在部分自提柜和网点，Allegro 可能允许按单独条款延长保管时间，此时状态会变为 `Time for pick-up has been extended`。逾期未取将退回卖家。

**自提柜已满或停用？** Allegro 可能会将包裹改投至 3 公里内（农村地区为 6 公里内）的另一处自提柜，并通知你新地址及取件期限。

**One Kurier 上门派送**在指定城市送货上门。若尺寸允许，Allegro 可能允许你对送货上门包裹申请一次改派，转至同一派送站点覆盖范围内的支持改派的自提柜或取件点。你可在包裹交寄后至派送前在 Allegro 应用或 allegro.pl 上提交申请；改派会增加 1 个工作日，且货到付款包裹不可改派。若上门无人应答，快递员会留下通知（`Notice`）：请在 48 小时内前往指定地点自提，或预约付费重新派送。

## Allegro One 包裹延误、丢失或破损

**等待过久。** 先查看“Moje przesyłki”。如果那里完全没有这个包裹，请向卖家索取运单号。另见[物流不更新怎么办](guide:tracking-not-updating)（英文）。

**卖家不配合。** 可发起“problem z zakupem”（购买问题申报），付款一小时后即可提交。卖家有 24 小时回复你的首条消息（不含周六、周日和法定节假日）；如需 Allegro 介入，请点击“Pomoc Allegro”（Allegro 帮助）。对于延误、丢件或漏发商品，卖家有 7 天解决期限。如果届时 Allegro 未确认问题已解决，会向你核实；若你表示仍未解决，Allegro 会引导你通过 Allegro Ochrona Kupujących（买家保护）申请赔付。在 Allegro Lokalnie 以及使用 Magazyn Allegro（Allegro 仓库）的卖家处，仍通过开启“Dyskusja”（争议讨论）处理。

**显示已签收但未收到。** 先确认同住人、邻居或公司前台是否代收，再参阅[显示已签收但未收到](guide:delivered-not-received)（英文）。

**丢失、破损或延误投诉。** 作为 Allegro Delivery 的运营方，Allegro 直接受理配送投诉：登录 Allegro 账号在线提交、核验包裹信息提交，或按邮政法允许的其他方式提交。只要包裹已送达给你，或在未送达的情况下卖家将索赔权转让给你，你都可以提出投诉。隐蔽损毁或内件短少须在签收后 7 天内申报；其他情形须在寄出后 12 个月内申报。Allegro 最迟会在 30 天内答复。

## Allegro One 客服与联系方式

所有 Allegro Delivery 包裹（含 Allegro One）的客服均由 Allegro 统一负责：

- **配送投诉：** 按上述流程向 Allegro 提交。
- **One Box 自提柜无法打开：** 拨打自提柜机身上标注的技术支持热线。
- **联系卖家：** 遇到包裹延误时，按上文步骤发起购买问题申报。

## 使用 Peek 查询 Allegro One 包裹

对于 `A0` 或 `AD` 加 8 位字母/数字以及 17 位 `AD0` 格式的单号，[Peek](/) 会直接识别为 Allegro One。若是 `ALE` 加 12 位数字，请点击“更换”手动选择 Allegro One。

Peek 会以英文读取 Allegro 物流（如果你将 Peek 界面设为波兰语，则显示波兰语），并按原文展示物流历史及签收时间。由于 Allegro 不返回具体地点信息，页面不会在地图上标点，也不显示取件点名称、预计送达日、重量或寄件人。

当你打开包裹页面查看，或在派送当天开启通知时，只要 Allegro One 官方查询正常响应，Peek 最高每 10 分钟检查一次，在 Allegro One 扫描包裹进入派送中的当天早上 6 点（当地时间）起最高每 2 分钟检查一次（通过物流查询服务时，在派送当天打开页面或开启提醒时最高每 15 分钟检查一次）；其余时间每小时检查一次。你可以按每次扫描、仅重要环节或仅派送当天接收通知。若连续两天无新动态（包裹仍处于“已生成面单”或“已揽收”状态时为 12 小时），Peek 会转为每小时检查一次。

## Allegro One 物流查询常见问题

### Allegro One Box 是什么？

它是 Allegro 自营的自提柜，与 One Punkt 取件点和 One Kurier 上门快递共同组成 Allegro One。包裹通过 Allegro Delivery 发往柜机，由 Allegro One 自身或 DPD、DHL 和 ORLEN Paczka 承运。

### 包裹在 Allegro One Box 保管多久？

保管 2 个自然日（48 小时）；在部分柜机 Allegro 可能允许你延长保管时间。One Punkt 取件点则保管 2 个工作日。

### Allegro One 运费多少钱？

具体运费由卖家在商品运费表中设定。根据现行条款，拥有 Allegro Smart! 会员时，向同一卖家购满 49.90 波兰兹罗提即可免除自提柜、取件点或上门快递运费。

:::sources
- [Allegro: The Allegro Delivery program, information for sellers](https://help.allegro.com/en/sell/a/the-allegro-delivery-program-information-for-sellers-MR7exl1wYS4) – One Box、One Punkt、部分城市 One Kurier、合作伙伴、保管期、自提柜热线、客服与运费表
- [Allegro: How to correctly dispatch parcels with orders](https://help.allegro.com/en/sell/a/how-to-correctly-dispatch-parcels-with-orders-a-guide-for-sellers-GRv326wwXF1) – 买家查件入口、单号自动回传与承运商追踪
- [Allegro: Regulamin Allegro](https://policies.allegro.com/doc/f86a1a793dc4f5bc4b695b0ff0d54d974badab4169f5aaaddad89585c2c90690) – 含承运商、单号与查询链接的通知邮件
- [Allegro: Regulamin Allegro Delivery](https://policies.allegro.com/doc/e5cf7a904b3b08d908db62830a8bdac17b22cf354baf748e7853adff00fea84c) – 取件通知、取件码/二维码/应用开柜、替代自提柜、延期、改派、通知单、退回卖家与投诉规则
- [Allegro: How to solve problems with purchases in the Messages tab in the Sales Center or in the Message Center](https://help.allegro.com/en/sell/a/how-to-solve-problems-with-purchases-in-the-messages-tab-in-the-sales-center-or-in-the-message-center-Bv0zrVqDVIX) – 自 2026 年 10 月 28 日起以购买问题替代争议讨论、付款一小时后可发起、24 小时回复与 7 天解决期限、买家保护及 Allegro Lokalnie/Magazyn Allegro 规则
- [Allegro: Jak rozwiązywać problemy z zakupem w zakładce Wiadomości w Sales Center lub w Centrum Wiadomości](https://help.allegro.com/pl/sell/a/jak-rozwiazywac-problemy-z-zakupem-w-zakladce-wiadomosci-w-sales-center-lub-w-centrum-wiadomosci-x5ZVrvzXPuP) – 付款一小时后开启及“Pomoc Allegro”按钮机制
- [Allegro: We changed the Allegro Smart! terms and conditions for buyers](https://help.allegro.com/en/sell/a/we-changed-the-allegro-smart-terms-conditions-for-buyers-wwkzRYxRdio) – 单一卖家满 49.90 兹罗提免运费规则
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – 单号格式、状态文案及其语言、合作快递以 Allegro 单号承运的包裹、Peek 读取的内容
:::
