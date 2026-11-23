---
title: Seguimento Pitney Bowes: códigos UPAA, UPBE e PBX
description: Segue uma encomenda Pitney Bowes (como um pedido internacional do eBay): formatos UPAA, UPBE e PBX, significado dos estados e quem entrega.
slug: seguimento-pitney-bowes
published: 2026-11-23
updated: 2026-11-23
---

A Pitney Bowes expede encomendas para outros países: no código do Peek, os seus números correspondem a encomendas do eBay dos Estados Unidos e do Reino Unido e a outras encomendas internacionais da Pitney Bowes. Nos históricos presentes no código do Peek, a encomenda passa por um centro de expedição da Pitney Bowes e segue depois para o país do comprador, onde um parceiro — como os correios nacionais — faz a entrega. O código começa por UPAA, UPBE ou PBX e vem do vendedor ou do marketplace: cola-o na caixa acima. O rastreador da Pitney Bowes encontra-se em trackpb.shipment.co.

## Código de seguimento da Pitney Bowes: qual é o formato

O Peek conhece três formatos para a Pitney Bowes, cada um com 14 a 20 caracteres. Com dígitos inventados:

| Número (inventado) | O que é |
| --- | --- |
| `UPAAK482917364058291` | UPAA, uma letra e 15 dígitos: no código do Peek, uma encomenda do eBay dos EUA ou do Reino Unido. |
| `UPBE48291736A4F02819` | UPBE e 16 caracteres, dígitos ou letras de A a F: uma encomenda do eBay do Reino Unido. |
| `PBXHK482917365` | PBX, duas letras e 9 dígitos: outra encomenda internacional da Pitney Bowes. |
| `PBXHK48291736A4F0281` | PBX, duas letras e 15 caracteres, dígitos ou letras de A a F: o mesmo na forma mais longa. |
| `UPAA482917364058291` | UPAA sem a letra: não tem o formato da Pitney Bowes e o Peek não reconhece nenhuma transportadora. Se escolheres a Pitney Bowes, o Peek diz «não emite números como este». Verifica se falta algum carácter. |

Espaços, hífenes e minúsculas não importam no Peek. O catálogo conhece também a Pitney Bowes por Pitney Bowes Global Ecommerce, Pitney Bowes Expedited Parcel Service, eBay Global Shipping Program e PBI.

**Onde encontrá-lo.** No e-mail de expedição do vendedor ou na página da encomenda. Para o eBay, vê [encomendas do eBay](guide:ebay-orders); caso contrário, [como encontrar o teu código de seguimento](guide:find-tracking-number).

## Estados de seguimento da Pitney Bowes explicados

O rastreador da Pitney Bowes apresenta cada linha com data, hora e, quase sempre, um local, da mais recente para a mais antiga. As linhas não têm código: o Peek lê o texto. Pela ordem habitual:

**No centro de expedição.** Linhas próprias da Pitney Bowes:

| O que a Pitney Bowes mostra | O que significa para ti | O que o Peek mostra |
| --- | --- | --- |
| `Tracking details uploaded`, `Label Created` | O vendedor enviou os dados da encomenda à Pitney Bowes. A Pitney Bowes ainda não a tem. | «Etiqueta criada» |
| `Package not received by Hub or First Mile Carrier yet` | A etiqueta existe, mas nem o centro de expedição nem a transportadora inicial registou ainda a encomenda. | «Etiqueta criada» |
| `Processing at Shipping Center` | O centro de expedição da Pitney Bowes tem a encomenda e está a processá-la. | «Em trânsito» |
| `First Dimension Event`, `Second Dimension Event` | O centro está a medir a encomenda. | «Em trânsito» |
| `Parcel Documentation and Labeling`, `Customs Documentation and Labeling` | O centro junta documentos de exportação e etiquetas. Ainda não é a alfândega em si. | «Em trânsito» |
| `Container Assignment`, `Container Closed` | Colocada num contentor para a viagem e contentor fechado. | «Em trânsito» |
| `Shipped from Global Shipping Center to Destination`, `Departed for Destination` | Saiu do centro com destino ao teu país. | «Em trânsito» |
| `International Carrier` | Entregue à transportadora que a leva para o estrangeiro. | «Em trânsito» |
| `In Transit` | Em caminho. Pode aparecer sem local. | «Em trânsito» |

