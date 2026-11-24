---
title: Rastreamento GOFO Express: códigos GFUS, fotos e suporte
description: Rastreie encomendas GOFO Express nos EUA: códigos GFUS, GF, CR e MGP, significado dos status em inglês, fotos de entrega e atendimento.
slug: rastreamento-gofo-express
published: 2026-11-24
updated: 2026-11-24
---

A GOFO Express é uma transportadora de encomendas nos Estados Unidos que coleta pacotes nos armazéns dos remetentes e os movimenta pelos seus hubs até a estação de entrega que atende seu endereço. Cole o número na caixa acima para rastrear seu pacote, ou consulte diretamente no site da GOFO. O código é fornecido pela loja que enviou seu pedido, normalmente no e-mail de envio ou na página do pedido; se não encontrar, veja [como encontrar o código de rastreamento](guide:find-tracking-number).

## Código de rastreamento GOFO Express: formatos nos EUA

Os códigos americanos da GOFO aparecem em quatro formatos principais (exemplos com dígitos inventados):

| Formato (inventado) | O que é |
| --- | --- |
| `GFUS48291736058291` | `GFUS` seguido de 14 dígitos: código padrão da GOFO nos EUA (o Peek interpreta uma letra `O` digitada logo após `GFUS` como zero). |
| `GF4829173605829` | `GF` seguido de 13 dígitos: série antiga ainda rastreada pela GOFO. |
| `CR482917360582` | `CR` seguido de 12 dígitos: outra série antiga. |
| `MGP4829173605829` | `MGP` seguido de 13 dígitos: código adicional que alguns pacotes carregam junto ao `GFUS`. |

Algumas encomendas possuem ao mesmo tempo um código `GFUS` e um código `CR` ou `MGP`, e qualquer um deles localiza o mesmo pacote. Em pedidos do DHgate entregues pela GOFO para a DHLink, o rastreio da GOFO também aceita o código `FC` + 13 dígitos da DHLink.

**Outras redes GOFO e códigos parecidos.** A GOFO opera redes separadas em outros países: `GFCA` (+14 dígitos) pertence à GOFO Canada, `GFFR`/`CIFR` à GOFO France, `GFIT`/`CIIT` à GOFO Italy, `GFES`/`CIES` à GOFO Spain e `GFNL`/`CINL` à GOFO Netherlands — o Peek reconhece e encaminha cada um à rede certa. Códigos iniciados por `YT` pertencem à [YunExpress](carrier:yunexpress), mesmo quando a entrega final nos EUA é feita pela GOFO (ambos os sites aceitam o mesmo número `YT`).

## Significado dos status de rastreamento da GOFO Express

As leituras da GOFO vêm em inglês, geralmente nesta ordem aproximada:

| Status da GOFO | O que significa para você | O Peek exibe |
| --- | --- | --- |
| `Shipping Label Created` | O remetente gerou a etiqueta; a GOFO ainda não tem o pacote. | "Etiqueta criada" |
| `Pick Up Start` | A GOFO saiu para coletar o pacote com o remetente, ainda sem recebê-lo. | "Etiqueta criada" |
| `Pickup Exception, GOFO has not Received the Package from the Warehouse yet.` | A GOFO ainda não recebeu o pacote do armazém do remetente. | "Etiqueta criada" |
| `Pick Up Success` | A GOFO coletou o pacote com o remetente. | "Recebida pela transportadora" |
| `Arrived at GOFO Regional Origin Hub` | Chegou ao primeiro hub regional da GOFO. | "Recebida pela transportadora" |
| `Arrived at Sorting Center` | Chegou a um centro de triagem antes do hub regional. | "Em trânsito" |
| `Arrived at GOFO Regional Hub` | Chegou a um hub regional a caminho do destino. | "Em trânsito" |
| `Processing at GOFO Facility` | Em triagem em uma unidade da GOFO. | "Em trânsito" |
| `Departed GOFO Regional Hub` | Saiu do hub regional. | "Em trânsito" |
| `Loaded on Vehicle` | Carregado em veículo para o próximo trecho. | "Em trânsito" |
| `In Transit to Next Facility` | Em trânsito entre unidades da GOFO. | "Em trânsito" |
| `Arrived at GOFO Delivery Station` | Chegou à estação local de entrega (a GOFO estima entrega em 1–2 dias corridos após esta etapa). | "Em trânsito" |
| `Departed GOFO Delivery Station` | Saiu da estação local de entrega. | "Em trânsito" |
| `Out for Delivery` | Saiu para entrega com o motorista hoje. | "Saiu para entrega" |
| `Delivered` | Entregue pelo motorista. | "Entregue" |
| `Delivery Exception, Business Closed` | O motorista não pôde entregar porque o comércio estava fechado. | "Tentativa de entrega" |
| `Incorrect/Insufficient Address, Additional Details Required for Delivery.` | Endereço incorreto ou incompleto (às vezes registrado no dia seguinte a uma tentativa falha); aguarda complemento. | "Precisa de atenção" |
| `Label Cancelled` | A etiqueta foi cancelada; nenhum pacote seguirá sob este código. | "Precisa de atenção" |

