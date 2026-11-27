---
title: 急速国际（JS Express）物流查询：JSE单号、状态与尾程
description: 查询急速国际（JS Express）跨境包裹：JSE…YQ单号格式、中英文轨迹与清关状态含义、欧美加澳尾程派送商、包裹异常排查及客服联系方式。
slug: jisu-guoji-js-express-wuliu-chaxun
published: 2026-11-23
updated: 2026-11-23
---

急速国际（JS Express，简称 JS EXP，运营主体为急创速供应链管理（苏州）有限公司）是一家总部位于江苏苏州的跨境电商物流服务商，自 2010 年在香港起步、2011 年设立苏州基地。网购订单由急速国际承运时，卖家通常会给出一个以 `JSE` 开头、以 `YQ` 结尾的物流单号——把它粘贴到上方查询框即可跟踪。急速国际负责中国集货、出口清关与干线运输，包裹抵达目的国后交给当地尾程快递派送：在其轨迹记录中常见美国由 USPS、GOFO 或 SpeedX 派送，加拿大由 [Straightship](carrier:straightship) 派送，英国由 Yodel 派送，德国由 DHL、iMile 或 J&T Express 派送，法国由 Colissimo 派送，西班牙由 CTT Express 派送，部分线路则由 [联邦快递（FedEx）](carrier:fedex) 负责尾程。

## 急速国际单号格式：怎么看、在哪里找

急速国际的标准运单号由 `JSE` + 2 位字母 + 10 位数字 + `YQ` 组成，共 17 位字符。下面以虚构数字说明：

| 单号（虚构） | 格式说明 |
| --- | --- |
| `JSEKP4829173605YQ` | `JSE` 加 2 位字母、10 位数字和 `YQ`：急速国际的标准运单号。 |
| `JSEKP482917360YQ` | 中间只有 9 位数字：不符合急速国际格式，请检查是否漏输了一位数字。 |
| `JXCKP4829173605YQ` | 同样以 `YQ` 结尾，但开头是 `JXC`：这是驹隙物流（Juxi Express）的单号，不是急速国际。 |

不少中国跨境专线货代都采用「3 位公司前缀 + 2 位字母 + 10 位数字 + `YQ`」的结构，例如 `FEI`（17FEIA）、`WNB`（WanbExpress）、`SFC`（三态速递）和 `CJP`（CJ Packet），其中 `JSE` 专属于急速国际。在 Peek 里粘贴时无需在意空格和大小写；Peek 还会自动把第二位字母处误输的数字 `0` 纠正为字母 `O`，把 10 位数字里误输的字母 `O` 纠正为数字 `0`。注意急速国际官网查件必须输入完整单号，截断输入查不到结果。

