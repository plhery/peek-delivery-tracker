---
title: Rastreamento GOFO Canada: códigos GFCA, status e atrasos
description: Rastreie um pacote da GOFO no Canadá: formato do código GFCA, significado dos status no Peek, entregas perdidas, atrasos e contato.
slug: rastreamento-gofo-canada
published: 2026-11-24
updated: 2026-11-24
---

A GOFO Canada é a rede canadense da GOFO, transportadora de encomendas presente em vários países, e opera o mesmo sistema de rastreamento da GOFO nos Estados Unidos. A GOFO informa que sua rede no Canadá já funciona em Toronto e Vancouver, com ampliação de cobertura em andamento. Seus códigos começam com `GFCA`: rastreie na página canadense da GOFO em gofo.com ou cole o código na caixa acima. Ainda sem número? Veja [como encontrar um código de rastreamento](guide:find-tracking-number).

## Código de rastreamento GOFO Canada: como reconhecer

O Peek identifica um código como GOFO Canada quando ele tem este formato, em maiúsculas ou minúsculas, com ou sem espaços:

| Formato (inventado) | O que é |
| --- | --- |
| `GFCA48291736405829` | `GFCA` seguido de 14 dígitos, o número de conhecimento (waybill) da GOFO Canada. Se a letra O for digitada logo após `GFCA`, o Peek a interpreta como zero |

A GOFO também atribui a cada pacote um número de rastreamento que repete o código `GFCA` ou usa a referência da própria loja. O Peek acompanha somente o waybill `GFCA`, portanto é esse o número que você deve colar.

**Códigos parecidos.** As outras redes da GOFO têm padrões próprios. `GFUS` com 14 dígitos, `GF` com 13, `CR` com 12 ou `MGP` com 13 pertencem à [GOFO Express](carrier:gofo) nos Estados Unidos; códigos `GFFR` e `CIFR` são da [GOFO France](carrier:gofo-fr); `GFIT` e `CIIT` são da GOFO Italy; `GFNL` e `GFES` pertencem às redes da GOFO na Holanda e na Espanha. Nenhum deles é código da GOFO Canada. Para diferenciar formatos, veja [formatos de código de rastreamento](guide:tracking-number-formats).

## Status do rastreamento GOFO Canada explicados

A GOFO Canada usa os mesmos códigos de eventos da GOFO dos EUA, e o Peek aplica o mesmo mapeamento. Um pacote no Canadá normalmente passa por estas etapas, nesta ordem:

| Registro da GOFO | Etapa no Peek | O que significa para você |
| --- | --- | --- |
| `Shipping Label Created` | Etiqueta criada | A loja gerou a etiqueta. A GOFO ainda não está com o pacote. |
| `Arrived at GOFO Regional Hub` | Em trânsito | Chegou a um dos centros regionais da GOFO. |
| `Loaded on Vehicle` | Em trânsito | Carregado para transferência entre unidades da GOFO, rumo à estação de entrega. |
| `Arrived at GOFO Delivery Station` | Em trânsito | Na estação da GOFO que fará a entrega. A GOFO diz que a entrega costuma ocorrer em 1 a 2 dias corridos. |
| `Out for Delivery` | Saiu para entrega | Com o motorista. |
| `Delivered` | Entregue | Entregue. |

Como os códigos são compartilhados, mensagens vistas em pacotes da GOFO nos EUA aparecem da mesma maneira se ocorrerem no Canadá. `Delivery Exception, Business Closed` indica tentativa sem sucesso, exibida como "Tentativa de entrega"; o motivo depois da vírgula varia. `Label Cancelled` significa que a etiqueta foi cancelada depois de emitida e nenhum pacote seguirá com aquele número; o Peek mostra "Precisa de atenção".

A GOFO inclui seu telefone e e-mail de suporte em algumas linhas de evento; o Peek remove esse trecho, assim como faz a página da própria GOFO. O evento de entrega aparece apenas como `Delivered`: o Peek omite o texto que a GOFO adiciona depois da vírgula.

## Pacote GOFO Canada atrasado, perdido ou tentativa sem sucesso

**Sem movimento.** Se só aparecer `Shipping Label Created`, a GOFO ainda não tem o pacote: a loja precisa entregá-lo. O Peek continua consultando a GOFO e, se passarem quatro dias sem nova leitura antes da saída para entrega, a página do pacote no Peek avisa. Mais em [rastreamento que não atualiza](guide:tracking-not-updating).

**Entrega perdida.** Uma tentativa sem sucesso aparece como "Tentativa de entrega", com o motivo informado pela GOFO na linha do evento. A GOFO afirma que um pacote que já saiu para entrega normalmente recebe nova tentativa em até 24 horas; quando há problema de endereço, como falta do número do apartamento, ele pode voltar ao depósito local enquanto o endereço é conferido. O passo seguinte depende da GOFO e da loja: veja [entregas perdidas](guide:missed-delivery) para saber o que checar. Se chegar um SMS cobrando pagamento para reentregar um pacote da GOFO, leia [golpes de SMS de entrega](guide:delivery-scam-texts) antes de clicar em qualquer link.

**Entregue, mas não recebido.** O Peek mostra quando a GOFO registrou a entrega, mas não traz a foto nem os detalhes adicionados pela GOFO na linha de entrega. A página de rastreamento da própria GOFO em gofo.com/ca/track exibe o comprovante de entrega — na maioria das vezes uma foto de onde o pacote foi deixado — pedindo o nome do destinatário usado no pedido. Olhe ao redor de casa primeiro: caixa de correio ou área compartilhada de correspondência, vizinhos, moradores da casa e portaria ou segurança do prédio. Se continuar sem achar, avise a GOFO (contatos abaixo), se possível com foto da fachada da casa ou prédio, e contate a loja com o número do pedido e o código `GFCA`. Veja [entregue, mas não recebido](guide:delivered-not-received).

