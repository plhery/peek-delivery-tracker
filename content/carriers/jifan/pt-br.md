---
title: Rastreamento Jifan: códigos JFGJ, eventos e horário
description: Rastreie encomendas da Jifan (吉凡国际物流) da China aos EUA: formato JFGJ…CN, significado dos status em inglês e por que os horários estão no fuso de Pequim.
slug: rastreamento-jifan
published: 2026-11-25
updated: 2026-11-25
---

A Jifan é a Shenzhen Jifan International Logistics (深圳市吉凡国际物流有限公司, ou 吉凡国际物流), uma empresa de logística de Shenzhen, na China, fundada em 2017. Ela envia encomendas de empresas chinesas para compradores nos Estados Unidos, por via aérea e marítima. Seus códigos de rastreamento têm o formato JFGJ, nove dígitos e CN. Consulte o seu no [rastreador da Jifan](https://www.szjf56.cn/index/tracknumber/track) ou cole o código na caixa acima. O vendedor fornece o código junto aos detalhes de envio do pedido.

## Código de rastreamento Jifan: como ele é

O site da Jifan não descreve seus códigos, mas eles seguem um formato fixo. JFGJ são as iniciais da empresa: 吉凡国际, Jifan Guoji ("Jifan International"). Todos os exemplos abaixo são inventados:

| Código | O que é |
| --- | --- |
| `JFGJ482910573CN` | JFGJ, nove dígitos e CN: código da Jifan. |
| `JFGJ48291057CN` | Apenas oito dígitos: não tem o formato da Jifan, e o Peek não identifica a Jifan. Confira se falta um dígito. |
| `RR482910576CN` | Duas letras, nove dígitos e CN: código da [China Post](carrier:china-post), não da Jifan, apesar do mesmo final CN. |

No peektracker.com, um código JFGJ…CN com um dígito a menos exibe o aviso: "Começa como um código de rastreamento de Jifan, mas falta um caractere: pode haver um erro de digitação. Compare com a sua mensagem de envio." No Peek, letras minúsculas, espaços e hifens não importam. Já no rastreador da Jifan, digite o código em letras maiúsculas e sem espaços.

**Onde encontrar.** No e-mail de envio do vendedor ou na página do pedido. Se não encontrar, veja [como encontrar seu código de rastreamento](guide:find-tracking-number).

**No site da Jifan.** O rastreador, 快件跟踪 (rastreamento de encomendas), tem o botão 跟踪(Track). Ele aceita até 20 códigos por vez e só encontra encomendas dos últimos dois meses.

## Status de rastreamento da Jifan explicados

O rastreador da Jifan está em chinês, mas as linhas de movimentação (轨迹) aparecem em inglês, da mais recente para a mais antiga, cada uma com seu horário (时间). Acima delas, uma linha de resumo informa o destino (目的地), o status (状态) e o último evento. Dois status comuns ali:

| O rastreador mostra | O que significa para você |
| --- | --- |
| `运输途中` | Em chinês, "em trânsito". Leia as linhas abaixo para ver onde a encomenda realmente está. |
| `查询不到` | Em chinês, "não encontrado". O rastreador não tem encomenda com esse código e não mostra linhas. |

Em seguida vêm as etapas próprias da Jifan na China, dos dados do pedido até o voo de saída. Nenhuma delas informa localidade. Na ordem habitual:

| A Jifan mostra | O que significa para você | O Peek exibe |
| --- | --- | --- |
| `Order information received` | O vendedor enviou os dados da encomenda à Jifan. Ela ainda não está com o pacote. | "Etiqueta criada" |
| `Arrive at the cross border receiving point` | A Jifan recebeu o pacote no ponto na China onde recolhe envios internacionais. | "Recebida pela transportadora" |
| `Goods leave the operation center` | O pacote saiu do centro operacional da Jifan na China. | "Em trânsito" |
| `Arrival at domestic internationality airport` | No aeroporto na China, antes do voo. `internationality` é a grafia do próprio rastreador. | "Em trânsito" |

Após o voo, as linhas passam a vir do agente da Jifan nos Estados Unidos e da empresa que realiza a entrega: chegada, desembaraço aduaneiro de importação e centros de triagem. Nos EUA, algumas dessas linhas começam com cidade, estado e CEP (ZIP code). O rastreador da Jifan usa o mesmo sistema da [CTTYEX](carrier:cttyex) e escreve as etapas da mesma forma, de modo que a página da CTTYEX detalha as mensagens que podem aparecer.

O Peek interpreta uma linha de desembaraço aduaneiro de importação como "Na alfândega". O primeiro registro da empresa entregadora na sua região continua como "Em trânsito", não como um novo começo: a Jifan está com o pacote desde a China. Uma linha indicando entrega aparece como "Entregue".

**Todos os horários estão no horário de Pequim.** O rastreador da Jifan exibe horários sem fuso. Todos seguem o horário de Pequim (UTC+8), mesmo nas etapas ocorridas nos Estados Unidos. A data em Pequim vira às 16:00 UTC. Assim, uma movimentação feita em Nova York depois das 11:00 no inverno (meio-dia no verão), ou em Los Angeles depois das 08:00 no inverno (09:00 no verão), mostra a data do dia seguinte.

## Quem entrega encomendas da Jifan

O site da Jifan lista quatro linhas, todas voltadas aos Estados Unidos:

- **Pequena encomenda econômica EUA** (美国经济小包-轻小件) para itens leves e planos: menos de 360 g e menos de 1,8 cm de espessura, em voos cargueiros diretos, entregue pela UPS em todo o território dos EUA, incluindo ilhas ultramarinas e endereços militares. A Jifan informa rastreamento de ponta a ponta. Cerca de 10 a 12 dias corridos.
- **Pequena encomenda aérea EUA** (美国空运小包标快): de 0 a 4,5 kg, em voos cargueiros diretos, entregue pela UPS ("UPS MI", escreve a Jifan) em todos os EUA, inclusive ilhas ultramarinas e endereços militares. Cerca de 8 a 10 dias corridos, segundo a empresa.
- **Encomendas marítimas Matson** (美森正班（限时达）海运大/小包): volumes pequenos de até 4,5 kg seguem o mesmo modelo da linha aérea. Volumes grandes, de 4,5 a 68 kg, vão por UPS Ground apenas para os EUA continentais. Cerca de 20 a 25 dias corridos.
- **Cargas superdimensionadas por navio** (美国海运超大件专线): de 100 a 2.000 kg por volume, em navios Matson e COSCO, depois caminhão até a porta, apenas nos EUA continentais. Cerca de 25 a 40 dias corridos.

Para as encomendas econômicas e pequenas encomendas até 4,5 kg por via aérea ou marítima, a Jifan afirma que um único conhecimento cobre todo o trajeto (一单到底). O rastreador não informa o nome da transportadora final nem fornece segundo código. Se o vendedor também passou um código da UPS, você pode acompanhá-lo na página da [UPS](carrier:ups).

A Jifan não publica regras para destinatários sobre tentativas de entrega, avisos ou pontos de retirada: valem as regras da empresa que entrega. Veja [entregas frustradas](guide:missed-delivery).

**Alfândega.** O site da Jifan não informa aos destinatários nada sobre impostos ou taxas. O que pode ser cobrado depende do seu país: veja [encomendas retidas na alfândega](guide:customs).

## Encomenda Jifan sem atualização, extraviada ou devolvida

Os clientes da Jifan são as empresas que despacham os pacotes, não o comprador. Em caso de atraso, extravio, avaria ou devolução, fale primeiro com o vendedor ou com o marketplace.

**`查询不到` (não encontrado).** Confira se houve erro de digitação, digite em maiúsculas e pergunte ao vendedor se continuar sem retorno. A encomenda fica como "Aguardando Jifan" e o Peek continua verificando. Se dois dias depois a Jifan ainda não reconhecer o código e nenhum outro serviço responder, o Peek exibe: "Depois de dois dias, a transportadora ainda não reconhece este código. Confira o código, escolha outra transportadora ou exclua a encomenda."

**Apenas `Order information received`.** A Jifan recebeu os dados do pedido, não o pacote. Se nada mudar em vários dias, pergunte ao vendedor se o pacote já foi entregue à transportadora.

**Sem atualizações após o aeroporto.** Depois de `Arrival at domestic internationality airport`, pode não haver novas linhas até o pouso ou a entrada na alfândega nos EUA. Envios marítimos levam semanas, portanto intervalos longos são normais. Quando uma encomenda em trajeto fica quatro dias sem nova linha, o Peek sinaliza a data do último evento ("Sem atualizações desde seg., 12 de out.") e lembra que é comum uma encomenda ficar sem atualizações em voos longos, na alfândega ou no fim de semana; após três semanas sem novidades, orienta falar com o vendedor. Veja [por que o rastreamento para de atualizar](guide:tracking-not-updating) e [rastrear encomendas vindas da China](guide:tracking-from-china).

**Na alfândega.** O rastreador da Jifan não exibe impostos nem taxas a pagar. Por isso, ele não confirma mensagens por SMS ou e-mail cobrando pagamento por um envio Jifan. Leia [golpes de SMS de entrega](guide:delivery-scam-texts) antes de pagar qualquer valor.

**Extraviada, danificada ou devolvida.** O site da Jifan não traz regras para destinatários sobre indenizações ou devoluções. Aos remetentes, informa que compensa atrasos e, em caso de extravio, devolve o frete mais o mesmo valor. Esse acerto ocorre entre a Jifan e o vendedor: peça reembolso ou reenvio ao vendedor. Veja [encomendas extraviadas](guide:lost-parcel), [encomendas danificadas](guide:damaged-parcel) e [devolvida ao remetente](guide:returned-to-sender).

**Consta entregue, mas não chegou.** Procure ao redor da residência e pergunte aos vizinhos; depois avise o vendedor: veja [consta como entregue, mas não chegou](guide:delivered-not-received).

## Contato da Jifan

Na [página inicial da Jifan](https://www.szjf56.cn/), na seção 联系我们 (fale conosco):

- **Linha direta:** 400-186-0001, para consultas (咨询电话), número de atendimento na China. A Jifan não informa horário de funcionamento nem se atende ligações do exterior ou em inglês.
- **E-mail:** 925930140@qq.com, um endereço QQ Mail. A empresa não diz quem responde nem em qual idioma.
- **Escritório:** Nanwan, distrito de Longgang, Shenzhen.

A página está somente em chinês e não tem formulário de contato, chat nem suporte para destinatários. Sobre o seu pedido, procure primeiro o vendedor.

## Rastrear uma encomenda Jifan com o Peek

O [Peek](/) identifica a Jifan imediatamente a partir de um código JFGJ…CN ("Transportadora detectada"): nenhuma outra transportadora acompanhada pelo Peek usa esse formato. Um link do rastreador da Jifan com o código JFGJ após `nos=` também funciona; se o link contiver vários códigos, o Peek usa o primeiro.

Se o vendedor passou qualquer outro código, cole-o mesmo assim: o Peek acompanha mais de 4.500 transportadoras. Quando o formato não identifica uma empresa de imediato, o Peek consulta serviços de rastreamento ao adicionar ou logo depois. Se eles não indicarem a transportadora, toque em "Trocar" e escolha a empresa informada pelo vendedor, ou use "Trocar transportadora" em uma encomenda já adicionada. O Peek consulta o rastreador da Jifan apenas para códigos JFGJ: ao escolher Jifan para outro formato, a linha da transportadora avisa que a Jifan "não emite códigos como este".

O Peek lê a encomenda direto do rastreador da Jifan: o status, cada linha no inglês original do rastreador e o país de destino. Quando uma linha nos EUA começa com cidade e estado, o Peek usa ambos como local da movimentação e remove o CEP. Cada horário é interpretado no horário de Pequim e exibido no fuso horário do seu aparelho; em peektracker.com e nos aplicativos iPhone e Android, o histórico termina com "Horários no seu fuso horário:" seguido do nome do fuso.

O status do Peek acompanha a linha mais recente que ele compreende, recorrendo ao status resumido (`运输途中`) apenas quando não reconhece nenhuma linha. Uma linha desconhecida é interpretada pelas suas palavras.

O rastreador da Jifan não informa data de entrega, peso, nome do serviço, empresa entregadora nem segundo código, portanto o Peek também não exibe esses dados. Ele continua acompanhando o código JFGJ no rastreador da Jifan até o fim.

Enquanto o rastreador da Jifan responde, o Peek verifica até a cada 10 minutos com a encomenda aberta na tela ou com alertas ligados se a entrega estiver prevista para hoje. Com a tela fechada e alertas desligados, as consultas normalmente ocorrem a cada hora. Quando apenas um serviço de rastreamento responde, o Peek verifica a cada hora, a cada 3 horas quando o último registro já tem um dia e até a cada 15 minutos no dia em que sai para entrega, somente enquanto você mantém aberto ou com notificações ligadas. Nas consultas diretas à transportadora, após dois dias sem novidades (12 horas enquanto ainda mostrar "Etiqueta criada" ou "Recebida pela transportadora"), passa a consultar a cada hora. Ele pode enviar alertas de "Cada movimentação", "Etapas importantes" ou "Só a entrega". Não consegue contatar a Jifan nem o vendedor por você.

## Perguntas frequentes sobre o rastreamento Jifan

### O que é a logística Jifan?

É a Shenzhen Jifan International Logistics (深圳市吉凡国际物流有限公司), empresa de Shenzhen fundada em 2017, segundo seu site. Ela envia encomendas comerciais da China para compradores nos Estados Unidos por via aérea e marítima. Seus códigos começam com JFGJ e terminam com CN.

### Onde posso rastrear um código JFGJ?

No rastreador da Jifan, [快件跟踪 em szjf56.cn](https://www.szjf56.cn/index/tracknumber/track), digitando em maiúsculas, ou no Peek. O rastreador só localiza encomendas dos últimos dois meses.

### Por que os horários no rastreador da Jifan parecem errados?

Eles não estão errados: estão no horário de Pequim (UTC+8), inclusive para movimentações nos Estados Unidos. Pequim fica 13 horas à frente de Nova York e 16 horas à frente de Los Angeles no inverno, então um registro após 11:00 em Nova York ou após 08:00 em Los Angeles aparece com a data do dia seguinte. O Peek exibe cada horário no fuso do seu dispositivo.

### O que significa 查询不到 no rastreador da Jifan?

Significa "não encontrado" em chinês: nenhuma encomenda no rastreador da Jifan corresponde ao código digitado. Confira se há erro de digitação, use letras maiúsculas e fale com o vendedor se continuar falhando. Encomendas com mais de dois meses também não aparecem mais no rastreador.

### Quanto tempo a Jifan leva para entregar?

O site da Jifan estima cerca de 8 a 10 dias corridos para pequenas encomendas aéreas aos EUA, cerca de 10 a 12 para pequenas encomendas econômicas, cerca de 20 a 25 para envios marítimos Matson e cerca de 25 a 40 para cargas superdimensionadas. O prazo informado pelo vendedor no seu pedido é a melhor referência.

:::sources
- [Jifan: 深圳市吉凡国际物流有限公司 (home page)](https://www.szjf56.cn/) – razão social, fundação em 2017, linhas exclusivas aos EUA, pesos e dimensões, entrega por UPS e UPS Ground, Matson e COSCO, regiões atendidas, prazos em dias corridos, conhecimento único, promessa de indenização aos clientes, linha direta, e-mail e bairro do escritório
- [Jifan: 快件跟踪 (tracking)](https://www.szjf56.cn/index/tracknumber/track) – rastreador chinês operado por 吉凡物流, botão Track, colunas de resultado, envios dos últimos dois meses e consulta de até 20 códigos por vez
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formato e iniciais JFGJ, link do rastreador, sistema da CTTYEX, status resumidos e linhas em inglês, etapas na China, agente e entregadora no exterior, horário de Pequim, cidades, estados e CEPs, e o que o Peek exibe ou omite
:::
