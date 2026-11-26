---
title: Rastreamento PIN AG: cartas registradas, pacotes e retirada
description: Rastreie cartas e pacotes PIN AG na Alemanha: quais itens têm rastreio, status em alemão explicados, retirada no depósito, extravio e contato.
slug: pin-ag-tracking
published: 2026-11-26
updated: 2026-11-26
---

A PIN AG é uma operadora postal sediada em Berlim, independente da Deutsche Post, com cerca de 1.300 funcionários. Ela entrega cartas, cartas registradas (*Einschreiben*), encomendas e pedidos da Amazon, medimops e rebuy. Apenas parte desses itens possui rastreamento. Acompanhe na [página de rastreamento da PIN AG](https://sendungsverfolgung.pin-ag.de/) (*Sendungsverfolgung*) ou cole o código na caixa acima. O número vem do remetente: em compras online, procure no e-mail de envio da loja; para carta registrada, peça a quem enviou.

## Quais itens da PIN AG podem ser rastreados?

A PIN rastreia apenas envios que exigem comprovante de entrega, desde o recebimento até a entrega final — escolha feita pelo remetente ao contratar o serviço. As páginas de ajuda da PIN listam:

| Com rastreamento | Sem rastreamento |
| --- | --- |
| Cartas registradas: Einschreiben-Einwurf, -Übergabe, -Persönlich, -Rückschein e -Persönlich-Rückschein | Cartas comuns (*Standardsendungen*) |
| Intimações e entregas formais (*Postzustellaufträge*): correspondências oficiais de tribunais ou órgãos públicos | Remessas de livros e pequenos bens (*Bücher- und Warensendungen*) |
| Pacotes (*Pakete*) | Pedidos da medimops e da rebuy |
| Itens Amazon Prime em toda a Alemanha | Itens comuns da Amazon fora de Berlim |
| Itens comuns da Amazon dentro de Berlim | Correspondência publicitária (*Werbepost*) |

O FAQ da página de rastreamento da PIN diverge em um ponto: afirma que itens comuns (não Prime) da Amazon não podem ser rastreados, sem mencionar exceção para Berlim. Por isso, um pedido comum da Amazon pode não exibir nenhum status.

## Número de rastreamento da PIN AG

Os formatos que o Peek reconhece (dígitos fictícios):

| Número (fictício) | O que é |
| --- | --- |
| `0100482917364058` | 16 dígitos começando por `0100`: número de pacote PIN segundo o código do Peek. 16 dígitos também são compatíveis com [DHL eCommerce](carrier:dhl-ecommerce) e [TNT](carrier:tnt). |
| `4829173640582` | 13 dígitos: carta registrada ou entrega formal. Muitas outras transportadoras também usam 13 dígitos. |
| `482917364058(2)` | Os mesmos 13 dígitos conforme impressos em algumas etiquetas, com o último algarismo entre parênteses. O Peek lê como `4829173640582`. |

Se o e-mail da loja mencionar [DHL](carrier:dhl) ou outra transportadora, o código é dela. Não tem número? Veja [como encontrar seu código de rastreamento](guide:find-tracking-number).

## Status de rastreamento da PIN AG explicados

A página da PIN exibe uma barra de progresso em quatro etapas (`Sortierzentrum`, `Zustelldepot`, `In Zustellung`, depois `Zugestellt`, ou `Unzustellbar` quando não consegue entregar) e a descrição em alemão. O que cada texto significa:

| A PIN informa | O que significa para você |
| --- | --- |
| `Sortierzentrum` | Seu item está no centro de triagem da PIN. |
| `Zustelldepot` | Chegou ao depósito de entrega da sua região. |
| `In Zustellung` | Saiu para entrega. A PIN costuma entregar no mesmo dia (os carteiros trabalham até a tarde). Se falhar, tentam novamente no dia seguinte. |
| `Zugestellt`, `Die Sendung wurde zugestellt.` | Entregue. |
| `Unzustellbar` | Não pôde ser entregue. A PIN devolve itens não entregáveis ao remetente. |
| `Die Sendung wurde in unserem Sortierzentrum erfasst und wird ohne weitere Aktualisierung der Sendungsverfolgung in den kommenden Tagen zugestellt. Hinweis: Bitte beachten Sie, dass es sich um eine Sendung handelt, bei dem der Zustellverlauf nicht dokumentiert wird. Für zusätzliche Informationen schauen Sie gerne in unsere FAQ.` | A PIN registrou no centro de triagem e entregará nos próximos dias sem novas atualizações: a PIN não documenta o percurso de entrega desse tipo de envio. |

Essa última mensagem aparece na etapa `In Zustellung`, mas o envio ainda não saiu para entrega naquele momento.

## Entrega PIN AG: caixa de correio, substituto e depósito

**Quem entrega.** Em Berlim, a PIN costuma entregar uma carta um dia após processá-la no centro de triagem. Fora de Berlim, quando a PIN repassa o item a um parceiro de entrega (*Zustellpartner*), o prazo pode ser um pouco maior e o rastreamento pode parar de atualizar mesmo em cartas registradas ([por que o rastreamento para de atualizar](guide:tracking-not-updating)). Pedidos comuns da Amazon fora de Berlim seguem como carta simples, sem mudanças de status.

**Como é entregue.** A PIN deposita na caixa de correio ou entrega em mãos. Se não for possível, pode entregar a um substituto: familiar, morador da residência, funcionário da empresa ou proprietário/locador do imóvel — exceto quando o envio exige entrega pessoal ou há instrução contrária sua ou do remetente. Cartas registradas seguem as exigências da respectiva modalidade. A PIN não entrega em Packstations.

**Retirada no depósito.** Quando não consegue entregar, a PIN guarda o item em um depósito PIN (*Zustelldepot*) ou parceiro e deixa um aviso (*Benachrichtigungsschein*):

- Vá a partir do dia útil seguinte, no horário de funcionamento. O aviso e a página de rastreamento da PIN (ao inserir seu código) mostram o endereço e o horário do depósito.
- Leve documento oficial com foto (obrigatório) e o aviso. Sem aviso, leve o código de rastreamento e a data da tentativa de entrega.
- Outra pessoa pode retirar levando a autorização preenchida no aviso (*Vollmacht*) e documento oficial com foto próprio.
- A PIN guarda por **7 dias úteis** (segunda a sábado), contados do dia seguinte à tentativa. Entregas formais aguardam 3 meses e contam como entregues na data do depósito.
- Passado o prazo, o envio retorna automaticamente ao remetente.

## Item PIN AG atrasado, extraviado ou devolvido

Para substituição ou reembolso, fale com o remetente — segundo a própria PIN, esse é o caminho mais rápido.

- **Não chegou após 8 dias úteis.** Conte de segunda a sábado a partir do envio. Peça reposição ou reembolso ao remetente. Você também pode enviar à PIN um pedido de busca (*Nachforschungsauftrag*) pelo formulário online. A busca pode levar até 21 dias e nem sempre localiza o objeto. Pelos termos da PIN, um item é presumido perdido quando não é entregue em 20 dias da postagem e não é localizado. Veja [encomendas perdidas](guide:lost-parcel).
- **Em `In Zustellung` há mais de três dias.** A PIN orienta entrar em contato pelo formulário vinculado na página de rastreamento.
- **Entregue, mas não encontrado.** Pergunte às pessoas da residência: a PIN pode entregar a um substituto. Para pedidos da Amazon, a PIN informa que o site de rastreamento mostra onde foi entregue (por exemplo, a um vizinho) ao informar o número e o código postal. Um erro técnico também pode marcar entregue antes da hora: aguarde um dia, pois costuma chegar no dia seguinte. Saiba mais em [entregue mas não recebido](guide:delivered-not-received).
- **Danificado.** Avise a PIN pelo formulário "Hinweis zur Briefzustellung" (usado também para atrasos, entregas erradas ou irregularidades; a PIN responde apenas em alguns casos). Se o conteúdo sofreu dano, fale com o remetente.
- **Devolvido.** A PIN devolve itens quando não há caixa de correio identificada ou fenda na porta, ninguém retira no prazo ou o dano impede a entrega. Pedidos da Amazon também retornam à Amazon se o endereço estiver errado/incompleto, seu nome não estiver na caixa de correio, o volume fugir do formato de carta acordado com a Amazon ou o endereço for uma Packstation. A PIN não altera endereço durante a entrega: peça novo envio à Amazon. Veja [devolvida ao remetente](guide:returned-to-sender).

## Contato e atendimento ao cliente da PIN AG

O Peek não pode contatar a PIN AG nem o remetente por você. A PIN AG informa:

- **ServiceCenter:** `030 577978-800`, de segunda a sexta, das 8h às 18h (horário da Alemanha).
- **Formulários online:** pedido de busca (*Nachforschungsauftrag*) e relato de entrega (*Hinweis zur Briefzustellung*), ambos disponíveis nas páginas de ajuda, além dos formulários de contato vinculados na página de rastreamento.
- **Sede:** PIN AG, Alt-Moabit 91, 10559 Berlim. Telefone `030 577978-0`, e-mail `info@pin-ag.de`.

## Rastreando um item PIN AG com o Peek

Cole o número no [Peek](/), com ou sem parênteses: o Peek junta automaticamente um dígito final entre parênteses aos demais.

- **16 dígitos começando com `0100`.** Outras transportadoras também usam 16 dígitos, então o Peek consulta as prováveis conforme seu país (região do celular ou fuso do navegador na web). Na Alemanha, pergunta primeiro à PIN AG enquanto exibe "Consultando serviços de rastreamento…". Se a PIN tiver o envio, mostra "PIN AG tem esta encomenda." e vincula à PIN. Fora da Alemanha, consulta a PIN AG apenas depois que as outras transportadoras não reconhecem o número.
- **Ainda sem registro.** Adicione mesmo assim. Na Alemanha, quando a PIN ainda não lista o código, o envio mostra "Aguardando a primeira movimentação de PIN AG" e "Provavelmente PIN AG", e o Peek segue consultando. Após uma semana, sugere conferir o número com o remetente ou escolher outra transportadora.
- **13 dígitos.** O Peek não identifica a PIN apenas por esse formato. Cole mesmo assim (o Peek acompanha mais de 4.500 transportadoras), toque em "Alterar" e escolha PIN AG (digite PIN). Em um item já adicionado, use "Alterar transportadora".
- **Link da página de rastreamento da PIN** com seu código identifica PIN AG imediatamente ("Transportadora detectada").

**O que o Peek mostra.** O status da PIN e cada etapa com horário no texto original em alemão, no fuso alemão. `Sortierzentrum`, `Zustelldepot` e o aviso sem atualizações futuras aparecem como "Em trânsito"; `In Zustellung` como "Saiu para entrega"; `Zugestellt` como "Entregue"; e `Unzustellbar` como "Precisa de atenção". Um envio aguardando no depósito (quando a página da PIN exibe endereço e horário do depósito) aparece como "Pronta para retirada", independentemente da etapa da PIN. A PIN não informa local das leituras, previsão de entrega, peso nem recebedor. O Peek omite seu endereço e o endereço do depósito: para consultar o depósito, use "Abrir o site de PIN AG", que abre seu número diretamente no rastreador da PIN. Cartas simples não têm rastreamento na PIN, logo o Peek também não consegue acompanhá-las.

Enquanto o item estiver aberto no Peek, ou no dia da entrega com notificações ativadas, o Peek consulta até a cada 10 minutos, e até a cada 2 minutos a partir das 6h (horário alemão) no dia em que a PIN registra saída para entrega enquanto o rastreador próprio responder (via serviços de rastreamento, a cada 15 minutos). Após dois dias sem novidades (12 horas em "Etiqueta criada" ou "Recebida pela transportadora"), consulta de hora em hora, e a cada 6 horas após sete dias em "Etiqueta criada".

Quando a leitura mais recente tem quatro dias e o item segue em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito", o Peek sinaliza a data dessa leitura, como "Sem atualização de rastreamento desde seg 12 out". A página então exibe "É comum uma encomenda ficar sem atualizações em voos longos, na alfândega ou no fim de semana. O Peek continua consultando PIN AG.", com o link "Obter ajuda da PIN AG" para o ServiceCenter da PIN. Após três semanas sem novas leituras, mostra "Nenhuma novidade há mais de três semanas. Se ainda não chegou, fale com o vendedor. O Peek continua consultando PIN AG."

O Peek pode notificar em "Cada leitura", "Etapas importantes" ou "Apenas entrega".

## Perguntas sobre o rastreamento da PIN AG

### Por que não consigo rastrear minha carta da PIN AG?

A PIN rastreia cartas registradas, entregas formais, pacotes e itens Amazon Prime, e suas páginas de ajuda incluem pedidos comuns da Amazon em Berlim. Outras correspondências, como cartas comuns, não têm rastreamento por escolha do remetente; a PIN prevê a entrega nos próximos dias úteis.

### Quanto tempo a PIN AG leva para entregar?

Em Berlim, normalmente um dia após o processamento no centro de triagem da PIN. Quando um parceiro assume fora de Berlim, pode demorar um pouco mais. Se nada chegar em 8 dias úteis (segunda a sábado) após a postagem, fale com o remetente.

### Por quanto tempo a PIN AG guarda minha carta no depósito?

Por 7 dias úteis (segunda a sábado), contados do dia seguinte à tentativa de entrega. Entregas formais aguardam 3 meses. Depois disso, o item volta ao remetente.

### Por que o rastreamento da PIN AG não encontra nenhum status para o meu código?

O FAQ da PIN informa a mensagem `Für die Sendungsnummer konnte kein Zustellstatus gefunden werden.`. O envio pode ser de uma categoria sem rastreamento (como carta simples), conter erro de digitação ou parênteses, ou ter mais de seis meses — a PIN apaga dados de rastreamento após seis meses por proteção de dados.

### A PIN AG entrega em Packstations?

Não, a PIN AG não entrega em Packstations. A Amazon normalmente envia esses pedidos pela Deutsche Post; se a PIN receber um mesmo assim, ela o devolve à Amazon.

:::sources
- [PIN AG: Sendungsverfolgung](https://sendungsverfolgung.pin-ag.de/) – etapas de progresso; FAQ: itens rastreados, mensagem sem status, entrega no mesmo dia, nova tentativa no dia seguinte e formulários de contato
- [PIN AG: Häufige Fragen zum Empfang von Briefsendungen](https://pin-ag.de/servicecenter/post-empfangen/sonstige-sendungen) – itens rastreados, prazos de entrega, 8 dias úteis, pedido de busca, retirada no depósito, devoluções e formulários
- [PIN AG: Häufige Fragen zum Empfang von Amazon-, medimops- und rebuy-Sendungen](https://pin-ag.de/servicecenter/post-empfangen/amazon-sendungen) – regras Amazon, entregue mas não recebido, devoluções e Packstation
- [PIN AG: ServiceCenter](https://pin-ag.de/servicecenter) – telefone e horário de atendimento
- [PIN AG: Allgemeine Geschäftsbedingungen](https://pin-ag.de/agb) – caixa de correio, substitutos, depósito, itens não entregáveis e presunção de perda após 20 dias
- [PIN AG: Das Unternehmen](https://pin-ag.de/unternehmen) – entrega de correspondências, cerca de 1.300 funcionários, Berlim
- [PIN AG: Impressum](https://pin-ag.de/impressum) – endereço da sede, telefone e e-mail
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – o que o Peek lê, etapas e textos da PIN, formatos de número
:::
