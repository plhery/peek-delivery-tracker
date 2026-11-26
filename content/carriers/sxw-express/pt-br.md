---
title: Rastreamento SXW Express: códigos SXW, etapas e entrega
description: Rastreie encomendas SXW Express (Shengxingwang) de Shenzhen: formato SXW, rastreador próprio, significado das etapas, entrega pela GOFO e contato.
slug: rastreamento-sxw-express
published: 2026-11-26
updated: 2026-11-26
---

SXW Express é o nome usado pelo Peek para a Shenzhen Shengxingwang Supply Chain Management Co., Ltd. (深圳胜兴旺供应链管理有限公司), empresa de logística em Shenzhen, na China, que transporta encomendas ao exterior para empresas e vendedores online. Se uma loja chinesa enviou seu pedido pela SXW, procure um código com SXW e doze dígitos — o formato que o Peek associa à SXW — e cole-o na caixa acima. Você também pode consultar no próprio rastreador da SXW, aberto pela caixa "Express Tracking" em sxwexpress.cn. O Peek lê esse mesmo rastreador para você.

## Código de rastreamento SXW Express: como ele é

O site da SXW não informa formato de código. O padrão que o Peek reconhece como da SXW Express tem SXW e doze dígitos, somando 15 caracteres. Com dígitos inventados:

| Código | O que é |
| --- | --- |
| `SXW482910573617` | SXW e doze dígitos: formato da SXW Express. O Peek identifica a SXW Express na hora. |
| `SXW48291057317` | Apenas onze dígitos: não segue o formato da SXW. Confira se falta um dígito. |
| `GFUS48291057364917` | GFUS e catorze dígitos: código da [GOFO Express](carrier:gofo). O registro da SXW pode fornecer um para envios aos EUA (veja abaixo). |

Espaços, hifens e letras minúsculas não fazem diferença ao colar o código no Peek.

**Onde encontrar.** O vendedor contrata o envio com a SXW e repassa o código. Se não encontrar, veja [como encontrar seu código de rastreamento](guide:find-tracking-number).

**No site da SXW.** Na página inicial de sxwexpress.cn, a aba "Express Tracking" traz o campo "Enter your tracking number here!" e o botão "Track". O resultado abre em nova aba no rastreador da SXW, em chinês, que também possui página em inglês. Ao lado da caixa, o logotipo da 17TRACK leva a um serviço externo de rastreamento, não ao rastreador da SXW.

## Status de rastreamento da SXW Express explicados

A maioria das etapas no rastreador da SXW aparece em inglês; três aparecem em chinês, que o Peek exibe em inglês. Principais linhas:

| O rastreador da SXW mostra | O que significa para você | O Peek exibe |
| --- | --- | --- |
| `Parcel Data Received` | O vendedor enviou os dados da encomenda. Ninguém está com o pacote ainda. | "Etiqueta criada" |
| `货物电子信息已经收到` → `The electronic cargo information has been received` | Significa que os dados eletrônicos da carga foram recebidos: o mesmo passo, gerado pelo sistema da SXW. | "Etiqueta criada" |
| `到达收货点` → `Arrived at the receiving point` | Chegou ao ponto de recebimento: a SXW recebeu o pacote. Primeira leitura já em suas mãos. | "Recebida pela transportadora" |
| `Parcel picked up` | O pacote foi coletado: primeira leitura na linha de transporte que o leva. | "Recebida pela transportadora" |
| `货物离开操作中心` → `Goods leave the operation center` | Saiu do centro operacional da própria SXW. | "Em trânsito" |
| `Parcel received at Shenzhen Sorting Centre.` | Um centro de triagem em Shenzhen recebeu o pacote. | "Em trânsito" |
| `Parcel processed at Shenzhen Sorting Centre.` | Triado ali. | "Em trânsito" |
| `Parcel departure in Shenzhen Sorting Centre.` | Saindo desse centro de triagem. | "Em trânsito" |
| `Parcel departure in Transit Facility, Parcel Departure` | Saiu de uma unidade de trânsito e segue viagem. | "Em trânsito" |
| `Customs declaration completed` | Declaração de exportação concluída na China, antes do voo. É a alfândega chinesa, não a do seu país, e o pacote não está retido. | "Em trânsito" |
| `Parcel is leaving Airport, Parcel Leaving Port` | O voo está partindo do aeroporto na China após a declaração de exportação. | "Em trânsito" |

