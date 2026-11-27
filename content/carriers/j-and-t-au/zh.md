---
title: 极兔澳大利亚（J&T Express Australia）单号查询与客服
description: 查询极兔澳大利亚（J&T Express Australia）包裹：JTAU 16 位单号规则、英文轨迹状态、清关揽收与签收照片说明及客服 13 18 18。
slug: jt-express-australia-chaxun
published: 2026-11-24
updated: 2026-11-24
---

J&T Express Australia（极兔澳大利亚，*J&T Express Australia Pty Ltd*，位于新南威尔士州 Villawood）是极兔速递集团在澳大利亚的快递网络，与东南亚及中国的极兔隶属同一集团。它为电商平台、品牌方和零售商在澳大利亚境内派送包裹，包括海外订单完成清关后的澳洲末端派送。如果商家通过极兔澳大利亚发货，运单号会以 `JTAU` 开头：你可以直接粘贴到上方查询框中，或在 [J&T 澳大利亚官方查件页](https://www.jtexpress.au/tracking) 查询。运单号由发货商家随发货邮件或订单详情提供。

## 极兔澳大利亚单号格式：如何识别 JTAU 运单号

J&T 将其称为 tracking number（追踪单号）或 waybill number（运单号）。澳大利亚极兔运单号固定为 `JTAU` 后接 12 位数字，共 16 位字符，Peek 不识别它的其他单号形态（以下均为虚构示例）：

| 单号（虚构） | 归属与说明 |
| --- | --- |
| `JTAU482910573649` | `JTAU` 加 12 位数字：极兔澳大利亚（J&T Express Australia）包裹，Peek 会立刻识别。 |
| `JTAU48291057364` | 少了一位数字：不符合极兔澳大利亚格式，Peek 不会自动识别承运商，请核对是否漏输。若所有承运商均未返回记录，peektracker.com 会提示“这个单号开头像 J&T Express Australia 的运单号，但少了一个字符，可能输错了。请和发货消息里的单号核对一下。” |
| `JTUK482910573649` | `JTUK` 加 12 位数字：属于极兔英国（J&T Express UK），并非澳大利亚网络。 |
| `JTDE482910573649` | `JTDE` 加 12 位数字：属于极兔德国（J&T Express Germany）。 |
| `JTE482910573649` | `JTE` 加 12 位数字：属于极兔中东（J&T Express Middle East，覆盖沙特、阿联酋和科威特）。 |
| `JT4829105736491` | `JT` 加 13 位数字：属于东南亚极兔或[极兔速递中国（J&T Express China）](carrier:j-and-t-cn)。 |

极兔在各国的网络各自使用独立查询系统和单号前缀：只有 `JTAU` 属于澳大利亚网络。在 Peek 中输入空格、连字符或小写字母不影响识别，`jtau 4829 1057 3649` 与第一行等效。Peek 的承运商目录中也收录了 J&T Express (AU)、J&T Express AU、J&T Australia 和 JT Express Australia 等别名。

**在哪里找到单号。** 可在商家发货邮件、网店或电商平台订单详情页，以及派送通知短信中找到。极兔的收件人姓名、地址和联系方式均来自发件方或购物平台，找不到单号时请联系商家，或参阅[如何查找快递单号](guide:find-tracking-number)（英文）。

**查不到记录。** 极兔网站对尚未录入的单号会显示 `No tracking records yet`。若 Peek 一直显示“等待 J&T Express Australia 更新”，请对照发货邮件逐位核对全部 16 个字符，再联系商家确认。

## 极兔澳大利亚（J&T Express Australia）物流状态含义

极兔澳大利亚网站为每条扫描提供时间戳与一句英文描述，不包含具体网点或城镇名称。Peek 读取英文原文，以下是 Peek 在真实极兔澳大利亚包裹中观测到的常见状态，按正常流转顺序排列：

| J&T 英文状态 | 对你的含义 | Peek 显示 |
| --- | --- | --- |
| `The parcel has been picked up from customs clearance.` | 跨境包裹已完成清关，极兔已从清关代理处提货。这是进口包裹在极兔上的首条扫描。 | “已揽收” |
| `The parcel has been successfully picked up and is being prepared for transportation.` | 极兔已揽收包裹，正在安排转运。 | “已揽收” |
| `The parcel arrives at the distribution center and is prepared for transport to the next destination.` | 包裹已抵达极兔分拨中心，准备发往下一站。 | “运输中” |
| `The parcel is dispatched from the transit center to the next destination` | 包裹已离开极兔中转中心发往下一节点。 | “运输中” |
| `Your parcel is out for delivery.` | 快递员正在派送途中。 | “派送中” |
| `The building was closed when we arrived. We'll try again.` | 快递员到达时大楼或商铺已关闭，极兔会再次尝试派送。 | “派送未成功” |
| `We couldn't deliver - the address details appear to be incorrect. Please update your delivery address.` | 地址信息有误导致无法投递，极兔要求更正收货地址。 | “派送未成功” |
| `Your parcel has been delivered.` | 包裹已送达签收。 | “已签收” |

注意，`The parcel has been picked up from customs clearance.` 听起来容易误以为被海关扣留，实则恰恰相反：说明海关已经放行，包裹已由极兔提走。更多海外进口说明请参阅[包裹被海关扣留怎么办](guide:customs)（英文）。

未列入上表的英文扫描将按 Peek 通用规则归类；Peek 暂未识别的文案会保留极兔原文并记作“运输中”，但如果包裹已进入“派送中”或之后阶段，则会维持当前阶段不变。

极兔网站的部分扫描句尾会附带客服电话或拨打提示，Peek 会自动剔除这些内容，也会移除方括号内容、电话号码以及快递员、司机和寄件人姓名。添加包裹的本人可看到签收人姓名，而分享链接仅显示“已签收”而不公开姓名。由于极兔扫描不附带地点信息，极兔网站与 Peek 都无法显示包裹当前所在的州或仓库。

## 极兔澳大利亚派送规则与签收照片

极兔在澳大利亚各地招募本地派送合作伙伴负责末端投递与揽收。在 Peek 观测到的实际包裹中，均由极兔派送员直接派送或报告投递失败原因，未见转交给其他澳大利亚快递商的情况：极兔网站全程追踪直至送达你家门口。

**来自中国跨境专线的包裹。** 跨境专线抵达澳大利亚后可能交由极兔派送。例如中国物流商[燕文物流（Yanwen）](carrier:yanwen)会在抵达港口批量交接给极兔，随后燕文轨迹里会同步出现极兔的英文扫描（部分带极兔客服电话）。若卖家给的是燕文单号，继续在 Peek 追踪该燕文单号即可：Peek 会将极兔扫描接入历史轨迹中、去除客服号码，并将极兔提货记录显示为“运输中”（因为燕文早已揽收）。

**正在派送。** 出现 `Your parcel is out for delivery.` 表示当天在派送路线上。极兔网站不提供具体派送时段或预计送达日，Peek 也无法显示。

**大楼或商铺关闭。** `The building was closed when we arrived. We'll try again.` 表示极兔会安排再次派送。在 Peek 观测到的实单中，这条记录紧随“派送中”出现，之后包裹会再次离开中转中心。如果白天的收件楼宇经常无人值守，可咨询商家能否更换收货地址。

**地址信息异常。** 出现 `We couldn't deliver - the address details appear to be incorrect. Please update your delivery address.` 后，请立即核对订单地址是否遗漏单元号或邮编，并第一时间通知商家更正。详见[如何修改收货地址](guide:change-delivery-address)（英文）与[错过派送怎么办](guide:missed-delivery)（英文）。

**派送次数、安全放置与自提点。** 极兔澳大利亚网站未公布尝试派送次数、是否可放安全位置以及无法派送后的保管期限。在 Peek 已知的所有极兔澳大利亚扫描文案中，均未见投递至自提点或快递柜的情况。如有需要请咨询卖家或查看派送短信。

**签收证明（Proof of delivery）。** 根据极兔澳大利亚隐私声明，送达或尝试派送时会记录以下信息：

- 司机在极兔 App 中记录每次签收或派送尝试，可能包含包裹放置位置照片、日期时间、司机设备定位、需签名时的电子签名及派送备注。
- 极兔要求司机拍摄包裹放置处照片，并尽量避免拍到人物、车牌或室内隐私画面。
- 你会通过短信或其他电子通知收到签收证明链接；持有该链接的任何人均可查看，但不会显示司机姓名。
- 如果你不希望拍照，极兔隐私声明注明可通过隐私邮箱 `dataprotection@jtexpress.au` 提供派送说明。

## 极兔澳大利亚包裹不动、遗失或显示已签收但未收到

发货承运商由网店选定，因此网购订单出现问题时请首先联系卖家。

**多日无新扫描。** 当处于“已揽收”或“运输中”的包裹满四天没有新动态时，Peek 会标出最新扫描日期（“自10月12日周一起没有物流更新”）并附上“向 J&T Express Australia 寻求帮助”链接。如果之后依然毫无进展，请携带运单号联系商家或极兔客服。详见[为什么物流停止更新](guide:tracking-not-updating)（英文）。

**迟迟查不到轨迹。** 极兔网站尚未查到的单号在 Peek 中显示为“等待 J&T Express Australia 更新”。商家往往在交货前就生成单号。如果两天后极兔仍查不到该单号且无其他追踪服务返回记录，Peek 会提示“两天过去了，快递公司仍查不到这个单号。请检查单号、更换快递公司，或删除这个包裹。”，此后改为每天查询一次，添加满一周后改为每周查询一次。请与商家核对单号并确认是否已实际寄出。

**显示已签收却未收到。** 先打开极兔短信里的签收证明链接核对照片中的放置位置，再检查门口周边、询问同住家人与邻居。若仍找不到，请携带运单号同时联系商家和极兔：极兔隐私声明指出其会使用签收记录调查丢件、破损与误投案件。同时向商家申请退款或补发。详见[显示已签收但未收到](guide:delivered-not-received)（英文）。

**丢失或破损。** 极兔澳大利亚网站未向个人收件人提供直接理赔通道。商家或平台同样会收到签收证明，请携带运单号要求卖家退款或重发。详见[包裹丢失](guide:lost-parcel)（英文）与[包裹破损](guide:damaged-parcel)（英文）。

## 极兔澳大利亚官方客服与联系方式

以下信息发布于 [J&T 澳大利亚联系我们页面](https://www.jtexpress.au/contact)：

- **客服电话：** `13 18 18`，服务时间 `08:00–20:00`（页面未标注具体星期）。
- **客服邮箱：** `service@jtexpress.au`。

该页面另列出商务合作邮箱（`business@jtexpress.au`）与派送合作伙伴招募邮箱（`partner@jtexpress.au`）。极兔隐私声明注明其呼叫中心与客户支持服务商位于马来西亚和菲律宾。

网购订单请同时通知卖家，电商平台订单请直接在订单页发起客服工单。包裹停滞时，Peek 页面中的“向 J&T Express Australia 寻求帮助”会直接打开极兔联系页面。

由于极兔确实会发短信推送签收照片链接，收到极兔相关短信未必是诈骗；但如果短信要求付费或索要银行卡等个人信息，请先对照[快递诈骗短信识别指南](guide:delivery-scam-texts)（英文）核实后再决定是否点击。

## 使用 Peek 追踪极兔澳大利亚包裹

在 [Peek](/) 中输入 `JTAU` 单号（不限大小写、空格）时，Peek 会立刻识别为 J&T Express Australia（“识别出的快递公司”），仅凭单号即可直连极兔追踪，无需邮编或账号。粘贴带有 `?q=` 和单号的 `www.jtexpress.au/tracking` 链接同样有效；若链接中是其他格式单号，Peek 会像单独粘贴该单号一样识别。如果你的单号不是 `JTAU` 开头，照常粘贴即可——Peek 跟踪 4,500 多家快递公司。`JT` 加 13 位数字的单号属于东南亚或中国极兔：Peek 不会仅凭单号询问极兔，而是询问聚合追踪服务（“正在查询物流服务…”）。对于非 `JTAU` 格式强行点击“更换”选择极兔澳大利亚没有效果，Peek 会提示其“不使用这种格式的单号”。

Peek 会展示当前阶段、精确到分钟的每条扫描时间及签收时间。由于极兔每条记录均带精确时间戳，Peek 会自动转换为你设备的本地时区；在 peektracker.com 及 iPhone、Android 客户端中，轨迹底部会注明“时间按你所在的时区显示：”及对应时区名。扫描文案保持极兔英文原文。因极兔不提供地点、预计送达日和重量，Peek 不会显示地图，也不会展示极兔签收照片（请点击极兔短信中的链接查看），同时会剔除网点联系信息；超长历史最多保留最新 100 条扫描。包裹页的“打开 J&T Express Australia 官网”按钮可直接用该单号打开极兔查询页。

在极兔首次扫描前，Peek 会持续查询并显示“等待 J&T Express Australia 更新”（若两天仍无记录则提示核对单号）。当极兔网站暂时无法连接或查无记录时，Peek 会向第三方聚合追踪服务查询并按其原文展示。

在打开包裹页面或预计派送当天开启提醒时，只要极兔网站正常响应，Peek 最高每 10 分钟检查一次，在极兔扫出派送中当天自当地时间早上 6 点起最高每 2 分钟检查一次（若通过聚合追踪服务跟进，在派送当天打开页面或开启提醒时最高每 15 分钟一次）；其余时间每小时检查一次。若连续两天无新扫描（或停留在“已揽收”满 12 小时），则转为每小时检查一次。你可以选择“每次扫描”、“重要环节”或“仅派送当天”通知预设。Peek 无法代你联系极兔或商家，也不能更改收件地址或预约重新派送。

## 关于极兔澳大利亚追踪的常见问题

### 极兔澳大利亚（J&T Express Australia）是正规快递公司吗？

是的。澳大利亚商业注册局（ABR）信息显示 *J&T EXPRESS AUSTRALIA PTY LTD* 是一家自 2026 年 6 月 4 日起注册生效的澳大利亚私营公司（ABN `18 698 743 609`），主要营业地位于新南威尔士州，隐私声明注明的注册办公地址在 Villawood NSW。它隶属于 2015 年创立于印尼、在香港上市（`01519.HK`）的极兔速递集团，运单号以 `JTAU` 开头，上表中的所有状态句均来自真实极兔澳大利亚包裹。

### 极兔轨迹中的 `picked up from customs clearance` 是什么意思？

说明清关已经完成。该海外包裹已获海关放行，极兔从清关代理处揽收了包裹准备派送。这是进口包裹在极兔的首条记录，Peek 会显示为“已揽收”，而非海关扣留。

### 为什么我的极兔单号查不到记录？

请先确认单号是否为 `JTAU` 加 12 位数字：例如英国 `JTUK` 等其他国家极兔单号不属于澳大利亚网络。如果单号无误，极兔可能尚未揽收，其网站会显示 `No tracking records yet`，Peek 显示“等待 J&T Express Australia 更新”。两天后仍查不到时，Peek 会提示核对单号、更换快递公司或删除包裹；若多日仍无记录请联系商家。

### 如何联系极兔澳大利亚客服？

可按联系页面拨打 `13 18 18`（服务时间 `08:00–20:00`）或发邮件至 `service@jtexpress.au`。网购订单请同时联系发货商家。

### 极兔澳大利亚送达时会拍照吗？

是的。根据极兔澳大利亚隐私声明，司机会对包裹及放置位置拍照留证，并通过短信或其他电子通知把签收证明链接发给收件人。

:::sources
- [J&T Express Australia: 运单查询](https://www.jtexpress.au/tracking) – 官方查件页、tracking/waybill 名称及 `No tracking records yet`
- [J&T Express Australia: 联系我们](https://www.jtexpress.au/contact) – 电话 `13 18 18`、服务时间、客服/商务/合作邮箱及电商零售客户
- [J&T Express Australia: 派送合作伙伴](https://www.jtexpress.au/career) – 覆盖全澳的末端派送与揽收合作伙伴
- [J&T Express Australia: 关于我们](https://www.jtexpress.au/about) – 极兔速递集团（`01519.HK`）、2015 年创立于印尼、东南亚与中国网络
- [J&T Express Australia: 隐私声明](https://www.jtexpress.au/privacy-policy) – 公司全称与注册地址、签收照片与短信链接、`dataprotection@jtexpress.au` 不拍照申请、理赔调查及马来西亚/菲律宾客服支持
- [Universal Parcel Scraper: J&T Express Australia](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/j-and-t-au) – 别名、`JTAU` 规则、链接规则、实单英文轨迹与阶段映射、隐私脱敏、最新 100 条限制与帮助页面
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – 其他国家极兔单号格式、聚合回退、燕文批次交接及透传轨迹
- [ABN Lookup: J&T Express Australia Pty Ltd](https://abr.business.gov.au/ABN/View?abn=18698743609) – 自 2026 年 6 月 4 日起生效的 ABN 及新南威尔士州主体信息
:::
