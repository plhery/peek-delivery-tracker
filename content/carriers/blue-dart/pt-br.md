---
title: Rastreamento Blue Dart: código de 11 dígitos e status
description: Rastreie uma remessa Blue Dart na Índia: formato do código de 11 dígitos, significado de Shipment Further Connected e outros status, e acompanhamento no Peek.
slug: rastreamento-blue-dart
published: 2026-11-26
updated: 2026-11-26
---

A Blue Dart é uma transportadora indiana: o catálogo do Peek a lista para a Índia e lê suas movimentações no horário da Índia. Uma remessa Blue Dart usa um número de conhecimento (waybill) de 11 dígitos fornecido pelo remetente — em compras online, ele pode constar no e-mail de envio ou na página do pedido da loja. Cole-o no campo acima ou consulte no próprio rastreador da Blue Dart em bluedart.com. O Peek não conseguiu consultar o site da Blue Dart para esta página, então as informações abaixo vêm do que o código do Peek conhece do rastreador dela.

## Código de rastreamento Blue Dart: como ele é

O Peek conhece um formato para a Blue Dart: 11 dígitos, sendo o último um dígito verificador calculado a partir dos dez primeiros. Com dígitos inventados:

| Código (inventado) | O que é |
| --- | --- |
| `48291736544` | 11 dígitos com dígito verificador válido: o formato de um conhecimento da Blue Dart. As redes GLS, PostLogistics, Japan Post, Mainfreight e outras também usam 11 dígitos, então o formato sozinho não confirma Blue Dart. |
| `48291736545` | O mesmo formato com dígito verificador incorreto. O Peek não sugere Blue Dart para ele: confira se há erro de digitação. |
| `4829173654` | 10 dígitos: não é um código Blue Dart. Se você escolher Blue Dart para ele, o Peek avisa que a Blue Dart "não emite códigos como este". O mesmo vale para 12 dígitos ou códigos com letras. |

Os códigos de 11 dígitos da Aramex e a correspondência registrada da [Japan Post](carrier:japan-post) usam o mesmo dígito verificador, portanto um número válido pode pertencer a elas: confira qual transportadora aparece na mensagem da loja. Espaços e hífens não importam ao colar no Peek. O rastreador da Blue Dart também busca por outras referências, mas o Peek precisa do número de conhecimento (waybill). Não encontra o seu? Veja [como encontrar seu código de rastreamento](guide:find-tracking-number).

## Status de rastreamento da Blue Dart explicados

O rastreador da Blue Dart exibe o status atual da remessa e, abaixo, as movimentações da mais nova para a mais antiga, cada uma com local, data e hora. As linhas que o Peek conhece do rastreador da Blue Dart, aproximadamente em ordem:

| A Blue Dart diz | O que significa para você | O Peek mostra |
| --- | --- | --- |
| `Pickup Employee Is Out To P/U Shipment` | Um entregador da Blue Dart está a caminho para coletar (P/U) a remessa com o remetente. A Blue Dart ainda não a tem. | "Etiqueta criada" |
| `Shipment Picked Up` | A Blue Dart coletou a remessa com o remetente. | "Recebida pela transportadora" |
| `Shipment Arrived`, `Shipment Arrived At Hub` | Chegou a uma unidade ou centro de triagem da Blue Dart, indicado no local da leitura. | "Em trânsito" |
| `Paper Work Inscan` | Leitura documental em um hub, entre a chegada ali e a conexão seguinte. | "Em trânsito" |
| `Clubbed Canvas Bag Scan` | Registrada em um malote junto com outras remessas. | "Em trânsito" |
| `Shipment Further Connected` | Expedida dessa unidade para a próxima etapa da rota. | "Em trânsito" |
| `Delivery Scheduled For Next Working Day` | Na unidade entregadora, mas após o horário de corte do dia: sairá na rota do próximo dia útil. | "Em trânsito" |
| `Shipment Out For Delivery` | Em rota de entrega. | "Saiu para entrega" |
| `Cnee Refused Id/Otp Not Shared-Incorrect` | Não entregue por recusa do destinatário (consignee) ou porque documento de identidade ou código único (OTP) não foi informado ou estava incorreto. | "Tentativa de entrega" |
| `Delivery Delayed`, `Delay Caused Beyond Control` | A entrega está atrasada; a segunda linha atribui o atraso a causas fora do controle da Blue Dart. | "Precisa de atenção" |
| `Shipment Delivered` | Entregue. | "Entregue" |
| `Shipment Returned Back To Shipper` | A remessa voltou ao remetente. A Blue Dart mostra esse status quando a devolução é concluída. | "Devolvida ao remetente" |

