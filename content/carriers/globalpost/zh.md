---
title: GlobalPost 物流查询：GP 运单号、状态与末端派送公司
description: 查询 GlobalPost 国际包裹：GP 与 SDC 运单号格式、物流状态含义、不同服务清关规则、各国末端派送伙伴及联系 GlobalPost 客服。
slug: globalpost-tracking
published: 2026-11-26
updated: 2026-11-26
---

GlobalPost 隶属 Auctane 集团，是面向网店卖家的跨境物流服务，卖家通常通过 ShipStation 或 Stamps.com 等平台下单。包裹会先发往 GlobalPost 发货中心，由其贴上国际面单并制作报关单，随后交由 GlobalPost 合作的邮政或商业快递公司运往海外，最终由你所在国家的邮政或快递公司投递上门。卖家提供的单号通常以 `GP` 开头：直接粘贴到上方输入框即可查询，GlobalPost 自己的查件页面在 goglobalpost.com/tracking。

## GlobalPost 运单号格式与查找位置

Peek 通过格式识别 GlobalPost 现行单号：`GP` + 4 位代表服务类型的字母 + 10 位数字 + 寄出国家代码 `US` 或 `GB`。以下字母与数字均为虚构：

| 单号（虚构） | 含义 |
| --- | --- |
| `GPECON4829105736US` | `GP` + 四位字母 + 十位数字 + `US`：从美国寄出的 GlobalPost 包裹。 |
| `GPSTND4829105736GB` | 同上但以 `GB` 结尾：从英国寄出的包裹。 |
| `SDC482910573649182` | `SDC` 加 15 位数字：较早期的 GlobalPost 面单号。Peek 无法仅凭单号识别，请点击“更换”选择 GlobalPost。 |
| `GPECON482910573US` | 只有 9 位数字：Peek 无法识别承运商，请检查是否漏输一位数字。 |
| `LZ482910576US` | 美国邮政单号，并非 GlobalPost 格式：Peek 会识别为 [USPS](carrier:usps)；若强行选择 GlobalPost，Peek 会提示其“不使用这种格式的单号”。 |

Peek 会忽略空格、连字符及大小写。

**在哪里查找。** 在卖家的发货邮件或订单页面中查找；如找不到，请参阅[如何查找运单号](guide:find-tracking-number)（英文）和[运单号格式指南](guide:tracking-number-formats)（英文）。

**第二个单号。** 在 GlobalPost 查询页的 “Final Mile Tracking By” 下方，可能会显示末端派送公司自己的运单号，你也可以直接用该单号在 GlobalPost 查询页查件。

## GlobalPost 物流状态含义

GlobalPost 查询页按最新扫描置顶排列，每条包含时间、说明及大多带有的地点，并将合作伙伴的扫描改写为 GlobalPost 自己的措辞。以下按流转顺序列出：

| GlobalPost 显示 | 对你的含义 | Peek 显示 |
| --- | --- | --- |
| `Label Created` | 卖家已生成面单，包裹尚未移动。 | “已生成面单” |
| `Data Received` | GlobalPost 已收到卖家提交的数据，尚未收到实物。 | “已生成面单” |
| `Arrived at Partner Facility`、`Departed Partner Facility` | 合作伙伴已完成扫描：如发往 GlobalPost 途中的 USPS，或你所在国家的本地邮政。 | “运输中” |
| `Arrived at GlobalPost Facility` | 包裹已抵达 GlobalPost 发货中心（此前可能已有 USPS 扫描）。 | “已揽收” |
| `Departed GlobalPost Facility` | 已离开该发货中心。 | “运输中” |
| `Departed from Origin Country` | 已离开美国或英国。 | “运输中” |
| `In Transit`、`In-Transit` | 运输途中。 | “运输中” |
| `Arrived in Destination Country` | 已抵达目的地国家。 | “运输中” |
| `In Customs` | 正在目的地国家海关清关。 | “清关中” |
| `Cleared Customs` | 海关已放行继续运输。 | “运输中” |
| `Out for Delivery` | 已安排今日派送。 | “派送中” |
| `Attempted Delivery` | 派送公司尝试派送但无人签收。 | “派送未成功” |
| `Exception, No Action Required` | 目的地派送遇到异常，但无需你采取任何操作。 | “需要留意” |
| `Delayed, No Action Required` | 出现延误，无需你采取操作。 | “需要留意” |
| `Delivered` | 已签收送达。 | “已签收” |

部分记录末尾会附带 GlobalPost 关于更新可能延迟的提示语，真正的状态是提示语前面的文字。其余未列出的描述由 Peek 按通用规则解析，无法识别的则原样展示。

**退回寄件人。** GlobalPost 查询页会将退件也标为 `Delivered`（地点显示在寄件人所在城镇）；当该城镇与你的所在国家不同时，Peek 会显示为“已退回寄件人”。

## 谁负责在你所在国家派送 GlobalPost 包裹

对于 Economy 和 Standard 服务，GlobalPost 会将包裹交由 USPS 等邮政承运商发往海外。其页面没有按国家列出末端派送伙伴，但查询页可以列出，并附上对方单号。

