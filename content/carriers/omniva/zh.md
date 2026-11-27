---
title: Omniva 物流查询：爱沙尼亚、拉脱维亚与立陶宛单号、状态与快递柜
description: 查询波罗的海三国 Omniva 包裹：JJEE 与 EE 单号格式、英文物流状态含义、快递柜 4 天保管与延期规则、清关说明及三国客服电话。
slug: omniva-tracking
published: 2026-11-26
updated: 2026-11-26
---

Omniva 是爱沙尼亚国家邮政（公司法定名称仍为 Eesti Post），也是覆盖爱沙尼亚、拉脱维亚与立陶宛三国的智能快递柜与包裹派送网络。你可以凭包裹追踪码在 minu.omniva.ee、Omniva 应用中查件，或直接将单号粘贴到上方输入框。追踪码位于商家的发货邮件，或 Omniva 发送的短信与电子邮件中。

## Omniva 运单号是什么样的

Omniva 将其称为“shipment tracking code”或“shipment code (tracking number)”。其英文退货帮助页面给出的示例为 `CEXXXXXXXXEE`。以下是常见的几种格式（数字均为虚构）：

| 单号 | 含义 |
| --- | --- |
| `JJEE48291736405829173` | Omniva 包裹：`JJEE` 加 17 位数字，共 21 位。 |
| `JJEEMKP48291736405` | 另一种 Omniva 包裹单号：`JJEE` + 三位字母 + 11 位数字。 |
| `CE482917367EE` | 两位字母 + 九位数字 + `EE`：交寄至 Omniva 的国际邮政标准单号。发往拉脱维亚和立陶宛快递柜的包裹也带有以 `EE` 结尾的单号。 |
| `RR739402812CN` | 境外寄入的包裹保留寄出地邮政的单号；此处以 `CN` 结尾，来自中国。 |

取件短信里的 6 位开门密码用于打开智能快递柜，并非运单号；6 位退货码也不是运单号。

以 `LV` 结尾的单号属于[拉脱维亚邮政（Latvijas Pasts）](carrier:latvijas-pasts)，以 `LT` 结尾的属于[立陶宛邮政（Lietuvos paštas）](carrier:lietuvos-pastas)，而 `JJFI` 单号属于芬兰 [Posti](carrier:posti)。更多格式见[运单号格式说明](guide:tracking-number-formats)（英文）。

## Omniva 英文物流状态含义

Omniva 网站查询页支持英语、爱沙尼亚语、拉脱维亚语、立陶宛语和俄语，官方应用以英文显示各步骤；点击“Track & trace details”可展开完整时间线。常见状态大致按顺序如下：

| Omniva 显示 | 对你的含义 |
| --- | --- |
| `Shipment registered. Shipment has not been handed over to Omniva` | 寄件人已创建运单，Omniva 尚未收到包裹。 |
| `Sender has placed the shipment in the parcel machine` | 寄件人已将包裹投入寄件快递柜。 |
| `The courier has picked up the shipment` | Omniva 快递员已从寄件人处揽收。 |
| `Shipment has been posted by the sender or collected by the postal service` | 境外包裹已在寄出地邮政收寄。 |
| `Shipment held in customs` | 正在等待海关清关。 |
| `Shipment has arrived at a postal facility for further processing` | 已进入 Omniva 网络，位于旁边标明的转运站或分拣线。 |
| `The shipment has been handed over to the courier` | 正在派送中。 |
| `Shipment is ready for collection from the parcel machine` | 已存入指定快递柜等待取件，请使用 6 位开门码。 |
| `A notification of shipment arrival has been sent` | Omniva 已向你发送短信、邮件或应用通知，包裹本身并未移动。 |
| `Delivery attempt made` | 快递员尝试派送但未成功。 |
| `Shipment delivered` | 已送达或已取件，并标注快递柜或邮局名称。 |
| `Shipment is being returned to the sender` | 正在退回寄件人。 |

有两条状态容易引起误解：`Delivery attempt made` 实际表示这次派送没有成功；而出现在退件之后的 `Shipment delivered` 表示包裹已退还给寄件人。

## Omniva 智能快递柜、快递上门与派送未成功

**到件通知。** Omniva 通过短信、邮件或应用通知提醒你。在 Omniva 应用中，短信通知默认关闭，可在 Profile → Notification preferences 中开启。

**快递柜取件。** 在通知指定的快递柜上输入 6 位开门码即可取件。持有开门码的任何人均可代取，除非寄件人附加了身份核验，否则无需出示身份证件。