**Trânsito internacional, alfândega e entrega aos correios.** Nos históricos do código do Peek, encomendas que saem do centro britânico da Pitney Bowes seguem com a Interpost, um parceiro que entrega a declaração de importação e as transfere aos correios do país de destino. As linhas deste troço, sendo as duas primeiras escritas sem local enquanto o centro britânico trata a encomenda:

| O que a Pitney Bowes mostra | O que significa para ti | O que o Peek mostra |
| --- | --- | --- |
| `Item processed at Interpost` | O centro britânico processa a encomenda para a Interpost. | «Em trânsito» |
| `Interpost handed item over to postal company` | Entrega pela Interpost aos correios do país de destino, que farão a entrega. Surge no mesmo instante que a linha acima, quando a encomenda ainda pode estar no centro. | «Em trânsito» |
| `Customs Clearance` | Declaração de importação submetida no país de destino. Formalidade documental: pode surgir horas após sair do Reino Unido, antes de a encomenda lá chegar. | «Na alfândega» |
| `B2C ClearanceStatus4` | Desalfandegada na importação. O Peek escreve `Customs import cleared`, como o sistema da Interpost descreve este código. | «Em trânsito» |
| `B2C ClearanceStatus` seguido de outro número | Um passo de desalfandegamento cujo significado o código do Peek desconhece. O Peek escreve `Customs clearance update`. | «Na alfândega» |
| `Cleared customs` | Passou pela alfândega. | «Em trânsito» |
| `Package is created in the system` | A transportadora seguinte abre o registo da encomenda. Não significa movimento. | sem alteração |

**Linhas dos correios.** Depois da passagem, o rastreador retransmite os registos dos correios que entregam:

| O que a Pitney Bowes mostra | O que significa para ti | O que o Peek mostra |
| --- | --- | --- |
| `Item in transit by postal company` | Os correios estão a transportar a encomenda até ti. | «Em trânsito» |
| `Parcel is out for delivery` | Em distribuição. | «Em distribuição» |
| `Item is waiting for addressee at local post office` | À tua espera na estação de correios local: vai levantá-la. | «Pronta para levantamento» |
| `Delivered by Consignee Pickup` | Tu, o destinatário, levantaste-a. | «Entregue» |
| `Item has been delivered` | Entregue. | «Entregue» |

`Departed carrier facility` e `Processed by Carrier` surgem igualmente como «Em trânsito». Outras linhas seguem as regras gerais do Peek; uma linha desconhecida aparece tal como foi escrita e conta como «Em trânsito», salvo se a encomenda já estiver em distribuição ou numa fase posterior, mantendo nesse caso o estado atual.

**O estado geral.** O rastreador apresenta também um estado geral. Quando indica entregue, o Peek mostra «Entregue» mesmo que a última linha não o diga. Os restantes estados gerais contam apenas enquanto nenhuma linha tiver estado. O estado de problema — que já surgiu numa encomenda simplesmente levantada pelo destinatário — nunca se sobrepõe a uma linha.

## Quem entrega encomendas da Pitney Bowes

O Peek não conseguiu ler o site da Pitney Bowes, pelo que esta página não lista parceiros nem países. O código do Peek mostra a Interpost a levar encomendas desde o centro britânico até aos correios do país de destino, e um histórico mostra a [Poste Italiane](carrier:poste-italiane) a entregar em Itália. O rastreador da Pitney Bowes indica a transportadora que assume a continuação e pode ligar uma linha à página de seguimento dessa transportadora com o respetivo número.

