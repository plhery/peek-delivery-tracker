---
title: Rastreamento ePost Global: códigos EPG e entrega final
description: Rastreie encomendas da ePost Global: formato dos códigos EPG, status da ePost e parceiros, alfândega na UE, contato e como o Peek segue a entrega final.
slug: rastreamento-epost-global
published: 2026-11-24
updated: 2026-11-24
---

A ePost Global é uma empresa norte-americana de logística cujos códigos de rastreamento começam com `EPG`. Nos históricos conhecidos pelo código do Peek, um centro de processamento da ePost Global nos Estados Unidos registra a entrada da encomenda e depois a transfere a um parceiro — um serviço postal ou transportadora — que segue com o transporte. A loja ou o remetente informa o código: cole-o na caixa acima. O rastreador oficial da ePost Global fica em epgtrack.com. O catálogo do Peek também reconhece a ePost Global como PPX Track e como RR Donnelley, RRD Donnelley ou RRD International Logistics: buscar qualquer um desses nomes ao escolher a transportadora no Peek leva à ePost Global.

## Como é o código de rastreamento da ePost Global

O Peek reconhece um formato para a ePost Global: `EPG` seguido de 10 a 15 dígitos. Com dígitos fictícios:

| Código (fictício) | O que é |
| --- | --- |
| `EPG482910573649182` | `EPG` e 15 dígitos: código da ePost Global. |
| `EPG4829105736` | `EPG` e 10 dígitos: também da ePost Global, o formato mais curto aceito pelo Peek. |
| `EPG482910573` | Apenas 9 dígitos: fora do padrão da ePost Global. Três letras e nove dígitos combinam com Purolator e TNT Australia, entre outras, então o Peek consulta Purolator e TNT Australia para ver quem conhece o código. Se você escolher ePost Global manualmente, o Peek avisa que ela "não emite códigos como este". Confira se falta um dígito. |
| `EPG4829105736491827` | 16 dígitos, um a mais: o Peek não reconhece nenhuma transportadora. Confira se há dígito sobrando. |
| `LZ482910576US` | Duas letras, nove dígitos e `US`: código de rastreamento postal dos EUA. O Peek identifica a [USPS](carrier:usps) na maioria dos códigos iniciados em `C`, `E`, `L` ou `R`. Para ver também as etapas da ePost Global, adicione o código `EPG`. |

Espaços, traços e letras minúsculas não fazem diferença ao colar o código no Peek.

**Onde encontrar o código.** No e-mail de envio da loja ou na página do pedido. Se não localizar, veja [como encontrar seu código de rastreamento](guide:find-tracking-number) e [formatos de código de rastreamento](guide:tracking-number-formats).

## Status de rastreamento da ePost Global explicados

O rastreador da ePost Global lista cada movimentação com horário, descrição e, na maioria das vezes, local. As etapas próprias da empresa aparecem nesta ordem:

| O que a ePost Global mostra | O que significa | O que o Peek mostra |
| --- | --- | --- |
| `Data Received` | A loja enviou os dados da encomenda à ePost Global, que ainda não recebeu o pacote. | "Etiqueta criada" |
| `Shipment in Transit to the ePost Global Processing Center` | Adicionado sempre um minuto após os dados: faz parte da emissão da etiqueta, não indica que o pacote já está em movimento. | "Etiqueta criada" |
| `Parcel Received and Processing` | Um centro de processamento da ePost Global recebeu o volume; a linha informa cidade e estado. | "Recebida pela transportadora" |
| `Parcel Shipped` | A encomenda saiu do centro de processamento. | "Em trânsito" |
| `In transit to … tracking #: …` | Entregue ao parceiro que segue com o transporte, um minuto após o despacho. A linha traz o serviço do parceiro e o novo código (também pode aparecer como `In transit via …` ou `tracking#:`). | "Em trânsito" |
| `In transit` | A mesma transferência, sem nome do serviço nem código do parceiro. | "Em trânsito" |

**Etapas do parceiro.** Após a transferência, o rastreador da ePost Global repassa as leituras do próprio parceiro. Estas são as reconhecidas no código do Peek:

| Após a transferência | O que significa | O que o Peek mostra |
| --- | --- | --- |
| `Shipping label created - OSM awaiting item` | A parceira OSM Worldwide recebeu os dados da encomenda. | sem alteração |
| `Arrived at OSM facility`, `Departed OSM facility` | Chegou a uma unidade da OSM Worldwide e saiu dela. | "Em trânsito" |
| `Package accepted at courier partner`, `Package processed by courier partner` | O parceiro de entrega da OSM Worldwide recebeu ou processou o volume. | "Em trânsito" |
| `Parcel has arrived at export distribution warehouse` | A [ECMS Express](carrier:ecms-express), parceira da rota, recebeu a encomenda no depósito de exportação. | "Em trânsito" |
| `Customer Information Manifested` | A DAI Post, parceira para a Nova Zelândia, recebeu os dados do envio. | sem alteração |
| `Checked in at origin hub` | Chegou ao hub da DAI Post nos Estados Unidos. | "Em trânsito" |
| `Airline Delay` | Atraso no voo: questão aérea, não um problema no pacote. | "Em trânsito" |
| `Collected from the airline for processing` | Retirada do voo para triagem no país de destino. | "Em trânsito" |
| `Consignment received at the PostNL Acceptance Centre`, `The item has been manifested and ready to transport` | A PostNL, via Spring, recebeu o pacote e o listou para transporte. | "Em trânsito" |
| `The item is tied to a MAWB and ready for shipment` | A PostNL, via Spring, vinculou o volume a um embarque aéreo. | "Em trânsito" |
| `Your item has been dropped off by the sender with our postal partner in the country of origin.`, `Your item is ready to be forwarded from its foreign territory. It will be given to the carrier for forwarding.` | Avisos da La Poste para pacotes que a PostNL leva à França: o envio ainda segue para lá. | "Em trânsito" |
| `The parcel has left the parcel center.`, `The parcel has reached the parcel center.` | Rede europeia da GLS: deixou o hub e chegou a um centro de encomendas no país de destino. | "Em trânsito" |
| `The parcel is expected to be delivered during the day.` | Rede europeia da GLS: saiu para entrega hoje com o motorista. | "Saiu para entrega" |
| `The parcel has been delivered.` | Rede europeia da GLS: entregue. | "Entregue" |
| `Departed Hub`, `Shipment Received in Depot`, `Shipment Departed Depot` | Parceiro no Reino Unido de entregas da [Royal Mail](carrier:royal-mail): a caminho do Reino Unido, passando por base antes e depois da alfândega e seguindo à unidade de entrega. | "Em trânsito" |
| `Arrived in customs` | Mesmo parceiro: na alfândega britânica, no mesmo minuto do registro na base. | "Na alfândega" |
| `Ready for Collection` | Mesmo parceiro: aguardando retirada num ponto de coleta. | "Pronta para retirada" |

Um recebimento do parceiro depois de `Parcel Shipped` conta como "Em trânsito", não como um novo início. Outras linhas de parceiros (como a entrega) são interpretadas pelas regras gerais de texto do Peek. Uma linha desconhecida aparece como escrita e conta como "Em trânsito", exceto se a encomenda já saiu para entrega ou avançou além dessa etapa — nesse caso o status se mantém.

## Quem faz a entrega das encomendas da ePost Global

A ePost Global afirma manter parcerias postais e de última milha em seis continentes. Nos históricos conhecidos pelo Peek, constam OSM Worldwide, DAI Post na Nova Zelândia, ECMS Express, rede europeia da GLS, Royal Mail no Reino Unido e PostNL via Spring, com a [La Poste](carrier:la-poste) assumindo na França após a PostNL. Quando a ePost Global não nomeia o parceiro mas fornece um código de rastreamento postal (duas letras, nove dígitos, duas letras), o Peek consulta o correio do país de destino — como [USPS](carrier:usps), [Royal Mail](carrier:royal-mail), An Post, Canada Post ou [Australia Post](carrier:australia-post), conforme explicado abaixo.

**Tentativas de entrega e retirada.** O número de tentativas e o local de guarda seguem as regras do parceiro final: consulte o parceiro com o código dele. Veja [entrega não realizada](guide:missed-delivery) e [encomenda não retirada](guide:uncollected-parcel).

