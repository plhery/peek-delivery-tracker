---
title: Rastreamento XDP: códigos, status e entregas no Reino Unido
description: Rastreie encomendas da XDP no Reino Unido: onde achar o número da remessa, por que a XDP pede o código postal, significado dos status e como contatar.
slug: xdp-tracking
published: 2026-11-26
updated: 2026-11-26
---

A XDP (XDP Express) é uma rede britânica de encomendas e cargas que entrega volumes grandes e pesados para lojas — móveis, colchões, itens de jardim e equipamentos de academia — frequentemente com um ou dois motoristas. Rastreie na [página de rastreamento da XDP](https://xdp.co.uk/track/) ou no app da XDP usando o número da remessa (consignment number) e o código postal de entrega, ou cole o código na caixa acima. O número é fornecido pela loja que enviou o item, não pela XDP.

## Código de rastreamento XDP: o número da remessa

A XDP chama o código de número da remessa (consignment number). Sua página de rastreamento explica claramente que você o recebe da empresa que está enviando o produto via XDP, portanto procure no e-mail de envio da loja ou na página do pedido. Os SMS e e-mails da própria XDP só chegam depois, quando o volume já está a caminho.

A XDP não publica um formato; seus códigos têm esta aparência:

| Formato (inventado) | O que é |
| --- | --- |
| `ZDBKRT482917` | `ZDB`, três letras e seis dígitos: um número de remessa da XDP. |
| `0ZDBKRT482917` | O mesmo código com um `0` inicial, que pode ser omitido. |

O rastreador da XDP também solicita o código postal de entrega ou de coleta. Um código postal errado e um número digitado incorretamente devolvem a mesma resposta, `Consignment not found for consignment number`, então confira ambos.

Nenhuma outra transportadora no Reino Unido usa o padrão `ZDB`. Um código da [DPD UK](carrier:dpd-uk) tem 14 dígitos e um da [APC Overnight](carrier:apc-overnight) tem 22 dígitos; se o e-mail indicar uma delas, rastreie por lá. Veja mais em [formatos de código de rastreamento](guide:tracking-number-formats).

## O que significam os status de rastreamento da XDP

O rastreador, o app e o FAQ da XDP existem apenas em inglês. Números de rota e horários variam conforme o envio.

| Status da XDP | O que significa para você |
| --- | --- |
| `Order received` | A loja enviou os dados do pedido à XDP, mas a XDP ainda não está com o volume. |
| `At Collection Depot` | A XDP coletou o item e ele segue para o seu depósito de entrega. |
| `At Delivery Depot` | Chegou ao depósito que fará a entrega. |
| `Allocated to Delivery Vehicle - Route 03` | Foi alocado em uma rota de entrega. |
| `Scheduled for Delivery - Route 11 - ETA: 10:51am to 12:51pm` | Previsão de entrega hoje dentro dessa janela de duas horas. |
| `Next stop: On time` | Você é a próxima parada do motorista. A XDP exibe `On time`, `Late by …` ou `Early by …` em relação à janela; o Peek acrescenta `Next stop:`. |
| `Delivered` | Entregue. |
| `Carded` | O motorista tentou entregar, mas não havia ninguém para receber. |
| `Consignment Split` | O depósito recebeu da loja apenas parte do seu pedido. |
| `Check Address` | O motorista não localizou o endereço e precisa de mais detalhes. |
| `Service Fail` | Algo fora do controle da XDP — como pane mecânica, trânsito ou mau tempo — atrasou a entrega. |
| `Return` | Voltou ao remetente: contate a loja. |

O app da XDP usa algumas expressões diferentes (`Delivery scheduled`). Após `Consignment Split`, a XDP diz que os itens restantes chegam nos dias seguintes. `RETURN LABEL CREATED` inicia o retorno ao remetente e `Damaged` indica que o item parece danificado (veja abaixo).

## Entregas XDP: faixas de horário, entregas não realizadas e retirada

A XDP envia uma janela de duas horas no dia da entrega depois que o motorista carrega a van, geralmente até o meio-dia. Em períodos movimentados, um depósito pode planejar com 24 horas ou mais de antecedência. Depois chega uma mensagem "You Are Next" por SMS, e-mail e aplicativo. Para entregas com duas pessoas (2Man), a XDP combina primeiro a data e depois envia a janela de duas horas no dia. No serviço 1+, o motorista pode levar o item até um cômodo térreo de sua escolha depois que você assina um termo de isenção enviado previamente pela XDP; no 2Man, sobe até um lance interno de escadas (equipamentos de academia somente no térreo).

No aplicativo da XDP você pode:

- escolher **Change Delivery Date** para reagendar a entrega ou, em certos casos, escolher uma data;
- incluir instruções de entrega, como deixar com um vizinho ou na varanda, galpão, garagem ou atrás do portão;
- deixar recados para o motorista e ver o comprovante de entrega.

A XDP avisa que, ao solicitar entrega "Left Safe" (deixar em local seguro), você assume total responsabilidade pelo item a partir do momento em que é deixado.

**Entregas não realizadas.** `Carded` significa que o motorista não encontrou ninguém. A XDP informa que fará novas tentativas três vezes antes de devolver ao remetente. Se não houve cartão deixado, o motorista não consegue voltar no mesmo dia: reagende via Change Delivery Date.

**Sem retirada em depósito.** O app da XDP afirma que não oferece retirada em seus depósitos ou hubs. O FAQ orienta falar com a loja se quiser retirar.

## Encomenda XDP não entregue, atrasada ou danificada

A XDP trabalha para o remetente, não para você. Em qualquer problema de entrega, a orientação da XDP é verificar o rastreamento no app ou no site e depois contatar o fornecedor (a loja) para qualquer providência: o contrato é da loja, e é ela quem faz eventuais pedidos de indenização.

**Atrasada ou sem atualização.** A XDP não garante entrega no dia seguinte, especialmente em picos como feriados bancários, Black Friday e Cyber Monday. Motoristas presos no trânsito nem sempre atualizam se a janela mudar. Veja [por que o rastreamento para de atualizar](guide:tracking-not-updating).

**Danificada.** A XDP pede que você aceite a entrega em vez de recusá-la e fale imediatamente com o remetente. Anote o dano no comprovante de entrega ao assinar: o guia de procedimentos da XDP para remetentes determina que pedidos de indenização são negados se a assinatura não registrar ressalva específica, e anotações genéricas como "unchecked" ou "unseen" não valem.

**Extraviada.** Os prazos do remetente junto à XDP são curtos: **33 dias** a partir do despacho para pedir indenização por extravio total ou parcial e **7 dias** da entrega em caso de dano, incluindo fins de semana. Avise a loja depressa. Se constar `Delivered` sem você ter recebido, veja [entregue mas não recebida](guide:delivered-not-received).

**Devolvida.** Após `Return`, a XDP orienta falar com o fornecedor.

## Contato e atendimento da XDP

Canais da XDP para destinatários:

- **Formulário Delivery Query** na [página de contato da XDP](https://xdp.co.uk/contact/) (o formulário "New Business Enquiry" ali é apenas comercial).
- **E-mail:** `trackingupdates@xdp.co.uk`.
- **Chat ao vivo** em xdp.co.uk e no app da XDP; a XDP não publica horários.
- **Formulário no aplicativo**, pedindo nome, código postal, e-mail e, opcionalmente, o número da remessa.

O site da XDP não informa telefone. O aplicativo menciona ligações com tarifa especial de 7p por minuto sem indicar qual número; telefones listados em sites de terceiros não são publicados pela XDP.

## Acompanhe encomendas da XDP com o Peek

O [Peek](/) reconhece na hora códigos iniciados por `ZDB` como da XDP. Links de xdp.co.uk (`/track/` seguido do código, ou `track.php?c=`) também funcionam, embora qualquer código postal incluído no link não seja aproveitado.

O Peek exibe um campo opcional "Código postal de entrega ou coleta" para a XDP. Sem código postal, a XDP não é consultada e o pacote é acompanhado apenas por [serviços universais de rastreamento](guide:universal-tracker). Com o código postal preenchido, o Peek lê a página de rastreamento da XDP e envia o código postal à XDP e, quando necessário, aos serviços de rastreamento. A XDP responde a um código postal errado da mesma forma que a um número desconhecido, então confira os dois.

As movimentações da XDP não trazem fuso horário: o Peek interpreta tudo no horário do Reino Unido e exibe o histórico próprio da XDP. Os status mantêm o texto em inglês da XDP, exceto `Delivered`, traduzido pelo Peek; o peso pode aparecer, e o Peek pode notificar você quando o status muda. Não há campo próprio para data prevista de entrega, locais das movimentações nem janela de horário, salvo quando aparecem no texto de uma linha como `Scheduled for Delivery - Route 11 - ETA: 10:51am to 12:51pm`. Com a encomenda aberta, ou no dia da entrega com notificações ligadas, o Peek consulta a XDP até a cada 10 minutos (até a cada 2 minutos a partir das 6h no horário do Reino Unido no dia em que sai para entrega, enquanto o próprio rastreador da XDP responder); nos demais casos, e após dois dias sem novidades (12 horas em "Etiqueta criada" ou "Recebida pela transportadora"), consulta de hora em hora. Sem código postal, apenas por serviços de rastreamento: a cada hora, a cada 3 horas após um dia sem novidades e até a cada 15 minutos no dia da entrega com o pacote aberto ou notificações ligadas. Em ambos os casos, a cada 6 horas depois de sete dias sem novidades em "Etiqueta criada".

## Perguntas sobre o rastreamento da XDP

### Por que meu rastreamento da XDP não funciona?

Geralmente pelo código postal: o rastreador da XDP exige tanto o número da remessa quanto o código postal de entrega ou coleta, e um código postal incorreto exibe "Consignment not found". Confira ambos no e-mail da loja.

### Quanto tempo a XDP leva para entregar?

A XDP informa aos remetentes prazo de 1 a 3 dias no serviço 1Man e de 3 a 5 dias nos serviços 1+ e 2Man, sujeito a transporte marítimo em ilhas. No dia da entrega há uma janela de duas horas.

### Posso retirar minha encomenda em um depósito da XDP?

Não. O aplicativo da XDP afirma que não há retirada em depósitos ou hubs, e o FAQ direciona você à loja. Você pode reagendar a entrega ou pedir entrega a um vizinho.

### A XDP é uma transportadora real?

Sim. A X D P Limited é registrada na Companies House desde 1995, com sede em Curdworth, perto de Sutton Coldfield, e entrega na Grã-Bretanha continental, Irlanda do Norte, Ilhas do Canal e Ilha de Wight.

:::sources
- [XDP: Track](https://xdp.co.uk/track/) – número da remessa vindo do remetente, código postal, avisos do rastreador
- [XDP: FAQs](https://xdp.co.uk/faqs/) – significado dos status, contato com o fornecedor, retirada no depósito, reagendamento no app
- [XDP: Get In Touch](https://xdp.co.uk/contact/) – formulário Delivery Query, e-mails
- [XDP: About Us](https://xdp.co.uk/about-us/) – fundada em 1995, cobertura, janela de duas horas, comprovante de entrega
- [XDP: Home](https://xdp.co.uk/) – cargas grandes e fora do padrão, serviços, app
- [XDP: 1Man Delivery](https://xdp.co.uk/services/1man-delivery/) – 1 a 3 dias, notificações, número intermediário
- [XDP: 1+ Delivery](https://xdp.co.uk/services/1-plus-delivery-service/) – 3 a 5 dias, cômodo à escolha, You Are Next, chat ao vivo e e-mail
- [XDP: 2Man Delivery](https://xdp.co.uk/services/2-man-delivery/) – data agendada, janela de duas horas
- [XDP: App (web version)](https://app.xdp.co.uk/) – janela de entrega, três tentativas, Change Delivery Date, Left Safe, sem retirada no depósito, danos, devoluções, ligações, formulário de contato
- [XDP: Terms and Conditions of Carriage](https://xdp.co.uk/wp-content/uploads/2024/02/XDP-TCFinal.2.24.V1.pdf) – sem garantia de entrega no dia seguinte, períodos de pico, prazos para pedidos de indenização
- [XDP: Procedure Guide](https://xdp.co.uk/wp-content/uploads/2026/03/Procedure-Guide-V.5.pdf) – 33 e 7 dias, anotar o dano no comprovante de entrega
- [XDP: Tracking (track.php)](https://www.xdp.co.uk/track.php) – avisos de código postal e de não encontrado
- [Companies House: X D P LIMITED](https://find-and-update.company-information.service.gov.uk/company/03139661) – registro, endereço
- [Apple App Store: XDP](https://apps.apple.com/gb/app/xdp/id1485292140) – o app da XDP
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – o que o Peek lê
:::
