---
title: Rastreamento Ekart Índia: códigos, status e RTO explicados
description: Rastreie encomendas da Ekart na Índia: formato do código de rastreamento, significados de InscannedAtDH e RTO, entregas não realizadas e o que fazer.
slug: rastreamento-ekart
published: 2026-11-24
updated: 2026-11-24
---

A Ekart (também chamada eKart Logistics) é uma transportadora indiana que coleta pedidos online com os vendedores, movimenta os volumes por seus centros de triagem e realiza a entrega final. O vendedor ou a loja fornece o código de rastreamento (*tracking ID*): procure-o na mensagem de envio ou solicite à loja e cole na caixa acima. O rastreador oficial da Ekart fica em ekartlogistics.in.

## Como é o código de rastreamento da Ekart

O código do Peek reconhece os *tracking IDs* da Ekart como três letras, depois `C`, `P` ou `R`, e dez dígitos: 14 caracteres no total. Com dígitos fictícios:

| Código (fictício) | O que é |
| --- | --- |
| `FMPP4829105736` | Um dos grupos de letras vinculados à Ekart (listados abaixo), seguido de `C`, `P` ou `R` e dez dígitos. O Peek identifica a Ekart só pelo formato. |
| `ABCP4829105736` | Outras três letras na frente, mesma estrutura. Pode ser Ekart, mas o Peek não afirma pelo formato e consulta a Ekart (veja abaixo). |

Os grupos de três letras vinculados à Ekart no Peek são `AMI`, `BAL`, `BGS`, `BSI`, `DEL`, `FLO`, `FMP`, `GLA`, `LUA`, `MYE`, `MYN`, `MYS`, `SDL`, `SHO`, `SPO`, `SRT` e `TEL`. Espaços e letras minúsculas não interferem ao colar no Peek. Duas exceções: um código nesse formato iniciado por `PB` é lido como Parcelforce (Reino Unido), e um iniciado por `HRBC` não é sugerido como Ekart por coincidir com o formato do AliExpress. Se o envio veio de uma loja indiana, toque em "Trocar" e escolha Ekart.

Use o código de rastreamento, não o número do pedido da loja. Não achou? Procure um link da Ekart na mensagem da loja ou veja [como encontrar seu código de rastreamento](guide:find-tracking-number).

## Status de rastreamento da Ekart explicados

O rastreador da Ekart lista cada movimentação com data, hora e cidade. Algumas linhas usam inglês direto, como `Shipment Created`; outras combinam um código, traço e nome do hub, como `InscannedAtDH` seguido do hub. As linhas reconhecidas pelo código do Peek, em ordem aproximada:

| A Ekart mostra | O que significa | O Peek exibe |
| --- | --- | --- |
| `Pickup Requested`, `Shipment Created` | O vendedor solicitou a coleta; a Ekart ainda não recebeu o pacote. | "Etiqueta criada" |
| `OutForPickupEvent` | Equipe de coleta da Ekart a caminho do vendedor; o volume ainda está lá. | "Etiqueta criada" |
| `NotPickedEvent` | Coleta falhou; o pacote segue com o vendedor. | "Etiqueta criada" |
| `Pickup From Seller`, `PickupReceived` | A Ekart coletou a encomenda com o vendedor. | "Recebida pela transportadora" |
| `ShipmentGrouped`, `ShipmentInTransit`, `dispatched_to_tc`, `dispatched`, `Dispatched to` + próximo hub | Em trânsito entre centros da rede Ekart. | "Em trânsito" |
| `Expected` | O hub de destino foi avisado de que o pacote está chegando. | "Em trânsito" |
| `Received`, `Received at` + hub | Chegou a um hub da Ekart. | "Em trânsito" |
| `InscannedAtFacility` | Escaneado na entrada de uma unidade da Ekart. | "Em trânsito" |
| `InscannedAtDH`, `PrimaryDHInscan` | Chegou ao centro de entrega (DH, *Delivery Hub*) da sua região: a entrega vem a seguir. | "Em trânsito" |
| `Out For Delivery` | Em rota de entrega hoje. | "Saiu para entrega" |
| `Delivered` | Entregue a você. Após uma linha RTO, indica devolução entregue ao vendedor (veja abaixo). | "Entregue" |
| `undelivered_attempted`, `ShipmentUndeliveredAttempted` | Entrega não realizada (`undelivered_update` pode surgir no mesmo instante). | "Tentativa de entrega" |
| `undelivered_unattempted` | A rota do dia terminou sem tentativa de entrega. | "Tentativa de entrega" |
| `Unsuccessful` + motivo (como `Unsuccessful Unable to attempt delivery due to operational issues` ou `Unsuccessful Undelivered. (Rescheduled to: …)`) | A rota terminou sem entrega, por extenso; a data após `Rescheduled to:` é o dia previsto da próxima tentativa. Linhas `Unsuccessful` ligadas à coleta não entram aqui. | "Tentativa de entrega" |
| `PickupCancel` | A coleta foi cancelada antes de o volume entrar na rede Ekart. | "Precisa de atenção" |
| `ShipmentRtoConfirmed` | Retorno ao remetente (RTO) confirmado: o pacote está voltando. | "Precisa de atenção" |
| `Marked RTO: ORDER_REJECT` | Após rotas sem entrega, marcado para voltar ao vendedor, com o motivo após os dois-pontos; linhas seguintes são do retorno. | "Precisa de atenção" |
| `RtoExpected`, `RtoReceived`, `ReturnInscanned` | A caminho de volta para o vendedor. | "Em trânsito" |

