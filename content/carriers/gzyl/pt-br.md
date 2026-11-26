---
title: Rastreamento GZYL: códigos YL…YQ, etapas e entregas
description: Rastreie encomendas GZYL (广州云链) de Guangzhou: formato YL…YQ, significado das etapas e por que o status Delivered pode ser escrito antecipadamente.
slug: rastreamento-gzyl
published: 2026-11-26
updated: 2026-11-26
---

A GZYL (广州云链, Guangzhou Yunlian) é a 广州云链货运代理有限公司, uma agente de cargas em Guangzhou, na China. Se uma loja chinesa enviou seu pedido pela GZYL, seu código tem o formato YL, dez dígitos e YQ: cole-o na caixa acima. O site da GZYL, yunlianhouyun.com, está em chinês, e sua busca de encomendas (小包查询) abre um rastreador em endereço direto de servidor. Leia esse rastreador com atenção: a GZYL grava boa parte do trajeto com antecedência, de modo que uma etapa ali — inclusive `Delivered` — nem sempre é uma leitura real.

## Código de rastreamento GZYL: como ele é

Os conhecimentos da GZYL têm YL, dez dígitos e YQ, somando 14 caracteres. Com dígitos inventados:

| Código | O que é |
| --- | --- |
| `YL4829105736YQ` | YL, dez dígitos e YQ: formato dos conhecimentos da GZYL. O Peek verifica no rastreador da GZYL. |
| `YL7384829105YQ` | YL738, sete dígitos e YQ: por esse formato, o Peek identifica a GZYL na hora. |
| `YL482910573YQ` | Apenas nove dígitos: não segue o formato da GZYL. Confira se falta um dígito. |
| `GZXH4829105736YQ` | GZXH, dez dígitos e YQ: [Xinghe Freight](carrier:xinghe-freight) (星和货运), outra agente de cargas. O rastreamento dela roda no rastreador da GZYL, com as palavras da GZYL. |
| `JXCAA4829105736YQ` | Também termina em YQ, como muitos códigos chineses, mas começa com JXC: código da [Juxi Express](carrier:juxi-express). |

Espaços e letras minúsculas não fazem diferença ao colar o código no Peek.

**Onde encontrar.** O vendedor contrata o envio com a GZYL e repassa o código. Se não encontrar, veja [como encontrar seu código de rastreamento](guide:find-tracking-number).

## Status de rastreamento da GZYL explicados

A GZYL escreve suas próprias etapas em inglês, inclusive com erros de grafia. Na ordem em que a encomenda passa por elas:

| A GZYL mostra | O que significa para você | O Peek exibe |
| --- | --- | --- |
| `Order information received` | O vendedor enviou os dados do pedido à GZYL. A GZYL ainda não está com o pacote. | "Etiqueta criada" |
| `Arrive at the cross border receiving point` | A GZYL recebeu o pacote: primeira leitura já em suas mãos. | "Recebida pela transportadora" |
| `Goods leave the operation center` | O pacote saiu do centro operacional da GZYL. | "Em trânsito" |
| `Arrive at the international airport` | No aeroporto, aguardando o voo. | "Em trânsito" |
| `Departing from the airport` | O voo partiu. | "Em trânsito" |
| `Item arrives at destination airport` | Pousou no seu país. | "Em trânsito" |
| `local carrier Agent Import customs clearance` | Um agente no seu país está realizando o desembaraço aduaneiro de importação. | "Na alfândega" |
| `The import customs keep it for inspection` | A alfândega do seu país está inspecionando a encomenda. | "Na alfândega" |
| `Customs clearance completed` | Liberada pela alfândega e de volta ao trajeto. | "Em trânsito" |
| `Internationaliy Package received at processing center` | Um centro de processamento no seu país recebeu a encomenda liberada. Não é uma nova coleta. | "Em trânsito" |
| `Leave the email processing center` | O pacote saiu desse centro de processamento. | "Em trânsito" |
| `Arrival sorting, distribution center` | Em um centro de distribuição no seu país, com a cidade após o texto. | "Em trânsito" |
| `Leaving the sorting center` | Saindo de um centro de triagem, com a cidade. | "Em trânsito" |
| `Package is on the way` | Seguindo viagem no seu país, com uma cidade. Não indica que saiu para entrega. | "Em trânsito" |
| `Delivered` | A entrega, com a cidade. Pode ser um plano previsto, não uma leitura real: veja abaixo. | "Entregue", com aviso de cronograma fixo, salvo se o Peek retiver a etapa (abaixo) |

