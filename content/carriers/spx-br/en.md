---
title: SPX Express Brazil tracking: Shopee orders and statuses
description: Track an SPX Express parcel from a Shopee order in Brazil: where to follow it, what BR numbers and SPX's Portuguese statuses mean, Agência Shopee pickup and refunds.
slug: spx-express-brazil-tracking
published: 2026-10-26
updated: 2026-10-27
---

SPX Express (Shopee Xpress) is Shopee's own logistics network in Brazil. It collects orders from sellers and brings them to your door or to an Agência Shopee, a partner shop where you pick parcels up. Shopee calls the service Entrega Padrão (standard delivery), and says you track it in the Shopee app, where the order, its delivery date and any refund live. spx.com.br has a tracking box too, or you can paste the number in the box above.

## SPX Express tracking number: where to find it

In the Shopee app, go to "Eu" (Me) > "Minhas Compras" (My purchases), pick the order under "A Caminho" (On its way; some Shopee articles call it "A Receber", To receive), then tap "Status de Envio" (shipping status) for every update. Shopee adds the tracking number there automatically once it exists, along with the estimated delivery date.

These are the shapes Peek knows (digits invented):

| Shape | What it is |
| --- | --- |
| `BR0000000000000` | SPX Express: `BR`, 12 digits and a final digit. |
| `BR000000000000X` | SPX Express: `BR`, 12 digits and a final letter. |
| `EA000000005BR` | Two letters, nine digits and `BR`: a [Correios](carrier:correios-br) code, not SPX's. |

The order ID ("ID do pedido") is not a tracking number, but Shopee may ask for it when you contact its Customer Service.

Not every Shopee order goes with SPX. Shopee lists Entrega Padrão (Shopee Xpress), Entrega Correios, whose code also works on Correios' site, and Entrega do Vendedor, where the seller delivers and updates the status. For orders from abroad, Shopee names Correios, with a local tracking number in the app, or its partner Anjun Courier. Still, SPX's own tracking has lines for some parcels from abroad (below).

## What SPX's tracking statuses mean

SPX writes its tracking in Portuguese; some lines end with the place after a colon.

| SPX's line | What it means for you |
| --- | --- |
| `Pedido em preparação` | "Order being prepared": the seller is getting it ready. |
| `Pedido postado …` | "Order posted": the seller left it at a drop-off point (spx.com.br adds its town); SPX collects it from there. |
| `Seu pedido foi coletado` | "Your order was collected": SPX has it. |
| `Seu pedido chegou ao centro logístico: …` | It reached a logistics centre. |
| `Seu pedido saiu do centro logístico: …` | It left a logistics centre. |
| `O pedido está em separação no centro logístico, novas atualizações em até 1 dia.` | It's being sorted; SPX says to expect new updates within a day. |
| `Pedido em trânsito` | In transit. |
| `Pedido em rota de entrega para seu endereço, aguarde pelas próximas horas.` | Out for delivery to your address, in the next few hours. |
| `Pedido entregue` | Delivered. |
| `Pedido será devolvido ao vendedor` | It's going back to the seller. |
| `Pedido devolvido` | Returned to the seller. |

