---
title: 速达非（Speedaf Express）快递查询：单号、状态与客服
description: 查询速达非（Speedaf）从中国发往非洲、中东和巴基斯坦的包裹：单号格式、物流与 pending Tax 状态含义及各国客服电话。
slug: speedaf-tracking
published: 2026-11-24
updated: 2026-11-24
---

速达非（Speedaf Express）成立于 2019 年，是一家从中国仓库空运包裹至非洲、中东、巴基斯坦及部分欧洲国家并自建尾程派送网络的快递公司。你可以在 [speedaf.com](https://www.speedaf.com/cn-en/send-parcel) 的“Track”页面、速达非客户端 App 或上方查询框输入运单号查件。运单号可在商家的发货详情中找到。

## 速达非运单号格式

速达非单号以目的地国家代码开头，例如尼日利亚为 `NG`、肯尼亚为 `KE`。常见两种格式如下（示例数字）：

| 单号 | 说明 |
| --- | --- |
| `NG482910573R` | 国家代码、9 位数字加 1 位字母（通常为 `R`，有时为 `E`）。 |
| `KE482910573649` | 国家代码加 12 位数字。 |
| `PK482910573649T` | 国家代码、12 位数字末尾加 `T`。 |
| `LP48291057364918` | 非速达非单号：属于速卖通的[菜鸟（Cainiao）](carrier:cainiao)参考号。 |

速达非官网一次最多支持查询 20 个单号，每行一个，且必须输入大写字母——小写会被系统视作无效单号。找不到单号？请看[如何查找运单号](guide:find-tracking-number)（英文）或[速卖通订单跟踪](guide:aliexpress-orders)（英文）。

## 速达非物流状态含义

速达非通常使用英文记录扫描，否则使用中文，偶尔使用目的地国家语言；站点名称会放在方括号【】内。从中国发出的包裹常见状态顺序如下：

| 速达非显示 | Peek 显示 | 中文含义 |
| --- | --- | --- |
| `processing, order has been inbounded` | 已揽收 | 前方注明的速达非中国仓库已收件入库。 |
| `processing assembling operation`、`processing, order has been outbounded` | 运输中 | 集包并从仓库发出。 |
| `clearance in progress` | 清关中 | 中国出口报关中。 |
| `Delivery to the airport`、`Cargo flight departured`、`Cargo flight arrived` | 运输中 | 发往机场、航班起飞并落地目的地国家。 |
| `Clearance handover`、`Customs clearance in progress` | 清关中 | 从机场转交目的地国海关清关。 |
| `pending Tax` | 清关中 | 需先缴纳关税海关才放行（见下文）。 |
| `Customs clearance tax paid` | 清关中 | 税费已缴，清关继续进行。 |
| `Customs Clearance Completed` | 运输中 | 海关已放行。 |
| `Arrived at DC Warehouse`、`Arrival at Speedaf DC` | 运输中 | 抵达目的地国速达非仓库或分拨中心。 |
| `Received in` | 运输中 | 目的地国首个速达非站点已接收，并非重新揽收。 |
| `Loaded at`、`Departed for`、`Arrive at Distribution`、`Arrived at Site` | 运输中 | 在速达非分拨中心与派送网点之间转运。 |
| `scanned for delivery` | 派送中 | 快递员已扫描带出派送。 |
| `Issue Parcel Reason【Rescheduled delivery】` | 派送未成功 | 派送改期至后面括号里注明的日期。 |
| `Issue Parcel Reason【Receiver Rejected Parcel】` | 需要留意 | 收件人拒收包裹。 |
| `Issue Parcel Reason【Delayed payment of tax by customer】` | 清关中 | 因未缴纳关税，快递员将包裹带回。 |
| `Please come to collect with the Pick-Up Code` | 待取件 | 已到达前方注明的速达非网点，请凭取件码领取。 |
| `Parcel delivered 【Collected】`、`【Parcel delivered】` | 已签收 | 已送达或已自提，条目可能注明自提点名称。 |
| `Transported to the warehouse，【Return Overdue】` | 需要留意 | 包裹正退回速达非仓库。 |
| `Warehouse received` | 已退回寄件人 | 退件已回到速达非仓库。 |

仅写着 `In Transit` 有时暗藏暂扣：速达非底层状态码可能对应库存盘点扣留。`Products not for personal use` 和 `Clearance Exception` 表示海关提出疑问或出现异常；`Long Time Tax No Paid,Email To Sender` 表示关税久未支付，速达非已发邮件通知寄件人。中文轨迹中可留意 `进行处理，订单已操作入库`（“已揽收”）、`清关中`（“清关中”）、`已做派件扫描`（“派送中”）和 `包裹【已签收】`（“已签收”）。

## 速达非派送：送货上门、网点自提与派送尝试

速达非自建尾程快递员团队，提供送货上门和货到付款（COD），其“Look for Service Site”页面标出了各地网点位置。

各国派送规则出自面向寄件人的当地条款，彼此存在差异（尼日利亚和阿联酋条款涵盖当地境内派送）：

- **阿联酋：** 每票包裹包含 3 次免费派送尝试，无法送达的包裹退回寄件人。
- **肯尼亚、乌干达与巴基斯坦：** 若第三次尝试仍无法送达，或收件人拒收/拒付费用，包裹退回寄件人；三国条款均注明速达非投递至收件地址，但未必交付给面单具名本人。
- **尼日利亚与阿联酋：** 拒收包裹或拒付货到付款金额、地址错误或超出派送范围，或你要求的派送日期超出速达非保管期限，均视作无法投递；两份条款均未写明具体保管天数。

速达非未公布网点自提的固定保留天数。错过派送？请看[错过派送怎么办](guide:missed-delivery)（英文）。

## 速达非关税状态：pending Tax

如果需要缴纳关税，物流会停在 `pending Tax`。寄往南非的包裹此时会在速达非查询页运单号旁边显示“I want to pay taxes”链接，缴清后变为“Taxes paid”。

若收到要求付款的短信或邮件，请先到 speedaf.com 核对真实物流状态，参考[快递诈骗短信指南](guide:delivery-scam-texts)（英文）和[包裹清关指南](guide:customs)（英文）。

## 速达非包裹延误、丢失或显示签收却未收到

**停滞不动。** 如果连续多日没有新扫描，请看[如何跟踪中国发出的包裹](guide:tracking-from-china)（英文）和[为什么物流停止更新](guide:tracking-not-updating)（英文）。

**谁能理赔。** 速达非肯尼亚、乌干达、巴基斯坦条款写明理赔仅在寄件人与 SPEEDAF 之间处理；尼日利亚和阿联酋条款亦规定只与寄件人结算赔偿。网购订单的寄件人是商家，因此退款或补发请找商家或平台。

**尽早通知商家。** 速达非条款规定的申报窗口很短：

- **破损：** 肯尼亚和乌干达须在签收后 7 个自然日内书面申报，尼日利亚和阿联酋为 72 小时内，沙特为 48 小时内。
- **显示签收但未收到：** 尼日利亚和阿联酋须在 5 个自然日内对签收证明提出异议，巴基斯坦为 5 个工作日内。
- **丢失：** 尼日利亚和阿联酋须在揽收后 14 个自然日内提交查件投诉；超过 24 小时无扫描时，速达非会查件 7 个工作日，仍找不到即判定丢失。

速达非查询页会显示签收人姓名，请先核对。更多见[显示已签收但未收到](guide:delivered-not-received)（英文）和[包裹丢失处理](guide:lost-parcel)（英文）。

## 速达非各国客服电话与联系方式

速达非各国官网页脚列出了各自的联系方式（爱尔兰与赞比亚可在国家切换器中选择国家后再选 English 查看）：

| 国家/地区 | 电话 | 邮箱 | 服务时间 |
| --- | --- | --- | --- |
| 尼日利亚 | +234 800 070 7070、+234 708 063 6400、+234 9073064940（同 WhatsApp） | ng.info@speedaf.com、nigeria.info@speedaf.com | 周一至周五 8:00–17:00，周六 8:00–14:00 |
| 加纳 | +233 30 824 9100 | gh.info@speedaf.com | 周一至周六 8:30–17:30 |
| 肯尼亚 | +254 741 000 666、+254 741 000 888；WhatsApp +254 741000666 | kenya.info@speedaf.com | 周一至周五 8:00–18:00，周六 8:00–14:00 |
| 乌干达 | +256 709 198 795 | ug.info@speedaf.com | 周一至周五 9:00–17:00，周六 8:00–14:00 |
| 赞比亚 | +260 971269390 | zm.info@speedaf.com | 周一至周五 7:45–17:15，周六 8:00–13:00 |
| 南非 | +27 108247184、+27 726393849；WhatsApp +27 664562613 | cs.southafrica@speedaf.com | 未公布 |
| 巴基斯坦 | +92 311 118 7654；WhatsApp +92 3450010817 | pk.info@speedaf.com | 周一至周日 9:00–18:00 |
| 阿联酋 | +971 800 178 | ae.info@speedaf.com | 周一至周六 9:00–18:00 |
| 沙特阿拉伯 | 未公布 | sa.info@speedaf.com | 未公布 |
| 爱尔兰 | +35312633068；WhatsApp +353 834281088 | info_customerserviceie@speedaf.com | 周一至周五 8:30–17:30 |
| 埃及 | +20 15818、+20 1050383370 | eg.info@speedaf.com | 周六至周四 9:00–18:00 |
| 摩洛哥 | +212 522 100 888 | ma.info@speedaf.com | 周一至周五 9:00–18:00 |
| 中国 | +86 400 888 0975 | info@speedaf.com | 周一至周五 9:30–18:30 |

速达非帮助中心还补充了其他国家热线：哈萨克斯坦 +7 778 371 9358、毛里求斯 4687316、马尔代夫 +960 7678788、瑞士与塞尔维亚 +41 798559737、黑山 +382 60021040、阿尔巴尼亚 +355 45620111。各站点亦提供“Customer Support”在线表单。

## 使用 Peek 跟踪速达非包裹

当单号以 NG、GH、KE、UG、RW、ZM、ZA、EG、MA、SA、AE、PK、KZ、MU、RE、MV 或 TR 开头、后接 9 位数字加 R 或 E 时，[Peek](/) 会按格式自动识别为 Speedaf。若这些代码或 IE 后接的是 12 位数字，光靠格式不足以定夺，Peek 会先向 Speedaf 查询：界面显示“正在查询物流服务…”，确认后显示“Speedaf 有这个包裹的记录”。Peek 未收录瑞士、阿尔巴尼亚、黑山或塞尔维亚的专属格式：遇到这些国家或其他不匹配的号码，点击“更换”选择“Speedaf”即可。粘贴带 `tradeNo=` 参数的 speedaf.com 链接会直接按 Speedaf 查询。Peek 会自动把单号转为大写提交，因此输入小写也没问题。

Peek 读取速达非官网数据，显示状态、轨迹、目的地国家和签收时间。每条扫描按所在网点时间读取（中国仓库按中国时间，抵达后按目的地国时间），再换算为你设备的时区；在 peektracker.com 以及 iPhone 和 Android App 中，轨迹末尾会注明“时间按你所在的时区显示：”及对应时区名称。速达非仅在文本中提及站点，因此没有地图，也不提供预计送达日期。如果速达非识别该单但尚无扫描（如部分较旧包裹），Peek 会显示“等待 Speedaf 更新”（对尚未归入 Speedaf 的 12 位单号则显示“等待 Speedaf 的首次扫描”）并继续查询。若归入 Speedaf 的单号两天后仍无任何记录且无其他来源响应，Peek 会提示“两天过去了，快递公司仍查不到这个单号。请检查单号、更换快递公司，或删除这个包裹。”并改为每天检查一次，满一周后改为每周检查一次。

速达非扫描保留英文或中文原文，但会去除快递员/操作员姓名及快递员电话。签收人姓名仅对你本人可见，分享链接会隐去并仅显示 `Delivered`。Peek 也会省略签收照片、缴税链接和箱数，且只跟踪你添加的这一单，不关联同运单下的其他箱件。由于速达非自行完成尾程派送，不会转交第二家快递。

在你打开页面或开启派送当天提醒期间，只要速达非官网正常响应，Peek 最高每 10 分钟检查一次，在速达非扫描进入派送当天自当地时间早上 6 点起最高每 2 分钟检查一次（通过物流跟踪服务查询时，在派送当天打开页面或开启提醒时最高每 15 分钟检查一次）；其余时候每小时检查一次。连续两天无新动态后（若仍停留在“已生成面单”或“已揽收”，则为 12 小时后），改为每小时检查一次。支持开启“每次扫描”、“重要环节”或“仅派送当天”通知。Peek 无法代你缴税、预约重派或联系速达非与卖家。

## 速达非快递查询常见问题

### 速达非（Speedaf Express）正规吗？

是的。速达非成立于 2019 年，创始股东包括中通（ZTO），拥有独立查询网站 speedaf.com，并在新兴市场自建尾程派送团队。

### 速达非派送哪些国家？

速达非国家列表包含中国以及埃及、南非、摩洛哥、阿联酋、加纳、尼日利亚、肯尼亚、乌干达、沙特阿拉伯、巴基斯坦、哈萨克斯坦、毛里求斯、马尔代夫、瑞士、黑山、阿尔巴尼亚、爱尔兰、塞尔维亚和赞比亚。此外 Peek 还识别以 RE（留尼汪）、RW（卢旺达）和 TR（土耳其）开头的单号。

### 速达非通常多久能送到？

速达非 eParcel 页面对从中国发往尼日利亚、加纳、肯尼亚、乌干达、摩洛哥、埃及、沙特、阿联酋和巴基斯坦的包裹给出的全程参考时效为 5 至 10 个工作日；帮助中心注明视目的地而定，肯尼亚、乌干达和巴基斯坦条款明确不承诺固定送达时间。

### 速达非显示 pending Tax 是什么意思？

说明包裹在目的地国海关放行前需要缴纳关税或税费。缴清后，状态会依次更新为 `Customs clearance tax paid` 和 `Customs Clearance Completed`。

:::sources
- [Speedaf: Enterprise Introduction](https://www.speedaf.com/ng-en/business/about-us) – 成立于 2019 年及中通股东背景
- [Speedaf: Select your location and language](https://www.speedaf.com/cn-en/user-opt/locale-select) – 服务国家及爱尔兰、赞比亚、中国联系方式入口
- [Speedaf: Speedaf eParcel](https://www.speedaf.com/ke-en/product/intl-straight-small) – 中国跨境电商小包与 5–10 个工作日参考时效
- [Speedaf: Warehouse & Fulfillment](https://www.speedaf.com/ke-en/product/warehouse-promise) – 自营尾程派送与货到付款
- [Speedaf: Last-mile delivery](https://www.speedaf.com/ke-en/product/warehouse-delivery) – 货到付款与送货上门
- [Speedaf: Help Center](https://www.speedaf.com/ng-en/help) – 时效说明、官网与 App 查询及各国热线
- [Speedaf: Track](https://www.speedaf.com/ng-en/send-parcel) – 一次最多查 20 单与南非缴税链接
- [Speedaf: Track (China English)](https://www.speedaf.com/cn-en/send-parcel) – 速达非官网查询入口
- [Speedaf: Look for Service Site](https://www.speedaf.com/ke-en/location-map) – 服务网点地图
- [Speedaf: Customer Support](https://www.speedaf.com/ng-en/service/support) – 在线客服表单
- [Speedaf Nigeria: Home](https://www.speedaf.com/ng-en) – 尼日利亚电话、邮箱、时间与 WhatsApp
- [Speedaf Ghana: Home](https://www.speedaf.com/gh-en) – 加纳电话、邮箱与时间
- [Speedaf Kenya: Home](https://www.speedaf.com/ke-en) – 肯尼亚电话、邮箱、时间与 WhatsApp
- [Speedaf Uganda: Home](https://www.speedaf.com/ug-en) – 乌干达电话、邮箱与时间
- [Speedaf South Africa: Home](https://www.speedaf.com/za-en) – 南非电话、邮箱与 WhatsApp
- [Speedaf Pakistan: Home](https://www.speedaf.com/pk-en) – 巴基斯坦电话、邮箱、时间与 WhatsApp
- [Speedaf UAE: Home](https://www.speedaf.com/ae-en) – 阿联酋电话、邮箱与时间
- [Speedaf Saudi Arabia: Home](https://www.speedaf.com/sa-en) – 沙特客服邮箱
- [Speedaf Egypt: Home](https://www.speedaf.com/eg-en) – 埃及电话、邮箱与时间
- [Speedaf Morocco: Home](https://www.speedaf.com/ma-en) – 摩洛哥电话、邮箱与时间
- [Speedaf Kenya: Terms and Conditions](https://www.speedaf.com/ke-en/service-rule) – 肯尼亚派送尝试、拒收、收件人、索赔与时效条款
- [Speedaf Uganda: Terms and Conditions](https://www.speedaf.com/ug-en/service-rule) – 乌干达相同条款
- [Speedaf Pakistan: Terms and Conditions](https://www.speedaf.com/pk-en/service-rule) – 巴基斯坦派送尝试、收件人、索赔与签收异议条款
- [Speedaf UAE: Terms & Conditions](https://www.speedaf.com/ae-en/service-rule) – 阿联酋境内派送尝试、不可投递情形与寄件人索赔期
- [Speedaf Nigeria: Service Agreement](https://www.speedaf.com/ng-en/service-rule) – 尼日利亚境内派送不可投递情形与寄件人索赔期
- [Speedaf Saudi Arabia: Express Shipping Terms](https://www.speedaf.com/sa-en/service-rule) – 沙特 48 小时破损申报期限
:::
