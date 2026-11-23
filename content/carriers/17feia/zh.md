---
title: 一起飞（17FEIA）国际物流查询：FEI…YQ单号与状态
description: 查询一起飞/上海宏杉国际物流（17FEIA）跨境包裹：FEI…YQ单号格式、中英文航班与海关查验状态、欧美尾程衔接与异常排查。
slug: yiqifei-17feia-wuliu-chaxun
published: 2026-11-23
updated: 2026-11-23
---

一起飞（17FEIA，全称上海宏杉国际物流，Shanghai Hongshan International Logistics）是一家中国物流公司，在深圳和上海设有仓库收货，将包裹空运出境并在进口清关后移交给目的国尾程快递派送。一起飞的运单号以 `FEI` 开头、以 `YQ` 结尾——可以直接粘贴到上方查询框，或前往官网 `www.17feia.com` 查询。单号由发货卖家或你使用的中国代购/集运转运商提供。

## 一起飞（17FEIA）单号格式：`FEI` + 网点代码 + 10 位数字 + `YQ`

Peek 识别的一起飞运单号固定为 17 位字符：`FEI` + 2 位字母（深圳发出的包裹为 `SZ`）+ 10 位数字 + `YQ`（以下示例数字均为虚构）：

| 单号（虚构） | 格式说明 |
| --- | --- |
| `FEISZ4829173605YQ` | `FEI` + 2 位字母 + 10 位数字 + `YQ`：标准一起飞单号，Peek 直接自动识别。 |
| `FEISZ482917360YQ` | 中间只有 9 位数字：不符合一起飞格式，请检查是否漏抄了一位数字。 |
| `FEISZ4829173605AB` | 结尾为 `YQ` 以外的两位字母：Peek 不会直接锁定，但会实时询问一起飞后台核实。 |
| `HHWCN4829173605YQ` | 同样以 `YQ` 结尾，但开头是 `HHW`：这是 [华翰物流（HuaHan Logistics）](carrier:huahan) 的单号。 |

在 Peek 中粘贴时，字母大小写、空格和连字符都不影响识别。找不到单号请先查订单邮件、集运后台或参考 [如何找到你的快递单号](guide:find-tracking-number)（英文）。后半程一起飞记录可能还会带出当地尾程快递单号。

## 一起飞（17FEIA）物流状态是什么意思

一起飞后台记录大多同时给出简短中文标签和英文正文（按最新优先排列），按常规流转顺序如下：

| 一起飞原文（英 / 中） | 对你的实际含义 | Peek 显示阶段 |
| --- | --- | --- |
| `Item information received.` | 一起飞收到预报订单资料，尚未收到实物包裹。 | 「已生成面单」 |
| `The package is on the way to the facility.`（`包裹在去设施路上`） | 正在送往一起飞仓库途中，仓库尚未签收。 | 「已生成面单」 |
| `Warehouse receipt.`（`仓库收货`） | 深圳或上海仓库已收货入库。 | 「已揽收」 |
| `The order has information pending customer confirmation.`（`订单待客户确认`） | 仓库正等待订舱客户（卖家或集运商）确认发运信息。 | 「已揽收」 |
| `Order Confirmed.`（`订单已确认`） | 订舱信息已确认，准备出库。 | 「已揽收」 |
| `Order dispatched from warehouse.`（`订单已出库`） | 包裹已离开中国仓库。 | 「运输中」 |
| `Export declaration in progress.`（`出口报关中`） | 中国离境前常规出口报关，并非目的国清关，也未扣关。 | 「运输中」 |
| `Flight has departed.`（`航班已起飞`） | 国际航班已飞离中国。 | 「运输中」 |
| `Flight Arrived at Transit Hub.`、`Flight Departed from Transit Hub.` | 国际航班在中转枢纽经停并起飞下一程。 | 「运输中」 |
| `Flight Arrived at Clearance Port.`（`航班抵达清关港`） | 航班已降落在进口清关口岸。 | 「运输中」 |
| `Broker picked up Shipment and delivered to warehouse.`（`清关行提货到仓`） | 清关行已提货至清关监管仓准备报关（不是送到你家）。 | 「清关中」 |
| `Customs Clearance is in progress.`（`清关中`） | 目的国进口海关正在审核清关。 | 「清关中」 |
| `Customs inspection in Progress, Release Pending.`（`海关查验，暂未放行`） | 落地清关港后被进口海关查验，暂未放行。 | 「清关中」 |
| `Customs Clearance Completed.`（`清关完成`） | 进口清关已放行。 | 「运输中」 |
| `Arrived at delivery hub，Awaiting carrie scan.`（`已交仓，等待上网`） | 已交到目的国尾程快递分拨仓，等待尾程首次扫描上网（`carrie` 为原系统拼写）。 | 「运输中」 |

