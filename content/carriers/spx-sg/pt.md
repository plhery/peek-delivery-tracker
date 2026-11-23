---
title: Seguimento SPX Express Singapura: Shopee e estados
description: Segue uma encomenda SPX Express (Shopee Xpress) em Singapura: códigos SPXSG e SG, significado dos estados, GST na importação e ajuda.
slug: seguimento-spx-express-singapore
published: 2026-11-22
updated: 2026-11-22
---

A SPX Express Singapore, também chamada Shopee Xpress ou Shopee Express, entrega encomendas em Singapura. Transporta pedidos da Shopee, incluindo encomendas de vendedores na China que passam pelo centro de triagem da Shopee em Hong Kong. O seguimento oficial da SPX fica em spx.sg. Para seguir uma encomenda aqui, cola o número na caixa acima. Numa encomenda da Shopee, abre o pedido na Shopee e copia o número de seguimento nas informações de envio.

## Número de seguimento SPX Express Singapura: como é

O Peek conhece dois formatos para a SPX Express Singapore (dígitos inventados):

| Número (inventado) | O que é |
| --- | --- |
| `SPXSG48291736482K` | SPXSG, 11 dígitos e mais um dígito ou letra (17 carateres): o número próprio da SPX. O Peek identifica logo SPX Express Singapore. |
| `SG482917364829K` | SG, 12 dígitos e um dígito ou letra final (15 carateres): o número Shopee associado no seguimento da SPX. O Peek não consegue identificar a SPX só por este formato (ver abaixo). |
| `SPXSG4829173648` | SPXSG e apenas 10 dígitos: o Peek não o reconhece. Verifica se falta um dígito. |
| `RA482917367SG` | Duas letras, nove dígitos e SG no fim: um número da Singapore Post, não da SPX. |

Espaços e minúsculas não importam ao colar um número no Peek.

**O número do pedido não é um número de seguimento.** Quando o código colado tem o formato de um pedido Shopee, o Peek mostra «Parece um número de pedido da Shopee». Copia antes o número de seguimento do pedido. Não o encontras? Vê [como encontrar o teu número de seguimento](guide:find-tracking-number).

## O que significam os estados da SPX Express Singapura

O seguimento da SPX, tal como o Peek o lê, escreve todas as linhas em inglês. Eis as linhas reconhecidas pelo código do Peek, pela ordem aproximada de uma encomenda vinda do estrangeiro:

| A SPX indica | O que significa para ti | O Peek mostra |
| --- | --- | --- |
| `[China]Sender is preparing to ship your parcel` | O vendedor na China está a preparar a encomenda. A SPX ainda não a tem. | «Etiqueta criada» |
| `[China] Parcel has arrived at Hong Kong sorting centre` | Chegou ao centro de triagem da Shopee em Hong Kong. | «Em trânsito» |
| `[China] Parcel has departed from Hong Kong sorting centre` | Saiu desse centro de triagem. | «Em trânsito» |
| `[China] Parcel has been handed over to delivery service provider` | Foi entregue a um operador de carga aérea que a encaminha. | «Em trânsito» |
| `[China] Parcel has cleared export customs` | Passou a alfândega de saída, não a de Singapura. | «Em trânsito» |
| `Parcel has departed from the origin port: Hongkong` | Saiu de Hong Kong. | «Em trânsito» |
| `Parcel has arrived at the destination port Singapore` | Chegou a Singapura: é uma chegada, não uma retenção aduaneira. | «Em trânsito» |
| `Parcel has cleared import customs` | A alfândega de importação de Singapura libertou-a. | «Em trânsito» |
| `Parcel has been picked up by our local logistics partner for further sorting. You will be notified when the parcel is ready for delivery.` | Já está na rede de entrega de Singapura para nova triagem antes da entrega. | «Em trânsito» |
| `[Singapore] Parcel has arrived at sorting facility` | Num centro de triagem em Singapura. | «Em trânsito» |
| `[SPX Express] Parcel is out for delivery` | Está em distribuição. | «Em distribuição» |
| `Parcel has been delivered` | Entregue. | «Entregue» |

**Outros passos.** O código do Peek reconhece mais estados SPX: recolha junto do vendedor ou entrega num ponto de depósito («Aceite pela transportadora»), entrega falhada («Tentativa de entrega») e devolução ao vendedor («Devolvida ao remetente», ou «Problema reportado» se a devolução parar). Mais em [significado dos estados de seguimento](guide:tracking-statuses).

## Entrega SPX Express em Singapura

Depois de `[SPX Express] Parcel is out for delivery`, a linha seguinte deve ser `Parcel has been delivered`. Se a SPX levar a encomenda para um ponto de recolha em vez da tua porta, o Peek mostra «Em distribuição» enquanto segue para lá.