**Linhas em chinês e inglês.** As linhas em chinês vêm do próprio sistema da SXW, enquanto a maioria das linhas em inglês vem da linha de transporte que leva o pacote. Por isso duas linhas podem dizer quase o mesmo: `货物电子信息已经收到` e `Parcel Data Received` indicam ambas os dados recebidos, e `到达收货点` e `Parcel picked up` indicam que o pacote já foi coletado.

**Outras linhas.** O rastreador da SXW pode mostrar textos fora desta tabela. O Peek interpreta tais linhas pelas palavras sempre que possível e as exibe conforme escritas pelo rastreador.

**Horários.** O Peek lê horários na China no horário de Pequim e os exibe no fuso horário do seu aparelho; a partir da primeira linha cuja localidade termine com o código de outro país, mantém o horário local daquele país. No inverno do hemisfério norte, 20:00 em Pequim equivale a 13:00 em Paris ou Berlim, 12:00 em Londres e 7:00 em Nova York. Etapas datadas após o momento da consulta no horário de Pequim aguardam até a hora chegar.

## Quem entrega encomendas da SXW Express

O site da SXW diz oferecer "DDU/DDP door-to-door delivery" e "local last-mile delivery", com rede cobrindo Europa, América, Sudeste Asiático, Oriente Médio e África. Sua lista de produtos cita linhas UPS saindo de Hong Kong, uma "ARAMEX Special Line", "Middle East and Europe small package" e "US Express Mail Small Packet", sem dizer qual empresa entrega no seu país.

**O que mostra o código do Peek.** Em envios aos EUA, o registro da SXW pode informar um código da [GOFO Express](carrier:gofo) (GFUS e catorze dígitos) ao lado do da SXW. Quando isso ocorre, o Peek exibe a GOFO Express como entregadora ("Entrega por GOFO Express") com o código e link para o rastreamento dela — salvo se o canal de envio no registro citar outra empresa, como UPS, caso em que nenhuma entregadora é nomeada. Enquanto a GOFO lista o pacote sem escaneá-lo, aparece "Anunciada a GOFO Express, ainda não recebida"; assim que a GOFO exibir uma leitura tão recente quanto a última da SXW ou mais nova, o Peek passa a acompanhar lá. Quando o canal cita a [Swiss Post](carrier:swiss-post) com código válido em envios para Suíça ou Liechtenstein, o Peek transfere para a Swiss Post da mesma forma.

**Tentativas e retirada.** Seguem as regras da transportadora entregadora, e o site da SXW não publica nenhuma. Pergunte ao vendedor qual empresa entrega e peça o código dela; veja [entrega frustrada](guide:missed-delivery).

**Taxas alfandegárias na UE.** Desde 1º de julho de 2026, compras online vindas de fora da UE em pacotes de até €150 pagam direito aduaneiro de €3 por tipo de produto. Desde 1º de novembro de 2026, incide também a taxa de manuseio da União de €2 por tipo de produto, qualquer que seja o valor. Um item significa as mercadorias do pacote com a mesma classificação tarifária, descrição e, quando exigida, origem, independentemente da quantidade. Sobre o que pagar e como pagar no Reino Unido, Irlanda, EUA, Canadá e Austrália, veja [encomendas retidas na alfândega](guide:customs).

## Encomenda SXW Express que não chega ou rastreamento parado

**Apenas `Parcel Data Received` ou `货物电子信息已经收到`.** O vendedor criou o envio, mas ninguém escaneou o pacote. Se nada mudar após alguns dias, confirme com o vendedor se foi enviado.

**"Aguardando SXW Express".** O Peek ainda não tem etapas reais para o código: o rastreador da SXW não o conhece, não tem etapas ou guarda apenas etapas escritas com antecedência, e os serviços de rastreamento também não têm nada. O Peek continua verificando. Quando a SXW lista o envio sem leitura ainda ou guarda apenas etapas previstas, mantém "Aguardando SXW Express". Quando nem o rastreador da SXW nem os serviços de rastreamento reconhecem o código, mostra "Aguardando SXW Express" e, dois dias depois, "Depois de dois dias, a transportadora ainda não reconhece este código. Confira o código, escolha outra transportadora ou exclua a encomenda." Compare com a mensagem do vendedor: SXW e doze dígitos.

