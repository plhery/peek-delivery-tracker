---
title: TopYou rastreio: número TYZR…YQ, estados e entrega
description: Segue uma encomenda TopYou (通邮) vinda da China: como é o número TYZR…YQ, o que significam as leituras em inglês, quem entrega em Portugal e a alfândega.
slug: topyou-rastreio
published: 2026-11-17
updated: 2026-11-17
---

A TopYou (通邮, Tongyou, também 通邮集团 ou SZTY56) é a 深圳市通邮物流科技（集团）股份有限公司, um transitário internacional sediado em Shenzhen, na China, que transporta compras online nas suas próprias linhas internacionais e postais. Se uma loja chinesa expediu a tua encomenda pela TopYou, o número começa por TYZ e acaba em YQ: cola-o na caixa acima. O seguimento da TopYou acompanha o pacote desde o armazém na China até ao voo e à alfândega, passando depois a entrega a uma transportadora local.

## Número de rastreio TopYou: como é

Os códigos da TopYou juntam TYZR, uma letra, dez algarismos e YQ: 17 caracteres no total. Alguns trazem outra letra no lugar do R. Com algarismos inventados:

| Número | O que é |
| --- | --- |
| `TYZRA0000000017YQ` | TYZR, uma letra, dez algarismos e YQ: um número de envio da TopYou. |
| `TYZRA000000017YQ` | Só nove algarismos: não tem o formato da TopYou. Confirma se falta um algarismo. |
| `JXCAB0000000017YQ` | Também termina em YQ, como noutros transitários chineses, mas começa por JXC: é um código da Juxi Express (que o Peek verifica primeiro na [DHLink](carrier:dhlink) quando a JXC se seguem YI, ZO, US ou ZI), não da TopYou. |

