---
title: Seguimento Russian Post (Почта России): números e estados
description: Segue uma encomenda Russian Post (Почта России): números RU e de 14 dígitos, significado dos estados em russo e seguimento com o Peek.
slug: seguimento-russian-post
published: 2026-11-22
updated: 2026-11-22
---

A Russian Post (Почта России, Pochta Rossii) é o serviço postal da Rússia. Transporta encomendas enviadas a partir da Rússia e encomendas enviadas para a Rússia. O remetente ou a loja dá-te o número de seguimento: cola-o na caixa acima. O site oficial da Russian Post é pochta.ru, e o Peek lê o seu seguimento por ti, como explicado abaixo.

## Número de seguimento Russian Post: que aspeto tem

Com algarismos inventados:

| Número (inventado) | O que é |
| --- | --- |
| `RR482910576RU` | Duas letras, nove algarismos e `RU`: o formato postal internacional terminado no código da Rússia, com dígito de controlo no nono algarismo. Quando começa por C, E, L ou R, o Peek reconhece logo a Russian Post. |
| `VV482910576RU` | Outro número internacional em `RU` cuja primeira letra não é C, E, L nem R. O Peek mostra-o como «Unknown postal carrier» (correio desconhecido): podes escolher a Russian Post manualmente. |
| `80482910573625` | 14 algarismos começados por `80`, sendo o último um dígito de controlo. O Peek associa-o à Russian Post apenas como correspondência fraca, pois a DPD, SEUR, BRT, Delhivery e outras transportadoras também usam este formato. |
| `80482910573620` | O mesmo formato com dígito de controlo errado. O Peek não sugere a Russian Post, apenas as outras transportadoras: se for um número da Russian Post, verifica se há algum erro de escrita. |
| `RR482910576CN` | Um número emitido por outro correio, aqui a [China Post](carrier:china-post). O Peek segue-o com esse correio, mesmo quando viaja para a Rússia. |

Espaços e minúsculas não interessam quando colas um número no Peek. Se não encontras o teu, vê [onde encontrar o número de seguimento](guide:find-tracking-number) e [formatos de números de seguimento](guide:tracking-number-formats).

## Estados de seguimento Russian Post explicados

A Russian Post escreve os seus passos em russo. O Peek lê-os, tal como constam da tabela abaixo, no seguimento da Qwintry, uma intermediária que entrega algumas encomendas à Russian Post: numa encomenda adicionada com o número da Qwintry, ou adicionada com o número RU e associada à Qwintry (vê «Encomendas da Qwintry» mais abaixo). Aí, cada passo da Russian Post tem uma operação, como `обработка` (processamento), uma linha de detalhe e o posto postal no final. Quando não há linha de detalhe, a própria operação aparece como passo. Aproximadamente pela ordem em que surgem:

| A Russian Post diz | O que significa para ti | O Peek mostra |
| --- | --- | --- |
| `Партионный` | A Russian Post recebeu a encomenda num lote com outras (operação `приём`, receção). | «Em trânsito» |
| `экспорт международной почты` | Exportação de correio internacional: a encomenda sai do país onde está. | «Em trânsito» |
| `Прибыло на территорию РФ` | A encomenda chegou à Rússia. | «Em trânsito» |
| `импорт международной почты` | Importação de correio internacional: a encomenda está a dar entrada na Rússia. | «Em trânsito» |
| `приём на таможню` | Entregue à alfândega. | «Na alfândega» |
| `Выпущено таможней` | Libertada pela alfândega: segue novamente viagem. | «Em trânsito» |
| `Покинуло место международного обмена` | Saiu do centro que trata correio vindo do estrangeiro. | «Em trânsito» |
| `Сортировка` | Triagem. | «Em trânsito» |
| `Прибыло в сортировочный центр` | Chegou a um centro de triagem. | «Em trânsito» |
| `Покинуло сортировочный центр` | Saiu de um centro de triagem. | «Em trânsito» |
| `Прибыло в место вручения` | Chegou ao posto postal que vai fazer a entrega ou onde a podes levantar. | «Em trânsito» |
| `Передано почтальону` | Entregue ao carteiro para distribuição. | «Em distribuição» |
| `Временное отсутствие адресата` | Não entregue: não estavas em casa. | «Tentativa de entrega» |
| `Адресат заберет отправление сам` | Não entregue porque vais levantar a encomenda pessoalmente. | «Tentativa de entrega» |
| `Вручение адресату` | Entregue a ti, o destinatário. | «Entregue» |

