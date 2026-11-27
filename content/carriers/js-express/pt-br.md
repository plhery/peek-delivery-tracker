---
title: Rastreamento JS Express: códigos JSE…YQ, status e entrega
description: Rastreie encomendas da JS Express (急速国际) vindas da China: formato do código JSE…YQ, significado dos status e quem faz a entrega nos EUA, Canadá e Europa.
slug: rastreamento-js-express
published: 2026-11-24
updated: 2026-11-24
---

A JS Express (急速国际, também chamada JS EXP) é uma operadora logística chinesa voltada para lojas online internacionais, operada pela 急创速供应链管理（苏州）有限公司 em Suzhou. Se a loja enviou seu pedido pela JS Express, o código começa com `JSE` e termina com `YQ`: cole o número na caixa acima. A JS Express leva o pacote da China até o exterior, e uma transportadora local faz a entrega no seu país: seu histórico mostra USPS, GOFO ou SpeedX nos Estados Unidos, Straightship no Canadá, Yodel no Reino Unido, DHL, iMile ou J&T Express na Alemanha, Colissimo na França, CTT Express na Espanha e FedEx em algumas rotas.

## Código de rastreamento da JS Express: qual é o formato

As etiquetas da JS Express seguem o formato `JSE`, duas letras, dez dígitos e `YQ`, somando 17 caracteres. Com dígitos fictícios:

| Código | O que é |
| --- | --- |
| `JSEKM4829173605YQ` | `JSE`, duas letras, dez dígitos e `YQ`: código de rastreamento da JS Express. |
| `JSEKM482917360YQ` | Apenas nove dígitos: fora do padrão da JS Express. Verifique se falta um dígito. |
| `JXCKM4829173605YQ` | Também termina em `YQ`, mas começa com `JXC`: código da Juxi Express. |

Outras transportadoras chinesas usam a mesma estrutura com letras próprias no início: `FEI` para 17FEIA, `WNB` para WanbExpress, `SFC` para SFC e `CJP` para CJ Packet. `JSE` identifica a JS Express. Espaços e letras minúsculas não importam no Peek, que ainda corrige um zero digitado no lugar da segunda letra para `O`, e uma letra `O` entre os dez dígitos para zero. O rastreador da JS Express exige o número completo: códigos parciais não encontram nada.

**Onde encontrar.** A loja repassa o código no e-mail de confirmação de envio ou na página do pedido. A JS Express afirma trabalhar de perto com Amazon, Walmart, Shopify, eBay, AliExpress, Wish e Shopee. Se não localizar, veja [como encontrar seu código de rastreamento](guide:find-tracking-number).

**Sem link compartilhável.** As páginas de rastreamento da JS Express aceitam o código apenas por formulário, então não existe um link direto para um pacote específico: copie o número. A página em inglês, "Express Query", responde imediatamente; a página em chinês exige digitar um código visual de verificação (验证码) primeiro.

## Status de rastreamento da JS Express explicados

A página da JS Express exibe cada leitura com data e hora, o local entre 【colchetes】 e a descrição, da mais recente para a mais antiga, com "Latest" no topo. As mensagens são em sua maioria em inglês. Na ordem em que uma encomenda costuma recebê-las:

