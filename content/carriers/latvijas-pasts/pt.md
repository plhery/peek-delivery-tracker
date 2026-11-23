---
title: Seguimento Latvijas Pasts: números, estados e cacifos
description: Acompanha uma encomenda Latvijas Pasts (correios da Letónia): formato do código, significado dos estados, cacifos, entregas falhadas e contactos.
slug: seguimento-latvijas-pasts
published: 2026-11-23
updated: 2026-11-23
---

A Latvijas Pasts é o operador postal estatal da Letónia e dispõe da maior rede de cacifos de encomendas do país. Podes acompanhar um envio em [mans.pasts.lv/track](https://mans.pasts.lv/track?locale=en-GB) — que abre em letão e tem seletor de idioma (incluindo inglês) — ou colar o número na caixa acima. O código vem na mensagem de envio da loja ou do remetente, ou no SMS do estafeta antes da entrega.

## Número de seguimento Latvijas Pasts

Um envio expedido pela Latvijas Pasts tem um [número postal internacional](guide:tracking-number-formats) de 13 caracteres: duas letras, nove dígitos e `LV` no fim. As duas primeiras letras identificam o serviço:

| Prefixo (exemplos fictícios) | Tipo de envio |
| --- | --- |
| `RR482917367LV` | Envio registado (Standard +): entregue mediante assinatura. |
| `LA482917367LV` | Envio rastreável (Standard): registado em cada etapa, colocado na caixa de correio sem assinatura. |
| `CA482917367LV` | Encomenda: recebes um aviso para levantar nos correios. |
| `EE482917367LV` | Express Mail Service (EMS / Premium). |

Os envios Simple ou Economy não são registados em nenhuma fase, pelo que não têm seguimento nem indemnização em caso de danos. Se não tens número, o envio pode ter seguido nessa modalidade; consulta [como encontrar um número de seguimento](guide:find-tracking-number).

Onde procurar: o SMS do estafeta traz o número do envio, a hora estimada e um link para redirecionar a encomenda. O SMS do cacifo indica a morada, o prazo de levantamento e o código de abertura.

Outras letras finais pertencem a outros correios: `…LT` é [Lietuvos paštas](carrier:lietuvos-pastas) e `…EE` é [Omniva](carrier:omniva). Um envio vindo do estrangeiro mantém o número de origem, aceite também em mans.pasts.lv/track assim que a Latvijas Pasts recebe o objeto ou os seus dados; os registos estrangeiros mostram o país em vez de uma estação letã.

## O que significam os estados da Latvijas Pasts

O rastreador mostra cada etapa com data, hora e posto letão. Nas expressões em inglês lidas pelo Peek, eis os estados mais comuns pela ordem habitual:

| Estado Latvijas Pasts | O que significa para ti |
| --- | --- |
| `Electronic data about shipment received` | [Os dados do remetente](guide:tracking-statuses) chegaram à Latvijas Pasts, que ainda não tem o volume. |
| `Item information received` | O remetente registou o envio; a Latvijas Pasts ainda não o recebeu. |
| `Item received from sender` | A Latvijas Pasts recebeu o envio do remetente. |
| `Item posted / received from customer` | Entregue num posto postal; o registo inicial de um correio estrangeiro usa o mesmo texto. |
| `Item sent to the processing facility` | Saiu do posto de aceitação rumo ao centro de triagem. |
| `Item arrived at office of exchange` | Chegou ao centro de permuta internacional, em Riga ou no estrangeiro. |
| `Item sent to the Customs` | Apresentado à alfândega no centro de permuta à saída do país de envio. |
| `Item returned from Customs` | Libertado pela alfândega: não é uma devolução ao remetente. |
| `Item sent abroad` | Saiu do centro de permuta para o país de destino. |
| `Item sent abroad (EDI-received)` | O correio estrangeiro expediu o envio para a Letónia; a Latvijas Pasts sabe-o pelos dados eletrónicos. |
| `Item accepted for temporary storage` | Envio do estrangeiro em depósito temporário sob controlo aduaneiro até ao desalfandegamento. |
| `Electonic notification (SMS) of customs clearance sent to recipient` | Foi-te enviado um SMS sobre o desalfandegamento (a gralha é da própria Latvijas Pasts). Continua em depósito temporário. |
| `Customs clearance completed, item to be delivered to recipient` | Desalfandegamento concluído; segue para a estação distribuidora. |
| `Item arrived at processing facility`, `Item arrived at sorting centre` | Chegou a um centro de triagem. |
| `Item sent to next processing facility` | A caminho do centro de triagem seguinte. |
| `Item sent to delivery facility` | A caminho do posto responsável pela entrega ou levantamento. |
| `Item received at delivery office` | Na estação de entrega; ainda pode sair para distribuição a partir daí. |
| `Item redirected` | Redirecionado, por exemplo para outro ponto de levantamento (ver redirecionamento abaixo). |
| `Item address re-labelled` | Reetiquetado para a nova morada, para onde segue agora. |
| `Item handed over for delivery` | Em distribuição. |
| `Unsuccessful delivery attempt` | Tentativa de entrega falhada (ver abaixo). Quando surge no mesmo segundo que `Item address re-labelled`, regista um redirecionamento: o volume continua viagem e o Peek mostra «Em trânsito», não «Tentativa de entrega». |
| `Notification - pick-up invitation printed`, `Electronic notification (SMS) - pick-up invitation sent to recipient` | Pronta para levantamento: a estação imprimiu o aviso ou enviou-o por SMS. |
| `Item arrived at pick-up point` | Registo de um correio estrangeiro: aguarda num ponto de recolha no estrangeiro (indica o país, não o ponto). |
| `Item delivered` | Entregue, inclusive quando o correio do país de destino entrega um envio partido da Letónia. |

`Item returned from Customs` assusta muitas vezes, mas significa apenas que o volume voltou do controlo aduaneiro e segue viagem, não que regressa ao remetente. `Item awaiting next planned cycle` indica que aguarda a expedição seguinte no centro de permuta. Encomendas de lojas que passam por um ponto de trânsito apresentam também estados próprios (`Parcel notify received`, `Parcel data received`, `Parcel has arrived at transit location`, `Parcel sent to destination country`, `Parcel has arrived in last mile`, `Parcel has gone thought last mile customs`). Para direitos e taxas da UE fora do espaço comunitário, consulta [encomendas retidas na alfândega](guide:customs).

Se o rastreador devolver `No information received on the tracking of the postal item. Please, try again!`, confirma o número ou aguarda: o volume pode ainda não ter sido lido, e envios do estrangeiro só surgem depois de o correio de origem enviar os dados. `Postal item number entered incorrectly. Please check it and enter it again.` aponta erro de escrita. Se um número certo continuar desconhecido durante vários dias, confirma com o remetente se usou um serviço rastreado: envios Simple e Economy nunca aparecem.

## Cacifos, estafeta e entregas falhadas

**Cacifos de encomendas (pakomāti).** Quando o volume chega ao cacifo recebes um SMS com a morada, o prazo e um código de seis dígitos (e e-mail, se o remetente indicou o teu endereço). Fica guardado **7 dias de calendário**; se continuar lá ao terceiro dia, o código é reenviado e recebes um lembrete antes do fim do prazo. Passados sete dias, o envio é dado como não entregue e devolvido ao remetente — exceto se tiver um prazo total de guarda mais longo, caso em que passa para o centro de atendimento mais próximo durante **30 dias de calendário**. Se o código não chegou, pede ao remetente que contacte a Latvijas Pasts: o número de telefone só é corrigido a pedido do remetente. Os cacifos aceitam até 30 kg e 38 × 38 × 58 cm (até 60 cm de comprimento em cacifos exteriores).

**Cacifos Unisend.** Uma encomenda enviada da Letónia para um cacifo Unisend na Lituânia ou Estónia também aguarda 7 dias de calendário, com código de seis dígitos enviado por SMS e e-mail (para números letões, lituanos ou estonianos) e reenviado após 48 horas. Se ninguém a levantar, regressa diretamente ao remetente.

**Entrega por estafeta.** A Latvijas Pasts indica até três tentativas de entrega. Se não estiveres e não redirecionares o envio, o estafeta deixa aviso e volta a tentar; se continuar sem entrega, fica em guarda e podes levantá-lo nos Correios Centrais ou contactar o apoio ao cliente para outra opção (o prazo de guarda é definido pelo remetente). Num Express Mail (EMS) falhado fica um aviso «You have received a shipment» na caixa de correio: liga para o número indicado com o código do envio à mão.

**Estação postal ou Customer Centre.** Recebes um SMS ou aviso em papel com o posto exato. Leva documento de identificação válido; a carta de condução serve para envios registados ou segurados até 150 €. Podes levantar sem o aviso se souberes o número de envio, ou ligar para o número no aviso para pedir entrega ao domicílio pagando a taxa no ato da entrega. Outra pessoa pode levantar com a tua autorização e respetivo passaporte ou cartão de cidadão. Em registados ou segurados segue segundo aviso aos 5 dias, e devolução ao remetente após 30 dias do primeiro aviso. Se a etiqueta de uma loja estrangeira só tiver parte do teu nome, leva documento de identificação e comprovativo da compra.

**Redirecionamento.** Se o remetente deu o teu telemóvel ou subscreveste notificações SMS, a mensagem traz um link para redirecionar o volume para um cacifo (0,00 €, IVA incluído), um centro de atendimento (1,89 €, IVA incluído), outra morada por estafeta (4,99 €, IVA incluído) ou o serviço gratuito «Postman at home» onde exista. Não é possível redirecionar envios já dentro de um cacifo ou sob controlo aduaneiro, registados ou segurados com prova de entrega para cacifo, envios à cobrança maiores que 38 × 38 × 58 cm para cacifo ou envios sem número de telefone. Ao ativar os avisos SMS podes também escolher um cacifo favorito.

## Encomenda Latvijas Pasts perdida, danificada ou atrasada

Em cacifos, só o remetente pode apresentar reclamação por perda ou dano (no prazo de 6 meses segundo as condições dos cacifos), pelo que em compras online deves contactar a loja. O pedido indica conteúdo e valor com comprovativo (fatura, recibo ou pagamento bancário) e fotografias dos danos; a Latvijas Pasts responde no prazo de um mês e o remetente pode ceder a indemnização ao destinatário.

Num envio registado ou segurado entregue por estafeta, verifica o conteúdo à frente do estafeta para assinar um auto. Se abrires sozinho e encontrares danos, tira fotografias e pede substituição ou reembolso ao remetente; a Latvijas Pasts refere que a indemnização deixa então de ser responsabilidade sua.

Se um envio para o estrangeiro não chegar, o remetente pode pedir pesquisa internacional: é paga antes de um mês e gratuita passados 30 dias sobre o envio.

Seguimento parado ou retido na alfândega? Consulta [seguimento sem atualizações](guide:tracking-not-updating) e [encomendas retidas na alfândega](guide:customs).

## Contacto e apoio ao cliente Latvijas Pasts

Canais publicados em pasts.lv:

- **Telefone:** +371 27008001 (também +371 67008001 nas condições dos cacifos).
- **E-mail:** info@pasts.lv.
- **Horário:** segunda a sexta das 8:00 às 19:00, sábado das 9:00 às 17:00, encerrado ao domingo.
- **Comunicar um problema:** formulário em [mans.pasts.lv/complaint](https://mans.pasts.lv/complaint), tratado no horário do apoio ao cliente.

> A Latvijas Pasts alerta para SMS e e-mails falsos a pedir confirmação de morada ou pagamento de alfândega em sites falsificados, bem como falsas vendas de «encomendas não reclamadas». Os seus e-mails terminam sempre em @pasts.lv, o site oficial é pasts.lv e nunca pede dados bancários ou de cartão por SMS ou e-mail nem confirmações Smart-ID.

## Seguir Latvijas Pasts com o Peek

O [Peek](/) associa imediatamente à Latvijas Pasts qualquer número postal que comece por C, E, L ou R e termine em `LV` com dígito de controlo válido. Para outros códigos válidos terminados em `LV`, como `VV482917367LV`, consulta em simultâneo a Latvijas Pasts e a [Chronopost](carrier:chronopost) e mostra «Latvijas Pasts tem esta encomenda.» quando o encontra, ou «Ainda não encontrada em … O Peek continua a verificar depois de adicionares.». Com dígito de controlo errado, a Latvijas Pasts não é consultada e escolhê-la à mão não resolve (o Peek indica que «não emite números como este»): confirma o código com o remetente. Um link de mans.pasts.lv/track com o número escolhe sempre Latvijas Pasts, e procurar «Latvian Post» no seletor também a encontra. Enquanto a Latvijas Pasts não conhecer o número, surge «À espera de Latvijas Pasts»; se dois dias depois continuar sem resposta, o Peek apresenta «Ao fim de dois dias, a transportadora ainda não reconhece este número. Verifica-o, escolhe outra transportadora ou elimina a encomenda.», passando a verificar diariamente e, após uma semana, semanalmente.

Uma encomenda vinda do estrangeiro é seguida primeiro no correio de origem. Quando esse correio indica a Letónia como destino (ou não indica destino e estás na Letónia), o Peek consulta também a Latvijas Pasts com o mesmo número e muda para ela assim que houver um registo recente, mantendo o histórico anterior. Para a acompanhar logo na Latvijas Pasts, escolhe «Latvijas Pasts» em «Alterar» ao adicionar ou «Alterar transportadora» numa encomenda já guardada.

As horas da Latvijas Pasts não trazem fuso horário: o Peek lê-as na hora local do local do registo (hora da Letónia para postos letões) e mostra o histórico nas expressões inglesas da Latvijas Pasts, incluindo `Item delivered`. Pode mostrar o posto onde o volume aguarda levantamento, mas não traz data estimada de chegada nem peso.

O Peek verifica até de 10 em 10 minutos enquanto a encomenda está aberta na app ou no dia da entrega com notificações ativas; no dia em que a Latvijas Pasts coloca o volume em distribuição, a partir das 6:00 hora local (hora letã para postos letões), verifica de 2 em 2 minutos enquanto o rastreador próprio da Latvijas Pasts responder. Caso contrário, verifica de hora a hora. Depois de 12 horas em «Etiqueta criada» ou «Aceite pela transportadora», ou dois dias sem novidades, verifica de hora a hora mesmo com a encomenda aberta, e de 6 em 6 horas após sete dias em «Etiqueta criada». Se o rastreador da Latvijas Pasts não responder e o Peek tiver de recorrer a serviços de seguimento, verifica de hora a hora, de 3 em 3 horas após um dia sem novo registo e, no dia da distribuição, até de 15 em 15 minutos enquanto a encomenda estiver aberta ou com notificações ativas. Quando outra transportadora assume a entrega, o Peek segue o ritmo dela.

Se passarem quatro dias sem um novo registo em «Etiqueta criada», «Aceite pela transportadora» ou «Em trânsito», o Peek assinala-o com o dia do último registo, por exemplo «Sem atualizações desde seg 12 out». Aparece então a nota «É habitual uma encomenda ficar sem novidades em voos longos, na alfândega ou ao fim de semana. O Peek continua a perguntar a Latvijas Pasts.» junto da ligação «Pede ajuda a Latvijas Pasts». Passadas mais de três semanas sem novidades, o Peek mostra: «Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, contacta o vendedor. O Peek continua a perguntar a Latvijas Pasts.»

Nas notificações podes escolher «Todos os registos», «Passos importantes» ou «Só a entrega».

## Perguntas frequentes sobre a Latvijas Pasts

### O que é a Latvijas Pasts?

É o operador postal nacional estatal da Letónia, com serviços postais, logísticos e financeiros na Letónia e no estrangeiro em pasts.lv.

### A Unisend é a Latvijas Pasts?

Não. A Unisend é a marca de encomendas da Lietuvos paštas, os correios da Lituânia. As duas empresas operam uma rede conjunta de cacifos, permitindo enviar da Letónia para cacifos Unisend na Lituânia e na Estónia.

### Posso seguir um envio internacional da Latvijas Pasts?

Sim, se tiver sido expedido com seguimento. mans.pasts.lv/track apresenta também os registos dos correios estrangeiros indicando o país em vez de um posto, e inclui o botão «Pārrobežu izsekošana» para um rastreador externo. No destino, a entrega cabe aos correios locais.

### O correio normal vindo da Letónia tem número de seguimento?

Nem sempre. Os envios Simple e Economy não são registados e não podem ser seguidos; os registados, rastreáveis, encomendas e EMS têm seguimento.

:::sources
- [Latvijas Pasts: Customer support](https://pasts.lv/en/client-support) – letras por serviço, sem indemnização Simple, pesquisa internacional, horário, telefone
- [Latvijas Pasts: Contacts](https://pasts.lv/en/about-us/contacts) – telefone, horário
- [Latvijas Pasts: Receive a parcel](https://pasts.lv/en/private-person/receive-parcel) – formas de receção, autorização
- [Latvijas Pasts: Receive by courier](https://pasts.lv/en/private-person/receive-parcel/receive-by-courier) – SMS do estafeta, três tentativas, guarda, Correios Centrais, Postman at home
- [Latvijas Pasts: Receive item in a parcel locker](https://pasts.lv/en/useful/receiving-items-private/receive-in-parcel-locker) – SMS de cacifo, sete dias, lembrete, 30 kg e 38 × 38 × 58 cm, cacifo favorito
- [Latvijas Pasts: Delivery to address](https://pasts.lv/en/useful/receiving-items-private/delivery-to-address) – segundo aviso aos 5 dias, devolução aos 30 dias, aviso EMS, nome incompleto
- [Latvijas Pasts: Receive at a Customer Centre](https://pasts.lv/en/private-person/receive-parcel/receive-at-customer-centre) – avisos, identificação, carta de condução até 150 €, levantamento sem aviso, entrega ao domicílio paga
- [Latvijas Pasts: Shipment redirection](https://pasts.lv/en/services/private-person/receiving/redirection-of-shipments) – link de redirecionamento, opções, preços com IVA, exclusões
- [Latvijas Pasts: Lost or damaged parcel locker shipments](https://pasts.lv/en/services/private-person/shipment-management/lost-or-damaged-parcel-locker-shipments) – reclamação pelo remetente, comprovativos, análise num mês
- [Latvijas Pasts: Damaged shipment](https://pasts.lv/en/useful/faulty-shipment) – verificação perante o estafeta, reclamação ao remetente
- [Latvijas Pasts: Pakomātu pakalpojumu noteikumi](https://pasts.lv/media/789/download/Pakomatu_noteikumi.pdf) – código de seis dígitos, e-mail, reenvio ao 3.º dia, devolução após 7 dias ou 30 dias em posto, 6 meses para reclamar, cacifos Unisend, info@pasts.lv e +371 67008001
- [Latvijas Pasts: Pasargā sevi no krāpniekiem](https://pasts.lv/pasarga-sevi-no-krapniekiem) – aviso sobre SMS e e-mails fraudulentos
- [Latvijas Pasts: Mans pasts, track shipment](https://mans.pasts.lv/track?locale=en-GB) – rastreador, seletor de idioma, seguimento internacional
- [Latvijas Pasts: Complaint form](https://mans.pasts.lv/complaint) – formulário de reclamação
- [Latvijas Pasts: About us](https://pasts.lv/en/about-us/par-pastu) – operador postal estatal, serviços
- [Latvijas Pasts: Joint parcel locker network with UNISEND](https://pasts.lv/en/news/latvijas-pasts-and-unisend-launch-their-joint-parcel-locker-network) – Unisend como marca da Lietuvos paštas, rede conjunta
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – o que o Peek lê e mostra, textos de estados e etapas, números recusados, passagem do correio estrangeiro
:::