**Via Estados Unidos.** Um pacote também pode exibir `Los Angeles Processing Center has received it`, com uma cidade entre colchetes depois: um centro de processamento nos Estados Unidos recebeu o volume no caminho até o destino. O Peek mostra "Em trânsito".

**Linha em chinês.** Para pedidos ainda sem leituras, a plataforma onde roda o rastreador da GZYL insere sozinha `货物电子信息已经收到` ("as informações eletrônicas da mercadoria foram recebidas"). O vendedor criou o pedido, mas ninguém escaneou o pacote. O Peek exibe a linha em chinês sob "Etiqueta criada".

**Via China Post.** Um pacote também pode mostrar `广州处理中心已收寄`, com a cidade entre colchetes: aceitação da China Post no centro de processamento de Guangzhou, repassada em chinês pela GZYL. Nos códigos da Xinghe Freight, `香港处理中心已收寄` representa a mesma aceitação no centro de Hong Kong. O Peek mostra ambas as linhas em chinês sob "Recebida pela transportadora".

**Horários.** Todo horário no rastreador da GZYL segue o relógio de Pequim, inclusive etapas no seu país, sem fuso exibido. No inverno do hemisfério norte, 20:00 lá equivale ao meio-dia em Londres e às 7:00 em Nova York.

**Etapas escritas antecipadamente.** A GZYL pode gravar toda a linha do tempo de uma encomenda bem antes das datas exibidas, da coleta à última etapa no seu país, e o rastreador revela cada etapa quando o horário chega. Uma etapa gravada assim é um plano previsto, não uma leitura real. A [Meiteshun](carrier:mtscn), na mesma plataforma, também faz isso. O cronograma da GZYL termina então em um `Delivered` inserido pela plataforma em lote noturno no dia exibido: datado no dia seguinte à última etapa prevista, em horário comercial de Pequim, qualquer que seja o país. Serviços de rastreamento que copiam o registro da GZYL podem mostrar essas etapas também, então um `Delivered` no rastreador da GZYL ou em um serviço de rastreamento não prova que o pacote chegou.

## Quem entrega encomendas da GZYL

O Peek não sabe quem entrega encomendas da GZYL nos EUA, Reino Unido, Irlanda, Canadá ou Austrália: as etapas no país de destino não citam nenhuma transportadora. A única empresa para a qual o Peek pode transferir um envio GZYL é a [Swiss Post](carrier:swiss-post), na Suíça e em Liechtenstein.

O Peek não encontrou prazos de entrega da GZYL nem regras sobre tentativas, pontos de retirada ou guarda: são da empresa que entrega. Na página de rastreamento da GZYL, observe a coluna 渠道转单号 (código de transferência) e 跟踪号码 (número de rastreamento) caso contenha algo diferente do seu código YL…YQ: qualquer um deles pode ser o código da transportadora local, que você pode adicionar ao Peek como encomenda separada. Se não houver nenhum, pergunte ao vendedor qual empresa entrega e peça o código dela.

## Encomenda GZYL que não chega ou rastreamento parado

**Apenas `Order information received` ou `货物电子信息已经收到`.** O vendedor criou o pedido, mas a GZYL ainda não escaneou o pacote. Se nada mudar após alguns dias, confirme com o vendedor se foi enviado.

**Parada na alfândega.** Se cobrarem taxas de importação, veja [encomendas retidas na alfândega](guide:customs). Na Irlanda, desde 1º de novembro de 2026, incide a taxa de manuseio da UE de €2 por item distinto (dois idênticos contam como um), qualquer que seja o valor do pacote, além do direito aduaneiro; se a loja não cobrou ambos no checkout, a entregadora pode cobrá-los antes da entrega. Se ficar dias parada ali, avise o vendedor.

**O rastreador da GZYL ou outro serviço mostra mais etapas do que o Peek.** O Peek retém etapas escritas antes do horário exibido, o `Delivered` que encerra o cronograma da GZYL e etapas datadas após a última consulta (abaixo), então as etapas extras podem ser o plano da GZYL, não leituras reais. Etapas escritas antecipadamente nunca contam como leituras: não alteram status nem previsão e não disparam alertas. Quando retém etapas previstas, o histórico do Peek abre com uma linha até a entrega ou devolução: "Outros sites podem mostrar mais etapas, mas são apenas previsões." Em seguida, "Ver" lista essas etapas em cinza, da mais recente para a mais antiga, com a data informada pela GZYL ("Previsto para seg 12 out"). Siga a estimativa dada pela loja e fale com o vendedor se o prazo vencer. Veja mais em [por que o rastreamento para de atualizar](guide:tracking-not-updating) e [rastrear encomendas da China](guide:tracking-from-china).

