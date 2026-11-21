---
title: Seguimento Techlink: números ZHPHD…ML, estados e entrega
description: Segue uma encomenda Techlink (智禾) enviada da China: formato do código ZHPHD…ML, significado das leituras, quem entrega no destino e o que fazer se parar.
slug: seguimento-techlink
published: 2026-11-21
updated: 2026-11-21
---

A Techlink (智禾国际物流, Zhihe International Logistics) é uma empresa de logística de Shenzhen, na China continental, que expede encomendas de vendedores online para a Europa e a América. Os seus códigos começam por `ZHPHD` e terminam em `ML`: introduz o teu na página de seguimento da Techlink (`www.techlinksz.cn/trajectory`) ou cola-o na caixa acima. A loja que enviou a compra fornece este número nos dados de expedição.

## Número de seguimento da Techlink: como é composto

A Techlink não publica o formato dos seus códigos. As suas cartas de porte têm 19 caracteres: `ZHPHD`, seis algarismos que correspondem a uma data válida (ano, mês e dia), mais seis algarismos e `ML`. Com algarismos inventados:

| Número | O que identifica |
| --- | --- |
| `ZHPHD260315482910ML` | `ZHPHD`, uma data de seis algarismos, seis algarismos e `ML`: uma carta de porte da Techlink. |
| `ZHPHD261315482910ML` | O mês `13` não existe: não é um código válido da Techlink. Verifica se há algum erro de digitação. |
| `RR482910576CN` | Duas letras, nove algarismos e `CN`: um registo postal da [China Post](carrier:china-post), não da Techlink. |

Minúsculas e espaços não fazem diferença no Peek. Se tiveres um código com outra estrutura, consulta [formatos de números de seguimento](guide:tracking-number-formats).

**Onde encontrá-lo.** Os clientes da Techlink são os comerciantes. A sua página inicial indica que as linhas dedicadas de pequenos volumes para a Europa e a América servem vendedores na Amazon, Walmart e `TK`, bem como lojas online independentes; não refere a AliExpress, a Temu nem a Shein. O vendedor reserva o envio e transmite o código no e-mail de confirmação ou na página da encomenda. Se não o encontrares, vê [como encontrar o número de seguimento](guide:find-tracking-number).

## O que significam os estados de seguimento da Techlink

A página de pesquisa da Techlink aceita até 40 códigos de uma vez, um por linha ou separados por vírgulas, e permanece em chinês mesmo quando mudas o menu para inglês. Para cada código apresenta a última leitura, o número total de registos (`条轨迹`) e os países de partida e de destino em chinês. Clica na linha ou em `展开全部` (expandir tudo) para ver todas as leituras com hora e país.

Antes de cada número, um símbolo colorido resume a última leitura: `▤` no armazém da Techlink, `➤` em trânsito ou em distribuição, um visto verde `✓` entregue, um ponto de exclamação vermelho `!` um problema (como uma tentativa de entrega falhada) e `?` sem resultados.

A Techlink não publica uma lista oficial de estados, mas grava cada leitura em inglês e chinês com a respetiva hora e, quase sempre, o país:

| O que escreve a Techlink | O que significa para a tua encomenda | O Peek mostra |
| --- | --- | --- |
| `Parcel electronic information received` (`收到电子信息`) | O vendedor enviou os dados eletrónicos da encomenda à Techlink, que ainda não recebeu o volume. | «Etiqueta criada» |
| `Parcel has been received and processed at original facility` (`仓库入库`) | A encomenda deu entrada e foi processada no armazém da Techlink na China continental. | «Aceite pela transportadora» |
| `Parcel has been packed and is ready for outbound shipping` (`包裹装箱`) | Embalada no armazém para seguir na linha internacional. | «Em trânsito» |
| `Departed from the original facility` | Saiu do armazém de origem da Techlink na China continental. | «Em trânsito» |
| `Export customs declaration completed` | Declaração aduaneira de exportação na China continental concluída após a saída do armazém; o volume não está retido. | «Em trânsito» |
| `Parcel is in transit to origin airport` | Em transporte rodoviário na China continental rumo ao aeroporto de partida. | «Em trânsito» |
| `Depart from the original airport` | O voo partiu do aeroporto de origem. | «Em trânsito» |
| `Arrive at the destination airport` | O avião aterrou no país de destino, antes do desalfandegamento de importação. | «Em trânsito» |
| `Shipment Received at Facility` | Recebida num hub no país de destino após o voo. | «Em trânsito» |
| `Your shipment has arrived at sorting center` | Chegou a um centro de triagem no país de destino. | «Em trânsito» |
| `Your shipment has been processed at sorting center and ready for shipping` | Triada nesse centro e pronta para o troço seguinte. | «Em trânsito» |
| `Your shipment has departed from the transit origin` | Saiu desse centro de triagem para a etapa seguinte. | «Em trânsito» |
| `In transit, it is currently in transit to the next facility` | Em circulação entre duas instalações no país de destino. | «Em trânsito» |
| `The parcel has been successfully picked up and is being prepared for transportation.` | Em encomendas para a Austrália: recolha pela J&T Express Australia após o voo da Techlink, seguida das leituras de trânsito e distribuição da J&T. | «Em trânsito» |
| `Parcel is out for delivery.` | Leitura retransmitida da transportadora local: a encomenda saiu para distribuição. | «Em distribuição» |
| `Parcel delivery failed [Wrong Address]. Parcel will be returned to the warehouse.` | Tentativa de entrega sem sucesso registada pela transportadora local (aqui morada incorreta; outro motivo pode surgir entre parênteses retos), com regresso ao armazém local. | «Tentativa de entrega» |

