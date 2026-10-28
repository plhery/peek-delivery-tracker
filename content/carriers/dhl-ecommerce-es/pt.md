---
title: DHL eCommerce Iberia rastreio: estados e ServicePoints
description: Rastreio DHL eCommerce (antiga DHL Parcel) em Portugal: o que quer dizer cada estado, entregas falhadas, o ServicePoint, a CTT Express e encomendas perdidas.
slug: dhl-ecommerce-iberia-rastreio
published: 2026-10-11
updated: 2026-10-26
---

A DHL eCommerce Iberia é a rede de encomendas da DHL em Portugal e Espanha, a antiga DHL Parcel Iberia; o nome DHL Parcel ainda aparece em vários sítios. Para seguires a encomenda, cola o número na caixa acima; na página da encomenda, «Abrir o site de DHL eCommerce Iberia» leva-te ao mesmo envio na página de seguimento da DHL. O número vem da loja que te enviou a encomenda.

## Número de rastreio DHL eCommerce: onde está e como é

Não tens número? Pede-o à loja ou lê [onde encontrar o número de seguimento](guide:find-tracking-number).

As formas que o catálogo do Peek conhece (exemplos inventados):

| Forma | Exemplo | De quem pode ser |
| --- | --- | --- |
| 10 algarismos | `1234567890` | DHL eCommerce Iberia, mas também [DHL Express](carrier:dhl-express), Mondial Relay, Relais Colis, TIPSA e GLS Spain |
| Duas letras, 7 algarismos e `0` | `AB12345670` | DHL eCommerce Iberia |

Se o email da loja indicar a [Mondial Relay](carrier:mondial-relay) para um número de 10 algarismos, segue-o lá. Duas letras, nove algarismos e `ES` é um código dos Correos espanhóis. Um número de 22 algarismos pode ser a etiqueta da [CTT Express](carrier:ctt-express), que entrega algumas encomendas da DHL.

## Estados DHL eCommerce: o que significam

O Peek pede à DHL as leituras em inglês, mas algumas chegam em espanhol, como `Llegada a`, `Tránsito en`, `Salida de` ou `Entregado`. As mais comuns, pela ordem em que costumam aparecer:

| A DHL diz | O que quer dizer para ti |
| --- | --- |
| `Shipment data received` | O remetente registou o envio. A DHL ainda não tem a encomenda. |
| `Picked up` | A DHL recolheu a encomenda no remetente; segue-se a localidade. |
| `Shipment received at DHL Depot` | A encomenda chegou a um centro da DHL. |
| `Arrived at` | Viagem entre centros, com a localidade. `In transit in` e `Departed from` funcionam da mesma forma, tal como as versões em espanhol. |
| `Out for delivery` | Saiu hoje para entrega. |
| `The delivery could not be made due to consignee absent` | Ninguém atendeu. Vê a secção seguinte. |
| `Delivered to DHL ServicePoint, ready for collection by the recipient` | Pronta para levantar num ServicePoint. |
| `Parcel picked up by recipient` | A encomenda foi levantada no ServicePoint. |
| `Delivered` | Entregue na morada. |
| `Shipment being returned to sender` | Vai de volta para a loja. |

Alguns pedem explicação:

- `Delivered, POD pending`: dada como entregue, mas a prova de entrega (POD) ainda não chegou. Pode aparecer cedo e ainda vir depois uma volta de entrega ou uma tentativa falhada.
- `The expected delivery date might be postponed one working day`: um atraso curto, não uma encomenda perdida.
- `Not delivered today: delivery expected for the next working day`: hoje não foi possível entregar; a DHL conta entregar no dia útil seguinte.
- `Shipment with incidence If you are the sender, please contact DHL Parcel, if you are the recipient please contact the sender`: há um problema com o envio, e a própria leitura manda o destinatário contactar o remetente. Fala com a loja.

## Entrega falhada e ServicePoints

**Ninguém em casa.** A tentativa falhada aparece como `The delivery could not be made due to consignee absent`, ou como `Receiver closed: delivery not possible` se o local estava fechado (uma loja ou um escritório, por exemplo). Se depois vires `New delivery date requested` ou `Delivery agreed with recipient`, foi pedida ou combinada uma nova data de entrega.

**ServicePoints.** `Parcel will be delivered to a DHL ServicePoint` e `Parcel is on it's way to our DHL ServicePoint` querem dizer que a encomenda vai a caminho de uma loja DHL ServicePoint. Quando já lá está, o Peek mostra um cartão «Ponto de levantamento» com o nome e a morada da loja, como chegar e um botão para copiar a morada. O Peek não recebe o horário: confirma-o com a DHL, tal como o tempo que a encomenda fica à tua espera.

## Encomenda DHL eCommerce parada, perdida ou danificada

**Sem atualizações.** Vê [encomenda parada](guide:tracking-not-updating).

**Perdida ou danificada.** Começa pela loja: a própria leitura `Shipment with incidence` da DHL manda o destinatário contactar o remetente. Segundo o Your Europe, o vendedor é responsável por qualquer dano desde a expedição até à entrega e deve entregar em 30 dias, salvo outro prazo combinado. Se não chegar, avisa-o e dá-lhe um prazo suplementar razoável; se mesmo assim não entregar, podes rescindir o contrato e ser reembolsado. Mais em [encomenda perdida](guide:lost-parcel) e [encomenda danificada](guide:damaged-parcel).

