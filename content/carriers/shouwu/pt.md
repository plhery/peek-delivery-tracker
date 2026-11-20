---
title: Rastreio Shouwu: número SWECN…YQ, estados e entrega
description: Segue uma encomenda da Shouwu International (守务国际) vinda da China: formato do número SWECN…YQ, significado dos estados e quem entrega na Europa.
slug: shouwu-rastreio
published: 2026-11-18
updated: 2026-11-20
---

A Shouwu International (守务国际, também identificada pela sigla SWE) é uma empresa de logística de Xangai que expede encomendas de vendedores online na China para a Europa e os Estados Unidos, onde um operador postal ou uma transportadora local faz a entrega final. Se uma loja enviou a tua compra pela Shouwu, o número começa por `SWECN` e termina em `YQ`: cola-o na caixa acima ou no portal de rastreio em inglês da Shouwu em [admin.shshouwu.com](https://admin.shshouwu.com/track.html). O código é fornecido pelo vendedor nos dados de envio da encomenda; a Shouwu não disponibiliza qualquer contacto para destinatários.

## Número de rastreio da Shouwu: qual é o formato

As cartas de porte próprias da Shouwu juntam o prefixo `SWECN`, doze algarismos e o sufixo `YQ`, num total de 19 caracteres. Com dígitos de exemplo:

| Número | O que é |
| --- | --- |
| `SWECN482910573649YQ` | `SWECN`, doze algarismos e `YQ`: um número de rastreio da Shouwu International. |
| `SWECN48291057364YQ` | Apenas onze algarismos: não corresponde ao formato da Shouwu. Confirma se ficou um dígito por copiar. |
| `JSEAA4829105736YQ` | Também termina em `YQ`, mas começa por `JSE` com dez algarismos: pertence à [JS Express](carrier:js-express). |

Outros transitários chineses terminam igualmente os seus códigos em `YQ`, mas usam as suas próprias letras iniciais e, no catálogo do Peek, quase sempre dez algarismos pelo meio. A combinação `SWECN` seguida de doze dígitos identifica exclusivamente a Shouwu. Espaços, hífenes e letras minúsculas não afetam a leitura no Peek.

**Onde encontrar o número.** Se não estiver no e-mail de confirmação de expedição nem na página da tua encomenda na loja, vê [como encontrar o número de rastreio](guide:find-tracking-number).

**Podes ter recebido antes o número da transportadora local.** Nas páginas das suas linhas, a Shouwu explica que o vendedor escolhe qual dos códigos entrega ao comprador: o número da própria Shouwu, que acompanha a remessa de ponta a ponta, ou diretamente o número da transportadora encarregada da entrega no país de destino. Por isso, uma encomenda expedida pela Shouwu pode chegar com um código postal ou de transportadora europeia. Cola-o na mesma na caixa acima: o Peek acompanha mais de 3.500 transportadoras.

**Um link de rastreio.** O botão «Copy Link» na página da Shouwu copia um endereço terminado em `?q=` seguido do número. Esse link abre diretamente a encomenda e pode ser colado por inteiro no Peek. O portal da Shouwu aceita até 40 números de uma só vez, um por linha.

## Estados de rastreio da Shouwu explicados

A página de rastreio da Shouwu apresenta-se em inglês sob o título em chinês `包裹追踪`. Para cada encomenda indica um estado geral e o país de destino, seguidos da hora e da descrição de cada registo, mas não mostra o local na tabela.

| Estado da Shouwu | O que significa para ti | O Peek mostra |
| --- | --- | --- |
| `Not Found` | A Shouwu ainda não tem dados para esse código: «We seem can't identify the number.» | «À espera de Shouwu International» |
| `Preparing` | Segundo a explicação da Shouwu, a encomenda foi entregue ao seu armazém na China ou está aí a ser pesada e selada. Pode surgir antes do primeiro registo. | «Etiqueta criada», passando a «Aceite pela transportadora» após a recolha pelo parceiro ou a entrada no armazém |
| `In Transit` | Abrange todo o percurso desde a entrega a uma transportadora até à alfândega e ao encaminhamento dentro do país de destino. | «Em trânsito», ou a etapa concreta do registo mais recente, como «Na alfândega»; mantém «Etiqueta criada» enquanto só existirem os dados do pedido e `Carrier received the shipment` |
| `Undelivered` | Uma tentativa de entrega falhou. | «Tentativa de entrega», ou então «Pronta para levantamento» ou «Devolvida ao remetente» se o último registo o disser |
| `Delivered` | Entregue ao destinatário. | «Entregue» |

A própria Shouwu avisa que estes estados gerais são atribuídos automaticamente pelo sistema («auto-judged by the system») e servem apenas como referência informativa («only for information reference use»). Os registos individuais dizem mais:

| O que escreve a Shouwu | O que significa para ti | O Peek mostra |
| --- | --- | --- |
| `Shipment information received`, `Order information received. We're expecting your parcel to arrive with us.` | O vendedor criou o envio eletronicamente; a Shouwu ainda não tem a encomenda física. | «Etiqueta criada» |
| `Carrier received the shipment` | Linha gerada pela Shouwu cerca de duas horas após os dados da encomenda, dias antes do primeiro registo real em armazém: ninguém recolheu ainda o pacote. | «Etiqueta criada» |
| `Picked up by Shipping Partner` | O parceiro logístico da Shouwu na China recolheu a encomenda junto do vendedor; primeiro movimento físico após os dados do pedido. | «Aceite pela transportadora» |
| `Arrival processing center`, `Arrived at Transit logistics facility` | O armazém da Shouwu na China deu entrada à encomenda. | «Aceite pela transportadora» |
| `Package processing completed` | O processamento no armazém terminou, antes da partida para o voo. | «Aceite pela transportadora» |
| `Item inbound in sorting center`, `Item outbound in sorting center`, `Item is in transit in Hong Kong` | Entrada e saída no centro de triagem e trânsito por Hong Kong a caminho do aeroporto. | «Em trânsito» |
| `Shipment started the customs clearance process at origin`, `Export Clearance` | Começou o desalfandegamento de exportação na China, passo habitual antes do embarque; a alfândega não está a reter a encomenda. | «Em trânsito» |
| `The package is shipped from the transfer warehouse to the airport Export customs clearance in progress` | Saiu do armazém de transferência para o aeroporto enquanto decorre o despacho de exportação na China (não é a alfândega do teu país). | «Em trânsito» |
| `Export customs declaration completed`, `Export customs clearance completed`, `Export Released` | A alfândega chinesa concluiu a exportação sem bloqueios. | «Em trânsito» |
| `The parcel has arrived at the airport and is currently being loaded onto a flight` | No aeroporto de partida após o despacho de exportação, a ser carregada num avião. | «Em trânsito» |
| `Arrived at Shanghai Pudong Airport`, `Departed from export airport`, `The flight has departed` | Chegada ao aeroporto de Xangai Pudong e partida do voo internacional para fora da China. | «Em trânsito» |
| `The ship has already set sail` | Numa linha marítima: o navio já largou da China após a exportação e segue para o porto de destino. | «Em trânsito» |
| `Arrived at airport of Destination`, `Shipment arrived in the destination country`, `Item is in transit in European` | A encomenda aterrou no país de destino ou circula já na Europa antes do desalfandegamento de importação. | «Em trânsito» |
| `NOA received` | O agente no destino recebeu o aviso de chegada da companhia aérea (*Notice of Arrival*); segue-se a alfândega. | «Em trânsito» |
| `Shipment started the customs clearance process at destination`, `Customs clearance in progress`, `Currently undergoing customs clearance in the destination country`, `Currently processing import customs clearance`, `Customs clearance for imports`, `Under Clearance`, `Item has started customs clearance process` | A alfândega do país de destino está a processar a importação. | «Na alfândega» |
| `Custom clearance completed`, `Shipment completed the customs clearance process at destination`, `Customs clearance has been completed`, `Customs clearance in the destination country has been completed` | A alfândega de destino libertou a encomenda. | «Em trânsito» |
| `Scheduled For Delivery` | Registo da Shouwu logo após a libertação aduaneira, dias antes de a encomenda chegar ao centro e à volta do estafeta: a entrega está planeada, ainda não em curso. | «Em trânsito» |
| `Delivery to local courier`, `The goods have been deliverey to the final delivery service provider`, `The goods have been delivered to the carrier` | Entregue à transportadora local que faz a distribuição no destino; ainda não te foi entregue («deliverey» é a grafia original da Shouwu). | «Em trânsito» |
| `Your consignment has been picked up` | A transportadora local levantou a remessa no armazém da Shouwu no estrangeiro após a alfândega; não é a recolha no vendedor (`Picked up by Shipping Partner`) nem um levantamento feito por ti. | «Em trânsito» |
| `Picked up`, `Arrived at FedEx location`, `Departed FedEx location` | Nos EUA, a FedEx recolheu a encomenda no armazém da Shouwu após a importação (indicando localidade e código postal) e encaminha-a nas suas instalações. | «Em trânsito» |
| `Parcel on its way`, `arrival city`, `leave the city` | Encaminhamento nacional pela transportadora final após o desalfandegamento, à chegada e saída de cada cidade. | «Em trânsito» |
| `The shipment has been processed in the destination parcel center` | A DHL triou a encomenda no centro de distribuição que serve a tua morada, antes da saída para entrega. | «Em trânsito» |
| `Mis routed` | Na Alemanha, a transportadora enviou o volume para uma instalação errada e está a reencaminhá-lo: causa atraso, não bloqueio, podendo ainda sair para distribuição no próprio dia. | «Em trânsito» |
| `Out for delivery` | A encomenda saiu para distribuição com a transportadora local. | «Em distribuição» |
| `Abnormal delivery` | A distribuição não conseguiu entregar; o motivo concreto surge num registo separado entre parênteses angulares, como `<Incorrect address.>`. | «Tentativa de entrega» |
| `The recipient is unknown` | Na volta da DHL na Alemanha, o destinatário não foi reconhecido na morada indicada; o pacote segue para devolução. | «Tentativa de entrega» |
| `Shipment will be returned to origin-Bad address` | A DHL está a devolver a encomenda por morada incorreta: vai a caminho da origem, ainda não chegou ao remetente. Fala com o vendedor. | «Problema reportado» |
| `Delivered by post man`, `This parcel has been delivered` | O correio ou a transportadora local entregou a encomenda. | «Entregue», assim que o estado geral da Shouwu passar também a `Delivered` |
| `Shipment sent directly from parcel center to business customer` | A DHL entregou diretamente a partir do centro de encomendas a um cliente empresarial; após uma devolução anterior, a mesma frase significa que o retorno chegou ao remetente. | «Entregue», ou «Devolvida ao remetente» após devolução |

Quando a [iMile](carrier:imile) faz a entrega em Itália, o histórico da Shouwu reproduz as linhas da iMile com o centro entre parênteses retos `【】` (o teu histórico pode indicar outro centro além de `Milan center` ou `ROM 10`):

| O que escreve a Shouwu | O que significa para ti | O Peek mostra |
| --- | --- | --- |
| `【Milan center】Received.` | O centro regional da iMile recebeu a encomenda. | «Em trânsito» |
| `【Milan center】Parcel shipped to【ROM 10】.`, `【ROM 10】Arrived.` | Expedida para a estação de entrega da iMile e recebida nessa unidade. | «Em trânsito» |
| `【ROM 10】Shipment out for delivery.` | Na volta de distribuição da iMile. | «Em distribuição» |
| `<User not at home>`, `【ROM 10】 Back To DS.` | O motorista não encontrou ninguém em casa e a encomenda regressou à estação de distribuição. | «Tentativa de entrega» |
| `【ROM 10】Parcel shipped to【Milan Transit Center】.` | Após duas tentativas falhadas, a estação devolve o volume ao centro de trânsito da iMile. | «Em trânsito» |
| `Back to Sender for reason: wrong recipient address or address related problem` | Depois das tentativas sem sucesso, a iMile inicia a devolução (aqui por problema de morada, podendo aparecer outro motivo após os dois pontos): segue de volta, ainda não está com o remetente. Contacta a loja. | «Problema reportado» |
| `【Milan Transit Center】Order Closed.` | A iMile encerra a ordem sem entregar; contacta o vendedor. | «Problema reportado» |

**Quando entrega a DPD.** Nas linhas entregues pela [DPD](carrier:dpd-de), o histórico continua com as frases em inglês da DPD terminadas por ponto final: `In transit.`, depois `At parcel delivery centre.` — o centro de distribuição da DPD antes da volta e novamente após uma tentativa falhada («Em trânsito») — e `Out for delivery.` («Em distribuição»). `Unfortunately we have not been able to deliver your parcel. Consignee address not correct.` assinala uma tentativa falhada por morada incorreta em que a encomenda volta ao armazém da DPD, não ao remetente («Tentativa de entrega»).

**Linhas em chinês.** Algumas remessas recebem também registos escritos em chinês que o Peek apresenta tal como vêm. `货物电子信息已经收到` (dados eletrónicos da mercadoria recebidos) acompanha os dados iniciais da encomenda: «Etiqueta criada». Seguem-se, todos como «Em trânsito»: `申报过港中` após o código de um lote (declarado para transporte rodoviário até Hong Kong), `已到港，安排航班中` (no ponto de partida, a aguardar voo), `预计23号的航班` (voo reservado, aqui para dia 23; com `二程` atrás quando se refere ao segundo troço aéreo), `航班已飞` e `二程航班已飞` (o voo, ou o segundo troço, partiu) e `已落地` (aterrou). `已落地，清关提货中` (aterrou, em desalfandegamento e recolha junto da companhia aérea) aparece como «Na alfândega», enquanto `清关完成` (desalfandegamento concluído) e `已交仓扫描` (leitura de entrega no armazém da transportadora final) voltam a «Em trânsito».

**Horas desfasadas.** O sistema da Shouwu grava a hora local de quase todos os registos — incluindo os de companhias aéreas e transportadoras no estrangeiro — como se fossem hora de Pequim (`UTC+8`), e o portal converte depois esse valor para o fuso horário do teu navegador. Assim, uma leitura feita às 10:00 em Portugal continental ou na Madeira aparece no site da Shouwu às 02:00 no inverno (`UTC+0`) e às 03:00 durante a hora de verão europeia (`UTC+1`, do último domingo de março ao último domingo de outubro), parecendo oito ou sete horas adiantada (e mais uma hora nos Açores). Guia-te pela ordem das linhas. O Peek calcula o relógio real de cada evento a partir do local ou da etapa da viagem e mostra tudo no fuso horário do teu dispositivo; em peektracker.com e nas aplicações para iPhone e Android, o final do histórico indica «Horas no teu fuso horário: …».

## Quem entrega as encomendas da Shouwu em Portugal e na Europa

Na sua página institucional em chinês, a Shouwu afirma trabalhar diretamente com USPS, Royal Mail, La Poste, DHL, DPD e Hermes. As fichas das suas rotas, também em chinês, indicam os parceiros de último quilómetro nos países para os quais publica linha própria:

- **Portugal (e Brasil).** Em `www.sedel.com`, a Shouwu **não lista nenhuma linha dedicada para Portugal nem para o Brasil e não indica qual a transportadora local** que entrega nesses países; a página de rastreio também não revela o nome nem o código do operador final, salvo quando o próprio texto de um registo o menciona. Pede ao vendedor o nome da transportadora e o respetivo número nacional de seguimento.
- **Outros países da União Europeia.** A Shouwu publica linhas próprias para a Alemanha — entregues sobretudo pela [DHL](carrier:dhl), [Hermes](carrier:hermes-de) ou [DPD](carrier:dpd-de) consoante o serviço —, França (principalmente [Colissimo](carrier:la-poste), surgindo também Colis Privé nalguns históricos), Países Baixos (sobretudo DHL), Suécia (sobretudo Early Bird) e Itália (linha económica de 10 a 12 dias úteis entregue pela UniUni e pela [Poste Italiane](carrier:poste-italiane), além da [iMile](carrier:imile) em históricos lidos pelo Peek).
- **Reino Unido e Estados Unidos.** No Reino Unido entregam [Royal Mail](carrier:royal-mail), Yodel e [Evri](carrier:evri) (grafada `ERVI` no site; a linha para artigos com baterias segue sobretudo pela Royal Mail), e nos Estados Unidos principalmente a [USPS](carrier:usps), além da [FedEx](carrier:fedex) nalguns envios.

**Entregas não conseguidas.** Novas tentativas, avisos e pontos de recolha seguem as regras da transportadora local. Sob `Undelivered`, o portal da Shouwu aconselha a contactar rapidamente a transportadora para reagendar a entrega ou levantar o pacote dentro do prazo, avisando que caso contrário «might be returned to the sender» (poderá ser devolvido ao remetente). Já os termos em chinês das linhas para o Reino Unido e os EUA estipulam que encomendas não entregues não regressam à China e são destruídas por defeito. Em qualquer dos casos, age depressa perante um aviso; vê [entrega falhada: o que fazer](guide:missed-delivery) e [encomendas não levantadas](guide:uncollected-parcel).

## Encomenda da Shouwu atrasada ou rastreio parado

**`Not Found`.** A Shouwu aponta quatro razões possíveis: ainda não aceitou a encomenda, ainda não a digitalizou, o número está incorreto ou o envio é tão antigo que os dados já saíram do sistema, acrescentando que o processamento inicial costuma demorar alguns dias. Aparece «À espera de Shouwu International» enquanto o Peek continua a verificar. Se ao fim de dois dias a Shouwu continuar sem reconhecer o número e nenhuma outra fonte responder por ele, o Peek mostra «Ao fim de dois dias, a transportadora ainda não reconhece este número. Verifica-o, escolhe outra transportadora ou elimina a encomenda.» e passa a consultar uma vez por dia, e semanalmente passado uma semana desde que a adicionaste. Confere o código carácter a carácter e, se nada surgir após alguns dias, contacta o vendedor.

**Parada em `Preparing`, `Shipment information received` ou `Carrier received the shipment`.** A Shouwu ainda não recebeu fisicamente o pacote ou ainda não o expediu do seu armazém na China. Pergunta à loja quando entregou a remessa ao operador.

**Retida na alfândega e taxas em Portugal.** Depois de `Shipment started the customs clearance process at destination`, `Customs clearance in progress`, `Currently undergoing customs clearance in the destination country`, `Currently processing import customs clearance`, `Customs clearance for imports` ou `已落地，清关提货中`, o volume aguarda desalfandegamento no país de destino. As páginas das linhas da Shouwu não esclarecem quem suporta os encargos de importação. Na União Europeia, compras online vindas de fora da UE pagam um direito aduaneiro de 3 € por categoria de produto em remessas até 150 € (desde 1 de julho de 2026) e a taxa de manuseamento da União de 2 € por categoria de produto, independentemente do valor (desde 1 de novembro de 2026). Em **Portugal**, quando o desalfandegamento corre pelos [CTT](carrier:ctt), os CTT indicam a cobrança do IVA de importação, dos 3 € (+ IVA) de direitos aduaneiros em compras até 150 € e, desde 1 de novembro de 2026, da *Taxa de Manuseamento Aduaneiro* de 2 € (+ IVA), alertando ainda que remessas até 150 € sem dados de identificação dos produtos na declaração aduaneira podem ser devolvidas à origem. Em **Portugal**, os CTT avisam também sobre mensagens fraudulentas de *phishing* em seu nome: confirma sempre com atenção o endereço do site, não introduzas dados pessoais ou de pagamento em páginas duvidosas e acede ao desalfandegamento diretamente pelos canais oficiais em `www.ctt.pt`. Mais informação em [encomendas retidas na alfândega](guide:customs).

**Sem atualizações após `Delivery to local courier`.** A transportadora nacional já tem o pacote, mas o portal da Shouwu não fornece o código local. Pede esse número ao vendedor e cola-o no Peek. Consulta também [porque é que o rastreio deixa de atualizar](guide:tracking-not-updating) e [rastrear uma encomenda vinda da China](guide:tracking-from-china).

**Dada como entregue, mas nada chegou.** Os termos das linhas da Shouwu referem que, quando o site da transportadora final indica entrega concluída mas o destinatário não recebeu a encomenda, a Shouwu ajuda a averiguar mas não paga indemnização. Reclama primeiro junto da transportadora local e logo a seguir junto da loja ou plataforma onde compraste. Vê [encomenda dada como entregue mas não recebida](guide:delivered-not-received).

**Perdida ou danificada.** O cliente da Shouwu é o vendedor, que segundo os termos das linhas tem 30 dias após a expedição pela Shouwu para apresentar reclamação. Se um volume entrado no armazém da Shouwu ficar sem registo de entrega, a Shouwu reembolsa ao remetente o valor declarado mais os portes até ao limite de 200 yuans (RMB) por envio, e não indemniza danos ocorridos no transporte. Pede por isso reembolso ou novo envio diretamente à loja ou marketplace sem deixar expirar a proteção ao comprador. Vê [encomendas perdidas](guide:lost-parcel).

## Contacto e apoio ao cliente da Shouwu

O site institucional da Shouwu, `http://www.sedel.com/`, está inteiramente em chinês e destina-se a vendedores que expedem mercadorias. Publica números de telemóvel chineses para a sede em Xangai e cinco filiais, um link de conversação no QQ e códigos QR do WeChat, sem endereço de e-mail, formulário de contacto, atendimento em português ou inglês, nem qualquer canal para destinatários. O portal de rastreio `admin.shshouwu.com` não apresenta contactos.

Para questões sobre a tua encomenda ou um reembolso, fala com o vendedor que contratou o envio; para tratar da entrega final na tua morada, contacta a transportadora local assim que souberes qual é.

## Seguir uma encomenda da Shouwu com o Peek

O [Peek](/) reconhece um número da Shouwu pelo seu formato — `SWECN`, doze algarismos e `YQ`, exclusivo desta transportadora no catálogo — e consulta diretamente o portal da Shouwu sem pedir conta, código postal nem telefone.

O Peek determina a etapa da encomenda a partir do estado da Shouwu e do registo mais recente, conforme as tabelas acima, e mostra o país de destino bem como o local associado a cada leitura, omitido na página da Shouwu. Quando o campo de local inclui algum algarismo, como um código postal, é descartado. A Shouwu não fornece previsão de entrega, pelo que o Peek também não apresenta data estimada.

Sem transportadora final identificada separadamente pela Shouwu, o Peek não exibe uma linha «Entrega por» nem transfere o seguimento de forma automática para outro operador, mesmo quando os registos transcrevem frases da iMile ou da DPD. Se tiveres o código da transportadora local, adiciona-o ao Peek como uma encomenda autónoma.

Enquanto tens a encomenda aberta, ou no dia de entrega com as notificações ativas, o Peek verifica novas leituras até a cada 10 minutos, e até a cada 2 minutos a partir das 6:00 (hora local) no dia em que sai para distribuição quando o portal da Shouwu responde diretamente; fora disso verifica de hora a hora, tal como após dois dias sem novidades ou passadas 12 horas se continuar em «Etiqueta criada» ou «Aceite pela transportadora». Quando a leitura é feita através de serviços externos de seguimento, a verificação ocorre de hora a hora, a cada 3 horas após um dia sem novos registos e, no dia em que a encomenda está em distribuição, até a cada 15 minutos com a encomenda aberta ou as notificações ligadas. Podes escolher alertas para «Todas as atualizações», «Só as importantes» (entrega, levantamento, alfândega, tentativas falhadas e devoluções) ou «Só no dia da entrega». O Peek não pode contactar a Shouwu nem o vendedor em teu nome.

## Perguntas frequentes sobre o rastreio Shouwu

### O que é a Shouwu International (守务国际)?

Uma empresa de logística de Xangai (`上海守务国际物流有限公司`, também abreviada SWE) que transporta encomendas de comércio eletrónico transfronteiriço. Diz ter iniciado atividade em 2018 no frete aéreo internacional e passado em 2019 às linhas de e-commerce para a Europa e os Estados Unidos.

### Onde posso rastrear uma encomenda da Shouwu?

Na caixa no topo desta página ou no portal de rastreio em inglês da Shouwu em `admin.shshouwu.com`. Usa o código completo de 19 caracteres no formato `SWECN…YQ`.

### Quem entrega as encomendas da Shouwu em Portugal?

A Shouwu não lista linha própria para Portugal nem nomeia transportadora portuguesa no seu site (noutros países europeus indica parceiros como Colissimo em França, DHL, Hermes ou DPD na Alemanha e UniUni ou Poste Italiane em Itália). Pergunta ao vendedor que transportadora faz a entrega final e qual o número local.

### Quanto tempo demora uma encomenda da Shouwu a chegar?

Depende da linha contratada pelo vendedor. A Shouwu não publica prazo de referência para Portugal; nas linhas europeias listadas indica 4 a 7 dias úteis para a Alemanha e França, 4 a 8 dias úteis para os Países Baixos e a Suécia e 10 a 12 dias úteis para Itália.

### Quem devo contactar se a encomenda da Shouwu não chegar?

O vendedor onde fizeste a compra: foi ele quem contratou o transporte junto da Shouwu e pode reclamar no prazo de 30 dias após a expedição. A Shouwu não disponibiliza apoio ao cliente para destinatários.

:::sources
- [Shouwu International: 守务国际包裹追踪 (rastreio de encomendas)](https://admin.shshouwu.com/track.html) – portal em inglês, até 40 números, estados e respetivas explicações, registos sem local visível, botão «Copy Link» e `Not Found`
- [Shouwu International: 首页 (página inicial, em chinês)](http://www.sedel.com/) – denominação social, telemóveis na China para Xangai e cinco filiais, chat QQ e códigos WeChat
- [Shouwu International: 关于我们 (sobre nós, em chinês)](http://www.sedel.com/about-us.html) – arranque em 2018 e viragem em 2019 para linhas e-commerce na Europa e nos EUA, parceiros diretos
- [Shouwu International: 主营线路 (linhas principais, em chinês)](http://www.sedel.com/main-route.html) – países com linha publicada (sem rota listada para Portugal)
- [Shouwu International: 英国标快 (Reino Unido expresso padrão, em chinês)](http://www.sedel.com/main-route-secondary.html?groupId=1&groupItemId=2) – Royal Mail, Yodel e ERVI, escolha do número, 5 a 8 dias úteis, sem devolução à China e regras de reclamação (30 dias, limite de 200 RMB)
- [Shouwu International: 英国经济 (Reino Unido económico, em chinês)](http://www.sedel.com/main-route-secondary.html?groupId=2&groupItemId=3) – 10 a 12 dias úteis
- [Shouwu International: 英国带电 (Reino Unido com baterias, em chinês)](http://www.sedel.com/main-route-secondary.html?groupId=3&groupItemId=4) – entrega sobretudo pela Royal Mail
- [Shouwu International: 美国标快 (EUA expresso padrão, em chinês)](http://www.sedel.com/main-route-secondary.html?groupId=1&groupItemId=1) – entrega sobretudo pela USPS, 6 a 8 dias úteis, sem devolução à China e reclamações
- [Shouwu International: 美国经济 (EUA económico, em chinês)](http://www.sedel.com/main-route-secondary.html?groupId=2&groupItemId=21) – 8 a 12 dias úteis
- [Shouwu International: 德国标快 (Alemanha expresso padrão, em chinês)](http://www.sedel.com/main-route-secondary.html?groupId=1&groupItemId=19) – sobretudo DHL, escolha do número, 4 a 7 dias úteis e reclamações
- [Shouwu International: 德国经济 (Alemanha económico, em chinês)](http://www.sedel.com/main-route-secondary.html?groupId=2&groupItemId=22) – sobretudo Hermes, 4 a 7 dias úteis
- [Shouwu International: 德国带电 (Alemanha com baterias, em chinês)](http://www.sedel.com/main-route-secondary.html?groupId=3&groupItemId=25) – sobretudo DPD, 4 a 7 dias úteis
- [Shouwu International: 法国标快 (França expresso padrão, em chinês)](http://www.sedel.com/main-route-secondary.html?groupId=1&groupItemId=20) – sobretudo Colissimo, 4 a 7 dias úteis
- [Shouwu International: 荷兰标快 (Países Baixos expresso padrão, em chinês)](http://www.sedel.com/main-route-secondary.html?groupId=4&groupItemId=28) – sobretudo DHL, 4 a 8 dias úteis
- [Shouwu International: 瑞典经济 (Suécia económico, em chinês)](http://www.sedel.com/main-route-secondary.html?groupId=4&groupItemId=29) – sobretudo EarlyBird, 4 a 8 dias úteis
- [Shouwu International: 意大利经济 (Itália económico, em chinês)](http://www.sedel.com/main-route-secondary.html?groupId=4&groupItemId=27) – UniUni e Poste Italiane, escolha do número e 10 a 12 dias úteis
- [Comissão Europeia: EU Customs Reform (em inglês)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – direito aduaneiro de 3 € desde 1 de julho de 2026 e taxa de manuseamento de 2 € desde 1 de novembro de 2026
- [Comissão Europeia: Guidance on the 3 EUR customs duty and Union handling fee (PDF, em inglês)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – aplicação da taxa de manuseamento de 2 € por categoria de produto em qualquer valor
- [CTT: Fim da isenção de direitos aduaneiros em compras até 150 € fora da UE](https://www.ctt.pt/particulares/receber/desalfandegar/fim-da-isencao-de-direitos-aduaneiros-em-compras-ate-150-euros-fora-da-uniao-europeia) – cobrança em Portugal de IVA, 3 € (+ IVA), Taxa de Manuseamento Aduaneiro de 2 € (+ IVA) desde 1 de novembro de 2026 e devolução por falta de identificação dos produtos
- [CTT: Alertas de Phishing](https://www.ctt.pt/transversais/alertas-de-phishing) – recomendações em Portugal para verificar o endereço do site e aceder diretamente a `www.ctt.pt`
- [Comissão Europeia: Summertime (em inglês)](https://transport.ec.europa.eu/transport-themes/summertime_en) – hora de verão europeia entre o último domingo de março e o último domingo de outubro
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formato `SWECN…YQ`, portal e link, estados, descrições dos registos e etapas (incluindo exportação e importação, carregamento no aeroporto, recolha pelo parceiro, recolha e centros FedEx, partida marítima, entrega empresarial DHL, linhas iMile e DPD e trânsito `arrival city` / `leave the city`), relógio de Pequim, locais e o que o Peek mostra
:::
