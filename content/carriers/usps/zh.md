---
title: USPS 美国邮政单号查询：运单格式、英文状态与客服电话
description: 查询美国邮政（USPS）快递包裹：22/26 位与 US 国际单号规则、常见英文状态含义、二次派送与邮局自提、丢件寻查及客服热线。
slug: usps-meiguo-youzheng-chaxun
published: 2026-11-24
updated: 2026-11-24
---

USPS（美国邮政，U.S. Postal Service）覆盖全美每一个收件地址，且大多数美国国内包裹服务均免费附带物流追踪。你可以在 `usps.com` 的 USPS Tracking 输入单号查询、发送单号短信至 `28777`，或直接把运单号粘贴到上方查询框中。运单号由寄件人提供，通常写在商家的发货确认邮件里。

## 如何查找与识别 USPS 运单号

在包裹面单上，运单号印在条形码下方。作为收件人如果遗失单号，需联系寄件人索取：邮局网点和客服中心均无法代查单号。已开通 Informed Delivery 的美国收件人可以在个人面板查看即将寄达的包裹单号。详见[如何查找快递单号](guide:find-tracking-number)（英文）。

USPS 运单号长度为 13 至 34 位字符，以下是最常见的格式（均为虚构示例）：

| 单号（虚构） | 归属与说明 |
| --- | --- |
| `9400 1482 9173 6058 2917 46` | 以 `94`、`95`、`93`、`92` 或 `91` 开头的 22 位数字：最常见的美国国内包裹面单。 |
| `9205 5948 2917 3605 8291 7406 88` | 以 `92` 开头的 26 位数字。 |
| `420 90210 9400 1482 9173 6058 2917 46` | 扫码枪读取的完整条码：`420` + 5 位美国邮编 + 运单号（开头的邮编段可省略）。 |
| `RR482917367US` | 国际邮政单号：2 位字母、9 位数字加 `US` 结尾。 |

未妥投通知单（PS Form 3849）上的条码编号同样可以直接在 USPS Tracking 中查询。

部分单号格式与其他承运商存在重叠：以 `9261` 或 `9361` 开头的 22 位数字也可能属于 DHL eCommerce，FedEx Ground Economy 单号有时会在前面加 `92`，20 位纯数字也常见于其他快递。若 USPS 查无此号，请核对商家邮件中标明的快递公司。详见[运单号格式说明](guide:tracking-number-formats)（英文）。

## USPS 英文物流状态含义解析

USPS 轨迹均以英文发布，包裹通常会依次出现以下常见状态：

| USPS 英文状态 | 对你的含义 |
| --- | --- |
| `Merchant Order Receipt Notification` | 商家系统发出的已接单电子通知，尚未产生任何物理扫描；USPS 尚未收到包裹。 |
| `Shipping Label Created, USPS Awaiting Item` | 寄件人已打印面单，USPS 尚未收到实物包裹；相关疑问请联系网店。 |
| `Picked Up by Shipping Partner, USPS Awaiting Item` | 第三方合作承运商已揽收并将转交 USPS；预计送达日需待 USPS 实收后生成。 |
| `USPS in Possession of Item` | USPS 已正式揽收或收到实物包裹，无需任何操作。 |
| `Arrived at USPS Facility` | 正在 USPS 分拣中心处理并运往你所在邮局（同一地点或不同日期可能重复出现）。 |
| `Arrived at Post Office` | 已到达你附近的投递邮局，准备今天或下一个邮政工作日派送。 |
| `Out for Delivery` | 邮递员今天正在派送途中（可能与日常信件投递时间不同）。 |
| `Delivered` | 已送达，并记录了日期、时间与投递地点。 |
| `Delivery Status Not Updated`、`Awaiting Delivery` | 派送当天当地时间晚 9 点前未录得签收扫描，USPS 预计顺延至下一个工作日送达。 |
| `No Access to Delivery Location` | 邮递员无法接近信箱或门口；USPS 会在下一个工作日自动再次投递。 |
| `Notice Left (No Authorized Recipient Available)` | 需要本人或授权人签收但当时无人；已留通知单，不会自动二次派送。 |
| `Available for Pickup` | 无法上门投递，请自下一个工作日起前往邮局自提。 |
| `Alert` | 出现异常延误，例如恶劣天气或其他突发情况。 |

