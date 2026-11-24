---
title: Rastreio 360Lion: códigos WSH e AR, estados e contactos
description: Segue uma encomenda 360Lion Express da China para a América Latina ou EUA: formatos WSH e AR, códigos de registo, entrega local e contactos.
slug: rastreio-360lion
published: 2026-11-24
updated: 2026-11-24
---

A 360Lion (*360Lion Express*, em chinês *纬狮物流 / 纬狮物联网*) é uma operadora chinesa de logística transfronteiriça para comércio eletrónico fundada em 2015 e sediada em Shenzhen, com delegações e centros operacionais em Xangai, Cantão, Dongguan, Yiwu e Hong Kong. Especializa-se em linhas aéreas dedicadas para pequenas encomendas, carga FBA/FBM e armazéns no exterior a partir da China para a América Latina e os Estados Unidos (México, Chile, Peru, Brasil, Colômbia, Equador, Argentina, Uruguai, Bolívia, Paraguai, El Salvador, Costa Rica, Guatemala, Venezuela, Panamá e EUA) para vendedores em plataformas como Mercado Libre, Amazon, AliExpress, Shopee, SHEIN, Temu, TikTok Shop, Walmart e Falabella. Podes consultar envios no [portal de rastreio da 360Lion](https://toms.360lion.com/track) ou em [360lion.com/en/](https://www.360lion.com/en/) (até 50 códigos em simultâneo), ou colar o número de rastreio na caixa acima.

## Números de rastreio 360Lion: formatos WSH e AR

Procura o número de rastreio no ecrã da encomenda no marketplace ou no e-mail de confirmação de envio. As encomendas da 360Lion costumam usar dois formatos:

| Formato (inventado) | O que é |
| --- | --- |
| `WSHMX4829173640YQ` | Guia de linha dedicada 360Lion: `WSH`, código de país de destino de duas letras (por exemplo `MX` para o México), 10 dígitos e `YQ`. |
| `AR4829173640` | `AR` seguido de 10 dígitos (usado em encomendas com destino à Argentina). Partilha este formato com [Colis Privé](carrier:colis-prive), [Aramex Australia](carrier:aramex-au) e [Aramex New Zealand](carrier:aramex-nz). |

Se a tua encomenda para o Brasil ou México seguir com o código de barras de outra transportadora — como `AJ` da [Anjun Logistics](carrier:anjun), `8880` da [J&T Express Brazil](carrier:j-and-t-br) ou `JMX` da [J&T Express Mexico](carrier:j-and-t-mx) — consulta [formatos de números de rastreio](guide:tracking-number-formats).

## O que significam os estados de rastreio 360Lion

A 360Lion agrupa os envios em estados gerais (`Processing`, `Pre-alert`, `In transit.`, `Returned`, `Delivered`) acompanhados de códigos de registo (`WSO*` na origem na China, `WSD*` no destino). Assim que a encomenda chega à transportadora local do último quilómetro, o rastreio pode apresentar as mensagens dessa operadora.

| A 360Lion mostra (código / grupo) | O que significa para ti | O Peek mostra |
| --- | --- | --- |
| `Processing` (`PRECESSING`) | O vendedor criou a ordem de envio na 360Lion antes de começarem os registos de transporte principal. | «Etiqueta criada» |
| Etapa `HUB` | Aceite no centro de triagem de origem da 360Lion na China. | «Aceite pela transportadora» |
| `departed from airport` (`WSO04`), `arrived at airport` (`WSD00`), `In transit.` (`IN_TRANSIT`) | Saiu de avião da China (com código de aeroporto como PVG) e aterrou no aeroporto de destino. | «Em trânsito» |
| Etapa `CUSTOMS` | Em desalfandegamento de exportação ou de importação no país de destino. | «Na alfândega» |
| `handover to lastmile` (`WSD02`), `Arrived at sorting center` (`WSD051`), `package on the way to delivery office` (`WSD050`) | Entregue ao parceiro local do último quilómetro no país de destino, triada e expedida para a estação local de entrega. | «Em trânsito» |
| `Returned` (`RETURNED_BACK`) | Devolvida em direção ao remetente ou armazém de origem. | «Devolvida ao remetente» |
| `Delivered` (`DELIVERED`) | Entregue ao destinatário pelo parceiro local do último quilómetro. | «Entregue» |

Repara que o estado `Pre-alert` (`WARNING` / *预警*) da 360Lion tanto pode ser um pré-aviso como um alerta, pelo que o Peek deixa as linhas de registo decidirem a etapa em vez de tratar `Pre-alert` isoladamente como falha.

## Como funciona a entrega transfronteiriça da 360Lion

Em [360lion.com/en/](https://www.360lion.com/en/) e [360lion.com](https://www.360lion.com/), a 360Lion apresenta-se como operadora de linha transfronteiriça e agente geral de vendas e serviços aéreos (GSSA) com voos charter regulares da China para a América Latina, apoiada por armazéns no México, Chile, Brasil, Colômbia e Estados Unidos.

**Entrega no último quilómetro.** Após o aeroporto de destino (`WSD00`) e a alfândega de importação, a 360Lion entrega a encomenda (`WSD02`) a uma transportadora local no teu país, que faz a entrega final.

**Regras para destinatários e alfândega.** Como a 360Lion trabalha com expedidores empresariais em vez de compradores particulares, o seu site não publica limites de tentativas de entrega, prazos de guarda em pontos de levantamento nem garantias de prazo para pequenas encomendas (a [página de FAQ e condições](https://www.360lion.com/?m=home&c=Lists&a=index&tid=82) cobre normas comerciais para vendedores, como frete aéreo SEND, artigos proibidos e prazo de reclamação de vendedores de 30 dias). O IVA e os direitos de importação seguem as regras aduaneiras do teu país. Consulta [encomendas retidas na alfândega](guide:customs).

## Encomenda 360Lion parada, sem novidades ou não entregue

Como a 360Lion contrata com a loja online ou marketplace e não com o comprador, contacta sempre primeiro o vendedor ou a plataforma em caso de atrasos, erros de morada ou reembolsos.

**Sem atualizações entre a China e o destino.** Voos de longo curso da China para a América Latina e filas alfandegárias deixam frequentemente intervalos de vários dias entre `departed from airport` (`WSO04`) e `arrived at airport` (`WSD00`). Consulta [porque o rastreio deixa de atualizar](guide:tracking-not-updating).

**Entrega local sem segundo número.** O Peek acompanha os registos da transportadora local através do rastreio da 360Lion sob o teu código `WSH` ou `AR` e não mostra um segundo número local. Se a entrega falhar e precisares do contacto ou da referência do parceiro local, pergunta ao vendedor ou escreve ao apoio de consulta de encomendas da 360Lion.

**Entregue, danificada ou perdida.** Confirma primeiro com familiares, vizinhos e receção do edifício se surgir `Delivered`; se faltar ou chegar danificada, guarda provas e abre disputa junto do vendedor ou marketplace antes de terminar a proteção ao comprador. Consulta [entregue mas não recebida](guide:delivered-not-received), [encomenda danificada](guide:damaged-parcel), [encomenda perdida](guide:lost-parcel) e [devolvida ao remetente](guide:returned-to-sender).

## Apoio ao cliente e contactos 360Lion

Contactos publicados na [página de contactos da 360Lion](https://www.360lion.com/?m=home&c=Lists&a=index&tid=79) e em [360lion.com/en/](https://www.360lion.com/en/):

- **E-mail para consulta de encomendas (*查件业务*):** `customerservice@360lion.com`.
- **E-mail geral / comercial:** `info@360lion.com`.
- **Telefone da sede em Shenzhen:** `+86 755 2360 1325` (Xangai `+86 21 6142 1360`, Cantão `+86 20 8653 4133`).
- **Morada da sede:** 7/F, Block A, Star River World, No. 1 Yabao Road, Bantian Street, Longgang District, Shenzhen, Guangdong, China.

> Atenção a SMS ou mensagens de WhatsApp falsas a exigir um pagamento urgente por link para «libertar» uma encomenda internacional na alfândega. Confirma sempre qualquer pedido genuíno de taxas através da página do pedido no marketplace ou no portal oficial postal/aduaneiro do teu país. Consulta [SMS falsas de entregas](guide:delivery-scam-texts).

## Segue uma encomenda 360Lion com o Peek

O [Peek](/) consulta automaticamente a 360Lion quando introduzes um código `WSH...YQ` (como `WSHMX4829173640YQ`, dígitos inventados) — onde a 360Lion é a única candidata — ou um código `AR` + 10 dígitos (como `AR4829173640`), no qual o Peek consulta a 360Lion a par de [Colis Privé](carrier:colis-prive), [Aramex Australia](carrier:aramex-au) e [Aramex New Zealand](carrier:aramex-nz) e mostra «360Lion tem esta encomenda.» quando a 360Lion responde. Colar um link `toms.360lion.com/track?trackNumber=` seleciona diretamente a 360Lion; caso contrário toca em «Alterar» ao adicionar ou em «Alterar transportadora» na página da encomenda e seleciona 360Lion (pesquisar «360Lion Express», «WSH» ou «纬狮物流» também a encontra).

O Peek lê diretamente o rastreio da 360Lion e mostra a etapa («Etiqueta criada», «Aceite pela transportadora», «Em trânsito», «Na alfândega», «Devolvida ao remetente» ou «Entregue»), o texto de cada registo (o da transportadora local quando a 360Lion o retransmite, ou o inglês da 360Lion), o local como nome de país ou código de aeroporto de três letras, o país de destino e a hora de entrega. Cada registo traz o fuso horário do local, como `+8` na China e `-3` na Argentina, sendo apresentado na hora do teu dispositivo. O Peek omite a barra de progresso do site, o país de origem, o contador de dias e a prova de entrega; o rastreio da 360Lion não indica data prevista, nome de serviço nem peso.

Enquanto manténs a encomenda aberta no Peek, ou no dia da entrega com as notificações ativas, o Peek verifica até de 10 em 10 minutos enquanto o rastreio da 360Lion responder. Nos restantes casos verifica de hora em hora. Após 12 horas só em «Etiqueta criada» ou «Aceite pela transportadora», ou dois dias sem novidades, verifica de hora em hora seja como for, e de 6 em 6 horas após sete dias em «Etiqueta criada». Quando o rastreio da 360Lion não responde e o Peek acompanha a encomenda apenas por serviços de rastreio, verifica de hora em hora, de 3 em 3 horas após um dia sem novidades e até de 15 em 15 minutos no dia de distribuição enquanto a mantiveres aberta ou com notificações ativas.

Quando o último registo tem quatro dias e a encomenda continua em «Etiqueta criada», «Aceite pela transportadora» ou «Em trânsito», o Peek assinala-o com a data desse registo, como «Sem atualizações desde seg., 12/10». A página mostra então: «É habitual uma encomenda ficar sem novidades em voos longos, na alfândega ou ao fim de semana. O Peek continua a perguntar a 360Lion.», com a ligação «Pede ajuda a 360Lion» para a página de contactos da 360Lion. Após três semanas sem novos registos, mostra: «Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, contacta o vendedor. O Peek continua a perguntar a 360Lion.»

Podes escolher os alertas «Todos os registos», «Passos importantes» ou «Só a entrega». O Peek apenas acompanha o rastreio e não pode desalfandegar encomendas nem contactar a 360Lion ou o parceiro local de entrega por ti.

## Perguntas sobre o rastreio 360Lion

### O que é a 360Lion Express?

A 360Lion (fundada em 2015 em Shenzhen, China) é uma transportadora de comércio eletrónico transfronteiriço dedicada a linhas aéreas de pequenas encomendas da China para a América Latina e os EUA, utilizada por vendedores na Mercado Libre, Amazon, AliExpress, Shopee, SHEIN e Temu.

### O que significam os códigos `WSH...YQ` e `AR...`?

`WSH` + duas letras de país + 10 dígitos + `YQ` (por exemplo `WSHMX4829173640YQ`, dígitos inventados) é o formato de linha transfronteiriça dedicada da 360Lion, enquanto `AR` + 10 dígitos (`AR4829173640`) é usado em encomendas 360Lion destinadas à Argentina.

### Quem entrega localmente as encomendas da 360Lion?

A 360Lion transporta as encomendas de avião da China até ao aeroporto do teu país e entrega-as (`handover to lastmile`) a um serviço postal ou transportadora local para a entrega à porta. Os registos do parceiro local aparecem no fluxo de rastreio da 360Lion.

### Como posso contactar a 360Lion sobre uma encomenda desaparecida?

Fala primeiro com o vendedor ou marketplace para proteção ao comprador e reembolsos. Podes também enviar e-mail ao serviço de consulta de encomendas da 360Lion em `customerservice@360lion.com` ou ligar para o escritório de Shenzhen através do `+86 755 2360 1325`.

:::sources
- [360Lion Express: English Home](https://www.360lion.com/en/) – fundada em 2015, filiais Shenzhen/Xangai/Cantão/Yiwu/Dongguan/Hong Kong, rastreio de 50 números, parceiros marketplace e morada da sede
- [360Lion: Chinese Home](https://www.360lion.com/) – rotas dedicadas para pequenas encomendas em 15 países latino-americanos e EUA, capacidade aérea charter e armazéns no exterior
- [360Lion: Contact Us](https://www.360lion.com/?m=home&c=Lists&a=index&tid=79) – `customerservice@360lion.com`, `info@360lion.com`, telefones e escritórios Shenzhen/Cantão/Xangai (URL de ajuda ligado pelo Peek)
- [360Lion: About Us](https://www.360lion.com/?m=home&c=Lists&a=index&tid=78) – apresentação da empresa
- [360Lion: Dedicated Small Parcels](https://www.360lion.com/?m=home&c=Lists&a=index&tid=90) – serviço B2C de pequenas encomendas transfronteiriças
- [360Lion: FAQ & Terms](https://www.360lion.com/?m=home&c=Lists&a=index&tid=82) – condições para vendedores, artigos proibidos e reclamação de vendedores em 30 dias
- [360Lion: Tracking Portal](https://toms.360lion.com/track) – portal público de rastreio
- [Universal Parcel Scraper: 360lion](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/360lion) – deteção `WSH` e `AR`, regras de link `toms.360lion.com`, códigos `WSO`/`WSD`, fusos horários explícitos, campos omitidos e URL de ajuda
:::