Para qualquer outra encomenda da Russian Post, o Peek lê o próprio seguimento da Russian Post, que responde em inglês: vê «Seguir uma encomenda Russian Post com o Peek» abaixo.

## Como a Russian Post entrega

Os passos acima mostram o percurso na Rússia: centros de triagem e depois o posto postal que entrega a encomenda. A partir daí, um carteiro pode levá-la (`Передано почтальону`). Se não estavas ou se a vais levantar, o passo indica-o. No seguimento da Qwintry, o Peek mostra a localidade do posto postal como local do passo. Com o Peek em russo, ou quando não consegue localizar o posto, mostra o nome do posto exatamente como a Russian Post o escreve, para saberes qual tem a tua encomenda.

Esta página não indica prazos de entrega, tempos de guarda nem regras de tentativas de entrega porque o Peek não pôde verificá-los no site da Russian Post. Para uma encomenda à espera num posto postal, vê [encomendas não levantadas](guide:uncollected-parcel) e [entregas falhadas](guide:missed-delivery).

**Enviada da Rússia para outro país.** O Peek não sabe à partida qual a empresa que entrega uma encomenda da Russian Post no teu país. Num número RU, pergunta também ao correio do país que o seguimento da Russian Post indica como destino e pode continuar a segui-la lá (vê abaixo).

**Enviada para a Rússia de outro país.** O Peek segue a encomenda com a transportadora que emitiu o número, através do seguimento desta ou de serviços de seguimento. Se esse seguimento indicar a Russian Post como a empresa que faz a entrega, o Peek consulta também a Russian Post e transfere a encomenda para lá assim que esta mostrar um registo pelo menos tão recente como o último conhecido. O simples facto de ir para a Rússia não basta para a transferir.

## Encomenda Russian Post parada ou sem chegar

**Ainda sem passos.** O seguimento da Russian Post ou os serviços de seguimento podem ainda não conhecer o número, ou o remetente ainda não entregou a encomenda. O Peek continua a consultar. Se nada aparecer ao fim de alguns dias, confirma o número com o remetente. Mais em [porque é que o seguimento para de atualizar](guide:tracking-not-updating).

**Parada na alfândega.** Se o Peek mostra «Na alfândega», a encomenda está na alfândega. Esta página não apresenta regras nem taxas alfandegárias por não ter sido possível verificá-las numa página oficial. Vê [encomendas retidas na alfândega](guide:customs).

**O Peek está atrasado.** Quando o seguimento da Russian Post não responde ao Peek, um serviço de seguimento substitui-o e pode ter atraso face ao site oficial. Compara com pochta.ru antes de te preocupares.

**Entregue, mas não recebeste nada.** Se o Peek indica «Entregue» e não tens nada, pergunta no teu posto postal e avisa o remetente. Mais em [encomenda dada como entregue mas não recebida](guide:delivered-not-received).

**Perdida ou devolvida.** Fala primeiro com o remetente ou com a loja: foram eles que contrataram o envio. Vê [encomendas perdidas](guide:lost-parcel) e [devolvida ao remetente](guide:returned-to-sender).

## Apoio ao cliente e contactos da Russian Post

Como o Peek não conseguiu ler o site da Russian Post, esta página não indica número de telefone nem endereço de e-mail: só publicamos contactos lidos diretamente nas páginas da transportadora. As páginas de ajuda da Russian Post ficam em info.pochta.ru. Numa encomenda parada, o Peek apresenta uma ligação para a página de ajuda da Russian Post sobre seguimento como «Pede ajuda a Russian Post».

