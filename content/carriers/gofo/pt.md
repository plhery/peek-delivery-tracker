---
title: Seguimento GOFO Express: número GFUS, estados e ajuda
description: Segue uma encomenda GOFO Express nos Estados Unidos: formatos GFUS, GF, CR e MGP, significado dos estados em inglês e o que fazer se parar.
slug: gofo-express-seguimento
published: 2026-11-17
updated: 2026-11-17
---

A GOFO Express é uma transportadora de encomendas que opera nos Estados Unidos: recolhe os envios no armazém do remetente ou através de um parceiro logístico, encaminha-os pelos centros de triagem e centros regionais até à estação local de distribuição e faz a entrega final ao domicílio. Quer estejas a enviar uma compra para uma morada norte-americana, quer estejas a acompanhar uma encomenda nos EUA, basta colares o número de seguimento na caixa acima; a GOFO tem também o seu próprio localizador no seu site. O código é fornecido pela loja que expediu o pedido — se não o encontrares, vê [como encontrar o número de seguimento](guide:find-tracking-number).

## Número de seguimento GOFO Express: formatos nos EUA

A GOFO Express utiliza quatro séries de números nos Estados Unidos. Aqui tens os formatos (com algarismos inventados), juntamente com dois formatos parceiros aceites nos mesmos envios:

| Número (inventado) | O que identifica |
| --- | --- |
| `GFUS12345678901234` | `GFUS` e 14 dígitos: a carta de porte (*waybill*) principal da GOFO nos Estados Unidos. Se a seguir a `GFUS` vier escrita uma letra `O` em vez do zero, o Peek lê-a automaticamente como `0`. |
| `GF1234567890123` | `GF` e 13 dígitos: uma série norte-americana mais antiga que o localizador da GOFO continua a suportar. |
| `CR123456789012` | `CR` e 12 dígitos: outra série da GOFO nos EUA, por vezes emparelhada com uma guia `GFUS`. |
| `MGP1234567890123` | `MGP` e 13 dígitos: número de seguimento emparelhado pela GOFO com determinadas guias `GFUS`. |
| `FC1234567890123` | `FC` e 13 dígitos: número próprio da [DHLink](carrier:dhlink) usado em encomendas da DHgate entregues pela GOFO nos EUA. |
| `YT1234567890123456` | `YT` e 16 dígitos: número da [YunExpress](carrier:yunexpress), mesmo quando a entrega final nos EUA fica a cargo da GOFO. |

**Dois números para a mesma encomenda.** Algumas encomendas têm simultaneamente uma guia `GFUS` e um número de seguimento `CR` (12 dígitos) ou `MGP` (13 dígitos): qualquer um dos dois devolve exatamente a mesma encomenda no Peek e no localizador norte-americano da GOFO. Nos envios da DHgate entregues pela GOFO em nome da [DHLink](carrier:dhlink), o número de seguimento é o da DHLink, `FC` e 13 dígitos — o Peek consulta a DHLink, e o localizador da GOFO nos EUA também encontra o volume através desse código `FC`.

**Números `YT` da YunExpress.** Quando a GOFO entrega nos Estados Unidos uma encomenda expedida pela [YunExpress](carrier:yunexpress), o seguimento da YunExpress inclui igualmente as leituras da GOFO, e o localizador da GOFO aceita o mesmo código `YT`.

**Redes da GOFO noutros países.** A GOFO opera redes separadas noutros países, cada uma com os seus próprios prefixos: os códigos começados por `GFCA` pertencem à GOFO Canada; por `GFFR` ou `CIFR` (e alguns códigos `PK` de 20 dígitos), à GOFO France; por `GFIT` ou `CIIT`, à GOFO Italy; por `GFES` ou `CIES`, à GOFO Spain; e por `GFNL` ou `CINL`, à GOFO Netherlands. O Peek reconhece-os no momento e encaminha cada número para a rede do respetivo país.

## Estados de seguimento da GOFO Express explicados

As leituras da GOFO Express nos Estados Unidos vêm em inglês. Estes são os estados pelos quais uma encomenda pode passar, pela ordem aproximada do percurso (nem todas passam por todos):

