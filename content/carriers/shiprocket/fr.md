---
title: Suivi Shiprocket : numéros AWB, statuts et RTO
description: Suis un colis Shiprocket ou ShiprocketX : format du numéro AWB, sens des statuts, échecs de livraison (NDR), RTO et contacts.
slug: suivi-shiprocket
published: 2026-11-22
updated: 2026-11-22
---

Shiprocket, basée à Gurugram, est une plateforme d’expédition indienne et non un transporteur : les vendeurs en ligne réservent leurs colis via Shiprocket auprès de transporteurs comme Delhivery, Blue Dart ou Xpressbees, et Shiprocket fournit une page de suivi à l’acheteur. ShiprocketX est son service pour les colis expédiés d’Inde vers l’étranger. Suis un colis sur la [page de suivi Shiprocket](https://www.shiprocket.in/shipment-tracking/) avec son AWB, numéro de commande ou numéro de mobile, sur le lien `shiprocket.co` envoyé par le vendeur, ou dans le champ ci-dessus. Shiprocket indique t’envoyer l’AWB par e-mail et SMS dès la confirmation de commande.

## Numéro de suivi Shiprocket : à quoi il ressemble

Shiprocket appelle le numéro de suivi l’AWB (*air waybill number*). En Inde, il s’agit du propre numéro du transporteur. Un colis ShiprocketX voyage sous le propre AWB de Shiprocket : 13 chiffres commençant par `832`. Shiprocket ne publie aucun format officiel. Avec des chiffres fictifs :

| Numéro (fictif) | À quoi il correspond |
| --- | --- |
| `8324829103756` | `832` suivi de 10 chiffres : un AWB ShiprocketX. |
| `7394028156342` | 13 ou 14 chiffres : l’AWB d’un transporteur national indien, comme Delhivery ou Xpressbees. |
| `74829103753` | 11 chiffres : le format d’un AWB Blue Dart. |
| `FMPC4829103756` | Trois lettres, C, P ou R, puis 10 chiffres : un AWB Ekart. |

**Où le trouver.** Regarde l’e-mail et le SMS envoyés lors de la confirmation de commande. Le suivi Shiprocket accepte aussi le numéro de commande ou ton numéro de mobile, vérifie un code à usage unique (OTP) et ouvre myShiprocket, son portail où les acheteurs suivent leurs commandes. D’autres pistes dans [comment retrouver un numéro de suivi](guide:find-tracking-number).

**Numéros qui se ressemblent.** Les bordereaux Delhivery pouvant aussi compter 13 chiffres, un numéro commençant par `832` appartient probablement, mais pas à coup sûr, à Shiprocket. Le suivi Shiprocket répond pour tout transporteur réservé via la plateforme, donc le lien `shiprocket.co/tracking/` du vendeur fonctionne quelle que soit la forme du numéro.

## Statuts de suivi Shiprocket expliqués

La page de suivi affiche le statut, le transporteur ou service à côté de l’AWB, puis les scans du plus récent au plus ancien, avec activité, souvent un lieu, jour et mois, et heure, sans année. La plupart des lignes reprennent les mots du transporteur : celui en Inde ou le transporteur final à l’étranger. Voici les lignes fréquentes, dans l’ordre :

| La page Shiprocket indique | Ce que cela signifie pour toi |
| --- | --- |
| `Order Received` | Le vendeur a créé la commande. Aucun transporteur n’a encore le colis. |
| `Manifested - Out for Pickup` | Le transporteur est en route pour collecter le colis chez le vendeur. |
| `In Transit - Shipment picked up` | Le transporteur a collecté le colis. |
| `In Transit - Shipment Recieved at Origin Center` | Au premier hub du transporteur (orthographié ainsi sur la page). |
| `Dispatched - Delivering At Shiprocket Warehouse` | ShiprocketX : le transporteur achemine le colis vers le hub d’exportation de Shiprocket, pas chez toi. |
| `Received At DC` | Le hub d’exportation de Shiprocket a reçu le colis. |
| `AWB Handed over to Flight` | Remis à la compagnie aérienne. |
| `CUSTOM CLEARED OVERSEAS` | Dédouané. |
| `Received by carrier` | Le transporteur de ton pays a pris le relais. |
| `RTO DELIVERED` | Retourné au vendeur. |

`Out For Pickup` et `PICKUP EMPLOYEE IS OUT TO P/U SHIPMENT` signifient aussi que le colis n’est pas encore collecté. `Reached At Warehouse` et `Connection Aligned` sont des étapes au hub d’exportation à Delhi. `Customer has made payment or Provided ID/Tax information` est une étape douanière à l’étranger (droits payés ou justificatif fiscal fourni ; voir [colis bloqué en douane](guide:customs)). Toute ligne commençant par `RTO` (*return to origin*) signifie que le colis repart chez le vendeur.

**Colis plus ancien.** Au bout d’un certain temps, la page n’affiche plus qu’une vue archivée : dernier statut (`DELIVERED`, `FULFILLED` pour une commande livrée et clôturée, ou `RTO DELIVERED`), son jour, le service et l’AWB dont les derniers caractères sont masqués. L’historique complet n’est montré qu’à l’acheteur après un code envoyé sur son téléphone. Après environ un an, la page répond `AWB Not Found`.

## Qui livre les colis Shiprocket

**En Inde**, le transporteur choisi par le vendeur. Shiprocket indique travailler avec 42 partenaires de livraison, dont Delhivery, Blue Dart, Xpressbees, Ekart, DTDC, Shadowfax, India Post et Amazon Shipping, et précise que les délais dépendent du transporteur, du code PIN et du service. La page de suivi nomme le service, par exemple `Delhivery Surface`.

**À l’étranger**, un transporteur local achemine le colis ShiprocketX jusqu’au hub d’exportation de Shiprocket à Delhi ; il part ensuite par avion, passe la douane et est confié à un transporteur dans le pays de destination, toujours sous l’AWB Shiprocket. Dans des historiques lus sur Shiprocket, USPS prenait le relais aux États-Unis. La page indique le service, par exemple `SRX Economy` ou `SRX Premium`. Shiprocket annonce aux vendeurs 12 à 15 jours ouvrés en économique vers les États-Unis, le Royaume-Uni, le Canada et ailleurs, et 10 à 12 jours en droits acquittés vers les États-Unis : ce sont des délais commerciaux, pas une date garantie pour ta commande. Ses menus appellent aussi ShiprocketX « ShipX ».

## Échec de livraison (NDR) et retour (RTO)

Un NDR (*non-delivery report*) signifie que le transporteur n’a pas pu livrer. Le vendeur le voit dans Shiprocket quelques minutes plus tard et décide de retenter la livraison ou de reprendre le colis.

- **Messages.** Si le vendeur active le parcours acheteur NDR de Shiprocket, une commande marquée « customer requested future delivery » ou « customer refused the delivery » t’envoie un SMS — et selon l’offre un appel automatisé — pour confirmer la livraison. Un vendeur peut aussi faire envoyer un SMS contenant un lien pour demander un nouveau passage et corriger ton adresse ou téléphone.
- **Page de suivi.** Shiprocket indique que les acheteurs peuvent y demander une nouvelle tentative et voir le motif de chaque essai.
- **Tentative jamais effectuée.** Préviens le vendeur, qui peut signaler une « fake attempt » à Shiprocket avec ta confirmation.
- **Trois tentatives.** Les transporteurs effectuent jusqu’à trois essais. Ensuite, la commande passe en RTO et retourne à l’adresse d’enlèvement ou à l’entrepôt du vendeur.

Une fois le colis reparti, demande au vendeur un nouvel envoi ou un remboursement. Voir [livraison manquée](guide:missed-delivery) et [retourné à l’expéditeur](guide:returned-to-sender).

## Colis Shiprocket bloqué ou qui n’arrive pas

**Aucune mise à jour.** Shiprocket précise que les transporteurs actualisent le suivi après scan, ce qui peut prendre quelques heures ; après plus d’un jour sans évolution, contacte le vendeur. Tant que la page affiche seulement `Order Received` ou une ligne de collecte, le colis n’est pas encore remis au transporteur. Plus de détails dans [pourquoi un suivi ne se met plus à jour](guide:tracking-not-updating).

**Qui agit.** Le client de Shiprocket est le vendeur. À la question « Whom shall I contact if I have concerns? », Shiprocket répond de contacter le vendeur chez qui tu as acheté. Pour un colis perdu, endommagé ou non livré, adresse-toi également au vendeur : la couverture perte/dommage ShiprocketX jusqu’à 5 000 INR concerne les vendeurs. Voir [colis perdu](guide:lost-parcel) et [colis livré mais non reçu](guide:delivered-not-received).

## Service client et téléphone Shiprocket

Coordonnées publiées par Shiprocket :

- **Téléphone :** 9643887070, 9711451060 et 8510042020 (numéros indiens, +91 depuis l’étranger). Le site de suivi les indique pour les problèmes de suivi, avec chat en direct, comme un support 24 h/24 et 7 j/7 ; la page de contact donne ces mêmes numéros de 9 h à 21 h (heure indienne).
- **E-mail :** support@shiprocket.in.
- **Escalade :** le support répond sous 5 jours ouvrés. Sans résolution, écris à escalation@shiprocket.com, puis à grievance@shiprocket.com ; chaque niveau répond sous 3 jours ouvrés.

L’essentiel de ce support vise les vendeurs. Le site de suivi Shiprocket avertit aussi : si un message provenant d’une source non vérifiée te demande un paiement, ne réponds pas, ne partage aucune donnée de paiement, bloque et signale l’expéditeur. Voir [les SMS frauduleux de livraison](guide:delivery-scam-texts).

## Suivre un colis Shiprocket avec Peek

Pour un numéro de 13 chiffres commençant par `832`, [Peek](/) interroge Shiprocket dès le départ (avec Delhivery si ton pays est l’Inde) et, seulement si Shiprocket ne connaît pas le numéro, des services de suivi et d’autres transporteurs : l’écran d’ajout affiche « Vérification des services de suivi… » puis « Shiprocket a ce colis. » Comme les bordereaux Delhivery partagent cette longueur, Peek ne classe pas le colis chez Shiprocket à la seule forme ; sans réponse, il affiche « En attente du premier scan de Shiprocket » et continue de l’interroger, et tu peux choisir Shiprocket avec « Changer » (ou « Changer de transporteur »). Un lien `shiprocket.co/tracking/` sélectionne Shiprocket quelle que soit la forme du numéro.

Peek lit la page de suivi Shiprocket et affiche le statut, chaque scan avec son lieu lorsqu’il figure, l’estimation de livraison de Shiprocket en cours de route et l’heure de livraison d’un colis livré en Inde. Les scans à l’étranger ne portant pas de fuseau horaire, Peek lit chacun sur l’horloge de son lieu (une ligne sans lieu sur celle d’une ligne voisine, parfois l’heure indienne) puis les affiche dans ton fuseau horaire (« Heures dans ton fuseau horaire : »). Les lignes conservent le texte de la page, sauf correspondances exactes comme `Delivered`. Peek écarte le nom du vendeur, la référence de commande et l’adresse du destinataire, ne reçoit ni poids ni preuve de livraison et ne bascule pas vers le transporteur étranger.

Pour un colis ancien, Peek affiche le dernier statut archivé et son jour, et cherche un historique plus complet via des services de suivi. Peek vérifie jusqu’à toutes les 10 minutes, et toutes les 2 minutes à partir de 6 h sur l’horloge du dernier scan (heure indienne en Inde) le jour où le colis passe « En livraison », tant que le colis est ouvert dans Peek ou le jour de la livraison avec les notifications activées, lorsque la page Shiprocket répond. Après deux jours sans nouvelle (12 heures tant que le colis reste sur « Étiquette créée » ou « Pris en charge »), il vérifie toutes les heures, et après sept jours sans nouvelle sur « Étiquette créée », toutes les 6 heures. Pour les notifications, tu peux choisir « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Après quatre jours sans scan sur un colis en « Étiquette créée », « Pris en charge » ou « En transit », Peek affiche « Aucune mise à jour depuis … » et rappelle « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger Shiprocket. » Peek suit plus de 4 500 transporteurs, dont [Delhivery](carrier:delhivery), [Blue Dart](carrier:blue-dart), Xpressbees, [Ekart](carrier:ekart), DTDC, Shadowfax et [India Post](carrier:india-post) sous leurs propres numéros, mais ne peut ni t’authentifier comme acheteur, ni demander un nouveau passage, ni contacter Shiprocket ou le vendeur à ta place.

## Questions sur le suivi Shiprocket

### Shiprocket est-elle une société de livraison ?

Non. Shiprocket est une plateforme d’expédition : les vendeurs y réservent des transporteurs comme Delhivery, Blue Dart ou Xpressbees, qui assurent le transport. Les colis ShiprocketX voyagent sous le propre AWB de Shiprocket et sont livrés par un transporteur dans le pays de destination.

### Que signifie RTO dans le suivi Shiprocket ?

RTO signifie *return to origin* (retour à l’expéditeur) : le colis n’a pas pu être livré et repart chez le vendeur après jusqu’à trois tentatives, ou plus tôt à sa demande. `RTO DELIVERED` indique que le vendeur l’a récupéré.

### Quel est le numéro du service client Shiprocket ?

Le site de suivi Shiprocket indique 9643887070, 9711451060 et 8510042020 pour les questions de suivi. Pour tout problème sur ta commande, Shiprocket invite les acheteurs à contacter le vendeur.

### Pourquoi le suivi Shiprocket affiche-t-il AWB Not Found ?

Shiprocket ne reconnaît pas le numéro saisi : vérifie les fautes de frappe ou chiffres manquants et patiente quelques minutes si la commande vient d’être passée, comme le suggère sa page. Les envois de plus d’un an environ n’apparaissent plus non plus.

:::sources
- [Shiprocket : Track your orders easily](https://www.shiprocket.in/shipment-tracking/) – suivi par AWB, numéro de commande ou mobile, OTP et myShiprocket, AWB par e-mail et SMS, contacter le vendeur, mises à jour après scan, ShipX
- [Shiprocket : AWB Not Found](https://shiprocket.co/tracking) – vérifications en cas de numéro introuvable, avertissement paiement, téléphones, chat en direct, support 24/7
- [Shiprocket : Contact us](https://www.shiprocket.in/contact/) – bureau de Gurugram, horaires téléphoniques, e-mail, escalade et délais de réponse
- [Shiprocket : FAQs](https://www.shiprocket.in/faq/) – plateforme pour vendeurs, délais estimés, RTO, mises à jour après scan
- [Shiprocket : Native courier integrations](https://www.shiprocket.in/carrier-integrations/) – 42 transporteurs partenaires et leurs noms
- [Shiprocket : ShiprocketX](https://www.shiprocket.in/x/) – services internationaux, délais, services droits acquittés, couverture jusqu’à 5 000 INR
- [Shiprocket : NDR management](https://www.shiprocket.in/ndr-management/) – NDR et RTO, parcours acheteur NDR, SMS et appels automatisés
- [Shiprocket Support : How to Process an NDR?](https://support.shiprocket.in/support/solutions/articles/43000664210-how-to-process-an-ndr-) – nouvelles tentatives, lien SMS, fausses tentatives, trois essais avant RTO
- [Shiprocket : Product highlights from March 2024](https://www.shiprocket.in/blog/product-highlights-from-march-2024/) – demande de nouvelle tentative et motifs affichés sur la page de suivi
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formats AWB, libellés de statut, hub d’export à Delhi, USPS aux États-Unis, vue archivée, ce que Peek affiche
:::
