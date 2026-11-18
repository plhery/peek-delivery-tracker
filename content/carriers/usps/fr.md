---
title: Suivi USPS : numéro, statuts et colis depuis les États-Unis
description: Suivre un colis USPS depuis les États-Unis : où trouver le numéro, sens des statuts en anglais, relais postal, douane, colis perdus et contact USPS.
slug: suivi-usps
published: 2026-11-17
updated: 2026-11-17
---

USPS, l’United States Postal Service, est l’opérateur postal public des États-Unis : il dessert toutes les adresses du pays et inclut le suivi (USPS Tracking) sans supplément sur la plupart de ses services de colis nationaux ainsi que sur de nombreux envois internationaux, mais pas tous. Saisis ton numéro dans USPS Tracking sur usps.com ou colle-le dans le champ ci-dessus ; aux États-Unis, tu peux aussi l’envoyer par SMS au `28777`. C’est l’expéditeur qui te communique ce numéro, le plus souvent dans l’e-mail d’expédition de la boutique.

## Numéro de suivi USPS : où le trouver et à quoi il ressemble

Sur l’étiquette, il s’agit de la suite de caractères placée sous le code-barres. En tant que destinataire, demande-le à l’expéditeur : ni les bureaux de poste (Post Offices) ni le service client d’USPS ne peuvent le rechercher à ta place. Aux États-Unis, les abonnés à Informed Delivery voient les numéros des colis en approche dans leur tableau de bord. Plus d’aide dans [retrouver un numéro de suivi](guide:find-tracking-number).

Les numéros USPS comptent de 13 à 34 caractères. Les formats les plus courants, avec des chiffres fictifs :

| Numéro (exemple fictif) | Ce que c’est |
| --- | --- |
| `9400 1000 4829 1057 3649 24` | 22 chiffres commençant par `94`, `95`, `93`, `92` ou `91` : l’étiquette habituelle d’un colis aux États-Unis. |
| `9205 5900 4829 1057 3649 2817 33` | 26 chiffres commençant par `92`. |
| `420 10001 9400 1000 4829 1057 3649 24` | Le code-barres complet tel qu’un lecteur le lit : `420`, le code postal américain (ZIP Code), puis le numéro du colis. Tu peux retirer le préfixe avec le ZIP. |
| `CP482910576US` | Un numéro postal international : 2 lettres, 9 chiffres et `US`. Pour ses réclamations internationales, USPS mentionne des numéros commençant par `EA` à `EZ`, `CA` à `CZ`, `HC` à `HZ` ou `RA` à `RZ`. |

Le numéro imprimé sous le code-barres de l’avis de passage PS Form 3849 laissé par un facteur aux États-Unis fonctionne lui aussi dans USPS Tracking.

Certaines formes sont partagées avec d’autres réseaux : des numéros à 22 chiffres commençant par `9261` ou `9361` peuvent aussi relever de DHL eCommerce, d’Austrian Post ou d’Estafeta, entre autres, et des numéros à 20 chiffres sont utilisés par d’autres transporteurs comme NZ Post. Si USPS ne trouve rien, vérifie quel transporteur figure dans l’e-mail de la boutique. Voir [formats des numéros de suivi](guide:tracking-number-formats).

## Statuts USPS : que signifie chacun d’eux ?

USPS rédige son suivi en anglais. Ces libellés, expliqués dans l’aide d’USPS, suivent l’ordre habituel d’un envoi :

| USPS écrit | Ce que cela veut dire | Dans Peek |
| --- | --- | --- |
| `Merchant Order Receipt Notification` | Avis électronique de la boutique confirmant ta commande, avant tout scan d’USPS. | « Étiquette créée » |
| `Shipping Label Created, USPS Awaiting Item` | Étiquette créée par l’expéditeur, colis pas encore remis à USPS. Vois avec la boutique. | « Étiquette créée » |
| `Picked Up by Shipping Partner, USPS Awaiting Item` | Un partenaire détient le colis et va le remettre à USPS ; la date prévue apparaît après réception par USPS. | « Étiquette créée » |
| `USPS in Possession of Item` | USPS a reçu ou collecté le colis. Rien à faire. | « Pris en charge » |
| `Arrived at USPS Facility` | En tri ou en route vers le bureau distributeur ; peut se répéter au même endroit ou sur plusieurs jours. | « En transit » |
| `Arrived at Post Office` | Au bureau distributeur, préparé pour la tournée d’aujourd’hui ou du prochain jour ouvré postal. | « En transit » |
| `Out for Delivery` | En tournée aujourd’hui, parfois avec un autre facteur et plus tard que le courrier habituel. | « En livraison » |
| `Delivered` | Livré, avec date, heure et lieu enregistrés. | « Livré » |
| `Delivery Status Not Updated`, `Awaiting Delivery` | Pas de scan de livraison à 21 h 00 locales le jour d’`Out for Delivery` ; USPS vise le prochain jour ouvré. | `Awaiting Delivery` : « En transit » ; `Delivery Status Not Updated` : garde l’étape précédente, souvent « En livraison » |
| `No Access to Delivery Location` | Accès impossible à la boîte ou à la porte ; USPS retente le prochain jour ouvré. | « Tentative de livraison » |
| `Notice Left (No Authorized Recipient Available)` | Signature requise en ton absence : avis PS Form 3849 laissé, sans second passage automatique. | « Tentative de livraison » |
| `Available for Pickup` | À retirer au bureau de poste dès le prochain jour ouvré. | « Prêt à être retiré » |
| `Alert` | Retard lié à la météo ou à un autre incident. | « Problème signalé » |

