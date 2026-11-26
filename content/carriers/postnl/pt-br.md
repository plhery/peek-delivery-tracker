---
title: Rastreamento PostNL: códigos NL e 3S, status e entrega
description: Rastreamento PostNL fora dos Países Baixos: códigos NL e 3S, status em postnl.post, quem entrega no seu país e quem procurar em caso de atraso.
slug: rastreamento-postnl
published: 2026-11-26
updated: 2026-11-26
---

A PostNL é o serviço postal dos Países Baixos. Ela envia encomendas e cartas de lojas neerlandesas ao exterior e, por meio da Spring GDS, seu braço internacional, transporta encomendas para vendedores online do mundo inteiro. Ambas são rastreadas no site internacional da PostNL, [postnl.post](https://postnl.post/), ou na caixa acima. No seu país, uma transportadora local — muitas vezes os correios nacionais — faz a entrega. O código aparece no e-mail de envio da loja ou na página do pedido.

## Código de rastreamento PostNL: como ele é

A PostNL utiliza dois tipos de código de barras, segundo sua documentação para desenvolvedores. Com dígitos inventados:

| Código | O que é |
| --- | --- |
| `CW482910576NL` | Duas letras, 9 dígitos e NL: número postal internacional da PostNL. O início C indica encomenda para fora da UE, como Reino Unido, EUA, Canadá ou Austrália. |
| `LA482910576NL` | L: pequeno volume ou carta com rastreamento. `LW` é o mesmo serviço. |
| `RI482910576NL` | R: carta registrada. `RF` é o mesmo serviço. |
| `UL482910576NL` | U: envio sem rastreamento completo. Não espere leituras no seu país. |
| `3SABCD482910573` | 3S, código de cliente em letras e dígitos, 15 caracteres ao todo: código de barras de encomenda da PostNL. |
| `3SABCD4829105` | O mesmo código na versão de 13 caracteres, usada pela PostNL no Parcel EU para outros países da UE, como a Irlanda. |

O último dos nove dígitos em um número NL é o dígito verificador, portanto um único dígito errado costuma invalidar o código.

O site postnl.post aceita até 150 códigos por vez. Ele aceita números NL e códigos 3S de 15 caracteres, mas recusa códigos de 13 caracteres com "The shipment barcode was incorrect.". Nesses casos — como um Parcel EU para a Irlanda — peça à loja o código da transportadora parceira. Quando a loja emite uma etiqueta Parcel EU, a PostNL gera o código de barras da parceira no exterior que fará a entrega, rastreável no site dela; esse código ainda pode mudar enquanto o pacote estiver nos Países Baixos.

## Status de rastreamento da PostNL explicados

O site postnl.post mostra uma barra de progresso — `Pre-advised`, `Preparing`, `Departed`, `Transit`, `Arrived`, `Customs`, `Processing`, `Delivered` — com as movimentações em inglês abaixo. A maioria indica o país onde ocorreu. Estas são as linhas usuais, em ordem aproximada:

| O site postnl.post mostra | O que significa para você |
| --- | --- |
| `Pre-advised`, `Preparing` | O remetente registrou o envio e mandou os dados à PostNL. Ela pode ainda não estar com o pacote. |
| `Consignment received at the PostNL Acceptance Centre`, `The shipment is handed over in bulk final acceptance of the item to be confirmed` | A PostNL recebeu o lote do remetente; a leitura individual do seu item ainda pode levar algum tempo. |
| `The item is received by PostNL` | A PostNL escaneou seu item. Pode surgir dias após a linha do centro de aceitação. |
| `Departed`, `Transit` | Em deslocamento entre países, geralmente por avião. |
| `Arrived` | Chegou a um país da rota, geralmente o seu. |
| `Customs` | Na alfândega do país de destino. |
| `The item has arrived at the domestic sorting centre`, `The item is at the local sorting centre` | Em triagem no país exibido ao lado da movimentação. |
| `The item is at the local delivery office` | Na agência responsável pela entrega. |
| `The item is out for delivery` | Saiu para entrega. |
| `Driver is en route to the pickup location` | A caminho de um ponto de retirada, não da sua porta. |
| `Pick-up point` | Aguardando retirada em um ponto de coleta. |
| `Unsuccesfull`, `Undelivered` | Tentativa de entrega sem sucesso (`Unsuccesfull` é a grafia da própria PostNL). |
| `Undeliverable item, has been returned to shipper` | Retornando ao remetente. |
| `The item has been returned and arrived at PostNL` | A devolução chegou de volta à PostNL. |
| `Delivered` | Entregue. |

`The shipment barcode was not found.` indica que postnl.post ainda não tem registro com esse código ou há um caractere errado. "Destination: unknown" na página de resultado significa que nenhuma leitura informou o destino ainda. Se você informar no Peek o código postal de entrega de uma encomenda 3S, ele consulta também o próprio rastreador da PostNL, cujos textos variam: para pacotes entregues no exterior, aparecem `Your parcel has crossed the border`, `Your parcel's at a sorting centre in the destination country`, `Your shipment is on its way to the recipient` (saiu para entrega) e `We missed you; we’ll try again` (tentativa frustrada).

## Quem entrega encomendas da PostNL no seu país

A PostNL ou a Spring GDS leva o pacote até o seu país. A Spring GDS afirma que "gerencia o trecho internacional, enquanto um parceiro local cuida da entrega final", e que após entrar no país de destino "costuma ser repassado ao serviço postal local (por exemplo, USPS, Royal Mail, Correos)".

- **Estados Unidos:** [USPS](carrier:usps). Investigações de itens do exterior passam pelo remetente junto aos correios do país de origem. Em pacotes comerciais para os EUA continentais e Porto Rico, a loja assina um aditivo permitindo à PostNL fazer o desembaraço e pré-pagar impostos.
- **Reino Unido:** [Royal Mail](carrier:royal-mail), um dos correios locais citados pela Spring GDS.
- **Irlanda:** An Post para itens postais. Já códigos Parcel EU seguem para a parceira da PostNL na Irlanda, cujo nome a PostNL não informa.
- **Canadá:** [Canada Post](carrier:canada-post). Se a ferramenta Track não mostrar nada, a Canada Post pede que o remetente abra investigação nos correios de origem.
- **Austrália:** [Australia Post](carrier:australia-post).

Um número NL segue o padrão S10 da UPU, compartilhado entre os correios para remessas internacionais, então você pode testá-lo no site dos correios do seu país assim que chegar. Um volume `UL` sem rastreamento pode não exibir nada lá.

Tentativas, avisos e prazos de guarda seguem as regras da transportadora local; veja [entrega frustrada](guide:missed-delivery) e [encomenda não retirada](guide:uncollected-parcel). Sobre impostos e taxas, veja [encomendas retidas na alfândega](guide:customs).

## Encomenda PostNL sem atualização, perdida ou devolvida

**Ainda sem leituras.** `Pre-advised` ou `Preparing` indica que o vendedor registrou o envio mas pode ainda não ter entregue o pacote. A Spring GDS pede para aguardar 24 a 48 horas após receber o código pela primeira leitura.

**Parada em trânsito.** Depois de `Departed` ou `Transit`, encomendas em voo ou navio não recebem leituras até a chegada, e a alfândega costuma reter pacotes por vários dias. Depois, teste o mesmo número nos correios nacionais. Após quatro dias sem movimentação em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito", o Peek mostra a data do último evento na lista ("Sem atualizações desde seg 12 out") e, na página do envio, exibe: "É comum uma encomenda ficar sem atualizações em voos longos, na alfândega ou no fim de semana. O Peek continua consultando PostNL.", com o link "Pedir ajuda a PostNL" para o suporte oficial. Após três semanas sem novidades, muda para: "Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, fale com o vendedor. O Peek continua consultando PostNL." Veja mais em [por que o rastreamento para de atualizar](guide:tracking-not-updating).

**Entregue, mas não chegou.** A Spring GDS recomenda conferir com vizinhos, locais seguros como varanda ou garagem e portaria. Também alerta que transportadoras às vezes marcam como entregue até 24 horas antes da entrega efetiva. Depois disso, fale com o vendedor. Veja [encomenda entregue mas não recebida](guide:delivered-not-received).

**Extravio ou dano.** Avise o vendedor imediatamente e fotografe a embalagem danificada: ele precisa das fotos para reclamar junto à Spring GDS. Veja [encomendas extraviadas](guide:lost-parcel).

**Devolvida.** Peça ao vendedor reenvio ou reembolso. A Spring GDS cita "Incorrect Address" e "Unclaimed" (após o prazo de guarda) como motivos comuns e diz que uma devolução iniciada não pode ser revertida. Veja [devolvida ao remetente](guide:returned-to-sender).

## Contato e atendimento ao cliente da PostNL

Segundo as páginas oficiais da PostNL e da Spring GDS:

- **postnl.post:** informa "Questions about your delivery outside The Netherlands? Please contact your webshop." Para entregas dentro dos Países Baixos, encaminha ao atendimento da PostNL.
- **Spring GDS:** não pode tratar de remessas diretamente com consumidores devido à privacidade de dados e aos contratos com vendedores; o vendedor detém a conta e abre investigações junto à Spring GDS.
- **Transportadora local:** para entrega frustrada ou reagendamento, fale diretamente com ela; a Spring GDS não consegue agendar reentregas locais.

Para qualquer outro assunto, a loja ou o marketplace onde você comprou é o caminho mais rápido.

## Rastrear uma encomenda PostNL com o Peek

O [Peek](/) reconhece na hora números NL com dígito verificador válido e códigos 3S com código de cliente de quatro letras. Um código 3S de três letras vai para a [DHL eCommerce Países Baixos](carrier:dhl-ecommerce-nl), embora a documentação da PostNL também mostre códigos de três letras. Para códigos de cliente mais curtos, o Peek não identifica a empresa apenas pelo formato (com duas letras, consulta também a DHL eCommerce Países Baixos): cole mesmo assim, pois o Peek acompanha mais de 4.500 transportadoras. Enquanto verifica, exibe "Consultando serviços de rastreamento…"; se aparecer outra transportadora, escolha PostNL em "Trocar". Links de postnl.post (`/track?barcodes=`) e de mailingtechnology.com, o rastreador da Spring GDS (`/tracking?tn=`), são identificados como PostNL. Para códigos 3S, o formulário também oferece o campo opcional "Código postal de entrega": "PostNL só mostra uma encomenda enviada dentro dos Países Baixos com o código postal de entrega." Informando-o (e o país, para códigos postais de fora dos Países Baixos), o Peek consulta primeiro o próprio rastreador da PostNL; sem ele, consulta outras fontes.

O Peek lê postnl.post e, quando você informa o código postal, o próprio rastreador da PostNL. Exibe status, histórico com o país de cada movimentação, horário de entrega e país de destino. Os horários aparecem no fuso do seu aparelho (em peektracker.com e nos aplicativos iPhone e Android, o histórico termina com "Horários no seu fuso horário:" e o nome dele); em países com vários fusos, como EUA, Canadá e Austrália, o Peek precisa assumir um deles, podendo haver diferença de algumas horas. A PostNL não fornece data prevista nem remetente. Para números NL, assim que a PostNL informa o destino, o Peek consulta os correios nacionais desse país — como Royal Mail ou USPS — com o mesmo número e muda para eles quando exibirem progresso mais recente. Como postnl.post considera códigos 3S de 13 caracteres incorretos, sem código postal o Peek pesquisa o envio por [serviços universais de rastreamento](guide:universal-tracker).

O Peek consulta a PostNL até a cada 30 minutos, inclusive no dia da entrega, mas uma atualização manual pode verificar antes. Quando outra transportadora assume a entrega, ele segue o ritmo dela. Pode notificar a cada movimentação, nas etapas importantes ou só no dia da entrega, mas não pode contatar a PostNL nem a loja por você.

## Perguntas frequentes sobre o rastreamento PostNL

### Onde está minha encomenda PostNL?

A última movimentação indica a etapa atual e o país onde ela está. Assim que mostrar o seu país, teste o mesmo número no site dos correios nacionais: lá costuma haver leituras locais mais detalhadas.

### O que significa "The shipment barcode was incorrect"?

Significa que postnl.post não aceita esse código de barras. Em geral trata-se de um código 3S de 13 caracteres do serviço Parcel EU da PostNL. Peça à loja o código da transportadora parceira e rastreie no site dela.

### Quem entrega encomendas da PostNL nos EUA ou no Reino Unido?

Geralmente os correios nacionais. A Spring GDS, braço internacional da PostNL, informa que encomendas ao entrarem no país costumam ser repassadas ao serviço postal local, como USPS ou Royal Mail. Assim que uma leitura indicar seu país, teste o mesmo número NL no site desses correios.

:::sources
- [PostNL: International Tracking](https://postnl.post/) – 150 códigos, barra de progresso, textos de leitura, respostas "not found" e "incorrect", destino e orientação para contatar a loja
- [PostNL Developer: Barcode guidelines](https://developer.postnl.nl/docs/#/http/api-endpoints/send-track/barcode/guidelines) – códigos 3S de 13 ou 15 caracteres, código de cliente em letras e faixa S10 NL
- [PostNL Developer: Product codes International](https://developer.postnl.nl/docs/#/http/reference-data/product-codes-international) – Parcel EU 3S de 13 caracteres e destinos, código da parceira, Parcel Non-EU C e CW, LA/LW, RI/RF, UL e aditivo dos EUA para desembaraço e impostos pré-pagos
- [Spring GDS: We are Spring GDS](https://www.spring-gds.com/about-us/) – integrante do grupo PostNL e comércio eletrônico internacional
- [Spring GDS: Track and Trace](https://www.spring-gds.com/track/) – serviço postal local, parceiro local, formato de código, primeira leitura, pausas em trânsito e alfândega, entrega não recebida, reclamações, devoluções, reentregas e ausência de atendimento direto ao consumidor
- [Spring GDS: Contact us](https://www.spring-gds.com/contact-us/) – dúvidas de rastreamento encaminhadas à loja
- [Canada Post: Tracking international packages](https://www.canadapost-postescanada.ca/cpc/en/support/kb/tracking/tracking-international-packages.page) – remetente investiga nos correios de origem
- [USPS: International Inquiries – The Basics](https://faq.usps.com/s/article/International-Inquiries-The-Basics) – remetente fora dos EUA aciona os próprios correios
- [UPU: S10 standard, Identification of postal items](https://www.upu.int/UPU/media/upu/files/postalSolutions/programmesAndServices/standards/S10-12.pdf) – numeração compartilhada entre correios e dígito verificador
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – o que o Peek lê e exibe
:::
