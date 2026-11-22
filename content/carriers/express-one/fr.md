---
title: Suivi Express One : statuts, points relais et contacts
description: Suis un colis Express One en Hongrie : d'où vient le numéro, signification des statuts, points relais, consignes, livraison manquée et contacts.
slug: suivi-express-one
published: 2026-11-22
updated: 2026-11-22
---

Express One Hungary est un transporteur hongrois détenu par [Austrian Post](carrier:austrian-post), qui livre pour les boutiques en ligne avec environ 700 livreurs et plus de 2 300 points relais et consignes. Suis un colis grâce à son identifiant de colis sur [tracking.expressone.hu](https://tracking.expressone.hu/) ou colle-le dans le champ ci-dessus. Le numéro figure dans l'e-mail qu'Express One envoie une fois le colis enregistré et parvenu dans son réseau.

## Numéro de suivi Express One

Express One t'envoie un préavis par e-mail dès que l'expéditeur a enregistré le colis et que celui-ci est physiquement arrivé chez Express One ; un SMS n'est envoyé que si l'expéditeur paie cette option. En Hongrie, ces messages indiquent le créneau de livraison, les coordonnées du livreur, le montant éventuel du contre-remboursement et un lien pour modifier la livraison. Les colis à destination de l'étranger ne reçoivent aucun préavis. Aucun message ? Demande à la boutique, et consulte [comment trouver son numéro de suivi](guide:find-tracking-number).

Le suivi accepte aussi la référence expéditeur ou le numéro de document, mais ceux-ci exigent l'identifiant entreprise à 5 chiffres de l'expéditeur : utilise plutôt l'identifiant de colis (le numéro du code-barres).

Express One ne publie pas de format officiel. Les identifiants de colis observés jusqu'ici se présentent sous deux formes (chiffres inventés) :

| Numéro | À qui il appartient |
| --- | --- |
| `0482917364829` | 13 chiffres : un identifiant de colis Express One, mais [DPD France](carrier:dpd-fr) et d'autres transporteurs utilisent aussi cette longueur. |
| `148291736482917364821011` | 24 chiffres se terminant par un code postal hongrois : Express One. Austrian Post, [InPost](carrier:inpost), [bpost](carrier:bpost) et [Ciblex](carrier:ciblex) utilisent aussi 24 chiffres. |
| `48291736482917` | 14 chiffres : DPD, jamais Express One. |
| `48291736482` | 11 ou 12 chiffres : GLS, jamais Express One. |
| `RR482917367HU` | Deux lettres, 9 chiffres, `HU` : Magyar Posta, jamais Express One. |

Fie-toi au transporteur nommé dans l'e-mail de la boutique ; plus de détails dans [les formats de numéros de suivi](guide:tracking-number-formats).

## Les statuts de suivi Express One expliqués

tracking.expressone.hu s'affiche en anglais ou en hongrois selon la langue de ton navigateur, avec un sélecteur EN/HU. Chaque scan comporte un code, un libellé, une date et une heure, et les scans de hub ou de dépôt ajoutent le lieu entre parenthèses, par exemple « (HU - Budapest) ». Voici les libellés en anglais :

| Statut Express One | Ce qu'il signifie pour toi |
| --- | --- |
| `Shipping details received, shipment not yet handed over to EXOHU` | L'expéditeur a [enregistré le colis](guide:tracking-statuses) ; Express One ne l'a pas encore reçu. |
| `Shipment (pan) data uploaded` | Les données du colis sont dans le système d'Express One, en général juste après un scan au hub. |
| `Arrived at central hub` | Arrivé au hub central de Budapest. |
| `Departed central hub` | Le colis a quitté le hub. |
| `Arrived at depot` | Arrivé au dépôt chargé de la livraison à ton adresse. |
| `Out for delivery` | Dans la tournée du livreur aujourd'hui. |
| `Delivery schedule updated` | Le plan de livraison a été recalculé ; le colis n'a pas bougé. |
| `Processing in progress` | La livraison a été reportée à un créneau ultérieur, sans motif précisé. |
| `Addressee not present at delivery address` | Le livreur s'est présenté et personne n'était là. |
| `Addressee unavailable` | La seconde tentative a également échoué. |
| `Obstacles of nature` | Les intempéries ont interrompu la tournée ; cela compte comme une tentative et le colis repartira en livraison. |
| `Successful delivery` | Livré. |

EXOHU désigne Express One Hungary : le premier statut signifie que seule l'étiquette existe. La barre au-dessus de l'historique utilise ses propres intitulés, de `Shipment data uploaded` à `Successfully delivered`. En hongrois, « livré » s'affiche `Sikeres kézbesítés`.

## Livraison Express One, points relais et avis de passage

**Horaires.** Le préavis indique une fenêtre de 4 heures. Le matin de la livraison, un message annonce un créneau de 2 heures qu'Express One qualifie d'indicatif uniquement : le livreur peut arriver plus tôt ou plus tard. Ce jour-là, la page de suivi montre combien d'arrêts restent au livreur avant toi. Il n'y a pas de livraison le week-end, hormis la livraison payante du samedi réservée aux expéditeurs professionnels.

**Modifications.** Le bouton « Modification » dans le SMS ou l'e-mail du matin permet de changer l'horaire ou l'adresse, ou d'orienter le colis vers un point relais ou une consigne, y compris une heure avant l'arrivée du livreur. Les modifications ne sont possibles que le premier jour de livraison et une seule fois. Certaines options dépendent de l'expéditeur (un colis volumineux ne peut pas aller en consigne). Les conditions générales d'Express One précisent qu'une modification compte comme une tentative de livraison et peut entraîner un supplément ; un billet du blog Express One de 2024 indique que la redirection est gratuite.

**À la porte.** Les particuliers signent et communiquent un numéro de pièce d'identité. Le contre-remboursement se règle en espèces (jusqu'à 300 000 Ft), par carte (jusqu'à 1 000 000 Ft) ou à l'avance via un lien dans le message de préavis ; en Exopoint, la limite en espèces est de 250 000 Ft. Tu ne peux pas ouvrir le colis avant de signer et payer.

**Livraison manquée.** Selon les conditions générales, Express One t'informe par e-mail ou SMS au plus tard le jour ouvré suivant, tente une nouvelle livraison gratuite et garde le colis au maximum jusqu'au 5e jour ouvré. Entre-temps, tu peux demander une nouvelle date ou un point relais. Après un second échec, le colis repart chez l'expéditeur ; un article de blog de 2024 mentionne qu'il attend d'abord 5 jours ouvrés au dépôt central de Budapest.

**Points relais et consignes.** De nombreuses boutiques proposent un point relais ou une consigne Exopoint lors de la commande. La page des points relais d'Express One indique une garde jusqu'à une semaine, 24 h/24. Ses articles de blog divergent : 3 jours en EXOBOX, 2 en Alzabox, jusqu'à 7 en point Packeta (2025), 72 heures en consigne (2026), 5 jours en point relais (2024), et seules les consignes EXOBOX et Alzabox sont ouvertes 24 h/24. Le retrait au centre logistique de Budapest (H-1239 Budapest, Európa street 12, building L1) nécessite un rendez-vous auprès du service client.

## Colis Express One en retard, endommagé ou perdu

**Aucune mise à jour ou introuvable.** Express One n'indique pas de délai d'attente. Son outil de suivi précise qu'un numéro peut être erroné, vieux de plus de 180 jours ou pas encore enregistré par l'expéditeur, et invite à réessayer plus tard. Ses conditions lui permettent d'effacer les données si aucun colis n'arrive sous 5 jours ouvrés, et qualifient le délai annoncé de purement indicatif. Consulte [le suivi qui ne se met plus à jour](guide:tracking-not-updating).

**Endommagé ou mauvais article.** Préviens dès que possible la boutique auprès de laquelle tu as commandé : Express One ouvre une réclamation dès que l'expéditeur la signale, et répond de la livraison, pas du contenu. Signale les dommages visibles lors de la remise par le livreur ; signale les dommages cachés par écrit dans les 3 jours ouvrés suivant la livraison, sous peine de déchéance.

**Perdu.** C'est généralement l'expéditeur qui réclame, mais tu peux le faire si le colis t'a été remis ou si l'expéditeur te cède son droit par écrit. Une réclamation écrite pour perte totale doit être déposée dans les 6 mois à compter du 15e jour après l'expédition, avec le numéro de bordereau, une preuve du contenu et de la valeur ainsi que le formulaire de réclamation d'Express One. Express One répond sous 30 jours calendaires ; si la réponse ne te convient pas, tu peux saisir la NMHH, l'autorité hongroise des médias et des communications, sous 30 jours.

## Service client et numéro de téléphone Express One

Coordonnées publiées sur la page de contact anglaise d'Express One, joignables les jours ouvrés de 8 h à 17 h (la FAQ indique jusqu'à 18 h), heure de Hongrie :

