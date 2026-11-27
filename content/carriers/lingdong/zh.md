---
title: 领东国际（Lingdong）物流查询：SZLD单号与状态
description: 查询深圳领东国际（Lingdong International）包裹：SZLD单号格式、中英文航班清关轨迹、移交尾程停更原因及预设节点说明。
slug: lingdong-guoji-wuliu-chaxun
published: 2026-11-23
updated: 2026-11-23
---

领东国际（Lingdong International，全称深圳市领东国际货运代理有限公司）是一家位于中国深圳宝安区的货运代理公司，为网店卖家把包裹运往海外。如果卖家通过领东国际发货，单号以 `SZLD` 开头——你可以直接粘贴到上方查询框，或在领东国际网站 `lingdong56.com` 的「快件查询」框里查询。单号由发货网店随订单物流详情提供。

## 领东国际单号格式：`SZLD` 加 10 位数字

领东国际网站未发布固定单号说明，其查询系统实际应答的运单号为 `SZLD` 加 10 位数字，共 14 位字符（以下示例数字均为虚构）：

| 单号（虚构） | 格式说明 |
| --- | --- |
| `SZLD4829105736` | `SZLD` 加 10 位数字：标准领东国际运单号。 |
| `SZLD482910573` | 只有 9 位数字：不符合领东格式，请检查是否漏掉了一位数字。 |
| `LX482910576CN` | 2 位字母 + 9 位数字 + `CN`：[中国邮政](carrier:china-post) 单号，不是领东自有单号。 |

在 Peek 中粘贴时，字母大小写和空格都不会影响识别。领东网站面向发货卖家，除首页查件框外没有针对收件人的帮助页；找不到单号请参阅 [如何找到你的快递单号](guide:find-tracking-number)（英文）。领东查件系统同样支持用卖家的自定义参考号查询（显示在「参考号」一栏）；此外领东也代理销售中国邮政、PostNL、UPS、DHL 与 [联邦快递（FedEx）](carrier:fedex) 渠道，属于那些公司的号段请参考 [快递单号格式指南](guide:tracking-number-formats)（英文）。

## 领东国际物流状态是什么意思

领东国际没有对外发布状态对照表，查件系统里的扫描为英文，或中文后附英文。按包裹常规流转顺序：

| 领东国际原文 | 对你的实际含义 |
| --- | --- |
| `货物电子信息已经收到` | 由查询平台在尚无实物扫描时自动生成的建单记录（非实扫），表示卖家已下单但领东尚未收货。 |
| `Shipping Label Created` | 已创建运单面单，比深圳仓库首扫早几个小时。 |
| `Shipment arrived at facility` | 包裹到达领东深圳仓库，这是领东实收的第一条扫描。 |
| `Shipment departed from facility` | 已离开深圳仓库发往海外。 |
| `预计…航班/Flight scheduled for …`、`预计…号航班【Expect flight ….】`、`预计…航班/Scheduled flight on …` | 已订舱排上指定日期的航班。 |
| `送往航司途中【On the way to the airline company】` | 正在发往航空公司货站途中。 |
| `航班已起飞【The flight has taken off.】`、`航班已启运/Package in transit to destination country` | 国际航班已起飞。 |
| `航班抵达【Flight arrival】`、`航班已落地，拆板中/Package arrived at destination country` | 航班已降落在目的国（后一句指机场正在拆板理货）。 |
| `拆板作业中，等待取货通知【Under destuffing, waiting for pickup instructions.】` | 落地拆板并等待清关代理提货（不需要收件人去机场自提）。 |
| `货物在清关【Customs clearance in progress】` | 正在目的国办理进口清关。 |
| `已完成国内报关手续【Domestic customs clearance procedures have been completed.】`、`货物已清关完成/Import customs clearance complete`、`货物已完成清关【The goods have completed customs clearance】` | 目的国进口清关已放行（落地后出现的「国内」指目的国境内而非中国）。 |
| `货物已交服务商【The shipment has been transferred to the logistics partner.】`、`包裹交接给本地物流商处理/Received by local delivery company` | 清关放行后已移交给目的国尾程快递商。 |

在 Peek 中，前两条归为「已生成面单」，`Shipment arrived at facility` 归为「已揽收」，`货物在清关【Customs clearance in progress】` 归为「清关中」，其余均归为「运输中」。

