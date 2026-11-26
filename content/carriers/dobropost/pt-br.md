---
title: Rastreamento DobroPost: códigos DBRPST, status e PVZ
description: Rastreie uma encomenda DobroPost da China para Rússia ou Belarus: formato DBRPST, leituras de armazém e alfândega, retirada em PVZ, parceiros e suporte.
slug: dobropost-tracking
published: 2026-11-26
updated: 2026-11-26
---

A DobroPost (*ДоброПост*, operada pela *OOO "Dobropost Logistika"* em Moscou em conjunto com a *OOO ICOM DP SOLUTIONS* na Armênia) é uma redirecionadora de encomendas da China para a Rússia e Belarus usada por compradores de marketplaces chineses como Taobao, Poizon, 1688, Pinduoduo, Tmall, 95, Goofish e JD. Os compradores enviam suas compras para o armazém da DobroPost em Jixi, Heilongjiang, onde os itens são conferidos, embalados e pesados; em seguida, o pacote passa pela alfândega e segue para um dos pontos de retirada próprios da DobroPost (PVZ) ou para a [CDEK](carrier:cdek), a DPD ou o [Russian Post](carrier:russian-post). O rastreador da DobroPost também repassa as leituras da Boxberry. Você pode acompanhar um envio em [tracking.dobropost.com](https://tracking.dobropost.com/) ou colar seu código `DBRPST` na caixa acima.

## Códigos de rastreamento da DobroPost (formato `DBRPST`)

Um código de encomenda da DobroPost começa com `DBRPST`, seguido de 10 ou 11 dígitos:

| Formato (inventado) | O que é |
| --- | --- |
| `DBRPST6482917304` | Código DobroPost de 16 caracteres (`DBRPST` + 10 dígitos). |
| `DBRPST73918462051` | Código DobroPost de 17 caracteres (`DBRPST` + 11 dígitos). |

Os códigos que as transportadoras chinesas atribuem aos seus pedidos no trajeto até o armazém da DobroPost pertencem a elas, não à DobroPost: [tracking.dobropost.com](https://tracking.dobropost.com/) e o Peek acompanham o pacote pelo número `DBRPST`. Veja [formatos de código de rastreamento](guide:tracking-number-formats).

## O que significam os status de rastreamento da DobroPost

A DobroPost publica movimentações em russo acompanhadas de códigos internos de status — primeiro a partir do seu próprio armazém na China e do desembaraço aduaneiro, e depois repassadas diretamente da transportadora doméstica de última milha ([CDEK](carrier:cdek), Boxberry ou [Russian Post](carrier:russian-post)):

| Código ou texto da DobroPost | O que significa para você | O Peek mostra |
| --- | --- | --- |
| `VIRTUAL_PARCEL` | A encomenda foi registrada na DobroPost; o armazém na China embala seus produtos nela alguns dias depois. | "Etiqueta criada" |
| `ON_ASSEMBLY`, `ON_WEIGHING`, `AWAITING_PAYMENT`, `READY_FOR_SENT`, `ADDED_TO_THE_REGISTRY` | Embalada e pesada no armazém da DobroPost na China, aguardando pagamento do frete ou adicionada ao manifesto de expedição. | "Recebida pela transportadora" |
| `LEFT_THE_DP_WERHOUSE_IN_CHINA`, `FLEW_OUT_FROM_CHINA` | Saiu do armazém da DobroPost na China e partiu da China no transporte internacional. | "Em trânsito" |
| `AT_CUSTOMS_IN_CHINA`, `ARRIVED_AT_CUSTOMS_IN_THE_COUNTRY_OFF_DESTINATION`, `START_OF_CUSTOMS_CLEARANCE`, `Выпуск с таможни` | Em desembaraço de exportação na China ou desembaraço de importação na Rússia ou em Belarus. | "Na alfândega" |
| `RELEASE_OF_GOODS_WITHOUT_PAYMENT_OF_CUSTOMS_DUTIES`, `LEFT_CUSTOMS_AND_HEADING_TO_LAST_MILE_CARRIER'S_WAREHOUSE`, `READY_FOR_SHIPMENT_FOR_LAST_MILE_DELIVERY` | Liberada pela alfândega e seguindo para a rede doméstica de última milha no destino. | "Em trânsito" |
| `REFUSAL_TO_RELEASE_GOODS_WITH_INDICATION_OF_THE_CODE_OF_THE_REASON_FOR_REFUSAL` | A alfândega recusou a liberação; o rastreador informa o motivo, como mercadoria não destinada a uso pessoal. Verifique sua conta DobroPost. | "Precisa de atenção" |
| `RECEIVED_AT_SHIPMENT_WAREHOUSE`, `SEND_TO_TRANSIT_OFFICE`, `SEND_TO_RECIPIENT_OFFICE`, `ACCEPTED_FOR_DELIVERY`, `SENT_TO_DESTINATION_CITY`, `Прием`, `Обработка` | Leituras domésticas de trânsito repassadas pela CDEK, Boxberry ou Russian Post rumo à sua cidade. | "Em trânsito" |
| `ACCEPTED_AT_WAREHOUSE_ON_DEMAND` (CDEK), `RECEIVED_AT_THE_POINT_OF_ISSUE` (Boxberry) | Chegou à agência ou ponto de retirada escolhido e está pronta para coleta. | "Pronta para retirada" |
| `GETTING_READY_TO_RETURN`, `SENT_TO_COLLECTION_POINT`, `RETURNED_TO_COLLECTION_POINT`, `RETURNED`, `Возврат` | Não retirada no ponto de coleta ou devolvida no trecho final, retornando à DobroPost, que pode reenviá-la. | "Devolvida ao remetente" |
| `RELEASE_OF_GOODS_CUSTOMS_PAYMENTS_PAID` (`Возврат в Китай разрешен`) | A alfândega recusou a liberação e autorizou o retorno da encomenda à China. | "Devolvida ao remetente" |
| `PARCEL_DELIVERED_TO_RECIPIENT`, `DELIVERED`, `Вручение` | Retirada no ponto de coleta ou entregue ao destinatário. | "Entregue" |

## Prazos de entrega, tarifas e pontos de retirada PVZ da DobroPost

De acordo com [Tarifas da DobroPost](https://dobropost.com/tariffs), os prazos de trânsito são contados a partir da expedição do armazém da DobroPost na China até a chegada ao ponto de retirada (PVZ) ou entrega à transportadora doméstica:

- **Tarifas Express** (peso volumétrico, sem mercadorias sensíveis): normalmente **9–13 dias** para Moscou, **10–14 dias** para São Petersburgo, **9–12 dias** para Novosibirsk, Krasnodar, Ecaterimburgo, Kazan, Irkutsk e Ulan-Ude, e **11–15 dias** para Minsk (Belarus).
- **Tarifas Economy** (peso real, indicadas para volumes mais pesados ou volumosos): normalmente **19–25 dias** para Moscou, **20–27 dias** para São Petersburgo, **15–23 dias** para Irkutsk e **22–29 dias** para Minsk.
- **Tarifas Extremo Oriente e Pacotes Pesados:** **6–9 dias** para Vladivostok, **8–12 dias** para Blagoveshchensk e **6–9 dias** para volumes pesados de item único entre 5 e 30 kg.
- **Pontos de retirada PVZ da DobroPost:** listados em [dobropost.com/pvz](https://dobropost.com/pvz) em Moscou, Odintsovo, São Petersburgo, Kazan, Krasnodar, Novosibirsk, Ecaterimburgo, Irkutsk, Ulan-Ude, Blagoveshchensk, Vladivostok, Ussuriysk e Minsk. As taxas publicadas de entrega em PVZ são `100 RUB` (até 5 kg), `200 RUB` (até 10 kg) e `300 RUB` (acima de 10 kg); cidades sem PVZ da DobroPost utilizam pontos parceiros e transportadoras. A encomenda aguarda gratuitamente por 7 dias em um PVZ da DobroPost, depois paga 50 ₽ por dia e é descartada 30 dias após a chegada.

## Alfândega, intervalos sem atualização e problemas com a encomenda

- **Pagamento do frete obrigatório antes do despacho:** se o rastreamento parar em `AWAITING_PAYMENT`, o pacote permanece no armazém da China até que você pague a fatura de frete calculada na sua conta de cliente DobroPost.
- **Intervalos sem movimentação na fronteira e alfândega:** é comum o rastreamento pausar entre `FLEW_OUT_FROM_CHINA` e `ARRIVED_AT_CUSTOMS_IN_THE_COUNTRY_OFF_DESTINATION`, ou durante a inspeção alfandegária. Veja [encomendas retidas na alfândega](guide:customs) e [por que o rastreamento para de atualizar](guide:tracking-not-updating).
- **Danos, itens faltantes ou devoluções:** os [termos](https://dobropost.com/rules) da DobroPost pedem que você registre qualquer dano na folha de entrega ao receber a encomenda e apresente o conteúdo e a embalagem original para abrir reclamação por dano. A [Base de Conhecimento](https://dobropost.com/baza_main) explica o que fazer quando falta um item na encomenda. Um pacote não retirado no ponto de coleta volta para a DobroPost (`RETURNED`), que pode enviá-lo novamente: fale com o suporte. Veja [encomenda perdida](guide:lost-parcel), [encomenda danificada](guide:damaged-parcel), [entregue, mas não recebida](guide:delivered-not-received) e [devolvida ao remetente](guide:returned-to-sender).

## Atendimento ao cliente da DobroPost e links oficiais

O site da DobroPost oferece o botão "Напишите в поддержку" (escrever ao suporte), uma comunidade de clientes e um canal no Telegram, além de convidar clientes a enviar reclamações e ideias diretamente ao CEO, Sergey Serbin. A [Base de Conhecimento](https://dobropost.com/baza_main) explica como abrir um pedido ao suporte, alterar a tarifa de uma encomenda já criada e concluir o desembaraço aduaneiro.

- **Operadora registrada na Rússia:** *OOO "Dobropost Logistika"* (*ООО "Добропост Логистика"*, INN `9722023780`), Moscou (`119192, Lomonosovsky Prospekt, 43 bldg. 2, office 4/1/1`; o site também lista `Michurinsky Prospekt, 54 bldg. 2`).
- **Entidade na Armênia:** *OOO ICOM DP SOLUTIONS* (*ООО ИКОМ ДП СОЛУШНС*), Vanadzor, província de Lori, Armênia.
- **Termos legais e tarifas:** [dobropost.com/rules](https://dobropost.com/rules), [dobropost.com/tariffs](https://dobropost.com/tariffs) e [dobropost.com/pvz](https://dobropost.com/pvz).

> Desconfie de mensagens falsas no Telegram ou links por SMS cobrando taxas alfandegárias extras fora da sua área de cliente oficial da DobroPost ou do portal oficial do despachante aduaneiro. Verifique sempre qualquer pagamento primeiro dentro da sua conta DobroPost. Veja [golpes de SMS de entrega](guide:delivery-scam-texts).

## Acompanhe uma encomenda da DobroPost com o Peek

O [Peek](/) detecta a DobroPost automaticamente como única candidata quando você informa um código `DBRPST` (`DBRPST6482917304` ou `DBRPST73918462051`, inventados) e a identifica na hora com "Transportadora detectada"; o Peek também reconhece links colados de `tracking.dobropost.com/?trackNumber=`. Se precisar escolher a DobroPost manualmente, toque em "Alterar" ao adicionar ou em "Alterar transportadora" na tela da encomenda (buscar por "ДоброПост" ou "Dobropost" também funciona).

O Peek lê diretamente o rastreador da DobroPost e mostra a etapa ("Etiqueta criada", "Recebida pela transportadora", "Em trânsito", "Na alfândega", "Pronta para retirada", "Precisa de atenção", "Devolvida ao remetente", "Entregue"), o texto em russo da DobroPost e das parceiras que ela repassa (CDEK, Boxberry e Russian Post) com números de telefone removidos, além do horário de entrega quando concluída. A cidade que a CDEK informa entre colchetes ao final de uma leitura é exibida como o local da movimentação. Os códigos internos de status não aparecem nos aplicativos do Peek. A DobroPost envia todas as movimentações no fuso horário de Moscou, incluindo leituras do armazém na China, e o Peek exibe os horários no relógio do seu aparelho. O rastreador da DobroPost não fornece data estimada de entrega, peso, nome de quem assinou nem código da transportadora de última milha.

Enquanto você mantém a encomenda aberta no Peek, ou no dia da entrega com notificações ativas, o Peek consulta até a cada 10 minutos enquanto o rastreador da DobroPost responder. Fora isso, verifica de hora em hora. Como o rastreador da DobroPost não possui etapa de saída para entrega, o Peek nunca ativa verificações a cada 2 minutos no dia da entrega. Após 12 horas apenas em "Etiqueta criada" ou "Recebida pela transportadora", ou dois dias sem novidades, passa a consultar a cada hora de qualquer maneira, e a cada 6 horas depois de sete dias em "Etiqueta criada". Quando o rastreador da DobroPost não responde e o Peek acompanha somente por serviços de rastreamento, consulta de hora em hora, a cada 3 horas após um dia quieto e até a cada 15 minutos no dia da saída para entrega.

Quando a leitura mais recente tem quatro dias e a encomenda continua em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito", o Peek marca o dia daquela movimentação, como "Sem atualização desde seg 12 out". A página passa a exibir: "É comum uma encomenda ficar sem atualizações em voos longos, na alfândega ou no fim de semana. O Peek continua consultando DobroPost." Após três semanas sem novas leituras, mostra: "Nenhuma novidade há mais de três semanas. Se ainda não chegou, fale com o vendedor. O Peek continua consultando DobroPost."

Você pode escolher alertas para "Todas as movimentações", "Etapas importantes" ou "Somente entrega". O Peek apenas monitora atualizações de rastreamento e não pode pagar faturas de frete, enviar dados de passaporte para a alfândega nem contatar a DobroPost ou suas parceiras domésticas em seu nome.

## Perguntas sobre o rastreamento da DobroPost

### Qual é o formato de um código de rastreamento da DobroPost?

Os códigos internacionais da DobroPost começam com `DBRPST` seguido por 10 ou 11 dígitos (por exemplo `DBRPST6482917304` ou `DBRPST73918462051`, inventados).

### Quanto tempo leva a entrega da DobroPost da China?

As estimativas publicadas em [dobropost.com/tariffs](https://dobropost.com/tariffs) variam de **6–9 dias** para Vladivostok e **9–13 dias** (Express) ou **19–25 dias** (Economy) para Moscou, e **11–15 dias** (Express) ou **22–29 dias** (Economy) para Minsk, contados a partir da saída do armazém da DobroPost na China.

### Quais transportadoras domésticas entregam encomendas da DobroPost na Rússia e em Belarus?

A DobroPost entrega nos seus próprios pontos PVZ na Rússia e em Minsk e, segundo suas tarifas, por meio da [CDEK](carrier:cdek), da DPD e do [Russian Post](carrier:russian-post) (apenas CDEK em Minsk). Seu rastreador repassa leituras da CDEK, Boxberry e Russian Post.

### Por que minha encomenda da DobroPost está parada em `AWAITING_PAYMENT` ou na alfândega?

`AWAITING_PAYMENT` significa que seu pacote foi embalado e pesado no armazém da DobroPost na China e aguarda o pagamento do frete na sua conta de cliente. Se o rastreamento pausar na alfândega, a [Base de Conhecimento](https://dobropost.com/baza_main) explica o desembaraço aduaneiro e o suporte pode informar a situação do envio.

:::sources
- [DobroPost: Official Website](https://dobropost.com/) – redirecionamento de encomendas de marketplaces da China para Rússia/Belarus, armazém em Jixi, suporte e canal com o CEO, entidades legais em Moscou e na Armênia (*OOO "Dobropost Logistika"*, INN 9722023780)
- [DobroPost: Parcel Tracker](https://tracking.dobropost.com/) – página pública de rastreamento `DBRPST`
- [DobroPost: Tariffs & Transit Times](https://dobropost.com/tariffs) – prazos das tarifas Express, Economy, Extremo Oriente e Pacotes Pesados, regras de peso e transportadoras parceiras
- [DobroPost: Pickup Points (PVZ)](https://dobropost.com/pvz) – endereços dos PVZ da DobroPost na Rússia e em Minsk, taxas de retirada e armazenagem
- [DobroPost: Knowledge Base](https://dobropost.com/baza_main) – guias de marketplaces, chamados ao suporte, troca de tarifa, desembaraço aduaneiro e itens faltantes
- [DobroPost: General Terms of Carriage](https://dobropost.com/rules) – termos de transporte e despacho aduaneiro
- [Universal Parcel Scraper: dobropost](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/dobropost) – formato `DBRPST`, regra de link `tracking.dobropost.com`, mapeamento de status DobroPost/CDEK/Boxberry/Russian Post, cidade entre colchetes como local e horários no fuso de Moscou
:::
