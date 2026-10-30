---
title: 4PX rastreio: números, estados e contacto em Portugal
description: Rastreio 4PX em Portugal: como é um número 4PX, o que quer dizer cada estado, quem faz a entrega final e a quem perguntar quando a encomenda parece parada.
slug: 4px-rastreio
published: 2026-10-11
updated: 2026-10-27
---

A 4PX (递四方速递, 4PX Express Co., Ltd.) é uma empresa de logística transfronteiriça de Shenzhen, na China: envia para o estrangeiro as encomendas de lojas online e entrega-as a uma transportadora do país de destino, que faz a última parte do caminho. Podes seguir o número no site da 4PX, [track.4px.com](https://track.4px.com/), ou colá-lo na caixa no topo desta página. O número vem da loja ou da plataforma onde compraste, nos dados de envio da encomenda.

## Número de rastreio 4PX: como é

A 4PX não publica nenhum número de exemplo. O site aceita de 5 a 50 letras e algarismos, até 100 números de uma vez, um por linha. As formas mais comuns, com algarismos inventados:

| Número | De quem é |
| --- | --- |
| `4PX1234567890123CN` | 4PX, 13 algarismos e CN: o número da própria 4PX. |
| `LP1234567890123CN` | LP, 13 algarismos e CN: muito provavelmente 4PX. |
| `LP12345678901234` | LP e 14 algarismos, sem CN: costuma ser da [Cainiao](carrier:cainiao), a logística do AliExpress, e não da 4PX. |
| `RR000000005CN` | Um [número postal](guide:tracking-number-formats) com o código do país no fim, aqui da China Post. |
| `YT1234567890123456` | Não é 4PX, é da [YunExpress](carrier:yunexpress). |

O Peek aceita também minúsculas e espaços. No resultado, a 4PX pode mostrar dois números: o `Rastreamento No. 4px`, que é o dela, e o `Numero de rastreamento`, que é o da transportadora que entrega no teu país (assim mesmo, sem acento, no site em português). A loja pode dar-te esse número local em vez do da 4PX; o site da 4PX responde a alguns deles também. As encomendas com vários volumes aparecem divididas em subencomendas.

## Estados do rastreio 4PX: o que quer dizer cada um

O site da 4PX tem versão em português, mas as leituras ficam no texto original, quase sempre em inglês: o botão de tradução só oferece inglês, chinês simplificado ou o texto original. As linhas que mais se veem, pela ordem em que a encomenda as encontra:

| A 4PX escreve | O que quer dizer para ti |
| --- | --- |
| `Parcel information received` | O vendedor criou o envio. A 4PX ainda não tem a encomenda. |
| `4px received shipment.` | A encomenda chegou ao armazém da 4PX. |
| `Shipment arrived at facility and measured.` | Foi medida num centro da 4PX. |
| `Hand over to airline.` | Entregue à companhia aérea para o voo. |
| `Arrival to the destination airport` | Aterrou no país de destino. |
| `Customs clearance agent received the shipment in destnation country.` | Na alfândega do país de destino (o erro de escrita é da 4PX). |
| `Released from customs: customs cleared.` | Saiu da alfândega e segue viagem. |
| `In transit, it's progressing through post network` | A transportadora local já a tem. |
| `Out for delivery` | Saiu para entrega. |
| `Delivery attempt unsuccessful` | O estafeta não a conseguiu entregar. |
| `Delivered, in/at mailbox` | Entregue na tua caixa do correio. |

`Shipping information received`, `Waybill generated`, `Shipping label created` e `Advance notice - package information received.` querem dizer o mesmo que a primeira linha. `Shipping label created, usps awaiting item` quer dizer que há uma etiqueta dos USPS, mas os USPS ainda não leram a encomenda.

Por cima das leituras, o site arruma cada encomenda num separador, com nomes traduzidos à letra e às vezes enganadores:

- `Aceitar o pedido`: não quer dizer que alguém aceitou o pedido, mas que chegou o pré-aviso. A 4PX sabe do envio, ainda sem a encomenda.
- `Em trânsito`: tudo desde o armazém até à entrega, alfândega incluída.
- `Aguardando retirada`: espera por ti num ponto de entrega.
- `Exceção`: destinatário incontactável, morada errada, recusa, dano, perda, prazo de levantamento ultrapassado, atraso, primeira tentativa falhada ou problemas na alfândega.
- `Suspeita de devolução`: a transportadora local pode devolvê-la.
- `Rastreamento interrompido`: 15 dias sem nenhuma atualização.
- `Sem dados`: a 4PX não encontra nada com esse número. Pode estar mal escrito ou ainda não ter atualizações.

## Entrega 4PX: tentativa falhada e levantamento

A 4PX não entrega à porta. O site diz que a encomenda «is being delivered to the delivery company of the destination country», ou seja, passa para uma transportadora do país de destino, como a Royal Mail no Reino Unido ou os USPS nos Estados Unidos. A 4PX não diz qual entrega em Portugal. A partir daí, a caixa «Terminal Service Provider» do resultado (assim chamada no site em inglês) mostra os contactos e o site dessa transportadora.

Tentativas, avisos, nova data de entrega e quanto tempo a encomenda fica guardada são regras da transportadora local, não da 4PX, que não publica nenhuma. No separador `Aguardando retirada`, a 4PX pede que a levantes «as soon as possible», para não ser devolvida. Depois de `Delivery attempt unsuccessful`, usa o número de rastreamento local e os contactos dessa caixa para pedir nova entrega ou saber onde a levantar.

## Encomenda 4PX parada, perdida ou devolvida

**Ainda sem seguimento.** O site diz que ainda não há rasto da encomenda e pede que esperes ou que entres em contacto com o comerciante, ou seja, a loja. `Sem dados` também pode ser um número mal escrito.

**Parada há dias.** Ao fim de 15 dias sem leituras, a 4PX passa a encomenda para `Rastreamento interrompido` e diz que pode estar perdida ou atrasada, ou que uma transportadora não registou as leituras. Vê primeiro o seguimento da transportadora local com o número dela. Mais em [porque é que o tracking não atualiza](guide:tracking-not-updating).

**Entregue, mas não a tens.** No separador `Entrega efetuada`, a 4PX pede que contactes o serviço de clientes para confirmar. Começa pela transportadora local, que fez a entrega, e vê [encomenda dada como entregue mas não recebi](guide:delivered-not-received).

**Devolvida ou perdida.** A 4PX não publica nenhum procedimento nem prazo de reclamação para quem recebe, e os seus sites tratam devoluções e reclamações como serviços para vendedores. É a loja ou a plataforma que tem contrato com a 4PX: pede-lhe o reembolso ou um novo envio, e fala com a transportadora local sobre o que correu mal na entrega.

## 4PX contacto: telefone e e-mail

A 11 de outubro de 2026, o site de seguimento da 4PX dava um só contacto para todos, em Help, Contact Us:

- **Telefone:** 0755-23508000, um número de Shenzhen, na China (+86 755 2350 8000 a partir de Portugal). O site não indica horário nem idiomas.
- **E-mail:** track@4px.com.
- **Morada:** 4PX Express Co., Ltd., No.12 Building, FuYong e-cool, BaoAn District, ShenZhen, GuangDong, China.

A 4PX não publica nenhum contacto para quem recebe em Portugal nem noutro país. Para tudo o que tenha a ver com a compra, a loja responde mais depressa; para a entrega final, usa a transportadora indicada na caixa «Terminal Service Provider».

## Rastrear encomenda 4PX com o Peek

O [Peek](/) reconhece logo um número `4PX…CN`. Com um número LP terminado em CN, mostra «A verificar serviços de seguimento…» e depois «4PX tem esta encomenda.» quando a 4PX o conhece; se não, escolhe tu a transportadora em «Alterar». Uma ligação do track.4px.com com `#/result/0/` e o número é lida como 4PX seja qual for o número, por isso serve também para o número local. As encomendas com vários volumes vão para serviços universais de seguimento.

O Peek lê a 4PX em inglês e mostra o estado, o histórico com o local de cada leitura num mapa, a hora de entrega, o país de destino e, quando a reconhece na sua lista (os USPS, por exemplo), a transportadora local com o número dela. A encomenda pode então passar a ser seguida aí, depois de lá aparecerem leituras mais recentes. `Out for delivery` e `Delivery attempt unsuccessful` aparecem com as palavras do Peek; o resto, como a 4PX escreve. O Peek não mostra data de entrega prevista, dados do ponto de levantamento nem peso.

As horas são lidas nos fusos horários da 4PX (as de uma leitura sem fuso, na hora do local onde foi feita) e mostradas no fuso horário do teu dispositivo. O Peek verifica de 10 em 10 minutos durante o dia, de 2 em 2 quando a encomenda sai para entrega e de hora a hora ou de duas em duas horas à noite, e pode enviar-te notificações de todas as leituras, só dos passos importantes ou «Só a entrega». Não pode contactar a 4PX nem a loja por ti.

## Perguntas sobre o rastreio 4PX

### O rastreio da 4PX é fake?

Não: a 4PX é uma empresa chinesa de logística, com site de seguimento próprio e, com a Cainiao, um parque logístico em Dongguan. Se o site mostrar `Sem dados`, a 4PX diz que o número pode estar errado ou ainda sem atualizações: confirma-o e pergunta à loja.

### Quem entrega as encomendas da 4PX em Portugal?

A 4PX não o diz: só explica que passa a encomenda a uma transportadora do país de destino. Quando isso acontece, a caixa «Terminal Service Provider» no resultado mostra qual é, com o número de rastreamento e os contactos dela.

### Quanto tempo demora a entrega da 4PX?

A 4PX não publica prazos de entrega para quem recebe, por isso vale o prazo que a loja ou a plataforma mostra na tua encomenda. Se nada mudar em 15 dias, a 4PX trata a encomenda como parada.

### Onde fica a 4PX?

Em Shenzhen, na China: o site de seguimento dá a morada da 4PX Express Co., Ltd. no distrito de BaoAn. A 4PX diz que foi fundada em 2004.

:::sources
- [4PX: Plataforma de monitoramento global](https://track.4px.com/) – regras do número, separadores, tradução, caixa Terminal Service Provider
- [4PX: ficheiro de idiomas do site de seguimento](https://track.4px.com/js/index.6aecf0a2c58f0d7c56a3.js) – textos em português, passagem à transportadora local, mensagem sem rasto
- [4PX: Contact Us](https://track.4px.com/#/help/contact) – telefone, e-mail, morada
- [4PX: Privacy Policy](https://track.4px.com/#/help/privacy) – 4PX Group, reclamações
- [4PX: For Perfect X-border](https://www.4px.com/view/index.html) – empresa e serviços para vendedores
- [4PX: 退换货 (devoluções)](https://www.4px.com/view/return.html) – devoluções como serviço para vendedores
- [4PX: 递四方成都至伦敦全货机航线成功首航](https://www.4px.com/view/newsCenterContentfive.html) – fundada em 2004
- [4PX: 菜鸟&递四方国际智慧物流园盛大开园](https://www.4px.com/view/newsCenterContentsix.html) – parque logístico da Cainiao e da 4PX
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – o que o Peek mostra
:::
