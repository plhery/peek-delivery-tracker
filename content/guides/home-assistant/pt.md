---
title: Segue as tuas encomendas no Home Assistant
description: A integração do Peek para o Home Assistant traz as encomendas como sensores, um calendário de entregas e um cartão para o painel. De que precisas e como instalar.
slug: seguir-encomendas-home-assistant
picture: Um tablet na parede de uma cozinha com o painel da casa: uma lista de encomendas, um pequeno calendário de entregas e o Pip, a encomenda com cara, a sorrir de um dos cartões.
published: 2026-11-19
updated: 2026-11-21
---

Para seguires as tuas encomendas no Home Assistant, instala o Peek for Home Assistant: traz a tua lista de encomendas do [Peek](/) como sensores, um calendário de entregas, um cartão para o painel e uma ferramenta para o Assist. Precisas de uma conta Peek gratuita e do Home Assistant 2026.11 ou posterior, onde pesquisas o Peek no Marketplace; na versão 2026.10, pesquisas no HACS.

## O que mostra o Peek no Home Assistant?

As tuas encomendas do Peek, nas formas que o Home Assistant usa:

| O quê | O que faz |
| --- | --- |
| Sensores | Contam as encomendas a caminho, as que chegam hoje, as que precisam de ti (à espera num ponto de levantamento, uma entrega falhada, a alfândega) e as entregas de hoje, e dão o próximo dia de entrega |
| Um calendário «Deliveries» | Eventos de dia inteiro: cada encomenda a caminho no dia previsto, cada entrega no dia em que chegou |
| Um evento «Parcel updates» | Dispara quando uma encomenda é adicionada, muda de estado, entra em distribuição, precisa de ti, é entregue ou quando o dia previsto muda |
| Ações | Adicionar encomendas a partir de um texto, mudar o nome de uma encomenda ou a sua transportadora, desligar ou ligar as suas notificações, arquivá-la ou restaurá-la, ou ler a lista |
| O cartão «Peek parcels» | As tuas encomendas num painel, em lista ou em cartões com o Pip |
| Uma ferramenta para o Assist | Responde a perguntas como «o que chega hoje?» |

O Home Assistant consulta o Peek a cada 30 minutos, a cada 10 minutos nos dias em que uma encomenda está em distribuição ou prevista, e logo depois de uma das suas ações alterar uma encomenda. Para o consultar de imediato, chama `homeassistant.update_entity` em qualquer entidade do Peek. «Hoje» é o dia no fuso horário do Home Assistant.

## De que preciso?

- **Home Assistant 2026.11 ou posterior**, como diz a página do Peek, e uma conta de administrador: o Marketplace do Home Assistant é só para administradores. Na versão 2026.10, instala-a a partir do HACS.
- **Uma conta Peek.** A configuração abre o Peek para iniciares sessão, com um código de seis dígitos que o Peek te envia por e-mail, ou com Google ou Apple, quando o ecrã o oferecer. O teu primeiro início de sessão cria a conta; um convidado não pode ligar o Home Assistant.
- **Mais nada.** O Peek é grátis, e não há nenhum ID de cliente, segredo ou «Application Credentials» para escrever. Cada Home Assistant pode estar ligado a uma só conta Peek.

A integração liga-se da mesma forma que os assistentes, e as ligações do Peek para assistentes estão em beta. Se o Peek disser «O Peek ainda não pode ligar assistentes a esta conta.», ainda não é possível configurar a integração.

## Como instalo a integração do Peek?

Pesquisa o Peek no Marketplace do Home Assistant. Na versão 2026.10, pesquisa-o no HACS ou instala a integração à mão.

### A partir do Marketplace (Home Assistant 2026.11 e posteriores)

:::steps
- Abre «Settings › Marketplace» | Pesquisa «Peek» e abre-o.
- Instala o Peek | Seleciona «Install» e depois «Set up».
- Inicia sessão no Peek | O teu navegador abre o Peek. Verifica o que o Home Assistant pede (ver as tuas encomendas, adicioná-las, e alterá-las, arquivá-las e restaurá-las) e seleciona «Permitir».
- Volta ao Home Assistant | O Peek leva-te de volta através do My Home Assistant (my.home-assistant.io). Da primeira vez, pede o endereço do teu Home Assistant.
:::

### Com o HACS (Home Assistant 2026.10)

:::steps
- Pesquisa o Peek | No HACS, pesquisa «Peek» e abre-o.
- Descarrega o Peek | Depois reinicia o Home Assistant.
- Adiciona a integração | Vai a «Settings › Devices & services › Add integration», escolhe o Peek, inicia sessão no Peek e seleciona «Permitir».
:::

Se o Peek não aparecer, adiciona `https://github.com/plhery/peek-ha` pela ligação: com «Add from link» no Marketplace, que da primeira vez te pede para ligares o GitHub, ou como repositório personalizado do tipo «Integration» no HACS.

Também a podes instalar à mão: copia a pasta `custom_components/peek` para a pasta `custom_components` da tua configuração, reinicia e adiciona a integração como acima.

## Como adiciono o cartão Peek parcels ao meu painel?

A integração carrega sozinha o seu cartão em todos os painéis: não há nenhum recurso para adicionar. Uma página que já estava aberta enquanto configuravas o Peek ainda não tem o cartão: recarrega-a primeiro, ou fecha a app do Home Assistant e volta a abri-la.

:::steps
- Edita um painel | Abre-o e seleciona o lápis. Num painel que o Home Assistant criou por ti, primeiro pede-te para assumires o controlo.
- Seleciona «Add card» | Depois o separador «By card». O separador «By entity» procura entidades, não cartões.
- Escolhe «Peek parcels» | Procura «Peek», escolhe «Peek parcels» e guarda.
:::

