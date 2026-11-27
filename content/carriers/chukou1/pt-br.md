---
title: Rastreamento Chukou1 (CK1): EC-FirstClass, repasses e ajuda
description: Rastreie encomendas internacionais Chukou1 (CK1 / 出口易) via EC-FirstClass: formato dos códigos, etapas de leitura, repasse local e suporte.
slug: chukou1-tracking
published: 2026-11-26
updated: 2026-11-26
---

A Chukou1 (*CK1*, em chinês *出口易*, operada por *Guangzhou Beifayi Information Technology Co., Ltd.* / *BFE*) é uma grande operadora chinesa de logística para e-commerce transfronteiriço e armazenagem no exterior sediada em Guangzhou, com filiais em Shenzhen, Xangai e Wuhan e centros de processamento em Dongguan e Yiwu. Com armazéns de fulfillment nos EUA, Reino Unido, Alemanha, França, Polônia, Canadá, Austrália, Peru e Argentina além de linhas dedicadas de pequenos pacotes internacionais (linhas Europa/EUA, linhas econômicas globais, América Latina e TEMU-Y2) para vendedores na Amazon, eBay, AliExpress, SHEIN, TikTok Shop, Walmart e Wayfair, a Chukou1 rastreia encomendas pela plataforma **EC-FirstClass** ([ec.chukou1.com](https://ec.chukou1.com/), suportando até 100 códigos por vez). Você pode consultar lá ou colar seu código na caixa acima.

## Códigos de rastreamento Chukou1 (formato de processamento EC-FirstClass)

Procure o número de processamento da Chukou1 na página do pedido no marketplace ou no e-mail de envio. Os códigos próprios da Chukou1 combinam um prefixo de serviço de 3 letras, data do pedido em 6 dígitos (`AAMMDD`), código do lojista de 3 ou 4 caracteres e sequência de 6 ou 7 dígitos (18 a 20 caracteres no total):

| Formato (fictício) | O que é |
| --- | --- |
| `CZN261015ABCD482917` | Código de processamento Chukou1 de 19 caracteres (`CZN` + data `261015` + código de cliente `ABCD` de 4 caracteres + serial de 6 dígitos). |
| `CUE261015KLM4829173` | Código de processamento Chukou1 de 19 caracteres (`CUE` + data `261015` + código de cliente `KLM` de 3 caracteres + serial de 7 dígitos). |

Após o desembaraço de exportação, a Chukou1 repassa o volume a uma transportadora para o restante do trajeto (a EC-FirstClass lista China Post, [USPS](carrier:usps), [DHL](carrier:dhl), [FedEx](carrier:fedex) e [UPS](carrier:ups) entre as parceiras) e exibe tanto o histórico inicial da Chukou1 quanto as leituras retransmitidas dessa parceira sob seu número de processamento. Veja [formatos de código de rastreamento](guide:tracking-number-formats).

## O que significam os status de rastreamento da Chukou1 (EC-FirstClass)

A Chukou1 combina um status geral (`InProcess`, `InTransit`, `Delivered`) com marcos iniciais em inglês e leituras retransmitidas da transportadora de destino usando o horário local de cada ponto de leitura:

| A Chukou1 informa | O que significa para você | O Peek mostra |
| --- | --- | --- |
| `Shipment information received`, `Carrier received the manifest` | Pedido eletrônico recebido do vendedor antes da entrada física no centro da Chukou1. | "Etiqueta criada" |
| `Item received by Logistics Centre`, `Carrier received the shipment` | Pacote físico bipado no armazém de origem da Chukou1 na China. | "Recebida pela transportadora" |
| `Item was sent out from Logistics Centre`, `Transfer to Airport of Origin`, `Departed from Airport of Origin`, `Arrived at Airport of destination`, `Shipment started the customs clearance process at origin` | Enviado do centro ao aeroporto de origem, exportado e pousado no país de destino. | "Em trânsito" |
| `Customs clearance in progress`, `Shipment started the customs clearance process at destination`, `Shipment is awaiting customs clearance at destination` | Passando por inspeção aduaneira de importação no destino. | "Na alfândega" |
| `Customs declaration completed`, `Turned over to last mile facility`, `Last mile Ascan`, `Arrived at Post Office` | Liberado pela alfândega de importação, entregue à transportadora de última milha e bipado na rede local. | "Em trânsito" |
| `Shipment is out for delivery` / `Out for Delivery` | Saiu para entrega final com a transportadora de destino. | "Saiu para entrega" |
| `Shipment is available for pickup` | Pronto para retirada em agência postal ou ponto de retirada local. | "Pronta para retirada" |
| `Wrong Address`, `Refused`, `Cannot be delivered` | Entrega não realizada por erro de endereço, recusa ou impossibilidade de entrega. | "Tentativa de entrega" |
| `Return To Sender`, `Delivered To Sender` | Em retorno ou devolvido ao remetente. | "Devolvida ao remetente" |
| `Lost` | Exceção marcada como extraviada em trânsito; fale imediatamente com o vendedor. | "Precisa de atenção" |
| `Shipment delivered to customer` / `Delivered` | Entregue pela parceira de destino (com nome de quem assinou quando registrado). | "Entregue" |

## Entrega internacional, repasses locais e alfândega

Em [chukou1.com](https://www.chukou1.com/) e [ec.chukou1.com](https://ec.chukou1.com/), a Chukou1 opera como consolidadora B2B internacional e operadora de armazéns no exterior, não como frota própria de entregas ao consumidor.

- **A parceira local controla a entrega final:** assim que surgem `Turned over to last mile facility` e `Last mile Ascan`, tentativas de entrega, redirecionamento, local seguro e prazo em ponto de retirada seguem as regras do correio ou transportadora local do seu país.
- **Alfândega e taxas de importação:** remessas internacionais para UE, EUA, Reino Unido, Canadá ou Austrália passam por desembaraço no destino (`Customs clearance in progress`), IVA de importação e tributos aplicáveis (incluindo €3 de imposto aduaneiro da UE até €150 e taxa de manuseio de €2 sobre pedidos fora da UE), salvo pagamento pelo lojista no checkout. Veja [encomendas retidas na alfândega](guide:customs).

## Encomenda Chukou1 parada, sem atualização ou extraviada

Como os lojistas detêm a conta de envio junto à Chukou1, contate sempre primeiro o vendedor ou marketplace para reembolsos, reenvios ou correções de endereço.

- **Intervalos sem leitura entre China e destino:** trechos aéreos internacionais e lotes aduaneiros costumam pausar atualizações por vários dias entre `Departed from Airport of Origin`, `Arrived at Airport of destination` e `Last mile Ascan`. Veja [por que o rastreamento para de atualizar](guide:tracking-not-updating).
- **Acompanhe pelo número local após o repasse:** quando a EC-FirstClass vincular o código de barras da parceira local, consultar essa transportadora diretamente costuma revelar janelas de entrega ou opções de reentrega.
- **Entregue, danificada ou perdida:** verifique primeiro com moradores, vizinhos e transportadora local e abra disputa junto ao vendedor antes de vencer a proteção ao comprador. Veja [entregue mas não recebido](guide:delivered-not-received), [encomenda danificada](guide:damaged-parcel), [encomenda perdida](guide:lost-parcel) e [devolvida ao remetente](guide:returned-to-sender).

## Atendimento ao cliente e contatos da Chukou1

Contatos oficiais divulgados em [Chukou1 Contact Us](https://www.chukou1.com/About/Contact.aspx) e [chukou1.com](https://www.chukou1.com/):

- **Central nacional de atendimento na China:** `4006 908 223`.
- **Linha de reclamações (seg–sex):** `13360562736`.
- **Escritórios:** Sede Guangzhou `020-23380666` (Units 2403–04, Tower A1, Hangxin Centre, No. 840 Huangpu Avenue East, Huangpu District, Guangzhou), Shenzhen `0755-28263408`, Xangai `021-59793099` e Wuhan `027-59083347`.

> Cuidado com mensagens SMS falsas cobrando pequeno pagamento online para "liberar" um pacote internacional na alfândega. Confirme qualquer cobrança legítima primeiro na página do pedido no marketplace ou no site do correio local. Veja [golpes de SMS de entrega](guide:delivery-scam-texts).

## Rastreie uma encomenda Chukou1 com o Peek

O [Peek](/) consulta a Chukou1 automaticamente como candidata única ao informar um número de processamento Chukou1 (`CZN261015ABCD482917` ou `CUE261015KLM4829173`, fictícios) e mostra "Chukou1 está com esta encomenda." quando a EC-FirstClass confirma; também reconhece links colados `ec.chukou1.com/track?track_number=` e `chukou1.com/LogistictsTrack.aspx?trackNo=` para códigos Chukou1. Se precisar escolher manualmente, toque em "Trocar" ao adicionar ou "Trocar transportadora" na tela do pacote (buscar "出口易" ou "CK1" também encontra).

O Peek lê diretamente o rastreador EC-FirstClass da Chukou1 e exibe a etapa ("Etiqueta criada", "Recebida pela transportadora", "Em trânsito", "Na alfândega", "Saiu para entrega", "Pronta para retirada", "Tentativa de entrega", "Precisa de atenção", "Devolvida ao remetente", "Entregue"), as leituras da Chukou1 e as retransmitidas pela transportadora de destino sem telefones nem nome do entregador, o local e o país de destino. O código postal de destino e o nome de quem assinou permanecem privados e não aparecem nos apps do Peek. Como a EC-FirstClass informa horários sem fuso, o Peek aplica o fuso horário do local de cada leitura e converte para o relógio do seu dispositivo. Quando a EC-FirstClass indica a transportadora local e seu número e o Peek a reconhece, ele confirma se ela já tem o pacote: a página então mostra "Entrega por" seguido do nome e lista ambos os códigos. O Peek omite o PDF de comprovante de entrega; a EC-FirstClass não fornece data estimada de entrega, peso, nome do serviço nem horário separado de entrega.

Enquanto você mantém a encomenda aberta no Peek, ou no dia da entrega com notificações ativadas, o Peek consulta até a cada 10 minutos; no dia em que a Chukou1 registra saída para entrega, a cada 2 minutos a partir das 6h no relógio do local da última leitura enquanto a EC-FirstClass responder. Nos demais casos, consulta de hora em hora. Após 12 horas apenas em "Etiqueta criada" ou "Recebida pela transportadora", ou dois dias sem novidades, consulta de hora em hora em qualquer modo, e a cada 6 horas após sete dias em "Etiqueta criada". Quando a EC-FirstClass não responde e o Peek acompanha apenas por serviços de rastreamento, consulta de hora em hora, a cada 3 horas após um dia sem movimento e até a cada 15 minutos no dia de saída para entrega.

Quando a leitura mais recente tem quatro dias e o pacote segue em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito", o Peek sinaliza a data dessa leitura, como "Sem atualizações desde seg 12 out". A página do pacote então exibe "É comum uma encomenda ficar sem atualizações em voos longos, na alfândega ou no fim de semana. O Peek continua consultando Chukou1.", com o link "Pedir ajuda a Chukou1" para a página de contato da Chukou1. Após três semanas sem novas leituras, mostra "Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, fale com o vendedor. O Peek continua consultando Chukou1."

Você pode escolher alertas para "Cada movimentação", "Etapas importantes" ou "Só a entrega". O Peek monitora apenas atualizações de rastreamento e não pode liberar alfândega, abrir PDFs de comprovante embutidos nem contatar a Chukou1 ou a parceira local por você.

## Perguntas sobre o rastreamento da Chukou1

### O que é a Chukou1 (CK1 / EC-FirstClass)?

A Chukou1 (*出口易*, operada por *Guangzhou Beifayi Information Technology Co., Ltd.*) é uma operadora chinesa de logística internacional de e-commerce e armazéns no exterior cujo portal de rastreamento se chama **EC-FirstClass** ([ec.chukou1.com](https://ec.chukou1.com/)).

### Como é um código de rastreamento da Chukou1?

Os números de processamento da Chukou1 trazem 3 letras de prefixo, data `AAMMDD` de 6 dígitos, código do lojista de 3 ou 4 caracteres e sequência de 6 ou 7 dígitos (como `CZN261015ABCD482917`, fictício).

### O que significa `Last mile Ascan` no rastreamento da Chukou1?

`Last mile Ascan` significa que o correio ou transportadora local do seu país realizou a primeira leitura de entrada após receber o pacote da linha internacional da Chukou1.

### Como entro em contato com o suporte da Chukou1?

Fale primeiro com o vendedor online para reembolsos ou alteração de endereço. A central da Chukou1 na China é `4006 908 223` (reclamações seg–sex `13360562736`, sede Guangzhou `020-23380666`) em [chukou1.com/About/Contact.aspx](https://www.chukou1.com/About/Contact.aspx).

:::sources
- [Chukou1: Site oficial](https://www.chukou1.com/) – linhas internacionais de pequenos pacotes, armazéns no exterior (EUA, Reino Unido, DE, FR, PL, CA, AU, PE, AR), primeira milha FBA e sede em Guangzhou (*广州市贝法易信息科技有限公司*)
- [Chukou1: Contact Us](https://www.chukou1.com/About/Contact.aspx) – `4006 908 223`, reclamações `13360562736`, telefones e endereços em Guangzhou/Shenzhen/Xangai/Wuhan (URL de ajuda vinculada pelo Peek)
- [EC-FirstClass (Plataforma de Rastreamento Chukou1)](https://ec.chukou1.com/) – rastreador global para até 100 códigos e parceiras listadas (China Post, USPS, DHL, FedEx, UPS)
- [Universal Parcel Scraper: chukou1](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/chukou1) – formato de número de processamento, regras de link `ec.chukou1.com`/`chukou1.com`, análise de leituras próprias e de parceiras, extração de assinatura/código postal/repasse, remoção do PDF POD e URL de ajuda
:::
