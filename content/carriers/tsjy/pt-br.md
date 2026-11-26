---
title: Rastreamento TSJY (通世集运): códigos de pedido e status
description: Rastreie encomendas consolidadas da TSJY (通世集运) vindas da China: formato TSJY…CN, tradução dos status em chinês e quando a 17FEIA assume o envio.
slug: tsjy-tracking
published: 2026-11-26
updated: 2026-11-26
---

A TSJY (通世集运) é um serviço de consolidação de compras na China operado pela 苏州通世供应链有限公司, empresa de cadeia de suprimentos sediada em Suzhou. Você compra em plataformas chinesas como Taobao, Tmall, Pinduoduo, JD ou Douyin, e as lojas enviam os produtos para o armazém da TSJY em Suzhou. A TSJY armazena os itens, junta tudo em um só volume e despacha ao exterior. Rastreie seu código TSJY no [site da TSJY](https://www.tsjy56.com/), em 订单查询 (consulta de pedidos), ou cole o código na caixa acima.

## Código de rastreamento TSJY: como é o formato

O site da TSJY não descreve seus códigos, mas eles seguem um padrão fixo. TSJY são as iniciais de 通世集运 (*Tongshi Jiyun*). Todos os exemplos abaixo são inventados:

| Código | O que é |
| --- | --- |
| `TSJY482917365CN` | `TSJY`, nove dígitos e `CN`: código de pedido da TSJY. |
| `CFJY482917365CN` | `CFJY`, nove dígitos e `CN`: também código de pedido da TSJY, de uma segunda série. |
| `TSJY48291736CN` | Apenas oito dígitos: fora do padrão da TSJY, e o Peek não sugere TSJY. Confira se faltou um dígito. |
| `FEIKM4829173650YQ` | `FEI`, duas letras, dez dígitos e `YQ`: código da [17FEIA](carrier:17feia), linha que transporta parte dos envios da TSJY ao exterior. |

No Peek, letras minúsculas, espaços e hifens não interferem.

**Onde encontrar.** É o número do seu pedido de envio (订单号), gerado ao solicitar o despacho na página web da TSJY (网页下单) ou pelo WeChat (微信下单) depois que suas compras chegam ao armazém. Se outra pessoa contratou o envio, peça a ela.

**Dois códigos.** A busca da TSJY também aceita o código da linha transportadora (运单号). No celular, o cartão de resultado de cada pedido exibe o código da TSJY e, após uma barra, o código da linha. Toque no cartão para abrir o histórico (轨迹详情).

## Status de rastreamento da TSJY explicados

O rastreador da TSJY está em chinês. No celular, cada cartão de resultado mostra um status resumido para o pedido inteiro:

| O cartão diz | O que significa para você |
| --- | --- |
| `等待处理` | Aguardando processamento: a TSJY recebeu sua ordem de envio. |
| `开始拣货`, `正在拣货`, `拣货完成`, `开始打包`, `已称重`, `已录单` | O armazém separa os itens, embala, pesa o pacote e registra o envio. |
| `等待出库`, `已出库`, `运输途中` | Aguardando saída do armazém; saiu do armazém; em trânsito. |
| `已送达`, `已完成` | Entregue; concluído. |
| `运输异常` | Problema no transporte. Contate a TSJY. |

O Peek não usa esse status resumido do cartão; ele lê as movimentações detalhadas (轨迹) dentro dele. Primeiro aparecem as etapas internas no armazém da TSJY, em chinês:

| A TSJY diz | O que significa para você | O Peek mostra |
| --- | --- | --- |
| `订单已提交，等待系统确认` | Pedido enviado, aguardando confirmação do sistema. Ainda não começou a separação. | "Etiqueta criada" |
| `仓库开始拣货` | O armazém iniciou a separação dos itens. | "Recebida pela transportadora" |
| `仓库拣货完成` | Separação concluída. | "Recebida pela transportadora" |
| `订单打包完成，等待出库` | Embalado, aguardando saída do armazém. Pode aparecer duas vezes no mesmo minuto. | "Recebida pela transportadora" |
| `订单已出库，开始国际运输` | Saiu do armazém da TSJY e iniciou o trajeto internacional. | "Em trânsito" |

Na linha 17FEIA, a TSJY passa então a reproduzir as movimentações da 17FEIA, em inglês:

| A linha diz | O que significa para você | O Peek mostra |
| --- | --- | --- |
| `Item information received.` | A 17FEIA recebeu os dados do envio. A TSJY ainda pode estar com o pacote. | "Recebida pela transportadora" |
| `The package is on the way to the facility.`, `Warehouse receipt.` | A caminho do armazém da 17FEIA na China e, depois, recebido lá. | "Em trânsito" |
| `The order has information pending customer confirmation.`, `Order Confirmed.` | O armazém da 17FEIA retém o pacote até a confirmação do pedido. | "Em trânsito" |
| `Order dispatched from warehouse.` | Saiu do armazém da 17FEIA. | "Em trânsito" |
| `Export declaration in progress.` | Declaração de exportação da China em andamento, etapa de rotina antes do voo. Não está retido nem passou pela alfândega do seu país. | "Em trânsito" |
| `Flight has departed.` | O voo decolou. | "Em trânsito" |
| `Flight delayed.` | O voo atrasou. A encomenda não se moveu. | mantém o status da linha anterior |
| `Flight Arrived at Transit Hub.`, `Flight Departed from Transit Hub.` | Escala no caminho. | "Em trânsito" |
| `Flight Arrived at Clearance Port.` | Pousou no ponto onde ocorrerá o desembaraço de importação. | "Em trânsito" |
| `Broker picked up Shipment and delivered to warehouse.` | Um despachante aduaneiro levou o pacote ao armazém para desembaraço. Não é entrega a você. | "Na alfândega" |
| `Customs Clearance is in progress.` | Alfândega de importação analisando o pacote. | "Na alfândega" |
| `Customs Clearance Completed.` | Liberado pela alfândega. | "Em trânsito" |

Em outras linhas, o pedido da linha também pode surgir: `已下单` (pedido registrado), em chinês e sem local, entre a embalagem e a saída da TSJY. Assim como `Item information received.` da 17FEIA, ocorre enquanto o pacote ainda está com a TSJY ("Recebida pela transportadora"). Depois da saída da TSJY, `已出库` (saída do armazém), também em chinês e sem local, indica que o armazém dessa linha despachou o pacote um dia após recebê-lo ("Em trânsito").

O Peek ignora a linha da 17FEIA com data estimada de partida (que não é uma movimentação real) e qualquer linha com data futura. Demais registros — como os da transportadora final — são interpretados pelas palavras-chave (como entregue ou alfândega).

**Horários.** A página de detalhes da TSJY exibe os horários no relógio do seu aparelho, e o Peek faz o mesmo para as etapas da TSJY. Para as linhas de armazém da 17FEIA, o Peek usa o horário de Pequim (UTC+8), como a 17FEIA registra. Quando o local de uma linha posterior termina com um código de país — como `(DE)` — de um país com fuso único, o Peek lê o horário no relógio desse país.

## Quem entrega encomendas da TSJY

O site da TSJY informa que envia para mais de 220 países e regiões por frete aéreo expresso (加急空运) ou marítimo econômico (经济海运), mas não informa quem entrega em cada país nem divulga prazos. A página de detalhes exibe o nome da linha (渠道, canal) usada no pedido.

O Peek conhece uma dessas linhas pelo nome: a [17FEIA](carrier:17feia) (一起飞), empresa de logística na China com rastreador próprio em `www.17feia.com`. O rastreamento da 17FEIA pode informar a transportadora local no seu país e o código dela.

A TSJY não divulga regras para tentativas de entrega ou pontos de retirada, que ficam a cargo da empresa entregadora. Veja [entrega perdida](guide:missed-delivery).

**Alfândega na UE.** Compras on-line trazidas de fora da UE pagam imposto aduaneiro temporário de €3 por item em pacotes até €150, em vigor desde 1º de julho de 2026, e taxa de manuseio da União de €2 por item, qualquer que seja o valor, desde 1º de novembro de 2026. Cada item reúne mercadorias com mesma classificação tarifária, descrição e, quando exigido, origem. Ambos incidem sobre vendas à distância (bens enviados pelo vendedor ou em seu nome). A orientação da Comissão Europeia indica que bens não são enviados pelo vendedor quando o próprio cliente contrata o transporte sem participação do vendedor, o que pode ocorrer ao contratar a TSJY diretamente — a decisão cabe à alfândega do seu país. O site da TSJY não informa como impostos ou taxas são pagos. Veja [encomendas retidas na alfândega](guide:customs).

## Encomenda TSJY sem atualização, extraviada ou devolvida

Na TSJY, normalmente você mesmo é o cliente contratante do envio. Por isso, em caso de atraso, extravio ou dano, fale primeiro com a TSJY.

**Produtos ainda não chegaram ao armazém.** Antes de chegarem à TSJY, as compras viajam com transportadoras domésticas chinesas: acompanhe no site onde comprou. Segundo a TSJY, o rastreamento dela começa quando o pacote entra no armazém e vai até a assinatura na entrega.

**Nada encontrado para o código.** Para códigos desconhecidos, a página móvel de resultados da TSJY exibe `未找到相关的轨迹信息。` (nenhuma informação de rastreamento encontrada). Confira se houve erro de digitação. O pacote mostra "Aguardando TSJY" enquanto o Peek continua consultando. Se a TSJY ainda não reconhecer o código dois dias depois e nenhuma outra fonte responder, o Peek mostra "Depois de dois dias, a transportadora ainda não reconhece este código. Confira o código, escolha outra transportadora ou exclua a encomenda." e passa a consultar diariamente — e semanalmente após uma semana da adição.

**Parada no armazém.** Se as etapas internas da TSJY pararem por vários dias, procure o atendimento ao cliente dela.

**Sem atualizações após o voo.** Depois de `Flight has departed.`, pode não haver novidades até o pouso ou a alfândega. Quando a linha mais recente completa quatro dias e o pacote segue em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito", o Peek marca com a data daquela movimentação, como "Sem atualizações desde seg 12 out". A página então mostra "É comum uma encomenda ficar sem atualizações em voos longos, na alfândega ou no fim de semana. O Peek continua consultando TSJY." Como TSJY e 17FEIA não têm página de ajuda cadastrada no Peek, o link "Pedir ajuda a …" só aparece quando o Peek passa a acompanhar a transportadora final (se ela tiver página de ajuda). Após três semanas sem novidade, aparece "Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, fale com o vendedor. O Peek continua consultando TSJY." Veja [por que o rastreamento para de atualizar](guide:tracking-not-updating) e [rastrear encomendas da China](guide:tracking-from-china).

**Extraviada ou danificada.** O site da TSJY promete indenização integral por perda ou dano (丢破必赔), mas suas páginas públicas não informam regras ou prazos de reclamação: contate o suporte imediatamente e guarde a caixa e os produtos. Veja [encomenda extraviada](guide:lost-parcel) e [encomenda danificada](guide:damaged-parcel).

**Entregue sem recebimento ou devolvida.** Verifique ao redor da casa e pergunte aos vizinhos, depois avise a TSJY. O site não traz regras sobre devoluções. Veja [entregue mas não recebida](guide:delivered-not-received) e [devolvida ao remetente](guide:returned-to-sender).

## Atendimento e contato da TSJY

Na [página inicial da TSJY](https://www.tsjy56.com/), na seção 客服中心 (central de atendimento):

- **Telefone e e-mail:** telefone de atendimento (客户服务电话) e endereço de e-mail (客服邮箱), sem horário informado.
- **WeChat:** código QR para adicionar o atendimento no WeChat (添加微信客服), além da conta de serviço e miniprograma no WeChat.
- **Armazém:** em Huaqiao (花桥镇), Suzhou, com endereço completo na página.

O site está disponível apenas em chinês e não menciona atendimento em outros idiomas.

## Acompanhe uma encomenda TSJY com o Peek

O [Peek](/) identifica a TSJY imediatamente em códigos `TSJY…CN` ou `CFJY…CN` ("Transportadora detectada"): nenhuma outra transportadora acompanhada pelo Peek usa esses padrões. Um link para a página de resultados da TSJY contendo um código TSJY após `nos=` também funciona; havendo vários códigos, o Peek lê o primeiro. Links da TSJY com o código da linha, ou qualquer código fora do padrão TSJY, não ficam classificados como TSJY: o Peek trata o código por conta própria como se você o tivesse colado diretamente.

Para qualquer outro código, cole-o assim mesmo: o Peek acompanha mais de 4.500 transportadoras. Quando o formato cabe em várias empresas, mostra "Consultando serviços de rastreamento…" enquanto consulta; quando não cabe em nenhuma, pesquisa o código depois que você adiciona. Se o Peek não nomear a transportadora, toque em "Trocar" e escolha em "Todas as transportadoras", ou use "Trocar transportadora" depois. Se escolher TSJY para um código de outro padrão, o Peek avisa que a TSJY "não emite códigos como este".

O Peek lê o próprio rastreador da TSJY: cada movimentação com local e horário, omitindo origem, país de destino e nome da linha. A TSJY não informa data prevista de entrega nem peso. O status no Peek acompanha a linha mais recente compreendida.

Quando a TSJY informa um código da 17FEIA, ou o código de uma linha cujo formato aponte uma transportadora conhecida pelo Peek, o Peek passa a consultá-la. Enquanto essa transportadora não tiver uma movimentação ao menos tão recente quanto a última da TSJY, o Peek segue lendo a TSJY, já exibindo quem entrega ("Entrega por 17FEIA", ou "Anunciada a 17FEIA, ainda não recebida" enquanto a 17FEIA tiver apenas o aviso prévio, sem movimentação própria) e o respectivo código. Depois disso, passa a acompanhar por lá mantendo o histórico da TSJY.

Com a encomenda aberta no Peek, ou no dia da entrega com notificações ligadas, o Peek verifica até a cada 10 minutos enquanto o próprio rastreador da TSJY responder. Após dois dias sem novidades (ou 12 horas em "Etiqueta criada" ou "Recebida pela transportadora"), consulta de hora em hora, e a cada 6 horas depois de sete dias em "Etiqueta criada". Quando passa a seguir a 17FEIA ou a transportadora entregadora, acompanha na frequência dela — até a cada 2 minutos a partir das 6h no horário local no dia em que sair para entrega quando o Peek lê o rastreador próprio da empresa.

O Peek envia alertas de "Cada movimentação", "Etapas importantes" ou "Só a entrega". Ele não entra em contato com a TSJY, a 17FEIA nem as lojas por você.

## Perguntas sobre o rastreamento da TSJY

### O que é a TSJY (通世集运)?

É um serviço de consolidação de encomendas (集运) voltado a quem compra em lojas chinesas morando no exterior. Segundo o site, foi fundado em 2005, oferece armazenagem gratuita por 180 dias e envia para mais de 220 países e regiões.

### Onde rastrear um código da TSJY?

No [site da TSJY](https://www.tsjy56.com/), em 订单查询: digite o código TSJY ou o código da linha e toque em 查询. Ou cole o código no Peek.

### O que significa 订单打包完成，等待出库?

Em chinês, significa pedido embalado, aguardando saída do armazém: seu pacote está pronto no armazém da TSJY esperando a linha transportadora. O Peek exibe "Recebida pela transportadora". Em seguida vem `订单已出库，开始国际运输`: saiu do armazém e começou a viagem internacional.

### Por que minha encomenda TSJY mostra um código da 17FEIA?

A [17FEIA](carrier:17feia) é a linha que leva seu pacote ao exterior: o cartão de resultado da TSJY mostra o código `FEI…YQ` após uma barra. Assim que a 17FEIA alcançar as movimentações, o Peek passa a acompanhar lá.

### Como entrar em contato com a TSJY?

Pela seção 客服中心 na página inicial do site: há telefone, e-mail e WeChat, todos em chinês.

:::sources
- [TSJY: 通世集运 (home page)](https://www.tsjy56.com/) – company, services, compensation promise, tracking span, order search and its results page on a phone, ordering, contacts, warehouse
- [TSJY: 轨迹查询 (tracking results)](https://www.tsjy56.com/orderResult.html) – results on a phone: cards with both numbers, card statuses, no-result message
- [TSJY: 轨迹详情 (track details)](https://www.tsjy56.com/orderDetail.html) – channel (渠道) name and number, tracking lines, times on the device's clock
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – €3 duty since 1 July 2026, €2 Union handling fee since 1 November 2026
- [European Commission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – €2 per item whatever the value, what an item is, distance sales and transport the customer arranges
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – number shape, link, wordings and stages, clocks, hand-over to the line, what Peek reads
:::
