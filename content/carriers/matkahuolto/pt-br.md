---
title: Rastreamento Matkahuolto: status, pontos de coleta e ajuda
description: Rastreie encomendas da Matkahuolto na Finlândia: onde achar o código, significado dos status em inglês, código de retirada, prazos de guarda, lockers e contato.
slug: matkahuolto-tracking
published: 2026-11-26
updated: 2026-11-26
---

A Matkahuolto é uma transportadora finlandesa de encomendas com pontos de retirada e armários (lockers) espalhados por toda a Finlândia, incluindo unidades em mercados K-market e quiosques R-kioski. Rastreie uma encomenda em matkahuolto.fi/tracking, em inglês, ou no aplicativo Paketit usando o ID do envio presente no e-mail da loja ou no SMS da Matkahuolto, ou cole o código na caixa acima.

## Código de rastreamento da Matkahuolto e onde encontrar

A Matkahuolto chama o código de shipment ID ou consignment ID (*lähetystunnus* em finlandês). Ele consta no e-mail de envio da loja e em todos os SMS ou e-mails da Matkahuolto. O código de retirada (pick-up code) enviado no aviso de chegada é outro número: serve para abrir o armário ou apresentar no balcão do ponto de coleta.

A Matkahuolto não publica especificações oficiais dos seus formatos. Estes são os padrões reconhecidos pelo catálogo do Peek (dígitos inventados):

| Código | O que é |
| --- | --- |
| `MH482910372FI` | `MH`, 9 dígitos e `FI`. |
| `MA7394028156342019482` | `MA` seguido de 19 dígitos. |
| `00373482917360582419` | Código de barras de encomenda de 20 dígitos (SSCC). O rastreador da Matkahuolto também aceita a versão de 18 dígitos, sem o `00`. |

Códigos iniciados por `JJFI`, bem como [códigos postais](guide:tracking-number-formats) terminados em `FI` com letras iniciais diferentes de `MH` (`RR482910372FI`), pertencem ao [Posti](carrier:posti), os correios da Finlândia. Se a Matkahuolto não encontrar o código, confira qual transportadora aparece no e-mail da loja.

Transferir uma encomenda para outro ponto de retirada gera um novo consignment ID. Uma remessa da GLS repassada à Matkahuolto na Finlândia exibe seu código da Matkahuolto no rastreamento da GLS como "Finland parcel number", começando por `00`.

Não tem o código? Se você possui cadastro na Matkahuolto ou usa o aplicativo Paketit, encomendas enviadas para seu telefone ou e-mail aparecem lá automaticamente; se alguma não aparecer, o remetente pode adicionar seus dados. Veja [como encontrar seu código de rastreamento](guide:find-tracking-number).

## O que significam os status de rastreamento da Matkahuolto

O rastreamento da Matkahuolto funciona em finlandês (padrão), sueco e inglês, trazendo data, hora e cidade em cada leitura. Os textos em inglês, em ordem:

| Status da Matkahuolto | O que significa para você |
| --- | --- |
| `Matkahuolto is waiting for the consignment from the sender` | O remetente [criou a remessa](guide:tracking-statuses); a Matkahuolto ainda não recebeu o pacote. |
| `Consignment is being processed` | Escaneada em um terminal. |
| `The consignment is on its way` | Em trânsito entre terminais. |
| `Your parcel has been sorted and will be taken next to the pickup point.` | Triada no último terminal; o próximo passo é seguir para o ponto de retirada. |
| `We will deliver the consignment to the pickup point during the day` | A caminho do seu ponto de retirada hoje. |
| `Your pick up point has changed` | Foi redirecionada para outro ponto, informado na própria linha. |
| `The shipment is available for pickup with the pickup code at the pick up point` | Pronta para retirada, indicando o nome do ponto e o último dia para buscar. |
| `We have sent the recipient a message about the arrival of the consignment (sms).` | Seu aviso de chegada foi enviado. Não representa movimentação física. |
| `The consignment has been delivered. Thank you for choosing Matkahuolto!` | Retirada no ponto de coleta ou no armário. |

**Ao longo do dia.** O primeiro ponto da rota pode não ser o seu, alerta a Matkahuolto, mas a encomenda deve ficar disponível para retirada no mesmo dia. Você recebe um aviso assim que ficar pronta.

## Pontos de retirada, armários e entregas residenciais da Matkahuolto