**RTO.** O Peek interpreta `ShipmentRtoConfirmed`, `Marked RTO` ou qualquer código iniciado por `Rto` ou `Return` como início do retorno ao remetente: dali em diante cada linha pertence à devolução. `InscannedAtDH` passa a indicar o hub do vendedor, não o seu, e um `Delivered` posterior significa que voltou ao vendedor — exibido no Peek como "Devolvida ao remetente", não "Entregue". Veja mais em [o que significam os status de rastreamento](guide:tracking-statuses).

## Como a Ekart entrega na Índia

O código do Peek cobre a Ekart apenas na Índia. O percurso segue as etapas acima: coleta no vendedor, hubs intermediários, centro de entrega próximo a você e rota final, sem repasse a outra transportadora.

Esta página não informa prazos de entrega, regras de tentativas ou reagendamento, e o código do Peek não especifica quantas tentativas a Ekart faz após `undelivered_attempted` nem quando inicia a devolução: pergunte à loja que contratou a Ekart. Veja [entrega não realizada](guide:missed-delivery).

## Rastreamento Ekart parado, RTO ou encomenda não entregue

**Parado em `Shipment Created` ou `Pickup Requested`.** O vendedor registrou o envio, mas ainda não entregou o pacote à Ekart. `NotPickedEvent` indica falha na coleta. Pergunte ao vendedor quando será despachado.

**Dias sem nova leitura.** Quando um pacote registrado, coletado ou em trânsito passa quatro dias sem atualização, o Peek destaca a data da última leitura ("Sem atualizações desde seg 12 out"). Pergunte à loja o que está retendo o envio. Veja [por que o rastreamento para de atualizar](guide:tracking-not-updating).

**`undelivered_attempted`, `undelivered_unattempted` ou `Unsuccessful`.** A entrega não ocorreu; `Rescheduled to:` mostra o dia da nova tentativa. As linhas seguintes indicarão nova saída ou devolução. Confirme se a loja tem seu endereço e telefone corretos.

**`ShipmentRtoConfirmed` ou `Marked RTO: ORDER_REJECT`.** A encomenda está voltando ao vendedor. Quando constar `Delivered` de retorno na Ekart, o Peek exibe "Devolvida ao remetente". Peça reembolso ou reenvio à loja: veja [devolvida ao remetente](guide:returned-to-sender).

**`PickupCancel`.** A coleta com o vendedor foi cancelada e o pacote nunca entrou na Ekart. Confirme com o vendedor se seu pedido segue ativo.

**Entregue, mas nada chegou.** Verifique com familiares, vizinhos e portaria/recepção do prédio, depois avise a loja. Veja [encomenda entregue mas não recebida](guide:delivered-not-received).

**Extravio ou dano.** Contate o vendedor ou marketplace onde comprou, pois ele contratou a Ekart. Veja [encomendas extraviadas](guide:lost-parcel) e [encomendas danificadas](guide:damaged-parcel).

## Atendimento e contato da Ekart

O site da Ekart não lista telefone nem e-mail para destinatários; seu FAQ direciona vendedores a abrir chamado no portal, respondido em até 24 horas. Sobre seu pedido, fale primeiro com a loja ou marketplace: foi ela quem contratou a Ekart e quem emite reembolso ou reenvio.

Antes de pagar qualquer cobrança recebida por SMS sobre entregas, leia [golpes de SMS de entrega](guide:delivery-scam-texts).

## Como rastrear uma encomenda da Ekart no Peek

