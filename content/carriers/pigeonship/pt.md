---
title: Rastreio PigeonShip: códigos, estados e entrega
description: Acompanha entregas no próprio dia e última milha PigeonShip nos EUA e Canadá: códigos de 18 carateres, estado em direto, horários e apoio.
slug: rastreio-pigeonship
published: 2026-11-24
updated: 2026-11-24
---

A PigeonShip é uma transportadora de entrega de última milha que opera nos Estados Unidos e no Canadá, com escritórios em Provo (Utah) e Oakville (Ontário). Retalhistas, lojas online e empresas locais usam a PigeonShip para entregas no próprio dia, no dia seguinte e regionais através de serviços como **Pigeon Flex** (entrega nacional em 2–5 dias), **Pigeon Express** (entrega nacional em 2 dias), **Pigeon Connect** (próprio dia a pedido) e **Pigeon Flash** (próprio dia agendada).

A PigeonShip recruta motoristas independentes e opera programas dedicados de entrega para empresas. Os destinatários recebem uma ligação de rastreio em `admin.pigeonship.com` que mostra o estado do pedido e a hora limite prevista, passando depois a uma página de conclusão assim que a encomenda é entregue.

## Formato do número de rastreio PigeonShip

O Peek aceita números PigeonShip de 8 a 30 letras e dígitos; remove espaços e converte letras em maiúsculas. O Peek reconhece um formato de 18 carateres que alterna dígitos e letras maiúsculas:

- **18 carateres alfanuméricos** seguindo o padrão `dígito-letra-dígito-letra-3 dígitos-letra-3 dígitos-letra-2 dígitos-letra-dígito-2 letras` (por exemplo, `7P4K829H631N58R2TM`).

As ligações de rastreio da PigeonShip têm o formato `admin.pigeonship.com/shipping/tracking/package/<trackingNumber>/`. Quando colas essa ligação no Peek, o Peek extrai o código da encomenda e encaminha-o diretamente para a PigeonShip. A partir de um código isolado de 18 carateres com este formato, o Peek consulta apenas a PigeonShip e mostra «PigeonShip tem esta encomenda.» assim que responde. Para comparar com outros formatos dos EUA e Canadá, consulta o [guia de formatos de números de rastreio](guide:tracking-number-formats).

## Como funciona o rastreio em direto da PigeonShip

A página de rastreio para destinatários da PigeonShip é uma vista de resumo em vez de um histórico de registos. Enquanto o pedido está ativo, mostra «In transit» e o motorista num mapa, com hora limite de entrega (`dueDate`) quando definida; nos bastidores, o pedido tem um código e uma etiqueta de estado, como `16` e `Assigned`. A página só mostra «Delivered» para o código `64`. Terminada a entrega, a PigeonShip substitui a página nesse mesmo URL por uma página de conclusão com `Your delivery has been completed successfully.` e uma ligação para as fotografias da entrega.

| Estado / página PigeonShip | O que significa |
| --- | --- |
| Estado `16` — `Assigned` | O pedido está na rota de um motorista; a página mostra «In transit». |
| Hora limite / `dueDate` | A hora prevista de conclusão mostrada nos pedidos ativos antes da entrega. |
| Estado `64` / `Delivered` ou `Your delivery has been completed successfully.` | O motorista concluiu a entrega; abrir a ligação própria da PigeonShip permite ver a fotografia de confirmação. |
| `Page not found` (404) | O número de rastreio não está ativo no sistema em direto da PigeonShip ou contém um erro de digitação. |

Como a PigeonShip não publica um histórico datado de registos na sua página de destinatário, não verás leituras intermédias em armazéns ou centros de triagem como na [UPS](carrier:ups), [USPS](carrier:usps), [Canada Post](carrier:canada-post) ou [Intelcom](carrier:intelcom). Em vez disso, a página atualiza o estado em direto durante o percurso e muda para o ecrã final após o depósito.

## Entregas no próprio dia e o que verificar em caso de atraso

A PigeonShip oferece entregas no próprio dia e no dia seguinte em todos os Estados Unidos e Canadá. Avaliações no seu site referem uma SMS antes da chegada do motorista e uma fotografia após a entrega.

Se o pedido permanecer em `Assigned` após a hora prevista ou a ligação mostrar `Page not found`:

