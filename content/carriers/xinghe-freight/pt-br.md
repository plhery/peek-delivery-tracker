---
title: Rastreamento Xinghe Freight: códigos GZXH…YQ e etapas
description: Rastreie encomendas Xinghe Freight (星和货运) de Guangzhou: formato GZXH…YQ, em qual rastreador funciona, etapas em inglês e quem procurar em atrasos.
slug: rastreamento-xinghe-freight
published: 2026-11-26
updated: 2026-11-26
---

A Xinghe Freight (星和货运, 广州星和货运代理有限公司) é uma agente de cargas sediada em Guangzhou, na China. Se uma loja chinesa enviou seu pedido pela Xinghe, procure um código com GZXH, dez dígitos e YQ — o formato que o Peek reconhece como da Xinghe — e cole-o na caixa acima. O rastreamento da Xinghe funciona no rastreador da GZYL, outra agente de cargas de Guangzhou: o campo de busca no site da Xinghe envia seu código para lá. Esse rastreador roda em um endereço direto de servidor, em HTTP simples. O Peek lê esse rastreador para você e consulta serviços de rastreamento quando ele não reconhece o código.

## Código de rastreamento Xinghe Freight: como ele é

Os códigos que o Peek reconhece como da Xinghe Freight têm GZXH, dez dígitos e YQ, somando 16 caracteres. Com dígitos inventados:

| Código | O que é |
| --- | --- |
| `GZXH4829105736YQ` | GZXH, dez dígitos e YQ: formato da Xinghe Freight. O Peek identifica a Xinghe Freight na hora. |
| `GZXH482910573YQ` | Apenas nove dígitos: não segue o formato da Xinghe. Confira se falta um dígito. |
| `YL4829105736YQ` | YL, dez dígitos e YQ: formato dos conhecimentos da [GZYL](carrier:gzyl). Mesmo rastreador, outra agente de cargas. |
| `JXCAA4829105736YQ` | Também termina em YQ, como muitos códigos de agentes chinesas, mas começa com JXC: código da [Juxi Express](carrier:juxi-express). |

Espaços, hifens e letras minúsculas não fazem diferença ao colar o código no Peek.

**Onde encontrar.** O vendedor contrata o envio com a Xinghe e repassa o código. Se não encontrar, veja [como encontrar seu código de rastreamento](guide:find-tracking-number).

## Status de rastreamento da Xinghe Freight explicados

Como o rastreamento da Xinghe usa o rastreador da GZYL, as etapas aparecem no texto da GZYL: em inglês, inclusive com erros de grafia. O Peek interpreta os registros pela lista de etapas da GZYL. Na ordem em que a encomenda passa por elas:

| O rastreador mostra | O que significa para você | O Peek exibe |
| --- | --- | --- |
| `Order information received` | O vendedor enviou os dados do pedido. Ninguém está com o pacote ainda. | "Etiqueta criada" |
| `Arrive at the cross border receiving point` | A agente recebeu o pacote: primeiro registro já em suas mãos. | "Recebida pela transportadora" |
| `Goods leave the operation center` | O pacote saiu do centro operacional. | "Em trânsito" |
| `Arrive at the international airport` | No aeroporto, aguardando o voo. | "Em trânsito" |
| `Departing from the airport` | O voo partiu. | "Em trânsito" |
| `Item arrives at destination airport` | Pousou no seu país. | "Em trânsito" |
| `local carrier Agent Import customs clearance` | Um agente no seu país está realizando o desembaraço aduaneiro de importação. | "Na alfândega" |
| `The import customs keep it for inspection` | A alfândega do seu país está inspecionando a encomenda. | "Na alfândega" |
| `Customs clearance completed` | Liberada pela alfândega e de volta ao trajeto. | "Em trânsito" |
| `Internationaliy Package received at processing center` | Um centro de processamento no seu país recebeu a encomenda liberada. Não é uma nova coleta. | "Em trânsito" |
| `Leave the email processing center` | Saiu desse centro de processamento. | "Em trânsito" |
| `Arrival sorting, distribution center` | Em um centro de distribuição no seu país, com a cidade após o texto. | "Em trânsito" |
| `Leaving the sorting center` | Saindo de um centro de triagem, com a cidade. | "Em trânsito" |
| `Package is on the way` | Seguindo viagem no seu país, com uma cidade. Não significa que saiu para entrega. | "Em trânsito" |
| `Delivered` | A entrega, com a cidade. Pode ser um cronograma previsto, não uma leitura real: veja abaixo. | "Entregue", com aviso de cronograma fixo, salvo se o Peek retiver a etapa (abaixo) |