**能看到多少追踪信息取决于卖家选用的服务等级：**

| 服务等级 | 追踪范围 | 关税与税费 | GlobalPost 向卖家公布的参考时效 |
| --- | --- | --- | --- |
| Economy | 至抵达目的国并完成清关 | 收件人支付 | 10 至 14 个工作日 |
| Standard | 全程追踪至签收 | 收件人支付 | 6 至 10 个工作日 |
| Plus / Plus – Duties & Taxes Included | 全程追踪至签收 | 卖家承担 | 5 至 8 个工作日 |
| Flat Rate（原名 Go） | 全程追踪至签收 | 已含在卖家售价内 | 6 至 10 个工作日 |

从美国寄出的 Plus 服务覆盖超过 30 个国家（含英国、爱尔兰、加拿大和澳大利亚）；Flat Rate 覆盖加拿大、澳大利亚、新西兰、香港和墨西哥。如果不确定卖家用了哪种服务，请向卖家咨询。

**派送未成功与自提。** 由你所在国家的末端承运商负责派送和暂存：请凭对方运单号直接联系该承运商。另见[派送未成功怎么办](guide:missed-delivery)（英文）、[未领取包裹](guide:uncollected-parcel)（英文），以及各国邮政指南：[英国皇家邮政（Royal Mail）](carrier:royal-mail)、An Post、[加拿大邮政（Canada Post）](carrier:canada-post)、[澳大利亚邮政（Australia Post）](carrier:australia-post)和 [USPS](carrier:usps)。

**海关清关。** 选用 Economy 与 Standard 服务时，关税与税费由收件人承担（Delivered Duties Unpaid）。在爱尔兰及其他欧盟国家，从欧盟境外网购入境的商品自 2026 年 7 月 1 日起对货值不超过 €150 的包裹按商品种类每项征收 €3 关税，并自 2026 年 11 月 1 日起无论货值高低按商品种类每项加收 €2 欧盟处理费；在欧盟增值税区内，如结账时未通过 IOSS 代收，还需缴纳进口增值税。欧盟委员会明确北爱尔兰在货物关税和增值税方面视同欧盟关境。GlobalPost 服务页面未说明 €2 处理费的收取方式，请向卖家核实售价是否已包含。其他地区请参阅[海关滞留包裹指南](guide:customs)（英文）。

## GlobalPost 包裹不更新、遗失或退回

**尚无记录。** 在 GlobalPost 返回首条扫描前，Peek 会显示“等待 GlobalPost 更新”并继续查询。若两天后 GlobalPost 仍查不到且所有物流查询服务均无结果，Peek 会提示“两天过去了，快递公司仍查不到这个单号。请检查单号、更换快递公司，或删除这个包裹。”若仅停留在 `Label Created`，包裹可能尚未送达 GlobalPost；若几天后仍无进展，请向卖家确认是否已发出。

**连续数日无更新。** GlobalPost 指出国际货运更新需要时间，天气、节假日、罢工和清关都可能导致延误；此外 Economy 服务的追踪在目的国清关后即告结束。若在途包裹连续 4 天无新扫描，Peek 会以最后一条扫描的日期做出提示（“自10月12日周一起没有物流更新”），并说明长途航班、清关或周末期间常暂时无动静；超过三周无更新时会建议联系卖家。该提示不附 GlobalPost 帮助页面链接；Peek 改由末端派送公司跟踪包裹后，如果 Peek 知道该公司的帮助页面，会附上它的链接。详见[物流停止更新的原因](guide:tracking-not-updating)（英文）。

**满 30 天未收到。** GlobalPost 要求在发货满 **30 天**未到货时联系卖家：卖家可在发货后 **90 天**内向 GlobalPost 查件并索赔，理赔处理最长需三周。详见[包裹遗失处理](guide:lost-parcel)（英文）。

**显示已签收却未收到。** 请先咨询物流中显示的末端派送公司，再联系卖家。详见[显示已签收却未收到](guide:delivered-not-received)（英文）。

**无法派送或退回。** GlobalPost 列出常见原因包括：地址错误或不完整、未缴关税或税费、错过清关通知、含禁限物品或无法清关。随后由目的地国家邮政处理，多数情况下退回卖家或留存当地邮局。GlobalPost 无法给出退件到达日期，也不会从当地邮政获得退件新单号。因条款将无法投递件排除在理赔之外，请联系卖家协商退款或补发。详见[包裹被退回寄件人](guide:returned-to-sender)（英文）。

## GlobalPost 客服与联系方式

