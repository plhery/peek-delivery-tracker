---
title: Express One 匈牙利快递追踪：单号、状态、自提点与客服
description: 追踪匈牙利 Express One 包裹：了解单号从哪里来、各状态的含义、自提点与自提柜的保管期限、派送未成功时怎么办以及客服联系方式。
slug: express-one-tracking
published: 2026-11-26
updated: 2026-11-26
---

Express One Hungary 是一家隶属于 [Austrian Post](carrier:austrian-post) 的匈牙利快递公司，拥有约 700 名派送员以及超过 2,300 个自提点与快递自提柜，为网店提供派送服务。你可以凭包裹编号（package ID）在 [tracking.expressone.hu](https://tracking.expressone.hu/) 查询物流，或直接粘贴到上方输入框中。该编号会在包裹完成登记并抵达 Express One 后随其发送的邮件一并告知你。

## Express One 运单号说明

寄件人登记包裹且实物抵达 Express One 后，Express One 会向你发送一封预通知邮件；只有当寄件人付费开通时才会发送短信。匈牙利境内派送通知会列出派送时间段、派送员联系方式、货到付款金额（如有）以及修改派送的链接；发往境外的包裹不会发送此类通知。如果没收到消息，请联系网店，也可参阅[如何查找运单号](guide:find-tracking-number)（英文）。

追踪器也支持寄件人参考号或单据号，但需要寄件人的 5 位公司编号（Company ID），因此请使用包裹编号（即条码号）。

Express One 未公布单号格式。目前已见到的包裹编号有两种形态（以下数字均为虚构）：

| 单号 | 归属说明 |
| --- | --- |
| `0482917364829` | 13 位数字：可能是 Express One 包裹编号，但 [DPD France](carrier:dpd-fr) 等其他快递也使用这一长度。 |
| `148291736482917364821011` | 24 位数字，末尾为匈牙利邮编：Express One。Austrian Post、[InPost](carrier:inpost)、[bpost](carrier:bpost) 与 [Ciblex](carrier:ciblex) 同样使用 24 位数字。 |
| `48291736482917` | 14 位数字：DPD，绝不是 Express One。 |
| `48291736482` | 11 或 12 位数字：GLS，绝不是 Express One。 |
| `RR482917367HU` | 两位字母、9 位数字加 `HU`：[Magyar Posta](carrier:magyar-posta)，绝不是 Express One。 |

请以商家邮件中注明的快递公司名称为准；更多格式详见[运单号格式说明](guide:tracking-number-formats)（英文）。

## Express One 追踪状态含义说明

tracking.expressone.hu 会按浏览器语言显示英文或匈牙利语，并提供 EN/HU 切换按钮。每条扫描包含状态码、说明文字、日期与时间，枢纽和分拨站扫描还会在括号内标明地点（例如“(HU - Budapest)”）。英文文案按包裹经历的顺序如下：

| Express One 状态文案 | 对你的含义 |
| --- | --- |
| `Shipping details received, shipment not yet handed over to EXOHU` | 寄件人已[预报登记包裹](guide:tracking-statuses)（英文），Express One 尚未拿到实物。 |
| `Shipment (pan) data uploaded` | 包裹数据已录入 Express One 系统，通常紧随枢纽扫描出现。 |
| `Arrived at central hub` | 已抵达布达佩斯中心枢纽。 |
| `Departed central hub` | 已离开中心枢纽。 |
| `Arrived at depot` | 已到达负责你所在区域派送的分拨站。 |
| `Out for delivery` | 今天已装上派送员车辆，正在派送。 |
| `Delivery schedule updated` | 派送计划已重新计算，包裹本身尚未移动。 |
| `Processing in progress` | 派送顺延至较晚时间，未说明具体原因。 |
| `Addressee not present at delivery address` | 派送员上门时无人签收。 |
| `Addressee unavailable` | 第二次派送尝试同样未能成功。 |
| `Obstacles of nature` | 恶劣天气中断派送行程；记作一次尝试，包裹会重新安排派送。 |
| `Successful delivery` | 已送达。 |

EXOHU 是 Express One Hungary 的简称：第一条状态说明仅生成了电子面单。历史记录上方的进度条有自己的一套阶段标签，从 `Shipment data uploaded` 到 `Successfully delivered`。在匈牙利语界面中，送达写作 `Sikeres kézbesítés`。

## Express One 派送、自提点与派送未成功

**派送时间：** 预通知会提供 4 小时派送区间。派送当天早上的消息会进一步给出 2 小时窗口，但 Express One 说明这仅供参考，派送员可能提前或延后到达。当天追踪页面还会显示派送员在你之前还剩几站。周末不派送，企业寄件人付费购买的周六派送除外。

**修改派送：** 派送当天早上短信或邮件里的“Modification”按钮支持更改派送时间、地址，或改寄至自提点/快递自提柜，即使在派送员抵达前 1 小时也可操作。修改仅限首次派送当天且仅可操作一次。部分选项取决于寄件人设置（例如大件包裹无法投递至自提柜）。Express One 条款指出改址/改期计作一次派送尝试且可能产生额外费用；其 2024 年的一篇博客则表示改派免费。

**上门签收：** 个人收件人需签字并提供身份证件号码。货到付款可用现金（最高 300,000 福林）或银行卡（最高 1,000,000 福林）支付，也可通过到达前消息中的链接提前在线支付；在 Exopoint 自提点现金上限为 250,000 福林。签收并付款前不得开箱验货。

**派送未成功：** 依据 Express One 服务条款，公司最晚在下一个工作日通过邮件或短信通知你，免费再试投一次，并最多保管包裹至第 5 个工作日。期间你可申请新日期或改送自提点。第二次派送仍未成功后，包裹将退回寄件人；其 2024 年的一篇博客说明退回前会先在布达佩斯中心分拨站暂存 5 个工作日。

**自提点与自提柜：** 许多网店支持在结账时选择 Exopoint 自提点或自提柜。Express One 自提点页面称包裹全天候保管最长一周；其多篇博客的说法不一：EXOBOX 自提柜 3 天、Alzabox 2 天、Packeta 网点最长 7 天（2025 年）、自提柜 72 小时（2026 年）、自提点 5 天（2024 年），且仅 EXOBOX 与 Alzabox 柜机 24 小时开放。如需前往布达佩斯物流中心自提（H-1239 Budapest, Európa street 12, building L1），须提前与客服预约。

## Express One 包裹延误、破损或遗失

**无更新或查不到：** Express One 没有给出等待期限。其追踪页提示，查无记录可能是单号有误、已超过 180 天或寄件人尚未登记，建议稍后再试。其条款允许在 5 个工作日内未收到实物包裹时清除数据，并声明预计送达时间仅供参考。详见[为什么物流停止更新](guide:tracking-not-updating)（英文）。

**破损或发错商品：** 请尽快联系下单网店：Express One 在寄件人申报后启动投诉流程，且只负责运输而非内件商品。外观明显破损须在派送员交件当场注明；隐蔽破损须在签收后 3 个工作日内书面报告，逾期权利失效。

**遗失理赔：** 通常由寄件人索赔，若包裹已交付给你或寄件人书面转让了索赔权，你也可提出。整件遗失须自寄出后第 15 天起算 6 个月内书面提交索赔，并附上运单号、内件与价值证明及 Express One 索赔表。Express One 会在 30 个自然日内答复投诉；如不满意，可在 30 天内向匈牙利媒体与通信管理局（NMHH）申诉。

## Express One 客服电话与联系方式

据 Express One 英文联系页面公布，工作日服务时间为匈牙利时间上午 8:00 至下午 5:00（常见问题页标注至下午 6:00）：

- **常规客服：** +36 1 8 777 400，`ugyfelszolgalat@expressone.hu`（派送员联系不上时同样拨打此号）。高峰期 Express One 建议优先发邮件。
- **国际件客服：** +36 1 8 777 410，`intcs@expressone.hu`；投诉邮箱 `intcsreklamacio@expressone.hu`。
- **破损理赔：** +36 1 8 777 470，`karugy@expressone.hu`。
- **联系表单：** 位于 [Express One 联系页面](https://expressone.hu/en/kapcsolat)，可选“I am expecting a parcel”和“Delivery complaint”等主题，均需提供包裹编号。

Express One 的包裹追踪页面另列出了 `+36 1 8 777 444` 电话以及在线聊天机器人。

## 使用 Peek 追踪 Express One 包裹

[Peek](/) 不会自行拿 13 位数字去问 Express One，而是交给其他快递公司查询。输入 24 位数字时，若你在匈牙利查询，Peek 会最先询问 Express One，排在 Austrian Post、InPost 和 Ciblex 之前；在其他国家则会先询问那三家，再询问 Express One。若都查不到，页面会显示“暂未找到 · Peek 会继续查询”：此时用“更换”选择“Express One”。你也可以粘贴带 `?plc_number=` 参数的 tracking.expressone.hu 链接，无论单号是什么，都会指向 Express One。

Express One 的扫描时间不含时区：Peek 会按扫描地点时钟解析（匈牙利境内按匈牙利时间），并列出 Express One 自身的历史记录。Peek 从中读取状态，以及枢纽扫描中的城市与国家。除 `Out for delivery` 外，其余文案保持 Express One 原文；`Successful delivery` 计为已签收，退件行程计为已退回寄件人。Peek 无法识别的状态会直接展示 Express One 原文而不归类阶段。你可以开启状态变更通知。系统不展示预计送达日、重量或自提点信息，也不包含进度条与签收签名。

## 关于 Express One 物流查询的问题

### “shipment not yet handed over to EXOHU”是什么意思？

表示寄件人已经创建了运单，但 Express One Hungary 还没有收到实物包裹。如果多日停留在此状态，请询问网店。

### Express One 包裹会在自提点保管多久？

Express One 自提点页面称最长一周且可全天候领取；其博客则视网点类型给出 2 至 7 天不等，并指出只有 EXOBOX 和 Alzabox 自提柜支持 24 小时取件。

### 斯洛伐克或斯洛文尼亚的 Express One 是同一家公司吗？

不是。它们同样隶属于 Austrian Post，但属于独立运营的公司；本页面以及 Peek 中的“Express One”仅涵盖匈牙利公司。

:::sources
- [Express One: Parcel Tracking](https://expressone.hu/en/csomagkovetes) – 派送员剩余站数、客服热线、聊天机器人
- [Express One: Tracking](https://tracking.expressone.hu/) – 单号类型、公司编号、未找到提示、180 天期限、状态与语言切换
- [Express One: Frequently asked questions](https://expressone.hu/en/gyik) – 修改派送、2 小时时间窗、周六派送、货到付款、破损与错发
- [Express One: GYIK](https://expressone.hu/gyik) – 货到付款金额上限
- [Express One: Contact](https://expressone.hu/en/kapcsolat) – 电话、邮箱、工作时间、在线表单与物流中心地址
- [Express One: Parcel point](https://expressone.hu/en/csomagpont) – 2,300 处自提点与自提柜、一周保管、24/7 与结账选择
- [Express One: Home](https://expressone.hu/en) – Austrian Post 控股、700 名派送员、抵达前 1 小时改派
- [Express One: General Terms and Conditions](https://expressone.hu/downloads/General_Terms_and_Conditions_2026_08_15.pdf) – 派送次数、保管期、证件查验、改派费、索赔、投诉、NMHH 申诉与数据清除
- [Express One: Merre jár a csomagom?](https://expressone.hu/blog/merre-jar-a-csomagom-csomagkovetes-es-ertesitesi-rendszerek-alkalmazasa) – 登记并到件后预通知、4 小时与 2 小时时段、短信选项
- [Express One: Csomag átirányítás és a kézbesítés átütemezése](https://expressone.hu/blog/csomag-atiranyitas-es-a-kezbesites-atutemezese) – 免费改派、布达佩斯分拨站或自提点保管 5 天
- [Express One: Minden, amit az EXOPOINT megoldásokról tudni érdemes](https://expressone.hu/blog/minden-amit-az-exopoint-megoldasokrol-tudni-erdemes) – 营业时间及 3 天、2 天、7 天保管期
- [Express One: Csomagküldés csomagautomatába](https://expressone.hu/blog/csomagkuldes-csomagautomataba-miert-valasztjak-egyre-tobben) – 自提柜 72 小时保管期
- [Express One: eBOX GYIK](https://expressone.hu/ebox/faq) – 通知内容、出口件无通知、Exopoint 现金上限 250,000 福林
- [Austrian Post: Austrian Post Group acquires Hungarian parcel delivery provider](https://news.post.at/presse/en/post/id/2255390/) – 子公司收购公告
- [Austrian Post: International subsidiaries](https://post.at/en/j/c/careers-austrian-post-subsidiaries-international) – 其他国家的 Express One 独立子公司
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Peek 对 Express One 的读取规则
:::
