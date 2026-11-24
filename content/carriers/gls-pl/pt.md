---
title: Rastreio GLS Poland: estados, GLS Points, cacifos e ajuda
description: Segue uma encomenda GLS Poland: números de 11 ou 12 dígitos e Track-ID, código postal opcional, estados, GLS Points, cacifos ORLEN e contactos.
slug: rastreio-gls-poland
published: 2026-11-24
updated: 2026-11-24
---

A GLS Poland (*General Logistics Systems Poland Sp. z o.o.*, com sede em Głuchowo perto de Poznań e principal centro de triagem em Stryków perto de Łódź) opera cerca de 50 filiais e mais de 26 000 pontos de levantamento fora de casa na Polónia — incluindo mais de 18 000 GLS Points (entre eles todas as 11 000 lojas de conveniência Żabka) e cerca de 8 000 cacifos partilhados 24/7 [ORLEN Paczka](carrier:orlen-paczka). Acompanha a tua encomenda na [página de rastreio da GLS Poland](https://gls-group.com/PL/en/parcel-tracking/) com o número da encomenda ou Track-ID, ou cola o número na caixa acima.

## Número de rastreio GLS Poland e Track-ID: formatos

Encontra o número da encomenda no e-mail de envio da loja online, no aviso por e-mail/SMS da GLS ou num cartão de aviso (*awizo*). A caixa de pesquisa da GLS também aceita o Track-ID incluído nas notificações da GLS.

| Formato (inventado) | O que é |
| --- | --- |
| `48291736405` | Número de encomenda GLS de 11 dígitos. Também usado por [GLS Alemanha](carrier:gls-de), [GLS França](carrier:gls-fr), [GLS Suíça](carrier:gls-ch), [GLS Áustria](carrier:gls-at) e [PPL](carrier:ppl). |
| `482917364052` | Número de encomenda de 12 dígitos terminado num dígito de controlo. Também usado por [BRT](carrier:brt), [Colis Privé](carrier:colis-prive) e outras. |

Na [página de rastreio da GLS Poland](https://gls-group.com/PL/en/parcel-tracking/), introduzir o código postal do destinatário desbloqueia os detalhes completos de rastreio e as opções de entrega. Para outros formatos, consulta [formatos de números de rastreio](guide:tracking-number-formats).

## O que significam os estados de rastreio GLS Poland

A GLS Poland apresenta descrições em polaco no seu site polaco e descrições em inglês do grupo através da API internacional da GLS lida pelo Peek.

| A GLS Poland / GLS Group mostra | O que significa para ti | O Peek mostra |
| --- | --- | --- |
| `Preadvice`, `Nadawca nadał numer paczce - paczka nie została jeszcze przekazana kurierowi GLS` | O remetente criou a etiqueta eletronicamente; a GLS ainda não recebeu a encomenda física. | «Etiqueta criada» |
| `Paczka została przekazana GLS` | A GLS recolheu ou aceitou a encomenda na sua rede. | «Aceite pela transportadora» |
| `In transit`, `Final parcel center`, `Paczka zarejestrowana w filii GLS`, `Paczka wyjechała z filii GLS` | Em circulação pelos centros de triagem da GLS e filiais polacas até ao depósito distribuidor. | «Em trânsito» |
| `In delivery`, `Paczka w doręczeniu` | O estafeta carregou a tua encomenda para entrega hoje. | «Em distribuição» |
| Chegada a um GLS Point ou cacifo, que a GLS marca como entregue | À espera de levantamento num GLS Point ou cacifo 24/7. | «Pronta para levantamento» |
| `The parcel has been delivered.`, `Paczka została doręczona`, `Handing over the parcel to the recipient at the GLS ParcelShop.` | Entregue a ti, a pessoa autorizada ou a um vizinho, ou levantada num GLS Point. | «Entregue» |
| `Paczka niedoreczona - odmowa przyjecia przez Odbiorce` | O destinatário recusou a encomenda. | «Tentativa de entrega» |
| `Paczka pozostawiona w filii doreczajacej - Odbiorca odmowil przyjecia` | Recusada e retida na filial distribuidora. | «Problema reportado» |

No dia da entrega, o [Live Tracking da GLS Poland](https://gls-group.com/PL/en/live-tracking/) envia por e-mail um link com a hora prevista ajustada a 15 minutos e mostra o estafeta no mapa quando faltam 20 paragens ou menos. A barra de progresso da GLS marca como entregue uma encomenda deixada num GLS Point ou cacifo enquanto espera por ti; o Peek mostra essa etapa como «Pronta para levantamento», e como «Entregue» assim que a levantas.

## Prazos GLS Poland, GLS Points, cacifos ORLEN e FlexDeliveryService

**Dias e prazos de entrega.** A GLS Poland entrega de segunda a sexta-feira (entrega ao sábado apenas se encomendada expressamente pelo remetente). As encomendas nacionais normais chegam habitualmente no dia útil seguinte (sem garantia; o remetente pode contratar Express 10:00 ou 12:00). A partir da Polónia, a GLS indica 2 a 7 dias úteis para outros países da UE consoante o destino ([GLS Points](https://gls-group.com/PL/en/shipping-receiving-points/gls-points/)).

**Peso, dimensões e CashService (contra reembolso).** As encomendas nacionais ao domicílio podem pesar até 31,5 kg (40 kg internacionais ao domicílio; 31,5 kg em GLS Points selecionados; 20 kg nos restantes GLS Points, cacifos e myGLS), até 200 × 80 × 60 cm e comprimento mais perímetro até 300 cm. O pagamento na entrega (`CashService`, até 6 000 PLN por dia) pode ser feito ao estafeta em numerário, cartão ou BLIK.

**Entrega falhada e segunda tentativa.** Segundo a [página de receção da GLS Poland](https://gls-group.com/PL/en/receiving/), se não estiveres em casa o estafeta pode deixar a encomenda a um vizinho (com o teu consentimento) ou encaminhar uma encomenda elegível sem contra reembolso para um GLS Point próximo. Caso contrário, regressa à filial para uma segunda tentativa no dia útil seguinte; se continuar por entregar, permanece no depósito antes de voltar ao remetente (5 dias úteis para levantamento em depósito FlexDeliveryService, ou devolução após 6 dias úteis segundo o guia de receção).

**Prazo de guarda em GLS Points.** Nos termos das [condições da GLS Poland](https://gls-group.com/PL/pl/regulamin/) para GLS Points e cacifos (em vigor desde 9 de novembro de 2026) e das suas [FAQ](https://gls-group.com/PL/en/faq/), as encomendas aguardam num GLS Point **7 dias de calendário** antes de voltarem ao remetente (as páginas [receção](https://gls-group.com/PL/en/receiving/) e GLS Points ainda indicam sete dias úteis). Levanta-a com o PIN enviado por SMS ou e-mail; a GLS pode pedir identificação.

**Cacifos 24/7 ORLEN Paczka.** As encomendas enviadas para [cacifos partilhados ORLEN Paczka](https://gls-group.com/PL/en/shipping-receiving-points/parcel-lockers/) ficam guardadas **72 horas** e regressam automaticamente ao remetente após esse prazo. Abre o cacifo com o PIN de SMS/e-mail, código QR ou abertura remota na app myGLS — não é exigido documento, pelo que qualquer pessoa com o código pode levantar. Apoio técnico 24/7 para cacifos: `801 167 536` ou `502 167 536`.

**Redirecionamento FlexDeliveryService.** Através do link no e-mail de notificação da GLS (sem conta), o [redirecionamento de encomendas](https://gls-group.com/PL/en/parcel-redirection/) permite escolher um GLS Point, cacifo ORLEN, nova morada, nova data, levantamento no depósito, autorização de depósito ou recusa da encomenda.

## Alfândega, danos, reclamações e encomendas GLS Poland em falta

Em compras online, o vendedor detém o contrato de transporte com a GLS Poland e trata de reembolsos ou substituições.

**Encomendas fora da UE.** Os envios dentro da UE não passam pela alfândega. Para pedidos online chegados de fora da UE, segundo as [regras aduaneiras da Comissão Europeia](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en), aplica-se um direito aduaneiro fixo de 3 € por categoria de artigo até 150 € (em vigor desde 1 de julho de 2026) e uma taxa europeia de processamento de 2 € (em vigor desde 1 de novembro de 2026), além do IVA polaco de importação. Consulta [encomendas retidas na alfândega](guide:customs).

**Danos visíveis e ocultos.** Podes recusar uma encomenda visivelmente danificada na entrega para que o estafeta registe o dano e a devolva ao remetente. Segundo os [Termos de Serviço da GLS Poland](https://gls-group.com/PL/pl/regulamin/), danos ocultos detetados após a aceitação têm de ser comunicados em **7 dias** (por escrito para a sede da GLS ou através do [formulário de reclamações de consumidores](https://gls-group.com/PL/pl/reklamacje-konsumenckie/)). Os prazos gerais de reclamação de consumidores são de 30 dias a contar da aceitação pela GLS (45 dias por extravio, até 12 meses após o envio), respondendo a GLS em 30 dias. Consulta [encomenda danificada](guide:damaged-parcel) e [encomenda perdida](guide:lost-parcel).

**Rastreio parado ou entregue mas em falta.** Se o rastreio parar vários dias, consulta [porque o rastreio deixa de atualizar](guide:tracking-not-updating). Se surgir como entregue e não a tens, verifica se está num GLS Point, pergunta aos vizinhos e contacta a loja; consulta [entregue mas não recebida](guide:delivered-not-received) e [devolvida ao remetente](guide:returned-to-sender).

## Apoio ao cliente GLS Poland e alerta contra fraudes

Contacta primeiro a loja online para reembolsos. Para falar diretamente com a GLS Poland (dados em [GLS Contact](https://gls-group.com/PL/en/gls-contact/) e [Corporate Information](https://gls-group.com/PL/en/corporate-information/)):

- **Linha de apoio:** `+48 46 814 82 20` (segunda a sexta-feira 07:00–19:00, sábado 07:00–15:00).
- **E-mail:** `gls@gls-poland.com` (sede `office@gls-poland.com`).
- **Formulários online:** [formulário de contacto GLS Poland](https://gls-group.com/PL/en/gls-contact/) e [formulário de reclamações de consumidores](https://gls-group.com/PL/pl/reklamacje-konsumenckie/).
- **Apoio técnico 24/7 para cacifos:** `801 167 536` / `502 167 536`.
- **Sede:** General Logistics Systems Poland Sp. z o.o., ul. Tęczowa 10, Głuchowo, 62-052 Komorniki, Polónia.

> **Alerta de fraude nos [conselhos de segurança da GLS Poland](https://gls-group.com/PL/en/security-advices/):** a GLS nunca envia links por e-mail a pedir pagamento de mercadorias por cartão ou transferência (CashService/ExWorksService é pago diretamente ao estafeta em numerário, cartão ou BLIK na entrega) e nunca envia mensagens operacionais de contas gratuitas como Gmail ou Outlook. Reencaminha mensagens suspeitas para `security@gls-poland.com` (ou `dataprotection@gls-poland.com`) e denuncia links polacos de phishing à CSIRT NASK CERT (`incydent.cert.pl`, `cert@cert.pl` ou SMS `8080`). Consulta [SMS falsas de entregas](guide:delivery-scam-texts).

## Segue uma encomenda GLS Poland com o Peek

Cola o número no [Peek](/). Números de 11 e 12 dígitos são partilhados por várias transportadoras, pelo que o Peek pergunta às mais prováveis guiando-se pelo teu país (a região do telemóvel ou, na web, de onde acedes). Na Polónia pergunta primeiro à GLS Poland, antes de [GLS França](carrier:gls-fr), [GLS Alemanha](carrier:gls-de), [GLS Suíça](carrier:gls-ch) e outras como [PPL](carrier:ppl) nos 11 dígitos ou [BRT](carrier:brt) e [Colis Privé](carrier:colis-prive) nos 12, mostrando normalmente «GLS Poland tem esta encomenda.». Noutros países a GLS Poland só é consultada depois de as outras transportadoras prováveis não encontrarem o número. Um link colado `gls-group.com` ou `gls-group.eu` com `/PL/` no caminho e o número em `match=`, `parcelNumber=` ou `matchParcelNumber=` é seguido diretamente como GLS Poland. Caso contrário, toca em «Alterar» ao adicionar ou em «Alterar transportadora» numa encomenda já adicionada e escolhe GLS Poland.

O campo «Código postal de entrega» surge como «Opcional», mas a GLS só mostra os registos com ele, como diz a linha por baixo do campo: «GLS Poland só mostra os registos com o código postal de entrega.». Sem ele, o Peek obtém o estado da GLS mas sem os registos: entretanto consulta outros serviços de rastreio, e a página da encomenda propõe «Adicionar código postal». Com conta, o Peek tenta uma vez o último código postal que indicaste numa encomenda GLS Poland ou, na falta dele, o da tua última encomenda. Se a GLS não encontrar a encomenda com esse código postal, o Peek avisa e oferece «Alterar código postal». Enquanto a GLS aguarda o código postal ou não encontra a encomenda com o teu e nada responde durante um dia, o Peek verifica uma vez por dia; adicionar ou alterar o código postal verifica a encomenda de imediato. O código postal é enviado à GLS e, quando necessário, a serviços de rastreio, e nunca aparece em ligações partilhadas.

Com o código postal vês o estado, o local de cada registo no mapa, o dia previsto, o peso, a hora de entrega e, num GLS Point, o respetivo nome e morada. O teu nome, morada, contactos, assinatura e referência do remetente são descartados. O Peek lê o serviço em inglês da GLS em vez da página polaca: aí `W doręczeniu` surge como `In delivery` e `Paczka została doręczona` como `The parcel has been delivered.`. A chegada a um GLS Point ou cacifo aparece como «Pronta para levantamento». As horas são lidas na hora da Polónia.

O Peek verifica a GLS cerca de uma vez por hora, dia de entrega incluído, e atualizar à mão não verifica mais cedo (4 horas após uma verificação falhada); de 6 em 6 horas após sete dias sem novidades em «Etiqueta criada». Através de serviços de rastreio apenas, verifica de hora em hora e de 3 em 3 horas após um dia sem novidades.

Quando o último registo tem quatro dias e a encomenda continua em «Etiqueta criada», «Aceite pela transportadora» ou «Em trânsito», o Peek assinala-o com a data desse registo, como «Sem atualizações desde seg 12 out». A página mostra então: «É habitual uma encomenda ficar sem novidades em voos longos, na alfândega ou ao fim de semana. O Peek continua a perguntar a GLS Poland.», com a ligação «Pede ajuda a GLS Poland» para a página de contactos da GLS Poland. Após três semanas sem novos registos, mostra: «Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, contacta o vendedor. O Peek continua a perguntar a GLS Poland.»

Podes escolher os alertas «Todos os registos», «Passos importantes» ou «Só a entrega». O Peek apenas acompanha o rastreio e não pode redirecionar encomendas nem apresentar reclamações junto da GLS Poland em teu nome.

## Perguntas sobre o rastreio GLS Poland

### Quanto tempo guarda a GLS Poland uma encomenda num GLS Point ou cacifo ORLEN?

Segundo os atuais [Termos de Serviço](https://gls-group.com/PL/pl/regulamin/) e as [FAQ](https://gls-group.com/PL/en/faq/) da GLS Poland, os GLS Points guardam encomendas 7 dias de calendário (as páginas receção e GLS Points ainda referem sete dias úteis), enquanto os cacifos 24/7 ORLEN Paczka guardam encomendas 72 horas antes da devolução automática ao remetente.

### Porque pede a GLS Poland o meu código postal para mostrar todo o rastreio?

A GLS protege os detalhes do envio devolvendo o histórico completo de registos, opções de entrega e dados do ParcelShop apenas quando o número da encomenda coincide com o código postal do destinatário. Introduz o código postal de entrega no Peek para obteres o feed direto da GLS.

### Posso pagar uma encomenda GLS Poland CashService com cartão ou BLIK?

Sim. A GLS Poland indica que as entregas CashService (até 6 000 PLN por dia) podem ser pagas ao estafeta na entrega em numerário, cartão de pagamento ou BLIK — nunca através de um link inesperado por e-mail.

### Como redireciono uma encomenda GLS Poland para uma loja Żabka ou cacifo?

Abre o link FlexDeliveryService no e-mail de notificação da GLS (não precisa de conta) ou usa o [redirecionamento de encomendas](https://gls-group.com/PL/en/parcel-redirection/) para encaminhar a encomenda para um GLS Point (incluindo lojas Żabka), um cacifo ORLEN Paczka, outra morada ou um dia posterior.

:::sources
- [GLS Poland: Parcel Tracking](https://gls-group.com/PL/en/parcel-tracking/) – número de encomenda, Track-ID e consulta por código postal
- [GLS Poland: About GLS](https://gls-group.com/PL/en/about-gls/) – centro central em Stryków, filiais e visão geral da rede
- [GLS Poland: Corporate Information](https://gls-group.com/PL/en/corporate-information/) – entidade legal, morada em Głuchowo e `office@gls-poland.com`
- [GLS Poland: Receiving Parcels](https://gls-group.com/PL/en/receiving/) – entregas nos dias úteis, entrega a vizinhos, segunda tentativa, guarda em depósito e CashService
- [GLS Poland: Parcel Redirection](https://gls-group.com/PL/en/parcel-redirection/) – opções FlexDeliveryService sem conta
- [GLS Poland: GLS Points](https://gls-group.com/PL/en/shipping-receiving-points/gls-points/) – mais de 18 000 GLS Points (incluindo 11 000 lojas Żabka), mais de 26 000 pontos fora de casa, limites de peso/dimensões e prazos UE
- [GLS Poland: Parcel Lockers](https://gls-group.com/PL/en/shipping-receiving-points/parcel-lockers/) – ~8 000 cacifos partilhados 24/7 ORLEN Paczka, levantamento em 72 horas, PIN/QR/app myGLS, limite de 20 kg e linha técnica
- [GLS Poland: Live Tracking](https://gls-group.com/PL/en/live-tracking/) – vista em mapa e hora prevista com margem de 15 minutos
- [GLS Poland: FAQ](https://gls-group.com/PL/en/faq/) – 7 dias de calendário em GLS Point, CashService numerário/cartão/BLIK, respostas sobre entregas falhadas
- [GLS Poland: Termos de Serviço (Regulamin)](https://gls-group.com/PL/pl/regulamin/) – termos, os de GLS Points e cacifos em vigor desde 9 de novembro de 2026: levantamento em 7 dias de calendário, comunicação de danos ocultos em 7 dias, prazos de reclamação de 30/45 dias e resolução em 30 dias
- [GLS Poland: Contact](https://gls-group.com/PL/en/gls-contact/) – linha `+48 46 814 82 20` (seg–sex 07:00–19:00, sáb 07:00–15:00), `gls@gls-poland.com` e formulário de contacto
- [GLS Poland: Reclamações de Consumidores](https://gls-group.com/PL/pl/reklamacje-konsumenckie/) – apresentação online de reclamações
- [GLS Poland: Security Advices](https://gls-group.com/PL/en/security-advices/) – alertas de phishing, `security@gls-poland.com` e denúncia à CERT NASK
- [GLS Group: Security Advice](https://gls-group.com/GROUP/en/security-advice/) – domínios oficiais do grupo e regras CashService
- [Comissão Europeia: Reforma Aduaneira da UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – direito aduaneiro de 3 € até 150 € (desde 1 de julho de 2026) e taxa UE de 2 € (desde 1 de novembro de 2026)
- [Universal Parcel Scraper: gls-pl](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/gls-pl) – deteção 11/12 dígitos, prioridade na Polónia, código postal opcional, mapeamento de estados, frequência horária/4 horas e URL de ajuda
:::
