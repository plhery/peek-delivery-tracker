---
title: 云途物流（YunExpress）查询：YT单号、状态与尾程
description: 查询深圳云途物流（YunExpress）跨境包裹：YT加16位数字单号格式、yuntrack英文状态与海关查验说明、欧美尾程派送、时效与客服。
slug: yuntu-yunexpress-wuliu-chaxun
published: 2026-11-23
updated: 2026-11-23
---

云途物流（YunExpress，全称深圳市前海云途物流有限公司，英文亦常写作 Yun Express）是一家总部位于深圳龙岗区的跨境电商物流企业，创立于 2014 年，专门为中国跨境卖家（包括 Amazon 和速卖通等平台卖家）承运国际小包专线。由 `YT` 加 16 位数字组成的单号就是云途物流运单号。你可以在云途自有追踪平台 `yuntrack.com` 查询，也可以直接把单号粘贴到上方查询框。云途官网没有说明会给个人收件人发送短信或邮件，单号请在网店的发货邮件或订单详情页查找。

## 云途物流单号格式：YT 加 16 位数字

`yuntrack.com` 只提示输入以 `YT` 开头的云途单号，并未明写位数。以下是虚构示例及常见 [易混单号格式](guide:tracking-number-formats)：

| 单号（虚构） | Peek 识别结果 |
| --- | --- |
| `YT4829173605142839` | 云途物流（`YT` 加 16 位数字，共 18 位）。 |
| `YT4829173605142` | [圆通速递（YTO Express）](carrier:yto)（`YT` 加 13 位数字，是另一家中国快递）。 |
| `YT482917360514283` | 不是云途物流（`YT` 加 15 位数字）：请检查是否少抄了一位数字。 |
| `LP48291736051428` | 速卖通/[菜鸟（Cainiao）](carrier:cainiao) 物流参考号（`LP` 加 14 位数字）。 |

无论单号是以 `YT2` 还是 `YT22` 开头，只要是 `YT` 紧跟 16 位数字，就都属于云途物流；云途未公开前几位数字含义，数清总共 16 位数字即可。找不到单号请参阅 [如何找到你的快递单号](guide:find-tracking-number)。

`yuntrack.com` 每次最多支持同时查询 100 个单号（可用逗号、空格或换行分隔），无需注册账号。包裹移交当地尾程快递后，详情页的「Additional Notes」一栏通常会显示当地尾程快递单号。云途未公布新单号上线所需时间；如果显示 `Not Found`，请逐位核对 `YT` 后的全部 16 位数字，再向卖家确认。

## 云途物流（YunExpress）状态是什么意思

`yuntrack.com` 为全英文界面，会把每个包裹归入 `Processing`、`Transit`、`Delivered`、`Not Found`、`Alert` 或 `Returned` 分类标签下并列出明细扫描。常见节点大致按顺序如下：

| 云途英文原文 | 对你的实际含义 |
| --- | --- |
| `Shipment information received` | 卖家已发送电子订单信息，云途可能尚未收到实物包裹。 |
| `Shipment picked up` | 云途已从卖家处揽收包裹。 |
| `The country of origin commences customs declaration.` | 起运国（中国）开始出口报关，不是收件国进口海关。 |
| `International flight has departed`、`Departed from AIRPORT` | 国际航班已起飞。 |
| `Start Customs Clearence` | 目的国进口清关已开始（`Clearence` 为云途原拼写）。 |
| `Customs inspection - Import` | 被目的国海关抽中查验。 |
| `Clearance processing completed - Import` | 目的国进口清关放行完成。 |
| `Delivered to local carrier` | 已移交给目的国本地尾程快递商，尚未送达你手中。 |
| `Item accepted` | 当地尾程快递已从云途接收包裹，属于交接入库而非妥投。 |
| `Arrived at GOFO Regional Destination Facility` | 已到达扫描中署名的尾程快递 GOFO 的区域设施。 |
| `The driver is out for delivery` | 尾程快递司机正在外出派送。 |
| `Your parcel is out for delivery.` | 澳大利亚专线中转发自 J&T Express Australia 的外出派送提示（末尾有时附带 J&T 客服号）。 |
| `Item is out for delivery` | 对应 `Item accepted` 那家尾程快递的外出派送提示。 |
| `Delivered by Mailbox` | 已投递进你的信箱或邮箱。 |

