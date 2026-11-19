---
title: Peek pour Android : obtenir la bêta et le widget « Colis »
description: Peek pour Android est en bêta : rejoins le test Google Play ou installe-le depuis F-Droid ou GitHub. Sans compte. Ajoute le widget, partage tes liens vers Peek.
slug: application-suivi-colis-android
picture: Un téléphone Android avec le widget « Colis » sur son écran d'accueil, et Pip, le colis avec un visage, qui jette un œil par-dessus le téléphone.
published: 2026-11-19
updated: 2026-11-19
---

Peek pour Android est une bêta, non publique sur Google Play : tu l'obtiens depuis [peektracker.com/android](https://peektracker.com/android), en rejoignant le test Google Play ou en l'installant depuis le dépôt F-Droid de Peek ou depuis GitHub. Il demande Android 12 ou plus récent, pas de compte, et il ajoute un widget « Colis » à ton écran d'accueil et « Ajouter à Peek » à ton menu de partage.

Il suit les colis de plus de 3 500 transporteurs : colle un numéro de suivi, le lien d'un transporteur ou tout un e-mail d'expédition, et [Peek](/) trouve le transporteur et affiche le trajet sur une carte. C'est gratuit, sans achats intégrés et sans pub. Pour un iPhone, un iPad ou un Mac, voir [Peek pour iPhone, iPad et Mac](guide:iphone-app).

## Peek est-il sur Google Play ?

