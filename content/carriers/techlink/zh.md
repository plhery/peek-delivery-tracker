---
title: 智禾国际（Techlink）物流查询：ZHPHD单号与状态
description: 查询深圳智禾国际物流（Techlink）跨境包裹：ZHPHD…ML19位单号规则、中英文状态与停更说明、美意澳尾程快递商及售后渠道。
slug: zhihe-techlink-wuliu-chaxun
published: 2026-11-23
updated: 2026-11-23
---

智禾国际物流（Techlink，全称智禾国际物流（深圳）有限公司，Zhihe International Logistics）是一家位于深圳的中国跨境物流企业，为亚马逊、沃尔玛、TK 以及欧美独立站卖家承运直发欧洲与美洲的小包专线。智禾的运单号以 `ZHPHD` 开头、以 `ML` 结尾——你可以在智禾查件页 `www.techlinksz.cn/trajectory` 查询，也可以直接把单号粘贴到上方查询框。单号由发货卖家在发货邮件或订单详情里提供。

## 智禾国际单号格式：19 位 `ZHPHD…ML`

智禾官网未明文发布单号规则，实际运单号固定为 19 位字符：`ZHPHD` + 6 位日期（`YYMMDD`）+ 6 位数字 + `ML`。以下示例数字均为虚构：

| 单号（虚构） | 格式说明 |
| --- | --- |
| `ZHPHD260814482917ML` | `ZHPHD` + 有效日期（如 `260814`）+ 6 位数字 + `ML`：标准智禾国际运单号。 |
| `ZHPHD261314482917ML` | 月份 `13` 不是合法日期：不符合智禾格式，请检查是否抄错数字。 |
| `RR482917367CN` | 2 位字母 + 9 位数字 + `CN`：[中国邮政](carrier:china-post) 单号，不是智禾单号。 |

在 Peek 中粘贴时，字母大小写和空格都不会影响识别。如果手里是其他格式单号，请参阅 [快递单号格式指南](guide:tracking-number-formats)（英文）；找不到单号请参阅 [如何找到你的快递单号](guide:find-tracking-number)（英文）。

## 智禾国际物流状态是什么意思

智禾查件页单次最多可查 40 个单号（换行或逗号分隔），哪怕切换英文菜单，正文仍以中文展示：每行显示最新一条扫描、总计「条轨迹」以及起运与目的国，点击行或「展开全部」可查看完整时间与地点。单号前的彩色图标概括当前阶段（仓储、在途/派送、绿色妥投、红色异常、问号未查到）。

智禾每条轨迹同时带有英文和中文，以下按常见流转顺序列出：

| 智禾国际原文 | 对你的实际含义 |
| --- | --- |
| `Parcel electronic information received`（收到电子信息） | 卖家已向智禾发送电子订单数据，智禾仓库尚未收到实物。 |
| `Parcel has been received and processed at original facility`（仓库入库） | 包裹已在智禾中国仓库扫描入库。 |
| `Parcel has been packed and is ready for outbound shipping`（包裹装箱） | 已装袋封箱，准备发往海外专线。 |
| `Departed from the original facility` | 包裹已离开智禾中国始发仓库。 |
| `Export customs declaration completed` | 中国出口报关申报已完成（在出仓后记录，不是目的国清关，也未扣关）。 |
| `Parcel is in transit to origin airport` | 正在中国境内由仓库运往起运机场。 |
| `Depart from the original airport` | 国际航班已从中国起运机场起飞。 |
| `Arrive at the destination airport` | 航班已降落在目的国机场，接下来进入进口清关。 |
| `Shipment Received at Facility` | 落地后已到达目的国中转枢纽。 |
| `Your shipment has arrived at sorting center` | 已到达目的国分拣中心。 |
| `Your shipment has been processed at sorting center and ready for shipping` | 目的国分拣中心已完成分拣，准备下一段转运。 |
| `Your shipment has departed from the transit origin` | 已离开目的国分拣中心发往下一站。 |
| `In transit, it is currently in transit to the next facility` | 正在目的国两个设施之间转运。 |
| `The parcel has been successfully picked up and is being prepared for transportation.` | 发往澳大利亚的包裹：航班落地后由 J&T Express Australia 接手提货（原文末尾有时附带 J&T 客服电话）。 |
| `The parcel is dispatched from the transit center to the next destination` | J&T Express Australia 扫描：已离开澳洲中转中心。 |
| `The parcel arrives at the distribution center and is prepared for transport to the next destination.` | J&T Express Australia 扫描：已到达澳洲分拨中心准备下一程。 |

