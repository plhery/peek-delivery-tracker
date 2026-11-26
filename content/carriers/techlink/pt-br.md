---
title: Rastreamento Techlink: códigos ZHPHD, status e ajuda
description: Rastreie encomendas Techlink (智禾) da China: formato ZHPHD, significado das etapas em inglês, quem entrega no destino e o que fazer se parar.
slug: rastreamento-techlink
published: 2026-11-26
updated: 2026-11-26
---

A Techlink (智禾国际物流, Zhihe International Logistics) é uma empresa de logística de Shenzhen que transporta encomendas da China para Europa e América para vendedores online. Seus códigos começam com ZHPHD e terminam em ML: digite o seu na página de rastreamento da Techlink, www.techlinksz.cn/trajectory, ou na caixa acima. A loja que enviou seu pedido fornece o código nos detalhes de envio.

## Código de rastreamento Techlink: como ele é

A Techlink não publica formato de código. Seus conhecimentos têm 19 caracteres: ZHPHD, seis dígitos que formam uma data (ano, mês, dia), mais seis dígitos e ML. Com dígitos inventados:

| Código | O que é |
| --- | --- |
| `ZHPHD260101482917ML` | ZHPHD, uma data, seis dígitos e ML: conhecimento da Techlink. |
| `ZHPHD261301482917ML` | Mês 13 não é uma data válida: não segue o formato da Techlink. Confira se há erro de digitação. |
| `RR482910576CN` | Duas letras, nove dígitos e CN: número da [China Post](carrier:china-post), não da Techlink. |

Letras minúsculas e espaços não fazem diferença ao colar o código no Peek. Se o seu código tiver outro formato, veja [formatos de código de rastreamento](guide:tracking-number-formats).

**Onde encontrar.** Os clientes da Techlink são vendedores. Sua página inicial informa que as linhas de pequenas encomendas para Europa e América atendem vendedores na Amazon, Walmart e "TK", além de lojas independentes; não menciona AliExpress, Temu nem Shein. O vendedor contrata o envio e repassa o código no e-mail de envio ou na página do pedido. Se não encontrar, veja [como encontrar seu código de rastreamento](guide:find-tracking-number).

## Status de rastreamento da Techlink explicados

A página de rastreamento da Techlink aceita até 40 códigos por vez, um por linha ou separados por vírgula. Ela permanece em chinês mesmo quando o menu é trocado para inglês. Para cada código, exibe o último evento, a quantidade de eventos (`条轨迹`, "leituras") e os países de origem e destino em chinês. Clique na linha ou em `展开全部` ("expandir tudo") para ver todas as movimentações com horário e local.

Um ícone colorido na frente de cada código resume o último evento: ▤ no armazém da Techlink, ➤ em trânsito ou em rota de entrega, ✓ verde entregue, ! vermelho problema como entrega não realizada e ? nada encontrado.

A Techlink não publica lista de eventos. Ela registra cada movimentação em inglês e chinês, com horário e, na maioria dos casos, o país. Linhas vistas no rastreador até agora:

| A Techlink mostra | O que significa para você |
| --- | --- |
| `Parcel electronic information received` (收到电子信息) | O vendedor enviou os dados do pedido à Techlink. Ela ainda não está com o pacote. |
| `Parcel has been received and processed at original facility` (仓库入库) | O pacote deu entrada no armazém da Techlink na China. |
| `Parcel has been packed and is ready for outbound shipping` (包裹装箱) | Embalado para a linha internacional e prestes a sair da China. |
| `Departed from the original facility` | Saiu do armazém da Techlink na China. |
| `Export customs declaration completed` | Declaração de exportação na China continental após sair do armazém. O pacote não está retido. |
| `Parcel is in transit to origin airport` | Em transporte rodoviário na China do armazém ao aeroporto de saída. |
| `Depart from the original airport` | O voo partiu do aeroporto na China. |
| `Arrive at the destination airport` | O voo pousou no país de destino. O desembaraço de importação vem a seguir. |
| `Shipment Received at Facility` | Chegou a um hub no país de destino após o voo. |
| `Your shipment has arrived at sorting center` | Em um centro de triagem no país de destino após o voo. |
| `Your shipment has been processed at sorting center and ready for shipping` | Triado nesse centro para o próximo trecho. |
| `Your shipment has departed from the transit origin` | Saiu desse centro de triagem rumo ao próximo trecho. |
| `In transit, it is currently in transit to the next facility` | Em trânsito entre duas unidades no país de destino. |
| `The parcel has been successfully picked up and is being prepared for transportation.` | Em envios à Austrália: a J&T Express Australia assumiu o pacote após o voo (pode aparecer junto com o telefone dela). |
| `The parcel is dispatched from the transit center to the next destination` | Registro da J&T Express Australia: saiu de um centro de trânsito para o próximo destino. |
| `The parcel arrives at the distribution center and is prepared for transport to the next destination.` | Registro da J&T Express Australia: chegou a um centro de distribuição sendo preparado para o próximo trecho. |

