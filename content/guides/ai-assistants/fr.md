---
title: Suivre ses colis avec ChatGPT, Claude ou Gemini
description: Connecte Peek à ChatGPT, Claude ou Gemini pour savoir où sont tes colis, ou fais-lui ajouter les numéros de suivi de tes e-mails. Qui peut, et comment faire.
slug: suivre-colis-chatgpt-claude-gemini
picture: Pip, le colis avec un visage, se penche hors d'une fenêtre de discussion sur un ordinateur portable, où une question sur les colis de la semaine reçoit une courte liste en réponse.
published: 2026-11-19
updated: 2026-11-21
---

Tu peux suivre tes colis dans Claude, ChatGPT ou Gemini en les connectant à [Peek](/), un suivi de colis gratuit : l'assistant peut alors te dire où sont tes colis et ajouter les numéros de suivi que tu lui donnes. Il te faut un compte Peek, et chaque assistant décide qui y a droit : Claude avec n'importe quelle offre, ChatGPT avec une offre Plus, Pro, Business, Enterprise ou Edu, Gemini en anglais (aux États-Unis pour les adultes avec un compte Google personnel, ailleurs via son « Developer Mode »), et OpenClaw pour qui le fait tourner. Peek marque la fonction « Bêta » : elle peut donc encore changer.

## ChatGPT, Claude ou Gemini peuvent-ils suivre mes colis ?

Oui, une fois connectés à Peek. Chaque assistant fixe ses propres conditions :

| Assistant | Où ça marche | Qui peut ajouter Peek |
| --- | --- | --- |
| Claude | claude.ai, et les apps Claude pour ordinateur, iPhone et Android | N'importe quelle offre. Avec une offre Team ou Enterprise, un propriétaire ajoute d'abord Peek |
| ChatGPT | Ajout sur ordinateur, sur chatgpt.com ou dans l'application ChatGPT pour ordinateur | Une offre Plus, Pro, Business, Enterprise ou Edu, pas Free ni Go. Dans un espace de travail, un administrateur devra peut-être d'abord ajouter ou autoriser Peek |
| Gemini | Connecté sur gemini.google.com, puis aussi dans l'app Gemini | En anglais. Aux États-Unis : 18 ans ou plus, avec un compte Google personnel et « History » activé. Ailleurs : via le « Developer Mode » de Gemini |
| OpenClaw | L'ordinateur ou le serveur où tu le fais tourner, dans les conversations de ses agents | Qui le fait tourner, depuis un terminal sur place |
| Un autre assistant | Tout assistant qui permet d'ajouter un connecteur personnalisé ou un serveur MCP | Quiconque peut en ajouter un dans cet assistant |

Côté Peek, n'importe quel compte peut en connecter un (pas un invité), et Peek est gratuit.

Home Assistant se connecte plutôt par l'intégration de Peek : voir le guide [Suivre ses colis dans Home Assistant](guide:home-assistant).

## Comment ajouter Peek à Claude, ChatGPT ou Gemini ?