| A GOFO escreve | O que significa para ti |
| --- | --- |
| `Shipping Label Created` | O remetente criou a etiqueta de envio; a GOFO ainda não tem a encomenda. |
| `Pick Up Start` | A GOFO iniciou a recolha junto do remetente, mas ainda não recebeu o volume. |
| `Pickup Exception, GOFO has not Received the Package from the Warehouse yet.` | A GOFO ainda não recebeu a encomenda do armazém do vendedor. |
| `Picked up by Shipping Partner, GOFO Awaiting Item` | Um parceiro de transporte recolheu o volume para a GOFO, que aguarda a chegada ao centro de triagem. |
| `Pick Up Success` | A GOFO recolheu fisicamente a encomenda no remetente. |
| `Arrived at GOFO Regional Origin Hub` | Primeira leitura física num centro regional de origem da GOFO. |
| `Arrived at Sorting Center` | Chegada a um centro de triagem, antes da entrada no centro regional. |
| `Arrived at GOFO Regional Hub` | Chegada a um centro regional (*Regional Hub*) da GOFO. |
| `Processing at GOFO Facility` | Em triagem numa instalação da GOFO. |
| `Departed GOFO Regional Hub` | Saiu do centro regional para o troço seguinte. |
| `Loaded on Vehicle` | Carregada num veículo para transporte até à instalação seguinte. |
| `In Transit to Next Facility` | Em viagem para a próxima instalação da GOFO. |
| `Arrived at GOFO Delivery Station` | Chegou à estação local da GOFO a partir da qual sai para distribuição. |
| `Departed GOFO Delivery Station` | Saiu da estação local de distribuição. |
| `Out for Delivery` | Segue hoje com o estafeta para entrega. |
| `Delivered` | Entregue. |
| `Delivery Exception, Business Closed` | Tentativa de entrega falhada porque o estabelecimento estava encerrado (o motivo após a vírgula varia consoante o caso). |
| `Package Returned to GOFO Facility` | No dia seguinte a uma tentativa de entrega falhada, a encomenda regressou à instalação local da GOFO para nova tentativa — não é uma devolução ao remetente. |
| `Incorrect/Insufficient Address, Additional Details Required for Delivery.` | Morada incorreta ou incompleta detetada no centro regional ou após uma tentativa falhada; o envio aguarda dados adicionais. |
| `Address Corrected.` | A morada foi corrigida e a encomenda retomou o percurso. |
| `Label Cancelled` | A etiqueta foi anulada após ser criada: não virá nenhuma encomenda com esse número. |

Quando surge `Delivery Exception, …`, o Peek coloca a encomenda em «Tentativa de entrega» e mostra a frase completa da GOFO sob «O que diz GOFO Express». Se no dia seguinte aparecer `Package Returned to GOFO Facility`, a encomenda volta a ficar «Em trânsito» na estação local para nova tentativa (e não «Devolvida ao remetente»). Já `Incorrect/Insufficient Address…` e `Label Cancelled` mudam o estado para «Problema reportado»: no primeiro caso, contacta logo a loja para completar o número de porta, apartamento ou código de acesso; no segundo, pede ao vendedor o número de envio correto. Em algumas leituras, a GOFO acrescenta no fim uma linha com contactos de suporte em inglês ou espanhol — o Peek limpa essa linha repetitiva antes de mostrar o evento.

**Fusos horários.** A GOFO regista sempre `Shipping Label Created` na hora do Pacífico (`America/Los_Angeles`) e atribui a cada leitura seguinte o fuso horário norte-americano da respetiva instalação. O Peek converte todas as horas para o fuso do teu telemóvel ou computador («Horas no teu fuso horário: …») no formato de 24 horas: uma etiqueta criada às 22:00 na costa oeste dos EUA aparece como 06:00 do dia seguinte se estiveres em Lisboa (05:00 nas poucas semanas de março e do fim de outubro em que os EUA e a Europa não mudam a hora no mesmo dia), ou 01:00 na costa leste dos EUA.

## Encomenda GOFO Express atrasada, parada ou não entregue: o que fazer

**Apenas `Shipping Label Created`, `Pick Up Start` ou `Pickup Exception`.** Enquanto o último estado for um destes três, a GOFO ainda não recebeu o pacote do armazém do vendedor. Se passarem vários dias sem alteração, pergunta à loja se já entregou a encomenda. Mais detalhes em [seguimento que não atualiza](guide:tracking-not-updating).

**Parada a meio do caminho.** Se passarem quatro dias sem novas leituras antes de a encomenda sair para distribuição, avisa a loja ou utiliza a ligação de ajuda que o Peek passa então a exibir.

**Tentativa falhada ou morada incompleta.** Lê o motivo indicado depois da vírgula em `Delivery Exception, …` ou o alerta `Incorrect/Insufficient Address…`. Se faltar número de apartamento, código do portão ou horário comercial, comunica esses dados à loja para que a morada passe a `Address Corrected.`.

