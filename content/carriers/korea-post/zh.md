---
title: Korea Post 韩国邮政追踪：EMS KR 单号、13 位包裹与客服
description: 追踪韩国邮政（Korea Post / ePOST）包裹：KR 结尾国际单号与 13 位境内单号、EMS 与韩语状态含义、清关交接及客服电话。
slug: korea-post-tracking
published: 2026-11-26
updated: 2026-11-26
---

韩国邮政（Korea Post，韩文 *우정사업본부*，线上门户 *ePOST* / *인터넷우체국*）是韩国科学技术信息通信部下属的国家邮政运营商，负责韩国全境的国内包裹与挂号信派送，并运营通达 100 多个目的地的国际 **EMS**（特快专递，另通过 EMS Premium 覆盖 70 多个国家）、**K-Packet** 以及国际挂号航空函件与包裹。你可以在 [Korea Post 英文 EMS 追踪页](https://trace.epost.go.kr/xtts/tt/epost/ems/ems_eng.jsp)查询国际邮件，在 [ePOST 国内追踪页](https://service.epost.go.kr/iservice/usr/trace/usrtrc001k01.jsp)查询韩国境内包裹，或直接将单号粘贴到上方输入框中。

## Korea Post 追踪单号：KR 结尾 S10 与 13 位境内格式

请查看邮局柜台收据、韩国网店订单详情或发货确认邮件。根据国际寄递还是韩国境内寄递，韩国邮政主要使用两类单号格式：

| 格式（虚构） | 说明 |
| --- | --- |
| `EM482917367KR` | 国际 **EMS** 商品类快件（以 `KR` 结尾的 UPU S10 格式；文件类通常以 `EE` 开头）。 |
| `RR482917367KR` | 国际挂号邮件或 K-Packet 货件（UPU S10 格式：2 位字母 + 9 位含校验码数字 + `KR`）。 |
| `6482917364058` | 13 位韩国国内挂号信或包裹单号（以数字 `1`–`6` 开头）。与 Correos de Chile、[Delhivery](carrier:delhivery)、[Econt](carrier:econt)、Paquetexpress 等承运商共享 13 位纯数字格式。 |

根据 [Korea Post EMS 服务概览](https://ems.epost.go.kr/front.Introduction01.postal)，`EE` 前缀用于 EMS 文件（无需商品海关查验），而 `EM` 前缀用于 EMS 商品包裹。如需了解其他国际邮政单号格式，请参阅[单号格式指南](guide:tracking-number-formats)（英文）。

## Korea Post 追踪状态含义

以 `KR` 结尾的国际邮件显示英文 UPU/EMS 扫描记录，保留各国邮政机构上报的当地时间；13 位国内包裹显示韩语扫描标签，采用韩国标准时间（`Asia/Seoul`，全年 UTC+9）：

| Korea Post 显示状态（国际 / 国内） | 对你的含义 | Peek 显示阶段 |
| --- | --- | --- |
| `운송장출력` *（国内）* | 已打印运单面单，尚未交寄至邮局。 | “已生成面单” |
| `Posting/Collection` / `접수`、`집하완료`、`인수완료` | 已在韩国邮局柜台收寄或由韩国邮政快递员揽收。 | “已揽收” |
| `Arrival at outward office of exchange`、`Ready for dispatch`、`Handed over to a transport company`、`Received by Air carriers`、`Departure from the Airport`、`Arrival at Destination Airport`、`Delivered to Destination Post`、`Arrival at inward office of exchange`、`Departure from inward office of exchange` / `발송`、`도착` | 已在韩国国际互换局（如仁川）处理、空运出境、移交目的地国家邮政或在韩国境内分拣中心间转运。 | “运输中” |
| `Ready for customs clearance` | 已提交至目的地国海关查验（或进出口清关检查）。 | “清关中” |
| `배달준비` *（国内）* | 当地韩国邮局投递员已准备最终派送。 | “派送中” |
| `Unsuccessful delivery` | 目的地派送尝试未能完成。 | “派送未成功” |
| `Delivery complete` / `배달완료` | 已送达；Korea Post 追踪页面有时会注明签收人姓名。 | “已签收” |

## Korea Post EMS 时效、清关与目的地邮政交接

根据 [Korea Post EMS 服务概览](https://ems.epost.go.kr/front.Introduction01.postal)、[各国寄递条件](https://ems.epost.go.kr/front.EmsApplyGoCondition.postal)及[预计送达天数与资费计算器](https://ems.epost.go.kr/front.EmsDeliveryDelivery09.postal)：

- **EMS 运输时效：** 对于中午前在首尔交寄的文件类快件（`EE` 单号），韩国邮政标注发往东京、香港、新加坡等直航城市为次日达或 3–4 天，其他地区为 4–6 天；其服务概览页面对上午在首尔交寄的文件标注为 2–3 天和 3–5 天。如在午后或首尔以外交寄，或收件地址不在目的地互换局所在城市，需增加 1 天；从山区或海岛邮局交寄则需额外加上运抵国际邮件物流中心的天数。商品类包裹（`EM` 单号）还需计入目的地海关查验时间。交寄当日及公共节假日不计入内。
- **报关单证：** 文件与印刷品使用 CN22 报关单，商品及应税物品需使用 CN23 报关单（商业货件另需商业发票及相关许可证）。禁限寄物品目录详见 [ePOST 禁限寄指南](https://ems.epost.go.kr/front.Introduction04.postal)。
- **目的地邮政交接：** 当 `KR` 邮件抵达收件国（`Delivered to Destination Post` / `Arrival at inward office of exchange`）后，将由你所在国家的邮政机构（例如 [USPS](carrier:usps)、[Royal Mail](carrier:royal-mail)、[La Poste](carrier:la-poste)、[DHL](carrier:dhl)、[Japan Post](carrier:japan-post) 或 [Australia Post](carrier:australia-post)）依据当地规则负责代收关税、上门投递及邮局招领。建议同时用同一 `...KR` 单号在当地邮政网站查询更完整的派送选项。详见[包裹滞留海关指南](guide:customs)（英文）。

## Korea Post 包裹延误、停滞或丢失怎么办

- **`Departure from the Airport` 后多日无更新：** 国际 EMS 和 K-Packet 邮袋离开韩国后，通常需数天至一周以上才会由目的地国家邮政在入境互换局扫码（`Arrival at inward office of exchange`）。详见[为何物流停止更新](guide:tracking-not-updating)（英文）。
- **停留在 `Ready for customs clearance`：** 请用同一 `...KR` 单号在目的地国家邮政网站查询，确认是否因未缴纳进口增值税、关税或清关手续费而暂扣。
- **丢失、损坏或内件缺失：** 网购订单请先联系韩国卖家或交易平台；ePOST 支持在线或书面提交查单申请（*행방조사청구*），并公布了赔偿规定（*손해배상규정*）。另请参阅[显示已签收但未收到包裹](guide:delivered-not-received)（英文）、[包裹损坏处理指南](guide:damaged-parcel)（英文）、[包裹丢失处理指南](guide:lost-parcel)（英文）与[包裹退回发件人](guide:returned-to-sender)（英文）。

## Korea Post 客服与联系方式

以下官方渠道发布于 [ePOST 客户中心](https://www.epost.go.kr/bbs.RetrieveBbsMain.comm)、[ePOST 主门户](https://www.epost.go.kr/main.retrieveMainPage.comm)及 [EMS 服务概览](https://ems.epost.go.kr/front.Introduction01.postal)：

- **韩国境内客服中心：** `1588-1300`（韩国时间工作日 `09:00–18:00`，周六及节假日休息；主叫付费）。
- **海外致电：** `+82-42-609-4295`，服务时间相同。
- **电子邮箱：** `help@posa.or.kr`。
- **在线问答与查单：** [ePOST 客户中心](https://www.epost.go.kr/bbs.RetrieveBbsMain.comm) 以及 [trace.epost.go.kr](https://trace.epost.go.kr/xtts/tt/epost/ems/ems_eng.jsp) 上的 *e-mail EMS Inquiry* 入口。
- **ePOST 页脚办公地址：** 1 Jeongbohwa-gil (Bitgaram-dong), Naju, 58323, South Korea。

> 请勿轻信声称 Korea Post 包裹因欠缴小额费用无法派送并附带非官方支付链接的诈骗短信。入境 `KR` 包裹的任何正规税费均由你本国邮政或海关门户收取，绝不会通过陌生短信链接催缴。详见[快递诈骗短信防范指南](guide:delivery-scam-texts)（英文）。

## 使用 Peek 追踪 Korea Post 包裹

[Peek](/) 可高置信度自动识别任何以 `KR` 结尾的 UPU S10 国际邮政单号（如虚构示例 `EM482917367KR` 或 `RR482917367KR`）为 Korea Post，也能识别粘贴的包含 `POST_CODE=` 的 `trace.epost.go.kr` 链接及包含 `sid1=` 的 `service.epost.go.kr` 链接。13 位韩国国内单号（`6482917364058`）同时符合多家承运商：Peek 会将 Korea Post 列在候选中由你选择。你也可稍后在包裹页面点击“更换快递公司”（搜索“South Korea Post”或“ePOST”同样能找到），或直接粘贴 ePOST 追踪链接。

Peek 会分别为 `KR` 单号读取 Korea Post 英文 EMS 追踪器、为 13 位单号读取 ePOST 韩语国内追踪器，显示阶段（“已生成面单”、“已揽收”、“运输中”、“清关中”、国内包裹的“派送中”、“派送未成功”、“已签收”）、附带邮局或机场名称的扫描描述以及国内包裹的签收时间。国内扫描按韩国时间解析，国际扫描按扫描地点所在时区解析（例如纽约扫描按纽约时间），并统一在你设备的本地时钟下显示。Korea Post 返回的收件人姓名仅显示在你本人的包裹页面“收件人”栏中，绝不出现在分享链接里；签收人姓名不会出现在任何 Peek 应用中，Peek 也会过滤掉服务名称、发件人姓名和邮局电话。Korea Post 追踪器不提供预计送达日期、重量或自提点信息。

当你在 Peek 中打开包裹页面，或在派送当日开启通知时，Peek 最多每 10 分钟检查一次；国内包裹显示 `배달준비` 当天，只要韩国邮政自己的追踪系统有响应，Peek 会从韩国时间早上 6 点起每 2 分钟检查一次。其余情况下每小时检查一次。若包裹停留在“已生成面单”或“已揽收”超过 12 小时，或连续两天没有新动态，无论你以何种方式关注，均改为每小时检查一次；若停留在“已生成面单”超过七天，则每 6 小时检查一次。当韩国邮政自己的追踪系统无响应、Peek 仅通过追踪服务跟进时，每小时检查一次，静默一天后每 3 小时一次，派送当天最多每 15 分钟一次。

当最新扫描距今已满四天，且包裹仍处于“已生成面单”、“已揽收”或“运输中”时，Peek 会用该次扫描的日期加以标记，例如“自10月12日周一起没有物流更新”。包裹页面随后会显示“包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 Korea Post 查询。”，并附上指向 ePOST 客户中心的“向 Korea Post 寻求帮助”链接。如果三周没有新扫描，页面会显示“已超过三周没有新动态。如果还没收到，请联系卖家。Peek 会持续向 Korea Post 查询。”

你可以选择按“每次扫描”、“重要环节”或“仅派送当天”提醒。Peek 仅监控物流更新，无法代你办理清关、改寄包裹或向韩国邮政提交 EMS 查单。

## 关于 Korea Post 物流查询的问题

### `EE...KR`、`EM...KR` 和 `RR...KR` 分别代表什么？

它们均为从韩国发出的 13 位 UPU S10 国际邮政单号：`EE` 代表 EMS 文件，`EM` 代表 EMS 商品包裹，`RR`（及其他 `R`/`C`/`L` 前缀）代表国际挂号邮件、K-Packet 及国际包裹。

### 为什么我的 Korea Post EMS 包裹在离开机场后停止更新？

在韩国出现 `Departure from the Airport` 后，追踪信息通常会暂时静止，直到目的地国邮政在入境互换局扫描接收该批邮袋（`Arrival at inward office of exchange`）。建议同时用同一 `...KR` 单号在当地国家邮政网站查询。

### Korea Post 会直接在海外送货上门吗？

韩国邮政负责韩国境内的揽收、出口分拣和航空发运，随后将包裹移交（`Delivered to Destination Post`）给目的地国家的邮政运营商，由当地邮政完成进口清关与上门派送。

### 如何联系 Korea Post 客服？

韩国境内可拨打 `1588-1300`（韩国时间周一至周五 `09:00–18:00`，周六及节假日休息），海外可拨打 `+82-42-609-4295`，发送邮件至 `help@posa.or.kr`，或访问 [ePOST 客户中心](https://www.epost.go.kr/bbs.RetrieveBbsMain.comm)。

:::sources
- [Korea Post：英文 EMS 追踪/确认](https://trace.epost.go.kr/xtts/tt/epost/ems/ems_eng.jsp) – 国际 EMS 追踪门户及合作目的地邮政
- [Korea Post (ePOST)：国内追踪](https://service.epost.go.kr/iservice/usr/trace/usrtrc001k01.jsp) – 13 位国内包裹与挂号邮件追踪
- [Korea Post (ePOST)：EMS 服务概览](https://ems.epost.go.kr/front.Introduction01.postal) – `EE` 与 `EM` 前缀、EMS 运输时效、CN22/CN23 报关规则、100+ EMS / 70+ EMS Premium 目的地及电话 `1588-1300`
- [Korea Post (ePOST)：各国寄递条件](https://ems.epost.go.kr/front.EmsApplyGoCondition.postal) – 各目的地 EMS 寄递限制与海关说明
- [Korea Post (ePOST)：预计送达天数与价格计算器](https://ems.epost.go.kr/front.EmsDeliveryDelivery09.postal) – EMS、EMS Premium 及包裹时效与费用计算器
- [Korea Post (ePOST)：禁限寄物品](https://ems.epost.go.kr/front.Introduction04.postal) – 国际寄递限制说明
- [Korea Post (ePOST)：客户中心](https://www.epost.go.kr/bbs.RetrieveBbsMain.comm) – `1588-1300`、`help@posa.or.kr`、工作日 `09:00–18:00`（KST）及罗州办公地址（Peek 帮助按钮链接地址）
- [Korea Post (ePOST)：主门户](https://www.epost.go.kr/main.retrieveMainPage.comm) – 国内包裹、挂号信、EMS 与 K-Packet 概览及海外客服热线 `+82-42-609-4295`
- [Universal Parcel Scraper: korea-post](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/korea-post) – `KR` S10 与 13 位国内单号识别、`trace.epost.go.kr` / `service.epost.go.kr` 链接规则、英文 EMS 与韩语国内状态映射、时区解析、过滤字段及帮助链接
:::
