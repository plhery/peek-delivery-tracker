---
title: Raben 物流查询：欧洲托盘与零担单号、状态与卸货说明
description: 查询 Raben 欧洲托盘或拼箱货运：15 位单号与 myRaben 链接格式、英文物流状态含义、收货卸货责任、破损理赔及各地网点联系。
slug: raben-tracking
published: 2026-11-26
updated: 2026-11-26
---

Raben Group 是一家家族式欧洲物流集团，1931 年创立于荷兰，现总部位于波兰波兹南附近。其公路网络通过覆盖 17 个欧洲国家的分支机构承运托盘货和拼箱零担货（groupage），也承接网店配送，因此大件网购商品可能由 Raben 货车送货上门。你可以在 raben-group.com 的“Shipment tracking”输入框输入货运单号查件，或直接将单号粘贴到上方输入框。

## Raben 货运单号：格式与查找位置

Raben 为每票货运分配唯一单号，持有该单号的是订舱发货方（通常为网店或制造商）：请查看发货邮件或订单页面。Raben 波兰公司表示收件人信息均来自委托运输方，因此若没有单号请联系寄件人。更多说明见[如何查找运单号](guide:find-tracking-number)（英文）。

| 单号（虚构） | 含义 |
| --- | --- |
| `274829173648291` | `27` 加 13 位数字：Peek 目录中识别的 Raben 货运单号格式。 |
| `27 4829 1736 4829 1` | 带空格的同一单号；Peek 会忽略空格与连字符。 |
| `oftc.myraben.com/link/ShipmentInformation?ShipmentNumber=274829173648291` | raben-group.com 查询框打开的单票跟踪链接，可直接整条粘贴到 Peek。 |

意大利 [BRT](carrier:brt) 和中国韵达也使用 15 位数字，因此这类单号并非必定属于 Raben。Raben 官网搜索也支持参考号或单件标签号，但 Peek 仅按货运单号本身追踪。

## Raben 物流状态含义

Raben 官方追踪系统 myRaben 会展示当前状态、附带地点与时间的每一步记录、预计送达日、重量、件数与体积、进度条、备注及经办网点联系方式。以下按货件通常经过的顺序列出步骤：

| Raben 显示 | 对你的含义 | Peek 显示 |
| --- | --- | --- |
| `registered in the system` | 寄件人已下单订舱，Raben 已接单但尚未收到货物。 | “已生成面单” |
| `arrived to the pick-up place` | 货车已到达寄件人处准备装货。 | “已生成面单” |
| `collected` | Raben 已从寄件人处揽收货物。 | “已揽收” |
| `loaded` | 已装车：发往下一分拨站或装上派送货车。 | “运输中” |
| `unloaded` | 已在下一分拨站卸车。 | “运输中” |
| `Inventory in a warehouse` | 已入库登记于分拨站仓库。 | “运输中” |
| `shipment in depo` | 正在分拨站暂存。 | “运输中” |
| `in progress - advice required` | 运输中，派送前需先通知收件人。 | “运输中” |
| `in progress - fix delivery` | 运输中，已约定固定日期派送。 | “运输中” |
| `in delivery` | 正在派送线路上。 | “派送中” |
| `arrived to the delivery place` | 货车已抵达收货地址。 | “派送中” |
| `delivered` | 已签收送达。 | “已签收” |
| `delivered D&D` | 已送达，并由司机卸货。 | “已签收” |
| `not delivered - self collection` | 未上门派送：标记为分拨站自提（但后续仍可能安排派送），前往前请先联系查询页列出的 Raben 网点。 | “待取件” |
| `not delivered - consignee not available` | 司机上门时未找到收件人。 | “派送未成功” |

**其他派送未成功的情况。** `not delivered - delivery refused`（拒收）、`not delivered - wrong address`（地址错误）、`not delivered - truck with lift required`（需带尾板货车）和 `not delivered - wrong/missing docs`（单据错误或缺失）均会注明具体原因，Peek 统一显示为“派送未成功”。

**中途受阻。** `not delivered - breakdown lorry` 与 `not delivered - strike` 表示途中故障或罢工延误；`not delivered - at sender disposal` 表示 Raben 正扣留货物等待寄件人指令；`cancelled` 表示运输已取消。Peek 均显示为“需要留意”。

**附异常备注的签收。** `delivered - damages`、`delivered - discrepancies`、`delivered - partly returned` 和 `delivered with delay` 均表示货物已交付，同时标明了出现的问题；Peek 显示为“已签收”。

**计划变更。** `new delivery date agreed with consignee` 与 `shipment parameters updated` 仅修改了日期或货件参数，货物本身并未移动。

