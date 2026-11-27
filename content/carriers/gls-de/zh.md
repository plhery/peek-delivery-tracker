---
title: GLS 德国物流查询：状态、ParcelShop 与客服
description: 查询德国 GLS 包裹：运单号与 Track-ID 查找、为何需要收件邮编、英文状态中文说明、ParcelShop 自提点与客服联系。
slug: gls-tracking-germany
published: 2026-11-24
updated: 2026-11-24
---

GLS 德国（GLS Germany）在工作日派送，据其集团公布数据，在德国拥有 9,000 个 ParcelShop 合作取件点和 200 座 Parcelstation 自提柜。你可以在 [GLS 物流查询页面](https://gls-group.com/EU/en/parcel-tracking)输入包裹单号与收件人邮编查件，或直接把单号粘贴到上方输入框中。单号位于商家邮件中，GLS 的投递通知卡上则印有 Track-ID。

## GLS 运单号与 Track-ID：在哪里查找

请先查看商家的发货确认邮件。一旦寄件人将包裹数据发送给 GLS 即可开始查询，境外商家通过 GLS 寄往德国的包裹同样在 GLS 集团页面查询。GLS 的纸质或邮件通知卡上印有一个 6 位字母的 Track-ID，在 GLS 查询系统中与包裹单号等效。

Peek 识别以下单号格式：

| 单号（虚构） | 说明 |
| --- | --- |
| `48291736058` | 11 位数字。[GLS 瑞士](carrier:gls-ch)、GLS 法国、GLS 奥地利、GLS 波兰、PostLogistics 等也使用该长度。 |
| `482917360580` | 12 位数字，最后一位为校验位。[BRT](carrier:brt)、[Mondial Relay](carrier:mondial-relay)、Colis Privé 等也使用 12 位数字。 |
| `KL482917` | 8 位字母与数字组合：部分 Track-ID 也呈此格式，同样适用于 GLS 瑞士、GLS 法国、GLS 荷兰和 Loggi。 |
| Track-ID | 通知卡上的 6 位字母。Peek 会将其当作包裹单号处理；如果未能识别，请改用包裹单号。 |

单号被拒？如果商家邮件中写明了其他快递公司，该单号属于那家公司：请参阅[运单号格式说明](guide:tracking-number-formats)（英文）。

## GLS 物流状态中文说明

GLS 的英文状态文案按包裹流转顺序列出如下：

| GLS 状态原文 | 对你的含义 |
| --- | --- |
| `The parcel data was entered into the GLS IT system…`、`The parcel has not been handed over to GLS.` | GLS 已收到寄件人预报数据，尚未收到实体包裹。 |
| `The parcel was handed over to GLS.` | GLS 已揽收包裹。 |
| `The parcel has not been released by customs.`、`Customs Consignment via Customs Portal` | 来自欧盟以外的包裹仍处于清关中；Peek 显示为“清关中”。 |
| `The parcel was released by customs.` | 海关已放行，包裹恢复转运；Peek 显示为“运输中”。 |
| `The parcel is in delivery.`、`The parcel is expected to be delivered during the day.` | 包裹已在 GLS 派送车上，预计今天送达。 |
| `The parcel is ready for collection at the ParcelShop.` | 包裹已在 ParcelShop 合作点或 Parcelstation 自提柜等你领取。 |
| `The parcel has been delivered.` | 已送达你本人，或已由邻居等代收人签收。 |

期间还会出现包裹到达或离开 GLS 包裹中心或网点的记录，表示正在转运中。如果司机上门无人或因其他原因未能妥投，Peek 会显示“派送未成功”。GLS 随后会再次派送一次，或将包裹送至附近的 ParcelShop、网点或 Parcelstation，并通过通知卡或邮件告知具体位置。如果逾期未取或无法派送，包裹将退回寄件人，Peek 显示为“已退回寄件人”。GLS 会把到达 ParcelShop 计为已送达，但实际上包裹仍在店里等你领取。`The parcel has not been handed over to GLS.` 会在寄件人实际交寄前一直保持为最新状态，请直接询问寄件人。

## GLS 派送时间、ParcelShop 与自提柜

**派送时间。** 周一至周五派送，周六绝不派送，GLS 不保证预计送达时间。

**信箱投递。** 重量不超过 2 公斤、尺寸不超过 DIN C4 且厚度不超过 5 厘米的小件包裹，可能直接投入你的信箱。

**家中无人。** 除非你或寄件人另有说明，司机可将包裹交给同住或同办公场所的人，或同一栋楼或最近一栋楼的邻居，并通过通知卡或邮件写明代收邻居姓名。否则 GLS 会请你到最近的 ParcelShop、网点或 Parcelstation 自提。GLS 最多进行两次派送尝试。

**更改派送。** 若 GLS 开放改派选项，你可以另选派送日或地址、转寄 ParcelShop 或选择网点自提；据 GLS 2018 年 1 月发布的消费者说明，这些选项对收件人免费。对于使用 GLS FlexDeliveryService 寄来的跨境包裹，GLS 会发送进度提醒、尽可能给出预计日期或时段并允许改派；如果你在境外商家结账时选择了 ParcelShop（ShopDeliveryService），包裹将直接送往门店而不再尝试送货上门。你也可以签发免签放置授权，让司机将包裹放在指定位置，放妥后 GLS 会发卡或邮件通知。

**ParcelShop 合作点**根据 GLS 面向商业寄件人的条款，对未妥投后转存的包裹至少保管 7 个工作日，逾期退回寄件人。

**Parcelstation** 是 GLS 的自提柜，开柜需使用你自己的设备。你可以在未妥投通知中按指引设置单次或永久拒绝投递至自提柜。

## GLS 包裹未送达、延误或破损

由于与 GLS 签合同的是寄件人，退款事宜需通过商家处理。

**物流不更新。** 请先联系寄件人，另见[物流为什么停止更新](guide:tracking-not-updating)（英文）。

**显示已签收但未收到。** 先查看物流是否写明了 ParcelShop，并检查信箱与邮件中是否有 GLS 通知卡写明代收邻居或放置地点。更多排查见[显示已签收但未收到](guide:delivered-not-received)（英文）。

**清关中。** 来自欧盟以外的包裹须办理清关。GLS 说明关税、进口增值税以及通常收取的清关服务费须在派送前付清；清关费由谁承担取决于寄件人选用的贸易条款。在欧盟，自 2026 年 7 月 1 日起，货值 150 欧元及以下的网购包裹按商品类别每类征收 3 欧元固定关税；自 2026 年 11 月 1 日起，无论货值多少，还需按商品类别每类缴纳 2 欧元欧盟处理费，由向海关申报的一方承担。更多详情见[清关中的包裹指南](guide:customs)（英文）。

**破损。** 德国《商法典》（HGB）第 438 条规定，明显破损应在收货当场指出（口头告知司机即可），隐蔽破损须在 7 天内、延误须在 21 天内以文本形式通知（按时发出通知即可）。收件人与寄件人均可报损或报失。按德国《民法典》（BGB）第 475 条，除非是你自行雇佣了商家未提供的承运商，否则商家承担运输途中的风险，因此请优先联系商家。

**争议调解。** 若包裹丢失或破损无法与 GLS 协商解决，无论你是寄件人还是收件人，均可向德国联邦网络管理局（Bundesnetzagentur）的邮政调解处（Schlichtungsstelle Post）免费申请调解，适用于 10 欧元至 2,500 欧元的争议，消费者提出申请时 GLS 必须参与。

**撤销权（Widerruf）。** 逾期不取件并不等于行使购物撤销权。按《民法典》第 355 条，消费者向商家进行远程购物（如网购）或场外购物，通常可在 14 天内无理由明确通知商家撤销合同；对于商品，期限自你实际收到商品起算（第 356 条），按时发出通知即有效。

## GLS 德国客服与联系方式

如果是网购包裹，商家是你的第一联系人。GLS 德国本地联系渠道位于德国网站 gls-pakete.de。GLS 集团建议将包裹状态咨询发往所在国客服，热线电话可在各国 GLS 网站查到；[集团在线联系表单](https://gls-group.com/GROUP/en/contact-form/)同样受理收件人咨询。如果是你自己通过 GLS-Pakete.de 或应用寄出的包裹，其 2023 年 8 月条款列出的联系方式为 versand-service@gls-germany.com 与 +49 6677 646907040。

> GLS 集团提醒警惕冒用其名义的虚假邮件、短信和网站。GLS 集团主站为 gls-group.com，仅该站及其链接的站点（如 gls-pakete.de）为官方站点。GLS 绝不会通过邮件要求收件人用银行卡、转账或在线支付货款；不过对于来自欧盟以外的包裹，GLS 或其授权代理可能会通过邮件、短信或信函要求缴纳关税与税费。使用 GLS Cash Service 的货到付款包裹仅在交付时向司机付现，GLS 建议不要为自己或邻居支付意料之外的到付款项。可疑邮件请以附件形式举报至 security@gls-group.com。

## 使用 Peek 查询 GLS 德国包裹

[Peek](/) 会就上述格式向可能的承运商发起查询。GLS 接口同时返回德国与瑞士包裹，Peek 会标注为 GLS 德国（“GLS Germany 有这个包裹的记录”），若 GLS 法国有该包裹的记录则标注 GLS 法国。如果你身在瑞士或列支敦士登（按手机地区设置或网络连接判定），Peek 会默认显示 GLS 瑞士及 4 位邮编输入框：若是寄往德国的包裹，点击“更换”并选择 GLS 德国即可。粘贴 gls-pakete.de 链接或带 `/DE/`、`/EU/`、`/GROUP/` 的 gls-group 链接也会直接归入 GLS 德国。

GLS 要求提供收件邮编才能返回详细物流历史，因此“收件邮编”为必填项；如果 GLS 用你输入的邮编未查到包裹，Peek 会给出提示并允许修改。任意国家的邮编格式均支持。邮编会发送给 GLS，必要时也会发送给物流查询服务。

随后即可查看最新状态、每条扫描的城镇与国家（标在地图上）、预计送达日、重量、签收时间，以及包裹存放在 ParcelShop 时的门店名称和地址。你的姓名、地址、联系方式、签收签名及寄件人参考号均会被去除。Peek 会将 GLS 的 ParcelShop 到店记录显示为“待取件”。所有时间按德国时间解析。Peek 大约每小时向 GLS 检查一次，派送当天同样如此（查询失败后间隔 4 小时），手动刷新不会提前触发。仅当 GLS 将包裹交接给瑞士邮政（Swiss Post）时 Peek 才会衔接追踪至下一家承运商；接手后遵循实际派送承运商的刷新频率。Peek 可在状态变化时向你发送通知。

## GLS 德国物流查询常见问题

### 我的 GLS 包裹现在在哪里？

输入单号与收件人邮编即可在 GLS 查询系统看到包裹状态：`The parcel is in delivery.` 表示包裹今天已在派送车上。

### 什么是 GLS Track-ID？

GLS 投递通知卡上印着的 6 位字母代码，在 GLS 物流查询中与包裹单号作用相同。

### GLS 派送需要多久？

GLS 在 gls-pakete.de 公布了预计送达时间但不作保证。其集团说明同一国家内通常在 24 小时以内送达，欧洲跨境一般为 24 至 96 小时。只有寄件人订购了 GLS 的 Guaranteed24Service 时，才保证德国境内（岛屿除外）次工作日（周一至周五）送达。

### GLS 保管包裹多久？

派送未成功后，ParcelShop、网点或 Parcelstation 至少保管 7 个工作日，逾期将退回寄件人。

### GLS 正规吗？

正规：GLS 德国主体为总部位于诺伊恩施泰因（Neuenstein）的 General Logistics Systems Germany GmbH & Co. OHG。但有诈骗信息冒用其名义，请注意上文防骗提醒。

:::sources
- [GLS: Parcel Tracking](https://gls-group.com/EU/en/parcel-tracking) – GLS 集团查询入口与通知卡 Track-ID 说明
- [GLS: About us](https://gls-group.com/GROUP/en/about-us/) – 德国 ParcelShop 与自提柜数量（2026 年 3 月）
- [GLS: Parcel, Logistics & Express](https://gls-group.com/GROUP/en/our-business/parcels/) – 境内与欧洲跨境时效、派送前缴关税/增值税与清关费
- [GLS: B2C Services](https://gls-group.com/GROUP/en/our-business/cross-border-services/b2c/) – 跨境包裹 FlexDeliveryService 与 ShopDeliveryService 说明
- [GLS: Contact](https://gls-group.com/GROUP/en/contact-form/) – 在线联系表单与各国网站热线提示
- [GLS Germany: General Terms and Conditions for business customers](https://gls-group.com/DE/media/downloads/AGB_GLS_Germany_2025-01_EN_2024-10-25_FINAL-WEB.pdf) – 周六不派送、参考时效、信箱小件、邻居代收、两次派送、7 个工作日保管期、退回寄件人、Parcelstation、改派与 Guaranteed24Service
- [GLS Germany: Terms for GLS-Pakete.de and the app](https://gls-group.com/DE/media/downloads/AGB_GLS-paketeApp_EN_2023-08-21.pdf) – 公司主体、通知卡或邮件、免签放置授权、邮政调解处与寄件人客服
- [GLS Germany: Consumer information on delivery options](https://gls-group.com/DE/media/downloads/180130_Verbraucherinformation_Umverfuegung_DE_EN.pdf) – 收件人改派选项免费说明（2018 年 1 月）
- [GLS: Security advice](https://gls-group.com/GROUP/en/security-advice/) – 防诈骗提醒、其网站域名、欧盟外税费通知、GLS Cash Service、举报邮箱与各国客服指引
- [Bundesnetzagentur: Schlichtungsstelle Post](https://www.bundesnetzagentur.de/DE/Vportal/Schlichtung/Schlichtung_Post/start.html) – 免费邮政调解、寄收双方适用与 10–2,500 欧元额度
- [Gesetze im Internet: § 438 HGB](https://www.gesetze-im-internet.de/hgb/__438.html) – 明显破损、隐蔽破损 7 天及延误 21 天通知期限
- [Gesetze im Internet: § 475 BGB](https://www.gesetze-im-internet.de/bgb/__475.html) – 消费者网购运输风险归属规定
- [Gesetze im Internet: § 355 BGB](https://www.gesetze-im-internet.de/bgb/__355.html) – 14 天无理由合同撤销权规定
- [Gesetze im Internet: § 356 BGB](https://www.gesetze-im-internet.de/bgb/__356.html) – 远程与场外交易撤销期起算规定
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 自 2026 年 7 月 1 日起 150 欧元及以下按类征收 3 欧元关税、自 2026 年 11 月 1 日起征收 2 欧元欧盟处理费
- [European Commission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 不限货值按类征收 2 欧元、商品项定义及申报人纳税义务
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Peek 读取的内容、GLS 的英文文案（含清关与退件）、Track-ID、每小时检查
:::
