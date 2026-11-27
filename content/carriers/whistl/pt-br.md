---
title: Rastreamento Whistl: quem entrega no Reino Unido e suporte
description: Rastreie encomendas Whistl (Parcelhub) no Reino Unido: onde achar a referência, status em inglês, transportadora final após repasse e contatos.
slug: whistl-tracking
published: 2026-11-26
updated: 2026-11-26
---

A Whistl, anteriormente TNT Post UK, é uma empresa britânica de entregas que despacha encomendas para lojas e as repassa a transportadoras como [Royal Mail](carrier:royal-mail), Evri ou DPD para a última milha. Rastreie no portal Parcelhub da Whistl usando a referência do e-mail de envio ou link de rastreamento da loja, ou cole o link na caixa acima. Depois que o pacote é repassado, o código próprio e o rastreador da transportadora final costumam trazer mais detalhes.

## Código de rastreamento da Whistl: onde encontrar

A Whistl não publica um formato único de código. Seus guias informam que o rastreador busca "por UPI (geralmente o código de barras do volume) ou Consignment Number": o UPI identifica um volume individual e o Consignment reúne tudo o que foi enviado junto para você. As referências variam conforme a loja e a transportadora, desde uma letra seguida de vários dígitos até o código de barras da transportadora final.

Você encontra a referência no e-mail de despacho da loja, muitas vezes já como link para o rastreador da Whistl. Em envios totalmente rastreados, a transportadora responsável pela entrega também envia e-mail e SMS no dia da entrega com sua própria marca, código e rastreamento.

O rastreador pede "Tracking Reference" e um código postal opcional, mostrando depois o "Tracking Number:" de cada volume, suas leituras e os campos "Carrier:" e "Service:" contratados.

A referência pode seguir o formato de outra transportadora, como o código próprio da transportadora final:

| Formato do código (fictício) | A qual transportadora se parece |
| --- | --- |
| `RR482917367GB` | Royal Mail (duas letras, nove dígitos e `GB`) |
| `H00KLM0048291708` | Código de barras de 16 caracteres da Evri, veja [rastreamento Evri](carrier:evri) |
| `48291736482917` | 14 dígitos: DPD UK entre várias outras, veja [DPD UK](carrier:dpd-uk) |
| `ZDBKLM482917` | XDP, veja [rastreamento XDP](carrier:xdp) |

Saiba mais sobre como identificar códigos em [formatos de código de rastreamento](guide:tracking-number-formats).

## Status de rastreamento da Whistl explicados

O rastreador da Whistl, somente em inglês, converte as leituras de todas as transportadoras nos mesmos "eventos normalizados" (*normalised events*); pacotes totalmente rastreados mostram também comentários da transportadora. Na ordem em que uma encomenda passa por eles:

| A Whistl informa | O que significa para você |
| --- | --- |
| `Shipment Created` | A loja registrou o envio. Nada se movimentou ainda. |
| `Item receipted and processed by Whistl` | A Whistl recebeu e processou o volume em um de seus depósitos. |
| `Handed over to Delivery Carrier` | A transportadora indicada na página assumiu a entrega. |
| `Handed over to Royal Mail` | A Royal Mail assumiu a última milha. |
| `Arrived At Depot` | Chegou a um depósito no trajeto. |
| `Shipment is Out for Delivery` | Saiu para entrega hoje. |
| `DELIVERED - Left as authorised` | Deixada no local autorizado por você, sem assinatura. |
| `Left with Neighbour` | Entregue a um vizinho. |
| `Delivered col. point` | Em um ponto de retirada, aguardando você. |
| `No-one to receive` | Ninguém estava no local: procure um aviso impresso ou mensagem da transportadora. |
| `Held by customs` | Retida pela [alfândega](guide:customs). |
| `Return - Delivered` | Retornou e foi entregue de volta ao remetente. |

Três pontos que costumam confundir:

- **`Shipment Created` isolado** indica apenas reserva. Se nada mudar por dias, a loja pode ainda não ter despachado.
- **`Handed over to …`** costuma ser o último evento mostrado pela Whistl. Para envios com rastreamento parcial (*part-tracked*), a Whistl afirma não ser possível rastrear após o repasse: acompanhe pelo código da transportadora final.
- **Problemas** como `Delayed`, `Bad Address`, `Refused`, `Lost` ou `Item damaged` indicam que algo deu errado; `Shipment Deleted` significa cancelamento pelo remetente. Linhas como `Item Weight Changed` ou `Label reprinted` são apenas anotações e não movimentam o pacote.