| A JS Express diz | O que significa para você | O Peek mostra |
| --- | --- | --- |
| `Electronic notification of order creation received.` | A loja enviou os dados do pedido à JS Express. A JS Express ainda não tem o pacote. | "Etiqueta criada" |
| `PRE-ADVICE` | Um parceiro recebeu os dados da encomenda antes de o depósito da JS Express estar com o pacote. | A etapa que a encomenda já tinha |
| `Item has been collected` | O depósito da JS Express na China recebeu o pacote: primeira leitura física. | "Recebida pela transportadora" |
| `Item inbound in sorting center.`, `Item outbound in sorting center.` | Passando por triagem na JS Express na China. | "Em trânsito" |
| `Domestic allocation has begun`, `Under domestic allocation` | Ainda no depósito da JS Express na China, após a triagem e antes da partida. | "Em trânsito" |
| `Waybill Generated for` | A transportadora local do seu país gerou a etiqueta enquanto o depósito na China ainda está com o pacote. | "Etiqueta criada", ou a etapa que a encomenda já tinha (geralmente "Em trânsito") |
| `Arrive at transit center` | No centro de trânsito da JS Express na China, entre a triagem e o embarque. | "Em trânsito" |
| `Item departed from origin.`, `Arrive at international airport to abroad` | Saiu da origem e chegou ao aeroporto na China. | "Em trânsito" |
| `Arrived at export center`, `Exports Center Sorting and Packing`, `Arrived at Origin Port` | Em algumas rotas, entre as duas etapas anteriores: um centro de exportação na China separa e embala o pacote, que depois chega ao porto de onde deixa a China. | "Em trânsito" |
| `Shipment arrived at facility and measured.`, `Depart from facility to service provider.` | Uma instalação na China pesa e mede o pacote e depois o repassa à companhia que fará o transporte aéreo. | "Em trânsito" |
| `Start on-site transportation and shipment` | No aeroporto na China, após a chegada: começou a movimentação interna antes da liberação pela alfândega de exportação chinesa. | "Em trânsito" |
| `Released From Export Customs` | A alfândega de exportação da China concluiu a liberação. Ainda não é a alfândega do seu país. | "Em trânsito" |
| `DEPART ORIGIN AIRPORT`, `Departure from the original airport`, `Item Flight departure`, `Initial landing`, `Connecting flights flight takeoff` | O voo partiu, às vezes com escala e voo de conexão. | "Em trânsito" |
| `ARRIVED AT DESTINATION AIRPORT`, `Arrival to the destination airport`, `Item Flight landing`, `NOA-Notification of arrival from GHA` | Pousou no país de destino. NOA é o aviso de chegada dado pelo operador de solo do aeroporto (GHA). | "Em trânsito" |
| `The customs clearance agent is queuing up to pick up the goods` | O despachante aduaneiro no destino aguarda no terminal de cargas do aeroporto para retirar o lote: nem o agente nem a alfândega estão com ele ainda. | "Em trânsito" |
| `The customs clearance agent has picked up the goods` | O agente retirou a carga no terminal aéreo para iniciar o desembaraço de importação. | "Na alfândega" |
| `The goods have been picked up abroad` | O agente no país de destino retirou a remessa no aeroporto. Não é uma nova coleta no vendedor. | "Em trânsito" |
| `Customs clearance agent received the shipment in destnation country.` | O agente aduaneiro no destino recebeu a remessa para desembaraço após o voo. "destnation" é a grafia usada pela própria JS Express. | "Na alfândega" |
| `Inbound to customs temporary warehouse` | Em armazém alfandegado aguardando desembaraço de importação. | "Na alfândega" |
| `Released From Import Customs`, `Released from customs: customs cleared.` | A alfândega do país de destino liberou a encomenda. | "Em trânsito" |
| `Depart from the destination distribution center.` | Um centro de distribuição no seu país encaminhou o pacote após a alfândega. | "Em trânsito" |
| `In transit, it's progressing through post network` | O serviço postal do país de destino segue levando a encomenda pela rede antes de sair para entrega. | "Em trânsito" |
| `collection by overseas warehouse` | O depósito da JS Express no país de destino recebeu o pacote após a liberação alfandegária. | "Em trânsito" |
| `Load cargo overseas`, `Vehicles depart from` | Esse depósito carrega o pacote para a transportadora local e o caminhão parte do local exibido na leitura. | "Em trânsito" |
| `Delivery to local courier`, `Last Mile Delivery` | Entregue à transportadora responsável pela entrega final no seu país, antes da primeira leitura dela. Ainda não foi entregue a você. | "Em trânsito" |
| `Delivery in progress` | Em algumas rotas: a transportadora local já está com o pacote após a chegada à unidade dela, antes da rota do entregador. Apesar do texto, ainda não saiu para entrega. | "Em trânsito" |
| `到达待取` | Status próprio da JS Express, em chinês: chegou e aguarda retirada. O local exibido é apenas o país. | "Pronta para retirada" |
| `The delivery address is incorrect. Please ask the recipient to contact their local area?` | Aviso da própria JS Express após falha de entrega da transportadora local: o endereço está confuso ou errado. Ele pede que você contate a unidade local, cujo telefone aparece na página da JS Express; o Peek omite telefones. | "Precisa de atenção" |

