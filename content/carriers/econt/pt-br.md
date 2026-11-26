---
title: Rastreamento Econt: número da remessa, status e agências
description: Rastreie uma encomenda Econt na Bulgária: onde encontrar o número da remessa, significado dos status em inglês, agências, Econtomats, indenizações e contatos.
slug: rastreamento-econt
published: 2026-11-26
updated: 2026-11-26
---

A Econt Express é uma transportadora búlgara fundada em 1993 que entrega na sua porta, em agências próprias e nos armários de encomendas Econtomat. Rastreie uma encomenda em "Track a shipment" em [econt.com](https://www.econt.com/en/services/track-shipment), pelo link do e-mail ou SMS da Econt, ou colando o número da remessa no campo acima. O código vem na notificação da Econt ou no e-mail de envio da loja.

## Código de rastreamento Econt: onde encontrar

A Econt chama esse código de número da remessa (shipment number) ou número da guia (waybill number). Os e-mails e SMS da Econt trazem um link que abre o rastreamento; o SMS é opcional e pago pelo remetente, então nem toda encomenda recebe um. Não recebeu mensagem? Pergunte à loja ou veja [onde mais encontrar seu código de rastreamento](guide:find-tracking-number).

A Econt não publica um formato, mas os números de exemplo no manual de integração XML dela têm 13 dígitos, e o Peek também lê os códigos da Econt como 13 dígitos, como o código inventado `1048291057364`.

Treze dígitos também servem para outras transportadoras, entre elas [DPD France](carrier:dpd-fr), a Ukrposhta da Ucrânia e a [FAN Courier](carrier:fan-courier) da Romênia; portanto confira qual transportadora aparece no e-mail da loja ou no SMS.

## O que significam os status de rastreamento da Econt

econt.com abre em búlgaro, com opção para trocar para inglês. Estes são os rótulos de movimentação da Econt em inglês como o Peek os lê, na ordem em que a encomenda passa por eles.

| Status da Econt | O que significa para você |
| --- | --- |
| `Awaiting delivery to Econt` | O remetente [criou a etiqueta](guide:tracking-statuses), mas ainda não entregou o pacote à Econt. |
| `Submitted to courier` | No início da lista: o entregador coletou o pacote com o remetente. Mais adiante: saiu para entrega. |
| `Arrival at an office` | Chegou à agência ou ao centro logístico da Econt indicado ao lado, possivelmente sua agência de retirada. |
| `Received by line-haul` | Está numa rota de transferência entre centros; o local indica o trajeto de cidade a cidade. |
| `Change delivery terms` | Alguém alterou as condições da entrega, como a agência. A encomenda não se moveu. |
| `Shipment forwarding` | Está sendo reencaminhada para outro destino. |
| `Failed delivery` | O entregador não conseguiu entregar e levou o pacote de volta a uma agência. |
| `Delivered` | Entregue a você ou retirada. |
| `Delivery return` | Está voltando para o remetente. |
| `Shipment destruction` | A encomenda está sendo destruída; o Peek mostra "Precisa de atenção". Procure a loja ou a Econt. |
| `Return receipt: Delivered` | O aviso de recebimento assinado chegou ao remetente; não traz nada novo sobre a sua encomenda. |

`Submitted to courier` é o status que mais confunde: as mesmas palavras indicam coleta no começo e entrega final no fim, então observe o que veio antes. `Failed delivery` traz um motivo entre parênteses. Uma linha `Change delivery terms` informa o que mudou.

No site em búlgaro, o resumo exibe `В офис` quando a encomenda aguarda em uma agência (pronta para retirada assim que a Econt indica a agência de guarda) e `Доставена` quando entregue. `Information` é apenas uma observação sem etapa.

## Entrega Econt, agências e Econtomats

**Na sua porta:** dentro de uma cidade, a Econt entrega no endereço até as 18h do dia útil seguinte; fora disso, conforme a programação dela. Um e-mail ou SMS avisa onde está a encomenda e qual agência ou entregador está com ela. O remetente pode contratar faixa horária de entrega ou entrega aos sábados mediante taxa.

**Em uma agência:** uma encomenda enviada para o dia útil seguinte fica disponível logo cedo, quando a agência abre. A Econt cobra taxa diária de armazenagem depois que o pacote fica mais de sete dias aguardando retirada.

**Em um Econtomat:** aberto 24 horas por dia, 7 dias por semana: selecione "Receive a shipment", escaneie o QR code ou digite os dois códigos PIN, pague com cartão no app da Econt se houver valor devido e retire o pacote do compartimento aberto. Os Econtomats comportam encomendas de até 61 x 44 x 37 cm.

**Alterar a entrega.** A Econt informa que você pode alterar destinatário, local e horário de entrega a qualquer momento: por telefone, pela notificação por e-mail ou no seu perfil e-Econt. As opções incluem outra agência, endereço, cidade ou dia, ou deixar com um vizinho.

**Conferência da mercadoria.** A tabela de preços da Econt inclui três opções gratuitas que o remetente pode ativar para você: Conferir (Review), Conferir e Testar (Review and Test) e Conferir, Testar e Escolher (Review, Test and Select).

## Encomenda Econt atrasada, extraviada ou danificada

A Econt informa entrega no dia útil seguinte em locais com agência Econt, em até três dias úteis onde não há agência e de um a dois dias úteis a partir de um Econtomat, dependendo do horário de envio. Dias sem movimentação? Veja [rastreamento que não atualiza](guide:tracking-not-updating) e depois fale com a Econt ou com a loja.

**Danificada, faltando itens ou extraviada.** Faça um pedido de indenização em "I want to file a claim" na página de atendimento ao cliente da Econt e avise a loja.

## Telefone e atendimento ao cliente da Econt

Canais da Econt na [página de atendimento ao cliente](https://www.econt.com/en/client-center):

- **Da Bulgária ou da Grécia:** 0 7001 7300, pelo valor de uma ligação local ou conforme seu plano telefônico.
- **De outros países:** +359 88 565 43 21, conforme seu plano telefônico.
- **Chat** com "Ask Ena".
- **Agências** e horários de funcionamento pelo localizador de agências na mesma página.

## Acompanhe uma encomenda Econt com o Peek

O [Peek](/) não consegue identificar uma encomenda da Econt apenas pelos 13 dígitos: ele consulta outras transportadoras, como a Delhivery, e só pergunta primeiro à própria Econt quando você adiciona o número pelo site estando na Bulgária ou no app em um celular configurado para a região da Bulgária. De qualquer outro lugar, o Peek pergunta à Econt por último, quando as outras transportadoras e os serviços de rastreamento não conhecem o código. Se nenhuma reconhecer o código, aparece "ainda não encontrada · o Peek continua verificando": escolha "Econt" em "Trocar". Ou cole um link de econt.com com `/services/track-shipment/`, `/tracking/` ou `/tracking/?num=` e o código, o que identifica a Econt na hora.

Em seguida, o Peek exibe seu próprio status, definido pelo resumo da Econt quando ele determina a etapa, o histórico com a agência de cada movimentação no mapa (uma agência escrita em búlgaro aparece pelo nome da cidade quando o Peek consegue localizá-la) e o horário da entrega. Enquanto a encomenda aguarda retirada, aparece o nome da agência da Econt onde está guardada. A Econt não informa dia previsto de entrega nem peso. A quantidade de volumes, o nome do entregador e as referências de remetente e pedido ficam de fora; o nome do destinatário aparece para quem adicionou a encomenda, em "Destinatário", mas fica oculto em links compartilhados.

As movimentações mantêm os rótulos em inglês acima; uma entrega assinada com nome na Econt aparece como `Delivered: <name>` para quem adicionou a encomenda, e nas palavras do Peek quando não há nome ou em link compartilhado; linhas `Return receipt: …` não alteram nada. Os horários são lidos no fuso de Sófia. Enquanto você mantiver a encomenda aberta, ou no dia da entrega com as notificações ativadas, o Peek verifica até a cada 10 minutos, ou a cada 2 minutos a partir das 6h no horário de Sófia no dia em que a Econt registra saída para entrega, enquanto o próprio rastreador da Econt responder (pelos serviços de rastreamento: a cada hora, a cada 3 horas após um dia sem novidades e até a cada 15 minutos no dia da entrega); fora isso, a cada hora. Ele pode avisar você sobre cada movimentação, etapas importantes ou apenas no dia da entrega. Após dois dias sem novidades (12 horas enquanto a encomenda ainda mostrar "Etiqueta criada" ou "Recebida pela transportadora"), o Peek passa a verificar a cada hora.

## Perguntas sobre o rastreamento Econt

### Posso ver o rastreamento da Econt em inglês?

No Peek, sim: ele mostra as movimentações da Econt com os rótulos em inglês da tabela acima. econt.com também tem versão em inglês, com "Track a shipment".

### Quanto tempo a Econt leva para entregar?

Na Bulgária, no dia útil seguinte em cidades com agência Econt, até três dias úteis onde não há agência e de um a dois dias úteis saindo de um Econtomat, conforme o horário de postagem, segundo a Econt.

### Como rastrear uma encomenda da Econt pelo número da guia?

Cole o código no campo acima e escolha "Econt" em "Trocar": o Peek não consegue identificar a Econt apenas pelos dígitos. Ou informe o número em "Track a shipment" em econt.com.

:::sources
- [Econt: Track a shipment](https://www.econt.com/en/services/track-shipment) – "Track a shipment" no site em inglês
- [Econt: Проследяване на пратка](https://www.econt.com/services/track-shipment) – site e rótulos em búlgaro
- [Econt: About Econt Express](https://www.econt.com/en/econt-express) – fundada em 1993, Bulgária
- [Econt: Customer service center](https://www.econt.com/en/client-center) – telefones e tarifa, Ask Ena, agências, "I want to file a claim"
- [Econt: Courier services](https://www.econt.com/en/services/courier-services) – dia útil seguinte, horários de entrega, faixas horárias, sábado, taxa de armazenagem, alterações, notificações, opções de conferência
- [Econt: Econtomat](https://www.econt.com/en/services/econtomat) – 24/7, dimensões, como retirar
- [Econt: Имейл или SMS известяване](https://www.econt.com/developers/29-sms-izvestyavane.html) – link de rastreamento nas notificações
- [Econt: Instructions form](https://instruction.econt.com/5cZyLrYsBZH) – vizinho, reencaminhamento, escolha de agência
- [Econt: Integration of e-shops with the information system of Econt Express](https://www.econt.com/e-econt/doc_templates/XML_manual_engl.pdf) – números de exemplo de 13 dígitos
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – o que o Peek lê
:::
