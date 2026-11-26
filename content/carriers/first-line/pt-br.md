---
title: Rastreamento FIRST LINE: códigos 1ST, status e entrega
description: Rastreie uma encomenda FIRST LINE (壹号专线) da China: formato do código 1ST, significado de cada status, quem entrega nos EUA e quem procurar se travar.
slug: rastreamento-first-line
published: 2026-11-26
updated: 2026-11-26
---

A FIRST LINE (壹号专线, Shenzhen First Line Supply Chain Co., Ltd.) é uma transitária de Shenzhen que envia encomendas da China para lojas virtuais, sobretudo para os Estados Unidos, e as repassa a uma transportadora local para a entrega. Ela faz parte do grupo 1ST (一代国际集团), cujas transitárias emitem conhecimentos de transporte iniciados por 1ST. Rastreie sua encomenda na caixa de busca da página inicial de firstline56.com, que abre a página 追踪单号 (rastrear um número) da FIRST LINE, ou no campo acima. A loja informa o código junto aos dados de envio do seu pedido.

## Código de rastreamento FIRST LINE: como ele é

A FIRST LINE não publica um formato de código. Seus conhecimentos de transporte, assim como os do restante do grupo 1ST, têm 1ST seguido de 11 dígitos. Com dígitos inventados:

| Código | O que é |
| --- | --- |
| `1ST48291057364` | 1ST e 11 dígitos: conhecimento de transporte do grupo 1ST, rastreado no site da FIRST LINE. |
| `9400 1092 0556 8473 9261 56` | 22 dígitos começando por `94`: etiqueta da [USPS](carrier:usps), usada na entrega nos EUA. |

**Mesmo prefixo, outras transitárias.** As quatro empresas do grupo — 1st Agent International (一代国际), FIRST LINE, Ferry-Boat (摆渡一下) e Universal Best Union (万国优联) — emitem o mesmo tipo de código 1ST. Um código 1ST que o rastreador da FIRST LINE não reconhece pode pertencer a uma das outras três.

**Onde encontrar.** Nos detalhes de envio da loja, que podem citar USPS, China Post ou FIRST LINE como transportadora: a FIRST LINE permite que os vendedores informem qualquer uma delas no marketplace. O grupo informa aos vendedores que o número da última milha, como o da USPS, sai de 6 a 9 dias depois que a encomenda chega ao armazém, então a loja pode fornecer esse número no lugar. Saiba mais em [como encontrar seu código de rastreamento](guide:find-tracking-number).

## Status de rastreamento da FIRST LINE explicados

O rastreador da FIRST LINE está em chinês, mas as movimentações aparecem em inglês. As próprias linhas da FIRST LINE, na ordem em que costumam aparecer:

| A FIRST LINE diz | O que significa para você |
| --- | --- |
| `Forecast has been received` | O vendedor enviou os dados do pedido. A FIRST LINE ainda não recebeu a encomenda. `Parcel information received` significa o mesmo. |
| `Order received` | A empresa responsável pela entrega, como a UniUni, recebeu os dados do pedido, mas ainda não tem o pacote. |
| `Barcode scanning` | Primeira leitura no armazém da FIRST LINE em Shenzhen. |
| `Shipment arrived` | Também é a primeira leitura em Shenzhen, e não a chegada ao seu país. |
| `Packaged` | Embalada para a viagem ao exterior. |
| `Leaving Scan` | Saiu do armazém. |
| `Export customs declaration completed` | Declaração aduaneira de exportação na China continental, feita em Shenzhen antes do voo. A encomenda não está retida na alfândega. |
| `Depart from facility to service provider` | A caminho da FIRST LINE para a empresa que assume o trecho seguinte. |
| `Hand over to airline` | Entregue à companhia aérea no aeroporto de Hong Kong, após a declaração de exportação. |
| `Next stop destination airport` | No voo para o aeroporto de destino. O local ainda aparece como aeroporto de Hong Kong. |
| `Arrived at destination airport` | O voo pousou. O desembaraço de importação vem a seguir. |
| `The package is being submitted for customs clearance` | Desembaraço de importação na chegada, depois do voo. O Peek mostra "Na alfândega". |
| `The package has been cleared through customs` | A alfândega liberou a encomenda. O Peek volta a mostrar "Em trânsito". |
| `Customs broker picked it up` | O despachante aduaneiro coletou o pacote após a liberação para repassá-lo à entrega. Ele não está mais retido na alfândega. |
| `The package has been transferred to the service provider` | Repassada à empresa que faz a entrega, depois do despachante. |