**Parcels from abroad.** Lines starting `[China]` are scans in China, such as `[China]O pedido foi liberado na alfândega de exportação` (it cleared export customs). Then come `Pedido chegou no Brasil` (it's in Brazil), `Pedido está na alfândega de importação` (at Brazilian import customs), `Pedido em inspeção na alfândega de importação` (being inspected) and `Import Customs Cleared`, in English: customs released it.

One line is bad news: `O pacote não pôde ser importado, será retornado ou confiscado pela alfândega`. Customs refused the import, and the parcel will be returned or seized. Ask Shopee: refunds for orders from abroad follow the usual steps (below).

## SPX delivery: attempts, the keyword and Agência Shopee

**Timing.** Neither you nor the seller can book a delivery date or time. Shopee gives 1 to 25 working days for orders from sellers in Brazil, counted from collection, and generally 30 to 50 calendar days for international orders. The order page shows your estimated date.

**Attempts.** Shopee's delivery partners make 3 attempts before the order goes back to the seller, and a failed delivery is refunded automatically. The address can only change before payment is confirmed, once. After that, Shopee's answer is to let the attempts run out and take the refund.

**The keyword (palavra-chave).** Some orders get a random password you tell the driver at the door; only someone who knows it can take the parcel. It's on the order page and in Shopee's email and notification. If you'll be out, give it to someone you trust, such as a neighbour or your building's porteiro (doorman). Never give it before the parcel is in your hands, and refuse a delivery made out to another name or address.

**Agência Shopee pickup ("Retire Fácil").** Where it's offered, choose it at checkout under "Opção de Envio" (shipping option) > "Ver mais" (see more) > "Retire Fácil" (easy pickup); you can't switch to home delivery later. When the parcel arrives, Shopee sends a PIN by WhatsApp, email or in the app, where the order page also shows the last day for pickup. Show the PIN and the tracking number at the counter; anyone with both can collect for you. You have 7 working days, then SPX takes the parcel back. If an agency is temporarily unavailable, Shopee may deliver to your default address instead. [SPX's map](https://spx.com.br/service-point) finds agencies: choose "Retirar um pacote" (pick up a parcel).

## Parcels from abroad: Remessa Conforme and taxes

Receita Federal lists Shopee among the shops certified in its Remessa Conforme programme (PRC). On a certified shop, import duty and ICMS are paid at checkout. Check that the taxes show on the checkout screen: if they don't, Receita says they'll be charged when the parcel arrives and you lose the reduced duty.

The rates on Receita's page (updated 12 June 2026):

- **Import duty** on a certified shop, for a parcel to a private person, on declarations registered from 12 May 2026: 0% up to US$50 of customs value (goods, freight and insurance); above US$50, 60%, with the equivalent of US$30 taken off the duty.
- **ICMS**, the state tax: currently 17% to 20% depending on the state, certified shop or not.

Shopee says an irregular CPF (taxpayer number) can block an international purchase, and import taxes aren't refunded. If customs holds an order Anjun Courier delivers, Shopee asks for proof of payment and price, an ID copy and Receita's value form (RCV). For a Correios parcel, look for anything pending on Correios' tracker; see [Correios Brazil](carrier:correios-br). Receita doesn't send payment slips (boletos), call or email to collect these taxes.

## SPX parcel not updating, late or not received

**No update for days.** Shopee says that's common: tracking only changes when the parcel is scanned at a new centre, so long trips between states, weekends and hub queues leave it still. A route that seems to move away from you is part of the plan, too. Usually several steps appear at once when the parcel goes out for delivery. See [tracking that isn't updating](guide:tracking-not-updating).

**Number not working.** The parcel may not be collected yet, or the number may be wrong or not in the system yet. Ask the seller in Shopee's chat; Shopee says it doesn't mean the parcel is lost.

**Late.** Wait until the estimated date has passed. From then on you can ask for a refund: "Eu" > "A Caminho" > the order > "Pedido de Reembolso" (refund request), reason "Não recebi o pedido" (I didn't receive the order). The seller has 2 days to answer, or the refund goes through on its own. Shopee says most late orders arrive within 5 days of the estimated date.

**Delivered but not received.** Check with neighbours, family and your building's reception or porteiro, then ask for a refund in the app: for Entrega Padrão, within 7 days of delivery. Shopee may have the carrier investigate ("acareação"): it may contact you by WhatsApp or email, or the driver may come back. More in [delivered but not received](guide:delivered-not-received).

**Returned.** After `Pedido será devolvido ao vendedor`, the parcel is on its way back. Shopee refunds a failed delivery automatically; see [returned to sender](guide:returned-to-sender).

## SPX Express contact: go through Shopee

Shopee says you can't contact the carrier directly about tracking or delivery: Shopee handles it, under its Garantia Shopee (Shopee guarantee). As read on 26 October 2026:

- **Chat Shopee**: in the app, "Eu" > "Central de Ajuda" (Help Centre), scroll to "Atendimento ao Cliente" (Customer Service) and pick "Chat Shopee". Monday to Friday 8:00 to 22:00, weekends 8:00 to 20:00, Brasília time.
- **Request form**: any time, from the [Customer Service hours page](https://help.shopee.com.br/portal/4/article/141507). Have the order ID ready.
- **A driver who misbehaved**, say by asking for extra money: report it through Chat Shopee.
- **Still unresolved?** Shopee Brasil is on [consumidor.gov.br](https://www.consumidor.gov.br), the government's complaint platform. Companies there commit to answer within 10 days. You need a gov.br account at silver or gold level.

> Shopee contacts you only through official channels, such as the app, verified social accounts or emails ending in shopee.com. Freight is always paid in the app: a message asking you to pay for shipping elsewhere is a scam. See [delivery scam texts](guide:delivery-scam-texts).

## Track an SPX Express parcel with Peek

[Peek](/) names SPX Express Brazil straight away for a `BR` number with 12 digits and a final digit or letter. A tracking link from spx.com.br (`/track?` or `/detail/` with the number) names SPX too, even when a text message prints it without `https://`. Peek's SPX lookup takes only `BR` numbers, not Shopee order IDs.

Peek reads the same tracking spx.com.br does and shows the status, SPX's own lines (in Portuguese, a few in English), the place SPX gives for scans at its centres, hubs and ports, and the delivery time. It also shows some lines SPX's page hides, such as `Seu pedido chegou ao último centro logístico` (it reached the last logistics centre) or a failed pickup at the seller's. There's no expected delivery date, weight, pickup point or delivering partner, and Peek drops the name of whoever took the parcel. The order, its keyword, the Agência PIN and refunds stay in the Shopee app. If the parcel is still on its way and 4 days pass without a new scan, Peek shows "Get help from SPX Express Brazil", which opens Shopee's Customer Service hours page.

Peek checks every 10 minutes from 8:00 to 22:00 Swiss time (4:00 to 18:00 Brasília time while Switzerland is on winter time, 3:00 to 17:00 in European summer time), every 2 in those hours once the parcel is out for delivery, and every hour or two the rest of the time. It can notify you with "Every scan", "Important steps" or "Delivery only". It can't contact SPX or Shopee for you.

## Questions about SPX Express Brazil tracking

### Is SPX Express the same as Shopee?

It's Shopee's own delivery network: Shopee calls SPX Express its own official logistics in Brazil. The company behind spx.com.br is SPX Express Brasil Ltda., with over 100 logistics hubs and 11 distribution centres.

### How do I track an SPX Express parcel?

In the Shopee app: "Eu" > "Minhas Compras" > the order > shipping status. You can also paste the `BR` number into Peek, or use the tracking box on spx.com.br.

### What does "Pedido em rota de entrega para seu endereço" mean?

The parcel is out for delivery to your address, and SPX says to expect it in the next few hours. If the order has a keyword, have it ready for the driver.

### How long does SPX Express take to deliver?

Shopee gives 1 to 25 working days from collection for orders from sellers in Brazil, and generally 30 to 50 calendar days for international ones. Your estimated date is on the order page.

### Can I contact SPX Express directly?

Shopee says no: it handles tracking and delivery problems itself through Chat Shopee, 8:00 to 22:00 on weekdays and 8:00 to 20:00 at weekends, Brasília time.

:::sources
- [SPX Express: Home](https://spx.com.br/br) – Shopee's integrated logistics in Brazil, hubs and distribution centres, tracking box, Agência Shopee finder, SPX Express Brasil Ltda.
- [SPX Express: Sobre Nós](https://spx.com.br/br/help-center/about-us.html) – tracking in the Shopee app
- [SPX Express: Encontre uma Agência Shopee](https://spx.com.br/service-point) – agency map, "Retirar um pacote"
- [Shopee: O que é a SPX Express?](https://help.shopee.com.br/portal/4/article/197188) – Shopee's own official logistics in Brazil
- [Shopee: O que é Shopee Xpress (SPX)?](https://help.shopee.com.br/portal/4/article/76163) – name, collection, distribution centre, partners
- [Shopee: Posso entrar em contato com os parceiros logísticos da Shopee?](https://help.shopee.com.br/portal/4/article/76264) – delivery kinds, tracking in the app, no direct carrier contact, 3 attempts
- [Shopee: Como faço para verificar o status do meu pedido?](https://help.shopee.com.br/portal/4/article/76323) – Correios codes
- [Shopee: Quais são as opções de envio disponíveis na Shopee?](https://help.shopee.com.br/portal/4/article/76335) – tracking number shared automatically
- [Shopee: Como eu acho a data prevista de entrega para o meu pedido?](https://help.shopee.com.br/portal/4/article/81887) – estimated date, Status de Envio
- [Shopee: Como encontro o ID do pedido da minha compra?](https://help.shopee.com.br/portal/4/article/76194) – order ID
- [Shopee: O que é logística do vendedor?](https://help.shopee.com.br/portal/4/article/132039) – seller delivery and tracking link
- [Shopee: Como minha encomenda do exterior será entregue quando chegar ao Brasil?](https://help.shopee.com.br/portal/4/article/76363) – Correios for orders from abroad
- [Shopee: Por que meu pedido está parado na alfândega do Brasil?](https://help.shopee.com.br/portal/4/article/153683) – Correios or Anjun Courier, documents, RCV
- [Shopee: Quando receberei meu pedido?](https://help.shopee.com.br/portal/4/article/76349) – 1 to 25 working days, 30 to 50 days
- [Shopee: Posso agendar a data e hora de entrega do meu pedido?](https://help.shopee.com.br/portal/4/article/76333) – no scheduling, attempts, automatic refund
- [Shopee: Posso alterar meu endereço de entrega depois que meu pedido for feito?](https://help.shopee.com.br/portal/4/article/76279) – address changes
- [Shopee: O que é a palavra-chave no meu pedido?](https://help.shopee.com.br/portal/4/article/127204) – keyword, 3 attempts, refund
- [Shopee: O que fazer quando não estiver em casa para informar a palavra-chave?](https://help.shopee.com.br/portal/4/article/127206) – someone you trust
- [Shopee: O que fazer se minha palavra-chave não funcionar corretamente?](https://help.shopee.com.br/portal/4/article/127205) – refusing a wrong delivery
- [Shopee: Retirada de pacotes na Agência Shopee](https://help.shopee.com.br/portal/4/article/164642) – Retire Fácil, notifications, PIN, deadline
- [Shopee: Sobre a Agência Shopee](https://help.shopee.com.br/portal/4/article/164639) – PIN and tracking number, 7 working days, SPX takes it back, no switch, redirect
- [Shopee: Meu pedido está sem atualização no rastreamento](https://help.shopee.com.br/portal/4/article/223973) – no daily updates, 5 days after the estimated date
- [Shopee: Por que meu pedido está parado ou mudou de rota?](https://help.shopee.com.br/portal/4/article/159363) – route changes
- [Shopee: Por que meu código de rastreio não está funcionando?](https://help.shopee.com.br/portal/4/article/76369) – reasons, ask the seller
- [Shopee: O que devo fazer se não receber meu pedido até a data prevista de entrega?](https://help.shopee.com.br/portal/4/article/76321) – refund steps, 2 days for the seller
- [Shopee: O que devo fazer se não receber um pedido, mas o status está como Entregue?](https://help.shopee.com.br/portal/4/article/76207) – checks, acareação
- [Shopee: Como faço para solicitar devolução/reembolso?](https://help.shopee.com.br/portal/4/article/76204) – 7 days after delivery
- [Shopee: Como entro em contato com o Atendimento ao Cliente da Shopee?](https://help.shopee.com.br/portal/4/article/78354) – Chat Shopee, hours
- [Shopee: Qual horário do Atendimento ao Cliente da Shopee?](https://help.shopee.com.br/portal/4/article/141507) – hours, request form
- [Shopee: O entregador do meu pedido teve uma má conduta](https://help.shopee.com.br/portal/4/article/224511) – reporting a driver
- [Shopee: Como faço para detectar um golpe?](https://help.shopee.com.br/portal/4/article/76330) – official channels, freight paid in the app
- [Shopee: Por que não consigo concluir minha compra internacional?](https://help.shopee.com.br/portal/4/article/141561) – irregular CPF
- [Shopee: Como solicitar devolução/reembolso ou realizar o cancelamento da minha compra internacional?](https://help.shopee.com.br/portal/4/article/134867) – same refund steps, import taxes not refunded
- [Receita Federal: Tributação](https://www.gov.br/receitafederal/pt-br/assuntos/aduana-e-comercio-exterior/manuais/remessas-postal-e-expressa/topicos/tributacao) – rates from 12 May 2026, ICMS, payment, scams
- [Receita Federal: Em que momento vou pagar os impostos?](https://www.gov.br/receitafederal/pt-br/assuntos/aduana-e-comercio-exterior/manuais/remessas-postal-e-expressa/preciso-pagar-impostos-nas-compras-internacionais/em-que-momento-pagarei-o-imposto) – taxes at checkout
- [Receita Federal: Empresas certificadas no Programa Remessa Conforme](https://www.gov.br/receitafederal/pt-br/assuntos/aduana-e-comercio-exterior/manuais/remessas-postal-e-expressa/empresas-certificadas-no-programa-remessa-conforme-prc) – Shopee certified
- [Consumidor.gov.br: Início](https://www.consumidor.gov.br/pages/principal/) – 10 days, gov.br account level
- [Consumidor.gov.br: Empresas participantes](https://www.consumidor.gov.br/pages/principal/empresas-participantes) – Shopee Brasil
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – SPX's status lines, number shapes, links, what Peek shows
:::
