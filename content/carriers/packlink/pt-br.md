---
title: Rastreamento Packlink: referência, status e contato
description: Rastreie uma encomenda Packlink: formato da referência ES…COM/PRO, significado de cada status, entregas não realizadas, danos e como contatar a Packlink.
slug: packlink-tracking
published: 2026-11-27
updated: 2026-11-27
---

A Packlink não é uma transportadora. É uma plataforma de reservas sediada em Madri que funciona como comparador de envios: uma pessoa ou loja compara transportadoras como SEUR, Correos, Correos Express ou UPS, paga, imprime a etiqueta, e a transportadora escolhida faz a coleta e a entrega. Lojas usam o Packlink PRO, serviço empresarial integrado a lojas virtuais e marketplaces como eBay e Amazon. Para rastrear uma encomenda, digite a referência Packlink ou o código próprio da transportadora na [página de rastreamento da Packlink](https://www.packlink.es/seguimiento-envios/) ou na caixa acima.

## Código de rastreamento Packlink: como identificar

A Packlink atribui a cada envio uma referência própria: duas letras do mercado, o ano, `COM` ou `PRO` e dez dígitos. O site espanhol da Packlink informa que suas referências começam com `ES`. A transportadora também gera um número próprio, e a página da Packlink aceita qualquer um dos dois. Exemplos com dígitos fictícios:

| Código (fictício) | O que é |
| --- | --- |
| `ES2026COM4829173650` | `ES`, ano, `COM` e 10 dígitos: referência Packlink. |
| `ES2026PRO6194028375` | O mesmo com `PRO`: também referência Packlink. |
| `48291736405829` | 14 dígitos: um dos formatos da SEUR, compartilhado com DPD e outras transportadoras. |
| `1Z9X482W0348291737` | `1Z` e 16 letras ou dígitos: código da UPS. |

**Onde encontrar.** A Packlink envia a referência no e-mail de confirmação assim que o envio é contratado. Se alguém vendeu um item a você e despachou pela Packlink, o e-mail ficou com o remetente: peça a referência ou o código da transportadora. Veja mais em [como encontrar seu código de rastreamento](guide:find-tracking-number), em inglês, e [pedidos de segunda mão](guide:second-hand-orders), em inglês.

## Significado dos status de rastreamento da Packlink

A Packlink lê cada movimentação da transportadora e a enquadra em um código próprio, exibido em inglês na página de rastreamento; muitas linhas mantêm também o texto original da transportadora, como as maiúsculas em espanhol da SEUR ou da Correos Express. Linhas mais comuns, em ordem:

| Linha de rastreamento | O que significa para você |
| --- | --- |
| `Waiting for carrier to collect the order` | O remetente contratou o envio e gerou a etiqueta, mas a transportadora ainda não coletou o pacote. |
| `SIN RECEPCION` | Texto da Correos Express para o mesmo: há reserva, mas o pacote ainda não foi entregue a ela. |
| `RECIBIDO EN ORIGEN` | O primeiro depósito da SEUR recebeu o pacote; a Packlink ainda o classifica como aguardando coleta. |
| `Shipment accepted by carrier (scanned)` | A transportadora leu o pacote e ele segue viagem. |
| `EN ARRASTRE` | Em deslocamento entre os depósitos da transportadora. |
| `DELEGACION DESTINO` | Chegou à unidade responsável pela entrega na sua região. |
| `TU ENVÍO ESTÁ PENDIENTE DE LLEGAR A DESTINO` | SEUR: ainda não chegou à filial local. A Packlink marca como problema, mas a encomenda segue apenas em trânsito. |
| `EN REPARTO` | Saiu para entrega. |
| `REPARTO FALLIDO` | Entrega não realizada. |
| `Package at pick-up point and ready for collection` | Aguardando você em um ponto de retirada. A Packlink agrupa esse status junto aos códigos de entrega, mas você ainda precisa retirar. |
| `ENTREGADO` | Entregue. |
| `Package returned to sender` | A encomenda retornou ao remetente. |

Outras linhas vêm em inglês claro: `Recipient not present at indicated address` e `Unsuccessful delivery and final attempt` indicam entrega não realizada, e `Packet delivered to customs for clearance` marca a etapa aduaneira em envios que cruzam fronteira fiscal.

Duas mensagens não descrevem o pacote em si: `Pending, there is no information available on the carrier’s page or the tracking number has not yet been activated` indica que a Packlink ainda não encontrou dados na transportadora; `Expired, there is no tracking information for the last 30 days` significa que a transportadora ficou um mês sem atualizar e a Packlink parou de acompanhar.

## Quem entrega uma encomenda Packlink

A transportadora escolhida pelo remetente. A página de rastreamento da Packlink lista [SEUR](carrier:seur), [Correos](carrier:correos-spain), [Correos Express](carrier:correos-express), [UPS](carrier:ups), GLS, DHL, [TNT](carrier:tnt), [InPost](carrier:inpost), [Mondial Relay](carrier:mondial-relay), [NACEX](carrier:nacex) e Zeleris, entre outras. Segundo os termos da Packlink:

- **Endereço ou ponto de retirada.** O remetente define na contratação se vai para endereço ou ponto de retirada.
- **Entrega não realizada.** A transportadora pode deixar aviso para reagendar e levar o pacote de volta ao depósito. Nem todas deixam aviso; por isso a Packlink pede que o remetente acompanhe o rastreamento e informe você.
- **Tentativas.** Na maioria dos casos há uma primeira tentativa e mais uma gratuita. Cada tentativa adicional custa ao remetente de €2 a €15 mais IVA, conforme a transportadora e o veículo. Em alguns países a transportadora pode deixar com vizinho ou em ponto de retirada.
- **Retirada.** Um pacote guardado no depósito precisa ser retirado no prazo da transportadora; depois disso, o remetente pode ter de pagar armazenagem, devolução ou destruição.
- **Recusada ou impossível de entregar.** A Packlink avisa o remetente pelo rastreamento ou e-mail, e ele tem 2 dias úteis para decidir entre devolução ou reenvio para outro endereço.

Veja também [entrega não realizada](guide:missed-delivery), em inglês, e [encomendas não retiradas](guide:uncollected-parcel), em inglês.

**Alfândega.** Em encomendas vindas de fora da União Europeia com compras on-line, aplica-se desde 1º de julho de 2026 um imposto aduaneiro da UE de €3 por tipo de produto em pedidos de até €150, e desde 1º de novembro de 2026 uma taxa de manuseio da União de €2 por tipo de produto, qualquer que seja o valor. Os termos da Packlink determinam que o destinatário paga os impostos e taxas de importação, sem explicar como essas duas cobranças chegam até você. Veja [encomendas retidas na alfândega](guide:customs), em inglês.

## Encomenda Packlink não chegou, danificada ou devolvida

**Quem pode agir.** Pelos termos da Packlink, apenas quem contratou e pagou o frete pode abrir reclamações e consultas, usando o e-mail da reserva. Um terceiro só tem acesso mediante declaração juramentada do remetente autorizando. Assim, se você é o comprador, quem aciona a Packlink é o vendedor.

**Sem atualização.** Enquanto constar `Waiting for carrier to collect the order`, a transportadora ainda não coletou: confirme com o remetente se houve entrega do volume. Para atrasos ou falta de atualização, o remetente deve falar com a Packlink. Mais em [por que o rastreamento para de atualizar](guide:tracking-not-updating), em inglês.

**Extraviada.** A Packlink só analisa indenização por extravio depois que a transportadora conclui a busca nos depósitos e declara a encomenda perdida, processo que normalmente leva até 30 dias a partir da data prevista. Veja [encomendas extraviadas](guide:lost-parcel), em inglês.

**Danificada.** Havendo dano visível na entrega, escreva *Con Daños* (com danos) no comprovante ou aparelho do entregador, como exige a Packlink — sem essa anotação, ela informa que a reclamação não poderá ser tratada de forma satisfatória. Guarde o item e toda a embalagem para inspeção: a Packlink rejeita reclamações se o produto for alterado ou reparado, ou se a embalagem for descartada. Para perda ou dano oculto, o remetente deve abrir a reclamação em até 7 dias no transporte rodoviário (na Espanha ou exterior), 10 dias no aéreo nacional e 14 dias no aéreo internacional, contados do dia seguinte à entrega. Veja [encomendas danificadas](guide:damaged-parcel), em inglês.

**Devolvida.** `Package returned to sender` significa que o pacote voltou ao remetente. Peça reenvio ou reembolso. Veja [devolvida ao remetente](guide:returned-to-sender), em inglês.

**Entregue mas não recebida.** Confira com vizinhos e no ponto de retirada e avise o remetente imediatamente. Veja [entregue mas não recebida](guide:delivered-not-received), em inglês.

## Telefone e contato da Packlink

Canais publicados pela Packlink:

- **Telefone:** não existe. A Packlink afirma operar somente on-line, sem atendimento telefônico, e alerta que telefones atribuídos à Packlink em outros sites não são verdadeiros nem verificados por ela — não ligue para eles.
- **Formulário e chat:** na central de ajuda da Packlink, acessível pela página de telefone. Selecione a categoria correta e tenha em mãos a referência do e-mail de confirmação. A Packlink também disponibiliza chat ao vivo.
- **Reclamações:** pela mesma central de ajuda, exclusivamente por quem fez a reserva.

## Como rastrear uma encomenda Packlink no Peek

Cole a referência Packlink no [Peek](/). O formato é exclusivo da Packlink, então a tela de adição já mostra Packlink como "Transportadora detectada". Você também pode colar o número próprio da transportadora: o Peek acompanha mais de 4.500 transportadoras. Se o formato coincidir com várias empresas — como os 14 dígitos da SEUR — o Peek exibe "Consultando serviços de rastreamento…" enquanto verifica; toque em "Trocar" se preferir escolher você mesmo.

Com a encomenda aberta no Peek, ou no dia da entrega com notificações ligadas, o Peek lê o rastreamento da Packlink a cada 10 minutos no máximo, e a cada 2 minutos a partir das 6h (horário de Madri) no dia em que sai para entrega, enquanto o rastreador da Packlink responder; fora disso, de hora em hora. Após dois dias sem novidade (12 horas em "Etiqueta criada" ou "Recebida pela transportadora"), consulta de hora em hora, e a cada 6 horas após sete dias em "Etiqueta criada". Depois de quatro dias sem nova linha em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito", destaca a data da última linha, exibe "Sem atualizações desde …", a nota "É comum uma encomenda ficar sem atualizações em voos longos, na alfândega ou no fim de semana. O Peek continua consultando Packlink." e o link "Pedir ajuda a Packlink"; após três semanas, passa para "Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, fale com o vendedor. O Peek continua consultando Packlink." Cada leitura aparece nas palavras da transportadora quando a Packlink as repassa (ou no texto em inglês da Packlink), com horários interpretados em Madri e exibidos no fuso do seu dispositivo. Leituras de ponto de retirada aparecem como "Pronta para retirada", e `RECIBIDO EN ORIGEN` como "Recebida pela transportadora". A data prevista aparece enquanto está em trânsito, e remessas com vários volumes aparecem num único histórico. O Peek omite os locais (sem mapa) e a contagem de volumes; a Packlink não informa peso, remetente nem destinatário.

O rastreamento da Packlink também traz o código próprio da transportadora. Quando esse formato identifica uma única empresa com certeza — como um código UPS iniciado por `1Z` — o Peek consulta essa empresa também e passa a segui-la assim que surgir uma leitura tão recente quanto a última da Packlink, mantendo o histórico anterior: o cartão mostra "Entrega por UPS" e exibe ambos os números nos detalhes. Como os formatos de SEUR e Correos Express não são exclusivos, seus pacotes continuam acompanhados pela Packlink.

O Peek pode notificar você em "Cada movimentação", "Etapas importantes" ou "Só a entrega", mas não abre reclamações, não altera entregas nem contata terceiros por você.

## Perguntas frequentes sobre o rastreamento Packlink

### A Packlink é uma transportadora?

Não. A Packlink é uma plataforma onde pessoas e lojas comparam e contratam transportadoras. A transportadora escolhida — como SEUR, Correos Express ou UPS — faz a coleta e a entrega.

### Qual é o telefone da Packlink?

Nenhum: a Packlink atua apenas on-line e alerta que números em outros sites não são verificados por ela. Use o formulário da central de ajuda ou o chat ao vivo.

### Onde fica meu código de rastreamento Packlink?

No e-mail de confirmação enviado pela Packlink ao contratar o frete: duas letras como `ES`, o ano, `COM` ou `PRO` e dez dígitos. Se você for o comprador, peça ao remetente.

### Por que meu rastreamento Packlink diz Expired?

`Expired, there is no tracking information for the last 30 days` significa que a transportadora ficou um mês sem novidades e a Packlink deixou de acompanhar. Não quer dizer necessariamente que a encomenda se perdeu: peça ao remetente para acionar a Packlink.

:::sources
- [Packlink: Seguimiento envios](https://www.packlink.es/seguimiento-envios/) – tracking by the Packlink reference or the carrier's number, references starting with ES, carriers listed, Packlink PRO
- [Packlink: ¿Y cómo funciona esto de Packlink?](https://www.packlink.es/como-funciona/) – comparing and booking carriers, labels, Packlink PRO integrations with eBay and Amazon
- [Packlink: Teléfono Packlink](https://www.packlink.es/telefono-packlink/) – comparison site, no phone support, numbers on other websites not verified, contact form, live chat, reference in the confirmation email
- [Packlink: Términos y Condiciones](https://www.packlink.es/terminos-condiciones/) – company in Madrid, only the person who booked can claim, home or pickup point, notices, attempts and their cost, collection deadline, refused parcels, import duties, claim deadlines, damage reservations, 30-day search
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – €3 customs duty per kind of product up to €150 since 1 July 2026, €2 Union handling fee since 1 November 2026
- [European Commission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – €2 per kind of product whatever the value, for goods sold at a distance
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – reference shape, carriers' number shapes, status wordings and codes, Madrid time, what Peek reads from Packlink's tracking, hand-off to the carrier
:::
