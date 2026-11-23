---
title: Seguimento DPD Romania: estados, Smart Control e contacto
description: Segue uma encomenda da DPD Romania: número AWB de 11 dígitos, estados em inglês e romeno, Smart Control, cacifos, balcões e contacto.
slug: seguimento-dpd-romania
published: 2026-11-23
updated: 2026-11-23
---

A DPD Romania (Dynamic Parcel Distribution S.A.) é a transportadora do grupo Geopost na Roménia, ativa desde 2008 e com sede em Mogoșoaia, Ilfov. Cada encomenda recebe o seu número de guia (*waybill* ou AWB): acompanha-a em tracking.dpd.ro (que tem versão em inglês), através da ligação Smart Control no SMS de entrega da DPD ou na caixa acima.

## Número de seguimento da DPD Romania: onde encontrar

A DPD chama-lhe número de guia (*waybill*) ou AWB. As páginas da DPD não indicam regra de comprimento; os AWB que o Peek reconhece como DPD Romania têm 11 dígitos começados por 8:

| Número (inventado) | O que é |
| --- | --- |
| `84927163504` | 11 dígitos a começar por 8: um AWB da DPD Romania. Também coincide com [GLS Germany](carrier:gls-de), [GLS France](carrier:gls-fr), [GLS Switzerland](carrier:gls-ch), GLS Austria, PPL e outras. |
| `01204819273645` | 14 dígitos: formato de outras empresas DPD como [DPD Germany](carrier:dpd-de), [DPD UK](carrier:dpd-uk), DPD France ou [DPD Portugal](carrier:dpd-pt). O Peek segue a DPD Romania apenas pelo AWB de 11 dígitos. |

Procura no e-mail de envio da loja ou na página da encomenda. A app móvel da DPD tem um painel de destinatário com início de sessão por código via SMS ou e-mail. Se a loja indicar Cargus, [FAN Courier](carrier:fan-courier) ou Sameday, o número pertence-lhes. Sem número nenhum? Vê [como encontrar o teu número de seguimento](guide:find-tracking-number).

## O que significam os estados de seguimento da DPD Romania

O seguimento da DPD responde em romeno ou inglês; os registos em romeno existem numa forma curta antiga e numa frase mais recente. Pela ordem habitual:

| A DPD mostra | O que significa para ti |
| --- | --- |
| `Shipment data received`, `Receptionare informatii colet`, `Expedierea ta a fost înregistrată de către expeditor.` | O remetente criou o AWB. A DPD tem os dados, mas ainda não a encomenda. |
| `Expedierea nu a putut fi preluată de către curierul DPD.` | O estafeta não conseguiu recolher junto do remetente; segue o motivo. |
| `Courier Pick-up`, `Expedierea ta a fost preluată de la expeditor de către curierul DPD.` | Um estafeta da DPD recolheu a encomenda no remetente. |
| `Received in Office`, `Receptie la sediu` | Um balcão DPD recebeu-a, entregue pelo estafeta ou ao balcão. |
| `Departure Scan`, `Arrival Scan`, `Intrare in depozit`, `Expedierea ta a fost recepționată în depozitul DPD.`, `Expedierea ta a fost procesată în depozitul DPD.` | Saída, chegada ou processamento num depósito DPD. |
| `Expedierea ta se află în tranzit spre depozitul de destinație.` | Em trânsito para o depósito que faz a entrega. |
| `Expedierea ta a ajuns în depozitul de destinație.` | Chegou ao depósito de destino: a entrega vem a seguir. |
| `Predict`, `Predict – s-au trimis informațiile cu privire la intervalul orar de livrare.` | A DPD enviou a janela horária de entrega. É um aviso, não um registo físico. |
| `Out for Delivery`, `Iesire in livrare`, `Expedierea ta a fost preluată spre livrare de către curierul DPD.` | Com o estafeta para entrega. |
| `Livrare nereusita`, `Expedierea ta nu a putut fi livrată.` | Não entregue. Segue o motivo, como morada errada ou recusa. |
| `Expedierea ta a fost returnată în depozitul DPD.` | De regresso ao depósito após tentativa falhada, não é devolução. |
| `Delivered`, `Livrat` | Entregue. O seguimento acrescenta o nome de quem a recebeu. |
| `Retur catre expeditor` | De volta ao remetente, com um AWB de devolução indicado na nota. |
| `Retur livrat catre expeditor` | Entregue de volta ao remetente. |
| `Canceled`, `Expedierea ta a fost anulată.` | O AWB foi cancelado: pergunta à loja. |

