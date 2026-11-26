---
title: DHL eCommerce 快递查询：GM 单号、状态与客服
description: 查询 DHL eCommerce（原 DHL Global Mail）包裹：GM 单号格式、英文物流状态对照、USPS 与本地邮政末端派送及客服电话。
slug: dhl-ecommerce-kuaidi-chaxun
published: 2026-11-25
updated: 2026-11-25
---

DHL eCommerce（原名 DHL Global Mail）是 DHL 旗下专攻电商网购的包裹业务部门。它在自有分拣中心处理包裹后，交由你所在国家的派送公司完成末端派送：在美国交由 [USPS](carrier:usps)，在其他国家则交给当地邮政或其他快递公司。你可以在 DHL eCommerce 的 Webtrack 网站输入商家发货邮件里的单号查询，或直接粘贴到上方查询框中。

## DHL eCommerce 快递单号格式

DHL eCommerce 单号至少包含 10 位字符，通常以 GM 开头。Webtrack 一次最多可查询 10 个单号，中间用英文逗号分隔。找不到单号请直接联系商家。

| 单号形态（虚构示例） | 说明 |
| --- | --- |
| `GM4829103756182947` | GM 后接 16 至 18 位数字：GM 单号，DHL eCommerce 惯用的形态。 |
| `GM4829103756` | GM 后接 8 至 15 位数字：较短的 GM 单号。 |
| `9261482910573648291057` | 以 9261 或 9361 开头的 22 位数字：美国包裹移交 USPS 后使用的 USPS 单号。 |
| `7394028156342819` | 16 或 17 位数字：另一种 DHL eCommerce 单号形态。 |
| `0031482910573648291057364829105` | 0031 后接 27 位数字：另一种 DHL eCommerce 单号形态。 |

Webtrack 页面上的 “Delivery by” 一栏会写明末端派送承运商，后续也可能显示对方的自有单号。

容易混淆的单号：16 位数字同样可能属于 TNT、Evri、Canada Post 或 Correos Express，9261 或 9361 开头也可能直接是 USPS 单号。14 位数字可能属于拥有独立查件系统的 DHL eCommerce UK，`JVGL` 开头属于 [DHL eCommerce 荷兰](carrier:dhl-ecommerce-nl)，而 [DHL Express](carrier:dhl-express) 和 [DHL Paket](carrier:dhl) 则采用完全不同的单号形态。详见[快递单号格式说明](guide:tracking-number-formats)（英文）。

## DHL eCommerce 物流状态含义

Webtrack 会在顶部展示一行汇总状态（如 `En Route`、`Delivered`、`Exception` 等），下方则是全大写的 “Tracking History” 扫描记录。大家最常看到的扫描，按包裹经过的顺序如下：

| DHL eCommerce 显示状态 | 对你的含义 |
| --- | --- |
| `LABEL CREATED` | 商家已打印面单，DHL eCommerce 尚未收到包裹。 |
| `EN ROUTE TO DHL ECOMMERCE DISTRIBUTION CENTER OR AWAITING PROCESSING` | 包裹正发往 DHL 分拣中心，或仍在商家处等待交接。 |
| `PACKAGE RECEIVED AT DHL ECOMMERCE DISTRIBUTION CENTER` | DHL eCommerce 已收到包裹。 |
| `PROCESSED` | 已在分拣中心完成分拣。 |
| `DEPARTURE ORIGIN DHL ECOMMERCE FACILITY` | 已离开首个 DHL eCommerce 设施。 |
| `ARRIVAL DESTINATION DHL ECOMMERCE FACILITY` | 已抵达靠近你的目的地 DHL eCommerce 设施。 |
| `CUSTOMS CLEARANCE` | 正在清关。 |
| `TENDERED TO DELIVERY SERVICE PROVIDER, ALLOW 1-3 DAYS FOR UPDATES FOR PACKAGES WITHIN THE US` | 已移交末端派送承运商；美国境内包裹在此后可能暂停更新 1 至 3 天。 |
| `OUT FOR DELIVERY` | 今日由派送员或邮递员外出派送。 |
| `DELIVERY ATTEMPT FAILED` | 派送未成功，请留意末端派送方留下的通知单。 |
| `DELIVERED` | 已签收送达。 |
| `RETURNED TO SENDER` | 正退回商家。 |

