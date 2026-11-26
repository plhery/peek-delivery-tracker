---
title: Rastreamento CDEK (СДЭК): números de pedido e status
description: Rastreie uma encomenda CDEK (СДЭК): formato do número de pedido, significado dos status em russo, entrega por entregador ou ponto de retirada e como usar o Peek.
slug: rastreamento-cdek
published: 2026-11-26
updated: 2026-11-26
---

A CDEK (СДЭК, também grafada SDEK) é uma transportadora expressa. O catálogo do Peek a lista na Rússia, no Cazaquistão, em Belarus e na Sérvia. Ela entrega na sua porta por entregador ou em pontos de retirada e armários inteligentes onde você busca o pacote. A loja ou o remetente informa o código: cole-o no campo acima. O próprio rastreador da CDEK fica em my.cdek.ru. A CDEK escreve suas etapas em russo, e a tabela abaixo explica cada uma em português.

## Código de rastreamento CDEK: como ele é

A CDEK chama cada envio de pedido (заказ), portanto seu código de rastreamento é um número de pedido — mas não o número de pedido da loja. O rastreador da CDEK aceita códigos de 5 a 40 letras e dígitos: os números de pedido dela e outros códigos vinculados a um pedido, como o número postal de uma encomenda vinda do exterior. O Peek associa um formato à CDEK. Com dígitos inventados:

| Código (inventado) | O que é |
| --- | --- |
| `10482917365` | 11 dígitos começando com `10`: único formato que o Peek vincula à CDEK, e apenas como correspondência fraca. [GLS Germany](carrier:gls-de), GLS Switzerland, [GLS France](carrier:gls-fr), GLS Austria, GLS Poland, PostLogistics, Coordinadora e Mainfreight também usam 11 dígitos. |
| `48291736548` | 11 dígitos que não começam com `10`. O Peek não sugere a CDEK para ele: confira o código ou escolha a CDEK manualmente (abaixo). |
| `RR482917367CN` | Número postal vindo do exterior, aqui da [China Post](carrier:china-post). O Peek acompanha nesse correio. Se a CDEK o vinculou a um pedido, selecione CDEK para ver as etapas dela. |

Não encontra o seu? Veja [como encontrar seu código de rastreamento](guide:find-tracking-number) e [formatos de código de rastreamento](guide:tracking-number-formats).

## Status de rastreamento da CDEK em português

Cada etapa da CDEK tem um código interno, pelo qual o Peek se orienta, e um texto em russo, que o Peek exibe como a CDEK escreveu. A CDEK agrupa suas leituras sob algumas grandes etapas, como `В пути`: o Peek só exibe uma grande etapa quando ela não tem leituras detalhadas. Aproximadamente em ordem:

| A CDEK diz | O que significa para você | O Peek mostra |
| --- | --- | --- |
| `Создан` | Criado: o pedido existe no sistema da CDEK. Ela pode ainda não ter o pacote. | "Etiqueta criada" |
| `Назначен курьер` | Um entregador está a caminho do remetente para coletar o pedido. | "Etiqueta criada" |
| `Курьер забрал заказ у отправителя` | O entregador coletou o pedido com o remetente. | "Recebida pela transportadora" |
| `Принят на доставку` | Aceito para entrega: a CDEK já está com a encomenda. | "Recebida pela transportadora" |
| `В пути` | A caminho: grande etapa do trajeto entre o recebimento e o trecho final. | "Em trânsito" |
| `Принят СДЭК на территории РФ` | Uma encomenda vinda do exterior entrou na rede da CDEK na Rússia. | "Em trânsito" |
| `Отправлен в сортировочный центр` | Enviada a um centro de triagem. | "Em trânsito" |
| `Прибыл в сортировочный центр` | Chegou a um centro de triagem. | "Em trânsito" |
| `Отправлено в г.` e uma cidade | Enviada para aquela cidade. | "Em trânsito" |
| `Отправлен в пункт доставки` | Enviada à unidade de onde saem os entregadores. | "Em trânsito" |
| `Прибыл в пункт доставки` | Chegou a essa unidade de entrega. | "Em trânsito" |
| `Отправлен в пункт выдачи` | Enviada ao seu ponto de retirada. | "Em trânsito" |
| `Доставляет курьер` | Um entregador está realizando a entrega: etapa que reúne as tentativas. | "Saiu para entrega" |
| `Выдан курьеру` | Entregue ao entregador que faz a rota. Aparece de novo a cada nova tentativa. | "Saiu para entrega" |
| `Курьер не смог доставить заказ` | O entregador não conseguiu entregar o pedido. | "Tentativa de entrega" |
| `Готов к выдаче`, `Поступил. Заберите заказ` | Pronta: chegou ao ponto de retirada ou armário inteligente. Vá retirar. | "Pronta para retirada" |
| `Вручён` | Entregue a você: concluída. | "Entregue" |
| `Не вручён` | Não entregue: o pedido terminou sem chegar a você, por não ter sido retirado ou por recusa. | "Precisa de atenção" |