`Shipment data received` costuma mostrar a data prevista de entrega. Registos em depósitos DPD no estrangeiro indicam o depósito e o país.

## Entregas da DPD na Roménia: Smart Control, balcões e cacifos

**Prazos.** O DPD Standard entrega em toda a Roménia em 1 a 2 dias úteis. Qualquer pagamento contra entrega faz-se ao estafeta em dinheiro ou, em zonas urbanas, por cartão.

**Smart Control.** Antes da entrega, a DPD envia um SMS com a data e uma ligação gratuita Smart Control onde podes confirmar ou mudar a morada, ver onde está a encomenda, redirecioná-la para um armazém, balcão, cacifo ou DPD Shop, ou adiá-la para o dia útil seguinte. O redirecionamento dentro da mesma cidade é gratuito. A entrega é sem contacto (a assinatura eletrónica deixou de ser exigida); a DPD pode confirmar a identidade por documento e PIN enviado por SMS ou e-mail.

**Entrega falhada.** A DPD faz duas tentativas em dias úteis consecutivos. Após a segunda deixa aviso e guarda a encomenda grátis num ponto DPD com atendimento durante 5 dias de calendário para combinares uma última tentativa. Encomendas Express e Livrare specială têm apenas uma tentativa antes da devolução. A DPD não entrega em caixas de correio. Se recusares a encomenda ou o pagamento à cobrança, a devolução arranca no próprio dia sem espera.

**Balcões, cacifos e Parcel Shops.** Prazos de levantamento segundo a DPD:

- Balcão DPD: 7 dias de calendário; pagamento em dinheiro, cartão ou Pay by Link.
- Cacifo DPD: 3 dias de calendário (72 horas a contar do aviso nos termos da DPD); cacifos exteriores abertos 24/7; cartão ou Pay by Link.
- DPD Parcel Shop: 5 dias de calendário; Pay by Link ou cartão (sem cartão nos pontos Pay Point).

Findo o prazo, a encomenda regressa ao remetente em 5 dias úteis (10 dias em envios internacionais).

## Encomenda DPD Romania atrasada, perdida, danificada ou na alfândega

Para reembolso ou novo envio, fala com a loja: é ela que detém o contrato com a DPD.

**Sem atualizações.** Se o estado não mudar durante muito tempo ou falhar o prazo, a DPD pede contacto por client@dpd.ro ou 031 824 90 90. Vê [seguimento sem atualizações](guide:tracking-not-updating).

**Entregue mas não recebida.** O seguimento indica quem recebeu a encomenda: pergunta primeiro a essa pessoa e depois contacta a DPD e a loja. Mais em [entregue mas não recebida](guide:delivered-not-received).

**Devolvida.** `Retur catre expeditor` significa que voltou atrás. Pede à loja para reenviar. Vê [devolvida ao remetente](guide:returned-to-sender).

**Reclamações.** Tanto o remetente como o destinatário podem reclamar em 6 meses desde a expedição com o formulário DPD: por e-mail para reclamatii@dpd.ro, online, num ponto DPD com atendimento ou por correio registado. A DPD decide em 3 meses e paga em 15 dias após a decisão. Em perda ou dano sem valor declarado nem cobrança, paga 5 vezes o preço do serviço. No prazo de 1 ano desde o envio podes também recorrer à ANCOM (regulador romeno das comunicações) ou aos tribunais. Vê [encomendas perdidas](guide:lost-parcel).

