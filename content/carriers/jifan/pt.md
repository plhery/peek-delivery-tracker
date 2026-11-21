---
title: Seguimento Jifan: números JFGJ, estados e hora de Pequim
description: Segue uma encomenda Jifan (吉凡国际物流) da China continental para os EUA: formato JFGJ…CN, significado dos estados e porque todas as horas estão em UTC+8.
slug: seguimento-jifan
published: 2026-11-21
updated: 2026-11-21
---

A Jifan é a Shenzhen Jifan International Logistics (`深圳市吉凡国际物流有限公司`, ou `吉凡国际物流`), uma empresa de logística fundada em 2017 em Shenzhen, na China continental, e membro NVOCC. Expede encomendas de empresas na China continental para compradores nos Estados Unidos, por via aérea e marítima. Os seus números de seguimento são compostos por `JFGJ`, nove algarismos e `CN`. Consulta o teu no [localizador da Jifan](https://www.szjf56.cn/index/tracknumber/track) ou cola-o na caixa acima; no catálogo do Peek também encontras a transportadora pesquisando `Jifan International Logistics`, `吉凡国际物流` ou `深圳市吉凡国际物流有限公司`.

## Número de seguimento da Jifan: como é

O site da Jifan não descreve o formato das suas cartas de porte, mas todas seguem uma estrutura única. `JFGJ` corresponde às iniciais em pinyin da empresa: `吉凡国际` (*Jifan Guoji*, «Jifan Internacional»). Todos os exemplos abaixo são inventados:

| Número (inventado) | O que é |
| --- | --- |
| `JFGJ482910573CN` | `JFGJ`, nove algarismos e `CN`: um número da Jifan (sem validação de dígito de controlo no nono algarismo). |
| `JFGJ48291057CN` | Apenas oito algarismos: não tem o formato da Jifan e o Peek não atribui a Jifan. Verifica se falta um dígito. |
| `RR482910576CN` | Duas letras, nove algarismos e `CN`: um número da [China Post](carrier:china-post), não da Jifan, apesar de terminar em `CN`. |

Em `peektracker.com`, um código `JFGJ…CN` com menos um carácter recebe esta nota: «Começa como um número de seguimento de Jifan, mas falta-lhe um carácter: pode estar mal escrito. Compara-o com a tua mensagem de envio.» No Peek, minúsculas, espaços e traços não importam. No localizador da Jifan, escreve o código em maiúsculas e sem espaços.

**Onde encontrar o número.** No e-mail de expedição do vendedor ou na página da encomenda. Se não o encontrares, vê [como encontrar o número de seguimento](guide:find-tracking-number).

**No site da Jifan.** A página inicial (`szjf56.cn`, área `单号查询`) aceita até 10 números separados por Enter, e a página dedicada `快件跟踪` tem o botão `跟踪(Track)`, aceita até 20 números de cada vez e só encontra encomendas dos últimos dois meses (`2个月内的包裹`).

## Estados de seguimento da Jifan explicados

O localizador da Jifan está em chinês, mas as linhas de seguimento surgem em inglês, da mais recente para a mais antiga, cada uma com data e hora. Acima delas, uma linha de resumo indica o destino (`目的地`), o estado (`状态`), a última hora (`最新时间`) e o último registo (`最新记录`). Dois estados habituais nesse resumo:

| O localizador diz | O que significa para ti |
| --- | --- |
| `运输途中` | «Em trânsito» em chinês. Lê as linhas em inglês abaixo para veres onde a encomenda realmente está. |
| `查询不到` | «Não encontrado» em chinês. O localizador não tem nenhuma encomenda com este número e não mostra leituras. |

Surgem primeiro as etapas próprias da Jifan na China continental — sem localidade — desde os dados da encomenda até à partida do voo, e depois as leituras nos Estados Unidos enviadas pelo agente de chegada e pela rede de entrega final (cujo nome o localizador substitui por `logistics carrier`). O localizador da Jifan funciona sobre o sistema da CTTYEX e redige as linhas da mesma maneira:

| A Jifan indica | O que significa para a tua encomenda | O Peek mostra |
| --- | --- | --- |
| `Order information received` | O vendedor enviou os dados da remessa; a Jifan ainda não tem o pacote. | «Etiqueta criada» |
| `Arrive at the cross border receiving point`, `Arrive at the receiving point` | A Jifan recebeu o volume no ponto de receção transfronteiriça na China continental. | «Aceite pela transportadora» |
| `Goods leave the operation center` | O pacote saiu do centro de operações da Jifan na China continental. | «Em trânsito» |
| `Arrival at domestic internationality airport` | No aeroporto de partida na China continental (`internationality` é a grafia do próprio localizador). | «Em trânsito» |
| `Departure from the airport of origin domestic` | O voo partiu da China continental. | «Em trânsito» |
| `Arrive at the destination international airport` | Chegada ao aeroporto internacional de destino nos Estados Unidos. | «Em trânsito» |
| `Import customs clearance` | Desalfandegamento de importação nos Estados Unidos (precedido da cidade do porto de entrada). | «Na alfândega» |
| `Leave the … processing center` | Saída do centro de triagem norte-americano indicado no lugar das reticências. | «Em trânsito» |
| `Arrived at logistics carrier Facility In Transit to Next Facility` | Passagem por uma instalação da transportadora norte-americana a caminho da seguinte. | «Em trânsito» |
| `Accepted at logistics carrier Regional Destination Facility` | Receção no centro regional de destino da transportadora nos EUA; mantém-se «Em trânsito» porque a Jifan já aceitou a encomenda na China continental. | «Em trânsito» |

Uma linha a confirmar a entrega final aparece no Peek como «Entregue». Nos Estados Unidos, algumas linhas começam pela cidade, estado e código ZIP.

**Todas as horas estão na hora de Pequim.** O localizador da Jifan imprime os horários sem fuso horário: todos estão na hora de Pequim (`UTC+8`), mesmo nas leituras feitas nos Estados Unidos. Em Pequim a data muda às `16:00 UTC`: por isso, uma leitura feita em Nova Iorque depois das 11:00 no inverno (12:00 no verão), ou em Los Angeles depois das 08:00 no inverno (09:00 no verão), já traz a data do dia seguinte.

## Quem entrega as encomendas da Jifan

O site da Jifan apresenta quatro linhas comerciais, todas da China continental para os **Estados Unidos**:

- **Pequena encomenda económica EUA** (`美国经济小包-轻小件`): artigos leves e planos abaixo de 360 g e com menos de 1,8 cm de espessura, em voos de carga diretos, entregues pela [UPS](carrier:ups) em todos os Estados Unidos (incluindo ilhas ultramarinas e moradas militares), com seguimento completo. Prazo indicativo: cerca de 10 a 12 dias seguidos (`自然日`).
- **Pequena encomenda aérea EUA** (`美国空运小包标快`): dos 0 aos 4,5 kg, em voos cargueiros diretos, entregue pela UPS (`UPS MI`, escreve a Jifan) em todo o território dos EUA, ilhas e moradas militares incluídas. Cerca de 8 a 10 dias seguidos.
- **Encomendas marítimas Matson** (`美森正班（限时达）海运大/小包`): pequenos pacotes até 4,5 kg são entregues como na linha aérea (`UPS MI`, todos os EUA); volumes dos 4,5 aos 68 kg seguem por `UPS GROUND`, apenas para os EUA continentais (`仅限美国本土`). Cerca de 20 a 25 dias seguidos.
- **Carga marítima de grandes dimensões EUA** (`美国海运超大件专线`): de 100 a 2 000 kg por volume em navios Matson e COSCO e depois de camião até à porta (moradas comerciais e residenciais), apenas nos EUA continentais sem ilhas ultramarinas nem moradas militares. Cerca de 25 a 40 dias seguidos.

Nas encomendas económicas e nos volumes pequenos até 4,5 kg por ar ou mar, a Jifan indica que uma única carta de porte cobre toda a viagem (`一单到底`). O seu localizador não identifica a transportadora final nem dá um segundo número; se o vendedor te tiver dado também um código da UPS, podes segui-lo na [UPS](carrier:ups). A Jifan não publica regras para destinatários sobre tentativas de entrega ou pontos de recolha ([entrega falhada](guide:missed-delivery)) nem sobre direitos ou impostos no país de destino ([encomenda retida na alfândega](guide:customs)).

## Encomenda Jifan sem atualizações, perdida ou devolvida

Os clientes da Jifan são as empresas expedidoras na China continental, não os destinatários: em caso de atraso, extravio, dano ou devolução, fala primeiro com o vendedor ou plataforma de compra.

**`查询不到` (não encontrado).** Confirma se há algum erro de escrita, digita o número em maiúsculas sem espaços e pergunta ao vendedor se continuar sem aparecer. O Peek mostra «À espera de Jifan» e continua a verificar. Se dois dias depois a Jifan ainda não conhecer o número e nenhum outro serviço tiver respondido, o Peek apresenta: «Ao fim de dois dias, a transportadora ainda não reconhece este número. Verifica-o, escolhe outra transportadora ou elimina a encomenda.» A partir daí o Peek verifica diariamente e, passada uma semana desde que a adicionaste, semanalmente.

**Apenas `Order information received`.** A Jifan recebeu os dados eletrónicos, ainda não o pacote físico. Se nada mudar durante vários dias, pergunta ao vendedor se entregou a encomenda.

**Silêncio após o aeroporto ou no mar.** Depois de `Arrival at domestic internationality airport` ou `Departure from the airport of origin domestic`, pode não surgir nada de novo até à aterragem ou à alfândega nos EUA, e os envios marítimos levam semanas. Quando uma encomenda em viagem fica quatro dias sem nova leitura, o Peek assinala o dia da última leitura («Sem atualizações desde seg 12 out») e nota: «É habitual uma encomenda ficar sem novidades em voos longos, na alfândega ou ao fim de semana. O Peek continua a perguntar a Jifan.» (sem botão de ajuda, pois a Jifan não tem página de apoio ao destinatário). Passadas três semanas sem nova leitura, o Peek aconselha antes: «Se a encomenda ainda não chegou, contacta o vendedor.» Lê [porque é que o seguimento não atualiza](guide:tracking-not-updating) e [seguir encomendas vindas da China](guide:tracking-from-china).

**Cuidado com SMS fraudulentos.** Os domínios oficiais da Jifan são `www.szjf56.cn` e `szjf56.cn`, e o seu localizador não apresenta taxas aduaneiras nem valores a pagar. Lê [mensagens falsas de entregas](guide:delivery-scam-texts) antes de pagares qualquer pedido recebido por SMS ou e-mail.

**Perdida, danificada, devolvida ou dada como entregue sem estar.** Na página inicial, a Jifan promete aos seus clientes expedidores indemnização por atraso e, em caso de perda, a devolução do porte mais outro tanto igual (`延误即赔,丢件按运费退一赔一`): esse compromisso é entre a Jifan e o vendedor, a quem deves pedir reembolso ou novo envio. Vê [encomenda perdida](guide:lost-parcel), [encomenda danificada](guide:damaged-parcel), [devolvida ao remetente](guide:returned-to-sender) e [entregue mas não recebido](guide:delivered-not-received).

## Contactos da Jifan

Na secção `联系我们` da página inicial da Jifan (`www.szjf56.cn`):

- **Linha telefónica**: `400-186-0001` (`咨询电话`), um número de serviço na China continental. A Jifan não indica horário de atendimento nem se atende chamadas do estrangeiro ou em inglês.
- **E-mail**: `925930140@qq.com` (`联系邮箱`, endereço QQ Mail, contacto `冯生`), sem indicar quem o lê nem em que idioma.
- **Escritório**: `深圳市龙岗区南湾街道2990号` (n.º 2990, subdistrito de Nanwan, distrito de Longgang, Shenzhen, China continental).

O site está apenas em chinês e não tem formulário nem chat para destinatários: sobre a tua compra, contacta primeiro o vendedor.

## Seguir uma encomenda da Jifan com o Peek

O [Peek](/) identifica de imediato a Jifan a partir de um número `JFGJ…CN` («Transportadora detetada»): nenhuma outra transportadora acompanhada pelo Peek utiliza este formato. Uma ligação para `szjf56.cn/index/tracknumber/track` com um código `JFGJ…CN` depois de `nos=` também funciona (se tiver vários, o Peek usa o primeiro).

Se o vendedor te deu outro número qualquer, cola-o na mesma: o Peek acompanha mais de 4 000 transportadoras, consulta serviços de seguimento quando o formato não identifica uma e permite mudá-la em «Alterar» ou «Alterar transportadora». O Peek só consulta o localizador da Jifan para números `JFGJ…CN`; se escolheres a Jifan para outro código, indica que «não emite números como este».

O Peek lê no localizador da Jifan o estado, todas as linhas no inglês original e o país de destino. Quando uma linha nos Estados Unidos começa pela cidade, estado e código ZIP, o Peek guarda a cidade e o estado como local da leitura e retira o código ZIP. Cada hora é lida como hora de Pequim (`UTC+8`) e mostrada no fuso horário do teu dispositivo; em `peektracker.com` e nas aplicações iPhone e Android, o histórico termina com «Horas no teu fuso horário: …». «Abrir o site de Jifan» abre o teu número em `szjf56.cn`.

O estado do Peek segue a leitura mais recente que compreende e só recorre ao estado de resumo (`运输途中`) quando nenhuma linha define etapa. Como o localizador da Jifan não mostra data prevista de entrega, peso, nome do serviço, empresa final nem segundo código, o Peek continua a acompanhar o número `JFGJ…CN` na Jifan até ao fim.

Enquanto o localizador da Jifan responder, o Peek verifica novidades até a cada 10 minutos com a encomenda aberta ou com alertas ativos se a entrega for esperada hoje. A partir das 6:00 no relógio da transportadora (hora de Pequim) no dia em que a Jifan indica a saída para distribuição, as verificações podem passar a cada 2 minutos com a encomenda no ecrã ou alertas ativos. Fora do ecrã e sem alertas, bem como após dois dias sem novidades (12 horas em «Etiqueta criada» ou «Aceite pela transportadora»), verifica de hora a hora; quando apenas um serviço de seguimento responde, verifica de hora a hora, a cada 3 horas quando a última leitura tem um dia e até a cada 15 minutos no dia da distribuição, só com a encomenda aberta ou alertas ativos. Podes receber alertas de «Todos os registos», «Passos importantes» ou «Só a entrega». O Peek não pode contactar a Jifan nem o vendedor por ti.

## Perguntas frequentes sobre o seguimento da Jifan

### O que é a Jifan Logistics?

A Shenzhen Jifan International Logistics (`深圳市吉凡国际物流有限公司`), empresa de logística fundada em 2017 em Shenzhen, na China continental, que envia encomendas comerciais da China continental para compradores nos Estados Unidos por ar e por mar com números `JFGJ…CN`.

### Onde posso seguir um número JFGJ…CN?

No localizador [快件跟踪 em szjf56.cn](https://www.szjf56.cn/index/tracknumber/track), digitado em maiúsculas, ou no Peek. O localizador oficial só encontra encomendas dos últimos dois meses.

### Porque é que as horas no localizador da Jifan parecem erradas?

Não estão erradas: estão todas na hora de Pequim (`UTC+8`), mesmo nas leituras feitas nos Estados Unidos. No inverno, Pequim está 13 horas à frente de Nova Iorque e 16 à frente de Los Angeles, pelo que uma leitura após as 11:00 em Nova Iorque ou após as 08:00 em Los Angeles mostra a data do dia seguinte. O Peek converte cada hora para o fuso do teu dispositivo.

### O que significa `查询不到` no localizador da Jifan?

Significa «não encontrado» em chinês: não há nenhuma encomenda no localizador para o código introduzido. Verifica se há gralhas, escreve o número em maiúsculas sem espaços e fala com o vendedor se continuar a falhar; encomendas com mais de dois meses deixam de constar no localizador.

### Quanto tempo demora a Jifan a entregar?

O site da Jifan indica cerca de 8 a 10 dias seguidos para pequenas encomendas aéreas para os EUA, 10 a 12 para pequenas encomendas económicas, 20 a 25 para encomendas marítimas Matson e 25 a 40 para carga marítima de grandes dimensões. Guia-te sobretudo pela estimativa dada pelo vendedor.

:::sources
- [Jifan: 深圳市吉凡国际物流有限公司 (página inicial)](https://www.szjf56.cn/) – denominação social, fundação em 2017, quatro linhas para os EUA, pesos e espessuras, entrega UPS e UPS Ground, navios Matson e COSCO, dias seguidos, carta de porte única, compensação aos expedidores, telefone, e-mail e morada em Shenzhen
- [Jifan: 快件跟踪 (seguimento)](https://www.szjf56.cn/index/tracknumber/track) – localizador em chinês, botão 跟踪(Track), colunas de resultados, limite de 20 números e histórico de dois meses
- [Universal Parcel Scraper: Jifan](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/jifan) – formato JFGJ…CN e iniciais, ligação nos=, sistema CTTYEX, estados em chinês e inglês, hora de Pequim e extração de cidade e estado sem código ZIP
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formato da China Post e regras partilhadas de leitura de estados
:::
