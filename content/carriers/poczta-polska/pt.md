---
title: Seguimento Poczta Polska: números, estados e levantamentos
description: Segue uma encomenda Poczta Polska ou Pocztex: formatos dos números, significado dos estados, avisos, pontos, cacifos, alfândega e contactos.
slug: seguimento-poczta-polska
published: 2026-11-22
updated: 2026-11-22
---

A Poczta Polska é o operador postal estatal da Polónia, e o Pocztex é o seu serviço de entregas expresso. Podes acompanhar uma encomenda ou carta registada na página eMonitoring da Poczta Polska, que dispõe de versão em inglês, ou colar o número na caixa acima. O número consta do comprovativo de envio do remetente — pelo que a loja o tem — e é repetido nos SMS e avisos da Poczta Polska.

## Número de seguimento Poczta Polska: onde encontrar

Escreve-o sem espaços nem parênteses. O seguimento abrange todos os envios registados com comprovativo de depósito, exceto cartas com valor declarado; as cartas registadas mostram apenas as datas de depósito e de entrega.

| Formato (inventado) | O que é |
| --- | --- |
| `PX4829105734` | `PX` e 10 algarismos. |
| `0045900773482910573` | 19 algarismos. |
| `00459007734829105736` | 20 algarismos com `5900773` após os primeiros três (ou 18 algarismos sem os dois zeros `00` iniciais). |
| `RR482910576PL` | Formato postal internacional: duas letras, nove algarismos e `PL`. |

Onde aparece:

- **Comprovativo de envio do remetente**: pergunta à loja se o e-mail da encomenda não incluir nenhum número.
- **Mensagem de levantamento** para um ponto ou cacifo Pocztex, com o número, o local, o prazo e um código único.
- **SMS, e-mail ou aviso na app após tentativa falhada**, com o número, o prazo e a morada do posto postal.

Se o eMonitoring responder «The given package number is incorrect», confirma cada caráter e qual a transportadora indicada pela loja. Não tens número? Vê [onde encontrar o número de seguimento](guide:find-tracking-number).

## O que significam os estados de seguimento da Poczta Polska

O eMonitoring abre em polaco; a versão em inglês apresenta estes textos, com data, hora e posto postal em cada passo:

| Estado da Poczta Polska | O que significa para ti |
| --- | --- |
| `Electronic item data received` | O remetente registou o envio. A Poczta Polska ainda não o recebeu. |
| `Posting/collection`, `Item arrived at the shipping point` | Depositado num posto ou ponto, ou recolhido no remetente. |
| `In transport` | Em viagem entre centros de triagem. |
| `Departure from Poland` | Saiu da Polónia rumo a outro país. |
| `Arrival at inward office of exchange (Poland)` | Uma encomenda do estrangeiro chegou ao centro de permuta da Polónia. |
| `Customs service` | Tratamento aduaneiro. |
| `Item returned from import customs` | Libertado pela alfândega de importação. |
| `In delivery` | Em distribuição, ou tentativa falhada (vê abaixo). |
| `Unsuccessful (physical) delivery` | Não estava ninguém. Foi deixado aviso e o envio segue para o posto postal ou volta a distribuição. |
| `Attempted delivery - redirected` | Tentativa após a qual o envio é encaminhado. |
| `Delivery at sender's request - delivery attempt` | Tentativa de entrega solicitada pelo remetente. |
| `Unsuccessful (physical) delivery - lost` | Extraviado durante a entrega. |
| `Ready for pick-up at the Post Office`, `Added to postal notice book - Item can be collected`, `Ready for pick up at the point` | À tua espera no posto ou ponto indicado no registo. |
| `Collected at the post office`, `Collected at the delivery point` | Levantado no posto ou ponto. |
| `Final delivery`, `Delivered to the PO box`, `Delivery at sender's request - delivered` | Entregue. |
| `Returned`, `Returned to sender`, `Not collected at the delivery point` | A regressar ao remetente. |

`Posting/collection` é o passo do remetente, não o teu levantamento. A Poczta Polska usa também `In delivery` num evento de entrega falhada: o Peek distingue ambos pelo código do evento e mostra «Tentativa de entrega» nesse caso em vez de «Em distribuição». Uma linha `Customs service` pode aparecer depois da entrega para registar direitos cobrados à porta. Sem «(Poland)», `Arrival at inward office of exchange` indica que um envio saído da Polónia chegou ao país de destino. Na página polaca, uma entrega falhada surge como `Awizowana` e um envio à espera como `Oczekuje na odbiór`. A Poczta Polska refere que os nomes dos eventos podem mudar.

