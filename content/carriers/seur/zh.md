---
title: SEUR 西班牙快递查询：单号、西语状态与 Pickup 自提点
description: 查询西班牙 SEUR 包裹：14 位与 21 位单号格式、西班牙语物流状态说明、Pickup 自提点与柜机、派送未成功及客服入口。
slug: seur-tracking
published: 2026-11-24
updated: 2026-11-24
---

SEUR 是西班牙的包裹和快递公司，隶属于同样运营 DPD 的 Geopost 集团。你可以在 seur.com 的 miSEUR 跟踪页免账号查询包裹，也可以通过 SEUR 跟踪链接，或直接把单号粘贴到上方查询框。

## SEUR 运单号格式与查找方式

SEUR 运单号通常为 14 位或 21 位数字，也支持 7 位编码：

| 格式（虚构） | 说明 |
| --- | --- |
| `48291736058419` | 14 位数字 |
| `739204815620374815264` | 21 位数字 |
| `7392048` | 7 位数字 |

请在商家的发货邮件或订单页面查找单号。完全找不到单号？查看[如何查找运单号](guide:find-tracking-number)（英文）。

14 位数字也符合 DPD、BRT 等其他快递的单号格式，请以商家发货邮件中注明的快递公司为准。

## SEUR 物流状态说明

SEUR 查询其网站提供西班牙语和英语版本。下表列出的是 SEUR 原文的全大写西班牙语状态——除签收状态外，Peek 也会按此原文展示：

| SEUR 状态 | 中文含义 |
| --- | --- |
| `EL ENVÍO HA SIDO REGISTRADO.` | 寄件人已创建运单，SEUR 尚未拿到实物包裹。 |
| `LA RECOGIDA HA SIDO REGISTRADA.` | 已预约上门揽收，SEUR 尚未拿到包裹。 |
| `LA RECOGIDA HA SIDO MODIFICADA.` | 已预约的揽收安排有变动。 |
| `LA RECOGIDA HA SIDO CONFIRMADA POR EL REPATIDOR.` | 快递员已将该揽收任务排入当天路线（*REPATIDOR* 为 SEUR 原文拼写）。 |
| `RECOGIDA NO REALIZADA: CLIENTE CERRADO O AUSENTE. SE PUEDE REPROGRAMAR UNA NUEVA FECHA DE RECOGIDA.` | 快递员未能揽收到件：寄件方关门或无人。这并非对你的派送失败，Peek 仍会显示“已生成面单”。 |
| `LA RECOGIDA HA SIDO REALIZADA.` | 快递员已完成揽收，SEUR 已拿到包裹。 |
| `EL ENVÍO HA SIDO DEPOSITADO EN UN PUNTO SEUR PICKUP.` | 寄件人已将包裹投寄到 SEUR Pickup 自提点，等待 SEUR 收走。 |
| `EL ENVÍO HA SIDO RECEPCIONADO EN LAS INSTALACIONES DE ORIGEN DE SEUR. COMIENZA EL TRÁNSITO A DESTINO` | SEUR 始发网点已收件，开始发往目的地。 |
| `EL ENVÍO ESTÁ EN TRÁNSITO. PRÓXIMAMENTE LLEGARÁ A LAS INSTALACIONES DE DESTINO.` | 正在运往负责你所在区域派送的目的地网点。 |
| `EN ENVÍO HA SIDO RECEPCIONADO EN LAS INSTALACIONES DE DESTINO. PRONTO SALDRÁ A REPARTO.` | 已到达当地派送网点，但尚未装车外出派送，因此 Peek 显示“运输中”（*EN ENVÍO* 为 SEUR 原文拼写）。 |
| `EL ENVÍO SERÁ ENTREGADO EN LA FECHA CONCERTADA. ACTUALIZAREMOS LA SITUACIÓN.` | 已约定派送日期。 |
| `EL ENVÍO HA SUFRIDO UN RETRASO Y ES POSIBLE QUE SE DEMORE LA ENTREGA. DISCULPA LAS MOLESTIAS.` | 发生延误，派送可能晚于原计划。 |
| `EL ENVÍO ESTÁ EN REPARTO.` | 今日正在派送途中。 |
| `EL ENVÍO ESTÁ EN REPARTO INTERNACIONAL.` | 跨境包裹正在派送途中。 |
| `EL ENVÍO NO SE HA ENTREGADO POR AUSENCIA O CIERRE. SE PUEDE REPROGRAMAR UNA NUEVA ENTREGA.` | 派送未成功：无人签收或场所关门，可重新预约派送。 |
| `EL ENVÍO NO SE HA ENTREGADO PORQUE LA DIRECCIÓN DE ENTREGA ES INCORRECTA.` | 未送达：收件地址有误。Peek 显示“派送未成功”。 |
| `EL ENVÍO NO SE HA ENTREGADO PORQUE NO ES LA DIRECCIÓN ACTUAL DEL DESTINATARIO.` | 未送达：地址错误或已失效。 |
| `NO SE HA PODIDO DEPOSITAR EL ENVÍO EN EL PUNTO SOLICITADO` | 未送达：指定的 SEUR Pickup 自提点无法接收该包裹。 |
| `EL ENVÍO ESTÁ DISPONIBLE PARA RECOGER EN EL PUNTO SEUR PICKUP.` | 已到达 SEUR Pickup 自提点，等待取件。 |
| `EL ENVÍO ESTÁ ENTREGADO.` | 已签收。 |
| `EL ENVÍO HA SIDO ENTREGADO A UN VECINO.` | 已交由邻居代收。 |

