---
title: Blue Dart 物流查询：11 位运单号与状态说明
description: 查询印度 Blue Dart 快递：11 位运单号规则、Shipment Further Connected 等状态含义及 Peek 跟踪方法。
slug: blue-dart-wuliu-chaxun
published: 2026-11-26
updated: 2026-11-26
---

Blue Dart 是一家印度快递公司：Peek 目录将其归属印度，并按印度时间读取扫描记录。Blue Dart 货件使用 11 位数字运单号（waybill number），由寄件人提供；网购订单的运单号可能在商家的发货邮件或订单页里。把它粘贴到上方输入框，或前往 Blue Dart 官网 bluedart.com 查询。由于 Peek 无法查看 Blue Dart 官网，以下内容来自 Peek 代码对 Blue Dart 查询系统的记录。

## Blue Dart 运单号长什么样

Peek 为 Blue Dart 收录了一种形态：11 位数字，末位是由前十位算出的校验位。以下数字为虚构：

| 单号（虚构） | 含义 |
| --- | --- |
| `48291736544` | 校验位正确的 11 位数字：Blue Dart 运单号的形态。GLS 网络、PostLogistics、日本邮政、Mainfreight 等也使用 11 位数字，仅凭形态不能确定是 Blue Dart。 |
| `48291736545` | 同形态但校验位错误。Peek 不会推荐 Blue Dart：请检查是否输错。 |
| `4829173654` | 10 位数字：不是 Blue Dart 运单号。如果为其手动选择 Blue Dart，Peek 会提示 Blue Dart “不使用这种格式的单号”。12 位数字或含字母的编号也同样如此。 |

Aramex 的 11 位运单号和[日本邮政](carrier:japan-post)挂号件采用相同的校验规则，因此即便通过校验也可能属于它们：请核对商家消息中的快递公司名称。粘贴到 Peek 时空格和连字符不影响识别。Blue Dart 查询页也可以按其他参考号查询，但 Peek 需要运单号。找不到单号？查看[如何查找运单号](guide:find-tracking-number)（英文）。

## Blue Dart 物流状态含义

Blue Dart 查询页先显示货件当前状态，下方按时间倒序列出每次扫描及其地点、日期和时间。Peek 已知的 Blue Dart 文案大致按流转顺序如下：

| Blue Dart 显示 | 对你的含义 | Peek 显示 |
| --- | --- | --- |
| `Pickup Employee Is Out To P/U Shipment` | Blue Dart 快递员正在前往寄件人处揽收（P/U），尚未拿到包裹。 | “已生成面单” |
| `Shipment Picked Up` | Blue Dart 已从寄件人处揽收。 | “已揽收” |
| `Shipment Arrived`、`Shipment Arrived At Hub` | 已到达扫描地点写明的 Blue Dart 网点或分拣中心。 | “运输中” |
| `Paper Work Inscan` | 在分拣中心到件后、发往下一程前完成的单证扫描。 | “运输中” |
| `Clubbed Canvas Bag Scan` | 已与其他货件一同装入帆布邮袋扫描。 | “运输中” |
| `Shipment Further Connected` | 已从该网点发出，转往下一站。 | “运输中” |
| `Delivery Scheduled For Next Working Day` | 已到达派送站点但已过当日截单时间，将于下一个工作日安排派送。 | “运输中” |
| `Shipment Out For Delivery` | 正在派送中。 | “派送中” |
| `Cnee Refused Id/Otp Not Shared-Incorrect` | 派送未成功：收件人（Cnee）拒收，或未提供/提供了错误的身份证件或一次性验证码（OTP）。 | “派送未成功” |
| `Delivery Delayed`、`Delay Caused Beyond Control` | 派送延误；后者指因超出 Blue Dart 控制范围的原因导致延误。 | “需要留意” |
| `Shipment Delivered` | 已送达。 | “已签收” |
| `Shipment Returned Back To Shipper` | 货件已退回寄件人。退件完成后 Blue Dart 会以此作为货件状态。 | “已退回寄件人” |

