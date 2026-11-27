---
title: Amazon Shipping seguimento: número PT ou ES e o que fazer
description: Seguir encomenda Amazon Shipping em Portugal sem conta Amazon: códigos PT, ES e TBA, diferença para Amazon Logistics, prazos na UE e a quem reclamar.
slug: amazon-shipping-seguimento
published: 2026-11-18
updated: 2026-11-27
---

Podes seguir uma encomenda da Amazon Shipping sem conta Amazon colando o código (`PT` ou `ES` + 10 algarismos, ou `TBA`/`TBC`/`TBM` + 12) na caixa acima ou no rastreador público (`track.amazon.es`, `track.amazon.fr`, `track.amazon.it` ou `track.amazon.co.uk`). A Amazon Shipping é a rede de última milha com que a Amazon entrega envios de outras empresas (comprados na Amazon ou noutras lojas); se o código der «não encontrado» e compraste na Amazon, segue-o em «Os meus pedidos» na [Amazon Logistics](carrier:amazon-logistics).

## Número de seguimento da Amazon Shipping

Não há página portuguesa no rastreador público: na Europa existem apenas páginas para Espanha (`track.amazon.es`), França (`track.amazon.fr`), Itália (`track.amazon.it`), Reino Unido (`track.amazon.co.uk`) e Índia. O Peek consulta `track.amazon.es` para códigos `ES`, `track.amazon.fr` para `PT` e `track.amazon.com` para `TBA`, `TBC` ou `TBM`.

Formatos reconhecidos pelo Peek (algarismos inventados):

| Formato | O que é |
| --- | --- |
| `PT4829105736`, `ES4829105736` | Código de país europeu (`PT`, `ES`, `FR`, `IT`, `DE`, `UK`, etc.) e 10 algarismos. |
| `TBA482910573649` | `TBA`, `TBC` ou `TBM` e 12 algarismos (também `TBR` com 9 ou `TBAU` com 9). |

A armadilha: a [Amazon Logistics](carrier:amazon-logistics), que entrega as compras da própria Amazon, usa exatamente os mesmos formatos. Só o rastreador público da Amazon Shipping os separa, pois apenas conhece pacotes Amazon Shipping e dá «não encontrado» para os restantes. Se nada surgir e compraste na Amazon, vê [Amazon Logistics](carrier:amazon-logistics). Números de pedido com hífenes (três, sete e sete dígitos) não são códigos de rastreio, e códigos `BR` ou `US` com 10 dígitos não são aceites pelo Peek como Amazon. Sem código? Pede-o à loja: [onde encontrar o número de seguimento](guide:find-tracking-number).

## Encomenda atrasada, extraviada ou danificada

Fala sempre com a loja onde compraste. Na UE, segundo o [Your Europe](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_pt.htm), o vendedor responde pela entrega e pelos danos desde a expedição até receberes o bem.

**Marcada como entregue, mas não chegou.** Segundo as [FAQ do Your Europe](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/faq/index_pt.htm), cabe ao vendedor provar junto da transportadora que houve entrega; se não o provar, tem de enviar artigo idêntico ou reembolsar. Vê [entregue mas não recebida](guide:delivered-not-received).

**Danificada.** O vendedor assume o risco do transporte: tens direito a reparação, substituição, redução do preço ou reembolso ([encomenda danificada](guide:damaged-parcel)).

**Atrasada ou perdida.** Sem outra data acordada, na UE a entrega deve ocorrer em 30 dias. Se falhar, dá um prazo adicional razoável; persistindo a falta, podes cancelar e receber reembolso. Se só o rastreio parou, lê [seguimento sem atualizações](guide:tracking-not-updating).

## Contactos da Amazon Shipping

Para atrasos, extravios ou danos, contacta diretamente o vendedor.

