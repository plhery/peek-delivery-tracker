---
title: Maersk eCommerce 追踪：1M 包裹单号、扫描状态与末端交接
description: 追踪 Maersk eCommerce（B2C Europe / InterPost）包裹：19 位 1M 单号结构、70000–77200 状态码、末端交接与客服。
slug: maersk-ecommerce-tracking
published: 2026-11-26
updated: 2026-11-26
---

**Maersk eCommerce**（*Maersk Logistics & Services USA Inc.*，整合了原有的 *B2C Europe* 与 *InterPost* 包裹平台）是马士基集团（A.P. Moller–Maersk）旗下的国内及跨境 B2C 包裹派送网络（[maersk.com/supply-chain-logistics/e-commerce-logistics](https://www.maersk.com/supply-chain-logistics/e-commerce-logistics)）。该网络每年在北美和欧洲通过超过 75 家对接承运商处理逾 8,000 万件包裹，在自有设施完成网店包裹分拣，在国际线路上办理 B2C 清关，并将末端派送交由当地快递与邮政运营商完成，例如 Amazon Logistics、[OnTrac](carrier:ontrac) 和 [DPD Austria](carrier:dpd-at)。你可以将 19 位 `1M` 单号粘贴到上方输入框，同时追踪马士基自有设施扫描及同步回传的当地承运商节点。

## Maersk eCommerce 运单号（`1M` 格式）

Maersk eCommerce 签发 19 位字母数字组合包裹号，以 `1M` 开头，后接 7 位商家/客户代码（至少包含一个字母）及 10 位数字：

| 格式（虚构） | 含义 |
| --- | --- |
| `1MAB4829C7360514928` | 19 位 Maersk eCommerce 包裹单号（`1M` + 7 位客户代码 + 10 位数字）。 |
| `1MKX7391E4829173605` | 北美或欧洲线路常用的另一组 19 位 `1M` 单号示例。 |

请在零售商的发货邮件或订单追踪链接（`maersk.com/tracking/parcel/...`）中查找 `1M` 单号。马士基追踪页可能会同时给出末端派送承运商名称及其分配的本地单号。另见[运单号格式说明](guide:tracking-number-formats)（英文）。

## Maersk eCommerce 追踪状态含义

马士基自身设施扫描使用 `70000` 至 `72004` 编号，末端承运商扫描则从 `77100` 起编：

| 马士基状态码与文案 | 对你的含义 | Peek 显示 |
| --- | --- | --- |
| `70000` `Shipment Manifest Received`、`70001` `Maersk Shipping Label Created` | `70000`：卖家预报清单中已登记该包裹，但马士基尚未收件。`70001`：包裹进入马士基网络后由马士基生成的自有标签，往往比 `70002` 早几分钟。 | “已建单” |
| `70002` `Parcel Received by Maersk` | 实物包裹已送达并录入 Maersk eCommerce 集货接收设施。 | “快递公司已揽收” |
| `72001` `Arrived at Maersk Facility`、`72002` `Processing at Maersk Facility`、`72004` `Departed from Maersk Facility` | 在马士基集货枢纽完成分拣并向目的地地区或口岸发运。 | “运输中” |
| `77100` `In-Transit Scan`、`77101` `Enroute to Facility`、`77102` `Arrival at Facility`、`77103` `Processing at Facility`、`77105` `Departed from Facility`、`77106` `Parcel In-transit` | 同步回传自当地末端承运商（如 OnTrac、Amazon Logistics 或 DPD）的境内中转扫描。 | “运输中” |
| `77203` `Out for Delivery` | 已装上当地末端派送车辆，今日派送。 | “派送中” |
| `77200` `Parcel Successfully Delivered` | 已由末端合作承运商送达收件地址。 | “已送达” |

若某条扫描未包含上述代码，当马士基将其标记为异常时显示“需要处理”，否则按马士基追踪页面归属的进度阶段进行展示。

## 覆盖范围、退货与跨境清关

Maersk E-Commerce 在北美和欧洲 32 个国家提供完整派送覆盖，并在中国和东南亚设有始发集货口岸（[Maersk E-Commerce](https://www.maersk.com/supply-chain-logistics/e-commerce-logistics)）：

- **设施与合作伙伴：** 在欧洲设有 8 座分拣与越库中心，在伦敦、陶洛夫（Taulov）和阿姆斯特丹设有入境集货枢纽，对接超过 40 家末端合作快递与邮政服务商，支持送货上门及自提点取件。在北美设有分拣设施及 JFK、LAX、ORD 口岸枢纽；标准服务在美国耗时 2–7 天、加拿大 1–10 天，加急服务分别为 2–5 天和 1–5 天。
- **国际清关：** 马士基为发往英国、欧盟和美国的 B2C 包裹提供清关服务。收件时是否另需缴纳关税与进口增值税，取决于卖家是否在结账时已代收。详见[包裹在海关滞留怎么办](guide:customs)（英文）。
- **电商退货：** 为开通该功能的卖家提供上门取件或网点投递的境内及国际退货服务，部分线路支持无纸化及免税退运。

## Maersk eCommerce 包裹延误、静默或遗失

由于 Maersk eCommerce 是由电商品牌签约委托的 B2B 包裹集运商，如需更改地址、处理少件、补发或退款，**请务必先联系下单网店**。

- **干线运输或末端交接期间无更新：** 在 `72004`（`Departed from Maersk Facility`）与首条 `77100+` 末端承运商扫描之间，因干线卡车或航班向当地快递转运，轨迹常见数日停顿。详见[为什么物流停止更新](guide:tracking-not-updating)（英文）。
- **善用末端承运商单号：** 一旦马士基给出末端承运商及其本地单号，后续便由该承运商的官网与客服承接。对于 Amazon Logistics、[OnTrac](carrier:ontrac) 和 [DPD Austria](carrier:dpd-at)，Peek 会显示“由……派送”。
- **显示已签收或包裹破损：** 请先查看门廊、收发室和邻居家，核对当地合作快递的追踪记录，并及时联系商家处理。详见[显示已签收但未收到](guide:delivered-not-received)（英文）、[包裹遗失](guide:lost-parcel)（英文）、[包裹破损](guide:damaged-parcel)（英文）和[包裹退回寄件人](guide:returned-to-sender)（英文）。

## Maersk eCommerce 客服支持与官方入口

马士基支持页面提供在线聊天与工单管理，但主要面向企业客户；卖家通过马士基企业端 MyParcel 平台下单。

- **Maersk E-Commerce 介绍与商务咨询：** [maersk.com/supply-chain-logistics/e-commerce-logistics](https://www.maersk.com/supply-chain-logistics/e-commerce-logistics)。
- **马士基综合支持与常见问题：** [maersk.com/support](https://www.maersk.com/support) 与 [maersk.com](https://www.maersk.com/)。
- **收件人求助途径：** 请联系所购商品的线上零售商，或在当地末端承运商完成首扫后直接联系该本地快递。

> 请警惕要求点击陌生链接并支付小额费用以放行“马士基包裹”的虚假短信。任何物流通知请务必通过零售商订单页或当地快递官方网站核实。详见[警惕快递诈骗短信](guide:delivery-scam-texts)（英文）。

## 使用 Peek 追踪 Maersk eCommerce 包裹

输入 19 位 `1M` 包裹单号（例如虚构单号 `1MAB4829C7360514928` 或 `1MKX7391E4829173605`）时，[Peek](/) 会将其作为唯一候选自动向 Maersk eCommerce 查询，并在马士基包裹接口确认后显示“Maersk eCommerce 已承运此包裹。”Peek 也会直接识别粘贴的 `maersk.com/tracking/parcel/` 链接。如需手动选择，可在添加时点击“更改”（或在包裹详情页点击“更改快递公司”），搜索“B2C Europe”、“InterPost”、“Maersk Parcel”或“Maersk E-Delivery”也能找到。

Peek 会读取马士基包裹追踪接口，并展示当前阶段（“已建单”、“快递公司已揽收”、“运输中”、“派送中”、“需要处理”、“已送达”）、马士基及末端承运商的扫描原文、城市与地区、马士基给出的预计送达日、目的地国家及签收时间。内部状态码与服务名称不会在 Peek 各客户端显示。马士基自有扫描及多数合作伙伴扫描带有时区偏移；不带偏移的合作扫描（如 DPD）会按扫描地时区解析（例如格拉茨的扫描按维也纳时间），否则沿用相邻扫描的时区，再统一换算为你的设备本地时区。若追踪器重复列出同一条合作商扫描，Peek 仅保留一条。当马士基给出 Amazon Logistics、[OnTrac](carrier:ontrac) 或 [DPD Austria](carrier:dpd-at) 且对应单号符合其规则时，Peek 会核验其承运状态，随后在页面显示“由……派送”及承运商名称，并列出两个单号。Peek 不会读取客户订单号、目的地城市和邮编，也不会读取合作伙伴链接。

在马士基追踪接口正常响应期间，当你打开包裹页面或在派送当天开启通知时，Peek 最高每 10 分钟检查一次；在马士基出现 `77203`（`Out for Delivery`）当天的最新扫描所在时区早上 6 点起，改为每 2 分钟检查一次。其余时间每小时检查一次。若停留在“已建单”或“快递公司已揽收”满 12 小时，或连续两天无新动态，则无论你如何关注都改为每小时检查一次；停留在“已建单”满七天后改为每 6 小时一次。当马士基追踪接口无响应而 Peek 仅通过追踪服务跟进时，每小时检查一次，静默一天后每 3 小时一次，派送中当天最高每 15 分钟一次。

若最新扫描已满四天且包裹仍停留在“已建单”、“快递公司已揽收”或“运输中”，Peek 会标出该扫描的日期（例如“自 10月12日 周一 起无物流更新”）。包裹页面随后会显示：“包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 Maersk eCommerce 查询。”若超过三周仍无新扫描，则会显示：“已经三周多没有新动态。如果包裹仍未送达，请联系卖家。Peek 会持续向 Maersk eCommerce 查询。”

你可以自由选择“每次扫描”、“重要节点”或“仅送达”提醒预设。Peek 仅监控物流追踪更新，无法代你办理清关、更改派送路线，也无法代为联系 Maersk eCommerce 或其末端合作快递。

## Maersk eCommerce 追踪常见问题

### Maersk eCommerce（B2C Europe / InterPost）是什么？

Maersk eCommerce 是马士基集团（A.P. Moller–Maersk）面向北美和欧洲的 B2C 包裹集运与派送网络（整合了原 B2C Europe 和 InterPost 网络），依托 75 家以上末端合作快递，每年运送超过 8,000 万件包裹。

### Maersk eCommerce 运单号是什么格式？

Maersk eCommerce 包裹单号共 19 位：以 `1M` 开头，后接 7 位含至少一个字母的客户代码以及 10 位数字——例如虚构单号 `1MAB4829C7360514928`。

### 谁负责把我的 Maersk eCommerce 包裹送到家门口？

马士基负责枢纽间集运与干线转运，随后将末端派送移交给你所在国家的区域合作伙伴，例如 Amazon Logistics、[OnTrac](carrier:ontrac)、[DPD Austria](carrier:dpd-at) 或当地邮政快递。

### 为什么我的 Maersk eCommerce 包裹多日停留在 `70000 Shipment Manifest Received`？

`70000` 表示卖家的预报清单已列出该包裹，但马士基尚未拿到实物，因此在卖家交寄前物流不会推进，如长时间停留请联系卖家。包裹进入马士基网络后，系统会生成自有标签 `70001`，且往往几分钟后便会出现 `70002`（`Parcel Received by Maersk`）。

:::sources
- [Maersk: E-Commerce Parcel Delivery Services](https://www.maersk.com/supply-chain-logistics/e-commerce-logistics) – 年运量 8,000 万+件包裹、75+ 家对接承运商、北美与欧洲国内/国际/退货网络
- [Maersk: Official Website](https://www.maersk.com/) – 全球物流门户与包裹追踪入口
- [Maersk: Support & FAQs](https://www.maersk.com/support) – 马士基官方支持门户
- [Universal Parcel Scraper: maersk-ecommerce](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/maersk-ecommerce) – 19 位 `1M` 格式、`maersk.com/tracking/parcel/` 链接规则、70000–77200 状态码、预计到达日与国家及 Amazon/OnTrac/DPD-AT 交接规则
:::
