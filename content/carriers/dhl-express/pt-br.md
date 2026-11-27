---
title: Rastreamento DHL Express: número de conhecimento e status
description: Rastreie encomendas DHL Express: onde achar o código de 10 dígitos, significado dos status, taxas alfandegárias, entregas não realizadas e extravios.
slug: rastreamento-dhl-express
published: 2026-11-24
updated: 2026-11-24
---

Cole um número de conhecimento aéreo (waybill) da DHL Express, ou um link de rastreamento do MyDHL+, na caixa acima para acompanhar a encomenda. O rastreamento oficial da DHL fica no site MyDHL+ e no aplicativo móvel da DHL Express.

## Código de rastreamento da DHL Express: onde encontrar o waybill

A DHL Express chama seu código de rastreamento de waybill number (número de conhecimento). Procure no e-mail de envio da loja ou na página do seu pedido; links do MyDHL+ também trazem o número logo após `id=`. Ainda não achou? Veja [onde encontrar um código de rastreamento](guide:find-tracking-number).

| Formato (fictício) | O que é |
| --- | --- |
| `4829173602` | O waybill: 10 dígitos que rastreiam toda a remessa. O último dígito é verificador. |
| `JD014600004829173605` | Número de volume (piece number): `JD01` e 16 dígitos, impresso como `(J)JD…` sob o código de barras inferior da etiqueta. Cada volume de uma remessa tem o seu próprio e remete ao waybill. |

Códigos de 10 dígitos também combinam com [Mondial Relay](carrier:mondial-relay), [Relais Colis](carrier:relais-colis), Estafeta, Packeta, [DHL eCommerce Iberia](carrier:dhl-ecommerce-es) e [DHL Paket](carrier:dhl), portanto confira qual transportadora aparece no e-mail da loja.

A DHL reutiliza números de waybill: um código pode retornar leituras de uma remessa antiga, que o Peek ignora automaticamente.

## O que significam os status de rastreamento da DHL Express

Estas são as mensagens que o Peek lê da DHL Express, no texto original em inglês da DHL, aproximadamente na ordem em que surgem, junto com o status exibido pelo Peek. Muitas terminam com o nome da unidade, como `Processed at` seguido da cidade e do país.

| Leitura da DHL Express | O Peek mostra | O que significa |
| --- | --- | --- |
| `Shipment information received`, `Supplementary information has been received and merged with applicable shipment data` | Etiqueta criada | O remetente enviou os dados da remessa à DHL. |
| `Shipment picked up`, `Shipment accepted` | Recebida pela transportadora | A DHL Express já está com o pacote. |
| `Processed at`, `Arrived at DHL Sort Facility`, `Shipment has departed from a DHL facility`, `Shipment is in transit to destination` | Em trânsito | Em deslocamento pela rede da DHL. |
| `Scheduled to depart on the next planned movement` | Em trânsito | Aguardando em uma unidade o próximo caminhão ou voo programado. |
| `Customs clearance status updated. Note - The Customs clearance process` …, `Processed for clearance at`, `Clearance event` | Na alfândega | Passando pela alfândega (veja abaixo). |
| `On hold awaiting for payment of shipment related fees` | Na alfândega | Há tributos ou taxas a pagar, e a DHL retém a encomenda até a quitação. Não há problema com a remessa em si. |
| `Payment is received and recorded for shipment related fees` | Na alfândega | O pagamento das taxas foi registrado; o pacote segue na alfândega até a liberação final. |
| `Clearance processing complete at` | Em trânsito | A alfândega concluiu a liberação e o pacote voltou a circular. |
| `Arrived at DHL Delivery Facility` | Em trânsito | Chegou à unidade da DHL responsável pela entrega final. |
| `Shipment is out with courier for delivery` | Saiu para entrega | O entregador está levando o pacote até você. |
| `Delivery attempted but no response at Consignee address`, `Delivery attempt could not be completed` | Tentativa de entrega | A entrega não pôde ser concluída. "Consignee" é você, o destinatário. |
| `Shipment is scheduled for delivery` | Em trânsito | Nova entrega agendada para uma rota posterior, por exemplo após uma tentativa sem sucesso. |
| `Awaiting collection by the consignee` | Pronta para retirada | Guardada em uma unidade da DHL para você retirar. |
| `Delivered` | Entregue | Encomenda entregue. |
| `Shipment is on hold` | Precisa de atenção | A DHL suspendeu temporariamente a movimentação da remessa. |
| `Shipment arrived at incorrect facility. It will be sent to the correct destination` | Precisa de atenção | Foi triada para a unidade errada e será encaminhada ao destino correto, com atraso. |
| `Returned to shipper` | Devolvida ao remetente | A caminho de volta para o remetente. |

## Entrega sem sucesso da DHL Express

