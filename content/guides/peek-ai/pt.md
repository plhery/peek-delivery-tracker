---
title: Quando chega a minha encomenda? A estimativa do Peek IA
description: O Peek IA é a estimativa do próprio Peek de quando a tua encomenda vai chegar, ao lado da data da transportadora. Quando aparece, quanto acerta e como o desativar.
slug: quando-chega-a-minha-encomenda
picture: O Pip, a encomenda com cara, a espreitar um telemóvel onde o cartão de uma encomenda mostra uma pequena estrela e «Peek IA até às 16:00» por baixo da data da transportadora.
published: 2026-11-24
updated: 2026-11-25
---

Quando o [Peek](/) tem uma pista forte sobre quando a tua encomenda vai chegar, o Peek IA di-lo ao lado da data da transportadora, ou no lugar dela quando a transportadora não indica nenhuma: «provavelmente até às 16:00», «provavelmente hoje ou amanhã», «talvez antes» ou «hoje é pouco provável». É uma estimativa do próprio Peek, nunca uma promessa da transportadora, e a maioria das encomendas não mostra nenhuma.

Está ligado por predefinição, com ou sem conta, em peektracker.com, na app para iPhone, iPad e Mac e na [beta para Android](guide:android-app), que se instala a partir do teste no Google Play, do repositório F-Droid do próprio Peek ou do GitHub.

:::steps
- Olha para a tua lista | Por baixo da data da transportadora, no cartão de uma encomenda, ou ao lado do estado em «A seguir»: «✦ Peek IA» e poucas palavras, como «até às 16:00».
- Abre a encomenda | Um cartão curto diz, por exemplo, «✦ Peek IA provavelmente até às 16:00», com uma linha a explicar porquê.
- Toca em (i) | O botão (i) («Sobre o Peek IA») abre a folha «Peek IA»: a estimativa, com o que diz a transportadora por baixo, como «DPD diz hoje».
- Lê os pormenores | Muitas vezes, os dias ou as horas prováveis, sombreados conforme a probabilidade; depois, «Porquê», «Fiabilidade» e o interruptor «Mostrar o Peek IA».
:::