在 Peek 中，第一条归为「已生成面单」，第二条归为「已揽收」，其余均归为「运输中」。若智禾系统查无记录，页面会显示「查询不到轨迹」「未知 → 未知」和「暂无最新轨迹」。

## 智禾国际包裹在目的国由谁派送

智禾官网介绍其采用「中国空运出口 + 2023 年设立的海外自有清关公司清关 + 目的国自有账号尾程派送」的门到门模式，但官网与查件页均不写明合作尾程快递名称。在 Peek 读取的真实轨迹中可见：意大利线路常见转交 iMile（扫描以【网点名】开头，如 `【Milan center】Parcel shipped to【VEN 07】.`）；澳大利亚线路航班落地后交给 J&T Express Australia；美国部分线路交由 YWE 派送（同步 `Parcel departed from Facility.`、`Parcel received by local Facility.` 直到 `Parcel is out for delivery.` 或派送失败）。

智禾后台数据有时包含目的国尾程快递的单号（但页面上没有展示该栏，也不写公司名）。派送次数、通知卡和自提保管期限完全由当地尾程快递决定，智禾不发布任何收件人派送条款。此外，智禾门到门服务同时提供包税（DDP）与不包税（DDU）两种模式，是否需要由收件人缴纳进口税费取决于卖家订舱选项，详情见 [包裹海关扣留与清关指南](guide:customs)（英文）。

## 智禾国际包裹不更新、查不到或没收到怎么办

- **只停在 `Parcel electronic information received`。** 卖家刚下单建单，实物还未到达智禾中国仓库；过几天仍无入库记录请联系卖家确认发货。
- **卡在 `Arrive at the destination airport` 或目的国分拣中心。** 落地后的进口清关和移交尾程常常出现数日空窗，而且智禾查件页很多时候只更新到目的国枢纽或分拣转运（部分线路同步到当地派送中或派送失败）。若持续停更，请向卖家索取目的国尾程快递商与单号。另见 [物流为什么停止更新](guide:tracking-not-updating)（英文） 和 [中国直邮包裹跟踪指南](guide:tracking-from-china)（英文）。
- **查不到轨迹。** 请核对单号是否正好 19 位（`ZHPHD` + 6 位合法日期 + 6 位数字 + `ML`），或确认卖家是否刚刚建单尚未入库。
- **丢失、破损或显示已签收但没收到。** 智禾未向个人收件人开放理赔通道，请直接联系卖家或购物平台处理退款或重发。参见 [包裹丢失怎么办](guide:lost-parcel)（英文）、[显示已签收但未收到](guide:delivered-not-received)（英文） 以及 [亚马逊订单追踪指南](guide:amazon-orders)（英文）。

## 智禾国际客服与联系方式

智禾官网 `www.techlinksz.cn` 未列出任何电话号码、客服邮箱或办公地址，页面上的「联系我们」链接无跳转目标，也没有在线聊天或表单。有关订单配送或售后问题，请直接联系发货卖家；获知目的国尾程快递后，末端派送请直接联系当地快递商。

## 用 Peek 跟踪智禾国际包裹