**保管与延期。** 波罗的海三国的快递柜均保管 **4 天**，到期前 Omniva 会发提醒。你可以在自助服务平台（e-service）付费延长保管期，每次最多延 4 天，累计最多可延 12 天；爱沙尼亚邮局的保管期可申请延长一次，最长一个月。逾期未取的包裹将退回寄件人，退件运费由寄件人承担。

**送货上门。** 你会收到写明派送日期的短信，快递员到达前会致电联系（拉脱维亚和立陶宛会提前约好日期与时段）。上门派送限重：爱沙尼亚和拉脱维亚为 30 公斤，立陶宛为 25 公斤。若时间不方便，可请快递员改期或改投附近快递柜。

**上门派送未成功。** L 号及以下尺寸包裹会转投附近快递柜保管 4 天。在爱沙尼亚，XL 号以内包裹可能改存邮局 7 天，XXL 包裹存派送中心 7 天；在拉脱维亚，大件存派送中心 4 天；在立陶宛，大件直接退回寄件人。

**改派。** 包裹抵达分拣中心前，通过 Omniva 邮件链接或 e-service 改派免费；到达分拣中心后改派，或改投上门派送，需支付费用。包裹也可能因快递柜已满而被改投他处。

## Omniva 包裹延误、破损或滞留海关

**未送达或无更新。** 请先确认是否已超过 Omniva 参考时效（见下文）；若已超期，Omniva 要求收件人联系寄件人，由寄件人提交“Request parcel delivery status”查件表单。另见[物流停止更新的原因](guide:tracking-not-updating)（英文）。

**空柜或投错地点。** 请直接联系所在国家的 Omniva 个人客户支持。

**包裹破损。** 在爱沙尼亚，收件人可在交寄后 **6 个月**内提交破损或遗失表单，但赔款通常仅支付给寄件人。在拉脱维亚和立陶宛，帮助页面指引收件人联系寄件人发起理赔（立陶宛需附包裹与包装照片），不过 Omniva 拉脱维亚条款同样允许收件人在交寄后 **6 个月**内书面投诉。

**海关清关（爱沙尼亚）。** 自 2021 年 7 月 1 日起，所有来自欧盟境外的包裹无论货值高低均须在 **30 天**内完成申报。你可以委托 Omniva 代办，也可自行向爱沙尼亚税务与海关委员会申报。详见[海关滞留包裹指南](guide:customs)（英文）。

> 冒充 Omniva 的诈骗短信常附带虚假链接要求支付小额派送费或关税。Omniva 绝不会用境外号码发短信，官方邮件仅来自 `omniva.ee`、`omniva.lv`、`omniva.lt` 或 `omniva.eu`。请直接前往 `minu.omniva.ee` 查询包裹。

## Omniva 客服电话与联系方式

以下联系方式公布于 Omniva 个人客户联系页面（均为各国当地时间）：

| 国家 | 电话 | 电子邮箱 | 服务时间 |
| --- | --- | --- | --- |
| 爱沙尼亚 | `+372 661 6616` | `info@omniva.ee` | 周一至周五 08:00–19:00，周六 09:00–15:00 |
| 拉脱维亚 | `+371 27042424` | `info@omniva.lv` | 周一至周五 08:00–18:00，周六 09:00–15:00 |
| 立陶宛 | `+370 700 55077` | `info@omniva.lt` | 周一至周五 08:00–18:00，周六 09:00–15:00 |

三国网站均提供配送状态查询、破损/遗失申报及改派表单。立陶宛热线话费按 Telia 资费标准收取。Omniva 未提供在线人工聊天。

## 使用 Peek 跟踪 Omniva 包裹

[Peek](/) 会直接将上述两种 `JJEE` 单号识别为 Omniva。粘贴 `minu.omniva.ee`、`mana.omniva.lv`、`mano.omniva.lt` 的 `/track/` 链接，以及 `omniva.ee` 或 `omniva.lt` 带 `barcode=` 的链接，无论单号格式如何均直接生效，且无需填写邮编。

