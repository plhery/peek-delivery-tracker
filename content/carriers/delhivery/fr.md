---
title: Suivi Delhivery : numéros AWB, statuts et assistance
description: Suis un colis Delhivery en Inde ou vers l'étranger : format du numéro AWB, sens des statuts comme RTO, livraison manquée, douane, réclamations et support.
slug: suivi-delhivery
published: 2026-11-21
updated: 2026-11-21
---

Fondée en 2011, Delhivery (*Delhivery Limited*) se présente comme le premier prestataire logistique entièrement intégré d'Inde : elle livre les commandes des places de marché et boutiques en ligne dans plus de `18 850` codes PIN et expédie depuis l'Inde vers plus de `220` pays et territoires. Tu peux suivre un envoi sur la [page de suivi Delhivery](https://www.delhivery.com/tracking) avec son numéro AWB, un numéro de commande (`Order Id`), un `LRN` ou ton numéro de mobile, ou coller directement l'AWB dans le champ ci-dessus.

## Numéro de suivi Delhivery (AWB) : à quoi il ressemble

Delhivery appelle son numéro de suivi **AWB** (*airway bill number*, numéro de lettre de transport). Voici les formats d'AWB Delhivery reconnus par Peek, avec des chiffres inventés :

| Numéro (inventé) | Ce que c'est |
| --- | --- |
| `1482910573642` | **13 chiffres** : un AWB Delhivery. Plusieurs autres transporteurs utilisent aussi 13 chiffres, dont [DPD France](carrier:dpd-fr), [Econt](carrier:econt), FAN Courier ou Korea Post. |
| `14829105736492` | **14 chiffres** : également un AWB Delhivery, format partagé avec [DPD](carrier:dpd-de), [SEUR](carrier:seur), [BRT](carrier:brt) ou Xpressbees. |

La longueur seule ne suffit donc pas à désigner Delhivery : vérifie quel transporteur nomme le SMS ou l'e-mail d'expédition de la boutique. Sur `delhivery.com/tracking`, tu peux aussi chercher par `Order Id`, par `LRN` (fret B2B) ou te connecter avec ton mobile et un code à usage unique (`OTP`) pour voir tous tes colis en cours ; dans Peek, seul le numéro **AWB** à 13 ou 14 chiffres fonctionne ([comment trouver ton numéro de suivi](guide:find-tracking-number)).

## Signification des statuts de suivi Delhivery

Le suivi Delhivery associe une barre de progression et la liste des scans accompagnés de leur ville. Voici les statuts et codes que Peek lit chez Delhivery :

| Statut Delhivery | Ce que cela signifie | Peek affiche |
| --- | --- | --- |
| `WAITING_PICKUP` | Envoi réservé mais pas encore collecté (affiché `Waiting pickup` tant qu'aucun scan n'a eu lieu). | « Étiquette créée » |
| `Picked Up` | Delhivery a collecté l'envoi chez l'expéditeur (jalon également utilisé en fret sans détail de scan). | « Pris en charge » |
| `PROD_REPLACED` | Lors d'un échange avec reprise à domicile, le livreur a récupéré l'article remplacé. | « Pris en charge » |
| `In Transit`, `IN_TRANSIT`, `On the Way` | Colis en cours d'acheminement dans le réseau Delhivery (`IN_TRANSIT` est le code brut, `On the Way` le jalon fret). | « En transit » |
| `Dispatched`, `Out for Delivery` | Départ en tournée finale vers ton adresse (`Dispatched` sur le scan colis, `Out for Delivery` sur la barre de progression). | « En livraison » |
| `Delivered` | Colis livré (ou rendu au vendeur lorsqu'il s'agit d'un flux retour). | « Livré » (ou « Retourné à l’expéditeur ») |
| `Undelivered` | Non livré et reparti vers le vendeur ; en fret, Delhivery renomme ainsi l'étape `On the Way` en gardant sa date initiale, qui n'est donc pas celle d'une tentative. | « Problème signalé » |
| `REACHED_SEL_CITY` | Un colis en cours de retour a atteint la ville du vendeur. | « En transit » |
| `RTO`, `DELIVERED_SELLER` | *Return to Origin* : le colis non livré est revenu jusqu'au vendeur. | « Retourné à l’expéditeur » |
| `DTO` | Un retour client expédié par tes soins a été remis au vendeur. | « Retourné à l’expéditeur » |
| `LOST` | Delhivery a déclaré l'envoi perdu. | « Problème signalé » |
| `CANCELLED`, `Canceled` | L'expédition ou l'enlèvement retour a été annulé. | « Problème signalé » |

Pour mieux comprendre la différence entre création d'étiquette, transit et retour, consulte [que signifient les statuts de suivi](guide:tracking-statuses).

## Livraison Delhivery en Inde : appels, absences et adresse

**Réseau.** Delhivery exploite `50` centres de tri automatisés, `123` gateways et plus de `4 500` centres de livraison directe dans tous les États indiens, couvrant `99,5 %` des codes PIN (y compris le Nord-Est et le Jammu-et-Cachemire), 24 h/24 et 7 j/7.

**Appels et WhatsApp.** Ses conditions générales prévoient que Delhivery ou ses livreurs partenaires peuvent t'appeler, t'envoyer un SMS ou te joindre sur WhatsApp (appels susceptibles d'être enregistrés) pour coordonner la remise ou confirmer ta disponibilité. Pour les marchands e-commerce, Delhivery propose aussi une gestion des échecs de livraison (`Smart NDR`) par WhatsApp et appels automatisés — sans **jamais** demander de code `OTP` ni d'identifiants pour confirmer ton adresse.

**Livraison manquée et conditions Direct.** Sur son service particulier *Delhivery Direct* (colis jusqu'à `50 kg` en poids facturé, `10 kg` et `50 cm` max en mode express, hors boîtes postales), la demande de livraison est annulée après **3 tentatives infructueuses** (sans remboursement des frais d'envoi ; retour possible aux frais de l'expéditeur). Une remise à un voisin, en boîte aux lettres ou en point approuvé par Delhivery est possible avec accord préalable, mais Delhivery peut refuser tout changement de code PIN ([livraison manquée](guide:missed-delivery)). Pour une commande passée chez un marchand, c'est le contrat de la boutique qui s'applique.

## Colis Delhivery expédiés à l'international et douane

**Délais et partenaires.** Pour les exportateurs indiens, **DLV Saver** couvre le Royaume-Uni (`5–7` jours), les États-Unis (`8–12` jours), le Canada et l'Australie, tandis que **DLV Premium** dessert plus de `220` pays et territoires en `3–6` jours (*Express*) ou `6–10` jours (*Deferred Express*), dès `50 g`. À l'international, Delhivery cite [FedEx](carrier:fedex) et Aramex parmi ses partenaires stratégiques : si la boutique te communique un second numéro pour le dernier kilomètre, ajoute-le aussi dans Peek.

**Douane en France, Belgique, Luxembourg et Suisse.** Dans l'**Union européenne**, les achats en ligne expédiés depuis l'Inde (hors UE) d'une valeur jusqu'à `150 €` supportent depuis le 1er juillet 2026 un droit de douane temporaire de `3 €` par article (marchandises de même classement tarifaire, description et, si requis, origine ; tarif normal au-delà de `150 €`) et, depuis le 1er novembre 2026, des frais de traitement de l'Union de `2 €` par article quelle que soit la valeur, plus la TVA à l'importation en **France métropolitaine**, en **Belgique** et au **Luxembourg** sauf si la TVA a déjà été encaissée lors de l'achat via le guichet IOSS. En **Guadeloupe**, en **Martinique** et à **La Réunion**, la TVA est due sur la valeur totale de l'envoi si celle-ci dépasse 22 euros, et l'octroi de mer et l'octroi de mer régional sont dus dans les mêmes conditions que la TVA ; en **Guyane** et à **Mayotte**, la TVA n'est provisoirement pas applicable, mais l'octroi de mer s'applique dans les mêmes conditions. Ces départements et Saint-Martin sont dans le territoire douanier de l'UE mais hors de son territoire TVA ; les autres territoires français d'outre-mer sont hors des deux. En **Suisse** (hors UE), l'OFDF perçoit `8,1 %` de TVA à l'importation (`2,6 %` au taux réduit) et ne perçoit ni droits ni TVA lorsque leurs montants respectifs sont inférieurs à `5 francs` ([colis retenu en douane](guide:customs)).

## Suivi Delhivery bloqué, colis perdu, endommagé ou retourné

Pour une commande en ligne, ton interlocuteur principal reste le vendeur qui a mandaté Delhivery.

- **Aucun mouvement :** sur « Étiquette créée » (`WAITING_PICKUP`), le marchand n'a pas encore remis le colis. En cours de route, connecte-toi sur `delhivery.com/support` ou dans l'application avec ton mobile pour ouvrir une demande ([suivi qui ne se met plus à jour](guide:tracking-not-updating)).
- **Perdu, endommagé ou livré au mauvais endroit :** sur *Delhivery Direct*, toute réclamation pour perte, avarie ou erreur de livraison doit être ouverte par ticket dans l'application sous **3 jours** (le jour même pour une boîte Delhivery livrée abîmée), avec **3 photos** de l'emballage extérieur et du contenu pour un dommage (vidéo de déballage conseillée). Les dossiers sont traités sous **30 à 45 jours ouvrés** ; sans l'assurance payante *Delhivery Protect* (obligatoire entre `10 000 ₹` et le plafond Direct de `49 999 ₹` ; jusqu'à `30 000 ₹` par AWB sur l'offre e-commerce), l'indemnisation Direct est plafonnée à **`2 000 ₹`** maximum selon la catégorie (`500 ₹` livres/documents/autres, `700 ₹` vêtements, `800 ₹` maison, `1 000 ₹` électronique ou multi-articles, `2 000 ₹` sport, `400 ₹` consommables), sans indemnité de retard ([colis perdu](guide:lost-parcel), [colis endommagé](guide:damaged-parcel), [indiqué livré mais non reçu](guide:delivered-not-received)).
- **Retourné (`RTO`, `DELIVERED_SELLER`) :** le colis est revenu chez le vendeur ; demande un remboursement ou un nouvel envoi ([colis retourné à l'expéditeur](guide:returned-to-sender)).

## Service client Delhivery et alertes anti-fraude

- **Support officiel uniquement sur le web ou l'application :** Delhivery indique que son assistance client n'est joignable **que** depuis sa [page Support](https://www.delhivery.com/support) (`Raise a Query` après connexion par numéro de mobile et `OTP` ; rubriques `Report Issues`, `Grievance Officer`, `Report on-field staff issues`, `Returns`) ou l'application mobile *Delhivery Direct*.
- **Aucun numéro d'appel public :** Delhivery avertit que les numéros de service client trouvés ailleurs sur Internet peuvent être frauduleux. Ses e-mails officiels proviennent uniquement de `@delhivery.com` et ses seuls comptes sociaux d'assistance sont ses pages officielles Facebook, X et Instagram.
- **Attention aux faux SMS :** Delhivery alerte contre les SMS d'échec de livraison renvoyant vers des domaines imités (comme `delhiveryu.top` ou `delhiveryma.top`) et ne demande jamais de `PIN` ni d'`OTP` pour un paiement, ni d'`OTP` ou d'identifiants pour confirmer ton adresse. En Inde, en cas de perte financière, appelle le `1930` ou signale sur `cybercrime.gov.in` (et les appels/SMS suspects sous 3 jours sur `sancharsaathi.gov.in`) ; voir aussi [reconnaître les SMS de livraison frauduleux](guide:delivery-scam-texts).

## Suivre un colis Delhivery avec Peek

Colle l'AWB à 13 ou 14 chiffres dans [Peek](/) : comme ce format est partagé, Peek affiche « Vérification des services de suivi… » et interroge automatiquement Delhivery lorsque tu ajoutes le colis depuis l'Inde pour les numéros commençant par `1`–`9` et finissant par `0`–`6` (« Delhivery a ce colis. » dès qu'une trace existe). Hors d'Inde, ou pour un AWB commençant par `0` ou se terminant par `7`, `8` ou `9`, appuie sur « Changer » et choisis Delhivery. Pour toute autre longueur, une sélection manuelle indique qu'il « n’émet pas de numéros comme celui-ci ».

Enregistré sous Delhivery, un envoi sans scan affiche « En attente de Delhivery ». Peek lit le flux public de Delhivery sans compte : il affiche les scans réalisés avec leur ville (en ignorant les étapes futures de la barre de progression et les coordonnées du destinataire), reconstitue l'année des jalons fret à partir du jour de la semaine, lit les heures sur le fuseau indien (`Asia/Kolkata`, `UTC+5:30`) et les convertit dans celui de ton appareil (« Heures dans ton fuseau horaire : … »), sans date de livraison estimée. Un colis livré en retour chez le marchand s'affiche « Retourné à l’expéditeur » et non « Livré ».

Tant que tu gardes le colis ouvert ou le jour de livraison avec notifications actives, Peek vérifie jusqu'à toutes les 10 minutes, et jusqu'à toutes les 2 minutes dès `06:00` heure indienne le jour où Delhivery scanne le colis « En livraison » tant que le traceur de Delhivery répond ; sinon, et après 12 h sur « Étiquette créée » / « Pris en charge » ou deux jours sans nouveauté, toutes les heures (« Chaque scan », « Étapes importantes » ou « Livraison seulement »). Peek ne peut contacter ni Delhivery ni la boutique pour toi.

## Questions fréquentes sur le suivi Delhivery

### Comment suivre un colis Delhivery ?

Colle le numéro AWB à 13 ou 14 chiffres dans Peek en haut de cette page (et sélectionne Delhivery via « Changer » si tu es hors d'Inde), ou cherche par AWB, `Order Id`, `LRN` ou numéro de mobile sur `delhivery.com/tracking`.

### Qu'est-ce qu'un numéro AWB chez Delhivery ?

C'est le numéro de lettre de transport (*airway bill*) à 13 ou 14 chiffres attribué par Delhivery, communiqué dans le SMS ou l'e-mail d'expédition du vendeur.

### Que signifie `RTO` dans le suivi Delhivery ?

*Return to Origin* : après un échec de livraison ou une annulation, le colis est reparti et a été rendu au vendeur. Contacte la boutique pour obtenir un remboursement ou un renvoi.

### Quel est le numéro de téléphone du service client Delhivery ?

Delhivery ne publie pas de standard téléphonique d'assistance et précise que son support est joignable uniquement en se connectant avec son numéro de téléphone sur `delhivery.com/support` ou dans l'application Delhivery, les numéros trouvés ailleurs en ligne pouvant être frauduleux.

:::sources
- [Delhivery : Track your order](https://www.delhivery.com/tracking) – suivi par mobile, AWB, Order Id ou LRN et alertes anti-hameçonnage
- [Delhivery : Support](https://www.delhivery.com/support) – connexion par OTP, ouverture de ticket et rubriques dont Grievance Officer
- [Delhivery : About Us](https://www.delhivery.com/about-us) – création en 2011, plus de 18 850 codes PIN, 50 centres de tri et plus de 4 500 centres de livraison 24/7
- [Delhivery : Accueil](https://www.delhivery.com/) – plus de 220 pays et territoires, partenaires stratégiques FedEx et Aramex
- [Delhivery : Express Parcel](https://www.delhivery.com/services/express-parcel) – couverture de 99,5 % des codes PIN, Smart NDR par WhatsApp/appels IA et Delhivery Protect e-commerce
- [Delhivery : International](https://www.delhivery.com/services/international) – délais DLV Saver et DLV Premium dès 50 g et visibilité sur les droits et taxes
- [Delhivery : Personal Courier](https://www.delhivery.com/solutions/personal-courier) – envois particuliers Delhivery Direct
- [Delhivery : Terms and Conditions](https://www.delhivery.com/terms-and-conditions) – appels, SMS et messages WhatsApp enregistrés pour la livraison
- [Delhivery : TnC (Direct National Courier)](https://www.delhivery.com/tnc-direct) – limites de poids, 3 tentatives, réclamations sous 3 jours avec 3 photos, règlement en 30–45 jours ouvrés et plafonds jusqu'à 2 000 ₹ / Delhivery Protect
- [Delhivery : Fraud Disclaimer](https://www.delhivery.com/fraud-disclaimer) – canaux officiels @delhivery.com, faux SMS, absence de demande d'OTP de paiement, 1930 et cybercrime.gov.in
- [Commission européenne : EU Customs Reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – droit de douane de 3 € jusqu'à 150 € depuis juillet 2026 et frais de traitement de 2 € depuis novembre 2026
- [Commission européenne : Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – définition d'un article, frais de 2 € quelle que soit la valeur et TVA via IOSS
- [Commission européenne : Champ d'application territorial de la TVA et des douanes](https://taxation-customs.ec.europa.eu/taxation/vat/vat-directive/how-does-vat-work/territorial-scope_en) – statut douanier et TVA des départements et collectivités d'outre-mer français
- [Douane française : Vous achetez sur internet](https://www.douane.gouv.fr/demarche/vous-achetez-sur-internet) – TVA et octroi de mer outre-mer
- [OFDF : Réception de lettres et de paquets](https://www.bazg.admin.ch/fr/reception-de-lettres-et-paquets) – TVA suisse de 8,1 % / 2,6 % et franchise en dessous de 5 francs
- [Universal Parcel Scraper : Delhivery](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/delhivery) – formats AWB à 13 et 14 chiffres, 17 statuts et codes, jalons fret, flux retour et fuseau Asia/Kolkata
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – autres transporteurs à 13 et 14 chiffres
:::
