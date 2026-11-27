---
title: 一龙物流（Yilong Logistics）查询：YIL 单号与全程状态解析
description: 查询中国莆田一龙物流（Yilong Logistics）跨境包裹：了解 YIL…WL 单号格式、华磊系统与末端派送状态、预报节点识别及联系渠道。
slug: yilong-logistics-tracking
published: 2026-11-27
updated: 2026-11-27
---

一龙物流（Yilong Logistics）是一家位于中国福建省莆田市的货运代理公司，其运单号以 `YIL` 开头、以 `WL` 结尾。你可以在 yxhlexpress.com 的“包裹追踪”输入框查询，或直接把单号粘贴到上方输入框。一龙物流的轨迹涵盖从中国仓库到航空运输，并可能延续到目的国清关以及当地末端派送公司的更新记录。

## 一龙物流单号是什么样子

一龙物流官网未公布单号规范，其追踪系统返回的运单格式为 `YIL`、9 位数字加 `WL`。以下三条均为虚构示例：

| 单号 | 说明 |
| --- | --- |
| `YIL482917365WL` | `YIL`、9 位数字加 `WL`：一龙物流标准运单号。 |
| `YIL48291736WL` | 只有 8 位数字而非 9 位：可能漏输了一位数字。 |
| `RR482917367CN` | 2 位字母、9 位数字加 `CN`：[中国邮政（China Post）](carrier:china-post) 单号，不是一龙物流单号。 |

在 Peek 中粘贴时，小写字母和空格均无影响。如果卖家尚未提供单号，请参阅 [如何查找追踪单号](guide:find-tracking-number)（英文）。

**一龙物流官网查询框。** 官网要求每行输入一个“订单号或追踪号”，结果页一次最多可查 30 个。由于查询以表单方式提交，结果页没有独立的分享网址，无法保存或转发官网追踪链接。

## 一龙物流状态含义解析

除其华磊（Hualei）追踪平台自动写入的一条中文提示外，一龙物流其余扫描节点均为英文，按常见顺序如下：

| 一龙物流原文 | 对你的含义 |
| --- | --- |
| `货物电子信息已经收到` | 意为“已收到电子运单信息”：订单已生成，但一龙物流尚未扫描到实物包裹。 |
| `Package received and scanned at warehouse` | 一龙仓库已收到并扫描包裹（附仓库地点），是进入一龙物流后的首条实物扫描。 |
| `Package departed from the warehouse` | 包裹已离开仓库（同样附地点）。 |
| `Customs export clearance completed` | 已通过中国出口清关，并非目的国进口清关。 |
| `Package handed over to airline` | 已交由航空公司准备装机。 |
| `Package in transit to destination country` | 航班正在飞往目的国途中。 |
| `Package arrived at destination country` | 包裹已抵达目的国。 |
| `Flight arrived at destination country port` | 航班已在目的国口岸落地。 |
| `Customs import clearance completed, awaiting pickup by local carrier` | 目的国进口清关已完成，正等待当地承运商提货。 |
| `Package picked up by last-mile carrier` | 当地末端承运商已提货；此后的记录来自该承运商并沿用其原文。 |

清关与航空节点不带地点，所有节点也均不带时区：时间戳均为北京时间，因此显示为 09:00 的记录按纽约时间实际上发生在前一天傍晚。

发往美国的包裹有时会在末端显示 SwiftX 的轨迹：`Arrived at Origin Hub`（抵达美国首个枢纽）、`Departed Destination Hub`、`Arrived At Warehouse`（派送出发仓库）、`Out for Delivery` 以及 `Delivery failed`（后附司机备注与 SwiftX 热线）。SwiftX 的末端面单节点 `Shipping Label Created` 会一并出现，但时间通常紧跟在中国仓库扫描之后。

## 谁负责派送一龙物流包裹

一龙物流的轨迹记录不列出末端派送公司名称，也不给出第二追踪单号，其官网也未说明各国由谁派送。不过在 `Package picked up by last-mile carrier` 之后，轨迹仍可能继续展示末端承运商的原始节点（如部分美国件的 SwiftX 记录）。派送尝试次数、自提点与保管期限均由末端公司规定，一龙物流未公布相关规则。请向卖家询问航班落地后由哪家公司接手及对应的单号。如果收到进口税费通知，请参阅 [包裹被海关扣留指南](guide:customs)（英文）。

## 一龙物流包裹未到货或轨迹停滞

**停在 `Package handed over to airline`。** 接下来是国际航班与目的国进口清关，短暂静默并不意味着包裹停摆。请参照商家给出的预计送达时间，逾期再联系卖家。航班后静默原因见 [追踪来自中国的包裹](guide:tracking-from-china)（英文） 和 [物流为何停止更新](guide:tracking-not-updating)（英文）。

**只有中文预报行。** 说明卖家已建单，但一龙物流尚未收到实物。若数日仍无仓库扫描，请向卖家确认是否确已寄出。

**查无此单。** 一龙追踪系统查不到单号时，结果只会在“参考号”下回显该编号。Peek 会显示“等待 Yilong Logistics 更新”，两天后仍无记录时会提示核对单号、更换承运商或删除包裹，请逐字对照卖家消息检查。

**丢失、破损或退回。** 一龙官网没有理赔表单和服务条款。卖家是一龙物流的签约客户，退款或补发需由卖家处理；若卖家失联，请在电商平台发起申诉。详见 [包裹丢失](guide:lost-parcel)（英文） 和 [退回寄件人](guide:returned-to-sender)（英文）。

