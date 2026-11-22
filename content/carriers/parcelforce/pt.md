---
title: Seguimento Parcelforce Worldwide: estados e alfândega
description: Acompanha uma encomenda Parcelforce Worldwide: onde está o número, significado dos estados, opções de entrega, faturas aduaneiras e contacto Royal Mail.
slug: seguimento-parcelforce-worldwide
published: 2026-11-22
updated: 2026-11-22
---

A Parcelforce Worldwide faz parte do grupo [Royal Mail](carrier:royal-mail), pelo que podes acompanhar uma encomenda Parcelforce na página Track your item da Royal Mail ou colar o número na caixa acima. O número vem da loja ou do remetente, normalmente no e-mail de confirmação da encomenda.

## Número de seguimento Parcelforce: onde encontrar

Procura no e-mail de confirmação da loja ou pergunta à loja. Perdeste-o? Vê [como encontrar o teu número de seguimento](guide:find-tracking-number). Três formatos que podes ver:

| Número (inventado) | O que é |
| --- | --- |
| `EA482917367GB` | Duas letras (`E` e outra letra, ou `CP` ou `GI`), nove dígitos e `GB`, com dígito de controlo antes de `GB`. |
| `PBTR4829173654` | Número de volume de 14 caracteres: `PB`, o número de expedição e a posição do volume nela. |
| `TR4829173` | Um número de expedição isolado: o rastreador da Royal Mail pede em vez disso o número próprio de cada volume. |

O formato de 13 caracteres também pertence à Royal Mail (`RR482917367GB` é Royal Mail, não Parcelforce), e algumas encomendas express48 usam número Royal Mail. Como ambas se seguem na mesma página, pouco muda para ti.

## Estados de seguimento da Parcelforce explicados

O rastreador da Royal Mail apresenta estas linhas em inglês. Pela ordem habitual:

| O seguimento diz | O que significa para ti |
| --- | --- |
| `Sender has despatched item` | O remetente registou a encomenda. A Parcelforce ainda não a tem. |
| `Item Collected` | Recolhida junto do remetente. |
| `Accepted at Parcelshop` | O remetente entregou-a numa estação de correios ou ponto de recolha. |
| `Item received` | Está na rede. |
| `Customs charges raised` | Uma importação de fora do Reino Unido tem IVA, direitos ou taxa a pagar antes da entrega. |
| `Despatched to delivery office` | A caminho do depósito ou centro de distribuição local. |
| `Received at Delivery Depot` | No depósito local. Ainda não está pronta para levantamento. |
| `Out for delivery` | Com o motorista hoje. |
| `Delivery attempted` | O motorista não conseguiu entregar. |
| `Delivered by` | Entregue. A linha pode indicar o centro de distribuição que a entregou. |
| `Delivered to Neighbour/Reception by` | Deixada com um vizinho ou na receção, indicado na linha. |
| `Returned to sender` | De regresso ao remetente. |

Em encomendas enviadas para o estrangeiro, o histórico continua com linhas como `Item Leaving the UK` e `Due to be delivered today` do parceiro local.

## Opções de entrega Parcelforce e entregas falhadas

**Antes da entrega.** Na app Royal Mail ou através da Royal Mail Customer Services podes escolher outra data de entrega para uma encomenda Parcelforce, pedir entrega a um vizinho ou solicitar que o motorista bata mais forte ou te dê mais tempo para chegar à porta. O local seguro também se define na Royal Mail para encomendas Parcelforce.

**Entrega falhada.** O seguimento mostra `Delivery attempted` ou `Delivered to Neighbour/Reception by` com o nome de quem a recebeu. Se ainda não conseguires localizar a encomenda, liga para a Royal Mail Customer Services (abaixo).

## Seguimento Parcelforce sem atualizar, atrasado ou perdido

**Ainda sem registos.** O remetente pode ter criado o número antes de entregar a encomenda: `Sender has despatched item` significa que a Parcelforce ainda não a recebeu. Confirma o número e tenta mais tarde. Mais em [porque é que o seguimento para de atualizar](guide:tracking-not-updating).

**Atrasada, perdida ou danificada.** Se compraste online, contacta primeiro o vendedor: a Citizens Advice indica que costumas obter melhor resultado e podes pedir reembolso ou novo envio. Para indemnização, consulta como reclamar no site da Parcelforce.

**Taxas aduaneiras.** A Parcelforce trata do desalfandegamento de encomendas vindas de fora do Reino Unido e contacta-te caso haja IVA, direitos ou outras taxas a pagar, com uma fatura que indica exatamente o valor ([mais sobre alfândega](guide:customs)). Normalmente guarda a encomenda cerca de 3 semanas; sem pagamento até lá, devolve-a ao remetente. Questões sobre a mercadoria seguem também para a Parcelforce.

> Tens dúvidas se um e-mail ou SMS a pedir pagamento é verdadeiro? Não cliques em ligações nem dês dados bancários. Encaminha gratuitamente e-mails fraudulentos para report@phishing.gov.uk e SMS suspeitos para 7726 (Reino Unido).

## Telefone e apoio ao cliente da Parcelforce

A Citizens Advice encaminha clientes Parcelforce para a Royal Mail para preferências de entrega e locais seguros:

- **Royal Mail Customer Services:** 03457 740 740, de segunda a sexta das 8:00 às 18:30, sábado das 8:00 às 15:00 e domingo das 9:00 às 14:00 (hora do Reino Unido). **Relay UK:** 18001 e depois 0345 266 0075.
- **App Royal Mail:** data de entrega, vizinho, local seguro e necessidades de entrega também para encomendas Parcelforce.

