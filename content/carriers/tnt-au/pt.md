---
title: Rastreio TNT Australia: números, estados e apoio
description: Segue envios domésticos TNT Australia: códigos de 3 letras + 9 dígitos, estados de depósito, nova entrega seg–sex e linha de apoio 13 11 50.
slug: rastreio-tnt-australia
published: 2026-11-24
updated: 2026-11-24
---

A TNT Australia (*TNT Express Australia*, em integração com a FedEx Express) transporta encomendas, documentos e paletes por toda a Austrália através dos seus depósitos ([tnt.com/express/en_au](https://www.tnt.com/express/en_au/site/home.html)). Os envios internacionais têm um rastreio separado (ver [TNT](carrier:tnt)); os envios domésticos australianos dispõem de uma consulta própria em [tntexpress.com.au](https://www.tntexpress.com.au/interaction/Trackntrace.aspx).

Podes consultar um envio doméstico australiano em [TNT Australia Track & Trace](https://www.tntexpress.com.au/interaction/Trackntrace.aspx) ou colar o teu número de expedição de 12 caracteres na caixa acima.

## Formatos de número de rastreio da TNT Australia

O Peek acompanha os envios domésticos da TNT Australia através de um número de 12 caracteres:

| Formato (inventado) | O que é |
| --- | --- |
| `SYD482917360` | **3 letras maiúsculas + 9 dígitos** (12 caracteres no total) — número padrão de envio doméstico da TNT Australia rastreado em `tntexpress.com.au`. |

Como 3 letras seguidas de 9 dígitos também coincidem com números da [Purolator](carrier:purolator), o Peek consulta as duas transportadoras por ordem: primeiro a Purolator no Canadá e primeiro a TNT Australia noutros países. Mostra «TNT Australia tem esta encomenda.» assim que a TNT Australia responde. Alguns prefixos, como `RWM` da Kuehne+Nagel, ficam fora do formato da TNT Australia. Uma ligação direta `tntexpress.com.au/interaction/ASPs/trackref_detail.asp?con=...` ou `trackref.aspx?con=...` vai de imediato para a TNT Australia.

O [Centro de Ajuda da TNT Australia](https://www.tnt.com/express/en_au/site/support.html) chama número de envio (*shipment / consignment number*) ao código de **9 dígitos** do lado esquerdo da fatura, cerca de 3 cm abaixo do número de cliente. O rastreio da TNT Australia no Peek aceita apenas 3 letras mais 9 dígitos. Para outros formatos de encomendas, consulta o guia de [formatos de números de seguimento](guide:tracking-number-formats).

## O que significam os estados de rastreio da TNT Australia

A página de detalhe doméstico da TNT Australia apresenta quatro colunas — `Status`, `Date`, `Time` e `Depot` — bem como uma data `Estimated Delivery by:` no topo, quando disponível.

| Texto da TNT Australia | O que significa |
| --- | --- |
| `Your shipment data is lodged` | O remetente enviou eletronicamente os dados da expedição; a TNT Australia ainda não recolheu a encomenda. |
| `We've collected your shipment` | A TNT Australia recolheu o envio junto do remetente, antes do primeiro registo em depósito. |
| `We're processing your shipment` | Registada num depósito da TNT Australia de origem, trânsito ou distribuição. |
| `Your shipment's with a driver` | Em distribuição com um motorista para entrega hoje em casa ou numa empresa. |
| `New delivery address required` | A volta de entrega não conseguiu entregar na morada indicada; a encomenda fica retida no depósito local a aguardar nova morada ou levantamento. |
| `We've delivered your shipment` | Entregue na morada do destinatário. |
| `Your shipment was collected` | O destinatário levantou a encomenda presencialmente no depósito TNT que a guardava (distinto de `We've collected your shipment`, que corresponde à recolha junto do remetente). |
| `Book-In Delivery Requested` / `Book-In Delivery Arranged` | O destinatário pediu uma entrega agendada e a TNT confirmou-a. São avisos e não registos: mantêm a etapa da encomenda e a confirmação pode surgir após a entrega. |

## Nova entrega, levantamento em depósito e atrasos

O [Centro de Ajuda da TNT Australia](https://www.tnt.com/express/en_au/site/support.html) explica como proceder quando falhas uma entrega ou precisas de remarcar:

- **Se o motorista deixou um aviso:** usa o TNT Re-delivery Service para marcar uma nova data de entrega.
- **Se não foi deixado aviso (ou se ainda não houve tentativa de entrega):** contacta a TNT Australia com o número de expedição, a morada completa e a data desejada para a nova entrega.
- **Novas entregas apenas de segunda a sexta-feira:** a TNT Australia refere que as encomendas só podem ser entregues novamente entre segunda e sexta-feira.
- **`New delivery address required`:** a encomenda permanece no depósito indicado na coluna `Depot` até dares outra morada ou a levantares; contacta a TNT Australia. Consulta também os guias de [entrega falhada](guide:missed-delivery) e [quando o rastreio deixa de atualizar](guide:tracking-not-updating).

Se a tua encomenda na Austrália circula noutra transportadora doméstica, compara o acompanhamento com a [Australia Post](carrier:australia-post), a [Aramex Australia](carrier:aramex-au), a [CouriersPlease](carrier:couriersplease) ou a [Border Express](carrier:border-express).

## Acompanhar a TNT Australia com o Peek

Cola no Peek o teu número doméstico de 3 letras + 9 dígitos ou uma ligação de `tntexpress.com.au`. Se o Peek a associar a outra transportadora, usa «Alterar» ao adicionar a encomenda ou «Alterar transportadora» no ecrã da encomenda.

O Peek consulta diretamente a página doméstica da TNT Australia através do [adaptador TNT Australia em código aberto no universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/tnt-au), repetindo automaticamente em caso de falha por tempo limite de rede. Lê cada descrição de registo, depósito e hora, além da data `Estimated Delivery by:` até à entrega. A TNT Australia apresenta as horas de depósito sem desfasamento em relação a UTC: o Peek data cada registo no fuso do respetivo depósito (hora de Sydney para Sydney, hora de Perth para Perth, e assim por diante), um registo sem depósito no fuso do registo vizinho e, na falta deste, na hora de Sydney, mostrando depois cada registo na hora do teu dispositivo. A tabela doméstica não inclui peso, nome do serviço nem códigos de estado.

O Peek traduz os textos da TNT Australia em etapas claras: «Etiqueta criada» (`Your shipment data is lodged`), «Aceite pela transportadora» (`We've collected your shipment`), «Em trânsito» (`We're processing your shipment`), «Em distribuição» (`Your shipment's with a driver`), «Problema reportado» (`New delivery address required`) e «Entregue» (`We've delivered your shipment` ou `Your shipment was collected`). Os avisos de agendamento (`Book-In Delivery Requested` e `Book-In Delivery Arranged`) permanecem no histórico sem reverter uma entrega concluída nem alterar a etapa atual.

Enquanto tens a encomenda aberta no Peek, ou no dia da entrega com as notificações ativadas, o Peek consulta até de 10 em 10 minutos; no dia em que a TNT Australia apresenta `Your shipment's with a driver`, de 2 em 2 minutos a partir das 6h00 no relógio do registo mais recente enquanto o rastreio da TNT Australia responder. Caso contrário, o Peek verifica de hora a hora. Após 12 horas apenas em «Etiqueta criada» ou «Aceite pela transportadora», ou dois dias sem novidades, verifica de hora a hora independentemente da forma como acompanhas a encomenda, e de 6 em 6 horas após sete dias em «Etiqueta criada». Quando a TNT Australia não responde e o Peek acompanha a encomenda apenas através de serviços de rastreio, verifica de hora a hora, de 3 em 3 horas após um dia sem novidades e até de 15 em 15 minutos no dia em que a encomenda está em distribuição, enquanto tiveres a encomenda aberta ou as notificações ativadas.

Quando o registo mais recente tem quatro dias e a encomenda continua em «Etiqueta criada», «Aceite pela transportadora» ou «Em trânsito», o Peek assinala-a com o dia desse registo, como «Sem atualizações desde seg 12 out». A página da encomenda apresenta então «É habitual uma encomenda ficar sem novidades em voos longos, na alfândega ou ao fim de semana. O Peek continua a perguntar a TNT Australia.», com a ligação «Pede ajuda a TNT Australia» para o Centro de Ajuda da TNT Australia. Passadas três semanas sem novos registos, mostra «Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, contacta o vendedor. O Peek continua a perguntar a TNT Australia.»

Escolhe «Todos os registos», «Passos importantes» ou «Só a entrega» nas definições de alertas do Peek para definir quando recebes notificações.

## Contactar a TNT Australia

- **Telefone (na Austrália):** `13 11 50` (publicado em todo o site da TNT Australia)
- **Centro de Ajuda e nova entrega:** [Apoio TNT Australia](https://www.tnt.com/express/en_au/site/support.html)
- **Portal de rastreio doméstico:** [tntexpress.com.au Track & Trace](https://www.tntexpress.com.au/interaction/Trackntrace.aspx) (rastreia até 10 envios domésticos, Sameday Local Jobs ou 1 referência eletrónica de cada vez)
- **Site da TNT Australia e rastreio internacional:** [tnt.com/express/en_au](https://www.tnt.com/express/en_au/site/home.html) e [visão geral Track & Trace](https://www.tnt.com/express/en_au/site/shipping-tools/tracking.html)

Tem à mão o teu número doméstico de 12 caracteres e a morada de entrega quando ligares para o `13 11 50` ou marcares uma nova entrega num dia útil.

## Perguntas frequentes

### Qual é a diferença entre `We've collected your shipment` e `Your shipment was collected`?

`We've collected your shipment` surge no início do percurso, quando a TNT Australia recolhe a encomenda junto do remetente. `Your shipment was collected` surge no fim, quando o destinatário levanta presencialmente uma encomenda retida no depósito TNT, pelo que o Peek a marca como «Entregue».

### Porque apareceu `Book-In Delivery Arranged` depois de a minha encomenda já estar entregue?

`Book-In Delivery Arranged` confirma uma entrega agendada pela TNT Australia — é um aviso e não um registo, podendo ser lançado depois da entrega propriamente dita. O Peek mantém a encomenda em «Entregue» em vez de a fazer recuar.

### A TNT Australia entrega ou faz novas entregas ao fim de semana?

O [Centro de Ajuda da TNT Australia](https://www.tnt.com/express/en_au/site/support.html) indica que as entregas remarcadas só podem ser feitas entre **segunda e sexta-feira**.

### O meu número TNT tem 9 dígitos sem letras — porque não funciona como TNT Australia no Peek?

O rastreio da TNT Australia no Peek aceita 3 letras seguidas de 9 dígitos, o formato dos envios domésticos lidos em `tntexpress.com.au`. O Centro de Ajuda da TNT designa um número de 9 dígitos na fatura como número de envio; experimenta-o em [TNT Australia Track & Trace](https://www.tnt.com/express/en_au/site/shipping-tools/tracking.html) ou liga para o `13 11 50`.

:::sources
- [TNT Australia](https://www.tnt.com/express/en_au/site/home.html)
- [TNT Australia Help Center](https://www.tnt.com/express/en_au/site/support.html)
- [TNT Australia Track & Trace](https://www.tnt.com/express/en_au/site/shipping-tools/tracking.html)
- [TNT Express Australia Domestic Tracker](https://www.tntexpress.com.au/interaction/Trackntrace.aspx)
- [universal-parcel-scraper TNT Australia adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/tnt-au)
:::
