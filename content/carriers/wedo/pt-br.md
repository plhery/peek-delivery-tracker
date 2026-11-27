---
title: Rastreio One by Allegro CZ (WE|DO): códigos e entregas
description: Rastreie encomendas One by Allegro CZ (WE|DO, IN TIME, Uloženka) na Tchéquia: códigos de 11 caracteres, retirada One Box 48h, One Point e suporte.
slug: one-by-allegro-cz-tracking
published: 2026-11-27
updated: 2026-11-27
---

A One by Allegro CZ é a transportadora tcheca do grupo Allegro (seu site, [onedelivery.cz](https://onedelivery.cz/), pertence à Allegro Retail a.s.). Ela entrega pedidos de allegro.cz e de outras lojas virtuais em três modalidades: **One Box** (mais de 5.244 armários automáticos, incluindo parceiros AlzaBox e OX Box), **One Point** (1.960 lojas parceiras de retirada) e **One Courier** (entrega residencial em toda a Tchéquia com mais de 350 vans e entregadores). A busca do Peek também a encontra como WE|DO, IN TIME ou Uloženka.

Você pode consultar um pacote tcheco em [onedelivery.cz/sledovani-zasilky](https://onedelivery.cz/sledovani-zasilky) ou colar seu código de 11 caracteres na caixa acima.

## Formato do código One by Allegro CZ (WE|DO)

Envios tchecos da One by Allegro / WE|DO utilizam um **código alfanumérico compacto de 11 caracteres**:

| Formato (inventado) | O que é |
| --- | --- |
| `2BM48291736` | 1 dígito + 1 letra + 1 letra/dígito + 8 dígitos (11 caracteres) — código padrão One by Allegro CZ (WeDo). |
| `48P48291736` | 2 dígitos + 1 letra + 8 dígitos (11 caracteres) — formato alternativo tcheco da One by Allegro (WeDo). |

Prefixos iniciados por `7D` + dígito ou `7X` + dígito ficam excluídos; todos os demais códigos de 11 caracteres compatíveis identificam **One by Allegro CZ** sem ambiguidade e com alta confiança. O Peek também lê links diretos de `onedelivery.cz/sledovani-zasilky?numer=...`, `onedelivery.cz/?numer=...`, `trace.wedo.cz/index.php?orderNumber=...` e `trace.intime.cz/index.php?orderNumber=...`. Pacotes poloneses da Allegro One usam rastreador separado — veja [Allegro One](carrier:allegro-one) e o [guia de formatos de código de rastreio](guide:tracking-number-formats).

## Significado dos status One by Allegro CZ

A API pública da One by Allegro CZ devolve mensagens em inglês, cada uma com horário e deslocamento UTC. Veja as habituais:

| Mensagem One by Allegro CZ | O que significa |
| --- | --- |
| `Parcel has been prepared by the sender` | A loja gerou a etiqueta; o entregador ainda não coletou o pacote. |
| `Parcel has been picked up by the courier` | A One by Allegro CZ coletou o pacote no remetente. |
| `Parcel has been dispatched to its destination point` | Saiu do centro de origem rumo à região de destino ou rede de retirada. |
| `Parcel has been accepted at the sorting center` | Processado em um centro de triagem One by Allegro. |
| `Parcel has been accepted at the branch` | Chegou à unidade local que atende seu endereço ou ponto de retirada. |
| `Parcel has been released for delivery` | Carregado na van para entrega em domicílio ou depósito no One Box / One Point hoje. |
| `Parcel is awaiting pick-up` | Pronto para retirada no armário One Box ou loja One Point (veja o código PIN no SMS, e-mail ou app). |
| `Parcel has been delivered` | Entregue ao destinatário ou retirado no armário ou loja parceira. |

Se o rastreador devolver uma mensagem fora do padrão que possa conter dados pessoais do destinatário, o Peek a substitui por `Tracking update` preservando a data e hora no histórico.

## Regras de entrega One Box, One Point e One Courier

A One by Allegro CZ publica prazos claros de retirada e regras de entrega em [onedelivery.cz](https://onedelivery.cz/):

- **Armários One Box ([onedelivery.cz/one-box](https://onedelivery.cz/one-box)):** acesso **24 horas por dia, 7 dias por semana** em mais de 5.244 armários (One Box, AlzaBox e OX Box). Retire em até **48 horas** após o depósito digitando o telefone do destinatário e o PIN numérico recebido por SMS, e-mail ou pelos apps One by Allegro ou Allegro (não é obrigatório ter app na tela). Pagamento na entrega (*dobírka*) pode ser quitado pelo link no aviso ou no terminal de cartão do One Box.
- **Pontos One Point ([onedelivery.cz/one-point](https://onedelivery.cz/one-point)):** em 1.960 lojas parceiras durante o horário comercial, o prazo padrão é de **5 dias úteis** (prorrogável gratuitamente em até **3 dias úteis extras** pelo atendimento; na alta temporada pré-Natal, a guarda no One Point cai para **3 dias**, enquanto o One Box segue em 48 horas). Limites do One Point: **20 kg**, maior lado **1,2 m** e volume **0,2 m³**. COD pode ser pago em dinheiro ou cartão nas lojas com maquininha.
- **Entrega domiciliar One Courier ([onedelivery.cz/one-courier](https://onedelivery.cz/one-courier)):** entrega padrão no **próximo dia útil (`D+1`)** após a coleta, em **dias úteis das 08h00 às 18h00**, até a primeira porta com tranca (entregadores não são obrigados a subir escadas e não há entrega em caixa postal). No dia da entrega você recebe aviso por SMS, e-mail ou app. COD pode ser pago em dinheiro (pede-se valor exato) ou cartão sem taxa extra. É possível alterar a data ou redirecionar pacotes elegíveis para um ponto de retirada pelo app One by Allegro ou atendimento; trocas de endereço passam pelo atendimento.

Se perder o prazo ou precisar de ajuda para localizar o pacote, consulte os guias de [armários de encomendas](guide:parcel-lockers), [encomendas não retiradas](guide:uncollected-parcel) e [quando o rastreio para de atualizar](guide:tracking-not-updating). Para outras redes tchecas, compare com [Packeta](carrier:packeta), [PPL](carrier:ppl) e [Česká pošta](carrier:ceska-posta).

## Como acompanhar One by Allegro CZ no Peek

Cole seu código de 11 caracteres ou um link de `onedelivery.cz` ou `trace.wedo.cz` no Peek para reconhecer **One by Allegro CZ** na hora. Se precisar trocar a transportadora, use **Trocar** ao adicionar ou **Trocar transportadora** na tela do pacote.

O Peek consulta diretamente o endpoint público da One by Allegro CZ (`edge.onedelivery.cz`) pelo [adaptador One by Allegro CZ de código aberto do universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/wedo), com nova tentativa automática em caso de tempo esgotado. Ele registra carimbos exatos com fuso horário e o instante da entrega (`delivered_at`), descartando textos livres que possam vazar dados pessoais. A API pública não traz previsão de entrega, cidade do depósito nem peso.

O Peek organiza os eventos em etapas: **Etiqueta criada** (`Parcel has been prepared by the sender`), **Recebida pela transportadora** (`Parcel has been picked up by the courier`), **Em trânsito** (expedição, centro de triagem e unidade local), **Saiu para entrega** (`Parcel has been released for delivery`), **Pronta para retirada** (`Parcel is awaiting pick-up`) e **Entregue** (`Parcel has been delivered`), lendo também outros textos como `Parcel has been returned to the sender`.

Enquanto o pacote estiver aberto no Peek, ou no dia da entrega com notificações ligadas, o Peek consulta até a cada 10 minutos; no dia em que exibir `Parcel has been released for delivery`, a cada 2 minutos a partir das 6h no horário tcheco, enquanto o rastreador da One by Allegro CZ responder. Nos demais casos, verifica de hora em hora. Após 12 horas apenas em "Etiqueta criada" ou "Recebida pela transportadora", ou dois dias sem novidades, consulta a cada hora, e a cada 6 horas após sete dias em "Etiqueta criada". Quando a One by Allegro CZ não responde e o Peek acompanha por serviços de rastreamento, consulta de hora em hora, a cada 3 horas após um dia sem novidades e até a cada 15 minutos no dia da saída para entrega.

Quando a leitura mais recente tem quatro dias e o pacote segue em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito", o Peek sinaliza a data dessa leitura, como "Sem atualizações desde seg 12 out". A página do pacote então exibe "É comum uma encomenda ficar sem atualizações em voos longos, na alfândega ou no fim de semana. O Peek continua consultando One by Allegro CZ.", com o link "Pedir ajuda a One by Allegro CZ" para a página de contato da One by Allegro CZ. Após três semanas sem novas leituras, mostra "Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, fale com o vendedor. O Peek continua consultando One by Allegro CZ."

Nos alertas do Peek, escolha **Cada movimentação**, **Etapas importantes** ou **Só a entrega**.

## Como entrar em contato com a One by Allegro CZ

- **Formulário de consulta e suporte:** [onedelivery.cz/kontakt](https://onedelivery.cz/kontakt) (clique em *Dotaz na zásilku* para dúvidas de comprador e vendedor, PIN perdido, prorrogação gratuita de 3 dias no One Point ou alteração de endereço)
- **Página oficial de rastreio:** [onedelivery.cz/sledovani-zasilky](https://onedelivery.cz/sledovani-zasilky)
- **Informações dos serviços:** [One Box](https://onedelivery.cz/one-box), [One Point](https://onedelivery.cz/one-point) e [One Courier](https://onedelivery.cz/one-courier)
- **Site principal:** [onedelivery.cz](https://onedelivery.cz/)

## Perguntas frequentes

### WE|DO, IN TIME e One by Allegro CZ são a mesma transportadora?

O Peek trata todas como uma só: buscar WE|DO, IN TIME ou Uloženka leva à One by Allegro CZ, e links de `trace.wedo.cz` ou `trace.intime.cz` bem como códigos de 11 caracteres rastreiam sob One by Allegro CZ. O site `onedelivery.cz` não detalha o histórico das marcas.

### Por quanto tempo um pacote fica no One Box ou no One Point?

No **One Box** (incluindo AlzaBox e OX Box), os pacotes aguardam **48 horas** após o depósito. Nas lojas **One Point**, o prazo padrão é de **5 dias úteis** (prorrogável gratuitamente em até 3 dias úteis pelo atendimento), reduzido para **3 dias** no pico pré-Natal.

### Posso pagar na entrega (dobírka) com cartão?

Sim. Entregadores One Courier aceitam dinheiro (pede-se valor trocado) ou cartão sem tarifa extra; o One Box aceita cartão pelo link da notificação ou terminal do armário; e o One Point aceita dinheiro ou cartão nas lojas com maquininha.

### O que fazer se perdi meu código de retirada One Box ou One Point?

Os códigos são enviados por SMS, e-mail e nos aplicativos One by Allegro e Allegro. Se não encontrar em nenhum deles, acione o suporte pelo formulário de consulta em [onedelivery.cz/kontakt](https://onedelivery.cz/kontakt).

:::sources
- [One by Allegro CZ (onedelivery.cz)](https://onedelivery.cz/)
- [One by Allegro CZ — Sledování zásilky](https://onedelivery.cz/sledovani-zasilky)
- [One by Allegro CZ — One Box](https://onedelivery.cz/one-box)
- [One by Allegro CZ — One Point](https://onedelivery.cz/one-point)
- [One by Allegro CZ — One Courier](https://onedelivery.cz/one-courier)
- [One by Allegro CZ — Kontakt](https://onedelivery.cz/kontakt)
- [universal-parcel-scraper One by Allegro CZ adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/wedo)
:::
