---
title: 递四方（4PX）国际物流查询：单号格式、状态与客服
description: 查询递四方速递（4PX）跨境包裹：4PX…CN与LP…CN单号格式、英文扫描及异常标签含义、英美当地尾程派送查询及深圳客服电话邮箱。
slug: disifang-4px-wuliu-chaxun
published: 2026-11-23
updated: 2026-11-23
---

递四方速递（4PX，4PX Express Co., Ltd.）是一家创立于 2004 年、总部位于深圳宝安区的跨境物流企业，为网店卖家把包裹发往海外，并交由目的国当地快递商派送尾程。你可以把单号直接粘贴到上方查询框，或前往递四方官方全球查件网站 `track.4px.com` 查询。单号由你购物的网店或平台在订单发货信息里提供。

## 递四方（4PX）单号格式：怎么看、怎么区分

递四方网站未固定单一示例，查件框接受 5 至 50 位字母和数字。以下是常见递四方单号及易混格式（数字均为虚构）：

| 单号（虚构） | 归属与说明 |
| --- | --- |
| `4PX4829173605142CN` | `4PX` + 13 位数字 + `CN`：递四方标准自有单号。 |
| `LP4829173605142CN` | `LP` + 13 位数字 + `CN`：可能是递四方运单号。 |
| `LP48291736051428` | `LP` 加 14 位数字（不以 `CN` 结尾）：这是 [菜鸟（Cainiao）](carrier:cainiao) 速卖通参考号，不是递四方。 |
| `RR482917367GB` | [国际邮政单号](guide:tracking-number-formats)（英文），以国别代码结尾：属于对应国家邮政（此处为英国皇家邮政）。 |
| `YT4829173605142839` | 不是递四方，而是 [云途物流（YunExpress）](carrier:yunexpress)。 |

在 Peek 中粘贴时，字母大小写和空格都不影响识别。递四方结果页通常会展示两个单号：「4PX Order No.」（递四方内部单号）和「Tracking No.」（目的国当地尾程快递单号）；网店有时会直接把当地尾程单号发给你，递四方查件页也支持查询其中部分尾程单号。找不到单号请参阅 [如何找到你的快递单号](guide:find-tracking-number)（英文）。

## 递四方（4PX）物流状态是什么意思

递四方的扫描记录通常以英文生成，其查件页支持切换简体中文翻译或查看英文原文。按包裹流转顺序，常见节点如下：

| 递四方英文原文 | 对你的实际含义 |
| --- | --- |
| `Parcel information received` | 卖家已创建运单并上传预报数据，递四方尚未拿到实物包裹。 |
| `4px received shipment.` | 递四方中国仓库已实际揽收包裹。 |
| `Shipment arrived at facility and measured.` | 包裹在递四方操作中心完成称重和量方。 |
| `Hand over to airline.` | 已移交航空公司安排国际航班出境。 |
| `Arrival to the destination airport` | 航班已降落在目的国机场。 |
| `Customs clearance agent received the shipment in destnation country.` | 目的国清关代理已接货进入当地清关（`destnation` 为系统原拼写）。 |
| `Released from customs: customs cleared.` | 进口海关已放行，包裹继续转运。 |
| `In transit, it's progressing through post network` | 包裹已交由目的国当地邮政或尾程快递网络运输。 |
| `Out for delivery` | 尾程快递员正在外出派送途中。 |
| `Delivery attempt unsuccessful` | 派送尝试未成功。 |
| `Delivered, in/at mailbox` | 已投递至你的信箱或邮箱。 |

其中 `Shipping information received`、`Waybill generated`、`Shipping label created` 以及 `Advance notice - package information received.` 与第一行同义，均指仅建单未揽收；`Shipping label created, usps awaiting item` 表示美国 USPS 已提前出面单但尚未扫描到实物（`usps awaiting item` 这个结尾也可能接在较晚的记录后面）。

