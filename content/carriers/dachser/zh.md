---
title: Dachser 西班牙与葡萄牙物流查询：完整链接、状态与预约
description: 查询西班牙和葡萄牙的 Dachser 货运与大件配送：为什么需要完整追踪链接、常见英文状态含义、预约派送规则以及西葡官方联系方式。
slug: dachser-tracking
published: 2026-11-27
updated: 2026-11-27
---

Dachser 是一家德国物流集团，其在西班牙和葡萄牙的子公司主要在企业之间承运货物与托盘，同时也为预订了 targo on-site 服务的商家向个人家庭派送大件订单。要追踪发往西班牙或葡萄牙的货物，请打开你收到的配送邮件或消息中的追踪链接，它会指向 Dachser 的 Customer Iberia 页面；将完整链接直接粘贴到上方输入框中同样可以追踪。

## Dachser 追踪单号与完整追踪链接

Dachser 未公布固定的单号格式。其 Customer Iberia 页面会显示运单号（“N.º Expedición”）以及出现在追踪链接里的唯一编号（“N.º Único”，英文称 “Unique Number”）。Dachser 页面说明可为每票货物生成并向客户分享追踪链接，但未说明由谁通过何种渠道发送。没收到消息？请向商家索取，并参阅 [如何查找追踪单号](guide:find-tracking-number)（英文）。

真正起作用的是完整链接，样式如下（单号和密钥均为虚构示例）：

`https://customeriberia.dachser.com/customerarea/utilidades/seguimiento-publico/detalle?numeroUnico=48291057362&hash=EXAMPLE739`

除编号外，链接里还包含访问密钥：`hash=`，或者 `clave=` 搭配 8 位日期参数 `fecha=`。缺少密钥时页面不会显示任何内容，因此请完整复制整条链接，不要改动。

如果只单独输入数字，Dachser 单号看起来会和许多其他承运商重叠（以下均为虚构数字）：

| 单号 | 追踪工具可能识别为 |
| --- | --- |
| `4829105736` | 10 位数字：[DHL](carrier:dhl)、Mondial Relay 等。 |
| `48291057362` | 11 位数字：GLS 等。 |
| `482910573621` | 12 位数字：MRW、BRT 等。 |
| `4829105736214` | 13 位数字：Delhivery 等。 |
| `19482910573680` | 14 位数字：SEUR、DPD 等。 |

如果通知消息写明承运商为 Dachser，请以消息为准，而不要仅靠位数判断。

## Dachser 物流状态含义解析

Customer Iberia 页面的界面标签提供西班牙语、葡萄牙语、英语和法语，但每条事件的文本直接来自 Dachser 对应货运的系统记录。历史记录表在英文界面名为 “Delivery Exceptions Historical”（西班牙语 “Historial de Incidencias”），虽然字面上带“异常”，但实际会列出所有事件，而不仅仅是异常情况。

dachser.es 和 dachser.pt 上的 “Track & Trace” 按钮会打开集团另一套工具 shipmentpointer。Dachser 帮助文档用以下英文措辞解释各状态，你通过链接看到的页面文案可能略有不同：

| Dachser 英文说明 | 对你的含义 |
| --- | --- |
| `The shipment has no status yet.` | [已创建运单](guide:tracking-statuses)（英文）：Dachser 已收到电子数据，但尚未拿到实物。 |
| `The shipment is in handling.` | 货物正在 Dachser 场站内分拣、装卸中转。 |
| `The shipment is in main carriage.` | 正在各分拨中心之间的干线长途运输中。 |
| `The shipment is under delivery in short distance traffic.` | 已装上当地短途配送车辆，正在派送途中。 |
| `The shipment is not yet in delivery or could not be successfully delivered.` | 货物暂缓派送，或此前派送未能成功。 |
| `The shipment has been delivered.` | 已送达。 |
| `The shipment has been booked out (e.g., cancellation).` | 已取消。 |

容易让人困惑的几项说明：

- “In handling” 指在 Dachser 自家仓库或分拨站操作，并非移交给了其他公司。
- “Main carriage” 指始发分拨站与目的分拨站之间的干线运输。
- “Not yet in delivery or could not be successfully delivered” 涵盖多种情况，Dachser 西班牙语帮助页举了三个例子：收件人要求提前通知、拒收货物或地址错误。请核对商家登记的手机号与收货地址是否正确。
- “Booked out” 意思是已取消，而不是已登记入库。

## Dachser 派送：预约与错过派送

