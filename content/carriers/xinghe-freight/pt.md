---
title: Rastreio Xinghe Freight: números GZXH, estados e entrega
description: Segue uma encomenda Xinghe Freight (星和货运) da China: formato do código GZXH…YQ, significado de cada estado, etapas escritas antes e quem entrega em Portugal.
slug: xinghe-freight-rastreio
published: 2026-11-18
updated: 2026-11-18
---

A Xinghe Freight (星和货运, 广州星和货运代理有限公司, Guangzhou Xinghe Freight Forwarding) é um transitário de Cantão (Guangzhou), na China, cujo seguimento funciona sobre o sistema da [GZYL](carrier:gzyl). Se a loja onde fizeste a encomenda te enviou um número começado por `GZXH` e terminado em `YQ`, cola-o na caixa acima ou pesquisa-o na caixa de consulta de envios do site chinês da Xinghe, xinghe-express.cn. Lê o histórico com cautela: nesta plataforma todo o percurso da encomenda, até `Delivered`, pode ser carregado de antemão antes mesmo de alguém pegar fisicamente no volume.

## Número de seguimento Xinghe Freight: como é formado

Os números que o [Peek](/) identifica como envios da Xinghe Freight são compostos pelas letras `GZXH`, dez algarismos e `YQ`, num total de 16 caracteres. Com algarismos inventados:

| Número | O que é |
| --- | --- |
| `GZXH1234567890YQ` | `GZXH`, dez algarismos e `YQ`: a estrutura da Xinghe Freight. O Peek identifica logo a transportadora. |
| `GZXH123456789YQ` | Apenas nove algarismos: não corresponde à estrutura da Xinghe. Confirma se falta um algarismo. |
| `YL1234567890YQ` | `YL`, dez algarismos e `YQ`: a estrutura das cartas de porte da [GZYL](carrier:gzyl). Usa o mesmo sistema de seguimento, mas é outro transitário. |
| `JXCAA1234567890YQ` | Também termina em `YQ`, como muitos códigos de transitários chineses, mas começa por `JXC`: é um número da Juxi Express. |

Espaços, hífenes e letras minúsculas não afetam a pesquisa quando colas o número no Peek.

**Onde o encontrar.** O vendedor regista o envio com a Xinghe e indica o código no e-mail de expedição ou na página da encomenda. Se não o encontrares, consulta o guia sobre [como encontrar o número de seguimento](guide:find-tracking-number).

## Estados de seguimento Xinghe Freight explicados

Como o seguimento da Xinghe corre no sistema da GZYL, as etapas aparecem com as frases em inglês da GZYL, gralhas incluídas. O Peek interpreta-as pela lista de etapas da GZYL. Pela ordem habitual em que surgem:

| O sistema mostra | O que significa para ti | O Peek mostra |
| --- | --- | --- |
| `Order information received` | O vendedor transmitiu os dados da encomenda. Ainda ninguém recebeu o pacote. | «Etiqueta criada» |
| `Arrive at the cross border receiving point` | O transitário recebeu a encomenda: é o primeiro registo físico nas suas mãos. | «Aceite pela transportadora» |
| `Goods leave the operation center` | O pacote saiu do centro operacional na China. | «Em trânsito» |
| `Arrive at the international airport` | No aeroporto de partida, à espera de voo. | «Em trânsito» |
| `Departing from the airport` | O voo partiu. | «Em trânsito» |
| `Item arrives at destination airport` | Aterrou no país de destino. | «Em trânsito» |
| `local carrier Agent Import customs clearance` | Um despachante no país de destino está a tratar do desalfandegamento de importação. | «Na alfândega» |
| `The import customs keep it for inspection` | A alfândega do país de destino reteve a encomenda para inspeção. | «Na alfândega» |
| `Customs clearance completed` | Desalfandegamento concluído; a encomenda segue caminho. | «Em trânsito» |
| `Internationaliy Package received at processing center` | Um centro de tratamento no país de destino recebeu o pacote desalfandegado (`Internationaliy` é gralha da plataforma). Não é uma nova recolha no remetente. | «Em trânsito» |
| `Leave the email processing center` | A encomenda saiu desse centro de tratamento (`email` resulta da tradução automática de correio na plataforma). | «Em trânsito» |
| `Arrival sorting, distribution center` | Chegada a um centro de distribuição no teu país, com o nome da localidade a seguir. | «Em trânsito» |
| `Leaving the sorting center` | Saída de um centro de triagem, com a localidade indicada. | «Em trânsito» |
| `Package is on the way` | Em encaminhamento no país de destino, com indicação de cidade. Não significa que tenha saído hoje para entrega na morada. | «Em trânsito» |
| `Delivered` | Registo de entrega com a localidade. Pode fazer parte de um plano pré-carregado e não de uma leitura real (ver abaixo). | «Entregue», com aviso sobre o horário fixo, exceto quando o Peek a retém (ver abaixo) |