No Peek, o status aparece como "Etiqueta criada" na primeira linha, "Recebida pela transportadora" na segunda e "Em trânsito" nas demais.

Para códigos desconhecidos, a página mostra `查询不到轨迹` ("sem rastreamento encontrado"), `未知 → 未知` (países desconhecidos) e `暂无最新轨迹` ("sem última movimentação ainda").

## Quem entrega encomendas da Techlink

A Techlink não informa. Sua página inicial descreve linhas porta a porta: frete aéreo saindo da China, desembaraço aduaneiro por empresa própria criada no exterior em 2023 e entrega por contas próprias junto a transportadoras do país de destino. O site não cita nenhuma dessas empresas, e o rastreador também não informa o nome delas. Na Itália, porém, históricos lidos pelo Peek mostram pacotes indo para a [iMile](carrier:imile): o rastreador repassa linhas da iMile que começam com o centro entre colchetes, como `【Milan center】Parcel shipped to【VEN 07】.` Na Austrália, a [J&T Express Australia](carrier:j-and-t-au) assume após o voo, aparecendo desde `The parcel has been successfully picked up and is being prepared for transportation.` (tabela acima). Nos Estados Unidos, a Techlink repassa eventos da [YWE](carrier:ywe), que entrega parte dos envios lá, como `Parcel departed from Facility.` e `Parcel received by local Facility.` (estação de entrega), até `Parcel is out for delivery.` ou uma entrega não realizada.

Os registros do rastreador podem guardar o código da transportadora entregadora sem o nome dela, e a página da Techlink não tem campo para exibi-lo. Assim que souber qual transportadora entrega, o rastreamento dela mostra o resto e valem as regras dela para tentativas, avisos, pontos de retirada e guarda. A Techlink não publica nada disso.

O serviço porta a porta da Techlink opera com impostos pagos (DDP) ou não pagos (DDU). A opção escolhida pelo vendedor define se taxas de importação podem recair sobre você: veja [encomendas retidas na alfândega](guide:customs).

## Encomenda Techlink que não chega ou rastreamento parado

**Apenas `Parcel electronic information received`.** O vendedor registrou o envio, mas o pacote ainda não chegou ao armazém da Techlink. Se nada mudar após vários dias, pergunte ao vendedor se foi despachado.

**Parada após `Arrive at the destination airport`.** O voo pousou no seu país. A alfândega de importação e o repasse à transportadora local vêm depois e podem gerar dias sem leitura. Depois dessa linha, o rastreador da Techlink mostrou até hoje apenas etapas dentro do país de destino — um hub, um centro de triagem e a saída dele, as cinco linhas a partir de `Shipment Received at Facility` acima — e, em alguns pacotes, leituras da entregadora local até `Parcel is out for delivery.` ou uma entrega não realizada. Se continuar parado, pergunte ao vendedor qual transportadora faz a entrega e peça o código dela. Veja mais em [por que o rastreamento para de atualizar](guide:tracking-not-updating) e [rastrear encomendas da China](guide:tracking-from-china).

**Nada encontrado.** Confira o código com o vendedor: códigos ZHPHD têm exatamente 19 caracteres. Um pacote recém-registrado também pode ainda não mostrar nada.

**Extravio, dano ou entregue sem receber.** A Techlink não publica processo de reclamação para destinatários. O vendedor detém o contrato com a Techlink, então peça reembolso ou reenvio ao vendedor ou ao marketplace. Veja [encomendas extraviadas](guide:lost-parcel) e [encomenda entregue mas não recebida](guide:delivered-not-received); para pedidos na Amazon, [rastreamento de pedidos da Amazon](guide:amazon-orders).

## Telefone e atendimento da Techlink

O site da Techlink, www.techlinksz.cn, não lista telefone, e-mail nem endereço comercial. O link 联系我们 (fale conosco) não leva a lugar algum, e não há chat nem formulário de contato.

Sobre o seu pedido, fale com o vendedor. Para problemas de entrega no seu país, fale com a transportadora local assim que souber qual é.

## Rastrear uma encomenda Techlink com o Peek

