---
title: Rastreio Ozon: números de encomenda e estados em português
description: Segue uma encomenda Ozon (Ozon Rocket, Ozon Доставка): formato dos números com hífenes, significado dos estados russos em português e seguimento no Peek.
slug: rastreio-ozon
published: 2026-11-21
updated: 2026-11-21
---

O serviço de entregas da Ozon transporta as encomendas que os vendedores lhe entregam, até à tua porta ou a um ponto de recolha. O catálogo do Peek lista-o na Rússia, Bielorrússia e Cazaquistão, também com os nomes Ozon Rocket, OZON Rocket Доставка, Ozon Доставка e Ozon Dostavka. Cola o teu número na caixa acima, com os hífenes. O rastreio próprio da Ozon é tracking.ozon.ru, e o Peek lê-o por ti conforme explicado abaixo.

## Número de seguimento Ozon: como é

O Peek conhece dois formatos para a Ozon, ambos escritos com hífenes. Os hífenes interessam: sem eles, os algarismos parecem-se com os de outras transportadoras. Exemplos com algarismos inventados:

| Número (inventado) | O que é |
| --- | --- |
| `48291057-0362-1` | 8 algarismos, 4 algarismos e 1 algarismo ligados por hífenes. O Peek identifica logo a Ozon. |
| `4829105736-2841-2` | O mesmo formato com 10 algarismos no início. O Peek também identifica a Ozon. |
| `4829105703621` | O primeiro número sem hífenes, ou com espaços no lugar deles. A DPD France, a Ukrposhta e outras transportadoras têm números de 13 algarismos, pelo que só os algarismos não dizem ao Peek que é Ozon. Adicionado a partir da Rússia, Bielorrússia ou Cazaquistão, o Peek pergunta primeiro à Ozon; a partir de outros países pergunta-lhe em último lugar, quando as outras transportadoras e os serviços de seguimento não encontraram o número. Volta a pôr os hífenes ou escolhe a Ozon à mão (abaixo). O mesmo vale para 15 algarismos, usados pela FedEx e outras. |
| `PL-482910-57362` | `PL`, 6 algarismos e mais letras ou algarismos, com hífenes. O Peek identifica a Ozon. |
| `PL-482910-5736` | `PL` seguido exatamente de 10 algarismos tem também a forma dos números da [Amazon](carrier:amazon-logistics). O Peek toma-o por um número da Amazon e não permite escolher a Ozon para ele. |

Minúsculas não interessam: `pl-` funciona igual a `PL-`. Não encontras o número? Vê [onde encontrar o número de seguimento](guide:find-tracking-number) e [formatos de números de seguimento](guide:tracking-number-formats).

## Estados de seguimento Ozon em português

O código do Peek lista os passos que o rastreio da Ozon mostra em russo. O Peek lê esse rastreio e apresenta os passos nas mesmas palavras russas, exceto `Заказ в работе`, `Заказ на пути к границе страны назначения` e `Заказ прибывает на приграничный пункт`, que deixa de fora (vê «Seguir uma encomenda Ozon com o Peek»).

**No teu país.** Mais ou menos pela ordem habitual:

| O que diz a Ozon | O que significa para ti |
| --- | --- |
| `Передается в доставку` | O vendedor embalou o pedido e está a entregá-lo para envio. A Ozon pode ainda não o ter. |
| `Заказ принят перевозчиком.` | A transportadora aceitou a encomenda: está a caminho. |
| `Заказ отправили на сортировочный терминал`, `Заказ отправлен на сортировочный терминал.` | Enviada para um centro de triagem. |
| `Заказ покинул сортировочный терминал.` | Saiu do centro de triagem. |
| `Заказ ожидает отправки в город получателя.` | Aguarda expedição para a tua cidade. |
| `Заказ везут в город получателя` | A caminho da tua cidade. |
| `Заказ везут` | A caminho. |
| `Заказ передали в курьерскую доставку` | Entregue à distribuição por estafeta, que te telefona para combinar a hora. |
| `Заказ в пункте выдачи` | Chegou ao teu ponto de recolha: podes ir buscá-la. |
| `Заказ получен в пункте выдачи.` | Levantada no ponto de recolha ou estação de correios: entregue. |
| `Заказ успешно доставлен получателю.` | Entregue. O rastreio da Ozon mostra este texto quando a encomenda não foi para ponto de recolha nem correios, como numa entrega por estafeta. |
| `Заказ отменён` | O pedido foi cancelado. |
| `Заказ в работе` | Em curso: marcador de espera no rastreio da Ozon, não uma etapa do trajeto. |

**De outro país.** Uma encomenda enviada do estrangeiro recebe também etapas aduaneiras, aproximadamente por esta ordem:

| O que diz a Ozon | O que significa para ti |
| --- | --- |
| `Заказ везут на таможню в стране отправления` | A caminho da alfândega no país de origem. |
| `Заказ привезли на таможню для экспортного таможенного оформления` | Chegou à alfândega de exportação nesse país. |
| `Заказ покинул зону экспортного таможенного оформления` | Saiu da zona de desalfandegamento de exportação. |
| `Заказ на пути к границе страны назначения` | A caminho da fronteira do teu país. |
| `Заказ прибывает на приграничный пункт` | A chegar ao posto fronteiriço. |
| `Заказ привезли в страну назначения` | Chegou ao teu país. |
| `Заказ передан на импортное таможенное оформление.` | Entregue à alfândega de importação no teu país. |
| `Заказ проходит импортное таможенное оформление` | Em desalfandegamento de importação. |
| `Заказ выпущен импортной таможней.` | Libertada pela alfândega de importação: novamente em caminho. |
| `Импортная таможня задержала заказ` | A alfândega de importação reteve a encomenda para controlo adicional. |

## Como a Ozon entrega: estafeta ou ponto de recolha

As etapas dizem de que modo chega o teu pedido.

**À tua porta.** Após `Заказ передали в курьерскую доставку` (mostrado como «Em trânsito» no Peek), um estafeta traz a encomenda e liga primeiro para combinar a hora. O último passo é `Заказ успешно доставлен получателю.`

**Num ponto de recolha.** Espera por `Заказ в пункте выдачи` («Pronta para levantamento») e vai buscá-la. Depois de a levantares, aparece `Заказ получен в пункте выдачи.` (o mesmo texto aplica-se ao levantamento numa estação de correios).

Como o Peek não conseguiu ler o site da Ozon, esta página não indica prazos de entrega, horários de abertura, prazos de guarda nem regras de tentativas de entrega: pergunta à Ozon. Vê também [encomenda não levantada](guide:uncollected-parcel) e [entrega falhada](guide:missed-delivery).

**Do estrangeiro.** As etapas aduaneiras acima mostram onde está um pedido internacional. Esta página não indica regras nem taxas alfandegárias, pois o Peek não conseguiu confirmá-las numa fonte oficial. Vê [encomendas na alfândega](guide:customs).

## Encomenda Ozon atrasada ou seguimento parado

**Ainda sem passos, ou só `Передается в доставку`.** O vendedor pode ainda não ter entregue a encomenda, ou o rastreio da Ozon ainda não tem o número. O Peek continua a perguntar. Se adicionaste o número sem hífenes, escolhe Ozon em «Alterar transportadora».

**O Peek está atrás do tracking.ozon.ru.** Quando o rastreio da Ozon não responde ao Peek, entra um serviço de seguimento que pode estar atrasado. Compara com tracking.ozon.ru antes de te preocupares.

**Dias sem um passo novo.** Se o passo mais recente tiver quatro dias ou mais enquanto o Peek mostra «Etiqueta criada», «Aceite pela transportadora» ou «Em trânsito», o Peek assinala-o com o dia desse passo («Sem atualizações desde seg 12 out») e inclui uma ligação para a ajuda da Ozon («Pede ajuda a Ozon»). Mais em [porque o seguimento não atualiza](guide:tracking-not-updating).

**`Импортная таможня задержала заказ`.** A alfândega está a verificar a encomenda. Aguarda `Заказ выпущен импортной таможней.`; se demorar muito sem mudar, pergunta à Ozon.

**`Заказ отменён`.** O pedido foi cancelado e não vai chegar. Pede reembolso à Ozon ou à loja onde compraste.

**Entregue, mas nada chegou.** Verifica se alguém a levantou por ti e contacta a Ozon. Vê [entregue mas não recebida](guide:delivered-not-received).

**Perdida.** Contacta a Ozon ou a loja onde fizeste a compra. Vê [encomenda perdida](guide:lost-parcel).

## Apoio ao cliente e contacto da Ozon

Como o Peek não conseguiu ler o site da Ozon, esta página não apresenta número de telefone nem e-mail: o Peek só lista contactos lidos nas páginas oficiais da transportadora. O rastreio da Ozon está em tracking.ozon.ru.

Numa compra feita na Ozon, contacta a Ozon. Numa compra noutra loja, fala primeiro com essa loja, que contratou a entrega.

## Seguir uma encomenda Ozon com o Peek

O [Peek](/) segue um número Ozon apenas com o número: sem conta, telefone nem código.

- **Com os hífenes.** O Peek identifica logo a Ozon («Transportadora detetada») e arquiva a encomenda na Ozon.
- **Sem os hífenes.** O Peek não consegue distinguir a transportadora só pelos algarismos. Cola-o na mesma: o Peek segue mais de 4500 transportadoras. Enquanto consulta as transportadoras compatíveis e os serviços de seguimento, mostra «A verificar serviços de seguimento…». Se adicionares o número a partir da Rússia, Bielorrússia ou Cazaquistão, o Peek pergunta primeiro à Ozon; a partir de outros países, em último lugar, quando as outras transportadoras e os serviços de seguimento não o encontraram. Se os serviços de seguimento indicarem a Ozon com passos datados, o Peek arquiva-a na Ozon sozinho; caso contrário, toca em «Alterar» e procura Ozon (ou «Alterar transportadora» numa encomenda já adicionada).
- **Uma ligação.** Cola uma ligação de tracking.ozon.ru com `track=` ou de rocket.ozon.ru com `SearchId=`, e o Peek segue esse número na Ozon.