**Passagem por Hong Kong.** Uma encomenda pode também apresentar a linha `香港处理中心已收寄【Hong Kong】`: aceitação postal no centro de tratamento de Hong Kong, transmitida em chinês com a cidade entre parênteses retos. O Peek mostra a frase tal como está escrita, sob «Aceite pela transportadora».

**Uma linha em chinês.** Quando a encomenda ainda não tem registos físicos, a página da plataforma pode apresentar `货物电子信息已经收到`, que significa que a informação eletrónica da mercadoria foi recebida. A plataforma cria esta linha sozinha quando o vendedor abre o pedido, antes de qualquer leitura física. O Peek mostra a frase em chinês sob «Etiqueta criada».

**Horas e fuso horário.** O Peek lê todas as horas do sistema como hora de Pequim (`UTC+8`), incluindo as etapas registadas na Europa, e apresenta-as no fuso horário do teu dispositivo. No horário de inverno europeu (do último domingo de outubro ao último domingo de março), uma etapa às `20:00` em Pequim corresponde às `12:00` em Lisboa (`11:00` nos Açores); no horário de verão (do último domingo de março ao último domingo de outubro), corresponde às `13:00` em Lisboa (`12:00` nos Açores).

**Etapas escritas com antecedência.** Nesta plataforma de seguimento, os transitários podem carregar antecipadamente toda a cronologia de uma encomenda, desde a recolha até à última etapa no país de destino, e o sistema vai revelando cada linha à medida que a hora prevista passa. Uma linha dessas é uma previsão, não uma leitura física. No sistema da GZYL, esse plano termina num `Delivered` acrescentado em lote ao final da tarde do dia indicado: traz data do dia seguinte à última etapa planeada, dentro do horário de expediente de Pequim, qualquer que seja o país de destino. O Peek retém estas etapas (ver abaixo), mas um serviço externo de seguimento que copie o registo pode mostrá-las — pelo que um `Delivered` visto aí não prova que a encomenda tenha chegado.

## Quem entrega as encomendas Xinghe Freight em Portugal

O Peek não consegue saber quem faz a entrega final em Portugal: nem as etapas do sistema nem o site da Xinghe indicam o nome da transportadora local. A única empresa de distribuição para a qual o Peek consegue transferir automaticamente uma encomenda da Xinghe é a [Posta Suíça](carrier:swiss-post) (*Swiss Post*), na Suíça e no Liechtenstein, quando o registo da Xinghe a identifica.

O site da Xinghe não contém informações para particulares que compram online: não indica prazos de entrega, tentativas de distribuição nem pontos de recolha. Essas regras pertencem à transportadora que assegura o último troço no teu país; pergunta ao vendedor que empresa entrega a tua encomenda e pede-lhe o respetivo código de seguimento local.

