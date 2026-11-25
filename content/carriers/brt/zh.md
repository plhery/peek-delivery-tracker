---
title: BRT 意大利快递查询：单号、状态与未妥投处理
description: 查询意大利 BRT 快递包裹：BRTcode 与运单号查找、英文状态中文对照、未妥投寄存、fermopoint 自提点与自提柜及客服联系方式。
slug: brt-tracking
published: 2026-11-24
updated: 2026-11-24
---

BRT 是一家意大利特快包裹快递公司，与 DPD 一样隶属于 Geopost 集团。你可以通过 BRT 短信或邮件中的链接、在 [brt.it](https://www.brt.it/en/) 的“Where is my shipment?”查询，或直接把单号粘贴到上方输入框中。只要寄件人向 BRT 提供了你的联系方式，BRT 在揽收包裹后便会向你发送该单号（即 BRTcode）。

## BRT 运单号及查找位置

BRTcode 位于 BRT 发送的短信和邮件、面单以及派送未成功后留下的纸质通知单上。没有收到消息？请参阅[其他查找运单号的位置](guide:find-tracking-number)（英文）。

BRT 官方各页面对长度说法不一，请按格式辨认：

| 格式（虚构） | 说明 |
| --- | --- |
| `482917360584` | 12 位数字：面单上的发货编号（shipment number）。 |
| `48291736058419` | 14 位数字：BRTcode 或发货编号。 |
| `482917360584192` | 15 位数字：寄件人贴在包裹上的包裹 ID（parcel ID）。 |
| 19 位数字 | 按 BRT 查询表单说明，也可作为 BRTcode。 |

用于修改派送安排的 3 位 BRT 安全码并不是运单号。

在 brt.it 查不到？可能是输错了数字，或包裹尚未进入 BRT 网络。BRT 的[高级查询](https://vas.brt.it/vas/sped_numspe_par.htm)可查近 12 个月记录（按包裹 ID 查询为 2 个月）。

14 位数字也适用于德国、瑞士和英国的 DPD，12 位数字也适用于 Mondial Relay、Colis Privé 等承运商，因此请核对商家邮件或短信中写明的承运商。更多格式对比见[运单号格式说明](guide:tracking-number-formats)（英文）。

## BRT 物流状态中文说明

vas.brt.it 上的 BRT 查询页面默认以意大利语打开，可切换英文；以下为其英文文案。表头仍保留意大利语：`Data`（日期）、`Ora`（时间）、`Filiale`（负责网点，咨询时联系此网点）、`Evento`（事件）。brt.it 首页跳转的 myBRT 页面文案可能有所不同。

| BRT 状态原文 | 对你的含义 |
| --- | --- |
| `SHIPPING INFO SENT TO BRT` | BRT 仅收到寄件人预报资料，尚未拿到包裹：属于[“已生成面单”阶段](guide:tracking-statuses)（英文）。 |
| `COLLECTED` | BRT 已向寄件人完成揽收。 |
| `DEPARTED` | 已离开 BRT 网点或转运枢纽。 |
| `ARRIVED AT DEPOT` | 已到达 `Filiale` 一栏所标明的网点。 |
| `FOR DELIVERY` | 正在快递员车上外出派送。 |
| `NOTICE OF ATTEMPTED DELIVERY` | 派送未成功：无人签收，快递员已留下通知。 |
| `UNKNOWN/INCOMPLETE CONSIGNEE` | 派送未成功：未能找到收件人或地址。 |
| `TURMOIL` | 因公共游行或示威活动暂时延误。 |
| `PATRONAL HOLIDAY` | 目的地城镇正值守护神节假期，包裹在到达派送网点前顺延一天，当天不派送。 |
| `DELIVERED` | 已签收。 |
| `ARRIVED AT BRT-fermopoint` | 包裹已到 BRT-fermopoint 合作门店等你领取。 |
| `COLLECTED AT BRT-fermopoint` | 已在 fermopoint 门店取件。 |
| `ARRIVED AT BRT LOCKER` | 包裹已存入 BRT 自提柜待领。 |
| `COLLECTED AT BRT LOCKER` | 已从自提柜取走。 |
| `To collect at the parcel shop` | 境外派送：当地合作承运商已将包裹存放至其包裹自提点。 |
| `RETURNED TO SENDER` | 包裹正在退回寄件人。 |

单独出现的 `COLLECTED` 表示 BRT 从寄件人处揽收，并非你本人取件。`UNKNOWN/INCOMPLETE CONSIGNEE` 中的 consignee 指收件人，BRT 将收件人不明或地址不全列为包裹转入寄存的原因之一（见下文）。若 `DELIVERED` 出现在 `RETURNED TO SENDER` 之后，表示包裹已退回到寄件人手中。

## BRT 未妥投处理：重新派送、fermopoint 与自提柜

如果你不在家，BRT 会通过邮件、短信或纸质单留下通知，通常在下一个工作日再次派送。BRT 服务宪章注明会进行第二次尝试，若仍未成功，当地网点会致电联系。

通过 BRT 通知里的链接，或在 myBRT 应用及 mybrt.it 输入 BRTcode 加邮编或 PIN 码，你或许可以另选日期或时段、改投 BRT-fermopoint 或网点自提、解除寄存滞留，或在寄件人允许时修改地址。

**寄存滞留（“in giacenza”）。** 原因包括收件人不在或查无此人、地址有误或不完整以及拒收；物流详情会标明具体原因。可通过 BRT 通知或查询页面解除寄存。BRT 会向寄件人征询处理指示；其服务宪章说明，如寄件人未给出书面指示，将在通知寄件人 10 天后退回包裹（商家合同另有约定的除外）。寄件人给出指示后，详情页会注明是否产生仓储费以及由寄件人还是收件人承担；BRT 未公开具体金额。

**前往网点自提**时，可本人领取，或委托他人携带其身份证件、你的身份证件复印件以及 BRT 授权委托表代领。

**BRT-fermopoint** 合作商户（报刊亭、烟草店、文具店等，部分周末营业）在包裹到店后会发消息通知，并保管 10 天。

**BRT 自提柜**（设于商店、购物中心和加油站，室外柜机 24 小时开放）支持单件 20 公斤以内包裹：扫描 BRT 通知里的二维码或输入 8 位 PIN 码即可开柜。若遇屏幕故障或空柜格，请使用 BRT 取件邮件底部的表单报修。

## BRT 包裹延误、丢失或破损

BRT 说明其派送时效仅供参考。如果物流停止更新，请联系与 BRT 签订运输合同的商家，或联系物流中注明的网点，或在 BRT [客服支持页面](https://services.brt.it/en/support)选择“The delivery is delayed”；另见[物流不更新怎么办](guide:tracking-not-updating)（英文）。

若发生丢失或破损，如运费由你直接支付，可使用 BRT 破损申报表单并附照片；否则请通知寄件人。收货当场发现明显破损必须在面单上具体写明（BRT 表示笼统备注无效）；意大利境内包裹的隐蔽破损须在签收后 8 个自然日内书面申报。

收件人也可提出投诉：在 [Carta dei servizi 页面](https://www.brt.it/it/carta-dei-servizi/)下载 BRT 投诉表 PDF，并附到 services.brt.it/it/form-agcom 表单提交。BRT 会在 45 天内答复。若对回复不满，可通过 conciliazioni@brt.it 与 Casa del Consumatore 申请调解（需加入该消费者协会，年费 50 欧元，50 欧元以内争议为 25 欧元），之后可向意大利通信监管机构 AGCOM 申诉。如果包裹[显示已签收但未收到](guide:delivered-not-received)（英文），请先询问邻居和家人，再使用 BRT 支持页面对应的申报表，之后再走正式投诉流程。

## BRT 客服电话与联系方式

BRT 为收件人提供的联系渠道如下：

- **在线表单：** 通过已替代“Contact us”的 BRT [客服支持页面](https://services.brt.it/en/support)，按问题类型（延误、破损、已签收未收到等）进入申报表单。该页面不显示电话号码。
- **免费热线：** 800 278 708，周一至周五 8:30 至 18:30（载于 BRT 意大利语服务宪章；BRT 未说明是否支持境外拨打）。
- **电子邮件：** assistenzaclienti@brt.it（同样载于服务宪章），邮件中须写明你的姓名、寄件与收件邮编及 BRTcode，否则 BRT 不予回复。
- **在线聊天与网点：** 在 brt.it 使用在线聊天，或通过[网点查询](https://services.brt.it/en/customer-service/search-for-a-branch)联系当地网点。

BRT 文件中标注的博洛尼亚电话号码是总部办公电话，并非客户服务热线。

> 2026 年 4 月 16 日，BRT 发出防骗提醒，指出有诈骗分子冒用其名义发送虚假短信和邮件（例如谎称派送失败）。请核实发件人，如有疑问切勿点击链接或打开附件。

## 使用 Peek 查询 BRT 包裹

[Peek](/) 会识别 BRT 的 12 位、14 位、15 位数字单号以及以 `EOB` 开头的包裹 ID。对于 `EOB` 编号及大多数 15 位数字，无论你身处哪个国家，Peek 都只向 BRT 查询；对于 12 位数字，Peek 会同时向 BRT 及其他共用该格式的承运商查询。对于 14 位数字，如果你所在国家为意大利（在 iPhone 和 Android 应用中按手机地区设置判定，网页端按添加包裹时所在国家判定），Peek 会立即同时查询 BRT 和 DPD 各网络；在其他国家通常会先查 DPD 各网络及其他承运商，均无结果时再查 BRT。带有 `brtCode=` 的 vas.brt.it 链接同样适用；19 位 BRTcode 和 mybrt.it 链接不支持。

Peek 会显示 BRT 的英文状态（自提柜或 fermopoint 取件统一显示为“已签收”）。若将 Peek 语言设为意大利语，则会显示 BRT 的意大利语原文，签收显示为“Consegnata”；若包裹有 Peek 只知道英文文案的扫描（例如自提柜扫描），整个包裹仍以英文显示。页面会在地图上标出每条扫描地点、显示包裹重量并可在状态变化时通知你，同时略去寄件人、收件人、商家参考号、总件数、体积及取件点详情。BRT 本身不提供预计送达日，但对于 14 位单号，Peek 可以引用同样支持读取 BRT 单号的 DPD 预计日期（若由 Chronopost 承运至意大利，也可引用 Chronopost 的预计日期）。该预计日期会在过期后消失，若 BRT 提前签收、退回、转存自提点、派送失败或报告异常也会随之撤下。BRT 扫描不含时区：Peek 按意大利时间（境外站点按当地站点时间）解析，并严格按 BRT 原始顺序列出历史记录。

Peek 最高每 10 分钟检查一次；包裹进入派送当天，只要 BRT 或 DPD 官方查询正常响应，自当地时间早上 6 点起（若 DPD 或 Chronopost 提供了派送时段，则自时段开始前半小时起）最高每 2 分钟检查一次（DPD 需用浏览器查询时为每 5 分钟）；通过物流查询服务时，在派送当天打开页面或开启提醒时最高每 15 分钟检查一次。若连续两天无新动态（包裹仍处于“已生成面单”或“已揽收”状态时为 12 小时），Peek 会转为每小时检查一次。

## BRT 物流查询常见问题

### 我的 BRT 包裹现在在哪里？

最新一条扫描记录会告诉你位置：`ARRIVED AT DEPOT` 标明了存放包裹的网点，`FOR DELIVERY` 表示包裹已在快递员车上。还没有任何记录？可能尚未交给 BRT。

### BRT 和 DPD 是同一家公司吗？

不完全一样：两者同属 Geopost 集团旗下品牌。通过 DPD 网络发往意大利境外的包裹，BRT 详情页会提供跳转至 DPD 物流查询的链接。

### BRT 派送需要多久？

据 BRT 说明，意大利境内通常为 24 至 48 小时，卡拉布里亚、西西里、撒丁岛及 800 公里以上路程顺延 12 至 24 小时；发往大多数欧洲国家为 48 至 72 小时。

### 离我最近的 BRT 合作包裹点在哪里？

使用 BRT 的 [fermopoint 网点查询](https://www.brt.it/en/brt-fermopoint/)即可查找附近门店。超过 20 公斤的包裹无法投递至合作包裹点。

:::sources
- [BRT: FAQ](https://www.brt.it/en/customer-service/faq/) – BRTcode、派送、寄存、时效与破损说明
- [BRT: Codes and Support](https://www.brt.it/en/customer-service/codes-and-support/) – BRTcode 查找位置
- [BRT: Home](https://www.brt.it/en/) – “Where is my shipment?”查询入口
- [BRT: Rintraccia spedizione](https://vas.brt.it/vas/sped_numspe_par.htm) – 单号长度与查询保留期
- [BRT: Shipment detail help](https://vas.brt.it/vas/aiuto/aiuto.hsm?urlaiuto=sped_det_show.htm&lang=en) – 寄存、费用、网点与 DPD 衔接说明
- [BRT: Privati](https://www.brt.it/it/privati/) – myBRT 与 fermopoint 查找工具
- [BRT: BRT-fermopoint](https://www.brt.it/en/brt-fermopoint/) – 合作门店、周末营业与 10 天保管期
- [BRT: Locker](https://www.brt.it/en/network/locker/) – 二维码、PIN 码与 20 公斤限制
- [BRT: Branch pickup FAQ](https://www.brt.it/en/faq/can-i-pick-up-my-shipment-at-a-brt-branch/) – 网点代领所需证件与授权书
- [BRT: Support](https://services.brt.it/en/support) – 延误、破损、已签收未收到申报表单
- [BRT: Search for a branch](https://services.brt.it/en/customer-service/search-for-a-branch) – 当地 BRT 网点查询
- [BRT: Carta dei servizi PDF](https://www.brt.it/wp-content/uploads/sites/275/2026/11/261105-Carta-dei-Servizi.pdf) – 派送次数、寄存、破损、联系方式与 45 天答复期限
- [BRT: Carta dei servizi](https://www.brt.it/it/carta-dei-servizi/) – 投诉、调解与 AGCOM 说明
- [BRT: Modulo di Reclamo](https://www.brt.it/wp-content/uploads/sites/275/2026/02/2.-Modulo-Reclamo.pdf) – 收件人可提出投诉表单
- [BRT: Conciliation instructions](https://www.brt.it/wp-content/uploads/sites/275/2026/02/3.-Modulo-di-Conciliazione-con-istruzioni.pdf) – 调解协会会费标准
- [BRT: Condizioni generali](https://www.brt.it/wp-content/uploads/sites/275/2026/01/CONDIZIONI-GENERALI-CONTRATTO-TARIFFARIO_IT_CON-LOGO_REV-31.pdf) – 合同约定寄存天数
- [BRT: Alert phishing](https://www.brt.it/en/news/2026/04/16/alert-phishing-sms-email/) – 2026 年 4 月 16 日防钓鱼提醒
- [DPD: Home](https://www.dpd.com/en/) – Geopost 集团旗下品牌说明
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Peek 读取的内容、DPD 与 Chronopost 的派送时段
- [Peek: Routing](https://github.com/plhery/peek-delivery-tracker/blob/main/docs/ROUTING.md) – Peek 的检查频率：最高每 10 分钟一次，派送中每 2 或 5 分钟一次，派送时段开始前半小时，通过物流查询服务时每小时一次（派送中最高每 15 分钟一次），无动态时每小时一次
:::
