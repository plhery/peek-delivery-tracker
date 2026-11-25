---
title: Quand mon colis va-t-il arriver ? L'estimation de Peek IA
description: Peek IA est l'estimation de Peek pour l'arrivée de ton colis, à côté de la date du transporteur. Quand elle s'affiche, sa fiabilité, comment la désactiver.
slug: quand-arrive-mon-colis
picture: Pip, le colis avec un visage, regarde un téléphone où la carte d'un colis affiche, sous la date du transporteur, une petite étincelle et « Peek IA avant 16:00 ».
published: 2026-11-24
updated: 2026-11-25
---

Quand [Peek](/) a un indice solide sur l'arrivée de ton colis, Peek IA le dit à côté de la date du transporteur, ou à sa place quand le transporteur n'en donne pas : « probablement avant 16:00 », « probablement aujourd’hui ou demain », « peut-être plus tôt » ou « peu probable aujourd’hui ». C'est l'estimation de Peek, jamais une promesse du transporteur, et la plupart des colis n'en affichent aucune.

Peek IA est activé par défaut, avec ou sans compte : sur peektracker.com, dans l'app iPhone, iPad et Mac, et dans l'[app Android](guide:android-app), qu'elle vienne de Google Play, du dépôt F-Droid de Peek ou de GitHub.

:::steps
- Regarde ta liste | Sous la date du transporteur sur la carte d'un colis, ou à côté du statut dans « À suivre » : « ✦ Peek IA » et quelques mots, comme « avant 16:00 ».
- Ouvre le colis | Une petite carte affiche par exemple « ✦ Peek IA probablement avant 16:00 », avec une ligne qui dit pourquoi.
- Touche (i) | Le bouton (i) (« À propos de Peek IA ») ouvre le panneau « Peek IA » : l'estimation, avec en dessous ce que dit le transporteur lui-même, comme « Selon DPD : aujourd’hui ».
- Lis les détails | Souvent les jours ou les heures probables, plus ou moins teintés selon leurs chances ; puis « Pourquoi », « Fiabilité » et l'interrupteur « Afficher Peek IA ».
:::

Le widget « Colis » sur [iPhone](guide:iphone-app) et sur Android l'affiche quand il y a la place, et les [assistants connectés](guide:ai-assistants) la reçoivent comme l'estimation de Peek IA, pas celle du transporteur. Les [notifications](guide:parcel-notifications), les e-mails de livraison, les passeports des amis et l'aperçu d'un lien partagé ne la montrent pas. La [démo de Peek](https://peektracker.com/demo) a des colis d'exemple avec une estimation.

## Quand vais-je recevoir mon colis ? Ce que Peek IA peut te dire

Peek IA ne se prononce que lorsque l'un des cinq indices ci-dessous s'applique, chacun juste au moins 8 fois sur 10 sur les colis suivis par Peek.

| L'indice | Peek IA dit | Sa ligne d'explication, par exemple |
| --- | --- | --- |
| En cours de livraison, sans heure donnée par le transporteur | « probablement avant 16:00 » | « En cours de livraison depuis 09:12. DPD ne donne pas d’heure. » |
| Le transporteur dit aujourd'hui ; pas encore parti en livraison l'après-midi | « peu probable aujourd’hui » | « Selon DPD, le colis arrive aujourd’hui, mais il n’est pas encore parti en livraison. » |
| La date du transporteur était hier ; scanné dans les dernières 24 heures | « probablement aujourd’hui ou demain » | « DPD l’annonçait pour hier, mais le colis avance de nouveau. » |
| La date du transporteur est dans plusieurs jours ; les colis comme lui arrivent généralement avant | « peut-être plus tôt » | « DPD annonce le ven 27 nov. Le colis est en avance. » |
| Pas de date du transporteur | « probablement d’ici jeu 26 nov », « probablement cette semaine » et d'autres | « UPS ne donne pas de date. Le colis est arrivé en douane hier. » |

DPD et UPS tiennent ici la place de ton transporteur. Sur une carte, « peu probable aujourd’hui » devient « pas aujourd’hui », ou « peu probable » à côté d'une date du transporteur qui tombe aujourd'hui. Les heures s'affichent selon ton réglage (12 h ou 24 h).

Chaque estimation disparaît quand elle expire, même d'une page restée ouverte : « peu probable aujourd’hui » à minuit, « probablement avant 16:00 » à 16 h, « peut-être plus tôt » à la date du transporteur, et un jour une fois qu'il est passé.

### Pourquoi mon colis n'affiche-t-il aucune estimation ?

En général, parce que Peek n'a pas d'indice solide. Peek IA se tait aussi :

- une fois le colis livré, archivé, en point de retrait ou en retour, ou après une tentative de livraison (voir [avis de passage](guide:missed-delivery)) ;
- avant son premier vrai scan : une étiquette seule ne suffit pas ;
- quand le transporteur range la livraison parmi ses étapes prévues, ou donne pour aujourd'hui une heure ou un créneau pas encore passé ;
- avant que le colis soit en cours de livraison, si Peek ne l'a pas vérifié récemment (depuis 36 heures, sauf pour « peu probable aujourd’hui ») ;
- une fois la date du transporteur dépassée de plus d'un jour (voir [quand s'inquiéter](guide:tracking-not-updating)) ;
- pour « peut-être plus tôt » et les estimations sans date du transporteur, 45 jours après le premier scan, ou 30 jours après le scan le plus récent.

