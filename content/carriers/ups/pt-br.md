---
title: Rastreamento UPS: códigos 1Z, status Exception e alfândega
description: Rastreie encomendas UPS no Brasil e no exterior: formato do código 1Z, significado de Exception e outros status, Access Points e alfândega.
slug: rastreamento-ups
published: 2026-11-24
updated: 2026-11-24
---

Os códigos de rastreamento da UPS (United Parcel Service) geralmente começam com `1Z`. Cole o código ou um link de rastreamento da UPS na caixa acima para acompanhar a encomenda; a página oficial de consulta fica em `ups.com`.

## Código de rastreamento UPS: onde encontrar e como identificar

A loja que enviou a encomenda informa o código no e-mail de confirmação de envio ou na página do pedido. Links de `ups.com` também trazem o número logo após `tracknum=`. Não encontrou? Veja [como encontrar o código de rastreamento](guide:find-tracking-number).

| Formato (inventado) | O que é |
| --- | --- |
| `1Z4829173605829143` | Código padrão de encomenda UPS: `1Z` seguido de 16 letras e números, totalizando 18 caracteres. O último algarismo é um dígito verificador calculado a partir dos 15 caracteres anteriores. |

## O que significam os status da UPS, incluindo Exception

A página da UPS traz uma barra de progresso (`Label Created`, `We Have Your Package`, `On the Way`, `Out for Delivery`, `Delivered`) e as leituras detalhadas abaixo. Estas são as mensagens em inglês que o Peek lê da UPS, na ordem habitual:

| Leitura da UPS | O Peek exibe | O que significa |
| --- | --- | --- |
| `Shipper created a label, UPS has not received the package yet.` | "Etiqueta criada" | O remetente gerou a etiqueta; a UPS ainda não recebeu o pacote. |
| `Drop-Off`, `Pickup Scan` | "Recebida pela transportadora" | A UPS já está com o pacote (entregue em ponto de coleta ou coletado). A leitura de origem `Arrived at Facility` também entra aqui. |
| `Arrived at Facility`, `Departed from Facility`, `Processing at UPS Facility` | "Em trânsito" | Movimentando-se pela rede da UPS (`Export Scan` e `Import Scan` marcam a passagem por fronteiras). |
| `Your package is prepared for clearance.` | "Na alfândega" | Em processo de desembaraço aduaneiro. |
| `Your package is pending release from a Government Agency. We'll notify the receiver or sender if information is needed.` | "Na alfândega" | Aguardando liberação de órgão governamental entre exportação e importação; a UPS avisa se precisar de dados. |
| `Your package has been released by a government agency.` | "Em trânsito" | O órgão liberou o pacote. |
| `Duties or taxes are due on this package.` | "Na alfândega" | Há tributos de importação a pagar antes da entrega (veja abaixo). |
| `Loaded on Delivery Vehicle`, `Out For Delivery Today` | "Saiu para entrega" | Está com o motorista para entrega. |
| `Delivery instructions have been provided by the receiver.` | Sem alteração | O destinatário enviou instruções à UPS; é apenas um aviso, não uma movimentação física, então o Peek mantém a etapa anterior. |
| `The receiver was not available at the time of the final delivery attempt.` | "Tentativa de entrega" | A última tentativa não teve sucesso. |
| `Delivered to UPS Access Point™`, `UPS Access Point™ possession` | "Pronta para retirada" | Aguardando retirada em um ponto UPS Access Point. |
| `DELIVERED` | "Entregue" | Entregue ao destinatário ou retirado no Access Point. |
| `Return to Sender` | "Devolvida ao remetente" | Retornando ao remetente. |

`Exception` é um rótulo genérico da UPS que pode indicar apenas um atraso temporário. Leia sempre a frase específica abaixo dele: `We're sorry this package may experience a temporary delay.` é apenas um atraso, enquanto `The company or receiver name is incorrect. This will delay delivery.` exige correção de dados (o Peek exibe "Precisa de atenção"). O Peek se baseia na leitura mais recente, e não apenas no rótulo genérico.

## Entrega não realizada e pontos UPS Access Point

