---
title: India Post 印度邮政查询：Speed Post 单号与状态
description: 查询印度邮政 India Post 与 Speed Post 包裹：IN 国际单号格式、物流状态说明、投递时效、海外衔接追踪、投诉理赔及客服热线。
slug: india-post-tracking
published: 2026-11-24
updated: 2026-11-24
---

India Post（印度邮政部 Department of Posts）是印度的国家邮政机构，据其公布拥有 164,999 家邮局，是全球规模最大的邮政网络。印度境内提供 Speed Post、挂号信及 India Post Parcel 服务，国际业务涵盖 EMS（International Speed Post）、可追踪小包和航空包裹。你可以在 [indiapost.gov.in](https://www.indiapost.gov.in/) 的查询框中查件（需输入验证码），或直接把单号粘贴到上方输入框中。India Post 将其称为 article barcode number（邮件条码号）；网购订单请向卖家获取。

## India Post 运单号格式

India Post 运单号遵循[国际邮政单号标准](guide:tracking-number-formats)（英文），共 13 位字符：2 位字母、9 位数字加 `IN`，第 9 位数字为校验位。以下数字均为虚构：

| 格式（示例） | 说明 |
| --- | --- |
| `EE482910576IN` | 由 India Post 编号的印度境内或寄往海外的邮件。前两位字母各不相同，末尾的 `IN` 才是关键。 |
| `EE482910576US` | 以其他国家代码（如 `US`）结尾：由发件国邮政编号。从海外寄往印度的包裹通常带有此类单号。 |

India Post 官方系统支持查询 Speed Post、挂号和保价邮件、商业及特快包裹、电子汇款单以及国际 EMS。找不到单号？请参阅[如何查找运单号](guide:find-tracking-number)（英文）。

## India Post 物流状态中文说明

India Post 常见问题将流程概括为收寄、接收、装袋、发运、派送中和已签收。以下大致按顺序列出 Peek 所知的 India Post 扫描记录：

| India Post 状态原文 | 对你的含义 |
| --- | --- |
| `Item Booked` | 寄件人已交寄，或在线下单并由 India Post 受理。 |
| `Bag Received for Forwarding` | 装有该邮件的邮袋已到达处理局，等待转发。 |
| `Assigned to load plan` | 已排入下一班次运输装载计划。 |
| `Transport Leg Completed` | 运输途中的某一段航程或路程已完成。 |
| `Transfer to OOE (Otb)` | 正在发往负责出境邮件的国际互换局（OOE）。 |
| `Aircraft Departure` | 载有该邮件的航班已起飞。India Post 有时写作 `UPLIFT`。 |
| `Bag unloaded at destination Airport` | 邮袋已在目的国机场卸机。 |
| `Send item to domestic location (Inb)` | 目的国国际互换局已将邮件发往该国国内邮政网络。 |
| `Item Delivered` | 已签收。 |

`(Otb)` 标记发件国邮政的扫描，`(Inb)` 标记由 India Post 转发的目的国邮政扫描。在 Peek 读取的数据中，India Post 现常返回类似 `BAG_DISPATCH` 的代码；Peek 会将已知代码展开为易读词句（如“Bag Dispatched”或“Item Presented To Customs”），其余代码也会转成自然词组。更多解释见[物流状态含义](guide:tracking-statuses)（英文）。

## 印度境内接收 India Post 邮件

**派送时效。** India Post 公布的 Speed Post 自收寄至送达标准为：同城 1 至 2 天、大都市之间 1 至 3 天、邦首府之间或邦内 1 至 4 天、全国其他地区 4 至 5 天（由支局投递的地区顺延 1 天）。24 Speed Post 在六大都市（新德里、孟买、班加罗尔、金奈、加尔各答和海得拉巴）每周七天提供次日达；若延误，寄件人可在 7 天内在线申请退还邮资。

**短信与一次性验证码（OTP）。** Speed Post 包裹到达派送邮局以及签收时，登记的手机号会收到短信通知。部分 Speed Post 邮件交付时须核验一次性验证码（OTP）：该项为 24 Speed Post 标配，普通 Speed Post 可付费选配。

**派送未成功。** 当邮局签发投递通知（Delivery Intimation Notice）提示邮件可供投递时，India Post 物流会显示对应记录。另见[派送未成功指南](guide:missed-delivery)（英文）和[逾期未取包裹指南](guide:uncollected-parcel)（英文）。

**修改地址。** 通过 India Post 账号在线下单的寄件人，在邮件签收或交由邮递员派送之前，可申请召回或改寄。

## 发往海外的 India Post 包裹：派送方与海关

India Post 的国际可追踪服务包括 EMS（国际特快专递，限重 35 公斤）、面向跨境卖家的国际可追踪小包（International Tracked Packet，限重 5 公斤）以及国际航空包裹（限重 20 公斤）。包裹抵达目的国后，标有 `(Inb)` 的扫描来自当地邮政：你可以沿用同一个 `IN` 单号在 [USPS](carrier:usps)、[Royal Mail](carrier:royal-mail)（EMS 为 Parcelforce）、An Post、Canada Post 或 [Australia Post](carrier:australia-post) 继续查询。

**欧盟海关。** 印度属于欧盟以外地区，包裹须在你所在的国家办理清关。自 2026 年 7 月 1 日起，从欧盟外网购且订单金额不超过 150 欧元的商品按项征收 3 欧元欧盟关税；自 2026 年 11 月 1 日起，无论货值多少，还需按项缴纳 2 欧元欧盟处理费（“项”指包裹中税则归类、货品描述及必要时原产地一致的商品）。India Post 将完税后交货（DDP）列为出口商可选附加项，请询问卖家是否使用。其他国家按当地海关规定执行，详见[清关中的包裹指南](guide:customs)（英文）。

**线路暂停通知。** 当发往某国的邮政服务暂停或恢复时（例如 2025 年 10 月暂停发往加拿大、2026 年 8 月恢复发往伊拉克、科威特和黎巴嫩），India Post 会在其[新闻与公告](https://www.indiapost.gov.in/media/newsandupdates)页面发布通告。

**寄往印度。** 从海外寄给你的包裹带有发件国邮政单号，请先在发件国邮政追踪；India Post 常见问题提醒，境外邮政机构的回传信息可能会有延迟。

## India Post 物流不更新、丢失或退回

**一段时间没有新扫描。** India Post 说明，缺少扫描并不意味着包裹未寄出：记录上网可能需要时间，在乡村地区或来自境外邮政时最为常见。发往海外的包裹一旦出现 `Aircraft Departure`，请改用同一个单号在目的国邮政查询。另见[物流为什么停止更新](guide:tracking-not-updating)（英文）。

**丢失、延误或破损。** 超过服务标准时限后，须在交易日起 60 天内凭邮件条码号提交投诉；India Post 的标准处理时限为 90 天。印度境内 Speed Post 延误退还邮资，丢失、失窃或破损按双倍邮资或 1,000 卢比（取较低者）赔付。国际 EMS 物品按货值或 130 特别提款权（SDR，取较低者）加邮资赔付，超出公布时限 5 天以上赔付邮资的 5%。国际可追踪小包按货值或 1,000 卢比（取较低者）赔付。如果是网购订单，请同时通知商家：详见[包裹丢失指南](guide:lost-parcel)（英文）和[包裹破损指南](guide:damaged-parcel)（英文）。

**显示已签收但未收到。** 请按下方渠道投诉，或在各邮局备置的意见簿登记投诉；另见[显示已签收但未收到](guide:delivered-not-received)（英文）。

**退回寄件人。** 在线交寄时，寄件人可指定不同于自身地址的退件地址。Peek 将退件显示为“已退回寄件人”，详见[包裹退回寄件人指南](guide:returned-to-sender)（英文）。

## India Post 客服热线与投诉渠道

India Post 公布的官方渠道如下：

- **免费客服热线：** 1800 266 6868。人工客服时间为印度时间 8:00 至 20:00；自动语音热线全年 24 小时运行（含周日，法定节假日除外）。
- **提交投诉：** 可在 India Post [在线投诉页面](https://www.indiapost.gov.in/grievance-redressal/file-a-complaint)直接提交并追踪进度（无需注册账号），也可到邮局向局长或邮政监管员投诉，或致电呼叫中心。
- **升级申诉：** 若 India Post 未能在合理时间内解决，可向印度政府公共申诉局（dpg.gov.in）升级投诉。

India Post 提醒谨防假冒其名义的钓鱼链接：请仅访问 www.indiapost.gov.in，切勿通过任何链接泄露 OTP 验证码或银行账户、银行卡及 UPI 信息。更多见[快递诈骗短信指南](guide:delivery-scam-texts)（英文）。

## 使用 Peek 查询 India Post 包裹

对于 2 位字母、9 位数字加 `IN` 且校验位正确的单号，[Peek](/) 会直接识别为 India Post 并显示“识别出的快递公司”；点击“更换”可切换其他承运商。如果校验位有误，Peek 会提示“这个单号的校验位对不上。”：请核对单号，或点击“按输入的单号跟踪”。归入 India Post 后，在首次扫描出现前页面显示“等待 India Post 更新”。

Peek 不直接读取 India Post 官网，而是通过从 India Post 获取扫描数据、但不在 India Post 域名下的查询网站 MySpeedPost（或当 MySpeedPost 转发查询时通过其运营方的 track91）获取数据。如果该副本超过 30 分钟未更新，Peek 会请求 MySpeedPost 刷新，若刷新失败则保留旧副本，因此扫描可能较晚才到达 Peek。

Peek 保留 India Post 的英文状态文案，显示处理邮局及印度境内的 PIN 邮编（track91 不提供邮编），并在位置核实后标于地图上。航班起飞显示为“Aircraft Departure”，若 India Post 给出航线信息还会附带航班号与起降机场。Peek 会将记录归类到对应阶段：派送未成功归为“派送未成功”，拒收、破损、遗失或地址不全归为“需要留意”。通过 track91 返回的文案更简短：`Dispatched`（互换局发往海外）和 `Aircraft arrival in Transit country`（经停中转枢纽）显示为“运输中”，`Delivered` 显示为“已签收”。Peek 会去除收件人姓名、地址与签名，且不显示预计送达日期。印度境内的扫描按印度时间解析，包裹抵达目的国或出现首条 `(Inb)` 扫描后的记录（无论来自 MySpeedPost 还是 track91）按目的国时钟解析，并统一换算为你设备的时区显示；在 peektracker.com 以及 iPhone 和 Android 应用中，物流历史底部会注明所在时区。

当 India Post 标明了目的国，或未标明而你在印度境外查件时，Peek 还会用同一 `IN` 单号询问目的地对应承运商，如 USPS、Royal Mail、Canada Post、Australia Post、德国 [DHL](carrier:dhl) 或法国 [La Poste](carrier:la-poste)，并在对方出现新扫描后切换跟踪且保留 India Post 历史。Peek 在欧洲大部分国家和部分其他国家（包括新西兰的 NZ Post、卡塔尔的 Qatar Post）都有可询问的承运商，但在阿联酋、科威特、阿曼、新加坡、马来西亚和斯里兰卡没有。Peek 不会向 India Post 查询其他国家签发的单号：若强行选择 India Post，会提示“不使用这种格式的单号”。

当你打开包裹页面查看，或在派送当天开启通知时，Peek 最高每 10 分钟检查一次，进入派送中后最高每 2 分钟检查一次；其余时间以及连续两天无新动态时（包裹仍处于“已生成面单”或“已揽收”状态时为 12 小时）每小时检查一次。派送当天的 2 分钟刷新自当地时间早上 6 点起在 MySpeedPost 或目的国邮政接口正常响应时运行；完成衔接后遵循目的国邮政的刷新节奏。通过其他物流查询服务时，在派送当天打开页面或开启提醒时最高每 15 分钟检查一次。通知支持选择“每次扫描”、“重要环节”或“仅派送当天”。如果包裹在 India Post 阶段长时间停滞，“向 India Post 寻求帮助”会直接打开 India Post 联系页面。Peek 无法代你联系 India Post、目的国邮政或商家。

## India Post 物流查询常见问题

### 如何查询 India Post Speed Post？

在 indiapost.gov.in 输入以 `IN` 结尾的 13 位单号，或直接粘贴到 Peek 中——即使寄往海外也能全程衔接追踪至送达。

### 为什么我的 India Post 物流不更新？

扫描上传可能需要时间，尤其是来自乡村邮局或境外邮政的记录。如果寄往海外的包裹停留在 `Aircraft Departure`，说明已离境，请改用同一单号在目的国邮政查询。

### Speed Post 派送需要多久？

India Post 的标准时效为大都市间 1 至 3 天、全国其他地区 4 至 5 天（支局投递加 1 天）。24 Speed Post 在六大都市提供次日达。

### India Post 客服电话是多少？

免费热线 1800 266 6868：印度时间 8:00 至 20:00 提供人工服务，自动语音热线除法定节假日外全天 24 小时运行。

:::sources
- [India Post: Home](https://www.indiapost.gov.in/) – 查询框、热线电话与防诈骗提示
- [India Post: Contact Us](https://www.indiapost.gov.in/help&Support/contactus) – 免费电话、服务时间与邮局现场投诉
- [India Post: Help and FAQs](https://www.indiapost.gov.in/grievance-redressal/help-faq) – 条码号、可追踪业务、状态、延误、投诉、召回改寄与退件地址
- [India Post: About Us](https://www.indiapost.gov.in/ministry/aboutus) – 印度邮政部及 164,999 家邮局数据
- [India Post: Speed Post](https://www.indiapost.gov.in/mail&Parcel/mailproducts/premiumservices/speedpost) – OTP、时效标准、短信通知与赔付规则
- [India Post: 24/48 Speed Post FAQs](https://www.indiapost.gov.in/speedpost/faqs) – 六大都市次日达、OTP 与延误退邮资
- [India Post: India Post Parcel](https://www.indiapost.gov.in/mail&Parcel/mailproducts/domesticservices/indiapostparcel) – 印度国内邮政包裹说明
- [India Post: International Mail](https://www.indiapost.gov.in/mail&Parcel/mailproducts/mailservices/international) – 国际服务与出口商 DDP 选项
- [India Post: International services](https://www.indiapost.gov.in/mail&Parcel/mailproducts/internationalservices) – EMS、国际可追踪小包、航空包裹限重与赔付
- [India Post: News and Updates](https://www.indiapost.gov.in/media/newsandupdates) – 各国线路暂停与恢复通告
- [India Post: File a Complaint](https://www.indiapost.gov.in/grievance-redressal/file-a-complaint) – 在线投诉与升级申诉说明
- [India Post: Guidelines on Complaint Registration](https://www.indiapost.gov.in/grievance-redressal/guidelines) – 投诉时限与受理窗口
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 3 欧元关税与 2 欧元欧盟处理费
- [European Commission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 按商品项计费及定义说明
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – 扫描文案（含 track91 的文案）、Peek 读取的内容及所用时钟、Peek 接下来询问哪家邮政
:::
