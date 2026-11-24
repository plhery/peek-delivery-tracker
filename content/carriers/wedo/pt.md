---
title: Rastreio One by Allegro CZ (WE|DO): códigos e levantamento
description: Segue encomendas One by Allegro CZ (WE|DO, IN TIME, Uloženka) na Chéquia: códigos de 11 caracteres, One Box 48 h, One Point 5 dias e apoio.
slug: rastreio-one-by-allegro-cz
published: 2026-11-24
updated: 2026-11-24
---

A One by Allegro CZ é a transportadora checa do grupo Allegro (o seu site, [onedelivery.cz](https://onedelivery.cz/), pertence à Allegro Retail a.s.). Entrega encomendas da allegro.cz e de outras lojas online através de três serviços: **One Box** (mais de 5 244 cacifos automáticos incluindo parceiros AlzaBox e OX Box), **One Point** (1 960 pontos de recolha parceiros) e **One Courier** (entrega ao domicílio em toda a Chéquia com mais de 350 carrinhas e estafetas). A pesquisa do Peek também a encontra como WE|DO, IN TIME ou Uloženka.

Podes consultar uma encomenda checa em [onedelivery.cz/sledovani-zasilky](https://onedelivery.cz/sledovani-zasilky) ou colar o teu número de 11 caracteres na caixa acima.

## Formato do número de rastreio One by Allegro CZ (WE|DO)

Os envios checos da One by Allegro / WE|DO usam um **código alfanumérico compacto de 11 caracteres**:

| Formato (inventado) | O que é |
| --- | --- |
| `2BM48291736` | 1 dígito + 1 letra + 1 letra/dígito + 8 dígitos (11 caracteres no total) — número padrão One by Allegro CZ (WeDo). |
| `48P48291736` | 2 dígitos + 1 letra + 8 dígitos (11 caracteres no total) — formato alternativo checo One by Allegro (WeDo). |

Os prefixos iniciados por `7D` + dígito ou `7X` + dígito ficam excluídos desta regra; todos os outros códigos compatíveis de 11 caracteres identificam **One by Allegro CZ** sem ambiguidade com elevada confiança. O Peek também lê ligações diretas de `onedelivery.cz/sledovani-zasilky?numer=...`, `onedelivery.cz/?numer=...`, `trace.wedo.cz/index.php?orderNumber=...` e `trace.intime.cz/index.php?orderNumber=...`. As encomendas polacas da Allegro One usam outro rastreio — vê [Allegro One](carrier:allegro-one) e o guia de [formatos de números de seguimento](guide:tracking-number-formats).

## O que significam os estados da One by Allegro CZ

A API pública da One by Allegro CZ devolve textos em inglês, cada um com a hora e o desfasamento em relação a UTC. Estes são os mais comuns:

| Texto One by Allegro CZ | O que significa |
| --- | --- |
| `Parcel has been prepared by the sender` | A loja criou a etiqueta de envio; o estafeta ainda não recolheu a encomenda. |
| `Parcel has been picked up by the courier` | A One by Allegro CZ recolheu a encomenda ao remetente. |
| `Parcel has been dispatched to its destination point` | Saiu do hub de origem rumo à região ou rede de recolha de destino. |
| `Parcel has been accepted at the sorting center` | Processada num centro de triagem One by Allegro. |
| `Parcel has been accepted at the branch` | Chegou ao depósito local que serve a tua morada ou ponto de recolha. |
| `Parcel has been released for delivery` | Carregada numa carrinha para entrega ao domicílio ou colocação no teu One Box / One Point hoje. |
| `Parcel is awaiting pick-up` | Pronta para levantamento no teu cacifo One Box ou loja One Point (vê o PIN no SMS, email ou aplicação). |
| `Parcel has been delivered` | Entregue ao destinatário ou levantada no cacifo ou ponto de recolha. |

Se o rastreio devolver uma mensagem fora do padrão que possa conter dados pessoais do destinatário, o Peek substitui essa linha por `Tracking update` mantendo a data e hora no histórico.

## Regras de entrega One Box, One Point e One Courier

A One by Allegro CZ publica prazos claros de levantamento e regras de distribuição em [onedelivery.cz](https://onedelivery.cz/):

- **Cacifos One Box ([onedelivery.cz/one-box](https://onedelivery.cz/one-box)):** acessíveis **24/7** em mais de 5 244 cacifos (One Box, AlzaBox e OX Box). Levanta em **48 horas** após a colocação no cacifo introduzindo o número de telefone do destinatário e o PIN numérico enviado por SMS, email, aplicação One by Allegro ou aplicação Allegro (não é preciso aplicação no ecrã do cacifo). A cobrança na entrega (*dobírka*) pode ser paga pela ligação na notificação ou no terminal de cartões da One Box.
- **Pontos One Point ([onedelivery.cz/one-point](https://onedelivery.cz/one-point)):** em 1 960 lojas parceiras durante o horário comercial, as encomendas aguardam normalmente **5 dias úteis** (prolongáveis gratuitamente até **3 dias úteis adicionais** através do apoio ao cliente; na época alta antes do Natal o prazo One Point reduz-se de 5 para **3 dias**, mantendo-se One Box nas 48 horas). Os limites máximos One Point são **20 kg**, lado mais comprido **1,2 m** e volume **0,2 m³**. A cobrança na entrega paga-se em numerário ou cartão quando a loja tem terminal.
- **Entrega ao domicílio One Courier ([onedelivery.cz/one-courier](https://onedelivery.cz/one-courier)):** entrega padrão no **dia útil seguinte (`D+1`)** à recolha, em **dias úteis entre as 08h00 e as 18h00**, até à primeira porta fechada à chave (os estafetas não são obrigados a subir escadas e não há entrega em apartados postais). No dia da entrega recebes aviso por SMS, email ou app. A cobrança na entrega pode ser paga em numerário (valor exato pedido) ou cartão sem taxa extra. Podes mudar a data de entrega ou redirecionar uma encomenda elegível para ponto de recolha na app móvel One by Allegro ou pelo apoio ao cliente; alterações de morada passam pelo apoio ao cliente.

Se perderes um prazo de levantamento ou precisares de localizar uma encomenda, consulta os guias sobre [cacifos de encomendas](guide:parcel-lockers), [encomenda não levantada](guide:uncollected-parcel) e [quando o rastreio deixa de atualizar](guide:tracking-not-updating). Para outras redes checas, compara com [Packeta](carrier:packeta), [PPL](carrier:ppl) e [Česká pošta](carrier:ceska-posta).

## Acompanhar a One by Allegro CZ com o Peek

Cola o teu código de 11 caracteres One by Allegro CZ, ou uma ligação `onedelivery.cz` ou `trace.wedo.cz`, no Peek e o Peek reconhece logo **One by Allegro CZ**. Se precisares de mudar a transportadora, usa «Alterar» ao adicionar ou «Alterar transportadora» no ecrã de rastreio.

O Peek consulta diretamente o endpoint público da One by Allegro CZ (`edge.onedelivery.cz`) através do [adaptador One by Allegro CZ em código aberto no universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/wedo), com repetição automática em caso de tempo limite de rede. Regista horários exatos com o desfasamento em relação a UTC e o instante da entrega (`delivered_at`), descartando mensagens livres que possam expor dados pessoais. A API pública não inclui data estimada de entrega, cidade do depósito nem peso.

O Peek traduz estes textos em etapas: «Etiqueta criada» (`Parcel has been prepared by the sender`), «Aceite pela transportadora» (`Parcel has been picked up by the courier`), «Em trânsito» (expedição, centro de triagem e filial), «Em distribuição» (`Parcel has been released for delivery`), «Pronta para levantamento» (`Parcel is awaiting pick-up`) e «Entregue» (`Parcel has been delivered`). Lê também outros textos da Allegro One, como `Parcel has been returned to the sender`.

Enquanto tens a encomenda aberta no Peek, ou no dia da entrega com as notificações ativadas, o Peek consulta até de 10 em 10 minutos; no dia em que a One by Allegro CZ apresenta `Parcel has been released for delivery`, de 2 em 2 minutos a partir das 6h00 hora checa enquanto o rastreio responder. Caso contrário, o Peek verifica de hora a hora. Após 12 horas apenas em «Etiqueta criada» ou «Aceite pela transportadora», ou dois dias sem novidades, verifica de hora a hora independentemente da forma como acompanhas a encomenda, e de 6 em 6 horas após sete dias em «Etiqueta criada». Quando a One by Allegro CZ não responde e o Peek acompanha a encomenda apenas através de serviços de rastreio, verifica de hora a hora, de 3 em 3 horas após um dia sem novidades e até de 15 em 15 minutos no dia em que a encomenda está em distribuição, enquanto tiveres a encomenda aberta ou as notificações ativadas.

Quando o registo mais recente tem quatro dias e a encomenda continua em «Etiqueta criada», «Aceite pela transportadora» ou «Em trânsito», o Peek assinala-a com o dia desse registo, como «Sem atualizações desde seg 12 out». A página da encomenda apresenta então «É habitual uma encomenda ficar sem novidades em voos longos, na alfândega ou ao fim de semana. O Peek continua a perguntar a One by Allegro CZ.», com a ligação «Pede ajuda a One by Allegro CZ» para a página de contactos. Passadas três semanas sem novos registos, mostra «Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, contacta o vendedor. O Peek continua a perguntar a One by Allegro CZ.»

Nas definições de alertas do Peek escolhe «Todos os registos», «Passos importantes» ou «Só a entrega» para receber as notificações que pretendes.

## Contactar a One by Allegro CZ

- **Formulário de pedido e apoio:** [onedelivery.cz/kontakt](https://onedelivery.cz/kontakt) (clica em *Dotaz na zásilku* para questões de comprador/vendedor, PIN de levantamento perdido, prolongamento gratuito de 3 dias em One Point ou alteração de morada)
- **Página oficial de rastreio:** [onedelivery.cz/sledovani-zasilky](https://onedelivery.cz/sledovani-zasilky)
- **Informação de serviços:** [One Box](https://onedelivery.cz/one-box), [One Point](https://onedelivery.cz/one-point) e [One Courier](https://onedelivery.cz/one-courier)
- **Site principal:** [onedelivery.cz](https://onedelivery.cz/)

## Perguntas frequentes

### WE|DO, IN TIME e One by Allegro CZ são a mesma transportadora?

O Peek trata-as como uma só: a pesquisa encontra One by Allegro CZ como WE|DO, IN TIME ou Uloženka, e ligações `trace.wedo.cz` e `trace.intime.cz` bem como códigos de 11 caracteres são todos seguidos sob One by Allegro CZ. O site `onedelivery.cz` não conta a história das marcas.

### Quanto tempo fica uma encomenda no One Box ou num One Point?

No **One Box** (incluindo AlzaBox e OX Box), as encomendas aguardam **48 horas** após a entrega no cacifo. Nas lojas **One Point**, o prazo padrão é de **5 dias úteis** (prolongável gratuitamente até 3 dias úteis via apoio ao cliente), reduzido para **3 dias** na época pré-natalícia.

### Posso pagar a cobrança na entrega (dobírka) com cartão?

Sim. Os estafetas One Courier aceitam numerário (valor exato pedido) ou cartão sem comissão para o destinatário; o One Box aceita cartão pela ligação de pagamento ou no terminal do cacifo; e o One Point aceita numerário ou cartão quando a loja tem terminal.

### E se perdi o meu código de levantamento One Box ou One Point?

Os códigos de levantamento são enviados por SMS, email e nas aplicações One by Allegro e Allegro. Se não o encontrares em lado nenhum, contacta o apoio ao cliente através do formulário em [onedelivery.cz/kontakt](https://onedelivery.cz/kontakt).

:::sources
- [One by Allegro CZ (onedelivery.cz)](https://onedelivery.cz/)
- [One by Allegro CZ — Sledování zásilky](https://onedelivery.cz/sledovani-zasilky)
- [One by Allegro CZ — One Box](https://onedelivery.cz/one-box)
- [One by Allegro CZ — One Point](https://onedelivery.cz/one-point)
- [One by Allegro CZ — One Courier](https://onedelivery.cz/one-courier)
- [One by Allegro CZ — Kontakt](https://onedelivery.cz/kontakt)
- [universal-parcel-scraper One by Allegro CZ adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/wedo)
:::