Nos EUA, as movimentações da empresa entregadora vêm a seguir com o texto dela, até a entrega: da USPS, da [GOFO](carrier:gofo) ou da [UniUni](carrier:uniuni) nos históricos observados, explicadas nas páginas de cada uma. A saída para entrega da GOFO aparece como `Out for Delivery`, exibida no Peek como "Saiu para entrega"; a entrega aparece como `Delivered,`, seguida do local onde o pacote foi deixado (como `Door/Yard`) e de `For delivery issues & tracking support, contact GOFO at` com um contato, que o Peek mostra como "Entregue". O rastreador da FIRST LINE nunca informa qual empresa faz a entrega, então o texto das movimentações é a pista.

Cada horário usa o relógio local do lugar que registrou a leitura — Shenzhen ou Hong Kong e, depois, o local nos EUA — sem fuso horário, de modo que uma leitura nos EUA pode mostrar uma hora anterior à última registrada na China. Quando o rastreador não reconhece o código, exibe `未找到此单号信息` ("nenhuma informação encontrada para este número").

## Quem entrega encomendas da FIRST LINE

A FIRST LINE leva a encomenda para fora da China, providencia o desembaraço aduaneiro na chegada e a entrega a uma transportadora local:

- **Estados Unidos, por via aérea:** entrega pela USPS ou por uma transportadora comercial local; a página da FIRST LINE cita USPS e [DHL eCommerce](carrier:dhl-ecommerce). A FIRST LINE informa prazo de 7 a 16 dias porta a porta.
- **Estados Unidos, por via marítima:** nos navios rápidos da Matson e depois [FedEx](carrier:fedex) ou USPS, em 19 a 31 dias porta a porta.
- **Canadá:** voo para Vancouver ou Toronto e entrega pela [Canada Post](carrier:canada-post), que segundo a FIRST LINE leva de 2 a 7 dias após a liberação.
- **Europa, por via aérea:** voo para Praga e desembaraço na Tchéquia, seguido de entrega pela [UPS](carrier:ups) em residências, empresas e armazéns da Amazon em mais de 20 países, entre eles Alemanha, França, Itália, Espanha e Reino Unido. Cada volume é cobrado por no mínimo 12 kg, e os impostos já entram no valor pago pelo vendedor.

As rotas para os EUA entregam apenas nos 48 estados contíguos, sem atender Alasca, Havaí, Porto Rico, Ilhas Virgens Americanas, Guam, caixas postais, endereços militares nem endereços da Amazon. A FIRST LINE vende suas rotas de encomendas para os EUA e o Canadá na modalidade DDP (delivered duty paid): o vendedor paga os encargos de importação. Veja mais em [encomendas retidas na alfândega](guide:customs).

A FIRST LINE não publica regras para tentativas de entrega, avisos de ausência ou pontos de retirada: valem as regras da transportadora entregadora. O rastreador dela não mostra o número da última milha, então, para reagendar uma entrega, peça ao vendedor o código da própria transportadora local.

## Encomenda da FIRST LINE parada ou sem chegar

**Apenas `Forecast has been received`.** A FIRST LINE ainda não registrou o pacote. O grupo informa aos vendedores que encomendas entregues antes das 17h aparecem online no mesmo dia. Se nada mudar por vários dias, pergunte ao vendedor se o envio já foi feito.