**Retirada.** Basta o código de retirada do aviso de chegada. Não é exigido documento de identidade, salvo quando o aviso indica entrega em mãos, normalmente para bebidas alcoólicas ou documentos. Qualquer pessoa com o código pode retirar, exceto nos armários externos controlados por aplicativo, que só abrem com o celular do próprio destinatário no app Paketit. Se o aviso não mencionar um armário, peça no caixa do ponto de coleta.

**Aviso de chegada.** Primeiro chega por notificação no app Paketit, depois por SMS ou WhatsApp, e por e-mail caso o remetente o tenha informado.

**Prazo de guarda.** 5 dias, após os quais volta ao remetente; 14 dias quando o aviso é enviado em papel por falta de telefone ou e-mail. É possível comprar de 1 a 16 dias extras no aplicativo ou, com cadastro, no site, salvo se o remetente bloquear a extensão. A página da GLS indica 10 dias para suas remessas; siga sempre a data limite do seu aviso.

**Mudar o ponto de retirada.** Uma vez gratuitamente no aplicativo: antes de a Matkahuolto receber o pacote, segundo o FAQ, ou quando chega o primeiro aviso de chegada, segundo a página em sueco sobre recebimento. Não é possível mudar durante o transporte. Depois do aviso de chegada, você pode pagar por uma transferência.

**Entregas residenciais (Home Parcels)** ocorrem em dias úteis das 8:00 às 18:00 no horário da Finlândia; Business Parcels e encomendas [FedEx](carrier:fedex) são entregues em dias úteis das 8:00 às 16:00, sem aviso prévio. O aviso informa o dia previsto, que pode ser remarcado para um dos cinco dias úteis seguintes no dia em que a mensagem chega. O pacote é deixado em local seguro e protegido junto à porta; se isso não for possível e você não atender, ele segue para um ponto de retirada próximo no dia útil seguinte.

**Åland.** Desde 1º de outubro de 2026, o Åland Post entrega as encomendas da Matkahuolto em Åland, integrando suas agências, agentes postais, lockers e entregas em domicílio à rede da Matkahuolto.

**Devoluções.** Gere um código de ativação com o botão Palauta no app ou no site, escreva-o na caixa com seu nome e telefone e entregue em um armário ou ponto de coleta.

## Encomenda da Matkahuolto atrasada, sem atualização ou danificada

A maioria das encomendas chega no dia seguinte ao despacho, afirma a Matkahuolto, e todas em até três dias após o recebimento pela transportadora (três dias úteis no FAQ em finlandês). Remessas do exterior podem passar por outras transportadoras antes e levar pouco mais de uma semana para chegar à Matkahuolto.

**Sem atualização.** O rastreamento pode pausar, por exemplo enquanto a encomenda aguarda saída ou passa por conferência de embalagem. Se continuar parada, a Matkahuolto pede que você fale com o remetente. Veja [rastreamento que não atualiza](guide:tracking-not-updating).

**Sem aviso de chegada.** Geralmente decorre de erro de digitação nos seus dados de contato: verifique a caixa de spam e peça ao remetente para corrigir.

**Danificada ou perdida.** Avise a loja ou o remetente imediatamente, enviando fotos. A empresa que enviou a encomenda registra a reclamação junto à Matkahuolto; entre pessoas físicas, cabe ao remetente. Registre danos visíveis no momento da retirada; do contrário, reporte por escrito em até 7 dias, ou em até 30 dias como consumidor. A responsabilidade da Matkahuolto é de €20 por kg, ou o valor da mercadoria se menor.

## Atendimento ao cliente e telefone da Matkahuolto

Conforme listado nas páginas em inglês da Matkahuolto:

- **Serviços de Encomendas:** `0600 11300`, segunda a sexta das 8h às 18h (horário da Finlândia), €2,49 por minuto mais tarifa local.
- **Suporte de Serviços de Encomendas:** `+358 20 33 6161`, segunda a sexta das 8h às 10h e das 14h às 16h (horário da Finlândia), tarifa local, para armários, lockers por app e entregas residenciais.
- **Chat** com o chatbot Mauno na [página de atendimento de encomendas](https://www.matkahuolto.fi/customer-service/parcels) ou no app Paketit, com possibilidade de atendimento humano.
- **Formulário de contato** em finlandês em matkahuolto.fi/palautelomake. Não há e-mail publicado para destinatários.

> A Matkahuolto alerta contra mensagens falsas, nomes de remetente forjados e sites parecidos como matkahuoltoo.fi. A empresa afirma que nunca intermedia pagamentos nem atua como gateway de pagamento; o único link de pagamento enviado destina-se a remetentes, para custos de devolução. Confira qualquer aviso digitando o ID do envio no rastreamento oficial ou no app; se pagou, contate seu banco.

## Acompanhe uma encomenda da Matkahuolto com o Peek

O [Peek](/) consulta Matkahuolto, Posti e [Chronopost](carrier:chronopost) para códigos `MH…FI` e exibe "Matkahuolto está com esta encomenda." quando apenas a Matkahuolto reconhece o número; se o Posti também o conhecer, você escolhe. Códigos `MA` vão exclusivamente para a Matkahuolto. Em códigos de barras de 18 ou 20 dígitos com o prefixo `64` da Finlândia ou `73` da Suécia após o primeiro dígito (após `00` e um dígito na forma de 20 dígitos), o Peek também consulta a Matkahuolto junto com as demais transportadoras compatíveis e a seleciona quando apenas ela reconhece. Se o [Posti](carrier:posti) também reconhecer um código `64`, ou a [Bring](carrier:bring) um código `73`, o Peek seleciona essa outra transportadora: troque para Matkahuolto em "Alterar". Se outra empresa, como a PostNord, também o reconhecer, você escolhe. Demais códigos de barras não são enviados automaticamente à Matkahuolto; selecione-a em "Alterar". Links de matkahuolto.fi com `parcelNumber=` atribuem qualquer número à Matkahuolto, mas o Peek só consegue consultar seus códigos `MH…FI` e `MA`, códigos postais e códigos SSCC com dígito verificador válido; qualquer outro falha.

As leituras da Matkahuolto não trazem fuso horário: o Peek interpreta cada uma no horário da sua cidade (horário da Finlândia dentro da Finlândia; linhas sem local usam o fuso da linha vizinha ou horário finlandês) e lista o histórico próprio da Matkahuolto. Os status mantêm o texto em inglês da Matkahuolto. O Peek exibe o ponto de retirada ou locker com cidade e rua e permite escolher alertas para "Todas as movimentações", "Etapas importantes" ou "Somente entrega".

O Peek não recebe da Matkahuolto dia previsto de chegada nem peso, omite serviços adicionais e códigos postais e não separa o último dia de retirada (que consta na linha de status e no aviso de chegada). Em códigos `MH…FI` rastreados fora da Finlândia, o Peek também consulta o correio daquele país quando suportado (exceto Países Baixos e Åland) e passa a acompanhá-lo lá assim que houver uma leitura datada não anterior à mais recente da Matkahuolto, preservando o histórico anterior; para códigos `MA` e códigos de barras, o Peek não transfere para outra transportadora.

Enquanto a encomenda está aberta no Peek, ou no dia da entrega com notificações ligadas, o Peek consulta até a cada 10 minutos, e a cada 2 minutos a partir das 6h no relógio da última leitura (horário da Finlândia na Finlândia) no dia em que sai para entrega, enquanto o rastreador da Matkahuolto responder. Após dois dias sem novidades (12 horas ainda em "Etiqueta criada" ou "Recebida pela transportadora"), verifica de hora em hora, e a cada 6 horas depois de sete dias em "Etiqueta criada". Após quatro dias sem movimentação em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito", exibe "Sem atualização desde …", a nota "É comum uma encomenda ficar sem atualizações em voos longos, na alfândega ou no fim de semana. O Peek continua consultando Matkahuolto." e o link "Pedir ajuda a Matkahuolto".

## Perguntas sobre o rastreamento da Matkahuolto

### A Matkahuolto é confiável?

Sim, a Matkahuolto é uma transportadora finlandesa legítima. Trate qualquer mensagem cobrando pagamento em nome dela como golpe: a Matkahuolto afirma que nunca intermedia pagamentos.

### Posso rastrear uma encomenda da Matkahuolto em inglês?

Sim. O rastreamento da Matkahuolto oferece versão em inglês em matkahuolto.fi/tracking, e o idioma do aplicativo Paketit pode ser alterado nas configurações.

### Posso rastrear uma encomenda da Matkahuolto sem o código?

Sim, se você for cadastrado ou usar o app Paketit e o remetente tiver informado seu telefone ou e-mail: o pacote aparece lá automaticamente. Caso contrário, peça o ID do envio à loja.

### Quanto tempo leva uma encomenda da Matkahuolto?

Em geral de 1 a 2 dias úteis para armários e pontos de retirada, e no máximo três dias após a Matkahuolto receber o pacote (três dias úteis segundo o FAQ em finlandês). Remessas do exterior podem levar pouco mais de uma semana para chegar à Matkahuolto.

:::sources
- [Matkahuolto: How to receive a parcel?](https://www.matkahuolto.fi/customer-service/parcels/how-to-receive-a-parcel) – código de retirada, documento, avisos, mudança de ponto, entregas residenciais, danos e telefones
- [Matkahuolto: Frequently asked questions](https://www.matkahuolto.fi/frequently-asked-questions) – entregas residenciais, tentativas falhas e telefones
- [Matkahuolto: Track your parcel](https://www.matkahuolto.fi/customer-service/parcels/track-your-parcel) – visualização do rastreamento, prazos, pausas e contato com o remetente
- [Matkahuolto: Receive your parcel easily](https://www.matkahuolto.fi/packages/receive-a-parcel) – 1 a 2 dias úteis, avisos e 1 a 16 dias extras de guarda
- [Matkahuolto: Pakettien säilytysaika muuttuu](https://www.matkahuolto.fi/news/pakettien-sailytysaika-muuttuu-tavoitteena-entistakin-sujuvampi) – guarda de 5 e 14 dias e prorrogações
- [Matkahuolto: Domestic parcels](https://www.matkahuolto.fi/packages/domestic-parcels) – guarda de 5 dias e retorno ao remetente
- [Matkahuolto: Parcel lockers and parcel points in Finland](https://www.matkahuolto.fi/packages/service-point-network) – rede de pontos e lockers por aplicativo
- [Matkahuolto: Transport damage guide for consumers](https://www.matkahuolto.fi/packages/damage-in-transit) – quem reclama, prazos e fotos
- [Matkahuolto: Kuljetusvahinko-ohje kuluttajille](https://www.matkahuolto.fi/paketit/kuljetusvahingot) – prazos e indenização de €20 por kg
- [Matkahuolto: About scam messages](https://www.matkahuolto.fi/packages/about-scam-messages) – mensagens falsas, remetente legítimo e ID do envio
- [Matkahuolto: Return the arrived parcel easily](https://www.matkahuolto.fi/packages/return-parcel) – devoluções com código de ativação
- [Matkahuolto: Paketit app](https://www.matkahuolto.fi/packages/paketit-app) – idioma do app e chat
- [Matkahuolto: Customer service for parcel services](https://www.matkahuolto.fi/customer-service/parcels) – chatbot Mauno
- [Matkahuolto: Usein kysyttyä](https://www.matkahuolto.fi/usein-kysyttya) – preço da central, três dias úteis, Business Parcels e FedEx das 8h às 16h e formulário de contato
- [Matkahuolto: Seuraa pakettisi matkaa](https://www.matkahuolto.fi/asiakaspalvelu/paketit/seuraa-paketin-matkaa) – pouco mais de uma semana do exterior e link de pagamento de devolução para remetentes
- [Matkahuolto: Track the parcel](https://www.matkahuolto.fi/tracking) – rastreamento em inglês, finlandês e sueco e textos de status
- [Matkahuolto: Ta emot paket](https://www.matkahuolto.fi/paket/ta-emot-paket) – em sueco: mudança gratuita de ponto no primeiro aviso de chegada
- [Matkahuolto: Spåra ditt paket](https://www.matkahuolto.fi/kundservice/paket/spara-ditt-paket) – em sueco: todas as encomendas em até três dias
- [Matkahuolto: Åland Post blir Matkahuoltos nya transportpartner på Åland](https://www.matkahuolto.fi/news/%C3%85land%20Post%20blir%20Matkahuoltos%20nya%20transportpartner%20p%C3%A5%20%C3%85land) – em sueco: Åland Post em Åland desde 1º de outubro de 2026
- [GLS Finland: Lähetysseuranta](https://gls-group.com/FI/fi/laehetysseuranta.html) – pontos Matkahuolto, número finlandês e prazo de 10 dias
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – o que o Peek lê da Matkahuolto
:::
