---
title: Rastreamento Paack: pedido, código postal e entregas
description: Rastreie um pacote da Paack: número do pedido e código postal exigidos, significado dos status, pontos PaackGo e a união da Paack com a Colis Privé.
slug: rastreamento-paack
published: 2026-11-24
updated: 2026-11-24
---

A Paack entrega pedidos de lojas online. Para rastrear uma encomenda, digite o número do pedido e o código postal de entrega na página da Paack, ou cole o número na caixa acima e escolha Paack. A Paack valida os dois em par: se um deles estiver errado, não mostra nada.

## Código de rastreamento Paack: onde achar

A página da Paack pede o número do pedido, ou o código de barras da etiqueta do pacote, junto com o código postal de entrega. A maioria dos números da Paack é o próprio número de pedido ou código de etiqueta da loja, então não existe formato fixo: procure nas mensagens de pedido e envio da loja ou na etiqueta. Sem código? Peça à loja ou veja [como encontrar o código de rastreamento](guide:find-tracking-number).

Sem formato fixo, um número de pedido pode se parecer com o de outra transportadora (exemplos inventados):

| Número do pedido | Com o que se parece |
| --- | --- |
| `482917369AB` | Letras e dígitos: nenhuma transportadora em especial. |
| `48291736` | 8 dígitos: mesmo formato de Mondial Relay, [Colisweb](carrier:colisweb) e Heppner. |
| `482917360584` | 12 dígitos: também usado por [FedEx](carrier:fedex), MRW, BRT e outras. |

Se a loja informar que a Paack faz a entrega, o código é da Paack seja qual for o formato.

Um número ou código postal incorreto retorna `Order not found. Incorrect order number or postal code.`.

## O que significam os status da Paack

A página da Paack resume a viagem em quatro etapas (dados do pedido recebidos, no centro de distribuição da Paack, saiu para entrega e entregue), e o histórico detalha as etapas intermediárias. O Peek exibe cada passo com suas próprias expressões:

| No Peek | O que significa para você |
| --- | --- |
| `Informações de envio recebidas` | A loja enviou os dados do pedido à Paack. A Paack ainda não está com o pacote. |
| `Encomenda coletada` | Primeira leitura do pacote pela Paack, em seu centro de distribuição. |
| `Em trânsito` | A Paack já está com o pacote, mas ele ainda não saiu para entrega. |
| `Courier assigned` | Um entregador foi designado para a entrega. Volta a aparecer antes de cada nova tentativa. |
| `Saiu para entrega` | Com o entregador, na janela de horário exibida. |
| `Delivery attempt failed`, `Problema na entrega` | Não entregue desta vez: o entregador não conseguiu entregar ou o PaackGo Point estava fechado, lotado ou recusou o pacote. |
| `New delivery attempt scheduled` | A Paack tentará novamente, na nova janela mostrada. Quando ela agenda no exato instante da tentativa falha, isso faz parte daquela etapa e o Peek não lista à parte. |
| `Pronta para retirada` | Aguardando no PaackGo Point; a página da Paack informa o último dia para retirada. |
| `Entregue` | Entregue, ou retirado no PaackGo Point. |
| `Em devolução ao remetente`, `Devolvida ao remetente` | Voltando para a loja, ou já devolvido — por exemplo, não retirado no prazo, recusado ou não pago no PaackGo Point. |
| `Problema com a encomenda` | Algum problema com o pacote, como avaria ou perda no PaackGo Point: fale com a loja. |

## Janelas de entrega da Paack e PaackGo Points

**A janela.** A página da Paack mostra o dia previsto e a faixa de horário no fuso local do endereço de entrega. Depois de uma tentativa falha, a faixa exibida passa a ser a da próxima tentativa.

**PaackGo Points** são os pontos de retirada da Paack. Quando o pacote chega lá, a página da Paack mostra nome e endereço do ponto, data limite para retirada, código de retirada e QR code. Se você não retirar no prazo, o pacote volta à loja.

## Pacote Paack atrasado, não entregue ou avariado

Você comprou da loja e não da Paack, portanto reembolsos, devoluções e cancelamentos passam pela loja:

- **Atrasado ou parado:** veja [por que o rastreamento para de atualizar](guide:tracking-not-updating) e fale com a loja.
- **Devolvido à loja ou sem código de rastreamento:** fale com a loja.
- **Extraviado ou avariado:** fale com a loja.
- **Entregue, mas não está lá:** pergunte a moradores da casa e vizinhos e depois fale com a loja. Mais em [entregue, mas não recebido](guide:delivered-not-received).

## Como rastrear um pacote da Paack no Peek