## La date de livraison du transporteur est-elle fiable ? « Peut-être plus tôt » et « peu probable aujourd’hui »

Pas toujours, et Peek IA le dit dans deux cas : « peut-être plus tôt » quand les colis aux scans semblables à ceux du tien arrivent généralement avant la date du transporteur, et « peu probable aujourd’hui » quand le transporteur dit aujourd'hui mais que le colis n'est pas parti en livraison l'après-midi.

« Peut-être plus tôt » peut nommer le premier jour probable (« peut-être plus tôt, dès demain », « peut-être plus tôt, dès le jeu 26 nov »), et n'apparaît jamais quand le transporteur donne une fourchette de jours.

## Colis en cours de livraison mais pas encore livré : arrivera-t-il aujourd'hui ?

Peek IA te le dira peut-être. « Probablement avant 16:00 » veut dire que la plupart des colis de ce transporteur suivis par Peek sont arrivés avant cette heure le jour de leur livraison ; il ne s'affiche que si le transporteur ne donne pas d'heure et que le colis est parti avant. Tard dans la journée, « peu probable aujourd’hui » veut dire que peu de colis arrivent si tard : « Toujours en cours de livraison à cette heure. Chez DPD, peu de colis arrivent si tard. » Pour certains transporteurs, Peek IA ne dit rien pendant que le colis est en livraison, car Peek ne se fie pas à leurs scans « En livraison ».

Les horaires habituels de chaque transporteur, et pourquoi une camionnette peut encore tourner à 20 h, sont dans [à quelle heure les colis sont livrés](guide:delivery-times) ; ce que veut dire le statut, dans [les étapes du suivi](guide:tracking-statuses).

## Le transporteur ne donne pas de date de livraison : combien de temps ça va prendre ?

Peek IA peut donner un jour ou une période, selon ce dont son modèle est assez sûr : « probablement d’ici jeu 26 nov », « probablement aujourd’hui ou demain » (seulement si le transporteur livre demain), « probablement cette semaine » (d'ici dimanche) ou « probablement d’ici la fin de la semaine prochaine » (d'ici le dimanche suivant).

Pour les délais habituels depuis la Chine, voir [suivre un colis venant de Chine](guide:tracking-from-china).

## Comment Peek IA prédit-il les délais de livraison, et est-il fiable ?

Peek IA est un modèle entraîné, plus des tables de règles, sur le propre serveur de Peek, et il n'affiche que les types d'estimation qui ont vu juste au moins 8 fois sur 10.

Le modèle, un classifieur à gradient boosting fait d'arbres de décision, donne la probabilité qu'un colis soit arrivé d'ici chacun des prochains jours de livraison du transporteur. Il lit les scans et leur lieu, le transporteur, le service, le poids, le fuseau horaire et le pays de destination déclaré, et si le colis a été consulté sans compte. Les tables de règles contiennent l'heure de livraison habituelle de chaque transporteur et la fréquence à laquelle chaque type d'estimation s'est vérifié.

Le serveur calcule l'estimation chaque fois qu'il envoie les informations du colis à ton téléphone ou à ton navigateur, sans appeler aucun service extérieur ; ceux-ci ne font que l'afficher. Seul le serveur de Peek détient le modèle : ni lui ni son code d'entraînement ne sont dans [le code open source de Peek](guide:developers). Ce n'est pas un chatbot.

