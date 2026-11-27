---
title: EURODIS 物流查询：运单号格式与各国合作派送商
description: 查询德国、奥地利及欧洲多国 EURODIS 包裹与托盘：13 位运单号和 24 位条码格式、各国合作派送商（trans-o-flex 等）及客服方式。
slug: eurodis-tracking
published: 2026-11-27
updated: 2026-11-27
---

EURODIS 是一个覆盖欧洲的包裹与托盘物流网络。在每个国家，都由当地合作伙伴负责揽收与派送：德国为 trans-o-flex，奥地利为 Österreichische Post（奥地利邮政）。你可以通过 EURODIS 的 [Track & Trace 页面](https://eurodis.com/track-trace/)跨网络追踪货物（下文详述）。如果当地合作伙伴给了你它自己的追踪单号，也可以直接粘贴到上方输入框查询。

## EURODIS 运单号是什么样的

Peek 的代码识别两种 EURODIS 单号：一种代表整票货物，另一种是其中每个包裹或托盘的独立条码。以下使用虚构数字说明：

| 单号（虚构示例） | 含义 |
| --- | --- |
| `7394028156342` | 13 位数字：EURODIS 整票运单号，涵盖该票货中的所有包裹和托盘。 |
| `849203716482910375618294` | 24 位字符：单个包裹或托盘的条码。大多为数字，部分合作伙伴会在前面加字母。 |
| `eurodis.com/track-trace?trackCollo-input-of=849203716482910375618294` | 含单号的 EURODIS 查询链接。Peek 会从中提取单号并归类到 EURODIS。 |

负责派送的合作伙伴也可能有自己的追踪单号，EURODIS 查询页面会将其显示在 EURODIS 单号旁边。如果你手上有该合作伙伴的单号，可直接向该合作伙伴查询，或在 Peek 中归到该合作伙伴名下追踪。

不少其他快递公司也使用 13 位和 24 位数字，因此仅凭格式无法断定单号一定属于 EURODIS；24 位数字也符合 [Austrian Post](carrier:austrian-post) 的格式之一。如果完全没有单号，请询问寄件人，或参阅[如何查找快递单号](guide:find-tracking-number)（英文）。

## 谁负责派送 EURODIS 货物：各国合作伙伴

EURODIS 网络连接 38 个国家。其 [Network & Members 页面](https://eurodis.com/network-members/)列出的成员包括：

| 国家/地区 | EURODIS 合作伙伴 |
| --- | --- |
| 德国 | trans-o-flex |
| 奥地利 | Österreichische Post（[Austrian Post](carrier:austrian-post)） |
| 匈牙利 | Express One HU（[Express One](carrier:express-one)） |
| 波黑、保加利亚、黑山、斯洛伐克、斯洛文尼亚 | Express One |
| 捷克 | One by Allegro |
| 克罗地亚 | Overseas |
| 希腊、塞浦路斯 | [Geniki Taxydromiki](carrier:geniki-taxydromiki) |
| 意大利 | SDA / [Poste Italiane](carrier:poste-italiane) |
| 卢森堡 | Interoute |
| 荷兰 | [PostNL](carrier:postnl) |
| 安道尔、葡萄牙、西班牙 | Redur |
| 罗马尼亚 | [FAN Courier](carrier:fan-courier) |
| 塞尔维亚 | City Express |
| 瑞士、列支敦士登 | Bonafide |

在比利时、丹麦、法国、爱尔兰、波兰、瑞典和英国等其他国家，由当地派送合作伙伴承接 EURODIS 货物。德国成员 trans-o-flex 表示，它通过 EURODIS 承运德国境内以及发往其他 38 个欧洲国家的货物，既派送至企业也派送至私人住宅，且包裹和托盘可以合并为一票货物共同运输。

### 德国派送：trans-o-flex

trans-o-flex 是一家总部位于德国魏恩海姆（Weinheim）的快递与物流公司。它自己的页面对派送说明如下：

- **查询方式。** 收件人可通过 trans-o-flex insight 应用或网页查询。无需注册账号，只需输入运单号、邮编和门牌号即可查看。
- **派送时间。** 常规派送时间为德国时间周一至周五 8:00 至 17:00，周六 7:00 至 14:00。物流页面上的送达时间为预估，客服无法在派送途中更改时间，也不能直接联系司机。
- **派送通知。** 若寄件人勾选了此项服务，trans-o-flex 会通过短信或邮件通知你派送安排。
- **不在家。** 只有在你提前给出书面授权时，司机才会将货物留在指定位置。你可以在 trans-o-flex insight 中设置，但前提是寄件人允许；危险品、货到付款、岛屿派送和机密货件不适用。另见[派送未成功](guide:missed-delivery)（英文）。
- **改期、改地址与二次派送。** 只有寄件人可以申请修改地址。在德国，非冷藏货物的二次派送同样需经寄件人提出，且 insight 无法自选派送日期，请联系寄件人。
- **自行网点取件。** 除冷藏货物（2–8 °C）外，通常可前往 trans-o-flex 网点自提，请提前联系客服备货。

### 奥地利派送：Österreichische Post

在奥地利，EURODIS 的合作伙伴是 Österreichische Post。有关通知单、自提点和自提柜规则，请参阅我们的 [Austrian Post](carrier:austrian-post) 页面。

## EURODIS 物流状态

EURODIS 的 [Track & Trace 页面](https://eurodis.com/track-trace/)可用四种方式查询：24 位条码、13 位 EURODIS 运单号、寄件人参考号加目的地国家与邮编，或发件合作伙伴单号加发件国家。在德国，收件人还可使用 trans-o-flex 自家的 trans-o-flex insight（见上文）；奥地利情况见 [Austrian Post](carrier:austrian-post) 页面。

本页不列出 EURODIS 的逐条原始措辞：Peek 代码中的措辞仅源自旧版归档页面，我们无法核实当前是否一致。关于各家快递公司通用物流阶段的含义，请参阅[常见物流状态详解](guide:tracking-statuses)（英文）。

## EURODIS 货物延误、破损或未送达

**物流停滞。** 如果多日没有新动态，请携带单号联系所在国家的合作伙伴（见下文联系方式），或联系寄件人。另见[物流长期不更新](guide:tracking-not-updating)（英文）。

**派送延误。** 根据欧盟 Your Europe 网站说明，如果你作为欧盟消费者网购，除非双方另行约定时间，否则商家必须在 30 天内送达。逾期后可给予商家一段合理的额外时间；如仍未送达，你有权取消订单并要求退款。

**破损或丢失。** trans-o-flex 规定只有托运人（寄件人）可就运输途中的破损或丢失提出索赔，因此请联系寄件人。就消费者权益而言，Your Europe 指出自发货起至你签收前，商品损坏责任均由卖家承担，应先联系卖家。参阅[包裹破损怎么办](guide:damaged-parcel)（英文）和[包裹丢失处理指南](guide:lost-parcel)（英文）。

**派送投诉。** trans-o-flex 要求通过其联系表单以书面形式提交投诉。表单承诺对紧急事项或投诉在 48 小时内（工作日）回复。

**货物退回。** 如果货物已退回寄件人，请联系寄件人申请退款或重新发货。另见[包裹被退回寄件人](guide:returned-to-sender)（英文）。

## EURODIS 客服与联系方式

- **EURODIS。** 其[联系页面](https://eurodis.com/contact/)提供“Shipment request”表单：选择发货国与收货国即可找到对应货件的联系渠道。商务咨询或一般问题可发邮件至 contact@eurodis.com。
- **德国：trans-o-flex。** 收件人客服电话 +49 6201 988 222，德国时间周一至周五 7:00 至 17:00；或填写“[Are you awaiting a consignment?](https://www.trans-o-flex.com/en/contact-persons/are-you-awaiting-a-consignment/)”表单并附上运单号。
- **奥地利：Österreichische Post。** 热线与表单请见我们的 [Austrian Post](carrier:austrian-post) 页面。
- **寄件人。** 在 trans-o-flex 体系下，仅寄件人可更改收件地址或申请破损、丢失索赔，二次派送也需由寄件人提出。

## 使用 Peek 追踪 EURODIS 货件

**EURODIS 查询页。** Peek 从 eurodis.com 的追踪页面获取 EURODIS 货件节点。如果该页面未响应，Peek 会转而向追踪服务查询该单号。若各方均未查到，包裹会显示“等待 EURODIS 更新”，并且从第二次查询起，包裹页面会提示“暂时无法连接 EURODIS，Peek 会继续尝试。”Peek 会自行持续查询 EURODIS 及追踪服务，但无法代你联系 EURODIS、其合作伙伴或寄件人。

**当地合作伙伴单号。** 如果你所在国家的合作伙伴提供了它自己的单号，请将其作为独立包裹添加到 Peek：Peek 跟踪 4,500 多家快递公司，包括奥地利邮政和 Express One（匈牙利）。若 Peek 没有显示该合作伙伴，点“更换”选择即可。粘贴带 `ref=` 和单号的 trans-o-flex 查询链接（trans-o-flex.com/sendungsverfolgung/tracking）会归入 trans-o-flex，由 Peek 通过追踪服务查询。

**EURODIS 单号。** Peek 无法仅凭格式判定 EURODIS 单号，因为没有任何格式为 EURODIS 独有。遇到 13 位数字时，Peek 会先查询 [FAN Courier](carrier:fan-courier)，再查询追踪服务（显示“正在查询物流服务…”），均无结果后才尝试 Delhivery、Econt、Ozon 和 Paquetexpress；遇到 24 位数字时，则依次查询 [Austrian Post](carrier:austrian-post)、InPost 和 Ciblex，再查追踪服务，均无结果才查 Express One，从不查 EURODIS。若各方都查不到，包裹会显示“暂无物流记录”。要归类到 EURODIS，请点“更换”，搜索并选择 EURODIS；对已添加的包裹请使用“更换快递公司”。粘贴 eurodis.com 查询页链接则会立即归类到 EURODIS，显示为“识别出的快递公司”。针对 EURODIS，Peek 接受 13 位纯数字，或至少包含一位数字的 24 位字母数字组合；若格式不符，会提示 EURODIS “不使用这种格式的单号”。

**交接、查询频率与提醒。** 当 EURODIS 标明由 Express One 或 Austrian Post 派送时，Peek 会显示该成员名称和单号，并在其轨迹出现不早于 EURODIS 最新记录的扫描后切换至该成员并按其频率跟进；其他成员则不跟进。当你打开包裹页面或在派送日开启通知时，只要 EURODIS 查询页正常响应，Peek 最高每 10 分钟检查一次，在出库派送当天则自最新扫描所在时钟的早上 6 点起每 2 分钟检查一次。若连续两天无新动态（处于“已生成面单”或“已揽收”时为 12 小时），Peek 改为每小时查询一次；在“已生成面单”满七天无更新后，改为每 6 小时查询一次。若停在“已生成面单”、“已揽收”或“运输中”连续四天无新扫描，Peek 会显示“自……起没有物流更新”、提示“包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 EURODIS 查询。”并提供“向 EURODIS 寻求帮助”链接。通知可按“每次扫描”、“重要环节”或“仅派送当天”推送。

## EURODIS 物流查询常见问题

### 如何查询 EURODIS 货件？

打开 EURODIS 的 [Track & Trace 页面](https://eurodis.com/track-trace/)，使用 24 位条码、13 位运单号、寄件人参考号或发件合作伙伴单号查询；或者将 13 位或 24 位单号粘贴到 Peek 后，点“更换”选择 EURODIS。

### 谁负责在德国派送 EURODIS 货物？

由总部位于魏恩海姆的快递物流公司 trans-o-flex 负责，它是 EURODIS 的德国成员。收件人客服电话为 +49 6201 988 222（德国时间周一至周五 7:00 至 17:00），自有收件人查询工具为 trans-o-flex insight。

### 谁负责在奥地利派送 EURODIS 货物？

根据 EURODIS 合作伙伴名单，由 Österreichische Post（奥地利邮政）负责。查询与联系方式详见我们的 [Austrian Post](carrier:austrian-post) 页面。

### 如何联系 EURODIS？

请在 EURODIS 的[联系页面](https://eurodis.com/contact/)使用“Shipment request”表单，选择发件国和收件国以获取对应货件的联系方式；商务咨询和一般问题可发送邮件至 contact@eurodis.com。

:::sources
- [EURODIS: home page](https://eurodis.com/) – network of 38 countries, tracking by 24-digit barcode
- [EURODIS: Track & Trace](https://eurodis.com/track-trace/) – four tracking options
- [EURODIS: Network & Members](https://eurodis.com/network-members/) – members and delivery partners by country
- [EURODIS: Contact](https://eurodis.com/contact/) – shipment request by dispatch and delivery country; contact@eurodis.com for business enquiries and general questions
- [trans-o-flex: Express delivery in Europe](https://www.trans-o-flex.com/en/products/international-express-delivery/europe/) – partner of Eurodis GmbH, 38 other European countries, businesses and private homes, parcels and pallets in one consignment, text or email notice
- [trans-o-flex: Parcel tracking with trans-o-flex insight](https://www.trans-o-flex.com/en/e-services/parcel-tracking-with-trans-o-flex-insight/) – guest tracking with number, postcode and house number; no delivery day choice; address change and second attempt via the sender; drop-off permission and its limits
- [trans-o-flex: Questions and answers](https://www.trans-o-flex.com/en/contact-persons/questions-and-answers/) – delivery hours, estimated times, drop-off permission, branch pickup, damage and loss claimed by the shipper, written complaints, 48-hour answer
- [trans-o-flex: Are you awaiting a consignment?](https://www.trans-o-flex.com/en/contact-persons/are-you-awaiting-a-consignment/) – recipient contact form, Recipient Customer Service number and hours
- [trans-o-flex: Imprint](https://www.trans-o-flex.com/en/imprint/) – based in Weinheim
- [Your Europe: Your rights when shopping in the EU](https://europa.eu/youreurope/citizens/consumers/shopping/shopping-consumer-rights/index_en.htm) – 30 days, extra time, cancel and refund, seller responsible for damage, contact the seller first
- [Universal Parcel Scraper: EURODIS](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/eurodis) – number shapes, tracker links, the partner's number beside EURODIS's, wordings known only from archived copies, the tracking page Peek reads
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – no shape detection for EURODIS, Austrian Post and Express One, trans-o-flex links, tracking services when the tracker fails
:::
