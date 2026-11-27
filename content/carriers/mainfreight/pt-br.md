---
title: Rastreamento Mainfreight: códigos de remessa e status
description: Rastreie uma remessa Mainfreight na Nova Zelândia, Austrália ou EUA: formato dos códigos, significado dos status rodoviários, aéreos e marítimos e atrasos.
slug: mainfreight-tracking
published: 2026-11-27
updated: 2026-11-27
---

A Mainfreight transporta cargas por rodovia, via aérea e marítima. O catálogo do Peek a lista para Nova Zelândia (seu país de origem), Austrália e Estados Unidos; o rastreador da empresa também cobre carga rodoviária na Europa e movimentação de contêineres. Para acompanhar um frete ou item grande vindo pela Mainfreight, cole na caixa acima o número de remessa (consignment number) fornecido por quem contratou o transporte. O rastreador da Mainfreight fica em www.mainfreight.com.

## Código de rastreamento Mainfreight: como identificar

Exemplos com dígitos fictícios:

| Código (fictício) | O que é |
| --- | --- |
| `48291736542` | 11 dígitos: remessa rodoviária na Nova Zelândia, Austrália ou Estados Unidos — único formato atribuído à Mainfreight no catálogo do Peek. |
| Letras e dígitos | Número de operação (job number), conhecimento filho (house bill) ou referência do remetente. Depois de selecionar Mainfreight, o Peek aceita de 4 a 40 letras e dígitos. |
| Conhecimento master | 11 dígitos de conhecimento aéreo ou master bill of lading marítimo: o Peek localiza a operação aérea ou marítima se houver apenas uma com esse número. |

O catálogo do Peek também reconhece Mainfreight como Mainfreight Group, Mainfreight Transport e Mainfreight Air & Ocean.

**Onde encontrar.** No e-mail de envio ou na página do pedido; em fretes entre empresas, peça ao remetente. Veja [como encontrar seu código de rastreamento](guide:find-tracking-number), em inglês.

**Outras transportadoras com 11 dígitos.** Onze dígitos também coincidem com [GLS Germany](carrier:gls-de), [GLS France](carrier:gls-fr), [DHL eCommerce Poland](carrier:dhl-ecommerce-pl), PostLogistics e outras. Se o e-mail indicar alguma delas, rastreie por lá. Mais em [formatos de código de rastreamento](guide:tracking-number-formats), em inglês.

## O que significam os status da Mainfreight

**Carga rodoviária na Nova Zelândia e Austrália:**

| A Mainfreight mostra | O que significa para você | O Peek mostra |
| --- | --- | --- |
| `Information Received` | A Mainfreight recebeu os dados, mas ainda não a carga. | "Etiqueta criada" |
| `Pickup Allocated to Driver` | Um motorista foi designado para a coleta; a carga segue com o remetente. | "Etiqueta criada" |
| `Picked Up` | Coletada com o remetente. | "Recebida pela transportadora" |
| `In Transit` | Em trânsito. | "Em trânsito" |
| `At Delivery Depot` | No depósito que fará a entrega. | "Em trânsito" |
| `On Delivery Vehicle` | No caminhão de entrega. | "Saiu para entrega" |
| `Delivered` | Entregue. | "Entregue" |

**Carga rodoviária nos Estados Unidos.** As etapas americanas acrescentam o local após o texto, e o Peek exibe esse local ao lado:

| A Mainfreight mostra | O que significa para você | O Peek mostra |
| --- | --- | --- |
| `Booked` | Transporte reservado. | "Etiqueta criada" |
| `Pickup assigned to driver` | Motorista designado para coleta. | "Etiqueta criada" |
| `Goods picked up` | Coletada com o remetente. | "Recebida pela transportadora" |
| `Departed from Terminal`, `Arrived at Terminal` | Saiu ou chegou a um terminal. | "Em trânsito" |
| `On drivers vehicle en-route` | No caminhão a caminho de você. | "Saiu para entrega" |
| `Goods delivered` | Entregue. | "Entregue" |
| `Collected by Customer` | Retirada pelo cliente no terminal. | "Entregue" |

**Envios aéreos e marítimos:**

| A Mainfreight mostra | O que significa para você | O Peek mostra |
| --- | --- | --- |
| `Booking Confirmation Sent` | Reserva confirmada. | "Etiqueta criada" |
| `Pickup Cartage Advised` | Caminhão agendado para coletar a carga. | "Etiqueta criada" |
| `Pick up Cartage Complete` | Carga coletada para exportação. | "Recebida pela transportadora" |
| `Origin Customs Cleared` | Alfândega de exportação liberada antes do embarque. | "Em trânsito" |
| `Departure from First Load Port`, `Arrival at Final Discharge Port` | Partiu do primeiro porto/aeroporto ou chegou ao último. | "Em trânsito" |
| `Customs Clearance Commenced` | Desembaraço aduaneiro de importação iniciado. | "Na alfândega" |
| `Customs Clearance Finalised` | Liberada pela alfândega e novamente em movimento. | "Em trânsito" |
| `Cargo Available` | Liberada no destino, aguardando entrega ou retirada. | "Em trânsito" |
| `Cargo Delivered`, `Full Container Delivered` | Entregue. | "Entregue" |