**清关。** 需清关的货件会出现 `customs: clearance in progress`、`customs clearance completed` 等步骤；`customs: lack of invoices / incomplete invoices` 表示海关缺少发票，请联系商家。详见[海关滞留包裹指南](guide:customs)（英文）和[物流状态含义指南](guide:tracking-statuses)（英文）。

## Raben 派送方式：托盘、卸货与时效

Raben 波兰的托盘常见问题说明了当地派送流程，附加服务均由订舱客户（通常为网店）选购：

- **卸货责任。** 卸货原则上由收件人自行负责，Raben 不负责卸货，也不负责带叉车或坡道。委托方可另行订购司机人工卸货（HUN）或送货地卸货（UNLO）；大件重货请向商家确认已购服务。
- **派送时效。** 波兰境内托盘运输按所购服务通常需 1 至 2 个工作日；跨国运输视目的地国家而定。客户也可订购指定日期（FIX）、8:00/10:00/12:00/16:00 前送达（ND 08 至 ND 16）或约定日期时段（WXACT）。
- **收件人不在场。** 如因收件人原因派送未成功，Raben 可能向委托方收取费用。
- **拍照签收（PCD）。** 若委托方选用了免费的 Picture Confirming Delivery（PCD）服务，你无需在场：司机将货放在指定位置，并拍摄面单、货物及两者合影作为交货凭证。

## Raben 货件延误、破损或未送达

**物流不动。** 若连续数日无进展，请联系商家或查询页列明的 Raben 经办网点。详见[物流停止更新的原因](guide:tracking-not-updating)（英文）。

**延误。** 欧盟官方 Your Europe 网站说明，除非另行约定，网店须在 30 天内送达；超期后你可给予合理宽限期，若仍未送达可取消订单并要求退款。

**破损或遗失。** 发货至你收货期间的货损责任由卖家承担，Your Europe 建议先联系卖家。另见[包裹破损处理](guide:damaged-parcel)（英文）和[包裹遗失处理](guide:lost-parcel)（英文）。Raben 各国理赔规则如下：

- **波兰：** Raben Logistics Polska 接受寄件人或收件人的书面理赔（通过无需 myRaben 账号的 myClaim 在线表单，或邮寄、传真、邮件提交表格）。外观明显破损须当场提出异议，隐藏破损须在签收后 **7 天**内申报（国际运输不含周日与公众假期）。Raben 在收齐材料后 **30 天**内答复。国内货件自计划送达日起满第 **31 天**视为遗失。
- **德国：** Raben Germany 要求收货时当场核验包装与件数，并将明显破损详细写入交货单由司机共同签字；须向负责的 Raben 分部书面报损，隐藏破损发现后立即报告，最晚不超过签收后 **7 天**。

## Raben 客服与联系方式

Raben 官网公布的联系渠道如下：

