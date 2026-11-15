---
title: DHLink rastreio: número da DHgate, estados e quem entrega
description: Segue uma encomenda DHLink da DHgate: que números o rastreio aceita, o que querem dizer os estados em inglês, quem entrega no teu país e quem contactar.
slug: dhlink-rastreio
published: 2026-11-14
updated: 2026-11-15
---

A DHLink (驼飞侠) é a plataforma de logística transfronteiriça da DHgate (敦煌网). Criada em 2014, envia encomendas de vendedores da DHgate e de outros clientes a partir da China ou dos seus armazéns no estrangeiro, entregando-as depois a uma transportadora no país de destino para a etapa final. Segue o teu código na [página de rastreio da DHLink](https://www.dhlink.com/trackQuery?lang=en) ou cola-o na caixa acima. Encontras o número nos detalhes da encomenda na DHgate; se não aparecer lá, pede-o ao vendedor.

## Número de rastreio DHLink: como é

A DHLink não publica um formato fixo de numeração. A sua página de rastreio aceita até 40 códigos de uma vez e procura pelo que chama *Global Tracking No.*, *Logistics Delivery No.* e *DHLink pickup No.* Estes são os formatos vistos em encomendas da DHLink, com algarismos inventados:

| Número | O que é |
| --- | --- |
| `FC0000000000000` | `FC` e 13 algarismos: o número interno da DHLink («DHLink No.»). Nalgumas linhas serve também de número de rastreio. |
| `XZG000000000WL` | `XZG`, 9 algarismos e `WL`: número de um parceiro de linha que a DHLink reconhece como número de seguimento. |
| `YLUS0000000000` | `YLUS` e 10 algarismos: linha para os EUA entregue pela GOFO. |
| `QH000000000000FS` | `QH`, 12 algarismos e `FS`: outra linha para os EUA entregue pela GOFO. |
| `EYT0000000000HZ` | `EYT`, 10 algarismos e `HZ`: linha para os EUA entregue pela SwiftX. |
| `CYS00000000000` | `CYS` e 11 algarismos: linha para os EUA entregue pela UniUni. |
| `JXCYI0000000000YQ` | `JXC`, `YI` ou `ZO`, 10 algarismos e `YQ`: formato de carta de porte da Juxi Express a que o seguimento da DHLink também responde em algumas linhas para os EUA. O Peek pergunta primeiro à DHLink por este formato; os restantes códigos `JXC` ficam atribuídos à Juxi Express. |
| `RR000000005CN` | Duas letras, 9 algarismos e `CN`: número da [China Post](carrier:china-post), usado quando a encomenda segue por via postal. |
| `GFUS00000000000000` | Não é da DHLink: é um número da GOFO Express para a última etapa de algumas linhas norte-americanas. |

Num resultado, a DHLink mostra o «DHLink No.», o «Tracking No.» e, quando há entrega por outra empresa, o «Last Leg No.» dessa transportadora. Se o número de rastreio for diferente do número DHLink, pesquisa pelo número de rastreio: procurando apenas pelo código `FC`, uma encomenda que tenha outro número de seguimento pode não aparecer. Sem `lang=en` no endereço, a página abre em chinês.

## Estados do rastreio DHLink explicados

O resultado agrupa o envio em `In transit`, `Delivered` ou `Not found`, mostra os códigos dos países de origem e destino e a hora de cada leitura, acrescentando `Signed for` quando há registo de entrega. Como os eventos chegam por várias fontes, o mesmo passo aparece muitas vezes repetido na página da DHLink. Eis as mensagens em inglês, pela ordem habitual da viagem:

| A DHLink escreve | O que quer dizer para ti |
| --- | --- |
| `Data Received; Data transferred and received by carrier` | Numa linha para o sudeste da Europa: a Pactic, plataforma na Hungria por onde passa essa rota, recebeu os dados do envio. Antes da leitura no armazém da DHLink, o Peek mostra «Etiqueta criada»; se a linha surgir de novo depois do voo, mantém «Em trânsito». |
| `Arrive at the collection warehouse` | A encomenda chegou ao armazém de recolha da DHLink na China. |
| `The collection warehouse has completed sorting` | Já passou pela triagem nesse armazém. |
| `Package received & scanned at warehouse` | Deu entrada e foi lida no armazém da DHLink. |
| `Parcel handed over to carrier,waiting for transportation` | Foi entregue no ponto de recolha e aguarda transporte. |
| `Package departed from the warehouse`, `The package has sent from the warehouse` | Saiu do armazém. |
| `Warehouse scanning bagging`, `Master airway bill number received`, `Leave the warehouse` | Nalgumas linhas: ensacamento no armazém na China para o voo, receção da carta de porte aéreo principal (*master air waybill*) e saída do armazém («Em trânsito» no Peek). |
| `Customs export clearance completed`, `Package handed over to airline` | A alfândega de exportação chinesa libertou a encomenda, que passou para a companhia aérea. |
| `Flight departure`, `The flight has taken off` | O avião já descolou da China. |
| `Shipment Departed From Original Country at …`, `Departed from airport of origin` | O mesmo passo: o voo partiu da China («Em trânsito» no Peek). |
| `The flight has arrived and the board is being dismantled`, `Dismantling the board` | O voo aterrou e as paletes aéreas estão a ser desmontadas no aeroporto de chegada («Em trânsito» no Peek). |
| `Shipment has arriveved at destination Country at …` | Chegou ao país de destino (a gralha em *arriveved* é da própria DHLink). |
| `Arrived to … hub`, `Linehaul transit; Parcel has entered the linehaul network.` | Depois do voo, entrada num centro europeu de um parceiro (identificado por código onde estão as reticências, como `HU` na Hungria para a Pactic) e o transporte até à etapa seguinte («Em trânsito» no Peek). |
| `Customs clearance completed`, `Customs import clearance completed, awaiting pickup by local carrier` | Desalfandegamento de importação concluído; falta a transportadora local recolher a encomenda. |
| `UPS/USPS/Fedex` | Vai ser entregue à transportadora norte-americana. |
| `货物已交UPS/USPS/Fedex,The goods have been delivered to UPS/USPS/Fedex` | Entregue à [UPS](carrier:ups), à USPS ou à [FedEx](carrier:fedex) nos EUA. Ainda não foi entregue na tua morada. |
| `Package picked up by last-mile carrier` | A transportadora responsável pela entrega no teu país já recolheu a encomenda. |
| `Scheduled for Delivery` | Saiu para distribuição. |
| `Shipment has delivered at …` | Entregue. |

Nas linhas para os EUA surgem também leituras como `Arrived at Origin Hub`, `Departed Destination Hub` ou `Shipping Order Created.` («Etiqueta criada» no Peek, cerca de uma hora antes de entrar no armazém). Uma instalação da transportadora final pode registar `Parcel received by ABC01.` (com o seu próprio código no lugar de `ABC01`) e, minutos depois, `Parcel departed from Facility.`, ambos «Em trânsito». Seguem-se as leituras da empresa que entrega, como `Arrived at GOFO Delivery Station` na GOFO, `No Access to Delivery Location` na USPS (tentativa falhada) ou, na UniUni, `Parcel received at UniUni sorting hub.`, `Parcel processed.` e `Parcel scanned and in transit.` Nas linhas da [China Post](carrier:china-post) aparecem também estados em chinês: `中国邮政 已收取邮件` (aceite pela China Post), `送交出口海关` (apresentada à alfândega de exportação) e `已妥投` a seguir ao código do país (entregue). Quando a DHLink não conhece o código, a página responde `There is no track for this waybill number`.

## Quem entrega as encomendas DHLink

O site da DHLink é feito para vendedores e não indica que parceiro faz a distribuição em cada país: quem revela a empresa final é o próprio rastreio. Nas linhas para os EUA, uma leitura pode anunciar a passagem para «UPS/USPS/Fedex» antes de surgirem os eventos dessa transportadora, enquanto outras linhas terminam na GOFO Express (cujo seguimento também aceita o código `FC` de 13 algarismos nas encomendas da DHgate que entrega) ou na SwiftX; na Suíça, o «Last Leg No.» pode ser da DPD Suíça. Já as encomendas enviadas pela [China Post](carrier:china-post) mantêm o código terminado em `CN` até à porta: vê na página da China Post que operador postal entrega no teu país.

Assim que souberes qual é a transportadora local e o respetivo número, consulta o seguimento dela para veres o detalhe da distribuição, avisos de passagem e pontos de levantamento: lê também [entrega falhada](guide:missed-delivery).

**Alfândega e taxas em Portugal e na UE.** Segundo a Autoridade Tributária e Aduaneira (AT), desde 1 de julho de 2026 acabou na União Europeia a franquia aduaneira para remessas até 150 €: além do IVA, as compras online vindas de fora da UE com valor intrínseco até 150 € pagam direitos aduaneiros fixos de 3 € por adição (categoria de artigo). Além disso, desde 1 de novembro de 2026, a UE aplica uma taxa de tratamento da União de 2 € por adição, contada como nos 3 €, nas vendas à distância de bens importados de países terceiros, independentemente do valor da encomenda, devida no momento em que a declaração aduaneira é aceite. Mais pormenores em [encomendas retidas na alfândega](guide:customs).

## Encomenda DHLink parada, não encontrada ou dada como entregue

**Não encontrada.** Confirma o código na tua encomenda da DHgate e experimenta o número de seguimento (*Tracking No.*) em vez do número `FC` da DHLink. Um número da transportadora local não dá resultado na página da DHLink: pesquisa-o diretamente nessa transportadora.

**Parada depois do voo ou da alfândega.** Procura um «Last Leg No.» no resultado e consulta essa transportadora, que muitas vezes tem leituras mais recentes do que a DHLink. Vê mais em [porque é que o rastreio não atualiza](guide:tracking-not-updating) e [rastrear uma encomenda vinda da China](guide:tracking-from-china).

**Entregue mas não recebida, atrasada ou perdida.** As regras de reembolso e indemnização da DHLink destinam-se aos vendedores: é a loja que reclama junto da DHLink, e tu tratas do assunto com o vendedor ou com a DHgate. Essas regras excluem problemas causados pelo comprador, como atrasos por falta de levantamento ou assinatura, ou falhas no desalfandegamento quando o destinatário não colabora a tempo. Responde depressa aos pedidos da alfândega ou da transportadora e avisa cedo o vendedor: ele só pode apresentar pedido até 90 dias seguidos após criar a ordem logística. Consulta [entregue mas não recebida](guide:delivered-not-received) e [encomenda perdida](guide:lost-parcel).

## Contactos e apoio ao cliente da DHLink

Consultada a 14 de novembro de 2026, a página de contactos da DHLink indica apenas um email de apoio ao cliente, `dhlinkservice@dhlink.com`, um acesso de consulta na plataforma e o assistente de chat «AI 小飞». A página está em chinês e não lista nenhum número de telefone. Para questões sobre a tua compra, fala primeiro com o vendedor na DHgate; para um problema na entrega local, contacta a transportadora que tem a encomenda no teu país.

## Rastrear uma encomenda DHLink com o Peek

O [Peek](/) não consegue confirmar a DHLink só pelo formato do código: os números `FC`, `XZG`, `YLUS`, `QH`, `EYT`, `CYS`, `JXCYI` e `JXCZO` são correspondências fracas. Cola-o na mesma: o Peek acompanha mais de 3.500 transportadoras, mostra «A verificar serviços de seguimento…» e, quando a DHLink reconhece a encomenda, indica «DHLink tem esta encomenda.»; caso contrário, toca em «Alterar» e escolhe a DHLink. Um código `JXCYI` ou `JXCZO` também pode ser da Juxi Express: o Peek pergunta primeiro à DHLink e, enquanto nenhuma responder, mostra «Provavelmente DHLink»; se o vendedor enviou pela Juxi, muda para a Juxi Express em «Alterar». Um código da China Post (duas letras, 9 algarismos, `CN`) segue para a [China Post](carrier:china-post).

O Peek lê o seguimento da DHLink, mostra o estado, o país de destino e a localidade de cada evento, e apresenta cada leitura uma única vez (como a DHLink a escreve, quase sempre em inglês), limpando códigos postais e referências de encomenda. As leituras feitas na China são interpretadas na hora de Pequim, as da USPS na hora do respetivo estado norte-americano e as da Pactic, na rota via Hungria, na hora de Budapeste, aparecendo no fuso horário do teu dispositivo (no peektracker.com e na app para iPhone, uma linha no fim do histórico recorda-o: «Horas no teu fuso horário: …»); as restantes mantêm a hora dada pela DHLink. A DHLink não fornece peso, nome do serviço nem hora de entrega; a única previsão de entrega que o Peek mostra vem de uma leitura reencaminhada da USPS do tipo `Out for Delivery, Expected Delivery …` quando traz uma janela horária para o próprio dia. Quando o «Last Leg No.» é da GOFO (`GFUS` e 14 algarismos), da SwiftX (`SWX` e 18 algarismos) ou da DPD Suíça (14 algarismos começados por `060` ou `061`), o Peek mostra essa transportadora e o seu número, consulta-a e passa a segui-la assim que tiver progresso mais recente; outros números de última etapa mantêm-se no histórico da DHLink.

Enquanto o próprio seguimento da DHLink responder, o Peek verifica a cada 10 minutos, ou a cada 2 minutos no dia em que a encomenda sai para distribuição (a partir das 6h00, hora local, ou, se houver uma janela horária, a partir de meia hora antes de ela começar); através de serviços de seguimento, verifica a cada 15 minutos. Quando passa a seguir a encomenda na GOFO, consulta a cada 30 minutos, mesmo em distribuição. Ao fim de dois dias sem novidades (12 horas enquanto continuar em «Etiqueta criada» ou «Aceite pela transportadora»), passa a verificar de hora a hora. Podes receber alertas em «Todas as leituras», «Passos importantes» ou «Só a entrega». O Peek não pode contactar a DHLink, a DHgate nem o vendedor por ti.

## Perguntas sobre o rastreio DHLink

### O que é a DHLink?

É a plataforma de logística transfronteiriça da DHgate, gerida em Pequim pela 驼飞侠科技（北京）有限公司. Fundada em 2014, expede encomendas a partir da China e de armazéns no estrangeiro através de linhas próprias e de transportadoras parceiras.

### Quem entrega a minha encomenda da DHLink?

Uma transportadora no teu país. Nas linhas para os EUA, a DHLink passa encomendas à UPS, USPS ou FedEx, ou ainda à GOFO, SwiftX ou UniUni; na Suíça, por vezes à DPD Suíça; e nos envios `CN` da China Post, ao operador postal nacional. Procura o «Last Leg No.» no resultado da DHLink.

### O que significa «The goods have been delivered to UPS/USPS/Fedex»?

Quer dizer que a DHLink entregou a encomenda à transportadora norte-americana responsável pela etapa seguinte. Ainda não foi entregue na tua morada: as leituras dessa transportadora aparecem a seguir.

:::sources
- [DHLink: Sobre nós (关于我们)](https://www.dhlink.com/about/introduce) – fundada em 2014, plataforma de logística transfronteiriça da DHgate, armazéns no estrangeiro e entrega no país de destino
- [DHLink: Página inicial (首页)](https://www.dhlink.com/) – envio direto da China e armazéns no estrangeiro
- [DHLink: Rastreio (轨迹查询)](https://www.dhlink.com/trackQuery?lang=en) – pesquisa até 40 números, números aceites, campos DHLink No., Tracking No., Last Leg No., Signed for, mensagem de número desconhecido e idioma por defeito
- [DHLink: Contactos (联系我们)](https://www.dhlink.com/about/connect) – email dhlinkservice@dhlink.com, formulário na plataforma, assistente AI 小飞, sem telefone
- [DHLink: Acordo de serviço (服务协议)](https://www.dhlink.com/agreementPage/service) – operador 驼飞侠科技（北京）有限公司
- [DHgate Seller Help: Regras de indemnização DHLink (DHLink物流异常件赔付规则)](https://seller.dhgate.com/help/c7502/516002.html) – indemnização para vendedores, exclusões por falta de levantamento ou de apoio na alfândega, prazo de 90 dias seguidos após a ordem logística
- [Portal das Finanças (AT): Mercadorias eCommerce](https://info.portaldasfinancas.gov.pt/pt/destaques/Paginas/Mercadorias_eCommerce.aspx) – fim da franquia até 150 € e direitos aduaneiros de 3 € por adição desde 1 de julho de 2026
- [Comissão Europeia: EU Customs Reform (em inglês)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – taxa de tratamento da União de 2 € a partir de 1 de novembro de 2026
- [Comissão Europeia: Customs Guidance on EUR 3 customs duty, PDF (em inglês)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – taxa fixa de 2 € por adição, contada como nos 3 €, nas vendas à distância independentemente do valor, devida na aceitação da declaração aduaneira
- [Universal Parcel Scraper: DHLink (em inglês)](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/dhlink) – formatos de número, leituras (Pactic, UniUni, China Post, USPS), fusos horários, passagem para GOFO, SwiftX e DPD Suíça, funcionamento no Peek
- [Universal Parcel Scraper: GOFO Express (em inglês)](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/gofo) – seguimento GOFO compatível com números FC da DHLink e cadência de consulta de 30 minutos
:::
