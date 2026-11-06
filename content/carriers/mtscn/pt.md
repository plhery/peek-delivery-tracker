---
title: Rastreio Meiteshun: número AMTS, estados e a quem perguntar
description: Rastreio Meiteshun (美特顺) em Portugal: como é um número AMTS, o que quer dizer cada passo e porque é que o «Delivered» pode ser escrito antes da entrega.
slug: meiteshun-rastreio
published: 2026-11-01
updated: 2026-11-01
---

A Meiteshun Logistics (美特顺物流, 深圳市美特顺物流有限公司) é um transitário de Shenzhen, na China. Os seus números começam por AMTS e acabam em FS: se a loja que te enviou a encomenda te deu um, cola-o na caixa acima. Lê com cuidado o seguimento da Meiteshun: o seu sistema escreve a viagem toda, entrega incluída, quando a encomenda é criada, por isso um `Delivered` não prova que a encomenda chegou.

## Número de rastreio Meiteshun: como é

As guias da Meiteshun têm AMTS, nove algarismos e FS. Com algarismos inventados:

| Número | O que é |
| --- | --- |
| `AMTS000000017FS` | AMTS, nove algarismos e FS: uma guia da Meiteshun. |
| `AMTS00000001FS` | Só oito algarismos: não é o formato da Meiteshun. Vê se falta um algarismo. |
| `RR000000005CN` | Duas letras, nove algarismos e CN: um número da [China Post](carrier:china-post), não da Meiteshun. |

No Peek, tanto faz escreveres o número em minúsculas ou com espaços.

**Onde o encontrar.** A loja regista o envio na Meiteshun e passa-te o número. Se não o encontrares, vê [onde encontrar o número de seguimento da encomenda](guide:find-tracking-number).

**Uma referência da loja.** O site de seguimento da Meiteshun também encontra uma encomenda pela referência da própria loja, escrita tal e qual, com os hífenes, e mostra então o número AMTS.

**Uma ligação com algarismos em vez de um nome.** O site de seguimento da Meiteshun não está num domínio, mas num endereço numérico de servidor, em HTTP simples. Uma ligação para ele tem esse endereço onde estaria o nome de um site, e leva `trackIndex.htm`, `documentCode=` e o teu número ou referência: podes colá-la no Peek tal como está.

## Estados do rastreio Meiteshun: o que quer dizer cada passo

Todas as encomendas da Meiteshun seguem o mesmo guião, e a maior parte dos passos é escrita com antecedência (mais abaixo). As frases, pela ordem do guião:

| A Meiteshun escreve | O que quer dizer para ti |
| --- | --- |
| `The package has been collected and is ready to be sent to the distribution center` | O primeiro passo: a recolha da Meiteshun em Shenzhen. |
| `item inbound in sorting center` | Num centro de triagem, com o local `ShenZhen`. |
| `item outbound in sorting center` | A sair desse centro de triagem. |
| `Arriving at the terminal` | Num terminal de aeroporto, com o local `Hong Kong`. |
| `Leaving the terminal, the airline will take off` | O voo parte de Hong Kong. |
| `Arriving at the destination country` | Chegou ao teu país, indicado pelo código de duas letras (`PT` em Portugal). |
| `Customs clearanceisin progress` | Na alfândega de importação do teu país (a falta de espaços é da Meiteshun). |
| `Customs clearance completed` | Desalfandegada e outra vez a caminho. |
| `Arriving at the mail processing center` | Num centro de tratamento de correio no teu país. |
| `Arrive at the sorting and distribution center` | Num centro de triagem perto de ti, com a tua localidade ou região. |
| `package is on the way` | O passo antes da entrega. Não diz que a encomenda saiu para entrega. |
| `Delivered` | O último passo do guião (mais abaixo). |

O local de um passo vem no início, antes de uma vírgula, e os passos no teu país acrescentam a tua localidade ou região e o código postal depois da frase. As horas são sempre as de Pequim, mesmo nos passos em Portugal: 21h00 no site da Meiteshun são 13h00 em Portugal continental no inverno e 14h00 no verão.

