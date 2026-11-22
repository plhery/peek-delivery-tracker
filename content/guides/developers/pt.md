---
title: Seguimento de encomendas open source: código e motor do Peek
description: O Peek é open source (AGPL-3.0). Aloja-o tu, corre-o localmente ou segue encomendas a partir do teu código com o Universal Parcel Scraper: CLI, Node ou HTTP.
slug: seguimento-encomendas-open-source
picture: O Pip, a encomenda com cara, empoleirado num portátil aberto cujo terminal mostra as leituras de uma encomenda linha a linha.
published: 2026-11-19
updated: 2026-11-22
---

O [Peek](/) é um serviço de seguimento de encomendas gratuito e de código aberto: a app web, a API e as apps para iPhone e Android estão no GitHub com a licença GNU AGPL 3.0, e as transportadoras são lidas por um motor à parte, o Universal Parcel Scraper, livre para uso não comercial. O Peek não tem uma API pública com chaves, por isso, para seguires encomendas a partir do teu próprio código, usa o scraper, como comando, biblioteca Node ou servidor HTTP, sem conta nem chave de API. Também podes alojar o teu próprio Peek, ou corrê-lo no teu computador com encomendas inventadas.

Para experimentares o scraper no terminal:

:::steps
- Instala-o | Precisas de um Node.js recente (o README indica a versão mínima). Corre `npm install -g universal-parcel-scraper`, ou escreve `npx universal-parcel-scraper` onde quer que apareça `parcel-scraper` abaixo.
- Descobre a transportadora | `parcel-scraper detect 1Z999AA10123456784` diz-te a que transportadora pertence um número, offline.
- Vai buscar o histórico | `parcel-scraper track` seguido do teu número pergunta à transportadora e mostra o histórico da encomenda em JSON. Se o número servir para várias transportadoras, junta, por exemplo, `--carrier dhl`. Algumas transportadoras também pedem `--postcode`, `--phone-digits` ou `--tracking-url`.
:::

## O Peek é de código aberto? Com que licença?

