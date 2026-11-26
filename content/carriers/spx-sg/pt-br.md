---
title: Rastreamento SPX Express Singapura: Shopee e status
description: Rastreie encomendas SPX Express (Shopee Xpress) em Singapura: códigos SPXSG e SG, status explicados, GST de importação e o que fazer se parar.
slug: spx-express-singapore-tracking
published: 2026-11-26
updated: 2026-11-26
---

A SPX Express Singapore, também chamada de Shopee Xpress ou Shopee Express, entrega encomendas em Singapura. Ela transporta pedidos da Shopee, incluindo pacotes de vendedores na China que passam pelo centro de triagem da Shopee em Hong Kong. O rastreador próprio da SPX fica em spx.sg. Para acompanhar um pacote aqui, cole o número na caixa acima. Em um pedido da Shopee, abra o pedido no app ou site da Shopee e copie o código de rastreamento nas informações de envio.

## Código de rastreamento SPX Express Singapore: como ele se parece

O Peek reconhece dois formatos para a SPX Express Singapore. Com dígitos fictícios:

| Código (fictício) | O que é |
| --- | --- |
| `SPXSG48291736482K` | `SPXSG`, 11 dígitos e mais um dígito ou letra (17 caracteres): o código próprio da SPX para o pacote. O Peek identifica SPX Express Singapore imediatamente. |
| `SG482917364829K` | `SG`, 12 dígitos e um dígito ou letra final (15 caracteres): o código da Shopee que o rastreador da SPX associa ao pacote. O Peek não consegue identificar a SPX apenas por esse formato (veja abaixo). |
| `SPXSG4829173648` | `SPXSG` com apenas 10 dígitos: o Peek não reconhece. Verifique se falta um dígito. |
| `RA482917367SG` | Duas letras, nove dígitos e `SG` no final: código da [Singapore Post](carrier:singapore-post), não da SPX. |

Espaços e letras minúsculas não fazem diferença ao colar o código no Peek.

**O número do pedido não é o código de rastreamento.** Quando o número colado tem o formato de um número de pedido da Shopee, o Peek exibe "Parece um número de pedido da Shopee". Copie o código de rastreamento nos dados de envio do pedido. Não consegue achar? Veja [como encontrar seu código de rastreamento](guide:find-tracking-number).

## Status de rastreamento da SPX Express Singapore explicados

O rastreador da SPX, conforme lido pelo Peek, exibe todas as linhas em inglês. Estas são as mensagens que o código do Peek conhece, aproximadamente na ordem em que uma encomenda do exterior passa por elas:

| A SPX informa | O que significa para você | O Peek mostra |
| --- | --- | --- |
| `[China]Sender is preparing to ship your parcel` | O vendedor na China está preparando o pacote. A SPX ainda não o recebeu. | "Etiqueta criada" |
| `[China] Parcel has arrived at Hong Kong sorting centre` | Chegou ao centro de triagem da Shopee em Hong Kong. | "Em trânsito" |
| `[China] Parcel has departed from Hong Kong sorting centre` | Saiu desse centro de triagem. | "Em trânsito" |
| `[China] Parcel has been handed over to delivery service provider` | Foi entregue a uma transportadora aérea, que segue com o envio. | "Em trânsito" |
| `[China] Parcel has cleared export customs` | Passou pela alfândega de exportação, ainda não pela de Singapura. | "Em trânsito" |
| `Parcel has departed from the origin port: Hongkong` | Partiu de Hong Kong. | "Em trânsito" |
| `Parcel has arrived at the destination port Singapore` | Chegou a Singapura: trata-se de chegada, não de retenção alfandegária. | "Em trânsito" |
| `Parcel has cleared import customs` | A alfândega de importação de Singapura liberou o pacote. | "Em trânsito" |
| `Parcel has been picked up by our local logistics partner for further sorting. You will be notified when the parcel is ready for delivery.` | Já está com a rede de entrega em Singapura para triagem antes da saída para entrega. | "Em trânsito" |
| `[Singapore] Parcel has arrived at sorting facility` | Está em um centro de triagem em Singapura. | "Em trânsito" |
| `[SPX Express] Parcel is out for delivery` | Saiu para entrega. | "Saiu para entrega" |
| `Parcel has been delivered` | Entregue. | "Entregue" |

**Outras etapas.** O código do Peek reconhece mais etapas da SPX: pacote coletado do vendedor ou deixado em ponto de postagem ("Recebida pela transportadora"), falha na entrega ("Tentativa de entrega") e devolução ao vendedor ("Devolvida ao remetente", ou "Precisa de atenção" se o retorno ficar parado). Saiba mais em [o que significam os status de rastreamento](guide:tracking-statuses).

## Entrega SPX Express em Singapura