A CDEK também pode encerrar um pedido como cancelado ou como parcialmente entregue quando você recebeu parte dos itens e recusou o restante. O Peek mostra "Precisa de atenção" para pedido cancelado e "Entregue" para pedido parcialmente entregue.

## Como a CDEK entrega: entregador ou ponto de retirada

As etapas mostram de qual maneira a encomenda chegará até você.

**Na sua porta.** Uma encomenda na unidade de entrega (`Прибыл в пункт доставки`) sai com o entregador (`Выдан курьеру`). Após uma entrega não realizada, `Выдан курьеру` aparece de novo na próxima tentativa.

**Em ponto de retirada ou armário.** Depois de `Отправлен в пункт выдачи`, aguarde `Поступил. Заберите заказ` e então vá buscar.

A página do pedido em my.cdek.ru mostra o ponto de retirada, endereço, contatos, horário de funcionamento e último dia para retirada. O Peek omite esses dados, então consulte lá. O Peek não conseguiu ler o site principal cdek.ru, por isso esta página não informa prazos de entrega, tempos de guarda nem regras de tentativas. Veja [encomendas não retiradas](guide:uncollected-parcel), [entregas não realizadas](guide:missed-delivery) e [armários de encomendas](guide:parcel-lockers).

**Do exterior.** `Принят СДЭК на территории РФ` significa que a CDEK assumiu na Rússia uma encomenda vinda do exterior. Esta página não traz regras nem taxas alfandegárias porque o Peek não pôde verificá-las numa página oficial. Veja [encomendas retidas na alfândega](guide:customs).

## Encomenda CDEK parada ou sem chegar

**Apenas `Создан`, ou nenhuma etapa.** O remetente pode ainda não ter entregue o pacote, ou há um erro de digitação no código. O Peek continua consultando. Confira o código dígito por dígito na mensagem da loja e, se nada mudar em alguns dias, pergunte à loja quando o envio foi feito.

**Dias sem nova etapa.** Se a etapa mais recente tiver quatro dias ou mais e a encomenda ainda não tiver saído para entrega nem chegado ao ponto de retirada, o Peek destaca o dia dessa etapa: "Sem atualizações desde seg 12 out". Saiba mais em [por que o rastreamento para de atualizar](guide:tracking-not-updating).

**`Курьер не смог доставить заказ`.** O entregador não conseguiu fazer a entrega. Uma nova tentativa aparece outra vez como `Выдан курьеру`. Se não surgir outra tentativa, fale com a loja que contratou a CDEK.

**`Не вручён`.** O pedido encerrou sem chegar a você. Pergunte à loja o que acontece agora: reembolso ou reenvio. Veja [encomenda devolvida ao remetente](guide:returned-to-sender).

**Marcada como entregue, mas não chegou.** Confira se alguém recebeu por você e depois procure a loja. Veja mais em [encomenda marcada como entregue mas não recebida](guide:delivered-not-received).

