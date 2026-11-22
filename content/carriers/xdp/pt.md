---
title: Seguimento XDP: números de expedição, estados e entrega
description: Acompanha uma encomenda XDP no Reino Unido: onde obter o número de expedição, porque é que a XDP pede o código postal, estados e contactos de apoio.
slug: seguimento-xdp
published: 2026-11-22
updated: 2026-11-22
---

A XDP (XDP Express) é uma rede britânica de encomendas e carga que entrega artigos grandes e pesados para lojas: móveis, colchões, equipamento de jardim e de ginásio, muitas vezes com um ou dois motoristas. Acompanha uma encomenda na [página de seguimento da XDP](https://xdp.co.uk/track/) ou na app XDP com o número de expedição (*consignment number*) e o código postal de entrega, ou cola o número na caixa acima. O número vem da loja que envia o artigo, não da XDP.

## Número de seguimento XDP: o número de expedição

A XDP chama-lhe *consignment number*. A página de seguimento explica-o claramente: recebes o número da empresa que envia o artigo via XDP, por isso procura no e-mail de envio da loja ou na página do pedido. Os próprios SMS e e-mails da XDP chegam mais tarde, quando a encomenda já segue para ti.

A XDP não publica um formato; os números têm este aspeto:

| Formato (inventado) | O que é |
| --- | --- |
| `ZDBKLM482917` | `ZDB`, três letras e seis dígitos: um número de expedição XDP. |
| `0ZDBKLM482917` | O mesmo número com um `0` à frente, que pode ser omitido. |

O rastreador da XDP pede também o código postal de entrega ou recolha. Um código postal errado e um número mal escrito devolvem a mesma resposta (`Consignment not found for consignment number`), pelo que deves confirmar ambos.

Nenhuma outra transportadora britânica usa a forma `ZDB`. Um número [DPD UK](carrier:dpd-uk) tem 14 dígitos e uma guia APC Overnight tem 22 dígitos: se o teu e-mail indicar uma delas, acompanha lá. Mais em [formatos de números de seguimento](guide:tracking-number-formats).

## Significado dos estados de seguimento da XDP

O rastreador, a app e as perguntas frequentes da XDP existem apenas em inglês. Os números de rota e horários variam de encomenda para encomenda.

| Estado da XDP | O que significa para ti |
| --- | --- |
| `Order received` | A loja enviou os dados do pedido à XDP, mas a XDP ainda não tem o artigo. |
| `At Collection Depot` | A XDP tem o artigo e segue para o depósito de entrega. |
| `At Delivery Depot` | Chegou ao depósito que fará a entrega. |
| `Allocated to Delivery Vehicle - Route 03` | Atribuída a uma rota de entrega. |
| `Scheduled for Delivery - Route 11 - ETA: 10:51am to 12:51pm` | Deve chegar hoje, dentro desta janela de duas horas. |
| `Next stop: On time` | És a próxima paragem do motorista. A XDP mostra `On time`, `Late by …` ou `Early by …` face à janela; o Peek acrescenta `Next stop:`. |
| `Delivered` | Entregue. |
| `Carded` | O motorista tentou, mas não estava ninguém para receber. |
| `Consignment Split` | O depósito recebeu apenas parte do teu pedido da loja. |
| `Check Address` | O motorista não encontra a morada e precisa de mais dados. |
| `Service Fail` | Algo fora do controlo da XDP (avaria, trânsito ou mau tempo) causou atraso. |
| `Return` | Devolvida ao remetente: contacta a loja. |

A app XDP redige alguns estados de forma diferente (`Delivery scheduled`). Após `Consignment Split`, a XDP diz que os restantes artigos seguem nos dias seguintes. `RETURN LABEL CREATED` inicia o regresso ao remetente e `Damaged` indica que o artigo aparenta estar danificado (ver abaixo).

## Entrega XDP: janelas horárias, falhas e levantamento

A XDP envia uma janela de entrega de duas horas no próprio dia assim que o motorista carrega a carrinha, geralmente até ao meio-dia (hora do Reino Unido). Em períodos movimentados, um depósito pode planear com 24 horas ou mais de antecedência. Segue-se um aviso «You Are Next» por SMS, e-mail e app. Para entregas com duas pessoas (2Man), a XDP contacta primeiro para definir a data e envia a janela de duas horas no dia. Nos serviços 1+ e 2Man o motorista pode levar o artigo para a divisão que escolheres, após assinares um termo de responsabilidade enviado antes pela XDP.

Na app XDP podes:

- escolher **Change Delivery Date** para reagendar a entrega ou, em certos casos, escolher data;
- adicionar instruções de entrega, como deixar com um vizinho ou num alpendre, anexo, garagem ou atrás de um portão;
- deixar notas para o motorista e ver o comprovativo de entrega.

A XDP alerta que ao pedir «Left Safe» assumes total responsabilidade pelo artigo a partir do momento da entrega.

**Entregas falhadas.** `Carded` significa que o motorista não encontrou ninguém. A XDP indica fazer três novas tentativas de entrega antes de devolver ao remetente. Se não foi deixado cartão, o motorista não volta no mesmo dia: reagenda em Change Delivery Date.

**Sem levantamento em depósito.** A app da XDP refere não oferecer levantamento nos seus depósitos ou centros. As perguntas frequentes encaminham para a loja se quiseres levantar.

## Encomenda XDP não entregue, atrasada ou danificada

A XDP trabalha para o remetente, não para ti. Em qualquer problema recomenda seguir primeiro o artigo na app ou site e depois contactar o fornecedor (a loja), que detém o contrato e apresenta reclamações.

**Atrasada ou sem atualizar.** A XDP não garante entrega no dia seguinte, menos ainda em picos como feriados, Black Friday e Cyber Monday. Motoristas presos no trânsito podem nem sempre atualizar a janela horária. Vê [seguimento sem atualizações](guide:tracking-not-updating).

**Danificada.** A XDP pede para aceitares a entrega em vez de a recusares e contactares de imediato o remetente. Anota o dano no comprovativo de entrega ao assinar: o guia de procedimentos da XDP para remetentes diz que uma reclamação é recusada se a assinatura não registar anomalia, e observações como «unchecked» ou «unseen» não contam.

**Perdida.** Os prazos do remetente junto da XDP são curtos: 33 dias desde a expedição para perda total ou parcial e 7 dias desde a entrega para danos, fins de semana incluídos. Avisa rapidamente a loja. Se constar `Delivered` sem nada recebido, vê [entregue mas não recebida](guide:delivered-not-received).

**Devolvida.** Após `Return`, a XDP aconselha contactar o fornecedor.

## Contacto e apoio ao cliente da XDP

Canais XDP para destinatários:

- **Formulário Delivery Query** na [página de contacto da XDP](https://xdp.co.uk/contact/). O formulário «New Business Enquiry» destina-se apenas a vendas.
- **E-mail:** trackingupdates@xdp.co.uk.
- **Chat ao vivo** em `xdp.co.uk` e na app XDP; a XDP não publica horários.
- **Formulário de contacto na app**, pedindo nome, código postal, e-mail e, opcionalmente, número de expedição.

O site da XDP não indica telefone. A app menciona chamadas de valor acrescentado a 7 pence por minuto sem dizer qual o número; números listados noutros sites não são publicados pela XDP.

## Seguir encomendas XDP com o Peek

O [Peek](/) reconhece logo um número `ZDB` (com ou sem `0` inicial) como da XDP. Links de `xdp.co.uk` (`/track/` seguido do número ou `track.php?c=`) também funcionam, embora um código postal no link não seja aproveitado.

O Peek mostra um campo opcional «Código postal de entrega» para a XDP. Sem código postal não consulta a XDP e acompanha a encomenda apenas por [serviços universais de seguimento](guide:universal-tracker). Com ele, o Peek lê diretamente a página da XDP e envia o código postal à XDP e, se preciso, aos serviços de seguimento. A XDP responde a um código postal errado tal como a um número desconhecido: verifica ambos.

As horas da XDP não têm fuso horário: o Peek lê-as na hora do Reino Unido e mostra-as no teu fuso horário («Horas no teu fuso horário:»). Mostra o histórico XDP (retirando linhas de e-mail/SMS e registos repetidos de vários volumes) e pode mostrar o peso, deixando de fora códigos de depósito ou motorista, remetente, referências e número de peças. Não há dia previsto, locais por registo nem campo separado de janela horária (exceto no texto de registos como `Scheduled for Delivery - Route 11 - ETA: 10:51am to 12:51pm`).

Com código postal, enquanto mantiveres a encomenda aberta no Peek — ou no dia da entrega com as notificações ligadas — o Peek consulta diretamente a XDP até de 10 em 10 minutos, e até de 2 em 2 minutos a partir das 6:00 (hora do Reino Unido) no dia em que sai para distribuição, enquanto o rastreador da XDP responder; fora disso, e após dois dias sem novidades (12 horas enquanto continuar em «Etiqueta criada» ou «Aceite pela transportadora»), de hora em hora. Sem código postal, só através dos serviços universais, verifica de hora em hora, de 3 em 3 horas após um dia sem registos e até de 15 em 15 minutos no dia da entrega se a encomenda estiver aberta no Peek ou as notificações estiverem ligadas. Em ambos os casos, após sete dias sem novidades em «Etiqueta criada», verifica de 6 em 6 horas. Após quatro dias sem novos registos numa encomenda em «Etiqueta criada», «Aceite pela transportadora» ou «Em trânsito», o Peek mostra a data do último registo («Sem atualizações desde …») e «É habitual uma encomenda ficar sem novidades em voos longos, na alfândega ou ao fim de semana. O Peek continua a perguntar a XDP.», com a ligação «Pede ajuda a XDP» para a página de contactos. Podes escolher alertas para «Todos os registos», «Passos importantes» ou «Só a entrega».

## Perguntas sobre o seguimento XDP

### Porque é que o meu seguimento XDP não funciona?

Geralmente devido ao código postal: o rastreador da XDP exige o número de expedição e o código postal de entrega ou recolha, e um código errado mostra «Consignment not found». Confirma ambos no e-mail da loja.

### Quanto tempo demora a XDP a entregar?

A XDP indica aos remetentes 1 a 3 dias para o serviço 1Man e 3 a 5 dias para 1+ e 2Man, sujeito a transporte marítimo em ilhas. O dia de entrega traz uma janela de duas horas.

### Posso levantar a minha encomenda num depósito XDP?

Não. A app XDP refere não oferecer levantamento nos seus depósitos ou centros, e as perguntas frequentes encaminham para a loja. Podes reagendar a entrega ou pedir entrega a um vizinho.

### A XDP é uma empresa de entregas real?

Sim. A X D P Limited está registada na Companies House desde 1995, com sede em Curdworth, perto de Sutton Coldfield, e entrega na Grã-Bretanha continental, Irlanda do Norte, Ilhas do Canal e Ilha de Wight.

:::sources
- [XDP: Track](https://xdp.co.uk/track/) – número de expedição pelo remetente, código postal, avisos do rastreador
- [XDP: FAQs](https://xdp.co.uk/faqs/) – significado dos estados, contactar o fornecedor, levantamento em depósito, reagendamento na app
- [XDP: Get In Touch](https://xdp.co.uk/contact/) – formulário Delivery Query, e-mails
- [XDP: About Us](https://xdp.co.uk/about-us/) – fundada em 1995, cobertura, janela de duas horas, prova de entrega
- [XDP: Home](https://xdp.co.uk/) – carga grande e fora de padrão, serviços, app
- [XDP: 1Man Delivery](https://xdp.co.uk/services/1man-delivery/) – 1 a 3 dias, notificações, telefone proxy
- [XDP: 1+ Delivery](https://xdp.co.uk/services/1-plus-delivery-service/) – 3 a 5 dias, divisão à escolha, You Are Next, chat e e-mail
- [XDP: 2Man Delivery](https://xdp.co.uk/services/2-man-delivery/) – data pré-marcada, janela de duas horas
- [XDP: App (web version)](https://app.xdp.co.uk/) – janela de entrega, três tentativas, Change Delivery Date, left safe, sem levantamento em depósito, danos, devoluções, chamadas, formulário de contacto
- [XDP: Terms and Conditions of Carriage](https://xdp.co.uk/wp-content/uploads/2024/02/XDP-TCFinal.2.24.V1.pdf) – sem garantia dia seguinte, épocas de pico, prazos de reclamação
- [XDP: Procedure Guide](https://xdp.co.uk/wp-content/uploads/2026/03/Procedure-Guide-V.5.pdf) – 33 e 7 dias, anotar danos no comprovativo de entrega
- [XDP: Tracking (track.php)](https://www.xdp.co.uk/track.php) – código postal e avisos de expedição não encontrada
- [Companies House: X D P LIMITED](https://find-and-update.company-information.service.gov.uk/company/03139661) – registo, sede
- [Apple App Store: XDP](https://apps.apple.com/gb/app/xdp/id1485292140) – a app XDP
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – o que o Peek lê
:::
