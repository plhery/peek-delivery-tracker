---
title: Suivi de commande Amazon : trouver le numéro de suivi
description: Où Amazon affiche le transporteur et le numéro de suivi de ton colis, et comment le suivre dans Peek, toi-même ou avec Claude ou ChatGPT qui le fait pour toi.
slug: suivi-commande-amazon
picture: Un téléphone affiche l'avancée d'une commande, deux camionnettes se passent un colis, et Pip regarde.
published: 2026-10-16
updated: 2026-11-21
---

Amazon affiche le transporteur et le numéro de suivi de chaque colis dans Vos commandes, puis Suivre le colis. Copie le numéro dans [Peek](/), et tu suis ce colis avec tous les autres. Ça marche pareil sur tous les sites Amazon.

## Où trouver le numéro de suivi sur Amazon ?

:::steps
- Ouvre Vos commandes | Sur le site, c'est en haut à droite. Dans l'application Amazon, touche l'icône en forme de personne, puis Vos commandes.
- Choisis Suivre le colis | Le bouton est à côté de chaque colis en route ou tout juste livré. Une commande en plusieurs colis a un bouton par colis.
- Copie le numéro de suivi | Le transporteur est en gras, Colissimo par exemple, et son numéro de suivi juste en dessous.
- Colle-le dans Peek | Peek reconnaît le transporteur et suit le colis.
:::

:::picture amazon-orders/orders-fr.webp 480x300
Vos commandes sur Amazon : une commande, avec le bouton Suivre le colis mis en évidence à côté. Une commande d'exemple, pas une vraie.
:::

:::picture amazon-orders/track-fr.webp 480x300
La page Suivre le colis d'Amazon : le transporteur Colissimo et, juste en dessous, le numéro de suivi, tous deux mis en évidence. Valeurs d'exemple.
:::

Sur les autres sites Amazon, les mêmes boutons s'appellent :

| Site | Vos commandes | Suivre le colis |
| --- | --- | --- |
| amazon.com, amazon.co.uk | Your Orders | Track package |
| amazon.de | Meine Bestellungen | Sendung verfolgen |
| amazon.it | I miei ordini | Traccia il mio pacco |
| amazon.es | Mis pedidos | Localiza tu paquete |
| amazon.pl | Moje zamówienia | Śledź przesyłkę |

### Pas de numéro de suivi ?

- **Une commande récente.** Le numéro apparaît en général quand le transporteur a le colis.
- **Amazon le livre lui-même.** La commande peut n'afficher aucun numéro, ou un numéro d'Amazon : deux lettres pour le pays et 10 chiffres, comme `FR1234567890`. Colle-le quand même dans Peek : Peek le suit si le suivi public d'Amazon le connaît, et te dit si seul ton compte Amazon le montre.
- **Le numéro de commande,** trois groupes de chiffres comme `123-1234567-1234567`, n'est jamais un numéro de suivi.

## Claude ou ChatGPT peuvent-ils le faire pour moi ?

Oui, sur un ordinateur : l'assistant doit piloter le navigateur où tu es connecté à Amazon. Claude le fait avec Claude in Chrome, avec une offre Claude payante ; ChatGPT, depuis une conversation Work dans son application pour ordinateur, avec son extension de navigateur. Sur téléphone, ou dans une conversation qui peut seulement lire tes onglets ouverts ou qui navigue depuis ses propres serveurs, l'assistant n'a pas accès à tes commandes, et les e-mails d'Amazon ne donnent pas le numéro de suivi.

### Avec Peek dans l'assistant

