---
title: Seguimento Cyprus Post: números, estados e alfândega
description: Segue um envio Cyprus Post: código de 13 carateres, significado dos estados UPU em inglês, taxas da UE para compras fora da UE e reclamações.
slug: seguimento-cyprus-post
published: 2026-11-22
updated: 2026-11-22
---

A Cyprus Post (Κυπριακά Ταχυδρομεία), o Department of Postal Services, é o serviço postal nacional da República de Chipre. Segue um objeto no site da Cyprus Post, cypruspost.post, ou cola o número na caixa acima. Não tens número? Consulta [como encontrar o teu número de seguimento](guide:find-tracking-number).

## Número de seguimento Cyprus Post

O seguimento da Cyprus Post aceita os números de 13 carateres da norma S10 da União Postal Universal, atribuídos pelos correios a objetos registados, EMS, encomendas e cartas com seguimento: [duas letras, nove dígitos e duas letras](guide:tracking-number-formats).

:::anatomy RR 482917367 CY
- RR | Indicador de serviço: a primeira letra indica o tipo de objeto
- 482917367 | Número de série de oito dígitos seguido de um dígito de controlo
- CY | Código de país: CY para um número emitido sob autoridade de Chipre
:::

| Primeira letra (exemplos inventados) | O que é |
| --- | --- |
| `RR482917367CY` | R: objeto registado |
| `CP482917367CY` | C: encomenda postal |
| `EE482917367CY` | E: objeto EMS |
| `LA482917367CY` | L: carta com seguimento |
| `UA482917367CY` | U: objeto de correspondência com mercadorias, sem seguimento para o cliente |

Um objeto expedido no estrangeiro mantém o código emitido pelos correios de origem terminado no respetivo código de país (`…GB`, `…FR`, `…GR`), e o seguimento da Cyprus Post aceita-o também com os registos dos correios estrangeiros. `There is no information for this item` significa que o sistema não tem dados sobre esse número: confirma-o com o remetente.

## O que significam os estados da Cyprus Post

Na versão em inglês do seguimento da Cyprus Post lida pelo Peek, cada registo apresenta hora, país, local, evento, estação seguinte e informação extra. Os eventos seguem os [nomes postais internacionais (UPU)](guide:tracking-statuses): `(Otb)` indica uma etapa no país de expedição e `(Inb)` no país de destino. A estação seguinte indica para onde o objeto segue, não onde está. Para um objeto dirigido a ti:

| Estado da Cyprus Post | O que significa para ti |
| --- | --- |
| `Receive item from customer (Otb)` | O objeto foi entregue nos correios, em Chipre ou no estrangeiro. |
| `Insert item into bag (Otb)` | Ensacado na estação de permuta para seguir para o estrangeiro. |
| `Receive item at office of exchange (Inb)` | Chegou ao país de destino. |
| `Send items to delivery point/ collection point` | A caminho da estação que vai entregá-lo ou guardá-lo para levantamento. |
| `Receive item at delivery office (Inb)` | Na estação responsável pela distribuição. |
| `Hold item at point of delivery (Inb)` | Na estação de distribuição, a aguardar a volta. |
| `Handover item to delivery agent (Inb)` | Entregue ao carteiro. |
| `Item not delivered/ Pending delivery` | Não entregue: a coluna Extra Information indica o motivo. |
| `Receive item at collection point for pick-up (Inb)` | Pronto para levantamento na estação de correios. |
| `Deliver item (Inb)` | Entregue, ou levantado na estação de correios. |

## Encargos aduaneiros em compras fora da UE

Desde 1 de julho de 2026, os bens comprados online fora da UE — incluindo objetos postais — deixaram de entrar isentos de direitos quando a remessa vale 150 € ou menos: a UE cobra um direito aduaneiro temporário de 3 € por artigo. Um artigo reúne todos os bens da remessa com a mesma classificação pautal, descrição e, quando declarada, origem, independentemente do número de unidades. Desde 1 de novembro de 2026 a UE cobra ainda uma taxa de tratamento da União de 2 € por artigo nas compras online fora da UE, qualquer que seja o valor, sujeita a IVA. O pagamento compete em primeiro lugar a quem declara os bens à alfândega: plataforma, vendedor, transportadora ou representante. Em Chipre, o Department of Customs and Excise aplica direitos de importação e IVA sobre bens fora da UE. Mais em [encomendas retidas na alfândega](guide:customs).

