---
title: GLS Allemagne suivi colis : statuts, relais et contact
description: Suivre un colis GLS Allemagne (GLS Germany) : numéro ou Track-ID, code postal requis, statuts en anglais, ParcelShops, douane, litiges et service client.
slug: gls-allemagne-suivi-colis
published: 2026-11-14
updated: 2026-11-20
---

GLS Germany (*General Logistics Systems Germany GmbH & Co. OHG*) livre du lundi au vendredi et compte, d'après les chiffres du groupe, 9 000 ParcelShops et 200 consignes Parcelstation en Allemagne. Que tu attendes un colis à une adresse allemande ou une commande expédiée depuis l'Allemagne vers la France, la Belgique, le Luxembourg ou la Suisse, tu peux le suivre sur [la page de suivi du groupe GLS](https://gls-group.com/EU/en/parcel-tracking) avec son numéro de colis et le code postal de livraison, ou coller le numéro dans le champ ci-dessus. Le numéro figure dans l'e-mail d'expédition de la boutique, et l'avis de passage GLS porte un Track-ID.

## Numéro de colis GLS et Track-ID : où les trouver

Regarde d'abord l'e-mail d'expédition du marchand. Le suivi s'active dès que l'expéditeur transmet les données du colis à GLS, et un envoi transfrontalier confié à GLS en Allemagne se suit sur la même page européenne du groupe (puis, selon le pays d'arrivée, aussi chez [GLS France](carrier:gls-fr), [GLS Suisse](carrier:gls-ch) ou [La Poste suisse](carrier:swiss-post)). L'avis de passage laissé par GLS en Allemagne, sur papier ou par e-mail, indique un Track-ID de six lettres qui fonctionne comme le numéro de colis dans l'outil de suivi.

Voici les formats que Peek reconnaît, avec des exemples inventés :

| Numéro (inventé) | Ce que c'est |
| --- | --- |
| `48291057364` | 11 chiffres. Même format chez [GLS Suisse](carrier:gls-ch), [GLS France](carrier:gls-fr), GLS Autriche, GLS Pologne, PostLogistics et d'autres. |
| `000000000009` | 12 chiffres, dont le dernier est une clé de contrôle. Même format chez BRT, [Mondial Relay](carrier:mondial-relay), [Colis Privé](carrier:colis-prive), [DPD France](carrier:dpd-fr) et d'autres. |
| `AB482910` | 8 lettres et chiffres : un Track-ID peut aussi prendre cette forme. Commun à [GLS Suisse](carrier:gls-ch), [GLS France](carrier:gls-fr), GLS Pays-Bas et Loggi. |
| `ABCDEF` | Track-ID de six lettres sur l'avis de passage. Peek le lit comme un numéro de colis ; s'il n'est pas reconnu, utilise le numéro de colis complet. |

Numéro refusé ? Si l'e-mail du vendeur mentionne un autre transporteur, le numéro est le sien : consulte [les formats de numéros de suivi](guide:tracking-number-formats).

## Ce que veulent dire les statuts de suivi GLS

La page internationale du groupe affiche ses étapes en anglais. Les voici dans l'ordre habituel du trajet :

| Ce qu'affiche GLS | Ce que ça veut dire pour toi |
| --- | --- |
| `The parcel data was entered into the GLS IT system…`, `The parcel has not been handed over to GLS.` | GLS a reçu les données électroniques de l'expéditeur, mais pas encore le colis. |
| `The parcel was handed over to GLS.` | GLS a pris le colis en charge. |
| `The parcel has not been released by customs.`, `Customs Consignment via Customs Portal` | Un colis venu hors UE est retenu en douane ; Peek affiche « À la douane ». |
| `The parcel was released by customs.` | La douane a libéré le colis, qui reprend sa route ; Peek affiche « En transit ». |
| `The parcel is in delivery.`, `The parcel is expected to be delivered during the day.` | Chargé dans la camionnette GLS, livraison prévue aujourd'hui. |
| `The parcel is ready for collection at the ParcelShop.` | Disponible au retrait dans un ParcelShop ou une consigne Parcelstation. |
| `The parcel has been delivered.` | Remis à toi ou à un tiers qui l'a accepté pour toi, comme un voisin. |

