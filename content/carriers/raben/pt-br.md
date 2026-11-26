---
title: Rastreamento Raben: código de envio, status e entregas
description: Rastreie cargas paletizadas e fracionadas da Raben na Europa: formato do código, significado das etapas no myRaben, descarga, danos e contatos.
slug: raben-tracking
published: 2026-11-26
updated: 2026-11-26
---

O Raben Group é um grupo europeu familiar de logística fundado em 1931 nos Países Baixos e hoje sediado perto de Poznań, na Polônia. Sua rede rodoviária transporta paletes e cargas fracionadas (groupage) por filiais em 17 países europeus e também atende lojas virtuais, então um pedido volumoso pode chegar em um caminhão da Raben. Acompanhe pelo número do envio na caixa "Shipment tracking" em raben-group.com ou cole o código na caixa acima.

## Código de envio Raben: formato e onde encontrar

A Raben atribui um número próprio a cada remessa. A empresa que contratou o transporte possui esse código (geralmente a loja ou fabricante): procure no e-mail de envio ou na página do pedido. As empresas polonesas da Raben informam que recebem os dados do destinatário de quem contratou o frete, portanto peça ao remetente se não tiver o código. Veja mais em [como encontrar seu código de rastreamento](guide:find-tracking-number).

| Código (inventado) | O que é |
| --- | --- |
| `274829173648291` | `27` e mais 13 dígitos: formato de número de envio da Raben no catálogo do Peek. |
| `27 4829 1736 4829 1` | O mesmo código com espaços. Espaços e hifens não interferem no Peek. |
| `oftc.myraben.com/link/ShipmentInformation?ShipmentNumber=274829173648291` | Link direto para um envio no rastreador da Raben, como aberto pela busca de raben-group.com. Cole o link inteiro no Peek. |

A [BRT](carrier:brt) na Itália e a Yunda na China também usam códigos de 15 dígitos, portanto um número assim nem sempre é da Raben. A busca da Raben também aceita referência ou etiqueta de volume, mas o Peek rastreia somente pelo número do envio.

## Status de rastreamento da Raben explicados

O rastreador da Raben, myRaben, exibe o status, cada etapa com local e horário, data prevista de entrega, peso, quantidade e volume, barra de progresso, observações e dados de contato da filial da Raben responsável. Etapas na ordem habitual:

| A Raben diz | O que significa para você | Status no Peek |
| --- | --- | --- |
| `registered in the system` | O remetente contratou o transporte. A Raben recebeu o pedido, ainda não a mercadoria. | "Etiqueta criada" |
| `arrived to the pick-up place` | O caminhão chegou ao remetente para coleta. | "Etiqueta criada" |
| `collected` | A Raben coletou a carga do remetente. | "Recebida pela transportadora" |
| `loaded` | Carregada no caminhão: rumo ao próximo depósito ou ao veículo de entrega. | "Em trânsito" |
| `unloaded` | Descarregada no próximo depósito. | "Em trânsito" |
| `Inventory in a warehouse` | Registrada no armazém de um depósito. | "Em trânsito" |
| `shipment in depo` | No depósito. | "Em trânsito" |
| `in progress - advice required` | Em trânsito; a entrega precisa ser avisada/agendada previamente com o destinatário. | "Em trânsito" |
| `in progress - fix delivery` | Em trânsito, para entrega em data fixa. | "Em trânsito" |
| `in delivery` | Em rota de entrega. | "Saiu para entrega" |
| `arrived to the delivery place` | O caminhão chegou ao endereço de entrega. | "Saiu para entrega" |
| `delivered` | Entregue. | "Entregue" |
| `delivered D&D` | Entregue e descarregada pelo motorista. | "Entregue" |
| `not delivered - self collection` | Não entregue: marcada para retirada no depósito (embora nova saída ainda possa ocorrer). Consulte a filial indicada no rastreador antes de ir. | "Pronta para retirada" |
| `not delivered - consignee not available` | O motorista não encontrou você no local. | "Tentativa de entrega" |

**Outras entregas frustradas.** `not delivered - delivery refused`, `not delivered - wrong address`, `not delivered - truck with lift required` (precisa de caminhão com plataforma elevatória) e `not delivered - wrong/missing docs` detalham o motivo; o Peek exibe "Tentativa de entrega".

**Retenções e imprevistos.** `not delivered - breakdown lorry` e `not delivered - strike` indicam atraso na estrada; `not delivered - at sender disposal` significa retenção aguardando instruções do remetente; `cancelled` indica transporte cancelado. O Peek exibe "Precisa de atenção".

**Entregue com ressalva.** `delivered - damages`, `delivered - discrepancies`, `delivered - partly returned` e `delivered with delay` confirmam a entrega apontando o problema ocorrido; o Peek mostra "Entregue".

**Alterações de plano.** `new delivery date agreed with consignee` e `shipment parameters updated` alteram data ou dados sem movimentação física da carga.

