---
title: Kun Express（鲲辰）快递追踪：KUN 单号、物流状态与 USPS 派送
description: 追踪从中国发往欧美及加拿大的 Kun Express（鲲辰）包裹：KUN 单号格式、中英文状态含义、美国段 USPS 交接规则，以及包裹停滞时的求助方式。
slug: kun-express-tracking
published: 2026-11-26
updated: 2026-11-26
---

Kun Express（鲲辰，杭州鲲辰跨境供应链管理有限公司）是一家位于中国杭州的跨境货代公司，为跨境电商卖家将包裹空运发往美国、加拿大和欧洲。在其美国专线上，末端由 USPS 负责派送。其单号以 `KUN` 开头：你可以在 [kun-express.com](https://www.kun-express.com/en) 的搜索框中输入单号（页面会跳转到 Kun Express 追踪页），或直接粘贴到上方输入框。单号由向你出售商品的商家随发货信息一并提供。

## Kun Express 运单号：外观与查找方式

Kun Express 未公开成文的编号规范，但实际追踪记录中出现的单号均为 `KUN` 后接 10 位数字（以下数字均为虚构）：

| 单号 | 归属说明 |
| --- | --- |
| `KUN4829173650` | `KUN` 加 10 位数字：Kun Express 单号。 |
| `9400148291736504829172` | 以 `94` 开头的 22 位数字：USPS 单号。 |

**在哪里查找单号：** Kun Express 面向卖家服务，包括独立站、一件代发商家以及 TikTok、Temu 和 Amazon 平台卖家。单号由卖家下单后生成，通常会附在发货邮件或订单详情页中。如果找不到，请参阅[如何查找运单号](guide:find-tracking-number)（英文）。Kun Express 的追踪页面也支持通过卖家自有参考号查询，查询后会显示对应的 `KUN` 单号。

**订单显示的是 USPS 单号：** Kun Express 向卖家介绍，其美国专线包裹会提前在 USPS 预报并全程沿用同一单号，方便卖家在 Amazon 和 Walmart 等平台回传。因此你的订单页面可能直接给出的是 USPS 单号：此时请按 [USPS](carrier:usps) 包裹进行追踪。

**容易混淆的单号：** 韩国快递公司 Kunyoung Express（건영택배）虽然名字相近，但使用纯数字运单号，与 Kun Express 毫无关系。若 `KUN` 后的数字位数不是 10 位，则不符合 Kun Express 的单号形态，请与商家邮件仔细核对。

## Kun Express 追踪状态详细说明

Kun Express 追踪页面提供中文和英文两种界面，其自有扫描记录以英文书写。按包裹大致经过的顺序如下：

| Kun Express 显示文案 | 对你的含义 |
| --- | --- |
| `Shipment information received` | 卖家已预报建单，Kun Express 尚未收到实物包裹。 |
| `Arrive at the delivery point` | 卖家已在杭州交寄包裹，并非正在向你派送。 |
| `Arrived at the receiving point` | 包裹已到达 Kun Express 仓库，地点标注为 `KUN Warehouse`。 |
| `KUN Warehouse` | 包裹已离开该仓库。该条记录没有正文，直接以地点名代替。 |
| `Arrive at the sorting center for operation and transfer` | 已到达杭州分拣中心等待操作与中转。 |
| `Cargo leaving the operation center` | 已离开分拣操作中心。 |
| `arrive at international airport to abroad` | 已抵达国际机场准备出境航班。 |
| `Waiting to be loaded` | 正在等待装机起飞。 |
| `Shipment started the customs clearance process at origin` | 正在中国口岸办理出口清关。 |
| `departed from airport of origin` | 航班已离开中国始发机场。 |
| `U.S. Postal Service, The goods are in transit` | Kun Express 在航班起降机场自行写入的节点，仅说明后续将由 USPS 派送，此时 USPS 尚未拿到包裹。 |
| `arrived at airport of destination` | 航班已降落美国目的地机场。 |
| `custom clearance completed` | 已完成美国进口清关，地点标注为 `US`。 |

此后，Kun Express 会把 USPS 的扫描记录原样复制到自家轨迹中，例如 `Arrived at USPS Regional Facility`、`Out for Delivery`、`Delivered, In/At Mailbox` 等。具体含义详见 [USPS 追踪页面](carrier:usps)。

在 Kun Express 网页上，同一条 USPS 扫描有时会重复显示多达三次：单独一行、紧跟在地点之后，以及带箭头和 USPS 详细说明的版本（前面有时还带 `On the Way` 等分类）。它们其实都是同一条扫描。Kun Express 自己的节点采用中国时间，而复制过来的 USPS 节点则使用美国当地网点时间。若包裹在入境环节受阻，请参阅[包裹在海关滞留怎么办](guide:customs)（英文）。

## 谁负责派送 Kun Express 包裹

Kun Express 的美国专线从上海、郑州或广州起飞，飞抵纽约（JFK）、芝加哥（ORD）等美国机场，随后由 USPS 派送。其另一项美国产品还列出了 ACI、DHL eCommerce 以及自有派送服务作为末端选择。

其加拿大专线从上海或青岛起飞，末端由 [Canada Post](carrier:canada-post) 派送。发往欧洲的线路未公开末端承运商名称。此外，该公司也从位于美国、加拿大和德国的海外仓发货，使用 USPS、Canada Post、DHL 及主流国际快递派送。

Kun Express 未向收件人发布关于派送次数、通知单、自提点或改址的规则。包裹移交 USPS 后，一切均按 USPS 规则执行；另见[派送未成功怎么办](guide:missed-delivery)（英文）。

## Kun Express 包裹未到货或物流停滞

**应该找谁处理：** 卖家才是 Kun Express 的签约客户，由卖家开户并下单发货。Kun Express 未面向收件人公布索赔通道或赔偿机制，因此遇到延误、丢件或破损，请直接联系卖家或下单平台。

**运输时效参考：** Kun Express 未公布美国 USPS 专线的时效。面向卖家宣传时称，其加拿大专线超过 98% 的包裹在 12 个自然日内送达，“DHL/UPS 美国虚拟仓”服务中 70% 在 7 个自然日内送达、超过 98% 在 10 个自然日内送达。这些仅为招商统计数据，并不代表你订单的承诺送达日。

**查不到记录：** 当 Kun Express 追踪页无法查到单号时，Peek 会显示“等待 Kun Express 更新”并持续查询：新建面单通常需要一段时间才会入网。若两天后 Kun Express 仍查不到该单号且没有其他来源返回记录，Peek 会提示“两天过去了，快递公司仍查不到这个单号。请检查单号、更换快递公司，或删除这个包裹。”，随后改为每天检查一次，添加满一周后改为每周检查一次。请再次与卖家核对单号是否为 `KUN` 加 10 位数字。

**长时间无更新：** Kun Express 只有在同步抓取后才会显示 USPS 扫描，因此最新的 USPS 动态可能会滞后。若包裹处于“已生成面单”、“已揽收”或“运输中”且满四天无新记录，Peek 会标出最新一条的日期（“自……起没有物流更新”），并提示：“包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 Kun Express 查询。”若超过三周仍无更新，提示会变为：“已超过三周没有新动态。如果还没收到，请联系卖家。Peek 会持续向 Kun Express 查询。”如果商家另外提供了 USPS 单号，建议也直接查询 USPS 轨迹。更多原因见[为什么物流停止更新](guide:tracking-not-updating)（英文）。

**显示已签收但没收到：** 若轨迹以 USPS 的签收记录结束，请阅读[显示已签收但未收到](guide:delivered-not-received)（英文）并联系卖家。

**包裹被退回：** Kun Express 在美国专线说明中告知卖家，若包裹无法由 USPS 妥投或被收件人拒收，可退回其美国海外仓暂存、二次销售或重新派送；加拿大专线亦同。退件未必会寄回中国：请联系卖家确认后续安排，并参阅[包裹退回寄件人](guide:returned-to-sender)（英文）。

## Kun Express 客服电话与邮箱

kun-express.com 仅列出一套面向卖家的联系方式：

- **电话：** +86 19106772411（中国手机号），周一至周日 09:00 至 18:00；网站未注明时区。
- **邮箱：** kunexpress2022@gmail.com。
- **在线表单：** 可在其[联系页面](https://www.kun-express.com/contact_en)填写姓名、邮箱与留言。
- **微信与 WhatsApp：** 网站各页底部附有二维码。

Kun Express 未提供收件人帮助指南，也未发布虚假派送短信警示。凡涉及实际处理，请联系卖家解决。

## 使用 Peek 追踪 Kun Express 包裹

输入 `KUN` 加 10 位数字的单号时，[Peek](/) 会立刻识别为 Kun Express——目录中没有其他快递共用这一格式。你可以直接输入或粘贴单号，也可以粘贴在 kun-express.com 搜索框打开的追踪页网址，Peek 会从中提取 `KUN` 单号。

在 Kun Express 自有追踪正常响应期间，当你打开包裹页面或在派送当天开启通知时，Peek 最高每 10 分钟检查一次，进入派送中当天从当地时间早上 6 点起每 2 分钟检查一次；其余时间每小时检查一次。若连续两天无新动态（或停留在“已生成面单”、“已揽收”满 12 小时），则改为每小时检查一次；停留在“已生成面单”满七天无更新后改为每 6 小时一次。Peek 会显示当前状态、带扫描地点（当 Kun Express 提供时）的物流历史以及目的地国家。每条扫描按 Kun Express 所给时区解析并转换为你的设备本地时区；不带具体时刻的 USPS 扫描仅显示日期。对于 Kun Express 页面上重复出现的 USPS 扫描，Peek 只显示一次，并去掉 USPS 的解释和美国邮编。Kun Express 的记录有时还会在 USPS 签收数周后再次冒出 `KUN Warehouse` 出库记录：Peek 会略去签收一天或更久之后出现的 `KUN Warehouse` 条目。若某条记录的写入时间早于其标注时间超过 12 小时（或在同一秒内与这类条目一同写入且早于标注时间超过 6 小时），Peek 会将其判定为提前生成的预写轨迹而不作为真实扫描，也不会改变包裹状态。在包裹签收或退回前，Peek 会在物流记录开头写：“Kun Express 的网站会提前列出整条路线，包括签收。这些步骤并非真实扫描。”并在“查看”下以灰色列出这些行：日期未到时标为“预计：”和日期，日期已过则标为“预设：”和日期。若其中一条是时间已到的签收，而没有扫描显示包裹已签收或退回，Peek 会在状态下方补充：“Kun Express 网站上的签收是提前写好的，并非真实扫描。如果没有收到，请联系卖家。”Peek 不展示 Kun Express 页面上的收件人姓名，也不提供预计送达日期。

`Shipment information received` 会转换为 Peek 的标准中文阶段文案，其余记录（包括 USPS 的记录）除 `Delivered` 等完全匹配项外均保留原文。

Peek 无法将该包裹交接给 USPS：Kun Express 的轨迹既不标明末端承运商也不公开 USPS 单号，因此包裹页不会显示“由 USPS 派送”，历史记录也仅更新到 Kun Express 同步为止。如果卖家另行提供了 USPS 单号，可将其作为独立包裹添加到 Peek 中直接追踪 USPS。

你可以选择“每次扫描”、“重要环节”或“仅派送当天”通知。Peek 无法代你联系 Kun Express 或卖家。

## 关于 Kun Express 物流查询的问题

### Kun Express 是什么公司？

Kun Express 是杭州鲲辰跨境供应链管理有限公司旗下品牌，总部位于中国杭州，为跨境电商卖家将包裹运往美国、加拿大和欧洲。该品牌创立于 2022 年，网站列出在深圳、香港、胡志明市、纽约和汉堡设有分支机构。

### 在美国谁负责派送 Kun Express 包裹？

Kun Express 美国专线的包裹由 USPS 负责派送。Kun Express 先空运至美国并完成清关，再移交 USPS，同时把 USPS 的扫描记录同步到自家追踪页面。

### “U.S. Postal Service, The goods are in transit”是什么意思？

表示包裹正处于空运前往美国的途中。这条文案是 Kun Express 自行写入的，用于标注后续将负责派送的承运商；USPS 只有在包裹通过美国海关后才会真正接手。

### Kun Express 派送需要多长时间？

Kun Express 未公开美国 USPS 专线的派送时效。其向卖家提供的参考数据显示，加拿大专线超过 98% 的包裹在 12 个自然日内送达，部分美国服务在 10 个自然日内送达，但这不构成对个人订单的时效承诺。

### Kun Express 和 Kunyoung Express 是同一家公司吗？

不是。Kunyoung Express 是一家使用纯数字单号的韩国快递公司；Kun Express 则是使用 `KUN` 开头单号的中国跨境货代。

:::sources
- [Kun Express: Home](https://www.kun-express.com/en) – 美加欧专线、2022 年品牌、客户群体、总部与分支、仓库、追踪搜索框、电话、营业时间、邮箱、微信与 WhatsApp
- [Kun Express: 首页 (home, Chinese)](https://www.kun-express.com/) – 中文公司名称、追踪搜索框及其打开的中英文追踪页
- [Kun Express: Cross border special line small package](https://www.kun-express.com/product_a_en) – 美国线机场、USPS 预注册单号、适配平台、退回美国海外仓、其他美国末端渠道、时效数据、加拿大线与 Canada Post
- [Kun Express: 跨境专线小包 (cross-border lines, Chinese)](https://www.kun-express.com/product_a) – 上述跨境专线的中文说明
- [Kun Express: Overseas Warehouse (US, Canada, Germany)](https://www.kun-express.com/product_c_en) – 海外仓及对应派送承运商
- [Kun Express: About us](https://www.kun-express.com/about_en) – 公司名称、杭州总部、各地分支与主营业务
- [Kun Express: Contact us](https://www.kun-express.com/contact_en) – 在线联系表单
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – 单号形态、扫描文案与含义、同步 USPS 扫描及其时区、参考号查询、未查到单号提示及 Peek 展示/过滤逻辑（含签收后次日及以后重复出现的 `KUN Warehouse` 条目）
:::