**Tentativas de entrega e levantamento.** Tentativas, avisos de receção e prazos de guarda seguem as regras dos correios que entregam: pergunta-lhes ou consulta a sua página no Peek, se existir. Vê também [entrega falhada](guide:missed-delivery) e [encomendas não levantadas](guide:uncollected-parcel).

**Alfândega.** Pagares ou não algo à chegada depende do teu país e do que o vendedor ou o marketplace cobrou na compra: pergunta-lhes primeiro. O Peek não pôde verificar como a Pitney Bowes trata taxas de importação. Vê [encomendas retidas na alfândega](guide:customs).

## Encomenda Pitney Bowes sem atualizações, perdida ou devolvida

**Nada por enquanto.** Para um número desconhecido ou um histórico expirado, o rastreador da Pitney Bowes dá a mesma resposta que quando não consegue ler uma encomenda. O Peek não consegue distinguir isso de uma falha: consulta serviços de seguimento e, se nenhum tiver a encomenda, mostra «À espera de Pitney Bowes» e, a partir da segunda verificação, «Ainda não foi possível contactar Pitney Bowes. O Peek continua a tentar.», enquanto continua a verificar. Confirma o código na tabela acima. Se nada aparecer ao fim de alguns dias, pergunta ao vendedor se a encomenda foi mesmo enviada.

**Parada em `Package not received by Hub or First Mile Carrier yet`.** O vendedor criou a etiqueta, mas nem o centro de expedição da Pitney Bowes nem a transportadora inicial registou a encomenda. Pergunta ao vendedor quando a expediu.

**Parada em `Customs Clearance`.** A declaração foi entregue e a encomenda pode ainda nem estar no país. Aguarda a linha de desalfandegamento, mostrada como `Customs import cleared` no Peek. Se permanecer assim muito tempo, avisa o vendedor.

**Nada de novo depois de chegar aos correios.** Após as primeiras linhas dos correios, o seguimento próprio destes pode ir à frente do que a Pitney Bowes transmite, e o Peek não muda sozinho para lá. Abre a página dos correios a partir da linha no rastreador da Pitney Bowes ou adiciona o código postal ao Peek como encomenda separada.

**Vários dias sem atualizações.** Ao fim de quatro dias sem registo numa encomenda em «Etiqueta criada», «Aceite pela transportadora» ou «Em trânsito», o Peek mostra um aviso («Sem atualizações há quatro dias» ou «Sem atualizações desde …») com a nota «É habitual uma encomenda ficar sem novidades em voos longos, na alfândega ou ao fim de semana. O Peek continua a perguntar a Pitney Bowes.» Vê [porque é que o seguimento deixa de atualizar](guide:tracking-not-updating).

**Entregue mas não chegou.** Verifica onde poderá ter sido deixada e pergunta aos vizinhos, depois aos correios que entregaram e ao vendedor. Vê [entregue mas não recebida](guide:delivered-not-received).

**Extraviada ou devolvida.** Pede ao vendedor ou ao marketplace reembolso ou reenvio: foram eles que contrataram o transporte. Vê [encomendas perdidas](guide:lost-parcel) e [devolvida ao remetente](guide:returned-to-sender).

## Apoio ao cliente e contactos da Pitney Bowes

O Peek não conseguiu ler o site da Pitney Bowes, pelo que esta página não indica número de telefone nem e-mail: o Peek só apresenta contactos lidos diretamente nas páginas da própria transportadora.

A Pitney Bowes envia a encomenda para o vendedor ou para o marketplace, portanto recorre primeiro a eles — para uma encomenda do eBay, através do eBay. Assim que os correios locais tiverem a encomenda, pergunta-lhes sobre a entrega.

## Seguir uma encomenda Pitney Bowes com o Peek

O [Peek](/) identifica de imediato a Pitney Bowes a partir de um número UPAA, UPBE ou PBX («Transportadora detetada») e consulta o rastreador apenas com esse número: sem código postal nem conta. Uma ligação de seguimento de trackpb.shipment.co ou parceltracking.pb.com também funciona. Se tiveres apenas o número do primeiro troço ou o dos correios, cola-o mesmo assim: o Peek segue mais de 4 500 transportadoras. Escolher Pitney Bowes em «Alterar» para outro formato não resulta: o Peek indica «não emite números como este».