**Via Hong Kong.** Um pacote também pode exibir `香港处理中心已收寄【Hong Kong】`: postagem recebida no centro de processamento de Hong Kong, repassada pelo rastreador em chinês com a cidade entre colchetes. O Peek exibe o texto original em "Recebida pela transportadora".

**Linha em chinês.** Em pedidos ainda sem leituras, a página do rastreador pode mostrar `货物电子信息已经收到`, que significa que as informações eletrônicas da mercadoria foram recebidas. A própria plataforma gera essa linha: o vendedor criou o pedido, mas ninguém escaneou o pacote. O Peek exibe a linha em chinês sob "Etiqueta criada".

**Horários.** O Peek interpreta cada horário no rastreador como horário de Pequim, inclusive etapas no seu país, e exibe no fuso horário do seu aparelho. No inverno do hemisfério norte, uma etapa às 20:00 em Pequim corresponde às 13:00 em Paris ou Berlim, 12:00 em Londres e 7:00 em Nova York.

**Etapas escritas antecipadamente.** Nessa plataforma de rastreamento, agentes podem gravar toda a linha do tempo do pacote com antecedência, da coleta até a última etapa no seu país, e o rastreador exibe cada registro quando a hora chega. Essa etapa é um plano previsto, não uma leitura real. No rastreador da GZYL, o cronograma termina em um `Delivered` inserido pela plataforma em lote noturno no dia exibido: datado no dia seguinte à última etapa prevista, em horário comercial de Pequim, qualquer que seja o seu país. O Peek retém essas etapas (veja abaixo), mas a cópia de serviços de rastreamento pode continuar mostrando-as, então um `Delivered` lá não prova que a encomenda chegou.

## Quem entrega encomendas da Xinghe Freight

O Peek não sabe quem entrega pacotes da Xinghe no seu país. As etapas do rastreador não citam nenhuma empresa, e o site da Xinghe também não. A única empresa entregadora para a qual o Peek pode transferir um pacote da Xinghe é a [Swiss Post](carrier:swiss-post), na Suíça e em Liechtenstein, quando o registro da Xinghe a informa.

O site da Xinghe não trata de encomendas para compradores online: não divulga prazos, tentativas de entrega nem pontos de retirada. Valem as regras da transportadora final. Pergunte ao vendedor qual empresa entrega seu pedido e peça o código de rastreamento dela.

**Taxas alfandegárias na UE.** Desde 1º de julho de 2026, compras online vindas de fora da UE em pacotes de até €150 pagam direito aduaneiro de €3 por tipo de produto. Desde 1º de novembro de 2026, incide também a taxa de manuseio da União de €2 por tipo de produto, qualquer que seja o valor. Um item significa as mercadorias do pacote que compartilham a mesma classificação tarifária, descrição e, quando exigida, origem, independentemente da quantidade. Sobre o que pagar e como pagar no Reino Unido, Irlanda, EUA, Canadá e Austrália, veja [encomendas retidas na alfândega](guide:customs).

## Encomenda Xinghe Freight que não chega ou rastreamento parado

**Apenas `Order information received` ou `货物电子信息已经收到`.** O vendedor criou o pedido, mas ninguém escaneou o pacote. Se nada mudar após alguns dias, pergunte ao vendedor se o pedido foi realmente enviado.

**"Aguardando Xinghe Freight".** O Peek ainda não tem nenhuma leitura real para o seu código: o rastreador da GZYL não o reconhece ou guarda apenas um plano de etapas escritas com antecedência — que pode terminar em `Delivered` — e os serviços de rastreamento consultados pelo Peek também não têm nada. O Peek continua verificando. Para códigos desconhecidos, o rastreador da GZYL retorna um resultado com o código na referência e todos os demais campos vazios. O Peek interpreta isso como não encontrado: mostra "Aguardando Xinghe Freight" e, dois dias depois, "Confira o código de rastreamento". Um cronograma previsto conta como a Xinghe tendo o pedido, mantendo "Aguardando Xinghe Freight" sem pedir conferência do código. Compare o número com a mensagem do vendedor.

