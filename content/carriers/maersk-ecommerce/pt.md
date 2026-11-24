---
title: Rastreio Maersk eCommerce: códigos 1M, estados e apoio
description: Acompanha uma encomenda Maersk eCommerce (B2C Europe / InterPost): código 1M de 19 carateres, códigos 70000–77200, entregas locais e apoio Maersk.
slug: rastreio-maersk-ecommerce
published: 2026-11-24
updated: 2026-11-24
---

A **Maersk eCommerce** (*Maersk Logistics & Services USA Inc.*, integrando as antigas plataformas de encomendas *B2C Europe* e *InterPost*) é a rede nacional e transfronteiriça de entrega de encomendas B2C da A.P. Moller–Maersk ([maersk.com/supply-chain-logistics/e-commerce-logistics](https://www.maersk.com/supply-chain-logistics/e-commerce-logistics)). Movimentando mais de 80 milhões de encomendas por ano com mais de 75 transportadoras integradas na América do Norte e na Europa, a Maersk eCommerce faz a triagem de encomendas de lojas online nas suas instalações, desalfandega envios B2C em rotas internacionais e entrega a fase final a transportadoras locais e operadores postais como [Amazon Logistics](carrier:amazon-logistics), [OnTrac](carrier:ontrac) e [DPD Áustria](carrier:dpd-at). Podes colar o teu código `1M` de 19 carateres na caixa acima para acompanhar tanto os registos dos centros Maersk como os marcos retransmitidos da transportadora local.

## Números de rastreio Maersk eCommerce (formato `1M`)

A Maersk eCommerce emite números alfanuméricos de 19 carateres começados por `1M`, seguidos por um código de cliente/comerciante de 7 carateres (com pelo menos uma letra) e 10 dígitos:

| Formato (inventado) | O que é |
| --- | --- |
| `1MAB4829C7360514928` | Código de encomenda Maersk eCommerce de 19 carateres (`1M` + código cliente de 7 carateres + 10 dígitos). |
| `1MKX7391E4829173605` | Outro número de encomenda `1M` de 19 carateres usado em rotas da América do Norte ou da Europa. |

Verifica o e-mail de expedição da loja ou a ligação de rastreio (`maersk.com/tracking/parcel/...`) para o número `1M`. O rastreio da Maersk pode indicar a transportadora de última milha e o código atribuído por ela. Consulta os [formatos de números de rastreio](guide:tracking-number-formats).

## O que significam os estados de rastreio da Maersk eCommerce

Os registos próprios da Maersk usam códigos de `70000` a `72004`, e os da transportadora de última milha a partir de `77100`:

| Código e texto Maersk | O que significa para ti | O Peek mostra |
| --- | --- | --- |
| `70000` `Shipment Manifest Received`, `70001` `Maersk Shipping Label Created` | `70000`: o manifesto do vendedor inclui a encomenda antes de a Maersk a receber. `70001`: etiqueta própria da Maersk, gerada quando a encomenda entra na sua rede, muitas vezes minutos antes de `70002`. | «Etiqueta criada» |
| `70002` `Parcel Received by Maersk` | Encomenda física recebida e registada num centro de entrada Maersk eCommerce. | «Aceite pela transportadora» |
| `72001` `Arrived at Maersk Facility`, `72002` `Processing at Maersk Facility`, `72004` `Departed from Maersk Facility` | Triada num hub de consolidação Maersk e expedida para a região ou gateway de destino. | «Em trânsito» |
| `77100` `In-Transit Scan`, `77101` `Enroute to Facility`, `77102` `Arrival at Facility`, `77103` `Processing at Facility`, `77105` `Departed from Facility`, `77106` `Parcel In-transit` | Eventos de trânsito nacional retransmitidos pela transportadora local de última milha (como OnTrac, Amazon Logistics ou DPD). | «Em trânsito» |
| `77203` `Out for Delivery` | Carregada no veículo do motorista local para entrega hoje. | «Em distribuição» |
| `77200` `Parcel Successfully Delivered` | Entregue na morada do destinatário pelo parceiro de última milha. | «Entregue» |

Um registo sem um destes códigos mostra «Problema reportado» quando a Maersk o assinala como exceção, e caso contrário adota a etapa sob a qual surge no rastreio Maersk.

## Cobertura, devoluções e alfândega transfronteiriça

A Maersk E-Commerce garante cobertura total de entrega em 32 países na América do Norte e Europa, com pontos de injeção na origem na China e Sudeste Asiático ([Maersk E-Commerce](https://www.maersk.com/supply-chain-logistics/e-commerce-logistics)):

- **Instalações e parceiros:** na Europa, 8 instalações de triagem e cross-docking, hubs de entrada em Londres, Taulov e Amesterdão e mais de 40 parceiros de última milha e operadores postais com entrega ao domicílio e pontos de levantamento. Na América do Norte, centros de triagem e gateways em JFK, LAX e ORD; Standard demora 2–7 dias nos EUA e 1–10 dias no Canadá, Expedited 2–5 e 1–5 dias.
- **Desalfandegamento internacional:** a Maersk desalfandega encomendas B2C para o Reino Unido, UE e Estados Unidos. Pagar ou não direitos e IVA à chegada depende de o vendedor os ter cobrado no pagamento. Consulta [encomendas retidas na alfândega](guide:customs).
- **Devoluções e-commerce:** devoluções nacionais e internacionais por entrega em ponto ou recolha ao domicílio nos vendedores aderentes, sem papel e isentas de direitos em algumas rotas.

## Encomenda Maersk eCommerce atrasada, sem novidades ou perdida

Como a Maersk eCommerce é um consolidador B2B contratado por marcas de comércio eletrónico, **contacta sempre primeiro a loja online** para alterações de morada, artigos em falta, substituições ou reembolsos.

- **Pausa na viagem principal ou entrega ao parceiro:** o rastreio pára frequentemente alguns dias entre `72004` (`Departed from Maersk Facility`) e o primeiro registo `77100+` da transportadora local enquanto camiões ou voos transferem as encomendas para o estafeta local. Consulta [por que o rastreio deixa de atualizar](guide:tracking-not-updating).
- **Usa o número da transportadora local:** assim que a Maersk indica transportadora e código, passam a valer o seu rastreio e apoio. O Peek mostra «Entrega:» para [Amazon Logistics](carrier:amazon-logistics), [OnTrac](carrier:ontrac) e [DPD Áustria](carrier:dpd-at).
- **Dada como entregue ou danificada:** verifica entradas, salas de correio e vizinhos, consulta o rastreio do parceiro local e avisa rapidamente a loja. Consulta [entregue mas não recebida](guide:delivered-not-received), [encomenda perdida](guide:lost-parcel), [encomenda danificada](guide:damaged-parcel) e [devolvida ao remetente](guide:returned-to-sender).

## Apoio Maersk eCommerce e portais oficiais

A página de apoio da Maersk oferece chat ao vivo e gestão de casos, sobretudo para clientes empresariais; os vendedores reservam pelo acesso empresarial MyParcel da Maersk.

- **Apresentação Maersk E-Commerce e pedidos comerciais:** [maersk.com/supply-chain-logistics/e-commerce-logistics](https://www.maersk.com/supply-chain-logistics/e-commerce-logistics).
- **Apoio geral e FAQ Maersk:** [maersk.com/support](https://www.maersk.com/support) e [maersk.com](https://www.maersk.com/).
- **Apoio ao destinatário:** contacta a loja online onde compraste o artigo (ou a transportadora local de última milha assim que esta registar a encomenda).

> Tem cuidado com SMS falsas a pedir que cliques numa ligação desconhecida e pagues uma pequena taxa para desbloquear uma «encomenda Maersk». Confirma sempre avisos de entrega na página da tua encomenda na loja ou no site oficial da transportadora local. Consulta [SMS falsas de entrega](guide:delivery-scam-texts).

## Seguir uma encomenda Maersk eCommerce com o Peek

O [Peek](/) consulta automaticamente a Maersk eCommerce como candidata única quando introduzes um número `1M` de 19 carateres (`1MAB4829C7360514928` ou `1MKX7391E4829173605`, inventados) e mostra «Maersk eCommerce tem esta encomenda.» assim que a API de encomendas Maersk confirma; o Peek reconhece diretamente também ligações coladas `maersk.com/tracking/parcel/`. Se precisares de a escolher manualmente, toca em «Alterar» ao adicionar ou «Alterar transportadora» no ecrã da encomenda (pesquisar «B2C Europe», «InterPost», «Maersk Parcel» ou «Maersk E-Delivery» também a encontra).

O Peek lê o rastreio de encomendas Maersk e mostra a etapa («Etiqueta criada», «Aceite pela transportadora», «Em trânsito», «Em distribuição», «Problema reportado», «Entregue»), o texto dos registos da Maersk e da transportadora final, localidade e região, dia estimado de entrega quando indicado pela Maersk, país de destino e hora de entrega. Os códigos de estado e o nome do serviço ficam fora de todas as apps Peek. A Maersk indica o desfasamento em relação a UTC nos seus registos e na maioria dos parceiros; um registo parceiro sem desfasamento (como o da DPD) é datado no fuso do respetivo local (Viena para um registo em Graz) ou no do registo vizinho, e o Peek mostra-os todos na hora do teu dispositivo. O Peek conserva uma única cópia quando o rastreio repete um registo parceiro. Quando a Maersk indica [Amazon Logistics](carrier:amazon-logistics), [OnTrac](carrier:ontrac) ou [DPD Áustria](carrier:dpd-at) com um número compatível com essa transportadora, o Peek verifica se ela tem a encomenda: a ficha mostra então «Entrega:» e o nome da transportadora, listando ambos os números. O Peek nunca lê o número de pedido do cliente, a localidade e código postal de destino nem a ligação do parceiro.

Enquanto tens a encomenda aberta no Peek, ou no dia da entrega com as notificações ativadas, o Peek consulta até de 10 em 10 minutos; no dia em que a Maersk mostra `77203` (`Out for Delivery`), de 2 em 2 minutos a partir das 6h00 no relógio do registo mais recente enquanto o rastreio Maersk responder. Caso contrário, o Peek verifica de hora a hora. Após 12 horas apenas em «Etiqueta criada» ou «Aceite pela transportadora», ou dois dias sem novidades, verifica de hora a hora independentemente da forma como acompanhas a encomenda, e de 6 em 6 horas após sete dias em «Etiqueta criada». Quando o rastreio Maersk não responde e o Peek acompanha a encomenda apenas através de serviços de rastreio, verifica de hora a hora, de 3 em 3 horas após um dia sem novidades e até de 15 em 15 minutos no dia em que a encomenda está em distribuição, enquanto tiveres a encomenda aberta ou as notificações ativadas.

Quando o registo mais recente tem quatro dias e a encomenda continua em «Etiqueta criada», «Aceite pela transportadora» ou «Em trânsito», o Peek assinala-a com o dia desse registo, como «Sem atualizações desde seg 12 out». A página da encomenda apresenta então «É habitual uma encomenda ficar sem novidades em voos longos, na alfândega ou ao fim de semana. O Peek continua a perguntar a Maersk eCommerce.» Passadas três semanas sem novos registos, mostra «Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, contacta o vendedor. O Peek continua a perguntar a Maersk eCommerce.»

Podes escolher alertas para «Todos os registos», «Passos importantes» ou «Só a entrega». O Peek acompanha apenas atualizações de rastreio e não pode desalfandegar encomendas, redirecionar envios nem contactar a Maersk eCommerce ou os seus parceiros locais por ti.

## Perguntas sobre o rastreio Maersk eCommerce

### O que é a Maersk eCommerce (B2C Europe / InterPost)?

A Maersk eCommerce é a rede de consolidação e entrega B2C da A.P. Moller–Maersk na América do Norte e Europa (integrando as antigas redes B2C Europe e InterPost), movimentando mais de 80 milhões de encomendas por ano através de mais de 75 transportadoras parceiras.

### Como é um número de rastreio Maersk eCommerce?

Os números de encomenda Maersk eCommerce têm 19 carateres: `1M`, seguido de um código alfanumérico de cliente de 7 carateres (com pelo menos uma letra) e 10 dígitos — por exemplo `1MAB4829C7360514928` (inventado).

### Quem entrega a minha encomenda Maersk eCommerce à minha porta?

A Maersk consolida e transporta a encomenda entre hubs e entrega o último troço a um parceiro regional no teu país, como [Amazon Logistics](carrier:amazon-logistics), [OnTrac](carrier:ontrac), [DPD Áustria](carrier:dpd-at) ou correios locais.

### Por que fica a minha encomenda Maersk eCommerce dias em `70000 Shipment Manifest Received`?

`70000` significa que o manifesto do vendedor lista a encomenda antes de a Maersk a receber, pelo que nada avança até o vendedor a entregar: contacta o vendedor se continuar aí. A Maersk cria a sua etiqueta `70001` quando a encomenda entra na rede, muitas vezes minutos antes de `70002` (`Parcel Received by Maersk`).

:::sources
- [Maersk: serviços de entrega de encomendas E-Commerce](https://www.maersk.com/supply-chain-logistics/e-commerce-logistics) – 80M+ encomendas anuais, 75+ transportadoras integradas, redes domésticas, internacionais e devoluções na América do Norte e Europa
- [Maersk: site oficial](https://www.maersk.com/) – portal logístico global e acesso ao rastreio de encomendas
- [Maersk: apoio e FAQ](https://www.maersk.com/support) – portal oficial de apoio Maersk
- [Universal Parcel Scraper: maersk-ecommerce](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/maersk-ecommerce) – formato `1M` de 19 carateres, regra de ligação `maersk.com/tracking/parcel/`, códigos 70000–77200, estimativa e país, e regras de passagem Amazon/OnTrac/DPD-AT
:::