美国包裹在移交后还会同步显示 USPS 的节点，例如 `ARRIVAL AT POST OFFICE`。

有一条状态常引发误解：`Picked up at parcelshop` 表示寄件人在包裹点交寄了包裹，并不是包裹正放在自提点等你去拿。

## DHL eCommerce 派送方式：USPS、当地邮政与派送未成功

在美国境内，DHL eCommerce 的 Webtrack 常见问题说明，包裹由 USPS 随你平时的投递时段一起送达。国际包裹则交由“当地派送服务商”负责，可能是“当地邮局或其他快递公司”。

因此，派送未成功的通知单、自提柜台、保管天数及重派规则均以末端派送方为准。DHL 建议你先检查信箱里是否有包裹或通知单，并直接向 USPS 或当地承运商提交派送说明。

关于改址：Webtrack 常见问题说明，包裹一旦离开商家，DHL eCommerce 便无法修改收件地址，请直接联系商家。

## DHL eCommerce 包裹延误、丢失或退回

**暂无扫描记录。** Webtrack 常见问题指出，物流节点通常在 24 至 48 小时内出现；若超过 48 小时仍无任何记录，请联系商家。Webtrack 查不到结果时，建议先核对单号再联系商家。

**连续多日无更新。** Webtrack 常见问题提及天气、节假日、清关、罢工及其他 DHL 无法控制的因素。美国境内包裹在移交扫描本身就提示需预留 1 至 3 天等待更新。参考[物流长时间不更新怎么办](guide:tracking-not-updating)（英文）。

**超期未收到。** 如果预计送达时间窗过后仍未收到包裹，Webtrack 常见问题建议联系商家。在欧盟地区，Your Europe 进一步说明：在你收货前商品破损由商家担责，且除非另有约定，商家应在 30 天内交货；逾期未交时可催告并给予宽限期，之后有权解除合同并要求退款。

**清关。** `CUSTOMS CLEARANCE` 表示包裹正在海关查验。[包裹卡在海关怎么办](guide:customs)（英文）解释了可能涉及的税费。

**退回寄件人。** `RETURNED TO SENDER` 表示包裹正在退回商家，请联系商家申请退款或重发。

## DHL eCommerce 客服渠道与联系电话

按照 Webtrack 常见问题的建议，请先联系商家。Webtrack 的 “Contact Customer Service” 页面按国家列出了客服卡片：

