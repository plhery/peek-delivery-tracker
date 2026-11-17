---
title: Suivi DPD Allemagne (DPD Germany) : numéro, statuts et aide
description: Suis un colis DPD Allemagne vers la France, la Belgique, la Suisse ou en Allemagne : numéro à 14 chiffres, statuts anglais, Predict, Relais Pickup et contact.
slug: suivi-dpd-allemagne
published: 2026-11-17
updated: 2026-11-17
---

DPD en Allemagne est DPD Deutschland GmbH (Aschaffenburg), membre du réseau Geopost avec 10 000 Paketshops outre-Rhin. Quand une boutique allemande expédie vers la France, la Belgique, la Suisse ou une adresse en Allemagne, tu suis l’envoi avec son numéro à 14 chiffres sur le site de DPD ou dans le champ ci-dessus.

Tant que le colis circule en Allemagne, DPD Deutschland assure le tri ; après la frontière, la remise passe aux entités Geopost locales ([DPD France](carrier:dpd-fr), DPD Belgium ou [DPD Suisse](carrier:dpd-ch)) sous ce même numéro à 14 chiffres.

## Numéro de colis DPD Allemagne : format et où le trouver

D’après DPD Deutschland, le numéro de colis figure dans la confirmation d’expédition du marchand, l’e-mail d’annonce DPD ou — selon la FAQ anglaise — le SMS de DPD (les SMS authentiques de DPD Allemagne viennent uniquement du `+49 177 1787807`). Sur un avis de passage en Allemagne (*Paketinfokarte*), le numéro sous le QR code est le numéro de colis.

| Exemple (inventé) | Ce que c’est |
| --- | --- |
| `01482910573649` | Numéro de colis à 14 chiffres utilisé par le suivi DPD. |
| `014829105736492` | Même numéro suivi d’un 15e caractère de contrôle (chiffre ou lettre majuscule). |

DPD n’explique pas ce 15e caractère : d’après le code de Peek, c’est un caractère de contrôle imprimé sur les étiquettes et avis après les 14 chiffres. Les 14 chiffres suffisent au suivi. Une simple référence de commande ne permet pas de chercher l’envoi : DPD demande toujours le numéro de colis (*Paketnummer*). Voir aussi [retrouver son numéro de suivi](guide:find-tracking-number).

Les numéros à 14 chiffres servent entre autres aussi à [DPD France](carrier:dpd-fr), [DPD Suisse](carrier:dpd-ch), DPD Belgium, [Chronopost](carrier:chronopost), [Ciblex](carrier:ciblex), [SEUR](carrier:seur), [BRT](carrier:brt) et Hermes : vérifie quel transporteur nomme l’e-mail du vendeur.

## Statuts du suivi DPD Allemagne

Peek interroge DPD Allemagne en anglais et affiche les scans tels quels, dans l’ordre habituel du trajet :

| Message de DPD | Signification |
| --- | --- |
| `Order information has been transmitted to DPD.` | Annoncé : données transmises par l’expéditeur, colis pas encore chez DPD (`Auftragsdaten übermittelt` en allemand). |
| `Parcelinfo handed to DPD` | Données reçues sans le colis ; Peek affiche « Étiquette créée » (uniquement via des services de suivi tiers). |
| `Parcel handed to DPD` | Colis pris en charge par DPD. |
| `Parcel handed to Pickup parcelshop by consignor.` | Déposé en Pickup Paketshop par l’expéditeur (*consignor*). |
| `In transit.`, `Your parcel is on its way` | En transit dans le réseau DPD. |
| `At parcel delivery centre.` | Au centre de tri ou dépôt DPD (*Paketzustellzentrum*). |
| `Out for delivery.` | Dans le véhicule pour livraison aujourd’hui. |
| `Unsuccessful delivery attempt` | Tentative infructueuse ; nouvelle présentation ou relais prévu. |
| `Back at parcel delivery centre after an unsuccessful delivery attempt.` | Retour au dépôt après l’échec de livraison. |
| `Delivered by driver to DPD Pickup parcelshop/ station.` | Déposé en commerce Pickup ou Pickup Station (« Prêt à être retiré »). |
| `Picked up from Pickup parcelshop by consignee.` | Retiré au point Pickup par le destinataire (*consignee*, toi) : livré. |
| `Delivered.`, `Parcel has been left in: mail box` | Livré ; la seconde ligne précise le lieu (ici la boîte aux lettres). |
| `At parcel delivery centre. (Return to sender)` | Retour vers l’expéditeur en cours. |

