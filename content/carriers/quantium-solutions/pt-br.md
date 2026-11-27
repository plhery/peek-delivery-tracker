---
title: Rastreamento Quantium Solutions: códigos, status e ajuda
description: Rastreie uma encomenda Quantium Solutions: formatos aceitos, significado de cada status, quem entrega na Ásia e Austrália, alfândega e suporte.
slug: quantium-solutions-tracking
published: 2026-11-27
updated: 2026-11-27
---

A Quantium Solutions é uma empresa de logística para e-commerce presente em 10 países da região Ásia-Pacífico, transportando pedidos on-line para dentro, dentro e para fora da região. Rastreie uma encomenda na página [Track Your Shipments](https://track.quantiumworldwide.com/) da Quantium ou cole o código na caixa acima. A loja fornece o código no e-mail de envio ou na página do pedido.

## Código de rastreamento Quantium Solutions: como identificar

O rastreador da Quantium solicita um "Tracking/Reference number" e aceita vários de uma vez, um por linha. O código do Peek conhece dois formatos da Quantium, nenhum deles exclusivo dela. Exemplos com dígitos fictícios:

| Código (fictício) | O que é |
| --- | --- |
| `4829173654` | Dez dígitos: no código do Peek, número da Quantium para envios na Malásia. Como várias transportadoras (incluindo DHL) usam 10 dígitos, o Peek não identifica Quantium só pelo formato. |
| `UQ482917367TH` | `UQ`, nove dígitos e `TH`: no código do Peek, envio da Quantium na Tailândia. Como segue o padrão postal internacional, o Peek identifica Thailand Post. |
| `RA482917367SG` | Duas letras, nove dígitos e `SG`: código da [Singapore Post](carrier:singapore-post). O código do Peek observa que a Quantium transporta alguns volumes com códigos da SingPost; o Peek identifica Singapore Post. |

A consulta direta da Quantium no Peek aceita apenas esses dois formatos. Espaços e letras minúsculas não importam. Para referências em outros formatos, use o rastreador da Quantium. Não encontrou o código? Veja [como encontrar um código de rastreamento](guide:find-tracking-number), em inglês, e [formatos de código de rastreamento](guide:tracking-number-formats), em inglês.

## Significado dos status da Quantium Solutions

Cada linha no rastreador da Quantium traz um marco (milestone) e frequentemente uma observação (remark) com o texto do parceiro que manuseia o volume. O Peek mostra o marco seguido da observação após dois-pontos quando ela acrescenta detalhes. As linhas que o Peek conhece, na ordem em que o pacote passa por elas:

| A Quantium mostra | O que significa para você | O Peek mostra |
| --- | --- | --- |
| `Shipment created` | A loja reservou o envio; a Quantium ainda não tem o pacote. | "Etiqueta criada" |
| `Shipment received` | A Quantium recebeu o pacote. | "Recebida pela transportadora" |
| `Shipment under processing at origin hub` | Em triagem em um hub no país de origem. | "Em trânsito" |
| `Shipment despatched to transshipment hub` | A caminho de um hub de transbordo para a próxima etapa. | "Em trânsito" |
| `DEPART ORIGIN` | Saiu do ponto de origem. | "Em trânsito" |
| `Shipment received at transshipment hub: Arrive Hub` | Chegou ao hub de transbordo. | "Em trânsito" |
| `Shipment despatched to overseas: Depart Hub` | Deixou o hub rumo ao seu país. | "Em trânsito" |
| `READY FOR DEPARTURE`, `FLIGHT DEPARTURE TO DESTINATION` | Aguardando embarque aéreo e depois em voo para o destino. | "Em trânsito" |
| `Shipment arrived at destination port: Arrive Destination` | Chegou ao seu país. | "Em trânsito" |
| `IN TRANSIT: Customs Clearance Begins` | A alfândega do seu país iniciou o desembaraço. | "Na alfândega" |
| `Shipment released from customs/ government check point: Customs Cleared` | Liberada pela alfândega. | "Em trânsito" |
| `Shipment received at delivery branch: Handed Over for Delivery` | Chegou à filial de entrega local. | "Em trânsito" |
| `IN TRANSIT` | Em deslocamento; leituras de triagem do parceiro local costumam vir assim. | "Em trânsito" |
| `Shipment out for delivery` | Saiu para entrega. | "Saiu para entrega" |
| `Shipment delivered` | Entregue. Se a observação informar quem assinou ou recebeu, o nome fica mantido na linha como a Quantium escreve. | "Entregue" |

Linhas desconhecidas aparecem como escritas pela Quantium, e o Peek deduz a etapa quando possível. Para números desconhecidos, o site da Quantium mostra "No record!". Mais em [significado dos status de rastreamento](guide:tracking-statuses), em inglês.

**Horários.** Cada linha traz data e hora sem fuso horário, no relógio de quem fez a leitura: o país do remetente nas primeiras linhas e o parceiro local nas seguintes. Por isso, linhas de países diferentes não podem ser comparadas hora a hora.

## Quem entrega encomendas da Quantium Solutions

A Quantium oferece às lojas quatro modalidades internacionais:

| Serviço | Rastreamento | Canal de entrega | Prazo estimado | Impostos e taxas |
| --- | --- | --- | --- | --- |
| Q-Simple | nenhum | postal | 4 a 17 dias | destinatário (DDU) |
| Q-Smart | rastreado | postal | 4 a 10 dias | destinatário (DDU) |
| Q-Speed | rastreado, prioritário | postal | 3 a 7 dias | destinatário (DDU) |
| Q-Sure | rastreado | comercial | 2 a 5 dias | remetente ou destinatário (DDP ou DDU) |

O Peek não informa qual modalidade foi contratada: confirme com a loja.

**Na Ásia.** Os serviços próprios de última milha da Quantium incluem entrega na porta, retirada em agências postais, armários e mais de 3.000 pontos de retirada pela Ásia, com pagamento em dinheiro ou eletrônico na entrega. Sua modalidade Regular leva de 2 a 4 dias.

**Na Austrália.** A filial australiana, Quantium Solutions (Australia) Pty Ltd, envia pacotes internamente por transportadoras como a CouriersPlease e pelos serviços eParcel e express eParcel da [Australia Post](carrier:australia-post), conforme sua página de sobretaxa de combustível; ela também lista a CouriersPlease como parceira oficial. A transportadora responsável pela entrega define as tentativas e onde um pacote não entregue aguarda retirada.

Se não houver ninguém em casa, consulte [entrega não realizada](guide:missed-delivery), em inglês, e [encomendas não retiradas](guide:uncollected-parcel), em inglês.

## Alfândega e impostos em encomendas Quantium

Nos serviços Q-Simple, Q-Smart e Q-Speed, a Quantium indica que impostos e taxas ficam a cargo do destinatário (DDU). No Q-Sure, pode ser DDP (remetente paga) ou DDU. Pergunte à loja o que vale para seu pedido. A eventual cobrança depende das leis do seu país: veja [encomendas retidas na alfândega](guide:customs), em inglês.

**Para a União Europeia.** Compras on-line vindas de fora da UE pagam um imposto aduaneiro temporário de €3 por item em pacotes de até €150 desde 1º de julho de 2026, e uma taxa de manuseio da União de €2 por item, qualquer que seja o valor, desde 1º de novembro de 2026. Considera-se item o conjunto de mercadorias com mesma classificação tarifária, descrição e, quando exigido, origem. A Quantium avisa às empresas que a taxa de €2 incide por linha de declaração aduaneira tanto em IOSS quanto em DAP, em qualquer valor, acrescida de IVA quando exigido pelo destino. Confirme com a loja se seu preço já a incluía.

## Rastreamento Quantium Solutions parado, extravio ou devolução

**Apenas `Shipment created`.** A loja reservou o envio, mas a Quantium ainda não leu o pacote. Se ficar dias assim, confirme com a loja se já foi despachado.

**Sem atualizações em trânsito.** Após quatro dias sem nova linha em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito", o Peek destaca o dia da última linha ("Sem atualizações desde …") e exibe "É comum uma encomenda ficar sem atualizações em voos longos, na alfândega ou no fim de semana. O Peek continua consultando Quantium Solutions.", com o link "Pedir ajuda a Quantium Solutions" para o formulário de suporte. A Quantium divulga avisos de atraso em sua página de notícias, como o de neblina severa na Malásia e em Singapura em 7 de outubro de 2026. O rastreador também pode mostrar o código do parceiro em "Tracking No.": consulte-o lá também. Veja [por que o rastreamento para de atualizar](guide:tracking-not-updating), em inglês.

**Entregue, mas não apareceu.** Verifique arredores da casa e vizinhos; depois fale com a loja e com a Quantium. Veja [entregue mas não recebida](guide:delivered-not-received), em inglês.

**Extraviada ou danificada.** O site da Quantium não divulga processo de indenização para destinatários. Como a loja é cliente da Quantium, peça reembolso ou reenvio à loja. Veja [encomendas extraviadas](guide:lost-parcel), em inglês, e [encomendas danificadas](guide:damaged-parcel), em inglês.

**Devolvida.** A Quantium oferece às lojas serviço de devolução à origem. Veja [devolvida ao remetente](guide:returned-to-sender), em inglês.

## Atendimento ao cliente e contato da Quantium Solutions

Canais da Quantium:

- **On-line:** [Customer Support Form](https://www.quantiumsolutions.com/contact-support/) para rastreamento, ajuda geral e feedback. Pede nome, e-mail, país de origem e número de referência (o campo Customer ID é exclusivo para clientes corporativos). A Quantium informa resposta do atendimento local até o próximo dia útil.
- **Malásia:** (603) 5569 6901, customerservice.my@quantiumworldwide.com.
- **Tailândia:** (662) 712 2999, customerservice.th@quantiumworldwide.com.
- **Outros países:** a página [Contact Us](https://www.quantiumsolutions.com/contact-us/) lista e-mails de suporte para Hong Kong, Indonésia, Taiwan e Vietnã; para Singapura informa apenas contato comercial e não lista contato australiano. Nenhum horário de atendimento é divulgado.

Para compras on-line, procure primeiro a loja que contratou a Quantium.

## Como rastrear Quantium Solutions no Peek

O [Peek](/) não reconhece um número Quantium apenas pelo formato. Cole-o mesmo assim: o Peek acompanha mais de 4.500 transportadoras. Para dez dígitos, ele consulta transportadoras de formato parecido e depois serviços de rastreamento ("Consultando serviços de rastreamento…"). Para números no padrão postal terminados em `SG` ou `TH` com dígito verificador válido, ele seleciona Singapore Post ou Thailand Post ("Transportadora detectada"). Para acompanhar direto as linhas da Quantium, toque em "Trocar" e escolha Quantium Solutions; em encomendas já adicionadas, use "Trocar transportadora". Se escolher Quantium para outro formato, o Peek diz que ela "não emite códigos como este".

Um link do rastreador da Quantium com `trackno=` também funciona: o Peek extrai o número e escolhe Quantium Solutions na hora.

O Peek mostra a etapa, cada linha nas palavras da Quantium conforme a tabela acima e o país de destino, omitindo referência do cliente, código do parceiro e país de origem exibidos no site. Como a Quantium não fornece local por linha, data prevista, peso ou comprovante de entrega, o Peek não mostra nenhum deles. "Abrir o site de Quantium Solutions" abre o número no rastreador da Quantium.

Como os horários não trazem fuso, o Peek os interpreta no relógio do destino para envios a Singapura, Malásia, Tailândia ou Austrália (horário de Sydney); para outros países, usa o horário de Singapura até a linha de chegada (`Shipment arrived at destination port: Arrive Destination`) e depois o fuso do país de destino (um fuso por país, como Nova York para os EUA), convertendo tudo para o fuso do seu dispositivo — o que pode gerar diferença de algumas horas.

Quando o número segue o formato postal válido e a Quantium indica o país de destino, o Peek consulta também o correio local com o mesmo número — como [Australia Post](carrier:australia-post), [USPS](carrier:usps), [Royal Mail](carrier:royal-mail), An Post, [Canada Post](carrier:canada-post) ou [NZ Post](carrier:nz-post) — e passa a segui-lo assim que houver leituras mais novas, mantendo o histórico da Quantium. Isso não vale para todos os países (não ocorre para Singapura, Malásia, Tailândia, Hong Kong ou Vietnã, por exemplo) nem para códigos de dez dígitos.

Com a encomenda aberta no Peek, ou no dia da entrega com notificações ativas, o Peek consulta a Quantium diretamente a cada 10 minutos no máximo, e a cada 2 minutos a partir das 6h (horário de Singapura) no dia em que sai para entrega, enquanto o site dela responder; fora disso, e após dois dias sem novidade (12 horas em "Etiqueta criada" ou "Recebida pela transportadora"), consulta de hora em hora, e a cada 6 horas após sete dias em "Etiqueta criada". O Peek pode notificar você em "Cada movimentação", "Etapas importantes" ou "Só a entrega". O Peek não pode contatar a Quantium nem a loja por você.

## Perguntas frequentes sobre o rastreamento Quantium Solutions

### Quantium Solutions é a mesma coisa que Quantium Worldwide?

Sim: o rastreador e os e-mails de atendimento ficam no domínio quantiumworldwide.com, e o Peek também a reconhece como Quantium ou Quantium Solutions International.

### Por que o Peek mostra Singapore Post ou Thailand Post para meu código Quantium?

Porque seu código usa o formato postal internacional, que aponta para um correio nacional (`SG` para Singapore Post, `TH` para Thailand Post). Para ver as linhas próprias da Quantium, toque em "Trocar" e escolha Quantium Solutions.

### O que significa Customs Clearance Begins?

Significa que a alfândega do seu país iniciou o desembaraço da encomenda, e o Peek exibe "Na alfândega". Quando liberada, aparece `Shipment released from customs/ government check point: Customs Cleared`. Em envios DDU, eventuais tributos são pagos pelo destinatário: veja [encomendas retidas na alfândega](guide:customs), em inglês.

### Quanto tempo a Quantium Solutions leva para entregar?

A Quantium informa de 2 a 5 dias para Q-Sure e até 17 dias para Q-Simple (tabela acima). Dentro da Ásia, o envio Regular leva de 2 a 4 dias.

### Posso rastrear uma encomenda Q-Simple?

Não durante o trajeto. A Quantium vende o Q-Simple como modalidade econômica sem rastreamento; pergunte à loja quando o pacote foi despachado.

:::sources
- [Quantium Solutions: Track Your Shipments](https://track.quantiumworldwide.com/) – the tracker, its "Tracking No." column, "No record!"
- [Quantium Solutions: Home](https://www.quantiumsolutions.com/) – presence in 10 countries in Asia Pacific
- [Quantium Solutions: About Us](https://www.quantiumsolutions.com/about-us/) – what Quantium does, CouriersPlease among its partners
- [Quantium Solutions: Cross Border Shipping](https://www.quantiumsolutions.com/cross-border-shipping/) – the four services, return to origin
- [Quantium Solutions: Last Mile Delivery](https://www.quantiumsolutions.com/last-mile-delivery/) – delivery options in Asia
- [Quantium Solutions: Australia Fuel Surcharges](https://www.quantiumsolutions.com/australia-fuel-surcharges/) – CouriersPlease, Australia Post eParcel
- [Quantium Solutions: Service Impact Notice (haze)](https://www.quantiumsolutions.com/news/13/) – haze delays
- [Quantium Solutions: Service Update (EU customs handling fee)](https://www.quantiumsolutions.com/news/14/) – €2 fee per declaration line
- [Quantium Solutions: Customer Support Form](https://www.quantiumsolutions.com/contact-support/) – what the form asks, reply time
- [Quantium Solutions: Contact Us](https://www.quantiumsolutions.com/contact-us/) – phones and mailboxes
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – €3 duty, €2 handling fee
- [European Commission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – what an item is
- [Universal Parcel Scraper: Quantium Solutions](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/quantium-solutions) – number shapes, links, wordings, clocks, fields
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – detection, the clock Quantium's times are read on, hand-off to the destination post
:::
