---
title: BUFFALO 国际物流单号查询：BUFZA 轨迹、南非清关与客服
description: 查询 BUFFALO 国际物流中国至南非专线包裹：BUFZA…YQ 单号格式、南非海关增值税与关税节点、周一至周六派送时段及中非官方客服。
slug: buffalo-ex-nanfei-chaxun
published: 2026-11-24
updated: 2026-11-24
---

**BUFFALO**（*BUFFALO International Logistics*，常称 *Buffaloex* 或 *Buffalo Express*）是一家自 2017 年 4 月开始运营的中国至非洲跨境电商物流承运商（[buffaloex.com](https://www.buffaloex.com/)）。公司总部位于上海，非洲分公司设在南非约翰内斯堡，拥有 35,000 平方米综合仓储（含 6,500 平方米保税仓）和 300 辆自营配送车。BUFFALO 官网介绍其系统已与南非海关及银行系统直连，并在中国至南非跨境电商小包市场份额位居第一。你可以在 [buffaloex.com/tracks.html](https://www.buffaloex.com/tracks.html) 查询快件，也可以直接把 `BUFZA` 单号粘贴到上方查询框中。

## BUFFALO 单号格式（`BUFZA…YQ`）

通过 BUFFALO 从中国发往南非的跨境电商包裹使用 17 位条码单号，以 `BUFZA` 开头并以 `YQ` 结尾（以下均为虚构示例）：

| 单号（虚构） | 归属与说明 |
| --- | --- |
| `BUFZA6482917305YQ` | BUFFALO 标准 17 位中国至南非包裹单号（`BUFZA` + 10 位数字 + `YQ`）。 |
| `BUFZA4SK73918BUYQ` | 另一组 17 位 `BUFZA…BU…YQ` 跨境小包单号示例。 |

BUFFALO 联系页面注明，以 `BUFEX-` 开头的订单号归属南非端 ZA-BUFFALO 处理。如果遗失运单号，BUFFALO 常见问题建议向供货卖家索取或登录 BUFFALO 账号查看。详见[运单号格式说明](guide:tracking-number-formats)（英文）。

## BUFFALO 物流状态含义解析

根据 [BUFFALO 帮助与支持页面](https://www.buffaloex.com/support.html) 及 BUFFALO 追踪数据流，包裹依次经过中国入仓、出口空运、南非海关缴税、约翰内斯堡及区域分拨仓和末端派送：

| BUFFALO 轨迹原文 | 对你的含义 | Peek 显示 |
| --- | --- | --- |
| `Order Confirmed` | 订单已创建，包裹仍在卖家手中或发往 BUFFALO 中国仓库途中。 | “已生成面单” |
| `Enter Warehouse` | 包裹已抵达 BUFFALO 中国集运仓库。 | “已揽收” |
| `Out of Warehouse`、`Air Shipping`、`International Shipping` | 已从 BUFFALO 中国仓库出库，经空运或海运发往南非。 | “运输中” |
| `Clearance`、`At Destination`、`Arrival at South Africa customs`、`Customs VAT and duty can be paid now, please pay in time`、`Tax Paid`、`Clearance finished` | 正在办理中国出口清关等待起飞，或处于南非进口清关阶段；放行前须缴纳进口增值税与关税。 | “清关中” |
| `Inbound`、`AIR DC Received the parcel`、`BUFFALO Airport Warehouse received`、`Transit the parcel from AIRDC to JHBDC`、`JHB DC Received the parcel`、`Dispatching`、`WAYBILL IMPORTED`、`Transit to thirdparty Express`、`RETURNS` | 已完成清关，正在 BUFFALO 机场仓（`AIR DC`）与约翰内斯堡分拨中心（`JHB DC`）之间流转、转交南非第三方快递，或在两轮派送间隙退回分拨仓。 | “运输中” |
| `Driver Pick the Order`、`SCAN VEHICLE DEPARTURE` | 已由 BUFFALO 或当地合作快递司机装车，今天正在派送。 | “派送中” |
| `Returned to BUFFALO warehouse[...]` | 本地快递投递失败后已退回方括号所指的 BUFFALO 仓库，请按 BUFFALO 发送的短信或邮件处理，或联系客服。 | “需要留意” |
| `Client Received` | 已送达收件地址或在自提柜台签收领取。 | “已签收” |

## 南非海关增值税/关税、派送时段与改址限制

根据 [BUFFALO 帮助中心](https://www.buffaloex.com/support.html) 和 [关于 BUFFALO](https://www.buffaloex.com/about.html)：

- **进口增值税与关税：** 当轨迹提示 `Customs VAT and duty can be paid now, please pay in time` 时，需先缴清进口增值税与关税，南非海关才会放行。BUFFALO 提供在线或银行转账缴税及上传付款凭证的指引视频，其在线咨询表单也受理税费缴纳及身份证件/进口商代码（importer's code）相关咨询。详见[包裹被海关扣留怎么办](guide:customs)（英文）。
- **派送时间与参考时效：** BUFFALO 在南非的派送时间为**周一至周六 08:00 至 17:00**（周日及法定节假日不派送）。轨迹进入在途阶段后，BUFFALO 预计在 **1–5 天**内送达，具体视收件地址而定。
- **修改地址、电话或申报价值限制：** 按 BUFFALO 常见问题说明，包裹一旦进入中国国内中转仓，申报价值、数量和 HS 编码**不可**修改；包裹抵达南非后，收件地址与联系电话同样**不可**更改。

## BUFFALO 包裹延误、退仓或遗失怎么办

- **轨迹暂时没有更新：** BUFFALO 常见问题说明包裹正在运输途中、尚未到达下一扫描站点，具体详情可向客服核查。请参阅[为什么物流停止更新](guide:tracking-not-updating)（英文）。
- **傍晚出现 `RETURNS` 扫描：** 在第三方追踪服务转发的本地快递扫描中，`RETURNS` 有时会出现在 `SCAN VEHICLE DEPARTURE` 当天傍晚，表示包裹结束当日派送返回分拨仓并在次日早晨再次出发；而 `Returned to BUFFALO warehouse[...]` 则代表投递失败退仓，需要你及时跟进。
- **显示 `Client Received` 却未收到：** BUFFALO 建议先向家人、邻居以及小区/大楼物业保安核实，仍未找到再联系南非客服邮箱（`support@buffaloex.com`）。详见[显示已签收但未收到](guide:delivered-not-received)（英文）、[包裹丢失](guide:lost-parcel)（英文）、[包裹破损](guide:damaged-parcel)（英文）和[包裹退回寄件人](guide:returned-to-sender)（英文）。

## BUFFALO 官方客服与联系方式

以下官方联络信息发布于 [BUFFALO 联系我们](https://www.buffaloex.com/contact.html)：

- **南非客服：** 邮箱 `support@buffaloex.com`，电话 `+27-10-510-9307`，WhatsApp `+27-67-758-6614`。
- **中国客服邮箱与热线：** `service@bufflogistics.com`，`+86-400-921-9066`。
- **在线咨询表单与状态说明：** [buffaloex.com/contact.html](https://www.buffaloex.com/contact.html) 与 [buffaloex.com/support.html](https://www.buffaloex.com/support.html)。
- **办公地点：** 上海总部（Room 301, Building A, Lane 1156, Shenbin South Road, Minhang District, Shanghai）、广州办事处（Room 401, Building 3, Site 5, Greenland Airport International Center, Huadu District, Guangzhou）以及南非约翰内斯堡分公司。

> 请警惕冒充南非海关或 BUFFALO、附带仿冒网址的诈骗短信。付款前请务必先在 [buffaloex.com/tracks.html](https://www.buffaloex.com/tracks.html) 核对真实物流状态与应付税费金额。详见[快递诈骗短信识别指南](guide:delivery-scam-texts)（英文）。

## 使用 Peek 追踪 BUFFALO 包裹

在 [Peek](/) 输入 17 位 `BUFZA…YQ` 单号（例如虚构单号 `BUFZA6482917305YQ` 或 `BUFZA4SK73918BUYQ`）时，BUFFALO 是唯一匹配候选，Peek 会自动识别；同时也能直接识别粘贴的 `buffaloex.com/tracks.html?order=`、`buffaloex.com/track.html?order=` 和 `buffaloex.com/express/record?order=` 追踪链接。如需手动选择，可在添加时点击“更换”或在包裹页点击“更换快递公司”（搜索“Buffaloex”、“Buffalo Express”或“BUFFALO International”均可找到）。

Peek 目前没有 BUFFALO 直连查询接口，而是通过第三方聚合追踪服务跟进 BUFFALO 包裹，并按 BUFFALO 专属状态规则解析扫描文本——因此傍晚的 `RETURNS` 会保持“运输中”，`SCAN VEHICLE DEPARTURE` 显示“派送中”，`Returned to BUFFALO warehouse[...]` 显示“需要留意”——同时呈现每条扫描对应的阶段（“已生成面单”、“已揽收”、“运输中”、“清关中”、“派送中”、“需要留意”、“已签收”）。

在通过聚合追踪服务跟进期间，Peek 每小时检查一次；超过一天无新扫描后降为每 3 小时检查一次；在包裹“派送中”当天，在你打开包裹页面或开启提醒时最高每 15 分钟检查一次。

当最新扫描满四天且包裹仍停留在“已生成面单”、“已揽收”或“运输中”时，Peek 会标出该扫描日期（如“自10月12日周一起没有物流更新”），并在页面提示“包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 BUFFALO 查询。”，附带指向 BUFFALO 联系页面的“向 BUFFALO 寻求帮助”链接。若超过三周没有新扫描，则显示“已超过三周没有新动态。如果还没收到，请联系卖家。Peek 会持续向 BUFFALO 查询。”

你可以自由选择“每次扫描”、“重要环节”或“仅派送当天”通知预设。Peek 仅监控物流更新，无法代你缴纳南非 SARS 关税、提交进口商代码或代为联系 BUFFALO。

## 关于 BUFFALO 物流追踪的常见问题

### BUFFALO 运单号是什么格式？

中国发往南非的 BUFFALO 运单号为 17 位字符，以 `BUFZA` 开头、以 `YQ` 结尾——例如虚构单号 `BUFZA6482917305YQ` 或 `BUFZA4SK73918BUYQ`。

### BUFFALO 在南非什么时间派送？

根据 [buffaloex.com/support.html](https://www.buffaloex.com/support.html)，BUFFALO 派送时间为**周一至周六 08:00 至 17:00**（周日及公众假期除外），包裹显示在途后预计 **1–5 天**内送达。

### 为什么我的 BUFFALO 包裹停在南非海关？

如果轨迹显示 `Customs VAT and duty can be paid now, please pay in time`，说明需要缴清进口增值税与关税后南非海关才会放行。关于税费支付及身份证号/进口商代码的问题，可通过 BUFFALO 在线咨询表单提交。

### 如何联系 BUFFALO 南非客服？

可将你的 `BUFZA…YQ` 单号发邮件至 `support@buffaloex.com`，或通过 [buffaloex.com/contact.html](https://www.buffaloex.com/contact.html) 提交工单（中国客服热线 `+86-400-921-9066`）。

:::sources
- [BUFFALO 国际物流官网](https://www.buffaloex.com/) – 中国至非洲跨境电商物流网络
- [关于 BUFFALO](https://www.buffaloex.com/about.html) – 2017 年 4 月起运营、上海总部、约翰内斯堡分公司、35,000 m² 仓库（含 6,500 m² 保税仓）、300 辆配送车及海关银行系统对接
- [BUFFALO 支持与常见问题](https://www.buffaloex.com/support.html) – 官方状态含义（`Order Confirmed` 至 `Client Received`）、周一至周六 08:00–17:00 派送时段、1–5 天在途时效及修改地址截止规则
- [BUFFALO 联系我们](https://www.buffaloex.com/contact.html) – `support@buffaloex.com`、`service@bufflogistics.com`、`+86-400-921-9066` 及办公仓储地址（Peek 帮助链接指向此页）
- [BUFFALO 包裹查询](https://www.buffaloex.com/tracks.html) – 官方运单追踪查询页
- [Universal Parcel Scraper: buffalo-ex](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/buffalo-ex) – `BUFZA…YQ` 识别规则、`buffaloex.com` 链接规则、状态词典（`RETURNS`、`SCAN VEHICLE DEPARTURE`、`Returned to BUFFALO warehouse`）、第三方聚合追踪路由及帮助链接
:::
