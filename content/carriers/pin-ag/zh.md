---
title: PIN AG 德国邮政追踪：挂号信、包裹与网点自提指南
description: 追踪德国 PIN AG 挂号信与包裹：可追踪邮件类型、16 位与 13 位单号、德语状态含义、网点自提规则及柏林客服联系方式。
slug: pin-ag-tracking
published: 2026-11-26
updated: 2026-11-26
---

PIN AG 是一家总部位于德国柏林的邮政公司（独立于德国邮政 Deutsche Post），拥有约 1,300 名员工，承运信件、挂号信（Einschreiben）、包裹以及来自 Amazon、medimops 和 rebuy 的订单。其中只有部分邮件支持追踪。您可在 [PIN AG 追踪页面](https://sendungsverfolgung.pin-ag.de/)（Sendungsverfolgung）查询，或将单号粘贴到上方输入框中。单号由发件人提供：网购订单请查看商家的发货邮件，挂号信请向寄件人索取。

## 哪些 PIN AG 邮件可以追踪？

PIN 仅对需要投递证明的邮件提供从揽收到签收的全程追踪，是否包含追踪取决于发件人下单时选择的产品类型。PIN 帮助页面列出如下：

| 支持追踪 | 不支持追踪 |
| --- | --- |
| 挂号信：Einschreiben-Einwurf、-Übergabe、-Persönlich、-Rückschein 和 -Persönlich-Rückschein | 普通平信（Standardsendungen） |
| 正式法定送达函件（Postzustellaufträge）：如法院或政府机构发出的公函 | 书籍与小商品邮件（Bücher- und Warensendungen） |
| 包裹（Pakete） | medimops 与 rebuy 订单 |
| 全德国范围内的 Amazon Prime 订单 | 柏林以外地区的普通 Amazon 订单 |
| 柏林市内的普通 Amazon 订单 | 广告直邮（Werbepost） |

PIN 追踪页面上的常见问题有一处表述略有不同：该页面称普通（非 Prime）Amazon 订单无法追踪，未列明柏林例外。因此普通 Amazon 订单可能完全不显示任何状态。

## PIN AG 追踪单号格式

Peek 识别的常见格式如下（数字均为虚构示例）：

| 单号（虚构示例） | 说明 |
| --- | --- |
| `0100482917364058` | 以 `0100` 开头的 16 位数字：Peek 识别的 PIN 包裹单号。16 位数字同样符合 [DHL eCommerce](carrier:dhl-ecommerce) 与 [TNT](carrier:tnt)。 |
| `4829173640582` | 13 位数字：挂号信或正式送达函件单号。许多其他承运商也使用 13 位数字。 |
| `482917364058(2)` | 同一组 13 位数字在面单上的常见印法（末位带括号）。Peek 会自动将其识别为 `4829173640582`。 |

若商家发货邮件中注明了 [DHL](carrier:dhl) 或其他承运商，该单号则属于对应承运商。完全没有单号？请参阅[如何找到您的追踪单号](guide:find-tracking-number)（英文）。

## PIN AG 德语状态中文含义

PIN 追踪页面展示四步进度条（`Sortierzentrum`、`Zustelldepot`、`In Zustellung`，随后为 `Zugestellt`，无法投递时显示 `Unzustellbar`）及德语状态说明。具体含义如下：

| PIN 显示状态 | 对您的实际含义 |
| --- | --- |
| `Sortierzentrum` | 邮件位于 PIN 分拣中心。 |
| `Zustelldepot` | 已抵达负责您所在区域的派送网点。 |
| `In Zustellung` | 正在派送中。PIN 通常当日送达（邮递员派送至下午）；若派送未果，次日会再次尝试。 |
| `Zugestellt`、`Die Sendung wurde zugestellt.` | 已送达。 |
| `Unzustellbar` | 无法投递，PIN 会将无法投递的邮件退回发件人。 |
| `Die Sendung wurde in unserem Sortierzentrum erfasst und wird ohne weitere Aktualisierung der Sendungsverfolgung in den kommenden Tagen zugestellt. Hinweis: Bitte beachten Sie, dass es sich um eine Sendung handelt, bei dem der Zustellverlauf nicht dokumentiert wird. Für zusätzliche Informationen schauen Sie gerne in unsere FAQ.` | PIN 已在分拣中心录入该邮件，并将在未来几天内投递，但不再提供后续追踪更新（PIN 不记录此类邮件的投递过程）。 |

最后一条提示虽然显示在 `In Zustellung` 步骤下，但邮件当时尚未进入上门派送环节。

## PIN AG 投递方式、代收与网点自提

**由谁投递。** 在柏林，信件通常会在 PIN 分拣中心处理后的次日送达。若 PIN 在柏林以外地区将邮件移交给合作派送商（Zustellpartner），时效可能稍慢，且追踪状态可能停止更新，即使挂号信亦如此（详见[为何物流停止更新](guide:tracking-not-updating)（英文））。发往柏林以外的普通 Amazon 订单按平信寄送，因此状态不会有任何变化。

**投递方式。** PIN 会将邮件投入您的信箱或当面交给您。如无法直接交付，可能会交由代收人签收：家人、同住人员、您工作场所的员工，或房屋业主/房东。若邮件要求本人亲收，或您与发件人已明确禁止代收，则不会交由他人。挂号信严格按照对应挂号类型要求投递。PIN 无法投递至 Packstation 自助包裹柜。

**网点自提。** 当 PIN 无法完成投递时，会将邮件寄存在 PIN 派送网点（Zustelldepot）或合作网点，并留下通知卡（Benachrichtigungsschein）：

- 请于下一个工作日起在营业时间内前往领取。通知卡以及 PIN 追踪页面输入单号后均会显示网点地址与营业时间。
- 必须携带官方带照片身份证件和通知卡；若遗失通知卡，则需提供追踪单号及派送尝试日期。
- 委托他人代领需出示通知卡上的授权书（Vollmacht）及代领人本人的带照片身份证件。
- PIN 按派送尝试次日起计保留 **7 个工作日**（周一至周六）。正式法定送达函件（Postzustellauftrag）保留 3 个月，且以存入网点当日视为已送达。
- 逾期未领取的邮件将自动退回发件人。

## PIN AG 邮件延误、丢失或退回怎么办

如需补发或退款，请直接联系发件人——PIN 也表示这是最快捷的解决途径。

- **寄出满 8 个工作日仍未送达：** 自寄出日起按周一至周六计算。请向发件人申请补发或退款，也可以通过 PIN 在线表单提交查单申请（Nachforschungsauftrag）。查单最长耗时 21 天，且不一定能找回邮件。根据 PIN 条款，若交寄后 20 天内未能送达且 PIN 查无下落，邮件将推定为丢失。详见[包裹丢失处理指南](guide:lost-parcel)（英文）。
- **停留在 `In Zustellung` 超过三天：** PIN 建议通过追踪页面链接的联系表单与客服联系。
- **显示已送达但未收到：** 请先询问同住人员，因为 PIN 可能已交由代收人签收。对于 Amazon 订单，PIN 说明在追踪页同时输入单号和邮编可查看具体投递位置（如邻居代收）。有时系统技术故障也会提前显示已送达，可等待一天观察，通常次日会送达。详见[显示已签收但未收到包裹](guide:delivered-not-received)（英文）。
- **外包装或内件损坏：** 可使用 PIN 的“Hinweis zur Briefzustellung”表单反馈（该表单也受理延误、误投或投递异常，PIN 仅对部分情况回复）。如内件受损，请联系发件人处理。
- **邮件退回：** 当信箱未标注姓名、无投递口、逾期未自提或破损过于严重无法投递时，PIN 会将邮件退回发件人。若地址有误/不全、信箱无收件人姓名、体积不符与 Amazon 约定的信函规格，或收件地址写为 Packstation，Amazon 订单也会被退回 Amazon。PIN 无法在派送途中修改地址，请联系 Amazon 重新安排配送。详见[包裹退回发件人](guide:returned-to-sender)（英文）。

## PIN AG 客服电话与联系方式

Peek 无法代您联系 PIN AG 或发件人。PIN AG 公布的联系方式如下：

- **客服中心（ServiceCenter）：** `030 577978-800`，德国时间周一至周五 8:00–18:00。
- **在线表单：** PIN 帮助页面提供查单申请（Nachforschungsauftrag）与投递反馈（Hinweis zur Briefzustellung）表单，追踪页面也附有联系表单链接。
- **总部地址：** PIN AG, Alt-Moabit 91, 10559 Berlin；总机 `030 577978-0`，邮箱 `info@pin-ag.de`。

## 使用 Peek 追踪 PIN AG 邮件

在 [Peek](/) 中粘贴单号即可，是否带有括号均支持（Peek 会自动合并括号内的末位数字）。

- **以 `0100` 开头的 16 位数字：** 其他承运商也使用 16 位单号，因此 Peek 会按您所在国家（手机地区设置，或网页浏览器时区）向最可能的承运商发起查询。在德国会优先询问 PIN AG，查询过程中显示“正在查询物流服务…”。若 PIN 查到记录，页面会显示“PIN AG 有这个包裹的记录。”并自动归类；在德国以外地区，Peek 会在其他承运商查无记录后再询问 PIN AG。
- **尚未录入系统：** 仍可照常添加。在德国地区，若 PIN 暂未收录该单号，包裹页面会显示“等待 PIN AG 的首次扫描”和“可能是 PIN AG”，并持续向 PIN 查询。一周后如仍无结果，Peek 会提示您向发件人核对单号或切换其他承运商。
- **13 位数字：** Peek 无法仅凭 13 位长度判定属于 PIN。仍可直接粘贴（Peek 支持超过 4,500 家承运商），点击“更改”并搜索选择“PIN AG”（输入 PIN 即可）；已添加的邮件请使用“更改承运商”。
- **PIN 追踪链接：** 粘贴包含单号的 PIN 追踪页面链接会立刻识别为 PIN AG（“识别出的快递公司”）。

**Peek 展示的内容。** 显示 PIN 的状态以及带时间戳的每一步德语原文（按德国时间解析）。`Sortierzentrum`、`Zustelldepot` 和不再更新的状态提示显示为“运输中”，`In Zustellung` 显示为“派送中”，`Zugestellt` 显示为“已送达”，`Unzustellbar` 显示为“需要关注”。若邮件已存放在网点待领（此时 PIN 追踪页会显示网点地址与营业时间），无论 PIN 归入哪个步骤，Peek 都会显示为“待取件”。PIN 不提供扫描地点、预计送达日、重量或签收人。Peek 不会显示您的收货地址或自提网点地址：如需查看网点地址，请点击“打开 PIN AG 官网”直接跳转至 PIN 追踪页。普通平信在 PIN 官网无追踪记录，Peek 亦无法追踪。

当您在 Peek 中打开邮件页面，或在派送当日开启通知时，Peek 最多每 10 分钟检查一次；在 PIN 扫描出车派送当天，只要 PIN 官网正常响应，会从德国时间早上 6 点起每 2 分钟检查一次（通过追踪服务跟进时为每 15 分钟一次）。若连续两天无新动态（或停留在“已创建面单”/“承运商已揽收”满 12 小时），改为每小时检查一次；停留在“已创建面单”满七天后每 6 小时一次。

当最新扫描距今已满四天且邮件仍处于“已创建面单”、“承运商已揽收”或“运输中”时，Peek 会按该次扫描日期加以标记，例如“自 10 月 12 日（周一）起无物流更新”。页面随即会提示“包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 PIN AG 查询。”并提供指向 PIN ServiceCenter 的“从 PIN AG 获取帮助”链接。如果超过三周仍无新扫描，则会提示“超过三周没有新动态。如果包裹仍未送达，请联系卖家。Peek 会持续向 PIN AG 查询。”

Peek 可为您提供“每次扫描”、“重要节点”或“仅送达通知”提醒。

## 关于 PIN AG 追踪的常见问题

### 为什么我无法追踪自己的 PIN AG 信件？

PIN 仅追踪挂号信、正式送达函件、包裹及 Amazon Prime 订单（其帮助页面另涵盖柏林市内普通 Amazon 订单）。普通平信等其他邮件不具备追踪功能，这由发件人选用的产品决定。PIN 预计普通平信会在接下来几个工作日内送达。

### PIN AG 派送通常需要多久？

在柏林，邮件通常会在 PIN 分拣中心处理后的次日送达。在柏林以外由合作派送商接手时耗时可能稍长。若寄出后满 8 个工作日（周一至周六）仍未收到，请联系发件人。

### PIN AG 会在网点保留我的信件多久？

自派送尝试次日起保留 7 个工作日（周一至周六）；正式法定送达函件保留 3 个月。逾期后邮件将退回发件人。

### 为什么 PIN AG 追踪查不到我的单号状态？

PIN 常见问题给出的提示语为 `Für die Sendungsnummer konnte kein Zustellstatus gefunden werden.`。原因可能是该邮件属于不提供追踪的类型（如平信）、单号输错或包含括号，或者邮件已超过六个月——出于数据保护要求，PIN 会在六个月后删除追踪数据。

### PIN AG 可以派送到 Packstation 自助包裹柜吗？

不可以，PIN AG 无法向 Packstation 投递。Amazon 通常会通过 Deutsche Post 发送寄往 Packstation 的订单；如果邮件仍落到 PIN 手中，PIN 会将其退回 Amazon。

:::sources
- [PIN AG: Sendungsverfolgung](https://sendungsverfolgung.pin-ag.de/) – 四步进度条；常见问题：可追踪邮件范围、未查到状态提示、当日派送、次日重试及联系表单
- [PIN AG: Häufige Fragen zum Empfang von Briefsendungen](https://pin-ag.de/servicecenter/post-empfangen/sonstige-sendungen) – 可追踪邮件、派送时效、8 个工作日、查单申请、网点自提、退件及在线表单
- [PIN AG: Häufige Fragen zum Empfang von Amazon-, medimops- und rebuy-Sendungen](https://pin-ag.de/servicecenter/post-empfangen/amazon-sendungen) – Amazon 订单规则、显示已送达未收到、退回及 Packstation 限制
- [PIN AG: ServiceCenter](https://pin-ag.de/servicecenter) – 客服电话及服务时间
- [PIN AG: Allgemeine Geschäftsbedingungen](https://pin-ag.de/agb) – 信箱投递、代收人、网点保管、无法投递处理及 20 天推定丢失条款
- [PIN AG: Das Unternehmen](https://pin-ag.de/unternehmen) – 柏林信件派送业务与约 1,300 名员工规模
- [PIN AG: Impressum](https://pin-ag.de/impressum) – 总部地址、总机电话与电子邮箱
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Peek 读取字段、PIN 步骤文案与单号格式
:::
