---
title: Seguimento Ekart: códigos ID, estados InscannedAtDH e RTO
description: Segue uma encomenda Ekart na Índia: formato do ID de 14 caracteres, significado de InscannedAtDH e RTO, tentativas de entrega e seguimento Peek.
slug: seguimento-ekart
published: 2026-11-21
updated: 2026-11-21
---

A Ekart (também conhecida como *eKart Logistics* e *Ekart Express*), lançada em 2009 segundo a página *About Us* e operada pela *Instakart Services Private Limited*, com sede em Bengaluru (Karnataka), é uma das maiores transportadoras indianas de comércio eletrónico. Recolhe encomendas online junto do vendedor, encaminha-as pelos seus centros de triagem e entrega em mais de 15 000 códigos PIN na Índia. Cola o ID de seguimento enviado pela loja na caixa acima ou introdu-lo na [página Track Order de ekartlogistics.in](https://www.ekartlogistics.in/track-order).

## ID de seguimento da Ekart: como é

O Peek reconhece os IDs de comércio eletrónico da Ekart como três letras, seguidas de `C`, `P` ou `R` e dez algarismos — 14 caracteres no total (exemplos inventados):

| ID de seguimento (inventado) | O que é |
| --- | --- |
| `FMPP4829105736` | Um dos 17 grupos de três letras ligados à Ekart (listados abaixo), seguido de `C`, `P` ou `R` e dez algarismos: o Peek identifica a Ekart apenas pela forma. |
| `ABCP4829105736` | Outras três letras iniciais com o mesmo formato: pode pertencer à Ekart, pelo que o Peek pergunta primeiro ao localizador da Ekart antes de a confirmar (ver abaixo). |

Os 17 prefixos de três letras associados diretamente à Ekart são `AMI`, `BAL`, `BGS`, `BSI`, `DEL`, `FLO`, `FMP`, `GLA`, `LUA`, `MYE`, `MYN`, `MYS`, `SDL`, `SHO`, `SPO`, `SRT` e `TEL`. Espaços e minúsculas não contam no Peek. Há duas exceções: um código iniciado por `PB` neste formato é lido como da transportadora britânica Parcelforce, e um iniciado por `HRBC` (`HRBC4829105736`) encaixa num formato [Cainiao](carrier:cainiao) / AliExpress em vez de ser sugerido como Ekart — se veio de uma loja indiana para a Ekart, toca em «Alterar» e escolhe a Ekart.

Usa sempre o ID de seguimento e não o número da encomenda da loja ([como encontrar o número de seguimento](guide:find-tracking-number)); para carga B2B/FTL/PTL com número LR, `ekartlogistics.in/track-order` disponibiliza uma ligação separada.

## Estados de seguimento da Ekart explicados

O localizador da Ekart apresenta cada leitura com data, hora e cidade — umas vezes em inglês corrente (`Shipment Created`), outras como código técnico seguido de traço e do nome do centro (`InscannedAtDH` mais o centro). Pela ordem habitual:

| A Ekart diz | O que significa para ti | O Peek mostra |
| --- | --- | --- |
| `Pickup Requested`, `Shipment Created` | O vendedor reservou o envio na Ekart; a Ekart ainda não tem a encomenda. | «Etiqueta criada» |
| `OutForPickupEvent` | Uma equipa da Ekart vai a caminho do vendedor para recolher o volume. | «Etiqueta criada» |
| `NotPickedEvent` | A recolha falhou: a encomenda continua com o vendedor. | «Etiqueta criada» |
| `Pickup From Seller`, `PickupReceived` | A Ekart recolheu a encomenda junto do vendedor. | «Aceite pela transportadora» |
| `ShipmentGrouped`, `ShipmentInTransit`, `dispatched_to_tc`, `dispatched`, `Dispatched to` + centro | Em trânsito entre centros da rede da Ekart. | «Em trânsito» |
| `Expected` | O centro seguinte foi avisado de que a encomenda está a chegar. | «Em trânsito» |
| `Received`, `Received at` + centro | Chegou a um centro operacional da Ekart. | «Em trânsito» |
| `InscannedAtFacility` | Registo de entrada numa instalação da Ekart. | «Em trânsito» |
| `InscannedAtDH`, `PrimaryDHInscan` | Chegou ao centro de distribuição (*Delivery Hub*, DH) da tua zona: segue-se a saída para entrega. | «Em trânsito» |
| `Out For Delivery` | Em distribuição hoje. | «Em distribuição» |
| `Delivered` | Entregue a ti (ou devolvida ao vendedor se vier depois de uma linha RTO, ver abaixo). | «Entregue» |
| `undelivered_attempted`, `ShipmentUndeliveredAttempted`, `undelivered_update` | Tentativa de entrega sem sucesso (`undelivered_update` pode surgir no mesmo instante junto ao centro). | «Tentativa de entrega» |
| `undelivered_unattempted` | O giro do dia terminou sem tentativa de entrega. | «Tentativa de entrega» |
| `Unsuccessful Unable to attempt delivery due to operational issues`, `Unsuccessful Undelivered. (Rescheduled to: …)` | Entrega não conseguida descrita por extenso; a data após `Rescheduled to:` indica a próxima tentativa prevista (um `Unsuccessful` de recolha não é lido assim). | «Tentativa de entrega» |
| `PickupCancel` | A recolha no vendedor foi cancelada antes de a encomenda entrar na rede da Ekart. | «Problema reportado» |
| `ShipmentRtoConfirmed` | Devolução à origem (*Return to Origin*, RTO) confirmada: a encomenda regressa ao vendedor. | «Problema reportado» |
| `Marked RTO: ORDER_REJECT` | Após voltas sem entrega, a encomenda foi marcada para regressar ao vendedor com o motivo após os dois pontos. | «Problema reportado» |
| `RtoExpected`, `RtoReceived`, `ReturnInscanned` | Em viagem de regresso ao vendedor. | «Em trânsito» |

**O que é RTO.** O Peek lê `ShipmentRtoConfirmed`, qualquer linha `Marked RTO` ou qualquer código iniciado por `Rto` ou `Return` como o início da devolução ao vendedor: essa linha e todas as seguintes fazem parte do regresso. A partir daí, `InscannedAtDH` refere-se ao centro do vendedor e um `Delivered` posterior significa que o volume chegou ao comerciante: o Peek mostra «Devolvida ao remetente» em vez de «Entregue» ([o que significam os estados de seguimento](guide:tracking-statuses)).

## Como a Ekart entrega na Índia

O código do Peek só conhece a Ekart na Índia e o Peek não transfere encomendas da Ekart para nenhuma outra transportadora. Em `ekartlogistics.in`, a página B2C Express indica cobertura em mais de 15 000 códigos PIN de entrega (as FAQ referem 5 000 códigos PIN de recolha e 15 000 de entrega, enquanto *About Us* menciona 14 000 códigos PIN, mais de 80 centros de triagem e 3 500 centros de última milha), com opções no próprio dia ou no dia seguinte a curta distância e em 48 a 96 horas a distâncias maiores consoante o código PIN.

Segundo as FAQ oficiais da Ekart, são feitas **cinco novas tentativas** (*reattempts*) na primeira milha (recolha no vendedor) e **três novas tentativas** na última milha (entrega ao destinatário), podendo a entrega ser reagendada a pedido do cliente desde que a remessa ainda não esteja marcada como `RTO` ([falha na entrega](guide:missed-delivery)).

## Seguimento Ekart parado, em RTO ou não entregue

**Parada em `Shipment Created`, `Pickup Requested` ou `NotPickedEvent`.** O vendedor ainda não entregou o pacote à Ekart (ou a recolha falhou); `PickupCancel` indica cancelamento da recolha. Pergunta à loja quando será expedida.

**Vários dias sem nova leitura.** Quando uma encomenda registada, aceite ou em trânsito passa quatro dias sem nova leitura, o Peek assinala «Sem atualizações desde …» com o dia dessa última leitura. Fala com a loja ([porque é que o seguimento não atualiza](guide:tracking-not-updating)).

**Tentativa falhada ou RTO.** Após `undelivered_attempted`, `undelivered_unattempted` ou `Unsuccessful`, verifica a data após `Rescheduled to:` e confirma a morada e o telefone com a loja. Se surgir `ShipmentRtoConfirmed` ou `Marked RTO: ORDER_REJECT`, o pacote regressa ao vendedor: pede reembolso ou novo envio ([devolvida ao remetente](guide:returned-to-sender)).

**Dada como entregue mas ausente, perdida ou danificada.** Confirma com familiares, vizinhos e portaria ou segurança do edifício, e contacta depois a loja ou marketplace que contratou a Ekart ([entregue mas não recebido](guide:delivered-not-received), [encomenda perdida](guide:lost-parcel), [encomenda danificada](guide:damaged-parcel)).

## Apoio ao cliente e contactos da Ekart

Nas FAQ de `ekartlogistics.in`, a Ekart instrui os vendedores a abrir um pedido no portal de expedidores (resposta em 24 horas) e em *Corporate Information* indica a morada da *Instakart Services Private Limited* em Bengaluru (`Embassy Tech Village, Outer Ring Road, Devarabeesanahalli Village, Bengaluru KA 560103`), sem divulgar telefone nem e-mail de apoio para destinatários. Para questões, reenvio ou reembolso da tua compra, dirige-te primeiro à loja ou marketplace onde compraste.

**Cuidado com SMS falsos.** Os domínios oficiais da Ekart são `www.ekartlogistics.in` (`ekartlogistics.in`) e `ekartlogistics.com`. Antes de pagares qualquer valor pedido por mensagem, lê [mensagens falsas de entregas](guide:delivery-scam-texts).

## Seguir uma encomenda da Ekart com o Peek

Cola o ID no [Peek](/): com um dos 17 prefixos acima seguido de `C`, `P` ou `R` e dez algarismos, o Peek atribui logo a Ekart («Transportadora detetada»). Com outras letras à frente (`ABCP4829105736`), mostra «A verificar serviços de seguimento…» e pergunta ao localizador da Ekart: mal exista uma leitura, diz «Ekart tem esta encomenda.» (se ainda não houver, toca em «Alterar» e escolhe a Ekart em «Corresponde a este número»). Uma ligação de `ekartlogistics.com` com `shipmenttrack/` também a regista na Ekart; já uma ligação de `shiprocket.co` é seguida na Shiprocket. Escolher a Ekart manualmente para algo diferente de quatro letras e dez algarismos indica que «não emite números como este».

Registada na Ekart, uma encomenda sem leituras mostra «À espera de Ekart» (ou «Ainda sem histórico de seguimento» sem transportadora). Dois dias depois de a Ekart responder pela primeira vez que não conhece o número, o Peek pede-te que o verifiques, escolhas outra transportadora ou elimines a encomenda. O Peek lê diretamente o localizador da Ekart sem conta nem telefone: cada linha com códigos e cidade, hora exata convertida para o fuso do teu dispositivo («Horas no teu fuso horário: …») sem repetições e data prevista de entrega enquanto a encomenda segue para ti (ocultada após entrega, cancelamento ou RTO).

Enquanto o localizador da Ekart responder, com a encomenda aberta ou no dia da entrega com notificações ativas, o Peek verifica até a cada 10 minutos, e até a cada 2 minutos a partir das 6:00 (hora da Índia) no dia em que a Ekart regista `Out For Delivery` (via serviços de seguimento, até a cada 15 minutos nesse dia com a encomenda aberta ou alertas ativos); nos restantes casos, e após 12 horas em «Etiqueta criada» / «Aceite pela transportadora» ou dois dias sem novidades, de hora a hora. Alertas: «Todos os registos», «Passos importantes» ou «Só a entrega». O Peek não pode contactar a Ekart nem a loja por ti.

## Perguntas frequentes sobre o seguimento da Ekart

### Como sigo a minha encomenda da Ekart?

Cola o ID de 14 caracteres da mensagem de envio na caixa no topo do Peek ou introdu-lo em *Ecommerce Parcel Tracking* em `ekartlogistics.in/track-order`.

### Como é um ID de seguimento da Ekart?

Três letras, depois `C`, `P` ou `R` e dez algarismos, como `FMPP4829105736` (inventado).

### O que significa `RTO` no seguimento da Ekart?

*Return to Origin*: a encomenda está a voltar para o vendedor. `ShipmentRtoConfirmed` ou `Marked RTO: ORDER_REJECT` inicia o retorno e um `Delivered` seguinte confirma a chegada ao vendedor («Devolvida ao remetente» no Peek).

### O que significa `InscannedAtDH` na Ekart?

A encomenda deu entrada num centro de distribuição (*Delivery Hub*): à ida, o centro que entrega na tua área; após RTO, o centro do vendedor.

### Quantas tentativas de entrega faz a Ekart?

As FAQ oficiais da Ekart indicam três novas tentativas (*reattempts*) na última milha ao destinatário (e cinco na recolha ao vendedor). Sobre a tua encomenda, fala primeiro com a loja.

:::sources
- [Ekart Logistics: Página inicial](https://www.ekartlogistics.in/) – transportadora e-commerce na Índia e mais de 15 000 códigos PIN cobertos
- [Ekart Logistics: Track Order](https://www.ekartlogistics.in/track-order) – seguimento de encomendas e-commerce e ligação separada B2B/LR
- [Ekart Logistics: FAQ](https://www.ekartlogistics.in/faq) – três novas tentativas de entrega, cinco de recolha, reagendamento antes de RTO, 5 000 / 15 000 códigos PIN e suporte por ticket para vendedores
- [Ekart Logistics: About Us](https://www.ekartlogistics.in/about-us) – início em 2009, 3 500 centros de distribuição, mais de 80 centros de triagem e 14 000 códigos PIN
- [Ekart Logistics: B2C Express](https://www.ekartlogistics.in/b2c-express) – entregas no próprio dia / dia seguinte e em 48–96 horas na Índia
- [Ekart Logistics: Corporate Information](https://www.ekartlogistics.in/corporate-info) – Instakart Services Private Limited e sede em Bengaluru
- [Ekart Logistics: Terms & Conditions](https://www.ekartlogistics.in/terms-and-conditions) – titularidade do domínio ekartlogistics.com pela Instakart Services Private Limited
- [Universal Parcel Scraper: Ekart](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/ekart) – 17 prefixos, ligações shipmenttrack, 31 códigos e estados, lógica RTO, horas exatas e data prevista de entrega
- [Universal Parcel Scraper: Shiprocket](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/shiprocket) – ligações de seguimento shiprocket.co
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – exceções PB (Parcelforce) e HRBC (Cainiao / AliExpress) e ausência de transição externa
:::
