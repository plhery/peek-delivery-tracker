---
title: Rastreamento GLS Suíça: status, Swiss Post e contato
description: Rastreie encomendas GLS na Suíça: formatos de número, por que informar o código postal, status em inglês, entrega pela Swiss Post, alfândega e contato.
slug: gls-switzerland-tracking
published: 2026-11-26
updated: 2026-11-26
---

A GLS é uma rede internacional de encomendas sediada em Amsterdã sem subsidiária própria na Suíça: sua parceira, a Swiss Post, faz o desembaraço aduaneiro e entrega os pacotes que a GLS leva ao país. Acompanhe o envio na [página de rastreamento da GLS](https://gls-group.com/EU/en/parcel-tracking) informando o número da encomenda e o seu código postal, ou cole o código na caixa acima. O número vem do remetente, portanto comece verificando o e-mail de envio da loja.

## Número de encomenda e Track ID da GLS Suíça

O rastreamento da GLS aceita números de encomenda (*parcel numbers*) ou Track IDs. A GLS imprime o Track ID nos cartões de aviso, mas na Suíça quem entrega é a Swiss Post, e nenhuma página indica o uso de cartões da GLS no país. Nem a GLS nem a Swiss Post publicam uma tabela oficial de formatos. Os formatos que o Peek reconhece como da GLS são:

| Número (fictício) | O que é |
| --- | --- |
| `48291736542` | 11 dígitos. Também compatível com [GLS Germany](carrier:gls-de), [GLS France](carrier:gls-fr), PostLogistics e [DHL eCommerce Poland](carrier:dhl-ecommerce-pl). |
| `482917365424` | 12 dígitos, sendo o último um dígito verificador. Também compatível com BRT, Mondial Relay, Colis Privé e outras. |
| `K7M4P9X2` | 8 letras e dígitos. Também compatível com GLS Germany e GLS France. |
| Seis letras | Como `HMPQRT`. Também compatível com GLS Germany. |

Copie o código em vez de digitá-lo: um número de 12 dígitos com o último dígito incorreto, ou um de 11 dígitos faltando um algarismo, deixa de corresponder à GLS. Se o e-mail da loja mencionar outra transportadora, o código pertence a ela. Não tem nenhum número? Veja [como encontrar seu código de rastreamento](guide:find-tracking-number).

## Rastreamento GLS na Suíça: o que cada status significa

O rastreamento da GLS para encomendas suíças aparece somente em inglês, e a GLS não publica uma lista oficial de status para a Suíça. Estas são as mensagens usadas pelo rastreamento do grupo, em ordem habitual:

| A GLS informa | O que significa para você |
| --- | --- |
| `Preadvice`, `The parcel has not been handed over to GLS.` | O remetente cadastrou os dados do pacote. A GLS ainda não está com a encomenda física. |
| `The parcel was handed over to GLS.` | A GLS recebeu o pacote. |
| `In transit`, `The parcel has left the parcel center` | Em deslocamento entre centros da GLS. |
| `Customs Consignment via Customs Portal`, `The parcel has not been released by customs.` | Na alfândega, etapa conduzida pela Swiss Post para encomendas GLS. |
| `The parcel was released by customs.` | Liberada pela alfândega e novamente em trânsito. |
| `Final parcel center` | No último centro de distribuição: a entrega vem a seguir. |
| `In delivery`, `The parcel is in delivery.` | Saiu para entrega, com previsão durante o dia. |
| `The parcel could not be delivered as the consignee was absent` | Não entregue porque ninguém estava em casa. |
| `The parcel is stored in the parcel center` | Guardada no depósito após tentativa sem sucesso, por exemplo por ausência no local. |
| `The parcel is ready for collection at the ParcelShop.` | Aguardando retirada em um ParcelShop ou armário. |
| `The parcel has been delivered.`, `Delivered` | Entregue. |

`Preadvice` significa apenas que a loja registrou os dados da encomenda no sistema da GLS: se permanecer assim por dias, pergunte ao remetente. A GLS também pode exibir `Delivered` quando deixa o pacote em um ParcelShop que você ainda precisa retirar. Essas duas mensagens e explicações vêm da GLS Holanda, onde a própria GLS faz a entrega; na Suíça, quem entrega é a Swiss Post.

## Entrega GLS na Suíça: Swiss Post e avisos de retirada

**Quem realiza a entrega.** A parceira da GLS para a Suíça e Liechtenstein é a Swiss Post GLS, unidade da Swiss Post em Basileia. A Swiss Post desembaraça na alfândega e entrega as encomendas recebidas via GLS, observando que o desembaraço pode diferir do seu fluxo habitual.

**ParcelShops.** Nenhuma página da GLS lista um GLS ParcelShop ou armário próprio na Suíça.

**Entrega perdida.** A GLS não publica regras para a Suíça sobre número de tentativas, cartões de aviso ou entrega aos sábados. Se a Swiss Post deixar um aviso de retirada (*collection note*), valem estas regras aplicadas aos seus próprios envios (nenhuma página cita especificamente pacotes GLS):

- Retire com o aviso ou o QR code no Post-App, normalmente em até sete dias, ou 15 dias para envios internacionais. Prorrogar o prazo é gratuito e dispensa conta.
- Com acesso a "My consignments", agende uma segunda entrega, redirecione para um terminal My Post 24, outro endereço ou agência (CHF 8 em dias úteis), ou autorize outra pessoa a retirar gratuitamente com uma autorização avulsa.

## Encomenda GLS atrasada, na alfândega, perdida ou danificada

O remetente escolheu a GLS e, conforme informam as páginas da Swiss Post GLS, paga sempre o transporte; portanto, a loja é o seu primeiro contato.

**Sem atualização.** `Preadvice` indica que a encomenda ainda está com a loja; um status alfandegário significa espera pela liberação da Swiss Post. Fora isso, consulte [por que o rastreamento para de atualizar](guide:tracking-not-updating).

**Taxas alfandegárias.** Quem paga depende das condições de entrega (Incoterm) escolhidas pelo remetente: no Incoterm 20 da GLS você paga a taxa de desembaraço, imposto de importação e IVA; nos Incoterms 30 ou 40 os custos são divididos e você paga ao menos o IVA. A Swiss Post aplica aos pacotes GLS suas [taxas habituais de desembaraço](https://www.post.ch/en/business-solutions/exports-imports-and-customs-clearance/exports/swiss-post-gls/import-costs). Para contestar uma cobrança, ligue para o Atendimento Internacional da Swiss Post em +41 848 48 48 47; a alfândega concede 60 dias a partir da decisão de lançamento tributário para corrigir o procedimento, o endereço ou o imposto.

**Perda ou dano.** A GLS não publica regras de reclamação, prazos nem limites de indenização para a Suíça: fale diretamente com a loja.

## Telefone e e-mail de contato da GLS Suíça

A GLS não mantém uma central telefônica própria na Suíça. Sua página de contato direciona para "o site da empresa nacional da GLS", que na Suíça e em Liechtenstein corresponde à Swiss Post GLS:

- **Telefone:** +41 848 48 48 47, linha de importação da Swiss Post GLS, CHF 0,08 por minuto de linha fixa suíça, de segunda a sexta das 8h às 12h e das 13h30 às 17h30 (horário suíço). A Swiss Post lista esse número como "GLS (parcels)" em páginas comerciais e alfandegárias, não como central de rastreamento.
- **E-mail:** infogls@swisspost.ch (inglês), infogls@post.ch, infogls@poste.ch ou infogls@posta.ch.
- **Correio:** Swiss Post GLS, Post-Passage 11, 4002 Basel.
- **Grupo GLS:** [formulário de contato](https://gls-group.com/GROUP/en/contact-form/) em inglês.

> A GLS alerta sobre e-mails, SMS, sites falsos e abordagens fraudulentas na porta de casa. Fora da UE, a GLS ou um agente autorizado pode legitimamente cobrar impostos por e-mail, SMS ou carta, mas a GLS nunca envia e-mail pedindo pagamento pelos produtos por cartão, pagamento online ou transferência bancária. Seu site principal é gls-group.com. Denuncie fraudes para security@gls-group.com.

## Rastreando uma encomenda GLS Suíça com o Peek

O [Peek](/) aceita os quatro formatos e consulta as transportadoras prováveis. Como GLS Germany e GLS Switzerland usam os mesmos números, ele escolhe pelo seu país (a região do celular ou, na web, o local da conexão). Na Suíça ou em Liechtenstein normalmente aparecerá "GLS Switzerland tem esta encomenda."; se o pacote for para a Alemanha, toque em "Alterar" e escolha GLS Germany. Em outros países aparece GLS Germany, que rastreia encomendas suíças igualmente bem com o código postal suíço. Links colados de gls-group com `/EU/` ou `/GROUP/` são acompanhados como GLS Germany.

A GLS exibe o histórico de leituras somente com o código postal de entrega, por isso o campo "Código postal de entrega" é obrigatório: quatro dígitos, como 8000. Ele é enviado à GLS e, quando necessário, aos serviços de rastreamento; se não corresponder à encomenda, o Peek avisa ("GLS Switzerland não encontrou esta encomenda com o código postal ….") para que você corrija.

Em seguida, você vê o status, a cidade e o país de cada leitura no mapa, o dia previsto de entrega, o peso e o horário de entrega, sem expor seu nome, endereço, dados de contato ou assinatura. Os status mantêm o texto original em inglês da GLS; o status de ParcelShop aparece no Peek como "Pronta para retirada". A GLS é consultada cerca de uma vez por hora, inclusive no dia da entrega (4 horas após uma consulta com falha), e atualizar manualmente não antecipa a verificação. Quando outra transportadora assume a entrega, o Peek acompanha no ritmo da transportadora que realiza a entrega. Você pode receber alertas de cada leitura, etapas importantes ou apenas no dia da entrega.

Quando a GLS indica a Swiss Post como responsável pela entrega, a página do pacote exibe "Entrega por Swiss Post" (ou "Anunciada a Swiss Post, ainda não recebida" enquanto a Swiss Post tem apenas o anúncio da encomenda, sem leitura própria), e o Peek pode acompanhar o envio no rastreamento da Swiss Post com o mesmo número assim que a Swiss Post registrar uma leitura ao menos tão recente quanto a última da GLS.

## Perguntas sobre o rastreamento da GLS Suíça

### Qual empresa de entregas é a GLS?

GLS significa General Logistics Systems, sediada em Amsterdã e parte da International Distributions Services, anteriormente Royal Mail Group. Na Suíça, sua parceira Swiss Post cuida das encomendas.

### Quanto tempo a GLS leva para entregar na Suíça?

A GLS informa prazo apenas a partir da Áustria: 2 dias úteis de transporte como padrão. De outros países, siga a estimativa informada pela loja.

### Por que o rastreamento da GLS não está funcionando?

Confira primeiro o código postal: a GLS não mostra histórico de leituras sem o código postal de entrega. Verifique também se há algum dígito errado ou faltando; endereços antigos `gls-group.com/CH/` levam a uma página de erro.

### A GLS é confiável?

Sim, mas mensagens falsas usam o nome da empresa: veja o alerta acima.

:::sources
- [GLS: Parcel Tracking](https://gls-group.com/EU/en/parcel-tracking) – formatos de número, código postal e exibição apenas em inglês
- [GLS: Parceiros GLS, Suíça](https://gls-group.com/GROUP/en/gls-partners/?partner=switzerland) – Swiss Post GLS
- [GLS: Parceiros GLS, Liechtenstein](https://gls-group.com/GROUP/en/gls-partners/?partner=liechtenstein) – mesma parceira
- [GLS: Sobre nós](https://gls-group.com/GROUP/en/about-us/) – dados corporativos
- [GLS: Contato](https://gls-group.com/GROUP/en/contact-form/) – formulário e linhas nacionais
- [GLS: Orientações de segurança](https://gls-group.com/GROUP/en/security-advice/) – alertas contra golpes
- [GLS Holanda: FAQ destinatários](https://gls-group.com/NL/en/faq-recipient/) – textos de eventos, significado e entrega em ParcelShop
- [GLS Áustria: Versand in die Schweiz](https://gls-group.com/AT/de/versand-in-die-schweiz/) – entrega pela Swiss Post
- [GLS Áustria: Regellaufzeiten](https://gls-group.com/AT/de/regellaufzeiten/) – prazo padrão de trânsito
- [Swiss Post: Swiss Post GLS](https://www.post.ch/en/business-solutions/exports-imports-and-customs-clearance/exports/swiss-post-gls) – telefone e e-mail
- [Swiss Post: Import costs](https://www.post.ch/en/business-solutions/exports-imports-and-customs-clearance/exports/swiss-post-gls/import-costs) – Incoterms e contestação
- [Swiss Post: Importverzollung](https://www.post.ch/de/empfangen/importverzollung) – desembaraço GLS e taxas
- [Swiss Post: Collection note](https://www.post.ch/en/receiving-mail/collection-note) – prazos e opções de retirada
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – o que o Peek lê e marcação da GLS de chegada em ParcelShop como entregue
:::