Para reclamações ou pedidos de indemnização, usa o site próprio da Parcelforce.

## Seguir encomendas Parcelforce com o Peek

O [Peek](/) identifica logo a Parcelforce para números tipo `EA482917367GB` com dígito de controlo válido e para números `PB` de 14 caracteres. Um número de expedição isolado não é seguido. Um link Parcelforce com `trackNumber=` funciona, tal como um link Royal Mail terminado em `#/tracking-results/` com número Parcelforce.

O Peek consulta a Parcelforce no site da Royal Mail abrindo-o num navegador real no servidor, pelo que cada verificação demora mais e pode falhar quando o site bloqueia visitas automatizadas. O Peek tenta então serviços universais de seguimento, que têm pouca informação sobre a Parcelforce.

O Peek mostra o estado, cada registo com local (sem códigos postais), o dia previsto de entrega, a hora de entrega e o país de destino. Os estados mantêm o inglês da Royal Mail, exceto alguns que o Peek traduz, como `Out for delivery` («Em distribuição»), `Delivered` («Entregue») e `Returned to sender` («Devolvida ao remetente»). Um registo no estrangeiro sem fuso horário não fica datado; os restantes surgem no teu fuso horário («Horas no teu fuso horário:»). Numa exportação com número tipo `EA482917367GB`, o Peek pode também consultar os correios nacionais do país de destino indicado pela Parcelforce (se não indicar nenhum, os do país onde te situa, exceto o Reino Unido) e passar para o seu seguimento assim que mostrarem um registo não mais antigo do que o último da Parcelforce.

Enquanto mantiveres a encomenda aberta no Peek — ou no dia da entrega com as notificações ligadas — o Peek verifica até de 10 em 10 minutos, e até de 5 em 5 minutos a partir das 6:00 hora do Reino Unido (ou meia hora antes de abrir a janela horária, quando a Parcelforce indicar uma) no dia em que a Parcelforce a regista em distribuição, enquanto o rastreador da Parcelforce responder; fora disso, e após dois dias sem novidades (12 horas enquanto continuar em «Etiqueta criada» ou «Aceite pela transportadora»), de hora em hora; após sete dias sem novidades em «Etiqueta criada», de 6 em 6 horas. Após quatro dias sem novos registos numa encomenda em «Etiqueta criada», «Aceite pela transportadora» ou «Em trânsito», o Peek mostra a data do último registo («Sem atualizações desde …») e «É habitual uma encomenda ficar sem novidades em voos longos, na alfândega ou ao fim de semana. O Peek continua a perguntar a Parcelforce Worldwide.», com a ligação «Pede ajuda a Parcelforce Worldwide» para a página de contactos da Parcelforce. Coloca os registos num mapa e envia alertas à tua escolha («Todos os registos», «Passos importantes» ou «Só a entrega»).

## Perguntas sobre o seguimento Parcelforce

### A Parcelforce faz parte da Royal Mail?

Sim. O GOV.UK integra a Parcelforce no Royal Mail Group e as suas encomendas são acompanhadas na página Track your item da Royal Mail.

### Porque é que o meu número Parcelforce não é reconhecido?

A encomenda pode ainda não ter chegado à Parcelforce ou o número pode estar mal escrito. Um número de expedição isolado não é seguido: pede ao remetente o número `PB` de 14 caracteres de cada volume e tenta mais tarde.

### Quanto tempo guarda a Parcelforce uma encomenda com taxas aduaneiras?

Cerca de 3 semanas. Se a fatura não for paga até lá, a encomenda regressa ao remetente.

### Posso escolher outro dia para uma entrega Parcelforce?

Sim, na app Royal Mail ou através da Royal Mail Customer Services. Também podes pedir entrega a um vizinho.

:::sources
- [GOV.UK: Check when you can account for import VAT on your VAT Return](https://www.gov.uk/guidance/check-when-you-can-account-for-import-vat-on-your-vat-return) – Parcelforce parte do Royal Mail Group
- [Citizens Advice: Telling delivery companies about your delivery or collection needs](https://www.citizensadvice.org.uk/consumer/post-and-parcels/telling-delivery-companies-about-your-delivery-or-collection-needs/) – app Royal Mail e Customer Services para Parcelforce, outra data, vizinho, local seguro, bater à porta, telefone e horários
- [Citizens Advice: Claiming compensation from Royal Mail](https://www.citizensadvice.org.uk/consumer/post-and-parcels/claiming-compensation-from-royal-mail/) – número Relay UK
- [Citizens Advice: Damaged, lost or delayed post or parcel](https://www.citizensadvice.org.uk/consumer/post-and-parcels/damaged-lost-delayed-post-or-parcel/) – vendedor primeiro, reclamações no site da transportadora
- [GOV.UK: Tax and customs for goods sent from abroad: Overview](https://www.gov.uk/goods-sent-from-abroad) – Parcelforce trata da alfândega, dúvidas sobre mercadorias
- [GOV.UK: Tax and customs for goods sent from abroad: Tax and duty](https://www.gov.uk/goods-sent-from-abroad/tax-and-duty) – fatura, cerca de 3 semanas, devolução ao remetente
- [GOV.UK: Avoid and report internet scams and phishing](https://www.gov.uk/report-suspicious-emails-websites-phishing) – 7726, report@phishing.gov.uk
- [UPU: S10 standard, Identification of postal items](https://www.upu.int/UPU/media/upu/files/postalSolutions/programmesAndServices/standards/S10-12.pdf) – dígito de controlo antes do código de país
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – o que o Peek lê, estados e formatos de números
:::
