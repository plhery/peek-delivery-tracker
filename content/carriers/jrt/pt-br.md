---
title: Rastreamento JRT: códigos JRT…MM, voos e entrega DHL
description: Rastreie encomendas JRT (今日通, Jinritong) de Xiamen, China: formato JRT…MM, o que significam os status em chinês e inglês e quem entrega no seu país.
slug: rastreamento-jrt
published: 2026-11-24
updated: 2026-11-24
---

A JRT (今日通, Jinritong) é a 厦门今日通商贸有限公司, uma transportadora chinesa sediada em Xiamen, China. Se uma loja chinesa enviou seu pedido pela JRT, ela fornece um código que começa com `JRT` e termina com `MM`: cole esse código na caixa acima. O rastreador da JRT acompanha a encomenda desde a China, passando pelo voo e pela alfândega até a empresa responsável pela entrega final: na Europa, a DHL, e nos Estados Unidos, a UPS, cujas leituras ele replica. Já a linha FedEx da JRT para a Irlanda termina na FedEx, a linha para a Espanha na CTT Express, a linha para a Polônia na InPost e a linha para o México na J&T Express México.

## Código de rastreamento da JRT: qual é o formato

Os códigos próprios da JRT têm `JRT`, dez dígitos e `MM`, totalizando 15 caracteres; `JRT` são as iniciais de 今日通. Com dígitos fictícios:

| Código | O que é |
| --- | --- |
| `JRT4829173605MM` | `JRT`, dez dígitos e `MM`: código de rastreamento da JRT. |
| `JRT482917360MM` | Apenas nove dígitos: não segue o formato da JRT. Verifique se falta um dígito. |
| `JRTO4829173605MM` | Uma letra `O` depois de `JRT`: o Peek lê como `JRT4829173605MM`, único formato reconhecido pelo rastreador da JRT. |
| `UP4829173605841926` | `UP` e dezesseis dígitos: referência do remetente que pode localizar um pedido JRT. Nada indica que apenas remetentes da JRT usem esse padrão. |
| `RR482917367CN` | Duas letras, nove dígitos e `CN`: código da [China Post](carrier:china-post), não da JRT. |

Espaços e letras minúsculas não fazem diferença no Peek. Não encontra o código? Veja [como encontrar seu código de rastreamento](guide:find-tracking-number). O rastreador da JRT também localiza pedidos pela referência interna do vendedor, digitada exatamente como fornecida, inclusive com hifens.

**Um link com números em vez de nome.** O rastreador da JRT opera em um endereço numérico de servidor sobre HTTP simples, sem domínio web próprio, e o Peek não conhece outro site oficial da JRT. Um link para ele traz esse endereço numérico no lugar do nome do site e contém `trackIndex.htm`, `documentCode=` e o código ou referência: você pode colá-lo no Peek exatamente como recebeu. O próprio Peek não coloca links para esse rastreador, pois HTTP sem criptografia enviaria seu código em texto aberto.

## Status de rastreamento da JRT explicados

Muitas linhas da JRT aparecem em chinês seguidas do inglês da própria JRT, e o Peek exibe o texto completo. Na ordem em que uma encomenda costuma recebê-las:

| A JRT diz | O que significa para você | O Peek mostra |
| --- | --- | --- |
| `货物电子信息已经收到` | "As informações eletrônicas da carga foram recebidas": o vendedor criou o pedido. Mensagem gerada pela Hualei (华磊), plataforma por trás do rastreador da JRT, não uma leitura física. | "Etiqueta criada" |
| `Shipment information received` | Os dados da remessa chegaram à JRT; ninguém escaneou o pacote ainda. | "Etiqueta criada" |
| `到达收货点` | Chegou ao ponto de recebimento da JRT: primeira leitura física nas mãos da JRT. | "Recebida pela transportadora" |
| `货物离开操作中心` | A encomenda saiu do centro operacional da JRT. | "Em trânsito" |
| `深圳集散中心 已收货` | O hub da JRT em Shenzhen recebeu o pacote após sair do centro operacional e antes do voo. O Peek exibe em inglês: "Received at the Shenzhen distribution center". | "Em trânsito" |
| `货物打包完成，已发往航司` | Consolidado e enviado à companhia aérea antes do voo. O Peek mostra "Goods packed and sent to the airline". | "Em trânsito" |
| `抵达航司已起飞` | Já com a companhia aérea e o voo decolou do aeroporto indicado na linha. O Peek exibe "Arrived at the airline, flight departed". | "Em trânsito" |
| `货物已交机场,等待航班安排中 Cargo delivered to airport, pending flight arrangement` | Entregue no aeroporto aguardando alocação de voo. "Delivered" significa entregue ao aeroporto, não a você. | "Em trânsito" |
| `报关已放行等待…号航班起飞 Customs released,Awaiting departure on flight of the …` | Liberado pela alfândega de exportação da China, aguardando o voo do dia indicado nas reticências. | "Em trânsito" |
| `航班起飞，以实际抵达为准 Flight departure subject to actual arrival time` | O voo decolou. | "Em trânsito" |
| `航班落地，拆板中 Flight landing, dismantling board in progress` | Pousou. Os paletes estão sendo desmontados antes da alfândega. | "Em trânsito" |
| `机场内等待提货，等待清关 Waiting for cargo pickup and customs clearance at the airport.` | No aeroporto de destino, aguardando retirada da carga e alfândega de importação. | "Na alfândega" |
| `理货较慢，导致提货缓慢，已催促尽快处理 Slow cargo sorting has resulted in delayed pickup. We have followed up for prompt handling.` | A triagem no aeroporto está lenta, atrasando a retirada da carga, e a JRT cobrou agilidade. É um atraso, não extravio. | Sem mudança de status: mantém o anterior |
| `已提货，清关中 The goods have been picked up and are currently undergoing customs clearance` | Desembaraço aduaneiro de importação em andamento. | "Na alfândega" |
| `清关完成，卡车发运 Customs clearance completed; truck dispatched.` | Liberado pela alfândega e embarcado em caminhão. | "Em trânsito" |
| `已交服务商 Delivered to service provider` | Entregue à empresa que fará a entrega final. Ainda não foi entregue a você. | "Em trânsito" |

Algumas linhas que a JRT escreve apenas em chinês aparecem no Peek nas traduções em inglês adotadas por outras operadoras: `货物电子信息已经收到` como "The electronic cargo information has been received", `到达收货点` como "Arrived at the receiving point", `货物离开操作中心` como "Goods leave the operation center" e `快件操作完成` como "Shipment departed from facility". A linha do aeroporto que agenda um voo, `货物已交机场,预计…号的航班 Goods delivered to airport, scheduled on flight of the …`, cita o dia do voo nas duas partes; o Peek exibe "Goods delivered to airport", pois o voo ainda vai partir. Mais três linhas apenas em chinês surgem antes do voo, todas em "Em trânsito": `分拣包裹已出库` como "Sorted parcel has left the warehouse" (após o centro operacional), `货物到达口岸，预计…号航班起飞` como "Arrived at the port, booked on a flight" (no porto de saída, com a data completa do voo nas reticências) e `预计…号航班飞` como "Booked on a flight" (mês.dia nas reticências).

Na rota para a Europa, outras três linhas chegam apenas em chinês e o Peek as traduz para inglês, todas em "Em trânsito": `航班已抵达` como "Flight has arrived" (pouso no aeroporto europeu citado), `清关完成，中转德国中` como "Customs cleared, in transit to Germany" (desembaraçado no aeroporto de chegada, seguindo para repasse na Alemanha) e `抵达德国，已交服务商` como "Arrived in Germany, handed to the delivering carrier" (entregue à transportadora final, como a DHL; não é entrega ao destinatário).

**Por rodovia via Cazaquistão.** Na linha rodoviária da JRT para a Europa, o pacote deixa a China por terra em vez de avião, com cinco linhas apenas em chinês exibidas em inglês pelo Peek, todas em "Em trânsito": `已装柜发车，待抵达新疆` como "Loaded into a container and on its way to Xinjiang" (após o hub de Shenzhen), `已抵达新疆，换装/报关，待出境` como "Arrived in Xinjiang for transloading and export declaration, waiting to leave China", `因哈萨克斯坦拥堵，外车入境新疆拥堵缓慢，在等待外车倒装后报关出境。` como "Congestion in Kazakhstan is slowing foreign trucks into Xinjiang; the parcel will be declared for export once it is reloaded onto one" (espera na fronteira, não extravio), `已出境，哈萨克斯坦阿拉木图` como "Left China, Almaty, Kazakhstan" (já fora da China, em Almaty) e `哈俄边境，待办理手续进入俄罗斯` como "At the Kazakhstan–Russia border, awaiting the formalities to enter Russia" (fronteira de trânsito, não a alfândega do seu país).