Como o Peek não conseguiu ler o site da SPX Express Singapore, esta página não dá horários de entrega, número de tentativas nem regras de levantamento. Pergunta ao vendedor, ou à Shopee se encomendaste lá. Vê [entregas falhadas](guide:missed-delivery) e [encomendas não levantadas](guide:uncollected-parcel).

## Encomendas do estrangeiro: GST e alfândega em Singapura

Regras da Singapore Customs para compras no estrangeiro vindas por via aérea ou postal:

- **GST no checkout.** Se cada artigo custar 400 S$ ou menos e o vendedor ou marketplace estrangeiro estiver registado na GST, o imposto já está incluído no preço. O vendedor envia os dados do pagamento à transportadora e a GST não volta a ser cobrada na importação. O limite refere-se apenas aos bens, sem envio nem seguro.
- **GST na importação.** Se o vendedor não estiver registado na GST e o valor CIF total do envio (bens, seguro e frete) ultrapassar 400 S$, a GST é cobrada à entrada em Singapura. Até 400 S$ não há GST na importação, exceto álcool e tabaco.
- **Cobrado duas vezes?** Se pagaste GST no checkout e te voltarem a cobrar na importação, paga a GST de importação para receber os artigos e pede depois reembolso ao vendedor com comprovativo.

**Retida na alfândega.** A Immigration & Checkpoints Authority (ICA) pode reter encomendas com artigos sujeitos a impostos, controlados ou proibidos, ou com valor CIF total acima de 400 S$ sem fatura à chegada. A ICA entrega então um recibo de depósito à transportadora; para te informares, pede à transportadora os dados do envio e esse recibo e apresenta-os à autoridade indicada no recibo.

**Burlas.** A Singapore Customs afirma que não pede pagamentos de GST nem dados pessoais por SMS, e-mail ou apps de mensagens. Vê [SMS falsos de entrega](guide:delivery-scam-texts) e [encomendas retidas na alfândega](guide:customs).

## Encomenda SPX sem atualizações, perdida ou devolvida

**Número ainda não encontrado.** Quando a SPX ainda não conhece um número SPXSG, o Peek mostra «À espera de SPX Express Singapore» e continua a verificar. O vendedor pode ainda não ter entregue a encomenda; confirma também o número.

**Parada em `[China]Sender is preparing to ship your parcel`.** O vendedor ainda não enviou a encomenda: pergunta-lhe quando vai expedir.

**Sem atualizações há dias.** É habitual uma encomenda ficar sem novidades em voos longos, na alfândega ou ao fim de semana. Após quatro dias sem registos numa encomenda em «Etiqueta criada», «Aceite pela transportadora» ou «Em trânsito», o Peek assinala-a com «Sem atualizações desde…» e continua a perguntar à SPX. Vê [seguimento que não atualiza](guide:tracking-not-updating) e [seguir encomendas da China](guide:tracking-from-china).

**Entregue mas não chegou.** Procura junto à porta e pergunta em casa e aos vizinhos; depois contacta o vendedor ou a Shopee. Vê [entregue mas não recebida](guide:delivered-not-received).

**Perdida ou devolvida.** Quem contratou a entrega foi o vendedor: fala com ele ou com a Shopee para reembolso ou novo envio. Vê [encomenda perdida](guide:lost-parcel) e [devolvida ao remetente](guide:returned-to-sender).

## Apoio ao cliente e contacto da SPX Express Singapura

Como o Peek não conseguiu ler o site da SPX Express Singapore, esta página não apresenta telefone nem e-mail: o Peek só inclui contactos lidos nas páginas da própria transportadora.

Num pedido Shopee, pede ajuda na Shopee. Noutra loja, fala primeiro com a loja que reservou a entrega. A Singapore Customs não pode ajudar porque não tem acesso ao seguimento das transportadoras e remete para a transportadora.

## Seguir uma encomenda SPX Express Singapura com o Peek

O [Peek](/) segue uma encomenda SPX apenas com o número: sem conta, telefone nem código postal.

- **Um número SPXSG.** O Peek identifica logo SPX Express Singapore («Transportadora detetada») e consulta diretamente o seguimento da SPX. «Alterar» deixa escolher outra transportadora.
- **Um link.** Cola um link do seguimento em spx.sg com um número SPXSG (`/track?`, `/m/track?` ou `/detail/`) e o Peek extrai o número.
- **Um número SG.** O Peek não tem certeza da transportadora só por esta forma. Cola-o na mesma: o Peek segue mais de 4500 transportadoras. Enquanto verifica aparece «A verificar serviços de seguimento…». Para escolher SPX, carrega em «Alterar»: SPX Express Singapore surge em «Corresponde a este número». Ainda assim, usa o número SPXSG se o tiveres, pois o seguimento SPX muitas vezes só encontra encomendas Shopee por esse código.

