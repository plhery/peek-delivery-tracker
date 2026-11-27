---
title: Rastreamento CMT Express: códigos CMT, Shenzhen e suporte
description: Rastreie encomendas internacionais CMT Express (诚贸通) da China: códigos CMT de 15 caracteres, leituras em Shenzhen, repasse local e suporte.
slug: cmt-express-tracking
published: 2026-11-26
updated: 2026-11-26
---

A CMT Express (*Shenzhen Chengmaotong Supply Chain Co., Ltd.*, em chinês *深圳诚贸通供应链有限公司*) é uma operadora de logística internacional e cadeia de suprimentos sediada em Shenzhen que atua com frete aéreo, marítimo e terrestre, remessa expressa internacional, linhas de pequenos pacotes de Guangzhou, despacho aduaneiro e fulfillment para destinos no mundo todo, oferecendo produtos expressos via DHL, UPS, FedEx, TNT e USPS. Você pode consultar um envio no [portal de rastreamento em inglês da CMT Express](https://tms.cmtexpress.com.cn/en/trackIndex.htm), em [cmtexpress.com.cn/en/](https://www.cmtexpress.com.cn/en/) ou colar seu código na caixa acima.

## Códigos de rastreamento CMT Express: formato CMT + 12 dígitos

Verifique a tela do pedido no marketplace ou o e-mail de envio para achar o código. Os números próprios da CMT Express têm 15 caracteres — `CMT` seguido por 12 dígitos:

| Formato (fictício) | O que é |
| --- | --- |
| `CMT482917364058` | Conhecimento padrão de 15 caracteres da CMT Express (`CMT` + 12 dígitos). |

Depois que o pacote sai do armazém da CMT Express em Shenzhen e passa pela alfândega no destino, o portal da CMT também pode associar um código separado da transportadora local de última milha (por exemplo, um código postal ou de courier do país de destino) enquanto retransmite as leituras sob o seu código `CMT`. Para outros prefixos de logística chinesa, veja [formatos de código de rastreamento](guide:tracking-number-formats).

## O que significam os status de rastreamento da CMT Express

A CMT Express opera sua consulta de rastreamento em uma plataforma TMS que exibe atualizações em chinês e inglês de armazém, exportação e parceiros locais no horário de Pequim (`Asia/Shanghai`):

| A CMT Express informa | O que significa para você | O Peek mostra |
| --- | --- | --- |
| `货物电子信息已经收到` (`The electronic cargo information has been received`), `Order processed by shipper` | Pedido eletrônico recebido do vendedor antes de o pacote físico chegar ao armazém da CMT em Shenzhen. | "Etiqueta criada" |
| `Arrive at SZ warehouse` (`ARRIVAL`) | Pacote físico recebido e registrado no armazém da CMT Express em Shenzhen. | "Recebida pela transportadora" |
| Despacho do hub de exportação, voo internacional e atualizações aduaneiras/locais | Processada para exportação, embarcada para o exterior, liberada na alfândega e transferida ao parceiro local de entrega. | "Em trânsito" |
| Leitura de saída para entrega do parceiro de destino | Carregada para entrega final pela transportadora local no país de destino. | "Saiu para entrega" |
| Leitura de entrega do parceiro de destino | Entregue ao destinatário pela transportadora local. | "Entregue" |

## Como funciona a entrega internacional da CMT Express

Segundo [cmtexpress.com.cn](https://www.cmtexpress.com.cn/) e [Sobre a CMT Express](https://www.cmtexpress.com.cn/about.html), a empresa tem sede em Shenzhen e opera agenciamento marítimo, aéreo e rodoviário, expresso internacional, desembaraço aduaneiro, armazenagem, embalagem e distribuição.

**Entrega na última milha e alfândega.** A CMT Express atua como agente de origem e consolidadora, e não como frota própria de entregas ao consumidor fora da China. Após o manuseio de exportação e o transporte internacional aéreo/marítimo, um correio ou transportadora local do seu país executa a entrega final, tentativas e guarda em ponto de retirada conforme suas próprias regras. Como a CMT Express atende empresas remetentes e não consumidores finais, seu site não publica garantias de prazo ao consumidor nem número de tentativas de entrega. Pedidos internacionais estão sujeitos à alfândega do destino, IVA de importação e tributos aplicáveis, salvo pagamento prévio no checkout; veja [encomendas retidas na alfândega](guide:customs).

## Encomenda CMT Express parada, sem atualização ou não entregue

Como a loja online ou marketplace detém o contrato de transporte com a CMT Express, contate sempre o vendedor primeiro para pedidos atrasados, endereços incorretos ou reembolsos.

- **Intervalos sem leitura após `Arrive at SZ warehouse`:** lotes consolidados internacionais frequentemente pausam por vários dias enquanto aguardam exportação, embarque aéreo ou entrada no destino. Veja [por que o rastreamento para de atualizar](guide:tracking-not-updating).
- **Verifique eventual código local:** se o rastreador da CMT exibir um código da transportadora local após a chegada ao seu país, acompanhe-o diretamente no site dessa transportadora para ver opções de entrega.
- **Extraviada, danificada ou marcada entregue sem chegada:** verifique primeiro com moradores, vizinhos, portaria e transportadora local e avise rapidamente a loja online antes de encerrar a proteção ao comprador. Veja [entregue mas não recebido](guide:delivered-not-received), [encomenda danificada](guide:damaged-parcel), [encomenda perdida](guide:lost-parcel) e [devolvida ao remetente](guide:returned-to-sender).

## Atendimento ao cliente e contatos da CMT Express

Dados oficiais publicados em [cmtexpress.com.cn](https://www.cmtexpress.com.cn/), [cmtexpress.com.cn/en/](https://www.cmtexpress.com.cn/en/) e [Contact Us](https://www.cmtexpress.com.cn/contact.html):

- **Telefone (China):** `13927573102`.
- **Portal de rastreamento:** [tms.cmtexpress.com.cn/en/trackIndex.htm](https://tms.cmtexpress.com.cn/en/trackIndex.htm).
- **Empresa:** Shenzhen Chengmaotong Supply Chain Co., Ltd. (*深圳诚贸通供应链有限公司*), Shenzhen, China.

> Atenção a mensagens falsas por SMS ou WhatsApp pedindo taxa no cartão para "liberar alfândega" ou "corrigir endereço" de uma encomenda vinda da China. Confirme qualquer taxa de importação legítima primeiro na página do pedido no marketplace ou no site do correio local. Veja [golpes de SMS de entrega](guide:delivery-scam-texts).

## Rastreie uma encomenda CMT Express com o Peek

O [Peek](/) consulta a CMT Express automaticamente como candidata única ao informar um código de 15 caracteres `CMT` + 12 dígitos (`CMT482917364058`, dígitos fictícios) e mostra "CMT Express está com esta encomenda." quando o rastreador confirma; também reconhece links colados de `tms.cmtexpress.com.cn/en/trackIndex.htm?documentCode=`. Se precisar escolher manualmente, toque em "Trocar" ao adicionar ou "Trocar transportadora" na tela do pacote (buscar "诚贸通" ou "CMT" também encontra).

O Peek lê diretamente o rastreador da CMT Express e mostra a etapa ("Etiqueta criada", "Recebida pela transportadora", "Em trânsito", "Saiu para entrega", "Entregue"), o texto em inglês da plataforma (`The electronic cargo information has been received` para `货物电子信息已经收到`), o local sem abreviações de armazém como `SZ.` e o país de destino. Quando a CMT registra o pacote sob outro número diferente do informado, o Peek mostra em "CMT Express rastreia com o código". Códigos internos e nome do serviço não aparecem nos apps do Peek. Como a CMT fornece horários sem fuso, o Peek aplica o horário de Pequim (`Asia/Shanghai`) e converte para o relógio do seu dispositivo. Quando a CMT informa o código da transportadora local e o Peek a reconhece, ele confirma se ela já está com o pacote: a página então mostra "Entrega por" com o nome e lista ambos os números. O Peek omite quantidade de volumes, peso, referência do cliente, nomes de remetente e destinatário, endereço, códigos postais, assinatura, registros POD e dados de pagamento; o rastreador da CMT não informa data estimada nem horário separado de entrega.

Enquanto você mantém a encomenda aberta no Peek, ou no dia da entrega com notificações ativadas, o Peek consulta até a cada 10 minutos; no dia em que a CMT Express registra saída para entrega, a cada 2 minutos a partir das 6h (horário de Pequim) enquanto o rastreador próprio responder. Nos demais casos, consulta de hora em hora. Após 12 horas apenas em "Etiqueta criada" ou "Recebida pela transportadora", ou dois dias sem novidades, consulta de hora em hora em qualquer modo, e a cada 6 horas após sete dias em "Etiqueta criada". Quando o rastreador da CMT Express não responde e o Peek acompanha apenas por serviços de rastreamento, consulta de hora em hora, a cada 3 horas após um dia sem movimento e até a cada 15 minutos no dia de saída para entrega.

Quando a leitura mais recente tem quatro dias e o pacote segue em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito", o Peek sinaliza a data dessa leitura, como "Sem atualizações desde seg 12 out". A página do pacote então exibe "É comum uma encomenda ficar sem atualizações em voos longos, na alfândega ou no fim de semana. O Peek continua consultando CMT Express." Após três semanas sem novas leituras, mostra "Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, fale com o vendedor. O Peek continua consultando CMT Express."

Você pode escolher alertas para "Cada movimentação", "Etapas importantes" ou "Só a entrega". O Peek monitora apenas atualizações de rastreamento e não pode liberar alfândega nem contatar a CMT Express ou sua transportadora local por você.

## Perguntas sobre o rastreamento da CMT Express

### O que é a CMT Express (*诚贸通*)?

A CMT Express (*Shenzhen Chengmaotong Supply Chain Co., Ltd.*) é uma operadora de logística internacional e e-commerce sediada em Shenzhen que movimenta pequenos pacotes e cargas pelo mundo em conjunto com parceiros expressos e postais.

### Como é um código de rastreamento `CMT`?

Os conhecimentos da CMT Express consistem em `CMT` seguido de 12 dígitos (15 caracteres ao todo, por exemplo `CMT482917364058`, dígitos fictícios).

### O que significa `Arrive at SZ warehouse`?

Significa que a CMT Express recebeu fisicamente seu pacote e o registrou em seu armazém de exportação em Shenzhen (`SZ`) antes do despacho internacional.

### Como entro em contato com a CMT Express?

Fale primeiro com o vendedor online ou marketplace para reembolsos, ajustes de endereço ou atrasos. A CMT Express informa o telefone `13927573102` e o rastreador em [tms.cmtexpress.com.cn/en/trackIndex.htm](https://tms.cmtexpress.com.cn/en/trackIndex.htm).

:::sources
- [CMT Express: Site em chinês](https://www.cmtexpress.com.cn/) – *深圳诚贸通供应链有限公司*, cobertura global, produtos via DHL, UPS, FedEx, TNT e USPS, `13927573102`
- [CMT Express: Site em inglês](https://www.cmtexpress.com.cn/en/) – página inicial em inglês
- [CMT Express: Sobre nós](https://www.cmtexpress.com.cn/about.html) – agenciamento aéreo/marítimo/terrestre, expresso internacional, despacho aduaneiro e armazenagem em Shenzhen
- [CMT Express: Fale conosco](https://www.cmtexpress.com.cn/contact.html) – telefone publicado `13927573102`
- [CMT Express: Portal de Rastreamento TMS](https://tms.cmtexpress.com.cn/en/trackIndex.htm) – consulta pública em inglês
- [Universal Parcel Scraper: cmt-express](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/cmt-express) – formato `CMT\d{12}`, regra de link `tms.cmtexpress.com.cn`, mapeamento de status em chinês e inglês, fuso `Asia/Shanghai` e campos removidos
:::
