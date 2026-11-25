---
title: Peek para Android: obtém a beta e o widget Encomendas
description: O Peek para Android está em beta: adere ao teste no Google Play ou instala-o pelo F-Droid ou pelo GitHub. Sem conta. Adiciona o widget e partilha ligações.
slug: app-seguimento-encomendas-android
picture: Um telemóvel Android com o widget Encomendas no ecrã principal, e o Pip, a encomenda com cara, a espreitar por cima do telemóvel.
published: 2026-11-19
updated: 2026-11-25
---

O Peek para Android é uma beta e não está público no Google Play: obténs a app em [peektracker.com/android](https://peektracker.com/android), ao aderires ao teste no Google Play ou ao instalá-la a partir do repositório F-Droid do próprio Peek ou do GitHub. Precisa do Android 12 ou posterior, não pede conta e acrescenta um widget «Encomendas» ao ecrã principal e «Adicionar ao Peek» ao menu de partilha.

Segue encomendas de mais de 4500 transportadoras: cola um número de seguimento, a ligação de uma transportadora ou um e-mail de envio inteiro, e o [Peek](/) descobre a transportadora e mostra o percurso num mapa. É grátis, sem compras integradas nem anúncios. Para um iPhone, iPad ou Mac, vê [o Peek para iPhone, iPad e Mac](guide:iphone-app).

## O Peek está no Google Play?

Só para quem o testa. O Peek está num teste fechado, e a página dele no Google Play não abre para ninguém de fora. A Google abre-o a todos quando pelo menos 12 testadores o tiverem mantido durante 14 dias seguidos, e qualquer pessoa pode aderir ao teste. No teu telemóvel Android, abre [peektracker.com/android](https://peektracker.com/android), «Experimenta o Peek para Android»:

:::steps
- «Adere ao grupo de testadores» | Toca em «Aderir ao grupo», adere ao grupo do Google na página que se abre e depois volta.
- «Torna-te testador no Google Play» | Toca em «Abrir o Google Play» e depois em **Tornar-se um testador** na página que se abre.
- «Instala-a e mantém-na duas semanas» | Toca em «Instalar o Peek» e mantém a app durante 14 dias.
:::

Enquanto não abrires a ligação do grupo nesse navegador, os dois botões seguintes dizem «Adere primeiro ao grupo». Podes sair do teste quando quiseres, na mesma página do Google Play.

### Como instalo o Peek sem o Google Play?

Adiciona à mão o repositório do próprio Peek à app F-Droid, ou descarrega a app do GitHub. O Peek não está no catálogo principal do F-Droid.

:::steps
- Copia o endereço | Em [peektracker.com/android](https://peektracker.com/android), por baixo de «Sem o Google Play», toca em «Copiar o endereço».
- Adiciona o repositório | Na app F-Droid, vai a **Definições** › **Repositórios** › **+** e cola o endereço completo.
- Instala o Peek | Toca no repositório novo para ver as apps dele e instala «Peek». O F-Droid não instala nada até tu escolheres.
:::

Ou toca em «Abrir o GitHub» na mesma página: na versão mais recente das [versões do Peek no GitHub](https://github.com/plhery/peek-delivery-tracker/releases), toca no ficheiro .apk e deixa o teu navegador instalá-lo. Lá, as versões do Peek estão marcadas como «Pre-release».

Esta versão é assinada pelo Peek, não pela Google, por isso não pode atualizar por cima de uma cópia do Google Play, nem o contrário: se tens o Peek do Google Play, ou uma cópia antiga do repositório ou do GitHub, desinstala-a primeiro. As atualizações chegam pelo repositório F-Droid do Peek. Nenhum dos dois caminhos te torna testador no Google Play.

### Qual é a diferença entre a versão do Google Play e a do F-Droid?

| O quê | Versão do Google Play | Versão do F-Droid e do GitHub |
| --- | --- | --- |
| Serviços da Google | Firebase, ML Kit, Play Integrity | Nenhum |
| Leitor de códigos de barras | Sim | Não, e não pede autorização para a câmara |
| Notificações | Através dos serviços do Google Play | Através de uma app UnifiedPush como o ntfy, ou o Peek procura novidades mais ou menos de 15 em 15 minutos |
| Assinada e atualizada por | Google, através do Google Play | Peek, através do seu repositório F-Droid |

As notificações de cada versão estão explicadas em [notificações de entrega de encomendas](guide:parcel-notifications).

## De que versão do Android precisa o Peek?

Android 12 ou posterior, nas duas versões. Um dispositivo sem câmara traseira também a pode instalar, só que sem o leitor.

Está disponível em dez idiomas: inglês, alemão, francês, italiano, espanhol, português, português do Brasil, polaco, russo e chinês simplificado. Escolhe um em «Definições» › «Preferências» › «Idioma» (as definições abrem-se a partir do botão Conta, no topo de Entregas) ou na definição de idioma por app do próprio Android. Se o escolheres nas definições do Peek, «Adicionar ao Peek», o nome do widget no seletor e o atalho «Adicionar uma encomenda» ficam no idioma do telemóvel; os textos do próprio widget seguem o do Peek.

## Posso usar o Peek no Android sem conta?

Podes. Cola um número de seguimento no primeiro ecrã e o Peek segue-o, listado só nesse telemóvel; o widget e o atalho para a próxima encomenda também funcionam. Entregas mostra «Todas as tuas encomendas num só lugar», com «Por agora, só neste dispositivo.» e um botão «Iniciar sessão».

O Peek esquece uma encomenda procurada 30 dias depois da entrega; a app guarda então a última resposta, marcada «Já não é atualizada», até a apagares. «Definições» › «Neste dispositivo» › «Esquecer todas» esquece-as todas de uma vez. As cópias de segurança do próprio telemóvel guardam as encomendas que ele segue e as preferências do Peek.

Uma conta acrescenta a mesma lista em todos os teus dispositivos e em peektracker.com, o passaporte e os amigos, os e-mails de entrega e «Adicionar encomendas por e-mail» ([como adicionar encomendas por e-mail](guide:add-parcels-by-email)). Inicia sessão com «Continuar com o Google» ou «Iniciar sessão com e-mail»; no Android não há início de sessão com a Apple. «Explorar a demonstração», no ecrã de início de sessão, também não precisa de conta. [Usar o Peek sem conta](guide:account-or-guest) compara as duas opções.

## Como adiciono o widget do Peek ao ecrã principal?

Adiciona «Encomendas», o único widget do Peek, a partir do seletor de widgets, onde aparece com a descrição «Vê num relance as encomendas a caminho, a próxima primeiro.» Os passos da Google:

:::steps
- Abre os widgets | Num ecrã principal, toca sem largar num espaço vazio e depois toca em **Widgets**.
- Procura o Peek | Toca no Peek para veres o widget dele, «Encomendas».
- Coloca-o | Toca sem largar no widget, arrasta-o para onde quiseres e levanta o dedo.
:::

A Google avisa que alguns destes passos só funcionam no Android 14 e em versões posteriores.

«Encomendas» começa com quatro células por duas e pode encolher até duas por duas. Lista as encomendas a caminho e depois as entregues no último dia, pela ordem do Peek. Cada encomenda abre a sua página; o resto do widget abre a tua lista. É redesenhado pelo menos de 20 em 20 minutos, e os dias mudam à meia-noite mesmo com a app fechada.

| O widget mostra | O que significa |
| --- | --- |
| «Não há encomendas a caminho» | Nenhuma encomenda está a caminho: «Adiciona um número ou ligação de seguimento para começar.» |

«Encomendas» é só para o ecrã principal: não há widget para o ecrã bloqueado, nem nada parecido com as Atividades em direto.

### O Peek tem atalhos no ícone da app?

Tem. Toca sem largar no ícone do Peek para veres «Adicionar uma encomenda», que abre o ecrã de adicionar, e um atalho com o nome da tua próxima encomenda a caminho, que a abre. Para fixar um no ecrã principal, toca nele sem largar e arrasta-o para o sítio. O Peek não tem frases de voz no Android: estes atalhos fazem as vezes das ações da Siri no iPhone.

## Como partilho uma ligação de seguimento com o Peek no Android?

Partilha-a e escolhe «Adicionar ao Peek», que aceita qualquer texto ou ligação partilhada.

:::steps
- Partilha | Partilha o número de seguimento, a ligação ou a página a partir de qualquer app e escolhe «Adicionar ao Peek».
- Guarda | No cartão que se abre, toca em «Guardar dados de seguimento». Diz «Guardado. Abre o Peek nos próximos 10 minutos para reveres e adicionares a tua encomenda.»
- Abre o Peek | Dentro desses 10 minutos, o ecrã de adicionar abre com os dados já preenchidos.
:::

As ligações de encomendas do Peek (peektracker.com/p/…) abrem na app depois de instalada. Para enviar uma encomenda a outra pessoa, vê [como partilhar o seguimento de uma encomenda](guide:share-parcel).

### O Peek consegue ler um código de barras no Android?

Só a versão do Google Play. Toca no ícone de código de barras no fim do campo de seguimento (lido em voz alta como «Ler código de barras»), no primeiro ecrã ou no ecrã de adicionar; com a lista vazia, há também um botão «Ler código de barras». «Ler um código de barras de seguimento» abre com a câmara traseira e segue o código de barras mais perto do centro. Se recusares a câmara, o leitor desaparece até a permitires nas definições do Android. Não sabes qual é o número de seguimento? Vê [onde encontrar o número de seguimento](guide:find-tracking-number).

:::sources
- [Peek: Experimenta o Peek para Android](https://peektracker.com/android) – os três passos do teste, Android 12, o repositório F-Droid, o GitHub, a assinatura
- [Peek: notas do Google Play (em inglês)](https://github.com/plhery/peek-delivery-tracker/blob/main/docs/google-play/README.md) – teste fechado, 12 testadores durante 14 dias, o grupo de testadores
- [Peek: app para Android (em inglês)](https://github.com/plhery/peek-delivery-tracker/blob/main/android/README.md) – as duas versões, conteúdo e atualização do widget, atalhos, chaves de assinatura, versões de pré-lançamento
- [Peek: entrada no F-Droid (em inglês)](https://github.com/plhery/peek-delivery-tracker/blob/main/android/fdroid/com.plhery.peekdeliverytracker.yml) – beta, nome, que cópias anteriores desinstalar
- [F-Droid: How to Add a Repo to F-Droid (em inglês)](https://f-droid.org/en/tutorials/add-repo/) – Settings › Repositories › +, e depois escolher as apps a instalar
- [Peek: versões no GitHub](https://github.com/plhery/peek-delivery-tracker/releases) – os ficheiros .apk, marcados como Pre-release
- [Peek: textos em português](https://github.com/plhery/peek-delivery-tracker/blob/main/shared/locales/pt.json) – cada etiqueta do Peek citada aqui
- [Peek: manifesto Android](https://github.com/plhery/peek-delivery-tracker/blob/main/android/app/src/main/AndroidManifest.xml) – Adicionar ao Peek, ligações de encomendas, câmara, cópias de segurança
- [Peek: o widget Encomendas](https://github.com/plhery/peek-delivery-tracker/blob/main/android/app/src/main/res/xml/parcels_widget.xml) – só no ecrã principal, os tamanhos
- [Peek: Definições](https://github.com/plhery/peek-delivery-tracker/blob/main/android/app/src/main/kotlin/com/plhery/peekdeliverytracker/ui/settings/SettingsSheet.kt) – Esquecer todas, Idioma, sem Atividades em direto
- [Peek: o leitor da versão do Google Play](https://github.com/plhery/peek-delivery-tracker/blob/main/android/app/src/play/kotlin/com/plhery/peekdeliverytracker/platform/CameraLabelScanner.kt) – códigos de barras, autorização da câmara
- [Peek: Support (em inglês)](https://peektracker.com/support.html) – sem conta, Forget all
- [Android Help: Add apps, shortcuts and widgets to your Home screens (em inglês)](https://support.google.com/android/answer/9450271?hl=en) – adicionar um widget, fixar um atalho, Android 14
:::