**Alfândega.** Se a encomenda vier de fora da UE, vê o [guia da alfândega](guide:customs).

**CTT Express.** A DHL eCommerce e os CTT assinaram a 12 de maio de 2026 uma parceria: em Portugal, o negócio da DHL eCommerce passa para a CTT Expresso; em Espanha, a CTT Express fica com as entregas a particulares e a DHL com os envios entre empresas. Algumas encomendas têm um número da CTT Express: segue-as na [CTT Express](carrier:ctt-express).

> Recebeste um SMS ou email suspeito sobre uma encomenda da DHL? Vê primeiro [como saber se é burla](guide:delivery-scam-texts).

## Contactar a DHL eCommerce

Procura os contactos da DHL eCommerce no site da DHL do país onde a encomenda é entregue, Portugal ou Espanha. Se a encomenda se perdeu ou chegou danificada, fala primeiro com a loja.

## Rastrear DHL eCommerce Iberia com o Peek

Para duas letras, 7 algarismos e `0`, o [Peek](/) consulta a DHL eCommerce Iberia e a TNT Itália, cujos números têm a mesma forma. Para 10 algarismos consulta a Relais Colis, a TIPSA e a DHL eCommerce Iberia, e primeiro a DHL Express quando o dígito de controlo dela bate certo; se a DHL Express também conhecer o número, fica ela. Se o número for conhecido por transportadoras de marcas diferentes, ou por nenhuma, escolhes com «Alterar transportadora». Outros números da DHL (12 ou 22 algarismos, códigos `JJD`, `JVGL` ou `3S`, números postais internacionais) só funcionam depois de escolheres aí «DHL eCommerce Iberia». Não é preciso código postal.

Também podes colar ligações de envios da página de seguimento da DHL eCommerce Iberia, em português, espanhol ou inglês (`/LiveTracking/ModificarMeuEnvio/`, `ModificarEnvio` ou `ModifyMyShipment`). Indicam logo a DHL eCommerce Iberia, seja qual for o formato do número. Se a ligação tiver um código postal no fim, o Peek não o usa, porque não precisa dele.

As leituras da DHL não têm fuso horário: o Peek lê-as na hora do local de cada leitura e mostra a lista de leituras da própria DHL. A consulta à DHL é feita em inglês, por isso os estados aparecem como a DHL os escreve em inglês (por exemplo `Delivered to DHL ServicePoint, ready for collection by the recipient`), exceto `Delivered` e `Out for delivery`, que aparecem com as palavras do próprio Peek. Pode enviar-te notificações quando o estado muda. Não mostra data prevista nem hora de entrega para a DHL eCommerce Iberia. Se a DHL indicar um número da CTT Express, segue a encomenda na [CTT Express](carrier:ctt-express) com esse número.

## Perguntas sobre o rastreio DHL eCommerce

### A DHL eCommerce é o mesmo que a DHL Express?

Não. Os envios da DHL Express têm o seu próprio rastreio (a página de seguimento do MyDHL+), e no Peek são duas transportadoras diferentes. Um número de 10 algarismos pode ser de qualquer uma: o Peek consulta primeiro a DHL Express quando o dígito de controlo dela bate certo e, se a DHL Express conhecer o número, fica ela.

### Porque é que o rastreio DHL eCommerce não mostra nada?

Confirma o número e a transportadora com a loja: os números de 10 algarismos são usados por várias transportadoras (vê a tabela acima). Um envio antigo também pode desaparecer do rastreio da DHL; não se sabe durante quanto tempo a DHL guarda o histórico.

### Como faço uma reclamação à DHL Parcel?

Se a encomenda se perdeu ou chegou danificada, reclama primeiro à loja: segundo o Your Europe, o vendedor é responsável pelos danos até à entrega e tem de entregar no prazo combinado ou, se não combinaram nenhum, em 30 dias.

### A CTT Express entrega encomendas da DHL eCommerce?

Algumas, sim. Pela parceria que a DHL eCommerce e os CTT assinaram em maio de 2026, o negócio da DHL eCommerce em Portugal passa para a CTT Expresso e, em Espanha, a CTT Express fica com as entregas a particulares. Se o rastreio te der um número da CTT Express, segue-o na [CTT Express](carrier:ctt-express).

:::sources
- [CTT: CTT e DHL Ecommerce selam parceria estratégica](https://www.ctt.pt/grupo-ctt/media/noticias/ctt-e-dhl-ecommerce-selam-parceria-estrategica-para-criar-rede-de-distribuicao-de-encomendas-mais-completa-da-peninsula-iberica) – parceria assinada a 12 de maio de 2026, negócio da DHL eCommerce em Portugal para a CTT Expresso, particulares com a CTT Express e empresas com a DHL em Espanha
- [Your Europe: Expedição e entrega](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_pt.htm) – vendedor responsável pelos danos até à entrega, 30 dias, prazo suplementar, reembolso
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – o que o Peek lê e mostra, textos dos estados e a etapa de cada um, loja ServicePoint, ligações de envios, antigo nome DHL Parcel Iberia, números da CTT Express
:::
