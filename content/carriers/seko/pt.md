---
title: Rastreio SEKO Logistics: números, OmniParcel e entrega
description: Acompanha envios SEKO Logistics e SEKO OmniParcel: formatos de números, códigos GX, estados, linhas EUA e Reino Unido e prazos estimados.
slug: rastreio-seko-logistics
published: 2026-11-24
updated: 2026-11-24
---

A SEKO Logistics começou em 1976 com um único escritório em Chicago e conta hoje com mais de 150 escritórios em mais de 60 países e 15 hubs principais. Os seus serviços incluem carga aérea, marítima e terrestre, comércio eletrónico, *White Glove & Specialty Delivery* e *Home Delivery & Last Mile*. Os envios são seguidos em [sekologistics.com/en/track](https://www.sekologistics.com/en/track/), e as encomendas transfronteiriças de comércio eletrónico no [rastreio OmniParcel](https://track.omniparcel.com/) da SEKO.

Como o portal padrão da SEKO e o OmniParcel funcionam como dois sistemas separados, com formatos de números e estilos de registos distintos, saber que formato tens ajuda a ler corretamente o histórico.

## Formatos de números SEKO Logistics e OmniParcel

Os envios da SEKO Logistics não seguem um prefixo exclusivo único; dividem-se em duas famílias tratadas em portais distintos:

- **Números de envio SEKO Logistics:** o Peek aceita de 8 a 20 letras e dígitos com uma sequência de pelo menos 7 dígitos. Entre as formas observadas há 3 letras seguidas de 7 dígitos (como `WFY4829173`), 5 letras seguidas de 7 dígitos ou 9 dígitos isolados (como `482917360`). O Peek nunca reconhece a SEKO por um número isolado: para 3 letras + 7 dígitos pergunta à [Australia Post](carrier:australia-post), para 5 letras + 7 dígitos a nenhuma e para 9 dígitos à [DPD Ireland](carrier:dpd-ie) (fora da Irlanda apenas em último recurso). Seleciona SEKO Logistics manualmente ou cola a tua ligação de seguimento.
- **Números SEKO OmniParcel (`GX` + 17 ou 18 dígitos):** os envios transfronteiriços de comércio eletrónico em `track.omniparcel.com` usam `GX` seguido de 17 ou 18 dígitos (como `GX482917360514829173`). A página principal da SEKO não reconhece códigos `GX`; apenas o rastreio OmniParcel responde por eles.

Se colares uma ligação de `sekologistics.com/en/track/?trackingNumbers=...` ou `track.omniparcel.com/...`, o Peek extrai o número e encaminha-o automaticamente para a SEKO Logistics. Para perceber melhor as diferenças entre códigos de carga e de encomendas, consulta o guia de [formatos de números de seguimento](guide:tracking-number-formats).

## Estados de rastreio da SEKO Logistics e do OmniParcel

A página principal da SEKO apresenta frequentemente marcos no formato `Status Changed to [...]` (por vezes com um travessão final em vez de parênteses retos), enquanto o OmniParcel lista hubs internacionais, voos, alfândega e descrições do estafeta de última milha.

| Texto SEKO / OmniParcel | O que significa |
| --- | --- |
| `Shipment Data Received` / `Shipment Created` / `Tracking number allocated & order ready` | Os dados do envio chegaram à SEKO ou ao OmniParcel, antes da recolha física ou entrada em armazém. |
| `Arrived at PU location` | O motorista de recolha chegou ao remetente; a carga ainda não foi registada como recolhida. |
| `Picked Up From Customer` / `At Pickup Terminal` / `Received at Origin Warehouse` | A SEKO recolheu o envio ou deu-lhe entrada no terminal ou armazém de origem. |
| `In Transit` / `In Transit to Delivery Terminal` / `At Transfer Terminal` / `Processed through Export Hub` / `Flight - Freight departed to destination` | Em transporte rodoviário doméstico ou carga aérea internacional rumo ao país ou estação de entrega. |
| `At Delivery Terminal` / `At Delivery Terminal Delivery Appt Set` / `On Hand Destination Station` / `Delivered To Warehouse` | Chegou ao terminal de destino da SEKO ou armazém local antes da entrega final; `Delivery Appt Set` significa que foi agendada uma entrega ao domicílio. |
| `Received by Destination Customs` / `Customs held for inspection and clearance` | Em processamento ou inspeção aduaneira no destino num envio transfronteiriço OmniParcel. |
| `Customs cleared` / `Received by Last Mile Provider` / `Received by carrier` | Desalfandegada e transferida para o hub local de destino ou transportadora de última milha. |
| `Shipment is Out for Delivery` / `Driver on site Delivery Location` / `The order is on the way to the recipient.` | Em distribuição hoje com o motorista, ou motorista já no local de entrega. |
| `Awaiting collection` / `Customer to collect from carrier` / `Delivered to locker/collection point` | A aguardar levantamento pelo destinatário num depósito, ponto de recolha ou cacifo. |
| `Attempted delivery` / `Attempted Delivery - Refused by customer` | Uma tentativa de entrega não teve sucesso. |
| `Temporary Origin Hold` / `Temporary Destination Hold` | Uma retenção temporária suspendeu o envio no terminal de origem ou destino. |
| `Shipment Delivered` / `Delivered - Authority to Leave / Safe Drop` | Entregue na morada do destinatário (ou deixada com autorização de entrega segura). |
| `Return to sender` | A caminho de volta para o remetente. |

Duas expressões no portal principal da SEKO merecem atenção:

- **`Delivered To Warehouse`** significa que a carga chegou ao armazém de destino da SEKO, não à tua casa — a entrega ainda não aconteceu.
- **`POD Image Attached` e `POD IMAGED`** assinalam o arquivo do comprovativo de entrega (por vezes dias após a entrega) e **`Return Data Received`** regista o pedido de devolução sem provar que a mercadoria já voltou atrás. Nenhuma delas altera a etapa física de entrega.

## Entrega ao domicílio, alfândega transfronteiriça e atrasos

O site da SEKO inclui *White Glove & Specialty Delivery* e *Home Delivery & Last Mile*, bem como logística segura para eletrónica e equipamento informático, a par das encomendas transfronteiriças OmniParcel.

- **Agendamentos de entrega ao domicílio:** `At Delivery Terminal Delivery Appt Set` e `On Hand DEST Delv Appt Set` indicam que há uma marcação de entrega definida enquanto o envio aguarda no terminal de entrega. Em trânsito, a página da SEKO mostra frequentemente uma data `Expected Arrival` ou um intervalo `Estimated delivery between`.
- **Passagens transfronteiriças OmniParcel:** em encomendas internacionais de comércio eletrónico (`GX...`), os registos do OmniParcel vão do armazém de origem, exportação, voo e alfândega de destino até à transportadora local (`Received by Last Mile Provider`). Os registos podem parar durante voos e alfândega; consulta o [guia de alfândega](guide:customs) e [quando o rastreio deixa de atualizar](guide:tracking-not-updating) se não houver novidades.
- **Horários e repetições:** a página da SEKO mostra horas ao minuto sem fuso horário e por vezes repete o mesmo estado duas vezes no mesmo minuto com maiúsculas diferentes; o Peek guarda apenas um e data os marcos da SEKO (que não indicam local) pela hora de Chicago. A página do OmniParcel apresenta horas em UTC+10:00 salvo se mudares o seletor de fuso horário; o Peek consulta o OmniParcel em UTC, mantendo o instante exato de cada registo. O Peek mostra cada registo na hora do teu dispositivo.

Se a tua encomenda internacional usar outra grande transportadora noutra parte do trajeto, compara o rastreio com a [DHL Express](carrier:dhl-express) ou a [FedEx](carrier:fedex).

## Acompanhar a SEKO Logistics com o Peek

Como os códigos da SEKO se sobrepõem a outras transportadoras e não têm um prefixo universal, cola sempre que possível a ligação completa `sekologistics.com/en/track/?trackingNumbers=...` ou `track.omniparcel.com/...`. Se colares um número isolado — `WFY4829173`, `482917360` ou um código `GX...` do OmniParcel — seleciona SEKO Logistics manualmente em «Alterar» ao adicionar a encomenda ou em «Alterar transportadora» no ecrã de rastreio.

O Peek consulta ambos os portais diretamente através do [adaptador SEKO em código aberto no universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/seko): os códigos `GX` + 17–18 dígitos seguem automaticamente para o OmniParcel (pedido em UTC, lendo locais de registo, nome do serviço, peso em quilogramas e país de destino), enquanto os números de envio SEKO seguem para a página da SEKO (data prevista de chegada ou janela de entrega, nome do serviço, país de destino e marcos sem duplicados, filtrando as linhas administrativas `POD Image Attached` / `POD IMAGED` e descartando nomes de signatários e códigos postais).

O Peek mapeia os marcos da SEKO e do OmniParcel para etapas padrão: «Etiqueta criada» para dados recebidos, criação e chegada do motorista à recolha; «Aceite pela transportadora» para recolha e entrada no armazém de origem; «Em trânsito» para hub, voo, transferência, terminal de entrega, chegada a armazém, desalfandegamento concluído e entrega à última milha; «Na alfândega» para inspeção aduaneira no destino; «Em distribuição» para saída para entrega e motorista no local; «Pronta para levantamento» para depósito, ponto de recolha ou cacifo; «Tentativa de entrega» para tentativas falhadas; «Problema reportado» para retenções na origem ou no destino; «Entregue» para entrega e entrega segura autorizada; e «Devolvida ao remetente» para devoluções.

Enquanto tens a encomenda aberta no Peek, ou no dia da entrega com as notificações ativadas, o Peek consulta até de 10 em 10 minutos; no dia em que a página da SEKO apresenta `Shipment is Out for Delivery`, `Out for Delivery` ou `Driver on site Delivery Location`, ou o OmniParcel mostra `Out for delivery` ou `The order is on the way to the recipient.`, de 2 em 2 minutos a partir das 6h00 no relógio do registo mais recente enquanto o rastreio da SEKO responder. Caso contrário, o Peek verifica de hora a hora. Após 12 horas apenas em «Etiqueta criada» ou «Aceite pela transportadora», ou dois dias sem novidades, verifica de hora a hora independentemente da forma como acompanhas a encomenda, e de 6 em 6 horas após sete dias em «Etiqueta criada». Quando a SEKO não responde e o Peek acompanha a encomenda apenas através de serviços de rastreio, verifica de hora a hora, de 3 em 3 horas após um dia sem novidades e até de 15 em 15 minutos no dia em que a encomenda está em distribuição, enquanto tiveres a encomenda aberta ou as notificações ativadas.

Quando o registo mais recente tem quatro dias e a encomenda continua em «Etiqueta criada», «Aceite pela transportadora» ou «Em trânsito», o Peek assinala-a com o dia desse registo, como «Sem atualizações desde seg 12 out». A página da encomenda apresenta então «É habitual uma encomenda ficar sem novidades em voos longos, na alfândega ou ao fim de semana. O Peek continua a perguntar a SEKO Logistics.» Passadas três semanas sem novos registos, mostra «Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, contacta o vendedor. O Peek continua a perguntar a SEKO Logistics.»

Nas definições de alertas do Peek, escolhe «Todos os registos», «Passos importantes» ou «Só a entrega» para ajustar as tuas notificações.

## Contactar a SEKO Logistics

A SEKO publica linhas regionais de apoio ao cliente diretamente na sua [página de rastreio](https://www.sekologistics.com/en/track/) e na página [Contact Us](https://www.sekologistics.com/en/contact-us/):

- **Linha US Home Delivery:** (866) 268-5263
- **Apoio ao Cliente Reino Unido:** +44 1784 417120
- **Rastreio oficial online:** [SEKO Track My Shipment](https://www.sekologistics.com/en/track/) (aceita até 30 números de seguimento de cada vez) e [OmniParcel Tracker](https://track.omniparcel.com/)
- **Pedido de informação online:** [Contactar SEKO Logistics](https://www.sekologistics.com/en/contact-us/) (seleciona o formulário «Track My Shipment»)
- **Sobre a empresa:** [Sobre a SEKO Logistics](https://www.sekologistics.com/en/about/) e [sekologistics.com](https://www.sekologistics.com/en/)

Tem o teu número de rastreio SEKO à mão quando ligares ou preencheres o formulário Track My Shipment.

## Perguntas frequentes

### Porque é que o meu número `GX` não funciona em `sekologistics.com/en/track/`?

Os números iniciados por `GX` seguidos de 17 ou 18 dígitos pertencem à plataforma OmniParcel da SEKO e só funcionam em [track.omniparcel.com](https://track.omniparcel.com/). No Peek, selecionar SEKO Logistics envia automaticamente os números `GX` para o OmniParcel e os números SEKO padrão para o rastreio principal da SEKO.

### «Delivered To Warehouse» significa que a minha encomenda foi entregue na morada errada?

Não. No rastreio SEKO Logistics, `Delivered To Warehouse` indica que o envio chegou ao armazém de destino da SEKO antes da entrega final. Aguarda `Out for delivery` e `Shipment Delivered` para confirmar a entrega na tua morada.

### Porque é que o Peek me pediu para escolher a transportadora para o meu número SEKO?

Os números da SEKO (como 3 letras + 7 dígitos ou 9 dígitos) coincidem com outras transportadoras, e o Peek nunca reconhece a SEKO a partir de um número isolado, incluindo códigos `GX`. Selecionar SEKO Logistics em «Alterar» ou colar a ligação oficial da SEKO ou do OmniParcel diz exatamente ao Peek onde consultar.

### Como agendar uma entrega ao domicílio da SEKO Logistics?

Liga para a linha US Home Delivery através do `(866) 268-5263` ou para o apoio no Reino Unido pelo `+44 1784 417120`, ou utiliza o formulário Track My Shipment em [sekologistics.com/en/contact-us](https://www.sekologistics.com/en/contact-us/).

:::sources
- [SEKO Logistics](https://www.sekologistics.com/en/)
- [SEKO Logistics — Track My Shipment](https://www.sekologistics.com/en/track/)
- [SEKO Logistics — About](https://www.sekologistics.com/en/about/)
- [SEKO Logistics — Contact Us](https://www.sekologistics.com/en/contact-us/)
- [SEKO OmniParcel Tracker](https://track.omniparcel.com/)
- [universal-parcel-scraper SEKO adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/seko)
:::
