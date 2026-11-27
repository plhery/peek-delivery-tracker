---
title: Rastreamento Pitney Bowes: códigos UPAA, UPBE e PBX
description: Rastreie uma encomenda Pitney Bowes, como um pedido internacional do eBay: formatos UPAA, UPBE e PBX, status do rastreador, Interpost e entrega final.
slug: pitney-bowes-tracking
published: 2026-11-27
updated: 2026-11-27
---

A Pitney Bowes envia encomendas para outros países: no código do Peek, seus números correspondem a pedidos do eBay vindos dos Estados Unidos e do Reino Unido e a outros pacotes internacionais da empresa. Nos históricos registrados no código do Peek, o volume passa por um centro de envio da Pitney Bowes e segue para o país do comprador, onde um parceiro — como o correio local — realiza a entrega. O código começa com `UPAA`, `UPBE` ou `PBX` e vem do vendedor ou do marketplace: cole-o na caixa acima. O rastreador oficial da Pitney Bowes fica em trackpb.shipment.co.

## Código de rastreamento Pitney Bowes: como identificar

O Peek reconhece três formatos da Pitney Bowes, cada um com 14 a 20 caracteres. Exemplos com dígitos fictícios:

| Código (fictício) | O que é |
| --- | --- |
| `UPAAK482917364058291` | `UPAA`, uma letra e 15 dígitos: no código do Peek, um pedido do eBay enviado dos EUA ou do Reino Unido. |
| `UPBE48291736A4F02819` | `UPBE` e 16 caracteres (dígitos ou letras de A a F): pedido do eBay vindo do Reino Unido. |
| `PBXHK482917365` | `PBX`, duas letras e 9 dígitos: outro envio internacional da Pitney Bowes. |
| `PBXHK48291736A4F0281` | `PBX`, duas letras e 15 caracteres (dígitos ou letras de A a F): o mesmo, na versão longa. |
| `UPAA482917364058291` | `UPAA` sem a letra seguinte: não segue o padrão da Pitney Bowes e o Peek não identifica nenhuma transportadora. Se você escolher Pitney Bowes manualmente, o Peek avisa que ela não emite códigos nesse formato. Confira se faltou um caractere. |

Espaços, hifens e letras minúsculas não importam no Peek. O catálogo também reconhece Pitney Bowes como Pitney Bowes Global Ecommerce, Pitney Bowes Expedited Parcel Service, eBay Global Shipping Program e PBI.

**Onde encontrar.** No e-mail de envio do vendedor ou na página do pedido. Para compras no eBay, veja [pedidos do eBay](guide:ebay-orders), em inglês; para demais pedidos, [como encontrar seu código de rastreamento](guide:find-tracking-number), em inglês.

## Significado dos status de rastreamento da Pitney Bowes

O rastreador da Pitney Bowes exibe cada linha com data, hora e, quase sempre, local, da mais recente para a mais antiga. Como as linhas não têm código próprio, o Peek interpreta o texto. Na ordem em que o envio avança:

**No centro de envio (Shipping Center).** Linhas da própria Pitney Bowes:

| A Pitney Bowes mostra | O que significa para você | O Peek mostra |
| --- | --- | --- |
| `Tracking details uploaded`, `Label Created` | O vendedor enviou os dados da encomenda à Pitney Bowes; ela ainda não está com o pacote. | "Etiqueta criada" |
| `Package not received by Hub or First Mile Carrier yet` | A etiqueta existe, mas nem o centro de envio nem a transportadora da primeira etapa leram o pacote. | "Etiqueta criada" |
| `Processing at Shipping Center` | O centro de envio da Pitney Bowes recebeu e está processando o pacote. | "Em trânsito" |
| `First Dimension Event`, `Second Dimension Event` | O centro está medindo o volume. | "Em trânsito" |
| `Parcel Documentation and Labeling`, `Customs Documentation and Labeling` | O centro inclui documentação de exportação e etiquetas; ainda não é a alfândega em si. | "Em trânsito" |
| `Container Assignment`, `Container Closed` | Colocado em um contêiner para a viagem e lacrado. | "Em trânsito" |
| `Shipped from Global Shipping Center to Destination`, `Departed for Destination` | Saiu do centro rumo ao país de destino. | "Em trânsito" |
| `International Carrier` | Repassado à transportadora internacional. | "Em trânsito" |
| `In Transit` | Em trânsito. Pode aparecer sem local. | "Em trânsito" |

**Trânsito posterior, alfândega e repasse.** Nos históricos reunidos no código do Peek, volumes que saem do centro da Pitney Bowes no Reino Unido seguem com a Interpost, parceira que protocola a declaração de importação e entrega ao correio de destino. As duas primeiras linhas costumam surgir sem local ainda no centro britânico:

| A Pitney Bowes mostra | O que significa para você | O Peek mostra |
| --- | --- | --- |
| `Item processed at Interpost` | O centro britânico está processando o volume para a Interpost. | "Em trânsito" |
| `Interpost handed item over to postal company` | Repasse da Interpost ao correio do país de destino. Surge no mesmo instante da linha acima, quando o volume pode ainda estar no centro. | "Em trânsito" |
| `Customs Clearance` | Declaração de importação protocolada no país de destino. Trâmite documental que pode ocorrer horas após sair do Reino Unido, antes de o pacote chegar. | "Na alfândega" |
| `B2C ClearanceStatus4` | Liberado pela alfândega de importação. O Peek escreve `Customs import cleared`, conforme o rastreador da Interpost. | "Em trânsito" |
| `B2C ClearanceStatus` e outro número | Etapa de desembaraço cujo significado exato o Peek desconhece; o Peek exibe `Customs clearance update`. | "Na alfândega" |
| `Cleared customs` | Liberado pela alfândega. | "Em trânsito" |
| `Package is created in the system` | A próxima transportadora abriu registro do volume; não indica movimento físico. | sem alteração |

**Linhas do correio local.** Após o repasse, o rastreador repassa as leituras do correio que faz a entrega:

| A Pitney Bowes mostra | O que significa para você | O Peek mostra |
| --- | --- | --- |
| `Item in transit by postal company` | O correio está levando a encomenda até você. | "Em trânsito" |
| `Parcel is out for delivery` | Saiu para entrega. | "Saiu para entrega" |
| `Item is waiting for addressee at local post office` | Aguardando você na agência postal local para retirada. | "Pronta para retirada" |
| `Delivered by Consignee Pickup` | Retirada por você, o destinatário. | "Entregue" |
| `Item has been delivered` | Entregue. | "Entregue" |

`Departed carrier facility` e `Processed by Carrier` também aparecem como "Em trânsito". Outras linhas seguem as regras gerais do Peek; linhas desconhecidas aparecem como escritas em "Em trânsito", exceto se a encomenda já estiver em rota de entrega ou em etapa posterior, quando o status permanece inalterado.

**Status geral.** O rastreador informa ainda um status resumido. Quando marca entregue, o Peek mostra "Entregue" mesmo que a linha mais recente não diga isso. Outros status gerais valem apenas quando nenhuma linha define a etapa; o status de problema, já visto em encomendas retiradas normalmente pelo destinatário, nunca sobrescreve uma linha.

## Quem entrega encomendas da Pitney Bowes

O Peek não conseguiu ler o site da Pitney Bowes; por isso esta página não lista parceiros nem países atendidos. O código do Peek mostra a Interpost seguindo com volumes do centro britânico até o correio de destino, incluindo um histórico entregue pela [Poste Italiane](carrier:poste-italiane) na Itália. O rastreador da Pitney Bowes informa o nome da transportadora seguinte e pode trazer um link para o site dela com o número local.

**Tentativas e retirada.** Tentativas de entrega, avisos deixados e prazo de guarda seguem as regras do correio local: consulte o correio ou leia a página dele no Peek, se houver. Veja também [tentativa de entrega](guide:missed-delivery), em inglês, e [encomendas não retiradas](guide:uncollected-parcel), em inglês.

**Alfândega.** Se você pagará taxas na chegada depende das regras do seu país e do que o vendedor ou marketplace cobrou no checkout: confirme primeiro com eles. O Peek não pôde verificar como a Pitney Bowes trata tarifas de importação. Veja [encomendas retidas na alfândega](guide:customs), em inglês.

## Encomenda Pitney Bowes parada, extraviada ou devolvida

**Nada ainda.** Para um código desconhecido ou histórico expirado, o rastreador da Pitney Bowes responde da mesma forma que em caso de indisponibilidade, e o Peek não consegue distinguir um do outro: ele consulta serviços de rastreamento e, se nenhum encontrar, mostra "Aguardando Pitney Bowes" e, a partir da segunda consulta, avisa que ainda não conseguiu acessar a Pitney Bowes e continua tentando. Confira o código na tabela acima; se nada surgir após alguns dias, confirme com o vendedor se foi enviado.

**Parada em `Package not received by Hub or First Mile Carrier yet`.** O vendedor gerou a etiqueta, mas nem o centro da Pitney Bowes nem a transportadora inicial fizeram a leitura. Pergunte ao vendedor quando postou.

**Parada em `Customs Clearance`.** A declaração foi enviada, mas a encomenda talvez ainda nem tenha chegado ao país. Aguarde a liberação, exibida pelo Peek como `Customs import cleared`. Se demorar muito, avise o vendedor.

**Sem novidades depois que o correio assume.** Após as primeiras linhas do correio local, o rastreador próprio dele pode atualizar antes do repasse da Pitney Bowes, e o Peek não faz a troca sozinho. Abra o site do correio pelo link na página da Pitney Bowes ou adicione o código do correio como outra encomenda no Peek.

**Dias sem atualização.** Após quatro dias sem nova linha em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito", o Peek destaca o dia da última atualização ("Sem atualização de rastreamento desde seg 12 out") e a nota "É comum uma encomenda ficar sem atualizações em voos longos, na alfândega ou no fim de semana. O Peek continua consultando Pitney Bowes." Após três semanas, mostra "Nada de novo há mais de três semanas. Se ainda não chegou, fale com o vendedor. O Peek continua consultando Pitney Bowes." Veja [por que o rastreamento para de atualizar](guide:tracking-not-updating), em inglês.

