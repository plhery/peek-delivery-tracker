---
title: Rastreamento SunYou (SYPost): códigos SY, status e entrega
description: Rastreie encomendas SunYou (SYPost) da China: formato dos códigos SY, significado dos status em inglês, qual transportadora entrega e o que fazer em atrasos.
slug: rastreamento-sunyou
published: 2026-11-25
updated: 2026-11-25
---

A SunYou, também chamada de SYPost, é uma empresa de logística de Shenzhen que transporta pequenas encomendas para lojas online da China e as repassa a uma transportadora local no seu país para o trecho final. Seus códigos começam com SY: consulte o seu no site de rastreamento da SunYou, [sypost.net](https://www.sypost.net), ou na caixa acima. A loja fornece o código nos detalhes de envio do pedido.

## Código de rastreamento SunYou: como ele é

A SunYou não publica um formato de código, mas os códigos vistos no rastreamento seguem dois formatos. Com dígitos inventados:

| Código | O que é |
| --- | --- |
| `SY73918462519` | SY seguido de 11 dígitos: SunYou. |
| `SYGB482910637` | SY, duas letras e 9 dígitos: também é SunYou. |
| `LP61840295738142` | LP e 14 dígitos: código logístico do AliExpress e da Cainiao. |
| `RR482910576CN` | Duas letras, nove dígitos e CN: código da [China Post](carrier:china-post). |

As duas letras após SY nem sempre correspondem ao país de destino. No rastreamento da SunYou, um código `SYRM` foi para o Japão e outro para a Bulgária, enquanto um código `SYAE` foi para a Grécia.

**Onde encontrar.** Nenhuma página da SunYou explica como o código chega até você. O vendedor registra o envio e fornece o código junto aos detalhes de envio do pedido. Se não encontrar, veja [como encontrar seu código de rastreamento](guide:find-tracking-number).

**Códigos parecidos.** Algumas lojas dizem que um código `LP` viaja pela SunYou, mas esse formato pertence à logística do AliExpress; rastreie como [encomenda Cainiao](carrier:cainiao). Um código SY com apenas 10 dígitos, ou com mais de 11, não segue o formato da SunYou.

**Segundo código no caminho.** Depois que o pacote sai do centro da SunYou na China, o rastreamento costuma acrescentar "Carrier Tracking Number:" com o código próprio da transportadora local, o nome dela após "Distributor:" e o respectivo site. Esse código funciona no rastreamento da transportadora local.

## Status de rastreamento da SunYou: o que significam

O site sypost.net exibe os registros no idioma escolhido lá (15 idiomas, incluindo inglês, espanhol, francês, alemão e português). Em inglês, na ordem em que a encomenda costuma passar por eles:

| A SunYou mostra | O que significa para você |
| --- | --- |
| `Pre-Shipment Info Sent To United Kingdom` | O vendedor registrou o pacote. A SunYou ainda não está com ele. |
| `Acceptance, Sent To United Kingdom` | A SunYou recebeu o pacote na China. |
| `Departed Sunyou Facility` | Saiu do centro da SunYou, muitas vezes já com o código da transportadora local. |
| `Export Customs Clearance Successed` | Liberado para sair da China. |
| `Departed From Port Of Origin` | O voo ou navio partiu. |
| `Arrived At GMTC` | Chegou ao centro global de trânsito postal da SunYou, um hub intermediário. |
| `Arrived At Destination Country Airport` | Pousou no país de destino. |
| `Customs Clearance In Process` | Passando pela alfândega de importação. |
| `Customs Clearance Successed` | Liberado pela alfândega. |
| `Hand Over To Last Mile` | A transportadora local recebeu o pacote. Acompanhe pelo código dela a partir daqui. |
| `Delivery In Progress` | Saiu para entrega. |
| `Delivered` | Entregue. |

Algumas linhas mudam de redação conforme o pacote: `GMTC, Arrived` equivale a `Arrived At GMTC`, e `Customs Clearance Was Completed` equivale a `Customs Clearance Successed`. `Delivered, At Mailbox` e `Delivered, Left At The Door` indicam onde o pacote foi deixado.

Dois pontos geram dúvida. Primeiro, `Delivered` pode aparecer logo após `Hand Over To Last Mile`, ou até logo após a linha do aeroporto, sem etapas intermediárias: o rastreamento da transportadora local traz os passos que a SunYou omite. Segundo, o resumo no topo da página usa rótulos próprios. `Alert` indica possível problema e `Expired` significa que o envio ficou tempo demais em trânsito; nenhum dos dois indica uma entrega não realizada. Já `Undelivered` indica que uma entrega não foi realizada; o histórico pode informar o motivo, como `Delivery Failed, Reason: Incorrect Address`.

Em retenções aduaneiras, observe `Customs Clearance Hold` e `Delivery failed, Reason: Refusal to pay VAT / customs duties`; veja [encomendas retidas na alfândega](guide:customs).

## Quem entrega encomendas da SunYou

A SunYou não realiza entregas próprias na Europa: sua linha comercial repassa os pacotes a transportadoras locais no país de destino, e o rastreamento informa o nome da empresa. Em históricos consultados no rastreamento, a [Royal Mail](carrier:royal-mail) entregou no Reino Unido, a [DHL Paket](carrier:dhl) na Alemanha, a [USPS](carrier:usps) ou a [GOFO](carrier:gofo) nos EUA e a Canada Post no Canadá.

A SunYou não publica regras sobre tentativas de entrega, avisos, pontos de retirada ou prazo de guarda. Valem as regras da transportadora local; assim que tiver o nome e o código dela, consulte o site local para reagendar a entrega ou ver onde o pacote aguarda retirada.

Algumas encomendas SunYou seguem como correio simples, que segundo a própria SunYou só tem rastreamento até os correios de destino receberem o objeto. Nesses casos, o histórico pode parar assim que os correios locais assumem o pacote.

## Encomenda SunYou atrasada ou rastreamento parado

**Quem acionar.** O cliente da SunYou é o vendedor: ele abre a conta, contrata o envio e detém o contrato. As páginas da SunYou não oferecem canal de reclamação para destinatários, então em caso de atraso, extravio, dano ou devolução procure o vendedor ou o marketplace onde comprou. As regras de indenização da SunYou aplicam-se aos vendedores.

**Prazo estimado.** Os números comerciais da SunYou para vendedores falam em "4-5 dias da postagem para alguns destinos principais" na linha geral, "entrega mais rápida em até 5 dias" para os principais países da Europa e das Américas na linha comercial e 20 a 30 dias corridos na linha econômica para os EUA. São números comerciais, não uma garantia de data para o seu pedido.

**Sem atualização.** Se o histórico já mostrar um código de transportadora local, consulte primeiro o rastreamento dela. Veja mais em [por que o rastreamento para de atualizar](guide:tracking-not-updating).

**Extraviada ou devolvida.** As mensagens da SunYou para esses casos são `Unfortunately, Your Parcel Is Lost`, `Returned` e `Package Return, Reason: Incomplete Address Information`. Qualquer uma delas é motivo para contatar o vendedor.

## Telefone e atendimento da SunYou

A SunYou lista os mesmos canais para todos os países, sem escritório local para destinatários:

- **Telefone:** +86 4006075388, número chinês. Não há horários nem custos de ligação publicados.
- **E-mail:** mto@sypost.com, caixa de rastreamento.
- **Reclamações:** tousu@sypost.com, exibido em uma página de contato em chinês.

Para qualquer providência prática, fale com o vendedor e com a transportadora local. A SunYou não publica avisos sobre mensagens falsas de entrega.

## Rastrear uma encomenda SunYou com o Peek

O [Peek](/) identifica a SunYou de imediato nos dois formatos SY, que nenhuma outra transportadora do catálogo compartilha. Também é possível colar um link de sypost.net com o código, como o gerado por "Copy Link".

As linhas da SunYou aparecem em inglês em qualquer idioma, exceto correspondências exatas como `Delivered`, exibidas no texto do próprio Peek. O Peek mostra o status, o histórico (até 20 movimentações, sem localidade em cada linha), o país de destino, a hora da entrega e a transportadora local com seu código. Não exibe data prevista de entrega nem ponto de retirada para envios SunYou. Os horários são lidos no fuso informado pela SunYou — primeiro o da China e depois o do seu país — e exibidos no fuso horário do seu aparelho.

Quando a SunYou informa o código da transportadora local, o Peek pode consultar essa empresa (Royal Mail, DHL na Alemanha, Canada Post, USPS ou os correios nacionais, por exemplo) e passar a acompanhá-la assim que ela registrar avanço mais recente. O pacote mantém o código SunYou, a página mostra "Entrega por" seguido da transportadora e lista o código dela com link. Enquanto essa empresa tiver apenas o anúncio do envio, sem registro próprio, a página mostra "Anunciada a …, ainda não recebida". Isso não ocorre em todos os envios: a Meest na Grécia, por exemplo, não é acompanhada.

Enquanto o pacote está aberto no Peek, ou no dia da entrega com alertas ligados, o Peek verifica até a cada 10 minutos — ou a cada 2 minutos a partir das 6h no horário local no dia em que sai para entrega — enquanto o rastreador da própria SunYou responde (quando apenas serviços de rastreamento respondem: a cada hora, a cada 3 horas após um dia sem novidades e até a cada 15 minutos no dia da entrega enquanto o pacote estiver aberto ou com alertas ligados). Nos demais momentos, verifica a cada hora. Quando outra transportadora assume a entrega, o Peek acompanha o ritmo da empresa que realiza a entrega. Ele pode enviar alertas de cada movimentação, apenas das etapas importantes ou "Só a entrega". Após dois dias sem novidades (12 horas enquanto ainda mostrar "Etiqueta criada" ou "Recebida pela transportadora"), verifica a cada hora. Ele não pode contatar a SunYou nem o vendedor por você.

## Perguntas frequentes sobre o rastreamento SunYou

### O rastreamento SunYou é real ou falso?

A SunYou é uma empresa de logística real, fundada em Shenzhen em 2008, e seu rastreamento funciona em sypost.net. Se o código mostrar `Not Found` lá, peça ao vendedor para conferir.

### O que é a SunYou Post?

É a SunYou Logistics, empresa de Shenzhen que envia pequenas encomendas ao exterior para vendedores online na China e as repassa a uma transportadora local para entrega final.

### Onde fica o centro da SunYou?

A SunYou tem sede em Shenzhen, na China, com filiais em Guangzhou, Dongguan, Yiwu, Kunshan e Ningbo. `Departed Sunyou Facility` significa que o pacote saiu do centro da SunYou na China.

### Quanto tempo a SunYou leva para entregar?

A SunYou informa a vendedores 4 a 5 dias para alguns destinos principais na linha geral e 20 a 30 dias corridos na linha econômica para os EUA. Isso não representa promessa de prazo para o seu pedido.

### Quem entrega encomendas SunYou no Reino Unido?

Nos históricos consultados no rastreamento da SunYou para o Reino Unido, a Royal Mail fez a entrega. O histórico do seu próprio pacote indica a transportadora após `Departed Sunyou Facility`, junto com o código dela.

:::sources
- [SunYou: Sunyou Package Tracking](https://www.sypost.net/) – site de rastreamento, idiomas, busca de códigos, telefone, e-mail de rastreamento, rótulos de status, linhas de código da transportadora, eventos e transportadoras locais
- [SunYou: 联系我们 (Contact)](https://www.sypost.com/views/contact.html) – telefone, e-mail e canal de reclamações
- [SunYou: 顺邮宝专线 (general line)](https://www.sypost.com/views/services-1.html) – limites de rastreamento de correio simples, prazos e indenização a vendedores
- [SunYou: 顺速宝专线 (commercial line)](https://www.sypost.com/views/services-2.html) – transportadoras locais de última milha e prazos
- [SunYou: 顺速派专线 (US economy line)](https://www.sypost.com/views/services-3.html) – 20 a 30 dias para os EUA
- [SunYou: 投寄说明 (posting instructions)](https://www.sypost.com/views/document-3.html) – contratação por vendedores e rastreamento em sypost.net
- [SunYou: 顺友简介 (about)](https://www.sypost.com/views/about.html) – fundação em 2008, Shenzhen e filiais
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – o que o Peek mostra e formatos de código
:::