**Atrasado ou extraviado.** Fale com a loja que enviou: ela tem os dados do pedido e do frete, e a GOFO afirma que o remetente pode abrir investigação por você. Veja [encomendas extraviadas](guide:lost-parcel) para saber o que pedir. Se o pacote estiver voltando à loja, veja [devolvida ao remetente](guide:returned-to-sender): a GOFO informa que uma encomenda em devolução não pode ser interceptada, então peça à loja reenvio ou substituição.

Comprou na Temu ou na Shein? Veja [pedidos da Temu](guide:temu-orders) e [pedidos da Shein](guide:shein-orders). Sobre tributos de importação, veja [alfândega](guide:customs).

## Atendimento ao cliente da GOFO Canada

Para pedidos atrasados, perdidos ou avariados, comece pela loja que enviou e tenha em mãos o código `GFCA` e o número do pedido. As páginas canadenses da GOFO ficam em gofo.com.

Para problemas de entrega ou dúvidas de rastreamento, a GOFO lista estes canais na [página de atendimento no Canadá](https://www.gofo.com/ca/customer-service):

- **E-mail:** cs@mail.gofo.com
- **Telefone (linha direta para destinatários):** +1 (949) 688-6032, de segunda a sexta-feira das 9h às 21h, sábados e domingos das 11h às 19h (horário do Leste).
- **WhatsApp:** +1 (787) 469-5811, de segunda a sexta-feira das 9h às 21h, sábados e domingos das 11h às 20h (horário do Leste).
- **Online:** formulário de consulta (que pede o número do waybill) e chat, ambos na mesma página.

## Como rastrear um pacote da GOFO Canada no Peek

O [Peek](/) identifica um código da GOFO Canada pelo formato: `GFCA` e 14 dígitos, em maiúsculas ou minúsculas, com ou sem espaços. Um link de rastreamento da GOFO Canada em gofo.com com `searchID=` e o número também funciona. O Peek acompanha apenas o waybill `GFCA`, não a referência da loja.

O Peek lê o rastreamento próprio da GOFO Canada: o status, cada movimentação com horário, cidade e província informados pela GOFO, esses locais no mapa, o horário da entrega e o país de destino. Os eventos aparecem como a GOFO escreve, sem a linha de suporte. O Peek mostra a data prevista da GOFO até o pacote sair para entrega, ser entregue, voltar ou aguardar retirada, e omite fotos de entrega, coordenadas e peso.

Enquanto o pacote estiver aberto no Peek, ou no dia da entrega com alertas ligados, o Peek verifica até a cada 10 minutos; no dia em que a GOFO registra saída para entrega, a partir das 6h no horário local da movimentação mais recente, a cada 2 minutos enquanto o rastreador da GOFO responder. Nos demais casos, verifica de hora em hora. Após 12 horas apenas em "Etiqueta criada" ou "Recebida pela transportadora", ou dois dias sem novidade, verifica de hora em hora em qualquer modo, e a cada 6 horas depois de sete dias em "Etiqueta criada".

Quando o registro mais recente completa quatro dias e o pacote segue em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito", o Peek sinaliza com a data desse registro, como "Sem atualizações desde seg., 12 de out.". A página passa a exibir "É comum uma encomenda ficar sem atualizações em voos longos, na alfândega ou no fim de semana. O Peek continua consultando GOFO Canada." junto com o link "Pedir ajuda a GOFO Canada" para a página canadense de suporte. Após três semanas sem novidade, mostra "Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, fale com o vendedor. O Peek continua consultando GOFO Canada."

O Peek pode avisar em "Cada movimentação", "Etapas importantes" ou "Só a entrega". Ele não pode agendar reentrega nem falar com a GOFO ou a loja por você.

## Perguntas sobre o rastreamento GOFO Canada

### A GOFO Canada é confiável?

A Corporations Canada registra a GOFO EXPRESS CANADA INC. como empresa federal ativa, número 1807362-7, com sede registrada em Mississauga, Ontário. Ela passou à esfera da Canada Business Corporations Act em julho de 2026, vinda de Ontário. A GOFO também tem redes nos EUA, França, Itália, Holanda e Espanha, que o Peek acompanha separadamente.

### O que significa "Loaded on Vehicle"?

Que a GOFO carregou o pacote para o trajeto entre duas unidades próprias, antes de chegar à estação que faz a entrega na sua região. O próximo evento costuma ser `Arrived at GOFO Delivery Station` e, depois, `Out for Delivery`.

### Por que meu código da GOFO não funciona?

Confira primeiro o formato: um código da GOFO Canada tem `GFCA` e 14 dígitos. Códigos `GFUS`, `GFFR` ou `GFIT` pertencem às redes da GOFO nos EUA, França ou Itália, que o Peek rastreia sob seus próprios nomes. Se um código `GFCA` ainda não retornar nada, confira no e-mail de envio da loja e fale com ela.

:::sources
- [Corporations Canada: GOFO EXPRESS CANADA INC.](https://ised-isde.canada.ca/cc/lgcy/fdrlCrpDtls.html?corpId=18073627) – empresa federal ativa 1807362-7, continuada de Ontário sob a CBCA em 2026-07-08, sede em Mississauga
- [GOFO Canada: Customer Service](https://www.gofo.com/ca/customer-service) – e-mail, telefone e horários para destinatários, WhatsApp, formulário e chat; comprovante de entrega, onde procurar, nova tentativa em 24 horas, 1–2 dias a partir da estação, atrasos e perdas pelo remetente e devoluções que não podem ser interceptadas
- [GOFO Canada](https://www.gofo.com/ca) – operando em Toronto e Vancouver, mais cobertura a caminho
:::