**Linhas apenas em inglês.** Outras rotas da JRT trazem eventos somente em inglês, todos como "Em trânsito" no Peek, salvo indicação contrária:

- `Shipment arrived at facility`, `Sending to the airport`, `The goods have been packed and sent to the airline`, `Package has been packed and delivered to airport`, `Cargo handed over to the airline`: a caminho do voo. `Domestic customs clearance completed` é a alfândega de exportação chinesa, não a do seu país.
- `Arriving at the airport - arranging flights`, `Arriving at the airline, in the process of booking a flight`: reservando voo.
- `Flight is expected to take off on …`, `The flight is expected to depart on … and land on …`, `Estimated departure is …` ou `Estimated arrival date is …`, com datas no lugar das reticências: voo reservado.
- `Flight has flown`, `The flight has departed and is expected to … land`, `The flight has taken off`: em voo.
- `Sorry, the second flight has been delayed and is expected to … land`: o voo de conexão atrasou após a partida do primeiro; o novo dia de pouso aparece nas reticências.
- `During transportation to …`, com o país de destino: em trânsito para esse país.
- `Flight has arrived`, `The flight has arrived and is awaiting dismantling`, `Arrived at Destinated Airport`, `The NOA has been received` (aviso de chegada da companhia aérea), `Dismantling and picking up goods in progress`: pousou, antes da alfândega.
- `Customs clearance of the cargo is in progress` e `Arrived at customs clearance warehouse` (no armazém alfandegado de destino): "Na alfândega". Depois, `Customs clearance completed` ou `Customs clearance in destination country completed`.
- `In transit to final service provider`, `Delivery to final service provider` e `The goods have been handed over to the carrier`: a caminho ou entregue à empresa entregadora. Não é entrega a você.
- `The cargo has been picked up and arrived at the warehouse，awaiting outbound shipment` (no armazém da JRT no país de destino) e `It has been handed over to the service provider.` (à entregadora, como a FedEx): após a alfândega, não é a primeira leitura.
- `It has been transferred to the provider`: na linha aérea para a Espanha, após o transporte desde os Países Baixos, repassado à empresa entregadora. Não é entrega ao destinatário nem primeira leitura.
- `returned shipment has been received by processing center`: a encomenda devolvida retornou ao centro da JRT ("Devolvida ao remetente").

Em `The flight is expected to depart on … and land on …`, `The flight has departed and is expected to … land` e `Sorry, the second flight has been delayed and is expected to … land`, as datas seguem o formato mês.dia: `11.12` significa 12 de novembro.

**Linhas com barra.** Em alguns envios, a JRT junta chinês e inglês com uma barra, tudo como "Em trânsito" no Peek: `预计…航班/Flight scheduled for …` (voo reservado, com a data nos dois lados), `航班已启运/Package in transit to destination country` (em voo), `航班已落地，拆板中/Package arrived at destination country` (pousou, antes da alfândega) e `货物已清关完成/Import customs clearance complete` (alfândega de importação concluída, antes da primeira leitura da transportadora local). Antes delas, `国内清关完成` indica desembaraço de exportação na China, não no seu país ("Em trânsito"). `预计配载航班…月…日,Expected flight on …`, com vírgula em vez de barra, indica o voo em que o pacote será embarcado (`11月11日` e `November 11th` para 11 de novembro): o voo ainda vai partir ("Em trânsito"), e o Peek não trata essa data como previsão de entrega. `航班已起飞,The flight has taken off from the origin`, também com vírgula, indica que o voo partiu ("Em trânsito").