Avec `Available for Pickup`, aucune nouvelle livraison ne se réserve selon USPS : retrait au guichet avec pièce d’identité avec photo. `Notice Left (No Secure Location Available)` indique l’absence d’endroit sûr et le retour au bureau, sans second passage automatique. `Insufficient Address` et `No Such Number` signifient un retour à l’expéditeur, qui doit réexpédier à ses frais (« Tentative de livraison » dans Peek). À l’international, `Attempted Delivery Abroad` signale un échec de remise par la poste locale (« Tentative de livraison »), `Held at Delivery Depot/Delivery Office` un dépôt au bureau local affiché quelques minutes après l’arrivée (« Prêt à être retiré »), et `Payment of charges - Item being held, addressee being notified` une retenue jusqu’au règlement des frais d’importation par le destinataire averti (« À la douane »).

Chaque scan est horodaté à l’heure locale du lieu où il a eu lieu.

## Colis venu des États-Unis : livraison en France, en Belgique, en Suisse et au Canada

**Qui livre.** Pour un code postal international en `US` à clé valide, Peek interroge aussi la poste de ton pays sous le même numéro : [La Poste](carrier:la-poste) en France, [bpost](carrier:bpost) en Belgique, [La Poste suisse](carrier:swiss-post) en Suisse, [POST Luxembourg](carrier:post-luxembourg) au Luxembourg et [Postes Canada](carrier:canada-post) au Canada (selon la région de l’iPhone ou le pays de connexion navigateur). Dès que cette poste publie un scan au moins aussi récent que celui d’USPS, Peek y poursuit le suivi en gardant l’historique d’USPS. Pour les numéros uniquement chiffrés ou à Monaco, aucune autre poste n’est interrogée.

**Douane.** Tout envoi venu des États-Unis passe en douane : voir [colis bloqué en douane](guide:customs). En France, en Belgique et au Luxembourg, comme dans toute l’UE, les achats en ligne expédiés depuis l’extérieur de l’UE jusqu’à 150 € supportent depuis le 1er juillet 2026 un droit fixe de 3 € par catégorie d’articles, plus 2 € de frais de traitement de l’UE (*Union handling fee*) par catégorie depuis le 1er novembre 2026, quelle que soit la valeur ; en Belgique, bpost perçoit aussi la TVA non prépayée et des frais administratifs. En Suisse (selon l’OFDF et La Poste suisse), la TVA porte sur la valeur marchandise et port compris et les droits sur le poids brut, avec franchise jusqu’à 100 CHF pour les cadeaux entre particuliers hors alcool et tabac ; le Canada applique ses propres règles.

**Absent aux États-Unis.** Après un [passage manqué](guide:missed-delivery) aux États-Unis, le facteur laisse l’avis couleur pêche PS Form 3849. La nouvelle présentation (*Redelivery*) est gratuite mais non offerte partout : une seule demande, à l’adresse d’origine et sans choix d’heure (en ligne avant 2 h 00 CST du lundi au samedi pour le jour même, par QR code, téléphone ou avis signé en boîte). Sinon, retrait au bureau avec pièce d’identité avec photo, ou par un tiers muni de sa pièce et de l’avis signé à son nom. Un second avis part après 5 jours et le retour après 15 jours sur la plupart des colis suivis (5 jours après le premier essai en Priority Mail Express, 30 jours pour la plupart des colis étrangers, 5 jours en USPS Smart Locker puis 10 au guichet).

## Colis USPS en retard, perdu ou retourné

