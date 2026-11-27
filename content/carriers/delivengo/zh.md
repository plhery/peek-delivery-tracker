---
title: Delivengo 物流查询：追踪法国邮政出口国际小包
description: 查询法国邮政（La Poste）Delivengo 出口小包：了解 FR 结尾单号、状态阶段、目的国邮政派送以及物流停滞时的求助渠道。
slug: delivengo-tracking
published: 2026-11-27
updated: 2026-11-27
---

Delivengo 是 [法国邮政（La Poste）](carrier:la-poste) 面向小型国际包裹的出口寄递服务。如果法国商家选用了 Delivengo，你的包裹会由法国邮政运离法国，再由你所在国家的邮政完成末端派送。你可以使用商家提供的单号在法国邮政官网 laposte.fr 上查询，或直接粘贴到上方输入框中。

## 你的 Delivengo 追踪单号

Delivengo 单号采用国际邮政标准格式：2 位字母、9 位数字加 `FR`，例如虚构示例 `LD482910576FR`。

**在哪里找单号。** 由商家提供，通常位于商家的发货邮件中。MyDelivengo 网站（mydelivengo.laposte.fr）是商家专用后台，并非收件人查单入口。找不到单号请参阅 [如何查找追踪单号](guide:find-tracking-number)（英文）。

**外观相近的单号**（均为虚构示例）：

| 格式 | 归属承运商 |
| --- | --- |
| `LD482910576FR` | 法国邮政：Delivengo、Colissimo 及其他法国邮政邮件共用这一格式。 |
| `LD482910576GB` | 末尾字母为其他国家代码时属于该国邮政（此处为 [英国皇家邮政 Royal Mail](carrier:royal-mail)），不属于 Delivengo。 |

最后两位字母表明签发单号的邮政机构。仅凭 `…FR` 单号本身看不出它究竟是 Delivengo 还是其他法国邮政产品，但这并无影响，因为查询通道完全相同。此类单号带有校验位，输错一位就会失效导致 Peek 无法识别，建议直接复制粘贴。

## Delivengo 物流状态含义解析

法国邮政追踪系统对 Delivengo 单号的处理方式与其他法国邮政件一致。Peek 会将每条法国邮政扫描归类到对应阶段，各阶段含义如下：

| Peek 显示阶段 | 法国邮政扫描含义 |
| --- | --- |
| `Label created` | 运单已预报：商家已生成面单，法国邮政尚未收到包裹实物。 |
| `Received by carrier` | 法国邮政已收寄接管该包裹。 |
| `In transit` | 包裹正在转运：在法国境内流转、准备出口离境，或已抵达你所在国家并正在转运。 |
| `At customs` | 已进入海关查验，放行后恢复为 `In transit`。 |
| `Delivered` | 已送达。如未收到，请参阅 [显示已送达却未收到](guide:delivered-not-received)（英文）。 |
| `Needs attention` | 出现异常，如破损、拒收、地址有误或正退回商家。法国邮政扫描通常会注明原因（地址不准确、超期未领或进口单证不齐）。 |
| `Returned to sender` | 包裹已退回商家。 |

出口离境后由你所在国家邮政接手末端派送，此时法国邮政轨迹可能不再更新，你看到的最后一条记录可能停在离开法国或抵达目的国。

## 末端派送与错过派送

**谁负责派送。** 由你所在国家的邮政机构完成末端派送，而不是法国邮政。

**如果当时不在家。** 通知方式、保管天数及是否再次投递均遵循你当地邮政的规则，而非法国邮政。详见 [错过派送](guide:missed-delivery)（英文）。

**关税与清关。** 欧盟内部寄递（例如从法国寄往爱尔兰）不征收关税：欧盟规定货物在欧盟成员国间流通免征关税。寄往欧盟以外地区（如英国、美国、加拿大或澳大利亚）则适用目的国关税规则，请参阅 [包裹被海关扣留指南](guide:customs)（英文）。

## Delivengo 包裹延误、停滞或丢失

包裹由商家通过其 Delivengo 账户发出，因此请优先联系商家。

**多日无更新。** 出口之后当地邮政接手派送时，法国邮政记录常会沉寂。Peek 也能同步查询部分目的国邮政（见下文）。若一直毫无进展，请联系商家。常见原因见 [物流停止更新](guide:tracking-not-updating)（英文）。