A leitura de entrega da GOFO pode trazer um detalhe após a vírgula, e o portal da GOFO mostra também uma data prevista de entrega.

## Entrega da GOFO: fotos de comprovação e tentativas falhas

**Fotos de entrega.** Informando o nome do destinatário usado no pedido na página de rastreamento da GOFO, é possível visualizar o comprovante de entrega — na maioria das vezes com foto do local onde o pacote foi deixado. O Peek não exibe essas fotos.

**Tentativa sem sucesso.** `Delivery Exception` indica tentativa falha (o motivo aparece após a vírgula, como `Business Closed`); o Peek exibe "Tentativa de entrega" junto com a frase original da GOFO. A GOFO informa tentar novamente normalmente em até **24 horas**. Problemas de endereço (`Incorrect/Insufficient Address…`, como falta de número do apartamento) podem devolver o pacote ao armazém local: avise a loja imediatamente. A GOFO não permite retirada presencial pelo destinatário em suas unidades.

## Encomenda GOFO atrasada, não recebida ou extraviada

- **Sem movimentação:** se aparecer apenas `Shipping Label Created`, `Pick Up Start` ou `Pickup Exception…`, a GOFO ainda não está com o pacote — confirme o envio com a loja. Após quatro dias sem nova leitura antes da saída para entrega, o Peek mostra o link "Pedir ajuda a GOFO Express" para a página de suporte. Veja [por que o rastreamento para de atualizar](guide:tracking-not-updating).
- **Consta entregue, mas você não recebeu:** confira a foto de entrega no site da GOFO, verifique a caixa de correio, vizinhos e portaria, depois acione a GOFO e a loja. Veja [entregue, mas não recebido](guide:delivered-not-received).
- **Extravio ou devolução:** fale com a loja — a GOFO informa que o remetente pode abrir uma consulta com a GOFO por você; peça também à loja reembolso ou reenvio. A GOFO devolve encomendas avariadas, com endereço incompleto ou em endereços não atendidos (como endereços militares), e um retorno já iniciado não pode ser cancelado. Veja [encomenda perdida](guide:lost-parcel) e [devolvida ao remetente](guide:returned-to-sender).

## Atendimento ao cliente da GOFO Express nos EUA

