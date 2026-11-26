---
title: Rastreamento Ozon: códigos de pedido e status em russo
description: Rastreie pedidos da Ozon: formato dos códigos com hifens e PL, tradução das etapas em russo do rastreador da Ozon e como o Peek acompanha o envio.
slug: rastreamento-ozon
published: 2026-11-26
updated: 2026-11-26
---

O serviço de entregas da Ozon transporta os pedidos repassados por vendedores, levando-os até sua porta ou a um ponto de retirada. O catálogo do Peek lista a empresa na Rússia, Belarus e Cazaquistão, também pelos nomes Ozon Rocket e Ozon Доставка. Cole seu código na caixa acima com os hifens. O próprio rastreador da Ozon fica em tracking.ozon.ru, e o Peek o lê para você conforme explicado abaixo.

## Código de rastreamento Ozon: como ele é

O Peek reconhece dois formatos da Ozon, ambos escritos com hifens. Os hifens fazem diferença: sem eles, os dígitos parecem códigos de outras transportadoras. Com dígitos inventados:

| Código (inventado) | O que é |
| --- | --- |
| `48291057-0362-1` | 8 dígitos, 4 dígitos e 1 dígito ligados por hifens. O Peek identifica a Ozon na hora. |
| `4829105736-2841-2` | O mesmo padrão começando com 10 dígitos. O Peek também identifica a Ozon. |
| `4829105703621` | O primeiro código sem hifens, ou com espaços no lugar. DPD France, Ukrposhta e outras usam 13 dígitos, então só os números não indicam Ozon. Adicionando da Rússia, Belarus ou Cazaquistão, o Peek consulta primeiro a Ozon; de outros países, consulta a Ozon por último, se outras transportadoras e serviços não acharem. Digite os hifens novamente ou escolha Ozon manualmente (abaixo). O mesmo vale para 15 dígitos, usados por FedEx e outras. |
| `PL-482910-57362` | PL, 6 dígitos e mais letras ou dígitos com hifens. O Peek identifica a Ozon. |
| `PL-482910-5736` | PL com exatamente 10 dígitos coincide com o formato da Amazon. O Peek trata como código da Amazon e não permite escolher Ozon, portanto não consegue acompanhá-lo na Ozon. |

Letras minúsculas não fazem diferença: `pl-` funciona igual a `PL-`. Não encontra seu código? Veja [como encontrar seu código de rastreamento](guide:find-tracking-number) e [formatos de código de rastreamento](guide:tracking-number-formats).

## Status de rastreamento da Ozon explicados

O código do Peek lista as etapas em russo exibidas pelo rastreador da Ozon. O Peek lê esse rastreador e mantém os textos originais em russo, exceto `Заказ в работе`, `Заказ на пути к границе страны назначения` e `Заказ прибывает на приграничный пункт`, que ele omite (veja "Rastrear um pedido Ozon com o Peek").

**No seu país** (em ordem aproximada):

| A Ozon mostra | O que significa para você |
| --- | --- |
| `Передается в доставку` | O vendedor embalou o pedido e está repassando para entrega. A Ozon pode ainda não estar com ele. |
| `Заказ принят перевозчиком.` | A transportadora recebeu o pedido: está a caminho. |
| `Заказ отправили на сортировочный терминал`, `Заказ отправлен на сортировочный терминал.` | Enviado a um centro de triagem. |
| `Заказ покинул сортировочный терминал.` | Saiu do centro de triagem. |
| `Заказ ожидает отправки в город получателя.` | Aguardando despacho para a sua cidade. |
| `Заказ везут в город получателя` | A caminho da sua cidade. |
| `Заказ везут` | Em deslocamento. |
| `Заказ передали в курьерскую доставку` | Repassado ao entregador, que liga para combinar o horário. |
| `Заказ в пункте выдачи` | Chegou ao ponto de retirada; pode ir buscar. |
| `Заказ получен в пункте выдачи.` | Você retirou no ponto de coleta ou agência postal: entregue. |
| `Заказ успешно доставлен получателю.` | Entregue (usado quando não foi para ponto de retirada nem agência, como na entrega em domicílio). |
| `Заказ отменён` | Pedido cancelado. |
| `Заказ в работе` | Pedido em andamento: exibido no rastreador da Ozon como marcador genérico, não como etapa do trajeto. |

**Vindo de outro país:** envios internacionais também recebem etapas aduaneiras, aproximadamente nesta ordem:

