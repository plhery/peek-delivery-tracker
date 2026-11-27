---
title: Morning Global 物流查询：BR…MG单号、状态与客服联系
description: 查询香港 Morning Global（MG）发出的跨境包裹：BR…MG 单号格式、各节点含义、欧美末端派送与欧盟处理费说明，以及全球客服联络方式。
slug: morning-global-mg-wuliu-chaxun
published: 2026-11-23
updated: 2026-11-23
---

Morning Global（简称 MG）成立于 1987 年，是香港快递集团 Morning Express Group 旗下的国际物流板块。公司为跨境电商卖家和购物平台提供出境包裹运输服务，在香港及中国内地多地设有办事处，包裹抵达目的国后通常交由当地合作伙伴完成末端投递。你可以在 Morning Global 网站的 [Trace & Track 英文查询页](https://www.morninglobal.com/trace-track/)输入卖家提供的单号，或直接粘贴到上方查询框中。

## Morning Global 单号格式规则

Morning Global 网站未公开单号格式文档，Peek 承运商库收录的典型单号格式如下（以下数字均为虚构示例）：

| 单号 | 说明 |
| --- | --- |
| `BR482910573MG` | `BR` 加 9 位数字再加 `MG`：Morning Global 单号。由于该结构与部分邮政单号相似，Peek 会先向 Morning Global 核实再确认归属。 |
| `BR48291057MG` | 仅有 8 位数字：不符合 Morning Global 格式，Peek 不会指向该公司，请检查是否少输了一位数字。 |

在 Peek 中输入单号时，小写字母、空格和连字符均不影响识别。Morning Global 网站查询页支持单次最多输入 20 个单号，以英文逗号分隔。

**在哪里找到单号。** 可在店铺发货邮件或订单详情页中查看。若找不到，可参阅[如何查找快递单号](guide:find-tracking-number)（英文）和[中国跨境包裹追踪指南](guide:tracking-from-china)（英文）。

**其他格式的单号。** 如果你手动选定了 Morning Global，Peek 会对任何 8 至 40 位的字母数字组合向其追踪接口发起查询。

**两大核心产品线。** Morning Global 提供自有端到端门到门渠道 MG International Express（MGIE），以及依托全球邮政枢纽运行的经济渠道 MG International Postal（MGIP）。两者合计覆盖全球 220 多个国家和地区，单件最高可承运 30 公斤。

## Morning Global 物流状态含义解析

Morning Global 追踪页面在 `Shipment Journey` 下方按条列出物流记录，通常包含状态名称、补充说明、日期时间及地点（常以国家代码加 UTC 偏移量表示，如 `CA (UTC-4)`）。按包裹常见经过顺序如下：

| Morning Global 英文状态 | 对你的含义 | Peek 显示阶段 |
| --- | --- | --- |
| `Order Created` | 商家已下单预约发货，Morning Global 尚未拿到实物包裹。 | “已生成面单” |
| `Collection` | Morning Global 已从商家处揽收包裹。 | “已揽收” |
| `Arrive Hub` | 包裹已抵达 Morning Global 转运枢纽。 | “运输中” |
| `Depart Origin` | 已离开始发枢纽启程。 | “运输中” |
| `Depart Hub` | 已离开某一转运枢纽（见于旧版查询页面）。 | “运输中” |
| `Ready for Departure` | 已完成出运准备，等待发往目的国。 | “运输中” |
| `Flight Departure to Destination` | 飞往目的国的国际航班已起飞。 | “运输中” |
| `Arrive Destination` | 包裹已抵达目的国，随后进入清关及末端交接环节。 | “运输中” |
| `Customs Clearance Begins` | 目的国海关已开始清关审核。 | “清关中” |
| `Customs Cleared` | 目的国海关已清关放行。 | “运输中” |
| `Handed Over for Delivery` | 已移交目的国当地派送伙伴，但尚未装车派送。 | “运输中” |
| `Out for Delivery` | 当地派送员正在派送途中。 | “派送中” |
| `Delivered` | 已妥投签收。 | “已签收” |

当一条记录的补充说明比状态标题包含更多信息时，Peek 会以“状态：说明”的格式呈现，并去掉其中的电话号码。未列入上表的其他措辞会按 Peek 通用规则解读，或原样保留不改变阶段。

**查无此单。** 若后台查不到该单号，Morning Global 网站会提示 `No shipment record was found for this tracking number.`。

## 谁负责派送 Morning Global 包裹

出现 `Handed Over for Delivery` 后，Morning Global 会把包裹转交给目的国当地派送合作伙伴。其网站并未公开各国具体合作伙伴名单，查询结果也不向 Peek 标明合作商名称，也没有显示合作商自有单号的栏目；如需改约投递时间，请联系卖家或 Morning Global 客服获取末端转单号。

**自营网点与合作网络。** Morning Global 将英国与美国列为自营地区，并在伦敦与洛杉矶设有区域总部；加拿大与美国同属其重点布局市场，澳大利亚则依托当地合作伙伴网络派送。

**派送尝试与自提规则。** Morning Global 不面向收件人公布派送次数、未投递通知单或自提点保管期限，这些均按当地派送商规则执行。详见[错过派送怎么办](guide:missed-delivery)（英文）和[未领取的包裹怎么处理](guide:uncollected-parcel)（英文）。

**欧盟进口关税与 2 欧元处理费。** 自 2026 年 7 月 1 日起，从欧盟境外网购且货值不超过 150 欧元的包裹每类货品须缴纳 3 欧元欧盟关税；自 2026 年 11 月 1 日起，无论货值高低，每类货品另须缴纳 2 欧元欧盟处理费。2026 年 10 月 22 日，Morning Global 向发件商家发布公告称，自 11 月 1 日起按每条报关品目收取 2 欧元清关处理费（视目的国规定另计增值税），但公告未说明商家是否将该费用转嫁给消费者。英国、美国、加拿大和澳大利亚则遵循各自海关规定，详见[包裹被海关扣留怎么办](guide:customs)（英文）。

**清关资料上传。** 若海关要求收件人补充身份证件复印件、发票或其他文件，Morning Global 网站设有专门的 [Upload Clearance Documents](https://www.morninglobal.com/upload-clearance-documents/) 表单（需填写运单号）。切记只在 `morninglobal.com` 官方域名下提交资料，切勿轻信陌生短信里的付费或身份上传链接，详见[防范快递诈骗短信](guide:delivery-scam-texts)（英文）。

**延误公告。** Morning Global 会在其网站[新闻公告页](https://www.morninglobal.com/news/)发布异常提醒，例如 2026 年 10 月 30 日曾公告澳大利亚劳资纠纷可能导致 MGIP 渠道延误约 2 至 3 个工作日。

## Morning Global 包裹无更新、丢失或退回怎么办

Morning Global 的签约方是委托发货的商家。遇到包裹延误、丢失、破损或退回，请先联系网店或下单平台处理。

**暂未查到记录。** 若单号符合 `BR…MG` 结构但尚无结果，Peek 会标记可能为 Morning Global 并等待首次扫描；如果该单号同时满足邮政校验码规则，Peek 会同时询问 Morning Global 与 Chronopost 并以实际认领者为准。若持续一周仍无任何更新，请联系商家核对单号。

**停在 `Order Created`。** 表示商家仅完成了线上下单，尚未把实物交给 Morning Global，请向商家询问实际交货日期。

**停在清关阶段。** `Customs Clearance Begins` 说明包裹正在目的国海关审查。如收到缴税通知可参阅[包裹被海关扣留怎么办](guide:customs)（英文）；若滞留时间过长，请通知卖家。

**多日无新扫描。** 包裹处于「已生成面单」「已揽收」或「运输中」且 4 天没有新扫描时，Peek 会显示「自10月12日周一起没有物流更新」这样的提醒（日期为最后一条扫描的日期），并说明包裹在长途航班上、在海关或遇到周末时常常暂时没有动静。若已显示 `Handed Over for Delivery`，建议索要本地派送商单号跟进，详见[为什么快递物流停止更新](guide:tracking-not-updating)（英文）。

**丢件或破损。** 务必要尽早处理。Morning Global 服务条款规定，发货商家须自公司揽收之日起 **30 天内** 提出索赔报备，并在 **90 天内** 补齐佐证材料；因 Morning Global 责任导致的赔付上限为申报价值且每票最高不超过 50 美元。作为买家，请直接找商家要求退款或补发，详见[包裹丢失](guide:lost-parcel)（英文）和[包裹破损](guide:damaged-parcel)（英文）。

**地址错误或拒收。** Morning Global 条款明确写明，因地址错误/不全、无人应门或收件人拒收导致的派送失败，对商家仍按已完成订单结算。请及时向卖家核对收货地址；若包裹被退回，参阅[包裹退回寄件人](guide:returned-to-sender)（英文）。

**显示已签收但没拿到。** 请先检查门口、信箱并询问邻居。Morning Global 网站在 `Signed By` 栏可能会显示签收人信息（Peek 不显示该栏），核查后尽快联系商家，详见[显示已签收但未收到包裹](guide:delivered-not-received)（英文）。

## Morning Global 客服与联系方式

[Morning Global 联系我们页面](https://www.morninglobal.com/contact/)（货运查询通道）：

- **美国团队**：cs.mgus@morninglobal.com，电话 `+1 888 981 8688`。
- **英国团队**：cs.mgeu@morninglobal.com，电话 `+44 20 7019 9035`。
- **香港及澳门团队**（香港为全球总部）：cs.mghk@morninglobal.com，电话 `+852 3669 6360`。
- **其他区域团队**：中国内地、印度、意大利、菲律宾和新加坡在同一页面均列有各自联络邮箱。
- **全球其他地区**：cs.mgworldwide@morninglobal.com，并支持 WhatsApp（`+1 (479) 777 3307`）及页面上的二维码联络。
- **在线留言表单**：同页提供，需填写姓名、电子邮箱与留言内容。

其网站未标注具体客服办公时间，并在追踪页面提示联系客服时请务必附上运单号。涉及订单退款或重发，仍请先找下单店铺。

## 使用 Peek 追踪 Morning Global 包裹

单凭 `BR…MG` 格式，[Peek](/) 不会直接武断判定为 Morning Global，而是直接粘贴即可：Peek 跟踪 4,500 多家快递公司，会先联网核实，一旦 Morning Global 后台认领该包裹，便会显示“Morning Global 有这个包裹的记录。”；如果你粘贴的是带有 `tracking_number=` 参数的 morninglobal.com 追踪链接，则会直接自动归类为 Morning Global。如未自动识别，可在添加包裹时点击“更换”手动选中 Morning Global，或在包裹详情页点击“更换快递公司”。

Peek 仅凭单号即可直接读取 Morning Global 网站追踪（无需邮编），展示当前阶段（“已生成面单”、“已揽收”、“运输中”、“清关中”、“派送中”、“已签收”）、每条轨迹及其地点、目的地国家以及签收时间。对于标注了 UTC 偏移量的行，Peek 会严格按该偏移量解析；未附偏移量的行则依次按扫描地点时钟、相邻行时钟或香港时间解析，再换算为你所在的时区显示，物流记录下方注明「时间按你所在的时区显示：…」。Peek 会略去始发地、`Estimated Delivery`（预计送达）、重量、件数、`Reference` 与签收人信息，因此不展示预计送达日期。

由于 Morning Global 不标明当地派送伙伴，即便单号恰好符合邮政校验码，Peek 也不会转交本国邮政，而是持续通过 Morning Global 网站跟进至签收。

Peek 对 Morning Global 最高每小时检查一次（包括派送当天），若单次请求失败则会等待 2 小时后再重试，手动刷新同样受此冷却限制。包裹处于「已生成面单」「已揽收」或「运输中」且 4 天没有新扫描时，Peek 会显示「自10月12日周一起没有物流更新」这样的提醒（日期为最后一条扫描的日期），并附上说明「包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 Morning Global 查询。」；超过三周后，说明改为「已超过三周没有新动态。如果还没收到，请联系卖家。Peek 会持续向 Morning Global 查询。」。

你可以按需选择“每次扫描”、“重要环节”（覆盖 `Customs Clearance Begins`、`Out for Delivery` 与 `Delivered`）或“仅派送当天”三种通知预设，也可以通过详情页的“向 Morning Global 寻求帮助”链接直接访问其网站联系页面。Peek 无法代你联系 Morning Global 或卖家。

## 关于 Morning Global 物流查询的常见问题

### Morning Global 是正规物流公司吗？

是的。Morning Global 创立于 1987 年，是香港快递集团 Morning Express Group 的国际业务部门，全球总部位于香港，其网站介绍其在 14 个国家和地区拥有自营团队，为品牌方、零售商及电商平台提供跨境物流服务。

### 如何追踪 Morning Global 包裹？

直接把卖家给你的单号粘贴到页面上方查询框，或前往 Morning Global 网站的 Trace & Track 页面查询。

### Morning Global 显示 `Handed Over for Delivery` 是什么意思？

表示包裹已完成目的国清关并移交给当地合作派送商，但此时还未装车派送；看到 `Out for Delivery` 才表示当天正在投递。

### 谁负责在当地派送 Morning Global 包裹？

由 Morning Global 在目的国的本地物流合作伙伴负责，其网站并未公开具体承运商名称。如需获知本地承运商及转单号，请向卖家或 Morning Global 客服查询。

### 如何联系 Morning Global 客服？

美国收件人可发邮件至 cs.mgus@morninglobal.com，英国发至 cs.mgeu@morninglobal.com，香港及澳门发至 cs.mghk@morninglobal.com，其他地区发至 cs.mgworldwide@morninglobal.com，并在邮件中附上运单号。涉及订单售后请优先联系购物店铺。

:::sources
- [Morning Global：Trace & Track](https://www.morninglobal.com/trace-track/) – 追踪页面、单次最多 20 单、Shipment Journey、Estimated Delivery、Reference、Signed By、查无记录提示及附单号联系客服
- [Morning Global 网站首页](https://www.morninglobal.com/) – 1987 年创立、亚洲起家、14 个自营国家与服务公告
- [Morning Global：About Us](https://www.morninglobal.com/about-us/) – Morning Express Group 国际部门、香港总部及服务品牌/平台定位
- [Morning Global：Services](https://www.morninglobal.com/services/) – MGIE 与 MGIP 渠道、覆盖 220+ 目的地、最高 30 公斤
- [Morning Global：Global Network](https://www.morninglobal.com/global-network/) – 英美自营网点、澳大利亚合作网络及北美重点市场
- [Morning Global：Contact](https://www.morninglobal.com/contact/) – 各大区客服邮箱与电话、全球团队及 WhatsApp、在线表单与内地分支机构
- [Morning Global：Upload Clearance Documents](https://www.morninglobal.com/upload-clearance-documents/) – 清关证件与资料上传表单
- [Morning Global：Privacy Policy / Terms](https://www.morninglobal.com/privacy-policy/) – 派送失败界定、30 天报备/90 天补齐索赔期限及 50 美元赔付上限
- [Morning Global：EUR 2 Customs Handling Fee Notice](https://www.morninglobal.com/morning-global-service-update-introduction-of-eur-2-customs-handling-fee-for-eu-bound-b2c-shipments-from-1-november-2026/) – 自 2026 年 11 月 1 日起每条报关品目收取 2 欧元清关处理费及适用增值税
- [Morning Global：Extended Transit Times in Australia](https://www.morninglobal.com/morning-global-service-impact-notice-extended-transit-times-in-australia-due-to-industrial-action/) – 澳大利亚劳资纠纷致 MGIP 延误 2 至 3 个工作日
- [Morning Global：News](https://www.morninglobal.com/news/) – 服务影响与最新公告页
- [European Commission：EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 欧盟 3 欧元关税与 2 欧元处理费政策
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – BR…MG 单号与链接解析、状态阶段映射、时区规则、每小时轮询频率及 Peek 展示字段
:::
