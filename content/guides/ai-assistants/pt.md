---
title: Segue as tuas encomendas no ChatGPT, no Claude ou no Gemini
description: Liga o Peek ao ChatGPT, Claude ou Gemini e pergunta onde estão as tuas encomendas, ou pede-lhe que adicione os números de seguimento dos e-mails. Quem pode e como.
slug: seguir-encomendas-chatgpt-claude-gemini
picture: O Pip, a encomenda com cara, a espreitar de uma janela de conversa num portátil, onde uma pergunta sobre as encomendas desta semana recebe uma lista curta como resposta.
published: 2026-11-19
updated: 2026-11-19
---

Podes seguir as tuas encomendas no Claude, no ChatGPT ou no Gemini se os ligares ao [Peek](/), um serviço gratuito de seguimento de encomendas: o assistente pode então dizer-te onde estão as tuas encomendas e adicionar os números de seguimento que lhe deres. Precisas de uma conta Peek, e cada assistente decide quem pode: o Claude em qualquer plano, o ChatGPT num plano Business, Enterprise ou Edu, e o Gemini para adultos nos EUA com uma conta Google pessoal. O Peek marca a funcionalidade como «Beta», por isso ainda pode mudar.

## O ChatGPT, o Claude ou o Gemini podem seguir as minhas encomendas?

Sim, depois de os ligares ao Peek. Cada assistente define as suas próprias condições:

| Assistente | Onde funciona | Quem pode adicionar o Peek |
| --- | --- | --- |
| Claude | claude.ai e as apps do Claude para computador, iPhone e Android | Qualquer plano. Num plano Team ou Enterprise, primeiro um proprietário adiciona o Peek |
| ChatGPT | chatgpt.com | Um plano Business, Enterprise ou Edu. Num espaço de trabalho, pode ser preciso um administrador adicionar ou permitir o Peek primeiro |
| Gemini | Liga-se em gemini.google.com e depois funciona também na app Gemini | 18 anos ou mais, nos EUA, com uma conta Google pessoal e o histórico ativado, em inglês |
| Outro assistente | Qualquer um que deixe adicionar um conector personalizado ou um servidor MCP | Quem lá puder adicionar um |

Do lado do Peek, qualquer conta pode ligar um (um convidado não), e o Peek é grátis.

O Home Assistant liga-se de outra forma, através da integração do próprio Peek: vê o guia [Segue as tuas encomendas no Home Assistant](guide:home-assistant).

## Como adiciono o Peek ao Claude, ao ChatGPT ou ao Gemini?

