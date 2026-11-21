---
title: Suivi J&T Express Australia : numéros JTAU et statuts
description: Suis un colis J&T Express Australia : format JTAU à 12 chiffres, signification des statuts anglais, photo de livraison, contact 13 18 18 et Peek.
slug: suivi-jt-express-australia
published: 2026-11-21
updated: 2026-11-21
---

J&T Express Australia (*J&T Express Australia Pty Ltd*, basée à Villawood en Nouvelle-Galles du Sud) est la filiale australienne du groupe J&T Express, présent en Asie du Sud-Est et en Chine continentale. Elle livre des colis dans toute l'Australie pour les plateformes e-commerce, marques et détaillants, y compris les commandes venues de l'étranger après dédouanement. Ses numéros commencent par `JTAU` : colle le tien ci-dessus ou recherche-le sur le [traceur officiel de J&T Australia](https://www.jtexpress.au/tracking). Tu le trouveras dans l'e-mail d'expédition ou sur la page de commande de la boutique.

## Numéro de suivi J&T Express Australia : à quoi il ressemble

Un bordereau (*waybill*) australien de J&T se compose de `JTAU` suivi de 12 chiffres, soit 16 caractères au total, seule forme connue de Peek (exemples inventés) :

| Numéro (inventé) | Ce que c'est |
| --- | --- |
| `JTAU482910573649` | `JTAU` et 12 chiffres : un colis J&T Express Australia, détecté aussitôt par Peek. |
| `JTAU48291057364` | Onze chiffres seulement : il manque un caractère. Si aucun transporteur ne répond, `peektracker.com` affiche « Ça commence comme un numéro de suivi J&T Express Australia, mais il manque un caractère : il y a peut-être une faute de frappe. Compare-le avec ton message d’expédition. » |
| `JTDE482910573649` | `JTDE` et 12 chiffres : [J&T Express Allemagne](carrier:j-and-t-de). |
| `JTE482910573649` | `JTE` et 12 chiffres : [J&T Express Middle East](carrier:j-and-t-middle-east) (Arabie saoudite, Émirats et Koweït). `JTUK` (`JTUK482910573649`) correspond à J&T Express UK. |
| `JT4829105736491` | `JT` et 13 chiffres : réseaux J&T d'Asie du Sud-Est et de [J&T Express Chine](carrier:j-and-t-cn). |

Espaces, tirets et minuscules n'empêchent pas la lecture dans Peek (`jtau 4829 1057 3649` fonctionne à l'identique). Le catalogue connaît aussi le transporteur sous *J&T Express (AU)*, *J&T Express AU*, *J&T Australia* et *JT Express Australia*.

**Où le trouver.** J&T reçoit ton nom, ton adresse et tes coordonnées de l'expéditeur ou de la marketplace : cherche dans l'e-mail d'expédition ou ta commande ([comment trouver son numéro de suivi](guide:find-tracking-number)). Sur `jtexpress.au/tracking`, tu peux saisir jusqu'à 10 numéros séparés par des espaces ou virgules ; un numéro inconnu renvoie `No tracking records yet`.

## Statuts de suivi J&T Express Australia expliqués

Le traceur de J&T affiche chaque scan sous forme d'heure et d'une phrase, sans indiquer de ville ni de dépôt. Peek demande la version anglaise (certains premiers scans existent aussi en chinois sur le traceur : `包裹已从清关行取件。`, `快件已揽收成功，运输准备中。`, `快件到达转运中心，做发往下一站的运输准备`, `快件从转运中心发往下一站`) :

| Statut J&T | Ce que cela veut dire pour toi | Peek affiche |
| --- | --- | --- |
| `The parcel has been picked up from customs clearance.` | Colis venu de l'étranger : la douane est terminée et J&T a récupéré le colis auprès du courtier en douane (premier scan d'un envoi importé, et non blocage douanier). | « Pris en charge » |
| `The parcel has been successfully picked up and is being prepared for transportation.` | J&T détient le colis et le prépare au transport. | « Pris en charge » |
| `The parcel arrives at the distribution center and is prepared for transport to the next destination.` | Arrivé dans un centre J&T avant l'étape suivante. | « En transit » |
| `The parcel is dispatched from the transit center to the next destination` | Parti d'un centre de transit vers l'étape suivante. | « En transit » |
| `Your parcel is out for delivery.` | En tournée de livraison vers ton adresse. | « En livraison » |
| `The building was closed when we arrived. We'll try again.` | Bâtiment fermé au passage du chauffeur ; J&T repassera. | « Tentative de livraison » |
| `We couldn't deliver - the address details appear to be incorrect. Please update your delivery address.` | Adresse incomplète ou erronée ; J&T demande une correction. | « Tentative de livraison » |
| `Your parcel has been delivered.` | Livré. | « Livré » |

Pour en savoir plus sur l'importation, consulte [colis retenu en douane](guide:customs). Toute autre phrase est classée selon ses mots (par défaut « En transit », sauf si le colis est déjà en livraison ou au-delà).

