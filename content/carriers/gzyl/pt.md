---
title: Rastreio GZYL: número YL…YQ, estados e a quem perguntar
description: Seguir uma encomenda GZYL (广州云链) vinda da China: como é o número YL…YQ, o que significam os estados e porque pode aparecer entregue antes de chegar.
slug: gzyl-rastreio
published: 2026-11-18
updated: 2026-11-21
---

A GZYL (广州云链, Guangzhou Yunlian) é a 广州云链货运代理有限公司, um transitário de Guangzhou (Cantão), na China. Se uma loja chinesa enviou a tua encomenda pela GZYL, o número de seguimento começa por YL, tem dez algarismos pelo meio e termina em YQ: cola-o na caixa acima. O site da GZYL (`yunlianhouyun.com`) está em chinês, e a sua pesquisa de pequenos pacotes (小包查询) abre um sistema de seguimento alojado num simples endereço numérico de servidor, sem nome de domínio. Lê o que lá aparece com cautela: a GZYL escreve de antemão grande parte do percurso de uma encomenda, pelo que um passo — mesmo `Delivered` — nem sempre corresponde a uma leitura real.

## Número de rastreio GZYL: como é

As cartas de porte da GZYL têm YL, dez algarismos e YQ, num total de 14 caracteres. Com algarismos inventados:

| Número | O que é |
| --- | --- |
| `YL4829105736YQ` | YL, dez algarismos e YQ: o formato das cartas de porte da GZYL. O Peek confirma o número junto do sistema de seguimento da GZYL. |
| `YL7384829105YQ` | YL738, sete algarismos e YQ: por este formato, o Peek identifica a GZYL de imediato. |
| `YL482910573YQ` | Apenas nove algarismos: não é o formato da GZYL. Confirma se falta um algarismo. |
| `GZXH4829105736YQ` | GZXH, dez algarismos e YQ: Xinghe Freight (星和货运), outro transitário de Guangzhou. O seu seguimento corre no sistema da GZYL, com os mesmos textos. |
| `JXCAA4829105736YQ` | Também termina em YQ, como sucede em muitos transitários chineses, mas começa por JXC: é um número da Juxi Express, não da GZYL. |

No Peek, espaços e minúsculas não fazem diferença quando colas o número.

**Onde o encontrar.** É o vendedor quem contrata o envio com a GZYL e te transmite o número. Se não o encontrares, consulta [onde encontrar o número de seguimento da encomenda](guide:find-tracking-number).

## Estados do rastreio GZYL: o que significa cada passo

A GZYL escreve os seus próprios passos em inglês, gralhas incluídas. Pela ordem em que surgem numa encomenda:

| O que mostra a GZYL | O que significa para ti | O Peek mostra |
| --- | --- | --- |
| `Order information received` | A loja enviou os dados da encomenda à GZYL. A GZYL ainda não tem o pacote em mãos. | «Etiqueta criada» |
| `Arrive at the cross border receiving point` | A GZYL recebeu a encomenda: é a primeira leitura física nas suas mãos. | «Aceite pela transportadora» |
| `Goods leave the operation center` | A encomenda saiu do centro de operações da GZYL. | «Em trânsito» |
| `Arrive at the international airport` | No aeroporto, a aguardar o voo. | «Em trânsito» |
| `Departing from the airport` | O avião descolou. | «Em trânsito» |
| `Item arrives at destination airport` | Aterrou no país de destino. | «Em trânsito» |
| `local carrier Agent Import customs clearance` | Um despachante no país de destino está a tratar do desalfandegamento de importação. | «Na alfândega» |
| `The import customs keep it for inspection` | A alfândega do teu país retém a encomenda para inspeção. | «Na alfândega» |
| `Customs clearance completed` | Desalfandegada, a encomenda retoma o percurso. | «Em trânsito» |
| `Internationaliy Package received at processing center` | Um centro de tratamento no teu país recebeu a encomenda já desalfandegada. Não se trata de uma nova recolha na origem. | «Em trânsito» |
| `Leave the email processing center` | A encomenda saiu desse centro de tratamento. | «Em trânsito» |
| `Arrival sorting, distribution center` | Chegada a um centro de distribuição no país de destino, seguida do nome da localidade. | «Em trânsito» |
| `Leaving the sorting center` | Saída de um centro de triagem, com indicação da localidade. | «Em trânsito» |
| `Package is on the way` | Em encaminhamento no país de destino, com uma localidade. Não quer dizer que já tenha saído para entrega. | «Em trânsito» |
| `Delivered` | A entrega, com a localidade. Pode fazer parte de um plano escrito de antemão e não ser uma leitura (ver abaixo). | «Entregue», com nota sobre o horário fixo, salvo se o Peek retiver o passo (abaixo) |

