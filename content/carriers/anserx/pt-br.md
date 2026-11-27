---
title: Rastreamento AnserX: códigos BTYCN...YQ, repasses e ajuda
description: Rastreie encomendas internacionais AnserX (斑头雁) da China: códigos BTYCN...YQ, eventos BTY, repasse a parceiros locais, alfândega e suporte.
slug: anserx-tracking
published: 2026-11-26
updated: 2026-11-26
---

A AnserX (*Shenzhen AnserX International Logistics Technology Co., Ltd.*, em chinês *斑头雁国际物流*), fundada em 2018 e sediada em Shenzhen, é uma provedora chinesa de logística para e-commerce transfronteiriço que opera linhas dedicadas de pequenos pacotes da China para Estados Unidos, Europa (incluindo Reino Unido, França e Alemanha), Japão, Canadá, Austrália, Sudeste Asiático, México, Nigéria e Arábia Saudita, além de carga aérea/marítima FBA (0–30 kg, lado máximo 1,2 m) e armazéns no exterior. Você pode acompanhar um envio no [portal de rastreamento OMS da AnserX](https://oms.anserx.com/tracking) ou colar seu código na caixa acima.

## Códigos de rastreamento AnserX: formato BTYCN...YQ

Consulte o número nos detalhes do pedido no marketplace ou no e-mail de confirmação de envio. Os códigos da AnserX têm 17 caracteres: `BTYCN` seguido por 10 dígitos e finalizado em `YQ`:

| Formato (fictício) | O que é |
| --- | --- |
| `BTYCN4829173640YQ` | Conhecimento padrão internacional AnserX: `BTYCN` + 10 dígitos + `YQ`. |

Assim que a remessa chega ao país de destino e passa pela alfândega, a AnserX a transfere a um correio ou transportadora local (como [USPS](carrier:usps) nos Estados Unidos ou um parceiro local na Europa ou México), vincula o código de rastreamento local desse parceiro e retransmite as leituras locais sob o seu código `BTYCN...YQ`. Para outros prefixos de logística internacional da China, veja [formatos de código de rastreamento](guide:tracking-number-formats).

## O que significam os status e códigos da AnserX

A AnserX combina códigos de marco `BTY_` com descrições em inglês e chinês cobrindo triagem de exportação, voo internacional, desembaraço aduaneiro e entrega no destino:

| Código e texto da AnserX | O que significa para você | O Peek mostra |
| --- | --- | --- |
| `BTY_ORDER_CREATED` (`Shipment information received`) | O vendedor enviou os dados eletrônicos do pedido à AnserX antes de o pacote físico dar entrada no centro. | "Etiqueta criada" |
| `BTY_WAREHOUSE_INBOUND` (`Shipment inbound in sorting center`) | Recebida e bipada no centro de triagem da AnserX na China. | "Recebida pela transportadora" |
| `BTY_WAREHOUSE_OUTBOUND`, `BTY_LINE_HANDOVER` (`Hand over to airline` / `Departed from origin port`), `BTY_LINE_ARRIVED` (`Arrived at destination port`) | Saiu do centro de triagem na China, foi entregue à companhia aérea e pousou no aeroporto de destino. | "Em trânsito" |
| `BTY_EXCC_COMPLETED` (`Relased from export customs` / `目的地进口清关完成`) | Desembaraço aduaneiro concluído (observação: o texto em inglês da AnserX diz "export", mas o texto chinês confirma conclusão do desembaraço de importação no destino); o pacote segue para o trânsito local. | "Em trânsito" |
| `BTY_LASTMILE_TRANSIT` (`Delivery to local courier company` / `arrival at transportation partner facilities`) | Entregue à transportadora de última milha no país de destino e em deslocamento para a unidade local de entrega. | "Em trânsito" |
| `BTY_LASTMILE_DELIVERING` (`Parcel currently being delivered` / `Out for Delivery`) | Saiu com o entregador local hoje. | "Saiu para entrega" |
| `BTY_LASTMILE_DELIVERED` (`Parcel has been delivered` / `Delivered`) | Entregue ao destinatário pelo parceiro de destino. | "Entregue" |

## Como funciona a entrega internacional da AnserX

Em [anserx.com](https://www.anserx.com/) e [Sobre a AnserX](https://www.anserx.com/about.html), a AnserX descreve sua plataforma inteligente de roteamento e capacidade ligando coleta doméstica na China, centros de exportação, transporte aéreo, desembaraço aduaneiro e transportadoras de última milha no destino (incluindo linhas dedicadas para eletrônicos com bateria).

**Entrega na última milha e alfândega.** A AnserX atua como consolidadora de linha internacional, e não como frota própria de entrega porta a porta fora da China. Depois que o pacote conclui a alfândega no destino (`BTY_EXCC_COMPLETED`), o correio ou transportadora local do seu país define tentativas de entrega, locais seguros, guarda em ponto de retirada e devoluções. Eventuais IVA de importação, impostos aduaneiros ou taxas seguem as regras do destino, salvo pagamento prévio no checkout pelo vendedor; veja [encomendas retidas na alfândega](guide:customs).

## Encomenda AnserX parada, sem atualização ou não entregue

Os clientes da AnserX são os vendedores que despacham por ela, então contate primeiro o vendedor para atrasos, endereços incorretos, reembolsos ou pedidos de indenização por extravio.

- **Intervalos sem leitura no voo ou alfândega:** o rastreamento costuma ficar vários dias sem atualização entre `BTY_LINE_HANDOVER` (saída do porto de origem), `BTY_LINE_ARRIVED` e `BTY_EXCC_COMPLETED` enquanto voos pousam e a alfândega processa lotes consolidados. Veja [por que o rastreamento para de atualizar](guide:tracking-not-updating).
- **Consulte o código local após o repasse:** assim que a AnserX informar o código da transportadora de destino, acompanhá-lo diretamente no site dela costuma trazer detalhes adicionais de entrega ou retirada.
- **Entregue, danificada ou extraviada:** verifique primeiro com moradores, vizinhos, portaria e transportadora local e acione o vendedor online rapidamente. Veja [entregue mas não recebido](guide:delivered-not-received), [encomenda danificada](guide:damaged-parcel), [encomenda perdida](guide:lost-parcel) e [devolvida ao remetente](guide:returned-to-sender).

## Atendimento ao cliente e contatos da AnserX

Contatos oficiais publicados em [AnserX Contact Us](https://www.anserx.com/contact-us.html) e [anserx.com](https://www.anserx.com/):

- **Centrais 24 horas (China):** `0755-2891 8474` e `0755-2868-5256` (atendimento online das `09:00–24:00`, horário da China).
- **E-mail de atendimento:** `bantouyankf@anserx.com`.
- **Sede:** Shenzhen AnserX International Logistics Technology Co., Ltd., Room 804, Block C, Building 3, Tian'an Cloud Park, Xuegang North Road, Bantian Street, Longgang District, Shenzhen, China.

> Cuidado com mensagens não solicitadas por SMS ou WhatsApp pedindo pagamento online com cartão para "liberar alfândega" ou "corrigir endereço" de uma encomenda da China. Verifique qualquer taxa legítima primeiro na página do pedido no marketplace ou no site do correio local. Veja [golpes de SMS de entrega](guide:delivery-scam-texts).

## Rastreie uma encomenda AnserX com o Peek

O [Peek](/) reconhece automaticamente com alta confiança `BTYCN` + 10 dígitos + `YQ` (`BTYCN4829173640YQ`, dígitos fictícios) como AnserX, além de links colados de `oms.anserx.com/tracking?nos=`. Se precisar escolher a AnserX manualmente, toque em "Trocar" ao adicionar ou em "Trocar transportadora" na tela da encomenda (buscar "斑头雁" ou "AnserX International Logistics" também encontra).

O Peek consulta diretamente o rastreador OMS da AnserX e exibe a etapa ("Etiqueta criada", "Recebida pela transportadora", "Em trânsito", "Saiu para entrega", "Entregue"), o texto da leitura em inglês (ou chinês quando não houver inglês), a cidade ou hub sem o código postal e o país de destino. Os códigos `BTY_` definem a etapa e não aparecem nos apps do Peek. A AnserX registra suas próprias leituras no horário de Pequim; as leituras retransmitidas do parceiro local recebem o fuso horário do local (Los Angeles em Los Angeles, Cidade do México em Puebla), e o Peek exibe todas no relógio do seu dispositivo. Quando a AnserX informa o código da transportadora local e o Peek a reconhece, ele confirma se ela já tem o pacote: a página então mostra "Entrega por" seguido do nome da transportadora e lista ambos os números. Códigos de barras USPS perdem o prefixo de roteamento de ZIP. O Peek omite código da conta do cliente e referência do pedido; o rastreador da AnserX não informa data estimada de entrega, peso, nome do serviço, horário separado de entrega nem foto de comprovante.

Enquanto você mantém a encomenda aberta no Peek, ou no dia da entrega com notificações ativadas, o Peek consulta até a cada 10 minutos; no dia em que a AnserX mostra `BTY_LASTMILE_DELIVERING`, a cada 2 minutos a partir das 6h no relógio do local da leitura mais recente enquanto o rastreador próprio responder. Nos demais casos, consulta de hora em hora. Após 12 horas apenas em "Etiqueta criada" ou "Recebida pela transportadora", ou dois dias sem novidades, consulta de hora em hora em qualquer modo, e a cada 6 horas após sete dias em "Etiqueta criada". Quando o rastreador da AnserX não responde e o Peek acompanha apenas por serviços de rastreamento, consulta de hora em hora, a cada 3 horas após um dia sem movimento e até a cada 15 minutos no dia de saída para entrega.

Quando a leitura mais recente tem quatro dias e o pacote segue em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito", o Peek sinaliza a data dessa leitura, como "Sem atualizações desde seg 12 out". A página do pacote então exibe "É comum uma encomenda ficar sem atualizações em voos longos, na alfândega ou no fim de semana. O Peek continua consultando AnserX.", com o link "Pedir ajuda a AnserX" para a página de contato da AnserX. Após três semanas sem novas leituras, mostra "Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, fale com o vendedor. O Peek continua consultando AnserX."

Você pode escolher alertas para "Cada movimentação", "Etapas importantes" ou "Só a entrega". O Peek monitora apenas atualizações de rastreamento e não pode desembaraçar alfândega nem contatar a AnserX ou seu entregador local em seu nome.

## Perguntas sobre o rastreamento da AnserX

### O que é a AnserX (*斑头雁*)?

A AnserX (*Shenzhen AnserX International Logistics Technology Co., Ltd.*, fundada em 2018 em Shenzhen) é uma provedora chinesa de logística para e-commerce internacional com linhas aéreas dedicadas para pequenos pacotes, envios FBA e armazéns no exterior para EUA, Europa, Canadá, Austrália, Japão, México e outros destinos.

### O que significa um código de rastreamento `BTYCN...YQ`?

`BTYCN` + 10 dígitos + `YQ` (por exemplo `BTYCN4829173640YQ`, dígitos fictícios) é o conhecimento internacional de 17 caracteres da AnserX.

### Por que a AnserX exibe `Relased from export customs` depois de chegar ao meu país?

Trata-se de uma imprecisão na tradução para o inglês da AnserX no código `BTY_EXCC_COMPLETED` — o texto correspondente em chinês (`目的地进口清关完成`) confirma que o desembaraço aduaneiro de importação no destino foi concluído e o pacote está seguindo para a transportadora local.

### Como entro em contato com o suporte da AnserX?

Procure primeiro a loja online ou marketplace para problemas no pedido e reembolsos. Para consultas à transportadora, a AnserX divulga `bantouyankf@anserx.com` e as centrais 24 horas em Shenzhen `0755-2891 8474` e `0755-2868-5256` em [anserx.com/contact-us.html](https://www.anserx.com/contact-us.html).

:::sources
- [AnserX: Site oficial](https://www.anserx.com/) – linhas dedicadas de pequenos pacotes, limites FBA 0–30 kg / 1,2 m, armazéns no exterior e sede em Shenzhen
- [AnserX: Sobre nós](https://www.anserx.com/about.html) – fundada em 2018, rotas globais (EUA, Europa, Japão, Sudeste Asiático, Rússia, Austrália) e plataforma de roteamento inteligente
- [AnserX: Fale conosco](https://www.anserx.com/contact-us.html) – `bantouyankf@anserx.com`, `0755-2891 8474`, `0755-2868-5256`, atendimento online `09:00–24:00` e endereço em Shenzhen (URL de ajuda vinculada pelo Peek)
- [AnserX: Portal de Rastreamento OMS](https://oms.anserx.com/tracking) – consulta pública de rastreamento
- [Universal Parcel Scraper: anserx](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/anserx) – detecção `BTYCN...YQ`, regra de link `oms.anserx.com/tracking?nos=`, códigos `BTY_*`, horário de Pequim nas leituras próprias e fuso local nas retransmitidas, extração de `deliveryNo` local e URL de ajuda
:::