**De onde vêm os passos.** O Peek lê o rastreio da Ozon e mostra cada passo no russo da Ozon (como nas tabelas acima), com a hora e as datas previstas de entrega dadas pela Ozon. As horas com desfasamento explícito são lidas diretamente e as restantes na hora de Moscovo, sendo mostradas no fuso horário do teu dispositivo; no peektracker.com e nas apps para iPhone e Android, o histórico termina com «Horas no teu fuso horário:» e o respetivo nome. Como a Ozon não dá local para os passos, não há mapa. Enquanto a Ozon não conhecer o número, o Peek mostra «À espera de Ozon», ou «Ainda sem histórico de seguimento» se tiver sido adicionado sem transportadora. Se o rastreio da Ozon não responder, o Peek consulta por ordem ParcelsApp, Ship24 e 17TRACK e mostra o histórico do primeiro que tiver a encomenda, nas palavras dele (podendo estar atrás do tracking.ozon.ru). A consulta direta da Ozon no Peek não aceita números `PL-`: o Peek mostra «Verifica o número de seguimento» até um serviço de seguimento encontrar a encomenda.

**A ligação na página da encomenda.** «Abrir o site de Ozon» abre o número em tracking.ozon.ru. Se tiver respondido um serviço de seguimento, a ligação abre esse serviço, como «Abrir o site de ParcelsApp».

**Encomenda de outra transportadora.** O Peek guarda o número Ozon sem hífenes. Enquanto o rastreio da Ozon não tiver mostrado a encomenda, uma verificação sem resposta consulta também nos primeiros 30 dias, antes dos serviços de seguimento, algumas transportadoras com o mesmo número de algarismos (como Ukrposhta e Delhivery para 13, ou Hermes Germany e BRT para 15). Se uma delas mostrar movimento, o Peek muda a encomenda para lá e indica «Alterado automaticamente de Ozon».

**Entrega por outra transportadora.** Se um serviço de seguimento indicar outra transportadora com o respetivo número e o Peek ler o rastreio direto dela, consulta-a também; assim que mostrar um registo pelo menos tão recente como o do serviço, passa a segui-la lá.

**Verificações e alertas.** Enquanto tens a encomenda aberta no ecrã ou no dia da entrega com as notificações ativas e o rastreio da Ozon responde, o Peek verifica até de 10 em 10 minutos; caso contrário, de hora a hora. Enquanto um serviço de seguimento acompanhar a encomenda, o Peek verifica de hora a hora, até de 15 em 15 minutos no dia da distribuição enquanto a tens aberta ou com notificações ativas, e de 3 em 3 horas após um dia sem novo passo. Pode notificar-te de «Todos os registos», «Passos importantes» ou «Só a entrega». Para pontos de recolha escolhe «Passos importantes»: «Só a entrega» não avisa quando a encomenda fica pronta para levantar. O Peek não pode contactar a Ozon nem o vendedor por ti.

## Perguntas sobre o rastreio Ozon

### Como é um número de seguimento Ozon?

O Peek associa dois formatos à Ozon, ambos com hífenes: três grupos de algarismos (8 ou 10 algarismos, depois 4 e depois 1, como `48291057-0362-1`) e números começados por `PL-`, como `PL-482910-57362`. Escreve os hífenes: sem eles o Peek não sabe sozinho que o número é da Ozon.

### Porque é que o Peek não reconhece o meu número Ozon?

Provavelmente escreveste-o sem hífenes ou com espaços. Volta a escrevê-lo como `48291057-0362-1` com os teus algarismos, ou carrega em «Alterar» e escolhe Ozon.

### O que significa «Заказ в пункте выдачи»?

A tua encomenda está no ponto de recolha e podes ir buscá-la. Quando a levantares, o rastreio da Ozon mostra `Заказ получен в пункте выдачи.`

### Posso seguir uma encomenda Ozon em português?

Sim. O Peek mostra o estado geral em português (como «Em trânsito») e o histórico nas palavras russas da Ozon; as tabelas acima explicam cada passo.

:::sources
- [Universal Parcel Scraper: Ozon](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/ozon-logistics) – nomes, países (`RU`, `BY`, `KZ`), formatos com hífenes, ligações tracking.ozon.ru e rocket.ozon.ru, página de ajuda `docs.ozon.ru/common/dostavka/`, dados lidos e marcadores descartados (`FantomDelivery` e passos fantasma fronteiriços), etapas russas e correspondência, chamada do estafeta, levantamento em ponto ou correios
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – transportadoras que partilham 13 e 15 algarismos, números Amazon (`PL-` + 10 algarismos), ordem dos serviços de seguimento, transportadoras consultadas nos primeiros 30 dias, passagens indicadas por serviços de seguimento
:::