> Segundo a [About Amazon España](https://www.aboutamazon.es/noticias/politicas-publicas/6-consejos-para-comprar-online-de-forma-segura), a Amazon nunca pede por telefone informações sobre o teu meio de pagamento. Desconfia de urgências, revê erros nos links e confirma mensagens no centro de mensagens da tua conta Amazon antes de agir; denuncia contactos suspeitos à Amazon. Mais em [burlas por SMS](guide:delivery-scam-texts).

## Seguimento da Amazon Shipping no Peek

Cola o número ou um link de `track.amazon` (`/tracking/` e o código) no [Peek](/). Se o rastreador público da Amazon responder, vês «A Amazon Shipping disponibiliza seguimento público para esta encomenda. O Peek mantém esta entrega atualizada.» Se não conhecer o número, é tratado como entrega privada da [Amazon Logistics](carrier:amazon-logistics). Se a consulta falhar, toca em «Tentar novamente». A Amazon Shipping não consta de «Alterar transportadora» e não pede código postal.

O Peek mostra estado, histórico com cidade, região e país num mapa, dia previsto (exceto `TBA`, `TBC` e `TBM`) e hora de entrega («Horas no teu fuso horário: …»). Usa etapas curtas em português («Em distribuição», «Entregue»), deixando em inglês apenas `Arrived at sorting center`, `Arrived at delivery center` e `Departed facility`, e omite loja, nome, morada, assinatura e foto. Quando o rastreador da Amazon deixa de dar o histórico (após 45 dias), o Peek avisa e pára de consultar.

O Peek verifica até a cada 10 minutos com a encomenda aberta ou no dia da entrega com notificações ativas, e a cada hora nos outros casos (também após dois dias sem novidades ou 12 horas em «Etiqueta criada» ou «Aceite pela transportadora»). Nas mesmas condições, em «Em distribuição» desde as 6:00 locais verifica até a cada 2 minutos, com alertas «Todos os registos», «Passos importantes» ou «Só a entrega».

## Perguntas sobre a Amazon Shipping

### Qual é a diferença entre Amazon Shipping e Amazon Logistics?

A Amazon Shipping é a rede de entrega usada por outras empresas (vendam na Amazon ou noutras lojas) e tem rastreio público por código sem iniciar sessão. A [Amazon Logistics](carrier:amazon-logistics) entrega as restantes encomendas da própria Amazon e usa os mesmos formatos de código, mas só se segue em «Os meus pedidos».

### Existe rastreador português da Amazon Shipping?

Não: na Europa existem apenas páginas públicas para Espanha (`track.amazon.es`), França (`track.amazon.fr`), Itália (`track.amazon.it`) e Reino Unido (`track.amazon.co.uk`), além da Índia (clientes em Portugal compram via [Amazon.es](https://www.aboutamazon.eu/news/press-lounge/amazon-prime-now-available-to-customers-in-portugal)). No Peek, códigos `ES` consultam `track.amazon.es` e códigos `PT` consultam `track.amazon.fr`.

### Como são os códigos PT, ES e TBA (e porque o número de pedido não funciona)?

Os códigos da Amazon Shipping têm duas letras de país europeu (`PT`, `ES`, `FR`, `IT`, `DE`, `UK`) seguidas de 10 algarismos, ou `TBA`/`TBC`/`TBM` + 12 algarismos. O número de pedido com hífenes (3-7-7 dígitos) serve apenas para identificar a compra e não rastreia o transporte.

### Quanto tempo demora a Amazon Shipping a entregar?

Quando o rastreador público indica dia previsto, o Peek mostra-o automaticamente (exceto em códigos `TBA`, `TBC` e `TBM`), mantendo o histórico disponível durante 45 dias. Na UE, sem data combinada, o prazo legal máximo de entrega é de 30 dias.

### A quem reclamar se uma encomenda Amazon Shipping atrasar ou não chegar?

Fala sempre com a loja onde compraste: segundo o [Your Europe](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_pt.htm), o vendedor responde pela entrega e por danos até receberes a encomenda, cabendo-lhe provar a entrega ou reembolsar/substituir. Desconfia de telefonemas ou links suspeitos a pedir dados de pagamento.

:::sources
- [About Amazon España: Carta anual 2023](https://www.aboutamazon.es/noticias/noticias-de-la-compania/carta-anual-a-los-accionistas-2023-de-nuestro-ceo-andy-jassy) – rede de última milha para envios de outras empresas
- [About Amazon EU: Prime in Portugal](https://www.aboutamazon.eu/news/press-lounge/amazon-prime-now-available-to-customers-in-portugal) – clientes em Portugal via Amazon.es
- [About Amazon España: 6 consejos de compra segura](https://www.aboutamazon.es/noticias/politicas-publicas/6-consejos-para-comprar-online-de-forma-segura) – pagamentos, links, mensagens e denúncias
- [Your Europe: Expedição e entrega](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_pt.htm) – responsabilidade do vendedor e prazo de 30 dias
- [Your Europe: FAQ entrega](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/faq/index_pt.htm) – prova de entrega e reembolso
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formatos PT, ES e TBA, rastreadores consultados, 45 dias e intervalos
:::