## 一龙物流联系方式与客服

以下信息来自中文官网 yxhlexpress.com：

- **栏目页面：** “联系我们”、“客服中心”和“公司介绍”均只有标题，没有正文内容。
- **微信：** 页面侧边栏与页脚均有标为“官网微信二维码”的图片。
- **电话：** 侧边栏底部留有一个未标注说明的中国手机号，未注明归属部门、接听时间或语言。

网站没有电子邮箱、实体街道地址或面向收件人的说明；首页其余部分均面向发货客户，包含“寄件下单”登录注册与运费试算。订单问题请直接联系卖家。

## 使用 Peek 追踪一龙物流包裹

将 `YIL…WL` 单号粘贴到 [Peek](/) 会直接归入 Yilong Logistics，Peek 目录中没有其他承运商使用该格式。Peek 支持超过 4,500 家承运商，但卖家订单参考号没有固定形状：照常添加后点“更改”选择 Yilong Logistics（搜“一龙物流”或 “YXHL” 也能找到），已添加的包裹则点“更换承运商”。若一龙查到了该参考号，Peek 也会一并显示对应的 `YIL` 单号；但注意 Peek 会去掉连字符，含连字符的参考号请直接在一龙官网查询框查询。

Peek 直接读取一龙物流追踪接口，展示原文节点（含中文预报行）、两处仓库节点的地点以及目的国（如有）。仓库入库记为“承运商已揽收”；离仓直至末端派送商仓库（包含跟在仓库扫描后的 `Shipping Label Created`）记为“运输中”；`Out for Delivery` 记为“派送中”；`Delivery failed` 记为“派送未成功”。北京时间会自动换算为你设备的本地时区。姓名、地址、邮编、重量、费用、卖家参考号、签名和签收凭证均不会出现在 Peek 中；因一龙不提供预计送达日，界面也不显示预计日期。

按北京时间晚于 Peek 查询时刻的节点会被暂缓展示，直到该时刻到来。如果记录显示某节点的写入时间比其标注的扫描时间早超过 12 小时（或与这类节点在同一秒写入且提前超过 6 小时），Peek 会将其视为预报而非真实扫描并暂缓：在包裹真正妥投或退回前，Peek 会在历史上方提示一龙物流官网会预先列出整条路线（含送达）、这些步骤不是真实扫描，并将对应节点置灰附日期展示；若预写送达时间已到却仍无真实签收扫描，还会在状态下方提示如未收到货请联系卖家。由于一龙记录不披露末端公司名，Peek 只能跟到一龙轨迹为止；拿到卖家提供的末端单号后请另行添加。

当你打开包裹，或在开启通知的情况下进入派送日时，只要一龙官网正常响应，Peek 最高每 10 分钟检查一次，并于派送当天自北京时间早上 6 点起提高至最高每 2 分钟一次；其余情况及连续两天无新动态时（处于“已创建运单”或“承运商已揽收”时为 12 小时）改为每小时一次。一龙自有节点（含进口清关）均归为途中状态，只有开启“每次扫描”才会逐条提醒，“重要节点”和“仅派送和送达”要到末端公司的 `Out for Delivery` 才会触发。Peek 无法代替你催促一龙物流或卖家。

## 一龙物流追踪常见问题

### 一龙物流（Yilong Logistics）是什么公司？

全称为“莆田市城厢区一龙物流有限公司”，是一家位于福建莆田城厢区的货代公司；其官网 yxhlexpress.com 介绍主营国际出口，主打美国专线，运单格式为 `YIL` 加 9 位数字加 `WL`。

### 为什么一龙物流轨迹停在 “Package handed over to airline”？

因为接下来进入国际航班段，下一条记录通常是 `Package in transit to destination country`，之后依次是抵达目的国、进口清关以及末端派送公司的节点。一龙记录不写明末端公司名，请向卖家索取承运商名称及末端单号。

### 一龙物流一般需要多久送达？

一龙物流未公布时效标准。其官网查询框下方反复列出某脱敏单号配“5天”及 2022 年日期的列表对你的包裹并无参考意义，请以商家给出的预计送达时间为准。

:::sources
- [Yilong Logistics: 莆田市城厢区一龙物流有限公司 (home)](http://www.yxhlexpress.com/) – 公司全称与福建 ICP 备案（页脚）、主打美国专线的货代介绍、寄件下单登录注册与运费试算、“包裹追踪”输入框与文案、框下列表、微信二维码及侧边栏手机号
- [Yilong Logistics: 包裹追踪 (tracking results page)](http://www.yxhlexpress.com/track.aspx) – 多单号每行一个、一次最多 30 个
- [Yilong Logistics: 公司介绍 (company profile)](http://www.yxhlexpress.com/about.html) – 页面无正文内容
- [Yilong Logistics: 联系我们 (contact)](http://www.yxhlexpress.com/contact.html) – 页面无正文内容
- [Yilong Logistics: 客服中心 (customer service)](http://www.yxhlexpress.com/kfzx.html) – 页面无正文内容
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – 公司名称、华磊平台、YIL 单号格式、扫描措辞与地点、航班至末端及 SwiftX 节点、平台无扫描中文行、北京时间、查无此单返回、订单号查询、不列末端公司及 Peek 展示/暂缓/省略规则
:::