Peek 暂未收录的文案会按 Blue Dart 原文显示并归为“运输中”；若包裹已处于派送中或后续阶段，则保持原阶段不变。更多见[物流状态含义](guide:tracking-statuses)（英文）。

## Blue Dart 在印度的派送：证件与 OTP 验证码

由于 Peek 无法核实 Blue Dart 的派送规则（如派送尝试次数，或派送未成功的货件保管多久），本页不列这些规则。

**身份证件或 OTP 验证码。** `Cnee Refused Id/Otp Not Shared-Incorrect` 表明 Blue Dart 派送可能需要核验身份证件或一次性验证码（OTP）。如果收到了派送验证码，请在派送当天提前准备好。若出现此记录但你并未拒收，请联系商家重新安排派送。参见[派送未成功](guide:missed-delivery)（英文）和[未领取的包裹](guide:uncollected-parcel)（英文）。

收到称派送失败并要求通过链接付款或提供验证码的短信？参见[快递诈骗短信识别](guide:delivery-scam-texts)（英文）。

## Blue Dart 货件：末端派送与清关

**Blue Dart 运单。** Peek 未收录 Blue Dart 在印度境内或海外转交给其他承运商的规则。如果商家另外给了其他快递的单号，请一并添加。

**通过 ICC Worldwide 寄送。** ICC Worldwide 的查询系统可能在 Blue Dart 单号旁注明 Blue Dart。只有当该单号为 11 位数字时，Peek 才会继续向 Blue Dart 查询，并在 Blue Dart 出现不早于 ICC Worldwide 最新记录的扫描后转为在 Blue Dart 处跟进（同时保留 ICC Worldwide 历史记录）。其他位数单号则停留在 ICC Worldwide。添加时输入 ICC Worldwide 单号，然后点“更换”搜索 ICC Worldwide（Peek 不会根据单号推荐它）。

**清关。** Peek 代码仅覆盖印度境内的 Blue Dart，且无法查看其官网上关于国际货件的说明。包裹卡在海关请看[包裹卡在海关怎么办](guide:customs)（英文）。

## Blue Dart 物流停更、延误或退回

如需退款或重发，请联系向 Blue Dart 下单的商家。

**尚无扫描。** 对查无信息的单号，Blue Dart 查询系统返回 `There is no information on the Waybill/Reference Number/Order Number currently.`。Peek 会继续查询。请对照商家消息检查 11 位数字；几天后仍无记录，请问商家是否已交寄。

**停在 `Pickup Employee Is Out To P/U Shipment`。** Blue Dart 尚未从寄件人处揽收，请问商家何时交付。

**`Delivery Delayed` 或 `Delay Caused Beyond Control`。** Peek 会显示“需要留意”。如果之后没有进展，请联系商家。

**多天没有更新。** 在途货件连续四天无新扫描时，Peek 会以最后一次扫描的日期标记：“自10月12日周一起没有物流更新”。参见[物流为什么停止更新](guide:tracking-not-updating)（英文）。

**显示已签收但没收到。** 先询问家人或大楼前台，再联系商家。参见[显示已签收但未收到](guide:delivered-not-received)（英文）。

**丢失或退回。** 请联系商家退款或重发：见[包裹丢失](guide:lost-parcel)（英文）。`Shipment Returned Back To Shipper` 表示已退回寄件人：见[退回寄件人](guide:returned-to-sender)（英文）。

## Blue Dart 客服与联系方式

由于 Peek 无法查看 Blue Dart 官网，本页不列 Blue Dart 电话或邮箱：Peek 只提供从承运商自有页面核实过的联系方式。

网购订单请先联系向 Blue Dart 下单的商家。Peek 无法替你联系 Blue Dart 或商家。

## 用 Peek 跟踪 Blue Dart 货件

把运单号粘贴到 [Peek](/)。多家承运商共用 11 位数字，因此 Peek 不会直接把 Blue Dart 单号标为“识别出的快递公司”：