Cole o código de rastreamento no [Peek](/). Com um dos grupos de letras acima seguido de `C`, `P` ou `R`, o Peek identifica a Ekart na hora ("Transportadora detectada"); "Trocar" escolhe outra transportadora. Com outras letras iniciais, exibe "Consultando serviços de rastreamento…" e verifica na Ekart se ela reconhece o código: havendo movimentação, mostra "Ekart está com esta encomenda." e acompanha pela Ekart. Se a Ekart ainda não tiver registro, consulta serviços de rastreamento; para ver as linhas da própria Ekart mesmo assim, toque em "Trocar" e escolha Ekart em "Compatível com este código".

Links de rastreamento da Ekart (ekartlogistics.com com `shipmenttrack` e o código) também funcionam e identificam Ekart quaisquer que sejam as letras iniciais. Se o vendedor mandou um link de shiprocket.co, você pode colar também, mas o Peek acompanhará pela página da Shiprocket, não pela Ekart. Selecionar Ekart manualmente para qualquer formato diferente de quatro letras e dez dígitos exibe "não emite códigos como este".

Enquanto aguarda a primeira leitura da Ekart, o pacote mostra "Aguardando Ekart". Dois dias após a Ekart responder pela primeira vez que não conhece o número, o Peek sugere conferir o código, trocar a transportadora ou excluir a encomenda. Um pacote adicionado sem transportadora exibe "Ainda sem histórico de rastreamento". O Peek lê o rastreador da própria Ekart sem conta nem telefone: cada linha nas palavras da Ekart (incluindo códigos), com horário exato e cidade, da mais recente à mais antiga e sem duplicatas. Os horários aparecem no fuso do seu dispositivo; em peektracker.com e nos apps de iPhone e Android, o histórico termina com "Horários no seu fuso horário:" e o nome do fuso. A data prevista de entrega da Ekart aparece enquanto o pacote vai até você e some após entrega ou retorno.

Enquanto o pacote está aberto no Peek, ou no dia da entrega com alertas ligados, o Peek verifica até a cada 10 minutos; no dia em que a Ekart registra saída para entrega, enquanto o rastreador da própria Ekart responde, a cada 2 minutos a partir das 6h da Índia (pelos serviços de rastreamento, até a cada 15 minutos nesse dia, enquanto o pacote estiver aberto ou com alertas ligados); fora disso, de hora em hora. Após dois dias sem novidades (ou 12 horas ainda em "Etiqueta criada" ou "Recebida pela transportadora"), verifica de hora em hora. Alertas: "Cada movimentação", "Etapas importantes" ou "Só a entrega". O Peek não pode falar com a Ekart nem com a loja por você.

## Perguntas frequentes sobre rastreamento Ekart

### Como rastrear minha encomenda da Ekart?

Cole o código de rastreamento (*tracking ID*) da mensagem de envio da loja no Peek ou digite em ekartlogistics.in. Use o *tracking ID*, não o número do pedido.

### Como é um código de rastreamento da Ekart?

Três letras, depois `C`, `P` ou `R` e dez dígitos — por exemplo `FMPP4829105736` (fictício).

### O que significa RTO no rastreamento da Ekart?

Significa retorno ao remetente (*Return to Origin*). `ShipmentRtoConfirmed` ou `Marked RTO: ORDER_REJECT` inicia a devolução, e um `Delivered` depois indica que chegou de volta ao vendedor. Peça reembolso ou reenvio à loja.

### O que significa InscannedAtDH no rastreamento da Ekart?

Indica que o volume chegou a um centro de entrega (*Delivery Hub*) da Ekart: na ida, é o centro que atende seu endereço; após RTO, é o centro do vendedor.

### Quem contatar sobre uma encomenda extraviada da Ekart?

A loja ou marketplace onde você comprou, que contratou a Ekart. O site da Ekart não divulga telefone de suporte para destinatários.

:::sources
- [Ekart Logistics: FAQ](https://www.ekartlogistics.in/faq) – suporte a vendedores por chamado no portal com resposta em até 24 horas
- [Universal Parcel Scraper: Ekart](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/ekart) – nomes, formatos do código e como o Peek os reconhece, links de rastreamento, o rastreador da Ekart, textos e códigos das leituras com suas etapas, RTO, data prevista de entrega
- [Universal Parcel Scraper: Shiprocket](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/shiprocket) – links de rastreamento da Shiprocket
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formatos PB da Parcelforce e HRBC do AliExpress, transportadoras consultadas no reconhecimento, transferências, o que o Peek lê e mostra
:::