**Leituras da transportadora local.** Depois de `Delivery to local courier`, o histórico continua com os textos da empresa local. Nos EUA, os do USPS: `Accepted at USPS Origin Facility` ou `Accepted at USPS Facility` (primeira leitura, não um novo envio), `Out for Delivery` e `Delivered, Front Door/Porch`. Os da SpeedX aparecem sem citar o nome dela: `Processing at Transit Facility`, `In transit to destination facility`, `Arrival at Destination Facility` e `Processing at Designated Facility`, todos como "Em trânsito". Outro parceiro americano também envia leituras sem nome: `Arrived at Origin Hub` (primeiro hub junto ao aeroporto de chegada), `Departed Destination Hub` e `Arrived At Warehouse` (depósito de última milha antes da rota), todos em "Em trânsito". No Canadá, a Straightship exibe `Package is received at a Straightship station`, depois `Package will be delivered before 9:00pm`, indicando a rota daquele dia ("Saiu para entrega", seguida de entrega ou tentativa), e `Delivered` com a cidade entre colchetes. Na Alemanha, as leituras da DHL vêm em inglês, às vezes com ponto final: `The instruction data for this shipment have been provided by the sender to DHL electronically` (registro eletrônico dos dados, exibido como "Etiqueta criada", ou "Em trânsito" se já havia leitura anterior) e `The shipment is in transit to DHL` ("Em trânsito"); pacotes pequenos também podem mostrar `Die Sendung wurde im Briefzentrum bearbeitet.`, em alemão, de um centro postal da DHL ("Em trânsito"). Motoristas da iMile podem encerrar a rota com `Route not completed` ("Tentativa de entrega"). As da J&T Express Alemanha vêm em alemão com os locais dentro de 【】: `【】【】Ihr Sendung wurde abgeholt.` é a primeira leitura ao assumir o pacote após o voo e a alfândega, seguida por `【】Die Sendung hat【】verlassen. Weiterversand nach【】` (saiu de uma unidade para a próxima) e `【】Die Sendung ist bei【】 angekommen.` (chegou a um hub ou depósito de entrega antes da rota), todas em "Em trânsito". Quando a FedEx entrega, `Shipment information sent to FedEx` é a emissão da etiqueta antes de o depósito da JS Express ter o pacote ("Etiqueta criada"), seguida por `Arrived at FedEx location`, `Left FedEx origin facility` e `On the way` ("Em trânsito"). Na Hungria, surgem `Arrived to HU HUB`, `Linehaul Transit` (trajeto do hub ao centro de triagem) e `Shipment arrived to sorting centre / Shipment in the sorting centre`, todas em "Em trânsito". Na Espanha, `Enviado`, em espanhol, significa que a CTT Express encaminhou o pacote após recebê-lo e antes de sair para entrega ("Em trânsito").

**Uma transportadora sem nome.** Em algumas rotas, a JS Express repassa eventos da transportadora final em letras maiúsculas sem indicar a empresa nem o país. `SHIPMENT RECEIVED`, `PROCESSED AT HUB` e `SHIPMENT PROCESSED` indicam entrada no hub da transportadora, e `DEPARTED FROM DEPOT` a saída para o depósito de entrega, todos em "Em trânsito". `RECEIVED AT LOCAL DEPOT` é esse depósito, antes de cada rota e de novo após uma tentativa falha: trata-se do depósito da transportadora, não de um ponto de retirada, então o Peek mantém o status da linha anterior. `DELIVERY ATTEMPTED: 1ST DELIVERY ATTEMPT`, `DELIVERY ATTEMPTED: 2ND DELIVERY ATTEMPT` e `DELIVERY ATTEMPTED: 3RD DELIVERY ATTEMPT` são tentativas sem sucesso ("Tentativa de entrega"). Na terceira, a transportadora pede para agendar a entrega; `SHIPMENT REDIRECTED: ON CONSIGNEE'S REQUEST` indica que o pacote seguiu conforme o agendamento ("Em trânsito"). `DELIVERY EXCEPTION` sinaliza problema no hub antes da rota, e `DELIVERY EXCEPTION, WAIT FOR INFORMATION CONFIRMATION`, após `ARRIVE AT THE WAREHOUSE` ("Em trânsito"), indica retenção aguardando confirmação de dados: o Peek mostra ambos como "Precisa de atenção". Se o Peek não exibir o código da transportadora local, pergunte ao vendedor quem faz a entrega e o que precisa ser confirmado.

