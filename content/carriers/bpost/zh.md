---
title: 比利时邮政 bpost 快递查询：单号、状态、自提与关税
description: 查询比利时邮政 bpost 包裹：条码运单号格式、Track & Trace 物流状态中文解读、自提点与 Bbox 快递柜期限、欧盟清关费及客服电话。
slug: bpost-bilishi-youzheng-chaxun
published: 2026-11-24
updated: 2026-11-24
---

比利时邮政（bpost）是比利时的邮政运营商，隶属 bnode 集团，负责比利时与荷兰的末端派送。你可以凭条码在 [bpost Track & Trace 页面](https://track.bpost.cloud/)、My bpost 手机应用中查件，或直接将单号粘贴到上方输入框。条码通常出现在商家的发货邮件、bpost 的短信或邮件、包裹面单或信箱投递通知单上。

## bpost 运单号（条码）在哪里找？

bpost 把运单号称为条码（barcode），常见于包裹面单、邮局交寄回执、未妥投通知单及确认邮件中。网店通常会发邮件告知，有时直接附带 Track & Trace 链接。如果没有条码，请联系商家。更多方法见[如何查找运单号](guide:find-tracking-number)（英文）。

bpost 公布的单号格式如下（以下均为虚构示例）：

| 格式 | 示例 | 类型 |
| --- | --- | --- |
| 3232 或 3299 开头的 24 位数字 | `323248291057364918273649` | 包裹条码 |
| 3232 开头的 18 位数字 | `323248291057364918` | 包裹条码 |
| JJBEA 开头的 27 位字符 | `JJBEA4829105736491827364918` | 包裹条码 |
| 两位字母 + 九位数字 + `BE` | `CE482910576BE` | 比利时寄出的国际邮政单号 |
| `R` + 一位字母 + 九位数字 + 两位字母 | `RR482910576BE` | 国际挂号邮件 |
| 010 开头的 30 位数字 | `010482910573649182736491827364` | 比利时境内挂号邮件 |

以 `U` 开头的条码仅用于海关申报：寄件人或买家选择了无 Track & Trace 跟踪的服务，bpost 表示这类邮件会在清关完成后 5 个工作日内送达。

末尾为其他国家字母的国际邮政单号属于对应国家邮政。以 `BE` 结尾的单号若输错一位数字，校验位就会失效，Peek 将不会推荐任何快递公司。部分 24 位数字单号可能属于 [InPost](carrier:inpost) 或 Ciblex。

## bpost Track & Trace 物流状态中文解读

bpost Track & Trace 提供英语、法语、荷兰语和德语版本。以下为包裹的英文状态（按流转顺序排列）：

| bpost 状态原文 | 中文含义 |
| --- | --- |
| `Confirmation of preparation of the shipment received` | 寄件人已预报包裹，bpost 尚未收到实物。 |
| `Item is announced / Landmark Global (bpost) received the information` | 跨境包裹已向集团旗下跨境品牌 Landmark Global 预报。 |
| `Landmark Global (bpost) has received the item` | Landmark Global 已收到包裹。 |
| `Arrival at facilities` | 境外寄出的包裹已到达发件国分拣中心，尚未由 bpost 扫描。 |
| `Your item has been sorted` | 已完成分拣并正在发运途中。 |
| `Departure to country of destination` | 正发往目的国。 |
| `Your shipment has arrived at the postal operator of the country of destination and will be delivered in the coming days` | 已抵达目的国邮政，将在未来几天内派送。 |
| `The postman's delivery round for your item has started` | 邮递员今天已带件出发派送。 |
| `Delivery interrupted due to unforeseen circumstances` | 因 bpost 方面原因，本次派送行程在送达前中断，后续会另行安排派送日。 |
| `New delivery scheduled` | bpost 已排定新的派送日期，包裹正等待派送。 |
| `Item available at Pick-up point` | 已到达自提点等待领取。 |
| `Item available at the bbox locker` | 已存入 Bbox 快递柜等待领取。 |
| `Item delivered` | 已送达或已从自提点取件。 |

## 派送、自提点与 Bbox 快递柜

若你不在家且未设置派送偏好，邮递员通常会尝试交由邻居代收或放至安全存放处。如果仍无法投递，你会收到未妥投通知，自次个工作日即可前往通知注明的自提点领取。包裹就绪时 bpost 会发邮件通知；只有在无法通过电子方式联系到你时才会留纸质通知单。邮递员当天不会再次上门，但若周六派送未成功，会在下一个工作日再派一次。

**保管期限：** 自提点（Pick-up point）保管 14 个自然日，Bbox 快递柜保管 5 个自然日且不可延期，逾期均退回寄件人。任何持条码的人都可代取普通包裹；挂号邮件则需收件人本人身份证，或在通知单上签字授权代领。

**Bbox 快递柜：** 全天候 24 小时开放，无需注册账号。扫描 My bpost 应用或邮件中的二维码即可开柜；无屏幕快递柜只能通过 My bpost 应用以蓝牙开启。

**修改派送偏好：** 可在 MyPreferences 或 My bpost 应用中预先设定安全存放处、邻居代收、自提点或快递柜。包裹在途期间也可改送至附近其他地址，或延期一个工作日（仅限一次）。邻居代收与安全存放处最迟可在派送前 5 分钟修改，自提点或快递柜则须在前一晚午夜前修改。

## 包裹延误、丢失、破损与欧盟海关税费

**轨迹停滞：** 跨境包裹方面，bpost 建议至少连续 5 个工作日（邻国）或 10 个工作日（英国及其他国家）无更新后，再请寄件人联系 bpost 核查。另见[物流停止更新怎么办](guide:tracking-not-updating)（英文）。

**派送行程中断：** 出现 `Delivery interrupted due to unforeseen circumstances` 说明 bpost 当日派送受阻，随后会安排新日期并显示 `New delivery scheduled`。Peek 对前一条显示“需要留意”，对后一条恢复为“运输中”。

**谁来投诉索赔：** 收件人本人无法直接向 bpost 索赔。多数商家与 bpost 签有丢件或破损协议，请联系商家申请补发或退款；只有寄件人能发起正式调查。

**包裹破损：** 请在 7 天内附照片联系寄件人；境外寄来的包裹还需同时通知 bpost。

**显示已签收但没收到：** 先询问邻居、检查信箱（小件可能直接投入而不按门铃）、核对你的派送偏好以及应用或 Track & Trace 里的安全存放处照片。bpost 也提供未收到包裹申诉表单，填写时请备好条码和收件邮编。

**欧盟外清关与税费：** 欧盟境外寄来的包裹须先完成清关，建议在 14 天内通过 My bpost 应用或 Track & Trace 预先缴费。货值 150 欧元以下的订单，bpost 列出每项商品 3 欧元进口关税以及自 2026 年 11 月 1 日起每项商品 2 欧元欧盟统一处理费（“项”指同一商品类别：两件同款 T 恤合计收 5 欧元关税加处理费，一件 T 恤加一条泳裤则合计 10 欧元），另加增值税（通常 21%）及 21.50 欧元代办费（若商家未预收增值税和关税）。超过 150 欧元则按品目征收关税、增值税、40 欧元代办费及自 2026 年 11 月 1 日起的每项 2 欧元处理费。详见 [bpost 欧盟境外网购清关说明](https://www.bpost.be/en/shopping-outside-eu)和[海关清关指南](guide:customs)（英文）。

> 欧盟境内包裹运输途中，bpost 绝不会要求付费，也绝不通过邮件或短信索要个人信息。不过对于需缴进口税费的欧盟境外包裹，bpost 确实会通过邮件、短信、My bpost 推送或信件发送缴费通知：请务必仅在 My bpost 应用、Track & Trace 官网或邮局柜台付款。可疑信息请转发至 abuse@bpost.be。

## bpost 客服电话与联系渠道

bpost 官网列出的客服渠道如下：

- **客服电话：** 英语与荷兰语页面为 02 278 51 26，法语与德语页面为 02 278 51 27；周一至周五 08:00–12:30 及 13:00–18:00，周六 09:00–13:00，按区段资费计费。
- **在线聊天：** 部分服务（含 Track & Trace）提供在线客服。
- **线下网点：** 前往 Post Office、Post Point 或 Parcel Point 柜台咨询。
- **信件（免贴邮票）：** bpost, Box 5000, 1000 Brussels。

联系前请备好条码。bpost 未公布公开客服邮箱。

## 用 Peek 查询和跟踪 bpost 包裹

对于两位字母 + 九位数字 + `BE` 且校验位正确的单号，以及 `JJBE` 后跟一位字母和 18 或 22 位数字的单号，[Peek](/) 会立刻识别为 bpost。3232 或 3299 开头的 24 位单号会同时向 bpost、奥地利邮政、InPost 和 Ciblex 询问；3232 开头的 18 位单号会询问 bpost（当第 6 至 9 位为零、与 Ecoscooting 格式重叠时也会询问 Ecoscooting）。只要 bpost 有记录，Peek 就会选中 bpost；仅当 Ecoscooting 也同时查到该 18 位单号时，才会让你点“选择”自行确定。30 位比利时国内挂号单号须先点击“更换”手动选择 bpost 才可查询。粘贴 bpost.be 或 Track & Trace 页面带 `itemCodes` 的链接也会直接按 bpost 解析。`U` 开头条码因 bpost 不提供跟踪，Peek 不会将其归为 bpost，仅建议 Chronopost。

Peek 会直接向 bpost 查询，无需填写邮编。你将看到 bpost 的英文原文轨迹、扫描地点、重量和尺寸；比利时境内扫描按比利时时间解析并换算为你设备的本地时区。包裹停放在自提点或快递柜时，Peek 会显示该自提点名称和地址，取件后也会保留。比利时境内签收的包裹会显示签收时间，退回寄件人的包裹会标记为已退回。Peek 最高每 10 分钟检查一次；在 bpost 扫描包裹进入派送的当天，只要 bpost 自己的查询系统正常响应，自当地时间早上 6 点起最高每 2 分钟检查一次（通过物流查询服务时，派送当天在打开包裹页面或开启通知时最高每 15 分钟一次）。状态变化时可推送通知。连续两天无新动态（或在“已生成面单”、“已揽收”状态下满 12 小时），Peek 会改为每小时检查一次。

Peek 不显示预计送达日期，也会隐去寄件人、收件人、收件地址、联系方式、派送偏好和签收凭证。

## bpost 查询常见问题

### bpost 是什么快递？

bpost 是 bnode 集团（原 bpostgroup）旗下负责比利时和荷兰包裹与信件派送的邮政品牌；该集团的跨境包裹品牌为 Landmark Global。

### bpost 周六或周日派送吗？

包裹有时会派送，但前提是寄件商家与 bpost 签有周末配送协议：周六 8:00 至 17:00 派送至自提点或快递柜，周日 9:00 至 19:00 送货上门。信件和挂号件仅在工作日投递。

### bpost 有在线客服吗？

有，部分服务（包括 Track & Trace）提供在线聊天，不过 bpost 未公布聊天服务时段。

### 为什么我的 bpost 包裹查不到轨迹？

通常是因为寄件人尚未把包裹交给 bpost。另外，以 `U` 开头的条码本身不带 Track & Trace 跟踪功能。

:::sources
- [bpost: What does a barcode look like and where can I find it?](https://www.bpost.be/en/faq/what-does-barcode-look-and-where-can-i-find-it) – 条码格式、查找位置与 U 开头条码
- [bpost: Track & Trace, follow a parcel](https://www.bpost.be/en/faq/can-i-follow-my-parcel-online) – 查件渠道、所需条码与跨境件
- [bpost: When can you follow a parcel with Track & Trace?](https://www.bpost.be/en/faq/when-can-i-follow-my-parcel-track-trace) – 交寄后开始跟踪
- [bpost: Parcel in transit but didn't receive a barcode](https://www.bpost.be/en/faq/i-am-expecting-parcel-have-not-received-barcode-what-do-i-do-now) – 商家邮件与联系寄件人
- [bpost: Track your shipment](https://track.bpost.cloud/) – Track & Trace 多语言与状态措辞
- [bpost: I was not home, where is my parcel now?](https://www.bpost.be/en/faq/i-was-not-home-when-postman-passed-where-my-parcel-now) – 邻居代收、安全存放处与通知单
- [bpost: Not home, can I pick up my parcel somewhere today?](https://www.bpost.be/en/faq/my-parcel-could-not-be-delivered-my-home-where-and-when-can-i-pick-it) – 次工作日可取、邮件通知
- [bpost: Missed parcel, can the postman pass by again?](https://www.bpost.be/en/faq/i-just-missed-my-parcel-can-postman-pass-again-today) – 当天不再二次上门
- [bpost: Notice in letterbox, postman will pass by a second time](https://www.bpost.be/en/faq/why-will-postman-be-passing-second-time-after-leaving-failed-delivery-notice) – 周六未妥投次工作日再派
- [bpost: Receiving a parcel in a Pick-up point](https://www.bpost.be/en/receive-parcel/pick-up-point) – 14 天和 5 天保管期、代取与挂号件规定
- [bpost: How to get a parcel out of a bbox locker?](https://www.bpost.be/en/faq/how-do-i-remove-parcel-parcel-locker) – 二维码、6 位码、蓝牙与不可延期
- [bpost: Bbox](https://www.bpost.be/en/bbox) – 24 小时开放、免账号
- [bpost: What happens to my parcel if I am not at home?](https://www.bpost.be/en/faq/what-if-i-am-not-home-when-postman-delivers-my-parcel) – 偏好设定、改址与延期
- [bpost: Can I change the time of delivery of my parcel?](https://www.bpost.be/en/faq/can-i-change-time-delivery) – 延期一个工作日一次
- [bpost: Your delivery preferences, your rules](https://www.bpost.be/en/mybpostapp-pref-parcel) – 修改截止时间
- [bpost: Receive a parcel](https://www.bpost.be/en/receive-parcel) – 14 天进口费缴费期限
- [bpost: My parcel stopped moving in Track & Trace](https://www.bpost.be/en/faq/my-parcel-seems-be-stuck-somewhere-it-has-not-moved-while-track-trace) – 5 与 10 个工作日等待期、寄件人投诉
- [bpost: How to file a complaint about a parcel](https://www.bpost.be/en/faq/how-do-i-file-complaint-if-my-parcel-damaged-or-lost-or-hasnt-been-delivered) – 收件人不可索赔、破损 7 天期限与客服时间
- [bpost: Parcel delivered, but didn't receive anything](https://www.bpost.be/en/faq/i-get-message-my-parcel-has-been-delivered-i-havent-received-anything-what-now) – 排查清单、照片与申诉表单
- [bpost: Parcel must pass through customs](https://www.bpost.be/en/receive-parcel/customs) – 预缴进口费用
- [bpost: A parcel from outside the EU?](https://www.bpost.be/en/shopping-outside-eu) – 每项 3 欧元关税与 2 欧元处理费、21.50 与 40 欧元代办费
- [bpost: Does bpost deliver on Saturday and Sunday?](https://www.bpost.be/en/faq/which-mail-or-shipments-does-bpost-deliver-saturday-and-sunday) – 周末派送时段
- [bpost: How do I contact bpost's customer services?](https://www.bpost.be/en/faq/how-do-i-contact-bposts-customer-services) – 电话、聊天、网点与 Box 5000 地址
- [bpost: Comment contacter le service clientèle de bpost ?](https://www.bpost.be/fr/faq/comment-contacter-le-service-clientele-de-bpost) – 法语客服电话 02 278 51 27
- [bpost: Phishing, received suspicious text or email?](https://www.bpost.be/en/faq/i-received-suspicious-text-message-or-email-allegedly-bpost-what-should-i-do) – 防钓鱼提示与 abuse@bpost.be
- [bnode: bpostgroup becomes bnode](https://press.bnode.com/bpostgroup-becomes-bnode) – 集团更名、bpost 品牌与 Landmark Global
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Peek 读取和显示的信息
:::
