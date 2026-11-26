---
title: Rastreio Shiprocket: códigos AWB, status e RTO
description: Rastreie encomendas Shiprocket ou ShiprocketX: formato do AWB, significado dos status, falhas de entrega (NDR) e devolução (RTO), além de contato.
slug: shiprocket-tracking
published: 2026-11-26
updated: 2026-11-26
---

A Shiprocket, sediada em Gurugram, é uma plataforma logística indiana, não uma transportadora própria: vendedores contratam por meio dela transportadoras como Delhivery, Blue Dart ou Xpressbees, e a Shiprocket oferece ao comprador uma página de rastreamento. ShiprocketX é o serviço voltado a encomendas enviadas da Índia para o exterior. Rastreie na [página da Shiprocket](https://www.shiprocket.in/shipment-tracking/) usando o AWB, número do pedido ou celular, pelo link shiprocket.co enviado pelo vendedor, ou colando o código na caixa acima. A Shiprocket informa enviar o AWB por e-mail e SMS assim que o pedido é confirmado.

## Formato do código de rastreio Shiprocket

A Shiprocket chama o código de rastreio de AWB (*air waybill number*). Dentro da Índia, trata-se do código da própria transportadora local. Encomendas internacionais ShiprocketX viajam sob o AWB próprio da Shiprocket: 13 dígitos começando com 832. A empresa não publica um formato único; veja exemplos com dígitos inventados:

| Código (inventado) | O que é |
| --- | --- |
| `8324829103756` | 832 seguido de mais 10 dígitos: um AWB ShiprocketX. |
| `7394028156342` | 13 ou 14 dígitos: AWB de transportadora doméstica indiana, como Delhivery ou Xpressbees. |
| `74829103753` | 11 dígitos: formato de um AWB da Blue Dart. |
| `FMPC4829103756` | Três letras, C, P ou R e 10 dígitos: AWB da Ekart. |

**Onde encontrar.** Procure no e-mail e SMS recebidos na confirmação do pedido. O rastreador da Shiprocket também aceita número do pedido ou celular, valida um código único (OTP) e abre o myShiprocket, portal onde compradores acompanham pedidos. Veja mais em [como encontrar seu código de rastreio](guide:find-tracking-number).

**Códigos parecidos.** Os códigos da Delhivery também podem ter 13 dígitos, então um código iniciado por 832 provavelmente — mas não com certeza absoluta — é Shiprocket. O rastreador da Shiprocket responde por qualquer transportadora contratada via plataforma, logo o link shiprocket.co/tracking/ enviado pelo vendedor funciona para qualquer formato.

## Significado dos status de rastreio Shiprocket

A página exibe o status, a transportadora ou serviço ao lado do AWB e os eventos do mais recente ao mais antigo, com descrição, frequentemente local, dia, mês e hora (sem ano). A maioria dos textos vem da transportadora responsável: local na Índia ou no país de destino no exterior. Etapas habituais em ordem:

| A página Shiprocket exibe | O que significa para você |
| --- | --- |
| `Order Received` | O vendedor criou o pedido. Nenhuma transportadora está com o pacote ainda. |
| `Manifested - Out for Pickup` | O entregador está a caminho para coletar o pacote com o vendedor. |
| `In Transit - Shipment picked up` | A transportadora coletou a encomenda. |
| `In Transit - Shipment Recieved at Origin Center` | Chegou ao centro inicial da transportadora (grafado assim na página). |
| `Dispatched - Delivering At Shiprocket Warehouse` | ShiprocketX: a transportadora local está levando o pacote ao centro de exportação da Shiprocket, não à sua casa. |
| `Received At DC` | O centro de exportação da Shiprocket recebeu o pacote. |
| `AWB Handed over to Flight` | Entregue à companhia aérea para embarque. |
| `CUSTOM CLEARED OVERSEAS` | Liberado pela alfândega no exterior. |
| `Received by carrier` | A transportadora do seu país recebeu o pacote. |
| `RTO DELIVERED` | Devolvido ao vendedor. |

`Out For Pickup` e `PICKUP EMPLOYEE IS OUT TO P/U SHIPMENT` também significam que o pacote ainda não foi coletado. `Reached At Warehouse` e `Connection Aligned` são etapas no centro de exportação em Déli. `Customer has made payment or Provided ID/Tax information` é etapa aduaneira no exterior indicando tributos pagos ou documento informado (veja [encomendas retidas na alfândega](guide:customs)). Qualquer linha iniciada por `RTO` (*return to origin*) indica devolução ao vendedor.

**Encomendas antigas.** Após algum tempo, a página exibe apenas uma visão arquivada: último status (`DELIVERED`, `FULFILLED` para pedido entregue e concluído, ou `RTO DELIVERED`), data, serviço e AWB com os últimos caracteres ocultos. O histórico completo só aparece ao comprador mediante código enviado por SMS. Depois de cerca de um ano, exibe `AWB Not Found`.

## Quem entrega encomendas Shiprocket

**Na Índia**, a transportadora contratada pelo vendedor. A Shiprocket informa integrar 42 parceiras, entre elas Delhivery, Blue Dart, Xpressbees, Ekart, DTDC, Shadowfax, India Post e Amazon Shipping, com prazos variando conforme transportadora, código postal (PIN code) e modalidade. A página mostra o serviço usado, como `Delhivery Surface`.

**No exterior**, uma transportadora doméstica leva o pacote ShiprocketX até o centro de exportação da Shiprocket em Déli; de lá segue de avião, passa pela alfândega e é entregue a uma transportadora local no destino, mantendo o mesmo AWB Shiprocket. Em históricos consultados na Shiprocket, a USPS assumiu a entrega nos EUA. A página cita o serviço, como `SRX Economy` ou `SRX Premium`. A Shiprocket informa a lojistas que a modalidade econômica leva de 12 a 15 dias úteis para EUA, Reino Unido, Canadá e outros destinos, e a modalidade com impostos pagos leva de 10 a 12 dias para os EUA — estimativas comerciais, não data garantida para seu pedido. Nos menus, o ShiprocketX também aparece como "ShipX".

## Falha na entrega (NDR) e devolução (RTO)

Um NDR (*non-delivery report*) indica que a transportadora não conseguiu entregar. O vendedor vê o aviso minutos depois no painel e decide entre nova tentativa ou devolução.

- **Mensagens ao comprador.** Se o vendedor usa o fluxo NDR da Shiprocket, pedidos marcados como "cliente pediu entrega futura" ou "cliente recusou a entrega" geram SMS — e em alguns planos ligação automática — para confirmar a entrega. O vendedor também pode enviar via Shiprocket um link por SMS para solicitar nova tentativa e corrigir endereço ou telefone.
- **Página de rastreio.** A Shiprocket afirma que compradores podem pedir nova tentativa direto na página e conferir o motivo de cada tentativa.
- **Tentativa que não ocorreu.** Avise o vendedor, que pode denunciar uma "tentativa falsa" (*fake attempt*) à Shiprocket com sua confirmação.
- **Três tentativas.** As transportadoras fazem até três tentativas; depois disso o pedido entra em RTO e volta ao endereço de coleta ou armazém do vendedor.

Quando o pacote já estiver voltando, peça novo envio ou reembolso ao vendedor. Veja [entregas perdidas](guide:missed-delivery) e [devolvida ao remetente](guide:returned-to-sender).

## Encomenda Shiprocket que não chega ou parou de atualizar

**Sem atualização.** A Shiprocket informa que as transportadoras atualizam assim que registram a leitura, o que pode levar algumas horas; após mais de um dia sem mudança, contate o vendedor. Enquanto constar apenas `Order Received` ou coleta em andamento, a transportadora ainda não retirou o pacote. Saiba mais em [por que o rastreio para de atualizar](guide:tracking-not-updating).

**A quem recorrer.** O cliente da Shiprocket é o vendedor. À pergunta sobre quem contatar em caso de problema, a própria Shiprocket responde para procurar o vendedor onde a compra foi feita. Em caso de perda, dano ou não entrega, acione o vendedor também: a cobertura ShiprocketX contra perda ou dano, de até INR 5.000, vale para vendedores. Veja [encomendas perdidas](guide:lost-parcel) e [entregue mas não recebida](guide:delivered-not-received).

## Telefone e contato do atendimento Shiprocket

- **Telefone:** 9643887070, 9711451060 e 8510042020 (números indianos, +91 ligando de fora). O site de rastreio lista esses números e chat ao vivo para problemas de rastreio, citando atendimento 24/7; a página de contato indica os mesmos números das 9h às 21h no horário da Índia.
- **E-mail:** support@shiprocket.in.
- **Escalonamento:** o suporte responde em até 5 dias úteis. Sem retorno, escreva para escalation@shiprocket.com e depois grievance@shiprocket.com, cada um com prazo de 3 dias úteis.

A maior parte desse suporte é voltada a vendedores. O site de rastreio também alerta: se receber cobrança de fonte não verificada, não responda nem informe dados de pagamento; bloqueie e denuncie. Leia [golpes por SMS de entrega](guide:delivery-scam-texts).

## Acompanhe encomendas Shiprocket no Peek

Para códigos de 13 dígitos começando por 832, o [Peek](/) consulta a Shiprocket logo de início (junto com a Delhivery se seu país for a Índia) e recorre a outros serviços apenas se a Shiprocket não reconhecer o código: a tela exibe "Consultando serviços de rastreamento…" e confirma assim que a Shiprocket responde. Como códigos da Delhivery têm o mesmo tamanho, o Peek não trava na Shiprocket só pelo formato; sem resposta imediata, mostra "Aguardando a primeira movimentação de Shiprocket" e segue consultando, ou você pode escolher Shiprocket em "Alterar". Um link shiprocket.co/tracking/ seleciona Shiprocket para qualquer número.

Enquanto o pacote estiver aberto no Peek, ou no dia da entrega com notificações ligadas, o Peek lê a página da Shiprocket até a cada 10 minutos — ou a cada 2 minutos a partir das 6h no relógio da leitura mais recente (horário da Índia na Índia) no dia em que sai para entrega, enquanto a página oficial responder. Após dois dias sem novidades (12 horas enquanto ainda mostrar "Etiqueta criada" ou "Recebida pela transportadora"), consulta a cada hora, e a cada 6 horas após sete dias sem novidades em "Etiqueta criada". O Peek mostra o status, cada leitura com local quando fornecido, a previsão de entrega enquanto viaja e o horário de entrega na Índia. Leituras no exterior vêm sem fuso: o Peek estima o relógio pelo local (ou pela linha vizinha / horário da Índia) e exibe no fuso do seu aparelho. Os textos mantêm a redação da página, salvo correspondências exatas como `Delivered`. O Peek omite vendedor, itens, seu nome, endereço e comprovante de entrega, e não migra automaticamente para a transportadora estrangeira.

Em encomendas antigas arquivadas, o Peek mostra o último status arquivado e a data, buscando histórico completo em serviços de rastreamento. Ele não pode autenticar você como comprador, pedir nova tentativa nem falar com a Shiprocket ou com o vendedor. As notificações cobrem "Cada atualização", "Etapas importantes" ou "Somente entrega". Após quatro dias sem novidades em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito", exibe o aviso de pausa com a data da última leitura (por exemplo, "Sem atualização de rastreamento desde seg 12 out") e a nota "É comum uma encomenda ficar sem atualizações em voos longos, na alfândega ou no fim de semana. O Peek continua consultando Shiprocket." O Peek também rastreia [Delhivery](carrier:delhivery), [Blue Dart](carrier:blue-dart), Xpressbees, [Ekart](carrier:ekart), DTDC, Shadowfax e [India Post](carrier:india-post) por seus próprios códigos.

## Perguntas sobre o rastreio Shiprocket

### A Shiprocket é uma transportadora?

Não. A Shiprocket é uma plataforma de envios: vendedores contratam transportadoras como Delhivery, Blue Dart ou Xpressbees por meio dela, e a transportadora transporta o pacote. Encomendas ShiprocketX viajam sob o AWB da própria Shiprocket e são entregues por uma transportadora no país de destino.

### O que significa RTO no rastreio Shiprocket?

RTO significa *return to origin* (retorno à origem): o pacote não pôde ser entregue e está voltando ao vendedor após até três tentativas ou antes, se o vendedor solicitar. `RTO DELIVERED` significa que o vendedor já o recebeu de volta.

### Qual é o número de atendimento da Shiprocket?

O site de rastreamento da Shiprocket lista 9643887070, 9711451060 e 8510042020 para dúvidas de rastreio. Para problemas com seu pedido, a Shiprocket orienta os compradores a falar com o vendedor.

### Por que o rastreio Shiprocket diz AWB Not Found?

A Shiprocket não reconhece o código informado: verifique se há erro de digitação ou dígitos faltando e aguarde alguns minutos se o pedido acabou de ser feito, como orienta a página. Envios com mais de cerca de um ano também deixam de ser encontrados.

:::sources
- [Shiprocket: Track your orders easily](https://www.shiprocket.in/shipment-tracking/) – rastreio por AWB, número do pedido ou celular, OTP e myShiprocket, AWB por e-mail e SMS, contato com o vendedor, atualizações após leituras, ShipX
- [Shiprocket: AWB Not Found](https://shiprocket.co/tracking) – verificação de código não encontrado, alerta de pagamento, telefones, chat ao vivo, suporte 24/7
- [Shiprocket: Contact us](https://www.shiprocket.in/contact/) – escritório em Gurugram, horários de telefone, e-mail, escalonamento e prazos de resposta
- [Shiprocket: FAQs](https://www.shiprocket.in/faq/) – plataforma para vendedores, previsões de entrega, RTO, atualizações após leituras
- [Shiprocket: Native courier integrations](https://www.shiprocket.in/carrier-integrations/) – 42 transportadoras parceiras e seus nomes
- [Shiprocket: ShiprocketX](https://www.shiprocket.in/x/) – serviços internacionais, prazos, serviços com impostos pagos, cobertura de até INR 5.000
- [Shiprocket: NDR management](https://www.shiprocket.in/ndr-management/) – NDR e RTO, fluxo NDR para comprador, SMS e ligações automáticas
- [Shiprocket Support: How to Process an NDR?](https://support.shiprocket.in/support/solutions/articles/43000664210-how-to-process-an-ndr-) – novas tentativas, link por SMS, tentativas falsas, três tentativas antes do RTO
- [Shiprocket: Product highlights from March 2024](https://www.shiprocket.in/blog/product-highlights-from-march-2024/) – pedidos de nova tentativa e motivos na página de rastreio
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formatos de AWB, textos de status, centro de exportação em Déli, USPS nos EUA, visão arquivada, o que o Peek mostra
:::