**Alfândega, IVA e alertas de burla (UE e Portugal).** Na **União Europeia** (incluindo **Portugal**), desde 1 de julho de 2026 as mercadorias compradas à distância fora da UE em remessas com valor intrínseco até 150 € pagam um direito aduaneiro fixo de 3 € por categoria de artigo, e desde 1 de novembro de 2026 aplica-se também a taxa de manuseamento aduaneiro da União (*Union Handling Fee*) de 2 € por categoria de artigo, qualquer que seja o valor da encomenda. Uma categoria de artigo abrange os bens da mesma remessa que partilham classificação pautal, descrição e, quando exigida, origem, independentemente da quantidade. Em **Portugal**, os [CTT](carrier:ctt) explicam que às compras online extracomunitárias até 150 € acrescem o IVA sobre o valor das compras, os direitos aduaneiros (3 € + IVA por tipo de artigo/conteúdo segundo o Código Harmonizado) e, desde 1 de novembro de 2026, a Taxa de Manuseamento Aduaneiro (2 € + IVA por tipo de artigo/conteúdo), alertando ainda que objetos até 150 € que cheguem a Portugal sem os dados obrigatórios de identificação dos produtos na declaração aduaneira poderão ser devolvidos. Quanto a tentativas de burla em **Portugal**, os CTT alertam para SMS e sites fraudulentos que usam indevidamente a imagem dos CTT ou de transportadoras como a DHL e recomendam confirmar cuidadosamente o endereço do site, desconfiar de domínios semelhantes ao nome CTT, não fornecer dados pessoais ou de pagamento em páginas de autenticidade duvidosa e aceder aos serviços diretamente pelos canais oficiais (`www.ctt.pt`). Mais detalhes em [encomendas retidas na alfândega](guide:customs) e em [SMS falsos de entregas](guide:delivery-scam-texts).

## Encomenda Xinghe Freight atrasada ou seguimento parado

**Parada na alfândega.** Se te pedirem o pagamento de encargos de importação, confirma a notificação diretamente nos canais oficiais do operador responsável em Portugal e consulta [encomendas retidas na alfândega](guide:customs). Se o estado aduaneiro não avançar durante vários dias, avisa o vendedor.

**O sistema ou outro rastreador mostra mais etapas do que o Peek.** As linhas extra, incluindo `Delivered`, podem fazer parte de um plano pré-escrito que o Peek omite por não estar confirmado. Orienta-te pela estimativa de entrega indicada pela loja na compra e contacta o vendedor se esse prazo já tiver sido ultrapassado. Lê mais em [seguimento parado sem atualizar](guide:tracking-not-updating) e [seguir uma encomenda vinda da China](guide:tracking-from-china).

**Aparece `Delivered`, mas nada chegou.** Essa linha pode pertencer a uma cronologia criada de antemão. Exceto quando a entrega passa para a Posta Suíça, o Peek mostra por baixo de «Entregue» o aviso: «As atualizações de Xinghe Freight seguem um horário fixo, sem assinatura. Se nada chegou, contacta o vendedor.» Informa o vendedor de que não recebeste a encomenda e pede-lhe o nome da transportadora local e o respetivo número. Consulta também [encomenda dada como entregue mas não recebida](guide:delivered-not-received).

**Extraviada ou devolvida.** Como foi o vendedor quem contratou o envio com a Xinghe, pede o reembolso ou um novo envio ao vendedor ou à plataforma onde compraste. Vê [encomenda perdida](guide:lost-parcel) e [devolvida ao remetente](guide:returned-to-sender).

## Contactos e apoio ao cliente Xinghe Freight

De acordo com o site xinghe-express.cn, disponível apenas em chinês:

- **Linha direta**: `19924483103`, anunciada como disponível 24 horas por dia, todos os dias (`+86 199 2448 3103` a partir do estrangeiro).
- **E-mail**: `3975605292@qq.com`, destinado a contactos comerciais e pedidos de cotação.
- **Morada**: Cantão (Guangzhou), província de Guangdong, sem rua indicada; a página pede que entres em contacto antes de qualquer visita.

O site não disponibiliza apoio para destinatários particulares no estrangeiro e o formulário de contacto apenas abre uma mensagem para esse endereço de e-mail. O cliente da Xinghe é o vendedor: fala primeiro com a loja sobre a tua encomenda e recorre ao marketplace se o vendedor não responder.

## Seguir uma encomenda Xinghe Freight com o Peek

O [Peek](/) identifica imediatamente a Xinghe Freight perante um número `GZXH…YQ`. Se o vendedor te deu um código com outro formato, cola-o na mesma: o Peek acompanha mais de 3 500 transportadoras. Para associar manualmente um código à Xinghe, carrega para alterar a transportadora ao adicionar e procura Xinghe Freight (também podes escrever `星和货运`), ou usa «Alterar transportadora» numa encomenda já guardada.