Para um número que não conhece, a página da Techlink apresenta `查询不到轨迹` (nenhum seguimento encontrado), `未知 → 未知` (países desconhecidos) e `暂无最新轨迹` (ainda sem leitura recente).

## Quem entrega as encomendas da Techlink no país de destino

A Techlink não o divulga. A sua página inicial descreve linhas porta a porta: carga aérea a partir da China continental, desalfandegamento no estrangeiro assegurado por uma empresa própria criada em 2023 e distribuição final através das suas próprias contas junto de transportadoras no país de destino, sem nomear nenhuma no site nem no seguimento. Nos históricos lidos pelo Peek, porém, remessas em Itália passam para a [iMile](carrier:imile), cujas linhas abrem com o centro entre parênteses como `【Milan center】Parcel shipped to【VEN 07】.` Na Austrália, a [J&T Express Australia](carrier:j-and-t-au) assume o volume após o voo a partir de `The parcel has been successfully picked up and is being prepared for transportation.` Nos Estados Unidos, a Techlink retransmite em alguns envios as leituras da [YWE](carrier:ywe), como `Parcel departed from Facility.` e `Parcel received by local Facility.`, até `Parcel is out for delivery.` ou uma falha na entrega.

Os registos do rastreador da Techlink podem conter o número próprio da transportadora final sem indicar o nome desta, e a página web da Techlink não tem campo para o mostrar. Assim que souberes qual é a transportadora local, são as regras dela que definem tentativas de entrega, avisos, pontos de recolha e prazos de levantamento — a Techlink não publica nenhuma.

O serviço expresso porta a porta da Techlink é prestado em modalidade DDP (direitos pagos) ou DDU (direitos não pagos). A opção contratada pelo vendedor determina se podem ser cobrados encargos de importação na chegada: consulta [encomendas retidas na alfândega](guide:customs).

## Encomenda Techlink parada ou atrasada: o que fazer

**Apenas `Parcel electronic information received`.** O vendedor registou a remessa, mas o volume ainda não entrou no armazém da Techlink na China continental. Se passarem vários dias sem novidades, pergunta ao vendedor se já expediu a encomenda.

**Parada depois de `Arrive at the destination airport`.** O voo aterrou no país de destino; seguem-se a alfândega de importação e a entrega à transportadora local, etapas que podem passar vários dias sem nova leitura. Em muitos envios o histórico da Techlink termina na aterragem ou poucos passos depois num hub ou centro de triagem local (`Shipment Received at Facility` até `In transit, it is currently in transit to the next facility`), embora noutros retransmita as leituras do distribuidor local até `Parcel is out for delivery.` Se deixar de avançar, pede ao vendedor o nome da transportadora no teu país e o respetivo número de seguimento. Mais informações em [seguimento que não atualiza](guide:tracking-not-updating) e [seguir uma encomenda vinda da China](guide:tracking-from-china).

**Nenhum resultado.** Confirma o código com o vendedor: um número `ZHPHD…ML` tem exatamente 19 caracteres. Uma encomenda acabada de criar também pode ainda não devolver registos.

**Extraviada, danificada ou dada como entregue sem ter chegado.** A Techlink não publica qualquer processo de reclamação para destinatários. O contrato de transporte é celebrado pelo vendedor, pelo que deves pedir o reembolso ou novo envio à loja ou ao marketplace. Vê [encomendas perdidas](guide:lost-parcel), [entregue mas não recebida](guide:delivered-not-received) e, para compras na Amazon, [seguimento de encomendas Amazon](guide:amazon-orders).

## Contactos e apoio ao cliente da Techlink

O site da Techlink (`www.techlinksz.cn`) não apresenta número de telefone, endereço de e-mail nem morada. A hiperligação `联系我们` (contacte-nos) não aponta para qualquer página, e o site não dispõe de chat nem de formulário de contacto.

Para questões sobre a encomenda, fala sempre com o vendedor. Para problemas na distribuição final, contacta a transportadora local quando souberes qual é.

## Seguir uma encomenda Techlink com o Peek

O [Peek](/) identifica de imediato qualquer código `ZHPHD…ML` como pertencente à Techlink — nenhuma outra transportadora do catálogo utiliza este formato — e mostra «Transportadora detetada» com o botão «Alterar». Não existe uma ligação da Techlink para colar, pois `www.techlinksz.cn/trajectory` não coloca o código no endereço da página.