Entre ces jalons, les scans indiquent l'arrivée ou le départ d'un centre de tri ou d'un dépôt GLS : le colis avance sur le réseau. Si le livreur n'a trouvé personne ou n'a pas pu livrer, Peek affiche « Tentative de livraison ». En Allemagne, GLS tente une seconde fois ou dépose le colis dans un ParcelShop, un dépôt ou une Parcelstation à proximité, en t'indiquant l'adresse sur l'avis de passage ou par e-mail. Faute de retrait dans les délais, ou si la livraison est impossible, le colis repart chez le vendeur et Peek affiche « Retourné à l’expéditeur ». Attention : GLS comptabilise l'arrivée en ParcelShop comme un colis livré, alors qu'il t'y attend encore. Tant que `The parcel has not been handed over to GLS.` reste la dernière ligne, l'expéditeur n'a pas remis le paquet : contacte la boutique.

## Livraison GLS en Allemagne, ParcelShops et consignes

**Jours et délais.** Du lundi au vendredi uniquement, jamais le samedi, selon des délais indicatifs non garantis (seul le service *Guaranteed24Service* prévoit une livraison le jour ouvré suivant en Allemagne continentale, hors îles).

**Boîte aux lettres.** En Allemagne, un petit colis jusqu'à 2 kg, au format maximal DIN C4 et de 5 cm d'épaisseur au plus, peut être glissé directement dans ta boîte aux lettres.

**En cas d'absence.** Le chauffeur peut remettre le colis à une personne présente chez toi ou dans ton entreprise, ou à un voisin du même immeuble ou du bâtiment le plus proche, sauf instruction contraire de ta part ou de l'expéditeur ; l'avis papier ou l'e-mail précise le nom du voisin. Sinon, GLS t'invite à le retirer au ParcelShop, au dépôt ou à la Parcelstation la plus proche. GLS effectue au maximum deux tentatives de livraison.

**Modifier la livraison.** Lorsque GLS propose ses options de redirection, tu peux choisir un autre jour, une autre adresse, un ParcelShop ou un retrait au dépôt ; l'information consommateurs de GLS Germany (janvier 2018) précise que ces options sont gratuites pour le destinataire. Pour un colis expédié depuis l'étranger avec *FlexDeliveryService*, GLS t'informe par e-mail, annonce la date ou le créneau estimé quand c'est possible et permet de modifier la remise ; si tu as choisi un relais dès la commande sur un site étranger (*ShopDeliveryService*), le colis va directement au ParcelShop sans passage à domicile. Une autorisation de dépôt permet aussi au chauffeur de laisser le paquet à l'endroit convenu ; le dépôt t'est ensuite confirmé par carte ou par e-mail.

**ParcelShops.** Selon les conditions générales professionnelles de GLS Germany, un colis déposé en ParcelShop après une absence y est conservé au moins sept jours ouvrables avant d'être retourné à l'expéditeur.

**Parcelstations.** Ce sont les consignes automatiques de GLS en Allemagne ; leur ouverture nécessite ton propre appareil mobile. Tu peux t'opposer au dépôt dans une consigne, pour un envoi précis ou de façon permanente, selon la marche à suivre indiquée sur l'avis de passage.

## Colis GLS en retard, bloqué en douane ou endommagé

Le contrat de transport lie GLS à l'expéditeur : pour un remboursement, adresse-toi toujours à la boutique.

**Suivi figé.** Contacte le vendeur et consulte [pourquoi le suivi ne bouge plus](guide:tracking-not-updating).

