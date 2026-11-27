---
title: 拉脱维亚邮政（Latvijas Pasts）查询：单号、状态与自提柜
description: 查询拉脱维亚邮政（Latvijas Pasts）包裹：LV 国际单号格式、英文物流状态、自提柜取件期限、改投费用、理赔与客服联系方式。
slug: latvijas-pasts-kuaidi-chaxun
published: 2026-11-25
updated: 2026-11-25
---

拉脱维亚邮政（Latvijas Pasts / Latvian Post）是拉脱维亚国有国家邮政机构，拥有全国规模最大的包裹自提柜网络。你可以在默认拉脱维亚语且带英文切换的 [mans.pasts.lv/track](https://mans.pasts.lv/track) 查件，或直接把单号粘贴到上方查询框。单号来自商家或寄件人的发货通知，或在派送前由快递员通过短信发送。

## 拉脱维亚邮政单号格式

通过拉脱维亚邮政交寄的邮件使用 13 位[国际邮政单号](guide:tracking-number-formats)（英文）：前两位字母、9 位数字加 `LV`。前两位字母标明了邮寄服务类别：

| 开头字母（虚构示例） | 说明 |
| --- | --- |
| `RR482917367LV` | 挂号邮件（Registered）：收件时需签字确认。 |
| `LA482917367LV` | 可追踪邮件（Traceable）：各环节均记录轨迹，直接投入信箱无需签收。 |
| `CA482917367LV` | 包裹（Parcel）：你会收到通知前往邮局领取。 |
| `EE482917367LV` | 国际特快专递（EMS）。 |

平邮（Simple）或经济类（Economy）邮件全程不登记，因此无法追踪，破损也不予赔付。如果手头没有单号，包裹可能采用了这类邮寄方式；参见[如何查找快递单号](guide:find-tracking-number)（英文）。

查找位置：快递员短信会列出运单号、预计送达时段及改投入口；自提柜短信则会写明柜机地址、最晚取件日期和开柜取件码。

其他国家代码代表对应国家邮政：`…LT` 为[立陶宛邮政](carrier:lietuvos-pastas)，`…EE` 为 [Omniva](carrier:omniva)。境外寄入的邮件保留原寄国单号，一旦拉脱维亚邮政收到邮件，mans.pasts.lv/track 也可直接查询；境外扫描节点会显示国家名而非拉脱维亚网点。

## 拉脱维亚邮政物流状态含义

查件页会逐条列出每个环节的日期、时间和对应的拉脱维亚邮局网点；列表上方设有五段进度条，从 `Shipment created` 到 `Delivered to recipient`。以下是英文文案，大致按出现顺序排列：

| 拉脱维亚邮政英文状态 | 对你的含义 |
| --- | --- |
| `Electronic data about shipment received` | [寄件人的电子数据](guide:tracking-statuses)（英文）已传至拉脱维亚邮政，尚未交寄实物。 |
| `Item information received` | 寄件人已登记邮件信息，拉脱维亚邮政同样尚未收到实物。 |
| `Item received from sender` | 拉脱维亚邮政已从寄件人处揽收邮件。 |
| `Item posted / received from customer` | 已在邮局交寄；境外寄入件在原寄国邮政的交寄扫描也显示该文案。 |
| `Item sent to the processing facility` | 已离开交寄邮局，发往分拣处理中心。 |
| `Item arrived at office of exchange` | 已抵达里加或境外的国际互换局。 |
| `Item sent to the Customs` | 邮件离开寄出国前在互换局送交海关。 |
| `Item returned from Customs` | 海关已放行，并非退回寄件人。 |
| `Item sent abroad` | 已离开互换局发往目的地国家。 |
| `Item sent abroad (EDI-received)` | 境外邮政已发往拉脱维亚，拉脱维亚邮政通过对方电子数据获知该进度。 |
| `Item accepted for temporary storage` | 境外入境件已进入海关监管临时仓库等待清关，并非从寄件人新揽收。 |
| `Electonic notification (SMS) of customs clearance sent to recipient` | 已向你发送清关短信通知（原文拼写如此），邮件仍处于临时仓储中。 |
| `Customs clearance completed, item to be delivered to recipient` | 已完成清关，将继续发往负责派送的网点。 |
| `Item arrived at processing facility`、`Item arrived at sorting centre` | 已抵达分拣处理中心。 |
| `Item sent to next processing facility` | 正发往下一级分拣中心。 |
| `Item sent to delivery facility` | 正发往负责投递或自提的末端网点。 |
| `Item received at delivery office` | 已抵达末端网点，仍可能从此处外出派送。 |
| `Item redirected` | 已改投至其他地点，如另一个自提点（见下文“改投”）。 |
| `Item address re-labelled` | 已重新贴标并按新地址继续寄送。 |
| `Item handed over for delivery` | 正在派送中。 |
| `Unsuccessful delivery attempt` | 派送未成功（见下文）。但若与 `Item address re-labelled` 精确到同一秒出现，则代表改投记录，邮件正在发往新地点，Peek 会显示为“运输中”而非“派送未成功”。 |
| `Notification - pick-up invitation printed`、`Electronic notification (SMS) - pick-up invitation sent to recipient` | 待取件：邮局已打印取件通知单或向你发送取件短信。 |
| `Item arrived at pick-up point` | 境外邮政节点：邮件已到达境外自提点，仅显示国家名而不显示具体网点。 |
| `Item delivered` | 已签收送达；发往境外的邮件由目的国邮政妥投后也显示此状态。 |

`Item returned from Customs` 最容易让人担心：它表示海关查验结束、邮件已放行并继续流转，并不是退回寄件人。`Item awaiting next planned cycle` 表示在互换局等待下一班次发运。经由中转点发出的网店包裹还可能出现专用文案：`Parcel notify received` 与 `Parcel data received`（仅预报数据，未交寄）、`Parcel has arrived at transit location`（首次扫描）、`Parcel collected at transit location`、`Parcel sent to destination country`、`Parcel has arrived in last mile`（已进入目的国派送网络）以及 `Parcel has gone thought last mile customs`（系统原文拼写，表示已完成当地清关）。欧盟外入境包裹的关税与欧盟处理费详见[包裹卡在海关怎么办](guide:customs)（英文）。

若查件页提示 `No information received on the tracking of the postal item. Please, try again!`，请先核对单号或稍等片刻：邮件可能尚未扫描，境外件也需对方邮政回传数据后才显示。`Postal item number entered incorrectly. Please check it and enter it again.` 表示单号输入有误。若单号无误但多日查无记录，请向寄件人确认是否采用可追踪渠道：平邮和经济件永远不会有追踪记录。

## 拉脱维亚邮政自提柜、上门派送与派送未成功

**自提柜。** 包裹入柜后，你会收到含柜机地址、截止日期及 6 位取件码的短信（若寄件人提供了邮箱，也会同步发邮件）。包裹最多保管 7 天；第 3 天若仍未领取会重发取件码，并在临期前发送提醒。超 7 天未取将视为无法投递并退回寄件人；拥有更长保管期的邮件则会转送至最近的客户服务中心（Customer Service Location），在那里保管 30 天。如果一直没收到取件码，请联系寄件人通知拉脱维亚邮政：拉脱维亚邮政只在寄件人要求时才更正手机号。自提柜单件限重 30 公斤，尺寸上限 38 × 38 × 58 厘米。

**Unisend 自提柜。** 从拉脱维亚发往立陶宛或爱沙尼亚 Unisend 自提柜的包裹同样保管 7 天，取件码会通过短信和邮件发送（发往拉脱维亚、立陶宛或爱沙尼亚手机号），并在 48 小时后重发一次；超期无人领取将直接退回寄件人。

**上门快递。** 拉脱维亚邮政表示最多尝试派送三次。若某次失败且你未改投，快递员会留下通知单并再次尝试；若仍失败，包裹将转入暂存：你可前往主邮局（Main Post Office）自提，或致电客服另做安排，保管期限由寄件人设定。EMS 快递无人收件时会在信箱留下 “You have received a shipment” 通知单，请手持运单号拨打通知单上的电话预约重派。

**邮局或客户中心取件。** 你会收到写明网点的短信或纸质通知单。取件请携带身份证件（货值不超过 150 欧元的包裹可用驾照）。记住运单号即使没带纸质通知单也能取件，或拨打通知单上的电话申请付费改送上门（交件时付费）。他人代领需凭你的授权书及代领人护照或身份证。挂号件或保价件 5 天后会发第二次通知，自首次通知起满 30 天未领则退回寄件人。如果境外网购包裹面单上只印了你的部分姓名，除身份证件外还需携带证明订单归你所有的凭证。

**改投服务。** 如果寄件人填写了你的手机号，或你开通了短信提醒，短信内会附带链接，可将包裹改投至自提柜、客户服务中心、其他地址，或在覆盖区域选用免费的 “Postman at home” 服务。改投页面标明：改投自提柜免费，客户服务中心收费 1.89 欧元，快递改派地址收费 4.99 欧元（均含增值税）。已入柜或处于海关监管下的邮件无法改投；带签收证明的挂号/保价件，以及尺寸超过 38 × 38 × 58 厘米的货到付款件不能改投至自提柜。开通短信提醒时还可设置常用自提柜。

## 拉脱维亚邮政包裹丢失、破损或未到达

自提柜包裹丢失或破损的理赔必须由寄件人发起，因此网购订单请直接联系商家。理赔需列明内件清单与价值，并附收据、发票或付款证明以及破损照片。拉脱维亚邮政在一个月内审核理赔申请。

快递员送达挂号或保价邮件时，请当面查验内件以便签署记录。如果独自拆封后才发现破损，请拍照联系寄件人申请补发或退款；拉脱维亚邮政声明此种情形下其不承担赔付责任。

寄往境外却未到达的邮件，可由寄件人申请国际查单：交寄后一个月内提出需收费，满 30 天后免费。

物流停滞或入境时卡在海关？参见[物流长时间不更新怎么办](guide:tracking-not-updating)（英文）和[包裹卡在海关怎么办](guide:customs)（英文）。

## 拉脱维亚邮政客服与联系方式

以下渠道来自 pasts.lv：

- **客服热线：** +371 27008001（自提柜服务条款中另列出 +371 67008001）。
- **电子邮箱：** info@pasts.lv。
- **服务时间：** 周一至周五 8:00 至 19:00，周六 9:00 至 17:00，周日休息。
- **问题反馈：** 通过 [mans.pasts.lv/complaint](https://mans.pasts.lv/complaint) 表单提交，客服工作时间内受理。

> 拉脱维亚邮政提醒警惕仿冒短信和邮件，骗子常以确认运单/地址或前往仿冒网站缴关税为由行骗。官方邮件一律以 @pasts.lv 结尾，其网站为 www.pasts.lv，官方绝不会通过短信或邮件索要银行或银行卡信息，也不会要求进行 Smart-ID 确认。

## 使用 Peek 追踪拉脱维亚邮政包裹

对于以 C、E、L 或 R 开头、以 `LV` 结尾且校验位有效的国际邮政单号，[Peek](/) 会立刻识别为拉脱维亚邮政。对于其他以 `LV` 结尾的有效单号（例如 `VV482917367LV`），Peek 会同时向拉脱维亚邮政与 [Chronopost](carrier:chronopost) 查询，有记录时显示“Latvijas Pasts 有这个包裹的记录。”，暂未查到时则提示“…… 暂未找到。添加后 Peek 会继续查询。”。校验位错误时不会向拉脱维亚邮政查询，手动选择也没有用（Peek 会提示“不使用这种格式的单号”），请与寄件人核对单号。内含单号的 mans.pasts.lv/track 链接，Peek 总会认定为拉脱维亚邮政；在快递列表搜索 “Latvian Post” 也能找到它。如果拉脱维亚邮政暂时查不到这个单号，记在它名下的包裹会显示“等待 Latvijas Pasts 更新”，Peek 会继续查询；两天后若拉脱维亚邮政仍不认识该单号且无其他服务返回结果，Peek 会显示“两天过去了，快递公司仍查不到这个单号。请检查单号、更换快递公司，或删除这个包裹。”，此后改为每天查一次，添加满一周后改为每周查一次。

从国外寄来的邮件会先由原寄国邮政追踪；当原寄国邮政标明发往拉脱维亚（或未标明目的地而你身处拉脱维亚）时，Peek 会用同一单号同时询问拉脱维亚邮政，拉脱维亚邮政有了新的扫描后，Peek 就转到它继续跟踪，之前的记录保留。若想立即在拉脱维亚邮政名下追踪，可在添加时点击“更换”选择 “Latvijas Pasts”，或对已添加包裹使用“更换快递公司”。

拉脱维亚邮政的时间不带时区信息：Peek 按扫描地点的当地时钟读取（拉脱维亚邮局按拉脱维亚时间）并列出拉脱维亚邮政自己的记录。状态保留拉脱维亚邮政的英文原文，包括 `Item delivered`。Peek 可显示包裹待取的邮局网点并在状态变化时推送提醒，但不提供预计送达日期或重量。在你打开包裹页面期间，或在派送当天开启提醒时，Peek 最高每 10 分钟检查一次；在拉脱维亚邮政交付派送的当天，只要拉脱维亚邮政自己的查件系统正常响应，自该扫描地点的当地时间早上 6 点起（拉脱维亚境内按拉脱维亚时间）每 2 分钟检查一次；其余时间每小时检查一次。若停留在“已生成面单”或“已揽收”满 12 小时，或连续两天没有新动态，则不论何种方式均改为每小时检查一次；停在“已生成面单”满七天则每 6 小时一次。当拉脱维亚邮政的查件系统没有响应、Peek 只通过物流查询服务跟踪时，Peek 每小时检查一次，超过一天无新动态后每 3 小时一次，在派送当天打开页面或开启提醒时最高每 15 分钟检查一次。若由其他承运商接手派送，Peek 会按实际派送方的节奏查询。

当包裹在“已生成面单”、“已揽收”或“运输中”状态下连续四天没有新扫描时，Peek 会标出最新扫描日期（如“自10月12日周一起没有物流更新”），详情页提示“包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 Latvijas Pasts 查询。”并附上指向拉脱维亚邮政客服页面的“向 Latvijas Pasts 寻求帮助”链接。超过三周无新扫描时，提示变为“已超过三周没有新动态。如果还没收到，请联系卖家。Peek 会持续向 Latvijas Pasts 查询。”。

Peek 可以按“每次扫描”、“重要环节”或“仅派送当天”发送提醒。

## 拉脱维亚邮政查询常见问题

### Latvijas Pasts 是什么？

拉脱维亚国有国家邮政公司，在拉脱维亚境内外提供邮政、物流与金融服务，其网站为 pasts.lv。

### Unisend 是拉脱维亚邮政吗？

不是。Unisend 是立陶宛邮政（Lietuvos paštas）旗下的包裹品牌。两家邮政运营联合自提柜网络，因此拉脱维亚邮政客户可以将包裹寄往立陶宛和爱沙尼亚的 Unisend 自提柜。

### 可以追踪拉脱维亚邮政的国际邮件吗？

只要寄送时选择了可追踪服务就可以。mans.pasts.lv/track 也会显示境外邮政回传的扫描记录（标注国家名而非具体网点）。邮件到达国外后由目的国邮政派送；查件页上的 “International Tracking” 按钮会跳转至第三方网站。

### 从拉脱维亚寄出的普通平邮有单号吗？

不一定。平邮（Simple）和经济件（Economy）不进行登记，因此没有可追踪单号；挂号件、可追踪件、包裹和 EMS 才有。

:::sources
- [Latvijas Pasts: Customer support](https://pasts.lv/en/client-support) – 各服务开头字母、平邮不赔付、查件常见问题、国际查单一个月内收费、满 30 天免费、服务时间、热线与问题反馈
- [Latvijas Pasts: Contacts](https://pasts.lv/en/about-us/contacts) – 客服热线、邮箱与服务时间
- [Latvijas Pasts: Receive a parcel](https://pasts.lv/en/private-person/receive-parcel) – 各类收件方式与授权代领
- [Latvijas Pasts: Receive by courier](https://pasts.lv/en/private-person/receive-parcel/receive-by-courier) – 快递员短信、三次派送、暂存、主邮局与 Postman at home
- [Latvijas Pasts: Receive item in a parcel locker](https://pasts.lv/en/useful/receiving-items-private/receive-in-parcel-locker) – 自提柜短信、7 天保管期、提醒、30 公斤与 38 × 38 × 58 厘米上限及常用柜机
- [Latvijas Pasts: Delivery to address](https://pasts.lv/en/useful/receiving-items-private/delivery-to-address) – 5 天后二次通知、30 天退回、EMS 通知单与重派、境外网购姓名不全凭证
- [Latvijas Pasts: Receive at a Customer Centre](https://pasts.lv/en/private-person/receive-parcel/receive-at-customer-centre) – 通知单、证件要求、150 欧元以下可用驾照、无通知单取件与付费改送上门
- [Latvijas Pasts: Shipment redirection](https://pasts.lv/en/services/private-person/receiving/redirection-of-shipments) – 改投链接、可选方式、费用及限制条件
- [Latvijas Pasts: Lost or damaged parcel locker shipments](https://pasts.lv/en/services/private-person/shipment-management/lost-or-damaged-parcel-locker-shipments) – 寄件人理赔、内件清单与凭证、1 个月审核期
- [Latvijas Pasts: Damaged shipment](https://pasts.lv/en/useful/faulty-shipment) – 当面查验与找寄件人索赔
- [Latvijas Pasts: Pakomātu pakalpojumu noteikumi](https://pasts.lv/media/789/download/Pakomatu_noteikumi.pdf) – 6 位取件码、邮件通知、第 3 天重发、7 天自提柜及 30 天客户服务中心、+371 67008001、改号流程与立陶宛/爱沙尼亚 Unisend 柜机
- [Latvijas Pasts: Pasargā sevi no krāpniekiem](https://pasts.lv/pasarga-sevi-no-krapniekiem) – 防范假短信与假邮件警示
- [Latvijas Pasts: Mans pasts, track shipment](https://mans.pasts.lv/track?locale=en-GB) – 英文查件页、进度条、状态文案、未查到提示与 International Tracking 按钮
- [Latvijas Pasts: About us](https://pasts.lv/en/about-us/par-pastu) – 国有国家邮政及业务范围
- [Latvijas Pasts: Latvijas Pasts and UNISEND launch their joint parcel locker network](https://pasts.lv/en/news/latvijas-pasts-and-unisend-launch-their-joint-parcel-locker-network) – Unisend 隶属立陶宛邮政及联合自提柜网络
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Peek 读取和显示的信息、状态文案与含义、查件错号提示、直接识别与拒绝规则及境外件交接
:::