| A Ozon mostra | O que significa para você |
| --- | --- |
| `Заказ везут на таможню в стране отправления` | A caminho da alfândega no país de origem. |
| `Заказ привезли на таможню для экспортного таможенного оформления` | Na alfândega de exportação daquele país. |
| `Заказ покинул зону экспортного таможенного оформления` | Saiu da área de desembaraço de exportação. |
| `Заказ на пути к границе страны назначения` | A caminho da fronteira do país de destino. |
| `Заказ прибывает на приграничный пункт` | Chegando ao posto de fronteira. |
| `Заказ привезли в страну назначения` | Chegou ao país de destino. |
| `Заказ передан на импортное таможенное оформление.` | Encaminhado à alfândega de importação no país de destino. |
| `Заказ проходит импортное таможенное оформление` | Passando pelo desembaraço de importação. |
| `Заказ выпущен импортной таможней.` | Liberado pela alfândega de importação: de volta ao trajeto. |
| `Импортная таможня задержала заказ` | Retido pela alfândega de importação para conferência adicional. |

## Como a Ozon entrega: domicílio ou ponto de retirada

As etapas informam qual modalidade se aplica ao seu pedido.

**Na sua porta.** Após `Заказ передали в курьерскую доставку`, um entregador leva o pedido e liga antes para combinar o horário. A etapa final é `Заказ успешно доставлен получателю.`

**Em um ponto de retirada.** Aguarde `Заказ в пункте выдачи` e vá retirar. Depois de retirado, aparece `Заказ получен в пункте выдачи.` (o mesmo texto vale para retirada em agência postal).

Como o Peek não conseguiu ler o site institucional da Ozon, esta página não informa prazos de entrega, horários de funcionamento, períodos de guarda nem regras de novas tentativas: pergunte à própria Ozon. Veja também [encomenda não retirada](guide:uncollected-parcel) e [entregas não realizadas](guide:missed-delivery).

**Do exterior.** As etapas aduaneiras acima mostram onde está um pedido internacional. Esta página não traz regras nem taxas aduaneiras porque o Peek não pôde verificá-las em fonte oficial. Veja [encomendas retidas na alfândega](guide:customs).

## Pedido Ozon que não chega ou rastreamento parado

**Nada ainda ou apenas `Передается в доставку`.** O vendedor pode ainda não ter entregue o pedido, ou o rastreador da Ozon ainda não o cadastrou. O Peek continua verificando. Se você adicionou sem os hifens, selecione Ozon em "Trocar transportadora".

**O Peek está atrás do rastreador da Ozon.** Quando o rastreador da Ozon não responde ao Peek, um serviço de rastreamento assume temporariamente e pode atrasar em relação à Ozon. Compare em tracking.ozon.ru antes de se preocupar.

**Dias sem nova etapa.** Se o evento mais recente tiver quatro ou mais dias em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito", o Peek destaca a data ("Sem atualizações desde seg 12 out"). Veja mais em [por que o rastreamento para de atualizar](guide:tracking-not-updating).

**`Импортная таможня задержала заказ`.** A alfândega está verificando o pedido. Aguarde `Заказ выпущен импортной таможней.` Se nada mudar por muito tempo, pergunte à Ozon.

**`Заказ отменён`.** O pedido foi cancelado e não chegará. Solicite reembolso à Ozon ou à loja onde comprou.

**Entregue, mas nada chegou.** Confira se alguém retirou por você e contate a Ozon. Veja mais em [encomenda entregue mas não recebida](guide:delivered-not-received).

**Extravio.** Fale com a Ozon ou com a loja onde comprou. Veja [encomendas extraviadas](guide:lost-parcel).

## Atendimento e contato da Ozon

Como o Peek não pôde ler o site da Ozon, esta página não publica telefone nem e-mail: o Peek lista apenas contatos lidos nas páginas da própria transportadora. O rastreador da Ozon fica em tracking.ozon.ru.

Para compras feitas na Ozon, fale com a Ozon. Para pedidos de outra loja, procure primeiro essa loja, que contratou a entrega.

## Rastrear um pedido Ozon com o Peek

O [Peek](/) acompanha códigos Ozon apenas com o número: sem conta, telefone nem código extra.

- **Com hifens.** O Peek identifica a Ozon na hora ("Transportadora detectada") e salva a encomenda nela.
- **Sem hifens.** Só os dígitos não bastam para identificar a empresa. Cole mesmo assim: o Peek acompanha mais de 4.500 transportadoras. Enquanto consulta as empresas com esse formato e os serviços de rastreamento, exibe "Consultando serviços de rastreamento…". Adicionando da Rússia, Belarus ou Cazaquistão, a Ozon é consultada primeiro; de outros países, por último, se nenhuma outra nem os serviços encontrarem. Se os serviços apontarem Ozon com etapas datadas, o Peek a seleciona sozinho; senão, toque em "Trocar" e busque Ozon (ou "Trocar transportadora" em pedidos já adicionados).
- **Por link.** Cole um link de tracking.ozon.ru com o código (`track=`) ou de rocket.ozon.ru (`SearchId=`), e o Peek acompanha esse código pela Ozon.