Quando a GZYL acrescenta uma localidade a seguir aos quatro últimos textos da tabela, o Peek apresenta essa localidade como lugar do passo e retira o código postal. Os passos mantêm-se tal como a GZYL os escreve, exceto `Delivered`, que aparece em português no histórico: «Entregue».

**Passagem pelos Estados Unidos.** Uma encomenda também pode apresentar `Los Angeles Processing Center has received it`, seguido de uma cidade entre parênteses retos: um centro de processamento nos Estados Unidos recebeu-a a caminho do destino. O Peek mostra «Em trânsito».

**Uma linha em chinês.** Para uma encomenda ainda sem leituras, a plataforma onde corre o seguimento da GZYL gera automaticamente `货物电子信息已经收到`: os dados eletrónicos da mercadoria foram recebidos. O vendedor criou o pedido, mas ninguém leu ainda a encomenda. O Peek mostra essa linha em chinês sob «Etiqueta criada».

**Via China Post.** Uma encomenda pode igualmente mostrar `广州处理中心已收寄`, seguida da cidade entre parênteses: trata-se da aceitação pela China Post no seu centro de tratamento de Guangzhou, retransmitida em chinês pela GZYL. Nos números da Xinghe Freight, `香港处理中心已收寄` representa a mesma aceitação no centro de tratamento de Hong Kong. O Peek mostra qualquer uma delas em chinês sob «Aceite pela transportadora».

**Horas.** Todas as horas do sistema de seguimento da GZYL estão no fuso horário de Pequim (UTC+8), incluindo os passos ocorridos em Portugal ou noutro país de destino, sem indicação de fuso. Uma etapa às 20h00 em Pequim corresponde às 12h00 em Portugal continental e na Madeira no inverno (11h00 nos Açores) e às 13h00 com a hora de verão, do último domingo de março ao último domingo de outubro (12h00 nos Açores).

**Passos escritos com antecedência.** A GZYL pode deixar escrita de antemão toda a cronologia de uma encomenda, desde a recolha até ao último passo no país de destino, muito antes das horas indicadas em cada linha; o sistema vai mostrando cada etapa quando chega a respetiva hora. Um passo escrito assim é uma previsão, não uma leitura da tua encomenda. A [Meiteshun](carrier:mtscn), que usa a mesma plataforma, faz o mesmo. Na GZYL, esse plano termina depois num `Delivered` adicionado pela plataforma num lote ao fim do dia indicado: traz a data do dia seguinte ao último passo previsto, dentro do horário de expediente em Pequim, seja qual for o país de destino. Os serviços de seguimento que copiam o registo da GZYL também podem exibir esses passos, pelo que um `Delivered` visto no sistema da GZYL ou num serviço externo não prova que a encomenda tenha chegado.

## Quem entrega as encomendas GZYL

O Peek não sabe que transportadora entrega as encomendas da GZYL em Portugal ou noutros países da União Europeia: os passos da GZYL no país de destino não nomeiam nenhuma empresa. A única transportadora para a qual o Peek consegue transferir automaticamente uma encomenda da GZYL é a [Swiss Post](carrier:swiss-post), na Suíça e no Liechtenstein.

O Peek não encontrou prazos de entrega da GZYL nem regras sobre tentativas de entrega, pontos de recolha ou tempo de permanência em posto: tudo isso depende da transportadora responsável pela entrega final. Na página de seguimento da GZYL, espreita a coluna 渠道转单号 (número de transferência) e também 跟踪号码 (número de seguimento) caso mostre algo diferente do teu número YL…YQ: qualquer um deles pode ser o número da transportadora local, que podes adicionar ao Peek como encomenda separada. Se não aparecer nada, pergunta ao vendedor que empresa entrega a encomenda e qual é o respetivo número de seguimento.