Numa compra numa loja, fala primeiro com a loja: foi ela quem contratou a entrega.

## Seguir uma encomenda Russian Post com o Peek

Cola o número no [Peek](/): o Peek segue mais de 4500 transportadoras. Consoante a forma do número:

- **Número internacional em `RU` começado por C, E, L ou R (dígito de controlo válido).** O Peek deteta logo a Russian Post («Transportadora detetada»).
- **Outro número internacional em `RU` (outra letra inicial).** O Peek mostra-o como «Unknown postal carrier». Toca em «Alterar» (ou mais tarde em «Alterar transportadora») e escolhe Russian Post: escrever Почта России também a encontra. O Peek passa então a mostrar Russian Post.
- **14 algarismos começados por `80` (dígito de controlo válido).** Enquanto a adicionas, o Peek mostra «A verificar serviços de seguimento…» e pergunta ao seguimento da Russian Post, bem como à DPD e a outras transportadoras compatíveis, se têm a encomenda. Se só a Russian Post a tiver, o Peek mostra «Russian Post tem esta encomenda.» e guarda-a aí. Caso contrário, toca em «Alterar»: a Russian Post aparece em «Corresponde a este número», com as outras transportadoras compatíveis, começando pelas do teu país. Numa encomenda já adicionada, usa «Alterar transportadora».

O Peek lê o próprio seguimento da Russian Post, que lhe responde em inglês. Mostra cada passo nas palavras inglesas da Russian Post, como `Sorting`, `Arrived at the local distribution center` ou `Forwarded to the postman` («Em distribuição»), com a hora, a cidade, o país e o país de destino. `Available for pickup at the Post Office` aparece como «Pronta para levantamento» e `Handed over for temporary storage` como «Problema reportado». Depois de `Returned to the sender` (também «Problema reportado»), a encomenda regressa ao remetente e a sua entrega passa a «Devolvida ao remetente». A Russian Post não dá data prevista de entrega. Enquanto o seu seguimento não conhece o número, o Peek mostra «À espera de Russian Post», ou «À espera do primeiro registo de Russian Post» num número `80` adicionado sem transportadora. Quando o seguimento da Russian Post não responde ao Peek, o Peek consulta serviços de seguimento — ParcelsApp, Ship24 e 17TRACK por esta ordem, e num número RU o seguimento da União Postal Universal por último — e mostra o histórico do primeiro que tiver a encomenda, nas palavras desse serviço, o que pode ficar atrás de pochta.ru. Um serviço que não conhecia o número volta a ser consultado no dia seguinte.

Num número `80` adicionado sem transportadora, ou num número RU mostrado como «Unknown postal carrier», o Peek também pergunta, nos primeiros 30 dias e antes dos serviços de seguimento, às transportadoras que podem ter a encomenda (como a Russian Post, DPD e outras nos números `80`). Se uma mostrar movimento, o Peek transfere a encomenda para lá. Se adicionaste um número `80` sem transportadora e um serviço de seguimento indicar apenas a Russian Post com passos datados, o Peek associa-a automaticamente à Russian Post. Num número RU mostrado como «Unknown postal carrier», essa indicação de um serviço não chega: o Peek precisa de ver a encomenda no seguimento da Russian Post ou que escolhas a Russian Post tu mesmo.

O Peek não tem ligação direta para o seguimento da Russian Post. Se um serviço de seguimento respondeu, a ligação na página da encomenda abre esse serviço, por exemplo «Abrir o site de ParcelsApp». Para pochta.ru, abre-o tu mesmo.

