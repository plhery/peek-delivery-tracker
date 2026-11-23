---
title: Seguimento DSV: números, estados e DB Schenker
description: Segue um envio DSV ou ex-DB Schenker (rodoviário, aéreo ou marítimo): que número usar, significado dos estados, entrega, reclamações e contacto.
slug: seguimento-dsv
published: 2026-11-23
updated: 2026-11-23
---

A DSV é um grupo dinamarquês de transporte e logística que movimenta carga por estrada, ar e mar, tendo comprado a Schenker (DB Schenker) à Deutsche Bahn em 2025. Em dsv.com, a caixa «Track your shipment» tem dois campos: «Track DSV shipment» abre o seguimento da DSV, o myDSV, e «Track Schenker shipment» o antigo da DB Schenker. Não precisas de conta. O número vem de quem reservou o transporte, normalmente o remetente ou a loja: cola-o na caixa acima.

## Número de seguimento DSV: formato e onde encontrar

A empresa que contratou a DSV tem o número: procura no e-mail de envio da loja, na página do pedido ou pergunta ao remetente. Mais em [como encontrar o teu número de seguimento](guide:find-tracking-number).

Enquanto a DSV integra a Schenker nos seus sistemas, mantém dois rastreadores: um envio reservado na Schenker pode constar apenas no da Schenker. Se um não mostrar nada, experimenta o outro.

| Número (inventado) | O que é |
| --- | --- |
| `DEKLM482917365` | 14 carateres: código de país europeu como DE, SE, DK ou PL, três letras e nove dígitos. Antigo número rodoviário da DB Schenker e o formato que o catálogo do Peek atribui à DSV. |
| `KLM4829173` | Três letras e sete ou oito dígitos: outro antigo número rodoviário da DB Schenker. O Peek não identifica a DSV por esta forma. |
| `094827361549283710` | 18 dígitos (20 com `00` à frente): o código de barras de um volume de um antigo envio rodoviário DB Schenker. |
| Letras e dígitos | Número de envio, reserva ou *house bill* do myDSV. O Peek consulta o myDSV com quaisquer 5 a 40 letras e dígitos que incluam pelo menos um dígito. |
| `mydsv.com/track-shipment?query=DEKLM482917365` | Ligação para o seguimento myDSV, tal como abre «Track DSV shipment» em dsv.com. |

Uma referência de cliente ou encomenda, ou o conhecimento aéreo principal (*master air waybill*), encontra envios na pesquisa do myDSV, mas o Peek não os acompanha: usa o número de envio, reserva ou *house bill* da DSV. O Peek também não escolhe entre vários envios quando um número corresponde a mais do que um.

## O que significam os estados de seguimento da DSV

O myDSV regista cada leitura desde a reserva até à prova de entrega. A vista pública mostra dados gerais; a vista privada, mediante pedido, apresenta os detalhes completos. Etapas do myDSV lidas pelo Peek:

| A DSV mostra | O que significa para ti | Estado no Peek |
| --- | --- | --- |
| `Booking` | A DSV tem a reserva, com data mas sem hora. | «Etiqueta criada» |
| `Creation` | O envio foi criado no sistema rodoviário da DSV. | «Etiqueta criada» |
| `Actual pickup` | A DSV recolheu a mercadoria no remetente. | «Aceite pela transportadora» |
| `Cargo received` | A carga chegou à DSV, num terminal ou após a recolha. | «Aceite pela transportadora» |
| `Actual Departure`, `Actual departure - Origin Port` | Partiu de um porto ou aeroporto no trajeto principal. | «Em trânsito» |
| `Actual arrival`, `Actual arrival- Destination Port` | Chegou a um porto ou aeroporto no trajeto principal. | «Em trânsito» |
| `Handover responsibilty of cargo`, `Handover of Cargo` | A DSV entregou a carga no fim do seu serviço, concluindo o envio (o primeiro mantém a grafia da DSV). | «Entregue» |
| `Actual delivery` | Entregue. | «Entregue» |