在明细扫描上方，递四方还会给包裹贴一个汇总标签：`receive forecast`（电子预报）、`In Transit`（从入仓到尾程转运全过程）、`Arrived for Pickup`（已到达自提点待领取）、`Exception`（异常：涵盖无人联系、地址错误、拒收、破损、遗失、超时未取、延误、首次派送失败或清关受阻）、`Suspected return`（疑似将被尾程退回）、`Trajectory stalled`（超过 15 天无新轨迹）以及 `No Data Found`（查无数据，可能是单号有误或尚未录入）。

## 递四方派送、自提与派送失败处理

递四方不直接上门投递最后一公里，包裹清关后移交给目的国尾程公司（例如英国常用 Royal Mail，美国常用 USPS）。交接完成后，递四方结果页的「Terminal Service Provider」（末端服务商）信息栏会提供当地派送公司的联系方式与其网站链接。

派送次数、通知卡、改约和代保管期限均由当地尾程快递决定，递四方不发布收件人派送条款。看到 `Arrived for Pickup` 时，递四方提醒收件人尽快领取以免被服务商退回；遇到 `Delivery attempt unsuccessful` 时，请带上「Tracking No.」按末端服务商栏的联系方式直接找当地快递重派或自提。参见 [错过派送怎么办](guide:missed-delivery)（英文） 和 [未领取包裹退件说明](guide:uncollected-parcel)（英文）。

## 递四方包裹不更新、丢失或退件怎么办

