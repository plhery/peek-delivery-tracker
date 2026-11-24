---
title: Rastreamento FedEx: códigos, Delivery exception e alfândega
description: Rastreie encomendas FedEx no Brasil e no exterior: formato de 12, 15 e 20 dígitos, significado de Delivery exception, alfândega e suporte.
slug: rastreamento-fedex
published: 2026-11-24
updated: 2026-11-24
---

Cole um código de rastreamento da FedEx — ou um link de rastreamento de `fedex.com` — na caixa acima para acompanhar a encomenda. A página oficial da transportadora fica em `fedex.com`.

## Código de rastreamento FedEx: onde encontrar e quantos dígitos tem

Procure o código no e-mail de envio da loja ou na página do pedido. Links de `fedex.com` também contêm o número após `trknbr=` ou `tracknumbers=`. Ainda não encontrou? Veja [como encontrar o código de rastreamento](guide:find-tracking-number).

| Formato (inventado) | O que é |
| --- | --- |
| `482917360586` | 12 dígitos: código padrão da FedEx. O último algarismo é um dígito verificador calculado a partir dos 11 anteriores. |
| `482917360582919` | 15 dígitos: formato que o Peek também associa à FedEx, exceto quando começa por `8880` ([J&T Express Brasil](carrier:j-and-t-br)) ou por `30000`, `31000` ou `36000` (Andreani). |
| `61294829173605829147` | 20 dígitos começando por `6129` ou `7489`: FedEx Ground Economy (antigo SmartPost) nos Estados Unidos. |

Códigos de 12 dígitos também são usados por DPD France, Mondial Relay, BRT e diversas transportadoras asiáticas, e códigos de 15 dígitos também pertencem a DPD France, BRT e Yunda Express. Se `fedex.com` não reconhecer o código, confirme qual transportadora consta no e-mail da loja.

## O que significam os status da FedEx, incluindo Delivery exception

Estas são as leituras em inglês que o Peek interpreta da FedEx, na ordem aproximada do trajeto, com a etapa exibida pelo Peek:

| Leitura da FedEx | O Peek exibe | O que significa |
| --- | --- | --- |
| `Shipment information sent to FedEx` | "Etiqueta criada" | O remetente enviou os dados eletrônicos do envio à FedEx. |
| `Picked up`, `Tendered at FedEx location` | "Recebida pela transportadora" | A FedEx já tem o pacote (coletado ou entregue em unidade FedEx). |
| `Arrived at FedEx location`, `Departed FedEx location`, `At local FedEx facility`, `On the way` | "Em trânsito" | Em deslocamento pela rede da FedEx. |
| `Clearance in progress`, `Clearance delay` | "Na alfândega" | Em fiscalização ou desembaraço aduaneiro (veja abaixo). |
| `International shipment release - Import` | "Em trânsito" | Liberado pela alfândega no país de destino. |
| `On FedEx vehicle for delivery` | "Saiu para entrega" | No veículo do motorista para entrega. |
| `Delivery exception`, `Customer not available or business closed` | "Tentativa de entrega" | A tentativa de entrega não pôde ser concluída. |
| `Ready for pickup` | "Pronta para retirada" | Disponível para coleta em ponto de retirada. |
| `Delivered` | "Entregue" | Entregue ao destinatário. |
| `Shipment exception` | "Precisa de atenção" | A FedEx registrou uma ocorrência na remessa. |
| `Returning to shipper` | "Devolvida ao remetente" | Retornando ao remetente. |

O Peek classifica `Delivery exception` entre as tentativas falhas: `Customer not available or business closed` indica ausência ou estabelecimento fechado, e `Local delivery restriction - Delivery not attempted` indica restrição local que impediu a tentativa. Quando a FedEx sinaliza atraso, o Peek exibe "Precisa de atenção", exceto se a leitura mais recente for `Package received after FedEx cutoff` (recebido após o horário de corte do dia, seguindo no próximo dia útil).

