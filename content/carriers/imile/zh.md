---
title: iMile 国际快递查询：单号格式、英文轨迹与客服联系
description: 查询 iMile 跨境电商包裹：IM 与纯数字单号规则、英文扫描轨迹含义、多国自营末端派送与欧盟税费说明，以及客服与 Peek 追踪指南。
slug: imile-guoji-kuaidi-chaxun
published: 2026-11-24
updated: 2026-11-24
---

iMile 是一家 2017 年创立于迪拜的跨境电商快递与物流公司，承接从中国等始发地发往海外的跨境小包运输、清关，并在多个目的国以自营团队完成最后一公里派送。你可以在 [iMile 中文查询页面](https://www.imile.com/CN-zh/track) 或 [英文查询页面](https://www.imile.com/en/track) 输入运单号查询，也可以直接粘贴到上方查询框中。单号由卖家随订单发货详情提供。

## iMile 单号格式规则与常见区分

iMile 官网没有公开统一的单号规范，但实际包裹主要采用以下几类格式（以下均为虚构示例）：

| 单号（虚构） | 归属与说明 |
| --- | --- |
| `IM06014829173640` | `IM` 加 `0601` 再加 10 位数字：iMile 专属单号。 |
| `IM48291736405812` | `IM` 加 14 位数字：同样属于 iMile。 |
| `6041526482917` | `6` 开头的 13 位数字，紧随其后的 6 位为“月日年”日期形态：iMile 自有运单号。 |
| `49482917364058` | `49` 开头的 14 位数字：见于发往意大利的 iMile 包裹。DPD、BRT、Chronopost 等也用 14 位数字，单看格式无法确定，但 Peek 会优先询问 iMile。 |
| `3482917364058` | `3` 开头的 13 位数字：常用于 iMile，但 FAN Courier 等承运商同样使用 `3` 开头 13 位号，Peek 会同时向多家核实。 |
| `2482917364058` | 其他 13 位数字：不符合 iMile 单号格式，可能属于韵达、韩国邮政等承运商。 |
| `LP61840295738142` | `LP` 加 14 位数字：属于速卖通[菜鸟（Cainiao）](carrier:cainiao)物流单号，并非 iMile 原生运单。 |

在 iMile 查询页的“输入您的订单号”处输入单号，轨迹显示在“订单状态”下；若 iMile 尚未收录该单号，结果框会保持空白而不会报错。如果在订单中找不到运单号，可参阅[如何查找快递单号](guide:find-tracking-number)（英文）并联系购物店铺确认。

## iMile 英文物流轨迹含义解析

Peek 读取到的 iMile 扫描轨迹均为英文原文，其中带有中文方括号 `【】` 的部分代表操作中心名称或机场三字码。按包裹常见流转顺序如下：

| iMile 英文轨迹 | 对你的含义 |
| --- | --- |
| `Order submitted, pending to receive.` | 卖家已提交发货预报，iMile 尚未拿到实物包裹。 |
| `Your order has been picked up.` | iMile 已从寄件方完成揽收。 |
| `【IST】The flight has taken off.` | 国际航班已起飞，括号内为始发机场代码。 |
| `【RUH】Flight Arrived - Waiting for customs clearance.` | 航班已落地目的国或中转地，等待清关。 |
| `Clearance Processing` | 海关正在查验清关。 |
| `【Milan center】Your order has been received.` | 包裹已进入 iMile 在目的国的首站分拨中心。 |
| `Your order has been shipped from 【Milan center】.` | 已离开该分拨中心发往下一站或末端站点。 |
| `Parcel is currently being processed.` | 正在负责你所在片区的末端站点分拣处理。 |
| `Our delivery associate is out for delivery.` | 派送员今天正在上门投递途中。 |
| `User not at home` | 投递未成功，原因标注为收件人不在家。 |
| `Delivery attempt finished,return to Local logistics company warehouse.` | 投递失败后包裹已退回当地仓库。 |
| `The delivery method has been confirmed with the recipient as Self-Pickup.` | 投递失败后已与收件人确认改为自提，包裹尚未移动。 |
| `The parcel has been delivered to …. Please collect your parcel promptly at … using your pickup code.` | 包裹已送达指定自提点，请凭取件码前往领取。这里的 delivered 指送达自提点而非本人签收，Peek 会显示为“待取件”。 |
| `【Contact Error】need to return.` | 派送员无法联系到收件人，启动退回流程。 |
| `Your order has been delivered successfully.` | 包裹已妥投签收。 |
| `Order delivery failed, returning to sender.` | 派送失败，正在退回寄件人。 |
| `【Milan Transit Center】Shipment returned to origin.` | 已退回发件地。 |

欧洲段包裹还常见 `Order Submited.`（iMile 系统自带拼写），含义等同于首条预报记录；`Your order has assigned delivery drvier.` 表示已分配派送员但尚未出发。`Shipment is scheduled to` 后接方括号日期表示已预约派送日期；而夹在派送行程之间、不带日期的 `Scheduled Delivery` 仅代表重新排期，包裹并未移动，Peek 会维持原有阶段。

除 `User not at home` 外，常见的派送失败原因还包括：`wrong address` 或 `Wrong Address(Insufficient address details)`（地址有误或信息不全）、`switch off`（电话关机）、`Customer Wants to Change Location`（收件人要求更改地址）、`Route not completed`（当日路线未派完）、`Traffic, Strike, Bad weather`（交通、罢工或恶劣天气）、以 `failed to schedule due to` 开头附原因（如 `noanswer`，无法预约派送），以及 `The driver failed to deliver due to shop(building) closure, we will try to deliver again.`（商铺或大楼关闭，将重新派送）。此外，`Lost order` 有时也会作为派送途中的司机异常原因出现，单凭这一条并不代表最终丢件，需留意后续扫描。

## iMile 跨境网络与末端派送规则

iMile 的国际直邮派送（International Dropship Delivery）页面写明，公司配备自营最后一公里派送团队，从中国出发的线路覆盖阿联酋、沙特阿拉伯、墨西哥、阿曼、科威特、卡塔尔、巴林、黎巴嫩、澳大利亚、约旦、南非与马来西亚。

**改约派送与签收凭证。** iMile 并未在官网公布各国统一的派送尝试次数、通知单规则或自提点保管期限，实际处理以追踪扫描为准。其中文[常见问题页面](https://www.imile.com/CN-zh/CustomerService/FAQ)说明可通过客服热线申请改约派送；英文[常见问题页面](https://www.imile.com/en/CustomerService/FAQ)还说明：如需更改收货地址，须在包裹进入最终派送阶段前联系客服或通过 iMile 在线助手提交；签收凭证通常为投递点照片以及适用时的电子签名。iMile 官网追踪页也提供签收证明入口（需验证授权用户身份），部分派送中扫描还会附上派送员电话或当地客服热线。如遇投递未成功，可参阅[错过派送怎么办](guide:missed-delivery)（英文）。

**欧盟进口关税与 2 欧元处理费。** 从欧盟以外（如中国）寄往欧盟成员国的跨境网购包裹，自 2026 年 7 月 1 日起，货值不超过 150 欧元的每类商品适用 3 欧元欧盟固定关税；自 2026 年 11 月 1 日起，不论订单货值高低，每类商品另须缴纳 2 欧元欧盟处理费。iMile 官网未说明这些税费的具体代收方式，请向购物平台确认是否已在结账时预缴，详见[包裹被海关扣留怎么办](guide:customs)（英文）。

## iMile 包裹停滞、丢件或退回怎么办

**一直停在 `Order submitted, pending to receive.`** 卖家仅创建了运单，尚未把实物交给 iMile，请向店铺确认发货时间。

**多日没有新扫描。** 从中国发出的包裹在国际航班、清关与目的国首站分拨之间常有数天静默，详见[为什么快递物流停止更新](guide:tracking-not-updating)（英文）和[中国跨境包裹追踪指南](guide:tracking-from-china)（英文）。

**追踪链接打不开。** iMile 常见问题建议先刷新页面、清除浏览器缓存或更换浏览器尝试；若仍异常，可携带订单号联系客服重新获取链接。

**显示已签收但未收到。** 先到 iMile 官网查看是否有签收照片，排查家门口、前台并询问邻居，随后联系 iMile 和购物店铺，详见[显示已签收但未收到包裹](guide:delivered-not-received)（英文）。

**丢失、破损或退回。** iMile 未向个人收件人公布直接索赔通道。运输合同由发货商家与 iMile 签订，请直接要求卖家退款或补发，详见[包裹丢失](guide:lost-parcel)（英文）和[包裹退回寄件人](guide:returned-to-sender)（英文）。

## iMile 官方客服与联系方式

根据 [iMile 中文官网](https://www.imile.com/CN-zh)、[澳大利亚法律声明](https://www.imile.com/AU-en/LegalNotice)与英文[客户支持](https://www.imile.com/en/CustomerService/CustomerSupport)页面：

- **iMile Chat 在线助手**：点击 `imile.com` 右下角聊天按钮即可打开虚拟客服，支持英文、阿拉伯语、西班牙语、意大利语、葡萄牙语、匈牙利语与德语。
- **澳大利亚客服**：热线 `+61 272 557 988`，邮箱 `customerservice-aus@imile.me`。
- **客户支持表单**：位于官网客户支持页面，提交包裹咨询时需填写公司名称与日均订单量。
- **中国与全球其他地区**：官网未公布面向个人买家的中国本地收件人电话或邮箱；涉及跨境网购订单问题，直接联系购物店铺或电商平台通常更快。

## 使用 Peek 追踪 iMile 包裹

粘贴 `IM0601…` 单号后，[Peek](/) 会立刻自动识别为 iMile（“识别出的快递公司”）。对于其他 `IM` 开头 14 位单号、`49` 开头 14 位单号，以及 `6` 或 `3` 开头的 13 位单号，由于与其他承运商共享长度，请照常粘贴——Peek 跟踪 4,500 多家快递公司，会在添加时先向格式匹配的承运商核实（对 `49` 开头及 `6` 开头日期形态单号会优先询问 iMile），一旦确认即显示“iMile 有这个包裹的记录。”；若暂未查到或需手动指定，可在添加时点击“更换”，或在已添加包裹页点击“更换快递公司”。包含 `/track?waybillNo=` 的 `imile.com` 追踪链接也可直接粘贴识别。

Peek 直接读取 iMile 的英文追踪接口，展示标准中文阶段（“已生成面单”、“已揽收”、“运输中”、“清关中”、“派送中”、“派送未成功”、“待取件”、“已签收”、“已退回寄件人”、“需要留意”）、带有中心或城市定位的地图轨迹、目的地国家，并将按目的国时钟读取的扫描时间自动换算为你的设备本地时区。若包裹前段由[菜鸟（Cainiao）](carrier:cainiao)运出中国再交由 iMile 派送，Peek 会把菜鸟前程的揽收、分拣、出口、航班与进口记录一并衔接在 iMile 历史之前。

Peek 会将最新一条 `Shipment is scheduled to` 预约的日期显示为预计送达日，直至出现派送失败、异常、到达自提点、签收、退回或晚于该日期的新扫描。Peek 不显示包裹重量、具体服务名称、收件人姓名与地址、签收照片、联系信息或付款详情，同时会从轨迹文本中剔除派送员手机号与 iMile 客服热线。

在打开包裹页面或预计派送当天开启提醒时，Peek 最高每 10 分钟检查一次，在 iMile 扫出“派送中”当天自当地时间早上 6 点起最高每 2 分钟检查一次（仅在 iMile 官网正常响应时）；其余时间每小时检查一次。若仅由第三方聚合追踪服务返回结果，Peek 每小时检查一次，最新扫描满一天后降为每 3 小时一次，包裹被扫出“派送中”当天，在打开页面或开启提醒时最高每 15 分钟一次。连续两天无新动态（或停留在“已生成面单”、“已揽收”满 12 小时）后，无论你以何种方式关注，刷新频率都转为每小时一次；若停留在“已生成面单”满七天无更新，则降为每 6 小时一次。当最新扫描满四天且包裹仍处于“已生成面单”、“已揽收”或“运输中”时，Peek 会标出该扫描日期（如“自10月12日周一起没有物流更新”），并提示“包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 iMile 查询。”及“向 iMile 寻求帮助”链接；超过三周无新动态时，则提示“已超过三周没有新动态。如果还没收到，请联系卖家。Peek 会持续向 iMile 查询。”

你可以自由选择“每次扫描”、“重要环节”或“仅派送当天”三种通知预设。Peek 仅提供追踪服务，不能代你联系 iMile 或卖家。

## 关于 iMile 快递查询的常见问题

### 怎么判断我的 iMile 包裹现在到了哪里？

查看最新一条扫描所处的阶段，以及方括号 `【】` 内的机场代码或分拨中心名称。出现 `Our delivery associate is out for delivery.` 代表当天正在派送；若随后出现司机原因行，则说明当次投递未成功。

### iMile 轨迹里的 `Order Submited.` 是什么意思？

表示卖家已经创建了寄递订单，但 iMile 还没有收到实物包裹（拼写错误为 iMile 系统自带）。待揽收后会出现 `Your order has been picked up.`。

### iMile 是正规快递公司吗？

是的。iMile 于 2017 年在迪拜创立，是一家专注于跨境电商与最后一公里派送的国际物流公司，在中东、澳大利亚、拉美及欧洲多个国家设有本地团队和官方查询网站。

### 找不到本地客服电话时该找谁处理 iMile 包裹？

对于跨境网购订单，最直接有效的方式是联系发货卖家或电商平台客服；同时也可以通过 `imile.com` 官网右下角的 iMile Chat 在线助手查询。

:::sources
- [iMile 中文官网首页](https://www.imile.com/CN-zh) – 跨境电商快递、国际直邮与本地派送业务概览
- [iMile 中文订单查询](https://www.imile.com/CN-zh/track) – 中文查询入口、“输入您的订单号”与“订单状态”
- [iMile 中文常见问题](https://www.imile.com/CN-zh/CustomerService/FAQ) – 在线查询与客服热线改约派送
- [iMile 英文官网首页](https://www.imile.com/en) – 2017 年创立于迪拜、跨境派送与 PUDO 自提点
- [iMile: Track Your Shipment](https://www.imile.com/en/track) – 英文查询页、查无记录空白结果与签收证明入口
- [iMile: FAQs](https://www.imile.com/en/CustomerService/FAQ) – 更改地址、Online Bot、签收照片与链接故障排查
- [iMile: International Dropshipping](https://www.imile.com/en/logistics-solutions/international-dropship-delivery) – 自营末端团队及中国出发覆盖国家清单
- [iMile: Legal Notice (Australia)](https://www.imile.com/AU-en/LegalNotice) – 澳大利亚客服电话与邮箱
- [iMile: Customer Support](https://www.imile.com/en/CustomerService/CustomerSupport) – 在线支持表单及必填字段
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 自 2026 年 7 月 1 日起 3 欧元关税及自 2026 年 11 月 1 日起 2 欧元欧盟处理费
- [Universal Parcel Scraper: iMile](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/imile) – 单号格式、英文轨迹映射、菜鸟前程拼接、保留与剔除字段及刷新规则
:::
