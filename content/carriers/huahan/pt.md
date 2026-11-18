---
title: Seguimento HuaHan Logistics: código HHW…YQ e entrega
description: Siga uma encomenda HuaHan Logistics (华翰物流) vinda da China: formato do código HHW…YQ, significado dos estados, transportadora local, alfândega e Peek.
slug: seguimento-huahan-logistics
published: 2026-11-18
updated: 2026-11-18
---

A HuaHan Logistics (华翰物流, também conhecida como HuaHan Express ou 华翰速递) é uma empresa chinesa de logística para comércio eletrónico transfronteiriço que expede encomendas a partir da China por via aérea ou postal e as entrega a uma transportadora local no país de destino para a distribuição final. Se uma loja online enviou a sua encomenda pela HuaHan, procure um número começado por `HHW` e terminado em `YQ` e cole-o na caixa acima. O localizador público da HuaHan em [hh-exp.cn/WebTrack/CargoTrack.html](https://www.hh-exp.cn/WebTrack/CargoTrack.html) também pode indicar a transportadora responsável pela entrega na sua área e o respetivo código local.

## Número de seguimento HuaHan: formato e exemplos

O Peek reconhece as cartas de porte da HuaHan com o formato `HHW`, duas letras, dez algarismos e `YQ`, num total de 17 caracteres. Exemplos com algarismos inventados:

| Código | O que é |
| --- | --- |
| `HHWCN4829105736YQ` | `HHW`, duas letras, dez algarismos e `YQ`: uma carta de porte da HuaHan. |
| `HHWCN482910573YQ` | Apenas nove algarismos: não corresponde ao formato da HuaHan. Verifique se falta um dígito. |
| `WNBAA4829105736YQ` | Também termina em `YQ`, mas começa por `WNB`: um número da WanbExpress. |
| `JXCAA4829105736YQ` | Começa por `JXC`: um número da Juxi Express. |

Muitos transitários chineses terminam os seus números em `YQ`, cada qual com as suas letras iniciais: `HHW` identifica exclusivamente a HuaHan e mais nenhuma transportadora do catálogo do Peek utiliza esse prefixo. Espaços, traços e letras minúsculas não afetam a pesquisa ao colar o código no Peek.

**Onde encontrá-lo.** A loja envia o número no e-mail de confirmação de expedição ou mostra-o na página da encomenda. Se não o encontrar, consulte [como encontrar o número de seguimento](guide:find-tracking-number). Na página pública de consulta da HuaHan ([hh-exp.cn/WebTrack/CargoTrack.html](https://www.hh-exp.cn/WebTrack/CargoTrack.html), com o título `运单追踪`), o formulário está em chinês e aceita até 20 códigos separados por vírgulas (`多个单号请用英文逗号隔开（最多支持 20 个单号同时查询）`) após resolver uma soma simples ou clicar nos caracteres de uma imagem; já o endereço principal [hh-exp.cn](https://www.hh-exp.cn/) abre o portal de início de sessão para expedidores (`华翰物流订单管理系统`), com ligação para a consulta pública.

**Um segundo código.** O registo da HuaHan pode apresentar o número atribuído pela transportadora local. Por vezes, a HuaHan acrescenta depois de um traço inferior um sufixo interno (como `_D104`) que não faz parte do código dessa transportadora: retire o `_` e tudo o que vier a seguir ao pesquisar diretamente no site do operador local. O Peek remove esse sufixo automaticamente.

## Estados de seguimento da HuaHan explicados

O rastreador da HuaHan apresenta cada leitura com data e hora, descrição e local, da mais recente para a mais antiga. Os registos próprios da HuaHan surgem em inglês, alguns antecedidos de chinês ou apenas em chinês em certas etapas de armazém e aeroporto. Eis os estados habituais pela ordem do percurso:

| O que a HuaHan mostra | O que significa para si | Estado no Peek |
| --- | --- | --- |
| `快件电子信息已经收到 Shipment information received`, `Shipment information received` | A loja enviou os dados da encomenda à HuaHan, que ainda não recebeu fisicamente o pacote (quando surge apenas `Shipment information received`, o Peek traduz para «Dados da encomenda recebidos»). | «Etiqueta criada» |
| `快件到达作业中心 Shipment arrived at facility`, `Arrived at origin facility`, `Has entered the warehouse` | O centro operacional da HuaHan na China (em Shenzhen nos históricos presentes no código do Peek) recebeu a encomenda. | «Aceite pela transportadora» |
| `快件操作完成 Shipment departed from facility`, `Departed from origin facility`, `货物离开操作中心`, `Shipment received`, `Shipments were sent` | Tratada e a sair do centro de origem, ou em passagem pelo armazém de transferência da HuaHan antes da exportação. | «Em trânsito» |
| `Transfer to Origin airport`, `快件从转运仓发出至机场，出口报关中（…）` | A caminho do aeroporto de partida com o despacho aduaneiro de exportação em curso. | «Em trânsito» |
| `Export customs clearance completed`, `快件抵达机场，预计…号航班飞`, `航班延误，重新配载至…号航班飞` | Desalfandegamento de exportação na China concluído (não é a alfândega do seu país) e voo marcado ou reagendado. | «Em trânsito» |
| `Departed from airport of Origin` | O avião descolou. | «Em trânsito» |
| `Arrived at destination airport`, `拆板中` | O voo aterrou no aeroporto de destino ou de escala e a palete aérea está a ser desagregada (`拆板中`). | «Em trânsito» |
| `Import Customs clearance started` | A alfândega de importação no país de destino iniciou a verificação. | «Na alfândega» |
| `Import Customs clearance completed`, `目的国清关完成` | A alfândega de importação autorizou a saída da encomenda. | «Em trânsito» |
| `Has been given to local carrier`, `转单：…` | A HuaHan entregou a encomenda à transportadora local (ou indica após `转单：` o código de transferência). Seguem-se as leituras dessa transportadora. | «Em trânsito» |

**As linhas da transportadora local.** Após a entrega ao parceiro, o rastreador da HuaHan retransmite os eventos da empresa que faz a distribuição final. O Peek classifica-os pelo código interno da HuaHan ou pelo texto:

| Após a entrega à transportadora local | O que significa para si | Estado no Peek |
| --- | --- | --- |
| `Ready to Ship, Parcel received and processed` | A transportadora local recebeu e triou a encomenda. | «Em trânsito» |
| `The parcel is in the central warehouse waiting to be processed.`, `In Transit, Shipment arrived to sorting centre / Shipment in the sorting centre`, `In Transit, Shipment remained at sorting hub` | No armazém central ou num centro de triagem da transportadora local. | «Em trânsito» |
| `In Transit, Arrived to HU HUB`, `In Transit, Parcel has entered the Linehaul network.` | Passagem por uma plataforma logística europeia (aqui na Hungria) e transporte entre centros. | «Em trânsito» |
| `In Transit, Picked Up` (seguido do alemão `Einrollung` da DPD), `In Transit, Scanned By DPD At parcel delivery centre.`, `In Transit, At parcel delivery centre.` | Entrada no centro de distribuição da DPD (não é uma recolha em sua casa) e leitura diária enquanto lá aguarda. | «Em trânsito» |
| `Out for Delivery, Parcel is at courier, delivery expected soon.` | Em volta de distribuição com o estafeta. | «Em distribuição» |
| `The courier will deliver the parcel soon. Additional details are available in the SAMEDAY app, email or SMS.` | Em distribuição pela Sameday, transportadora na Roménia e na Bulgária. | «Em distribuição» |
| `Delivery Attempted`, `Delivery Attempted, Unfortunately we have not been able to deliver your parcel. Pickup parcelshop delivery after first delivery attempt.` | Tentativa de entrega falhada da DPD; na mensagem mais longa, a encomenda segue para uma loja Pickup. | «Tentativa de entrega» |
| `Awaiting Pickup, Parcel was deposited in Pickup parcel station` | Depositada pela DPD num ponto ou cacifo Pickup para levantamento. | «Pronta para levantamento» |
| `The parcel has been successfully delivered.`, `Delivered` | Entregue (o texto isolado `Delivered` aparece no Peek como «Entregue»). | «Entregue» |

A HuaHan regista a distribuição, o ponto de recolha e a entrega com códigos próprios (`CB`, `PB`, `CC`), mas o Peek verifica primeiro as palavras do registo: a HuaHan aplica, por exemplo, o código de distribuição `CB` à saída de um centro da [USPS](carrier:usps) (`Departed USPS Facility`), que o Peek mantém como «Em trânsito».

**Duas linhas que geram confusão.** `We will pick up your parcel from the sender soon.` pode surgir logo no início, ainda antes de a HuaHan ter recebido o pacote na China: trata-se do pré-aviso criado na transportadora local, não de uma recolha na sua morada; o Peek mostra «Etiqueta criada». Da mesma forma, `The parcel was redirected. As soon as it is in the couriers possession, it will be processed as requested.` regista um pedido de redirecionamento feito enquanto a encomenda ainda está na China: não representa movimento físico e o Peek não lhe atribui estado.

**Dados eletrónicos, não leituras físicas.** `The instruction data for this shipment have been provided by the sender to DHL electronically.` (com ou sem ponto final) é o registo informático na [DHL](carrier:dhl); `Pendiente de recepción en CTT Express` é o pré-aviso na [CTT Express](carrier:ctt-express), transmitido quando a encomenda ainda se encontra na China; `Pre-Shipment Info Sent to USPS` é o pré-aviso na USPS, e `货物电子信息已经收到` assinala em chinês a receção eletrónica dos dados do pedido. Nenhum destes eventos move o pacote: o Peek mostra «Etiqueta criada» ou mantém o estado anterior.

**Uma leitura repetida em duas linhas.** O seguimento da HuaHan lista por vezes o mesmo evento da transportadora final duas vezes no mesmo minuto: uma linha com local e texto próprio da transportadora, e outra sem local com uma frase genérica da HuaHan como `Delivered, Parcel was delivered successfully`. O Peek guarda apenas a linha que indica o local.

**Por via postal.** A HuaHan também envia encomendas através da [China Post](carrier:china-post). Nesse caso, o rastreador reproduz em chinês as leituras da China Post com a estação entre `【】` e, mais tarde, as do operador postal de destino copiadas pela China Post:

| Leitura postal retransmitida | O que significa para si | Estado no Peek |
| --- | --- | --- |
| `中国邮政已收取邮件` | A China Post recebeu a encomenda entregue pela HuaHan. | «Em trânsito» |
| `邮件已在【…】完成分拣，准备发出`, `邮件到达【…】`, `邮件离开【…】，正在发往【…】`, `邮件离开【…】，正在发往下一站` | Triada, chegada ou expedida numa estação da China Post indicada em `【】`. | «Em trânsito» |
| `送交出口海关` | Apresentada à alfândega de exportação na China (não é a alfândega do seu país). | «Na alfândega» |
| `出口海关/放行`, `已交承运商运输` | Autorizada pela alfândega chinesa e entregue à companhia aérea. | «Em trânsito» |
| `到达寄达地`, `到达寄达地处理中心`, `到达境外经转局` | Chegada ao país de destino, ao respetivo centro de tratamento postal ou a um entreposto de trânsito. | «Em trânsito» |
| `境外进口海关留存待验(等待提交给边境机构/安全部门)`, `境外进口互换局留存`, `送交境外进口海关` | Retida na estação de permuta ou entregue à alfândega de importação local. | «Na alfândega» |
| `寄达地海关允许进口（无需缴税）` | Importação autorizada pela alfândega de destino sem taxas a pagar. | «Em trânsito» |
| `离开【…】处理中心`, `已到达【…】投递局` | Saiu do centro de tratamento local e chegou ao centro de distribuição postal. | «Em trânsito» |

**Através da Landmark Global.** Noutras encomendas, a HuaHan retransmite em inglês as linhas da [Landmark Global](carrier:landmark-global). As duas primeiras surgem antes da receção na China e as restantes após a passagem para a rede da [bpost](carrier:bpost):

| Linha da Landmark Global | O que significa para si | Estado no Peek |
| --- | --- | --- |
| `Shipment Data Uploaded` | Dados do remetente carregados na Landmark Global. | «Etiqueta criada» |
| `Processed` | Processamento no mesmo minuto, lido pelo Peek como trânsito. | «Em trânsito» |
| `Incoming scan at facility` | Entrada num centro logístico após entrega à rede da bpost. | «Em trânsito» |
| `Outgoing scan at facility` | Saída desse centro (arquivada sob o código de distribuição pela HuaHan, mas exibida como «Em trânsito» pelo Peek porque a entrega pode ocorrer dias depois). | «Em trânsito» |
| `Out for delivery (On the way)` | Em giro de entrega. | «Em distribuição» |
| `Delivery attempt (Delivery not possible)` | Tentativa de entrega sem sucesso. | «Tentativa de entrega» |

## Quem entrega as encomendas da HuaHan e quanto tempo demoram

No seu site institucional ([hh-exp.com](https://www.hh-exp.com/) e [hh-exp.com/gyhh](https://www.hh-exp.com/gyhh)), a HuaHan (`深圳市华翰鸿运国际物流技术有限公司`, fundada em 2008 com sede em Shenzhen) afirma cobrir mais de 150 países e regiões na Europa, América do Norte, América do Sul e Austrália, com foco em linhas expresso de pequenos pacotes para mercadorias especiais (`特货小包专线`, como cosméticos, baterias e cigarros eletrónicos) e canais postais (`特货邮路`). Na página inicial refere um prazo global indicativo de 6 a 12 dias úteis (`整体时效6-12个工作日`), sem publicar tabelas por país nem a lista completa de parceiros: oriente-se pela estimativa fornecida pela loja onde comprou.

Depois de `Has been given to local carrier`, a entrega cabe a um operador do país de destino. Nos percursos lidos pelo código do Peek surgem a [CTT Express](carrier:ctt-express) (cujo aviso `Pendiente de recepción en CTT Express` aparece logo no início em envios para Portugal e Espanha), a [DHL](carrier:dhl), a DPD, a Sameday, a [USPS](carrier:usps), a [UPS](carrier:ups), a [China Post](carrier:china-post) (que encaminha para o correio nacional, como os [CTT](carrier:ctt) em Portugal) e a [Landmark Global](carrier:landmark-global) com entrega pela rede da [bpost](carrier:bpost). O Peek segue sempre a transportadora e o código indicados para a sua encomenda.

**Tentativas de entrega e levantamento.** O número de tentativas, o aviso de entrega e o prazo de permanência em ponto de recolha ou Loja CTT seguem as regras da transportadora local: contacte-a com o respetivo número local para reagendar. Veja [entrega falhada](guide:missed-delivery) e [encomendas não levantadas](guide:uncollected-parcel).

## Alfândega, taxas de importação e SMS fraudulentos

A HuaHan presta serviços aos vendedores online e as suas páginas não indicam se o IVA e os direitos da sua compra foram pagos antecipadamente: confirme na fatura da encomenda se esses encargos estavam incluídos. Saiba mais em [encomendas retidas na alfândega](guide:customs) e [identificar SMS falsos de entregas](guide:delivery-scam-texts).

- **União Europeia.** Desde 1 de julho de 2026, na **União Europeia**, os bens comprados à distância fora do território aduaneiro da UE em remessas com valor intrínseco até 150 € estão sujeitos a um direito aduaneiro de 3 € por categoria de artigos; desde 1 de novembro de 2026 acresce uma taxa de tramitação da União de 2 € por categoria de artigos, qualquer que seja o valor da remessa (uma categoria reúne as mercadorias com a mesma classificação pautal, descrição e, quando exigida, origem, independentemente do número de unidades).
- **Portugal.** Os Açores e a Madeira integram tanto o território aduaneiro como o território do IVA da UE. Em **Portugal**, segundo os [CTT](https://www.ctt.pt/particulares/receber/desalfandegar/fim-da-isencao-de-direitos-aduaneiros-em-compras-ate-150-euros-fora-da-uniao-europeia), o IVA, os direitos aduaneiros e a Taxa de Manuseamento Aduaneiro que não forem pagos no checkout são devidos à chegada da mercadoria; se os CTT o contactarem para concluir o desalfandegamento de um envio, pedem que disponibilize a informação solicitada e consulte os encargos no portal de desalfandegamento dos CTT, e nos seus [alertas de phishing](https://www.ctt.pt/transversais/alertas-de-phishing) avisam contra mensagens fraudulentas por SMS ou e-mail que invocam taxas alfandegárias ou dados em falta com links falsos.

## Encomenda HuaHan atrasada, parada ou não encontrada

**Número não encontrado (`没有找到记录`).** Enquanto a HuaHan não tiver registo do código, o seu rastreador responde `没有找到记录` («nenhum registo encontrado»); em peektracker.com, o Peek mostra «À espera de HuaHan Logistics» e continua a verificar. Se ao fim de dois dias nem a HuaHan nem outra fonte reconhecerem o número, surge «Ao fim de dois dias, a transportadora ainda não reconhece este número. Verifica-o, escolhe outra transportadora ou elimina a encomenda.» e as consultas passam a diárias e, após uma semana, semanais. Confirme cada carácter (`HHW`, duas letras, dez algarismos, `YQ`) na mensagem do vendedor.

**Parada em `Shipment information received`.** A loja registou o envio, mas a HuaHan ainda não leu fisicamente a encomenda no armazém. Pergunte ao vendedor quando entregou o pacote.

**Parada em `Import Customs clearance started`.** A alfândega do seu país está a inspecionar a encomenda. Se permanecer assim durante muitos dias sem contacto da transportadora local, avise o vendedor.

**Sem novidades após `Has been given to local carrier`.** A transportadora local já tem a encomenda e o seu próprio seguimento costuma estar mais avançado do que o da HuaHan. Procure o nome e o número local no rastreador da HuaHan ou no cartão da encomenda no Peek.

**Vários dias sem atualização.** Quando uma encomenda em trânsito passa quatro dias sem novas leituras, o Peek assinala («Sem atualizações há quatro dias») e recorda que é comum não haver registos durante voos longos, na alfândega ou ao fim de semana. Consulte [porque é que o seguimento não atualiza](guide:tracking-not-updating) e [seguir encomendas vindas da China](guide:tracking-from-china).

**Dada como entregue, mas não chegou.** Verifique caixa do correio, portaria, vizinhos e pontos de recolha próximos, e fale depois com a transportadora local e com o vendedor. Veja [entregue mas não recebida](guide:delivered-not-received).

**Perdida ou devolvida.** Quem contratou a HuaHan foi o vendedor, pelo que deve pedir o reembolso ou novo envio à loja ou plataforma onde comprou. Veja [encomendas perdidas](guide:lost-parcel) e [devolvida ao remetente](guide:returned-to-sender).

## Telefone e apoio ao cliente da HuaHan

Na sua página oficial de contactos ([hh-exp.com/lxwm](https://www.hh-exp.com/lxwm)), a HuaHan divulga para a **China**:

- **Linha oficial (`官方热线`):** `400-015-6080` (número chinês 400 sem horário de atendimento publicado; o telemóvel `13794450408` destina-se a contactos comerciais de expedidores `业务咨询`).
- **Sede:** 10.º andar, bloco C, edifício Bairuida, 4001 Xuegang Avenue, Bantian, distrito de Longgang, Shenzhen (`深圳市龙岗区坂田雪岗大道4001号百瑞达大厦C座10楼`), com filiais em Yiwu, Zhengzhou e Hangzhou.

A HuaHan não publica endereço de e-mail de apoio a destinatários particulares. Para questões sobre a compra ou reembolso, contacte o vendedor; para a entrega final, fale com a transportadora local.

## Seguir uma encomenda HuaHan com o Peek

O [Peek](/) identifica imediatamente a HuaHan Logistics a partir de um código `HHW…YQ` («Transportadora detetada») e consulta o rastreador da HuaHan apenas com esse número completo, sem conta, código postal nem telefone. Quando a HuaHan pede uma soma aritmética, o Peek resolve-a diretamente; quando apresenta a verificação de imagem com caracteres para clicar, o Peek suspende os pedidos diretos para respeitar o limite horário da HuaHan (`请求次数已达每小时上限`) e abre a página no seu serviço de navegador, ou recorre a serviços de seguimento se o site da HuaHan estiver inacessível.

O Peek mostra o estado, cada leitura com a respetiva localidade e o país de destino registado. Como o rastreador da HuaHan imprime horas sem fuso horário, o Peek mantém a hora local impressa nos registos próprios da HuaHan anteriores à entrega ao parceiro — exceto o aviso `Pendiente de recepción en CTT Express`, lido na hora de Madrid mesmo em encomendas com destino a Portugal — e coloca as leituras posteriores a `Has been given to local carrier` no fuso do país de destino quando este tem um único fuso horário (em Portugal, onde os Açores têm fuso próprio face ao Continente e à Madeira, mantém-se a hora local impressa caso o país tenha mais de um fuso), convertendo as horas com fuso atribuído para o do seu dispositivo («Horas no teu fuso horário: …» em peektracker.com e na app para iPhone). A HuaHan não indica data prevista de entrega nem peso, pelo que o Peek também não os apresenta.

Quando a HuaHan indica uma transportadora local conhecida pelo Peek com um número válido — ou inclui numa linha `转单：` um código inequívoco de outro operador, como um `1Z…` da [UPS](carrier:ups) —, o cartão da encomenda mostra-o («Entrega: CTT Express», ou «Anunciada a CTT Express, ainda não recebida» enquanto existir apenas o pré-aviso) e consulta também essa transportadora. Se a HuaHan fornecer um código postal internacional (duas letras, nove algarismos, duas letras) sem transportadora conhecida, o Peek consulta o correio nacional do país de destino. Assim que essa transportadora apresenta uma leitura pelo menos tão recente quanto a última da HuaHan, o Peek acompanha a entrega por ela mantendo o histórico da HuaHan.

Enquanto mantiver a encomenda aberta, ou no dia da entrega com as notificações ativas, o Peek verifica até a cada 10 minutos — e no dia em que sai para distribuição a partir das 6:00 locais, até a cada 2 minutos se o site da HuaHan responder diretamente, ou a cada 5 minutos quando a verificação abre um navegador; apenas através de serviços de seguimento, verifica de hora a hora, a cada 3 horas após um dia sem leituras e até a cada 15 minutos no dia da entrega enquanto a encomenda estiver aberta ou com alertas ativos. Nas restantes alturas, bem como após dois dias sem novidades (12 horas em «Etiqueta criada» ou «Aceite pela transportadora»), verifica de hora a hora e adota a cadência da transportadora local assim que esta assume a entrega. Pode escolher alertas para «Todas as leituras», «Passos importantes» (abrange `Import Customs clearance started`, leituras alfandegárias postais como `送交出口海关` e `送交境外进口海关`, saída para distribuição, ponto de recolha e entrega, mas não o despacho de exportação de rotina `快件从转运仓发出至机场，出口报关中`) ou «Só a entrega». O Peek não pode contactar a HuaHan nem o vendedor por si.

## Perguntas frequentes sobre o seguimento HuaHan

### O que é a HuaHan Logistics (华翰物流)?

É um operador logístico chinês especializado em comércio eletrónico transfronteiriço (`深圳市华翰鸿运国际物流技术有限公司`), fundado em 2008 em Shenzhen e também designado HuaHan Express (`华翰速递`). Expede encomendas da China para mais de 150 países e entrega-as a transportadoras locais no destino.

### Onde posso seguir uma encomenda da HuaHan?

Na caixa de pesquisa no topo desta página ou no localizador público da HuaHan em `hh-exp.cn/WebTrack/CargoTrack.html` (até 20 números separados por vírgulas, após validação por soma ou imagem). Utilize sempre o número `HHW…YQ` completo de 17 caracteres.

### O que significa «Has been given to local carrier» na HuaHan?

O desalfandegamento de importação terminou e a HuaHan entregou a encomenda à transportadora que fará a distribuição no seu país. As linhas seguintes do histórico pertencem a essa transportadora local.

### Quem entrega as encomendas da HuaHan no meu país?

Depende da linha escolhida pelo remetente: no código do Peek surgem a CTT Express, a DHL, a DPD, a Sameday, a USPS, a UPS, a China Post (com entrega pelo operador postal nacional, como os CTT) e a Landmark Global seguida da rede bpost. Consulte o cartão da encomenda no Peek ou o rastreador da HuaHan para ver qual foi atribuída ao seu envio.

### Quem devo contactar se a minha encomenda HuaHan não chegar?

O vendedor ou a plataforma onde efetuou a compra, pois foi quem contratou o envio à HuaHan. Se a encomenda já tiver sido entregue à transportadora local no seu país, contacte esse operador com o respetivo número local para tratar da entrega.

:::sources
- [HuaHan Express: 运单追踪 (seguimento público de remessas, em chinês)](https://www.hh-exp.cn/WebTrack/CargoTrack.html) – pesquisa pública até 20 códigos separados por vírgulas, colunas de estado e detalhe de leituras
- [HuaHan Logistics: 华翰物流订单管理系统 (portal de clientes, em chinês)](https://www.hh-exp.cn/) – acesso para vendedores e ligação ao seguimento público
- [HuaHan Express: Página inicial oficial (em chinês)](https://www.hh-exp.com/) – fundação em 2008, linhas de comércio eletrónico e mercadorias especiais para mais de 150 países e regiões, prazo indicativo global de 6 a 12 dias úteis e linha 400-015-6080
- [HuaHan Express: 关于华翰 (apresentação da empresa, em chinês)](https://www.hh-exp.com/gyhh) – historial desde 2008, denominação social completa, canais postais e especiais e filiais
- [HuaHan Express: 联系我们 (contactos, em chinês)](https://www.hh-exp.com/lxwm) – sede em Shenzhen, filiais em Yiwu, Zhengzhou e Hangzhou, linha oficial 400-015-6080 e contacto comercial
- [Comissão Europeia: EU Customs Reform (em inglês)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – direito aduaneiro de 3 € por categoria de artigos até 150 € desde 1 de julho de 2026 e taxa de tramitação da União de 2 € desde 1 de novembro de 2026
- [Comissão Europeia: Guidance on the EUR 3 customs duty and the Union handling fee (PDF, em inglês)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – taxa de 2 € independentemente do valor e definição de categoria de artigos
- [Comissão Europeia: Territorial Scope (em inglês)](https://taxation-customs.ec.europa.eu/taxation/vat/vat-directive/how-does-vat-work/territorial-scope_en) – Açores e Madeira integrados no território aduaneiro e no território do IVA da UE
- [CTT: Fim da isenção de direitos aduaneiros em compras até 150 euros fora da União Europeia](https://www.ctt.pt/particulares/receber/desalfandegar/fim-da-isencao-de-direitos-aduaneiros-em-compras-ate-150-euros-fora-da-uniao-europeia) – encargos não pagos no checkout devidos à chegada a Portugal e o que fazer quando os CTT pedem para concluir o desalfandegamento
- [CTT: Alertas de Phishing](https://www.ctt.pt/transversais/alertas-de-phishing) – avisos em Portugal sobre SMS e e-mails fraudulentos invocando taxas alfandegárias ou dados em falta
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formato `HHW…YQ`, verificação aritmética ou de imagem e limite horário, códigos e textos da HuaHan, China Post, Landmark Global/bpost, DPD, Sameday, DHL, CTT Express, USPS e UPS (`转单：`), remoção do sufixo `_`, relógios e entrega à transportadora local
:::
