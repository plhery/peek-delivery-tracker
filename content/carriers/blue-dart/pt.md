---
title: Seguimento Blue Dart: números waybill e estados explicados
description: Segue um envio da Blue Dart: formato do número de 11 dígitos, o que significa Shipment Further Connected e seguimento com o Peek.
slug: seguimento-blue-dart
published: 2026-11-22
updated: 2026-11-22
---

A Blue Dart é uma transportadora indiana: o catálogo do Peek lista-a para a Índia e lê os seus registos segundo a hora da Índia. Um envio Blue Dart usa um número de guia (*waybill*) de 11 algarismos fornecido pelo remetente. Numa compra online pode aparecer, por exemplo, no e-mail de envio da loja ou na página da encomenda. Cola-o na caixa acima ou escreve-o no seguimento em bluedart.com. Como o Peek não conseguiu consultar o site da Blue Dart para esta página, tudo o que se segue vem do que o código do Peek conhece do seguimento da Blue Dart.

## Número waybill da Blue Dart: que aspeto tem

O Peek conhece um único formato para a Blue Dart: 11 algarismos, sendo o último um dígito de controlo calculado a partir dos primeiros dez. Com algarismos inventados:

| Número (inventado) | O que é |
| --- | --- |
| `48291057360` | 11 algarismos com dígito de controlo válido: o formato de uma guia Blue Dart. As redes GLS, PostLogistics, Japan Post, Mainfreight e outras também usam 11 algarismos, pelo que o formato só por si não indica Blue Dart. |
| `48291057361` | O mesmo formato com dígito de controlo errado. O Peek não sugere Blue Dart para ele: verifica se há erro de escrita. |
| `4829105736` | 10 algarismos: não é uma guia Blue Dart. Se escolheres Blue Dart para este número, o Peek diz que a Blue Dart «não emite números como este». O mesmo acontece com 12 algarismos ou números com letras. |

As guias de 11 algarismos da Aramex e o correio registado da Japan Post usam o mesmo dígito de controlo, pelo que um número válido pode ser deles: confirma qual a transportadora indicada pela loja. Espaços e hífenes não importam no Peek. O seguimento da Blue Dart também permite pesquisar por outras referências, mas o Peek precisa do número *waybill*. Não o encontras? Vê [onde encontrar o número de seguimento](guide:find-tracking-number).

## Estados de seguimento da Blue Dart explicados

O seguimento da Blue Dart mostra o estado atual do envio e abaixo os registos, do mais recente ao mais antigo, cada um com local, data e hora. Estas são as linhas que o Peek conhece no seguimento da Blue Dart, aproximadamente pela ordem em que um envio as encontra:

| A Blue Dart diz | O que significa para ti | O Peek mostra |
| --- | --- | --- |
| `Pickup Employee Is Out To P/U Shipment` | Um estafeta da Blue Dart está a caminho para recolher (*P/U*) o envio junto do remetente. A Blue Dart ainda não o tem. | «Etiqueta criada» |
| `Shipment Picked Up` | A Blue Dart recolheu o envio no remetente. | «Aceite pela transportadora» |
| `Shipment Arrived`, `Shipment Arrived At Hub` | Chegou a uma instalação ou centro de triagem da Blue Dart, indicado no local do registo. | «Em trânsito» |
| `Paper Work Inscan` | Registado num centro entre a chegada e a ligação seguinte. | «Em trânsito» |
| `Clubbed Canvas Bag Scan` | Registado num saco de transporte juntamente com outros envios. | «Em trânsito» |
| `Shipment Further Connected` | Seguido a partir desse local para a etapa seguinte do percurso. | «Em trânsito» |
| `Delivery Scheduled For Next Working Day` | Na estação de entrega, mas depois da hora limite do dia: segue na volta do dia útil seguinte. | «Em trânsito» |
| `Shipment Out For Delivery` | Em distribuição. | «Em distribuição» |
| `Cnee Refused Id/Otp Not Shared-Incorrect` | Não entregue devido a recusa do destinatário (*Cnee*, *consignee*) ou porque o documento de identificação ou código único (*OTP*) não foi fornecido ou estava errado. | «Tentativa de entrega» |
| `Delivery Delayed`, `Delay Caused Beyond Control` | A entrega está atrasada; a segunda linha atribui a causa a fatores fora do controlo da Blue Dart. | «Problema reportado» |
| `Shipment Delivered` | Entregue. | «Entregue» |
| `Shipment Returned Back To Shipper` | O envio regressou ao remetente. A Blue Dart apresenta-o como estado final assim que a devolução termina. | «Devolvida ao remetente» |

