---
title: Rastreio DobroPost: códigos DBRPST, estados e hubs China
description: Rastreia uma encomenda DobroPost da China para a Rússia ou Bielorrússia: códigos DBRPST, armazém e alfândega, levantamento PVZ e contactos.
slug: rastreio-dobropost
published: 2026-11-24
updated: 2026-11-24
---

A DobroPost (*ДоброПост*, operada pela *OOO «Dobropost Logistika»* em Moscovo em conjunto com a *OOO ICOM DP SOLUTIONS* na Arménia) é um transitário de encomendas da China para a Rússia e a Bielorrússia usado por compradores em plataformas chinesas como Taobao, Poizon, 1688, Pinduoduo, Tmall, 95, Goofish e JD. Os compradores mandam entregar as suas compras no armazém da DobroPost em Jixi (Heilongjiang), onde os artigos são verificados, embalados e pesados; depois a encomenda passa na alfândega e segue para um ponto de levantamento próprio da DobroPost (PVZ) ou para a [CDEK](carrier:cdek), DPD ou [Russian Post](carrier:russian-post). O rastreio da DobroPost reencaminha também registos da Boxberry. Podes consultar um envio em [tracking.dobropost.com](https://tracking.dobropost.com/) ou colar o teu código `DBRPST` na caixa acima.

## Números de rastreio DobroPost (formato `DBRPST`)

Um número de encomenda DobroPost começa por `DBRPST`, seguido de 10 ou 11 dígitos:

| Formato (inventado) | O que é |
| --- | --- |
| `DBRPST6482917304` | Número de encomenda DobroPost de 16 caracteres (`DBRPST` + 10 dígitos). |
| `DBRPST73918462051` | Número de encomenda DobroPost de 17 caracteres (`DBRPST` + 11 dígitos). |

Os códigos atribuídos por estafetas chineses a caminho do armazém da DobroPost pertencem a esses estafetas, não à DobroPost: [tracking.dobropost.com](https://tracking.dobropost.com/) e o Peek acompanham a encomenda pelo código `DBRPST`. Vê [formatos de números de rastreio](guide:tracking-number-formats).

## O que significam os estados de rastreio da DobroPost

A DobroPost publica etapas em russo com códigos internos — primeiro do seu armazém na China e da alfândega, e depois reencaminhados diretamente da transportadora local ([CDEK](carrier:cdek), Boxberry ou [Russian Post](carrier:russian-post)):

| Código ou texto DobroPost | O que significa para ti | O Peek mostra |
| --- | --- | --- |
| `VIRTUAL_PARCEL` | A encomenda foi criada no registo da DobroPost; alguns dias depois o armazém na China embala lá as tuas compras. | «Etiqueta criada» |
| `ON_ASSEMBLY`, `ON_WEIGHING`, `AWAITING_PAYMENT`, `READY_FOR_SENT`, `ADDED_TO_THE_REGISTRY` | Embalada e pesada no armazém da DobroPost na China, a aguardar pagamento do envio ou adicionada a um registo de saída. | «Aceite pela transportadora» |
| `LEFT_THE_DP_WERHOUSE_IN_CHINA`, `FLEW_OUT_FROM_CHINA` | Saiu do armazém da DobroPost na China e partiu em transporte internacional. | «Em trânsito» |
| `AT_CUSTOMS_IN_CHINA`, `ARRIVED_AT_CUSTOMS_IN_THE_COUNTRY_OFF_DESTINATION`, `START_OF_CUSTOMS_CLEARANCE`, `Выпуск с таможни` | Em despacho de exportação na China ou desalfandegamento de importação na Rússia ou Bielorrússia. | «Na alfândega» |
| `RELEASE_OF_GOODS_WITHOUT_PAYMENT_OF_CUSTOMS_DUTIES`, `LEFT_CUSTOMS_AND_HEADING_TO_LAST_MILE_CARRIER'S_WAREHOUSE`, `READY_FOR_SHIPMENT_FOR_LAST_MILE_DELIVERY` | Libertada da alfândega e a caminho da rede local de entrega. | «Em trânsito» |
| `REFUSAL_TO_RELEASE_GOODS_WITH_INDICATION_OF_THE_CODE_OF_THE_REASON_FOR_REFUSAL` | A alfândega recusou a libertação; o rastreio indica o motivo (por ex., mercadoria não destinada a uso pessoal). Verifica a tua conta DobroPost. | «Problema reportado» |
| `RECEIVED_AT_SHIPMENT_WAREHOUSE`, `SEND_TO_TRANSIT_OFFICE`, `SEND_TO_RECIPIENT_OFFICE`, `ACCEPTED_FOR_DELIVERY`, `SENT_TO_DESTINATION_CITY`, `Прием`, `Обработка` | Registos internos reencaminhados de CDEK, Boxberry ou Russian Post a caminho da tua cidade. | «Em trânsito» |
| `ACCEPTED_AT_WAREHOUSE_ON_DEMAND` (CDEK), `RECEIVED_AT_THE_POINT_OF_ISSUE` (Boxberry) | Chegou ao ponto de levantamento ou balcão escolhido e está pronta para levantamento. | «Pronta para levantamento» |
| `GETTING_READY_TO_RETURN`, `SENT_TO_COLLECTION_POINT`, `RETURNED_TO_COLLECTION_POINT`, `RETURNED`, `Возврат` | Não levantada ou devolvida na última etapa, regressando à DobroPost que pode reenviá-la. | «Devolvida ao remetente» |
| `RELEASE_OF_GOODS_CUSTOMS_PAYMENTS_PAID` (`Возврат в Китай разрешен`) | A alfândega recusou a libertação e autorizou a devolução à China. | «Devolvida ao remetente» |
| `PARCEL_DELIVERED_TO_RECIPIENT`, `DELIVERED`, `Вручение` | Levantada no ponto de entrega ou entregue ao destinatário. | «Entregue» |

## Prazos de entrega, tarifas e pontos de levantamento DobroPost PVZ

De acordo com [Tarifas DobroPost](https://dobropost.com/tariffs), os prazos contam a partir da expedição do armazém na China até à chegada a um ponto PVZ ou entrega a um estafeta nacional:

- **Tarifas Express** (peso volumétrico, sem artigos sensíveis): tipicamente **9–13 dias** para Moscovo, **10–14 dias** para São Petersburgo, **9–12 dias** para Novosibirsk, Krasnodar, Ecaterimburgo, Kazan, Irkutsk e Ulan-Ude, e **11–15 dias** para Minsk (Bielorrússia).
- **Tarifas Economy** (peso real, adequadas a encomendas mais pesadas ou volumosas): tipicamente **19–25 dias** para Moscovo, **20–27 dias** para São Petersburgo, **15–23 dias** para Irkutsk e **22–29 dias** para Minsk.
- **Tarifas Extremo Oriente e Encomenda Pesada:** **6–9 dias** para Vladivostok, **8–12 dias** para Blagoveshchensk e **6–9 dias** para encomendas pesadas unitárias de 5 a 30 kg.
- **Pontos de levantamento DobroPost PVZ:** listados em [dobropost.com/pvz](https://dobropost.com/pvz) em Moscovo, Odintsovo, São Petersburgo, Kazan, Krasnodar, Novosibirsk, Ecaterimburgo, Irkutsk, Ulan-Ude, Blagoveshchensk, Vladivostok, Ussuriysk e Minsk. As taxas de entrega PVZ publicadas são `100 RUB` (até 5 kg), `200 RUB` (até 10 kg) e `300 RUB` (acima de 10 kg); cidades sem PVZ próprio usam pontos e transportadoras parceiras. Uma encomenda fica 7 dias grátis num PVZ DobroPost, depois 50 ₽ por dia, e é descartada 30 dias após a chegada.

## Alfândega, pausas de rastreio e incidentes

- **Pagamento do envio obrigatório antes da partida:** se o rastreio parar em `AWAITING_PAYMENT`, a encomenda fica no armazém na China até pagares a fatura de envio na tua conta DobroPost.
- **Pausas na fronteira e alfândega:** os registos param frequentemente entre `FLEW_OUT_FROM_CHINA` e `ARRIVED_AT_CUSTOMS_IN_THE_COUNTRY_OFF_DESTINATION` ou durante a revisão aduaneira. Vê [encomendas retidas na alfândega](guide:customs) e [por que razão o rastreio deixa de atualizar](guide:tracking-not-updating).
- **Danos, artigos em falta ou devoluções:** as [condições da DobroPost](https://dobropost.com/rules) pedem que assinales danos na guia de entrega ao receber e apresentes conteúdo e embalagem original numa reclamação por danos. A [Base de Conhecimento](https://dobropost.com/baza_main) explica como proceder se faltar um artigo. Uma encomenda não levantada volta à DobroPost (`RETURNED`), que a pode reenviar mediante pedido ao apoio. Vê [encomenda perdida](guide:lost-parcel), [encomenda danificada](guide:damaged-parcel), [entregue mas não recebida](guide:delivered-not-received) e [devolvida ao remetente](guide:returned-to-sender).

## Apoio ao cliente e links oficiais DobroPost

O site da DobroPost inclui o botão «Напишите в поддержку» (escrever ao apoio), uma comunidade de clientes e um canal Telegram, e convida a enviar reclamações e ideias diretamente ao CEO, Sergey Serbin. A [Base de Conhecimento](https://dobropost.com/baza_main) explica como contactar o apoio, alterar a tarifa de uma encomenda criada e tratar do desalfandegamento.

- **Operador russo registado:** *OOO «Dobropost Logistika»* (*ООО «Добропост Логистика»*, INN `9722023780`), Moscovo (`119192, Lomonosovsky Prospekt, 43 bldg. 2, office 4/1/1`; o site também refere `Michurinsky Prospekt, 54 bldg. 2`).
- **Entidade arménia:** *OOO ICOM DP SOLUTIONS* (*ООО ИКОМ ДП СОЛУШНС*), Vanadzor, província de Lori, Arménia.
- **Termos e tarifas:** [dobropost.com/rules](https://dobropost.com/rules), [dobropost.com/tariffs](https://dobropost.com/tariffs) e [dobropost.com/pvz](https://dobropost.com/pvz).

> Desconfia de mensagens falsas no Telegram ou links SMS a exigir taxas aduaneiras extra fora da tua conta oficial DobroPost ou do portal oficial do despachante. Confirma sempre qualquer pagamento dentro da tua conta DobroPost. Vê [SMS fraudulentas de entregas](guide:delivery-scam-texts).

## Rastrear uma encomenda DobroPost com o Peek

[O Peek](/) deteta a DobroPost automaticamente como única candidata quando introduzes um número `DBRPST` (`DBRPST6482917304` ou `DBRPST73918462051`, inventados) e identifica-a logo com «Transportadora detetada»; o Peek reconhece também links colados `tracking.dobropost.com/?trackNumber=`. Se precisares de selecionar a DobroPost manualmente, toca em «Alterar» ao adicionar ou em «Alterar transportadora» no ecrã da encomenda (pesquisar «ДоброПост» ou «Dobropost» também a encontra).

O Peek lê diretamente o rastreio da DobroPost e mostra a etapa («Etiqueta criada», «Aceite pela transportadora», «Em trânsito», «Na alfândega», «Pronta para levantamento», «Problema reportado», «Devolvida ao remetente», «Entregue»), os textos russos da DobroPost e dos parceiros reencaminhados (CDEK, Boxberry e Russian Post) sem telefones, e a hora de entrega assim que é entregue. A localidade entre parênteses no fim de um registo CDEK surge como o local desse registo. Os códigos de estado ficam de fora em todas as apps do Peek. A DobroPost envia cada registo com o desfasamento UTC de Moscovo, incluindo o armazém na China, e o Peek apresenta-os na hora do teu dispositivo. O rastreio da DobroPost não dá data estimada de entrega, peso, nome do signatário nem número de rastreio da última milha.

Enquanto tiveres a encomenda aberta no Peek, ou no dia da entrega com as notificações ativadas, o Peek consulta até de 10 em 10 minutos enquanto o rastreio da DobroPost responder. Caso contrário, verifica de hora a hora. O rastreio da DobroPost não apresenta etapa em distribuição, pelo que o Peek nunca muda para consultas de 2 em 2 minutos no dia de entrega. Após 12 horas apenas em «Etiqueta criada» ou «Aceite pela transportadora», ou dois dias sem novidades, passa a verificar de hora a hora seja qual for o modo de acompanhamento, e de 6 em 6 horas após sete dias em «Etiqueta criada». Quando o rastreio da DobroPost não responde e o Peek acompanha a encomenda apenas através de serviços de rastreio, verifica de hora a hora, de 3 em 3 horas após um dia sem registos e até de 15 em 15 minutos no dia em que a encomenda está em distribuição, enquanto tiveres a encomenda aberta ou as notificações ativadas.

Quando o registo mais recente tem quatro dias e a encomenda continua em «Etiqueta criada», «Aceite pela transportadora» ou «Em trânsito», o Peek assinala-a com o dia desse registo, como «Sem atualizações desde seg 12 out». A página da encomenda mostra então «É habitual uma encomenda ficar sem novidades em voos longos, na alfândega ou ao fim de semana. O Peek continua a perguntar a DobroPost.» Após três semanas sem novos registos, mostra «Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, contacta o vendedor. O Peek continua a perguntar a DobroPost.»

Podes escolher alertas para «Todos os registos», «Passos importantes» ou «Só a entrega». O Peek acompanha apenas as atualizações de rastreio e não pode pagar faturas de envio, submeter dados de passaporte na alfândega nem contactar a DobroPost ou os seus parceiros nacionais por ti.

## Perguntas frequentes sobre o rastreio DobroPost

### Como é um número de rastreio DobroPost?

Os números de rastreio internacionais DobroPost começam por `DBRPST` seguido de 10 ou 11 dígitos (por exemplo `DBRPST6482917304` ou `DBRPST73918462051`, inventados).

### Quanto tempo demora uma entrega DobroPost desde a China?

As estimativas publicadas em [dobropost.com/tariffs](https://dobropost.com/tariffs) vão de **6–9 dias** para Vladivostok e **9–13 dias** (Express) ou **19–25 dias** (Economy) para Moscovo, até **11–15 dias** (Express) ou **22–29 dias** (Economy) para Minsk, contados da saída do armazém na China.

### Que transportadoras nacionais entregam encomendas DobroPost na Rússia e Bielorrússia?

A DobroPost entrega nos seus próprios pontos PVZ na Rússia e em Minsk e, segundo as suas tarifas, através da [CDEK](carrier:cdek), DPD e [Russian Post](carrier:russian-post) (apenas CDEK em Minsk). O rastreio reencaminha registos de CDEK, Boxberry e Russian Post.

### Porque é que a minha encomenda DobroPost está parada em `AWAITING_PAYMENT` ou na alfândega?

`AWAITING_PAYMENT` significa que a encomenda foi embalada e pesada no armazém da DobroPost na China e aguarda pagamento do envio na tua conta. Se parar na alfândega, a [Base de Conhecimento](https://dobropost.com/baza_main) explica o desalfandegamento e o apoio pode informar o ponto de situação.

:::sources
- [DobroPost: site oficial](https://dobropost.com/) – reenvio de encomendas de marketplaces China–Rússia/Bielorrússia, armazém Jixi, apoio e linha CEO, entidades legais em Moscovo e Arménia (*OOO «Dobropost Logistika»*, INN 9722023780)
- [DobroPost: rastreio de encomendas](https://tracking.dobropost.com/) – página pública de consulta `DBRPST`
- [DobroPost: tarifas e prazos](https://dobropost.com/tariffs) – prazos Express, Economy, Extremo Oriente e Encomenda Pesada, regras de peso e parceiros
- [DobroPost: pontos de levantamento (PVZ)](https://dobropost.com/pvz) – localizações PVZ DobroPost na Rússia e Minsk, taxas e guarda
- [DobroPost: Base de Conhecimento](https://dobropost.com/baza_main) – guias de compra, apoio, mudança de tarifa, alfândega e artigos em falta
- [DobroPost: Condições Gerais de Transporte](https://dobropost.com/rules) – termos de transporte e despacho aduaneiro
- [Universal Parcel Scraper: dobropost](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/dobropost) – formato `DBRPST`, regra de link `tracking.dobropost.com`, mapa de estados DobroPost/CDEK/Boxberry/Russian Post, localidade entre parênteses e horas de Moscovo
:::