## Entrega Whistl e entregas não realizadas

Quem leva o pacote até você é a transportadora final, não a Whistl, e valem as regras dela. Após uma entrega não realizada, a transportadora deixa um cartão ou aviso eletrônico informando quem compareceu e quando tentará novamente.

O guia de transportadoras do Parcelhub da Whistl (agosto de 2025) detalha cada parceira:

| Transportadora | Tentativas e o que acontece depois |
| --- | --- |
| Royal Mail | 1 tentativa e aviso "something for you"; retire em um centro de entrega ou agência postal, ou retorna após 18 dias. Desde maio de 2023, se o pacote não couber na caixa de correio ou pedir assinatura e você não estiver, tenta deixar em local seguro ou com vizinho. |
| Evri | Até três tentativas; configure preferências no app Evri; janela de 2 horas por e-mail ou SMS. |
| DPD | Aviso impresso mais SMS ou e-mail para escolher vizinho, local seguro, nova data ou ponto Pickup; janela de uma hora. |
| Yodel | Até três tentativas; endereços em ilhas ou áreas remotas podem ter apenas uma tentativa antes da retirada em agência postal. |
| [Amazon Shipping](carrier:amazon-shipping) | Até três tentativas em 3 dias consecutivos. |
| DHL Parcel UK | Até 2 tentativas. |
| [Parcelforce](carrier:parcelforce) | 1 tentativa, com opções de local seguro e redirecionamento. |
| UPS | 1 tentativa, seguida de notificação digital com instruções de reentrega. |

Envios com rastreamento parcial para o exterior seguem por parceiros internacionais, valendo as regras do correio local. A Whistl não possui pontos de retirada próprios: `Delivered col. point` refere-se à loja parceira ou armário da transportadora final. Encomendas recusadas retornam ao remetente e não podem ser reentregues.

Marcada como entregue, mas não apareceu? Os guias da Whistl recomendam conferir primeiro o cartão de aviso, locais seguros e vizinhos. Em seguida, veja [entregue mas não recebido](guide:delivered-not-received).

## Encomenda Whistl não entregue, atrasada ou perdida

A página de contato da Whistl orienta o público a "entrar em contato com o varejista de onde comprou". A loja detém o contrato, abre uma solicitação "Where Is My Item" junto à Whistl e faz eventuais pedidos de indenização.

O que dizem os guias da Whistl:

- Encomendas no Reino Unido que não podem ser entregues retornam ao remetente em até **5 dias úteis** após ingressarem na rede da Whistl.
- Em envios com rastreamento parcial, não há indenização por atraso e a Whistl não consegue intervir após o repasse se o item não for entregue.
- Os prazos de pedido de indenização para remetentes variam conforme a transportadora — por exemplo, até 28 dias da postagem na Evri, ou na Royal Mail espera mínima de 10 dias corridos e prazo máximo de 80 dias. Avise a loja quanto antes.

Se o rastreamento estacionar, comece por [por que o rastreamento para de atualizar](guide:tracking-not-updating).

## Telefone e atendimento ao cliente da Whistl

A página de contato da Whistl informa:

- **Sobre sua encomenda:** fale com a loja onde fez a compra.
- **Rastreamento:** botão "Whistl Tracking Portal" na página de contato.
- **Assuntos gerais:** `01628 702 965`, das 8h30 às 17h30 (horário do Reino Unido).
- **E-mail de atendimento:** `packagescustomerservices@whistl.co.uk`, que "não pode atender o público sobre consultas de rastreamento de encomendas".
- **Sede:** Network House, Third Avenue, Marlow, Buckinghamshire, SL7 1EY.

Procure a loja e, após o repasse, a transportadora ou correio local indicado no rastreador.

## Rastreando encomendas Whistl com o Peek

Para uma referência formada por `L` e 17 dígitos, como `L48291736482917364`, o Peek consulta a Whistl diretamente e exibe "Whistl está com esta encomenda." assim que ela confirma. Outras referências Whistl não têm formato próprio, portanto cole um link da Whistl (`web.parcelhub.net` ou `despatch.whistl.co.uk` com `/Tracking/reference/…`, ou `trackmyitem.whistl.co.uk` com `/tracking/…`), ou adicione a referência e escolha "Whistl" em "Trocar". Não é necessário informar código postal. Se a referência tiver o formato de outra transportadora (como um código `…GB` da Royal Mail), o rastreamento segue aquela transportadora, não a Whistl.