**Linhas que não são leituras físicas.** `SHIPPING INFORMATION RECEIVED`, `SHIPMENT CREATED`, `Data Received`, `Shipping Label Created, USPS Awaiting Item` do USPS, `Package information is created in the system` e, em hubs europeus, `Shipment Data Uploaded` seguido de `Processed` são apenas o registro eletrônico dos dados por um parceiro, às vezes dias antes de ele receber o pacote. `Consignee Contacted`, registrado no momento de uma tentativa falha, é o contato da transportadora com você e também não altera o status.

**Horários fora de ordem.** Os horários não trazem fuso horário e misturam relógios diferentes: horário de Pequim na China, horário local nos aeroportos e na maioria das transportadoras, e UTC na Straightship, horas à frente dos fusos canadenses. Guie-se pela ordem das linhas, não pelos horários. No Peek, as linhas em alemão da J&T Express Alemanha mostram o horário informado pelo rastreador oficial da própria J&T Express Alemanha.

## Quem entrega as encomendas da JS Express

**Estados Unidos.** O histórico da JS Express mostra o [USPS](carrier:usps) entregando (com leituras do USPS até a entrega), ou a [GOFO](carrier:gofo) (com leituras do centro de triagem à rota), ou a [SpeedX](carrier:speedx), cujas leituras não trazem o nome dela. Depois que o USPS recebe o pacote, aceita abertura de busca de Missing Mail pelo remetente ou pelo destinatário a partir de 7 dias após a postagem.

**Canadá.** A Straightship, transportadora canadense, entrega parte dos envios da JS Express; suas leituras aparecem no histórico da JS Express.

**Alemanha, França e Espanha.** A rota europeia da JS Express atende países como Reino Unido, França, Alemanha, Itália, Espanha, Suíça e Dinamarca, exceto alguns códigos postais remotos. Para a Alemanha, a JS Express cita DPD e outras transportadoras de última milha e afirma que mais de 98% dos pacotes são entregues em até 8 dias úteis; o histórico lá já mostrou leituras da [DHL Paket](carrier:dhl), da [iMile](carrier:imile) em centrais alemãs e da [J&T Express Alemanha](carrier:j-and-t-de), em alemão. Na França já exibiu leituras da [Colissimo](carrier:la-poste), em francês, e na Espanha, da CTT Express.

**Reino Unido, Irlanda, Austrália e outros países.** Em algumas rotas o histórico mostra leituras da [FedEx](carrier:fedex). No Reino Unido aparecem eventos da Yodel; dois deles, `DESPATCHED AND ON ROUTE TO YODEL` e `PARCEL PICKED UP`, surgem enquanto o pacote ainda está no depósito da JS Express na China. Para a Austrália, a JS Express cita Australia Post e Fastway (hoje [Aramex Australia](carrier:aramex-au)) abaixo de 4 kg, e DPEX acima desse peso. O registro da JS Express pode conter o código da transportadora local, sem citar o nome, e o site da JS Express não mostra nenhum dos dois. O Peek identifica a transportadora quando esse número combina com uma única empresa do seu país e consulta os correios do seu país quando se trata de um [código de rastreamento postal internacional](guide:tracking-number-formats) (duas letras, nove dígitos e duas letras); veja mais abaixo. Se o Peek não exibir o nome da transportadora, pergunte ao vendedor quem entrega.

Tentativas de entrega, avisos de ausência e pontos de retirada seguem as regras da transportadora local: a JS Express não publica regras próprias. Uma linha `到达待取` indica apenas o país, então confira o rastreamento da empresa local ou pergunte ao vendedor onde o pacote está guardado.

## Pacote da JS Express que não chega ou rastreamento parado

