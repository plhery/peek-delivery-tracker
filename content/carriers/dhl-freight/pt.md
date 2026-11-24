---
title: Rastreio DHL Freight: códigos ActiveTracing e ajuda
description: Rastreia um envio rodoviário ou ferroviário DHL Freight no ActiveTracing: códigos JND, códigos de pedido, ID de envio, SSCC, agendamento e apoio.
slug: rastreio-dhl-freight
published: 2026-11-24
updated: 2026-11-24
---

A DHL Freight (*DHL Freight GmbH*, parte do DHL Group) é a divisão europeia de carga rodoviária e ferroviária da DHL, com filiais em mais de 40 países e mais de 45 milhões de toneladas movimentadas por ano nas suas redes LTL (carga agrupada), PTL (carga parcial), FTL (carga completa), temperatura controlada, alta segurança e ferrovia europeia. Além de carga paletizada B2B (como *EuroConnect*), a DHL Freight também entrega encomendas residenciais volumosas (*DHL Home Delivery*) e envios internos suecos *DHL Service Point*. Podes consultar um envio em [DHL ActiveTracing](https://activetracing.dhl.com/DatPublic/datSelection.do) ou colar a tua referência na caixa acima.

## Números de rastreio DHL Freight: códigos de pedido, IDs de envio e códigos JND

O DHL ActiveTracing aceita vários tipos de referência e mantém o histórico de envios online durante **seis meses**:

| Formato (inventado) | O que é |
| --- | --- |
| `JND48291736405829173` | **DHL Package ID / License Plate** de 20 caracteres (`JND` + 17 dígitos). |
| `FRA-EC-4829173` | **Código de pedido DHL (Order code)**: código de terminal de 3 letras + código de produto de 2 letras (como `EC` para EuroConnect) + 7 dígitos (com ou sem hífenes). |
| `4829173640` | **ID de envio (Consignment ID)**: 9, 10 ou 11 dígitos (os de 10 dígitos são comuns nos envios internos suecos *DHL Service Point*). Partilha o formato de 10 dígitos com [DHL](carrier:dhl), [Mondial Relay](carrier:mondial-relay), [Relais Colis](carrier:relais-colis) e [Packeta](carrier:packeta). |
| `00348291736405829177` | Identificador de volume **GS1 SSCC** de 20 dígitos começado por `00` (dígito de controlo GS1 válido). Partilha o formato SSCC de 20 dígitos com [USPS](carrier:usps) e [NZ Post](carrier:nz-post). |

Nota que as entregas normais de encomendas na Alemanha usam a [DHL](carrier:dhl) (*DHL Paket*) e os documentos ou encomendas aéreas expresso internacionais usam a [DHL Express](carrier:dhl-express), enquanto paletes, mobiliário pesado e carga europeia por estrada ou ferrovia se seguem no portal ActiveTracing da DHL Freight. Consulta [formatos de números de rastreio](guide:tracking-number-formats).

## O que significam os estados do DHL Freight ActiveTracing

O ActiveTracing apresenta em cada linha uma frase de estado, em inglês ou alemão, o terminal (por exemplo `DE-KOBLENZ`) e a data e hora desse terminal, sem indicação de fuso horário:

| A DHL Freight indica (EN / DE) | O que significa para ti | O Peek mostra |
| --- | --- | --- |
| `Consignment created`, `Collection arranged - OK`, `Out for Collection`, `Not Collected - ...` / `Auftrag erfasst`, `In Abholung` | Ordem de transporte criada ou motorista enviado para recolha; a mercadoria ainda está com o expedidor. | «Etiqueta criada» |
| `Collected`, `Shipment handover by sender` / `Abholung` | Recolhida no expedidor ou entregue num DHL Service Point sueco. | «Aceite pela transportadora» |
| `Received at terminal`, `Shipped from terminal`, `Arrival at destination`, `Loaded into ferry`, `Direct delivery`, `Receiver notified`, `Shipment in warehouse - Awaiting delivery appointment` / `Delivery appointment booked` / `Empfangen im Terminal`, `Verladen`, `Avisierung` | Em circulação entre terminais DHL Freight, em travessia de ferry, desalfandegada (`Customs cleared`) ou retida no terminal de destino a aguardar marcação de entrega. | «Em trânsito» |
| `On hold - in customs clearance` | Retida para desalfandegamento transfronteiriço (ex.: Suíça, Reino Unido ou Noruega). | «Na alfândega» |
| `Out for Delivery` / `In Zustellung` | Carregada no camião local para entrega hoje. | «Em distribuição» |
| `Shipment in warehouse - ready for self collection by consignee` | Pronta para levantamento pelo destinatário no terminal local da DHL Freight. | «Pronta para levantamento» |
| `Attempted DHL delivery - ...` | Tentativa de entrega não concluída (por exemplo destinatário fechado ou recusa). | «Tentativa de entrega» |
| `Shipment in warehouse - waiting for consignee payment`, `Shipment delivered with exception - partly delivered`, `Exception occurred - Strike`, `New shipping instruction - consignment cancelled by sender` | Retenção por pagamento, entrega parcial com volumes em falta, perturbação por greve ou cancelamento pelo remetente. | «Problema reportado» |
| `New shipping instruction - shipment returned` | Carga devolvida ao remetente segundo novas instruções. | «Devolvida ao remetente» |
| `Delivered`, `Received by consignee`, `Picked up by receiver`, `Shipment delivered with exception` / `Zugestellt` | Entregue ao destinatário ou levantada no terminal / Service Point. | «Entregue» |

## Agendamentos de entrega (*Avisierung*), alfândega e regras de carga

Segundo [DHL Freight Alemanha](https://www.dhl.com/de-en/home/freight.html) e o [Centro de Ajuda da DHL Freight](https://www.dhl.com/de-en/home/freight/help-center-for-european-road-and-rail.html):

- **Agendamentos de entrega (*Avisierung*):** um envio pode aguardar no terminal de destino em `Shipment in warehouse - Awaiting delivery appointment` até ser combinada uma data com o destinatário; assim que fica marcada surge `Delivery appointment booked`. Se continuar em espera, fala com o expedidor ou com o terminal de destino.
- **Histórico de seis meses no ActiveTracing:** [activetracing.dhl.com](https://activetracing.dhl.com/DatPublic/datSelection.do) apresenta o histórico de envios dos **últimos seis meses** em inglês, alemão, espanhol, francês, sueco, polaco e italiano.
- **Alfândega transfronteiriça europeia:** a carga rodoviária entre a UE e países vizinhos fora da UE (como Reino Unido, Suíça ou Noruega) exige desalfandegamento (`On hold - in customs clearance`). Para encomendas de comércio eletrónico de fora da UE que entram na UE aplicam-se o direito aduaneiro de 3 € em envios até 150 € e a taxa de tratamento da UE de 2 € no âmbito da [reforma aduaneira da UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en); vê [encomendas retidas na alfândega](guide:customs).

## Envio DHL Freight atrasado, danificado ou entregue parcialmente

Foi o expedidor quem contratou o transporte com a DHL Freight, por isso contacta primeiro o vendedor ou remetente.

- **Danos visíveis ou volumes em falta (`partly delivered`):** verifica a mercadoria e a quantidade de volumes à chegada e informa logo o expedidor. O Centro de Ajuda da DHL Freight tem uma página *How to Prepare a Claim*.
- **A aguardar agendamento ou pagamento:** se o ActiveTracing mostrar `Awaiting delivery appointment` ou `waiting for consignee payment`, contacta de imediato o terminal de destino indicado no último registo (ou o expedidor).
- Vê [por que razão o rastreio deixa de atualizar](guide:tracking-not-updating), [entregue mas não recebida](guide:delivered-not-received), [encomenda danificada](guide:damaged-parcel), [encomenda perdida](guide:lost-parcel) e [devolvida ao remetente](guide:returned-to-sender).

## Apoio ao cliente e contactos DHL Freight

Canais de apoio publicados no [Centro de Ajuda da DHL Freight](https://www.dhl.com/de-en/home/freight/help-center-for-european-road-and-rail.html) e em [DHL Freight Alemanha](https://www.dhl.com/de-en/home/freight.html):

- **Centro de Ajuda para transporte rodoviário e ferroviário europeu:** [dhl.com/de-en/home/freight/help-center-for-european-road-and-rail.html](https://www.dhl.com/de-en/home/freight/help-center-for-european-road-and-rail.html) — perguntas frequentes, *How to Prepare a Claim*, Incoterms 2020, Termos e Condições e regras sobre mercadorias perigosas.
- **Viva e localizador de terminais:** o Centro de Ajuda disponibiliza a **Viva**, assistente de apoio ao cliente com IA da DHL Freight, e *Freight Terminals and Locations* para encontrar terminais e escritórios por país.
- **Portal empresarial:** *myDHLFreight*, com vídeos tutoriais e FAQ no Centro de Ajuda.

> Ignora links não solicitados por SMS ou e-mail que peçam um pagamento online inesperado para «libertar» um envio da DHL Freight. Verifica qualquer estado diretamente em [activetracing.dhl.com](https://activetracing.dhl.com/DatPublic/datSelection.do) ou junto do terminal de destino da DHL Freight. Vê [SMS fraudulentas de entregas](guide:delivery-scam-texts).

## Rastrear um envio DHL Freight com o Peek

[O Peek](/) consulta a DHL Freight perante um código `JND` (`JND48291736405829173`, inventado), a única transportadora que consulta para esse formato, e mostra «DHL Freight tem esta encomenda.» assim que o ActiveTracing responde. Para um ID de envio de 10 dígitos (`4829173640`), o Peek consulta a DHL Freight entre as transportadoras de dez dígitos quando rastreias a partir da Alemanha, Suécia, Áustria, Polónia, Países Baixos, Bélgica, França, Itália, Espanha ou Reino Unido, e apenas em último recurso noutros países. O Peek também reconhece links colados de `activetracing.dhl.com/DatPublic/datSelection.do` (`a=`, `valuePackageField=` ou `valueShipmentOrderField=`). Para um código de pedido (`FRA-EC-4829173`), um ID de envio de 9 ou 11 dígitos ou um SSCC (`00348291736405829177`), toca em «Alterar» ao adicionar ou em «Alterar transportadora» no ecrã da encomenda e escolhe DHL Freight (pesquisar «DHL ActiveTracing», «DHL Frachtverfolgung» ou «DHL Freight GmbH» também a encontra).

O Peek lê diretamente a página em inglês do ActiveTracing e mostra a etapa («Etiqueta criada», «Aceite pela transportadora», «Em trânsito», «Na alfândega», «Em distribuição», «Pronta para levantamento», «Tentativa de entrega», «Problema reportado», «Devolvida ao remetente», «Entregue»), as frases de estado do ActiveTracing com o terminal (como `KOBLENZ, DE`), o peso bruto e o país de destino. O Peek data cada linha no fuso horário do respetivo terminal e apresenta-a na hora do teu dispositivo. O nome do serviço fica de fora em todas as apps do Peek. A localidade do destinatário, a única parte da morada mostrada pelo ActiveTracing, pode encerrar o percurso no teu mapa enquanto o envio está a caminho; os links partilhados não a mostram. O Peek omite a origem, o número de volumes, o volume cúbico, a data de partida, a hora de entrega separada, outras referências, a prova de entrega e a assinatura; o ActiveTracing não indica data estimada de entrega, códigos de estado nem nomes de remetente ou destinatário.

Enquanto tiveres a encomenda aberta no Peek, ou no dia da entrega com as notificações ativadas, o Peek consulta até de 10 em 10 minutos; no dia em que o ActiveTracing mostra `Out for Delivery`, de 2 em 2 minutos (de 5 em 5 após uma verificação que abriu um navegador) a partir das 6h na hora do local do último registo, enquanto o ActiveTracing responder. Caso contrário, verifica de hora a hora. Após 12 horas apenas em «Etiqueta criada» ou «Aceite pela transportadora», ou dois dias sem novidades, passa a verificar de hora a hora seja qual for o modo de acompanhamento, e de 6 em 6 horas após sete dias em «Etiqueta criada». Quando o ActiveTracing não responde e o Peek acompanha o envio apenas através de serviços de rastreio, verifica de hora a hora, de 3 em 3 horas após um dia sem registos e até de 15 em 15 minutos no dia da entrega enquanto tiveres a encomenda aberta ou as notificações ativadas.

Quando o registo mais recente tem quatro dias e a encomenda continua em «Etiqueta criada», «Aceite pela transportadora» ou «Em trânsito», o Peek assinala-o com o dia desse registo, como «Sem atualizações desde seg., 12/10». A página da encomenda mostra então «É habitual uma encomenda ficar sem novidades em voos longos, na alfândega ou ao fim de semana. O Peek continua a perguntar a DHL Freight.», com o link «Pede ajuda a DHL Freight» para o Centro de Ajuda da DHL Freight. Após três semanas sem novos registos, mostra «Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, contacta o vendedor. O Peek continua a perguntar a DHL Freight.»

Podes escolher alertas para «Todos os registos», «Passos importantes» ou «Só a entrega». O Peek acompanha apenas as atualizações de rastreio e não pode marcar um agendamento de entrega, pagar fretes nem contactar um terminal DHL Freight em teu nome.

## Perguntas frequentes sobre o rastreio DHL Freight

### Qual é a diferença entre DHL Freight, DHL Parcel e DHL Express?

A DHL Freight trata do transporte europeu rodoviário e ferroviário (paletes, LTL/PTL/FTL, *DHL Home Delivery* volumoso e carga interna sueca) rastreado no [ActiveTracing](https://activetracing.dhl.com/DatPublic/datSelection.do), enquanto encomendas normais seguem pela [DHL](carrier:dhl) e envios aéreos internacionais expresso com prazo definido seguem pela [DHL Express](carrier:dhl-express).

### Que números de rastreio funcionam no DHL ActiveTracing?

O ActiveTracing aceita códigos de pedido DHL (`FRA-EC-4829173`, inventado), IDs de envio de 9 a 11 dígitos (`4829173640`), códigos de volume / License Plates `JND` de 20 caracteres (`JND48291736405829173`) e números GS1 SSCC de 20 dígitos (`00348291736405829177`).

### Porque é que o meu envio está retido em `Awaiting delivery appointment`?

O terminal de entrega guarda a mercadoria até ser acordado um dia de entrega com o destinatário. Pergunta ao expedidor ou ao terminal indicado na última linha do rastreio.

### Durante quanto tempo o DHL ActiveTracing guarda o histórico?

O [ActiveTracing](https://activetracing.dhl.com/DatPublic/datSelection.do) indica que os dados dos envios permanecem disponíveis online durante os **últimos seis meses**.

:::sources
- [DHL Freight: ActiveTracing](https://activetracing.dhl.com/DatPublic/datSelection.do) – pesquisa por ID de envio, código de pedido e Package ID / License Plate, retenção de 6 meses, *DHL Freight GmbH*
- [DHL Freight: página Alemanha (inglês)](https://www.dhl.com/de-en/home/freight.html) – mais de 40 países, mais de 45 M toneladas/ano, LTL/PTL/FTL, GoGreen Plus Flex, Rail Freight Europe e rede de terminais
- [DHL Freight: Centro de Ajuda rodoviário e ferroviário europeu (inglês)](https://www.dhl.com/de-en/home/freight/help-center-for-european-road-and-rail.html) – assistente IA Viva, localizador de terminais, How to Prepare a Claim, Incoterms 2020 e myDHLFreight (URL de ajuda ligado pelo Peek)
- [Comissão Europeia: reforma aduaneira da UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – direito aduaneiro de 3 € até 150 € e taxa de tratamento da UE de 2 €
- [Universal Parcel Scraper: dhl-freight](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/dhl-freight) – leitura de `JND`, código de pedido, ID de envio de 9–11 dígitos e SSCC, regras de links `activetracing.dhl.com`, mapa de estados inglês/alemão, hora local de cada terminal, peso e país de destino lidos, e URL de ajuda
:::
