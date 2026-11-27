---
title: Delhivery 印度快递查询：AWB 单号、状态与客服
description: 查询印度 Delhivery 境内外包裹：13/14 位 AWB 单号格式、RTO 等状态含义、派送规则、理赔与防骗提醒。
slug: delhivery-yindu-kuaidi-chaxun
published: 2026-11-25
updated: 2026-11-25
---

Delhivery 是一家印度物流公司，自称印度规模最大的全链路综合物流服务商。它为电商平台和商家派送订单，覆盖印度超过 18,850 个邮编区，并将包裹从印度发往 220 多个国家和地区。你可以在 [Delhivery 查件页面](https://www.delhivery.com/tracking)使用 AWB 单号、订单号（order ID）、LRN 或手机号查询包裹，或直接把 AWB 单号粘贴到上方查询框。AWB 单号由寄件人提供：网购订单请查看商家的发货短信或邮件，或直接向商家索取。

## Delhivery 运单号（AWB）格式

Delhivery 将快递单号称为 AWB（航空运单号，airway bill number），其网站查件页也接受 LRN。以下是 Peek 识别为 Delhivery AWB 的单号形态（数字为虚构示例）：

| 单号（虚构示例） | 说明 |
| --- | --- |
| `1482910573642` | 13 位数字：Delhivery AWB 单号。从 DPD 法国到韩国邮政，多家快递也使用 13 位数字。 |
| `14829105736425` | 14 位数字：同样是 Delhivery AWB 单号。DPD、SEUR、BRT、Xpressbees 等也使用 14 位数字。 |

仅凭位数无法直接断定属于 Delhivery，因此请核对商家消息中写明的承运商名称。订单号、LRN 或手机号仅在 Delhivery 网站可用，Peek 无法识别，必须输入 AWB。在 Delhivery 查件页面，查询 AWB 请选 “AWB” 标签页，查询网店订单号请选 “Order Id”；使用手机号登录后，则会列出所有由 Delhivery 派送给你的快件。找不到任何单号？参考[如何查找快递单号](guide:find-tracking-number)（英文）。

## Delhivery 物流状态含义

Delhivery 查件页面顶部有分步进度条，下方按城市列出已发生的扫描记录。以下是 Peek 已知的 Delhivery 英文文案，大致按出现顺序排列：

| Delhivery 显示状态 | 对你的含义 |
| --- | --- |
| `WAITING_PICKUP` | 已下单预订但尚未揽收（在无扫描记录前显示为 `Waiting pickup`）。 |
| `Picked Up` | Delhivery 已从寄件人处揽收快件。 |
| `PROD_REPLACED` | 换货订单中，快递员已取走待更换的旧商品。 |
| `In Transit`、`IN_TRANSIT`、`On the Way` | 正在 Delhivery 网络中运输；`IN_TRANSIT` 是同一状态的代码写法。 |
| `Dispatched` | 发出派送的扫描节点：包裹即将送达。 |
| `Out for Delivery` | 正在派送中（Delhivery 进度条上的对应步骤名）。 |
| `Undelivered` | 未能送达，正退回卖家。在货运快件上，Delhivery 会把这一名称套在 “On the Way” 节点上并沿用该节点日期，因此显示的日期不一定是实际尝试派送日。 |
| `Delivered` | 已签收送达；若包裹处于退回途中，则表示已退回并送达卖家。 |
| `REACHED_SEL_CITY` | 退回中的包裹已抵达卖家所在城市。 |
| `RTO` | 退回原寄地（Returned to origin）：包裹已退回并交还卖家。 |
| `DELIVERED_SELLER` | 退件已送达卖家。 |
| `DTO` | 买家退货件（由你寄回的包裹）已送达卖家。 |
| `LOST` | Delhivery 已将快件标记为丢失。 |
| `CANCELLED`、`Canceled` | 该快件或退货取件已被取消。 |

`Picked Up`、`On the Way` 和 `Out for Delivery` 多见于货运件，Delhivery 会按步骤而非单次扫描标注日期。更多见[物流状态说明](guide:tracking-statuses)（英文）。

## Delhivery 印度境内派送：来电、派送未成功与地址

**谁负责派送。** 由 Delhivery 自有网络派送：全国设有 4,500 多个直营派送中心，覆盖每个邦及 99.5% 的邮编区，包括印度东北部与查谟和克什米尔地区。官方称该网络全年 365 天、每天 24 小时不间断运行。

**电话与消息联系。** Delhivery 条款说明，Delhivery 或其派送服务商可能会致电、发短信或通过 WhatsApp 联系你，以安排派送、确认是否在家及处理异常，通话可能被录音。商家也可让 Delhivery 通过 AI 电话或 WhatsApp 向你核实订单。

**派送未成功。** 通过个人寄件服务 Delhivery Direct 预订的包裹，条款规定因收件人不在而 3 次派送未成功后将取消派送，寄件人可自费申请退回。网购订单则按商家与 Delhivery 的协议处理，请询问商家。为提高派送成功率，Delhivery 向商家提供 “WhatsApp-based NDR management” 与 “Smart NDR” 服务（含 AI 外呼、WhatsApp 确认和智能重派）。因此收到关于再次派送的电话或 WhatsApp 可能是真的，但 Delhivery 绝不会索要一次性验证码（OTP）或账户凭据来确认地址。参考[派送未成功怎么办](guide:missed-delivery)（英文）。

**投递地点。** 针对 Direct 个人寄件，条款规定 Delhivery 送达指定地址即可，未必亲自交到你本人手中，且绝不派送至邮政信箱；收件时可能需要签名或政府颁发的身份证件。经 Delhivery 同意后可申请更改放置点或到指定点自提，但它有权拒绝变更邮编区。周末与法定节假日不计入 Direct 服务的派送时效。

## Delhivery 寄往境外的包裹

**派送时效。** 针对使用 DLV Saver 出口的商家，Delhivery 估算发往英国需 5 至 7 天、发往美国需 8 至 12 天（该线路覆盖美、英、加、澳）。面向 220 多个国家和地区的 DLV Premium 时效为 3 至 6 天（Express）或 6 至 10 天（Deferred Express）。

**境外末端派送。** Delhivery 将 FedEx 和 Aramex 列为境外战略伙伴，但未说明具体由哪一家派送你的包裹。Peek 会在 Delhivery 系统中追踪 AWB；如果商家另提供了末端承运商单号，也可一并粘贴查询。

**清关与税费。** Delhivery 向商家承诺关税和税费全程透明，但未在页面中说明由谁支付：请向商家确认售价是否含税。在爱尔兰及欧盟其余关税区（不含黑尔戈兰岛、布辛根、利维尼奥、休达和梅利利亚等地），自 2026 年 7 月 1 日起，欧盟以外网购包裹货值不超过 150 欧元按每类商品征收 3 欧元关税；自 2026 年 11 月 1 日起，无论货值多少，还按每类商品加收 2 欧元欧盟处理费。其他国家适用当地规定，详见[包裹卡在海关怎么办](guide:customs)（英文）。

## Delhivery 物流不更新、丢件或退回

申请退款或补发请联系商家：由商家委托 Delhivery 承运。

**无更新。** 如果 Peek 仍显示“已生成面单”，说明商家只预订了 Delhivery 但尚未交出包裹，请联系商家。如果包裹已在流转但轨迹停滞，请用手机号登录 Delhivery 网站或应用：你能看到 Delhivery 派送给你的所有包裹并直接提交咨询。详见[物流长时间不更新怎么办](guide:tracking-not-updating)（英文）。

**丢失或破损。** `LOST` 表示 Delhivery 已标记包裹丢失，请通知商家。对于 Delhivery Direct 个人寄件，丢失或破损索赔必须在 3 天内通过应用工单提出；破损件需提供 3 张包裹与包装照片及文字说明，官方还建议拍摄开箱视频。根据该条款，赔付审核需 30 至 45 个工作日，未购买付费保障 Delhivery Protect 时最高赔付 2,000 印度卢比，且按品类设更低上限（如书籍文件 500 卢比、电子产品 1,000 卢比），延误不予赔偿。详见[包裹丢失怎么办](guide:lost-parcel)（英文）和[包裹破损怎么办](guide:damaged-parcel)（英文）。

**显示已签收但未收到。** 请先询问同住家人或大楼前台。根据 Direct 条款，错投必须在送达日起 3 天内报告；若你曾授权投递至信箱或自提柜，Delhivery 可应要求提供投递证明，但不承担丢失或破损责任。详见[显示已签收但未收到包裹](guide:delivered-not-received)（英文）。

**退回寄件人。** `RTO` 表示包裹已退回卖家，请联系商家退款或重新发货。详见[包裹退回寄件人](guide:returned-to-sender)（英文）。

## Delhivery 客服渠道与防骗提醒

Peek 无法代你联系 Delhivery 或商家。以下为 Delhivery 官方说明：

- **客服入口：** Delhivery 声明客服仅可通过其网站或应用联系。请在[帮助页面](https://www.delhivery.com/support)使用手机号加一次性验证码（OTP）登录后提交工单，或在 Delhivery 应用内发起咨询。
- **投诉主题：** 帮助页面的快件咨询类别包含 “Report Issues”、“Grievance Officer” 和 “Report on-field staff issues”，并列于 “Destination and Contact details” 与 “Returns” 旁。
- **电话警示：** Delhivery 警告称，网上搜到的其他客服渠道或联系电话均可能是诈骗。
- **社交媒体：** Delhivery 仅通过其官方 Facebook、X 和 Instagram 账号联系和服务客户，其他账号发来的消息均应视为可疑。

> Delhivery 提醒防范以“派送失败”为由导向仿冒网站的诈骗短信。官方绝不会索要 OTP 或凭据来确认地址，也不会要求提供 PIN 或 OTP 付款；切勿通过非 Delhivery 官方账号发来的链接付款。官方邮件仅来自 delhivery.com。如已蒙受资金损失，请拨打印度全国反诈热线 1930 并在 cybercrime.gov.in 报案；垃圾来电和短信请在 3 天内到 sancharsaathi.gov.in 举报。详见[防范快递诈骗短信](guide:delivery-scam-texts)（英文）。

## 使用 Peek 追踪 Delhivery 包裹

直接将 AWB 粘贴到 [Peek](/) 即可。由于多家快递公司都使用 13 位和 14 位数字，Peek 不会显示“识别出的快递公司”，而是显示“正在查询物流服务…”并向使用该形态的快递查询——当你身处印度（以手机地区设置或网页访问位置为准）时会优先询问 Delhivery；在其他地方，Peek 会在其他快递公司和物流查询服务都没查到这个单号后，最后询问 Delhivery。Delhivery 查到这个单号时，会显示“Delhivery 有这个包裹的记录。”如果暂时没有任何快递查到，仍可直接添加，或点“更换”手动选择 Delhivery：Peek 跟踪 4,500 多家快递公司。对于以 0 开头，或以 7、8、9 结尾的数字，Peek 不会询问 Delhivery，请自己选择 Delhivery。若你为非 13 或 14 位数字手动选择 Delhivery，首次查询会提示：“这家快递公司不使用这种格式的运单号。请检查单号，或选择其他快递公司。”

归入 Delhivery 后，在出现首次扫描前包裹会显示“等待 Delhivery 更新”。Peek 无需账号即可读取 Delhivery 公开轨迹，按 Delhivery 原文展示每条扫描及其所在城市，不含收件人详情或预计送达日期。Peek 将时间按印度时间读取并换算为你设备的时区；在 peektracker.com 及 iPhone、Android 应用中，轨迹底部会写明“时间按你所在的时区显示：”及对应时区名称。未发生的进度条步骤会被略过，缺少的货运节点年份则会根据星期推算。

Peek 会把节点归入自有阶段：`Picked Up` 归为“已揽收”，`In Transit` 归为“运输中”，`Dispatched` 归为“派送中”，`RTO` 归为“已退回寄件人”，而 `Undelivered`、`LOST` 或取消则归为“需要留意”。退回并送达卖家的包裹会显示为“已退回寄件人”，而非“已签收”。

在你打开包裹页面期间，或在派送当天开启提醒时，只要 Delhivery 自己的查件系统正常响应，Peek 最高每 10 分钟检查一次，在 Delhivery 扫描进入派送当天当地时间早上 6 点起每 2 分钟一次（通过物流查询服务时每小时检查一次，超过一天无新动态后每 3 小时一次，在派送当天打开页面或开启提醒时最高每 15 分钟检查一次）；其余时间，以及连续两天没有新动态后（仍停留在“已生成面单”或“已揽收”时为 12 小时后），每小时检查一次。提醒可以选择“每次扫描”、“重要环节”或“仅派送当天”。

## Delhivery 快递查询常见问题

### 如何查询 Delhivery 包裹？

在 delhivery.com/tracking 输入 AWB，或使用订单号、LRN 及手机号加验证码登录查询；也可以把 AWB 粘贴到 Peek 中一路跟进至签收。

### Delhivery 的 AWB 单号是什么？

即航空运单号（airway bill number），也是 Delhivery 的 13 位或 14 位快递单号，可在商家的发货短信或邮件中找到。

### Delhivery 物流里的 RTO 是什么意思？

即 Returned to origin（退回原寄地），表示包裹已退回并交还卖家。请联系商家退款或重新发货。

### Delhivery 客服电话是多少？

Delhivery 说明其客服仅可通过其网站或应用联系：使用手机号登录后提交工单即可，并提醒网上搜到的其他电话号码可能存在诈骗风险。

:::sources
- [Delhivery: Track your order](https://www.delhivery.com/tracking) – 可用手机号、AWB、订单号或 LRN 查件、防骗警示及仅限网站/应用客服
- [Delhivery: Support](https://www.delhivery.com/support) – AWB/LRN 查件、手机号登录、提交工单及含 Grievance Officer 的咨询类别
- [Delhivery: About us](https://www.delhivery.com/about-us) – 印度最大全链路综合物流商、邮编覆盖、派送中心与 24/7 运营
- [Delhivery: Home](https://www.delhivery.com/) – 220 多个国家和地区及 FedEx、Aramex 两家战略伙伴
- [Delhivery: Express Parcel](https://www.delhivery.com/services/express-parcel) – 99.5% 邮编覆盖、电商平台、WhatsApp/AI 电话确认、NDR 管理与智能重派
- [Delhivery: International](https://www.delhivery.com/services/international) – DLV Saver 与 DLV Premium 线路时效及税费说明
- [Delhivery: Personal Courier](https://www.delhivery.com/solutions/personal-courier) – Delhivery Direct 个人寄件
- [Delhivery: Terms and Conditions](https://www.delhivery.com/terms-and-conditions) – 派送来电、短信与 WhatsApp 沟通条款
- [Delhivery: TnC (Direct National Courier)](https://www.delhivery.com/tnc-direct) – AWB 定义、三次派送失败、派送地址、索赔与赔付限额
- [Delhivery: Fraud Disclaimer](https://www.delhivery.com/fraud-disclaimer) – 官方渠道、假短信、OTP 与付款提醒、1930 及 cybercrime.gov.in
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 150 欧元以下每类商品 3 欧元关税与 2 欧元欧盟处理费
- [European Commission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 无论货值按每类商品收取 2 欧元
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Delhivery 文案、Peek 读取和显示的信息、单号形态
:::
