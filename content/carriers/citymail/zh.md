---
title: CityMail 物流查询：BCM 单号、瑞典语状态与派送指南
description: 查询瑞典 CityMail 包裹：了解 BCM 单号格式、瑞典语状态含义、派送时段、DHL 自提点与 iBoxen 快递柜及客服联系方式。
slug: citymail-tracking
published: 2026-11-27
updated: 2026-11-27
---

CityMail 是一家瑞典包裹和信件投递公司，创立于 1991 年，拥有 1,200 名员工、104 个当地网点以及 4 座分拨中心。网店用它寄送包裹，企业用它投递信件、广告邮件和杂志。你可以通过 [CityMail 追踪页面](https://www.citymail.se/spara-paket/)（Spåra paket）、CityMail 短信中的链接查询，或直接把单号粘贴到上方输入框。追踪单号位于 CityMail 发给你的包裹短信或邮件中。

## CityMail 追踪单号格式

CityMail 追踪页要求输入追踪单号（spårningsnummer），其示例以 `BCM` 开头。Peek 已知的格式如下（数字均为虚构示例）：

| 单号（虚构示例） | 格式说明 |
| --- | --- |
| `BCMKP48291037` | `BCM`、2 位字母加 8 位数字：CityMail 包裹参考号。 |
| `BCMK4829103`、`BCMKPR482910375` | 同类格式，字母为 1 至 3 位，数字为 7 至 9 位。 |
| `BCMKP4829103756182CN` | `BCM`、2 位字母、13 位数字加 `CN`：较长的 CityMail 参考号。 |
| 其他参考号 | Peek 代码也接受寄件人自己的参考号用于 CityMail：8 至 35 位字母和数字（见下文）。 |

没有单号？请询问发货商家或寄件公司。如果邮件里写着 PostNord 或 [Bring](carrier:bring)，那属于它们的单号而非 CityMail。详见 [如何查找追踪单号](guide:find-tracking-number)（英文）。

## CityMail 瑞典语物流状态含义解析

CityMail 追踪器使用瑞典语显示状态，在其追踪页上最新一条列在 “senaste händelse”（最新动态）下。以下为 Peek 代码已知的状态措辞，大致按流转顺序排列：

| CityMail 原文 | 对你的含义 |
| --- | --- |
| `Vi har fått beställning på en leverans till dig och väntar på ditt paket` | 寄件人已为你预订派送，CityMail 正在等待接收包裹。 |
| `Vi har fått en beställning på en leverans och väntar på paketet` | 含义相同，措辞更简短。 |
| `Paketet har aviserats, men ännu inte lämnats in till CityMail` | 寄件人已预报包裹，但尚未交给 CityMail。 |
| `Paketet har ankommit till terminal`、`Paket har ankommit till vår terminal` | 包裹已抵达 CityMail 分拨中心。 |
| `Paketet har levererats hem till dig` | 已送达你家中。 |
| `Paketet är utdelat, vid dörr/finns i påse på brevlåda` | 已放在门外，或装袋挂在信箱上。 |
| `Paketet har levererats till din brevlåda/postfack` | 已投入你的信箱或邮政信箱。 |
| `Namn saknades på adressen - leveransen kunde inte genomföras` | 投递未成功：地址处缺少你的姓名标识。 |
| `Paketet är på väg till ombud` | 正在送往自提点（ombud）。 |
| `Paketet har levererats till ombud` | 已送达自提点，可以前往取件。 |
| `Paketet hämtades inte ut i tid och skickas tillbaka till avsändaren` | 逾期未领取，正在退回寄件人。 |

前三条均表示包裹尚未交到 CityMail 手中。如果因缺少姓名导致投递失败，请确认你的信箱或家门上贴有你的姓名。

## CityMail 瑞典境内派送规则

**派送时间。** CityMail 帮助页面说明，包裹在非公共假日的周一至周五、瑞典时间 08:00 至 17:00 派送；其面向商家的服务列表也包含晚间送货上门（Hemleverans kväll）。预计送达日期可在 CityMail 短信或邮件的追踪链接中查看，如日期变动，CityMail 会通过同一方式通知。时效取决于网店所选服务：送货上门为 1–2 天或 1–4 天；寄往快递柜或自提点的包裹在 CityMail 从商家揽收后的次日送达。

**投递位置。** CityMail 自有投递员会将包裹放进你的信箱或邮政信箱、放在家门口，或装袋挂在信箱上。你无需在家等候，也无需签字或出示证件。

**无法投递到你地址时。** 你可以选择改投至 iBoxen 快递柜或 DHL 自提点（DHL-ombud）。若不做选择，CityMail 会发送新的送货上门日期。只要你的派送可选快递柜或自提点，也可以通过 CityMail 短信中的链接主动改投。CityMail 不会将包裹转寄至新地址。参阅 [更改收货地址](guide:change-delivery-address)（英文）。

**在 DHL 自提点取件。** CityMail 使用 DHL 的自提点网络。你会收到包含运单号、BankID 身份验证链接以及自提点名称的短信。取件请携带有效身份证件；他人代领需同时出示你的证件与代领人证件。逾期未取的包裹将退回寄件人，CityMail 的页面未写明具体保管天数。详见 [无人领取的包裹](guide:uncollected-parcel)（英文）。

**在 iBoxen 快递柜取件。** 你会收到短信以及 iBoxen 应用内的通知，应用中 “Hitta boxen” 可在地图上定位快递柜。到达柜前点击 “Hämta ut paket”，用 BankID 验证，再点击 “Öppna lucka” 开柜。如需他人代取，在 iBoxen 应用中输入对方手机号即可，代取人同样需要该应用与 Mobilt BankID。

**普通信件。** CityMail 在非节假日的工作日投递信件，但并非每天访问每个邮编。它将区域划分为四个片区，每四个工作日轮巡投递一次。要查看下次到访日期，可在 citymail.se 的 “När kommer posten?” 页面查询邮编，具体送达时段取决于你在投递路线中的顺序。

## CityMail 包裹延误、丢失、破损或退回

- **延误或改期。** CityMail 会尽量在预计日期送达，但有时无法按期完成，届时会在你的追踪链接中更新新的预计日期。部分订单还可通过该链接改投至快递柜或自提点。参阅 [物流停止更新](guide:tracking-not-updating)（英文）。
- **显示已送达但没见到包裹。** 请先检查信箱或邮政信箱、家门外以及挂在信箱上的塑料袋。如仍找不到，请联系 CityMail 的 Mottagarservice（见下文）。详见 [显示已送达却未收到](guide:delivered-not-received)（英文）。
- **包裹破损。** 先确认是内件破损还是仅外包装受损。如果内件破损，请联系商家，由商家向 CityMail 发起调查。参阅 [包裹破损](guide:damaged-parcel)（英文）。
- **发错商品或取消订单。** 请联系商家。CityMail 无法中途截停派送。
- **信件丢失。** 先联系寄件人确认信件确实已经寄出，CityMail 表示这通常是最快查明原因的办法。如果联系不上寄件人，再联系 Mottagarservice。详见 [包裹丢失](guide:lost-parcel)（英文）。
- **已退回。** 超过保管期未从自提点取走的包裹会退回寄件人，请联系商家申请退款或重寄。参阅 [退回寄件人](guide:returned-to-sender)（英文）。

## CityMail 联系方式与客服

Peek 无法代替你联系 CityMail 或商家。以下信息来自 citymail.se：

- **Mottagarservice（收件人服务）：** 电话 0771-40 60 40，电子邮箱 mottagarservice@citymail.se。服务时间为瑞典时间周一至周五 08:30–17:00。
- **商务咨询：** 总机 08-709 43 00。
- **在线渠道：** CityMail 瑞典语 [帮助页面](https://www.citymail.se/hjalp/) 和 [常见问题](https://www.citymail.se/hjalp/fragor-och-svar/)。CityMail 还提供网页应用，可跟踪配送、改投、预约退货并接收更新。

## 使用 Peek 追踪 CityMail 包裹

- **`BCM` 单号。** 直接粘贴到 [Peek](/)，系统会立即识别为 CityMail（“识别出的快递公司”），空格、连字符和大小写均不影响。
- **CityMail 追踪链接。** 带有 `?search=` 及参考号的 tracking.citymail.se 链接同样会立刻识别为 CityMail，无论参考号是什么格式。
- **其他参考号。** Peek 无法仅凭格式判断它属于 CityMail，也不会主动向 CityMail 查询。你仍可照常粘贴：Peek 跟踪 4,500 多家快递公司。随后点“更换”并选择 CityMail，即便 Peek 正在为其他快递公司显示“正在查询物流服务…”或已识别为其他快递公司也一样。对已添加的包裹请使用“更换快递公司”。针对 CityMail，Peek 接受 8 至 35 位且至少包含 1 位数字的字母数字组合；否则会提示 CityMail“不使用这种格式的单号”。
- **尚未收录。** 在 CityMail 录入该单号前，包裹会显示“等待 CityMail 更新”，Peek 会持续查询；两天后仍未查到时，Peek 会提示你核对单号、更换快递公司或删除包裹。

**Peek 展示的信息。** 包括 CityMail 的状态以及每条扫描的时间、地点与瑞典语原文。由于 CityMail 的追踪页不带时区，Peek 均按瑞典时间读取。预订与已预报节点显示为“已生成面单”；抵达分拨中心和发往自提点显示为“运输中”（Peek 代码中没有映射为“派送中”的 CityMail 措辞）；`Paketet har levererats till ombud` 显示为“待取件”并附带自提点名称；送达措辞显示为“已签收”；投递失败显示为“派送未成功”，并在“CityMail 的原文”下展示 CityMail 的措辞；退件显示为“已退回寄件人”。

Peek 还会展示寄件人名称以及在途时的预计送达日，但不显示你的邮编和 CityMail 的签收凭证：如需查看，请点“打开 CityMail 官网”，它会在 CityMail 的追踪页打开你的单号。

当你打开包裹，或在开启通知的情况下进入派送日时，Peek 最高每 10 分钟检查一次。连续两天无新动态时（处于“已生成面单”或“已揽收”时为 12 小时）改为每小时检查一次，在“已生成面单”下七天无更新则每 6 小时检查一次。若包裹在“已生成面单”、“已揽收”或“运输中”连续四天无新节点，Peek 会用该节点的日期显示“自10月12日周一起没有物流更新”，并提供“向 CityMail 寻求帮助”链接。通知可按“每次扫描”、“重要环节”或“仅派送当天”设置。

## 关于 CityMail 物流查询的问题

### 为什么我的 CityMail 追踪单号查不到？

请确认你完整复制了 CityMail 短信或邮件里的参考号。当 CityMail 追踪页查不到某参考号时，会显示以 `Ingen spårningsinformation hittades för den angivna referensen` 开头的提示，意思是未找到该参考号的追踪信息。如果一直如此，请联系安排发货的寄件人。

### CityMail 几点派送？

包裹在非节假日的周一至周五、瑞典时间 08:00 至 17:00 派送，同时 CityMail 也向网店提供晚间送货上门服务。预计日期可查看 CityMail 短信或邮件中的追踪链接。普通信件在白天投递，但并非每个邮编每天都会派送。

### 收 CityMail 包裹必须在家等吗？

不需要。CityMail 会直接把包裹放入你的信箱或邮政信箱、放在门口，或装袋挂在信箱上，无需签收。若现场无法投递，你可以选择改投到 iBoxen 快递柜或 DHL 自提点。

### 可以追踪 CityMail 普通信件吗？

CityMail 追踪页面名为 Spåra paket（追踪包裹），要求输入包裹追踪单号；其丢失信件帮助页面没有提及追踪功能，建议先联系寄件人，再联系 CityMail 的 Mottagarservice。

### CityMail 是正规公司吗？

是的。CityMail 自 1991 年起在瑞典投递邮件，持有瑞典邮政和电信管理局（Post- och Telestyrelsen）颁发的邮政运营许可。如果收到看似可疑的 CityMail 短信，请阅读 [防范快递诈骗短信](guide:delivery-scam-texts)（英文）。

:::sources
- [CityMail: Spåra paket](https://www.citymail.se/spara-paket/) – 追踪页、单号输入框及 BCM 示例
- [CityMail: Var är mitt paket?](https://www.citymail.se/hjalp/var-ar-mitt-paket/) – 含追踪链接的短信、最新动态、短信或邮件中的单号
- [CityMail: När kommer mitt paket?](https://www.citymail.se/hjalp/nar-kommer-mitt-paket/) – 派送日期与时段、1–2 天或 1–4 天
- [CityMail: Frågor och svar](https://www.citymail.se/hjalp/fragor-och-svar/) – 投递位置、免签收、快递柜与自提点、身份证件、破损、延误、信件四日轮巡、邮政许可、Mottagarservice 服务时间
- [CityMail: Hjälp & kontakt](https://www.citymail.se/hjalp/) – Mottagarservice 电话、邮箱与营业时间、总机
- [CityMail: Ombud](https://www.citymail.se/paket/ombud/) – DHL 自提点、含 BankID 链接的短信、取件证件、晚间派送
- [CityMail: Var är mitt brev?](https://www.citymail.se/hjalp/var-ar-mitt-brev/) – 丢失信件先询问寄件人
- [CityMail: Om CityMail](https://www.citymail.se/om-citymail/) – 1991 年成立、员工数、网点、分拨中心、自有投递员、网页应用
- [CityMail: Integritetspolicy](https://www.citymail.se/integritetspolicy/) – DHL 自提点、iBoxen 快递柜、不转寄包裹
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Peek 读取内容、CityMail 状态原文、单号格式
:::
