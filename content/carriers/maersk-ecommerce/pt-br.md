---
title: Rastreamento Maersk eCommerce: códigos 1M e status
description: Rastreie encomendas da Maersk eCommerce (B2C Europe / InterPost): formato 1M de 19 caracteres, códigos 70000–77200, entrega local e suporte da Maersk.
slug: maersk-ecommerce-tracking
published: 2026-11-26
updated: 2026-11-26
---

A **Maersk eCommerce** (*Maersk Logistics & Services USA Inc.*, reunindo as antigas plataformas de encomendas *B2C Europe* e *InterPost*) é a rede doméstica e internacional de entrega B2C da A.P. Moller–Maersk ([maersk.com/supply-chain-logistics/e-commerce-logistics](https://www.maersk.com/supply-chain-logistics/e-commerce-logistics)). Movimentando mais de 80 milhões de pacotes por ano com mais de 75 transportadoras integradas na América do Norte e na Europa, a Maersk eCommerce faz a triagem de remessas de lojas virtuais em instalações próprias, realiza o desembaraço aduaneiro B2C em rotas internacionais e entrega a etapa final a transportadoras e correios locais, como Amazon Logistics, [OnTrac](carrier:ontrac) e [DPD Áustria](carrier:dpd-at). Cole seu código `1M` de 19 caracteres na caixa acima para acompanhar tanto as leituras nas unidades da Maersk quanto os eventos repassados pela transportadora local.

## Códigos de rastreamento da Maersk eCommerce (formato `1M`)

A Maersk eCommerce emite códigos alfanuméricos de 19 caracteres iniciados por `1M`, seguidos por um código de cliente/lojista de 7 caracteres (com pelo menos uma letra) e 10 dígitos:

| Formato (inventado) | O que é |
| --- | --- |
| `1MAB4829C7360514928` | Código de encomenda Maersk eCommerce de 19 caracteres (`1M` + código de cliente de 7 caracteres + 10 dígitos). |
| `1MKX7391E4829173605` | Exemplo alternativo de código `1M` de 19 caracteres usado em rotas da América do Norte ou Europa. |

Procure o código `1M` no e-mail de envio da loja ou no link de rastreamento do pedido (`maersk.com/tracking/parcel/...`). O rastreador da Maersk pode informar a transportadora de última milha e o número atribuído por ela. Veja [formatos de código de rastreamento](guide:tracking-number-formats).

## O que significam os status de rastreamento da Maersk eCommerce

As leituras próprias da Maersk usam códigos de `70000` a `72004`, e as da transportadora de última milha começam em `77100`:

| Código e texto da Maersk | O que significa para você | O Peek mostra |
| --- | --- | --- |
| `70000` `Shipment Manifest Received`, `70001` `Maersk Shipping Label Created` | `70000`: o manifesto do vendedor lista a encomenda antes de ela chegar à Maersk. `70001`: etiqueta própria da Maersk, criada quando o pacote entra na rede, muitas vezes minutos antes de `70002`. | "Etiqueta criada" |
| `70002` `Parcel Received by Maersk` | Pacote recebido fisicamente e escaneado em uma unidade de recepção da Maersk eCommerce. | "Recebida pela transportadora" |
| `72001` `Arrived at Maersk Facility`, `72002` `Processing at Maersk Facility`, `72004` `Departed from Maersk Facility` | Triado em um centro de consolidação da Maersk e expedido rumo à região de destino ou aeroporto/porto de entrada. | "Em trânsito" |
| `77100` `In-Transit Scan`, `77101` `Enroute to Facility`, `77102` `Arrival at Facility`, `77103` `Processing at Facility`, `77105` `Departed from Facility`, `77106` `Parcel In-transit` | Eventos domésticos de trânsito repassados pela transportadora local de última milha (como OnTrac, Amazon Logistics ou DPD). | "Em trânsito" |
| `77203` `Out for Delivery` | Carregada no veículo do entregador local para entrega hoje. | "Saiu para entrega" |
| `77200` `Parcel Successfully Delivered` | Entregue no endereço do destinatário pela parceira de última milha. | "Entregue" |

Leituras sem um desses códigos aparecem como "Precisa de atenção" quando a Maersk as marca como exceção; caso contrário, seguem a etapa correspondente exibida no rastreador da Maersk.

## Cobertura, devoluções e alfândega internacional

A Maersk E-Commerce oferece cobertura completa de entrega em 32 países da América do Norte e Europa, com pontos de entrada de origem na China e no Sudeste Asiático ([Maersk E-Commerce](https://www.maersk.com/supply-chain-logistics/e-commerce-logistics)):

- **Instalações e parceiros:** na Europa, 8 unidades de triagem e cross-docking, hubs de entrada em Londres, Taulov e Amsterdã e mais de 40 parceiros de última milha e correios, com entrega em domicílio e pontos de retirada. Na América do Norte, centros de triagem e portais em JFK, LAX e ORD; o serviço Standard leva 2–7 dias nos EUA e 1–10 dias no Canadá, e o Expedited leva 2–5 e 1–5 dias.
- **Desembaraço aduaneiro internacional:** a Maersk desembaraça encomendas B2C para envio ao Reino Unido, à UE e aos EUA. O pagamento de impostos e IVA de importação na chegada depende de o vendedor tê-los cobrado no checkout. Veja [encomendas retidas na alfândega](guide:customs).
- **Devoluções de e-commerce:** devoluções domésticas e internacionais por ponto de entrega ou coleta em casa para lojas que oferecem o serviço, sem papel e com isenção tarifária em algumas rotas.

## Encomenda da Maersk eCommerce atrasada, sem atualização ou extraviada

Como a Maersk eCommerce é uma consolidadora B2B contratada por marcas de e-commerce, **contate sempre a loja virtual primeiro** para alterações de endereço, itens faltantes, trocas ou reembolsos.

- **Intervalo sem atualização no transporte ou repasse:** o rastreamento costuma pausar por alguns dias entre `72004` (`Departed from Maersk Facility`) e a primeira leitura `77100+` da parceira local enquanto caminhões ou voos transferem os pacotes para a transportadora de destino. Veja [por que o rastreamento para de atualizar](guide:tracking-not-updating).
- **Use o código da transportadora local:** assim que a Maersk informa a transportadora final e o número dela, passam a valer o rastreador e o suporte dessa empresa. O Peek mostra "Entrega por" para Amazon Logistics, [OnTrac](carrier:ontrac) e [DPD Áustria](carrier:dpd-at).
- **Marcada como entregue ou danificada:** confira entradas, portarias e vizinhos, verifique o histórico da parceira local e avise a loja rapidamente. Veja [entregue, mas não recebida](guide:delivered-not-received), [encomenda perdida](guide:lost-parcel), [encomenda danificada](guide:damaged-parcel) e [devolvida ao remetente](guide:returned-to-sender).

## Suporte e portais oficiais da Maersk eCommerce

A página de suporte da Maersk oferece chat ao vivo e gestão de chamados, voltados principalmente a clientes empresariais; vendedores fazem envios pelo login comercial MyParcel da Maersk.

- **Visão geral e contatos comerciais Maersk E-Commerce:** [maersk.com/supply-chain-logistics/e-commerce-logistics](https://www.maersk.com/supply-chain-logistics/e-commerce-logistics).
- **Suporte geral e FAQs da Maersk:** [maersk.com/support](https://www.maersk.com/support) e [maersk.com](https://www.maersk.com/).
- **Suporte ao destinatário:** contate a loja onde você comprou o produto (ou a transportadora local de última milha assim que ela escanear a encomenda).

> Desconfie de mensagens SMS falsas pedindo para clicar em um link desconhecido e pagar uma pequena taxa para liberar uma "encomenda da Maersk". Confirme sempre qualquer aviso na página do pedido na loja ou no site oficial da transportadora local. Veja [golpes de SMS de entrega](guide:delivery-scam-texts).

## Acompanhe uma encomenda da Maersk eCommerce com o Peek

O [Peek](/) consulta a Maersk eCommerce automaticamente como única candidata quando você informa um código `1M` de 19 caracteres (`1MAB4829C7360514928` ou `1MKX7391E4829173605`, inventados) e mostra "Maersk eCommerce está com esta encomenda." assim que a API de encomendas da Maersk confirma; o Peek também reconhece links colados de `maersk.com/tracking/parcel/` diretamente. Se precisar selecionar manualmente, toque em "Alterar" ao adicionar ou em "Alterar transportadora" na tela da encomenda (buscar por "B2C Europe", "InterPost", "Maersk Parcel" ou "Maersk E-Delivery" também encontra).

O Peek lê o rastreador de encomendas da Maersk e exibe a etapa ("Etiqueta criada", "Recebida pela transportadora", "Em trânsito", "Saiu para entrega", "Precisa de atenção", "Entregue"), o texto das leituras da Maersk e da transportadora final, a cidade e região, o dia estimado de entrega quando fornecido, o país de destino e o horário de entrega. Códigos internos de status e nome do serviço ficam fora dos aplicativos do Peek. A Maersk informa o fuso horário das próprias leituras e da maioria das parceiras; uma leitura parceira sem fuso, como a da DPD, é interpretada no fuso horário do local (Viena para uma leitura em Graz) ou no fuso da leitura vizinha, e o Peek exibe tudo no relógio do seu aparelho. O Peek mantém apenas uma cópia quando o rastreador repete uma movimentação parceira. Quando a Maersk informa Amazon Logistics, [OnTrac](carrier:ontrac) ou [DPD Áustria](carrier:dpd-at) com um código compatível com aquela transportadora, o Peek verifica se ela está com o pacote: a página passa a exibir "Entrega por" com o nome da transportadora e lista os dois números. O Peek não lê o número do pedido do cliente, a cidade e o código postal de destino nem o link da parceira.

Enquanto você mantém a encomenda aberta no Peek, ou no dia da entrega com notificações ligadas, o Peek consulta até a cada 10 minutos; no dia em que a Maersk registra `77203` (`Out for Delivery`), verifica a cada 2 minutos a partir das 6h no relógio da leitura mais recente, enquanto o rastreador da Maersk responder. Caso contrário, verifica de hora em hora. Após 12 horas apenas em "Etiqueta criada" ou "Recebida pela transportadora", ou dois dias sem novidades, consulta de hora em hora de qualquer forma, e a cada 6 horas depois de sete dias em "Etiqueta criada". Quando o rastreador da Maersk não responde e o Peek acompanha somente por serviços de rastreamento, consulta de hora em hora, a cada 3 horas após um dia quieto e até a cada 15 minutos no dia da saída para entrega.

Quando a leitura mais recente tem quatro dias e o pacote permanece em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito", o Peek destaca o dia daquela leitura, como "Sem atualização desde seg 12 out". A página passa a mostrar: "É comum uma encomenda ficar sem atualizações em voos longos, na alfândega ou no fim de semana. O Peek continua consultando Maersk eCommerce." Após três semanas sem novas leituras, exibe: "Nenhuma novidade há mais de três semanas. Se ainda não chegou, fale com o vendedor. O Peek continua consultando Maersk eCommerce."

Você pode escolher alertas para "Todas as movimentações", "Etapas importantes" ou "Somente entrega". O Peek apenas acompanha atualizações de rastreamento e não pode liberar pacotes na alfândega, redirecionar entregas nem contatar a Maersk eCommerce ou suas parceiras de última milha por você.

## Perguntas sobre o rastreamento da Maersk eCommerce

### O que é a Maersk eCommerce (B2C Europe / InterPost)?

A Maersk eCommerce é a rede de consolidação e entrega B2C da A.P. Moller–Maersk na América do Norte e na Europa (integrando as antigas redes B2C Europe e InterPost), movimentando mais de 80 milhões de encomendas por ano por meio de mais de 75 transportadoras parceiras de última milha.

### Qual é o formato do código de rastreamento da Maersk eCommerce?

Os códigos da Maersk eCommerce têm 19 caracteres: `1M`, seguido de um código de cliente alfanumérico de 7 caracteres (com pelo menos uma letra) e 10 dígitos — por exemplo `1MAB4829C7360514928` (inventado).

### Quem entrega minha encomenda da Maersk eCommerce na minha porta?

A Maersk consolida e transporta a encomenda entre os centros e entrega a etapa final a uma parceira regional no seu país, como Amazon Logistics, [OnTrac](carrier:ontrac), [DPD Áustria](carrier:dpd-at) ou os correios locais.

### Por que minha encomenda da Maersk eCommerce fica parada dias em `70000 Shipment Manifest Received`?

`70000` indica que o manifesto do vendedor lista a encomenda antes de a Maersk recebê-la, portanto nada avança até o vendedor entregar o pacote: fale com o vendedor se permanecer assim. A Maersk adiciona `70001`, sua própria etiqueta, quando o pacote entra na rede, muitas vezes minutos antes de `70002` (`Parcel Received by Maersk`).

:::sources
- [Maersk: E-Commerce Parcel Delivery Services](https://www.maersk.com/supply-chain-logistics/e-commerce-logistics) – mais de 80 milhões de pacotes anuais, mais de 75 transportadoras integradas, redes doméstica/internacional/devoluções na América do Norte e Europa
- [Maersk: Official Website](https://www.maersk.com/) – portal global de logística e acesso ao rastreamento de encomendas
- [Maersk: Support & FAQs](https://www.maersk.com/support) – portal oficial de suporte da Maersk
- [Universal Parcel Scraper: maersk-ecommerce](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/maersk-ecommerce) – formato `1M` de 19 caracteres, regra de link `maersk.com/tracking/parcel/`, códigos de status 70000–77200, previsão de entrega e país, e regras de repasse para Amazon/OnTrac/DPD-AT
:::