O Peek mostra o estado, cada linha com o respetivo local e o peso nas tuas unidades (o rastreador dá libras e onças). Omite a ligação e o número da transportadora seguinte. Como o rastreador não dá data prevista nem comprovativo de entrega, o Peek não mostra nenhum deles. «Abrir o site de Pitney Bowes» na página da encomenda abre o número no seu rastreador.

O Peek não transfere a encomenda para os correios: os registos postais chegam através do rastreador da Pitney Bowes. Quando este está incontactável ou não consegue ler a encomenda, o Peek consulta serviços de seguimento e apresenta o histórico destes nas suas próprias palavras.

O rastreador apresenta cada hora no relógio de quem registou a encomenda, sem fuso horário. O Peek lê cada linha no relógio do seu local ou do troço respetivo. Sem local indicado, `In Transit` e `Item processed at Interpost` ficam em UTC, `Interpost handed item over to postal company` em UTC+2 e `Customs Clearance` na hora do país mencionado. Cada hora é apresentada no fuso horário do teu dispositivo.

Enquanto tiveres a encomenda aberta no Peek, ou no dia da entrega com as notificações ligadas, o Peek consulta diretamente a Pitney Bowes até de 10 em 10 minutos, e até de 2 em 2 minutos a partir das 6:00 no relógio do último registo no dia em que sai para distribuição, enquanto o rastreador da Pitney Bowes responder; caso contrário, de hora a hora. Após dois dias sem novidades (12 horas enquanto ainda mostra «Etiqueta criada» ou «Aceite pela transportadora»), verifica de hora a hora; após sete dias sem novidades em «Etiqueta criada», de 6 em 6 horas. Pode notificar-te em «Todos os registos», «Passos importantes» ou «Só a entrega». O Peek não pode contactar a Pitney Bowes, o vendedor nem os correios por ti.

## Perguntas sobre o seguimento Pitney Bowes

### O que é um número de seguimento UPAA?

Um número da Pitney Bowes: UPAA, uma letra e 15 dígitos. O código do Peek lê os códigos UPAA como encomendas do eBay dos EUA e do Reino Unido, os UPBE como encomendas do eBay do Reino Unido e os PBX como outras encomendas internacionais da Pitney Bowes. O catálogo indica também eBay Global Shipping Program como outro nome da Pitney Bowes.

### O que significa «Interpost handed item over to postal company»?

É a passagem da tua encomenda pela Interpost — que recolhe encomendas a partir do centro britânico da Pitney Bowes — para os correios do país de destino que a entregam. A Pitney Bowes regista-a enquanto o centro britânico trata a encomenda, pelo que pode aparecer antes de ela sair. Os registos dos correios aparecem depois no rastreador da Pitney Bowes.

### Porque é que o meu seguimento Pitney Bowes está parado em «Customs Clearance»?

Trata-se da declaração de importação, que pode ser submetida horas após sair do Reino Unido, antes da chegada. Aguarda a linha de desalfandegamento, `Customs import cleared` no Peek. Se ficar assim muito tempo, pergunta ao vendedor.

### Como contacto a Pitney Bowes sobre a minha encomenda?

Pergunta primeiro ao vendedor ou ao marketplace: foram eles que contrataram a entrega. Como o Peek não conseguiu ler o site da Pitney Bowes, esta página não indica telefone nem e-mail.

:::sources
- [Universal Parcel Scraper: Pitney Bowes](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/pitney-bowes) – nomes, os três formatos de número e o que abrangem, ligações de seguimento, o rastreador, textos e etapas, linhas da Interpost e código aduaneiro, linhas retransmitidas pelos correios, Poste Italiane numa encomenda para Itália, estado geral, relógios, peso, o que o Peek omite
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – sem passagem automática para os correios locais, serviços de seguimento como alternativa
:::
