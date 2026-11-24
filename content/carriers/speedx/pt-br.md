---
title: Rastreamento SpeedX: status, tentativas de entrega e contato
description: Rastreie um pacote da SpeedX nos EUA: formato do código SPX, o que significam os status, entregas sem sucesso, fotos de entrega e como falar com o suporte.
slug: rastreamento-speedx
published: 2026-11-24
updated: 2026-11-24
---

A SpeedX (Speed Xpress Inc., com sede em New Hyde Park, Nova York) é uma transportadora de última milha que entrega compras online na porta dos clientes nos Estados Unidos. Rastreie uma encomenda em [tracking.speedx.io](https://tracking.speedx.io/) ou cole o código na caixa acima. O código vem da loja onde você comprou: a SpeedX gera a etiqueta para o lojista, então a loja recebe o número antes mesmo de a SpeedX estar com o pacote.

## Código de rastreamento da SpeedX: formato e onde encontrar

A SpeedX informa que todos os seus códigos começam com `SPX`. Na prática, trata-se de `SPX`, três letras e depois 12 ou 18 dígitos. Veja os formatos com caracteres fictícios:

| Formato (fictício) | O que é |
| --- | --- |
| `SPXLGA482910573649` | SPX, 3 letras e 12 dígitos (18 caracteres) |
| `SPXLGA482910573649182736` | SPX, 3 letras e 18 dígitos (24 caracteres) |

O Peek ignora letras minúsculas e espaços.

**Onde encontrar.** Procure no e-mail de confirmação de envio da loja ou na página do seu pedido. Se não encontrar, a SpeedX orienta perguntar ao varejista; [como encontrar um código de rastreamento](guide:find-tracking-number) lista outros lugares onde verificar. A SpeedX também pode enviar alertas por SMS quando a encomenda sai para entrega, com cadastro feito no próprio site.

**Códigos parecidos.** `SPX` seguido apenas de números não corresponde a nenhum dos dois formatos. `SPXPH` seguido de números também não: esse padrão pertence à SPX Express nas Filipinas, outra empresa. Se um código `SPX…` tiver um dígito a mais ou a menos, compare com o e-mail da loja. Os códigos da SpeedX nunca se sobrepõem aos da UniUni, da [GOFO](carrier:gofo) ou do [USPS](carrier:usps). Se o código ainda não aparecer no sistema, a SpeedX sugere confirmar com o vendedor: o pacote pode estar com outra transportadora de última milha.

## O que significam os status de rastreamento da SpeedX

A página de rastreamento da SpeedX funciona apenas em inglês. Estas são as mensagens pelas quais uma encomenda costuma passar, em ordem.

| Status da SpeedX | O que significa para você | O Peek mostra |
| --- | --- | --- |
| `Shipping Label Created` | A loja gerou a etiqueta. A SpeedX ainda não está com o pacote. | "Etiqueta criada" |
| `Pickup Requested` | A loja solicitou que a SpeedX colete o pacote. A SpeedX ainda não está com ele. | "Etiqueta criada" |
| `Parcel Picked Up` | O motorista da SpeedX coletou o pacote na loja. | "Recebida pela transportadora" |
| `Processing at Origin Facility` | A SpeedX recebeu o pacote e faz a triagem na primeira unidade. | "Em trânsito" |
| `In transit to destination facility` | Em deslocamento entre unidades da SpeedX. | "Em trânsito" |
| `Processing at Transit Facility` | Passando por triagem em uma unidade intermediária. | "Em trânsito" |
| `Arrival at Destination Facility` | Chegou à unidade que atende a sua região. | "Em trânsito" |
| `Processing at Designated Facility` | Em triagem local para entrar na rota de um entregador. | "Em trânsito" |
| `Out for Delivery` | O pacote já está com o motorista e a caminho hoje. | "Saiu para entrega" |
| `Attempted Delivery: No access` | O entregador não conseguiu chegar até a sua porta: portão, prédio residencial, obra ou mau tempo. | "Tentativa de entrega" |
| `Delivered` | Entregue. A página mostra onde foi deixado, como na portaria ou sala de correspondências, junto com uma foto. | "Entregue" |
| `Waiting for collection from Parcelshop` | Após a rota, a encomenda foi deixada em um ponto parceiro (parcelshop) aguardando sua retirada. | "Pronta para retirada" |
| `Self picked up from Parcelshop` | A encomenda foi retirada no ponto parceiro. | "Entregue" |
| `Parcel Void` | A SpeedX sinalizou um problema com o pacote. Fale com a loja. | "Precisa de atenção" |
| `Exception- Parcel not found at the sort facility` | O pacote não foi localizado na central de triagem. Fale com a loja. | "Precisa de atenção" |
| `Parcel Disposal` | A SpeedX registrou um problema sem detalhar o motivo. Pergunte à loja o que acontece agora. | "Precisa de atenção" |

Enquanto o rastreamento exibir apenas `Shipping Label Created`, a SpeedX ainda não recebeu o pacote: ele segue com a loja ou no depósito dela.

Uma tentativa também pode vir acompanhada de outros motivos, como `Attempted Delivery:Building Closed/Weekend/Holiday` para um comércio fechado; o Peek exibe isso igualmente como "Tentativa de entrega". Abaixo da tentativa, a SpeedX inclui uma observação indicando o próximo passo: `The package is undeliverable and schedule to redelivery` significa que haverá nova tentativa de entrega, e `The package is undeliverable and return to facility` indica retorno à unidade. O Peek mostra essa nota logo após o texto principal.

Quando o evento mais recente é um problema (`Parcel Void`, `Parcel Disposal` ou pacote não encontrado), a SpeedX o classifica em categorias como `Last Mile Intercept` ou `Last Mile Undelivered`. A SpeedX não explica publicamente essas classificações: fale com a loja.

## Entrega da SpeedX, tentativa de entrega e retirada

**Encomendas do exterior.** A SpeedX também entrega pedidos enviados de fora dos Estados Unidos. Seu serviço internacional recebe cargas nos principais hubs aeroportuários dos EUA e realiza o desembaraço aduaneiro americano com despacho próprio antes da triagem para sua rede de entrega. Sobre tributos e quem paga por eles, veja [encomendas paradas na alfândega](guide:customs).

**Fins de semana.** A SpeedX entrega aos sábados em toda a área que atende, das 8h às 20h no horário local, e aos domingos em algumas regiões, no mesmo horário. A empresa não oferece janelas de horário agendadas nem avisa quando o motorista está chegando.

**Assinatura e foto.** A maioria dos pacotes da SpeedX não exige assinatura; itens com restrição de idade, como bebidas alcoólicas, podem exigir um adulto de 21 anos ou mais. Se não houver ninguém em casa, o entregador deixa a encomenda e tira uma foto. Para vê-la, rastreie o pacote em speedx.io e informe o ZIP code de entrega.

**Instruções e endereço.** A SpeedX recebe instruções de entrega (senhas de portão, onde deixar o pacote, horário comercial, telefone) por meio do Miles, seu chatbot, informando o código de rastreamento. O entregador também recebe essas orientações, mas não é possível falar diretamente com ele. A SpeedX consegue corrigir o nome da rua ou número do apartamento se for avisada a tempo; para alterar cidade, estado ou ZIP code, fale com o vendedor.

**Tentativa sem sucesso.** Se constar uma tentativa de entrega que você considera indevida, confira o endereço cadastrado no site da loja e envie correções ou instruções pelo chat. A SpeedX afirma que a nova tentativa deve ocorrer em 1 a 2 dias úteis. A empresa não publica quantas tentativas faz nem por quanto tempo mantém o pacote.

**Retirada.** A SpeedX informa que não é possível retirar encomendas em suas centrais: a empresa precisa fazer a entrega. Ainda assim, alguns pacotes acabam deixados em um ponto parceiro após a rota: nesse caso, o histórico mostra `Waiting for collection from Parcelshop`, e você retira a encomenda lá.

## Pacote da SpeedX parado, não entregue ou extraviado

**Sem movimentação.** A SpeedX orienta entrar em contato com o varejista se o status ficar três dias úteis sem atualização. Veja mais em [rastreamento que não atualiza](guide:tracking-not-updating).

**Marcado como entregue mas não encontrado.** A própria SpeedX reconhece que um pacote às vezes é marcado como entregue antes de realmente chegar, ou deixado em um ponto inesperado do imóvel. Confira a foto da entrega e procure na varanda da frente e dos fundos, na sala de correspondências ou caixa de correio, na garagem, nas laterais da casa e em locais abrigados, além de perguntar a outras pessoas da casa ou do prédio. Avise a SpeedX pelo Miles e depois fale com o vendedor para definir os próximos passos. Veja também [marcado como entregue mas não recebido](guide:delivered-not-received).

**Extravio, avaria ou endereço incorreto.** A SpeedX não aceita pedidos de indenização enviados por destinatários. Procure o vendedor para abrir reclamação, reembolso ou reenvio, com o número do pedido, o código de rastreamento e eventuais mensagens; em caso de avaria, o lojista pode pedir fotos. A SpeedX não publica prazo limite para isso.

**Devoluções.** Depois de entregue, qualquer devolução passa pela loja onde você comprou; a SpeedX não faz coletas no endereço do cliente. Se o pacote ainda não foi entregue, você pode pedir ao Miles que interrompa a entrega para que a SpeedX devolva a encomenda ao remetente.

## Telefone e chat do atendimento ao cliente da SpeedX

Estes são os canais da SpeedX para destinatários nos EUA listados em sua [página de suporte](https://speedx.io/customer-service-and-support/):

- **Chat:** Miles, o chatbot da SpeedX, todos os dias das 8h às 20h (horário do Leste dos EUA — ET). Abra o rastreamento, digite seu código e o chat aparece no canto inferior da tela.
- **Telefone:** 1-949-667-6769, das 9h às 21h (EST) de segunda a sexta e das 11h às 20h (EST) nos fins de semana. A mesma página também afirma que a SpeedX não oferece atendimento telefônico e direciona para o Miles, então espere ser encaminhado ao chat.
- **E-mail:** speedxops@speedx.io.

## Como rastrear pacotes da SpeedX com o Peek

O [Peek](/) identifica a SpeedX de imediato a partir de um código SPX em qualquer um dos dois formatos, ou de um link de tracking.speedx.io contendo o número.

Ele consulta diretamente a página de rastreamento da SpeedX e exibe o status e cada leitura com a cidade no mapa, no horário registrado pela SpeedX, além do horário de entrega e da data estimada quando a página da transportadora informa uma. O Peek omite seu endereço e a foto de entrega; consulte a página da SpeedX para ver a imagem. Quando a SpeedX adiciona uma nota dizendo onde o pacote foi deixado, a linha de entrega mantém o texto original; em links compartilhados, aparece apenas "Entregue". As demais linhas são exibidas como a SpeedX as escreve, inclusive `Out for Delivery`. Não há repasse para outra transportadora a acompanhar.

Enquanto você mantém a encomenda aberta, ou no dia da entrega com alertas ligados, o Peek verifica atualizações até a cada 10 minutos — ou até a cada 2 minutos a partir das 6h (horário local) do dia em que a SpeedX registra o pacote como saído para entrega — enquanto o rastreador da própria SpeedX responde (por meio de serviços de rastreamento, até a cada 15 minutos enquanto o pacote estiver aberto ou com alertas ligados); nos demais casos, a consulta é feita a cada hora. Ele também pode notificar você quando o status mudar. Após dois dias sem novidades (12 horas enquanto a encomenda ainda mostra "Etiqueta criada" ou "Recebida pela transportadora"), o Peek passa a consultar de hora em hora. Depois de quatro dias sem nova leitura enquanto o pacote está em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito", o Peek avisa que o rastreamento parou e exibe um link para a página de suporte da SpeedX ("Pedir ajuda a SpeedX"). Se a página da SpeedX não responder, outras fontes de rastreamento raramente têm dados de encomendas da SpeedX, então pode não surgir nenhuma novidade até o site voltar ao ar.

## Perguntas sobre o rastreamento da SpeedX

### O rastreamento da SpeedX é confiável?

Sim. A SpeedX é a marca da Speed Xpress Inc., uma transportadora de última milha fundada em 2022 em Nova York que realiza entregas para lojas online em todos os Estados Unidos.

### Posso retirar meu pacote da SpeedX pessoalmente?

Não nas instalações da SpeedX: a empresa não permite retiradas em suas centrais e precisa entregar o pacote. Porém, se o rastreamento mostrar `Waiting for collection from Parcelshop`, a encomenda foi deixada em um ponto parceiro após a rota e pode ser retirada lá.

### Quanto tempo a SpeedX leva para entregar?

A SpeedX não publica um prazo de entrega fixo para destinatários. Ela oferece serviços de entrega no dia seguinte e em dois dias para remetentes, e qualquer data estimada vem da loja — a SpeedX alerta que essa previsão pode não corresponder à data real.

### Por que meu código de rastreamento da SpeedX não funciona?

A página de rastreamento da SpeedX indica que o remetente pode ainda não ter enviado os dados do pacote, ou que o código está incorreto. A SpeedX sugere confirmar o número com o vendedor, pois o pacote pode estar com outra transportadora.

:::sources
- [SpeedX: Customer Service and Support](https://speedx.io/customer-service-and-support/) – prefixo SPX, glossário, SMS, fins de semana, assinatura, fotos, instruções, alteração de endereço, sem retirada em central, atrasos, pacotes marcados como entregues antes da hora, reclamações, devoluções, telefone, e-mail e horário do chatbot
- [SpeedX: Tracking](https://tracking.speedx.io/) – textos de status, observações, categorias, mensagem de código não encontrado e página apenas em inglês
- [SpeedX: Tech-Enabled Last Mile Delivery](https://speedx.io/) – transportadora de última milha, etiquetas emitidas pela SpeedX, cobertura por ZIP code nos EUA, serviços de 1 e 2 dias e fotos de comprovação de entrega
- [SpeedX: Cross-Border Shipping](https://speedx.io/cross-border-shipping/) – encomendas internacionais em hubs aeroportuários dos EUA, desembaraço aduaneiro próprio e triagem na rede da SpeedX
- [SpeedX: About Us](https://speedx.io/about-us/) – fundação em 2022 em Nova York e cidades atendidas nos EUA
- [SpeedX: Privacy Policy](https://speedx.io/privacy-policy/) – razão social Speed Xpress Inc. e endereço em New Hyde Park
:::
