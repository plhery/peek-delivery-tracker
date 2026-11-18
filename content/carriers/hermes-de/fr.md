---
title: Suivi Hermes Allemagne : statuts expliqués, relais et suivi
description: Suis un colis Hermes Germany (myHermes) en français : numéro de suivi, statuts anglais et allemands traduits, relais, douane, droits et suivi avec Peek.
slug: suivi-hermes
published: 2026-11-18
updated: 2026-11-18
---

Hermes Germany (aussi affiché sous *myHermes* ou *Hermes Borderguru*) est le transporteur colis allemand du groupe Otto (*Otto Group*). Pour suivre une commande expédiée depuis l'Allemagne, consulte le site allemand `myhermes.de` ou colle ton numéro dans le champ ci-dessus sur [Peek](/) afin d'afficher l'étape actuelle en français et les horaires dans ton fuseau horaire.

Au Royaume-Uni, l'ancienne filiale Hermes UK est devenue [Evri](carrier:evri) en mars 2022 ; Hermes Germany reste un transporteur allemand distinct. Pour le mobilier et le gros électroménager livrés en Allemagne par équipes de deux personnes, le groupe utilise un service séparé (*Hermes Einrichtungs-Service*).

## Numéro de suivi Hermes : où le trouver et formats

Le numéro de suivi (*Sendungsnummer*) figure dans l'e-mail d'expédition du marchand ou dans le détail de ta commande (par exemple sous *Mein Konto* sur OTTO). Sans numéro, demande-le au vendeur ou consulte [comment trouver un numéro de suivi](guide:find-tracking-number).

Le suivi public Hermes Germany demande un numéro de 14 à 20 lettres ou chiffres :

| Format (inventé) | Signification | Reconnaissance dans Peek |
| --- | --- | --- |
| `H1048291057364928` | `H` et 16 à 19 chiffres : code propre à Hermes Germany. | Reconnu aussitôt comme Hermes Germany. |
| `74829105736493` | 14 chiffres avec clé Hermes : partagé avec [DPD](carrier:dpd-de), [BRT](carrier:brt), [Chronopost](carrier:chronopost) et d'autres. Avec le préfixe `022` (`02248291057367`), Hermes est testé en premier. | Hermes est interrogé parmi les transporteurs probables ; un code de 15 chiffres avec zéro en tête (`002248291057367`) est vérifié pareillement. |
| `H482910573649283` | `H` et 15 chiffres : Hermes Germany ou [Evri](carrier:evri) (Royaume-Uni). | Peek interroge Hermes Germany (et Evri si la clé Evri correspond). |

Les bagages *Hermes Gepäckversand* (Deutsche Bahn) et les références à 14 chiffres hors clé de code-barres se suivent par numéro de commande : clique sur « Changer » (ou « Changer de transporteur ») et choisis **Hermes Germany**.

## Statuts de suivi Hermes expliqués en français

Sur `myhermes.de`, tout est en allemand ; en français, Peek interroge l'interface anglaise d'Hermes Germany, mais certaines lignes restent en allemand :

| Message Hermes | Étape Peek | Explication |
| --- | --- | --- |
| `The shipment was announced to Hermes electronically. Further information will follow as soon as Hermes received the shipment.` | « Étiquette créée » | Envoi annoncé informatiquement ; Hermes n'a pas encore reçu le colis. |
| `The shipment was taken over by Hermes in … and is prepared for further shipping.` | « Pris en charge » | Colis collecté au dépôt allemand nommé. |
| `Die Sendung wird an Hermes weitergeleitet. Weitere Informationen folgen, sobald uns die Sendung erreicht hat.` | « En transit » | Envoi international en route vers Hermes. |
| `The shipment has arrived in the destination country … and is prepared for the further shipping.` | « En transit » | Arrivé dans le pays de destination et en cours de tri vers le partenaire local. |
| `The shipment was prepared for further shipping (…).` | « En transit » | Trié par le partenaire dans le pays indiqué entre parenthèses. |
| `The shipment was loaded into the delivery vehicle and is expected to be delivered today.` / `The shipment was loaded into the delivery vehicle. The delivery is expected to take place today.` | « En livraison » | Chargé dans le véhicule pour livraison aujourd'hui. |
| `The shipment is ready for pick up in a parcel shop/store.` | « Prêt à être retiré » | Disponible en point relais ou *PaketShop*. |
| `The shipment could not be delivered.` / `The shipment could not be delivered because the address was not found.` | « Tentative de livraison » | Remise échouée ou adresse introuvable (`Die Zustelladresse wurde nicht gefunden. Mögliche Gründe: Fehler in der Adressübermittlung. Bitte PaketShop buchen oder Kundenservice kontaktieren.`). |
| `The shipment was not picked up in the parcel shop/store.` | « Problème signalé » | Non retiré à temps en relais ; retour vers l'expéditeur. |
| `The shipment was delivered to the recipient address.` / `Die Sendung wurde erfolgreich zugestellt.` | « Livré » | Remis à l'adresse indiquée ou distribué à l'étranger. |