Adicionas o Peek no assistente, com o endereço do Peek para assistentes: `https://peektracker.com/mcp`. Os passos abaixo são [os do próprio Peek](https://peektracker.com/pt/assistants); com sessão iniciada no site do Peek (não nas apps), também estão em «Definições» → «Entregas» → «Assistentes», com um botão «Copiar endereço».

### Claude

:::steps
- Abre «Peek Parcel Tracker» | No [diretório de conectores do Claude](https://claude.ai/directory/peek-parcel-tracker) ou, no Claude, abre «Customize › Connectors», seleciona «+» e procura Peek.
- Seleciona «Connect» | O Claude pede-te então que inicies sessão no Peek.
- Inicia sessão no Peek | Escolhe o que o Claude pode fazer e seleciona «Permitir».
- Ativa o Peek numa conversa | Seleciona «+» em baixo à esquerda, depois «Connectors», e ativa o Peek.
:::

Num plano Team ou Enterprise, primeiro um proprietário adiciona o Peek: «Organization settings › Connectors», «Browse connectors» e depois «Add to your team».

Se o Claude não mostrar «Peek Parcel Tracker», adiciona o Peek como conector personalizado no claude.ai ou na app para computador (o plano Free permite um):

:::steps
- Abre «Customize › Connectors» | Seleciona «+ Add» e depois «Add custom connector».
- Dá-lhe o nome Peek | Cola `https://peektracker.com/mcp` e seleciona «Continue».
- Mantém as definições de início de sessão que o Claude encontrar | Seleciona «Continue» e depois «Add».
:::

### ChatGPT

:::steps
- Vai a «chatgpt.com/plugins» | Seleciona «+» e depois «Add custom MCP server».
- Dá-lhe o nome Peek | Cola `https://peektracker.com/mcp` e escolhe «OAuth» como autenticação. Seleciona «I understand and want to continue» e depois «Create as a plugin».
- Inicia sessão no Peek | Escolhe o que o ChatGPT pode fazer e seleciona «Permitir».
- Instala-o | Abre o Peek nos teus plugins e seleciona «+». Depois escreve @ numa nova conversa e escolhe o Peek.
:::

### Gemini

A página de ajuda da Google diz que, para ligar uma app personalizada como o Peek, tens de ter 18 anos ou mais e estar nos EUA, usar uma conta Google pessoal, e não uma do trabalho ou da escola, e ter o histórico («History») ativado. Ligas a app em gemini.google.com, e depois funciona também na app Gemini para telemóvel, só em inglês. A Google não menciona nenhum plano pago. A página da Google e a do Peek dizem ambas para adicionares o endereço do Peek nas «Settings» do Gemini, depois em «Connected Apps», em «Custom apps».

## O que lhe posso pedir?

Pede com as tuas palavras, por exemplo:

- «Vê os meus e-mails e adiciona os números de seguimento que encontrares». Isto funciona quando o assistente consegue ler o teu correio por si, por exemplo através do seu próprio conector do Gmail: o Peek nunca lê o teu e-mail.
- «O que chega esta semana?»
- «Onde está a minha encomenda da IKEA?»
- «Arquiva tudo o que já foi entregue».
- «Qual foi a minha última encomenda entregue?»

Também consegue encontrar as encomendas que precisam de ti, como uma à espera num ponto de levantamento, e as previstas para hoje, no teu fuso horário.

Para adicionar encomendas, dá-lhe um número de seguimento ou a parte do e-mail sobre o envio: o Peek segue cada número de seguimento que encontrar, até 10 de cada vez, e guarda só os números e o nome indicado, nunca o texto. Um número de pedido não é um número de seguimento: [vê como os distinguir](guide:find-tracking-number).

### Encomendas da Amazon

Os e-mails da Amazon normalmente não trazem número de seguimento. Se um assistente conseguir navegar no site da Amazon onde tens sessão iniciada, é-lhe indicado que abra «Os meus pedidos», depois «Track package», e que envie a transportadora e o «Tracking ID», nunca o número do pedido. Aí só pode ler: nunca comprar, cancelar, devolver ou alterar nada, nem iniciar sessão por ti. Caso contrário, procura tu o número: [o nosso guia da Amazon](guide:amazon-orders) mostra onde. Uma encomenda que a própria Amazon entrega, sem ID de seguimento, só se segue na tua conta Amazon.

## O assistente pode apagar ou alterar as minhas encomendas?

Nunca pode apagar uma encomenda: isso fazes tu no Peek. O resto depende de ti: quando o ligas, o ecrã do Peek («Ligar Claude ao Peek?», por exemplo) mostra o teu endereço de e-mail e uma caixa de seleção para cada permissão que o assistente pede:

| Permissão no ecrã do Peek | O que o assistente pode então fazer |
| --- | --- |
| «Ver as tuas encomendas: onde está cada uma, quando está prevista e o seu histórico» | Listar as tuas encomendas e abrir uma com as suas leituras |
| «Adicionar encomendas a partir dos números de seguimento que lhe deres e arquivá-las» | Adicionar encomendas e arquivar uma |
| «Alterar o nome, a transportadora ou as notificações das tuas encomendas e restaurar as arquivadas» | Mudar o nome de uma encomenda ou a sua transportadora, desligar ou ligar as notificações dela ou os e-mails do Peek, e restaurar uma arquivada |

Desmarca o que não quiseres; «Permitir» fica a cinzento até haver pelo menos uma caixa marcada. Se mais tarde pedires algo que não permitiste, o assistente recebe a indicação de ligar o Peek outra vez com essa permissão.

## O que vê o assistente e como o desligo?

Recebe o que permitiste: nomes, remetentes, transportadoras, números de seguimento, estados, dias previstos, pontos de levantamento e leituras das tuas encomendas. Nunca o nome do destinatário, o código postal ou dígitos do telefone: uma leitura que nomeia o destinatário, quem assinou, um código ou uma porta só lhe chega como a sua etapa, por exemplo «Entregue». Aplicam-se depois as regras de privacidade do fornecedor do assistente. O [aviso de privacidade](https://peektracker.com/privacy.html) do Peek diz o que o Peek guarda sobre cada assistente que ligas.

Para desligar um assistente, no site do Peek:

:::steps
- Abre «Definições» | A partir do botão redondo no topo, com a primeira letra do teu endereço de e-mail.
- Seleciona «Assistentes» | Em «Entregas». Cada assistente mostra o que pode fazer e quando foi usado pela última vez.
- Seleciona «Desligar» | Ao lado do assistente.
:::

Também podes remover o Peek nas definições do próprio assistente. Uma ligação sem uso durante 60 dias termina sozinha, e apagar a tua conta Peek apaga os assistentes ligados a ela.

## Porque é que não consigo adicionar o Peek ao meu assistente?

Normalmente é o plano, o país ou a conta:

- **O teu plano ou o teu país.** Vê a tabela acima e, num plano de equipa ou de espaço de trabalho, pergunta ao proprietário ou ao administrador.
- **Não tens conta Peek.** Inicia sessão quando o assistente pedir, com um código de seis dígitos que o Peek te envia por e-mail, ou com Google ou Apple, quando o ecrã o oferecer. O teu primeiro início de sessão cria a conta.
- **O Peek pede-te para iniciar sessão outra vez.** Permitir um assistente exige um início de sessão recente, mesmo com a sessão já iniciada: inicia sessão e depois seleciona «Permitir».
- **«Esta ligação não serve para ligar um assistente».** Volta a ligar a partir do teu assistente.

Há mais casos em «Se algo correr mal», na [página dos assistentes do Peek](https://peektracker.com/pt/assistants), e a [página de ajuda](https://peektracker.com/support.html) do Peek diz como contactar o Peek.

:::sources
- [Peek: Ligar o Peek ao Claude, ao ChatGPT ou ao Gemini](https://peektracker.com/pt/assistants) – quem pode, os passos de cada assistente, o que lhe pedir, o que o Peek partilha, se algo correr mal
- [Peek no GitHub: a configuração de cada assistente](https://github.com/plhery/peek-delivery-tracker/blob/main/src/assistants/setups.tsx) – passos e nomes dos menus, iniciar sessão antes do último passo
- [Peek no GitHub: textos em português](https://github.com/plhery/peek-delivery-tracker/blob/main/shared/locales/pt.json) – planos e locais, permissões, Beta, se algo correr mal
- [Peek no GitHub: as ferramentas dos assistentes](https://github.com/plhery/peek-delivery-tracker/blob/main/src/server/assistants/tools.ts) – o que um assistente pode listar, adicionar e editar, nada de apagar, Amazon
- [Peek no GitHub: o ecrã de consentimento](https://github.com/plhery/peek-delivery-tracker/blob/main/src/components/AssistantConsentScreen.tsx) – caixas de seleção, Permitir, início de sessão recente
- [Peek no GitHub: Definições, Assistentes](https://github.com/plhery/peek-delivery-tracker/blob/main/src/components/Assistants.tsx) – endereço, assistentes ligados, Desligar
- [Peek: Privacy notice (em inglês)](https://peektracker.com/privacy.html) – o que um assistente recebe, o que o Peek guarda, 60 dias, eliminação da conta
- [Peek no GitHub: Terms (em inglês)](https://github.com/plhery/peek-delivery-tracker/blob/main/TERMS.md) – grátis, sem taxas nem assinatura
- [Peek: Help (em inglês)](https://peektracker.com/support.html) – iniciar sessão com um código de seis dígitos
- [Claude Help Center: Get started with custom connectors using remote MCP (em inglês)](https://support.claude.com/en/articles/11175166-get-started-with-custom-connectors-using-remote-mcp) – passos do conector personalizado, um só no plano Free
- [Gemini Apps Help: Connect & manage custom apps for Gemini Apps (em inglês)](https://support.google.com/gemini/answer/17209137?hl=en&co=GENIE.Platform%3DDesktop) – quem pode ligar uma app personalizada, histórico ativado, inglês, ligação na web
:::
