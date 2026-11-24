---
title: Rastreamento India Post: códigos Speed Post e status
description: Rastreie envios da India Post ou Speed Post: formato do código IN, significado dos status, prazos, encomendas internacionais, reclamações e telefone.
slug: rastreamento-india-post
published: 2026-11-24
updated: 2026-11-24
---

A India Post (Department of Posts) é o serviço postal nacional da Índia, com 164.999 agências — segundo ela, a maior rede postal do mundo. Dentro da Índia, opera Speed Post, correspondência registrada e India Post Parcel; para o exterior, EMS (International Speed Post), pacotes rastreados e encomendas aéreas. Rastreie na caixa em [indiapost.gov.in](https://www.indiapost.gov.in/), que exige CAPTCHA, ou cole o código na caixa acima. A India Post chama o número de article barcode number; em compras online, peça ao vendedor.

## Código de rastreamento da India Post: qual é o formato

Um código da India Post tem 13 caracteres no [formato postal internacional](guide:tracking-number-formats): duas letras, nove dígitos e `IN`. O último dos nove dígitos é verificador. Com dígitos fictícios:

| Formato (fictício) | O que é |
| --- | --- |
| `EE482910576IN` | Item numerado pela India Post, doméstico ou enviado ao exterior. As duas letras iniciais variam; o `IN` final é o que identifica a India Post. |
| `EE482910576US` | Termina em outras letras (aqui `US`): emitido pelos correios de outro país. Pacotes chegando à Índia do exterior geralmente seguem esse formato. |

O rastreador da India Post cobre Speed Post, cartas registradas e seguradas, Business e Express Parcels, ordens postais eletrônicas e EMS internacional. Não encontrou o código? Veja [como encontrar um código de rastreamento](guide:find-tracking-number).

## Status de rastreamento da India Post explicados

O FAQ da India Post resume as etapas como postado, recebido, ensacado, despachado, saiu para entrega e entregue. Estas são as linhas que o Peek conhece da India Post, aproximadamente em ordem:

| A India Post diz | O que significa para você |
| --- | --- |
| `Item Booked` | O remetente postou na agência ou cadastrou online e a India Post aceitou o objeto. |
| `Bag Received for Forwarding` | A mala postal com o item chegou a uma unidade para encaminhamento. |
| `Assigned to load plan` | Programado para o próximo transporte. |
| `Transport Leg Completed` | Um trecho do trajeto foi concluído. |
| `Transfer to OOE (Otb)` | Seguindo para o centro internacional de saída (Office of Exchange). |
| `Aircraft Departure` | O voo levando a encomenda decolou. Às vezes aparece como `UPLIFT`. |
| `Bag unloaded at destination Airport` | A mala postal foi descarregada do avião no país de destino. |
| `Send item to domestic location (Inb)` | O centro internacional do destino encaminhou o objeto à rede interna local. |
| `Item Delivered` | Entregue. |

`(Otb)` marca leituras dos correios de origem; `(Inb)`, dos correios de destino repassadas pela India Post. Nos dados lidos pelo Peek, a India Post hoje costuma enviar códigos como `BAG_DISPATCH`; o Peek converte os conhecidos em frases claras (como "Bag Dispatched" ou "Item Presented To Customs") e exibe os demais em palavras legíveis. Veja mais em [o que significam os status de rastreamento](guide:tracking-statuses).

## Recebendo um envio da India Post na Índia

**Prazos de entrega.** Os padrões do Speed Post desde a postagem são: 1 a 2 dias localmente, 1 a 3 entre metrópoles, 1 a 4 entre capitais estaduais ou dentro do mesmo estado e 4 a 5 para o restante do país, mais um dia onde uma agência rural entrega. O 24 Speed Post entrega no dia seguinte em seis metrópoles (Nova Délhi, Mumbai, Bengaluru, Chennai, Calcutá e Hyderabad), sete dias por semana; em caso de atraso, o remetente pode pedir reembolso da postagem online em até 7 dias.

**SMS e OTP.** Em encomendas Speed Post, o celular cadastrado recebe SMS na chegada à agência entregadora e na entrega. Alguns envios exigem código único (OTP) na entrega — incluso no 24 Speed Post e opcional pago nos demais.

**Entrega sem sucesso.** O rastreador registra quando um Delivery Intimation Notice é emitido avisando que o objeto está disponível. Veja [entregas sem sucesso](guide:missed-delivery) e [encomendas não retiradas](guide:uncollected-parcel).

**Alteração de endereço.** O remetente que cadastrou online em uma conta India Post pode solicitar devolução ou redirecionamento antes da entrega ou da saída com o carteiro.

## Encomendas da India Post no exterior: entrega e alfândega

A India Post oferece EMS (até 35 kg), International Tracked Packet para lojas online (até 5 kg) e International Air Parcel (até 20 kg), todos rastreados. Após a chegada ao destino, leituras `(Inb)` vêm dos correios do seu país: acompanhe lá com o mesmo código `IN`, em [USPS](carrier:usps), [Royal Mail](carrier:royal-mail) (Parcelforce para EMS), An Post, Canada Post ou Australia Post.

**Alfândega na UE.** A Índia está fora da União Europeia, portanto a encomenda passa pela alfândega no país de destino. Desde 1º de julho de 2026, compras online fora da UE até € 150 pagam € 3 de imposto alfandegário por item; desde 1º de novembro de 2026, incide também a taxa de manuseio da UE de € 2 por item, qualquer que seja o valor (um item reúne mercadorias com mesma classificação tarifária, descrição e, quando exigido, origem). A India Post lista Delivery Duty Paid (DDP) como opcional para exportadores: confirme com o vendedor se foi usado. Em outros países valem as regras locais: veja [encomendas paradas na alfândega](guide:customs).

**Suspensões de serviço.** A India Post publica avisos quando suspende ou retoma envios para determinado país (como a suspensão ao Canadá em outubro de 2025 ou a retomada para Iraque, Kuwait e Líbano em agosto de 2026) em [News and Updates](https://www.indiapost.gov.in/media/newsandupdates).

**Chegando à Índia.** Pacotes enviados do exterior mantêm o código dos correios de origem. Rastreie lá primeiro; o FAQ da India Post alerta que dados vindos de correios estrangeiros podem demorar.

## Rastreamento da India Post parado, extravio ou devolução

**Sem leituras há algum tempo.** A India Post afirma que a ausência temporária de leitura não significa que o item não foi enviado: eventos podem demorar a aparecer, sobretudo em áreas rurais ou vindos do exterior. Assim que um envio internacional mostrar `Aircraft Departure`, consulte os correios do país de destino com o mesmo número. Veja [por que o rastreamento para de atualizar](guide:tracking-not-updating).

**Extraviado, atrasado ou danificado.** Reclame após o prazo padrão do serviço e em até 60 dias da transação informando o código do objeto; o prazo padrão de análise da India Post é de 90 dias. O Speed Post doméstico reembolsa a postagem por atraso e paga o dobro do frete ou ₹ 1.000 (o menor valor) por extravio, violação ou avaria. Mercadorias em EMS têm cobertura até o valor do bem ou 130 SDR (o menor) mais a postagem, e atrasos superiores a 5 dias além da norma geram reembolso de 5% do frete. International Tracked Packet cobre até o valor ou ₹ 1.000 (o menor). Comprou online? Avise também a loja: veja [encomendas extraviadas](guide:lost-parcel) e [encomendas danificadas](guide:damaged-parcel).

**Marcado como entregue mas não chegou.** Registre reclamação pelos canais abaixo ou no livro de reclamações presente em toda agência postal: veja [marcado como entregue mas não recebido](guide:delivered-not-received).

**Devolvido.** Ao postar online, o remetente pode informar um endereço de devolução diferente do seu. O Peek mostra a devolução como "Devolvida ao remetente": veja [devolvidas ao remetente](guide:returned-to-sender).

## Telefone de atendimento e reclamações da India Post

Canais oficiais da India Post:

- **Atendimento gratuito (toll-free):** 1800 266 6868. Atendentes respondem das 8h às 20h (horário da Índia); a central automática funciona 24 horas por dia, inclusive domingos, exceto feriados oficiais.
- **Reclamações:** online pela página [File a Complaint](https://www.indiapost.gov.in/grievance-redressal/file-a-complaint) (sem precisar de conta e com acompanhamento do status), presencialmente em uma agência (ao chefe da agência ou Superintendent of Post Offices) ou pela central telefônica.
- **Escalonamento:** se a India Post não resolver em prazo razoável, acione o Directorate of Public Grievances do governo indiano (dpg.gov.in).

A India Post alerta sobre links falsos que se passam por ela: acesse apenas www.indiapost.gov.in e nunca informe OTP nem dados bancários, de cartão ou UPI por links. Veja [golpes de entrega por SMS](guide:delivery-scam-texts).

## Como rastrear uma encomenda da India Post com o Peek

O [Peek](/) reconhece duas letras, nove dígitos e `IN` como India Post quando o dígito verificador está correto e mostra "Transportadora detectada"; "Trocar" escolhe outra. Com dígito verificador incorreto, avisa "O dígito verificador deste código não confere.": confira o número ou escolha "Rastrear como digitado". Registrada sob India Post, a encomenda exibe "Aguardando India Post" até a primeira leitura.

O Peek não consulta diretamente o site da India Post: lê as leituras pelo MySpeedPost, site de rastreamento fora do domínio da India Post que busca os dados dela (ou pelo track91, rastreador da mesma operadora, quando o MySpeedPost encaminha a consulta para lá). Quando a cópia tem mais de 30 minutos, o Peek solicita atualização ao MySpeedPost e mantém a cópia anterior se não conseguir atualizar, então uma leitura pode chegar com certo atraso ao Peek.

O Peek exibe cada evento no texto da India Post, com a agência e, na Índia, seu código PIN (o track91 não inclui PIN), marcando a agência no mapa quando o local é verificado. Decolagens aparecem como "Aircraft Departure", ou com número do voo e aeroportos quando informados. O Peek classifica eventos em etapas próprias: falha de entrega aparece como "Tentativa de entrega", e recusa, avaria, extravio ou endereço insuficiente como "Precisa de atenção". Via track91, as linhas são mais curtas: `Dispatched` (enviado ao exterior) e `Aircraft arrival in Transit country` (pousou em trânsito) aparecem como "Em trânsito", e `Delivered` como "Entregue". O Peek remove nome do destinatário, endereço e assinatura, e não mostra data prevista. Leituras na Índia seguem o horário indiano; leituras no exterior seguem o fuso do destino a partir do pouso ou da primeira leitura `(Inb)` (tanto via MySpeedPost quanto track91), convertidas para o fuso horário do seu dispositivo, informado em uma linha ao final do histórico em peektracker.com e nos apps iPhone e Android.

Quando a India Post informa o país de destino — ou não informa e você rastreia fora da Índia — o Peek também consulta uma transportadora conhecida nesse país com o mesmo código `IN`, como USPS, Royal Mail, Canada Post, Australia Post, [DHL](carrier:dhl) na Alemanha ou [La Poste](carrier:la-poste) na França, passando para ela assim que surgem leituras mais novas e mantendo o histórico da India Post. O Peek cobre a maior parte da Europa e outros países (incluindo NZ Post na Nova Zelândia e Qatar Post no Catar), mas não Emirados Árabes, Kuwait, Omã, Singapura, Malásia ou Sri Lanka. Ele não consulta a India Post para códigos emitidos por outros correios: escolher India Post para um desses exibe "não emite códigos como este".

Enquanto o pacote estiver aberto, ou no dia da entrega com alertas ligados, o Peek verifica até a cada 10 minutos e até a cada 2 minutos quando sai para entrega; nos demais períodos, e após dois dias sem novidades (12 horas enquanto ainda mostra "Etiqueta criada" ou "Recebida pela transportadora"), verifica de hora em hora. As consultas a cada 2 minutos rodam a partir das 6h (horário local) do dia da saída para entrega enquanto o MySpeedPost ou os correios locais respondem; após repasse, segue o ritmo dos correios do destino. Por meio de outros serviços de rastreamento, até a cada 15 minutos enquanto o pacote estiver aberto ou com alertas ligados. Alertas disponíveis: "Cada movimentação", "Etapas importantes" ou "Só a entrega". Se o pacote travar com a India Post, "Pedir ajuda a India Post" abre a página Contact Us oficial. O Peek não pode contatar a India Post, seus correios locais nem a loja por você.

## Perguntas sobre o rastreamento da India Post

### Como rastrear India Post Speed Post?

Digite o código de 13 caracteres terminado em `IN` em indiapost.gov.in ou cole no Peek, que acompanha o envio até a entrega, inclusive no exterior.

### Por que meu rastreamento da India Post não está atualizando?

Leituras podem demorar a aparecer, principalmente em agências rurais ou vindas de correios no exterior. Se um pacote enviado ao exterior parou em `Aircraft Departure`, já saiu da Índia: consulte os correios do país de destino com o mesmo código.

### Quanto tempo o Speed Post leva para entregar?

O padrão da India Post é de 1 a 3 dias entre metrópoles e 4 a 5 dias para o restante da Índia (mais um dia em agências rurais). O 24 Speed Post entrega no dia seguinte em seis metrópoles.

### Qual é o telefone de atendimento da India Post?

1800 266 6868 (ligação gratuita na Índia): atendentes das 8h às 20h no horário da Índia e linha automática 24 horas por dia, exceto feriados oficiais.

:::sources
- [India Post: Home](https://www.indiapost.gov.in/) – rastreador, linha de ajuda e alerta contra fraudes
- [India Post: Contact Us](https://www.indiapost.gov.in/help&Support/contactus) – telefone gratuito, horários e reclamações em agência
- [India Post: Help and FAQs](https://www.indiapost.gov.in/grievance-redressal/help-faq) – código de barras, serviços rastreados, status, atrasos, reclamações, redirecionamento e endereço de devolução
- [India Post: About Us](https://www.indiapost.gov.in/ministry/aboutus) – Department of Posts e rede de agências
- [India Post: Speed Post](https://www.indiapost.gov.in/mail&Parcel/mailproducts/premiumservices/speedpost) – OTP, prazos padrão, SMS do Speed Post Parcel e indenizações
- [India Post: 24/48 Speed Post FAQs](https://www.indiapost.gov.in/speedpost/faqs) – entrega no dia seguinte em seis metrópoles, OTP e reembolsos
- [India Post: India Post Parcel](https://www.indiapost.gov.in/mail&Parcel/mailproducts/domesticservices/indiapostparcel) – India Post Parcel
- [India Post: International Mail](https://www.indiapost.gov.in/mail&Parcel/mailproducts/mailservices/international) – serviços internacionais e DDP para exportadores
- [India Post: International services](https://www.indiapost.gov.in/mail&Parcel/mailproducts/internationalservices) – EMS, pacotes rastreados, encomendas aéreas e indenizações
- [India Post: News and Updates](https://www.indiapost.gov.in/media/newsandupdates) – avisos de suspensão e retomada por país
- [India Post: File a Complaint](https://www.indiapost.gov.in/grievance-redressal/file-a-complaint) – registro de reclamações e escalonamento
- [India Post: Guidelines on Complaint Registration](https://www.indiapost.gov.in/grievance-redressal/guidelines) – prazos e canais de reclamação
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – tarifa de € 3 e taxa de manuseio da UE de € 2
- [European Commission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – cobrança por item e definição de item
:::
