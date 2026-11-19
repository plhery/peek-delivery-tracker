---
title: Suivre ses colis dans Home Assistant
description: Avec l'intégration Peek, Home Assistant affiche tes colis en capteurs, calendrier des livraisons et carte de tableau de bord. Prérequis, installation et réglages.
slug: suivre-colis-home-assistant
picture: Une tablette sur un mur de cuisine affiche un tableau de bord de la maison, avec une liste de colis, un petit calendrier des livraisons et Pip, le colis avec un visage, qui sourit depuis l'une des cartes.
published: 2026-11-19
updated: 2026-11-19
---

Pour suivre tes colis dans Home Assistant, installe Peek pour Home Assistant : l'intégration y apporte ta liste de colis [Peek](/) sous forme de capteurs, d'un calendrier des livraisons, d'une carte de tableau de bord et d'un outil pour Assist. Il te faut un compte Peek gratuit et Home Assistant 2026.11 ou plus récent, où tu l'ajoutes depuis le Marketplace grâce à son lien ; avec la version 2026.10, le README de l'intégration indique un chemin par HACS.

## Qu'est-ce que Peek affiche dans Home Assistant ?

Tes colis Peek, sous les formes qu'utilise Home Assistant :

| Quoi | Ce que ça fait |
| --- | --- |
| Des capteurs | Comptent les colis en route, ceux qui arrivent aujourd'hui, ceux qui demandent ton attention (en attente dans un point de retrait, une livraison manquée, la douane) et les livraisons du jour, et donnent le prochain jour de livraison |
| Un calendrier « Livraisons » | Des événements sur la journée entière : chaque colis en route à son jour prévu, chaque livraison le jour où elle est arrivée |
| Un événement « Mises à jour des colis » | Se déclenche quand un colis est ajouté, change de statut, part en livraison, demande ton attention, est livré, ou que son jour prévu change |
| Trois actions | Ajouter des colis à partir d'un texte, archiver un colis ou lire la liste |
| La carte « Peek parcels » | Tes colis sur un tableau de bord, en liste ou en cartes avec Pip |
| Un outil pour Assist | Répond à des questions comme « Qu'est-ce qui arrive aujourd'hui ? » |

Home Assistant interroge Peek toutes les 30 minutes, toutes les 10 minutes un jour où un colis est en cours de livraison ou attendu, et juste après avoir ajouté ou archivé un colis. Pour l'interroger tout de suite, appelle `homeassistant.update_entity` sur n'importe quelle entité Peek. « Aujourd'hui », c'est le jour dans le fuseau horaire de Home Assistant.

## De quoi ai-je besoin ?

- **Home Assistant 2026.11 ou plus récent**, comme l'indique la page de Peek, et un compte administrateur : le Marketplace de Home Assistant est réservé aux administrateurs. Avec la version 2026.10, le README de l'intégration indique un chemin par HACS.
- **Un compte Peek.** La configuration ouvre Peek pour que tu te connectes, avec un code à six chiffres que Peek t'envoie par e-mail, ou avec Google ou Apple quand l'écran le propose. Ta première connexion crée le compte ; un invité ne peut pas connecter Home Assistant.
- **Rien d'autre.** Peek est gratuit, et il n'y a ni identifiant client, ni secret, ni Application Credentials à saisir. Un seul compte Peek peut être connecté à chaque Home Assistant.

L'intégration se connecte comme les assistants, et les connexions de Peek pour les assistants sont en bêta. Si Peek affiche « Peek ne peut pas encore connecter d’assistants à ce compte. », l'intégration ne peut pas encore être configurée.

## Comment installer l'intégration Peek ?

Depuis le Marketplace de Home Assistant, grâce à son lien, car Peek n'est pas encore dans le catalogue du Marketplace. Avec la version 2026.10, passe par HACS ou installe-la à la main.

### Depuis le Marketplace (Home Assistant 2026.11 et plus récent)

:::steps
- Ouvre « Settings › Marketplace » | Sélectionne « Add from link », saisis `https://github.com/plhery/peek-ha` et sélectionne « Add ». La première fois, le Marketplace te demande de connecter GitHub.
- Installe Peek | Ouvre Peek dans la liste et sélectionne « Install », puis « Set up ».
- Connecte-toi à Peek | Ton navigateur ouvre Peek. Vérifie ce que Home Assistant demande (voir tes colis, les ajouter et les archiver), puis sélectionne « Autoriser ».
- Reviens dans Home Assistant | Peek te renvoie par My Home Assistant (my.home-assistant.io). La première fois, il te demande l'adresse de ton Home Assistant.
:::

### Avec HACS (Home Assistant 2026.10)

:::steps
- Ajoute un dépôt personnalisé | Dans HACS, ajoute `https://github.com/plhery/peek-ha` comme dépôt personnalisé de type « Integration ».
- Télécharge Peek | Puis redémarre Home Assistant.
- Ajoute l'intégration | Va dans « Settings › Devices & services › Add integration », choisis Peek, connecte-toi à Peek et sélectionne « Autoriser ».
:::

Tu peux aussi l'installer à la main : copie le dossier `custom_components/peek` dans le dossier `custom_components` de ta configuration, redémarre, puis ajoute l'intégration comme ci-dessus.

## Comment ajouter la carte Peek parcels à mon tableau de bord ?

L'intégration charge elle-même sa carte sur chaque tableau de bord : il n'y a aucune ressource à ajouter. Une page déjà ouverte pendant que tu configures Peek n'a pas encore la carte : recharge-la d'abord, ou ferme l'app Home Assistant et rouvre-la.

