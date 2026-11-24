---
title: Rastreio BFE Express: códigos CK e WYB, estados e apoio
description: Segue encomendas BFE Express para Argentina, Peru, Uruguai e EUA: números CK e WYB, alfândega, estados de entrega e emails de apoio regionais.
slug: rastreio-bfe-express
published: 2026-11-24
updated: 2026-11-24
---

A BFE Express (*BFEexpress*, operada pela Hongkong Bfe Info Tech Co Limited e fundada em 2005) é uma rede logística transfronteiriça de comércio eletrónico e entrega de última milha que liga vendedores internacionais — incluindo comerciantes da Temu Argentina — a destinatários na Argentina, no Peru, no Uruguai e nos Estados Unidos. A empresa presta serviços de frete internacional, desalfandegamento, armazenagem local e entrega de última milha acompanhados em [bfeexpress.com/tracking](https://www.bfeexpress.com/tracking).

Como a BFE Express gere a chegada internacional e o desalfandegamento antes de entregar as encomendas aos depósitos locais da última milha, um único histórico combina normalmente registos aeroportuários e aduaneiros em inglês com registos de entrega locais em espanhol.

## Formatos de número de rastreio da BFE Express

O Peek aceita números BFE Express com 6 a 50 letras e dígitos. O formulário de contacto da BFE Express aceita estes formatos, por destino:

| Formato (inventado) | Destino e detalhes |
| --- | --- |
| `CK54829173604518` | `CK54` + 12 dígitos (16 caracteres) — Argentina; nenhuma outra transportadora no Peek tem esta forma. |
| `CK548291736045182` | `CK54` + 13 dígitos (17 caracteres) — formato alternativo para a Argentina (coincide em comprimento com a [Colis Privé](carrier:colis-prive)). |
| `CK512829173604518` | `CK512` + 12 dígitos (17 caracteres) — formato Peru (coincide em comprimento com a [Colis Privé](carrier:colis-prive)). |
| `CK598829173604518` | `CK598` + 12 dígitos (17 caracteres) — formato Uruguai (coincide em comprimento com a [Colis Privé](carrier:colis-prive)). |
| `WYB482917360` | `WYB` + 9 dígitos (12 caracteres) — formato de guia para o Peru (sobrepõe-se à [Purolator](carrier:purolator) e à [TNT Australia](carrier:tnt-au)). |

Os dígitos após `CK` seguem o destino: `54` para a Argentina, `512` para o Peru e `598` para o Uruguai. Se colares uma ligação direta no formato `bfeexpress.com/track?track_number=...`, o Peek extrai o código e seleciona a BFE Express de imediato. Para outros padrões internacionais, consulta o guia de [formatos de números de seguimento](guide:tracking-number-formats).

## O que significam os estados de rastreio da BFE Express

O rastreio da BFE Express apresenta dois grupos de linhas — o primeiro troço internacional e a última milha — e o Peek lê ambos.

| Texto da BFE Express | O que significa |
| --- | --- |
| `Arrived at Airport of destination` | O voo que transporta a tua encomenda aterrou no país de destino. |
| `Transport to next facility` | Em deslocação entre o terminal aeroportuário e o depósito local de desalfandegamento ou triagem. |
| `Customs declaration completed` | Desalfandegamento no destino concluído, antes da passagem para a última milha. |
| `Turned over to last mile facility` | Entregue pelo troço aduaneiro da BFE ao depósito local de distribuição. |
| `Se ha recibido el paquete.` / `Tu paquete fue recibido por en: ...` | Recebida no depósito local de última milha na Argentina ou no Uruguai. |
| `En embarque` / `En transporte` / `Tu paquete está en viaje a ...` | Carregada e a circular entre depósitos domésticos em direção à tua cidade. |
| `Tu paquete se encuentra en ...` / `En espera para salir a reparto en ...` | No centro de distribuição local, a aguardar saída numa rota de entrega. |
| `Reparto en curso` | Em distribuição hoje com o estafeta local. |
| `Entregado` | Entregue ao destinatário. Ao lado, o estado próprio da BFE indica `Delivered`. |

As linhas em inglês da BFE Express colocam o local primeiro, antes de uma vírgula (como `<City>, Customs declaration completed`); as linhas em espanhol na Argentina colocam o país primeiro, enquanto a última milha uruguaia indica o local depois de `en` ou `a`. Quando o estado próprio da BFE para a encomenda diz `Delivered`, o Peek marca-a como entregue mesmo que a linha mais recente diga outra coisa.

## Desalfandegamento, entrega de última milha e atrasos

O site da BFE Express oferece envio de encomendas, frete internacional, desalfandegamento, armazenagem local e entrega de última milha, e dirige-se também a vendedores da Temu Argentina. Alguns pontos ajudam a interpretar os prazos:

- **Antes do aeroporto de destino:** se o rastreio da BFE Express ainda não mostrar nada, verifica a página da encomenda no vendedor e lê [quando o rastreio deixa de atualizar](guide:tracking-not-updating).
- **Alfândega e última milha:** `Customs declaration completed` e `Turned over to last mile facility` podem surgir com uma hora de diferença no mesmo local, seguindo-se um dia depois a receção no depósito local (`Se ha recibido el paquete.` ou `Tu paquete fue recibido por en: ...`). Se uma encomenda ficar parada na alfândega de destino, consulta o [guia de alfândega](guide:customs).
- **Dia previsto de entrega:** a BFE Express pode indicar um dia previsto de entrega enquanto a encomenda está a caminho e o Peek mostra-o. O Peek deixa de o mostrar assim que a encomenda é entregue, devolvida ou fica pronta para levantamento, ou quando um registo ultrapassa esse dia.
- **Marcada como entregue ou danificada:** a página de [Apoio ao Cliente da BFE Express](https://www.bfeexpress.com/contactUS) inclui ambos os casos nas FAQ e pede que tenhas o número de rastreio à mão ao contactar a equipa local. Vê também o guia [entregue mas não recebida](guide:delivered-not-received).

Para encomendas domésticas na Argentina tratadas diretamente por transportadoras locais, compara com a [OCA](carrier:oca) e a [Andreani](carrier:andreani).

## Acompanhar a BFE Express com o Peek

Cola o teu código `CK...` ou `WYB...` no Peek, que acompanha mais de 4 500 transportadoras. O Peek nunca pergunta à BFE Express por um número isolado: para um código `CK54` de 16 caracteres não consulta nenhuma transportadora, para um código `CK` de 17 caracteres consulta a [Colis Privé](carrier:colis-prive) e para um código `WYB` consulta a [TNT Australia](carrier:tnt-au) e a [Purolator](carrier:purolator) (primeiro a Purolator no Canadá e primeiro a TNT Australia noutros países). Depois consulta serviços de seguimento («A verificar serviços de seguimento…») e só atribui a encomenda à BFE Express se um serviço de seguimento a indicar sozinha, com registos datados. Por isso, carrega em «Alterar» e escolhe BFE Express em «Corresponde a este número», ou usa «Alterar transportadora» numa encomenda já adicionada. Uma ligação `bfeexpress.com/track?track_number=...` vai de imediato para a BFE Express.

O Peek consulta diretamente a API de rastreio da BFE Express através do [adaptador BFE Express em código aberto no universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/bfe-express). Lê ambos os grupos de linhas por ordem cronológica, usa a cidade, agência ou país como local e mantém o dia previsto de entrega e o país de destino. Uma hora com desfasamento GMT mantém esse instante exato; uma hora sem desfasamento é datada no fuso do local (hora de Buenos Aires para Ezeiza, hora de Lima para Lima, hora de Montevideu para Montevideu), um registo sem local no fuso do registo vizinho, caso contrário na hora de Buenos Aires, e cada registo é apresentado na hora do teu dispositivo. Moradas de rua e fotos de comprovativo de entrega do portal BFE ficam de fora.

O Peek apresenta as linhas da BFE Express como «Em trânsito», exceto `Reparto en curso` («Em distribuição») e `Entregado` («Entregue»). Também marca a encomenda como «Entregue» quando o estado próprio da BFE indica `Delivered`.

Enquanto tens a encomenda aberta no Peek, ou no dia da entrega com as notificações ativadas, o Peek consulta até de 10 em 10 minutos; no dia em que a BFE Express apresenta `Reparto en curso`, de 2 em 2 minutos a partir das 6h00 no relógio do registo mais recente enquanto o rastreio da BFE Express responder. Caso contrário, o Peek verifica de hora a hora. Após 12 horas apenas em «Etiqueta criada» ou «Aceite pela transportadora», ou dois dias sem novidades, verifica de hora a hora independentemente da forma como acompanhas a encomenda, e de 6 em 6 horas após sete dias em «Etiqueta criada». Quando a BFE Express não responde e o Peek acompanha a encomenda apenas através de serviços de rastreio, verifica de hora a hora, de 3 em 3 horas após um dia sem novidades e até de 15 em 15 minutos no dia em que a encomenda está em distribuição, enquanto tiveres a encomenda aberta ou as notificações ativadas.

Quando o registo mais recente tem quatro dias e a encomenda continua em «Etiqueta criada», «Aceite pela transportadora» ou «Em trânsito», o Peek assinala-a com o dia desse registo, como «Sem atualizações desde seg 12 out». A página da encomenda apresenta então «É habitual uma encomenda ficar sem novidades em voos longos, na alfândega ou ao fim de semana. O Peek continua a perguntar a BFE Express.», com a ligação «Pede ajuda a BFE Express» para a página Contact Us da BFE Express. Passadas três semanas sem novos registos, mostra «Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, contacta o vendedor. O Peek continua a perguntar a BFE Express.»

Usa as predefinições de alertas do Peek — «Todos os registos», «Passos importantes» ou «Só a entrega» — para decidir que eventos enviam notificações.

## Contactar a BFE Express

A BFE Express publica emails regionais de apoio ao cliente na página [Contact Us](https://www.bfeexpress.com/contactUS), todos com atendimento de **segunda a sexta-feira, das 9h00 às 18h00** (exceto feriados):

- **Argentina:** `info@ar.bfeexpress.com`
- **Peru:** `info@pe.bfeexpress.com`
- **Uruguai:** `info@uy.bfeexpress.com`
- **Estados Unidos:** `info@us.bfeexpress.com`
- **Paraguai:** `info_py@bfeexpress.com`
- **Outras regiões suportadas (Colômbia, Equador, Sérvia, Domínica):** `info@other.bfeexpress.com`
- **Site oficial e rastreio:** [bfeexpress.com](https://www.bfeexpress.com/) e [bfeexpress.com/tracking](https://www.bfeexpress.com/tracking)

Mantém o teu número completo `CK...` ou `WYB...` à mão quando enviares email à equipa do teu país.

## Perguntas frequentes

### Porque é que o meu rastreio BFE Express começa no aeroporto de destino sem registos anteriores?

O rastreio da BFE Express pode começar em `Arrived at Airport of destination`, a chegada do voo ao país de destino. Antes disso, consulta a página do pedido no vendedor para confirmar o envio.

### Porque é que os meus registos da BFE Express aparecem em inglês e em espanhol?

A BFE Express regista a chegada internacional ao aeroporto, a declaração aduaneira e a passagem para a última milha em inglês, enquanto o depósito doméstico que assegura a entrega final lança os seus registos em espanhol (terminando em `Reparto en curso` e `Entregado`).

### O que significam os dígitos `54`, `512` e `598` nos números de rastreio `CK`?

Identificam o país de destino: `CK54...` é usado para a Argentina (16 ou 17 caracteres), `CK512...` para o Peru (17 caracteres) e `CK598...` para o Uruguai (17 caracteres). Os envios para o Peru também podem usar o formato de 12 caracteres `WYB` + 9 dígitos.

### E se a BFE Express não encontrar o meu número?

Verifica se não falta nenhum carácter no teu código `CK` ou `WYB` e pergunta ao vendedor se já entregou a encomenda à BFE Express.

:::sources
- [BFE Express](https://www.bfeexpress.com/)
- [BFE Express Tracking](https://www.bfeexpress.com/tracking)
- [BFE Express Customer Support](https://www.bfeexpress.com/contactUS)
- [universal-parcel-scraper BFE Express adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/bfe-express)
:::