**Linhas da DHL e da FedEx.** Para a Europa, o rastreador continua com as leituras da DHL, quase sempre em minúsculas: `the instruction data for this shipment have been provided by the sender to dhl electronically` (a DHL recebeu os dados da etiqueta: "Etiqueta criada"), `shipment in transit to dhl` (a caminho da DHL antes do voo: "Em trânsito"), `A preferred location was designated as a reception option for this shipment.` (local preferido de entrega registrado enquanto o pacote ainda segue para a DHL: não muda o status), `The shipment has been posted by the sender at the retail outlet` (a JRT postou o pacote em uma unidade da Deutsche Post), `pick-up was successful.` (a DHL coletou o pacote após o repasse da JRT, não uma retirada por você), `the shipment has been processed in the parcel center of origin` e `loaded to movement / tour vehicle` ("Em trânsito"). Leituras posteriores da DHL também são replicadas: `The shipment has arrived in the destination country/destination area.`, `The shipment has left the import parcel center in the destination country/destination area.` e `The shipment is being prepared for delivery in the delivery depot` (a unidade prepara a rota, que ainda não saiu), todas em "Em trânsito"; `The shipment has been loaded onto the delivery vehicle` (na rota da DHL: "Saiu para entrega"); `not delivered-consignee could not be reached`, `The shipment could not be delivered. Please find more information here.` ou `The recipient was not present. Please find more information here.` (tentativa falha, sendo que na primeira e na última ninguém atendeu; "Please find more information here" é o texto do link de ajuda da DHL) e `not delivered-bad address` (o motorista não localizou o endereço), todas em "Tentativa de entrega"; `held for consignee collection` (deixado em ponto de retirada) e `The shipment is ready for collection from a ParcelShop/retail outlet. Please find more information here.` (aguardando retirada em ParcelShop ou agência), ambas em "Pronta para retirada"; `shipment will be returned to origin-not ordered` (recusado por não ter sido pedido) e `shipment will be returned to origin-bad address` (devolvido por endereço não encontrado), ambas em "Devolvida ao remetente"; e `the shipment has been successfully delivered` ("Entregue", ou "Devolvida ao remetente" se vier após uma linha de devolução). Outros envios começam com `Shipment information sent to FedEx`: os dados da etiqueta foram enviados à FedEx, que entrega o trecho final ("Etiqueta criada"), seguindo com leituras em inglês da FedEx como `Arrived at FedEx hub`, `Departed FedEx hub` e `On the way` ("Em trânsito"). `Delivery option requested, hold request received - check back later for shipment status` da FedEx significa que o destinatário pediu retenção e a FedEx registrou o pedido; o pacote segue "Em trânsito".

**Linhas da JRT e da UPS para os Estados Unidos.** Na rota americana, a JRT escreve apenas em chinês e o Peek exibe em inglês, tudo como "Em trânsito": `…号航班`, com mês/dia do voo (`10/22号航班` = 22 de outubro), como "Booked on a flight"; `航班已飞` como "Flight has flown"; `航班抵达` como "Flight has arrived"; `提货中` como "Picking up the goods" (no aeroporto, após pouso e antes da alfândega); `清关完成，已提货，待交仓` como "Customs clearance completed, goods picked up, awaiting hand-over to the warehouse"; e `已交仓` como "Handed over to the warehouse" (armazém da entregadora, antes da primeira leitura da UPS). Em seguida o rastreador replica eventos da UPS em chinês, que o Peek exibe no inglês oficial da UPS: `发件人已创建标签，但是 UPS 尚未收到包裹。` ("Shipper created a label, UPS has not received the package yet.": "Etiqueta criada"); `等待递送至 UPS Access Point? 地点。` ("Awaiting delivery to a UPS Access Point location.", aviso da UPS de envio a um Access Point que pode surgir antes da primeira leitura da JRT; o rastreador troca o símbolo registrado por interrogação), sem alterar o status; `抵达设施` ("Arrived at Facility"), `离开设施` ("Departed from Facility"), `UPS 设施处理` ("Processing at UPS Facility") e `已提取` ("Picked Up", entre unidades da UPS, não retirada por você), todos em "Em trânsito"; `已装上递送车辆` ("Loaded on Delivery Vehicle") e `准备今天递送` ("Out For Delivery Today"), "Saiu para entrega"; e `已递送` ("Delivered").

**Linhas da J&T Express México para o México.** Na rota mexicana, após a alfândega, o rastreador copia leituras em espanhol da J&T Express México, com cidade e unidade entre 【】 (aqui 【…】), tudo como "Em trânsito" até a saída para entrega: `【…】【…】¡Recolección exitosa!. El mensajero de J&T asignado … recolectó tu envío.` (o entregador da J&T coletou a remessa já liberada: etapa intermediária, não primeira leitura nem retirada por você; o rastreador pode trocar `¡` por `?` e incluir o telefone da J&T), `【…】Tu envío ha llegado a【…】` (chegou a uma unidade J&T) e `【…】Tu envío ha salido de【…】con destino a【…】` (saiu de uma unidade para outra). Depois de chegar à base entregadora, `【…】【…】El mensajero de J&T Express … está en camino a tu domicilio.` significa que o entregador da J&T está a caminho do seu endereço: "Saiu para entrega".