Partidas e chegadas planejadas, marcadas como estimadas, ainda não ocorreram e são omitidas pelo Peek.

**Outras etapas.** Movimentações de contêineres entre pátios, como `Picked Up (Site Movement)`, aparecem como "Em trânsito", e `Final Delivery (Empty to Customer)` como "Entregue". Na carga rodoviária europeia, `Order received` vira "Etiqueta criada" e `Out for delivery` vira "Saiu para entrega". Sem etapas detalhadas, o Peek usa o status geral do rastreador, como `Created` ou `Final Delivery`.

O código do Peek não conhece textos próprios da Mainfreight para tentativa sem sucesso, problema ou devolução. Outras linhas seguem as regras gerais do Peek ou aparecem sem alterar a etapa. Mais em [status de rastreamento explicados](guide:tracking-statuses), em inglês.

## Como a Mainfreight entrega

O Peek não conseguiu ler o site institucional da Mainfreight, portanto esta página não traz prazos, agendamentos, tentativas nem regras de retirada em depósito. Pergunte a quem contratou o frete como e quando a carga será entregue.

O Peek não transfere envios da Mainfreight para outra transportadora. O rastreador pode informar uma data prevista para cargas aéreas, marítimas e rodoviárias na Europa, exibida pelo Peek até a saída para entrega; ele não fornece comprovante de entrega nem nome de quem recebeu.

**Alfândega.** Para cargas enviadas à Nova Zelândia, a alfândega neozelandesa (NZ Customs) determina:

- Compras em sites do exterior de até NZ$ 1.000 não pagam imposto de importação, e o vendedor estrangeiro recolhe o GST, exceto se integrarem uma remessa maior acima de NZ$ 1.000. Tabaco e bebidas alcoólicas pagam imposto e GST em qualquer valor.
- O GST é de 15% sobre valor da mercadoria somado a imposto, frete e seguro. Aplica-se também a taxa Customs and MPI Goods Levy a todas as importações não isentas; seu despachante ou agente de carga paga e pode repassar o custo.
- Remessas de NZ$ 1.000 ou mais exigem Customs Number, e a alfândega só libera a carga após o pagamento das taxas.

Para outros destinos, consulte [encomendas retidas na alfândega](guide:customs), em inglês, e confirme com quem contratou a Mainfreight quem arca com as taxas.

## Carga Mainfreight atrasada, parada, danificada ou extraviada

**Nada ainda.** Uma remessa registrada em Mainfreight que ainda não aparece no sistema dela mostra "Aguardando Mainfreight" enquanto o Peek segue consultando. Um número de 11 dígitos que nenhuma transportadora reconhece mostra "Ainda sem histórico de rastreamento". Confira os dígitos e pergunte ao remetente se a coleta já ocorreu.

**Parada.** Após quatro dias sem nova etapa em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito", o envio ganha o alerta com o dia da última atualização ("Sem atualização de rastreamento desde seg 12 out"). Pergunte à loja ou ao remetente o motivo. Veja [rastreamento que não atualiza](guide:tracking-not-updating), em inglês.

**Danificada, incompleta ou extraviada.** Avise primeiro a loja ou o remetente, pois foram eles que contrataram o transporte. Na Nova Zelândia e na Austrália, valem os termos da Mainfreight e a legislação local. Para frete rodoviário interestadual nos Estados Unidos, a lei federal americana determina:

- A transportadora responde pelo prejuízo ou dano real à carga, podendo limitar a responsabilidade a um valor declarado pelo remetente ou acordado por escrito. Não pode fixar prazo menor que 9 meses para reclamação nem menor que 2 anos para ação judicial a partir da recusa por escrito de qualquer parte do pedido.
- A reclamação deve ser feita por escrito, identificando o envio, atribuindo responsabilidade à transportadora e pedindo valor determinado ou determinável; uma simples observação de dano ou falta no recibo de entrega não vale sozinha como reclamação. A transportadora pode pedir conhecimento de transporte, comprovante do frete e fatura.
- A transportadora deve acusar o recebimento por escrito em até 30 dias e pagar, recusar ou apresentar oferta firme em até 120 dias; se não concluir nesse prazo, deve informar o andamento por escrito então e a cada 60 dias.

Se constar `Delivered` sem você ter recebido, veja [entregue mas não recebida](guide:delivered-not-received), em inglês. Mais em [encomendas danificadas](guide:damaged-parcel), em inglês, e [encomendas extraviadas](guide:lost-parcel), em inglês.

## Atendimento ao cliente e contato da Mainfreight

Como o Peek não conseguiu ler o site da Mainfreight, esta página não informa telefone, e-mail nem filiais — o Peek publica apenas contatos verificados diretamente nas páginas da própria transportadora.

Procure primeiro quem contratou o frete: a loja ou fornecedor em um pedido, ou a empresa remetente nos demais casos. É quem detém a reserva e pode tratar direto com a Mainfreight.

## Como rastrear uma remessa Mainfreight no Peek

