---
title: Rastreamento A Tu Hora Express: códigos, status e ajuda
description: Rastreie encomendas da A Tu Hora Express na Espanha: códigos de 10, 16 e 24 dígitos, por que o código postal de 5 dígitos é exigido, status e contatos.
slug: a-tu-hora-express-tracking
published: 2026-11-26
updated: 2026-11-26
---

A A Tu Hora Express (*A TU HORA EXPRESS, S.L.*) é uma transportadora espanhola de última milha sediada em San Fernando de Henares (Madri), com filiais em toda a Espanha continental, incluindo Madri, Barcelona, Valência, Sevilha, Málaga, Alicante, Múrcia, Almería, Cádiz, Castellón, Córdoba, Girona, Granada, Huelva, Jaén, Lleida e Tarragona. Entrega pedidos de varejo on-line e B2B pelos serviços *Express 24h*, *Economy 48h*, *Masivo 72h*, *Reverse Logistics* (*Logística Inversa*) e *B2B Premium*. Você pode consultar uma encomenda no [portal de rastreamento da A Tu Hora Express](https://mydelivery.atuhoraexpress.com/#/login-form-cli) ou colar o código na caixa acima.

## Códigos de rastreamento e código postal da A Tu Hora Express

Procure o número de serviço ou rastreamento no e-mail de envio da loja ou na página do pedido. Os envios da A Tu Hora Express usam três tamanhos exclusivamente numéricos:

| Formato (inventado) | O que é |
| --- | --- |
| `0000004829173640` | Código de serviço de 16 dígitos (`000000` + 10 dígitos). Exige o código postal espanhol de 5 dígitos no rastreador. |
| `4829173640` | Código curto de 10 dígitos (completado com seis zeros à esquerda para formar o código de 16 dígitos). Também exige o código postal de 5 dígitos. |
| `000000482917364000128013` | Código de barras completo de 24 dígitos: os 16 dígitos do serviço + `001` + o código postal espanhol de 5 dígitos (aqui `28013`). Compartilha o tamanho de 24 dígitos com [Ciblex](carrier:ciblex), [InPost](carrier:inpost), [bpost](carrier:bpost), [Austrian Post](carrier:austrian-post) e [Express One](carrier:express-one). |

**Por que o código postal importa.** Em [mydelivery.atuhoraexpress.com](https://mydelivery.atuhoraexpress.com/#/login-form-cli), códigos de 10 e 16 dígitos só devolvem detalhes quando acompanhados do **código postal espanhol de 5 dígitos**. O código de barras de 24 dígitos já embute esse código postal nos últimos cinco dígitos, dispensando digitação separada. Para outros padrões espanhóis e internacionais, veja [formatos de código de rastreamento](guide:tracking-number-formats).

## O que significam os status de rastreamento da A Tu Hora Express

A A Tu Hora Express registra cada movimentação em espanhol, com um código de status (`cod`) e horário de Madri:

| A Tu Hora Express diz (código) | O que significa para você | O Peek mostra |
| --- | --- | --- |
| `Documentado` (`0`) | O remetente cadastrou o envio eletronicamente antes da entrega ao depósito. | "Etiqueta criada" |
| `En tránsito` (`1`) | Circulando entre filiais da rede espanhola da A Tu Hora Express. | "Em trânsito" |
| `En agencia distribución` (`11`) | Chegou à filial de distribuição espanhola que atende o seu código postal. | "Em trânsito" |
| `En reparto` (`2`) / `En realización` | Saiu com o entregador local para entrega hoje. | "Saiu para entrega" |
| `Incidencia - Pendiente nuevo reparto` (`3`), `Ausente`, `Dirección incorrecta o incompleta`, `Rechazado` | A entrega não pôde ser concluída (destinatário ausente, endereço incorreto ou incompleto, recusa ou agendado para nova tentativa). | "Tentativa de entrega" |
| `Incidencia - Falta parcial` (`3`), `Incidencia - No cumplimiento agencia` / `Incumplimiento de la agencia` | Ocorrência operacional, como volume faltante em envio múltiplo ou descumprimento da filial. | "Precisa de atenção" |
| `Pendiente de realizar` (`5`) | Aparece no mesmo horário junto a uma ocorrência para sinalizar nova tentativa pendente; a etapa segue a linha da ocorrência. | *(mantém a etapa da ocorrência)* |
| `Realizado con éxito` (`4`) / `Entregado con éxito` | Entregue pela A Tu Hora Express. | "Entregue" |
| `Devuelto` | Em devolução ao remetente. | "Devolvida ao remetente" |

## Serviços de entrega e o que o site público informa

Em [atuhoraexpress.com/home/](https://atuhoraexpress.com/home/) e [atuhoraexpress.com](https://atuhoraexpress.com/), a A Tu Hora Express lista cinco serviços principais — *Express 24h*, *Economy 48h*, *Masivo 72h*, *Reverse Logistics* e *B2B Premium* — na sua rede espanhola, com rastreamento em [atuhoraexpress.com/tracking/](https://atuhoraexpress.com/tracking/) e [mydelivery.atuhoraexpress.com](https://mydelivery.atuhoraexpress.com/#/login-form-cli).

O site da A Tu Hora Express não publica número de tentativas de entrega, limites de peso ou dimensões nem prazo de guarda. Se o pedido veio de fora da União Europeia, incide imposto aduaneiro de €3 por item quando o valor for de até €150, além da taxa de manuseio da União de €2 por item, salvo cobrança pela loja no checkout; veja [encomendas retidas na alfândega](guide:customs).

## Encomenda A Tu Hora Express atrasada, parada ou extraviada

Como as lojas virtuais detêm o contrato de transporte com a A Tu Hora Express, fale primeiro com o varejista se o envio ficar preso repetidamente em `Incidencia`, apontar problema de endereço (`Dirección incorrecta o incompleta`) ou perder a data prevista.

- **Ocorrências repetidas ou `Pendiente nuevo reparto`:** confirme rua, andar/porta e telefone com a loja e envie um e-mail para `info@atuhoraexpress.com` informando o código de 16 ou 24 dígitos e o código postal de entrega.
- **Marcada como `Realizado con éxito`, mas não recebida:** confira primeiro com moradores, vizinhos e portaria (*portería*), depois acione a loja e a A Tu Hora Express imediatamente.
- Para orientações passo a passo, veja [por que o rastreamento para de atualizar](guide:tracking-not-updating), [entregue mas não recebida](guide:delivered-not-received), [encomenda danificada](guide:damaged-parcel), [encomenda extraviada](guide:lost-parcel) e [devolvida ao remetente](guide:returned-to-sender).

## Atendimento e contatos da A Tu Hora Express

Contatos oficiais publicados em [atuhoraexpress.com/home/](https://atuhoraexpress.com/home/), [atuhoraexpress.com/aviso-legal/](https://atuhoraexpress.com/aviso-legal/) e [atuhoraexpress.com/ayudanos-a-mejorar/](https://atuhoraexpress.com/ayudanos-a-mejorar/):

- **Telefone:** `911 631 356` (Espanha).
- **E-mail de atendimento:** `info@atuhoraexpress.com`.
- **Formulário de feedback:** [Ayúdanos a mejorar](https://atuhoraexpress.com/ayudanos-a-mejorar/).
- **Proteção de dados:** `protecciondedatos@atuhoraexpress.com`.
- **Sede em Madri:** Calle Artesanos 10, 28830 San Fernando de Henares (Madrid), Espanha (domicílio legal registrado de *A TU HORA EXPRESS, S.L.*, NIF `B19300748`: C/ Espliego 16, 19208 Alovera, Guadalajara).

> Desconfie de mensagens por SMS ou WhatsApp pedindo pequeno pagamento por cartão para reagendar uma entrega da A Tu Hora Express. Confira diretamente em [mydelivery.atuhoraexpress.com](https://mydelivery.atuhoraexpress.com/#/login-form-cli) ou por `info@atuhoraexpress.com`. Veja [golpes de entrega por SMS](guide:delivery-scam-texts).

## Acompanhe uma encomenda A Tu Hora Express com o Peek

O [Peek](/) reconhece automaticamente com alta confiança qualquer código de 16 dígitos iniciado por seis zeros (`0000004829173640`, dígitos inventados) como A Tu Hora Express. Códigos de 24 dígitos (`000000482917364000128013`) cabem em várias transportadoras: o Peek consulta A Tu Hora Express usando o código postal embutido no código de barras, além de [Austrian Post](carrier:austrian-post), [InPost](carrier:inpost) e [Ciblex](carrier:ciblex) (e [Express One](carrier:express-one) apenas se nenhuma delas responder), exibindo "A Tu Hora Express está com esta encomenda." quando a A Tu Hora Express reconhece o envio. Para códigos de 10 dígitos (`4829173640`), toque em "Trocar" ao adicionar ou "Trocar transportadora" na tela da encomenda e selecione A Tu Hora Express (buscar por "A Tu Hora" também encontra).

Para códigos de 10 e 16 dígitos, o Peek exibe o campo opcional "Código postal de entrega" com o aviso "A Tu Hora Express só mostra a encomenda com o código postal de entrega." Enquanto você não o preenche, a tela oferece "Adicionar código postal" e o Peek acompanha por serviços de rastreamento, verificando uma vez por dia após um dia sem resposta. Assim que você informa o código postal, o Peek refaz a consulta na hora. Códigos de 24 dígitos já trazem o código postal nos cinco dígitos finais e não precisam de preenchimento.

Quando a A Tu Hora Express responde, o Peek exibe a etapa ("Etiqueta criada", "Em trânsito", "Saiu para entrega", "Tentativa de entrega", "Precisa de atenção", "Devolvida ao remetente", "Entregue"), o texto da movimentação em espanhol com a cidade, a data prevista enquanto o envio estiver aberto e o peso. Os códigos numéricos definem a etapa, mas não aparecem na tela. Os horários seguem o fuso de Madri e são mostrados no relógio do seu aparelho. Coordenadas GPS das movimentações, barra de progresso, data de saída, referência do cliente, nomes de remetente e destinatário, endereço de destino, quantidade de volumes, cubagem, observações, comprovante e opções de entrega são omitidos; o rastreador da A Tu Hora Express não informa ponto de retirada nem nome do serviço.

Com a encomenda aberta no Peek, ou no dia da entrega com notificações ligadas, o Peek consulta até a cada 10 minutos; no dia em que a A Tu Hora Express exibe `En reparto` ou `En realización`, a cada 2 minutos a partir das 6h no horário de Madri enquanto o próprio rastreador da A Tu Hora Express responder. Nos demais casos, verifica a cada hora. Após 12 horas apenas em "Etiqueta criada" ou "Recebida pela transportadora", ou dois dias sem novidades, passa a consultar a cada hora de qualquer modo, e a cada 6 horas depois de sete dias em "Etiqueta criada". Quando o rastreador da A Tu Hora Express não responde e o Peek acompanha apenas por serviços de rastreamento, verifica a cada hora, a cada 3 horas após um dia sem novidades e até a cada 15 minutos no dia da entrega.

Quando a movimentação mais recente completa quatro dias e a encomenda segue em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito", o Peek sinaliza com a data desse registro, como "Sem atualizações desde seg 12 out". A página então mostra "É comum uma encomenda ficar sem atualizações em voos longos, na alfândega ou no fim de semana. O Peek continua consultando A Tu Hora Express." Após três semanas sem novidade, exibe "Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, fale com o vendedor. O Peek continua consultando A Tu Hora Express."

Você pode escolher alertas para "Cada movimentação", "Etapas importantes" ou "Só a entrega". O Peek apenas acompanha o rastreamento e não reagenda entregas, não altera endereços nem contata a A Tu Hora Express por você.

## Perguntas sobre o rastreamento da A Tu Hora Express

### Por que a A Tu Hora Express pede meu código postal de entrega?

A A Tu Hora Express só exibe códigos de 10 ou 16 dígitos quando acompanhados do código postal espanhol de 5 dígitos, tanto em [mydelivery.atuhoraexpress.com](https://mydelivery.atuhoraexpress.com/#/login-form-cli) quanto no Peek (onde o campo é opcional, mas sem ele a A Tu Hora Express não responde). Se a loja enviou um código de 24 dígitos, os cinco últimos dígitos já contêm o código postal.

### O que significa `Realizado con éxito` no rastreamento da A Tu Hora Express?

`Realizado con éxito` (código `4`) significa que a A Tu Hora Express registrou o envio como entregue. Se não estiver com você, verifique com moradores, vizinhos e portaria e fale com a loja e com `info@atuhoraexpress.com`.

### O que significa `Incidencia - Pendiente nuevo reparto`?

Significa que uma tentativa de entrega não pôde ser concluída e o volume entrou na fila para nova saída (muitas vezes junto a `Pendiente de realizar` no mesmo horário). Confirme seu endereço e telefone com a loja se isso se repetir.

### Como entrar em contato com a A Tu Hora Express na Espanha?

Ligue para `911 631 356`, envie e-mail para `info@atuhoraexpress.com` ou preencha o formulário [Ayúdanos a mejorar](https://atuhoraexpress.com/ayudanos-a-mejorar/) informando seu código de rastreamento e o código postal de 5 dígitos.

:::sources
- [A Tu Hora Express: Home (Spanish)](https://atuhoraexpress.com/) – transportadora espanhola de última milha, filiais, sede em Madri, `911 631 356` e `info@atuhoraexpress.com`
- [A Tu Hora Express: Home (English)](https://atuhoraexpress.com/home/) – Express 24h, Economy 48h, Masivo 72h, Reverse Logistics, B2B Premium e lista de filiais
- [A Tu Hora Express: Tracking Page](https://atuhoraexpress.com/tracking/) – entrada de rastreamento do site
- [A Tu Hora Express: MyDelivery Portal](https://mydelivery.atuhoraexpress.com/#/login-form-cli) – portal de consulta direta de envios
- [A Tu Hora Express: Legal Notice](https://atuhoraexpress.com/aviso-legal/) – *A TU HORA EXPRESS, S.L.*, NIF `B19300748`, domicílio legal em Alovera (Guadalajara) e `protecciondedatos@atuhoraexpress.com`
- [A Tu Hora Express: Feedback Form](https://atuhoraexpress.com/ayudanos-a-mejorar/) – formulário de feedback de clientes
- [Universal Parcel Scraper: a-tu-hora-express](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/a-tu-hora-express) – reconhecimento de 10/16/24 dígitos, exigência opcional do código postal de 5 dígitos, códigos de status em espanhol (`0`, `1`, `11`, `2`, `3`, `4`, `5`), horário local `Europe/Madrid`, data prevista de entrega, peso e campos omitidos
:::