**Alfândega.** Compras online vindas de fora da UE pagam um direito aduaneiro europeu de 3 € por tipo de produto em encomendas até 150 € desde 1 de julho de 2026 e uma taxa de tratamento da União de 2 € por tipo de produto, qualquer que seja o valor, desde 1 de novembro de 2026. As páginas da DPD Romania não explicam como os repassa: pergunta à loja se estavam incluídos. O aviso de 22 de dezembro de 2025 explica também a taxa fixa romena de 25 RON por encomenda extra-UE declarada abaixo de 150 €, em vigor desde 1 de janeiro de 2026: é paga pelo vendedor, remetente ou marketplace, e a DPD cobra-a a eles. Vê [encomendas retidas na alfândega](guide:customs).

## Contacto e apoio ao cliente da DPD Romania

O Peek não pode contactar a DPD nem a loja por ti. Na página de contacto da DPD Romania:

- **Encomendas na Roménia:** 031 824 90 90, segunda a sexta das 8:30 às 16:30 (hora da Roménia). Pedidos para client@dpd.ro.
- **Encomendas internacionais:** 031 824 90 32 ou international@dpd.ro, no mesmo horário.
- **Reclamações:** reclamatii@dpd.ro ou formulário de reclamação.
- **Correio:** Dynamic Parcel Distribution S.A., Str. Buiacului nr. 2, LogIQ Logistic Park, Corp A (A1-A2), 077135 Mogoșoaia, Ilfov.

A DPD não indica se o atendimento telefónico fala inglês ou português.

> A DPD Romania alerta para SMS e e-mails falsos em seu nome com ligações para sites que copiam a DPD para pedir pagamento ou dados de cartão. A DPD Romania nunca pede dados bancários por SMS nem e-mail. Não abras a ligação, marca como spam e avisa a DPD. Vê [SMS fraudulentos de entregas](guide:delivery-scam-texts).

## Seguir uma encomenda da DPD Romania com o Peek

Cola o AWB de 11 dígitos no [Peek](/). Outras transportadoras partilham este formato, pelo que o Peek consulta as mais prováveis consoante o teu país (região do telemóvel ou país de ligação na web). Na Roménia pergunta primeiro à DPD Romania e mostra «DPD Romania tem esta encomenda.» quando a DPD a conhece. Noutros países consulta outras transportadoras: se vires «ainda não encontrada · o Peek continua a verificar», prime «Alterar» e escolhe DPD Romania (também listada como «DPD România», «DPD RO» ou «Dynamic Parcel Distribution»), ou usa «Alterar transportadora» numa encomenda já adicionada. Cola-o na mesma: o Peek segue mais de 4500 transportadoras. Funcionam também ligações de tracking.dpd.ro ou services.dpd.ro/tracking com `shipmentNumber=`, e de dpdgroup.com/ro/ ou dpd.com/ro/ com `parcelNumber=`.

O Peek pede à DPD o seguimento em inglês e mostra o estado, o histórico nas palavras da DPD com a localidade (e país) de cada registo, a data prevista em trânsito e a hora de entrega. Deixa de fora quem recebeu, a assinatura e o AWB de devolução; a DPD não fornece peso nem remetente.

Quando a DPD entrega uma encomenda à ACS Courier para a etapa final (por exemplo para Chipre), indica a ACS e o seu número. Se tiver dez dígitos como os códigos ACS, o Peek consulta a ACS e passa a segui-la assim que apresentar um registo tão recente quanto o da DPD. Até lá, ou com outro formato, segue a DPD e o cartão já mostra a ACS e o respetivo número.

O Peek verifica até de 10 em 10 minutos, ou de 2 em 2 a partir das 6:00 hora local no dia em que a DPD regista a encomenda em distribuição, enquanto o seguimento da DPD responder (via serviços de seguimento, de hora a hora, e até de 15 em 15 minutos no dia da entrega com a página aberta ou alertas ativos). Podes ativar alertas para «Todos os registos», «Passos importantes» ou «Só a entrega». Após dois dias sem novidades (12 horas enquanto mostrar «Etiqueta criada» ou «Aceite pela transportadora»), verifica de hora a hora. Para alterar a entrega, usa o Smart Control.