**Des États-Unis vers chez toi.** Seul l’expéditeur américain peut ouvrir en ligne une enquête internationale auprès d’USPS ; pour un achat, vois avec la boutique. Délais de déclaration de perte : de 3 à 90 jours après l’envoi en Priority Mail Express International, de 7 jours à 6 mois en Priority Mail International (10 jours à 6 mois vers le Canada) ou Registered Mail (pas d’enquête en ligne en First-Class Package International Service non recommandé). Voir [colis perdu](guide:lost-parcel) et [suivi qui ne bouge plus](guide:tracking-not-updating).

**De chez toi vers les États-Unis.** L’indemnisation revient à l’expéditeur étranger sauf cession écrite au destinataire américain : ouvre la recherche auprès de ta poste de dépôt, et fais présenter immédiatement tout colis endommagé dans un bureau USPS.

**Aux États-Unis.** Délais indicatifs : 2 à 3 jours ouvrés en Priority Mail, 2 à 5 en Ground Advantage, 2 à 8 en Parcel Select ; seul Priority Mail Express est garanti. Livraison du lundi au samedi, en principe avant 17 h 00 locales. Si `Out for Delivery`, `Arrived at Post Office` ou `Delivery Status Not Updated` n’avance pas d’un jour ouvré complet — ou si `Delivered` date de plus d’un jour ouvré sans colis devant la porte, en boîte, au garage, chez les proches ou à la gérance ([livré mais non reçu](guide:delivered-not-received)) — envoie une demande (*service request*) à USPS. Dès 7 jours après l’envoi, expéditeur ou destinataire peuvent lancer une recherche Missing Mail sur usps.com.

**Indemnisation intérieure.** Réservée aux envois assurés avec reçu d’expédition original : avarie sous 60 jours, perte entre 15 et 60 jours (7 à 60 en Priority Mail Express). Garde carton et contenu. Voir aussi [colis retourné à l’expéditeur](guide:returned-to-sender).

## Service client USPS : téléphone, contact et SMS frauduleux

Au 17 novembre 2026, « Contact Us » d’USPS indique :

- **1-800-ASK-USPS** (`1-800-275-8777`), lundi–vendredi 8 h 00–20 h 30 et samedi 8 h 00–18 h 00 heure de l’Est (ET, soit d’ordinaire 14 h 00 à Paris, Bruxelles et Genève pour 8 h 00 ET) ; suivi vocal automatisé 24 h/24.
- **1-800-222-1811** pour le suivi national et international, mêmes horaires (les agents voient les mêmes données qu’USPS Tracking).
- Le formulaire « Email Customer Service » et un chat pour les soucis techniques d’usps.com.
- Aux États-Unis, le relais `7-1-1` pour les personnes sourdes, malentendantes ou ayant des troubles de la parole.

USPS ne dit pas si les `1-800` fonctionnent hors d’Amérique du Nord ; depuis l’Europe, passe d’abord par l’expéditeur. Selon l’USPIS et USPS, aux États-Unis, USPS n’envoie de SMS ou d’e-mail de suivi que si tu l’as demandé avec un numéro de suivi, ces messages ne contiennent aucun lien et la seconde présentation est gratuite. Ces règles ne valent que pour USPS : en Europe, c’est la poste ou le transporteur qui livre qui réclame les frais d’importation, par ses propres canaux. En Belgique, bpost envoie une invitation à payer par e-mail, SMS, notification push ou lettre, à régler dans l’app My bpost ou Track & Trace ; en Suisse, les frais d’importation se paient en ligne dans la Post-App. Aux États-Unis, transfère les SMS suspects au `7726` ou à `spam@uspis.gov` ; voir [faux SMS de livraison](guide:delivery-scam-texts).

Pages d’USPS citées ici, lues le 17 novembre 2026 : sur about.usps.com « Who we are » ; sur faq.usps.com « Where is my package? Tracking Status Help », « USPS Tracking - The Basics », « How to find your tracking number », « PS Form 3849 Redelivery Notice », « Redelivery - The Basics », « What are the Second and Final Notice and Return Dates for Redelivery? », « Picking Up Mail that is Being Held at Your Post Office », « USPS Smart Locker », « Delayed mail and packages? » et « Scams & Scheme Alerts » ; sur www.usps.com « Text Tracking FAQs », « Receive Mail & Packages », « Informed Delivery », « Missing Mail and Lost Packages », « File a Claim », « File a USPS Claim: International » et « Contact Us ».

## Suivre un colis USPS avec Peek

