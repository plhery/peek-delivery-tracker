---
title: Rastreamento Meiteshun: AMTS, etapas e a quem perguntar
description: Rastreie encomendas Meiteshun Logistics (美特顺) de Shenzhen: formato AMTS, significado dos eventos e por que a etapa Delivered pode ser gravada com antecedência.
slug: rastreamento-meiteshun
published: 2026-11-24
updated: 2026-11-24
---

A Meiteshun Logistics (美特顺物流, 深圳市美特顺物流有限公司) é um agente de cargas sediado em Shenzhen, China. Seus códigos começam com `AMTS` e terminam com `FS`: se a loja que enviou seu pedido passou um número assim, cole-o na caixa acima. Leia o rastreamento da Meiteshun com atenção: o sistema dela grava todo o percurso do pacote — inclusive a entrega — no momento em que o pedido é criado, de modo que um "Delivered" lá não comprova que a encomenda chegou.

## Código de rastreamento da Meiteshun: qual é o formato

Os códigos da Meiteshun têm `AMTS`, nove dígitos e `FS`. Com dígitos fictícios:

| Código | O que é |
| --- | --- |
| `AMTS482917360FS` | `AMTS`, nove dígitos e `FS`: código de rastreamento da Meiteshun. |
| `AMTS48291736FS` | Apenas oito dígitos: fora do padrão da Meiteshun. Verifique se falta um dígito. |
| `RR482917367CN` | Duas letras, nove dígitos e `CN`: código da [China Post](carrier:china-post), não da Meiteshun. |

Letras minúsculas e espaços não importam ao colar o código no Peek.

**Onde encontrar.** O vendedor contrata o envio com a Meiteshun e repassa o código. Se não encontrar, veja [como encontrar seu código de rastreamento](guide:find-tracking-number).

**Referência da loja.** O rastreador da Meiteshun também localiza um pedido pela referência própria do vendedor, digitada exatamente como informada, com hifens, e então exibe o código `AMTS`.

**Um link com números em vez de nome.** O rastreador da Meiteshun funciona em um endereço numérico de servidor sobre HTTP simples, sem domínio web próprio. Um link para ele traz esse endereço numérico no lugar do nome do site e contém `trackIndex.htm`, `documentCode=` e seu código ou referência: você pode colá-lo no Peek como recebeu.

## Status de rastreamento da Meiteshun explicados

Todo pedido da Meiteshun segue o mesmo roteiro, e a maior parte das etapas é gravada com antecedência (veja abaixo). Veja os textos na ordem em que o roteiro avança e o que o Peek mostra quando cada um é o mais recente:

| A Meiteshun diz | O que significa para você | O Peek mostra |
| --- | --- | --- |
| `The package has been collected and is ready to be sent to the distribution center` | Primeira etapa: coleta da Meiteshun em Shenzhen. | "Recebida pela transportadora" |
| `item inbound in sorting center` | Em um centro de triagem, com o local `ShenZhen`. | "Em trânsito" |
| `item outbound in sorting center` | Saindo desse centro de triagem. | "Em trânsito" |
| `Arriving at the terminal` | No terminal aeroportuário, com o local `Hong Kong`. | "Em trânsito" |
| `Leaving the terminal, the airline will take off` | O voo decola de Hong Kong. | "Em trânsito" |
| `Arriving at the destination country` | Chegou ao país de destino, indicado pelo código de duas letras (`US` para Estados Unidos). | "Em trânsito" |
| `Customs clearanceisin progress` | Alfândega de importação no seu país. A falta de espaço é do próprio texto da Meiteshun. | "Na alfândega" |
| `Customs clearance completed` | Liberado pela alfândega e novamente em trânsito. | "Em trânsito" |
| `Arriving at the mail processing center` | Em um centro de processamento no seu país. | "Em trânsito" |
| `Arrive at the sorting and distribution center` | Em um centro de triagem próximo a você, com sua cidade ou região. | "Em trânsito" |
| `package is on the way` | Etapa anterior à entrega. Não diz que o pacote saiu para entrega. | "Em trânsito" |
| `Delivered` | Última etapa do roteiro (veja abaixo). | "Entregue", salvo se o Peek retiver a etapa (veja abaixo) |

Em pedidos ainda sem etapas, a plataforma grava por conta própria `货物电子信息已经收到` ("as informações eletrônicas da carga foram recebidas"). O vendedor criou o pedido, mas ninguém escaneou o pacote; o Peek mostra essa linha em chinês, sob "Etiqueta criada".

O local da etapa vem antes de uma vírgula no início da linha, e as etapas no seu país adicionam sua cidade ou região e código postal após o texto. Todos os horários seguem o relógio de Pequim, mesmo nas etapas no país de destino: uma marcação às 21h no rastreador da Meiteshun corresponde à tarde em Londres e à manhã em Nova York.

**Etapas gravadas com antecedência.** A Meiteshun usa um rastreador próprio na Hualei (华磊), plataforma de agentes de cargas também utilizada por outros agentes de cargas chineses como a Pengyou. No sistema da Meiteshun, todas as etapas de um pedido são geradas no momento da criação do envio, cada uma com o horário planejado, da coleta até `Delivered`. Trata-se de uma previsão, não de leituras reais do pacote: mostram onde o envio deveria estar em certa data. Serviços de rastreamento que copiam o registro da Meiteshun repetem essas mesmas etapas.