Na rota para a Europa, as linhas próprias da JRT usam o horário de Pequim: 17h lá equivale a 9h em Londres e 10h em Berlim no inverno. Já as linhas copiadas da DHL usam o horário local da DHL: aquelas iniciadas por `The shipment …`, `The international shipment …`, `the instruction data …` ou `A preferred location …`, linhas de veículo de transferência, `pick-up was successful.`, `not delivered-consignee …`, `not delivered-bad address`, `held for consignee collection` e `shipment will be returned to origin …`. O Peek interpreta cada linha que cita uma cidade na Alemanha ou no país de destino no horário daquela cidade; sem nome de cidade (um código de país ao lado não indica cidade), lê os dados de etiqueta e centros de origem/exportação/transporte no horário da Alemanha e o restante no horário do país de destino (ou Alemanha se não houver destino). O Peek interpreta as leituras copiadas da UPS e qualquer evento localizado nos EUA no horário local do local indicado (o aviso de Access Point da UPS somente quando informa um local nos EUA). As linhas copiadas da J&T Express México seguem o fuso da Cidade do México, 14 horas atrás de Pequim, enquanto as linhas próprias da JRT nessa rota permanecem no horário de Pequim.

## Quem entrega as encomendas da JRT

**Na Europa**, as linhas da JRT terminam na DHL, exceto a linha FedEx para a Irlanda (abaixo), a linha da Espanha (CTT Express) e a da Polônia (InPost). Nas linhas DHL, a JRT posta a encomenda em uma unidade da Deutsche Post e seu rastreador copia as leituras da DHL. Após a postagem, o registro passa a trazer o código da DHL no lugar do código JRT, ainda sem indicar o nome da linha. Quando o Peek identifica esse código como DHL, mostra "Entrega por DHL" com o número (ou "Anunciada a DHL, ainda não recebida" enquanto a DHL só tem o aviso eletrônico) e migra para o rastreamento próprio da DHL assim que ela registra uma leitura (detalhes abaixo). Caso contrário, peça ao vendedor o código da DHL: o rastreamento da DHL segue até a entrega (veja [DHL Paket](carrier:dhl)). Na rota da Espanha, `En tránsito internacional. Pendiente de recepción en CTT Express` significa que a CTT Express foi avisada do envio, mas ainda não o recebeu ("Em trânsito").

**Impostos na União Europeia.** Nas rotas aéreas da JRT, o desembaraço aduaneiro ocorre no aeroporto de chegada (linhas "Na alfândega" acima). Desde 1º de novembro de 2026, a UE aplica uma taxa de manuseio de € 2 por categoria de produto em compras online vindas de fora do bloco; veja quem faz a cobrança, bem como IVA e tarifas alfandegárias, em [encomendas paradas na alfândega](guide:customs).

**Nos Estados Unidos**, as rotas da JRT terminam na UPS: a JRT entrega o pacote no armazém da empresa final (`已交仓`) e seu rastreador copia os eventos da UPS até a entrega.

**No México**, a rota termina na J&T Express México, cujos eventos em espanhol são replicados no rastreador (acima).

**Na linha FedEx para a Irlanda**, cujo nome de serviço inclui FedEx, a JRT repassa o envio à FedEx e o registro passa a trazer o código FedEx no lugar do código JRT. Quando o código segue um formato da FedEx, o Peek exibe "Entrega por FedEx" com o número (ou "Anunciada a FedEx, ainda não recebida" enquanto há apenas o anúncio) e migra para o rastreamento da FedEx assim que ela escaneia o pacote. Se a FedEx entregar e o Peek não mostrar o código dela, peça o número ao vendedor e acompanhe pela [FedEx](carrier:fedex).

**Em outros países**, o Peek não sabe quem entrega encomendas da JRT. Pergunte ao vendedor qual empresa realiza a entrega e qual é o código dela: tentativas, pontos de retirada e prazos de guarda seguem as regras dessa transportadora. Recebeu cobrança de importação? Veja [encomendas paradas na alfândega](guide:customs).

## Pacote da JRT que não chega ou rastreamento parado