Sur le traceur J&T, plusieurs phrases se terminent par le numéro du service client ou une formule d'invitation à l'appeler : Peek les retire, tout comme les mentions entre crochets (`[…]`, `【…】`), les numéros de téléphone et le nom du chauffeur ou de l'expéditeur. Lorsqu'une livraison nomme la personne ayant signé ou réceptionné le colis, Peek conserve ce nom pour la personne qui a ajouté le suivi et le masque sur un lien partagé. Comme aucun scan ne mentionne de lieu, ni J&T ni Peek ne peuvent indiquer dans quel État ou dépôt se trouve le colis.

## Livraison J&T Express Australia et photo de livraison

J&T recrute des entreprises partenaires dans toute l'Australie pour assurer la collecte et le dernier kilomètre, sans remise à un autre transporteur doté d'un second numéro : le code `JTAU` va jusqu'à ta porte.

**Colis acheminés depuis l'étranger.** Des transitaires internationaux remettent leurs envois à J&T en Australie : [Yanwen](carrier:yanwen), par exemple, les confie par lots au port d'arrivée et relaie ensuite les phrases de J&T dans son propre historique (Peek y affiche alors la prise en charge J&T comme « En transit », puisque Yanwen suivait déjà l'envoi).

**Bâtiment fermé, adresse erronée ou absence.** Après `The building was closed when we arrived. We'll try again.`, J&T reprogramme un passage ; après `We couldn't deliver - the address details appear to be incorrect. Please update your delivery address.`, vérifie immédiatement numéro d'appartement et code postal auprès du vendeur ([modifier l'adresse de livraison](guide:change-delivery-address), [livraison manquée](guide:missed-delivery)). Le site de J&T ne précise ni le nombre maximal de tentatives ni la durée de garde, et aucun statut connu n'envoie en point relais ou consigne.

**Preuve de livraison.** La notice de confidentialité de J&T décrit l'enregistrement effectué à ta porte dans l'application chauffeur :
- photos du colis à l'endroit où il a été déposé, date et heure, géolocalisation de l'appareil, signature si requise et notes de livraison ;
- consigne donnée aux chauffeurs de photographier le colis et le lieu de dépôt en évitant autant que possible les personnes, plaques d'immatriculation et intérieurs privés ;
- envoi d'un lien par SMS ou notification électronique au destinataire (accessible à toute personne disposant du lien, sans le nom ni le contact du chauffeur), également transmis à l'expéditeur ou à la marketplace ;
- si tu refuses qu'une photo soit prise à la livraison, tu peux transmettre tes instructions via `dataprotection@jtexpress.au`.

## Colis J&T bloqué, introuvable ou livré mais non reçu

La boutique ayant choisi J&T reste ton premier interlocuteur.

**Plus de scans depuis quatre jours.** Un colis « Pris en charge » ou « En transit » sans nouveauté depuis quatre jours affiche dans Peek « Aucune mise à jour depuis quatre jours » ainsi que le lien « Demande de l’aide à J&T Express Australia » ([pourquoi le suivi ne bouge plus](guide:tracking-not-updating)).

**Aucun suivi pour l'instant.** Tant que J&T ne connaît pas le numéro, Peek affiche « En attente de J&T Express Australia ». Si rien n'a répondu au bout de deux jours, Peek affiche « Après deux jours, le transporteur ne connaît toujours pas ce numéro. Vérifie-le, choisis un autre transporteur ou supprime le colis. » et espace alors ses vérifications à une fois par jour, puis une fois par semaine passé sept jours.

**Marqué livré mais absent, perdu ou endommagé.** Ouvre le lien de preuve de livraison reçu par SMS pour vérifier l'emplacement photographié, interroge ton foyer et tes voisins, puis préviens la boutique et J&T : J&T utilise ces relevés pour enquêter sur les colis manquants, endommagés ou mal livrés, mais ne publie pas de procédure d'indemnisation directe pour les destinataires — demande remboursement ou remplacement au vendeur ou à la marketplace ([colis livré mais non reçu](guide:delivered-not-received), [colis perdu](guide:lost-parcel), [colis endommagé](guide:damaged-parcel)).

## Service client et contact J&T Express Australia