**在哪里找单号。** 发货后可在商家的发货邮件或订单详情页找到单号。[急速国际官网简介](https://www.js-exp.com/index.php?page=6) 提到其与 Amazon、Walmart、Shopify、eBay、AliExpress、Wish 和 Shopee 等跨境平台合作密切。如果找不到单号，可参考 [如何找到你的快递单号](guide:find-tracking-number) 和 [快递单号格式指南](guide:tracking-number-formats)。

**没有可分享的单件直达链接。** 急速国际的查件页面需要手动填表单提交，无法靠链接直接打开某一个包裹，请直接复制单号查询。其 [英文查件页 Express Query](https://www.js-exp.com/EN/index.php?page=19) 可直接出结果，而 [中文快件查询页](https://www.js-exp.com/index.php?page=19) 查询前会先要求输入图形验证码。

## 急速国际物流状态是什么意思

急速国际查件页按时间倒序排列记录，每条包含时间、地点以及主要为英文的状态说明。按包裹实际流转顺序，常见状态如下：

| 急速国际原文 | 对你的实际含义 | Peek 显示阶段 |
| --- | --- | --- |
| `Electronic notification of order creation received.` | 卖家已向急速国际发送电子建单信息，急速国际尚未收到包裹。 | 「已生成面单」 |
| `PRE-ADVICE` | 合作方已收到包裹预报信息，但包裹还未入急速国际仓库。 | 维持上一阶段不变 |
| `Item has been collected` | 急速国际中国仓库已揽收包裹，这是第一条真实扫描。 | 「已揽收」 |
| `Item inbound in sorting center.`、`Item outbound in sorting center.` | 包裹进入并离开急速国际在中国的分拣中心。 | 「运输中」 |
| `Domestic allocation has begun`、`Under domestic allocation` | 分拣后仍在急速国际中国仓库内进行国内分拨配载。 | 「运输中」 |
| `Waybill Generated for` | 目的国当地快递已提前生成尾程面单，此时包裹通常仍在急速国际中国仓库。 | 「已生成面单」（排在真实扫描之后时为「运输中」） |
| `Arrive at transit center` | 到达急速国际中国中转中心，介于分拣与离境之间。 | 「运输中」 |
| `Item departed from origin.`、`Arrive at international airport to abroad` | 已从始发地发运并到达中国出境国际机场。 | 「运输中」 |
| `Arrived at export center`、`Exports Center Sorting and Packing`、`Arrived at Origin Port` | 部分渠道会先经中国出口中心分拣打包，再抵达离境口岸。 | 「运输中」 |
| `Shipment arrived at facility and measured.`、`Depart from facility to service provider.` | 在中国操作站点完成称重量方，并移交给承运航线服务商。 | 「运输中」 |
| `Start on-site transportation and shipment` | 抵达中国机场后开始场站内转运和装运操作，处于出口放行之前。 | 「运输中」 |
| `Released From Export Customs` | 中国出口报关已放行（并非收件国进口清关）。 | 「运输中」 |
| `DEPART ORIGIN AIRPORT`、`Departure from the original airport`、`Item Flight departure`、`Initial landing`、`Connecting flights flight takeoff` | 国际航班已起飞，部分线路会先经停中转再搭乘联程航班。 | 「运输中」 |
| `ARRIVED AT DESTINATION AIRPORT`、`Arrival to the destination airport`、`Item Flight landing`、`NOA-Notification of arrival from GHA` | 航班已降落在目的国机场；NOA 是机场地面代理发出的到货通知。 | 「运输中」 |
| `The customs clearance agent is queuing up to pick up the goods` | 目的国清关代理正在机场货运站排队提货，尚未进入清关环节。 | 「运输中」 |
| `The customs clearance agent has picked up the goods` | 清关代理已从机场货运站提取货物，开始办理进口清关。 | 「清关中」 |
| `The goods have been picked up abroad` | 当地代理已在境外机场提货（并非卖家重新揽收）。 | 「运输中」 |
| `Customs clearance agent received the shipment in destnation country.` | 目的国清关代理已接货办理进口清关（`destnation` 为系统原拼写）。 | 「清关中」 |
| `Inbound to customs temporary warehouse` | 已进入海关监管临时仓库，等待进口清关放行。 | 「清关中」 |
| `Released From Import Customs`、`Released from customs: customs cleared.` | 目的国进口海关已放行。 | 「运输中」 |
| `Depart from the destination distribution center.` | 清关完成后，包裹已离开目的国分拨中心继续转运。 | 「运输中」 |
| `In transit, it's progressing through post network` | 正通过目的国邮政网络转运，尚未装车派送。 | 「运输中」 |
| `collection by overseas warehouse` | 急速国际在目的国的海外仓已接收该批清关包裹。 | 「运输中」 |
| `Load cargo overseas`、`Vehicles depart from` | 海外仓正在装车交寄尾程快递，随后货车从扫描地点出发。 | 「运输中」 |
| `Delivery to local courier`、`Last Mile Delivery` | 已移交给目的国本地快递商，但当地快递尚未扫入或上门——此时并未送达你手中。 | 「运输中」 |
| `Delivery in progress` | 部分渠道显示此句代表包裹已在本地快递站点内流转，尚未真正外出派送。 | 「运输中」 |
| `到达待取` | 急速国际自有中文状态，表示包裹已到自提点等待领取，但地点栏通常只写国家名。 | 「待取件」 |
| `The delivery address is incorrect. Please ask the recipient to contact their local area?` | 当地快递因地址不清或有误派送失败后的系统提示，要求联系当地网点。 | 「需要留意」 |

**各目的国尾程快递同步过来的轨迹。** 出现 `Delivery to local courier` 之后，轨迹通常会直接沿用当地快递的原文：

- **美国（USPS、SpeedX 与未署名伙伴）：** USPS 会出现 `Accepted at USPS Origin Facility` 或 `Accepted at USPS Facility`（代表 USPS 首次收件入网）、`Out for Delivery`（派送中）和 `Delivered, Front Door/Porch`（已签收）。SpeedX 的轨迹往往不带公司名，包括 `Processing at Transit Facility`、`In transit to destination facility`、`Arrival at Destination Facility` 和 `Processing at Designated Facility`，均归类为「运输中」。另一家未署名美国合作方会出现 `Arrived at Origin Hub`、`Departed Destination Hub` 和 `Arrived At Warehouse`（最后派送仓），也均属「运输中」。
- **加拿大（Straightship）：** 先出现 `Package is received at a Straightship station`，接着是当天外出派送的 `Package will be delivered before 9:00pm`（「派送中」），最后是带城市名的 `Delivered`。
- **德国（DHL、iMile、J&T Express Germany）：** DHL 的英文记录有 `The instruction data for this shipment have been provided by the sender to DHL electronically`（电子预报）和 `The shipment is in transit to DHL`（「运输中」），小件还可能出现德语 `Die Sendung wurde im Briefzentrum bearbeitet.`（邮局信件中心处理）。iMile 司机未完成当天路线会记作 `Route not completed`（「派送未成功」）。J&T Express Germany 的德语记录会把地点放在【】内：`【】【】Ihr Sendung wurde abgeholt.`（清关后接手揽收）、`【】Die Sendung hat【】verlassen. Weiterversand nach【】`（发往下一站）和 `【】Die Sendung ist bei【】 angekommen.`（到达中转或派送网点），均为「运输中」。
- **联邦快递、匈牙利与西班牙：** 由 [联邦快递（FedEx）](carrier:fedex) 派送的线路，往往在入库前就先出现 `Shipment information sent to FedEx`（「已生成面单」），随后是 `Arrived at FedEx location`、`Left FedEx origin facility` 和 `On the way`（「运输中」）。匈牙利渠道会出现 `Arrived to HU HUB`、`Linehaul Transit` 和 `Shipment arrived to sorting centre / Shipment in the sorting centre`（「运输中」）。西班牙 CTT Express 的西语记录 `Enviado` 表示已发出转运（「运输中」）。
- **全大写匿名尾程快递轨迹：** 部分专线会以全大写转发尾程扫描却不写快递名或国家：`SHIPMENT RECEIVED`、`PROCESSED AT HUB`、`SHIPMENT PROCESSED` 和 `DEPARTED FROM DEPOT` 都是中转处理（「运输中」）；`RECEIVED AT LOCAL DEPOT` 指到达当地派送站（并非自提点，Peek 会维持前一条状态）；`DELIVERY ATTEMPTED: 1ST / 2ND / 3RD DELIVERY ATTEMPT` 为派送未成功（第三次后通常要求收件人预约派送，改约后出现 `SHIPMENT REDIRECTED: ON CONSIGNEE'S REQUEST` 恢复「运输中」）；`DELIVERY EXCEPTION` 以及在 `ARRIVE AT THE WAREHOUSE` 之后出现的 `DELIVERY EXCEPTION, WAIT FOR INFORMATION CONFIRMATION` 表示包裹在站点受阻或等待收件信息确认，Peek 会显示「需要留意」——此时若 Peek 未显示尾程单号，请向卖家核实快递公司与需要补充的信息。

**并非实际扫描的电子记录。** `SHIPPING INFORMATION RECEIVED`、`SHIPMENT CREATED`、`Data Received`、USPS 的 `Shipping Label Created, USPS Awaiting Item`、`Package information is created in the system` 以及欧洲中转仓的 `Shipment Data Uploaded` / `Processed` 都是合作方提前录入的数据，可能比实件到达早好几天。此外，派送失败同时记录的 `Consignee Contacted` 仅表示快递联系过收件人，不会改变当前状态。

**时间顺序错乱的原因。** 急速国际原页面混用了多种时钟：中国境内用北京时间，机场和多数当地快递用当地时间，而加拿大 Straightship 用 UTC（比加拿大当地快好几个小时）。因此请以列表排列顺序为准，不要单看钟表时刻。

## 急速国际包裹在各国由谁派送

- **美国：** 轨迹常见 USPS、GOFO 或 SpeedX 派送。包裹交给 USPS 后，按照 [USPS 遗失邮件说明](https://www.usps.com/help/missing-mail.htm)，寄出满 7 天后即可提交 Missing Mail 寻件申请。
- **加拿大：** 部分包裹由加拿大本地快递 [Straightship](carrier:straightship) 派送，其扫描会同步在急速国际轨迹中。
- **欧洲主要国家（德、法、西等）：** [急速国际欧洲专线](https://www.js-exp.com/index.php?aid=35) 覆盖英国、法国、德国、意大利、西班牙、瑞士、丹麦等国（不含部分偏远邮编）。[国际专线页面](https://www.js-exp.com/index.php?catid=13) 介绍德国专线尾程由 DPD 等快递派送且超 98% 包裹在 8 个工作日内妥投，实际轨迹中也常见 DHL Paket、iMile 和 J&T Express Germany；法国常见 Colissimo，西班牙常见 CTT Express。
- **英国、爱尔兰、澳大利亚及其他地区：** 部分线路使用 [联邦快递（FedEx）](carrier:fedex)。英国线路常见 Yodel（其中 `DESPATCHED AND ON ROUTE TO YODEL` 和 `PARCEL PICKED UP` 在包裹还在中国仓库时就可能提前写入）。澳大利亚专线列明 4 公斤以下由 [澳洲邮政（Australia Post）](carrier:australia-post) 或 Fastway（已更名为 [Aramex Australia](carrier:aramex-au)）派送，4 公斤以上由 DPEX 派送。急速国际的数据接口有时包含尾程单号但不写快递商名称，Peek 会根据号段与收件国自动匹配（国际邮政 S10 单号也会自动询问目的国邮政）。

派送次数、通知卡和自提点保管期限全部执行当地尾程快递的规则，急速国际本身不公布统一标准。看到 `到达待取` 时因为只显示国家名，请通过尾程单号查询或询问卖家具体取件地点。

## 急速国际包裹延误、卡关或未收到怎么办

- **提示 `未获取到任何数据,请提供有效单号!` 或 `暂无轨迹数据`。** 前者说明系统查不到该单号（拼写错误或尚未建单），后者表示已有运单但暂无扫描记录。Peek 会继续查询；若满两天仍查不到且第三方服务也无记录，Peek 会提示「请检查运单号」，并建议核对单号或更换快递公司。请逐位核对单号并向卖家确认是否已发出。
- **一直停在 `Electronic notification of order creation received.`。** 急速国际还没揽收到实物，请问卖家何时交付货代。
- **卡在清关环节（`Inbound to customs temporary warehouse`）。** 部分线路会出现 `Start Custom clearance Process` 和 `customs clearance completed`（更早的 `Under customs clearance, please wait patiently.` 是中国离境机场出口报关，Peek 归为「运输中」）。急速国际的 [美国专线](https://www.js-exp.com/index.php?aid=27) 和 [欧洲专线](https://www.js-exp.com/index.php?aid=35) 均采用完税后交货（DDP）模式，由货代提前向商家收取税费，通常不应再向收件人另收关税；如遇快递催缴，付款前先找卖家核实。根据 [爱尔兰税务局欧盟统一处理费公告](https://www.revenue.ie/en/corporate/press-office/press-releases/2026/pr-092926-handling-fee.aspx)，欧盟自 2026 年 11 月 1 日起按每类商品征收 2 欧元统一处理费，由卖家或派送企业缴纳并可能转嫁。更多请看 [包裹海关扣留与清关指南](guide:customs)、[物流为什么停止更新](guide:tracking-not-updating) 和 [中国直邮包裹跟踪指南](guide:tracking-from-china)。
- **停在 `Delivery to local courier` 后没下文。** 包裹已移交当地尾程快递，请用 Peek 提取出的尾程单号直接追踪当地快递，若未显示则向卖家索取尾程快递商与单号。
- **显示已签收但没拿到包裹。** 先联系当地尾程快递（例如美国联系 USPS），再联系卖家。急速国际美国专线说明中提到，对尾程显示已签收但收件人称未收到的包裹，急速国际只协助核查、不作赔付，最终处理取决于卖家售后政策。见 [显示已签收但未收到](guide:delivered-not-received)。
- **丢失、破损或退件。** 卖家才是急速国际的签约客户。根据 [急速国际投保理赔规则](https://www.js-exp.com/index.php?page=29)，索赔须由客户在急速国际收件后 30 天内书面提交，因此收件人应尽快找卖家或平台处理退款/补发。因无人签收、拒收、地址错误或清关问题无法派送的包裹，只有寄件人付费申请才会退回，否则可能被销毁；美国专线明确说明退件既不退回中国也不二次重派。部分线路出现 `Refused` 后接 `Returned` 表示包裹被拒收后回到当地分拣中心、尚未退回卖家，Peek 会标记为「需要留意」。参见 [包裹丢失怎么办](guide:lost-parcel) 和 [包裹退回寄件人](guide:returned-to-sender)。

## 急速国际客服电话与联系方式

根据 [急速国际中文联系我们页面](https://www.js-exp.com/index.php?page=35)：

- **客服热线**：`400-0353-518`（列作服务热线，同时也列作国际查询和国内查询电话；页面未公布服务时间，也未说明海外能否直接拨通）。
- **企业 QQ 与微信**：企业 QQ `4000353518`，微信公众号 `js-express`。
- **投诉入口**：[英文投诉表单页](https://www.js-exp.com/EN/index.php?page=32) 以及同一条 400 热线。
- **公司网点**：总部和主仓位于江苏苏州，在国内多地设有仓库，并在香港及美国加州 Walnut 设有分支。

理赔必须由发货卖家（急速国际的客户）提交。咨询订单与退款请联系卖家，咨询具体派送时间与改址请联系当地尾程快递。

## 用 Peek 跟踪急速国际包裹

[Peek](/) 会通过 `JSE` + 2 位字母 + 10 位数字 + `YQ` 的唯一规则自动识别急速国际（例如虚构单号 `JSEKP4829173605YQ`），确认记录后显示「JS Express 有这个包裹的记录。」。如果手里只有当地尾程快递单号，直接粘贴尾程单号即可——Peek 跟踪 4,500 多家快递公司。需要手动调整时，点击添加页的「更换」或详情页的「更换快递公司」并搜索 JS Express 或「急速国际」；详情页也提供「向 JS Express 寻求帮助」链接，直达急速国际的英文联系页面。

Peek 读取急速国际英文查件页的轨迹，保留每条扫描的地点并补充原页面未展示的目的国信息，把 `到达待取` 清晰地归类为「待取件」，不读取派送照片和商家订单号。预计送达日期按急速国际给出的日期显示（只显示日期）。所有非扫描的电子预报行不会错误地改动包裹阶段（「已生成面单」「已揽收」「运输中」「清关中」「派送中」「派送未成功」「待取件」「已签收」「需要留意」等）。

时间方面，Peek 将中国节点按北京时间解析，Straightship 按 UTC 解析，其余节点结合地点或目的国推算时区，最终统一换算成你设备的本地时间按最新优先展示。

当急速国际返回的尾程单号能唯一匹配收件国当地快递（例如美国的 USPS 条码或加拿大的 Straightship 单号）时，Peek 会显示当地派送商与尾程单号并同时发起查询（国际邮政单号则询问收件国邮政）。只要当地快递出现了不早于急速国际最新时间的扫描，Peek 就会无缝接力切换到当地快递继续跟踪，同时完整保留之前的急速国际轨迹和两个单号。

Peek 只在你打开这个包裹时，或在派送当天且开启了通知时，最快每 10 分钟检查一次，其余时间通常每小时一次（不登录账户跟踪、一小时内没有打开且未开启通知时为每 3 小时一次）。包裹进入「派送中」的当天，从快递当地时间早上 6 点起最快每 2 分钟检查一次（需要打开浏览器查询时每 5 分钟一次）。连续两天没有新扫描（处于「已生成面单」或「已揽收」时为 12 小时）后改为每小时一次。如果已切换给尾程快递，则按该快递自己的节奏查询。静默超过四天时 Peek 会显示「包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 JS Express 查询。」，超过三周无动静则显示「已超过三周没有新动态。如果还没收到，请联系卖家。Peek 会持续向 JS Express 查询。」。

通知范围可选「每次扫描」「重要环节」或「仅派送当天」。Peek 仅负责监控轨迹，不能替你联系急速国际或卖家。

## 关于急速国际物流查询的常见问题

### 急速国际（JS Express）是什么快递？

急速国际是急创速供应链管理（苏州）有限公司运营的跨境电商物流服务商，2010 年在香港创立、2011 年落户苏州，专门把中国跨境网店的包裹运往欧美加澳等国家并交由当地快递派送。

### 在哪里可以查询急速国际包裹？

可以直接粘贴在页面上方的 Peek 查询框里，也可以访问 [急速国际英文查件页](https://www.js-exp.com/EN/index.php?page=19)（免验证码）或 [中文查件页](https://www.js-exp.com/index.php?page=19)（需输入图片验证码）。

### 急速国际提示「未获取到任何数据」是什么意思？

表示系统当前查不到这个运单号：可能是单号少抄或抄错了字符，也可能是卖家刚下单、急速国际还没有入库扫描。请向卖家核对完整 17 位 `JSE…YQ` 单号。

### 急速国际包裹在收件国由哪家快递派送？

由目的国当地快递商负责最后一公里：美国常见 USPS、GOFO 或 SpeedX，加拿大常见 [Straightship](carrier:straightship)，英国常见 Yodel，德国常见 DHL、iMile 或 J&T Express，法国常见 Colissimo，西班牙常见 CTT Express，部分专线为 [联邦快递（FedEx）](carrier:fedex)。其他国家请直接询问卖家。

### 急速国际包裹丢了应该联系谁？

应优先联系你下单的卖家或购物平台：急速国际只受理签约寄件客户在 30 天内提交的书面理赔申请。

:::sources
- [急速国际：官网首页](https://www.js-exp.com/) – 运营公司主体与跨境物流业务
- [急速国际：企业介绍](https://www.js-exp.com/index.php?page=6) – 2011 年苏州基地、分支网点及合作跨境电商平台
- [急速国际：快件查询（中文）](https://www.js-exp.com/index.php?page=19) – 中文查询表单与验证码要求
- [急速国际：Express Query（英文）](https://www.js-exp.com/EN/index.php?page=19) – 免验证码英文查询
- [急速国际：投保理赔](https://www.js-exp.com/index.php?page=29) – 30 天理赔期限及无法派送退件处理
- [急速国际：Complaint（英文投诉页）](https://www.js-exp.com/EN/index.php?page=32) – 在线投诉表单与 400 热线
- [急速国际：联系我们](https://www.js-exp.com/index.php?page=35) – 400-0353-518 热线、企业 QQ、微信公众号及海内外网点
- [急速国际：国际专线](https://www.js-exp.com/index.php?catid=13) – 德国专线时效与尾程、澳大利亚专线合作快递
- [急速国际：急速美国专线](https://www.js-exp.com/index.php?aid=27) – DDP 完税模式、退件不退回也不重派及已签收未收到核查规则
- [急速国际：急速欧洲专线](https://www.js-exp.com/index.php?aid=35) – 覆盖欧洲国家、DDP 模式与偏远邮编说明
- [Aramex Australia：关于我们（英文）](https://www.aramex.com.au/why-aramex/about-us/) – Fastway 更名为 Aramex Australia
- [Revenue（爱尔兰税务局）：欧盟统一处理费新闻稿（英文）](https://www.revenue.ie/en/corporate/press-office/press-releases/2026/pr-092926-handling-fee.aspx) – 2026 年 11 月 1 日起每类商品 2 欧元处理费
- [USPS：Missing Mail 遗失邮件说明（英文）](https://www.usps.com/help/missing-mail.htm) – 寄出满 7 天可提交寻件
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – 单号规则与 O/0 容错、中英文及多国尾程状态映射、时区换算、匿名尾程轨迹解析、浏览器回退及自动接力切换尾程快递机制
:::
