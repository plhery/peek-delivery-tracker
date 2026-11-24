---
title: Rastreio A Tu Hora Express: códigos, código postal e ajuda
description: Rastreia uma encomenda A Tu Hora Express em Espanha: códigos de 10, 16 e 24 dígitos, código postal de 5 dígitos, estados espanhóis e contactos.
slug: rastreio-a-tu-hora-express
published: 2026-11-24
updated: 2026-11-24
---

A A Tu Hora Express (*A TU HORA EXPRESS, S.L.*) é uma transportadora espanhola de última milha com sede em San Fernando de Henares (Madrid) e delegações em toda a Espanha continental, incluindo Madrid, Barcelona, Valência, Sevilha, Málaga, Alicante, Múrcia, Almería, Cádis, Castellón, Córdova, Girona, Granada, Huelva, Jaén, Lérida e Tarragona. Entrega encomendas de comércio eletrónico e B2B em serviços como *Express 24h*, *Economy 48h*, *Masivo 72h*, *Reverse Logistics* (*Logística Inversa*) e *B2B Premium*. Podes consultar uma encomenda no [portal de rastreio da A Tu Hora Express](https://mydelivery.atuhoraexpress.com/#/login-form-cli) ou colar o número de rastreio na caixa acima.

## Números de rastreio A Tu Hora Express e código postal de entrega

Procura o número de serviço ou de rastreio no e-mail de envio da loja ou na página da encomenda. Os envios da A Tu Hora Express surgem em três comprimentos só com dígitos:

| Formato (inventado) | O que é |
| --- | --- |
| `0000004829173640` | Número de serviço A Tu Hora Express de 16 dígitos (`000000` + 10 dígitos). Requer o teu código postal espanhol de entrega de 5 dígitos no rastreio. |
| `4829173640` | Código curto de 10 dígitos (completado com seis zeros iniciais para formar o número de serviço de 16 dígitos). Também requer o código postal de entrega de 5 dígitos. |
| `000000482917364000128013` | Código de barras completo de 24 dígitos: número de serviço de 16 dígitos + `001` + código postal espanhol de entrega de 5 dígitos (aqui `28013`). Partilha o formato de 24 dígitos com [Ciblex](carrier:ciblex), [InPost](carrier:inpost), [bpost](carrier:bpost), [Austrian Post](carrier:austrian-post) e [Express One](carrier:express-one). |

**Porque importa o código postal.** Em [mydelivery.atuhoraexpress.com](https://mydelivery.atuhoraexpress.com/#/login-form-cli), os números de 10 e 16 dígitos só devolvem os dados da encomenda quando acompanhados pelo **código postal espanhol de entrega de 5 dígitos**. O código de barras de 24 dígitos já incorpora esse código postal nos últimos cinco dígitos, pelo que funciona sem o introduzires à parte. Para outros padrões espanhóis e internacionais, vê [formatos de números de rastreio](guide:tracking-number-formats).

## O que significam os estados de rastreio da A Tu Hora Express

A A Tu Hora Express apresenta cada registo em espanhol, com um código de estado (`cod`) e a hora no relógio de Madrid:

| A A Tu Hora Express indica (código) | O que significa para ti | O Peek mostra |
| --- | --- | --- |
| `Documentado` (`0`) | O remetente registou o envio eletronicamente antes da entrega no armazém. | «Etiqueta criada» |
| `En tránsito` (`1`) | A circular entre delegações na rede espanhola da A Tu Hora Express. | «Em trânsito» |
| `En agencia distribución` (`11`) | Chegou à delegação local espanhola que serve o teu código postal. | «Em trânsito» |
| `En reparto` (`2`) / `En realización` | Com o estafeta local para entrega hoje. | «Em distribuição» |
| `Incidencia - Pendiente nuevo reparto` (`3`), `Ausente`, `Dirección incorrecta o incompleta`, `Rechazado` | A entrega não pôde ser concluída (destinatário ausente, morada incompleta, recusa ou nova tentativa em fila). | «Tentativa de entrega» |
| `Incidencia - Falta parcial` (`3`), `Incidencia - No cumplimiento agencia` / `Incumplimiento de la agencia` | Incidente operacional, como volume em falta num envio com vários volumes ou falha de entrega da delegação. | «Problema reportado» |
| `Pendiente de realizar` (`5`) | Surge junto de um incidente com a mesma data e hora para assinalar que há nova entrega pendente; a linha do incidente define a etapa. | *(mantém a etapa do incidente)* |
| `Realizado con éxito` (`4`) / `Entregado con éxito` | Entregue pela A Tu Hora Express. | «Entregue» |
| `Devuelto` | Em devolução ao remetente. | «Devolvida ao remetente» |

## Serviços de entrega e dados publicados no site oficial

Em [atuhoraexpress.com/home/](https://atuhoraexpress.com/home/) e [atuhoraexpress.com](https://atuhoraexpress.com/), a A Tu Hora Express indica cinco serviços principais — *Express 24h*, *Economy 48h*, *Masivo 72h*, *Reverse Logistics* e *B2B Premium* — na sua rede espanhola de delegações, com rastreio através de [atuhoraexpress.com/tracking/](https://atuhoraexpress.com/tracking/) e [mydelivery.atuhoraexpress.com](https://mydelivery.atuhoraexpress.com/#/login-form-cli).

O site da A Tu Hora Express não publica número de tentativas de entrega, limites de peso ou dimensões nem prazo de guarda. Se a tua encomenda veio de fora da União Europeia, paga 3 € de direito aduaneiro por artigo quando vale 150 € ou menos e a taxa de tratamento da União de 2 € por artigo, salvo se a loja já os tiver cobrado no pagamento; vê [encomendas retidas na alfândega](guide:customs).

## Encomenda A Tu Hora Express atrasada, bloqueada ou desaparecida

Como o contrato de transporte pertence à loja online, contacta primeiro o vendedor se a encomenda ficar repetidamente em `Incidencia`, mostrar erro de morada (`Dirección incorrecta o incompleta`) ou ultrapassar o dia previsto de entrega.

- **Incidentes repetidos ou `Pendiente nuevo reparto`:** confirma rua, andar/porta e telefone junto do vendedor e envia e-mail para `info@atuhoraexpress.com` com o código de 16 ou 24 dígitos e o código postal de entrega.
- **Marcada como `Realizado con éxito` mas não recebida:** confirma primeiro com familiares, vizinhos e a portaria (*portería*) do prédio, depois contacta de imediato a loja e a A Tu Hora Express.
- Para os passos seguintes, consulta [por que razão o rastreio deixa de atualizar](guide:tracking-not-updating), [entregue mas não recebida](guide:delivered-not-received), [encomenda danificada](guide:damaged-parcel), [encomenda perdida](guide:lost-parcel) e [devolvida ao remetente](guide:returned-to-sender).

## Apoio ao cliente e contactos A Tu Hora Express

Contactos oficiais publicados em [atuhoraexpress.com/home/](https://atuhoraexpress.com/home/), [atuhoraexpress.com/aviso-legal/](https://atuhoraexpress.com/aviso-legal/) e [atuhoraexpress.com/ayudanos-a-mejorar/](https://atuhoraexpress.com/ayudanos-a-mejorar/):

- **Telefone:** `911 631 356` (Espanha).
- **E-mail de apoio:** `info@atuhoraexpress.com`.
- **Formulário de contacto:** [Ayúdanos a mejorar](https://atuhoraexpress.com/ayudanos-a-mejorar/).
- **E-mail de proteção de dados:** `protecciondedatos@atuhoraexpress.com`.
- **Sede em Madrid:** Calle Artesanos 10, 28830 San Fernando de Henares (Madrid), Espanha (morada legal registada da *A TU HORA EXPRESS, S.L.*, NIF `B19300748`: C/ Espliego 16, 19208 Alovera, Guadalajara).

> Desconfia de SMS ou mensagens de WhatsApp a pedir um pequeno pagamento com cartão para reagendar uma entrega da A Tu Hora Express. Verifica a encomenda diretamente em [mydelivery.atuhoraexpress.com](https://mydelivery.atuhoraexpress.com/#/login-form-cli) ou através de `info@atuhoraexpress.com`. Vê [SMS fraudulentas de entregas](guide:delivery-scam-texts).

## Rastrear uma encomenda A Tu Hora Express com o Peek

[O Peek](/) reconhece automaticamente com elevada confiança qualquer código de 16 dígitos que comece por seis zeros (`0000004829173640`, dígitos inventados) como A Tu Hora Express. Um código de barras de 24 dígitos (`000000482917364000128013`) corresponde a várias transportadoras: o Peek pergunta à A Tu Hora Express (usando o código postal incluído no código de barras), [Austrian Post](carrier:austrian-post), [InPost](carrier:inpost) e [Ciblex](carrier:ciblex) se o conhecem, e à [Express One](carrier:express-one) só se nenhuma o reconhecer, mostrando «A Tu Hora Express tem esta encomenda.» quando a A Tu Hora Express responde. Para um código de 10 dígitos (`4829173640`), toca em «Alterar» ao adicionar ou em «Alterar transportadora» no ecrã da encomenda e seleciona A Tu Hora Express (pesquisar «A Tu Hora» também a encontra).

Para códigos de 10 e 16 dígitos, o Peek disponibiliza um campo opcional «Código postal de entrega» com a indicação «A Tu Hora Express só mostra a encomenda com o código postal de entrega.». Enquanto não o introduzires, o ecrã da encomenda oferece «Adicionar código postal» e o Peek acompanha o envio através de serviços de rastreio, consultando uma vez por dia após um dia sem resposta. Introduzir o código postal faz o Peek verificar logo. Um código de barras de 24 dígitos traz o código postal nos últimos cinco dígitos, pelo que não precisa dele.

Quando a A Tu Hora Express responde, o Peek mostra a etapa («Etiqueta criada», «Em trânsito», «Em distribuição», «Tentativa de entrega», «Problema reportado», «Devolvida ao remetente», «Entregue»), a descrição em espanhol com a localidade, o dia previsto de entrega enquanto a A Tu Hora Express o mostrar num envio aberto, e o peso. Os códigos de estado definem a etapa; o Peek não os exibe. Os registos são datados na hora de Madrid e mostrados na hora do teu dispositivo. O Peek omite as coordenadas GPS junto aos registos, a barra de progresso, a data de partida, a referência de cliente, os nomes de remetente e destinatário, a morada de destino, o número de volumes, o volume cúbico, as observações, a prova de entrega e as opções de entrega; o rastreio da A Tu Hora Express não mostra ponto de levantamento nem nome do serviço.

Enquanto tiveres a encomenda aberta no Peek, ou no dia da entrega com as notificações ativadas, o Peek consulta até de 10 em 10 minutos; no dia em que a A Tu Hora Express mostra `En reparto` ou `En realización`, de 2 em 2 minutos a partir das 6h na hora de Madrid, enquanto o rastreio da A Tu Hora Express responder. Caso contrário, verifica de hora a hora. Após 12 horas apenas em «Etiqueta criada» ou «Aceite pela transportadora», ou dois dias sem novidades, passa a verificar de hora a hora seja qual for o modo de acompanhamento, e de 6 em 6 horas após sete dias em «Etiqueta criada». Quando o rastreio da A Tu Hora Express não responde e o Peek acompanha a encomenda apenas através de serviços de rastreio, verifica de hora a hora, de 3 em 3 horas após um dia sem registos e até de 15 em 15 minutos no dia em que a encomenda está em distribuição, enquanto tiveres a encomenda aberta ou as notificações ativadas.

Quando o registo mais recente tem quatro dias e a encomenda continua em «Etiqueta criada», «Aceite pela transportadora» ou «Em trânsito», o Peek assinala-a com o dia desse registo, como «Sem atualizações desde seg 12 out». A página da encomenda mostra então «É habitual uma encomenda ficar sem novidades em voos longos, na alfândega ou ao fim de semana. O Peek continua a perguntar a A Tu Hora Express.» Após três semanas sem novos registos, mostra «Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, contacta o vendedor. O Peek continua a perguntar a A Tu Hora Express.»

Podes escolher alertas para «Todos os registos», «Passos importantes» ou «Só a entrega». O Peek acompanha apenas as atualizações de rastreio e não pode reagendar entregas, alterar a tua morada nem contactar a A Tu Hora Express em teu nome.

## Perguntas frequentes sobre o rastreio A Tu Hora Express

### Porque é que a A Tu Hora Express pede o meu código postal de entrega?

A A Tu Hora Express só mostra um código de 10 ou 16 dígitos com o respetivo código postal espanhol de entrega de 5 dígitos, tanto em [mydelivery.atuhoraexpress.com](https://mydelivery.atuhoraexpress.com/#/login-form-cli) como no Peek, onde o campo é opcional mas sem o qual a A Tu Hora Express não responde. Se a loja te deu um código de 24 dígitos, os últimos cinco dígitos já incluem o código postal.

### O que significa `Realizado con éxito` no rastreio da A Tu Hora Express?

`Realizado con éxito` (código de estado `4`) significa que a A Tu Hora Express registou a encomenda como entregue. Se não a recebeste, verifica com familiares, vizinhos e a portaria, depois contacta a loja e `info@atuhoraexpress.com`.

### O que significa `Incidencia - Pendiente nuevo reparto`?

Significa que uma tentativa de entrega não pôde ser concluída e a encomenda está em fila para sair novamente para distribuição (muitas vezes junto de `Pendiente de realizar` na mesma hora). Se se repetir, confirma a morada e o telefone junto do vendedor.

### Como posso contactar a A Tu Hora Express em Espanha?

Liga para `911 631 356`, envia e-mail para `info@atuhoraexpress.com` ou submete o formulário [Ayúdanos a mejorar](https://atuhoraexpress.com/ayudanos-a-mejorar/) com o número de rastreio e o código postal de entrega de 5 dígitos.

:::sources
- [A Tu Hora Express: página inicial (espanhol)](https://atuhoraexpress.com/) – transportadora espanhola de última milha, delegações, sede em Madrid, `911 631 356` e `info@atuhoraexpress.com`
- [A Tu Hora Express: página inicial (inglês)](https://atuhoraexpress.com/home/) – Express 24h, Economy 48h, Masivo 72h, Reverse Logistics, B2B Premium e lista de delegações
- [A Tu Hora Express: página de rastreio](https://atuhoraexpress.com/tracking/) – ponto de entrada do rastreio no site
- [A Tu Hora Express: portal MyDelivery](https://mydelivery.atuhoraexpress.com/#/login-form-cli) – portal de consulta direta de envios
- [A Tu Hora Express: aviso legal](https://atuhoraexpress.com/aviso-legal/) – *A TU HORA EXPRESS, S.L.*, NIF `B19300748`, sede legal em Alovera (Guadalajara) e `protecciondedatos@atuhoraexpress.com`
- [A Tu Hora Express: formulário de feedback](https://atuhoraexpress.com/ayudanos-a-mejorar/) – formulário de contacto de clientes
- [Universal Parcel Scraper: a-tu-hora-express](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/a-tu-hora-express) – deteção de 10/16/24 dígitos, pedido opcional de código postal de 5 dígitos, códigos de estado espanhóis (`0`, `1`, `11`, `2`, `3`, `4`, `5`), hora local `Europe/Madrid`, data prevista de entrega, peso e campos omitidos
:::