## Objeto Cyprus Post atrasado ou não recebido

Se o seguimento parou, vê [seguimento que não atualiza](guide:tracking-not-updating). Se [consta como entregue](guide:delivered-not-received) mas não o tens, confirma o número com o remetente. Em caso de extravio ou dano, vê [encomenda perdida](guide:lost-parcel) e [encomenda danificada](guide:damaged-parcel).

**Compraste online e continuas à espera?** O Consumer Protection Service de Chipre refere que o vendedor tem, por regra, de entregar em 30 dias salvo outra data combinada. Se não cumprir, informa-o e concede um prazo adicional razoável; se continuar sem entregar, podes cancelar o contrato. A linha ao consumidor, 1429, é gratuita.

**Uma mensagem pede pagamento para libertar uma encomenda?** Lê [SMS falsos de entrega](guide:delivery-scam-texts) antes de carregares em qualquer link.

## Seguir objetos Cyprus Post com o Peek

Para um número postal acabado em `CY` com dígito de controlo válido, o [Peek](/) pergunta ao mesmo tempo à Cyprus Post e à [Chronopost](carrier:chronopost), mostrando «Cyprus Post tem esta encomenda.» quando a Cyprus Post o conhece, ou «Ainda não encontrada em… O Peek continua a verificar depois de adicionares.». Com dígito de controlo errado, o Peek não consulta a Cyprus Post mesmo que a escolhas em «Alterar»: confirma o número com o remetente. Um link de cypruspost.post funciona quando contém o número.

Para um objeto enviado do estrangeiro, o Peek pergunta primeiro aos correios emissores quando suportados — como os [ELTA](carrier:elta) para `…GR` — ou aos serviços de seguimento. O seguimento da Cyprus Post também aceita esses códigos: escolhe «Cyprus Post» em «Alterar». O Peek tenta a Cyprus Post automaticamente quando os correios estrangeiros indicam Chipre como destino ou, sem destino indicado, quando adicionas a partir de Chipre, mudando assim que a Cyprus Post tiver registos tão recentes quanto os dos correios estrangeiros. O Peek só pergunta à Cyprus Post por números de 13 carateres. Sem certeza da transportadora? Cola o número na mesma: o Peek segue mais de 4500 transportadoras.

O Peek apresenta os estados da Cyprus Post como escritos, a estação ou centro e o país de cada registo no mapa, a hora da entrega e a estação onde aguarda ou foi levantado. Os registos cipriotas mantêm a hora exata e os estrangeiros são lidos no relógio do respetivo país (quando um país tem vários fusos, o Peek calcula o fuso a partir do local indicado, podendo variar a hora), tudo mostrado no teu fuso horário («Horas no teu fuso horário:»). Não há data prevista nem peso, e o Peek descarta Extra Information, estação seguinte, códigos postais e quem assinou: para saber o motivo de uma entrega falhada, consulta cypruspost.post.

O Peek consulta diretamente a Cyprus Post até de 10 em 10 minutos, ou de 2 em 2 minutos a partir das 6:00 no relógio do último registo (hora de Chipre em Chipre) no dia em que sai com o carteiro, enquanto o objeto estiver aberto no Peek ou no dia da entrega com as notificações ligadas. Após dois dias sem novidades (12 horas enquanto mostrar «Etiqueta criada» ou «Aceite pela transportadora»), o Peek verifica de hora a hora. Para as notificações, podes escolher «Todos os registos», «Passos importantes» ou «Só a entrega». Após quatro dias sem registos num objeto em «Etiqueta criada», «Aceite pela transportadora» ou «Em trânsito», o Peek mostra «Sem atualizações desde…», explica que «É habitual uma encomenda ficar sem novidades em voos longos, na alfândega ou ao fim de semana. O Peek continua a perguntar a Cyprus Post.» e disponibiliza «Pede ajuda a Cyprus Post».