**Marqué livré mais introuvable.** Vérifie dans le suivi s'il n'est pas au ParcelShop, puis regarde ta boîte aux lettres et tes e-mails pour voir si un voisin ou un lieu de dépôt est mentionné. Plus de conseils dans [colis indiqué livré mais non reçu](guide:delivered-not-received).

**À la douane.** Aucun droit de douane ne s'applique entre l'Allemagne et la France, la Belgique ou le Luxembourg au sein de l'Union européenne. En revanche, un envoi arrivant d'un pays hors UE (ou livré en Suisse, où GLS peut confier le colis à [La Poste suisse](carrier:swiss-post)) passe en douane : GLS indique que les droits de douane, la TVA à l'importation et généralement des frais de dédouanement doivent être réglés avant la livraison, selon les conditions d'expédition choisies par le vendeur. Dans l'UE, un droit de douane forfaitaire de 3 € par catégorie d'article s'applique depuis le 1er juillet 2026 aux commandes en ligne jusqu'à 150 €, auquel s'ajoutent depuis le 1er novembre 2026 des frais de traitement européens de 2 € par catégorie d'article quelle que soit la valeur, dus par le déclarant en douane. Détails dans [colis bloqué en douane](guide:customs).

**Colis endommagé (droit allemand).** Pour une livraison soumise au droit allemand (Code de commerce, § 438 HGB), un dommage visible doit être signalé dès la remise (le dire au chauffeur suffit), un dommage non apparent sous 7 jours et un retard sous 21 jours, sous forme de texte (e-mail ou courrier) ; l'envoi dans le délai fait foi. Destinataire comme expéditeur peuvent déclarer une perte ou une avarie. Lors d'un achat auprès d'un professionnel, le vendeur supporte le risque du transport sauf si tu as toi-même mandaté un transporteur que la boutique ne proposait pas (Code civil allemand, § 475 BGB) : préviens donc la boutique en premier.

**Médiation en Allemagne.** Un litige de perte ou d'avarie non résolu avec GLS Germany peut être soumis gratuitement à la *Schlichtungsstelle Post* de la Bundesnetzagentur, que tu sois expéditeur ou destinataire, pour un montant compris entre 10 € et 2 500 € ; GLS est tenu d'y participer lorsqu'un consommateur la saisit.

**Droit de rétractation (*Widerruf*).** Ne pas aller chercher un colis ne vaut pas rétractation. Pour un achat à distance ou hors établissement auprès d'un professionnel, tu disposes en règle générale de 14 jours à compter de la réception du bien pour déclarer clairement ta rétractation au vendeur, sans avoir à te justifier (Code civil allemand, §§ 355 et 356 BGB).

## Service client GLS Allemagne et contacts utiles

Pour une commande en ligne, ton premier interlocuteur reste le marchand. Les coordonnées nationales de GLS Germany figurent sur son site allemand `gls-pakete.de`. Pour toute question sur le statut d'un colis, le groupe GLS demande de contacter le service client GLS de ton pays (dont le numéro figure sur le site GLS national, par exemple chez [GLS France](carrier:gls-fr)) ; le [formulaire de contact du groupe GLS](https://gls-group.com/GROUP/en/contact-form/) accepte aussi les demandes des destinataires. Si tu as expédié toi-même un colis via GLS-Pakete.de ou l'application allemande, ses conditions d'août 2023 indiquent `versand-service@gls-germany.com` et le `+49 6677 646907040`.

> Le groupe GLS met en garde contre les faux e-mails, SMS et sites usurpant son nom. Son site principal est `gls-group.com` : seuls ce domaine et les sites nationaux vers lesquels il renvoie, comme `gls-pakete.de`, sont authentiques. GLS ne demande jamais par e-mail de payer une marchandise par carte, virement ou paiement en ligne. Pour un colis hors UE, GLS ou un agent agréé peut toutefois demander par e-mail, SMS ou courrier le règlement des droits et taxes. Les envois contre remboursement (*GLS Cash Service*) se règlent uniquement en espèces au chauffeur, et GLS déconseille de payer un contre-remboursement que tu n'attendais pas, pour toi ou pour un voisin. Transfère tout e-mail suspect en pièce jointe à `security@gls-group.com`.