注意：`Available for Pickup` 并非可改约派送的选项，USPS 明确规定该状态不可预约重派，收件人须携带带照片的有效身份证件前往领取；`Notice Left (No Secure Location Available)` 与签收通知单类似，也不会自动重派。出现 `Insufficient Address` 或 `No Such Number` 说明地址不全或无此门牌，包裹将退回寄件人。对于发往海外的国际邮件，`Attempted Delivery Abroad` 表示目的国邮政投递失败（Peek 显示“派送未成功”）；`Held at Delivery Depot/Delivery Office` 表示当地邮局暂存待取；`Payment of charges - Item being held, addressee being notified` 表示目的国邮政因待缴进口税费暂扣并通知收件人（Peek 显示“清关中”）。

## 错过派送：免费二次派送、邮局自提与智能快递柜

[投递未成功](guide:missed-delivery)（英文）后，邮递员会留下桃色的 PS Form 3849 通知单并注明原因，你可以选择：

- **预约免费重派（Redelivery）：** 通过 `usps.com/redelivery`、USPS Tracking 页面、扫描通知单二维码、致电或签字后放回信箱申请。退件前仅可免费申请一次且限原地址、不可指定具体时段；周一至周六中部时间凌晨 2 点前在线提交可安排当日重派（部分区域不提供）。
- **前往邮局自提：** 凭带照片的身份证件及通知单到指定邮局领取（他人代领须持你的书面授权及代领人证件）。USPS Tracking 会显示该邮局地址与营业时间。
- **退回寄件人：** 如果不再需要该包裹，可申请退回。

**邮局保管期限：** 多数带追踪包裹（含 Priority Mail 与 Ground Advantage）在第 5 天发出最终提醒，满 15 天退回寄件人；Priority Mail Express 在首次尝试 5 天后退回；多数境外来件保管 30 天。投递至 USPS Smart Locker 智能柜的包裹在柜内保留 5 天，随后移至邮局柜台再保留 10 天。

## USPS 包裹延误、丢失或显示已签收但未收到

先核对 USPS 参考时效：Priority Mail 为 2–3 个工作日，Ground Advantage 为 2–5 个工作日，Parcel Select 为 2–8 个工作日，其中仅 Priority Mail Express 提供时效保证。周一至周六常规投递截止时间为当地时间下午 5 点。

