---
title: Rastreio Shipter: números H3 da Coreia do Sul e estados
description: Acompanha encomendas aéreas internacionais Shipter (H3 Networks) da Coreia do Sul: códigos H3...KR, entregas locais, estados e ligações.
slug: rastreio-shipter
published: 2026-11-24
updated: 2026-11-24
---

A Shipter, operada pela **H3 NETWORKS Co., Ltd.** na Coreia do Sul, expede encomendas B2B e B2C a partir da Coreia para mais de **100 países** através de mais de 40 canais logísticos, numa rede aérea que liga mais de 25 aeroportos todas as semanas ([shipter.kr](https://www.shipter.kr/)). O seu serviço B2B cobre transporte, alfândega e armazenagem; o serviço B2C cobre entrega no estrangeiro, alfândega e última milha. A Amazon Korea escolheu-a como parceira logística oficial na sua rede SPN (*Service Provider Network*), está registada como transportadora no eBay, Kogan e Allegro, o seu rastreio está ligado à AfterShip e à 17TRACK e atua ainda como parceira de envios para influenciadores.

Podes consultar uma encomenda em [shipter.kr/tracking](https://www.shipter.kr/tracking) ou colar o teu número `H3...KR` na caixa acima.

## Formato do número de rastreio Shipter

Os números de rastreio da Shipter começam por `H3` e terminam em `KR`, com duas letras e cinco ou seis dígitos pelo meio:

| Formato (inventado) | O que é |
| --- | --- |
| `H3AU482917KR` | 12 caracteres: `H3`, duas letras, seis dígitos e `KR`. O Peek consulta a [Colis Privé](carrier:colis-prive) e a Shipter, primeiro a Shipter na Coreia do Sul. |
| `H3FR48291KR` | 11 caracteres: `H3`, duas letras, cinco dígitos e `KR`. O Peek consulta apenas a Shipter. |

Uma ligação de `shipter.kr/tracking/<número>` ou `shiptertracking.com/trackingResult/<número>` associa de imediato a encomenda à Shipter. Para encomendas enviadas da Coreia do Sul pela Korea Post, consulta [Korea Post](carrier:korea-post); para outros formatos, vê o guia de [formatos de números de seguimento](guide:tracking-number-formats).

## O que significam os estados de rastreio da Shipter

O histórico da Shipter junta os seus próprios marcos de exportação na Coreia do Sul e carga aérea internacional aos registos retransmitidos pela transportadora local de última milha no país de destino, em inglês.

| Texto da Shipter / parceiro | O que significa |
| --- | --- |
| `The shipment information has been received by shipter, but the package has not been picked up yet.` | Pré-aviso recebido do vendedor coreano; a Shipter ainda não registou a caixa física. |
| `The parcel has been scanned by shipter and ready to be shipped.` | A Shipter recebeu e registou a encomenda nas suas instalações na Coreia. |
| `The parcel has been shipped.` / `The flight has been departed.` / `Depart origin airport` | Expedida da Coreia do Sul num voo internacional de carga. |
| `Arrived at destination airport` / `Collected from airport terminal` | Aterrou no país de destino e foi recolhida no terminal de carga do aeroporto. |
| `Import customs cleared` | A alfândega de destino libertou a encomenda para seguir para a entrega local. |
| `Arrived at destination` / `In transit` | Entrou no fluxo de triagem do destino antes dos registos da transportadora local. |
| `Parcel received in processing centre (...)` / `Parcel depart from ... depot` / `The item is ready for shipment...` | Processada e expedida na rede da transportadora local de destino. |
| `Your parcel has been received at ... depot..` / `Your parcel is currently in transit to ...` | Retransmitido do parceiro de última milha no país de destino, com depósito e cidade. |
| `Out for delivery` / `Your parcel is with your local courier and ready for delivery..` | Em distribuição hoje com o estafeta local. |
| `Your parcel was delivered` / `Delivered.` | Entregue na morada do destinatário. |

Dois avisos retransmitidos pelo parceiro local, `Your parcel will be entrusted to last mile .it is being prepared by your sender` e `Shipping information received`, surgem depois do registo de aceitação da Shipter. Não significam que a encomenda recuou.

## Trânsito aéreo internacional, alfândega e entregas locais

A Shipter envia as encomendas por via aérea a partir da Coreia do Sul e entrega-as a uma transportadora local no país de destino:

- **Voo e aeroporto:** após `Depart origin airport`, a Shipter mostra `Arrived at destination airport` quando o voo aterra, `Collected from airport terminal` quando a encomenda sai do terminal de carga e `Import customs cleared` após concluído o desalfandegamento de importação. Consulta o [guia de alfândega](guide:customs) e [quando o rastreio deixa de atualizar](guide:tracking-not-updating).
- **Entrega local de última milha:** uma transportadora no país de destino entrega a encomenda, e a Shipter retransmite os seus registos de depósito, `Out for delivery` e entrega dentro do seu próprio histórico.
- **Sem data estimada de entrega:** a API pública da Shipter não divulga data estimada de entrega; acompanha o progresso observando `Import customs cleared`, a receção no depósito local e `Out for delivery`.

## Acompanhar a Shipter com o Peek

Cola o teu código `H3...KR`, ou uma ligação `shipter.kr/tracking/...` ou `shiptertracking.com/trackingResult/...`, no Peek. Para um código de 12 caracteres o Peek consulta a [Colis Privé](carrier:colis-prive) e a Shipter (primeiro a Shipter na Coreia do Sul); para um de 11 caracteres, apenas a Shipter. Mostra «Shipter tem esta encomenda.» assim que a Shipter responde. Se o Peek indicar outra transportadora, toca em «Alterar» ao adicionar ou em «Alterar transportadora» na página da encomenda e escolhe Shipter.

O Peek lê o rastreio da Shipter através do [adaptador Shipter em código aberto no universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/shipter) e volta a tentar em caso de erro de rede. Mostra o texto em inglês de cada registo com o depósito ou local indicado e a hora de entrega. A Shipter indica as horas com desfasamento GMT e o Peek apresenta-as no relógio do teu dispositivo; uma hora sem desfasamento é apresentada tal como a Shipter a fornece.

O Peek traduz os registos da Shipter em etapas claras: «Etiqueta criada» para o pré-aviso antes da recolha, «Aceite pela transportadora» assim que a Shipter regista a encomenda na Coreia, «Em trânsito» para partida de voo, chegada ao aeroporto, libertação aduaneira e transferências locais, «Em distribuição» quando o estafeta local inicia a rota e «Entregue» na conclusão. Avisos tardios do parceiro local (`Shipping information received` e `Your parcel will be entrusted to last mile...`) ficam no histórico sem fazer recuar a encomenda.

Quando o registo mais recente tem quatro dias e a encomenda continua em «Etiqueta criada», «Aceite pela transportadora» ou «Em trânsito», o Peek assinala-a com o dia desse registo, como «Sem atualizações desde seg 12 out». A página da encomenda apresenta então «É habitual uma encomenda ficar sem novidades em voos longos, na alfândega ou ao fim de semana. O Peek continua a perguntar a Shipter.» Passadas três semanas sem novos registos, mostra «Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, contacta o vendedor. O Peek continua a perguntar a Shipter.»

Enquanto tens a encomenda aberta no Peek, ou no dia da entrega com as notificações ativadas, o Peek consulta até de 10 em 10 minutos; no dia em que a Shipter apresenta `Out for delivery` ou `Your parcel is with your local courier and ready for delivery..`, de 2 em 2 minutos a partir das 6h00 no relógio do registo mais recente enquanto o rastreio da Shipter responder. Caso contrário, o Peek verifica de hora a hora. Após 12 horas apenas em «Etiqueta criada» ou «Aceite pela transportadora», ou dois dias sem novidades, verifica de hora a hora independentemente da forma como acompanhas a encomenda, e de 6 em 6 horas após sete dias em «Etiqueta criada». Quando o rastreio da Shipter não responde e o Peek acompanha a encomenda apenas através de serviços de rastreio, verifica de hora a hora, de 3 em 3 horas após um dia sem novidades e até de 15 em 15 minutos no dia em que a encomenda está em distribuição, enquanto tiveres a encomenda aberta ou as notificações ativadas.

O Peek pode notificar-te para «Todos os registos», «Passos importantes» ou «Só a entrega».

## Contactar a Shipter

A Shipter trabalha para as empresas que expedem através da sua rede:

- **Site oficial e pedidos comerciais:** [shipter.kr](https://www.shipter.kr/) (H3 NETWORKS Co., Ltd.; *이용문의* para pedidos de serviço)
- **Rastreio oficial online:** [shipter.kr/tracking](https://www.shipter.kr/tracking)
- **Contacta primeiro o vendedor:** cada empresa cliente tem um gestor de apoio dedicado na Shipter, que trata de ocorrências, devoluções e reclamações com os seus clientes. Se a tua encomenda estiver atrasada, danificada ou perdida, contacta a loja que a enviou com o teu número `H3...KR`.

## Perguntas frequentes

### Como é um número de rastreio Shipter?

Começa por `H3` e termina em `KR`, com duas letras e cinco ou seis dígitos no meio: 11 ou 12 caracteres, como `H3AU482917KR`. O site da Shipter não indica o significado das letras.

### Porque apareceu «Shipping information received» dias depois de a encomenda já ter sido expedida?

Trata-se de um aviso do parceiro de última milha no país de destino, retransmitido pela Shipter após o seu próprio registo de aceitação. Não faz recuar a encomenda: o Peek mantém a etapa do registo anterior.

### Porque mostra a minha encomenda Shipter atualizações de outra transportadora?

A Shipter gere a exportação a partir da Coreia do Sul e o transporte aéreo internacional, passando depois a encomenda a uma transportadora local no país de destino e exibindo os registos de depósito e entrega desse parceiro no teu histórico Shipter.

### A Shipter fornece data estimada de entrega?

Não. O fluxo público da Shipter mostra eventos com hora e desfasamento GMT para exportação, voo, alfândega e estafeta local, sem data estimada de entrega.

:::sources
- [Shipter (H3 NETWORKS Co., Ltd.)](https://www.shipter.kr/)
- [Shipter Tracking](https://www.shipter.kr/tracking)
- [universal-parcel-scraper Shipter adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/shipter)
:::