La [page de contact J&T Australia](https://www.jtexpress.au/contact) indique :

- **Téléphone :** `13 18 18`, horaires de travail `08:00-20:00` (jours non précisés).
- **E-mail service client :** `service@jtexpress.au` (les adresses `business@jtexpress.au` et `partner@jtexpress.au` concernent les entreprises et futurs partenaires de livraison, et `dataprotection@jtexpress.au` les questions de confidentialité au siège `10/2 Christina Road, Villawood NSW 2163`).

La notice de confidentialité précise que ses prestataires de centre d'appels et d'assistance client sont situés en Malaisie et aux Philippines.

Un SMS de J&T contenant un lien vers la photo de livraison peut être légitime, mais si un message réclame un paiement ou des données bancaires, vérifie que le domaine est bien `www.jtexpress.au` (`jtexpress.au`) et lis [reconnaître les SMS de livraison frauduleux](guide:delivery-scam-texts).

## Suivre un colis J&T Express Australia avec Peek

[Peek](/) détecte J&T Express Australia dès la saisie d'un numéro `JTAU` (« Transporteur détecté ») ou d'un lien `www.jtexpress.au/tracking` avec `?q=` contenant un code `JTAU` (si `?q=` contient un autre numéro, Peek l'analyse comme s'il était collé seul ; en sélection manuelle sur un autre format, Peek indique qu'il « n’émet pas de numéros comme celui-ci »). Pour un numéro `JT` à 13 chiffres, Peek interroge les réseaux J&T et les services de suivi.

Peek interroge le traceur officiel en `UTC` (`GMT+0000`) et affiche chaque scan à son instant exact converti dans le fuseau de ton appareil (« Heures dans ton fuseau horaire : … »), jusqu'aux 100 événements les plus récents, sans carte, date prévue, poids, coordonnées d'agence ni photo de livraison (consultable via le SMS de J&T). Lorsque le traceur J&T est injoignable ou ne connaît pas encore le numéro, Peek interroge des services de suivi en relais.

Peek vérifie jusqu'à toutes les 10 minutes lorsque le colis est ouvert ou le jour de livraison avec notifications actives, et jusqu'à toutes les 2 minutes dès `06:00` heure locale le jour où J&T scanne le colis « En livraison » tant que le traceur J&T répond (via les services de suivi : toutes les heures, toutes les 3 h après un jour calme et jusqu'à toutes les 15 minutes le jour de livraison si le colis est ouvert ou les alertes activées) ; sinon, et après 12 h sur « Pris en charge » ou deux jours sans nouveau scan, toutes les heures (« Chaque scan », « Étapes importantes » ou « Livraison seulement »). Peek ne peut ni contacter J&T ou le vendeur, ni modifier ton adresse.

## Questions fréquentes sur le suivi J&T Express Australia

### J&T Express Australia est-il un transporteur légitime ?

Oui. Le registre australien des entreprises (*Australian Business Register*) recense *J&T EXPRESS AUSTRALIA PTY LTD* comme société privée australienne avec un ABN actif depuis le 4 juin 2026 (`18 698 743 609`, ACN `698 743 609`, localisée dans le code postal `NSW 2163`, siège à `10/2 Christina Road, Villawood NSW 2163`), filiale du groupe J&T Express (`01519.HK`) fondé en Indonésie en 2015.

### Que veut dire `picked up from customs clearance` dans le suivi J&T ?

Que le dédouanement est terminé : J&T a récupéré ton colis importé auprès du courtier en douane pour l'acheminer en Australie. Peek l'affiche comme « Pris en charge », et non comme une retenue en douane.

### Pourquoi mon numéro J&T est-il introuvable ?

Vérifie qu'il commence bien par `JTAU` suivi de 12 chiffres (et non `JTUK` ou `JTDE`). S'il est exact, J&T n'a peut-être pas encore reçu le colis (`No tracking records yet`, « En attente de J&T Express Australia » dans Peek) ; après deux jours sans résultat, vérifie-le auprès de la boutique.

### Comment contacter J&T Express Australia ?

Appelle le `13 18 18` (`08:00-20:00`) ou écris à `service@jtexpress.au`, et préviens également la boutique où tu as passé commande.

### J&T Express Australia prend-il une photo à la livraison ?

Oui : selon sa notice de confidentialité, les chauffeurs photographient le colis à l'endroit où il est laissé et tu reçois un lien vers la preuve de livraison par SMS ou notification électronique.

:::sources
- [J&T Express Australia : Track & Trace](https://www.jtexpress.au/tracking) – traceur officiel et message `No tracking records yet`
- [J&T Express Australia : Contact Us](https://www.jtexpress.au/contact) – ligne 13 18 18 (08:00-20:00), service@jtexpress.au, business@jtexpress.au, partner@jtexpress.au et ABN 18 698 743 609
- [J&T Express Australia : Delivery Partners](https://www.jtexpress.au/career) – recrutement de partenaires de collecte et de livraison du dernier kilomètre en Australie
- [J&T Express Australia : About Us](https://www.jtexpress.au/about) – groupe J&T Express (01519.HK), fondation en Indonésie en 2015, Asie du Sud-Est et Chine continentale
- [J&T Express Australia : Privacy Notice](https://www.jtexpress.au/privacy-policy) – siège à 10/2 Christina Road, Villawood NSW 2163, preuve de livraison par photo et lien SMS, dataprotection@jtexpress.au, enquêtes et support en Malaisie et aux Philippines
- [ABN Lookup : J&T Express Australia Pty Ltd](https://abr.business.gov.au/ABN/View?abn=18698743609) – ABN 18 698 743 609 actif depuis le 4 juin 2026, société privée australienne, NSW 2163
- [Universal Parcel Scraper : J&T Express Australia](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/j-and-t-au) – format JTAU, statuts anglais et chinois, heures UTC, nettoyage des mentions, conservation du signataire et page d'aide
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formats des autres réseaux J&T, remise groupée Yanwen et services de suivi en relais
:::