**"未获取到任何数据,请提供有效单号!"** A página em inglês da JS Express responde essa frase em chinês quando não reconhece o código ("nenhum dado encontrado, informe um número de rastreamento válido"). `No track data for now!` significa que ainda não há leituras. O Peek mostra "Aguardando JS Express" em ambos os casos e segue verificando. Se a JS Express continuar sem reconhecer o código após dois dias e nenhuma outra fonte responder por ele, o Peek exibe "Depois de dois dias, a transportadora ainda não reconhece este código. Confira o código, escolha outra transportadora ou exclua a encomenda." Revise o código caractere por caractere; se nada surgir após alguns dias, pergunte ao vendedor se o pacote foi mesmo postado.

**Parado em `Electronic notification of order creation received.`** A JS Express ainda não coletou o pacote. Pergunte à loja quando ele foi entregue à transportadora.

**Parado em `Inbound to customs temporary warehouse`.** Está aguardando a alfândega do seu país. Em algumas rotas aparecem `Start Custom clearance Process` e depois `customs clearance completed`; uma linha anterior como `Under customs clearance, please wait patiently.` refere-se à alfândega de exportação chinesa no aeroporto de origem, e por isso o Peek a exibe como "Em trânsito". A JS Express comercializa sua rota europeia e sua linha padrão para os EUA com tributos pagos na origem (DDP): ela cobra os impostos de importação do lojista antecipadamente, então nessas rotas nada deveria ser cobrado de você. Se uma transportadora exigir pagamento, confirme com o vendedor antes de pagar. Na União Europeia, vigora desde 1º de novembro de 2026 a taxa de manuseio de € 2 por categoria de produto; o vendedor ou a empresa entregadora arca com ela e pode repassá-la. Veja [encomendas paradas na alfândega](guide:customs); se o pacote ficar retido por dias, avise o vendedor. Leia também [por que o rastreamento para de atualizar](guide:tracking-not-updating) e [como rastrear uma encomenda da China](guide:tracking-from-china).

**Nada após `Delivery to local courier`.** A transportadora local já está com a encomenda. Se o Peek mostrar o código dela, acompanhe também no rastreamento dessa empresa; caso contrário, pergunte ao vendedor quem entrega.

**Marcado como entregue, mas nada chegou.** Fale primeiro com a transportadora local (USPS nos EUA) e depois com o vendedor. Na linha dos EUA, a JS Express ajuda a verificar entregas que constam como concluídas mas não recebidas, porém não paga indenização nesses casos, valendo a política do vendedor. Veja [marcado como entregue mas não recebido](guide:delivered-not-received).

**Extraviado, avariado ou devolvido.** O vendedor é o cliente contratante da JS Express. Pelos termos da empresa, o cliente abre reclamação por escrito em até 30 dias após o recebimento do pacote pela JS Express, e aciona o seguro pela própria conta. Portanto, peça reembolso ou reenvio à loja ou marketplace sem demora. Um pacote que não pôde ser entregue (ninguém em casa, recusado, endereço errado, alfândega) volta mediante tarifa se o remetente solicitar, ou é descartado; na linha dos EUA, a JS Express não devolve o pacote à China nem faz novo envio. Em algumas rotas, `Refused` seguido de `Returned` indica recusa na entrega e retorno ao centro de triagem da transportadora local, ainda não ao vendedor: o Peek mostra "Precisa de atenção". Veja [encomendas extraviadas](guide:lost-parcel) e [devolvidas ao remetente](guide:returned-to-sender).

## Telefone e atendimento ao cliente da JS Express

A página de contato em chinês da JS Express lista os canais abaixo (a página em inglês é mais antiga e ainda traz outros números e endereços):

- **Central telefônica**: 400-0353-518, linha de atendimento na China também indicada para consultas internacionais. A JS Express não divulga horários nem informa se aceita chamadas do exterior.
- **QQ e WeChat**: QQ corporativo 4000353518 e conta oficial no WeChat `js-express`.
- **Reclamações**: formulário na página de reclamações em inglês e a mesma linha telefônica.
- **Escritórios**: matriz e depósito em Suzhou, outros depósitos pela China e escritórios da rede em Hong Kong e em Walnut, Califórnia. O único telefone informado para a unidade americana é a linha chinesa.

