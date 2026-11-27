---
title: Rastreio Cyprus Post: códigos, status e alfândega
description: Rastreie envios dos Correios de Chipre (Cyprus Post): código S10 de 13 caracteres, status UPU em inglês, taxas da UE fora do bloco e onde reclamar.
slug: cyprus-post-tracking
published: 2026-11-27
updated: 2026-11-27
---

O Cyprus Post (Κυπριακά Ταχυδρομεία), Departamento de Serviços Postais, é o correio nacional da República de Chipre. Rastreie um objeto no site oficial cypruspost.post ou cole o código na caixa acima. Não tem nenhum código? Veja [onde encontrar o código de rastreio](guide:find-tracking-number).

## Código de rastreio Cyprus Post

O rastreador do Cyprus Post aceita os códigos de 13 caracteres do padrão S10 da União Postal Universal (UPU), emitidos pelos correios para registrados, EMS, encomendas e cartas rastreadas: [duas letras, nove dígitos e duas letras](guide:tracking-number-formats).

:::anatomy RR 482917367 CY
- RR | Indicador de serviço: a primeira letra indica o tipo de objeto
- 482917367 | Número serial de oito dígitos seguido por um dígito verificador
- CY | Código do país: CY para códigos emitidos sob autoridade de Chipre
:::

| Primeira letra (exemplos inventados) | Tipo de objeto |
| --- | --- |
| `RR482917367CY` | R: objeto registrado |
| `CP482917367CY` | C: encomenda |
| `EE482917367CY` | E: envio EMS |
| `LA482917367CY` | L: carta rastreada |
| `UA482917367CY` | U: objeto de correspondência com mercadoria, sem rastreio para o cliente |

Um objeto postado no exterior mantém o código emitido pelo correio de origem, terminando com a sigla daquele país (`…GB`, `…FR`, `…GR`), e o rastreador do Cyprus Post também aceita esse código junto com os registros do correio estrangeiro. `There is no information for this item` significa que o rastreador não localizou o número: confira com o remetente.

## Significado dos status de rastreio Cyprus Post

Na versão em inglês do rastreador do Cyprus Post, que é a lida pelo Peek, cada registro traz horário, país, local, evento, próxima agência (*next office*) e informações extras (*Extra Information*). Os eventos seguem os [nomes postais internacionais da UPU](guide:tracking-statuses): `(Otb)` marca etapas no país de postagem e `(Inb)` etapas no país de destino. A próxima agência indica para onde o objeto vai seguir, não onde está agora. Para uma encomenda a caminho do seu endereço:

| Status do Cyprus Post | O que significa para você |
| --- | --- |
| `Receive item from customer (Otb)` | O objeto foi postado em Chipre ou no exterior. |
| `Insert item into bag (Otb)` | Colocado em mala postal no centro de permuta para embarque internacional. |
| `Receive item at office of exchange (Inb)` | Chegou ao centro de permuta do país de destino. |
| `Send items to delivery point/ collection point` | A caminho da agência que fará a entrega ou guardará para retirada. |
| `Receive item at delivery office (Inb)` | Chegou à unidade de entrega. |
| `Hold item at point of delivery (Inb)` | Na unidade de entrega, aguardando saída em rota. |
| `Handover item to delivery agent (Inb)` | Com o carteiro para entrega. |
| `Item not delivered/ Pending delivery` | Não entregue: a coluna Extra Information no site oficial explica o motivo. |
| `Receive item at collection point for pick-up (Inb)` | Pronto para retirada na agência dos correios. |
| `Deliver item (Inb)` | Entregue ou retirado na agência. |

## Taxas alfandegárias sobre compras de fora da UE