**Extraviada.** A loja contratou a entrega com a CDEK, então procure a loja primeiro. Veja [encomendas extraviadas](guide:lost-parcel).

## Atendimento ao cliente e contato da CDEK

O Peek não consegue ler o site principal cdek.ru, então esta página não lista telefone nem e-mail: o Peek só informa contatos lidos nas páginas da própria transportadora. O rastreador e as páginas de pedido da CDEK ficam em my.cdek.ru.

Em compras de loja virtual, fale primeiro com a loja: foi ela quem contratou o envio.

## Acompanhe uma encomenda CDEK com o Peek

O [Peek](/) não pode ter certeza de que um código é da CDEK só pelo formato. Cole-o mesmo assim: o Peek acompanha mais de 4.500 transportadoras.

- **11 dígitos começando com 10.** O Peek pergunta às transportadoras compatíveis com esse formato se alguma tem a encomenda, de cinco em cinco, exibindo "Consultando serviços de rastreamento…" enquanto verifica. Se você consultar da Rússia, Cazaquistão, Belarus ou Sérvia (pela região do celular ou local de conexão na web), a CDEK é consultada primeiro. De outros países, ela entra entre as cinco primeiras ou as cinco seguintes. Se a CDEK reconhecer o número, o Peek indica CDEK dizendo "está com esta encomenda". Se várias transportadoras o reconhecerem, o Peek pede que você escolha. Se nenhuma reconhecer ainda, aparece "ainda não encontrada · o Peek continua verificando". Você também pode tocar em "Trocar" e selecionar CDEK em "Compatível com este código".
- **Outro formato.** Toque em "Trocar" e busque por CDEK: digitar СДЭК ou SDEK também encontra.
- **Um link.** Cole um link de pedido de my.cdek.ru e o Peek rastreia o pedido pela CDEK.

Em uma encomenda já adicionada, use "Trocar transportadora". Uma encomenda adicionada sem transportadora, antes de alguém reconhecê-la, exibe "Ainda sem histórico de rastreamento". Se você a classificou como CDEK, ela exibe "Aguardando CDEK" até a CDEK reconhecer o código. Se após dois dias a CDEK ainda não o reconhecer, o Peek pede para você conferir o código, escolher outra transportadora ou excluir a encomenda.

**O que o Peek mostra.** O Peek lê o rastreamento próprio da CDEK apenas com o código: sem conta e sem confirmação por telefone. Você vê o status, cada etapa nas palavras da CDEK e a cidade em que ocorreu, no horário exato. A resposta anônima da CDEK oculta o código de retirada destinado a destinatários logados, e o Peek omite o endereço do remetente, os detalhes do ponto de retirada, o prazo de armazenagem e pagamentos, além de não receber peso nem data prevista de entrega da CDEK. A resposta da CDEK traz as iniciais e o endereço do destinatário: o Peek mostra as iniciais em "Destinatário" na página privada da sua encomenda, nunca em link compartilhado, e usa a cidade do endereço para a distância restante no seu mapa. A página traz o link "Abrir o site de CDEK" para a página do pedido.

**Outro número para o mesmo pedido.** Se você rastrear um código que a CDEK vinculou a um pedido, como um número postal do exterior, a CDEK responde com o próprio número de pedido dela. O Peek o exibe por último, como "CDEK rastreia com o código".

**Quando a CDEK não responde.** Os sites da CDEK podem bloquear consultas automatizadas. Nesses casos, o Peek consulta serviços de rastreamento: ParcelsApp, Ship24 e 17TRACK. A cópia do ParcelsApp para leituras da CDEK descreve os trechos da viagem em vez das etapas da CDEK e pode trazer apenas a data de cada leitura. Quando a CDEK volta a responder, o Peek retorna a ela.

**Número pertencente a outra transportadora.** Se você classificou um código de 11 dígitos como CDEK e a CDEK não o conhece, o Peek consulta outras transportadoras compatíveis nos primeiros 30 dias da encomenda. Se uma delas mostrar movimentação, o Peek transfere para lá e exibe "Transportadora trocada automaticamente (antes: CDEK)".