Quando o motorista não consegue entregar, a leitura informa destinatário ausente ou comércio fechado, e a UPS tenta novamente. Após a tentativa final, uma leitura seguinte indica se a UPS vai reter o pacote, levá-lo a um Access Point ou devolvê-lo ao remetente. Veja [entrega não realizada](guide:missed-delivery).

Quando a encomenda vai para um UPS Access Point, surgem `Delivered to UPS Access Point™` e `UPS Access Point™ possession`, e o Peek exibe o nome e o endereço do ponto. Retire antes de vencer o prazo: quando um pacote não retirado inicia retorno, aparece `The UPS Access Point™ location has prepared the package for return to UPS or pickup by UPS.`. Veja [encomenda não retirada](guide:uncollected-parcel).

## Impostos alfandegários em encomendas UPS

Encomendas internacionais podem exigir pagamento de tributos antes da entrega: o rastreamento mostra `Duties or taxes are due on this package.` e `Your package is being held until duties and taxes are paid. Awaiting customer action.`, e o Peek exibe "Na alfândega" com o aviso "Taxas ou impostos de importação a pagar".

Se a alfândega exigir seu número fiscal (como CPF ou CNPJ), a UPS exibe `Importer's consumer or business Identification number is required for clearance. Awaiting information from the customer.`, e o Peek exibe "Na alfândega" com "A alfândega precisa do seu número fiscal".

