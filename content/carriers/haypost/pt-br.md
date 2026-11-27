---
title: Rastreamento HayPost: códigos AM, status e contatos
description: Rastreie encomendas do HayPost (Armênia): códigos S10 AM, status IPS internacionais e domésticos, serviços de entrega, contatos em Yerevan e Peek.
slug: haypost-tracking
published: 2026-11-26
updated: 2026-11-26
---

O HayPost (*"Haypost" Closed Joint-Stock Company*, em armênio *Հայփոստ*) é o operador postal nacional da República da Armênia. Fundado em 1994 e reorganizado como sociedade anônima fechada estatal em 1998, administra cerca de 777 agências postais em toda a Armênia, 23 centros regionais e um centro internacional de permuta postal, operando no âmbito da União Postal Universal (UPU) e da PostEurop. Você pode consultar um envio na [página Track & Trace do HayPost](https://www.haypost.am/en/track-trace) ou colar o código de rastreamento na caixa acima.

## Códigos de rastreamento HayPost: formato S10 e onde encontrar

O código de rastreamento vem impresso no recibo da agência postal ou no e-mail de despacho da loja virtual. O HayPost rastreia códigos de barras padrão UPU S10 de 13 caracteres com dígito verificador:

| Formato (inventado) | O que é |
| --- | --- |
| `RR482917367AM` | Carta registrada internacional ou pequeno pacote enviado da Armênia (`R` + segunda letra + 9 dígitos + `AM`). |
| `CP482917367AM` | Encomenda postal enviada da Armênia (`CP` + 9 dígitos + `AM`). |

O rastreador do HayPost também consulta códigos internacionais UPU S10 originados em outros países (terminados no código ISO de duas letras do país remetente, como `US`, `DE` ou `CN`) assim que o correio de origem repassa os dados eletrônicos de despacho ou o objeto entra na Armênia. Correspondência simples sem registro não tem código S10 e não pode ser acompanhada on-line. Veja [formatos de código de rastreamento](guide:tracking-number-formats) se o envio usar um formato de transportadora privada.

## O que significam os status de rastreamento do HayPost

O HayPost exibe linhas de movimentação em inglês da União Postal Universal (IPS) identificadas pelo trecho da viagem: `(Otb)` saída do país remetente, `(Inb)` entrada no país de destino ou `(EDI-received)` dados eletrônicos de despacho enviados pelo correio de origem.

| O HayPost diz | O que significa para você | O Peek mostra |
| --- | --- | --- |
| `Receive item from customer (Otb)` | Postado pelo remetente em uma agência postal. | "Recebida pela transportadora" |
| `Insert item into bag (Otb)`, `Send item abroad (EDI-received)`, `Receive item at office of exchange (Otb)` / `(Inb)` | Ensacado para despacho internacional, informado ao exterior por dados eletrônicos ou recebido no centro de permuta internacional. | "Em trânsito" |
| `Send item to customs (Otb)` | Desembaraço aduaneiro de exportação no centro de permuta de saída antes de deixar o país de origem. | "Em trânsito" |
| `Send item to customs (Inb)`, `Record item customs information (Inb)` | Retido ou inspecionado pela alfândega de importação no destino. | "Na alfândega" |
| `Return item from customs (Inb)` | Liberado pela alfândega de volta à rede postal (não é devolução ao remetente). | "Em trânsito" |
| `Send item to domestic location (Inb)`, `Receive item at location (Inb)`, `Receive item at sorting centre (Inb)`, `Item was delivered to the postal office (Inb)` | Seguindo pelos centros de triagem domésticos e chegando à agência postal local responsável pela entrega. | "Em trânsito" |
| `Send item out for physical delivery (Inb)` | Saiu com o carteiro para entrega hoje. | "Saiu para entrega" |
| `Unsuccessful item delivery attempt (Inb)`, `The notification was sent to the customer: ...` | A entrega não foi concluída; o HayPost costuma acrescentar o motivo e o próximo passo (por exemplo `Addressee not available; Scheduled for further action/processing today`). | "Tentativa de entrega" |
| `Receive item at collection point for pick-up (Inb)` | Disponível para retirada na agência postal. | "Pronta para retirada" |
| `Deliver item (Inb)` | Entregue e registrado como entregue. | "Entregue" |

Atenção a duas frases fáceis de interpretar errado. Primeiro, `Return item from customs (Inb)` é boa notícia: a alfândega terminou a análise e devolveu a encomenda ao correio para seguir entrega. Segundo, `Item was delivered to the postal office (Inb)` (frequentemente repassado por correios de destino no exterior) significa que a agência local recebeu o objeto sob sua guarda, não que ele já foi entregue a você.

## Serviços domésticos, internacionais e expressos do HayPost

Em sua rede de cerca de 777 agências postais ([Sobre o HayPost](https://www.haypost.am/en/about-us)), o HayPost opera:

- **Serviços postais domésticos ([haypost.am/en/postal-services/domestic-postal-services](https://www.haypost.am/en/postal-services/domestic-postal-services)):** cartões-postais, cartas, pequenos pacotes de até 2 kg, encomendas de até 20 kg, **Yerevan Express Service (YES)** para entregas rastreadas agência-a-agência ou em domicílio em Yerevan (pedidos feitos das 09:00 às 16:00 processados no mesmo dia; após 16:00 no próximo dia útil; pedidos de sábado na segunda-feira; linha de agendamento `+374 10 514 513`) e **HayPost Sprint** para pacotes domésticos rastreados de e-commerce até 2 kg.
- **Serviços postais internacionais ([haypost.am/en/postal-services/international-delivery](https://www.haypost.am/en/postal-services/international-delivery)):** cartas internacionais, pequenos pacotes até 2 kg (comprimento + largura + espessura ≤ 0,9 m, lado maior ≤ 0,6 m), encomendas até 20 kg (qualquer lado ≤ 1,05 m), malas M até 30 kg e correio acelerado EMS.
- **HayPost Express para os Estados Unidos ([haypost.am/en/postal-services/Haypost-Express](https://www.haypost.am/en/postal-services/Haypost-Express)):** envio aéreo de qualquer agência na Armênia para Los Angeles (pontos de retirada em Glendale, Van Nuys e Sylmar), até 30 kg, com prazo médio de 7 a 10 dias úteis.

**FAQ do HayPost.** O [FAQ do HayPost](https://www.haypost.am/en/faq) reúne perguntas sobre prazos de referência domésticos, taxas aduaneiras, indenizações e tempo de guarda de envios EMS; para confirmar os valores aplicáveis ao seu objeto, consulte sua agência ou o HayPost.

## Encomendas HayPost atrasadas, na alfândega ou extraviadas

Para compras on-line enviadas de ou para a Armênia por via postal, contate primeiro o vendedor sobre reembolsos ou reenvios.

**Pausas entre países.** Após `Send item abroad (EDI-received)`, a correspondência internacional aguarda transporte aéreo, triagem na chegada e alfândega de importação no centro de permuta de destino, o que pode gerar pausas de vários dias. Veja [por que o rastreamento para de atualizar](guide:tracking-not-updating).

**Desembaraço aduaneiro.** Encomendas que entram na Armênia e envios `AM` destinados a locais como UE ou EUA passam pela alfândega de importação de destino (`Send item to customs (Inb)`). Tenha em mãos a fatura da compra e o código de rastreamento caso a alfândega solicite comprovação de valor. Veja [encomendas retidas na alfândega](guide:customs).

**Entregue, danificada ou devolvida.** Se o envio mostrar `Deliver item (Inb)` sem estar com você, verifique com moradores do endereço e com a agência local (usando o [diretório de agências do HayPost](https://www.haypost.am/en/our-network)), depois fale com o vendedor e com o suporte do HayPost. Veja [entregue mas não recebida](guide:delivered-not-received), [encomenda danificada](guide:damaged-parcel), [encomenda extraviada](guide:lost-parcel) e [devolvida ao remetente](guide:returned-to-sender).

## Atendimento e contatos do HayPost

Contatos publicados em [haypost.am/en/contact-us](https://www.haypost.am/en/contact-us) (e [haypost.am/am/contact-us](https://www.haypost.am/am/contact-us)):

- **Telefone de atendimento:** `+374 10 514-514`.
- **Vendas e atendimento corporativo:** `+374 10 544-444`.
- **Linha de pedidos YES Yerevan Express:** `+374 10 514 513`.
- **E-mail:** `info@haypost.am`.
- **Sede:** 65/5 Arshakunyats Ave., Yerevan 0061, República da Armênia.
- **Formulário on-line:** em [haypost.am/en/contact-us](https://www.haypost.am/en/contact-us), além de chat ao vivo em [haypost.am](https://www.haypost.am/en).

> Desconfie de mensagens por SMS, WhatsApp ou e-mail dizendo que você precisa abrir um link de terceiros e digitar dados do cartão para pagar uma pequena taxa de "reentrega" ou "confirmação de endereço" de uma encomenda HayPost. Confira seu código S10 diretamente em [haypost.am/en/track-trace](https://www.haypost.am/en/track-trace) ou ligue para `+374 10 514-514`. Veja [golpes de entrega por SMS](guide:delivery-scam-texts).

## Acompanhe uma encomenda do HayPost com o Peek

Para códigos UPU S10 terminados em `AM` (como `RR482917367AM` ou `CP482917367AM`, dígitos inventados), o [Peek](/) acompanha o HayPost automaticamente como correio de origem preferencial e também pode rastrear códigos S10 enviados à Armênia depois que você seleciona o HayPost. Como o Peek não possui regras dedicadas para links do HayPost, cole o código S10 de 13 caracteres; se precisar escolher o HayPost manualmente, toque em "Trocar" ao adicionar ou "Trocar transportadora" na tela da encomenda e selecione HayPost (buscar por "Armenia Post" ou "Հայփոստ" também funciona).

O Peek lê diretamente a API pública JSON do HayPost em inglês e exibe a etapa correspondente ("Recebida pela transportadora", "Em trânsito", "Na alfândega", "Saiu para entrega", "Tentativa de entrega", "Pronta para retirada", "Entregue"), o texto das movimentações IPS (incluindo motivos de falha e observações de próximo passo como `Addressee not available`), a agência/país (sem códigos postais numéricos), o país de destino e o horário de entrega. Movimentações na Armênia e linhas `Send item abroad (EDI-received)` usam o fuso de Yerevan, enquanto movimentações de correios estrangeiros usam o fuso local (por exemplo, o da França em Paris); o Peek mostra tudo no relógio do seu aparelho. Quando o HayPost publica previamente um registro planejado com data futura de `Send item abroad (EDI-received)` (muitas vezes horas antes), o Peek oculta essa linha até o horário real chegar. A API do HayPost não retorna data prevista de entrega, peso, nome do serviço, dados do remetente/destinatário nem foto de entrega.

Com a encomenda aberta no Peek, ou no dia da entrega com notificações ligadas, o Peek verifica até a cada 10 minutos; no dia em que o HayPost exibe `Send item out for physical delivery (Inb)`, a cada 2 minutos a partir das 6h no fuso da movimentação mais recente do HayPost, enquanto o próprio rastreador do HayPost responder. Nos demais momentos, consulta de hora em hora. Após 12 horas apenas em "Etiqueta criada" ou "Recebida pela transportadora", ou dois dias sem novidades, passa a verificar a cada hora de qualquer modo, e a cada 6 horas depois de sete dias em "Etiqueta criada". Quando o rastreador do HayPost não responde e o Peek acompanha a encomenda apenas por serviços de rastreamento, consulta a cada hora, a cada 3 horas após um dia sem novidades e até a cada 15 minutos no dia da entrega.

Quando a movimentação mais recente completa quatro dias e a encomenda continua em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito", o Peek sinaliza com a data desse registro, como "Sem atualizações desde seg 12 out". A página então exibe "É comum uma encomenda ficar sem atualizações em voos longos, na alfândega ou no fim de semana. O Peek continua consultando HayPost.", com um link "Pedir ajuda a HayPost" para a página de contato do HayPost. Após três semanas sem novidade, mostra "Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, fale com o vendedor. O Peek continua consultando HayPost."

Você pode escolher alertas para "Cada movimentação", "Etapas importantes" ou "Só a entrega". O Peek apenas acompanha atualizações de rastreamento e não faz desembaraço aduaneiro nem abre uma consulta junto ao HayPost por você.

## Perguntas sobre o rastreamento do HayPost

### Como é um código de rastreamento do HayPost?

O HayPost usa números postais padrão UPU S10 de 13 caracteres: duas letras, nove dígitos e `AM` no final para envios postados na Armênia (por exemplo, `RR482917367AM` ou `CP482917367AM`, dígitos inventados). Envios internacionais de entrada mantêm as duas letras finais do país remetente.

### Por que minha encomenda HayPost mostrou `Return item from customs (Inb)`?

Esse status IPS indica que a alfândega liberou o objeto e o devolveu ao serviço postal para continuar o transporte — não significa que a encomenda esteja voltando ao remetente.

### O HayPost rastreia encomendas enviadas do exterior para a Armênia?

Sim. Qualquer código internacional rastreado UPU S10 destinado à Armênia pode ser consultado em [haypost.am/en/track-trace](https://www.haypost.am/en/track-trace) ou no Peek assim que os dados eletrônicos de despacho chegam ou o HayPost registra a movimentação na Armênia.

### Como entro em contato com o atendimento do HayPost?

Ligue para `+374 10 514-514` (ou `+374 10 544-444` para serviços corporativos), envie um e-mail para `info@haypost.am` ou use o formulário em [haypost.am/en/contact-us](https://www.haypost.am/en/contact-us) ou o chat ao vivo no site do HayPost.

:::sources
- [HayPost: Home](https://www.haypost.am/en) – portal do operador postal nacional
- [HayPost: Track & Trace](https://www.haypost.am/en/track-trace) – rastreamento on-line de objetos
- [HayPost: About Us](https://www.haypost.am/en/about-us) – fundado em 1994, sociedade anônima fechada desde 1998, cerca de 777 agências, 23 centros regionais, membro da UPU e da PostEurop
- [HayPost: Domestic Postal Services](https://www.haypost.am/en/postal-services/domestic-postal-services) – pequenos pacotes de 2 kg, encomendas de 20 kg, YES Yerevan Express Service (`+374 10 514 513`) e HayPost Sprint
- [HayPost: International Delivery](https://www.haypost.am/en/postal-services/international-delivery) – limites internacionais de pequeno pacote (2 kg), encomenda (20 kg), mala M (30 kg) e EMS
- [HayPost: HayPost Express](https://www.haypost.am/en/postal-services/Haypost-Express) – envio aéreo para Los Angeles (30 kg, 7–10 dias úteis)
- [HayPost: Our Network](https://www.haypost.am/en/our-network) – diretório de agências na Armênia
- [HayPost: Contact Us](https://www.haypost.am/en/contact-us) – `+374 10 514-514`, `+374 10 544-444`, `info@haypost.am`, 65/5 Arshakunyats Ave., Yerevan
- [HayPost: Contact Us (Armenian)](https://www.haypost.am/am/contact-us) – página de ajuda indicada pelo Peek
- [HayPost: FAQ](https://www.haypost.am/en/faq) – índice de perguntas frequentes
- [Universal Parcel Scraper: haypost](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/haypost) – detecção de S10 `AM`, consulta JSON em `api.haypost.am`, correspondência de status IPS, horário de Yerevan, filtro de EDI com data futura e link de ajuda
:::