**Alfândega na União Europeia.** Compras online vindas de fora da UE pagam taxa aduaneira de €3 por item em pacotes de até €150, desde 1º de julho de 2026, e taxa de manuseio da União de €2 por item, qualquer que seja o valor, desde 1º de novembro de 2026. Considera-se um item as mercadorias com a mesma classificação tarifária, descrição e, quando exigido, origem. Na área de IVA da UE também incide IVA de importação, salvo se já cobrado na compra via IOSS. Segundo a ePost Global, tanto remessas DDP quanto DDU devem o imposto e a taxa: no DDP o remetente quita antes da chegada e você nada paga na entrega; no DDU você paga ao receber. A Alemanha não aceita mais remessas DDU, e a ePost desligou o envio DDU para lá em 26 de outubro. Nos demais países, o valor varia conforme as regras locais. Veja [encomendas retidas na alfândega](guide:customs).

## Encomenda da ePost Global parada, extraviada ou devolvida

**Nada ainda.** Quando o rastreador da ePost Global ainda não traz atualizações para o código, o Peek exibe "Aguardando ePost Global" e continua consultando. Confira o código: `EPG` mais 10 a 15 dígitos. Se após dois dias a ePost Global continuar sem reconhecer o código e nenhum serviço de rastreamento encontrá-lo, o Peek informa: "Depois de dois dias, a transportadora ainda não reconhece este código. Confira o código, escolha outra transportadora ou exclua a encomenda." Se nada aparecer após alguns dias, confirme com a loja se o pacote realmente foi postado.

**Sem novidades após a transferência.** O parceiro já está com a encomenda e o rastreamento próprio dele pode estar mais adiantado que o da ePost Global. O Peek acompanha a encomenda por lá quando possível (veja abaixo); caso contrário, busque o nome e o código do parceiro na linha de transferência.

**Dias sem atualização.** Após quatro dias sem nova linha, o Peek destaca a data da última leitura ("Sem atualizações desde seg 12 out"), avisa que encomendas costumam ficar quietas em voos longos, na alfândega ou no fim de semana (após três semanas sem novidade, orienta falar com o vendedor) e exibe o link "Pedir ajuda a ePost Global" — ou o do parceiro quando o Peek já segue a encomenda com um parceiro cuja página de ajuda ele conhece. Veja [por que o rastreamento para de atualizar](guide:tracking-not-updating).

**Entregue, mas não chegou.** Fale com o parceiro final e depois com a loja. Veja [encomenda entregue mas não recebida](guide:delivered-not-received).

**Extraviada ou devolvida.** Solicite reembolso ou reenvio à loja ou marketplace. Veja [encomendas extraviadas](guide:lost-parcel) e [devolvida ao remetente](guide:returned-to-sender).

## Atendimento e contato da ePost Global

Em epostglobalshipping.com, a ePost Global divulga uma linha gratuita, +1 866-784-8444, e um telefone local, +1 310-784-8485, sem horários nem e-mail de suporte publicados. Seu assistente online, o ePost Assist, fica em epostassist.epgtrack.com, e a página de suporte traz um formulário de contato. Quando um pacote para de atualizar, o Peek vincula essa página de suporte no link "Pedir ajuda a ePost Global".

Em compras de loja, fale primeiro com a loja: foi ela quem contratou o envio. Depois que um parceiro assume o pacote, trate dúvidas da entrega diretamente com o parceiro.

## Como rastrear uma encomenda da ePost Global no Peek

O [Peek](/) identifica a ePost Global de imediato por `EPG` seguido de 10 a 15 dígitos ("Transportadora detectada") e consulta o rastreador oficial só com esse código, sem código postal nem login. Links de rastreamento da ePost Global ou da PPX Track também funcionam: o Peek extrai o código do link (o primeiro, se houver vários). Se você só tiver o código do parceiro final, cole-o assim mesmo: o Peek acompanha mais de 4.500 transportadoras.

O Peek exibe o status, cada movimentação com local, o país de destino e o parceiro com o código dele quando a ePost Global informa esses dados. Ele omite barra de progresso, códigos postais e números de conhecimento aéreo (AWB). Como o rastreador da ePost Global não fornece previsão de entrega, peso nem comprovante de entrega, o Peek não exibe esses campos. O link "Abrir o site de ePost Global" na página da encomenda abre o código direto no rastreador oficial.

