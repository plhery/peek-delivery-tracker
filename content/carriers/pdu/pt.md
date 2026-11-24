---
title: Rastreio PDU: códigos Parcel Delivery Union e apoio
description: Acompanha uma encomenda internacional PDU (Parcel Delivery Union / 友递联): formatos de guia, estados de trânsito e entrega, alfândega e contactos.
slug: rastreio-pdu
published: 2026-11-24
updated: 2026-11-24
---

A **PDU** (*Parcel Delivery Union*, operada pela *Shanghai Youdilian Technology Co., Ltd.*, em chinês *友递联科技*) é um operador de logística inteligente transfronteiriça sediado em Xangai, fundado em 2022 ([pduni.com](https://www.pduni.com/)). Com delegações próprias e hubs operacionais locais em mais de 10 países na América do Norte, Europa, África e Ásia, a PDU fornece a comerciantes eletrónicos e fabricantes desalfandegamento ponta a ponta, recolha, entrega de última milha no estrangeiro e cobrança contra reembolso (COD). Podes verificar um envio no portal em inglês da PDU em [pduni.com/MV/en/customer-services/waybill-inquiry](https://www.pduni.com/MV/en/customer-services/waybill-inquiry) ou colar o teu número de rastreio na caixa acima.

## Números de rastreio PDU (sufixo `PD` e guias personalizadas)

Muitas encomendas internacionais PDU levam um código de 12 carateres composto por duas letras, 8 dígitos e `PD`:

| Formato (inventado) | O que é |
| --- | --- |
| `MX48291736PD` | Guia transfronteiriça PDU de 12 carateres (`MX` + 8 dígitos + `PD`). |
| `CO73918462PD` | Outro formato de guia `..PD` de 12 carateres usado em rotas internacionais. |

A página pública de pesquisa da PDU aceita também outras guias alfanuméricas de 6 a 40 carateres quando selecionas PDU manualmente. O mesmo formato encaixa nos números da [Colis Privé](carrier:colis-prive): o Peek consulta primeiro a Colis Privé e depois a PDU, ou primeiro a PDU na China. Consulta os [formatos de números de rastreio](guide:tracking-number-formats).

## O que significam os estados de rastreio da PDU

A PDU atribui a cada envio e registo um código de estado global (`TRANSIT`, `COLLECTING`, `DELIVERY`, `SIGNED`, etc.) acompanhado de texto em inglês ou na língua de destino (frequentemente espanhol nas rotas latino-americanas, muitas vezes antecedido por um código curto da transportadora local como `ENT,Entregado`, que o Peek limpa na apresentação):

| Estado ou texto PDU | O que significa para ti | O Peek mostra |
| --- | --- | --- |
| `Shipment information sent to PDU.`, `Package not found in last mile or customs process.` | Pedido eletrónico recebido pela PDU antes da entrada física ou antes de a rede local criar o primeiro registo. | «Etiqueta criada» |
| `COLLECTING` (`Being Collected`), `FINAL_COLLECTING` (`Last-Mile Collection`), `TRANSIT` (`In Transit`) | Recolhida na origem ou em trânsito internacional e centros de distribuição regionais. | «Em trânsito» |
| `Recogida en Aduanas`, `Ingreso a CEDI`, `En tránsito nacional` | Recolhida da alfândega, entrada no centro de distribuição nacional (CEDI) ou em trânsito interno. | «Em trânsito» |
| `DELIVERY` (sem texto de saída em rota) | Entregue à rede da transportadora local de destino; o Peek mantém em trânsito até começar a rota efetiva de entrega. | «Em trânsito» |
| `Salida a Ruta` | Em distribuição hoje com o estafeta local. | «Em distribuição» |
| `DELIVERY_EXCEPTION` (`Delivery Exception`), `TO_BE_RETURN` (`Awaiting Return`) | Exceção de entrega assinalada ou encomenda a aguardar devolução ao remetente; contacta o vendedor ou o estafeta local. | «Problema reportado» |
| `RETURNED` (`Returned`) | Devolvida ao remetente ou ao armazém de origem. | «Devolvida ao remetente» |
| `SIGNED` (`Delivered`), `Entregado` | Entregue e assinada na morada de destino. | «Entregue» |

Nota que a mensagem inicial `Package not found in last mile or customs process.` da PDU significa apenas que o parceiro local da última milha ainda não registou a encomenda — **não** significa que a alfândega tenha retido o envio.

## Desalfandegamento, entrega local no estrangeiro e cobrança na entrega

De acordo com [pduni.com](https://www.pduni.com/) e o [Centro de Ajuda PDU](https://www.pduni.com/MV/en/customer-services/help-center):

- **Desalfandegamento próprio e classificação SH por IA:** a PDU opera a sua própria rede de desalfandegamento em mais de 10 países com atribuição automática de códigos SH e pré-pagamento de direitos; segundo as FAQ, a PDU não cobra taxa separada por declarações formais de exportação e os comerciantes podem enviar online documentos aduaneiros complementares caso falte algo.
- **Entrega local da última milha:** depois de a alfândega de destino libertar o envio (`Recogida en Aduanas` / `Ingreso a CEDI`), filiais locais ou estafetas parceiros efetuam a entrega ao domicílio e, quando contratado, a cobrança contra reembolso (COD).
- **Impostos e direitos de importação:** o IVA de importação ou taxas aduaneiras dependem dos limites fiscais locais e de o vendedor ter cobrado os encargos no checkout. Consulta [encomendas retidas na alfândega](guide:customs).

## Encomenda PDU parada, sem novidades ou desaparecida

Como são as lojas online e fábricas que mantêm a conta de envio na PDU, **contacta sempre primeiro o teu vendedor ou marketplace** para correções de morada, reembolsos ou reenvios.

- **Pausas entre a China e a receção local:** os registos param frequentemente vários dias entre `Shipment information sent to PDU.` (ou `Package not found in last mile or customs process.`) e `Recogida en Aduanas` / `Ingreso a CEDI` enquanto a consolidação aérea ou marítima chega ao país de destino. Consulta [por que o rastreio deixa de atualizar](guide:tracking-not-updating).
- **`DELIVERY_EXCEPTION` ou `TO_BE_RETURN`:** age depressa — confirma a tua morada e telefone com o vendedor para que o hub local agende nova tentativa antes de a encomenda passar a `RETURNED`.
- **Dada como entregue ou danificada:** verifica primeiro junto de familiares, receção do edifício e vizinhos, depois abre reclamação na loja antes de terminar a proteção ao comprador. Consulta [entregue mas não recebida](guide:delivered-not-received), [encomenda perdida](guide:lost-parcel), [encomenda danificada](guide:damaged-parcel) e [devolvida ao remetente](guide:returned-to-sender).

## Apoio ao cliente PDU e contactos em Xangai

Contactos oficiais publicados em [PDU Waybill Inquiry](https://www.pduni.com/MV/en/customer-services/waybill-inquiry) e [PDU Help Center](https://www.pduni.com/MV/en/customer-services/help-center):

- **Entidade legal:** *Shanghai Youdilian Technology Co., Ltd.* (*友递联科技*, ICP `沪ICP备2022026205号`).
- **Linha telefónica:** `021-50837888` (`+86 21 5083 7888`).
- **E-mail:** `service@pduni.com`.
- **Sede:** Room 305, 6F, Zhonghai Building, Zhenbei Road, Putuo District, Xangai, China.
- **Rastreio e apoio online:** [pduni.com/MV/en/customer-services/waybill-inquiry](https://www.pduni.com/MV/en/customer-services/waybill-inquiry) e [pduni.com/MV/en/customer-services/help-center](https://www.pduni.com/MV/en/customer-services/help-center).

> O Centro de Ajuda da PDU alerta expressamente para fraudes de usurpação que utilizam o logótipo ou os contactos da PDU. Nunca pagues taxas aduaneiras ou de entrega através de uma ligação SMS não verificada — confirma primeiro qualquer cobrança junto da loja ou no portal oficial da transportadora. Consulta [SMS falsas de entrega](guide:delivery-scam-texts).

## Seguir uma encomenda PDU com o Peek

Quando introduzes um código `..PD` de 12 carateres (`MX48291736PD` ou `CO73918462PD`, inventados), o [Peek](/) pergunta à Colis Privé e à PDU por ordem (primeiro à PDU na China) e mostra «PDU tem esta encomenda.» assim que o rastreio da PDU a reconhece. Se o teu número PDU usar outro formato de 6 a 40 carateres, toca em «Alterar» ao adicionar ou «Alterar transportadora» no ecrã da encomenda e seleciona **PDU** (pesquisar «Parcel Delivery Union» ou «友递联» também a encontra).

O Peek lê o rastreio da PDU e mostra a etapa («Etiqueta criada», «Em trânsito», «Em distribuição», «Problema reportado», «Devolvida ao remetente», «Entregue»), o texto do registo sem o código da transportadora local à frente (`Entregado`, não `ENT,Entregado`), o local e a hora de entrega. Os códigos de estado da PDU definem a etapa e não aparecem em nenhuma app Peek. A PDU indica cada hora de registo com o seu desvio UTC, ou então na hora de Pequim, e o Peek apresenta-os na hora do teu dispositivo. O rastreio da PDU não fornece data estimada de entrega.

Enquanto tens a encomenda aberta no Peek, ou no dia da entrega com as notificações ativadas, o Peek consulta até de 10 em 10 minutos; no dia em que a PDU mostra `Salida a Ruta`, de 2 em 2 minutos a partir das 6h00 no relógio do registo mais recente enquanto o rastreio da PDU responder. Caso contrário, o Peek verifica de hora a hora. Após 12 horas apenas em «Etiqueta criada» ou «Aceite pela transportadora», ou dois dias sem novidades, verifica de hora a hora independentemente da forma como acompanhas a encomenda, e de 6 em 6 horas após sete dias em «Etiqueta criada». Quando o rastreio da PDU não responde e o Peek acompanha a encomenda apenas através de serviços de rastreio, verifica de hora a hora, de 3 em 3 horas após um dia sem novidades e até de 15 em 15 minutos no dia da distribuição enquanto tens a encomenda aberta ou as notificações ativadas.

Quando o registo mais recente tem quatro dias e a encomenda continua em «Etiqueta criada», «Aceite pela transportadora» ou «Em trânsito», o Peek assinala-a com o dia desse registo, como «Sem atualizações desde seg., 12/10». A página da encomenda apresenta então «É habitual uma encomenda ficar sem novidades em voos longos, na alfândega ou ao fim de semana. O Peek continua a perguntar a PDU.» Passadas três semanas sem novos registos, mostra «Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, contacta o vendedor. O Peek continua a perguntar a PDU.»

Podes escolher alertas para «Todos os registos», «Passos importantes» ou «Só a entrega». O Peek acompanha apenas atualizações de rastreio e não pode desalfandegar encomendas, editar a tua morada de entrega nem contactar a PDU por ti.

## Perguntas sobre o rastreio PDU

### O que é a PDU (*Parcel Delivery Union*)?

A PDU (*Shanghai Youdilian Technology Co., Ltd.*, *友递联科技*) é uma empresa de logística transfronteiriça sediada em Xangai, fundada em 2022, que presta serviços internacionais de desalfandegamento, recolha, entrega de última milha e cobrança na entrega em mais de 10 países.

### Como é um número de rastreio PDU?

Muitas guias internacionais da PDU têm 12 carateres: duas letras, 8 dígitos e `PD` (por exemplo `MX48291736PD`, inventado), embora o portal da PDU também acompanhe outras guias alfanuméricas de 6 a 40 carateres.

### `Package not found in last mile or customs process.` significa que a minha encomenda está retida na alfândega?

Não. No rastreio da PDU, esse registo inicial significa apenas que os dados eletrónicos da encomenda foram criados e ainda não houve o primeiro registo físico no sistema local de entrega ou alfândega.

### Como contactar o apoio ao cliente da PDU?

Contacta primeiro o teu vendedor online para reembolsos ou alterações de entrega. Os contactos publicados pela PDU em Xangai em [pduni.com/MV/en/customer-services/help-center](https://www.pduni.com/MV/en/customer-services/help-center) são `021-50837888` e `service@pduni.com`.

:::sources
- [PDU: site oficial](https://www.pduni.com/) – fundada em 2022 no distrito de Putuo, Xangai, rede própria de desalfandegamento e última milha em 10+ países, COD
- [PDU: portal de rastreio de guias](https://www.pduni.com/MV/en/customer-services/waybill-inquiry) – pesquisa pública em inglês, `service@pduni.com` e `021-50837888`
- [PDU: centro de ajuda e FAQ](https://www.pduni.com/MV/en/customer-services/help-center) – FAQ alfândega, aviso contra usurpação e morada da sede em Xangai (*Shanghai Youdilian Technology Co., Ltd.*)
- [Universal Parcel Scraper: pdu](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/pdu) – regra de deteção `^[A-Z]{2}\d{8}PD$`, API `traceByWeb`, mapa de estado/texto (`DELIVERY` vs. `Salida a Ruta`), limpeza de prefixos e tratamento de horas UTC/Pequim
:::