Attention à quatre formules : `Pickup ordered for: …` vise l’enlèvement chez l’expéditeur, et `Goods issue at sender.` la sortie d’entrepôt avant remise à DPD. `We're sorry but your parcel couldn't be delivered as arranged.` contient *delivered* mais signale un échec de livraison. `Delivered. (Return to sender)` confirme le retour remis à l’expéditeur.

Sur le site allemand, continuer sans code postal ne montre qu’un statut général ; si `Im Paketzustellzentrum` ne bouge plus plusieurs jours ouvrés, demande au vendeur d’ouvrir une enquête (*Nachforschung*).

## Livraison, créneau Predict et points Pickup

**En Allemagne.** DPD annonce généralement 1 à 2 jours ouvrés après prise en charge (sans délai garanti en DPD CLASSIC). Si le vendeur a transmis ton e-mail ou mobile, Predict envoie le matin un créneau de 2 heures, affiné jusqu’à 30 minutes en direct (hors îles). Dans l’app DPD, myDPD ou le suivi avec code postal, tu peux rediriger vers un voisin proche, un Paketshop, un lieu sûr (*Abstell-Okay*, avec photo obligatoire et transfert du risque de perte ou dommage sur toi) ou un autre jour ; seule la dernière consigne avant mise en tournée compte. Un second essai a lieu le jour même ou le lendemain ouvré, mais les CGV autorisent dès le premier passage la remise à un voisin à 50 m maximum ou au point Pickup proche (sauf opposition). Retrait sur pièce d’identité officielle avec photo (sans avis papier, ou avec [procuration signée](https://www.dpd.com/de/de/support/vollmacht/) et pièce du mandataire) : la FAQ indique 7 jours calendaires en Paketshop et 3 en Paketstation (7 jours ouvrables dans les CGV après échec à domicile). Livraison du samedi uniquement en express DPD 12 Uhr (hors îles), et aucun retrait particulier au dépôt.

**Vers la France, la Belgique et la Suisse :**

- **France ([DPD France](carrier:dpd-fr)) :** selon les CGV, garde de 9 jours calendaires en Relais Pickup (dès le lendemain de l’arrivée) et 5 jours calendaires en consigne Pickup (dès le jour de dépôt).
- **Belgique (DPD Belgium) :** livraisons lundi–samedi 8 h 30–18 h 00 ; garde de 5 jours calendaires (dimanche inclus) en Pickup parcelshop.
- **Suisse ([DPD Suisse](carrier:dpd-ch)) :** retrait en [DPD Pickup Location](https://www.dpd.com/ch/fr/dpd-pickup-location/) avec pièce d’identité officielle avec photo (ou procuration et pièce du porteur).

**Retours.** Avec étiquette retour du marchand, dépôt gratuit en Allemagne en Paketshop, Pickup Station ou au chauffeur DPD (ou QR code mobile dans l’app DPD ou myDPD) ; voir [suivre un colis de retour](guide:returns-tracking).

## Douane : UE, Suisse et pays tiers

Aucune douane entre l’Allemagne (hors Helgoland et Büsingen), la France métropolitaine et la Belgique (hors outre-mer français, exclus du territoire TVA ou douanier de l’UE). En revanche :

- **Suisse :** selon l’OFDF, la TVA à l’importation, d’éventuels droits et les frais de dédouanement du transporteur peuvent s’appliquer ; pour ces frais et le suivi, l’OFDF renvoie au transporteur. Les e-mails officiels de facturation et d’importation de DPD Suisse viennent de `no-replyinvoicing@dpd.ch`, `import@dpd.ch`, `export@dpd.ch` ou `faktura@dpd.ch`.
- **Hors UE vers l’UE :** DPD Deutschland rappelle que droits, TVA d’importation et frais administratifs s’appliquent si l’expéditeur ne les a pas réglés. Dans l’UE, un droit temporaire de 3 € par article remplace depuis le 1er juillet 2026 la franchise sous 150 €, complété depuis le 1er novembre 2026 par 2 € de frais de traitement de l’Union par article distinct, répercutables par le vendeur ou le transporteur. Voir [colis bloqué en douane](guide:customs).

## Colis bloqué, marqué livré ou endommagé

Le contrat lie DPD à l’expéditeur : selon DPD Deutschland, le vendeur gère remboursement, remplacement ou enquête officielle, et selon Your Europe il reste responsable du bien dans l’UE jusqu’à ta réception.

**Marqué livré mais absent.** Vérifie voisin ou point Pickup dans le suivi, puis entrée, garage, boîte aux lettres, poubelles et porte arrière. Préviens ensuite le vendeur, puis le formulaire DPD si besoin ; voir [livré mais non reçu](guide:delivered-not-received) et [suivi immobile](guide:tracking-not-updating).

**Endommagé et droit allemand.** Photographie carton et marchandise et préviens le vendeur. En droit allemand : assurance d’office jusqu’à 520 € par colis (au profit de l’expéditeur selon les CGV, plafonnée au prix d’achat prouvé), droits propres du destinataire (`§ 421 HGB`), et délais d’avis au transporteur (`§ 438 HGB`) : à la livraison pour un dommage apparent, 7 jours par écrit (forme texte) pour un dommage caché, 21 jours pour un retard. En cas de litige non résolu sur perte ou avarie, l’*Impressum* cite la médiation postale de la Bundesnetzagentur (*Schlichtungsstelle Post*).

## Contacter DPD Allemagne et les relais locaux

- **DPD Allemagne :** `06021-150415` (`+49 6021 150 415` depuis l’étranger), `info@dpd.de`, menu « Plus / Chat / Aide / Contact » dans l’app DPD, ou [formulaire DPD Allemagne](https://www.dpd.com/de/de/support/kontakt/) (`06021 8430` dans l’Impressum est le standard du siège).
- **France ([DPD France](carrier:dpd-fr)) :** `09 70 80 85 66` (non surtaxé, lun.–ven. 8 h–20 h, sam. 10 h–18 h hors fériés) sur la [page contact DPD France](https://www.dpd.com/fr/fr/contact/service-clients/).
- **Belgique (DPD Belgium) :** `070/222 222` (0,30 €/min, lun.–ven.) sur [dpd.com/be](https://www.dpd.com/be/en/contact/).
- **Suisse ([DPD Suisse](carrier:dpd-ch)) :** `058 311 13 13` (lun.–ven. 8 h–18 h, max. 0,08 CHF/min) sur [dpd.com/ch](https://www.dpd.com/ch/fr/centre-de-support/votre-demande/).

> Selon DPD Deutschland, ses SMS viennent uniquement du `+49 177 1787807` et ses e-mails de `@dpd.de` ou de domaines officiels DPD (jamais de pièce `.doc`). En cas de doute sur des frais réclamés, vérifie le numéro sur dpd.com/de/de/ ou écris à `info@dpd.de`. En France, DPD France invite à transférer les SMS suspects au `33700`.

## Suivre un colis DPD Allemagne avec Peek

[Peek](/) accepte les 14 chiffres avec ou sans caractère de contrôle et les liens `tracking.dpd.de`, `my.dpd.de` et `dpd.com/de`. Les préfixes `01` placent DPD Germany en première vague ; les préfixes `10` et `1550` vont par défaut à [DPD France](carrier:dpd-fr) ou DPD UK. En détection automatique, DPD Germany ne répond (« DPD Germany a ce colis. ») que tant que DPD localise l’envoi en Allemagne ; sinon touche « Changer » (ou « Changer de transporteur ») et choisis DPD Germany sous « Correspond à ce numéro ». Une fois choisi, si le colis passe dans un autre pays, Peek le garde sous DPD Germany et lit en parallèle le suivi groupe et l’unité locale dotée d’un suivi propre ([DPD France](carrier:dpd-fr), [SEUR](carrier:seur), [BRT](carrier:brt)).

Le champ « Code postal de livraison » est facultatif et réservé aux codes allemands à 5 chiffres (laisse-le vide hors d’Allemagne). Sans code postal, Peek lit l’app allemande : scans géolocalisés sur la carte, poids, dimensions, date ou créneau prévu, ainsi que nom et adresse du point Pickup en instance. Avec un code allemand valide à 5 chiffres, Peek lit le suivi vérifié : scans vérifiés, nom de l’expéditeur, poids et créneau (sans dimensions ni adresse Pickup). Si DPD refuse le code, Peek repasse sans code et affiche « DPD Germany n’a pas accepté le code postal … et affiche donc moins de détails. ». Preuve de livraison écartée, nom de réceptionnaire non mappé remplacé par `Delivery update`, et heures allemandes converties (« Heures dans ton fuseau horaire : … »).

Peek vérifie le colis jusqu’à toutes les 10 minutes tant que tu as le colis ouvert ou, le jour de la livraison, avec les notifications activées, et toutes les heures sinon (ainsi qu’après deux jours sans nouveauté, ou après 12 heures sur « Étiquette créée » ou « Pris en charge »). En statut « En livraison », la fréquence monte jusqu’à toutes les 2 minutes dès 6 h 00 locales — ou 30 minutes avant le créneau DPD — tant que DPD répond directement. Via les seuls services de suivi tiers, Peek contrôle toutes les heures, toutes les 3 heures après un jour sans scan et jusqu’à toutes les 15 minutes le jour de la livraison tant que tu as le colis ouvert ou les notifications activées (« Chaque scan », « Étapes importantes » ou « Livraison seulement »).

## Questions fréquentes sur le suivi DPD Allemagne

### Pourquoi mon numéro DPD Allemagne n’est-il pas reconnu ?

La page d’erreur myDPD cite trois causes : erreur de saisie (`O` au lieu de `0` selon la FAQ), numéro archivé après 180 jours, ou colis annoncé mais pas encore remis à DPD.

### Combien de temps prend la livraison avec DPD Allemagne ?

En Allemagne, DPD annonce habituellement 1 à 2 jours ouvrés après remise (sans garantie en tarif standard), avec un créneau Predict de 2 heures le matin de la tournée si le vendeur a transmis tes coordonnées.

### DPD Allemagne livre-t-il le samedi ?

En Allemagne, uniquement en option payante avec l’express DPD 12 Uhr (hors îles, remise le vendredi). En Belgique, DPD Belgium livre du lundi au samedi.

### Puis-je retirer mon colis dans un dépôt DPD en Allemagne ?

Non : aucun retrait particulier au dépôt n’est possible. Redirige l’envoi vers un relais trouvé dans le [chercheur de Paketshops DPD](https://www.dpd.com/de/de/pickup-paketshops/paketshop-finden/).

:::sources
- [DPD : Startseite](https://www.dpd.com/de/de/) – 10 000 Paketshops
- [DPD : Paketnummer finden](https://www.dpd.com/de/de/faq/wo-finde-ich-meine-paketnummer/) – e-mail, carte
- [DPD : Parcel number](https://www.dpd.com/de/en/faq/where-do-i-find-my-parcel-number/) – SMS
- [DPD : Nummer unter QR-Code](https://www.dpd.com/de/de/faq/was-bedeutet-die-nummer-unter-dem-qr-code/) – Paketinfokarte
- [DPD : Paketnummer nicht erkannt](https://www.dpd.com/de/de/faq/meine-paketnummer-wird-nicht-erkannt-was-kann-ich-tun/) – 0 et O
- [DPD : Referenznummer](https://www.dpd.com/de/de/faq/was-ist-eine-referenznummer/) – référence
- [myDPD : Postleitzahl](https://my.dpd.de/myparcels/dataprotection-tracking.aspx) – 180 jours
- [DPD : Sendungsverfolgung](https://www.dpd.com/de/de/support/empfaenger/sendungsverfolgung/) – 14 chiffres, délai
- [DPD : PLZ beim Tracking](https://www.dpd.com/de/de/faq/warum-muss-ich-beim-tracking-meine-plz-eingeben/) – sans PLZ
- [DPD : Auftragsdaten übermittelt](https://www.dpd.com/de/de/faq/was-heisst-auftragsdaten-uebermittelt/) – préavis
- [DPD : Predict](https://www.dpd.com/de/de/faq/was-ist-predict/) – créneau
- [DPD : Paket empfangen](https://www.dpd.com/de/de/empfangen/) – îles, options
- [DPD : Mehrmals umleiten](https://www.dpd.com/de/de/faq/kann-ich-mein-paket-mehrmals-umleiten/) – dernière consigne
- [DPD : Abstellbedingungen](https://www.dpd.com/wp-content/uploads/sites/59/2026/09/20260923_Abstellbedingungen_DE_final.pdf) – photo, risque
- [DPD : Zweiter Zustellversuch](https://www.dpd.com/de/de/faq/das-paket-konnte-nicht-zugestellt-werden-gibt-es-einen-zweiten-zustellversuch/) – 2e essai
- [DPD : Paket abholen](https://www.dpd.com/de/de/support/empfaenger/paket-abholen/) – 7 et 3 jours
- [DPD : Ohne Paketinfokarte](https://www.dpd.com/de/de/faq/kann-ich-mein-paket-ohne-paketinfokarte-abholen/) – pièce d’identité
- [DPD : Vollmacht](https://www.dpd.com/de/de/support/vollmacht/) – procuration
- [DPD : Parcel return](https://www.dpd.com/de/en/support/recipients/parcel-return/) – retours
- [DPD : AGB DPD CLASSIC](https://www.dpd.com/wp-content/uploads/sites/59/2026/07/AGB-DPD-CLASSIC_06-2026_DE_END.pdf) – 50 m, 7 j ouvrables
- [DPD : Zusatzleistungen](https://www.dpd.com/wp-content/uploads/sites/59/2025/01/Beforderungsbedingungen-Zusatzleistungen-01-2025_DE_END.pdf) – samedi
- [DPD : Delivery center](https://www.dpd.com/de/en/faq/can-i-pick-up-my-parcel-myself-at-the-dpd-delivery-center/) – dépôt
- [DPD : Paketshop finden](https://www.dpd.com/de/de/pickup-paketshops/paketshop-finden/) – horaires
- [DPD : DPD oder Versender](https://www.dpd.com/de/de/faq/wann-wende-ich-mich-an-dpd-und-wann-an-den-versender/) – vendeur
- [DPD : Zugestellt, nicht erhalten](https://www.dpd.com/de/de/faq/mein-paket-wurde-laut-tracking-zugestellt-aber-ich-habe-es-nicht-erhalten-was-kann-ich-tun/) – recherches
- [DPD : Delivered, not here](https://www.dpd.com/de/en/faq/my-parcel-was-delivered-but-it-is-not-here-where-is-it/) – +49 6021 150 415
- [DPD : Paket beschädigt](https://www.dpd.com/de/de/faq/mein-paket-ist-beschaedigt-angekommen-was-kann-ich-tun/) – avarie
- [DPD : Versicherung](https://www.dpd.com/de/de/faq/wie-hoch-ist-mein-paket-versichert/) – 520 €
- [HGB § 421](https://www.gesetze-im-internet.de/hgb/__421.html) – destinataire
- [HGB § 438](https://www.gesetze-im-internet.de/hgb/__438.html) – délais
- [DPD : Impressum](https://www.dpd.com/de/de/impressum/) – siège, BNetzA
- [DPD : Zustellung und Zoll](https://www.dpd.com/de/de/support/empfaenger/zustellung-und-zoll/) – douane
- [DPD : Kontakt](https://www.dpd.com/de/de/support/kontakt/) – téléphone, e-mail
- [DPD : App-Support](https://www.dpd.com/de/de/faq/wie-kontaktiere-ich-den-support-ueber-die-app/) – app
- [DPD : Sicherheit](https://www.dpd.com/de/de/sicherheit/) – SMS, .doc
- [DPD : Phishing-Warnung](https://www.dpd.com/de/de/news/warnung-vor-phishing-mails-und-sms-im-namen-von-dpd/) – @dpd.de
- [Your Europe : Livraison](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_fr.htm) – responsabilité UE
- [DPD France : CGV](https://www.dpd.com/fr/fr/conditions-generales-vente/) – 9 et 5 jours
- [DPD France : Contact](https://www.dpd.com/fr/fr/contact/service-clients/) – 09 70 80 85 66
- [DPD France : Fraudes](https://www.dpd.com/fr/fr/alerte-aux-fraudes/) – 33700
- [DPD Belgium : Receiving](https://www.dpd.com/be/en/receiving/) – lun.–sam.
- [DPD Belgium : Parcelshop](https://www.dpd.com/be/en/contact/faq/pickup-parcelshop/) – 5 jours
- [DPD Belgium : Contact](https://www.dpd.com/be/en/contact/) – 070/222 222
- [DPD Suisse : Pickup](https://www.dpd.com/ch/fr/dpd-pickup-location/) – retrait
- [DPD Suisse : Contact](https://www.dpd.com/ch/fr/centre-de-support/votre-demande/) – 058 311 13 13
- [DPD Suisse : Sécurité](https://www.dpd.com/ch/fr/dpd-securite/) – e-mails @dpd.ch
- [OFDF : Réception](https://www.bazg.admin.ch/fr/reception-de-lettres-et-paquets) – import Suisse
- [OFDF : Frais et suivi](https://www.bazg.admin.ch/fr/frais-dedouanement-et-suivi-des-colis) – transporteur
- [UE : Territorial Scope](https://taxation-customs.ec.europa.eu/taxation/vat/vat-directive/how-does-vat-work/territorial-scope_en) – territoires exclus
- [UE : Customs Reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 3 € et 2 €
- [Revenue : Handling Fee](https://www.revenue.ie/en/corporate/press-office/press-releases/2026/pr-092926-handling-fee.aspx) – 2 € par article
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – suivi Peek
:::