以 `C`、`R`、`E` 或 `L` 开头（不含 `CR`、`CV`、`RO`）、以 `EE` 结尾且校验位有效的国际邮政单号会直接识别为 Omniva。以其他字母开头的 `EE` 单号会由 Peek 同时向 Omniva 和 Chronopost 查询，归属查有记录的一方；若暂均无记录，页面会提示“……暂未找到。添加后 Peek 会继续查询。”并按递减频率持续跟进。已归属 Omniva 但 Omniva 尚未录入的包裹会显示“等待 Omniva 更新”；如果两天后 Omniva 仍无记录且无其他来源回应，Peek 会提示检查单号、更换快递公司或删除包裹，之后改为每天检查一次，添加满一周后每周一次。校验位错误的单号不会匹配任何快递，即便手动选择 Omniva 也会被拒：请检查是否输错。境外邮政单号可通过“更换”或 Omniva 链接切换至 Omniva 查询；当寄出地邮政注明发往爱沙尼亚（或未标明目的地且你在爱沙尼亚）时，Peek 会同步询问 Omniva 并在 Omniva 出现不早于寄出地最新扫描的记录后转入 Omniva 跟进（发往拉脱维亚或立陶宛则相应询问 Latvijas Pasts 或 Lietuvos paštas）。Omniva 寄往境外的 `EE` 邮政单号同理可接续至目的地邮政。

Peek 展示 Omniva 英文状态、在地图上标注每次扫描的 Omniva 站点、目的地国家、签收时间以及包裹存放的快递柜或邮局；具体地址、邮编与失败原因会被过滤，且不显示预计送达日或重量。当你正在 Peek 中查看该包裹，或派送当天已开启通知时，Peek 最多每 10 分钟查询一次 Omniva；在 Omniva 扫描包裹开始派送的当天，只要 Omniva 自己的查件系统正常响应，自爱沙尼亚时间早上 6 点起每 2 分钟查询一次；其余时候每小时查询一次。连续两天无新动态（或在“已生成面单”/“已揽收”停留满 12 小时）后改为每小时一次；在“已生成面单”停留满 7 天后每 6 小时一次。若包裹停留在“已生成面单”、“已揽收”或“运输中”满 4 天无新记录，Peek 会显示“自……起没有物流更新”、提示“包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 Omniva 查询。”并提供“向 Omniva 寻求帮助”链接；满三周后提示“已超过三周没有新动态。如果还没收到，请联系卖家。Peek 会持续向 Omniva 查询。”Peek 可以按“每次扫描”、“重要环节”或“仅派送当天”通知你。历史较久的包裹可能只保留签收记录。

## 关于 Omniva 物流查询的问题

### Omniva 派送需要多长时间？

自交寄给 Omniva 起算，波罗的海单国内部为 1 至 2 个工作日，三国跨境之间为 2 至 3 个工作日。爱沙尼亚和立陶宛页面注明快递上门为次日达且不超过 3 天；拉脱维亚页面仅注明 3 天内。

### 可以用手机号查询 Omniva 包裹吗？

Omniva 的页面只介绍了凭运单号查件。在 `minu.omniva.ee` 注册 Omniva e-service 账号后，可以在同一处查看寄给你的未来包裹。

### Omniva 显示 “Delivery attempt made” 是什么意思？

表示快递员尝试上门派送但未能成功——虽然字面看似完成了一次操作，但实际上派送没有成功。

### 能用 Omniva 查询从国外寄往爱沙尼亚的国际包裹吗？

可以，前提是寄出地国家已将数据上传至国际邮政数据库；在包裹进入爱沙尼亚前，请先在寄出地邮政网站查询。

