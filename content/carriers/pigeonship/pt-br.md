---
title: Rastreamento PigeonShip: códigos, status e entregas
description: Rastreie entregas no mesmo dia e de última milha da PigeonShip nos EUA e Canadá: códigos de 18 caracteres, status ao vivo, prazo limite e contatos de suporte.
slug: pigeonship-tracking
published: 2026-11-26
updated: 2026-11-26
---

A PigeonShip é uma transportadora de última milha que atua nos Estados Unidos e no Canadá, com escritórios em Provo (Utah) e Oakville (Ontário). Varejistas, lojas virtuais e negócios locais utilizam a PigeonShip para entregas no mesmo dia, no dia seguinte e regionais de e-commerce por meio de serviços como **Pigeon Flex** (entrega nacional em 2–5 dias), **Pigeon Express** (entrega nacional em 2 dias), **Pigeon Connect** (entrega sob demanda no mesmo dia) e **Pigeon Flash** (entrega agendada no mesmo dia).

A PigeonShip recruta motoristas independentes e opera programas dedicados de entrega para empresas. Os destinatários recebem um link de rastreamento em `admin.pigeonship.com` que exibe o status do pedido e o horário limite de entrega, substituído por uma tela de conclusão assim que o pedido é entregue.

## Formato do código de rastreamento da PigeonShip

O Peek aceita códigos da PigeonShip de 8 a 30 letras e dígitos, removendo espaços e convertendo letras para maiúsculas. O Peek reconhece um formato de 18 caracteres alternando dígitos e letras maiúsculas:

- **18 caracteres alfanuméricos** no padrão dígito-letra-dígito-letra-3 dígitos-letra-3 dígitos-letra-2 dígitos-letra-dígito-2 letras (por exemplo `7P4K829H631N58R2TM`, inventado).

Os links de rastreamento da PigeonShip seguem o padrão `admin.pigeonship.com/shipping/tracking/package/<trackingNumber>/`. Ao colar esse link no Peek, o app extrai o código do pacote e o envia direto para a PigeonShip. Para um código isolado de 18 caracteres nesse formato, o Peek consulta apenas a PigeonShip e exibe "PigeonShip está com esta encomenda." assim que recebe resposta. Para comparar com outros formatos dos EUA e Canadá, consulte o [guia de formatos de código de rastreamento](guide:tracking-number-formats).

## Como funciona o rastreamento ao vivo da PigeonShip

A página de rastreamento ao destinatário da PigeonShip apresenta uma visão resumida em vez de um histórico de leituras. Enquanto o pedido está ativo, mostra "In transit" e o motorista em um mapa, além do horário limite de entrega (`dueDate`) quando configurado; por trás da página, o pedido carrega um código de status e rótulo, como `16` e `Assigned`. A página só exibe "Delivered" para o código `64`. Assim que a entrega termina, a PigeonShip substitui a página naquele mesmo endereço por uma tela de conclusão com `Your delivery has been completed successfully.` e um link para as fotos da entrega.

| Status / página da PigeonShip | O que significa |
| --- | --- |
| Status `16` — `Assigned` | O pedido está na rota de um motorista; a página mostra "In transit". |
| Prazo / horário limite de entrega | O horário previsto de conclusão exibido em pedidos ativos antes da entrega. |
| Status `64` / `Delivered` ou `Your delivery has been completed successfully.` | O motorista concluiu a entrega; abra o link original da PigeonShip para ver a foto de confirmação. |
| `Page not found` (404) | O código de rastreamento não está ativo no sistema da PigeonShip ou contém erro de digitação. |

Como a PigeonShip não publica um registro cronológico de leituras na página do destinatário, você não verá movimentações intermediárias de armazéns ou centros de triagem como ocorre com [UPS](carrier:ups), [USPS](carrier:usps), [Canada Post](carrier:canada-post) ou Intelcom. Em vez disso, a página atualiza o estado em tempo real durante o trajeto e muda para a tela de conclusão ao finalizar a entrega.

## Expectativas na entrega no mesmo dia e o que verificar se atrasar

A PigeonShip oferece entregas no mesmo dia e no dia seguinte em todos os EUA e Canadá. Avaliações em seu site mencionam um SMS antes da chegada do motorista e uma foto após deixar o pacote.

Se o seu pedido continuar em `Assigned` após o horário esperado ou se o link exibir `Page not found`:

- **Verifique erros de digitação:** confirme o código completo ou o link completo de `admin.pigeonship.com` no e-mail ou SMS da loja; o site da PigeonShip responde `Page not found` para qualquer código sem página ativa.
- **Contate a PigeonShip:** use o [formulário de contato](https://www.pigeonship.com/contact-us) caso o pedido continue em "In transit" após o horário limite.
- **Confira instruções e fotos de entrega:** avaliações no site da PigeonShip mencionam motoristas seguindo as instruções da etiqueta e registrando foto da entrega; abra o link original em `admin.pigeonship.com` para ver as imagens após a conclusão. Para mais dicas, leia [quando o rastreamento para de atualizar](guide:tracking-not-updating) e o [guia de entrega não realizada](guide:missed-delivery).

## Acompanhe a PigeonShip com o Peek

Cole seu código de rastreamento da PigeonShip ou o link completo `admin.pigeonship.com/shipping/tracking/package/...` no Peek. Se o Peek não pré-selecionar a PigeonShip para um código fora do padrão, escolha **Trocar** ao adicionar o pacote ou **Trocar transportadora** na tela da encomenda.

O Peek lê a página de rastreamento da PigeonShip diretamente pelo [adaptador PigeonShip do universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/pigeonship). Ele extrai o código e o rótulo de status do pedido, se foi entregue e o horário limite (`dueDate`) quando disponível, exibido pelo Peek como entrega esperada. O Peek não mostra o nome do destinatário, as coordenadas do motorista no mapa, a barra de progresso nem as fotos da entrega — abra o link próprio da PigeonShip para ver a foto.

Como a PigeonShip informa apenas o estado atual, o Peek cria uma entrada datada no momento em que detecta cada mudança de etapa: um pedido ativo `Assigned` aparece como **Em trânsito** com seu horário esperado de entrega, e uma entrega concluída aparece como **Entregue**.

Enquanto você mantém a encomenda aberta no Peek, ou no dia da entrega com notificações ligadas, o Peek consulta até a cada 10 minutos enquanto a página da PigeonShip responder. Fora isso, verifica de hora em hora. Como a página da PigeonShip não possui etapa de saída para entrega, o Peek nunca ativa verificações a cada 2 minutos no dia da entrega. Após 12 horas apenas em "Etiqueta criada" ou "Recebida pela transportadora", ou dois dias sem novidade, consulta de hora em hora de qualquer forma, e a cada 6 horas depois de sete dias em "Etiqueta criada". Quando a página da PigeonShip não responde e o Peek acompanha somente por serviços de rastreamento, consulta de hora em hora, a cada 3 horas após um dia quieto e até a cada 15 minutos no dia da saída para entrega.

Quando a leitura mais recente tem quatro dias e o pacote segue em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito", o Peek sinaliza a data dessa leitura, como "Sem atualizações desde seg 12 out". A página do pacote então exibe "É comum uma encomenda ficar sem atualizações em voos longos, na alfândega ou no fim de semana. O Peek continua consultando PigeonShip.", com o link "Pedir ajuda a PigeonShip" para a página de contato da PigeonShip. Após três semanas sem novas leituras, mostra "Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, fale com o vendedor. O Peek continua consultando PigeonShip."

Nas configurações de notificação do Peek, escolha **Cada movimentação**, **Etapas importantes** ou **Só a entrega** para definir quais avisos deseja receber.

## Como contatar a PigeonShip

- **Formulário oficial de contato:** [PigeonShip Contact Us](https://www.pigeonship.com/contact-us) (selecione United States, Canada ou Other e descreva sua solicitação de entrega)
- **Site oficial:** [pigeonship.com](https://www.pigeonship.com/)
- **Escritório nos EUA:** PigeonShip USA, 1675 N Freedom Blvd Ste 9B, Provo, Utah 84604-6929
- **Escritório no Canadá:** PigeonShip Canada, 405 Dundas St W, Oakville, ON L6M 5P9

## Perguntas frequentes

### Por que o rastreamento da PigeonShip não mostra uma lista de leituras anteriores?

A página ao vivo da PigeonShip para destinatários é apenas um resumo: mostra o status atual do pedido ("In transit" ou "Delivered") e o horário limite de entrega, sem histórico de paradas em armazéns.

### Onde posso ver a foto de entrega da PigeonShip?

O Peek não armazena nem exibe fotos de entrega da PigeonShip. Quando a encomenda aparecer como entregue, abra o link original `admin.pigeonship.com/shipping/tracking/package/...` do seu e-mail ou SMS para ver a tela de conclusão e as fotos.

### O que significa o status `Assigned` na PigeonShip?

`Assigned` (código `16`) é o status interno do pedido na página da PigeonShip enquanto está na rota do motorista; a própria página exibe "In transit", e o Peek o mostra como **Em trânsito**.

### A PigeonShip entrega tanto nos EUA quanto no Canadá?

Sim. A PigeonShip opera serviços de entrega no mesmo dia, no dia seguinte e de última milha em todos os Estados Unidos e Canadá, com escritórios em Provo (Utah) e Oakville (Ontário).

:::sources
- [PigeonShip](https://www.pigeonship.com/)
- [PigeonShip Contact Us](https://www.pigeonship.com/contact-us)
- [Adaptador PigeonShip do universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/pigeonship)
:::
