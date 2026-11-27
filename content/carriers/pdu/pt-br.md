---
title: Rastreamento PDU (Parcel Delivery Union): códigos e status
description: Rastreie encomendas internacionais da PDU (Parcel Delivery Union / 友递联): códigos terminados em PD, status de trânsito e última milha, alfândega e contatos.
slug: pdu-tracking
published: 2026-11-26
updated: 2026-11-26
---

A **PDU** (*Parcel Delivery Union*, operada pela *Shanghai Youdilian Technology Co., Ltd.*, em chinês *友递联科技*) é uma provedora de logística inteligente internacional sediada em Xangai, fundada em 2022 ([pduni.com](https://www.pduni.com/)). Operando filiais próprias e centros locais em mais de 10 países na América do Norte, Europa, África e Ásia, a PDU oferece a lojistas de e-commerce e fabricantes desembaraço aduaneiro ponta a ponta, coleta, entrega de última milha no exterior e cobrança na entrega (COD). Você pode consultar uma remessa no portal em inglês da PDU em [pduni.com/MV/en/customer-services/waybill-inquiry](https://www.pduni.com/MV/en/customer-services/waybill-inquiry) ou colar seu código de rastreamento na caixa acima.

## Códigos de rastreamento da PDU (sufixo `PD` e outros formatos)

Muitas encomendas internacionais da PDU trazem um código de 12 caracteres formado por duas letras, 8 dígitos e `PD`:

| Formato (inventado) | O que é |
| --- | --- |
| `MX48291736PD` | Conhecimento internacional da PDU de 12 caracteres (`MX` + 8 dígitos + `PD`). |
| `CO73918462PD` | Exemplo alternativo de código `..PD` de 12 caracteres usado em rotas internacionais. |

A página pública de consulta da PDU também aceita outros conhecimentos alfanuméricos de 6 a 40 caracteres quando você escolhe a PDU manualmente. O mesmo formato serve para os códigos da [Colis Privé](carrier:colis-prive): o Peek consulta primeiro a Colis Privé e depois a PDU, ou primeiro a PDU na China. Veja [formatos de código de rastreamento](guide:tracking-number-formats).

## O que significam os status de rastreamento da PDU

A PDU atribui a cada remessa e leitura um código geral de estado (`TRANSIT`, `COLLECTING`, `DELIVERY`, `SIGNED` etc.) acompanhado de descrição em inglês ou no idioma do destino (com frequência em espanhol nas rotas da América Latina, muitas vezes precedida por uma sigla curta da transportadora local como `ENT,Entregado`, que o Peek limpa para exibição):

| Estado ou texto da PDU | O que significa para você | O Peek mostra |
| --- | --- | --- |
| `Shipment information sent to PDU.`, `Package not found in last mile or customs process.` | Pedido eletrônico recebido pela PDU antes da entrada física ou antes de a rede de última milha criar seu primeiro registro. | "Etiqueta criada" |
| `COLLECTING` (`Being Collected`), `FINAL_COLLECTING` (`Last-Mile Collection`), `TRANSIT` (`In Transit`) | Coletada na origem ou em trânsito internacional e por centros regionais de distribuição. | "Em trânsito" |
| `Recogida en Aduanas`, `Ingreso a CEDI`, `En tránsito nacional` | Retirada da alfândega, entrada no centro de distribuição nacional (CEDI) ou em trânsito doméstico. | "Em trânsito" |
| `DELIVERY` (sem texto de saída para rota) | Entregue à rede da transportadora local de destino; o Peek mantém em trânsito até o início real da rota de entrega. | "Em trânsito" |
| `Salida a Ruta` | Em rota de entrega com o mensageiro local hoje. | "Saiu para entrega" |
| `DELIVERY_EXCEPTION` (`Delivery Exception`), `TO_BE_RETURN` (`Awaiting Return`) | Exceção de entrega registrada ou encomenda aguardando devolução ao remetente; contate o vendedor ou a transportadora local. | "Precisa de atenção" |
| `RETURNED` (`Returned`) | Em retorno ao remetente ou ao armazém de origem. | "Devolvida ao remetente" |
| `SIGNED` (`Delivered`), `Entregado` | Entregue e assinada no endereço de destino. | "Entregue" |

Observe que a mensagem inicial da PDU `Package not found in last mile or customs process.` significa apenas que a parceira local de última milha ainda não escaneou o pacote — ela **não** significa que a alfândega reteve sua encomenda.

## Desembaraço aduaneiro, entrega de última milha e COD

De acordo com [pduni.com](https://www.pduni.com/) e a [Central de Ajuda da PDU](https://www.pduni.com/MV/en/customer-services/help-center):

- **Desembaraço próprio e classificação HS por IA:** a PDU mantém sua própria rede de desembaraço aduaneiro em mais de 10 países, com correspondência automatizada de códigos HS e fluxos de pré-pagamento de impostos; segundo o FAQ da Central de Ajuda, a PDU não cobra taxa separada por declarações formais de exportação, e lojistas podem enviar documentos aduaneiros complementares on-line caso falte algo.
- **Entrega local de última milha:** assim que a alfândega do destino libera o volume (`Recogida en Aduanas` / `Ingreso a CEDI`), filiais locais ou transportadoras parceiras realizam a entrega na porta e, quando contratado, a cobrança na entrega (COD).
- **Impostos e taxas de importação:** IVA de importação ou taxas alfandegárias no destino dependem dos limites fiscais locais e de o varejista ter cobrado os tributos no checkout. Veja [encomendas retidas na alfândega](guide:customs).

## Encomenda da PDU parada, sem atualização ou extraviada

Como são as lojas virtuais e fábricas que mantêm a conta de envio com a PDU, **fale sempre primeiro com o vendedor ou marketplace** para correções de endereço, reembolsos ou reenvios.

- **Intervalos sem leitura entre a China e a entrada local:** o rastreamento costuma pausar por vários dias entre `Shipment information sent to PDU.` (ou `Package not found in last mile or customs process.`) e `Recogida en Aduanas` / `Ingreso a CEDI` enquanto a carga consolidada aérea ou marítima chega ao país de destino. Veja [por que o rastreamento para de atualizar](guide:tracking-not-updating).
- **`DELIVERY_EXCEPTION` ou `TO_BE_RETURN`:** aja rapidamente — confirme seu endereço de entrega e telefone junto ao vendedor para que a base local agende nova tentativa antes de o volume passar para `RETURNED`.
- **Marcada como entregue ou danificada:** verifique primeiro com moradores, portaria e vizinhos e abra uma reclamação com o varejista antes do fim da proteção ao comprador. Veja [entregue, mas não recebida](guide:delivered-not-received), [encomenda perdida](guide:lost-parcel), [encomenda danificada](guide:damaged-parcel) e [devolvida ao remetente](guide:returned-to-sender).

## Atendimento ao cliente da PDU e contatos em Xangai

Contatos oficiais divulgados em [PDU Waybill Inquiry](https://www.pduni.com/MV/en/customer-services/waybill-inquiry) e [PDU Help Center](https://www.pduni.com/MV/en/customer-services/help-center):

- **Razão social:** *Shanghai Youdilian Technology Co., Ltd.* (*友递联科技*, registro ICP `沪ICP备2022026205号`).
- **Linha telefônica:** `021-50837888` (`+86 21 5083 7888`).
- **E-mail:** `service@pduni.com`.
- **Sede:** Room 305, 6F, Zhonghai Building, Zhenbei Road, Putuo District, Xangai, China.
- **Rastreador on-line e ajuda:** [pduni.com/MV/en/customer-services/waybill-inquiry](https://www.pduni.com/MV/en/customer-services/waybill-inquiry) e [pduni.com/MV/en/customer-services/help-center](https://www.pduni.com/MV/en/customer-services/help-center).

> A Central de Ajuda da PDU alerta expressamente sobre golpes que usam indevidamente o logotipo ou os contatos da PDU. Nunca pague taxas alfandegárias ou de entrega por um link de SMS não verificado — confirme qualquer cobrança primeiro na loja ou no portal oficial da transportadora. Veja [golpes de SMS de entrega](guide:delivery-scam-texts).

## Acompanhe uma encomenda da PDU com o Peek

Quando você informa um código `..PD` de 12 caracteres (`MX48291736PD` ou `CO73918462PD`, inventados), o [Peek](/) consulta Colis Privé e PDU em sequência (PDU primeiro na China) e exibe "PDU está com esta encomenda." assim que o rastreador da PDU reconhece o código. Se seu código da PDU seguir outro formato de 6 a 40 caracteres, toque em "Trocar" ao adicionar ou em "Trocar transportadora" na tela da encomenda e selecione **PDU** (pesquisar "Parcel Delivery Union" ou "友递联" também a encontra).

O Peek lê o rastreador da PDU e mostra a etapa ("Etiqueta criada", "Em trânsito", "Saiu para entrega", "Precisa de atenção", "Devolvida ao remetente", "Entregue"), o texto da movimentação sem a sigla inicial da parceira local (`Entregado`, em vez de `ENT,Entregado`), o local e o horário de entrega. Os códigos de estado da PDU definem a etapa e não aparecem nos aplicativos do Peek. A PDU envia o horário com fuso UTC — ou, na ausência dele, no horário de Pequim — e o Peek exibe tudo no relógio do seu aparelho. O rastreador da PDU não informa data estimada de entrega.

Enquanto você mantém a encomenda aberta no Peek, ou no dia da entrega com notificações ligadas, o Peek consulta até a cada 10 minutos; no dia em que a PDU registra `Salida a Ruta`, verifica a cada 2 minutos a partir das 6h no relógio da leitura mais recente, enquanto o rastreador da PDU responder. Caso contrário, verifica de hora em hora. Após 12 horas apenas em "Etiqueta criada" ou "Recebida pela transportadora", ou dois dias sem novidade, consulta de hora em hora de qualquer forma, e a cada 6 horas depois de sete dias em "Etiqueta criada". Quando o rastreador da PDU não responde e o Peek acompanha somente por serviços de rastreamento, consulta de hora em hora, a cada 3 horas após um dia quieto e até a cada 15 minutos no dia da saída para entrega.

Quando a leitura mais recente tem quatro dias e o pacote segue em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito", o Peek sinaliza a data dessa leitura, como "Sem atualizações desde seg 12 out". A página do pacote então exibe "É comum uma encomenda ficar sem atualizações em voos longos, na alfândega ou no fim de semana. O Peek continua consultando PDU." Após três semanas sem novas leituras, mostra "Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, fale com o vendedor. O Peek continua consultando PDU."

Você pode escolher alertas para "Cada movimentação", "Etapas importantes" ou "Só a entrega". O Peek apenas acompanha atualizações de rastreamento e não pode liberar pacotes na alfândega, alterar seu endereço de entrega nem contatar a PDU por você.

## Perguntas sobre o rastreamento da PDU

### O que é a PDU (*Parcel Delivery Union*)?

A PDU (*Shanghai Youdilian Technology Co., Ltd.*, *友递联科技*) é uma empresa de logística internacional sediada em Xangai, fundada em 2022, que oferece desembaraço aduaneiro internacional, coleta, entrega de última milha e serviços COD em mais de 10 países.

### Qual é o formato de um código de rastreamento da PDU?

Muitos conhecimentos internacionais da PDU têm 12 caracteres: duas letras, 8 dígitos e `PD` (por exemplo `MX48291736PD`, inventado), embora o portal da PDU também rastreie outros códigos alfanuméricos de 6 a 40 caracteres.

### `Package not found in last mile or customs process.` significa que minha encomenda está presa na alfândega?

Não. No rastreador da PDU, essa leitura inicial significa apenas que os dados eletrônicos da encomenda foram criados e ela ainda não recebeu a primeira leitura física no sistema local de última milha ou da alfândega.

### Como entrar em contato com o atendimento da PDU?

Fale primeiro com a loja virtual para reembolsos ou alterações de entrega. Os contatos publicados pela PDU em Xangai na página [pduni.com/MV/en/customer-services/help-center](https://www.pduni.com/MV/en/customer-services/help-center) são `021-50837888` e `service@pduni.com`.

:::sources
- [PDU: Official Website](https://www.pduni.com/) – fundada em 2022 no distrito de Putuo, Xangai, rede própria de desembaraço e última milha em mais de 10 países e COD
- [PDU: Waybill Tracking Portal](https://www.pduni.com/MV/en/customer-services/waybill-inquiry) – consulta pública de conhecimentos em inglês, `service@pduni.com` e `021-50837888`
- [PDU: Help Center & FAQs](https://www.pduni.com/MV/en/customer-services/help-center) – FAQs aduaneiras, aviso contra golpes de falsa identidade e endereço da sede em Xangai (*Shanghai Youdilian Technology Co., Ltd.*)
- [Universal Parcel Scraper: pdu](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/pdu) – regra de detecção `^[A-Z]{2}\d{8}PD$`, API `traceByWeb`, mapeamento de estados/textos (`DELIVERY` vs. `Salida a Ruta`), limpeza de prefixos e horários com fuso UTC/Pequim
:::
