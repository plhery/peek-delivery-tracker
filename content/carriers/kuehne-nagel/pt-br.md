---
title: Rastreamento Kuehne+Nagel: códigos, status e entrega
description: Rastreie cargas rodoviárias, aéreas e marítimas da Kuehne+Nagel por referência ou link myKN: formatos de código, status, alfândega e contato.
slug: kuehne-nagel-tracking
published: 2026-11-26
updated: 2026-11-26
---

A Kuehne+Nagel é uma das maiores operadoras logísticas do mundo, com quase 92.000 funcionários em quase 1.300 unidades espalhadas por cerca de 100 países. Ela movimenta cargas marítimas, aéreas e rodoviárias, e seu [rastreador público myKN](https://mykn.kuehne-nagel.com/public-tracking/) consulta remessas de todos os modais.

O rastreamento público no myKN cobre remessas iniciadas nos últimos 90 dias. Como a Kuehne+Nagel opera entre terminais rodoviários, aeroportos, estações de carga conteinerizada (CFS), terminais ferroviários e portos marítimos, uma única remessa pode exibir tanto marcos físicos da rota quanto avisos separados de reserva, alfândega ou documentação.

## Formatos de código de rastreamento da Kuehne+Nagel

O Peek aceita referências Kuehne+Nagel de 6 a 35 letras e dígitos, com pelo menos um dígito, removendo espaços e hifens. O myKN consulta na mesma busca os códigos próprios da Kuehne+Nagel, conhecimentos de transporte, reservas e referências de clientes. O Peek reconhece três formatos específicos:

- **`RWM` seguido de 9 dígitos** (12 caracteres ao todo, como `RWM482917360`) — número de rastreamento de carga rodoviária da Kuehne+Nagel, reconhecido pelo Peek exclusivamente como Kuehne+Nagel.
- **`RAPI` seguido de 8 dígitos** (12 caracteres ao todo, como `RAPI48291736`) — número de rastreamento da Kuehne+Nagel, também reconhecido exclusivamente como Kuehne+Nagel.
- **2 dígitos, `A` e 9 dígitos** (12 caracteres ao todo, como `26A482917360`) — número de rastreamento rodoviário. O formato também é compatível com [Australia Post](carrier:australia-post) e [Colis Privé](carrier:colis-prive), então o Peek consulta Australia Post, Colis Privé e Kuehne+Nagel em sequência (colocando a Kuehne+Nagel em segundo lugar na Suíça e na Alemanha).

Para carga aérea e marítima, remetentes também podem informar um conhecimento Kuehne+Nagel, *house bill of lading* ou referência de cliente fora desses três padrões. Ao colar um link de `mykn.kuehne-nagel.com/public-tracking/shipments?query=...`, o Peek extrai a referência e direciona diretamente para a Kuehne+Nagel. Para comparar códigos de outras redes de carga e encomendas, veja o [guia de formatos de código de rastreamento](guide:tracking-number-formats).

## Como funcionam os marcos da Kuehne+Nagel

O rastreador público da Kuehne+Nagel divide a remessa em dois fluxos: paradas físicas ao longo da rota (origem, terminal de partida, hub de trânsito, gateway, terminal de chegada e destino) e avisos de informação de transporte (reservas, registros alfandegários e documentos de comprovante de entrega).

| Texto da Kuehne+Nagel | O que significa |
| --- | --- |
| Booking submission / Booking confirmation | O pedido foi registrado no sistema da Kuehne+Nagel antes de a carga se mover; uma edição tardia da reserva nunca faz uma remessa já em movimento retroceder. |
| Ready for pickup / Arrival (at origin) | Carga pronta no remetente ou caminhão de coleta chegou, antes da confirmação de carregamento. |
| Pickup | A Kuehne+Nagel coletou a carga no remetente. |
| Arrival / Departure / Load on vehicle / Load on vessel | Leituras físicas em terminal, cross-dock, terminal ferroviário, aeroporto ou porto no trecho principal. |
| Customs entry start / Customs duties payment / Entry documents required | Desembaraço aduaneiro de exportação ou importação em andamento, ou autoridades solicitaram documentos, tributos ou inspeção de órgãos anuentes (como FDA ou PGA). |
| Customs entry release / Export customs entry release / Import customs entry release | A alfândega ou agência de fronteira liberou a carga para seguir viagem. |
| In delivery | O veículo rodoviário saiu para a entrega final ou chegou ao endereço de entrega. |
| Delivery / Freight collection / Proof of delivery available | Carga entregue ao destinatário ou retirada no terminal; `Delivery with discrepancies` ainda marca a entrega, registrando falta ou dano. |
| Delivery attempt failed / Delivery not possible | Uma tentativa de entrega não foi concluída — por exemplo, recusa pelo destinatário. |
| Shipment on hold / Delivery instructions pending / Missing/incomplete document | A remessa está pausada em uma unidade ou ponto aduaneiro até o envio de documentos, pagamento ou instruções de entrega pendentes. |

Alguns rótulos merecem atenção especial:

- **Available at CY / Available at CFS / Ready for collection / Available at rail terminal** significam que a carga chegou a um pátio marítimo de contêineres (CY), estação CFS, aeroporto ou terminal ferroviário e aguarda repasse ou liberação. A Kuehne+Nagel ou uma parceira ainda pode fazer a entrega rodoviária final, por isso essas leituras permanecem em trânsito em vez de marcar entrega final.
- **Freight handover** significa que a Kuehne+Nagel entregou a carga a um despachante ou transportadora subsequente, não ao destinatário final.
- **Avisos documentais e administrativos** como `HAWB available`, `MAWB available`, `Transport document available`, `VGM closure`, `Delivery appointment submission` e `Customs billing invoice available` registram etapas administrativas e não alteram a etapa física da remessa.
- **Empty container gate-in e vehicle departure at destination** ocorrem após a conclusão da entrega e mantêm o status como entregue.

## Entrega, alfândega e atrasos

`Delivery appointment submission` registra uma data de entrega combinada para depois. Não é uma leitura física: a remessa só muda para saída para entrega quando aparece `In delivery` ou a chegada (`Arrival`) do caminhão ao endereço de entrega.

Se o rastreamento mostrar `Awaiting consignee's feedback`, `Awaiting consignee's payment` ou `Entry documents required`, a alfândega aguarda resposta, pagamento ou documentação do destinatário. `Unable to file customs entry - missing details or documentation` indica que a declaração aduaneira não pode ser protocolada até que esses dados cheguem. Veja o [guia de alfândega](guide:customs) para entender como funcionam o desembaraço e as taxas de importação.

Datas planejadas exibidas no myKN são previsões, não leituras efetivamente concluídas. Para orientações gerais sobre rastreamento parado, leia [por que o rastreamento para de atualizar](guide:tracking-not-updating).

## Rastreando Kuehne+Nagel com o Peek

Ao colar um código `RWM`, `RAPI` ou um link `mykn.kuehne-nagel.com/public-tracking/shipments?query=...` no [Peek](/), a Kuehne+Nagel é reconhecida imediatamente. Para códigos `26A...`, o Peek consulta as transportadoras citadas acima em sequência e exibe "Kuehne+Nagel tem esta encomenda." quando ela responde. Para conhecimentos ou referências de cliente sem esses formatos, selecione Kuehne+Nagel em **Alterar** ao adicionar ou **Alterar transportadora** na tela de rastreamento.

O Peek lê diretamente a busca pública de remessas, os marcos da rota e os avisos de transporte da Kuehne+Nagel por meio do [adaptador open-source Kuehne+Nagel do universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/kuehne-nagel). Ele registra apenas marcos concluídos (ignorando datas previstas) e lê o peso total em quilogramas e o país de destino quando retornados. Quando a busca localiza a carga por uma referência de cliente, o Peek mostra o código próprio da Kuehne+Nagel ao lado do seu, em "Kuehne+Nagel rastreia com o código"; se a referência corresponder a várias remessas, o Peek não consegue distinguir qual é a sua. Quando o sistema emissor informa o fuso horário, o Peek preserva esse instante exato; caso contrário, aplica o fuso da localidade (Berlim para Hamburgo, Zurique para Basileia), usa o fuso vizinho quando não há local ou o horário de Zurique por padrão, exibindo tudo no relógio do seu dispositivo.

O Peek organiza os marcos da Kuehne+Nagel em etapas claras: **Etiqueta criada** para reservas e avisos de prontidão para coleta, **Recebida pela transportadora** para coleta, **Em trânsito** para movimentações em hubs, portos, ferrovias e repasses, **Na alfândega** para desembaraço e retenções regulatórias, **Saiu para entrega** para `In delivery` e chegada do caminhão ao destino, **Tentativa de entrega** para tentativas sem sucesso, **Precisa de atenção** para retenções, coletas falhas, cancelamentos e falta de documentos, e **Entregue** para entrega, retirada de carga ou comprovante concluído. Avisos administrativos ficam na linha do tempo sem retroceder a etapa atual, e um registro alfandegário pós-entrega não desfaz o status entregue.

Enquanto você mantém o envio aberto no Peek, ou no dia da entrega com notificações ativadas, o Peek consulta até a cada 10 minutos; no dia em que a Kuehne+Nagel mostra `In delivery` ou chegada (`Arrival`) do caminhão no endereço de destino, a cada 2 minutos a partir das 6h no relógio da leitura mais recente, enquanto o rastreador próprio responder. Nos demais casos, consulta de hora em hora. Após 12 horas apenas em "Etiqueta criada" ou "Recebida pela transportadora", ou dois dias sem novidades, consulta de hora em hora em qualquer modo, e a cada 6 horas após sete dias em "Etiqueta criada". Quando a Kuehne+Nagel não responde e o Peek acompanha apenas por serviços de rastreamento, consulta de hora em hora, a cada 3 horas após um dia sem movimento e até a cada 15 minutos no dia de saída para entrega.

Quando a leitura mais recente tem quatro dias e o envio segue em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito", o Peek sinaliza a data dessa leitura, como "Sem atualização de rastreamento desde seg 12 out". A página então informa "É comum uma encomenda ficar sem atualizações em voos longos, na alfândega ou no fim de semana. O Peek continua consultando Kuehne+Nagel." Após três semanas sem novas leituras, mostra "Nenhuma novidade há mais de três semanas. Se ainda não chegou, fale com o vendedor. O Peek continua consultando Kuehne+Nagel."

Você pode configurar alertas para **Cada leitura**, **Etapas importantes** ou **Apenas entrega** nas configurações da encomenda.

## Como contatar a Kuehne+Nagel

O site da Kuehne+Nagel oferece busca de unidades e formulário de contato:

- **Site global e portal de rastreamento:** [kuehne-nagel.com](https://www.kuehne-nagel.com/) e [myKN Public Tracking](https://mykn.kuehne-nagel.com/public-tracking/)
- **Diretório de escritórios locais:** [Unidades da Kuehne+Nagel no mundo](https://www.kuehne-nagel.com/company/locations) para localizar a filial mais próxima
- **Formulário de consulta online:** [Talk to an expert](https://www.kuehne-nagel.com/contact/talk-to-an-expert) para receber orientação por e-mail
- **Outras redes de carga:** se o pedido foi dividido entre transportadoras, compare com [DHL Freight](carrier:dhl-freight) e [DSV](carrier:dsv).

## Perguntas frequentes

### Por que meu código Kuehne+Nagel diz que nenhum resultado foi encontrado?

O rastreador público da Kuehne+Nagel cobre apenas remessas iniciadas nos últimos 90 dias. Confira se não faltou nenhum caractere e, caso a reserva seja recente, aguarde o primeiro marco concluído. Se uma referência corresponder a várias remessas ao mesmo tempo, peça ao remetente o número próprio `RWM`, `RAPI` ou outro código da Kuehne+Nagel.

### O que significam "Service complete" ou "Freight handover" no rastreamento da Kuehne+Nagel?

`Freight handover` significa que a Kuehne+Nagel transferiu a carga para um despachante aduaneiro ou transportadora seguinte, portanto o envio continua a caminho de você. `Service complete` marca o encerramento do trecho contratado com a Kuehne+Nagel — que pode ser uma entrega em porto ou aeroporto, e não na sua porta —, então verifique se foi emitido um código separado para a transportadora final.

### Por que algumas datas exibidas no myKN não aparecem no histórico do Peek?

A visão pública de rota do myKN mistura datas previstas de planejamento e avisos futuros sem data com leituras concluídas. O Peek registra somente os marcos que a Kuehne+Nagel marca como efetivamente realizados, para que metas previstas não apareçam como etapas concluídas.

### Por quanto tempo o rastreamento público da Kuehne+Nagel fica disponível?

O rastreamento público em `mykn.kuehne-nagel.com` cobre remessas iniciadas nos últimos 90 dias. Para ver mais detalhes, o myKN oferece cadastro gratuito.

:::sources
- [Site global da Kuehne+Nagel](https://www.kuehne-nagel.com/)
- [myKN Public Tracking](https://mykn.kuehne-nagel.com/public-tracking/)
- [Unidades da Kuehne+Nagel no mundo](https://www.kuehne-nagel.com/company/locations)
- [Kuehne+Nagel Talk to an expert](https://www.kuehne-nagel.com/contact/talk-to-an-expert)
- [Adaptador Kuehne+Nagel do universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/kuehne-nagel)
:::