`Estimated Departure`, `Estimated arrival`, `Estimated pickup`, `Estimated delivery`, `Requested pickup` e `Requested delivery` são previsões, não registos: o Peek omite-os do histórico e mostra `Estimated delivery` como dia previsto (ou intervalo de dias) até sair para entrega.

O antigo seguimento da DB Schenker cobre envios rodoviários com expressões próprias:

| O seguimento DB Schenker mostra | O que significa para ti | Estado no Peek |
| --- | --- | --- |
| `Booked` | O remetente reservou o transporte. | «Etiqueta criada» |
| `Out for collection` | Um motorista segue para recolher no remetente. | «Etiqueta criada» |
| `Collected` | Recolhido no remetente. | «Aceite pela transportadora» |
| `Delivered to terminal by shipper` | O remetente entregou num terminal. | «Aceite pela transportadora» |
| `Arrived`, `Departed` | Chegou ou saiu de um terminal. | «Em trânsito» |
| `Customs clearance initiated` | Desalfandegamento iniciado. | «Na alfândega» |
| `Customs clearance finalised` | Desalfandegado e novamente em viagem. | «Em trânsito» |
| `Out for Delivery` | No camião de entrega. | «Em distribuição» |
| `Not delivered` | Entrega falhada; pode incluir um motivo. | «Tentativa de entrega» |
| `To consignee's disposal`, `Ready for pick up` | Disponível para levantamento. | «Pronta para levantamento» |
| `Delivered` | Entregue. | «Entregue» |
| `Picked up by consignee`, `Picked up at agent` | Levantado pelo destinatário ou num agente. | «Entregue» |
| `ePOD available` | Prova eletrónica de entrega disponível. | «Entregue» |

Pelo caminho surgem ainda `Linehaul departed`, `Linehaul arrived`, `Terminal inventory`, `Vessel Loaded`, `Vessel Unloaded`, `Train Loaded`, `Train Unloaded`, `Vessel/Train Loaded` e `Vessel/Train Unloaded`: todos surgem como «Em trânsito» no Peek. `Not Collected` (recolha falhada) e `Not loaded` (falhou o embarque previsto, com motivo) mostram «Problema reportado». Quando o seguimento indica um motivo junto a uma etapa, o Peek acrescenta-o ao texto. Mais em [significado dos estados de seguimento](guide:tracking-statuses).

## Como a DSV entrega

**Paletes e cargas parciais.** A DSV apresenta a sua rede de grupagem rodoviária como a maior da Europa: mais de 36 países, mais de 400 terminais e envios até 2.500 kg, pelo que uma palete ou encomenda volumosa pode chegar de camião a partir de um terminal DSV.

**Encomendas online.** O serviço Parcel E-commerce da DSV recolhe encomendas de lojas, consolida-as entre fronteiras e recorre a redes locais para a entrega final, pelo que outra transportadora pode entregar a tua. Se o e-mail da loja indicar uma ou der um segundo número, segue esse também: a consulta DSV do Peek não indica transportadora seguinte.

**Atualizações e prova de entrega.** No myDSV os utilizadores podem configurar notificações (como recolha ou desalfandegamento), ver horas previstas de chegada para carga rodoviária e descarregar a prova de entrega no final. Se não reservaste tu a DSV, pergunta ao remetente. As páginas da DSV não indicam quantas tentativas faz nem quanto tempo um terminal guarda a carga: confirma junto do escritório DSV responsável.

**Alfândega.** Na maioria dos países onde tem escritórios, a DSV é despachante aduaneiro licenciado; nos restantes trabalha com agentes. Para apoio num desalfandegamento, a DSV pede contacto com a DSV nesse país. Na Irlanda e no resto da UE, compras online fora da UE pagam 3 € de direitos por tipo de produto em encomendas até 150 € desde 1 de julho de 2026 e 2 € de taxa de tratamento da União por tipo de produto, qualquer que seja o valor, desde 1 de novembro de 2026. Fora da UE aplicam-se as regras do teu país: vê [encomendas retidas na alfândega](guide:customs).

## Envio DSV atrasado, parado ou danificado