:::steps
- Modifie un tableau de bord | Ouvre-le et sélectionne le crayon. Sur un tableau de bord que Home Assistant a créé pour toi, il te demande d'abord d'en prendre le contrôle.
- Sélectionne « Add card » | Puis l'onglet « By card ». L'onglet « By entity » cherche des entités, pas des cartes.
- Choisis « Peek parcels » | Cherche « Peek », choisis « Peek parcels » et enregistre.
:::

La carte existe en deux styles : une liste, par défaut, avec une ligne par colis qui montre le camion de son transporteur, son étape et son jour ; ou une carte par colis aux couleurs de son transporteur, comme les widgets de Peek pour iPhone et Android, avec Pip, la ville du dernier scan, le nom du colis, son étape et son jour. Les deux suivent les sections de Peek, en commençant par les colis qui demandent ton attention, et se terminent par « +N autres » quand il y a plus de colis que de lignes.

La carte suit ton thème, clair ou sombre, et ta langue, si c'est l'anglais ou le français. En YAML, c'est `type: custom:peek-parcels-card` ; le README de l'intégration liste ses options.

## Puis-je l'utiliser dans des automatisations ou demander à Assist ?

Oui. Le README propose quatre automatisations toutes prêtes :

- une notification sur ton téléphone quand un colis part en livraison ;
- l'ajout des numéros de suivi de tes e-mails d'expédition, lus avec l'intégration IMAP de Home Assistant ;
- un message le matin avec ce qui arrive aujourd'hui ;
- l'archivage de ce qui a été livré, chaque soir.

Quand tu ajoutes des colis à partir d'un texte, Peek ajoute chaque numéro de suivi qu'il trouve, 10 au plus à la fois, et ne garde que les numéros et le nom que tu donnes : envoie donc seulement la partie expédition d'un e-mail quand tu le peux.

Une fois qu'une des entités de Peek est exposée à Assist (« Settings › Voice assistants › Expose »), tu peux demander « Qu'est-ce qui arrive aujourd'hui ? » ou où se trouve ton colis venant de telle boutique. L'outil Peek d'Assist ne fait que lire : il n'ajoute ni n'archive jamais de colis. Ce qu'il renvoie (noms, boutiques, transporteurs, étapes, jours prévus et lieu du dernier scan) part vers l'agent de conversation que tu as choisi, qui peut être un service cloud.

## Qu'est-ce que l'intégration ne fait pas ?

- **Supprimer ou modifier des colis.** Home Assistant demande seulement à voir tes colis, et à les ajouter et les archiver. Tu peux restaurer un colis archivé dans Peek.
- **Connecter Claude, ChatGPT ou Gemini.** Ils se connectent eux-mêmes à Peek : voir le guide [Suivre ses colis avec ChatGPT, Claude ou Gemini](guide:ai-assistants).

## Que garde Home Assistant, et comment supprimer l'intégration ?

Home Assistant ne parle qu'à Peek, sur peektracker.com, et garde les jetons de la connexion dans sa configuration, comme toute intégration. Son historique enregistre les chiffres des capteurs et les événements « Mises à jour des colis », avec le nom du colis, son transporteur, son étape et son dernier lieu, mais pas les listes de colis des capteurs. La carte tourne dans ton navigateur avec les données de Home Assistant lui-même et ne charge rien d'ailleurs.

Pour la supprimer, supprime Peek dans « Settings › Devices & services » : Home Assistant rend son accès à Peek, qui oublie la connexion, et tes colis restent dans Peek. Si Peek est injoignable à ce moment-là, ou pour couper Home Assistant du côté de Peek, sélectionne « Déconnecter » à côté de Home Assistant sur le site de Peek (pas dans ses apps), dans « Réglages » → « Livraisons » → « Assistants ». Une intégration que tu gardes ensuite affiche « Authentication expired for Peek » ; la reconfigurer relance la même connexion.

:::sources
- [Peek pour Home Assistant : README (en anglais)](https://github.com/plhery/peek-ha) – ce qu'elle ajoute, versions, installation, connexion, la carte, automatisations, Assist, confidentialité, suppression
- [Peek pour Home Assistant : hacs.json](https://github.com/plhery/peek-ha/blob/main/hacs.json) – la plus ancienne version de Home Assistant sur laquelle elle s'installe, 2026.10
- [Home Assistant : Marketplace (en anglais)](https://www.home-assistant.io/integrations/marketplace/) – Settings › Marketplace, Add from link, connexion à GitHub, Install, réservé aux administrateurs, remplace HACS à partir de 2026.11
- [Peek : Connecter Peek à Claude, ChatGPT ou Gemini](https://peektracker.com/fr/assistants) – les étapes pour Home Assistant sur la page de Peek, 2026.11 ou plus récent
- [Peek sur GitHub : la configuration de chaque assistant](https://github.com/plhery/peek-delivery-tracker/blob/main/src/assistants/setups.tsx) – les noms des menus de Home Assistant
- [Peek sur GitHub : textes en français](https://github.com/plhery/peek-delivery-tracker/blob/main/shared/locales/fr.json) – version, étapes, compte, bêta
- [Peek sur GitHub : Réglages, Assistants](https://github.com/plhery/peek-delivery-tracker/blob/main/src/components/Assistants.tsx) – assistants connectés, Déconnecter
- [Peek : aide (en anglais)](https://peektracker.com/support.html) – connexion avec un code à six chiffres
- [Peek sur GitHub : conditions d'utilisation (en anglais)](https://github.com/plhery/peek-delivery-tracker/blob/main/TERMS.md) – gratuit, sans frais ni abonnement
:::
