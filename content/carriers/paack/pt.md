---
title: Paack rastreio: número, código postal e janela horária
description: Rastreio Paack em Portugal: número de pedido e código postal, o que quer dizer cada estado, a janela horária, os Pontos PaackGo e a ligação à Colis Privé.
slug: paack-rastreio
published: 2026-10-11
updated: 2026-10-27
---

A Paack entrega encomendas de lojas online na última milha, em casa ou num ponto de levantamento, numa janela horária que a página de seguimento mostra. Depois da aproximação concluída no verão de 2026, a Paack e a Colis Privé estão presentes, juntas, em Portugal, Espanha, França, Bélgica e Luxemburgo. Para rastreares uma encomenda Paack, escreve o número de pedido e o código postal de entrega na página de seguimento da Paack, em paack.co, ou cola o número na caixa acima e escolhe Paack.

## Paack rastreio: número de pedido e código postal

A página de seguimento da Paack pede o número de pedido da loja ou o código de barras da etiqueta, sempre com o código postal de entrega. Sem número? Pede-o à loja ou vê [onde encontrar o número de seguimento da encomenda](guide:find-tracking-number).

A maior parte destes números não tem forma fixa, por isso um número de pedido pode parecer de outra transportadora (exemplos inventados):

| Número de pedido | Com o que se parece |
| --- | --- |
| `123456789AB` | Letras e algarismos: nenhuma transportadora em especial. |
| `12345678` | 8 algarismos: também a forma da Mondial Relay, da Colisweb e da Heppner. |
| `1234567890` | 10 algarismos: também a da [DHL](carrier:dhl-express), da Mondial Relay, da Tipsa e de outras. |

Se a loja te diz que é a Paack que entrega, o número é da Paack, tenha a forma que tiver.

## Rastreio encomenda Paack: o que quer dizer cada estado

A página da Paack resume o percurso em quatro passos (dados do pedido recebidos, no centro de distribuição, em distribuição e entregue), e o histórico acrescenta os intermédios. O Peek mostra cada passo com palavras suas, quase todas em português; três aparecem em inglês:

| No Peek | O que quer dizer para ti |
| --- | --- |
| `Dados da encomenda recebidos` | A loja enviou os dados da encomenda à Paack, que ainda não tem a encomenda. |
| `Encomenda recolhida` | Primeira leitura da encomenda pela Paack, no centro de distribuição. |
| `Em trânsito` | A Paack tem a encomenda, que ainda não saiu para entrega. |
| `Courier assigned` | Já há um estafeta atribuído à tua entrega. Volta a aparecer antes de cada nova tentativa. |
| `Em distribuição` | Está com o estafeta, que passa na janela indicada. |
| `Delivery attempt failed`, `Problema de entrega` | Desta vez não foi entregue: o estafeta não conseguiu, ou o Ponto PaackGo estava fechado ou cheio, ou recusou a encomenda. |
| `New delivery attempt scheduled` | A Paack volta a tentar, na nova janela indicada. |
| `Pronta para levantamento` | Está à tua espera no Ponto PaackGo; a página da Paack mostra o último dia para a levantares. |
| `Entregue` | Entregue, ou levantada no Ponto PaackGo. Não a tens? Vê mais abaixo. |
| `Em devolução ao remetente`, `Devolvida ao remetente` | Vai voltar para a loja, ou já voltou: por exemplo, não foi levantada a tempo, ou foi recusada ou não paga no Ponto PaackGo. |
| `Problema com a encomenda` | Um problema com a encomenda, por exemplo danificada ou perdida no Ponto PaackGo: contacta a loja. |

## Entrega Paack: janela horária e pontos de recolha

**A janela horária.** A página de seguimento da Paack mostra o dia e a janela de entrega previstos, na hora do país de entrega. Depois de uma tentativa falhada, a janela passa a ser a da nova tentativa.

**Pontos PaackGo.** É o nome que a Paack dá aos seus pontos de levantamento. Quando a encomenda chega, a página mostra o nome e a morada do ponto, um código de levantamento, um código QR e o último dia para a levantares; depois dessa data, a encomenda volta para a loja.

## Encomenda Paack atrasada, não entregue ou dada como entregue

É a loja que confia a encomenda à Paack e, na UE, o vendedor é responsável por qualquer dano causado aos artigos «desde o momento da expedição até ao momento da entrega». Cancelamentos, reembolsos e encomendas danificadas tratam-se, por isso, com a loja.

- **Atrasada ou sem notícias:** vê [porque é que o tracking não atualiza](guide:tracking-not-updating) e fala com a loja.
- **Reagendada:** é uma nova tentativa; confirma o dia e a janela na página da Paack.
- **Dada como entregue, mas não a tens:** contacta a loja. Mais em [encomenda dada como entregue mas não recebi](guide:delivered-not-received).
- **Nunca chegou:** avisa a loja e dá-lhe um prazo suplementar razoável. Se mesmo assim não entregar, podes rescindir o contrato e ser reembolsado.

