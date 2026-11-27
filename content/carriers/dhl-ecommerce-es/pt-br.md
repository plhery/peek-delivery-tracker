---
title: Rastreamento DHL eCommerce Ibéria: status e ServicePoints
description: Rastreie encomendas DHL eCommerce na Espanha ou Portugal: significado dos status, entregas não realizadas, ServicePoints, CTT Express e danos ou extravios.
slug: dhl-ecommerce-iberia-tracking
published: 2026-11-26
updated: 2026-11-26
---

A DHL eCommerce Ibéria (DHL eCommerce Iberia, anteriormente DHL Parcel Iberia — nome que ainda aparece em alguns locais) é a rede de encomendas da DHL na Espanha e em Portugal. Cole o código na caixa acima para rastrear; na página da encomenda, "Abrir o site de DHL eCommerce Iberia" leva ao mesmo envio no próprio rastreador da DHL. O código vem da loja remetente.

## Código de rastreamento DHL eCommerce: formato e onde encontrar

Sem código? Peça à loja ou veja [como encontrar seu código de rastreamento](guide:find-tracking-number).

Os formatos que o catálogo do Peek reconhece (exemplos inventados):

| Formato | Exemplo | A quem pode pertencer |
| --- | --- | --- |
| 10 dígitos | `4829173649` | DHL eCommerce Iberia, mas também [DHL Express](carrier:dhl-express), Mondial Relay, Relais Colis e GLS Spain |
| Duas letras, 7 dígitos e `0` | `KL48291730` | DHL eCommerce Iberia |
| `JJD000061`, `JJD000268`, `JJD000269` ou `JJD000368` + 14 dígitos | `JJD00006148291736482917` | DHL eCommerce Iberia |

Se o e-mail da loja indicar [Mondial Relay](carrier:mondial-relay) ou [GLS Spain](carrier:gls-es) para um código de 10 dígitos, rastreie por lá. Duas letras, nove dígitos e `ES` pertencem aos [Correos](carrier:correos-spain).

## Status de rastreamento da DHL eCommerce explicados

O Peek solicita à DHL os textos em inglês, embora algumas movimentações ainda venham em espanhol, como `Llegada a`, `Tránsito en`, `Salida de` e `Entregado`. Os principais, na ordem em que a encomenda os recebe:

| A DHL diz | O que significa para você |
| --- | --- |
| `Shipment data received` | O remetente registrou a encomenda. A DHL ainda não está com o pacote. |
| `Picked up` | A DHL coletou a encomenda do remetente; a cidade vem em seguida. |
| `Shipment received at DHL Depot` | O volume chegou a um depósito da DHL. |
| `Arrived at` | Circulando entre depósitos; a cidade vem em seguida (`In transit in` e `Departed from` funcionam igual). |
| `Out for delivery` | Na van de entrega hoje. |
| `The delivery could not be made due to consignee absent` | Ninguém atendeu. Veja a próxima seção. |
| `Delivered to DHL ServicePoint, ready for collection by the recipient` | Disponível para retirada em um ServicePoint. |
| `Parcel picked up by recipient` | Retirado no ServicePoint. |
| `Delivered` | Entregue no endereço. |
| `Shipment being returned to sender` | Em devolução ao remetente. |

Alguns textos merecem atenção:

- `Delivered, POD pending`: marcada como entregue sem comprovante confirmado ainda; pode aparecer antes da hora e ser seguida por nova rota ou entrega não realizada.
- `Shipment with incidence If you are the sender, please contact DHL Parcel, if you are the recipient please contact the sender`: ocorreu um problema com o envio; conforme a própria mensagem, o destinatário deve falar com a loja.
- `The expected delivery date might be postponed one working day` e `Not delivered today: delivery expected for the next working day` indicam atrasos curtos, não extravio.

## Entrega não realizada e ServicePoints

**Ninguém em casa.** Entregas não realizadas aparecem como `The delivery could not be made due to consignee absent`, ou `Receiver closed: delivery not possible` quando o local estava fechado. Registros posteriores como `New delivery date requested` ou `Delivery agreed with recipient` indicam novo agendamento solicitado ou combinado. Se aparecer `Incorrect address details: delivery not possible`, os dados do endereço impediram a entrega: o Peek mostra "Precisa de atenção", e você ou o remetente precisam corrigir antes que o pacote siga.

**ServicePoints.** `Parcel will be delivered to a DHL ServicePoint` e `Parcel is on it's way to our DHL ServicePoint` significam que a encomenda está indo para um ponto de retirada DHL ServicePoint. Assim que ficar pronta, o Peek mostra um cartão "Ponto de retirada" com nome, endereço, rota e botão para copiar o endereço; horários de funcionamento e prazo de guarda devem ser conferidos diretamente com a DHL.

## Encomenda atrasada, extraviada ou danificada

**Sem atualização.** Veja [por que o rastreamento para de atualizar](guide:tracking-not-updating).