Uma linha que o Peek ainda não conhece aparece como a Blue Dart escreve e conta como "Em trânsito", a menos que a encomenda já tenha saído para entrega ou avançado além disso — nesse caso o status se mantém. Veja mais em [significado dos status de rastreamento](guide:tracking-statuses).

## Entrega da Blue Dart na Índia: documento e OTP

O Peek não conseguiu verificar as regras de entrega da Blue Dart, como quantas tentativas são feitas ou por quanto tempo uma remessa não entregue fica guardada, então esta página não informa esses dados.

**Documento ou OTP.** `Cnee Refused Id/Otp Not Shared-Incorrect` indica que uma entrega da Blue Dart pode exigir documento de identidade ou código único (OTP). Se você recebeu um código para a remessa, deixe-o à mão no dia da entrega. Se essa linha surgir sem que você tenha recusado nada, peça à loja para combinar nova entrega. Veja [entrega não realizada](guide:missed-delivery) e [encomendas não retiradas](guide:uncollected-parcel).

Recebeu uma mensagem sobre falha na entrega pedindo pagamento ou código por um link? Veja [golpes por SMS de entrega](guide:delivery-scam-texts).

## Remessas Blue Dart: quem entrega e alfândega

**Conhecimento Blue Dart.** O Peek não conhece nenhuma transportadora para a qual a Blue Dart transfira suas remessas, na Índia ou no exterior. Se a loja fornecer também o código de outra transportadora, adicione-o separadamente.

**Enviada pela ICC Worldwide.** O rastreador da ICC Worldwide pode citar a Blue Dart junto a um código da Blue Dart. O Peek continua acompanhando na Blue Dart apenas quando esse código tem 11 dígitos: ele consulta a Blue Dart e, quando surgir uma leitura pelo menos tão recente quanto a última da ICC Worldwide, segue por lá mantendo o histórico da ICC Worldwide. Com qualquer outro número, permanece na ICC Worldwide. Adicione a remessa pelo código da ICC Worldwide e toque em "Trocar" buscando por ICC Worldwide: o Peek não a sugere pelo formato.

**Alfândega.** O código do Peek conhece a Blue Dart apenas na Índia e não pôde consultar o site dela sobre envios internacionais. Para encomendas retidas na alfândega, veja [encomendas retidas na alfândega](guide:customs).

## Rastreamento Blue Dart parado, atrasado ou devolvido

Para reembolso ou reenvio, seu contato é a loja: foi ela quem contratou a Blue Dart.

**Nenhuma leitura ainda.** Para um código sem registro, o rastreador da Blue Dart responde `There is no information on the Waybill/Reference Number/Order Number currently.` O Peek continua consultando. Confira os 11 dígitos na mensagem da loja; se nada surgir em alguns dias, pergunte à loja se a remessa já foi entregue à transportadora.

**Parada em `Pickup Employee Is Out To P/U Shipment`.** A Blue Dart ainda não coletou a remessa com o remetente. Pergunte à loja quando fará a entrega à transportadora.

**`Delivery Delayed` ou `Delay Caused Beyond Control`.** O Peek mostra "Precisa de atenção". Se nada mudar depois, fale com a loja.

**Dias sem atualização.** Quando uma remessa ainda em trânsito fica quatro dias sem nova leitura, o Peek destaca o dia da última movimentação: "Sem atualizações desde seg 12 out". Veja [por que o rastreamento para de atualizar](guide:tracking-not-updating).

**Marcada como entregue, mas não chegou.** Pergunte em casa ou na portaria e depois procure a loja. Veja [encomenda marcada como entregue mas não recebida](guide:delivered-not-received).

**Extraviada ou devolvida.** Peça reembolso ou novo envio à loja: veja [encomendas extraviadas](guide:lost-parcel). `Shipment Returned Back To Shipper` significa que retornou ao remetente: veja [encomenda devolvida ao remetente](guide:returned-to-sender).

## Atendimento ao cliente e contato da Blue Dart

O Peek não conseguiu ler o site da Blue Dart, portanto esta página não lista telefone nem e-mail da Blue Dart: o Peek só informa contatos verificados nas páginas da própria transportadora.

Em compras online, fale primeiro com a loja: ela contratou a Blue Dart. O Peek não pode falar com a Blue Dart nem com a loja por você.

## Acompanhe uma remessa Blue Dart com o Peek

Cole o código no [Peek](/). Várias transportadoras usam 11 dígitos, então, só pelo código, o Peek não mostra "Transportadora detectada" para a Blue Dart:

- **Na Índia** (pela região do celular ou local de conexão na web), o Peek exibe "Consultando serviços de rastreamento…" enquanto consulta as transportadoras compatíveis, começando pela Blue Dart. Quando a Blue Dart reconhece o código, aparece "Blue Dart está com esta encomenda."
- **Fora da Índia**, o Peek consulta a Blue Dart por último, depois que as outras transportadoras e os serviços de rastreamento não encontraram o código. Para classificar a remessa em Blue Dart desde já, toque em "Trocar" e selecione Blue Dart em "Compatível com este código".

Se nenhuma transportadora reconhecer o código ainda, adicione mesmo assim ou escolha Blue Dart manualmente: o Peek acompanha mais de 4.500 transportadoras. O Peek lista Blue Dart nas sugestões somente quando o dígito verificador confere. Se o seu não conferir mas você tiver certeza do número, busque Blue Dart na lista completa: o Peek consulta mesmo assim. Um link de rastreamento de bluedart.com com `trackNo=` também funciona: o Peek extrai o código e identifica Blue Dart imediatamente ("Transportadora detectada").

Classificada em Blue Dart, a remessa mostra "Aguardando Blue Dart" até a primeira leitura, ou "Confira o código de rastreamento" se a Blue Dart continuar sem reconhecer o código após dois dias; adicionada sem transportadora, mostra "Ainda sem histórico de rastreamento". O Peek consulta o rastreador público da Blue Dart apenas pelo código, sem conta: mostra o status dado pela Blue Dart e cada leitura nas palavras dela com o local, lido no horário da Índia. Omite dados do destinatário e não exibe data prevista de entrega. O link "Abrir o site de Blue Dart", na página da encomenda, abre o código no rastreador da Blue Dart.

Enquanto você mantiver a remessa aberta, ou no dia da entrega com as notificações ativadas, e o próprio rastreador da Blue Dart responder, o Peek consulta até a cada 10 minutos, e até a cada 2 minutos a partir das 6h no horário local no dia em que a Blue Dart registra a saída para entrega; fora isso, a cada hora. Após dois dias sem novidades (12 horas enquanto a remessa ainda mostrar "Etiqueta criada" ou "Recebida pela transportadora"), o Peek verifica a cada hora. Quando apenas os serviços de rastreamento respondem, ele verifica a cada hora, a cada 3 horas quando a leitura mais recente tem um dia e até a cada 15 minutos no dia em que a remessa sai para entrega enquanto você a mantiver aberta ou com notificações ativadas. Alertas: "Cada movimentação", "Etapas importantes" ou "Só a entrega".

## Perguntas sobre o rastreamento Blue Dart

### Como rastrear uma remessa da Blue Dart?

Digite o código de 11 dígitos no rastreador da Blue Dart em bluedart.com ou cole-o no Peek. Na Índia, o Peek consulta a Blue Dart primeiro; fora da Índia, consulta a Blue Dart por último, ou você pode tocar em "Trocar" e escolher Blue Dart.

### Como é um código de rastreamento da Blue Dart?

11 dígitos, sendo o último um dígito verificador. Aramex e outras empresas também usam 11 dígitos, então confirme se a mensagem da loja cita Blue Dart.

### O que significa "Shipment Further Connected" no rastreamento da Blue Dart?

A Blue Dart encaminhou a remessa de uma de suas unidades para a próxima. O Peek exibe "Em trânsito".

### O que significa "Cnee Refused Id/Otp Not Shared-Incorrect"?

Uma entrega não realizada devido à recusa do destinatário (consignee) ou a um documento de identidade ou código único (OTP) não fornecido ou incorreto. O Peek exibe "Tentativa de entrega". Se você não recusou nada, peça à loja que combine outra entrega.

### Quem procurar se minha remessa Blue Dart sumiu?

A loja ou o remetente que contratou a Blue Dart. Como o Peek não pôde consultar o site da Blue Dart, esta página não informa contatos diretos dela.

:::sources
- [Universal Parcel Scraper: Blue Dart](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/blue-dart) – Índia, código de 11 dígitos e dígito verificador, rastreador e resposta sem informações, textos e fases, horário da Índia, o que fica de fora, links de rastreamento
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – números de 11 dígitos de outras transportadoras, dígito verificador da Aramex e Japan Post, transportadoras consultadas na Índia e no exterior, vínculo da ICC Worldwide com a Blue Dart e regra dos 11 dígitos, ausência de transferência a partir da Blue Dart
:::