**Passos escritos com antecedência.** A Meiteshun tem o seu próprio site de seguimento na Hualei (华磊), uma plataforma de transitários que outras empresas chinesas, como a Pengyou, também usam. Aí, todos os passos de uma encomenda são escritos quando ela é criada, cada um já com a data prevista, da recolha até ao `Delivered`. São uma previsão, não leituras da tua encomenda: dizem onde ela devia estar em cada data. Os serviços de seguimento que copiam o registo da Meiteshun mostram os mesmos passos.

## Quem entrega as encomendas Meiteshun em Portugal

Os passos da Meiteshun no teu país não indicam que empresa faz a entrega. O nome do serviço nos registos é MTS全球小包 (pequena encomenda global da MTS).

O Peek não encontrou prazos de entrega da Meiteshun, nem regras para tentativas de entrega, pontos de levantamento ou quanto tempo fica guardada uma encomenda. Pergunta à loja que empresa entrega a tua encomenda e pede-lhe o número de seguimento: contam o seguimento e as regras dessa empresa. Se te pedirem taxas de importação, vê [encomenda retida na alfândega](guide:customs).

## Encomenda Meiteshun parada ou que não chega

**`Delivered`, mas não chegou nada.** O passo pode ser a previsão do guião, e não uma leitura. Diz à loja que a encomenda não chegou e pede-lhe o número de seguimento da empresa que faz a entrega. Mais em [encomenda dada como entregue mas não recebi](guide:delivered-not-received).

**Todos os passos a horas, mas nada de encomenda.** Os passos foram escritos quando a encomenda foi criada: mostram o plano, não onde ela está. Guia-te pela estimativa de entrega que a loja te deu e pergunta-lhe quando esse prazo passar.

**Parada em Shenzhen no Peek.** Se o Peek só mostra os primeiros passos em Shenzhen, mas uma ligação de seguimento da loja mostra mais, os passos que o Peek deixa de fora foram escritos com antecedência ou têm data posterior à verificação (mais abaixo). Mais em [porque é que o tracking não atualiza](guide:tracking-not-updating) e em [rastrear encomendas da China](guide:tracking-from-china).

**Nada encontrado.** Para um número que não conhece, o site da Meiteshun mostra o número como referência e todos os outros campos vazios. Confirma o número com a loja.

**Perdida, danificada ou devolvida.** Foi a loja que registou o envio na Meiteshun, por isso pede o reembolso ou um novo envio à loja ou ao marketplace. Vê [encomenda perdida](guide:lost-parcel) e [encomenda devolvida ao remetente](guide:returned-to-sender).

## Meiteshun contacto: telefone e apoio ao cliente

A 28 de outubro de 2026, o Peek não encontrou contactos da Meiteshun numa página que conseguisse ler: nem telefone, nem e-mail, nem morada para quem recebe. Para tudo o que tenha a ver com a encomenda, fala com a loja, e com o marketplace se a loja não responder.

## Rastrear encomenda Meiteshun com o Peek

O [Peek](/) reconhece um número da Meiteshun pelo formato: AMTS, nove algarismos e FS, que nenhuma outra transportadora do seu catálogo usa. Também podes colar uma ligação para o site da Meiteshun com `documentCode=` e o número ou a referência da loja. O Peek segue mais de 3.500 transportadoras, mas não reconhece uma referência da loja pelo formato: cola-a na mesma, carrega em «Alterar» e escolhe a Meiteshun Logistics (também a encontras escrevendo 美特顺 ou MTSCN), ou usa «Alterar transportadora» numa encomenda que já adicionaste. O Peek mostra então também o número AMTS. Mas tira os hífenes, escritos ou numa ligação: uma referência com hífenes, procura-a no site da própria Meiteshun.

