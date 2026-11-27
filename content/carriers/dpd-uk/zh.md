---
title: DPD UK 英国快递查询：14 位单号、状态与自提点
description: 查询英国 DPD UK 包裹：14 位单号在哪里找、英语状态说明、Predict 派送选项、Pickup 门店自提以及客服联系方式。
slug: dpd-uk-tracking
published: 2026-11-24
updated: 2026-11-24
---

DPD UK 是一家英国包裹快递公司，为众多网店提供派送服务。你可以在 [DPD 查询页面](https://track.dpd.co.uk/)输入包裹单号和邮编查询，或直接把单号粘贴到上方查询框。单号来自发货商家、DPD 发送的短信或邮件，以及“sorry we missed you”未妥投通知卡。

## DPD UK 运单号在哪里找与常见格式

发货商家掌握你的运单号，你也可以直接点击 DPD 邮件或短信里的链接。如果司机留下了“sorry we missed you”通知卡，单号就印在条码旁边，DPD 查询页同样支持输入通知卡号查询。找不到单号？查看[如何查找运单号](guide:find-tracking-number)（英文）。

DPD 网站未说明单号格式。在 DPD UK 跟踪页上看到的包裹单号为 14 位数字，其中很多以 `1550` 或 `1597` 开头；DPD 页面可能还会在末尾附带一个校验字符。

| 单号（示例） | 说明 |
| --- | --- |
| `15504829105730` | DPD UK 包裹单号：以 `1550`（或 `1597`）开头的 14 位数字。 |
| `15504829105730K` | 同一个单号末尾加上 DPD 校验字符。 |
| `48291736058419` | 其他 14 位数字：可能是 DPD UK、其他国家的 DPD 或其他快递。 |

其他国家的 DPD 以及 [DHL eCommerce UK](carrier:dhl-ecommerce-uk)、SEUR 和 BRT 同样使用 14 位数字，因此光看数字无法断定由哪家承运。DPD 的通知可以作为依据：其短信发件人现为“DPD UK”（或“DPD Local”、“DPDgroup UK”），内容中包含商家名称。DPD Local 是 DPD 的姐妹品牌。爱尔兰境内的包裹则由使用独立单号的 [DPD Ireland](carrier:dpd-ie) 派送。

## DPD UK 物流状态说明

DPD UK 查询页仅提供英文版本。以下是大多数包裹依次会出现的状态：

| DPD 显示 | 中文含义 | Peek 显示 |
| --- | --- | --- |
| `We've received your order details, but have not yet received your parcel` | 商家已预约派送，DPD 尚未拿到实物包裹。 | “已生成面单” |
| `We've received your order details, and we're expecting your parcel shortly` | 面单已预约，DPD 正等待寄件人将包裹交到 DPD Pickup Shop。 | “已生成面单” |
| `Your parcel has been dropped off at a DPD Pickup Shop` | 包裹已投寄到 DPD Pickup Shop。 | “已揽收” |
| `We have your parcel and it's on its way to our depot`、`We have your parcel and it's on its way to you` | DPD 已揽收并正发往分拨中心，或正向你派送途中。 | “运输中”；若此前没有 Pickup Shop 投寄记录，发往分拨中心这一条会显示为“已揽收” |
| `Your parcel has arrived at our depot`、`Your parcel is at our depot` | 已到达 DPD 分拨中心，正在分拣。 | “运输中” |
| `Your parcel is at our depot ready for delivery` | 已在分拨中心备派，之后同一条会变为 `Your parcel is at our depot`。 | “运输中” |
| `A notification has been sent to advise you when the delivery will take place` | DPD 已向你发送含派送日期或一小时时间段的短信或邮件。 | 保持之前的状态 |
| `As requested, your parcel will now be delivered on Thu 05 November 2026 at 11:00` | 你已改约其他派送日期（有时附带具体时间）；日期和时间因件而异。 | 保持之前的状态 |
| `Your parcel will be with you today`、`Your parcel will be with you today between 14:18 and 15:18` | 今日派送中，若 DPD 给出预计送达时段会一并注明；时段因件而异。 | “派送中” |
| `As requested, your parcel will now be left in your selected safe place` | 你（或你的 DPD App 偏好设置）指定了安全放置点，司机将把包裹放在那里。 | 保持之前的状态 |
| `We missed you. Your driver won't be back today but we'll be back Wed 28 October with your parcel` | 司机未能送达，将于所示日期再次派送（日期因件而异）。DPD 原文还会列出司机姓名，Peek 会将其隐去。 | “派送未成功” |
| `Your parcel has been delivered` | 已送达；标题还会补充送达日期和时间。 | “已签收” |
| `Your parcel is waiting for you at home (left in safe place)` | 已送达并放在你指定的安全放置点。 | “已签收” |
| `Your delivery has been cancelled` | 寄件人已取消该次派送，DPD 会请你联系所示商家。 | “需要留意” |

`We have X-rayed your parcel as part of our normal security procedures` 属于例行安检扫描，无需任何操作（Peek 显示为“运输中”）。对于 DPD UK 发往海外的包裹，`Your parcel has left our sortation facility and is on it's onwards journey`（DPD 原文拼写如此）表示已离开英国，随后轨迹会接入当地派送合作商的扫描：`The parcel is in transit to its final destination`、`The parcel is undergoing customs clearance` 与 `Duties and Taxes paid`（Peek 显示为“清关中”）、`The parcel has now cleared customs`、`The parcel has arrived at the delivery depot`、`The parcel is on the vehicle for delivery`（“派送中”）以及 `The parcel has been delivered`（“已签收”）；未特别标注阶段的条目均显示为“运输中”。

## DPD 派送选项、Pickup 门店与派送未成功

**Predict 时段预告。** DPD 会通过短信和邮件发给你一个 1 小时预计派送窗口；在当天司机装车完成后发出，一般在中午 12 点前，随后实时地图还会进一步缩小到 15 分钟时段。你无法自行更改该时段本身。

**更改派送。** 在派送前一晚及当天，你可以通过短信、邮件或 DPD App 改约日期、指定邻居代收、选择安全放置点、转投 DPD Pickup Shop，或升级为 10:30 前、12:00 前、下午以及周六/周日派送。如果寄件人要求签收，部分选项不会开放。如需[更改收件地址](guide:change-delivery-address)（英文），请联系商家。

**家中无人。** DPD 会自动尝试交由邻居代收——其条款将邻居定义为步行 50 米范围内居住或工作的人。你可以提前指定首选邻居或安全放置点。否则司机会留下通知卡，你可以凭参考号和邮编改约日期、选择安全放置点、指定邻居或转投 Pickup Shop。DPD 网站未写明派送尝试次数。如果你当时在家却没等到司机，DPD 跟踪页会显示司机留下通知卡的位置并允许预约重派或自提；司机当天无法折返。显示已签收但找不到？由于 DPD 会自动找邻居代收，请先检查安全放置点并询问邻居，再参考[显示已签收但未收到](guide:delivered-not-received)（英文）。

**Pickup 自提门店。** DPD 拥有 6,000 多家 Pickup 门店，包括 Sainsbury's、Post Office、Co-op、Morrisons、Argos 和 Matalan 门店。改投至门店的包裹通常次日可取。取件须携带两份证件：带照片身份证件以及带地址的证明（如水电账单）。DPD 查询页会显示最晚取件日期，但网站未公布固定保管天数。DPD App 也支持投递到自提柜。

## DPD 包裹未送达、延误或破损

**延误。** DPD 的派送时间均为预计。若你所在区域出现延误，跟踪页可能会标明并给出新的预计日期，你也可以在 DPD [服务中断查询页](https://www.dpd.co.uk/content/products_services/uk_issues.jsp)按邮编查看。若多日没有更新，请看[为什么物流停止更新](guide:tracking-not-updating)（英文）并联系商家。

**丢失或破损。** 与 DPD 签有运输合同的是商家，因此由商家发起索赔。按 DPD 条款，寄件人须在发货后 14 天内申报丢失或破损，并在一年内提交理赔。请保留外包装并拍照留证。对于丢失件，DPD 可能会请你签署未收到确认函。

**无法派送。** 对于无法投递的包裹，DPD 最短可在 5 个自然日后将其退回。

**进口税费。** 从英国境外寄入的包裹可能产生增值税或关税，由派送公司代收。GOV.UK 说明派送公司通常会保管约 3 周，逾期未缴则退回寄件人。

> DPD 绝不会通过邮件、短信或 iMessage 里的链接向你收取“重新派送费”。若发件人是普通邮箱地址或陌生号码而非“DPD”，即为诈骗。可疑短信请转发至 7726。DPD 声明其自动通知通过普通短信而非 iMessage 发送，官方链接仅使用 dpd.co.uk、dpdlocal.co.uk 和 dpdgroup.co.uk，拼写近似的域名均为诈骗；可疑邮件请举报至 report@phishing.gov.uk。

## DPD UK 客服电话与联系方式

dpd.co.uk 公布的联系方式如下：

- **电话：** 0121 275 0500，周一至周五 8:00 至 18:30，周六 8:00 至 16:00，周日 9:00 至 15:00（银行假日除外）。DPD 预计等候时间约 5 至 10 分钟，请提前准备好包裹单号和邮编。
- **在线聊天：** 访问[联系我们页面](https://www.dpd.co.uk/content/how-can-we-help/contact.jsp)，页面注明全天候 24/7 开放，输入参考号和收件邮编即可开始。
- **DPD App：** 在跟踪地图下方点击聊天或致电按钮；DPD 表示通过 App 拨打客服电话可优先接入队列前端。
- **邮寄地址：** Customer Services, DPD UK, Roebuck Lane, Smethwick, B66 1BY。

## 使用 Peek 跟踪 DPD UK 包裹

[Peek](/) 会识别 14 位 DPD 单号，但由于该格式为多家快递共用，Peek 会向相关快递查询确认：以 `1550` 或 `1597` 开头时优先询问 DPD UK，其他情况则优先询问你所在国家的承运商。当 DPD UK 确认有记录时，快递栏会显示 DPD UK“查到了这个包裹”并持续跟进。如果显示“暂未找到 · Peek 会继续查询”或显示了别家快递，点击“更换”选择“DPD UK”即可。带有 `reference=` 或 `parcelNumber=` 的 track.dpd.co.uk 或 tracking.dpd.co.uk 链接会直接识别为 DPD UK。需要邮编才能查的订单号或取件参考号无法跟踪。

DPD UK 的扫描记录不带时区：Peek 按英国时间解析，换算为你设备的时区后按 DPD 原轨迹展示。在 peektracker.com 以及 iPhone 和 Android App 中，轨迹末尾会注明“时间按你所在的时区显示：”及对应时区名称。状态保留 DPD 英文原文，已取消的派送会显示为“需要留意”，支持开启状态变化通知。

DPD 不提供扫描地点，Peek 也不读取 DPD 的预计派送日或 Predict 1 小时时段，并会隐去你的个人信息、参考号和签收照片。Peek 会按 DPD 页面上的名称以“来自 …”显示下单商家，但由寄件人在 Pickup Shop 自投的包裹除外（预订该面单的账号并非实际发件方；在退货面单上，该账号就是包裹退回的商家）。DPD 会在数月后复用旧单号，因此旧单号可能会查到更新的包裹。

在你打开包裹页面时，或在派送当天开启了通知时，Peek 最高每 10 分钟检查一次；在 DPD 标注今日派送的当天，只要 DPD UK 自己的查询系统有响应，自英国时间早上 6 点起最高每 2 分钟检查一次（通过物流查询服务时，最高每 15 分钟检查一次）；其余时候每小时检查一次。连续两天无新动态后（若仍停留在“已生成面单”或“已揽收”，则为 12 小时后），改为每小时检查一次。通知支持“每次扫描”、“重要环节”（派送、自提、清关、派送未成功与退件）或“仅派送当天”。Peek 无法代你更改派送，请在 DPD 网站或 App 中操作。

## DPD UK 快递查询常见问题

### DPD 属于 Royal Mail 吗？

不属于。DPD UK 由 DPDgroup UK Ltd 运营，隶属于 Geopost，而 Geopost 属于法国邮政 [La Poste](carrier:la-poste) 集团。

### DPD 在英国派送需要几天？

取决于商家选择的服务。DPD Next Day 目标为次工作日送达；海峡群岛、马恩岛和锡利群岛标准时效为两天。

### 怎么查看我的 DPD 司机走到哪里了？

派送当天，DPD 的 Predict 短信或邮件会给出司机姓名、你在派送顺序中的排位，以及可实时追踪的地图。

### DPD Pickup 门店会保管包裹多久？

DPD UK 未公布固定天数，跟踪页面上会明确标注该包裹的最晚取件日期。

:::sources
- [DPD UK: Help](https://www.dpd.co.uk/content/how-can-we-help/index.jsp) – 包裹单号、通知卡、1 小时窗口、邻居代收、安全放置点、Pickup 门店、证件、未妥投、通知卡位置、改地址与公司信息
- [DPD UK: Contact Us](https://www.dpd.co.uk/content/how-can-we-help/contact.jsp) – 客服电话、服务时间、预计等候、24/7 在线聊天与 App 优先排队
- [DPD UK: Predict & Follow My Parcel](https://www.dpd.co.uk/content/products_services/predict_fmp.jsp) – 1 小时窗口、15 分钟时段、司机姓名、改派选项、10:30/12:00/下午/周六或周日升级
- [DPD UK: Follow My Parcel](https://track.dpd.co.uk/) – 查询字段、最晚取件日、延误、进口费与状态表述
- [DPD UK: Cyber Security, phishing and smishing](https://www.dpd.co.uk/content/about_dpd/phishing.jsp) – 短信发件人名称、防诈骗警示、7726、短信提醒、官方链接域名与 report@phishing.gov.uk
- [DPD UK: DPD Pickup](https://www.dpd.co.uk/content/products_services/dpd_pickup.jsp) – 途中改投 Pickup 自提点
- [DPD UK: The DPD app](https://www.dpd.co.uk/dpdapp/) – App 派送选项与自提柜
- [DPD UK: App FAQs](https://www.dpd.co.uk/lp/app/index.html) – DPD UK 与 DPD Local 包裹及 App 聊天/致电
- [DPD UK: Standard Terms & Conditions of Carriage](https://www.dpd.co.uk/pdf/dpd-stcc.pdf) – 客户与收件人、邻居定义、预计时效与索赔时限
- [DPD UK: Complaints Charter](https://www.dpd.co.uk/pdf/dpd-complaints-charter.pdf) – 投诉处理时限、在线聊天时间与邮寄地址
- [DPD UK: Return, Disposal and Destruction of Goods Policy](https://www.dpd.co.uk/return-disposal-of-goods-policy.jsp) – 5 个自然日后退回规则
- [DPD UK: UK Delivery Services](https://www.dpd.co.uk/content/products_services/uk_delivery_services.jsp) – Next Day、10:30 与 12:00 服务及离岛时效
- [DPD UK: Service Disruption](https://www.dpd.co.uk/content/products_services/uk_issues.jsp) – 按邮编查询受影响区域
- [GOV.UK: Tax and customs for goods sent from abroad](https://www.gov.uk/goods-sent-from-abroad/tax-and-duty) – 派送公司账单与约 3 周保留期
- [Geopost: About us](https://www.geopost.com/en/about-us/) – 隶属 La Poste Groupe
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Peek 读取的内容：以 1550 或 1597 开头的单号、识别方式、状态原文及其对应阶段、提示信息与商家名称
:::