Sim. O código do Peek é público em [github.com/plhery/peek-delivery-tracker](https://github.com/plhery/peek-delivery-tracker), com a licença GNU Affero General Public License 3.0 (AGPL-3.0): a app web e a API (Next.js, React, TypeScript), a app nativa em SwiftUI para iPhone, iPad e Mac, e a app Android. Uma cópia ou versão modificada mantém o crédito «Based on Peek by Paul-Louis Hery» com a sua ligação. O [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper), que o Peek instala a partir do npm, está desde a versão 0.1294.0 sob a licença PolyForm Noncommercial 1.0.0, que só permite uso não comercial; as versões anteriores continuam sob Apache 2.0. A sua imagem de navegador TRAWL, opcional, está sob AGPL-3.0.

O Peek é um projeto não comercial e sem publicidade; [o seguimento universal de encomendas](guide:universal-tracker) compara-o com outros serviços de seguimento.

Erros e ideias vão para as [issues do GitHub](https://github.com/plhery/peek-delivery-tracker/issues) do Peek, que são públicas: não incluas números de seguimento reais, códigos postais nem códigos de acesso. Problemas de segurança seguem pelo [relatório privado de vulnerabilidades](https://github.com/plhery/peek-delivery-tracker/security/advisories/new) do GitHub. O [CONTRIBUTING.md](https://github.com/plhery/peek-delivery-tracker/blob/main/CONTRIBUTING.md) lista as verificações a correr antes de um pull request.

## O Peek tem uma API que eu possa usar?

Não tem uma API pública: o Peek não dá chaves de API. A API dele é a da própria app, a que o site e as apps para iPhone e Android chamam, com a sessão iniciada do utilizador. Está descrita num [ficheiro OpenAPI](https://peektracker.com/api/openapi.json), guardado no repositório como `contracts/openapi.json`; o próprio peektracker.com/api responde 404.

O catálogo de transportadoras, `GET /api/carriers`, não pede início de sessão. Seguir uma encomenda sem conta também não, mas aí uma nova pesquisa tem primeiro de passar uma verificação do navegador ou da app. Os tokens pessoais para scripts estão desativados, a menos que o servidor os ative, e nas «Definições» não há nenhum ecrã que crie um.

De fora, a única porta de entrada é o início de sessão de um assistente. O Claude, o ChatGPT, o Gemini ou outro assistente inicia sessão na tua conta com OAuth padrão e usa o servidor MCP do Peek, `https://peektracker.com/mcp`, dentro do que permitiste. O mesmo token pode ler as tuas encomendas em JSON em `GET /api/parcels`, e a integração do Peek para o Home Assistant inicia sessão da mesma forma. Vê [o guia dos assistentes](guide:ai-assistants), [a página do Peek para assistentes](https://peektracker.com/pt/assistants) e [o guia do Home Assistant](guide:home-assistant).

## Como sigo encomendas a partir do meu próprio código sem chave de API?

Com o Universal Parcel Scraper, o motor que lê as transportadoras para o Peek. Descobre a transportadora, lê o próprio site dessa transportadora, recorre a serviços universais de seguimento só quando tem de ser, e devolve o mesmo JSON para todas as transportadoras, com cada leitura arrumada numa etapa de entrega comum.

O README dele conta mais de 4500 transportadoras, através de mais de 800 adaptadores dedicados e de serviços de recurso universais. No benchmark dele, com 100 transportadoras escolhidas para quem compra na Europa, os adaptadores devolvem um histórico para 92, ou 95 com todos os serviços de recurso, contra 73 do melhor agregador sozinho.

Além da linha de comandos, funciona de três formas:

| Onde | Como |
| --- | --- |
| Node | Importa `createTracker` de `universal-parcel-scraper/node`; `tracker.track({ number })` devolve a transportadora, quem respondeu, as leituras, a etapa atual e o que a transportadora mostra, como a entrega prevista |
| Um navegador | Só a deteção da transportadora, com `parseTrackingInput`: sem imports de Node nem pedidos |
| HTTP | `parcel-scraper serve --port 8080`, ou a imagem Docker `ghcr.io/plhery/universal-parcel-scraper`, com tags apenas de versões publicadas; depois, um POST para `/v1/track`. O servidor guarda as respostas em cache na memória e limita o ritmo de pedidos de cada cliente; não tem base de dados e nunca consulta por iniciativa própria |

Antes de construíres em cima dele:

- **Responde a uma pesquisa de cada vez.** Guardar as encomendas e voltar a consultá-las fica a teu cargo: é essa a parte que o Peek acrescenta.
- **Alguns sites de transportadoras só respondem a um navegador.** Para esses, precisa de um Chromium que controla através de pacotes opcionais, ou do seu serviço de navegador TRAWL. A imagem Docker já traz o Chromium.
- **Os serviços de recurso têm de ser ativados.** Só o UPU está ativo por omissão; ParcelsApp, Ship24, 17TRACK, Postal Ninja e Track718 esperam até os ativares, e cada um que ativares recebe o número de seguimento.
- **Uma pesquisa envia o número à transportadora**, com o código postal, a ligação ou os dígitos do telefone quando a transportadora os pede. Não há telemetria.

## Posso alojar o meu próprio Peek?

Sim. Um Peek alojado por ti é um único contentor sempre em execução, com a app web, a API e o worker que continua a consultar as transportadoras, ao lado do Supabase para o início de sessão e o Postgres, atrás de HTTPS. Precisas de:

- **uma stack Supabase** (Auth, PostgREST e Postgres) com cópia de segurança e reposição testadas;
- **SMTP**, se o início de sessão por e-mail ou o e-mail de entrega estiverem ativos;
- **um alojamento que consiga fazer o build do Dockerfile e manter o contentor a correr.** O alojamento serverless não serve: o worker tem de se manter vivo.

As notificações web push, as do iPhone (uma equipa Apple e uma chave APNs) e as do Android (Firebase) são opcionais. Os assistentes também: sem `ASSISTANT_ACCOUNTS`, `/mcp` e `/oauth` não existem. Essa variável lista as contas que se podem ligar, ou `*` para todas, e precisa de `CANONICAL_ORIGIN`. Com ela definida, `PERSONAL_TOKENS=on` ativa os tokens pessoais para os teus scripts, criados através da API. O [docs/DEPLOYMENT.md](https://github.com/plhery/peek-delivery-tracker/blob/main/docs/DEPLOYMENT.md) tem os passos, da base de dados e do início de sessão às atualizações, às cópias de segurança e à mudança de alojamento.

### Experimenta primeiro no teu computador

Sem conta, sem base de dados e sem ficheiro `.env`:

:::steps
- Obtém o código | Clona `https://github.com/plhery/peek-delivery-tracker.git`. Precisas do nvm e da versão do Node.js que o README indica.
- Instala | Na pasta dele, corre `nvm use` e depois `npm ci`.
- Arranca | Corre `npm run dev` e abre localhost:3000: o Peek responde no navegador com encomendas inventadas. localhost:3000/demo mostra uma lista de entregas de exemplo que avançam sempre que atualizas a página.
:::

Para contas e seguimento reais, copia `.env.example` para `.env.local`, preenche os dados de um projeto Supabase e define `NEXT_PUBLIC_USE_API=true`; o [docs/AUTHENTICATION.md](https://github.com/plhery/peek-delivery-tracker/blob/main/docs/AUTHENTICATION.md) tem os passos do início de sessão. A app para iPhone compila no Xcode para um simulador de iPhone e arranca com encomendas de exemplo, sem conta, servidor nem equipa Apple.

## Falta uma transportadora: como a comunico ou adiciono?

Comunica-a ao scraper, que é onde vivem as transportadoras. Na [página para abrir uma nova issue](https://github.com/plhery/universal-parcel-scraper/issues/new/choose), para onde o README do Peek remete como «Tell the scraper», escolhe «Add a carrier» (o nome, o país de origem e o site público de seguimento da transportadora) ou «A carrier stopped working». Novas transportadoras e correções vão para o scraper como pull requests: cada transportadora tem a sua pasta, com fixtures sintéticas e testes offline, e `npm run carrier:new` cria a estrutura de uma nova.

O Peek adota sozinho as novas versões do scraper, por isso uma transportadora não precisa de nenhum pull request ao Peek. E a app para iPhone atualiza a lista de transportadoras a partir do servidor, por isso também não é preciso uma nova versão da app.

Não tens conta no GitHub? Avisa o Peek a partir da página da encomenda, na web, no iPhone ou no Android, com ou sem conta Peek:

:::steps
- Responde à pergunta | Quando o Peek não encontra transportadora para uma encomenda, a página dela pergunta «O site da transportadora mostra-a?». Escolhe «Sim».
- Indica a transportadora | Em «Quem a transporta?», escreve o nome em «Transportadora ou loja», ou cola a ligação de seguimento em «A página de seguimento, se tiveres a ligação». Basta um dos dois.
- Envia | Escolhe «Enviar». A resposta segue com o número da encomenda e nada sobre ti.
:::

Uma pessoa lê estas respostas, mas ninguém te pode responder: se quiseres resposta, escreve para hello@peektracker.com, o endereço indicado na [página de ajuda do Peek](https://peektracker.com/support.html). Como o Peek descobre a transportadora a partir do que colas está explicado em [Seguir uma encomenda sem saber a transportadora](guide:track-without-carrier).

:::sources
- [Peek no GitHub: README (em inglês)](https://github.com/plhery/peek-delivery-tracker) – o que o repositório contém, as transportadoras no scraper, correr localmente, alojar o teu, o build para iPhone
- [Peek no GitHub: LICENSE (em inglês)](https://github.com/plhery/peek-delivery-tracker/blob/main/LICENSE) – GNU Affero General Public License 3.0
- [Universal Parcel Scraper no GitHub (em inglês)](https://github.com/plhery/universal-parcel-scraper) – comandos, opções, Node, navegador, HTTP e Docker, Chromium na imagem, serviços de recurso, privacidade, número de transportadoras, benchmark, licenças, adicionar uma transportadora
- [Universal Parcel Scraper: new issue (em inglês)](https://github.com/plhery/universal-parcel-scraper/issues/new/choose) – os formulários «Add a carrier» e «A carrier stopped working»
- [Universal Parcel Scraper no npm (em inglês)](https://www.npmjs.com/package/universal-parcel-scraper) – o pacote e a versão do Node.js de que precisa
- [Peek: ficheiro OpenAPI](https://peektracker.com/api/openapi.json) – a API da própria app, as rotas sem início de sessão e as suas verificações, ler encomendas com o token de um assistente
- [Peek no GitHub: Architecture (em inglês)](https://github.com/plhery/peek-delivery-tracker/blob/main/docs/ARCHITECTURE.md) – o início de sessão dos assistentes e do Home Assistant, GET /api/parcels, tokens pessoais desativados
- [Peek no GitHub: configuração dos assistentes no servidor](https://github.com/plhery/peek-delivery-tracker/blob/main/src/server/assistants/settings.ts) – tokens pessoais desativados a menos que PERSONAL_TOKENS esteja ativo, ASSISTANT_ACCOUNTS
- [Peek: Ligar o Peek ao Claude, ao ChatGPT ou ao Gemini](https://peektracker.com/pt/assistants) – a porta de entrada para os assistentes
- [Peek no GitHub: Deployment (em inglês)](https://github.com/plhery/peek-delivery-tracker/blob/main/docs/DEPLOYMENT.md) – o que é preciso para alojar o teu Peek, as partes opcionais, assistentes e tokens pessoais na tua própria instância
- [Peek no GitHub: Contributing (em inglês)](https://github.com/plhery/peek-delivery-tracker/blob/main/CONTRIBUTING.md) – verificações antes de um pull request, as transportadoras vão para o scraper, novas versões adotadas sozinhas
- [Peek no GitHub: feedback sobre a encomenda](https://github.com/plhery/peek-delivery-tracker/blob/main/src/peek/parcel/Feedback.tsx) – «Quem a transporta?» quando não se encontra transportadora, basta um dos dois campos para enviar
- [Peek: Help (em inglês)](https://peektracker.com/support.html) – comunicar uma transportadora sem GitHub, issues públicas, relatórios de segurança, contacto
- [Peek: Terms of use (em inglês)](https://peektracker.com/terms.html) – um projeto grátis, não comercial e sem publicidade
:::
