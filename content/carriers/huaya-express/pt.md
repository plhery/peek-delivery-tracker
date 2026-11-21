---
title: Seguimento Huaya Express: números HYYT…GZ e estados
description: A Huaya Express (华亚运通) envia encomendas online a partir de Cantão. Como ler um número HYYT…GZ, o que dizem os registos e a quem pedir o código local.
slug: seguimento-huaya-express
published: 2026-11-21
updated: 2026-11-21
---

A Huaya Express (`华亚运通`) é a marca no logótipo da `华亚运通国际货运代理有限公司` (*Huaya Yuntong International Freight Forwarding*), um transitário de Cantão (Guangzhou), na China continental, que expede encomendas para o estrangeiro por conta de lojas online. Uma carta de porte da Huaya começa por `HYYT` e termina em `GZ`. Cola-a na caixa acima ou segue a ligação `物流跟踪` (seguimento logístico) no menu superior de `hyytgz.com` para o localizador próprio da Huaya. O histórico da Huaya termina quando entrega a encomenda a uma transportadora no teu país, ou após algumas leituras dessa transportadora (nos EUA, por vezes até à entrega), sem nunca indicar qual é — exceto numa linha via [DHL](carrier:dhl) na Alemanha, onde copia os registos da DHL, numa linha sueca que refere a Early Bird e numa linha para o México, onde copia as leituras da J&T Express.

## Número de seguimento da Huaya Express: como é

A Huaya não publica o formato dos seus números. O padrão que o Peek conhece tem `HYYT`, doze algarismos e `GZ` (exemplos inventados):

| Número (inventado) | O que é |
| --- | --- |
| `HYYT482910573617GZ` | `HYYT`, doze algarismos e `GZ`: o formato de uma carta de porte da Huaya Express. |
| `HYYT48291057317GZ` | Apenas onze algarismos: perdeu-se um dígito ao copiar. Volta a copiá-lo da mensagem do vendedor. |
| `RR482910576CN` | Número postal terminado em `CN`: é seguido pela [China Post](carrier:china-post), não pela Huaya. |

O Peek lê o código com ou sem espaços e independentemente de maiúsculas ou minúsculas.

**Onde obter o número.** A Huaya trabalha para comerciantes: a página inicial menciona lojas Shopify, Vova, DHgate (`敦煌`) e vendedores no Facebook. Por isso, o número vem da loja onde compraste; se ainda não chegou, vê [como encontrar o número de seguimento](guide:find-tracking-number). O localizador da Huaya também aceita a referência da encomenda da loja (`参考号`), escrita tal como o vendedor a indicou, incluindo traços.

**Ligação de seguimento com endereço numérico.** O localizador próprio da Huaya não está em `hyytgz.com`, mas num endereço numérico em HTTP simples (`trackIndex.htm` com `documentCode=` e o teu código): podes colar essa ligação diretamente no Peek. Já a ligação `物流跟踪` no rodapé do site abre o serviço externo 17TRACK.

## Estados de seguimento da Huaya Express explicados

O localizador da Huaya tem versão em chinês e em inglês. As linhas conhecidas pelo Peek estão sobretudo em inglês, exceto a entrada em armazém e alguns passos de aeroporto, voo, alfândega e entrega ao parceiro que podem surgir em chinês, além de uma linha em sueco na Suécia. Pela ordem habitual da viagem:

| A Huaya diz | O que significa para ti | O Peek mostra |
| --- | --- | --- |
| `Shipping Label Created` | A etiqueta da transportadora que vai entregar no teu país foi criada: apenas dados da encomenda, a Huaya ainda não tem o volume. | «Etiqueta criada» |
| `Shipment not yet received or processed` | Linha da transportadora de destino transmitida antes de o volume chegar ao ponto de recolha da Huaya: ainda sem receção física. | «Etiqueta criada» |
| `Package received & scanned at warehouse`, `到达收货点` | A Huaya recebeu a encomenda no seu armazém em Cantão (China continental): primeira leitura nas mãos da Huaya (`到达收货点` significa chegada ao ponto de receção). | «Aceite pela transportadora» |
| `Package departed from the warehouse` | Saiu desse armazém em Cantão. | «Em trânsito» |
| `货物发往机场途中`, `货物到达机场` | A caminho do aeroporto e chegada ao aeroporto na China continental, antes do desalfandegamento de exportação. | «Em trânsito» |
| `Customs export clearance completed`, `国内清关完成` | Desalfandegamento de exportação concluído na China continental; o controlo aduaneiro de importação no teu país ocorre mais tarde. | «Em trânsito» |
| `安排航班中`, `Package handed over to airline` | A aguardar reserva de voo e entrega à companhia aérea. | «Em trânsito» |
| `Shipment Announced` | A Huaya anunciou a remessa à transportadora do teu país, que ainda não a recebeu. | «Em trânsito» |
| `Package in transit to destination country`, `航班起飞，预计航班落地时间(…)` | Em viagem para o país de destino (a frase em chinês indica entre parênteses o dia previsto de aterragem). | «Em trânsito» |
| `Package arrived at destination country` | Chegou ao país de destino. | «Em trânsito» |
| `航班落地，清关中` | Voo aterrado, desalfandegamento de importação em curso. | «Na alfândega» |
| `Customs import clearance completed, awaiting pickup by local carrier`, `清关完成` | A alfândega do teu país libertou a encomenda; falta a transportadora local recolhê-la. | «Em trânsito» |
| `It has been handed over to the service provider.`, `已交仓`, `Preparing for delivery` | Entregue ao operador local (ou ao seu armazém) ou em preparação para distribuição. A Huaya não indica nem a empresa nem o novo código; depois disto surgem leituras de um parceiro nos EUA (`Arrived at Origin Hub` junto a Chicago O'Hare, `Departed Origin Hub`, `Arrived at Destination Hub`, `Departed Destination Hub`, `Arrived At Warehouse`, seguidas de `Out for Delivery` como «Em distribuição» e `Delivered` como «Entregue») ou, para a Alemanha, leituras copiadas da [DHL](carrier:dhl), ou, para o México, leituras copiadas da J&T Express. O Peek conhece ainda outras linhas de entrega em inglês cujo país nenhuma fonte indica (`Arrived at the regional hub`, `Departed from the regional hub`, `Arrived at the sorting center`, `Departed from the sorting center`, `Arrived in your region`, `Out for delivery with the courier` como «Em distribuição»). | «Em trânsito» |
| `Din leverans ?r bokad med Early Bird.` | Sueco para «A tua entrega está reservada com a Early Bird» (o `ä` de `är` surge como `?`): na Suécia entrega a Early Bird. | «Em trânsito» |

As horas da Huaya vêm sem fuso horário: estão na hora de Pequim (`UTC+8`), exceto nas leituras copiadas da DHL e nas leituras norte-americanas numa cidade dos EUA, que mantêm a hora local do sítio da leitura, e nas leituras da J&T Express copiadas para o México, na hora da Cidade do México. Quando o localizador não conhece um número, a coluna `参考号` (referência) repete o código e deixa o resto da linha em branco.

## Quem entrega as encomendas da Huaya Express?

Tirando as leituras copiadas da [DHL](carrier:dhl) numa rota para a Alemanha, a referência à Early Bird na linha sueca e as leituras copiadas da J&T Express numa rota para o México, o registo da Huaya não identifica a transportadora final nem um segundo código. O próprio site da Huaya contradiz-se nas páginas para vendedores: a página da linha do Reino Unido (`ywfw.php?id=21`) termina com texto dos EUA sobre a USPS, e a da Austrália (`ywfw.php?id=27`) envia a encomenda para os EUA para a USPS. Quem entrega no teu país é a transportadora contratada pelo vendedor: pede-lhe o nome dessa empresa e o respetivo número de seguimento. Sobre taxas aduaneiras, consulta [encomendas retidas na alfândega](guide:customs).

## Encomenda Huaya Express parada ou que não chega

**Parada em `It has been handed over to the service provider.`.** O localizador da Huaya pode não acrescentar mais nada depois disto, pois o volume segue agora sob o código próprio da transportadora local. Pede ao vendedor o nome da transportadora e esse código ([seguir encomendas vindas da China](guide:tracking-from-china)).

**Sem avanços após o desalfandegamento de importação.** A alfândega já libertou o pacote, que aguarda recolha pela transportadora local. Se passarem vários dias sem entrega ao operador, avisa o vendedor ([porque é que o seguimento não atualiza](guide:tracking-not-updating)).

**Sem resultados.** Como a Huaya devolve uma linha em branco com a referência repetida para números desconhecidos, o Peek trata essa resposta como remessa registada ainda sem primeira leitura e mostra «À espera de Huaya Express» sem pedir que verifiques o número (numa consulta sem conta continua a verificar nas primeiras 6 horas, depois quando abres a ligação e, até 30 dias após a consulta, enquanto as notificações estiverem ativas). Confere tu mesmo os caracteres com a mensagem da loja (`HYYT`, doze algarismos, `GZ`) e confirma com o vendedor se expediu pela Huaya.

**Atrasada, perdida ou devolvida.** Os termos de indemnização da Huaya destinam-se às lojas remetentes, não ao destinatário. Pede ao vendedor novo envio ou reembolso e, se deixar de responder, abre disputa na plataforma onde compraste ([encomenda perdida](guide:lost-parcel), [devolvida ao remetente](guide:returned-to-sender), [entregue mas não recebido](guide:delivered-not-received)).

## Contactos e apoio ao cliente da Huaya Express

Dados empresariais listados apenas em chinês em `hyytgz.com`:

- **Telefone**: `18039073131`, identificado como linha de serviço da empresa (`公司服务热线`). É um telemóvel da China continental (marca `+86` fora da China continental); não indica horários nem idiomas.
- **E-mail**: `2065332407@qq.com`, endereço QQ Mail indicado como e-mail da empresa (`公司邮箱`).
- **Formulário**: a página `投诉建议` (`message.php`, reclamações e sugestões) pede nome, telemóvel, e-mail e mensagem.
- **Localização**: distrito de Baiyun, em Cantão (`广州市白云区`), China continental.

As páginas `关于我们` (`about.php`) e `服务网点` (`outlets.php`) têm apenas o título sem conteúdo, e nenhuma página é dirigida a destinatários.

**Cuidado com SMS falsos.** O site institucional da Huaya Express é `hyytgz.com`. Antes de abrires ligações recebidas por SMS ou e-mail, lê [mensagens falsas de entregas](guide:delivery-scam-texts). Sobre a tua compra, contacta primeiro a loja.

## Seguir uma encomenda da Huaya Express com o Peek

Cola um código `HYYT…GZ` ou uma ligação do localizador com `documentCode=` e o [Peek](/) arquiva-o de imediato na Huaya Express («Transportadora detetada»), única no catálogo com esse formato. Para procurar pela referência da loja entre mais de 3 500 transportadoras, introdu-la e escolhe a Huaya Express em «Alterar» (`华亚运通` ou `GZHY` também a encontram; ou «Alterar transportadora» numa encomenda já guardada): o Peek mostra também o código da Huaya associado, mas retira os traços — referências com traços devem ser pesquisadas no localizador da Huaya.

**Leituras, fuso horário e etapas escritas com antecedência.** O Peek lê o localizador próprio da Huaya, mostra cada etapa com local e país de destino, descarta dados pessoais (nomes, moradas, códigos postais, assinaturas, fotos, peso) e não apresenta data prevista de entrega (a Huaya não dá nenhuma). Converte as horas de Pequim (`UTC+8`) — ou da hora local nas leituras copiadas da DHL e nas cidades dos EUA, e da hora da Cidade do México nas leituras da J&T Express — para o fuso do teu dispositivo («Horas no teu fuso horário: …»). Se uma linha trouxer uma hora ainda futura em Pequim, aguarda até lá. Retém também linhas gravadas mais de 12 horas antes da hora que exibem, ou mais de 6 horas antes no mesmo segundo de uma dessas linhas (previsões, não leituras reais): mostra-as a cinzento, à parte das leituras, como etapas que nada confirma, até a encomenda ser entregue ou devolvida. Estas linhas nunca contam como uma leitura, não alteram o estado nem geram alertas.

**Sem transição automática e alertas.** Como a Huaya não fornece transportadora final nem segundo código utilizável, o Peek mantém-se no localizador da Huaya; adiciona o código local pedido ao vendedor como segunda encomenda. O Peek verifica até a cada 10 minutos com a encomenda aberta ou no dia da entrega com notificações ativas, e até a cada 2 minutos a partir das 6:00 locais no dia «Em distribuição» (nos restantes casos e após 12 horas em «Etiqueta criada» / «Aceite pela transportadora» ou dois dias sem movimento, de hora a hora). Das linhas próprias da Huaya, só `航班落地，清关中` conta como «Na alfândega» em «Passos importantes», enquanto «Só a entrega» apenas dispara nas entregas copiadas da DHL, da J&T Express ou do parceiro nos EUA: prefere «Todas as leituras» ou «Passos importantes». O Peek não pode contactar a Huaya nem o vendedor por ti.

## Perguntas frequentes sobre o seguimento da Huaya Express

### O que é a Huaya Express (`华亚运通`)?

É a marca da `华亚运通国际货运代理有限公司` (*Huaya Yuntong International Freight Forwarding*), um transitário de Cantão (China continental) para lojas online que diz recorrer sobretudo à [China Post](carrier:china-post), a linhas próprias desalfandegadas com impostos incluídos para os EUA e a Europa, a linhas para artigos sensíveis e às quatro grandes transportadoras expresso. As suas cartas de porte têm `HYYT`, doze algarismos e `GZ`.

### Porque é que o seguimento parou em `It has been handed over to the service provider.`?

Porque a parte da Huaya termina aí: uma transportadora do teu país recebeu o pacote sob um número próprio que a linha da Huaya não refere. O vendedor pode indicar-te a transportadora e esse número.

### O que significam as linhas a cinzento no Peek?

São etapas registadas no sistema mais de 12 horas antes da hora indicada (ou mais de 6 horas antes no mesmo segundo): previsões pré-escritas e não leituras confirmadas, mesmo que falem em entrega.

### Quem devo contactar sobre uma encomenda Huaya Express em falta?

A loja onde fizeste a compra: é o cliente da Huaya e as condições de reclamação dirigem-se aos expedidores. A Huaya disponibiliza contactos apenas em chinês.

:::sources
- [Huaya Express: 华亚运通国际货运代理有限公司 (página inicial)](http://hyytgz.com/) – firma e logótipo, plataformas servidas, serviços principais, ligações 物流跟踪 (localizador próprio e 17TRACK), linha 18039073131, e-mail 2065332407@qq.com e morada em Baiyun (Cantão)
- [Huaya Express: 欧洲双清包税小包 (linhas Europa e Reino Unido)](http://hyytgz.com/ywfw.php?id=21) – linhas do Reino Unido com texto dos EUA a terminar na USPS e condições para remetentes
- [Huaya Express: 澳大利亚专线小包 (linha Austrália)](http://hyytgz.com/ywfw.php?id=27) – linha da Austrália encaminhada para os EUA para a USPS e condições para remetentes
- [Huaya Express: 投诉建议 (reclamações e sugestões)](http://hyytgz.com/message.php) – campos do formulário (nome, telemóvel, e-mail, mensagem)
- [Huaya Express: 关于我们 (sobre nós)](http://hyytgz.com/about.php) – página sem conteúdo
- [Huaya Express: 服务网点 (balcões)](http://hyytgz.com/outlets.php) – página sem conteúdo
- [Universal Parcel Scraper: Huaya Express](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/huaya-express) – formato HYYT…GZ, nomes alternativos, ligação trackIndex.htm, estados em inglês/chinês/sueco, leituras DHL e EUA, relógios e etapas escritas com antecedência
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – deteção no catálogo, pesquisa por referência e o que o Peek mostra
:::