Após uma tentativa sem sucesso, o rastreamento exibe `Delivery attempted but no response at Consignee address` ou `Delivery attempt could not be completed`. Um `Shipment is scheduled for delivery` posterior indica que uma nova tentativa foi agendada; `Awaiting collection by the consignee` indica que o pacote aguarda retirada em uma unidade da DHL. Para reagendar a entrega, entre em contato com a DHL Express pelo site ou aplicativo: o Peek não altera entregas. Veja mais em [entregas sem sucesso](guide:missed-delivery) e [encomendas não retiradas](guide:uncollected-parcel).

## Encomenda da DHL Express retida na alfândega

Uma encomenda vinda de fora do Reino Unido, ou de fora da União Europeia para a Irlanda, pode pagar encargos de importação. O rastreamento mostra `Customs clearance status updated…`, `Processed for clearance at` ou `Clearance event` durante a análise aduaneira, `Payment is received and recorded for shipment related fees` após o pagamento e `Clearance processing complete at` quando a alfândega libera o pacote.

- **Reino Unido:** segundo o GOV.UK, a transportadora envia uma cobrança de IVA, tarifas alfandegárias ou encargos de entrega e normalmente guarda o pacote por cerca de 3 semanas. Sem pagamento, ele volta ao remetente.
- **Irlanda:** desde 1º de julho de 2026, a maioria das encomendas de até € 150 vindas de fora da UE paga € 3 de imposto alfandegário por categoria de produto. Se a loja não cobrou no checkout, a Revenue informa que a transportadora cobrará antes da entrega e pode somar sua própria taxa administrativa; confirme esse valor com ela.

Saiba mais em [encomendas paradas na alfândega](guide:customs).

## Encomenda DHL Express atrasada, extraviada ou marcada como entregue

Se o rastreamento parou de atualizar ou mostra `Shipment is on hold`, consulte [rastreamento que não atualiza](guide:tracking-not-updating).

Se você mora na Grã-Bretanha ou na Irlanda e comprou de uma loja, a loja responde por fazer o pedido chegar até você, mesmo quando a DHL Express faz o transporte:

- **Inglaterra, Escócia e País de Gales:** segundo a Citizens Advice, cabe ao vendedor cobrar a transportadora, não a você. Se o item não chegar em até 30 dias após a compra, você pode cancelar e pedir reembolso. Se for deixado em local não autorizado por você e sumir, o vendedor deve resolver.
- **Irlanda:** segundo a CCPC, sem data combinada, o pedido deve chegar em até 30 dias da compra. Caso contrário, combine uma nova data com a empresa; se ela também não cumprir, você tem direito a reembolso integral. Um pacote danificado ou extraviado em trânsito deve ser substituído, e a empresa continua responsável até você — ou alguém indicado por você — receber o pacote em mãos.

Marcado como `Delivered`, mas não chegou? A página de rastreamento da DHL pode exibir a assinatura, que o Peek não armazena. Confira o rastreamento no site da DHL e depois fale com a loja. Veja mais em [marcado como entregue mas não recebido](guide:delivered-not-received) e [encomendas extraviadas](guide:lost-parcel).

## Como rastrear encomendas da DHL Express com o Peek

O [Peek](/) identifica um waybill de 10 dígitos que passa no dígito verificador da DHL Express, coloca a DHL Express em primeiro lugar e consulta ao mesmo tempo outras transportadoras compatíveis, como ACS Courier, Geniki Taxydromiki (estas duas apenas para dígitos sem espaços nem hifens) e DHL eCommerce Iberia, exibindo quem tiver a encomenda. ACS e Geniki compartilham o mesmo dígito verificador; se uma delas também reconhecer o código, você escolhe. Um número de 10 dígitos que falha na verificação não é enviado automaticamente à DHL Express: selecione-a com "Trocar". Números de volume `JD01` e etiquetas `JJD0146` (`JJD014648291736058419`) são identificados de imediato como DHL Express e vinculados ao waybill principal. Links de mydhl.express.dhl também funcionam. Para links de dhl.com, cole diretamente o número do waybill; se o pacote cair em "DHL", troque para "DHL Express" usando "Trocar".

O Peek consulta a DHL Express pelo serviço usado pelo aplicativo móvel dela ou, como alternativa, abre a página de rastreamento do dhl.com em um navegador no servidor (mais lento e sujeito a bloqueio), com serviços de rastreamento preenchendo quando necessário. Ele verifica até a cada 10 minutos — ou a cada 2 a 5 minutos (5 minutos ao abrir o dhl.com no navegador) a partir das 6h (horário local) do dia em que a DHL registra a saída para entrega — enquanto o rastreamento próprio da DHL responde (por meio de serviços de rastreamento, até a cada 15 minutos nesse dia, enquanto o pacote estiver aberto ou com alertas ligados), e pode notificar em cada leitura, só em etapas importantes ou só no dia da entrega. Após dois dias sem novidades (12 horas enquanto ainda mostra "Etiqueta criada" ou "Recebida pela transportadora"), o Peek verifica de hora em hora.