Les lignes `Für die Sendung wurde ein WunschPaketShop gebucht.` et `Ein WunschTag für die Zustellung wurde gebucht.` notent un choix de livraison, pas un mouvement : Peek les ignore. Si la tournée manque de temps avant ton adresse, aucun échec n'est compté et le colis reste « En transit » jusqu'au jour ouvrable suivant.

## Livraison en Allemagne et vers la France, la Belgique ou la Suisse

**En Allemagne.** Le réseau réunit plus de 17 000 *Hermes PaketShops*. Les articles en stock expédiés par OTTO via *Hermes Paketversand* arrivent habituellement en 1 à 2 jours ouvrables (lundi–samedi). Avec *Hermes WunschZustellung* (suivi ou application), le destinataire choisit un lieu sûr (*Ablageort*), un voisin (*Nachbar*), un *PaketShop* ou un autre jour (*Tag*). En *PaketShop*, le colis est gardé jusqu'à 7 jours ouvrables contre pièce d'identité (*Personalausweis*). Dans une consigne *Hermes Box*, un code demandé sur le suivi donne 72 heures pour le retrait (affiché « Prêt à être retiré » jusque-là). Les livraisons lourdes par équipes de deux (*2-Mann-Teams*) sont annoncées par e-mail et SMS avec appel 30 à 60 minutes avant et suivi sur carte à la demi-heure près.

**À l'international.** Hors d'Allemagne, Hermes confie le dernier kilomètre à un transporteur partenaire local. Quand Hermes publie le lien du partenaire et que Peek en reconnaît le domaine et le numéro, la fiche indique « Livraison par … » (ou « Annoncé à …, pas encore reçu » avant le premier scan du partenaire) et suit les scans locaux. Avis de passage, relais et garde dépendent alors de ce transporteur local.

## Douane, TVA et faux messages de paiement

- **France, Belgique, Luxembourg :** un envoi venant d'Allemagne circule dans l'Union européenne sans douane ni frais de dossier. Pour un colis venant de hors UE en vente à distance, l'exonération sous 150 € prend fin : droit fixe de **3 €** par catégorie d'articles jusqu'à 150 € dès le **1er juillet 2026**, plus **2 €** de frais européens de traitement par catégorie d'articles quelle que soit la valeur dès le **1er novembre 2026**.
- **Suisse :** les colis d'Allemagne passent la douane suisse. Selon l'OFDF, TVA et droits ne sont pas perçus en dessous de **5 CHF** chacun (cadeaux privés francs jusqu'à **100 CHF**, hors alcool et tabac) ; au-delà, le transporteur ajoute ses frais de dédouanement, publiés notamment par [La Poste suisse](carrier:swiss-post).

> **Faux SMS et e-mails :** en France, Cybermalveillance.gouv.fr rappelle de ne jamais payer via un lien reçu par SMS ou courriel réclamant quelques euros pour un colis et de signaler le SMS au **33700**. En Belgique, [bpost](carrier:bpost) ne demande un paiement (uniquement dans *My bpost*, le *Track & Trace* officiel ou un bureau de poste) que pour les frais d'importation d'un envoi hors UE — jamais pour un colis parti d'Allemagne.

## Colis Hermes en retard, absent ou endommagé

- **Bloqué sur « Étiquette créée » :** Hermes attend encore la remise du colis par le vendeur ; contacte la boutique et lis [pourquoi un suivi ne bouge plus](guide:tracking-not-updating).
- **Marqué livré mais introuvable :** vérifie voisin et relais puis préviens le marchand ; vois [colis livré mais non reçu](guide:delivered-not-received).
- **Achats en ligne (UE et droit allemand) :** le vendeur professionnel supporte le risque du transport jusqu'à la remise physique (§ 475 BGB) et doit livrer sous **30 jours** sauf accord contraire ; tu disposes de **14 jours** après réception pour te rétracter (§§ 355 et 356 BGB).
- **Droit allemand des transports et médiation :** selon le § 438 HGB, signale une avarie visible à la livraison, un dommage caché par écrit sous **7 jours** et un retard sous **21 jours** (droits propres du destinataire au § 421 HGB). Après échec d'une réclamation écrite auprès d'Hermes pour perte, vol ou avarie d'un envoi sans conditions spéciales (litige de 10 € à 2 500 €), expéditeur ou destinataire peut saisir gratuitement en allemand la **Schlichtungsstelle Post** (*Bundesnetzagentur*), obligatoire pour l'opérateur face à un consommateur.

## Suivre un colis Hermes Germany avec Peek

[Peek](/) interroge le suivi destinataire d'Hermes sans code postal et accepte les liens `myhermes.de`. Il place sur la carte le dépôt, la région, le pays ou la ville du *PaketShop* cités dans le scan, convertit les dates (« Heures dans ton fuseau horaire : … ») et ne lit que l'adresse d'un *PaketShop* actif ou utilisé, jamais ton domicile.

Lorsque le suivi est ouvert à l'écran, ou le jour de livraison avec notifications activées, Peek vérifie jusqu'à toutes les 10 minutes — et toutes les 2 minutes le jour du départ en tournée dès 6 h heure allemande (ou 30 minutes avant le créneau Hermes) — tant qu'Hermes répond directement (via services de suivi : toutes les heures, toutes les 3 heures après une journée sans scan, jusqu'à toutes les 15 minutes ce jour-là) ; autrement, le contrôle est horaire, y compris après deux jours sans scan (ou 12 heures sur « Étiquette créée » ou « Pris en charge »). Avec un partenaire local, Peek suit la cadence de ce dernier. Alertes au choix : « Chaque scan », « Étapes importantes » ou « Livraison seulement » ; les anciens numéros purgés renvoient 404.