**`Delivered`, mas nada chegou.** A etapa pode integrar o cronograma previsto da GZYL, não ser uma leitura real. Salvo quando a Swiss Post entrega, o Peek avisa sob "Entregue": "As atualizações de GZYL seguem um cronograma fixo, sem assinatura. Se nada chegou, fale com o vendedor." Avise o vendedor e pergunte qual transportadora faz a entrega e qual é o código dela. Veja mais em [encomenda entregue mas não recebida](guide:delivered-not-received).

**Nada encontrado.** Para códigos desconhecidos, o rastreador da GZYL exibe um resultado com seu número em 参考号 (referência) e todos os demais campos vazios. O Peek trata essa resposta como não encontrado: mostra "Aguardando GZYL" e, dois dias depois, "Confira o código de rastreamento". Compare o número com a mensagem do vendedor.

**Extravio ou devolução.** O vendedor contratou a GZYL, então peça reembolso ou reenvio ao vendedor ou ao marketplace. Veja [encomendas extraviadas](guide:lost-parcel) e [devolvida ao remetente](guide:returned-to-sender).

## Contato e atendimento da GZYL

A página de contato (联系我们) do site da GZYL, em chinês, lista um telefone 24 horas (全国24小时服务热线), uma conta no WeChat e uma conta de pós-venda no QQ (售后QQ), mas nada para destinatários: esses canais atendem os clientes da GZYL, que são os vendedores. Sobre seu pedido, fale com o vendedor — ou com o marketplace se o vendedor não responder.

## Rastrear uma encomenda GZYL com o Peek

O [Peek](/) identifica a GZYL na hora para códigos iniciados por YL738 com mais sete dígitos e YQ ("Transportadora detectada"). Outros códigos YL com dez dígitos e YQ são correspondência fraca. Cole mesmo assim: o Peek acompanha mais de 4.500 transportadoras. Ao adicionar, ele consulta o rastreador da GZYL ("Consultando serviços de rastreamento…") e exibe "GZYL está com esta encomenda." quando o rastreador devolve um resultado para o código — mesmo o resultado vazio retornado para códigos desconhecidos. Caso contrário, toque em "Trocar" e escolha GZYL em "Compatível com este código" (digitar 广州云链 também encontra), ou use "Trocar transportadora" em uma encomenda já adicionada. A referência do vendedor também funciona, mas apenas após selecionar GZYL manualmente em "Trocar" (ou "Trocar transportadora") buscando por GZYL; o Peek passa então a exibir também o número próprio do pedido, rotulado "GZYL rastreia com o código". Como o Peek remove hifens, referências com hifens não são encontradas: peça o código YL…YQ ao vendedor.

O Peek lê o próprio rastreador da GZYL e mostra o status, cada etapa com sua localidade e o país de destino quando consta no registro. Os horários são lidos no horário de Pequim e exibidos no fuso horário do seu aparelho; em peektracker.com e nos aplicativos iPhone e Android, o histórico termina com "Horários no seu fuso horário:" seguido do nome do fuso. O Peek omite nomes, endereços, códigos postais, peso, taxas, assinatura e comprovante de entrega que o registro possa conter, e não mostra previsão de entrega porque a GZYL não fornece nenhuma.

O Peek retém qualquer etapa gravada mais de doze horas antes do horário exibido, qualquer etapa gravada no mesmo segundo que outra gravada mais de meia hora antes do seu horário (exceto o registro do pedido) e qualquer etapa datada após a consulta no horário de Pequim. Em pedidos com etapas previstas, retém também `Delivered`, mesmo quando gravado no próprio dia. Quando a interface de dados do rastreador não tem etapas para o código, o Peek lê a página de rastreamento da GZYL; como essa página não informa quando cada etapa foi gravada, o Peek retém ali todos os `Delivered`. Ele segue apenas as etapas mantidas, nunca o status resumido. Um pedido cujas etapas sejam todas retidas fica ainda sem histórico no Peek, que exibe "Aguardando GZYL" sem pedir para conferir o código — pois considera etapas previstas como sinal de que a GZYL tem o pedido, assim como um envio listado ainda sem leitura — e continua consultando o rastreador da GZYL. O Peek também põe de lado cópias de serviços de rastreamento das etapas retidas como plano da GZYL, inclusive `Delivered`, mesmo mantendo outras etapas. Quando o rastreador da GZYL não responde, porém, o Peek consulta serviços de rastreamento, cujas cópias podem incluir etapas previstas.

