---
title: Rastreamento HuaHan Logistics: códigos HHW…YQ e entrega
description: Rastreie um pacote da HuaHan Logistics (华翰物流) da China: formato do código HHW…YQ, significado dos status e quem entrega no seu país.
slug: rastreamento-huahan-logistics
published: 2026-11-24
updated: 2026-11-24
---

A HuaHan Logistics (华翰物流, também chamada de HuaHan Express ou 华翰速递) é uma transportadora chinesa que despacha pacotes por via aérea a partir da China e os entrega a uma transportadora local no país de destino para a etapa final. Se uma loja enviou seu pedido pela HuaHan, procure um código iniciado em `HHW` e terminado em `YQ` e cole-o na caixa acima. O rastreador da própria HuaHan fica em hh-exp.cn e pode informar qual transportadora entrega na sua região, junto com o código usado por ela para o seu pacote.

## Código de rastreamento HuaHan: como reconhecer

O Peek reconhece os códigos da HuaHan no formato `HHW`, duas letras, dez dígitos e `YQ`, somando 17 caracteres. Com dígitos inventados:

| Código | O que é |
| --- | --- |
| `HHWCN4829105736YQ` | `HHW`, duas letras, dez dígitos e `YQ`: um código da HuaHan. |
| `HHWCN482910573YQ` | Apenas nove dígitos: não segue o formato da HuaHan. Veja se falta um dígito. |
| `WNBAA4829105736YQ` | Também termina em `YQ`, mas começa com `WNB`: código da [WanbExpress](carrier:wanbexpress). |
| `JXCAA4829105736YQ` | Começa com `JXC`: código da Juxi Express. |

Muitas operadoras chinesas terminam seus códigos com `YQ`, cada uma com seu prefixo próprio: `HHW` identifica a HuaHan, e nenhuma outra transportadora no catálogo do Peek usa esse prefixo. Espaços, traços e letras minúsculas não fazem diferença ao colar o código no Peek.

**Onde encontrar.** A loja informa o número no e-mail de envio ou na página do pedido. Se não conseguir achar, veja [como encontrar o código de rastreamento](guide:find-tracking-number).

**Um segundo código.** O rastreador da HuaHan também pode mostrar o código da transportadora local para o pacote. Às vezes a HuaHan acrescenta um sufixo após um sublinhado (`_`) que não faz parte do número da outra transportadora: retire esse trecho ao consultar no site dela.

## Status do rastreamento HuaHan explicados

O rastreador da HuaHan lista cada movimentação com horário, texto e local, do mais recente ao mais antigo. Os eventos próprios dela estão em inglês, alguns precedidos pela versão em chinês. Aqui estão eles na ordem em que o código do Peek os organiza:

| A HuaHan mostra | O que significa para você | O Peek mostra |
| --- | --- | --- |
| `快件电子信息已经收到 Shipment information received`, `Shipment information received` | A loja enviou os dados do pacote à HuaHan. A HuaHan ainda não está com o pacote. | "Etiqueta criada" |
| `快件到达作业中心 Shipment arrived at facility`, `Has entered the warehouse` | O centro da HuaHan na China recebeu o pacote — nos históricos presentes no código do Peek, em Shenzhen. | "Recebida pela transportadora" |
| `快件操作完成 Shipment departed from facility` | Processado e despachado desse centro. | "Em trânsito" |
| `Transfer to Origin airport` | A caminho do aeroporto de origem. | "Em trânsito" |
| `Export customs clearance completed` | Desembaraço aduaneiro de exportação concluído na saída. Ainda não é a alfândega do seu país. | "Em trânsito" |
| `Departed from airport of Origin` | O voo partiu. | "Em trânsito" |
| `Import Customs clearance started` | A alfândega do país de destino está com o pacote. | "Na alfândega" |
| `Arrived at destination airport` | O voo pousou no país de destino. | "Em trânsito" |
| `Import Customs clearance completed` | A alfândega de importação liberou o pacote. | "Em trânsito" |
| `Has been given to local carrier` | A HuaHan repassou o pacote à transportadora que fará a entrega. A partir daqui entram os registros dela. | "Em trânsito" |

**Registros da transportadora local.** Após a entrega à parceira, o rastreador da HuaHan repassa os eventos da transportadora local. O Peek os interpreta pelo código interno da HuaHan quando existe, ou pelo próprio texto. Textos encontrados no código do Peek e atribuídos à transportadora local:

| Após o repasse | O que significa para você | O Peek mostra |
| --- | --- | --- |
| `Ready to Ship, Parcel received and processed` | A transportadora local recebeu o pacote. | "Em trânsito" |
| `The parcel is in the central warehouse waiting to be processed.`, `In Transit, Shipment arrived to sorting centre / Shipment in the sorting centre`, `In Transit, Shipment remained at sorting hub` | No depósito central ou centro de triagem da transportadora local, ou ainda aguardando ali. | "Em trânsito" |
| `In Transit, Arrived to HU HUB`, `In Transit, Parcel has entered the Linehaul network.` | No hub da transportadora local na Hungria e depois em deslocamento entre hubs. | "Em trânsito" |
| `In Transit, Picked Up` (seguido do termo alemão `Einrollung` da DPD), `In Transit, Scanned By DPD At parcel delivery centre.`, `In Transit, At parcel delivery centre.` | No depósito de entrega da DPD: é o registro de entrada lá — não uma retirada sua — seguido de um registro a cada dia em que o pacote permanece no depósito. | "Em trânsito" |
| `Out for Delivery, Parcel is at courier, delivery expected soon.` | Em rota de entrega. | "Saiu para entrega" |
| `The courier will deliver the parcel soon. Additional details are available in the SAMEDAY app, email or SMS.` | Em rota de entrega, com o texto da Sameday, transportadora da Romênia e da Bulgária. | "Saiu para entrega" |
| `Delivery Attempted`, `Delivery Attempted, Unfortunately we have not been able to deliver your parcel. Pickup parcelshop delivery after first delivery attempt.` | A DPD tentou entregar e não conseguiu. Na linha mais longa, o pacote segue para um ponto de retirada. | "Tentativa de entrega" |
| `Awaiting Pickup, Parcel was deposited in Pickup parcel station` | Após essa tentativa, a DPD deixou o pacote em um ponto de retirada para você buscar. | "Pronta para retirada" |
| `The parcel has been successfully delivered.` | Entregue. | "Entregue" |

A HuaHan agrupa os eventos de saída para entrega, ponto de retirada e entrega da transportadora local sob códigos próprios, e o Peek os exibe como "Saiu para entrega", "Pronta para retirada" ou "Entregue", salvo quando o texto indica outra etapa: a HuaHan também coloca a saída de um centro de distribuição da USPS sob seu código de saída para entrega.

**Duas mensagens que confundem.** `We will pick up your parcel from the sender soon.` pode surgir logo no começo, antes mesmo de a HuaHan receber o pacote na China: é o aviso da transportadora local de que recebeu os dados do pacote, não uma coleta com você. O Peek mostra "Etiqueta criada". `The parcel was redirected. As soon as it is in the couriers possession, it will be processed as requested.` é a transportadora registrando um redirecionamento pedido por você, também enquanto o pacote ainda está na China. Isso não move nada e o Peek não cria uma etapa própria para esse aviso.

**Dados eletrônicos, não movimentação.** `The instruction data for this shipment have been provided by the sender to DHL electronically.`, às vezes sem ponto final, é o registro eletrônico enviado à DHL para o trecho final, repassado antes de a HuaHan estar com o pacote na China ou depois da saída de lá. `货物电子信息已经收到` (dados eletrônicos da mercadoria recebidos), em chinês, corresponde aos dados do pedido e pode aparecer depois de a HuaHan já ter recebido o pacote. Nenhum dos dois indica movimentação: o Peek mostra "Etiqueta criada" ou mantém o status anterior.

**Uma leitura, duas linhas.** O rastreador da HuaHan às vezes lista o mesmo evento da transportadora local duas vezes no mesmo minuto: uma com o texto e a localidade da parceira e outra sem localidade no texto genérico da HuaHan, como `Delivered, Parcel was delivered successfully`. O Peek mantém a linha com a localidade.

**Por correio postal.** A HuaHan também pode despachar pelo China Post. Nesse caso, seu rastreador repassa os registros do China Post em chinês, com a agência entre 【】, e depois os registros dos correios de destino copiados pelo China Post. O Peek os exibe em chinês, conforme repassados pela HuaHan:

| A HuaHan repassa | O que significa para você | O Peek mostra |
| --- | --- | --- |
| `中国邮政已收取邮件` | O China Post recebeu o pacote da HuaHan: daqui em diante segue pelo serviço postal. | "Em trânsito" |
| `邮件已在【…】完成分拣，准备发出`, `邮件到达【…】`, `邮件离开【…】，正在发往【…】`, `邮件离开【…】，正在发往下一站` | Triado, recebido ou saindo de uma unidade do China Post na China, nomeada entre 【】. | "Em trânsito" |
| `送交出口海关` | Entregue à alfândega de exportação na China. Não é a alfândega do seu país. | "Na alfândega" |
| `出口海关/放行` | Liberado pela alfândega de exportação. | "Em trânsito" |
| `已交承运商运输` | Entregue à companhia aérea que o leva ao exterior. | "Em trânsito" |
| `到达寄达地`, `到达寄达地处理中心`, `到达境外经转局` | Chegou ao país de destino, ao centro de tratamento postal local ou a uma unidade de trânsito no caminho. | "Em trânsito" |
| `境外进口海关留存待验(等待提交给边境机构/安全部门)` | Retido para a alfândega de importação do seu país; os parênteses dizem que aguarda apresentação às autoridades de fronteira ou segurança. | "Na alfândega" |
| `境外进口互换局留存`, `送交境外进口海关` | Mantido na unidade internacional dos correios de destino e depois enviado à alfândega de importação. | "Na alfândega" |
| `寄达地海关允许进口（无需缴税）` | Liberado pela alfândega de importação, sem imposto a pagar. | "Em trânsito" |
| `离开【…】处理中心`, `已到达【…】投递局` | Saiu do centro de tratamento no país entre 【】 e chegou à unidade de entrega. | "Em trânsito" |

**Via Landmark Global.** Em alguns envios, o rastreador da HuaHan repassa em inglês os registros da Landmark Global. Os dois primeiros surgem antes de a HuaHan receber o pacote na China; os demais vêm após o repasse da Landmark Global para a rede da bpost, que conclui a entrega:

| A HuaHan repassa | O que significa para você | O Peek mostra |
| --- | --- | --- |
| `Shipment Data Uploaded` | Registro dos dados do remetente pela Landmark Global. Não é movimentação. | "Etiqueta criada" |
| `Processed` | Processamento eletrônico da Landmark Global, no mesmo minuto do registro anterior. Não é recebimento físico: é um aviso, e o Peek mantém a etapa anterior. | A etapa anterior |
| `Incoming scan at facility` | Um depósito recebeu o pacote após o repasse para a rede da bpost. | "Em trânsito" |
| `Outgoing scan at facility` | Saiu desse depósito. A entrega ainda pode levar dias. | "Em trânsito" |
| `Out for delivery (On the way)` | Em rota de entrega. | "Saiu para entrega" |
| `Delivery attempt (Delivery not possible)` | Tentativa de entrega sem sucesso. | "Tentativa de entrega" |

A HuaHan enquadra `Outgoing scan at facility` em seu código de saída para entrega, mas o texto indica saída de depósito, então o Peek exibe "Em trânsito".

## Quem entrega pacotes da HuaHan

Depois de `Has been given to local carrier`, uma transportadora do país de destino faz a entrega, e o rastreador da HuaHan pode informar o nome e o número dela.

**O que o código do Peek mostra.** Um histórico da HuaHan mostra a Sameday entregando, com menção ao aplicativo SAMEDAY na linha de saída para entrega. O código do Peek também trata números da [USPS](carrier:usps) informados pela HuaHan: quando ela exibe o código longo da etiqueta da USPS, o Peek extrai o código de rastreamento próprio da USPS. A [DHL](carrier:dhl) aparece reservada para o trecho final de alguns pacotes (a linha de dados eletrônicos acima vem dela). A DPD entrega outros, com registros de depósito como `In Transit, Scanned By DPD At parcel delivery centre.`. A [CTT Express](carrier:ctt-express) é anunciada no trecho final de outros envios: o rastreador da HuaHan repassa o aviso `Pendiente de recepción en CTT Express` ("aguardando recebimento na CTT Express") minutos após a chegada na China, no horário de Madri, e o Peek mostra "Etiqueta criada". Outros pacotes seguem via Landmark Global, com a rede da bpost entregando após o repasse (veja "Via Landmark Global" acima). Fora esses casos, o código não fixa uma transportadora por país: o Peek segue aquela que a HuaHan indicar para o seu envio.

**Tentativas de entrega e retirada.** Tentativas, avisos de ausência, local e prazo de guarda seguem as regras da transportadora local: procure essa empresa com o código dela para reagendar a entrega. Veja [entrega perdida](guide:missed-delivery) e [encomendas não retiradas](guide:uncollected-parcel).

