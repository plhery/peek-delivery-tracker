---
title: Rastreamento TopYou: códigos TYZR, status e entrega
description: Rastreie encomendas TopYou (通邮) vindas da China: formato do código TYZR…YQ, significado dos status em inglês e quem assume a entrega no seu país.
slug: rastreamento-topyou
published: 2026-11-24
updated: 2026-11-24
---

A TopYou (通邮, Tongyou, também chamada 通邮集团 ou SZTY56) é a 深圳市通邮物流科技（集团）股份有限公司, uma transportadora internacional sediada em Shenzhen, China, que opera linhas logísticas internacionais. Se uma loja online chinesa enviou seu pedido pela TopYou, ela fornece um código que começa com `TYZ` e termina com `YQ`: cole o código na caixa acima. O histórico da TopYou acompanha a encomenda desde o armazém na China, passando pelo voo e pela alfândega e, em pacotes para os Estados Unidos, até a entrega pelo USPS.

## Código de rastreamento da TopYou: qual é o formato

Os códigos da TopYou seguem o formato `TYZR`, uma letra, dez dígitos e `YQ`, somando 17 caracteres; alguns trazem outra letra no lugar do `R`. Com dígitos fictícios:

| Código | O que é |
| --- | --- |
| `TYZRK4829173605YQ` | `TYZR`, uma letra, dez dígitos e `YQ`: código de rastreamento da TopYou. |
| `TYZRK482917360YQ` | Apenas nove dígitos: fora do padrão da TopYou. Verifique se falta um dígito. |
| `JXCKM4829173605YQ` | Também termina em `YQ`, como códigos de outras transportadoras chinesas, mas começa com `JXC`: código da Juxi Express. |