`Transit` 表示在途，具体进度以最新一条扫描为准；云途没有单独解释 `Alert`（异常标签），遇到时请阅读下方明细行，如果多日无进展立即联系卖家。

在 `Delivered to local carrier` 之后，轨迹通常会以英文继续转发当地快递的扫描（详见 [常见物流状态含义指南](guide:tracking-statuses)）。其中 DPD 的 `Sender goods issue` 往往是包裹还在中国时录入的电子数据，不是商品故障。如果德国段由 DHL 接手，云途会依次同步 `The instruction data for this shipment have been provided by the sender to DHL electronically`（电子预报，「已生成面单」）、`Pick-up was successful.`（DHL 交接取件，不是首次揽收）、`The shipment has been processed in the parcel center of origin`、`Loaded to movement / tour vehicle`（包裹中心之间转运，不是派送车）、`The shipment has been processed in the destination parcel center` 和 `Movement / tour vehicle arrived`（均属「运输中」），以及无人签收时的 `Not delivered-Consignee could not be reached`（「派送未成功」）。`yuntrack.com` 顶部进度条里的 `Pickup` 指从卖家处揽收，并非自提点；部分包裹还会显示 `Estimated delivery time`（预计送达时间）。

## 云途物流包裹在英国、美国和欧洲由谁派送

云途的标准流程是从中国空运出境、完成进口清关后交由当地快递商派送尾程（参见 [中国直邮包裹跟踪指南](guide:tracking-from-china)），`Delivered to local carrier` 之后的轨迹有时会写出具体接手的当地快递名称。