Ele exibe o status, cada leitura com a unidade no mapa e o país de destino. Os horários das leituras seguem o fuso da cidade da unidade ou, quando insuficiente, o das leituras vizinhas. Os textos permanecem no inglês da DHL, exceto `Delivered`, `Shipment picked up` e `Shipment information received`, exibidos no texto do próprio Peek. O Peek omite a assinatura, descarta leituras antigas de waybills reciclados e não mostra peso nem Service Point. Ele exibe a data prevista da DHL quando lê a página do dhl.com; o serviço do app da DHL não informa previsão. O Peek não paga tributos nem altera entregas.

## Perguntas sobre o rastreamento da DHL Express

### O número de waybill da DHL é o mesmo que o código de rastreamento?

Sim. Na DHL Express, o número de waybill de 10 dígitos rastreia toda a remessa; o número de volume `JD01` identifica um único pacote e leva ao waybill.

### A DHL é a mesma coisa que a DHL Express?

Não no Peek: a DHL Express é uma transportadora separada da [DHL Paket](carrier:dhl) e das divisões DHL eCommerce, cada uma com seus próprios códigos e sistemas de rastreamento. Um pacote sem waybill de 10 dígitos nem código `JD01` normalmente pertence a uma dessas outras divisões.

### Por que meu número de waybill da DHL aparece como inválido?

Muitas vezes o código pertence a outra divisão da DHL: waybills da DHL Express têm exatamente 10 dígitos e o último é verificador, de modo que um erro de digitação costuma invalidá-lo. Um waybill recém-emitido também pode exibir apenas `Shipment information received` até a coleta pela DHL.

### Posso rastrear a DHL Express sem número de waybill?

Não no Peek: é necessário ter o waybill de 10 dígitos ou um código de volume `JD01` da etiqueta. Peça o waybill à loja ou ao remetente.

### Um SMS da DHL Express pedindo pagamento é golpe?

Pode ser. Segundo a Citizens Advice, uma mensagem pedindo pagamento para liberar um pacote ou reagendar entrega geralmente é golpe. Não clique no link: consulte o waybill diretamente no site da DHL ou no Peek. No Reino Unido, encaminhe o SMS gratuitamente para 7726. Nos Estados Unidos, a FTC orienta procurar a transportadora por um site ou telefone que você sabe ser verdadeiro. Veja mais em [golpes de entrega por SMS](guide:delivery-scam-texts).

:::sources
- [Citizens Advice: If something you ordered hasn't arrived](https://www.citizensadvice.org.uk/consumer/somethings-gone-wrong-with-a-purchase/if-something-you-ordered-hasnt-been-delivered/) – responsabilidade do vendedor, 30 dias, local combinado e mensagens fraudulentas (Inglaterra, Escócia e País de Gales)
- [GOV.UK: Tax and customs for goods sent from abroad](https://www.gov.uk/goods-sent-from-abroad/tax-and-duty) – cobrança da transportadora, cerca de 3 semanas e devolução ao remetente
- [GOV.UK: Report internet scams and phishing](https://www.gov.uk/report-suspicious-emails-websites-phishing) – encaminhamento gratuito de SMS fraudulentos para 7726
- [CCPC: Your consumer rights if your order is not delivered](https://www.ccpc.ie/consumer-advice/consumer-rights/buying-goods/non-delivery-of-goods) – 30 dias, nova data, reembolso e responsabilidade até a entrega (Irlanda)
- [Revenue: Buying goods from outside the EU](https://www.revenue.ie/en/customs/individuals/buying-online-personal/outside-eu.aspx) – € 3 por categoria de produto desde 1º de julho de 2026, pagos à transportadora antes da entrega
- [Revenue: Buying goods online for personal use](https://www.revenue.ie/en/customs/individuals/buying-online-personal/index.aspx) – taxa administrativa variável das transportadoras
- [FTC: Fake shipping notification emails and text messages](https://consumer.ftc.gov/consumer-alerts/2023/12/fake-shipping-notification-emails-text-messages-what-you-need-know-holiday-season) – procurar a transportadora por um site ou telefone que você sabe ser verdadeiro
- [Universal Parcel Scraper: DHL Express](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/dhl-express) – formatos de waybill e de número de volume, dígito verificador, códigos de 10 dígitos parecidos, waybills reutilizados, textos de leitura da DHL Express e o status do Peek para cada um, assinatura exibida pela DHL, o que o Peek lê da DHL Express
:::