O Peek lê o site de seguimento da própria Meiteshun e mostra o estado, cada leitura com o seu local e o país de destino. As leituras aparecem como a Meiteshun as escreve: `Customs clearanceisin progress` põe a encomenda «Na alfândega», e `package is on the way` deixa-a «Em trânsito» (não diz que saiu para entrega). Dos passos no teu país, o Peek guarda a tua localidade e deixa de fora o código postal. As horas são lidas no fuso de Pequim e mostradas no fuso horário do teu dispositivo. O Peek deixa de fora os nomes, a morada, o peso, os custos, a assinatura e a prova de entrega que o registo da Meiteshun pode trazer, e não mostra data prevista de entrega: a Meiteshun não dá nenhuma.

O Peek não mostra um passo escrito mais de doze horas antes da hora que traz, nem um passo com data posterior ao momento em que verifica, na hora de Pequim. Guia-se pelos passos que mostra, não pelo estado resumido do registo. Se o guião inteiro de uma encomenda foi escrito quando ela foi criada, o Peek só mostra os passos com data anterior a esse momento ou até doze horas depois, por isso o `Delivered` escrito nessa altura fica de fora. Quando o Peek tem de ler a página `trackIndex.htm`, porque a consulta do site de seguimento não devolve passos para o número, deixa de fora todos os `Delivered` dessa página: ela não mostra quando cada passo foi escrito. Quando ficam todos de fora, a encomenda ainda não tem histórico no Peek e aparece como «À espera da transportadora». Deixe de fora alguns passos ou todos, o Peek põe também de parte as cópias desses passos feitas pelos serviços de seguimento.

O Peek segue a encomenda no site da Meiteshun. Só a passa para outra transportadora num caso: quando o registo indica uma linha e um número da [Swiss Post](carrier:swiss-post) para uma encomenda com destino à Suíça ou ao Liechtenstein. Se a loja te der o número da transportadora que entrega em Portugal, adiciona-o como uma encomenda à parte.

O Peek verifica de 10 em 10 minutos, e de 2 em 2 minutos quando a encomenda sai para entrega (nesse dia, a partir das 6h00, hora local, e enquanto for o próprio seguimento da Meiteshun ou da Swiss Post a responder; através de serviços de seguimento, de 15 em 15 minutos), embora nenhum passo da Meiteshun que o Peek conheça indique isso. Pode enviar-te notificações de todas as leituras, só dos passos importantes ou «Só a entrega». Depois de dois dias sem novidades, ou de 12 horas se a encomenda ainda estiver em «Etiqueta criada» ou «Aceite pela transportadora», o Peek passa a consultar apenas uma vez por hora. Não pode contactar a Meiteshun nem a loja por ti.

## Perguntas sobre o rastreio Meiteshun

### O que é a Meiteshun Logistics (美特顺)?

É a 深圳市美特顺物流有限公司, Shenzhen Meiteshun Logistics, um transitário de Shenzhen, na China. Os seus números de guia têm AMTS, nove algarismos e FS, e nos registos o serviço chama-se MTS全球小包.

### Um número AMTS é da China Post?

Não. AMTS, nove algarismos e FS é o formato da Meiteshun. Os números internacionais da China Post têm duas letras, nove algarismos e CN.

### Porque é que a Meiteshun dá a encomenda como entregue se não a recebi?

Muito provavelmente porque o sistema da Meiteshun escreve todos os passos de uma encomenda, entrega incluída, quando a encomenda é criada. Um `Delivered` escrito assim é uma previsão, não uma leitura. Diz à loja que a encomenda não chegou.

### Quem entrega as encomendas Meiteshun em Portugal?

Os passos da Meiteshun não indicam nenhuma empresa de entrega. Pergunta à loja quem entrega a tua encomenda e pede-lhe o número de seguimento dessa empresa.

### Quem devo contactar se a encomenda Meiteshun não chegar?

A loja onde compraste: foi ela que registou o envio na Meiteshun. O Peek não encontrou contactos da Meiteshun numa página que conseguisse ler.

:::sources
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – nome da empresa, plataforma Hualei, formato AMTS, o site de seguimento e as ligações para ele, pesquisa por referência, o guião e as suas frases, locais e hora de Pequim, passos escritos com antecedência, a resposta para um número desconhecido, nome do serviço, nenhuma empresa de entrega nos passos, a passagem para a Swiss Post, o que o Peek mostra, põe de parte e deixa de fora
:::