Desde 1º de julho de 2026, mercadorias compradas on-line fora da União Europeia — incluindo envios postais — deixaram de entrar isentas de direitos aduaneiros quando a remessa vale até € 150: a UE cobra uma tarifa aduaneira temporária de € 3 por item. Um item corresponde a todos os bens na remessa que compartilhem classificação tarifária, descrição e, quando declarada, origem, independentemente da quantidade de peças. Desde 1º de novembro de 2026, a UE também cobra uma taxa de manuseio da União de € 2 por item sobre compras on-line vindas de fora do bloco, qualquer que seja o valor, incidindo IVA sobre essa taxa. O pagamento cabe prioritariamente a quem declara a mercadoria à alfândega: plataforma, vendedor, transportadora ou despachante. Em Chipre, o Departamento de Alfândega e Impostos Especiais (Department of Customs and Excise) cobra imposto de importação e IVA sobre mercadorias vindas de fora da UE. Veja mais em [encomendas retidas na alfândega](guide:customs).

## Encomenda Cyprus Post atrasada ou não recebida

Se o rastreio parou de avançar, veja [rastreio sem atualização](guide:tracking-not-updating). Se [consta como entregue](guide:delivered-not-received) mas não está com você, confira o código com o remetente. Para pacotes perdidos ou quebrados, consulte [encomendas perdidas](guide:lost-parcel) e [encomendas danificadas](guide:damaged-parcel).

**Comprou on-line e ainda aguarda?** O Serviço de Proteção ao Consumidor de Chipre informa que, em regra, o comerciante deve entregar em até 30 dias, salvo outro prazo acordado. Se não cumprir, notifique a loja concedendo prazo adicional razoável; persistindo a falta de entrega, você pode rescindir o contrato. A Linha do Consumidor 1429 é gratuita.

**Recebeu mensagem cobrando taxa para liberar um pacote?** Leia [golpes por SMS de entrega](guide:delivery-scam-texts) antes de tocar em qualquer link.

## Como acompanhar encomendas Cyprus Post no Peek

Para um código postal terminado em `CY` com dígito verificador válido, o [Peek](/) consulta o Cyprus Post e a [Chronopost](carrier:chronopost), informando quando o Cyprus Post reconhece o código ou avisando que ainda não foi encontrado e que continuará verificando depois que você adicionar. Se o dígito verificador estiver incorreto, o Peek não consulta o Cyprus Post mesmo que você o selecione em "Alterar": confira o código com o remetente. Links de cypruspost.post funcionam quando incluem o número.

Para objetos postados no exterior, o Peek consulta primeiro o correio emissor sempre que possível — como a ELTA para `…GR` — ou serviços de rastreamento. O rastreador do Cyprus Post também aceita esses códigos: basta escolher "Cyprus Post" em "Alterar". O Peek consulta o Cyprus Post automaticamente quando o correio estrangeiro indica Chipre como destino ou, sem destino indicado, quando você adiciona o pacote a partir de Chipre, alternando assim que o Cyprus Post tiver registros tão recentes quanto os do correio estrangeiro. O Peek só consulta o Cyprus Post com códigos de 13 caracteres. Não sabe qual é a transportadora? Cole o número mesmo assim: o Peek acompanha mais de 4.500 transportadoras.

O Peek exibe os status do Cyprus Post como escritos, mostra a agência ou unidade e o país de cada leitura no mapa, informa o horário de entrega e indica a agência onde o objeto aguarda ou foi retirado. Leituras em Chipre mantêm o horário exato, e registros de correios estrangeiros são interpretados no relógio daquele país; em países com vários fusos, o Peek estima o fuso pelo local informado, podendo haver pequena diferença de hora. Enquanto o objeto estiver aberto no Peek, ou no dia da entrega com notificações ligadas, o Peek consulta o Cyprus Post diretamente até a cada 10 minutos — ou a cada 2 minutos a partir das 6h no horário de Chipre no dia em que sai com o carteiro, enquanto o rastreador oficial responder — e pode notificar mudanças de status. Após dois dias sem novidades (12 horas enquanto ainda mostrar "Etiqueta criada" ou "Recebida pela transportadora"), verifica de hora em hora. Após quatro dias sem movimentação em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito", exibe a data do último evento (por exemplo, "Sem atualização de rastreamento desde seg 12 out") com a nota "É comum uma encomenda ficar sem atualizações em voos longos, na alfândega ou no fim de semana. O Peek continua consultando Cyprus Post." e um link de ajuda. O Peek não exibe previsão de entrega nem peso e omite Extra Information, próxima agência, códigos postais e quem assinou: para saber o motivo de uma entrega não realizada, consulte cypruspost.post.