**Alfândega.** Remessas aduaneiras exibem etapas como `customs: clearance in progress` e `customs clearance completed`; `customs: lack of invoices / incomplete invoices` indica falta de faturas, então fale com a loja. Veja [alfândega](guide:customs) e [status de rastreamento](guide:tracking-statuses).

## Como a Raben entrega: paletes, descarga e prazos

O FAQ de paletes da Raben Polônia detalha o funcionamento local; adicionais são contratados pelo cliente da Raben (em geral a loja):

- **Descarga.** A descarga cabe ao destinatário: a Raben não responde por ela nem fornece empilhadeira ou rampa por padrão. O contratante pode contratar descarga manual pelo motorista (HUN) ou descarga no local de entrega (UNLO). Em itens pesados, confirme com a loja o que foi contratado.
- **Prazos.** Na Polônia, um palete leva um ou dois dias úteis conforme o serviço contratado; no exterior varia por país. O cliente também pode contratar data fixa (FIX), entrega até 8:00, 10:00, 12:00 ou 16:00 (ND 08 a ND 16) ou dia e hora combinados (WXACT).
- **Ausência na entrega.** Quando a entrega falha por responsabilidade do destinatário, a Raben pode cobrar taxa do contratante.
- **Fotos de entrega.** Com Picture Confirming Delivery (PCD), serviço gratuito escolhido pelo contratante, você não precisa estar presente: o motorista deixa a mercadoria onde indicado e fotografa a etiqueta, a carga e ambos juntos, servindo as fotos de comprovante.

## Envio Raben atrasado, danificado ou não entregue

**Sem movimento.** Se ficar dias sem novidade, fale com a loja ou com a filial Raben exibida no rastreador. Veja [por que o rastreamento para de atualizar](guide:tracking-not-updating).

**Atraso.** Na UE, segundo o portal Your Europe, a loja tem até 30 dias para entregar salvo acordo diferente; depois disso, você pode conceder prazo extra e então cancelar com reembolso.

**Danificado ou extraviado.** O vendedor responde por danos até você receber o produto, e o Your Europe orienta procurar o vendedor primeiro. Veja [encomenda danificada](guide:damaged-parcel) e [encomenda extraviada](guide:lost-parcel). Regras próprias da Raben:

- **Polônia:** a Raben Logistics Polska aceita reclamações por escrito do remetente ou destinatário via myClaim (formulário on-line sem necessidade de conta myRaben) ou por correio, fax ou e-mail. Conteste danos visíveis na entrega e reporte danos ocultos em até **7 dias** da entrega (em transporte internacional, sem contar domingos e feriados). A Raben responde em **30 dias** após receber toda a documentação. Envios domésticos consideram-se extraviados a partir do **31º dia** após a data prevista.
- **Alemanha:** a Raben Alemanha orienta conferir embalagem e volumes na entrega e registrar danos visíveis detalhadamente na nota de entrega com assinatura do motorista; comunique por escrito à filial responsável, e danos ocultos assim que descobertos, em no máximo **7 dias** da entrega.

## Contato e atendimento da Raben

Nos sites da Raben:

- **Contato da sua remessa:** a Raben orienta acionar o responsável dedicado exibido na página de status da remessa (não exibido no Peek).
- **Horário:** o atendimento da Raben Polônia funciona de segunda a sexta, das 8:00 às 16:00 (horário da Polônia), com meta de resposta em 3 horas e atendimento padrão em polonês.
- **Filiais:** a [página de contato da Raben](https://www.raben-group.com/contact) localiza depósitos por código postal e lista filiais por país; cada site nacional (como `polska.raben-group.com`) informa seus próprios contatos.

## Acompanhe um envio Raben com o Peek

Como o formato de 15 dígitos é compartilhado com BRT e Yunda, o Peek não distingue a Raben apenas pelo número. Cole assim mesmo: o Peek acompanha mais de 4.500 transportadoras. Ao adicionar, consulta Raben e BRT — e Ozon em último caso se nenhuma reconhecer ("Consultando serviços de rastreamento…") — exibindo "Raben está com esta encomenda." quando o rastreador da Raben reconhece o código; se várias reconhecerem, você escolhe. Se nenhuma reconhecer ainda, aparece "Ainda sem histórico de rastreamento" enquanto o Peek continua consultando, ou "Aguardando Raben" depois que você escolhe Raben. Para selecionar manualmente, clique em "Trocar" e escolha "Raben" em "Compatível com este código" ou busque por "Raben Group"; em um envio já adicionado, use "Trocar transportadora".

Links de rastreamento de `myraben.com` ou `oftc.myraben.com` (`…/link/ShipmentInformation?ShipmentNumber=…`) já atribuem o envio à Raben na hora. Se seu código Raben tiver outro formato, escolha Raben manualmente: o Peek consulta qualquer código de 4 a 40 letras e dígitos com ao menos um dígito.

O Peek lê o rastreador da Raben em inglês: exibe o status, cada etapa no texto da Raben com cidade e país, a data prevista (ou, quando a rota do motorista define, a janela de horário), o peso e o horário de entrega, convertendo os horários para o fuso do seu aparelho. Etapas que apenas mudam data ou dados mantêm o status anterior. Quantidade de volumes, cubagem, barra de progresso, observações e contato da filial ficam de fora; o rastreador público não informa nomes de remetente ou destinatário, endereço, ponto de retirada, serviço nem comprovante.

Com o envio aberto no Peek, ou no dia da entrega com notificações ligadas, o Peek consulta a Raben diretamente até a cada 10 minutos — e até a cada 2 minutos no dia em que sair para entrega a partir das 6h no relógio do país da última movimentação (ou meia hora antes de abrir a janela informada pela Raben), enquanto o próprio rastreador da Raben responder. Após dois dias sem novidades (12 horas em "Etiqueta criada" ou "Recebida pela transportadora"), verifica de hora em hora; depois de sete dias em "Etiqueta criada", a cada 6 horas. Após quatro dias sem nova movimentação em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito", exibe "Sem atualizações desde …" e "É comum uma encomenda ficar sem atualizações em voos longos, na alfândega ou no fim de semana. O Peek continua consultando Raben." Você pode escolher notificações para "Cada movimentação", "Etapas importantes" ou "Só a entrega". O Peek não contata a Raben ou a loja, não agenda descarga nem abre reclamações por você.

## Perguntas sobre o rastreamento da Raben

### O que é o Raben Group?

É um grupo familiar europeu de logística fundado em 1931 em Winterswijk (Países Baixos) e sediado em Robakowo, perto de Poznań (Polônia), transportando paletes e cargas fracionadas por rodovia em 17 países.

### Como rastrear uma remessa da Raben?

Digite o código na caixa "Shipment tracking" em raben-group.com e clique em "Search" para abrir o rastreador em uma nova aba, ou cole o número ou link na caixa acima.

### O motorista da Raben vai descarregar meu pedido?

Pelas regras da Raben Polônia, somente se a empresa contratante comprou o serviço adicional de descarga (HUN ou UNLO); por padrão a descarga cabe ao destinatário. A etapa `delivered D&D` indica que o motorista entregou e descarregou.

### Quanto tempo a Raben leva para entregar?

Na Polônia, um ou dois dias úteis conforme o serviço contratado pelo remetente; no exterior varia segundo o país de destino. O rastreador da Raben mostra a data prevista.

### Quem devo contatar em caso de entrega danificada da Raben?

Primeiro a loja: na UE o vendedor responde até você receber o produto. Na Polônia você também pode abrir reclamação por escrito direto na Raben Logistics Polska, reportando danos ocultos em até 7 dias da entrega.

:::sources
- [Raben Group: home page](https://www.raben-group.com/) – tracking box, "Search", new tab, 17 countries
- [Raben Group: Customer Zone](https://www.raben-group.com/customer-zone) – a number per shipment, expected date, the status page's contact
- [Raben Group: Contact](https://www.raben-group.com/contact) – depot search, branches by country
- [Raben Group: Road transport and international transport](https://www.raben-group.com/services/road-network) – groupage
- [Raben Group: E-commerce](https://www.raben-group.com/e-commerce) – delivery for online shops
- [Raben Group: Picture Confirming Delivery](https://www.raben-group.com/it-solutions/pcd) – free service, photos, delivery note
- [Raben Group: Raben Group in Europe](https://www.raben-group.com/about-us/raben-group-in-europe) – 1931, Winterswijk, family-owned, 17 countries
- [Raben Group: Imprint](https://www.raben-group.com/imprint) – Robakowo near Poznań
- [Raben Poland: Pytania i odpowiedzi - Transport paletowy](https://polska.raben-group.com/strefa-klienta/pytania-i-odpowiedzi-transport-paletowy) – unloading, HUN, UNLO, timing, FIX, ND, WXACT, failed delivery fee, PCD, customer service hours, Polish
- [Raben Poland: Strefa uczestnika przewozu](https://polska.raben-group.com/strefa-uczestnika-przewozu) – recipient's details from whoever ordered the transport
- [Raben Logistics Polska: Claims](https://polska.raben-group.com/en/customer-zone/raben-logistics-polska/claims-raben-logistics-polska) – who claims, how, visible and hidden damage, loss, 30-day answer
- [Raben Germany: Transportschäden – Wir helfen Ihnen](https://deutschland.raben-group.com/kundenbereich/reklamationen) – checks at delivery, written report, 7 days
- [Your Europe: Your rights when shopping in the EU](https://europa.eu/youreurope/citizens/consumers/shopping/shopping-consumer-rights/index_en.htm) – 30 days, trader responsible, contact the trader first
- [Universal Parcel Scraper: Raben](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/raben) – number shape, links, references, what the tracker shows, steps and stages, what Peek reads
:::