- **Brasil:** a Receita Federal explica no [Programa Remessa Conforme](https://www.gov.br/receitafederal/pt-br/assuntos/aduana-e-comercio-exterior/manuais/remessas-postal-e-expressa/programa-remessa-conforme-o-que-e-como-funciona) como os tributos de compras internacionais são cobrados: em sites certificados, o Imposto de Importação e o ICMS são cobrados na compra; quando o site não os cobra, eles são cobrados na chegada ao Brasil.
- **Reino Unido:** segundo o GOV.UK, a transportadora envia a cobrança de VAT/impostos e costuma guardar o pacote por cerca de 3 semanas antes de devolvê-lo se não houver pagamento.
- **União Europeia e Irlanda:** desde 1º de julho de 2026, compras de até €150 de fora da UE pagam €3 de tarifa aduaneira por categoria de produto, e desde 1º de novembro de 2026 incidem mais €2 de taxa europeia de manuseio por categoria (independentemente do valor), cobrados pela transportadora com eventual taxa administrativa quando não pagos no checkout.

Mais detalhes em [encomendas retidas na alfândega](guide:customs).

## Encomenda UPS atrasada, perdida ou marcada como entregue

Se o rastreamento parou de atualizar, leia [por que o rastreamento para de atualizar](guide:tracking-not-updating). Se você mora na Grã-Bretanha ou na Irlanda e comprou de uma loja, é a loja que responde pela entrega, mesmo quando a UPS transporta a encomenda. Se ela não chegar em 30 dias, o Citizens Advice diz que você pode cancelar a compra e pedir o reembolso; na Irlanda, a CCPC diz para combinar uma nova data com a loja e, se ela também passar, dá direito ao reembolso integral, e diz que uma encomenda danificada ou perdida no transporte deve ser substituída.

Consta `DELIVERED`, mas você não recebeu? A página de `ups.com` pode mostrar onde o pacote foi deixado (`Left at: Front Door`, por exemplo) e um comprovante que o Peek não armazena. Confira em `ups.com` e acione a loja. Veja [entregue, mas não recebido](guide:delivered-not-received) e [encomenda perdida](guide:lost-parcel).

## Rastrear encomendas UPS com o Peek

O [Peek](/) identifica a UPS na hora para códigos `1Z` com dígito verificador válido; com dígito inválido, a UPS recusa como erro de digitação. Links contendo um código `1Z` também funcionam. O Peek consulta a UPS diretamente apenas para códigos `1Z`; outros formatos seguem para serviços de rastreamento.

O Peek consulta `ups.com` via navegador em servidor próprio até a cada 10 minutos enquanto você mantém o pacote aberto ou no dia da entrega com alertas ativados — e a cada 5 minutos a partir das 6h no horário local no dia em que a UPS registra saída para entrega, enquanto o rastreador da UPS responde (pelos serviços de rastreamento, consulta de hora em hora, a cada 3 horas após um dia sem novidades e até a cada 15 minutos no dia da entrega enquanto o pacote estiver aberto ou com alertas ligados). Após dois dias sem novidades (ou 12 horas em "Etiqueta criada" ou "Recebida pela transportadora"), verifica de hora em hora. Se a UPS recusar a consulta, serviços de rastreamento completam o histórico. O Peek exibe etapa, mapa com horário exato de cada leitura, data prevista (sem faixa horária), hora da entrega e nome/endereço do Access Point enquanto aguarda retirada, com alertas para "Cada movimentação", "Etapas importantes" ou "Só a entrega".

O Peek omite quem assinou, seu endereço, comprovante, local exato onde foi deixado, fotos e peso. Quando a UPS transfere um envio Ground Saver ao USPS e informa o código, o Peek acompanha lá assim que o USPS mostra progresso ("Entrega por USPS").

## Perguntas frequentes sobre o rastreamento UPS

### Por que meu código de rastreamento UPS aparece como inválido?

Na maioria das vezes houve erro de digitação: um único caractere trocado em um código `1Z` quebra o dígito verificador e faz a UPS rejeitá-lo. Também pode pertencer a outra transportadora — confira no e-mail da loja.

### Quanto tempo leva para o rastreamento UPS começar a aparecer?

A primeira leitura (`Shipper created a label, UPS has not received the package yet.`) surge assim que o remetente emite a etiqueta, e avança quando o pacote é postado ou coletado (`Drop-Off` / `Pickup Scan`).

### Posso alterar uma entrega da UPS pelo Peek?

Não: o Peek apenas lê o rastreamento da UPS. Mudanças solicitadas diretamente à UPS aparecem nas movimentações, como `The package will be delivered to the UPS Access Point™ location requested by the receiver.`

### SMS de entrega da UPS pedindo pagamento é golpe?

Pode ser. O Citizens Advice, no Reino Unido, alerta que mensagens pedindo pagamento em link desconhecido para liberar ou reagendar entregas costumam ser golpe. Impostos legítimos aparecem no rastreamento oficial em `ups.com` ou no Peek. No Reino Unido, encaminhe o SMS gratuitamente para 7726. Veja [golpes por SMS de entrega](guide:delivery-scam-texts).

:::sources
- [Receita Federal: Programa Remessa Conforme](https://www.gov.br/receitafederal/pt-br/assuntos/aduana-e-comercio-exterior/manuais/remessas-postal-e-expressa/programa-remessa-conforme-o-que-e-como-funciona) – regras tributárias para importações expressas no Brasil
- [Citizens Advice: If something you ordered hasn't arrived](https://www.citizensadvice.org.uk/consumer/somethings-gone-wrong-with-a-purchase/if-something-you-ordered-hasnt-been-delivered/) – responsabilidade do vendedor, prazo de 30 dias e alertas de golpe
- [GOV.UK: Tax and customs for goods sent from abroad](https://www.gov.uk/goods-sent-from-abroad/tax-and-duty) – cobrança pela transportadora, guarda de cerca de 3 semanas e devolução
- [GOV.UK: Report internet scams and phishing](https://www.gov.uk/report-suspicious-emails-websites-phishing) – denúncia de golpes por SMS
- [CCPC: Your consumer rights if your order is not delivered](https://www.ccpc.ie/consumer-advice/consumer-rights/buying-goods/non-delivery-of-goods) – direitos do consumidor na Irlanda (30 dias e reembolso)
- [Revenue: Buying goods from outside the EU](https://www.revenue.ie/en/customs/individuals/buying-online-personal/outside-eu.aspx) – tarifa de €3 por categoria de produto desde 1º de julho de 2026
- [Revenue: Buying goods online for personal use](https://www.revenue.ie/en/customs/individuals/buying-online-personal/index.aspx) – taxa administrativa das couriers
- [European Commission: EU Customs Reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – taxa europeia de manuseio de €2 desde 1º de novembro de 2026
- [European Commission: The temporary EUR 3 customs duty and the Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – cobrança de €2 por categoria independentemente do valor
- [Universal Parcel Scraper: UPS](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/ups) – formato `1Z`, dígito verificador, mapeamento de status e dados lidos pelo Peek
:::