- **美国与加拿大**（从美国寄出的包裹）：+1 317-554-5191，周一至周五 EST 8:00 至 20:00（加拿大卡片写为 ET 8:00 至 21:00）。
- **德国：** 卡片引导至 [DHL 德国客服页面](https://www.dhl.de/de/privatkunden/hilfe-kundenservice.html)，周一至周日 7:00 至 20:00。
- **爱尔兰：** 818 370050（爱尔兰境内拨打），周一至周五 8:00 至 18:00。
- **澳大利亚：** +61 37 0356818，周一至周五 8:00 至 18:00。
- **奥地利与瑞士：** +49 228 92 95 95 95，周一至周五 9:00 至 18:00（奥地利卡片标注早上 8:00 起）。
- **英国：** 卡片列出的是 DHL eCommerce UK 专线 024 7693 7770。

Webtrack 的 “Contact Support” 表单说明客服会在 “24-72 小时内”回复，页面未标明通话资费。

## 使用 Peek 追踪 DHL eCommerce 包裹

对于 GM 后接 16 至 18 位数字的单号，[Peek](/) 会立刻识别为 DHL eCommerce。对于 GM 后接 8 至 15 位数字的较短单号，Peek 会同时向 DHL eCommerce 与 Aramex 查询（在法国添加时也同时问 Colis Privé），DHL eCommerce 有记录即认定为 DHL eCommerce。对于与其他快递公司共用的形态，Peek 会同时询问可能匹配的快递公司（16 位数字查 TNT，末位校验通过时一并查 Evri UK、Canada Post 与 Correos Express；在法国添加的 17 位数字查 Colis Privé；9261 或 9361 开头同时查奥地利邮政，两家都查不到时，再查物流查询服务、USPS 页面及 DHL 全球查件页）。在其他国家/地区添加时，Peek 要等其他快递公司和物流查询服务都查不到这个单号，最后才问 Colis Privé。如果仅 DHL eCommerce 查到记录，Peek 会直接提示；如果多家承运商都有记录，Peek 会将它们逐一列出并标注“有这个单号”，供你挑选；点击“更换”可为任意至少 10 位字母与数字的单号手动指定 DHL eCommerce。来自 webtrack.dhlglobalmail.com 与 ecommerceportal.dhl.com 的链接均可直接粘贴，包含非短 GM 形态 DHL eCommerce 单号的 dhl.com 查件链接同样适用。

Peek 会先查询 Webtrack；若未查到包裹，再打开 dhl.com 全球查件页，该途径耗时较长，且在 DHL 拦截自动化访问时可能失败。

你可以查看当前状态、每条扫描及其城市和国家在地图上的位置、DHL 提供的预计送达日、寄件人和签收时间；经由 Webtrack 时还可看到重量与末端派送方。Peek 会隐去你的地址、商家参考号和签收人姓名。扫描节点保留 DHL 的美式英文原文，所有妥投节点均显示为“已签收”。

当 Webtrack 标明末端由 USPS 派送时，一旦 USPS 出现不早于 DHL eCommerce 最新节点的扫描，包裹就会连同其单号转入 USPS 追踪，页面显示“由 USPS 派送”；如果 USPS 仅有预报记录而尚无实体扫描，则显示“USPS 已收到预报，尚未收到包裹”。对于 Webtrack 列出的其他末端承运商（如 La Poste、Austrian Post、Poczta Polska、Correos 或 Poste Italiane），Peek 也按相同方式衔接。仅由 dhl.com 全球页面查到的包裹，Peek 不会转到当地邮政继续跟踪。提醒可选择“每次扫描”、“重要环节”或“仅派送当天”。在你打开包裹页面期间，或在派送当天开启提醒时，Peek 最高每 10 分钟检查一次；在进入“派送中”当天，从最新扫描所在时区早上 6 点起，只要 Webtrack 有响应便每 2 分钟检查一次（若上次通过浏览器打开 dhl.com 查询则每 5 分钟一次）。当只有物流查询服务提供更新时，每小时检查一次，超过一天无新动态后每 3 小时一次，在派送当天打开页面或开启提醒时最高每 15 分钟检查一次。连续两天没有新动态后（仍停留在“已生成面单”或“已揽收”时为 12 小时后），改为每小时检查一次；停在“已生成面单”满七天无新动态则改为每 6 小时一次。当处于“已生成面单”、“已揽收”或“运输中”的包裹连续四天没有新扫描时，Peek 会提示“自……起没有物流更新”，附上说明“包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 DHL eCommerce 查询。”并提供“向 DHL eCommerce 寻求帮助”链接。

## DHL eCommerce 查询常见问题

### DHL eCommerce 和 DHL Express 是一样的吗？

不一样。DHL eCommerce 是 DHL 面向网店的包裹部门，通常把末端派送交给当地邮政或其他快递公司；DHL Express 拥有独立查件系统，Peek 也将两者作为不同承运商分别追踪。

### DHL eCommerce 是由 USPS 派送的吗？

在美国境内是的：DHL 的 Webtrack 常见问题明确说明由 USPS 负责最终派送。美国以外地区则由当地邮政或其他快递公司派送。

### 为什么我的 DHL eCommerce 还没有物流信息？

Webtrack 常见问题说明，物流事件通常在 24 至 48 小时内出现。如果超过 48 小时仍无记录，请联系发货商家。

### DHL eCommerce 是正规快递吗？

是的，它是 DHL 旗下专做电商网购包裹的业务部门，原名为 DHL Global Mail。

:::sources
- [DHL eCommerce: Webtrack tracking](https://webtrack.dhlecs.com/) – 查件页、状态标签、详情栏、10 个单号批量查询、无结果建议、联系表单与邮件更新
- [DHL eCommerce: Webtrack Contact Customer Service](https://webtrack.dhlecs.com/support/customer-service) – 各国客服卡片、电话与时间、24 至 48 小时起运常见问题、USPS 派送、延误、丢件、改址与派送说明
- [Your Europe: Shipping and delivery](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_en.htm) – 商家对交货前破损担责、30 天交货期、宽限期与退款
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – 原名 DHL Global Mail、单号形态与 USPS 单号、扫描文案与阶段、DHL Express 独立追踪、Peek 读取和显示的信息
:::