Exceto pela transferência para a Swiss Post, o Peek permanece na GZYL: se o vendedor informar o código da transportadora local, adicione-o separadamente. Enquanto você mantém a encomenda aberta, ou no dia da entrega com alertas ligados, o Peek verifica até a cada 10 minutos — e até a cada 2 minutos no dia em que mostrar "Saiu para entrega" (a partir das 6h locais ou, se a transportadora der janela horária, meia hora antes de abrir), enquanto o próprio rastreador da GZYL ou da Swiss Post responde, embora nenhuma etapa conhecida da GZYL gere esse status. Nos demais horários, consulta a cada hora. Por serviços de rastreamento, a cada hora, a cada 3 horas após um dia sem novidades e até a cada 15 minutos no dia da entrega sob a mesma condição. Quando a Swiss Post assume, o Peek segue o ritmo dela. Após dois dias sem novidades (12 horas enquanto ainda mostrar "Etiqueta criada" ou "Recebida pela transportadora"), verifica a cada hora. Pode notificar cada movimentação, etapas importantes ou só a entrega: "Etapas importantes" cobre as duas etapas aduaneiras da GZYL e `Delivered`, não voos nem triagem. Não pode contatar a GZYL nem o vendedor por você.

## Perguntas frequentes sobre o rastreamento GZYL

### O que é a GZYL (广州云链)?

É a 广州云链货运代理有限公司 (Guangzhou Yunlian), uma agente de cargas em Guangzhou, na China, com site em chinês em yunlianhouyun.com. Seus conhecimentos têm YL, dez dígitos e YQ.

### Todo código YL…YQ é da GZYL?

Não necessariamente. YL, dez dígitos e YQ é o formato da GZYL, e nenhuma outra transportadora no catálogo do Peek o usa. O Peek identifica códigos iniciados por YL738 como GZYL pelo formato; para os demais, consulta o rastreador da GZYL primeiro. Com nove ou onze dígitos, não segue o formato da GZYL.

### Por que a GZYL diz que meu pacote foi entregue se não chegou?

Pode fazer parte do cronograma previsto pela GZYL. Ela escreve grande parte da linha do tempo antecipadamente e a encerra em `Delivered`, e essas etapas são previsões, não leituras reais. O Peek nunca conta essas etapas do rastreador da GZYL como leituras — inclusive `Delivered` — e, quando exibe "Entregue", acrescenta que o rastreamento da GZYL segue cronograma fixo. Avise o vendedor e pergunte qual empresa faz a entrega.

### Quem entrega encomendas da GZYL no meu país?

O Peek não sabe: as etapas da GZYL no país de destino não nomeiam nenhuma transportadora. A página da GZYL pode mostrar o código da entregadora em 渠道转单号 (código de transferência); se não mostrar, pergunte ao vendedor qual empresa entrega e qual é o código dela, e adicione-o ao Peek como encomenda separada. Apenas na Suíça e em Liechtenstein o Peek transfere envios da GZYL para a Swiss Post.

:::sources
- [广州云链货运代理有限公司 (GZYL): Home](http://yunlianhouyun.com/) – razão social, site em chinês e busca de encomendas (小包查询) no rastreador da GZYL
- [广州云链货运代理有限公司 (GZYL): 联系我们 (contact)](http://yunlianhouyun.com/contact.html) – telefone 24 horas, WeChat e QQ de pós-venda
- [Revenue: Union Handling Fee](https://www.revenue.ie/en/corporate/press-office/press-releases/2026/pr-092926-handling-fee.aspx) – taxa de €2 por item distinto na Irlanda desde 1º de novembro de 2026, qualquer que seja o valor, além do direito de €3, no checkout ou antes da entrega
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – nome e apelidos, rastreador em endereço direto, formatos YL…YQ, YL738 identificado na hora e demais como correspondência fraca, Xinghe Freight no rastreador da GZYL, busca por código ou referência com hifens, textos e etapas, linha chinesa da plataforma, cidades após textos, relógio de Pequim, cronograma antecipado, resposta para código desconhecido, colunas de transferência e rastreamento, Swiss Post como única transferência e o que o Peek mostra, retém ou omite
:::
