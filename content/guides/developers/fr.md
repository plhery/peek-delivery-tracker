---
title: Suivi de colis open source : le code et le moteur de Peek
description: Peek est open source, sous licence Apache 2.0. Héberge-le, lance-le en local ou suis des colis depuis ton code avec Universal Parcel Scraper : CLI, Node ou HTTP.
slug: suivi-colis-open-source
picture: Pip, le colis qui a un visage, perché sur un ordinateur portable ouvert dont le terminal affiche les scans d'un colis ligne par ligne.
published: 2026-11-19
updated: 2026-11-19
---

[Peek](/) est un outil de suivi de colis gratuit et open source : son app web, son API et ses apps iPhone et Android sont sur GitHub sous licence Apache 2.0, et c'est un moteur open source distinct, Universal Parcel Scraper, qui interroge ses transporteurs. Peek n'a pas d'API publique avec des clés : pour suivre des colis depuis ton propre code, utilise le scraper, sous forme de commande, de bibliothèque Node ou de serveur HTTP, sans compte ni clé d'API. Tu peux aussi héberger ton propre Peek, ou le lancer sur ton ordinateur avec des colis fictifs.

Pour essayer le scraper depuis ton terminal :

:::steps
- Installe-le | Il te faut un Node.js récent (le README indique la version minimale). Lance `npm install -g universal-parcel-scraper`, ou tape `npx universal-parcel-scraper` partout où `parcel-scraper` apparaît ci-dessous.
- Identifie le transporteur | `parcel-scraper detect 1Z999AA10123456784` indique à quel transporteur appartient un numéro, hors ligne.
- Récupère l'historique | `parcel-scraper track` suivi de ton numéro interroge le transporteur et affiche l'historique du colis en JSON. Si le numéro correspond à plusieurs transporteurs, ajoute par exemple `--carrier dhl`. Certains transporteurs demandent aussi `--postcode`, `--phone-digits` ou `--tracking-url`.
:::

## Peek est-il open source, et sous quelle licence ?