**Encomendas da Qwintry.** Se a Qwintry enviou a tua encomenda para a Rússia, adiciona antes o número da Qwintry: `QR` e oito algarismos. O Peek reconhece logo a Qwintry e lê o seu seguimento, que continua a mostrar os passos da Russian Post depois de os correios receberem a encomenda. Quando o seguimento da Qwintry refere a Russian Post, a encomenda mostra «Entrega: Russian Post» e o número RU dos correios, e o Peek pergunta também à Russian Post: assim que esta mostrar um registo pelo menos tão recente como o último da Qwintry, o Peek passa a segui-la aí, guardando o histórico da Qwintry. Enquanto a Russian Post tiver apenas o aviso prévio sem registo próprio, a encomenda mostra «Anunciada a Russian Post, ainda não recebida». O seguimento da Qwintry também aceita o número RU de uma encomenda entregue por si aos correios: se só tens esse número e a Russian Post não o mostra, toca em «Alterar» e escolhe Qwintry.

**O correio do teu país.** Num número RU associado à Russian Post, o seguimento da Russian Post indica o país de destino e o Peek pergunta também ao correio desse país com o número RU, se ler diretamente o seu seguimento, como faz com a [USPS](carrier:usps), [Royal Mail](carrier:royal-mail), An Post, Canada Post e Australia Post. Numa encomenda mostrada como «Unknown postal carrier», basta que um serviço de seguimento indique o correio de entrega com o respetivo número, ou o país de destino num número RU: o Peek consulta esse correio da mesma forma e passa a segui-la lá assim que mostrar um registo pelo menos tão recente como o último conhecido.

Enquanto tens a encomenda aberta no ecrã, ou no dia da entrega com as notificações ativas, o Peek verifica o seguimento da Russian Post até de 10 em 10 minutos — e até de 2 em 2 minutos a partir das 6:00 (hora local) no dia em que sai para distribuição — enquanto o próprio seguimento da Russian Post responder; através de serviços de seguimento, de hora a hora, de 3 em 3 horas após um dia sem novos registos e até de 15 em 15 minutos no dia em que sai para distribuição. Fora disso, e após dois dias sem novidades (12 horas enquanto continuar em «Etiqueta criada» ou «Aceite pela transportadora»), verifica de hora a hora. Pode notificar-te de «Todos os registos», «Passos importantes» ou «Só a entrega». O Peek não pode contactar a Russian Post nem o remetente por ti.

## Perguntas sobre o seguimento Russian Post

### O que significa Почта России?

Correios da Rússia, conhecidos internacionalmente por Russian Post ou Pochta Rossii. O site oficial é pochta.ru.

### Que aspeto tem um número de seguimento Russian Post?

Um número internacional tem duas letras, nove algarismos e `RU`, como `RR482910576RU`. O Peek também associa à Russian Post números de 14 algarismos começados por `80` quando o dígito de controlo está certo.

### Porque é que os passos no Peek não coincidem com pochta.ru?

O Peek consulta o seguimento da Russian Post em inglês, pelo que na maioria dos passos mostra os textos ingleses da Russian Post em vez dos russos. Quando esse seguimento não responde ao Peek, entra um serviço de seguimento com os seus próprios textos e possível atraso. O Peek mostra os passos em russo da tabela acima nas encomendas seguidas através da Qwintry.

### O Peek consegue seguir uma encomenda enviada para a Rússia de outro país?

Sim. Cola o número dado pelo remetente: o Peek segue-o com a transportadora que o emitiu e só muda para a Russian Post se esse seguimento indicar a Russian Post como a empresa que faz a entrega.

:::sources
- [Universal Parcel Scraper: Russian Post](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/russian-post) – nomes, formatos `80` e `RU` com dígitos de controlo e correspondências, seguimento lido pelo Peek e textos em inglês, locais, destino, etapas e página de ajuda
- [Universal Parcel Scraper: Qwintry](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/qwintry) – passos russos da Russian Post no seguimento da Qwintry e respetivas etapas, posto postal como local, número `QR` e números `RU` aceites
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – números postais `RU`, transportadoras consultadas primeiro, ordem dos serviços de seguimento, passagens para o correio do teu país e para a Russian Post
:::