O Peek consulta o sistema da GZYL tal como faz a caixa de pesquisa do próprio site da Xinghe, apresentando o estado atual, cada etapa com o respetivo local e o país de destino quando o registo o indica. As horas são lidas no fuso de Pequim e convertidas para o fuso horário do teu dispositivo (com a nota «Horas no teu fuso horário: …» em `peektracker.com` e na aplicação para iPhone). O Peek descarta nomes, morada, códigos postais, peso, dados de pagamento, assinatura e comprovativo de entrega que possam constar do registo, e não mostra data prevista de entrega porque o sistema não indica nenhuma.

Quando o sistema da GZYL não conhece o teu número, o Peek consulta serviços de seguimento externos como alternativa e apresenta a cópia das etapas que encontrem, o que para alguns números Xinghe pode ser toda a informação disponível. Enquanto nem o sistema direto nem os serviços externos tiverem etapas, a encomenda mostra «À espera de Xinghe Freight» em `peektracker.com`, sem pedir que verifiques o número.

O Peek retém qualquer etapa escrita mais de 12 horas antes da hora que ostenta, ou mais de 6 horas antes no mesmo segundo de uma etapa nessas condições, bem como qualquer etapa com data posterior ao momento da verificação (na hora de Pequim). Num registo com etapas escritas com antecedência, retém também `Delivered`, mesmo quando gravado no próprio dia. O Peek define o estado apenas pelas etapas que conserva, ignorando o estado de resumo do registo, e afasta igualmente as cópias feitas pelos serviços de seguimento das etapas retidas como pré-escritas (`Delivered` incluído), mesmo quando mantém outras etapas. Contudo, se o sistema direto não tiver registo do número ou estiver inacessível, na cópia de um serviço externo o Peek não consegue distinguir uma etapa planeada de uma leitura real. Quando retém etapas pré-escritas, o Peek mostra sob o estado da encomenda, até que seja entregue ou devolvida: «O site de Xinghe Freight mostra etapas escritas com antecedência, até mesmo a entrega. O Peek não as pode confirmar, por isso omite-as. Procura nesse site a transportadora que entrega no teu país e o respetivo número de seguimento; se não houver, pergunta ao vendedor.» Uma etapa dessas nunca conta como registo: não muda o estado nem envia alerta. Quando todas as etapas ficam retidas ou a encomenda consta ainda sem leituras, na aplicação para iPhone ou na tua conta o Peek volta a consultar o sistema a cada 6 horas após as primeiras horas; numa pesquisa sem conta, verifica durante as primeiras 6 horas, depois quando abres a ligação (no máximo a cada 6 horas nos dois primeiros dias e depois uma vez por dia) e, com notificações ativas, diariamente até 30 dias após a pesquisa.

À exceção da Posta Suíça na Suíça e no Liechtenstein, o Peek não transfere uma encomenda da Xinghe para a empresa que faz a entrega final: se o vendedor te der o código dessa transportadora, adiciona-o como uma encomenda autónoma. Enquanto tens a encomenda aberta no ecrã, ou no dia da entrega com notificações ativas, o Peek verifica até a cada 10 minutos quando o sistema direto responde; caso contrário, e quando a encomenda fica sem movimento, verifica de hora a hora (através apenas de serviços de seguimento: de hora a hora, a cada 3 horas após um dia sem leituras e, no dia em que sai para distribuição, até a cada 15 minutos enquanto tens a encomenda aberta ou as notificações ativas). Podes ativar alertas para «Todas as atualizações», «Só as importantes» (que aqui abrange as duas etapas de alfândega e `Delivered`, não voos nem triagens) ou «Só no dia da entrega». O Peek não pode contactar a Xinghe Freight, a GZYL nem o vendedor por ti.

## Perguntas frequentes sobre o seguimento Xinghe Freight

### O que é a Xinghe Freight (星和货运)?

É a 广州星和货运代理有限公司 (Guangzhou Xinghe Freight Forwarding), um transitário sediado em Cantão (Guangzhou), na China. No seu site afirma ter sido fundada em 2014 e operar transporte marítimo, aéreo e terrestre, agência de navegação e despacho aduaneiro. Os códigos que o Peek associa à Xinghe Freight têm a forma `GZXH`, dez algarismos e `YQ`.