O [Peek](/) identifica Techlink na hora para códigos ZHPHD: nenhuma outra transportadora no catálogo usa esse formato. Não há link da Techlink para colar, pois a página de rastreamento não inclui o código no endereço.

O Peek lê o próprio rastreador da Techlink — o mesmo usado pela página de rastreamento dela — e mostra o status, cada movimentação com o país e o país de destino. As linhas em inglês aparecem como a Techlink as escreve. Leituras na China e qualquer linha no exterior iniciada pelo nome do centro entre colchetes (`【…】`) são lidas no relógio da China; leituras da J&T Express Australia em envios à Austrália, no horário de Sydney (independentemente do estado do depósito); as demais, no relógio do país correspondente. O Peek exibe tudo no fuso horário do seu aparelho. A Techlink não informa data prevista, peso nem nome do serviço, portanto o Peek não mostra nenhum deles.

Quando o registro da Techlink traz o código da transportadora entregadora, o Peek analisa o formato: um número postal internacional (duas letras, nove dígitos e duas letras) aponta para os correios do país de destino; um formato exclusivo de uma transportadora no catálogo aponta para ela. Quando o Peek lê o próprio rastreador dessa empresa, ele a consulta e migra para ela assim que surgir progresso tão recente quanto o da Techlink; a encomenda mantém o código ZHPHD e mostra o outro ao lado. Sem essa correspondência, o Peek permanece no rastreador da Techlink, cujas linhas conhecidas terminam em `Arrive at the destination airport` ou poucos passos depois no país de destino.

Enquanto você mantém a encomenda aberta, ou no dia da entrega com alertas ligados, o Peek verifica até a cada 10 minutos — e até a cada 2 minutos a partir das 6h locais no dia em que sai para entrega — enquanto o próprio rastreador da Techlink responde; nos demais horários, a cada hora (por serviços de rastreamento, a cada hora, a cada 3 horas após um dia sem novidades e até a cada 15 minutos no dia da entrega sob a mesma condição). Após dois dias sem novidades (12 horas enquanto ainda mostrar "Etiqueta criada" ou "Recebida pela transportadora"), verifica a cada hora. Para alertas, escolha "Cada movimentação", "Etapas importantes" ou "Só a entrega". Não pode contatar a Techlink nem o vendedor por você.

## Perguntas frequentes sobre o rastreamento Techlink

### O que é a Techlink?

É a 智禾国际物流(深圳)有限公司, Zhihe International Logistics (Shenzhen), empresa que envia encomendas da China para lojas online, com linhas de pequenas encomendas para Europa e América. Seu site é www.techlinksz.cn.

### O que é um código de rastreamento ZHPHD?

É um conhecimento da Techlink: ZHPHD, seis dígitos que formam uma data, mais seis dígitos e ML. Rastreie na página da Techlink ou no Peek, que o reconhece imediatamente.

### Quem entrega encomendas da Techlink?

Uma transportadora no seu país que a Techlink não divulga; na Itália o rastreamento repassou leituras da iMile, nos Estados Unidos da YWE e na Austrália da J&T Express Australia, que assume após o voo. O rastreador pode guardar o código dela sem o nome, então pergunte ao vendedor ou deixe o Peek seguir o código quando o formato identificar a empresa.

### Por que meu rastreamento da Techlink parou?

As leituras vistas no rastreador da Techlink até agora terminam na chegada do voo ao país de destino (`Arrive at the destination airport`) ou poucos passos depois, em um hub ou centro de triagem desse país ou entre duas unidades (`In transit, it is currently in transit to the next facility`); em alguns envios repassa também leituras da entregadora até `Parcel is out for delivery.` ou uma entrega não realizada. O restante aparece no rastreamento da transportadora local, portanto peça o nome e o código dela ao vendedor e acione o vendedor se houver atraso.

:::sources
- [Techlink: 智禾官网 (home)](http://www.techlinksz.cn/) – razão social, linhas de pequenas encomendas para Europa e América, Amazon, Walmart, TK e lojas independentes, porta a porta DDU/DDP, desembaraço próprio criado em 2023, contas de entrega no destino e link de contato sem destino
- [Techlink: 轨迹跟踪 (tracking)](http://www.techlinksz.cn/trajectory) – até 40 códigos, página em chinês, países, ícones de status e resposta para código desconhecido
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formato ZHPHD, textos em inglês e chinês, linhas da iMile na Itália, assunção pela J&T Express Australia, leituras da YWE nos EUA, respostas do rastreador, código da entregadora e o que o Peek mostra
:::