## Entrega FedEx não realizada e pontos de retirada

Se você solicitar retenção em ponto de retirada da FedEx, uma leitura pode mostrar `Delivery option requested` durante o trajeto até o local e depois `Ready for pickup` na chegada. O Peek exibe o local em "Ponto de retirada" (com nome e endereço quando fornecidos pela FedEx). Essas alterações são solicitadas diretamente à FedEx, não pelo Peek. Veja [entrega não realizada](guide:missed-delivery) e [encomenda não retirada](guide:uncollected-parcel).

## Encomenda FedEx retida na alfândega

Envios internacionais podem exigir tributos de importação ou documentos extras. Durante a análise surgem `Clearance in progress` ou `Clearance delay`, seguidos de `International shipment release - Import` após a liberação.

- **Brasil:** encomendas expressas internacionais passam pela fiscalização da Receita Federal sob o [Programa Remessa Conforme](https://www.gov.br/receitafederal/pt-br/assuntos/aduana-e-comercio-exterior/manuais/remessas-postal-e-expressa/programa-remessa-conforme-o-que-e-como-funciona) (com Imposto de Importação, ICMS estadual e eventuais taxas de desembaraço da courier quando não pagos na compra).
- **Reino Unido:** segundo o GOV.UK, a transportadora entra em contato com a cobrança de VAT/impostos e costuma guardar o pacote por cerca de 3 semanas antes da devolução se não houver pagamento.
- **União Europeia e Irlanda:** desde 1º de julho de 2026, compras de até €150 de fora da UE pagam €3 de tarifa aduaneira por categoria de produto, e desde 1º de novembro de 2026 incidem mais €2 de taxa europeia de manuseio por categoria, cobrados antes da entrega quando não recolhidos no checkout (mais eventual taxa administrativa da courier).

Veja mais em [encomendas retidas na alfândega](guide:customs).

## Encomenda FedEx atrasada, perdida ou marcada como entregue

Se o rastreamento parou de atualizar, veja [por que o rastreamento para de atualizar](guide:tracking-not-updating). Em compras feitas em lojas online, a responsabilidade pela entrega é do vendedor, mesmo usando a FedEx (na Grã-Bretanha e na Irlanda, Citizens Advice e CCPC destacam o prazo padrão de 30 dias e o direito a reembolso ou substituição pelo lojista).

Consta `Delivered`, mas o pacote não chegou? A leitura de entrega da FedEx inclui quem assinou (dado que o Peek não armazena). Consulte o detalhamento em `fedex.com` e fale com a loja. Veja [entregue, mas não recebido](guide:delivered-not-received) e [encomenda perdida](guide:lost-parcel).

## Rastrear encomendas FedEx com o Peek

O [Peek](/) identifica códigos FedEx de 12 e 15 dígitos quando o dígito verificador é válido. Como outras transportadoras compartilham esses comprimentos, o reconhecimento pode vir de um serviço de rastreamento após breve consulta. Números que falham na verificação — ou de 15 dígitos começando por `8880`, `30000`, `31000` ou `36000` — não são enviados automaticamente à FedEx: escolha FedEx tocando em "Trocar". Códigos Ground Economy de 20 dígitos são reconhecidos imediatamente, códigos de barras escaneados têm o número extraído e links de `fedex.com` com `trknbr=` ou `tracknumbers=` também funcionam. Números domésticos de 13 dígitos da Polônia seguem pela FedEx Poland.

O Peek abre o rastreamento de `fedex.com` em navegador no próprio servidor; quando a FedEx recusa ou solicita verificação do destinatário, serviços de rastreamento assumem a leitura.

O Peek exibe a etapa, cada leitura com local e horário convertido para o fuso do seu dispositivo, o mapa do trajeto, a data prevista, o horário da entrega e, enquanto aguarda coleta, nome e endereço da unidade FedEx quando informados. Verifica atualizações até a cada 10 minutos enquanto você mantém o pacote aberto ou no dia da entrega com alertas ativados — e a cada 5 minutos a partir das 6h no horário local no dia em que a FedEx registra saída para entrega enquanto o rastreador da FedEx responde (pelos serviços de rastreamento, consulta de hora em hora, a cada 3 horas após um dia sem novidades e até a cada 15 minutos no dia da entrega enquanto o pacote estiver aberto ou com alertas ligados). Após dois dias sem novidades (ou 12 horas em "Etiqueta criada" ou "Recebida pela transportadora"), verifica de hora em hora. Você pode escolher alertas para "Cada movimentação", "Etapas importantes" ou "Só a entrega". O Peek omite nome, endereço, remetente, assinatura, peso e dimensões, e acompanha transferências Ground Economy ao USPS quando anunciadas ("Entrega por USPS").

## Perguntas frequentes sobre o rastreamento FedEx

### Códigos da FedEx começam com `1Z`?

Não. Códigos iniciados por `1Z` pertencem à [UPS](carrier:ups). Os códigos da FedEx são compostos apenas por números.

### Por que meu rastreamento FedEx só mostra `Shipment information sent to FedEx`?

Essa primeira leitura indica apenas que o remetente enviou os dados eletrônicos da etiqueta à FedEx. O rastreio só avança quando a FedEx coleta ou recebe fisicamente o pacote (`Picked up` ou `Tendered at FedEx location`). Se recebido após o horário de corte (`Package received after FedEx cutoff`), segue no próximo dia útil.

### Mensagem SMS da FedEx cobrando taxa de entrega é golpe?

Pode ser. Órgãos de defesa do consumidor (como o Citizens Advice) alertam que mensagens pedindo pagamento em link desconhecido para liberar ou reagendar entrega geralmente são golpe. Não clique no link: confira o código diretamente em `fedex.com` ou no Peek. Veja [golpes por SMS de entrega](guide:delivery-scam-texts).

:::sources
- [Receita Federal: Programa Remessa Conforme](https://www.gov.br/receitafederal/pt-br/assuntos/aduana-e-comercio-exterior/manuais/remessas-postal-e-expressa/programa-remessa-conforme-o-que-e-como-funciona) – tributação de remessas expressas internacionais no Brasil
- [Citizens Advice: If something you ordered hasn't arrived](https://www.citizensadvice.org.uk/consumer/somethings-gone-wrong-with-a-purchase/if-something-you-ordered-hasnt-been-delivered/) – responsabilidade do vendedor, 30 dias e golpes de entrega
- [GOV.UK: Tax and customs for goods sent from abroad](https://www.gov.uk/goods-sent-from-abroad/tax-and-duty) – cobrança pela transportadora, guarda de cerca de 3 semanas e devolução
- [GOV.UK: Report internet scams and phishing](https://www.gov.uk/report-suspicious-emails-websites-phishing) – denúncia de golpes por SMS
- [CCPC: Your consumer rights if your order is not delivered](https://www.ccpc.ie/consumer-advice/consumer-rights/buying-goods/non-delivery-of-goods) – direitos do consumidor na Irlanda
- [Revenue: Buying goods from outside the EU](https://www.revenue.ie/en/customs/individuals/buying-online-personal/outside-eu.aspx) – tarifa de €3 desde 1º de julho de 2026
- [Revenue: Buying goods online for personal use](https://www.revenue.ie/en/customs/individuals/buying-online-personal/index.aspx) – taxa administrativa das couriers
- [Revenue: Union Handling Fee, what does this mean for consumers?](https://www.revenue.ie/en/customs/individuals/union-handling-fee/what-does-this-mean.aspx) – taxa de €2 por categoria de produto
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – tarifa de €3 até €150 e taxa de manuseio de €2
- [European Commission: Guidance on the €3 customs duty and the Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – cobrança de €2 por categoria independentemente do valor
- [Universal Parcel Scraper: FedEx](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/fedex) – formatos de 12/15/20 dígitos, dígitos verificadores, mapeamento de status e comportamento no Peek
:::