- **单票专属联系人：** Raben 建议优先联系货件状态页面显示的专属经办人（Peek 不展示该联系方式）。
- **波兰客服时间：** 波兰时间周一至周五 08:00–16:00，目标 3 小时内回复，默认使用波兰语服务。
- **各地网点：** 可在 Raben [联系页面](https://www.raben-group.com/contact)按邮编查找分拨站或按国家浏览分支机构；各国子站（如 `polska.raben-group.com`）亦列有本地联系方式。

## 使用 Peek 跟踪 Raben 货件

由于 Raben 的 15 位单号与 BRT 和韵达重叠，Peek 无法仅凭数字断定。尽管粘贴即可：Peek 可以跟踪超过 4,500 家快递公司。添加时 Peek 会同时询问 Raben 和 BRT（若都查不到再以 Ozon 兜底，期间显示“正在查询物流服务…”），当 Raben 查询系统认出该单号时显示“Raben 有这个包裹的记录。”；若多家均有记录则由你选择。若暂均无记录，页面会显示“暂无物流记录”并持续查询，或在你选中 Raben 后显示“等待 Raben 更新”。你也可以点击“更换”，在“符合这个单号”下选择“Raben”或直接搜索（搜“Raben Group”同样可找到），已添加的包裹则用“更换快递公司”。

粘贴 `myraben.com` 或 `oftc.myraben.com` 的跟踪链接（`…/link/ShipmentInformation?ShipmentNumber=…`）会直接归属 Raben。若你的 Raben 单号格式不同，手动选择 Raben 即可：Peek 会向 Raben 查询任意 4 至 40 位含至少一个数字的字母数字组合。

Peek 读取 myRaben 英文页面：展示状态、保留 Raben 原文并附带城镇和国家的每条步骤、预计送达日（纯日期；一旦司机排线后显示带起止时间的派送时段）、重量与签收时间，时间换算为你设备的本地时间。仅改动日期或参数的步骤不会改变当前阶段。件数、体积、进度条、备注及经办网点联系方式均会被剔除；公开查询本身也不含收寄件人姓名、地址、自提点、服务名或签收证明。

当你正在 Peek 中查看货件，或派送当天已开启通知时，Peek 最多每 10 分钟直接查询一次 Raben；在 Raben 扫描货件开始派送的当天，只要 Raben 自己的查件系统正常响应，自最新扫描所在国家的早上 6 点起（Raben 给出派送时段后，则从时段开始前半小时起）最多每 2 分钟查询一次。连续两天无新动态（或在“已生成面单”/“已揽收”停留满 12 小时）后改为每小时一次；在“已生成面单”停留满 7 天后每 6 小时一次。若货件停留在“已生成面单”、“已揽收”或“运输中”满 4 天无新扫描，Peek 会提示“自……起没有物流更新”及“包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 Raben 查询。”Peek 可以按“每次扫描”、“重要环节”或“仅派送当天”通知你。Peek 无法代你联系 Raben 或商家、预约卸货或发起理赔。

## 关于 Raben 物流查询的问题

### Raben Group 是什么公司？

一家创立于 1931 年荷兰 Winterswijk、现总部位于波兰波兹南附近 Robakowo 的家族式欧洲物流集团，通过公路在 17 个欧洲国家运输托盘与拼箱零担货，通常由寄件方委托订舱。

### 如何查询 Raben 货件？

在 raben-group.com 的“Shipment tracking”输入框填入货运单号并点击“Search”，即可在新标签页打开 Raben 查询页；也可以直接将单号或查询链接粘贴到上方输入框。

### Raben 司机会帮我卸货吗？

按 Raben 波兰规则，只有订舱企业额外购买了卸货服务（HUN 或 UNLO）才会由司机卸货；默认卸货由收件人负责。若记录显示 `delivered D&D`，则表示司机已完成送达并卸货。

### Raben 运输需要多长时间？

波兰境内按寄件人所购服务需 1 至 2 个工作日；跨国运输取决于目的地国家。具体预计送达日期可在 Raben 查询页查看。

### Raben 货件破损该联系谁？

请先联系商家：在欧盟，收货前的货损责任由卖家承担。在波兰，你也可以亲自向 Raben Logistics Polska 提交书面理赔，隐藏破损须在签收后 7 天内申报。

:::sources
- [Raben Group: home page](https://www.raben-group.com/) – 查询框、“Search”、新标签页、17 个国家
- [Raben Group: Customer Zone](https://www.raben-group.com/customer-zone) – 每票货一个单号、预计日期、状态页面上的联系人
- [Raben Group: Contact](https://www.raben-group.com/contact) – 分拨站搜索、按国家列出的分支机构
- [Raben Group: Road transport and international transport](https://www.raben-group.com/services/road-network) – 拼箱零担（groupage）
- [Raben Group: E-commerce](https://www.raben-group.com/e-commerce) – 为网店配送
- [Raben Group: Picture Confirming Delivery](https://www.raben-group.com/it-solutions/pcd) – 免费服务、照片、交货凭证
- [Raben Group: Raben Group in Europe](https://www.raben-group.com/about-us/raben-group-in-europe) – 1931 年、Winterswijk、家族企业、17 个国家
- [Raben Group: Imprint](https://www.raben-group.com/imprint) – 波兹南附近的 Robakowo
- [Raben Poland: Pytania i odpowiedzi - Transport paletowy](https://polska.raben-group.com/strefa-klienta/pytania-i-odpowiedzi-transport-paletowy) – 卸货、HUN、UNLO、时效、FIX、ND、WXACT、派送未成功的费用、PCD、客服时间、波兰语
- [Raben Poland: Strefa uczestnika przewozu](https://polska.raben-group.com/strefa-uczestnika-przewozu) – 收件人信息来自委托运输方
- [Raben Logistics Polska: Claims](https://polska.raben-group.com/en/customer-zone/raben-logistics-polska/claims-raben-logistics-polska) – 谁可以索赔、如何索赔、明显与隐藏破损、遗失、30 天内答复
- [Raben Germany: Transportschäden – Wir helfen Ihnen](https://deutschland.raben-group.com/kundenbereich/reklamationen) – 收货时核验、书面报损、7 天
- [Your Europe: Your rights when shopping in the EU](https://europa.eu/youreurope/citizens/consumers/shopping/shopping-consumer-rights/index_en.htm) – 30 天、卖家负责、先联系卖家
- [Universal Parcel Scraper: Raben](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/raben) – 单号格式、链接、参考号、查件页面显示的内容、步骤与阶段、Peek 读取的内容
:::
