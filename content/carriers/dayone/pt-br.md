---
title: Rastreamento DayOne: códigos DOT, status e quem entrega
description: Rastreie encomendas DayOne da China: formato de códigos DOT0/DOT1, status no DayOneTrack explicados, quem faz a entrega local e como resolver atrasos.
slug: dayone-tracking
published: 2026-11-26
updated: 2026-11-26
---

A DayOne é o armazém que despacha pedidos online de lojas a partir da China. Ela não é uma transportadora: seu site afirma ser "o armazém de atendimento de pedidos, não uma prestadora de serviço de entrega". Outras empresas transportam o pacote, e o rastreador da DayOne, [DayOneTrack](https://www.dayonetrack.com/), em inglês e chinês, reúne as leituras delas. Consulte seu número `DOT` lá ou cole na caixa acima. A loja fornece esse número nos dados de envio do pedido.

## Código de rastreamento DayOne: como ele se parece

A DayOne não publica uma especificação oficial de formato. Os formatos que o Peek reconhece como DayOne, com dígitos fictícios:

| Código | O que é |
| --- | --- |
| `DOT0482917364058291` | `DOT` seguido de 16 dígitos: código DayOne. |
| `DOT1482917364058291` | `DOT1` seguido de 15 dígitos: também DayOne; o rastreador da DayOne consulta esses códigos em outro sistema. |
| `DOT048291736405829` | Apenas 15 dígitos: não corresponde ao formato DayOne, e o Peek não a identifica. Confira se faltou um dígito. |

No Peek, minúsculas, espaços e hifens não fazem diferença quando você cola apenas o código. Ao colar uma mensagem inteira, mantenha `DOT` colado aos dígitos: com espaço logo após `DOT`, o Peek pode ler só os números e identificar outra transportadora.

**Onde encontrar.** No e-mail de envio da loja ou na página do pedido. Se não encontrar, veja [como encontrar seu código de rastreamento](guide:find-tracking-number).

**No rastreador da DayOne.** É possível consultar até 10 números de uma vez, um por linha. O site aceita apenas códigos com `DOT0` ou `DOT1`: qualquer outro número, como o do correio local, exibe `Not found`. Ao lado do código, o resultado pode mostrar `Reference Number` (referência própria da loja) e o país de destino.

## Status de rastreamento da DayOne explicados

O rastreador da DayOne atribui um status geral em inglês e chinês e lista as leituras em `Package Track`, da mais recente para a mais antiga:

| Status da DayOne | O que significa para você | O Peek mostra |
| --- | --- | --- |
| `Not found` (查询不到) | A DayOne ainda não tem registro para esse número e acrescenta: "The order information has not been updated yet". | "Aguardando DayOne" |
| `Info received` (收到信息) | Dados de envio recebidos; o pacote pode ainda não ter saído do armazém. | "Etiqueta criada" |
| `In transit` (运输途中) | A caminho. | "Em trânsito" |
| `Pick up` (到达待取) | Chegou e aguarda você retirar. | "Pronta para retirada" |
| `Out for Delivery` (派送途中) | Saiu para entrega. | "Saiu para entrega" |
| `Undelivered` (投递失败) | Uma tentativa de entrega falhou. | "Tentativa de entrega" |
| `Delivered` (成功签收) | Entregue. | "Entregue" |
| `Alert` (可能异常) | Pode haver um problema. | "Precisa de atenção" |
| `Expired` (运输过久) | Em chinês, "em trânsito há tempo demais": o pacote está circulando há bastante tempo. | o status da leitura mais recente |

O Peek se orienta pela leitura mais recente que consegue interpretar, usando o status geral da DayOne apenas quando nenhuma leitura é reconhecida.

Cada leitura traz hora, local e o texto da empresa que está com o pacote: Yanwen na China e depois o correio do seu país. As leituras mais comuns, na ordem habitual:

| A DayOne informa | O que significa para você | O Peek mostra |
| --- | --- | --- |
| `Order processed by shipper` | A Yanwen recebeu os dados do pedido; ninguém bipou o pacote físico ainda. | "Etiqueta criada" |
| `Yanwen Pickup Scan`, `Processing information input` | A Yanwen já está com o pacote na China e o está processando. | "Recebida pela transportadora" |
| `Yanwen facility - Outbound`, `Arrived at domestic terminal station` | Saiu do centro de triagem da Yanwen e chegou a um terminal na China antes da alfândega de exportação. | "Em trânsito" |
| `Port of departure - Received by carrier`, `International shipment release - Export`, `Port of departure - Departure` | No porto de partida, liberado pela alfândega de exportação chinesa (não a do destino) e embarcado em voo. | "Em trânsito" |
| `Port of destination - Arrival`, `ARRIVED AT DESTINATION AIRPORT` | Pousou no seu país ou próximo dele; a alfândega de importação ainda vai ocorrer. | "Em trânsito" |
| `CLEARED BY CUSTOMS`, `COLLECTED FROM AIRPORT TERMINAL`, `ARRIVED AT FACILITY IN DESTINATION COUNTRY` | A alfândega do seu país liberou o volume, que seguiu do aeroporto para uma unidade local. | "Em trânsito" |
| `RECEIVED BY AUSTRALIA POST FOR TRANSPORTATION TO PROCESSING FACILITY` | Na Austrália: entregue à Australia Post. | "Em trânsito" |
| `ITEM PROCESSED AT POST FACILITY`, `PROCESSED THROUGH AUSTRALIA POST FACILITY`, `TRANSFERRED` | Triado pelo correio local e encaminhado adiante. | "Em trânsito" |
| `WITH AUSTRALIA POST FOR DELIVERY TODAY` | Saiu para entrega hoje. | "Saiu para entrega" |
| `DELIVERED WITH SAFE DROP` | Entregue e deixado em local seguro no endereço. | "Entregue" |

`SHIPPING INFORMATION RECEIVED`, `SHIPPING INFORMATION RECEIVED BY AUSTRALIA POST` e `SHIPPING INFORMATION APPROVED BY AUSTRALIA POST` indicam apenas que o correio recebeu os dados do pacote. Eles podem surgir depois de leituras posteriores: isso não é retrocesso e o Peek mantém o avanço mais recente.

Se a única leitura for `Shipment has begun. In transit to destination city`, a DayOne repassou o pacote para a [YunExpress](carrier:yunexpress), e o restante do histórico está no rastreador da YunExpress sob um número `YT` + 16 dígitos. `Clearence processing completed - Export` é a liberação de exportação da YunExpress, com a grafia usada por ela.

## Quem entrega as encomendas DayOne

A DayOne não declara quem faz a entrega final no seu país, mas as leituras revelam quem está com o volume: Yanwen na China e depois o correio local, como a [Australia Post](carrier:australia-post) em entregas para a Austrália.

A DayOne não publica regras sobre tentativas de entrega, avisos ou pontos de retirada: valem as regras da empresa que entrega. Após `Pick up` ou `Undelivered`, procure um aviso ou mensagem dela. Veja [entregas perdidas](guide:missed-delivery) e [encomendas não retiradas](guide:uncollected-parcel).

**Alfândega na UE.** Compras online de fora da UE pagam €3 de imposto aduaneiro por item em encomendas até €150 desde 1º de julho de 2026 e a taxa de manuseio da União de €2 por item, qualquer que seja o valor, desde 1º de novembro de 2026 (item são mercadorias com a mesma classificação tarifária, descrição e origem exigida). O site da DayOne nada informa sobre tributos ou quem os cobra. Em outros países valem as regras locais: veja [encomendas retidas na alfândega](guide:customs).

## Encomenda DayOne sem atualização, perdida ou devolvida

A DayOne encaminha você para a loja. Seu rastreador diz: "Para todas as questões de logística, itens perdidos, reembolsos e reenvios, entre em contato com o atendimento da loja onde fez o pedido."

**`Not found`.** A DayOne ainda não tem registro para esse código. Confira se digitou `DOT` e 16 dígitos; se estiver certo, o pedido pode ainda não ter saído. O Peek mostra "Aguardando DayOne" e segue verificando. Se após dois dias a DayOne continuar sem reconhecer e nenhuma outra fonte responder, o Peek informa que após dois dias a transportadora ainda não reconhece o código, sugerindo conferir, trocar de transportadora ou excluir o pacote — passando a consultar diariamente e, depois de uma semana, semanalmente. Se nada aparecer em alguns dias, pergunte à loja.

**Apenas `Info received` ou `Order processed by shipper`.** Ninguém bipou o pacote físico ainda; se continuar assim por vários dias, pergunte à loja quando foi enviado.

**Sem nova leitura há dias.** Quando a leitura mais recente tem quatro dias e o pacote segue em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito", o Peek sinaliza a data dessa leitura ("Sem atualização de rastreamento desde seg 12 out"). A página então mostra "É comum uma encomenda ficar sem atualizações em voos longos, na alfândega ou no fim de semana. O Peek continua consultando DayOne.", com o link "Obter ajuda da DayOne" para o site oficial (assim que o Peek passa a acompanhar o correio local, o link muda para esse correio se houver página cadastrada). Após três semanas sem novas leituras, mostra "Nenhuma novidade há mais de três semanas. Se ainda não chegou, fale com o vendedor. O Peek continua consultando DayOne." Veja [por que o rastreamento para de atualizar](guide:tracking-not-updating) e [rastrear encomendas da China](guide:tracking-from-china).

**`Alert` ou `Expired`.** Leia as últimas movimentações e peça ajuda à loja informando seu código `DOT`.

**Perdida, danificada ou devolvida.** A DayOne não oferece canal de reclamação para destinatários. Peça reembolso ou reenvio à loja. Veja [encomendas perdidas](guide:lost-parcel) e [devolvida ao remetente](guide:returned-to-sender).

**Entregue, mas não encontrada.** `DELIVERED WITH SAFE DROP` indica entrega deixada no endereço. Olhe ao redor da residência, pergunte a vizinhos e avise a loja. Veja [entregue mas não recebido](guide:delivered-not-received).

## Contato da DayOne

Conforme o [rastreador da DayOne](https://www.dayonetrack.com/):

- **Sobre sua encomenda:** fale com a loja onde comprou, conforme orientação da DayOne.
- **E-mail comercial:** `info@dayone-shipping.com`, indicado apenas para parcerias e contatos comerciais, não para consultas de pacotes.

O rastreador da DayOne não divulga telefone, chat, formulário nem endereço, e informa armazenar apenas códigos de rastreamento sem manter dados de pedidos.

## Rastreie uma encomenda DayOne com o Peek

O [Peek](/) identifica DayOne imediatamente a partir de um código `DOT` com 16 dígitos ("Transportadora detectada"). Links de dayonetrack.com com `trackNo=` também funcionam. Para outros códigos, cole normalmente: o Peek acompanha mais de 4.500 transportadoras e exibe "Consultando serviços de rastreamento…" quando vários formatos coincidem. Se o Peek não identificar DayOne, toque em "Alterar" e selecione DayOne em "Todas as transportadoras" (buscar "Day One" também encontra), ou use "Alterar transportadora" em um pacote já adicionado.

Para códigos `DOT0`, o Peek lê diretamente o rastreador da DayOne: status, cada leitura com local, país de destino e horário de entrega. Para códigos `DOT1`, consulta serviços de rastreamento.

Quando a DayOne fornece o fuso horário da leitura, o Peek usa esse fuso; sem ele, calcula a partir do local ou das leituras vizinhas (no fuso do seu país assim que constar chegada) e mostra tudo no relógio do seu dispositivo. O Peek omite códigos postais, nome de quem recebeu e `Reference Number` da loja. A DayOne não informa data prevista de entrega nem peso.

O site da DayOne não mostra o código da empresa local, mas sua consulta às vezes o retorna. O Peek então consulta também essa empresa — o correio do seu país em códigos postais de 13 caracteres (como Royal Mail, USPS, Canada Post, An Post ou Australia Post) ou a transportadora correspondente ao formato (como YunExpress para códigos `YT`). Assim que ela tiver leitura ao menos tão recente quanto a da DayOne, o Peek passa a acompanhá-la, lista o código dela ao lado do `DOT` e preserva o histórico da DayOne.

Para códigos `DOT0`, enquanto o pacote estiver aberto no Peek ou no dia da entrega com notificações ativadas, o Peek consulta até a cada 10 minutos, ou a cada 2 minutos a partir das 6h (horário local) no dia em que a DayOne registra saída para entrega enquanto o rastreador próprio responder. Após dois dias sem novidades (12 horas em "Etiqueta criada" ou "Recebida pela transportadora"), consulta de hora em hora, e a cada 6 horas após sete dias em "Etiqueta criada". Quando serviços de rastreamento respondem (como em códigos `DOT1`), consulta de hora em hora, a cada 3 horas após um dia sem movimento e a cada 6 horas após sete dias em "Etiqueta criada"; no dia da entrega, até a cada 15 minutos com o pacote aberto ou notificações ativadas.

O Peek pode notificar em "Cada leitura", "Etapas importantes" ou "Apenas entrega". Ele não pode contatar a DayOne nem a loja por você.

## Perguntas sobre o rastreamento da DayOne

### A DayOne é uma transportadora?

Não. A DayOne declara ser "o armazém de atendimento de pedidos, não uma prestadora de serviço de entrega". Outras empresas transportam o pedido: nas leituras da DayOne, a Yanwen na China e depois o correio do seu país.

### Onde posso rastrear um código DOT?

No rastreador da DayOne, [dayonetrack.com](https://www.dayonetrack.com/), em inglês ou chinês (até 10 códigos por vez), ou colando no Peek.

### Por que o rastreador da DayOne diz Not found?

A DayOne ainda não tem informações para esse código. Confira se digitou `DOT` e todos os 16 dígitos. Se estiver correto, aguarde um ou dois dias e pergunte à loja se o pacote já saiu.

### Por que os horários no rastreador da DayOne parecem fora de ordem?

Cada leitura mostra o horário local de onde foi registrada, sem fuso horário, e o site ordena literalmente por esses horários. No inverno do hemisfério norte, Pequim fica 8 horas à frente de Londres e 13 à frente de Nova York, então uma leitura no destino pode parecer mais antiga que a última na China; já Sydney fica 3 horas à frente de Pequim no verão australiano. O Peek converte todos os horários para o fuso do seu dispositivo.

:::sources
- [DayOne: Dayone Tracking](https://www.dayonetrack.com/) – armazém e não transportadora, pós-venda na loja, privacidade, e-mail comercial, inglês e chinês, até 10 códigos, consultas DOT0/DOT1, status, campos e horários
- [Comissão Europeia: Reforma Aduaneira da UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – imposto de €3 desde 1º de julho de 2026 e taxa de manuseio da União de €2 desde 1º de novembro de 2026
- [Comissão Europeia: Orientação sobre o imposto aduaneiro de €3 e taxa de manuseio da União (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – €2 por item qualquer que seja o valor e definição de item
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formato do código, links e consultas, textos e códigos de leitura, fusos, código da transportadora local, repasse e o que o Peek lê
:::
