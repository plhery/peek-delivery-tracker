---
title: PigeonShip 追踪：18 位单号、实时状态与美加末端派送
description: 追踪美国与加拿大 PigeonShip 当日达及末端派送包裹：18 位字母数字单号、实时追踪页状态、截止送达时间、送达照片与客服联系方式。
slug: pigeonship-tracking
published: 2026-11-26
updated: 2026-11-26
---

PigeonShip 是一家业务覆盖美国与加拿大的末端派送承运商，在美国犹他州普罗沃（Provo）和加拿大安大略省奥克维尔（Oakville）设有办事处。零售商、电商卖家及本地商家通过 PigeonShip 开展当日达、次日达及区域电商派送，旗下服务包括 **Pigeon Flex**（2–5 天全国派送）、**Pigeon Express**（2 天全国派送）、**Pigeon Connect**（按需当日达）以及 **Pigeon Flash**（预约当日达）。

PigeonShip 招募独立司机并为企业运行专属派送项目。收件人会收到一个 `admin.pigeonship.com` 追踪链接，派送期间显示订单状态与截止送达时间，送达后则切换为完成页面。

## PigeonShip 运单号格式

Peek 接受由 8 至 30 位字母和数字组成的 PigeonShip 单号，并会自动去除空格、转为大写。Peek 能识别一种数字与大写字母交替排列的 18 位格式：

- **18 位字母数字组合**，结构为“数字-字母-数字-字母-3 位数字-字母-3 位数字-字母-2 位数字-字母-数字-2 位字母”（例如虚构单号 `7P4K829H631N58R2TM`）。

PigeonShip 的追踪链接格式为 `admin.pigeonship.com/shipping/tracking/package/<trackingNumber>/`。将该链接粘贴到 Peek 后，Peek 会自动提取包裹编号并直连 PigeonShip 查询。若输入符合上述 18 位格式的裸单号，Peek 会单独询问 PigeonShip，并在对方响应后显示“PigeonShip 已承运此包裹。”如需对比其他美国及加拿大快递单号，请参阅[运单号格式指南](guide:tracking-number-formats)（英文）。

## PigeonShip 实时追踪如何运作

PigeonShip 的收件人追踪页采用摘要视图，而非逐站扫描流水。订单进行期间，页面显示“In transit”、地图上的司机位置，以及（若设置了）截止送达时间（`dueDate`）；页面底层带有状态码与标签，例如 `16` 与 `Assigned`。仅当状态码为 `64` 时页面才显示“Delivered”。一旦完成派送，PigeonShip 会在同一网址将页面替换为完成界面，显示 `Your delivery has been completed successfully.` 并附带查看派送照片的链接。

| PigeonShip 状态 / 页面 | 含义 |
| --- | --- |
| 状态 `16` — `Assigned` | 订单已排入司机派送路线，页面显示“In transit”。 |
| Due date / 截止送达时间 | 订单送达前在追踪页显示的目标完成时刻。 |
| 状态 `64` / `Delivered` 或 `Your delivery has been completed successfully.` | 司机已完成派送；打开 PigeonShip 原始链接即可查看送达确认照片。 |
| `Page not found`（404） | 该运单号在 PigeonShip 实时系统中未激活，或存在拼写错误。 |

由于 PigeonShip 不在收件人页面公开带时间戳的扫描流水，你不会看到类似 [UPS](carrier:ups)、[USPS](carrier:usps)、[Canada Post](carrier:canada-post) 或 Intelcom 那样的中转仓库或分拣中心扫描。页面而是随着派送推进刷新实时状态，并在投递完成后切换至完成界面。

## 当日达预期与延误排查建议

PigeonShip 在美国和加拿大全境提供当日达与次日达派送服务。其官网客户评价提到司机抵达前会发送短信、放下包裹后会拍照留证。

如果你的订单过了预计送达时间仍显示 `Assigned`，或追踪链接打开后显示 `Page not found`：

