---
title: Rastreamento Yanwen: códigos UL…YP, status e entrega
description: Código com duas letras (como UL ou UK), 9 dígitos e YP no fim: rastreie sua encomenda Yanwen, entenda cada status e veja quem entrega no destino.
slug: rastreamento-yanwen
published: 2026-11-24
updated: 2026-11-24
---

A Yanwen é uma empresa chinesa de logística fundada em Pequim em 1998 que transporta pacotes internacionais para lojas online e os repassa a uma transportadora local para a entrega de última milha. Você pode acompanhar seu envio no site da própria Yanwen, em [track.yw56.com.cn](https://track.yw56.com.cn/en/index), ou colando o código na caixa acima. O número é fornecido pela loja ou marketplace nos detalhes de envio do pedido.

## Código de rastreamento Yanwen: formatos mais comuns

A Yanwen não divulga exemplos de códigos nem central de ajuda voltada a destinatários. Os formatos usados em seus envios (com dígitos inventados) são:

| Código (inventado) | A quem pertence |
| --- | --- |
| `BYS482917365` | `BYS` seguido de 9 dígitos: Yanwen. |
| `UL482917367YP` | Duas letras, 9 dígitos e `YP` no final: Yanwen (`UL` e `UK` são prefixos frequentes). |
| `YWABC482917364058` | `YW`, três letras e 12 dígitos: código de última milha para entregas nos EUA. |
| `RR482917367CN` | Mesmo formato postal terminado em `CN`: [China Post](carrier:china-post), não Yanwen. |
| `YT4829173640581926` | Não é Yanwen, e sim [YunExpress](carrier:yunexpress). |
| `LP48291736405812` | Não é Yanwen, e sim Cainiao (código logístico do AliExpress). |

Códigos de formato postal da Yanwen terminam sempre em `YP`; qualquer outra terminação pertence a outro correio. A página de resultado da Yanwen exibe dois códigos: o "Tracking Number" (da própria Yanwen) e o "Last Mile Tracking" (da transportadora que faz a entrega no país de destino). Se a loja informou esse código local, o site da Yanwen também o encontra. Sem nenhum código, peça ao vendedor.

O portal da Yanwen aceita até 100 números por consulta, mas o autoatendimento cobre apenas remessas emitidas nos últimos 12 meses e não rastreia produtos da China Post.

## Significado dos status de rastreamento da Yanwen

O site de rastreio da Yanwen está disponível apenas em inglês e chinês, e as movimentações da própria Yanwen aparecem em inglês em ambas as versões. Na ordem em que costumam surgir:

| A Yanwen informa | O que significa para você |
| --- | --- |
| `Order processed by shipper` | A loja registrou o envio; a Yanwen ainda não está com o pacote. |
| `Yanwen Pickup Scan` | A Yanwen recebeu o pacote na China. |
| `Yanwen facility - Outbound` | Saiu do centro de triagem da Yanwen. |
| `Tendered for Delivery` | Transferido para o trecho internacional minutos após sair da triagem e antes da alfândega de exportação; apesar do nome, ainda não é saída para entrega. |
| `International shipment release - Export` | Desembaraço de exportação concluído na China. |
| `Port of departure - Departure` | Entregue à companhia aérea e embarcado. |
| `Port of destination - Arrival` | Chegou ao país de destino ou a um centro próximo. |
| `International shipment release - Import` | Liberado pela alfândega de importação. |
| `Batch delivery to carrier` | Repassado à transportadora local; a partir daqui surgem as leituras dela. |
| `Parcel is out for delivery.` | Saiu para entrega (serviço próprio da Yanwen nos EUA). |
| `<User not at home>` | Tentativa de entrega sem sucesso repassada pela parceira local. |
| `Delivered.` | Entregue, na redação de uma transportadora local. |

`Order Submited.` e `Waybill Generated` têm o mesmo sentido da primeira linha: só existe a etiqueta, e o pacote pode não ter saído da loja. `Shipping Order Created.` é um aviso de pedido de parceiro — o Peek mantém a etapa anterior. `Pick up by local carrier at destination port` equivale a `Batch delivery to carrier`. Depois disso aparecem os textos da parceira local (como `Out for Delivery` ou `Delivered, In/At Mailbox` do USPS). Uma transportadora local envia textos em maiúsculas sem identificar o nome — `ARRIVE TRANSITHUB`, `TASK ASSIGNED` e `ORDER SORTED` — todos exibidos como "Em trânsito" no Peek. Na Itália, `First parcel scan along Poste Italiane’s logistic network` marca a primeira leitura após o repasse ("Em trânsito"). Mensagens precoces como `Shipping Label Created, USPS Awaiting Item` ou `The recipient has selected a Packstation as the new delivery address.` podem surgir ainda com o pacote na China; o Peek mantém a etapa vigente sem atribuir local falso.

Alguns envios são repassados à [China Post](carrier:china-post) após `Yanwen facility - Outbound`, exibindo leituras em chinês como `中国邮政已收取邮件` (repasse) ou `【…】退回，备注:安检退回` (devolução interna na triagem de segurança antes de seguir viagem, exibida como "Precisa de atenção" no Peek).

Acima das movimentações, a Yanwen classifica o envio em categorias: `Info_Received` (etiqueta), `In_Transportation` (voo, alfândega e trecho local), `End_of_Tracking` (a Yanwen encerrou o acompanhamento — não prova entrega nem extravio) e `Not_Found` (`No information was found`). Pacotes entregues mostram a duração total, como "Delivered（7d）".

## Entrega local, retirada e tentativa sem sucesso

A Yanwen não faz entregas próprias na Europa nem no Brasil: trabalha "com transportadoras locais para concluir a entrega de última milha". Quando identifica a parceira, a página de resultado exibe um quadro com "Distributor", "Distributor Website" e "Contact Number" (em parte dos envios o quadro fica em branco).

A empresa que entrega varia conforme o país e o serviço contratado pela loja: no Reino Unido há linhas via Royal Mail, Yodel, Hermes e Evri; nos EUA, via USPS, UniUni ou YanwenExpress; no Canadá, via Emile ou UniUni; e na Austrália, com repasse em lote à J&T Express Australia (cujas leituras aparecem no histórico da Yanwen). Para compras enviadas ao Brasil, a Receita Federal explica como os tributos são cobrados no [Programa Remessa Conforme](https://www.gov.br/receitafederal/pt-br/assuntos/aduana-e-comercio-exterior/manuais/remessas-postal-e-expressa/programa-remessa-conforme-o-que-e-como-funciona).

Tentativas de entrega, avisos, reagendamento e prazo de guarda seguem as regras da transportadora local. `Available_For_Pick_Up` indica disponibilidade em ponto de retirada e `Undelivered` indica tentativa sem sucesso: use o código "Last Mile Tracking" e o contato da distribuidora para tratar a nova entrega. Na página de resultado, cada leitura traz seu deslocamento GMT.

## Encomenda Yanwen sem atualização, perdida ou devolvida

**Ainda sem histórico.** Enquanto constar `Info_Received`, a loja gerou a etiqueta mas pode não ter postado o pacote; confirme com o vendedor.

**Parada há vários dias.** Depois do repasse à transportadora local, consulte o código "Last Mile Tracking" diretamente no site dela. Veja [por que o rastreamento para de atualizar](guide:tracking-not-updating).

**Entregue, mas você não recebeu.** Como a entrega foi feita pela parceira local, comece falando com ela e depois com a loja. Veja [entregue, mas não recebido](guide:delivered-not-received).

**Extravio, avaria ou devolução.** A Yanwen não divulga processo de reembolso ou indenização para destinatários: quem tem contrato com ela é a loja ou o marketplace, que deve reembolsar ou reenviar. `Exception` e `Returned` são as categorias da Yanwen para esses casos.

**Envios antigos.** Códigos com mais de um ano saem da busca online; segundo aviso da Yanwen, a central telefônica ainda consegue consultá-los.

## Telefone e atendimento ao cliente da Yanwen

Os canais divulgados pela Yanwen são:

- **Central telefônica na China:** `400-108-5656`, de segunda a sábado, das `9:00` às `18:00` (número chinês de serviço, sem confirmação de atendimento internacional ou idiomas).
- **E-mail:** `marketing@yw56.com.cn`, único endereço listado no site em inglês (não apresentado como suporte ao comprador).
- **Outros canais:** chat online no canto inferior direito de `yw56.com.cn`, portal do cliente e conta no WeChat, todos em chinês e voltados a vendedores.
- **Última milha nos EUA:** a página de suporte da YanwenExpress não informa telefone nem e-mail.

A Yanwen não lista contatos locais na Europa nem no Brasil. Para questões do pedido, a loja é mais rápida; para a entrega final, use a transportadora e o código exibidos no quadro de distribuição.

## Rastrear uma encomenda Yanwen com o Peek

O [Peek](/) identifica na hora códigos `BYS`, códigos `YP` e códigos `YW` dos Estados Unidos. Um código `YP` com dígito verificador incorreto não é reconhecido automaticamente: toque em "Trocar" para selecionar Yanwen. Links de `track.yw56.com.cn` contendo `nums=` são reconhecidos como Yanwen com qualquer código inserido, inclusive o código local.

O Peek exibe a etapa, o histórico com cidade ou região no mapa, a hora da entrega, o país de destino e a transportadora local com seu número ("Entrega por" aquela transportadora, ou "Anunciada a …, ainda não recebida" enquanto a parceira local ainda não registrou leitura própria). Quando a Yanwen informa uma parceira conhecida pelo Peek e o código é compatível, o Peek consulta essa transportadora e passa a acompanhá-la assim que houver avanço mais recente. A Yanwen não informa data prevista; o Peek não exibe ponto de retirada e remove referências de pedido, contatos e códigos postais.

Os horários das leituras são lidos com os deslocamentos GMT da Yanwen e aparecem no horário do seu aparelho; leituras repetidas são removidas. Leituras `In Transit to Next Facility` repassadas do USPS à meia-noite mostram apenas o dia. `Delivered` e `Out for delivery` aparecem nas etapas do Peek; as demais linhas mantêm o texto original, incluindo `Delivered.`. Enquanto você mantém o pacote aberto, ou no dia da entrega com alertas ativados, o Peek verifica atualizações até a cada 10 minutos — e até a cada 2 minutos a partir das 6h no horário local no dia em que sai para entrega, enquanto o rastreador próprio da Yanwen responde; fora disso, verifica de hora em hora. Pelos serviços de rastreamento, checa de hora em hora, a cada 3 horas após um dia sem novidades e até a cada 15 minutos no dia da entrega enquanto o pacote estiver aberto ou com alertas ligados. Quando outra transportadora assume a entrega, o Peek segue o ritmo dela. Após dois dias sem novidades (ou 12 horas em "Etiqueta criada" ou "Recebida pela transportadora"), checa de hora em hora. Você pode escolher alertas para "Cada movimentação", "Etapas importantes" ou "Só a entrega". O Peek não pode contatar a Yanwen nem a loja em seu nome.

## Perguntas frequentes sobre o rastreamento Yanwen

### Onde está meu pacote da Yanwen?

A leitura mais recente indica a etapa atual e a cidade ou região, quando informada. Após `Batch delivery to carrier`, uma transportadora do seu país já recebeu o pacote: procure o nome e o código dela na página da Yanwen.

### O que significa `Yanwen facility - Outbound`?

Indica que o pacote saiu do centro de triagem da Yanwen na China. Em seguida costumam vir `Arrived at domestic terminal station`, o desembaraço de exportação e o embarque aéreo.

### A Yanwen é confiável?

Sim. A Yanwen é uma operadora logística fundada em Pequim em 1998 com portal próprio de rastreamento, e informa atuar como provedora recomendada do AliExpress e do eBay desde 2015.

### Quanto tempo a Yanwen demora para entregar?

A Yanwen não divulga prazos de entrega ao destinatário; use a estimativa exibida pela loja ou marketplace no seu pedido. Para entender o trajeto habitual, veja [como rastrear encomendas da China](guide:tracking-from-china).

:::sources
- [Yanwen: YW Tracking](https://track.yw56.com.cn/en/index) – portal de rastreamento, idiomas, colunas de resultado, categorias, quadro de distribuidor e central `400-108-5656`
- [Yanwen: 燕文追踪 (chinês)](https://track.yw56.com.cn/) – leituras em inglês e aviso sobre China Post
- [Yanwen: YANWEN EXPRESS (inglês)](https://www.yw56.com.cn/en/) – fundação em 1998 em Pequim, entrega final por parceiros locais, central e e-mail
- [Yanwen: Company Profile](https://www.yw56.com.cn/webfile/Profile/) – recomendação pelo AliExpress e eBay
- [Yanwen: Contact Us](https://www.yw56.com.cn/webfile/ContactUs/) – telefone e e-mail `marketing@yw56.com.cn`
- [Yanwen: 联系我们 (contato, chinês)](https://www.yw56.com.cn/webfile/联系我们/) – horário da central, linhas via Royal Mail, Yodel, Hermes e Evri
- [Yanwen: Aviso oficial de canais de rastreamento](https://www.yw56.com.cn/webfile/ANNOUNCEMENTS/2025/06-30/7345353199651393536.html) – limite de 100 códigos, consulta online de 1 ano, busca por telefone, chat, portal e WeChat
- [YanwenExpress: Página inicial](https://www.yanwenexpress.com/) – entrega de última milha nos EUA
- [YanwenExpress: Customer support](https://www.yanwenexpress.com/contactus.html) – ausência de telefone ou e-mail divulgado
- [Receita Federal: Programa Remessa Conforme](https://www.gov.br/receitafederal/pt-br/assuntos/aduana-e-comercio-exterior/manuais/remessas-postal-e-expressa/programa-remessa-conforme-o-que-e-como-funciona) – regras tributárias para encomendas internacionais no Brasil
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – comportamento no Peek, repasse para J&T Express Australia, avisos da DHL e USPS e primeira leitura da Poste Italiane
:::
