---
title: GEODIS Poland 波兰物流查询：PL 单号、状态与派送
description: 查询波兰 GEODIS Poland（原 Pekaes）货运与包裹：PL 单号格式、状态含义、派送与场站自提、理赔及客服电话。
slug: geodis-poland-tracking
published: 2026-11-24
updated: 2026-11-24
---

GEODIS Poland 是法国国家铁路（SNCF）旗下法国物流集团 GEODIS 在波兰的托盘与包裹运输网络。GEODIS 于 2021 年收购 PEKAES，目前该网络由总部位于波兰布沃涅（Błonie）的 GEODIS Road Network 运营。具体查询方式取决于寄件人给你的单号：

- **`PL` 加 16 位数字，或 Strefa Klienta 链接：** 属于 GEODIS Poland 的包裹或托盘货件。可直接粘贴到上方查询框，或在 GEODIS 波兰查询页 [Strefa Klienta](https://strefaklienta.pekaes.geodis.pl/en/) 查询。
- **其他 GEODIS 单号：** Peek 还读取另一个 GEODIS 查询系统，即 GEODIS France 的查询系统，其单号以 `1G` 开头（见下表）。geodis.com 上的总搜索可查空运、海运和尾程货件，公路货件则会指引到 Strefa Klienta。
- **没有单号：** 单号由商家或寄件人提供，通常写在订单确认或发货邮件中。

## GEODIS Poland 运单号格式与查找方式

GEODIS 仅面向企业客户承接发货，因此运单号掌握在寄件方（通常是网店或制造商）手里；如果手头没有，请联系寄件人。仍然找不到？请看[如何查找运单号](guide:find-tracking-number)（英文）。

| 单号（虚构） | 说明 |
| --- | --- |
| `PL4829105736491827` | `PL` 加 16 位数字：GEODIS Poland 运单号，其查询页称为“Shipment No.”。 |
| `1G4829105736` | `1G` 加 10 位大写字母或数字：属于法国 [GEODIS](carrier:geodis)，需在 GEODIS 法国收件人入口而非 Strefa Klienta 查询。 |

在 Strefa Klienta 页面中，将单号输入“Enter Shipment No.”框后点击“Search”。如果系统回复 `Shipment with such number does not exist`，说明查无此号：按 GEODIS 建议请联系商家或寄件人。若出现波兰语提示 `Proszę wprowadzić właściwy numer przesyłki.`，表示需要输入有效的货件编号，请检查是否输错。

## GEODIS Poland 物流状态说明

Strefa Klienta 提供波兰语和英语界面。查询后先展示概览（状态、目的地国家、场站、日期、件数及收件邮编与城镇），点击“Details”即可展开带时间和场站地点的每条扫描。英文页面的措辞如下，按货件通常经过的先后顺序排列：

| GEODIS 显示 | 对你意味着什么 |
| --- | --- |
| `Received from the sender` | GEODIS 已从寄件人处揽收货件。 |
| `Inbound Scan From Driver to Terminal` | 司机已将货件交回场站：揽收后交回发件场站，或在 `Outbound for Delivery` 后于派送路线结束时带回派送场站。若此前没有 `Consignee Absent` 或 `Failed delivery (SMS)`，说明当天路线未实际尝试投递，货件等待下一趟派送。 |
| `Outbound Scan from (Collection) Terminal to Line-haul` | 已装上干线卡车离开发件场站，前往负责你所在片区的场站。 |
| `Inbound Scan from Line-haul to (Delivery) Terminal` | 已抵达负责派送的终端场站。 |
| `Awaiting Delivery` | 正在派送场站等待装车派送。 |
| `Outbound for Delivery` | 已装上派送车辆外出派送。 |
| `Consignee Absent` | 司机上门时未见到收件人。 |
| `Failed delivery (SMS)` | 派送未成功，并已通过短信确认。 |
| `Awaiting designation of new delivery date` | GEODIS 正等待确定新的派送日期。 |
| `Awaiting Decision` | GEODIS 正等待寄件人下达处理指令。 |
| `Delivery Refusal` | 收件人拒收货件。 |
| `Request for Return` | GEODIS 已收到退件指令：货件正在退回寄件人途中，尚未送达。 |
| `Delivered` | 已签收。`Delivered (SMS)` 同义，并经短信确认。 |
| `Claim Received` | 已登记理赔申请；仅作记录，货件本身并未移动。 |

还有几条扫描表示阻滞：`Stopped at Hub` 和 `Stopped at Collection Terminal`（滞留在分拨枢纽或始发场站）、`Not Dispatched`（错过原定班车或派送但仍在网内）以及 `Failure`。以 `Pseudoscan` 开头的条目会冲销此前的签收、退件或理赔扫描。波兰语界面对应相同步骤，例如 `wydanie do dostawy`（派送中）和 `doręczone`（已签收）。更多见[物流状态含义](guide:tracking-statuses)（英文）。

## 波兰 GEODIS 派送：送货上门、通知与场站自提

GEODIS 自 2024 年 11 月 4 日起施行的一般服务条款规定了派送细则；条款约束下单客户（即订舱企业，通常是网店），其中大多数规则针对托盘拼箱货件：

- **派送未成功。** 如收件人不在，司机会留下未妥投通知。第二次派送失败后货件退回寄件人；拒收或寄件人地址有误同样退回。参见[错过派送怎么办](guide:missed-delivery)（英文）和[包裹退回寄件人](guide:returned-to-sender)（英文）。
- **发往私人地址的托盘货件。** GEODIS 仅在工作日（周一至周五 8:00 至 16:00）派送。寄件客户必须提供你的手机号：GEODIS 会发短信更新动态，并提前通过电话、短信或邮件预约送货，最晚在可派送当天致电。若两个工作日内联系不上你，GEODIS 会通知寄件客户并将货件退回。
- **卸货与搬运。** 私人地址托盘由司机在地面层卸货；若卡车无法安全停靠，则在最近的安全硬化地面处卸货。搬运入户（“wniesienie”）、指定时间点以及 10:00 前/12:00 前送达（仅限部分邮编）均须由寄件客户单独预订。
- **场站自提。** 若寄件客户指示你前往 GEODIS 场站自提，货件自该指令起保留 48 小时（按工作日计），领取时间为周一至周五 11:00 至 16:00，逾期退回寄件人。
- **货到付款。** 可付现金，或通过银行卡、BLIK 及其他 GEODIS 接受的非现金方式支付，最高限额 10,000 波兰兹罗提（PLN）。
- **签收验货。** 在司机终端或送货单据上签字。GEODIS 条款规定签收前不得拆包查验内件，因此请先检查外包装并在单据上注明任何破损或短少。

## GEODIS Poland 货件延误、破损或未送达

**停止更新。** 若连续多日没有新扫描，请看[物流停止更新怎么办](guide:tracking-not-updating)（英文）并联系商家。

**延误。** 波兰消费者保护机构 UOKiK 指出，除非双方另行约定，卖家须及时且在购买后 30 天内交货；逾期后你可给予额外期限，若仍未送达可解除合同。

**破损或少件。** 签收当场须与司机使用 GEODIS 表格共同填写并签署破损报告（“protokół szkody”）。隐性破损或短少须在签收后 7 天内向 GEODIS 报告，索赔方须证明系交货前发生。请保留货物供 GEODIS 勘验并拍摄带日期时间的照片。GEODIS 理赔表提醒：仅填写破损报告不等于正式索赔。详见[包裹破损处理](guide:damaged-parcel)（英文）。

**谁负责索赔。** 按 GEODIS 条款，理赔由寄件客户或其书面授权方提出，须尽快书面寄送至布沃涅理赔部：破损或短少在签收后 14 天内，延误或丢失在计划派送日后 21 天内。作为买家请直接联系商家：UOKiK 指出，在你收到包裹之前由卖家对包裹负责（即使破损由承运商造成），因此你向卖家投诉，由卖家向承运商理赔。

## GEODIS Poland 客服与联系方式

GEODIS 在波兰的官方渠道如下：

- **客户服务（Dział Obsługi Klienta）：** +48 22 460 26 26（见 Strefa Klienta 波兰语页面），未公布服务时间。
- **邮寄理赔地址：** Dział Reklamacji, ul. Spedycyjna 1, 05-870 Błonie。理赔表与破损报告表可在 Strefa Klienta 下载。
- **网点地址：** [GEODIS 波兰联系页面](https://geodis.com/pl-pl/skontaktuj-sie-z-nami)列出了波兰 42 处网点地址，包含 GEODIS Road Network 各分支。
- **在线表单：** geodis.com 上的表单用于业务报价且要求填写公司名称，仅面向企业客户。

## 使用 Peek 跟踪 GEODIS Poland 包裹

`PL` 加 16 位数字并非 GEODIS 独占格式（`PL` 代表波兰），因此 Peek 无法单凭单号直接锁定 GEODIS Poland。但直接粘贴即可（Peek 跟踪 4,500 多家快递公司）：它会向 GEODIS Poland 核实，确认有记录时显示“GEODIS Poland 有这个包裹的记录。”如果 GEODIS 尚未录入，页面会先显示“可能是 GEODIS Poland”和“等待 GEODIS Poland 的首次扫描”并持续查询；满一周仍查不到时，Peek 会建议与寄件人核对单号或换一家快递。如需手动指定，点击快递栏的“更换”，在“符合这个单号”下选择“GEODIS Poland”或搜索（输入 Pekaes 也能找到；单独的“GEODIS”指的是 GEODIS France）；已添加的包裹则使用“更换快递公司”。粘贴包含单号的 Strefa Klienta 链接无需手动选择，Peek 会直接识别为 GEODIS Poland。

Peek 读取英文版 Strefa Klienta，展示状态、每条 GEODIS 英文扫描及场站地点、签收时间以及目的地国家。波兰场站按华沙时间解析，境外场站按当地时钟解析，再全部换算为你设备的时区；在 peektracker.com 以及 iPhone 和 Android App 中，轨迹末尾会注明“时间按你所在的时区显示：”及对应时区名称。当出现 `Pseudoscan` 冲销先前扫描时，Peek 会同步移除被取消的条目，而 `Claim Received` 不会改变包裹当前状态。GEODIS 查询页不提供预计送达日、重量、服务名、收件人姓名或签收证明；Peek 也会省略收件邮编、地址、件数、场站代码以及可能包含签收人姓名的备注栏。

在你打开包裹页面时，或在派送当天开启了通知时，Peek 最高每 10 分钟检查一次；在 GEODIS 扫描货件外出派送的当天，自当地时间早上 6 点起，只要 GEODIS 自己的查询系统正常响应，最高每 2 分钟检查一次（通过物流查询服务时，最高每 15 分钟一次）；其余时候每小时检查一次。连续两天无新动态后（若仍停留在“已生成面单”或“已揽收”，则为 12 小时后），改为每小时检查一次。Peek 可以按“每次扫描”、“重要环节”或“仅派送当天”通知你。Peek 无法预约派送日、安排自提或提交理赔，请致电 GEODIS 或联系商家。

## GEODIS Poland 快递查询常见问题

### GEODIS Poland 和 Pekaes 是同一家公司吗？

是的。GEODIS 在 2021 年收购了 PEKAES，现由 GEODIS Road Network 运营，查询系统仍沿用 strefaklienta.pekaes.geodis.pl 域名。

### GEODIS 在波兰周六派送吗？

GEODIS 条款未提及周六派送：发往私人地址的托盘仅在周一至周五 8:00 至 16:00 派送，包裹时效也不计周六、周日及法定节假日。

### GEODIS 司机会帮我把货搬进屋里吗？

仅限托盘货件且寄件客户单独预订了搬运入户服务时才提供（限重 300 公斤、距卸货点 150 米以内、无电梯最高二层）；否则司机仅在你的地址地面层卸货。

### GEODIS 在波兰派送要多久？

GEODIS 官网标明波兰境内标准时效为 24 至 48 小时，特快服务为次日中午前。其条款规定 30 公斤以内包裹自揽收起 48 小时（不含周末与节假日），托盘为 2 个工作日，两者均注明“原则上如此”。

:::sources
- [GEODIS Poland: Strefa Klienta, shipment tracking](https://strefaklienta.pekaes.geodis.pl/en/) – 查询框与按钮、查无此号提示、理赔表与破损报告表
- [GEODIS Poland: Strefa Klienta, Śledzenie przesyłek](https://strefaklienta.pekaes.geodis.pl/pl/) – 客服电话与一般服务条款
- [GEODIS Road Network: Ogólny Regulamin Świadczenia Usług, from 4 November 2024](https://strefaklienta.pekaes.geodis.pl/pl/?file=files/pekaes/Dla%20Klienta%20-%20nowe%20www/GEODIS_SK/GEODIS%20RN_Ogolny%20Regulamin%20Swiadczenia%20Uslug_od%2004.11.2024.pdf) – 寄件客户、尝试次数、私人托盘、通知、卸货、搬运入户、场站自提、货到付款、签收、时效与理赔
- [GEODIS Road Network: Formularz zgłoszenia reklamacji](https://strefaklienta.pekaes.geodis.pl/pl/?file=files/pekaes/Dla%20Klienta%20-%20nowe%20www/GEODIS_SK/GRN%20Formularz%20zgloszenia%20reklamacji.pdf) – 理赔寄送地址与破损报告不等同理赔说明
- [GEODIS: Śledź swoją przesyłkę](https://geodis.com/pl-pl/sledz-swoja-przesylke) – 单号来源、geodis.com 查空海运与尾程、公路货件独立查询及单号无效处理
- [GEODIS: Twój obszar roboczy](https://geodis.com/pl-pl/twoj-obszar-roboczy) – Strefa Klienta 作为 GEODIS 跟踪系统
- [GEODIS: Transport palet i paczek](https://geodis.com/pl-pl/uslugi-transportowe/transport-palet-i-paczek) – 仅服务企业客户与参考时效
- [GEODIS: Skontaktuj się z nami](https://geodis.com/pl-pl/skontaktuj-sie-z-nami) – 报价表单与波兰 42 处网点
- [GEODIS: Historia](https://geodis.com/pl-pl/historia) – 2021 年收购 PEKAES 及 SNCF 控股背景
- [UOKiK: Odpowiedzialność za przesyłkę](https://prawakonsumenta.uokik.gov.pl/prawa-ogolne/odpowiedzialnosc-sprzedawcy-za-przesylke/) – 送达前由卖家承担包裹责任
- [UOKiK: Wydanie towaru](https://prawakonsumenta.uokik.gov.pl/prawa-ogolne/wydanie-towaru/) – 30 天交货期、宽限期与解约权
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – 单号格式、Strefa Klienta 链接、状态措辞、派送路线结束后带回场站的货件、GEODIS France 单号、Peek 读取的内容
:::