如果 `Out for Delivery`、`Arrived at Post Office` 或 `Delivery Status Not Updated` 停滞超过一个完整邮政工作日，或显示 `Delivered` 超过一个工作日后仍未在门廊、信箱、车库、家人或物业处找到，可通过 USPS [包裹查询表单](https://emailus.usps.com/s/package-inquiry)提交服务请求。详见[显示已签收但未收到](guide:delivered-not-received)（英文）。

寄出满 7 天后，寄件人或收件人可提交[遗失邮件搜寻（Missing Mail search）](https://www.usps.com/help/missing-mail.htm)，附上收寄地址、物品描述及照片。

**理赔规则：** 仅已投保（insured）邮件支持理赔。寄件人或收件人均可申请，但需提供原始邮寄收据。破损须在寄出后 60 天内索赔；遗失须在寄出 15 天后（Priority Mail Express 为 7 天后）至 60 天内索赔，处理完毕前请保留原外箱与物品。

## USPS 官方客服电话与防诈骗提示

USPS 官网公布的官方联系渠道如下：

- **`1-800-ASK-USPS`（`1-800-275-8777`）：** 美东时间周一至周五 `08:00–20:30`、周六 `08:00–18:00` 提供人工服务，全天候提供语音自助查询。
- **`1-800-222-1811`：** 其他追踪问题专线，服务时段相同（USPS 提示客服看到的轨迹与官网一致）。
- **在线客服：** [USPS 联系我们页面](https://www.usps.com/help/contact-us.htm)提供邮件表单及网站问题在线聊天。
- **听障中继服务：** 拨打 `7-1-1`。

> USPS 仅在你主动用运单号订阅更新后才会发短信或邮件，且官方消息绝不包含任何网址链接，重派也不收取费用。收到假冒 USPS 的短信请转发至 `7726` 并举报至 `spam@uspis.gov`。发往美国以外的包裹若需缴纳进口税费，则由目的国当地邮政或快递通过其官方渠道通知。

## 使用 Peek 追踪 USPS 包裹

在 [Peek](/) 中输入校验通过的多数 22 位 USPS 面单、26 位 `92` 开头单号或 `420` 开头扫码条码（Peek 会自动去掉前面的邮编段），系统都会立刻识别为 USPS。对于共享格式（如 `9261`/`9361` 开头或 20 位数字），Peek 会先询问其他可能承运商及聚合追踪服务，最后打开 USPS 页面，因此结果可能需要稍候片刻。粘贴 `usps.com` 追踪链接同样有效。

Peek 通过自建服务器浏览器读取 USPS 官网页面；由于速度较慢且 USPS 有时会拒绝访问，第三方追踪服务会在必要时补全（但未必保留全部 USPS 历史）。Peek 会展示当前阶段、带地图标注的每次扫描（按事件发生所在州时区读取并自动换算为你设备的本地时间）及预计送达日，并支持“每次扫描”、“重要环节”或“仅派送当天”通知预设，同时隐藏签收人与投递说明。

扫描保持 USPS 英文原文，妥投行显示为“已签收”。当 [UPS](carrier:ups) 或 [FedEx](carrier:fedex) 将包裹转交 USPS，或国际邮件入境美国后，一旦 USPS 出现新进展，Peek 即可无缝切换至 USPS 跟进。申请重派及 Informed Delivery 仍需在 `usps.com` 办理。

## 关于 USPS 美国邮政追踪的常见问题

### 为什么 USPS 显示 `Tracking Not Available`？

表示 USPS 尚未收录该单号或历史记录已过期清除。请核对数字是否有误；USPS 对大多数追踪记录保留 120 天。

### USPS 周日派送吗？

根据 USPS 说明，仅 Priority Mail Express 与 Amazon 包裹提供周日派送，其余服务均为周一至周六派送。

### 收到关于包裹的 USPS 短信是诈骗吗？

如果你并未主动订阅单号提醒，通常就是诈骗。真正的 [USPS 官方短信](guide:delivery-scam-texts)（英文）仅在用户使用单号主动登记后发送，且绝不附带链接。位于美国以外的收件人，进口税费由本国邮政通过正规渠道收取。

### USPS 追踪会显示精确的送达时间吗？

不会。系统有时会提供 2 小时的参考时间窗，但不具保证效力，USPS 也无法预知邮递员抵达你家门口的准确时刻。

:::sources
- [USPS: 包裹去哪了？追踪状态帮助](https://faq.usps.com/articles/FAQ/Where-is-my-package) – 状态含义、服务请求与已签收未收到排查
- [USPS: USPS Tracking 基础说明](https://faq.usps.com/articles/Knowledge/USPS-Tracking-The-Basics) – 免费追踪、参考时间窗、120 天保存期与 Alert
- [USPS: 如何查找运单号](https://faq.usps.com/articles/Knowledge/How-to-find-your-tracking-number) – 联系寄件人、Informed Delivery 与通知单条码
- [USPS: 短信查件常见问题](https://www.usps.com/text-tracking/welcome.htm) – 短信查件 `28777` 与 `420` 邮编前缀
- [USPS: 重派服务基础说明](https://faq.usps.com/articles/Knowledge/Redelivery-The-Basics) – 免费重派、单次限制、截止时间与申请方式
- [USPS: PS Form 3849 未妥投通知单](https://faq.usps.com/articles/Knowledge/PS-Form-3849-Redelivery-Notice) – 通知单与可选处理方式
- [USPS: 二次与最终提醒及退件日期](https://faq.usps.com/articles/Knowledge/What-are-the-Second-and-Final-Notice-and-Return-Dates-for-Redelivery) – 5 天/15 天/30 天保管规则
- [USPS: 前往邮局领取暂存邮件](https://faq.usps.com/articles/Knowledge/Picking-Up-Mail-that-is-Being-Held-at-Your-Post-Office) – 身份证件、代领授权与邮局信息
- [USPS: USPS Smart Locker 智能柜](https://faq.usps.com/articles/Knowledge/USPS-Smart-Locker) – 柜内 5 天 + 柜台 10 天规则
- [USPS: 接收邮件与包裹管理](https://www.usps.com/manage/) – Delivery Instructions 与 Package Intercept
- [USPS: 邮件与包裹延误说明](https://faq.usps.com/articles/Knowledge/Delayed-Mail-and-Packages) – 参考时效、下午 5 点截止与周日派送范围
- [USPS: 遗失邮件搜寻](https://www.usps.com/help/missing-mail.htm) – 寄出 7 天后提交 Missing Mail search
- [USPS: 提交理赔申请](https://www.usps.com/help/claims.htm) – 投保理赔资格、时限与 13–34 位单号长度
- [USPS: 联系我们](https://www.usps.com/help/contact-us.htm) – 客服电话、服务时间、表单与 `7-1-1` 中继
- [USPS: 诈骗与钓鱼警示](https://faq.usps.com/articles/Knowledge/Scams-Scheme-Alerts) – 常见诈骗话术与免费重派声明
- [U.S. Postal Inspection Service: 短信钓鱼防骗](https://www.uspis.gov/news/scam-article/smishing/) – 仅按需发短信及举报号码 `7726`
- [USPS: 企业概况](https://about.usps.com/who/profile/) – 全美地址覆盖
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Peek 对 USPS 追踪页的解析及国际邮件境外投递/海关状态映射
:::