A partir da Nova Zelândia, Austrália ou Estados Unidos, ao colar 11 dígitos o Peek consulta a Mainfreight imediatamente junto com GLS France, GLS Germany, GLS Switzerland e PostLogistics ("Consultando serviços de rastreamento…"), escolhendo Mainfreight quando só o rastreador dela encontra a remessa; se mais de uma responder, você escolhe. De outros países, o Peek não consulta a Mainfreight automaticamente: selecione-a como abaixo.

Qualquer outro formato não indica Mainfreight ao Peek. Cole mesmo assim — o Peek acompanha mais de 4.500 transportadoras — toque em "Alterar" e selecione Mainfreight (para 11 dígitos, ela aparece em "Compatível com este código"; nos demais casos, pesquise pelo nome). Em uma remessa já adicionada, use "Alterar transportadora". Um link do rastreador da Mainfreight (mainfreight.com/tracking com `trackingnumber=` ou mainfreight.com/track/…) identifica Mainfreight imediatamente ("Transportadora detectada").

Não é preciso conta nem código postal. Se a busca geral da Mainfreight não localizar 11 dígitos, o Peek tenta simultaneamente os sistemas da Nova Zelândia, Austrália e EUA; se mais de um conhecer o número, não há como saber qual remessa é a sua. Para house bill, master bill ou referência do remetente, o Peek exibe o código retornado pela Mainfreight em "Mainfreight rastreia com o código".

O Peek mostra a etapa, cada linha nos termos da Mainfreight com o local e o peso nas suas unidades quando disponível, omitindo contatos de filiais, códigos postais, referência do remetente e documentos. "Abrir o site de Mainfreight" abre o número no rastreador oficial. Quando o rastreador não responde ou não consegue ler o envio, o Peek consulta [serviços de rastreamento](guide:universal-tracker), em inglês, e exibe o histórico deles.

Etapas rodoviárias com fuso da Nova Zelândia, Austrália ou EUA (`NZDT`, `AEST`, `PST` etc.) mostram a hora exata. As demais não trazem fuso: o Peek usa o relógio do local da etapa ou das etapas vizinhas quando reconhecido, ou o horário da Nova Zelândia (como em leituras rodoviárias europeias e eventos aéreos e marítimos, que podem apresentar diferença de algumas horas).

Com a remessa aberta ou no dia da entrega com notificações ativas, o Peek consulta a cada 10 minutos no máximo, e a cada 2 minutos a partir das 6h no relógio da última leitura no dia em que sai para entrega, enquanto o rastreador da Mainfreight responder. Fora disso, e após dois dias sem novidade (12 horas em "Etiqueta criada" ou "Recebida pela transportadora"), verifica de hora em hora. Você pode escolher notificações para "Cada atualização", "Etapas importantes" ou "Somente na entrega". O Peek não pode contatar a Mainfreight por você, agendar entregas nem abrir reclamações.

## Perguntas frequentes sobre o rastreamento Mainfreight

### Como é um código de rastreamento da Mainfreight?

Para carga rodoviária na Nova Zelândia, Austrália e Estados Unidos, um número de remessa de 11 dígitos. Números de operação, house bills e referências do remetente misturam letras e dígitos: nesses casos, selecione Mainfreight manualmente no Peek.

### O que significa "At Delivery Depot" no rastreamento Mainfreight?

Significa que a carga chegou ao depósito responsável pela entrega, e o Peek exibe "Em trânsito". Em seguida vem `On Delivery Vehicle`, quando entra no caminhão para entrega. Se ficar parada ali por dias, pergunte ao remetente quando a entrega está prevista.

### O que significa "Pickup Allocated to Driver"?

Um motorista foi escalado para fazer a coleta, mas a carga ainda está com o remetente — por isso o Peek mostra "Etiqueta criada". A etapa seguinte é `Picked Up`.

:::sources
- [Universal Parcel Scraper: Mainfreight](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/mainfreight) – names and countries, the 11-digit shape, other references and master bills, links, the tracker's search and services, wordings and their stages, planned steps, clocks, weight, expected day, what Peek leaves out
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – carriers sharing 11 digits, Mainfreight asked only from New Zealand, Australia and the United States, no hand-off, tracking services as a fallback
- [NZ Customs: Duty and GST](https://www.customs.govt.nz/sending-and-receiving/duty-and-gst) – NZ$1000, GST collected by overseas sellers, larger consignments, tobacco and alcohol, how GST is worked out, Goods Levy, Customs Number, release after payment
- [GovInfo: 49 U.S.C. 14706, Liability of carriers under receipts and bills of lading](https://www.govinfo.gov/content/pkg/USCODE-2024-title49/html/USCODE-2024-title49-subtitleIV-partB-chap147-sec14706.htm) – liability for actual loss or injury, limited value, 9 months to claim and 2 years to sue
- [GovInfo: 49 CFR Part 370, Investigation and voluntary disposition of loss and damage claims and processing salvage](https://www.govinfo.gov/content/pkg/CFR-2025-title49-vol5/xml/CFR-2025-title49-vol5-part370.xml) – what a claim contains, notes on delivery receipts, supporting documents, 30, 120 and 60 days
:::