:::sources
- [Omniva Estonia: Track and receive parcels](https://www.omniva.ee/en/track-and-receive-parcels/) – 包裹追踪码、开门码、收件方式、快递员短信与来电
- [Omniva Latvia: Track and receive](https://www.omniva.lv/en/track-and-receive-parcels/) – 拉脱维亚的收件、快递上门、30 公斤
- [Omniva Lithuania: Track and receive](https://www.omniva.lt/en/track-and-receive-parcels/) – 立陶宛的收件、快递上门、25 公斤
- [Omniva: How to track your parcel?](https://www.omniva.ee/help/how-to-track-your-parcel-ee) – 短信或邮件中的追踪码、概览与详情、境外包裹
- [Omniva: Parcel delivery timeframes](https://www.omniva.ee/help/omniva-parcel-delivery-timeframes-ee) – 派送时效、快递次日送达
- [Omniva Latvia: Omniva sūtījumu piegādes termiņi](https://www.omniva.lv/palidziba/omniva-sutijumu-piegades-termini) – 快递 3 天内送达（拉脱维亚语）
- [Omniva Lithuania: OMNIVA siuntų pristatymo terminai](https://www.omniva.lt/pagalba/omniva-siuntu-pristatymo-terminai-lt) – 快递次个工作日送达（立陶宛语）
- [Omniva: How long items are stored by Omniva?](https://www.omniva.ee/help/how-long-items-are-stored-by-omniva-ee) – 各国的保管期、退回
- [Omniva Latvia: Cik ilgi Omniva uzglabā sūtījumus?](https://www.omniva.lv/palidziba/cik-ilgi-omniva-uzglaba-sutijumus) – 拉脱维亚的到期提醒（拉脱维亚语）
- [Omniva: Extend parcel storage time](https://www.omniva.ee/help/extend-parcel-storage-time-ee) – 每次最多延 4 天、累计最多 12 天
- [Omniva: Will I get notified about a delivery?](https://www.omniva.ee/help/will-i-get-notified-about-a-delivery-ee) – 到件通知、快递员来电
- [Omniva: Issue with the delivery SMS or email notification](https://www.omniva.ee/help/i-have-an-issue-with-the-delivery-smsemail-notification) – 应用的通知设置
- [Omniva: When courier parcels are delivered?](https://www.omniva.ee/help/when-courier-parcels-are-delivered-ee) – 快递派送时间
- [Omniva: Will the courier deliver parcels to my door?](https://www.omniva.ee/help/will-the-courier-deliver-parcels-to-my-door-ee) – 重量限制
- [Omniva: Not at the address when the courier delivers](https://www.omniva.ee/help/what-happens-if-im-not-at-the-delivery-address-when-the-courier-delivers-the-parcel-ee) – 上门派送未成功时的选项
- [Omniva: Can someone else pick up my parcel?](https://www.omniva.ee/help/can-someone-else-pick-up-my-parcel-ee) – 他人代取
- [Omniva: Do I need to show ID?](https://www.omniva.ee/help/do-i-need-to-show-id-when-receiving-items-via-omniva-ee) – 身份证件规定
- [Omniva: Redirect a parcel or letter](https://www.omniva.ee/help/redirect-a-parcel-or-letter-ee) – 改派规则与费用
- [Omniva: My parcel hasn't been delivered](https://www.omniva.ee/help/my-parcel-hasn%E2%80%99t-been-delivered-ee) – 包裹延误时由谁处理
- [Omniva: My parcel is damaged](https://www.omniva.ee/help/my-parcel-is-damaged-ee) – 破损表单、6 个月
- [Omniva Latvia: Sūtījumu pakalpojumu standarta noteikumi privātajiem klientiem](https://www.omniva.lv/wp-content/uploads/sites/6/2026/06/LV_Omniva-Standard-Terms-and-Conditions-of-Parcel-Services-for-Private-Customers_2026_07_22.pdf) – 寄件人或收件人在 6 个月内投诉（拉脱维亚语）
- [Omniva: The parcel machine locker is empty](https://www.omniva.ee/help/the-parcel-machine-locker-is-empty-or-a-shipment-is-missing) – 联系客服
- [Omniva: How to return a parcel?](https://www.omniva.ee/help/manage-shipments-general/return-a-parcel-containing-items-purchased-in-estonia-latvia-or-lithuania-ee) – 示例单号 CEXXXXXXXXEE、退货码
- [Omniva Estonia: Customs: receiving](https://www.omniva.ee/en/customs-receiving/) – 30 天内申报
- [Omniva: Which shipments require a customs declaration](https://www.omniva.ee/help/which-shipments-require-a-customs-declaration) – 自 2021 年 7 月 1 日起所有欧盟境外包裹
- [Omniva Estonia: Contacts for private customers](https://www.omniva.ee/en/contacts-for-private-customers/) – 电话、邮箱、服务时间、表单、Eesti Post
- [Omniva Latvia: Contacts for private customers](https://www.omniva.lv/en/contacts-for-private-customers/) – 电话、邮箱、服务时间
- [Omniva Lithuania: Contacts for private customers](https://www.omniva.lt/en/contacts-for-private-customers/) – 电话、邮箱、服务时间、Telia 资费
- [Omniva: How to distinguish scams from genuine parcel notifications](https://www.omniva.ee/en/frauds/) – 诈骗迹象、电话号码、邮件域名
- [Omniva Estonia: Home](https://www.omniva.ee/en/) – 诈骗提醒、e-service
- [Omniva: Tracker interface strings](https://minu.omniva.ee/locales/common/common_en.json) – 查件页面的语言
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Peek 读取的内容
:::
