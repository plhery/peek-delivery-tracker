---
title: Rastreamento Landmark Global: status, parceiros e contato
description: Rastreie uma encomenda Landmark Global: de onde vem o código LTN, o que cada status significa, quem entrega no seu país e quem procurar se travar.
slug: rastreamento-landmark-global
published: 2026-11-26
updated: 2026-11-26
---

A Landmark Global transporta pedidos de lojas virtuais entre países. Ela faz parte da Bnode, o grupo de Bruxelas que também controla a bpost, e geralmente entrega o pacote a uma transportadora local do seu país para o trecho final. Rastreie com o Landmark Tracking Number (LTN) enviado no e-mail de confirmação da loja, no [site de rastreamento da Landmark](https://track.landmarkglobal.com/) ou colando o código no campo acima.

## Código de rastreamento Landmark Global

A Landmark chama esse código de LTN, seu "identificador único criado para cada encomenda para acompanhar toda a jornada", e informa que você normalmente o recebe da loja, muitas vezes na confirmação do pedido.

| Formato (inventado) | O que é |
| --- | --- |
| `LTN48291736` | LTN e 8 dígitos: um Landmark Tracking Number. |
| `LTN482917365` | LTN e 9 dígitos: o mesmo tipo de código. |
| `LTN482917365N1` | LTN, 9 dígitos e N1: o mesmo, com o sufixo da Landmark. |
| `AB48291736` | Referência do pedido ou do pacote da loja, que o rastreador da Landmark também aceita. |
| Código de outra transportadora | O código próprio do parceiro de entrega para a mesma encomenda (Evri, UPS, bpost…). |

A página de rastreamento da Landmark aceita até 25 códigos de uma vez, separados por vírgulas. Assim que o parceiro de entrega é definido, a página o exibe em "Delivery Partner" com o código próprio daquela empresa e um link para o site dela, então a mesma encomenda pode ter dois números.

Um LTN com 7 ou 10 dígitos, ou com terminação diferente de N1, não segue os formatos da Landmark: confira o e-mail para ver se há erro de digitação. Veja mais em [formatos de código de rastreamento](guide:tracking-number-formats).

## Status de rastreamento da Landmark Global explicados

O site de rastreamento da Landmark tem um menu de idiomas, mas o Peek lê a página em inglês, portanto estes são os textos exibidos pelo Peek. Em outro idioma, a página da Landmark pode redigir uma etapa de modo diferente, e as movimentações do parceiro mantêm o texto do próprio parceiro. Na ordem em que costumam aparecer:

| A Landmark diz | O que significa para você |
| --- | --- |
| `Shipment Data Uploaded` | A loja enviou os dados da encomenda para a Landmark. A Landmark ainda não recebeu o pacote. |
| `Processed` | A Landmark processou a encomenda. |
| `Scanned at Landmark crossdock facility` | Está passando por um centro de transbordo da Landmark. |
| `Grouped when pallet scanned to crossing (… #…)` | A Landmark colocou o pacote em um palete para a travessia até o seu país; os parênteses indicam o ponto de travessia, como DDU ou SLC INTERPOST, e o número do palete. |
| `Transmitted customs information` | Os dados aduaneiros foram transmitidos antecipadamente. |
| `Customs cleared` | A alfândega liberou a encomenda. |
| `Departure to country of destination` | Está no trecho internacional. |
| `Crossing border and in transit to carrier hub` | Cruzando a fronteira a caminho do centro de triagem do parceiro de entrega. |
| `Departure from office of exchange` | Foi exportada do país de origem. |
| `Received in destination country` | Chegou ao seu país. |
| `Out for Delivery` | Está com o entregador hoje. |
| `Item arrival at collection point for pick-up` | Aguardando você no balcão do correio local. |
| `Delivered` | Entregue. |

Depois que o parceiro de entrega recebe a encomenda, as movimentações dele aparecem nas palavras dele. `Out For Delivery To Courier`, da Evri, significa que o depósito passou o pacote ao entregador, que confirma o recebimento antes da rota. `Item deposited to local postal operator` significa que a Landmark entregou o pacote ao correio do seu país para o trecho final, e não que ele já foi entregue a você.

## Entrega Landmark Global: quem entrega e encomendas perdidas

Na maioria dos países a Landmark não faz a entrega própria. Suas páginas citam alguns parceiros: bpost na Bélgica, sua empresa irmã Apple Express no Canadá, e DHL eCommerce e UPS nos EUA. A página do Reino Unido fala em "múltiplos parceiros logísticos" sem citá-los. Em encomendas consultadas pelo Peek, a Evri assumiu no Reino Unido com suas próprias leituras de hub e depósito, a Hermes entregou na Alemanha, e o correio nacional entregou na Itália, no Marrocos e na Colômbia. São exemplos, não regras fixas: vale o parceiro exibido na sua encomenda.

Avisos de ausência, pontos de retirada, prazos de guarda e novas tentativas seguem as regras desse parceiro, não as da Landmark. Se você não estiver, a Landmark orienta seguir as instruções do entregador ou falar com o atendimento para reagendar ou combinar retirada, lembrando que nem todo serviço oferece reentrega ou retirada.

Alteração de endereço: o FAQ europeu da Landmark afirma que, depois que a encomenda recebe etiqueta, o endereço não pode mais ser alterado. Já a página dos EUA e Canadá diz que a alteração depende da etapa do envio.

Para as regras do parceiro, consulte a página da [Evri](carrier:evri), da [Hermes](carrier:hermes-de), da [UPS](carrier:ups) ou da [bpost](carrier:bpost).

## Encomenda Landmark Global atrasada, extraviada ou devolvida

**Parada em `Shipment Data Uploaded`.** A loja enviou os dados à Landmark, mas a Landmark ainda não recebeu o pacote. Pergunte à loja quando ele foi enviado.

**Sem atualização após a transferência.** Novas movimentações podem aparecer apenas no site do parceiro de entrega, pelo link "Delivery Partner" na página da Landmark. Veja [por que o rastreamento para de atualizar](guide:tracking-not-updating).

**Extraviada, danificada ou item errado.** A Landmark pede que você procure o vendedor, que pode abrir investigação e tratar diretamente com a Landmark. Ela só oferece seguro aos clientes diretos, as lojas. Aja rápido: os termos europeus da Landmark dão à loja 30 dias corridos a partir da leitura de recebimento ("accepted") pela Landmark para registrar reclamação.

**Alfândega.** A Landmark não publica orientações para destinatários sobre tributos; [encomendas retidas na alfândega](guide:customs) explica o que costuma valer.

**Devolvida.** Uma encomenda devolvida viaja com uma nova referência: a página da Landmark traz o link para o rastreamento da devolução, e o Peek mostra "Devolvida ao remetente". Fale com a loja sobre reembolso ou novo envio.

## Atendimento ao cliente e telefone da Landmark Global

As páginas da Landmark recomendam falar primeiro com a loja ou consultar as perguntas frequentes.

- **Reino Unido, Irlanda e Europa:** a [página de suporte ao cliente](https://landmarkglobal.com/eu/en/customer-support/) reúne o FAQ e o botão "Send Email" para ClientservicesEU@landmarkglobal-group.com. Inclua o código de rastreamento e os dados do envio. Não há telefone, chat nem horários informados. Endereço no Reino Unido: Unit 3, Heathrow Logistics Park, Bedfont Rd, Feltham TW14 8EE.
- **EUA e Canadá:** a [página do formulário de suporte](https://landmarkglobal.com/us/en/customer-support-form/) lista o telefone 1-888-205-2251, de segunda a sexta das 8h às 20h EST, e o e-mail clientservices@landmarkglobal.com.

A página geral de contato da Landmark é apenas para consultas comerciais: dúvidas de rastreamento enviadas por lá não são processadas. Assim que houver um parceiro de entrega definido, dúvidas sobre entregas perdidas e retirada devem ser tratadas com esse parceiro.

## Acompanhe uma encomenda Landmark Global com o Peek

O [Peek](/) identifica a Landmark Global na hora para um código LTN. Links de track.landmarkglobal.com ou landmarkglobal.com (com `search=` ou `trck=`) também funcionam, mesmo trazendo uma referência da loja. Se você digitar apenas a referência do pedido ou do pacote da loja, escolha Landmark Global em "Trocar".

Ele lê a página em inglês da Landmark, então os textos aparecem em inglês, as leituras de parceiros vêm nas palavras deles e a confirmação de entrega da Landmark aparece como "Entregue". O Peek exibe o status, o local de cada etapa, o país de destino e o parceiro de entrega com seu código, mas sem dia previsto de entrega, e omite referências de pedido/pacote e avisos de pagamento.

A Landmark registra todos os horários no fuso Central dos EUA. O Peek lê os horários assim, lista o histórico da própria Landmark e converte cada horário para o seu fuso.

Quando o parceiro é uma transportadora acompanhada diretamente pelo Peek, como Evri, UPS, bpost ou Apple Express, a encomenda pode passar a ser acompanhada por lá; a página então mostra "Entrega por" essa transportadora e traz o código do parceiro com link. Isso não ocorre em todas as encomendas. Cada movimentação vai para o mapa, e o Peek pode avisar você sobre "Cada movimentação", "Etapas importantes" ou "Só a entrega".

## Perguntas sobre o rastreamento Landmark Global

### A Landmark Global é uma transportadora?

É uma empresa de envios internacionais para lojas virtuais que trabalha com correios e parceiros de entrega para alcançar mais de 220 destinos. A entrega final geralmente é feita por um parceiro local indicado no rastreamento.

### A Landmark Global faz parte da Canada Post?

Não. A Landmark faz parte da Bnode, o grupo da bpost, desde 2016. No Canadá ela repassa encomendas à empresa irmã Apple Express, também da Bnode, que segundo a Landmark cobre 60% da população canadense.

### Quanto tempo a Landmark Global leva para entregar?

A Landmark diz que o prazo depende do serviço escolhido pela loja e do destino. Sua página para lojas que enviam aos EUA indica um prazo padrão de 3 a 6 dias. Confira os detalhes de envio da loja ou o rastreamento.

:::sources
- [Landmark Global: Customer Support](https://landmarkglobal.com/eu/en/customer-support/) – LTN, falar primeiro com a loja, entrega perdida, mudança de endereço, perda ou dano, seguro, prazos, e-mail e endereços
- [Landmark Global: Customer Support Form (US and Canada)](https://landmarkglobal.com/us/en/customer-support-form/) – telefone, horários, e-mail, alterações de endereço
- [Landmark Global: Contact](https://landmarkglobal.com/eu/en/contact/) – apenas consultas comerciais
- [Landmark Global: Landmark Tracking](https://track.landmarkglobal.com/) – rastreamento, 25 códigos, menu de idiomas
- [Landmark Global: About us](https://landmarkglobal.com/eu/en/about-us/) – parte da Bnode desde 2016
- [Landmark Global: Home](https://landmarkglobal.com/eu/en/) – atuação, parceiros, mais de 220 destinos
- [Landmark Global: Parcel delivery to Belgium](https://landmarkglobal.com/eu/en/destinations/parcel-delivery-to-belgium/) – bpost
- [Landmark Global: Parcel delivery to Canada](https://landmarkglobal.com/eu/en/destinations/parcel-delivery-to-canada/) – Apple Express, parte da Bnode, 60% da população
- [Landmark Global: Parcel delivery to the US](https://landmarkglobal.com/eu/en/destinations/parcel-delivery-to-united-states/) – DHL E-Com, UPS, SurePost e UPS Ground; padrão de 3 a 6 dias
- [Landmark Global: Parcel delivery to the UK](https://landmarkglobal.com/eu/en/destinations/parcel-delivery-to-uk/) – "multiple logistics partners", sem citar nomes
- [Landmark Global: EU General Terms and Conditions 2026](https://landmarkglobal.com/uploads/no-index-files/2026-GTC-_-LG-EN-EU.pdf) – 30 dias corridos para reclamação da loja
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – status, leituras da Evri, parceiros vistos em encomendas, parceiro de entrega, devoluções, horários das leituras, o que o Peek lê e exibe
:::
