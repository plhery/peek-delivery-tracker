---
title: Rastreamento CTTYEX: códigos CTTY, eventos e horário
description: Rastreamento CTTYEX de Shenzhen: formato CTTY…CN, tradução dos status em inglês no rastreador chinês e por que cada horário usa o fuso de Pequim.
slug: rastreamento-cttyex
published: 2026-11-25
updated: 2026-11-25
---

A CTTYEX é a Shenzhen Chengtai Tongyun International Logistics (深圳诚泰通运国际物流, abreviada como 诚泰通运), uma agente de cargas de comércio eletrônico transfronteiriço na China que envia encomendas ao exterior para lojas online. Seu site informa fundação em 2011 e atuação para vendedores em plataformas como Amazon, Wish, Tophatter, VOVA e Shopify, com voos fretados para EUA, Reino Unido, Canadá, Alemanha e Austrália. Seus códigos de rastreamento consistem em CTTY, dez dígitos e CN, sendo CTTY a sigla de Chengtai Tongyun. Consulte o seu no rastreador da CTTYEX em [cttyvip888.com](https://www.cttyvip888.com/assets/html/search.html) ou cole-o na caixa acima.

## Código de rastreamento CTTYEX: como ele é

O site da CTTYEX não descreve o formato dos códigos, mas os códigos aceitos pelo seu rastreador seguem uma estrutura fixa. Todos os exemplos abaixo são inventados:

| Código | O que é |
| --- | --- |
| `CTTY6051827049CN` | CTTY, dez dígitos e CN: código da CTTYEX. |
| `CTTY605182704CN` | Apenas nove dígitos: não tem o formato da CTTYEX. Falta um dígito; se nenhuma transportadora responder, a página em peektracker.com exibe "Começa como um código de rastreamento de CTTYEX, mas falta um caractere: pode haver um erro de digitação." |
| `LX482910576CN` | Duas letras, nove dígitos e CN: código da [China Post](carrier:china-post), não da CTTYEX, apesar do mesmo final CN. |

O Peek aceita o código em letras minúsculas ou com espaços. O site da CTTYEX não: o campo de busca aceita apenas letras e dígitos, e o rastreador só encontra o código em maiúsculas, então digite como CTTY…CN, sem espaços.

**Onde encontrar.** Os clientes da CTTYEX são os vendedores online que despacham com ela. O vendedor contrata o envio e repassa o código no e-mail de envio ou na página do pedido. Se não encontrar, veja [como encontrar seu código de rastreamento](guide:find-tracking-number).

**No site da CTTYEX.** Abra 物流查询 (consulta logística), digite um código por linha e clique em 提交 (enviar). Isso abre o rastreador, 快件跟踪 (rastreamento de encomendas), que encontra apenas envios dos últimos dois meses.

## Status de rastreamento da CTTYEX explicados

O site da CTTYEX não explica nenhum status. O rastreador está em chinês, mas cada movimentação aparece em inglês, da mais recente para a mais antiga, abaixo de uma linha de resumo em chinês com o país de destino (目的地) e um status (状态):

| A CTTYEX mostra | O que significa para você |
| --- | --- |
| `运输途中` | "Em trânsito". Pode aparecer mesmo quando a única movimentação são os dados do pedido; leia as linhas abaixo. |
| `查询不到` | "Não encontrado": o rastreador não reconhece nenhuma encomenda com esse código e não lista movimentações. |

Depois vêm as etapas da CTTYEX na China, da mais antiga para a mais recente. Nenhuma informa localidade:

| A CTTYEX mostra | O que significa para você |
| --- | --- |
| `Order information received` | Os dados do pedido chegaram à CTTYEX vindos do vendedor. O pacote ainda está com o vendedor. |
| `Arrive at the cross border receiving point` | O pacote chegou às mãos da CTTYEX, no ponto na China onde ela recebe envios internacionais. `Arrive at the receiving point` é a mesma etapa. |
| `Goods leave the operation center` | Saiu do centro operacional da CTTYEX na China. |
| `Arrival at domestic internationality airport` | No aeroporto na China, antes do embarque. `internationality` é a grafia da CTTYEX. |
| `Departure from the airport of origin domestic` | Saiu da China de avião. |

Após o voo, as linhas passam a vir do agente da CTTYEX no seu país e da rede responsável pela entrega:

| A CTTYEX mostra | O que significa para você |
| --- | --- |
| `Arrive at the destination international airport` | A encomenda pousou no seu país. `Item arrives at destination airport` significa a mesma etapa. |
| `Import customs clearance` | Alfândega de importação, escrita após a cidade do porto ou aeroporto. O pacote ainda não foi liberado. |
| `transfer to local acting company International self-operation team` | Repassado ao agente da CTTYEX no seu país após a alfândega. |
| `Leave the … processing center` | Saiu de um centro de processamento cuja cidade aparece no lugar de `…`. |
| `Internationaliy Package received at processing center` | Primeiro registro na rede de entrega, não uma nova postagem. `Internationaliy` é a grafia da CTTYEX. |
| `Arrived at logistics carrier Facility In Transit to Next Facility` | Em uma unidade da rede entregadora, a caminho da próxima unidade. O rastreador chama essa rede apenas de "logistics carrier". |
| `Accepted at logistics carrier Regional Destination Facility` | Primeiro registro da rede entregadora na sua região. "Accepted" não é um recomeço: a CTTYEX está com o pacote desde a China. |
| `Arrive at the last mile sorting and distribution center` | No centro de triagem de última milha da rede entregadora, próximo a você. |
| `Leaving the sorting center` | Saiu desse centro de triagem. |
| `Arrive at the sorting and distribution center` | Em um centro de triagem cuja cidade o rastreador escreve depois de `Arrive`. |
| `package is on the way` | Escrito após a cidade; segue a passagem pelo centro de triagem daquela cidade. Não indica necessariamente que a rota de entrega começou. |
| `Delivered` | A rede entregadora concluiu a entrega. A linha começa com a cidade e o código postal. |

Nos EUA, as três linhas de `Accepted at logistics carrier Regional Destination Facility` até `Leaving the sorting center` começam com cidade, estado e CEP (ZIP code). Essas são as formulações que o Peek registrou no rastreador da CTTYEX; qualquer outra linha aparece exatamente como a CTTYEX a escreve.

**Todos os horários estão no horário de Pequim.** O rastreador da CTTYEX exibe horários sem fuso, mas cada um está no horário de Pequim (UTC+8), incluindo as movimentações feitas no seu país. A data em Pequim vira às 16:00 UTC. Assim, uma movimentação no Reino Unido ou na Irlanda depois das 16:00 no inverno, ou 17:00 no verão, aparece com a data do dia seguinte; em Nova York, depois das 11:00 no inverno ou do meio-dia no verão.

## Quem entrega encomendas da CTTYEX

A CTTYEX recolhe o pacote na China e o embarca de avião. Depois do pouso, seu agente no seu país faz o desembaraço aduaneiro de importação e uma rede local realiza a entrega. Nem o site nem o rastreador da CTTYEX informam o nome dessa rede: o rastreador a chama apenas de "logistics carrier" e não exibe segundo código de rastreamento. O código CTTY continua sendo o que você acompanha pela alfândega e pelos centros de triagem locais.

A CTTYEX não publica regras para destinatários sobre tentativas de entrega, avisos, pontos de retirada ou prazo de guarda, e seu rastreador não dá previsão de entrega: siga a estimativa informada pela loja. Um aviso ou mensagem de uma transportadora local indica que valem as regras dela; [entregas frustradas](guide:missed-delivery) explica o que fazer.

## Encomenda CTTYEX que não chega ou rastreamento parado

**Apenas `Order information received`.** A CTTYEX recebeu os dados do pedido, mas ainda não está com o pacote, mesmo quando o resumo mostra `运输途中`. Se passarem dias sem nova linha, peça ao vendedor que confirme a entrega do pacote.

**Sem atualizações após `Departure from the airport of origin domestic`.** Nada novo aparece até o pouso ou o desembaraço de importação no seu país. Veja [por que o rastreamento para de atualizar](guide:tracking-not-updating) e [rastrear encomendas vindas da China](guide:tracking-from-china).

**Retida em `Import customs clearance`.** O rastreador não menciona tributos nem taxas e não informa nome de empresa, por isso não confirma SMS ou e-mails pedindo pagamento por um pacote CTTYEX. Leia primeiro [golpes de SMS de entrega](guide:delivery-scam-texts); [encomendas retidas na alfândega](guide:customs) cobre o restante.

**`查询不到`.** Confira o código com o vendedor e digite-o em letras maiúsculas. O rastreador também deixa de exibir pacotes com mais de dois meses.

**Extraviada, danificada ou devolvida.** Nada no site da CTTYEX trata de reclamações, indenizações ou devoluções. O contrato da CTTYEX é com o vendedor, então o reembolso ou reenvio parte do vendedor ou do marketplace onde você comprou: [encomendas extraviadas](guide:lost-parcel) e [devolvida ao remetente](guide:returned-to-sender) explicam como proceder.

## Contato da CTTYEX

A página [联系我们 (fale conosco)](https://www.cttyvip888.com/assets/html/contact.html) da CTTYEX informa um endereço em Fuyong, distrito de Bao'an, Shenzhen, e um endereço de e-mail. Não lista telefone, chat nem formulário, está escrita em chinês e não oferece suporte a destinatários. Sobre seu pedido, escreva para o vendedor.

## Rastrear uma encomenda CTTYEX com o Peek

O [Peek](/) reconhece a CTTYEX apenas pelo formato do código: CTTY, dez dígitos e CN não é usado por nenhuma outra transportadora acompanhada pelo Peek. Você também pode colar um link do rastreador cttyvip888.com que contenha o código após `nos=`.

O Peek lê a encomenda diretamente do rastreador da CTTYEX: o status, as movimentações e o país de destino. Quando uma linha começa com cidade, ou cidade e estado, o Peek coloca isso como local da movimentação e remove o CEP; a cidade de um centro de processamento permanece no texto. O Peek também omite números de telefone. Linhas duplicadas aparecem uma única vez, e cada movimentação mantém o inglês original da CTTYEX. Cada horário é interpretado no horário de Pequim e mostrado no fuso horário do seu aparelho, para que as datas batam com o seu dia; em peektracker.com e nos aplicativos iPhone e Android, o histórico termina com "Horários no seu fuso horário:" seguido do nome do fuso.

O status do Peek acompanha a movimentação mais recente que ele compreende, usando o resumo apenas quando não reconhece nenhuma. `Order information received` conta como "Etiqueta criada", as duas linhas do ponto de recebimento como "Recebida pela transportadora", `Import customs clearance` como "Na alfândega", `Accepted at logistics carrier Regional Destination Facility` e `Internationaliy Package received at processing center` como "Em trânsito", e `Delivered` como "Entregue". Uma linha desconhecida é interpretada pelas palavras, portanto uma linha informando que o pacote foi entregue aparece como entregue.

O rastreador da CTTYEX não informa previsão de entrega, peso nem nome do serviço, e não revela a transportadora final nem um segundo código, de modo que o Peek também não tem esses dados. Ele continua consultando o rastreador da CTTYEX com o código CTTY e nunca transfere a encomenda para outra transportadora.

Enquanto você mantém a encomenda aberta, ou no dia da entrega com notificações ligadas, o Peek verifica até a cada 10 minutos; nos demais momentos, a cada hora. Ele pode enviar alertas de cada movimentação, apenas das etapas importantes ou "Só a entrega". Após dois dias sem novidades (12 horas enquanto ainda mostrar "Etiqueta criada" ou "Recebida pela transportadora"), passa a consultar a cada hora. Ele não pode contatar a CTTYEX nem o vendedor por você.

## Perguntas frequentes sobre o rastreamento CTTYEX

### O que é a CTTYEX?

É a Shenzhen Chengtai Tongyun International Logistics (深圳诚泰通运国际物流), uma agente de cargas na China que envia encomendas internacionais de comércio eletrônico para lojas online. Seu site é cttyvip888.com e seus códigos começam com CTTY e terminam com CN.

### Onde posso rastrear um código CTTY?

No rastreador da CTTYEX, por meio da página 物流查询 em [cttyvip888.com](https://www.cttyvip888.com/assets/html/search.html), digitando em maiúsculas, ou no Peek.

### Os horários da CTTYEX estão no meu fuso horário?

Não. Todos os horários no rastreador estão no horário de Pequim (UTC+8), mesmo sem aviso e inclusive para movimentações feitas no seu país; por isso, na Europa e nas Américas, um registro no fim do seu dia pode aparecer com a data de amanhã. O Peek converte cada horário para o fuso do seu aparelho.

### Por que o rastreador da CTTYEX mostra 查询不到?

É a expressão em chinês para "não encontrado": nenhuma encomenda no rastreador da CTTYEX corresponde ao código digitado. Procure erros de digitação, digite em letras maiúsculas e pergunte ao vendedor se o problema persistir. Encomendas enviadas há mais de dois meses também deixam de aparecer no rastreador.

### Quem devo procurar se uma encomenda CTTYEX sumir?

Comece pelo vendedor: o cliente da CTTYEX é a loja que enviou a encomenda, não você. O site da CTTYEX não oferece aos destinatários nada além de um endereço em Shenzhen e um e-mail, em uma página escrita em chinês.

:::sources
- [CTTYEX: 深圳诚泰通运国际物流 (home)](https://www.cttyvip888.com/) – razão social
- [CTTYEX: 关于我们 (about us)](https://www.cttyvip888.com/assets/html/about_us.html) – fundação em 2011, logística para vendedores em Amazon, Wish, Tophatter, VOVA e Shopify, voos fretados para EUA, Reino Unido, Canadá, Alemanha e Austrália
- [CTTYEX: 物流查询 (search)](https://www.cttyvip888.com/assets/html/search.html) – um código por linha, apenas letras e dígitos e botão enviar
- [CTTYEX: 快件跟踪 (tracking)](https://www.cttyvip888.com/index/tracknumber/track) – rastreador chinês operado por 诚泰通运国际物流, colunas de destino e status, envios dos últimos dois meses
- [CTTYEX: 联系我们 (contact us)](https://www.cttyvip888.com/assets/html/contact.html) – endereço em Fuyong, distrito de Bao'an, Shenzhen, e e-mail; sem telefone, chat nem formulário
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – agente de cargas de e-commerce internacional, formato CTTY e sigla, busca em maiúsculas, status resumidos e eventos em inglês, agente no exterior e rede entregadora sem nome, horários em Pequim, cidades, estados e CEPs no exterior, e o que o Peek mostra ou omite
:::