**Apenas as primeiras linhas.** `货物电子信息已经收到`, `Shipment information received` ou apenas uma linha de etiqueta da DHL ou FedEx indicam que o vendedor reservou o envio, mas ninguém o escaneou ainda. Se nada avançar em alguns dias, confirme com o vendedor se foi realmente postado.

**Aguardando voo.** A linha de voo informa o dia previsto, e `航班延误，预计…号飞出 Flight delayed, estimated departure on …` informa uma nova data. Se esse dia passar sem linha de decolagem, avise o vendedor.

**Nada após `已交服务商`, `Delivery to final service provider` ou `已交仓`.** A parte da JRT terminou. A menos que o rastreador copie eventos da DHL, UPS, FedEx ou J&T Express México, ou que o Peek exiba o código da DHL ou FedEx, os próximos passos estarão no rastreamento da entregadora local: peça esse número ao vendedor. Veja mais em [por que o rastreamento para de atualizar](guide:tracking-not-updating) e [como rastrear uma encomenda da China](guide:tracking-from-china).

**Nada encontrado.** Para um número desconhecido, o rastreador da JRT retorna uma linha contendo o número como referência e todas as demais colunas vazias. O Peek interpreta isso como não encontrado: mostra "Aguardando JRT" e, dois dias depois, "Confira o código de rastreamento". Compare o código caractere por caractere com a mensagem do vendedor.

**Endereço não encontrado.** Após `not delivered-bad address`, verifique o endereço no pedido e avise o vendedor na hora. Se surgir `shipment will be returned to origin-bad address`, o pacote está voltando: peça reembolso ou reenvio ao vendedor.

**Marcado como entregue, mas nada chegou.** O evento de entrega vem da empresa local, não da JRT: pergunte primeiro a ela e depois avise o vendedor. Veja [marcado como entregue mas não recebido](guide:delivered-not-received).

**Extraviado ou devolvido.** Foi o vendedor quem contratou a JRT, então solicite reembolso ou novo envio à loja ou ao marketplace. Veja [encomendas extraviadas](guide:lost-parcel) e [devolvidas ao remetente](guide:returned-to-sender).

## Telefone e atendimento ao cliente da JRT

O Peek não encontrou dados de contato da JRT: não há site oficial conhecido além do rastreador, nem telefone, e-mail ou endereço para destinatários. O vendedor contratou o envio com a JRT e é quem deve responder sobre o pedido — ou o marketplace, caso o vendedor não responda.

## Como rastrear uma encomenda da JRT com o Peek

O [Peek](/) identifica um código da JRT pelo formato — `JRT`, dez dígitos e `MM`, exclusivo no catálogo — e também por links do rastreador da JRT (acima). Para referências `UP` com dezesseis dígitos, o Peek consulta o rastreador da JRT e seleciona a JRT se ela reconhecer o pedido. Qualquer outra referência não tem formato próprio: cole mesmo assim (o Peek acompanha mais de 4.500 transportadoras), clique em "Trocar" e escolha JRT (digitar 今日通 ou Jinritong também encontra), ou use "Trocar transportadora" em uma encomenda já adicionada. Ao rastrear uma referência, o Peek mostra o código JRT em "JRT rastreia com o código" até que a JRT repasse o pacote e o registro passe a trazer o número da DHL ou FedEx. Como o Peek remove hifens, referências com hífen não são encontradas por ele: peça ao vendedor o código `JRT…MM`.

O Peek exibe o status, cada linha com seu local e o país de destino quando consta no registro da JRT, guiando-se pelas linhas em vez do resumo. Os horários usam o horário de Pequim, exceto nas linhas com fuso próprio (acima), e aparecem no fuso horário do seu dispositivo. O Peek retém qualquer linha gravada mais de doze horas antes do horário que ela carrega — ou mais de seis horas antes, no mesmo segundo de uma linha assim — e qualquer linha com data futura em relação à consulta. Nesse caso, o histórico abre com "Outros sites podem mostrar mais etapas, mas são apenas previsões." até a entrega ou devolução, e "Ver" lista essas linhas em cinza, cada uma com "Previsto para" e o dia. Essas linhas nunca contam como leitura física: não mudam o status nem disparam alertas. O Peek omite nomes, endereço, códigos postais, peso, taxas, assinatura e comprovante de entrega, e não exibe previsão de data de entrega porque a JRT não informa nenhuma.

