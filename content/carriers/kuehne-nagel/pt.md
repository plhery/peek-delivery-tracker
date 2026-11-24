---
title: Rastreio Kuehne+Nagel: números, estados e entregas
description: Acompanha envios rodoviários, aéreos e marítimos Kuehne+Nagel por referência ou ligação myKN: formatos de códigos, etapas, alfândega e contactos.
slug: rastreio-kuehne-nagel
published: 2026-11-24
updated: 2026-11-24
---

A Kuehne+Nagel é um dos principais operadores logísticos mundiais, com cerca de 92 000 colaboradores em quase 1 300 locais em perto de 100 países. Transporta carga marítima, aérea e rodoviária, e o seu [rastreio público myKN](https://mykn.kuehne-nagel.com/public-tracking/) consulta envios de qualquer modo de transporte.

O rastreio público no myKN abrange envios iniciados nos últimos 90 dias. Como a Kuehne+Nagel movimenta mercadorias por terminais rodoviários, aeroportos, estações de consolidação de contentores (CFS), terminais ferroviários e portos marítimos, um único envio pode apresentar tanto marcos físicos de percurso como avisos separados de reserva, alfândega ou documentação.

## Formatos de números de rastreio Kuehne+Nagel

O Peek aceita referências Kuehne+Nagel de 6 a 35 letras e dígitos com pelo menos um dígito, removendo espaços e hífenes. O myKN pesquisa os números de rastreio próprios da Kuehne+Nagel, guias e reservas no mesmo campo que as referências de cliente. O Peek reconhece três formatos:

- **`RWM` seguido de 9 dígitos** (12 carateres no total, como `RWM482917360`) — número de rastreio de um envio rodoviário Kuehne+Nagel, reconhecido pelo Peek exclusivamente como da Kuehne+Nagel.
- **`RAPI` seguido de 8 dígitos** (12 carateres no total, como `RAPI48291736`) — número de rastreio Kuehne+Nagel, também reconhecido exclusivamente como próprio.
- **2 dígitos, `A` e 9 dígitos** (12 carateres no total, como `26A482917360`) — número de envio rodoviário. Este formato encaixa igualmente em [Australia Post](carrier:australia-post) e [Colis Privé](carrier:colis-prive), pelo que o Peek consulta Australia Post, Colis Privé e Kuehne+Nagel por ordem (Kuehne+Nagel em segundo lugar na Suíça e na Alemanha).

Para carga aérea e marítima, os expedidores podem ainda fornecer uma guia Kuehne+Nagel, um conhecimento de embarque interno (*house bill of lading*) ou uma referência de cliente fora destes três padrões. Se colares uma ligação `mykn.kuehne-nagel.com/public-tracking/shipments?query=...`, o Peek extrai a referência e consulta diretamente a Kuehne+Nagel. Para comparar outros códigos de carga e encomendas, consulta o [guia de formatos de números de rastreio](guide:tracking-number-formats).

## Como funcionam as etapas da Kuehne+Nagel

O rastreio público da Kuehne+Nagel divide um envio em dois fluxos: paragens físicas ao longo do trajeto (origem, terminal de partida, hub de trânsito, gateway, terminal de chegada e destino) e avisos de informação de transporte (reservas, declarações aduaneiras e comprovativos de entrega).

| Texto Kuehne+Nagel | O que significa |
| --- | --- |
| Booking submission / Booking confirmation | A ordem fica registada no sistema da Kuehne+Nagel antes de a carga se mover; uma alteração tardia de reserva nunca faz recuar um envio já em movimento. |
| Ready for pickup / Arrival (at origin) | A mercadoria está pronta no expedidor ou o camião de recolha chegou, antes da confirmação de carga. |
| Pickup | A Kuehne+Nagel recolheu a carga junto do expedidor. |
| Arrival / Departure / Load on vehicle / Load on vessel | Registos físicos em terminal, plataforma de cross-dock, rampa ferroviária, aeroporto ou porto durante o transporte principal. |
| Customs entry start / Customs duties payment / Entry documents required | O desalfandegamento de exportação ou importação está em curso, ou as autoridades pediram documentação, pagamento de direitos ou inspeção de agência (como FDA ou PGA). |
| Customs entry release / Export customs entry release / Import customs entry release | A alfândega ou a autoridade fronteiriça competente libertou a carga para continuar viagem. |
| In delivery | O veículo rodoviário saiu para o último troço de entrega ou chegou ao local de destino. |
| Delivery / Freight collection / Proof of delivery available | A carga foi entregue ao destinatário ou recolhida no terminal; `Delivery with discrepancies` continua a assinalar entrega registando falta ou dano. |
| Delivery attempt failed / Delivery not possible | Uma visita de entrega não teve sucesso, por exemplo por recusa do destinatário. |
| Shipment on hold / Delivery instructions pending / Missing/incomplete document | O envio encontra-se retido num terminal ou na alfândega até serem fornecidos documentos, pagamento ou instruções de entrega. |

Alguns marcos merecem atenção especial:

- **Available at CY / Available at CFS / Ready for collection / Available at rail terminal** significam que a carga chegou a um parque de contentores marítimo, estação CFS, aeroporto ou terminal ferroviário e está pronta para transferência ou levantamento. A Kuehne+Nagel ou um parceiro pode ainda efetuar a entrega rodoviária final, pelo que estes registos permanecem em trânsito em vez de assinalarem entrega final.
- **Freight handover** significa que a Kuehne+Nagel entregou a carga a um despachante aduaneiro ou transportador seguinte, e não ao destinatário final.
- **Avisos documentais e administrativos** como `HAWB available`, `MAWB available`, `Transport document available`, `VGM closure`, `Delivery appointment submission` e `Customs billing invoice available` registam passos administrativos e não alteram a fase física do envio.
- **Empty container gate-in e partida do veículo no destino** ocorrem após concluída a entrega e mantêm o envio no estado entregue.

## Entrega, alfândega e atrasos

`Delivery appointment submission` regista uma data de entrega acordada para mais tarde. Não é um registo de saída para entrega: o envio só muda para distribuição quando surge `In delivery` ou a chegada (`Arrival`) do camião na morada de destino.

Se o rastreio mostrar `Awaiting consignee's feedback`, `Awaiting consignee's payment` ou `Entry documents required`, a alfândega aguarda resposta, pagamento ou documentos do destinatário. `Unable to file customs entry - missing details or documentation` significa que a declaração aduaneira não pode ser submetida até esses dados chegarem. Consulta o [guia da alfândega](guide:customs) para saber como funcionam o desalfandegamento e os encargos de importação.

As datas planeadas apresentadas no myKN são previsões e não registos efetivos. Para conselhos gerais sobre rastreio parado, lê [quando o rastreio deixa de atualizar](guide:tracking-not-updating).

## Seguir a Kuehne+Nagel com o Peek

Quando colas um número `RWM` ou `RAPI`, ou uma ligação `mykn.kuehne-nagel.com/public-tracking/shipments?query=...`, no Peek, o Peek reconhece logo a Kuehne+Nagel. Para um número `26A...`, o Peek consulta os transportadores acima por ordem e mostra «Kuehne+Nagel tem esta encomenda.» assim que a Kuehne+Nagel responde. Para uma guia ou referência de cliente fora destes formatos, seleciona Kuehne+Nagel em **Alterar** ao adicionar ou **Alterar transportadora** no ecrã de rastreio.

O Peek lê diretamente a pesquisa pública de envios, os marcos de percurso e os avisos de transporte da Kuehne+Nagel através do [adaptador aberto Kuehne+Nagel do universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/kuehne-nagel). Regista apenas marcos efetivamente concluídos (ignorando previsões planeadas) e lê o peso total em quilogramas e o país de destino quando fornecidos. Quando a pesquisa encontra o envio por uma referência de cliente, o Peek indica o número interno da Kuehne+Nagel junto do teu sob «Kuehne+Nagel segue a encomenda com o número»; quando uma referência corresponde a vários envios, o Peek não consegue saber qual é o teu. Se o sistema de leitura incluir o fuso UTC, o Peek preserva esse instante exato; caso contrário data o registo no fuso horário do local (hora de Berlim para Hamburgo, hora de Zurique para Basileia), um registo sem local no fuso vizinho ou então na hora de Zurique, mostrando tudo na hora do teu dispositivo.

O Peek traduz os marcos da Kuehne+Nagel em etapas claras: **Etiqueta criada** para reservas e avisos de pronto para recolha, **Aceite pela transportadora** para recolha, **Em trânsito** para movimentos em hubs, portos, ferrovia e transferências, **Na alfândega** para desalfandegamento e retenções de autoridades, **Em distribuição** para `In delivery` e chegada do camião ao destino, **Tentativa de entrega** para tentativas falhadas, **Problema reportado** para retenções, recolhas falhadas, cancelamentos e documentos em falta, e **Entregue** para entrega, levantamento da carga ou comprovativo de entrega. Avisos administrativos permanecem na linha temporal sem recuar a etapa atual, e um registo aduaneiro pós-entrega nunca reverte um envio já entregue.

Enquanto tens a encomenda aberta no Peek, ou no dia da entrega com as notificações ativadas, o Peek consulta até de 10 em 10 minutos; no dia em que a Kuehne+Nagel mostra `In delivery` ou `Arrival` do camião na morada de destino, de 2 em 2 minutos a partir das 6h00 no relógio do registo mais recente enquanto o rastreio da Kuehne+Nagel responder. Caso contrário, o Peek verifica de hora a hora. Após 12 horas apenas em «Etiqueta criada» ou «Aceite pela transportadora», ou dois dias sem novidades, verifica de hora a hora independentemente da forma como acompanhas a encomenda, e de 6 em 6 horas após sete dias em «Etiqueta criada». Quando a Kuehne+Nagel não responde e o Peek acompanha a encomenda apenas através de serviços de rastreio, verifica de hora a hora, de 3 em 3 horas após um dia sem novidades e até de 15 em 15 minutos no dia da distribuição enquanto tens a encomenda aberta ou as notificações ativadas.

Quando o registo mais recente tem quatro dias e a encomenda continua em «Etiqueta criada», «Aceite pela transportadora» ou «Em trânsito», o Peek assinala-a com o dia desse registo, como «Sem atualizações desde seg., 12/10». A página da encomenda apresenta então «É habitual uma encomenda ficar sem novidades em voos longos, na alfândega ou ao fim de semana. O Peek continua a perguntar a Kuehne+Nagel.» Passadas três semanas sem novos registos, mostra «Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, contacta o vendedor. O Peek continua a perguntar a Kuehne+Nagel.»

Podes escolher alertas de notificação para **Todos os registos**, **Passos importantes** ou **Só a entrega** nas definições da encomenda.

## Contactar a Kuehne+Nagel

O site da Kuehne+Nagel disponibiliza um localizador de escritórios e um formulário de contacto:

- **Site global e portal de rastreio:** [kuehne-nagel.com](https://www.kuehne-nagel.com/) e [myKN Public Tracking](https://mykn.kuehne-nagel.com/public-tracking/)
- **Diretório de escritórios locais:** [Kuehne+Nagel locations worldwide](https://www.kuehne-nagel.com/company/locations) para encontrar a delegação nacional ou local mais próxima
- **Formulário de pedido online:** [Talk to an expert](https://www.kuehne-nagel.com/contact/talk-to-an-expert) para obter resposta por e-mail
- **Outras redes de carga:** se a tua encomenda foi dividida por várias transportadoras, compara com [DHL Freight](carrier:dhl-freight) e [DSV](carrier:dsv).

## Perguntas frequentes

### Por que diz o meu número de rastreio Kuehne+Nagel que não foram encontrados resultados?

O rastreio público da Kuehne+Nagel abrange apenas envios iniciados nos últimos 90 dias. Confirma se não faltam carateres e, se a reserva acabou de ser criada, aguarda o primeiro marco efetivo. Se uma referência corresponder a vários envios ao mesmo tempo, pede ao expedidor o número `RWM`, `RAPI` ou outro código próprio da Kuehne+Nagel.

### O que significam «Service complete» ou «Freight handover» na Kuehne+Nagel?

`Freight handover` significa que a Kuehne+Nagel transferiu a carga para um despachante aduaneiro ou transportador seguinte, pelo que o envio continua a caminho de ti. `Service complete` marca o fim do troço contratado à Kuehne+Nagel — que pode ser uma transferência num porto ou aeroporto em vez da entrega à porta —, por isso confirma se foi emitido um número separado da transportadora final.

### Por que faltam no histórico do Peek algumas datas que aparecem no myKN?

A vista pública de rota da Kuehne+Nagel apresenta datas previstas a par dos registos efetivos e avisos futuros sem data. O Peek guarda apenas os marcos que a Kuehne+Nagel assinala como efetivamente alcançados, para que metas previstas não surjam como movimentos concluídos.

### Durante quanto tempo fica disponível o rastreio público da Kuehne+Nagel?

O rastreio público em `mykn.kuehne-nagel.com` cobre envios iniciados nos últimos 90 dias. Para consultar mais histórico, o myKN oferece registo gratuito.

:::sources
- [Site global Kuehne+Nagel](https://www.kuehne-nagel.com/)
- [myKN Public Tracking](https://mykn.kuehne-nagel.com/public-tracking/)
- [Escritórios Kuehne+Nagel no mundo](https://www.kuehne-nagel.com/company/locations)
- [Kuehne+Nagel Talk to an expert](https://www.kuehne-nagel.com/contact/talk-to-an-expert)
- [adaptador Kuehne+Nagel do universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/kuehne-nagel)
:::