## Encomenda GZYL atrasada ou rastreio parado

**Apenas `Order information received` ou `货物电子信息已经收到`.** O vendedor criou a encomenda, mas a GZYL ainda não fez nenhuma leitura. Se nada surgir ao fim de alguns dias, pergunta à loja se o pacote foi efetivamente expedido.

**Retida na alfândega.** As encomendas vindas da China entram na União Europeia a partir de fora do território aduaneiro comunitário. Desde 1 de julho de 2026, as compras online provenientes de fora do território aduaneiro da UE em remessas até 150 € pagam um direito aduaneiro de 3 € por categoria de artigo; desde 1 de novembro de 2026 acresce uma taxa de tramitação da União de 2 € por categoria de artigo, qualquer que seja o valor da encomenda (uma categoria reúne os bens de uma remessa com a mesma classificação pautal, a mesma descrição e, quando tem de ser declarada, a mesma origem, independentemente do número de unidades). Para ver como funcionam o IVA e o desalfandegamento, lê [encomenda retida na alfândega](guide:customs). Se ficar parada vários dias na alfândega, avisa o vendedor.

**O sistema da GZYL ou um serviço de seguimento mostra mais passos do que o Peek.** O Peek retém os passos escritos antes da hora que ostentam, o `Delivered` que encerra o plano da GZYL e qualquer passo com data posterior à última verificação (detalhes abaixo), pelo que as linhas extra podem pertencer apenas ao plano da GZYL e não ser leituras reais. Um passo escrito com antecedência nunca conta como leitura: não muda o estado nem a data prevista e não dispara alertas. Quando retém passos escritos com antecedência pela GZYL, o histórico de seguimento do Peek abre com uma linha até a encomenda ser entregue ou devolvida ao remetente: «Outros sites podem mostrar mais passos, mas são apenas previsões.» Em «Ver», logo a seguir, aparecem esses passos a cinzento, do mais recente para o mais antigo, cada um com o dia que a GZYL lhe deu («Previsto para seg 12 out»). Orienta-te pela estimativa de entrega indicada pela loja e fala com o vendedor quando esse prazo terminar. Mais informações em [porque é que o tracking não atualiza](guide:tracking-not-updating) e [rastrear encomendas da China](guide:tracking-from-china).

**`Delivered`, mas não chegou nada.** O passo pode fazer parte do plano da GZYL e não corresponder a uma leitura. Salvo se a entrega couber à Swiss Post, o Peek acrescenta por baixo de «Entregue»: «As atualizações de GZYL seguem um horário fixo, sem assinatura. Se nada chegou, contacta o vendedor.» Informa o vendedor de que não recebeste a encomenda e pede-lhe o nome da transportadora local e o seu número de seguimento. Vê também [encomenda dada como entregue mas não recebi](guide:delivered-not-received).

**Nada encontrado.** Para um número que não conhece, o sistema da GZYL apresenta um único resultado com o teu número em 参考号 (referência) e todos os outros campos em branco. O Peek entende essa resposta como uma remessa já listada pela GZYL mas ainda sem leitura: continua a consultar no ritmo descrito abaixo para passos escritos com antecedência e mostra «À espera de GZYL», sem pedir que verifiques o número. Compara por isso o número, carácter a carácter, com a mensagem da loja.

**Perdida ou devolvida.** Foi o vendedor quem contratou o transporte junto da GZYL, pelo que deves pedir o reembolso ou novo envio à loja ou ao marketplace. Consulta [encomenda perdida](guide:lost-parcel) e [encomenda devolvida ao remetente](guide:returned-to-sender).

## GZYL contacto: telefone e apoio ao cliente

A página de contactos (联系我们) do site da GZYL, em chinês, indica uma linha de atendimento permanente 24 horas por dia (全国24小时服务热线), uma conta WeChat e uma conta QQ de pós-venda (售后QQ), mas nenhum canal pensado para destinatários: estes contactos destinam-se aos clientes da GZYL, ou seja, aos vendedores. Como foi a loja quem contratou o envio, fala com o vendedor sobre a tua encomenda e recorre ao marketplace caso não obtenhas resposta.