Tu ajoutes Peek dans l'assistant, avec l'adresse de Peek pour les assistants : `https://peektracker.com/mcp`. Les étapes ci-dessous sont [celles de Peek](https://peektracker.com/fr/assistants) ; si tu es connecté sur le site de Peek (pas dans ses apps), tu les trouves aussi dans « Réglages » → « Livraisons » → « Assistants », avec un bouton « Copier l’adresse ».

### Claude

:::steps
- Ouvre « Peek Parcel Tracker » | Dans le [répertoire de connecteurs de Claude](https://claude.ai/directory/peek-parcel-tracker), ou dans Claude : ouvre « Customize › Connectors », sélectionne « + » et cherche Peek.
- Sélectionne « Connect » | Claude te demande alors de te connecter à Peek.
- Connecte-toi à Peek | Choisis ce que Claude peut faire, puis sélectionne « Autoriser ».
- Active Peek dans une conversation | Sélectionne « + » en bas à gauche, puis « Connectors », et active Peek.
:::

Avec une offre Team ou Enterprise, un propriétaire ajoute d'abord Peek : « Organization settings › Connectors », « Browse connectors », puis « Add to your team ».

Si Claude ne propose pas « Peek Parcel Tracker », ajoute Peek comme connecteur personnalisé sur claude.ai ou dans l'app pour ordinateur (l'offre Free en permet un) :

:::steps
- Ouvre « Customize › Connectors » | Sélectionne « + Add », puis « Add custom connector ».
- Nomme-le Peek | Colle `https://peektracker.com/mcp` et sélectionne « Continue ».
- Garde les réglages de connexion que Claude trouve | Sélectionne « Continue », puis « Add ».
:::

### ChatGPT

:::steps
- Ouvre « Plugins » | Dans la barre latérale de ChatGPT, ou sur « chatgpt.com/plugins ». Sélectionne « + » en haut à droite, puis « Add custom MCP server ».
- Nomme-le Peek | Colle `https://peektracker.com/mcp` et choisis « OAuth » pour l'authentification. Sélectionne « I understand and want to continue », puis « Create as a plugin ».
- Connecte-toi à Peek | Choisis ce que ChatGPT peut faire, puis sélectionne « Autoriser ».
- Installe-le | Ouvre Peek dans tes plugins et sélectionne « Install plugin ». Puis tape @ dans une nouvelle conversation et choisis Peek.
:::

### Gemini

D'après la page d'aide de Google, pour connecter une app personnalisée comme Peek, tu dois avoir 18 ans ou plus et te trouver aux États-Unis, utiliser un compte Google personnel, pas un compte professionnel ou scolaire, et avoir activé « History ». Tu la connectes sur gemini.google.com, puis elle marche aussi dans l'app mobile Gemini, en anglais seulement. Google ne cite aucune offre payante. La page de Google et celle de Peek disent toutes deux d'ajouter l'adresse de Peek dans les « Settings » de Gemini, puis « Connected Apps », sous « Custom apps ».

Hors des États-Unis, les « Settings » de Gemini n'ont pas de « Connected Apps ». Tu ajoutes alors Peek via le « Developer Mode » : Google l'a conçu pour tester des apps comme Peek, et Peek marche ensuite dans tes conversations et dans l'app Gemini, comme aux États-Unis.

:::steps
- Ouvre « Developer Mode » | Dans les « Settings » de Gemini sur gemini.google.com, ou à l'adresse gemini.google.com/devmode. Sélectionne « Create new ».
- Colle l'adresse de Peek | Colle `https://peektracker.com/mcp` et sélectionne « Next ». Coche la case pour accepter les risques, puis sélectionne « Connect », puis « Agree and continue ».
- Connecte-toi à Peek | Peek l'appelle « Google » : choisis ce qu'il peut faire, puis sélectionne « Autoriser ».
- Sélectionne à nouveau « Connect » | Gemini enregistre Peek. Tape ensuite @ dans une conversation et choisis Peek.
:::

### OpenClaw

OpenClaw est un assistant que tu fais tourner toi-même, sur ton ordinateur ou ton serveur. Tu y ajoutes Peek depuis un terminal, et ses agents utilisent ensuite ton compte Peek dans toutes les conversations, quel que soit leur interlocuteur. Ses commandes `openclaw mcp set` et `openclaw mcp login` refusent de tourner tant que son Gateway tourne : tu arrêtes donc d'abord le Gateway.

:::steps
- Arrête le Gateway | Lance `openclaw gateway stop`.
- Ajoute Peek | Lance `openclaw mcp set peek '{"url":"https://peektracker.com/mcp","transport":"streamable-http","auth":"oauth"}'`.
- Connecte-toi à Peek | Lance `openclaw mcp login peek` et ouvre le lien que la commande affiche. Peek l'appelle « OpenClaw MCP » : choisis ce qu'il peut faire, puis sélectionne « Autoriser ».
- Relance le Gateway | Lance `openclaw gateway start`, puis demande à ton agent où en sont tes colis.
:::

Si ton navigateur est sur un autre appareil, la page qu'il ouvre après la connexion ne se charge pas : termine avec le code présent dans son adresse, comme OpenClaw l'explique dans le terminal.

## Qu'est-ce que je peux lui demander ?

Demande-lui avec tes propres mots, par exemple :

- « Parcours mes e-mails et ajoute les numéros de suivi que tu trouves. » Ça marche quand l'assistant peut lire lui-même tes e-mails, par exemple avec son propre connecteur Gmail : Peek ne lit jamais tes e-mails.
- « Qu’est-ce qui arrive cette semaine ? »
- « Où est mon colis IKEA ? »
- « Archive tout ce qui a été livré. »
- « Quel est mon dernier colis livré ? »

Il peut aussi repérer les colis qui demandent ton attention, comme un colis qui attend en point de retrait, et ceux prévus aujourd'hui, dans ton fuseau horaire.

Pour ajouter des colis, donne-lui un numéro de suivi ou la partie d'un e-mail qui concerne l'expédition : Peek suit chaque numéro de suivi qu'il y trouve, jusqu'à 10 à la fois, et ne garde que les numéros et le nom donné, jamais le texte. Un numéro de commande n'est pas un numéro de suivi : [voici comment les distinguer](guide:find-tracking-number).

### Commandes Amazon

Les e-mails d'Amazon ne contiennent en général aucun numéro de suivi. L'assistant doit donc pouvoir ouvrir des pages et cliquer dans le navigateur où tu es connecté à Amazon, sur un ordinateur : Claude in Chrome, ou une conversation Work dans l'application ChatGPT pour ordinateur avec son extension de navigateur. Depuis son application sur téléphone, depuis une conversation où il peut seulement lire tes onglets ouverts, ou avec un navigateur qu'il fait tourner sur ses propres serveurs, il n'a pas accès à tes commandes, et il a pour consigne de le dire plutôt que d'essayer, et de t'indiquer où copier les numéros toi-même. Un assistant capable de naviguer sur le site Amazon où tu es connecté reçoit la consigne d'ouvrir « Vos commandes », puis « Track package », et d'envoyer le transporteur et le « Tracking ID », jamais le numéro de commande. Sur Amazon, il ne peut que lire : jamais acheter, annuler, retourner ou modifier quoi que ce soit, ni se connecter à ta place. Sinon, trouve le numéro toi-même : [notre guide Amazon](guide:amazon-orders) montre où. Un colis qu'Amazon livre lui-même, sans numéro de suivi, ne se suit que dans ton compte Amazon.

## L'assistant peut-il supprimer ou modifier mes colis ?

Il ne peut jamais supprimer un colis : ça, tu le fais dans Peek. Pour le reste, c'est toi qui décides : quand tu le connectes, l'écran de Peek (« Connecter Claude à Peek ? », par exemple) affiche ton adresse e-mail et une case à cocher pour chaque autorisation que l'assistant demande :

| Autorisation sur l'écran de Peek | Ce que l'assistant peut alors faire |
| --- | --- |
| « Voir tes colis : où chacun se trouve, quand il est prévu et son historique » | Lister tes colis, et en ouvrir un avec ses scans |
| « Ajouter des colis à partir des numéros de suivi que tu lui donnes » | Ajouter des colis |
| « Changer le nom, le transporteur ou les notifications de tes colis, et les archiver ou les restaurer » | Renommer un colis, changer son transporteur, couper ou réactiver ses notifications ou les e-mails de Peek, et l'archiver ou le restaurer |

Décoche ce que tu ne veux pas ; « Autoriser » reste grisé tant qu'aucune case n'est cochée. Si tu demandes plus tard quelque chose que tu n'as pas autorisé, l'assistant reçoit la consigne de connecter à nouveau Peek avec cette autorisation.

## Que voit l'assistant, et comment le déconnecter ?

Il reçoit ce que tu as autorisé : le nom de tes colis, leurs expéditeurs, transporteurs, numéros de suivi, statuts, jours prévus, points de retrait et scans. Jamais le nom du destinataire, ni le code postal ou les chiffres de téléphone que tu as donnés : un scan qui nomme le destinataire, la personne qui a signé, un code ou une porte ne lui arrive que sous forme d'étape, comme « Livré ». Le lieu d'un scan reste tel que le transporteur l'a écrit : il peut nommer la ville de livraison et son code postal. Les règles de confidentialité de son fournisseur s'appliquent ensuite. L'[avis de confidentialité](https://peektracker.com/privacy.html) de Peek indique ce qu'il conserve sur chaque assistant que tu connectes.

Pour déconnecter un assistant, sur le site de Peek :

:::steps
- Ouvre « Réglages » | Avec le bouton rond en haut, qui affiche la première lettre de ton adresse e-mail.
- Sélectionne « Assistants » | Sous « Livraisons ». Chaque assistant indique ce qu'il peut faire et quand il a servi pour la dernière fois.
- Sélectionne « Déconnecter » | À côté de l'assistant.
:::

Tu peux aussi retirer Peek dans les paramètres de l'assistant. Une connexion inutilisée pendant 60 jours prend fin d'elle-même, et supprimer ton compte Peek supprime les assistants qui y sont connectés.

## Pourquoi je n'arrive pas à ajouter Peek à mon assistant ?

En général, c'est l'offre, le pays ou le compte :

- **Ton offre ou ton pays.** Vérifie le tableau ci-dessus et, avec une offre d'équipe ou d'espace de travail, demande à son propriétaire ou à son administrateur.
- **Pas de compte Peek.** Connecte-toi quand l'assistant te le demande, avec un code à six chiffres que Peek t'envoie par e-mail, ou avec Google ou Apple si l'écran le propose. Ta première connexion crée le compte.
- **Peek te redemande de te connecter.** Autoriser un assistant demande une connexion récente, même si ta session est ouverte : connecte-toi, puis sélectionne « Autoriser ».
- **« Ce lien ne peut pas connecter d’assistant ».** Recommence la connexion depuis ton assistant.

D'autres cas sont décrits sous « En cas de problème » sur [la page de Peek consacrée aux assistants](https://peektracker.com/fr/assistants), et la [page d'aide](https://peektracker.com/support.html) de Peek explique comment le contacter.

:::sources
- [Peek : Connecter Peek à Claude, ChatGPT ou Gemini](https://peektracker.com/fr/assistants) – qui peut, les étapes de chaque assistant, quoi lui demander, ce que Peek partage, en cas de problème
- [Peek sur GitHub : la configuration de chaque assistant](https://github.com/plhery/peek-delivery-tracker/blob/main/src/assistants/setups.tsx) – étapes et noms des menus, connexion avant la dernière étape
- [Peek sur GitHub : textes en français](https://github.com/plhery/peek-delivery-tracker/blob/main/shared/locales/fr.json) – offres et lieux, autorisations, Bêta, en cas de problème
- [Peek sur GitHub : les outils des assistants](https://github.com/plhery/peek-delivery-tracker/blob/main/src/server/assistants/tools.ts) – ce qu'un assistant peut lister, ajouter et modifier, pas de suppression, Amazon
- [Peek sur GitHub : l'écran d'autorisation](https://github.com/plhery/peek-delivery-tracker/blob/main/src/components/AssistantConsentScreen.tsx) – cases à cocher, Autoriser, connexion récente
- [Peek sur GitHub : Réglages, Assistants](https://github.com/plhery/peek-delivery-tracker/blob/main/src/components/Assistants.tsx) – adresse, assistants connectés, Déconnecter
- [Peek : avis de confidentialité (en anglais)](https://peektracker.com/privacy.html) – ce que reçoit un assistant, ce que Peek conserve, 60 jours, suppression du compte
- [Peek sur GitHub : conditions d'utilisation (en anglais)](https://github.com/plhery/peek-delivery-tracker/blob/main/TERMS.md) – gratuit, sans frais ni abonnement
- [Peek : aide (en anglais)](https://peektracker.com/support.html) – connexion avec un code à six chiffres
- [Aide Claude : Commencer avec les connecteurs personnalisés utilisant MCP distant](https://support.claude.com/fr/articles/11175166-commencer-avec-les-connecteurs-personnalises-utilisant-mcp-distant) – étapes du connecteur personnalisé, un seul avec l'offre Free
- [OpenAI : Add custom MCP server (en anglais)](https://developers.openai.com/api/docs/guides/custom-mcp-server) – les étapes dans ChatGPT, avec les noms de ses menus et boutons
- [Aide OpenAI : Plugins in ChatGPT (en anglais)](https://help.openai.com/en/articles/20001256-plugins-in-chatgpt) – « Plugins » dans la barre latérale, « Install plugin »
- [ChatGPT : Pricing (en anglais)](https://chatgpt.com/pricing/) – « Developer mode », dont relève un serveur MCP personnalisé : Plus et Pro, pas Free ni Go
- [Aide OpenAI : Connecting custom MCP servers in ChatGPT (en anglais)](https://help.openai.com/en/articles/12584461-connecting-custom-mcp-servers-in-chatgpt) – Business, Enterprise et Edu, et qui peut en ajouter un dans un espace de travail
- [Aide Gemini : Connect & manage custom apps for Gemini Apps (en anglais)](https://support.google.com/gemini/answer/17209137?hl=en&co=GENIE.Platform%3DDesktop) – qui peut connecter une app personnalisée, History activé, anglais, connexion sur le web
- [Google for Developers : Test, debug, and evaluate in Gemini Apps Developer Mode (en anglais)](https://developers.google.com/connected-apps/mcp/dev-mode/usage) – ouvrir le Developer Mode, ajouter un serveur comme app personnalisée, ses fenêtres et ses boutons
- [OpenClaw : Transports and OAuth (en anglais)](https://docs.openclaw.ai/cli/mcp/transports) – enregistrer un serveur avec OAuth, `openclaw mcp login`, terminer avec le code, une connexion partagée
- [OpenClaw : Connect MCP servers (en anglais)](https://docs.openclaw.ai/tools/mcp) – les agents utilisent les outils d'un serveur enregistré
- [OpenClaw sur GitHub : les commandes MCP et le Gateway (en anglais)](https://github.com/openclaw/openclaw/blob/v2026.11.5/src/cli/mcp-cli-state-owner.ts) – `mcp set` et `mcp login` refusés tant que le Gateway tourne
:::