## Perguntas sobre o seguimento da DPD na Roménia

### Quanto tempo demora a DPD a entregar na Roménia?

1 a 2 dias úteis com o DPD Standard. Os termos da DPD admitem até 7 dias de calendário para localidades com dias fixos de visita.

### Quanto tempo guarda a DPD uma encomenda em balcão ou cacifo?

7 dias de calendário num balcão, 5 num Parcel Shop, 3 num cacifo e 5 num ponto DPD após duas entregas falhadas. Depois regressa ao remetente.

### Posso acompanhar uma encomenda da DPD Romania em inglês?

Sim. O seguimento da DPD tem versão inglesa e o Peek mostra os textos da DPD em inglês.

### Qual é o número de telefone da DPD Romania?

031 824 90 90 para encomendas na Roménia e 031 824 90 32 para internacionais, segunda a sexta das 8:30 às 16:30 (hora da Roménia).

:::sources
- [DPD Romania: Where is my parcel?](https://www.dpd.com/ro/en/faq/unde-este-coletul-meu/) – número AWB, seguimento
- [DPD Romania: My parcel has not arrived yet](https://www.dpd.com/ro/en/faq/coletul-meu-nu-a-sosit-inca-ce-este-de-facut/) – seguimento em inglês, quando ligar
- [DPD Romania: How can I change the place or day of delivery?](https://www.dpd.com/ro/en/faq/cum-pot-schimba-locul-sau-ziua-livrarii/) – redirecionamento
- [DPD Romania: Contact customer service](https://www.dpd.com/ro/en/faq/contact-serviciul-clienti/) – telefone, horários
- [DPD Romania: Smart Control](https://www.dpd.com/ro/en/smart-control/) – SMS, redirecionamento gratuito, entrega sem contacto
- [DPD Romania: Home](https://www.dpd.com/ro/en/) – opções Smart Control
- [DPD Romania: DPD OOH](https://www.dpd.com/ro/en/dpd-ooh/) – prazos de levantamento, pagamento
- [DPD Romania: Contact](https://www.dpd.com/ro/en/support/contact-dpd/) – telefones, e-mails, horários
- [DPD Romania: National services](https://www.dpd.com/ro/en/parcel-shipping/national-services/) – 1 a 2 dias úteis, pagamento por cartão
- [DPD Romania: Payment](https://www.dpd.com/ro/en/payment/) – pagamento contra entrega
- [DPD Romania: Mobile application](https://www.dpd.com/ro/en/mobile-application-dpd/) – app, painel de destinatário
- [DPD Romania: Termeni și condiții](https://www.dpd.com/ro/ro/termeni-si-conditii/) – AWB, PIN, tentativas, guarda, recusa, prazos de entrega e devolução, ANCOM
- [DPD Romania: Resolution mechanism of complaints](https://www.dpd.com/ro/en/support/tips-and-tricks-dpd-romania/resolution-mechanism-of-complaints/) – reclamações, indemnização
- [DPD Romania: About the company](https://www.dpd.com/ro/en/about-company/) – desde 2008, Geopost
- [DPD Romania: Legal and copyright notice](https://www.dpd.com/ro/en/support/legal-notice/) – empresa, sede
- [DPD Romania: 25 RON fee for non-EU parcels under EUR 150](https://www.dpd.com/ro/en/2025/12/22/25-ron-fee-for-non-eu-parcels-under-eur-150-2/) – taxa romena de encomendas
- [DPD Romania: Campanie de informare phishing](https://www.dpd.com/ro/ro/2025/08/01/campanie-de-informare-phishing/) – SMS e e-mails falsos
- [Comissão Europeia: reforma aduaneira da UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – direitos de 3 € por tipo de produto até 150 € desde 1 de julho de 2026, taxa de 2 € desde 1 de novembro de 2026
- [Comissão Europeia: guia sobre os direitos de 3 € e a taxa de tratamento da União (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 € por tipo de produto qualquer que seja o valor
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – o que o Peek lê, textos DPD Romania e transferência para a ACS
:::