## Rastrear encomenda GZYL com o Peek

O [Peek](/) identifica a GZYL de imediato quando o número começa por YL738, seguido de sete algarismos e YQ («Transportadora detetada»). Nos restantes números com YL, dez algarismos e YQ, o formato é apenas um indício fraco. Cola-o na mesma: o Peek acompanha mais de 4.500 transportadoras. Durante a adição, o Peek consulta o sistema da GZYL («A verificar serviços de seguimento…») e mostra «GZYL tem esta encomenda.» quando o sistema devolve um resultado para o número, mesmo o registo vazio apresentado para números desconhecidos. Caso contrário, toca em «Alterar» e escolhe a GZYL em «Corresponde a este número» (também aparece pesquisando 广州云链), ou usa «Alterar transportadora» numa encomenda já guardada. A referência da loja também funciona, mas só se escolheres manualmente a GZYL em «Alterar» (ou «Alterar transportadora»); o Peek mostra então o número próprio da remessa sob «GZYL segue a encomenda com o número». Como o Peek remove os hífenes, uma referência que os contenha não é encontrada: pede nesse caso o número YL…YQ ao vendedor.

O Peek lê o próprio sistema da GZYL e apresenta o estado, cada passo com a respetiva localidade e, quando consta no registo, o país de destino. As horas são lidas na hora de Pequim e convertidas para o fuso horário do teu dispositivo; em peektracker.com e nas aplicações para iPhone e Android, o histórico termina com «Horas no teu fuso horário:» e o nome desse fuso. O Peek deixa de fora nomes, morada, códigos postais, peso, custos, assinatura e prova de entrega que o registo da GZYL possa incluir, e não indica data prevista de entrega porque a GZYL não fornece nenhuma.

O Peek retém qualquer passo escrito mais de doze horas antes da hora que indica, ou mais de seis horas antes no mesmo segundo de um passo desses, bem como qualquer passo com data posterior ao momento da verificação, na hora de Pequim. Numa encomenda com passos escritos com antecedência, retém igualmente `Delivered`, mesmo quando escrito no próprio dia. Quando a interface de dados não devolve passos para um número, o Peek lê em alternativa a página de seguimento da GZYL; como essa página não mostra quando cada linha foi escrita, aí retém todos os `Delivered`. O estado baseia-se nos passos conservados e não no resumo geral do registo. Uma encomenda cujos passos fiquem todos retidos ainda não tem histórico no Peek e surge como «À espera de GZYL», sem pedido de confirmação do número: o Peek considera passos escritos com antecedência sinal de que a GZYL tem a encomenda, tal como num registo sem leituras, e continua a consultar o sistema da GZYL de 6 em 6 horas após as primeiras horas na aplicação para iPhone ou Android, ou na tua conta. Numa pesquisa sem conta, consulta durante as primeiras 6 horas, depois ao abrires a ligação (no máximo de 6 em 6 horas e, após os dois primeiros dias, no máximo uma vez por dia) e, enquanto as notificações estiverem ativas, uma vez por dia até 30 dias após a pesquisa. O Peek descarta também nas cópias dos serviços de seguimento os passos já retidos como plano da GZYL, `Delivered` incluído; contudo, quando o sistema da GZYL está inacessível, recorre aos serviços de seguimento, cujas cópias podem trazer passos escritos de antemão.