## Entrega falhada Poczta Polska: avisos, pontos de recolha e cacifos

**Encomendas de estafeta Pocztex.** O estafeta entrega a encomenda a ti, a um adulto do teu agregado ou a alguém autorizado. Se não estiveres, recebes SMS, e-mail ou aviso na app quando o remetente forneceu os teus contactos, ou um aviso na caixa do correio, seguido de segunda mensagem dois dias depois. A encomenda espera 7 dias no posto postal a contar do dia seguinte ao aviso e regressa depois ao remetente. Podes também ligar ao estafeta para a deixar junto à tua morada (não aplicável a cobrança, valor declarado, porte pago pelo destinatário ou entrega «do rąk własnych»), por tua conta e risco.

**Pontos de levantamento e cacifos.** Num ponto Pocztex (`PUNKT`) escolhido pelo remetente, a encomenda aguarda 7 dias num posto postal ou 3 dias numa loja parceira como Żabka, ABC ou Lewiatan. Os cacifos (`AUTOMAT`), em postos postais, postos de combustível e lojas como Biedronka, guardam-na 72 horas, com lembretes após 24 e 48 horas. Abrem-se com o número de telefone e o código ou com a app Pocztex Mobile. O prazo não pode ser prolongado nem a encomenda movida para outro ponto, mas qualquer pessoa com o código pode levantá-la.

**Cartas registadas e encomendas postais.** É deixado um aviso (*awizo*) na caixa do correio, ou enviado eletronicamente se pedido. Passados 7 dias chega um segundo aviso e o envio é devolvido se não for levantado nos 14 dias seguintes ao primeiro. Mediante pagamento, podes pedir no posto ou em poczta-polska.pl até 14 dias extra de guarda.

## Encomenda Poczta Polska atrasada, perdida, danificada ou na alfândega

O seguimento da Poczta Polska é meramente informativo e não serve de base a reclamações, nem publica conselhos quando o seguimento para: pergunta ao remetente e vê [porque é que o seguimento para de atualizar](guide:tracking-not-updating).

**Quem reclama.** O remetente, ou tu assim que o envio tenha sido entregue ou o remetente te tenha cedido o direito: online em poczta-polska.pl (eReklamacje), em papel ou presencialmente em qualquer posto postal.

**Prazos** para envios nacionais segundo as condições da Poczta Polska:

- Até 12 meses após o depósito.
- Danos visíveis: no ato da entrega, em auto. Podes verificar o conteúdo de uma encomenda visivelmente danificada, exceto em pontos e cacifos.
- Danos ocultos ou falta de conteúdo: até 7 dias após a receção.
- Extravio: uma encomenda Pocztex considera-se perdida 30 dias após o depósito; o extravio de uma encomenda postal pode ser participado 14 dias após o depósito, sem contar domingos nem feriados.
- A Poczta Polska responde em 30 dias; podes recorrer em 14 dias.

Envios internacionais: no prazo de seis meses a contar do dia seguinte ao depósito.

**Alfândega.** Desde 1 de julho de 2026, envios de fora da UE de valor até 150 € pagam um direito aduaneiro da UE de 3 € por tipo de produto, cobrado pela Poczta Polska; ofertas entre particulares até 45 € estão isentas. Desde 1 de novembro de 2026 acresce uma taxa europeia de tratamento de 2 € por artigo (vários produtos idênticos podem contar como um) em compras online enviadas de fora da UE: a Poczta Polska cobra-a ao destinatário e transfere-a para a administração fiscal polaca (KAS). Podem juntar-se IVA não pago e a taxa de declaração aduaneira da Poczta Polska (20 PLN, ou 25 PLN se tratares tu mesmo da alfândega), pagáveis na entrega ou antes do levantamento. Podes recusar o envio. Mais em [encomendas retidas na alfândega](guide:customs).

## Contacto telefónico e apoio ao cliente da Poczta Polska

Contactos indicados nas páginas oficiais em inglês e polaco da Poczta Polska e do Pocztex:

- **Linha geral:** +48 438 420 600, segunda a sexta-feira das 8:00 às 20:00, ao tarifário do operador, além de formulário online na [página de contacto](https://www.poczta-polska.pl/en/kontakt/).
- **Pocztex:** 804 104 104 a partir de redes fixas polacas e +48 43 842 0 842 a partir de telemóveis e do estrangeiro, segunda a sexta-feira das 8:00 às 20:00 e sábado das 8:00 às 16:00.
- **Dúvidas de seguimento:** por e-mail para kontakt@poczta-polska.pl.
- **Problemas no ponto de levantamento** (encomenda em falta, ponto fechado): Contact Center 801 333 444 (rede fixa) ou +48 438 420 600.

> A Poczta Polska alerta para SMS falsos do Pocztex sobre entregas falhadas com ligações para sites fraudulentos. O Pocztex nunca envia ligações de pagamento nem pede dados de cartão por SMS. Reencaminha SMS suspeitos para 8080.

## Seguir envios da Poczta Polska com o Peek

O [Peek](/) segue mais de 4500 transportadoras. Reconhece logo números `PX` com dígito de controlo válido, números de 20 algarismos com `5900773` após os três primeiros e dígito de controlo válido, e números de 18 algarismos sem os dois zeros `00` iniciais (que o Peek acrescenta sozinho). Para números de 19 algarismos começados por `00`, consulta apenas a Poczta Polska, acrescentando o dígito de controlo tal como o site oficial. Um número internacional válido terminado em `PL` que comece por R, E, L ou C (exceto CV), como `RR`, `EE` ou `CP`, vai direto para a Poczta Polska; outro válido em `PL`, como um `VV`, aparece como «Operador postal desconhecido» enquanto o Peek pergunta à Poczta Polska e à [Chronopost](carrier:chronopost), vencendo a Poczta Polska quando o reconhece. As ligações do eMonitoring com `numer=` também funcionam, sem código postal.

O Peek mostra os textos ingleses tal como vêm escritos, exceto `Returned to sender`, que aparece como «Devolvida ao remetente» (ou os polacos se usares o Peek em polaco), cada posto postal no mapa, o peso, o nome do serviço, o país de destino e o posto que guarda uma encomenda pronta a levantar. Não há data prevista de entrega. Deixa de fora moradas e horários de postos, documentos de pagamento e serviços adicionais. Registos retransmitidos do estrangeiro não trazem fuso horário: o Peek lê-os segundo a hora do país de onde provêm. Paletes, envios agrupados e correio não registado não podem ser seguidos. Quando um envio registado, aceite ou em trânsito passa quatro dias sem registos, o Peek mostra «Sem atualizações desde seg 12 out» (o dia do último registo) e «Pede ajuda a Poczta Polska».

Enquanto tens a encomenda aberta no ecrã, ou no dia da entrega com as notificações ativas, o Peek verifica até de 10 em 10 minutos — e até de 2 em 2 minutos a partir das 6:00 (hora local) no dia em que a Poczta Polska a regista em distribuição — enquanto o seguimento próprio da Poczta Polska responder. Fora disso, e após dois dias sem novidades (12 horas enquanto continuar em «Etiqueta criada» ou «Aceite pela transportadora»), verifica de hora a hora. Só através de serviços de seguimento, verifica de hora a hora, de 3 em 3 horas após um dia sem novidades e até de 15 em 15 minutos no dia em que está em distribuição, enquanto a tiveres aberta ou as notificações ativas. Pode notificar-te de «Todos os registos», «Passos importantes» ou «Só a entrega». Uma encomenda enviada do estrangeiro para a Polónia pode passar para a Poczta Polska.

## Perguntas sobre o seguimento Poczta Polska

### O que é o Pocztex?

O Pocztex é o serviço de estafeta da Poczta Polska, com site próprio em pocztex.pl e a app Pocztex Mobile. As suas encomendas usam o mesmo seguimento eMonitoring.

### Posso seguir a Poczta Polska em inglês?

Sim. O eMonitoring abre em polaco mas tem versão em inglês.

### Quanto tempo demora o Pocztex a entregar?

A Poczta Polska garante a entrega do Pocztex Kurier até ao quinto dia útil após o depósito, ou ao sétimo para encomendas 2XL e não normalizadas. Dias úteis são de segunda a sexta-feira exceto feriados.

### Posso redirecionar uma encomenda da Poczta Polska?

Não enquanto destinatário. As condições da Poczta Polska reservam alterações de morada ou destinatário ao remetente, e encomendas enviadas para um ponto ou cacifo Pocztex não podem ser transferidas para outro.

:::sources
- [Poczta Polska: Track your shipment](https://www.poczta-polska.pl/en/sledzenie-przesylek/) – formato de números, âmbito, pontos de recolha, operadores estrangeiros
- [Poczta Polska: Śledź przesyłkę](https://www.poczta-polska.pl/sledzenie-przesylek/) – o mesmo em polaco
- [Poczta Polska: eMonitoring (inglês)](https://emonitoring.poczta-polska.pl/?lang=en) – exemplos, mensagem de erro, caráter informativo, kontakt@
- [Poczta Polska: eMonitoring](https://emonitoring.poczta-polska.pl/) – versão polaca, nomes de estados
- [Pocztex: Śledzenie przesyłek](https://www.pocztex.pl/sledzenie-przesylek/) – seguimento Pocztex
- [Poczta Polska: Regulamin usługi Pocztex](https://www.poczta-polska.pl/wp-content/uploads/2026/08/Regulamin-swiadczenia-uslugi-Pocztex-w-obrocie-krajowym-dla-klienta-detalicznego-obowiazuje-od-24.08.2026-r.pdf) – entrega, avisos, pontos, cacifos, prazos, reclamações, garantia
- [Poczta Polska: Regulamin usług powszechnych](https://www.poczta-polska.pl/wp-content/uploads/2026/07/Regulamin-swiadczenia-uslug-powszechnych-tekst-jednolity.pdf) – awizo, guarda, extravio, reclamações internacionais
- [Poczta Polska: Lista zdarzeń / Events list](https://www.poczta-polska.pl/wp-content/uploads/2025/02/PPSA_events_lists.pdf) – lista de eventos e possibilidade de alteração
- [Poczta Polska: Pocztex PUNKT and AUTOMAT FAQ](https://odbiorwpunkcie.poczta-polska.pl/en/faq/) – lojas parceiras, cacifos, sem redirecionamento, Contact Center
- [Pocztex: FAQ](https://www.pocztex.pl/en/faq-en/) – avisos, pontos de levantamento, auto de danos
- [Poczta Polska: eReklamacje, paczka pocztowa](https://ereklamacje.poczta-polska.pl/sub/reklamacje-krajowe/paczka-pocztowa) – quem reclama, prazos, 30 dias
- [Poczta Polska: Contact](https://www.poczta-polska.pl/en/kontakt/) – linha geral, horários, formulário
- [Poczta Polska: Kontakt](https://www.poczta-polska.pl/kontakt/) – versão polaca, formulário de reclamação
- [Pocztex: Kontakt](https://www.pocztex.pl/kontakt/) – linhas Pocztex e horários
- [Poczta Polska: Beware of fake "Pocztex" messages](https://www.poczta-polska.pl/en/news/uwaga-na-falszywe-wiadomosci-pocztex-grozna-kampania-phishingowa/) – SMS falsos, 8080
- [Poczta Polska: Uwaga na fałszywe wiadomości „Pocztex”](https://www.poczta-polska.pl/news/uwaga-na-falszywe-wiadomosci-pocztex-grozna-kampania-phishingowa/) – versão polaca, Contact Center
- [Poczta Polska: Changes in fees for shipments from outside the EU](https://www.poczta-polska.pl/en/news/zmiany-w-oplatach-dla-przesylek-spoza-unii-europejskiej-od-1-lipca-2026-r/) – direito de 3 €, ofertas até 45 €, recusa
- [Poczta Polska: Nowe przepisy Unii Europejskiej. Tak działa nowa opłata za przesyłki spoza UE](https://media.poczta-polska.pl/releases/nowe-przepisy-unii-europejskiej-tak-dziala-nowa-oplata-za-przesylki-spoza-ue) – taxa europeia de tratamento de 2 € por posição a cargo do destinatário, transferida para a KAS
- [Poczta Polska: Cennik opłat dodatkowych](https://www.poczta-polska.pl/wp-content/uploads/2025/04/cennik-oplat-dodatkowych.pdf) – taxas de declaração aduaneira
- [Poczta Polska: Poczta Polska SA Group](https://www.poczta-polska.pl/en/grupa-pp/poczta-polska/) – empresa estatal
- [Pocztex Mobile na App Store](https://apps.apple.com/pl/app/pocztex-mobile/id1661761150) – a aplicação
- [Universal Parcel Scraper: Poczta Polska](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/poczta-polska) – deteção, números de 18/19/20 algarismos, campos lidos e descartados
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – o que o Peek lê
:::
