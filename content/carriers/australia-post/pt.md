---
title: Seguimento Australia Post: códigos, estados e cacifos
description: Segue uma encomenda da Australia Post: onde achar o article ID, o que significa cada estado em inglês, levantamento nos correios ou Parcel Locker e contactos.
slug: seguimento-australia-post
published: 2026-11-21
updated: 2026-11-21
---

A Australia Post é o operador postal nacional da **Austrália**. Podes seguir uma encomenda sem conta em [auspost.com.au/mypost/track](https://auspost.com.au/mypost/track), na aplicação AusPost ou colando o número na caixa acima. O código vem do remetente: consta da confirmação de encomenda da loja, de um e-mail enviado pela Australia Post ou do talão de aceitação numa estação de correios australiana.

## Número de seguimento da Australia Post: onde encontrá-lo e formatos

A Australia Post chama-lhe número de seguimento ou identificador de artigo (*tracking number, or article ID*). Encontra-se habitualmente num destes locais:

- **Compra online:** na confirmação da encomenda ou fatura da loja; se o remetente tiver indicado o teu e-mail à Australia Post, esta envia-te diretamente o número e as atualizações.
- **Envio entregue numa estação de correios na Austrália:** no recibo de aceitação.
- **Saqueta pré-paga (*prepaid satchel*):** junto ao código de barras e no autocolante destacável.

A Australia Post não consegue pesquisar envios pelo nome nem pela morada, pelo que sem número tens de o pedir ao remetente. As encomendas associadas ao e-mail ou telemóvel da tua conta MyPost aparecem automaticamente no perfil e na aplicação. Em `auspost.com.au/mypost/track` podes introduzir até 10 números em simultâneo.

A Australia Post não publica uma tabela oficial de formatos; eis as estruturas que o Peek reconhece (com caracteres inventados):

| Formato (exemplo) | O que identifica |
| --- | --- |
| `33X482910573000482910` | Identificador de artigo com 21 caracteres: 3 letras ou algarismos, 9 algarismos, `000` e 6 algarismos. |
| `2933X482910573000482910` | O mesmo identificador precedido de 2 algarismos (23 caracteres). |
| `94004829105736000482910` | 23 algarismos iniciados por `9400` com `000` antes dos últimos 6 algarismos: também da Australia Post. |
| `29ABC4829105` | Número de remessa (*consignment number*) de 12 caracteres. |
| `ABC4829105` | Número de remessa de 10 caracteres. |
| `ABCZ48291057` | Número de remessa StarTrack (12 caracteres): 3 letras ou algarismos, `Z` e 8 algarismos. |
| `0048291057364918273643` | Código de barras pré-pago de 22 algarismos iniciado entre `00` e `03` (com dígito GS1). |
| `R482910573649182736491827` | `R` seguido de 24 algarismos (25 caracteres). |
| `EE482910576AU` | Envio internacional EMS, encomenda ou correio seguido: `C`, `E` ou `L`, uma letra, 9 algarismos e `AU`. |
| `RR482910576AU` | Outro número postal internacional terminado em `AU` (como correio registado). |

Formatos parecidos: 2 letras e 10 algarismos (`AB4829105736`) pertencem à Aramex Australia ou Aramex New Zealand, ao passo que 22 algarismos começados por `9400` (`9400482910573649182736`) pertencem à [USPS](carrier:usps). Se o código não for reconhecido, pode conter uma gralha ou ainda não ter sido lido: a Australia Post sugere tentar novamente ao fim de 24 horas. Mais detalhes em [formatos de números de seguimento](guide:tracking-number-formats) e [como encontrar o número de seguimento](guide:find-tracking-number).

## O que significam os estados de seguimento da Australia Post

Acima das leituras detalhadas, a Australia Post apresenta um marco resumido em inglês:

| Estado na Australia Post | O que significa para a encomenda | O Peek mostra |
| --- | --- | --- |
| `Label created by sender` | O remetente criou a etiqueta; a Australia Post ainda não tem o volume. | «Etiqueta criada» |
| `Pending` | Ainda sem leitura na rede da Australia Post; costuma atualizar em 24 a 48 horas. | «À espera de Australia Post» (antes da primeira leitura) |
| `We've got it` | A Australia Post já recebeu a encomenda. | «Aceite pela transportadora» |
| `It's on its way` | Aceite, tratada e a circular entre centros de triagem. | «Em trânsito» |
| `International arrival - awaiting clearance` | Encomenda vinda do estrangeiro: aguarda desalfandegamento na Austrália. | «Na alfândega» |
| `Possible delay` | Mau tempo, imprevistos, erro de dados ou alfândega estão a atrasar o percurso, mas o envio prossegue. | «Em trânsito» / «Problema reportado» consoante o código |
| `It's coming today` / `Onboard for delivery` | No veículo de distribuição para entrega hoje. | «Em distribuição» |
| `Attempted delivery` | Não estava ninguém disponível ou o estafeta não conseguiu aceder à morada. | «Tentativa de entrega» |
| `Awaiting collection` | Aguarda levantamento na estação de correios ou no cacifo *Parcel Locker* indicado na leitura. | «Pronta para levantamento» |
| `Delivered` / `Delivered - Left in a safe place` | Entregue na morada ou deixada num local seguro (*Safe Drop*). | «Entregue» |
| `Returning to sender` | Não levantada a tempo, destinatário desconhecido, recusada ou danificada: em devolução ao remetente. | «Devolvida ao remetente» |

Se `Pending` ou `Ready for processing` se mantiver por mais de uma semana, confirma com o remetente se o envio foi efetivamente expedido. Na Austrália, as encomendas entre estados passam pelos grandes centros de triagem de Redbank (QLD), Chullora (NSW) ou Sunshine West (VIC), pelo que uma leitura longe da origem e do destino é normal. Numa encomenda vinda do estrangeiro para a Austrália, `It's on its way` significa que já chegou à Austrália e mantém-se até entrar no veículo de distribuição.

Na Austrália, os carteiros entregam habitualmente das 7h00 às 17h00, de segunda a sexta-feira; um volume que não chegue até às 17h00 tende a ter prioridade no dia útil seguinte. Com `It's coming today`, algumas zonas recebem um SMS com uma janela horária de 2 horas.

`Contact sender` indica que a encomenda regressou por dados incorretos ou incompletos ou após 10 dias úteis sem resposta a um aviso de levantamento; `Returned` confirma que já foi devolvida ao remetente, a quem cabe combinar novo envio.

## Entrega falhada, estações de correios e Parcel Lockers na Austrália

As regras abaixo aplicam-se a entregas efetuadas na **Austrália**:

Se não estiver ninguém em casa e não houver local seguro autorizado (*Safe Drop*), a encomenda segue para uma estação de correios próxima ou, se tiver o teu número de telemóvel, pode ir para um cacifo *Parcel Locker* (recebes um código de acesso). Os utilizadores MyPost recebem aviso por e-mail, aplicação ou SMS; os restantes podem ainda receber um cartão *Sorry We Missed You*. Vê também [entrega não conseguida](guide:missed-delivery).

Uma estação de correios australiana guarda o volume durante **10 dias úteis** antes de o devolver. A Australia Post não faz nova tentativa ao domicílio a partir de uma estação; transferir a encomenda para outra estação custa **7,55 AU$** pagos no levantamento (o formulário de transferência ainda apresenta 7,20 AU$), e se não souberes porque foi parar àquela estação podes participar um problema de entrega e pedir a isenção da taxa. Leva um documento de identificação com fotografia, ou dois sem fotografia desde que um mostre a tua morada. Outra pessoa pode levantar a encomenda com autorização pela aplicação, formulário ou cartão, acompanhada da própria identificação.

Os cacifos *Parcel Lockers* são gratuitos e, na maioria dos locais, estão abertos 24/7. Tens **2 dias úteis** (48 horas sem contar fins de semana e feriados) para levantar o volume com o código recebido ou o código QR da aplicação; findo esse prazo (com e-mail de aviso 24 horas antes), a encomenda é transferida para uma estação de correios próxima por 10 dias úteis.

Com uma conta gratuita MyPost podes redirecionar uma encomenda associada para outra morada até entrar em distribuição (conta com mais um dia útil) e escolher *Safe Drop* até uma hora antes da entrega.

## Seguimento sem atualização, atraso ou dada como entregue

A ausência de novas leituras durante alguns dias não significa que a encomenda tenha parado: a Australia Post refere que continua em trânsito e que a maioria dos envios atrasados chega até **5 dias úteis** após a data prevista. Consulta [seguimento que não atualiza](guide:tracking-not-updating).

A Australia Post encaminha primeiro o destinatário para o remetente a fim de confirmar o código e a morada e abrir um pedido de averiguação. Se não resolver, preenche o formulário online para objetos atrasados ou extraviados (requer conta gratuita MyPost) passados **10 dias úteis** sobre a data prevista. Na Austrália, as encomendas vindas do estrangeiro podem passar até **15 dias** na alfândega australiana — ou mais se houver direitos a pagar —, e a alfândega ou a quarentena contactam o destinatário por carta em caso de retenção prolongada; reclamações por atraso internacional são apresentadas junto do operador postal de origem através do remetente. Mais detalhes em [encomendas retidas na alfândega](guide:customs).

**Marcada como entregue mas não recebida.** Procura em locais abrigados da propriedade, fala com quem vive contigo ou com a portaria, consulta a fotografia de entrega na aplicação ou no formulário e submete a participação; se a fotografia mostrar a entrega na tua morada e o volume tiver desaparecido, participa à polícia. Em caso de danos na Austrália, leva a encomenda e a embalagem a uma estação de correios que efetue avaliação de danos. A maioria dos serviços inclui até **100 AU$** por perda ou dano (pagos em regra ao remetente nas perdas domésticas e ao destinatário nos danos), com prazo de **6 meses** a contar do envio; em compras online, contacta primeiro o vendedor. Vê [entregue mas não recebida](guide:delivered-not-received).

## Contactos da Australia Post e apoio

Canais divulgados pela Australia Post:

- **Telefone:** `13 76 78` (`13POST`) na Austrália ou `+61 3 8340 7239` a partir do estrangeiro, das 8h00 às 18h00 de segunda a sexta-feira exceto feriados nacionais australianos.
- **Online:** formulários para envios atrasados/extraviados e problemas de entrega, além de chatbot e assistentes no chat em [auspost.com.au/help](https://auspost.com.au/help).
- **Presencialmente na Austrália:** mais de 4 100 estações de correios prestam apoio para registar reclamações.

A StarTrack, divisão de expresso e carga da Australia Post, dispõe da linha `13 23 45` e de formulários próprios, mas os seus envios seguem na mesma rede e surgem também na aplicação AusPost.

> A Australia Post sublinha que nunca telefona, envia SMS nem manda e-mails a pedir dados pessoais ou bancários não autorizados ou pagamentos inesperados (como supostas taxas de nova entrega ou confirmação de código postal), mesmo que surjam sob o nome *AusPost*. Reencaminha mensagens suspeitas para `scams@auspost.com.au` e lê [mensagens falsas de entregas](guide:delivery-scam-texts).

## Seguir encomendas da Australia Post com o Peek

O [Peek](/) identifica de imediato a Australia Post para identificadores de artigo de 21 e 23 caracteres e para números internacionais EMS, encomenda ou correio seguido terminados em `AU` (`C…AU`, `E…AU`, `L…AU`) com algarismo de controlo válido, mostrando «Transportadora detetada» com «Alterar». Números de remessa (incluindo StarTrack), códigos pré-pagos de 22 algarismos e códigos `R` de 25 caracteres não chegam por si sós: ao adicioná-los, o Peek consulta as transportadoras prováveis («A verificar serviços de seguimento…») e apresenta «Australia Post tem esta encomenda.» assim que o sistema responde, ou permite-te escolher a Australia Post em «Alterar» sob «Corresponde a este número». Os restantes códigos postais terminados em `AU` abrem como envio postal enquanto o Peek consulta a Australia Post. Ligações de `auspost.com.au` com `id=` (incluindo `#/search?id=`) ou `/mypost/track/details/` atribuem logo a Australia Post qualquer que seja o número.

Se o sistema da Australia Post recusar o pedido direto do Peek, um navegador no servidor do Peek abre `auspost.com.au`; como os serviços externos guardam muito pouco histórico da Australia Post, os dados podem ficar curtos se o navegador também for bloqueado.

O Peek mostra o estado atual, cada leitura com a localidade no mapa, o nome do serviço (como `Parcel Post Large Satchel`), a hora exata da entrega, o país de destino e o nome da estação de correios ou do *Parcel Locker* onde o volume aguarda levantamento, deixando de fora destinatário, morada e fotografias de entrega (numa ligação partilhada, a entrega mostra apenas `Delivered`). As leituras próprias da Australia Post trazem o respetivo fuso australiano e as retransmitidas de operadores estrangeiros são lidas no relógio desse país, convertidas para o fuso do teu dispositivo («Horas no teu fuso horário: …» no fim do histórico em `peektracker.com` e nas aplicações para iPhone e Android). Enquanto a encomenda está em trânsito, o Peek mostra a data ou o intervalo previsto pela Australia Post — e, no próprio dia, a janela horária enquanto não tiver terminado —, ocultando a estimativa quando fica entregue, devolvida ou pronta para levantamento. Quando a Australia Post não indica data (como em envios depositados em marco de correio ou com morada manuscrita), a ferramenta web da própria Australia Post ainda pode estimar uma a partir do código postal. O Peek não pode redirecionar volumes nem mudar cacifos.

Enquanto o código não for reconhecido, o Peek apresenta «À espera de Australia Post»; se dois dias depois nem a Australia Post nem outra fonte responderem, mostra «Ao fim de dois dias, a transportadora ainda não reconhece este número. Verifica-o, escolhe outra transportadora ou elimina a encomenda.», e após quatro dias sem nova leitura em caminho disponibiliza «Pede ajuda a Australia Post».

Enquanto mantiveres a encomenda aberta no ecrã ou, no dia da entrega, com as notificações ativas, o Peek verifica novidades até a cada 10 minutos — e até a cada 2 minutos (a cada 5 minutos quando recorre ao navegador) no dia em que a Australia Post regista a saída para distribuição, a partir das 6h00 locais ou meia hora antes da abertura da janela horária indicada, enquanto o rastreador da Australia Post responder (através apenas de serviços de seguimento externos: de hora a hora, a cada 3 horas após um dia sem novidades e até a cada 15 minutos no dia da entrega com a encomenda aberta ou alertas ativos; quando um operador estrangeiro assume a entrega final, o Peek acompanha o ritmo desse operador) —; fora disso, e após dois dias sem novidades (ou 12 horas em «Etiqueta criada» ou «Aceite pela transportadora»), verifica de hora a hora. Podes escolher alertas para «Todas as leituras», «Passos importantes» ou «Só a entrega». Uma encomenda expedida do estrangeiro para a Austrália com código postal passa a ser seguida na Australia Post assim que esta mostre progresso.

## Perguntas frequentes sobre o seguimento Australia Post

### O que significa Pending no seguimento da Australia Post?

Significa que o volume ainda não teve leitura na rede da Australia Post: pode ainda não ter sido entregue pelo remetente ou ter sido depositado num marco de correio na rua na Austrália. Costuma atualizar em 24 a 48 horas. Um número antigo sem atividade há mais de 3 meses também mostra `Pending`.

### Quanto tempo guarda a Australia Post uma encomenda num Parcel Locker?

**2 dias úteis** (48 horas sem contar fins de semana e feriados). Depois passa para a estação de correios mais próxima com serviço de levantamento, onde fica **10 dias úteis** antes de ser devolvida ao remetente.

### Todas as encomendas da Australia Post têm número de seguimento?

Não: as modalidades *International Post Registered* e *International Economy* (via aérea e marítima) não incluem seguimento por defeito. Nas encomendas enviadas do estrangeiro para a Austrália, o seguimento depende do serviço contratado nos correios ou transportadora de origem.

:::sources
- [Australia Post: Seguimento de encomendas](https://auspost.com.au/mypost/track) – pesquisa até 10 números de seguimento de uma só vez
- [Australia Post: Lost tracking number](https://auspost.com.au/help/parcel-tracking/tracking-support/lost-tracking-number) – onde encontrar o número, sem pesquisa por nome e nova tentativa após 24 horas
- [Australia Post: Help with tracking](https://auspost.com.au/help/parcel-tracking/tracking-support/help-with-tracking) – article ID, centros de triagem e eventos recentes
- [Australia Post: What your status means](https://auspost.com.au/help/parcel-tracking/tracking-support/what-your-status-means) – Pending (24–48 h e mais de 3 meses), tentativa de entrega, cartão Sorry We Missed You, It's on its way do estrangeiro e SMS de 2 horas
- [Australia Post: Tracking service](https://auspost.com.au/personal/receiving/tracking-service) – seguimento sem conta e centros de Redbank, Chullora e Sunshine West
- [Australia Post: Taking longer than expected](https://auspost.com.au/help/parcel-tracking/taking-longer-than-expected) – Pending e Ready for processing acima de uma semana e margem de 5 dias úteis
- [Australia Post: Delays and disruptions](https://auspost.com.au/help/parcel-tracking/delays-and-disruptions) – significado de Possible delay
- [Australia Post: Return to sender](https://auspost.com.au/help/delivered-parcel/return-to-sender) – Returning to sender, Returned e Contact sender ao fim de 10 dias úteis
- [Australia Post: Missed parcel deliveries](https://auspost.com.au/personal/receiving/parcel-deliveries/missed-parcel-deliveries) – estação de correios ou cacifo após falha, identificação, autorização a terceiros e transferência por 7,55 AU$
- [Australia Post: Requested delivery but parcel at Post Office](https://auspost.com.au/help/delivered-parcel/parcel-at-post-office) – guarda de 10 dias úteis, sem nova entrega a partir da estação e pedido de isenção de taxa
- [Australia Post: Transfer to another post office](https://helpandsupport.auspost.com.au/s/products-and-services?transfertoPO) – formulário de transferência ainda com o valor de 7,20 AU$
- [Australia Post: 24/7 Parcel Lockers](https://auspost.com.au/help/collection-delivery/parcel-lockers) – abertura 24/7 na maioria dos locais, 48 horas sem fins de semana e feriados, código e lembrete 24 h antes
- [Australia Post: Redirect parcel or change address](https://auspost.com.au/help/parcel-tracking/redirect-parcel-change-address) – reencaminhamento com MyPost antes da distribuição e dia útil adicional
- [Australia Post: Leave in a safe place (Safe Drop)](https://auspost.com.au/help/collection-delivery/safe-drop) – ativação até 1 hora antes e entregas das 7h00 às 17h00 nos dias úteis
- [Australia Post: AusPost app](https://auspost.com.au/personal/receiving/australia-post-app) – avisos digitais, cartões físicos e janela de 2 horas
- [Australia Post: No recent update](https://auspost.com.au/help/parcel-tracking/no-recent-update) – ausência de leituras, 5 dias úteis, averiguação após 10 dias úteis e horário telefónico
- [Australia Post: Lost or late items](https://auspost.com.au/personal/receiving/parcel-deliveries/lost-late-items) – formulário de averiguação e conta MyPost
- [Australia Post: Customs](https://auspost.com.au/help/parcel-tracking/international-parcel/customs) – até 15 dias na alfândega australiana, cartas das autoridades e reclamação junto do operador de origem
- [Australia Post: International tracking](https://auspost.com.au/help/parcel-tracking/international-parcel/tracking) – serviços sem seguimento padrão (International Post Registered, International Economy) e seguimento de entrada
- [Australia Post: Delivered status but parcel not received](https://auspost.com.au/help/delivered-parcel/parcel-not-received) – verificações, fotografia de entrega e participação policial
- [Australia Post: Damaged parcel](https://auspost.com.au/help/delivered-parcel/damaged-parcel) – avaliação de danos em estação de correios
- [Australia Post: Compensation](https://auspost.com.au/personal/receiving/parcel-deliveries/compensation) – indemnização até 100 AU$, beneficiários, prazo de 6 meses e contacto prévio com o vendedor
- [Australia Post: Complaints and feedback](https://auspost.com.au/about-us/corporate-information/complaints-and-feedback) – linhas 13 76 78, +61 3 8340 7239, StarTrack 13 23 45 e apoio nas estações
- [Australia Post: Help & support](https://auspost.com.au/help) – formulários online e chat
- [Australia Post: Expected delivery date](https://auspost.com.au/help/parcel-tracking/tracking-support/expected-delivery-date) – data ou intervalo previsto, envios sem data e estimativa por código postal
- [Australia Post: StarTrack tracking](https://auspost.com.au/help/startrack/tracking) – seguimento StarTrack na mesma ferramenta e na aplicação AusPost
- [Australia Post: Scam alerts](https://auspost.com.au/about-us/about-our-site/online-security-scams-fraud/scam-alerts) – mensagens fraudulentas AusPost e reencaminhamento para scams@auspost.com.au
- [Australia Post: About us](https://auspost.com.au/about-us) – rede de mais de 4 100 estações de correios na Austrália
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formatos e ligações reconhecidos, gateway e leitura por navegador, data e janela previstas, fusos horários e privacidade
:::
