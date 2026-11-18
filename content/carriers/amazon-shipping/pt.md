---
title: Amazon Shipping seguimento: número PT ou ES e o que fazer
description: Segue uma encomenda da Amazon Shipping sem conta Amazon: formato do número PT, ES ou TBA, qual rastreador responde e com quem falar se atrasar.
slug: amazon-shipping-seguimento
published: 2026-11-18
updated: 2026-11-18
---

Uma encomenda da Amazon Shipping segue-se sem conta Amazon no rastreador público da Amazon Shipping (como `track.amazon.es` ou `track.amazon.fr`) ou colando o código na caixa acima. A Amazon Shipping é a rede de última milha com que a Amazon entrega envios de outras empresas: segundo esse rastreador, é o vendedor quem cria o envio na Amazon Shipping, quer tenhas comprado na Amazon quer noutro site. Os restantes pedidos da própria Amazon — incluindo compras para Portugal via `Amazon.es` — seguem-se em «Os meus pedidos».

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

O Peek verifica até a cada 10 minutos com a encomenda aberta ou no dia da entrega com notificações ativas, e a cada hora nos outros casos (também após dois dias sem novidades ou 12 horas em «Etiqueta criada» ou «Aceite pela transportadora»). Nas mesmas condições, em «Em distribuição» desde as 6:00 locais verifica até a cada 2 minutos, com alertas «Todas as leituras», «Passos importantes» ou «Só a entrega».

## Perguntas sobre a Amazon Shipping

### A Amazon Shipping é o mesmo que a Amazon?

Não de todo: é a rede da Amazon a entregar para outras empresas (na Amazon ou noutras lojas), com rastreio público por código; as restantes encomendas da Amazon seguem-se em «Os meus pedidos».

### Existe rastreador português da Amazon Shipping?

Não: o rastreador europeu só tem páginas para Espanha, França, Itália, Reino Unido e Índia (clientes em Portugal usam a [Amazon.es](https://www.aboutamazon.eu/news/press-lounge/amazon-prime-now-available-to-customers-in-portugal)). O Peek consulta `track.amazon.es` para `ES` e `track.amazon.fr` para `PT`.

### Quanto tempo demora a Amazon Shipping?

Se o rastreador indicar dia previsto, o Peek mostra-o (exceto `TBA`, `TBC` e `TBM`). Na UE, sem data combinada, o prazo supletivo é de 30 dias.

:::sources
- [About Amazon España: Carta anual 2023](https://www.aboutamazon.es/noticias/noticias-de-la-compania/carta-anual-a-los-accionistas-2023-de-nuestro-ceo-andy-jassy) – rede de última milha para envios de outras empresas
- [About Amazon EU: Prime in Portugal](https://www.aboutamazon.eu/news/press-lounge/amazon-prime-now-available-to-customers-in-portugal) – clientes em Portugal via Amazon.es
- [About Amazon España: 6 consejos de compra segura](https://www.aboutamazon.es/noticias/politicas-publicas/6-consejos-para-comprar-online-de-forma-segura) – pagamentos, links, mensagens e denúncias
- [Your Europe: Expedição e entrega](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_pt.htm) – responsabilidade do vendedor e prazo de 30 dias
- [Your Europe: FAQ entrega](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/faq/index_pt.htm) – prova de entrega e reembolso
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formatos PT, ES e TBA, rastreadores consultados, 45 dias e intervalos
:::