领东自有轨迹本身不包含最终派送签收节点，最后一条是在目的国清关后移交当地物流商。但发往意大利的包裹，领东查件系统可能会转发意大利邮政（Poste Italiane）的扫描，如 `First parcel scan along Poste Italiane’s logistic network`、`In transit along Poste Italiane’s logistic network`、`In transito, la spedizione è in transito`，直到 `In consegna`（「派送中」）与 `Consegnata, la spedizione è stata consegnata`（「已签收」）；在美国可能会转发 GOFO 的 `Arrived at GOFO Regional Hub`（「运输中」）。

## 领东国际包裹在各国由谁派送与欧盟税费

领东网站主营业务列明提供发往美国、英国、欧盟、澳大利亚、墨西哥、加拿大和日本的专线小包、中国邮政 e 邮宝与大包（通达 200 多个国家和地区）、荷兰邮政 PostNL、轻抛专线以及 UPS/DHL/FedEx 代理服务，但未按国家写明每条专线的固定尾程快递商，也未发布收件人派送次数、通知卡或保管期规则——移交尾程后全部遵循当地快递规定。

根据 [欧盟委员会海关改革说明](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en)，发往欧盟的境外网购包裹自 2026 年 7 月 1 日起对价值 150 欧元以内包裹按每类商品征收 3 欧元临时关税，自 2026 年 11 月 1 日起无论价值高低均另按每类商品收 2 欧元欧盟统一处理费（原有 150 欧元以内 IOSS 增值税规则不变，结账已由商家代收 IOSS 增值税的无需入境重复缴增值税）。英、爱、美、加、澳清关规则见 [包裹海关扣留与清关指南](guide:customs)（英文）。

## 领东国际包裹停更、查不到或丢件怎么办

- **停在 `Shipment departed from facility` 或 `货物已交服务商`。** 领东自有轨迹不包含尾程派送，出仓或移交当地服务商后原页面可能一直不再更新。请检查领东查件页有没有「渠道转单号」一栏（那就是当地尾程单号，可单独添加到 Peek）；如果没有，请直接问卖家索取尾程快递商名称与单号。最后一条记录满四天后，即使派送公司仍在运送，Peek 也会在包裹上标出该记录的日期，如「自10月12日周一起没有物流更新」。参见 [物流为什么停止更新](guide:tracking-not-updating)（英文）和 [中国直邮包裹跟踪指南](guide:tracking-from-china)（英文）。
- **只有中文行 `货物电子信息已经收到`，或查无记录。** 仅有中文行说明卖家建单尚未入仓；面对未知单号，领东查件系统只显示单号、其余栏目全空，Peek 会视为领东已建单但暂无扫描，继续查询并显示「等待 Lingdong International 更新」，不会提示你检查单号，请自行逐位核对 14 位 `SZLD` 单号并与卖家确认。
- **丢失、破损或退件。** 领东网站「运输条款」页面为空，且发货合同由卖家与领东签订。遇到问题请直接找卖家或购物平台申请退款或补发。参见 [包裹丢失怎么办](guide:lost-parcel)（英文）、[包裹退回寄件人](guide:returned-to-sender)（英文）和 [显示已签收但未收到](guide:delivered-not-received)（英文）。

## 领东国际客服与联系方式

领东中文网站 `lingdong56.com` 公开的联络方式（仅中文、面向卖家）：

- **手机电话**：其网站顶部与「联系我们」列有两个中国大陆手机号（未标注服务时间与支持语种）。
- **QQ 客服**：查价、大货与异常理赔聊天按钮均跳转中文 QQ。
- **公司地址**：深圳市宝安区福海街道展城社区福园二路 10 号濠和智创 B 栋 625（无公开邮箱及海外办公室）。

## 用 Peek 跟踪领东国际包裹

粘贴 14 位 `SZLD` 单号（如虚构单号 `SZLD4829105736`）或含 `documentCode=` 的领东查件链接时，[Peek](/) 会立刻识别出领东国际（显示「识别出的快递公司」），Peek 目录中没有其他快递公司使用这种格式；如需手动切换，点击添加页的「更换」或详情页的「更换快递公司」即可。

Peek 直接读取领东后台，展示标准阶段（「已生成面单」「已揽收」「运输中」「清关中」「派送中」「已签收」等）、扫描地点与目的国，中文后附英文的行只显示英文部分，纯中文行与意大利邮政原文照原样显示，隐去收件人姓名，并按北京时间解析换算为你设备的本地时区（领东不提供预计送达日期）。

