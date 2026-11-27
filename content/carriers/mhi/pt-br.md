---
title: Rastreamento MHI: status Trackmail e quem entrega
description: Recebeu um código MHI de uma loja do Reino Unido? Veja onde rastrear, status da Trackmail, qual transportadora entrega no seu país e como contatar.
slug: mhi-tracking
published: 2026-11-26
updated: 2026-11-26
---

A MHI (Mail Handling International) é uma empresa de Bristol que envia pedidos de lojas online do Reino Unido para compradores no exterior. As lojas remetem seus pacotes para o centro de processamento da MHI em Bristol; a MHI faz a exportação e entrega cada volume a uma transportadora ou correio do seu país, que realiza a entrega final sob seu próprio número. Rastreie o código MHI no site de rastreamento da MHI, o [Trackmail](https://www.trackmail.co.uk/), ou na caixa acima. O número vem da loja: a MHI fornece aos seus clientes um código de rastreamento para cada pacote, portanto procure no e-mail de envio ou nos detalhes do pedido.

## Código de rastreamento MHI: como ele se parece

A MHI não publica um formato para compradores. Os formatos que seus códigos assumem, com dígitos fictícios:

| Código | O que é |
| --- | --- |
| `MHI482W736405829173` | `MHI`, três dígitos, depois `W` e 12 dígitos: MHI. |
| `MHI482WBB736405829` | `MHI`, três dígitos, depois `WBB` e 9 dígitos: MHI. |
| `MHI-482-P736405829` | `MHI`, três dígitos, depois `P` e 9 dígitos: MHI. As etiquetas imprimem este formato com hifens. |
| `MHI482736405829173` | `MHI`, três dígitos e mais 12 dígitos: MHI. |
| `RR482917367US` | Duas letras, 9 dígitos e duas letras: número de um correio para o mesmo item, mostrado no Trackmail em alguns envios sem rastreamento completo. |
| Qualquer outro formato | O número próprio da transportadora local, exibido no Trackmail após "Tracking number:" em itens rastreados. |

Os três dígitos após `MHI` identificam a conta da loja na MHI. Digite um número `P` no Trackmail com os hifens exatamente como impressos: o Trackmail só localiza esses códigos com eles. Um mesmo pacote pode acabar com dois códigos: o da MHI e o da transportadora local. Saiba mais em [formatos de código de rastreamento](guide:tracking-number-formats).

## Status de rastreamento da MHI explicados

O Trackmail funciona em inglês. Acima do histórico ele indica o serviço contratado pela loja: "The seller requested this item be shipped via the following service:", seguido por `TRACKED` ou `UNTRACKED`. Abaixo vêm os eventos, do mais recente ao mais antigo, cada um com data, hora e descrição, quase sempre com categoria e local: primeiro a leitura de exportação da própria MHI, depois as leituras da transportadora local nas palavras da MHI. Na ordem em que uma encomenda passa por elas:

| O Trackmail informa | O que significa para você |
| --- | --- |
| `Your order has been processed at the UK export facility. It is now on route to the receiver.` | A MHI recebeu o pacote no Reino Unido e o despachou para o exterior. Em envios sem rastreamento completo, muitas vezes esta é a única linha. |
| `Shipment information sent to last mile service provider` | A transportadora no seu país recebeu os dados do pacote, mas ainda não o objeto físico. |
| `Notification sent to recipient` | A transportadora avisou você de que a encomenda está a caminho. |
| `Handed Over to Delivery Partner` | A transportadora local assumiu o pacote. |
| `Arrived at sorting center` | Leitura em centro de triagem da rede da transportadora. |
| `Dispatched from sorting center` | Saiu desse centro de triagem. |
| `Assigned to Transport` | Em veículo circulando entre depósitos. |
| `Shipment at final delivery center` | Chegou ao depósito responsável pela entrega final. |
| `Delivery scheduled in the final delivery depot` | Entrou na rota de entrega do dia. |
| `Delivery attempted` | A entrega não foi realizada: ninguém recebeu o pacote. |
| `Arrived at Pick-up Point` | Aguardando você em um ponto de retirada. |
| `Successfully delivered` | Entregue ou retirado. |

Leia o texto do evento, não a categoria ao lado. O Trackmail classifica `Shipment at final delivery center` e `Arrived at Pick-up Point` sob "Out for delivery", embora o primeiro venha antes da saída em rota e o segundo indique que você precisa ir retirar o pacote. Também classifica `Delivery attempted` sob "Delivery", como se fosse uma entrega concluída. Rotas e tentativas podem se repetir por vários dias.

Os horários no Trackmail não trazem fuso horário: a leitura da própria MHI está no horário do Reino Unido e as da transportadora no horário local dela.

Para um número desconhecido, o Trackmail mostra uma única linha começando com `No details found for Tracking Code, or Tracking Code not recognised.`, acrescentando que se o pedido for recente a MHI pode ainda não ter os dados.

## Entrega MHI: quem traz sua encomenda

A MHI não faz entregas no exterior diretamente. Ela informa trabalhar com mais de 100 transportadoras para alcançar 252 países e territórios. No centro de Bristol, o código de barras de cada pacote é lido e o sistema da MHI escolhe a transportadora de última milha conforme o país de destino. As lojas podem contratar modalidades rastreadas (*tracked*), não rastreadas (*untracked*) ou expressas (*express*), então quem entrega depende de onde você mora e do serviço pago pela loja.

**Itens rastreados (`TRACKED`).** O Trackmail pode mostrar o número da transportadora local após "Tracking number:", junto com um link "View carrier tracking" para o site dela. Para dúvidas, o Trackmail orienta usar esse link ou falar com o varejista.

**Itens sem rastreamento completo (`UNTRACKED`).** O Trackmail avisa que há informações limitadas e muitas vezes exibe apenas a leitura de exportação da MHI. Em alguns casos ele fornece um "local poster number": segundo o Trackmail, ao informar esse número ao correio local ou agência postal, eles podem ajudar com mais detalhes. Tente consultá-lo também no rastreador do seu correio nacional: [USPS](carrier:usps), [Canada Post](carrier:canada-post), [Australia Post](carrier:australia-post) ou An Post.

Avisos, tentativas, reentregas e pontos de retirada seguem as regras da transportadora local: confira o rastreamento dela via "View carrier tracking" e veja [entregas não realizadas](guide:missed-delivery).

## Encomenda MHI sem atualização, perdida ou devolvida

**`No details found…`.** Confira o código e digite números `P` com os hifens. Se comprou há pouco tempo, a MHI pode ainda não ter os dados: tente novamente mais tarde.

**Apenas a leitura de exportação.** Em um serviço não rastreado, essa pode ser a única linha do Trackmail: use o "local poster number" no correio local se houver um, ou pergunte à loja. Em um serviço rastreado, a transportadora local pode ainda não ter lido o pacote; assim que o código dela aparecer, o rastreador próprio dela pode estar mais atualizado. Veja [por que o rastreamento para de atualizar](guide:tracking-not-updating).

**Entregue, mas não chegou.** Quem realizou a entrega foi a transportadora local: consulte o rastreamento dela em "View carrier tracking" e depois contate a loja. Veja [entregue mas não recebido](guide:delivered-not-received).

**Perdida ou danificada.** Fale com a loja. A MHI atende a loja, não publica processo de pedido de indenização para destinatários, e o próprio Trackmail encaminha você ao varejista. Veja [encomendas perdidas](guide:lost-parcel).

**Alfândega.** A MHI oferece às lojas diversas modalidades aduaneiras, incluindo DDP (*Delivered Duty Paid*), portanto a existência de taxas a pagar depende da opção escolhida pela loja. Veja o que pode incidir em [encomendas retidas na alfândega](guide:customs).

**Devolvida.** O prazo que um pacote aguarda sem retirada antes de retornar segue a regra da transportadora local; consulte o rastreamento dela. Se voltar ao remetente, peça reembolso ou reenvio à loja. Veja [devolvida ao remetente](guide:returned-to-sender).

## Telefone e atendimento ao cliente da MHI

A MHI divulga:

- **Dúvidas de rastreamento:** e-mail `tracking@mhi.co`, informado na página de entregas de e-commerce e na página de contato da Nexae (sua plataforma de envios).
- **Telefone:** `+44 (0) 117 977 6655`, número do Reino Unido exibido com o endereço em Bristol, 82 Feeder Road, BS2 0TQ. A MHI não informa horário de atendimento nem indica atendimento a consultas de encomendas por telefone.
- **Formulário Enquiries:** para assuntos gerais. A MHI avisa que dúvidas sobre rastreamento enviadas por esse formulário não serão atendidas nem encaminhadas.

A orientação da MHI aos compradores é consultar o Trackmail ou falar com o vendedor ou marketplace. A loja é cliente da MHI e pode cobrá-la; para entrega não realizada ou retirada, procure a transportadora exibida no Trackmail.

## Rastreie uma encomenda MHI com o Peek

O [Peek](/) reconhece códigos MHI pelo formato nas quatro séries acima, com ou sem hifens. Etiquetas antigas com apenas dois dígitos após `MHI`, ou oito dígitos no final, não são consultadas: verifique essas diretamente no Trackmail. Você também pode colar um link do Trackmail contendo o código; o Peek o lê como MHI quando o número segue o formato da MHI.

O Peek consulta o Trackmail e mostra o status e o histórico, com o local de cada leitura no mapa. O Trackmail não fornece data prevista de entrega, peso nem comprovante de entrega, portanto o Peek não recebe esses dados da MHI e omite seu endereço e o código postal contido no link da transportadora.

Quando o link do Trackmail aponta para uma transportadora que o Peek conhece, a página do pacote exibe "Entrega por" seguido dessa transportadora e do código dela. Enquanto essa transportadora tiver apenas o anúncio da encomenda, sem leitura própria, a página mostra "Anunciada a …, ainda não recebida". Se o Peek ler diretamente o rastreador dessa transportadora, consulta com o código dela e passa a acompanhá-la assim que surgir progresso mais recente. Transportadoras desconhecidas pelo Peek não são exibidas: use "View carrier tracking" no Trackmail. Em envios sem rastreamento completo com um "local poster number", o Peek pode testar esse código em um correio nacional — geralmente o do país de onde você adiciona o pacote — e alternar da mesma forma. Porém nem todos os correios entram nisso: um código `RR…` terminado em `AU` adicionado na Austrália não é encaminhado à Australia Post, então acompanhe diretamente no site da Australia Post.

Enquanto você mantém a encomenda aberta no Peek, ou no dia da entrega com notificações ativadas, o Peek verifica até a cada 10 minutos, ou a cada 2 minutos a partir das 6h (horário local) no dia em que sai para entrega, enquanto o Trackmail (ou o rastreador da transportadora que assumiu) responder; via serviços de rastreamento, a cada 15 minutos. Após dois dias sem novidades (12 horas enquanto ainda estiver em "Etiqueta criada" ou "Recebida pela transportadora"), consulta de hora em hora, e a cada 6 horas depois de sete dias em "Etiqueta criada".

Quando a leitura mais recente tem quatro dias e o pacote segue em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito", o Peek sinaliza a data dessa leitura, como "Sem atualizações desde seg 12 out". A página então mostra "É comum uma encomenda ficar sem atualizações em voos longos, na alfândega ou no fim de semana. O Peek continua consultando MHI." Como a MHI não tem página de ajuda cadastrada no Peek, o link "Pedir ajuda a …" aparece apenas quando o Peek passa a acompanhar a transportadora local e ela possui essa página. Após três semanas sem novas leituras, mostra "Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, fale com o vendedor. O Peek continua consultando MHI."

O Peek pode notificar sobre "Cada movimentação", "Etapas importantes" ou "Só a entrega". Ele não pode entrar em contato com a MHI, a transportadora local nem a loja em seu nome.

## Perguntas sobre o rastreamento da MHI

### Onde está minha encomenda MHI?

A linha mais recente no Trackmail indica a etapa, e as leituras da transportadora mostram a cidade. Antes de `Handed Over to Delivery Partner`, o pacote está com a MHI ou a caminho do seu país; depois disso, está com a transportadora local, cujo código aparece em itens rastreados após "Tracking number:" no Trackmail.

### O que significa "processed at the UK export facility"?

Significa que a MHI processou a encomenda em sua unidade de exportação no Reino Unido e a enviou em direção a você. É a leitura própria da MHI e, em serviços não rastreados, muitas vezes é a única exibida.

### O site trackmail.co.uk é confiável?

Sim. Trackmail é o site de rastreamento da MHI: o link "Track my Parcel" no site da própria MHI leva diretamente para lá. O registro britânico Companies House lista a Mail Handling International Limited como empresa ativa, constituída em 1996, no endereço de Bristol informado pela MHI.

### A MHI tem telefone de atendimento ao cliente?

Ela divulga o número `+44 (0) 117 977 6655`, mas para rastreamento pede que você escreva para `tracking@mhi.co` ou fale com a loja. Para a entrega final em si, procure a transportadora informada no Trackmail.

:::sources
- [MHI: Home](https://www.mhi.co/) – link Track my Parcel para o Trackmail, orientação de consultas ao vendedor, formulário geral não trata rastreamento, 100+ transportadoras, 252 países e territórios, serviços tracked/untracked/express, DDP, endereço e telefone
- [MHI: eCommerce Delivery](https://www.mhi.co/ecommerce-delivery) – centro de Bristol, escolha de transportadora por país, códigos para lojas, exportação e `tracking@mhi.co`
- [Nexae: Contact Us](https://nexae.co.uk/ContactUsHomepage) – `tracking@mhi.co` e vínculo da Nexae com a MHI
- [MHI: Trackmail](https://www.trackmail.co.uk/) – site de rastreamento da MHI, idioma inglês e contato de rastreamento
- [Companies House: Mail Handling International Limited](https://find-and-update.company-information.service.gov.uk/company/03241562) – ativa, constituída em 23 de agosto de 1996, sede em 82 Feeder Road, Bristol
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formatos de código, textos e categorias do Trackmail, linha de serviço, link da transportadora, local poster number, mensagem de número desconhecido, fusos, o que o Peek exibe e qual transportadora ou correio consulta
:::