- **Verifica erros de escrita:** confirma o código completo ou a ligação inteira `admin.pigeonship.com` no e-mail ou SMS da loja; o site da PigeonShip devolve `Page not found` para qualquer número sem página ativa.
- **Contacta a PigeonShip:** usa o [formulário de contacto](https://www.pigeonship.com/contact-us) se o pedido continuar em «In transit» depois da hora limite.
- **Revê as instruções de entrega:** avaliações no site da PigeonShip referem que os motoristas seguem as indicações da etiqueta e tiram foto ao depósito; abre a ligação original em `admin.pigeonship.com` para ver as fotos após conclusão. Para mais passos, lê [quando o rastreio deixa de atualizar](guide:tracking-not-updating) e o [guia de entrega falhada](guide:missed-delivery).

## Seguir a PigeonShip com o Peek

Cola o número PigeonShip ou a ligação completa `admin.pigeonship.com/shipping/tracking/package/...` no Peek. Se o Peek não pré-selecionar a PigeonShip para um formato fora do habitual, escolhe **Alterar** ao adicionar a encomenda ou **Alterar transportadora** no ecrã da encomenda.

O Peek obtém diretamente a página de rastreio renderizada no servidor da PigeonShip através do [adaptador aberto PigeonShip do universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/pigeonship). Lê o código e a etiqueta de estado do pedido, se foi entregue e a hora limite (`dueDate`) quando presente, que o Peek mostra como entrega prevista. O Peek omite o nome do destinatário, as coordenadas do motorista no mapa, a barra de progresso e as fotografias de entrega — abre a ligação própria da PigeonShip para ver a foto.

Como a PigeonShip mostra apenas o estado atual, o Peek acrescenta uma linha datada quando deteta cada mudança de etapa: um pedido ativo `Assigned` surge como **Em trânsito** com a hora prevista, e uma entrega concluída como **Entregue**.

Enquanto tens a encomenda aberta no Peek, ou no dia da entrega com as notificações ativadas, o Peek consulta até de 10 em 10 minutos enquanto a página de rastreio da PigeonShip responder. Caso contrário, o Peek verifica de hora a hora. Como a página da PigeonShip não apresenta uma etapa de saída para distribuição, o Peek nunca passa às verificações de 2 em 2 minutos no dia da entrega. Após 12 horas apenas em «Etiqueta criada» ou «Aceite pela transportadora», ou dois dias sem novidades, verifica de hora a hora independentemente da forma como acompanhas a encomenda, e de 6 em 6 horas após sete dias em «Etiqueta criada». Quando a página da PigeonShip não responde e o Peek acompanha a encomenda apenas através de serviços de rastreio, verifica de hora a hora, de 3 em 3 horas após um dia sem novidades e até de 15 em 15 minutos no dia da distribuição enquanto tens a encomenda aberta ou as notificações ativadas.

Quando o registo mais recente tem quatro dias e a encomenda continua em «Etiqueta criada», «Aceite pela transportadora» ou «Em trânsito», o Peek assinala-a com o dia desse registo, como «Sem atualizações desde seg., 12/10». A página da encomenda apresenta então «É habitual uma encomenda ficar sem novidades em voos longos, na alfândega ou ao fim de semana. O Peek continua a perguntar a PigeonShip.», com uma ligação «Pede ajuda a PigeonShip» para a página de contacto da PigeonShip. Passadas três semanas sem novos registos, mostra «Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, contacta o vendedor. O Peek continua a perguntar a PigeonShip.»

Nas definições de notificações do Peek, escolhe **Todos os registos**, **Passos importantes** ou **Só a entrega** para decidir que atualizações geram alertas.

## Contactar a PigeonShip

- **Formulário oficial de contacto:** [PigeonShip Contact Us](https://www.pigeonship.com/contact-us) (seleciona United States, Canada ou Other e descreve o pedido de entrega)
- **Site oficial:** [pigeonship.com](https://www.pigeonship.com/)
- **Escritório EUA:** PigeonShip USA, 1675 N Freedom Blvd Ste 9B, Provo, Utah 84604-6929
- **Escritório Canadá:** PigeonShip Canada, 405 Dundas St W, Oakville, ON L6M 5P9

## Perguntas frequentes

### Por que não mostra o rastreio PigeonShip uma lista de registos anteriores?

A página em direto da PigeonShip para destinatários é apenas um resumo: apresenta o estado atual do pedido («In transit» ou «Delivered») e a hora limite em vez de um histórico por armazéns.

### Onde posso ver a foto de entrega da PigeonShip?

O Peek não guarda nem exibe fotografias de entrega da PigeonShip. Quando a encomenda aparecer como entregue, abre a ligação original `admin.pigeonship.com/shipping/tracking/package/...` do teu e-mail ou SMS para ver a página final e as fotos da PigeonShip.

### O que significa o estado `Assigned` na PigeonShip?

`Assigned` (código de estado `16`) é o estado interno do pedido nos bastidores da página PigeonShip enquanto está na rota de um motorista; a página mostra «In transit». O Peek apresenta-o como **Em trânsito**.

### A PigeonShip faz entregas tanto nos EUA como no Canadá?

Sim. A PigeonShip opera entregas no próprio dia, no dia seguinte e nacionais de última milha nos Estados Unidos e no Canadá, com escritórios em Provo (Utah) e Oakville (Ontário).

:::sources
- [PigeonShip](https://www.pigeonship.com/)
- [PigeonShip Contact Us](https://www.pigeonship.com/contact-us)
- [adaptador PigeonShip do universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/pigeonship)
:::