## Rastrear encomenda Paack com o Peek

O [Peek](/) reconhece logo como da Paack um número `PK` com 18 algarismos, o das encomendas que a Paack entrega para a Evri International, e segue-o sem código postal. Com um número de etiqueta de `5340` e mais 14 algarismos, que aparece em encomendas da Paack, «Alterar» mostra a Paack em primeiro lugar em «Corresponde a este número», à frente de outras transportadoras que usam 18 algarismos. Com um número de encomenda da SHEIN (`C` ou `E`, uma data de seis algarismos com ano, mês e dia, e mais 8 algarismos), também mostra lá a Paack. Os outros números da Paack não têm uma forma própria, e o Peek não consegue perguntar à Paack se conhece um. Qualquer outro número com letras fica normalmente sem transportadora («o Peek procura-a quando começares a seguir»); um só de algarismos pode ser atribuído a outra transportadora com essa forma, consultada em vez da Paack. Carrega em «Alterar» e escolhe Paack, ou cola uma ligação para a página de seguimento da Paack com `tracking_number=`, do mydeliveries.paack.app ou do paack.co/shipments/tracking.

Depois, preenche o campo «Código postal de entrega». Aparece como opcional porque um número `PK` não precisa dele, mas a Paack só mostra as outras encomendas com ele; um código postal dentro da ligação não conta. O campo aceita o formato português, com hífen. A Paack só responde quando o número e o código postal batem certo, por isso um par errado parece um número desconhecido. O código postal vai para a Paack e, quando é preciso, para serviços de seguimento. O Peek escreve o número em maiúsculas e tira espaços, pontos e hífenes: se nada aparecer, experimenta também na página da Paack.

Vês o estado, o histórico e o dia e a janela previstos, na hora local da morada de entrega. Num Ponto PaackGo, o Peek deve mostrar o nome e a morada do ponto (ainda não visto numa encomenda real), nunca o código de levantamento, o código QR nem o prazo: para esses, usa a página da Paack. Os passos aparecem com as palavras do Peek da tabela acima, e um levantamento num Ponto PaackGo aparece como «Entregue». A Paack não dá o local das leituras nem nomeia outra transportadora, por isso o seguimento fica na Paack. Com um número `PK`, o histórico vem do seguimento da Evri International, com os textos e os locais dele. O nome da loja, o teu nome e a tua morada ficam de fora. O Peek verifica de 10 em 10 minutos durante o dia, de 2 em 2 quando a encomenda sai para entrega e de hora a hora ou de duas em duas horas à noite, e pode enviar-te notificações de todas as leituras, só dos passos importantes ou «Só no dia da entrega».

## Perguntas sobre o rastreio Paack

### A Paack e a Colis Privé são a mesma empresa?

São duas empresas que se juntaram e vão ter o mesmo nome. A 16 de outubro de 2026, a Colis Privé anunciou que, depois da aproximação concluída no verão de 2026, passam a usar uma marca comum, Paack, introduzida aos poucos a partir de janeiro de 2027. Até lá, a entrega e o seguimento das encomendas continuam como de costume: segue a encomenda na transportadora que ta anunciou.

### Quanto tempo demora a Paack a entregar?

O dia e a janela aparecem na página de seguimento da Paack. Enquanto não houver data, conta o prazo da loja: na UE, o vendedor tem 30 dias para entregar, salvo se tiverem combinado outro prazo.

### A Paack tem pontos de recolha em Portugal?

A Paack não diz se os há em Portugal nem onde: chama Pontos PaackGo aos seus pontos de levantamento, mas não publica a lista.

:::sources
- [Paack: Track your order](https://paack.co/shipments/tracking?lng=pt) – Pontos PaackGo
- [Colis Privé: Colis Privé and Paack unite under one brand: Paack](https://colisprive.fr/en/blog/colis-prive-and-paack-unite-under-one-brand-paack/) – anúncio de 16 de outubro de 2026, aproximação, países, marca comum, janeiro de 2027, seguimento sem mudanças
- [Your Europe: Expedição e entrega](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_pt.htm) – 30 dias, danos até à entrega, prazo suplementar e reembolso
- [Universal Parcel Scraper: Paack](https://github.com/plhery/universal-parcel-scraper/blob/main/carriers/paack/README.md) – número de pedido ou código de barras e código postal, formas de número (`PK`, `5340`, SHEIN), passos e palavras que o Peek guarda, janela, Pontos PaackGo
- [Universal Parcel Scraper: estados da Paack](https://github.com/plhery/universal-parcel-scraper/blob/main/carriers/paack/statuses.json) – passos dos Pontos PaackGo, nova tentativa
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – o que o Peek lê
:::