Uma linha que o Peek ainda não conheça surge nas palavras da Blue Dart e conta como «Em trânsito», exceto se a encomenda já estiver em distribuição ou numa fase posterior: aí mantém o estado anterior. Mais em [o que significam os estados de seguimento](guide:tracking-statuses).

## Entrega Blue Dart na Índia: identificação e código OTP

Como o Peek não pôde verificar as regras de entrega da Blue Dart — como quantas tentativas faz ou quanto tempo guarda um envio não entregue — esta página não indica nenhuma.

**Documento ou código OTP.** A linha `Cnee Refused Id/Otp Not Shared-Incorrect` mostra que uma entrega Blue Dart pode depender de um documento de identificação ou de um código único (*OTP*). Se recebeste um código para o envio, tem-no à mão no dia da entrega. Se esta linha aparecer sem que tenhas recusado nada, pede à loja para combinar nova entrega. Vê [entregas falhadas](guide:missed-delivery) e [encomendas não levantadas](guide:uncollected-parcel).

Uma mensagem sobre uma entrega falhada pede-te para pagar ou partilhar um código através de uma ligação? Vê [SMS fraudulentos de entregas](guide:delivery-scam-texts).

## Envios Blue Dart: quem entrega e alfândega

**Uma guia Blue Dart.** O Peek não conhece nenhuma transportadora para a qual a Blue Dart transfira os seus envios, na Índia ou no estrangeiro. Se a loja te der também o número de outra transportadora, adiciona esse igualmente.

**Enviado com a ICC Worldwide.** O seguimento da ICC Worldwide pode referir a Blue Dart junto a um número Blue Dart. O Peek só acompanha o envio na Blue Dart quando esse número tem 11 algarismos: consulta a Blue Dart e, assim que esta mostrar um registo pelo menos tão recente como o último da ICC Worldwide, passa a seguir o envio lá, mantendo o histórico da ICC Worldwide. Com qualquer outro número, o Peek fica na ICC Worldwide. Adiciona o envio com o número da ICC Worldwide, toca em «Alterar» e procura ICC Worldwide: o Peek não a sugere a partir do número.

**Alfândega.** O código do Peek conhece a Blue Dart apenas na Índia e não pôde consultar o site da Blue Dart quanto a envios internacionais. Para uma encomenda parada na alfândega, vê [encomendas retidas na alfândega](guide:customs).

## Seguimento Blue Dart parado, atrasado ou devolvido

Para um reembolso ou novo envio, fala com a loja: foi ela quem contratou a Blue Dart.

**Ainda sem registos.** Quando não tem dados do número, o seguimento da Blue Dart responde `There is no information on the Waybill/Reference Number/Order Number currently.` O Peek continua a verificar. Confirma os 11 algarismos na mensagem da loja; se nada surgir após alguns dias, pergunta à loja se entregou o envio.

**Parado em `Pickup Employee Is Out To P/U Shipment`.** A Blue Dart ainda não recolheu o envio no remetente. Pergunta à loja quando será entregue à transportadora.

**`Delivery Delayed` ou `Delay Caused Beyond Control`.** O Peek mostra «Problema reportado». Se nada mudar depois, fala com a loja.

**Dias sem atualizações.** Quando um envio a caminho fica quatro dias sem novo registo, o Peek assinala-o com o dia desse registo: «Sem atualizações desde seg 12 out». Vê [porque é que o seguimento para de atualizar](guide:tracking-not-updating).

**Entregue mas não chegou.** Pergunta em casa ou na receção do prédio e depois avisa a loja. Vê [encomenda dada como entregue mas não recebida](guide:delivered-not-received).

**Perdida ou devolvida.** Pede à loja reembolso ou reenvio: vê [encomendas perdidas](guide:lost-parcel). `Shipment Returned Back To Shipper` indica que voltou ao remetente: vê [devolvida ao remetente](guide:returned-to-sender).

## Apoio ao cliente e contactos da Blue Dart

Como o Peek não conseguiu verificar o site da Blue Dart, esta página não apresenta número de telefone nem endereço de e-mail: só publicamos contactos lidos nas páginas da própria transportadora. Quando um envio fica parado, o Peek liga à página de ajuda da Blue Dart como «Pede ajuda a Blue Dart».

Numa compra online, fala primeiro com a loja que contratou a Blue Dart. O Peek não pode contactar a Blue Dart nem a loja por ti.

## Seguir um envio da Blue Dart com o Peek

