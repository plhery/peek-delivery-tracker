---
title: Rastreio Post Haste: códigos NZ, estados e apoio
description: Acompanha uma encomenda Post Haste Couriers na Nova Zelândia: formatos de tickets e etiquetas, estados Freightways, nova entrega e filiais.
slug: rastreio-post-haste
published: 2026-11-24
updated: 2026-11-24
---

A **Post Haste** (*Post Haste Couriers*) é uma rede de estafetas da Nova Zelândia que integrou o **grupo Freightways** em 1977 e trabalha em parceria com a Castle Parcels ([posthaste.co.nz](https://posthaste.co.nz/)). Servindo entregas empresariais e residenciais nas Ilhas do Norte e do Sul através de mais de 20 filiais regionais e parceiros Delivery Link, a Post Haste assegura envios locais, regionais no dia seguinte, inter-ilhas no dia seguinte e económicos em dois dias, além de zonas rurais. Podes consultar uma encomenda em [posthaste.co.nz/tools/track/](https://posthaste.co.nz/tools/track/) ou colar a tua ligação de rastreio Post Haste ou código de envio na caixa acima.

## Números de rastreio Post Haste (tickets, etiquetas e ligações)

A Post Haste usa vários formatos de etiqueta Freightways consoante o remetente tenha utilizado um ticket/saqueta pré-pago, uma etiqueta faturada de 8 dígitos, um código de barras SSCC de 20 dígitos ou uma etiqueta impressa pelo cliente (como Despatch IT):

| Formato (inventado) | O que é |
| --- | --- |
| `PH48291736` | Ticket de estafeta ou saqueta Express Pack: código de produto de 2–4 letras (por exemplo `PE` ou `PH`) + número de ticket de 5–8 dígitos (introduzir sem espaços). |
| `73918462` | Número de etiqueta faturada Post Haste de 8 dígitos. |
| `KLM4829173` | Número de expedição impresso pelo cliente (letras + 6–8 dígitos); algumas etiquetas impressas pelo remetente exigem também o número de conta do remetente contido na ligação de seguimento do email (`receiver.posthaste.co.nz/?consignmentid=CU..…`). |

Se a loja te enviou uma ligação `receiver.posthaste.co.nz` ou `posthaste.co.nz` por email ou SMS, **cola essa ligação completa diretamente no Peek** — transmite automaticamente tanto o número de expedição como o código de conta do remetente quando necessário. Consulta o guia de [formatos de números de seguimento](guide:tracking-number-formats).

## O que significam os estados de rastreio da Post Haste

A Post Haste funciona na plataforma partilhada de seguimento da Freightways (usada pela Post Haste, Castle Parcels, New Zealand Couriers e Now Couriers) e apresenta tipos e subtipos de registo junto do bairro ou região:

| Código ou registo Post Haste | O que significa para ti | O Peek mostra |
| --- | --- | --- |
| `Label created` | Etiqueta eletrónica criada pelo sistema de expedição do remetente antes da recolha. | «Etiqueta criada» |
| `P` (`Picked Up`) | Recolhida ao remetente por um estafeta da Post Haste. | «Aceite pela transportadora» |
| `STAT/RZDEL` | Aviso de zona rural ou não urbana; a passagem a um parceiro rural acrescenta normalmente 2–3 dias úteis. | «Em trânsito» |
| `STAT/REDEL` | Reagendada e enviada novamente após uma tentativa de entrega anterior falhada. | «Em trânsito» |
| `D` (`Delivery Handed Over` / `Delivered by courier or branch` para parceiro rural) | Entregue a um subcontratado de distribuição rural; ainda a caminho da tua morada e não à tua porta. | «Em trânsito» |
| `ONBD` | A bordo da carrinha do estafeta local para entrega hoje. | «Em distribuição» |
| `NOND` / `NOND/*` | Incidente de não entrega (ninguém em casa com aviso deixado, instalações fechadas ou problema de acesso). | «Tentativa de entrega» |
| `D`, `3P/D` | Entregue na morada do destinatário (incluindo `3P/D` concluída por um parceiro rural). | «Entregue» |

`STAT/HLDFR8` significa que a encomenda ficou retida no balcão de carga da filial após uma entrega não concluída. Não indica se aguarda levantamento ou nova tentativa: agenda uma nova entrega ou contacta a filial.

## Prazos, zonas rurais e nova entrega na Nova Zelândia

De acordo com [Post Haste Sending & Receiving](https://posthaste.co.nz/services/sending-and-receiving/), [Redelivery](https://posthaste.co.nz/tools/redelivery/) e [Receiving Parcels Help](https://help.posthaste.co.nz/category/receiving-parcels/):

- **Prazos publicados:** serviço **local** entre empresas na mesma cidade; **regional no dia seguinte** (normalmente até à hora de almoço do dia útil seguinte); **nacional no dia seguinte** dentro e entre as Ilhas do Norte e do Sul (habitualmente até ao meio-dia do dia útil seguinte para empresas, até ao fim do dia para particulares); **económico inter-ilhas em dois dias**; e **entrega ao sábado** nas principais áreas metropolitanas.
- **Moradas não urbanas e rurais:** a Post Haste cobre a maioria das moradas rurais da Nova Zelândia através de parceiros rurais (`STAT/RZDEL`), o que acrescenta dois a três dias úteis aos prazos urbanos.
- **Ausência e nova entrega:** a regra padrão da Post Haste é **não** deixar encomendas numa morada sem ninguém presente, salvo autorização prévia (*authority to leave*). Se falhares uma entrega, marca nova entrega online em [posthaste.co.nz/tools/redelivery/](https://posthaste.co.nz/tools/redelivery/) com a referência de entrega de 16 dígitos, ou contacta a tua filial se não a tiveres. Leva um documento de identificação para levantar na filial.

## Encomenda Post Haste atrasada, retida ou desaparecida

- **A passagem rural parece «entregue» noutros rastreadores:** se um registo indicar `Delivered by courier or branch` ou `Delivery Handed Over`, a encomenda foi habitualmente entregue a um parceiro rural (`3P`) e ainda não chegou ao teu portão — aguarda 2–3 dias úteis pela volta rural (`3P/D`). O Peek mantém essas passagens em «Em trânsito» para não as marcar entregues antes do tempo.
- **Sem registos após a etiqueta ou parada no depósito:** se uma expedição impressa pelo cliente não aparecer apenas com o número, cola a ligação completa `receiver.posthaste.co.nz` do email de envio para o Peek enviar o código de conta do remetente. Para encomendas paradas numa filial, contacta o depósito local Post Haste ou o vendedor.
- **Reclamações por perda ou dano:** consulta as orientações de perda e danos em [help.posthaste.co.nz/category/receiving-parcels/](https://help.posthaste.co.nz/category/receiving-parcels/) e contacta primeiro a loja que contratou o envio. Vê também [entregue mas não recebida](guide:delivered-not-received), [encomenda perdida](guide:lost-parcel), [encomenda danificada](guide:damaged-parcel), [devolvida ao remetente](guide:returned-to-sender) e [quando o rastreio deixa de atualizar](guide:tracking-not-updating).

## Apoio ao cliente e filiais da Post Haste na Nova Zelândia

Contactos e diretórios oficiais publicados em [posthaste.co.nz/about-us/contact/](https://posthaste.co.nz/about-us/contact/) e [help.posthaste.co.nz/category/receiving-parcels/](https://help.posthaste.co.nz/category/receiving-parcels/):

- **Diretório de filiais locais (Ilhas do Norte e do Sul):** [posthaste.co.nz/about-us/contact/](https://posthaste.co.nz/about-us/contact/) lista telefones e moradas das filiais da Ilha do Norte (Whangārei, North Harbour, Auckland, Hamilton, Tauranga, Rotorua, New Plymouth, Taupō, Gisborne, Napier, Hastings, Whanganui, Palmerston North, Wellington) e da Ilha do Sul (Nelson, Blenheim, West Coast, Christchurch, Timaru, Dunedin, Queenstown, Invercargill), incluindo filiais partilhadas com a Castle Parcels.
- **Ferramenta online de nova entrega:** [posthaste.co.nz/tools/redelivery/](https://posthaste.co.nz/tools/redelivery/).
- **Centro de ajuda e regras para destinatários:** [help.posthaste.co.nz/category/receiving-parcels/](https://help.posthaste.co.nz/category/receiving-parcels/).

> Desconfia de SMS falsos que alegam ser necessário um pequeno pagamento por cartão para reagendar uma entrega na Nova Zelândia. Usa sempre [posthaste.co.nz/tools/redelivery/](https://posthaste.co.nz/tools/redelivery/) ou confirma diretamente com a tua filial Post Haste. Consulta [mensagens SMS fraudulentas de entrega](guide:delivery-scam-texts).

## Acompanhar uma encomenda Post Haste com o Peek

A forma mais simples de adicionar um envio Post Haste no [Peek](/) é colar a ligação completa do email de expedição (`receiver.posthaste.co.nz/?consignmentid=CU..…` ou `posthaste.co.nz/track?query=`), que o Peek reconhece automaticamente como Post Haste e usa para extrair a conta do remetente quando necessário. Se introduzires um número isolado de ticket, etiqueta faturada ou expedição (`PH48291736`, `73918462` ou `KLM4829173`, inventados), o Peek não reconhece a Post Haste só pelo número (pergunta à TNT Itália por um ticket como `PH48291736` e à Australia Post por uma expedição como `KLM4829173`), pelo que deves tocar em «Alterar» ao adicionar ou em «Alterar transportadora» no ecrã da encomenda e escolher **Post Haste** (pesquisar «Post Haste Couriers» ou «Posthaste» também encontra). O Peek disponibiliza então o campo opcional «Ligação de seguimento completa» para a ligação do email, que contém a conta do remetente necessária para algumas encomendas.

O Peek lê a consulta Freightways sob o nome Post Haste e apresenta a etapa («Etiqueta criada», «Aceite pela transportadora», «Em trânsito», «Em distribuição», «Tentativa de entrega», «Entregue»), o texto do registo, o bairro e a região, o dia estimado de entrega quando a Post Haste o indica e a hora de entrega. Os tipos e subtipos de registo determinam a etapa e não surgem nas aplicações Peek. As horas chegam como instantes UTC e aparecem na hora do teu dispositivo. Quando outra rede Freightways, como a Castle Parcels, faz o registo mais recente, a encomenda apresenta «Entrega:» seguido do nome dessa rede. O Peek não guarda assinatura nem nome de quem assinou, fotos de entrega, marca de autorização de entrega sem assinatura, códigos postais nem o motorista.

Enquanto tens a encomenda aberta no Peek, ou no dia da entrega com as notificações ativadas, o Peek consulta até de 10 em 10 minutos; no dia em que a Post Haste apresenta `ONBD`, de 2 em 2 minutos a partir das 6h00 hora da Nova Zelândia enquanto o rastreio da Post Haste responder. Caso contrário, o Peek verifica de hora a hora. Após 12 horas apenas em «Etiqueta criada» ou «Aceite pela transportadora», ou dois dias sem novidades, verifica de hora a hora independentemente da forma como acompanhas a encomenda, e de 6 em 6 horas após sete dias em «Etiqueta criada». Quando o rastreio da Post Haste não responde e o Peek acompanha a encomenda apenas através de serviços de rastreio, verifica de hora a hora, de 3 em 3 horas após um dia sem novidades e até de 15 em 15 minutos no dia em que a encomenda está em distribuição, enquanto tiveres a encomenda aberta ou as notificações ativadas.

Quando o registo mais recente tem quatro dias e a encomenda continua em «Etiqueta criada», «Aceite pela transportadora» ou «Em trânsito», o Peek assinala-a com o dia desse registo, como «Sem atualizações desde seg 12 out». A página da encomenda apresenta então «É habitual uma encomenda ficar sem novidades em voos longos, na alfândega ou ao fim de semana. O Peek continua a perguntar a Post Haste.», com a ligação «Pede ajuda a Post Haste» para a página de ajuda a destinatários da Post Haste. Passadas três semanas sem novos registos, mostra «Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, contacta o vendedor. O Peek continua a perguntar a Post Haste.»

Podes escolher alertas para «Todos os registos», «Passos importantes» ou «Só a entrega». O Peek acompanha apenas as atualizações de rastreio e não pode agendar novas entregas, autorizar entrega sem presença nem contactar a tua filial Post Haste por ti.

## Perguntas sobre o rastreio Post Haste

### Que números de rastreio funcionam na Post Haste?

A Post Haste acompanha tickets de estafeta e saquetas Express Pack (2–4 letras + 5–8 dígitos, como `PH48291736`, inventado), etiquetas faturadas de 8 dígitos (`73918462`, inventado), códigos de barras SSCC de 20 dígitos e números de expedição impressos pelo cliente (`KLM4829173`, inventado) — ou cola a ligação completa `receiver.posthaste.co.nz` do email de envio.

### Porque é que o meu número de expedição impresso pelo cliente não aparece?

Algumas etiquetas impressas pelo cliente (como expedições Despatch IT) estão associadas ao número de conta do remetente. Cola a ligação completa `receiver.posthaste.co.nz` do email de notificação de envio para incluir o código de conta na pesquisa.

### O que faço se falhei a entrega da Post Haste?

A Post Haste requer normalmente alguém presente salvo autorização prévia para deixar a encomenda. Usa o formulário online em [posthaste.co.nz/tools/redelivery/](https://posthaste.co.nz/tools/redelivery/) com a referência de entrega de 16 dígitos, ou liga para a filial local em [posthaste.co.nz/about-us/contact/](https://posthaste.co.nz/about-us/contact/).

### Porque é que o rastreio Post Haste mostra `Delivery Handed Over` ou `STAT/RZDEL`?

`STAT/RZDEL` indica uma morada rural ou não urbana, e um registo de passagem significa que a encomenda foi entregue a um parceiro rural — acrescentando normalmente 2–3 dias úteis até à entrega final à porta (`3P/D`).

:::sources
- [Post Haste: Official Website](https://posthaste.co.nz/) – rede nacional de estafetas da Nova Zelândia (Freightways Group)
- [Post Haste: Our Story](https://posthaste.co.nz/about-us/our-story/) – aquisição pela Freightways (1977) e parceria com a Castle Parcels
- [Post Haste: Sending & Receiving Services](https://posthaste.co.nz/services/sending-and-receiving/) – serviços local, regional no dia seguinte, nacional no dia seguinte, económico inter-ilhas em dois dias, sábado e entregas rurais
- [Post Haste: Advanced Track & Trace](https://posthaste.co.nz/tools/track/) – etiquetas impressas pelo cliente (com conta da empresa), tickets/Express Packs, códigos de barras e sacos de contrato
- [Post Haste: Redelivery](https://posthaste.co.nz/tools/redelivery/) – pedidos online de nova entrega e documento de identificação para levantamento em filial
- [Post Haste: Contact & Branches](https://posthaste.co.nz/about-us/contact/) – diretório de filiais das Ilhas do Norte e do Sul
- [Post Haste: Receiving Parcels Help](https://help.posthaste.co.nz/category/receiving-parcels/) – política de ausências, notificações e normas de perda/dano (página de ajuda ligada pelo Peek)
- [Universal Parcel Scraper: post-haste](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/post-haste) – pesquisa Freightways de tickets/etiquetas/SSCC/expedições, regras de ligação `receiver.posthaste.co.nz`, mapa de códigos (`P`, `ONBD`, `NOND`, `STAT/RZDEL`, `D`, `3P/D`) e leitura da data estimada
:::