[Peek](/) reconnaît aussitôt USPS sur la plupart des étiquettes à 22 chiffres à clé valide, la plupart des numéros à 26 chiffres en `92`, les codes `420` scannés (dont il retire le ZIP) et les codes `US` commençant par `C` (hors `CE` et `CV`), `E`, `L` ou `R` à clé valide. Sur les formes partagées (numéros en `9261` ou `9361` et numéros à 20 chiffres), Peek affiche « Vérification des services de suivi… », interroge d’abord les autres candidats (DHL eCommerce, Austrian Post ou NZ Post) et les services de suivi, puis ouvre la page d’USPS en dernier, ce qui peut prendre un instant. Sinon, touche « Changer » et choisis USPS sous « Correspond à ce numéro », ou plus tard « Changer de transporteur ». Un lien de suivi usps.com contenant le numéro fonctionne aussi.

Peek ouvre le suivi d’USPS dans un navigateur serveur ; si USPS détourne ou refuse la requête, les services de suivi complètent, sans toujours avoir tout l’historique. Il affiche le statut, les étapes avec lieu et heure lue dans l’État américain concerné puis convertie sur ton appareil (« Heures dans ton fuseau horaire : … »), ainsi que le jour prévu et l’éventuel créneau de 2 heures, sans le signataire ni les consignes. Les lignes restent en anglais sauf `Delivered`, affiché « Livré ».

Peek vérifie USPS jusqu’à toutes les 30 minutes tant que le colis est ouvert à l’écran ou le jour de livraison avec notifications activées (même en tournée), attend une heure après un échec et contrôle toutes les heures sinon — y compris après deux jours sans scan ou 12 heures sur « Étiquette créée » ou « Pris en charge » (toutes les 3 heures après un jour calme via les seuls services de suivi). Alertes au choix : « Chaque scan », « Étapes importantes » ou « Livraison seulement ».

Quand [UPS](carrier:ups) ou [FedEx](carrier:fedex) remettent un colis à USPS, Peek suit chez USPS dès qu’un progrès y apparaît. Vers les États-Unis, pour un numéro international en `FR`, `BE`, `CH`, `LU` ou `CA`, Peek interroge aussi USPS quand la poste d’origine indique les États-Unis comme destination, ou si ton pays dans Peek est réglé sur les États-Unis. Reprogrammation, Informed Delivery et réclamations restent sur usps.com.

## Questions fréquentes sur le suivi USPS

### Que signifie « Tracking Not Available » chez USPS ?

USPS ne trouve aucun dossier pour ce numéro : il est inconnu ou expiré. Vérifie chaque caractère ; USPS conserve l’historique 120 jours pour USPS Tracking et jusqu’à 2 ans pour les envois internationaux numérotés.

### Peut-on suivre tous les colis envoyés depuis les États-Unis ?

Non. Selon USPS, le suivi est inclus sur de nombreux produits internationaux, mais pas sur tous, et sa disponibilité dépend aussi du pays de destination. Demande au vendeur si ton envoi comporte un numéro de suivi.

### USPS livre-t-il le dimanche ?

Uniquement les envois Priority Mail Express et les colis Amazon, indique USPS. Tout le reste est distribué du lundi au samedi.

### USPS affiche-t-il une heure de passage précise ?

Non. USPS peut indiquer un créneau estimé de 2 heures (*Expected Delivery Window*), non garanti, et précise ne pas savoir à quelle heure exacte le facteur passera à ton adresse. Peek affiche le jour prévu ainsi que ce créneau lorsqu’USPS le fournit.

:::sources
- [U.S. Postal Inspection Service : Smishing – Package Tracking Text Scams (en anglais)](https://www.uspis.gov/news/scam-article/smishing/) – SMS envoyés uniquement sur demande avec numéro de suivi, jamais de lien, signalement au 7726 et à spam@uspis.gov
- [Commission européenne : EU customs reform (en anglais)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – droit de douane de 3 € jusqu’à 150 € depuis le 1er juillet 2026 et frais de traitement de 2 € depuis le 1er novembre 2026
- [Commission européenne : Customs Guidance on EUR 3 customs duty (en anglais)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – calcul par catégorie d’articles et frais indépendants de la valeur
- [bpost : Shopping hors UE](https://www.bpost.be/fr/shopping-hors-ue) – TVA et frais de dédouanement en Belgique
- [OFDF : Frais de dédouanement et suivi des colis](https://www.bazg.admin.ch/fr/frais-dedouanement-et-suivi-des-colis) – dédouanement assuré par le transporteur en Suisse
- [La Poste suisse : Dédouanement à l’importation](https://www.post.ch/fr/reception/dedouanement-a-l-importation) – TVA, droits au poids brut et franchise de 100 CHF pour les cadeaux entre particuliers
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formes de numéros, libellés et étapes, fuseaux horaires américains et relais avec les postes nationales
:::
