---
title: Rastreio HayPost: códigos AM, estados e contactos
description: Segue uma encomenda HayPost Arménia: números S10 AM, estados IPS internacionais e nacionais, serviços postais, contactos em Erevan e rastreio Peek.
slug: rastreio-haypost
published: 2026-11-24
updated: 2026-11-24
---

A HayPost (*«Haypost» Closed Joint-Stock Company*, em arménio *Հայփոստ*) é o operador postal nacional da República da Arménia. Fundada em 1994 e reorganizada em 1998 como sociedade anónima fechada estatal (CJSC), gere cerca de 777 estações de correios na Arménia, 23 centros regionais e um centro de permuta postal internacional, operando no âmbito da União Postal Universal (UPU) e da PostEurop. Podes consultar um envio na [página Track & Trace da HayPost](https://www.haypost.am/en/track-trace), ou colar o código de rastreio na caixa acima.

## Números de rastreio HayPost: formato S10 e onde os encontrar

O número de rastreio aparece no talão dos correios ou no e-mail de expedição da loja online. A HayPost acompanha códigos de barras padrão UPU S10 de 13 caracteres com dígito de controlo:

| Formato (inventado) | O que é |
| --- | --- |
| `RR482917367AM` | Carta registada internacional ou pequeno pacote enviado da Arménia (`R` + segunda letra + 9 dígitos + `AM`). |
| `CP482917367AM` | Encomenda postal enviada da Arménia (`CP` + 9 dígitos + `AM`). |

O rastreio da HayPost consulta igualmente códigos UPU S10 recebidos de outros países (terminados no código ISO de duas letras do país de origem, como `US`, `DE` ou `CN`) assim que os correios de origem enviam dados eletrónicos de expedição ou o objeto entra na Arménia. O correio simples sem registo não tem código S10 e não pode ser seguido online. Consulta [formatos de números de rastreio](guide:tracking-number-formats) se a tua encomenda usar uma transportadora privada.

## O que significam os estados de rastreio HayPost

A HayPost devolve linhas de registo em inglês da União Postal Universal (IPS) identificadas pelo troço do percurso: `(Otb)` na saída do país de origem, `(Inb)` na entrada no país de destino ou `(EDI-received)` para dados eletrónicos de expedição transmitidos pelos correios de origem.

| A HayPost mostra | O que significa para ti | O Peek mostra |
| --- | --- | --- |
| `Receive item from customer (Otb)` | Entregue pelo remetente num posto de correios. | «Aceite pela transportadora» |
| `Insert item into bag (Otb)`, `Send item abroad (EDI-received)`, `Receive item at office of exchange (Otb)` / `(Inb)` | Acondicionada para expedição internacional, comunicada ao estrangeiro por dados eletrónicos ou chegada à estação de permuta internacional. | «Em trânsito» |
| `Send item to customs (Otb)` | Desalfandegamento de exportação na estação de permuta de saída antes de deixar o país de origem. | «Em trânsito» |
| `Send item to customs (Inb)`, `Record item customs information (Inb)` | Retida ou inspecionada pela alfândega de importação no destino. | «Na alfândega» |
| `Return item from customs (Inb)` | Libertada pela alfândega e devolvida à rede postal (não é devolução ao remetente). | «Em trânsito» |
| `Send item to domestic location (Inb)`, `Receive item at location (Inb)`, `Receive item at sorting centre (Inb)`, `Item was delivered to the postal office (Inb)` | Em trânsito pelos centros de triagem nacionais até chegar ao posto de correios local encarregado da entrega. | «Em trânsito» |
| `Send item out for physical delivery (Inb)` | Em distribuição hoje com o carteiro. | «Em distribuição» |
| `Unsuccessful item delivery attempt (Inb)`, `The notification was sent to the customer: ...` | Entrega não concluída; a HayPost acrescenta frequentemente o motivo e o passo seguinte (por exemplo `Addressee not available; Scheduled for further action/processing today`). | «Tentativa de entrega» |
| `Receive item at collection point for pick-up (Inb)` | Pronta para levantamento no posto de correios. | «Pronta para levantamento» |
| `Deliver item (Inb)` | Entregue e registada como entregue. | «Entregue» |

Repara em duas expressões fáceis de interpretar mal. Primeiro, `Return item from customs (Inb)` é boa notícia: a alfândega concluiu a verificação e devolveu o volume aos correios para seguir viagem. Segundo, `Item was delivered to the postal office (Inb)` (muitas vezes retransmitida por correios estrangeiros) significa que a estação postal local recebeu o objeto à sua guarda, não que foi entregue a ti.

## Serviços nacionais, internacionais e expresso da HayPost

Na sua rede de cerca de 777 estações de correios ([Sobre a HayPost](https://www.haypost.am/en/about-us)), a HayPost disponibiliza:

- **Serviços postais nacionais ([haypost.am/en/postal-services/domestic-postal-services](https://www.haypost.am/en/postal-services/domestic-postal-services)):** postais, cartas, pequenos pacotes até 2 kg, encomendas até 20 kg, **Yerevan Express Service (YES)** para entregas com rastreio entre estações ou ao domicílio em Erevan (pedidos entre 09:00 e 16:00 tratados no próprio dia; após 16:00 no dia útil seguinte; pedidos de sábado na segunda-feira; linha de marcação `+374 10 514 513`) e **HayPost Sprint** para envios nacionais de comércio eletrónico com rastreio até 2 kg.
- **Serviços postais internacionais ([haypost.am/en/postal-services/international-delivery](https://www.haypost.am/en/postal-services/international-delivery)):** cartas internacionais, pequenos pacotes até 2 kg (comprimento + largura + espessura ≤ 0,9 m, lado mais comprido ≤ 0,6 m), encomendas até 20 kg (qualquer lado ≤ 1,05 m), sacos M até 30 kg e correio acelerado EMS.
- **HayPost Express para os Estados Unidos ([haypost.am/en/postal-services/Haypost-Express](https://www.haypost.am/en/postal-services/Haypost-Express)):** entrega aérea de encomendas a partir de qualquer estação postal arménia para Los Angeles (estações de levantamento em Glendale, Van Nuys e Sylmar), até 30 kg, com tempo médio de 7 a 10 dias úteis.

**FAQ da HayPost.** As [FAQ da HayPost](https://www.haypost.am/en/faq) listam perguntas sobre prazos de entrega nacional, taxas aduaneiras, indemnizações e guarda de envios EMS; para os valores concretos da tua encomenda, pergunta no teu posto de correios ou à HayPost.

## Encomendas HayPost atrasadas, na alfândega ou desaparecidas

Em compras online enviadas por via postal de ou para a Arménia, contacta primeiro o vendedor para reembolsos ou reenvios.

**Pausas entre países.** Após `Send item abroad (EDI-received)`, o correio internacional aguarda transporte aéreo, triagem à chegada e alfândega de importação na estação de permuta de destino, o que pode deixar lacunas de vários dias. Consulta [porque o rastreio deixa de atualizar](guide:tracking-not-updating).

**Desalfandegamento.** As encomendas recebidas na Arménia e os envios `AM` chegados a destinos como a UE ou os EUA passam pela alfândega de importação (`Send item to customs (Inb)`). Guarda a fatura de compra e o número de rastreio caso a alfândega peça prova de valor. Consulta [encomendas retidas na alfândega](guide:customs).

**Entregue, danificada ou devolvida.** Se surgir `Deliver item (Inb)` sem teres o objeto, confirma com familiares e no posto local (usando o [diretório da rede HayPost](https://www.haypost.am/en/our-network)), e fala depois com o vendedor e o apoio da HayPost. Consulta [entregue mas não recebida](guide:delivered-not-received), [encomenda danificada](guide:damaged-parcel), [encomenda perdida](guide:lost-parcel) e [devolvida ao remetente](guide:returned-to-sender).

## Apoio ao cliente e contactos HayPost

Contactos publicados em [haypost.am/en/contact-us](https://www.haypost.am/en/contact-us) (e [haypost.am/am/contact-us](https://www.haypost.am/am/contact-us)):

- **Telefone de apoio ao cliente:** `+374 10 514-514`.
- **Vendas e clientes empresariais:** `+374 10 544-444`.
- **Linha de pedidos YES Yerevan Express:** `+374 10 514 513`.
- **E-mail:** `info@haypost.am`.
- **Sede:** 65/5 Arshakunyats Ave., Erevan 0061, República da Arménia.
- **Formulário online:** em [haypost.am/en/contact-us](https://www.haypost.am/en/contact-us), com chat em direto em [haypost.am](https://www.haypost.am/en).

> Desconfia de SMS, mensagens de WhatsApp ou e-mails a pedir que cliques num link externo e introduzas dados do cartão bancário para pagar uma pequena taxa de «nova entrega» ou «confirmação de morada» de uma encomenda HayPost. Verifica o teu código S10 diretamente em [haypost.am/en/track-trace](https://www.haypost.am/en/track-trace) ou liga `+374 10 514-514`. Consulta [SMS falsas de entregas](guide:delivery-scam-texts).

## Segue uma encomenda HayPost com o Peek

Para códigos UPU S10 terminados em `AM` (como `RR482917367AM` ou `CP482917367AM`, dígitos inventados), o [Peek](/) segue automaticamente a HayPost como correios de origem preferenciais, e segue também códigos S10 recebidos na Arménia assim que selecionas HayPost. Como a HayPost não usa regras próprias de URL de rastreio no Peek, cola diretamente o código S10 de 13 caracteres; se precisares de escolher a HayPost manualmente, toca em «Alterar» ao adicionar ou em «Alterar transportadora» na página da encomenda e seleciona HayPost (pesquisar «Armenia Post» ou «Հայփոստ» também a encontra).

O Peek lê diretamente a API JSON pública da HayPost em inglês e mostra a etapa («Aceite pela transportadora», «Em trânsito», «Na alfândega», «Em distribuição», «Tentativa de entrega», «Pronta para levantamento», «Entregue»), o texto IPS (incluindo motivos de falha e passos seguintes como `Addressee not available`), posto/país (sem códigos postais numéricos), país de destino e hora de entrega. Os registos arménios e as linhas `Send item abroad (EDI-received)` usam a hora de Erevan, e os registos de correios estrangeiros o fuso horário desse local (hora francesa em Paris, por exemplo); o Peek apresenta-os na hora do teu dispositivo. Quando a HayPost publica antecipadamente uma linha planeada `Send item abroad (EDI-received)` com data futura (muitas vezes marcada horas à frente), o Peek omite essa linha até chegar a hora efetiva. A API da HayPost não devolve data prevista de entrega, peso, nome do serviço, dados de remetente/destinatário nem foto de prova de entrega.

Enquanto manténs a encomenda aberta no Peek, ou no dia da entrega com as notificações ativas, o Peek verifica até de 10 em 10 minutos; no dia em que a HayPost mostra `Send item out for physical delivery (Inb)`, de 2 em 2 minutos a partir das 6 h pela hora do registo mais recente da HayPost, enquanto o rastreio próprio da HayPost responder. Nos restantes casos verifica de hora em hora. Após 12 horas só em «Etiqueta criada» ou «Aceite pela transportadora», ou dois dias sem novidades, verifica de hora em hora seja como for, e de 6 em 6 horas após sete dias em «Etiqueta criada». Quando o rastreio da HayPost não responde e o Peek acompanha a encomenda apenas por serviços de rastreio, verifica de hora em hora, de 3 em 3 horas após um dia sem novidades e até de 15 em 15 minutos no dia de distribuição enquanto a mantiveres aberta ou com notificações ativas.

Quando o último registo tem quatro dias e a encomenda continua em «Etiqueta criada», «Aceite pela transportadora» ou «Em trânsito», o Peek assinala-o com a data desse registo, como «Sem atualizações desde seg 12 out». A página mostra então: «É habitual uma encomenda ficar sem novidades em voos longos, na alfândega ou ao fim de semana. O Peek continua a perguntar a HayPost.», com a ligação «Pede ajuda a HayPost» para a página de contactos da HayPost. Após três semanas sem novos registos, mostra: «Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, contacta o vendedor. O Peek continua a perguntar a HayPost.»

Podes escolher os alertas «Todos os registos», «Passos importantes» ou «Só a entrega». O Peek apenas acompanha o rastreio e não pode desalfandegar encomendas nem abrir pedidos de averiguação junto da HayPost por ti.

## Perguntas sobre o rastreio HayPost

### Como é um número de rastreio HayPost?

A HayPost usa códigos padrão UPU S10 de 13 caracteres: duas letras, nove dígitos e `AM` no fim para envios expedidos da Arménia (por exemplo `RR482917367AM` ou `CP482917367AM`, dígitos inventados). O correio internacional de entrada mantém as duas letras do país remetente.

### Porque diz a minha encomenda HayPost `Return item from customs (Inb)`?

Esse estado IPS significa que a alfândega libertou o objeto e o devolveu ao serviço postal para prosseguir viagem — não significa que a encomenda volte para o remetente.

### A HayPost consegue seguir encomendas enviadas para a Arménia do estrangeiro?

Sim. Qualquer código internacional UPU S10 com rastreio destinado à Arménia pode ser consultado em [haypost.am/en/track-trace](https://www.haypost.am/en/track-trace) ou no Peek assim que chegarem os dados eletrónicos de expedição ou a HayPost o registar na Arménia.

### Como contacto o apoio ao cliente da HayPost?

Liga para `+374 10 514-514` (ou `+374 10 544-444` para empresas), escreve para `info@haypost.am` ou usa o formulário em [haypost.am/en/contact-us](https://www.haypost.am/en/contact-us) ou o chat em direto no site da HayPost.

:::sources
- [HayPost: Home](https://www.haypost.am/en) – portal do operador postal nacional
- [HayPost: Track & Trace](https://www.haypost.am/en/track-trace) – rastreio de objetos online
- [HayPost: About Us](https://www.haypost.am/en/about-us) – fundada em 1994, CJSC desde 1998, ~777 estações postais, 23 centros regionais, membro da UPU e PostEurop
- [HayPost: Domestic Postal Services](https://www.haypost.am/en/postal-services/domestic-postal-services) – pequenos pacotes 2 kg, encomendas 20 kg, YES Yerevan Express Service (`+374 10 514 513`) e HayPost Sprint
- [HayPost: International Delivery](https://www.haypost.am/en/postal-services/international-delivery) – limites de pequeno pacote internacional (2 kg), encomenda (20 kg), sacos M (30 kg) e EMS
- [HayPost: HayPost Express](https://www.haypost.am/en/postal-services/Haypost-Express) – envio aéreo para Los Angeles (30 kg, 7–10 dias úteis)
- [HayPost: Our Network](https://www.haypost.am/en/our-network) – diretório da rede de estações arménias
- [HayPost: Contact Us](https://www.haypost.am/en/contact-us) – `+374 10 514-514`, `+374 10 544-444`, `info@haypost.am`, 65/5 Arshakunyats Ave., Erevan
- [HayPost: Contact Us (arménio)](https://www.haypost.am/am/contact-us) – URL oficial de ajuda ligado pelo Peek
- [HayPost: FAQ](https://www.haypost.am/en/faq) – índice de temas das FAQ
- [Universal Parcel Scraper: haypost](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/haypost) – deteção S10 `AM`, consulta JSON `api.haypost.am`, mapeamento de estados IPS, hora de Erevan, filtro EDI futuro e URL de ajuda
:::