- **Service client :** +36 1 8 777 400, ugyfelszolgalat@expressone.hu, également si ton livreur ne répond pas. En période chargée, Express One conseille d'écrire.
- **Service client international :** +36 1 8 777 410, intcs@expressone.hu ; réclamations à intcsreklamacio@expressone.hu.
- **Réclamations dommages :** +36 1 8 777 470, karugy@expressone.hu.
- **Formulaire de contact :** sur [la page de contact d'Express One](https://expressone.hu/en/kapcsolat), avec des objets comme « I am expecting a parcel » et « Delivery complaint » ; les deux exigent le numéro de colis.

La page Parcel Tracking d'Express One mentionne également le +36 1 8 777 444 et un chatbot.

## Suivre un colis Express One avec Peek

Un numéro à 13 chiffres n'est pas interrogé automatiquement chez Express One : [Peek](/) le soumet d'abord à d'autres transporteurs. Si aucun ne le connaît, Peek affiche « pas encore trouvé · Peek continue de vérifier » : appuie sur « Changer » et choisis « Express One ». Pour un numéro à 24 chiffres, Peek interroge Express One en premier lorsque tu cherches depuis la Hongrie, avant [Austrian Post](carrier:austrian-post), [InPost](carrier:inpost) et [Ciblex](carrier:ciblex) ; en dehors de la Hongrie, Peek interroge d'abord ces trois autres transporteurs, puis Express One. Tu peux aussi coller un lien tracking.expressone.hu contenant `?plc_number=`, qui désigne directement Express One quel que soit le format.

Les scans d'Express One indiquent une heure sans fuseau horaire : Peek les lit sur l'horloge du lieu du scan (heure de Hongrie pour les scans en Hongrie) et les affiche dans ton fuseau horaire (« Heures dans ton fuseau horaire : »). Peek récupère le statut ainsi que, pour les scans de hub, la ville et le pays. Les libellés restent ceux d'Express One, sauf `Out for delivery` ; `Successful delivery` correspond à livré et un trajet retour à retourné. Un scan inconnu garde le libellé d'Express One sans étape. Il n'y a ni date de livraison prévue, ni poids, ni point relais, et la barre de progression comme la signature ne sont pas reprises.

Peek interroge directement Express One jusqu'à toutes les 10 minutes, ou toutes les 2 minutes à partir de 6 h sur l'horloge du dernier scan (heure de Hongrie en Hongrie) le jour où Express One scanne le colis en livraison, tant que le colis est ouvert dans Peek ou le jour de la livraison avec les notifications activées. Après deux jours sans nouvelle (12 heures tant que le colis reste sur « Étiquette créée » ou « Pris en charge »), Peek passe à une vérification par heure. Pour les notifications, choisis « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Après quatre jours sans scan en transit, hors douane ou incident signalé, Peek affiche « Aucune mise à jour depuis… », rappelle qu'« Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger Express One. » et propose « Demande de l’aide à Express One ».

## Questions fréquentes sur Express One

### Que signifie « shipment not yet handed over to EXOHU » ?

L'expéditeur a créé l'envoi, mais Express One Hungary n'a pas encore reçu le colis. Si ce statut reste figé plusieurs jours, contacte la boutique.

### Combien de temps un colis Express One attend-il en point relais ?

Jusqu'à une semaine, à toute heure, indique la page des points relais d'Express One ; ses billets de blog mentionnent de 2 à 7 jours selon le point, et un accès 24 h/24 uniquement pour les consignes EXOBOX et Alzabox.

### Express One en Slovaquie ou en Slovénie est-elle la même société ?

Non. Elles appartiennent aussi à Austrian Post, mais ce sont des sociétés distinctes ; cette page et « Express One » dans Peek couvrent uniquement l'entité hongroise.

:::sources
- [Express One: Parcel Tracking](https://expressone.hu/en/csomagkovetes) – arrêts du livreur, centre d'appel, chatbot
- [Express One: Tracking](https://tracking.expressone.hu/) – types d'identifiants, Company ID, message introuvable, 180 jours, statuts, langues
- [Express One: Frequently asked questions](https://expressone.hu/en/gyik) – modification, créneau de 2 heures, samedi, contre-remboursement, dommages, erreur d'article
- [Express One: GYIK](https://expressone.hu/gyik) – plafonds du contre-remboursement
- [Express One: Contact](https://expressone.hu/en/kapcsolat) – téléphones, e-mails, horaires, formulaire, centre logistique
- [Express One: Parcel point](https://expressone.hu/en/csomagpont) – 2 300 points et consignes, une semaine, 24 h/24, choix à la commande
- [Express One: Home](https://expressone.hu/en) – Austrian Post, 700 livreurs, redirection jusqu'à une heure avant
- [Express One: General Terms and Conditions](https://expressone.hu/downloads/General_Terms_and_Conditions_2026_08_15.pdf) – tentatives, garde, pièce d'identité, frais de modification, réclamations, NMHH, effacement des données
- [Express One: Merre jár a csomagom?](https://expressone.hu/blog/merre-jar-a-csomagom-csomagkovetes-es-ertesitesi-rendszerek-alkalmazasa) – préavis après enregistrement et arrivée, fenêtres de 4 heures et 2 heures, option SMS
- [Express One: Csomag átirányítás és a kézbesítés átütemezése](https://expressone.hu/blog/csomag-atiranyitas-es-a-kezbesites-atutemezese) – redirection gratuite, 5 jours au dépôt de Budapest ou en point relais
- [Express One: Minden, amit az EXOPOINT megoldásokról tudni érdemes](https://expressone.hu/blog/minden-amit-az-exopoint-megoldasokrol-tudni-erdemes) – horaires d'ouverture, 3, 2 et 7 jours
- [Express One: Csomagküldés csomagautomatába](https://expressone.hu/blog/csomagkuldes-csomagautomataba-miert-valasztjak-egyre-tobben) – 72 heures en consigne
- [Express One: eBOX GYIK](https://expressone.hu/ebox/faq) – contenu des notifications, aucune pour l'export, 250 000 Ft en espèces en Exopoint
- [Austrian Post: Austrian Post Group acquires Hungarian parcel delivery provider](https://news.post.at/presse/en/post/id/2255390/) – filiale
- [Austrian Post: International subsidiaries](https://post.at/en/j/c/careers-austrian-post-subsidiaries-international) – sociétés Express One dans d'autres pays
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – données lues par Peek
:::
