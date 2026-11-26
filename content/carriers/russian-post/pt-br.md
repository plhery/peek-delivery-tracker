---
title: Rastreamento Russian Post (Почта России): códigos e etapas
description: Rastreie uma encomenda da Russian Post (Почта России): códigos RU e de 14 dígitos, significado das etapas em russo e como o Peek acompanha o envio.
slug: rastreamento-russian-post
published: 2026-11-26
updated: 2026-11-26
---

A Russian Post (Почта России, Pochta Rossii) é o serviço postal da Rússia. Ela transporta encomendas enviadas da Rússia e encomendas enviadas para a Rússia. O remetente ou a loja fornece o código de rastreamento: cole-o no campo acima. O site próprio da Russian Post é pochta.ru, e o Peek consulta esse rastreador para você, como explicado abaixo.

## Código de rastreamento Russian Post: como ele é

Com dígitos inventados:

| Código (inventado) | O que é |
| --- | --- |
| `RR482910576RU` | Duas letras, nove dígitos e `RU`: o formato internacional, terminado no código da Rússia. O último dos nove dígitos é um dígito verificador. Com dígito verificador correto, o Peek identifica a Russian Post na hora quando o código começa com C, E, L ou R, e exibe "Operador postal desconhecido" nos demais casos (veja abaixo). |
| `80482910573649` | 14 dígitos começando com `80`, sendo o último um dígito verificador. O Peek associa esse formato à Russian Post apenas como correspondência fraca: DPD, SEUR, BRT, Delhivery e outras transportadoras também usam 14 dígitos. |
| `80482910573641` | O mesmo formato com dígito verificador incorreto. O Peek não sugere a Russian Post para ele, apenas as outras transportadoras: se o seu código for da Russian Post, confira se há erro de digitação. |
| `RR482910576CN` | Um número postal emitido por outro correio, aqui a [China Post](carrier:china-post). O Peek acompanha naquele correio, inclusive quando viaja para a Rússia. |

Espaços e letras minúsculas não importam ao colar o código no Peek. Se não encontrar o seu, veja [como encontrar seu código de rastreamento](guide:find-tracking-number) e [formatos de código de rastreamento](guide:tracking-number-formats).

## Status de rastreamento da Russian Post explicados

A Russian Post escreve suas etapas em russo. O Peek as lê, conforme a tabela abaixo, no rastreador da Qwintry, uma empresa de redirecionamento de encomendas que repassa parte dos pacotes à Russian Post: para encomendas adicionadas com o código próprio da Qwintry ou adicionadas com o código RU e classificadas em Qwintry (veja "Encomendas da Qwintry" abaixo). Ali, cada etapa da Russian Post tem uma operação, como `обработка` (processamento), seguida de uma linha de detalhe e da agência postal ao final. Quando não há linha de detalhe, a própria operação é a etapa. Aproximadamente na ordem em que surgem:

| A Russian Post diz | O que significa para você | O Peek mostra |
| --- | --- | --- |
| `Партионный` | A Russian Post recebeu a encomenda em lote com outras (operação `приём`, recebimento). | "Em trânsito" |
| `экспорт международной почты` | Exportação de correspondência internacional: a encomenda sai do país onde está. | "Em trânsito" |
| `Прибыло на территорию РФ` | A encomenda chegou à Rússia. | "Em trânsito" |
| `импорт международной почты` | Importação de correspondência internacional: a encomenda está dando entrada na Rússia. | "Em trânsito" |
| `приём на таможню` | Entregue à alfândega. | "Na alfândega" |
| `Выпущено таможней` | Liberada pela alfândega: novamente a caminho. | "Em trânsito" |
| `Покинуло место международного обмена` | Saiu da unidade que trata correspondências do exterior. | "Em trânsito" |
| `Сортировка` | Triagem. | "Em trânsito" |
| `Прибыло в сортировочный центр` | Chegou a um centro de triagem. | "Em trânsito" |
| `Покинуло сортировочный центр` | Saiu de um centro de triagem. | "Em trânsito" |
| `Прибыло в место вручения` | Chegou à agência postal que fará a entrega ou a retirada. | "Em trânsito" |
| `Передано почтальону` | Entregue ao carteiro para entrega. | "Saiu para entrega" |
| `Временное отсутствие адресата` | Não entregue: você não estava. | "Tentativa de entrega" |
| `Адресат заберет отправление сам` | Não entregue porque você mesmo retirará a encomenda. | "Tentativa de entrega" |
| `Вручение адресату` | Entregue a você, destinatário. | "Entregue" |