延误提示不会说明具体原因或延误时长。需要清关的包裹在派送前还会显示抵达海关、放行等清关步骤。

## SEUR 派送、Pickup 自提点与派送未成功

**派送未成功。** 物流会显示 `EL ENVÍO NO SE HA ENTREGADO POR AUSENCIA O CIERRE. SE PUEDE REPROGRAMAR UNA NUEVA ENTREGA.`，你可以重新预约派送。如果改为转投 SEUR Pickup 自提点，则会显示 `LOS DATOS DEL ENVÍO HAN SIDO MODIFICADOS Y SE ENTREGARÁ EN UN PUNTO SEUR PICKUP.`。

**SEUR Pickup 自提点与自提柜。** SEUR 拥有数千家 Pickup 门店和自提柜，可在 seur.com 查找。包裹到达后，状态会显示 `EL ENVÍO ESTÁ DISPONIBLE PARA RECOGER EN EL PUNTO SEUR PICKUP.`；从门店取走后会显示 `EL DESTINATARIO HA RETIRADO EL ENVÍO DE LA TIENDA SEUR PICKUP SELECCIONADA.`。如果指定的自提点无法接收，会显示 `NO SE HA PODIDO DEPOSITAR EL ENVÍO EN EL PUNTO SOLICITADO`；如果所选柜机无法投递，`EL ENVÍO NO HA SIDO DEPOSITADO EN EL LOCKER SELECCIONADO. SE DEBE ELEGIR OTRO PUNTO SEUR PICKUP.` 表示需要另选其他 SEUR Pickup 网点。

## SEUR 包裹延误、丢失或破损

对于刚发出尚无记录的包裹和查无此号的包裹，SEUR 都会返回同样的“未找到”提示，因此新发货件请稍等一段时间再查。如果仍然查不到，请向寄件人确认是否已交寄。已超过预计送达日？请看[物流停止更新怎么办](guide:tracking-not-updating)（英文）。

谁负责处理：如果是网购商品，请先联系商家。根据欧盟规定，在商品实际送达你手中之前，运输破损责任由卖家承担。

