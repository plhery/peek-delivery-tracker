---
title: Rastreio Korea Post: códigos EMS KR, 13 dígitos e ajuda
description: Rastreia uma encomenda Korea Post (ePOST): códigos S10 em KR, números internos de 13 dígitos, estados EMS e coreanos, alfândega, prazos e apoio.
slug: rastreio-korea-post
published: 2026-11-24
updated: 2026-11-24
---

A Korea Post (*우정사업본부*, portal online *ePOST* / *인터넷우체국*) é o operador postal nacional da Coreia do Sul, sob o Ministério da Ciência e TIC. Assegura entregas internas de encomendas e correio registado em toda a Coreia do Sul, bem como serviços internacionais **EMS** (Express Mail Service para mais de 100 destinos, além de mais de 70 países via EMS Premium), **K-Packet** e correio registado ou encomendas aéreas internacionais. Podes verificar envios internacionais no [rastreio EMS em inglês da Korea Post](https://trace.epost.go.kr/xtts/tt/epost/ems/ems_eng.jsp), consultar encomendas internas no [rastreio nacional ePOST](https://service.epost.go.kr/iservice/usr/trace/usrtrc001k01.jsp) ou colar o número de rastreio na caixa acima.

## Números de rastreio Korea Post: formato S10 em KR e 13 dígitos nacionais

Procura o código no recibo dos correios, na página da encomenda da loja coreana ou no e-mail de confirmação de envio. A Korea Post usa dois formatos principais consoante o envio seja internacional ou dentro da Coreia do Sul:

| Formato (inventado) | O que é |
| --- | --- |
| `EM482917367KR` | Envio internacional **EMS** de mercadorias (formato UPU S10 terminado em `KR`; documentos começam normalmente por `EE`). |
| `RR482917367KR` | Correio registado internacional ou envio K-Packet (formato UPU S10: 2 letras + 9 dígitos com dígito de controlo + `KR`). |
| `6482917364058` | Número interno sul-coreano de 13 dígitos para correio registado ou encomenda (começa por um dígito de `1` a `6`). Partilha o formato numérico de 13 dígitos com [Correos de Chile](carrier:correos-chile), [Delhivery](carrier:delhivery), [Econt](carrier:econt), [Paquetexpress](carrier:paquetexpress) e outras transportadoras. |

Na [apresentação EMS da Korea Post](https://ems.epost.go.kr/front.Introduction01.postal), o prefixo `EE` identifica documentos EMS (isentos de inspeção aduaneira de mercadorias), enquanto o prefixo `EM` identifica envios EMS de mercadorias. Para outros códigos postais internacionais, consulta [formatos de números de rastreio](guide:tracking-number-formats).

## O que significam os estados de rastreio da Korea Post

Os envios internacionais `KR` apresentam registos UPU/EMS em inglês com a hora local comunicada pelo operador postal de cada país, enquanto os envios internos de 13 dígitos mostram descrições em coreano na hora padrão da Coreia (`Asia/Seoul`, UTC+9 todo o ano):

| A Korea Post indica (internacional / nacional) | O que significa para ti | O Peek mostra |
| --- | --- | --- |
| `운송장출력` *(nacional)* | Guia de transporte impressa antes da aceitação no balcão postal. | «Etiqueta criada» |
| `Posting/Collection` / `접수`, `집하완료`, `인수완료` | Aceite num posto dos correios coreanos ou recolhida por um estafeta da Korea Post. | «Aceite pela transportadora» |
| `Arrival at outward office of exchange`, `Ready for dispatch`, `Handed over to a transport company`, `Received by Air carriers`, `Departure from the Airport`, `Arrival at Destination Airport`, `Delivered to Destination Post`, `Arrival at inward office of exchange`, `Departure from inward office of exchange` / `발송`, `도착` | Processada no centro de permuta internacional da Coreia (como Incheon), expedida por via aérea, entregue aos correios do país de destino ou transferida entre centros de triagem nacionais. | «Em trânsito» |
| `Ready for customs clearance` | Apresentada à alfândega no país de destino (ou controlo aduaneiro de exportação/importação). | «Na alfândega» |
| `배달준비` *(nacional)* | Preparada para distribuição final pelo carteiro da estação local na Coreia. | «Em distribuição» |
| `Unsuccessful delivery` | A tentativa de entrega no destino não pôde ser concluída. | «Tentativa de entrega» |
| `Delivery complete` / `배달완료` | Entregue; o rastreio da Korea Post pode indicar quem recebeu a encomenda. | «Entregue» |

## Prazos EMS da Korea Post, alfândega e entrega local

De acordo com a [visão geral do serviço EMS da Korea Post](https://ems.epost.go.kr/front.Introduction01.postal), as [condições de envio por país](https://ems.epost.go.kr/front.EmsApplyGoCondition.postal) e a [calculadora de prazos e tarifas](https://ems.epost.go.kr/front.EmsDeliveryDelivery09.postal):

- **Prazos de trânsito EMS:** para documentos (códigos `EE`) entregues em Seul até ao meio-dia, a Korea Post indica o dia seguinte ou 3–4 dias para cidades com voos diretos, como Tóquio, Hong Kong e Singapura, e 4–6 dias para outros destinos; a página geral indica 2–3 e 3–5 dias para documentos depositados em Seul de manhã. Soma um dia para envios entregues após o meio-dia ou fora de Seul, ou destinados fora da cidade do centro de permuta de destino; a partir de estações em zonas de montanha ou ilhas, somam-se os dias até ao International Mail Logistics Center. As mercadorias (códigos `EM`) aguardam ainda a inspeção aduaneira no país de destino. O dia de expedição e os feriados não contam.
- **Declarações aduaneiras:** documentos e impressos usam declaração CN22, enquanto mercadorias e artigos sujeitos a direitos exigem declaração CN23 (além de fatura comercial e eventuais licenças para envios comerciais). Os artigos proibidos ou restritos constam do [guia de artigos proibidos e restritos do ePOST](https://ems.epost.go.kr/front.Introduction04.postal).
- **Entrega pelos correios de destino:** assim que o envio `KR` chega ao teu país (`Delivered to Destination Post` / `Arrival at inward office of exchange`), o operador postal nacional (por exemplo [USPS](carrier:usps), [Royal Mail](carrier:royal-mail), [La Poste](carrier:la-poste), [DHL](carrier:dhl), [Japan Post](carrier:japan-post) ou [Australia Post](carrier:australia-post)) trata da cobrança de encargos aduaneiros, das tentativas de entrega e do prazo para levantamento segundo as suas próprias regras. Rastreia o mesmo código `...KR` também no site dos teus correios locais para ver opções de entrega mais detalhadas. Vê [encomendas retidas na alfândega](guide:customs).

## Encomenda Korea Post atrasada, parada em trânsito ou desaparecida

- **Pausa sem registos após `Departure from the Airport`:** os despachos internacionais EMS e K-Packet ficam frequentemente sem novos registos durante vários dias ou mais de uma semana entre a saída da Coreia do Sul e o registo de entrada no centro de permuta do país de destino (`Arrival at inward office of exchange`). Vê [por que razão o rastreio deixa de atualizar](guide:tracking-not-updating).
- **Retida em `Ready for customs clearance`:** verifica o mesmo número `...KR` no site dos correios do teu país para confirmar se existe IVA de importação, direitos aduaneiros ou uma taxa de apresentação por pagar.
- **Envios perdidos, danificados ou em falta:** em compras online, contacta primeiro o vendedor ou marketplace coreano; o ePOST aceita pedidos de pesquisa (*행방조사청구*) online ou por escrito e publica as regras de indemnização (*손해배상규정*). Vê [entregue mas não recebida](guide:delivered-not-received), [encomenda danificada](guide:damaged-parcel), [encomenda perdida](guide:lost-parcel) e [devolvida ao remetente](guide:returned-to-sender).

## Apoio ao cliente e contactos da Korea Post

Contactos oficiais publicados no [Centro de Apoio ePOST](https://www.epost.go.kr/bbs.RetrieveBbsMain.comm), no [portal principal ePOST](https://www.epost.go.kr/main.retrieveMainPage.comm) e na [apresentação do serviço EMS](https://ems.epost.go.kr/front.Introduction01.postal):

- **Centro de Apoio Korea Post (na Coreia do Sul):** `1588-1300` (dias úteis `09:00–18:00` KST, encerrado aos sábados e feriados; chamada taxada).
- **A partir do estrangeiro:** `+82-42-609-4295`, no mesmo horário.
- **E-mail:** `help@posa.or.kr`.
- **Perguntas frequentes e pedidos online:** [Centro de Apoio ePOST](https://www.epost.go.kr/bbs.RetrieveBbsMain.comm) e a opção *e-mail EMS Inquiry* em [trace.epost.go.kr](https://trace.epost.go.kr/xtts/tt/epost/ems/ems_eng.jsp).
- **Morada no rodapé do ePOST:** 1 Jeongbohwa-gil (Bitgaram-dong), Naju, 58323, Coreia do Sul.

> Ignora SMS falsas a dizer que a tua encomenda Korea Post não pode ser entregue sem pagares uma pequena taxa através de um link não oficial. Encargos reais de importação numa encomenda recebida com código `KR` são cobrados pelos correios ou portal aduaneiro do teu país, nunca por links aleatórios por SMS. Vê [SMS fraudulentas de entregas](guide:delivery-scam-texts).

## Rastrear uma encomenda Korea Post com o Peek

[O Peek](/) reconhece automaticamente com elevada confiança qualquer número de rastreio UPU S10 terminado em `KR` (`EM482917367KR` ou `RR482917367KR`, dígitos inventados) como Korea Post, e reconhece também links colados de `trace.epost.go.kr` com `POST_CODE=` e de `service.epost.go.kr` com `sid1=`. Um número interno de 13 dígitos (`6482917364058`) corresponde a várias transportadoras: o Peek indica a Korea Post entre elas e pede-te para escolher. Também a podes selecionar depois com «Alterar transportadora» no ecrã da encomenda (pesquisar «South Korea Post» ou «ePOST» também a encontra), ou colar o link do ePOST.

O Peek lê o rastreio EMS em inglês da Korea Post para códigos `KR` e o rastreio interno coreano do ePOST para códigos de 13 dígitos. Mostra a etapa («Etiqueta criada», «Aceite pela transportadora», «Em trânsito», «Na alfândega», «Em distribuição» em encomendas internas, «Tentativa de entrega», «Entregue»), a descrição do registo com o posto postal ou aeroporto e a hora de entrega em encomendas internas. Os registos internos usam a hora da Coreia e os internacionais o fuso horário do respetivo local (a hora de Nova Iorque num registo em Nova Iorque, por exemplo); o Peek converte-os para a hora do teu dispositivo. O nome do destinatário indicado pela Korea Post surge em «Destinatário» na tua própria página da encomenda, nunca num link partilhado; o nome de quem assina fica de fora em todas as apps do Peek, e o Peek omite o nome do serviço, o nome do remetente e os telefones das estações. O rastreio da Korea Post não indica data estimada de entrega, peso nem ponto de levantamento.

Enquanto tiveres a encomenda aberta no Peek, ou no dia da entrega com as notificações ativadas, o Peek consulta até de 10 em 10 minutos; no dia em que uma encomenda interna mostra `배달준비`, de 2 em 2 minutos a partir das 6h na hora coreana, enquanto o rastreio da Korea Post responder. Caso contrário, consulta de hora a hora. Após 12 horas apenas em «Etiqueta criada» ou «Aceite pela transportadora», ou dois dias sem novidades, passa a verificar de hora a hora seja qual for o modo de acompanhamento, e de 6 em 6 horas após sete dias em «Etiqueta criada». Quando o rastreio da Korea Post não responde e o Peek acompanha a encomenda apenas através de serviços de rastreio, verifica de hora a hora, de 3 em 3 horas após um dia sem registos e até de 15 em 15 minutos no dia da entrega enquanto tiveres a encomenda aberta ou as notificações ativadas.

Quando o registo mais recente tem quatro dias e a encomenda continua em «Etiqueta criada», «Aceite pela transportadora» ou «Em trânsito», o Peek assinala-o com o dia desse registo, como «Sem atualizações desde seg., 12/10». A página da encomenda mostra então «É habitual uma encomenda ficar sem novidades em voos longos, na alfândega ou ao fim de semana. O Peek continua a perguntar a Korea Post.», com o link «Pede ajuda a Korea Post» para o Centro de Apoio do ePOST. Após três semanas sem novos registos, mostra «Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, contacta o vendedor. O Peek continua a perguntar a Korea Post.»

Podes escolher alertas para «Todos os registos», «Passos importantes» ou «Só a entrega». O Peek acompanha apenas as atualizações de rastreio e não pode desalfandegar encomendas, redirecionar entregas nem apresentar um pedido de pesquisa EMS junto da Korea Post em teu nome.

## Perguntas frequentes sobre o rastreio Korea Post

### O que significam `EE...KR`, `EM...KR` e `RR...KR`?

São números postais internacionais UPU S10 de 13 caracteres expedidos a partir da Coreia do Sul: `EE` é usado para documentos EMS, `EM` para encomendas EMS com mercadorias e `RR` (ou outros prefixos `R`/`C`/`L`) para correio registado, K-Packet e encomendas internacionais.

### Porque é que a minha encomenda EMS da Korea Post parou de atualizar depois de sair do aeroporto?

Quando surge `Departure from the Airport` na Coreia do Sul, o rastreio fica sem novos registos até os correios do país de destino registarem o despacho recebido no centro de permuta de entrada (`Arrival at inward office of exchange`). Consulta também o mesmo número `...KR` no site dos teus correios nacionais.

### A Korea Post faz a entrega diretamente no estrangeiro?

A Korea Post assegura a recolha, a triagem de exportação e a expedição aérea a partir da Coreia do Sul, entregando depois a encomenda (`Delivered to Destination Post`) ao serviço postal nacional do país de destino para desalfandegamento e distribuição final.

### Como posso contactar o apoio ao cliente da Korea Post?

Liga para `1588-1300` dentro da Coreia do Sul (seg.–sex. `09:00–18:00` KST, encerrado aos sábados e feriados), envia e-mail para `help@posa.or.kr` ou utiliza o [Centro de Apoio ePOST](https://www.epost.go.kr/bbs.RetrieveBbsMain.comm).

:::sources
- [Korea Post: rastreio EMS em inglês](https://trace.epost.go.kr/xtts/tt/epost/ems/ems_eng.jsp) – portal internacional de rastreio EMS e correios parceiros de destino
- [Korea Post (ePOST): rastreio nacional](https://service.epost.go.kr/iservice/usr/trace/usrtrc001k01.jsp) – rastreio interno de 13 dígitos para encomendas e correio registado
- [Korea Post (ePOST): visão geral do serviço EMS](https://ems.epost.go.kr/front.Introduction01.postal) – prefixos `EE` e `EM`, prazos EMS, regras aduaneiras CN22/CN23, mais de 100 destinos EMS / mais de 70 EMS Premium e `1588-1300`
- [Korea Post (ePOST): condições de envio por país](https://ems.epost.go.kr/front.EmsApplyGoCondition.postal) – limites EMS e notas aduaneiras por país
- [Korea Post (ePOST): calculadora de prazos e preços](https://ems.epost.go.kr/front.EmsDeliveryDelivery09.postal) – calculadora para EMS, EMS Premium e encomendas
- [Korea Post (ePOST): artigos proibidos e restritos](https://ems.epost.go.kr/front.Introduction04.postal) – restrições a envios internacionais
- [Korea Post (ePOST): Centro de Apoio](https://www.epost.go.kr/bbs.RetrieveBbsMain.comm) – `1588-1300`, `help@posa.or.kr`, horário em dias úteis `09:00–18:00` KST e morada em Naju (URL de ajuda ligado pelo Peek)
- [Korea Post (ePOST): portal principal](https://www.epost.go.kr/main.retrieveMainPage.comm) – resumo de encomendas internas, correio registado, EMS e K-Packet, e `+82-42-609-4295` a partir do estrangeiro
- [Universal Parcel Scraper: korea-post](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/korea-post) – deteção `KR` S10 e 13 dígitos, regras de links `trace.epost.go.kr` / `service.epost.go.kr`, mapas de estados EMS em inglês e nacional coreano, fusos horários, campos omitidos e URL de ajuda
:::