- **暂时查不到轨迹。** 查无记录时页面提示包裹暂无轨迹，请耐心等待或向商家核实发货状态；也请仔细核对单号是否抄错。
- **连续多日停滞（`Trajectory stalled`）。** 满 15 天无新扫描时，递四方会将包裹标为轨迹停滞并提示可能延误、遗失或承运商漏扫。先用「Tracking No.」去当地尾程快递网站查件，并参考 [物流为什么停止更新](guide:tracking-not-updating)（英文）。
- **显示已签收却没收到。** 先联系实际投递的当地尾程快递核实签收记录，再通知网店。详见 [显示已签收但未收到](guide:delivered-not-received)（英文）。
- **退件或丢件。** [递四方网站](https://www.4px.com/view/index.html) 没有面向收件人的理赔流程或截止期限说明，[退换货服务页](https://www.4px.com/view/return.html) 介绍的是为卖家提供的退货服务。请携带订单号与单号找网店或购物平台申请退款或重发。参见 [包裹丢失怎么办](guide:lost-parcel)（英文） 和 [包裹退回寄件人](guide:returned-to-sender)（英文）。

## 递四方（4PX）客服电话与联系方式

递四方追踪站「Contact Us」（`track.4px.com/#/help/contact`）公布的统一联系方式：

- **中国深圳电话**：`0755-23508000`（海外拨打 `+86 755 2350 8000`，未注明服务时段与支持语言）。
- **客服邮箱**：`track@4px.com`。
- **总部地址**：中国广东省深圳市宝安区福永 e 酷园区 12 栋（4PX Express Co., Ltd.）。

递四方未针对英、美、爱尔兰或其他海外国家单独设立个人收件人热线。订单问题请优先联系网店，末端派送请联系「Terminal Service Provider」栏中列出的当地快递公司。

## 用 Peek 跟踪递四方（4PX）包裹

粘贴 `4PX…CN` 单号（如虚构单号 `4PX4829173605142CN`）时，[Peek](/) 会立刻自动识别递四方；遇到 `LP…CN` 单号（如 `LP4829173605142CN`）时，Peek 会实时询问递四方后台，确认有记录后显示「4PX 有这个包裹的记录。」。也可以直接粘贴带 `#/result/0/` 路径的 `track.4px.com` 链接（哪怕里面放的是当地尾程单号）；一单多件的合单会交给第三方聚合追踪服务处理。如需手动指定，点击添加页的「更换」或详情页的「更换快递公司」，详情页还附有「向 4PX 寻求帮助」链接直达递四方联系页。

Peek 读取递四方英文轨迹并展示规范化阶段（「已生成面单」「已揽收」「运输中」「清关中」「派送中」「派送未成功」「待取件」「已签收」「需要留意」等）、带地点并标在地图上的完整历史、签收时间、目的国以及当地尾程快递与单号。只要该尾程快递在 Peek 支持范围内（例如 USPS），待其出现更新扫描后 Peek 会自动接力继续跟踪。递四方不提供预计送达日期或包裹重量。

时间方面，Peek 按递四方标注的时区（无标注时按扫描地点时钟）读取并统一换算为你设备的本地时区。只要你打开着这个包裹，或在派送当天开启了通知，Peek 最快每 10 分钟检查一次；同样条件下，包裹派送当天从当地时间早上 6 点起最快每 2 分钟一次（仅在递四方官方查询正常响应时）；其他时候每小时一次。只有第三方查询服务有结果时，Peek 每小时检查一次，最新扫描超过一天后每 3 小时一次，派送当天在你打开包裹或开启通知时最快每 15 分钟一次。包裹交给尾程快递后，Peek 按该快递的频率检查。连续两天无新扫描（或前段处于「已生成面单」「已揽收」满 12 小时）也降为每小时一次。静默满四天时 Peek 会提示「包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 4PX 查询。」，超过三周无动静则提示「已超过三周没有新动态。如果还没收到，请联系卖家。Peek 会持续向 4PX 查询。」。

通知偏好可选「每次扫描」「重要环节」或「仅派送当天」。Peek 无法替你联系递四方或卖家。

## 关于递四方（4PX）物流查询的常见问题

### 递四方（4PX）总部在哪里？

位于中国广东省深圳市宝安区。据 [递四方官方新闻](https://www.4px.com/view/newsCenterContentfive.html)，公司成立于 2004 年。

### 递四方（4PX）是正规快递公司吗？

是的。递四方是运营多年的中国跨境电商物流服务商，建有自有全球查件平台，并与 [菜鸟（Cainiao）](carrier:cainiao) 合作建有 [东莞国际智慧物流园](https://www.4px.com/view/newsCenterContentsix.html)。查件显示 `No Data Found` 时，递四方说明可能是单号有误或尚未录入，请核对单号并向卖家核实。

### `Parcel information received` 是什么意思？

表示卖家已经创建运单并把电子信息发送给了递四方，但递四方仓库尚未收到实物包裹。实物入库后会更新为 `4px received shipment.`。

### 递四方国际配送一般要多久？

递四方不向个人收件人公布统一时效，请以网店或购物平台给出的预计送达时间为准。如果连续 15 天没有任何扫描更新，递四方系统会自动将其标记为轨迹停滞（`Trajectory stalled`）。

:::sources
- [递四方：企业网站首页](https://www.4px.com/view/index.html) – 跨境电商卖家物流服务与公司简介
- [递四方：退换货服务](https://www.4px.com/view/return.html) – 面向卖家的退换货服务说明
- [递四方新闻：成都至伦敦全货机航线首航](https://www.4px.com/view/newsCenterContentfive.html) – 创立于 2004 年
- [递四方新闻：菜鸟&递四方国际智慧物流园](https://www.4px.com/view/newsCenterContentsix.html) – 与菜鸟共建东莞国际智慧物流园
- [递四方：查件网站语言文件](https://track.4px.com/js/index.6aecf0a2c58f0d7c56a3.js) – 汇总标签及其提示（15 天无轨迹、尽快领取、查无数据）、「4PX Order No.」与「Tracking No.」
- [递四方：联系我们（Contact Us）](https://track.4px.com/#/help/contact) – 电话、邮箱、地址
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – 4PX…CN 与 LP…CN 单号规则、`track.4px.com` 链接与状态映射、末端服务商信息、时区换算及自动接力尾程机制
:::
