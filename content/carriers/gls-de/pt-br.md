---
title: Rastreamento GLS Alemanha: status, ParcelShops e contato
description: Rastreie encomendas GLS na Alemanha: onde achar o número ou Track-ID, por que a GLS pede o código postal, status, ParcelShops e contato do suporte.
slug: rastreamento-gls-alemanha
published: 2026-11-24
updated: 2026-11-24
---

A GLS Alemanha (GLS Germany) entrega em dias úteis e, segundo dados do grupo, conta com 9.000 ParcelShops e 200 Parcelstations na Alemanha. Rastreie uma encomenda na [página de rastreamento da GLS](https://gls-group.com/EU/en/parcel-tracking) usando o número do pacote e o código postal do destinatário, ou cole o código na caixa acima. O número aparece no e-mail da loja, e o cartão de aviso da GLS traz um Track-ID.

## Número de rastreamento da GLS e Track-ID: onde encontrar

Confira primeiro o e-mail de envio da loja. O rastreamento funciona assim que o remetente envia os dados do pacote à GLS, e encomendas enviadas por lojas do exterior via GLS são acompanhadas na mesma página do grupo GLS. O cartão de aviso da GLS, em papel ou por e-mail, traz um Track-ID de seis letras que funciona como o número do pacote no rastreamento da GLS.

O Peek reconhece estes formatos:

| Código (fictício) | O que é |
| --- | --- |
| `48291736058` | 11 dígitos. Também combina com GLS Suíça, GLS França, GLS Áustria, GLS Polônia, PostLogistics e outras. |
| `482917360580` | 12 dígitos, sendo o último verificador. Também combina com [BRT](carrier:brt), [Mondial Relay](carrier:mondial-relay), Colis Privé e outras. |
| `KL482917` | 8 letras e dígitos: um Track-ID também pode ter esse formato. Serve ainda para GLS Suíça, GLS França, GLS Países Baixos e Loggi. |
| Track-ID | Seis letras presentes no cartão de aviso. O Peek lê como número de encomenda; se o seu não for aceito, use o número do pacote. |

Código recusado? Se o e-mail da loja citar outra transportadora, o número pertence a ela: veja [formatos de código de rastreamento](guide:tracking-number-formats).

## O que significam os status de rastreamento da GLS

Estes são os textos em inglês da GLS, na ordem em que uma encomenda passa por eles:

| A GLS diz | O que significa para você |
| --- | --- |
| `The parcel data was entered into the GLS IT system…`, `The parcel has not been handed over to GLS.` | A GLS recebeu os dados do remetente, mas ainda não está com o pacote. |
| `The parcel was handed over to GLS.` | A GLS recebeu o pacote. |
| `The parcel has not been released by customs.`, `Customs Consignment via Customs Portal` | Encomenda vinda de fora da UE ainda retida na alfândega; o Peek mostra "Na alfândega". |
| `The parcel was released by customs.` | A alfândega liberou e o pacote seguiu viagem; o Peek mostra "Em trânsito". |
| `The parcel is in delivery.`, `The parcel is expected to be delivered during the day.` | No furgão da GLS, com entrega prevista para hoje. |
| `The parcel is ready for collection at the ParcelShop.` | Disponível para retirada em um ParcelShop ou Parcelstation. |
| `The parcel has been delivered.` | Entregue a você ou a quem recebeu por você, como um vizinho. |

Entre essas etapas, aparecem leituras de chegada e saída em centros de triagem ou depósitos da GLS, indicando que o pacote está a caminho. Se o motorista não encontrou ninguém ou não pôde entregar por outro motivo, o Peek mostra "Tentativa de entrega". A GLS então tenta mais uma vez ou leva o pacote a um ParcelShop, depósito ou Parcelstation próximo, informando onde ficou por cartão ou e-mail. Se não for retirado ou não puder ser entregue, retorna ao remetente e o Peek mostra "Devolvida ao remetente". A GLS contabiliza a chegada ao ParcelShop como entregue, embora o pacote ainda aguarde retirada lá. `The parcel has not been handed over to GLS.` segue como última etapa até o remetente postar o envio — cobre o remetente.

## Horários de entrega, ParcelShops e armários da GLS

**Quando entrega.** De segunda a sexta, nunca aos sábados, dentro de prazos estimados que a GLS não garante.

**Caixa de correio.** Pacotes pequenos, de até 2 kg, tamanho máximo DIN C4 e altura máxima de 5 cm, podem ir direto para sua caixa de correio.

**Ninguém em casa.** O entregador pode entregar a alguém na sua residência ou empresa, ou a um vizinho no mesmo prédio ou no prédio mais próximo, salvo instrução em contrário sua ou do remetente; um cartão ou e-mail informa o nome do vizinho. Caso contrário, a GLS pede que você retire no ParcelShop, depósito ou Parcelstation mais próximo. A GLS realiza no máximo duas tentativas de entrega.

**Como alterar.** Quando a GLS oferece opções de entrega, você pode escolher outro dia ou endereço, um ParcelShop ou retirada no depósito; segundo as informações ao consumidor da GLS (janeiro de 2018), essas opções são gratuitas para destinatários. Em envios do exterior com o FlexDeliveryService da GLS, ela mantém você informado, mostra previsão de dia ou faixa horária quando possível e oferece alterações; se você escolheu um ParcelShop no checkout de uma loja estrangeira (ShopDeliveryService), o pacote vai direto para lá sem tentativa na sua casa. Uma autorização de depósito permite que o motorista deixe pacotes em local escolhido por você, com aviso por cartão ou e-mail.

**ParcelShops** guardam encomendas deixadas após entrega sem sucesso por pelo menos sete dias úteis, segundo os termos da GLS para remetentes comerciais. Encomendas não retiradas voltam ao remetente.

**Parcelstations** são os armários da GLS e exigem seu próprio dispositivo móvel para abertura. Você pode se opor ao uso de armário uma vez ou permanentemente, seguindo as instruções no aviso de ausência.

## Pacote da GLS não entregue, atrasado ou danificado

O contrato da GLS é com o remetente, portanto reembolsos passam pela loja.

**Sem atualização.** Pergunte ao remetente e veja [por que o rastreamento para de atualizar](guide:tracking-not-updating).

**Marcado como entregue mas não chegou.** Confira no rastreamento se foi para um ParcelShop e olhe a caixa de correio e o e-mail em busca do aviso da GLS indicando vizinho ou local de depósito. Veja mais em [marcado como entregue mas não recebido](guide:delivered-not-received).

**Na alfândega.** Encomendas vindas de fora da União Europeia passam pela alfândega. A GLS informa que imposto de importação, IVA de importação e normalmente uma tarifa de desembaraço precisam ser pagos antes da entrega; quem paga a tarifa depende das condições escolhidas pelo remetente. Na UE, vigora desde 1º de julho de 2026 uma tarifa alfandegária fixa de € 3 por tipo de item em pedidos online até € 150. Desde 1º de novembro de 2026, aplica-se também a taxa de manuseio da UE de € 2 por tipo de item, qualquer que seja o valor, devida por quem declara a mercadoria à alfândega. Mais detalhes em [encomendas paradas na alfândega](guide:customs).

**Danificado.** A lei alemã (Código Comercial, § 438 HGB) exige que avarias visíveis sejam sinalizadas na entrega (avisar o motorista basta), danos ocultos reportados em até 7 dias e atrasos em até 21 dias, em forma de texto; enviar o aviso dentro do prazo é suficiente. Tanto o destinatário quanto o remetente podem comunicar perda ou avaria. A loja vendedora assume o risco do transporte, salvo se você mesmo contratou uma transportadora não indicada pela loja (Código Civil, § 475 BGB); portanto, fale primeiro com a loja.

**Disputas.** Perdas ou avarias não resolvidas com a GLS podem ser levadas à Schlichtungsstelle Post da Bundesnetzagentur, tanto por remetentes quanto por destinatários. O serviço é gratuito para disputas entre € 10 e € 2.500, e a GLS é obrigada a participar quando um consumidor abre o pedido.

**Direito de arrependimento (Widerruf).** Deixar de retirar um pacote não cancela a compra. Se você comprou à distância, como consumidor, de uma empresa (como online) ou fora do estabelecimento comercial, pode em geral desistir avisando a loja claramente e sem precisar justificar em até 14 dias (Código Civil, § 355 BGB). Para bens físicos, o prazo começa após o recebimento (§ 356), bastando enviar o aviso a tempo.

## Atendimento ao cliente e contato da GLS Alemanha

Para pedidos de lojas, a loja é seu primeiro ponto de contato. As opções de atendimento da GLS Alemanha ficam no site alemão gls-pakete.de. O grupo GLS pede que dúvidas sobre status sejam enviadas ao atendimento do seu país e informa que o telefone está no site nacional da GLS; o [formulário de contato do grupo](https://gls-group.com/GROUP/en/contact-form/) também recebe solicitações de destinatários. Para envios feitos por você via GLS-Pakete.de ou app, os termos (agosto de 2023) informam versand-service@gls-germany.com e +49 6677 646907040.

> O grupo GLS alerta sobre e-mails, SMS e sites falsos em seu nome. O portal principal é gls-group.com, e somente ele e os domínios vinculados a partir dele, como gls-pakete.de, são legítimos. A GLS nunca pede por e-mail que destinatários paguem por mercadorias com cartão, transferência ou pagamento online. Porém, em encomendas de fora da UE, a GLS ou um agente autorizado pode cobrar impostos e taxas por e-mail, SMS ou carta. Encomendas contra reembolso (GLS Cash Service) só são entregues mediante pagamento em dinheiro ao motorista, e a GLS desaconselha pagar entregas contra reembolso inesperadas para você ou vizinhos. Encaminhe e-mails suspeitos como anexo para security@gls-group.com.

## Como rastrear uma encomenda GLS Alemanha com o Peek

O [Peek](/) consulta as transportadoras prováveis para os formatos acima. A GLS responde tanto por envios alemães quanto suíços, e o Peek exibe GLS Germany ("GLS Germany está com esta encomenda"), ou GLS France quando esta reconhece o envio. Na Suíça ou em Liechtenstein (pela região do aparelho ou conexão web), ele exibe GLS Switzerland com campo de código postal de 4 dígitos: para envios destinados à Alemanha, clique em "Trocar" e escolha GLS Germany. Colar um link de gls-pakete.de ou um link gls-group com `/DE/`, `/EU/` ou `/GROUP/` também seleciona GLS Germany.

A GLS exige o código postal de entrega para exibir o histórico detalhado, então o campo "Código postal de entrega" é obrigatório; se a GLS não encontrar o pacote com o código informado, o Peek avisa e permite corrigir. Qualquer formato nacional funciona. O código postal é enviado à GLS e, quando necessário, a serviços de rastreamento.

Em seguida você vê o status, a cidade e o país de cada leitura no mapa, a data prevista, o peso, o horário de entrega e, em ParcelShops, o nome e endereço do ponto. Seu nome, endereço, contatos, assinatura e referência do remetente são removidos. O Peek mostra a chegada ao ParcelShop como "Pronta para retirada". Os horários seguem o horário da Alemanha. O Peek consulta a GLS cerca de uma vez por hora, inclusive no dia da entrega (4 horas depois de uma consulta que falhou), e atualizar manualmente não antecipa a consulta. Somente pacotes repassados pela GLS à Swiss Post são acompanhados em outra transportadora. Quando outra transportadora assume a entrega, o Peek segue o ritmo da transportadora que entrega o pacote. O Peek pode avisar você a cada mudança.

## Perguntas sobre o rastreamento da GLS na Alemanha

### Onde está meu pacote da GLS?

O rastreamento da GLS mostra assim que você digita o número e o código postal do destinatário: `The parcel is in delivery.` indica que está no furgão hoje.

### O que é o Track-ID da GLS?

É o código de seis letras impresso no cartão de aviso da GLS. Ele funciona da mesma forma que o número do pacote no rastreamento da GLS.

### Quanto tempo a GLS leva para entregar?

A GLS publica prazos estimados em gls-pakete.de sem garanti-los. O grupo afirma que envios nacionais costumam chegar em menos de 24 horas e envios pela Europa em 24 a 96 horas. Apenas envios contratados pelo remetente com o Guaranteed24Service têm entrega prevista para o dia útil seguinte (segunda a sexta) na Alemanha, exceto ilhas.

### Por quanto tempo a GLS guarda uma encomenda?

Após uma entrega sem sucesso, o ParcelShop, depósito ou Parcelstation guarda o pacote por pelo menos sete dias úteis. Depois ele volta ao remetente.

### A GLS é confiável?

Sim: a GLS Alemanha é a General Logistics Systems Germany GmbH & Co. OHG, com sede em Neuenstein. Porém, mensagens falsas usam seu nome — confira o alerta acima.

:::sources
- [GLS: Parcel Tracking](https://gls-group.com/EU/en/parcel-tracking) – página de rastreamento do grupo e Track-ID no cartão de aviso
- [GLS: About us](https://gls-group.com/GROUP/en/about-us/) – ParcelShops e armários na Alemanha (março de 2026)
- [GLS: Parcel, Logistics & Express](https://gls-group.com/GROUP/en/our-business/parcels/) – prazos nacionais e europeus, impostos, taxas e desembaraço antes da entrega
- [GLS: B2C Services](https://gls-group.com/GROUP/en/our-business/cross-border-services/b2c/) – FlexDeliveryService e ShopDeliveryService para envios do exterior
- [GLS: Contact](https://gls-group.com/GROUP/en/contact-form/) – formulário de contato e orientação de telefones nos sites nacionais
- [GLS Germany: General Terms and Conditions for business customers](https://gls-group.com/DE/media/downloads/AGB_GLS_Germany_2025-01_EN_2024-10-25_FINAL-WEB.pdf) – sem entrega aos sábados, prazos estimados, pacotes pequenos na caixa de correio, vizinhos, duas tentativas, ParcelShop/depósito/Parcelstation, sete dias úteis, retorno ao remetente, opções de entrega e Guaranteed24Service
- [GLS Germany: Terms for GLS-Pakete.de and the app](https://gls-group.com/DE/media/downloads/AGB_GLS-paketeApp_EN_2023-08-21.pdf) – razão social, cartão ou e-mail, autorização de depósito, Schlichtungsstelle Post e contato para remetentes
- [GLS Germany: Consumer information on delivery options](https://gls-group.com/DE/media/downloads/180130_Verbraucherinformation_Umverfuegung_DE_EN.pdf) – gratuidade das opções para destinatários (janeiro de 2018)
- [GLS: Security advice](https://gls-group.com/GROUP/en/security-advice/) – golpes, sites legítimos, taxas de fora da UE, GLS Cash Service, denúncias e atendimento nacional
- [Bundesnetzagentur: Schlichtungsstelle Post](https://www.bundesnetzagentur.de/DE/Vportal/Schlichtung/Schlichtung_Post/start.html) – arbitragem gratuita para remetentes e destinatários entre € 10 e € 2.500
- [Gesetze im Internet: § 438 HGB](https://www.gesetze-im-internet.de/hgb/__438.html) – prazos de aviso de danos e atrasos pelo destinatário ou remetente
- [Gesetze im Internet: § 475 BGB](https://www.gesetze-im-internet.de/bgb/__475.html) – risco de transporte em compras de consumo
- [Gesetze im Internet: § 355 BGB](https://www.gesetze-im-internet.de/bgb/__355.html) – direito de arrependimento em 14 dias sem justificativa
- [Gesetze im Internet: § 356 BGB](https://www.gesetze-im-internet.de/bgb/__356.html) – início do prazo de arrependimento em contratos à distância
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – tarifa alfandegária de € 3 por tipo de produto até € 150 desde 1º de julho de 2026 e taxa de manuseio da UE de € 2 desde 1º de novembro de 2026
- [European Commission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – € 2 por tipo de produto qualquer que seja o valor e responsabilidade do declarante
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – o que o Peek lê, os textos em inglês da GLS (inclusive alfândega e devoluções), Track-ID, consultas de hora em hora
:::