## Questions fréquentes (FAQ)

### Hermes et Evri sont-ils le même transporteur ?

Uniquement au Royaume-Uni, où Hermes UK est devenu [Evri](carrier:evri) en mars 2022. Hermes Germany est une société allemande distincte du groupe Otto ; sur `H` suivi de 15 chiffres, Peek teste les deux si la clé concorde.

### Pourquoi les scans Hermes sont-ils en anglais ou en allemand ?

Peek demande à Hermes Germany ses textes d'événements en anglais pour un lecteur francophone (quelques lignes restent en allemand) et traduit l'étape principale en français.

### Comment suivre des meubles expédiés avec Hermes en Allemagne ?

Les livraisons par équipes de deux relèvent de *Hermes Einrichtungs-Service*, distinct de *myHermes* (annonce par e-mail/SMS et appel 30 à 60 minutes avant l'arrivée). Choisis-le via « Changer de transporteur » dans Peek si besoin.

### Y a-t-il des frais de douane depuis l'Allemagne ?

Non vers la France, la Belgique ou le Luxembourg (libre circulation dans l'UE). Vers la Suisse, le dédouanement s'applique dès 5 CHF de TVA ou de droits.

:::sources
- [Otto Group : Konzernfirmen](https://www.ottogroup.com/de/ueber-uns/konzernfirmen/hermes-germany.php) – groupe Otto
- [OTTO : Lieferung](https://www.otto.de/service/lieferung/), [FAQ](https://www.otto.de/service/faq/) et [Live-Tracking](https://www.otto.de/shoppages/geographische_sendungsverfolgung) – délais, *WunschZustellung*, 17 000 *PaketShops* (7 jours, pièce d'identité) et équipes de deux
- [Evri : About us](https://www.evri.com/about-us) – Hermes UK devenu Evri (mars 2022)
- [Bundesnetzagentur : Schlichtungsstelle Post](https://www.bundesnetzagentur.de/DE/Vportal/Schlichtung/Schlichtung_Post/start.html) – médiation postale gratuite
- [BGB : § 475](https://www.gesetze-im-internet.de/bgb/__475.html), [§ 355](https://www.gesetze-im-internet.de/bgb/__355.html), [§ 356](https://www.gesetze-im-internet.de/bgb/__356.html) et [HGB : § 421](https://www.gesetze-im-internet.de/hgb/__421.html), [§ 438](https://www.gesetze-im-internet.de/hgb/__438.html) – garantie, rétractation et délais de transport
- [Your Europe : Livraison](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_fr.htm), [EU Customs Reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) et [Guidance 3 €](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – règles UE et douane 2026
- [Cybermalveillance : Escroqueries colis](https://www.cybermalveillance.gouv.fr/tous-nos-contenus/actualites/escroqueries-livraison-colis) et [bpost : Hors UE](https://www.bpost.be/fr/shopping-hors-ue) – prévention du phishing
- [OFDF : Réception](https://www.bazg.admin.ch/fr/reception-de-lettres-et-paquets), [Frais](https://www.bazg.admin.ch/fr/frais-dedouanement-et-suivi-des-colis) et [Poste suisse : Dédouanement](https://www.post.ch/fr/reception/dedouanement-a-l-importation) – importations en Suisse
- [Universal Parcel Scraper : hermes-de](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/hermes-de) et [statuses.json](https://github.com/plhery/universal-parcel-scraper/blob/main/carriers/hermes-de/statuses.json) – lecture dans Peek
:::