Para questões do pedido, comece pela loja. Para a entrega, a [página de atendimento da GOFO](https://www.gofo.com/us/customer-service) (aberta pelo link "Pedir ajuda a GOFO Express" no Peek) lista os seguintes canais (horário do Leste dos EUA):

- **Telefone (destinatários):** `+1 (949) 688-6032`, segunda a sexta das `9h` às `21h`, sábado e domingo das `11h` às `19h`.
- **WhatsApp:** `+1 (787) 469-5811`, segunda a sexta das `9h` às `21h`, sábado e domingo das `11h` às `20h`.
- **E-mail e formulário:** `cs@mail.gofo.com`, além de formulário online (que solicita o número do *waybill*) e chat na mesma página.

## Rastrear encomendas GOFO Express com o Peek

O [Peek](/) identifica GOFO Express na hora para códigos `GFUS` ("Transportadora detectada"). Para códigos `GF`, `CR` ou `MGP`, consulta a GOFO e atribui automaticamente quando confirmados (espaços não interferem). Enquanto a GOFO não reconhece o código, o Peek continua consultando; uma encomenda atribuída à GOFO Express (um código `GFUS` ou um em que você escolheu a GOFO) mostra "Aguardando GOFO Express" nesse meio-tempo. Se dois dias depois a GOFO ainda não reconhecer o código dessa encomenda e nenhum outro serviço responder por ele, o Peek exibe "Depois de dois dias, a transportadora ainda não reconhece este código. Confira o código, escolha outra transportadora ou exclua a encomenda." e passa a checar diariamente (e semanalmente após uma semana). Em pedidos do AliExpress acompanhados pela [Cainiao](carrier:cainiao) sob um código `GFUS`, quando a GOFO mostra apenas a etiqueta por dois dias, o Peek consulta também a Cainiao para trazer o trajeto anterior. Você pode ainda escolher GOFO Express via "Trocar" ou colar links de rastreamento da GOFO com `searchID=`.

O Peek consulta o rastreamento americano da GOFO até a cada 30 minutos (inclusive quando sai para entrega, aguardando 1 hora após falha); após dois dias sem novidades (12 horas em "Etiqueta criada" ou "Recebida pela transportadora"), verifica de hora em hora. Quando acompanha via serviços de rastreamento, consulta de hora em hora, a cada 3 horas após um dia sem novidades e até a cada 15 minutos no dia da entrega enquanto o pacote estiver aberto ou com alertas ligados. O Peek exibe etapa, cidade e estado de cada leitura no mapa, horário exato, hora da entrega, destino e data prevista até a saída/entrega/devolução, removendo das leituras a linha de suporte da GOFO (telefone e e-mail) e omitindo fotos, coordenadas e peso. Alertas disponíveis: "Cada movimentação", "Etapas importantes" ou "Só a entrega". O Peek não pode contatar a GOFO nem a loja em seu nome.

## Perguntas frequentes sobre o rastreamento GOFO Express

### A GOFO Express é confiável?

Sim. A GOFO Express é uma transportadora americana com sistema próprio de rastreamento que informa cobrir mais de 12.000 CEPs (alcançando 75% da população dos EUA), além de operar redes no Canadá, França, Itália, Espanha e Holanda.

### O que significa `Shipping Label Created` na GOFO?

Indica que o remetente gerou a etiqueta, mas a GOFO ainda não recebeu o pacote. Em seguida costumam surgir `Pick Up Start` e `Pick Up Success`.

### Por que meu código GOFO não funciona?

Confirme se é um código da GOFO nos EUA (`GFUS`, `GF`, `CR` ou `MGP`): prefixos como `GFCA` ou `GFFR` pertencem a outros países e `YT` à YunExpress (envios do DHgate podem usar `FC`). Códigos recém-emitidos podem levar um tempo para aparecer; já `Label Cancelled` indica etiqueta cancelada — peça o código correto à loja.

### Por que meu pedido do AliExpress tem um código da GOFO?

A [Cainiao](carrier:cainiao) acompanha alguns pedidos do AliExpress entregues nos EUA usando diretamente o código `GFUS` da GOFO. No Peek, enquanto a GOFO exibe apenas a etiqueta, a Cainiao também é consultada para mostrar o trecho inicial. Veja [pedidos do AliExpress](guide:aliexpress-orders).

:::sources
- [GOFO: Customer Service](https://www.gofo.com/us/customer-service) – telefone `+1 (949) 688-6032`, WhatsApp `+1 (787) 469-5811`, e-mail `cs@mail.gofo.com`, horários, ausência de retirada presencial, nova tentativa em 24h e prazo de 1–2 dias a partir da estação
- [GOFO: Home](https://www.gofo.com/us/) – cobertura de mais de 12.000 CEPs e 75% da população dos EUA
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formatos `GFUS`/`GF`/`CR`/`MGP`/`FC`/`YT`, redes internacionais da GOFO, mapeamento de status, integração com Cainiao e comportamento no Peek
:::
