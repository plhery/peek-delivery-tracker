---
title: Rastreamento iMile: códigos, status, alfândega e contato
description: Rastreie encomendas da iMile no Brasil: formatos de código IM e numéricos, significado dos status em inglês, Remessa Conforme e canais de atendimento.
slug: rastreamento-imile
published: 2026-11-24
updated: 2026-11-24
---

A iMile é uma empresa de entregas para comércio eletrônico fundada em Dubai em 2017. Ela transporta encomendas internacionais, inclusive saídas da China, realiza o desembaraço aduaneiro e faz a entrega de última milha com equipe própria em diversos países. Você pode acompanhar seu pacote na página de rastreamento da iMile, em [imile.com/BR-pt/track](https://www.imile.com/BR-pt/track), ou colando o código na caixa acima. O número é informado pela loja nos detalhes de envio do pedido.

## Código de rastreamento iMile: formatos e onde encontrar

A iMile não publica um formato padrão único em seu site, mas os códigos usados em seus envios seguem padrões conhecidos. Todos os exemplos abaixo usam dígitos inventados:

| Código (inventado) | O que significa |
| --- | --- |
| `IM06014829173640` | `IM`, `0601` e 10 dígitos: formato exclusivo da iMile. |
| `IM48291736405812` | `IM` e 14 dígitos: também pertence à iMile. |
| `6041526482917` | 13 dígitos começando com `6`, seguidos de seis dígitos em formato de data (mês, dia, ano): conhecimento de transporte próprio da iMile. |
| `49482917364058` | 14 dígitos começando com `49`: usado pela iMile em vários envios internacionais. Como [Jadlog](carrier:jadlog), DPD, BRT e Chronopost também usam 14 dígitos, o Peek consulta a iMile entre as primeiras. |
| `3482917364058` | 13 dígitos começando com `3`: frequente na iMile, mas compartilhado com outras transportadoras; o Peek consulta todas em paralelo. |
| `2482917364058` | Outros códigos de 13 dígitos: não seguem os padrões da iMile (podem pertencer a Yunda, Korea Post ou outras transportadoras). |
| `LP61840295738142` | Código `LP` seguido de 14 dígitos: pertence à logística Cainiao (AliExpress), não diretamente à iMile. |

Na página de rastreamento da iMile, digite o código no campo de consulta para visualizar o histórico; quando o número ainda não consta na base, o campo de status permanece vazio sem mensagem de erro. Se não encontrar o código no e-mail da loja ou no aplicativo onde comprou, consulte [como encontrar o código de rastreamento](guide:find-tracking-number) e fale com o vendedor.

## Significado dos status de rastreamento da iMile

As movimentações da iMile retornam em inglês na consulta direta feita pelo Peek. Os nomes entre colchetes `【】` indicam o aeroporto ou centro de distribuição que registrou a leitura. Na ordem habitual do trajeto:

| A iMile informa | O que significa para você |
| --- | --- |
| `Order submitted, pending to receive.` | A loja registrou o envio, mas a iMile ainda não recebeu o pacote fisicamente. |
| `Your order has been picked up.` | A iMile coletou a encomenda com o remetente. |
| `【IST】The flight has taken off.` | O voo internacional decolou; a sigla indica o aeroporto de origem. |
| `【RUH】Flight Arrived - Waiting for customs clearance.` | A encomenda desembarcou e aguarda liberação alfandegária. |
| `Clearance Processing` | O pacote está em análise na alfândega. |
| `【Milan center】Your order has been received.` | Chegou ao centro de triagem da iMile no país de destino. |
| `Your order has been shipped from 【Milan center】.` | Saiu do centro de triagem rumo à próxima unidade ou base de entrega. |
| `Parcel is currently being processed.` | Em processamento na base responsável pela sua região. |
| `Our delivery associate is out for delivery.` | Saiu para entrega com o entregador hoje. |
| `User not at home` | Tentativa de entrega sem sucesso: destinatário ausente. |
| `Delivery attempt finished,return to Local logistics company warehouse.` | Após uma tentativa sem sucesso, o pacote voltou à base local. |
| `The delivery method has been confirmed with the recipient as Self-Pickup.` | Após uma tentativa falha, foi combinada a retirada pelo destinatário; o pacote ainda não mudou de local. |
| `The parcel has been delivered to …. Please collect your parcel promptly at … using your pickup code.` | O pacote aguarda retirada no ponto indicado com seu código de coleta. O Peek exibe "Pronta para retirada", e não entrega concluída. |
| `【Contact Error】need to return.` | O entregador não conseguiu contato e o processo de devolução foi iniciado. |
| `Your order has been delivered successfully.` | Entregue ao destinatário. |
| `Order delivery failed, returning to sender.` | Entrega não concluída; a encomenda está voltando ao remetente. |
| `【Milan Transit Center】Shipment returned to origin.` | Devolvida à origem. |

Em envios europeus pode aparecer `Order Submited.` (com erro de grafia próprio da iMile), com o mesmo sentido de etiqueta criada, assim como `Your order has assigned delivery drvier.` antes da saída para entrega. A linha `Shipment is scheduled to` seguida de uma data entre colchetes marca o dia agendado para a entrega; já `Scheduled Delivery`, quando surge entre uma tentativa e outra sem data, apenas indica novo agendamento sem movimentação física.

Outros motivos comuns de tentativa falha incluem `wrong address` ou `Wrong Address(Insufficient address details)` (endereço incorreto ou incompleto), `switch off` (telefone desligado), `Customer Wants to Change Location`, `Route not completed`, `Traffic, Strike, Bad weather`, linhas iniciadas por `failed to schedule due to` (como `noanswer`) e `The driver failed to deliver due to shop(building) closure, we will try to deliver again.` O aviso `Lost order` também pode aparecer como motivo lançado pelo entregador durante a rota e, isoladamente, não confirma extravio definitivo: acompanhe as leituras seguintes.

## Entrega da iMile no Brasil e regras da alfândega

A iMile mantém página oficial em português do Brasil ([imile.com/BR-pt](https://www.imile.com/BR-pt)), apresentando soluções de entrega transfronteiriça, entrega expressa doméstica, logística reversa e pontos de coleta e entrega (PUDO). No entanto, as páginas públicas da iMile para o Brasil não especificam quantas tentativas de entrega são feitas, qual o prazo de guarda em pontos de retirada nem se o entregador deixa aviso impresso.

Nas perguntas frequentes gerais da empresa, a iMile informa que solicitações de reagendamento podem ser feitas pela central de atendimento, alterações de endereço devem ser pedidas antes de o pacote entrar na fase final de entrega (pelo suporte ou pelo assistente virtual), e a comprovação de entrega pode incluir foto do pacote no local e assinatura digital quando aplicável. Na página oficial de rastreio há ainda a opção de visualizar o comprovante mediante confirmação do usuário autorizado. Para situações de destinatário ausente, veja [entrega não realizada](guide:missed-delivery).

**Alfândega e Programa Remessa Conforme no Brasil.** Em compras internacionais enviadas de fora do Brasil, a cobrança de tributos segue as regras do [Programa Remessa Conforme da Receita Federal](https://www.gov.br/receitafederal/pt-br/assuntos/aduana-e-comercio-exterior/manuais/remessas-postal-e-expressa/programa-remessa-conforme-o-que-e-como-funciona):

- **Lojas certificadas no Remessa Conforme:** o pagamento ocorre no momento da compra. Em remessas de até US$ 50 (incluindo produto, frete e seguro), há cobrança do Imposto de Importação conforme a faixa vigente e ICMS estadual de 17% a 20% conforme o estado de destino; acima de US$ 50, aplica-se alíquota de 60% de Imposto de Importação com desconto de US$ 30 no valor do tributo, além do ICMS estadual.
- **Lojas não certificadas:** incide 60% de Imposto de Importação sem o desconto de US$ 30 (mesmo até US$ 50), mais ICMS de 17% a 20%, cobrados na chegada da encomenda ao Brasil.

Para mais detalhes sobre retenções alfandegárias, consulte [encomendas retidas na alfândega](guide:customs).

## Encomenda iMile parada, atrasada ou não recebida

**Somente `Order submitted, pending to receive.`** A loja gerou a etiqueta, mas ainda não entregou o pacote à iMile. Confirme com o vendedor se a postagem já ocorreu.

**Dias sem atualização.** Entre o voo internacional, a fiscalização aduaneira e a chegada ao centro local, é normal passar vários dias sem novas leituras. Veja [por que o rastreamento para de atualizar](guide:tracking-not-updating) e [como rastrear encomendas da China](guide:tracking-from-china).

**Link de rastreio não abre.** As perguntas frequentes da iMile recomendam atualizar a página, limpar cookies ou testar outro navegador antes de contatar o suporte com o número do pedido.

**Consta como entregue, mas você não recebeu.** Verifique primeiro com moradores, vizinhos ou portaria e procure o comprovante de entrega na página da iMile; depois acione a loja e a iMile. Veja [entregue, mas não recebido](guide:delivered-not-received).

**Extravio, avaria ou devolução.** A iMile não publica um fluxo de indenização direta ao destinatário no Brasil: quem tem o contrato de frete é a loja, que deve providenciar reembolso ou reenvio. Veja [encomenda perdida](guide:lost-parcel), [encomenda danificada](guide:damaged-parcel) e [devolvida ao remetente](guide:returned-to-sender). Se uma loja ou transportadora cadastrada não resolver o problema, consumidores no Brasil com conta gov.br prata ou ouro podem registrar reclamação gratuita no [Consumidor.gov.br](https://www.consumidor.gov.br/pages/conteudo/publico/1), com prazo de resposta de até 10 dias.

## Atendimento e contato da iMile no Brasil

As páginas brasileiras da iMile ([Suporte iMile](https://www.imile.com/BR-pt/CustomerService/CustomerSupport), [Escritórios iMile](https://www.imile.com/BR-pt/CustomerService/OfficeLocations) e [Aviso Legal](https://www.imile.com/BR-pt/LegalNotice)) não divulgam telefone 0800 ou endereço de e-mail específico para o Brasil. Os canais disponíveis no site são:

- **iMile Chat:** o botão de chat no canto inferior direito de `imile.com` abre o assistente virtual, com atendimento em português, inglês, espanhol, italiano, alemão, húngaro e árabe.
- **Formulário de suporte ao cliente:** disponível na página de suporte da iMile para dúvidas sobre envios (o formulário solicita também nome da empresa e volume diário de pedidos).
- **Escritórios regionais:** a página de escritórios reúne telefones e e-mails de mercados selecionados (como a Austrália); para pedidos de compras online no Brasil, o contato mais rápido continua sendo a loja ou o marketplace onde você comprou.

> Desconfie de mensagens de SMS ou WhatsApp pedindo pagamento imediato por links desconhecidos para liberar uma encomenda iMile na alfândega. Leia [golpes por SMS de entrega](guide:delivery-scam-texts) antes de efetuar qualquer pagamento.

## Rastrear uma encomenda iMile com o Peek

O [Peek](/) identifica automaticamente códigos iniciados por `IM0601` como iMile ("Transportadora detectada"). Para outros códigos `IM` de 14 dígitos, números de 14 dígitos iniciados por `49` e códigos de 13 dígitos começando por `6` ou `3`, o formato é compartilhado com outras transportadoras — cole o número mesmo assim, pois o Peek acompanha mais de 4.500 transportadoras. Ao adicionar o código, o Peek consulta primeiro as transportadoras compatíveis (incluindo a iMile entre as primeiras para `49` e códigos de data iniciados por `6`) e exibe "iMile está com esta encomenda." assim que a iMile confirma o pacote. Se nenhuma responder ainda, toque em "Trocar" ao adicionar ou em "Trocar transportadora" na tela do pacote e escolha iMile. Links de `imile.com` contendo `/track?waybillNo=` também são reconhecidos na hora.

O Peek consulta o rastreamento da iMile em inglês e exibe o status traduzido ("Etiqueta criada", "Recebida pela transportadora", "Em trânsito", "Na alfândega", "Saiu para entrega", "Tentativa de entrega", "Pronta para retirada", "Entregue", "Devolvida ao remetente", "Precisa de atenção"), o histórico com a cidade ou centro de triagem no mapa e o país de destino, convertendo os horários locais para o fuso do seu dispositivo. Quando a Cainiao transporta o pacote da China até a iMile, o Peek reúne as etapas iniciais da Cainiao antes do histórico da iMile.

O Peek mostra a data prevista pela linha `Shipment is scheduled to` mais recente até haver tentativa falha, imprevisto, chegada a ponto de retirada, entrega, devolução ou nova leitura posterior àquela data. O Peek não exibe peso, nome do serviço, nome ou endereço do destinatário, comprovante fotográfico de entrega, dados de contato nem detalhes de pagamento, e remove telefones do entregador e da central das descrições.

Enquanto você mantém a encomenda aberta no Peek ou no dia da entrega com alertas ativados, o Peek verifica atualizações até a cada 10 minutos — e até a cada 2 minutos a partir das 6h no horário local no dia em que a iMile registra "Saiu para entrega", enquanto o rastreador próprio da iMile responde; fora dessas condições, verifica a cada hora. Quando apenas serviços de rastreamento respondem, a checagem é de hora em hora, a cada 3 horas após um dia sem novidades e até a cada 15 minutos no dia da entrega enquanto o pacote estiver aberto ou com alertas ligados. Após dois dias sem novidades (ou 12 horas em "Etiqueta criada" ou "Recebida pela transportadora"), passa a checar a cada hora (exceto enquanto o pacote está aberto ou previsto para hoje), e a cada 6 horas após sete dias parado em "Etiqueta criada". Quando a leitura mais recente completa quatro dias em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito", o Peek exibe o alerta com a data dessa leitura (por exemplo, "Sem atualizações desde seg., 12 de out."), junto da mensagem "É comum uma encomenda ficar sem atualizações em voos longos, na alfândega ou no fim de semana. O Peek continua consultando iMile." e do link "Pedir ajuda a iMile". Após três semanas sem novidades, aparece "Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, fale com o vendedor. O Peek continua consultando iMile."

Você pode escolher alertas para "Cada movimentação", "Etapas importantes" ou "Só a entrega". O Peek apenas acompanha o rastreamento e não pode reagendar entregas nem contatar a iMile ou a loja em seu nome.

## Perguntas frequentes sobre o rastreamento iMile

### Como saber onde está minha encomenda iMile?

A última leitura mostra a etapa atual, e o nome entre `【】` indica o aeroporto ou centro onde o pacote foi bipado. `Our delivery associate is out for delivery.` indica tentativa de entrega no dia; um motivo do motorista logo em seguida sinaliza que a tentativa não foi concluída.

### O que significa `Order Submited.` no rastreamento da iMile?

Significa que a loja gerou o pedido de envio, mas a iMile ainda não recebeu o pacote fisicamente (o erro de grafia é do próprio sistema da iMile). Quando a coleta ocorre, surge `Your order has been picked up.`

### A iMile é confiável?

Sim. A iMile é uma transportadora de e-commerce fundada em Dubai em 2017, com operação própria de última milha e sites oficiais em vários países, incluindo o Brasil (`imile.com/BR-pt`).

### Como falar com o atendimento da iMile no Brasil?

As páginas brasileiras da iMile não listam telefone ou e-mail local; o atendimento direto no site é feito pelo botão do iMile Chat (em português) ou pelo [formulário de suporte](https://www.imile.com/BR-pt/CustomerService/CustomerSupport). Para reembolso, atraso ou reenvio, fale primeiro com a loja onde comprou.

:::sources
- [iMile Brasil: Página inicial](https://www.imile.com/BR-pt) – entregas transfronteiriças, entrega expressa doméstica, logística reversa e soluções no Brasil
- [iMile Brasil: Rastrear encomenda](https://www.imile.com/BR-pt/track) – página de rastreamento em português
- [iMile Brasil: Perguntas frequentes](https://www.imile.com/BR-pt/CustomerService/FAQ) – dúvidas de rastreio e entrega
- [iMile Brasil: Suporte ao cliente](https://www.imile.com/BR-pt/CustomerService/CustomerSupport) – formulário de suporte e campos obrigatórios
- [iMile Brasil: Escritórios](https://www.imile.com/BR-pt/CustomerService/OfficeLocations) – ausência de telefone/e-mail local listado para o Brasil
- [iMile Brasil: Aviso Legal](https://www.imile.com/BR-pt/LegalNotice) – dados legais da página brasileira
- [iMile: Home](https://www.imile.com/en) – fundada em Dubai em 2017, entregas transfronteiriças e PUDOs
- [iMile: Track Your Shipment](https://www.imile.com/en/track) – página em inglês, resultado vazio e comprovante de entrega
- [iMile: FAQs](https://www.imile.com/en/CustomerService/FAQ) – reagendamento, alteração de endereço, Online Bot, comprovante e erro no link
- [Receita Federal: Programa Remessa Conforme](https://www.gov.br/receitafederal/pt-br/assuntos/aduana-e-comercio-exterior/manuais/remessas-postal-e-expressa/programa-remessa-conforme-o-que-e-como-funciona) – regras de tributação para lojas certificadas e não certificadas
- [Consumidor.gov.br](https://www.consumidor.gov.br/pages/conteudo/publico/1) – mediação gratuita para consumidores com conta gov.br prata ou ouro e resposta em até 10 dias
- [Universal Parcel Scraper: iMile](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/imile) – formatos de código, mapeamento de status, integração com Cainiao, campos exibidos/ocultados e ritmo de atualização
:::