### Porque é que a minha encomenda Xinghe Freight mostra o seguimento da GZYL?

Porque o seguimento da Xinghe funciona sobre o sistema da [GZYL](carrier:gzyl): a própria caixa de pesquisa no site da Xinghe envia o número para lá, pelo que as etapas surgem com as frases da GZYL. O Peek mantém a encomenda associada à Xinghe Freight e lê diretamente esse sistema por ti.

### Quem entrega as encomendas da Xinghe Freight em Portugal?

O Peek não tem forma de saber: nem as etapas do sistema nem o site da Xinghe indicam a empresa distribuidora local. Apenas na Suíça e no Liechtenstein pode o Peek passar automaticamente o envio para a Posta Suíça. Em Portugal, pergunta ao vendedor qual é a transportadora responsável pela entrega e pede o respetivo número de seguimento.

### Porque é que o Peek ainda não mostra seguimento para o meu número GZXH?

O sistema pode ainda não ter a encomenda registada, ou ter apenas etapas escritas com antecedência (incluindo um `Delivered` final) que o Peek omite por não poder confirmar. O Peek consulta também serviços de seguimento externos e continua a verificar, mantendo «À espera de Xinghe Freight» sem pedir que revejas o código: compara-o tu próprio com a mensagem da loja e, se nada surgir ao fim de alguns dias, pergunta ao vendedor se a encomenda já foi expedida.

:::sources
- [Xinghe Freight: 广州星和货运代理有限公司 (página inicial, em chinês)](http://xinghe-express.cn/) – denominação social, serviços de trânsito internacional, caixa de pesquisa ligada ao sistema da GZYL, telefone e e-mail
- [Xinghe Freight: 关于我们 (sobre nós, em chinês)](http://xinghe-express.cn/about.html) – fundação em 2014, transporte marítimo, aéreo e terrestre, agência marítima e despacho aduaneiro
- [Xinghe Freight: 产品服务 (serviços, em chinês)](http://xinghe-express.cn/services.html) – serviços apresentados sem informação sobre encomendas de comércio eletrónico para particulares
- [Xinghe Freight: 联系我们 (contactos, em chinês)](http://xinghe-express.cn/contact.html) – linha aberta 24 horas por dia, e-mail comercial e de cotações, localização indicada apenas como Guangzhou com pedido de contacto prévio e formulário que abre um e-mail
- [Comissão Europeia: EU Customs Reform (em inglês)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – direito aduaneiro de 3 € por categoria de artigo até 150 € desde 1 de julho de 2026 e taxa de manuseamento da União de 2 € desde 1 de novembro de 2026
- [Comissão Europeia: Guidance on the 3 EUR customs duty and Union handling fee (PDF, em inglês)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – aplicação da taxa de 2 € por categoria de produto independentemente do valor e definição de item
- [CTT: Fim da isenção de direitos aduaneiros em compras até 150 Euros fora da UE](https://www.ctt.pt/particulares/receber/desalfandegar/fim-da-isencao-de-direitos-aduaneiros-em-compras-ate-150-euros-fora-da-uniao-europeia) – IVA, direitos de 3 € + IVA, Taxa de Manuseamento Aduaneiro de 2 € + IVA e devolução de envios sem dados de identificação dos produtos em Portugal
- [CTT: Procedimentos para Alertas de Phishing](https://www.ctt.pt/transversais/alertas-de-phishing) – alertas sobre SMS e sites falsos em Portugal e recomendação de aceder diretamente pelos canais oficiais dos CTT
- [Comissão Europeia: Summertime (em inglês)](https://transport.ec.europa.eu/transport-themes/summertime_en) – horário de verão europeu entre o último domingo de março e o último domingo de outubro
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – nomes, formato GZXH…YQ, leitura no sistema da GZYL, frases e fases das etapas, aceitação em Hong Kong, linha em chinês da plataforma, hora de Pequim, cronologias escritas de antemão, serviços de seguimento como alternativa, resposta para número desconhecido, passagem à Posta Suíça e o que o Peek mostra, retém e descarta
:::
