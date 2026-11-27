---
title: Rastreamento 5Post Rússia: códigos, retirada e status
description: Rastreie encomendas 5Post (FivePost / X5) na Rússia: códigos P da Avito e pedidos de loja, status em russo, retirada na Pyaterochka e suporte.
slug: 5post-tracking
published: 2026-11-26
updated: 2026-11-26
---

A 5Post (*5Пост* / *FivePost*, operada pelo grupo X5) é uma rede russa de encomendas de e-commerce, envios entre pessoas e fulfillment que entrega pedidos online e envios da Avito Delivery em mais de **25.700** armários (*постаматы*) e balcões de retirada dentro dos supermercados **Pyaterochka** («Пятёрочка») e **Perekrestok** («Перекрёсток») em **4.950** cidades e vilas da Rússia, apoiada por **51** centros de triagem ([fivepost.ru](https://fivepost.ru/)). A 5Post atende mais de 1.000 varejistas e marketplaces — incluindo Avito Delivery e pedidos internacionais da AliExpress — e opera também entrega porta a porta e cargas LTL/FTL.

Você pode acompanhar um envio em [fivepost.ru](https://fivepost.ru/) (em *Где мой заказ?*) ou colar o número do pedido ou link `fivepost.ru/tracking?id=...` na caixa acima.

## Formatos de código de rastreamento da 5Post

A 5Post rastreia encomendas pelo número do pedido da loja ou marketplace — qualquer sequência única de 3 a 40 letras e dígitos com pelo menos um dígito:

| Formato (fictício) | O que é |
| --- | --- |
| `P48291736051` | `P` + 11 dígitos (12 caracteres), formato dos códigos Avito Delivery. O Peek consulta [Colis Privé](carrier:colis-prive) e 5Post, priorizando a 5Post na Rússia. |
| `99994829173605` | 14 dígitos, número de pedido de loja online. O Peek nunca indica a 5Post para esse formato e consulta DPD, Chronopost, SEUR, BRT e outras: escolha 5Post manualmente ou cole o link da 5Post. |

Como cada loja define seus próprios números de pedido, dois pedidos podem compartilhar o mesmo código. Nesses casos o site da 5Post pede dígitos do telefone do destinatário, e o Peek não consegue diferenciá-los. Um link `fivepost.ru/tracking?id=...` vincula diretamente à 5Post. Para outros formatos, veja o [guia de formatos de código de rastreamento](guide:tracking-number-formats).

## O que significam os status de rastreamento da 5Post

O rastreamento da 5Post combina códigos técnicos com textos em russo, indicando o centro regional de triagem (`РЦ ...`) nas leituras de processamento e despacho.

| Código e texto em russo da 5Post | O que significa |
| --- | --- |
| `INFORMATION_RECEIVED` — `Ожидается передача заказа в 5post` | Dados do pedido registrados; a 5Post aguarda o vendedor ou remetente entregar o pacote. |
| `PROCESS_WITHDRAWN` — `Передан службе доставки` | O remetente postou ou entregou o pacote à 5Post. |
| `WAREHOUSE_PROCESSING` — `Обрабатывается на складе ...` | Em processamento em um armazém regional de triagem da 5Post (`РЦ`). |
| `READY_FOR_SHIPMENT` — `Покинул склад ...` | Saiu do armazém de triagem rumo ao próximo centro ou loja de retirada. |
| `DELIVERED` — `Доставлен в пункт выдачи` | Chegou ao caixa da Pyaterochka / Perekrestok escolhida ou ao armário 5Post — **pronta para retirada**, ainda não retirada! |
| `DONE` — `Получен` | Retirada pelo destinatário (entregue). |
| `RECEIVED_BY_COURIER` — `Передан курьеру` | Saiu com entregador para entrega em domicílio nos pedidos com courier. |
| `RETURNING_BY_COURIER` — `Неуспешная попытка вручения` | Tentativa de entrega por courier não realizada. |
| `CUSTOMS_CLEARANCE_STARTED` / `CUSTOMS_DATA_REQUIRED` / `CUSTOMS_AWAITING_PAYMENT` | Pedido do exterior (como AliExpress) em desembaraço aduaneiro, aguardando envio de dados ao representante aduaneiro ou pagamento de tributos. |
| `CUSTOMS_CLEARANCE_COMPLETED` — `Таможенное оформление завершено` | Alfândega liberou o pacote para seguir na rede da 5Post. |
| `UNCLAIMED_...` / `RETURNED_TO_PARTNER` / `RETURNED_TO_CLIENT` | Não retirado no prazo ou cancelado após postagem, retornando à loja ou remetente (`UNCLAIMED_READY_FOR_REPICKUP` indica preparação para novo envio ao ponto de retirada). |
| `INCEPTION_PROBLEM` / `LOST` / `CANCELLED` / `REGISTRATION_ERROR_...` | Problema no pedido, cancelamento, extravio ou ausência de telefone/data/endereço exigindo correção. |

Repare em um detalhe importante dos códigos da 5Post: `DELIVERED` (`Доставлен в пункт выдачи`) significa **pronta para retirada** na loja ou no armário, enquanto `DONE` (`Получен`) indica que você realmente retirou o pedido.

## Retirada na Pyaterochka, Perekrestok e armários

Pedidos para retirada aguardam em um armário 5Post (*постамат*) ou no caixa (*касса*) da loja Pyaterochka ou Perekrestok selecionada na compra; o FAQ da 5Post traz os horários de funcionamento dos armários e pontos de retirada:

- **Código de retirada:** quando o pedido chega ao local (`Доставлен в пункт выдачи`), a 5Post envia o código de retirada por SMS. Digite-o na tela do armário ou informe no caixa. O FAQ da 5Post explica onde achar o código e o que fazer se a equipe não localizar o pedido.
- **Prorrogação de guarda e alteração de telefone:** conforme o [FAQ da 5Post](https://fivepost.ru/faq/), destinatários podem consultar o prazo de guarda, prorrogar o armazenamento, pagar online pelo site ou app, além de ver como trocar o telefone, lidar com porta travada no armário ou relatar compartimento vazio ou errado.
- **Pedidos internacionais (AliExpress):** pedidos do exterior encaminhados pela 5Post passam por trecho aéreo (`DEPARTED_BY_AIR`, `DELIVERED_BY_AIR`) e alfândega russa; se aparecer `Требуется предоставить данные таможенному представителю` (`CUSTOMS_DATA_REQUIRED`) ou `Требуется оплата таможенных платежей` (`CUSTOMS_AWAITING_PAYMENT`), o despachante precisa dos seus dados ou há tributos pendentes. O FAQ da 5Post detalha quais pedidos passam pela alfândega, por que os dados são solicitados, como contatar o representante aduaneiro e quando pagar. Veja o [guia de alfândega](guide:customs) e [por que o rastreamento para de atualizar](guide:tracking-not-updating).

Para encomendas transportadas na Rússia pelo correio nacional ou outra courier, compare com [Russian Post](carrier:russian-post) e [CDEK](carrier:cdek).

## Rastreando a 5Post com o Peek

Cole seu número `P`, número do pedido da loja ou link `fivepost.ru/tracking?id=...` no [Peek](/). Para códigos `P`, o Peek consulta Colis Privé e 5Post (5Post primeiro na Rússia) e exibe "5Post está com esta encomenda." quando ela confirma. Para números de pedido de 14 dígitos ele nunca seleciona 5Post sozinho: toque em "Trocar" ao adicionar ou "Trocar transportadora" na página e selecione 5Post. Um link da 5Post vincula diretamente à 5Post.

O Peek consulta o rastreamento de visitante da 5Post pelo [adaptador open-source 5Post do universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/5post) e tenta novamente em caso de erro de rede. Ele mostra o texto em russo de cada leitura, o centro de triagem ou hub citado (`РЦ ...`, `ХАБ ...`), o nome e o endereço do ponto de retirada ou armário assim que o pedido aguarda coleta e a hora em que você o retirou. A 5Post informa cada horário com fuso UTC, e o Peek exibe no relógio do seu dispositivo. Prazo limite de guarda, dados de pagamento, horário da loja, nome do remetente e quantidade de volumes permanecem no site e app da 5Post.

O Peek traduz os códigos da 5Post com precisão para que `DELIVERED` nunca dispare um falso alerta de entrega em casa: **Etiqueta criada** (`INFORMATION_RECEIVED`), **Recebida pela transportadora** (`PROCESS_WITHDRAWN`), **Em trânsito** (armazém, despacho, voos e trânsito pós-alfândega), **Na alfândega** (pedidos de dados, desembaraço e pagamento de tributos), **Saiu para entrega** (`RECEIVED_BY_COURIER`), **Tentativa de entrega** (`RETURNING_BY_COURIER`), **Pronta para retirada** (`DELIVERED` — `Доставлен в пункт выдачи`), **Entregue** (`DONE` — `Получен`), **Devolvida ao remetente** (`RETURNED_TO_...` e códigos `UNCLAIMED_...`, exceto `UNCLAIMED_READY_FOR_REPICKUP`, que permanece Em trânsito) e **Precisa de atenção** (`LOST`, `CANCELLED`, `CUSTOMS_CLEARANCE_REJECTED` e erros de registro).

Quando a leitura mais recente tem quatro dias e o pacote segue em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito", o Peek sinaliza a data dessa leitura, como "Sem atualizações desde seg 12 out". A página do pacote então exibe "É comum uma encomenda ficar sem atualizações em voos longos, na alfândega ou no fim de semana. O Peek continua consultando 5Post.", com o link "Pedir ajuda a 5Post" para o FAQ da 5Post. Após três semanas sem novas leituras, mostra "Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, fale com o vendedor. O Peek continua consultando 5Post."

Enquanto você mantém a encomenda aberta no Peek, ou no dia da entrega com notificações ativadas, o Peek consulta até a cada 10 minutos; no dia em que a 5Post mostra `Передан курьеру`, a cada 2 minutos a partir das 6h no relógio da leitura mais recente enquanto o rastreador próprio responder. Nos demais casos, consulta de hora em hora. Após 12 horas apenas em "Etiqueta criada" ou "Recebida pela transportadora", ou dois dias sem novidades, consulta de hora em hora em qualquer modo, e a cada 6 horas após sete dias em "Etiqueta criada". Quando o rastreador da 5Post não responde e o Peek acompanha apenas por serviços de rastreamento, consulta de hora em hora, a cada 3 horas após um dia sem movimento e até a cada 15 minutos no dia de saída para entrega. Pode notificar em "Cada movimentação", "Etapas importantes" ou "Só a entrega".

## Como contatar a 5Post

A 5Post divulga telefones oficiais e formulários de suporte em [fivepost.ru/contacts](https://fivepost.ru/contacts/) e [fivepost.ru/faq](https://fivepost.ru/faq/):

- **Moscou e região de Moscou:** `8-495-935-88-00` (tarifa da sua operadora)
- **Demais regiões da Rússia:** `8-800-100-05-05`
- **FAQ online e formulário de chamado:** [5Post FAQ](https://fivepost.ru/faq/) e [5Post Contacts](https://fivepost.ru/contacts/) (aceita consultas de destinatários com ou sem login e anexos de até 40 MB)
- **Endereço legal:** 109029, Moscou, ul. Srednyaya Kalitnikovskaya, 28 bldg. 4 (INN `7722461620`, OGRN `1187746567568`)

## Perguntas frequentes

### Por que o status interno da 5Post diz `DELIVERED` antes de eu retirar meu pacote?

No sistema da 5Post, `DELIVERED` (`Доставлен в пункт выдачи`) indica que o pacote foi entregue ao ponto de retirada ou armário na Pyaterochka/Perekrestok e aguarda você. O Peek classifica isso como **Pronta para retirada** e aguarda `DONE` (`Получен`) para marcar **Entregue**.

### Onde retiro um pedido da 5Post?

Você retira no armário 5Post (*постамат*) ou no caixa da loja Pyaterochka («Пятёрочка») ou Perekrestok («Перекрёсток») escolhida no checkout, usando o código enviado por SMS pela 5Post.

### Por que a 5Post pediu dados alfandegários no meu pedido da AliExpress?

Pedidos do exterior são desembaraçados por um representante aduaneiro, que pode precisar dos seus dados (`CUSTOMS_DATA_REQUIRED`). O FAQ da 5Post explica quais pedidos passam pela alfândega, por que os dados são necessários e como contatar o representante. Após a liberação, a 5Post mostra `Таможенное оформление завершено` (`CUSTOMS_CLEARANCE_COMPLETED`).

### Como contatar a 5Post se a porta do armário não abrir ou o pacote sumir?

Ligue para `8-495-935-88-00` (Moscou e região) ou `8-800-100-05-05` (regiões), ou abra um chamado de destinatário (com fotos de até 40 MB) em [fivepost.ru/contacts](https://fivepost.ru/contacts/) ou na sua conta 5Post.

:::sources
- [5Post (fivepost.ru)](https://fivepost.ru/)
- [5Post FAQ](https://fivepost.ru/faq/)
- [5Post Contacts](https://fivepost.ru/contacts/)
- [Adaptador 5Post do universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/5post)
:::