**Sem novidades depois das linhas de exportação.** O voo e o desembaraço de importação vêm em seguida, e o histórico pode ficar parado por vários dias. Se continuar sem atualização muito além dos prazos acima, fale com o vendedor. Saiba mais em [por que o rastreamento para de atualizar](guide:tracking-not-updating).

**Marcada como entregue, mas não chegou.** Quando a transportadora entregadora registra entrega sem que você tenha recebido o pacote, os termos da FIRST LINE para EUA e Canadá dizem que ela ajuda a apurar o caso, mas não paga indenização. Comece pela transportadora que fez a entrega e pelo vendedor: veja [encomenda marcada como entregue mas não recebida](guide:delivered-not-received).

**Extraviada ou danificada.** As reclamações cabem ao vendedor (em até 60 dias do despacho nas rotas para EUA e Canadá), e a FIRST LINE não indeniza danos em trânsito nem atrasos. Peça reembolso ou reenvio ao vendedor ou ao marketplace. Em caso de dano, o FAQ do grupo pede que o destinatário registre a ocorrência localmente primeiro e guarde o protocolo e fotos da embalagem. Veja [encomendas extraviadas](guide:lost-parcel).

**Devolvida.** Nas rotas para os EUA, um pacote que não pode ser entregue não volta para a China: vai para o armazém da FIRST LINE no exterior e, se o vendedor não pagar por um novo envio em até 14 dias, é destruído. Se o rastreamento mostrar devolução, fale com o vendedor imediatamente. Mais detalhes em [encomenda devolvida ao remetente](guide:returned-to-sender).

## Contato e atendimento ao cliente da FIRST LINE

A página de contato da FIRST LINE, em chinês, lista um centro de atendimento ao cliente (客维中心) e o endereço do escritório: 3º andar, Edifício 36, Zona 5, Parque Industrial Cuigang, Huaide, Fuyong, Distrito de Bao'an, Shenzhen, China. Não há horários nem idiomas informados. O site do grupo 1ST tem um formulário de reclamações (投诉建议) que pede número de telefone, e-mail e mensagem.

Esses canais atendem os vendedores, que são os clientes da FIRST LINE; ela não publica suporte para destinatários. Fale com o vendedor sobre o pedido e com a transportadora entregadora sobre a entrega.

## Acompanhe uma encomenda FIRST LINE com o Peek

O [Peek](/) identifica a FIRST LINE imediatamente para um código 1ST seguido de 11 dígitos, em maiúsculas ou minúsculas, com ou sem espaços. O Peek não lê links da FIRST LINE, então cole o número em si.

O Peek consulta o rastreador da FIRST LINE e mostra o status, o histórico (até 100 movimentações), o local de cada leitura sem o código postal e o horário lido no relógio daquele local. As linhas aparecem como a FIRST LINE as escreve, incluindo movimentações repassadas de USPS, GOFO ou UniUni, exceto um `Delivered` exato, exibido nas palavras do Peek. O rastreador não informa país de destino, empresa entregadora, código da última milha nem comprovante de entrega, então o Peek não mostra esses dados. A única estimativa de entrega possível é uma leitura repassada da USPS do tipo "Out for Delivery, Expected Delivery …": enquanto essa leitura for a mais recente, o Peek exibe a janela daquele dia quando ela vier informada; uma leitura apenas com horário-limite não adiciona estimativa, pois "Saiu para entrega" já indica hoje. O Peek não troca automaticamente para USPS, GOFO ou UniUni: se o vendedor passar o código da última milha, adicione-o como uma encomenda à parte.

