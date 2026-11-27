---
title: One by Allegro CZ（WE|DO）捷克物流查询：单号与自提规则
description: 查询捷克 One by Allegro CZ（WE|DO）包裹：了解 11 位单号、One Box 48 小时取件、One Point 5 天保管与客服联系。
slug: one-by-allegro-cz-tracking
published: 2026-11-27
updated: 2026-11-27
---

One by Allegro CZ 是 Allegro 集团旗下的捷克派送公司（官网 [onedelivery.cz](https://onedelivery.cz/) 归属 Allegro Retail a.s.）。它为 allegro.cz 及其他网店提供三种派送方式：**One Box**（5,244 组以上自助快递柜，含合作的 AlzaBox 与 OX Box）、**One Point**（1,960 家合作自提点）以及 **One Courier**（350 辆以上配送车与快递员覆盖捷克全境的送货上门服务）。在 Peek 承运商搜索中输入 WE|DO、IN TIME 或 Uloženka 同样可以找到它。

你可以在 [onedelivery.cz/sledovani-zasilky](https://onedelivery.cz/sledovani-zasilky) 上查询捷克包裹，或直接把 11 位追踪单号粘贴到上方输入框。

## One by Allegro CZ（WE|DO）追踪单号格式

捷克 One by Allegro / WE|DO 包裹采用紧凑的 **11 位字母数字编码**：

| 格式（虚构示例） | 说明 |
| --- | --- |
| `2BM48291736` | 1 位数字 + 1 位字母 + 1 位字母/数字 + 8 位数字（共 11 位）——标准 One by Allegro CZ（WeDo）追踪单号。 |
| `48P48291736` | 2 位数字 + 1 位字母 + 8 位数字（共 11 位）——捷克 One by Allegro（WeDo）另一种常见格式。 |

以 `7D` 加数字或 `7X` 加数字开头的前缀排除在外；其余所有符合条件的 11 位单号都能毫无歧义地高置信度识别为 **One by Allegro CZ**。Peek 同样支持解析 `onedelivery.cz/sledovani-zasilky?numer=...`、`onedelivery.cz/?numer=...`、`trace.wedo.cz/index.php?orderNumber=...` 以及 `trace.intime.cz/index.php?orderNumber=...` 链接。波兰的 Allegro One 使用独立追踪系统，请参阅 [Allegro One](carrier:allegro-one) 与 [追踪单号格式指南](guide:tracking-number-formats)（英文）。

## One by Allegro CZ 物流状态含义解析

One by Allegro CZ 公开追踪接口返回英文描述，每条均带时间和 UTC 偏移量，常见如下：

| One by Allegro CZ 原文 | 含义说明 |
| --- | --- |
| `Parcel has been prepared by the sender` | 商家已创建运单标签，快递员尚未揽收包裹。 |
| `Parcel has been picked up by the courier` | One by Allegro CZ 快递员已从寄件人处揽收包裹。 |
| `Parcel has been dispatched to its destination point` | 已离开始发枢纽，发往目的地区或自提网络。 |
| `Parcel has been accepted at the sorting center` | 已在 One by Allegro 分拣中心处理。 |
| `Parcel has been accepted at the branch` | 已抵达负责你地址或自提点的当地派送网点。 |
| `Parcel has been released for delivery` | 今天已装车，准备送货上门或投递至你选定的 One Box / One Point。 |
| `Parcel is awaiting pick-up` | 已到达 One Box 快递柜或 One Point 自提点等待领取（查看短信、邮件或应用获取取件 PIN 码）。 |
| `Parcel has been delivered` | 已当面签收，或已从快递柜/自提点取走。 |

若接口返回可能包含收件人个人信息的非标准文本，Peek 会将该行替换为 `Tracking update` 并保留原时间戳。

## One Box、One Point 与 One Courier 派送规则

One by Allegro CZ 在 [onedelivery.cz](https://onedelivery.cz/) 公布了清晰的取件时限与派送规则：

- **One Box 快递柜（[onedelivery.cz/one-box](https://onedelivery.cz/one-box)）：** 5,244 组以上快递柜（含 One Box、AlzaBox 和 OX Box）**24/7 全天开放**。投柜后须在 **48 小时**内输入收件人手机号及通过短信、邮件、One by Allegro 或 Allegro 应用收到的数字 PIN 码取件（屏幕取件无需安装应用）。货到付款（*dobírka*）可通过通知里的付款链接或快递柜刷卡终端支付。
- **One Point 自提点（[onedelivery.cz/one-point](https://onedelivery.cz/one-point)）：** 1,960 家合作门店在营业时间内常规保管 **5 个工作日**（可通过客服免费延长最多 **3 个工作日**；圣诞前高峰期保管期从 5 天缩短为 **3 天**，One Box 仍为 48 小时）。One Point 单件上限为 **20 公斤**、最长边 **1.2 米**、体积 **0.2 立方米**。货到付款支持现金或（门店备有刷卡机时）刷卡。
- **One Courier 送货上门（[onedelivery.cz/one-courier](https://onedelivery.cz/one-courier)）：** 标准时效为揽收后**次日工作日（`D+1`）**，派送时间为**工作日 08:00 至 18:00**，送至首道可上锁大门处（快递员无义务搬运上楼，不支持邮政信箱）。派送当天通过短信、邮件或应用通知。货到付款支持现金（请备零钱）或免费刷卡。可通过 One by Allegro 应用或客服改期或改投自提点，修改地址须联系客服。

错过取件期限或找不到包裹可参阅 [快递柜取件指南](guide:parcel-lockers)（英文）、[无人领取的包裹](guide:uncollected-parcel)（英文） 和 [物流停止更新怎么办](guide:tracking-not-updating)（英文）。捷克其他常见快递网络可对比 [Packeta](carrier:packeta)、[PPL](carrier:ppl) 与 [捷克邮政（Česká pošta）](carrier:ceska-posta)。

## 使用 Peek 追踪 One by Allegro CZ

将 11 位单号或 `onedelivery.cz` / `trace.wedo.cz` 链接粘贴到 Peek，即可立即识别为 **One by Allegro CZ**。如需改选承运商，请在添加时点**更改**或在追踪页点**更换承运商**。

Peek 通过开源的 [universal-parcel-scraper One by Allegro CZ 适配器](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/wedo) 直接查询公开追踪接口（`edge.onedelivery.cz`），网络超时自动重试，精确保留带时区偏移的时间戳与签收时间（`delivered_at`），并丢弃可能泄露个人隐私的自由文本备注。公开接口不返回预计送达日、网点城市或包裹重量。

Peek 将状态映射为：**已创建运单**（`Parcel has been prepared by the sender`）、**承运商已揽收**（`Parcel has been picked up by the courier`）、**运输中**（发出、分拣中心与网点扫描）、**派送中**（`Parcel has been released for delivery`）、**待取件**（`Parcel is awaiting pick-up`）和**已送达**（`Parcel has been delivered`），同时支持 `Parcel has been returned to the sender` 等其他 Allegro One 措辞。

当你打开包裹，或在开启通知的情况下进入派送日时，只要官方追踪接口正常响应，Peek 最高每 10 分钟检查一次；在显示 `Parcel has been released for delivery` 当天，自捷克时间早上 6 点起每 2 分钟检查一次。其余时间每小时一次；在“已创建运单”或“承运商已揽收”下满 12 小时或连续两天无新动态时改为每小时一次，在“已创建运单”下满七天改为每 6 小时一次。若官网无响应而改由综合追踪服务跟进，Peek 每小时检查一次，静默满一天后每 3 小时一次，派送当天最高每 15 分钟一次。

若包裹处于“已创建运单”、“承运商已揽收”或“运输中”且最新扫描满四天，Peek 会按该扫描日期标示并提示“包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 One by Allegro CZ 查询。”，同时附上官方联系页面链接；超过三周无更新时会建议联系卖家并继续查询。

你可在 Peek 通知设置中选择**每次扫描**、**重要节点**或**仅派送和送达**。

## 联系 One by Allegro CZ

- **包裹咨询表单与客服：** [onedelivery.cz/kontakt](https://onedelivery.cz/kontakt)（点击 *Dotaz na zásilku* 咨询买卖家包裹问题、找回取件 PIN 码、申请 One Point 免费延期 3 天或修改地址）
- **官方追踪页：** [onedelivery.cz/sledovani-zasilky](https://onedelivery.cz/sledovani-zasilky)
- **服务说明：** [One Box](https://onedelivery.cz/one-box)、[One Point](https://onedelivery.cz/one-point) 与 [One Courier](https://onedelivery.cz/one-courier)
- **官方网站：** [onedelivery.cz](https://onedelivery.cz/)

## 常见问题

### WE|DO、IN TIME 和 One by Allegro CZ 是同一家承运商吗？

Peek 将它们视为同一承运商：搜索 WE|DO、IN TIME 或 Uloženka 均可找到 One by Allegro CZ，且 `trace.wedo.cz`、`trace.intime.cz` 链接及 11 位单号都归在 One by Allegro CZ 下查询。`onedelivery.cz` 官网本身未详述品牌沿革。

### 包裹在 One Box 或 One Point 会保管多久？

**One Box**（含 AlzaBox 与 OX Box）投柜后保管 **48 小时**。**One Point** 自提点常规保管 **5 个工作日**（可通过客服免费延长最多 3 个工作日），圣诞前高峰期缩短为 **3 天**。

### 货到付款（dobírka）可以刷卡吗？

可以。One Courier 司机支持现金（请备零钱）或免费刷卡；One Box 支持通过通知中的付款链接或快递柜刷卡终端支付；One Point 支持现金或在门店配有机具时刷卡。

### 弄丢了 One Box 或 One Point 取件码怎么办？

取件码会通过短信、邮件以及 One by Allegro 与 Allegro 应用发送。如果都找不到，请通过 [onedelivery.cz/kontakt](https://onedelivery.cz/kontakt) 的包裹咨询表单联系客服。

:::sources
- [One by Allegro CZ (onedelivery.cz)](https://onedelivery.cz/)
- [One by Allegro CZ — Sledování zásilky](https://onedelivery.cz/sledovani-zasilky)
- [One by Allegro CZ — One Box](https://onedelivery.cz/one-box)
- [One by Allegro CZ — One Point](https://onedelivery.cz/one-point)
- [One by Allegro CZ — One Courier](https://onedelivery.cz/one-courier)
- [One by Allegro CZ — Kontakt](https://onedelivery.cz/kontakt)
- [universal-parcel-scraper One by Allegro CZ adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/wedo)
:::
