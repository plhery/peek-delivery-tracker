---
title: Rastreamento euShipments: status e transportadora local
description: Rastreie um pedido euShipments: de onde vem o código, o que significam New, Loaded hub ou On delivery, qual transportadora local entrega e contatos.
slug: eushipments-tracking
published: 2026-11-27
updated: 2026-11-27
---

A euShipments é uma empresa de logística para e-commerce sediada em Ruse, na Bulgária. Ela armazena, embala e despacha pedidos de lojas on-line para os países listados em "We deliver to" — os 27 da União Europeia e o Reino Unido — e repassa cada encomenda a uma transportadora local na etapa final. Rastreie seu código na página "Track my order", em [eushipments.com/tracking](https://eushipments.com/tracking), ou cole-o na caixa acima. O código é aquele enviado pela loja.

## Código de rastreamento euShipments: como identificar

A euShipments não publica um formato fixo de código. Ela informa que, assim que o pedido é processado, você recebe o "tracking code, AWB number or tracking ID" para digitar no campo "Enter your tracking number to track packages." O rastreador mostra o pedido sob o número que a loja enviou, que às vezes já é o próprio código da transportadora local. Exemplos com dígitos fictícios:

| Código | O que é |
| --- | --- |
| `74829103` | Oito dígitos: o único formato que o Peek já viu no rastreador da euShipments, em um pedido entregue na Bulgária pela transportadora local sob o mesmo número. |
| O número após "Tracking Number:" | O código próprio da transportadora local, exibido no resultado da euShipments assim que ela recebe a encomenda e aceito no rastreador dela. |

Oito dígitos também coincidem com [Mondial Relay](carrier:mondial-relay), [Colisweb](carrier:colisweb) e [Heppner](carrier:heppner); por isso o número sozinho não identifica a euShipments: confira qual empresa aparece no e-mail da loja. Um link para a página da euShipments não abre o pedido diretamente, pois o site só aceita o número pelo formulário. Sem código algum? Veja [como encontrar um código de rastreamento](guide:find-tracking-number), em inglês.

## Significado dos status de rastreamento da euShipments

O resultado traz "My order N" e o seu código, seguidos por uma tabela de três colunas: "Date/Time", "Tracking Status" e "Tracking Information". O status usa sempre os termos em inglês da euShipments, seja qual for a transportadora que entrega. "Tracking Information" mostra ao lado o texto da transportadora assim que ela assume a encomenda. Cada linha indica data e minuto, como "10 August, 2026 15:39", sem fuso horário.

| A euShipments mostra | O que significa para você |
| --- | --- |
| `New` | A loja registrou o pedido na euShipments. Ele pode ainda não ter sido embalado. |
| `Warehouse` | O pedido está no centro de distribuição da euShipments, cujo nome aparece depois do termo. |
| `Loaded hub` | Carregado em um dos hubs da euShipments para seguir ao próximo. O nome do hub aparece em seguida com a sigla do país entre parênteses, como (HR). |
| `Unloaded hub` | Descarregado em um hub, identificado da mesma forma. |
| `Picked up` | A transportadora local coletou a encomenda. |
| `In transit` | Em trânsito pela rede da transportadora local. |
| `On delivery` | Saiu para entrega com o entregador. |
| `Delivered` | Entregue. Último status do pedido. |

`Loaded hub` e `Unloaded hub` podem se repetir várias vezes, uma para cada hub no trajeto. Depois de `Picked up`, três linhas surgem abaixo da tabela: "Courier Name:", "Tracking Number:" e "Tracking Link:", indicando quem faz a entrega, o código local e o link do rastreador dela.

O Peek nunca viu a euShipments usar um status próprio para entrega não realizada, encomenda pronta para retirada ou devolução. Nesses casos, leia o texto da transportadora em "Tracking Information" ou abra o link dela.

## Quem entrega uma encomenda euShipments no seu país

A euShipments delega a última milha a transportadoras locais. Ela movimenta os pacotes por transporte rodoviário a partir de seus centros de atendimento e cross-docking (Sófia e Ruse na Bulgária, Oradea na Romênia, Zagreb na Croácia, Liubliana na Eslovênia, Senec na Eslováquia e Budapeste na Hungria) até o depósito de uma transportadora local. Seu FAQ informa parceria com mais de 60 transportadoras locais, ao menos duas por país europeu, cobrindo 28 países em "We deliver to": os 27 da UE e o Reino Unido.

A página inicial exibe logotipos de parceiras em "Key eCommerce partnerships", incluindo [Speedy](carrier:speedy), Sameday, [FAN Courier](carrier:fan-courier), Express One, GLS, DPD, [BOX NOW](carrier:box-now), [Pošta Slovenije](carrier:posta-slovenije), [Slovenská pošta](carrier:slovenska-posta), [PPL](carrier:ppl), [ACS](carrier:acs-courier) e [Geniki Taxydromiki](carrier:geniki-taxydromiki), sem especificar qual entrega onde. O histórico da empresa cita ainda uma parceria estratégica com a Austrian Post em 2026, sem detalhar o alcance. A empresa que entrega a você depende do país e do método escolhido no checkout da loja — "Courier Name:" revela qual é.

**Onde pode ser entregue.** Na Bulgária, a euShipments entrega em endereço residencial ou comercial, ou fora de casa: agência da transportadora, ponto de retirada (PUDO) ou armário. As páginas de Croácia e Romênia listam opções semelhantes. A rede soma mais de 150.000 pontos fora de casa e envia notificação com instruções assim que a encomenda fica pronta para retirada.

**Tentativas, guarda e reentrega.** Valem as regras da transportadora local: a euShipments não publica regras próprias. Use "Tracking Link:" e "Tracking Number:" para consultar a página dela e peça nova tentativa ou mudança de endereço diretamente a essa transportadora.

**Pagamento na entrega.** A euShipments oferece a opção em 17 países. A transportadora local recebe o valor e repassa à loja.

## Encomenda euShipments parada, extraviada ou devolvida

**Parada em `New`.** A loja registrou o pedido na euShipments, mas ainda não há registro em armazém ou hub. Pergunte à loja quando será despachado.

**Parada entre hubs.** Após `Loaded hub`, a encomenda segue na estrada para o próximo hub, e a nova leitura costuma sair ao descarregar lá. Se passar do prazo informado pela loja, fale com ela. Veja [por que o rastreamento para de atualizar](guide:tracking-not-updating), em inglês.

**Parada após `Picked up`.** Já está com a transportadora local. Abra "Tracking Link:" e consulte o rastreador dela com o código local.

**Entregue mas não recebida.** A entrega foi feita pela transportadora local: comece por ela e depois avise a loja. Veja [entregue mas não recebida](guide:delivered-not-received), em inglês.

**Devolvida.** A página de devoluções da euShipments classifica como não entregue a encomenda que falha por dados incorretos do cliente ou falha de comunicação; ela retorna ao armazém da euShipments ou à loja, não a você. Peça à loja reenvio ou reembolso. Mais em [devolvida ao remetente](guide:returned-to-sender), em inglês.

**Devolução por sua conta.** A euShipments oferece três modelos às lojas: solicitação de devolução no site da loja com agendamento de dia, horário e local para coleta; coleta agendada pela loja; ou envio feito por você, por qualquer transportadora, ao endereço local de devolução indicado pela loja (um dos armazéns da euShipments). As condições da loja dizem qual se aplica. Veja [rastreamento de devolução](guide:returns-tracking), em inglês.

**Extraviada ou danificada.** A euShipments não publica fluxo de indenização para destinatários, pois o contrato é com a loja. Na União Europeia, a loja responde por danos do despacho até a entrega e deve entregar em até 30 dias, salvo outro prazo combinado; se não entregar, dê um prazo extra razoável e, persistindo o atraso, cancele e peça reembolso. Veja [encomenda extraviada](guide:lost-parcel), em inglês, e [encomenda danificada](guide:damaged-parcel), em inglês.

## Telefone e atendimento ao cliente da euShipments

A euShipments informa:

- **Telefone:** +359 700 70 755 (número da Bulgária), listado nas páginas de contato, FAQ e rastreamento, sem horário divulgado.
- **E-mail:** office@eushipments.com.
- **Formulário de contato:** questionário comercial para lojas pedirem orçamento (volume mensal, mercados); não se destina a destinatários.

O FAQ voltado às lojas afirma oferecer atendimento por telefone, e-mail e chat aos compradores "quando solicitado". Por isso, comece pela loja que contratou o envio; para questões da entrega em si, a transportadora indicada em "Courier Name:" costuma resolver mais rápido.

## Como rastrear euShipments no Peek

O [Peek](/) não reconhece códigos da euShipments pelo formato: não há regra própria para ela, e um código de 8 dígitos tem correspondência fraca com outras empresas. Cole-o mesmo assim — o Peek acompanha mais de 4.500 transportadoras — e depois toque em "Trocar" e escolha "euShipments", ou use "Trocar transportadora" em uma encomenda já adicionada.

O Peek lê o rastreador da euShipments e mostra a etapa, o histórico e, nas linhas de armazém e hub, o local (com país nos hubs). Mantém os status em inglês da euShipments e omite o texto da transportadora vindo de "Tracking Information". Quando "Tracking Link:" aponta para uma transportadora que o Peek consegue acompanhar — como [Econt](carrier:econt), Sameday, FAN Courier, [Packeta](carrier:packeta), Slovenská pošta ou Geniki Taxydromiki — trazendo o número dela no link, o Peek exibe "Entrega por" essa transportadora e o código dela quando for diferente do seu. Enquanto essa transportadora tiver apenas o pré-aviso, sem leitura própria, aparece "Anunciada a …, ainda não recebida". O Peek a consulta e passa a seguir por ela assim que houver progresso datado pelo menos tão recente quanto a última linha da euShipments, preservando as linhas anteriores; caso contrário, mantém o histórico da euShipments.

Como o resultado da euShipments não traz data prevista nem comprovante de entrega, o Peek não exibe nenhum dos dois. Com o cartão aberto no Peek, ou no dia da entrega com notificações ativas, o Peek consulta a euShipments diretamente a cada 10 minutos no máximo, ou a cada 2 minutos a partir das 6h no relógio da última leitura no dia em que sai para entrega, enquanto o rastreador responder. Após dois dias sem novidade (12 horas em "Etiqueta criada" ou "Recebida pela transportadora"), verifica de hora em hora; após sete dias em "Etiqueta criada", a cada 6 horas. Depois de quatro dias sem leitura em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito", mostra "Sem atualizações desde …", a nota "É comum uma encomenda ficar sem atualizações em voos longos, na alfândega ou no fim de semana. O Peek continua consultando euShipments." e o link "Pedir ajuda a euShipments". Você escolhe alertas para "Cada movimentação", "Etapas importantes" ou "Só a entrega". O Peek não pode contatar a euShipments, a loja ou a transportadora por você.

## Perguntas frequentes sobre o rastreamento euShipments

### A euShipments é confiável?

Sim. É uma empresa de logística em Ruse, na Bulgária, fundada em 2012 como InOut Trade e renomeada para euShipments.com em 2019. Faz envios para lojas on-line, incluindo vendedores na Temu e na eMAG, via integrações próprias.

### Qual transportadora entrega minha encomenda euShipments?

Uma transportadora local do seu país. Assim que ela recebe o pacote, o resultado da euShipments mostra "Courier Name:", "Tracking Number:" e "Tracking Link:" logo abaixo da tabela.

### Quanto tempo a euShipments demora para entregar?

Depende de onde fica o estoque da loja. Dos centros de Sófia e Ruse, a euShipments informa entrega no dia seguinte para Bulgária e Romênia, e de 2 a 4 dias para Croácia, Eslovênia, Eslováquia e Tchéquia. De Zagreb, Liubliana ou Senec, informa 1 dia útil no próprio país. Para o seu pedido, acompanhe o prazo da loja. Mais em [prazos de entrega](guide:delivery-times), em inglês.

### O que significa Loaded hub na euShipments?

Significa que a encomenda foi carregada em um hub da euShipments para o próximo trecho, identificado com a sigla do país entre parênteses. `Unloaded hub` marca a chegada e descarga no hub seguinte.

### Para quais países a euShipments entrega?

Sua lista "We deliver to" reúne 28 países: os 27 da União Europeia e o Reino Unido. Em cada um deles, uma transportadora local realiza a entrega final.

:::sources
- [euShipments: Track Your Order](https://eushipments.com/tracking) – tracking form and its wording, phone, email
- [euShipments: Parcel Tracking For End Customers](https://eushipments.com/ecommerce-delivery/new-parcel-tracking/parcel-tracking-for-end-customers) – tracking code, AWB number or tracking ID, 60+ last-mile partners
- [euShipments: We deliver to](https://eushipments.com/we-deliver-to) – the 28 countries listed: the 27 of the EU and the UK
- [euShipments: FAQ](https://eushipments.com/faq) – more than 60 couriers, at least two per country, support for end customers when requested, phone, email
- [euShipments: Contact Us](https://eushipments.com/contact) – phone, email, Ruse, quote form for shops
- [euShipments: About us](https://eushipments.com/about-us) – InOut Trade, 2012, 2019 rename, Austrian Post partnership, 17 cash-on-delivery countries
- [euShipments: Home](https://eushipments.com/) – couriers' logos among the Key eCommerce partnerships
- [euShipments: eCommerce Deliveries To Bulgaria](https://eushipments.com/countries/bulgaria) – Sofia and Ruse, line haul to the courier's depot, address and out-of-home delivery, transit times, cash on delivery
- [euShipments: International eCommerce Delivery To Romania](https://eushipments.com/countries/romania) – Oradea, Budapest, delivery options
- [euShipments: Domestic and International eCommerce Delivery To Croatia](https://eushipments.com/countries/croatia) – Zagreb and the other centres, next-day delivery, lockers, pickup points, courier offices
- [euShipments: International Parcel Delivery To Slovenia](https://eushipments.com/countries/slovenia) – Ljubljana, 1 business day
- [euShipments: International Delivery To Slovakia](https://eushipments.com/countries/slovakia) – Senec, 1 business day
- [euShipments: Out-of-home Delivery](https://eushipments.com/ecommerce-delivery/out-of-home-delivery) – 150,000 locations, collection notifications
- [euShipments: Returns Management](https://eushipments.com/returns-management) – undelivered shipments, three ways to return
- [euShipments: Temu Integration](https://eushipments.com/integration/ecommerce-platform-integration/temu) – Temu sellers
- [euShipments: eMAG Integration](https://eushipments.com/integration/ecommerce-platform-integration/emag-integration) – eMAG sellers
- [Your Europe: Shipping and delivery](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_en.htm) – 30 days, trader responsible until you receive the goods, reminder and refund
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – result page labels, statuses, numbers, what Peek reads and shows
:::