## Quem entrega as encomendas da Meiteshun

As etapas da Meiteshun no país de destino não citam o nome da empresa entregadora local. O nome de serviço que consta nos registros é `MTS全球小包` (pequeno pacote global MTS). Na página de rastreamento da Meiteshun, um número na coluna `渠道转单号` (número de transferência) ou em `跟踪号码` (número de rastreamento), quando diferente do seu código `AMTS`, pode ser o código da empresa local: adicione-o ao Peek como uma encomenda separada.

O Peek não encontrou prazos de entrega da Meiteshun nem regras sobre tentativas de entrega, pontos de retirada ou tempo de guarda. Pergunte ao vendedor qual empresa entrega seu pedido e qual é o número de rastreamento dela: são o rastreamento e as regras dessa empresa que valem. Se cobrarem taxas de importação — que na União Europeia incluem, desde 1º de novembro de 2026, a taxa de manuseio de € 2 por categoria de produto, qualquer que seja o valor do pacote — consulte [encomendas paradas na alfândega](guide:customs).

## Pacote da Meiteshun que não chega ou rastreamento parado

**"Delivered", mas nada chegou.** Essa linha pode ser apenas a previsão do roteiro, não uma leitura real. Avise o vendedor que o pacote não chegou e peça o código da empresa que faz a entrega local. Veja mais em [marcado como entregue mas não recebido](guide:delivered-not-received).

**Todas as etapas no prazo, mas sem pacote.** Como os passos foram escritos na criação do pedido, refletem o cronograma previsto, não a localização real da encomenda. Guie-se pelo prazo informado pela loja e cobre o vendedor assim que expirar.

**Parado em Shenzhen no Peek.** Se o Peek mostra apenas as primeiras etapas em Shenzhen enquanto o link do vendedor exibe várias outras, as linhas ausentes no Peek foram gravadas antecipadamente ou trazem data futura em relação à consulta (veja abaixo). Uma etapa pré-gravada nunca conta como leitura real: não muda o status nem a data prevista e não envia alertas. Até que a encomenda seja entregue ou devolvida, o Peek abre o histórico com "Outros sites podem mostrar mais etapas, mas são apenas previsões." e lista as linhas retidas em "Ver", em cinza, cada uma com "Previsto para" e o dia. Procure na página de rastreamento da Meiteshun a empresa que entrega no seu país e o código de rastreamento dela; se não houver, pergunte ao vendedor. Veja também [por que o rastreamento para de atualizar](guide:tracking-not-updating) e [como rastrear uma encomenda da China](guide:tracking-from-china).

**Nada encontrado.** Para um código desconhecido, o rastreador da Meiteshun exibe o número como referência com todos os demais campos vazios. O Peek interpreta como não encontrado: mostra "Aguardando Meiteshun Logistics" e, dois dias depois, "Confira o código de rastreamento". Confirme o número com o vendedor.

**Extraviado, avariado ou devolvido.** Foi o vendedor quem contratou a Meiteshun, então solicite reembolso ou reenvio à loja ou ao marketplace. Veja [encomendas extraviadas](guide:lost-parcel) e [devolvidas ao remetente](guide:returned-to-sender).

## Telefone e atendimento ao cliente da Meiteshun

Para qualquer assunto do pedido, fale primeiro com o vendedor, que contratou o envio na Meiteshun. Se o vendedor não responder, acione o marketplace. Se nenhum dos dois ajudar, a página do 17TRACK sobre a Meiteshun informa o e-mail `szmeiteshun@163.com`, o site `mtswl.com.cn` e um escritório no distrito de Longgang, em Shenzhen, sem telefone: escreva informando seu código `AMTS` e o nome do vendedor.

## Como rastrear um pacote da Meiteshun com o Peek

O [Peek](/) reconhece códigos da Meiteshun pelo formato — `AMTS`, nove dígitos e `FS`, exclusivo no catálogo. Links para o rastreador da Meiteshun contendo `documentCode=` e o código ou a referência do vendedor também podem ser colados. O Peek não consegue identificar uma referência do vendedor pelo formato: cole-a mesmo assim (o Peek acompanha mais de 4.500 transportadoras), toque em "Trocar" e escolha Meiteshun Logistics (digitar 美特顺 ou MTSCN também encontra), ou use "Trocar transportadora" em um pacote já salvo. O Peek passa então a mostrar também o código `AMTS` do pedido. Porém, o Peek remove hifens digitados ou em links; se a referência tiver hifens, consulte-a diretamente no rastreador da Meiteshun.

O Peek lê o rastreador da própria Meiteshun e exibe o status, cada leitura com seu local e o país de destino. Os eventos aparecem como a Meiteshun os escreve: `Customs clearanceisin progress` conta como alfândega e `package is on the way` como em trânsito, não como saída para entrega. O Peek preserva sua cidade nas etapas do país de destino e omite o código postal. Os horários são lidos no horário de Pequim e exibidos no fuso do seu dispositivo. O Peek descarta nomes, endereço, peso, taxas, assinatura e comprovante de entrega que constem no registro, e não mostra previsão de data de entrega porque a Meiteshun não fornece nenhuma.

