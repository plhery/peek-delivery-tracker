---
title: 立陶宛邮政 Lietuvos paštas 查询：单号、状态与快递柜
description: 查询立陶宛邮政 Lietuvos paštas（LP Express）包裹：LT 单号格式、物流状态解读、快递柜与邮局保管期、清关费及客服电话。
slug: lietuvos-pastas-litaowan-youzheng-chaxun
published: 2026-11-24
updated: 2026-11-24
---

立陶宛邮政（Lietuvos paštas）是立陶宛国家邮政服务机构，LP Express 则是其面向快递柜、上门派送和自助寄件的品牌。你可以在支持英文切换的 [lpexpress.lt/track](https://lpexpress.lt/track) 页面查件，或直接把运单号粘贴到上方输入框。单号通常出现在立陶宛邮政发送的短信或邮件里，自行寄件时也会印在寄件回执上。

## 立陶宛邮政运单号格式

立陶宛邮政运单号共 13 位，采用[国际邮政标准格式](guide:tracking-number-formats)（英文）：两位字母、九位数字，末尾为 `LT`。立陶宛邮政条款列明了两个 LP Express 前缀：`CC` 和 `CH`。

| 单号示例（虚构） | 说明 |
| --- | --- |
| `RR482917367LT` | 从立陶宛寄出的邮政邮件或包裹。 |
| `CC482917367LT` | 发往快递柜的 LP Express 包裹。 |
| `CH482917367LT` | 由快递员送货上门（立陶宛境内或境外）的 LP Express 包裹。 |

包裹送达快递柜后，立陶宛邮政会通过短信和/或邮件发送单号、快递柜地址以及取件 PIN 码。

官方短信均以 `LPASTAS` 或 `LP_EXPRESS` 作为发件人名称发送，绝不会来自电话号码。

结尾为其他国家代码则属于对应国家的邮政，例如 `…LV` 为拉脱维亚邮政（Latvijas Pasts），`…EE` 为[爱沙尼亚邮政（Omniva）](carrier:omniva)。境外寄入立陶宛的包裹会保留发件国邮政的原始单号。另一家在立陶宛运营的快递 Venipak 则使用不同格式（如 `V48291E7365428`）。如果找不到单号，请参阅[如何查找运单号](guide:find-tracking-number)（英文）。

## 立陶宛邮政物流状态中文解读

lpexpress.lt/track 可用英语、立陶宛语、拉脱维亚语或爱沙尼亚语显示每条动态；主站 post.lt 仅提供立陶宛语。每一步包含一条简短标题和一条较长的事件描述。以下为英文事件描述的常见顺序：

| 立陶宛邮政状态原文 | 中文含义 |
| --- | --- |
| `The label for the parcel has been created` | 寄件人已创建面单，立陶宛邮政尚未收到包裹实物。 |
| `Parcel received` | 立陶宛邮政已从寄件人处收寄包裹。 |
| `The parcel is at the distribution center` | 包裹已到达分拣中心（轨迹会标明城市）。 |
| `The parcel is transported to another distribution center` | 正在不同分拣中心之间转运。 |
| `The parcel has been sent abroad` | 包裹已发往立陶宛境外。 |
| `The parcel was received at the distribution center of the recipient's (or transit) country` | 已抵达收件国或中转国的分拣中心。 |
| `The parcel has been forwarded for delivery` | 正在派送中，送往收件地址或指定快递柜。 |
| `The parcel was delivered to the UNISEND parcel locker` | 已存入指定快递柜，等待你前往领取。 |
| `The parcel has been delivered to the recipient` | 已由收件人签收，或已从快递柜取走。 |
| `Sent to receiver` | 仅为阶段标题而非具体步骤：包裹在途或正停放在快递柜中。 |

有两处容易让人误解：一是标题 `Sent to receiver` 会从首次分拣扫描一直持续到你领走包裹（包含存放在快递柜的整段时间），只有查看下方的事件描述才能确认是否已经可以取件；二是即使包裹在立陶宛境内的 LP EXPRESS 快递柜里，轨迹原文也会写着 UNISEND，因为 Unisend 是该快递柜网络在拉脱维亚和爱沙尼亚使用的名称。

如果页面提示 `Your parcel could not be found`，请检查单号是否输错；`Without tracking` 表示该邮件未购买跟踪服务。在立陶宛境内，只有挂号类邮件才会显示完整轨迹。另见[物流状态说明](guide:tracking-statuses)（英文）。

## LP Express 快递柜、邮局自提与派送未成功

**快递柜：** 立陶宛、拉脱维亚和爱沙尼亚共有约 1,500 组 LP EXPRESS 与 Unisend 快递柜，全天候 24 小时开放。在柜机屏幕选择 “Atsiimti siuntą”（取件）并输入短信或邮件收到的 PIN 码即可开柜。取件时限为包裹入柜起 **72 小时**。超时后快递员会取出包裹，你会收到一条带链接的短信：可在 4 天内免费将包裹重新投递至同一快递柜或更换另一组快递柜一次，否则包裹将退回寄件人。尺寸过大放不进快递柜的包裹会转存至立陶宛邮政服务网点，保管 15 天。

**中途改投：** 如需把在途包裹改投到其他快递柜，可打开查询页面输入单号，在 “To parcel locker” 下选择新快递柜（也提供 “To home address” 选项）。如果已经来不及修改，页面会提示 `Redirection is not possible` 或改投期限已过。

**快递员上门派送：** 快递员上门等待不超过 10 分钟，需在快递员手持终端上签收。如果家中无人，你会收到短信或邮件通知，包裹会自首次派送日起在物流中心免费保管 7 个自然日。

**邮局邮件：** 挂号邮件上门派送一次；无人接收时会通过信箱、邮件或短信发出通知单。领件需携带通知单与身份证件。按 TAI-16 条款，邮局包裹和信件自通知次日起保管 30 天（post.lt 常见问题写为一个自然月），除非寄件人合同另有约定。

**来自欧盟境外的包裹：** 立陶宛邮政要求在收到缴费通知后 **7 个自然日内**通过 LP EXPRESS 自助系统缴清进口税费，逾期将退回寄件人。缴清后包裹会送往邮局或快递柜，取件时无需再缴费。委托立陶宛邮政代为申报的清关服务费为：货值 150 欧元以内 6.99 欧元，超过 150 欧元或标准报关为 30 欧元，另加按货值及运费计征的 21% 进口增值税。若自行申报或商家已在结账时通过 IOSS 代收增值税，其服务费为 2 欧元。自 2026 年 7 月 1 日起，从欧盟境外网购且货值不超过 150 欧元的包裹按每项商品征收 3 欧元欧盟关税；自 2026 年 11 月 1 日起，无论货值高低均按每项商品加征 2 欧元欧盟统一处理费。“一项商品”指共享同一税则分类、品名描述及（如需）原产地的货品。另见[包裹滞留海关怎么办](guide:customs)（英文）。

## 包裹延误、丢失或退回怎么办？

立陶宛邮政建议拨打 1842 报备丢失或延误的挂号件及保价件。理赔权属于寄件人，期限为交寄起 6 个月内，网购订单请先联系商家处理。一旦签收，立陶宛邮政将不再赔付。

境外寄出的邮件发生问题，立陶宛邮政同样要求联系寄件人。快递员送货上门时如发现破损，务必当场在快递员设备或随附单据上注明，否则立陶宛邮政不承担责任。

**从立陶宛寄往境外：** 出现 `The parcel has been sent abroad` 后，由目的国邮政承接派送，理赔时立陶宛邮政以目的国邮政对妥投或丢失的核实结果为准。Peek 也会同步向目的国邮政查询（见下文）。无论包裹在哪个国家丢失，寄件人均可在交寄起 6 个月内索赔，立陶宛邮政会在收到索赔后 90 个自然日内完成国际件赔付。

如果轨迹多日不动，参阅[物流停止更新怎么办](guide:tracking-not-updating)（英文）。

## 立陶宛邮政客服与联系方式

post.lt 官网公布的联系方式（联系页面为立陶宛语）：

- **立陶宛境内电话：** 1842 或 +370 700 554 00（按运营商资费另加接通费）。
- **境外拨打电话：** +370 5 233 3060。
- **服务时间：** 立陶宛时间工作日 8:00 至 19:00，周六 9:00 至 16:00。
- **电子邮件：** info@post.lt。
- **在线表单：** 访问 post.lt 上的 “Rašykite mums”（写信给我们），内设理赔与国际查单标签页；网站同时提供在线聊天客服。

> 立陶宛邮政警示防范以地址错误、包裹扣留或退件为由的诈骗短信。官方短信仅以 `LPASTAS` 或 `LP_EXPRESS` 发出，绝不会索要银行卡号或个人敏感信息，也绝不发送直接付款链接。遇到可疑信息请举报至 prevencija@post.lt；若已填写银行卡信息，请立刻联系发卡银行。

## 用 Peek 查询和跟踪立陶宛邮政包裹

当 `LT` 结尾的国际邮政单号以 `C`、`E` 或 `HC` 开头且校验位正确时，[Peek](/) 会按格式直接识别为立陶宛邮政并显示“识别出的快递公司”。不过不少 `CC`、`CH` 和 `HC` 单号并不满足国际邮政校验位，此时 Peek 会提示“这个单号的校验位对不上。”，点击“按输入的单号跟踪”后仅向立陶宛邮政查询。其余以 `LT` 结尾且校验位正确的单号，Peek 会同时询问立陶宛邮政和 [Chronopost](carrier:chronopost)，查到后显示“Lietuvos paštas 有这个包裹的记录。”；若暂时未查到，则会提示尚未查到且添加后 Peek 会持续查询。lpexpress.lt 和 post.lt 的查件链接也可以直接粘贴。

从境外寄入、使用国际邮政格式的包裹会先由发件国邮政跟踪。当发件国邮政显示目的国为立陶宛，或未写明目的国且你在立陶宛本地查件时，Peek 也会询问立陶宛邮政；一旦立陶宛邮政出现更新的记录，Peek 即转由立陶宛邮政接续跟踪并保留前期轨迹。你也可以随时点“更换”手动选定 “Lietuvos paštas”。注意以 `C` 开头、`DE` 结尾的 [DHL Paket](carrier:dhl) 单号不会转交立陶宛邮政查询。

对于从立陶宛寄出且校验位正确的单号，Peek 采用同样机制：一旦立陶宛邮政显示包裹已到达收件国或中转国的分拣中心，Peek 也会向目的国邮政查询；在此之前，如果你不在立陶宛查件，Peek 会询问你查件所在国家的邮政。发往俄罗斯、哈萨克斯坦、中国及部分其他国家的邮件，Peek 不会询问目的国邮政。

Peek 直接读取 lpexpress.lt/track 后台英文数据，并可在状态更新时发送通知。当你打开包裹页面，或在派送当天开启了通知时，Peek 最高每 10 分钟检查一次；在立陶宛邮政于立陶宛境内派送包裹的当天，只要立陶宛邮政自己的查询系统正常响应，自立陶宛时间 06:00 起最高每 2 分钟检查一次。若已转由其他承运商接手派送，Peek 会按该承运商的频率检查：Royal Mail 为每小时一次，Poste Italiane、PostNL 和 USPS 最高每 30 分钟一次（派送当天同样如此）。其余情况，以及连续两天无新动态（或在“已生成面单”、“已揽收”状态下满 12 小时），Peek 均每小时检查一次。Peek 会在地图上标出每一步所在的城市、分拣中心或国家，显示清关环节、签收时间以及存放包裹的快递柜或邮局及其所在城镇（不显示具体街道和邮编）。立陶宛境内记录按维尔纽斯时间解析并换算为你设备的本地时区，不提供预计送达日和重量。状态保留立陶宛邮政英文原文；退件时先显示“需要留意”，退回寄件人后显示“已退回寄件人”。

## 立陶宛邮政查询常见问题

### LP Express 是什么？

LP Express 是立陶宛邮政在立陶宛境内用于快递柜、快递员上门派送和自助寄件的服务品牌；在拉脱维亚和爱沙尼亚，同一服务名为 Unisend。

### 立陶宛邮政有英文查询页面吗？

有。打开 lpexpress.lt/track 即可把界面切换为英文；主站 post.lt 目前没有英文版。

### 立陶宛邮政能查国际包裹吗？

可以，前提是寄件时选择了带跟踪的服务：从立陶宛寄出需选择 “Su sekimu”（带跟踪）服务；境外寄出的国际件可向寄件人核实是否带跟踪单号。

### LP Express 包裹通常几天送达？

立陶宛邮政表示，若寄件人在截单时间前交寄，立陶宛境内的快递柜包裹通常在次个工作日或周六送达。邮局包裹的国内服务质量目标为 97% 在交寄后 4 个工作日内送达；寄往其他欧盟国家的优先信件为 85% 在 3 个工作日内、97% 在 5 个工作日内送达。

:::sources
- [Lietuvos paštas: Siuntų sekimas](https://www.post.lt/lt/siuntu-sekimas) – 单号格式、回执、轨迹范围、1842 报备、索赔规则、快递柜二次投递、国际查单与送达时效目标
- [LP Express: Parcel tracking](https://lpexpress.lt/track) – 英文查询页、多语言切换、标题与改投提示
- [Lietuvos paštas: Dažniausiai užduodami klausimai](https://www.post.lt/duk) – 快递柜常见问题、邮局取件与保管期
- [Lietuvos paštas: Siuntos](https://www.post.lt/lt/duk/siuntos) – 保管期限与取件规定
- [Lietuvos paštas: LP EXPRESS paštomatai](https://www.post.lt/lt/duk/lp-express-pastomatai) – 72 小时取件期、4 天免费改投、超尺寸转存 15 天与预缴进口税费
- [Lietuvos paštas: Siuntų siuntimas paštomatu](https://www.post.lt/lt/lp-express-pastomatai) – LP EXPRESS 与 Unisend 品牌、约 1,500 组快递柜、PIN 通知与次日达
- [Lietuvos paštas: Pašto paslaugų teikimo nesutartiniams naudotojams taisyklės TAI-16](https://www.post.lt/sites/default/files/Dokumentai/PA%C5%A0TO%20PASLAUG%C5%B2%20TEIKIMO%20TAISYKL%C4%96S.pdf) – CC 与 CH 前缀、派送规则、7 天和 30 天保管期、证件、破损与 7 天缴费期
- [Lietuvos paštas: Siuntų deklaravimas](https://www.post.lt/lt/siuntu-deklaravimas) – 报关服务费、增值税与 7 天缴费期
- [Lietuvos paštas: Kontaktai](https://www.post.lt/lt/kontaktai) – 电话、服务时间、资费、邮箱与在线客服
- [Lietuvos paštas: Rašykite mums](https://www.post.lt/rasykite-mums) – 在线表单、理赔与国际查单入口
- [Lietuvos paštas: Būkite atsargūs, Lietuvos paštu apsimetantys sukčiai](https://www.post.lt/lt/apie-mus/naujienos/bukite-atsargus-lietuvos-pastu-apsimetantys-sukciai-gviesiasi-pinigu-ir-duomenu) – 诈骗短信提醒、官方发件名与 prevencija@post.lt
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 自 2026 年 11 月 1 日起实施的 2 欧元欧盟处理费
- [European Commission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 每项 3 欧元关税与 2 欧元处理费及“项”的认定
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Peek 读取和显示的信息
:::
