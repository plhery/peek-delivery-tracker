---
title: Rastreamento SpeedPAK (eBay): códigos de 28 caracteres
description: Rastreie encomendas SpeedPAK do eBay (Orange Connex): códigos ES, EE e EX de 28 caracteres, status, entregador final, prazos, reembolso e contato.
slug: rastreamento-speedpak
published: 2026-11-24
updated: 2026-11-24
---

O SpeedPAK é o serviço de envio do eBay para pedidos internacionais, operado com a empresa logística Orange Connex e criado para encomendas que saem da China. O código tem 28 caracteres e começa com `ES`, `EE` ou `EX`: digite-o na página de rastreamento da Orange Connex, [orangeconnex.com/tracking](https://www.orangeconnex.com/tracking), ou na caixa acima. No eBay, o número aparece como link ao lado do item no seu Histórico de compras.

## Como é o código de rastreamento do SpeedPAK

Todo código SpeedPAK lido no rastreador da Orange Connex tem 28 letras e dígitos, iniciados por `ES`, `EE` ou `EX`. Com caracteres fictícios:

| Código | O que é |
| --- | --- |
| `ES4829173640582917364058290N` | `ES` e 26 letras e dígitos: SpeedPAK. |
| `EE4829173640582917364058295X` | `EE` e 26 letras e dígitos: também SpeedPAK. |
| `EX4829173640582917364058291A` | `EX` e 26 letras e dígitos: SpeedPAK. Nos registros observados até hoje, a [DHL Express](carrier:dhl-express) fez todo o transporte. |
| `EE482917367CN` | Duas letras, nove dígitos e duas letras: código postal internacional, não SpeedPAK. |

As páginas do eBay para vendedores citam ainda códigos SpeedPAK começando com `EM`, que o Peek não rastreia como SpeedPAK.

**Onde encontrar.** No Histórico de compras do eBay, o código surge como link ao lado do pedido. Comprou como convidado? Abra o e-mail cujo assunto começa com "Order Confirmed", clique em ver detalhes do pedido e depois em detalhes da entrega. Se não houver código, o eBay orienta pedir ao vendedor para adicioná-lo. Veja mais em [como encontrar seu código de rastreamento](guide:find-tracking-number).

**Um segundo código mais tarde.** Assim que a encomenda chega ao seu país, o histórico do SpeedPAK muitas vezes indica a transportadora local e o código próprio dela, que pode ser rastreado direto no site dessa transportadora.

## O que significam os status do SpeedPAK

O rastreador da Orange Connex existe apenas em inglês ou chinês; se abrir em chinês, use o seletor de idioma. As principais linhas, na ordem habitual:

| O SpeedPAK mostra | O que significa |
| --- | --- |
| `Shipment Data Received` | O vendedor emitiu a etiqueta; o SpeedPAK tem os dados, mas ainda não recebeu o pacote. |
| `Package Received` | Coletado com o vendedor. |
| `Arrived at Regional Distribution Center` | Num centro de triagem regional, geralmente ainda na China. |
| `Export Customs Declaration Completed` | Liberado pela alfândega de exportação na origem. |
| `Departed from Origin Country` | O voo partiu. |
| `Arrived at Destination Hub` | Chegou ao seu país, antes da alfândega de importação. |
| `Import Customs Clearance Completed` | Liberado pela alfândega no seu país. |
| `Destination DC arrival` | Chegou a um centro de distribuição no seu país. |
| `Out for delivery` | Saiu para entrega hoje. |
| `Delivered` | Entregue. |

Algumas frases mudam conforme a remessa: `Arrived at destination country` e `The consignment has arrived in the country of destination` equivalem a `Arrived at Destination Hub`. `Shipment Picked Up (A-SCAN)` é a primeira leitura física após a coleta.

Uma linha como `LM Carrier: [transportadora] , LM tracking No.: [código]` indica a transferência: *LM* significa *last mile* (última milha) e informa quem entrega na sua porta junto ao código dela. Depois disso, o histórico repassa as leituras desse parceiro nas palavras dele, como `The item is received by PostNL`. Na rota internacional da PostNL, seguem-se as etapas do correio de destino em inglês: `Item is cleared by customs` (liberado pela alfândega) e `Arrival at Delivery Post Office` (na agência entregadora), ambas exibidas como "Em trânsito" no Peek, e após tentativa falha surge `The item is being held, addressee being notified`: aguarda naquela agência para retirada ("Pronta para retirada"). Algumas linhas aparecem em chinês mesmo na interface em inglês: `货物运输中` significa simplesmente "mercadoria em trânsito". `Delivered. (Recipient's front door)` indica onde o volume foi deixado.

O rastreamento pode ficar parado em `Arrived at Destination Hub` ou numa etapa aduaneira por vários dias até a transportadora local escanear o pacote. Entenda o trâmite em [encomendas retidas na alfândega](guide:customs).

## Quem faz a entrega de encomendas SpeedPAK

O SpeedPAK não faz entrega porta a porta por conta própria: seu histórico indica a transportadora que assume no destino. Nos históricos analisados para esta página, [USPS](carrier:usps), [UniUni](carrier:uniuni) e DHL eCommerce (repassando à USPS) entregaram nos EUA, e a PostNL (escrita "NL Post") levou encomendas para Suíça e Portugal.

Nenhuma página do eBay ou da Orange Connex lista previamente o entregador final no Reino Unido, Alemanha, França, Itália ou Espanha. Não tente adivinhar: aguarde a linha `LM Carrier` ou pergunte ao vendedor.

Nenhuma página do SpeedPAK ou da Orange Connex define regras de tentativas de entrega, cartões de aviso, pontos de retirada, lockers ou prazo de guarda — tudo segue as regras da transportadora final. Assim que tiver o nome e o código dela, use o site dela para reagendar ou localizar o volume.

Certos envios SpeedPAK têm rastreamento parcial. A Orange Connex informa que o serviço Economy oferece rastreamento apenas até metade do trajeto, e a modalidade europeia mais ampla do eBay rastreia somente da coleta até a chegada ao país de destino — nesses casos, o histórico pode parar após o pouso.

**Prazos de referência.** A tabela do eBay para vendedores indica 8 a 12 dias para os EUA e 17 países europeus (incluindo Reino Unido e Irlanda), e 10 a 15 dias para a faixa europeia ampliada. A Orange Connex cita 8 a 12 dias úteis no serviço Standard. São referências, não uma data garantida para seu pedido.

## Rastreamento SpeedPAK parado ou encomenda extraviada

**Sem atualização.** A Orange Connex orienta compradores a procurar o vendedor para confirmar o despacho e a contatá-lo caso não haja movimentação por mais de cinco dias. Veja também [por que o rastreamento para de atualizar](guide:tracking-not-updating) e os prazos do eBay em [como rastrear um pedido do eBay](guide:ebay-orders).

**Atraso ou extravio.** No eBay UK, você tem até 30 dias corridos após a data estimada de entrega para avisar que o item não chegou. O vendedor tem 3 dias úteis para responder; se não responder ou não resolver, você pode pedir intervenção do eBay em até 21 dias úteis da abertura do pedido. O eBay informa que reembolsos costumam ficar disponíveis em 3 a 5 dias úteis.

**Entregue, mas não chegou.** O eBay recomenda checar primeiro com vizinhos e depois reportar pelo mesmo pedido.

A página de reclamações da Orange Connex destina-se apenas a vendedores, não a destinatários: reclamações e reembolsos passam sempre pelo vendedor e pelo eBay.

## Atendimento e telefone do SpeedPAK

Nem o eBay nem a Orange Connex divulgam contato SpeedPAK voltado a compradores no Reino Unido ou Irlanda; a própria Orange Connex orienta os compradores a falar com o vendedor pelo eBay ou plataforma de compra.

Canais de suporte divulgados no site da Orange Connex:

- **Telefone:** 400 126 0008 (China continental), 852 30183458 (Hong Kong).
- **Horário:** segunda a sábado, das 9h às 18h (horário padrão da China); fechado aos domingos e feriados nacionais.
- **E-mail:** CS@orangeconnex.com.

O eBay publica o mesmo e-mail e horário (horário da China) numa página para vendedores. Uma vez informada a transportadora final, é o atendimento dela que resolve entregas falhas.

## Como rastrear uma encomenda SpeedPAK no Peek

O [Peek](/) reconhece códigos `ES`, `EE` ou `EX` de 28 caracteres na hora, pois nenhuma outra transportadora usa esse padrão. Links de orangeconnex.com ou orangeconnex.cn contendo o código também podem ser colados. Ele exibe o status, o histórico com cidade e país de cada leitura, o país de destino, o horário de entrega e a transportadora final com seu código. Como o SpeedPAK não fornece previsão de entrega nem ponto de retirada, o Peek não os exibe.

Quando o SpeedPAK informa a transportadora entregadora, o Peek pode consultá-la e migrar para ela assim que surgir progresso mais recente. O pacote mantém o código original, a página indica "Entrega por" seguido da transportadora e lista o novo código com link para o site dela (enquanto o parceiro só tiver o aviso prévio sem leitura própria, exibe "Anunciada a …, ainda não recebida"). Isso depende do que o SpeedPAK publicar, não ocorrendo em todos os envios.

Enquanto o pacote está aberto no Peek, ou no dia da entrega com alertas ligados, a verificação acontece até a cada 10 minutos; no dia da saída para entrega, a partir das 6h locais, a cada 2 minutos enquanto o rastreador oficial do SpeedPAK responde. Fora disso, de hora em hora. Após 12 horas apenas em "Etiqueta criada" ou "Recebida pela transportadora", ou dois dias sem novidades, verifica de hora em hora de qualquer modo, e a cada 6 horas depois de sete dias em "Etiqueta criada" (salvo se estiver aberto ou previsto para aquele dia). Quando só os serviços de rastreamento respondem, verifica de hora em hora, a cada 3 horas após um dia sem nova movimentação e, no dia da entrega, até a cada 15 minutos enquanto o pacote estiver aberto ou com alertas ligados. Quando outra transportadora assume, o Peek segue o ritmo dela.

**Dias sem nova leitura.** Quando a leitura mais recente completa quatro dias e a encomenda continua em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito", o Peek destaca o dia da leitura, como "Sem atualizações desde seg., 12 de out.", com a mensagem "É comum uma encomenda ficar sem atualizações em voos longos, na alfândega ou no fim de semana. O Peek continua consultando SpeedPAK." e o link "Pedir ajuda a SpeedPAK" apontando para a ajuda de item não recebido do eBay (ou para a transportadora final assim que ela assume). Após três semanas sem novidade, exibe "Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, fale com o vendedor. O Peek continua consultando SpeedPAK."

O Peek coloca cada leitura no mapa e oferece alertas para "Cada movimentação", "Etapas importantes" ou "Só a entrega". Ele não fala com o SpeedPAK ou vendedor nem abre reclamações por você.

## Perguntas frequentes sobre rastreamento SpeedPAK

### O que é o eBay SpeedPAK?

É um serviço de envio do eBay para compras internacionais, gerido pela Orange Connex e criado para encomendas da China. O eBay também oferece o serviço a partir de alguns outros países — da Alemanha, por exemplo, envia para EUA, Reino Unido e Austrália.

### O SpeedPAK é confiável?

Sim: o eBay apresenta o SpeedPAK como seu próprio serviço de frete em parceria com a Orange Connex. Se o pacote não chegar, você reporta ao vendedor e ao eBay como em qualquer pedido da plataforma.

### O SpeedPAK é entregue pela Royal Mail no Reino Unido?

Nenhuma página oficial confirma qual transportadora entrega pacotes SpeedPAK no Reino Unido. A linha `LM Carrier` no rastreamento informa o entregador do seu envio quando o SpeedPAK divulga esse dado.

### Por que meu rastreamento SpeedPAK aparece em chinês?

O site da Orange Connex oferece inglês ou chinês e abre em chinês a menos que você já tenha escolhido inglês ali ou que o idioma do navegador seja exatamente "en" (variantes britânica ou americana não contam). Troque o idioma na página; ainda assim algumas poucas linhas permanecem em chinês.

:::sources
- [Orange Connex: Tracking For SpeedPAK](https://www.orangeconnex.com/tracking) – idiomas, telefones, horários, status e entregadores de última milha
- [Orange Connex: FAQ](https://www.orangeconnex.com/helpFAQ) – modalidades, 8 a 12 dias úteis no Standard e rastreamento parcial no Economy
- [Orange Connex Logistics DE: Tracking](https://globalshipping.orangeconnex.de/tracking) – contato com vendedor, cinco dias sem movimentação e atrasos na transferência local
- [Orange Connex Logistics DE: Help centre](https://globalshipping.orangeconnex.de/helpPage) – compradores contatam o vendedor
- [Orange Connex: Claims](https://help.orangeconnex.com/helpCentre/claims.html) – reclamações voltadas a vendedores
- [eBay: SpeedPAK shipping services](https://export.ebay.com/sea-en/speedpak/speedpak-shipping-services/) – serviço da China, prazos, países e contato divulgado pelo eBay
- [eBay: eBay SpeedPAK](https://export.ebay.com/en/ebay-speedpak/) – parceria com Orange Connex e códigos EX, EM e EE
- [eBay UK: International purchases and postage](https://www.ebay.co.uk/help/buying/postage-delivery/changing-delivery-address-method/international-purchases-postage-buy?id=4057) – SpeedPAK da Alemanha para EUA, Reino Unido e Austrália
- [eBay UK: Tracking your item](https://www.ebay.co.uk/help/buying/postage-delivery/tracking-item?id=4027) – Histórico de compras, pedidos de convidado e código ausente
- [eBay UK: Get help with an item that hasn't arrived](https://www.ebay.co.uk/help/buying/returns-refunds/get-help-item-hasnt-arrived?id=4042) – 30 dias, 3 e 21 dias úteis, reembolsos e vizinhos
:::
