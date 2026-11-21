---
title: Seguimento India Post e Speed Post: números e estados
description: Segue um envio da India Post ou Speed Post: formato do código terminado em IN, significado dos estados, entrega pelos CTT ou Correios, alfândega e contactos.
slug: seguimento-india-post
published: 2026-11-18
updated: 2026-11-20
---

A India Post é o Department of Posts, o serviço postal público da **Índia**, com 164 999 estações de correio — a maior rede postal do mundo, segundo a própria página oficial. Dentro da Índia opera o Speed Post, o correio registado e o India Post Parcel; para o estrangeiro envia EMS (International Speed Post), pequenos pacotes com seguimento (International Tracked Packet) e encomendas aéreas. Podes seguir um objeto em [indiapost.gov.in](https://www.indiapost.gov.in/), que pede um CAPTCHA, ou colar o número na caixa acima. A India Post chama-lhe *article barcode number* (número de código de barras do objeto); numa compra online, pede-o ao vendedor.

## Número de seguimento da India Post: como é

Um número da India Post tem 13 caracteres no [formato postal internacional](guide:tracking-number-formats): duas letras, nove algarismos e `IN` no fim. O último dos nove algarismos é um dígito de controlo. Exemplos com algarismos inventados:

| Exemplo inventado | O que identifica |
| --- | --- |
| `EE482910576IN` | Um objeto numerado pela India Post, quer circule na Índia quer siga para o estrangeiro. As duas letras iniciais variam consoante o serviço; o `IN` final identifica a Índia. |
| `EE482910576PT` | Termina noutras letras, aqui `PT`: foi emitido pelos correios de outro país ([CTT](carrier:ctt)). Uma encomenda enviada do estrangeiro para a Índia mantém o número do operador postal de origem. |

O seguimento da India Post abrange Speed Post, envios registados e com valor declarado, Business Parcel e Express Parcel, vales postais eletrónicos (*electronic money orders*) e International EMS. Não o encontras? Vê [como encontrar o número de seguimento](guide:find-tracking-number).

## Estados de seguimento da India Post explicados

As perguntas frequentes da India Post resumem o percurso nas etapas *booked*, *received*, *bagged*, *dispatched*, *out for delivery* e *delivered*. Estas são as linhas que o Peek conhece, pela ordem habitual:

| A India Post mostra | O que significa para o teu envio |
| --- | --- |
| `Item Booked` | O remetente entregou o objeto num balcão ou registou-o online e a India Post aceitou-o. |
| `Bag Received for Forwarding` | A mala postal com o objeto chegou a uma estação para reexpedição. |
| `Assigned to load plan` | Está atribuído ao plano de carga do transporte seguinte. |
| `Transport Leg Completed` | Uma etapa do transporte ficou concluída. |
| `Transfer to OOE (Otb)` | A caminho da estação de permuta internacional na Índia (*Office of Exchange*) que expede correio para o exterior. |
| `Aircraft Departure` | O voo que o transporta já partiu. Por vezes a India Post escreve `UPLIFT`. |
| `Bag unloaded at destination Airport` | A mala postal foi descarregada no aeroporto do país de destino. |
| `Send item to domestic location (Inb)` | A estação de permuta do país de destino encaminhou o objeto para a rede postal interna. |
| `Item Delivered` | Entregue. |

A indicação `(Otb)` assinala uma leitura do correio expedidor na Índia e `(Inb)` uma leitura comunicada pelo operador postal do país de destino e retransmitida pela India Post. Nos dados lidos pelo Peek, a India Post envia hoje muitas vezes códigos curtos como `BAG_DISPATCH`; o Peek desdobra os que conhece — por exemplo `Bag Dispatched` ou `Item Presented To Customs` — e converte os restantes códigos em palavras legíveis. Mais detalhes em [o que significam os estados de seguimento](guide:tracking-statuses).

## Receber um envio da India Post na Índia

As regras abaixo aplicam-se a entregas dentro da **Índia**:

**Prazos de entrega.** Nos padrões publicados pela India Post para o Speed Post na Índia, da aceitação até à entrega, os prazos são de 1 a 2 dias em envios locais, 1 a 3 dias entre áreas metropolitanas, 1 a 4 dias entre capitais estaduais ou dentro do mesmo estado e 4 a 5 dias para o resto do país, mais um dia quando a entrega cabe a um posto rural ou sucursal (*branch office*). O 24 Speed Post entrega no dia seguinte em seis metrópoles indianas (Nova Deli, Bombaim, Bangalore, Chennai, Calcutá e Hyderabad), sete dias por semana; em caso de atraso, o remetente pode pedir online o reembolso do porte no prazo de 7 dias.

**SMS e código OTP.** Numa encomenda Speed Post na Índia, o telemóvel registado recebe um SMS quando o envio chega à estação de distribuição e outro na entrega. Alguns objetos Speed Post só são entregues mediante um código único (OTP): faz parte do 24 Speed Post e é um extra pago nos restantes envios Speed Post.

**Tentativa de entrega falhada.** O seguimento da India Post indica quando foi emitido um aviso (*Delivery Intimation Notice*) a informar que o envio está disponível para entrega. Consulta também [falhaste a entrega](guide:missed-delivery) e [encomendas não levantadas](guide:uncollected-parcel).

**Alteração de morada.** Na Índia, o remetente que tenha registado o envio online através de uma conta da India Post pode pedir a devolução ou o reencaminhamento enquanto o objeto não tiver sido entregue nem atribuído a um carteiro para distribuição.

## Encomendas da India Post para o estrangeiro: entrega e alfândega

Para o exterior, a India Post expede EMS (International Speed Post, até 35 kg), International Tracked Packet pensado para vendas online (até 5 kg) e International Air Parcel (até 20 kg), todos com seguimento. Depois da aterragem, as leituras marcadas `(Inb)` vêm do correio do teu país: podes acompanhá-las aí com o mesmo número terminado em `IN`, nos [CTT](carrier:ctt) em **Portugal**, nos Correios no **Brasil**, na [USPS](carrier:usps) nos **Estados Unidos**, na [Royal Mail](carrier:royal-mail) (Parcelforce para EMS) no **Reino Unido**, na Canada Post no **Canadá** ou na Australia Post na **Austrália**.

**Alfândega na União Europeia (Portugal) e noutros países.** Como a Índia fica fora da União Europeia, qualquer encomenda passa pela alfândega à chegada. Na **União Europeia**, desde 1 de julho de 2026 os bens comprados online fora da UE em remessas até 150 € pagam um direito aduaneiro da UE de 3 € por artigo (*per item*); desde 1 de novembro de 2026 acresce também uma taxa de tratamento da União de 2 € por artigo, qualquer que seja o valor, além do IVA na importação do país de destino e, acima de 150 €, dos direitos pautais habituais. Conta como um artigo o conjunto de mercadorias dentro de uma encomenda que partilham classificação pautal, descrição e, quando exigida, origem. Fora da UE — nomeadamente no **Brasil** — aplicam-se as regras aduaneiras e fiscais do próprio país de destino: vê [encomendas retidas na alfândega](guide:customs). A India Post inclui o regime Delivery Duty Paid (DDP) entre os serviços adicionais disponíveis para exportadores; pergunta ao vendedor se foi contratado.

**Suspensões de serviço.** Sempre que a India Post suspende ou retoma o serviço para determinado país — como a suspensão para o Canadá em outubro de 2025 ou a retoma para o Iraque, Kuwait e Líbano em agosto de 2026 —, publica um aviso em [News and Updates](https://www.indiapost.gov.in/media/newsandupdates).

**Envios com destino à Índia.** Uma encomenda expedida do estrangeiro para a Índia traz o número atribuído pelo correio de origem. Segue-a primeiro aí; as FAQ da India Post avisam que os dados enviados por administrações postais estrangeiras podem chegar com atraso.

## Seguimento da India Post parado, encomenda perdida ou devolvida

**Sem novas leituras.** A India Post esclarece que a falta de uma leitura não quer dizer que o objeto não tenha seguido viagem: a atualização online pode demorar, sobretudo a partir de zonas rurais da Índia ou de operadores postais no estrangeiro. Quando uma encomenda internacional mostra por último `Aircraft Departure`, já saiu da Índia: consulta o mesmo número no site do correio do teu país. Sabe mais em [porque é que o seguimento deixa de atualizar](guide:tracking-not-updating).

**Atraso, extravio ou dano (regras da India Post).** Junto da India Post pode apresentar-se reclamação depois de ultrapassado o padrão de entrega e no prazo de 60 dias após a transação, indicando o número do objeto; o prazo normal de tratamento indicado pela India Post é de 90 dias. No Speed Post doméstico na Índia, o atraso dá direito à devolução dos portes pagos e a perda, espoliação ou dano é indemnizada pelo dobro dos portes ou 1 000 ₹, consoante o valor mais baixo. Em mercadorias enviadas por EMS internacional, a cobertura vai até ao valor declarado ou 130 DSE (direitos de saque especiais), o menor dos dois, mais o porte, e um atraso superior a 5 dias além da norma publicada devolve 5 % da franquia; um International Tracked Packet está coberto até ao seu valor ou 1 000 ₹, o montante inferior. Se compraste numa loja online, avisa também o vendedor: vê [encomendas perdidas](guide:lost-parcel) e [encomendas danificadas](guide:damaged-parcel).

**Dada como entregue mas não recebida.** Reclama pelos canais abaixo ou, na Índia, no livro de reclamações existente em todas as estações de correio: consulta [entregue mas não recebida](guide:delivered-not-received).

**Devolvida ao remetente.** Ao registar um envio online na India Post, o remetente pode indicar uma morada de devolução diferente da sua. O Peek mostra as devoluções como «Devolvida ao remetente»: vê [devolvida ao remetente](guide:returned-to-sender).

## Apoio ao cliente da India Post e reclamações

Canais oficiais da India Post:

- **Apoio telefónico (gratuito na Índia):** `1800 266 6868`. Os operadores atendem das 8h00 às 20h00 (hora da Índia, IST); a linha automática (IVRS) funciona 24/7, domingos incluídos, exceto feriados oficiais (*gazetted holidays*) na Índia.
- **Reclamações:** online através da página [File a Complaint](https://www.indiapost.gov.in/grievance-redressal/file-a-complaint) da India Post (onde podes acompanhar o estado do pedido), numa estação de correio na Índia (junto do *postmaster* ou do *Superintendent of Post Offices*) ou pelo centro de atendimento. Não é preciso criar conta.
- **Recurso na Índia:** caso a India Post não resolva a reclamação em tempo razoável, o Directorate of Public Grievances do governo indiano (`dpg.gov.in`).

Na página inicial, a India Post alerta para ligações falsas que se fazem passar pelo organismo: usa apenas `www.indiapost.gov.in` e nunca partilhes códigos OTP nem dados bancários, de cartão ou UPI através de links não autorizados («do not share OTP, banking, card, UPI or other personal/financial information through unauthorized links»). Vê [mensagens fraudulentas de entregas](guide:delivery-scam-texts).

## Seguir uma encomenda da India Post com o Peek

O [Peek](/) reconhece duas letras, nove algarismos e `IN` como India Post quando o dígito de controlo está correto e mostra «Transportadora detetada»; «Alterar» permite escolher outra. Com um dígito de controlo errado (por exemplo `EE482910577IN`), avisa «O dígito de controlo deste número não bate certo.»: confirma o código que te deram ou escolhe «Seguir tal como está». Guardada sob a India Post, uma encomenda mostra «À espera de India Post» até à primeira leitura.

O Peek não lê diretamente o portal da India Post: obtém as leituras através do MySpeedPost, um site de seguimento fora do domínio da India Post que as recolhe junto da India Post (ou através do track91).

O Peek apresenta cada linha com a redação da India Post, acompanhada da estação postal, assinalando a estação no mapa quando a localidade é confirmada. Uma descolagem surge como `Aircraft Departure`. As leituras são organizadas nas etapas do Peek: uma tentativa falhada aparece como «Tentativa de entrega», e uma recusa, dano, extravio ou morada insuficiente como «Problema reportado». Através do track91 as linhas são mais curtas: `Dispatched` (expedido para o estrangeiro pela estação de permuta) e `Aircraft arrival in Transit country` (escala num país de trânsito) surgem como «Em trânsito», e `Delivered` como «Entregue». O Peek retira o nome do destinatário, a morada e a assinatura, e não apresenta data prevista de entrega. Lê os registos na Índia na hora indiana (UTC+5:30), convertendo todas as horas para o fuso do teu dispositivo; em peektracker.com e nas apps para iPhone e Android, uma linha por baixo do histórico indica esse fuso («Horas no teu fuso horário: …»).

Quando a India Post indica o país de destino — ou não o indica e pesquisas fora da Índia —, o Peek consulta também com o mesmo número `IN` o operador postal que conhece nesse país, como os [CTT](carrier:ctt) em Portugal, os Correios no Brasil, a [USPS](carrier:usps), a Royal Mail (ou Parcelforce para EMS), a Canada Post, a Australia Post, a [DHL](carrier:dhl) na Alemanha ou a [La Poste](carrier:la-poste) em França. Assim que esse operador devolve leituras mais recentes, o Peek muda para ele mantendo o histórico da India Post. O Peek abrange a maior parte da Europa e vários outros países, incluindo a NZ Post na Nova Zelândia e a Qatar Post no Catar, mas não tem ligação postal direta para Emirados Árabes Unidos, Kuwait, Omã, Singapura, Malásia ou Sri Lanca. Também não pergunta à India Post por números emitidos por outros correios: escolher manualmente a India Post num deles mostra «não emite números como este».

Enquanto mantiveres a encomenda aberta ou, no dia da entrega, com as notificações ativas, o Peek verifica novidades até a cada 10 minutos — e até a cada 2 minutos a partir das 6h00 locais no dia em que passa a «Em distribuição», enquanto o MySpeedPost ou o seguimento direto do teu correio responder (depois da passagem, o Peek segue o ritmo do correio de destino; apenas através de outros serviços de seguimento, de hora a hora, a cada 3 horas após um dia sem novidades e até a cada 15 minutos no dia da entrega com a encomenda aberta ou notificações ligadas). Fora destas situações, bem como ao fim de dois dias sem atualizações (ou 12 horas em «Etiqueta criada» ou «Aceite pela transportadora»), o Peek verifica de hora a hora. Alertas disponíveis: «Todos os registos», «Passos importantes» ou «Só a entrega». Se a encomenda parar enquanto está com a India Post, «Pede ajuda a India Post» abre a página Contact Us da India Post. O Peek não pode contactar a India Post, o teu correio nem a loja por ti.

## Perguntas frequentes sobre o seguimento da India Post

### Como seguir um envio Speed Post da India Post?

Introduz o número de 13 caracteres terminado em `IN` em indiapost.gov.in ou cola-o no Peek, que acompanha o envio até à entrega final, incluindo depois da passagem para os CTT, os Correios ou outro operador local.

### Porque é que o seguimento da India Post não atualiza?

As leituras podem demorar a surgir online, sobretudo a partir de estações rurais na Índia ou de administrações postais estrangeiras. Se o último registo de uma encomenda internacional for `Aircraft Departure`, ela já saiu da Índia: pesquisa o mesmo código no site do correio do teu país.

### Quanto tempo demora o Speed Post?

Dentro da Índia, o padrão da India Post é de 1 a 3 dias entre metrópoles e de 4 a 5 dias para o resto do país, mais um dia quando entrega um posto sucursal; o 24 Speed Post entrega no dia seguinte em seis metrópoles indianas. Para o estrangeiro, o tempo varia com a modalidade escolhida e o desalfandegamento no destino.

### Qual é o número de apoio ao cliente da India Post?

O `1800 266 6868` (gratuito na Índia): atendimento humano das 8h00 às 20h00 hora da Índia e linha automática disponível 24 horas por dia exceto feriados oficiais indianos. Fora da Índia podes submeter e consultar reclamações online em indiapost.gov.in sem criar conta.

:::sources
- [India Post: Home](https://www.indiapost.gov.in/) – seguimento, linha de apoio e alerta de fraude
- [India Post: Contact Us](https://www.indiapost.gov.in/help&Support/contactus) – número gratuito, horários e reclamações nas estações de correio
- [India Post: Help and FAQs](https://www.indiapost.gov.in/grievance-redressal/help-faq) – código de barras, objetos rastreáveis, estados, atrasos, reclamações, reencaminhamento e morada de devolução
- [India Post: About Us](https://www.indiapost.gov.in/ministry/aboutus) – Department of Posts e 164 999 estações de correio
- [India Post: Speed Post](https://www.indiapost.gov.in/mail&Parcel/mailproducts/premiumservices/speedpost) – OTP, padrões de entrega, SMS do Speed Post Parcel e indemnizações
- [India Post: 24/48 Speed Post FAQs](https://www.indiapost.gov.in/speedpost/faqs) – entrega no dia seguinte em seis metrópoles, OTP e reembolsos
- [India Post: India Post Parcel](https://www.indiapost.gov.in/mail&Parcel/mailproducts/domesticservices/indiapostparcel) – India Post Parcel
- [India Post: International Mail](https://www.indiapost.gov.in/mail&Parcel/mailproducts/mailservices/international) – serviços internacionais e opção DDP para exportadores
- [India Post: International services](https://www.indiapost.gov.in/mail&Parcel/mailproducts/internationalservices) – EMS, International Tracked Packet, encomendas aéreas e limites de compensação
- [India Post: News and Updates](https://www.indiapost.gov.in/media/newsandupdates) – avisos de suspensão e retoma por país
- [India Post: File a Complaint](https://www.indiapost.gov.in/grievance-redressal/file-a-complaint) – reclamações online e recurso para o DPG
- [India Post: Guidelines on Complaint Registration](https://www.indiapost.gov.in/grievance-redressal/guidelines) – prazos e canais de reclamação
- [Comissão Europeia: Reforma aduaneira da UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – direito aduaneiro de 3 € e taxa de tratamento de 2 €
- [Comissão Europeia: Orientações sobre o direito aduaneiro de 3 € e a taxa de tratamento da União (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – cobrança por artigo e definição de artigo
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – textos de leitura (incluindo track91), fusos horários e passagem aos operadores postais de destino
:::