**Alfândega na UE.** Compras online vindas de fora da União Europeia pagam imposto aduaneiro de € 3 por item em pacotes de até € 150 desde 1º de julho de 2026 e uma taxa de manuseio da União de € 2 por item, qualquer que seja o valor, desde 1º de novembro de 2026. Um item reúne mercadorias com a mesma classificação tarifária, descrição e, quando exigido, origem. O Peek não pôde confirmar se a HuaHan cobra isso do vendedor antes do envio: pergunte à loja se seu pagamento já incluiu esses valores. Em outros países, a cobrança depende das regras locais. Veja [encomendas paradas na alfândega](guide:customs).

## Pacote HuaHan atrasado ou rastreamento parado

**Código não encontrado.** Quando a HuaHan não encontra registro, seu rastreador responde 没有找到记录 ("nenhum registro encontrado"), e o Peek exibe "Aguardando HuaHan Logistics" enquanto continua verificando. Se após dois dias a HuaHan ainda não reconhecer o número e nenhum outro serviço responder, o Peek mostra "Depois de dois dias, a transportadora ainda não reconhece este código. Confira o código, escolha outra transportadora ou exclua a encomenda." Confira o número caractere por caractere na mensagem do vendedor: `HHW`, duas letras, dez dígitos e `YQ`. Se nada aparecer após alguns dias, confirme com o vendedor se o pacote foi enviado.

**Parado em `Shipment information received`.** A loja reservou o envio, mas a HuaHan ainda não bipou o pacote. Pergunte à loja quando ele foi entregue à transportadora.

**Parado em `Import Customs clearance started`.** O pacote está na alfândega do seu país. Se cobrarem encargos de importação, veja [encomendas paradas na alfândega](guide:customs); se permanecer assim por muito tempo, avise o vendedor.

**Nada após `Has been given to local carrier`.** A transportadora local já está com o pacote, e o rastreamento dela pode estar mais atualizado que o da HuaHan. Procure o nome e o código dela no rastreador da HuaHan ou no cartão do pacote no Peek (quando o Peek a reconhecer), ou pergunte ao vendedor quem entrega.

**Dias sem atualização.** Quando um pacote ainda em viagem passa quatro dias sem novo registro, o Peek sinaliza com a data da última movimentação ("Sem atualizações desde seg., 12 de out.") e explica que pacotes costumam ficar quietos em voos longos, na alfândega ou no fim de semana; após três semanas sem novidade, recomenda falar com o vendedor. Veja [por que o rastreamento para de atualizar](guide:tracking-not-updating) e [como rastrear encomendas da China](guide:tracking-from-china).

**Entregue, mas não chegou.** Confira onde foi deixado, pergunte aos vizinhos e depois fale com a transportadora local e com o vendedor. Veja [entregue, mas não recebido](guide:delivered-not-received).

**Extraviado ou devolvido.** Como o vendedor contratou o frete com a HuaHan, peça reembolso ou reenvio ao vendedor ou ao marketplace. Veja [encomendas extraviadas](guide:lost-parcel) e [devolvida ao remetente](guide:returned-to-sender).

## Telefone e atendimento da HuaHan