**De onde vêm as etapas.** O Peek lê o próprio rastreador da Ozon e exibe cada etapa no russo original (conforme as tabelas acima), com horário e datas previstas informadas pela Ozon. Ele preserva o fuso horário fornecido pela Ozon, lê horários sem fuso no horário de Moscou e exibe tudo no fuso do seu aparelho; em peektracker.com e nos aplicativos iPhone e Android, o histórico termina com "Horários no seu fuso horário:" seguido do nome dele. A Ozon não fornece localidade das etapas, então não há mapa. Até o rastreador da Ozon reconhecer o código, aparece "Aguardando Ozon" (ou "Ainda sem histórico de rastreamento" para códigos sem transportadora). Quando o rastreador da Ozon não responde, o Peek consulta ParcelsApp, Ship24 e 17TRACK nessa ordem e mostra o histórico do primeiro que encontrar, no texto dele (que pode atrasar frente a tracking.ozon.ru). A consulta do Peek à Ozon não aceita códigos `PL-`: aparece "Confira o código de rastreamento" até que um serviço de rastreamento localize o pedido.

**Link na página da encomenda.** "Abrir o site de Ozon" abre o código no rastreador da Ozon. Se um serviço de rastreamento respondeu, o link abre esse serviço, como "Abrir o site de ParcelsApp".

**Encomenda de outra transportadora.** O Peek armazena o código da Ozon sem hifens. Enquanto o rastreador da Ozon não retorna o envio, nos primeiros 30 dias — antes dos serviços de rastreamento — o Peek também consulta algumas empresas com a mesma quantidade de dígitos (como Ukrposhta e Delhivery para 13, ou Hermes Alemanha e BRT para 15). Se uma delas mostrar movimentação, o Peek transfere o envio e exibe "Transportadora trocada automaticamente (antes: Ozon)".

**Entrega por outra transportadora.** Se um serviço de rastreamento indicar outra empresa com o código dela e o Peek ler o próprio rastreador dela, ele também a consulta e passa a acompanhá-la quando surgir leitura ao menos tão recente quanto a última do serviço.

**Verificações e alertas.** Enquanto você mantém a encomenda aberta, ou no dia da entrega com alertas ligados, se o rastreador da Ozon responder, o Peek verifica até a cada 10 minutos; nos demais momentos, a cada hora. Enquanto um serviço de rastreamento acompanha o envio, consulta a cada hora, a cada 3 horas após um dia sem novidades e até a cada 15 minutos no dia em que sai para entrega enquanto aberta ou com alertas ligados. Pode notificar "Cada movimentação", "Etapas importantes" ou "Só a entrega". Para ponto de retirada, escolha "Etapas importantes", pois "Só a entrega" não avisa quando o pedido chega para retirada. O Peek não pode contatar a Ozon nem o vendedor por você.

## Perguntas frequentes sobre o rastreamento Ozon

### Como é um código de rastreamento da Ozon?

O Peek associa dois formatos com hifens à Ozon: dígitos em três blocos (8 ou 10 dígitos, depois 4 e depois 1, como `48291057-0362-1`) e códigos iniciados por `PL-` (como `PL-482910-57362`). Digite os hifens: sem eles, o Peek não identifica a Ozon pelo formato.

### Por que o Peek não reconheceu meu código da Ozon?

Provavelmente você digitou sem os hifens ou com espaços. Digite novamente como `48291057-0362-1` com os seus dígitos, ou toque em "Trocar" e escolha Ozon.

### O que significa "Заказ в пункте выдачи"?

Seu pedido chegou ao ponto de retirada e já pode ser retirado. Depois da retirada, o rastreador da Ozon mostra `Заказ получен в пункте выдачи.`

### Posso acompanhar um pedido da Ozon sem saber russo?

Sim. O Peek exibe a etapa geral no seu idioma, como "Em trânsito", e mantém o texto original em russo da Ozon; as tabelas acima explicam cada etapa.

:::sources
- [Universal Parcel Scraper: Ozon](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/ozon-logistics) – nomes, países, os dois formatos com hifens, links tracking.ozon.ru e rocket.ozon.ru, o que o Peek lê e omite de tracking.ozon.ru, etapas em russo e significados, ligação do entregador e retirada em ponto ou agência postal
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – transportadoras que compartilham 13 e 15 dígitos, códigos da Amazon, ordem dos serviços de rastreamento, empresas consultadas nos primeiros 30 dias e transferências indicadas por serviços de rastreamento
:::
