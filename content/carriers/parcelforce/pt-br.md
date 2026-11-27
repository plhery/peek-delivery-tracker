---
title: Rastreamento Parcelforce Worldwide: status e alfândega
description: Rastreie encomendas da Parcelforce Worldwide: onde achar o código, significado dos status, opções de entrega, taxas aduaneiras e atendimento da Royal Mail.
slug: parcelforce-worldwide-tracking
published: 2026-11-26
updated: 2026-11-26
---

A Parcelforce Worldwide faz parte do grupo [Royal Mail](carrier:royal-mail), e você acompanha um envio da Parcelforce na página "Track your item" do Royal Mail ou colando o código na caixa acima. O código vem da loja ou do remetente, geralmente no e-mail de confirmação do pedido.

## Código de rastreamento Parcelforce: onde encontrar

Verifique o e-mail de confirmação do pedido ou peça à loja. Perdeu o código? Veja [como encontrar seu código de rastreamento](guide:find-tracking-number). Três formatos que podem aparecer:

| Código (inventado) | O que é |
| --- | --- |
| `EA482917367GB` | Duas letras (`E` e outra letra, ou `CP` ou `GI`), nove dígitos e `GB`, com um dígito verificador antes de `GB`. |
| `PBTR4829173654` | Código de volume de 14 caracteres: `PB`, o número da remessa e a posição do volume nela. |
| `TR4829173` | Número da remessa isolado: o rastreador do Royal Mail exige o código individual de cada volume. |

O formato de 13 caracteres também é usado pelo Royal Mail (`RR482917367GB` pertence ao Royal Mail, não à Parcelforce), e algumas encomendas express48 usam código do Royal Mail. Como ambas são rastreadas na mesma página, isso muda pouco na prática.

## Status de rastreamento da Parcelforce explicados

O rastreador do Royal Mail exibe essas movimentações em inglês. Na ordem em que a encomenda costuma passar por eles:

| O rastreamento diz | O que significa para você |
| --- | --- |
| `Sender has despatched item` | O remetente registrou a postagem. A Parcelforce ainda não está com o pacote. |
| `Item Collected` | Coletado junto ao remetente. |
| `Accepted at Parcelshop` | O remetente entregou o volume em um Post Office ou parcel shop. |
| `Item received` | Já entrou na rede de transporte. |
| `Customs charges raised` | Importação de fora do Reino Unido tem IVA, imposto aduaneiro ou taxa pendente antes da entrega. |
| `Despatched to delivery office` | A caminho do seu depósito local ou centro de entrega. |
| `Received at Delivery Depot` | Chegou ao depósito local; ainda não está disponível para retirada. |
| `Out for delivery` | Saiu com o motorista hoje. |
| `Delivery attempted` | O motorista não conseguiu entregar. |
| `Delivered by` | Entregue. A linha pode citar a unidade que realizou a entrega. |
| `Delivered to Neighbour/Reception by` | Deixada com um vizinho ou na recepção, indicado no texto. |
| `Returned to sender` | Retornando ao remetente. |

Em envios ao exterior, o histórico continua com registros como `Item Leaving the UK` e o `Due to be delivered today` do parceiro entregador no destino.

## Opções de entrega e entregas não realizadas na Parcelforce

**Antes da entrega.** No aplicativo do Royal Mail ou pelo atendimento ao cliente do Royal Mail, você pode escolher outra data de entrega para um volume da Parcelforce, pedir entrega a um vizinho e solicitar que o motorista bata mais forte ou dê mais tempo para você chegar à porta. A definição de local seguro (safe place) também é feita no Royal Mail e vale para pacotes da Parcelforce.

**Entrega não realizada.** O rastreamento mostra `Delivery attempted` ou `Delivered to Neighbour/Reception by` com o nome de quem recebeu. Se ainda não souber onde o pacote está, ligue para o atendimento do Royal Mail (abaixo).

## Rastreamento Parcelforce sem atualizar, atrasado ou extraviado

**Sem movimentações ainda.** O remetente pode ter gerado o código antes de despachar o volume: `Sender has despatched item` indica que a Parcelforce ainda não está com ele. Confira o código e tente novamente depois. Mais em [por que o rastreamento para de atualizar](guide:tracking-not-updating).

**Atrasada, extraviada ou danificada.** Em compras on-line, fale primeiro com o vendedor: o Citizens Advice lembra que isso costuma trazer resultado melhor, permitindo pedir reembolso ou reenvio. Para indenização junto à transportadora, consulte como fazer o pedido no próprio site da Parcelforce.

**Taxas alfandegárias.** A Parcelforce conduz o desembaraço aduaneiro de pacotes vindos de fora do Reino Unido e entra em contato quando há IVA, imposto ou outras taxas a pagar, enviando uma cobrança com o valor exato ([mais sobre alfândega](guide:customs)). Normalmente guarda a encomenda por cerca de **3 semanas**; se não for paga nesse prazo, o volume volta ao remetente. Dúvidas sobre as mercadorias também devem ser tratadas com a Parcelforce.

> Não tem certeza se um e-mail ou SMS cobrando taxa por uma encomenda é verdadeiro? Não clique nos links nem informe dados bancários. Encaminhe e-mails suspeitos para `report@phishing.gov.uk` e SMS suspeitos para `7726`, gratuitamente.

## Telefone e atendimento da Parcelforce

O Citizens Advice direciona destinatários da Parcelforce ao Royal Mail para ajustes de entrega e locais seguros:

- **Atendimento ao cliente do Royal Mail:** `03457 740 740`, segunda a sexta das 8h às 18h30, sábado das 8h às 15h e domingo das 9h às 14h (horário do Reino Unido). **Relay UK:** `18001` seguido de `0345 266 0075`.
- **Aplicativo do Royal Mail:** alteração de data, vizinho, local seguro e necessidades especiais também para encomendas Parcelforce.

Para um pedido de indenização ou uma reclamação, acesse o próprio site da Parcelforce.

## Acompanhe encomendas da Parcelforce com o Peek

O [Peek](/) identifica a Parcelforce de imediato em códigos no estilo `EA482917367GB` com dígito verificador válido e em códigos `PB` de 14 caracteres. Um número de remessa isolado não é rastreado. Links de rastreamento da Parcelforce com `trackNumber=` funcionam, assim como links do Royal Mail terminados em `#/tracking-results/` seguido de um código Parcelforce.

O Peek consulta a Parcelforce no site de rastreamento do Royal Mail abrindo a página em um navegador real no servidor, portanto a verificação demora um pouco mais e pode falhar caso o site bloqueie acessos automatizados; nessas horas o Peek recorre a serviços universais de rastreamento, que têm poucos dados sobre a Parcelforce.

O Peek mostra o status, cada movimentação com o local (sem códigos postais), a data prevista de entrega, o horário de entrega e o país de destino. Com a encomenda aberta, ou no dia da entrega com notificações ligadas, verifica até a cada 10 minutos (até a cada 5 minutos a partir das 6h no horário do Reino Unido — ou meia hora antes de abrir a janela de entrega, quando a Parcelforce informar uma — no dia em que a Parcelforce marcar saída para entrega, enquanto o próprio rastreador da Parcelforce responder). Nos demais momentos, e após dois dias sem novidades (ou 12 horas em "Etiqueta criada" ou "Recebida pela transportadora"), consulta a cada hora, e a cada 6 horas depois de sete dias sem novidades em "Etiqueta criada". O Peek põe cada movimentação no mapa e pode notificar você quando o status muda. Os status mantêm o texto em inglês do Royal Mail, e movimentações do exterior sem fuso horário aparecem sem horário. Para exportações com código `EA482917367GB`, o Peek também pode consultar o correio nacional do destino informado pela Parcelforce (ou, se ela não informar nenhum, o do país onde o Peek localiza você, fora o Reino Unido) e continuar lá assim que surgir uma movimentação tão recente quanto a última da Parcelforce.

## Perguntas sobre o rastreamento da Parcelforce

### A Parcelforce faz parte do Royal Mail?

Sim. O GOV.UK inclui a Parcelforce no grupo Royal Mail, e seus envios são acompanhados na página "Track your item" do Royal Mail.

### Por que meu código da Parcelforce não é reconhecido?

A encomenda pode ainda não ter chegado à Parcelforce ou o código pode ter erro de digitação. Além disso, um número de remessa isolado não é rastreado: peça ao remetente o código `PB` de 14 caracteres de cada volume e tente novamente mais tarde.

### Por quanto tempo a Parcelforce guarda uma encomenda com taxas alfandegárias?

Cerca de 3 semanas. Se a cobrança não for paga nesse período, a encomenda volta ao remetente.

### Posso escolher outro dia para uma entrega da Parcelforce?

Sim, pelo aplicativo do Royal Mail ou pelo atendimento ao cliente do Royal Mail. Também é possível solicitar entrega a um vizinho.

:::sources
- [GOV.UK: Check when you can account for import VAT on your VAT Return](https://www.gov.uk/guidance/check-when-you-can-account-for-import-vat-on-your-vat-return) – Parcelforce parte do grupo Royal Mail
- [Citizens Advice: Telling delivery companies about your delivery or collection needs](https://www.citizensadvice.org.uk/consumer/post-and-parcels/telling-delivery-companies-about-your-delivery-or-collection-needs/) – app e atendimento do Royal Mail para a Parcelforce, outra data, vizinho, local seguro, bater à porta, telefone e horários
- [Citizens Advice: Claiming compensation from Royal Mail](https://www.citizensadvice.org.uk/consumer/post-and-parcels/claiming-compensation-from-royal-mail/) – número do Relay UK
- [Citizens Advice: Damaged, lost or delayed post or parcel](https://www.citizensadvice.org.uk/consumer/post-and-parcels/damaged-lost-delayed-post-or-parcel/) – vendedor primeiro, pedidos de indenização no site da transportadora
- [GOV.UK: Tax and customs for goods sent from abroad: Overview](https://www.gov.uk/goods-sent-from-abroad) – a Parcelforce faz o desembaraço das mercadorias, dúvidas sobre as mercadorias
- [GOV.UK: Tax and customs for goods sent from abroad: Tax and duty](https://www.gov.uk/goods-sent-from-abroad/tax-and-duty) – cobrança, cerca de 3 semanas, devolução ao remetente
- [GOV.UK: Avoid and report internet scams and phishing](https://www.gov.uk/report-suspicious-emails-websites-phishing) – 7726, report@phishing.gov.uk
- [UPU: S10 standard, Identification of postal items](https://www.upu.int/UPU/media/upu/files/postalSolutions/programmesAndServices/standards/S10-12.pdf) – dígito verificador antes do código do país
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – o que o Peek lê, status, formatos de código
:::
