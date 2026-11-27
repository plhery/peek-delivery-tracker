---
title: FAN Courier 快递查询：AWB 运单号、状态与 FANbox 自提柜
description: 查询罗马尼亚 FAN Courier 包裹：AWB 运单号在哪里找、物流状态说明、FANbox 自提柜取件期限、派送未成功及客服联系方式。
slug: fan-courier-tracking
published: 2026-11-24
updated: 2026-11-24
---

FAN Courier（法定名称 FAN Courier Express S.R.L.）是成立于 1998 年的罗马尼亚快递公司，在全罗马尼亚设有超过 3,000 台 FANbox 自提柜，并称自 2006 年以来一直是罗马尼亚快递行业的领军者。你可以在 [fancourier.ro 的 AWB 查询页面](https://www.fancourier.ro/en/awb-track/) 输入 AWB 运单号查询，也可以通过 FAN 短信中的链接或 FAN Courier App 查询。AWB 单号由寄出包裹的商家或寄件人提供。

## FAN Courier AWB 运单号与查找方式

AWB（Air Waybill，空运单/运单）是 FAN 为每个包裹配发的随附单据名称，其编号就是你的运单号。FAN 未公开单号长度规范，但其官网查询系统可识别的 AWB 为 13 位数字，例如（示例数字）`2482910573649`。

查找单号的渠道：

- **商家的发货邮件**或订单页面。FAN 建议直接联系寄件人或下单商家获取。
- **FAN 发送的短信。** 快递员揽收包裹后，FAN 会通过短信向你发送一个用于跟踪派送的安全链接，链接中也会显示你的 PIN 码。派送当天还会再发一条短信，附上预计派送时间和快递员联系方式。
- **FAN Courier App。** 无需账号即可查询任意 AWB；如果登录账号（使用 selfAWB 账号，或用邮箱和手机号新注册账号），App 会列出寄往你手机号的包裹，甚至包括寄件人尚未交寄的包裹。

完全找不到单号？查看[如何查找运单号](guide:find-tracking-number)（英文）。

`The AWB was not found` 可能是单号输错，也可能是 FAN 尚未揽收：FAN 会在向寄件人取件时自动登记该运单。

容易混淆的单号：Econt 运单号同样是 13 位数字，[Cargus](carrier:cargus) 也把单号称为 AWB，而 Sameday 的 AWB 以 `1` 和字母开头（如 `1ABC482910573`）。请以商家邮件中写明的快递公司为准。更多见[运单号格式指南](guide:tracking-number-formats)（英文）。

## FAN Courier 物流状态含义

FAN 查询页默认打开为罗马尼亚语，可切换至英语。页面显示一条“Shipment status”总状态以及四个阶段节点（`Picked up`、`In transit`、`In delivery`、`Delivered`），每个节点均附带日期。以下是英文界面的状态表述，按包裹经历的先后顺序排列：

| FAN Courier 状态 | 中文含义 |
| --- | --- |
| `The AWB has been registered by sender` | 商家已[生成运单](guide:tracking-statuses)（英文），FAN 尚未拿到实物包裹。 |
| `Delivered to FANbox` | 寄件人已将包裹投进 FANbox 等待快递员取走，并非投递给你。 |
| `Picked up` | FAN 已从寄件人处揽收包裹。 |
| `In transit` | 正在 FAN 各分拨网点之间转运。 |
| `On hold. The shipment will leave the country on the next transport.` | 正在发往境外，等待下一班运输。 |
| `In delivery` | 快递员已带件外出，正在今天的派送路线上。 |
| `Recipient notified` | 未能送达：快递员已尝试派送，FAN 已向你发出通知。 |
| `Redirected` | 已改派至新地址或自提柜，并启用页面上显示的新 AWB 单号。 |
| `Delivered` | 已签收。 |
| `Rejected delivery` | 收件人拒收，包裹将退回寄件人。 |
| `Returned` | 正在以独立的退件 AWB 单号退回寄件人。 |

有两条状态容易引起误会。`Picked up` 表示 FAN 向寄件人取走了包裹，并不是你去自提了包裹。`Delivered to FANbox`（以及 `Delivered to Collect Point`）是寄件流程的起点：页面提示说明这是通知快递员去自提柜取件的指令。FAN 跟踪页不会单独显示“包裹已在 FANbox 等待你取件”这一步，这类取件通知会连同你的 PIN 码一起通过短信、邮件或 App 发送。

每条扫描都有各自的表述，例如 `Shipment sorted`，或同属派送中的 `Shipment taken over for delivery` 与 `Delivery in process`，页面会将它们汇总为上述节点，并在签收后隐藏后续条目。

## FAN Courier 派送时间、FANbox 与派送未成功

FAN 表示标准包裹自揽收起 1 至 3 个工作日送达。快递员派送时间为周一至周五 9:00 至 17:00；仅当寄件人选择了周六派送服务时，周六 9:00 至 14:00 才会派送。

**家中无人。** FAN 服务包含两次派送尝试。快递员会在次日再次派送；你也可以通过短信里的链接或 App 改约日期、转投至 FANbox（若附近格口有空位甚至可当天转投）或修改收件地址。更改地址视作一次新派送，通常需要 24 至 48 小时，且需征得寄件人同意。你还可以申请前往 FAN 营业厅自提，取件时出示身份证、护照或短信 PIN 码即可。

如果 FAN 无法完成派送，其一般条款规定会通知你，并在自提柜或有人值守网点自通知日起保留 5 个自然日（Standard 服务页面写明最长 7 天）。

**FANbox 自提柜。** 室外自提柜全天 24 小时开放。短信中包含一个安全链接和四位验证码，打开后可查看取件 PIN 码；邮件和 App 则直接提供 PIN 码及可在柜机扫码的二维码。取件期限为自通知起 36 小时；若到期时自提柜发生故障，修复后 FAN 会免费延长 24 小时。在期限届满前，FAN Courier App 还支持付费延长 24、48、72 小时或 5 天，并可开启一次设置即可生效的自动延期。直接寄往 FANbox 而逾期未取的包裹会退回寄件人；改派至 FANbox 的包裹逾期后则会转至最近的 FAN 网点暂存。货到付款（COD）可在自提柜刷卡支付，付款并打开柜门后不可拒收。

**Collect Point（PayPoint Colet Expres 合作门店）：** 仅限已预付的包裹（不支持货到付款），限重 10 公斤。取件需出示身份证件及短信或邮件中的 5 位取件码，门店保留 7 个自然日。

**向快递员付款。** 货到付款仅在商家允许时支持刷卡。除非寄件人购买了“Check on Delivery”（签收前开箱验货）服务，否则付款前不能拆包。

## FAN Courier 包裹延误、丢失或破损

FAN 没有规定多少天无更新即算异常。罗马尼亚邮政监管机构 ANCOM 指出，除非双方另行约定了交货时间，罗马尼亚法律规定网店须在下单后 30 天内送达，逾期消费者有权取消订单。如果包裹长时间没有动静，请联系商家：商家与 FAN 签有运输合同，可发起“Delivery Delay”工单，或向 FAN 发送附带 AWB、内件清单和货值的邮件。更多见[物流停止更新怎么办](guide:tracking-not-updating)（英文）。

**签收时发现破损：** 请当场要求快递员出具破损报告，再决定签收或拒收。若收到错误商品，请直接联系寄件人，FAN 不核验内件。

**投诉与索赔：** 寄件人和收件人均可通过电话、邮件、前往任意 FAN 网点提交书面材料或挂号信投诉。根据 FAN 条款：投诉须在交寄后 6 个月内提出，FAN 须在 3 个月内回复，并在达成一致后 30 天内赔付；赔偿款支付给寄件人。如对处理结果不满，可在交寄后一年内向罗马尼亚监管机构 ANCOM 申诉或提起诉讼。ANCOM 可以调查并处罚 FAN，但无权强制其支付赔偿。

## FAN Courier 客服电话与联系方式

FAN 的官方联系渠道：

- **电话：** 021.9336（境外拨打 +40 21 9336，按普通市话计费），Vodafone 与 Orange 用户可拨 0723 55 22 33 或 0742 55 22 33，也可拨 \*9336。服务时间为罗马尼亚时间周一至周五 09:00 至 17:00。
- **电子邮箱：** 一般咨询 customer@fancourier.ro；FANbox 问题 fanbox@fancourier.ro；Collect Point 问题 collectpoint@fancourier.ro；国际包裹 international@fancourier.ro。
- **ȘteFAN：** fancourier.ro 上的在线聊天助手，全天候可用。
- **快递员本人：** 仅限派送当天，通过 App 或短信链接联系。

> FAN 提醒冒充其名义的诈骗活动正在增加。切勿点击索要银行卡信息的虚假链接。

## 使用 Peek 跟踪 FAN Courier 包裹

遇到以 `2`、`3`、`4`、`5` 或 `7` 开头的 13 位数字时，[Peek](/) 会直接向 FAN Courier 查询；若以 `3` 开头，还会同时查询 iMile。如果你的手机地区或网络位于保加利亚，还会一并查询 Econt；若位于印度且符合 Delhivery 规则，则会同时查询 Delhivery。只有 FAN 查到记录时，页面会显示“FAN Courier 有这个包裹的记录”；若多家快递都有记录，由你选择。其他格式的 AWB 不会被识别：你仍可照常粘贴（Peek 跟踪 4,500 多家快递公司），然后点击“更换”并选择“FAN Courier”。粘贴 FAN 官网罗马尼亚语（`/awb-tracking/`）或英语（`/en/awb-track/`）查询链接（带 `tracking=`、`awb=` 或 `/awb-tracking/` 后紧跟 13 位 AWB）会直接定位到 FAN Courier。

Peek 会显示 FAN 的每一条扫描记录及其所在城镇和时间。FAN 的时间戳不含时区，因此 Peek 会按 FAN 所列城镇的当地时间读取（罗马尼亚城镇按罗马尼亚时间），再换算为你设备的时区；在 peektracker.com 以及 iPhone 和 Android App 中，轨迹底部会注明“时间按你所在的时区显示：”及对应时区名称。扫描文本保留 FAN 英文原文，只有 `Shipment picked up` 会显示为“快件已揽收”。在你打开该包裹页面时，或在派送当天且已开启通知时，只要 FAN 官网正常响应，Peek 最高每 10 分钟检查一次，在 FAN 扫描派送当天自罗马尼亚时间早上 6 点起最高每 2 分钟检查一次；其余时候每小时检查一次。状态变化时，Peek 可以通知你。连续两天无新动态后（若仍停留在“已生成面单”或“已揽收”，则为 12 小时后），改为每小时检查一次。

Peek 不显示预计送达日和 FANbox 柜点名称，也不展示收件人姓名、内件信息、重量和付款明细。由于退件会启用单独的新 AWB，原单号不会显示为已退回。

## FAN Courier 快递查询常见问题

### “The AWB has been registered by sender” 是什么意思？

说明商家已经创建了 AWB，但 FAN 还没有拿到包裹。FAN 查询页也可能改为显示 `The AWB has been registered by the sender but not handed over to the courier.`。FAN 提醒仅生成 AWB 并不会触发上门取件，寄件人仍须预约取件。若提示结尾为 `…has not been handed over to FANbox.` 或 `…to Collect Point.`，则表示寄件人需自行投柜或交至 PayPoint 合作店；根据 FAN 条款，投柜有效期为下单后 30 个自然日（除非商家合同另行约定），此外 FAN 未设定其他时限。如果状态一直不变，请联系商家——只有寄件人才能把包裹交给快递。

### 如何跟踪 FANbox 包裹或查找附近的 FANbox？

与普通 FAN 包裹完全一样：在 FAN 查询页或 App 中输入 AWB，或打开 FAN 的每日汇总短信链接（其中同样列出 FANbox 和 Collect Point 包裹）。查找柜机位置可使用 [FANbox 服务页面](https://www.fancourier.ro/en/services/fanbox-service/)的地图、[FAN 网点地图](https://www.fancourier.ro/en/fan-locations/)（含营业厅和 Collect Point 的地址与营业时间）或 App 中的 Locations 栏目。

### FAN Courier 做国际快递吗？

是的：FAN 提供覆盖大多数欧盟国家的陆运以及通达全球的空运，并在摩尔多瓦设有办事处。其条款规定欧盟和欧洲经济区内最长 12 个工作日，其他地区最长 14 个工作日。由 16 位数字、字母 `F` 和 4 位数字组成的 AWB 属于 FAN 面向电商卖家的跨境服务 [FAN Courier International](carrier:fan-courier-international)，在 fancourier.eu 查询。FAN 还说明，如果寄件人显示为 MEEST，包裹大概率来自中国——Meest 是一家主要承运中国货件的物流公司。

### FAN Courier 周六派送吗？

仅在寄件人选择了周六派送增值服务时才会派送，时间为周六 9:00 至 14:00。

### 没有 AWB 单号可以查 FAN Courier 包裹吗？

在 FAN 官网查询页不行，必须输入 AWB 或参考号。你可以向商家索要单号、打开 FAN 发来的短信链接，或登录 FAN Courier App 查看寄往你手机号的包裹。

:::sources
- [FAN Courier: AWB Tracking](https://www.fancourier.ro/en/awb-track/) – 查询表单、提示语、状态标签与防诈骗提醒
- [FAN Courier: Tracking AWB](https://www.fancourier.ro/awb-tracking/) – 罗马尼亚语标签、语言切换、FANbox 与 Collect Point 提示
- [FAN Courier: tracking page script](https://www.fancourier.ro/wp-content/themes/fancourier/dist/tracking-DIhaHmX7.js) – 阶段节点、状态行、签收后隐藏条目与退件 AWB
- [FAN Courier: F.A.Q.](https://www.fancourier.ro/en/faq/) – AWB、预约取件、短信、改派、当日转投 FANbox、PIN 码、付款、破损、MEEST、FANbox、Collect Point、网点与邮箱
- [FAN Courier: F.A.Q. (Romanian)](https://www.fancourier.ro/f-a-q/) – Collect Point 保留 7 天
- [FAN Courier: Contact](https://www.fancourier.ro/en/contact-us/) – 客服电话、时间、邮箱与 ȘteFAN
- [FAN Courier: Contact (Romanian)](https://www.fancourier.ro/contact/) – 普通资费通话说明
- [FAN Courier: Standard](https://www.fancourier.ro/standard/) – 1 至 3 天时效、派送时段、周六派送、两次尝试与 7 天保留期
- [FAN Courier: FANbox](https://www.fancourier.ro/en/services/fanbox-service/) – 24 小时开放、刷卡支付与柜机地图
- [FAN Courier: FAN locations](https://www.fancourier.ro/en/fan-locations/) – 营业厅、FANbox 与 Collect Point
- [FAN Courier: Terms and Conditions for the use of FANbox lockers](https://www.fancourier.ro/en/terms-and-conditions-for-the-use-of-fanbox-lockers/) – 30 天投柜、取件码、PIN、36 小时、延期、故障补时 24 小时、未取处理与 ANCOM
- [FAN Courier: Collect Point](https://www.fancourier.ro/collect-point-service/) – PayPoint 门店、限重与取件码
- [FAN Courier: General Conditions Regarding The Provision Of Postal Services](https://www.fancourier.ro/en/general-conditions-regarding-the-provision-of-postal-services/) – FAN Courier Express S.R.L.、时效、5 天保管、投诉、赔偿归寄件人及一年内申诉或诉讼
- [FAN Courier: Condiții generale](https://www.fancourier.ro/conditii-generale-privind-furnizarea-serviciilor-postale/) – 拒收与期限条款
- [FAN Courier: Despre FAN](https://www.fancourier.ro/despre-fan/) – 自 2006 年领先地位与摩尔多瓦办事处
- [FAN Courier: Press Release](https://www.fancourier.ro/en/fan-courier-press-release/) – 成立于 1998 年与通达国家
- [FAN Courier: SMS Link notification](https://www.fancourier.ro/en/fan-courier-implements-the-sms-link-notification/) – 含 PIN 码与链接的短信通知
- [FAN Courier: One Daily SMS](https://www.fancourier.ro/en/one-daily-sms-all-the-information-about-your-parcels/) – 每日汇总链接与 FANbox 包裹
- [FAN Courier: FAN Courier app](https://www.fancourier.ro/en/app/) – 3,000+ 自提柜、账号登录与自动延期
- [FAN Courier: Discover the FAN Courier Mobile App](https://www.fancourier.ro/en/discover-the-fan-courier-mobile-app/) – 免账号查询
- [FAN Courier: Terms of use of the FAN Courier Mobile App](https://www.fancourier.ro/en/terms-of-use-of-the-fan-courier-mobile-app/) – AWB、手机号账号、已登记订单与 Locations
- [ANCOM: Comanzi online? Ce obligatii au cei care iti livreaza colete?](https://www.ancom.ro/despre-noi/media/comunicate-de-presa/comanzi-online-63-ce-obligatii-au-cei-care-iti-livreaza-colete-63/) – 30 天交货期与 ANCOM 权限范围
- [Cargus: Track your parcel](https://www.cargus.ro/en/personal/track-your-parcel/) – Cargus 同样称运单号为 AWB
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Peek 读取的内容
:::