- **英国与爱尔兰：** 据 [云途关于我们页面](https://www.yunexpress.com/about-us)，云途早在 2016 年就开通了英国专线，但公开页面未写明英国和爱尔兰固定尾程快递商，也没有设立面向收件人的当地电话或办公室；包裹派送问题请直接联系轨迹中显示的当地快递。部分专线覆盖英国本土及内岛，但不含泽西岛、根西岛等离岛。增值税和关税规定请参阅 [包裹海关扣留与清关指南](guide:customs)。
- **美国：** [云途中文官网](https://www.yunexpress.cn/) 向卖家介绍自有尾程服务 CIRRO-E-COMMERCE，且早在 2020 年就上线了美国标准邮政渠道，但未公开每票包裹固定走哪家尾程。在实际轨迹中，部分美国包裹由 GOFO 派送，GOFO 官网也能直接用同一个 `YT` 单号查询。
- **欧洲自提点与派送规则：** 云途中文新闻提到欧洲部分专线支持距离收件人 3 公里内自提点或快递柜投递（入库到自提点平均约 6–10 个自然日，轨迹会显示网点地址）。云途官网未向个人收件人公布派送次数、通知卡、保管期或改约规则，一切以实际派送的当地快递为准。

## 云途物流不更新、海关查验、延误或丢件怎么办

依据 [云途隐私政策](https://www.yunexpress.com/privacy-policy)，云途的签约客户是开通账号的发货商家而非终端收件人，官网没有面向收件人的理赔入口或时限说明。若包裹严重延误、丢失或破损，请携带订单号与 `YT` 单号直接联系卖家或购物平台。长时间无扫描原因见 [物流为什么停止更新](guide:tracking-not-updating)。

**遇到 `Customs inspection - Import`（进口海关查验）怎么办？** [云途香港博客关于海关抽查的说明](https://hk.yunexpress.com/blog/2179/) 指出，被抽查本身不一定意味着长期滞留，具体耗时取决于查验等级：单证审核通常多花几个工作日至约一周；机检或开箱实物查验一般需要 1–2 周，且可能产生仓储或查验费；若移交主管部门专项审查，则可能耗时数周甚至超过一个月。涉税说明见 [包裹海关扣留与清关指南](guide:customs)。

**显示已签收却没收到，或显示 `Returned`。** 查件页有时会提供签收凭证（proof of delivery），请先核查当地尾程快递再联系卖家，另见 [显示已签收但未收到](guide:delivered-not-received)。显示 `Returned` 表示包裹正在退回，云途没有面向收件人的退件重派政策，请直接找卖家协商退款或补发。

## 云途物流客服电话与联系方式

云途在英国、爱尔兰和美国均未公布面向个人收件人的客服电话、表单或门店，公开渠道如下：

- **客服邮箱**：[云途英文官网](https://www.yunexpress.com/) 页脚列出 `info@yunexpress.com`（标注服务时间 `08:00–23:00`，未注明时区，首页提示提供中英双语人工响应，但未说明是否直接受理个人收件人咨询）。
- **中国大陆热线**：[云途中文官网](https://www.yunexpress.cn/) 列出 `400-8575-500`（中国国内客服号，未说明海外能否拨通）。
- **区域办事处**：[云途区域联系页](https://sg.yunexpress.com/contact/) 仅列出亚洲和澳大利亚联络方式。

收件人遇到问题应先联系签约发货的卖家，再联系轨迹中署名的当地尾程快递（例如美国 GOFO 可前往 `gofo.com` 联系其美国客服）。

## 用 Peek 跟踪云途物流包裹

[Peek](/) 会通过 `YT` + 16 位数字的唯一格式自动识别云途物流（例如虚构单号 `YT4829173605142839`），查询框里会显示 YunExpress 和「识别出的快递公司」；也支持直接粘贴 `yuntrack.com` 的查件链接。如果需要手动调整，点击添加页的「更换」或详情页的「更换快递公司」即可。

Peek 直接查询云途轨迹并显示规范化阶段（「已生成面单」「已揽收」「运输中」「清关中」「派送中」「派送未成功」「已签收」「已退回寄件人」「需要留意」等）、完整历史、每条扫描的地点（标在地图上）和签收时间。时区方面，中国扫描按北京时间读取，国际航班落地及无城市名的 `- Import` 清关记录按云途标注的到达机场时钟读取，澳大利亚 J&T Express Australia 节点按悉尼时间读取，其余结合地点或相邻节点推算，最后统一换算为你设备的本地时区显示。

当云途记录给出当地尾程快递商及其单号时，Peek 会在支持的线路上自动接力跟踪该尾程快递（但并非每票包裹都适用）。Peek 不展示云途的预计送达日期、自提点或商家订单参考号。

你打开着这个包裹，或在派送当天开着通知时，Peek 最快每 10 分钟检查一次，包裹派送中时最快每 5 分钟一次（Peek 用浏览器查询云途；只在派送当天当地时间早上 6 点以后，且结果来自云途官方系统时）；其他时候每小时一次。只有第三方查询服务有结果时，Peek 每小时检查一次，静默满一天后每 3 小时一次，派送当天你打开着包裹或开着通知时最快每 15 分钟一次。连续两天无新扫描（或处于「已生成面单」「已揽收」满 12 小时）改为每小时检查一次。最新扫描仍是「已生成面单」「已揽收」或「运输中」且静默满四天时，Peek 会提示「包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 YunExpress 查询。」，超过三周无动静则提示「已超过三周没有新动态。如果还没收到，请联系卖家。Peek 会持续向 YunExpress 查询。」。

你可以在通知设置里选择「每次扫描」「重要环节」或「仅派送当天」。Peek 无法替你联系云途物流或发起理赔。

## 关于云途物流查询的常见问题

### 哪种快递使用 `YT` 开头的单号？

`YT` 加 16 位数字（共 18 位，含 `YT2`、`YT22` 开头）是云途物流（YunExpress）的专属单号。注意 `YT` 加 13 位数字则是另一家中国快递 [圆通速递（YTO Express）](carrier:yto)。

### `yuntrack.com` 是什么网站？

`yuntrack.com` 是云途物流官方的全球包裹追踪平台，全英文界面、免登录、单次最多查 100 个 `YT` 单号。

### 云途物流（YunExpress）是正规公司吗？

是的。深圳市前海云途物流有限公司于 2014 年在深圳成立，2017 年成为亚马逊推荐物流承运商，并于 2025 年 4 月成为速卖通官方物流合作伙伴。

### 云途物流总部在哪里？

总部位于中国深圳市龙岗区；根据 2024 年隐私政策，中国大陆以外地区的数据控制主体为香港云途物流有限公司（Hongkong YunExpress Logistics Limited）。

### 云途物流寄到欧美一般要几天？

按云途向商家公布的产品参考时效：[标准专线（Standard Express）](https://www.yunexpress.com/our-solutions/b2c/33) 发英国 3–5 个工作日、美国 5–8 个工作日、加拿大 8–10 个工作日、澳大利亚 4–7 个工作日；[特快专线（Priority Express）](https://www.yunexpress.com/our-solutions/b2c/priority-express) 发英国 3–5 个工作日（不含偏远邮编）、美国 5–8 个工作日；部分特惠带电专线发英国 5–8 个工作日、爱尔兰 6–10 个工作日、美国 8–15 个工作日。买家通常看不到卖家选的具体渠道，且海关查验会额外增加耗时。

:::sources
- [YunExpress：官网首页（英文）](https://www.yunexpress.com/) – 公司背景、深圳总部、客服邮箱 `info@yunexpress.com` 与服务时段
- [YunExpress：关于我们（英文）](https://www.yunexpress.com/about-us) – 2014 年成立、2016 英国专线、2020 美国邮政专线、2024 Priority Express 与亚马逊合作
- [YunExpress：隐私政策（英文）](https://www.yunexpress.com/privacy-policy) – 签约客户关系与香港云途物流主体
- [YunExpress：标准专线 Standard Express（英文）](https://www.yunexpress.com/our-solutions/b2c/33) – 英、美、加、澳标准专线参考时效
- [YunExpress：特快专线 Priority Express（英文）](https://www.yunexpress.com/our-solutions/b2c/priority-express) – 英美特快时效与偏远邮编说明
- [云途物流：中文官网](https://www.yunexpress.cn/) – 跨境小包专线、中国大陆热线 `400-8575-500` 与 CIRRO-E-COMMERCE
- [云途香港博客：为什么你的包裹会被海关抽查？](https://hk.yunexpress.com/blog/2179/) – 单证、机检/实物查验与专项审查耗时说明
- [YunExpress：区域联系方式（英文）](https://sg.yunexpress.com/contact/) – 亚洲及澳大利亚联络方式
- [YunExpress：Yuntrack 查件页代码](https://www.yuntrack.com/static/js/0.38a2ccebfb56bd50819e.js) – 以 YT 开头的提示、分隔符、分类标签、进度条、Additional Notes、签收凭证、预计送达时间
- [YunExpress：Yuntrack 结果页代码](https://www.yuntrack.com/static/js/6.9b7c1a51737b2bcaa67c.js) – 免登录单次最多 100 个单号
- [YunExpress：全球专线挂号（特惠带电）](https://www.yunexpress.com/our-solutions/b2c/7) – 英国、爱尔兰、美国参考时效，不含根西岛和泽西岛
- [YunExpress：最高省21%！云途自提服务上线](https://www.yunexpress.com/newsroom/detail/3) – 3 公里内自提点、平均 6–10 个自然日、轨迹显示自提点地址
- [YunExpress：云途物流成为速卖通官方物流合作伙伴](https://www.yunexpress.com/newsroom/detail/1) – 2025 年 4 月 16 日成为速卖通官方物流合作伙伴
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – YT 16 位单号规则、`yuntrack.com` 链接与英文状态/DHL/GOFO/J&T Australia 节点解析、时区推算及尾程接力规则
:::
