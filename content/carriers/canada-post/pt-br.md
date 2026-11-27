---
title: Rastreamento Canada Post: códigos, status e avisos
description: Rastreie encomendas dos Correios do Canadá (Canada Post): onde achar o código, status em inglês, cartões de aviso, prazos de retirada, alfândega e atendimento.
slug: rastreamento-canada-post
published: 2026-11-25
updated: 2026-11-25
---

A Canada Post (Canada Post Corporation) é a empresa estatal federal que opera o serviço postal do Canadá. Rastreie uma encomenda na [página Track da Canada Post](https://www.canadapost-postescanada.ca/track-reperage/en), no aplicativo gratuito oficial ou colando o código na caixa acima. O código vem do remetente, geralmente no e-mail de envio da loja: a Canada Post não localiza encomendas por nome nem endereço. Se terminar em `CA` e você acompanhar de fora do Canadá, o Peek também pode consultar os correios do seu país com o mesmo código.

## Código de rastreamento Canada Post: como ele é

O Peek associa estes dois formatos à Canada Post, aqui com dígitos inventados:

| Código (inventado) | O que é |
| --- | --- |
| `4018 2930 5746 1826` | 16 dígitos; o último é dígito verificador. [DHL eCommerce](carrier:dhl-ecommerce), [Correos Express](carrier:correos-express), [TNT](carrier:tnt) e Evri também usam 16 dígitos. |
| `RR482910576CA` | Número postal internacional: 2 letras, 9 dígitos e `CA`. O último dígito antes de `CA` é verificador. |

A Canada Post também localiza encomendas pelo número de 15 dígitos de um cartão de aviso de entrega ou por uma referência composta apenas de dígitos (pelo menos 11). Nesses casos, selecione Canada Post manualmente, conforme explicado abaixo.

A Purolator, outra transportadora canadense, usa códigos próprios: 12 dígitos, ou 3 letras e 9 dígitos. O Peek não consulta a Canada Post por conta própria para esses formatos. Se a Canada Post não reconhecer seu código, confirme no e-mail da loja qual transportadora foi usada. Veja mais em [como encontrar um código de rastreamento](guide:find-tracking-number) e [formatos de código de rastreamento](guide:tracking-number-formats).

## O que significam os status da Canada Post

O Peek lê os eventos da Canada Post em inglês. Abaixo estão os mais frequentes, palavra por palavra e em ordem aproximada, com a etapa exibida no Peek.

**No Canadá**

| A Canada Post mostra | O que significa para você | No Peek |
| --- | --- | --- |
| `Electronic information submitted by shipper` | O remetente gerou a etiqueta, mas a Canada Post ainda não recebeu o pacote. | "Etiqueta criada" |
| `Item accepted at the Post Office` | Postado em uma agência postal. | "Recebida pela transportadora" |
| `Item picked up by Canada Post` | A Canada Post coletou com o remetente. | "Recebida pela transportadora" |
| `Item processed` | Em movimento na rede da Canada Post. Pode se repetir várias vezes. | "Em trânsito" |
| `Item out for delivery` | A caminho do seu endereço hoje. | "Saiu para entrega" |
| `Delivered to your community mailbox, parcel locker or apt./condo mailbox` | Entregue na caixa postal comunitária, armário de encomendas ou caixa do prédio. | "Entregue" |
| `Delivered to recipient's safe drop location` | Deixado no local seguro definido por você. | "Entregue" |
| `Notice card left indicating where and when to pick up item` | Houve tentativa de entrega; o pacote vai para uma agência e o cartão informa onde e quando retirar. | "Tentativa de entrega" |
| `Item available for pickup at Post Office` | Aguardando retirada na agência. | "Pronta para retirada" |
| `Final Notice; Item will be returned to sender if not collected within 10 days` | Último aviso: retire em até 10 dias ou volta ao remetente. | "Pronta para retirada" |
| `Recipient not located at address provided. Item being returned to sender.` | Destinatário não localizado no endereço; o pacote está retornando. | "Precisa de atenção" |
| `Item has been returned and is enroute to the Sender` | A caminho de volta ao remetente. | "Em trânsito" |

`Delivered to your concierge or building manager` e `Delivered to mailroom` também aparecem como "Entregue". `Item re-routed due to processing error; Possible delay` e `Delivery may be delayed due to transportation delay` alertam para atraso ("Precisa de atenção"). `Expected delivery date updated` atualiza a previsão sem movimentação física do pacote.

**Do exterior para o Canadá**

| A Canada Post mostra | O que significa para você | No Peek |
| --- | --- | --- |
| `International item mailed in originating country` | Postado no exterior; a Canada Post acompanha pelo código dos correios de origem. | "Recebida pela transportadora" |
| `International item has left originating country and is en route to Canada` | Saiu do país de origem rumo ao Canadá. | "Em trânsito" |
| `Item has arrived in Canada and will be presented for review` | Chegou ao Canadá e aguarda revisão aduaneira. | "Na alfândega" |
| `Item was released by Customs and is now with Canada Post for processing` | Liberado pela alfândega; a Canada Post assume o processamento. | "Em trânsito" |

**Do Canadá para o exterior:** `International item being forwarded to destination country` e `Item has arrived in foreign country` aparecem como "Em trânsito", e `Item presented to customs` como "Na alfândega".

No histórico, registros de entrega exibem "Entregue"; os demais preservam o inglês da Canada Post.

## Ausências, retirada e alfândega no Canadá

**Entrega não realizada.** Encomendas que exigem assinatura ou não cabem na caixa de correio ou compartimento são levadas até a porta. Se ninguém atender, a Canada Post deixa um Delivery Notice Card ou, sem assinatura exigida, pode deixar em local seguro. Após `Delivery Attempt Made / Rescheduled for delivery next business day`, costuma tentar novamente no próximo dia útil. Em prédios com interfone, tocar o interfone conta como tentativa de entrega.

**Cartão de aviso.** Quando o cartão é deixado, o Track exibe "Notice left" e depois informa a agência próxima e a data limite ("Pick up by"). Um Final Notice Card segue após 5 dias corridos; ao fim de 15 dias corridos no total, o pacote retorna ao remetente. Veja mais em [entrega não realizada](guide:missed-delivery) e [encomenda não retirada](guide:uncollected-parcel).

**Retirada.** Leve documento oficial com foto (o Track pede um que mostre o endereço do pacote) e o cartão, o e-mail de aviso ou o código de barras do aplicativo. Terceiros podem retirar levando documento com foto próprio e o seu cartão assinado com o nome deles escrito por extenso, ou uma carta de autorização.

**Armários de encomendas.** A Canada Post deixa a chave do armário na sua caixa de correio. Após 5 dias no armário, o pacote vai para a agência e você recebe um cartão.

**Alfândega em pacotes do exterior.** A Canada Post entrega toda correspondência internacional à Canada Border Services Agency (CBSA) na chegada. Enquanto a alfândega analisa, a Canada Post informa não poder intervir nem consultar detalhes.

**Impostos e taxas.** Bens enviados pelo correio de até CAN$ 20 ficam isentos de impostos e taxas, e presentes de familiares ou amigos do exterior ficam isentos de GST até CAN$ 60. Acima disso, aplicam-se 5% de GST (ou HST na província), além de imposto provincial sobre vendas em Colúmbia Britânica, Manitoba, Quebec e Saskatchewan e alíquotas aduaneiras conforme o produto. O valor aparece no Form E14 (CBSA Postal Import Form) anexado ao pacote. A Canada Post faz a cobrança, incluindo taxa de manuseio de $9,95. Pague pelo botão "Pay" no Track ou no aplicativo (cartão de crédito, ou Apple Pay e Google Pay no app) antes de sair para entrega; a janela online pode ser curta, por isso a Canada Post sugere ativar alertas de tributos. Caso contrário, pague com cartão na porta ou na agência em dinheiro, cartão, cheque ou ordem de pagamento da Canada Post.

**Discorda do valor?** Recuse o pacote e peça revisão, ou pague e solicite ajuste pelo Form B2G, no verso do E14. Veja mais em [encomendas retidas na alfândega](guide:customs).

## Encomenda Canada Post enviada ao exterior: quem entrega

**Quem entrega.** Com número postal internacional terminado em `CA` e dígito verificador válido, o Peek também pode consultar os correios do país de onde você acompanha usando o mesmo número: por exemplo, [USPS](carrier:usps) nos Estados Unidos, [Royal Mail](carrier:royal-mail) no Reino Unido (Parcelforce para códigos iniciados em `E`), An Post na Irlanda, [Australia Post](carrier:australia-post), [NZ Post](carrier:nz-post), [La Poste](carrier:la-poste) na França e [DHL](carrier:dhl) na Alemanha.

A Canada Post não envia ao Peek o país de destino, portanto o seu país decide: nos apps iPhone e Android, a região configurada no aparelho; no navegador, o país de onde você adicionou o envio. Assim que os correios locais mostrarem um evento ao menos tão recente quanto o último da Canada Post, o Peek acompanha por lá mantendo o histórico canadense. Por mais um dia, ainda consulta a Canada Post no máximo a cada 55 minutos. Acompanhado do Canadá, ou com código de 16 dígitos, permanece na Canada Post. Para alfândega no destino, veja [encomendas retidas na alfândega](guide:customs).

## Encomenda Canada Post atrasada, perdida, devolvida ou sumida

**Sem novos registros.** Após quatro dias sem movimentação em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito", o Peek mostra a data do último evento na lista ("Sem atualizações desde seg 12 out") e, na página do envio, exibe: "É comum uma encomenda ficar sem atualizações em voos longos, na alfândega ou no fim de semana. O Peek continua consultando Canada Post." Depois de três semanas sem novidades, muda para: "Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, fale com o vendedor. O Peek continua consultando Canada Post." Também mostra um link de ajuda, como "Pedir ajuda a Canada Post". Segundo a CBSA, itens em revisão aduaneira no Canadá não têm rastreamento preciso. Veja mais em [rastreamento sem atualização](guide:tracking-not-updating).

**Atraso ou extravio.** A Canada Post diz que a maioria dos pacotes atrasados chega em até 5 dias úteis após a previsão. Passado esse prazo, confira o endereço e arredores e abra um chamado de investigação na [página de pacotes perdidos](https://www.canadapost-postescanada.ca/cpc/en/support/kb/claims/missing-packages.page) (faça login para acompanhar) em até 90 dias do envio, ou 6 meses em envios aos EUA ou exterior. O remetente também pode solicitar, e apenas o remetente recebe indenização: se não aparecer em alguns dias, a Canada Post encerra seu chamado e você pede ao remetente para reclamar. Em compras online, comece pela loja. Veja [encomendas extraviadas](guide:lost-parcel).

**Entregue, mas não chegou.** Pelo Canada Post Corporation Act, entregas feitas da maneira usual em casa, empresa, caixa postal ou com alguém razoavelmente autorizado contam como entregues. O Peek mostra "Entregue", mas o rastreamento da Canada Post nomeia o local, como `Delivered to mailroom`: confira lá primeiro, além de armário, portaria, moradores e assinatura no Track antes de abrir investigação. Se os correios do seu país entregaram, verifique lá. Veja [encomenda entregue mas não recebida](guide:delivered-not-received).

**Devolvida.** Quando o pacote devolvido volta ao remetente, o Peek exibe "Devolvida ao remetente". Veja [devolvida ao remetente](guide:returned-to-sender).

## Atendimento ao cliente e contato da Canada Post

- **Telefone:** 1-866-607-6301 no Canadá, 1-416-979-3033 do exterior, TTY 1-800-267-2797. Segunda a sexta das 7h às 23h ET, fins de semana das 9h às 21h ET.
- **Online:** chat ou chamado na [página de ajuda](https://www.canadapost-postescanada.ca/cpc/en/support.page) da Canada Post.

Quando um pacote vindo do Canadá já está nos correios do seu país, fale com eles sobre entrega e retirada; veja as páginas de [USPS](carrier:usps), [Royal Mail](carrier:royal-mail) e [Australia Post](carrier:australia-post). O Peek não pode contatar a Canada Post nem a loja por você.

> Mensagens de texto dizendo que um pacote está retido por taxas pendentes com link para pagamento não são da Canada Post. Mensagens oficiais vêm de 272727 ou 55555 (se você cadastrou alertas), ou de 613-734-1002 e 613-734-1004, com links para canadapost-postescanada.ca. Confira taxas no Track e encaminhe golpes por SMS para 7726.

## Rastrear encomendas Canada Post com o Peek

O [Peek](/) identifica Canada Post na hora para números postais internacionais terminados em `CA` com dígito verificador válido. Um código de 16 dígitos com verificador válido serve para várias empresas: o Peek exibe "Consultando serviços de rastreamento…", consulta Canada Post, DHL eCommerce, TNT, Correos Express e Evri ao mesmo tempo e seleciona a que reconhecer o código. Para número de cartão de aviso ou referência, toque em "Trocar" e escolha Canada Post em "Todas as transportadoras" ou pela busca. Um link da Canada Post com `searchFor=` também funciona; links com `#/search` funcionam apenas com números postais terminados em `CA`, portanto cole códigos de 16 dígitos diretamente.

O Peek consulta a própria Canada Post, sem navegador. Você vê status, histórico com localidade (cidade e província no Canadá), dia previsto de entrega, janela horária quando sai para entrega no Canadá e a Canada Post a fornece, hora da entrega e nome completo do remetente quando informado. Opções de entrega, endereços, assinaturas e fotos ficam de fora. Os horários aparecem no seu fuso horário; em peektracker.com e nos apps iPhone e Android, o histórico termina com "Horários no seu fuso horário:" e o nome dele.

Enquanto o pacote está aberto, ou no dia da entrega com alertas ligados, o Peek verifica até a cada 10 minutos — e até a cada 2 minutos quando a própria Canada Post informa saída para entrega naquele dia, a partir das 6h locais ou, havendo janela horária, meia hora antes de abrir; por serviços de rastreamento, a cada hora, a cada 3 horas após um dia sem novidades e até a cada 15 minutos no dia da entrega enquanto o pacote estiver aberto ou com alertas ligados; nos demais momentos, a cada hora. Quando outra transportadora assume a entrega, o Peek segue o ritmo dela. Após 12 horas sem evento em "Etiqueta criada" ou "Recebida pela transportadora", ou 48 horas em qualquer outra etapa, verifica a cada hora. Alertas: "Cada movimentação", "Etapas importantes" ou "Só a entrega".

**Para o Canadá.** Em pacotes postados no exterior com número postal internacional, o Peek consulta também a Canada Post com o mesmo código quando os correios de origem indicam Canadá como destino ou, sem destino informado, quando você acompanha do Canadá. Assim que a Canada Post exibir evento ao menos tão recente quanto o da origem, o Peek passa a acompanhá-la mantendo o histórico anterior. Um código iniciado em `L` e terminado em `CH` acompanhado pela Swiss Post nunca é transferido para a Canada Post.

## Perguntas frequentes sobre o rastreamento Canada Post

### Por que meu rastreamento da Canada Post não atualiza?

Voos longos, alfândega e fins de semana costumam gerar intervalos entre leituras. O Peek continua verificando, a cada hora após dois dias sem novidades. Se já se passaram mais de 5 dias úteis da data prevista, peça investigação à Canada Post. Veja [rastreamento sem atualização](guide:tracking-not-updating).

### Por que o Peek não encontra meu código Canada Post?

A Canada Post retorna a mesma resposta tanto para códigos inexistentes quanto ainda não escaneados ou expirados, então o Peek exibe "Aguardando Canada Post" até o primeiro registro. Se dois dias depois a resposta continuar igual e nada mais responder, mostra "Depois de dois dias, a transportadora ainda não reconhece este código. Confira o código, escolha outra transportadora ou exclua a encomenda." e passa a verificar diariamente e, após uma semana, semanalmente. Motivos comuns segundo o Track: pacote ainda não entregue à Canada Post ou ainda no exterior, enviado há mais de um ano ou sem rastreamento. Confira todos os dígitos: nos de 16 dígitos o último é verificador, e nos postais o último antes de `CA`.

### Posso rastrear um envio Canada Post pelo número do cartão de aviso?

Sim. Cole o número de 15 dígitos do cartão, toque em "Trocar" e selecione Canada Post em "Todas as transportadoras". O Peek consulta o pacote por ele na Canada Post.

### Quem entrega pacotes da Canada Post nos EUA ou no Reino Unido?

Os correios do seu país, se o código terminar em `CA`: USPS nos EUA, Royal Mail no Reino Unido, ou Parcelforce para códigos iniciados em `E`. O Peek consulta com o mesmo código. Códigos de 16 dígitos ficam na Canada Post.

:::sources
- [Canada Post: Track a package by tracking number](https://www.canadapost-postescanada.ca/track-reperage/en) – app gratuito, "Notice left", data de retirada, documento e motivos de código não encontrado
- [Canada Post: Tracking - How to use Track](https://www.canadapost-postescanada.ca/cpc/en/support/kb/tracking/how-to-use-track.page) – somente remetente tem o código, sem busca por nome ou endereço e números de cartão
- [Canada Post: Tracking - Tracking statuses](https://www.canadapost-postescanada.ca/cpc/en/support/kb/tracking/tracking-statuses.page) – "Notice left", documento, representantes, nova tentativa e alfândega
- [Canada Post: Parcel Services shipping in Canada - Delivery options](https://www.canadapost-postescanada.ca/cpc/en/support/articles/parcel-services-shipping-in-canada/delivery-options.page) – entrega na porta, interfone como tentativa, local seguro, aviso final após 5 dias e guarda de 15 dias
- [Canada Post Support: Why wasn't my parcel delivered to my parcel locker?](https://www.canadapost-postescanada.ca/cpc/en/support/kb/receiving/parcel-lockers/why-wasnt-my-parcel-delivered.page) – 5 dias no armário
- [Canada Post Support: How to pay duty and taxes](https://www.canadapost-postescanada.ca/cpc/en/support/kb/receiving/delivery-faq/how-to-pay-duty-and-taxes-online-canada-post.page) – taxa de $9,95, formas de pagamento, Apple Pay e Google Pay no app e alertas de tributos
- [Canada Post: Claims - Missing packages](https://www.canadapost-postescanada.ca/cpc/en/support/kb/claims/missing-packages.page) – 5 dias úteis, onde procurar, chave do armário, investigações e prazos
- [Canada Post Support: Find answers, contact us or report a problem](https://www.canadapost-postescanada.ca/cpc/en/support.page) – telefones, horários, chat e chamados
- [Canada Post Support: Recognizing spam, fraudulent emails and text messages](https://www.canadapost-postescanada.ca/cpc/en/support/kb/company-policies/privacy-security/what-do-to-if-you-get-suspicious-email.page) – SMS falsos de taxas, remetentes oficiais e 7726
- [Canada Post: Responsible business practices](https://www.canadapost-postescanada.ca/cpc/en/our-company/corporate-sustainability/responsible-business-practices.page) – empresa estatal federal
- [CBSA: Importing by mail](https://www.cbsa-asfc.gc.ca/import/personal-personnel/postal-postale-eng.html) – alfândega postal, isenção de $20, Form E14, taxa de manuseio e falta de rastreamento preciso
- [CBSA: Importing by mail or courier - Paying duty and/or taxes on imported goods](https://www.cbsa-asfc.gc.ca/import/postal-postale/dtytx-drttx-eng.html) – GST, HST, PST, limites de CAN$ 20 e CAN$ 60 e direitos aduaneiros
- [CBSA: Importing by mail or courier - Disputing duty and taxes](https://www.cbsa-asfc.gc.ca/import/postal-postale/dispute-contestation-eng.html) – revisão e Form B2G
- [Justice Laws Website: Canada Post Corporation Act](https://laws-lois.justice.gc.ca/eng/acts/C-10/page-1.html) – a corporação e critérios de entrega concluída
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formatos, textos e etapas, o que o Peek lê e mostra, transições
:::
