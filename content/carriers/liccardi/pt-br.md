---
title: Rastreamento Liccardi Express Courier: códigos LDV e ajuda
description: Rastreie encomendas da Liccardi Express Courier (F.lli Liccardi) na Itália: etiquetas 660 e códigos LDV, status de filiais e lockers, giacenza e suporte.
slug: liccardi-tracking
published: 2026-11-26
updated: 2026-11-26
---

A **Liccardi Express Courier** (*F.lli Liccardi S.r.l.*) é uma transportadora expressa nacional italiana sediada em Casandrino, perto de Nápoles ([liccarditrasporti.com](https://www.liccarditrasporti.com/)). Lançada em sua forma moderna em 1992 a partir de uma empresa familiar de transporte fundada trinta anos antes por Giovanni Liccardi, a companhia evoluiu do transporte de documentos bancários nos anos 1990 para a distribuição nacional B2C e de e-commerce, movimentando mais de 5,9 milhões de remessas por ano em mais de 20 filiais e centros italianos, incluindo Nápoles, Roma, Milão, Bolonha, Turim, Florença, Verona, Bari e Salerno. Você pode rastrear um envio pelo site da Liccardi ou colar seu código de etiqueta `660` de 13 dígitos ou a *lettera di vettura* (LDV) de 7 ou 8 dígitos na caixa acima.

## Códigos de rastreamento da Liccardi Express Courier (etiqueta `660` e LDV)

A Liccardi identifica cada remessa com duas referências numéricas — uma etiqueta de 13 dígitos iniciada por `66` (geralmente `660`) e um número de remessa mais curto de 7 ou 8 dígitos (*lettera di vettura* / LDV):

| Formato (inventado) | O que é |
| --- | --- |
| `6604829173605` | Código de barras de 13 dígitos da Liccardi (`660` + número de remessa de 8 dígitos + 2 dígitos verificadores/de volume). |
| `48291736` | Número de remessa de 7 ou 8 dígitos da Liccardi (*LDV* / *lettera di vettura*). |

Procure qualquer um desses dois códigos no e-mail de envio da loja ou no aviso de entrega da Liccardi; ambos apontam para a mesma remessa no sistema da transportadora. Veja [formatos de código de rastreamento](guide:tracking-number-formats).

## O que significam os status de rastreamento da Liccardi Express Courier

O rastreador da Liccardi publica códigos numéricos de ocorrência (*codici esito*) acompanhados de descrições em italiano das filiais. Em remessas com vários volumes (*colli*), a Liccardi registra eventos por volume; o Peek mantém a remessa ativa até que todos os volumes sejam entregues:

| O que a Liccardi exibe (código) | O que significa para você | O Peek mostra |
| --- | --- | --- |
| `DATI SPEDIZIONE TRASMESSI A LICCARDI` (`50`), `SERVIZIO PROGRAMMATO` (`58`), `RITIRO ASSEGNATO AD AUTISTA` (`36`), `IN RITIRO` (`92`) | O remetente transmitiu os dados da remessa ou uma coleta por motorista está agendada/em andamento antes da entrada no depósito. | "Etiqueta criada" |
| `SPEDIZIONE RITIRATA` (`37`), `COLLO PRESO IN CARICO` (`3`), `SPEDIZIONE PRESA IN CARICO` (`4`) | Encomenda coletada com o remetente e aceita na rede da Liccardi. | "Recebida pela transportadora" |
| `ARRIVATO IN FILIALE` (`47`), `IN TRANSITO` (`48`), `SPEDIZIONE PARTITA` (`91`), `ARRIVO SU FILIALE DI DESTINO` (`72`), `IN GESTIONE` (`49`), `GIACENZA SVINCOLATA` (`70`), `IN PRENOTAZIONE` (`33`) | Em deslocamento entre centros, chegada à filial de destino, agendamento de entrega ou liberação após retenção. | "Em trânsito" |
| `CARICO VETTURA` (`51`), `CARICO VETTURA CONFERMATO` (`95`), `IN CONSEGNA` (`52`) | Carregada no veículo local de entrega e em rota hoje. | "Saiu para entrega" |
| `CONSEGNATA AL PUDO` (`6`), `CHECK-IN AL PUDO` (`80`), `SPEDIZIONE DEPOSITATA NEL LOCKER` (`11`), `FERMO DEPOSITO` (`32`) | Disponível para retirada em ponto PUDO, armário de encomendas (locker) ou filial local da Liccardi (*fermo deposito*). | "Pronta para retirada" |
| `ASSENTE` (`28`), `INDIRIZZO ERRATO` (`40`), `CONSEGNA NON ESEGUITA` (`43`), `MANCA CONTRASSEGNO` (`44`), `INDIRIZZO NON TROVATO` (`45`), `CHIUSO` (`46`), `GIRO DI CONSEGNA NON ULTIMATO` (`67`) | Não foi possível concluir a entrega (destinatário ausente, erro de endereço, pagamento na entrega indisponível, local fechado ou rota não finalizada). | "Tentativa de entrega" |
| `IN GIACENZA` (`35`), `COLLO DANNEGGIATO` (`30`), `MERCE RESPINTA` (`42`), `SPEDIZIONE SMARRITA` (`54`), `COLLO DISGUIDATO` (`68`), `ORDINE SOSPESO: IN ATTESA DI ISTRUZIONI MITTENTE` (`76`), `DISCONOSCIMENTO FIRMA` (`103`), `CONTATTARE ASSISTENZA` (`107`) | Volume retido em depósito aguardando liberação do remetente (*giacenza*), danificado, recusado, mal encaminhado, extraviado ou precisando de suporte. | "Precisa de atenção" |
| `RIENTRO AL MITTENTE` (`31`), `IN CONSEGNA AL MITTENTE` (`53`), `RESO A MITTENTE` (`8`) | A caminho de volta ou devolvida ao remetente. | "Devolvida ao remetente" |
| `SPEDIZIONE CONSEGNATA` (`1`), `SPEDIZIONE CONSEGNATA A COMMITTENTE` (`5`), `SPEDIZIONE RITIRATA DA LOCKER DAL DESTINATARIO` (`12`) | Entregue ao destinatário ou retirada do locker. | "Entregue" |

Observe que a barra de progresso própria da Liccardi trata o depósito em locker (`11`) como entregue, enquanto o Peek mapeia eventos de PUDO, locker e *fermo deposito* para **"Pronta para retirada"** até o destinatário realmente recolher o pacote (`12`).

## Prazos de entrega, serviços e rede de filiais na Itália

De acordo com [Serviços da Liccardi](https://www.liccarditrasporti.com/servizi/), [A Empresa](https://www.liccarditrasporti.com/azienda/) e [Filiais](https://www.liccarditrasporti.com/filiali/):

- **Prazos de trânsito:** a Liccardi coleta pedidos dos lojistas em até 48 horas, pesa, mede e fotografa cada volume nos centros de triagem e anuncia entrega nacional em **24–48 horas** (cobrindo estatisticamente toda a sua área nacional em até **72 horas**, com localidades remotas — *località disagiate* — levando mais tempo e períodos de pico afetando os prazos habituais).
- **Opções de entrega:** expresso B2C padrão, entrega no andar (*consegne al piano*), entrega com horário marcado (*consegne tassative*), entrega no mesmo dia, pagamento na entrega (*contrassegno*), retirada em filial (*fermo deposito*), coletas de troca/reversas e seguro complementar opcional.
- **Rede de filiais:** mais de 20 centros operacionais e depósitos em toda a Itália, incluindo o hub central Casandrino/Nápoles (`NA1`), Roma (`RM1`, `RM3`), Milão (`MI1`), Bolonha (`BO1`), Turim (`TO2`), Florença (`FI1`), Verona (`VR1`), Bari (`BA1`), Salerno (`SA1`), Avellino (`AV1`), Frosinone (`FR1`), Latina (`LT1`), Módena (`MO1`), Parma (`PR1`), Foggia (`FG1`), Lecce (`LE2`), Matera (`MT1`), Potenza (`PZ1`), Cosenza (`CS1`), Catanzaro (`CZ1`) e Reggio Calabria (`RC1`). Quando outra transportadora assume um volume, o rastreador da Liccardi pode exibir o conhecimento (LDV) dessa parceira.

## Entrega não realizada, retenção em *giacenza* e reclamações

- **`IN GIACENZA` (`35`) ou entrega não realizada (`28`, `40`, `45`):** após entrega não realizada ou erro de endereço, a Liccardi coloca o envio em *giacenza* (retenção em depósito) e aguarda instruções de liberação (*svincolo giacenza*) do **remetente**. Contate primeiro a loja virtual para que ela envie a liberação, ou acesse o portal do destinatário em [Assistenza / Reclami Destinatari](https://www.liccarditrasporti.com/assistenza-reclami-destinatari/) para atualizar instruções de entrega, informar erro de endereço ou solicitar retirada na filial (*ritiro in filiale*).
- **Contestação de ocorrência (`Contestazione Esito`) ou extravio:** o guia de reclamações para destinatários da Liccardi permite reportar um status incorreto, registro de "destinatário ausente" quando você estava em casa ou pacote marcado como entregue sem recebimento, oferecendo formulários para download (*Modulo reclamo*, *Modulo conciliazione* e *Formulario CP*). Avise sempre o vendedor também. Veja [entregue, mas não recebida](guide:delivered-not-received), [encomenda danificada](guide:damaged-parcel), [encomenda perdida](guide:lost-parcel), [devolvida ao remetente](guide:returned-to-sender) e [por que o rastreamento para de atualizar](guide:tracking-not-updating).

## Atendimento ao cliente e contatos da Liccardi Express Courier

Contatos oficiais divulgados em [Liccardi Contatti](https://www.liccarditrasporti.com/contatti/) e [Assistenza / Reclami Destinatari](https://www.liccarditrasporti.com/assistenza-reclami-destinatari/):

- **Número gratuito na Itália (*Numero Verde*):** `800 029 764` (`800029764`).
- **Telefone:** `+39 081 5053642` (fax `081 5053071`).
- **E-mail de atendimento:** `assistenzaclienti@liccarditrasporti.com` (informações gerais: `info@liccarditrasporti.com`; PEC: `flliliccardi@legalmail.it`).
- **Portal de suporte e reclamações de destinatários:** [liccarditrasporti.com/assistenza-reclami-destinatari/](https://www.liccarditrasporti.com/assistenza-reclami-destinatari/).
- **Sede e hub central:** *F.lli Liccardi S.r.l.* (P.IVA `06588280633`), Via Paolo Borsellino 133 (sede legal 123), 80025 Casandrino (NA), Itália.

> Desconfie de mensagens SMS falsas cobrando um pequeno pagamento on-line para reagendar uma entrega na Itália. Confirme sempre qualquer aviso diretamente com a loja ou pelos canais oficiais da Liccardi. Veja [golpes de SMS de entrega](guide:delivery-scam-texts).

## Acompanhe uma encomenda da Liccardi Express Courier com o Peek

Ao digitar um código de barras `660` de 13 dígitos (`6604829173605`, inventado), o [Peek](/) consulta primeiro a Liccardi Express Courier em qualquer país exceto na Rússia (onde consulta primeiro a Ozon), exibindo "Liccardi Express Courier está com esta encomenda." assim que o rastreador da Liccardi confirma. O Peek também reconhece links colados de `my.liccarditrasporti.com/segui?ldv=`. Já uma LDV de 7 ou 8 dígitos (`48291736`, inventada) não identifica nenhuma transportadora sozinha: cole o link, ou toque em "Trocar" ao adicionar a encomenda (ou "Trocar transportadora" na página dela) e escolha **Liccardi Express Courier** (pesquisar "Liccardi Trasporti", "Liccardi Express" ou "F.lli Liccardi" também encontra).

O Peek lê o rastreador da Liccardi e mostra a etapa ("Etiqueta criada", "Recebida pela transportadora", "Em trânsito", "Saiu para entrega", "Pronta para retirada", "Tentativa de entrega", "Precisa de atenção", "Devolvida ao remetente", "Entregue"), o texto em italiano da Liccardi e a filial. Os códigos `codEsito` definem a etapa; nenhum aplicativo do Peek exibe esses códigos. O Peek interpreta os horários no fuso da Itália e os exibe no relógio do seu aparelho. Também mostra o dia estimado de entrega quando informado pela Liccardi (sem a faixa horária), o horário de entrega quando concluída e o status resumido antes da primeira leitura do volume. Quando a Liccardi informa o código de outra transportadora reconhecida pelo Peek, o app verifica se ela tem o pacote: a página passa a exibir "Entrega por" seguido do nome da parceira e lista os dois códigos. Em envios com vários volumes, o Peek mescla leituras repetidas da mesma filial ocorridas dentro de um minuto e mantém a remessa aberta até que todos os volumes sejam entregues. O Peek não exibe cidade ou código postal do destinatário, nomes de motoristas nem assinaturas de comprovante de entrega.

Enquanto você mantém a encomenda aberta no Peek, ou no dia da entrega com notificações ativas, o Peek verifica até a cada 10 minutos; no dia em que a Liccardi registra `CARICO VETTURA` (`51`), `CARICO VETTURA CONFERMATO` (`95`) ou `IN CONSEGNA` (`52`), consulta a cada 2 minutos a partir das 6h no horário italiano, enquanto o rastreador próprio da Liccardi responder. Caso contrário, verifica de hora em hora. Após 12 horas apenas em "Etiqueta criada" ou "Recebida pela transportadora", ou dois dias sem novidade, passa a consultar a cada hora de qualquer forma, e a cada 6 horas depois de sete dias em "Etiqueta criada". Quando o rastreador da Liccardi não responde e o Peek acompanha apenas por serviços de rastreamento, consulta de hora em hora, a cada 3 horas após um dia sem movimento e até a cada 15 minutos no dia da saída para entrega.

Quando a leitura mais recente tem quatro dias e o pacote segue em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito", o Peek sinaliza a data dessa leitura, como "Sem atualizações desde seg 12 out". A página do pacote então exibe "É comum uma encomenda ficar sem atualizações em voos longos, na alfândega ou no fim de semana. O Peek continua consultando Liccardi Express Courier.", com um link "Pedir ajuda a Liccardi Express Courier" para a página de suporte e reclamações de destinatários da Liccardi. Após três semanas sem novas leituras, mostra "Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, fale com o vendedor. O Peek continua consultando Liccardi Express Courier."

Você pode escolher alertas para "Cada movimentação", "Etapas importantes" ou "Só a entrega". O Peek apenas monitora atualizações de rastreamento e não pode liberar uma *giacenza*, alterar o endereço de entrega nem contatar a Liccardi Express Courier por você.

## Perguntas sobre o rastreamento da Liccardi Express Courier

### Como é o código de rastreamento da Liccardi?

A Liccardi usa um código de barras de 13 dígitos iniciado por `66` (geralmente `660`, por exemplo `6604829173605`, inventado) ou um número de remessa de 7 ou 8 dígitos (*LDV*, por exemplo `48291736`, inventado).

### O que significa `IN GIACENZA` no rastreamento da Liccardi?

`IN GIACENZA` (código `35`) indica que o pacote ficou retido em uma filial da Liccardi após entrega não realizada ou problema no endereço e aguarda instruções de liberação (*svincolo*) do remetente. Fale com o vendedor e envie dados atualizados de entrega por [Assistenza / Reclami Destinatari](https://www.liccarditrasporti.com/assistenza-reclami-destinatari/).

### Quanto tempo a Liccardi Express Courier leva para entregar na Itália?

A Liccardi anuncia entrega nacional em **24–48 horas** após a coleta (estatisticamente em até **72 horas** em toda a sua rede italiana), podendo levar mais tempo em áreas remotas (*località disagiate*) e períodos de pico.

### Como entrar em contato com o atendimento da Liccardi Express Courier?

Ligue para o número gratuito na Itália `800 029 764` ou para `+39 081 5053642`, escreva para `assistenzaclienti@liccarditrasporti.com` ou registre um pedido de suporte ou reclamação em [liccarditrasporti.com/assistenza-reclami-destinatari/](https://www.liccarditrasporti.com/assistenza-reclami-destinatari/).

:::sources
- [Liccardi Express Courier: Official Website](https://www.liccarditrasporti.com/) – *F.lli Liccardi S.r.l.*, mais de 20 filiais, mais de 5,9 milhões de remessas anuais, sede em Casandrino (NA) e contatos
- [Liccardi Express Courier: Azienda](https://www.liccarditrasporti.com/azienda/) – história da empresa desde 1992 (origem nos anos 1960), expansão B2C desde 2004 e associação à Unione Industriali
- [Liccardi Express Courier: Servizi](https://www.liccarditrasporti.com/servizi/) – entrega em 24/48 h (até 72 h), coleta em 48 h, *contrassegno*, *fermo deposito* e triagem automatizada com foto
- [Liccardi Express Courier: Filiali](https://www.liccarditrasporti.com/filiali/) – lista completa das mais de 20 filiais e centros italianos (NA1, RM1, MI1, BO1, TO2, FI1, VR1, BA1 etc.)
- [Liccardi Express Courier: Contatti](https://www.liccarditrasporti.com/contatti/) – `800 029 764`, `+39 081 5053642`, e-mails, PEC e endereço em Casandrino
- [Liccardi Express Courier: Assistenza / Reclami Destinatari](https://www.liccarditrasporti.com/assistenza-reclami-destinatari/) – categorias de suporte a destinatários, opções de contestação e formulários de reclamação/conciliação (URL de ajuda vinculada pelo Peek)
- [Universal Parcel Scraper: liccardi](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/liccardi) – regras de código `660`/LDV, regra de link `my.liccarditrasporti.com`, tabela completa de status `codEsito`, tratamento de vários volumes, dia estimado de entrega e extração de LDV parceira
:::
