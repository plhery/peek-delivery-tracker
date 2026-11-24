---
title: PDU 友递联单号查询：PD 尾缀运单、轨迹状态与上海客服
description: 查询 PDU（友递联 / Parcel Delivery Union）跨境小包：12 位 PD 后缀单号规则、中英文及西语轨迹状态、清关与上海客服联系。
slug: pdu-youdilian-chaxun
published: 2026-11-24
updated: 2026-11-24
---

**PDU**（*Parcel Delivery Union*，运营主体为*上海友递联科技有限公司*，简称*友递联科技*）是一家成立于 2022 年、总部位于上海的跨境智能物流服务商（[pduni.com](https://www.pduni.com/)）。PDU 在北美、欧洲、非洲和亚洲 10 多个国家运营自营分公司与本地操作中心，为跨境电商卖家和制造企业提供端到端清关、揽收、海外末端派送及货到付款（COD）代收服务。你可以在 PDU 英文运单查询页 [pduni.com/MV/en/customer-services/waybill-inquiry](https://www.pduni.com/MV/en/customer-services/waybill-inquiry) 查件，或直接将单号粘贴到上方查询框中。

## PDU 友递联单号格式（`PD` 尾缀与自定义运单）

许多 PDU 跨境包裹使用 12 位运单号，由 2 位字母、8 位数字和 `PD` 尾缀构成（以下均为虚构示例）：

| 单号（虚构） | 归属与说明 |
| --- | --- |
| `MX48291736PD` | 12 位 PDU 跨境运单号（`MX` + 8 位数字 + `PD`）。 |
| `CO73918462PD` | 国际线路常用的另一组 12 位 `..PD` 运单号示例。 |

当你手动选择 PDU 时，其官网查询页面也支持其他 6 至 40 位字母数字服务运单号。该 12 位形态与 Colis Privé 存在重叠：Peek 默认先询问 Colis Privé 再询问 PDU（在中国地区则会优先询问 PDU）。详见[运单号格式说明](guide:tracking-number-formats)（英文）。

## PDU 友递联物流状态含义

PDU 为每票包裹和每条扫描分配状态代码（如 `TRANSIT`、`COLLECTING`、`DELIVERY`、`SIGNED` 等），并附带英文或目的国语言描述（拉美线路常见西班牙语，且常带本地快递短前缀如 `ENT,Entregado`，Peek 展示时会自动清理前缀）：

| PDU 状态码或扫描文案 | 对你的含义 | Peek 显示 |
| --- | --- | --- |
| `Shipment information sent to PDU.`、`Package not found in last mile or customs process.` | PDU 已收到电子订单数据，实物包裹尚未入库，或目的国末端网络尚未生成首条记录。 | “已生成面单” |
| `COLLECTING`（`Being Collected`）、`FINAL_COLLECTING`（`Last-Mile Collection`）、`TRANSIT`（`In Transit`） | 已在始发地揽收，或正在国际干线及区域分拨中心转运。 | “运输中” |
| `Recogida en Aduanas`、`Ingreso a CEDI`、`En tránsito nacional` | 已从海关提货、进入目的国分拨中心（CEDI）或正在当地国内转运。 | “运输中” |
| `DELIVERY`（不带出车派送字样） | 已移交进入目的国派送快递网络；在实际开始派送前 Peek 保持为运输中。 | “运输中” |
| `Salida a Ruta` | 当地快递员今天已装车上路派送。 | “派送中” |
| `DELIVERY_EXCEPTION`（`Delivery Exception`）、`TO_BE_RETURN`（`Awaiting Return`） | 出现派送异常或等待退回寄件人，请联系卖家或当地快递核实。 | “需要留意” |
| `RETURNED`（`Returned`） | 正在退回寄件人或始发仓库。 | “已退回寄件人” |
| `SIGNED`（`Delivered`）、`Entregado` | 已在收件地址妥投签收。 | “已签收” |

需要注意的是，早期出现的 `Package not found in last mile or customs process.` 仅表示目的国末端伙伴尚未扫描到该包裹，**并不**代表包裹被海关扣留。

## 自营清关、海外末端派送与货到付款（COD）

根据 [pduni.com](https://www.pduni.com/) 和 [PDU 帮助中心](https://www.pduni.com/MV/en/customer-services/help-center)：

- **自营清关与 AI HS 归类：** PDU 在 10 余个国家建有自营清关网络，支持自动化 HS 编码匹配与税费预付流程；根据帮助中心常见问题，PDU 对一般出口正式报关不另收费用，缺少清关资料时商家可在线补充提交。
- **海外末端派送：** 目的国海关放行（`Recogida en Aduanas` / `Ingreso a CEDI`）后，由 PDU 当地分公司或合作快递完成上门派送，并按订单提供货到付款（COD）代收。
- **进口税费：** 目的国进口增值税或关税取决于当地免税门槛及网店结账时是否已代收。详见[包裹被海关扣留怎么办](guide:customs)（英文）。

## PDU 包裹停滞、无更新或遗失怎么办

因为发件账号由电商商家或工厂在 PDU 开立，需要修改地址、退款或补发时，**请务必先联系卖家或购物平台**。

- **中国发运至目的国入网前静默：** 在 `Shipment information sent to PDU.`（或 `Package not found in last mile or customs process.`）与 `Recogida en Aduanas` / `Ingreso a CEDI` 之间，空海运合箱抵达目的国前常有数天无更新。请参阅[为什么物流停止更新](guide:tracking-not-updating)（英文）。
- **出现 `DELIVERY_EXCEPTION` 或 `TO_BE_RETURN`：** 请尽快向卖家核对收件地址与联系电话，以便当地站点在包裹转为 `RETURNED` 退件前安排重新派送。
- **显示已签收未收到或破损：** 请先核查家人、物业前台与邻居，并在平台买家保护期结束前向卖家发起理赔。详见[显示已签收但未收到](guide:delivered-not-received)（英文）、[包裹丢失](guide:lost-parcel)（英文）、[包裹破损](guide:damaged-parcel)（英文）和[包裹退回寄件人](guide:returned-to-sender)（英文）。

## PDU 友递联官方客服与上海联系信息

以下官方联系方式发布于 [PDU 运单查询页](https://www.pduni.com/MV/en/customer-services/waybill-inquiry) 与 [PDU 帮助中心](https://www.pduni.com/MV/en/customer-services/help-center)：

- **公司主体：** *上海友递联科技有限公司*（*友递联科技*，备案号 `沪ICP备2022026205号`）。
- **客服热线：** `021-50837888`（`+86 21 5083 7888`）。
- **客服邮箱：** `service@pduni.com`。
- **总部地址：** 中国上海市普陀区真北路中海大厦 6 楼 305 室。
- **官网查件与帮助中心：** [pduni.com/MV/en/customer-services/waybill-inquiry](https://www.pduni.com/MV/en/customer-services/waybill-inquiry) 与 [pduni.com/MV/en/customer-services/help-center](https://www.pduni.com/MV/en/customer-services/help-center)。

> PDU 帮助中心特别提醒用户警惕冒用 PDU 标识或联系方式的诈骗行为。切勿通过未经核实的短信链接支付关税或派送费——任何收费请先通过购物商家或官方快递门户确认。详见[快递诈骗短信识别指南](guide:delivery-scam-texts)（英文）。

## 使用 Peek 追踪 PDU 包裹

输入 12 位 `..PD` 运单号（如虚构单号 `MX48291736PD` 或 `CO73918462PD`）时，[Peek](/) 会依次询问 Colis Privé 和 PDU（在中国地区优先询问 PDU），并在 PDU 查到记录后显示“PDU 有这个包裹的记录。”。如果你的 PDU 单号采用其他 6 至 40 位格式，请在添加时点击“更换”或在包裹页点击“更换快递公司”并选择 **PDU**（搜索“Parcel Delivery Union”或“友递联”均可找到）。

Peek 直接读取 PDU 追踪接口，展示标准阶段（“已生成面单”、“运输中”、“派送中”、“需要留意”、“已退回寄件人”、“已签收”）、已去除末端快递代码前缀的扫描文案（例如显示 `Entregado` 而非 `ENT,Entregado`）、地点及签收时间。PDU 状态码用于判定阶段，不会出现在任何 Peek 应用界面中。PDU 每条扫描如附带 UTC 偏移则按原偏移解析，否则按北京时间处理，Peek 会统一换算为你设备的本地时间。PDU 追踪接口本身不提供预计送达日期。

在打开包裹页面或预计派送当天开启提醒时，只要 PDU 官方追踪接口正常响应，Peek 最高每 10 分钟检查一次；在 PDU 扫出 `Salida a Ruta` 的当天，自最新扫描所在地早上 6 点起最高每 2 分钟检查一次；其余时间每小时检查一次。若停留在“已生成面单”或“已揽收”满 12 小时，或连续两天无新动态，无论怎么关注都会转为每小时检查一次；停留在“已生成面单”满七天后降为每 6 小时一次。当 PDU 官网无响应、Peek 仅通过第三方追踪服务跟进时，Peek 每小时检查一次，静默满一天后改为每 3 小时一次，在派送当天打开页面或开启提醒时最高每 15 分钟检查一次。

当最新扫描满四天且包裹仍停留在“已生成面单”、“已揽收”或“运输中”时，Peek 会标出该扫描日期（如“自10月12日周一起没有物流更新”），并在页面提示“包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 PDU 查询。”。若超过三周没有新扫描，则显示“已超过三周没有新动态。如果还没收到，请联系卖家。Peek 会持续向 PDU 查询。”

你可以自由选择“每次扫描”、“重要环节”或“仅派送当天”通知预设。Peek 仅监控物流更新，无法代你清关、修改收货地址或代为联系 PDU。

## 关于 PDU 友递联追踪的常见问题

### PDU（*Parcel Delivery Union* / *友递联*）是什么公司？

PDU（*上海友递联科技有限公司*）是一家 2022 年创立于上海的跨境智能物流公司，在 10 多个国家提供国际清关、揽收、末端派送与 COD 代收服务。

### PDU 运单号是什么格式？

常见 PDU 跨境运单号为 12 位字符：2 位字母、8 位数字加 `PD`（如虚构示例 `MX48291736PD`）；PDU 官网也可查询其他 6 至 40 位字母数字运单。

### `Package not found in last mile or customs process.` 是指被海关扣了吗？

不是。在 PDU 查询结果中，这条早期状态仅代表电子运单数据已生成，包裹尚未进入目的国末端或清关系统完成首次实物扫描。

### 如何联系 PDU 友递联客服？

退款或修改派送信息请先联系网店卖家。PDU 在 [pduni.com/MV/en/customer-services/help-center](https://www.pduni.com/MV/en/customer-services/help-center) 公布的上海支持电话为 `021-50837888`，邮箱为 `service@pduni.com`。

:::sources
- [PDU 友递联官网](https://www.pduni.com/) – 2022 年成立于上海普陀区、10 余国自营清关与末端派送网络及 COD 服务
- [PDU 运单查询门户](https://www.pduni.com/MV/en/customer-services/waybill-inquiry) – 公开英文运单查询、`service@pduni.com` 与 `021-50837888`
- [PDU 帮助中心与常见问题](https://www.pduni.com/MV/en/customer-services/help-center) – 清关常见问题、防冒充诈骗提示及上海总部地址（*上海友递联科技有限公司*）
- [Universal Parcel Scraper: pdu](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/pdu) – `^[A-Z]{2}\d{8}PD$` 识别规则、`traceByWeb` 接口、状态与文案映射（`DELIVERY` 与 `Salida a Ruta`）、前缀清理及 UTC/北京时间处理
:::
