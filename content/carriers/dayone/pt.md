---
title: Seguimento DayOne: números DOT, estados e quem entrega
description: Acompanha uma encomenda DayOne da China: formato de um número DOT, significado dos estados no DayOneTrack, quem entrega e a quem perguntar em atrasos.
slug: seguimento-dayone
published: 2026-11-23
updated: 2026-11-23
---

A DayOne é o armazém que expede da China as encomendas online de uma loja. Não é uma transportadora: descreve-se como «the order fulfillment warehouse, not a courier delivery service provider». Outras empresas transportam a encomenda e o seguimento da DayOne, [DayOneTrack](https://www.dayonetrack.com/), em inglês e chinês, mostra os respetivos registos. Pesquisa aí o teu número DOT ou cola-o na caixa acima. A loja fornece-te esse número junto com os dados de envio do pedido.

## Número de seguimento DayOne: como é

A DayOne não publica um formato oficial. Este é o formato que o Peek reconhece como DayOne, com dígitos inventados:

| Número | O que é |
| --- | --- |
| `DOT0482917364058291` | DOT e 16 dígitos: um número DayOne. |
| `DOT1482917364058291` | DOT1 e mais 15 dígitos: também da DayOne. O site da DayOne consulta estes números noutro sistema. |
| `DOT048291736405829` | Apenas 15 dígitos: não é formato DayOne e o Peek não identifica a DayOne. Verifica se falta um dígito. |

No Peek, minúsculas, espaços e hífenes não importam quando colas o número sozinho. Numa mensagem inteira, mantém DOT colado aos dígitos: com um espaço logo após DOT, o Peek pode ler só os dígitos e indicar outra transportadora.

**Onde encontrar.** No e-mail de envio da loja ou na página da encomenda. Se não encontrares, vê [como encontrar o teu número de seguimento](guide:find-tracking-number).

**No seguimento da DayOne.** Podes pesquisar até 10 números de cada vez, um por linha. O site só procura números com DOT0 ou DOT1: qualquer outro código, como o do correio local, devolve `Not found`. Ao lado do código, um resultado pode mostrar um `Reference Number` (a referência da loja) e o país de destino da encomenda.

## Estados de seguimento DayOne explicados

O site da DayOne atribui um estado global a cada encomenda, em inglês e chinês, e lista os registos em `Package Track`, do mais recente para o mais antigo:

| Estado DayOne | O que significa para ti | O Peek mostra |
| --- | --- | --- |
| `Not found` (查询不到) | A DayOne ainda não tem dados para este número. Acrescenta: «The order information has not been updated yet». | «À espera de DayOne» |
| `Info received` (收到信息) | Os dados de envio entraram. A encomenda pode ainda não ter saído do armazém. | «Etiqueta criada» |
| `In transit` (运输途中) | A caminho. | «Em trânsito» |
| `Pick up` (到达待取) | Chegou e aguarda levantamento. | «Pronta para levantamento» |
| `Out for Delivery` (派送途中) | Em distribuição. | «Em distribuição» |
| `Undelivered` (投递失败) | Uma tentativa de entrega falhou. | «Tentativa de entrega» |
| `Delivered` (成功签收) | Entregue. | «Entregue» |
| `Alert` (可能异常) | Pode existir um problema. | «Problema reportado» |
| `Expired` (运输过久) | Em chinês, «em trânsito há tempo demais»: a encomenda viaja há muito tempo. | o estado do registo mais recente |

O Peek orienta-se pelo registo mais recente que entende. Só usa o estado global da DayOne quando não compreende nenhum dos registos.

Cada registo tem hora, local e as palavras da empresa com a encomenda: Yanwen na China e depois o correio do teu país. Registos habituais, pela ordem normal do percurso:

| A DayOne indica | O que significa para ti | O Peek mostra |
| --- | --- | --- |
| `Order processed by shipper` | A Yanwen tem os dados da encomenda. Ainda ninguém registou o volume. | «Etiqueta criada» |
| `Yanwen Pickup Scan`, `Processing information input` | A Yanwen recebeu a encomenda na China e está a tratá-la. | «Aceite pela transportadora» |
| `Yanwen facility - Outbound`, `Arrived at domestic terminal station` | Saiu do centro da Yanwen e chegou a um terminal na China antes da alfândega de exportação. | «Em trânsito» |
| `Port of departure - Received by carrier`, `International shipment release - Export`, `Port of departure - Departure` | No porto de partida, passou pela alfândega de exportação chinesa (não a tua) e seguiu de avião. | «Em trânsito» |
| `Port of destination - Arrival`, `ARRIVED AT DESTINATION AIRPORT` | Aterrou no teu país ou perto dele. A alfândega de importação ainda vai acontecer. | «Em trânsito» |
| `CLEARED BY CUSTOMS`, `COLLECTED FROM AIRPORT TERMINAL`, `ARRIVED AT FACILITY IN DESTINATION COUNTRY` | A alfândega do teu país libertou-a e foi levada do aeroporto para uma instalação postal. | «Em trânsito» |
| `RECEIVED BY AUSTRALIA POST FOR TRANSPORTATION TO PROCESSING FACILITY` | Na Austrália: entregue à Australia Post. | «Em trânsito» |
| `ITEM PROCESSED AT POST FACILITY`, `PROCESSED THROUGH AUSTRALIA POST FACILITY`, `TRANSFERRED` | Triada pelo correio e encaminhada. | «Em trânsito» |
| `WITH AUSTRALIA POST FOR DELIVERY TODAY` | Em distribuição hoje. | «Em distribuição» |
| `DELIVERED WITH SAFE DROP` | Entregue e deixada num local seguro na morada. | «Entregue» |

`SHIPPING INFORMATION RECEIVED`, `SHIPPING INFORMATION RECEIVED BY AUSTRALIA POST` e `SHIPPING INFORMATION APPROVED BY AUSTRALIA POST` significam que o correio tem os dados da encomenda. Podem aparecer após registos mais recentes: não é um recuo, e o Peek mantém o progresso posterior.

Se o único registo for `Shipment has begun. In transit to destination city`, a DayOne entregou a encomenda à [YunExpress](carrier:yunexpress), e o resto do histórico está no site da YunExpress sob um número YunExpress (`YT` e 16 dígitos). `Clearence processing completed - Export` é o desalfandegamento de exportação da YunExpress, com a ortografia dela.

## Quem entrega as encomendas DayOne

A DayOne não diz quem entrega no teu país. Os seus registos mostram quem tem o volume: Yanwen na China e depois o correio de onde vives, como a [Australia Post](carrier:australia-post) para envios para a Austrália.

A DayOne não publica regras sobre tentativas de entrega, avisos nem pontos de levantamento: são da empresa que entrega. Após `Pick up` ou `Undelivered`, procura um aviso ou mensagem dessa empresa. Vê [entregas falhadas](guide:missed-delivery) e [encomendas não levantadas](guide:uncollected-parcel).

**Alfândega na UE.** Bens comprados online fora da UE pagam um direito aduaneiro de 3 € por artigo em encomendas até 150 € desde 1 de julho de 2026, e uma taxa de tratamento da União de 2 € por artigo, qualquer que seja o valor, desde 1 de novembro de 2026. Um artigo são os bens com a mesma classificação pautal, descrição e, quando exigido, origem. O site da DayOne não diz nada sobre direitos, taxas ou quem os cobra. Noutros países aplicam-se as regras locais: vê [encomendas retidas na alfândega](guide:customs).

## Encomenda DayOne sem atualizar, perdida ou devolvida

A DayOne remete-te para a loja. O seguimento indica que para questões logísticas, perdas, reembolsos ou reenvios deves contactar o apoio ao cliente da loja onde compraste.

**`Not found`.** A DayOne ainda não tem dados para esse número. Confirma o formato: DOT e 16 dígitos. Se estiver certo, o pedido pode ainda não ter sido enviado. O Peek mostra «À espera de DayOne» e continua a verificar. Se dois dias depois a DayOne continuar sem reconhecer o número e ninguém mais responder, o Peek mostra «Ao fim de dois dias, a transportadora ainda não reconhece este número. Verifica-o, escolhe outra transportadora ou elimina a encomenda.» e passa a verificar diariamente, e semanalmente após uma semana desde que a adicionaste. Se após alguns dias nada aparecer, pergunta à loja.

**Apenas `Info received` ou `Order processed by shipper`.** Ainda ninguém registou o volume. Se permanecer assim durante vários dias, pergunta à loja quando saiu.

**Dias sem novo registo.** As encomendas costumam ficar sem novidades em voos longos e na alfândega. Se uma encomenda ainda em «Etiqueta criada», «Aceite pela transportadora» ou «Em trânsito» passar quatro dias sem novo registo, o Peek assinala-a com «Sem atualizações há quatro dias» (ou «Sem atualizações desde …»), explica: «É habitual uma encomenda ficar sem novidades em voos longos, na alfândega ou ao fim de semana. O Peek continua a perguntar a DayOne.» e mostra «Pede ajuda a DayOne». Após três semanas sem novos registos, o Peek mostra «Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, contacta o vendedor. O Peek continua a perguntar a DayOne.» Vê [porque o seguimento deixa de atualizar](guide:tracking-not-updating) e [seguir encomendas da China](guide:tracking-from-china).

**`Alert` ou `Expired`.** Lê os registos mais recentes e pede ajuda à loja indicando o número DOT.

**Perdida, danificada ou devolvida.** A DayOne não publica processo de reclamação para destinatários. Pede reembolso ou reenvio à loja. Vê [encomendas perdidas](guide:lost-parcel) e [devolvida ao remetente](guide:returned-to-sender).

**Entregue mas não chegou.** `DELIVERED WITH SAFE DROP` significa que foi deixada num local seguro na morada. Procura à volta de casa, pergunta aos vizinhos e avisa a loja. Vê [entregue mas não recebida](guide:delivered-not-received).

## Contacto DayOne

Segundo o [seguimento da DayOne](https://www.dayonetrack.com/):

- **Sobre a tua encomenda:** a loja onde fizeste o pedido, conforme pede a DayOne.
- **E-mail comercial:** info@dayone-shipping.com, indicado pela DayOne para parcerias e contactos comerciais que não sejam dúvidas de encomendas.

O site da DayOne não indica telefone, chat, formulário de contacto nem morada. Diz guardar apenas números de carta de porte e nenhuns dados de encomenda.

## Seguir uma encomenda DayOne com o Peek

O [Peek](/) identifica logo a DayOne num número DOT com 16 dígitos («Transportadora detetada»). Ligações de `dayonetrack.com` com `trackNo=` também funcionam. Para qualquer outro número, cola-o na mesma: o Peek acompanha mais de 4500 transportadoras. Quando o formato corresponde a várias transportadoras, o Peek mostra «A verificar serviços de seguimento…» enquanto consulta. Se o Peek não indicar DayOne para o teu número, toca em «Alterar» e escolhe DayOne em «Todas as transportadoras» (pesquisar «Day One» também a encontra), ou usa «Alterar transportadora» numa encomenda já adicionada.

Para um número `DOT0`, o Peek lê diretamente o site da DayOne: estado, cada registo com o local, país de destino e hora de entrega. Para um número `DOT1`, consulta serviços de seguimento.

Quando a DayOne fornece o fuso horário de um registo, o Peek utiliza-o. Sem fuso, calcula a hora a partir do local ou dos registos vizinhos (a do teu país assim que surge um registo de chegada). Mostra cada hora no fuso do teu dispositivo. Omite códigos postais, o nome de quem recebeu ou assinou a encomenda entregue e o `Reference Number` da loja. A DayOne não dá data prevista de entrega nem peso.

O site da DayOne não mostra o número da empresa que entrega, mas a resposta técnica inclui-o por vezes. O Peek consulta então também essa empresa: o correio do teu país para um número postal (duas letras, nove dígitos, duas letras) como Royal Mail, USPS, Canada Post, An Post ou Australia Post, ou a única transportadora indicada pelo formato, como a YunExpress para números `YT`. Assim que essa transportadora mostrar um registo pelo menos tão recente como o último da DayOne, o Peek acompanha a encomenda lá, mostra esse número junto ao DOT e preserva o histórico da DayOne.

Para um número `DOT0`, enquanto manténs a encomenda aberta no Peek ou no dia da entrega com as notificações ativadas, o Peek verifica até de 10 em 10 minutos, e a partir das 6:00 (hora local) do dia em que a DayOne regista a encomenda em distribuição até de 2 em 2 minutos enquanto o site próprio da DayOne responder; após dois dias sem novidades (12 horas enquanto continuar em «Etiqueta criada» ou «Aceite pela transportadora»), verifica de hora em hora, e se ficar sete dias em «Etiqueta criada», de 6 em 6 horas. Para um número `DOT1` (via serviços de seguimento), o Peek verifica de hora em hora, de 3 em 3 horas após um dia sem novos registos, de 6 em 6 horas após sete dias em «Etiqueta criada», e até de 15 em 15 minutos no dia em que a encomenda está em distribuição, enquanto manténs a encomenda aberta no Peek ou as notificações ativadas. Pode avisar-te de «Todos os registos», «Passos importantes» ou «Só a entrega». O Peek não pode contactar a DayOne nem a loja por ti.

## Perguntas sobre o seguimento DayOne

### A DayOne é uma transportadora?

Não. A DayOne afirma ser «the order fulfillment warehouse, not a courier delivery service provider». Outras empresas fazem o transporte: nos registos da DayOne, a Yanwen na China e depois o correio do teu país.

### Onde posso acompanhar um número DOT?

No seguimento da DayOne, [dayonetrack.com](https://www.dayonetrack.com/), em inglês ou chinês, até 10 números de cada vez. Ou colando-o no Peek.

### Porque o seguimento da DayOne diz Not found?

A DayOne ainda não tem nada registado com esse número. Confirma se escreveste DOT e os 16 dígitos. Se o número estiver certo, espera um ou dois dias e pergunta à loja se a encomenda já saiu.

### Porque as horas no site da DayOne aparecem fora de ordem?

Cada registo mostra a hora local de onde foi feito, sem fuso horário, e o site ordena os registos por essas horas. No inverno boreal, Pequim está 8 horas à frente de Londres e 13 de Nova Iorque, pelo que um registo no teu país pode parecer mais antigo do que o último na China. Sydney está 3 horas à frente de Pequim no verão austral, fazendo os registos parecerem mais recentes. O Peek mostra cada hora no fuso horário do teu dispositivo.

:::sources
- [DayOne: Dayone Tracking](https://www.dayonetrack.com/) – armazém, não transportadora; pós-venda na loja; política de privacidade; e-mail comercial; inglês e chinês; 10 números de cada vez; consultas DOT0 e DOT1; estados; campos de resultado; horas
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – direito de 3 € desde 1 de julho de 2026, taxa de tratamento da União de 2 € desde 1 de novembro de 2026
- [European Commission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 € por artigo qualquer que seja o valor, o que é um artigo
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formato do número, ligações e consultas, textos e códigos dos registos, fusos horários, número da transportadora final, passagem de testemunho, o que o Peek lê
:::