Depois de `[SPX Express] Parcel is out for delivery`, a próxima linha deve ser `Parcel has been delivered`. Se a SPX levar a encomenda a um ponto de retirada em vez da sua porta, o Peek mostra "Saiu para entrega" enquanto ela está a caminho de lá.

O Peek não conseguiu ler o site da SPX Express Singapore, portanto esta página não traz horários de entrega, número de tentativas nem regras de retirada. Pergunte ao vendedor ou à Shopee se o pedido foi feito lá. Veja [entregas perdidas](guide:missed-delivery) e [encomendas não retiradas](guide:uncollected-parcel).

## Encomendas do exterior: GST e alfândega em Singapura

Regras da Alfândega de Singapura (Singapore Customs) para mercadorias compradas no exterior enviadas por via aérea ou postal:

- **GST no checkout.** Se cada item custar S$400 ou menos e o vendedor ou marketplace no exterior for registrado para GST, o GST já vem incluído no preço. O vendedor envia os dados do pagamento do GST à transportadora, e o imposto não é cobrado novamente na importação. O valor aqui considera apenas a mercadoria, sem frete nem seguro.
- **GST na importação.** Se o vendedor não for registrado para GST e o valor CIF total da remessa (custo, seguro e frete) ultrapassar S$400, o GST é cobrado na entrada em Singapura. Até S$400, não há cobrança de GST na importação, exceto para bebidas alcoólicas ou tabaco.
- **Cobrado duas vezes?** Se você pagou GST no checkout e for cobrado novamente na importação, pague o GST de importação para liberar os bens e depois solicite reembolso ao vendedor apresentando comprovante do pagamento do GST.

**Retida na alfândega.** A Autoridade de Imigração e Postos de Controle (ICA) pode reter uma encomenda expressa contendo itens tributáveis, controlados ou proibidos, ou com valor CIF total acima de S$400 sem fatura na chegada. A ICA entrega à transportadora um recibo de depósito em armazém (*warehouse deposit receipt*): para consultar o pacote, obtenha os detalhes do envio e esse recibo com a transportadora e envie-os à autoridade indicada no recibo.

**Golpes.** A Singapore Customs informa que não pede pagamento de GST nem dados pessoais por SMS, e-mail ou aplicativos de mensagem. Veja [golpes de SMS de entrega](guide:delivery-scam-texts) e [encomendas retidas na alfândega](guide:customs).

## Encomenda SPX sem atualização, perdida ou devolvida

**Código ainda não encontrado.** Quando a SPX ainda não reconhece um número `SPXSG`, o Peek mostra "Aguardando SPX Express Singapore" e continua consultando. O vendedor pode ainda não ter entregue o pacote; confira também o número digitado.

**Parado em `[China]Sender is preparing to ship your parcel`.** O vendedor ainda não despachou a encomenda. Pergunte ao vendedor quando fará o envio.

**Sem atualização há dias.** Encomendas costumam ficar quietas em voos longos, na alfândega ou durante o fim de semana. Quando um pacote em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito" fica quatro dias sem nova linha, o Peek sinaliza a data da última leitura ("Sem atualização de rastreamento desde seg 12 out") e um link "Obter ajuda da SPX Express Singapore", continuando a consultar a SPX. Após três semanas sem novidades, sugere falar com o vendedor. Veja [por que o rastreamento para de atualizar](guide:tracking-not-updating) e [rastrear encomendas da China](guide:tracking-from-china).

**Entregue, mas nada chegou.** Verifique ao redor da porta e pergunte a familiares e vizinhos. Em seguida, fale com o vendedor ou com a Shopee. Veja [entregue mas não recebido](guide:delivered-not-received).

**Perdida ou devolvida.** Quem contratou a entrega foi o vendedor, então trate de reembolso ou reenvio com o vendedor ou a Shopee. Veja [encomendas perdidas](guide:lost-parcel) e [devolvida ao remetente](guide:returned-to-sender).

## Atendimento ao cliente e contato da SPX Express Singapore

O Peek não conseguiu ler o site da SPX Express Singapore, portanto esta página não lista telefone nem e-mail: o Peek divulga apenas contatos confirmados nas páginas oficiais da própria transportadora.

Para um pedido da Shopee, fale pelo atendimento da Shopee. Para pedidos de outra loja, procure primeiro a loja que contratou o frete. A Singapore Customs não consegue ajudar: ela não tem acesso ao rastreamento das transportadoras e orienta contatar a transportadora.

## Rastreie uma encomenda SPX Express Singapore com o Peek

O [Peek](/) acompanha uma encomenda SPX apenas com o código: sem conta, telefone nem código postal.

