---
title: Rastreamento DTDC: códigos AWB, status RTO e suporte
description: Rastreie encomendas DTDC na Índia ou no exterior: códigos AWB N, 7D e 7X, movimentações em hubs, devoluções RTO, prazos WorldWyde e atendimento.
slug: dtdc-tracking
published: 2026-11-26
updated: 2026-11-26
---

A DTDC (*DTDC Express Limited*), fundada em 26 de julho de 1990 e sediada em Bengaluru, é uma das maiores redes de logística expressa da Índia. Por meio de mais de 16.500 parceiros de canal que alcançam 96% da população indiana — com o serviço Express Parcel cobrindo mais de 15.300 códigos postais (pincodes) —, presença local em 7 países e alcance internacional em mais de 220 destinos, a DTDC opera entregas domésticas expressas, *Express Premium* no dia seguinte, *AirMax*, *Raftaar*, 3PL, carga fracionada LTL e envios internacionais *DTDC WorldWyde*. Você pode consultar uma remessa na [página de rastreamento da DTDC](https://www.dtdc.com/track-your-shipment/) ou no [MyDTDC](https://web.mydtdc.in/), ou colar o número AWB na caixa acima.

## Códigos de rastreamento DTDC (formatos AWB)

A DTDC envia o número do conhecimento aéreo (AWB) por e-mail e SMS quando o pedido é confirmado. Códigos AWB domésticos e internacionais da DTDC seguem estes formatos alfanuméricos comuns:

| Formato (inventado) | O que é |
| --- | --- |
| `N48291736` | Número de remessa padrão DTDC de 9 caracteres (`N` + 8 dígitos). |
| `7D482917364` | Número de remessa DTDC de 11 caracteres (`7D` + 9 dígitos). |
| `7X482917364` | Número de remessa DTDC de 11 caracteres (`7X` + 9 dígitos). |

A DTDC também emite códigos de 9 e 11 caracteres iniciados por outras letras isoladas (como `B`, `D`, `H`, `P`, `T`, `U`, `V` ou `X` seguidos de 8 ou 10 dígitos), que podem coincidir com outras transportadoras regionais e exigir seleção manual de DTDC se houver várias candidatas. Em [dtdc.com/track-your-shipment/](https://www.dtdc.com/track-your-shipment/), é possível alternar entre **Shipment Number** (AWB) e **Reference Number**. Para outros padrões na Índia e no mundo, veja [formatos de código de rastreamento](guide:tracking-number-formats).

## O que significam os status de rastreamento da DTDC

A DTDC registra marcos e passagens por centros de triagem (como `Arrived at DELHI`, `Dispatched from MATHURA` ou `Received at ... Processing Centre`) no Horário Padrão da Índia (`Asia/Kolkata`, UTC+5:30).

| A DTDC diz | O que significa para você | O Peek mostra |
| --- | --- | --- |
| `Booked`, `Booked at Origin Branch`, `Pickup Awaited`, `Pickup Scheduled`, `Pickup requested`, `Not picked` | Conhecimento registrado eletronicamente ou no balcão de origem; aguardando coleta física ou entrada no hub. | "Etiqueta criada" |
| `Picked Up`, `Pickup Completed` | A DTDC coletou a encomenda com o remetente. | "Recebida pela transportadora" |
| `In Transit`, `On The Way`, `At Processing Centre`, `At Destination`, `Reattempt Initiated`, `Arrived at ...`, `Dispatched from ...` | Circulando entre hubs de triagem da DTDC, chegando ao centro de destino ou agendada para nova tentativa. | "Em trânsito" |
| `Out For Delivery` | Saiu com o entregador local para entrega hoje. | "Saiu para entrega" |
| `Undelivered` | Tentativa de entrega não concluída (por exemplo, destinatário ausente ou problema no endereço). | "Tentativa de entrega" |
| `RTO Booked`, `Return Approval awaited`, `Return Initiated` / `Return - Initiated` | Devolução à origem (RTO) acionada para enviar o pacote de volta ao remetente. | "Precisa de atenção" |
| `Delivered` (trecho normal) | Entregue ao destinatário. | "Entregue" |
| `Delivered` (em trecho `RTO`) | Devolvida e entregue de volta ao remetente original. | "Devolvida ao remetente" |

**Atenção às devoluções `RTO`.** Quando um envio não pode ser entregue, a DTDC abre um trecho de retorno à origem (`RTO`) que gera seu próprio registro `Delivered` ao chegar de volta ao remetente. Confira se o trecho mais recente é o envio normal ou um `RTO` antes de presumir que foi entregue a você.

## Serviços domésticos e internacionais da DTDC

Segundo [DTDC Express Parcel](https://www.dtdc.com/express-parcel/), [DTDC International Shipping](https://www.dtdc.com/international-shipping/), [Sobre Nós](https://www.dtdc.com/about-us/) e o [FAQ da DTDC](https://www.dtdc.com/faq/):

- **Serviços domésticos na Índia:** *Express Parcel* (entrega cotidiana com data definida em mais de 15.300 pincodes, com pagamento na entrega, autenticação na entrega e logística reversa), *Express Premium* (urgente no dia seguinte), *AirMax*, *Raftaar*, *Personal Courier* e *3PL* / *LTL* B2B.
- **Funcionamento aos domingos e feriados:** o [FAQ da DTDC](https://www.dtdc.com/faq/) informa que algumas agências locais funcionam aos domingos e feriados públicos conforme o expediente local.
- **Prazos internacionais (*DTDC WorldWyde*):** cobrindo mais de 220 destinos com desembaraço aduaneiro próprio e KYC/CSB-V digital — *WorldWyde Express Premium* (**4–5 dias**), *WorldWyde Express Standard* (**7–8 dias**), *WorldWyde Imports Standard* (**10–12 dias**) e *WorldWyde B2C Standard* (**15–20 dias**). O prazo real depende da alfândega de destino; veja [encomendas retidas na alfândega](guide:customs).

As páginas de serviço da DTDC não publicam um número de tentativas de entrega nem um prazo de guarda. Uma entrega não realizada aparece como `Undelivered`, e uma nova tentativa como `Reattempt Initiated`.

## Encomenda DTDC atrasada, não entregue ou extraviada

Em compras on-line, fale primeiro com o vendedor para autorizar ajuste de endereço, reagendamento ou reembolso — especialmente se surgir `Return Approval awaited` ou `RTO Booked`.

- **Atualizar endereço, reagendar ou retirar:** a página [Track Your Shipment](https://www.dtdc.com/track-your-shipment/) permite abrir contestação, conversar no chat ou pedir retorno telefônico, e o formulário de [Customer Care](https://www.dtdc.com/customer-care/) recebe solicitações de novo endereço ou telefone, nova data de entrega ou retirada pelo destinatário.
- **Marcada como `Delivered`, mas não recebida:** verifique primeiro se não é uma devolução `RTO` ao remetente; tratando-se de entrega normal, consulte moradores, vizinhos e portaria e abra contestação imediatamente na DTDC e com o vendedor.
- Veja [por que o rastreamento para de atualizar](guide:tracking-not-updating), [entregue mas não recebida](guide:delivered-not-received), [encomenda danificada](guide:damaged-parcel), [encomenda extraviada](guide:lost-parcel) e [devolvida ao remetente](guide:returned-to-sender).

## Atendimento e contatos oficiais da DTDC

Contatos publicados em [DTDC Customer Care](https://www.dtdc.com/customer-care/) e [dtdc.com](https://www.dtdc.com/in/):

- **Telefone de atendimento:** `+91-9606 911 811`.
- **E-mail de suporte:** `customersupport@dtdc.com`.
- **Atendimento on-line:** formulário de consulta/contestação em [dtdc.com/customer-care/](https://www.dtdc.com/customer-care/), assistente virtual **DIVA** no site e app móvel **MyDTDC**.

> A DTDC destaca em [dtdc.com](https://www.dtdc.com/in/) que **`www.dtdc.com` é o único site oficial da DTDC**, isentando-se de ofertas, telefones ou pagamentos realizados em sites parecidos. Nunca pague taxas nem informe códigos OTP em links desconhecidos que se passem pela DTDC. Veja [golpes de entrega por SMS](guide:delivery-scam-texts).

## Acompanhe uma encomenda DTDC com o Peek

O [Peek](/) reconhece automaticamente com alta confiança códigos `N` + 8 dígitos (`N48291736`, inventado) e `7D` / `7X` + 9 dígitos (`7D482917364`, `7X482917364`) como DTDC, além de links colados de `dtdc.com` contendo `trackingId=` ou `awb=`. Para códigos alfanuméricos de 9 ou 11 caracteres com outras letras iniciais, toque em "Trocar" ao adicionar ou "Trocar transportadora" na tela da encomenda e selecione DTDC.

O Peek consulta a DTDC em duas etapas diretas — primeiro pelo feed anônimo do MyDTDC e, se necessário, recorrendo à página de rastreamento de `dtdc.com`. Exibe a etapa mapeada ("Etiqueta criada", "Recebida pela transportadora", "Em trânsito", "Saiu para entrega", "Tentativa de entrega", "Precisa de atenção", "Devolvida ao remetente", "Entregue"), o texto da DTDC, o hub ou filial e os horários convertidos de `Asia/Kolkata` para o fuso do seu aparelho. Quando a DTDC marca um trecho `RTO` como `Delivered`, o Peek mostra "Devolvida ao remetente" em vez de "Entregue". Endereços completos, telefones/contatos, metadados de reserva e valores de peso sem identificação são descartados; o rastreador da DTDC não informa data prevista nem ponto de retirada.

Com a encomenda aberta no Peek, ou no dia da entrega com notificações ligadas, o Peek consulta até a cada 10 minutos; no dia em que a DTDC exibe `Out For Delivery`, a cada 2 minutos a partir das 6h no horário da Índia enquanto o próprio rastreador da DTDC responder. Nos demais momentos, verifica de hora em hora. Após 12 horas apenas em "Etiqueta criada" ou "Recebida pela transportadora", ou dois dias sem novidades, passa a verificar a cada hora de qualquer modo, e a cada 6 horas depois de sete dias em "Etiqueta criada". Quando o rastreador da DTDC não responde e o Peek acompanha apenas por serviços de rastreamento, consulta a cada hora, a cada 3 horas após um dia sem novidades e até a cada 15 minutos no dia da entrega.

Quando a movimentação mais recente completa quatro dias e a encomenda continua em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito", o Peek sinaliza com a data desse registro, como "Sem atualizações desde seg 12 out". A página então mostra "É comum uma encomenda ficar sem atualizações em voos longos, na alfândega ou no fim de semana. O Peek continua consultando DTDC.", com um link "Pedir ajuda a DTDC" para o Customer Care da DTDC. Após três semanas sem novidade, exibe "Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, fale com o vendedor. O Peek continua consultando DTDC."

Você pode escolher alertas para "Cada movimentação", "Etapas importantes" ou "Só a entrega". O Peek apenas acompanha o rastreamento e não reagenda entregas, não altera endereços nem abre contestações na DTDC por você.

## Perguntas sobre o rastreamento da DTDC

### Como é um código de rastreamento (AWB) da DTDC?

Os códigos comuns da DTDC têm 9 caracteres começando por `N` (`N48291736`, inventado) ou 11 caracteres começando por `7D` ou `7X` (`7D482917364`, `7X482917364`), embora a DTDC também use outros prefixos de uma letra com 9 ou 11 caracteres.

### O que significa `RTO Booked` ou `Return Initiated` na DTDC?

`RTO` significa Return To Origin (devolução à origem). Indica que o pacote não pôde ser entregue (ou teve aprovação de devolução solicitada) e está sendo devolvido ao remetente. Avise o vendedor imediatamente se ainda quiser o pedido.

### Quanto tempo leva o envio internacional da DTDC?

Em [dtdc.com/international-shipping/](https://www.dtdc.com/international-shipping/), a DTDC informa 4–5 dias no *WorldWyde Express Premium*, 7–8 dias no *Express Standard*, 10–12 dias no *Imports Standard* e 15–20 dias no *B2C Standard*, sujeitos ao desembaraço aduaneiro.

### Como entro em contato com o suporte da DTDC?

Ligue para `+91-9606 911 811`, envie e-mail para `customersupport@dtdc.com` ou utilize o formulário e a assistente DIVA em [dtdc.com/customer-care/](https://www.dtdc.com/customer-care/).

:::sources
- [DTDC: Home](https://www.dtdc.com/in/) – aviso de site oficial (só `www.dtdc.com`), assistente DIVA e visão geral dos serviços
- [DTDC: About Us](https://www.dtdc.com/about-us/) – fundada em 26 de julho de 1990, sede em Bengaluru, mais de 16.500 parceiros de canal, alcance de 96% da população indiana, 7 países, mais de 220 destinos e mais de 5,6 milhões de usuários do MyDTDC
- [DTDC: Track Your Shipment](https://www.dtdc.com/track-your-shipment/) – consulta por AWB / referência, aviso do AWB por SMS/e-mail, contestação, chat e retorno telefônico
- [DTDC: Express Parcel](https://www.dtdc.com/express-parcel/) – mais de 15.300 pincodes, pagamento na entrega, autenticação na entrega e logística reversa
- [DTDC: International Shipping (WorldWyde)](https://www.dtdc.com/international-shipping/) – mais de 220 destinos, prazos WorldWyde (4–5, 7–8, 10–12, 15–20 dias) e desembaraço CSB-V/KYC
- [DTDC: FAQ](https://www.dtdc.com/faq/) – funcionamento das agências aos domingos e feriados e perguntas sobre rastreamento
- [DTDC: Customer Care](https://www.dtdc.com/customer-care/) – `+91-9606 911 811`, `customersupport@dtdc.com`, formulário on-line para pedidos de endereço, data e retirada (página de ajuda vinculada pelo Peek)
- [MyDTDC Portal](https://web.mydtdc.in/) – portal web de rastreamento MyDTDC
- [Universal Parcel Scraper: dtdc](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/dtdc) – detecção de alta confiança de `N`/`7D`/`7X`, regras de links `dtdc.com`, consulta em duas etapas, tratamento do trecho de devolução RTO, fuso `Asia/Kolkata`, campos descartados e URL de ajuda
:::
