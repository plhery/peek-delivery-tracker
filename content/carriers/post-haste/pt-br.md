---
title: Rastreamento Post Haste: códigos na Nova Zelândia e ajuda
description: Rastreie encomendas da Post Haste Couriers na Nova Zelândia: formatos de tíquetes, etiquetas e códigos, status Freightways, reentrega e filiais locais.
slug: post-haste-tracking
published: 2026-11-26
updated: 2026-11-26
---

A **Post Haste** (*Post Haste Couriers*) é uma rede de entregas da Nova Zelândia que integrou o **Freightways Group** em 1977 e opera em parceria com a Castle Parcels ([posthaste.co.nz](https://posthaste.co.nz/)). Atendendo entregas comerciais e residenciais nas Ilhas Norte e Sul por meio de mais de 20 filiais regionais e parceiros Delivery Link, a Post Haste realiza entregas locais, regionais overnight, overnight entre ilhas e econômicas em dois dias, além de áreas rurais. Você pode consultar um pacote em [posthaste.co.nz/tools/track/](https://posthaste.co.nz/tools/track/) ou colar o link de rastreamento ou o código da remessa na caixa acima.

## Códigos de rastreamento da Post Haste (tíquetes, etiquetas e links de e-mail)

A Post Haste usa vários formatos de etiqueta da Freightways conforme o remetente utilize um tíquete/envelope pré-pago, uma etiqueta de cobrança de 8 dígitos, um código de barras SSCC de 20 dígitos ou uma etiqueta impressa pelo cliente (como Despatch IT):

| Formato (inventado) | O que é |
| --- | --- |
| `PH48291736` | Tíquete ou envelope Express Pack: código de produto de 2–4 letras (como `PE` ou `PH`) + número de 5–8 dígitos (digite sem espaços). |
| `73918462` | Etiqueta de cobrança (charge label) de 8 dígitos da Post Haste. |
| `KLM4829173` | Código de remessa impresso pelo cliente (letras + 6–8 dígitos); algumas etiquetas impressas pelo remetente também exigem o número da conta do remetente contido no link do e-mail de envio (`receiver.posthaste.co.nz/?consignmentid=CU..…`). |

Se a loja enviou um link de `receiver.posthaste.co.nz` ou `posthaste.co.nz` por e-mail ou SMS, **cole o link completo diretamente no Peek** — ele fornece automaticamente tanto o número da remessa quanto o código da conta do remetente quando necessário. Veja [formatos de código de rastreamento](guide:tracking-number-formats).

## O que significam os status de rastreamento da Post Haste

A Post Haste opera no sistema de rastreamento compartilhado da Freightways (usado por Post Haste, Castle Parcels, New Zealand Couriers e Now Couriers) e informa tipos e subtipos de leitura acompanhados do bairro/região:

| Código ou leitura da Post Haste | O que significa para você | O Peek mostra |
| --- | --- | --- |
| `Label created` | Etiqueta eletrônica gerada pelo sistema de expedição do remetente antes da coleta. | "Etiqueta criada" |
| `P` (`Picked Up`) | Coletada com o remetente por um entregador da Post Haste. | "Recebida pela transportadora" |
| `STAT/RZDEL` | Aviso de área rural ou não urbana; o repasse a parceiro rural costuma acrescentar 2–3 dias úteis. | "Em trânsito" |
| `STAT/REDEL` | Reagendada e enviada novamente após uma entrega anterior não concluída. | "Em trânsito" |
| `D` (`Delivery Handed Over` / `Delivered by courier or branch` para parceiro rural) | Repassada a um contratado de entrega rural; ainda está a caminho do seu endereço, e não entregue na porta. | "Em trânsito" |
| `ONBD` | A bordo da van do entregador local para entrega hoje. | "Saiu para entrega" |
| `NOND` / `NOND/*` | Ocorrência de não entrega (como ausência com aviso deixado, local fechado ou dificuldade de acesso). | "Tentativa de entrega" |
| `D`, `3P/D` | Entregue no endereço do destinatário (incluindo `3P/D` concluída por parceiro de entrega rural). | "Entregue" |

`STAT/HLDFR8` indica que a encomenda está retida no balcão de cargas da filial após uma tentativa sem entrega, sem informar se aguarda retirada ou nova entrega: agende a reentrega ou contate a filial.

## Prazos de entrega, zonas rurais e reentrega na Nova Zelândia

De acordo com [Envio e Recebimento da Post Haste](https://posthaste.co.nz/services/sending-and-receiving/), [Reentrega](https://posthaste.co.nz/tools/redelivery/) e [Ajuda para Receber Encomendas](https://help.posthaste.co.nz/category/receiving-parcels/):

- **Prazos divulgados:** **Local** dentro da cidade entre empresas; **Regional overnight** (geralmente até o almoço do dia útil seguinte); **Nacional overnight** dentro e entre as Ilhas Norte e Sul (em geral até o meio-dia do dia útil seguinte para endereços comerciais e até o fim do dia para residenciais); **Econômica entre ilhas em dois dias**; além de **entrega aos sábados** nas principais áreas metropolitanas.
- **Endereços não urbanos e rurais:** a Post Haste atende a maior parte das áreas rurais da Nova Zelândia por meio de parceiros rurais (`STAT/RZDEL`), o que acrescenta dois a três dias úteis aos prazos urbanos.
- **Imóveis sem ninguém e reentrega:** a política padrão da Post Haste é **não** deixar encomendas em endereços desacompanhados, salvo quando há autorização prévia (authority to leave). Se perdeu a entrega, solicite reentrega on-line em [posthaste.co.nz/tools/redelivery/](https://posthaste.co.nz/tools/redelivery/) com a referência de 16 dígitos, ou fale com a filial local caso não a tenha. Leve um documento de identidade para retirar na filial.

## Encomenda da Post Haste atrasada, retida ou extraviada

- **Repasse rural parece "entregue" em alguns rastreadores:** se uma leitura indicar `Delivered by courier or branch` ou `Delivery Handed Over`, o volume normalmente foi transferido para um entregador rural terceirizado (`3P`), e não deixado na sua porta — aguarde 2–3 dias úteis pela rota rural (`3P/D`). O Peek mantém repasses rurais em "Em trânsito" para não marcar como entregue antes da hora.
- **Sem leituras após a etiqueta ou parada na filial:** se um código impresso pelo cliente não aparecer sozinho, cole o link completo `receiver.posthaste.co.nz` do e-mail de expedição para que o Peek envie o código de conta do remetente. Em caso de volume parado em filial, contate a unidade local da Post Haste ou o remetente.
- **Reclamações por extravio ou dano:** consulte as orientações de perda e danos da Post Haste em [help.posthaste.co.nz/category/receiving-parcels/](https://help.posthaste.co.nz/category/receiving-parcels/) e fale primeiro com a loja que contratou a transportadora. Veja [entregue, mas não recebida](guide:delivered-not-received), [encomenda perdida](guide:lost-parcel), [encomenda danificada](guide:damaged-parcel), [devolvida ao remetente](guide:returned-to-sender) e [por que o rastreamento para de atualizar](guide:tracking-not-updating).

## Atendimento ao cliente e filiais da Post Haste na Nova Zelândia

Canais oficiais publicados em [posthaste.co.nz/about-us/contact/](https://posthaste.co.nz/about-us/contact/) e [help.posthaste.co.nz/category/receiving-parcels/](https://help.posthaste.co.nz/category/receiving-parcels/):

- **Diretório de filiais (Ilhas Norte e Sul):** [posthaste.co.nz/about-us/contact/](https://posthaste.co.nz/about-us/contact/) reúne telefones e endereços das filiais da Ilha Norte (Whangārei, North Harbour, Auckland, Hamilton, Tauranga, Rotorua, New Plymouth, Taupō, Gisborne, Napier, Hastings, Whanganui, Palmerston North e Wellington) e da Ilha Sul (Nelson, Blenheim, West Coast, Christchurch, Timaru, Dunedin, Queenstown e Invercargill), incluindo unidades compartilhadas com a Castle Parcels.
- **Ferramenta de reentrega on-line:** [posthaste.co.nz/tools/redelivery/](https://posthaste.co.nz/tools/redelivery/).
- **Central de ajuda e políticas para destinatários:** [help.posthaste.co.nz/category/receiving-parcels/](https://help.posthaste.co.nz/category/receiving-parcels/).

> Desconfie de mensagens SMS falsas afirmando que uma encomenda na Nova Zelândia exige pagamento com cartão para reagendar a entrega. Utilize sempre [posthaste.co.nz/tools/redelivery/](https://posthaste.co.nz/tools/redelivery/) ou confirme diretamente com a filial local da Post Haste. Veja [golpes de SMS de entrega](guide:delivery-scam-texts).

## Acompanhe uma encomenda da Post Haste com o Peek

A forma mais prática de adicionar um envio da Post Haste no [Peek](/) é colar o link completo de rastreamento do e-mail de envio (`receiver.posthaste.co.nz/?consignmentid=CU..…` ou `posthaste.co.nz/track?query=`), que o Peek reconhece automaticamente como Post Haste e utiliza para extrair a conta do remetente quando exigida. Se você digitar apenas um tíquete, etiqueta de cobrança ou código de remessa isolado (`PH48291736`, `73918462` ou `KLM4829173`, inventados), o Peek não identifica a Post Haste pelo número sozinho (consulta a TNT Itália para tíquetes como `PH48291736` e o Australia Post para códigos como `KLM4829173`); toque em "Alterar" ao adicionar ou em "Alterar transportadora" na tela da encomenda e selecione **Post Haste** (pesquisar "Post Haste Couriers" ou "Posthaste" também encontra). O Peek exibe então um campo opcional "Link completo de rastreamento" para colar o link do e-mail de envio com a conta do remetente necessária para alguns volumes.

O Peek lê a consulta da Freightways sob o nome da Post Haste e exibe a etapa ("Etiqueta criada", "Recebida pela transportadora", "Em trânsito", "Saiu para entrega", "Tentativa de entrega", "Entregue"), o texto da movimentação, o bairro e a região, o dia estimado de entrega quando informado pela Post Haste e o horário de entrega. Os tipos e subtipos de leitura definem a etapa e não aparecem nos aplicativos do Peek. Os horários vêm em UTC e são convertidos para o relógio do seu aparelho. Quando outra rede da Freightways, como a Castle Parcels, registra a leitura mais recente, a página mostra "Entrega por" seguido do nome dessa rede. O Peek não armazena assinatura nem nome de quem assinou, fotos de entrega, marcação de autorização para deixar no local, códigos postais nem o nome do motorista.

Enquanto você mantém a encomenda aberta no Peek, ou no dia da entrega com notificações ligadas, o Peek verifica até a cada 10 minutos; no dia em que a Post Haste registra `ONBD`, consulta a cada 2 minutos a partir das 6h no horário da Nova Zelândia, enquanto o rastreador da Post Haste responder. Caso contrário, verifica de hora em hora. Após 12 horas apenas em "Etiqueta criada" ou "Recebida pela transportadora", ou dois dias sem novidade, consulta a cada hora de qualquer forma, e a cada 6 horas depois de sete dias em "Etiqueta criada". Quando o rastreador da Post Haste não responde e o Peek acompanha somente por serviços de rastreamento, consulta de hora em hora, a cada 3 horas após um dia quieto e até a cada 15 minutos no dia da saída para entrega.

Quando a leitura mais recente tem quatro dias e o pacote permanece em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito", o Peek destaca o dia daquela movimentação, como "Sem atualização desde seg 12 out". A página passa a dizer: "É comum uma encomenda ficar sem atualizações em voos longos, na alfândega ou no fim de semana. O Peek continua consultando Post Haste.", com o link "Pedir ajuda a Post Haste" para a central de ajuda ao destinatário da Post Haste. Após três semanas sem novas leituras, mostra: "Nenhuma novidade há mais de três semanas. Se ainda não chegou, fale com o vendedor. O Peek continua consultando Post Haste."

Você pode escolher alertas para "Todas as movimentações", "Etapas importantes" ou "Somente entrega". O Peek apenas acompanha atualizações de rastreamento e não pode agendar reentregas, conceder autorização para deixar o pacote sem assinatura nem contatar a filial local da Post Haste por você.

## Perguntas sobre o rastreamento da Post Haste

### Quais códigos de rastreamento funcionam na Post Haste?

A Post Haste rastreia tíquetes/Express Packs (2–4 letras + 5–8 dígitos, como `PH48291736`, inventado), etiquetas de cobrança de 8 dígitos (`73918462`, inventado), códigos de barras SSCC de 20 dígitos e códigos impressos pelo cliente (`KLM4829173`, inventado) — ou basta colar o link completo `receiver.posthaste.co.nz` do seu e-mail de envio.

### Por que meu código impresso pelo cliente da Post Haste não aparece?

Algumas etiquetas impressas pelo remetente (como remessas Despatch IT) são vinculadas ao número da conta do remetente. Cole o link completo `receiver.posthaste.co.nz` recebido no e-mail de envio para incluir o código da conta na consulta.

### E se eu perder a entrega da Post Haste?

A Post Haste normalmente exige alguém no local, salvo se houver autorização para deixar a encomenda. Use o formulário on-line em [posthaste.co.nz/tools/redelivery/](https://posthaste.co.nz/tools/redelivery/) com sua referência de entrega de 16 dígitos, ou ligue para a filial local em [posthaste.co.nz/about-us/contact/](https://posthaste.co.nz/about-us/contact/).

### Por que o rastreamento da Post Haste mostra `Delivery Handed Over` ou `STAT/RZDEL`?

`STAT/RZDEL` indica um endereço de entrega rural ou não urbano, e a leitura de repasse significa que o pacote foi entregue a um contratado rural — acrescentando normalmente 2–3 dias úteis antes da entrega final na porta (`3P/D`).

:::sources
- [Post Haste: Official Website](https://posthaste.co.nz/) – rede nacional de entregas da Nova Zelândia (Freightways Group)
- [Post Haste: Our Story](https://posthaste.co.nz/about-us/our-story/) – aquisição pela Freightways (1977) e parceria com a Castle Parcels
- [Post Haste: Sending & Receiving Services](https://posthaste.co.nz/services/sending-and-receiving/) – serviços local, regional overnight, nacional overnight, econômico entre ilhas em dois dias, sábado e entregas rurais
- [Post Haste: Advanced Track & Trace](https://posthaste.co.nz/tools/track/) – etiquetas impressas pelo cliente (com conta da empresa), tíquetes/Express Packs, códigos de barras e malotes contratados
- [Post Haste: Redelivery](https://posthaste.co.nz/tools/redelivery/) – pedidos de reentrega on-line e exigência de documento para retirada em filial
- [Post Haste: Contact & Branches](https://posthaste.co.nz/about-us/contact/) – diretório de filiais das Ilhas Norte e Sul
- [Post Haste: Receiving Parcels Help](https://help.posthaste.co.nz/category/receiving-parcels/) – política de entrega em local desacompanhado, notificações e diretrizes de perda/danos (URL de ajuda vinculada pelo Peek)
- [Universal Parcel Scraper: post-haste](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/post-haste) – consulta Freightways para tíquete/etiqueta/SSCC/remessa, regras de link com conta `receiver.posthaste.co.nz`, mapeamento de códigos (`P`, `ONBD`, `NOND`, `STAT/RZDEL`, `D`, `3P/D`) e leitura de previsão de entrega
:::