粘贴 19 位 `ZHPHD…ML` 单号（如虚构单号 `ZHPHD260814482917ML`）后，[Peek](/) 会按单号格式立即认出智禾国际（Peek 目录中没有其他快递使用这种格式；智禾查件页网址不含单号，请直接粘贴单号本身；如需手动调整可点添加页的「更换」或详情页的「更换快递公司」）。

Peek 直接读取智禾官方后台，展示标准阶段（「已生成面单」「已揽收」「运输中」「派送中」「派送未成功」等）、英文扫描原文、扫描所在国家与目的国。时区方面，中国扫描及以【】网点名开头的境外扫描按北京时间读取，澳大利亚 J&T Express Australia 节点按悉尼时间读取，其他节点按所在国时间读取，统一换算为你设备的本地时区显示。智禾不提供预计送达日期、重量或渠道名。

当智禾后台记录隐藏附带了目的国尾程单号时，Peek 会按单号格式自动识别——若为国际邮政 S10 单号则询问目的国邮政，若能唯一匹配某家 Peek 支持的当地快递也会同步查询，一旦当地快递出现不早于智禾最新记录的扫描即无缝接力切换，同时保留 `ZHPHD` 原单号与尾程单号。

只要你打开着这个包裹，或在派送当天开启了通知，Peek 最快每 10 分钟检查一次；同样条件下，包裹派送当天从当地时间早上 6 点起最快每 2 分钟一次（仅在智禾官方查询正常响应时）；其他时候每小时一次。连续两天无新扫描（或前段处于「已生成面单」「已揽收」满 12 小时）也降为每小时一次。最新扫描已满四天、包裹仍处于「已生成面单」「已揽收」或「运输中」时，Peek 会用这条扫描的日期标出包裹，如「自10月12日周一起没有物流更新」，包裹页随后提示「包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 Techlink 查询。」，超过三周无动静则提示「已超过三周没有新动态。如果还没收到，请联系卖家。Peek 会持续向 Techlink 查询。」。

通知偏好可选「每次扫描」「重要环节」或「仅派送当天」。Peek 无法替你联系智禾国际或卖家。

## 关于智禾国际（Techlink）物流查询的常见问题

### 智禾国际（Techlink）是什么公司？

全称智禾国际物流（深圳）有限公司，位于中国深圳，专为亚马逊、沃尔玛、TK 和独立站卖家承运发往欧洲与美洲的跨境小包专线，官网为 `www.techlinksz.cn`。

### `ZHPHD` 开头的是什么快递单号？

是智禾国际物流的专属 19 位运单号：`ZHPHD` + 6 位日期（年月日）+ 6 位数字 + `ML`。可以直接粘贴到上方 Peek 查询框，或在 `www.techlinksz.cn/trajectory` 查询。

### 智禾国际包裹在收件国由哪家快递派送？

智禾官网不固定公布尾程商；历史轨迹显示意大利常见 iMile，美国可见 YWE，澳大利亚由 J&T Express Australia 接手。若 Peek 未自动识别出尾程单号，请直接询问卖家。

### 为什么我的智禾国际轨迹停在目的国机场或分拣中心就不动了？

智禾查件后台的记录有时在航班落地（`Arrive at the destination airport`）或目的国枢纽中转后就不再继续回传尾程扫描，后续动态会记录在当地尾程快递官网上。请向卖家索取尾程快递商与单号，超期未到及时联系卖家处理。

:::sources
- [智禾：智禾官网（首页）](http://www.techlinksz.cn/) – 智禾国际物流(深圳)有限公司、欧美专线小包、亚马逊/沃尔玛/TK/独立站、DDU/DDP 门到门、2023 年成立海外本土清关公司、目的地派送账号、无目标的「联系我们」链接
- [智禾：轨迹跟踪](http://www.techlinksz.cn/trajectory) – 单次 40 个单号、中文页面、国家与状态图标、查无记录时的显示
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – 19 位 `ZHPHD…ML` 日期校验规则、中英文与 iMile/YWE/J&T Australia 节点映射、时区换算及隐藏尾程单号自动接力机制
:::
