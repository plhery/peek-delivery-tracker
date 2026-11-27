---
title: Aras Kargo 土耳其快递追踪：13 位单号与土耳其语状态含义
description: 追踪土耳其 Aras Kargo 包裹：60 与 65 开头 13 位单号格式、TESLİMAT ŞUBESİNDE 等土耳其语状态中文详解及 Peek 追踪说明。
slug: aras-kargo-tracking
published: 2026-11-26
updated: 2026-11-26
---

Aras Kargo（亦写作 Aras Cargo）是土耳其本土快递公司。Peek 读取的 Aras 追踪页全部以土耳其语呈现。追踪单号由商家或发件人提供，你可将其粘贴到上方输入框中。Aras Kargo 自有追踪门户位于 kargotakip.araskargo.com.tr。下表为你逐条解释土耳其语状态的中文含义。

## Aras Kargo 追踪单号长什么样

Peek 代码将 Aras Kargo 追踪单号识别为 13 位纯数字，并将 `60` 或 `65` 开头的 13 位数字与 Aras 关联。以下单号数字均为虚构：

| 单号（虚构） | 说明 |
| --- | --- |
| `6048291736542` | 以 `60` 开头的 13 位数字：Peek 关联至 Aras Kargo 的格式。[Delhivery](carrier:delhivery)、Correos Chile、Yunda 及 Korea Post 也存在同格式单号。 |
| `6548291736542` | 以 `65` 开头的 13 位数字：同上。 |
| `1482917365482` | 以其他数字开头的 13 位数字：Peek 不会将其关联至 Aras。若商家写明 Aras Kargo，请自行选择 Aras Kargo（详见下文）。 |
| `604829173654` | 仅有 12 位数字。若你为它选择 Aras Kargo，Peek 会提示 Aras Kargo“不使用这种格式的单号”，请检查是否漏掉一位数字。 |

粘贴单号时包含空格不影响识别。粘贴包含单号的 Aras 追踪链接（kargotakip.araskargo.com.tr/mainpage.aspx 且带 `code=`）同样有效。

**在哪里找到单号。** 请查看商家的发货通知邮件或订单详情页。若找不到单号，请参阅[如何找到你的追踪单号](guide:find-tracking-number)（英文）和[单号格式指南](guide:tracking-number-formats)（英文）。

## Aras Kargo 土耳其语状态中文含义

Aras 追踪页会显示包裹当前状态（`Son Durum`）、计划送达日期以及车辆转运记录（`Araç Hareketleri`）。每条记录包含日期时间（`İşlem Tarihi`）、省份（`İl`）、单位名称（`Birim`，即网点或中转中心）以及操作说明（`İşlem Açıklaması`）。如有发生，页面还会列出未能派送的日期及转寄至其他网点的记录。

**当前状态（位于页面顶部）：**

| Aras 显示状态 | 对你的含义 | Peek 显示阶段 |
| --- | --- | --- |
| `ÇIKIŞ ŞUBESİNDE` | 位于最初收寄该包裹的始发网点。 | “已揽收” |
| `YOLDA` | 正在 Aras 各中转中心之间运输途中。 | “运输中” |
| `TESLİMAT ŞUBESİNDE` | 已抵达负责向你派送的目的地网点；派送未成功后状态也会保持在此。 | “运输中” |
| `TESLİM EDİLDİ` | 已送达，Aras 会显示送达日期与时间。 | “已签收” |

**车辆转运扫描（装车、卸车或装袋记录）：**

| Aras 显示状态 | 对你的含义 | Peek 显示阶段 |
| --- | --- | --- |
| `ŞUBEDE ARAÇ YÜKLEME` | 在网点装上运输车辆。 | “运输中” |
| `ŞUBEDE ARAÇ İNDİRME` | 在网点从车辆卸下。 | “运输中” |
| `AKTARMADA HAT ARACI YÜKLEME` | 在中转中心装上长途干线车。 | “运输中” |
| `AKTARMADA HAT ARACI İNDİRME` | 在中转中心从长途干线车卸下。 | “运输中” |
| `AKTARMADA SUBE ARACI YUKLEME` | 在中转中心装上网点派送车。 | “运输中” |
| `AKTARMADA SUBE ARACI INDIRME` | 在中转中心从网点车辆卸下。 | “运输中” |
| `AKTARMADA TORBA DOLDURMA` | 在中转中心与其他包裹一同装袋集运。 | “运输中” |

