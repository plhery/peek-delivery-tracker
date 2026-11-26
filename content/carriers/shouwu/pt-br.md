---
title: Rastreamento Shouwu: códigos SWECN…YQ, status e entrega
description: Rastreie encomendas Shouwu International (守务国际) da China: formato SWECN…YQ, tradução dos status, quem entrega nos EUA e na Europa e prazos.
slug: rastreamento-shouwu
published: 2026-11-26
updated: 2026-11-26
---

A Shouwu International (守务国际, também SWE) é uma empresa de logística de Xangai que transporta encomendas para vendedores online na China até a Europa e os Estados Unidos, onde um correio ou transportadora local faz a entrega. Se uma loja enviou seu pedido pela Shouwu, o código começa com SWECN e termina com YQ: cole-o na caixa acima ou no rastreador da Shouwu em [admin.shshouwu.com](https://admin.shshouwu.com/track.html). A loja fornece o código junto aos detalhes de envio do pedido.

## Código de rastreamento Shouwu: como ele é

Os conhecimentos da Shouwu têm SWECN, doze dígitos e YQ, totalizando 19 caracteres. Com dígitos inventados:

| Código | O que é |
| --- | --- |
| `SWECN482910573649YQ` | SWECN, doze dígitos e YQ: conhecimento da Shouwu. |
| `SWECN48291057364YQ` | Apenas onze dígitos: não segue o formato da Shouwu. Confira se falta um dígito. |
| `JSEAA4829105736YQ` | Também termina em YQ, mas começa com JSE: código da [JS Express](carrier:js-express). |

Outras agentes chinesas também terminam seus códigos em YQ, com letras próprias no início e, no catálogo do Peek, geralmente dez dígitos. SWECN e doze dígitos identificam a Shouwu. Espaços, hifens e letras minúsculas não fazem diferença no Peek.

**Onde encontrar.** Se não estiver no e-mail de envio da loja nem na página do pedido, veja [como encontrar seu código de rastreamento](guide:find-tracking-number).

**Você pode receber o código da transportadora local.** A Shouwu permite ao vendedor escolher qual número repassar: o próprio da Shouwu, que acompanha o trajeto de ponta a ponta, ou o número da transportadora que entrega no seu país. Assim, uma encomenda Shouwu pode chegar até você com código da [Royal Mail](carrier:royal-mail), Yodel ou Evri no Reino Unido, ou da [USPS](carrier:usps) nos EUA. Cole esse código mesmo assim: o Peek acompanha mais de 4.500 transportadoras.

**Link de rastreamento.** O botão "Copy Link" da Shouwu gera um link para seu rastreador com `?q=` e o código. Ele abre a encomenda, e você pode colar o link inteiro no Peek. O rastreador aceita até 40 códigos por vez, um por linha.

## Status de rastreamento da Shouwu explicados

O rastreador da Shouwu está em inglês sob um cabeçalho em chinês. Ele mostra um status e o país de destino de cada encomenda, seguidos de horário e texto de cada movimentação, mas sem localidade.

| Status da Shouwu | O que significa para você | O Peek exibe |
| --- | --- | --- |
| `Not Found` | A Shouwu ainda não tem dados para esse código: "We seem can't identify the number." | "Aguardando Shouwu International" |
| `Preparing` | A Shouwu descreve como pacote entregue ao seu armazém na China ou em pesagem e lacre. Pode surgir antes da primeira leitura. | "Etiqueta criada", depois "Recebida pela transportadora" após coleta pelo parceiro ou leitura no armazém |
| `In Transit` | Qualquer etapa desde o repasse a um transportador até a alfândega e o trajeto no seu país. | "Em trânsito", ou a etapa do registro mais recente, como "Na alfândega"; "Etiqueta criada" enquanto há apenas dados do pedido e `Carrier received the shipment` |
| `Undelivered` | Entrega não realizada. | "Tentativa de entrega", ou "Pronta para retirada" ou "Devolvida ao remetente" conforme o último evento |
| `Delivered` | Entregue ao destinatário. | "Entregue" |

A Shouwu chama esses status de "auto-judged by the system" e "only for information reference use". As linhas de movimentação dizem mais:

| A Shouwu mostra | O que significa para você | O Peek exibe |
| --- | --- | --- |
| `Shipment information received`, `Order information received. We're expecting your parcel to arrive with us.` | O vendedor registrou o envio. A Shouwu ainda não está com o pacote. | "Etiqueta criada" |
| `Carrier received the shipment` | A Shouwu grava essa linha duas horas após os dados do pedido, dias antes da primeira leitura no armazém: ninguém recebeu o pacote ainda. | "Etiqueta criada" |
| `Picked up by Shipping Partner` | O parceiro logístico da Shouwu coletou o pacote com o vendedor: primeira leitura após os dados do pedido. | "Recebida pela transportadora" |
| `Arrival processing center` | O armazém da Shouwu na China recebeu o pacote. | "Recebida pela transportadora" |
| `Package processing completed` | Processamento concluído no armazém, antes do voo. | "Recebida pela transportadora" |
| `Shipment started the customs clearance process at origin` | Desembaraço de exportação iniciado na China, passo rotineiro antes do voo. A alfândega não reteve o pacote. | "Em trânsito" |
| `The package is shipped from the transfer warehouse to the airport Export customs clearance in progress` | Enviado do armazém de transferência ao aeroporto, onde ocorre a exportação chinesa: não é a alfândega do seu país. | "Em trânsito" |
| `Export customs declaration completed`, `Export customs clearance completed`, `Export Released` | Exportação liberada pela China; nada retido. | "Em trânsito" |
| `The parcel has arrived at the airport and is currently being loaded onto a flight` | No aeroporto de saída após a exportação, sendo carregado no voo. | "Em trânsito" |
| `Arrived at Shanghai Pudong Airport`, `Departed from export airport` | No aeroporto de Xangai, depois em voo para fora da China. | "Em trânsito" |
| `The ship has already set sail` | Em rota marítima: o navio zarpou da China após a exportação e antes de chegar ao porto de destino. | "Em trânsito" |
| `Arrived at airport of Destination`, `Shipment arrived in the destination country` | Pousou no seu país. | "Em trânsito" |
| `NOA received` | O agente no seu país recebeu o aviso de chegada da companhia aérea; a alfândega vem a seguir. | "Em trânsito" |
| `Shipment started the customs clearance process at destination`, `Customs clearance in progress`, `Currently undergoing customs clearance in the destination country`, `Currently processing import customs clearance`, `Customs clearance for imports` | Em desembaraço na alfândega do seu país. | "Na alfândega" |
| `Custom clearance completed`, `Shipment completed the customs clearance process at destination`, `Customs clearance has been completed`, `Customs clearance in the destination country has been completed` | Liberado pela alfândega do seu país. | "Em trânsito" |
| `Scheduled For Delivery` | Linha da Shouwu após a importação, dias antes do depósito e da rota local: entrega planejada, ainda não em rota. | "Em trânsito" |
| `Delivery to local courier`, `The goods have been deliverey to the final delivery service provider`, `The goods have been delivered to the carrier` | Entregue à transportadora que fará a entrega no seu país, não a você ("deliverey" é a grafia da Shouwu). | "Em trânsito" |
| `Your consignment has been picked up` | A transportadora local recolheu o pacote no depósito da Shouwu após a alfândega: não é a coleta no vendedor (`Picked up by Shipping Partner`) nem retirada por você. | "Em trânsito" |
| `Picked up` | Nos EUA, a FedEx recolheu o pacote no depósito da Shouwu após a importação (a linha traz cidade e código postal). Não é a coleta no vendedor nem retirada por você. | "Em trânsito" |
| `Arrived at FedEx location`, `Departed FedEx location` | Leituras próprias da FedEx em unidades nos EUA após recolher no depósito da Shouwu. | "Em trânsito" |
| `The shipment has been processed in the destination parcel center` | A DHL triou o pacote no centro de encomendas que atende seu endereço, antes da rota. | "Em trânsito" |
| `Mis routed` | A transportadora na Alemanha enviou o pacote à unidade errada e está redirecionando: um atraso, não uma parada. A rota de entrega ainda pode ocorrer no mesmo dia. | "Em trânsito" |
| `Out for delivery` | Em rota de entrega da transportadora local. | "Saiu para entrega" |
| `Abnormal delivery` | A rota não entregou o pacote. O motivo da transportadora aparece em seguida em uma linha própria entre sinais de menor/maior, como `<Incorrect address.>`. | "Tentativa de entrega" |
| `The recipient is unknown` | A rota da DHL na Alemanha não encontrou esse destinatário no endereço; o pacote retorna. | "Tentativa de entrega" |
| `Shipment will be returned to origin-Bad address` | A DHL está devolvendo o pacote por não localizar o endereço: a caminho de volta, ainda não no remetente. Fale com o vendedor. | "Precisa de atenção" |
| `Delivered by post man`, `This parcel has been delivered` | Os correios ou a transportadora local entregaram o pacote. | "Entregue", quando o status da Shouwu mostra `Delivered` |
| `Shipment sent directly from parcel center to business customer` | A DHL entregou diretamente do centro de encomendas a um endereço comercial; se houve devolução, a mesma linha significa retorno concluído ao remetente. | "Entregue", ou "Devolvida ao remetente" após devolução |

Quando a iMile entrega na Itália, o histórico da Shouwu repassa as linhas da iMile com o centro entre colchetes 【】 (o seu pode indicar outro centro além de Milan center ou ROM 10):

| A Shouwu mostra | O que significa para você | O Peek exibe |
| --- | --- | --- |
| `【Milan center】Received.` | O centro da iMile recebeu o pacote. | "Em trânsito" |
| `【Milan center】Parcel shipped to【ROM 10】.`, `【ROM 10】Arrived.` | Enviado à estação de entrega da iMile e recebido lá. | "Em trânsito" |
| `【ROM 10】Shipment out for delivery.` | Em rota de entrega da iMile. | "Saiu para entrega" |
| `<User not at home>`, `【ROM 10】 Back To DS.` | O entregador não encontrou ninguém e o pacote voltou à estação. | "Tentativa de entrega" |
| `【ROM 10】Parcel shipped to【Milan Transit Center】.` | Após duas rotas sem entrega, a estação envia o pacote de volta ao centro da iMile. | "Em trânsito" |
| `Back to Sender for reason: wrong recipient address or address related problem` | Após as rotas sem entrega, a iMile inicia a devolução por problema de endereço (outro motivo pode aparecer após os dois-pontos): voltando, ainda não no remetente. Fale com o vendedor. | "Precisa de atenção" |
| `【Milan Transit Center】Order Closed.` | A iMile encerra a ordem sem entregar; fale com o vendedor. | "Precisa de atenção" |

**Quando a DPD entrega.** Em rotas entregues pela [DPD](carrier:dpd-de), o histórico continua nas palavras da DPD, terminando com ponto final: `In transit.`, depois `At parcel delivery centre.` (depósito entregador da DPD, antes da rota e novamente após uma entrega não realizada — "Em trânsito") e `Out for delivery.` ("Saiu para entrega"). `Unfortunately we have not been able to deliver your parcel. Consignee address not correct.` indica uma rota sem entrega por endereço errado: o pacote volta ao depósito da DPD, não ao remetente ("Tentativa de entrega").

**Linhas em chinês.** Alguns pacotes trazem linhas escritas pela Shouwu em chinês, que o Peek mantém como escritas. `货物电子信息已经收到` (dados eletrônicos recebidos) acompanha os dados do pedido: "Etiqueta criada". Em seguida, todas em "Em trânsito": `申报过港中` após um código de lote (lote declarado para travessia terrestre a Hong Kong), `已到港，安排航班中` (no porto de saída, aguardando voo), `预计23号的航班` (voo reservado, aqui para o dia 23; com `二程` na frente para o segundo trecho), `航班已飞` e `二程航班已飞` (voo ou segundo trecho decolou) e `已落地` (pousou). `已落地，清关提货中` (pousou, em desembaraço e coleta na companhia aérea) aparece como "Na alfândega"; `清关完成` (desembaraço concluído) e `已交仓扫描` (escaneado no depósito da entregadora) voltam a "Em trânsito".

**Horários deslocados em horas.** O sistema da Shouwu grava a hora local de cada leitura como se fosse horário de Pequim — inclusive no exterior — e seu rastreador converte isso para o seu fuso. Assim, uma leitura no Reino Unido ou nos EUA aparece horas adiantada, fazendo o voo parecer horas mais curto. Guie-se pela ordem das linhas. O Peek calcula o fuso de cada leitura pela localidade ou trecho e mostra tudo no fuso horário do seu aparelho; em peektracker.com e nos aplicativos iPhone e Android, o histórico termina com "Horários no seu fuso horário:" e o nome dele.

## Quem entrega encomendas da Shouwu

A Shouwu afirma trabalhar diretamente com USPS, Royal Mail, La Poste, DHL, DPD e Hermes. Suas páginas de rotas em chinês informam as transportadoras por país:

- **Reino Unido.** As rotas cobrem todo o país, com entrega final por Royal Mail, Yodel e Evri (grafada ERVI no site); a linha para itens com bateria usa principalmente a Royal Mail.
- **Estados Unidos.** Cobrem todo o país, entregues principalmente pela USPS; em históricos lidos pelo Peek, a [FedEx](carrier:fedex) também realiza entregas lá.
- **União Europeia.** A Shouwu mantém rotas para a Alemanha, entregues principalmente por [DHL](carrier:dhl), [Hermes](carrier:hermes-de) ou [DPD](carrier:dpd-de) conforme a linha; França (principalmente [Colissimo](carrier:la-poste)); Países Baixos (principalmente DHL); Suécia (principalmente Early Bird); e Itália (linha econômica de 10 a 12 dias úteis entregue por UniUni e [Poste Italiane](carrier:poste-italiane); em históricos lidos pelo Peek, a [iMile](carrier:imile) também entrega lá com as linhas acima). Aqui também o vendedor pode passar o código da transportadora local.
- **Irlanda, Canadá e Austrália.** A Shouwu não lista rotas nem transportadoras para esses países. Pergunte ao vendedor quem entrega.

O rastreador da Shouwu não exibe o nome nem o código da transportadora final além do que eventualmente apareça no texto de uma movimentação.

**Entregas não realizadas.** Tentativas, avisos e pontos de retirada seguem as regras da transportadora local. Sob `Undelivered`, o rastreador orienta contatar a transportadora para reagendar ou retirar o pacote logo, antes do prazo dela, ou o pacote "might be returned to the sender". Os termos em chinês das linhas para Reino Unido e EUA dizem que encomendas não entregáveis não retornam à China e são destruídas por padrão. Em todo caso, responda a um aviso rapidamente. Veja [entrega não realizada](guide:missed-delivery) e [encomenda não retirada](guide:uncollected-parcel).

## Encomenda Shouwu que não chega ou rastreamento parado

**`Not Found`.** A Shouwu cita quatro motivos: ainda não recebeu o pacote, ainda não o escaneou, o código está errado ou o envio é tão antigo que o rastreamento expirou. Ela diz que o processamento costuma levar alguns dias. O Peek exibe "Aguardando Shouwu International" e continua verificando. Se dois dias depois a Shouwu ainda não reconhecer o código e nenhum outro serviço responder, o Peek exibe: "Depois de dois dias, a transportadora ainda não reconhece este código. Confira o código, escolha outra transportadora ou exclua a encomenda." Confira caractere por caractere; se nada surgir após alguns dias, fale com o vendedor.

**Parada em `Preparing`, `Shipment information received` ou `Carrier received the shipment`.** A Shouwu ainda não está com o pacote ou não o despachou do armazém. Pergunte ao vendedor quando foi entregue.

**Parada na alfândega.** Após `Shipment started the customs clearance process at destination`, `Customs clearance in progress`, `Currently undergoing customs clearance in the destination country`, `Currently processing import customs clearance`, `Customs clearance for imports` ou `已落地，清关提货中`, aguarda liberação no seu país. Nas rotas da UE, compras online de fora da UE pagam direito de €3 por tipo de produto em pacotes de até €150 (desde 1º de julho de 2026) e a taxa de manuseio da UE de €2 por tipo de produto em qualquer valor (desde 1º de novembro de 2026); as páginas da Shouwu não dizem quem paga. Se cobrarem taxas de importação, veja [encomendas retidas na alfândega](guide:customs).

**Nada após `Delivery to local courier`.** A transportadora local já recebeu o pacote, mas o rastreador da Shouwu não exibe o código dela. Peça esse código ao vendedor e cole-o no Peek. Veja mais em [por que o rastreamento para de atualizar](guide:tracking-not-updating) e [rastrear encomendas da China](guide:tracking-from-china).

**Entregue, mas nada chegou.** Os termos da Shouwu dizem que, quando o site da transportadora local mostra entrega não recebida, a Shouwu ajuda a apurar mas não paga indenização. Procure primeiro a transportadora local e depois o vendedor. Nos EUA, a USPS aceita busca de Missing Mail do remetente ou do destinatário a partir de 7 dias da postagem. Veja [encomenda entregue mas não recebida](guide:delivered-not-received).

**Extravio ou dano.** O cliente da Shouwu é o vendedor, que pelos termos precisa reclamar em até 30 dias do despacho pela Shouwu. Para pacotes sem registro de entrega após entrarem no armazém da Shouwu, ela paga o valor declarado mais o frete até o teto de 200 yuans por envio, e nada por dano em trânsito. Peça reembolso ou reenvio à loja ou ao marketplace sem demora. Veja [encomendas extraviadas](guide:lost-parcel).

## Contato e atendimento da Shouwu

O site da Shouwu, www.sedel.com, está em chinês e voltado a vendedores que contratam envios. Ele publica celulares chineses da matriz em Xangai e de cinco filiais, um link de chat no QQ e códigos WeChat. Não há e-mail, formulário de contato, atendimento em inglês nem canal para destinatários. O rastreador também não informa contatos.

Sobre o seu pedido, procure o vendedor, que contratou a Shouwu e abre reclamações. Sobre a entrega em si, fale com a transportadora local no seu país.

## Rastrear uma encomenda Shouwu com o Peek

O [Peek](/) reconhece um código Shouwu pelo formato: SWECN, doze dígitos e YQ — exclusivo no catálogo — e consulta o próprio rastreador da Shouwu sem exigir conta, código postal nem telefone.

O Peek define a etapa a partir do status da Shouwu, conforme as tabelas acima, e exibe o país de destino e cada movimentação com sua localidade, que a página da Shouwu omite. Localidades com dígitos, como códigos postais, são removidas. Como a Shouwu não informa previsão de entrega, o Peek não mostra data prevista.

Sem uma transportadora final nomeada, o Peek não mostra linha "Entrega por" nem transfere a encomenda para a entregadora local, mesmo quando o histórico repassa linhas da iMile. Se você tiver o código da transportadora local, adicione-o ao Peek como encomenda separada.

Enquanto você mantém a encomenda aberta, ou no dia da entrega com alertas ligados, o Peek verifica até a cada 10 minutos — e até a cada 2 minutos a partir das 6h locais no dia em que sai para entrega — enquanto o próprio rastreador da Shouwu responde; nos demais horários, a cada hora. Por serviços de rastreamento, consulta a cada hora, a cada 3 horas após um dia sem novidades e até a cada 15 minutos no dia da entrega sob a mesma condição. Após dois dias sem novidades (12 horas enquanto ainda mostrar "Etiqueta criada" ou "Recebida pela transportadora"), verifica a cada hora. Ele pode notificar cada movimentação, apenas etapas importantes (entrega, retirada, alfândega, entregas não realizadas e devoluções) ou "Só a entrega". Não pode contatar a Shouwu nem o vendedor por você.

## Perguntas frequentes sobre o rastreamento Shouwu

### O que é a Shouwu International (守务国际)?

É uma empresa de logística de Xangai, 上海守务国际物流有限公司, que transporta encomendas para vendedores online internacionais. Ela informa ter começado em 2018 como agente de carga aérea e migrado em 2019 para linhas de comércio eletrônico rumo à Europa e aos EUA.

### Onde posso rastrear uma encomenda Shouwu?

Na caixa acima ou no rastreador em inglês da Shouwu em admin.shshouwu.com, usando o código completo SWECN…YQ.

### Quem entrega encomendas da Shouwu no Reino Unido e nos EUA?

Os correios ou uma transportadora local: as páginas de linhas da Shouwu citam Royal Mail, Yodel e Evri no Reino Unido e principalmente USPS nos EUA. Para Irlanda, Canadá e Austrália ela não cita nenhuma, então pergunte ao vendedor.

### Quanto tempo a Shouwu leva para entregar?

Depende da linha escolhida pelo vendedor. Os prazos de referência da Shouwu são 5 a 8 dias úteis na linha padrão do Reino Unido, 10 a 12 na econômica do Reino Unido, 6 a 8 na padrão dos EUA e 8 a 12 na econômica dos EUA. Na UE, 4 a 7 dias úteis para Alemanha e França, 4 a 8 para Países Baixos e Suécia, e 10 a 12 para a Itália.

### Quem devo procurar se uma encomenda Shouwu desaparecer?

O vendedor onde você comprou: ele contratou a Shouwu e tem 30 dias após o envio para abrir reclamação. A Shouwu não publica contato para destinatários.

:::sources
- [Shouwu International: 守务国际包裹追踪 (parcel tracking)](https://admin.shshouwu.com/track.html) – rastreador em inglês, 40 códigos, status e explicações, eventos sem localidades, "Copy Link" e "Not Found"
- [Shouwu International: 首页 (home page)](http://www.sedel.com/) – lista de contatos, chat QQ e razão social
- [Shouwu International: 关于我们 (about us)](http://www.sedel.com/about-us.html) – 2018 e 2019 e parceiros diretos
- [Shouwu International: 主营线路 (main lines)](http://www.sedel.com/main-route.html) – rotas por país
- [Shouwu International: 英国标快 (UK standard express)](http://www.sedel.com/main-route-secondary.html?groupId=1&groupItemId=2) – Royal Mail, Yodel, ERVI, escolha do código, 5 a 8 dias úteis, sem retorno à China e reclamações
- [Shouwu International: 英国经济 (UK economy)](http://www.sedel.com/main-route-secondary.html?groupId=2&groupItemId=3) – 10 a 12 dias úteis
- [Shouwu International: 英国带电 (UK, batteries)](http://www.sedel.com/main-route-secondary.html?groupId=3&groupItemId=4) – principalmente Royal Mail
- [Shouwu International: 美国标快 (US standard express)](http://www.sedel.com/main-route-secondary.html?groupId=1&groupItemId=1) – principalmente USPS, 6 a 8 dias úteis, sem retorno à China e reclamações
- [Shouwu International: 美国经济 (US economy)](http://www.sedel.com/main-route-secondary.html?groupId=2&groupItemId=21) – 8 a 12 dias úteis
- [Shouwu International: 德国标快 (Germany standard express)](http://www.sedel.com/main-route-secondary.html?groupId=1&groupItemId=19) – principalmente DHL, escolha do código, 4 a 7 dias úteis e reclamações
- [Shouwu International: 德国经济 (Germany economy)](http://www.sedel.com/main-route-secondary.html?groupId=2&groupItemId=22) – principalmente Hermes, 4 a 7 dias úteis
- [Shouwu International: 德国带电 (Germany, batteries)](http://www.sedel.com/main-route-secondary.html?groupId=3&groupItemId=25) – principalmente DPD, 4 a 7 dias úteis
- [Shouwu International: 法国标快 (France standard express)](http://www.sedel.com/main-route-secondary.html?groupId=1&groupItemId=20) – principalmente Colissimo, 4 a 7 dias úteis
- [Shouwu International: 荷兰标快 (Netherlands standard express)](http://www.sedel.com/main-route-secondary.html?groupId=4&groupItemId=28) – principalmente DHL, 4 a 8 dias úteis
- [Shouwu International: 瑞典经济 (Sweden economy)](http://www.sedel.com/main-route-secondary.html?groupId=4&groupItemId=29) – principalmente EarlyBird, 4 a 8 dias úteis
- [Shouwu International: 意大利经济 (Italy economy)](http://www.sedel.com/main-route-secondary.html?groupId=4&groupItemId=27) – UniUni e Poste Italiane, escolha do código e 10 a 12 dias úteis
- [European Commission: EU Customs Reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – direito de €3 desde 1º de julho de 2026 e taxa de manuseio de €2 desde 1º de novembro de 2026
- [USPS: Missing Mail and Lost Packages](https://www.usps.com/help/missing-mail.htm) – remetentes ou destinatários, a partir de 7 dias
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formato do código, rastreador e link, status, textos e etapas, linhas da iMile repassadas na Itália, linhas da DPD, fusos horários, localidades e o que o Peek mostra
:::