## Perguntas frequentes sobre Cyprus Post

### A Cyprus Post é o Department of Postal Services?

Sim. A União Postal Universal lista o Department of Postal Services (Cyprus Post) como operador designado de Chipre, o serviço estatal responsável pelos serviços postais, e o regulador postal OCECPR designa a Cyprus Post como prestador do serviço postal universal.

### O que significa CY no fim do número?

Que o número foi emitido sob a autoridade de Chipre. Na norma S10 da UPU as duas últimas letras representam o país sob cuja autoridade o código foi emitido, pelo que não provam com certeza onde o objeto foi expedido. O Peek consulta a Cyprus Post para estes números.

### Posso seguir um objeto com número começado por U?

Não como envio com seguimento. A norma S10 atribui números U a objetos de correspondência com mercadorias que viajam sem seguimento para o cliente: o código dá visibilidade na cadeia logística, como nos dados eletrónicos enviados previamente. O Peek ainda assim consulta a Cyprus Post num código U terminado em `CY`.

### Como apresentar reclamação sobre a Cyprus Post?

Escreve primeiro à Cyprus Post. Se não responder em 15 dias ou a resposta não te satisfizer, reclama junto do regulador postal, o Office of the Commissioner for Electronic Communications and Postal Regulation (OCECPR). Podes fazê-lo online; o site está em grego, mas disponibiliza um formulário de reclamação em inglês.

:::sources
- [Universal Postal Union: S10, identification of postal items](https://www.upu.int/UPU/media/upu/files/postalSolutions/programmesAndServices/standards/S10-12.pdf) – 13 carateres, letras iniciais R, C, E, L e U, dígito de controlo, código do país emissor
- [Universal Postal Union: member countries, Cyprus](https://www.upu.int/en/Universal-Postal-Union/About-UPU/Member-Countries?csid=-1&cid=86) – Cyprus Post, operador designado
- [Universal Postal Union: list of designated operators](https://www.upu.int/getmedia/0ff98b1d-9dd4-46e7-bdd1-dda1b654308f/SettlementCircularsEn.pdf) – Department of Postal Services (Cyprus Post)
- [National Open Data Portal: list of post offices](https://www.data.gov.cy/el/dataset/katalogos-kai-theseis-tahydromikon-grafeion) – Department of Postal Services, serviço estatal responsável pelos serviços postais (em grego)
- [OCECPR: universal postal service](https://ocecpr.ee.cy/katanalotes/katholiki-ypiresia/taxidromeia) – Cyprus Post como prestador do serviço postal universal (em grego)
- [OCECPR: complaints](https://ocecpr.ee.cy/katanalotes/parapona) – primeiro por escrito ao operador, 15 dias, reclamação online, formulário em inglês (em grego)
- [Consumer Protection Service: online purchases](https://consumer.gov.cy/en/consumer-rights/66/59/?ctype=ar) – entrega em 30 dias, prazo adicional, resolução do contrato (em grego)
- [Consumer Protection Service: contact us](https://consumer.gov.cy/en/contact-us) – linha gratuita de apoio ao consumidor 1429 (em grego)
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – direito de 3 € por artigo desde 1 de julho de 2026, taxa de tratamento da União de 2 € desde 1 de novembro de 2026
- [European Commission: guidance on the EUR 3 customs duty and the Union handling fee](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – definição de artigo, envios postais, taxa para qualquer valor, responsável pelo pagamento, IVA sobre a taxa
- [Department of Customs and Excise: About us](https://www.mof.gov.cy/mof/customs/customs.nsf/All/289500205CFF33DFC22572AC003A2B02?OpenDocument) – aplicação de direitos de importação e IVA
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – dados lidos pelo Peek: seguimento inglês da Cyprus Post, colunas e nomes de eventos
:::
