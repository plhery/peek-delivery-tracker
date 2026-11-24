---
title: Suivi GLS Poland : statuts, GLS Points, consignes et aide
description: Suis un colis GLS Poland : numéros à 11 ou 12 chiffres et Track-ID, code postal facultatif, statuts, GLS Points, consignes ORLEN et contact.
slug: suivi-gls-poland
published: 2026-11-24
updated: 2026-11-24
---

GLS Poland (*General Logistics Systems Poland Sp. z o.o.*, dont le siège est à Głuchowo près de Poznań et le centre de tri central à Stryków près de Łódź) exploite environ 50 agences et plus de 26 000 points de retrait hors domicile en Pologne — dont plus de 18 000 GLS Points (incluant les 11 000 magasins de proximité Żabka) et environ 8 000 consignes automatiques 24h/24 partagées [ORLEN Paczka](carrier:orlen-paczka). Suis ton envoi sur [la page de suivi de GLS Poland](https://gls-group.com/PL/en/parcel-tracking/) avec le numéro de colis ou le Track-ID, ou colle le numéro dans le champ ci-dessus.

## Numéro de suivi GLS Poland et Track-ID : formats

Tu trouveras ton numéro de colis dans l’e-mail d’expédition de la boutique, dans l’alerte e-mail/SMS de GLS ou sur un avis de passage (*awizo*). Le champ de recherche de GLS accepte également le Track-ID figurant dans les notifications GLS.

| Format (inventé) | Ce que c’est |
| --- | --- |
| `48291736405` | Numéro de colis GLS à 11 chiffres. Également utilisé par [GLS Allemagne](carrier:gls-de), [GLS France](carrier:gls-fr), [GLS Suisse](carrier:gls-ch), [GLS Autriche](carrier:gls-at) et [PPL](carrier:ppl). |
| `482917364052` | Numéro de colis à 12 chiffres terminé par une clé de contrôle. Aussi utilisé par [BRT](carrier:brt), [Colis Privé](carrier:colis-prive) et d’autres. |

Sur [la page de suivi de GLS Poland](https://gls-group.com/PL/en/parcel-tracking/), saisir le code postal du destinataire débloque l’historique complet des scans et les options de livraison. Pour d’autres formats, consulte [les formats de numéros de suivi](guide:tracking-number-formats).

## Signification des statuts de suivi GLS Poland

GLS Poland affiche des libellés en polonais sur son site polonais et des libellés du groupe en anglais via l’API internationale GLS lue par Peek.

| Ce qu’affiche GLS Poland / GLS Group | Ce que ça signifie pour toi | Ce qu’affiche Peek |
| --- | --- | --- |
| `Preadvice`, `Nadawca nadał numer paczce - paczka nie została jeszcze przekazana kurierowi GLS` | L’expéditeur a créé l’étiquette électroniquement ; GLS n’a pas encore reçu le colis physique. | « Étiquette créée » |
| `Paczka została przekazana GLS` | GLS a collecté ou pris en charge le colis dans son réseau. | « Pris en charge » |
| `In transit`, `Final parcel center`, `Paczka zarejestrowana w filii GLS`, `Paczka wyjechała z filii GLS` | En transit dans les centres de tri GLS et agences polonaises vers le dépôt distributeur. | « En transit » |
| `In delivery`, `Paczka w doręczeniu` | Le livreur a chargé ton colis pour une livraison aujourd’hui. | « En livraison » |
| Arrivée en GLS Point ou consigne automatique, que GLS indique comme livrée | En attente de retrait dans un GLS Point ou une consigne 24h/24. | « Prêt à être retiré » |
| `The parcel has been delivered.`, `Paczka została doręczona`, `Handing over the parcel to the recipient at the GLS ParcelShop.` | Livré à toi, à une personne autorisée ou à un voisin, ou retiré en GLS Point. | « Livré » |
| `Paczka niedoreczona - odmowa przyjecia przez Odbiorce` | Le destinataire a refusé le colis. | « Tentative de livraison » |
| `Paczka pozostawiona w filii doreczajacej - Odbiorca odmowil przyjecia` | Refusé et conservé à l’agence distributrice. | « Problème signalé » |

Le jour de la livraison, [Live Tracking de GLS Poland](https://gls-group.com/PL/en/live-tracking/) envoie par e-mail un lien indiquant l’heure prévue à 15 minutes près et affiche le livreur sur une carte dès qu’il reste 20 arrêts ou moins. La barre de progression GLS marque comme livré un colis déposé en GLS Point ou en consigne tant qu’il t’attend ; Peek affiche cette étape en « Prêt à être retiré », puis en « Livré » une fois le retrait effectué.

## Délais GLS Poland, GLS Points, consignes ORLEN et FlexDeliveryService

**Jours et délais de livraison.** GLS Poland livre du lundi au vendredi (livraison le samedi uniquement si commandée expressément par l’expéditeur). Les colis nationaux standard arrivent habituellement le jour ouvrable suivant (sans garantie ; les expéditeurs peuvent choisir Express 10:00 ou 12:00). Depuis la Pologne, GLS annonce 2 à 7 jours ouvrables vers les autres pays de l’UE selon la destination ([GLS Points](https://gls-group.com/PL/en/shipping-receiving-points/gls-points/)).

**Poids, dimensions et CashService (contre-remboursement).** Les colis livrés à domicile en Pologne peuvent peser jusqu’à 31,5 kg (40 kg à l’international ; 31,5 kg dans certains GLS Points ; 20 kg dans les autres GLS Points, consignes et myGLS), dans la limite de 200 × 80 × 60 cm et d’un développé (longueur + pourtour) jusqu’à 300 cm. Le paiement à la livraison (`CashService`, jusqu’à 6 000 PLN par jour) se règle au livreur en espèces, par carte ou via BLIK.

**Absence et seconde tentative.** Selon [la page réception de GLS Poland](https://gls-group.com/PL/en/receiving/), si tu es absent, le livreur peut laisser le colis chez un voisin (avec ton accord) ou rediriger un colis éligible sans contre-remboursement vers un GLS Point voisin. Sinon, le colis retourne à l’agence pour une seconde tentative le jour ouvrable suivant ; s’il n’est toujours pas livré, il reste au dépôt avant de repartir chez l’expéditeur (5 jours ouvrables pour un retrait dépôt FlexDeliveryService, ou retour après 6 jours ouvrables selon le guide de réception).

**Délai de garde en GLS Point.** D’après [les conditions générales de GLS Poland](https://gls-group.com/PL/pl/regulamin/) pour les GLS Points et consignes (en vigueur depuis le 9 novembre 2026) et sa [FAQ](https://gls-group.com/PL/en/faq/), un colis attend en GLS Point pendant **7 jours calendaires** avant de repartir chez l’expéditeur (les pages [réception](https://gls-group.com/PL/en/receiving/) et GLS Points mentionnent encore sept jours ouvrables). Retire-le avec le code PIN reçu par SMS ou e-mail ; GLS peut demander une pièce d’identité.

**Consignes automatiques ORLEN Paczka 24h/24.** Les colis orientés vers les [consignes partagées ORLEN Paczka](https://gls-group.com/PL/en/shipping-receiving-points/parcel-lockers/) restent disponibles **72 heures** avant retour automatique à l’expéditeur. Ouvre le casier avec ton code PIN SMS/e-mail, ton QR code ou l’ouverture à distance dans l’appli myGLS — aucune pièce d’identité n’est demandée, donc toute personne ayant le code peut retirer le colis. Pour l’assistance technique 24h/24 des consignes, appelle le `801 167 536` ou le `502 167 536`.

**Redirection FlexDeliveryService.** Via le lien présent dans l’e-mail de notification GLS (sans créer de compte), [la redirection de colis](https://gls-group.com/PL/en/parcel-redirection/) permet de choisir un GLS Point, une consigne ORLEN, une autre adresse, une autre date, le retrait en dépôt, une autorisation de dépôt sans signature ou un refus de colis.

## Douane, dégâts, réclamations et colis GLS Poland manquants

Pour un achat en ligne, c’est le vendeur qui détient le contrat de transport avec GLS Poland et gère remboursements ou remplacements.

**Colis hors UE.** Les envois circulant à l’intérieur de l’UE ne passent pas en douane. Pour les commandes en ligne arrivant de l’extérieur de l’UE, selon [les règles douanières de la Commission européenne](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en), un droit de douane forfaitaire de 3 € par catégorie d’article s’applique jusqu’à 150 € (en vigueur depuis le 1er juillet 2026) et une taxe de traitement UE de 2 € s’applique également (en vigueur depuis le 1er novembre 2026), en plus de la TVA polonaise à l’importation. Consulte [colis retenu en douane](guide:customs).

**Dégâts visibles et cachés.** Tu peux refuser un colis visiblement abîmé à la livraison afin que le livreur le consigne et le retourne à l’expéditeur. Selon les [Conditions générales de GLS Poland](https://gls-group.com/PL/pl/regulamin/), tout dommage non apparent constaté après acceptation doit être signalé dans les **7 jours** (par écrit au siège de GLS ou via le [formulaire de réclamation consommateur](https://gls-group.com/PL/pl/reklamacje-konsumenckie/)). Les délais généraux de réclamation consommateur sont de 30 jours à compter de la prise en charge par GLS (45 jours pour perte, dans la limite de 12 mois après expédition), et GLS répond sous 30 jours. Consulte [colis endommagé](guide:damaged-parcel) et [colis perdu](guide:lost-parcel).

**Suivi silencieux ou livré mais introuvable.** Si le suivi reste figé plusieurs jours, consulte [pourquoi le suivi ne se met plus à jour](guide:tracking-not-updating). Si un colis s’affiche livré alors que tu ne l’as pas, vérifie s’il t’attend en GLS Point, demande aux voisins et contacte le vendeur ; consulte [colis livré mais non reçu](guide:delivered-not-received) et [retourné à l’expéditeur](guide:returned-to-sender).

## Service client GLS Poland et alerte aux arnaques

Contacte d’abord la boutique en ligne pour tout remboursement. Pour joindre directement GLS Poland (coordonnées sur [GLS Contact](https://gls-group.com/PL/en/gls-contact/) et [Corporate Information](https://gls-group.com/PL/en/corporate-information/)) :

- **Ligne client :** `+48 46 814 82 20` (lundi au vendredi 07h00–19h00, samedi 07h00–15h00).
- **E-mail :** `gls@gls-poland.com` (siège social `office@gls-poland.com`).
- **Formulaires en ligne :** [formulaire de contact GLS Poland](https://gls-group.com/PL/en/gls-contact/) et [formulaire de réclamation consommateur](https://gls-group.com/PL/pl/reklamacje-konsumenckie/).
- **Assistance technique consignes 24h/24 :** `801 167 536` / `502 167 536`.
- **Siège social :** General Logistics Systems Poland Sp. z o.o., ul. Tęczowa 10, Głuchowo, 62-052 Komorniki, Pologne.

> **Alerte phishing sur [la page conseils de sécurité de GLS Poland](https://gls-group.com/PL/en/security-advices/) :** GLS n’envoie jamais de lien par e-mail pour payer des achats par carte ou virement (CashService/ExWorksService se règle directement au livreur en espèces, par carte ou BLIK à la remise) et n’envoie jamais de messages opérationnels depuis des messageries gratuites comme Gmail ou Outlook. Transfère tout message suspect à `security@gls-poland.com` (ou `dataprotection@gls-poland.com`) et signale les liens frauduleux polonais au CSIRT NASK CERT (`incydent.cert.pl`, `cert@cert.pl` ou SMS `8080`). Consulte [les SMS d’arnaque à la livraison](guide:delivery-scam-texts).

## Suivre un colis GLS Poland avec Peek

Colle le numéro dans [Peek](/). Comme plusieurs transporteurs partagent des numéros de 11 et 12 chiffres, Peek interroge les plus probables en s’appuyant sur ton pays (la région de ton téléphone, ou ton pays de connexion sur le web). En Pologne, il interroge GLS Poland en premier, avant [GLS France](carrier:gls-fr), [GLS Allemagne](carrier:gls-de), [GLS Suisse](carrier:gls-ch) et d’autres comme [PPL](carrier:ppl) pour 11 chiffres ou [BRT](carrier:brt) et [Colis Privé](carrier:colis-prive) pour 12, et affiche généralement « GLS Poland a ce colis. ». Ailleurs, GLS Poland n’est interrogé que si les autres transporteurs probables ne connaissent pas le numéro. Un lien `gls-group.com` ou `gls-group.eu` contenant `/PL/` et le numéro dans `match=`, `parcelNumber=` ou `matchParcelNumber=` est suivi directement comme GLS Poland. Sinon, appuie sur « Changer » à l’ajout ou « Changer de transporteur » sur un colis déjà ajouté, puis choisis GLS Poland.

Le champ « Code postal de livraison » porte la mention « Facultatif », mais GLS n’affiche ses scans qu’avec lui, comme le rappelle la ligne sous le champ : « GLS Poland n’affiche les scans qu’avec le code postal de livraison. ». Sans lui, Peek obtient le statut GLS sans les scans : il interroge d’autres services de suivi en attendant, et la page du colis propose « Ajouter le code postal ». Avec un compte, Peek essaie une fois le code postal que tu as donné pour un colis GLS Poland précédent ou, à défaut, celui de ton dernier colis. Si GLS ne trouve pas le colis avec ce code postal, Peek le signale et propose « Modifier le code postal ». Tant que GLS attend le code postal ou ne trouve pas le colis avec le tien et que rien ne répond pendant une journée, Peek vérifie une fois par jour ; ajouter ou modifier le code postal lance une vérification immédiate. Le code postal est envoyé à GLS et, si besoin, à des services de suivi, et n’apparaît jamais sur les liens partagés.

Avec le code postal, tu vois le statut, le lieu de chaque scan sur une carte, le jour prévu, le poids, l’heure de livraison et, en GLS Point, son nom et son adresse. Ton nom, ton adresse, tes coordonnées, la signature et la référence de l’expéditeur sont retirés. Peek lit le service anglais de GLS plutôt que la page polonaise : `W doręczeniu` y apparaît comme `In delivery` et `Paczka została doręczona` comme `The parcel has been delivered.`. Une arrivée en GLS Point ou en consigne s’affiche en « Prêt à être retiré ». Les horaires sont lus en heure polonaise.

Peek vérifie GLS environ une fois par heure, jour de livraison inclus, et actualiser à la main ne vérifie pas plus tôt (4 heures après un échec) ; toutes les 6 heures après sept jours sans nouvelles sur « Étiquette créée ». Via des services de suivi seuls, il vérifie toutes les heures, et toutes les 3 heures après une journée calme.

Lorsque le dernier scan date de quatre jours et que le colis reste à « Étiquette créée », « Pris en charge » ou « En transit », Peek le signale avec la date de ce scan, par exemple « Aucune mise à jour depuis lun. 12 oct. ». La fiche du colis affiche alors « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger GLS Poland. », avec un lien « Demande de l’aide à GLS Poland » vers la page de contact de GLS Poland. Après trois semaines sans nouveau scan, elle indique « Rien de nouveau depuis plus de trois semaines. Si le colis n’est toujours pas arrivé, contacte le vendeur. Peek continue d’interroger GLS Poland. »

Tu peux choisir les réglages de notification « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Peek assure uniquement le suivi et ne peut ni rediriger un colis ni déposer une réclamation auprès de GLS Poland à ta place.

## Questions sur le suivi GLS Poland

### Combien de temps GLS Poland garde-t-il un colis en GLS Point ou en consigne ORLEN ?

Selon les [Conditions générales](https://gls-group.com/PL/pl/regulamin/) et la [FAQ](https://gls-group.com/PL/en/faq/) actuelles de GLS Poland, les GLS Points gardent les colis 7 jours calendaires (les pages réception et GLS Points mentionnent encore sept jours ouvrables), tandis que les consignes 24h/24 ORLEN Paczka les conservent 72 heures avant retour automatique à l’expéditeur.

### Pourquoi GLS Poland demande-t-il mon code postal pour le suivi complet ?

GLS protège les détails d’expédition en ne renvoyant l’historique complet des scans, les options de livraison et les infos ParcelShop que si le numéro de colis correspond au code postal du destinataire. Saisis le code postal de livraison dans Peek pour recevoir le flux direct de GLS.

### Puis-je payer un colis GLS Poland CashService par carte ou BLIK ?

Oui. GLS Poland indique que les livraisons CashService (jusqu’à 6 000 PLN par jour) peuvent être réglées au livreur à la remise en espèces, par carte bancaire ou via BLIK — jamais via un lien reçu par e-mail non sollicité.

### Comment rediriger un colis GLS Poland vers un magasin Żabka ou une consigne ?

Ouvre le lien FlexDeliveryService dans l’e-mail de notification GLS (aucun compte requis) ou utilise [la redirection de colis](https://gls-group.com/PL/en/parcel-redirection/) pour orienter le colis vers un GLS Point (dont les magasins Żabka), une consigne ORLEN Paczka, une autre adresse ou une date ultérieure.

:::sources
- [GLS Poland : Parcel Tracking](https://gls-group.com/PL/en/parcel-tracking/) – numéro de colis, Track-ID et recherche par code postal
- [GLS Poland : About GLS](https://gls-group.com/PL/en/about-gls/) – centre central de Stryków, agences et aperçu du réseau
- [GLS Poland : Corporate Information](https://gls-group.com/PL/en/corporate-information/) – entité juridique, adresse de Głuchowo et `office@gls-poland.com`
- [GLS Poland : Receiving Parcels](https://gls-group.com/PL/en/receiving/) – livraison en semaine, dépôt chez un voisin, seconde tentative, garde en dépôt et CashService
- [GLS Poland : Parcel Redirection](https://gls-group.com/PL/en/parcel-redirection/) – options FlexDeliveryService sans compte
- [GLS Poland : GLS Points](https://gls-group.com/PL/en/shipping-receiving-points/gls-points/) – 18 000+ GLS Points (dont 11 000 magasins Żabka), 26 000+ points hors domicile, limites poids/dimensions et délais UE
- [GLS Poland : Parcel Lockers](https://gls-group.com/PL/en/shipping-receiving-points/parcel-lockers/) – ~8 000 consignes partagées ORLEN Paczka 24h/24, garde de 72 heures, PIN/QR/appli myGLS, limite 20 kg et assistance consignes
- [GLS Poland : Live Tracking](https://gls-group.com/PL/en/live-tracking/) – suivi sur carte et heure prévue à 15 minutes près
- [GLS Poland : FAQ](https://gls-group.com/PL/en/faq/) – garde de 7 jours calendaires en GLS Point, CashService espèces/carte/BLIK, réponses en cas d’absence
- [GLS Poland : Conditions générales (Regulamin)](https://gls-group.com/PL/pl/regulamin/) – conditions, celles pour GLS Points et consignes en vigueur depuis le 9 novembre 2026 : garde de 7 jours calendaires, signalement de dommages cachés sous 7 jours, délais de réclamation de 30/45 jours et réponse sous 30 jours
- [GLS Poland : Contact](https://gls-group.com/PL/en/gls-contact/) – hotline `+48 46 814 82 20` (lun–ven 07h00–19h00, sam 07h00–15h00), `gls@gls-poland.com` et formulaire de contact
- [GLS Poland : Réclamations consommateurs](https://gls-group.com/PL/pl/reklamacje-konsumenckie/) – dépôt de réclamation consommateur en ligne
- [GLS Poland : Security Advices](https://gls-group.com/PL/en/security-advices/) – alertes phishing, `security@gls-poland.com` et signalement CERT NASK
- [GLS Group : Security Advice](https://gls-group.com/GROUP/en/security-advice/) – domaines officiels du groupe et règles CashService
- [Commission européenne : Réforme douanière de l’UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – droit de douane de 3 € jusqu’à 150 € (depuis le 1er juillet 2026) et taxe UE de 2 € (depuis le 1er novembre 2026)
- [Universal Parcel Scraper : gls-pl](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/gls-pl) – détection 11/12 chiffres, priorité Pologne, code postal facultatif, correspondance des statuts, rythme horaire/4 heures et URL d’aide
:::
