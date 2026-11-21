---
title: Seguimento DPD UK: número de 14 dígitos, estados e apoio
description: Segue uma encomenda DPD UK: formato do número de 14 dígitos, significado dos estados em inglês, opções Predict e Pickup no Reino Unido, alfândega e apoio.
slug: seguimento-dpd-uk
published: 2026-11-21
updated: 2026-11-21
---

A DPD UK (`DPDgroup UK Ltd`) é a transportadora britânica do grupo Geopost, detido pelo grupo francês [La Poste](carrier:la-poste) (*La Poste Groupe*). No **Reino Unido**, entrega encomendas de muitas lojas online e também expede remessas para a Europa e outros países. Podes consultar uma encomenda na página [Follow My Parcel da DPD UK](https://track.dpd.co.uk/) com o número de referência e o código postal de entrega, ou colar diretamente o número de 14 dígitos na caixa acima. Recebes esse número da loja, no SMS ou e-mail da DPD UK, ou junto ao código de barras no aviso de passagem (*sorry we missed you*).

## Onde está o número de seguimento da DPD UK e qual é o formato?

A loja que enviou a encomenda indica-te o número; também podes tocar na ligação do SMS ou e-mail enviado pela DPD UK. No **Reino Unido**, se o motorista deixou um cartão *sorry we missed you*, o número está ao lado do código de barras, e a página `track.dpd.co.uk` aceita igualmente o número desse aviso (*Calling card number*) acompanhado do código postal. Não o encontras? Vê [como encontrar o número de seguimento](guide:find-tracking-number).

As páginas de ajuda da DPD UK não descrevem uma regra oficial para o formato. Os números de encomenda vistos no seguimento da DPD UK têm 14 algarismos, muitas vezes começados por `1550` ou `1597`, podendo surgir no fim um décimo quinto carácter de controlo (algarismo ou letra):

| Número (inventado) | O que identifica |
| --- | --- |
| `15504829105710` | Número de encomenda da DPD UK: 14 algarismos começados por `1550` (ou `1597`). |
| `15504829105710S` | O mesmo número com o carácter de controlo da DPD acrescentado no fim. |
| `18429105736482` | Outros 14 algarismos: formato partilhado entre a DPD UK, outras filiais DPD e outras transportadoras. |

Outras redes também usam 14 algarismos — como a [DPD Portugal](carrier:dpd-pt), a [SEUR](carrier:seur), a [BRT](carrier:brt), a [Chronopost](carrier:chronopost), a [DPD Alemanha](carrier:dpd-de) e a DHL eCommerce UK —, pelo que os algarismos sozinhos nem sempre provam quem tem a encomenda. No Reino Unido, os SMS verdadeiros aparecem em nome de `DPD UK` (ou `DPD Local` / `DPDgroup UK`), enquanto o nome da loja surge no corpo da mensagem. A DPD Local é a marca irmã da DPD UK (a aplicação DPD mostra encomendas de ambas). Na **Irlanda**, as entregas ficam a cargo da DPD Ireland, que tem numeração própria.

## Estados de seguimento da DPD UK explicados

O seguimento da DPD UK está apenas em inglês. Estas são as mensagens mais comuns pela ordem habitual:

| A DPD UK mostra | O que significa para a tua encomenda | O Peek mostra |
| --- | --- | --- |
| `We've received your order details, but have not yet received your parcel` | A loja registou o envio, mas a DPD UK ainda não recebeu a encomenda. | «Etiqueta criada» |
| `We've received your order details, and we're expecting your parcel shortly` | A etiqueta foi criada e a DPD UK aguarda que o remetente entregue a encomenda numa loja DPD Pickup Shop. | «Etiqueta criada» |
| `Your parcel has been dropped off at a DPD Pickup Shop` | O remetente entregou a encomenda num ponto DPD Pickup Shop. | «Aceite pela transportadora» |
| `We have your parcel and it's on its way to our depot` | A DPD UK recolheu a encomenda e leva-a para o centro operacional. | «Aceite pela transportadora» (recolha direta) ou «Em trânsito» (após entrega numa Pickup Shop) |
| `We have your parcel and it's on its way to you` | A encomenda segue na rede da DPD UK em direção à tua zona. | «Em trânsito» |
| `Your parcel has arrived at our depot`, `Your parcel is at our depot` | No centro operacional da DPD UK, em triagem. | «Em trânsito» |
| `Your parcel is at our depot ready for delivery` | No centro operacional, pronta para sair para distribuição; mais tarde a mesma linha volta a aparecer como `Your parcel is at our depot`. | «Em trânsito» |
| `A notification has been sent to advise you when the delivery will take place` | A DPD UK enviou-te o SMS ou e-mail com o dia ou a janela horária de entrega. | Mantém-se o estado anterior |
| `As requested, your parcel will now be delivered on Thu 05 November 2026 at 11:00` | Escolheste outro dia de entrega (por vezes com hora); a data e a hora variam. | Mantém-se o estado anterior |
| `Your parcel will be with you today`, `Your parcel will be with you today between 14:18 and 15:18` | Em distribuição hoje, com a janela de uma hora quando indicada (as horas variam). | «Em distribuição» |
| `As requested, your parcel will now be left in your selected safe place` | Tu (ou as tuas preferências na aplicação DPD) escolheram um local seguro onde deixar a encomenda. | Mantém-se o estado anterior |
| `We missed you. Your driver won't be back today but we'll be back Wed 28 October with your parcel` | Tentativa falhada; nova passagem no dia indicado. O Peek retira o nome do motorista incluído na frase da DPD UK. | «Tentativa de entrega» |
| `Your parcel has been delivered` | Entregue (o cabeçalho na página da DPD UK acrescenta a hora e o dia). | «Entregue» |
| `Your parcel is waiting for you at home (left in safe place)` | Entregue no local seguro selecionado. | «Entregue» |
| `Your delivery has been cancelled` | O remetente cancelou a entrega; a DPD UK pede que contactes a loja indicada. | «Problema reportado» |
| `We have your parcel and it's on its way back to the sender` | Devolução em curso ao remetente (exceto antes do centro operacional numa etiqueta entregue numa Pickup Shop, em que segue para a conta que reservou a etiqueta: «Em trânsito»). | «Problema reportado» |

`We have X-rayed your parcel as part of our normal security procedures` é um rastreio de segurança de rotina que não exige qualquer ação («Em trânsito» no Peek), e `There has been a delay with your delivery` é um aviso de atraso que mantém a etapa anterior. Quando uma encomenda da DPD UK sai do **Reino Unido** para o estrangeiro, `Your parcel has left our sortation facility and is on it's onwards journey` (com a grafia `it's` da DPD UK) assinala a saída da triagem britânica, seguindo-se as linhas em inglês do parceiro de entrega no destino: `The parcel is in transit to its final destination` («Em trânsito»), `The parcel is undergoing customs clearance` e `Duties and Taxes paid` («Na alfândega»), `The parcel has now cleared customs` e `The parcel has arrived at the delivery depot` («Em trânsito»), `The parcel is on the vehicle for delivery` («Em distribuição») e `The parcel has been delivered` («Entregue»). Mais detalhes em [o que significam os estados de seguimento](guide:tracking-statuses).

## Predict, lojas Pickup no Reino Unido e alfândega para Portugal e a UE

As regras sobre Predict, vizinhos e lojas Pickup abaixo aplicam-se às entregas realizadas no **Reino Unido**:

**Janela Predict.** No Reino Unido, a DPD UK envia por SMS e e-mail uma janela de entrega de uma hora no próprio dia, assim que o motorista carrega e lê as encomendas na carrinha — normalmente até ao meio-dia (*usually by 12 noon*). Um mapa em tempo real reduz depois a previsão para um intervalo de 15 minutos e mostra o nome do motorista e a tua posição na fila de entregas. Não é possível alterar a janela horária em si.

**Alterar a entrega.** Na véspera à noite e no dia da entrega, o SMS, o e-mail ou a aplicação DPD permitem no Reino Unido escolher outra data, um vizinho indicado, um local seguro (*safe place*) ou uma loja DPD Pickup Shop, ou pagar um suplemento para entrega antes das 10:30, antes das 12:00, de tarde, ao sábado ou ao domingo. Se o remetente tiver exigido assinatura, algumas opções não ficam disponíveis. Para mudar para [outra morada de entrega](guide:change-delivery-address), fala diretamente com o vendedor.

**Ausente na entrega.** No Reino Unido, a DPD UK tenta automaticamente entregar a um vizinho: as suas condições gerais de transporte definem *Neighbour* como quem resida ou trabalhe num imóvel até 50 metros a pé da morada de entrega (*within 50 metres walking distance of the Delivery Address*). O motorista não pode voltar no mesmo dia. Com o número de referência e o código postal em `track.dpd.co.uk`, vês onde ficou o cartão *sorry we missed you* e escolhes nova data, local seguro, vizinho ou recolha numa loja Pickup; a DPD UK não publica um número fixo de tentativas. Se surgir como entregue mas não estiver contigo, verifica primeiro o local seguro e os vizinhos próximos e consulta [entregue mas não recebido](guide:delivered-not-received) e [entrega falhada](guide:missed-delivery).

**Lojas DPD Pickup e cacifos no Reino Unido.** A DPD UK tem mais de 6 000 lojas Pickup no Reino Unido (incluindo espaços Sainsbury's, Post Office, Co-op, Morrisons, Argos e Matalan). Uma encomenda desviada para lá fica habitualmente pronta no dia seguinte. Leva dois documentos (*2 pieces of ID*): identificação com fotografia e comprovativo de morada, como uma fatura de serviços, além do *DPD Pickup Pass* disponível no seguimento. As páginas de ajuda da DPD UK não fixam um número único de dias de levantamento nas lojas Pickup; a aplicação DPD disponibiliza também entrega em cacifo (*Locker*).

**Encargos de importação no Reino Unido.** As encomendas vindas do exterior para a Grã-Bretanha podem pagar IVA britânico (exceto ofertas até 39 £) e direitos aduaneiros, cobrados pela transportadora (pagáveis em `track.dpd.co.uk` em *import charges*); segundo o GOV.UK, a transportadora guarda normalmente o envio cerca de 3 semanas (*about 3 weeks*) antes de o devolver por falta de pagamento.

**Encomendas enviadas do Reino Unido para Portugal e a União Europeia.** Como o Reino Unido não pertence à UE, uma encomenda vinda de lá passa na alfândega à chegada. Na **União Europeia**, desde 1 de julho de 2026 os bens comprados online fora da UE em remessas com valor intrínseco até 150 € pagam um direito aduaneiro da UE de 3 € por artigo (*per item*), e desde 1 de novembro de 2026 acresce uma taxa de manuseamento da União (*Union handling fee*) de 2 € por artigo independentemente do valor (um artigo agrupa mercadorias da mesma encomenda com igual classificação pautal, descrição e, quando exigida, origem; acima de 150 € aplicam-se os direitos pautais normais), além do IVA de importação em **Portugal** (incluindo os **Açores** e a **Madeira**, que segundo o âmbito territorial publicado pela Comissão Europeia integram tanto o território aduaneiro como a área do IVA da UE). Consulta também [encomenda retida na alfândega](guide:customs).

## Encomenda DPD UK atrasada, perdida, danificada ou mensagens falsas

**Atrasos.** Nas condições de transporte da DPD UK, todos os prazos de entrega são meras estimativas (*estimates only*). Para entregas no **Reino Unido**, a página [Service Disruption da DPD UK](https://www.dpd.co.uk/content/products_services/uk_issues.jsp) lista os códigos postais britânicos afetados por constrangimentos. Se não houver movimento durante dias, lê [porque é que o seguimento não atualiza](guide:tracking-not-updating) e contacta a loja.

**Perda ou danos.** O contrato com a DPD UK pertence ao remetente (*Customer*), a quem cabe apresentar a reclamação de indemnização. Segundo as condições gerais da DPD UK, o remetente deve participar a perda ou o dano no prazo de 14 dias após a expedição (*within 14 days of the date of despatch*), enviar comprovativos em 28 dias após pedido e formalizar a reclamação até um ano a contar da data de expedição (*within one year from the date of despatch*). Guarda a embalagem, tira fotografias e fala com o vendedor: vê [encomenda perdida](guide:lost-parcel) e [encomenda danificada](guide:damaged-parcel).

**Devolução ao remetente.** De acordo com a política de devolução e abate da DPD UK, quando não consegue entregar uma remessa retida nas suas instalações, a DPD UK reserva-se o direito de a devolver ao remetente após um mínimo de 5 dias de calendário (*after a minimum of 5 calendar days*); bens que não possam ser devolvidos podem ser eliminados após 4 semanas (12 semanas no caso de bens pessoais). Vê [devolvida ao remetente](guide:returned-to-sender).

> A DPD UK sublinha que nunca pede o pagamento de uma taxa de nova entrega (*redelivery fee*) através de uma ligação num e-mail, SMS ou iMessage. No Reino Unido, os alertas automáticos são enviados por SMS (nunca por iMessage) com o remetente `DPD UK`, `DPD Local` ou `DPDgroup UK` — nunca a partir de um endereço de e-mail ou número de telemóvel aleatório —, os e-mails genuínos terminam em `@dpd.co.uk`, `@dpdlocal.co.uk`, `@dpdgroup.co.uk` ou `@dpd.uk` e as ligações usam apenas `www.dpd.co.uk`, `www.dpdlocal.co.uk` ou `www.dpdgroup.co.uk`. No **Reino Unido**, SMS suspeitos podem ser reencaminhados para `7726` e e-mails fraudulentos denunciados para `report@phishing.gov.uk`; vê também [SMS falsos de entregas](guide:delivery-scam-texts).

## Apoio ao cliente e contactos da DPD UK

Canais oficiais da DPD UK no **Reino Unido**:

- **Telefone:** `0121 275 0500` (número britânico), de segunda a sexta-feira das 8:00 às 18:30, sábado das 8:00 às 16:00 e domingo das 9:00 às 15:00 (hora do Reino Unido), exceto feriados bancários britânicos (*bank holidays*). A página de contacto prevê 5 a 10 minutos de espera (a *Complaints Charter* aponta para atendimento em 2 minutos e 30 segundos). Tem à mão o número da encomenda e o código postal de entrega.
- **Chat online:** na [página Contact Us da DPD UK](https://www.dpd.co.uk/content/how-can-we-help/contact.jsp), introduzindo o número de referência e o código postal de entrega; essa página indica funcionamento `24/7`, enquanto a *Complaints Charter* da DPD UK refere o mesmo horário do atendimento telefónico (seg.–sex. 8:00–18:30, sáb. 8:00–16:00, dom. 9:00–15:00).
- **Aplicação DPD:** botões de chat e chamada por baixo do mapa de seguimento; a DPD UK indica que quem liga diretamente pela aplicação passa para a frente da fila.
- **Correio postal e reclamações:** `Customer Services, DPD UK, Roebuck Lane, Smethwick, B66 1BY`, Reino Unido. Na *Complaints Charter*, a DPD UK procura responder em 5 dias úteis (até 28 dias em reclamações mais complexas).

## Seguir encomendas da DPD UK com o Peek

O [Peek](/) reconhece um número DPD de 14 algarismos (com ou sem o 15.º carácter de controlo), mas como o formato é partilhado consulta as transportadoras compatíveis para saber qual delas tem a encomenda: **primeiro a DPD UK** quando o número começa por `1550` ou `1597`, caso contrário primeiro as transportadoras de 14 algarismos que operam no teu país. Quando a DPD UK confirma o número, surge «Tem esta encomenda» e o Peek acompanha-a aí. Se aparecer «ainda não encontrada · o Peek continua a verificar» ou outra transportadora, carrega em «Alterar» (ou «Alterar transportadora») e escolhe **DPD UK**. Colar uma ligação de `track.dpd.co.uk` ou `tracking.dpd.co.uk` com `reference=` ou `parcelNumber=`, ou de `track.dpdlocal.co.uk` com `reference=`, seleciona logo a DPD UK. Referências de encomenda e números de aviso que exigem código postal não são suportados. Se um número guardado sob a DPD UK continuar desconhecido da DPD UK e dos serviços de seguimento, o Peek mostra inicialmente «À espera de DPD UK» e, ao fim de dois dias, «Ao fim de dois dias, a transportadora ainda não reconhece este número. Verifica-o, escolhe outra transportadora ou elimina a encomenda.»

As leituras da rede própria da DPD UK não trazem fuso horário: o Peek interpreta-as na hora do Reino Unido (`Europe/London`) e converte-as para o fuso do teu dispositivo (registos de uma Pickup Shop ou de um parceiro estrangeiro sem fuso mantêm a hora local indicada). Em peektracker.com e nas aplicações para iPhone e Android, no fim do histórico aparece «Horas no teu fuso horário: …». Os estados mantêm o texto original em inglês da DPD UK, omitindo o nome do motorista nas entregas falhadas e nomes de recebedor ou assinante após `delivered to …` ou `signed for by …`.

A DPD UK não indica localidade nem centro operacional nos eventos, e o Peek não lê a data prevista nem a janela Predict de uma hora; descarta identificadores internos, referências comerciais, dados do destinatário e fotografias de entrega. O Peek mostra a loja que reservou o envio como «Remetente: …» tal como surge na DPD UK, **exceto** quando o histórico mostra que a encomenda foi entregue numa DPD Pickup Shop: nessas etiquetas (como devoluções), o titular da conta não é quem envia e o Peek oculta esse campo. A DPD UK reutiliza números passados alguns meses, pelo que um código antigo pode mostrar uma remessa mais recente. Nos envios internacionais, o Peek mantém-se ligado ao histórico da DPD UK sem transição automática: se o vendedor te der um número local separado (por exemplo da DPD Portugal), adiciona-o como encomenda própria no Peek.

Enquanto mantiveres a página da encomenda aberta ou, no dia da entrega, tiveres as notificações ativas, o Peek procura atualizações até a cada 10 minutos — e até a cada 2 minutos a partir das 6:00 (hora do Reino Unido) no dia em que a DPD UK coloca a encomenda «Em distribuição», enquanto o localizador direto da DPD UK responder (apenas através de serviços de seguimento: de hora a hora, a cada 3 horas após um dia sem movimento e até a cada 15 minutos no dia da entrega com a encomenda aberta ou notificações ativas). Nos restantes casos, bem como após dois dias sem novidades (ou 12 horas em «Etiqueta criada» ou «Aceite pela transportadora»), o Peek verifica de hora a hora. Podes escolher alertas para «Todas as leituras», «Passos importantes» ou «Só a entrega» / «Só no dia da entrega». Para alterar opções de entrega no Reino Unido, usa o site ou a aplicação da DPD UK: o Peek não pode modificar a entrega nem contactar a DPD UK por ti.

## Perguntas frequentes sobre o seguimento da DPD UK

### A DPD UK faz parte da Royal Mail?

Não. A DPD UK é operada pela `DPDgroup UK Ltd` e pertence à Geopost, a divisão internacional de encomendas do grupo francês [La Poste](carrier:la-poste) (*La Poste Groupe*).

### Quanto tempo demora a DPD UK a entregar no Reino Unido?

Depende do serviço escolhido pela loja: no Reino Unido, o `DPD NEXT DAY` aponta para o dia útil seguinte até ao fecho comercial (com opções antes das 10:30, antes das 12:00, ao sábado e ao domingo). As Ilhas do Canal, a Ilha de Man e as Ilhas Scilly demoram dois dias por defeito, e a Irlanda do Norte um dia por avião ou dois por estrada e mar.

### Onde posso ver a localização do motorista da DPD UK?

Nas entregas no Reino Unido com Predict, o SMS ou e-mail enviado na manhã da distribuição (normalmente até ao meio-dia) abre um mapa em tempo real com o nome do motorista, o teu lugar na fila de entregas e um intervalo ajustado a 15 minutos.

### Durante quanto tempo a DPD UK guarda uma encomenda numa Pickup Shop?

As páginas de ajuda da DPD UK não publicam um prazo fixo único para as lojas Pickup britânicas; consulta o aviso de recolha ou o teu *DPD Pickup Pass* em `track.dpd.co.uk`. Em termos gerais, a política de devolução da DPD UK prevê que remessas não entregues retidas nas suas instalações possam ser devolvidas ao remetente após pelo menos 5 dias de calendário.

:::sources
- [DPD UK: Help](https://www.dpd.co.uk/content/how-can-we-help/index.jsp) – número e aviso, janela até ao meio-dia, vizinho, mais de 6 000 lojas Pickup e documentos
- [DPD UK: Contact Us](https://www.dpd.co.uk/content/how-can-we-help/contact.jsp) – telefone 0121 275 0500, horários, espera, chat e chamada pela app
- [DPD UK: Predict & Follow My Parcel](https://www.dpd.co.uk/content/products_services/predict_fmp.jsp) – janela de uma hora, mapa a 15 min, motorista e opções
- [DPD UK: Follow My Parcel](https://track.dpd.co.uk/) – pesquisa com referência ou aviso e código postal, Pickup Pass e encargos de importação
- [DPD UK: Cyber Security](https://www.dpd.co.uk/content/about_dpd/phishing.jsp) – remetentes SMS, sem taxas por ligação, domínios, 7726 e report@phishing.gov.uk
- [DPD UK: DPD Pickup](https://www.dpd.co.uk/content/products_services/dpd_pickup.jsp) – desvio para ponto DPD Pickup e devoluções
- [DPD UK: The DPD app](https://www.dpd.co.uk/dpdapp/) – preferências e cacifo (Locker)
- [DPD UK: App FAQs](https://www.dpd.co.uk/lp/app/index.html) – encomendas DPD UK e DPD Local na app
- [DPD UK: Standard Terms & Conditions of Carriage (PDF)](https://www.dpd.co.uk/pdf/dpd-stcc.pdf) – vizinho até 50 m, prazos estimados e reclamações (14 dias, 28 dias, um ano)
- [DPD UK: Complaints Charter (PDF)](https://www.dpd.co.uk/pdf/dpd-complaints-charter.pdf) – horários, morada em Smethwick e resposta em 5–28 dias
- [DPD UK: Return, Disposal and Destruction of Goods Policy](https://www.dpd.co.uk/return-disposal-of-goods-policy.jsp) – devolução após 5 dias de calendário e guarda por 4 ou 12 semanas
- [DPD UK: UK Delivery Services](https://www.dpd.co.uk/content/products_services/uk_delivery_services.jsp) – Next Day, 10:30, 12:00, fim de semana, ilhas e Irlanda do Norte
- [DPD UK: Service Disruption](https://www.dpd.co.uk/content/products_services/uk_issues.jsp) – códigos postais britânicos afetados
- [GOV.UK: Tax and customs for goods sent from abroad](https://www.gov.uk/goods-sent-from-abroad/tax-and-duty) – encargos de importação e guarda de cerca de 3 semanas
- [Geopost: About us](https://www.geopost.com/en/about-us/) – La Poste Groupe
- [Comissão Europeia: Reforma aduaneira da UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – direito de 3 € e taxa de 2 €
- [Comissão Europeia: Orientações sobre o direito de 3 € e a taxa (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – cobrança por artigo
- [Comissão Europeia: Âmbito territorial do IVA e das alfândegas](https://taxation-customs.ec.europa.eu/taxation/vat/vat-directive/how-does-vat-work/territorial-scope_en) – Açores e Madeira
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – números, reconhecimento, estados, fuso horário e remetente
:::
