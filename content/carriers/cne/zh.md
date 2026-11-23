---
title: 递一物流（CNE Express）查询：3A5V单号与尾程
description: 查询上海递一物流（CNE Express）跨境包裹：3A5V单号格式、英文与中文扣关状态含义、欧美尾程合作快递、欧盟税改与客服热线。
slug: diyi-wuliu-cne-express-chaxun
published: 2026-11-23
updated: 2026-11-23
---

递一物流（CNE Express，全称递一国际物流有限公司，CNE EXPRESS CO LTD）创立于 2003 年、总部位于上海青浦区，并在嘉兴和东莞建有日处理量超 200 万票的大型分拣中心（见 [递一官网简介](https://www.cne.com/en/about/intro)），专门为亚马逊、eBay、沃尔玛、TikTok、Temu 及 SHEIN 等跨境平台卖家将包裹空运发往欧美加澳巴等国并交由当地合作伙伴派送。你可以在 [CNE 中文查件页](https://www.cne.com/track) 或 [英文查件页](https://www.cne.com/en/track) 查件，也可以直接把单号粘贴到上方查询框。单号由发货卖家提供。

## 递一物流（CNE）单号格式：`3A5V` 开头 13 位编号

递一官网未公布固定格式说明，Peek 识别的 CNE 主流运单号为 `3A5V` 加 9 位数字，共 13 位字符（以下示例数字均为虚构）：

| 单号（虚构） | 格式说明 |
| --- | --- |
| `3A5V482917365` | `3A5V` 加 9 位数字：标准递一物流（CNE Express）单号。 |
| `3A5V48291736` | 只有 8 位数字（共 12 位）：不符合 CNE 格式，Peek 会误匹配为 12 位的 Colis Privé，请检查是否漏掉了一位数字。 |

在 Peek 中粘贴时，字母大小写、空格和连字符都不影响识别；CNE 官网查件页支持用空格或逗号分隔批量查多个单号。找不到单号请参阅 [如何找到你的快递单号](guide:find-tracking-number)、[Temu 订单追踪指南](guide:temu-orders) 或 [SHEIN 订单追踪指南](guide:shein-orders)。

**尾程单号与邮政渠道。** 在 CNE 查件页「Shipment Information」一栏中，通常还能看到卖家订单号和 `Last mile number`（目的国当地尾程快递单号及当地快递官网与电话）。此外 CNE 也代理销售中国邮政国际 e 邮宝和小包，遇到 `CN` 结尾国际邮政单号请参阅 [中国邮政](carrier:china-post)。

## 递一物流（CNE）状态是什么意思

CNE 查件页会在轨迹上方显示汇总状态（对应中文与 Peek 阶段如下）：

| CNE 汇总状态 | 对应中文与实际含义 | Peek 显示阶段 |
| --- | --- | --- |
| `Shipping` | 运输途中。 | 按最新一条扫描判定 |
| `Customs inspection` | 中文页显示「扣关」：被海关查验扣留。 | 「清关中」 |
| `Delivered` | 已妥投签收。 | 「已签收」 |
| `Invalid address`、`Lost`、`Exception`、`Destroyed` | 地址无效、遗失、异常或已销毁。 | 「需要留意」 |
| `Returned` | 已退回发件卖家。 | 「已退回寄件人」 |
| `Not delivered` | 中文页显示「未发送」：包裹尚未发出（不是派送失败）。 | 按最新一条扫描判定 |

常见明细英文扫描大致按时间顺序如下：

| CNE 明细原文 | 对你的实际含义 | Peek 显示阶段 |
| --- | --- | --- |
| `Parcel Infomation Received` | 卖家已传输包裹电子预报数据，CNE 尚未拿到实件（`Infomation` 为原拼写）。 | 「已生成面单」 |
| `Picked Up by Courier` | CNE 已在中国从卖家处揽收包裹。 | 「已揽收」 |
| `Arrived at CNE Facility`、`Inbound scan`、`Shipment Processed`、`Shipment Loaded` | 在 CNE 分拣设施入库、分拣并装车。 | 「运输中」 |
| `Departed CNE Facility`、`Departure Scan`、`Depart From Local Facility` | 已离开 CNE 操作设施。 | 「运输中」 |
| `Arrived Export Airport`、`Released From Export Customs` | 到达中国起运机场并完成中国出口报关（非目的国海关）。 | 「运输中」 |
| `Shipment Departed From Original Country` | 国际航班已离开中国。 | 「运输中」 |
| `Arrived At Transit Airport`、`Shipment Depart From Transit Airport` | 航班在中转机场经停。 | 「运输中」 |
| `Arrived at Import Airport`、`Arrived at destination` | 航班已降落目的国，后续仍需分拣与进口清关（尚未到达你当地邮局）。 | 「运输中」 |
| `The shipment is picked up by customs broker` | 清关代理已提货办理进口清关。 | 「清关中」 |
| `Released From Import Customs`、`Pickup from airport` | 进口清关放行并从机场提离。 | 「运输中」 |
| `Handled by local carrier` | 已移交给目的国本地尾程快递商。 | 「运输中」 |
| `Arrived At The Delivery Company`、`Your shipment has arrived at sorting center`、`Your shipment is in process at sorting center`、`Parcel Unloaded From Container` | 包裹在尾程快递商或目的国分拣中心卸柜处理。 | 「运输中」 |
| `Your shipment has been processed at sorting center and ready for shipping`、`Your shipment has departed from the transit origin`、`Your shipment has arrived at the transit destination` | 在目的国完成分拣并运抵当地派送站（此处的 transit 指当地干线转运）。 | 「运输中」 |
| `On Board with Courier` | 快递员已装车外出派送。 | 「派送中」 |
| `Your shipment delivery attempt failed [Recipient not at home]` | 派送尝试时收件人不在家，将安排重派。 | 「派送未成功」（若汇总标为 `Exception` 则显示「需要留意」） |
| `Sorting failed []` | 分拣出现异常。 | 「需要留意」 |
| `It was returned to customer's address by CNE` | CNE 已将包裹退回寄件客户（卖家）。 | 「已退回寄件人」 |

## 递一物流包裹在各国由谁派送与欧盟税改说明

根据 [CNE 合作伙伴页面](https://www.cne.com/en/about/partner)，其海外尾程合作伙伴涵盖 USPS、Royal Mail、Austrian Post、Correos、Poste Italiane、GLS、La Poste、DHL、Evri、DPD 和 POST Luxembourg。在 Peek 中，只要 CNE 给出了德国 DHL、英国 Royal Mail 或美国 USPS 的供应商代码及对应尾程单号，Peek 就会在包裹卡片上直接标注该尾程商与单号并自动接力追踪。派送次数、通知卡和自提保管均执行当地尾程快递规定，参见 [错过派送怎么办](guide:missed-delivery)。

**欧盟税改预收政策。** 根据 [欧盟委员会海关改革说明](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en)，发往欧盟的境外网购包裹自 2026 年 7 月 1 日起对 150 欧元以内包裹按每类商品征收 3 欧元关税，自 2026 年 11 月 1 日起无论价值高低均另收 2 欧元欧盟统一处理费（未走 IOSS 的包裹另涉进口增值税）。[CNE 2026 年 11 月欧盟税改通知](https://www.cne.com/news/article/rDe4ANa6KYOW) 明确告知卖家：自 2026 年 10 月 26 日起，CNE 欧盟路向已在 3 欧元关税基础上提前按每个税号向卖家代收 2 欧元处理费并按卖家申报数据清关（但通知未涵盖增值税及尾程快递可能另收的费用）。详见 [包裹海关扣留与清关指南](guide:customs)。

## 递一物流包裹不更新、扣关或没收到怎么办

递一的签约客户是发货卖家，遇延误、丢件、破损或退件请优先找卖家或平台处理：

- **查无记录或显示 `Not delivered`（未发送）。** `Not delivered` 仅表示包裹尚未发出，并非派送失败；若超过两天 CNE 与第三方服务均查不到单号，Peek 会提示核对 13 位 `3A5V` 单号。
- **扣关或清关停滞：** 出现 `Customs inspection`（扣关）时 CNE 依据卖家申报资料清关，滞留多日请第一时间联系卖家核实申报。另见 [物流为什么停止更新](guide:tracking-not-updating) 和 [中国直邮包裹跟踪指南](guide:tracking-from-china)。
- **遗失、销毁、错址或退回：** 看到 `Lost`、`Destroyed`、`Invalid address` 或 `Returned` 请立刻联系卖家退款或重发。参见 [包裹丢失怎么办](guide:lost-parcel) 和 [包裹退回寄件人](guide:returned-to-sender)。
- **显示已签收却没收到：** 在 CNE 追踪页看到 `View POD` 按钮时，输入收件人姓名或邮编可查看签收凭证或照片；核查周围与邻居后再联系卖家。见 [显示已签收但未收到](guide:delivered-not-received)。

## 递一物流（CNE）客服电话与联系方式

根据 [CNE 联系我们页面](https://www.cne.com/en/about/contact)：

- **全国服务热线**：`400-021-5600`（中国国内热线）。
- **上海总部电话与地址**：`021-5109 6677`，上海市青浦区华隆路 1777 弄 G 栋 15 楼。
- **客服时间**：周一至周日 `08:30–18:00`（未标注时区，分拣中心全天候运行）。
- **在线咨询/投诉表单**：官网联系页支持选择「Complaint」填写电话、邮箱、姓名、运单号与留言（无公开客服邮箱）。

## 用 Peek 跟踪递一物流（CNE Express）包裹

粘贴 13 位 `3A5V` 单号（如虚构单号 `3A5V482917365`）或带 `?no=` 参数的 `cne.com` 追踪链接时，[Peek](/) 会立刻自动识别递一物流并提示「CNE Express 有这个包裹的记录。」；如需手动调整可点「更换」或「更换快递公司」并搜索 `CNE` 或「递一物流」，详情页亦附有「向 CNE Express 寻求帮助」直达 CNE 联系页。

Peek 直接读取 CNE 轨迹，展示规范化阶段（「已生成面单」「已揽收」「运输中」「清关中」「派送中」「派送未成功」「已签收」「已退回寄件人」「需要留意」等）、扫描地点、目的国及尾程快递信息。由于 CNE 页面把 `Arrived at destination` 和交接尾程记录成北京时间而把其他海外扫描记成当地时间，Peek 会自动分节点修正时钟再统一换算为你设备的本地时区，避免时间倒序错乱；同时隐藏商家订单号、尾程网址电话及 POD 照片，CNE 亦不提供预计送达日期或重量。只要 CNE 给出德国 DHL、英国 Royal Mail、美国 USPS 或标准国际邮政单号（或可唯一匹配的当地单号），Peek 便自动同步查询并在尾程出现更新扫描后接力切换、保留 CNE 前期历史。

打开页面或派送当天开启通知时，Peek 最快每 10 分钟检查一次，进入派送当天早上 6 点起最快每 2 分钟检查一次（第三方服务每 15 分钟一次；切至尾程快递后遵循其频率）；平时及连续两天无新扫描（或前段 12 小时无变化）降为每小时一次。静默满四天时 Peek 会提示「包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 CNE Express 查询。」，超过三周无动静则提示「已超过三周没有新动态。如果还没收到，请联系卖家。Peek 会持续向 CNE Express 查询。」。

通知偏好可选「每次扫描」「重要环节」或「仅派送当天」。Peek 无法替你联系递一物流或卖家。

## 关于递一物流（CNE Express）查询的常见问题

### 递一物流（CNE Express）是正规公司吗？

是的。递一国际物流有限公司成立于 2003 年、总部在上海，在嘉兴和东莞设有大型分拣中心，日处理能力超 200 万票，是亚马逊、eBay、沃尔玛、TikTok、Temu 与 SHEIN 等平台卖家的常用跨境物流商。

### 递一物流包裹在收件国由哪家快递派送？

由 CNE 合作的当地邮政或快递商派送，常见 Royal Mail、La Poste、DHL、USPS、GLS、DPD 和 Evri。你可以在 CNE 追踪页的 `Last mile number` 查看对应尾程单号。

### `Arrived at destination` 是送到当地邮局了吗？

还没有。它只表示国际航班已落地目的国，之后还需分拣和进口清关才会交给当地快递。由于 CNE 把这一行记为北京时间（冬季比伦敦快 8 小时、比巴黎/柏林快 7 小时）而把海外节点记为当地时间，在官网上看起来可能会出现时间乱序。

### 递一物流寄到欧美一般要几天？

CNE 给卖家的参考时效为：优先专线（CNE Priority Express）发美、英、法、德约 3–8 个工作日；标准专线（CNE Standard Express）约 5–20 个工作日。具体请以网店给出的预计送达时间为准。

### 怎么联系递一物流客服？

可拨打中国全国热线 `400-021-5600`（周一至周日 `08:30–18:00`）或在 [CNE 联系页面](https://www.cne.com/en/about/contact) 填表选择 `Complaint`；订单售后建议先联系发货卖家。

:::sources
- [CNE：英文追踪页](https://www.cne.com/en/track) – 英文状态标签、Shipment Information 与 View POD
- [CNE：中文运单追踪页](https://www.cne.com/track) – 「未发送」与「扣关」对应中文含义
- [CNE：英文官网首页](https://www.cne.com/en) – 2003 年上海创立、跨境线路与合作电商平台
- [递一物流：中文官网首页](https://www.cne.com/) – 递一物流及递一国际物流有限公司名称
- [CNE：关于我们](https://www.cne.com/en/about/intro) – 嘉兴与东莞分拣中心及日处理超 200 万票规模
- [CNE：全球物流合作伙伴](https://www.cne.com/en/about/partner) – USPS、Royal Mail、La Poste、DHL、Evri、DPD 等尾程合作伙伴
- [CNE：联系我们](https://www.cne.com/en/about/contact) – 400-021-5600 热线、上海总部、服务时间与 Complaint 表单
- [CNE：关于欧盟国家 2026 年 11 月税改通知](https://www.cne.com/news/article/rDe4ANa6KYOW) – 2026 年 10 月 26 日起欧盟路向预收 2 欧元处理费与 3 欧元关税规则
- [European Commission：欧盟海关改革（英文）](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 自 2026 年 7 月 1 日起 3 欧元关税、自 2026 年 11 月 1 日起 2 欧元欧盟统一处理费
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – 3A5V 单号与 `?no=` 链接规则、中英文状态与尾程扫描映射、北京时间与当地时间混用修正及 DHL/Royal Mail/USPS 自动接力机制
:::