Cola a guia no [Peek](/). Como várias transportadoras usam 11 algarismos, o Peek não mostra logo «Transportadora detetada» a partir de um número Blue Dart sozinho:

- **Na Índia** (a região do teu telemóvel ou, na web, o país de onde acedes), o Peek mostra «A verificar serviços de seguimento…» enquanto consulta as transportadoras compatíveis, Blue Dart primeiro. Quando a Blue Dart conhece o número, vês «Blue Dart tem esta encomenda.».
- **Fora da Índia**, o Peek não pergunta automaticamente à Blue Dart. Toca em «Alterar» e escolhe Blue Dart em «Corresponde a este número».

Se nenhuma transportadora conhecer ainda o número, adiciona-o mesmo assim ou escolhe Blue Dart tu mesmo: o Peek segue mais de 4500 transportadoras. O Peek só sugere a Blue Dart quando o dígito de controlo bate certo. Se o teu não bater mas tiveres a certeza do número, procura Blue Dart na lista de transportadoras: o Peek consulta-a na mesma. Uma ligação de seguimento de bluedart.com com a guia (`trackNo=`) também funciona: o Peek extrai o número e identifica logo a Blue Dart («Transportadora detetada»).

Associado à Blue Dart, um envio mostra «À espera de Blue Dart» até ao primeiro registo, ou «Verifica o número de seguimento» se ao fim de dois dias a Blue Dart ainda não conhecer o número; adicionado sem transportadora, «Ainda sem histórico de seguimento». O Peek lê o seguimento público da Blue Dart apenas com a guia, sem conta: o estado geral e cada registo nas palavras da Blue Dart com o respetivo local, lido na hora da Índia. Deixa de fora os dados do destinatário e não mostra data prevista de entrega. A ligação «Abrir o site de Blue Dart» na página da encomenda abre a guia no seguimento da Blue Dart.

Enquanto tens a encomenda aberta no ecrã, ou no dia da entrega com as notificações ativas, o Peek verifica até de 10 em 10 minutos — e até de 2 em 2 minutos a partir das 6:00 (hora local) no dia em que a Blue Dart a coloca em distribuição — enquanto o seguimento próprio da Blue Dart responder. Fora disso, e após dois dias sem novidades (12 horas enquanto continuar em «Etiqueta criada» ou «Aceite pela transportadora»), verifica de hora a hora. Enquanto um serviço de seguimento acompanhar o envio, o Peek verifica de hora a hora, até de 15 em 15 minutos no dia da distribuição enquanto o tens aberto ou com notificações ativas, e de 3 em 3 horas após um dia sem novo registo. Pode notificar-te de «Todos os registos», «Passos importantes» ou «Só a entrega».

## Perguntas sobre o seguimento Blue Dart

### Como sigo um envio da Blue Dart?

Introduz a guia de 11 algarismos no seguimento em bluedart.com ou cola-a no Peek. Na Índia o Peek consulta a Blue Dart primeiro; noutros países, toca em «Alterar» e escolhe Blue Dart.

### Que aspeto tem um número waybill da Blue Dart?

11 algarismos, sendo o último um dígito de controlo. A Aramex e outras transportadoras também usam 11 algarismos, por isso confirma que a mensagem da loja indica Blue Dart.

### O que significa «Shipment Further Connected» na Blue Dart?

Que a Blue Dart encaminhou o envio de uma das suas instalações para a etapa seguinte do trajeto. O Peek mostra «Em trânsito».

### O que significa «Cnee Refused Id/Otp Not Shared-Incorrect»?

Uma entrega falhada por recusa do destinatário ou por um documento de identificação ou código único (OTP) não partilhado ou incorreto. O Peek mostra «Tentativa de entrega». Se não recusaste nada, pede à loja nova entrega.

### Quem devo contactar por um envio em falta da Blue Dart?

A loja ou o remetente que contratou a Blue Dart. Como o Peek não pôde verificar o site da Blue Dart, esta página não lista contactos da Blue Dart.

:::sources
- [Universal Parcel Scraper: Blue Dart](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/blue-dart) – Índia, guia de 11 algarismos e dígito de controlo, seguimento e mensagem de ausência de dados, textos e etapas, hora da Índia, dados omitidos, ligações de seguimento e página de ajuda
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – outras transportadoras de 11 algarismos, dígito de controlo da Aramex e Japan Post, transportadoras consultadas na Índia e no estrangeiro, ligação ICC Worldwide–Blue Dart e regra dos 11 algarismos, sem transferências a partir da Blue Dart
:::