O Peek lê o mesmo sistema de seguimento da página da Techlink e apresenta o estado atual, cada leitura com o respetivo país e o país de destino. As linhas em inglês surgem tal como a Techlink as escreve (numa ligação partilhada, uma entrega concluída mostra apenas `Delivered`). As leituras próprias na China continental, a receção eletrónica inicial sem local e qualquer leitura no estrangeiro iniciada pelo nome de um centro entre parênteses (`【…】`) são lidas na hora de Pequim; as leituras retransmitidas da J&T Express Australia para a Austrália usam a hora de Sydney qualquer que seja o estado australiano do depósito; as restantes mantêm o relógio local do respetivo país e o Peek converte cada hora associada a um fuso para o fuso horário do teu dispositivo — em `peektracker.com` e nas aplicações para iPhone e Android, o final do histórico indica «Horas no teu fuso horário: …». A Techlink não fornece data prevista de entrega, peso nem designação do serviço.

Enquanto a Techlink ainda não conhecer o número, o Peek mostra «À espera de Techlink»; se a resposta indicar inexistência de encomenda e ao fim de dois dias mais nenhuma fonte responder, surge «Ao fim de dois dias, a transportadora ainda não reconhece este número. Verifica-o, escolhe outra transportadora ou elimina a encomenda.»

Quando o registo da Techlink traz o código da transportadora final, o Peek analisa o seu formato: um número postal internacional (duas letras, nove algarismos e duas letras) aponta para o operador postal do país de destino, e um formato exclusivo de uma única transportadora do catálogo do Peek aponta para essa empresa. Quando o Peek lê diretamente o seguimento dessa transportadora, consulta-a e muda para ela assim que apresente progressos tão recentes quanto os da Techlink, mantendo o código `ZHPHD…ML` ao lado do segundo número. Caso contrário, permanece no seguimento da Techlink, cujo histórico costuma terminar à chegada ao aeroporto ou num centro de triagem de destino.

Enquanto mantiveres a encomenda aberta no ecrã ou, no dia da entrega, com as notificações ativas, o Peek verifica novidades até a cada 10 minutos — e até a cada 2 minutos a partir das 6h00 (hora local) no dia em que sai para distribuição, enquanto o rastreador próprio da Techlink responder (através apenas de serviços de seguimento externos: de hora a hora, a cada 3 horas após um dia sem novidades e até a cada 15 minutos no dia da entrega com a encomenda aberta ou alertas ligados; após transferência para a transportadora final, o Peek acompanha o ritmo desta) —; no resto do tempo, bem como após dois dias sem novidades (ou 12 horas em «Etiqueta criada» ou «Aceite pela transportadora»), consulta de hora a hora. Podes escolher alertas para «Todas as leituras», «Passos importantes» ou «Só a entrega». O Peek não pode contactar a Techlink nem o vendedor por ti.

## Perguntas frequentes sobre o seguimento Techlink

### O que é a Techlink (智禾)?

A Techlink é a `智禾国际物流(深圳)有限公司` (Zhihe International Logistics, Shenzhen), uma empresa logística da China continental que expede encomendas de vendedores online com linhas aéreas de pequenos volumes e expresso porta a porta para a Europa e a América. O seu site é `www.techlinksz.cn`.

### O que é um número de seguimento ZHPHD?

É uma carta de porte da Techlink com 19 caracteres: `ZHPHD`, seis algarismos de data, mais seis algarismos e `ML`. Podes segui-la em `www.techlinksz.cn/trajectory` ou diretamente no Peek.

### Quem entrega as encomendas da Techlink no meu país?

Uma transportadora local que a Techlink não nomeia no site nem na pesquisa de seguimento (nos históricos lidos pelo Peek surgiram a iMile em Itália, a J&T Express Australia na Austrália e a YWE nos Estados Unidos). Se as leituras não revelarem quem entrega e o Peek não tiver mudado automaticamente através de um segundo número, pergunta ao vendedor qual é a transportadora e o código local.

### Porque parou o meu seguimento Techlink depois de chegar ao aeroporto?

Em muitas remessas o seguimento da Techlink pára em `Arrive at the destination airport` ou poucos passos depois num hub ou centro de triagem do país de destino, ficando o resto do percurso visível apenas na transportadora final. Pede esse código local ao vendedor e contacta-o se o prazo estimado de entrega for ultrapassado.

:::sources
- [Techlink: 智禾官网 (página inicial)](http://www.techlinksz.cn/) – denominação social em Shenzhen, linhas de pequenos volumes para a Europa e América, vendedores na Amazon, Walmart, TK e lojas próprias, expresso porta a porta DDU e DDP, empresa externa de desalfandegamento criada em 2023, contas de entrega no destino e ligação de contacto sem destino
- [Techlink: 轨迹跟踪 (seguimento)](http://www.techlinksz.cn/trajectory) – pesquisa até 40 códigos em chinês, países de origem e destino, símbolos de estado e mensagem para números desconhecidos
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formato ZHPHD…ML, leituras em inglês e chinês, registos retransmitidos da iMile em Itália, J&T Express Australia na Austrália e YWE nos EUA, fusos horários de Pequim e Sydney, transição pelo segundo número e apresentação no Peek
:::
