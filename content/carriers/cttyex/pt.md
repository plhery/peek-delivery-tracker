---
title: Seguimento CTTYEX: números CTTY…CN, estados e hora Pequim
description: Segue uma encomenda CTTYEX (诚泰通运) desde Shenzhen: formato CTTY…CN, leituras em inglês no site chinês, hora de Pequim e seguimento no Peek.
slug: seguimento-cttyex
published: 2026-11-21
updated: 2026-11-21
---

A CTTYEX é a *Shenzhen Chengtai Tongyun International Logistics* (`深圳诚泰通运国际物流`, abreviadamente `诚泰通运`), um transitário de comércio eletrónico transfronteiriço em Shenzhen, na China continental, que expede encomendas para o estrangeiro por conta de vendedores online. O site indica fundação em 2011, cobertura em mais de 200 países e regiões, mais de seis filiais na China continental e envios para vendedores na Amazon, Wish, Tophatter, VOVA e Shopify, com voos fretados para os EUA, Reino Unido, Canadá, Alemanha e Austrália. Os seus códigos têm `CTTY`, dez algarismos e `CN` (`CTTY` de *Chengtai Tongyun*): pesquisa o teu em [cttyvip888.com](https://www.cttyvip888.com/assets/html/search.html) ou cola-o na caixa acima.

## Número de seguimento da CTTYEX: como é

O site da CTTYEX nada diz sobre o formato dos números, mas o localizador atribui sempre o mesmo padrão (exemplos inventados):

| Número (inventado) | O que é |
| --- | --- |
| `CTTY4829105736CN` | `CTTY`, dez algarismos e `CN`: um número da CTTYEX. |
| `CTTY482910573CN` | Apenas nove algarismos: falta um carácter. Se nenhuma transportadora responder, a página em `peektracker.com` mostra «Começa como um número de seguimento de CTTYEX, mas falta-lhe um carácter: pode estar mal escrito. Compara-o com a tua mensagem de envio.» |
| `LX482910576CN` | Duas letras, nove algarismos e `CN`: um número da [China Post](carrier:china-post), não da CTTYEX, apesar de terminar em `CN`. |

O Peek aceita minúsculas e espaços. O site da CTTYEX não: a caixa de pesquisa só aceita letras e algarismos e o localizador apenas reconhece o número em maiúsculas (`CTTY…CN`, sem espaços).

**Onde encontrar o número.** Como os clientes da CTTYEX são os vendedores online, o vendedor envia-te o código no e-mail de expedição ou na página da encomenda ([como encontrar o número de seguimento](guide:find-tracking-number)).

**No site da CTTYEX.** Abre `物流查询` (`search.html`), introduz um número por linha em maiúsculas e carrega em `提交` (submeter): abre-se o localizador `快件跟踪` (`index/tracknumber/track`), que só encontra encomendas dos últimos dois meses.

## Estados de seguimento da CTTYEX explicados

O localizador está em chinês, mas cada leitura surge em inglês, da mais recente para a mais antiga, sob uma linha de resumo em chinês com o país de destino (`目的地`) e o estado geral (`状态`):

| Resumo CTTYEX | O que significa para ti | O Peek mostra |
| --- | --- | --- |
| `运输途中` (`In Transit` em inglês) | Em trânsito; pode aparecer quando ainda só existem os dados da encomenda: lê as leituras abaixo. | «Em trânsito» |
| `查询不到` (`Not Found` em inglês) | Não encontrada: o localizador não conhece nenhuma encomenda com esse código nem lista leituras. | Não encontrada |

Seguem-se primeiro os passos na China continental, sem indicação de localidade:

| A CTTYEX diz | O que significa para ti | O Peek mostra |
| --- | --- | --- |
| `Order information received` | Os dados da encomenda enviados pelo vendedor chegaram à CTTYEX; o pacote ainda está com o vendedor. | «Etiqueta criada» |
| `Arrive at the cross border receiving point`, `Arrive at the receiving point` | Recebida no ponto de receção transfronteiriço da CTTYEX na China continental: já está em mãos da CTTYEX. | «Aceite pela transportadora» |
| `Goods leave the operation center` | Saiu do centro de operações da CTTYEX na China continental. | «Em trânsito» |
| `Arrival at domestic internationality airport` | No aeroporto de partida na China continental antes do voo (`internationality` é a grafia da CTTYEX). | «Em trânsito» |
| `Departure from the airport of origin domestic` | Partiu de avião da China continental. | «Em trânsito» |

Depois do voo, as linhas vêm do agente da CTTYEX no teu país e da rede responsável pela entrega:

| A CTTYEX diz | O que significa para ti | O Peek mostra |
| --- | --- | --- |
| `Arrive at the destination international airport`, `Item arrives at destination airport` | O avião aterrou no teu país. | «Em trânsito» |
| `Import customs clearance` | Desalfandegamento de importação (escrito após a cidade de entrada); a encomenda ainda não saiu da alfândega. | «Na alfândega» |
| `transfer to local acting company International self-operation team` | Entregue ao agente local da CTTYEX no teu país após a alfândega. | «Em trânsito» |
| `Leave the … processing center` | Saiu de um centro de processamento (cuja cidade surge no lugar das reticências). | «Em trânsito» |
| `Internationaliy Package received at processing center` | Primeira leitura da rede de distribuição (`Internationaliy` é a grafia da CTTYEX; não é nova recolha). | «Em trânsito» |
| `Arrived at logistics carrier Facility In Transit to Next Facility` | Numa instalação da rede de entrega (designada apenas por `logistics carrier`), a caminho da seguinte. | «Em trânsito» |
| `Accepted at logistics carrier Regional Destination Facility` | Primeira leitura da rede de entrega na tua região. | «Em trânsito» |
| `Arrive at the last mile sorting and distribution center` | No centro de triagem e distribuição de última milha perto de ti. | «Em trânsito» |
| `Leaving the sorting center` | Saiu desse centro de triagem. | «Em trânsito» |
| `Arrive at the sorting and distribution center` | Chegou a um centro de triagem (a cidade surge a seguir a `Arrive`). | «Em trânsito» |
| `package is on the way` | Segue-se à leitura do centro de triagem dessa cidade; ainda não indica início de volta de distribuição. | «Em trânsito» |
| `Delivered` | Entregue pela rede local (a linha começa pela localidade e código postal). | «Entregue» |

Nos EUA, as três linhas desde `Accepted at logistics carrier Regional Destination Facility` até `Leaving the sorting center` abrem com cidade, estado e código ZIP. Qualquer outra linha é mostrada tal como a CTTYEX a escreve.

**Todas as horas são hora de Pequim.** O localizador imprime as horas sem fuso horário, mas cada uma está na hora de Pequim (`UTC+8`), **incluindo as leituras feitas no teu país**. Como em Pequim a data muda às `16:00 UTC`, uma leitura feita em Portugal continental após as `16:00` no inverno (ou `17:00` no verão) — ou no Brasil a partir do início da tarde — aparece já com a data do dia seguinte.

## Quem entrega as encomendas da CTTYEX?

A CTTYEX recolhe a encomenda na China continental e envia-a de avião; após a aterragem, o seu agente trata da alfândega de importação e uma rede local faz a entrega. Nem o site nem o localizador revelam o nome dessa rede (`logistics carrier`) nem um segundo código: o número `CTTY…CN` continua a ser o que deves acompanhar. A CTTYEX não publica prazos de entrega, regras de tentativas nem tempos de guarda: segue a estimativa da loja e, se uma transportadora local deixar aviso, consulta [falha na entrega](guide:missed-delivery).

## Encomenda CTTYEX parada, na alfândega ou que não chega

**Apenas `Order information received`.** A CTTYEX recebeu os dados mas ainda não tem o volume físico, mesmo que o resumo mostre `运输途中`. Se nada mudar durante dias, confirma o envio com o vendedor.

**Sem novidades após `Departure from the airport of origin domestic` ou quatro dias parada.** Entre a descolagem e a aterragem ou alfândega no teu país não surgem passos novos; ao fim de quatro dias em viagem sem atualizações, o Peek mostra «Sem atualizações há quatro dias» ([porque é que o seguimento não atualiza](guide:tracking-not-updating), [seguir encomendas vindas da China](guide:tracking-from-china)).

**Retida em `Import customs clearance`.** O localizador não indica taxas nem empresa local, pelo que não confirma SMS ou e-mails a pedir pagamento por uma encomenda CTTYEX. Lê primeiro [mensagens falsas de entregas](guide:delivery-scam-texts) e consulta [encomendas retidas na alfândega](guide:customs).

**`查询不到`, perdida ou devolvida.** Confere o código com o vendedor e escreve-o em maiúsculas (ao fim de dois meses o localizador também deixa de mostrar a encomenda). Como o site da CTTYEX nada prevê sobre reclamações de destinatários, pede reembolso ou substituição ao vendedor ou marketplace ([encomenda perdida](guide:lost-parcel), [devolvida ao remetente](guide:returned-to-sender)).

## Contactos da CTTYEX

A [página 联系我们 (contactos) da CTTYEX](https://www.cttyvip888.com/assets/html/contact.html), apenas em chinês, indica uma morada em Fuyong, distrito de Bao'an, Shenzhen, China continental (`深圳市宝安区福永街道怀德新村8巷12号一层`), e um endereço de e-mail (`cttywlvip888@163.com`), sem telefone, chat, formulário ou apoio para destinatários.

**Cuidado com SMS falsos.** Os domínios oficiais da CTTYEX são `www.cttyvip888.com` e `cttyvip888.com` ([mensagens falsas de entregas](guide:delivery-scam-texts)). Sobre a tua encomenda, escreve ao vendedor.

## Seguir uma encomenda da CTTYEX com o Peek

O [Peek](/) reconhece a CTTYEX logo pelo formato `CTTY…CN` («Transportadora detetada»), exclusivo da CTTYEX no catálogo, ou por uma ligação de `cttyvip888.com` com o código após `nos=` (se escolheres manualmente a CTTYEX para algo fora de 8 a 40 letras e algarismos com pelo menos um algarismo, indica que «não emite números como este»).

O Peek lê o estado, as leituras e o país de destino no localizador da CTTYEX: quando no estrangeiro uma linha começa pela cidade (ou cidade e estado), transforma-a no local da etapa retirando o código postal, elimina repetidos, telefones e nome de quem assinou e converte cada hora da hora de Pequim (`UTC+8`) para o fuso do teu dispositivo («Horas no teu fuso horário: …»), alinhando as datas com o teu dia. O estado segue a leitura reconhecida mais recente (e o resumo apenas quando nenhuma tem etapa); como não há data prevista, peso nem segunda transportadora, o Peek acompanha sempre a encomenda na CTTYEX.

O Peek verifica até a cada 10 minutos com a encomenda aberta ou no dia da entrega com notificações ativas; nos restantes casos, e após 12 horas em «Etiqueta criada» / «Aceite pela transportadora» ou dois dias sem novidades, de hora a hora («Todas as leituras», «Passos importantes» ou «Só a entrega»). O Peek não pode contactar a CTTYEX nem o vendedor por ti.

## Perguntas frequentes sobre o seguimento da CTTYEX

### O que é a CTTYEX?

*Shenzhen Chengtai Tongyun International Logistics* (`深圳诚泰通运国际物流`), um transitário em Shenzhen, na China continental, que expede encomendas internacionais de comércio eletrónico, com site em `cttyvip888.com` e números iniciados por `CTTY` e terminados em `CN`.

### Onde posso seguir um número CTTY…CN?

Em `物流查询` no site [cttyvip888.com](https://www.cttyvip888.com/assets/html/search.html) (em maiúsculas e sem espaços) ou diretamente no Peek.

### As horas das leituras da CTTYEX estão no meu fuso horário?

Não. Todas as horas no localizador estão na hora de Pequim (`UTC+8`), mesmo nas leituras feitas na Europa ou nas Américas, pelo que uma leitura ao fim da tarde pode surgir já com a data de amanhã. O Peek converte cada hora para o fuso do teu dispositivo.

### Porque é que o localizador da CTTYEX diz `查询不到`?

Significa *não encontrada* em chinês: nenhuma encomenda corresponde ao código introduzido. Verifica se há gralhas, escreve em maiúsculas e fala com o vendedor se continuar sem aparecer; encomendas enviadas há mais de dois meses também deixam de constar.

### Quem devo contactar se faltar uma encomenda da CTTYEX?

Começa pelo vendedor: o contrato da CTTYEX é com a loja que enviou o pacote, e a página chinesa da CTTYEX apenas indica uma morada em Shenzhen e um e-mail sem apoio a destinatários.

:::sources
- [CTTYEX: 深圳诚泰通运国际物流 (página inicial)](https://www.cttyvip888.com/) – nome da empresa, serviços e contactos no rodapé
- [CTTYEX: 关于我们 (sobre nós)](https://www.cttyvip888.com/assets/html/about_us.html) – fundação em 2011, mais de 200 países, mais de 6 filiais na China continental, plataformas e voos fretados
- [CTTYEX: 物流查询 (pesquisa)](https://www.cttyvip888.com/assets/html/search.html) – um código por linha (letras e algarismos) e botão 提交
- [CTTYEX: 快件跟踪 (seguimento)](https://www.cttyvip888.com/index/tracknumber/track) – pesquisa de encomendas dos últimos 2 meses, colunas de destino e estado
- [CTTYEX: 联系我们 (contactos)](https://www.cttyvip888.com/assets/html/contact.html) – morada em Fuyong (Bao'an, Shenzhen, China continental) e e-mail cttywlvip888@163.com, sem telefone nem formulário
- [Universal Parcel Scraper: CTTYEX](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/cttyex) – formato CTTY…CN, correspondência em maiúsculas, 23 estados e resumos, hora de Pequim em todas as leituras e localidades
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – distinção face à China Post e apresentação no Peek
:::