上述带 `SUBE` 的两条记录在追踪页原文中即不带土耳其语特殊字母。

**未能派送原因（`Teslim Edilememe Nedeni`），每日一条：**

| Aras 显示状态 | 对你的含义 | Peek 显示阶段 |
| --- | --- | --- |
| `TESLİMAT GÜN İÇERİSİNDE YAPILAMADI` | 包裹当日未能完成派送。 | “派送未成功” |
| `TATİL DOLAYISIYLA TESLİMAT YAPILAMADI` | 因节假日未能派送。 | “派送未成功” |
| `UĞRAMA NOTU BIRAKILDI (1-2)` | 派送员曾上门并留下了到访通知单。 | “派送未成功” |
| `ALICI ADRESİ DAĞITIM ALANI DIŞINDA` | 你的收货地址位于该网点派送范围之外。 | “派送未成功” |

**网点转寄。** `YÖNLENDİRİLDİ` 表示包裹已转寄至另一网点。Aras 会附带原因，Peek 会将原因加在括号中并显示为“运输中”。

上述两类记录仅含日期、不含具体时间。Aras 追踪页在未成功派送的记录旁有时还会附带派送员备注，Peek 会省略该备注。更多阶段说明详见[追踪状态含义指南](guide:tracking-statuses)（英文）。

## Aras Kargo 土耳其境内派送流程

包裹从收寄的 Aras 始发网点出发，经由各中转中心（含 `AKTARMADA` 的记录），最终运抵目的地派送网点（`TESLİMAT ŞUBESİNDE`），由该网点派送至你的地址。

**计划送达日。** Aras 追踪页会给出计划送达日期，包裹在途期间 Peek 会将其显示为预计送达日期；派送未成功后，或该日期已过，Peek 会不再显示该日期。

**超出派送区域。** 若出现 `ALICI ADRESİ DAĞITIM ALANI DIŞINDA`，说明该网点不覆盖你的收货地址，请联系商家确认后续安排。

**本页未涵盖的内容。** 由于 Peek 无法读取 Aras Kargo 主站页面，本页未列出派送时效、尝试次数、寄存天数或自提规则；Peek 读取到的 Aras 追踪记录也不包含自提点名称。包裹未能派送时，请参考[派送未成功](guide:missed-delivery)（英文）和[未领取包裹指南](guide:uncollected-parcel)（英文）。

## Aras Kargo 无更新、丢失或退回怎么办

**查无结果。** 当 Aras 尚未查到该单号时，追踪页会提示 `Girdiğiniz bilgiler için bir sonuç bulunamamıştır.`（未找到你输入信息的结果），可能是商家尚未交寄。归入 Aras Kargo 后 Peek 会显示“等待 Aras Kargo 更新”并持续查询。请与商家核对 13 位单号；如数日仍无记录，请向商家确认是否已实际交给 Aras。

**停留在 `TESLİMAT ŞUBESİNDE`。** 说明目的地派送网点已收到包裹，且在派送未成功后顶部状态仍保持不变。请检查是否列出了某日的未派送原因（见上表）；若无，说明包裹正在网点等待派送。由于 Aras 追踪不提供单独的“派送中”步骤，包裹在签收或派送未成功前都会显示为“运输中”。

**多日无更新。** 当在途包裹连续四天没有新扫描时，Peek 会用该次扫描的日期加以标记（“自10月12日周一起没有物流更新”），并继续向 Aras 查询。周末期间包裹常会暂时没有动静。若三周仍无新扫描，Peek 会显示“已超过三周没有新动态。如果还没收到，请联系卖家。”详见[为何物流停止更新](guide:tracking-not-updating)（英文）。

**显示已签收但没收到。** 请先询问邻居及同住人员。Aras 追踪页会显示签收人姓名，Peek 不显示该姓名，请在 Aras 追踪页上查看，然后联系商家和 Aras。详见[显示已签收但未收到包裹](guide:delivered-not-received)（英文）。

**丢失或退回。** 运输合同由商家与 Aras 签订，请先联系商家申请退款或补发。另见[包裹丢失处理指南](guide:lost-parcel)（英文）与[包裹退回发件人](guide:returned-to-sender)（英文）。

## Aras Kargo 客服与联系方式

由于 Peek 无法读取 Aras Kargo 的网站，本页不列出客服电话或邮箱：Peek 只提供在快递公司自己页面上读到的联系方式。