**Sem movimento.** Se um seguimento parar, tenta o outro em dsv.com: um envio Schenker pode ainda não constar do myDSV. Depois fala com o remetente que reservou a DSV. Vê [seguimento sem atualizações](guide:tracking-not-updating).

**Bens danificados ou em falta.** Numa compra online começa pela loja que reservou a DSV. O portal eClaims da DSV recebe reclamações e documentos, mostra o estado e liga-te aos gestores de sinistros locais; não indica prazo limite ali, pelo que não deves esperar. Vê [encomendas danificadas](guide:damaged-parcel) e [encomendas perdidas](guide:lost-parcel).

**Uso indevido do nome DSV.** A DSV alerta que empresas e pessoas sem ligação ao grupo usam por vezes o seu nome para vender serviços de carga. Em caso de dúvida sobre um site ou contacto, confirma no escritório DSV local; sobre SMS suspeitos de entregas, vê [SMS fraudulentos de entregas](guide:delivery-scam-texts).

## Contacto e apoio ao cliente da DSV

Em dsv.com:

- **Formulário de contacto:** na [página de ajuda e contacto da DSV](https://www.dsv.com/en/support/help-and-contact).
- **Escritórios locais:** a DSV está em mais de 90 países; [encontrar escritório local da DSV](https://www.dsv.com/en/support/find-dsv) por país.
- **Reclamações:** através do eClaims na [página Claims da DSV](https://www.dsv.com/en/support/self-services/claims).
- **DSV A/S:** Hedehusene, Dinamarca, +45 43 20 30 40 conforme o aviso legal (não é linha de seguimento).

As páginas de ajuda da DSV não disponibilizam número de telefone para seguimento.

## Seguir um envio DSV com o Peek

Cola um número de 14 carateres como `DEKLM482917365` e o Peek consulta a DSV ao adicionares («A verificar serviços de seguimento…»), mostrando «DSV tem esta encomenda.» quando a DSV a reconhece. Se ainda não constar, surge «À espera do primeiro registo de DSV» com «Provavelmente DSV» enquanto o Peek continua a perguntar; ao fim de uma semana, sugere verificar o número ou escolher outra transportadora.

O Peek não identifica a DSV por outros formatos. Cola-os na mesma: o Peek segue mais de 4500 transportadoras. Para escolher a DSV manualmente, prime «Alterar» e pesquisa DSV («Schenker» também a encontra: «Também conhecida como DB Schenker»). Numa encomenda já adicionada, usa «Alterar transportadora». Uma ligação mydsv.com (`?query=`) associa logo o envio à DSV, tal como uma ligação do seguimento Schenker em dsv.com com `refNumber=`.

O Peek consulta primeiro o myDSV. Um antigo número rodoviário ou código de barras DB Schenker desconhecido do myDSV passa ao seguimento Schenker, que o Peek lê apenas para envios rodoviários: para envios aéreos ou marítimos presentes só lá, usa «Track Schenker shipment» em dsv.com.

O Peek mostra o estado, cada etapa nas palavras da DSV com cidade e país, e o dia previsto de entrega. A DSV indica a hora local de cada registo sem fuso horário: o Peek determina o fuso pelo local (cidade, porto, código de aeroporto) ou pelos registos vizinhos, ou usa a hora de Copenhaga, o que por vezes pode falhar. O Peek omite o nome do serviço, a cidade do remetente, a tua morada, códigos postais e a barra de progresso da DSV, e do seguimento Schenker as partes, referências, bens e quem assinou.

Se a transportadora sueca Early Bird entregar a tua encomenda à DSV ou DB Schenker, o Peek indica a DSV como transportadora entregadora e passa a segui-la assim que apresentar registos mais recentes.

O Peek verifica até de 10 em 10 minutos, ou de 2 em 2 a partir das 6:00 hora local no dia em que a DSV regista o envio em distribuição, enquanto o seguimento próprio responder (via serviços de seguimento, de hora a hora, e até de 15 em 15 minutos no dia da entrega com a página aberta ou alertas ativos), podendo notificar-te para «Todos os registos», «Passos importantes» ou «Só a entrega». Após dois dias sem novidades (12 horas enquanto mostrar «Etiqueta criada» ou «Aceite pela transportadora»), verifica de hora a hora. Não pode contactar a DSV nem o remetente, agendar entregas nem abrir reclamações.

## Perguntas sobre o seguimento da DSV

### A DSV é a mesma empresa que a DB Schenker?

Sim. A DSV, fundada em 1976, concluiu a aquisição da Schenker à Deutsche Bahn em 30 de abril de 2025, e dbschenker.com foi integrado em dsv.com. Os envios Schenker mantêm por agora o seu próprio seguimento.

### Como acompanho agora um envio da DB Schenker?

Introduz o número em «Track Schenker shipment» na caixa «Track your shipment» em dsv.com ou cola-o acima; a menos que tenha 14 carateres começando por código de país, escolhe DSV manualmente. O Peek consulta o myDSV e depois o seguimento Schenker para envios rodoviários.

### O que significa «handover of cargo» no seguimento da DSV?

A DSV entregou a carga no fim do seu serviço e o envio fica concluído; o Peek mostra «Entregue». Se não recebeste nada, pergunta ao remetente onde a DSV entregou a carga e consulta [entregue mas não recebida](guide:delivered-not-received).

### A DSV tem número de telefone de apoio ao cliente?

As páginas de ajuda não indicam nenhum para seguimento. Usa o formulário de contacto ou encontra o escritório local em dsv.com. O número no aviso legal da DSV A/S (+45 43 20 30 40 na Dinamarca) não é uma linha de seguimento.

:::sources
- [DSV: Book and track shipments with myDSV](https://www.dsv.com/en/digital-solutions/book-and-track) – seguimento sem conta, dois campos, integração de dbschenker.com em dsv.com
- [DSV: myDSV](https://www.dsv.com/en/digital-solutions/mydsv) – vista pública e privada, registo de leituras, notificações, prova de entrega
- [DSV: Self-services](https://www.dsv.com/en/support/self-services) – seguimentos DSV e Schenker, eClaims, sistemas Schenker até à migração
- [DSV: Frequently asked questions](https://www.dsv.com/en/support/faq) – formulário de contacto, duas versões do myDSV na transição
- [DSV: Help and contact](https://www.dsv.com/en/support/help-and-contact) – mais de 90 países, localizador de escritórios
- [DSV: Find your local DSV office](https://www.dsv.com/en/support/find-dsv) – escritórios por país
- [DSV: Claims](https://www.dsv.com/en/support/self-services/claims) – o que faz o eClaims
- [DSV: Groupage transport](https://www.dsv.com/en/our-solutions/modes-of-transport/road-transport/groupage) – 36+ países, 400+ terminais, até 2.500 kg
- [DSV: E-commerce delivery solutions](https://www.dsv.com/en/our-solutions/contract-logistics/ecommerce-solutions/last-mile-delivery) – consolidação transfronteiriça, redes locais de última milha
- [DSV: Customs clearance](https://www.dsv.com/en/our-solutions/modes-of-transport/value-added-services/customs-clearance) – despachante licenciado, agentes, contactar a DSV no país
- [DSV: History](https://www.dsv.com/en/about-dsv/history) – fundada em 1976, estrada, ar e mar, compra da Schenker em 2025
- [DSV: DSV completes acquisition of Schenker](https://www.dsv.com/en/about-dsv/press/news/dsv-completes-acquisition-of-schenker-2628130) – concluída em 30 de abril de 2025, integração
- [DSV: Legal notice](https://www.dsv.com/en/about-dsv/legal-notice) – DSV A/S na Dinamarca, telefone, uso indevido do nome DSV
- [Comissão Europeia: reforma aduaneira da UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – direitos de 3 € por tipo de produto até 150 €, taxa de 2 €
- [Comissão Europeia: guia sobre os direitos de 3 € e a taxa de tratamento da União (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 € por tipo de produto qualquer que seja o valor
- [Universal Parcel Scraper: DSV](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/dsv) – formatos e ligações, dois rastreadores, textos e etapas DSV, horas locais, o que o Peek lê
:::
