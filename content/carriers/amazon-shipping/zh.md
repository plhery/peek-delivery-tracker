---
title: Amazon Shipping 快递查询：单号、问题与求助
description: 查询其他商家通过 Amazon Shipping 寄出的包裹：UK/FR/TBA 单号格式、与 Amazon Logistics 区别、未收到处理与求助渠道。
slug: amazon-shipping-tracking
published: 2026-11-24
updated: 2026-11-24
---

Amazon Shipping 是亚马逊面向其他企业提供的包裹派送服务，因此亚马逊以外的卖家也可以通过亚马逊网络寄出你的订单。你可以凭运单号在 Amazon Shipping 公开查询页面（英国单号前往 track.amazon.co.uk）查件；该页面注明无论你是在 Amazon.co.uk 还是其他任何网站下单，货件都由卖家创建。其他亚马逊订单则在“我的订单（Your Orders）”中跟进。

## Amazon Shipping 运单号与单号格式

Peek 会将以下格式识别为亚马逊运单号：

| 格式（虚构） | 说明 |
| --- | --- |
| `UK4829105736` | 国家代码加 10 位数字，此处为英国。 |
| `FR4829105736` | 其他欧洲国家代码规则相同（`FR`、`IT`、`ES`、`DE` 等）。 |
| `TBA482910573649` | `TBA`、`TBC` 或 `TBM` 加 12 位数字。 |

需要注意：负责派送亚马逊自营订单的 Amazon Logistics 使用完全相同的单号格式，只有亚马逊公开查询系统能区分两者——亚马逊自营订单在公开查询页会返回“未找到”。如果查不到且你是在亚马逊本身下单的，它属于 Amazon Logistics 包裹，通常在亚马逊“我的订单”中跟踪。像 `408-2917364-8291057` 这样的订单号并非运单号。手头没有运单号？请向发货商家索要，或查看[如何查找运单号](guide:find-tracking-number)（英文）。

## Amazon Shipping 包裹未送达、下落不明或丢失

在英格兰，公民咨询局（Citizens Advice）建议：对于网购商品，在向快递公司投诉或索赔之前应先尝试联系卖家，这样通常能得到更好的结果。

**包裹丢失，或显示已签收但未收到。** 请联系卖家申请退款或重新发货。更多排查见[显示已签收但未收到](guide:delivered-not-received)（英文）。

**包裹破损。** 请联系卖家并了解退回破损商品有哪些选择。

## Amazon Shipping 客服与联系渠道

如前所述，如果包裹未送达或送达时破损，你的联系对象是寄出商品的卖家。

> 若收到提及购物的消息，亚马逊建议切勿回复或点击其中任何链接。亚马逊不会通过电话或电子邮件索要付款，也绝不会要求购买礼品卡，并提醒不要拨打短信、邮件或搜索结果中给出的电话号码。可疑信息请通过亚马逊自助举报工具上报。更多防骗建议见[快递诈骗短信指南](guide:delivery-scam-texts)（英文）。

## 使用 Peek 跟踪 Amazon Shipping 包裹

将运单号或 track.amazon 链接（`/tracking/` 后跟单号）直接粘贴到 [Peek](/)。Peek 会向亚马逊公开查询系统核实该单号是否存在。如果亚马逊返回记录，界面会提示“Amazon Shipping 提供公开的物流信息。Peek 会及时更新这个包裹的动态。”否则它会被视作 Amazon Logistics 包裹，通常需要在亚马逊“我的订单”中跟踪。如果核实无法运行，请重试。Amazon Shipping 不会出现在“更换”快递列表中，这次核实是进入该承运商的唯一入口，无需邮编。

Peek 会显示包裹状态、带每条扫描城镇、地区和国家的物流轨迹、预计送达日以及签收时间；最高每 10 分钟检查一次（在亚马逊扫描包裹进入派送当天，自当地时间早上 6 点起最高每 2 分钟检查一次），在地图上标出每次扫描位置，并可在有变化时通知你。连续两天无新动态后（若仍停留在“已生成面单”或“已揽收”，则为 12 小时后），Peek 改为每小时检查一次。除保持英文原文的 `Arrived at sorting center`、`Arrived at delivery center` 和 `Departed facility` 外，其余状态均以 Peek 自己的简短标签、用你的界面语言显示，而不是亚马逊的原句。Peek 会隐去商家名称、你的姓名和地址、签名以及签收照片。亚马逊公开查询是唯一数据源：一旦亚马逊停止提供该单轨迹，Peek 会说明情况并停止检查，也不会延续跟踪到其他快递。

## Amazon Shipping 快递查询常见问题

### Amazon Shipping 和 Amazon 是一回事吗？

不完全相同：Amazon Shipping 是亚马逊面向其他企业开放的派送网络，无论你是在 Amazon.co.uk 还是其他网站向这些商家购买，都由这个网络派送。你在 track.amazon.co.uk 查件，遇到问题则联系卖家。其他亚马逊订单仍在“我的订单”中管理。

### Amazon Shipping 是正规服务吗？

是的，它是亚马逊面向其他企业的包裹派送服务。但如果你收到一封不认识的订单短信则是另一回事：亚马逊建议切勿回复、切勿点击链接，也不要拨打短信里提供的电话号码。

### Amazon Shipping 派送需要多久？

只要亚马逊查询页给出了预计送达日期，Peek 就会一并显示——但 `TBA`、`TBC` 和 `TBM` 单号除外。

:::sources
- [Amazon Shipping: Track a Package](https://track.amazon.co.uk/) – 无论在 Amazon.co.uk 还是其他网站下单，由卖家创建货件及面向企业的派送服务
- [About Amazon UK: Amazon CEO Andy Jassy's 2023 Letter to Shareholders](https://www.aboutamazon.co.uk/news/company-news/ceo-andy-jassys-2023-letter-to-shareholders) – Amazon Shipping 为亚马逊为其他企业把包裹送到其客户手中的尾程派送网络
- [Citizens Advice: Damaged, lost or delayed post or parcel](https://www.citizensadvice.org.uk/consumer/post-and-parcels/damaged-lost-delayed-post-or-parcel/) – 建议先联系卖家、申请退款或重发及破损商品退换
- [About Amazon UK: 7 tips to avoid scams impersonating Amazon](https://www.aboutamazon.co.uk/news/retail/how-to-avoid-scams-impersonating-amazon-this-holiday-season) – 警惕涉及购物的消息、付款、礼品卡、不拨陌生号码及自助举报
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Peek 识别和读取的内容、哪个查询系统回应哪些单号、亚马逊自营订单在公开查询页查不到
:::