**丢失或破损。** 请向商家申请补发或退款。如果你居住在欧盟，Your Europe 指出商家对发货至你收货期间的任何破损负责；若商品未在 30 天内（或约定时间内）送达，你可催告商家并给予合理的宽限期，逾期仍未送达即可解除合同并获退款。详见 [包裹丢失](guide:lost-parcel)（英文）。

**已退回。** 无法投递的包裹会退回商家：退回途中 Peek 显示 `Needs attention`，退达后显示 `Returned to sender`。请向商家确认是否重新寄出。参阅 [退回寄件人](guide:returned-to-sender)（英文）。

## Delivengo 联系方式

- **先联系商家：** 由商家通过其 Delivengo 账户安排发货。
- **向法国邮政投诉：** 法国邮政监管机构 Arcep 指出，向法国邮政投诉可致电 36 31（周一至周五 8:30–19:00、周六 8:30–13:00）或 +33 810 821 821（周一至周五 8:00–19:00），也可通过官网、信函或邮政网点提交。Arcep 同时提示寄件人与收件人权利略有区别，且未说明这些渠道是否受理 Delivengo 包裹。
- **联系你所在国家邮政：** 包裹进入你所在国家后，可凭同一单号向当地邮政查询。

## 使用 Peek 追踪 Delivengo 包裹

[Peek](/) 没有单独的 Delivengo 接口，而是像所有法国邮政或 Colissimo 包裹一样通过法国邮政追踪系统查询。粘贴 `…FR` 单号后，Peek 会立即识别为 “La Poste / Colissimo”；你也可以点“更改”改选 “Delivengo”，查询结果完全一致。laposte.fr（及 mydelivengo.laposte.fr）追踪链接同样有效。

Peek 展示法国邮政公开的数据：当前状态、附带每次扫描国家的轨迹历史，以及法国邮政提供的预计送达日期；不显示你的姓名和地址。状态除“已送达”等少数常见词按你的界面语言展示外，其余保留法国邮政法语原文。

一旦法国邮政提供目的国，Peek 还会尝试查询其支持的该国邮政，并在对方出现更新进展时自动切换（覆盖全部欧盟国家、英国、瑞士、挪威、美国、加拿大、澳大利亚、日本等）。不过出口后法国邮政历史仍可能静默，末端扫描并非总能显示。

当你打开包裹，或在开启通知的情况下进入派送日时，只要法国邮政官网正常响应，Peek 最高每 10 分钟检查一次，并在派送当天自当地时间早上 6 点起提高至每 2 分钟一次（移交目的国邮政后按该国邮政节奏，走综合追踪服务时派送日最高每 15 分钟一次），其余时间每小时一次。通知支持每次扫描、重要节点或仅派送日。连续两天无新动态时（处于“已创建运单”或“承运商已揽收”时为 12 小时）改为每小时检查一次。

## Delivengo 追踪常见问题

### Delivengo 和 Colissimo 是同一个服务吗？

不是，尽管两者都隶属法国邮政且共用同一套追踪系统。Delivengo 是法国邮政专门用于小型国际包裹的出口服务。

### 为什么我的 Delivengo 物流离开法国后就不再更新了？

包裹离开法国后由你所在国家的邮政完成末端派送，法国邮政的轨迹记录常会停止更新，但包裹仍可能在正常运输中。Peek 也会同时查询部分目的国邮政（见上文）；若迟迟无动态，请联系商家。

### 可以追踪寄往美国或加拿大的 Delivengo 包裹吗？

可以，直接用同一 `…FR` 单号通过法国邮政追踪即可。一旦法国邮政标出目的国，Peek 还会同步查询 [USPS](carrier:usps) 或 [加拿大邮政（Canada Post）](carrier:canada-post)，并在对方有更新扫描时自动切换。

:::sources
- [Universal Parcel Scraper: Delivengo](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/delivengo) – 法国邮政出口服务、目的国邮政末端派送、出口后静默、商户后台及无独立单号规则
- [Universal Parcel Scraper: La Poste](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/la-poste) – 共用追踪系统、单号格式与校验位、各扫描节点含义
- [European Union: EU customs union in action](https://european-union.europa.eu/priorities-and-actions/actions-topic/customs_en) – 欧盟成员国间免征关税
- [Your Europe: Shipping and delivery](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_en.htm) – 交付前破损归责商家、逾期未达处理
- [Arcep: La résolution des litiges postaux](https://www.arcep.fr/mes-demarches-et-services/consommateurs/fiches-pratiques/la-resolution-des-litiges-postaux.html) – 向法国邮政投诉的电话、时段、网站、信函、网点及寄收件人权利差异
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Peek 读取的数据
:::