Enquanto a encomenda estiver aberta na tela, ou no dia da entrega com as notificações ativadas, o Peek consulta a cada 10 minutos no máximo, ou a cada 2 minutos a partir das 6h no horário local no dia em que ela sai para entrega, enquanto o próprio rastreador da FIRST LINE responder (apenas por serviços de rastreamento: a cada hora, a cada 3 horas após um dia sem novidades e até a cada 15 minutos no dia da entrega enquanto a encomenda estiver aberta ou com notificações ativadas); fora isso, a cada hora. Ele pode avisar você sobre "Cada movimentação", "Etapas importantes" ou "Só a entrega". Após dois dias sem novidades (12 horas enquanto a encomenda ainda mostrar "Etiqueta criada" ou "Recebida pela transportadora"), o Peek passa a consultar a cada hora. O Peek não pode entrar em contato com a FIRST LINE nem com o vendedor por você.

## Perguntas sobre o rastreamento da FIRST LINE

### O que é um código de rastreamento 1ST?

É um número de conhecimento de transporte das transitárias do grupo 1ST, entre elas a FIRST LINE: 1ST seguido de 11 dígitos. Rastreie em firstline56.com ou no Peek.

### Quem entrega encomendas da FIRST LINE nos EUA?

Uma transportadora dos EUA: as páginas da FIRST LINE citam USPS, DHL eCommerce e FedEx, e nos históricos observados a entrega foi feita por USPS, GOFO ou UniUni. O rastreador da FIRST LINE repassa as movimentações delas sem informar o nome da empresa.

### Por que o rastreador da FIRST LINE não encontra meu código 1ST?

O código pode ter sido digitado errado, ainda não ter sido registrado ou pertencer a outra transitária do grupo 1ST. Confira os caracteres e pergunte ao vendedor qual empresa está transportando a encomenda.

### Quanto tempo a FIRST LINE leva para entregar nos EUA?

A FIRST LINE informa aos vendedores de 7 a 16 dias porta a porta por via aérea e de 19 a 31 dias por via marítima. São prazos comerciais de referência, não uma data garantida para o seu pedido.

:::sources
- [FIRST LINE: 壹号专线 (home)](http://www.firstline56.com/) – caixa de rastreamento, site em chinês, endereço no rodapé
- [FIRST LINE: 追踪单号 (tracking)](http://www.firstline56.com/track.php) – rastreador aberto pela caixa da página inicial
- [FIRST LINE: 公司简介 (about)](http://www.firstline56.com/aboutus.php) – nome da empresa, transitária de Shenzhen para vendedores internacionais, rotas EUA e Canadá
- [FIRST LINE: 联系我们 (contact)](http://www.firstline56.com/contact.php) – centro de atendimento ao cliente, endereço do escritório
- [FIRST LINE: 美国小包 (US parcel line)](http://www.firstline56.com/business.php?id=4) – rota, transportadoras entregadoras, nomes para marketplaces, prazos e área de entrega, DDP, devoluções, reclamações, entregue mas não recebida
- [FIRST LINE: 美国海运小包 (US sea parcel line)](http://www.firstline56.com/business.php?id=5) – Matson, FedEx e USPS, 19 a 31 dias
- [FIRST LINE: 加拿大小包 (Canada parcel line)](http://www.firstline56.com/business.php?id=6) – Vancouver ou Toronto, Canada Post, prazo de entrega, DDP
- [FIRST LINE: 欧洲空派 (Europe air line)](http://www.firstline56.com/yhzx.php?id=4) – Praga, UPS, países, 12 kg por volume, impostos incluídos
- [1ST: Business](https://www.1st56.com/group/groupbusiness/en/) – as quatro empresas do grupo, nome em inglês da FIRST LINE
- [一代国际集团: 关于我们 (about)](https://www.1st56.com/group/aboutus/) – empresas do grupo, formulário de reclamações
- [一代国际集团: 常见问题 (FAQ)](https://www.1st56.com/group/commonproblems/) – EUA como mercado principal, leituras no mesmo dia, código da última milha após 6 a 9 dias, reclamações, relatos de dano, área de entrega nos EUA (páginas 1 a 4)
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formato do código, conhecimentos compartilhados do grupo, status, entregadoras observadas, horários locais, o que o Peek exibe
:::