**`Customs declaration completed`.** É a declaração de exportação na China antes do voo, não a alfândega do seu país. O pacote não está retido, por isso o Peek mostra "Em trânsito". Se mais tarde cobrarem taxas de importação, veja [encomendas retidas na alfândega](guide:customs).

**Dias sem nova etapa.** Se o Peek mostrar um código GOFO Express, consulte o rastreamento da GOFO. Caso contrário, siga a estimativa da loja e contate o vendedor após o prazo. Veja mais em [por que o rastreamento para de atualizar](guide:tracking-not-updating) e [rastrear encomendas da China](guide:tracking-from-china).

**Entregue, mas nada chegou.** Procure a empresa entregadora e o vendedor. Veja mais em [encomenda entregue mas não recebida](guide:delivered-not-received).

**Extravio ou devolução.** O vendedor contratou o envio com a SXW, portanto peça reembolso ou reenvio ao vendedor ou ao marketplace. Veja [encomendas extraviadas](guide:lost-parcel) e [devolvida ao remetente](guide:returned-to-sender).

## Telefone e atendimento da SXW Express

O site da SXW, sxwexpress.cn, não informa telefone, e-mail nem endereço físico. A página "Contact US" contém apenas o título, e o painel "Online Support" está vazio. O login de membros destina-se a contas comerciais da SXW: você não precisa de conta para rastrear.

O cliente da SXW é o vendedor, portanto pergunte primeiro ao vendedor sobre o pedido — e ao marketplace se ele não responder. Para entregas da GOFO Express nos EUA, contate a GOFO sobre a entrega.

## Rastrear uma encomenda SXW Express com o Peek

O [Peek](/) identifica a SXW Express na hora para códigos com SXW e doze dígitos ("Transportadora detectada"). Nenhuma outra transportadora no catálogo usa esse formato. Se o vendedor passou outro formato, cole mesmo assim: o Peek acompanha mais de 4.500 transportadoras. Para associar um código à SXW manualmente, toque em "Trocar" e busque SXW Express (digitar Shengxingwang ou 胜兴旺 também encontra), ou use "Trocar transportadora" em um pacote já adicionado.

O Peek consulta o próprio rastreador da SXW — o mesmo aberto pela caixa em sxwexpress.cn — sem conta, código postal nem telefone. Exibe o status, cada etapa com localidade quando informada e o país de destino. Omite nomes, endereços, códigos postais, peso, quantidade de volumes, referência do cliente, dados de pagamento, assinatura e comprovante de entrega que o registro possa conter. O rastreador da SXW não dá previsão de entrega, então o Peek não exibe nenhuma.

O Peek retém qualquer etapa datada após a consulta e qualquer etapa gravada mais de 12 horas antes do horário exibido — ou mais de 6 horas antes no mesmo segundo que uma etapa assim —, pois etapas escritas antecipadamente são previsões, não leituras. O histórico abre então com "Outros sites podem mostrar mais etapas, mas são apenas previsões." até a entrega ou devolução, e "Ver" lista essas etapas em cinza com "Previsto para" e a data. Elas nunca contam como leituras reais: não alteram status nem disparam alertas. Quando o próprio rastreador da SXW não reconhece o código ou não responde, o Peek consulta serviços de rastreamento e mostra a cópia deles no texto deles.

Além das transferências para GOFO Express e Swiss Post acima, o Peek permanece no rastreador da SXW. Se o vendedor informar o código da transportadora local, adicione-o ao Peek como encomenda separada.