**两条不改变状态的记录与进度条。** `Estimated departure date:` 加日期只是起飞前写的预计排期，Peek 会直接过滤；`Flight delayed.`（`航班延误`）仅提示航班晚点，Peek 保持原阶段不变。官网查件页上的进度条从「仓库收货」「飞机起飞」「飞机落地」到「成功签收」，凡标签为「成功签收」的记录在 Peek 中统一显示为「已签收」。

**尾程快递轨迹与时间偏差说明。** 交仓上网后，一起飞后台可能继续转发尾程快递的原文（统一打上「运输途中」标签，例如 DHL 的 `The international shipment has been processed in the export parcel center`），Peek 会结合原文自动识别外出派送与妥投。时间方面，中国仓库扫描按北京时间（UTC+8），尾程扫描按当地城市时间，但中间的航班和清关节点不标地点且时区标注偶有偏差，因此请以轨迹先后顺序为准。

## 一起飞（17FEIA）包裹在欧美由谁派送与欧盟税费

清关放行后由目的国当地快递商派送。Peek 代码中已知一起飞使用的三家尾程代码分别为德国 `DPD-DE`（DPD Germany）、德国 `DE-DHL`（德国 DHL；Peek 代码中有一条记录显示德国 DHL 接收了发往法国的包裹，再转交当地派送公司）以及美国 `FEDEX-GROUND`（[联邦快递（FedEx）](carrier:fedex)），一起飞记录中可见的目的国有德国、法国、荷兰、波兰与美国。派送次数、通知卡和自提保管均按当地尾程快递规则执行，参见 [错过派送怎么办](guide:missed-delivery)（英文）。

**欧盟进口税费与查验。** 根据 [欧盟委员会海关改革说明](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en)，自 2026 年 7 月 1 日起欧盟对 150 欧元以内境外网购包裹按每类商品收 3 欧元关税，自 2026 年 11 月 1 日起无论价值多少均另按每类商品收 2 欧元欧盟统一处理费；请向卖家或集运商确认是否已包税。遇到 `Customs inspection in Progress, Release Pending.` 说明海关正在查验，详见 [包裹海关扣留与清关指南](guide:customs)（英文）；收到不明补税短信请先阅读 [谨防快递诈骗短信](guide:delivery-scam-texts)（英文）。

## 一起飞包裹不更新、待确认或丢失怎么办

一起飞的签约客户是订舱卖家或集运转运商，延误、丢失、破损或退件请第一时间联系他们处理：

- **查无记录：** 包裹显示「等待 17FEIA 更新」，Peek 会继续查询。两天后一起飞仍查不到、也没有其他来源回应时，Peek 会显示「请检查运单号」并提示「两天过去了，快递公司仍查不到这个单号。请检查单号、更换快递公司，或删除这个包裹。」请对照发货信息核对 17 位 `FEI…YQ` 单号。
- **卡在 `订单待客户确认`：** 如果是你自己找的集运商，请登录集运账号查看是否有待确认消息或补款通知；网购订单则联系卖家。
- **航班起飞后多日无更新：** `Flight has departed.` 之后可能数日没有新记录。没有新记录满四天时，Peek 会在包裹上标出最后一条记录的日期，如「自10月12日周一起没有物流更新」。参见 [物流为什么停止更新](guide:tracking-not-updating)（英文）和 [中国直邮包裹跟踪指南](guide:tracking-from-china)（英文）。
- **显示已签收却没收到，或丢件/退件：** 先排查住宅周边和邻居，随后依次联系尾程快递与卖家/集运商。见 [显示已签收但未收到](guide:delivered-not-received)（英文）、[包裹丢失怎么办](guide:lost-parcel)（英文）和 [包裹退回寄件人](guide:returned-to-sender)（英文）。

## 一起飞客服与联系方式

由于 `www.17feia.com` 对自动访问开启了安全校验，本页不列出未经页面核验的电话或邮箱。订单售后与理赔请联系向一起飞订舱的卖家或集运转运商；尾程派送与改址请凭尾程单号直接联系当地派送快递商。