As reclamações na JS Express passam pelo vendedor que contratou o envio. Sobre o pedido, fale com o vendedor; sobre a entrega, com a transportadora local.

## Como rastrear um pacote da JS Express com o Peek

O [Peek](/) reconhece códigos da JS Express pelo formato — `JSE`, duas letras, dez dígitos e `YQ`, que nenhuma outra transportadora do catálogo utiliza — e consulta diretamente o rastreador da JS Express. Se você só tiver o código da transportadora local, cole esse código: o Peek acompanha mais de 4.500 transportadoras.

O Peek mostra o histórico da página em inglês da JS Express, cada leitura com seu local e o país de destino (que a página da transportadora omite). `到达待取` aparece traduzido como "Arrived, awaiting collection". Telefones são removidos; o nome de quem assinou é mantido. O Peek omite fotos de entrega e o número do pedido da loja que podem constar nos dados da JS Express. Como previsão de entrega, mostra o dia estimado pela JS Express; depois que a encomenda sai para entrega — inclusive em `Package will be delivered before 9:00pm` da Straightship — não mostra data, pois "Saiu para entrega" já significa hoje. Linhas que não são leituras físicas não mudam o status.

O Peek interpreta registros na China no horário de Pequim, os da Straightship em UTC e os em alemão da J&T Express Alemanha no deslocamento informado em cada linha, calcula os fusos das demais leituras pelo local ou destino e exibe tudo no horário do seu aparelho, do evento mais recente ao mais antigo.

Quando o código da transportadora local combina com uma empresa atuante no seu país, como um código do USPS nos EUA ou da Straightship no Canadá, o Peek mostra "Entrega por" seguido dessa transportadora e do código dela, passando a consultá-la também; códigos de rastreamento postais internacionais vão para os correios do seu país. Enquanto essa empresa só tem o aviso eletrônico do envio, sem leitura própria, aparece "Anunciada a …, ainda não recebida". Assim que ela registra um evento tão recente quanto o último da JS Express, o Peek passa a acompanhar o pacote por lá, exibindo ambos os códigos e preservando as leituras da JS Express. Até lá, segue com a JS Express, cujo histórico costuma incluir os eventos locais.

Enquanto você mantém a encomenda aberta, ou no dia da entrega com alertas ligados, o Peek verifica atualizações até a cada 10 minutos, e até a cada 2 minutos (a cada 5 quando precisa abrir o rastreador da JS Express em navegador) a partir das 6h (horário local) do dia em que o pacote sai para entrega, enquanto o rastreador da própria JS Express responde; nos demais horários, de hora em hora. Por meio de serviços de rastreamento, consulta de hora em hora, a cada 3 horas após um dia sem movimento e até a cada 15 minutos no dia da entrega enquanto o pacote estiver aberto ou com alertas ligados. Quando outra transportadora assume a entrega, o Peek segue a frequência dela; com um código da SpeedX para os EUA, o Peek acompanha pela [SpeedX](carrier:speedx). Após dois dias sem novidades (12 horas enquanto ainda mostra "Etiqueta criada" ou "Recebida pela transportadora"), o Peek verifica de hora em hora. Ele pode avisar em "Cada movimentação", "Etapas importantes" (alfândega, problemas, "Pronta para retirada", saída para entrega, entrega e devolução) ou "Só a entrega". Ele não entra em contato com a JS Express nem com o vendedor por você.

## Perguntas sobre o rastreamento da JS Express

### O que é a JS Express (急速国际)?

É uma empresa chinesa de logística voltada a vendedores internacionais de comércio eletrônico, operada pela 急创速供应链管理（苏州）有限公司. Segundo a empresa, começou em Hong Kong em 2010 e abriu sua base em Suzhou em 2011.

### Onde posso rastrear uma encomenda da JS Express?

Na caixa acima, ou na página em inglês "Express Query" em js-exp.com. A página em chinês pede um código visual de verificação primeiro.

### O que significa 未获取到任何数据 no rastreamento da JS Express?