Enquanto você mantém a encomenda aberta, ou no dia da entrega com alertas ligados, o Peek verifica até a cada 10 minutos; nos demais horários e quando o envio fica sem movimentação, a cada hora (quando apenas serviços de rastreamento respondem, a cada hora, a cada 3 horas após um dia sem novidades e até a cada 15 minutos no dia da entrega sob a mesma condição). Pode notificar cada movimentação, etapas importantes ou só a entrega. "Etapas importantes" cobre alfândega, problemas, rota de entrega, tentativas frustradas, retirada, entrega e devoluções — portanto nenhuma etapa da tabela acima dispara esse alerta, inclusive `Customs declaration completed`. "Só a entrega" cobre saída para entrega e entrega. Não pode contatar a SXW Express nem o vendedor por você.

## Perguntas frequentes sobre o rastreamento SXW Express

### O que é a SXW Express?

É o nome usado pelo Peek para a Shenzhen Shengxingwang Supply Chain Management Co., Ltd. (深圳胜兴旺供应链管理有限公司), em sxwexpress.cn. Seu site a descreve como provedora logística internacional completa em Shenzhen: frete marítimo e aéreo, ferrovia para a Europa, expresso internacional e entrega porta a porta para exportadores e vendedores online.

### Onde posso rastrear uma encomenda SXW Express por conta própria?

Na caixa "Express Tracking" da página inicial de sxwexpress.cn: digite o código e clique em "Track". O rastreador da SXW abre em chinês e oferece também página em inglês. O logotipo da 17TRACK ao lado da caixa leva a outro serviço de rastreamento.

### O que significa "Customs declaration completed" no rastreamento SXW?

Que a declaração de exportação na China foi concluída antes do voo. O Peek mostra como "Em trânsito": trata-se da alfândega chinesa, não da do seu país, e a encomenda não está retida.

### Quem entrega encomendas da SXW Express no meu país?

O site da SXW não informa. Em envios aos EUA, o registro da SXW pode incluir um código GOFO Express: o Peek então nomeia a GOFO e passa a acompanhá-la quando houver leitura dela. Nos demais casos, pergunte ao vendedor qual empresa entrega e peça o código dela.

### Quem devo procurar se uma encomenda SXW Express desaparecer?

O vendedor onde você comprou, pois foi ele quem contratou a SXW. O site da SXW não informa telefone nem e-mail. Se o vendedor não responder, abra disputa no marketplace.

:::sources
- [SXW Express: Shenzhen Shengxingwang Supply Chain Management Co., Ltd. (home page)](http://www.sxwexpress.cn/) – razão social, caixa "Express Tracking" abrindo o rastreador da SXW em nova aba, logotipo da 17TRACK ao lado, login de membros, produtos e descrição com "DDU/DDP door-to-door delivery"
- [SXW Express: About US](http://www.sxwexpress.cn/about.html) – sede em Shenzhen, logística internacional marítima, aérea, ferroviária e expressa, entrega DDP/DDU e "local last-mile delivery", rede na Europa, América, Sudeste Asiático, Oriente Médio e África
- [SXW Express: Products](http://www.sxwexpress.cn/products/default.html) – produtos: linhas UPS de Hong Kong, ARAMEX Special Line, Middle East and Europe small package e US Express Mail Small Packet
- [SXW Express: Contact US](http://www.sxwexpress.cn/contact.html) – página de contato sem telefone, e-mail nem endereço e painel "Online Support" vazio
- [SXW Express: 会员登录 (member login)](http://www.sxwexpress.cn:8082/) – razão social em chinês no sistema de rastreamento da SXW
- [SXW Express: trace (tracker, English page)](http://www.sxwexpress.cn:8082/en/trackIndex.htm) – rastreador da SXW em inglês; a página em chinês abre pela caixa da página inicial
- [European Commission: EU Customs Reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – direito aduaneiro de €3 por tipo de produto em pacotes de até €150 desde 1º de julho de 2026 e taxa de manuseio da União de €2 desde 1º de novembro de 2026
- [European Commission: Guidance on the €3 customs duty and the Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – taxa de manuseio por item qualquer que seja o valor e definição de item
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – nomes, formato SXW, leitura do próprio rastreador da SXW pelo Peek, textos e etapas, relógio de Pequim, retenção de etapas datadas ou escritas antecipadamente, serviços de rastreamento como alternativa, código GOFO Express e transferência salvo quando o canal cita outra empresa, e o que o Peek mostra ou omite
:::