**Encomendas repassadas à CDEK.** A Qwintry, um agente de cargas, repassa algumas encomendas à CDEK. Quando o rastreador da Qwintry cita a CDEK com o número da CDEK, a encomenda mostra "Entrega por CDEK" (ou "Anunciada a CDEK, ainda não recebida" enquanto a CDEK tiver apenas a previsão, sem leitura própria) e esse número. O Peek passa a consultar a CDEK também e acompanha a encomenda lá assim que a CDEK mostrar uma etapa pelo menos tão recente quanto a última da Qwintry. A DobroPost, que traz pedidos da China, mostra as etapas da CDEK no próprio rastreador quando a CDEK faz a última milha: o Peek acompanha essa encomenda lá pelo número DBRPST. Para um número postal do exterior classificado como CDEK, o Peek também pode consultar o correio do país de onde você pesquisa da mesma forma: Belpost em Belarus, Royal Mail no Reino Unido ou USPS nos EUA, por exemplo, mas nenhum na Rússia ou no Cazaquistão.

**Verificações e alertas.** Enquanto você mantiver a encomenda aberta, ou no dia da entrega com as notificações ativadas, o Peek consulta a CDEK até a cada 10 minutos, ou até a cada 2 minutos a partir das 6h no horário de Moscou no dia em que a CDEK registra a saída para entrega, enquanto o próprio rastreador da CDEK responder. Fora isso, e após dois dias sem nova etapa (12 horas enquanto mostrar "Etiqueta criada" ou "Recebida pela transportadora"), verifica a cada hora. Você pode receber avisos para "Cada movimentação", "Etapas importantes" ou "Só a entrega". Para ponto de retirada, escolha "Etapas importantes": "Só a entrega" não avisa quando o pacote fica pronto para retirada. O Peek não pode contatar a CDEK nem a loja por você.

## Perguntas sobre o rastreamento CDEK

### O que é СДЭК em alfabeto latino?

CDEK. Também se escreve SDEK e, às vezes, CDEK Express. Seu rastreador fica em my.cdek.ru.

### Como é um código de rastreamento da CDEK?

O Peek associa códigos de 11 dígitos iniciados por `10` à CDEK, como `10482917365`, mas outras transportadoras também usam 11 dígitos.

### O que significa "Поступил. Заберите заказ" no rastreamento da CDEK?

Sua encomenda chegou ao ponto de retirada ou ao armário inteligente e já pode ser retirada. O Peek exibe "Pronta para retirada". Confira o endereço e a data-limite para retirada na página do pedido da CDEK.

### Posso acompanhar uma encomenda CDEK em português?

Sim. O Peek exibe o status da encomenda em português, como "Em trânsito", e o histórico nas palavras em russo da CDEK, explicadas na tabela acima.

### Por que o Peek pede para escolher a transportadora no meu código CDEK?

GLS, PostLogistics e outras transportadoras também usam 11 dígitos. Se mais de uma reconhecer o seu número, o Peek não tem como saber qual encomenda é a sua: selecione CDEK na lista.

:::sources
- [Universal Parcel Scraper: CDEK](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/cdek) – nomes, países, formato de 11 dígitos e correspondência fraca, códigos aceitos pelo rastreador da CDEK, links de my.cdek.ru, códigos de etapa, textos em russo e fases, o que a página do pedido exibe e o que o Peek mantém, horários exatos, número próprio do pedido, bloqueio antibot
- [Universal Parcel Scraper: Qwintry](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/qwintry) – rastreador da Qwintry informando CDEK e seu número
- [Universal Parcel Scraper: DobroPost](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/dobropost) – etapas da CDEK na parte final da viagem no rastreador da DobroPost
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – transportadoras que compartilham o formato de 11 dígitos e ordem de consulta, ordem dos serviços de rastreamento, cópias do ParcelsApp para a CDEK, transferências, Belpost para números postais em Belarus
:::
