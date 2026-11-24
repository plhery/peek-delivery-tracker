---
title: Rastreamento DHLink: códigos, status e quem entrega
description: Rastreie um pacote da DHLink em pedidos do DHgate: códigos aceitos pelo rastreador, significado dos status, quem entrega e quem contatar.
slug: rastreamento-dhlink
published: 2026-11-24
updated: 2026-11-24
---

A DHLink (驼飞侠) é a plataforma de logística internacional do DHgate (敦煌网). Fundada em 2014, ela envia pedidos de vendedores do DHgate e de outros clientes a partir da China ou de seus armazéns no exterior, entregando-os a uma transportadora do seu país para o trecho final. Rastreie o código na [página de rastreamento da DHLink](https://www.dhlink.com/trackQuery?lang=en) ou na caixa acima. Pegue o código no seu pedido no DHgate ou peça ao vendedor.

## Código de rastreamento DHLink: como reconhecer

A DHLink não publica um padrão oficial de códigos. Seu rastreador aceita até 40 números de uma vez e consulta o que chama de Global Tracking No., Logistics Delivery No. e número de coleta da DHLink. Formatos encontrados em pacotes da DHLink, com dígitos inventados:

| Código | O que é |
| --- | --- |
| `FC4829105736491` | `FC` e 13 dígitos: código próprio da DHLink, o "DHLink No.". Em algumas linhas também é o código de rastreamento. |
| `XZG482910573WL` | `XZG`, 9 dígitos e `WL`: código de uma linha parceira, encontrado pela DHLink como número de rastreamento. |
| `YLUS4829105736` | `YLUS` e 10 dígitos: linha para os EUA entregue pela GOFO. |
| `QH482910573649FS` | `QH`, 12 dígitos e `FS`: outra linha para os EUA entregue pela GOFO. |
| `EYT4829105736HZ` | `EYT`, 10 dígitos e `HZ`: linha para os EUA entregue pela SwiftX. |
| `CYS48291057364` | `CYS` e 11 dígitos: linha para os EUA entregue pela [UniUni](carrier:uniuni). |
| `JXCYI4829105736YQ` | `JXC` seguido de `YI`, `ZO`, `US` ou `ZI`, 10 dígitos e `YQ`: formato de códigos da Juxi Express que o rastreador da DHLink também reconhece em parte dos envios para os EUA. O Peek consulta apenas a DHLink para esse padrão; outros códigos `JXC` vão para a Juxi Express. |
| `RR482910576CN` | Duas letras, 9 dígitos e `CN`: código do [China Post](carrier:china-post). Alguns pacotes da DHLink viajam pelo China Post com esse formato. |
| `GFUS48291057364918` | Não é da DHLink: é código da [GOFO Express](carrier:gofo), responsável pela etapa final de algumas linhas dos EUA. |

No resultado da consulta, a DHLink exibe o "DHLink No.", o "Tracking No." e, quando outra transportadora entrega, um "Last Leg No." para essa empresa. Se o Tracking No. for diferente do DHLink No., use o Tracking No.: buscando só pelo DHLink No., o pacote pode não aparecer. Sem `lang=en` no endereço, a página abre em chinês.

## Status do rastreamento DHLink explicados

O resultado traz o status (`In transit`, `Delivered`), os códigos de país de origem e destino, o código da linha, uma barra de progresso (`Original Leaving`, `Destination Arrived`, `Pending delivery` e `Delivered`) e cada evento com horário. Encomendas entregues também exibem "Signed for - Signed for time". Como as linhas vêm de vários fluxos, o mesmo evento costuma se repetir. Textos em inglês da DHLink, na ordem em que costumam aparecer:

| A DHLink mostra | O que significa para você |
| --- | --- |
| `Data Received; Data transferred and received by carrier` | Em uma linha para o sudeste europeu: a Pactic, plataforma na Hungria usada nessa rota, recebeu os dados do pacote. Antes da leitura no armazém da DHLink, o Peek mostra "Etiqueta criada"; se reaparecer após o voo, permanece "Em trânsito". |
| `Arrive at the collection warehouse` | O armazém de coleta da DHLink na China recebeu o pacote. |
| `The collection warehouse has completed sorting` | Triagem concluída nesse armazém. |
| `Package received & scanned at warehouse` | Registrado no armazém da DHLink. |
| `Parcel handed over to carrier,waiting for transportation` | No ponto de coleta, aguardando embarque. |
| `Package departed from the warehouse`, `The package has sent from the warehouse` | Saiu do armazém. |
| `Warehouse scanning bagging`, `Master airway bill number received`, `Leave the warehouse` | Em algumas linhas: o armazém na China ensacou o pacote para o voo, recebeu o conhecimento aéreo principal e despachou do armazém. O Peek mostra "Em trânsito". |
| `Customs export clearance completed`, `Package handed over to airline` | Liberado pela alfândega de exportação chinesa e entregue à companhia aérea. |
| `Flight departure`, `The flight has taken off` | Saiu de avião da China. |
| `Shipment Departed From Original Country at …` | O mesmo: partiu da China. |
| `Departed from airport of origin` | O voo partiu da China ("Em trânsito" no Peek). |
| `The flight has arrived and the board is being dismantled` | Pousou; os paletes do voo estão sendo desmontados. |
| `Dismantling the board` | O mesmo: palete em desmontagem no aeroporto de chegada ("Em trânsito" no Peek). |
| `Shipment has arriveved at destination Country at …` | No seu país. A grafia é da própria DHLink. |
| `Arrived to … hub`, `Linehaul transit; Parcel has entered the linehaul network.` | Após o voo, chegou a um hub parceiro na Europa (indicado por um código no lugar das reticências) e entrou no transporte rodoviário entre centros. Na linha para o sudeste europeu são eventos da Pactic no hub da Hungria (código HU). O Peek mostra "Em trânsito". |
| `Customs clearance completed`, `Customs import clearance completed, awaiting pickup by local carrier` | Liberado pela alfândega de importação; a transportadora do seu país fará a coleta em seguida. |
| `UPS/USPS/Fedex` | O repasse para a transportadora americana está a caminho. |
| `货物已交UPS/USPS/Fedex,The goods have been delivered to UPS/USPS/Fedex` | Entregue à transportadora nos EUA. Ainda não foi entregue a você. |
| `Package picked up by last-mile carrier` | A transportadora do seu país assumiu o pacote. |
| `Scheduled for Delivery` | Saiu para entrega. |
| `Shipment has delivered at …` | Entregue. |

Linhas para os EUA acrescentam eventos de hub como `Arrived at Origin Hub` e `Departed Destination Hub`; em uma delas, `Shipping Order Created.` marca o pedido na linha uma hora antes da entrada no armazém ("Etiqueta criada" no Peek). Uma unidade de última milha pode registrar `Parcel received by ABC01.` (com seu próprio código no lugar de ABC01) após retirar no armazém da DHLink e `Parcel departed from Facility.` minutos depois — ambos "Em trânsito". Após o repasse entram os eventos da transportadora local, como `Arrived at GOFO Delivery Station` da GOFO ou `No Access to Delivery Location` da USPS (tentativa sem sucesso). Os da UniUni incluem `Parcel received at UniUni sorting hub.`, `Parcel processed.` (minutos antes da rota) e `Parcel scanned and in transit.`, às vezes após cidade e estado, todos como "Em trânsito" no Peek. Nas linhas do China Post surgem também eventos dele, alguns só em chinês: `中国邮政 已收取邮件` (China Post recebeu o item), `送交出口海关` (encaminhado à alfândega de exportação) e `已妥投` após o código do país (entregue). Para números desconhecidos, a página diz `There is no track for this waybill number`.

## Quem entrega pacotes da DHLink

O site da DHLink é voltado para vendedores e não lista parceiras de última milha por país: é o rastreador que revela quem entrega. Em linhas para os EUA, um evento menciona repasse a "UPS/USPS/Fedex" sem dizer qual; as linhas seguintes são da transportadora real, como a [USPS](carrier:usps). Outras linhas americanas terminam com [GOFO Express](carrier:gofo) ou SwiftX, cujo número aparece como "Last Leg No." (o rastreador da GOFO também localiza pacotes do DHgate que ela entrega usando o código DHLink `FC` de 15 caracteres); na Suíça, esse número pode ser da DPD Switzerland. Encomendas via China Post mantêm o código terminado em `CN` até o fim: veja [China Post](carrier:china-post) para saber quem entrega no seu país.

Quando você souber a transportadora local e o número dela, o rastreamento dela traz os detalhes locais e valem as regras dela para tentativas, avisos e retirada: veja [entrega perdida](guide:missed-delivery). Desde 1º de novembro de 2026, a União Europeia cobra taxa de manuseio de € 2 por tipo de produto em compras online vindas de fora do bloco; o vendedor ou a transportadora paga e pode repassar a você na compra ou antes da entrega. Sobre impostos e taxas em compras da China, veja [encomendas paradas na alfândega](guide:customs).

## Pacote DHLink sem atualizar, perdido ou entregue sem chegar

**Não encontrado.** Confira o código no pedido do DHgate e tente o Tracking No. em vez do DHLink No. Um código de última milha não retorna nada na página da DHLink: consulte na transportadora local. Se você escolheu DHLink em "Trocar" para um número que ela ainda não conhece, o Peek mostra "Aguardando DHLink" e continua tentando. Se após dois dias a DHLink ainda não reconhecer e nenhum outro serviço responder, o Peek mostra "Depois de dois dias, a transportadora ainda não reconhece este código. Confira o código, escolha outra transportadora ou exclua a encomenda."

**Parado após o voo ou a alfândega.** Procure um "Last Leg No." e consulte na transportadora correspondente, cujo rastreamento pode estar mais adiantado que o da DHLink. Mais em [por que o rastreamento para de atualizar](guide:tracking-not-updating) e [como rastrear encomendas da China](guide:tracking-from-china).

**Entregue mas não chegou, perdido ou atrasado.** As regras de reembolso e indenização da DHLink são voltadas aos vendedores: o vendedor reclama junto à DHLink, e você trata com o vendedor ou com o DHgate. As regras excluem falhas causadas pelo comprador — como atraso por não retirar ou assinar, ou falha alfandegária por falta de retorno do comprador a tempo. Retire no prazo, responda rápido a pedidos da alfândega e avise o vendedor cedo, pois ele só pode reclamar em até 90 dias após registrar o envio. Veja [entregue, mas não recebido](guide:delivered-not-received) e [encomendas extraviadas](guide:lost-parcel).

## Contato e atendimento da DHLink

A página de contato da DHLink lista um e-mail de atendimento, dhlinkservice@dhlink.com, e um formulário da plataforma. O site conta ainda com o assistente de chat "AI 小飞". A página de contato está em chinês e não informa telefone. Sobre seu pedido, fale primeiro com o vendedor pelo DHgate; sobre problemas na entrega local, com a transportadora do seu país.

## Como rastrear um pacote da DHLink no Peek

O [Peek](/) não consegue cravar a DHLink apenas pelo formato do código: seus números `FC`, `XZG`, `YLUS`, `QH`, `EYT`, `CYS`, `JXCYI`, `JXCZO`, `JXCUS` e `JXCZI` são apenas indícios fracos. Cole o número mesmo assim: o Peek acompanha mais de 4.500 transportadoras. Ele mostra "Consultando serviços de rastreamento…" e depois "DHLink está com esta encomenda" quando a DHLink reconhece o número; caso contrário, escolha DHLink em "Trocar". Um código `JXCYI`, `JXCZO`, `JXCUS` ou `JXCZI` também pode ser da Juxi Express: o Peek consulta apenas a DHLink e, enquanto ninguém listar o código, mostra "Provavelmente DHLink"; se o vendedor enviou pela Juxi, selecione Juxi Express em "Trocar". Códigos do China Post (duas letras, 9 dígitos e `CN`) vão direto para o China Post.

O Peek lê o rastreador da DHLink, exibindo o status, o histórico com localidade de cada movimentação e o país de destino, listando cada evento uma única vez. Leituras na China usam o horário de Pequim, eventos da USPS o horário do respectivo estado americano e eventos da Pactic (na rota pela Hungria) o horário de Budapeste; os demais mantêm o horário informado pela DHLink. A DHLink não informa horário de entrega, peso nem nome do serviço, e o Peek omite códigos postais e referências de pedido. A única estimativa de entrega vem de um evento repassado da USPS ("Out for Delivery, Expected Delivery …") no horário do estado americano: enquanto for o mais recente, o Peek exibe a janela daquele dia quando o texto trouxer uma; se trouxer apenas horário limite, nada é acrescentado, pois "Saiu para entrega" já indica hoje. Quando o "Last Leg No." é um código GOFO (`GFUS` e 14 dígitos), SwiftX (`SWX` e 18 dígitos) ou DPD Switzerland (14 dígitos iniciados em `060` ou `061`), o Peek exibe essa transportadora e o código dela, consulta-a e migra para ela assim que surgir progresso mais recente. Outros códigos de última milha permanecem no histórico da DHLink.

Enquanto o rastreador da DHLink responder, o Peek verifica até a cada 10 minutos com o pacote aberto na tela ou com alertas ligados se a entrega for esperada hoje. A partir das 6h no relógio da transportadora no dia da saída para entrega, as verificações podem ocorrer a cada 2 minutos com o pacote aberto ou alertas ligados; se houver janela de entrega, o ritmo acelerado começa no máximo meia hora antes de ela abrir. Fora da tela e sem alertas, normalmente verifica de hora em hora. Quando apenas serviços de rastreamento respondem, verifica de hora em hora, a cada 3 horas após um dia sem movimento e até a cada 15 minutos no dia em que sai para entrega enquanto o pacote estiver aberto ou com alertas ligados. Quando passa a acompanhar na transportadora local, segue o ritmo dela. Você pode ativar alertas para "Cada movimentação", "Etapas importantes" ou "Só a entrega". Nas consultas diretas à transportadora, após dois dias sem novidade (12 horas enquanto ainda mostra "Etiqueta criada" ou "Recebida pela transportadora"), verifica de hora em hora. O Peek não pode falar com a DHLink, o DHgate nem o vendedor por você.

## Perguntas sobre o rastreamento DHLink

### O que é a DHLink?

É a plataforma de logística internacional do DHgate, operada pela 驼飞侠科技（北京）有限公司 em Pequim. Fundada em 2014, envia pedidos da China e de armazéns no exterior por linhas próprias e transportadoras parceiras.

### Quem entrega meu pacote da DHLink?

Uma transportadora do seu país. Em linhas para os EUA, a DHLink repassa pacotes para UPS, USPS ou FedEx, ou para GOFO, SwiftX ou UniUni; na Suíça, às vezes para a DPD. Procure o "Last Leg No." no resultado da DHLink.

### O que significa "The goods have been delivered to UPS/USPS/Fedex"?

Que a DHLink repassou o pacote à transportadora nos EUA. Ele ainda não foi entregue a você: os eventos dessa transportadora aparecem em seguida.

:::sources
- [DHLink: About us (关于我们)](https://www.dhlink.com/about/introduce) – fundada em 2014, plataforma de logística internacional do DHgate para o DHgate e outros clientes
- [DHLink: Home page (首页)](https://www.dhlink.com/) – envio direto da China, armazéns no exterior e link de rastreamento do DHgate
- [DHLink: Tracking (轨迹查询)](https://www.dhlink.com/trackQuery?lang=en) – 40 códigos, tipos aceitos, campos do resultado, DHLink No., Tracking No., Last Leg No., barra de progresso e "There is no track for this waybill number"
- [DHLink: Contact us (联系我们)](https://www.dhlink.com/about/connect) – e-mail de atendimento, formulário e chat
- [DHLink: Service agreement (服务协议)](https://www.dhlink.com/agreementPage/service) – operadora 驼飞侠科技（北京）有限公司
- [DHgate Seller Help: DHLink物流异常件赔付规则 (regras de indenização DHLink)](https://seller.dhgate.com/help/c7502/516002.html) – indenização para vendedores, tipos logísticos, exclusões por atraso ou falha alfandegária causados pelo comprador e prazo de 90 dias
- [Revenue: Union Handling Fee press release](https://www.revenue.ie/en/corporate/press-office/press-releases/2026/pr-092926-handling-fee.aspx) – € 2 por tipo de produto desde 1º de novembro de 2026 para compras de fora da UE, pago pelo vendedor ou pela entregadora, que podem repassar na compra ou antes da entrega
:::
