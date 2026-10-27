---
title: SunYou rastreamento: número SY, estados e quem entrega
description: Rastrear uma encomenda SunYou (SYPost) em Portugal: como é o número SY, o que quer dizer cada estado, quem faz a entrega e a quem perguntar quando fica parada.
slug: sunyou-rastreamento
published: 2026-10-12
updated: 2026-10-27
---

A SunYou, também escrita SYPost, é uma empresa de logística de Shenzhen que leva para o estrangeiro as encomendas pequenas de vendedores online chineses e as passa a uma transportadora local para o último troço. Os números começam por SY: escreve o teu no site de seguimento da SunYou, o [sypost.net](https://www.sypost.net), ou cola-o na caixa acima. O número vem da loja, nos detalhes de envio da encomenda.

## Número de rastreamento SunYou: como é

A SunYou não publica nenhum formato de número, mas os que aparecem no seguimento dela têm duas formas. Com algarismos inventados:

| Número | De quem é |
| --- | --- |
| `SY12345678901` | SY e 11 algarismos: SunYou. |
| `SYPT000000000` | SY, duas letras e 9 algarismos: também SunYou. |
| `LP00000000000000` | LP e 14 algarismos: uma referência logística do AliExpress e da Cainiao. |
| `RR000000005CN` | Duas letras, 9 algarismos e CN: um número dos [Correios da China](carrier:china-post). |

As duas letras a seguir a SY nem sempre são o país de destino. No seguimento da SunYou, um número `SYRM` foi para o Japão e outro para a Bulgária, e um número `SYAE` foi para a Grécia. Quem procura «sunyou post rm tracking» tem um número destes: o RM não indica o país de destino.

**Onde o encontras.** Nenhuma página da SunYou diz como o número te chega. É o vendedor que regista a encomenda e te dá o número com os detalhes de envio. Se não o encontras, vê [onde encontrar o número de seguimento](guide:find-tracking-number).

**Números parecidos.** Algumas lojas dizem que um número `LP` viaja com a SunYou, mas essa forma é da logística do AliExpress: segue-o como uma [encomenda Cainiao](carrier:cainiao). Um número SY com só 10 algarismos, ou com mais de 11, não tem nenhuma das formas vistas no seguimento da SunYou.

**Um segundo número mais tarde.** Quando a encomenda sai do centro da SunYou na China, o seguimento pode juntar «Carrier Tracking Number:» com o número da transportadora local, o nome dela depois de «Distributor:» e o site dela. Esse número funciona no seguimento dessa transportadora.

## Estados SunYou: o que quer dizer cada um

O sypost.net mostra as leituras na língua que escolheres lá, entre 13, com o português. Esse português é o da própria SunYou e soa a brasileiro («retirada», «seu pacote»). Em inglês, pela ordem em que a encomenda costuma encontrá-las:

| A SunYou escreve | O que quer dizer para ti |
| --- | --- |
| `Pre-Shipment Info Sent To United Kingdom` | O vendedor registou a encomenda (aqui para o Reino Unido; o país muda). A SunYou ainda não a tem. |
| `Acceptance, Sent To United Kingdom` | A SunYou recebeu-a, na China. |
| `Departed Sunyou Facility` | Saiu do centro da SunYou, muitas vezes já com o número da transportadora local. |
| `Export Customs Clearance Successed` | Passou a alfândega de exportação, na China. |
| `Departed From Port Of Origin` | O avião ou o navio partiu. |
| `Arrived At GMTC` | Chegou ao centro de trânsito de correio global da SunYou, uma paragem pelo caminho. |
| `Arrived At Destination Country Airport` | Aterrou no país de destino. |
| `Customs Clearance In Process` | Está na alfândega de importação. |
| `Customs Clearance Successed` | Passou a alfândega. |
| `Hand Over To Last Mile` | A transportadora local já a tem. A partir daqui, segue o número dela. |
| `Delivery In Progress` | Saiu para entrega. |
| `Delivered` | Entregue. |

Na versão portuguesa do sypost.net, as mesmas linhas leem-se, por exemplo, `Chegada ao centro de transferência postal`, `Desembaraço aduaneiro em andamento`, `Recebido para entrega`, `Em entrega` e `Entregue`.

Algumas linhas mudam de encomenda para encomenda: `GMTC, Arrived` é o mesmo que `Arrived At GMTC`, e `Customs Clearance Was Completed` o mesmo que `Customs Clearance Successed`. `Delivered, At Mailbox` e `Delivered, Left At The Door` dizem onde a encomenda ficou: na caixa do correio ou à porta.

Duas coisas confundem. Primeiro, `Delivered` pode vir logo a seguir a `Hand Over To Last Mile`, ou até à linha do aeroporto, sem nada pelo meio: os passos que faltam estão no seguimento da transportadora local. Segundo, o resumo no topo da página tem etiquetas próprias. `Alert` (em português `Possível exceção`) quer dizer um possível problema, e `Expired` (`Transporte demorando muito`) quer dizer que a encomenda está em trânsito há demasiado tempo; nenhum dos dois é uma entrega falhada. `Undelivered` (`Falha na entrega`) é que quer dizer que a entrega falhou; o histórico pode dar o motivo, como `Delivery Failed, Reason: Incorrect Address`.

Na alfândega, as linhas a vigiar são `Customs Clearance Hold` e `Delivery failed, Reason: Refusal to pay VAT / customs duties`: vê [encomenda retida na alfândega](guide:customs).

## Quem entrega a SunYou em Portugal

A SunYou não entrega nada na Europa: a linha comercial dela passa as encomendas a transportadoras locais no país de destino, e o seguimento nomeia essa transportadora. Nos históricos lidos no seguimento dela, quem entregou foi a Royal Mail no Reino Unido, a [DHL Paket](carrier:dhl) na Alemanha, a USPS ou a GOFO nos Estados Unidos e a Canada Post no Canadá. A SunYou não diz quem entrega em Portugal, e os CTT não aparecem entre os parceiros que mostra no site. O histórico diz-te quem tem a tua depois de `Departed Sunyou Facility`, com o número dessa transportadora.

A SunYou não publica regras para tentativas de entrega, avisos, pontos de levantamento nem o tempo que uma encomenda fica à espera. São as regras da transportadora local: com o nome e o número dela, vê no site dela como pedir nova entrega ou onde a encomenda está à espera.

Algumas encomendas SunYou viajam como correio normal, que, diz a SunYou, só se segue até o correio do país de destino a receber. Nessas, o histórico pode parar quando esse correio fica com a encomenda.

O seguimento da SunYou tem ainda uma ligação «Proof of Delivery», que pede o número de telemóvel de quem recebe.

## SunYou não entrega: encomenda parada ou perdida

**Quem trata.** O cliente da SunYou é o vendedor: é ele que abre conta, regista a encomenda e tem o contrato. As páginas da SunYou não dão a quem recebe nenhuma forma de reclamar, por isso, com uma encomenda atrasada, perdida, danificada ou devolvida, fala com o vendedor ou com o marketplace onde compraste. As indemnizações da SunYou são para vendedores.

**Quanto tempo demora.** Os números que a SunYou dá aos vendedores são 4 a 5 dias desde a aceitação para alguns destinos principais na linha geral, entrega em 5 dias no melhor dos casos para os principais países da Europa e da América na linha comercial e 20 a 30 dias de calendário na linha económica para os Estados Unidos. Na lista de países da linha comercial, Portugal não aparece. São números de venda, não uma data para a tua encomenda.

**Sem novidades.** Se o histórico mostra um número de transportadora, vê primeiro o seguimento dela. Mais em [porque é que o tracking não atualiza](guide:tracking-not-updating).

**Perdida ou devolvida.** As linhas da SunYou para estes casos são `Unfortunately, Your Parcel Is Lost`, `Returned` e `Package Return, Reason: Incomplete Address Information` (em português `Lamentamos, seu pacote foi perdido`, `Devolvido` e `Pacote devolvido, motivo: endereço incompleto`). Qualquer uma é razão para falares com o vendedor.

## SunYou contacto: telefone e e-mail

A 12 de outubro de 2026, a SunYou indicava os mesmos canais para todos os países, sem escritório para quem recebe em Portugal:

- **Linha de apoio:** +86 4006075388, um número chinês. Não publica horário nem custo da chamada.
- **E-mail:** mto@sypost.com, a caixa de correio de seguimento.
- **Reclamações:** tousu@sypost.com, numa página de contactos em chinês.

Para tudo o que precise de uma ação, quem deves contactar é o vendedor e a transportadora local. A SunYou não publica nenhum aviso sobre mensagens de entrega falsas.

## Rastrear encomenda SunYou com o Peek

O [Peek](/) reconhece logo a SunYou nas duas formas SY, que nenhuma outra transportadora do catálogo partilha. Também podes colar uma ligação do sypost.net com o número, como a que dá o botão «Copy Link».

O Peek pergunta à SunYou em inglês, por isso as linhas aparecem em inglês, salvo as que conhece tal e qual, como `Delivered`, que mostra em português. Mostra o estado, o histórico (até 20 leituras, sem o local de cada uma), o país de destino, a hora da entrega e a transportadora local com o número dela («Entrega:» seguido do nome). Não mostra data prevista de entrega nem ponto de levantamento para encomendas SunYou. As horas são lidas no fuso que a SunYou lhes dá, o da China e depois o do país de destino, e mostradas no fuso horário do teu dispositivo.

Quando a SunYou dá o número da transportadora local e o Peek segue essa transportadora no país de destino, pergunta-lhe e passa para ela assim que mostra novidades mais recentes. A encomenda fica com o número SunYou, e a página dela mostra a transportadora que entrega e o número dela com uma ligação. Isto não acontece com todas as encomendas: a Meest, na Grécia, por exemplo, não é seguida.

Verifica de 10 em 10 minutos durante o dia, de 2 em 2 minutos quando a encomenda sai para entrega e de hora a hora ou de duas em duas horas à noite, e pode enviar-te notificações de todas as leituras, só das importantes ou «Só no dia da entrega». Não pode contactar a SunYou nem o vendedor por ti.

## Perguntas sobre o rastreamento SunYou

### O tracking SunYou é falso?

Não: a SunYou é uma empresa de logística real, fundada em Shenzhen em 2008, e o seguimento dela está no sypost.net. Se o teu número dá `Not Found` lá, pede ao vendedor que o confirme.

### O que quer dizer Departed From GMTC?

Quer dizer que a encomenda saiu do centro de trânsito de correio global da SunYou, uma paragem a caminho do país de destino. A seguir costuma vir a chegada ao aeroporto e a alfândega.

### O SunYou Post tem tradução em português?

Sim: escolhe Português no sypost.net e as leituras aparecem traduzidas pela SunYou, num português do Brasil. No Peek, aparecem em inglês, salvo algumas que o Peek conhece, como `Delivered`.

### Quanto tempo demora uma encomenda SunYou?

A SunYou fala aos vendedores de 4 a 5 dias para alguns destinos principais na linha geral e de 20 a 30 dias de calendário na linha para os Estados Unidos. Não é uma promessa para a tua encomenda: guia-te pela estimativa da loja ou do marketplace.

:::sources
- [SunYou: Sunyou Package Tracking](https://www.sypost.net/) – site de seguimento, línguas, regras do número, linha de apoio, e-mail de seguimento, etiquetas dos estados, prova de entrega, número da transportadora, leituras e transportadoras locais
- [SunYou: 联系我们 (contactos)](https://www.sypost.com/views/contact.html) – linha de apoio, e-mail, contacto para reclamações
- [SunYou: 顺邮宝专线 (linha geral)](https://www.sypost.com/views/services-1.html) – limites do seguimento do correio normal, prazos, indemnização a vendedores
- [SunYou: 顺速宝专线 (linha comercial)](https://www.sypost.com/views/services-2.html) – transportadoras locais no último troço, lista de países, prazos
- [SunYou: 顺速派专线 (linha económica para os Estados Unidos)](https://www.sypost.com/views/services-3.html) – 20 a 30 dias para os Estados Unidos
- [SunYou: 投寄说明 (instruções de envio)](https://www.sypost.com/views/document-3.html) – os vendedores registam as encomendas, seguimento no sypost.net
- [SunYou: 顺友简介 (apresentação)](https://www.sypost.com/views/about.html) – fundada em 2008, Shenzhen
- [SunYou: 合作伙伴 (parceiros, correios e transportadoras)](https://www.sypost.com/views/customer-2.html) – correios e transportadoras parceiros, sem os CTT
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – o que o Peek mostra, formas dos números
:::
