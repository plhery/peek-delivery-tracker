---
title: Seguimento TSJY (通世集运): números de pedido e estados
description: Acompanha uma encomenda consolidada TSJY (通世集运) da China: formato de um número TSJY…CN, significado dos estados em chinês e quando a 17FEIA assume.
slug: seguimento-tsjy
published: 2026-11-23
updated: 2026-11-23
---

A TSJY (通世集运) é um serviço de consolidação de encomendas na China operado pela 苏州通世供应链有限公司, uma empresa logística de Suzhou. Compras em sites chineses como Taobao, Tmall, Pinduoduo, JD ou Douyin, e as lojas enviam os artigos para o armazém da TSJY em Suzhou. A TSJY guarda-os, junta-os num único volume e expede-os para o estrangeiro. Acompanha o teu número TSJY no [site da TSJY](https://www.tsjy56.com/), em 订单查询 (pesquisa de pedido), ou cola-o na caixa acima.

## Número de seguimento TSJY: como é

O site da TSJY não descreve os seus números, mas seguem um formato fixo. TSJY são as iniciais de 通世集运, Tongshi Jiyun. Todos os exemplos abaixo são inventados:

| Número | O que é |
| --- | --- |
| `TSJY482917365CN` | TSJY, nove dígitos e CN: número de pedido TSJY. |
| `TSJY48291736CN` | Apenas oito dígitos: não é o formato TSJY e o Peek não identifica a TSJY. Verifica se falta um dígito. |
| `FEIKM4829173650YQ` | FEI, duas letras, dez dígitos e YQ: número da [17FEIA](carrier:17feia), linha que transporta alguns envios TSJY para o estrangeiro. |

No Peek, minúsculas, espaços e hífenes não importam.

**Onde encontrar.** É o número da tua ordem de envio (订单号), submetida na página web de pedidos da TSJY (网页下单) ou no WeChat (微信下单) quando as compras chegam ao armazém. Se outra pessoa reservou o envio, pede-lho.

**Dois números.** A caixa de pesquisa da TSJY aceita também o número da linha (运单号). No telemóvel, o cartão de cada pedido mostra o número TSJY e, após uma barra, o da linha. Toca no cartão para ver as linhas de seguimento (轨迹详情).

## Estados de seguimento TSJY explicados

O site da TSJY está em chinês. No telemóvel, cada cartão apresenta um estado curto para todo o pedido:

| O cartão indica | O que significa para ti |
| --- | --- |
| `等待处理` | A aguardar processamento: a TSJY recebeu a tua ordem de envio. |
| `开始拣货`, `正在拣货`, `拣货完成`, `开始打包`, `已称重`, `已录单` | O armazém recolhe os artigos, embala e pesa o volume e regista a ordem. |
| `等待出库`, `已出库`, `运输途中` | A aguardar saída do armazém; já saiu; em trânsito. |
| `已送达`, `已完成` | Entregue; concluído. |
| `运输异常` | Problema no transporte. Pergunta à TSJY. |

O Peek não lê o estado do cartão, mas sim as linhas de seguimento (轨迹) por trás. Primeiro surgem as etapas internas da TSJY no armazém, em chinês:

| A TSJY indica | O que significa para ti | O Peek mostra |
| --- | --- | --- |
| `订单已提交，等待系统确认` | Pedido submetido, à espera de confirmação do sistema. Ainda nada foi recolhido. | «Etiqueta criada» |
| `仓库开始拣货` | O armazém começou a recolher os artigos. | «Aceite pela transportadora» |
| `仓库拣货完成` | Recolha concluída. | «Aceite pela transportadora» |
| `订单打包完成，等待出库` | Embalado, à espera de sair do armazém. Pode aparecer duas vezes no espaço de um minuto. | «Aceite pela transportadora» |
| `订单已出库，开始国际运输` | Saiu do armazém da TSJY e começou a viagem internacional. | «Em trânsito» |

Na linha 17FEIA, a TSJY copia depois as linhas da 17FEIA, em inglês:

| A linha indica | O que significa para ti | O Peek mostra |
| --- | --- | --- |
| `Item information received.` | A 17FEIA recebeu os dados do pedido. A TSJY pode ainda ter a encomenda. | «Aceite pela transportadora» |
| `The package is on the way to the facility.`, `Warehouse receipt.` | A caminho do armazém da 17FEIA na China, e depois recebida lá. | «Em trânsito» |
| `The order has information pending customer confirmation.`, `Order Confirmed.` | O armazém da 17FEIA retém a encomenda até confirmação do pedido. | «Em trânsito» |
| `Order dispatched from warehouse.` | Saiu do armazém da 17FEIA. | «Em trânsito» |
| `Export declaration in progress.` | Declaração de exportação na China em curso, passo habitual antes do voo. Não está retida pela alfândega, nem se trata da alfândega do teu país. | «Em trânsito» |
| `Flight has departed.` | O voo partiu. | «Em trânsito» |
| `Flight delayed.` | Voo atrasado. A encomenda não se moveu. | o estado da linha anterior |
| `Flight Arrived at Transit Hub.`, `Flight Departed from Transit Hub.` | Escala durante a viagem. | «Em trânsito» |
| `Flight Arrived at Clearance Port.` | Aterrou onde a alfândega de importação fará o desalfandegamento. | «Em trânsito» |
| `Broker picked up Shipment and delivered to warehouse.` | Um despachante levou-a ao seu armazém para desalfandegamento. Não é uma entrega em tua casa. | «Na alfândega» |
| `Customs Clearance is in progress.` | A alfândega de importação está a verificar o envio. | «Na alfândega» |
| `Customs Clearance Completed.` | Libertada pela alfândega. | «Em trânsito» |

Noutra linha pode também aparecer o registo dela: `已下单` (pedido efetuado), em chinês e sem local, entre o embalamento pela TSJY e a saída. Tal como `Item information received.` da 17FEIA, surge enquanto a TSJY ainda tem a encomenda: «Aceite pela transportadora». Depois da saída da TSJY, `已出库` (saída de armazém), também em chinês e sem local, significa que o armazém dessa linha expediu o volume no dia seguinte a recebê-lo: «Em trânsito».

O Peek ignora a linha da 17FEIA com data estimada de partida, que não é um registo real, bem como qualquer linha com data futura. Outras linhas, como as da transportadora final, são interpretadas pelas palavras, como entregue ou alfândega.

**Horas.** A página de detalhes da TSJY mostra cada hora no relógio do teu dispositivo, e o Peek mostra do mesmo modo os passos próprios da TSJY. O Peek lê as linhas de armazém da 17FEIA na hora de Pequim (UTC+8), conforme a 17FEIA as regista. Quando o local de uma linha posterior termina num código de país como `(DE)`, o Peek lê a hora no fuso desse país se este tiver apenas um fuso horário.

## Quem entrega as encomendas TSJY

O site da TSJY diz expedir para mais de 220 países e regiões, por via aérea expresso (加急空运) ou marítima económica (经济海运). Não indica quem entrega em cada país nem dá prazos de entrega. A página de detalhes nomeia a linha (渠道, canal) em que cada envio viaja.

O Peek conhece uma linha pelo nome: [17FEIA](carrier:17feia) (一起飞), empresa logística na China com seguimento próprio em www.17feia.com. O seguimento da 17FEIA pode indicar a empresa que entrega no teu país e o respetivo número.

A TSJY não publica regras de tentativas de entrega nem pontos de levantamento: dependem da empresa que entrega. Vê [entregas falhadas](guide:missed-delivery).

**Alfândega na UE.** Bens comprados online e introduzidos na UE vindos de fora têm um direito aduaneiro temporário de 3 € por artigo em encomendas até 150 € desde 1 de julho de 2026, e uma taxa de tratamento da União de 2 € por artigo, qualquer que seja o valor, desde 1 de novembro de 2026. Um artigo são os bens com a mesma classificação pautal, descrição e, quando exigido, origem. Ambos aplicam-se a vendas à distância: bens enviados pelo vendedor ou por sua conta. A orientação da Comissão diz que os bens não são enviados pelo vendedor quando o cliente organiza o transporte sem intervenção do vendedor, o que pode acontecer quando contratas a TSJY diretamente: decide a alfândega do teu país. O site da TSJY não diz como se pagam direitos ou taxas. Vê [encomendas retidas na alfândega](guide:customs).

## Encomenda TSJY sem atualizar, perdida ou devolvida

Com a TSJY és normalmente tu o cliente, pois reservaste o envio. Assim, em caso de atraso, perda ou dano, contacta primeiro a TSJY.

**Artigos ainda não chegados ao armazém.** Antes de chegarem à TSJY, as compras viajam com as transportadoras chinesas das lojas: acompanha-as onde compraste. O site da TSJY diz que o seguimento começa quando o volume entra no armazém e decorre até ser assinado.

**Nada para este número.** Para um código desconhecido, a página móvel da TSJY mostra `未找到相关的轨迹信息。` (sem informação de seguimento encontrada). Confirma se há erro de escrita. Aparece «À espera de TSJY» e o Peek continua a verificar. Se dois dias depois a TSJY continuar sem conhecer o número e nada mais responder, o Peek mostra «Ao fim de dois dias, a transportadora ainda não reconhece este número. Verifica-o, escolhe outra transportadora ou elimina a encomenda.» e passa a verificar diariamente, e semanalmente após uma semana desde que a adicionaste.

**Parada no armazém.** Se os passos internos da TSJY pararem durante vários dias, pergunta ao apoio ao cliente.

**Sem novidades após o voo.** Depois de `Flight has departed.`, pode não surgir nada novo até à aterragem ou alfândega. Se uma encomenda ainda em «Etiqueta criada», «Aceite pela transportadora» ou «Em trânsito» passar quatro dias sem nova linha, o Peek assinala-a com «Sem atualizações há quatro dias» (ou «Sem atualizações desde …»), explica: «É habitual uma encomenda ficar sem novidades em voos longos, na alfândega ou ao fim de semana. O Peek continua a perguntar a TSJY.» e mostra «Pede ajuda a TSJY». Vê [porque o seguimento deixa de atualizar](guide:tracking-not-updating) e [seguir encomendas da China](guide:tracking-from-china).

**Perdida ou danificada.** O site da TSJY promete indemnização integral por perda ou dano (丢破必赔), mas as páginas públicas não dão regras nem prazos: contacta logo o apoio ao cliente e guarda a caixa e os artigos. Vê [encomendas perdidas](guide:lost-parcel) e [encomendas danificadas](guide:damaged-parcel).

**Entregue mas não chegou, ou devolvida.** Verifica à volta de casa e pergunta aos vizinhos, depois avisa a TSJY. O site não refere devoluções. Vê [entregue mas não recebida](guide:delivered-not-received) e [devolvida ao remetente](guide:returned-to-sender).

## Apoio ao cliente e contactos TSJY

Na [página inicial da TSJY](https://www.tsjy56.com/), na secção 客服中心 (centro de apoio ao cliente):

- **Telefone e e-mail:** um número de apoio ao cliente (客户服务电话) e um e-mail (客服邮箱). Não indica horários.
- **WeChat:** um código QR para adicionar o apoio ao cliente no WeChat (添加微信客服), além da conta de serviço e mini-programa WeChat.
- **Armazém:** em Huaqiao (花桥镇), Suzhou, com a morada completa na página.

O site está apenas em chinês e não menciona atendimento em inglês.

## Seguir uma encomenda TSJY com o Peek

O [Peek](/) identifica logo a TSJY a partir de um número `TSJY…CN` («Transportadora detetada»): nenhuma outra transportadora seguida pelo Peek usa este formato. Uma ligação para os resultados da TSJY com um número TSJY após `nos=` também funciona; havendo vários, o Peek usa o primeiro. Uma ligação da TSJY com o número da linha ou qualquer número fora do formato TSJY não fica sob a TSJY: o Peek lê esse número por si só, como se o tivesses colado.

Para qualquer outro número, cola-o na mesma: o Peek acompanha mais de 4500 transportadoras. Quando o formato corresponde a várias, o Peek mostra «A verificar serviços de seguimento…» enquanto consulta; não correspondendo a nenhuma, pesquisa-o depois de adicionares. Se o Peek não indicar a transportadora, toca em «Alterar» e escolhe-a em «Todas as transportadoras», ou usa «Alterar transportadora» mais tarde. Se escolheres TSJY para outro número, o Peek diz que a TSJY «não emite números como este».

O Peek lê o seguimento da TSJY: cada linha com local e hora. Omite a origem, o país de destino e o nome da linha. A TSJY não fornece data de entrega, peso, código de levantamento nem quem assinou. O estado no Peek acompanha a linha mais recente que entende.

Quando a TSJY indica um número 17FEIA, ou um número de linha cujo formato aponta para uma transportadora que o Peek conhece, o Peek consulta essa transportadora. Enquanto esta não tiver um registo pelo menos tão recente como o último da TSJY, o Peek mantém-se na TSJY mostrando já quem entrega («Entrega: 17FEIA», ou «Anunciada a 17FEIA, ainda não recebida» enquanto a 17FEIA tiver apenas o pré-aviso sem registo próprio) e o respetivo número. Depois o Peek passa a acompanhar lá a encomenda e conserva o histórico da TSJY.

Enquanto manténs a encomenda aberta no Peek, ou no dia da entrega com as notificações ativadas, o Peek verifica até de 10 em 10 minutos enquanto o site próprio da TSJY responder (assim que a 17FEIA ou a transportadora final assumir, o Peek acompanha o ritmo dessa transportadora, incluindo até de 2 em 2 minutos a partir das 6:00 hora local no dia da entrega quando o seu site direto responder). Após dois dias sem novidades (12 horas enquanto continuar em «Etiqueta criada» ou «Aceite pela transportadora»), o Peek verifica de hora em hora; e se ficar sete dias em «Etiqueta criada», de 6 em 6 horas. Pode avisar-te de «Todos os registos», «Passos importantes» ou «Só a entrega». O Peek não pode contactar a TSJY, a 17FEIA nem as lojas por ti.

## Perguntas sobre o seguimento TSJY

### O que é a TSJY (通世集运)?

Um serviço de consolidação (集运) para quem vive fora da China e compra em sites chineses. O site indica que foi fundado em 2005, guarda encomendas gratuitamente durante 180 dias e envia para mais de 220 países e regiões.

### Onde posso acompanhar um número TSJY?

No [site da TSJY](https://www.tsjy56.com/), em 订单查询: escreve o número TSJY ou o da linha e carrega em 查询. Ou cola-o no Peek.

### O que significa 订单打包完成，等待出库?

Em chinês significa «pedido embalado, a aguardar saída do armazém»: a tua encomenda está embalada no armazém da TSJY à espera da linha de envio. O Peek mostra «Aceite pela transportadora». A seguir surge `订单已出库，开始国际运输`: saiu e começou a viagem internacional.

### Porque a minha encomenda TSJY mostra um número 17FEIA?

A [17FEIA](carrier:17feia) é a linha que transporta a encomenda para o estrangeiro: o cartão de resultados da TSJY mostra o número `FEI…YQ` após uma barra. Assim que a 17FEIA acompanhar o progresso, o Peek segue a encomenda lá.

### Como contacto a TSJY?

Na secção 客服中心 da página inicial: telefone, e-mail e WeChat, tudo em chinês.

:::sources
- [TSJY: 通世集运 (home page)](https://www.tsjy56.com/) – empresa, serviços, promessa de compensação, alcance do seguimento, pesquisa de pedido e resultados no telemóvel, pedidos, contactos, armazém
- [TSJY: 轨迹查询 (tracking results)](https://www.tsjy56.com/orderResult.html) – resultados no telemóvel: cartões com ambos os números, estados de cartão, mensagem sem resultado
- [TSJY: 轨迹详情 (track details)](https://www.tsjy56.com/orderDetail.html) – nome e número do canal (渠道), linhas de seguimento, horas no relógio do dispositivo
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – direito de 3 € desde 1 de julho de 2026, taxa de tratamento da União de 2 € desde 1 de novembro de 2026
- [European Commission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 € por artigo qualquer que seja o valor, o que é um artigo, vendas à distância e transporte organizado pelo cliente
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formato do número, ligação, textos e etapas, fusos horários, passagem para a linha, o que o Peek lê
:::