- **核对拼写错误：** 对照零售商的邮件或短信核实完整单号或完整的 `admin.pigeonship.com` 链接；对于没有对应页面的编号，PigeonShip 网站会返回 `Page not found`。
- **联系 PigeonShip：** 若超过截止时间仍停留在“In transit”，请通过其[联系表单](https://www.pigeonship.com/contact-us)查询。
- **查看派送说明与照片：** PigeonShip 官网评价提到司机会按面单说明投递并拍摄投递照片；完成后请打开原 `admin.pigeonship.com` 链接查看照片。更多排查指引请参阅[物流停止更新怎么办](guide:tracking-not-updating)（英文）和[错过派送指南](guide:missed-delivery)（英文）。

## 使用 Peek 追踪 PigeonShip 包裹

将 PigeonShip 单号或完整的 `admin.pigeonship.com/shipping/tracking/package/...` 链接粘贴到 Peek 中。若因非常规单号格式导致 Peek 未预选 PigeonShip，可在添加包裹时点击 **更改**，或在包裹详情页点击 **更改快递公司** 手动选择。

Peek 通过开源的 [universal-parcel-scraper PigeonShip 适配器](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/pigeonship)直接读取 PigeonShip 服务端渲染的追踪页，提取订单状态码与标签、是否已送达以及（如有）作为预计送达时间显示的 `dueDate`。Peek 不会展示收件人姓名、司机实时坐标、进度条或送达照片——如需查看送达照片，请打开 PigeonShip 原链接。

由于 PigeonShip 只返回当前状态，Peek 会在监测到阶段变化时添加一条带时间的记录：进行中的 `Assigned` 订单显示为 **运输中** 并附预计送达时间，派送完成后则显示为 **已送达**。

在 PigeonShip 追踪页正常响应期间，当你打开包裹页面或在派送当天开启通知时，Peek 最高每 10 分钟检查一次；其余时间每小时检查一次。因 PigeonShip 追踪页不含派送中节点，Peek 不会切换到派送日每 2 分钟检查。若停留在“已建单”或“快递公司已揽收”满 12 小时，或连续两天无新动态，无论你如何关注都改为每小时检查一次；停留在“已建单”满七天后改为每 6 小时一次。当 PigeonShip 追踪页无响应而 Peek 仅通过追踪服务跟进时，每小时检查一次，静默一天后每 3 小时一次，派送中当天最高每 15 分钟一次。

若最新扫描已满四天且包裹仍停留在“已建单”、“快递公司已揽收”或“运输中”，Peek 会标出该扫描的日期（例如“自 10月12日 周一 起无物流更新”）。包裹页面随后会显示：“包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 PigeonShip 查询。”并附带指向 PigeonShip 联系页面的“向 PigeonShip 求助”链接。若超过三周仍无新扫描，则会显示：“已经三周多没有新动态。如果包裹仍未送达，请联系卖家。Peek 会持续向 PigeonShip 查询。”

在 Peek 通知设置中，你可以按需选择 **每次扫描**、**重要节点** 或 **仅送达** 提醒。

## 联系 PigeonShip

- **官方联系表单：** [PigeonShip Contact Us](https://www.pigeonship.com/contact-us)（选择 United States、Canada 或 Other 并说明派送咨询内容）
- **官方网站：** [pigeonship.com](https://www.pigeonship.com/)
- **美国办事处：** PigeonShip USA, 1675 N Freedom Blvd Ste 9B, Provo, Utah 84604-6929
- **加拿大办事处：** PigeonShip Canada, 405 Dundas St W, Oakville, ON L6M 5P9

## 常见问题

### 为什么 PigeonShip 追踪不显示历史扫描列表？

PigeonShip 的收件人实时追踪页仅提供状态摘要，显示当前订单状态（“In transit”或“Delivered”）和截止送达时间，而不展示多站仓库扫描流水。

### 在哪里可以查看 PigeonShip 的送达照片？

Peek 不存储也不显示 PigeonShip 的送达照片。包裹标记为已送达后，请打开邮件或短信中的原始 `admin.pigeonship.com/shipping/tracking/package/...` 链接，在完成页面查看照片。

### PigeonShip 里的 `Assigned` 状态是什么意思？

`Assigned`（状态码 `16`）是订单进入司机派送路线时 PigeonShip 页面背后的状态；页面本身显示“In transit”，Peek 同样将其显示为 **运输中**。

### PigeonShip 同时在美国和加拿大派送吗？

是的。PigeonShip 在美国和加拿大均提供当日达、次日达及全国末端派送服务，并在犹他州普罗沃与安大略省奥克维尔设有办事处。

:::sources
- [PigeonShip](https://www.pigeonship.com/)
- [PigeonShip Contact Us](https://www.pigeonship.com/contact-us)
- [universal-parcel-scraper PigeonShip adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/pigeonship)
:::
