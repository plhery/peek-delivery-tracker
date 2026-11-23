---
title: Seguimento An Post: estados, alfândega e contactos
description: Segue uma encomenda An Post com código IE: estados em inglês explicados, taxas aduaneiras, levantamento nos correios e como contactar a An Post.
slug: seguimento-an-post
published: 2026-11-23
updated: 2026-11-23
---

A An Post é o serviço postal nacional da Irlanda. Cola o número de seguimento na caixa acima ou consulta-o na [página de seguimento da An Post](https://www.anpost.com/Post-Parcels/Track/Search). O número é fornecido pelo remetente, geralmente no e-mail de envio da loja.

## Formato do número de seguimento An Post

Os números próprios da An Post seguem o formato postal internacional: duas letras, nove dígitos e `IE`. O último dos nove dígitos é um dígito de controlo, pelo que um único erro de digitação invalida o número.

| Número (inventado) | O que é |
| --- | --- |
| `RR482917367IE` | Envio próprio da An Post terminado em `IE`. O Peek aceita também espaços ou minúsculas: `RR 482 917 367 IE`. |
| `CP482917367IE` | O mesmo formato com outras letras iniciais (`CP`, `CE`, etc.). |
| `RR482917367GB` | Uma encomenda vinda do estrangeiro mantém o número original. Este termina em `GB`, pelo que pertence à [Royal Mail](carrier:royal-mail). |

O seguimento da An Post também acompanha envios remetidos para a Irlanda por outros correios sob o código estrangeiro. Perdeste o código? Vê [como encontrar um número de seguimento](guide:find-tracking-number).

## Estados de seguimento da An Post explicados

A An Post apresenta os estados em inglês. Eis os mais comuns, por ordem:

| Estado na An Post | O que significa para ti |
| --- | --- |
| `We have received information about your incoming item from the sender` | O remetente enviou os dados à An Post. A An Post ainda não tem a encomenda. |
| `Your item has been handed to An Post` | A An Post recebeu a encomenda. |
| `We have your item and will process it for delivery` | Aceite na rede da An Post. |
| `Your item is going to a sorting office for delivery in` | A caminho do centro de triagem local. O nome do local aparece por vezes em branco. |
| `Your item is being prepared for delivery` | Passo anterior à saída para distribuição. |
| `Your item is out for delivery` | Em distribuição hoje. |
| `We attempted to deliver your item` | Houve tentativa de entrega sem sucesso. |
| `Your delivery is in …, POST OFFICE` | A encomenda encontra-se na estação de correios indicada. |
| `Your item is now available for collection` | Pronta para levantamento. |
| `Your item has been delivered` | Entregue. |
| `Your item has been delivered to your safe spot.` | Deixada no teu local seguro. |

A An Post responde a um número expirado exatamente como a um número desconhecido, sem registo, pelo que um código antigo pode parecer mal escrito. Nos envios que saem da Irlanda, `Your item is being sent internationally…` significa envio para o estrangeiro e `Your item arrived abroad…` indica chegada ao país de destino; a An Post recomenda depois acompanhar no site dos correios recetores, embora possam surgir registos retransmitidos como `Your item is in the sorting office of the local delivery provider`.

## Entrega falhada e levantamento nos correios

Quando a entrega falha, o seguimento mostra `We attempted to deliver your item`.

Uma encomenda também pode ficar a aguardar numa estação de correios. `Your delivery is in …, POST OFFICE` indica a estação e `Your item is now available for collection` confirma que podes levantá-la. Após o levantamento passa a entregue. Se ninguém a levantar dentro do prazo, aparece `Your item was not collected within the specified timeframe. This item will now be returned to sender.`

## Encomenda An Post atrasada, perdida ou retida na alfândega

**Sem movimento.** Se uma encomenda do estrangeiro atrasar após chegar à Irlanda, a Revenue (alfândega irlandesa) aconselha pedir ponto de situação ao operador postal ou ao vendedor. Se a alfândega a selecionar para controlo, a An Post pode pedir a confirmação de encomenda ou fatura; sem dados eletrónicos conformes ou informação suficiente para a declaração aduaneira, a encomenda pode ser devolvida ao remetente. Vê [porque o seguimento não atualiza](guide:tracking-not-updating).

**Encargos aduaneiros.** Compras online expedidas de fora da UE — Grã-Bretanha incluída — podem pagar direitos aduaneiros e IVA se a loja não os cobrou no pagamento. Segundo a [informação aduaneira da An Post](https://www.anpost.com/Post-Parcels/Receiving/Customs-Information), bens até 150 € têm um direito de importação mínimo de 3 € por linha de artigo, acrescido desde 1 de novembro de 2026 da taxa de manuseamento da União de mínimo 2 € mais IVA por linha de artigo (separada do direito aduaneiro e não reembolsável mesmo em caso de devolução ou defeito). A An Post cobra ainda uma taxa administrativa de 6,95 € (também não reembolsável).

A An Post avisa por SMS, e-mail e postal; pagas online, na app An Post ou nos correios antes da data limite («pay by date») indicada no aviso, caso contrário a encomenda regressa ao remetente. Também podes recusar um envio não pago online ou numa estação de correios com a referência aduaneira. Mais detalhes no [guia de alfândega](guide:customs).

> A Revenue avisa que a An Post nunca envia ligações num SMS aduaneiro: qualquer SMS em nome da An Post com um link clicável para pagamento é fraude. A Revenue também nunca envia links de pagamento por e-mail ou SMS.

**Perdida ou não entregue.** Em compras online, a CCPC (autoridade irlandesa do consumidor) indica que o vendedor é responsável pela entrega mesmo quando usa transportadora: se a encomenda se perder ou danificar no transporte, a loja deve substituir o produto. Se foste tu a enviar ou a loja não resolve, contacta a An Post.

## Apoio ao cliente e contactos An Post

Na [página de contacto](https://www.anpost.com/Help-Support/Contact-Us), a An Post disponibiliza os seguintes canais (hora irlandesa):

- **Telefone:** +353 1 705 7600 (01 705 7600 na Irlanda; opção 3 para questões ou contestação de taxas aduaneiras, com o número de seguimento e a referência aduaneira à mão).
- **Webchat:** segunda a sexta das 9h00 às 17h00 e sábado das 9h00 às 14h00 (hora irlandesa), além do chatbot OscarBot disponível 24/7.
- **WhatsApp:** segunda a sexta das 9h00 às 17h00 (hora irlandesa) para correio e encomendas, a partir da página de contacto.
- **Redes sociais:** mensagem no Facebook ou DM no X (`@postvox`).

## Seguir encomendas An Post com o Peek

O [Peek](/) reconhece de imediato um número válido de duas letras, nove dígitos e `IE` como An Post. Uma ligação Track History de anpost.com com `item=` ou `barcode=` também funciona. Se tiveres apenas a referência do remetente na etiqueta, escolhe An Post em «Alterar transportadora» e o Peek mostra o número próprio do envio. Não é preciso código postal.

Os registos da An Post não trazem fuso horário. O Peek lê-os pela hora irlandesa; um registo retransmitido por um parceiro no estrangeiro que indique local usa a hora desse local, bem como os registos seguintes sem local. O Peek apresenta cada registo no fuso horário do teu dispositivo e mantém os textos em inglês da An Post.

O Peek omite o teu nome, quem assinou, referências e comprovativo de entrega, e a An Post não indica data prevista de entrega. Números terminados noutro código de país são seguidos primeiro nos correios desse país; o Peek consulta também a An Post logo que esses correios indiquem a Irlanda como destino — ou nenhum se estiveres na Irlanda — e passa para a An Post assim que surgem novos registos, mantendo o histórico anterior. Em envios que saem da Irlanda, os registos locais retransmitidos ficam no histórico e, se estiveres fora da Irlanda, o Peek pode consultar também os correios do teu país com o mesmo número.

Enquanto mantiveres a encomenda aberta no Peek, ou no dia da entrega com as notificações ligadas, o Peek verifica até de 10 em 10 minutos — e a cada 2 minutos a partir das 6h00 (hora irlandesa) no dia em que sai para distribuição enquanto o seguimento da An Post responder (até de 15 em 15 minutos através de serviços de seguimento nas mesmas condições). Após dois dias sem novidades (12 horas enquanto continuar em «Etiqueta criada» ou «Aceite pela transportadora»), verifica de hora a hora, e a cada 6 horas após sete dias em «Etiqueta criada». Se uma encomenda em «Etiqueta criada», «Aceite pela transportadora» ou «Em trânsito» passar quatro dias sem novos registos, o Peek mostra «Sem atualizações há quatro dias» (ou «Sem atualizações desde…» com a data), «É habitual uma encomenda ficar sem novidades em voos longos, na alfândega ou ao fim de semana. O Peek continua a perguntar a An Post.» e a ligação «Pede ajuda a An Post». Podes escolher alertas para «Todos os registos», «Passos importantes» ou «Só a entrega».

## Perguntas sobre o seguimento An Post

### Posso seguir uma encomenda do Reino Unido com a An Post?

Sim, com o número original: o seguimento da An Post também acompanha envios enviados para a Irlanda por outros correios, como uma encomenda Royal Mail terminada em `GB`.

### Pago alfândega numa encomenda do Reino Unido?

Em compras online enviadas da Grã-Bretanha, sim: a Revenue confirma que pagam direitos aduaneiros e IVA, além da taxa de manuseamento da União (mínimo 2 € mais IVA por linha de artigo) desde 1 de novembro de 2026 e da taxa administrativa da An Post de 6,95 € se a loja não os cobrou no pagamento. Bens expedidos da Irlanda do Norte ou de outro país da UE não pagam direitos aduaneiros nem taxa de manuseamento.

### O que significa «Your item arrived abroad»?

A encomenda saiu da Irlanda e chegou ao país de destino. A An Post procura mostrar registos dos correios recetores, mas recomenda continuar o seguimento no site destes.

### Como recuso uma encomenda An Post com encargos aduaneiros?

Podes recusar um envio não pago no portal de pagamento online da An Post ou numa estação de correios, indicando a referência aduaneira do SMS, e-mail ou postal. Se não for pago até à data limite, regressa ao remetente.

:::sources
- [An Post: Track & Trace](https://www.anpost.com/Post-Parcels/Track/Search) – pesquisa de seguimento da An Post
- [An Post: Customs Information](https://www.anpost.com/Post-Parcels/Receiving/Customs-Information) – taxa administrativa de 6,95 €, direito mínimo de 3 €, taxa da União de 2 € mais IVA, prazo e opção 3 no 01 705 7600
- [An Post: Contact Us](https://www.anpost.com/Help-Support/Contact-Us) – telefone +353 1 705 7600, Webchat, WhatsApp, OscarBot e recusa de envios não pagos
- [Revenue: How to pay your import charges](https://www.revenue.ie/en/customs/individuals/buying-online-personal/pay-import-charges.aspx) – SMS, e-mail e postal, pagamento antes da libertação, devolução em caso de recusa
- [Revenue: Buying goods online for personal use](https://www.revenue.ie/en/customs/individuals/buying-online-personal/index.aspx) – IVA, direitos e taxas administrativas
- [Revenue: How to locate your goods](https://www.revenue.ie/en/customs/individuals/buying-online-personal/locate-your-goods.aspx) – atrasos, pedido de fatura e devolução ao remetente
- [Revenue: Fraudulent emails, SMS and phone calls](https://www.revenue.ie/en/customs/individuals/buying-online-personal/fraudulent-requests.aspx) – sem links nos SMS da An Post nem links de pagamento da Revenue
- [Revenue: Union Handling Fee press release](https://www.revenue.ie/en/corporate/press-office/press-releases/2026/pr-092926-handling-fee.aspx) – Grã-Bretanha incluída, taxa de 2 € desde 1 de novembro de 2026, Irlanda do Norte e UE isentas
- [CCPC: Non-delivery of goods](https://www.ccpc.ie/consumer-advice/consumer-rights/buying-goods/non-delivery-of-goods) – responsabilidade do vendedor em caso de perda ou dano
- [Universal Parcel Scraper: An Post](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/an-post) – formato, dígito de controlo, correio estrangeiro, textos dos estados, fusos horários e dados lidos pelo Peek
:::
