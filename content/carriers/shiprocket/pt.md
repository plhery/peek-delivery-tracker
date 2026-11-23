---
title: Seguimento Shiprocket: números AWB, estados e RTO
description: Segue uma encomenda Shiprocket ou ShiprocketX: formato AWB, significado dos estados, entregas falhadas (NDR), RTO e contacto.
slug: seguimento-shiprocket
published: 2026-11-22
updated: 2026-11-22
---

A Shiprocket, sediada em Gurugram, é uma plataforma indiana de envios, não uma transportadora: lojas online reservam através dela transportadoras como Delhivery, Blue Dart ou Xpressbees, e a Shiprocket oferece ao comprador uma página de seguimento. A ShiprocketX é o seu serviço para encomendas enviadas da Índia para o estrangeiro. Segue a tua encomenda na [página de seguimento da Shiprocket](https://www.shiprocket.in/shipment-tracking/) com o AWB, ID de pedido ou telemóvel, na ligação `shiprocket.co` enviada pelo vendedor, ou na caixa acima. A Shiprocket refere enviar o AWB por e-mail e SMS assim que o pedido é confirmado.

## Número de seguimento Shiprocket

A Shiprocket chama AWB (*air waybill number*) ao código de seguimento. Na Índia é o próprio número da transportadora. Uma encomenda ShiprocketX viaja com o AWB da própria Shiprocket: 13 dígitos começados por `832`. A Shiprocket não publica formato oficial. Com dígitos inventados:

| Número (inventado) | O que é |
| --- | --- |
| `8324829103756` | `832` e mais 10 dígitos: um AWB ShiprocketX. |
| `7394028156342` | 13 ou 14 dígitos: AWB de uma transportadora nacional indiana, como Delhivery ou Xpressbees. |
| `74829103753` | 11 dígitos: formato de um AWB Blue Dart. |
| `FMPC4829103756` | Três letras, C, P ou R, e 10 dígitos: um AWB Ekart. |

**Onde o encontrar.** Procura no e-mail e no SMS enviados aquando da confirmação do pedido. O seguimento da Shiprocket também aceita o ID do pedido ou o telemóvel, verifica um código único (OTP) e abre o myShiprocket para compradores. Mais pistas em [como encontrar um número de seguimento](guide:find-tracking-number).

**Números semelhantes.** Como as guias da Delhivery também podem ter 13 dígitos, um número começado por `832` é provavelmente, mas não com certeza, da Shiprocket. Como o seguimento Shiprocket responde para qualquer transportadora reservada na plataforma, a ligação `shiprocket.co/tracking/` funciona independentemente da forma do código.

## Estados de seguimento Shiprocket explicados

A página mostra o estado, a transportadora ou serviço ao lado do AWB e os registos do mais recente para o mais antigo, com atividade, frequentemente localidade, dia, mês e hora, mas sem ano. A maioria das linhas traz o texto da transportadora: a nacional na Índia ou a do país de destino no estrangeiro. Linhas frequentes por ordem:

| A página Shiprocket indica | O que significa para ti |
| --- | --- |
| `Order Received` | O vendedor criou o pedido. Nenhuma transportadora tem ainda a encomenda. |
| `Manifested - Out for Pickup` | A transportadora está a caminho para recolher o volume ao vendedor. |
| `In Transit - Shipment picked up` | A transportadora recolheu-a. |
| `In Transit - Shipment Recieved at Origin Center` | No primeiro centro da transportadora (escrito assim na página). |
| `Dispatched - Delivering At Shiprocket Warehouse` | ShiprocketX: a transportadora leva o volume ao hub de exportação da Shiprocket, não a tua casa. |
| `Received At DC` | Chegou ao hub de exportação da Shiprocket. |
| `AWB Handed over to Flight` | Entregue à companhia aérea. |
| `CUSTOM CLEARED OVERSEAS` | Desalfandegada. |
| `Received by carrier` | A transportadora do teu país já tem a encomenda. |
| `RTO DELIVERED` | Devolvida ao vendedor. |

`Out For Pickup` e `PICKUP EMPLOYEE IS OUT TO P/U SHIPMENT` também indicam que ainda não foi recolhida. `Reached At Warehouse` e `Connection Aligned` são etapas no hub de exportação em Deli. `Customer has made payment or Provided ID/Tax information` é uma etapa aduaneira no estrangeiro (direitos pagos ou dados fiscais fornecidos; vê [encomendas retidas na alfândega](guide:customs)). Qualquer linha começada por `RTO` (*return to origin*) significa regresso ao vendedor.

**Encomenda mais antiga.** Ao fim de algum tempo, a página mostra apenas uma vista arquivada: último estado (`DELIVERED`, `FULFILLED` para pedido entregue e encerrado, ou `RTO DELIVERED`), dia, serviço e AWB com os últimos caracteres ocultos. O histórico completo só abre para o comprador após código enviado por telefone. Após cerca de um ano, a página responde `AWB Not Found`.

## Quem entrega encomendas Shiprocket

**Na Índia**, a transportadora reservada pelo vendedor. A Shiprocket diz trabalhar com 42 transportadoras parceiras, incluindo Delhivery, Blue Dart, Xpressbees, Ekart, DTDC, Shadowfax, India Post e Amazon Shipping, indicando que o prazo depende da transportadora, código PIN e serviço. A página mostra o serviço, como `Delhivery Surface`.

**No estrangeiro**, uma transportadora local leva a encomenda ShiprocketX ao hub de exportação em Deli; segue depois de avião, passa na alfândega e é entregue a uma transportadora no país de destino, mantendo o AWB Shiprocket. Em históricos lidos na Shiprocket, a USPS assumiu a entrega nos EUA. A página indica o serviço, como `SRX Economy` ou `SRX Premium`. A Shiprocket apresenta aos vendedores 12 a 15 dias úteis no serviço económico para EUA, Reino Unido, Canadá e outros países, e 10 a 12 dias em serviços com direitos pagos para os EUA — estimativas comerciais, não uma data garantida para o teu pedido. Nos menus chama também «ShipX» à ShiprocketX.

## Entrega falhada (NDR) e devolução (RTO)

Um NDR (*non-delivery report*) significa que a transportadora não conseguiu entregar. O vendedor vê-o minutos depois na Shiprocket e decide entre nova tentativa ou recolha de volta.

- **Mensagens.** Se o vendedor usar o fluxo NDR de comprador, uma encomenda marcada «customer requested future delivery» ou «customer refused the delivery» envia-te um SMS — e nalguns planos uma chamada automática — a pedir confirmação de entrega. O vendedor pode ainda enviar uma ligação por SMS para pedires nova tentativa e corrigires morada ou telefone.
- **Página de seguimento.** A Shiprocket indica que os compradores podem pedir lá nova tentativa e ver o motivo de cada tentativa.
- **Tentativa que não aconteceu.** Avisa o vendedor, que pode reportar uma «fake attempt» à Shiprocket com a tua confirmação.
- **Três tentativas.** As transportadoras fazem até três tentativas. Depois o pedido passa a RTO e regressa à morada de recolha ou armazém do vendedor.

Quando estiver a regressar, pede ao vendedor novo envio ou reembolso. Vê [entrega falhada](guide:missed-delivery) e [devolvida ao remetente](guide:returned-to-sender).

## Encomenda Shiprocket que não chega ou sem atualizações

**Sem alterações.** A Shiprocket diz que as transportadoras atualizam assim que registam o volume, o que pode demorar algumas horas; após mais de um dia sem novidades, contacta o vendedor. Enquanto mostrar apenas `Order Received` ou uma linha de recolha, a transportadora ainda não a levantou. Mais em [porque o seguimento para de atualizar](guide:tracking-not-updating).

**Quem trata.** O cliente da Shiprocket é o vendedor. À pergunta «Whom shall I contact if I have concerns?», a Shiprocket responde que deves contactar o vendedor onde compraste. Para perda, dano ou não entrega fala também com o vendedor: a cobertura ShiprocketX até 5000 INR aplica-se a vendedores. Vê [encomenda perdida](guide:lost-parcel) e [entregue mas não recebida](guide:delivered-not-received).

## Apoio ao cliente e contacto Shiprocket

Contactos divulgados pela Shiprocket:

- **Telefone:** 9643887070, 9711451060 e 8510042020 (números indianos, +91 do estrangeiro). O site de seguimento lista-os para questões de seguimento com chat ao vivo 24/7; a página de contacto indica os mesmos números das 9:00 às 21:00 (hora indiana).
- **E-mail:** support@shiprocket.in.
- **Escalonamento:** o apoio responde em 5 dias úteis. Sem solução, escreve para escalation@shiprocket.com e depois para grievance@shiprocket.com; cada um responde em 3 dias úteis.

A maior parte deste apoio dirige-se a vendedores. O site de seguimento avisa ainda: se uma mensagem de origem não verificada pedir pagamento, não respondas nem partilhes dados de pagamento; bloqueia e denuncia o remetente. Vê [SMS falsos de entrega](guide:delivery-scam-texts).

## Seguir encomendas Shiprocket com o Peek

Para um número de 13 dígitos começado por `832`, o [Peek](/) pergunta desde o início à Shiprocket (com a Delhivery se o teu país for a Índia) e, só se a Shiprocket não conhecer o número, a serviços de seguimento e a outras transportadoras: ao adicionar aparece «A verificar serviços de seguimento…» e depois «Shiprocket tem esta encomenda.» Como as guias Delhivery têm o mesmo comprimento, o Peek não escolhe Shiprocket só pela forma; se não responder, mostra «À espera do primeiro registo de Shiprocket» e continua a perguntar-lhe, e podes selecionar Shiprocket em «Alterar» (ou «Alterar transportadora»). Uma ligação `shiprocket.co/tracking/` identifica a Shiprocket qualquer que seja a forma do número.

O Peek lê a página de seguimento Shiprocket e mostra o estado, cada registo com localidade se existir, a estimativa de entrega da Shiprocket em trânsito e a hora de entrega para entregas na Índia. Registos no estrangeiro não trazem fuso horário: o Peek lê cada um na hora local do local (uma linha sem local, na de uma linha vizinha, por vezes a hora indiana) e converte para o teu fuso horário («Horas no teu fuso horário:»). As linhas mantêm o texto da página exceto correspondências exatas como `Delivered`. O Peek descarta vendedor, referência de pedido e morada do destinatário, não recebe peso nem comprovativo de entrega e não muda para a transportadora estrangeira.

Em encomendas antigas mostra o último estado arquivado com o respetivo dia e procura um histórico mais completo em serviços de seguimento. O Peek verifica até de 10 em 10 minutos, e de 2 em 2 minutos a partir das 6:00 no relógio do último registo (hora indiana na Índia) no dia em que entra «Em distribuição», enquanto a encomenda estiver aberta no Peek ou no dia da entrega com as notificações ligadas, quando a página Shiprocket responde. Após dois dias sem novidades (12 horas enquanto estiver em «Etiqueta criada» ou «Aceite pela transportadora»), verifica de hora a hora, e após sete dias sem novidades em «Etiqueta criada», de 6 em 6 horas. Nas notificações podes escolher «Todos os registos», «Passos importantes» ou «Só a entrega». Ao fim de quatro dias sem registo numa encomenda em «Etiqueta criada», «Aceite pela transportadora» ou «Em trânsito», o Peek mostra «Sem atualizações desde …» e lembra «É habitual uma encomenda ficar sem novidades em voos longos, na alfândega ou ao fim de semana. O Peek continua a perguntar a Shiprocket.» O Peek segue mais de 4500 transportadoras — incluindo [Delhivery](carrier:delhivery), [Blue Dart](carrier:blue-dart), Xpressbees, [Ekart](carrier:ekart), DTDC, Shadowfax e [India Post](carrier:india-post) pelos respetivos números —, mas não pode verificar-te como comprador, pedir nova tentativa nem contactar a Shiprocket ou o vendedor por ti.

## Perguntas sobre o seguimento Shiprocket

### A Shiprocket é uma empresa transportadora?

Não. A Shiprocket é uma plataforma de envios: os vendedores reservam lá transportadoras como Delhivery, Blue Dart ou Xpressbees, que transportam a encomenda. Os envios ShiprocketX seguem com o AWB da Shiprocket e são entregues por uma transportadora no país de destino.

### O que significa RTO no seguimento Shiprocket?

RTO significa *return to origin* (devolução à origem): a encomenda não pôde ser entregue após até três tentativas — ou antes a pedido do vendedor — e regressa ao vendedor. `RTO DELIVERED` indica que o vendedor já a recebeu de volta.

### Qual é o número de apoio ao cliente da Shiprocket?

O site de seguimento da Shiprocket lista 9643887070, 9711451060 e 8510042020 para problemas de seguimento. Para qualquer problema com o pedido, a Shiprocket indica aos compradores que contactem o vendedor.

### Porque diz o seguimento Shiprocket AWB Not Found?

A Shiprocket não conhece o número inserido: confirma gralhas ou dígitos em falta e aguarda alguns minutos se o pedido acabou de ser feito, como recomenda a página. Envios com mais de cerca de um ano também já não surgem.

:::sources
- [Shiprocket: Track your orders easily](https://www.shiprocket.in/shipment-tracking/) – seguimento por AWB, ID de pedido ou telemóvel, OTP e myShiprocket, AWB por e-mail e SMS, contactar o vendedor, atualizações após registos, ShipX
- [Shiprocket: AWB Not Found](https://shiprocket.co/tracking) – verificações para número não encontrado, aviso sobre pagamentos, telefones, chat ao vivo, apoio 24/7
- [Shiprocket: Contact us](https://www.shiprocket.in/contact/) – escritório em Gurugram, horários telefónicos, e-mail, escalonamento e tempos de resposta
- [Shiprocket: FAQs](https://www.shiprocket.in/faq/) – plataforma para vendedores, estimativas de entrega, RTO, atualizações após registos
- [Shiprocket: Native courier integrations](https://www.shiprocket.in/carrier-integrations/) – 42 transportadoras parceiras e respetivos nomes
- [Shiprocket: ShiprocketX](https://www.shiprocket.in/x/) – serviços internacionais, prazos, serviços com direitos pagos, cobertura até 5000 INR
- [Shiprocket: NDR management](https://www.shiprocket.in/ndr-management/) – NDR e RTO, fluxo de comprador NDR, SMS e chamadas automáticas
- [Shiprocket Support: How to Process an NDR?](https://support.shiprocket.in/support/solutions/articles/43000664210-how-to-process-an-ndr-) – novas tentativas, ligação SMS, tentativas falsas, três tentativas antes de RTO
- [Shiprocket: Product highlights from March 2024](https://www.shiprocket.in/blog/product-highlights-from-march-2024/) – pedido de nova tentativa e motivos na página de seguimento
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formatos AWB, textos de estado, hub de exportação em Deli, USPS nos EUA, vista arquivada, o que o Peek mostra
:::
