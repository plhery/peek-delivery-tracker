---
title: 匈牙利邮政（Magyar Posta）物流查询：状态与快递柜
description: 查询匈牙利邮政（Magyar Posta / MPL）包裹：单号格式、匈牙利语状态中文含义、PostaPont 网点与快递柜、清关和联系。
slug: magyar-posta-wuliu-chaxun
published: 2026-11-26
updated: 2026-11-26
---

匈牙利邮政（Magyar Posta，Hungarian Post）是匈牙利的国家邮政服务，MPL 是其包裹服务、PostaPont 自提点和快递柜的品牌名称。你可以在 posta.hu 的“Nyomkövetés”页面、Magyar Posta 应用或上方输入框免费查询包裹。单号来自商家的发货邮件，或包裹到达时 Magyar Posta 发出的邮件或短信。

## Magyar Posta 运单号

posta.hu 查询页列出的单号形态如下（以下数字为虚构）：

| 单号 | 含义 |
| --- | --- |
| `PB48271936404` | 匈牙利国内 MPL 包裹：`P` 加一位字母和 11 位字符，共 13 位。如条码更长，输入前 13 位字符即可。 |
| `CP482917367HU` | 国际包裹（Europe+ 或 EMS）：两位字母、9 位数字加 `HU`。 |
| `RR619402838HU` | 国际挂号信。国内挂号信以 `RL` 开头，共 16 位字符。 |
| `JJH30KLMNPQ748291635` | MPL Europe Standard：以 `JJH` 开头的 20 位字符，类似 DHL 编号。 |

Magyar Posta 的邮件中会注明编号，点击即可在应用中打开查询。

海外网店给你的编号往往根本不是邮政编号，而是寄件人自家系统编号，永远不会出现在 posta.hu 上。来自海外的邮件也常常在抵达匈牙利后才显示记录，因此“Invalid item identifier”可能意味着尚未交寄、尚未到达匈牙利或输错了单号。请向商家索取邮政单号，并参见[运单号格式](guide:tracking-number-formats)（英文）。

`P` 加字母加 11 位数字的形态同时也符合 Peek 向 [La Poste](carrier:la-poste) 查询的格式（两位字符加 11 位数字），因此法国包裹单号可能看起来很像匈牙利包裹。

## Magyar Posta 物流状态中文解释

posta.hu 查询页默认打开匈牙利语，点击右上角国旗可切换英语或德语。Peek 显示匈牙利语原文，按流转顺序对照如下：

| Magyar Posta 状态 | 对你的含义 |
| --- | --- |
| `A küldeményt a feladó előrejelezte, az átadást követően megkezdjük a feldolgozást` | 已生成面单：寄件人已预报，交寄后开始处理。 |
| `A küldeményt a feladótól átvettük` | 已从寄件人处收寄。 |
| `A küldemény feldolgozás alatt` | 正在处理中。 |
| `A bejövő küldemény átvétele a kicserélőben` | 来自海外的包裹已到达匈牙利国际互换局。 |
| `Online fizetés elérhető` | 关税与费用已支持在线支付，正在等待缴费。 |
| `Vámkezelés vége` | 清关完成。 |
| `A küldemény továbbítása kézbesítésre` | 已发往你的派送邮局。 |
| `A küldemény a kézbesítőnél van` | 派送中：已交由派送员。 |
| `Sikertelen kézbesítés` | 派送尝试失败。 |
| `A küldemény postán átvehető (értesítőn szereplő dátumtól)` | 自通知单所载日期起可在邮局自提。 |
| `Sikeresen kézbesítve` | 已签收。 |
| `A küldemény nem kézbesíthető (nem kereste)` | 超期未取，正在退回寄件人。 |

`Küldemény előkészítése nemzetközi szállításra`（正在准备国际运输）表示出口包裹正准备离开匈牙利。`A vámkezeléshez szükséges információk bekérése (ügyfélre vár)` 表示海关需要你补充材料并正在等待回复。`A küldemény PostaPonton 14:00 után átvehető` 仅预告 PostaPont 何时可发放包裹，包裹尚未到位。`Feladónak visszakézbesítve` 表示已退回给寄件人。

其他以 `A küldemény nem kézbesíthető (…)` 开头的记录会在括号里标明原因，例如 `átvételt megtagadta`（拒收）或 `ismeretlen címzett`（收件人不详）。

## 派送、PostaPont 网点与快递柜