O rastreador mostra horários sem fuso horário. O Peek lê `Parcel Received and Processing` e `Parcel Shipped` no horário do estado do centro de processamento (quando o estado adota um único fuso), as linhas da GLS no horário da Europa Central, as da Royal Mail no horário do Reino Unido e as demais no relógio do local ou do trecho percorrido. Todos os horários aparecem convertidos para o fuso do seu dispositivo; em peektracker.com e nos apps de iPhone e Android, o histórico termina com "Horários no seu fuso horário:" seguido do nome do fuso.

Quando a ePost Global indica um parceiro conhecido pelo Peek, o cartão da encomenda avisa ("Entrega por PostNL", por exemplo, ou "Anunciada a PostNL, ainda não recebida" enquanto o parceiro tem apenas o aviso prévio sem leitura própria) e lista o código do parceiro logo após o código `EPG`, se informado. O Peek então consulta também esse parceiro usando o código dele, ou o código `EPG` se nenhum novo código vier. Sem parceiro nomeado, consulta o correio do país de destino para um código de rastreamento postal e, para outros formatos, a única transportadora correspondente ao padrão — sempre apenas entre transportadoras cujo rastreamento o Peek lê diretamente. Assim que essa transportadora mostra uma leitura tão recente quanto ou mais recente que a última da ePost Global, o Peek acompanha o pacote por lá preservando o histórico da ePost Global; caso contrário, mantém o rastreador da ePost Global e as cópias das linhas do parceiro. O mesmo vale para entregas pela rede europeia da GLS, já que a ePost Global não indica qual empresa nacional da GLS fará a entrega.

Enquanto você mantém o pacote aberto no Peek, ou no dia da entrega com alertas ligados, o Peek verifica até a cada 10 minutos, e até a cada 2 minutos a partir das 6h no horário local no dia da saída para entrega, enquanto o próprio rastreador da ePost Global responder (pelos serviços de rastreamento, até a cada 15 minutos); fora disso, de hora em hora. Quando outra transportadora assume, o Peek segue o ritmo dela. Após dois dias sem novidades (ou 12 horas ainda em "Etiqueta criada" ou "Recebida pela transportadora"), verifica de hora em hora. Você pode ativar alertas para "Cada movimentação", "Etapas importantes" ou "Só a entrega". O Peek não pode contatar a ePost Global nem a loja em seu nome.

## Perguntas frequentes sobre rastreamento ePost Global

### O que é PPX Track?

É outro nome que o catálogo do Peek dá à ePost Global. O Peek lê links de rastreamento da PPX Track como links da ePost Global e extrai o código dele.

### O que significa "In transit to" no rastreamento da ePost Global?

Significa que a ePost Global transferiu a encomenda para o parceiro que segue com o transporte. A linha informa o serviço desse parceiro e, após `tracking #:`, traz o novo código de rastreamento.

### Por que meu pacote da ePost Global está parado em "Data Received"?

A loja enviou os dados da encomenda à ePost Global, mas nenhum centro de processamento dela registrou o pacote fisicamente ainda. Pergunte à loja quando o volume foi entregue à transportadora.

### Quem entrega encomendas da ePost Global no meu país?

Um parceiro, informado na linha de transferência da ePost Global. Nos históricos conhecidos pelo Peek aparecem OSM Worldwide, DAI Post na Nova Zelândia, ECMS Express, rede europeia da GLS, Royal Mail no Reino Unido, PostNL via Spring e La Poste na França.

### Como entro em contato com a ePost Global?

Procure a loja primeiro, pois foi ela quem contratou o envio. A ePost Global divulga dois telefones, +1 866-784-8444 e +1 310-784-8485, mas nenhum e-mail de suporte; numa encomenda que parou de atualizar, o link "Pedir ajuda a ePost Global" do Peek abre a página de suporte da ePost Global.

:::sources
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – taxa aduaneira de €3 por item até €150 desde 1º de julho de 2026 e taxa de manuseio da União de €2 desde 1º de novembro de 2026
- [European Commission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – €2 por item qualquer que seja o valor e definição de item
- [Universal Parcel Scraper: ePost Global](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/epost-global) – nomes, o formato EPG, links de rastreamento, o rastreador, textos e suas etapas, linhas dos parceiros, fusos horários, o parceiro e o código dele, país de destino, o que fica de fora, página de ajuda
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formatos de código da Purolator e da USPS, transferência ao parceiro ou ao correio do seu país
:::
