---
title: Rastreamento Straightship: códigos STRTD, status e entrega
description: Rastreie um pacote da Straightship no Canadá: códigos STRTD, STRCA e STSH, significado dos status, entregas perdidas, alfândega e contato.
slug: rastreamento-straightship
published: 2026-11-24
updated: 2026-11-24
---

A Straightship (Straightship Solutions Limited) é uma empresa de logística sediada em Burnaby, na Colúmbia Britânica, que envia mercadorias da Ásia para a América do Norte e entrega pacotes em todo o Canadá com motoristas próprios. O site dela cita a SHEIN entre os parceiros. Se uma compra online veio com um código iniciado por `STRTD` ou `STRCA`, está com a Straightship; um código iniciado por `STSH` também pode ser dela. Rastreie em [straightship.com](https://www.straightship.com/new/), no campo "Track Your Shipment", ou na caixa acima. A loja informa o código no e-mail de envio ou na página do pedido.

## Código de rastreamento Straightship: como reconhecer

A Straightship não publica o formato dos seus códigos. Estes são os formatos que o Peek reconhece, com dígitos inventados:

| Código | O que é |
| --- | --- |
| `STRTD482910573` | `STRTD` e 9 dígitos. |
| `STRTD4829105736491827` | `STRTD` e 16 dígitos (também há códigos com 17). |
| `STRCA4829105736498` | `STRCA` e 13 dígitos. |
| `STSHYW48291057364` | `STSH`, duas letras e 11 dígitos. |

Letras minúsculas e espaços não fazem diferença no Peek. O rastreador da Straightship responde `No results found!` para códigos que não possui: confira o número no e-mail da loja. Não encontra o código? Veja [como encontrar o código de rastreamento](guide:find-tracking-number) e, para pedidos da SHEIN, [como rastrear pedidos da SHEIN](guide:shein-orders).

## Status do rastreamento Straightship explicados

O rastreador da Straightship é em inglês. Ele mostra um resumo com "Package Status" acima do histórico, que traz horário, status e cidade de cada registro. Estas são as linhas mais comuns, na ordem em que costumam aparecer:

| A Straightship mostra | O que significa para você |
| --- | --- |
| `Package information is created in the system` | A loja gerou a etiqueta. A Straightship ainda não está com o pacote: ele segue com o vendedor ou a caminho, vindo da Ásia. |
| `Package Received with Origin Scan warehouse` | Primeiro registro da Straightship no Canadá, com cidade e província. |
| `Package is received at a Straightship station` | Chegou a uma das unidades da Straightship. Páginas mais antigas exibem `Arrived at Facility`. |
| `Package arrived at transit station` | Em uma unidade de trânsito, a caminho do centro local. |
| `Package is in transit to a Straightship station` | Em deslocamento entre centros, antes da unidade responsável pela entrega. |
| `Loading` | Sendo carregado no centro antes da rota de entrega. Pode aparecer duas vezes, com segundos de diferença. |
| `Package will be delivered before 9:00pm` | Saiu para entrega hoje. Uma entrega ou tentativa acontece no mesmo dia. |
| `Attempted Delivery` | O motorista tentou e não conseguiu entregar. |
| `Shipment delayed` | Em atraso. A Straightship não informa o motivo no rastreador. |
| `Delivered` | Entregue, com cidade e província. |
| `Package is being returned to shipper` | A caminho de volta ao remetente. |

Duas mensagens de tentativa dizem o que fazer. `An attempt was made to deliver your package-consignee closed (please e-mail SS@straightship.com to make arrangement)` indica que o endereço estava fechado; a mesma frase com `unable to leave package at safe location` no lugar de `consignee closed` significa que o motorista não encontrou um local seguro para deixar o pacote. Ambas pedem que você envie um e-mail à Straightship para combinar a próxima entrega.

Três detalhes confundem as pessoas:

- **O resumo fica para trás.** O campo "Package Status" pode continuar em `ReadyForShipping` (ou `Ready For Shipping`) enquanto o pacote já circula entre unidades. Leia o histórico: a linha mais recente é a que vale. Os valores posteriores são `Shipped`, `Delivered` e `ReturnedToSender`.
- **"In progress" não significa saiu para entrega.** `Delivery is in progress` e `Your order is in Progress` aparecem depois de uma entrega que não aconteceu, às vezes dias mais tarde e antes de uma devolução. Envie um e-mail à Straightship se vir essas mensagens.
- **Os horários estão em UTC.** Abaixo do histórico, o rastreador informa que todos os horários estão em Tempo Universal Coordenado (UTC). Uma movimentação à noite no Canadá aparece com a data do dia seguinte.

Um pacote em devolução ainda pode ser bipado em um centro e sair novamente para entrega: `Package is being returned to shipper` nem sempre é a linha final.

## Entregas da Straightship no Canadá: quem faz a entrega

O rastreador da Straightship atende o Canadá: o cabeçalho diz "Canada B2C Shipment Tracking". A Straightship afirma que sua rede própria alcança 95% da população canadense, com mais de 85 centros de entrega, e o aviso de saída para entrega promete entrega até as 21h. Ela também entrega para a [JS Express](carrier:js-express), empresa chinesa de logística: o rastreamento da JS Express exibe as movimentações da Straightship no Canadá e, quando o registro da JS Express traz um código `STRTD` ou `STRCA`, o Peek consulta a Straightship também.

Para pacotes trazidos dos Estados Unidos ao Canadá, a Straightship oferece "different options on last-mile carriers": sua própria rede, Canada Post ou [UPS](carrier:ups), além da Purolator na página em inglês e da FedEx na página em chinês. A transportadora escolhida varia conforme a remessa. A Straightship informa que esse serviço entrega em 6 a 10 dias e faz o desembaraço aduaneiro em até 24 horas. O rastreador mostra apenas os centros da própria Straightship e não informa outra transportadora nem um segundo código.

A Straightship não publica regras sobre quantas tentativas faz, se deixa aviso de entrega ou por quanto tempo guarda um pacote não entregue no centro. O que ela informa:

- **Após tentativa sem sucesso**, envie um e-mail para SS@straightship.com, como pede o rastreador, informando o código de rastreamento.
- **Para alterar endereço ou telefone**, use o formulário de contato e escolha "Address and/or contact details update".
- **Comprovante de entrega**: o rastreador exibe o status mais recente "and POD image (Where available)", uma foto da entrega.

## Alfândega em pacotes da Straightship vindos da Ásia

A Straightship faz o desembaraço aduaneiro para as empresas com as quais trabalha, e sua página de serviços em chinês indica que pode cobrar os impostos no Canadá (`DDU`). Se pedirem algum pagamento por um pacote da Straightship, confirme primeiro com o vendedor. A SHEIN afirma que todos os seus pedidos para o Canadá são entregues com tributos pagos (duty paid), sem taxas alfandegárias extras para você. Outros rastreadores podem mostrar `Customs Released` antes do primeiro registro no Canadá; o da Straightship não mostra. Antes desse primeiro registro, o histórico também pode exibir `Customs Held For Examination`, enquanto a alfândega analisa o pacote, ou `Customs Rejected`, que nem sempre encerra o envio: em um pacote acompanhado pelo Peek, a alfândega inspecionou e liberou dois dias depois. O Peek mostra ambas as mensagens como "Na alfândega"; se alguma delas permanecer por dias, fale com o vendedor.

O valor devido depende do preço e da origem da mercadoria. Por courier, nada é cobrado até CAD 20 vindo da China ou de qualquer país que não seja Estados Unidos ou México; acima disso incidem tarifa e impostos. Dos Estados Unidos ou do México, há isenção até CAD 40, apenas impostos até CAD 150, e tarifa e impostos acima desse limite.

Em encomendas enviadas por courier, a Canada Border Services Agency (CBSA) permite que a transportadora recolha tarifas e impostos em seu nome. A CBSA explica que a transportadora entra em contato para organizar o pagamento antes da entrega e retém o pacote até a quitação, e que as taxas de desembaraço da transportadora são separadas dos tributos e fogem ao controle da CBSA. A CBSA nunca telefona, envia SMS ou e-mail pedindo seu Social Insurance Number ou número de cartão: mensagens assim são golpe. Detalhes sobre cobranças, isenções e reembolsos estão em [encomendas paradas na alfândega](guide:customs).

## Pacote Straightship atrasado, perdido ou devolvido

**Quem resolve.** Os clientes da Straightship são os vendedores e plataformas que contratam o envio, e ela não publica um canal de indenização para destinatários. Para reembolso ou reenvio, fale com a loja: no caso da SHEIN, veja [como rastrear pedidos da SHEIN](guide:shein-orders).

**Parado antes do Canadá.** `Package information is created in the system` sem novos registros indica que o pacote ainda não chegou à Straightship no Canadá. Ele pode seguir vindo da Ásia. Pergunte ao vendedor quando foi despachado; veja [como rastrear encomendas da China](guide:tracking-from-china).

**Sem atualização no Canadá.** Se não houver movimentação por vários dias após um registro em centro, ou se aparecer `Shipment delayed`, use o formulário de contato da Straightship selecionando "Delivery Status Enquiry". Saiba mais em [por que o rastreamento para de atualizar](guide:tracking-not-updating).

**Marcado como entregue, mas não chegou.** Procure a foto de entrega no rastreador da Straightship (nem sempre disponível), depois verifique com vizinhos, a portaria ou sala de correspondências do prédio e locais seguros perto da porta. A SHEIN observa que, em casos raros, o pacote aparece como entregue até 48 horas antes de chegar. Em seguida, use a opção "Delivered Order Not Received" do formulário e avise o vendedor; em pedidos da SHEIN, procure o botão "Parcel Delivered, Not Received" em Meus pedidos para abrir pedido de reembolso. Veja [entregue, mas não recebido](guide:delivered-not-received).

**Devolvido.** `Package is being returned to shipper` significa que o pacote está voltando para o vendedor. Peça reembolso ou novo envio à loja; veja [encomenda devolvida ao remetente](guide:returned-to-sender).

## Telefone e atendimento da Straightship

A Straightship informa os seguintes canais:

- **Telefone**: +1 (800) 452-3304, de segunda a sexta-feira das 8h às 21h e aos sábados das 8h às 16h (horário do Leste).
- **E-mail**: SS@straightship.com, endereço indicado nas mensagens de tentativa de entrega.
- **Formulário de contato**: na [página de contato](https://www.straightship.com/new/contact.html), com preenchimento obrigatório do código de rastreamento. A Straightship pede até 2 dias úteis para responder.

Para questões do pedido, reembolso ou devolução, procure o vendedor.

## Como rastrear um pacote Straightship no Peek

O [Peek](/) identifica a Straightship de imediato a partir de códigos `STRTD` ou `STRCA` nos formatos acima, que nenhuma outra transportadora do catálogo usa. Em um código `STSH`, ele não decide sozinho porque alguns formatos coincidem com os da Colis Privé: o Peek consulta a Straightship para confirmar se ela tem o pacote e a seleciona quando confirma. Se a Straightship não aparecer, toque em "Trocar" e escolha-a em "Compatível com este código". Links de rastreamento de straightship.com (`/track-your-shipment/tracking-id/` seguido do código) também podem ser colados. O Peek rastreia apenas esses formatos na Straightship: se você escolher a Straightship em "Trocar" para outro número, o Peek informa que ela "não emite códigos como este". Para códigos Straightship fora desses formatos, use o rastreador da própria empresa.

O Peek lê o rastreador da Straightship e mostra o status, o histórico com cidade e província de cada registro e o horário da entrega. Ele interpreta os horários do rastreador como UTC, conforme indicado pela Straightship, e os converte para o fuso do seu aparelho; em peektracker.com e nos aplicativos para iPhone e Android, o histórico termina com "Horários no seu fuso horário:" seguido do nome do fuso. `Package will be delivered before 9:00pm` conta como saiu para entrega, as mensagens de tentativa contam como tentativa de entrega e `Delivery is in progress` aparece como em trânsito. O Peek não exibe previsão de entrega e omite a referência do cliente e a foto da entrega: veja esses dados no rastreador da Straightship. Como a Straightship não informa outra transportadora, o Peek acompanha o código Straightship até o fim.

Enquanto o pacote estiver aberto, ou no dia da entrega com alertas ligados, o Peek verifica até a cada 10 minutos, ou a cada 2 minutos a partir das 6h (horário do Pacífico) no dia em que a Straightship registra saída para entrega — incluindo entregas noturnas — enquanto o rastreador da Straightship responder (por meio de serviços de rastreamento, até a cada 15 minutos nesse dia, enquanto o pacote estiver aberto ou com alertas ligados); nos demais casos, de hora em hora. Após dois dias sem novidade (12 horas enquanto o pacote ainda mostra "Etiqueta criada" ou "Recebida pela transportadora"), o Peek passa a verificar de hora em hora. Você pode ativar alertas para "Cada movimentação", "Etapas importantes" ou "Só a entrega". O Peek não pode contatar a Straightship nem o vendedor por você.

## Perguntas sobre o rastreamento Straightship

### A Straightship é confiável?

Sim. A Straightship Solutions Limited é uma empresa de logística de Burnaby, Colúmbia Britânica. O site dela lista SHEIN, Canada Post, FedEx, COSCO e YTO entre seus parceiros, cita Walmart e Costco entre os varejistas atendidos e afirma operar mais de 85 centros de entrega, movimentando mais de 500.000 pacotes da Amazon por dia.

### O que significa "Package will be delivered before 9:00pm"?

Seu pacote saiu para entrega hoje. O motorista faz a entrega, ou registra uma tentativa, no mesmo dia. Se ninguém puder receber, surge uma linha de tentativa pedindo que você envie e-mail para SS@straightship.com.

### Por que a Straightship ainda mostra ReadyForShipping?

O resumo "Package Status" pode permanecer em `ReadyForShipping` mesmo com o pacote circulando entre os centros da Straightship. Considere sempre a linha mais recente do histórico abaixo.

### Quanto tempo a Straightship leva para entregar?

A Straightship não divulga prazo para envios da Ásia; no serviço dos Estados Unidos para o Canadá, informa de 6 a 10 dias. Siga a estimativa dada pela loja no seu pedido. Quando aparecer `Package will be delivered before 9:00pm`, o motorista vai passar naquele dia.

### Quem entrega os pacotes da Straightship?

Os próprios motoristas da Straightship, saindo de seus centros no Canadá, até onde o rastreador mostra: ele não cita outra transportadora. Em envios dos Estados Unidos, a Straightship também cita Canada Post e UPS na etapa final, além de Purolator na página em inglês e FedEx na página em chinês.

:::sources
- [Straightship: Home](https://www.straightship.com/new/) – Ásia para América do Norte, frota própria, campo de rastreamento, foto POD, parceiros, telefone, e-mail e horários
- [Straightship: About](https://www.straightship.com/new/about.html) – porta a porta da Ásia para a América do Norte
- [Straightship: Services](https://www.straightship.com/new/services.html) – rede própria cobrindo 95% da população do Canadá; serviço EUA–Canadá em 6 a 10 dias, alfândega em 24 horas, opções de última milha com rede própria, Canada Post, Purolator ou UPS; desembaraço aduaneiro
- [Straightship: 服务 (serviços em chinês)](https://www.straightship.com/new/cn/services.html) – mesmo serviço EUA–Canadá com rede própria, Canada Post, FedEx ou UPS na etapa final
- [Straightship: Resources](https://www.straightship.com/new/resources.html) – mais de 85 centros de entrega, mais de 500.000 pacotes da Amazon por dia, Walmart e Costco
- [Straightship: Contact](https://www.straightship.com/new/contact.html) – motivos de contato, código obrigatório, prazo de 2 dias úteis
- [Straightship: Privacy Policy](https://www.straightship.com/new/privacy.html) – Straightship Solutions Limited, Burnaby
- [CBSA: Importing casual goods by courier](https://www.cbsa-asfc.gc.ca/import/courier/lvs-efv/prsn-eng.html) – couriers recolhem tarifas e impostos, taxas de desembaraço fora do controle da CBSA
- [CBSA: Protect yourself against fraud](https://www.cbsa-asfc.gc.ca/security-securite/pyaf-pvcf-eng.html) – transportadora combina pagamento antes da entrega e retém o pacote, sem pedido de SIN ou cartão
- [CBSA: Low-value shipment thresholds](https://www.cbsa-asfc.gc.ca/services/cusma-aceum/lvs-efv-eng.html) – por courier: CAD 20 de outros países, CAD 40 e CAD 150 dos EUA e México
- [SHEIN Canada: Are there any additional fees for customs?](https://ca.shein.com/faq/detail?id=2307) – pedidos ao Canadá entregues com tributos pagos
- [SHEIN Canada: My order is marked as delivered, but I haven't received it](https://ca.shein.com/faq/detail?id=2362) – marcado como entregue até 48 horas antes, botão Parcel Delivered, Not Received
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – o que o Peek mostra, formatos de código, textos dos eventos, aviso de UTC, valores do resumo, `Customs Released` em outros rastreadores, retenções aduaneiras liberadas dois dias depois, o repasse a partir da JS Express
:::
