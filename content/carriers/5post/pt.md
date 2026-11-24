---
title: Rastreio 5Post: números, levantamento Pyaterochka e ajuda
description: Rastreia encomendas 5Post (FivePost / X5) na Rússia: números P Avito e códigos de loja, estados russos, levantamento Pyaterochka e contactos.
slug: rastreio-5post
published: 2026-11-24
updated: 2026-11-24
---

A 5Post (*5Пост* / *FivePost*, operada pelo X5 Group) é uma rede russa de encomendas de comércio eletrónico, envios entre particulares e logística que entrega pedidos online e encomendas Avito Delivery em mais de **25 700** cacifos (*постаматы*) e balcões de levantamento nas caixas dos supermercados **Pyaterochka** («Пятёрочка») e **Perekrestok** («Перекрёсток») em **4 950** cidades e localidades da Rússia, apoiada por **51** centros de triagem ([fivepost.ru](https://fivepost.ru/)). A 5Post trabalha com mais de 1 000 lojas online e marketplaces — incluindo Avito Delivery e encomendas internacionais da AliExpress — e opera ainda entrega ao domicílio por estafeta e carga LTL/FTL.

Podes consultar uma encomenda em [fivepost.ru](https://fivepost.ru/) (em *Где мой заказ?*) ou colar o número do pedido ou um link `fivepost.ru/tracking?id=...` na caixa acima.

## Formatos de números de rastreio 5Post

A 5Post rastreia os envios pelo número de pedido da loja ou marketplace — qualquer código contínuo de 3 a 40 letras e dígitos que inclua pelo menos um dígito:

| Formato (inventado) | O que é |
| --- | --- |
| `P48291736051` | `P` + 11 dígitos (12 caracteres), o formato dos códigos Avito Delivery. O Peek consulta a [Colis Privé](carrier:colis-prive) e a 5Post, primeiro a 5Post na Rússia. |
| `99994829173605` | 14 dígitos, número de pedido de uma loja online. O Peek nunca sugere a 5Post para este formato e consulta as empresas da DPD, Chronopost, SEUR, BRT e outras: escolhe 5Post manualmente ou cola o link da 5Post. |

Como as lojas escolhem os seus próprios números de pedido, dois pedidos podem coincidir. O site da 5Post pede então dígitos do telefone do destinatário, e o Peek não consegue distinguir os pedidos. Um link `fivepost.ru/tracking?id=...` associa de imediato a encomenda à 5Post. Para outros formatos, consulta o [guia de formatos de números de rastreio](guide:tracking-number-formats).

## O que significam os estados de rastreio da 5Post

O serviço de rastreio da 5Post combina códigos técnicos de estado com descrições em russo, indicando o armazém regional de triagem (`РЦ ...`) nos registos de processamento e expedição.

| Código 5Post e texto em russo | O que significa |
| --- | --- |
| `INFORMATION_RECEIVED` — `Ожидается передача заказа в 5post` | Dados do pedido registados; a 5Post aguarda que o vendedor ou remetente entregue a encomenda. |
| `PROCESS_WITHDRAWN` — `Передан службе доставки` | O remetente deixou ou entregou a encomenda à 5Post. |
| `WAREHOUSE_PROCESSING` — `Обрабатывается на складе ...` | Em processamento num armazém regional de triagem 5Post (`РЦ`). |
| `READY_FOR_SHIPMENT` — `Покинул склад ...` | Saiu do armazém de triagem rumo ao centro seguinte ou à loja de levantamento. |
| `DELIVERED` — `Доставлен в пункт выдачи` | Chegou à caixa Pyaterochka / Perekrestok escolhida ou ao cacifo 5Post — **pronta para levantamento**, ainda não levantada! |
| `DONE` — `Получен` | Levantada pelo destinatário (entregue). |
| `RECEIVED_BY_COURIER` — `Передан курьеру` | Em distribuição ao domicílio em pedidos com estafeta. |
| `RETURNING_BY_COURIER` — `Неуспешная попытка вручения` | Falhou uma tentativa de entrega por estafeta. |
| `CUSTOMS_CLEARANCE_STARTED` / `CUSTOMS_DATA_REQUIRED` / `CUSTOMS_AWAITING_PAYMENT` | Pedido vindo do estrangeiro (como AliExpress) em desalfandegamento, a aguardar os teus dados para o despachante aduaneiro ou a aguardar pagamento de taxas. |
| `CUSTOMS_CLEARANCE_COMPLETED` — `Таможенное оформление завершено` | A alfândega libertou a encomenda para prosseguir na 5Post. |
| `UNCLAIMED_...` / `RETURNED_TO_PARTNER` / `RETURNED_TO_CLIENT` | Não levantada dentro do prazo de guarda ou cancelada após entrega à transportadora, a regressar à loja ou ao remetente (`UNCLAIMED_READY_FOR_REPICKUP` indica preparação para voltar a um ponto de levantamento). |
| `INCEPTION_PROBLEM` / `LOST` / `CANCELLED` / `REGISTRATION_ERROR_...` | Problema no pedido, cancelamento, extravio ou falta de telefone / data de entrega / morada que requer correção. |

Repara num detalhe essencial dos códigos 5Post: `DELIVERED` (`Доставлен в пункт выдачи`) significa **pronta para levantamento** na loja ou no cacifo, ao passo que `DONE` (`Получен`) significa que já a levantaste.

## Levantamento na Pyaterochka, Perekrestok e cacifos

Um pedido para levantamento aguarda num cacifo 5Post (*постамат*) ou na caixa (*касса*) da loja Pyaterochka ou Perekrestok escolhida na compra; as perguntas frequentes da 5Post indicam os horários dos cacifos e pontos de levantamento:

- **Código de levantamento:** quando a encomenda chega ao ponto (`Доставлен в пункт выдачи`), a 5Post envia o código por SMS. Introduz o código no ecrã do cacifo ou indica-o na caixa. As perguntas frequentes da 5Post mostram onde encontrar o código e o que fazer se os funcionários não encontrarem o pedido.
- **Prolongar guarda e alterar telefone:** conforme indicado nas [FAQ da 5Post](https://fivepost.ru/faq/), podes consultar prazos de guarda, prolongar o prazo, pagar online no site ou na app móvel, ou ver como alterar o número de telefone, abrir uma porta encravada ou reportar um compartimento vazio ou errado.
- **Pedidos internacionais (AliExpress):** envios do estrangeiro tratados via 5Post incluem transporte aéreo (`DEPARTED_BY_AIR`, `DELIVERED_BY_AIR`) e desalfandegamento russo; se surgir `Требуется предоставить данные таможенному представителю` (`CUSTOMS_DATA_REQUIRED`) ou `Требуется оплата таможенных платежей` (`CUSTOMS_AWAITING_PAYMENT`), o despachante aduaneiro precisa dos teus dados ou falta pagar os encargos; as FAQ da 5Post indicam que encomendas passam pela alfândega, porque são necessários os dados, como contactar o despachante e quando pagar. Vê o [guia de alfândega](guide:customs) e [quando o rastreio deixa de atualizar](guide:tracking-not-updating).

Para encomendas transportadas na Rússia pelos correios nacionais ou por outra transportadora, compara com [Russian Post](carrier:russian-post) e [CDEK](carrier:cdek).

## Rastrear 5Post com o Peek

Cola o código `P`, o número de pedido da loja ou o link `fivepost.ru/tracking?id=...` no [Peek](/). Para um código `P`, o Peek consulta a Colis Privé e a 5Post (primeiro a 5Post na Rússia) e mostra «5Post tem esta encomenda.» assim que a 5Post responde. Para um número de pedido de 14 dígitos nunca sugere a 5Post: toca em «Alterar» ao adicionar a encomenda ou em «Alterar transportadora» na página dela e seleciona 5Post. Um link da 5Post regista logo a encomenda na 5Post.

O Peek lê o rastreio público da 5Post através do adaptador open source [universal-parcel-scraper 5Post](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/5post) e volta a tentar após um erro de rede. Mostra o texto russo de cada registo, o centro de triagem ou hub indicado nos registos de armazém (`РЦ ...`, `ХАБ ...`), o nome e a morada do ponto de levantamento assim que o pedido aguarda na loja ou no cacifo, e a hora em que o levantaste. A 5Post data cada registo com o respetivo deslocamento UTC, e o Peek apresenta-o na hora do teu dispositivo. O prazo limite de guarda, os detalhes de pagamento, o horário do ponto, o nome do remetente e o número de volumes permanecem no site e na app da 5Post.

O Peek interpreta os códigos da 5Post com rigor para que `DELIVERED` nunca dispare um falso aviso de entrega ao domicílio: **Etiqueta criada** (`INFORMATION_RECEIVED`), **Aceite pela transportadora** (`PROCESS_WITHDRAWN`), **Em trânsito** (processamento em armazém, saída, troços aéreos e transferência pós-alfândega), **Na alfândega** (pedidos de dados do despachante, desalfandegamento e pagamento de taxas), **Em distribuição** (`RECEIVED_BY_COURIER`), **Tentativa de entrega** (`RETURNING_BY_COURIER`), **Pronta para levantamento** (`DELIVERED` — `Доставлен в пункт выдачи`), **Entregue** (`DONE` — `Получен`), **Devolvida ao remetente** (`RETURNED_TO_...` e códigos `UNCLAIMED_...`, exceto `UNCLAIMED_READY_FOR_REPICKUP` que permanece Em trânsito) e **Problema reportado** (`LOST`, `CANCELLED`, `CUSTOMS_CLEARANCE_REJECTED` e erros de registo).

Quando o registo mais recente tem quatro dias e a encomenda continua em «Etiqueta criada», «Aceite pela transportadora» ou «Em trânsito», o Peek assinala-a com o dia desse registo, como «Sem atualizações desde seg 12 out». A página da encomenda mostra então «É habitual uma encomenda ficar sem novidades em voos longos, na alfândega ou ao fim de semana. O Peek continua a perguntar a 5Post.», com o link «Pede ajuda a 5Post» para as FAQ da 5Post. Após três semanas sem novos registos, mostra «Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, contacta o vendedor. O Peek continua a perguntar a 5Post.»

Enquanto tiveres a encomenda aberta no Peek, ou no dia da entrega com as notificações ativadas, o Peek consulta até de 10 em 10 minutos; no dia em que a 5Post mostra `Передан курьеру`, de 2 em 2 minutos a partir das 6:00 no relógio do último registo, enquanto o rastreio da 5Post responder. Caso contrário, verifica de hora a hora. Após 12 horas apenas em «Etiqueta criada» ou «Aceite pela transportadora», ou dois dias sem novidades, passa a verificar de hora a hora seja qual for o modo de acompanhamento, e de 6 em 6 horas após sete dias em «Etiqueta criada». Quando o rastreio da 5Post não responde e o Peek acompanha a encomenda apenas através de serviços de rastreio, verifica de hora a hora, de 3 em 3 horas após um dia sem registos e até de 15 em 15 minutos no dia em que a encomenda está em distribuição, enquanto tiveres a encomenda aberta ou as notificações ativadas.

Pode notificar-te para «Todos os registos», «Passos importantes» ou «Só a entrega».

## Contactar a 5Post

A 5Post publica linhas telefónicas oficiais e formulários de apoio com ou sem conta em [fivepost.ru/contacts](https://fivepost.ru/contacts/) e [fivepost.ru/faq](https://fivepost.ru/faq/):

- **Telefone Moscovo e região de Moscovo:** `8-495-935-88-00` (à tarifa do teu operador)
- **Regiões da Rússia:** `8-800-100-05-05`
- **FAQ e formulário de pedidos online:** [FAQ 5Post](https://fivepost.ru/faq/) e [Contactos 5Post](https://fivepost.ru/contacts/) (aceita pedidos de destinatários com ou sem sessão iniciada e anexos até 40 MB)
- **Morada legal:** 109029, Moscovo, ul. Srednyaya Kalitnikovskaya, 28 bldg. 4 (INN `7722461620`, OGRN `1187746567568`)

## Perguntas frequentes

### Porque é que o estado interno da 5Post diz `DELIVERED` antes de eu levantar a encomenda?

No sistema da 5Post, `DELIVERED` (`Доставлен в пункт выдачи`) significa que a encomenda chegou ao balcão ou cacifo Pyaterochka/Perekrestok e está à tua espera. O Peek traduz isso como **Pronta para levantamento** e aguarda `DONE` (`Получен`) antes de assinalar **Entregue**.

### Onde levanto um pedido 5Post?

Levantas no cacifo 5Post (*постамат*) ou na caixa do supermercado Pyaterochka («Пятёрочка») ou Perekrestok («Перекрёсток») selecionado ao finalizar a compra, usando o código enviado por SMS pela 5Post.

### Porque é que a 5Post pediu dados aduaneiros na minha encomenda AliExpress?

Um pedido vindo do estrangeiro é desalfandegado por um despachante aduaneiro, que pode precisar dos teus dados (`CUSTOMS_DATA_REQUIRED`). As FAQ da 5Post explicam que pedidos passam na alfândega, porque são necessários esses dados e como contactar o despachante. Após a libertação, a 5Post mostra `Таможенное оформление завершено` (`CUSTOMS_CLEARANCE_COMPLETED`).

### Como contacto a 5Post se a porta do cacifo não abrir ou a encomenda faltar?

Liga para `8-495-935-88-00` (Moscovo e região) ou `8-800-100-05-05` (regiões), ou envia um pedido de apoio (com fotos até 40 MB) através de [fivepost.ru/contacts](https://fivepost.ru/contacts/) ou da tua conta 5Post.

:::sources
- [5Post (fivepost.ru)](https://fivepost.ru/)
- [FAQ 5Post](https://fivepost.ru/faq/)
- [Contactos 5Post](https://fivepost.ru/contacts/)
- [universal-parcel-scraper 5Post adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/5post)
:::