Em seu site ([hh-exp.com/lxwm](https://www.hh-exp.com/lxwm)), a HuaHan informa uma linha direta na **China**, `400-015-6080`, sem horários publicados, além da matriz em Shenzhen; não informa e-mail de suporte para destinatários. Foi o vendedor quem contratou a HuaHan, então fale com ele sobre o pedido — ou com o marketplace se não responder. Sobre a entrega final, fale com a transportadora local.

## Como rastrear um pacote da HuaHan no Peek

O [Peek](/) identifica a HuaHan Logistics de imediato a partir de um código `HHW…YQ` ("Transportadora detectada") e consulta o rastreador da HuaHan usando apenas esse número completo: não precisa de conta, CEP nem telefone. Se tiver apenas o número do pedido, peça ao vendedor o código `HHW…YQ`. Se só tiver o código da transportadora local, cole-o diretamente: o Peek acompanha mais de 4.500 transportadoras.

O Peek mostra o status, cada registro com sua localidade e o país de destino informado pela HuaHan. O rastreador da HuaHan imprime horários sem fuso horário. O Peek lê os registros da própria HuaHan no relógio do local ou trecho em que o pacote se encontra (horário de Pequim na China). Os eventos da transportadora local após `Has been given to local carrier` são lidos no horário do seu país quando ele tem um único fuso. Todos os horários são convertidos para o fuso do seu aparelho. A HuaHan não informa data prevista de entrega nem peso, então o Peek também não mostra esses dados.

Quando a HuaHan aponta uma transportadora local reconhecida pelo Peek junto com um código no formato dela, o cartão do pacote informa isso ("Entrega por USPS", por exemplo, ou "Anunciada a USPS, ainda não recebida" enquanto ela só tiver a pré-notificação, sem registro físico próprio) e exibe o número dela depois do da HuaHan. O Peek passa então a consultar essa transportadora também. Se a HuaHan informar um código postal (duas letras, nove dígitos e duas letras) sem uma transportadora conhecida pelo Peek, ele consulta os correios do país de destino; para outro número, consulta a transportadora única indicada pelo formato. Assim que essa transportadora registra um evento pelo menos tão recente quanto o último da HuaHan, o Peek passa a acompanhar lá mantendo o histórico da HuaHan. Se a HuaHan não fornecer número local, o Peek segue no rastreador da HuaHan, que continua repassando os eventos da parceira.

Quando o rastreador da HuaHan está inacessível, o Peek recorre a serviços de rastreamento e exibe o histórico deles, nas palavras deles.

Enquanto o pacote estiver aberto, ou no dia da entrega com alertas ligados, o Peek verifica até a cada 10 minutos, e até a cada 2 minutos a partir das 6h no horário local no dia da saída para entrega (a cada 5 minutos quando a consulta abre navegador), enquanto o rastreador da HuaHan responder; fora disso, de hora em hora. Por serviços de rastreamento, verifica de hora em hora, a cada 3 horas após um dia sem movimento e até a cada 15 minutos no dia da entrega enquanto o pacote estiver aberto ou com alertas ligados. Quando outra transportadora assume a entrega, o Peek segue o ritmo dela. Após dois dias sem novidade (12 horas enquanto ainda mostra "Etiqueta criada" ou "Recebida pela transportadora"), verifica de hora em hora. Ele pode avisar em "Cada movimentação", "Etapas importantes" ou "Só a entrega". "Etapas importantes" inclui `Import Customs clearance started`, os registros aduaneiros do China Post repassados em chinês quando o envio segue por correio (como `送交出口海关` e `送交境外进口海关`), a saída para entrega, ponto de retirada e entrega — mas não voos nem o desembaraço de exportação da própria HuaHan: `快件从转运仓发出至机场，出口报关中（ The package is shipped from the transfer warehouse to the airport Export customs clearance in progress ）` é etapa rotineira antes do voo e aparece como "Em trânsito". O Peek não pode falar com a HuaHan nem com o vendedor por você.

## Perguntas sobre o rastreamento HuaHan

### O que é a HuaHan Logistics (华翰物流)?

É uma transportadora chinesa, também chamada de HuaHan Express ou 华翰速递, que envia pacotes para o exterior. Nos históricos presentes no código do Peek, os registros começam no centro de Shenzhen, acompanham voo e alfândega e terminam com os eventos da transportadora local que entrega no país de destino.

### Onde posso rastrear um pacote da HuaHan?

Na caixa acima ou no rastreador da própria HuaHan em hh-exp.cn. Esse site pede para passar por uma verificação antes: clicar em ordem ou resolver uma soma simples. Use o código `HHW…YQ` completo.

### O que significa "Has been given to local carrier" no rastreamento da HuaHan?

Que a alfândega de importação liberou o pacote e a HuaHan o repassou à transportadora que fará a entrega no seu país. Os eventos dessa transportadora passam a aparecer no histórico da HuaHan.

### Quem entrega pacotes da HuaHan no meu país?

Uma transportadora local, que o rastreador da HuaHan pode indicar junto com o número dela. O código do Peek mostra a Sameday em um histórico e registros de depósito da DPD, lê números da USPS, traz dados da DHL e o aviso da CTT Express no trecho final de alguns envios, e repassa eventos da Landmark Global com entrega final pela rede da bpost em outros: confira no rastreador da HuaHan ou pergunte ao vendedor.

### Quem devo contatar se meu pacote da HuaHan sumir?

O vendedor de quem você comprou: foi ele quem contratou o frete com a HuaHan. A linha telefônica da própria HuaHan fica na China (`400-015-6080`).

:::sources
- [Comissão Europeia: Reforma aduaneira da UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – imposto aduaneiro de € 3 por item até € 150 desde 1º de julho de 2026 e taxa de manuseio da União de € 2 desde 1º de novembro de 2026
- [Comissão Europeia: Orientação sobre o imposto de € 3 e a taxa de manuseio da União (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – € 2 por item qualquer que seja o valor e definição de item
- [HuaHan Express: 联系我们 (contato em chinês)](https://www.hh-exp.com/lxwm) – matriz em Shenzhen e linha oficial 400-015-6080
:::