Para qualquer outra encomenda da Russian Post, o Peek lê o próprio rastreador da Russian Post, que devolve as etapas em inglês: veja "Acompanhe uma encomenda Russian Post com o Peek" abaixo.

## Como a Russian Post entrega

As etapas acima mostram o percurso na Rússia: centros de triagem e, depois, a agência postal responsável pela entrega. Dali, um carteiro pode levar até você (`Передано почтальону`). Se você não estava ou se deve retirar o pacote, a etapa indica isso. No rastreador da Qwintry, o Peek exibe a cidade da agência como local da etapa. Com o Peek em russo, ou quando o Peek não consegue localizar a agência, ele mostra o nome da agência como a Russian Post escreve, para você saber qual delas está com o pacote.

O Peek não encontrou prazos de entrega, tempos de guarda nem regras de tentativas de entrega que pudesse verificar no próprio site da Russian Post, então esta página não informa esses dados. Para um pacote aguardando em uma agência, veja [encomendas não retiradas](guide:uncollected-parcel) e [entregas não realizadas](guide:missed-delivery).

**Enviada da Rússia para outro país.** O Peek não sabe qual empresa entrega uma encomenda da Russian Post no seu país. Para um código RU, ele consulta o correio do país que o rastreador da Russian Post indica como destino e pode passar a acompanhar por lá (veja abaixo).

**Enviada para a Rússia de outro país.** O Peek acompanha a encomenda pela transportadora que emitiu o código, usando o rastreamento dela ou serviços de rastreamento. Se esse rastreamento citar a Russian Post como a empresa entregadora, o Peek consulta também o rastreador da Russian Post e transfere a encomenda para lá assim que surgir uma movimentação pelo menos tão recente quanto a última daquele rastreamento. O simples fato de o destino ser a Rússia não basta para transferir.

## Encomenda Russian Post parada ou sem chegar

**Nenhuma etapa ainda.** O rastreador da Russian Post ou os serviços de rastreamento podem ainda não reconhecer o número, ou o remetente ainda não entregou o pacote. O Peek continua consultando. Se nada aparecer após alguns dias, confira o código com o remetente. Veja mais em [por que o rastreamento para de atualizar](guide:tracking-not-updating).

**Parada na alfândega.** Se o Peek mostrar "Na alfândega", o pacote está com a alfândega. Esta página não traz regras nem taxas alfandegárias porque o Peek não conseguiu verificá-las numa página oficial. Veja [encomendas retidas na alfândega](guide:customs).

**O Peek está atrasado.** Quando o rastreador da Russian Post não responde ao Peek, um serviço de rastreamento assume no lugar, e ele pode ficar atrás do próprio rastreador da Russian Post. Compare com pochta.ru antes de se preocupar.

**Marcada como entregue, mas não chegou.** Se o Peek mostrar "Entregue" e nada tiver chegado, pergunte na sua agência postal e avise o remetente. Veja mais em [encomenda marcada como entregue mas não recebida](guide:delivered-not-received).

**Extraviada ou devolvida.** Fale primeiro com o remetente ou a loja: eles contrataram o envio. Veja [encomendas extraviadas](guide:lost-parcel) e [encomenda devolvida ao remetente](guide:returned-to-sender).

## Atendimento ao cliente e contato da Russian Post

O Peek não conseguiu ler o site da Russian Post, portanto esta página não lista telefone nem e-mail: o Peek fornece apenas contatos lidos nas páginas da própria transportadora. As páginas de ajuda da Russian Post ficam em info.pochta.ru. Para uma encomenda parada, o Peek traz o link para a página de ajuda sobre rastreamento da Russian Post como "Pedir ajuda a Russian Post".