## 用 Peek 跟踪一起飞（17FEIA）包裹

粘贴 17 位 `FEI…YQ` 单号（如虚构单号 `FEISZ4829173605YQ`）或带 `orderNumber=` 参数的 `17feia.com` 链接时，[Peek](/) 会直接识别出一起飞（显示「识别出的快递公司」），查询无需账号或邮编。如果手里是尾程单号或其他格式，直接粘贴即可（Peek 支持 4,500 多家快递），也可点击「更换」或「更换快递公司」并搜索 `17FEIA`、`Feia` 或「一起飞」手动调整（注意一起飞接口仅受理 `FEI` + 2 字母 + 10 数字 + 2 字母）。

Peek 展示规范化阶段（「已生成面单」「已揽收」「运输中」「清关中」「派送中」「已签收」等）、英文描述、扫描地点及目的国，自动按北京时间与尾程当地时间换算为你设备的本地时区（中间无地点的航班/清关节点按邻近时钟推算，可能偏差数小时），并隐去进度条、起运国、邮编与重量（一起飞不提供预计送达日期或签收照）。只要一起飞记录标注了 DPD Germany、DHL 或 FedEx 及其有效单号，Peek 就会同步向该尾程商查询并在其出现最新扫描后自动接力切换、保留一起飞前期历史；若官方接口连不上则自动回退第三方查询服务。

你打开这个包裹时，或在派送当天开启了通知时，只要一起飞自己的查件系统有回应，Peek 最快每 10 分钟查询一次；包裹进入「派送中」的当天，从当地时间早上 6 点起最快每 2 分钟一次。其余时间每小时查询一次。连续两天没有新动态（包裹仍为「已生成面单」或「已揽收」时为 12 小时）后，也是每小时一次。只能通过第三方查询服务时，Peek 每小时查询一次，一整天没有新扫描后改为每 3 小时一次；派送中当天，你打开包裹或开启通知时最快每 15 分钟一次。包裹交给派送快递后，Peek 按该快递公司自己的频率查询。

没有新动态满四天时，Peek 会在包裹上标出「自10月12日周一起没有物流更新」这样的提示，包裹页面上还会说明：「包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 17FEIA 查询。」超过三周没有新动态时则改为：「已超过三周没有新动态。如果还没收到，请联系卖家。Peek 会持续向 17FEIA 查询。」

通知偏好可选「每次扫描」「重要环节」（包含清关港进口清关与查验、派送中及签收，不含中国出口报关）或「仅派送当天」。Peek 无法替你联系一起飞、卖家或集运商。

## 关于一起飞（17FEIA）物流查询的常见问题

### 一起飞（17FEIA）是什么公司？

是一家中国跨境物流企业（亦称上海宏杉国际物流），在深圳和上海设仓揽收，空运出境清关后交由欧美当地快递商派送，运单号为 `FEI` + 2 位网点代码 + 10 位数字 + `YQ`。

### 在哪里查询 `FEI…YQ` 单号？

可直接粘贴完整 17 位单号到上方 Peek 查询框，或前往官网 `www.17feia.com` 查询。

### 「Awaiting carrie scan（已交仓，等待上网）」是什么意思？

说明包裹已通过目的国进口清关，一起飞已把包裹交到当地尾程快递的分拨仓，正等待尾程快递首次扫入系统；之后当地快递官网的更新可能很快就会领先于一起飞。

### 一起飞包裹在目的国由谁派送？

由进口清关后接手的当地快递派送，一起飞记录可能会写明是哪一家：如果是德国 DPD Germany、德国 DHL 或美国 [联邦快递（FedEx）](carrier:fedex) 并附有其单号，Peek 会自动展示并接力；其余渠道请向卖家或集运商询问。

:::sources
- [European Commission：欧盟海关改革（英文）](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 自 2026 年 7 月 1 日起 150 欧元以内 3 欧元关税、自 2026 年 11 月 1 日起 2 欧元欧盟统一处理费
- [欧盟委员会：Guidance on the €3 customs duty and the Union handling fee（PDF，英文）](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 欧元处理费与 3 欧元关税一样按每类商品收取，无论价值多少
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – 一起飞公司名、FEI…YQ 单号与深圳发出时的 SZ、`orderNumber=` 链接、中英文状态与海关查验/航班延误映射、DPD-DE/DE-DHL/FEDEX-GROUND 自动接力及多时钟换算机制
:::