Adicionada sem transportadora, a encomenda mostra «Ainda sem histórico de seguimento» enquanto o Peek consulta serviços de seguimento. Se um deles indicar apenas SPX Express Singapore com registos datados, o Peek associa-a à SPX.

**O que o Peek mostra.** Estado, cada linha nas palavras da SPX e o local nos registos em armazéns, portos e centros de triagem. A SPX dá o instante exato de cada registo, que o Peek apresenta no teu fuso horário («Horas no teu fuso horário:»). O Peek mantém também alguns passos que a página da SPX esconde, como desalfandegamento na importação e devolução em curso. Omite localização do vendedor, moradas de escritórios e nome de quem recebeu. A SPX não fornece data prevista, peso nem ponto de levantamento, pelo que o Peek não os mostra.

**De Janio para SPX.** Quando o seguimento de uma encomenda Janio indica a SPX com um número SPX Singapore, o cartão mostra «Entrega: SPX Express Singapore» e o Peek também pergunta à SPX. Enquanto a SPX tiver apenas o anúncio sem registo próprio, surge «Anunciada a SPX Express Singapore, ainda não recebida». Assim que a SPX apresenta um registo tão recente quanto o último da Janio, o Peek passa a segui-la lá mantendo o histórico da Janio.

**Verificações e notificações.** O Peek consulta diretamente a SPX até de 10 em 10 minutos, e até de 2 em 2 minutos a partir das 6:00 (hora de Singapura) no dia em que a SPX regista a encomenda em distribuição, enquanto estiver aberta no Peek ou no dia da entrega com as notificações ligadas. Após dois dias sem novidades (12 horas enquanto ainda mostrar «Etiqueta criada» ou «Aceite pela transportadora»), o Peek verifica de hora a hora; após sete dias sem novidades em «Etiqueta criada», de 6 em 6 horas. Para as notificações, podes escolher «Todas as atualizações», «Só as importantes» ou «Só no dia da entrega». Após quatro dias sem registos numa encomenda em «Etiqueta criada», «Aceite pela transportadora» ou «Em trânsito», o Peek mostra «Sem atualizações desde…», explica que «É habitual uma encomenda ficar sem novidades em voos longos, na alfândega ou ao fim de semana. O Peek continua a perguntar a SPX Express Singapore.» e disponibiliza «Pede ajuda a SPX Express Singapore». O Peek não pode contactar a SPX, a Shopee ou o vendedor por ti.

## Perguntas frequentes sobre SPX Express Singapura

### A SPX Express é o mesmo que a Shopee Express?

Sim. O catálogo do Peek também lista SPX Express Singapore como Shopee Xpress Singapore e Shopee Express Singapore, e entrega pedidos Shopee. A SPX tem redes separadas noutros países, como [SPX Express Brasil](carrier:spx-br): um número SPXSG pertence a Singapura.

### Qual é a diferença entre um número SPXSG e um número SG?

O número SPXSG é o número próprio da SPX para a encomenda, permitindo ao Peek identificá-la de imediato. O número SG é o número Shopee associado. Como o seguimento da SPX muitas vezes só encontra uma encomenda Shopee pelo SPXSG, usa esse.

### O que significa «Parcel has been picked up by our local logistics partner»?

A tua encomenda vinda do estrangeiro chegou a Singapura e foi entregue à rede local para triagem. A SPX diz que serás avisado quando ficar pronta para entrega. O Peek mostra «Em trânsito».

### Porque não atualiza o meu seguimento SPX?

Se a última linha for `[China]Sender is preparing to ship your parcel`, o vendedor ainda não a enviou. Mais tarde, as encomendas ficam frequentemente sem novidades em voos, na alfândega ou ao fim de semana. Após quatro dias sem novidades em «Etiqueta criada», «Aceite pela transportadora» ou «Em trânsito», o Peek assinala-a; se continuar parada, pergunta ao vendedor ou à Shopee.

:::sources
- [Singapore Customs: GST on Low-Value Goods](https://www.customs.gov.sg/personal-shipment/buying-from-overseas/gst-on-low-value-goods/) – regra dos 400 S$, GST no checkout ou importação, cobrança dupla, burlas
- [Singapore Customs: Courier / Postal Procedures](https://www.customs.gov.sg/personal-shipment/buying-from-overseas/courier-postal-procedures/) – encomendas retidas pela ICA, perguntar à transportadora pelo seguimento
- [Universal Parcel Scraper: SPX Express Singapore](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/spx-sg) – nomes, formatos de número, links do seguimento, textos e etapas
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – cada registo classificado numa etapa, serviços de seguimento
:::