Para pedidos de loja virtual, fale primeiro com a loja: ela contratou a entrega.

## Acompanhe uma encomenda Russian Post com o Peek

O [Peek](/) não pode ter certeza de que um código é da Russian Post apenas pelo formato. Cole-o mesmo assim: o Peek acompanha mais de 4.500 transportadoras. Enquanto você adiciona o código, o Peek mostra "Consultando serviços de rastreamento…".

- **14 dígitos começando com 80, com dígito verificador válido.** O Peek pergunta ao rastreador da Russian Post, junto com DPD e outras transportadoras compatíveis, se alguma tem a encomenda. Se somente a Russian Post a tiver, o Peek diz "Russian Post está com esta encomenda." e a classifica lá. Se não, toque em "Trocar": a Russian Post aparece em "Compatível com este código", ao lado de DPD e outras transportadoras compatíveis, listando primeiro as do seu país. Escolha-a ali. Em uma encomenda já adicionada, use "Trocar transportadora".
- **Um código RU.** Quando começa com C, E, L ou R e o dígito verificador está correto, o Peek identifica a Russian Post na hora. Outro código RU com dígito verificador correto aparece como "Operador postal desconhecido": toque em "Trocar" (ou "Trocar transportadora" depois) e selecione Russian Post — digitar Почта России também encontra. O Peek passa então a indicar Russian Post. Em ambos os casos, o Peek pode acompanhar a encomenda até o correio do seu país (abaixo).

O Peek lê o próprio rastreador da Russian Post, que lhe responde em inglês. Ele exibe cada etapa nas palavras da Russian Post, em sua maioria em inglês, como `Sorting`, `Arrived at the local distribution center` ou `Forwarded to the postman` ("Saiu para entrega"), com horário, cidade, país e país de destino. `Available for pickup at the Post Office` aparece como "Pronta para retirada" e `Handed over for temporary storage` como "Precisa de atenção". Após `Returned to the sender`, também exibido como "Precisa de atenção", o pacote segue de volta e a entrega final ao remetente aparece como "Devolvida ao remetente". A Russian Post não informa data prevista de entrega. Até o rastreador reconhecer o código, o Peek mostra "Aguardando Russian Post", ou "Aguardando a primeira movimentação de Russian Post" para um código iniciado por 80 adicionado sem transportadora. Quando o rastreador da Russian Post não responde ao Peek, o Peek consulta serviços de rastreamento no lugar — ParcelsApp, Ship24 e 17TRACK, nessa ordem, e para códigos RU o rastreamento da União Postal Universal por último — exibindo o histórico do primeiro que tiver a encomenda, nas palavras daquele serviço, o que pode ficar atrás de pochta.ru. Um serviço que não conhecia o código é consultado novamente um dia depois.

Para um código iniciado por 80 adicionado sem transportadora, ou um código RU exibido como "Operador postal desconhecido", o Peek também consulta, nos primeiros 30 dias da encomenda e antes dos serviços de rastreamento, as transportadoras que podem tê-la: para códigos iniciados por 80, Russian Post primeiro, depois DPD e outras. Se uma delas mostrar a encomenda em movimento, o Peek transfere para lá. Se você adicionou um código iniciado por 80 sem transportadora e um serviço de rastreamento apontar a Russian Post como única transportadora com etapas datadas, o Peek a classifica sozinho em Russian Post. Já para um código RU exibido como "Operador postal desconhecido", não basta um serviço de rastreamento citar a Russian Post: o Peek precisa que o próprio rastreador da Russian Post mostre a encomenda, ou que você escolha Russian Post manualmente.

O Peek não traz link direto para o rastreador da Russian Post. Se um serviço de rastreamento assumiu a consulta, o link na página da encomenda abre esse serviço, como "Abrir o site de ParcelsApp". Para acessar pochta.ru, abra o site por conta própria.

