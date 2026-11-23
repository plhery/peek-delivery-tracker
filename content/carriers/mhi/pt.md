---
title: Seguimento MHI: estados Trackmail e quem entrega
description: Recebeste um número MHI de uma loja online? Onde acompanhar, o que significam os estados no Trackmail, quem entrega no teu país e a quem perguntar.
slug: seguimento-mhi
published: 2026-11-23
updated: 2026-11-23
---

A MHI (Mail Handling International) é uma empresa de Bristol que envia encomendas de lojas online do Reino Unido para compradores no estrangeiro. As lojas enviam os volumes para o seu centro de tratamento em Bristol; a MHI exporta-os e entrega cada um a uma transportadora ou correio do teu país, que faz a entrega com o seu próprio número. Acompanha o código MHI no site de seguimento da MHI, o [Trackmail](https://www.trackmail.co.uk/), ou na caixa acima. O número vem da loja: a MHI atribui aos seus clientes um código por encomenda, portanto procura-o no e-mail de envio ou nos detalhes da tua compra.

## Número de seguimento MHI: como é

A MHI não publica um formato para compradores. Os formatos que os seus números assumem, com dígitos inventados:

| Número | O que é |
| --- | --- |
| `MHI482W736405829173` | MHI, três dígitos, depois W e 12 dígitos: MHI. |
| `MHI482WBB736405829` | MHI, três dígitos, depois WBB e 9 dígitos: MHI. |
| `MHI-482-P736405829` | MHI, três dígitos, depois P e 9 dígitos: MHI. As etiquetas imprimem este formato com hífenes. |
| `MHI482736405829173` | MHI, três dígitos e mais 12 dígitos: MHI. |
| `RR482917367US` | Duas letras, 9 dígitos, duas letras: número postal do mesmo envio, mostrado pelo Trackmail em alguns envios sem seguimento. |
| Qualquer outro formato | O próprio número da transportadora que entrega, mostrado no Trackmail após «Tracking number:» num envio com seguimento. |

Os três dígitos após MHI correspondem à conta da loja junto da MHI. Escreve um número P no Trackmail com os hífenes, tal como impresso: o Trackmail só o encontra assim. Uma mesma encomenda pode ficar com dois números, o da MHI e o da transportadora. Mais pormenores em [formatos de números de seguimento](guide:tracking-number-formats).

## Estados de seguimento MHI explicados

O Trackmail está em inglês. Acima do histórico indica o serviço escolhido pela loja: «The seller requested this item be shipped via the following service:», seguido de `TRACKED` ou `UNTRACKED`. Por baixo surgem os eventos, do mais recente para o mais antigo, cada um com data, hora e texto, a maioria com categoria e local: o registo de exportação da MHI e depois os da transportadora na redação da MHI. Pela ordem em que a encomenda passa por eles:

| O Trackmail mostra | O que significa para ti |
| --- | --- |
| `Your order has been processed at the UK export facility. It is now on route to the receiver.` | A MHI tratou a encomenda no Reino Unido e enviou-a para o estrangeiro. Num envio sem seguimento costuma ser a única linha. |
| `Shipment information sent to last mile service provider` | A transportadora no teu país recebeu os dados da encomenda, ainda não a encomenda física. |
| `Notification sent to recipient` | A transportadora avisou-te de que a encomenda está a caminho. |
| `Handed Over to Delivery Partner` | A transportadora recebeu a encomenda. |
| `Arrived at sorting center` | Registo num centro da rede da transportadora. |
| `Dispatched from sorting center` | Saiu desse centro. |
| `Assigned to Transport` | Segue num veículo entre centros. |
| `Shipment at final delivery center` | Chegou ao depósito que vai efetuar a entrega. |
| `Delivery scheduled in the final delivery depot` | Está prevista na rota de entrega do dia. |
| `Delivery attempted` | A tentativa falhou: ninguém recebeu a encomenda. |
| `Arrived at Pick-up Point` | Está à tua espera num ponto de levantamento. |
| `Successfully delivered` | Entregue ou levantada. |

Lê o texto e não a categoria ao lado. O Trackmail coloca `Shipment at final delivery center` e `Arrived at Pick-up Point` em «Out for delivery», embora o primeiro aconteça antes da rota e o segundo signifique que tens de ir levantar. Também coloca `Delivery attempted` em «Delivery», como se fosse uma entrega concluída. Uma rota e uma tentativa podem repetir-se durante vários dias.

As horas no Trackmail não trazem fuso horário: o registo da MHI está na hora do Reino Unido e os da transportadora na hora local desta.

Para um código desconhecido, o Trackmail mostra uma única linha começada por `No details found for Tracking Code, or Tracking Code not recognised.` e acrescenta que, se encomendaste recentemente, a MHI pode ainda não ter os dados.

## Entrega MHI: quem traz a tua encomenda

A MHI não entrega diretamente no estrangeiro. Indica trabalhar com mais de 100 transportadoras para chegar a 252 países e territórios. No centro de Bristol o código de barras de cada encomenda é lido e o sistema da MHI escolhe a transportadora da última milha conforme o país de destino. As lojas podem optar por serviços com seguimento, sem seguimento ou expresso, pelo que quem entrega depende de onde vives e do que a loja contratou.

**Envios com seguimento.** O Trackmail pode indicar o número da transportadora após «Tracking number:», com uma ligação «View carrier tracking» para o site dela. Para dúvidas, o Trackmail remete para essa ligação ou para o comerciante.

**Envios sem seguimento.** O Trackmail refere que só existe informação limitada e muitas vezes mostra apenas o registo de exportação da MHI. Em alguns envios apresenta um «local poster number»: se o deres ao teu operador postal ou estação de correios, refere o Trackmail, podem conseguir ajudar. Testa-o também no seguimento do teu correio: [USPS](carrier:usps), [Canada Post](carrier:canada-post), [Australia Post](carrier:australia-post) ou An Post.

Avisos, tentativas, novas entregas e pontos de levantamento seguem as regras da transportadora final: consulta o seguimento dela em «View carrier tracking» e vê [entregas falhadas](guide:missed-delivery).

## Encomenda MHI sem atualizações, perdida ou devolvida

**`No details found…`.** Confirma o número e escreve um número P com os hífenes. Se a compra for recente, a MHI pode ainda não ter os dados: tenta mais tarde.

**Apenas o registo de exportação.** Num serviço sem seguimento, pode ser tudo o que o Trackmail mostra: usa o «local poster number» junto do teu correio, se existir, ou pergunta à loja. Num envio com seguimento, a transportadora pode ainda não o ter lido; assim que aparecer o número dela, o site próprio dela pode estar mais atualizado. Vê [porque o seguimento deixa de atualizar](guide:tracking-not-updating).

**Entregue mas não chegou.** Foi a transportadora local quem entregou: verifica o seguimento dela através de «View carrier tracking» e depois fala com a loja. Vê [entregue mas não recebida](guide:delivered-not-received).

**Perdida ou danificada.** Contacta a loja. A MHI trabalha para a loja, não publica processo de reclamação para destinatários e o próprio Trackmail envia-te para o comerciante. Vê [encomendas perdidas](guide:lost-parcel).

**Alfândega.** A MHI disponibiliza às lojas vários regimes aduaneiros, incluindo DDP (Delivered Duty Paid), portanto se fica algo por pagar depende da escolha da loja. O que pode haver a pagar está em [encomendas retidas na alfândega](guide:customs).

**Devolvida.** O prazo durante o qual uma encomenda não levantada aguarda antes de voltar depende da transportadora que entrega: verifica o seguimento dela. Se regressar, pede reembolso ou reenvio à loja. Vê [devolvida ao remetente](guide:returned-to-sender).

## Contacto e apoio ao cliente MHI

Contactos indicados pela MHI:

- **Dúvidas de seguimento:** e-mail tracking@mhi.co, indicado na página eCommerce delivery e na página de contacto da Nexae (a sua plataforma de envio).
- **Telefone:** +44 (0) 117 977 6655, número britânico junto à morada de Bristol, 82 Feeder Road, BS2 0TQ. A MHI não publica horários nem indica que trate dúvidas de encomendas por telefone.
- **Formulário Enquiries:** para questões gerais. A MHI avisa que pedidos de seguimento enviados através deste formulário não são tratados nem respondidos.

O conselho da MHI aos compradores: consultar o Trackmail ou contactar o vendedor ou marketplace. A loja é cliente da MHI e pode acompanhá-la; para uma entrega falhada ou levantamento, fala com a transportadora indicada no Trackmail.

## Seguir uma encomenda MHI com o Peek

O [Peek](/) reconhece um número MHI pela forma nas quatro séries acima, com ou sem hífenes. Etiquetas antigas com apenas dois dígitos após MHI, ou oito dígitos no fim, não são pesquisadas: verifica-as no Trackmail. Podes também colar uma ligação Trackmail com o número; o Peek lê-a como MHI se o número tiver o formato da MHI.

O Peek lê o Trackmail e mostra o estado e o histórico, com o local de cada registo num mapa. O Trackmail não fornece data prevista de entrega, peso, prova de entrega nem quem assinou, pelo que o Peek não recebe nenhum deles da MHI, e omite a tua morada e o código postal presentes na ligação da transportadora.

Quando a ligação do Trackmail aponta para uma transportadora que o Peek conhece, a página da encomenda mostra «Entrega:» seguida dessa transportadora e do respetivo número. Enquanto essa transportadora tiver apenas o pré-aviso, sem registo próprio, aparece antes «Anunciada a …, ainda não recebida». Se o Peek ler diretamente o seguimento dessa transportadora, consulta-a com esse número e muda para ela assim que mostrar progresso mais recente. Uma transportadora que o Peek não conheça não é indicada: usa «View carrier tracking» no Trackmail. Num envio sem seguimento com «local poster number», o Peek pode testar esse número num correio nacional, normalmente o do país a partir do qual adicionas a encomenda, e mudar da mesma maneira. Nem com todos os correios, porém: um número `RR…` terminado em `AU` adicionado a partir da Austrália não é enviado à Australia Post, por isso confirma-o diretamente no site da Australia Post.

Enquanto manténs a encomenda aberta no Peek, ou no dia da entrega com as notificações ativadas, o Peek verifica até de 10 em 10 minutos, e a partir das 6:00 (hora local) do dia em que sai para distribuição até de 2 em 2 minutos enquanto o Trackmail (ou o site próprio da transportadora que assumiu a entrega) responder. Após dois dias sem novidades (12 horas enquanto a encomenda continuar em «Etiqueta criada» ou «Aceite pela transportadora»), o Peek verifica de hora em hora; e se ficar em «Etiqueta criada» durante sete dias, de 6 em 6 horas.

Se uma encomenda ainda em «Etiqueta criada», «Aceite pela transportadora» ou «Em trânsito» passar quatro dias sem novo registo, o Peek assinala-a com «Sem atualizações há quatro dias» (ou «Sem atualizações desde …»), explica: «É habitual uma encomenda ficar sem novidades em voos longos, na alfândega ou ao fim de semana. O Peek continua a perguntar a MHI.» e mostra «Pede ajuda a MHI». Podes ativar alertas para «Todos os registos», «Passos importantes» ou «Só a entrega». O Peek não pode contactar a MHI, a transportadora nem a loja por ti.

## Perguntas sobre o seguimento MHI

### Onde está a minha encomenda MHI?

A linha mais recente no Trackmail mostra a etapa e os registos da transportadora indicam a localidade. Antes de `Handed Over to Delivery Partner`, está com a MHI ou a caminho do teu país; depois disso, está com a transportadora local e, num envio com seguimento, o número dela aparece no Trackmail após «Tracking number:».

### O que significa «processed at the UK export facility»?

A MHI tratou a encomenda no seu centro de exportação no Reino Unido e enviou-a para ti. É o registo próprio da MHI e, num serviço sem seguimento, costuma ser o único.

### O site trackmail.co.uk é fidedigno?

Sim. O Trackmail é o site de seguimento da MHI: a ligação «Track my Parcel» no próprio site da MHI leva diretamente lá. A Companies House lista a Mail Handling International Limited como empresa ativa, constituída em 1996 na morada de Bristol indicada pela MHI.

### A MHI tem telefone de apoio ao cliente?

Indica o +44 (0) 117 977 6655, mas para seguimento pede que escrevas para tracking@mhi.co ou contactes a loja. Para a entrega em si, deves falar com a transportadora indicada no Trackmail.

:::sources
- [MHI: Home](https://www.mhi.co/) – ligação Track my Parcel para o Trackmail, dúvidas de encomendas para o vendedor, formulário geral não serve para seguimento, mais de 100 transportadoras, 252 países e territórios, com seguimento, sem seguimento e expresso, DDP, morada, telefone
- [MHI: eCommerce Delivery](https://www.mhi.co/ecommerce-delivery) – centro de Bristol, transportadora escolhida por país, códigos de seguimento para lojas, exportação, tracking@mhi.co
- [Nexae: Contact Us](https://nexae.co.uk/ContactUsHomepage) – tracking@mhi.co, a Nexae pertence à MHI
- [MHI: Trackmail](https://www.trackmail.co.uk/) – site de seguimento da MHI, inglês, contacto de seguimento
- [Companies House: Mail Handling International Limited](https://find-and-update.company-information.service.gov.uk/company/03241562) – ativa, constituída em 23 de agosto de 1996, sede 82 Feeder Road, Bristol
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formatos de números, textos e categorias do Trackmail, linha de serviço, ligação da transportadora, número postal local, texto de número desconhecido, fusos horários, o que o Peek mostra e que transportadora ou correio consulta
:::
