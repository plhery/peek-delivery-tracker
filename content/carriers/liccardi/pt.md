---
title: Rastreio Liccardi Express Courier: códigos LDV e apoio
description: Acompanha uma encomenda Liccardi Express Courier (F.lli Liccardi) em Itália: códigos 660 e LDV, registos em agência e cacifo, giacenza e apoio.
slug: rastreio-liccardi
published: 2026-11-24
updated: 2026-11-24
---

A **Liccardi Express Courier** (*F.lli Liccardi S.r.l.*) é uma transportadora expresso nacional italiana sediada em Casandrino, perto de Nápoles ([liccarditrasporti.com](https://www.liccarditrasporti.com/)). Lançada na sua forma atual em 1992 a partir de uma empresa familiar de transportes fundada trinta anos antes por Giovanni Liccardi, evoluiu do transporte de documentos bancários nos anos 1990 para a distribuição nacional B2C e de comércio eletrónico, movimentando mais de 5,9 milhões de envios por ano através de mais de 20 agências e hubs italianos, incluindo Nápoles, Roma, Milão, Bolonha, Turim, Florença, Verona, Bari e Salerno. Podes acompanhar um envio no site da Liccardi ou colar o código de etiqueta `660` de 13 dígitos ou a *lettera di vettura* (LDV) de 7 a 8 dígitos na caixa acima.

## Números de rastreio Liccardi Express Courier (etiqueta `660` e LDV)

A Liccardi identifica cada envio com duas referências numéricas — um código de barras de volume de 13 dígitos iniciado por `66` (normalmente `660`) e um número de envio mais curto de 7 ou 8 dígitos (*lettera di vettura* / LDV):

| Formato (inventado) | O que é |
| --- | --- |
| `6604829173605` | Código de barras de encomenda Liccardi de 13 dígitos (`660` + número de envio de 8 dígitos + 2 dígitos de controlo/volume). |
| `48291736` | Número de envio Liccardi de 7 ou 8 dígitos (*LDV* / *lettera di vettura*). |

Consulta o e-mail de expedição da loja ou a notificação de entrega da Liccardi para obteres um destes códigos; ambos apontam para o mesmo envio no sistema da Liccardi. Consulta os [formatos de números de rastreio](guide:tracking-number-formats).

## O que significam os estados de rastreio da Liccardi Express Courier

O rastreio da Liccardi publica códigos numéricos de resultado (*codici esito*) acompanhados por descrições de agência em italiano. Em envios com vários volumes (*colli*), a Liccardi regista eventos por volume; o Peek mantém o envio ativo até todos os volumes serem entregues:

| A Liccardi indica (código) | O que significa para ti | O Peek mostra |
| --- | --- | --- |
| `DATI SPEDIZIONE TRASMESSI A LICCARDI` (`50`), `SERVIZIO PROGRAMMATO` (`58`), `RITIRO ASSEGNATO AD AUTISTA` (`36`), `IN RITIRO` (`92`) | O remetente transmitiu os dados do envio ou uma recolha por motorista está agendada ou em curso antes da entrada. | «Etiqueta criada» |
| `SPEDIZIONE RITIRATA` (`37`), `COLLO PRESO IN CARICO` (`3`), `SPEDIZIONE PRESA IN CARICO` (`4`) | Encomenda recolhida junto do remetente e aceite na rede da Liccardi. | «Aceite pela transportadora» |
| `ARRIVATO IN FILIALE` (`47`), `IN TRANSITO` (`48`), `SPEDIZIONE PARTITA` (`91`), `ARRIVO SU FILIALE DI DESTINO` (`72`), `IN GESTIONE` (`49`), `GIACENZA SVINCOLATA` (`70`), `IN PRENOTAZIONE` (`33`) | Em trânsito entre hubs, chegada à agência de destino, em marcação de entrega ou libertada de retenção. | «Em trânsito» |
| `CARICO VETTURA` (`51`), `CARICO VETTURA CONFERMATO` (`95`), `IN CONSEGNA` (`52`) | Carregada na carrinha local e em distribuição hoje. | «Em distribuição» |
| `CONSEGNATA AL PUDO` (`6`), `CHECK-IN AL PUDO` (`80`), `SPEDIZIONE DEPOSITATA NEL LOCKER` (`11`), `FERMO DEPOSITO` (`32`) | Pronta para levantamento num ponto PUDO, cacifo ou agência local Liccardi (*fermo deposito*). | «Pronta para levantamento» |
| `ASSENTE` (`28`), `INDIRIZZO ERRATO` (`40`), `CONSEGNA NON ESEGUITA` (`43`), `MANCA CONTRASSEGNO` (`44`), `INDIRIZZO NON TROVATO` (`45`), `CHIUSO` (`46`), `GIRO DI CONSEGNA NON ULTIMATO` (`67`) | A entrega não pôde ser concluída (destinatário ausente, erro de morada, contra-reembolso indisponível, fechado ou rota incompleta). | «Tentativa de entrega» |
| `IN GIACENZA` (`35`), `COLLO DANNEGGIATO` (`30`), `MERCE RESPINTA` (`42`), `SPEDIZIONE SMARRITA` (`54`), `COLLO DISGUIDATO` (`68`), `ORDINE SOSPESO: IN ATTESA DI ISTRUZIONI MITTENTE` (`76`), `DISCONOSCIMENTO FIRMA` (`103`), `CONTATTARE ASSISTENZA` (`107`) | Encomenda retida em armazém a aguardar instruções do remetente (*giacenza*), danificada, recusada, extraviada, mal encaminhada ou a necessitar de apoio. | «Problema reportado» |
| `RIENTRO AL MITTENTE` (`31`), `IN CONSEGNA AL MITTENTE` (`53`), `RESO A MITTENTE` (`8`) | Em devolução ou devolvida ao remetente. | «Devolvida ao remetente» |
| `SPEDIZIONE CONSEGNATA` (`1`), `SPEDIZIONE CONSEGNATA A COMMITTENTE` (`5`), `SPEDIZIONE RITIRATA DA LOCKER DAL DESTINATARIO` (`12`) | Entregue ao destinatário ou levantada do cacifo. | «Entregue» |

Nota que a barra de progresso da própria Liccardi considera os depósitos em cacifo (`11`) como entregues, ao passo que o Peek classifica registos PUDO, cacifo e *fermo deposito* como **«Pronta para levantamento»** até o destinatário levantar efetivamente a encomenda (`12`).

## Prazos de entrega, serviços e rede de agências em Itália

De acordo com [Serviços Liccardi](https://www.liccarditrasporti.com/servizi/), [Empresa](https://www.liccarditrasporti.com/azienda/) e [Agências](https://www.liccarditrasporti.com/filiali/):

- **Prazos de trânsito:** a Liccardi recolhe encomendas nos comerciantes em até 48 horas, pesa, mede e fotografa cada volume nos seus hubs de triagem e anuncia entrega nacional em **24–48 horas** (cobrindo estatisticamente toda a rede nacional em **72 horas**, podendo zonas remotas — *località disagiate* — e picos sazonais demorar mais).
- **Opções de entrega:** expresso B2C normal, entrega ao andar (*consegne al piano*), entrega com hora marcada (*consegne tassative*), entrega no próprio dia, pagamento na entrega (*contrassegno*), levantamento em agência (*fermo deposito*), recolhas de troca/devolução e seguro suplementar opcional.
- **Rede de agências:** mais de 20 hubs e delegações operacionais em Itália, incluindo o hub central Casandrino/Nápoles (`NA1`), Roma (`RM1`, `RM3`), Milão (`MI1`), Bolonha (`BO1`), Turim (`TO2`), Florença (`FI1`), Verona (`VR1`), Bari (`BA1`), Salerno (`SA1`), Avellino (`AV1`), Frosinone (`FR1`), Latina (`LT1`), Módena (`MO1`), Parma (`PR1`), Foggia (`FG1`), Lecce (`LE2`), Matera (`MT1`), Potenza (`PZ1`), Cosenza (`CS1`), Catanzaro (`CZ1`) e Reggio Calabria (`RC1`). Quando outra transportadora assume um envio, o rastreio da Liccardi pode apresentar a guia (LDV) dessa transportadora.

## Entrega falhada, retenções *giacenza* e reclamações

- **`IN GIACENZA` (`35`) ou entrega falhada (`28`, `40`, `45`):** após tentativa sem sucesso ou erro de morada, a Liccardi coloca o envio em *giacenza* (guarda em armazém) e aguarda instruções de desbloqueio (*svincolo giacenza*) do **remetente**. Contacta primeiro a loja online para emitir o desbloqueio, ou utiliza o portal de destinatários [Assistenza / Reclami Destinatari](https://www.liccarditrasporti.com/assistenza-reclami-destinatari/) para atualizar instruções de entrega, reportar erro de morada ou pedir levantamento em agência (*ritiro in filiale*).
- **Contestar um resultado (`Contestazione Esito`) ou encomenda desaparecida:** o guia de reclamações da Liccardi permite reportar um estado incorreto, uma marcação de ausente estando em casa ou uma encomenda dada como entregue mas não recebida, disponibilizando os formulários *Modulo reclamo*, *Modulo conciliazione* e *Formulario CP*. Informa sempre também o vendedor. Consulta [entregue mas não recebida](guide:delivered-not-received), [encomenda danificada](guide:damaged-parcel), [encomenda perdida](guide:lost-parcel), [devolvida ao remetente](guide:returned-to-sender) e [por que o rastreio deixa de atualizar](guide:tracking-not-updating).

## Apoio ao cliente e contactos Liccardi Express Courier

Contactos oficiais publicados em [Liccardi Contatti](https://www.liccarditrasporti.com/contatti/) e [Assistenza / Reclami Destinatari](https://www.liccarditrasporti.com/assistenza-reclami-destinatari/):

- **Linha gratuita (*Numero Verde* em Itália):** `800 029 764` (`800029764`).
- **Telefone:** `+39 081 5053642` (fax `081 5053071`).
- **E-mail de apoio ao cliente:** `assistenzaclienti@liccarditrasporti.com` (informações gerais: `info@liccarditrasporti.com`; PEC: `flliliccardi@legalmail.it`).
- **Portal de apoio e reclamações para destinatários:** [liccarditrasporti.com/assistenza-reclami-destinatari/](https://www.liccarditrasporti.com/assistenza-reclami-destinatari/).
- **Sede e hub central:** *F.lli Liccardi S.r.l.* (P.IVA `06588280633`), Via Paolo Borsellino 133 (sede legal 123), 80025 Casandrino (NA), Itália.

> Tem cuidado com SMS falsas a pedir um pequeno pagamento online para reagendar uma entrega expresso em Itália. Confirma sempre qualquer aviso diretamente com a loja ou através do site oficial e dos canais de apoio da Liccardi. Consulta [SMS falsas de entrega](guide:delivery-scam-texts).

## Seguir uma encomenda Liccardi Express Courier com o Peek

Quando introduzes um código de barras `660` de 13 dígitos (`6604829173605`, inventado), o [Peek](/) pergunta primeiro à Liccardi Express Courier em qualquer país exceto a Rússia, onde pergunta primeiro à Ozon, e mostra «Liccardi Express Courier tem esta encomenda.» assim que o rastreio da Liccardi confirma. O Peek reconhece ainda como Liccardi uma ligação colada `my.liccarditrasporti.com/segui?ldv=`. Uma LDV de 7 ou 8 dígitos (`48291736`, inventada) não identifica sozinha nenhuma transportadora: cola a ligação ou toca em «Alterar» ao adicionar a encomenda ou «Alterar transportadora» na respetiva página e escolhe **Liccardi Express Courier** (pesquisar «Liccardi Trasporti», «Liccardi Express» ou «F.lli Liccardi» também a encontra).

O Peek lê o rastreio da Liccardi e mostra a etapa («Etiqueta criada», «Aceite pela transportadora», «Em trânsito», «Em distribuição», «Pronta para levantamento», «Tentativa de entrega», «Problema reportado», «Devolvida ao remetente», «Entregue»), o texto italiano da Liccardi e a agência. Os códigos `codEsito` determinam a etapa, mas nenhuma app Peek os exibe. O Peek data os registos na hora italiana e mostra-os na hora do teu dispositivo. Mostra também o dia estimado de entrega quando a Liccardi o indica (sem a faixa horária), a hora de entrega depois de entregue e o estado resumido antes do primeiro registo do volume. Quando a Liccardi fornece a guia de outra transportadora e o Peek a reconhece, verifica se essa transportadora tem a encomenda: a ficha mostra então «Entrega:» e o nome da transportadora, listando ambos os números. Num envio com vários volumes, o Peek agrupa registos repetidos de agência ocorridos no espaço de um minuto e mantém o envio aberto até todos os volumes serem entregues. O Peek não mostra localidade nem código postal do destinatário, nomes dos motoristas nem assinaturas de comprovativo de entrega.

Enquanto tens a encomenda aberta no Peek, ou no dia da entrega com as notificações ativadas, o Peek consulta até de 10 em 10 minutos; no dia em que a Liccardi mostra `CARICO VETTURA` (`51`), `CARICO VETTURA CONFERMATO` (`95`) ou `IN CONSEGNA` (`52`), de 2 em 2 minutos a partir das 6h00 hora italiana enquanto o rastreio próprio da Liccardi responder. Caso contrário, o Peek verifica de hora a hora. Após 12 horas apenas em «Etiqueta criada» ou «Aceite pela transportadora», ou dois dias sem novidades, verifica de hora a hora independentemente da forma como acompanhas a encomenda, e de 6 em 6 horas após sete dias em «Etiqueta criada». Quando o rastreio da Liccardi não responde e o Peek acompanha a encomenda apenas através de serviços de rastreio, verifica de hora a hora, de 3 em 3 horas após um dia sem novidades e até de 15 em 15 minutos no dia da distribuição enquanto tens a encomenda aberta ou as notificações ativadas.

Quando o registo mais recente tem quatro dias e a encomenda continua em «Etiqueta criada», «Aceite pela transportadora» ou «Em trânsito», o Peek assinala-a com o dia desse registo, como «Sem atualizações desde seg., 12/10». A página da encomenda apresenta então «É habitual uma encomenda ficar sem novidades em voos longos, na alfândega ou ao fim de semana. O Peek continua a perguntar a Liccardi Express Courier.», com uma ligação «Pede ajuda a Liccardi Express Courier» para a página de assistência e reclamações da Liccardi. Passadas três semanas sem novos registos, mostra «Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, contacta o vendedor. O Peek continua a perguntar a Liccardi Express Courier.»

Podes escolher alertas para «Todos os registos», «Passos importantes» ou «Só a entrega». O Peek acompanha apenas atualizações de rastreio e não pode desbloquear uma *giacenza*, alterar uma morada de entrega nem contactar a Liccardi Express Courier por ti.

## Perguntas sobre o rastreio Liccardi Express Courier

### Como é um número de rastreio Liccardi?

A Liccardi utiliza um código de barras de etiqueta de 13 dígitos iniciado por `66` (normalmente `660`, por exemplo `6604829173605`, inventado) ou um número de envio de 7 a 8 dígitos (*LDV*, por exemplo `48291736`, inventado).

### O que significa `IN GIACENZA` no rastreio Liccardi?

`IN GIACENZA` (código `35`) significa que a encomenda está retida numa agência Liccardi após entrega falhada ou erro de morada e precisa de instruções de desbloqueio (*svincolo*) do remetente. Contacta o vendedor e envia dados de entrega atualizados através de [Assistenza / Reclami Destinatari](https://www.liccarditrasporti.com/assistenza-reclami-destinatari/).

### Quanto tempo demora a Liccardi Express Courier a entregar em Itália?

A Liccardi anuncia entrega nacional em **24–48 horas** após a recolha (estatisticamente em **72 horas** em toda a rede italiana), com possível prazo adicional para zonas remotas (*località disagiate*) e picos sazonais.

### Como contactar o apoio ao cliente da Liccardi Express Courier?

Liga para a linha gratuita italiana `800 029 764` ou `+39 081 5053642`, envia e-mail para `assistenzaclienti@liccarditrasporti.com` ou submete um pedido ou reclamação de destinatário em [liccarditrasporti.com/assistenza-reclami-destinatari/](https://www.liccarditrasporti.com/assistenza-reclami-destinatari/).

:::sources
- [Liccardi Express Courier: site oficial](https://www.liccarditrasporti.com/) – *F.lli Liccardi S.r.l.*, 20+ agências, mais de 5,9 milhões de envios anuais, sede em Casandrino (NA) e contactos
- [Liccardi Express Courier: Azienda](https://www.liccarditrasporti.com/azienda/) – história desde 1992 (origem nos anos 1960), expansão B2C desde 2004 e filiação Unione Industriali
- [Liccardi Express Courier: Servizi](https://www.liccarditrasporti.com/servizi/) – entrega 24/48 h (até 72 h), recolha em 48 h, *contrassegno*, *fermo deposito* e triagem fotográfica automatizada
- [Liccardi Express Courier: Filiali](https://www.liccarditrasporti.com/filiali/) – lista completa dos 20+ hubs e agências italianas (NA1, RM1, MI1, BO1, TO2, FI1, VR1, BA1, etc.)
- [Liccardi Express Courier: Contatti](https://www.liccarditrasporti.com/contatti/) – `800 029 764`, `+39 081 5053642`, e-mails, PEC e morada em Casandrino
- [Liccardi Express Courier: Assistenza / Reclami Destinatari](https://www.liccarditrasporti.com/assistenza-reclami-destinatari/) – categorias de apoio a destinatários, contestações e formulários de reclamação/conciliação (URL de ajuda ligado pelo Peek)
- [Universal Parcel Scraper: liccardi](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/liccardi) – regras `660`/LDV, regra de ligação `my.liccarditrasporti.com`, mapa completo `codEsito`, gestão multivolume, dia estimado de entrega e extração de LDV parceira
:::