**Parada na alfândega.** Se houver cobrança de taxas de importação, veja [encomendas retidas na alfândega](guide:customs). Se o pacote ficar dias parado ali, avise o vendedor.

**O rastreador ou outro serviço mostra mais etapas do que o Peek.** As etapas extras, inclusive `Delivered`, podem ser um cronograma previsto retido pelo Peek, não leituras reais. Siga o prazo estimado pela loja e fale com o vendedor depois que vencer. Veja mais em [por que o rastreamento para de atualizar](guide:tracking-not-updating) e [rastrear encomendas da China](guide:tracking-from-china).

**`Delivered`, mas nada chegou.** A etapa pode fazer parte de um cronograma escrito antecipadamente. Salvo quando a Swiss Post faz a entrega, o Peek avisa sob "Entregue": "As atualizações de Xinghe Freight seguem um cronograma fixo, sem assinatura. Se nada chegou, fale com o vendedor." Informe ao vendedor que o pacote não chegou e pergunte qual transportadora faz a entrega e qual é o código dela. Veja mais em [encomenda entregue mas não recebida](guide:delivered-not-received).

**Extravio ou devolução.** O vendedor contratou o envio com a Xinghe, então peça reembolso ou reenvio ao vendedor ou ao marketplace. Veja [encomendas extraviadas](guide:lost-parcel) e [devolvida ao remetente](guide:returned-to-sender).

## Telefone e atendimento da Xinghe Freight

O site da Xinghe, xinghe-express.cn, escrito apenas em chinês, informa:

- **Telefone:** 19924483103, anunciado como atendimento 24 horas todos os dias (+86 199 2448 3103 ligando do exterior).
- **E-mail:** 3975605292@qq.com, voltado a consultas comerciais e cotações.
- **Endereço:** Guangzhou, província de Guangdong, sem nome de rua; o site pede contato prévio antes de visitar.

O site não traz canais para destinatários no exterior: o formulário de contato apenas abre um e-mail para esse endereço. O cliente da Xinghe é o vendedor, portanto fale primeiro com o vendedor e depois com o marketplace se ele não responder.

## Rastrear uma encomenda Xinghe Freight com o Peek

O [Peek](/) identifica a Xinghe Freight na hora para códigos GZXH…YQ, exibindo "Transportadora detectada". Se o vendedor informou um código de outro formato, cole-o mesmo assim: o Peek acompanha mais de 4.500 transportadoras. Para selecionar a Xinghe manualmente, toque em "Trocar" e busque Xinghe Freight (digitar 星和货运 também encontra), ou use "Trocar transportadora" em uma encomenda já adicionada.

O Peek consulta o rastreador da GZYL, assim como o campo de busca da própria Xinghe. Ele exibe o status, cada etapa com sua localidade e o país de destino quando consta no registro. Os horários são lidos no horário de Pequim e mostrados no fuso horário do seu aparelho. O Peek omite nomes, endereços, códigos postais, peso, dados de pagamento, assinatura e comprovante de entrega que o registro possa conter, e não mostra previsão de entrega porque o rastreador não informa nenhuma.

Quando o rastreador não reconhece seu código, o Peek consulta serviços de rastreamento e mostra a cópia deles, que pode ser o único histórico disponível para um código Xinghe. Enquanto nenhum dos dois tem etapas, aparece "Aguardando Xinghe Freight" e, dois dias depois, "Confira o código de rastreamento".

O Peek retém qualquer etapa gravada mais de 12 horas antes do horário exibido, qualquer etapa gravada no mesmo segundo que outra gravada mais de meia hora antes do seu horário (exceto o registro do pedido) e qualquer etapa datada após o momento da consulta no horário de Pequim. Em registros com etapas escritas com antecedência, retém também `Delivered`, mesmo quando gravado no próprio dia. Ele se baseia apenas nas etapas mantidas — não no status resumido — e descarta as cópias de serviços de rastreamento das etapas retidas, inclusive `Delivered`, mesmo mantendo outras etapas. Quando o rastreador não tem o código ou não responde, porém, o Peek não consegue distinguir na cópia de um serviço uma etapa prevista de uma leitura real. Ao reter etapas previstas, o histórico abre com a linha, até a entrega ou devolução: "Outros sites podem mostrar mais etapas, mas são apenas previsões." Em seguida, "Ver" lista essas etapas em cinza, da mais recente para a mais antiga, cada uma com a data informada ("Previsto para seg 12 out"). Essas etapas nunca contam como leituras reais: não alteram o status nem disparam alertas.