网购订单请优先联系商家。在 Peek 中，每条转运记录的地点都会写明对应网点或中转中心，送达记录的地点即为负责派送的网点，咨询时可提供该网点名称。

## 使用 Peek 追踪 Aras Kargo 包裹

[Peek](/) 无法仅凭 13 位长度确定属于 Aras Kargo（多家承运商都用 13 位），但你仍可直接粘贴：Peek 跟踪 4,500 多家快递公司。

- **`60` 或 `65` 开头的 13 位数字：** Peek 会立即将 Aras Kargo 与其他可查询该号段的承运商一并查询（如部分 `60` 开头会同时询问 Ukrposhta，部分 `65` 开头会同时询问 FedEx Poland），期间显示“正在查询物流服务…”。若仅 Aras 查到记录，Peek 会显示“Aras Kargo 有这个包裹的记录。”并将包裹归入 Aras Kargo；若多家承运商均有记录，则会让你选择。
- **其他 13 位数字或暂未查到：** 请点击“更换”并选择 Aras Kargo（输入 Aras 即可），Peek 随即会直接询问 Aras 追踪器；已添加的包裹请使用“更换快递公司”。
- **Aras 追踪链接：** Peek 会从链接中提取单号，并立即识别为 Aras Kargo（“识别出的快递公司”）。

未指定承运商添加的包裹会显示“暂无物流记录”，期间 Peek 持续查询各承运商和追踪服务；若某追踪服务唯一指明 Aras Kargo 且带有日期步骤，Peek 会将其归入 Aras。

**Peek 展示的内容。** 当前状态、每条车辆转运记录的时间与地点（网点或中转中心及所属省份）、未派送日期与原因、转寄记录、计划送达日期，以及带网点名称的签收时间。收件人姓名按 Aras 返回的脱敏格式显示；发件人姓名、签收人、派送码、付款与发票信息、体积与件数、进度条以及备注消息均会被隐藏。所有时间均按土耳其时间解析。点击包裹页面上的“打开 Aras Kargo 官网”可直接跳转查询。

若 Aras 追踪器暂时无法连接，Peek 会改查追踪服务并以对方原文显示历史记录。

当你在 Peek 中打开包裹页面，或在派送当日开启通知时，Peek 最多每 10 分钟检查一次 Aras；其余情况下，以及连续两天没有新动态后（包裹仍显示“已生成面单”或“已揽收”时为 12 小时），每小时检查一次。Peek 可以按“每次扫描”、“重要环节”或“仅派送当天”通知你。Peek 无法代你联系 Aras Kargo 或商家。

## 关于 Aras Kargo 物流查询的问题

### Aras Kargo 追踪里的 `TESLİMAT ŞUBESİNDE` 是什么意思？

意为“在目的地派送网点”：负责将包裹送到你地址的 Aras 网点已经收到包裹。派送未成功后该顶部状态仍保持不变，请向下查看是否带有未派送原因行（Peek 会将其标为“派送未成功”）。

### Aras Kargo 里的 `YOLDA` 是什么意思？

意为“在途”：包裹正在 Aras 各中转中心之间运往目的地派送网点。Peek 显示为“运输中”。

### Aras Kargo 追踪单号长什么样？

按 Peek 的规则为 13 位纯数字。Peek 会将 `60` 或 `65` 开头的 13 位数字关联至 Aras Kargo（例如 `6048291736542`）；其他数字开头的 13 位单号请自行选择 Aras Kargo。

### 为什么 Aras Kargo 查不到我的追踪单号？

提示 `Girdiğiniz bilgiler için bir sonuç bulunamamıştır.` 表示 Aras 目前尚未查到该单号，可能是商家尚未交寄包裹。请核对 13 位数字，等待一天后再向商家确认。

### 如何联系 Aras Kargo？

请先联系为你预订运输的商家。由于 Peek 无法读取 Aras Kargo 的网站，本页未列出电话号码或电子邮箱。

:::sources
- [Universal Parcel Scraper: Aras Kargo](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/aras-kargo) – 名称、13 位及 60/65 开头单号规则、追踪门户与链接、各栏目与土耳其语状态阶段映射、未查到提示、计划送达日、土耳其时区及过滤字段
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – 共享 13 位格式的其他承运商、查询顺序及追踪服务兜底逻辑
:::