Salvo transferência para a Swiss Post, o Peek mantém-se na GZYL: se o vendedor te der o número da transportadora local, adiciona-o como encomenda separada. O Peek verifica até de 10 em 10 minutos enquanto tens a encomenda aberta ou no dia da entrega com as notificações ativas, e até de 2 em 2 minutos no dia em que uma encomenda entra «Em distribuição» (a partir das 06h00 locais ou de meia hora antes de uma janela horária indicada, enquanto responder diretamente o sistema da GZYL ou da Swiss Post; através de serviços de seguimento, verifica de hora a hora, de 3 em 3 horas após um dia sem leituras, e até de 15 em 15 minutos no dia da entrega enquanto a encomenda estiver aberta ou com notificações ativas), embora nenhum passo conhecido da GZYL ative esse estado. Depois de a Swiss Post assumir o envio, segue o ritmo dela. Após dois dias sem novidades (ou 12 horas em «Etiqueta criada» ou «Aceite pela transportadora»), verifica de hora a hora. Podes receber alertas para «Todos os registos», «Passos importantes» ou «Só a entrega»: «Passos importantes» abrange os dois passos de alfândega da GZYL e `Delivered`, não os voos nem a triagem. O Peek não pode contactar a GZYL nem o vendedor por ti.

## Perguntas sobre o rastreio GZYL

### O que é a GZYL (广州云链)?

É a 广州云链货运代理有限公司 (Guangzhou Yunlian), um transitário de Guangzhou, na China, com site em chinês em `yunlianhouyun.com`. As suas cartas de porte seguem o formato YL, dez algarismos e YQ.

### Qualquer número YL…YQ pertence de certeza à GZYL?

Não com certeza absoluta. YL, dez algarismos e YQ é o formato da GZYL e nenhuma outra transportadora do catálogo do Peek o utiliza. Quando começa por YL738, o Peek atribui-o logo à GZYL pelo formato; nos restantes, consulta primeiro o sistema da GZYL. Com nove ou onze algarismos, já não é o formato da GZYL.

### Porque é que a GZYL diz que a encomenda foi entregue se ainda não chegou?

Pode fazer parte do plano escrito de antemão pela GZYL: a empresa regista antecipadamente grande parte do percurso e termina-o num `Delivered`, sendo esses passos previsões e não leituras. O Peek nunca conta tais passos do sistema da GZYL como leituras, `Delivered` incluído, e quando exibe «Entregue» avisa logo abaixo que as atualizações seguem um horário fixo. Diz ao vendedor que nada chegou e pergunta que empresa faz a entrega.

### Quem entrega as encomendas GZYL em Portugal?

O Peek não sabe: os passos da GZYL no país de destino não indicam nenhuma transportadora. A página de seguimento da GZYL pode mostrar o número da transportadora local em 渠道转单号 (número de transferência); se não mostrar, pergunta ao vendedor que empresa entrega a encomenda e qual é o seu número, adicionando-o ao Peek como encomenda separada. Apenas na Suíça e no Liechtenstein pode o Peek passar automaticamente uma encomenda da GZYL para a Swiss Post.

:::sources
- [广州云链货运代理有限公司 (GZYL): página inicial (em chinês)](http://yunlianhouyun.com/) – nome da empresa, site em chinês e pesquisa de pequenos pacotes (小包查询)
- [广州云链货运代理有限公司 (GZYL): 联系我们 (contactos, em chinês)](http://yunlianhouyun.com/contact.html) – linha permanente 24 horas, WeChat e QQ de pós-venda
- [Comissão Europeia: EU Customs Reform (em inglês)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – direito aduaneiro de 3 € por categoria de artigo em remessas até 150 € desde 1 de julho de 2026 e taxa de tramitação da União de 2 € desde 1 de novembro de 2026
- [Comissão Europeia: Guidance on the 3 EUR customs duty and Union handling fee (PDF, em inglês)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – taxa de 2 € por categoria de artigo qualquer que seja o valor e definição de categoria de artigo
- [Comissão Europeia: Summertime (em inglês)](https://transport.ec.europa.eu/transport-themes/summertime_en) – hora de verão na União Europeia do último domingo de março ao último domingo de outubro
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – nome da empresa e outros nomes, sistema de seguimento num endereço numérico, formatos YL…YQ, reconhecimento imediato de YL738 e correspondência fraca nos restantes, Xinghe Freight no sistema da GZYL e aceitação em Hong Kong, pesquisa por número ou referência com hífenes, textos e estados dos passos, linha em chinês da plataforma, localidades após os textos, hora de Pequim, cronologia escrita com antecedência, resposta para número desconhecido, colunas de número de transferência e de seguimento, passagem apenas para a Swiss Post e o que o Peek mostra, retém e deixa de fora
:::
