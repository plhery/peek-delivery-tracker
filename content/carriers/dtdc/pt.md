---
title: Rastreio DTDC: formatos AWB, estados RTO e contactos
description: Segue uma encomenda DTDC na Índia ou no mundo: códigos AWB N, 7D e 7X, registos de hub, devoluções RTO, prazos WorldWyde e apoio ao cliente.
slug: rastreio-dtdc
published: 2026-11-24
updated: 2026-11-24
---

A DTDC (*DTDC Express Limited*), fundada a 26 de julho de 1990 e sediada em Bengaluru, é uma das maiores redes de logística expressa da Índia. Através de mais de 16 500 parceiros que abrangem 96% da população indiana, com Express Parcel a servir mais de 15 300 códigos postais, presença local em 7 países e alcance internacional em mais de 220 destinos, a DTDC opera encomendas expressas nacionais, *Express Premium* no dia seguinte, *AirMax*, *Raftaar*, 3PL, carga LTL e expedição internacional *DTDC WorldWyde*. Podes consultar um envio na [página de rastreio da DTDC](https://www.dtdc.com/track-your-shipment/) ou no [MyDTDC](https://web.mydtdc.in/), ou colar o número AWB na caixa acima.

## Números de rastreio DTDC (formatos AWB)

A DTDC envia o teu número Air Waybill (AWB) por e-mail e SMS quando o pedido é confirmado. Os AWB nacionais e internacionais da DTDC seguem estes formatos alfanuméricos comuns:

| Formato (inventado) | O que é |
| --- | --- |
| `N48291736` | Número de envio padrão DTDC de 9 caracteres (`N` + 8 dígitos). |
| `7D482917364` | Número de envio DTDC de 11 caracteres (`7D` + 9 dígitos). |
| `7X482917364` | Número de envio DTDC de 11 caracteres (`7X` + 9 dígitos). |

A DTDC também emite códigos de 9 e 11 caracteres iniciados por outras letras isoladas (como `B`, `D`, `H`, `P`, `T`, `U`, `V` ou `X` seguidas de 8 ou 10 dígitos), que coincidem com outras transportadoras regionais e podem obrigar-te a escolher a DTDC manualmente se a deteção mostrar vários candidatos. Em [dtdc.com/track-your-shipment/](https://www.dtdc.com/track-your-shipment/) podes alternar entre **Shipment Number** (AWB) e **Reference Number**. Para outros formatos na Índia e no estrangeiro, consulta [formatos de números de rastreio](guide:tracking-number-formats).

## O que significam os estados de rastreio DTDC

A DTDC publica etapas e movimentos de centros (como `Arrived at DELHI`, `Dispatched from MATHURA` ou `Received at ... Processing Centre`) na hora padrão da Índia (`Asia/Kolkata`, UTC+5:30).

| A DTDC mostra | O que significa para ti | O Peek mostra |
| --- | --- | --- |
| `Booked`, `Booked at Origin Branch`, `Pickup Awaited`, `Pickup Scheduled`, `Pickup requested`, `Not picked` | Guia reservada eletronicamente ou ao balcão; aguarda recolha física ou entrada no hub. | «Etiqueta criada» |
| `Picked Up`, `Pickup Completed` | A DTDC recolheu a encomenda junto do remetente. | «Aceite pela transportadora» |
| `In Transit`, `On The Way`, `At Processing Centre`, `At Destination`, `Reattempt Initiated`, `Arrived at ...`, `Dispatched from ...` | Em deslocação entre centros de triagem DTDC, chegada ao centro de destino ou agendada para nova tentativa. | «Em trânsito» |
| `Out For Delivery` | Confiada ao parceiro local de entrega para distribuição hoje. | «Em distribuição» |
| `Undelivered` | Tentativa de entrega não concluída (por exemplo destinatário ausente ou problema de morada). | «Tentativa de entrega» |
| `RTO Booked`, `Return Approval awaited`, `Return Initiated` / `Return - Initiated` | Acionada devolução à origem (RTO) para enviar a encomenda de volta ao remetente. | «Problema reportado» |
| `Delivered` (percurso de ida) | Entregue ao destinatário. | «Entregue» |
| `Delivered` (em percurso `RTO`) | Devolvida e entregue novamente ao remetente original. | «Devolvida ao remetente» |

**Atenção às devoluções `RTO`.** Quando um envio não pode ser entregue, a DTDC abre um percurso Return To Origin (`RTO`) que tem o seu próprio registo `Delivered` quando a encomenda regressa ao remetente. Verifica se o último percurso é de ida ou `RTO` antes de assumires que te foi entregue.

## Serviços nacionais e internacionais da DTDC

Segundo [DTDC Express Parcel](https://www.dtdc.com/express-parcel/), [DTDC International Shipping](https://www.dtdc.com/international-shipping/), [About Us](https://www.dtdc.com/about-us/) e as [FAQ da DTDC](https://www.dtdc.com/faq/):

- **Serviços nacionais na Índia:** *Express Parcel* (entrega diária em mais de 15 300 códigos postais com contra reembolso, autenticação de entrega e logística inversa), *Express Premium* (urgente no dia seguinte), *AirMax*, *Raftaar*, *Personal Courier* e B2B *3PL* / *LTL*.
- **Domingos e feriados:** as [FAQ da DTDC](https://www.dtdc.com/faq/) indicam que algumas filiais locais da DTDC funcionam aos domingos e feriados consoante o horário de cada agência.
- **Prazos internacionais (*DTDC WorldWyde*):** mais de 220 destinos com desalfandegamento próprio e KYC/CSB-V digital — *WorldWyde Express Premium* (**4–5 dias**), *WorldWyde Express Standard* (**7–8 dias**), *WorldWyde Imports Standard* (**10–12 dias**) e *WorldWyde B2C Standard* (**15–20 dias**). O prazo real depende também da alfândega de destino; consulta [encomendas retidas na alfândega](guide:customs).

As páginas de serviço da DTDC não publicam número fixo de tentativas de entrega nem prazo de guarda. Uma tentativa falhada mostra `Undelivered` e uma nova tentativa `Reattempt Initiated`.

## Encomenda DTDC atrasada, não entregue ou perdida

Em compras online, contacta primeiro o vendedor para autorizar correção de morada, reagendamento ou reembolso — especialmente se surgir `Return Approval awaited` ou `RTO Booked`.

- **Atualizar morada, reagendar ou levantar:** a página [Track Your Shipment](https://www.dtdc.com/track-your-shipment/) permite abrir disputa, falar por chat ou pedir chamada, e o formulário [Customer Care](https://www.dtdc.com/customer-care/) aceita pedidos de nova morada ou telefone, nova data de entrega ou levantamento próprio.
- **Marcada `Delivered` mas não recebida:** confirma primeiro se é uma devolução `RTO` ao remetente; sendo entrega de ida, pergunta a familiares, vizinhos e segurança do edifício e abre logo reclamação na DTDC Customer Care e junto do vendedor.
- Consulta [porque o rastreio deixa de atualizar](guide:tracking-not-updating), [entregue mas não recebida](guide:delivered-not-received), [encomenda danificada](guide:damaged-parcel), [encomenda perdida](guide:lost-parcel) e [devolvida ao remetente](guide:returned-to-sender).

## Apoio ao cliente e contactos oficiais DTDC

Contactos publicados em [DTDC Customer Care](https://www.dtdc.com/customer-care/) e [dtdc.com](https://www.dtdc.com/in/):

- **Telefone de apoio ao cliente:** `+91-9606 911 811`.
- **E-mail de apoio:** `customersupport@dtdc.com`.
- **Apoio online:** formulário de pedidos/reclamações em [dtdc.com/customer-care/](https://www.dtdc.com/customer-care/), assistente virtual **DIVA** no site e app móvel **MyDTDC**.

> A DTDC avisa em destaque em [dtdc.com](https://www.dtdc.com/in/) que **`www.dtdc.com` é o único site oficial da DTDC** e declina responsabilidade por ofertas, números ou pagamentos feitos em sites imitadores. Nunca pagues taxas nem partilhes um código OTP através de um link desconhecido. Consulta [SMS falsas de entregas](guide:delivery-scam-texts).

## Segue uma encomenda DTDC com o Peek

O [Peek](/) reconhece automaticamente `N` + 8 dígitos (`N48291736`, inventado) e `7D` / `7X` + 9 dígitos (`7D482917364`, `7X482917364`) como DTDC com elevada confiança, além de links colados `dtdc.com` com `trackingId=` ou `awb=`. Para códigos alfanuméricos de 9 ou 11 caracteres começados por outras letras, toca em «Alterar» ao adicionar ou em «Alterar transportadora» na página da encomenda e escolhe DTDC.

O Peek consulta a DTDC em duas etapas diretas: primeiro pelo feed anónimo MyDTDC e, se necessário, pela página de rastreio `dtdc.com`. Mostra a etapa («Etiqueta criada», «Aceite pela transportadora», «Em trânsito», «Em distribuição», «Tentativa de entrega», «Problema reportado», «Devolvida ao remetente», «Entregue»), o texto dos registos DTDC, a localização do hub ou filial e horas em hora padrão da Índia (`Asia/Kolkata`) convertidas para a hora do teu dispositivo. Importante: quando a DTDC marca um percurso `RTO` como `Delivered`, o Peek mostra-o como «Devolvida ao remetente» em vez de «Entregue». O Peek descarta moradas, telefones/contactos, metadados de reserva e pesos sem etiqueta; o rastreio DTDC não fornece data prevista nem dados de ponto de levantamento.

Enquanto manténs a encomenda aberta no Peek, ou no dia da entrega com as notificações ativas, o Peek verifica até de 10 em 10 minutos; no dia em que a DTDC mostra `Out For Delivery`, de 2 em 2 minutos a partir das 6 h na hora da Índia, enquanto o rastreio próprio da DTDC responder. Nos restantes casos verifica de hora em hora. Após 12 horas só em «Etiqueta criada» ou «Aceite pela transportadora», ou dois dias sem novidades, verifica de hora em hora seja como for, e de 6 em 6 horas após sete dias em «Etiqueta criada». Quando o rastreio da DTDC não responde e o Peek acompanha a encomenda apenas por serviços de rastreio, verifica de hora em hora, de 3 em 3 horas após um dia sem novidades e até de 15 em 15 minutos no dia de distribuição enquanto a mantiveres aberta ou com notificações ativas.

Quando o último registo tem quatro dias e a encomenda continua em «Etiqueta criada», «Aceite pela transportadora» ou «Em trânsito», o Peek assinala-a com a data desse registo, como «Sem atualizações desde seg 12 out». A página mostra então: «É habitual uma encomenda ficar sem novidades em voos longos, na alfândega ou ao fim de semana. O Peek continua a perguntar a DTDC.», com a ligação «Pede ajuda a DTDC» para a página Customer Care da DTDC. Após três semanas sem novos registos, mostra: «Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, contacta o vendedor. O Peek continua a perguntar a DTDC.»

Podes escolher os alertas «Todos os registos», «Passos importantes» ou «Só a entrega». O Peek apenas acompanha o rastreio e não pode reagendar uma entrega DTDC, alterar moradas nem abrir disputas por ti.

## Perguntas sobre o rastreio DTDC

### Como é um número de rastreio (AWB) DTDC?

Os números habituais da DTDC têm 9 caracteres iniciados por `N` (`N48291736`, inventado) ou 11 caracteres iniciados por `7D` ou `7X` (`7D482917364`, `7X482917364`), embora a DTDC também use outros prefixos de uma letra de 9 e 11 caracteres.

### O que significam `RTO Booked` ou `Return Initiated` no rastreio DTDC?

`RTO` significa Return To Origin (devolução à origem). Indica que a encomenda não pôde ser entregue (ou foi pedida aprovação de devolução) e está a regressar ao remetente. Contacta de imediato o vendedor se ainda pretendes o pedido.

### Quanto tempo demora o envio internacional DTDC?

Em [dtdc.com/international-shipping/](https://www.dtdc.com/international-shipping/), a DTDC indica 4–5 dias para *WorldWyde Express Premium*, 7–8 dias para *Express Standard*, 10–12 dias para *Imports Standard* e 15–20 dias para *B2C Standard*, sujeito a desalfandegamento.

### Como contacto o apoio ao cliente da DTDC?

Liga para `+91-9606 911 811`, escreve para `customersupport@dtdc.com` ou usa o formulário de pedidos e o assistente DIVA em [dtdc.com/customer-care/](https://www.dtdc.com/customer-care/).

:::sources
- [DTDC: Home](https://www.dtdc.com/in/) – aviso de único site oficial (`www.dtdc.com`), apoio DIVA e visão geral dos serviços
- [DTDC: About Us](https://www.dtdc.com/about-us/) – fundada em 26 de julho de 1990, sede em Bengaluru, 16 500+ parceiros, 96% da população indiana, 7 países, 220+ destinos e 5,6M+ utilizadores MyDTDC
- [DTDC: Track Your Shipment](https://www.dtdc.com/track-your-shipment/) – pesquisa AWB / referência, aviso AWB por SMS/e-mail, disputa, chat e chamada
- [DTDC: Express Parcel](https://www.dtdc.com/express-parcel/) – 15 300+ códigos postais, contra reembolso, autenticação de entrega e logística inversa
- [DTDC: International Shipping (WorldWyde)](https://www.dtdc.com/international-shipping/) – 220+ destinos, prazos WorldWyde (4–5, 7–8, 10–12, 15–20 dias) e desalfandegamento CSB-V/KYC
- [DTDC: FAQ](https://www.dtdc.com/faq/) – disponibilidade aos domingos/feriados e perguntas de rastreio
- [DTDC: Customer Care](https://www.dtdc.com/customer-care/) – `+91-9606 911 811`, `customersupport@dtdc.com`, formulário online para morada, data e levantamento (URL de ajuda ligado pelo Peek)
- [Portal MyDTDC](https://web.mydtdc.in/) – portal web de rastreio MyDTDC
- [Universal Parcel Scraper: dtdc](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/dtdc) – deteção de elevada confiança `N`/`7D`/`7X`, regras de link `dtdc.com`, consulta em duas etapas, tratamento de percursos RTO, fuso `Asia/Kolkata`, campos omitidos e URL de ajuda
:::