**Encomendas da Qwintry.** Se a Qwintry enviou sua encomenda para a Rússia, adicione o código Qwintry no lugar: QR e oito dígitos. O Peek identifica a Qwintry imediatamente e lê o rastreador dela, que continua mostrando as etapas da Russian Post quando o correio recebe o pacote. Quando o rastreador da Qwintry cita a Russian Post, a encomenda mostra "Entrega por Russian Post" e o código RU do correio, e o Peek consulta também o rastreador da Russian Post: assim que este mostrar uma movimentação pelo menos tão recente quanto a última da Qwintry, o Peek acompanha por lá, mantendo as etapas da Qwintry no histórico. Enquanto a Russian Post tiver apenas o aviso da encomenda, sem leitura própria, aparece "Anunciada a Russian Post, ainda não recebida". O rastreador da Qwintry também aceita o código RU de um pacote que ela entregou ao correio: se for tudo o que você tem e o rastreador da Russian Post não encontrar, toque em "Trocar" e escolha Qwintry.

**O correio do seu país.** Para um código RU classificado em Russian Post, o rastreador da Russian Post informa o país de destino, e o Peek consulta também o correio daquele país com o mesmo código RU, caso leia o rastreamento próprio desse correio — como faz com [USPS](carrier:usps), [Royal Mail](carrier:royal-mail), An Post, [Canada Post](carrier:canada-post) e [Australia Post](carrier:australia-post). Para uma encomenda exibida como "Operador postal desconhecido", basta um serviço de rastreamento indicar o correio entregador com seu número ou, para códigos RU, o país de destino: o Peek consulta esse correio da mesma forma. Assim que o correio local mostrar uma movimentação pelo menos tão recente quanto a última da Russian Post ou do serviço, o Peek passa a acompanhar lá.

Enquanto você mantiver a encomenda aberta, ou no dia da entrega com as notificações ativadas, e o próprio rastreador da Russian Post responder, o Peek verifica até a cada 10 minutos, e até a cada 2 minutos a partir das 6h no horário local no dia em que a Russian Post registra a saída para entrega; fora isso, a cada hora. Quando apenas os serviços de rastreamento respondem, ele verifica a cada hora, a cada 3 horas quando a leitura mais recente tem um dia e até a cada 15 minutos no dia em que a encomenda sai para entrega enquanto você a mantiver aberta ou com notificações ativadas. Ele pode avisar você sobre "Cada movimentação", "Etapas importantes" ou "Só a entrega". O Peek não pode falar com a Russian Post nem com o remetente por você.

## Perguntas sobre o rastreamento da Russian Post

### O que é Почта России em inglês e português?

Russian Post (Correio da Rússia). Também se escreve Pochta Rossii. Seu site é pochta.ru.

### Como é um código de rastreamento da Russian Post?

Um número postal internacional tem duas letras, nove dígitos e `RU`, como `RR482910576RU`. O Peek também associa códigos de 14 dígitos começando por `80` à Russian Post, quando o dígito verificador está correto.

### Por que as etapas no Peek não coincidem com pochta.ru?

O Peek lê o rastreador da Russian Post em inglês, então exibe os textos em inglês da Russian Post na maioria das etapas, e não os em russo. Quando esse rastreador não responde ao Peek, um serviço de rastreamento assume com suas próprias palavras e pode ficar atrasado. O Peek mostra as etapas da Russian Post em russo, como na tabela acima, quando acompanha a encomenda pelo rastreador da Qwintry.

### O Peek consegue rastrear uma encomenda enviada para a Rússia de outro país?

Sim. Cole o código que o remetente forneceu. O Peek acompanha pela transportadora que emitiu o código e transfere para a Russian Post somente se esse rastreamento indicar a Russian Post como a empresa entregadora.

:::sources
- [Universal Parcel Scraper: Russian Post](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/russian-post) – nomes, formatos 80 e RU com dígitos verificadores e correspondências fracas, o rastreador lido pelo Peek e suas etapas em inglês, locais, destino e fases, página de ajuda
- [Universal Parcel Scraper: Qwintry](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/qwintry) – etapas da Russian Post como exibidas no rastreador da Qwintry e suas fases, agência como local, código QR e códigos RU aceitos, link de pochta.ru
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – códigos RU que não começam por C, E, L ou R como operador postal desconhecido, transportadoras consultadas primeiro, ordem dos serviços de rastreamento, transferências para o correio do seu país e para a Russian Post
:::
