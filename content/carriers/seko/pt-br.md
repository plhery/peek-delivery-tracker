---
title: Rastreio SEKO Logistics: códigos, OmniParcel e entrega
description: Rastreie envios SEKO Logistics e SEKO OmniParcel: formatos de código e números GX, significado dos status, telefones nos EUA e Reino Unido e entregas.
slug: seko-logistics-tracking
published: 2026-11-26
updated: 2026-11-26
---

A SEKO Logistics nasceu em 1976 com um único escritório em Chicago e hoje opera mais de 150 escritórios em mais de 60 países e 15 centros principais. Seus serviços abrangem frete aéreo, marítimo e rodoviário, e-commerce, entrega especial (*White Glove & Specialty Delivery*) e entrega domiciliar de última milha (*Home Delivery & Last Mile*). Envios padrão são acompanhados em [sekologistics.com/en/track](https://www.sekologistics.com/en/track/), e encomendas internacionais de e-commerce no [rastreador OmniParcel](https://track.omniparcel.com/) da SEKO.

Como o portal principal da SEKO e o OmniParcel funcionam como sistemas separados, com formatos de código e estilos de atualização próprios, saber qual código você tem ajuda a interpretar corretamente a linha do tempo.

## Formatos de código SEKO Logistics e OmniParcel

Os envios da SEKO Logistics não seguem um prefixo único; eles se dividem em duas famílias atendidas por portais diferentes:

- **Códigos SEKO Logistics:** o Peek aceita de 8 a 20 letras e dígitos contendo uma sequência de pelo menos 7 dígitos. Formatos comuns incluem 3 letras seguidas de 7 dígitos (como `WFY4829173`), 5 letras seguidas de 7 dígitos ou 9 dígitos puros (como `482917360`). O Peek nunca reconhece a SEKO por um número isolado: para 3 letras + 7 dígitos consulta [Australia Post](carrier:australia-post), para 5 letras + 7 dígitos consulta [Colis Privé](carrier:colis-prive) e para 9 dígitos consulta [DPD Ireland](carrier:dpd-ie) (fora da Irlanda, apenas como último recurso). Selecione SEKO Logistics manualmente ou cole o link de rastreio.
- **Códigos SEKO OmniParcel (`GX` + 17 ou 18 dígitos):** remessas internacionais de e-commerce em `track.omniparcel.com` usam `GX` seguido de 17 ou 18 dígitos (como `GX482917360514829173`). A página principal da SEKO não reconhece códigos `GX`; apenas o rastreador OmniParcel responde por eles.

Se você colar um link de `sekologistics.com/en/track/?trackingNumbers=...` ou `track.omniparcel.com/...`, o Peek extrai o código e o direciona automaticamente para a SEKO Logistics. Para entender como códigos logísticos e postais diferem, consulte o [guia de formatos de código de rastreio](guide:tracking-number-formats).

## Significado dos status SEKO Logistics e OmniParcel

A página principal da SEKO costuma registrar eventos como `Status Changed to [...]` (às vezes com um hífen final em vez de colchetes), enquanto o OmniParcel detalha centros internacionais, voos, alfândega e transportadora local.

| Mensagem SEKO / OmniParcel | O que significa |
| --- | --- |
| `Shipment Data Received` / `Shipment Created` / `Tracking number allocated & order ready` | Os dados do envio chegaram à SEKO ou ao OmniParcel antes da coleta física ou entrada no armazém. |
| `Arrived at PU location` | O motorista de coleta chegou ao remetente; a carga ainda não foi registrada como coletada. |
| `Picked Up From Customer` / `At Pickup Terminal` / `Received at Origin Warehouse` | A SEKO coletou o envio ou o deu entrada no terminal ou armazém de origem. |
| `In Transit` / `In Transit to Delivery Terminal` / `At Transfer Terminal` / `Processed through Export Hub` / `Flight - Freight departed to destination` | Em transporte rodoviário doméstico ou aéreo internacional rumo ao país ou terminal de destino. |
| `At Delivery Terminal` / `At Delivery Terminal Delivery Appt Set` / `On Hand Destination Station` / `Delivered To Warehouse` | Chegou ao terminal de destino ou armazém local da SEKO antes da entrega final; `Delivery Appt Set` indica que o agendamento domiciliar foi marcado. |
| `Received by Destination Customs` / `Customs held for inspection and clearance` | Em processo de desembaraço ou inspeção na alfândega de destino em um envio internacional OmniParcel. |
| `Customs cleared` / `Received by Last Mile Provider` / `Received by carrier` | Liberado pela alfândega e transferido ao centro local ou entregador final. |
| `Shipment is Out for Delivery` / `Driver on site Delivery Location` / `The order is on the way to the recipient.` | Saiu com o motorista para entrega hoje, ou o motorista já chegou ao endereço. |
| `Awaiting collection` / `Customer to collect from carrier` / `Delivered to locker/collection point` | Aguardando retirada pelo destinatário em depósito, ponto de coleta ou armário inteligente. |
| `Attempted delivery` / `Attempted Delivery - Refused by customer` | Uma tentativa de entrega não foi concluída. |
| `Temporary Origin Hold` / `Temporary Destination Hold` | Uma retenção pausou temporariamente o envio no terminal de origem ou de destino. |
| `Shipment Delivered` / `Delivered - Authority to Leave / Safe Drop` | Entregue no endereço do destinatário (ou deixado mediante autorização de entrega sem assinatura). |
| `Return to sender` | Retornando ao remetente. |

Duas mensagens do portal principal da SEKO merecem atenção:

- **`Delivered To Warehouse`** significa que a carga chegou ao armazém de destino da SEKO, não à sua casa — a entrega ainda não ocorreu.
- **`POD Image Attached` e `POD IMAGED`** registram apenas o arquivamento do comprovante de entrega (às vezes dias depois), e **`Return Data Received`** registra que uma devolução foi solicitada sem provar que a carga começou a voltar. Nenhuma delas altera o estágio físico.

## Entrega domiciliar, alfândega internacional e atrasos

O site da SEKO lista entrega *White Glove & Specialty*, entrega domiciliar de última milha e logística segura para eletrônicos e hardware, além de pedidos internacionais OmniParcel.

- **Agendamento de entrega domiciliar:** `At Delivery Terminal Delivery Appt Set` e `On Hand DEST Delv Appt Set` confirmam que há um agendamento marcado enquanto o envio aguarda no terminal de entrega. Durante o trânsito, a página da SEKO costuma informar uma data `Expected Arrival` ou um intervalo `Estimated delivery between`.
- **Repasse internacional no OmniParcel:** em pedidos internacionais (`GX...`), as leituras vão do armazém de origem até exportação, voo, alfândega de destino e transportadora local (`Received by Last Mile Provider`). É comum haver pausas durante voos e fiscalização; veja o [guia de alfândega](guide:customs) e [quando o rastreio para de atualizar](guide:tracking-not-updating).
- **Horários e duplicidades:** a página da SEKO informa horários com precisão de minutos sem fuso e às vezes repete o mesmo status no mesmo minuto com letras maiúsculas diferentes; o Peek mantém apenas um e interpreta marcos sem local no horário de Chicago. O OmniParcel mostra horários em UTC+10:00 a menos que você troque o seletor; o Peek consulta em UTC para preservar o instante exato e exibe tudo no relógio do seu aparelho.

Se outro trecho do pedido usar uma grande transportadora expressa separada, compare com [DHL Express](carrier:dhl-express) ou [FedEx](carrier:fedex).

## Como acompanhar SEKO Logistics no Peek

Como os códigos da SEKO coincidem com os de outras transportadoras e não têm prefixo universal, cole o link completo `sekologistics.com/en/track/?trackingNumbers=...` ou `track.omniparcel.com/...` sempre que tiver um. Ao colar apenas o número — seja `WFY4829173`, `482917360` ou um código OmniParcel `GX...` — selecione SEKO Logistics manualmente em **Alterar** ao adicionar ou em **Alterar transportadora** na tela do pacote.

O Peek consulta ambos os portais diretamente pelo [adaptador SEKO de código aberto do universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/seko): números `GX` + 17–18 dígitos vão automaticamente ao OmniParcel (em UTC, lendo locais, nome do serviço, peso em kg e país de destino), enquanto códigos SEKO padrão vão à página principal da SEKO (lendo data prevista ou intervalo de entrega, serviço, país de destino e marcos sem duplicação, filtrando registros administrativos `POD Image Attached` / `POD IMAGED` e descartando assinaturas e códigos postais).

O Peek organiza os eventos em etapas claras: **Etiqueta criada** para dados recebidos, criação e chegada à coleta; **Recebida pela transportadora** para coleta e entrada no armazém de origem; **Em trânsito** para centros, voos, transferências, chegada ao terminal/armazém, liberação alfandegária e repasse local; **Na alfândega** para inspeção aduaneira; **Saiu para entrega** para rota de entrega e motorista no local; **Pronta para retirada** para depósito, ponto ou armário; **Tentativa de entrega** para tentativas frustradas; **Precisa de atenção** para retenções; **Entregue** para entrega e entrega autorizada; e **Devolvida ao remetente** para devoluções.

Enquanto o pacote estiver aberto no Peek, ou no dia da entrega com notificações ativadas, o Peek consulta até a cada 10 minutos; no dia em que a SEKO exibir `Shipment is Out for Delivery`, `Out for Delivery` ou `Driver on site Delivery Location`, ou o OmniParcel exibir `Out for delivery` ou `The order is on the way to the recipient.`, verifica a cada 2 minutos a partir das 6h no relógio da leitura mais recente, enquanto o rastreador oficial responder. Nos demais casos, verifica de hora em hora. Após 12 horas somente em "Etiqueta criada" ou "Recebida pela transportadora", ou dois dias sem novidades, consulta a cada hora, e a cada 6 horas após sete dias em "Etiqueta criada". Quando a SEKO não responde e o Peek acompanha por serviços de rastreamento, consulta de hora em hora, a cada 3 horas após um dia sem novidades e até a cada 15 minutos no dia da saída para entrega.

Quando o registro mais recente completa quatro dias e o pacote segue em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito", o Peek exibe o alerta com a data daquela leitura (por exemplo, "Sem atualização de rastreamento desde seg 12 out") e a nota "É comum uma encomenda ficar sem atualizações em voos longos, na alfândega ou no fim de semana. O Peek continua consultando SEKO Logistics." Após três semanas sem novidade, sugere consultar o vendedor enquanto continua verificando.

Nos alertas do Peek, escolha **Cada atualização**, **Etapas importantes** ou **Somente entrega**.

## Como entrar em contato com a SEKO Logistics

A SEKO divulga canais regionais de atendimento diretamente na [página de rastreio](https://www.sekologistics.com/en/track/) e em [Contact Us](https://www.sekologistics.com/en/contact-us/):

- **Linha de entrega domiciliar nos EUA:** (866) 268-5263
- **Atendimento no Reino Unido:** +44 1784 417120
- **Rastreio oficial on-line:** [SEKO Track My Shipment](https://www.sekologistics.com/en/track/) (aceita até 30 códigos de uma vez) e [OmniParcel Tracker](https://track.omniparcel.com/)
- **Consulta de envio on-line:** [Contact SEKO Logistics](https://www.sekologistics.com/en/contact-us/) (selecione o formulário "Track My Shipment")
- **Sobre a empresa:** [About SEKO Logistics](https://www.sekologistics.com/en/about/) e [sekologistics.com](https://www.sekologistics.com/en/)

Tenha seu número de rastreio SEKO em mãos ao ligar ou preencher o formulário.

## Perguntas frequentes

### Por que meu código `GX` não funciona em `sekologistics.com/en/track/`?

Códigos iniciados por `GX` seguidos de 17 ou 18 dígitos pertencem à plataforma OmniParcel da SEKO e funcionam apenas em [track.omniparcel.com](https://track.omniparcel.com/). No Peek, escolher SEKO Logistics envia códigos `GX` automaticamente ao OmniParcel e códigos SEKO padrão ao portal principal.

### "Delivered To Warehouse" significa que meu pedido foi entregue no endereço errado?

Não. No rastreio da SEKO Logistics, `Delivered To Warehouse` significa que a carga chegou ao armazém de destino da SEKO antes da entrega final. Aguarde `Out for delivery` e `Shipment Delivered` para confirmar a entrega na sua porta.

### Por que o Peek pediu para eu escolher uma transportadora para meu código SEKO?

Os números da SEKO (como 3 letras + 7 dígitos ou 9 dígitos) coincidem com outras transportadoras, e o Peek nunca identifica a SEKO por um número isolado — incluindo códigos `GX`. Selecionar SEKO Logistics em **Alterar** ou colar o link oficial da SEKO ou OmniParcel indica ao Peek exatamente onde consultar.

### Como agendar uma entrega domiciliar da SEKO Logistics?

Ligue para a linha de entrega domiciliar dos EUA em `(866) 268-5263` ou para o atendimento do Reino Unido em `+44 1784 417120`, ou envie o formulário "Track My Shipment" em [sekologistics.com/en/contact-us](https://www.sekologistics.com/en/contact-us/).

:::sources
- [SEKO Logistics](https://www.sekologistics.com/en/)
- [SEKO Logistics — Track My Shipment](https://www.sekologistics.com/en/track/)
- [SEKO Logistics — About](https://www.sekologistics.com/en/about/)
- [SEKO Logistics — Contact Us](https://www.sekologistics.com/en/contact-us/)
- [SEKO OmniParcel Tracker](https://track.omniparcel.com/)
- [universal-parcel-scraper SEKO adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/seko)
:::