## Suivre un colis GLS Allemagne avec Peek

[Peek](/) interroge les transporteurs compatibles avec les formats de numéros ci-dessus. GLS répond aussi bien pour les colis allemands que suisses, et Peek affiche « GLS Germany a ce colis » — ou [GLS France](carrier:gls-fr) lorsque le numéro est suivi sur le réseau français sans code postal. En Suisse ou au Liechtenstein (selon la région de ton iPhone ou ta connexion web), Peek propose par défaut [GLS Suisse](carrier:gls-ch) avec un code postal à 4 chiffres : si le colis est livré en Allemagne, appuie sur « Changer » et choisis GLS Germany. Coller un lien `gls-pakete.de`, ou un lien `gls-group` contenant `/DE/`, `/EU/`, `/GROUP/`, `/BE/` ou `/LU/`, sélectionne aussi directement GLS Germany.

Comme GLS exige le code postal du destinataire pour livrer le détail des événements, le champ « Code postal de livraison » est requis, et si GLS ne trouve pas le colis avec le code saisi (« GLS Germany n’a pas trouvé ce colis avec le code postal … »), tu peux le corriger via « Modifier le code postal ». Tous les formats de codes postaux nationaux sont acceptés.

Tu vois alors le statut, les étapes telles que GLS les écrit (en anglais), la ville et le pays de chaque scan sur une carte, le jour prévu, le poids, l'heure de remise et, en cas de dépôt en relais, le nom et l'adresse du ParcelShop sous le statut « Prêt à être retiré ». Ton nom, ton adresse, tes coordonnées, la signature et la référence expéditeur sont écartés. Les heures sont lues à l'heure allemande et affichées dans le fuseau horaire de ton appareil ; sur peektracker.com et dans les applications iPhone et Android, une ligne sous l'historique le nomme (« Heures dans ton fuseau horaire : … »). Peek vérifie GLS environ une fois par heure, jour de livraison compris (toutes les 4 heures après un échec), et rafraîchir manuellement n'accélère pas ce délai. Seuls les colis remis à [La Poste suisse](carrier:swiss-post) basculent automatiquement vers un second transporteur. Quand un autre transporteur prend le relais pour la livraison, Peek suit le rythme de celui qui livre le colis. Notifications au choix : « Chaque scan », « Étapes importantes » ou « Livraison seulement ».

## Questions fréquentes sur le suivi GLS Allemagne

### Où se trouve mon colis GLS ?

Le suivi GLS te le montre dès que tu renseignes le numéro et le code postal de livraison : `The parcel is in delivery.` signifie qu'il est dans la camionnette du livreur aujourd'hui, et `The parcel is ready for collection at the ParcelShop.` qu'il t'attend en relais.

### Qu'est-ce que le Track-ID de GLS ?

C'est le code de six lettres inscrit sur l'avis de passage papier ou électronique laissé par GLS en Allemagne après une tentative de livraison. Il s'utilise exactement comme le numéro de colis sur la page de suivi.

### Quel est le délai de livraison de GLS depuis l'Allemagne ?

GLS indique des délais estimatifs sans garantie (hors *Guaranteed24Service* le jour ouvré suivant en Allemagne continentale). Selon le groupe GLS, un colis national arrive généralement en moins de 24 heures et un colis en Europe entre 24 et 96 heures.

### Combien de temps GLS garde-t-il un colis en ParcelShop ?

En Allemagne, après un passage infructueux à domicile, le ParcelShop, le dépôt ou la consigne Parcelstation conserve le colis au moins sept jours ouvrables avant de le renvoyer à l'expéditeur.

### GLS Germany est-il fiable ?