O cartão tem dois estilos: uma lista, a opção predefinida, com uma linha por encomenda que mostra a carrinha da transportadora, a etapa e o dia; ou um cartão por encomenda nas cores da transportadora, como os widgets do Peek para iPhone e Android, com o Pip, a localidade da última leitura, o nome da encomenda, a etapa e o dia. Os dois seguem as secções do Peek, a começar pelas encomendas que precisam de ti, e terminam com «+N more» quando há mais encomendas do que linhas.

O cartão segue o teu tema, claro ou escuro, e o teu idioma, se for inglês ou francês. Em YAML, é `type: custom:peek-parcels-card`; o README da integração lista as opções.

## Posso usá-la em automatizações ou perguntar ao Assist?

Sim. O README tem quatro automatizações prontas a usar:

- uma notificação no telemóvel quando uma encomenda entra em distribuição;
- adicionar os números de seguimento dos teus e-mails de envio, lidos com a integração IMAP do Home Assistant;
- uma mensagem de manhã com o que chega hoje;
- arquivar o que foi entregue, todas as noites.

Quando adicionas encomendas a partir de um texto, o Peek adiciona cada número de seguimento que encontrar, até 10 de cada vez, e guarda só os números e o nome que indicares, por isso, sempre que puderes, envia só a parte do e-mail sobre o envio.

Quando uma das entidades do Peek estiver exposta ao Assist («Settings › Voice assistants › Expose»), podes perguntar «o que chega hoje?» ou onde está a tua encomenda de uma loja. A ferramenta do Peek no Assist só lê: nunca adiciona, altera nem arquiva encomendas. O que devolve (nomes, lojas, transportadoras, etapas, dias previstos e o local da última leitura) vai para o agente de conversação que escolheste, que pode ser um serviço na nuvem.

## O que é que não consegue fazer?

- **Apagar encomendas.** O Home Assistant pode ver, adicionar, alterar e arquivar as tuas encomendas, mas nunca apagá-las. Se ligaste o Peek antes da versão 0.2.0 da integração, a primeira alteração pede-te para voltares a iniciar sessão no Peek, uma única vez.
- **Ligar o Claude, o ChatGPT ou o Gemini.** Ligam-se ao Peek por conta própria: vê o guia [Segue as tuas encomendas no ChatGPT, no Claude ou no Gemini](guide:ai-assistants).

## O que guarda o Home Assistant e como removo a integração?

O Home Assistant só fala com o Peek, em peektracker.com, e guarda os tokens do início de sessão na configuração, como qualquer integração. O histórico regista as contagens dos sensores e os eventos «Parcel updates», com o nome da encomenda, a transportadora, a etapa e o último local, mas não as listas de encomendas dos sensores. O cartão funciona no teu navegador com os dados do próprio Home Assistant e não carrega nada de nenhum outro sítio.

Para a remover, apaga o Peek em «Settings › Devices & services»: o Home Assistant devolve o acesso ao Peek, que esquece a ligação, e as tuas encomendas ficam no Peek. Se nesse momento o Peek não estiver acessível, ou para cortar o acesso do Home Assistant do lado do Peek, seleciona «Desligar» ao lado do Home Assistant no site do Peek (não nas apps), em «Definições» → «Entregas» → «Assistentes». Uma integração que mantenhas depois disso mostra «Authentication expired for Peek»; voltar a configurá-la abre o mesmo início de sessão.

:::sources
- [Peek for Home Assistant: README (em inglês)](https://github.com/plhery/peek-ha) – o que adiciona, ações, versões, instalação, início de sessão, o cartão, automatizações, Assist, privacidade, remoção
- [Peek for Home Assistant: hacs.json](https://github.com/plhery/peek-ha/blob/main/hacs.json) – a versão mais antiga do Home Assistant em que se instala, 2026.10
- [Home Assistant: Marketplace (em inglês)](https://www.home-assistant.io/integrations/marketplace/) – Settings › Marketplace, pesquisa, Add from link, ligar o GitHub, Install, só administradores, substitui o HACS a partir da 2026.11
- [HACS: lista predefinida de integrações](https://github.com/hacs/default/blob/master/integration) – a entrada do Peek, plhery/peek-ha, no catálogo onde o HACS e o Marketplace pesquisam
- [Home Assistant no GitHub: o catálogo do Marketplace](https://github.com/home-assistant/core/blob/dev/homeassistant/components/marketplace/const.py) – o Marketplace usa a lista predefinida do HACS, hacs/default
- [Peek: Ligar o Peek ao Claude, ao ChatGPT ou ao Gemini](https://peektracker.com/pt/assistants) – os passos do Home Assistant na página do Peek, 2026.11 ou posterior
- [Peek no GitHub: a configuração de cada assistente](https://github.com/plhery/peek-delivery-tracker/blob/main/src/assistants/setups.tsx) – os nomes dos menus do Home Assistant
- [Peek no GitHub: textos em português](https://github.com/plhery/peek-delivery-tracker/blob/main/shared/locales/pt.json) – versão, passos, conta, beta, o que o Home Assistant pode fazer
- [Peek no GitHub: Definições, Assistentes](https://github.com/plhery/peek-delivery-tracker/blob/main/src/components/Assistants.tsx) – assistentes ligados, Desligar
- [Peek: Help (em inglês)](https://peektracker.com/support.html) – iniciar sessão com um código de seis dígitos
- [Peek no GitHub: Terms (em inglês)](https://github.com/plhery/peek-delivery-tracker/blob/main/TERMS.md) – grátis, sem taxas nem assinatura
:::