**Extraviada ou danificada.** Comece pela loja: o próprio status `Shipment with incidence` orienta o destinatário a procurar o remetente. Na UE, o Your Europe esclarece que a loja responde por danos até a entrega e, sem data combinada, deve entregar em até 30 dias; se não cumprir, você pode dar prazo adicional razoável e depois cancelar com reembolso. Veja [encomenda extraviada](guide:lost-parcel) e [encomenda danificada](guide:damaged-parcel).

**Alfândega.** Para encomendas vindas de fora da UE, consulte o [guia de alfândega](guide:customs).

**CTT Express.** A DHL eCommerce e os CTT assinaram uma joint venture em 12 de maio de 2026: em Portugal, a operação da DHL eCommerce passa para a CTT Expresso, e na Espanha a CTT Express assume entregas a consumidores enquanto a DHL mantém entregas empresariais. Alguns volumes levam código da CTT Express: acompanhe em [CTT Express](carrier:ctt-express).

> Recebeu um SMS ou e-mail suspeito sobre uma encomenda DHL? Leia [como reconhecer golpes de entrega](guide:delivery-scam-texts).

## Como contatar a DHL eCommerce

Procure os canais da DHL eCommerce no site da DHL do país de entrega (Espanha ou Portugal). Em caso de extravio ou dano, fale primeiro com a loja.

## Acompanhe encomendas DHL eCommerce Ibéria com o Peek

Para duas letras, 7 dígitos e `0`, o [Peek](/) consulta DHL eCommerce Iberia e [TNT Itália](carrier:tnt-it), cujos códigos têm o mesmo padrão. Para 10 dígitos, consulta DHL eCommerce Iberia, Packeta e BOX NOW — além de DHL Freight (de Portugal ou sem país identificado, apenas se as demais não encontrarem) e DHL Express primeiro quando o dígito verificador bate (junto com ACS Courier e Geniki Taxydromiki, que passam no mesmo teste); se a DHL Express também reconhecer o código, ela prevalece. Se marcas diferentes reconhecerem ou nenhuma reconhecer, escolha em "Trocar transportadora". Códigos `JJD` no formato da tabela acima são atribuídos à DHL eCommerce Iberia na hora. Demais códigos da DHL (12 ou 22 dígitos, outros `JJD`, números postais) só funcionam depois de escolher "DHL eCommerce Iberia" ali, sem exigir código postal.

Links de envio da página da DHL eCommerce Iberia em espanhol, português ou inglês (`/LiveTracking/ModificarEnvio/`, `ModificarMeuEnvio` ou `ModifyMyShipment`) também podem ser colados e apontam direto para DHL eCommerce Iberia independentemente do formato (qualquer código postal no link é descartado, pois não é necessário).

As movimentações da DHL não trazem fuso horário: o Peek interpreta pelo relógio do local e lista o histórico próprio da DHL. Quando a resposta da DHL traz um código da CTT Express, o Peek também consulta a [CTT Express](carrier:ctt-express) e passa a acompanhá-la assim que surgir movimentação tão recente quanto a última da DHL. Os status preservam o inglês da DHL, exceto `Delivered` e `Out for delivery`, que aparecem com o texto do próprio Peek. O Peek pode avisar quando o status muda. Não há data prevista nem horário exato de entrega exibidos para a DHL eCommerce Iberia.

## Perguntas sobre o rastreamento da DHL eCommerce

### A DHL eCommerce é a mesma empresa que a DHL Express?

Não. Encomendas da DHL Express têm rastreamento próprio (MyDHL+), e o Peek trata as duas como transportadoras distintas. Códigos de 10 dígitos podem pertencer a qualquer uma delas: o Peek consulta primeiro a DHL Express quando o dígito verificador confere e, se ela reconhecer o código, prevalece.

### Por que minha encomenda DHL eCommerce não mostra rastreamento?

Confirme o código e a transportadora com a loja: códigos de 10 dígitos são compartilhados por várias transportadoras (veja a tabela acima). Envios antigos também podem sair do sistema da DHL, sem prazo de retenção divulgado.

### A CTT Express entrega encomendas da DHL eCommerce?

Parte delas. Pela joint venture assinada em maio de 2026 entre DHL eCommerce e CTT, a CTT Express assume entregas a consumidores na Espanha e a operação em Portugal migra para a CTT Expresso. Se surgir um código da CTT Express no rastreamento, acompanhe em [CTT Express](carrier:ctt-express).

:::sources
- [CTT: CTT e DHL Ecommerce selam parceria estratégica](https://www.ctt.pt/grupo-ctt/media/noticias/ctt-e-dhl-ecommerce-selam-parceria-estrategica-para-criar-rede-de-distribuicao-de-encomendas-mais-completa-da-peninsula-iberica) – joint venture assinada em 12 de maio de 2026, DHL eCommerce Portugal para a CTT Expresso, CTT Express para consumidores na Espanha, DHL para entregas a empresas
- [Your Europe: Shipping and delivery](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_en.htm) – loja responsável por danos até a entrega, 30 dias, prazo adicional, reembolso
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – o que o Peek lê, textos de status e suas etapas, loja ServicePoint, links de envio, antigo nome DHL Parcel Iberia, códigos da CTT Express
:::