Oui : GLS Germany est la société *General Logistics Systems Germany GmbH & Co. OHG*, établie à Neuenstein en Hesse. Des messages frauduleux circulent toutefois sous son nom : vérifie toujours que les liens renvoient vers `gls-group.com` ou ses sites nationaux officiels.

:::sources
- [GLS: Parcel Tracking](https://gls-group.com/EU/en/parcel-tracking) – page de suivi européenne, code postal et Track-ID sur l'avis de passage
- [GLS: About us](https://gls-group.com/GROUP/en/about-us/) – 9 000 ParcelShops et 200 consignes en Allemagne
- [GLS: Parcel, Logistics & Express](https://gls-group.com/GROUP/en/our-business/parcels/) – délais nationaux (< 24 h) et européens (24 à 96 h), droits, taxes et frais de dédouanement avant livraison
- [GLS: B2C Services](https://gls-group.com/GROUP/en/our-business/cross-border-services/b2c/) – FlexDeliveryService et ShopDeliveryService sur les envois internationaux
- [GLS: Contact](https://gls-group.com/GROUP/en/contact-form/) – formulaire de contact du groupe et renvoi vers le service client national
- [GLS Germany: General Terms and Conditions for business customers](https://gls-group.com/DE/media/downloads/AGB_GLS_Germany_2025-01_EN_2024-10-25_FINAL-WEB.pdf) – pas de livraison le samedi, petits colis en boîte aux lettres, voisinage, deux tentatives, sept jours ouvrables en ParcelShop, consignes Parcelstation, Guaranteed24Service et frais de douane répartis selon l'envoi choisi
- [GLS Germany: Terms for GLS-Pakete.de and the app](https://gls-group.com/DE/media/downloads/AGB_GLS-paketeApp_EN_2023-08-21.pdf) – raison sociale à Neuenstein, avis de passage, autorisation de dépôt, Schlichtungsstelle Post et contact expéditeurs
- [GLS Germany: Consumer information on delivery options](https://gls-group.com/DE/media/downloads/180130_Verbraucherinformation_Umverfuegung_DE_EN.pdf) – gratuité des options de redirection pour les destinataires
- [GLS: Security advice](https://gls-group.com/GROUP/en/security-advice/) – sites officiels, fraude, droits hors UE, GLS Cash Service et signalement à `security@gls-group.com`
- [La Poste : Importation](https://www.post.ch/fr/solutions-commerciales/exportation-importation-et-dedouanement/importation) – offre de La Poste pour les colis d'importation GLS : traitement dans le réseau postal suisse et taxes réparties entre expéditeur et destinataire
- [Bundesnetzagentur: Schlichtungsstelle Post](https://www.bundesnetzagentur.de/DE/Vportal/Schlichtung/Schlichtung_Post/start.html) – médiation postale gratuite entre 10 € et 2 500 €
- [Gesetze im Internet: § 438 HGB](https://www.gesetze-im-internet.de/hgb/__438.html) – délais de déclaration d'avarie (livraison / 7 jours) et de retard (21 jours)
- [Gesetze im Internet: § 475 BGB](https://www.gesetze-im-internet.de/bgb/__475.html) – transfert du risque lors d'une vente au consommateur
- [Gesetze im Internet: § 355 BGB](https://www.gesetze-im-internet.de/bgb/__355.html) – délai de rétractation de 14 jours sans motif auprès du vendeur
- [Gesetze im Internet: § 356 BGB](https://www.gesetze-im-internet.de/bgb/__356.html) – départ du délai de rétractation à la réception du bien
- [Commission européenne : réforme douanière de l'UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – droit de douane de 3 € jusqu'à 150 € depuis juillet 2026 et frais de traitement de 2 € depuis novembre 2026
- [Commission européenne : lignes directrices sur le droit de 3 € et les frais de traitement (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – application des 2 € par catégorie d'article quelle que soit la valeur
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – détection des formats GLS, gestion du code postal, bascule vers La Poste suisse et vérification horaire
:::