**通知。** 包裹寄出时 Magyar Posta 会发邮件通知，抵达后再发邮件或短信告知：是在邮局、PostaPont 还是快递柜待取，或已交由派送员及预计送达时间。距离取件截止还剩 2 天时会再发提醒。

**更改派送。** 点击邮件里的 MÓDOSÍTÁS（修改）按钮，或在应用中使用“Kiszállítás módosítása”可修改时间或地址（部分变更需收费）。第二次派送前的通知发出后，不能再做任何修改。

**无人签收。** MPL Business Parcel 提供两次上门派送。首次失败后派送员会致电约定第二次时间，你也可改为自提。如果无人签收，Magyar Posta 会留下通知单（értesítő）或发短信/邮件并保管包裹。取件须出示带照片的身份证件。

**保管期限。** 在邮局或 PostaPont，国内包裹保管 5 或 10 个工作日（由寄件人选定），海外邮件保管 15 个工作日。

**快递柜。** MPL Csomagautomata 快递柜通常 0–24 小时开放。你会通过邮件和短信收到 6 位取件码：在屏幕选“CSOMAGOM ÉRKEZETT”并输入即可。取件期为 2 个工作日，超期会转往附近邮局，按寄件人设定再保管 0、5 或 10 个工作日。

**PostaPont 网点。** 近 3,000 个取件点，包括邮局、MPL 快递柜、MOL 加油站和 COOP 商店。可在 posta.hu 和应用中的 Postakereső 工具查找最近网点。

## 海外包裹、清关与异常处理

**清关。** Magyar Posta 为来自欧盟以外的邮件办理清关。个人收件且价值不超过 150 欧元时通常自动办理：除已在结账时经由 IOSS 缴税外需缴 27% 增值税、按商品类别每类 3 欧元关税，外加邮政代办费。超过 150 欧元须提交清关委托。请在收到短信或邮件后 48 小时内在线支付，或派送时支付。参见[清关说明](guide:customs)（英文）及 posta.hu/vam 费率。

**没有更新。** 客服可凭单号确认邮件是否已到达 Magyar Posta。详见[物流不更新怎么办](guide:tracking-not-updating)（英文）。

**包裹破损。** 外观可见破损须在签收单上当场注明（快递柜包裹请拨打柜机上的电话）。隐蔽破损须在 3 个工作日内携带全部包装前往邮局申报。

**丢失。** 除包裹已签收或寄件人已转让索赔权外，由寄件人（通常是商家）申请理赔。国内包裹自交寄第 15 天起可立案，索赔期满六个月截止。Magyar Posta 在 30 天内答复；如对投诉答复不满意，可在 30 天内向匈牙利国家媒体与通信管理局（NMHH）申诉。

## Magyar Posta 客服电话与联系方式

posta.hu 公布的渠道：

- **电话：** 06-1-767-8282；海外拨打 +36 1 767 8282。匈牙利时间周一至周三及周五 8:30–16:30，周四 8:00–20:00。未单列英文专线。
- **邮箱：** ugyfelszolgalat@posta.hu。
- **线下接待：** 1062 Budapest, Teréz körút 51-53；周一 7:00–19:00，周二至周五 8:00–16:00。
- **清关咨询：** 通过 posta.hu/vam 上的联系表单提交。

> Magyar Posta 绝不会从境外号码发短信，也不会从“.com”邮箱发邮件。其官方短信号码为 06-30-344-4987，绝不会通过电话或邮件索要你的地址，且唯一的付款通知仅针对清关费用，网址始终为 www.posta.hu/szolgaltatasok/vam。

## 用 Peek 跟踪 Magyar Posta 包裹

对于国内 `P…` 单号、国际邮政 `…HU` 单号、挂号信 `RL…` 或 MPL Europe Standard 的 `JJH…`，[Peek](/) 会在服务端浏览器中询问 Magyar Posta 是否收录，因此首次返回可能需要约 20 秒；对于 `P` 加字母加 11 位数字同时会询问 La Poste，对于 `…HU` 邮政单号同时会询问 Chronopost。你也可以点“更换”选择 Magyar Posta，或粘贴带 `searchvalue=` 的 posta.hu 查询链接，立刻识别为 Magyar Posta。