**预约派送。** Dachser 西班牙条款说明，targo on-site 派送不固定具体送达时间，通常由网点致电收件人约定，因此请留意来电。Dachser 将 targo on-site 描述为预约送货至路边（上午或下午）；选择 targo on-site plus 时，送货员会将货物搬至使用地点，部分国家还支持晚间或周六派送；选择 targo on-site fix 时，你在下单时即可与商家敲定送达日期。

**在线选定日期。** 部分货运页面可能出现 “Arrange date.” 按钮，供你选择 “Delivery date”（派送日期）与 “Time range”（时间段）并提交确认。如果页面上没有该按钮，请等待 Dachser 来电或询问商家。

**派送日期与时效。** Dachser 在周日及法定节假日不承担派送义务。周六派送仅适用于 targospeed 10 和 targofix 10 服务，且需提前与 Dachser 分支机构安排。公布的时效仅供参考，从揽收后的下一个工作日起算，不含周末和节假日。

**无自提点或快递柜。** Dachser 西班牙语与葡萄牙语官网均未提及面向收件人的自提点、快递柜、未投递通知单、派送次数或保管期限。

**错过派送。** 根据 Dachser 西班牙和葡萄牙条款，因现场无人、地址错误或不完整或拒收导致的派送失败，责任和费用均由寄件人承担；二次派送属于寄件人的付费附加服务。因此一旦错过派送，请尽快通知商家。

## Dachser 货运延误、破损或丢失

你是向商家购买商品的，与 Dachser 签订运输合同的是商家或寄件人，因此遇到延误、丢失或退款问题应首先联系他们。

**无更新。** 先查看是否有来自 Dachser 的未接来电，再参阅 [物流停止更新](guide:tracking-not-updating)（英文）。

**破损。** Dachser 条款要求在交接货物时以书面形式注明短少或破损，因此请在司机离开前检查货物并在交货单据上写明任何损坏。以下时限适用于西班牙和葡萄牙的 Dachser 合同条款：

- **隐蔽破损：** 国内运输须在签收后 7 个自然日内提出；国际运输须在 7 天内（不含周日和节假日）提出。
- **延误：** 国内运输须自签收次日起 21 天内提交书面异议。

请保留原包装以备 Dachser 查验，并在当天通知商家。

**显示已送达但没收到。** 请要求商家核查签收凭证，并参阅 [显示已送达却未收到](guide:delivered-not-received)（英文）。

## Dachser 联系电话与客服

Dachser 未设专门的收件人客服热线。其西班牙和葡萄牙官网公布的联系方式如下：

- **西班牙：** DACHSER Spain, S.A.（Zubieta, Guipúzcoa），电话 +34 916 60 65 78，邮箱 dachser-spain.iberia@dachser.com。官网未注明办公时间或资费。
- **葡萄牙：** DACHSER Portugal（Vila do Conde），电话 +351 221 210 500（葡萄牙国内座机费率），邮箱同上。未注明办公时间。
- **联系表单与网点：** dachser.es 和 dachser.pt 均设有联系表单，dachser.es 还提供网点查询。
- **葡萄牙投诉簿：** dachser.pt 底部链接有葡萄牙官方投诉簿（Livro de Reclamações）。
- **德国总部：** DACHSER SE（Kempten），电话 +49 831 5916 0，邮箱 info@dachser.com。

## 使用 Peek 追踪 Dachser 配送

[Peek](/) 无法仅凭数字识别 Dachser，也不能在缺少密钥的情况下查询，因此请将消息中的完整链接直接粘贴进来：Peek 会自动识别为 Dachser 并保存链接。如果你只输入了单号，请点击“更改”、选择 Dachser，然后把完整链接粘贴到“完整追踪链接”栏中。地址不符、缺少密钥、包含未知参数或单号不一致的链接会被拒收；未提供链接时，Peek 会提示使用发货邮件中的完整追踪链接。

Peek 仅读取 Customer Iberia 页面，因此覆盖西班牙和葡萄牙的 Dachser 配送。它会显示当前状态、轨迹历史，以及派送前显示的预计送达日期。每条节点会以 Peek 自身文案呈现（如已收到货运信息、已揽收、到达 Dachser 站点、派送中、预约已更新或已送达），不保留 Dachser 原始文本。时间按 Dachser 提供的时区（若未提供则按马德里时间）读取并换算为你设备的本地时区。由于 Dachser 不提供具体地点，地图上几乎无定位点可标；寄件人、你的联系方式及签收人签名亦不会展示。

