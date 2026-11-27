---
title: Rastreio Yunda Express: códigos, status chineses e 95546
description: Rastreie encomendas domésticas Yunda Express (韵达快递) na China: códigos de 13 e 15 dígitos, etapas chinesas, devoluções e central 95546.
slug: yunda-tracking
published: 2026-11-27
updated: 2026-11-27
---

A **Yunda Express** (*Shanghai Yunda Freight Co., Ltd.*, em chinês *韵达快递* / *韵达速递* / *上海韵达货运有限公司*) é uma das maiores redes nacionais de encomendas expressas da China, com sede no distrito de Qingpu, em Xangai ([yundaex.com](https://www.yundaex.com/cn/index.php)). A Yunda opera entregas expressas — incluindo e-commerce e expresso internacional — além de armazenagem, frete (*韵达快运*), cadeia fria e serviços de cadeia de suprimentos. Você pode consultar um código doméstico em [web.yundaex.com/infoInquiry](https://web.yundaex.com/infoInquiry) ou colar seu código Yunda de 13 ou 15 dígitos na caixa acima.

## Códigos de rastreio Yunda Express (13 e 15 dígitos domésticos)

Os códigos domésticos da Yunda Express na China são sequências puramente numéricas de **13 ou 15 dígitos**:

| Formato (inventado) | O que é |
| --- | --- |
| `3148291736054` | Código doméstico Yunda Express de 13 dígitos. |
| `437391846205819` | Código doméstico Yunda Express de 15 dígitos (comum em pedidos recentes de e-commerce). |

Números puros de 13 e 15 dígitos coincidem com códigos de outras transportadoras, por isso o Peek só consulta a Yunda quando você cola um link `web.yundaex.com/infoInquiry?...&homeWaybill=` ou seleciona Yunda Express manualmente. Veja [formatos de código de rastreio](guide:tracking-number-formats).

## Significado dos status de rastreio Yunda Express

O rastreador da Yunda atribui cinco rótulos padrão em chinês às etapas domésticas, que o Peek traduz com clareza — distinguindo inclusive quando a encomenda retorna ao remetente em uma perna de devolução:

| Rótulo Yunda (CN) | Significado em português | O que o Peek mostra |
| --- | --- | --- |
| `已揽件` | Pacote coletado e escaneado pelo entregador ou unidade Yunda de origem. | "Recebida pela transportadora" |
| `运输中` | Em trânsito entre centros de triagem e transferência da Yunda pelas províncias e cidades chinesas. | "Em trânsito" |
| `派送中` | Saiu para entrega com o entregador local da Yunda (ou em rota de devolução ao remetente se houve leitura de retorno). | "Saiu para entrega" |
| `待取件` | Depositada em ponto de retirada, posto Cainiao ou armário inteligente aguardando coleta (ou aguardando retirada pelo remetente na devolução). | "Pronta para retirada" |
| `已签收` | Recebida ou retirada pelo destinatário (ou pelo armazém consolidador no envio ao exterior). | "Entregue" |
| `已签收` após leitura explícita de devolução (`退回件扫描` / assinado pelo remetente) | Devolvida e assinada pelo remetente original em vez do comprador. | "Devolvida ao remetente" |

## Entregas na China, armazéns consolidadores e devoluções

- **Compras do exterior via armazém chinês:** se você comprou no Taobao, 1688 ou Pinduoduo usando redirecionador ou armazém consolidador (como [DobroPost](carrier:dobropost) ou um agente em Guangzhou, Shenzhen, Dongguan ou Yiwu), `已签收` ("Entregue") na Yunda Express significa que o pacote chegou ao seu **armazém consolidador na China** — não ao seu país. Após o recebimento, o agente gera um novo código internacional.
- **Pontos de encomenda (`待取件`):** dentro da China, `待取件` indica que o pacote aguarda retirada pelo destinatário em um posto de entrega ou armário.
- **Detecção de devolução ao remetente:** o rastreador original da Yunda marca uma devolução assinada pelo remetente com o mesmo rótulo `已签收` de uma entrega normal; o Peek verifica o texto subjacente em busca de indicação explícita de retorno ou assinatura pelo remetente e marca "Devolvida ao remetente".

## Encomenda Yunda Express parada, extraviada ou entregue errado

- **Pedido em marketplace chinês:** fale primeiro com o vendedor no Taobao, Tmall, Pinduoduo ou JD — ele pode pedir à unidade Yunda (*网点*) que coletou o pacote para rastreá-lo.
- **Sem leitura após gerar etiqueta:** lojas frequentemente geram um número Yunda de 15 dígitos antes da entrega física; as leituras começam quando a unidade registra `已揽件` (coletado). Veja [por que o rastreio para de atualizar](guide:tracking-not-updating).
- **Entregue (`已签收`) mas não encontrada:** confira se um armário, portaria ou armazém consolidador assinou pelo volume, e abra reclamação com o vendedor ou a transportadora quanto antes em caso de sumiço ou dano. Veja [entregue mas não recebida](guide:delivered-not-received), [encomenda perdida](guide:lost-parcel), [encomenda danificada](guide:damaged-parcel) e [devolvida ao remetente](guide:returned-to-sender).

## Atendimento ao cliente Yunda Express e contatos em Xangai

Contatos oficiais publicados em [Yunda Contact Us](https://www.yundaex.com/cn/about_contact.php) e [yundaex.com](https://www.yundaex.com/cn/index.php):

- **Central nacional de atendimento (China):** **`95546`**.
- **Reclamações on-line e rastreio:** [yundaex.com/cn/about_contact.php](https://www.yundaex.com/cn/about_contact.php) e [web.yundaex.com/infoInquiry](https://web.yundaex.com/infoInquiry).
- **Fax:** `021-39297127` (imprensa `021-39291355`).
- **Sede:** *Shanghai Yunda Freight Co., Ltd.* (*上海韵达货运有限公司*), No. 6679 Yinggang East Road, Qingpu District, Shanghai `201700`, China (*上海市青浦区盈港东路6679号*).

> Desconfie de SMS ou links falsos no WeChat pedindo taxa de reentrega ou dados bancários para um pacote Yunda. Confirme qualquer pendência pelo app do marketplace ou pela central oficial `95546`. Veja [golpes por SMS de entrega](guide:delivery-scam-texts).

## Acompanhe sua encomenda Yunda Express no Peek

O Peek não consulta a Yunda apenas por um número isolado de 13 ou 15 dígitos (`3148291736054` ou `437391846205819`, inventados): para 13 dígitos consulta [iMile](carrier:imile) e [FAN Courier](carrier:fan-courier), entre outras, e para 15 dígitos consulta [BRT](carrier:brt). Toque em "Trocar" ao adicionar ou "Trocar transportadora" na tela do pacote, onde Yunda Express aparece em "Compatível com este código" (pesquisar "Yunda" ou "uda International" também encontra), ou cole um link `web.yundaex.com/infoInquiry?...&homeWaybill=` no [Peek](/), que identifica a Yunda na hora.

O Peek lê o rastreador da Yunda e mostra a etapa ("Recebida pela transportadora", "Em trânsito", "Saiu para entrega", "Pronta para retirada", "Devolvida ao remetente", "Entregue"), o texto da leitura em inglês, a cidade ou distrito citado no início da mensagem e o horário da entrega. Os horários da Yunda seguem o fuso de Pequim e o Peek os converte para o fuso do seu dispositivo. Em devoluções, as mensagens indicam retorno ao remetente. Durante o trajeto, seu mapa privado pode indicar a cidade do destinatário; o endereço completo nunca aparece, e links compartilhados não exibem nenhum dos dois. O Peek omite nomes e telefones de entregadores, endereços de postos, imagens de assinatura e pesos sem rótulo; a Yunda não informa data prevista de entrega.

Enquanto o pacote estiver aberto no Peek, ou no dia da entrega com notificações ligadas, o Peek consulta até a cada 10 minutos; no dia em que a Yunda exibir `派送中`, a cada 2 minutos a partir das 6h de Pequim, enquanto o rastreador da Yunda responder. Nos demais casos, verifica de hora em hora. Após 12 horas apenas em "Etiqueta criada" ou "Recebida pela transportadora", ou dois dias sem novidades, consulta a cada hora, e a cada 6 horas após sete dias em "Etiqueta criada". Quando a Yunda não responde e o Peek acompanha por serviços de rastreamento, consulta de hora em hora, a cada 3 horas após um dia sem novidades e até a cada 15 minutos no dia da saída para entrega.

Quando a leitura mais recente tem quatro dias e o pacote segue em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito", o Peek sinaliza a data dessa leitura, como "Sem atualizações desde seg 12 out". A página do pacote então exibe "É comum uma encomenda ficar sem atualizações em voos longos, na alfândega ou no fim de semana. O Peek continua consultando Yunda Express.", com o link "Pedir ajuda a Yunda Express" para a página de contato da Yunda. Após três semanas sem novas leituras, mostra "Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, fale com o vendedor. O Peek continua consultando Yunda Express."

Você pode escolher alertas de "Cada movimentação", "Etapas importantes" ou "Só a entrega". O Peek apenas monitora o rastreamento e não solicita reentrega, não recupera senhas de armários nem contata a Yunda Express por você.

## Perguntas sobre o rastreio Yunda Express

### Como é um código de rastreio da Yunda Express?

Os códigos domésticos da Yunda Express contêm 13 ou 15 dígitos numéricos (por exemplo `3148291736054` ou `437391846205819`, inventados).

### O que significam `已揽件`, `派送中`, `待取件` e `已签收`?

`已揽件` significa coletado, `派送中` significa saiu para entrega, `待取件` significa pronto para retirada em ponto ou armário e `已签收` significa entregue (salvo se precedido por leitura de devolução ao remetente).

### Por que minha encomenda Yunda aparece como "Entregue" se moro fora da China?

Se você comprou usando um redirecionador ou armazém consolidador chinês, a Yunda Express faz apenas o trecho interno na China até esse armazém. Aguarde o agente registrar a entrada do volume e emitir o código internacional.

### Como entrar em contato com o atendimento da Yunda Express?

Ligue para a central nacional chinesa **`95546`** ou acesse a página oficial de contato e reclamações em [yundaex.com/cn/about_contact.php](https://www.yundaex.com/cn/about_contact.php).

:::sources
- [Yunda Express: Official Website](https://www.yundaex.com/cn/index.php) – *Shanghai Yunda Freight Co., Ltd.* (*上海韵达货运有限公司*), serviços expressos domésticos, carga, cadeia fria e armazenagem
- [Yunda Express: Contact Us](https://www.yundaex.com/cn/about_contact.php) – central `95546`, fax `021-39297127`, sistema de reclamações on-line e sede em Qingpu, Xangai (URL de ajuda vinculada pelo Peek)
- [Yunda Express: Waybill Inquiry](https://web.yundaex.com/infoInquiry) – consulta oficial de conhecimentos domésticos
- [Universal Parcel Scraper: yunda](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/yunda) – regra de 13/15 dígitos, link `web.yundaex.com`, mapa dos 5 status chineses, detecção de devolução, extração de cidade e horários de Pequim
:::
