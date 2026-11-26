---
title: Rastreamento Delhivery: códigos AWB, status e atendimento
description: Rastreie encomendas Delhivery na Índia e no exterior: formato do código AWB, significado de RTO e outros status, entregas não realizadas, reclamações e atendimento.
slug: rastreamento-delhivery
published: 2026-11-25
updated: 2026-11-25
---

A Delhivery é uma empresa de logística indiana que se define como a maior provedora logística totalmente integrada da Índia. Ela entrega pedidos online de marketplaces e lojas para mais de 18.850 códigos postais e despacha da Índia para mais de 220 países e territórios. Rastreie uma encomenda na [página de rastreamento da Delhivery](https://www.delhivery.com/tracking) com o número AWB, o ID do pedido, um LRN ou seu número de celular, ou cole o AWB na caixa acima. O AWB vem do remetente: em compras online, procure no SMS ou e-mail de envio da loja, ou pergunte ao vendedor.

## Código de rastreamento Delhivery (AWB): formato

A Delhivery chama seu código de rastreamento de AWB (*airway bill number*). O rastreador também aceita um LRN. Estes são os formatos que o Peek reconhece como AWB da Delhivery, com dígitos inventados:

| Código (inventado) | O que é |
| --- | --- |
| `1482910573642` | 13 dígitos: AWB da Delhivery. Várias outras transportadoras, de DPD France a Korea Post, também usam 13 dígitos. |
| `14829105736425` | 14 dígitos: também é um AWB da Delhivery. DPD, SEUR, BRT, Xpressbees e outras também usam 14 dígitos. |

O formato por si só não identifica a Delhivery, então confirme o nome da transportadora na mensagem da loja. ID do pedido, LRN e celular funcionam no site da Delhivery, mas não no Peek, que precisa do AWB. Na página da Delhivery, selecione a aba "AWB" para o AWB ou "Order Id" para o número do pedido da loja; fazendo login com seu celular, você vê todos os envios que a Delhivery está entregando para você. Sem nenhum número? Veja [como encontrar seu código de rastreamento](guide:find-tracking-number).

## Status de rastreamento da Delhivery explicados

O rastreamento da Delhivery tem uma barra de etapas e a lista de eventos ocorridos com a cidade. Estes são os textos conhecidos pelo Peek, aproximadamente na ordem em que a encomenda passa por eles:

| A Delhivery mostra | O que significa para você |
| --- | --- |
| `WAITING_PICKUP` | Agendado, mas ainda não coletado (aparece como `Waiting pickup` antes da primeira leitura). |
| `Picked Up` | A Delhivery coletou a remessa com o remetente. |
| `PROD_REPLACED` | Em um pedido de troca, o entregador recolheu o item substituído. |
| `In Transit`, `IN_TRANSIT`, `On the Way` | Em trânsito pela rede da Delhivery. `IN_TRANSIT` é o mesmo status em forma de código. |
| `Dispatched` | Registro de saída para entrega: deve chegar em breve. |
| `Out for Delivery` | Saiu para entrega, como aparece na barra de progresso da Delhivery. |
| `Undelivered` | Não entregue e retornando ao vendedor. Em cargas de frete, a Delhivery dá esse nome à etapa "On the Way" mantendo a data original dela, que portanto não corresponde necessariamente a uma tentativa de entrega. |
| `Delivered` | Entregue. Em pacote em devolução, significa entregue de volta ao vendedor. |
| `REACHED_SEL_CITY` | Encomenda em devolução chegou à cidade do vendedor. |
| `RTO` | Retornado à origem (*Returned to origin*): o pacote voltou e chegou ao vendedor. |
| `DELIVERED_SELLER` | Devolução entregue ao vendedor. |
| `DTO` | Devolução do cliente (pacote que você enviou de volta) chegou ao vendedor. |
| `LOST` | A Delhivery marcou a remessa como extraviada. |
| `CANCELLED`, `Canceled` | O envio ou a coleta de devolução foi cancelado. |

`Picked Up`, `On the Way` e `Out for Delivery` vêm de envios de carga, onde a Delhivery data cada etapa em vez de cada leitura. Veja mais em [o que significam os status de rastreamento](guide:tracking-statuses).

## Entrega Delhivery na Índia: ligações, ausências e endereço

**Quem entrega.** A rede própria da Delhivery: mais de 4.500 centros diretos de entrega, presença em todos os estados e 99,5% dos códigos postais cobertos, incluindo o Nordeste da Índia e Jammu e Caxemira. Segundo a empresa, essa rede opera 24 horas por dia, 7 dias por semana, 365 dias por ano.

**Ligações e mensagens.** Os termos da Delhivery dizem que ela ou parceiros podem ligar, enviar SMS ou mandar mensagem e ligar pelo WhatsApp para combinar a entrega, checar disponibilidade e resolver problemas, e que essas chamadas podem ser gravadas. Lojas também podem autorizar a Delhivery a confirmar pedidos por ligação automatizada com IA ou WhatsApp.

**Entrega não realizada.** Em envios contratados pelo Delhivery Direct (serviço pessoal de encomendas), os termos cancelam a entrega após 3 tentativas não realizadas por ausência do destinatário; o remetente pode solicitar devolução pagando o frete. Para pedidos de lojas, vale o acordo comercial da loja com a Delhivery: pergunte à loja. Para aumentar entregas bem-sucedidas, a Delhivery oferece às lojas gestão de NDR via WhatsApp e "Smart NDR" (ligações de IA, confirmações no WhatsApp e novas tentativas inteligentes). Portanto, ligações ou mensagens no WhatsApp sobre nova tentativa podem ser legítimas, mas a Delhivery nunca pede OTP nem senhas para confirmar endereço. Veja [entrega não realizada](guide:missed-delivery).

**Onde entrega.** Nos termos do serviço Direct, a Delhivery entrega no endereço informado — não necessariamente em mãos ao destinatário — e nunca em caixas postais; assinatura ou documento oficial pode ser exigido. É possível pedir outro local ou retirada em ponto aprovado se a Delhivery concordar, podendo recusar alteração de código postal. Fins de semana e feriados não contam no prazo do serviço Direct.

## Encomendas Delhivery enviadas ao exterior

**Prazos.** Para lojas exportando pelo DLV Saver, a Delhivery estima 5 a 7 dias para o Reino Unido e 8 a 12 dias para os EUA (o serviço cobre EUA, Reino Unido, Canadá e Austrália). O DLV Premium, para mais de 220 países e territórios, leva 3 a 6 dias (Express) ou 6 a 10 dias (Deferred Express).

**Quem entrega no destino.** A Delhivery cita FedEx e Aramex entre seus parceiros estratégicos no exterior, sem especificar qual transporta seu pacote. O Peek acompanha o AWB no rastreamento da Delhivery; se a loja passar um segundo código de outra transportadora para a etapa final, cole-o também.

**Alfândega.** A Delhivery promete às lojas transparência total sobre impostos e taxas, sem especificar ali quem paga: confirme com a loja se estavam inclusos no preço. Na Irlanda e no restante do território aduaneiro da UE (que exclui locais como Helgoland, Büsingen, Livigno, Ceuta e Melilla), compras online de fora da UE pagam direito aduaneiro de €3 por tipo de produto em pacotes de até €150 desde 1º de julho de 2026, e taxa de manuseio da União de €2 por tipo de produto, qualquer que seja o valor, desde 1º de novembro de 2026. Fora da UE, valem as regras locais: veja [encomendas retidas na alfândega](guide:customs).

## Rastreamento Delhivery parado, extravio ou devolução

Para reembolso ou reenvio, fale com a loja, pois ela contratou a Delhivery.

**Sem atualização.** Se o Peek ainda mostrar "Etiqueta criada", a loja agendou a Delhivery mas ainda não entregou o pacote: pergunte à loja. Se o pacote já está em trânsito mas travou, entre no site ou aplicativo da Delhivery com seu celular para ver todos os envios destinados a você e abrir uma solicitação. Veja [por que o rastreamento para de atualizar](guide:tracking-not-updating).

**Extravio ou dano.** `LOST` indica que a Delhivery marcou como extraviado: avise a loja. Em envios Delhivery Direct, reclamações de extravio ou dano devem ser abertas em até 3 dias via chamado no aplicativo; para dano, a Delhivery exige 3 fotos do pacote e da embalagem com descrição e sugere filmar a abertura. Pelos termos, o prazo de análise é de 30 a 45 dias úteis e, sem o seguro pago Delhivery Protect, o teto é de ₹2.000, com limites menores por categoria (como ₹500 para livros e documentos ou ₹1.000 para eletrônicos). Não há indenização por atraso. Veja [encomendas extraviadas](guide:lost-parcel) e [encomendas danificadas](guide:damaged-parcel).

**Entregue, mas não chegou.** Verifique com moradores da casa ou a portaria do prédio. Pelos termos do serviço Direct, entrega errada deve ser reportada em até 3 dias da entrega; se você autorizou entrega em caixa de correio ou armário, a Delhivery fornece comprovante mediante solicitação, mas não responde por perda ou dano. Veja [encomenda entregue mas não recebida](guide:delivered-not-received).

**Devolvida.** `RTO` significa que o pacote voltou ao vendedor: peça reembolso ou reenvio à loja. Veja [devolvida ao remetente](guide:returned-to-sender).

## Atendimento Delhivery e alertas contra golpes

O Peek não pode contatar a Delhivery nem a loja por você. O que a Delhivery informa:

- **Atendimento:** a Delhivery afirma que seu atendimento só funciona pelo site ou aplicativo. Entre na [página de ajuda](https://www.delhivery.com/support) com seu celular e código único (OTP) para abrir um chamado, ou use o aplicativo Delhivery.
- **Reclamações:** os assuntos de envio incluem "Report Issues", "Grievance Officer" e "Report on-field staff issues", além de "Destination and Contact details" e "Returns".
- **Telefone:** a Delhivery alerta que outros canais ou números de telefone encontrados na internet podem ser fraudulentos.
- **Redes sociais:** a Delhivery usa apenas suas contas oficiais no Facebook, X e Instagram para atendimento; desconfie de qualquer outro perfil.

> A Delhivery alerta para SMS falsos alegando entrega não realizada com links para sites fraudulentos. Ela não pede OTP nem credenciais para confirmar endereço, nem PIN ou OTP para pagamento; não pague por links fora das contas oficiais. Seus e-mails vêm apenas de delhivery.com. Perdeu dinheiro? Ligue para a linha nacional 1930 e registre queixa em cybercrime.gov.in; denuncie chamadas e SMS de golpe em sancharsaathi.gov.in em até 3 dias. Veja [golpes de SMS de entrega](guide:delivery-scam-texts).

## Rastrear uma encomenda Delhivery com o Peek

Cole o AWB no [Peek](/). Como várias transportadoras usam 13 e 14 dígitos, o Peek não mostra "Transportadora detectada": exibe "Consultando serviços de rastreamento…" enquanto consulta as transportadoras com esse formato, começando pela Delhivery quando você está na Índia (região do celular ou país de acesso na web). De qualquer outro lugar, o Peek consulta a Delhivery por último, depois que as outras transportadoras e os serviços de rastreamento não encontraram o código. Quando a Delhivery reconhece o código, aparece "Delhivery está com esta encomenda." Se nenhuma empresa reconhecer ainda, adicione mesmo assim ou toque em "Trocar" e escolha Delhivery: o Peek acompanha mais de 4.500 transportadoras. O Peek não consulta a Delhivery para códigos iniciados por 0 ou terminados em 7, 8 ou 9: escolha a Delhivery você mesmo. Para um número que não tenha 13 ou 14 dígitos, escolher Delhivery manualmente retorna na primeira verificação: "Esta transportadora não emite códigos de rastreamento como este. Confira o código ou escolha outra transportadora."

Associada à Delhivery, a encomenda mostra "Aguardando Delhivery" até o primeiro registro. O Peek lê o rastreamento público da Delhivery sem precisar de conta: cada movimentação no texto da Delhivery com sua cidade, sem dados do destinatário nem previsão de entrega. Os horários são lidos no horário da Índia e exibidos no fuso horário do seu aparelho; em peektracker.com e nos aplicativos iPhone e Android, o histórico termina com "Horários no seu fuso horário:" e o nome dele. Etapas futuras da barra de progresso são ignoradas, e datas de carga sem ano são inferidas pelo dia da semana.

O Peek agrupa as linhas em suas próprias etapas: `Picked Up` aparece como "Recebida pela transportadora", `In Transit` como "Em trânsito", `Dispatched` como "Saiu para entrega", `RTO` como "Devolvida ao remetente" e `Undelivered`, `LOST` ou cancelamento como "Precisa de atenção". Um pacote entregue de volta ao vendedor mostra "Devolvida ao remetente", não "Entregue".

Enquanto o pacote está aberto, ou no dia da entrega com alertas ligados, o Peek verifica até a cada 10 minutos — ou a cada 2 minutos a partir das 6h locais no dia em que a Delhivery registra saída para entrega — enquanto o rastreador da própria Delhivery responde (por serviços de rastreamento, a cada hora, a cada 3 horas após um dia sem novidades e até a cada 15 minutos no dia da entrega enquanto o pacote estiver aberto ou com alertas ligados); nos demais horários, e após dois dias sem novidades (12 horas enquanto ainda mostrar "Etiqueta criada" ou "Recebida pela transportadora"), verifica a cada hora. Alertas: "Cada movimentação", "Etapas importantes" ou "Só a entrega".

## Perguntas frequentes sobre o rastreamento Delhivery

### Como rastreio uma encomenda da Delhivery?

Digite o AWB em delhivery.com/tracking, ou pesquise lá por Order ID, LRN ou número de celular com OTP. Você também pode colar o AWB no Peek para acompanhar até a entrega.

### O que é o código AWB na Delhivery?

É o *airway bill number*, o código de rastreamento da Delhivery, com 13 ou 14 dígitos. Ele aparece no SMS ou e-mail de envio da loja.

### O que significa RTO no rastreamento da Delhivery?

*Returned to origin* (devolvido à origem): a encomenda voltou e chegou ao vendedor. Peça reembolso ou reenvio à loja.

### Qual é o telefone de atendimento da Delhivery?

A Delhivery informa que o atendimento está disponível somente pelo site ou aplicativo: faça login com seu celular e abra uma solicitação. Ela alerta que outros números encontrados online podem ser golpes.

:::sources
- [Delhivery: Track your order](https://www.delhivery.com/tracking) – rastreamento por celular, AWB, ID do pedido ou LRN, alertas de fraude e atendimento apenas por site ou app
- [Delhivery: Support](https://www.delhivery.com/support) – rastreamento por AWB ou LRN, login com celular, abertura de solicitação e assuntos incluindo Grievance Officer
- [Delhivery: About us](https://www.delhivery.com/about-us) – maior provedora logística integrada, códigos postais, centros de entrega e operação 24/7
- [Delhivery: Home](https://www.delhivery.com/) – mais de 220 países e parceiros estratégicos FedEx e Aramex
- [Delhivery: Express Parcel](https://www.delhivery.com/services/express-parcel) – cobertura de 99,5% dos códigos postais, marketplaces, confirmações por WhatsApp e IA, gestão NDR e novas tentativas inteligentes
- [Delhivery: International](https://www.delhivery.com/services/international) – prazos e rotas DLV Saver e DLV Premium e tributos
- [Delhivery: Personal Courier](https://www.delhivery.com/solutions/personal-courier) – reservas Delhivery Direct
- [Delhivery: Terms and Conditions](https://www.delhivery.com/terms-and-conditions) – ligações, SMS e WhatsApp sobre entregas
- [Delhivery: TnC (Direct National Courier)](https://www.delhivery.com/tnc-direct) – código AWB, tentativas de entrega não realizadas, endereço de entrega, reclamações e indenização
- [Delhivery: Fraud Disclaimer](https://www.delhivery.com/fraud-disclaimer) – canais oficiais, SMS falsos, OTP e pagamentos, 1930 e cybercrime.gov.in
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – direito aduaneiro de €3 por tipo de produto até €150 e taxa de manuseio da União de €2
- [European Commission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – €2 por tipo de produto qualquer que seja o valor
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – textos da Delhivery, o que o Peek lê e mostra, formatos de código
:::