Significa "nenhum dado encontrado": o rastreador da JS Express não reconhece o número, seja por erro de digitação ou porque a empresa ainda não recebeu o pacote. Confirme o código com o vendedor.

### Quem entrega encomendas da JS Express no meu país?

Uma transportadora local. O histórico da JS Express mostra entregas pelo USPS, GOFO ou SpeedX nos Estados Unidos, Straightship no Canadá, Yodel no Reino Unido, DHL, iMile ou J&T Express na Alemanha, Colissimo na França, CTT Express na Espanha e FedEx em algumas rotas. Em outros países, pergunte ao vendedor qual empresa entrega.

### Com quem falar sobre uma encomenda da JS Express extraviada?

Com o vendedor onde você comprou: foi ele quem contratou o envio com a JS Express e quem pode abrir a reclamação. A linha 400-0353-518 da JS Express é um telefone de atendimento na China.

:::sources
- [JS Express: 急速国际 (página inicial)](https://www.js-exp.com/) – empresa operadora
- [JS Express: Introduction](https://www.js-exp.com/EN/index.php?page=6) – Hong Kong 2010, Suzhou 2011 e e-commerce internacional
- [JS Express: 企业介绍 (apresentação)](https://www.js-exp.com/index.php?page=6) – Suzhou 2011, filiais e plataformas
- [JS Express: Express Query](https://www.js-exp.com/EN/index.php?page=19) – rastreador em inglês, dados exibidos e respostas
- [JS Express: 快件查询 (rastreamento)](https://www.js-exp.com/index.php?page=19) – código visual de verificação
- [JS Express: Insurance Claims](https://www.js-exp.com/EN/index.php?page=29) – reclamações pelo cliente, prazo de 30 dias e encomendas não entregáveis
- [JS Express: 投保理赔 (seguro e reclamações)](https://www.js-exp.com/index.php?page=29) – 30 dias a partir do recebimento pela JS Express e abertura pela conta do cliente
- [JS Express: Complaint](https://www.js-exp.com/EN/index.php?page=32) – formulário e linha telefônica
- [JS Express: Contact Us](https://www.js-exp.com/EN/index.php?page=35) – linha telefônica, QQ, WeChat, números e endereços antigos
- [JS Express: 联系我们 (contato)](https://www.js-exp.com/index.php?page=35) – central para consultas internacionais, QQ, WeChat e escritórios
- [JS Express: 国际专线 (rotas internacionais)](https://www.js-exp.com/index.php?catid=13) – rota Alemanha (DPD e outras, mais de 98% em 8 dias úteis) e Austrália (Australia Post, Fastway e DPEX)
- [JS Express: 急速美国专线 (rota EUA)](https://www.js-exp.com/index.php?aid=27) – tributos pagos (DDP), devoluções não retornam à China nem são reenviadas e casos de entregue sem recebimento
- [JS Express: 急速欧洲专线 (rota Europa)](https://www.js-exp.com/index.php?aid=35) – países atendidos, tributos pagos (DDP) e códigos postais remotos
- [Aramex Australia: About Aramex Australia](https://www.aramex.com.au/why-aramex/about-us/) – Fastway tornou-se Aramex Australia
- [Revenue: Union Handling Fee press release](https://www.revenue.ie/en/corporate/press-office/press-releases/2026/pr-092926-handling-fee.aspx) – € 2 por categoria de produto desde 1º de novembro de 2026, pago pelo vendedor ou entregadora e passível de repasse
- [USPS: Missing Mail and Lost Packages](https://www.usps.com/help/missing-mail.htm) – busca por remetentes ou destinatários a partir de 7 dias
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formatos de código, textos das leituras e etapas, relógios (as linhas em alemão da J&T Express Alemanha no deslocamento que cada uma informa), leituras do USPS, GOFO, SpeedX, Straightship, Yodel, DHL, iMile, J&T Express Alemanha, FedEx, Colissimo e CTT Express, leituras de hub, depósito, tentativa, redirecionamento e exceção de uma transportadora sem nome, leituras de hub de um parceiro americano sem nome, centrais alemãs da iMile, a consulta pelo navegador, alfândega, recusas e devoluções, o código da transportadora local, o que o Peek mostra e como passa a encomenda a outra transportadora
:::