O Peek retém qualquer etapa gravada mais de doze horas antes do horário que ela informa e qualquer etapa com data posterior ao momento da consulta (no horário de Pequim). Ele se baseia apenas nas etapas mantidas, nunca no status resumido do registro. Quando todo o roteiro do pedido foi gravado na criação, o Peek mantém apenas etapas datadas até seis horas após aquele momento, retendo assim o `Delivered` pré-gravado. Quando precisa consultar a página `trackIndex.htm` — porque a consulta do rastreador não lista etapas para o código — o Peek retém qualquer `Delivered` dali, pois essa página não informa quando a linha foi gravada. Um pedido com todas as etapas retidas ainda não tem histórico no Peek e aparece como "Aguardando Meiteshun Logistics", sem pedir para conferir o código: o Peek considera etapas pré-gravadas como sinal de que a Meiteshun tem o pedido, como faz com um envio que a Meiteshun lista ainda sem leitura, e segue consultando o rastreador. Quer retenha parte das etapas ou todas, o Peek também descarta as cópias dessas mesmas linhas feitas por serviços de rastreamento.

O Peek acompanha a encomenda no rastreador da Meiteshun. Como os registros da Meiteshun nunca indicam a transportadora final no exterior, o Peek não faz nenhum repasse: adicione o código da entregadora fornecido pelo vendedor como uma nova encomenda.

Enquanto o pacote estiver aberto, ou no dia da entrega com alertas ligados, o Peek verifica até a cada 10 minutos, e até a cada 2 minutos no dia em que sair para entrega (a partir das 6h no horário local ou meia hora antes da janela informada pela transportadora) enquanto o rastreador da Meiteshun responde — embora nenhuma etapa conhecida da Meiteshun ative esse status. Nos demais horários, verifica de hora em hora. Por meio de serviços de rastreamento, verifica de hora em hora, a cada 3 horas após um dia sem movimento e até a cada 15 minutos no dia da entrega enquanto o pacote estiver aberto ou com alertas ligados. Após dois dias sem novidades (ou 12 horas enquanto ainda mostra "Etiqueta criada" ou "Recebida pela transportadora"), passa a consultar de hora em hora — estágio que um pedido com roteiro pré-gravado atinge rapidamente. Ele pode notificar em cada leitura, só em etapas importantes ou em "Só a entrega", e não pode contatar a Meiteshun nem o vendedor por você.

## Perguntas sobre o rastreamento da Meiteshun

### O que é a Meiteshun Logistics (美特顺)?

É a 深圳市美特顺物流有限公司 (Shenzhen Meiteshun Logistics), um agente de cargas em Shenzhen, China. Seus códigos seguem o formato `AMTS`, nove dígitos e `FS`, e o nome de serviço em seus registros é `MTS全球小包`.

### Um código AMTS é da China Post?

Não. `AMTS`, nove dígitos e `FS` é o formato da Meiteshun. Códigos internacionais da China Post têm duas letras, nove dígitos e `CN`.

### Por que a Meiteshun diz que meu pacote foi entregue se não chegou?

Muito provavelmente porque o sistema da Meiteshun grava todas as etapas do pedido — incluindo a entrega — logo na criação do envio. Um "Delivered" gravado assim é previsão, não confirmação real. Avise o vendedor que o pacote não chegou.

### Quem entrega encomendas da Meiteshun no meu país?

As etapas da Meiteshun não informam nenhuma transportadora entregadora. A página de rastreamento pode mostrar o código da empresa local em `渠道转单号` (número de transferência); se não mostrar, pergunte ao vendedor qual empresa faz a entrega e peça o código dela.

### Com quem falar sobre uma encomenda da Meiteshun extraviada?

Com o vendedor onde você fez a compra, pois foi ele quem contratou a Meiteshun. Se nem o vendedor nem o marketplace ajudarem, o 17TRACK lista o e-mail `szmeiteshun@163.com` para a Meiteshun.

:::sources
- [17TRACK: MTSCN tracking](https://www.17track.net/en/carriers/mtscn) – e-mail, site e escritório em Shenzhen listados para a Meiteshun, sem telefone
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – taxa de manuseio da UE de € 2 desde 1º de novembro de 2026
- [European Commission: Guidance on the 3 EUR customs duty and Union handling fee](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – € 2 por categoria de produto em vendas à distância, qualquer que seja o valor, calculados por linha de declaração (uma por categoria de produto) assim como a tarifa de € 3
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – nome da empresa, plataforma Hualei, formato do código AMTS, o rastreador e seus links, busca por referência, o roteiro e seus textos, locais e horário de Pequim, etapas gravadas com antecedência, a resposta para um código desconhecido, a linha em chinês da plataforma, nome do serviço, nenhuma empresa entregadora nas etapas, as colunas de número de transferência e de número de rastreamento da página de rastreamento, o que o Peek mostra, retém e omite
:::