- **在印度**（按手机地区或网页访问位置判断），Peek 会显示“正在查询物流服务…”并询问使用这种形态的快递公司，最先询问 Blue Dart。Blue Dart 查到这个单号时，会显示“Blue Dart 有这个包裹的记录。”
- **在印度以外**，Peek 会在其他快递公司和物流查询服务都没查到这个单号后，最后询问 Blue Dart。想直接归入 Blue Dart，请点“更换”，在“符合这个单号”下选择 Blue Dart。

如果暂时没有承运商查到，也照样可以添加或手动指定 Blue Dart：Peek 跟踪 4,500 多家快递公司。Peek 仅在校验位正确时把 Blue Dart 列入匹配建议；若你的单号校验位未通过但确认无误，在承运商列表搜索 Blue Dart 即可，Peek 仍会向其查询。带 `trackNo=` 的 bluedart.com 查询链接同样可用：Peek 会提取单号并立刻识别为 Blue Dart（“识别出的快递公司”）。

归入 Blue Dart 的货件在首次扫描前显示“等待 Blue Dart 更新”，两天后 Blue Dart 仍查不到则提示“请检查运单号”；未指定快递公司添加时显示“暂无物流记录”。Peek 仅凭运单号读取 Blue Dart 公开查询页，无需账号：展示 Blue Dart 给出的状态，以及按印度时间读取的每条 Blue Dart 原文扫描和地点，略去收件人详情且不显示预计送达日期。包裹页上的“打开 Blue Dart 官网”会在 Blue Dart 查询页打开该运单号。

在 Blue Dart 自己的查件系统正常响应期间，当你打开货件页，或在派送当天开启通知时，Peek 最多每 10 分钟查询一次，在 Blue Dart 扫描派送当天的当地时间早上 6 点起最多每 2 分钟查询一次；其余时间每小时查询一次。连续两天无新动态（在“已生成面单”或“已揽收”状态下为 12 小时）后，改为每小时查询一次。当只有物流查询服务响应时，Peek 每小时查询一次，最新扫描满一天后每 3 小时查询一次，在派送当天你打开货件页或开启通知时最多每 15 分钟查询一次。通知选项包括“每次扫描”、“重要环节”和“仅派送当天”。

## 关于 Blue Dart 物流查询的常见问题

### 怎么查 Blue Dart 快递？

在 bluedart.com 的查询页输入 11 位运单号，或直接粘贴到 Peek。在印度，Peek 会优先查询 Blue Dart；在其他国家，Peek 最后才询问 Blue Dart，你也可以点“更换”选择 Blue Dart。

### Blue Dart 运单号长什么样？

11 位数字，末位是校验位。Aramex 等公司也使用 11 位数字，请确认商家消息中写的是 Blue Dart。

### Blue Dart 物流里的“Shipment Further Connected”是什么意思？

表示 Blue Dart 已将货件从当前网点发往下一站，Peek 显示为“运输中”。

### “Cnee Refused Id/Otp Not Shared-Incorrect”是什么意思？

表示派送未成功，原因是收件人（Cnee）拒收，或者未提供/提供了错误的身份证件或一次性验证码（OTP），Peek 显示“派送未成功”。如果你并未拒收，请让商家重新安排派送。

### Blue Dart 包裹丢失联系谁？

联系向 Blue Dart 下单的商家或寄件人。由于 Peek 无法检查 Blue Dart 官网，本页未列出 Blue Dart 联系方式。

:::sources
- [Universal Parcel Scraper: Blue Dart](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/blue-dart) – 印度归属、11 位运单号与校验位、查询系统与无信息回复、状态文案及阶段、印度时间、略去字段、查询链接
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – 其他 11 位数字承运商、Aramex 与日本邮政共用校验位、印度内外查询顺序、ICC Worldwide 对 Blue Dart 的 11 位单号交接、Blue Dart 无下游交接
:::
