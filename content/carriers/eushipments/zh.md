---
title: euShipments 快递查询：物流状态与当地派送承运商
description: 查询 euShipments 订单：单号来源与格式、New/Loaded hub/On delivery 等状态含义、各国末端派送快递公司及客服方式。
slug: eushipments-tracking
published: 2026-11-27
updated: 2026-11-27
---

euShipments 是一家总部位于保加利亚鲁塞（Ruse）的电商物流公司，为网店提供仓储、打包和发货服务，配送范围涵盖其“We deliver to”名单中的欧盟 27 国和英国，并将每个包裹交由当地快递公司完成末端派送。你可以在其“Track my order”页面 [eushipments.com/tracking](https://eushipments.com/tracking) 或上方输入框中查询商家发给你的单号。

## euShipments 运单号是什么样的

euShipments 未公布统一的单号格式。官网说明订单处理完成后，你会收到“tracking code, AWB number or tracking ID”，将其填入页面中标有“Enter your tracking number to track packages.”的输入框即可。查询页面按商家发给你的单号显示订单，该单号有时就是派送快递公司的自有单号。常见形式如下（数字为虚构）：

| 单号 | 含义 |
| --- | --- |
| `74829103` | 8 位数字：Peek 在 euShipments 查询页上见过的唯一格式，用于发往保加利亚的订单，当地快递也沿用同一单号派送。 |
| “Tracking Number:” 后面的号码 | 当地派送快递公司的自有单号，会在该快递收到包裹后显示在 euShipments 查询结果下方，可直接用于该快递官网查询。 |

8 位数字也符合 [Mondial Relay](carrier:mondial-relay)、[Colisweb](carrier:colisweb) 和 Heppner 的单号格式，因此单看数字无法确定就是 euShipments，请核对商家邮件中注明的物流公司。直接粘贴 euShipments 查询页链接无法自动打开订单，其页面只支持在表单内输入单号查询。如果找不到单号，请参阅[如何查找快递单号](guide:find-tracking-number)（英文）。

## euShipments 物流状态详解

查询结果先显示“My order N”和你的单号，下方是一个三列表格：“Date/Time”、“Tracking Status”和“Tracking Information”。无论由哪家快递派送，“Tracking Status”始终是 euShipments 统一的英文状态；一旦当地快递接手，右侧“Tracking Information”中还会并列显示该快递的原始状态描述。每条记录精确到分钟（如“10 August, 2026 15:39”），不标注时区。

| euShipments 显示状态 | 对你的含义 |
| --- | --- |
| `New` | 商家已向 euShipments 下达订单，可能尚未打包。 |
| `Warehouse` | 订单位于 euShipments 履约仓，后面会附上仓库名称。 |
| `Loaded hub` | 已在 euShipments 的某个枢纽装车发往下一站，后附枢纽名称及括号内的国家代码，例如 `(HR)`。 |
| `Unloaded hub` | 已在枢纽卸货，命名方式相同。 |
| `Picked up` | 当地快递公司已揽收该包裹。 |
| `In transit` | 正在当地快递网络中转运。 |
| `On delivery` | 已由快递员带出派送。 |
| `Delivered` | 已送达，是订单的最终状态。 |

`Loaded hub` 和 `Unloaded hub` 可能多次出现，对应沿途经过的各个中转枢纽。在 `Picked up` 之后，表格下方会出现三行信息：“Courier Name:”、“Tracking Number:”和“Tracking Link:”，分别给出派送快递公司名称、单号以及官网查询链接。

Peek 未见过 euShipments 针对派送失败、待自提或退件使用独立的自有状态。遇到这些情况时，请查看“Tracking Information”中派送快递的原文，或点开“Tracking Link:”查看。

## 各国负责派送 euShipments 包裹的快递公司

euShipments 将最后一公里交由当地快递公司完成。它从自有履约与越库中心（保加利亚索非亚和鲁塞、罗马尼亚奥拉迪亚、克罗地亚萨格勒布、斯洛文尼亚卢布尔雅那、斯洛伐克塞内茨、匈牙利布达佩斯）通过干线运输将包裹送至当地快递分拨中心。其 FAQ 表示共合作超过 60 家当地快递，在每个欧洲国家至少有两家合作商；其“We deliver to”名单列出 28 个国家，即欧盟 27 国及英国。

其首页“Key eCommerce partnerships”一栏展示了多家合作快递徽标，包括 [Speedy](carrier:speedy)、Sameday、[FAN Courier](carrier:fan-courier)、Express One、GLS、DPD、[BOX NOW](carrier:box-now)、[Pošta Slovenije](carrier:posta-slovenije)、[Slovenská pošta](carrier:slovenska-posta)、[PPL](carrier:ppl)、[ACS](carrier:acs-courier) 和 [Geniki Taxydromiki](carrier:geniki-taxydromiki)，但未说明具体由谁负责哪个国家。公司大事记还提到 2026 年与奥地利邮政建立战略合作，未列出具体范围。最终哪家快递送货取决于你的国家以及在商家结账时选择的配送方式，查询结果中的“Courier Name:”会直接告诉你。

**收货方式。** 在保加利亚，euShipments 支持送货到家或公司地址，以及门外自提：快递网点、代收点（PUDO）或自提柜；其克罗地亚和罗马尼亚页面也列出了类似选项。euShipments 在其网络中拥有超过 150,000 个自提点，并说明包裹备妥可取时会发送含取件指引的通知。

**派送次数、保管期与重新派送。** 这些均执行实际派送快递公司的规则，euShipments 本身不作统一规定。请打开“Tracking Link:”并用“Tracking Number:”到对应快递页面查询，如需再次派送或改地址请直接联系该快递。

**货到付款。** euShipments 在 17 个国家提供货到付款服务，由当地快递代收货款并转交商家。

## euShipments 包裹不更新、丢失或退回怎么办

**停留在 `New`。** 商家已在 euShipments 创建订单，但尚未出现仓库或枢纽记录。请联系商家确认何时发货。

**停在枢纽之间。** 出现 `Loaded hub` 后包裹正在发往下一枢纽的路上，通常要等抵达卸货后才会有下一条记录。如果停滞时间超过商家预计时效，请联系商家。另见[物流为什么停止更新](guide:tracking-not-updating)（英文）。

**`Picked up` 后不动。** 包裹已交由当地快递，请打开“Tracking Link:”并用对方单号在当地快递官网查询。

**显示已送达但没收到。** 实际送货的是当地快递，请先联系该快递，再联系商家。参阅[显示已签收但未收到](guide:delivered-not-received)（英文）。

**被退回。** euShipments 退货页面将因收件信息有误或沟通问题导致的无法派送归类为未送达货件，这类包裹会退回 euShipments 仓库或商家，不会再寄给你。请联系商家安排重发或退款。更多内容见[包裹退回寄件人](guide:returned-to-sender)（英文）。

**自行退货。** euShipments 为商家提供三种退货方式：在商家网站提交退货申请并自选快递上门取件的日期、时间和地点；由商家代为预约上门取件；或由你自行通过任意快递寄至商家提供的本地退货地址（即 euShipments 的某一仓库）。具体适用哪一种请看商家的退货条款。参阅[退货包裹查询](guide:returns-tracking)（英文）。

**丢失或破损。** euShipments 不向收件人提供索赔流程，因为运输合同由商家签订。在欧盟，自发货起至你签收期间的商品损坏责任由商家承担，且除非另行约定，商家应在 30 天内送达；逾期可催告商家并给予合理宽限期，若仍未送达即可取消订单并要求退款。另见[包裹丢失](guide:lost-parcel)（英文）和[包裹破损](guide:damaged-parcel)（英文）。

## euShipments 客服电话与联系方式

euShipments 官网公布的联系方式如下：

- **电话：** +359 700 70 755（保加利亚号码），见于联系页、FAQ 和查询页，未注明接听时间。
- **邮箱：** office@eushipments.com。
- **联系表单：** 面向商家的报价问卷（需填写月订单量、销售市场等），不适用于收件人。

其面向商家的 FAQ 说明，仅在商家“提出需求时”才向终端买家提供电话、邮件和在线客服支持。因此请先联系下单的商家；若涉及具体派送问题，直接联系“Courier Name:”所列快递公司会更快。

## 使用 Peek 追踪 euShipments 包裹

[Peek](/) 无法仅凭格式识别 euShipments 单号：Peek 没有为 euShipments 设置单号规则，且 8 位数字与其他多家快递存在弱匹配。尽管粘贴查询：Peek 支持超过 4,500 家快递公司。粘贴后请点击“更改”并选择“euShipments”，或对已添加包裹使用“更改快递公司”。

Peek 会直接读取 euShipments 查询页，展示当前阶段、轨迹历史以及仓库和枢纽记录的地点（枢纽附带国家代码）。Peek 保留 euShipments 的英文状态，不展示“Tracking Information”中当地快递的原文。如果“Tracking Link:”指向 Peek 支持跟进的承运商（例如 [Econt](carrier:econt)、Sameday、FAN Courier、[Packeta](carrier:packeta)、Slovenská pošta 或 Geniki Taxydromiki）且链接中带有对方单号，Peek 会显示“由……派送”，并在单号不同时列出对方单号。当该当地快递仅收到预报而尚无自身扫描时，则显示“…… 已收到预报，尚未收到包裹”。Peek 会向该快递查询，一旦对方轨迹中出现带日期且不晚于 euShipments 最新记录的进展，便切换为跟进该快递并保留 euShipments 之前的记录；否则继续展示 euShipments 轨迹。

由于 euShipments 查询结果既不提供预计送达日期也不提供签收证明，Peek 也不会显示这两项。当你打开包裹页面或在派送日开启通知时，只要 euShipments 官网正常响应，Peek 最高每 10 分钟直接查询一次，出库派送当天则自最新扫描所在时钟早上 6 点起每 2 分钟查询一次。若连续两天无新动态（“已创建运单”或“快递公司已收件”状态为 12 小时），改为每小时查询一次；在“已创建运单”满七天无新动态后，改为每 6 小时查询一次。若包裹在“已创建运单”、“快递公司已收件”或“运输途中”状态连续四天无新扫描，Peek 会显示“自……以来无物流更新”、提示“包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 euShipments 查询。”并提供“联系 euShipments 获取帮助”链接。通知支持“每次扫描”、“重要节点”或“仅在送达时”。Peek 无法代你联系 euShipments、商家或当地快递。

## euShipments 物流查询常见问题

### euShipments 是正规公司吗？

是的。它是总部位于保加利亚鲁塞的物流公司，2012 年以 InOut Trade 名义成立，2019 年更名为 euShipments.com，通过自有系统对接为 Temu、eMAG 等平台上的网店卖家发货。

### 哪家快递会派送我的 euShipments 包裹？

由你所在国家的当地快递公司派送。对方揽收包裹后，euShipments 查询表格下方会列出“Courier Name:”、“Tracking Number:”和“Tracking Link:”。

### euShipments 派送需要多久？

取决于商家的备货仓库位置。从索非亚和鲁塞中心发出，euShipments 标称发往保加利亚和罗马尼亚为次日达，发往克罗地亚、斯洛文尼亚、斯洛伐克和捷克为 2 至 4 天；从萨格勒布、卢布尔雅那或塞内茨发往本国为 1 个工作日。具体订单请以商家预计时间为准。更多见[快递派送时效](guide:delivery-times)（英文）。

### euShipments 上的 Loaded hub 是什么意思？

表示包裹已在 euShipments 的某一枢纽装车准备发往下一段，后面会附上枢纽名和括号内的国家代码。`Unloaded hub` 则表示抵达下一个枢纽并卸货。

### euShipments 配送哪些国家？

其“We deliver to”名单共列出 28 个国家，包括欧盟全部 27 个成员国以及英国，每个国家均由当地快递公司完成末端派送。

:::sources
- [euShipments: Track Your Order](https://eushipments.com/tracking) – tracking form and its wording, phone, email
- [euShipments: Parcel Tracking For End Customers](https://eushipments.com/ecommerce-delivery/new-parcel-tracking/parcel-tracking-for-end-customers) – tracking code, AWB number or tracking ID, 60+ last-mile partners
- [euShipments: We deliver to](https://eushipments.com/we-deliver-to) – the 28 countries listed: the 27 of the EU and the UK
- [euShipments: FAQ](https://eushipments.com/faq) – more than 60 couriers, at least two per country, support for end customers when requested, phone, email
- [euShipments: Contact Us](https://eushipments.com/contact) – phone, email, Ruse, quote form for shops
- [euShipments: About us](https://eushipments.com/about-us) – InOut Trade, 2012, 2019 rename, Austrian Post partnership, 17 cash-on-delivery countries
- [euShipments: Home](https://eushipments.com/) – couriers' logos among the Key eCommerce partnerships
- [euShipments: eCommerce Deliveries To Bulgaria](https://eushipments.com/countries/bulgaria) – Sofia and Ruse, line haul to the courier's depot, address and out-of-home delivery, transit times, cash on delivery
- [euShipments: International eCommerce Delivery To Romania](https://eushipments.com/countries/romania) – Oradea, Budapest, delivery options
- [euShipments: Domestic and International eCommerce Delivery To Croatia](https://eushipments.com/countries/croatia) – Zagreb and the other centres, next-day delivery, lockers, pickup points, courier offices
- [euShipments: International Parcel Delivery To Slovenia](https://eushipments.com/countries/slovenia) – Ljubljana, 1 business day
- [euShipments: International Delivery To Slovakia](https://eushipments.com/countries/slovakia) – Senec, 1 business day
- [euShipments: Out-of-home Delivery](https://eushipments.com/ecommerce-delivery/out-of-home-delivery) – 150,000 locations, collection notifications
- [euShipments: Returns Management](https://eushipments.com/returns-management) – undelivered shipments, three ways to return
- [euShipments: Temu Integration](https://eushipments.com/integration/ecommerce-platform-integration/temu) – Temu sellers
- [euShipments: eMAG Integration](https://eushipments.com/integration/ecommerce-platform-integration/emag-integration) – eMAG sellers
- [Your Europe: Shipping and delivery](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_en.htm) – 30 days, trader responsible until you receive the goods, reminder and refund
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – result page labels, statuses, numbers, what Peek reads and shows
:::
