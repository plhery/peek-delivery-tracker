---
title: Rastreio TNT Australia: códigos domésticos, status e ajuda
description: Rastreie envios domésticos TNT Australia: códigos de 3 letras + 9 dígitos, status de depósito, reentrega de segunda a sexta e suporte 13 11 50.
slug: tnt-australia-tracking
published: 2026-11-27
updated: 2026-11-27
---

A TNT Australia (*TNT Express Australia*, em integração com a FedEx Express) transporta encomendas, documentos e paletes por toda a Austrália por meio de seus depósitos ([tnt.com/express/en_au](https://www.tnt.com/express/en_au/site/home.html)). Remessas internacionais usam um rastreador separado (veja [TNT](carrier:tnt)); já remessas domésticas australianas contam com consulta dedicada em [tntexpress.com.au](https://www.tntexpress.com.au/interaction/Trackntrace.aspx).

Você pode consultar uma remessa doméstica australiana no [TNT Australia Track & Trace](https://www.tntexpress.com.au/interaction/Trackntrace.aspx) ou colar seu código de 12 caracteres na caixa acima.

## Formatos de código de rastreio TNT Australia

O Peek acompanha remessas domésticas da TNT Australia por códigos de 12 caracteres:

| Formato (inventado) | O que é |
| --- | --- |
| `SYD482917360` | **3 letras maiúsculas + 9 dígitos** (12 caracteres no total) — código doméstico padrão da TNT Australia consultado em `tntexpress.com.au`. |

Como 3 letras seguidas de 9 dígitos também correspondem a códigos usados por [Colis Privé](carrier:colis-prive) e Purolator, o Peek consulta as três em ordem: TNT Australia primeiro na Austrália, Purolator primeiro no Canadá e Colis Privé primeiro nos demais países, confirmando assim que a TNT Australia responde. Alguns prefixos, como `RWM` da Kuehne+Nagel, ficam fora dessa regra. Um link direto `tntexpress.com.au/interaction/ASPs/trackref_detail.asp?con=...` ou `trackref.aspx?con=...` vai imediatamente para a TNT Australia.

A [Central de Ajuda da TNT Australia](https://www.tnt.com/express/en_au/site/support.html) chama de número de remessa (*consignment number*) o número de **9 dígitos** à esquerda da fatura, cerca de 3 cm abaixo do número do cliente. O rastreador TNT Australia do Peek aceita apenas 3 letras + 9 dígitos. Para outros padrões australianos, consulte o [guia de formatos de código de rastreio](guide:tracking-number-formats).

## Significado dos status de rastreio TNT Australia

A página doméstica da TNT Australia exibe quatro colunas — `Status`, `Date`, `Time` e `Depot` — além de uma previsão `Estimated Delivery by:` no topo quando disponível.

| Texto da TNT Australia | O que significa |
| --- | --- |
| `Your shipment data is lodged` | O remetente transmitiu os dados eletronicamente; a TNT Australia ainda não coletou o volume. |
| `We've collected your shipment` | A TNT Australia coletou a remessa com o remetente, antes da primeira leitura em depósito. |
| `We're processing your shipment` | Registrada em um depósito de origem, trânsito ou entrega da TNT Australia. |
| `Your shipment's with a driver` | Saiu no veículo de entrega para residência ou empresa hoje. |
| `New delivery address required` | A rota não conseguiu entregar no endereço informado; a encomenda aguarda no depósito local um novo endereço ou retirada. |
| `We've delivered your shipment` | Entregue no endereço do destinatário. |
| `Your shipment was collected` | O destinatário retirou pessoalmente o pacote no depósito TNT (diferente de `We've collected your shipment`, que é a coleta inicial no remetente). |
| `Book-In Delivery Requested` / `Book-In Delivery Arranged` | O destinatário solicitou agendamento de entrega e a TNT confirmou. São avisos, não leituras: mantêm o estágio atual e a confirmação pode surgir após a entrega. |

## Reentrega, retirada em depósito e atrasos

A [Central de Ajuda da TNT Australia](https://www.tnt.com/express/en_au/site/support.html) orienta como proceder se perder uma entrega ou precisar reagendar:

- **Se o motorista deixou aviso:** use o serviço de reentrega da TNT (*Re-delivery Service*) para marcar nova data.
- **Se não houve aviso (ou ainda não houve tentativa):** fale com a TNT Australia informando o código da remessa, endereço completo e data desejada.
- **Reentregas somente de segunda a sexta:** a TNT Australia esclarece que reentregas reagendadas ocorrem apenas de segunda a sexta-feira.
- **`New delivery address required`:** o pacote permanece no depósito indicado na coluna `Depot` até você fornecer outro endereço ou retirá-lo; contate a TNT Australia. Veja também o [guia de entrega perdida](guide:missed-delivery) e [quando o rastreio para de atualizar](guide:tracking-not-updating).

Se seu pedido australiano viaja por outra transportadora local, compare em [Australia Post](carrier:australia-post), Aramex Australia, CouriersPlease ou Border Express.

## Como acompanhar TNT Australia no Peek

Cole seu código doméstico de 3 letras + 9 dígitos ou um link de `tntexpress.com.au` no Peek. Se o Peek associar a outra transportadora, use **Alterar** ao adicionar ou **Alterar transportadora** na tela do pacote.

O Peek consulta a página doméstica da TNT Australia diretamente pelo [adaptador TNT Australia de código aberto do universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/tnt-au), com nova tentativa automática em caso de tempo esgotado. Lê cada texto de status, depósito e horário, além da data `Estimated Delivery by:` até a conclusão. A TNT Australia informa horários sem fuso: o Peek interpreta cada leitura no fuso do depósito (Sydney para Sydney, Perth para Perth e assim por diante; sem depósito usa o da leitura vizinha ou Sydney) e converte para o relógio do seu aparelho. A tabela doméstica não informa peso, nome do serviço nem códigos numéricos.

O Peek traduz as mensagens em etapas claras: **Etiqueta criada** (`Your shipment data is lodged`), **Recebida pela transportadora** (`We've collected your shipment`), **Em trânsito** (`We're processing your shipment`), **Saiu para entrega** (`Your shipment's with a driver`), **Precisa de atenção** (`New delivery address required`) e **Entregue** (`We've delivered your shipment` ou `Your shipment was collected`). Avisos de agendamento (`Book-In Delivery Requested` e `Book-In Delivery Arranged`) ficam na linha do tempo sem reverter uma entrega concluída nem alterar a etapa.

Enquanto o pacote estiver aberto no Peek, ou no dia da entrega com notificações ligadas, o Peek consulta até a cada 10 minutos; no dia em que aparecer `Your shipment's with a driver`, a cada 2 minutos a partir das 6h no relógio da leitura mais recente, enquanto o site responder. Nos demais casos, verifica de hora em hora. Após 12 horas apenas em "Etiqueta criada" ou "Recebida pela transportadora", ou dois dias sem novidades, consulta a cada hora, e a cada 6 horas após sete dias em "Etiqueta criada". Quando a TNT Australia não responde e o Peek acompanha por serviços de rastreamento, consulta de hora em hora, a cada 3 horas após um dia sem novidades e até a cada 15 minutos no dia da saída para entrega.

Quando o registro mais recente completa quatro dias e o pacote segue em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito", o Peek destaca a data da leitura (por exemplo, "Sem atualização de rastreamento desde seg 12 out") com a nota "É comum uma encomenda ficar sem atualizações em voos longos, na alfândega ou no fim de semana. O Peek continua consultando TNT Australia." e link para a Central de Ajuda. Após três semanas sem novidade, sugere consultar o vendedor enquanto continua verificando.

Escolha **Cada atualização**, **Etapas importantes** ou **Somente entrega** nas notificações do Peek.

## Como entrar em contato com a TNT Australia

- **Telefone (dentro da Austrália):** `13 11 50` (divulgado em todo o site da TNT Australia)
- **Central de ajuda e reentrega:** [TNT Australia Support](https://www.tnt.com/express/en_au/site/support.html)
- **Portal de rastreamento doméstico:** [tntexpress.com.au Track & Trace](https://www.tntexpress.com.au/interaction/Trackntrace.aspx) (rastreia até 10 remessas domésticas, envios Sameday Local ou 1 referência eletrônica por vez)
- **Site TNT Australia e visão internacional:** [tnt.com/express/en_au](https://www.tnt.com/express/en_au/site/home.html) e [visão geral de Track & Trace](https://www.tnt.com/express/en_au/site/shipping-tools/tracking.html)

Tenha em mãos o código doméstico de 12 caracteres e o endereço de entrega ao ligar para `13 11 50` ou agendar uma reentrega em dia útil.

## Perguntas frequentes

### Qual é a diferença entre `We've collected your shipment` e `Your shipment was collected`?

`We've collected your shipment` ocorre no início da viagem, quando a TNT Australia coleta o pacote no remetente. Já `Your shipment was collected` ocorre no fim, quando o destinatário retira pessoalmente o pacote retido no depósito TNT — por isso o Peek marca como **Entregue**.

### Por que `Book-In Delivery Arranged` apareceu depois que meu pacote já foi entregue?

`Book-In Delivery Arranged` é a confirmação da TNT Australia sobre um agendamento de entrega — um aviso, não uma leitura física — e pode ser lançada após a entrega. O Peek mantém o status em **Entregue** sem retroceder.

### A TNT Australia faz entregas ou reentregas nos fins de semana?

A [Central de Ajuda da TNT Australia](https://www.tnt.com/express/en_au/site/support.html) afirma que entregas reagendadas só podem ser feitas entre **segunda e sexta-feira**.

### Meu código TNT tem 9 dígitos sem letras — por que não rastreia como TNT Australia no Peek?

O rastreador TNT Australia no Peek aceita 3 letras seguidas de 9 dígitos, padrão lido em `tntexpress.com.au`. A Central de Ajuda cita o número de 9 dígitos da fatura como número da remessa: consulte-o diretamente em [TNT Australia Track & Trace](https://www.tnt.com/express/en_au/site/shipping-tools/tracking.html) ou ligue para `13 11 50`.

:::sources
- [TNT Australia](https://www.tnt.com/express/en_au/site/home.html)
- [TNT Australia Help Center](https://www.tnt.com/express/en_au/site/support.html)
- [TNT Australia Track & Trace](https://www.tnt.com/express/en_au/site/shipping-tools/tracking.html)
- [TNT Express Australia Domestic Tracker](https://www.tntexpress.com.au/interaction/Trackntrace.aspx)
- [universal-parcel-scraper TNT Australia adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/tnt-au)
:::
