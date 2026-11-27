---
title: Rastreamento DHL Freight: códigos ActiveTracing e status
description: Rastreie cargas rodoviárias ou ferroviárias da DHL Freight via ActiveTracing: códigos JND, códigos de pedido, IDs de remessa, SSCC, agendamentos e suporte.
slug: dhl-freight-tracking
published: 2026-11-26
updated: 2026-11-26
---

A DHL Freight (*DHL Freight GmbH*, parte do Grupo DHL) é a divisão europeia de carga rodoviária e ferroviária da DHL, com subsidiárias em mais de 40 países e mais de 45 milhões de toneladas movimentadas por ano em modalidades LTL (carga fracionada), PTL (carga parcial), FTL (carga completa), temperatura controlada, alta segurança e rede ferroviária europeia. Além de cargas paletizadas B2B (como *EuroConnect*), a DHL Freight também entrega pedidos residenciais volumosos (*DHL Home Delivery*) e remessas domésticas suecas *DHL Service Point*. Você pode consultar uma remessa no [DHL ActiveTracing](https://activetracing.dhl.com/DatPublic/datSelection.do) ou colar a referência na caixa acima.

## Códigos DHL Freight: códigos de pedido, IDs de remessa e códigos JND

O DHL ActiveTracing aceita vários tipos de referência e mantém o histórico on-line por **seis meses**:

| Formato (inventado) | O que é |
| --- | --- |
| `JND48291736405829173` | **DHL Package ID / License Plate** de 20 caracteres (`JND` + 17 dígitos). |
| `FRA-EC-4829173` | **Código de pedido DHL (Order code)**: código do terminal de 3 letras + código de produto de 2 letras (como `EC` para EuroConnect) + 7 dígitos (com ou sem hifens). |
| `4829173640` | **Consignment ID**: 9, 10 ou 11 dígitos (IDs de 10 dígitos são comuns em remessas suecas *DHL Service Point*). Compartilha o tamanho de 10 dígitos com [DHL](carrier:dhl), [Mondial Relay](carrier:mondial-relay), Relais Colis e [Packeta](carrier:packeta). |
| `00348291736405829177` | **GS1 SSCC** de 20 dígitos iniciado por `00` (com dígito verificador GS1 válido). Compartilha o formato SSCC de 20 dígitos com [USPS](carrier:usps) e [NZ Post](carrier:nz-post). |

Encomendas comuns na Alemanha usam [DHL](carrier:dhl) (*DHL Paket*) e remessas aéreas expressas internacionais usam [DHL Express](carrier:dhl-express), enquanto paletes, móveis pesados e cargas rodoviárias/ferroviárias europeias são acompanhadas no ActiveTracing da DHL Freight. Veja [formatos de código de rastreamento](guide:tracking-number-formats).

## O que significam os status do DHL Freight ActiveTracing

O ActiveTracing exibe cada registro com frase de status em inglês ou alemão, o terminal (como `DE-KOBLENZ`) e a data/hora do terminal sem fuso horário:

| A DHL Freight diz (EN / DE) | O que significa para você | O Peek mostra |
| --- | --- | --- |
| `Consignment created`, `Collection arranged - OK`, `Out for Collection`, `Not Collected - ...` / `Auftrag erfasst`, `In Abholung` | Ordem de frete cadastrada ou veículo enviado para coleta; a mercadoria ainda está com o embarcador. | "Etiqueta criada" |
| `Collected`, `Shipment handover by sender` / `Abholung` | Coletada junto ao embarcador ou postada em um DHL Service Point na Suécia. | "Recebida pela transportadora" |
| `Received at terminal`, `Shipped from terminal`, `Arrival at destination`, `Loaded into ferry`, `Direct delivery`, `Receiver notified`, `Shipment in warehouse - Awaiting delivery appointment` / `Delivery appointment booked` / `Empfangen im Terminal`, `Verladen`, `Avisierung` | Circulando entre terminais da DHL Freight, em travessia de balsa, liberada pela alfândega (`Customs cleared`) ou aguardando no terminal de destino o agendamento da entrega. | "Em trânsito" |
| `On hold - in customs clearance` | Retida para desembaraço aduaneiro internacional (por exemplo, Suíça, Reino Unido ou Noruega). | "Na alfândega" |
| `Out for Delivery` / `In Zustellung` | Carregada no caminhão local para entrega hoje. | "Saiu para entrega" |
| `Shipment in warehouse - ready for self collection by consignee` | Disponível para retirada pelo destinatário no terminal local da DHL Freight. | "Pronta para retirada" |
| `Attempted DHL delivery - ...` | Houve tentativa de entrega, mas ela não foi concluída (por exemplo, local fechado ou recusa). | "Tentativa de entrega" |
| `Shipment in warehouse - waiting for consignee payment`, `Shipment delivered with exception - partly delivered`, `Exception occurred - Strike`, `New shipping instruction - consignment cancelled by sender` | Retenção aguardando pagamento, entrega parcial com falta, greve ou cancelamento pelo remetente. | "Precisa de atenção" |
| `New shipping instruction - shipment returned` | Carga devolvida ao remetente conforme novas instruções. | "Devolvida ao remetente" |
| `Delivered`, `Received by consignee`, `Picked up by receiver`, `Shipment delivered with exception` / `Zugestellt` | Entregue ao destinatário ou retirada no terminal / Service Point. | "Entregue" |

## Agendamentos (*Avisierung*), alfândega e regras de carga

Segundo [DHL Freight Alemanha](https://www.dhl.com/de-en/home/freight.html) e a [Central de Ajuda DHL Freight](https://www.dhl.com/de-en/home/freight/help-center-for-european-road-and-rail.html):

- **Agendamentos (*Avisierung*):** uma carga pode aguardar no terminal de entrega em `Shipment in warehouse - Awaiting delivery appointment` até combinar uma data com o destinatário; depois surge `Delivery appointment booked`. Se ficar parada ali, fale com o embarcador ou o terminal de entrega.
- **Retenção de seis meses no ActiveTracing:** [activetracing.dhl.com](https://activetracing.dhl.com/DatPublic/datSelection.do) mantém o histórico dos **últimos seis meses** em inglês, alemão, espanhol, francês, sueco, polonês e italiano.
- **Alfândega europeia:** cargas rodoviárias entre a UE e vizinhos fora da UE (Reino Unido, Suíça, Noruega) exigem desembaraço aduaneiro (`On hold - in customs clearance`). Para compras on-line de fora da UE, aplicam-se o imposto aduaneiro de €3 em remessas até €150 e a taxa de manuseio da UE de €2 pela [Reforma Aduaneira da UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en); veja [encomendas retidas na alfândega](guide:customs).

## Carga DHL Freight atrasada, danificada ou entregue parcialmente

O contratante do transporte junto à DHL Freight é o embarcador, então contate o vendedor ou remetente primeiro.

- **Dano visível ou falta (`partly delivered`):** confira mercadorias e quantidade de volumes na chegada e avise o remetente imediatamente. A Central de Ajuda tem a página *How to Prepare a Claim*.
- **Aguardando agendamento ou pagamento:** se o ActiveTracing exibir `Awaiting delivery appointment` ou `waiting for consignee payment`, acione imediatamente o terminal de destino listado na última linha (ou o embarcador).
- Veja [por que o rastreamento para de atualizar](guide:tracking-not-updating), [entregue mas não recebida](guide:delivered-not-received), [encomenda danificada](guide:damaged-parcel), [encomenda extraviada](guide:lost-parcel) e [devolvida ao remetente](guide:returned-to-sender).

## Atendimento e contatos da DHL Freight

Canais publicados na [Central de Ajuda DHL Freight](https://www.dhl.com/de-en/home/freight/help-center-for-european-road-and-rail.html) e em [DHL Freight Alemanha](https://www.dhl.com/de-en/home/freight.html):

- **Central de Ajuda para Rodoviário e Ferroviário Europeu:** [dhl.com/de-en/home/freight/help-center-for-european-road-and-rail.html](https://www.dhl.com/de-en/home/freight/help-center-for-european-road-and-rail.html) — FAQs, *How to Prepare a Claim*, Incoterms 2020, Termos e Condições e normas de Mercadorias Perigosas.
- **Viva e localizador de terminais:** a Central de Ajuda conta com **Viva**, assistente de atendimento com IA da DHL Freight, e *Freight Terminals and Locations* para localizar terminais e escritórios por país.
- **Portal empresarial:** *myDHLFreight*, com vídeos tutoriais e FAQs na Central de Ajuda.

> Ignore links em SMS ou e-mails não solicitados cobrando pagamento on-line inesperado para "liberar" uma carga da DHL Freight. Confira sempre diretamente em [activetracing.dhl.com](https://activetracing.dhl.com/DatPublic/datSelection.do) ou com o terminal de destino da DHL Freight. Veja [golpes de entrega por SMS](guide:delivery-scam-texts).

## Acompanhe uma remessa DHL Freight com o Peek

Para um código `JND` (`JND48291736405829173`, inventado), o [Peek](/) consulta a DHL Freight, a única transportadora que ele consulta para esse formato, e exibe "DHL Freight está com esta encomenda." assim que o ActiveTracing responde por ela. Para Consignment IDs de 10 dígitos (`4829173640`), consulta a DHL Freight entre as transportadoras de 10 dígitos quando você rastreia da Alemanha, Suécia, Áustria, Polônia, Países Baixos, Bélgica, França, Itália, Espanha ou Reino Unido (e apenas em último caso nos demais países). Também reconhece links colados de `activetracing.dhl.com/DatPublic/datSelection.do` (`a=`, `valuePackageField=` ou `valueShipmentOrderField=`). Para códigos de pedido (`FRA-EC-4829173`), Consignment IDs de 9 ou 11 dígitos ou SSCCs (`00348291736405829177`), toque em "Trocar" ao adicionar ou "Trocar transportadora" na tela da encomenda e escolha DHL Freight (buscar "DHL ActiveTracing", "DHL Frachtverfolgung" ou "DHL Freight GmbH" também funciona).

O Peek lê diretamente a página em inglês do ActiveTracing e mostra a etapa ("Etiqueta criada", "Recebida pela transportadora", "Em trânsito", "Na alfândega", "Saiu para entrega", "Pronta para retirada", "Tentativa de entrega", "Precisa de atenção", "Devolvida ao remetente", "Entregue"), as frases do ActiveTracing com o terminal (como `KOBLENZ, DE`), o peso bruto e o país de destino. Cada linha usa o fuso do terminal e aparece no relógio do seu aparelho. O nome do serviço não é exibido nos apps do Peek; a cidade do destinatário, única parte do endereço que o ActiveTracing mostra, pode ser o fim da rota no seu próprio mapa enquanto a remessa está a caminho; links compartilhados não a mostram. Origem, número de volumes, cubagem, data de partida, horário separado de entrega, outras referências, comprovante e assinatura ficam de fora; o ActiveTracing não informa data prevista, códigos de status nem nomes de remetente ou destinatário.

Com a remessa aberta no Peek, ou no dia da entrega com notificações ligadas, o Peek consulta até a cada 10 minutos; no dia em que o ActiveTracing exibe `Out for Delivery`, a cada 2 minutos (a cada 5 minutos após consulta via navegador) a partir das 6h no fuso da última movimentação, enquanto o ActiveTracing responder. Nos demais casos, verifica a cada hora. Após 12 horas apenas em "Etiqueta criada" ou "Recebida pela transportadora", ou dois dias sem novidades, passa a verificar a cada hora de qualquer modo, e a cada 6 horas depois de sete dias em "Etiqueta criada". Quando o ActiveTracing não responde e o Peek acompanha apenas por serviços de rastreamento, consulta a cada hora, a cada 3 horas após um dia sem novidades e até a cada 15 minutos no dia da entrega.

Quando a movimentação mais recente completa quatro dias e a carga segue em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito", o Peek sinaliza com a data desse registro, como "Sem atualizações desde seg 12 out". A página então mostra "É comum uma encomenda ficar sem atualizações em voos longos, na alfândega ou no fim de semana. O Peek continua consultando DHL Freight.", com um link "Pedir ajuda a DHL Freight" para a Central de Ajuda. Após três semanas sem novidade, exibe "Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, fale com o vendedor. O Peek continua consultando DHL Freight."

Você pode escolher alertas para "Cada movimentação", "Etapas importantes" ou "Só a entrega". O Peek apenas acompanha o rastreamento e não agenda entregas, não paga fretes nem contata terminais da DHL Freight por você.

## Perguntas sobre o rastreamento da DHL Freight

### Qual é a diferença entre DHL Freight, DHL Paket e DHL Express?

A DHL Freight cuida do transporte rodoviário e ferroviário europeu (paletes, LTL/PTL/FTL, *DHL Home Delivery* volumoso e carga doméstica na Suécia) rastreado no [ActiveTracing](https://activetracing.dhl.com/DatPublic/datSelection.do), enquanto encomendas padrão usam [DHL](carrier:dhl) e entregas aéreas expressas internacionais com hora definida usam [DHL Express](carrier:dhl-express).

### Quais códigos funcionam no DHL ActiveTracing?

O ActiveTracing aceita códigos de pedido DHL (`FRA-EC-4829173`, inventado), Consignment IDs de 9 a 11 dígitos (`4829173640`), códigos de volume `JND` de 20 caracteres (`JND48291736405829173`) e códigos GS1 SSCC de 20 dígitos (`00348291736405829177`).

### Por que minha remessa está parada em `Awaiting delivery appointment`?

O terminal de entrega aguarda combinar o dia de entrega com o destinatário. Fale com o embarcador ou com o terminal citado na última movimentação.

### Por quanto tempo o DHL ActiveTracing mantém o histórico?

O [ActiveTracing](https://activetracing.dhl.com/DatPublic/datSelection.do) informa que os dados ficam disponíveis on-line pelos **últimos seis meses**.

:::sources
- [DHL Freight: ActiveTracing](https://activetracing.dhl.com/DatPublic/datSelection.do) – consulta por ID de remessa, código de pedido e Package ID/License Plate, retenção de 6 meses, *DHL Freight GmbH*
- [DHL Freight: Germany Home (English)](https://www.dhl.com/de-en/home/freight.html) – mais de 40 países, mais de 45 milhões de toneladas por ano, LTL/PTL/FTL, GoGreen Plus Flex, Rail Freight Europe e rede de terminais
- [DHL Freight: Help Center for European Road and Rail (English)](https://www.dhl.com/de-en/home/freight/help-center-for-european-road-and-rail.html) – assistente de IA Viva, localizador Freight Terminals, How to Prepare a Claim, Incoterms 2020 e myDHLFreight (URL de ajuda indicada pelo Peek)
- [European Commission: EU Customs Reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – imposto aduaneiro de €3 até €150 e taxa de manuseio da UE de €2
- [Universal Parcel Scraper: dhl-freight](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/dhl-freight) – leitura de `JND`, código de pedido, ID de remessa de 9 a 11 dígitos e SSCC, regras de links `activetracing.dhl.com`, mapa de status em inglês/alemão, relógio local de cada terminal, peso e país de destino lidos, e URL de ajuda
:::