与同平台其他货代一样，Peek 会识别并拦截晚于当前时刻的未来节点、提前超过 12 小时写入（或同秒内提前超 6 小时写入）以及通过系统「轨迹定制」功能提前编排的预设节点——这些是预测，不是扫描：在包裹签收或退回之前，轨迹开头会显示「其他网站可能显示更多步骤，但那些只是预测。」，点「查看」可以看到这些灰色记录，每条附「预计：」和日期。这类记录不算扫描，不会改变包裹阶段，也不会触发提醒。除非发往瑞士/列支敦士登且记录明确标明瑞士邮政（Swiss Post）及有效单号时可自动接力，其余国家的尾程单号请向卖家索取后作为新包裹添加到 Peek。

你打开这个包裹时，或在派送当天开启了通知时，只要领东或瑞士邮政自己的查件系统有回应，Peek 最快每 10 分钟查询一次；包裹显示「派送中」的当天，从当地时间早上 6 点起最快每 2 分钟一次。领东查件系统中只有意大利邮政的 `In consegna` 会带来「派送中」，接力后的瑞士邮政扫描也可能带来。其余时间每小时查询一次。连续两天没有新动态（包裹仍为「已生成面单」或「已揽收」时为 12 小时）后，也是每小时一次。只能通过第三方查询服务时，Peek 每小时查询一次，一整天没有新扫描后改为每 3 小时一次；派送中当天，你打开包裹或开启通知时最快每 15 分钟一次。

没有新动态满四天时，Peek 会在包裹上标出「自10月12日周一起没有物流更新」这样的提示，包裹页面上还会说明：「包裹在长途航班上、在海关或遇到周末时，常常暂时没有动静。Peek 会持续向 Lingdong International 查询。」超过三周没有新动态时则改为：「已超过三周没有新动态。如果还没收到，请联系卖家。Peek 会持续向 Lingdong International 查询。」

通知偏好可选「每次扫描」「重要环节」或「仅派送当天」。Peek 无法替你联系领东国际或卖家。

## 关于领东国际物流查询的常见问题

### 领东国际（Lingdong International）是什么公司？

全称深圳市领东国际货运代理有限公司，位于深圳宝安区，其网站为 `lingdong56.com`，为中国跨境电商卖家承运欧美澳加日墨小包专线和国际快递代理。

### 为什么领东国际轨迹停在 `Shipment departed from facility` 不动了？

这是包裹离开领东深圳仓库的扫描；领东自有轨迹通常只更新到离仓或交接尾程，不包含目的国上门派送。请找卖家确认当地尾程快递公司与单号。

### 领东国际一般需要多久送到？

领东国际网站未公布收件人参考时效，请以网店下单页给出的预计送达时间为准。

### 在哪里查询 `SZLD` 单号？

可直接粘贴到上方 Peek 查询框（Peek 会直接识别 `SZLD` 单号），或在 `lingdong56.com` 首页的「快件查询」框查询。

### 领东国际包裹丢了该联系谁？

联系下单的卖家或购物平台：领东仅与发货卖家签约，其网站手机与 QQ 客服均面向中国发货商家。

:::sources
- [European Commission：欧盟海关改革（英文）](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 自 2026 年 7 月 1 日起 150 欧元以内 3 欧元关税、自 2026 年 11 月 1 日起每类商品 2 欧元欧盟统一处理费
- [欧盟委员会：Guidance on the €3 customs duty and the Union handling fee（PDF，英文）](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 欧元处理费按每类商品收取，无论价值多少；150 欧元以内包裹的增值税规则不变，IOSS 增值税在付款时收取
- [领东国际：首页](http://www.lingdong56.com/) – 快件查询框、面向卖家的服务、手机号与 QQ 客服按钮
- [领东国际：公司介绍](http://www.lingdong56.com/gsjs.html) – 货运代理、小包、快递与空运
- [领东国际：主营业务](http://www.lingdong56.com/zyyw.html) – 各国专线小包、E邮宝、荷兰邮政、邮政大包、抛货专线，邮政包裹通达 200 多个国家和地区
- [领东国际：快递业务](http://www.lingdong56.com/kdyw.html) – UPS、DHL、FedEx 代理
- [领东国际：联系我们](http://www.lingdong56.com/lxwm.html) – 宝安区地址、手机号
- [领东国际：运输条款](http://www.lingdong56.com/ystk.html) – 页面没有条款内容
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – SZLD 14 位单号与 `documentCode=` 链接、中英文/意大利邮政/GOFO 轨迹映射、「轨迹定制」预设节点拦截与瑞士邮政自动接力规则
:::