Espaços e letras minúsculas não importam ao colar o código no Peek. O rastreador próprio da TopYou, [track.szty56.com](https://track.szty56.com/), diferencia maiúsculas de minúsculas, portanto digite o código em maiúsculas lá. Se não localizar nos detalhes do pedido, veja [como encontrar seu código de rastreamento](guide:find-tracking-number).

**Um segundo código.** O registro da TopYou também pode trazer o código próprio da transportadora local, exibido no rastreador da TopYou.

**Copie o código, não o link.** O Peek não lê links do rastreador da TopYou: cole apenas o número.

## Status de rastreamento da TopYou explicados

A TopYou publica suas leituras em inglês. Na ordem em que uma encomenda costuma recebê-las:

| A TopYou diz | O que significa para você | O Peek mostra |
| --- | --- | --- |
| `Parcel Data Received` | A loja enviou os dados da encomenda à TopYou, mas a TopYou ainda não está com o pacote. | "Etiqueta criada" |
| `Arrived at the first mile sorting center` | O armazém da TopYou na China recebeu o pacote: primeira leitura nas mãos da TopYou. | "Recebida pela transportadora" |
| `Departed from the first mile sorting center` | Saiu desse armazém. | "Em trânsito" |
| `Arrived at airport of the original country` | No aeroporto de origem, antes do embarque. | "Em trânsito" |
| `Departed from Airport to destination country` | O voo para o seu país decolou. | "Em trânsito" |
| `Arrived at destination airport` | Pousou no país de destino. | "Em trânsito" |
| `Departed from customs of` | A alfândega do seu país liberou o pacote. A TopYou deixa em branco o país após "of", e o Peek remove o "of". | "Em trânsito" |
| `At U.S. Postal Service facility, Accepted by U.S. Postal Service` | Nos EUA: o USPS assumiu a encomenda após o voo. Não se trata de uma nova postagem. | "Em trânsito" |

**Etiquetas mais recentes.** Em códigos mais novos, a TopYou usa textos diferentes para algumas etapas equivalentes:

| A TopYou diz | O que significa para você | O Peek mostra |
| --- | --- | --- |
| `Carrier received the shipment` | A TopYou recebeu o pacote na China: primeira leitura. | "Recebida pela transportadora" |
| `Shipment arrived at a carrier facility` | Avançando a partir do primeiro armazém da TopYou. O pacote não está sendo aceito novamente. | "Em trânsito" |
| `Shipment started the customs clearance process at origin` | Desembaraço de exportação na China, etapa rotineira antes do voo. Não está retido nem se trata da alfândega do seu país. | "Em trânsito" |
| `Shipment arrived in the destination country` | Chegou ao seu país. | "Em trânsito" |
| `Shipment started the customs clearance process at destination` | Está com a alfândega de importação do seu país. | "Na alfândega" |
| `Shipment completed the customs clearance process at destination` | Liberado pela alfândega e a caminho da transportadora local. | "Em trânsito" |
| `Shipment is out for delivery` | Ainda não saiu para entrega, apesar do texto: na França e na Itália, marca o repasse à GOFO no primeiro centro de triagem, dias antes de o motorista sair em rota. | "Em trânsito" |

**Rotas entregues pelos correios.** Em linhas entregues pelos correios do país de destino, a TopYou utiliza outro conjunto de frases até a alfândega do destino:

| A TopYou diz | O que significa para você | O Peek mostra |
| --- | --- | --- |
| `Accepted by carrier` | A TopYou recebeu o pacote na China: primeira leitura. | "Recebida pela transportadora" |
| `Inbound in sorting center` | Registrado no primeiro armazém da TopYou, minutos depois. Ainda não está em deslocamento. | "Recebida pela transportadora" |
| `Outbound in sorting center` | Saiu desse armazém. A TopYou acrescenta `, Lastmile Number:` seguido do código postal local; o Peek remove esse final da linha. | "Em trânsito" |
| `Received by line-haul`, `Hand over to airline` | Recebido para transporte tronco e depois entregue à companhia aérea. | "Em trânsito" |
| `Airline arrived at transit country or district.`, `Airline departed from transit or district country.` | Escala no caminho até o país de destino. | "Em trânsito" |
| `Airline arrived at destination country`, `Arrive at destination country` | Chegou ao país de destino. | "Em trânsito" |
| `Export clearance success` | Liberado pela alfândega de exportação da China. Não está retido. | "Em trânsito" |
| `Import clearance success` | Liberado pela alfândega do país de destino para os correios. Os correios ainda podem registrar eventos alfandegários próprios. | "Em trânsito" |
| `Customs Clearance Completed Successfully` | Alfândega de destino concluída, minutos após o repasse à transportadora entregadora, que já está com o pacote. | "Em trânsito" |

**Leituras da transportadora entregadora.** Após o repasse, o rastreador da TopYou pode seguir exibindo eventos da empresa local: os do USPS nos EUA, ou os da GOFO na França e na Itália, como `Arrived at sorting center`, `The package left sorting center` e `Package departed from sorting center` (chegada ou saída de centro de triagem da GOFO: "Em trânsito"), `Arrived at GOFO Regional Destination Facility` (na unidade regional da GOFO: "Em trânsito") e `The driver is out for delivery` (o entregador da GOFO está levando o pacote: "Saiu para entrega").

Na Alemanha, as leituras da iMile vêm com o nome da etapa seguido de vírgula e frase: `In Transit, Your order has been shipped from 【iMile Köln DC Center】.` (encaminhado de uma central da iMile: "Em trânsito"), `Delivery, Your order has arrived at 【Dortmund01】` (chegou à unidade entregadora da iMile: "Em trânsito"), `Delivery, Your order has assigned delivery drvier.` (motorista designado, mas ainda não saiu; o erro de grafia é da própria iMile: "Em trânsito") e `Delivery, Our delivery associate is out for delivery.` (entregador da iMile em rota: "Saiu para entrega"). Portanto, uma linha começada por `Delivery` não significa que o motorista já saiu. Uma linha da iMile que não movimenta o pacote, como conferência de estoque, mantém o status anterior. Já o aviso de ponto parceiro iniciado por `Arrived, The parcel has been delivered to 【】. Please collect your parcel promptly at 【】 using your pickup code.` significa que o pacote espera retirada com seu código de coleta: "Pronta para retirada".

Na Áustria, as leituras da DPD vêm no inglês da DPD: `In parcel delivery center` (na central entregadora da DPD: "Em trânsito"), `In delivery` (motorista da DPD em rota: "Saiu para entrega"), `Unfortunately, consignee not present. Notification was left.` (ninguém atendeu e a DPD deixou aviso: "Tentativa de entrega"), `Parcel was handed over to Pickup parcel shop` e `Takeover by Pickup parcel shop` (disponível em um DPD Pickup parcelshop: "Pronta para retirada") e `Parcel was not picked up` (não retirado a tempo, voltando ao remetente: "Precisa de atenção").

Na Espanha, o evento do parceiro local após a alfândega pode aparecer como `Parcel in transit` (a caminho de você: "Em trânsito").

## Quem entrega as encomendas da TopYou

**Estados Unidos.** O USPS assume o pacote após o voo, e o rastreador da TopYou pode continuar exibindo as leituras dele. O segundo código no registro da TopYou é o que o [USPS](carrier:usps) utiliza.

**França e Itália.** A GOFO pode fazer a entrega: [GOFO France](carrier:gofo-fr) ou GOFO Itália. O rastreador da TopYou exibe o repasse como `Shipment is out for delivery` e, em seguida, os eventos da GOFO.

**Alemanha.** A [iMile](carrier:imile) pode entregar, e o rastreador da TopYou segue com os eventos dela.

**Áustria.** A DPD Áustria pode entregar, e o rastreador da TopYou segue com as leituras da DPD.

**Em outros países.** O registro da TopYou pode mostrar o código da entregadora, mas sem citar o nome dela — e o nome da linha da TopYou em que o pacote viaja também não garante qual empresa entregará, pois uma rota pode levar o nome de outra transportadora. Em linhas entregues pelos correios, o código local também aparece no fim da linha `Outbound in sorting center`, após `Lastmile Number:`. Pergunte ao vendedor qual empresa fará a entrega.

## Pacote da TopYou que não chega ou rastreamento parado

**Parado em `Shipment started the customs clearance process at destination`.** O pacote está na alfândega do seu país. Se cobrarem encargos de importação — que na União Europeia incluem, desde 1º de novembro de 2026, a taxa de manuseio de € 2 por categoria de produto — veja [encomendas paradas na alfândega](guide:customs); se ficar retido por dias, avise o vendedor. Leia também [por que o rastreamento para de atualizar](guide:tracking-not-updating).

**Nada após `Departed from customs`, `Shipment completed the customs clearance process at destination`, `Customs Clearance Completed Successfully` ou `Import clearance success`.** A transportadora local já deve estar com a encomenda. Procure o código dela no rastreador da TopYou ou pergunte ao vendedor qual empresa entrega e sob qual número. Saiba mais em [como rastrear uma encomenda da China](guide:tracking-from-china).

**Não encontrado ou ainda sem leituras.** O rastreador da TopYou responde `查询不到` (não encontrado) para um número desconhecido e pode responder `暂无轨迹信息` (sem informações de rastreamento ainda) antes da primeira leitura. O Peek exibe "Aguardando TopYou" e segue consultando; se após dois dias a TopYou ainda não reconhecer o código e nenhuma outra fonte responder por ele, o Peek mostra "Depois de dois dias, a transportadora ainda não reconhece este código. Confira o código, escolha outra transportadora ou exclua a encomenda." O Peek identifica pela resposta da TopYou quando ela já lista a remessa mas ainda sem leituras: nesse caso, mantém "Aguardando TopYou" sem pedir para conferir o código. Se a TopYou exibir apenas `Parcel Data Received` por um dia ou mais, o Peek avisa "O remetente ainda não entregou a encomenda a TopYou. O rastreamento começa quando ele entregar." Pergunte à loja quando fará a postagem. Revise o código caractere por caractere com a mensagem do vendedor; se nada surgir após alguns dias, confirme se o pacote foi realmente enviado.

**Marcado como entregue, mas nada chegou.** Em envios nos EUA entregues pelo USPS, consulte primeiro sua agência postal e depois avise o vendedor. Veja mais em [marcado como entregue mas não recebido](guide:delivered-not-received).

**Extraviado ou devolvido.** O vendedor contratou o envio com a TopYou; peça reembolso ou reenvio à loja ou ao marketplace. Veja [encomendas extraviadas](guide:lost-parcel) e [devolvidas ao remetente](guide:returned-to-sender).

## Telefone e atendimento ao cliente da TopYou

O site da TopYou, [szty56.com](https://www.szty56.com/), informa um telefone em Shenzhen, +86 755 8181 9898 (0755-81819898 dentro da China), com horário das 9h às 18h em dias úteis, sem especificar fuso horário. A empresa não informa canal voltado a destinatários, pois seus clientes são as lojas remetentes. Fale com o vendedor que contratou a TopYou — ou com o marketplace, se o vendedor não responder.

## Como rastrear uma encomenda da TopYou com o Peek

O [Peek](/) identifica códigos da TopYou pelo formato — `TYZR`, uma letra, dez dígitos e `YQ`, exclusivo no catálogo. Com outra letra no lugar do `R`, a correspondência é fraca: o Peek mostra "Consultando serviços de rastreamento…" e pergunta ao rastreador da TopYou se ele reconhece o código. O Peek consulta o rastreador da TopYou apenas para códigos nesses dois formatos. Se você só tiver o código da transportadora local, cole esse código: o Peek acompanha mais de 4.500 transportadoras.

O Peek lê diretamente o rastreador da TopYou e mostra o status, cada evento nas palavras da TopYou ou da transportadora que ela repassa, o país de destino e o peso registrado pela TopYou. Locais que indicam apenas um país são omitidos nas linhas próprias da TopYou para que o evento no aeroporto chinês não pareça ter ocorrido no seu país. Quando o registro da TopYou marca a encomenda como entregue ou devolvida, o Peek exibe "Entregue" ou "Devolvida ao remetente" mesmo que o texto da última linha não diga isso. As leituras próprias da TopYou seguem o horário de Pequim; nos eventos repassados da transportadora local, o rastreador da TopYou nem sempre informa o fuso correto, então o Peek calcula o horário adequado e exibe tudo no fuso horário do seu dispositivo. Não há data prevista de entrega, pois a TopYou não informa uma.

Quando o registro da TopYou traz o código da transportadora local, o Peek tenta consultá-lo nos correios do seu país se for um código postal internacional válido (duas letras, nove dígitos com dígito verificador correto e duas letras), ou na única transportadora compatível com o formato, desde que o Peek leia diretamente o rastreador dela. Assim que essa transportadora exibe uma leitura tão recente quanto a última da TopYou, o Peek passa a acompanhar o pacote por lá, exibindo ambos os códigos e preservando as leituras da TopYou no histórico. Códigos que o Peek não consegue vincular a uma única empresa — como sequências simples de 20 dígitos usadas pelo USPS e outras transportadoras — não são consultados separadamente: o Peek permanece no rastreador da TopYou, que também pode mostrar os eventos da entregadora.

O Peek verifica até a cada 10 minutos enquanto você mantém o pacote aberto ou no dia da entrega com alertas ligados (nos demais horários, de hora em hora), voltando a consultar de hora em hora se a TopYou ficar sem novidades (após 12 horas enquanto ainda consta "Etiqueta criada" ou "Recebida pela transportadora", ou após dois dias nas demais etapas). No dia em que o pacote sai para entrega, verifica até a cada 2 minutos (a cada 5 quando precisa abrir navegador) a partir das 6h no horário local enquanto o rastreador da TopYou responde. Por meio de serviços de rastreamento, consulta de hora em hora, a cada 3 horas após um dia sem movimento e até a cada 15 minutos no dia da entrega enquanto o pacote estiver aberto ou com alertas ligados. Quando outra transportadora assume a entrega, o Peek segue a frequência dela. Ele pode avisar em cada leitura, só em etapas importantes ou em "Só a entrega". "Etapas importantes" (entrega, retirada, alfândega, tentativas sem sucesso e devoluções) alerta na alfândega do seu país (`Shipment started the customs clearance process at destination`), em `The driver is out for delivery` da GOFO e `Delivery, Our delivery associate is out for delivery.` da iMile, e na entrega — mas não no desembaraço de exportação chinês, no voo, na liberação alfandegária nem em `Shipment is out for delivery`. O Peek não pode falar com a TopYou nem com o vendedor por você.

## Perguntas sobre o rastreamento da TopYou

### O que é a TopYou (通邮)?

É a 深圳市通邮物流科技（集团）股份有限公司 (também chamada 通邮集团, Tongyou ou SZTY56), uma operadora logística internacional sediada em Shenzhen, China. Segundo a empresa, foi fundada em 2016, possui filiais e centros de triagem em Shenzhen, Guangzhou, Xangai e outras cidades chinesas e processa mais de 800.000 pedidos por dia via correios, linhas próprias e grandes transportadoras expressas. Seus códigos seguem o padrão `TYZR`, uma letra, dez dígitos e `YQ` (alguns com outra letra no lugar do `R`).

### Por que a TopYou mostra meu país antes de o pacote sair da China?

Porque o rastreador da TopYou escreve o país de destino ao lado de suas próprias leituras, inclusive no aeroporto de saída. Guie-se pelo texto: `Arrived at airport of the original country` significa que a encomenda ainda aguarda o voo na origem.

### Quem entrega encomendas da TopYou no meu país?

Nos Estados Unidos, o USPS assume o envio após o voo. Na França e na Itália pode ser a GOFO; na Alemanha, a iMile; na Áustria, a DPD. Em outros países, o registro da TopYou pode trazer o código da entregadora sem o nome: pergunte ao vendedor qual empresa entrega.

### Com quem falar sobre uma encomenda da TopYou extraviada?

Com o vendedor onde você comprou, pois foi ele quem contratou a TopYou. O site da TopYou informa um telefone em Shenzhen (acima), mas não oferece atendimento direto a destinatários.

:::sources
- [TopYou: 通邮集团 (página inicial)](https://www.szty56.com/) – telefone e horário
- [TopYou: 关于我们 (sobre)](https://www.szty56.com/view?path=about/about) – fundação em 2016, filiais, volume diário de pedidos e serviços
- [TopYou: 轨迹查询 (rastreamento)](https://track.szty56.com/) – rastreador oficial da TopYou
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – taxa de manuseio da UE de € 2 desde 1º de novembro de 2026
- [European Commission: Guidance on the 3 EUR customs duty and Union handling fee](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – € 2 por categoria de produto em vendas à distância, qualquer que seja o valor
:::
