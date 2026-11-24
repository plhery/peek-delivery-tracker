---
title: 极兔德国（J&T Express Germany）单号查询、邻居代收与客服
description: 查询德国极兔（J&T Express Germany）包裹：JTDE 16 位单号、邻居代收查询、投递偏好与签收照片政策、德国消费者权益及客服。
slug: jt-express-deguo-chaxun
published: 2026-11-24
updated: 2026-11-24
---

J&T Express Germany（极兔德国，*J&T Express Germany GmbH*，注册地位于法兰克福）是极兔速递集团在德国运营的快递网络，负责德国境内包裹派送。如果网店通过极兔德国发货，运单号会以 `JTDE` 开头：你可以直接粘贴到上方查询框中，或在 [jtexpress.de 官方查件页](https://www.jtexpress.de/tracking) 查询。运单号由商家在发货邮件或订单详情页提供。

## 极兔德国单号格式：`JTDE` 加 12 位数字

极兔德国常见问题写明运单号均以 `JTDE` 开头。Peek 仅识别一种形态：`JTDE` 后接 12 位数字，共 16 位字符（以下均为虚构示例）：

| 单号（虚构） | 归属与说明 |
| --- | --- |
| `JTDE482917360514` | `JTDE` 加 12 位数字：极兔德国包裹，Peek 会立刻识别。 |
| `JTDE48291736051` | 少了一位数字：不符合极兔德国格式，Peek 不会识别承运商，请检查是否漏输。 |

如果 `JTDE` 后面的数字多了一位或少了一位且没有任何承运商返回记录，peektracker.com 会提示“这个单号开头像 J&T Express Germany 的运单号，但少了一个字符，可能输错了。请和发货消息里的单号核对一下。”（或多了一个字符），提醒你对照发货消息核对。

**在哪里找到单号。** 极兔说明单号可在网店订单页、下单确认邮件、发货邮件或短信、派送预告/签收邮件以及包裹面单上找到；若找不到，请联系寄件人或电商平台客服，或参阅[如何查找快递单号](guide:find-tracking-number)（英文）。极兔德国官网单次最多查询 10 个单号（以空格或逗号分隔）。

**易混淆的其他极兔前缀。** 极兔在各国使用独立查询系统：`JTUK` 加 12 位属于极兔英国（J&T Express UK），`JTAU` 加 12 位属于[极兔澳大利亚（J&T Express Australia）](carrier:j-and-t-au)，`JTE` 加 12 位属于[极兔中东（J&T Express Middle East）](carrier:j-and-t-middle-east)，`JT` 加 13 位属于东南亚或[极兔速递中国（J&T Express China）](carrier:j-and-t-cn)。仅 `JTDE` 属于德国网络。若官网显示 `Keine Abfrageergebnisse`（英文 `No tracking records yet`）或 Peek 持续显示“等待 J&T Express Germany 更新”，请对照发货邮件核对单号或询问卖家。

## 极兔德国（J&T Express Germany）物流状态含义

极兔德国官网为每条扫描提供时间戳与一句描述，不含地点信息。网页默认德文，但可在语言菜单切换英文或中文重新加载轨迹。Peek 读取英文接口，以下是实单常见状态（按流转顺序）：

| 极兔德国英文状态 | 对你的含义 | Peek 显示 |
| --- | --- | --- |
| `The shipment has been successfully picked up and is being prepared for transportation` | 极兔已从寄件人处揽收包裹。 | “已揽收” |
| `The shipment arrives at the distribution center and is prepared for transport to the next destination.` | 已抵达极兔分拣中心。 | “运输中” |
| `The shipment is dispatched from the transit center to the next destination` | 已离开中转中心发往下一站。 | “运输中” |
| `Your package has been loaded and is on its way to you` | 已装上派送车，正在派送途中。 | “派送中” |
| `Delivery failed because the address could not be found` | 司机无法找到收件地址，投递失败。 | “派送未成功” |
| `Delivery failed, customer refused to accept` | 收件人拒收包裹。 | “派送未成功” |
| `Your parcel has been delivered to the pickup point. Please check your email for details` | 已存放在自提点，极兔会发邮件告知取件详情。 | “待取件” |
| `Your package has been successfully delivered.` | 包裹已妥投签收（极兔会在后面注明投递方式）。 | “已签收” |
| `Returns have begun` | 派送失败后已启动退回寄件人流程。 | “已退回寄件人” |
| `The shipment has been returned` | 已完成退回寄件人。 | “已退回寄件人” |

未列入上表的文案按 Peek 通用规则归类；暂未识别的文案保持原文并记作“运输中”（若已进入派送中或之后阶段则维持当前阶段不变）。

装车行可能因多次派送而重复出现。签收行末尾会在 `【】` 中标明签收方式并附带客服提示，Peek 会自动剔除客服提示，仅保留以下六种签收方式：`Left with neighbor`（邻居代收）、`Front Door Porch`（家门口）、`Front Desk Reception`（前台）、`Garden Yard`（花园/院子）、`Mailbox`（信箱）和 `Garage`（车库）。

**查是哪位邻居代收？** 在 `jtexpress.de` 签收记录下点击 `Zustelldetails anzeigen`（英文版 `Click to view delivery details`）并输入收件邮编即可查看代收邻居姓名与门牌号（输错 5 次邮编需稍候再试）。Peek 仅凭单号查询，因此只显示 `Left with neighbor` 而不展示姓名、门牌号或任何电话号码。

## 极兔德国派送偏好、签收照片与退件说明

在 Peek 观测到的德国实单中，极兔派送员（或德国各地加盟派送合作伙伴）直接送货上门、送交自提点或反馈未妥投原因，未见转交其他德国快递商的情况。

- **派送偏好设置：** 派送通知邮件中带有极兔[派送偏好页面](https://www.jtexpress.de/delivery-preference)链接，核验邮编后可选择本人签收、指定邻居（填姓名与同层/隔壁）、前台代收，或无签收留置在门口、花园、信箱或车库（选择无签收留置时，包裹放置后的遗失风险由收件人承担）。
- **签收照片政策：** 根据极兔德国 POD 隐私声明，司机在交接或无签收留置时会拍摄包裹及放置局部照片（误入画面的人会被自动模糊），照片常规保留 **6 个月**（如有投诉则保留至结案），并可提供给发件商家处理客诉。
- **拒收或找不到地址：** 拒收后次晨可能再次装车派送；若提示找不到地址，请检查是否漏填楼层、门铃姓名或门牌号并立刻联系商家更正。详见[如何修改收货地址](guide:change-delivery-address)（英文）与[错过派送怎么办](guide:missed-delivery)（英文）。
- **尝试次数与网点：** 极兔德国官网未公布派送尝试次数、保管天数或固定网点列表，但实单轨迹中存在投递至自提点并邮件通知的节点。

## 极兔德国包裹延误、丢件或破损的消费者权益

网购订单请优先联系发货商家。依据德国《民法典》第 475 条（§ 475 BGB），个人消费者向商家购物时，运输途中的灭失或破损风险原则上由卖家承担（除非买家自行委托了卖家未提供的承运商）。

- **轨迹不动或查不到：** 在途满 4 天无更新时，Peek 会标出最新扫描日期（“自10月12日周一起没有物流更新”）并附上“向 J&T Express Germany 寻求帮助”链接。未录入单号显示“等待 J&T Express Germany 更新”，满两天无记录后提示“两天过去了，快递公司仍查不到这个单号。请检查单号、更换快递公司，或删除这个包裹。”并降频为每日/每周查询。详见[为什么物流停止更新](guide:tracking-not-updating)（英文）。
- **显示已签收但未收到：** 先核对 Peek 中注明的放置处或前往官网凭邮编查询代收邻居，再联系极兔热线与卖家。详见[显示已签收但未收到](guide:delivered-not-received)（英文）。
- **破损与德国官方调解：** 依据德国《商法典》第 438 条（§ 438 HGB），外包装明显破损须最迟在签收当场申报（口头告知司机即可），隐蔽破损须在签收后 **7 天内**以文本形式（如发邮件给极兔）申报。如与极兔产生争议，可免费向联邦网络管理局（Bundesnetzagentur）邮政仲裁处（*Schlichtungsstelle Post*）在线申请调解，若大型电商特殊协议排除仲裁，也可求助 *Verbraucherservice Post*。详见[包裹丢失](guide:lost-parcel)（英文）、[包裹破损](guide:damaged-parcel)（英文）与[包裹退回寄件人](guide:returned-to-sender)（英文）。

## 极兔德国官方客服与联系方式

以下信息发布于[极兔德国联系我们页面](https://www.jtexpress.de/contact)：

- **客服电话：** `+49 6931090650`（德国境内 `069 31090650`），周一至周五 `08:00–20:00`（法定节假日休息）。
- **客服邮箱：** `de-jtservice@jtexpress.de`。
- **在线聊天：** 英文页面为“Start Chat”，德文页面为“Chat starten”。
- **签收照片与隐私数据：** `de-dpo@jtexpress.de`。
- **办公与注册地址：** 办公地址 *J&T Express Germany GmbH, Am Giener 2, 55268 Nieder-Olm*；法律声明注册地址 *Wiesenhüttenstraße 11, 60329 Frankfurt am Main*。

## 使用 Peek 追踪极兔德国包裹

在 [Peek](/) 输入 `JTDE` 单号（不限空格与大小写）会立刻识别为 J&T Express Germany（“识别出的快递公司”），仅凭单号即可直连查询；粘贴带 `?q=` 的 `www.jtexpress.de/tracking` 链接同样有效。非 `JTDE` 单号强行通过“更换”选极兔德国会提示其“不使用这种格式的单号”。

Peek 展示当前阶段、精确到分钟的每条扫描（自动换算为你的设备时区，底部标注“时间按你所在的时区显示：…”）和签收时间。扫描采用极兔英文原文；因极兔不提供地点、预计送达日和重量，Peek 不显示地图，超长历史最多保留最新 100 条。包裹页的“打开 J&T Express Germany 官网”按钮可直接打开该单号。若先前有记录但后续暂时查询为空，Peek 会保留已找到的记录并提示“快递公司暂时没有新的扫描记录。已找到的记录会保留，Peek 会继续查询。”

在打开包裹页面或预计派送当天开启提醒时，只要极兔官网正常响应，Peek 最高每 10 分钟检查一次，在极兔扫出派送中当天自当地时间早上 6 点起最高每 2 分钟检查一次；通过聚合追踪服务跟进时，每小时检查一次，静默满一天后改为每 3 小时一次，在派送当天打开页面或开启提醒时最高每 15 分钟检查一次；其余时间及连续两天无更新（或“已揽收”满 12 小时）后每小时检查一次。通知预设可选“每次扫描”、“重要环节”或“仅派送当天”。Peek 无法代你联系极兔、更改地址或预约重派。

## 关于极兔德国追踪的常见问题

### 极兔德国（J&T Express Germany）是正规快递公司吗？

是的。*J&T Express Germany GmbH* 在法兰克福注册，隶属于香港上市的极兔速递集团，自 2026 年 9 月 1 日起登记在德国联邦网络管理局（Bundesnetzagentur）邮政服务商名录中。

### 怎么查是哪位邻居代收了我的极兔包裹？

在 `jtexpress.de` 查到妥投记录后，点击 `Zustelldetails anzeigen`（英文 `Click to view delivery details`）并输入收件邮编即可看到代收邻居姓名与门牌号；Peek 仅显示 `Left with neighbor`。

### 极兔在德国有合作代收点（Parcel Shop）吗？

极兔德国官网未公布固定门店列表，但实单扫描中包含包裹投递至自提点并邮件发送取件信息的记录；你也可以在派送偏好页面指定邻居、前台或安全放置点。

### 为什么我的极兔单号查不到？

请确认单号是否为 `JTDE` 加 12 位数字（英国 `JTUK` 等前缀不属于德国网络）。如单号无误且刚生成，官网会显示 `Keine Abfrageergebnisse`，Peek 显示“等待 J&T Express Germany 更新”；超过数日仍无记录请联系卖家。

### 如何联系极兔德国客服？

可在工作日 `08:00–20:00` 致电 `+49 6931090650`、使用 `jtexpress.de` 在线聊天或发邮件至 `de-jtservice@jtexpress.de`。网购售后请同时联系发货商家。

:::sources
- [J&T Express Germany: 运单查询](https://www.jtexpress.de/tracking) – 单次 10 单查询、多语言切换与邮编查看邻居代收（限试 5 次）
- [J&T Express Germany: 常见问题](https://www.jtexpress.de/faq) – `JTDE` 单号查找、未收到与改址指引
- [J&T Express Germany: 派送偏好](https://www.jtexpress.de/delivery-preference) – 邮编验证、签收/无签收选项与留置风险提示
- [J&T Express Germany: 联系我们](https://www.jtexpress.de/contact) – `+49 6931090650`、`de-jtservice@jtexpress.de`、在线聊天与 Nieder-Olm 办公地址
- [J&T Express Germany: 法律声明](https://www.jtexpress.de/legal-statement) – *J&T Express Germany GmbH* 法兰克福注册地址
- [J&T Express Germany: POD 签收隐私声明](https://www.jtexpress.de/pod-policy) – 签收照片、6 个月保留期与 `de-dpo@jtexpress.de`
- [J&T Express Germany: 加盟合作伙伴](https://www.jtexpress.de/career) – 全德末端派送加盟网络
- [J&T Express Germany: 关于我们](https://www.jtexpress.de/about) – 2015 年创立与香港上市背景
- [Gesetze im Internet: § 475 BGB](https://www.gesetze-im-internet.de/bgb/__475.html) – 德国消费者网购运输风险归属卖家
- [Gesetze im Internet: § 438 HGB](https://www.gesetze-im-internet.de/hgb/__438.html) – 显性破损当场申报、隐蔽破损 7 天内文本申报
- [Bundesnetzagentur: 邮政服务商名录](https://www.bundesnetzagentur.de/DE/Fachthemen/Post/Anbieterverzeichnis/PostAnbieterverzeichnis.html) – *J&T Express Germany GmbH* 自 2026 年 9 月 1 日起登记在册
- [Bundesnetzagentur: 邮政仲裁常见问题](https://www.bundesnetzagentur.de/DE/Vportal/Schlichtung/Schlichtung_Post/_FAQ_Schlichtung/faqSchlichtung.html) – 免费仲裁申请与 Verbraucherservice Post
- [Universal Parcel Scraper: J&T Express Germany](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/j-and-t-de) – `JTDE` 规则、链接规则、实单英文状态、六类签收方式与隐私过滤
- [Universal Parcel Scraper: 极兔共用的查询接口](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/j-and-t-au) – 英文轨迹、精确扫描时间、最新 100 条扫描
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – 不转交其他快递公司、聚合追踪服务作为后备
:::
