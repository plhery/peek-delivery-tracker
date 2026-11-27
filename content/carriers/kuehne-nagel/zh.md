---
title: Kuehne+Nagel 物流追踪：单号格式、节点状态与清关说明
description: 使用参考号或 myKN 链接追踪 Kuehne+Nagel（德迅）海运、空运及陆运货件：单号格式、路线节点含义、海关清关与联系方式。
slug: kuehne-nagel-tracking
published: 2026-11-26
updated: 2026-11-26
---

Kuehne+Nagel（德迅）是全球领先的物流服务商之一，在近 100 个国家/地区的近 1,300 个网点拥有近 92,000 名员工。其业务涵盖海运、空运和陆运货运，用户可通过 [myKN 公开追踪门户](https://mykn.kuehne-nagel.com/public-tracking/)查询所有运输方式的货件。

myKN 公开追踪涵盖最近 90 天内起运的货件。由于 Kuehne+Nagel 的货运流程横跨公路站点、机场、集装箱货运站、铁路场站和海港，同一票货件可能同时显示物理运输节点以及独立的订舱、清关或单证通知。

## Kuehne+Nagel 追踪单号格式

Peek 接受长度为 6 至 35 位、包含字母和数字（至少含 1 位数字）的 Kuehne+Nagel 参考编号，并会移除空格与短横线。myKN 在同一查询框中支持检索 Kuehne+Nagel 自有单号、运单号、订舱号以及客户参考号。Peek 认得以下三种格式：

- **`RWM` 后接 9 位数字**（共 12 位，如 `RWM482917360`）——Kuehne+Nagel 陆运货件单号，Peek 会直接识别为 Kuehne+Nagel 专属格式。
- **`RAPI` 后接 8 位数字**（共 12 位，如 `RAPI48291736`）——Kuehne+Nagel 追踪单号，Peek 同样直接识别为 Kuehne+Nagel 专属格式。
- **2 位数字 + `A` + 9 位数字**（共 12 位，如 `26A482917360`）——陆运货件单号。该格式同时符合 [Australia Post](carrier:australia-post) 与 [Colis Privé](carrier:colis-prive)，因此 Peek 会依次向 Australia Post、Colis Privé 与 Kuehne+Nagel 查询（在瑞士和德国会将 Kuehne+Nagel 排在第二顺位）。

对于空运和海运货件，托运人还可能提供不在上述三种格式之内的 Kuehne+Nagel 运单号、分提单号（house bill of lading）或客户参考号。如果粘贴 `mykn.kuehne-nagel.com/public-tracking/shipments?query=...` 链接，Peek 会提取参考编号并直接交给 Kuehne+Nagel 查询。如需对比更多货运与快递网络的单号规则，请参阅[单号格式指南](guide:tracking-number-formats)（英文）。

## Kuehne+Nagel 节点状态如何运作

Kuehne+Nagel 公开追踪器将一票货件分为两类动态：路线物理站点（始发地、起运站、中转枢纽、口岸站点、到达站与目的地）和运输信息通知（订舱、报关申报及签收单证）。

| Kuehne+Nagel 表述 | 含义 |
| --- | --- |
| Booking submission / Booking confirmation | 订单已在 Kuehne+Nagel 系统中完成订舱登记，货物尚未起运；后续对订舱的补录修改不会将已在途的货件状态回退。 |
| Ready for pickup / Arrival (at origin) | 货物已在发货方备妥，或提货卡车已到达发货地，尚未确认装车。 |
| Pickup | Kuehne+Nagel 已从托运人处完成提货。 |
| Arrival / Departure / Load on vehicle / Load on vessel | 干线运输途中的场站、越库分拨中心、铁路场站、机场或港口物理扫描。 |
| Customs entry start / Customs duties payment / Entry documents required | 正在办理出口或进口清关，或海关/监管机构（如 FDA 或 PGA）要求补充单证、缴纳税费或实施查验。 |
| Customs entry release / Export customs entry release / Import customs entry release | 海关或相关口岸机构已放行，货物可继续发运。 |
| In delivery | 陆运车辆已出发执行末端派送，或已抵达收货地点。 |
| Delivery / Freight collection / Proof of delivery available | 货物已交付收货人或在场站完成自提；`Delivery with discrepancies` 仍标记为已送达，同时注明存在短少或货损。 |
| Delivery attempt failed / Delivery not possible | 派送未能完成，例如收货人拒收。 |
| Shipment on hold / Delivery instructions pending / Missing/incomplete document | 货件暂扣在场站或海关环节，需补齐缺失文件、费用或派送指令后方可继续。 |

以下几类节点标签值得特别留意：

- **Available at CY / Available at CFS / Ready for collection / Available at rail terminal** 表示货物已抵达海运集装箱堆场（CY）、拼箱货运站（CFS）、机场或铁路场站，等待交接或放行转运。由于 Kuehne+Nagel 或合作伙伴仍可能负责后续陆运派送，这类扫描仍保留在“运输中”，不会标记为最终送达。
- **Freight handover** 表示 Kuehne+Nagel 已将货物交接给报关行或下程承运商，而非交付给最终收货人。
- **单证与申报类通知**（如 `HAWB available`、`MAWB available`、`Transport document available`、`VGM closure`、`Delivery appointment submission`、`Customs billing invoice available`）仅记录行政单证节点，不改变货件的物理运输阶段。
- **目的地空箱还箱（Empty container gate-in）及车辆离场（vehicle departure at destination）**发生在签收完成之后，货件仍停留在“已签收”阶段。

## 派送、清关与延误

`Delivery appointment submission` 仅记录约定的未来派送日期，并非实物扫描：只有当系统出现 `In delivery` 或卡车抵达收货地址（`Arrival`）时，货件才会切换为“派送中”。

若追踪显示 `Awaiting consignee's feedback`、`Awaiting consignee's payment` 或 `Entry documents required`，说明清关环节正等待收货人的回复、付款或单证。`Unable to file customs entry - missing details or documentation` 表示在资料补齐之前无法提交报关申报。关于进口清关与税费机制，请参阅[包裹滞留海关指南](guide:customs)（英文）。

myKN 页面上展示的计划节点日期属于预计时间，而非已实际完成的扫描。如需了解货运或包裹物流停滞的通用排查步骤，请参阅[为何物流停止更新](guide:tracking-not-updating)（英文）。

## 使用 Peek 追踪 Kuehne+Nagel

将 `RWM`、`RAPI` 单号或 `mykn.kuehne-nagel.com/public-tracking/shipments?query=...` 链接粘贴至 [Peek](/) 时，Peek 会立即识别 Kuehne+Nagel。对于 `26A...` 单号，Peek 会依次询问前述承运商，在 Kuehne+Nagel 确认后显示“Kuehne+Nagel 有这个包裹的记录。”若运单号或客户参考号不符合以上三种格式，请在添加时点击**更换**，或在追踪页面点击**更换快递公司**手动选择 Kuehne+Nagel。

Peek 通过开源的 [universal-parcel-scraper Kuehne+Nagel 适配器](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/kuehne-nagel)直接读取 Kuehne+Nagel 公开货件检索、路线节点及运输通知。Peek 仅记录已实际达成的节点（忽略计划预测日期），并在接口返回时展示总重量（千克）及目的地国家。当系统通过客户参考号检索到货件时，Peek 会在“Kuehne+Nagel 记录的单号为”下方一并列出 Kuehne+Nagel 自有单号；若同一参考号同时匹配多票货件，Peek 无法判断哪一票属于你。当扫描系统提供时区偏移量时，Peek 会直接保留该准确时刻；否则按扫描地点时区解析（如汉堡按柏林时间、巴塞尔按苏黎世时间），无地点的扫描沿用相邻记录时区，其余默认苏黎世时间，并全部以你的设备时钟呈现。

Peek 会将 Kuehne+Nagel 节点映射为清晰阶段：**已生成面单**（订舱与待提货通知）、**已揽收**（提货）、**运输中**（枢纽、港口、铁路及交接移动）、**清关中**（进出口清关与口岸查验暂扣）、**派送中**（`In delivery` 与卡车抵达目的地）、**派送未成功**（派送失败）、**需要留意**（扣留、提货失败、取消及单证缺失）以及**已签收**（签收、场站提货或签收证明完成）。单证登记通知会保留在时间线中且不会导致当前阶段倒退，签收后的清关归档记录也不会让已签收的货件倒退。

当你在 Peek 中打开包裹页面，或在派送当日开启通知时，Peek 最多每 10 分钟检查一次；在 Kuehne+Nagel 显示 `In delivery` 或卡车抵达收货地址（`Arrival`）当天，只要 Kuehne+Nagel 自己的追踪系统有响应，Peek 会从最新扫描所在地早上 6 点起每 2 分钟检查一次。其余情况每小时检查一次。若停留在“已生成面单”或“已揽收”超过 12 小时，或连续两天没有新动态，无论你以何种方式关注，均改为每小时检查一次；若停留在“已生成面单”超过七天，则每 6 小时检查一次。当 Kuehne+Nagel 无响应、Peek 仅通过追踪服务跟进时，每小时检查一次，静默一天后每 3 小时一次，派送当天最多每 15 分钟一次。

当最新扫描距今已满四天，且包裹仍处于“已生成面单”、“已揽收”或“运输中”时，Peek 会用该次扫描的日期加以标记，例如“自10月12日周一起没有物流更新”。包裹页面随后会显示“包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 Kuehne+Nagel 查询。”如果三周没有新扫描，页面会显示“已超过三周没有新动态。如果还没收到，请联系卖家。Peek 会持续向 Kuehne+Nagel 查询。”

你可以在包裹设置中选择按**每次扫描**、**重要环节**或**仅派送当天**提醒。

## 联系 Kuehne+Nagel

Kuehne+Nagel 网站提供网点查询目录及在线咨询表单：

- **全球网站与追踪门户：** [kuehne-nagel.com](https://www.kuehne-nagel.com/) 与 [myKN 公开追踪](https://mykn.kuehne-nagel.com/public-tracking/)
- **全球网点查询：** 访问 [Kuehne+Nagel 全球分支机构](https://www.kuehne-nagel.com/company/locations) 查找最近的国家或城市办事处
- **在线专家咨询：** 通过 [Talk to an expert](https://www.kuehne-nagel.com/contact/talk-to-an-expert) 提交需求获取邮件回复
- **其他货运网络：** 若订单拆分由其他承运商承运，可对比查阅 [DHL Freight](carrier:dhl-freight) 与 [DSV](carrier:dsv)。

## 常见问题

### 为什么我的 Kuehne+Nagel 单号查不到结果？

Kuehne+Nagel 公开追踪仅涵盖最近 90 天内起运的货件。请确认没有漏输字符；如果刚完成订舱，请等待首条实际达成的节点出现。如果客户参考号同时对应多票货件，请向托运人索要 Kuehne+Nagel 自己的 `RWM`、`RAPI` 或其他运单号。

### Kuehne+Nagel 追踪中的“Service complete”或“Freight handover”是什么意思？

`Freight handover` 表示 Kuehne+Nagel 已将货物交接给报关行或下程承运商，货物仍在运往你的途中。`Service complete` 表示 Kuehne+Nagel 承运的合同段已结束——这可能是港口或机场交接而非送货上门——请确认是否另行签发了末端承运商单号。

### 为什么 myKN 上显示的某些日期没有出现在 Peek 的扫描历史中？

Kuehne+Nagel 公开路线视图同时列出计划预测日期、实际达成扫描以及无日期的未来提示。Peek 仅记录 Kuehne+Nagel 标记为已实际完成的节点，因此预计目标日期不会显示为已完成的物流动态。

### Kuehne+Nagel 公开追踪记录可以保留多久？

`mykn.kuehne-nagel.com` 的公开追踪覆盖最近 90 天内起运的货件。如需查看更多信息，可在 myKN 免费注册账号。

:::sources
- [Kuehne+Nagel 全球网站](https://www.kuehne-nagel.com/)
- [myKN 公开追踪门户](https://mykn.kuehne-nagel.com/public-tracking/)
- [Kuehne+Nagel 全球网点目录](https://www.kuehne-nagel.com/company/locations)
- [Kuehne+Nagel 在线咨询表单](https://www.kuehne-nagel.com/contact/talk-to-an-expert)
- [universal-parcel-scraper Kuehne+Nagel 适配器](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/kuehne-nagel)
:::
