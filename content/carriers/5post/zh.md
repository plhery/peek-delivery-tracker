---
title: 5Post 俄罗斯包裹追踪：单号、Pyaterochka 自提与状态
description: 追踪俄罗斯 5Post（FivePost / X5）包裹：Avito P 单号与订单号格式、俄语状态代码含义、自提柜与超市收银台取件及客服。
slug: 5post-tracking
published: 2026-11-26
updated: 2026-11-26
---

5Post（*5Пост* / *FivePost*，由 X5 集团运营）是俄罗斯电商包裹、个人寄递与履约网络，依托 **51** 座分拣中心，将网购订单与 Avito Delivery 包裹派送至遍布俄罗斯 **4,950** 个城镇的 **25,700** 多个自提柜（*постаматы*）及 **Pyaterochka**（«Пятёрочка»）与 **Perekrestok**（«Перекрёсток»）超市收银台自提点（[fivepost.ru](https://fivepost.ru/)）。5Post 与超过 1,000 家线上零售商及平台合作——包括 Avito Delivery 和跨境 AliExpress 订单——同时运营门到门快递与零担/整车货运。

您可以在 [fivepost.ru](https://fivepost.ru/)（点击 *Где мой заказ?*）查询包裹，或将订单号或 `fivepost.ru/tracking?id=...` 链接粘贴到上方输入框中。

## 5Post 追踪单号格式

5Post 使用商家或交易平台订单号追踪货件——任何由 3 至 40 位字母和数字组成、且至少包含 1 位数字的连续字符串：

| 格式（虚构示例） | 说明 |
| --- | --- |
| `P48291736051` | `P` + 11 位数字（共 12 位），为 Avito Delivery 常见单号格式。Peek 会同时询问 [Colis Privé](carrier:colis-prive) 与 5Post，在俄罗斯优先询问 5Post。 |
| `99994829173605` | 14 位数字，常见的网店订单号。Peek 不会自动将其归类为 5Post，而会询问 DPD 系、Chronopost、SEUR、BRT 等承运商：请手动选择 5Post 或直接粘贴 5Post 追踪链接。 |

由于各商家自行编制订单号，两个不同订单偶尔可能重号。遇到重号时 5Post 网站会要求输入收件人手机号的若干位数，而 Peek 无法区分重号订单。粘贴 `fivepost.ru/tracking?id=...` 链接则会立刻将包裹归入 5Post。如需了解其他单号格式，请参阅[单号格式指南](guide:tracking-number-formats)（英文）。

## 5Post 追踪状态含义

5Post 追踪接口将系统状态代码与俄语描述配对，并在仓库处理和发出扫描中注明区域分拣仓（`РЦ ...`）。

| 5Post 代码与俄语原文 | 实际含义 |
| --- | --- |
| `INFORMATION_RECEIVED` — `Ожидается передача заказа в 5post` | 订单数据已登记，5Post 正等待卖家或发件人交寄包裹。 |
| `PROCESS_WITHDRAWN` — `Передан службе доставки` | 发件人已将包裹交寄给 5Post。 |
| `WAREHOUSE_PROCESSING` — `Обрабатывается на складе ...` | 正在 5Post 区域分拣仓库（`РЦ`）内处理。 |
| `READY_FOR_SHIPMENT` — `Покинул склад ...` | 已离开分拣仓库，发往下一枢纽或自提门店。 |
| `DELIVERED` — `Доставлен в пункт выдачи` | 已送达您选定的 Pyaterochka / Perekrestok 收银台或 5Post 自提柜——**等待自提**，尚未被取走！ |
| `DONE` — `Получен` | 收件人已取走包裹（已送达）。 |
| `RECEIVED_BY_COURIER` — `Передан курьеру` | 上门派送订单已交由快递员外出派送。 |
| `RETURNING_BY_COURIER` — `Неуспешная попытка вручения` | 快递员上门派送尝试未成功。 |
| `CUSTOMS_CLEARANCE_STARTED` / `CUSTOMS_DATA_REQUIRED` / `CUSTOMS_AWAITING_PAYMENT` | 来自海外（如 AliExpress）的订单正在清关、等待您向报关代理提供个人资料或等待缴纳关税。 |
| `CUSTOMS_CLEARANCE_COMPLETED` — `Таможенное оформление завершено` | 海关已放行，包裹继续发往 5Post 网络。 |
| `UNCLAIMED_...` / `RETURNED_TO_PARTNER` / `RETURNED_TO_CLIENT` | 超过保管期限未领取或交寄后取消，正退回商家或发件人（`UNCLAIMED_READY_FOR_REPICKUP` 表示已准备重新送至自提点）。 |
| `INCEPTION_PROBLEM` / `LOST` / `CANCELLED` / `REGISTRATION_ERROR_...` | 订单异常、已取消、丢失，或缺少手机号/派送日期/地址需更正。 |

请特别注意 5Post 代码的一个特殊之处：`DELIVERED`（`Доставлен в пункт выдачи`）表示包裹**已到门店或自提柜待取**，而 `DONE`（`Получен`）才代表您已经实际签收领走。

## 在 Pyaterochka、Perekrestok 与自提柜取件

自提订单会存放在您下单时选定的 5Post 自提柜（*постамат*）或 Pyaterochka / Perekrestok 超市收银台（*касса*）；5Post 常见问题页提供自提柜与自提点的营业时间说明：

- **取件码：** 订单抵达自提点（`Доставлен в пункт выдачи`）后，5Post 会通过短信发送取件码。请在自提柜屏幕输入或在收银台报给店员。如未收到取件码或店员找不到订单，可查阅 5Post 常见问题指引。
- **延期保管与修改手机号：** 据 [5Post 常见问题](https://fivepost.ru/faq/)说明，收件人可通过官网或手机应用查询保管期限、申请延长保管期、在线支付货款，或查看更改手机号、柜门卡住以及柜格空置/放错件的处理步骤。
- **跨境订单（AliExpress）：** 经 5Post 转运的国际订单包含航空干线（`DEPARTED_BY_AIR`、`DELIVERED_BY_AIR`）及俄罗斯清关；若追踪显示 `Требуется предоставить данные таможенному представителю`（`CUSTOMS_DATA_REQUIRED`）或 `Требуется оплата таможенных платежей`（`CUSTOMS_AWAITING_PAYMENT`），说明报关代理需要您的清关信息或尚有税费未缴；5Post 常见问题详细说明了哪些订单需要清关、为何提交资料、如何联系报关代理以及缴费时机。另请参阅[包裹滞留海关指南](guide:customs)（英文）与[为何物流停止更新](guide:tracking-not-updating)（英文）。

如需对比俄罗斯境内由国家邮政或其他快递承运的包裹，可查阅[俄罗斯邮政（Russian Post）](carrier:russian-post)与 [CDEK](carrier:cdek)。

## 使用 Peek 追踪 5Post

将您的 `P` 开头单号、商家订单号或 `fivepost.ru/tracking?id=...` 链接粘贴至 [Peek](/)。对于 `P` 单号，Peek 会询问 Colis Privé 与 5Post（在俄罗斯优先 5Post），确认后显示“5Post 有这个包裹的记录。”对于 14 位订单号 Peek 不会自动识别为 5Post：请在添加时点击“更改”，或在包裹页点击“更改承运商”手动选择 5Post。粘贴 5Post 链接则会直接归入 5Post。

Peek 通过开源的 [universal-parcel-scraper 5Post 适配器](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/5post)读取 5Post 访客追踪，并在网络出错时自动重试。页面会显示每条扫描的俄语原文、仓库扫描注明的分拣中心或枢纽（`РЦ ...`、`ХАБ ...`）、到达自提点或自提柜后的取件点名称与地址，以及您的取件时间。5Post 每条扫描均附带 UTC 时区偏移，Peek 会按您设备的本地时钟展示。保管截止日期、付款详情、门店营业时间、发件人姓名与包裹件数仅保留在 5Post 官网与应用内。

Peek 会准确映射 5Post 的状态代码，确保 `DELIVERED` 不会误触发“已送达”通知：**已创建面单**（`INFORMATION_RECEIVED`）、**承运商已揽收**（`PROCESS_WITHDRAWN`）、**运输中**（仓库处理、发出、空运段与清关后转运）、**清关中**（报关资料要求、清关中与待缴税）、**派送中**（`RECEIVED_BY_COURIER`）、**派送未成功**（`RETURNING_BY_COURIER`）、**待取件**（`DELIVERED` — `Доставлен в пункт выдачи`）、**已送达**（`DONE` — `Получен`）、**已退回寄件人**（`RETURNED_TO_...` 及除 `UNCLAIMED_READY_FOR_REPICKUP` 外的 `UNCLAIMED_...` 代码，后者保持运输中）以及**需要关注**（`LOST`、`CANCELLED`、`CUSTOMS_CLEARANCE_REJECTED` 与登记错误）。

当最新扫描距今已满四天且包裹仍处于“已创建面单”、“承运商已揽收”或“运输中”时，Peek 会按该次扫描日期加以标记，例如“自 10 月 12 日（周一）起无物流更新”。包裹页面随即会提示“包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 5Post 查询。”并提供指向 5Post 常见问题的“从 5Post 获取帮助”链接。如果超过三周仍无新扫描，则会提示“超过三周没有新动态。如果包裹仍未送达，请联系卖家。Peek 会持续向 5Post 查询。”

当您在 Peek 中打开包裹页面，或在派送当日开启通知时，Peek 最多每 10 分钟检查一次；在 5Post 显示 `Передан курьеру` 当天，若 5Post 官网正常响应，会从最新扫描所在地早上 6 点起每 2 分钟检查一次。其余情况每小时一次。停留在“已创建面单”或“承运商已揽收”满 12 小时或连续两天无新动态后，改为每小时一次；停留在“已创建面单”满七天后每 6 小时一次。当 5Post 官网无响应、Peek 仅通过追踪服务跟进时，默认每小时一次，静默一天后每 3 小时一次，派送当天最多每 15 分钟一次。支持“每次扫描”、“重要节点”或“仅送达通知”提醒。

## 联系 5Post

5Post 在 [fivepost.ru/contacts](https://fivepost.ru/contacts/) 与 [fivepost.ru/faq](https://fivepost.ru/faq/) 公布了官方热线及在线反馈表单：

- **莫斯科及莫斯科州电话：** `8-495-935-88-00`（按运营商资费计费）
- **俄罗斯其他地区免费电话：** `8-800-100-05-05`
- **在线常见问题与工单表单：** [5Post FAQ](https://fivepost.ru/faq/) 与 [5Post 联系页面](https://fivepost.ru/contacts/)（支持免登录或登录提交收件人咨询，附件上限 40 MB）
- **法定地址：** 109029, Moscow, ul. Srednyaya Kalitnikovskaya, 28 bldg. 4（税号 INN `7722461620`，注册号 OGRN `1187746567568`）

## 常见问题

### 为什么我还没取件，5Post 的内部状态就显示 `DELIVERED`？

在 5Post 追踪系统中，`DELIVERED`（`Доставлен в пункт выдачи`）指包裹已送抵 Pyaterochka / Perekrestok 超市收银台或自提柜等待您领取。Peek 将其映射为**待取件**，待出现 `DONE`（`Получен`）后才标记为**已送达**。

### 在哪里领取 5Post 包裹？

凭 5Post 短信发送的取件码，前往您下单时选定的 Pyaterochka（«Пятёрочка»）或 Perekrestok（«Перекрёсток»）超市内的 5Post 自提柜（*постамат*）或收银台领取。

### 为什么 5Post 要求为我的 AliExpress 订单提供清关资料？

跨境订单由报关代理办理俄罗斯清关，可能需要收件人补充个人清关资料（`CUSTOMS_DATA_REQUIRED`）。您可在 5Post 常见问题中查看哪些订单需清关、资料用途及报关代理联系方式。清关放行后，5Post 会显示 `Таможенное оформление завершено`（`CUSTOMS_CLEARANCE_COMPLETED`）。

### 自提柜柜门打不开或包裹不见了怎么联系 5Post？

请拨打 `8-495-935-88-00`（莫斯科及周边）或 `8-800-100-05-05`（俄罗斯各地区），或通过 [fivepost.ru/contacts](https://fivepost.ru/contacts/) 或个人 5Post 账户提交收件人工单（可附带不超过 40 MB 的照片）。

:::sources
- [5Post (fivepost.ru)](https://fivepost.ru/)
- [5Post FAQ](https://fivepost.ru/faq/)
- [5Post Contacts](https://fivepost.ru/contacts/)
- [universal-parcel-scraper 5Post 适配器](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/5post)
:::