Connecte Peek une fois. Dans Claude, ajoute « Peek Parcel Tracker » depuis le [répertoire de connecteurs de Claude](https://claude.ai/directory/peek-parcel-tracker). Dans ChatGPT, ajoute Peek comme plugin, avec une offre Plus, Pro, Business, Enterprise ou Edu. [Toutes les étapes sont ici](guide:ai-assistants).

Puis demande :

> Importe mes colis Amazon dans Peek.

Peek indique à l'assistant où Amazon affiche les numéros. L'assistant ajoute chaque colis, avec le nom de son article, et te dit lesquels Amazon suit seulement dans ton compte.

### Sans le connecteur Peek

Copie ceci dans l'assistant :

> Importe mes colis Amazon dans Peek, dans ce navigateur, comme le montre https://peektracker.com/fr/guides/suivi-commande-amazon : copie le numéro de suivi de chaque colis depuis Vos commandes, puis Suivre le colis, et ajoute-le sur peektracker.com. Sur Amazon, lis seulement : n'achète, n'annule, ne retourne et ne modifie rien, et ne te connecte pas à ma place.

Connecte-toi d'abord à Peek dans ce navigateur : sans compte, Peek ne recherche que quelques colis par jour.

Dans les deux cas, l'assistant ne fait que lire Amazon. Si Amazon te demande de te connecter, fais-le toi-même dans cette fenêtre, puis redemande-lui.

Colis en retard, ou marqué livré mais introuvable ? Lis [livré mais pas reçu](guide:delivered-not-received) et [colis perdu](guide:lost-parcel).

:::sources
- [Amazon.fr : Suivre votre colis](https://www.amazon.fr/gp/help/customer/display.html?nodeId=GENAFPTNLHV7ZACW) – Vos commandes, Suivre le colis, les numéros de suivi ; la même page sur amazon.com, .co.uk, .de, .it, .es et .pl donne leurs mots
- [Amazon.fr : Coordonnées des transporteurs](https://www.amazon.fr/gp/help/customer/display.html?nodeId=GBPEXEXYULHB5WCH) – le nom du transporteur et le numéro de suivi sous Suivre le colis, un transporteur par colis
- [Amazon.fr : Informations de suivi manquantes](https://www.amazon.fr/gp/help/customer/display.html?nodeId=G6ZFEB9ZDU7QHMER) – pas de numéro tant que le colis n'a pas rejoint le transporteur
- [Amazon.fr : Livraisons d'Amazon Logistics](https://www.amazon.fr/gp/help/customer/display.html?nodeId=GEW3XT9JEMBLTKRV) – les colis qu'Amazon livre lui-même, suivis dans Vos commandes
- [Amazon Help : Return a Gift (en anglais)](https://www.amazon.com/gp/help/customer/display.html?nodeId=GMTDJ6XPUMN7NWNB) – le numéro de commande à 17 chiffres
- [About Amazon : How to track your packages (en anglais)](https://www.aboutamazon.com/news/retail/track-amazon-package) – le menu des commandes en haut à droite, l'icône en forme de personne dans l'application, Track package
- [Claude Help Center : Get started with Claude in Chrome (en anglais)](https://support.claude.com/en/articles/12012173-get-started-with-claude-in-chrome) – les offres Claude payantes, le panneau latéral, Claude in Chrome dans les connecteurs d'une conversation
- [ChatGPT : Browser extension (en anglais)](https://learn.chatgpt.com/docs/chrome-extension) – installée depuis l'application ChatGPT pour ordinateur ; lit les sites où tu es connecté et agit dessus depuis une conversation Work dans cette application, et utilise aussi les plugins
- [ChatGPT : Browser (en anglais)](https://learn.chatgpt.com/codex/browser) – ChatGPT Work sur le web et sur téléphone navigue dans le cloud, sans tes connexions
- [Peek : Connecter Peek à Claude, ChatGPT ou Gemini](https://peektracker.com/fr/assistants) – les étapes de Peek pour Claude et ChatGPT
- [Peek sur GitHub : les outils des assistants](https://github.com/plhery/peek-delivery-tracker/blob/main/src/server/assistants/tools.ts) – ce que Peek dit à un assistant sur Amazon : Your Orders, Track package, « Delivery By », « Tracking ID », lecture seule
- [Peek sur GitHub : textes en français](https://github.com/plhery/peek-delivery-tracker/blob/main/shared/locales/fr.json) – sans compte, quelques recherches par jour
- [Universal Parcel Scraper : données transporteurs](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers) – les formes des numéros d'Amazon
:::