No Peek não faz diferença se colares espaços ou letras minúsculas. Já o site de pesquisa da TopYou, [track.szty56.com](https://track.szty56.com/), distingue maiúsculas de minúsculas (aceita até 20 códigos por pesquisa, um por linha): escreve-o lá sempre em maiúsculas. O Peek não lê ligações copiadas desse site, por isso cola apenas o número. Se não o encontrares na encomenda, vê [como encontrar o número de seguimento](guide:find-tracking-number).

**Um segundo número.** O registo da TopYou também pode guardar o código próprio da transportadora que faz a entrega final, visível na página de seguimento da TopYou.

## Estados de seguimento da TopYou: o que significa cada um

A TopYou escreve as suas leituras em inglês, e o Peek mostra-as tal como vêm. Pela ordem habitual:

| O que escreve a TopYou | O que significa para ti | O Peek mostra |
| --- | --- | --- |
| `Parcel Data Received` | A loja enviou os dados eletrónicos do envio, mas a TopYou ainda não recebeu a encomenda. | «Etiqueta criada» |
| `Arrived at the first mile sorting center` | Primeira leitura física: o armazém da TopYou na China já tem a encomenda. | «Aceite pela transportadora» |
| `Departed from the first mile sorting center` | Saiu desse primeiro armazém na China. | «Em trânsito» |
| `Arrived at airport of the original country` | Chegou ao aeroporto de partida, na China, antes do voo. | «Em trânsito» |
| `Departed from Airport to destination country` | O voo partiu em direção ao país de destino. | «Em trânsito» |
| `Arrived at destination airport` | Aterrou no aeroporto do país de destino. | «Em trânsito» |
| `Departed from customs` | A alfândega do país de destino deu saída ao pacote. No site da TopYou a frase termina com a palavra `of` sem país a seguir; o Peek retira essa palavra solta. | «Em trânsito» |

**Números mais recentes.** Nestes envios, a TopYou descreve as etapas com outras frases:

| O que escreve a TopYou | O que significa para ti | O Peek mostra |
| --- | --- | --- |
| `Carrier received the shipment` | A TopYou recebeu a encomenda na China: é a primeira leitura física. | «Aceite pela transportadora» |
| `Shipment arrived at a carrier facility` | A encomenda avançou a partir do primeiro armazém da TopYou; não está a ser recolhida de novo. | «Em trânsito» |
| `Shipment started the customs clearance process at origin` | Despacho aduaneiro de exportação na China, um passo de rotina antes do voo: não está retida nem chegou ainda à alfândega portuguesa. | «Em trânsito» |
| `Shipment arrived in the destination country` | Chegou ao país de destino. | «Em trânsito» |
| `Shipment started the customs clearance process at destination` | Começou o desalfandegamento de importação no país de destino. | «Na alfândega» |
| `Shipment completed the customs clearance process at destination` | A alfândega terminou a verificação e a encomenda segue para a transportadora que a entrega. | «Em trânsito» |
| `Shipment is out for delivery` | Apesar do nome em inglês, ainda não saiu para a tua morada: marca a passagem à transportadora local (como a GOFO em França e Itália ou a [iMile](carrier:imile) na Alemanha) dias ou horas antes da volta do estafeta. | «Em trânsito» |

**Linhas entregues pelo correio do país de destino.** Quando a linha entrega através de um operador postal, a TopYou usa um terceiro conjunto de mensagens até à alfândega:

| O que escreve a TopYou | O que significa para ti | O Peek mostra |
| --- | --- | --- |
| `Accepted by carrier` | A TopYou tem a encomenda na China: primeira leitura. | «Aceite pela transportadora» |
| `Inbound in sorting center` | Registada no primeiro centro de triagem da TopYou minutos depois; ainda não saiu de lá. | «Aceite pela transportadora» |
| `Outbound in sorting center` | Saiu desse centro de triagem. No site da TopYou vem acrescentado `, Lastmile Number:` seguido do código postal de entrega; o Peek retira esse sufixo da linha. | «Em trânsito» |
| `Received by line-haul`, `Hand over to airline` | Recolhida para o transporte principal e entregue à companhia aérea. | «Em trânsito» |
| `Airline arrived at transit country or district.`, `Airline departed from transit or district country.` | Escala num país intermédio a caminho do destino. | «Em trânsito» |
| `Airline arrived at destination country`, `Arrive at destination country` | Chegou ao país de destino. | «Em trânsito» |
| `Export clearance success` | A alfândega chinesa autorizou a saída: nada retém a encomenda. | «Em trânsito» |
| `Import clearance success` | A alfândega de destino libertou o envio para o operador postal, que ainda pode mostrar os seus próprios estados aduaneiros. | «Em trânsito» |
| `Customs Clearance Completed Successfully` | Desalfandegamento concluído no destino, minutos após a passagem à distribuidora local, que já tem a encomenda. | «Em trânsito» |
| `The Shipment Is In Transit` | Linha própria da TopYou após a alfândega no país de destino: a encomenda continua a caminho. | «Em trânsito» |

**Leituras seguintes.** Depois da passagem à distribuidora, o site da TopYou pode continuar a mostrar leituras dessa empresa. Se um ponto de recolha enviar um aviso começado por `Arrived, The parcel has been delivered to 【】. Please collect your parcel promptly at 【】 using your pickup code.`, a encomenda aguarda levantamento nesse local com o código de recolha («Pronta para levantamento»).

## Quem entrega as encomendas da TopYou em Portugal

Depois da alfândega, a TopYou entrega o pacote a uma transportadora em Portugal. A ficha da TopYou pode mostrar o código dessa empresa, mas nunca o nome dela, e o nome interno da linha da TopYou também não serve de pista: uma linha pode usar o nome comercial de outra transportadora sem ser essa a entregar. Numa linha postal, o código do correio aparece também no fim de `Outbound in sorting center` no site da TopYou, depois de `Lastmile Number:`. Pergunta por isso à loja quem distribui a tua encomenda em Portugal e com que número.

Se esse segundo código for um [número postal internacional](guide:tracking-number-formats) válido (duas letras, nove algarismos com algarismo de controlo válido e duas letras) e o destino indicado pela TopYou for Portugal, o Peek consulta os [CTT](carrier:ctt). Essa consulta é apenas uma verificação: o Peek só passa a seguir a encomenda nos CTT se estes mostrarem uma leitura recente (mais abaixo).

As tentativas de entrega, avisos e prazos de levantamento dependem da distribuidora local. Quando a entrega corre pelos CTT e fica disponível numa Loja CTT ou Ponto CTT, os CTT avisam por SMS, e-mail ou aviso em papel, e se o remetente autorizar alterações podes prolongar o prazo para levantar em até 5 dias úteis. Vê também [tentativa de entrega falhada](guide:missed-delivery) e [encomenda não levantada](guide:uncollected-parcel).

**Alfândega e taxas em Portugal.** De acordo com a Autoridade Tributária e Aduaneira e os CTT, desde 1 de julho de 2026 terminou a isenção de direitos aduaneiros para compras online até 150 € vindas de fora da União Europeia, aplicando-se um direito fixo de 3 € por categoria de artigo, e desde 1 de novembro de 2026 acresce nas vendas à distância a Taxa de Manuseamento Aduaneiro da UE de 2 € por tipo de artigo, independentemente do valor, além do IVA quando este não foi cobrado no ato da compra. Os CTT recordam que nunca pedem pagamentos por SMS nem por e-mail: nos envios geridos pelos CTT, o desalfandegamento é feito exclusivamente no Portal de Desalfandegamento dos CTT. Mais informação em [encomenda retida na alfândega](guide:customs).

## Encomenda TopYou parada, sem leituras ou que não chega

**Parada em `Shipment started the customs clearance process at destination`.** A encomenda está na alfândega de importação. Se te pedirem documentos ou taxas, consulta [encomenda retida na alfândega](guide:customs); se passar vários dias sem mexer, avisa o vendedor e vê [rastreio não atualiza](guide:tracking-not-updating).

**Nada depois de `Departed from customs`, `Shipment completed the customs clearance process at destination`, `Customs Clearance Completed Successfully` ou `Import clearance success`.** A distribuidora local já deve ter o pacote. Procura o segundo código em [track.szty56.com](https://track.szty56.com/) ou pergunta à loja quem entrega e com que número. Vê também [seguir encomendas da China](guide:tracking-from-china).

**Não encontrada ou ainda sem leituras.** O site da TopYou responde `查询不到` (não encontrado) quando desconhece um número e pode responder `暂无轨迹信息` (ainda sem informações de seguimento) antes da primeira leitura. O Peek mostra então «À espera de TopYou» em peektracker.com e continua a verificar; se dois dias depois a TopYou continuar sem conhecer o código e nenhuma outra fonte responder por ele, aparece «Ao fim de dois dias, a transportadora ainda não reconhece este número. Verifica-o, escolhe outra transportadora ou elimina a encomenda.», passando o Peek a consultar uma vez por dia e, ao fim de uma semana desde que adicionaste a encomenda, uma vez por semana. Pela resposta da TopYou, o Peek consegue distinguir quando o envio já está registado com país de destino mas ainda sem leituras: nesse caso a TopYou já conhece o número, pelo que o Peek mantém «À espera de TopYou» sem te pedir que verifiques o código. Se apenas `Parcel Data Received` estiver visível há um dia ou mais, o Peek indica «O remetente ainda não a entregou a TopYou. O seguimento começa quando a entregar.»: pergunta à loja quando vai expedir a encomenda. Confere sempre o código carácter a carácter com o da loja.

**Dada como entregue, mas não chegou nada.** A entrega cabe à transportadora local, não à TopYou: contacta primeiro essa empresa e avisa de seguida o vendedor. Lê [encomenda dada como entregue mas não recebida](guide:delivered-not-received).

**Perdida ou devolvida.** Foi a loja quem contratou o envio com a TopYou: pede o reembolso ou uma nova remessa à loja ou ao marketplace. Consulta [encomenda perdida](guide:lost-parcel) e [encomenda devolvida ao remetente](guide:returned-to-sender).

## Contactos da TopYou: a quem ligar

Em 16 de novembro de 2026, o site oficial da TopYou, [szty56.com](https://www.szty56.com/), indica a sede em Shenzhen, o telefone +86 755 8181 9898 (`0755-81819898` na China), o e-mail `2543370662@qq.com` e o horário de dias úteis das 9:00 às 18:00, destinados aos comerciantes que expedem com ela e sem apoio dedicado a destinatários no estrangeiro. Para qualquer questão sobre a compra fala com a loja (ou com o marketplace, se ela não responder); se a encomenda já estiver com os CTT, vê os contactos na página dos [CTT](carrier:ctt).

## Seguir uma encomenda da TopYou com o Peek

O [Peek](/) reconhece um número da TopYou pelo seu formato (TYZR, uma letra, dez algarismos e YQ), que mais nenhuma transportadora do catálogo utiliza. Se aparecer outra letra em vez do R, o formato é apenas um indício fraco: o Peek mostra «A verificar serviços de seguimento…» e pergunta à TopYou se conhece o código. O Peek só consulta a TopYou com códigos destas duas formas. Se tiveres apenas o número da transportadora local, cola-o mesmo assim: o Peek segue mais de 3500 transportadoras e, se não reconhecer o formato, podes escolhê-la em «Alterar».

O Peek lê o seguimento da TopYou e apresenta o estado atual, cada leitura nas palavras da TopYou ou da distribuidora retransmitida, o país de destino e, em «Peso», o peso registado pela TopYou. Nas leituras próprias da TopYou, o Peek retira o local quando este é apenas o nome do país de destino: assim, uma leitura no aeroporto chinês não parece feita em Portugal. Quando o resumo da TopYou dá a encomenda como entregue ou devolvida, o Peek mostra «Entregue» ou «Devolvida ao remetente», mesmo que a última linha diga outra coisa. As leituras próprias da TopYou vêm na hora de Pequim (UTC+8); nas leituras retransmitidas da distribuidora local o site nem sempre traz o fuso certo, pelo que o Peek calcula a hora correta e mostra tudo no fuso horário do teu dispositivo. Não há data prevista de entrega, porque a TopYou não fornece nenhuma.

Quando o registo inclui o código da transportadora de entrega, o Peek testa-o nos CTT se for um número postal internacional válido para Portugal, ou na única transportadora à qual esse formato pertença com certeza, desde que o Peek leia o seguimento próprio dessa empresa. Assim que ela apresentar uma leitura tão ou mais recente do que a última da TopYou, o Peek passa a acompanhar a encomenda por lá e mostra ambos os números, mantendo o histórico da TopYou. Um código ambíguo que várias empresas usam não é mudado automaticamente: o Peek fica no seguimento da TopYou.

O Peek verifica de 10 em 10 minutos, passando a uma vez por hora quando deixam de surgir novidades: ao fim de 12 horas em «Etiqueta criada» ou «Aceite pela transportadora», ou ao fim de dois dias nas restantes etapas (e abranda também para uma vez por hora ou menos se nenhuma notificação te puder chegar e não abrires a encomenda durante uma hora). Quando a encomenda está «Em distribuição», verifica até de 2 em 2 minutos (de 5 em 5 quando a verificação tem de abrir um navegador) a partir das 6h00, hora local, nesse dia, enquanto responder o seguimento da TopYou; através de serviços de seguimento, de 15 em 15 minutos. Quando outra transportadora assume a entrega, o Peek segue o ritmo da que entrega a encomenda: de hora a hora na Royal Mail e na Poste Italiane, de 30 em 30 minutos na PostNL e nos USPS, também em distribuição. Podes escolher alertas para «Todas as leituras», «Passos importantes» ou «Só a entrega»: «Passos importantes» avisa-te na entrada na alfândega de destino (`Shipment started the customs clearance process at destination`), quando passa a «Em distribuição» e na entrega, mas não no despacho de exportação na China, no voo, na saída da alfândega nem em `Shipment is out for delivery`. O Peek não pode contactar a TopYou, a loja nem os CTT por ti.

## Perguntas frequentes sobre o rastreio TopYou

### O que é a TopYou (通邮)?

É a 深圳市通邮物流科技（集团）股份有限公司 (também 通邮集团, Tongyou ou SZTY56), um operador logístico de comércio eletrónico transfronteiriço fundado em 2016 em Shenzhen, com filiais noutras cidades chinesas e mais de 800 000 encomendas tratadas por dia em pacotes postais, linhas próprias e expresso internacional. Os seus números juntam TYZR, uma letra, dez algarismos e YQ (por vezes outra letra em vez do R).

### Porque é que a TopYou mostra Portugal se a encomenda ainda está na China?

Porque a página da TopYou escreve o país de destino ao lado das suas próprias leituras, mesmo no aeroporto de partida na China; o Peek limpa essa indicação enganadora. Guia-te pelo texto em inglês: `Arrived at airport of the original country` significa que ainda aguarda voo na China.

### Quem entrega as encomendas da TopYou em Portugal?

Uma transportadora local em Portugal. O registo da TopYou pode indicar o código dessa empresa, mas não o nome: pergunta à loja quem faz a entrega. Se for um número postal internacional válido, o Peek consulta os CTT, embora isso só se confirme quando os CTT mostrarem leituras próprias.

### Tenho de pagar alfândega numa encomenda da TopYou?

Desde 1 de julho de 2026, as compras online até 150 € vindas de fora da UE pagam um direito aduaneiro de 3 € por categoria de artigo, e desde 1 de novembro de 2026 acresce uma taxa de manuseamento da UE de 2 € por tipo de artigo nas vendas à distância (também acima de 150 €), além do IVA se não tiver sido cobrado na compra.

### A quem reclamo se a minha encomenda TopYou não chegar?

À loja onde compraste (ou ao marketplace): foi o vendedor quem contratou o transporte com a TopYou. Se a encomenda já tiver dado entrada nos CTT, trata da entrega diretamente com os CTT.

:::sources
- [TopYou: 通邮集团 (página inicial, em chinês)](https://www.szty56.com/) – fundação em 2016 em Shenzhen, telefone 0755-81819898, e-mail 2543370662@qq.com, horário em dias úteis 9:00–18:00, plataformas servidas
- [TopYou: 关于我们 (apresentação, em chinês)](https://www.szty56.com/view?path=about/about) – nome completo da empresa, filiais na China, mais de 800 000 encomendas por dia e linhas de serviço
- [TopYou: 轨迹查询 (pesquisa de envios)](https://track.szty56.com/) – portal de pesquisa até 20 números
- [Autoridade Tributária e Aduaneira: Comércio Eletrónico – Alterações para 1 de julho de 2026](https://info.portaldasfinancas.gov.pt/pt/destaques/Paginas/Mercadorias_eCommerce.aspx) – eliminação da franquia aduaneira até 150 €
- [CTT: Fim da isenção de direitos aduaneiros em compras até 150 euros fora da União Europeia](https://www.ctt.pt/particulares/receber/desalfandegar/fim-da-isencao-de-direitos-aduaneiros-em-compras-ate-150-euros-fora-da-uniao-europeia) – direito de 3 € desde 1 de julho de 2026, Taxa de Manuseamento Aduaneiro desde 1 de novembro de 2026, Portal de Desalfandegamento sem pedidos por SMS ou e-mail
- [CTT: Prazo para levantar](https://www.ctt.pt/ajuda/particulares/receber/levantar-correspondencia-ou-encomendas-/prazo-para-levantar) – avisos de levantamento e prolongamento até 5 dias úteis
- [Comissão Europeia: EU Customs Reform (em inglês)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – direito de 3 € desde 1 de julho de 2026 e taxa de manuseamento de 2 € desde 1 de novembro de 2026
- [Comissão Europeia: Guidance on the 3 EUR customs duty and Union handling fee (PDF, em inglês)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – taxa fixa de 2 € por tipo de artigo nas vendas à distância independentemente do valor
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formatos TYZR…YQ e TYZ…YQ, distinção de maiúsculas no site da TopYou, textos e fases das leituras (incluindo `The Shipment Is In Transit` e avisos de ponto de recolha), limpeza de `Lastmile Number:` e de `of`, país junto às leituras próprias, hora de Pequim, envios anunciados sem leituras, segundo código e passagem para os CTT
:::
