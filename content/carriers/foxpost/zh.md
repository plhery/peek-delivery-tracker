---
title: 匈牙利 Foxpost 物流查询：单号、状态、快递柜与客服
description: 查询匈牙利 Foxpost 包裹：CLFOX 与 66639 单号来源、匈语状态中文对照、快递柜 5 天保管期、Z-Pont 取件及客服联系。
slug: foxpost-tracking
published: 2026-11-26
updated: 2026-11-26
---

Foxpost 是匈牙利的包裹快递服务商，负责在匈牙利全境将包裹派送至其智能快递柜和收件人地址；自 2026 年 1 月起已合并整合 Packeta Hungary。你可以在 [foxpost.hu](https://foxpost.hu/csomagkovetes/) 查件，或直接将单号粘贴到上方输入框。包裹单号位于包裹创建时 Foxpost 发给你的邮件末尾，通常以 `CLFOX` 开头。

## Foxpost 运单号格式

Foxpost 的查询输入框支持以下三种单号：

| 单号 | 归属说明 |
| --- | --- |
| `CLFOX482917365048291` | `CLFOX` 加数字：发往 Foxpost 快递柜或自提点的包裹。Foxpost 也称其为包裹条码（barcode）。 |
| `Z4829173650` | `Z` 加数字：Packeta 包裹。如果 Foxpost 查询页查不到该 `Z` 单号，会自动跳转到 Packeta 查询页。 |
| `6663948271` | 以 `66639` 开头的数字：选择送货上门的 Foxpost 包裹。 |

Foxpost 发给你和寄件人的邮件末尾都附有包裹单号；若注册了 Foxpost 账号，单号也会显示在 “Bejövő csomagjaim”（待收包裹）中。包裹送入快递柜后，开柜码会通过 Viber（否则短信）以及电子邮件发送。

全都找不到？请向商家索取，或参阅[如何查找运单号](guide:find-tracking-number)（英文）。如果单号以 `Z` 开头，则属于 [Packeta](carrier:packeta)。

## Foxpost 匈牙利语物流状态含义

Foxpost 查询页按简短标题、日期时间和一句说明展示每个步骤。官网默认为匈牙利语，菜单中可切换英文。以下按包裹流转顺序列出匈牙利语状态原文与中文含义：

| Foxpost 显示 | 对你的含义 |
| --- | --- |
| `Csomagod létrejött a rendszerünkben, a feladó még nem adta át azt a FOXPOST részére.` | [面单已生成](guide:tracking-statuses)（英文），寄件人尚未将包裹交给 Foxpost。 |
| `Csomagod fel lett adva, a csomagautomatában el lett helyezve. A neked címzett (feladott/visszaküldött) csomagot hamarosan kézbesítjük.` | 寄件人已将包裹投入寄件快递柜，尚未到达你的取件柜。 |
| `Csomagodat futárunk kivette a csomagautomatából.` | 快递员已从寄件快递柜取出包裹。 |
| `Csomagod beérkezett a FOXPOST raktárába. A feladott/visszaküldött csomag hamarosan kézbesítésre kerül.` | 已抵达 Foxpost 仓库。 |
| `Csomagodat az automata telítettsége / műszaki hibája miatt átírányítottuk egy másik csomagautomatába` | 原快递柜已满或故障，包裹改投至另一座快递柜（随后注明新柜名称）。 |
| `Csomagod megérkezett az általad választott (vissza)kézbesítési automatába, most már átveheted!` | 已就绪待取：使用通知中的取件码开柜。 |
| `Csomagod (vissza)kézbesítve lett, a címzett/feladó átvette.` | 已取走或送达签收。 |
| `Csomagod nem került átvételre a tárolási idő alatt, ezért a küldeményt visszaküldjük a feladónak.` | 保管期内未取件，正在退回寄件人。 |
| `Csomagot a címzett nem vette át, a csomag vissza lett küldve a feladónak.` | 未领取并已退回寄件人。 |

查看状态时请看完整句子而非短标题：因为 “Automatában”（在快递柜中）既可能指寄件人刚投递，也可能指包裹已到达你的取件柜。句中的 “(vissza)” 与 “címzett/feladó” 是因为同一套模板同时用于退货件，所以“收件人/寄件人已签收”在收件场景下即表示已取件。

## Foxpost 快递柜、自提与派送未成功

**取件方式。** 在 A-BOX 快递柜屏幕上输入消息里的取件码即可开柜；在 Z-BOX（按键柜）输入 6 位开柜码。持有取件码的任何人均可代开柜门。在人工值守的 Z-Pont 自提点报出口令即可领取，也可请人代取，除非商家特别要求否则无需出示身份证件。

**保管期限。** Foxpost 快递柜保管 **5 个自然日**（含周末与公众假期），精确到分钟的截止时间会写在通知消息中，且无法申请延期（仅在柜机故障时自动顺延）。Z-Pont 自提点按 Foxpost 条款保管 7 天、10 月 1 日至次年 1 月 20 日旺季保管 5 天（官网取件页写为全年 7 天，请一律以通知消息中的截止日期为准），可致电客服申请延长。逾期未领的包裹将退回寄件人；如为网购订单，请联系商家。

**货到付款。** A-BOX 柜机终端仅支持刷卡；Z-BOX 需通过 Foxpost 邮件中的链接或在 Foxpost 应用内在线付款，付款后才会发放开柜码；Z-Pont 自提点支持现金或刷卡。

**快递柜满格或故障。** Foxpost 可能将包裹改投至附近其他快递柜或 Z-Pont，你可以在通知中选择接受或拒绝。若输入取件码后柜门打不开，请重新操作一次，再致电客服。

**DPD 包裹。** 自 2026 年 10 月起，网店结账时可为 DPD 承运的包裹选择投递至 Foxpost 快递柜。DPD 交接且包裹到达 Foxpost 仓库后，Foxpost 会向你发出通知。你可以用 DPD 14 位单号在 DPD 官网查询，或用 `CLFOX` 单号在 Foxpost 查询。保管期同样为 5 个自然日，逾期退回 DPD。

**送货上门。** 由合作快递员在连续两个工作日尝试派送两次，并提前通过短信和邮件通知。邮件链接可更改日期或地址，但会消耗一次派送机会。派送未成功会发邮件和短信通知，不留纸质条。若仍无人签收，包裹退回寄件人。

## Foxpost 包裹延误、破损或遗失

**无更新。** Foxpost 不提供时效承诺：柜到柜最快 1 个工作日，送货上门通常 2 至 3 个工作日。状态连续 **3 天**无变化后，可在 Foxpost 联系表单提交包裹查单（`Csomag utáni érdeklődés`）；Foxpost 仅在满 3 天后受理查件，并在 30 天内给出结果。另见[物流停止更新的原因](guide:tracking-not-updating)（英文）。

**破损。** 外观可见破损请于收货当场注明；否则须在收货后 **3 个工作日**内书面申报，拍下外包装、内部填充及商品照片，通过 Foxpost 理赔表单提交。

**遗失。** 寄出满 **15 天**仍未送达的国内包裹须由寄件人发起投诉。理赔原则上由寄件人提出，除非包裹已交付给你、寄件人书面转让索赔权，或你能提供已全额付款的订单确认凭证。遗失或破损书面索赔时限为自寄出第 15 天起 **6 个月**内。若对 Foxpost 答复不满，可在 30 天内向匈牙利通信管理局（NMHH）或消费者调解委员会申诉。

## Foxpost 客服与联系方式

Foxpost 联系页面公布如下：

- **电话：** `06-1-999-0-369`，匈牙利时间周一、周二、周三、周五 08:00–18:00，周四 08:00–20:00（按国内标准市话计费）。
- **现场受理：** 1097 Budapest, Könyves Kálmán krt. 12-14, green staircase, 2nd floor，仅限周四 08:00–20:00（匈牙利时间）。
- **书面提交：** [在线联系表单](https://foxpost.hu/kapcsolatfelveteli-urlapok?pageType=c2c)（含查件申请 `Csomag utáni érdeklődés`）与[理赔表单](https://foxpost.hu/reklamacio?pageType=c2c)。Foxpost 未公布客服电子邮箱。如为 Packeta 包裹，请联系 Packeta 客服。

> Foxpost 仅使用 `foxpost.hu` 域名发信。任何非 `foxpost.hu` 结尾的链接均为诈骗；Foxpost 绝不会索要网银登录信息，也不会致电要求你“确认派送”——遇到此类来电请直接挂断。

## 使用 Peek 跟踪 Foxpost 包裹

对于 `CLFOX` 单号，[Peek](/) 会直接识别为 Foxpost。对于以 `66639` 开头的上门派送单号（10 至 20 位数字），Peek 会优先查询 Foxpost，同时询问相同长度的其他承运商，一旦 Foxpost 查到该单号便归属 Foxpost；若暂未查到，Peek 会显示“可能是 Foxpost”并继续查询。你也可以点击“更换”手动选择“Foxpost”，或粘贴带 `code=` 的 foxpost.hu 查询链接（`/csomagkovetes/` 或 `/en/parcel-tracking`），无论单号格式如何都会直接按 Foxpost 跟踪。以 `Z` 开头的单号会交给 Packeta。

Peek 展示 Foxpost 自身轨迹，时间按布达佩斯时间解析。无论你在 Peek 使用何种语言，每条详细扫描仍保留 Foxpost 匈牙利语原文，而包裹整体阶段会以你的界面语言显示（例如“待取件”）。Peek 还能显示寄件人（通常为网店）及包裹前往或存放的快递柜，也可以在状态变化时通知你。Peek 不显示预计送达日期或重量，订单号与付款信息也会被过滤。

## Foxpost 物流查询常见问题

### Foxpost 和 Packeta 是同一家公司吗？

在匈牙利现已合并为同一家公司：Packeta Hungary 于 2026 年 1 月 1 日并入 FoxPost Kft.，官网现称“FOXPOST – Packeta Group”。

### 波兰有 Foxpost 吗？

没有。Foxpost 仅在匈牙利境内派送，其常见问题也注明暂不支持从 Foxpost 跨国发往海外 Packeta 网点。

### 可以在 Foxpost 快递柜用现金货到付款吗？

快递柜不能收现金。A-BOX 快递柜终端仅支持刷卡，Z-BOX 须点击 Foxpost 邮件中的链接在线支付；仅 Z-Pont 人工自提点或送货上门的快递员接受现金。

### Foxpost 派送需要多长时间？

Foxpost 表示柜到柜揽收后最快 1 个工作日送达，送货上门通常 2 至 3 个工作日，但两者均不作时效保证。

:::sources
- [Foxpost: Csomagkövetés](https://foxpost.hu/csomagkovetes/) – 单号格式、转到 Packeta 的 Z 单号、状态、查件页面的字段
- [Foxpost: Track your parcel](https://foxpost.hu/en/parcel-tracking) – 英文版查件页面（通过菜单切换）
- [Foxpost: Home](https://foxpost.hu/) – 快递柜与送货上门、单号格式、Packeta Group
- [Foxpost: Gyakran ismételt kérdések](https://foxpost.hu/ugyfelszolgalat) – 单号在哪里、应用、合并、不能寄往国外、派送时间、货到付款、快递柜满格、DPD 包裹
- [Foxpost: Csomagátvétel](https://foxpost.hu/csomagatvetel) – 取件码、5 天保管期、Z-Pont、货到付款、送货上门的派送尝试
- [Foxpost: ÁSZF](https://www.foxpost.hu/uploads/documents/hu/foxpost_aszf.pdf) – 保管期、替代快递柜、索赔、时限、投诉、NMHH
- [Foxpost: Kapcsolat](https://foxpost.hu/kapcsolat) – 电话、服务时间、办公室
- [Foxpost: Contact](https://foxpost.hu/en/contact) – 英文版的电话、服务时间与办公室
- [Foxpost: Kárigény](https://foxpost.hu/reklamacio?pageType=c2c) – 索赔表单、Foxpost 或 Packeta
- [Foxpost: Kapcsolatfelvételi űrlapok](https://foxpost.hu/kapcsolatfelveteli-urlapok?pageType=c2c) – 联系表单、状态 3 天无变化后的包裹查询
- [Foxpost: WebAPI integráció](https://foxpost.hu/uzleti-partnereknek/integracios-segedlet/webapi-integracio) – 状态标题与说明句
- [Foxpost: Adathalász csalások és visszaélések formái](https://foxpost.hu/adathalaszat) – 诈骗提醒
- [Foxpost: Minden, ami fontos az átirányításról](https://foxpost.hu/blog/maganszemely/minden-ami-fontos-az-atiranyitasrol) – 改投通知与选择
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Peek 读取的内容
:::