Para os Estados Unidos, o Peek acompanha pelo rastreador da JRT, com os eventos da UPS replicados, a menos que o registro da JRT contenha o código da entregadora (como um `1Z…` da UPS): nesse caso, migra para ela. Para a Europa, assim que o registro da JRT traz um código reconhecível como DHL, o Peek passa a consultar também a DHL e migra para o rastreamento dela quando surge uma leitura tão recente quanto a última da JRT, preservando o histórico da JRT. Na linha FedEx para a Irlanda, faz o mesmo com a FedEx assim que aparece um código no formato FedEx. O Peek também faz repasse à Swiss Post quando o registro indica linha e número da Swiss Post para Suíça ou Liechtenstein. Se surgir outro código substituindo o da JRT, o Peek consulta os correios do país de destino se for um código postal internacional (duas letras, nove dígitos, duas letras), ou a única transportadora compatível com o formato, migrando da mesma forma. Para qualquer outro código informado pelo vendedor, adicione-o como uma encomenda separada.

Enquanto o pacote estiver aberto, ou no dia da entrega com alertas ligados, o Peek verifica até a cada 10 minutos; nos demais casos, de hora em hora — assim como sempre que a JRT fica sem novidades (12 horas nas primeiras linhas ou dois dias sem atualização). Quando a encomenda entra em "Saiu para entrega", verifica até a cada 2 minutos a partir das 6h (horário local) enquanto a JRT ou a transportadora que assumiu responde diretamente (a cada 5 minutos quando a consulta exige navegador, como pode ocorrer com DHL, FedEx e UPS). Por meio de serviços de rastreamento, verifica de hora em hora, a cada 3 horas após um dia sem movimento e até a cada 15 minutos no dia da entrega enquanto o pacote estiver aberto ou com alertas ligados. Após repasse para transportadora com ritmo próprio, o Peek segue esse ritmo, inclusive no dia da entrega. Na rota europeia, a linha copiada da DHL `The shipment has been loaded onto the delivery vehicle` ativa esse status (após o repasse, vale o rastreador da nova transportadora); nos EUA, `已装上递送车辆` e `准备今天递送` da UPS; e no México, `…está en camino a tu domicilio.` da J&T Express México. Ele pode notificar em cada leitura, só em etapas importantes ou em "Só a entrega". "Etapas importantes" (entrega, retirada, alfândega, tentativas sem sucesso e devoluções) alerta nas linhas "Na alfândega" da JRT e em `returned shipment has been received by processing center`, nas linhas de saída para entrega, tentativa falha, ponto de retirada, entrega e devolução copiadas da DHL, nas linhas de saída para entrega e entrega copiadas da UPS e em `…está en camino a tu domicilio.` da J&T Express México — mas não em voos da JRT, `已交服务商` nem `已交仓`. O Peek não pode falar com a JRT nem com o vendedor por você.

## Perguntas sobre o rastreamento da JRT

### O que é a JRT (今日通)?

É a 厦门今日通商贸有限公司 (Xiamen Jinritong), uma operadora logística em Xiamen, China. Seus códigos seguem o formato `JRT`, dez dígitos e `MM`, e o rastreador roda na Hualei (华磊), plataforma usada por outras transportadoras chinesas.

### Por que a JRT diz "delivered" se minha encomenda não chegou?

Por causa das traduções dela. `Cargo delivered to airport` significa que a JRT entregou a carga no aeroporto para o voo, e `Delivered to service provider` ou `Delivery to final service provider` significa repasse à empresa entregadora local. Nenhuma dessas frases indica entrega a você, e o Peek exibe todas como "Em trânsito".

### Quem entrega encomendas da JRT no meu país?

Na Europa, a DHL, e nos Estados Unidos, a UPS: o rastreador da JRT copia as leituras delas. Alguns envios vão pela FedEx (como a linha FedEx para a Irlanda), enquanto as linhas para Espanha, Polônia e México terminam na CTT Express, InPost e J&T Express México. Em outros países, o Peek não sabe: pergunte ao vendedor qual empresa entrega e peça o código dela.

### Com quem falar sobre uma encomenda da JRT extraviada?

Com o vendedor onde você comprou, pois foi ele quem contratou a JRT. O Peek não conhece site oficial nem contatos da JRT além do próprio rastreador.

:::sources
- [European Commission: EU Customs Reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – taxa de manuseio da UE de € 2 desde 1º de novembro de 2026
:::