**Entregue, mas não chegou.** Confira onde pode ter sido deixada, fale com vizinhos e depois contate o correio local e o vendedor. Veja [entregue mas não recebida](guide:delivered-not-received), em inglês.

**Extraviada ou devolvida.** Peça reembolso ou novo envio ao vendedor ou marketplace que contratou o frete. Veja [encomendas extraviadas](guide:lost-parcel), em inglês, e [devolvida ao remetente](guide:returned-to-sender), em inglês.

## Atendimento ao cliente e contato da Pitney Bowes

Como o Peek não conseguiu ler o site da Pitney Bowes, esta página não exibe telefone nem e-mail — o Peek publica apenas contatos verificados nas páginas oficiais da própria transportadora.

A Pitney Bowes transporta para o vendedor ou marketplace, portanto fale com eles primeiro (em compras no eBay, diretamente pelo eBay). Quando o correio local estiver com o pacote, trate da entrega direto com esse correio.

## Como rastrear uma encomenda Pitney Bowes no Peek

O [Peek](/) identifica Pitney Bowes na hora por códigos `UPAA`, `UPBE` ou `PBX` ("Transportadora detectada") e consulta o rastreador apenas pelo número, sem código postal nem conta. Links de trackpb.shipment.co ou parceltracking.pb.com também funcionam. Se você tiver apenas o código inicial do vendedor ou o do correio local, cole-o mesmo assim: o Peek acompanha mais de 4.500 transportadoras. Escolher Pitney Bowes manualmente em "Alterar" para outro formato não funciona: o Peek avisa que ela não emite códigos nesse formato.

O Peek exibe a etapa, cada linha com seu local e o peso nas suas unidades (o rastreador informa libras e onças), omitindo o link e o número da transportadora seguinte. Como o rastreador não informa data prevista nem comprovante de entrega, o Peek não exibe nenhum dos dois. "Abrir o site de Pitney Bowes" abre o código no rastreador oficial.

O Peek não transfere automaticamente a encomenda para o correio: as leituras do correio chegam pelo rastreador da Pitney Bowes. Quando esse rastreador não responde ou não consegue ler o pacote, o Peek consulta serviços de rastreamento e mostra o histórico deles.

O rastreador registra cada horário no relógio de quem fez a leitura, sem fuso horário. O Peek lê cada linha no fuso do local ou trecho correspondente; sem local, interpreta `In Transit` e `Item processed at Interpost` em UTC, `Interpost handed item over to postal company` em UTC+2 e `Customs Clearance` no fuso do país citado. Tudo aparece convertido para o fuso do seu dispositivo.

Com a encomenda aberta no Peek, ou no dia da entrega com notificações ligadas, o Peek consulta a Pitney Bowes a cada 10 minutos no máximo, e a cada 2 minutos a partir das 6h no relógio da última leitura no dia em que sai para entrega, enquanto o rastreador responder; fora disso, de hora em hora. Após dois dias sem novidade (12 horas em "Etiqueta criada" ou "Recebida pela transportadora"), verifica de hora em hora, e a cada 6 horas após sete dias em "Etiqueta criada". Notifica em "Cada atualização", "Etapas importantes" ou "Somente na entrega". O Peek não pode contatar a Pitney Bowes, o vendedor ou o correio por você.

## Perguntas frequentes sobre o rastreamento Pitney Bowes

### O que é um código de rastreamento UPAA?

É um código da Pitney Bowes: `UPAA`, uma letra e 15 dígitos. No código do Peek, números `UPAA` representam pedidos do eBay vindos dos EUA e do Reino Unido, `UPBE` representa pedidos do eBay do Reino Unido e `PBX` cobre outros pacotes internacionais da Pitney Bowes. O catálogo também lista eBay Global Shipping Program como nome alternativo da Pitney Bowes.

### O que significa "Interpost handed item over to postal company"?

Indica o repasse do pacote pela Interpost — que assume remessas vindas do centro britânico da Pitney Bowes — ao correio do país de destino que fará a entrega. A Pitney Bowes registra essa linha enquanto ainda processa o volume no Reino Unido, podendo aparecer antes da saída; as leituras do correio chegam depois no mesmo rastreador.

### Por que meu rastreamento Pitney Bowes travou em "Customs Clearance"?

Trata-se da declaração de importação, que pode ser enviada horas após a saída do Reino Unido, antes mesmo da chegada ao país. Aguarde a liberação (`Customs import cleared` no Peek). Se demorar muito, fale com o vendedor.

### Como entro em contato com a Pitney Bowes sobre minha encomenda?

Procure primeiro o vendedor ou marketplace que contratou o envio. Como o Peek não conseguiu ler o site da Pitney Bowes, esta página não informa telefone nem e-mail.

:::sources
- [Universal Parcel Scraper: Pitney Bowes](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/pitney-bowes) – names, the three number shapes and what they cover, tracking links, the tracker, its wordings and their stages, Interpost's lines and clearance code, the post's relayed lines, Poste Italiane on a parcel to Italy, the overall status, clocks, weight, what Peek leaves out
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – no hand-off to the delivering post, tracking services as a fallback
:::