O widget «Encomendas» no [iPhone](guide:iphone-app) e no Android mostra-a onde couber, e os [assistentes ligados](guide:ai-assistants) recebem-na como estimativa do Peek IA, não da transportadora. As [notificações](guide:parcel-notifications), os e-mails de entrega, os passaportes dos amigos e a pré-visualização de uma ligação partilhada não a incluem. A [demonstração do Peek](https://peektracker.com/demo) tem encomendas de exemplo com uma estimativa.

## Quando chega a minha encomenda? O que o Peek IA te pode dizer

O Peek IA só diz alguma coisa quando tem uma de cinco pistas, cada uma das quais acertou pelo menos 8 vezes em cada 10 nas encomendas que o Peek seguiu.

| A pista | O Peek IA diz | A linha do porquê, por exemplo |
| --- | --- | --- |
| Em distribuição, sem hora da transportadora | «provavelmente até às 16:00» | «Em entrega desde as 09:12. DPD não indica hora.» |
| A transportadora diz hoje; à tarde ainda não saiu para entrega | «hoje é pouco provável» | «DPD diz hoje, mas a encomenda ainda não saiu para entrega.» |
| A data da transportadora era ontem; uma leitura nas últimas 24 horas | «provavelmente hoje ou amanhã» | «A data de DPD era ontem, mas a encomenda voltou a avançar.» |
| A data da transportadora está a uns dias; encomendas como esta costumam chegar antes | «talvez antes» | «DPD diz sex 27 nov. A encomenda vai adiantada.» |
| Sem data da transportadora | «provavelmente até qui 26 nov», «provavelmente esta semana» e outras | «UPS não indica data. Chegou à alfândega ontem.» |

A DPD e a UPS estão no lugar da tua transportadora. Num cartão, «hoje é pouco provável» fica só «hoje não», ou «pouco provável» ao lado de uma data da transportadora que é hoje. As horas seguem o teu formato de 12 ou 24 horas.

Cada estimativa desaparece quando acaba, mesmo numa página deixada aberta: «hoje é pouco provável» à meia-noite, «provavelmente até às 16:00» às 16:00, «talvez antes» na data da transportadora, e a de um dia, quando esse dia termina.

### Porque é que a minha encomenda não mostra nenhuma estimativa?

Normalmente, porque o Peek não tem uma pista forte. O Peek IA também fica calado:

- quando a encomenda já foi entregue, está arquivada, espera num ponto de levantamento ou está a ser devolvida, ou depois de uma tentativa de entrega (vê [encomenda não entregue](guide:missed-delivery));
- antes da primeira leitura a sério: uma etiqueta sozinha não chega;
- quando a transportadora tem a entrega entre os passos previstos, ou indica para hoje uma hora ou um intervalo que ainda não passou;
- antes de a encomenda sair para entrega, se o Peek não a consultou há pouco tempo (nas últimas 36 horas, exceto para «hoje é pouco provável»);
- quando a data da transportadora passou há mais de um dia (vê [quando te deves preocupar](guide:tracking-not-updating));
- para «talvez antes» e para as estimativas sem data da transportadora, 45 dias depois da primeira leitura ou 30 dias depois da mais recente.

## A data de entrega da transportadora é fiável? «Talvez antes» e «hoje é pouco provável»

Nem sempre, e o Peek IA di-lo em dois casos: «talvez antes», quando as encomendas com leituras como as da tua costumam chegar antes da data da transportadora, e «hoje é pouco provável», quando a transportadora diz hoje mas à tarde a encomenda ainda não saiu para entrega.

«Talvez antes» pode indicar o primeiro dia provável («talvez antes, a partir de amanhã», «talvez antes, a partir de qui 26 nov») e nunca aparece quando a transportadora indica um intervalo de dias.

## Encomenda em distribuição mas ainda não chegou: ainda chega hoje?

O Peek IA pode dizer-te. «Provavelmente até às 16:00» quer dizer que a maioria das encomendas dessa transportadora que o Peek seguiu chegou até essa hora no dia da entrega; só aparece quando a transportadora não indica hora e a encomenda saiu antes dessa hora. Ao fim do dia, «hoje é pouco provável» quer dizer que poucas chegam tão tarde: «Ainda em entrega a esta hora. Com DPD, poucas chegam tão tarde.» Com algumas transportadoras não diz nada enquanto a encomenda está em distribuição, porque o Peek não confia nas suas leituras «Em distribuição».

As horas habituais de cada transportadora, e porque é que uma carrinha ainda pode andar na rua às 20:00, estão em [a que horas chegam as encomendas](guide:delivery-times); o que quer dizer o estado, em [estados da encomenda](guide:tracking-statuses).

## A transportadora não indica data de entrega: quanto tempo vai demorar?

O Peek IA pode dar um dia ou um intervalo, conforme aquilo de que o seu modelo tiver certeza suficiente: «provavelmente até qui 26 nov», «provavelmente hoje ou amanhã» (só quando a transportadora entrega amanhã), «provavelmente esta semana» (até domingo) ou «provavelmente até ao fim da próxima semana» (até ao domingo seguinte).

Para os prazos habituais das encomendas vindas da China, vê [como seguir uma encomenda da China](guide:tracking-from-china).

## Como é que o Peek IA prevê as entregas e quanto acerta?

O Peek IA é um modelo treinado mais tabelas de regras, no próprio servidor do Peek, e só mostra os tipos de estimativa que acertaram pelo menos 8 vezes em cada 10.

O modelo, um classificador de gradient boosting feito de árvores de decisão, dá a probabilidade de uma encomenda já ter chegado em cada um dos próximos dias de entrega da transportadora. Tem em conta as leituras e onde foram feitas, a transportadora, o serviço, o peso, o fuso horário e o país de destino declarado, e se a encomenda foi consultada sem conta. As tabelas de regras guardam a hora de entrega habitual de cada transportadora e quantas vezes cada tipo de estimativa se confirmou.

O servidor calcula a estimativa sempre que envia os dados da encomenda para o teu telemóvel ou o teu navegador, sem recorrer a nenhum serviço externo; estes só a mostram. Só o servidor do Peek tem o modelo: nem o modelo nem o código que o treina estão no [código aberto do Peek](guide:developers). Não é um chatbot.

«Fiabilidade» diz «Acerta 8 em cada 10 vezes com encomendas como esta.» ou, para «hoje é pouco provável», «Acerta 8 em cada 10 vezes a esta hora.» O número é 8 ou 9: o Peek IA não mostra nada abaixo de 8 em cada 10, nunca diz mais de 9 e arredonda para baixo.

## Como desativo o Peek IA?

Desativa «Peek IA» em «Preferências», nas «Definições» do Peek, ou toca em (i) no cartão do Peek IA de uma encomenda e desativa «Mostrar o Peek IA».

:::steps
- Abre as «Definições» | No site, o botão da tua conta (a tua inicial) ou, sem conta, o botão da roda dentada «Definições». Nas apps, o botão Conta, no topo de Entregas; num Mac, a janela de definições do Peek, separador «Geral».
- Procura o Peek IA | «Peek IA» é a última linha de «Preferências», com o texto «Quando tem uma pista forte, o Peek diz-te quando acha que a encomenda vai chegar.»
- Desativa-o | O Peek IA desaparece dos cartões, de «A seguir», das páginas das encomendas e dos widgets nesse dispositivo.
:::

Com uma conta, a escolha acompanha-te nos outros dispositivos, e os assistentes ligados deixam de receber a estimativa. Sem conta, fica nesse navegador ou nesse dispositivo; [o Peek sem conta](guide:account-or-guest) compara as duas opções.

:::sources
- [Peek: o Peek IA no servidor](https://github.com/plhery/peek-delivery-tracker/blob/main/src/server/peekAi.ts) – as cinco pistas, 8 em cada 10, 36 horas, quando não diz nada
- [Peek: as palavras do Peek IA](https://github.com/plhery/peek-delivery-tracker/blob/main/src/lib/peekAi.ts) – o que dizem os cartões e as páginas, quando acaba cada estimativa, Fiabilidade
- [Peek: o cartão e a folha do Peek IA](https://github.com/plhery/peek-delivery-tracker/blob/main/src/components/PeekAi.tsx) – o cartão, a folha do (i), Mostrar o Peek IA
- [Peek: o modelo](https://github.com/plhery/peek-delivery-tracker/blob/main/src/server/peekAiModel.ts) – árvores de decisão, o que lê, 45 e 30 dias
- [Peek: arquitetura (em inglês)](https://github.com/plhery/peek-delivery-tracker/blob/main/docs/ARCHITECTURE.md) – como funciona o Peek IA, onde não aparece, a escolha da conta
- [Peek: textos em português](https://github.com/plhery/peek-delivery-tracker/blob/main/shared/locales/pt.json) – cada etiqueta do Peek IA citada aqui
- [Peek: Preferências no site](https://github.com/plhery/peek-delivery-tracker/blob/main/src/components/Preferences.tsx) – o interruptor do Peek IA, que acompanha a conta
- [Peek: Definições no iPhone, iPad e Mac](https://github.com/plhery/peek-delivery-tracker/blob/main/ios/PeekDeliveryTracker/SettingsViews.swift) – o Peek IA em Preferências, Geral num Mac
- [Peek: Definições no Android](https://github.com/plhery/peek-delivery-tracker/blob/main/android/app/src/main/kotlin/com/plhery/peekdeliverytracker/ui/settings/SettingsSheet.kt) – o Peek IA em Preferências
- [Peek: ferramentas dos assistentes](https://github.com/plhery/peek-delivery-tracker/blob/main/src/server/assistants/tools.ts) – a estimativa na lista de encomendas de um assistente
- [App Store: Peek](https://apps.apple.com/pt/app/id6823615370) – o Peek IA nas novidades da app
- [Peek: notas do Android (em inglês)](https://github.com/plhery/peek-delivery-tracker/blob/main/fastlane/metadata/android/en-US/changelogs/21.txt) – o Peek IA na beta para Android
- [Peek: Experimenta o Peek para Android](https://peektracker.com/android) – a beta no teste do Google Play, no F-Droid e no GitHub
- [Peek: demonstração](https://peektracker.com/demo) – encomendas de exemplo com uma estimativa
:::