显示已签收但未收到？如果状态写着 `EL ENVÍO HA SIDO ENTREGADO A UN VECINO.`，请先询问邻居；更多排查步骤见[显示已签收但未收到](guide:delivered-not-received)（英文）。

## SEUR 客服入口

如需联系 SEUR 客服，请前往 seur.com 上的 miSEUR 客户支持专区。

> 2021 年 2 月，西班牙国家网络安全研究所（INCIBE）曾发布预警，指出出现冒充 SEUR 的诈骗短信：短信中的链接指向仿冒 SEUR 网站，诱导支付小额费用以盗取银行卡信息。INCIBE 建议：切勿点击短信链接，手动在浏览器输入 SEUR 网站地址查询包裹；如果已经输入银行卡信息，请尽快联系发卡银行。

## 使用 Peek 跟踪 SEUR 包裹

遇到 21 位数字时，[Peek](/) 会直接向 SEUR 查询。遇到 14 位数字时，Peek 会同时向多家可能匹配的快递查询，并对位于西班牙的用户优先查 SEUR。界面会先显示“正在查询物流服务…”，当只有 SEUR 有记录时显示“SEUR 有这个包裹的记录。”；否则点击“更换”并选择 SEUR。7 位单号也请这样手动选择——由于 SEUR 会复用 7 位码，Peek 不会自动拿 7 位数字去查 SEUR。带有 `tracking=` 参数的 miSEUR 或实时跟踪链接同样可直接粘贴，无需邮编。第 13 至 15 位为 `000` 的 21 位数字会被识别为[澳大利亚邮政](carrier:australia-post)，如需查 SEUR 请点击“更换”手动选择。

Peek 会显示包裹状态、物流轨迹和重量。由于 SEUR 的扫描记录不附带地点，地图上不会标出轨迹位置。状态文字保留 SEUR 的西班牙语原文，签收类状态会显示为“已签收”。Peek 会隐去包裹件数、寄件人、收件人以及派送 PIN 码和二维码。SEUR 的时间戳不含可靠时区，因此 Peek 按马德里时间解析并展示 SEUR 原轨迹。一票多件（多包裹）货件只能通过第三方物流跟踪服务跟进。你可以开启状态变化通知。

## SEUR 快递查询常见问题

### 为什么 SEUR 单号查不到？

可能是包裹刚寄出：SEUR 对刚创建的新件和查不到的单号返回相同的“未找到”提示。如果持续查不到，请联系寄件人确认包裹是否已经交寄。

### SEUR 显示延误提示是什么意思？

表示 SEUR 已标记该件延误，送达时间可能晚于原计划，但不会给出原因或延误天数。Peek 会将包裹维持在延误提示前的阶段。请等待下一次扫描，如已超过预计送达日，请联系 SEUR 或卖家。

### SEUR 和 DPD 是同一家公司吗？

不完全是：SEUR 是 Geopost 集团旗下的派送品牌之一，与 DPD 并列。

:::sources
- [Geopost: Who we are](https://www.geopost.com/en/about-us/who-we-are/) – SEUR 与 DPD 同为 Geopost 集团旗下派送品牌
- [Geopost: SEUR](https://www.geopost.com/en/about-us/our-companies-and-partners/seur/) – 西班牙快递服务、Pickup 门店与自提柜
- [Your Europe: Shipping and delivery](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_en.htm) – 欧盟规则下收货前的运输破损责任由卖家承担
- [INCIBE: Circulan SMS fraudulentos que suplantan a SEUR (西班牙语)](https://www.incibe.es/ciudadania/avisos/circulan-sms-fraudulentos-que-suplantan-seur-que-no-te-enganen) – 2021 年 2 月防诈骗警示：虚假短信、小额收费、盗刷银行卡、手动输入其网站及联系银行
- [Universal Parcel Scraper: SEUR](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/seur) – 免账号查询、西班牙语和英语跟踪页、单号格式、状态与阶段、“未找到”提示、客服页面、Peek 读取的内容
:::