只要 Dachser 追踪页正常响应，当包裹打开在屏幕上或开启通知且预计当天送达时，Peek 最高每 10 分钟检查一次；在货物进入派送当天，自承运商时区早上 6 点起，如包裹处于打开状态或已开启通知，最高每 2 分钟检查一次。关闭页面且未开通知时通常每小时检查一次。若仅由综合追踪服务返回数据，Peek 每小时检查一次，最新扫描满一天后每 3 小时一次，派送当天在屏幕打开或开启通知时最高每 15 分钟一次。直接查询承运商时，连续两天无新动态（处于“已创建运单”或“承运商已揽收”时为 12 小时）改为每小时检查一次。你可以设置接收每次扫描、重要节点或仅派送日通知。查无此单或链接失效时可能显示为未找到或查询失败。

## Dachser 追踪常见问题

### Dachser 总部在哪里？

位于德国肯普滕（Kempten）：DACHSER SE 是一家家族物流集团，由 Thomas Dachser 于 1930 年创立。DACHSER Spain, S.A. 注册地位于 Zubieta（Guipúzcoa），DACHSER Portugal 位于 Vila do Conde。

### Dachser 会送货到个人家里吗？

会，前提是商家预订了该服务：targo on-site 按预约为个人客户提供上午或下午的路边送货；如果需要搬入室内使用位置，则对应由商家选订的 targo on-site plus。

### 可以更改 Dachser 的派送日期吗？

有时可以。如果 Customer Iberia 页面显示 “Arrange date.” 按钮，可直接在线选择日期和时间段；如果没有，请接听 Dachser 来电或联系商家协调。

:::sources
- [DACHSER Spain: Condiciones generales European Logistics](https://www.dachser.es/downloads/Spain/Spain_Terms_Conditions.zip) – 电话预约、周日与周六规则、参考时效、错过派送费用、附加费与索赔期限
- [DACHSER Portugal: Condições gerais de transporte](https://www.dachser.pt/downloads/Portugal/Portugal_Terms_Conditions.zip) – 参考时效、错过派送费用、附加费与索赔期限
- [DACHSER Spain: targo on-site fix permite fijar una fecha de entrega al realizar un pedido](https://www.dachser.es/es/mediaroom/DACHSER-amplia-su-cartera-de-servicios-para-entregas-B2C-24216) – targo on-site、路边派送、plus 与 fix
- [DACHSER Portugal: DACHSER disponibiliza serviço entargo em Portugal e Espanha](https://www.dachser.pt/pt/mediaroom/DACHSER-disponibiliza-servico-entargo-em-Portugal-e-Espanha-16917) – 预约派送、上午与下午
- [DACHSER Spain: eLogistics](https://www.dachser.es/es/elogistics-116) – 向客户分享追踪链接
- [DACHSER Spain: Información legal](https://www.dachser.es/es/informacion-legal-273) – 公司信息、Zubieta、电话、邮箱、DACHSER SE
- [DACHSER Portugal: Aviso legal](https://www.dachser.pt/pt/aviso-legal-273) – 公司信息、Vila do Conde、电话、邮箱
- [DACHSER Spain: Contacto](https://www.dachser.es/es/contacto-275) – 联系表单
- [DACHSER Portugal: Contacto](https://www.dachser.pt/pt/contacto-275) – 联系表单
- [DACHSER Spain: Home](https://www.dachser.es/es/) – Track & Trace 打开 shipmentpointer
- [DACHSER Portugal: Home](https://www.dachser.pt/pt/) – Track & Trace 与 Livro de Reclamações
- [Dachser Customer Iberia: public shipment page](https://customeriberia.dachser.com/customerarea/utilidades/seguimiento-publico/detalle) – 完整链接打开的公开页面
- [Dachser Customer Iberia: English page texts](https://customeriberia.dachser.com/customerarea/assets/i18n/common/en.json) – 查询参数、历史表与预约日期按钮
- [Dachser Customer Iberia: Spanish page texts](https://customeriberia.dachser.com/customerarea/assets/i18n/common/es.json) – N.º Único 与 Historial de Incidencias
- [Dachser: shipmentpointer help (English)](https://elogistics.dachser.com/help/en/shp.html) – 英文状态说明
- [Dachser: shipmentpointer, ayuda](https://elogistics.dachser.com/help/es/shp.html) – 状态示例与追踪链接
- [Dachser: Company](https://www.dachser.com/en/company-10) – 肯普滕、1930 年、家族企业
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Peek 读取与展示的数据
:::