GlobalPost 客服邮箱为 `info@goglobalpost.com`，电话为 `(888) 899-1255`（太平洋时间周一至周五 06:00–18:00）。其[客服联系表单](https://www.goglobalpost.com/contact-support/)也受理收件人咨询：选择 “Recipient” 并填入运单号即可。GlobalPost 提醒，客服团队看到的信息与其查件页面相同。

若包裹迟迟未到，GlobalPost 建议先联系卖家——因为下单和发起理赔的主体都是卖家。GlobalPost 另特别提示：由中国邮政（China Post）发出的中国包裹并不属于 GlobalPost 承运，请直接询问寄件人。

## 使用 Peek 跟踪 GlobalPost 包裹

输入 `GP` 开头单号后，[Peek](/) 会立刻识别为 GlobalPost（“识别出的快递公司”），仅凭单号即可查询，无需邮编或账号；直接粘贴 GlobalPost 查询链接也可以。较早期的 `SDC` 单号需点击“更换”手动选择 GlobalPost。如果你手上只有末端派送公司的单号，也尽管粘贴：Peek 跟踪 4,500 多家快递公司。

Peek 会显示包裹状态、附带地点的每条扫描、目的地国家，以及 GlobalPost 公布时的末端派送公司与单号；进度条、收寄城镇与邮编均会被剔除。GlobalPost 不提供预计送达日期、重量或签收凭证，因此 Peek 也不会显示。点击“打开 GlobalPost 官网”会在 GlobalPost 的查件页面打开该单号。

GlobalPost 返回的时间不含时区：Peek 会按扫描地点或当前航段所在时区解析，并换算为你的设备本地时间；在 peektracker.com 及 iPhone、Android 应用中，历史记录底部会写明“时间按你所在的时区显示：”加时区名称。

当 GlobalPost 给出的末端承运商是 Peek 认得的快递公司时，包裹卡片会显示“由 <快递公司> 派送”并在 `GP` 单号后列出对方单号，同时由 Peek 同步向对方查询；若对方尚只有电子预报而无实物扫描，则显示“<快递公司> 已收到预报，尚未收到包裹”。若只有末端单号而未识别承运商名称，Peek 会按单号格式询问你的国家邮政或对应承运商。一旦末端承运商出现不早于 GlobalPost 最新记录的扫描，Peek 即转由对方接续跟踪并保留先前全部历史。

当你正在 Peek 中查看该包裹，或派送当天已开启通知时，Peek 最多每 10 分钟查询一次；在包裹开始派送的当天，只要 GlobalPost 自己的查件系统正常响应，自当地时间早上 6 点起最多每 2 分钟查询一次（需要在浏览器中打开 GlobalPost 查件页面时每 5 分钟一次；通过物流查询服务时最多每 15 分钟一次）；其余时候，以及连续两天没有新动态后（包裹仍显示“已生成面单”或“已揽收”时为 12 小时），每小时查询一次。Peek 改由末端派送公司跟踪包裹时，按该公司的频率查询。Peek 可以按“每次扫描”、“重要环节”或“仅派送当天”通知你。Peek 无法代你联系 GlobalPost 或卖家。

## GlobalPost 查询常见问题

### 为什么我的 GlobalPost 物流在清关后就停止更新了？

使用 GlobalPost Economy 服务时，包裹抵达你的国家并完成清关后追踪即结束，且 GlobalPost 说明部分线路在目的国仅提供有限追踪。包裹仍在派送途中，可能在没有新扫描的情况下直接送达；如果查询页给出了末端承运商单号，Peek 和你都可以去对方系统继续查看。

### 谁负责派送 GlobalPost 包裹？

由你所在国家的邮政或快递公司负责末端派送，GlobalPost 查询页可以显示该公司名称及对应运单号。

### GlobalPost 运输需要多长时间？

GlobalPost 官网称发往邻近国家需 5 至 6 天，较远国家最长 20 天；其向卖家公布的估算时效（自发货中心处理完毕起算）为：Economy 10 至 14 个工作日、Standard 6 至 10 个工作日、Plus 5 至 8 个工作日。

### GlobalPost 是正规物流公司吗？

是的。GlobalPost 隶属 Auctane，通过 ShipStation 和 Stamps.com 等发货平台提供服务，覆盖超过 220 个国家和地区，官方称已累计递送超过 6,000 万件包裹。

### 如何联系 GlobalPost？

可发邮件至 `info@goglobalpost.com`，或在太平洋时间周一至周五 06:00–18:00 致电 `(888) 899-1255`。若发货满 30 天仍未收到，GlobalPost 建议优先联系卖家处理。

:::sources
- [GlobalPost: FAQs](https://www.goglobalpost.com/faqs/) – Auctane、运作方式、查件、延误、派送时间、退货、索赔、无法投递的包裹不受理索赔
- [GlobalPost: Standard](https://www.goglobalpost.com/standard/) – 服务对比：查件、关税、运输时间
- [GlobalPost: Contact Support](https://www.goglobalpost.com/contact-support/) – 邮箱、电话、服务时间、表单、中国邮政
- [GlobalPost: Home](https://www.goglobalpost.com/) – 国家、已递送的包裹
- [GlobalPost: Track your package](https://www.goglobalpost.com/tracking/) – 查件页面
- [GlobalPost: Terms and Conditions](https://www.goglobalpost.com/conditions/) – 发货中心、运输时间仅为估计
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 150 欧元以下每类商品 3 欧元关税、2 欧元欧盟手续费、日期
- [European Commission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 不论价值，每类商品 2 欧元
- [Universal Parcel Scraper: GlobalPost](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/globalpost) – 单号格式、状态措辞、末端派送伙伴、退件、时间所依据的时区
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – USPS 单号格式、交给末端派送公司
:::