Seulement pour les testeurs. Peek est en test fermé, et sa page Google Play ne s'ouvre pour personne en dehors de ce test. Google l'ouvre à tous dès qu'au moins 12 testeurs l'ont gardé 14 jours d'affilée, et n'importe qui peut rejoindre le test. Sur ton téléphone Android, ouvre [peektracker.com/android](https://peektracker.com/android), « Essaie Peek pour Android » :

:::steps
- « Rejoins le groupe des testeurs » | Touche « Rejoindre le groupe », rejoins le groupe Google sur la page qui s'ouvre, puis reviens.
- « Deviens testeur sur Google Play » | Touche « Ouvrir Google Play », puis **Devenir testeur** sur la page qui s'ouvre.
- « Installe-la et garde-la deux semaines » | Touche « Installer Peek », et garde l'app 14 jours.
:::

Tant que tu n'as pas suivi le lien du groupe dans ce navigateur, les deux boutons suivants affichent « Rejoins d’abord le groupe ». Tu peux quitter le test à tout moment, sur la même page Google Play.

### Comment installer Peek sans Google Play ?

Ajoute à la main le dépôt de Peek à l'app F-Droid, ou télécharge l'app depuis GitHub. Peek n'est pas dans le catalogue principal de F-Droid.

:::steps
- Copie l'adresse | Sur [peektracker.com/android](https://peektracker.com/android), sous « Sans Google Play », touche « Copier l’adresse ».
- Ajoute le dépôt | Dans l'app F-Droid, va dans **Paramètres** › **Dépôts** › **+**, puis colle l'adresse en entier.
- Installe Peek | Touche le nouveau dépôt pour voir ses apps, puis installe « Peek ». F-Droid n'installe rien tant que tu ne l'as pas choisi.
:::

Ou touche « Ouvrir GitHub » sur la même page : dans la version la plus récente des [versions de Peek sur GitHub](https://github.com/plhery/peek-delivery-tracker/releases), touche le fichier .apk, puis laisse ton navigateur l'installer. Les versions de Peek y sont marquées « Pre-release ».

Cette version est signée par Peek, pas par Google, donc elle ne peut pas mettre à jour une copie de Google Play, ni l'inverse : si tu as Peek depuis Google Play, ou une ancienne copie du dépôt ou de GitHub, désinstalle-la d'abord. Les mises à jour arrivent par le dépôt F-Droid de Peek. Aucune des deux voies ne fait de toi un testeur Google Play.

### Quelle différence entre les versions Google Play et F-Droid ?

| Quoi | Version Google Play | Version F-Droid et GitHub |
| --- | --- | --- |
| Services de Google | Firebase, ML Kit, Play Integrity | Aucun |
| Scanner de codes-barres | Oui | Non, et pas d'autorisation pour l'appareil photo |
| Notifications | Par les services Google Play | Par une app UnifiedPush comme ntfy, ou Peek vérifie environ toutes les 15 minutes |
| Signée et mise à jour par | Google, via Google Play | Peek, via son dépôt F-Droid |

Les notifications de chaque version sont expliquées dans [les notifications de livraison](guide:parcel-notifications).

## De quelle version d'Android Peek a-t-il besoin ?

Android 12 ou plus récent, dans les deux versions. Un appareil sans appareil photo au dos peut quand même l'installer, simplement sans le scanner.

Il parle dix langues : anglais, allemand, français, italien, espagnol, portugais, portugais du Brésil, polonais, russe et chinois simplifié. Choisis-en une dans « Réglages » › « Préférences » › « Langue » (« Réglages » s'ouvre avec le bouton du compte, en haut de « Livraisons »), ou dans le réglage de langue par app d'Android. Si tu la choisis dans les réglages de Peek, « Ajouter à Peek », le nom du widget dans le sélecteur et le raccourci « Ajouter un colis » restent dans la langue de ton téléphone ; les textes du widget lui-même suivent celle de Peek.

## Peut-on utiliser Peek sur Android sans compte ?

Oui. Colle un numéro de suivi sur le premier écran et Peek le suit, listé sur ce téléphone seulement ; le widget et le raccourci du prochain colis fonctionnent aussi. « Livraisons » affiche « Tous tes colis au même endroit », avec « Sur cet appareil seulement, pour l’instant. » et un bouton « Se connecter ».

Un colis est oublié 30 jours après sa livraison ; « Réglages » › « Sur cet appareil » › « Tout oublier » les oublie tous d'un coup. Les sauvegardes du téléphone lui-même incluent les colis qu'il suit et les préférences de Peek.

Un compte ajoute la même liste sur tous tes appareils et sur peektracker.com, le passeport et les amis, les réglages de notifications et « Ajouter des colis par e-mail » ([suivre ses colis depuis ses e-mails](guide:add-parcels-by-email)). Connecte-toi avec « Continuer avec Google » ou « Se connecter par e-mail » ; il n'y a pas de connexion avec Apple sur Android. « Explorer la démo », sur l'écran de connexion, ne demande pas de compte non plus. [Utiliser Peek sans compte](guide:account-or-guest) compare les deux.

## Comment ajouter le widget Peek à mon écran d'accueil ?

Ajoute « Colis », l'unique widget de Peek, depuis le sélecteur de widgets, où il est décrit ainsi : « Consulte tes colis en route en un coup d’œil, le prochain en premier. » Les étapes de Google :

:::steps
- Ouvre les widgets | Sur un écran d'accueil, maintiens le doigt sur un espace vide, puis touche **Widgets**.
- Trouve Peek | Touche Peek pour voir son widget, « Colis ».
- Place-le | Maintiens le doigt sur le widget, fais-le glisser où tu veux, puis relâche.
:::

Google prévient : « Certaines de ces étapes ne s'appliquent qu'à Android 14 ou version ultérieure. »

« Colis » fait d'abord quatre cases sur deux et se réduit jusqu'à deux sur deux. Il liste les colis en route, puis ceux livrés dans la dernière journée, dans l'ordre de Peek. Chaque colis ouvre sa page ; le reste du widget ouvre ta liste. Il est redessiné au moins toutes les 20 minutes, et ses jours changent à minuit, même quand l'app est fermée.

| Le widget affiche | Ce que ça veut dire |
| --- | --- |
| « Aucun colis en route pour le moment » | Aucun colis n'est en route : « Ajoute un numéro ou un lien de suivi pour commencer. » |

« Colis » ne s'ajoute qu'à l'écran d'accueil : il n'y a pas de widget pour l'écran verrouillé, ni rien qui ressemble aux activités en direct.

### Peek a-t-il des raccourcis sur son icône ?

Oui. Maintiens le doigt sur l'icône de Peek pour trouver « Ajouter un colis », qui ouvre l'écran du même nom, et un raccourci au nom de ton prochain colis en route, qui l'ouvre. Pour en épingler un sur ton écran d'accueil, maintiens le doigt dessus et fais-le glisser à sa place. Peek n'a pas de commandes vocales sur Android : ces raccourcis remplacent les actions Siri de l'iPhone.

## Comment partager un lien de suivi vers Peek sur Android ?

Partage-le et choisis « Ajouter à Peek », qui accepte tout texte ou lien partagé.

:::steps
- Partage | Partage le numéro de suivi, le lien ou la page depuis n'importe quelle app, puis choisis « Ajouter à Peek ».
- Enregistre | Sur la carte qui s'ouvre, touche « Enregistrer le suivi ». Elle indique « Enregistré. Ouvre Peek dans les 10 minutes pour vérifier et ajouter ton colis. »
- Ouvre Peek | Dans ces 10 minutes, « Ajouter un colis » s'ouvre avec les informations déjà remplies.
:::

Les liens de colis de Peek (peektracker.com/p/…) s'ouvrent dans l'app une fois qu'elle est installée. Pour envoyer un colis à quelqu'un d'autre, voir [partager le suivi d'un colis](guide:share-parcel).

### Peek peut-il scanner un code-barres sur Android ?

Seule la version Google Play le peut. Touche l'icône de code-barres au bout du champ de suivi (nom lu à voix haute : « Scanner le code-barres »), sur le premier écran ou dans « Ajouter un colis » ; avec une liste vide, il y a aussi un bouton « Scanner le code-barres ». « Scanner un code-barres de suivi » s'ouvre avec l'appareil photo arrière et suit le code-barres le plus proche du centre. Si tu refuses l'appareil photo, le scanner disparaît jusqu'à ce que tu l'autorises dans les paramètres d'Android. Tu ne sais pas quel numéro est le numéro de suivi ? Voir [où trouver son numéro de suivi](guide:find-tracking-number).

:::sources
- [Peek : Essaie Peek pour Android](https://peektracker.com/android) – les trois étapes du test, Android 12, le dépôt F-Droid, GitHub, la signature
- [Peek : notes sur Google Play (en anglais)](https://github.com/plhery/peek-delivery-tracker/blob/main/docs/google-play/README.md) – test fermé, 12 testeurs pendant 14 jours, le groupe des testeurs
- [Peek : app Android (en anglais)](https://github.com/plhery/peek-delivery-tracker/blob/main/android/README.md) – les deux versions, contenu et rafraîchissement du widget, raccourcis, clés de signature, préversions
- [Peek : fiche F-Droid (en anglais)](https://github.com/plhery/peek-delivery-tracker/blob/main/android/fdroid/com.plhery.peekdeliverytracker.yml) – bêta, nom, quelles copies antérieures désinstaller
- [F-Droid : Comment ajouter un dépôt à F-Droid](https://f-droid.org/fr/tutorials/add-repo/) – Paramètres › Dépôts › +, puis le choix des apps à installer
- [Peek : versions sur GitHub](https://github.com/plhery/peek-delivery-tracker/releases) – les fichiers .apk, marqués Pre-release
- [Peek : textes en français](https://github.com/plhery/peek-delivery-tracker/blob/main/shared/locales/fr.json) – chaque libellé de Peek cité ici
- [Peek : manifeste Android](https://github.com/plhery/peek-delivery-tracker/blob/main/android/app/src/main/AndroidManifest.xml) – « Ajouter à Peek », liens de colis, appareil photo, sauvegardes
- [Peek : le widget « Colis »](https://github.com/plhery/peek-delivery-tracker/blob/main/android/app/src/main/res/xml/parcels_widget.xml) – écran d'accueil seulement, ses tailles
- [Peek : Réglages](https://github.com/plhery/peek-delivery-tracker/blob/main/android/app/src/main/kotlin/com/plhery/peekdeliverytracker/ui/settings/SettingsSheet.kt) – « Tout oublier », « Langue », pas d'activités en direct
- [Peek : le scanner de la version Play](https://github.com/plhery/peek-delivery-tracker/blob/main/android/app/src/play/kotlin/com/plhery/peekdeliverytracker/platform/CameraLabelScanner.kt) – codes-barres, autorisation de l'appareil photo
- [Peek : aide (en anglais)](https://peektracker.com/support.html) – pas besoin de compte, « Tout oublier »
- [Aide Android : Ajouter des applications, des raccourcis et des widgets aux écrans d'accueil](https://support.google.com/android/answer/9450271?hl=fr) – ajouter un widget, épingler un raccourci, Android 14
:::
