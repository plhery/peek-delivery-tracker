---
title: Seguimento Purolator: PIN, estados e levantamentos
description: Segue uma encomenda Purolator no Canadá: formato do PIN, significado dos estados, levantamento após falha de entrega, alfândega dos EUA e contactos.
slug: seguimento-purolator
published: 2026-11-23
updated: 2026-11-23
---

A Purolator é uma transportadora canadiana de encomendas e carga detida maioritariamente pela Canada Post. Para seguir um envio, introduz o PIN em [purolator.com](https://www.purolator.com/en/shipping/tracker) ou cola-o na caixa acima. O PIN é enviado pelo remetente — numa compra online está no e-mail de confirmação de envio da loja.

## Número de seguimento Purolator: o que é o PIN e como é

A Purolator chama PIN (Package Identification Number) ao seu número de seguimento. Cada envio recebe um PIN, que a Purolator pede como referência em todos os contactos sobre essa encomenda. PIN e número de seguimento são, portanto, a mesma coisa.

| Formato (inventado) | O que é |
| --- | --- |
| `482917364051` | 12 dígitos; o Peek verifica o último como dígito de controlo. |
| `BKM482917364` | 3 letras e 9 dígitos; o seguimento da Purolator também classifica este formato como PIN. |

O seguimento da Purolator aceita ainda uma referência até 15 caracteres se o remetente tiver definido uma (número de pedido, código ou frase), funcionando tal como um PIN.

Doze dígitos é um comprimento muito comum: DPD France, Mondial Relay, Colis Privé, BRT, FedEx e várias transportadoras chinesas também usam 12 dígitos, pelo que deves confirmar a transportadora no e-mail da loja. Um número de 16 dígitos nunca é da Purolator (costuma ser da [Canada Post](carrier:canada-post)), e 13 caracteres terminados em `CA` (`RR482917367CA`) pertencem à Canada Post. Vê [formatos de números de seguimento](guide:tracking-number-formats).

Um envio pode receber um novo PIN pelo caminho: o histórico apresenta então um registo com o novo número.

## O que significam os estados de seguimento da Purolator

O site mostra um título principal (`Shipment Created`, depois `Delivered`) acima da lista de registos. Eis os textos em inglês (o site em francês apresenta-os em francês):

| Estado Purolator | O que significa para ti |
| --- | --- |
| `Shipper created a label` | O remetente criou a etiqueta. A Purolator ainda não tem a encomenda. |
| `Label information electronically submitted` | O mesmo: os dados da etiqueta chegaram à Purolator, mas a encomenda ainda não. |
| `Picked up by Purolator` | Recolhida pela Purolator. |
| `Arrived at sort facility` | Chegou a um terminal Purolator; o local indica a cidade e a província do terminal. |
| `Departed sort facility` | Saiu do terminal rumo ao seguinte ou ao teu depósito local. |
| `Delayed in transit due to missed connection` | Atraso interno na rede Purolator; a encomenda continua em trânsito. |
| `Cleared customs` | Vinda do estrangeiro: libertada pela alfândega e de novo a caminho. |
| `On vehicle for delivery` | Na carrinha de distribuição hoje. |
| `Attempted delivery - receiver unavailable` | O estafeta passou mas ninguém pôde receber. |
| `Address correction required - missing entry code` | O estafeta não conseguiu entrar no edifício. |
| `Available for pickup for 5 business days from arrival date at the counter` | Disponível para levantamento ao balcão. |
| `Shipment delivered` | Entregue a ti ou levantada por ti ao balcão. |

`Delayed in transit due to rail delay` funciona como a ligação perdida: um atraso da própria rede Purolator, sem problemas com a tua encomenda. `Address correction required - resolution in progress` significa que a Purolator está a corrigir a morada, seguindo-se frequentemente `Shipment scheduled for delivery next business day`.

`Shipment unavailable - En route to Purolator pickup location` indica que a encomenda segue para o balcão onde ficará guardada: só podes levantá-la quando surgir `Available for pickup…`.

## Tentativa de entrega, levantamento e assinaturas na Purolator

Quando não está ninguém em casa, o seguimento mostra tipicamente `Attempted delivery - receiver unavailable`, depois `Available for pickup for 5 business days from arrival date at the counter` na mesma cidade e `Shipment delivered` após levantamento. O prazo de 5 dias úteis consta no próprio texto da Purolator, por isso não deixes passar o tempo.

Os balcões usados para encomendas retidas são os Purolator Shipping Centres, agentes autorizados e lojas PostNet. O Guia de Serviços da Purolator indica mais de 130 Shipping Centres e mais de 550 agentes autorizados no Canadá, pesquisáveis no [localizador de centros](https://www.purolator.com/fr/expedition/trouver-un-centre-dexpedition).

As assinaturas dependem da opção escolhida pelo remetente:

- **Sem assinatura** é o padrão para moradas residenciais. «Signature required» é uma opção do remetente.
- **Adult signature required:** tens de mostrar documento oficial com fotografia e prova de idade à porta. Sem ele a encomenda não é entregue e segue para um Shipping Centre ou agente, onde pedem documento oficial com foto e morada válida. Estas encomendas nunca ficam à porta.

O remetente pode também escolher «Hold for pickup» para enviar diretamente para um centro Purolator em vez da tua morada.

## Seguimento Purolator parado, atrasado ou em falta

Sem resultados significa habitualmente que a Purolator ainda não registou a encomenda: a ajuda da Purolator indica que os detalhes só aparecem depois de receber e ler fisicamente o volume. Confirma também os dígitos — um único erro impede encontrar o envio.

Os atrasos figuram como registos próprios (ligação perdida, atraso ferroviário); a ajuda mais antiga refere também atrasos meteorológicos e mecânicos. A encomenda segue viagem depois. Vê [seguimento que não atualiza](guide:tracking-not-updating).

Perdida, danificada ou dada como entregue sem a receberes? Contacta primeiro a loja: o remetente detém o contrato com a Purolator e indica como abrir reclamação.

Bens enviados por transportadora dos Estados Unidos ou México podem beneficiar de isenção de direitos aduaneiros até 150 CAD e de impostos sobre vendas e consumo até 40 CAD segundo a Canada Border Services Agency (CBSA). Não é permitido dividir uma compra em vários volumes para ficar abaixo desses limites. O importador paga direitos e impostos, e a CBSA não regula as taxas de desalfandegamento (brokerage fees), que cabem à transportadora. Vê [encomendas na alfândega](guide:customs).

## Telefone de apoio ao cliente da Purolator

O número da Purolator no Canadá segundo o Guia de Serviços em vigor desde 30 de junho de 2026 é **1 888 SHIP-123 (1 888 744-7123)**. Tem o PIN à mão: a Purolator pede-o em todos os contactos.

Recebeste um SMS de entrega falhada com ligação? Não cliques: introduz tu próprio o PIN em purolator.com. O Governo do Canadá recomenda reencaminhar SMS de spam para 7726 (SPAM).

## Seguir encomendas Purolator com o Peek

O [Peek](/) consulta 3 letras e 9 dígitos (exceto alguns prefixos de outras transportadoras) em simultâneo na Colis Privé, na TNT Australia e na Purolator, sem assumir Purolator só pelo formato; quando a Purolator confirma o número, o Peek mostra «Purolator tem esta encomenda.». Um número de 12 dígitos também é partilhado: se passar o dígito de controlo, o Peek pergunta a seis transportadoras — Purolator, BRT, Colis Privé, Sending, Paquetexpress e Metropolitan Warehouse —, a cinco em simultâneo e à sexta se nenhuma dessas cinco o conhecer (a Purolator em primeiro lugar para leitores no Canadá), e escolhe a que conhece o número. Se várias marcas distintas responderem ou nenhuma o conhecer, escolhe Purolator em «Alterar». Ligações de purolator.com com `pin=`, `pins=` ou `searchValue=` também funcionam.

O Peek mostra o estado, cada registo com cidade, província e país no mapa, o dia previsto de entrega, o país de destino e, em envios de volume único, o peso. Os estados mantêm o inglês da Purolator. O nome do destinatário e o balcão exato ficam de fora (aparece apenas `Available for pickup…`), e o Peek não permite alterar a entrega.

As horas ajustam-se ao fuso horário do teu dispositivo. Em envios transfronteiriços, a Purolator envia-as sem fuso horário: o Peek lê-as então na hora da cidade do registo mantendo o histórico da Purolator. Se o site da Purolator responder por vezes com uma verificação em vez de dados, os serviços de seguimento preenchem a falha. Enquanto mantiveres a encomenda aberta no Peek, ou no dia da entrega com as notificações ligadas, o Peek verifica até de 10 em 10 minutos — e a cada 2 minutos a partir das 6h00 (hora local) no dia em que a Purolator a regista em distribuição, enquanto o seguimento próprio da Purolator responder (através de serviços de seguimento, nesse dia até de 15 em 15 minutos enquanto a tiveres aberta ou com as notificações ligadas). Caso contrário, verifica de hora a hora. Após dois dias sem novidades (12 horas enquanto continuar em «Etiqueta criada» ou «Aceite pela transportadora»), verifica de hora a hora mesmo que a tenhas aberta, e a cada 6 horas após sete dias em «Etiqueta criada». Se uma encomenda em «Etiqueta criada», «Aceite pela transportadora» ou «Em trânsito» passar quatro dias sem novos registos, o Peek mostra «Sem atualizações há quatro dias» (ou «Sem atualizações desde…» com a data), «É habitual uma encomenda ficar sem novidades em voos longos, na alfândega ou ao fim de semana. O Peek continua a perguntar a Purolator.» e a ligação «Pede ajuda a Purolator». O Peek pode avisar-te com «Todos os registos», «Passos importantes» ou «Só a entrega».

## Perguntas sobre o seguimento Purolator

### Um PIN da Purolator é o mesmo que um número de seguimento?

Sim. PIN é o nome que a Purolator dá ao seu número de seguimento, geralmente 12 dígitos ou 3 letras e 9 dígitos.

### Porque não funciona o meu número de seguimento Purolator?

Na maioria das vezes a Purolator ainda não registou a encomenda: o seguimento começa após receção física. Caso contrário confirma se há gralhas, pergunta à loja qual a transportadora ou procura um novo PIN no histórico.

### Quanto tempo guarda a Purolator uma encomenda para levantamento?

5 dias úteis a contar do dia de chegada ao balcão, conforme indica o estado `Available for pickup for 5 business days from arrival date at the counter`. Leva identificação oficial com foto se exigir assinatura de adulto.

### É possível seguir Purolator sem número de seguimento?

Apenas com a referência de seguimento do remetente, se este definiu uma: o site da Purolator aceita-a como um PIN. Caso contrário pede o PIN à loja.

:::sources
- [Purolator: Tracker](https://www.purolator.com/en/shipping/tracker) – formatos PIN, textos de estados, títulos, guarda de 5 dias úteis
- [Purolator: Trouver un centre d'expédition](https://www.purolator.com/fr/expedition/trouver-un-centre-dexpedition) – localizador de centros de envio
- [Purolator: Guide des services de Purolator](https://web.purolator.com/app/guides/pdf/fr/Purolator_Service_Guide_French.pdf) – telefone, Shipping Centres e agentes, Hold for pickup, assinaturas, assinatura de adulto
- [Purolator: Tracking Details](https://eshiponline.purolator.com/ShipOnline/Help/Default/trackingdetails_E.htm) – sem registos antes da recolha, novo PIN, atrasos meteorológicos e mecânicos
- [Purolator: Tracking - By PIN / By Reference](https://eshiponline.purolator.com/ShipOnline/Help/Default/trackingRef_E.htm) – seguimento por PIN e referência de 15 caracteres
- [Purolator: Preparing a Shipment - Shipment Confirmation](https://eshiponline.purolator.com/ShipOnline/Help/Default/shipstep3_E.htm) – definição de PIN
- [CBSA: Memorandum D8-2-16](https://www.cbsa-asfc.gc.ca/publications/dm-md/d8/d8-2-16-eng.html) – limites de 150 CAD e 40 CAD, proibição de fracionar
- [CBSA: Step 4, import guide](https://www.cbsa-asfc.gc.ca/import/guide-4-eng.html) – importador paga, brokerage fees não reguladas
- [Government of Canada: Mobile spam](https://ised-isde.canada.ca/site/canada-anti-spam-legislation/en/hung-mobile-spam) – reencaminhar SMS spam para 7726
- [Wikipedia: Purolator Inc.](https://en.wikipedia.org/wiki/Purolator_Inc) – participação maioritária da Canada Post
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – dados lidos pelo Peek, dígitos de controlo, candidatos e serviços de seguimento
:::