## Perguntas sobre o rastreio Cyprus Post

### Cyprus Post é o Departamento de Serviços Postais?

Sim. A União Postal Universal lista como operador designado de Chipre o Department of Postal Services (Cyprus Post), órgão estatal responsável pelos serviços postais, e o regulador postal OCECPR designa o Cyprus Post como prestador do serviço postal universal.

### O que significa CY no final do código?

Indica que o código foi emitido sob autoridade de Chipre. Pelo padrão S10 da UPU, as duas últimas letras identificam o país sob cuja autoridade o número foi emitido, não garantindo necessariamente onde o objeto foi postado. O Peek consulta o Cyprus Post para esses códigos.

### Posso rastrear um objeto com código iniciado por U?

Não como envio rastreado. O padrão S10 atribui códigos com U a correspondências com mercadorias que viajam sem rastreamento para o cliente: o número serve apenas para visibilidade na cadeia logística, como nos dados eletrônicos enviados antecipadamente. Ainda assim, o Peek consulta o Cyprus Post quando um código U termina em `CY`.

### Como fazer uma reclamação sobre o Cyprus Post?

Escreva primeiro ao próprio Cyprus Post. Se ele não responder em até 15 dias, ou se a resposta não for satisfatória, reclame junto ao regulador postal, o Office of the Commissioner for Electronic Communications and Postal Regulation (OCECPR). A reclamação pode ser enviada pela internet; o site está em grego, mas oferece formulário de reclamação em inglês.

:::sources
- [Universal Postal Union: S10, identification of postal items](https://www.upu.int/UPU/media/upu/files/postalSolutions/programmesAndServices/standards/S10-12.pdf) – 13 caracteres, letras iniciais R, C, E, L e U, dígito verificador, código do país emissor
- [Universal Postal Union: member countries, Cyprus](https://www.upu.int/en/Universal-Postal-Union/About-UPU/Member-Countries?csid=-1&cid=86) – Cyprus Post, operador designado
- [Universal Postal Union: list of designated operators](https://www.upu.int/getmedia/0ff98b1d-9dd4-46e7-bdd1-dda1b654308f/SettlementCircularsEn.pdf) – Department of Postal Services (Cyprus Post)
- [National Open Data Portal: list of post offices](https://www.data.gov.cy/el/dataset/katalogos-kai-theseis-tahydromikon-grafeion) – Departamento de Serviços Postais, órgão estatal responsável pelos serviços postais, em grego
- [OCECPR: universal postal service](https://ocecpr.ee.cy/katanalotes/katholiki-ypiresia/taxidromeia) – Cyprus Post como prestador do serviço postal universal, em grego
- [OCECPR: complaints](https://ocecpr.ee.cy/katanalotes/parapona) – reclamar primeiro por escrito ao prestador, prazo de 15 dias, reclamação on-line, formulário em inglês, página em grego
- [Consumer Protection Service: online purchases](https://consumer.gov.cy/en/consumer-rights/66/59/?ctype=ar) – entrega em até 30 dias, prazo extra, rescisão contratual, em grego
- [Consumer Protection Service: contact us](https://consumer.gov.cy/en/contact-us) – Linha do Consumidor gratuita 1429, em grego
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – tarifa de € 3 por item desde 1º de julho de 2026 e taxa de manuseio da União de € 2 desde 1º de novembro de 2026
- [European Commission: guidance on the EUR 3 customs duty and the Union handling fee](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – definição de item, remessas postais, taxa independente do valor, quem paga, IVA sobre a taxa
- [Department of Customs and Excise: About us](https://www.mof.gov.cy/mof/customs/customs.nsf/All/289500205CFF33DFC22572AC003A2B02?OpenDocument) – cobrança de imposto de importação e IVA sobre mercadorias importadas
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – o que o Peek lê: rastreador em inglês do Cyprus Post, colunas e nomes de eventos
:::
