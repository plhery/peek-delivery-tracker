---
title: Seguimento Canada Post: códigos, estados e alfândega
description: Segue uma encomenda Canada Post: números em CA e de 16 dígitos, significado dos estados, avisos de levantamento, entrega pelos CTT e alfândega.
slug: canada-post-tracking
published: 2026-11-21
updated: 2026-11-21
---

A Canada Post (Canada Post Corporation / Postes Canada) é a empresa pública federal da Coroa que opera o serviço postal do Canadá. Podes seguir uma encomenda na [página Track da Canada Post](https://www.canadapost-postescanada.ca/track-reperage/en), na sua aplicação gratuita ou colando o código diretamente na caixa acima. O número é fornecido apenas pelo remetente — normalmente no e-mail de envio da loja —, pois a Canada Post não pesquisa encomendas pelo nome nem pela morada. Se terminar em `CA` e seguires a encomenda fora do Canadá, o Peek também pode consultar o correio nacional do teu país com o mesmo código.

## Número de seguimento da Canada Post: formato e exemplos

O Peek associa dois formatos principais à Canada Post, aqui ilustrados com algarismos inventados:

| Exemplo (inventado) | O que identifica |
| --- | --- |
| `4018 2930 5746 1826` | 16 algarismos; o último é um dígito de controlo. Também usam 16 algarismos a DHL eCommerce, [Correos Express](carrier:correos-express), [TNT](carrier:tnt) e [Evri](carrier:evri). |
| `RR482910576CA` | Código postal internacional de 13 caracteres: 2 letras (como `RR` ou `EE482910576CA`), 9 algarismos e `CA`; o último algarismo antes de `CA` é de controlo. |

No Canadá, a Canada Post encontra ainda um objeto pelo número de 15 algarismos de um aviso de entrega (*Delivery Notice Card*, por exemplo `482910573649182`) ou por uma referência composta apenas por dígitos; no Peek (uma referência precisa de pelo menos 11 algarismos), basta selecionar manualmente a Canada Post como explicado abaixo.

Outra transportadora canadiana frequente, a Purolator, emite códigos próprios de 12 algarismos ou de 3 letras e 9 algarismos: para esses o Peek não interroga a Canada Post por iniciativa própria. Se a Canada Post não reconhecer o código, confirma qual a transportadora indicada pela loja e vê [como encontrar o número de seguimento](guide:find-tracking-number) e [formatos dos números de seguimento](guide:tracking-number-formats).

## Estados de seguimento da Canada Post em inglês

O Peek lê os registos da Canada Post em inglês. Eis as mensagens mais comuns, palavra por palavra, com a etapa apresentada no Peek:

**No Canadá e em envios para o estrangeiro**

| A Canada Post mostra | O que significa para a tua encomenda | No Peek |
| --- | --- | --- |
| `Electronic information submitted by shipper` | O remetente criou a etiqueta, mas a Canada Post ainda não recebeu o volume. | «Etiqueta criada» |
| `Item accepted at the Post Office` | Entregue ao balcão de uma estação de correios canadiana. | «Aceite pela transportadora» |
| `Item picked up by Canada Post` | Recolhida pela Canada Post nas instalações do remetente. | «Aceite pela transportadora» |
| `Item processed` | Em circulação na rede da Canada Post (pode repetir-se várias vezes). | «Em trânsito» |
| `International item being forwarded to destination country` | Expedida do Canadá para o país de destino (`Item has arrived in foreign country` à chegada). | «Em trânsito» |
| `Item presented to customs` | Apresentada à alfândega no país de destino. | «Na alfândega» |
| `Item out for delivery` | Saiu hoje para entrega no Canadá. | «Em distribuição» |
| `Delivered to your community mailbox, parcel locker or apt./condo mailbox` | Entregue na caixa postal comunitária, cacifo de encomendas ou caixa do prédio. | «Entregue» |
| `Delivered to recipient's safe drop location` | Deixada num local seguro definido na morada (tal como `Delivered to mailroom` ou `Delivered to your concierge or building manager`). | «Entregue» |
| `Notice card left indicating where and when to pick up item` | Tentativa de entrega efetuada; o aviso indica em que estação e quando levantar. | «Tentativa de entrega» |
| `Item available for pickup at Post Office` | Disponível para levantamento na estação de correios. | «Pronta para levantamento» |
| `Final Notice; Item will be returned to sender if not collected within 10 days` | Último aviso: levanta nos próximos 10 dias ou regressa ao remetente. | «Pronta para levantamento» |
| `Recipient not located at address provided. Item being returned to sender.` | Destinatário não localizado na morada indicada; devolução iniciada. | «Problema reportado» |
| `Item has been returned and is enroute to the Sender` | A caminho de volta para o remetente. | «Em trânsito» |

`Item re-routed due to processing error; Possible delay` e `Delivery may be delayed due to transportation delay` alertam para atraso («Problema reportado»), enquanto `Expected delivery date updated` altera a data prevista sem movimento físico. Nos envios **do estrangeiro para o Canadá**, `International item mailed in originating country` surge como «Aceite pela transportadora», `International item has left originating country and is en route to Canada` como «Em trânsito», `Item has arrived in Canada and will be presented for review` como «Na alfândega» e `Item was released by Customs and is now with Canada Post for processing` regressa a «Em trânsito». No histórico, as entregas surgem como «Entregue» e as restantes linhas mantêm o inglês da Canada Post.

## Encomenda enviada do Canadá para Portugal ou Brasil: entrega e alfândega

**Quem entrega.** Quando o código postal internacional termina em `CA` com dígito de controlo válido, o Peek interroga também com o mesmo número o correio nacional do país a partir do qual segues o envio — os [CTT](carrier:ctt) a partir de Portugal, os Correios a partir do Brasil, a [USPS](carrier:usps) nos EUA ou a [Royal Mail](carrier:royal-mail) no Reino Unido (Parcelforce para números iniciados por `E`). Como a Canada Post não informa o país de destino, conta a região definida no teu telemóvel ou o país a partir do qual adicionaste a encomenda no navegador. Assim que o teu correio mostrar uma leitura pelo menos tão recente como a última da Canada Post, o Peek passa a segui-la lá mantendo o histórico canadiano e, durante mais um dia, continua a consultar a Canada Post no máximo a cada 55 minutos. Um número de 16 algarismos mantém-se apenas na Canada Post.

**Alfândega em Portugal.** Como o Canadá está fora da UE, as mercadorias passam pelo desalfandegamento. Em **Portugal** (continente, Açores e Madeira) incide o IVA de importação desde o primeiro euro (salvo cobrança prévia no checkout via IOSS); desde 1 de julho de 2026 as compras online fora da UE até 150 € pagam também um direito aduaneiro da UE de 3 € por categoria de artigo, e desde 1 de novembro de 2026 acresce uma taxa de manuseamento da União de 2 € por artigo independentemente do valor (no Brasil aplicam-se as regras brasileiras de tributação postal). Mais detalhes em [encomendas retidas na alfândega](guide:customs).

## Entrega falhada, cacifos e alfândega no Canadá

Para encomendas recebidas **dentro do Canadá**, aplicam-se estas regras da Canada Post e da agência fronteiriça canadiana (CBSA):

- **Tentativa à porta e aviso**: se exigir assinatura ou não couber na caixa, o carteiro toca à porta (em prédios com intercomunicador, tocar no intercomunicador conta como tentativa). Não havendo resposta, deixa um *Delivery Notice Card* (após `Delivery Attempt Made / Rescheduled for delivery next business day` volta a tentar no dia útil seguinte). Ao fim de 5 dias seguidos segue um aviso final e após **15 dias seguidos** no total na estação de correios o objeto é devolvido ao remetente. Para levantar leva documento oficial com fotografia e morada (ou assina o aviso indicando o nome de quem vai levantar por ti). Nos cacifos de encomendas (*parcel lockers*), a chave fica na tua caixa de correio e ao fim de **5 dias** sem recolha o pacote passa para a estação postal. Vê [entrega falhada](guide:missed-delivery) e [encomenda não levantada](guide:uncollected-parcel).
- **Alfândega no Canadá (CBSA)**: envios por correio até **20 CAN$** estão isentos de direitos e impostos, e os presentes de familiares ou amigos no estrangeiro até **60 CAN$** não pagam GST. Acima disso aplicam-se 5 % de GST (ou HST/PST conforme a província) e eventuais direitos no formulário **E14**, mais **9,95 CAN$** de taxa de manuseamento da Canada Post: podes pagar online no botão *Pay* em Track ou na app (com cartão de crédito; na app, também com Apple Pay ou Google Pay) antes de sair para entrega, com cartão à porta ou na estação de correios, ou contestar recusando para reavaliação ou através do formulário **B2G** no verso do E14.

## Encomenda Canada Post atrasada, parada, perdida ou não recebida

**Quatro dias sem leituras.** Após quatro dias sem novidades em «Etiqueta criada», «Aceite pela transportadora» ou «Em trânsito», o Peek mostra na lista o dia da última leitura («Sem atualizações desde …») e na ficha explica: «É habitual uma encomenda ficar sem novidades em voos longos, na alfândega ou ao fim de semana. O Peek continua a perguntar a Canada Post.», juntando o atalho «Pede ajuda a Canada Post». Segundo a CBSA, o correio em revisão aduaneira no Canadá não tem rastreio pormenorizado; lê [porque é que o seguimento não atualiza](guide:tracking-not-updating).

**Atrasada ou perdida.** A Canada Post refere que a maioria dos pacotes atrasados chega até **5 dias úteis** após a data prevista. Passado esse prazo, abre um pedido de averiguação na [página de encomendas em falta](https://www.canadapost-postescanada.ca/cpc/en/support/kb/claims/missing-packages.page) no prazo de **90 dias** após o envio (ou **6 meses** para envios destinados aos EUA ou estrangeiro). Apenas o remetente pode ser indemnizado, pelo que em compras online fala primeiro com a loja; vê [encomenda perdida](guide:lost-parcel).

**Dada como entregue mas não está.** Segundo o *Canada Post Corporation Act*, o correio deixado da forma habitual na morada, na caixa postal ou entregue a quem possa razoavelmente recebê-lo considera-se entregue. Confirma em Track o local exato (`Delivered to mailroom`, portaria ou *safe drop*) e eventual assinatura; se em Portugal entregaram os [CTT](carrier:ctt), consulta o respetivo comprovativo e vê [entregue mas não recebida](guide:delivered-not-received). Quando uma devolução chega ao remetente, o Peek apresenta «Devolvida ao remetente» ([devolvida ao remetente](guide:returned-to-sender)).

## Contactos e apoio ao cliente da Canada Post

- **Telefone**: `1-866-607-6301` no Canadá, `+1-416-979-3033` a partir do estrangeiro, TTY `1-800-267-2797`. Segunda a sexta-feira das 7:00 às 23:00 ET; fins de semana das 9:00 às 21:00 ET.
- **Online**: chat e abertura de pedidos na [página de suporte da Canada Post](https://www.canadapost-postescanada.ca/cpc/en/support.page).

> Mensagens SMS a dizer que uma entrega está retida por portes em dívida e a pedir o pagamento com um link não vêm da Canada Post. Os SMS legítimos da Canada Post partem de `272727` ou `55555` (mediante subscrição) ou de `613-734-1002` e `613-734-1004`, com links para `canadapost-postescanada.ca`. No Canadá podes reencaminhar SMS fraudulentos para `7726`.

## Seguir encomendas Canada Post com o Peek

O [Peek](/) identifica de imediato a Canada Post nos números internacionais terminados em `CA` com dígito de controlo válido. Num número de 16 algarismos válido, mostra «A verificar serviços de seguimento…», interroga em simultâneo a Canada Post, DHL eCommerce, TNT, Correos Express e Evri, e escolhe a que reconhecer o número. Para os 15 algarismos de um aviso de entrega ou uma referência numérica, toca em «Alterar» e escolhe Canada Post em «Todas as transportadoras». Links com `searchFor=` também funcionam (links com `#/search` só funcionam com códigos `CA`). Para objetos expedidos do estrangeiro para o Canadá, o Peek consulta a Canada Post assim que o correio de origem indicar o Canadá como destino.

O Peek consulta diretamente a Canada Post e apresenta o estado, o histórico com localidade (cidade e província no Canadá), o dia previsto, a janela horária quando está em distribuição no Canadá e a Canada Post a indica, a hora de entrega e o nome completo do remetente quando disponível, descartando opções de entrega, moradas, assinaturas e fotografias. As horas surgem no teu fuso («Horas no teu fuso horário: ….»).

Enquanto tens a encomenda aberta no ecrã ou no dia da entrega com as notificações ativas, o Peek verifica até a cada 10 minutos, e até a cada 2 minutos assim que a Canada Post indica que está «Em distribuição» nesse dia — a partir das 6h locais ou 30 minutos antes do início da janela horária —; fora disso, e após 12 horas sem novidades em «Etiqueta criada» ou «Aceite pela transportadora» (48 horas nas restantes etapas), verifica de hora a hora. Através dos serviços de seguimento verifica até a cada 15 minutos nesse dia «Em distribuição» nas mesmas condições, caso contrário de hora a hora, ou a cada 3 horas após um dia sem atualizações. Quando passa para o correio de destino, segue a cadência desse operador. Podes ativar alertas para «Todos os registos», «Passos importantes» ou «Só a entrega».

## Perguntas frequentes sobre o seguimento da Canada Post

### Porque é que o Peek mostra «À espera de Canada Post»?

A Canada Post devolve a mesma resposta quer um código ainda não tenha primeira leitura, quer não exista ou tenha expirado (mais de um ano). Se passarem dois dias sem resposta de nenhuma fonte, o Peek mostra «Ao fim de dois dias, a transportadora ainda não reconhece este número. Verifica-o, escolhe outra transportadora ou elimina a encomenda.» e passa a verificar uma vez por dia, e semanalmente após uma semana desde que a adicionaste. Confirma o dígito de controlo no fim dos 16 algarismos ou antes de `CA`.

### Quem entrega em Portugal uma encomenda enviada pela Canada Post?

Se o código terminar em `CA`, a entrega cabe aos [CTT](carrier:ctt) e o Peek consulta-os automaticamente com o mesmo número. Um código de 16 algarismos mantém-se na Canada Post.

### Posso seguir a encomenda pelos 15 algarismos do aviso de levantamento?

Sim: cola os 15 algarismos do cartão, toca em «Alterar» e seleciona Canada Post em «Todas as transportadoras».

:::sources
- [Canada Post: Track a package by tracking number](https://www.canadapost-postescanada.ca/track-reperage/en) – app gratuita, avisos de levantamento, identificação e motivos de código não encontrado
- [Canada Post: How to use Track](https://www.canadapost-postescanada.ca/cpc/en/support/kb/tracking/how-to-use-track.page) – código fornecido apenas pelo remetente, sem pesquisa por nome/morada e avisos de 15 algarismos
- [Canada Post: Tracking statuses](https://www.canadapost-postescanada.ca/cpc/en/support/kb/tracking/tracking-statuses.page) – estados de aviso, levantamento por terceiros, nova tentativa e alfândega
- [Canada Post: Delivery options](https://www.canadapost-postescanada.ca/cpc/en/support/articles/parcel-services-shipping-in-canada/delivery-options.page) – intercomunicador como tentativa, local seguro, aviso final aos 5 dias e guarda de 15 dias
- [Canada Post: Parcel lockers FAQ](https://www.canadapost-postescanada.ca/cpc/en/support/kb/receiving/parcel-lockers/why-wasnt-my-parcel-delivered.page) – chave na caixa postal e limite de 5 dias no cacifo
- [Canada Post: How to pay duty and taxes](https://www.canadapost-postescanada.ca/cpc/en/support/kb/receiving/delivery-faq/how-to-pay-duty-and-taxes-online-canada-post.page) – taxa de 9,95 CAN$, pagamento online e Apple Pay / Google Pay na app
- [Canada Post: Missing packages](https://www.canadapost-postescanada.ca/cpc/en/support/kb/claims/missing-packages.page) – margem de 5 dias úteis e prazos de 90 dias e 6 meses
- [Canada Post: Support and contact](https://www.canadapost-postescanada.ca/cpc/en/support.page) – telefones no Canadá e no estrangeiro, horários e chat
- [Canada Post: Recognizing spam and fraudulent messages](https://www.canadapost-postescanada.ca/cpc/en/support/kb/company-policies/privacy-security/what-do-to-if-you-get-suspicious-email.page) – remetentes reais de SMS e código 7726
- [Canada Post: Responsible business practices](https://www.canadapost-postescanada.ca/cpc/en/our-company/corporate-sustainability/responsible-business-practices.page) – empresa pública federal da Coroa
- [CBSA: Importing by mail](https://www.cbsa-asfc.gc.ca/import/personal-personnel/postal-postale-eng.html) – revisão aduaneira postal e formulário E14
- [CBSA: Paying duty and taxes](https://www.cbsa-asfc.gc.ca/import/postal-postale/dtytx-drttx-eng.html) – isenções de 20 CAN$ e 60 CAN$, GST/HST/PST
- [CBSA: Disputing duty and taxes](https://www.cbsa-asfc.gc.ca/import/postal-postale/dispute-contestation-eng.html) – reavaliação e formulário B2G
- [Justice Laws Website: Canada Post Corporation Act](https://laws-lois.justice.gc.ca/eng/acts/C-10/page-1.html) – definição legal de entrega postal
- [Your Europe: Expedição e entrega](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_pt.htm) – direito aduaneiro de 3 € por artigo até 150 €
- [Comissão Europeia: Customs Guidance on EUR 3 customs duty](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – aplicação desde 1 de julho de 2026
- [Comissão Europeia: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – taxa de manuseamento de 2 € por artigo desde 1 de novembro de 2026
- [Comissão Europeia: Territorial scope of EU VAT and customs](https://taxation-customs.ec.europa.eu/taxation/vat/vat-directive/how-does-vat-work/territorial-scope_en) – enquadramento aduaneiro e de IVA de Portugal
- [Universal Parcel Scraper: Canada Post](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/canada-post) – formatos de código, mapeamento de estados e transição postal
:::