O [Peek](/) identifica a Paack na hora em códigos `PK` seguidos de 18 dígitos — usados nos pacotes que a Paack entrega para a Evri International — e rastreia esses sem precisar de código postal. Em etiquetas com `5340` e mais 14 dígitos, que aparecem em pacotes da Paack, "Trocar" lista a Paack primeiro em "Compatível com este código", à frente de outras transportadoras que usam 18 dígitos (na Bélgica, logo após a bpost). Em códigos de pacote da SHEIN (`C` ou `E`, data de seis dígitos ano/mês/dia e mais 8 dígitos), a Paack também aparece ali. Os demais códigos da Paack não têm formato próprio, e o Peek não pode consultar a Paack para ver se ela reconhece o número. Qualquer outro código com letras normalmente fica sem transportadora ("o Peek descobre quando você rastrear"); um código só de dígitos pode ser atribuído a outra transportadora com o mesmo formato. Toque em "Trocar" e escolha Paack, ou cole um link da página da Paack com `tracking_number=` (de mydeliveries.paack.app ou paack.co/shipments/tracking).

Depois preencha "Código postal de entrega". O campo aparece como opcional porque códigos `PK` não exigem código postal, mas a Paack só mostra os demais pedidos com ele — e um código postal dentro do link não é aproveitado. Um código `PK` sem código postal é rastreado pela Evri International; com código postal, o Peek consulta primeiro a página da Paack e recorre à Evri International se a Paack não encontrar o par ou não responder. Se você já digitou um código postal antes, o Peek o sugere abaixo do campo ("Usar …") ao adicionar o pacote e em "Trocar transportadora". A Paack só responde quando os dois conferem, então um par errado parece um número inexistente. O código postal é enviado à Paack e, quando necessário, a serviços de rastreamento. O Peek converte o código para maiúsculas e remove espaços, pontos e hífens: se nada aparecer, teste também na página da Paack com o número exatamente como está escrito.

Você vê o status, o histórico e o dia e faixa previstos no horário do endereço de entrega (horário das Canárias para códigos postais iniciados por 35 ou 38). Os horários do histórico aparecem no seu fuso: em peektracker.com e nos aplicativos para iPhone e Android, a linha abaixo informa o fuso ("Horários no seu fuso horário: …"). Em um PaackGo Point, o Peek deve mostrar nome e endereço (ainda não observado em pacote real), nunca o código de retirada, QR code ou prazo: para isso, abra a página da Paack. As etapas usam as expressões do Peek; retirada em PaackGo aparece como "Entregue". A Paack não informa o local das leituras nem cita outra transportadora, então o Peek não acompanha nenhuma outra; já em códigos `PK`, o histórico vem da Evri International, com o texto e locais dela. O nome da loja e o seu nome e endereço são omitidos. As verificações acontecem até a cada 10 minutos, ou a cada 2 minutos no dia da saída para entrega a partir das 6h no horário local (ou meia hora antes da janela, quando houver), enquanto o próprio rastreador da Paack ou da Evri responder (por meio de serviços de rastreamento, a cada 15 minutos), com alertas para "Cada movimentação", "Etapas importantes" ou "Só a entrega". Após dois dias sem novidade (12 horas enquanto ainda mostra "Etiqueta criada" ou "Recebida pela transportadora"), verifica de hora em hora.

## Perguntas sobre o rastreamento Paack

### O que é a Paack?

É uma transportadora de última milha que atende lojas online. Paack e Colis Privé uniram-se no verão de 2026 e passarão a compartilhar a marca Paack, adotada gradualmente a partir de janeiro de 2027; juntas atuam na França, Bélgica, Luxemburgo, Espanha e Portugal. Até lá, segundo a Colis Privé, entregas e rastreamento continuam normais: acompanhe pela transportadora informada pela loja.

### Por que a Paack pede meu código postal?

A Paack só exibe um pedido a quem possui o número do pedido e o código postal de entrega, de modo que o par funciona como senha. Se qualquer um dos dois estiver errado, a página responde `Order not found` como se o número não existisse. Use o código postal do endereço de destino.

### O que significa quando a Paack reagenda a entrega?

Que a Paack fará nova tentativa. Após tentativa sem sucesso, o Peek mostra `Delivery attempt failed` e a página da Paack informa o novo dia e janela. Uma nova tentativa marcada naquele mesmo instante integra o evento falho e não aparece separada; agendamentos feitos depois aparecem como `New delivery attempt scheduled`. Entregas para um PaackGo Point são reagendadas quando o ponto está fechado, lotado ou recusa o pacote, exibido pelo Peek como `Problema na entrega`.

### Quanto tempo a Paack leva para entregar?

A página da Paack mostra o dia e a janela previstos para seu pacote, e o Peek exibe o mesmo intervalo. Sobre os prazos disponíveis na compra, confirme com a loja.

:::sources
- [Colis Privé: Colis Privé and Paack unite under one brand: Paack](https://colisprive.fr/en/blog/colis-prive-and-paack-unite-under-one-brand-paack/) – 16 de outubro de 2026: juntas desde o verão de 2026, marca única a partir de janeiro de 2027, cinco países, entregas e rastreamento normais
- [Universal Parcel Scraper: Paack](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/paack) – número do pedido ou código de barras da etiqueta com o código postal, mensagem de pedido não encontrado, formatos de número, etapas e textos que o Peek guarda, janela de entrega, PaackGo Points
:::
