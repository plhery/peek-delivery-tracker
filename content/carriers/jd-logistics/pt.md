---
title: Seguimento JD Logistics: códigos JD, estados e contactos
description: Segue uma encomenda da JD Logistics (京东物流): formatos JD e VG, significado dos estados em inglês e chinês, armazéns na Europa, alfândega e apoio ao cliente.
slug: seguimento-jd-logistics
published: 2026-11-18
updated: 2026-11-21
---

A JD Logistics (京东物流, Jingdong Logistics) é o ramo de logística do grupo JD.com, cotado na Bolsa de Hong Kong (`HKEX: 2618`). Fora da China opera sob as marcas JoyLogistics e JoyExpress: expede mercadorias guardadas por vendedores nos seus armazéns em **França**, na **Alemanha**, nos **Países Baixos**, na **Polónia**, no **Reino Unido**, nos **Estados Unidos** ou na **Austrália**, e transporta encomendas de comércio eletrónico a partir da **China**. Podes seguir o envio em inglês no [rastreador internacional da JD Logistics](https://www.jingdonglogistics.com/Tracking) ou colar o código na caixa acima. O código é fornecido pela loja que expediu a encomenda.

## Número de seguimento da JD Logistics: formatos e exemplos

Estes são os formatos que o Peek reconhece como JD Logistics, juntamente com um formato parecido que pertence a outra transportadora (com algarismos inventados):

| Número | O que é |
| --- | --- |
| `JD4829105736491` | `JD` e 13 algarismos: carta de porte da JD Logistics. |
| `JDA482910573649` | `JD`, uma letra e 12 algarismos: também JD Logistics. |
| `JDAP48291057364` | `JD`, duas letras e 11 algarismos: também JD Logistics. |
| `VG48291057364` | `VG` e 11 algarismos: usado pela JD Logistics, mas coincide com um formato da [La Poste](carrier:la-poste), pelo que o Peek não escolhe logo a JD (ver abaixo). |
| `JD482910573649182736` | `JD` e 18 algarismos: não é da JD Logistics. O Peek consulta a [DHL Paket](carrier:dhl), que usa este formato. |

Minúsculas, espaços e hífenes não importam no Peek. O rastreador internacional da JD aceita até 100 números numa só pesquisa, separados por espaços: prime Enter ou clica em «Track».

**Onde encontrá-lo.** A loja que contratou a JD envia-o no e-mail de expedição ou mostra-o na página da encomenda. Se não o encontrares, consulta [como encontrar o teu número de seguimento](guide:find-tracking-number).

**Encomenda dentro da China.** Um envio a circular dentro da **China** (por exemplo, a caminho do armazém de um transitário) segue-se no site chinês [jdl.com](https://www.jdl.com/orderSearch): introduz o número da carta de porte ou do volume em `运单查询` e prime `立即查询` (aceita vários códigos separados por vírgulas, espaços ou quebras de linha).

## Estados de seguimento da JD Logistics explicados

O seguimento internacional da JD apresenta cada passo em inglês com a cidade onde ocorreu. Estas são as mensagens originais da JD lidas pelo Peek, pela ordem habitual numa encomenda saída de um armazém externo da JD:

| O que diz a JD | Estado no Peek | O que significa para ti |
| --- | --- | --- |
| `Picking Completed.` | «Aceite pela transportadora» | A tua encomenda foi recolhida na prateleira de um armazém da JD onde o vendedor já tinha stock guardado. |
| `Weighed at Warehouse.` | «Aceite pela transportadora» | Pesada nesse mesmo armazém antes da saída. |
| `Departed from Warehouse.` | «Em trânsito» | Saiu do armazém da JD Logistics. |
| `Handed over to Carrier` | «Em trânsito» | A JD entregou o volume à transportadora responsável pela entrega final. |
| `Shipping Label Created` | sem recuo de estado | A etiqueta da transportadora local retransmitida pela JD; uma encomenda já em trânsito mantém o seu estado. |
| `Arrived at GOFO Regional Hub` | «Em trânsito» | Nos **Estados Unidos**: a [GOFO Express](carrier:gofo), que aparece no histórico da JD a entregar nesse país, leu a encomenda no seu centro regional. |

**Uma encomenda vinda da China** passa por outras etapas nesta sequência: primeiro `Shipment Picked up` (recolhida na loja), depois `Arrived at Warehouse`, `Weighed at Warehouse` e `Consolidation Completed` no armazém chinês da JD até partir — todas «Aceite pela transportadora» (`Consolidation Completed` significa agrupada com outras encomendas para o voo, não entregue). Seguem-se, todas como «Em trânsito»: `Departed from Warehouse.`, `Arrive at airport`, `Customs released from origin port` (desalfandegamento de exportação concluído), `Departure from origin port`, `Arrive at destination port`, `Cleanrance agency picked` (grafia da JD: o despachante aduaneiro levantou o volume desalfandegado, não o destinatário) e `Handover to local carrier`. Nos **Estados Unidos**, os passos da GOFO continuam sob os códigos da JD: `Arrived at GOFO Regional Hub`, `In Transit to Next Facility`, `Loaded on Vehicle` (carregada num centro da GOFO) e `Arrived at GOFO Delivery Station` («Em trânsito»), seguidos de `Out for Delivery` («Em distribuição»).

**O número da transportadora local.** Nas encomendas vindas da China, a linha `Departed from Warehouse.` no rastreador da JD termina por vezes com `Tracking no` e o código atribuído pela transportadora final (nos **Estados Unidos**, um código `GFUS` com 14 algarismos como `GFUS48291057364918` da GOFO Express). O Peek retira esse número do texto da linha, mas consulta diretamente essa transportadora quando o formato a identifica: assim que a transportadora local mostra uma leitura pelo menos tão recente como a última da JD, o Peek passa a acompanhar a entrega aí mantendo o histórico da JD e apresenta «Entrega: GOFO Express». Se o Peek não conseguir associar o código a uma transportadora, pesquisa-o no site do operador local.

O Peek interpreta também os restantes passos da JD pelo respetivo código interno: pedido criado («Etiqueta criada»); desalfandegamento iniciado («Na alfândega»); desalfandegamento concluído, descarga ou carga em centro de triagem, descarga no destino ou encaminhamento para centro externo («Em trânsito»); estafeta da JD a caminho («Em distribuição»); entrega assinada («Entregue»). Quando a linha de saída para distribuição da JD inclui o nome e o telefone do estafeta entre parênteses `【…】`, o Peek omite ambos.

**Linhas do rastreador chinês (`jdl.com`).** Para encomendas dentro da **China**, os registos surgem em chinês, entre os quais `京东快件已取件` (recolhida no remetente), `已到达【…】` (chegada ao centro de triagem ou estação de entrega indicada entre parênteses), `正在派送中` (em distribuição), `已与收件人约定新的派送时间` (novo horário de entrega combinado com o destinatário) e `已送达` (entregue).

## Entrega da JD Logistics na Europa e noutros mercados

**Encomendas vindas da China.** Na página «Cross-Border Parcels», a JD apresenta o seu serviço transfronteiriço desde a **China** para vendedores online rumo aos **Estados Unidos**, **Reino Unido**, **Austrália**, **Canadá**, **México**, **Rússia** e outros países em implementação, com opções standard, expresso ou económica. A JD assegura o transporte aéreo e entrega a última milha a parceiros locais com um prazo médio anunciado de 3 a 6 dias úteis nessa etapa final, sem publicar os nomes desses parceiros (nos **Estados Unidos**, o histórico da própria JD mostra a [GOFO Express](carrier:gofo)). Na **Europa**, a JD.com lançou também em abril de 2025 a sua marca própria de entregas expresso JoyExpress.

**Encomendas vindas de um armazém europeu ou internacional da JD.** A JD Logistics opera igualmente armazéns fora da China: em **França** perto de Paris (`FR-JD-PAR1` em Dugny, com distribuição para França e para toda a UE), na **Alemanha** perto de Frankfurt (`DE-JD-FRA1` em Buseck) e Dortmund (`DE-JD-DTM1` em Unna), nos **Países Baixos** em Venray (`NL-JD-VEN1`) e Tilburg (`NL-JD-TIL1`), na **Polónia** perto de Varsóvia (`PL-JD-WAW1` em Urzut e `PL-JD-WAW2` em Brwinów), no **Reino Unido** (`GB-JD-BHM1` em Lutterworth), nos **Estados Unidos** (Nova Jérsia, área de Los Angeles e Geórgia) e na **Austrália** (Sydney). Quando o vendedor envia a partir desse stock local, o histórico começa com `Picking Completed.` e `Weighed at Warehouse.` em vez de `Shipment Picked up`.

As páginas da JD não indicam quantas tentativas de entrega faz a transportadora local nem onde aguarda uma encomenda não recebida: aplicam-se as regras do operador do teu país. Vê [entrega falhada](guide:missed-delivery).

## Alfândega, taxas de importação e SMS fraudulentos

As regras do serviço expresso internacional da JD Logistics a partir da **China continental** ([jdl.com/serviceDetails](https://www.jdl.com/serviceDetails), separador `国际`) referem que durante o desalfandegamento transfronteiriço podem surgir direitos e impostos, suportados pelo remetente ou pelo destinatário e não incluídos no frete da JD. Confirma com a loja de onde saiu a encomenda e se o IVA e os direitos já estavam incluídos no preço pago. Sabe mais em [encomendas retidas na alfândega](guide:customs) e [identificar SMS falsos de entregas](guide:delivery-scam-texts).

- **União Europeia.** Desde 1 de julho de 2026, na **União Europeia**, os bens comprados à distância fora do território aduaneiro da UE em remessas com valor intrínseco até 150 € estão sujeitos a um direito aduaneiro de 3 € por categoria de artigos; desde 1 de novembro de 2026 acresce uma taxa de tramitação da União de 2 € por categoria de artigos, qualquer que seja o valor da remessa (uma categoria reúne as mercadorias com a mesma classificação pautal, descrição e, quando exigida, origem, independentemente do número de unidades).
- **Portugal.** Os Açores e a Madeira integram tanto o território aduaneiro como o território do IVA da UE. Em **Portugal**, segundo os [CTT](https://www.ctt.pt/particulares/receber/desalfandegar/fim-da-isencao-de-direitos-aduaneiros-em-compras-ate-150-euros-fora-da-uniao-europeia), o IVA, os direitos aduaneiros e a Taxa de Manuseamento Aduaneiro que não forem pagos no checkout são devidos à chegada da mercadoria; se os CTT te contactarem para concluir o desalfandegamento de um envio, pedem que disponibilizes a informação solicitada e consultes os encargos no portal de desalfandegamento dos CTT, e nos seus [alertas de phishing](https://www.ctt.pt/transversais/alertas-de-phishing) avisam contra mensagens fraudulentas por SMS ou e-mail que invocam taxas alfandegárias ou dados em falta com ligações falsas.

## Encomenda da JD Logistics sem atualização, atrasada ou devolvida

**Ainda sem resultados.** Se o rastreador da JD ainda não encontrar nada sob o número, confirma-o com o vendedor. O Peek mostra «À espera de JD Logistics» e continua a verificar; se ao fim de dois dias continuar desconhecido, surge «Ao fim de dois dias, a transportadora ainda não reconhece este número. Verifica-o, escolhe outra transportadora ou elimina a encomenda.».

**Sem atualizações durante vários dias.** Quando uma encomenda em viagem passa quatro dias sem novas leituras, o Peek assinala o dia da última leitura («Sem atualizações desde seg 12 out»), lembra que é habitual ficar sem novidades em voos longos, na alfândega ou ao fim de semana (ao fim de três semanas sem leituras, aconselha antes a falar com o vendedor) e mostra uma ligação «Pede ajuda a JD Logistics» para a página de contactos da JD (ou para a transportadora local quando uma parceira como a GOFO já assumiu a entrega). Mais informações em [porque é que o seguimento não atualiza](guide:tracking-not-updating) e [seguir encomendas vindas da China](guide:tracking-from-china).

**Atrasada ou perdida.** Quem contratou a JD foi o vendedor, pelo que deves contactar primeiro a loja. Para o seu expresso internacional a partir da **China continental**, o regulamento da JD indica que o prazo estimado de entrega constante na carta de porte serve apenas de referência: a JD não reembolsa portes nem paga indemnização por atraso. Consulta [encomendas perdidas](guide:lost-parcel).

**Devolvida.** As regras do expresso internacional da JD a partir da **China continental** não preveem serviço de logística inversa depois de a mercadoria sair da China continental. Para devolver uma compra, pergunta à loja como proceder. Consulta [devolvida ao remetente](guide:returned-to-sender).

**Dada como entregue, mas não chegou.** Contacta a loja, verifica com vizinhos ou portaria e consulta [entregue mas não recebida](guide:delivered-not-received).

## Apoio ao cliente e contactos da JD Logistics

A [página internacional Contact Us da JD Logistics](https://www.jingdonglogistics.com/contact) divulga os contactos de apoio ao cliente por país (encerrado aos fins de semana e feriados salvo indicação em contrário, sem fuso horário indicado):

- **Alemanha, Países Baixos e Reino Unido:** `org.cs.eu1@jd.com`, de segunda a sexta-feira das 09:00 às 18:00.
- **França:** `org.cs.eu1@jd.com`, de segunda a sexta-feira das 09:00 às 17:00.
- **Polónia:** `org.cs.intl1@jd.com`, de segunda a sexta-feira das 09:00 às 17:00.
- **Estados Unidos:** `org.cs.us1@jd.com`, de segunda a sexta-feira das 08:00 às 17:00.
- **Austrália:** `+61 (02)87239755` e `org.cs.intl1@jd.com`, de segunda a sexta-feira das 09:00 às 18:00 (encerrado ao domingo e feriados).

A JD não lista contacto próprio para Portugal, Brasil ou Canadá. Na **China**, a linha central da JD Logistics é `950616` (`0086 950616` a partir do estrangeiro na página International Express), e a [página Contact Us em jdl.com](https://www.jdl.com/en/contactUs) dispõe também do botão «Online Consultation». Sobre a encomenda ou reembolsos, fala sempre primeiro com a loja.

## Seguir uma encomenda da JD Logistics com o Peek

O [Peek](/) identifica a JD Logistics de imediato a partir dos formatos `JD…` da tabela acima («Transportadora detetada»). Como um código `VG` de 11 algarismos também encaixa no formato da La Poste, o Peek verifica-o primeiro junto da La Poste e de outros serviços de seguimento («A verificar serviços de seguimento…»); se não o encontrarem, toca em «Alterar» e escolhe JD Logistics. Uma ligação de `jingdonglogistics.com` com `waybillCodes=` regista a encomenda na JD Logistics qualquer que seja o número; a partir de uma ligação de `jdl.com`, o Peek extrai o código quando segue um dos formatos `JD…`. Se o teu número da JD tiver outro aspeto, cola-o na mesma: o Peek acompanha mais de 3500 transportadoras, e podes tocar em «Alterar» e escolher JD Logistics em «Todas as transportadoras» (pesquisando também `Jingdong` ou `京东物流`), ou usar «Alterar transportadora» numa encomenda já adicionada.

O Peek lê o seguimento internacional da JD: o estado, cada etapa nas palavras da JD com cidade e país em inglês (mesmo quando a JD escreve o país em chinês) e a hora de entrega. As horas são lidas no relógio do local da leitura e mostradas no fuso horário do teu dispositivo («Horas no teu fuso horário: …» em peektracker.com e nas apps para iPhone e Android). O Peek não mostra data prevista de entrega para encomendas da JD e deixa de fora os dados do destinatário, eventuais fotografias de entrega guardadas pela JD, o nome e o telefone do motorista e o número do parceiro local colado no fim da linha de saída.

Nas séries de números próprias do **tráfego interno chinês** (`JD` e 13 algarismos começados por `0`; `JDJ`, `JDK`, `JDV` ou `JDX` e 12 algarismos; `JDVA`, `JDVB`, `JDVC` ou `JDAZ` e 11 algarismos), o Peek abre diretamente o rastreador chinês em `jdl.com`. Para `JDW` com 12 algarismos ou `JDAP` com 11 consulta apenas o internacional; nos restantes códigos `JD`, tenta primeiro o internacional e passa ao chinês se o primeiro vier vazio. As linhas chinesas aparecem em chinês sem nome nem telefone do estafeta nem nome de quem assinou. Como `jdl.com` pede frequentemente uma verificação que o Peek não consegue concluir, após uma recusa o Peek suspende a abertura de `jdl.com` para esse código durante 6 horas ou mais, até um dia: se uma encomenda dentro da China não mostrar registos no Peek, consulta-a diretamente em `jdl.com`.

Enquanto tens a encomenda aberta, ou no dia da entrega com as notificações ativas, o Peek verifica até de 10 em 10 minutos — e a partir das 6:00 locais no dia em que a JD regista a encomenda em distribuição, até de 2 em 2 minutos enquanto responder o rastreador internacional da JD (de 5 em 5 minutos quando abre o rastreador chinês em `jdl.com`); apenas através de serviços de seguimento, verifica de hora a hora, de 3 em 3 horas após um dia sem novidades e até de 15 em 15 minutos no dia da entrega enquanto a encomenda estiver aberta ou com alertas ativos. No resto do tempo, bem como após dois dias sem atualizações (12 horas em «Etiqueta criada» ou «Aceite pela transportadora»), verifica de hora a hora. Quando uma transportadora local assume a entrega, o Peek adota o ritmo dessa transportadora (nos **Estados Unidos**, GOFO Express: até de 30 em 30 minutos, ou de hora a hora após falha, também no dia da entrega). Podes ativar alertas de «Todas as leituras», «Passos importantes» ou «Só a entrega». O Peek não pode contactar a JD Logistics nem a loja por ti.

## Perguntas frequentes sobre o seguimento da JD Logistics

### A JD Logistics é fidedigna?

Sim. A JD.com começou a construir a sua rede logística própria em 2007, autonomizou o grupo JD Logistics em abril de 2017 e cotou-o na Bolsa de Hong Kong em maio de 2021 (código `2618`). Segundo a sua página institucional, opera mais de 1600 armazéns próprios e 2000 armazéns na nuvem operados por terceiros em 26 mercados. A nível global, a JD Logistics opera mais de 200 armazéns alfandegados, de distribuição direta internacional e no estrangeiro.

### Como seguir uma encomenda da JD Logistics?

Em `jingdonglogistics.com/Tracking` (até 100 códigos separados por espaços), em `jdl.com/orderSearch` para encomendas dentro da China ou colando o número no topo desta página.

### Quem entrega as encomendas da JD Logistics em Portugal e na Europa?

A JD não publica a lista de parceiros de última milha para envios vindos da China, mas opera na Europa desde abril de 2025 com a marca expresso JoyExpress e mantém armazéns perto de Paris (Dugny, com distribuição para toda a UE), Frankfurt, Dortmund, Venray, Tilburg, Varsóvia e em Inglaterra. Se a linha `Departed from Warehouse.` indicar após `Tracking no` o código de uma transportadora local reconhecida pelo Peek, o Peek acompanha automaticamente esse número assim que tiver leituras.

### Porque é que o seguimento da JD Logistics não atualiza?

O vendedor pode ainda não ter entregue o pacote à JD, a encomenda pode estar num voo ou na alfândega, ou a transportadora local ainda não comunicou leituras. Ao fim de quatro dias sem novidades, fala com o vendedor ou escreve para o apoio europeu da JD (`org.cs.eu1@jd.com`). Para envios dentro da China, verifica `jdl.com`.

### Como contactar a JD Logistics?

Através dos e-mails publicados em `jingdonglogistics.com/contact` (`org.cs.eu1@jd.com` para a Europa Ocidental, sem caixa própria listada para Portugal ou Brasil). Na China, a linha de apoio é `950616` (`0086 950616` a partir do estrangeiro). Sobre a compra ou reembolsos, contacta primeiro a loja.

:::sources
- [JD Logistics: Track your shipment (em inglês)](https://www.jingdonglogistics.com/Tracking) – rastreador internacional em inglês, até 100 números separados por espaços, Enter ou botão Track
- [JD Logistics: About JINGDONG Logistics (em inglês)](https://www.jingdonglogistics.com/about) – divisão logística da JD.com (HKEX: 2618), marcas JoyLogistics e JoyExpress, mais de 1600 armazéns próprios, 2000 na nuvem e mais de 200 internacionais em 26 mercados
- [JD Logistics: Contact Us (em inglês)](https://www.jingdonglogistics.com/contact) – caixas de e-mail de apoio ao cliente, número australiano, horários e encerramentos por país e linha 950616
- [JD Logistics: Cross-Border Parcels (em inglês)](https://www.jingdonglogistics.com/ser/CrossExport) – países servidos, opções standard/expresso/económica, transporte aéreo e última milha em 3 a 6 dias úteis em média
- [JD Logistics: JINGDONG Logistics (France) (em inglês)](https://www.jingdonglogistics.com/ser/GlobalWarehouseFR) – armazém FR-JD-PAR1 em Dugny e distribuição em França e na UE
- [JD Logistics: JINGDONG Logistics (Germany) (em inglês)](https://www.jingdonglogistics.com/ser/GlobalWarehouseDE) – armazéns DE-JD-FRA1 em Buseck e DE-JD-DTM1 em Unna
- [JD Logistics: JINGDONG Logistics (The Netherlands) (em inglês)](https://www.jingdonglogistics.com/ser/GlobalWarehouseNL) – armazéns NL-JD-VEN1 em Venray e NL-JD-TIL1 em Tilburg
- [JD Logistics: JINGDONG Logistics (Poland) (em inglês)](https://www.jingdonglogistics.com/ser/GlobalWarehousePL) – armazéns PL-JD-WAW1 em Urzut e PL-JD-WAW2 em Brwinów perto de Varsóvia
- [JD Logistics: JINGDONG Logistics (United Kingdom) (em inglês)](https://www.jingdonglogistics.com/ser/GlobalWarehouseUK) – armazém GB-JD-BHM1 em Lutterworth (Inglaterra)
- [JD Logistics: JINGDONG Logistics (USA) (em inglês)](https://www.jingdonglogistics.com/ser/GlobalWarehouseUS) – armazéns nos EUA (Nova Jérsia, área de Los Angeles e Geórgia)
- [JD Logistics: JINGDONG Logistics (Australia) (em inglês)](https://www.jingdonglogistics.com/ser/GlobalWarehouseAS) – armazéns em Sydney
- [JDL: 运单查询 (pesquisa de envios, em chinês)](https://www.jdl.com/orderSearch) – rastreador chinês por carta de porte ou volume, pesquisa múltipla
- [JDL: 服务细则 (regras de serviço, em chinês)](https://www.jdl.com/serviceDetails) – separador internacional: impostos e direitos fora do frete, prazo estimado apenas indicativo sem reembolso nem indemnização e ausência de devolução fora da China continental
- [JDL: International Express (em inglês)](https://www.jdl.com/en/crossBorder/internationalC2C) – expresso da China continental para o estrangeiro e telefone 0086 950616
- [JDL: Contact Us (em inglês)](https://www.jdl.com/en/contactUs) – linha 950616 e botão Online Consultation
- [JDL: Company Profile (em inglês)](https://www.jdl.com/en/profile) – criação em abril de 2017, cotação em Hong Kong em maio de 2021 e lançamento da JoyExpress na Europa em abril de 2025
- [Comissão Europeia: EU Customs Reform (em inglês)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – direito aduaneiro de 3 € por categoria de artigos até 150 € desde 1 de julho de 2026 e taxa de tramitação da União de 2 € desde 1 de novembro de 2026
- [Comissão Europeia: Guidance on the EUR 3 customs duty and the Union handling fee (PDF, em inglês)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – taxa de 2 € independentemente do valor e definição de categoria de artigos
- [Comissão Europeia: âmbito territorial aduaneiro e do IVA da UE](https://taxation-customs.ec.europa.eu/taxation/vat/vat-directive/how-does-vat-work/territorial-scope_en) – enquadramento dos Açores e da Madeira
- [CTT: fim da isenção de direitos aduaneiros até 150 €](https://www.ctt.pt/particulares/receber/desalfandegar/fim-da-isencao-de-direitos-aduaneiros-em-compras-ate-150-euros-fora-da-uniao-europeia) – encargos na chegada e portal de desalfandegamento dos CTT em Portugal
- [CTT: alertas de phishing](https://www.ctt.pt/transversais/alertas-de-phishing) – avisos sobre mensagens fraudulentas em Portugal
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formatos `JD` e `VG`, textos e códigos da JD, transição para a GOFO Express, consulta em `jdl.com` e apresentação no Peek
:::
