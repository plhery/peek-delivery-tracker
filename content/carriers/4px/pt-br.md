---
title: Rastreamento 4PX: códigos, status, alfândega e contato
description: Rastreie encomendas 4PX Express da China no Brasil: formato dos códigos 4PX e LP...CN, significado dos status em inglês, entrega local e suporte.
slug: rastreamento-4px
published: 2026-11-24
updated: 2026-11-24
---

A 4PX (*递四方速递*, *4PX Express Co., Ltd.*) é uma empresa chinesa de logística internacional sediada em Shenzhen que despacha encomendas para lojas online e as entrega a uma transportadora local no país de destino para a última milha. Você pode acompanhar o envio no site da própria 4PX, em [track.4px.com](https://track.4px.com/), ou colando o código na caixa acima. O número é informado pela loja ou marketplace nos detalhes de envio do pedido.

## Código de rastreamento 4PX: formatos comuns

A 4PX não publica exemplos oficiais de código; seu rastreador aceita de 5 a 50 letras e dígitos. Os formatos mais frequentes (com dígitos inventados) são:

| Código (inventado) | A quem pertence |
| --- | --- |
| `4PX4829173640581CN` | `4PX`, 13 dígitos e `CN`: código próprio da 4PX. |
| `LP4829173640581CN` | `LP`, 13 dígitos e `CN`: provável 4PX. |
| `LP48291736405812` | `LP` e 14 dígitos, sem `CN`: [Cainiao](carrier:cainiao) (referência logística do AliExpress). |
| `RR482917367GB` | [Código postal](guide:tracking-number-formats) terminado em sigla de país: correio daquele país (aqui, Royal Mail). |
| `YT4829173640581926` | Não é 4PX, e sim [YunExpress](carrier:yunexpress). |

O Peek também aceita letras minúsculas e espaços. A página de resultado da 4PX pode exibir dois números: o "4PX Order No." e o "Tracking No.", que pertence à transportadora local no país de destino. A loja pode informar esse número local em vez do código da 4PX, e o site da 4PX também responde a parte deles.

## Significado dos status de rastreamento da 4PX

As movimentações da 4PX costumam aparecer em inglês, e o portal permite traduzi-las para chinês simplificado ou manter o texto original. Na ordem habitual:

| A 4PX informa | O que significa para você |
| --- | --- |
| `Parcel information received` | A loja registrou o envio; a 4PX ainda não está com o pacote. |
| `4px received shipment.` | A 4PX recebeu a encomenda em seu armazém. |
| `Shipment arrived at facility and measured.` | Medido e conferido em uma unidade da 4PX. |
| `Hand over to airline.` | Entregue à companhia aérea para o voo internacional. |
| `Arrival to the destination airport` | Pousou no país de destino. |
| `Customs clearance agent received the shipment in destnation country.` | Na alfândega do país de destino (o erro de grafia `destnation` é da própria 4PX). |
| `Released from customs: customs cleared.` | Liberado pela alfândega; segue viagem. |
| `In transit, it's progressing through post network` | Já está na rede da transportadora ou correio local. |
| `Out for delivery` | Saiu para entrega com o entregador. |
| `Delivery attempt unsuccessful` | O entregador não conseguiu concluir a entrega. |
| `Delivered, in/at mailbox` | Entregue na caixa de correio. |

`Shipping information received`, `Waybill generated`, `Shipping label created` e `Advance notice - package information received.` têm o mesmo sentido da primeira linha. `Shipping label created, usps awaiting item` indica que existe etiqueta do USPS aguardando o objeto físico.

Acima das movimentações, a 4PX atribui um rótulo de resumo a cada pacote: `receive forecast` (pré-aviso antes de receber o pacote), `In Transit` (do armazém até a última milha), `Arrived for Pickup` (aguardando retirada em um ponto de entrega), `Exception` (destinatário ausente, endereço errado, recusa, avaria, extravio, prazo vencido, atraso, tentativa falha ou problema aduaneiro), `Suspected return` (possível devolução pela transportadora local), `Trajectory stalled` (15 dias sem atualização) e `No Data Found` (número sem registro ou digitado incorretamente).

## Entrega final, retirada, tentativa falha e alfândega

A 4PX não realiza a entrega final na porta do destinatário: segundo o site, o pacote é repassado "à empresa de entrega do país de destino" (como Royal Mail no Reino Unido ou USPS nos EUA). Quando ocorre o repasse, o quadro "Terminal Service Provider" na página de resultado mostra o site e o contato dessa transportadora local.

Tentativas de entrega, avisos, reagendamento e prazo de guarda seguem as regras dessa transportadora local, pois a 4PX não publica regras próprias. O aviso de `Arrived for Pickup` recomenda retirar o pacote "o quanto antes para evitar devolução pelo provedor". Após `Delivery attempt unsuccessful`, use o "Tracking No." e os dados do quadro local para solicitar nova tentativa ou retirada. Em encomendas enviadas ao Brasil, a cobrança de tributos na importação segue o [Programa Remessa Conforme da Receita Federal](https://www.gov.br/receitafederal/pt-br/assuntos/aduana-e-comercio-exterior/manuais/remessas-postal-e-expressa/programa-remessa-conforme-o-que-e-como-funciona).

## Encomenda 4PX sem atualização, perdida ou devolvida

**Ainda sem histórico.** O site da 4PX informa: "There is currently no trace of your package. Please wait patiently or contact the merchant to inquire about the delivery status." `No Data Found` também pode significar código digitado errado.

**Parada há muitos dias.** Após 15 dias sem novas leituras, a 4PX marca o envio como `Trajectory stalled`, alertando para atraso, extravio ou ausência de leituras registradas. Consulte primeiro o "Tracking No." diretamente na transportadora local. Veja [por que o rastreamento para de atualizar](guide:tracking-not-updating).

**Consta como entregue, mas não chegou.** Comece acionando a transportadora local que executou a entrega e depois o vendedor; veja [entregue, mas não recebido](guide:delivered-not-received).

**Devolvida ou extraviada.** A 4PX não publica processo de indenização nem prazo de reclamação para compradores, descrevendo devoluções e sinistros como serviços contratados pelo vendedor. Procure a loja ou marketplace para pedir reembolso ou reenvio.

## Atendimento ao cliente e contato da 4PX

Na seção Help > Contact Us de [track.4px.com](https://track.4px.com/), a 4PX lista um único contato global:

- **Telefone:** `0755-23508000` (Shenzhen, China; `+86 755 2350 8000` do exterior, sem informar horários ou idiomas).
- **E-mail:** `track@4px.com`.
- **Endereço:** 4PX Express Co., Ltd., No.12 Building, FuYong e-cool, BaoAn District, ShenZhen, GuangDong, China.

A 4PX não lista contatos locais para destinatários no Brasil, Europa ou Estados Unidos. Para problemas do pedido, a loja é mais ágil; para a entrega final, acione a transportadora exibida em "Terminal Service Provider".

## Rastrear uma encomenda 4PX com o Peek

O [Peek](/) reconhece códigos `4PX…CN` imediatamente. Para códigos `LP` terminados em `CN`, o app exibe "Consultando serviços de rastreamento…" e depois "4PX está com esta encomenda." assim que a 4PX confirma; se ainda não houver resposta, selecione a transportadora tocando em "Trocar". Links de `track.4px.com` contendo `#/result/0/` e o código são identificados como 4PX independentemente do formato, inclusive para o "Tracking No." local. Pedidos com múltiplos volumes seguem pelos serviços globais de rastreamento.

O Peek lê a 4PX em inglês e exibe a etapa, o histórico com o local de cada leitura no mapa, a hora da entrega, o país de destino e a transportadora local com seu número. Quando essa parceira faz parte da lista do Peek (como o USPS), o acompanhamento passa para ela assim que registra progresso mais recente. `Out for delivery` e `Delivery attempt unsuccessful` aparecem traduzidos nas etapas do Peek; demais linhas mantêm o texto da 4PX. A 4PX não informa data prevista de entrega nem peso.

Os horários respeitam os fusos da 4PX (ou do local quando ausente) e são exibidos no relógio do seu dispositivo. Enquanto você mantém a encomenda aberta, ou no dia da entrega com alertas ativados, o Peek verifica atualizações até a cada 10 minutos — e até a cada 2 minutos a partir das 6h no horário local no dia em que sai para entrega, enquanto o rastreador próprio da 4PX responde; fora disso, checa de hora em hora. Pelos serviços de rastreamento, consulta de hora em hora, a cada 3 horas após um dia sem novidades e até a cada 15 minutos no dia da entrega enquanto o pacote estiver aberto ou com alertas ligados. Quando outra transportadora assume a entrega, o Peek segue o ritmo dela. Após dois dias sem novidades (ou 12 horas em "Etiqueta criada" ou "Recebida pela transportadora"), verifica de hora em hora. Você pode escolher alertas para "Cada movimentação", "Etapas importantes" ou "Só a entrega". O Peek não pode contatar a 4PX nem a loja em seu nome.

## Perguntas frequentes sobre o rastreamento 4PX

### Onde fica a sede da 4PX?

Em Shenzhen, na China: o portal de rastreamento indica a *4PX Express Co., Ltd.* no distrito de BaoAn, em Shenzhen. A empresa informa ter sido fundada em 2004.

### A 4PX é confiável?

Sim. A 4PX é uma operadora logística chinesa consolidada, com portal próprio de rastreio global e parque logístico inteligente em Dongguan em parceria com a [Cainiao](carrier:cainiao). Se o site mostrar `No Data Found`, confira se o código está correto ou aguarde as primeiras leituras com o vendedor.

### O que significa `Parcel information received` na 4PX?

Significa que o vendedor criou o envio e transmitiu os dados eletrônicos, mas a 4PX ainda não recebeu o pacote fisicamente. A etapa seguinte (como `4px received shipment.`) aparece quando o item chega ao armazém da 4PX.

### Quanto tempo a entrega da 4PX demora?

A 4PX não divulga prazos oficiais ao destinatário; considere a estimativa informada pela loja ou marketplace no pedido. Sem movimentações por 15 dias, a própria 4PX classifica o pacote como parado (`Trajectory stalled`).

:::sources
- [4PX: Global Order Tracking](https://track.4px.com/) – regras de código, rótulos de resumo, tradução, quadro Terminal Service Provider e seção de contato (`0755-23508000`, `track@4px.com`)
- [4PX: For Perfect X-border](https://www.4px.com/view/index.html) – visão geral da empresa e soluções para vendedores
- [4PX: Devoluções (退换货)](https://www.4px.com/view/return.html) – devoluções como serviço voltado a lojistas
- [4PX: Voo inaugural Chengdu–Londres](https://www.4px.com/view/newsCenterContentfive.html) – fundação em 2004
- [4PX: Parque logístico inteligente Cainiao e 4PX](https://www.4px.com/view/newsCenterContentsix.html) – parque conjunto em Dongguan
- [Receita Federal: Programa Remessa Conforme](https://www.gov.br/receitafederal/pt-br/assuntos/aduana-e-comercio-exterior/manuais/remessas-postal-e-expressa/programa-remessa-conforme-o-que-e-como-funciona) – regras tributárias para importações ao Brasil
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – comportamento da 4PX no Peek e mapeamento de status
:::