Oui. Le code de Peek est public sur [github.com/plhery/peek-delivery-tracker](https://github.com/plhery/peek-delivery-tracker), sous licence Apache 2.0 : l'app web et l'API (Next.js, React, TypeScript), l'app native SwiftUI pour iPhone, iPad et Mac, et l'app Android. [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper), que Peek installe depuis npm, est lui aussi sous Apache 2.0 ; seule son image de navigateur TRAWL, facultative, est sous AGPL-3.0.

Peek est un projet non commercial, sans publicité ; [le suivi de colis universel](guide:universal-tracker) le compare aux autres trackers.

Les bugs et les idées vont dans les [issues GitHub](https://github.com/plhery/peek-delivery-tracker/issues) de Peek, qui sont publiques : n'y mets ni vrais numéros de suivi, ni codes postaux, ni codes de connexion. Les problèmes de sécurité passent par le [signalement privé de vulnérabilité](https://github.com/plhery/peek-delivery-tracker/security/advisories/new) de GitHub. [CONTRIBUTING.md](https://github.com/plhery/peek-delivery-tracker/blob/main/CONTRIBUTING.md) liste les vérifications à lancer avant une pull request.

## Peek a-t-il une API que je peux utiliser ?

Pas d'API publique : Peek ne délivre aucune clé d'API. Son API est celle de l'app, celle qu'appellent le site et les apps iPhone et Android, avec la session de l'utilisateur connecté. Elle est décrite dans un [fichier OpenAPI](https://peektracker.com/api/openapi.json), conservé dans le dépôt sous `contracts/openapi.json` ; peektracker.com/api lui-même répond 404.

Le catalogue des transporteurs, `GET /api/carriers`, ne demande pas de connexion. Suivre un colis sans compte non plus, mais une nouvelle recherche doit d'abord passer une vérification du navigateur ou de l'app. Les jetons personnels pour les scripts sont désactivés, sauf si le serveur les active, et les « Réglages » n'ont aucun écran pour en créer un.

Depuis l'extérieur, la seule porte d'entrée est la connexion d'un assistant. Claude, ChatGPT, Gemini ou un autre assistant se connecte à ton compte en OAuth standard et utilise le serveur MCP de Peek, `https://peektracker.com/mcp`, dans les limites de ce que tu as autorisé. Le même jeton peut lire tes colis en JSON sur `GET /api/parcels`, et l'intégration Home Assistant de Peek se connecte de la même façon. Voir [le guide des assistants](guide:ai-assistants), [la page de Peek pour les assistants](https://peektracker.com/fr/assistants) et [le guide Home Assistant](guide:home-assistant).

## Comment suivre des colis depuis mon propre code sans clé d'API ?

Avec Universal Parcel Scraper, le moteur qui interroge les transporteurs pour Peek. Il trouve le transporteur, lit directement le site de ce transporteur, ne se rabat sur des trackers universels que lorsqu'il le faut, et renvoie le même JSON pour chaque transporteur, chaque scan étant classé dans une étape de livraison commune.

Son README compte plus de 4 500 transporteurs, grâce à plus de 800 adaptateurs dédiés et à des services de repli universels. Dans son benchmark de 100 transporteurs choisis pour les acheteurs en Europe, les adaptateurs renvoient un historique pour 91 d'entre eux, 95 avec tous les services de repli, contre 73 pour le meilleur agrégateur seul.

En plus de la ligne de commande, il fonctionne de trois façons :

| Où | Comment |
| --- | --- |
| Node | Importe `createTracker` depuis `universal-parcel-scraper/node` ; `tracker.track({ number })` renvoie le transporteur, qui a répondu, les scans, l'étape actuelle et ce qu'affiche le transporteur, comme la livraison prévue |
| Un navigateur | Détection du transporteur seulement, avec `parseTrackingInput` : aucun import Node, aucune requête |
| HTTP | `parcel-scraper serve --port 8080`, ou l'image Docker `ghcr.io/plhery/universal-parcel-scraper`, taguée uniquement avec les numéros de version publiés ; puis un POST sur `/v1/track`. Le serveur met les réponses en cache en mémoire et limite le débit des appelants ; il n'a pas de base de données et n'interroge jamais de lui-même |

Avant de bâtir dessus :

- **Il répond à une recherche à la fois.** Garder les colis et les vérifier à nouveau, c'est à toi de le faire : c'est la part qu'ajoute Peek.
- **Certains sites de transporteurs ne répondent qu'à un navigateur.** Pour ceux-là, il lui faut un Chromium qu'il pilote via des paquets facultatifs, ou son service de navigateur TRAWL. L'image Docker embarque Chromium.
- **Les services de repli sont à activer.** Seul UPU est actif par défaut ; ParcelsApp, Ship24, 17TRACK, Postal Ninja et Track718 attendent que tu les actives, et chacun de ceux que tu actives reçoit le numéro de suivi.
- **Une recherche envoie le numéro au transporteur**, avec le code postal, le lien ou les chiffres du numéro de téléphone quand il les demande. Aucune télémétrie.

## Puis-je héberger mon propre Peek ?

Oui. Un Peek auto-hébergé, c'est un seul conteneur qui tourne en continu, avec l'app web, l'API et le worker qui interroge sans cesse les transporteurs, à côté de Supabase pour la connexion et Postgres, derrière HTTPS. Il te faut :

- **une stack Supabase** (Auth, PostgREST et Postgres) avec une sauvegarde et une restauration testées ;
- **un serveur SMTP**, si la connexion par e-mail ou l'e-mail de livraison est activé ;
- **un hébergeur capable de construire l'image à partir du Dockerfile et de garder le conteneur en marche.** L'hébergement serverless ne convient pas : le worker doit rester en vie.

Le push web, les notifications iPhone (une équipe Apple et une clé APNs) et les notifications Android (Firebase) sont facultatifs. Les assistants aussi : sans `ASSISTANT_ACCOUNTS`, `/mcp` et `/oauth` n'existent pas. Cette variable liste les comptes qui peuvent se connecter, ou `*` pour tout le monde, et demande `CANONICAL_ORIGIN`. Quand elle est définie, `PERSONAL_TOKENS=on` active les jetons personnels pour tes scripts, créés via l'API. [docs/DEPLOYMENT.md](https://github.com/plhery/peek-delivery-tracker/blob/main/docs/DEPLOYMENT.md) donne les étapes, de la base de données et de la connexion jusqu'aux mises à jour, aux sauvegardes et au changement d'hébergeur.

### Lance-le d'abord sur ton ordinateur

Pas besoin de compte, de base de données ni de fichier `.env` :

:::steps
- Récupère le code | Clone `https://github.com/plhery/peek-delivery-tracker.git`. Il te faut nvm et la version de Node.js indiquée dans le README.
- Installe | Dans son dossier, lance `nvm use`, puis `npm ci`.
- Démarre | Lance `npm run dev` et ouvre localhost:3000 : Peek répond dans le navigateur avec des colis fictifs. localhost:3000/demo liste des exemples de livraisons qui avancent quand tu actualises la page.
:::

Pour de vrais comptes et un vrai suivi, copie `.env.example` en `.env.local`, renseigne un projet Supabase et mets `NEXT_PUBLIC_USE_API=true` ; [docs/AUTHENTICATION.md](https://github.com/plhery/peek-delivery-tracker/blob/main/docs/AUTHENTICATION.md) donne les étapes de la connexion. L'app iPhone se compile dans Xcode pour un simulateur d'iPhone et démarre avec des colis d'exemple, sans compte, serveur ni équipe Apple.

## Un transporteur manque : comment le signaler ou l'ajouter ?

Signale-le au scraper, là où vivent les transporteurs. Sur sa [page de nouvelle issue](https://github.com/plhery/universal-parcel-scraper/issues/new/choose), vers laquelle le README de Peek renvoie sous le nom « Tell the scraper », choisis « Add a carrier » (son nom, son pays d'origine et son site de suivi public) ou « A carrier stopped working ». Les nouveaux transporteurs et les corrections arrivent dans le scraper sous forme de pull requests : chaque transporteur a son propre dossier avec des fixtures synthétiques et des tests hors ligne, et `npm run carrier:new` en crée le squelette.

Peek adopte de lui-même les nouvelles versions du scraper : un transporteur n'a donc besoin d'aucune pull request vers Peek. Et l'app iPhone actualise sa liste de transporteurs depuis le serveur, donc pas besoin non plus d'une nouvelle version de l'app.

Pas de compte GitHub ? Dis-le à Peek depuis la page du colis, sur le web, sur iPhone ou sur Android, avec ou sans compte Peek :

:::steps
- Réponds à la question | Quand Peek ne trouve aucun transporteur pour un colis, sa page demande « Le site du transporteur l’affiche ? ». Choisis « Oui ».
- Nomme le transporteur | Dans « Qui le transporte ? », indique-le sous « Transporteur ou boutique », ou colle son lien de suivi sous « Sa page de suivi, si tu as le lien ». L'un des deux suffit.
- Envoie | Choisis « Envoyer ». Ta réponse part avec le numéro du colis, et rien sur toi.
:::

Une personne lit ces réponses, mais personne ne peut te répondre : pour avoir une réponse, écris à hello@peektracker.com, l'adresse indiquée sur [la page d'aide de Peek](https://peektracker.com/support.html). La façon dont Peek identifie un transporteur à partir de ce que tu colles est expliquée dans [Suivre un colis sans connaître le transporteur](guide:track-without-carrier).

:::sources
- [Peek sur GitHub : README (en anglais)](https://github.com/plhery/peek-delivery-tracker) – ce que contient le dépôt, les transporteurs dans le scraper, le lancer en local, l'héberger soi-même, la compilation de l'app iPhone
- [Peek sur GitHub : LICENSE (en anglais)](https://github.com/plhery/peek-delivery-tracker/blob/main/LICENSE) – Apache License 2.0
- [Universal Parcel Scraper sur GitHub (en anglais)](https://github.com/plhery/universal-parcel-scraper) – commandes, options, Node, navigateur, HTTP et Docker, Chromium dans l'image, services de repli, confidentialité, nombre de transporteurs, benchmark, licences, ajouter un transporteur
- [Universal Parcel Scraper : nouvelle issue (en anglais)](https://github.com/plhery/universal-parcel-scraper/issues/new/choose) – les formulaires « Add a carrier » et « A carrier stopped working »
- [Universal Parcel Scraper sur npm (en anglais)](https://www.npmjs.com/package/universal-parcel-scraper) – le paquet et la version de Node.js requise
- [Peek : fichier OpenAPI](https://peektracker.com/api/openapi.json) – l'API propre à l'app, les routes sans connexion et leurs vérifications, la lecture des colis avec le jeton d'un assistant
- [Peek sur GitHub : architecture (en anglais)](https://github.com/plhery/peek-delivery-tracker/blob/main/docs/ARCHITECTURE.md) – la connexion des assistants et de Home Assistant, GET /api/parcels, jetons personnels désactivés
- [Peek sur GitHub : paramètres des assistants](https://github.com/plhery/peek-delivery-tracker/blob/main/src/server/assistants/settings.ts) – jetons personnels désactivés sauf si PERSONAL_TOKENS est activé, ASSISTANT_ACCOUNTS
- [Peek : Connecter Peek à Claude, ChatGPT ou Gemini](https://peektracker.com/fr/assistants) – la porte d'entrée des assistants
- [Peek sur GitHub : déploiement (en anglais)](https://github.com/plhery/peek-delivery-tracker/blob/main/docs/DEPLOYMENT.md) – ce qu'il faut pour l'auto-hébergement, les parties facultatives, les assistants et les jetons personnels sur ta propre instance
- [Peek sur GitHub : contribuer (en anglais)](https://github.com/plhery/peek-delivery-tracker/blob/main/CONTRIBUTING.md) – vérifications avant une pull request, les transporteurs vont dans le scraper, nouvelles versions adoptées d'elles-mêmes
- [Peek sur GitHub : retour sur un colis](https://github.com/plhery/peek-delivery-tracker/blob/main/src/peek/parcel/Feedback.tsx) – « Qui le transporte ? » quand aucun transporteur n'est trouvé, l'un des deux champs suffit pour envoyer
- [Peek : aide (en anglais)](https://peektracker.com/support.html) – signaler un transporteur sans GitHub, issues publiques, signalements de sécurité, contact
- [Peek : conditions d'utilisation (en anglais)](https://peektracker.com/terms.html) – un projet gratuit, non commercial, sans publicité
:::