« Fiabilité » dit « Voit juste 8 fois sur 10 pour les colis comme celui-ci. », ou « Voit juste 8 fois sur 10 à cette heure-ci. » pour « peu probable aujourd’hui ». Le nombre est 8 ou 9 : Peek IA n'affiche rien sous 8 sur 10, n'annonce jamais plus de 9, et arrondit à l'inférieur.

## Comment désactiver Peek IA ?

Désactive « Peek IA » sous « Préférences » dans les « Réglages » de Peek, ou touche (i) sur la carte Peek IA d'un colis et désactive « Afficher Peek IA ».

:::steps
- Ouvre les Réglages | Sur le site, le bouton de ton compte (ton initiale), ou sans compte le bouton engrenage « Réglages ». Dans les apps, le bouton Compte en haut de « Livraisons » ; sur Mac, la fenêtre « Réglages » de Peek, onglet « Général ».
- Trouve Peek IA | « Peek IA » est la dernière ligne sous « Préférences », avec l'indication « Quand Peek a un indice solide, il te dit quand il pense que le colis arrivera. »
- Désactive-le | Peek IA disparaît des cartes, de « À suivre », des pages des colis et des widgets sur cet appareil.
:::

Avec un compte, ton choix te suit sur tes autres appareils, et les assistants connectés ne reçoivent plus l'estimation. Sans compte, il reste dans ce navigateur ou sur cet appareil ; [Utiliser Peek sans compte](guide:account-or-guest) compare les deux.

:::sources
- [Peek : Peek IA sur le serveur](https://github.com/plhery/peek-delivery-tracker/blob/main/src/server/peekAi.ts) – les cinq indices, 8 fois sur 10, 36 heures, quand il ne dit rien
- [Peek : les mots de Peek IA](https://github.com/plhery/peek-delivery-tracker/blob/main/src/lib/peekAi.ts) – les mots des cartes et des pages, quand chaque estimation expire, « Fiabilité »
- [Peek : la carte et le panneau Peek IA](https://github.com/plhery/peek-delivery-tracker/blob/main/src/components/PeekAi.tsx) – la carte, le panneau du (i), « Afficher Peek IA »
- [Peek : le modèle](https://github.com/plhery/peek-delivery-tracker/blob/main/src/server/peekAiModel.ts) – arbres de décision, ce qu'il lit, 45 et 30 jours
- [Peek : architecture (en anglais)](https://github.com/plhery/peek-delivery-tracker/blob/main/docs/ARCHITECTURE.md) – comment marche Peek IA, où il n'apparaît pas, le choix du compte
- [Peek : textes en français](https://github.com/plhery/peek-delivery-tracker/blob/main/shared/locales/fr.json) – chaque libellé de Peek cité ici
- [Peek : préférences du site](https://github.com/plhery/peek-delivery-tracker/blob/main/src/components/Preferences.tsx) – l'interrupteur « Peek IA », qui suit le compte
- [Peek : réglages iPhone, iPad et Mac](https://github.com/plhery/peek-delivery-tracker/blob/main/ios/PeekDeliveryTracker/SettingsViews.swift) – « Peek IA » sous « Préférences », « Général » sur Mac
- [Peek : réglages Android](https://github.com/plhery/peek-delivery-tracker/blob/main/android/app/src/main/kotlin/com/plhery/peekdeliverytracker/ui/settings/SettingsSheet.kt) – « Peek IA » sous « Préférences »
- [Peek : les outils des assistants](https://github.com/plhery/peek-delivery-tracker/blob/main/src/server/assistants/tools.ts) – l'estimation dans la liste de colis d'un assistant
- [App Store : Peek](https://apps.apple.com/app/id6823615370) – Peek IA dans les notes de version de l'app
- [Peek : notes de version Android (en anglais)](https://github.com/plhery/peek-delivery-tracker/blob/main/fastlane/metadata/android/en-US/changelogs/21.txt) – Peek IA dans l'app Android
- [Peek : Peek pour Android](https://peektracker.com/android) – l'app sur Google Play, F-Droid et GitHub
- [Peek : démo](https://peektracker.com/demo) – des colis d'exemple avec une estimation
:::
