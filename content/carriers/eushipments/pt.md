---
title: Seguimento euShipments: estados e estafeta local
description: Segue uma encomenda euShipments: origem do código, o que significam New, Loaded hub ou On delivery, estafeta local e contacto.
slug: seguimento-eushipments
published: 2026-11-22
updated: 2026-11-22
---

A euShipments é uma empresa de logística de comércio eletrónico sediada em Ruse, na Bulgária. Armazena, embala e expede pedidos de lojas online para os 28 países da sua lista «We deliver to» — os 27 da UE e o Reino Unido — entregando cada encomenda a uma transportadora local para a última milha. Podes acompanhar o número na página «Track my order», [eushipments.com/tracking](https://eushipments.com/tracking), ou colá-lo acima. O número é o que a loja te enviou.

## Número de seguimento euShipments

A euShipments não publica um formato fixo. Refere que, após o processamento do pedido, recebes o teu «tracking code, AWB number or tracking ID» para introduzir onde o formulário indica «Enter your tracking number to track packages.» O seguimento apresenta a encomenda sob o código enviado pela loja, que pode ser o número da transportadora final (com dígitos inventados):

| Número | O que é |
| --- | --- |
| `74829103` | Oito dígitos: o único formato observado pelo Peek no portal da euShipments, numa entrega na Bulgária entregue sob o mesmo código pela transportadora local. |
| O número após «Tracking Number:» | O número próprio da transportadora local, mostrado no resultado da euShipments assim que ela recebe a encomenda. O seguimento dessa transportadora aceita-o diretamente. |

Oito dígitos também correspondem à [Mondial Relay](carrier:mondial-relay), Colisweb e [Heppner](carrier:heppner), pelo que o número isolado não prova ser euShipments: confirma qual a empresa indicada no e-mail da loja. Uma ligação para a página de seguimento da euShipments não abre a encomenda, pois o número só entra pelo formulário. Não tens código? Vê [como encontrar um número de seguimento](guide:find-tracking-number).

## Estados de seguimento euShipments explicados

O resultado apresenta «My order N» e o teu número, seguido de uma tabela de três colunas: «Date/Time», «Tracking Status» e «Tracking Information». O estado usa os termos ingleses da euShipments, iguais qualquer que seja a transportadora final. «Tracking Information» mostra ao lado os textos da transportadora assim que esta tem a encomenda. Cada linha indica data e hora ao minuto, como «10 August, 2026 15:39», sem fuso horário.

| A euShipments indica | O que significa para ti |
| --- | --- |
| `New` | A loja registou o pedido na euShipments. Pode ainda não estar embalado. |
| `Warehouse` | A encomenda encontra-se no armazém logístico da euShipments indicado a seguir. |
| `Loaded hub` | Carregada num hub da euShipments rumo ao seguinte. Segue-se o nome do hub com o país entre parênteses, como (HR). |
| `Unloaded hub` | Descarregada num hub, indicada da mesma forma. |
| `Picked up` | A transportadora local recolheu a encomenda. |
| `In transit` | Em circulação na rede da transportadora. |
| `On delivery` | Em distribuição com o estafeta. |
| `Delivered` | Entregue. É o estado final de um pedido. |

`Loaded hub` e `Unloaded hub` podem aparecer várias vezes, em cada hub intermédio. Após `Picked up`, surgem três linhas por baixo da tabela: «Courier Name:», «Tracking Number:» e «Tracking Link:», com a transportadora que entrega, o respetivo número e a ligação para o seu seguimento.

O Peek não observou estados próprios da euShipments para entrega falhada, ponto de levantamento ou devolução. Nesses casos, lê o texto da transportadora em «Tracking Information» ou abre a respetiva ligação.

## Quem entrega uma encomenda euShipments no teu país

A euShipments deixa a última milha a cargo de transportadoras locais. Transporta as encomendas em linha regular desde os seus centros de fulfillment e cross-docking (Sófia e Ruse na Bulgária, Oradea na Roménia, Zagreb na Croácia, Liubliana na Eslovénia, Senec na Eslováquia e Budapeste na Hungria) até ao depósito de uma transportadora local. As FAQ indicam mais de 60 transportadoras locais, pelo menos duas em cada país europeu, e a lista «We deliver to» inclui 28 países: os 27 da UE e o Reino Unido.

A página inicial exibe logótipos de parceiros principais («Key eCommerce partnerships»), incluindo Speedy, Sameday, [FAN Courier](carrier:fan-courier), Express One, GLS, DPD, BOX NOW, Pošta Slovenije, [Slovenská pošta](carrier:slovenska-posta), PPL, ACS e [Geniki Taxydromiki](carrier:geniki-taxydromiki), sem especificar qual entrega em cada país. A história da empresa refere uma parceria estratégica com a Austrian Post em 2026 sem detalhar o âmbito. A transportadora que faz a entrega depende do país e do método escolhido na compra: «Courier Name:» indica-a.

**Onde pode ser entregue.** Na Bulgária, a euShipments entrega em morada residencial ou comercial, ou fora de casa: balcão da transportadora, ponto de levantamento (PUDO) ou cacifo. As páginas para a Croácia e a Roménia apresentam opções semelhantes. A rede soma mais de 150 000 locais fora de casa e a empresa indica que, quando a encomenda fica pronta para levantamento, recebes uma notificação com instruções.

**Tentativas, guarda e nova entrega.** Aplicam-se as regras da transportadora local: a euShipments não publica regras próprias. Usa «Tracking Link:» e «Tracking Number:» para consultar a página da transportadora e pedir nova tentativa ou alteração de morada.

**Pagamento contra entrega.** A euShipments disponibiliza envio contra reembolso em vários países europeus: a transportadora local cobra o valor e transfere-o para a loja.

## Encomenda euShipments parada, perdida ou devolvida

**Parada em `New`.** A loja registou o pedido na euShipments, mas ainda não há registo de armazém ou hub. Pergunta à loja quando envia.

**Parada entre hubs.** Após `Loaded hub`, a encomenda segue por estrada até ao hub seguinte e a linha seguinte surge geralmente ao descarregar. Se nada mudar além do prazo dado pela loja, fala com a loja. Vê [porque o seguimento para de atualizar](guide:tracking-not-updating).

**Parada após `Picked up`.** Já está com a transportadora local. Abre «Tracking Link:» e consulta o seguimento com o número dela.

**Entregue, mas não chegou.** Foi a transportadora local quem entregou: começa por ela e avisa depois a loja. Vê [entregue mas não recebida](guide:delivered-not-received).

**Devolvida ao remetente.** A página de devoluções da euShipments trata como envio não entregue qualquer encomenda que não possa ser entregue por dados incorretos ou falhas de contacto; regressa ao armazém da euShipments ou à loja, não a ti. Pede nova entrega ou reembolso à loja. Mais em [devolvida ao remetente](guide:returned-to-sender).

**Devolver por conta própria.** A euShipments trata devoluções para lojas de três formas: pedido de devolução no site da loja escolhendo dia, hora e local de recolha; recolha marcada pela loja; ou envio por ti, com qualquer transportadora, para a morada local de devolução indicada pela loja (um armazém da euShipments). As condições da loja indicam qual se aplica. Vê [seguir uma devolução](guide:returns-tracking).

**Perdida ou danificada.** A euShipments não publica processo de reclamação para destinatários: o contrato é com a loja. Na UE, o vendedor responde por danos desde a expedição até à receção e deve entregar em 30 dias salvo outro acordo. Se não o fizer, lembra a loja e dá-lhe um prazo extra razoável; se continuar sem entregar, podes cancelar e pedir reembolso. Vê [encomenda perdida](guide:lost-parcel) e [encomenda danificada](guide:damaged-parcel).

## Contacto e apoio ao cliente euShipments

A euShipments indica os seguintes contactos:

- **Telefone:** +359 700 70 755, número búlgaro presente nas páginas de contacto, FAQ e seguimento. Não indica horários.
- **E-mail:** office@eushipments.com.
- **Formulário de contacto:** um questionário de orçamento para lojas (volume mensal, mercados), não destinado a destinatários.

As FAQ para lojas indicam que a empresa oferece apoio por telefone, e-mail e chat ao vivo a compradores finais mediante pedido («when requested»). Começa por isso pela loja; para a entrega em si, a transportadora indicada em «Courier Name:» responde mais depressa.

## Seguir uma encomenda euShipments com o Peek

O [Peek](/) não consegue identificar um número euShipments pela forma: não existe regra de formato para a euShipments e oito dígitos correspondem fracamente a outras transportadoras. Cola-o mesmo assim: o Peek segue mais de 4500 transportadoras. Carrega depois em «Alterar» e escolhe «euShipments», ou usa «Alterar transportadora» numa encomenda já adicionada.

O Peek lê o seguimento da euShipments. Mostra o estado, o histórico e, nas etapas de armazém e hub, o local (com o país nos hubs). Mantém os estados em inglês da euShipments e deixa de fora os textos da transportadora em «Tracking Information». Quando «Tracking Link:» aponta para uma transportadora que o Peek segue — como [Econt](carrier:econt), Sameday, [FAN Courier](carrier:fan-courier), [Packeta](carrier:packeta), [Slovenská pošta](carrier:slovenska-posta) ou [Geniki Taxydromiki](carrier:geniki-taxydromiki) — com o número dela na ligação, o Peek mostra «Entrega: …» e esse número se diferir do teu. Enquanto essa transportadora tiver apenas o anúncio sem registo próprio, mostra «Anunciada a …, ainda não recebida». Assim que apresenta progresso datado pelo menos tão recente como a última linha da euShipments, o Peek passa a acompanhá-la lá mantendo as linhas anteriores; caso contrário permanece na euShipments.

A euShipments não indica data prevista de entrega nem comprovativo de entrega, pelo que o Peek não mostra nenhum. As horas sem fuso horário são lidas na hora local (hora búlgara na Bulgária) e convertidas para o teu fuso horário («Horas no teu fuso horário:»). O Peek verifica até de 10 em 10 minutos, e de 2 em 2 minutos a partir das 6:00 no relógio do último registo no dia em que entra «Em distribuição» enquanto tiveres a encomenda aberta no Peek ou as notificações ligadas, quando o portal da euShipments responde. Após dois dias sem novidades (12 horas enquanto estiver em «Etiqueta criada» ou «Aceite pela transportadora»), verifica de hora a hora. Nas notificações podes escolher «Todas as atualizações», «Só as importantes» ou «Só no dia da entrega». Ao fim de quatro dias sem registo em trânsito, fora da alfândega ou de um problema reportado, o Peek mostra «Sem atualizações desde …», a nota «É habitual uma encomenda ficar sem novidades em voos longos, na alfândega ou ao fim de semana. O Peek continua a perguntar a euShipments.» e «Pede ajuda a euShipments». O Peek não pode contactar a euShipments, a loja nem a transportadora por ti.

## Perguntas sobre o seguimento euShipments

### A euShipments é de confiança?

Sim. É uma empresa de logística de Ruse, Bulgária, fundada em 2012 como InOut Trade e renomeada euShipments.com em 2019. Expede para lojas online, incluindo vendedores na Temu e na eMAG através de integrações próprias.

### Que transportadora entrega a minha encomenda euShipments?

Uma transportadora local no teu país. Assim que tem o volume, o resultado da euShipments mostra «Courier Name:», «Tracking Number:» e «Tracking Link:» abaixo da tabela.

### Quanto tempo demora uma entrega euShipments?

Depende do armazém onde está o artigo. A partir de Sófia e Ruse, a euShipments indica entrega no dia seguinte na Bulgária e Roménia, e 2 a 4 dias para a Croácia, Eslovénia, Eslováquia e Chéquia. A partir de Zagreb, Liubliana ou Senec indica 1 dia útil no respetivo país. Orienta-te pela estimativa da loja. Mais em [prazos de entrega](guide:delivery-times).

### O que significa Loaded hub na euShipments?

A encomenda foi carregada num hub da euShipments para o troço seguinte, indicado com o país entre parênteses. `Unloaded hub` assinala a chegada ao hub seguinte.

### Para que países entrega a euShipments?

A lista «We deliver to» nomeia 28 países: os 27 da UE e o Reino Unido. Em cada um, uma transportadora local assegura a entrega final.

:::sources
- [euShipments: Track Your Order](https://eushipments.com/tracking) – formulário de seguimento e termos, telefone, e-mail
- [euShipments: Parcel Tracking For End Customers](https://eushipments.com/ecommerce-delivery/new-parcel-tracking/parcel-tracking-for-end-customers) – código de seguimento, AWB ou ID, mais de 60 parceiros de última milha
- [euShipments: We deliver to](https://eushipments.com/we-deliver-to) – lista dos 28 países: 27 da UE e Reino Unido
- [euShipments: FAQ](https://eushipments.com/faq) – mais de 60 transportadoras, pelo menos duas por país, apoio ao cliente final mediante pedido, telefone, e-mail
- [euShipments: Contact Us](https://eushipments.com/contact) – telefone, e-mail, Ruse, formulário de orçamento para lojas
- [euShipments: About us](https://eushipments.com/about-us) – InOut Trade, 2012, mudança de nome em 2019, parceria Austrian Post
- [euShipments: Home](https://eushipments.com/) – logótipos de parceiros-chave de comércio eletrónico
- [euShipments: eCommerce Deliveries To Bulgaria](https://eushipments.com/countries/bulgaria) – Sófia e Ruse, transporte até ao depósito do estafeta, entrega ao domicílio e fora de casa, prazos, contra reembolso
- [euShipments: International eCommerce Delivery To Romania](https://eushipments.com/countries/romania) – Oradea, Budapeste, opções de entrega
- [euShipments: Domestic and International eCommerce Delivery To Croatia](https://eushipments.com/countries/croatia) – Zagreb e outros centros, entrega no dia seguinte, cacifos, pontos de levantamento, balcões
- [euShipments: International Parcel Delivery To Slovenia](https://eushipments.com/countries/slovenia) – Liubliana, 1 dia útil
- [euShipments: International Delivery To Slovakia](https://eushipments.com/countries/slovakia) – Senec, 1 dia útil
- [euShipments: Out-of-home Delivery](https://eushipments.com/ecommerce-delivery/out-of-home-delivery) – 150 000 locais, notificações de levantamento
- [euShipments: Returns Management](https://eushipments.com/returns-management) – envios não entregues, três formas de devolução
- [euShipments: Temu Integration](https://eushipments.com/integration/ecommerce-platform-integration/temu) – vendedores Temu
- [euShipments: eMAG Integration](https://eushipments.com/integration/ecommerce-platform-integration/emag-integration) – vendedores eMAG
- [Your Europe: Shipping and delivery](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_en.htm) – 30 dias, responsabilidade do vendedor até à receção, lembrete e reembolso
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – etiquetas da página de resultados, estados, números, o que o Peek lê e mostra
:::
