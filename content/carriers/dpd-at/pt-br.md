---
title: Rastreamento DPD Áustria: status, pontos Pickup e contato
description: Rastreie encomendas da DPD na Áustria: número de 14 dígitos e PIN, o que significam os status do myDPD em alemão e inglês, prazos Pickup e como contatar a DPD.
slug: dpd-austria-tracking
published: 2026-11-26
updated: 2026-11-26
---

A DPD Áustria (DPD Austria) é uma transportadora privada sediada em Leopoldsdorf. Dentro da Áustria, costuma entregar em até um dia útil após receber o volume da loja. Rastreie no [myDPD](https://www.mydpd.at/) — site e app da DPD Áustria — usando o código de 14 dígitos do e-mail de envio da loja, ou cole o código na caixa acima.

## Número da encomenda e PIN na DPD Áustria

O número da encomenda DPD tem 14 dígitos. A loja o envia, ou a DPD envia por e-mail quando a loja informa seu endereço eletrônico; com conta myDPD, aparece em "My parcels". O PIN (Parcel Information Number) vem no cartão vermelho e branco deixado quando ninguém atende. O myDPD aceita ambos; o Peek precisa do número da encomenda. Padrões reconhecidos pelo Peek:

| Código (inventado) | O que é |
| --- | --- |
| `06204819273645` | 14 dígitos começando entre `0620`–`0630`, `0632`–`0635`, `0643`–`0645` ou `0649`: faixa de depósitos da DPD Áustria. |
| `01904819273645` | 14 dígitos começando entre `0101`–`0199`: o Peek lê primeiro como [DPD Alemanha](carrier:dpd-de), mas a DPD Áustria ainda pode realizar a entrega (veja abaixo). |

Se a etiqueta mostrar uma letra ou dígito extra após os 14 dígitos, omita-o. Se o e-mail da loja indicar [Austrian Post](carrier:austrian-post), [GLS Áustria](carrier:gls-at) ou outra empresa, o código pertence a ela. Não tem nenhum código? Veja [como encontrar seu código de rastreamento](guide:find-tracking-number).

## O que significam os status de rastreamento da DPD Áustria

O myDPD mostra cada movimentação com o depósito responsável, em alemão ou inglês. Na ordem em que a encomenda costuma passar por eles (*Paketstation* significa locker de encomendas):

| O myDPD diz | O que significa para você |
| --- | --- |
| `Auftragsdaten übermittelt` | O remetente enviou os dados eletrônicos à DPD, mas ainda não entregou o volume (no FAQ em inglês aparece como "Order data transmitted to DPD"). |
| `Paket unterwegs` | A DPD está com a encomenda: coletada no primeiro depósito ou passando por um hub. |
| `Im Paketzustellzentrum` | No depósito que fará a entrega. A saída para entrega vem em seguida. |
| `Unfortunately, parcel was loaded incorrectly and will be delivered soon.` | Carregada no veículo errado; outro depósito fará nova triagem antes da entrega. |
| `In Zustellung` | Com o motorista, a caminho do seu endereço. |
| `Empfänger leider nicht angetroffen.`, `Unfortunately, consignee not present. Notification was left.` | Ninguém em casa; o motorista deixou cartão de aviso. |
| `Unfortunately, parcel was not delivered.` | Não entregue nesta rota; retorna ao depósito para nova tentativa. |
| `In Zustellung an Pickup Paketstation`, `In delivery to Pickup parcel shop` | Após uma entrega não realizada, o motorista leva o volume a um locker ou loja Pickup. |
| `Paket wurde in der Pickup Paketstation hinterlegt`, `Parcel was handed over to Pickup parcel shop`, `Takeover by Pickup parcel shop` | Aguardando você em um locker ou loja Pickup. A DPD envia o código de retirada por e-mail ou SMS (com endereço no caso de lockers). |
| `Paket durch Empfänger abgeholt` | Retirada no ponto Pickup. |
| `Paket wurde erfolgreich abgestellt` | Deixada no local seguro autorizado por você (deposit okay), como a garagem. |
| `Parcel was not picked up` | Ninguém retirou no prazo; a devolução ao remetente costuma começar no mesmo minuto. |
| `Parcel will be returned to the sender.` | A encomenda iniciou o retorno ao remetente. |
| `Parcel was picked up by driver from Pickup parcel shop` | Motorista recolheu de um ponto Pickup: no início da viagem indica postagem pelo remetente; depois da espera para você, indica retorno ao remetente. |

## Entregas da DPD na Áustria: Predict, alterações e pontos Pickup

**Predict.** Se a loja fornecer seu e-mail ou celular à DPD, ela avisa com antecedência quando a entrega acontecerá. No dia, o Live-Tracking exibe uma janela de horário e mapa atualizado a cada 15 minutos (não garantida em razão de trânsito ou clima). Para envios do exterior, siga a estimativa da loja.

**Alterar a entrega.** Cadastre-se no myDPD para alterar a entrega até pouco antes da chegada usando o código de 14 dígitos ou PIN: outro dia ou endereço, vizinho indicado, ponto ou locker Pickup, ou deposit okay (autorização para deixar em local escolhido, como o terraço). Com conta, você também pode definir ponto/locker favorito ou autorização permanente.

**Ninguém em casa.** A DPD pode entregar a qualquer pessoa que receba no seu endereço residencial ou comercial sem pedir documento. Após uma primeira entrega não realizada, pode deixar com um vizinho ou, se não houver, no ponto/locker Pickup mais próximo, informando nome e endereço no cartão e no myDPD. Veja [entregas não realizadas](guide:missed-delivery).

**Retirada.**
- Ponto Pickup: **7 dias corridos**, com lembretes após 3 e 5 dias. Apresente o código de retirada enviado por e-mail ou SMS, ou documento de identidade com assinatura (a DPD não reenvia o código e o myDPD não o exibe). Cada loja tem horário próprio.
- Locker Pickup: **5 dias corridos**, com lembrete após 3 dias. Exige o código enviado por e-mail ou SMS; funciona 24 horas.

Após o prazo, o volume volta ao remetente: peça reenvio à loja. Veja [encomendas não retiradas](guide:uncollected-parcel) e [devolvida ao remetente](guide:returned-to-sender).

## Encomenda DPD atrasada, extraviada, danificada ou na alfândega

Para reembolso ou reenvio, fale com a loja titular do contrato com a DPD.

**Sem atualização.** `Auftragsdaten übermittelt` por dias significa que a loja ainda não entregou o volume à DPD: pergunte ao remetente. Se o Live-Tracking ficar horas parado no dia da entrega, fale com a DPD informando seu código. Veja [por que o rastreamento para de atualizar](guide:tracking-not-updating).

**Entregue, mas não recebida.** Como orienta a DPD, verifique primeiro o local autorizado e o vizinho citado no cartão. Nada ainda? Use o formulário de destinatários ("Where was my parcel delivered/handed over.") e avise a loja. Veja [entregue mas não recebida](guide:delivered-not-received).

**Extraviada.** Se ninguém localizar o volume, contate o suporte da DPD e a loja. Veja [encomenda extraviada](guide:lost-parcel).

**Danificada.** A DPD investiga danos apenas a pedido do remetente, então avise a loja para que ela acione a DPD e, conforme os termos dela, lhe ofereça reembolso, nova entrega ou algo parecido. Os termos da DPD exigem comunicação escrita de danos ocultos em no máximo **seis dias corridos** e de danos visíveis logo após a entrega, então aja rápido. Veja [encomenda danificada](guide:damaged-parcel).

**Alfândega.** Compras on-line de fora da UE têm imposto aduaneiro fixo de €3 por item em pacotes até €150 (em vigor desde 1º de julho de 2026) e taxa de manuseio da União de €2 por item, qualquer que seja o valor (desde 1º de novembro de 2026). Cada item reúne mercadorias com mesma classificação tarifária, descrição e, quando exigido, origem. As páginas da DPD Áustria não dizem se ou como ela repassa essas cobranças: confirme com a loja se estavam incluídas no preço. Veja [encomendas retidas na alfândega](guide:customs).

## Contato e atendimento da DPD Áustria

O Peek não contata a DPD nem a loja por você. As páginas em inglês da DPD Áustria informam:

- **Telefone:** `05 03 73 420` (`+43 5 03 73 420` do exterior), central para clientes particulares, segunda a sexta das 7h às 18h (horário da Áustria).
- **On-line:** [formulário de contato da DPD Áustria para destinatários](https://www.dpd.com/at/en/support/contact-form-consignee/), informando código ou PIN, nome e e-mail. A DPD pede que você use apenas um canal por vez para não atrasar o atendimento.
- **Endereço postal:** DPD Direct Parcel Distribution Austria GmbH, Arbeitergasse 46, 2333 Leopoldsdorf.

> E-mails e SMS falsos em nome da DPD continuam circulando. A DPD orienta: não pague, não abra anexos, não clique em links de login e apague a mensagem. Se houve prejuízo financeiro, procure a polícia. Veja [golpes de entrega por SMS](guide:delivery-scam-texts).

## Acompanhe uma encomenda da DPD Áustria com o Peek

Cole o código de 14 dígitos no [Peek](/), sem precisar de código postal.

Se o número estiver em uma das faixas de depósito da DPD Áustria, o Peek consulta DPD Áustria entre as primeiras transportadoras onde quer que você esteja e mostra "DPD Austria está com esta encomenda." assim que o myDPD reconhece o envio e sua última etapa está na Áustria. Se ainda não constar, aparece "ainda não encontrada · o Peek continua verificando"; adicione assim mesmo — o pacote mostra "Provavelmente DPD Austria" e "Aguardando a primeira movimentação de DPD Austria" enquanto o Peek segue consultando.

Outros códigos de 14 dígitos cabem em várias empresas DPD e outras transportadoras, então o Peek consulta as mais prováveis; se a DPD Áustria tiver o volume, clique em "Trocar" e escolha DPD Austria (ou "Trocar transportadora" depois). Links do myDPD com o número apontam direto para a DPD Austria.

**De outra empresa DPD.** Em envios da [DPD Países Baixos](carrier:dpd-nl), DPD Bélgica ou DPD Hungria, assim que a DPD posiciona o volume em um depósito de entrega na Áustria ou além, o Peek passa a consultar a DPD Áustria com o mesmo código e prossegue lá quando o myDPD registra movimentação tão recente quanto a última da origem (antes disso, o cartão já indica a DPD Áustria como entregadora). Volumes da [DPD Alemanha](carrier:dpd-de) permanecem sob DPD Alemanha, mas assim que entram na Áustria o Peek lê também o myDPD e exibe os detalhes mais completos.

**O que aparece.** Status, histórico nos textos do myDPD com cidade e país de cada depósito e horário de entrega no fuso austríaco (etapas em países de outro fuso mantêm o fuso local). Volumes em pontos Pickup aparecem como "Pronta para retirada". Nomes, endereços, janela de horário, ponto Pickup, assinante e opções de entrega ficam de fora, sem data prevista (e o myDPD não informa peso). Confira o ponto Pickup no myDPD ou no cartão, e o código de retirada no e-mail ou SMS da DPD.

Com a encomenda aberta no Peek, ou no dia da entrega com notificações ligadas, verifica até a cada 10 minutos — e a cada 2 minutos a partir das 6h (horário da Áustria) no dia em que sair para entrega. Você pode escolher notificações para "Cada movimentação", "Etapas importantes" ou "Só a entrega". Após dois dias sem novidades (12 horas em "Etiqueta criada" ou "Recebida pela transportadora"), consulta de hora em hora, e a cada 6 horas depois de sete dias em "Etiqueta criada". Após quatro dias sem movimentação em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito", exibe "Sem atualizações desde …" e um link "Pedir ajuda a DPD Austria". Para alterar a entrega, use o myDPD.

## Perguntas sobre o rastreamento da DPD na Áustria

### Quanto tempo a DPD leva para entregar na Áustria?

Normalmente um dia útil dentro da Áustria, contado a partir do recebimento do volume pela DPD. Antes disso, o myDPD pode mostrar `Auftragsdaten übermittelt`.

### Por quanto tempo a DPD guarda minha encomenda em um ponto ou locker Pickup?

7 dias corridos em um ponto Pickup e 5 dias corridos em um locker Pickup. Depois disso, volta ao remetente.

### O que é o código PIN da DPD?

É o Parcel Information Number, impresso no cartão deixado quando a entrega não é realizada. O myDPD aceita o PIN; no Peek, use o código de 14 dígitos.

### O que significa `Auftragsdaten übermittelt`?

A loja transmitiu os dados do envio à DPD, mas ainda não entregou o pacote físico. Se ficar dias assim, fale com a loja.

### Qual é o telefone da DPD Áustria?

`05 03 73 420` para clientes particulares, de segunda a sexta das 7h às 18h (horário da Áustria).

:::sources
- [DPD Austria: Receiving parcels](https://www.dpd.com/at/en/receiving-parcels/) – Predict, Live-Tracking, alterações, deposit okay, lockers
- [DPD Austria: FAQ receiving parcels](https://www.dpd.com/at/en/support/faq/faq-receiving-parcels/) – número da encomenda, PIN, um dia útil, alterações
- [DPD Austria: FAQ Pickup](https://www.dpd.com/at/en/support/faq/pickup/) – prazo de guarda, código de retirada, documento
- [DPD Austria: General terms and conditions](https://www.dpd.com/at/en/gtc/) – prazo de guarda, lembretes, devolução
- [DPD Austria: General terms and conditions, national (PDF)](https://www.dpd.com/wp-content/uploads/sites/224/2022/01/AGB0447_AGBs_DPDA_national_en.pdf) – vizinho ou ponto Pickup após a primeira tentativa, Predict, janela de horário, comunicação de danos
- [DPD Austria: What does the status "Order data transmitted to DPD" mean?](https://www.dpd.com/at/en/faq/was-bedeuter-der-status-auftragsdaten-an-dpd-uebermittelt/) – pergunte ao remetente
- [DPD Austria: Status not changing in live tracking](https://www.dpd.com/at/en/faq/der-paketstatus-meines-pakets-hat-sich-schon-lange-nicht-mehr-veraendert-woran-kann-das-liegen/) – várias horas
- [DPD Austria: Parcel shown as delivered but not delivered](https://www.dpd.com/at/en/faq/ein-paket-das-mir-als-zugestellt-angezeigt-wird-ist-noch-nicht-geliefert-worden-woran-liegt-das/) – o que verificar primeiro
- [DPD Austria: My parcel can't be located](https://www.dpd.com/at/en/faq/mein-paket-ist-unauffindbar-was-soll-ich-tun/) – atendimento ao cliente
- [DPD Austria: Parcel arrives damaged](https://www.dpd.com/at/en/faq/was-ist-zu-tun-wenn-ein-paket-beschadigt-beim-empfanger-ankommt/) – primeiro o remetente
- [DPD Austria: Contact](https://www.dpd.com/at/en/support/contact/) – telefone, horários
- [DPD Austria: Contact form for consignees](https://www.dpd.com/at/en/support/contact-form-consignee/) – um canal por vez
- [DPD Austria: Security](https://www.dpd.com/at/en/security/) – e-mails e SMS falsos
- [DPD Austria: Legal notice](https://www.dpd.com/at/en/legal-notice/) – empresa, endereço, telefone do exterior
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – imposto de €3 desde 1º de julho de 2026, taxa de €2 desde 1º de novembro de 2026
- [European Commission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – o que é um item
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – o que o Peek lê, textos do myDPD, faixas de depósito, transferências
:::