首次查询需在 Peek 服务端浏览器打开 posta.hu 通过验证码，因此不会瞬间返回；后续检查则从 Magyar Posta 应用接口读取。如果验证码被拒，Peek 会在 15 分钟后重试，并在持续被拒时逐次翻倍间隔，最长至 6 小时；期间由物流查询服务顶替，但记录可能滞后。Peek 会以 Magyar Posta 的匈牙利语原文显示状态、含地点的历史记录以及取件点（邮局、PostaPont 或快递柜），但不显示预计送达日、取件截止日或签收人。在 Magyar Posta 自有查询正常响应期间，当你打开包裹页，或在派送当天开启通知时，Peek 最多每 10 分钟查询一次，并在 Magyar Posta 扫描派送当天从匈牙利时间早上 6 点起每 2 分钟查询一次（需在浏览器打开 posta.hu 时为 5 分钟）；其余时间每小时一次。连续两天无新动态（在“已生成面单”或“已揽收”状态下为 12 小时）后改为每小时查询一次，在“已生成面单”下连续七天无新动态后改为每 6 小时一次。如果包裹在“已生成面单”、“已揽收”或“运输中”状态下连续四天没有新记录，Peek 会标出该记录日期，显示“自……起没有物流更新”、提示“包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 Magyar Posta 查询。”以及“向 Magyar Posta 寻求帮助”链接；满三周无新记录时提示“已超过三周没有新动态。如果还没收到，请联系卖家。Peek 会持续向 Magyar Posta 查询。”。Peek 支持“每次扫描”、“重要环节”或“仅派送当天”通知。来自海外的邮件保留外国邮政单号；当外国邮政标明寄往匈牙利（或未标明目的地而你在匈牙利查询）时，Peek 也会询问 Magyar Posta，并在 Magyar Posta 出现不晚于外国邮政最新记录的扫描后切换跟进。

## 关于 Magyar Posta 物流查询的常见问题

### Magyar Posta 是哪个国家的邮政？

匈牙利。Magyar Posta Zrt. 是匈牙利指定的普遍邮政服务提供商，英文官网称为 Hungarian Post。

### 可以用英文查 Magyar Posta 吗？

可以。在 posta.hu 查询页点击右上角国旗图标即可切换英文。

### MPL 是什么？

MPL 是 Magyar Posta 用于包裹服务和网点网络的品牌名称，包括 MPL Business Parcel、MPL PostaPont 和 MPL Csomagautomata 快递柜。

### 怎么查寄往匈牙利的国际包裹？

向商家索取邮政运单号并在 posta.hu 查询。海外包裹通常在抵达匈牙利后才出现在那里；网店自己的订单号永远查不到。

:::sources
- [Magyar Posta: Keresés - Magyar Posta Nyomkövetés](https://www.posta.hu/ikrnyomkovproxy/) – 单号格式、13 与 16 位字符、语言、10 个单号、海外商家单号、到达匈牙利、电话与邮箱
- [Magyar Posta: Nyomkövetés](https://www.posta.hu/kuldemeny_erkezese/nyomonkovetes) – 免费查询、应用、客服
- [Magyar Posta: Belföldi csomagküldemények értesítései](https://www.posta.hu/kuldemeny_erkezese/elektronikus_ugyfelertesites_belfold) – 通知、MÓDOSÍTÁS、提醒、修改截止点
- [Magyar Posta: Háznál történő csomagkézbesítés](https://www.posta.hu/kuldemeny_erkezese/haznal_torteno_csomagkezbesites) – 两次派送、电话约定、代领资格、隐蔽破损
- [Magyar Posta: Postai küldemények átvétele](https://www.posta.hu/kuldemeny_erkezese/hasznos_tudnivalok/postai_kuldemenyek_atvetele) – 保管期限、身份证件
- [Magyar Posta: MPL Csomagautomata](https://www.posta.hu/csomagautomata) – 快递柜取件码、2 个工作日、转邮局
- [Magyar Posta: PostaPont](https://www.posta.hu/postapont) – 网点规模、MOL 与 COOP、营业时间
- [Magyar Posta: Postai vámügynöki szolgáltatás](https://www.posta.hu/vam) – 清关、150 欧元、增值税、关税、48 小时缴费
- [Magyar Posta: Postai szolgáltatások ÁSZF](https://www.posta.hu/static/internet/download/PASZF_ASZF01_ASZF.pdf) – 普遍服务商、通知、理赔、投诉、NMHH、客服时间与接待地址
- [Magyar Posta: Magyar Posta app](https://www.posta.hu/postaapp) – 应用查件、更改派送、Postakereső
- [Magyar Posta: WARNING! Phishing emails, SMS messages](https://www.posta.hu/news/phishing) – 诈骗信息、官方短信号码
- [Magyar Posta: Hungarian Post](https://www.posta.hu/international_main) – 英文名称
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Peek 读取内容、匈牙利语状态文案
:::