O [Peek](/) consulta o rastreador Parcelhub da Whistl. Como os horários não incluem fuso horário, o Peek aplica o horário do local citado pela Whistl ou o horário do Reino Unido por padrão, exibindo o histórico da própria Whistl. O Peek pode mostrar a transportadora responsável pela entrega com o código dela e notificar mudanças de status. Não há dia previsto de entrega, peso nem ponto de retirada. Os status mantêm o texto em inglês da Whistl, exceto `Delivered`, `In transit` e `Returned to sender` simples, que aparecem nas palavras do Peek.

Quando a Whistl indica Royal Mail, Parcelforce Worldwide, DPD UK, DHL Parcel UK, Amazon Shipping, [InPost](carrier:inpost) (incluindo Yodel, rastreada pelo Peek como InPost), [UPS](carrier:ups), [FedEx](carrier:fedex) ou Evri, o Peek consegue continuar acompanhando por lá. Um pacote indicado apenas como "DPD" não é seguido: adicione o código da própria transportadora. Referências que agrupam múltiplos volumes não são rastreadas: adicione o número individual de cada pacote.

## Perguntas sobre o rastreamento da Whistl

### A Whistl faz parte da Royal Mail?

Não. A Whistl UK Ltd é uma empresa independente, antiga TNT Post UK. Ela repassa muitos envios à Royal Mail para a entrega final, motivo pelo qual aparece `Handed over to Royal Mail`.

### Quem entrega as encomendas da Whistl?

Em geral, outra transportadora: DPD, Evri, Yodel, Royal Mail, Amazon Shipping, DHL Parcel UK, Parcelforce ou UPS no Reino Unido, e parceiros locais no exterior.

### O rastreamento da Whistl é confiável?

Sim. O rastreamento próprio da Whistl funciona em web.parcelhub.net, despatch.whistl.co.uk e trackmyitem.whistl.co.uk, e a Parcelhub integra o Whistl Group.

### Por que meu código de rastreamento Whistl não funciona?

O rastreador responde "Unable to find shipment" quando não reconhece a referência. Confira o código com a loja ou acompanhe pelo número próprio da transportadora final informado por SMS ou e-mail.

:::sources
- [Whistl: Contact Us](https://www.whistl.co.uk/contact) – orientação ao público para falar com o varejista, telefone e horários, e-mail de atendimento, portal de rastreamento e sede
- [Whistl: página inicial](https://www.whistl.co.uk/) – Whistl UK Ltd, serviços empresariais e Parcelhub como parte do Whistl Group
- [Whistl: Domestic Fully Tracked Customer Guide (October 2025)](https://www.whistl.co.uk/1817/6062/1711/Whistl_Domestic_Fully_Tracked_Customer_Guide_-_V2.2_October_2025.pdf) – UPI e consignment, eventos normalizados, SMS e e-mails da transportadora, aviso de ausência, recusa, perda, 5 dias úteis e consultas/reclamações pelo remetente
- [Whistl: Part Tracked Services Customer Guide v1.7](https://www.whistl.co.uk/download_file/force/3171/250) – repasse à Royal Mail e parceiros, 18 dias, sem indenização por atraso, links de rastreamento e e-mails de marca
- [Parcelhub: Domestic Carrier Guide (August 2025)](https://www.whistlparcels.co.uk/hubfs/Parcels/Documents/Parcelhub_Carrier_Guide_Domestic_-_V1.1_Aug_2025.pdf) – transportadoras utilizadas, tentativas e opções por parceira, prazos de reclamação para remetentes
- [Parcelhub: Part Tracked Services Customer Guide V1.0](https://www.whistlparcels.co.uk/hubfs/Parcels/Documents/Parcelhub_Part_Tracked_Customer_Guide_-_V1_September_2024.pdf) – portais de rastreamento, busca por UPI ou consignment e sem suporte após repasse
- [Parcelhub: Tracking](https://web.parcelhub.net/Tracking) – campos do formulário, layout de resultado e exibição apenas em inglês
- [Whistl Despatch: Tracking](https://despatch.whistl.co.uk/tracking) – mesmo rastreador sob a marca Whistl
- [Wikipedia: Whistl](https://en.wikipedia.org/wiki/Whistl) – mudança de marca da TNT Post UK e última milha com a Royal Mail
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – o que o Peek lê, status registrados e detecção de códigos
:::
