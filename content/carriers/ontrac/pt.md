---
title: Seguimento OnTrac: números, estados e entregas falhadas
description: Segue uma encomenda OnTrac ou LaserShip nos EUA: formatos C, D, LX e 1LS, significado dos estados, tentativas falhadas, atrasos e apoio via chat Wink.
slug: seguimento-ontrac
published: 2026-11-21
updated: 2026-11-21
---

A OnTrac é uma transportadora norte-americana de última milha que entrega encomendas online para retalhistas e marcas em `35` estados dos EUA e em Washington, D.C. (`70 %` da população norte-americana), integrando a LaserShip desde a fusão de 2021. Podes acompanhar o envio em [ontrac.com/tracking](https://www.ontrac.com/tracking/) com o número de seguimento ou colá-lo diretamente na caixa acima.

## Formato do número de seguimento OnTrac e onde o encontrar

O código consta na página da encomenda ou no e-mail de expedição da loja (o número da encomenda não serve). Segundo as FAQ da OnTrac, os números começam por `C`, `D`, `1LS`, `LS`, `LX` ou `BN` ([encontrar o número de seguimento](guide:find-tracking-number)). Eis os formatos reconhecidos pelo Peek, com algarismos inventados:

| Formato (inventado) | O que é |
| --- | --- |
| `C14829105736497` | `C1` e 13 algarismos terminados num dígito de controlo (15 caracteres): identificado de imediato como OnTrac. |
| `D10482910573645` | `D10` e 12 algarismos com dígito de controlo (15 caracteres): identificado de imediato como OnTrac (`C24829105736495` e outros `C`/`D` válidos de 14 dígitos são confirmados na OnTrac). |
| `LX48291057` | Formato LaserShip: `L`, depois `A`, `E`, `H`, `I`, `N` ou `X` e 8 algarismos (10 caracteres). |
| `1LS482910573649` | Formato LaserShip: `1LS` e 12 a 14 letras ou algarismos. |
| `AD482910573` | Referência do expedidor (`AD` + 9 algarismos), convertida pela OnTrac no seu próprio número quando escolhes a OnTrac. |

O antigo domínio `lasership.com` redireciona agora para `ontrac.com`, onde as remessas LaserShip são seguidas. Para prefixos `LS` ou `BN` (ou outros formatos), pesquisa diretamente em `ontrac.com/tracking/`, pois a seleção manual no Peek indicará que a OnTrac «não emite números como este». Um código acabado de criar pode não mostrar leituras no início: a OnTrac pede para aguardar **1 a 3 dias** após o e-mail de envio, já que alguns expedidores demoram até 24 horas a enviar os dados.

## O que significam os estados de seguimento da OnTrac

O site da OnTrac está apenas em inglês. Estas são as leituras e mensagens de atraso lidas pelo Peek:

| Estado da OnTrac | O que significa para a tua encomenda | O Peek mostra |
| --- | --- | --- |
| `On its way to OnTrac` | A OnTrac recebeu os dados da etiqueta, mas ainda não o pacote (`LABEL CREATED, TRACKING DETAILS WILL BE AVAILABLE ONCE THE PACKAGE ARRIVES AT THE ONTRAC FACILITY` ou `Waiting for package from shipper.`). | «Etiqueta criada» |
| `Origin Scan` | A OnTrac recebeu a encomenda e encaminha-a para o centro local (as passagens intermédias surgem como `Arrived at Facility`). | «Em trânsito» |
| `Arrived at Final Servicing Facility` | Chegou ao centro local responsável pela entrega na tua área. | «Em trânsito» |
| `Loaded onto vehicle` | Carregada num veículo de ligação ou distribuição. | «Em trânsito» |
| `Out for Delivery` | Hoje com o motorista para entrega. | «Em distribuição» |
| `Package Delivered` | Entregue. | «Entregue» |
| `Delivery Pending` | O motorista tentou entregar sem deixar o volume (segurança ou assinatura); a OnTrac voltará a tentar. | «Tentativa de entrega» |
| `Business closed. Please provide hours` | Estabelecimento encerrado; até 3 tentativas, a OnTrac pede o horário de funcionamento. | «Tentativa de entrega» |
| `Incomplete address. Please contact us` | Morada incompleta na distribuição; indica os dados para evitar devolução. | «Tentativa de entrega» |
| `Details needed. Please contact us` | Encomenda retida a aguardar detalhes de morada ou acesso. | «Problema reportado» |
| `Order has been transferred to another carrier` | Transferida para outra transportadora não identificada (embora o cabeçalho em `ontrac.com` diga *Delivered*). | «Em trânsito» |
| `Delivery Delay`, `Delayed. Delivery date updated`, `Delayed by road closure`, `Delayed. Could not reach delivery area` | Atraso operacional, estrada cortada ou área inacessível, com data de entrega atualizada. | «Em trânsito» |
| `Weather delay. Delivery date updated`, `Weather delay. See service alerts` | Atraso meteorológico na rede da OnTrac. | «Em trânsito» |
| `Misrouted. Delivery date updated` | Desvio de triagem corrigido com nova data prevista. | «Em trânsito» |

Mais detalhes em [o que significam os estados de seguimento](guide:tracking-statuses).

## Horários de entrega da OnTrac e entregas falhadas

**Horários.** A OnTrac entrega habitualmente em residências entre as **`08:00` e as `21:00`** (hora local) na data prevista, incluindo **sábados e domingos** nesse mesmo horário em várias áreas servidas (limites standard nos termos adicionais: peso faturável até `50 lbs`, comprimento `48 in`, segundo lado `30 in` e volume `8 640 polegadas cúbicas`).

**Entrega falhada.** A OnTrac faz até **3 tentativas de entrega** (a seguinte em **1 a 2 dias úteis**, nunca no próprio dia) e depois devolve ou trata o pacote conforme as regras de remessas não entregáveis ([falha na entrega](guide:missed-delivery)).

**Instruções de acesso e alteração de morada.** Se surgir uma tentativa quando estavas em casa, confirma a morada na loja e envia códigos de acesso ou instruções ao assistente virtual **Wink** com o teu número de seguimento (se já estiver em distribuição, só deverão aplicar-se na tentativa seguinte). Qualquer **alteração de morada** tem de ser aprovada pelo vendedor ([alterar a morada de entrega](guide:change-delivery-address)).

**Levantamento em centro e alertas SMS.** O site da OnTrac não indica pontos de recolha nem cacifos próprios e a OnTrac só retém um pacote no centro local mediante pedido prévio (leva o número de seguimento e identificação com fotografia). Em `ontrac.com/tracking/`, o botão `Get Text Alerts` (`NotifyMe`) envia SMS na saída para entrega, tentativa e entrega concluída (cancela respondendo `STOP` para `(469) 529-1133`); a página pode também mostrar a foto de entrega (`VPOD`).

## Encomenda OnTrac atrasada, parada ou dada como entregue

- **Sem atualizações:** se o estado não mudar durante **3 dias úteis**, a OnTrac recomenda contactar primeiro a loja e depois o assistente **Wink** ([porque é que o seguimento não atualiza](guide:tracking-not-updating)).
- **Dada como entregue mas não está:** verifica a morada e a fotografia em `ontrac.com/tracking/` (ou fala com a administração do condomínio se foi deixada num cacifo), escolhe `Tracking shows delivered; no package` no formulário `Have a question?` ou abre um pedido no **Wink**. Receberás um número de processo por e-mail com análise em até **48 horas**; se passarem mais de **2 dias úteis** sem resposta, contacta o vendedor ([entregue mas não recebido](guide:delivered-not-received)).
- **Danificada ou perdida:** segundo os termos da OnTrac, as reclamações formais são submetidas pelo expedidor (`cargoclaims@ontrac.com`); pede substituição ou reembolso à loja ([encomenda danificada](guide:damaged-parcel), [encomenda perdida](guide:lost-parcel)).

## Apoio ao cliente da OnTrac: chat, SMS e horários

Nas páginas [Support](https://www.ontrac.com/support/) e [FAQ](https://www.ontrac.com/faq/), a OnTrac não divulga linha telefónica de voz nem e-mail para destinatários, disponibilizando:

- **Chat com o Wink:** ícone vermelho no canto inferior direito de `ontrac.com` (exige número de seguimento válido).
- **Linha de SMS:** envia `start` por SMS para **`703-348-8441`** (apenas texto para seguir a encomenda ou abrir processo; tarifas normais do operador; `STOP` para sair).
- **Formulário web:** secção `Have a question?` por baixo do resultado em `ontrac.com/tracking/`.
- **Horário de atendimento:** segunda a sexta das `08:00` às `21:00` (hora de Leste dos EUA, *Eastern Time*) e sábado e domingo das `08:00` às `20:00` (*Eastern Time*); encerrado no dia de Natal, 26 de dezembro e Ano Novo.

Para detetares mensagens fraudulentas, lê [mensagens falsas de entregas](guide:delivery-scam-texts).

## Seguir encomendas OnTrac com o Peek

O [Peek](/) identifica logo a OnTrac («Transportadora detetada») para números `C1` e `D10` que passam o dígito de controlo da OnTrac, códigos LaserShip `LX` e `1LS` e ligações de `ontrac.com` com `number=`. Noutros `C` ou `D` válidos de 14 algarismos mostra «A verificar serviços de seguimento…» e depois «OnTrac tem esta encomenda.». Para uma referência `AD` de 9 algarismos, toca em «Alterar» e escolhe a OnTrac: a OnTrac encontra o pacote e o Peek mostra também o número próprio da OnTrac («OnTrac segue a encomenda com o número») (antes da primeira leitura surge «À espera de OnTrac»).

O Peek lê o serviço JSON da OnTrac e apresenta o estado, cada leitura com cidade e estado no mapa (até às 100 mais recentes), entrega prevista, hora de entrega, peso e dimensões, deixando de fora destinatário, assinatura e fotografia de entrega. Quando o evento inclui fuso horário, o instante é convertido para o fuso do teu dispositivo («Horas no teu fuso horário: …»); sem fuso, o Peek lê-a na hora do estado norte-americano da leitura e converte-a da mesma forma. `Order has been transferred to another carrier` permanece «Em trânsito» no Peek, sem transição adicional porque a OnTrac não nomeia o operador seguinte.

Com a encomenda aberta ou no dia da entrega com notificações ativas, o Peek verifica até a cada 10 minutos, e até a cada 2 minutos desde as `06:00` locais no dia da leitura `Out for Delivery` enquanto o serviço da OnTrac responder; nos restantes casos, e após 12 horas em «Etiqueta criada» / «Aceite pela transportadora» ou dois dias sem leituras, de hora a hora («Todos os registos», «Passos importantes» ou «Só a entrega»). O Peek não pode contactar a OnTrac nem a loja por ti.

## Perguntas frequentes sobre o seguimento da OnTrac

### Por que letras começam os números de seguimento da OnTrac?

Por `C`, `D`, `1LS`, `LS`, `LX` ou `BN`, segundo a OnTrac; os números `C` e `D` validados pelo Peek têm 15 caracteres (letra mais 14 algarismos).

### Porque é que o meu seguimento da OnTrac não atualiza?

Aguarda 1 a 3 dias após o e-mail de envio para que surjam as primeiras atualizações. Se uma encomenda em curso não mudar de estado durante 3 dias úteis, fala com a loja e depois com o Wink.

### A que horas entrega a OnTrac?

Entre as `08:00` e as `21:00` na tua hora local na data prevista, incluindo sábados e domingos nas áreas com distribuição ao fim de semana.

### A OnTrac tem seguimento em tempo real?

Mostra cada leitura com data, hora e cidade (além de foto de entrega no site) e envia alertas SMS `NotifyMe` na saída para entrega, tentativa e conclusão.

### A OnTrac é fidedigna?

Sim: começou em 1991 como *California Overnight*, fundiu-se em 2021 com a LaserShip (criada em 1986), opera mais de `102` instalações e `18` centros de triagem nos EUA e é transportadora recomendada para vendedores Amazon MFN.

:::sources
- [OnTrac: FAQ](https://www.ontrac.com/faq/) – prefixos, entregas 08:00–21:00 e fim de semana, novas tentativas em 1–2 dias úteis, Wink, SMS 703-348-8441, 102+ instalações e 18 centros de triagem
- [OnTrac: Support](https://www.ontrac.com/support/) – prazo inicial de 1 a 3 dias, averiguação em 48 horas, NotifyMe e horários/feriados (incluindo 26 de dezembro)
- [OnTrac: Tracking](https://www.ontrac.com/tracking/) – formulário de seguimento, foto de entrega e formulário Have a question?
- [OnTrac: Script da página de seguimento](https://www.ontrac.com/wp-content/themes/ontrac/assets/js/ontrac-package-status.js) – títulos de estado, colunas e motivos de pedido
- [OnTrac: Códigos de estado](https://www.ontrac.com/wp-content/themes/ontrac/assets/data/statusCodes.json) – significado de tentativas, retenções, transferências e atrasos
- [OnTrac: Additional Terms & Conditions](https://www.ontrac.com/terms-conditions-additional/) – 3 tentativas, limites de 50 lbs / 48 in e reclamações apresentadas pelo expedidor
- [OnTrac: About OnTrac](https://www.ontrac.com/about/) – fundação da LaserShip em 1986 e da OnTrac em 1991, fusão em 2021 e rede transcontinental
- [OnTrac: Residential E-Commerce Delivery](https://www.ontrac.com/residential-e-commerce-delivery/) – 35 estados e D.C., cobertura de 70 % dos EUA e comprovativo fotográfico VPOD
- [OnTrac: Amazon MFN Sellers](https://www.ontrac.com/amazon-mfn-sellers/) – transportadora preferencial para vendedores Amazon MFN
- [OnTrac: NotifyMe Terms of Use](https://www.ontrac.com/notifyme-terms-of-use/) – alertas SMS e cancelamento com STOP para (469) 529-1133
- [Universal Parcel Scraper: OnTrac](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/ontrac) – formatos C, D, LX, 1LS e referência AD, 18 descrições de estado, relógios, peso/dimensões e transferência NFRP
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – deteção de transportadoras e serviços de seguimento
:::