**Dada como entregue, mas não apareceu.** O Peek não importa as fotografias de entrega guardadas pela GOFO. Procura primeiro no alpendre, na sala de correio do edifício (*mailroom*), na receção ou na página de seguimento da GOFO (onde a GOFO guarda a foto de entrega), e avisa o vendedor. Vê também os passos em [encomenda entregue mas não recebida](guide:delivered-not-received).

**Perdida, devolvida e direitos do comprador.** Como o contrato de transporte com a GOFO é celebrado pelo vendedor, pede sempre o reenvio ou o reembolso à loja (consulta [encomenda perdida](guide:lost-parcel) e [encomenda devolvida ao remetente](guide:returned-to-sender)). Para entregas nos Estados Unidos, a regra federal da FTC sobre compras à distância ([16 CFR § 435.2](https://www.law.cornell.edu/cfr/text/16/435.2)) obriga o vendedor a expedir no prazo prometido — ou em 30 dias se nenhum prazo tiver sido indicado — e, havendo atraso, a pedir o teu acordo ou devolver prontamente o valor pago. Nos pagamentos com cartão de crédito norte-americano por artigos não entregues, o Fair Credit Billing Act ([15 U.S.C. § 1666](https://www.law.cornell.edu/uscode/text/15/1666)) dá 60 dias após o envio do extrato para contestar por escrito o erro de faturação junto do emissor. Se compraste a um vendedor profissional estabelecido na União Europeia, aplica-se também o prazo legal supletivo de 30 dias para entrega ([Your Europe](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_pt.htm)). E se uma mercadoria não solicitada chegar pelo correio a uma morada nos EUA (*brushing*), a lei federal ([39 U.S.C. § 3009](https://www.law.cornell.edu/uscode/text/39/3009)) permite tratá-la como oferta sem obrigação de pagar ou devolver; essa lei fala de envios postais, não expressamente de uma entrega da GOFO.

## Apoio ao cliente da GOFO Express

Para qualquer atraso, correção de morada ou pedido de reembolso, fala primeiro com a loja onde fizeste a compra. Se a encomenda estiver quatro dias sem leituras novas antes de entrar em distribuição (mesmo que só tenha a etiqueta criada), o Peek apresenta um aviso com a ligação «Pede ajuda a GOFO Express», que abre a página de apoio ao cliente da GOFO nos Estados Unidos (`gofo.com/us/customer-service`).

## Seguir uma encomenda GOFO Express com o Peek

Com um código `GFUS` de 14 dígitos, o [Peek](/) reconhece a GOFO Express de imediato («Transportadora detetada»). Com um código `GF` (13 dígitos), `CR` (12 dígitos) ou `MGP` (13 dígitos), o Peek pergunta à GOFO se tem o envio («A verificar serviços de seguimento…»); quando a GOFO confirma, vês «GOFO Express tem esta encomenda.» e o acompanhamento fica ativo. Podes colar o número com ou sem espaços. Como a [Cainiao](carrier:cainiao) acompanha algumas encomendas da AliExpress para os EUA através da guia `GFUS` da GOFO, o Peek consulta também a Cainiao sobre o trajeto anterior quando a GOFO mostra apenas a etiqueta há dois dias; vê mais em [encomendas AliExpress](guide:aliexpress-orders).

Enquanto a GOFO ainda não conhecer um número `GF`, `CR` ou `MGP`, o Peek mantém a verificação e mostra «À espera do primeiro registo de GOFO Express» (se já souberes que é da GOFO Express, podes selecioná-la manualmente com «Alterar»). Quando a guia já consta registada na GOFO ainda sem leituras, ou num número `GFUS` guardado em peektracker.com, vês «À espera de GOFO Express» e o Peek continua a consultar sem disparar erro enquanto a guia estiver listada. Só se a GOFO devolver o número como desconhecido durante dois dias numa encomenda atribuída à GOFO Express — e nenhuma outra fonte responder por ele — é que o peektracker.com apresenta «Ao fim de dois dias, a transportadora ainda não reconhece este número. Verifica-o, escolhe outra transportadora ou elimina a encomenda.», passando a verificar uma vez por dia e, ao fim de uma semana, uma vez por semana. Também podes colar uma ligação de seguimento para os EUA de `gofo.com` (`/us…` ou raiz) ou de `gofoexpress.com` que inclua `searchID=`.

O Peek mostra o estado atual, cada leitura com a cidade e o estado norte-americano indicados pela GOFO (como `Jamaica, NY`), esses pontos num mapa, a hora de entrega e o país de destino. Os eventos aparecem em inglês tal como a GOFO os escreve, enquanto a etapa principal surge em português («Etiqueta criada», «Aceite pela transportadora», «Em trânsito», «Em distribuição», «Entregue» — sem o detalhe adicional que a GOFO por vezes escreve após a vírgula na entrega). A GOFO calcula uma data prevista de entrega, que o Peek exibe até a encomenda entrar «Em distribuição», «Entregue» ou «Devolvida ao remetente»; ficam de fora as fotografias de entrega, coordenadas GPS e peso.

O Peek consulta a GOFO a cada 30 minutos (inclusive enquanto a encomenda está «Em distribuição»), aguarda 60 minutos após uma consulta falhada e passa para intervalos de uma hora após dois dias sem novidades ou após 12 horas se o envio continuar em «Etiqueta criada» ou «Aceite pela transportadora». Podes ativar alertas para «Todas as leituras», «Passos importantes» ou «Só a entrega». O Peek não consegue contactar a GOFO nem a loja em teu nome.

## Perguntas frequentes sobre o seguimento GOFO Express

### O que é a GOFO Express?

É uma transportadora de encomendas que faz recolhas em armazém, triagem regional e entregas de última milha nos Estados Unidos. O grupo tem ainda redes nacionais independentes no Canadá, em França, em Itália, em Espanha e nos Países Baixos, cada uma com a sua própria numeração.

### Porque é que o meu número de seguimento da GOFO não funciona?

Confirma primeiro se é um número norte-americano da GOFO: `GFUS` (14 dígitos), `GF` (13 dígitos), `CR` (12 dígitos) ou `MGP` (13 dígitos). Os prefixos `GFCA`, `GFFR`/`CIFR`, `GFIT`/`CIIT`, `GFES`/`CIES` e `GFNL`/`CINL` pertencem às redes de outros países, e os `YT` pertencem à [YunExpress](carrier:yunexpress); uma encomenda da DHgate entregue pela GOFO também pode ser pesquisada com o número `FC` da [DHLink](carrier:dhlink). Se a loja acabou de criar o envio, a guia pode estar apenas à espera da primeira leitura. Se dois dias depois a GOFO continuar a não reconhecer o código no peektracker.com, confirma se há algum algarismo trocado; e se aparecer `Label Cancelled`, a etiqueta foi cancelada e tens de pedir o novo número à loja.

### O que significa `Package Returned to GOFO Facility` na GOFO?

Apesar do nome em inglês, não significa que a encomenda foi devolvida ao vendedor: este registo surge no dia seguinte a uma tentativa de entrega falhada (`Delivery Exception`), quando o motorista devolve o volume à estação local da GOFO para voltar a sair para entrega, pelo que o Peek mantém o estado «Em trânsito».

### Porque é que a minha encomenda da AliExpress ou da DHgate tem um número GOFO?

Nas entregas nos Estados Unidos, a [Cainiao](carrier:cainiao) (logística da AliExpress) acompanha alguns pedidos com o número `GFUS` da GOFO Express quando é a GOFO que os entrega, enquanto a [DHLink](carrier:dhlink) entrega certos pacotes da DHgate à GOFO, que também os segue pelo código `FC` da DHLink. Com um número `GFUS`, o Peek consulta a GOFO e verifica também a Cainiao quando a GOFO mostra apenas a etiqueta há dois dias.

:::sources
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formatos nos EUA (GFUS, GF, CR e MGP), códigos emparelhados DHLink (FC) e YunExpress (YT), redes nacionais da GOFO, estados e etapas, fusos horários, limpeza da linha de suporte, Cainiao e dados omitidos pelo Peek
- [16 CFR § 435.2 – Mail, Internet, or Telephone Order Merchandise Rule (Cornell LII)](https://www.law.cornell.edu/cfr/text/16/435.2) – dever de expedição no prazo anunciado ou em 30 dias nos EUA e direito a reembolso em caso de atraso
- [15 U.S.C. § 1666 – Fair Credit Billing Act (Cornell LII)](https://www.law.cornell.edu/uscode/text/15/1666) – reclamação escrita em 60 dias sobre erros de faturação no cartão por bens não entregues
- [39 U.S.C. § 3009 – Mailing of unordered merchandise (Cornell LII)](https://www.law.cornell.edu/uscode/text/39/3009) – mercadoria não encomendada entregue nos EUA considerada oferta sem obrigação de pagamento
- [Your Europe: Envios e entregas](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_pt.htm) – prazo legal supletivo de 30 dias nas compras a vendedores estabelecidos na UE
:::