- **Código SPXSG.** O Peek identifica SPX Express Singapore imediatamente ("Transportadora detectada") e consulta o rastreador da SPX. "Alterar" permite escolher outra transportadora.
- **Link.** Cole um link do rastreador da SPX em spx.sg contendo um código SPXSG (`/track?`, `/m/track?` ou `/detail/`), e o Peek extrai o número automaticamente.
- **Código SG.** O Peek não tem certeza da transportadora apenas por esse formato. Cole mesmo assim: o Peek acompanha mais de 4.500 transportadoras. Enquanto verifica, exibe "Consultando serviços de rastreamento…". Para escolher a SPX manualmente, toque em "Alterar": SPX Express Singapore aparece em "Compatível com este código". Melhor ainda, use o código `SPXSG` se tiver: o rastreador da SPX frequentemente encontra encomendas da Shopee apenas por esse número.

Adicionada sem transportadora, a encomenda mostra "Ainda sem histórico de rastreamento" enquanto o Peek consulta serviços de rastreamento. Se algum indicar apenas SPX Express Singapore, com etapas datadas, o Peek vincula o pacote à SPX.

**O que o Peek mostra.** O status, cada linha nas palavras da SPX e o local das leituras em armazéns, portos e centros de triagem. A SPX fornece o instante exato de cada leitura, portanto o Peek exibe no fuso horário do seu dispositivo. O Peek também preserva etapas que a página própria da SPX oculta, como liberação de importação e devolução em andamento. Ele omite o local do vendedor, endereços de escritórios e o nome de quem recebeu. A SPX não informa data prevista de entrega, peso nem ponto de retirada, logo o Peek não exibe esses campos.

**De Janio para SPX.** Quando o rastreamento de um pacote Janio indica a SPX com um código SPX de Singapura, o cartão mostra "Entrega por SPX Express Singapore" e o Peek consulta a SPX também. Enquanto a SPX tiver apenas o anúncio da encomenda, sem leitura própria, o cartão mostra "Anunciada a SPX Express Singapore, ainda não recebida". Assim que a SPX tiver uma leitura tão recente quanto a última da Janio, o Peek acompanha por lá e mantém o histórico da Janio.

Se o rastreador da SPX ficar indisponível, o Peek mostra o que os serviços de rastreamento informam, nas palavras deles.

**Consultas e alertas.** Enquanto você mantém a encomenda aberta no Peek, ou no dia da entrega com notificações ativadas, o Peek consulta a SPX diretamente até a cada 10 minutos, e até a cada 2 minutos a partir das 6h (horário de Singapura) no dia em que a SPX registrar saída para entrega. Após dois dias sem novidades (12 horas enquanto ainda estiver em "Etiqueta criada" ou "Recebida pela transportadora"), consulta de hora em hora; após sete dias sem novidades em "Etiqueta criada", a cada 6 horas. Alertas: "Cada leitura", "Etapas importantes" ou "Apenas entrega". O Peek não pode entrar em contato com a SPX, a Shopee nem o vendedor em seu nome.

## Perguntas sobre o rastreamento da SPX Express Singapore

### A SPX Express é a mesma coisa que a Shopee Express?

Sim. O catálogo do Peek também lista SPX Express Singapore como Shopee Xpress Singapore e Shopee Express Singapore, e ela entrega pedidos da Shopee. A SPX possui redes separadas em outros países, como [SPX Express Brazil](carrier:spx-br): um código `SPXSG` pertence à operação de Singapura.

### Qual é a diferença entre um código SPXSG e um código SG?

O código `SPXSG` é o número próprio da SPX para o pacote: o Peek identifica a SPX imediatamente a partir dele. O código `SG` é o número da Shopee vinculado a ele. O rastreador da SPX muitas vezes encontra o pedido da Shopee apenas pelo `SPXSG`, então prefira esse número.

### O que significa "Parcel has been picked up by our local logistics partner"?

Sua encomenda vinda do exterior já está em Singapura e foi entregue à rede local de entregas para triagem. A SPX informa que você será notificado quando estiver pronta para entrega. O Peek mostra "Em trânsito".

### Por que meu rastreamento da SPX não está atualizando?

Se a última linha for `[China]Sender is preparing to ship your parcel`, o vendedor ainda não despachou o pacote. Mais adiante, encomendas costumam ficar quietas em voos, na alfândega ou no fim de semana. Após quatro dias sem novidades em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito", o Peek sinaliza o pacote. Se continuar parado, fale com o vendedor ou com a Shopee.

:::sources
- [Singapore Customs: GST on Low-Value Goods](https://www.customs.gov.sg/personal-shipment/buying-from-overseas/gst-on-low-value-goods/) – regra dos S$400, GST no checkout ou na importação, cobrança duplicada e alertas de golpes
- [Singapore Customs: Courier / Postal Procedures](https://www.customs.gov.sg/personal-shipment/buying-from-overseas/courier-postal-procedures/) – encomendas retidas pela ICA e orientação para consultar a transportadora
- [Universal Parcel Scraper: SPX Express Singapore](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/spx-sg) – nomes, formatos de número, links do rastreador, textos e etapas correspondentes
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – classificação de cada leitura por etapa e serviços de rastreamento
:::
