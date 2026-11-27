---
title: Rastreio Shipter: códigos coreanos H3 e status
description: Rastreie encomendas internacionais aéreas Shipter (H3 Networks) da Coreia do Sul: códigos H3...KR, repasses locais, status e links de rastreamento.
slug: shipter-tracking
published: 2026-11-26
updated: 2026-11-26
---

A Shipter, operada pela **H3 NETWORKS Co., Ltd.** na Coreia do Sul, envia encomendas B2B e B2C da Coreia para mais de **100 países** por meio de mais de 40 canais logísticos, em uma malha aérea que conecta mais de 25 aeroportos por semana ([shipter.kr](https://www.shipter.kr/)). Seu serviço B2B cobre transporte, alfândega e armazenagem; o serviço B2C cobre envio internacional, desembaraço e entrega de última milha. A Amazon Coreia a escolheu como parceira oficial em sua SPN (Service Provider Network), ela é registrada como transportadora no eBay, Kogan e Allegro, seu rastreamento é integrado ao AfterShip e ao 17TRACK e também atua em envios para criadores de conteúdo (*influencer seeding*).

Você pode consultar em [shipter.kr/tracking](https://www.shipter.kr/tracking) ou colar seu código `H3...KR` na caixa acima.

## Formato do código de rastreio Shipter

Os códigos da Shipter começam com `H3` e terminam com `KR`, trazendo duas letras e cinco ou seis dígitos no meio:

| Formato (inventado) | O que é |
| --- | --- |
| `H3AU482917KR` | 12 caracteres: `H3`, duas letras, seis dígitos e `KR`. O Peek consulta [Colis Privé](carrier:colis-prive) e Shipter, priorizando Shipter na Coreia do Sul. |
| `H3FR48291KR` | 11 caracteres: `H3`, duas letras, cinco dígitos e `KR`. O Peek consulta apenas a Shipter. |

Um link de rastreio de `shipter.kr/tracking/<código>` ou `shiptertracking.com/trackingResult/<código>` identifica a Shipter imediatamente. Para envios feitos pelo correio coreano, veja [Korea Post](carrier:korea-post); para outros padrões, consulte o [guia de formatos de código de rastreio](guide:tracking-number-formats).

## Significado dos status de rastreio Shipter

A linha do tempo da Shipter combina suas próprias etapas de exportação da Coreia do Sul e frete aéreo internacional com leituras repassadas pela transportadora local no país de destino, em inglês.

| Texto da Shipter / parceira | O que significa |
| --- | --- |
| `The shipment information has been received by shipter, but the package has not been picked up yet.` | Pré-aviso recebido do vendedor coreano; a Shipter ainda não recebeu o pacote físico. |
| `The parcel has been scanned by shipter and ready to be shipped.` | A Shipter recebeu e registrou a encomenda em sua unidade na Coreia. |
| `The parcel has been shipped.` / `The flight has been departed.` / `Depart origin airport` | Despachada da Coreia do Sul em voo internacional de carga. |
| `Arrived at destination airport` / `Collected from airport terminal` | Chegou ao país de destino e foi retirada do terminal de cargas do aeroporto. |
| `Import customs cleared` | A alfândega de destino liberou a encomenda para seguir à entrega local. |
| `Arrived at destination` / `In transit` | Entrou no fluxo de triagem do destino antes das leituras da transportadora local. |
| `Parcel received in processing centre (...)` / `Parcel depart from ... depot` / `The item is ready for shipment...` | Processada e expedida dentro da rede da transportadora local de destino. |
| `Your parcel has been received at ... depot..` / `Your parcel is currently in transit to ...` | Repassada pela parceira de última milha no destino, informando o depósito e a cidade. |
| `Out for delivery` / `Your parcel is with your local courier and ready for delivery..` | Saiu hoje com o entregador local. |
| `Your parcel was delivered` / `Delivered.` | Entregue no endereço do destinatário. |

Dois avisos repassados pela parceira local — `Your parcel will be entrusted to last mile .it is being prepared by your sender` e `Shipping information received` — surgem após a leitura de recebimento da Shipter e não significam que o pacote retrocedeu.

## Trânsito aéreo internacional, alfândega e repasse local

A Shipter envia encomendas por via aérea a partir da Coreia do Sul e as entrega a uma transportadora local no destino:

- **Voo e aeroporto:** após `Depart origin airport`, a Shipter exibe `Arrived at destination airport` no pouso, `Collected from airport terminal` na saída do terminal e `Import customs cleared` ao concluir a liberação de importação. Consulte o [guia de alfândega](guide:customs) e [quando o rastreio para de atualizar](guide:tracking-not-updating).
- **Entrega local:** uma transportadora no país de destino faz a entrega final, e a Shipter repassa os registros de depósito, `Out for delivery` e entrega para a mesma linha do tempo.
- **Sem data prevista de entrega:** a API pública da Shipter não publica previsão de entrega; acompanhe o avanço observando `Import customs cleared`, chegada ao depósito local e `Out for delivery`.

## Como acompanhar Shipter no Peek

Cole seu código `H3...KR` — ou um link `shipter.kr/tracking/...` ou `shiptertracking.com/trackingResult/...` — no Peek. Em códigos de 12 caracteres, o Peek consulta [Colis Privé](carrier:colis-prive) e Shipter (Shipter primeiro na Coreia do Sul); em códigos de 11 caracteres, consulta apenas a Shipter. O Peek mostra "Shipter está com esta encomenda." assim que a Shipter responde. Se o Peek identificar outra transportadora, toque em "Trocar" ao adicionar ou "Trocar transportadora" na tela do pacote e escolha Shipter.

O Peek lê o rastreamento da Shipter pelo [adaptador Shipter de código aberto do universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/shipter) e tenta novamente em caso de falha de rede. Exibe cada texto em inglês com o depósito ou local informado e o horário da entrega. A Shipter envia deslocamentos GMT e o Peek converte para o relógio do seu aparelho; horários sem fuso aparecem como recebidos.

O Peek organiza os registros em etapas claras: **Etiqueta criada** para o pré-aviso antes da coleta, **Recebida pela transportadora** quando a Shipter registra o pacote na Coreia, **Em trânsito** para decolagem, chegada ao aeroporto, liberação alfandegária e transferências locais, **Saiu para entrega** quando o entregador local inicia a rota e **Entregue** na conclusão. Pré-avisos tardios da parceira local (`Shipping information received` e `Your parcel will be entrusted to last mile...`) permanecem no histórico sem fazer o status regredir.

Quando a leitura mais recente tem quatro dias e o pacote segue em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito", o Peek sinaliza a data dessa leitura, como "Sem atualizações desde seg 12 out". A página do pacote então exibe "É comum uma encomenda ficar sem atualizações em voos longos, na alfândega ou no fim de semana. O Peek continua consultando Shipter." Após três semanas sem novas leituras, mostra "Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, fale com o vendedor. O Peek continua consultando Shipter."

Enquanto o pacote estiver aberto no Peek, ou no dia da entrega com notificações ligadas, o Peek consulta até a cada 10 minutos; no dia em que a Shipter mostrar `Out for delivery` ou `Your parcel is with your local courier and ready for delivery..`, a cada 2 minutos a partir das 6h no relógio da leitura mais recente, enquanto o rastreador da Shipter responder. Nos demais casos, verifica de hora em hora. Após 12 horas apenas em "Etiqueta criada" ou "Recebida pela transportadora", ou dois dias sem novidades, consulta a cada hora, e a cada 6 horas após sete dias em "Etiqueta criada". Quando a Shipter não responde e o Peek acompanha por serviços de rastreamento, consulta de hora em hora, a cada 3 horas após um dia sem novidades e até a cada 15 minutos no dia da saída para entrega. As notificações aceitam "Cada movimentação", "Etapas importantes" ou "Só a entrega".

## Como entrar em contato com a Shipter

A Shipter atende as empresas que contratam seus envios:

- **Site oficial e consultas de serviço:** [shipter.kr](https://www.shipter.kr/) (H3 NETWORKS Co., Ltd.; *이용문의* para consultas comerciais)
- **Rastreador oficial:** [shipter.kr/tracking](https://www.shipter.kr/tracking)
- **Fale primeiro com a loja:** cada empresa cliente tem um gerente de atendimento dedicado na Shipter para tratar ocorrências, devoluções e indenizações. Em caso de atraso, dano ou extravio, contate a loja que enviou o pedido informando seu código `H3...KR`.

## Perguntas frequentes

### Como é um código de rastreio da Shipter?

Começa com `H3` e termina com `KR`, com duas letras e cinco ou seis dígitos no meio: 11 ou 12 caracteres, como `H3AU482917KR`. O site da Shipter não explica o significado das letras intermediárias.

### Por que "Shipping information received" apareceu dias depois de o pacote já ter saído?

É um aviso eletrônico da parceira local no país de destino, repassado pela Shipter após sua própria leitura inicial. Ele não faz a encomenda retroceder: o Peek mantém a etapa da leitura anterior.

### Por que minha encomenda Shipter mostra atualizações de outra transportadora?

A Shipter faz a exportação da Coreia do Sul e o frete aéreo internacional, transferindo depois o pacote a uma transportadora local no país de destino e exibindo os registros de depósito e entrega dessa parceira na mesma linha do tempo.

### A Shipter informa data prevista de entrega?

Não. O rastreamento público da Shipter mostra eventos com data, hora e fuso GMT de exportação, voo, alfândega e entrega local, sem data estimada de entrega.

:::sources
- [Shipter (H3 NETWORKS Co., Ltd.)](https://www.shipter.kr/)
- [Shipter Tracking](https://www.shipter.kr/tracking)
- [universal-parcel-scraper Shipter adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/shipter)
:::