Exceto pela transferência para a Swiss Post, o Peek não transfere um envio Xinghe para a transportadora final: se o vendedor informar o código dela, adicione-o como uma encomenda separada. Enquanto você mantém a encomenda aberta, ou no dia da entrega com alertas ligados, o Peek verifica até a cada 10 minutos; nos demais horários e após ficar sem movimentação, a cada hora (por serviços de rastreamento, a cada hora, a cada 3 horas após um dia sem novidades e até a cada 15 minutos no dia da entrega enquanto aberta ou com alertas ligados). Ele pode notificar cada movimentação, apenas etapas importantes ou só a entrega: "Etapas importantes" cobre as duas etapas aduaneiras e `Delivered`, não voos nem triagem. O Peek não pode contatar a Xinghe Freight, a GZYL nem o vendedor por você.

## Perguntas frequentes sobre o rastreamento Xinghe Freight

### O que é a Xinghe Freight (星和货运)?

É a 广州星和货运代理有限公司 (Guangzhou Xinghe Freight Forwarding), agente de cargas em Guangzhou, China. Seu site informa fundação em 2014 e serviços de frete marítimo, aéreo e terrestre, agenciamento marítimo e desembaraço aduaneiro. Os códigos associados a ela no Peek têm GZXH, dez dígitos e YQ.

### Por que minha encomenda Xinghe Freight mostra o rastreamento da GZYL?

O rastreamento da Xinghe roda no rastreador da GZYL: a caixa de busca no site da própria Xinghe envia o código para lá, e as etapas aparecem no texto da GZYL. O Peek mantém a encomenda sob Xinghe Freight e lê esse rastreador para você.

### Quem entrega encomendas da Xinghe Freight no meu país?

O Peek não sabe: nem as etapas do rastreador nem o site da Xinghe informam a empresa. Apenas na Suíça e em Liechtenstein o Peek consegue acompanhar a transferência para a Swiss Post. Pergunte ao vendedor qual empresa entrega o pedido e qual é o código dela.

### Por que o Peek ainda não mostra rastreamento para meu código GZXH?

O rastreador pode ainda não ter o pedido ou conter apenas etapas escritas com antecedência — que podem terminar em `Delivered` — retidas pelo Peek fora das leituras reais e listadas apenas em "Ver". O Peek também consulta serviços de rastreamento e continua verificando. Se o rastreador não reconhece o código, o Peek trata como não encontrado: exibe "Aguardando Xinghe Freight" e, dois dias depois, "Confira o código de rastreamento". Quando há apenas etapas previstas, ele não pede para conferir o código. Compare o número com a mensagem do vendedor e, se nada aparecer após alguns dias, confirme se o pedido foi enviado.

:::sources
- [Xinghe Freight: 广州星和货运代理有限公司 (home page)](http://xinghe-express.cn/) – razão social, serviços de agenciamento de cargas, campo de busca que abre o rastreador da GZYL, telefone e e-mail
- [Xinghe Freight: 关于我们 (about)](http://xinghe-express.cn/about.html) – fundação em 2014, frete marítimo, aéreo e terrestre, agenciamento marítimo e declaração aduaneira
- [Xinghe Freight: 产品服务 (services)](http://xinghe-express.cn/services.html) – serviços listados, sem informações de encomendas para compradores online
- [Xinghe Freight: 联系我们 (contact)](http://xinghe-express.cn/contact.html) – telefone 24 horas, e-mail comercial e para cotações, endereço apenas como Guangzhou, aviso antes da visita e formulário que abre e-mail
- [European Commission: EU Customs Reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – direito aduaneiro de €3 por tipo de produto em pacotes de até €150 desde 1º de julho de 2026 e taxa de manuseio da União de €2 desde 1º de novembro de 2026
- [European Commission: Guidance on the €3 customs duty and the Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – taxa de manuseio por item qualquer que seja o valor e definição de item
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – nomes, formato GZXH…YQ, Xinghe lida no rastreador da GZYL, textos e etapas, aceitação em Hong Kong, linha em chinês, horário de Pequim, cronogramas escritos antecipadamente, serviços de rastreamento como alternativa, resposta para código desconhecido, transferência à Swiss Post e o que o Peek mostra, retém ou omite
:::
