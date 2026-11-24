---
title: Rastreamento JD Logistics: códigos JD, status e contato
description: Rastreie um pacote da JD Logistics (京东物流): formatos JD e VG, significado dos status, quem entrega nos EUA e canais de contato.
slug: rastreamento-jd-logistics
published: 2026-11-24
updated: 2026-11-24
---

A JD Logistics (京东物流, Jingdong Logistics) é a operação logística da JD.com, com ações listadas em Hong Kong. No exterior atua sob as marcas JoyLogistics e JoyExpress: despacha estoques de vendedores a partir de armazéns nos EUA, Reino Unido, Austrália e outros países, além de enviar encomendas da China para lojas online. Rastreie no [rastreador internacional da JD](https://www.jingdonglogistics.com/Tracking), em inglês, ou cole o código na caixa acima. O número vem da loja que enviou seu pedido.

## Código de rastreamento JD Logistics: como reconhecer

Estes são os formatos que o Peek reconhece como JD Logistics — e um que não pertence a ela —, com dígitos inventados:

| Código | O que é |
| --- | --- |
| `JD4829105736491` | `JD` e 13 dígitos: um código da JD Logistics. |
| `JDA482910573649` | `JD`, uma letra e 12 dígitos: também JD Logistics. |
| `JDAP48291057364` | `JD`, duas letras e 11 dígitos: também JD Logistics. |
| `VG48291057364` | `VG` e 11 dígitos: também JD Logistics, mas códigos da La Poste podem ter o mesmo formato, então o Peek não crava a JD de imediato (veja abaixo). |
| `JD482910573649182736` | `JD` e 18 dígitos: não é JD Logistics — a [DHL Paket](carrier:dhl) usa esse formato. |

Minúsculas, espaços e traços não importam no Peek. O rastreador internacional da JD aceita até 100 códigos por vez, separados por espaços: aperte Enter ou "Track".

**Onde encontrar.** A loja que contratou a JD informa o número no e-mail de envio ou na página do pedido. Se não encontrar, veja [como encontrar o código de rastreamento](guide:find-tracking-number).

**Pacote dentro da China.** Envios em circulação na China (por exemplo, até o armazém de uma operadora) são rastreados no site chinês da JD, [jdl.com](https://www.jdl.com/orderSearch). Digite o número em `运单查询` (busca de conhecimento) e clique em `立即查询` (buscar agora); aceita vários códigos separados por vírgula, espaço ou quebra de linha.

## Status do rastreamento JD Logistics explicados

O rastreamento internacional da JD mostra cada etapa em inglês junto com o local. Estas são as etapas vistas pelo Peek nos textos da JD, na ordem de um pacote que sai de um armazém da JD no exterior:

| A JD mostra | Status no Peek | O que significa para você |
| --- | --- | --- |
| `Picking Completed.` | "Recebida pela transportadora" | Seu pedido foi separado em um armazém da JD que já guardava o estoque do vendedor. |
| `Weighed at Warehouse.` | "Recebida pela transportadora" | Pesado no mesmo armazém antes da saída. |
| `Departed from Warehouse.` | "Em trânsito" | Saiu do armazém da JD. |
| `Handed over to Carrier` | "Em trânsito" | A JD repassou o pacote à transportadora que entrega na sua região. |
| `Shipping Label Created` | sem mudança | Etiqueta dessa transportadora, repassada pela JD. O status do pacote não muda. |
| `Arrived at GOFO Regional Hub` | "Em trânsito" | Nos EUA: a GOFO Express, que aparece entregando no histórico da JD lá, bipou o pacote em seu hub. |

**Pacote vindo da China** passa por outras etapas, nesta ordem. Primeiro `Shipment Picked up` (coletado na loja), depois `Arrived at Warehouse`, `Weighed at Warehouse` e `Consolidation Completed` no armazém da JD na China — tudo como "Recebida pela transportadora". Consolidado significa agrupado com outros pacotes para o voo, não entregue. Em seguida, tudo como "Em trânsito": `Departed from Warehouse.`, `Arrive at airport`, `Customs released from origin port` (exportação liberada), `Departure from origin port`, `Arrive at destination port`, `Cleanrance agency picked` (grafia da própria JD: o despachante recolheu o pacote liberado, não você) e `Handover to local carrier`. Nos EUA entram então os eventos da GOFO sob códigos da JD: `Arrived at GOFO Regional Hub`, `In Transit to Next Facility`, `Loaded on Vehicle` e `Arrived at GOFO Delivery Station` ("Em trânsito"), seguidos de `Out for Delivery` ("Saiu para entrega").

**O número da transportadora local.** No rastreador da JD, `Departed from Warehouse.` em um pacote da China termina com "Tracking no" e o código dado pela transportadora que entrega perto de você (nos EUA, um número GOFO iniciado em `GFUS`). O Peek remove esse número do texto da linha, mas consulta o rastreador dela quando o formato identifica a empresa — como `GFUS` com 14 dígitos identifica a GOFO. Assim que a GOFO mostra uma leitura pelo menos tão recente quanto a última da JD, o Peek acompanha por lá, mantém as etapas da JD no histórico e exibe "Entrega por GOFO Express". Quando o formato não permite associar o número a uma transportadora, consulte no site da entregadora local.

O Peek interpreta outras etapas da JD pelo código interno dela: pedido criado ("Etiqueta criada"), início de desembaraço ("Na alfândega"), liberação, descarga ou carga em centro de triagem e movimentações entre centros ("Em trânsito"), entregador a caminho ("Saiu para entrega") e assinado ("Entregue"). Cada linha preserva o texto da JD, removendo nome e telefone do entregador na linha de saída para entrega.

**Textos do rastreador chinês da JD.** Dentro da China, as linhas vêm em chinês, como `京东快件已取件` (coletado com o remetente), `已到达【…】` (chegou à unidade entre colchetes: centro de triagem ou estação de entrega), `正在派送中` (saiu para entrega) e `已送达` (entregue).

## Entregas da JD Logistics nos EUA, Reino Unido, Canadá e Austrália

**Pacotes da China.** O serviço Cross-Border Parcels da JD, para vendedores online chineses, lista EUA, Reino Unido, Austrália, Canadá, México e Rússia, nas modalidades standard, express ou economic. A JD despacha de avião e entrega a parceiras locais na última milha — processo que leva em média de 3 a 6 dias úteis, segundo a JD, sem nomear essas parceiras. Nos EUA, o histórico mostra a [GOFO Express](carrier:gofo) entregando, com os eventos da GOFO aparecendo no próprio rastreamento da JD.

**Pacotes de armazéns locais da JD.** A JD também opera armazéns no exterior: nos EUA (Nova Jersey, Los Angeles e Atlanta), na Inglaterra e em Sydney, entre outros. Quando o vendedor mantém estoque ali, o envio sai já de dentro do seu país, começando por `Picking Completed.` e `Weighed at Warehouse.` (em vez de `Shipment Picked up`, típico da China).

As páginas da JD não informam quantas tentativas a transportadora local faz nem onde o pacote fica guardado: isso depende da entregadora do seu país. Veja [entrega perdida](guide:missed-delivery).

**Alfândega.** Envios da China passam pela alfândega do seu país. As regras do expresso internacional da JD dizem que impostos e tarifas podem incidir no desembaraço, pagos pelo remetente ou destinatário, e não estão incluídos no frete. Pergunte à loja se o valor pago já cobriu esses tributos. Mais em [encomendas paradas na alfândega](guide:customs).

## Pacote JD Logistics sem atualizar, perdido ou devolvido

**Nada ainda.** Se o rastreador da JD não retornar dados, confira o número com o vendedor. O Peek mostra "Aguardando JD Logistics" e continua verificando.

**Sem atualização por dias.** Quando um pacote ainda em viagem completa 4 dias sem nova leitura, o Peek explica que encomendas costumam ficar quietas em voos longos, na alfândega ou no fim de semana (após três semanas sem leitura, orienta falar com o vendedor) e exibe o link "Pedir ajuda a JD Logistics" para a página de contato (depois que a GOFO assume, muda para "Pedir ajuda a GOFO Express" e o suporte da GOFO). Mais em [por que o rastreamento para de atualizar](guide:tracking-not-updating) e [como rastrear encomendas da China](guide:tracking-from-china).

**Atrasado ou extraviado.** Quem contratou a JD foi o vendedor, então fale com ele primeiro. Para seu expresso internacional da China, as regras da JD classificam o prazo no conhecimento como mera referência, sem reembolso de frete nem indenização por atraso. Veja [encomendas extraviadas](guide:lost-parcel).

**Devolvido.** As regras do expresso internacional da JD não oferecem serviço de devolução depois que o pacote deixa a China continental. Para devolver um pedido, combine com a loja. Veja [devolvida ao remetente](guide:returned-to-sender).

**Entregue, mas não chegou.** Fale com a loja, pergunte a vizinhos e à portaria do prédio e consulte [entregue, mas não recebido](guide:delivered-not-received).

## Atendimento e contato da JD Logistics

A [página Contact Us internacional da JD](https://www.jingdonglogistics.com/contact) traz o atendimento por país e horários:

- **Estados Unidos:** org.cs.us1@jd.com, de segunda a sexta-feira, das 8h às 17h.
- **Reino Unido:** org.cs.eu1@jd.com, de segunda a sexta-feira, das 9h às 18h — mesmo canal usado para Alemanha, Holanda e França.
- **Austrália:** +61 (02) 87239755 e org.cs.intl1@jd.com, de segunda a sexta-feira, das 9h às 18h.

A JD não especifica o fuso horário desses atendimentos, fecha em feriados públicos e não lista contato para Canadá ou Irlanda. Na China, o telefone é 950616 (grafado 0086 950616 na página International Express em inglês); a [página Contact Us em jdl.com](https://www.jdl.com/en/contactUs) também oferece o botão "Online Consultation". Sobre seu pedido, fale com a loja.

## Como rastrear um pacote da JD Logistics no Peek

O [Peek](/) identifica a JD Logistics de imediato nos formatos `JD…` da tabela acima. Códigos `VG` também coincidem com o padrão da La Poste: o Peek consulta a La Poste e serviços de rastreamento e, se não encontrarem, toque em "Trocar" e escolha JD Logistics. Um link de jingdonglogistics.com com `waybillCodes=` seleciona a JD Logistics qualquer que seja o formato; em links de jdl.com, o Peek só captura o código quando ele segue um dos formatos `JD…`. Se seu código JD tiver outra forma, cole mesmo assim (o Peek acompanha mais de 4.500 transportadoras), toque em "Trocar" e escolha JD Logistics em "Todas as transportadoras" (buscar por Jingdong ou 京东物流 também encontra), ou use "Trocar transportadora" em um pacote já adicionado.

O Peek lê o rastreamento internacional da JD: status, cada etapa nas palavras da JD com cidade e país (em inglês, mesmo quando a JD escreve o país em chinês) e horário da entrega. Os horários são lidos no fuso local de cada leitura e exibidos no seu fuso; em peektracker.com e nos aplicativos para iPhone e Android, a linha final do histórico indica esse fuso. O Peek não exibe data prevista de entrega para a JD e omite dados do destinatário, fotos da entrega, nome e telefone do entregador e o número da transportadora local no fim da linha de saída.

Para códigos domésticos chineses (`JD` + 13 dígitos começando em `0`; `JDJ`, `JDK`, `JDV` ou `JDX` + 12 dígitos; `JDVA`, `JDVB`, `JDVC` ou `JDAZ` + 11 dígitos), o Peek abre o rastreador chinês em jdl.com em vez do internacional. Para `JDW` + 12 dígitos ou `JDAP` + 11, consulta só o internacional; nos demais códigos JD, tenta o internacional primeiro e recorre ao chinês se vier vazio. As linhas chinesas aparecem em chinês, omitindo entregador, telefone e quem assinou. Como o site chinês frequentemente exige verificação que o Peek não contorna, ele pausa novas tentativas ali por 6 horas ou mais (até um dia): para pacotes dentro da China sem dados no Peek, consulte em jdl.com. Resposta vazia da JD não significa código inválido; o Peek continua verificando.

Enquanto o pacote estiver aberto, ou no dia da entrega com alertas ligados, o Peek verifica até a cada 10 minutos, e até a cada 2 minutos (a cada 5 quando precisa abrir o rastreador chinês) a partir das 6h no horário local no dia da saída para entrega, enquanto o rastreador da JD responder; fora disso, de hora em hora. Apenas por serviços de rastreamento, verifica de hora em hora, a cada 3 horas após um dia sem movimento e até a cada 15 minutos no dia em que sai para entrega enquanto o pacote estiver aberto ou com alertas ligados. Quando outra transportadora assume a entrega, o Peek segue o ritmo dela. Após dois dias sem novidade (12 horas enquanto ainda mostra "Etiqueta criada" ou "Recebida pela transportadora"), verifica de hora em hora. Alertas disponíveis: "Cada movimentação", "Etapas importantes" ou "Só a entrega". O Peek não pode falar com a JD nem com a loja por você.

## Perguntas sobre o rastreamento JD Logistics

### A JD Logistics é confiável?

Sim. A JD.com começou a montar sua rede logística nacional em 2007, criou o grupo JD Logistics em 2017 e abriu capital na Bolsa de Hong Kong em maio de 2021 (código 2618). Em 30 de junho de 2026, informava operar mais de 1.600 armazéns próprios e 2.000 terceirizados em 26 mercados.

### Como rastrear um pacote da JD Logistics?

Em jingdonglogistics.com/Tracking, ou em jdl.com para pacotes dentro da China — ou colando o código na caixa acima.

### Quem entrega pacotes da JD Logistics nos EUA?

A JD não nomeia parceiras locais. Nos EUA, o histórico da JD mostra a GOFO Express entregando: depois de `Handed over to Carrier` ou `Handover to local carrier` vêm etapas como `Arrived at GOFO Regional Hub` e `Arrived at GOFO Delivery Station`. Quando a linha de saída da JD informa o código `GFUS` da GOFO, o Peek também acompanha na GOFO assim que ela registra uma leitura.

### Por que meu rastreamento da JD Logistics não atualiza?

A JD ainda não tem registros para o código, o pacote está em voo ou na alfândega, ou a transportadora local ainda não reportou. Após 4 dias sem atualização, fale com o vendedor ou com o atendimento da JD do seu país. Para envios dentro da China, consulte em jdl.com.

### Como falar com a JD Logistics?

Pela página Contact Us em jingdonglogistics.com, que traz contatos e horários para EUA, Reino Unido, Austrália e outros países (mas não Canadá nem Irlanda). Na China, pela página Contact Us de jdl.com. Sobre o pedido, fale primeiro com a loja.

:::sources
- [JD Logistics: Track your shipment](https://www.jingdonglogistics.com/Tracking) – rastreador em inglês, até 100 códigos separados por espaços, Enter ou "Track"
- [JD Logistics: About JINGDONG Logistics](https://www.jingdonglogistics.com/about) – divisão logística da JD.com, HKEX 2618, JoyLogistics e JoyExpress, armazéns e mercados em 30 de junho de 2026
- [JD Logistics: Contact Us](https://www.jingdonglogistics.com/contact) – e-mails de suporte, telefone na Austrália, horários e feriados por país e 950616
- [JD Logistics: Cross-Border Parcels](https://www.jingdonglogistics.com/ser/CrossExport) – países atendidos, modalidades, voos, parceiras locais e 3 a 6 dias úteis
- [JD Logistics: JINGDONG Logistics (USA)](https://www.jingdonglogistics.com/ser/GlobalWarehouseUS) – armazéns nos EUA
- [JD Logistics: JINGDONG Logistics (United Kingdom)](https://www.jingdonglogistics.com/ser/GlobalWarehouseUK) – armazém na Inglaterra
- [JD Logistics: JINGDONG Logistics (Australia)](https://www.jingdonglogistics.com/ser/GlobalWarehouseAS) – armazéns em Sydney
- [JDL: 运单查询 (busca de conhecimento)](https://www.jdl.com/orderSearch) – rastreador chinês e múltiplos códigos
- [JDL: 服务细则 (regras de serviço)](https://www.jdl.com/serviceDetails) – expresso internacional: impostos e taxas, estimativa apenas como referência e sem devolução após sair da China
- [JDL: International Express](https://www.jdl.com/en/crossBorder/internationalC2C) – expresso da China para destinatários no exterior e 0086 950616
- [JDL: Contact Us](https://www.jdl.com/en/contactUs) – telefone 950616 e Online Consultation
- [JDL: Company Profile](https://www.jdl.com/en/profile) – 2007, 2017 e listagem em Hong Kong em maio de 2021
:::
