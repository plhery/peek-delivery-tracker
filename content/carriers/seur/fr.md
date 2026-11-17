---
title: Suivi SEUR : numéro, statuts espagnols et points Pickup
description: Suis un colis SEUR depuis l’Espagne vers la France, la Belgique ou la Suisse : format du numéro, sens des statuts espagnols, points Pickup et contact.
slug: suivi-seur
published: 2026-11-17
updated: 2026-11-17
---

SEUR est un transporteur espagnol de colis et d’express du groupe Geopost, comme DPD et [Chronopost](carrier:chronopost). Tu peux suivre ton envoi sans compte dans miSEUR sur seur.com, avec un lien SEUR ou en collant le numéro ci-dessus.

Lorsqu’une boutique espagnole expédie vers la France, la Belgique ou la Suisse, SEUR achemine le colis dans le réseau Geopost : les scans de SEUR s’affichent en espagnol, puis la remise locale relève de [DPD France](carrier:dpd-fr), DPD Belgium ou [DPD Suisse](carrier:dpd-ch).

## Numéro de suivi SEUR : format et où le trouver

Les numéros SEUR comptent le plus souvent 14 ou 21 chiffres, mais trois autres formats fonctionnent (tous ces exemples sont inventés) :

| Exemple (inventé) | Format |
| --- | --- |
| `48291057364928` | 14 chiffres, partagé dans le réseau Geopost |
| `482910573649281736492` | 21 chiffres |
| `4829105` | 7 chiffres (numéro court réutilisé par SEUR) |
| `2026150848291057364928` | 22 chiffres débutant par une date (année, jour, mois) : référence expéditeur |
| `REC482910573649` | `REC` et 12 chiffres : référence d’enlèvement |

Cherche ce code dans l’e-mail d’expédition ou sur ta commande ; sinon, vois [comment trouver son numéro de suivi](guide:find-tracking-number). Comme 14 chiffres conviennent entre autres aussi à [Chronopost](carrier:chronopost), [Ciblex](carrier:ciblex), [DPD France](carrier:dpd-fr), [DPD Allemagne](carrier:dpd-de), [DPD Suisse](carrier:dpd-ch), DPD Belgium, [BRT](carrier:brt) ou Hermes (et 22 chiffres à USPS, Austrian Post ou Estafeta), vérifie le transporteur nommé par le vendeur.

## Statuts du suivi SEUR : sens des messages en espagnol

Le site de SEUR existe en espagnol et en anglais, mais ses scans arrivent en majuscules espagnoles :

| Statut SEUR | Sens pour ton colis |
| --- | --- |
| `EL ENVÍO HA SIDO REGISTRADO.` | Envoi enregistré par l’expéditeur ; SEUR n’a pas encore le colis. |
| `LA RECOGIDA HA SIDO REGISTRADA.` | Enlèvement programmé chez l’expéditeur ; SEUR n’a pas encore le colis. |
| `LA RECOGIDA HA SIDO CONFIRMADA POR EL REPATIDOR.` | Enlèvement sur la tournée du chauffeur (*REPATIDOR* est l’orthographe de SEUR). |
| `RECOGIDA NO REALIZADA: CLIENTE CERRADO O AUSENTE. SE PUEDE REPROGRAMAR UNA NUEVA FECHA DE RECOGIDA.` | Échec d’enlèvement chez l’expéditeur (fermé ou absent), pas chez toi : Peek garde « Étiquette créée ». |
| `LA RECOGIDA HA SIDO REALIZADA.` | Colis récupéré chez l’expéditeur : SEUR l’a en main. |
| `EL ENVÍO HA SIDO DEPOSITADO EN UN PUNTO SEUR PICKUP.` | Déposé par l’expéditeur dans un point SEUR Pickup. |
| `EL ENVÍO HA SIDO RECEPCIONADO EN LAS INSTALACIONES DE ORIGEN DE SEUR. COMIENZA EL TRÁNSITO A DESTINO` | Reçu à l’agence d’origine de SEUR ; départ en transit. |
| `EL ENVÍO ESTÁ EN TRÁNSITO. PRÓXIMAMENTE LLEGARÁ A LAS INSTALACIONES DE DESTINO.` | En transit vers l’agence qui livre ton secteur. |
| `EN ENVÍO HA SIDO RECEPCIONADO EN LAS INSTALACIONES DE DESTINO. PRONTO SALDRÁ A REPARTO.` | Arrivé à l’agence locale (*EN ENVÍO* est l’orthographe de SEUR), pas encore en tournée : Peek affiche « En transit ». |
| `EL ENVÍO HA LLEGADO A LA ADUANA DE DESTINO. INICIAMOS LOS TRÁMITES DE DESPACHO ADUANERO.` | Arrivé à la douane de destination. |
| `INCIDENCIA EN ADUANAS: FALTA FACTURA. CONTACTA CON SEUR DESDE EL ÁREA DE SOPORTE.` | Facture manquante en douane : demande-la au vendeur et joins SEUR via miSEUR. |
| `LA MERCANCÍA HA SIDO DESPACHADA Y PRÓXIMAMENTE SALDRÁ A REPARTO.` | Dédouané ; départ prochain en livraison. |
| `EL ENVÍO SERÁ ENTREGADO EN LA FECHA CONCERTADA. ACTUALIZAREMOS LA SITUACIÓN.` | Date de livraison convenue enregistrée. |
| `EL ENVÍO HA SUFRIDO UN RETRASO Y ES POSIBLE QUE SE DEMORE LA ENTREGA. DISCULPA LAS MOLESTIAS.` | Retard signalé, sans motif ni nouveau délai. |
| `EL ENVÍO ESTÁ EN REPARTO.` | En livraison aujourd’hui (`INTERNACIONAL` pour un envoi transfrontalier). |
| `EL ENVÍO NO SE HA ENTREGADO POR AUSENCIA O CIERRE. SE PUEDE REPROGRAMAR UNA NUEVA ENTREGA.` | Absent ou fermé ; nouvelle livraison programmable. |
| `EL ENVÍO NO SE HA ENTREGADO PORQUE LA DIRECCIÓN DE ENTREGA ES INCORRECTA.` | Adresse incorrecte (ou `INCOMPLETA O ERRÓNEA`) : Peek affiche « Tentative de livraison ». |
| `EL ENVÍO NO SE HA ENTREGADO PORQUE NO ES LA DIRECCIÓN ACTUAL DEL DESTINATARIO.` | Ce n’est plus l’adresse actuelle du destinataire : Peek affiche « Problème signalé ». |
| `NO SE HA PODIDO DEPOSITAR EL ENVÍO EN EL PUNTO SOLICITADO` | Le point Pickup choisi n’a pas pu recevoir le colis. |
| `EL ENVÍO ESTÁ DISPONIBLE PARA RECOGER EN EL PUNTO SEUR PICKUP.` | Prêt à être retiré au point SEUR Pickup. |
| `EL ENVÍO ESTÁ ENTREGADO.` | Livré (`ENTREGADO A UN VECINO.` si remis à un voisin). |

Une fois retiré en boutique Pickup espagnole, le suivi affiche `EL DESTINATARIO HA RETIRADO EL ENVÍO DE LA TIENDA SEUR PICKUP SELECCIONADA.`

## Livraison en France, Belgique, Suisse et Espagne

**En Espagne.** SEUR dispose de milliers de boutiques et consignes Pickup sur seur.com. Après une absence, le passage en relais affiche `LOS DATOS DEL ENVÍO HAN SIDO MODIFICADOS Y SE ENTREGARÁ EN UN PUNTO SEUR PICKUP.` ; si une consigne refuse le dépôt, `EL ENVÍO NO HA SIDO DEPOSITADO EN EL LOCKER SELECCIONADO. SE DEBE ELEGIR OTRO PUNTO SEUR PICKUP.` demande de choisir un autre point.

**Vers la France, la Belgique et la Suisse.** Avec SEUR Classic, Packlink indique un délai estimé de 2 à 4 jours vers les grandes villes européennes, sans créneau garanti hors express horaire :

- **France :** [DPD France](carrier:dpd-fr) garde les colis 9 jours calendaires en Relais Pickup (dès le lendemain de l’arrivée) et 5 jours calendaires en consigne Pickup selon ses CGV.
- **Belgique :** DPD Belgium livre du lundi au samedi (8 h 30–18 h 00) et conserve les colis 5 jours calendaires (dimanche inclus) en Pickup parcelshop.
- **Suisse :** [DPD Suisse](carrier:dpd-ch) remet les colis en DPD Pickup Location contre une pièce d’identité officielle avec photo (ou procuration et pièce d’identité du mandataire).

## Douane : Suisse, Canaries, Ceuta et Melilla

Aucun dédouanement ne s’applique entre l’Espagne péninsulaire (ou les Baléares) et la France métropolitaine ou la Belgique (hors outre-mer français, exclus du territoire TVA ou douanier de l’UE). Deux cas font exception :

- **Suisse :** hors union douanière de l’UE, tout colis venant d’Espagne passe la douane suisse. Selon l’OFDF, TVA à l’importation, droits éventuels et frais de dédouanement du transporteur peuvent s’appliquer ; pour ces frais et le suivi, l’OFDF renvoie au transporteur.
- **Canaries, Ceuta, Melilla ou pays hors UE :** les Canaries sont hors territoire TVA de l’UE, et Ceuta et Melilla hors territoire douanier et TVA de l’UE. Dans l’UE, un droit temporaire de 3 € par article s’applique depuis le 1er juillet 2026 aux envois hors UE jusqu’à 150 €, plus des frais de traitement de l’Union de 2 € par article distinct depuis le 1er novembre 2026 (répercutables par le vendeur ou le livreur). Voir [colis bloqué en douane](guide:customs).

## Colis introuvable, en retard ou marqué livré

**Introuvable.** SEUR renvoie la même erreur pour un envoi tout récent que pour un numéro inconnu : patiente, puis vérifie auprès du vendeur si le colis a été remis.

**En retard ou marqué livré.** Au-delà de la date prévue, consulte [suivi qui ne s’actualise plus](guide:tracking-not-updating). Pour un achat en ligne dans l’UE, Your Europe rappelle que le vendeur reste responsable jusqu’à la réception physique. Si le colis affiche `ENTREGADO A UN VECINO.`, interroge tes voisins, sinon vois [colis marqué livré mais non reçu](guide:delivered-not-received).

## Service client SEUR et contacts locaux

Pour SEUR, passe par l’espace d’aide de miSEUR sur seur.com. Côté Geopost local :

- **France ([DPD France](carrier:dpd-fr)) :** `09 70 80 85 66` (non surtaxé, lun.–ven. 8 h–20 h, sam. 10 h–18 h hors fériés) sur la [page contact DPD France](https://www.dpd.com/fr/fr/contact/service-clients/).
- **Belgique (DPD Belgium) :** `070/222 222` (0,30 €/min, lun.–ven.) sur [dpd.com/be](https://www.dpd.com/be/en/contact/).
- **Suisse ([DPD Suisse](carrier:dpd-ch)) :** `058 311 13 13` (lun.–ven. 8 h–18 h, max. 0,08 CHF/min) sur [dpd.com/ch](https://www.dpd.com/ch/fr/centre-de-support/votre-demande/).

> En février 2021, l’INCIBE a alerté sur de faux SMS imitant SEUR pour réclamer un petit paiement et voler les données de carte : ne suis pas le lien, tape l’adresse de SEUR dans le navigateur et préviens ta banque si tu as saisi ta carte. En France, DPD France rappelle ses seuls domaines officiels (`@dpd.fr`, `@information.dpd.fr`, `@voc.dpd.fr`, `@mail.dpd.fr`, `@dpdgroup.com`, `@network.pickup.fr`) et le signalement au `33700`.

## Suivre un colis SEUR avec Peek

[Peek](/) interroge directement SEUR pour les numéros à 21 chiffres et les codes `REC` (sauf `000` aux chiffres 13 à 15, attribué à Australia Post : touche « Changer » et choisis SEUR sous « Correspond à ce numéro »). Pour 22 chiffres, Peek interroge SEUR et Austrian Post avant les services de suivi et USPS. Pour 14 chiffres, il teste les transporteurs par vagues — en France [Chronopost](carrier:chronopost), [Ciblex](carrier:ciblex), Hermes, DPD Belgium et [DPD Suisse](carrier:dpd-ch), puis DPD Pays-Bas, [DPD Allemagne](carrier:dpd-de), SEUR, [BRT](carrier:brt) et DPD UK. Tu vois « Vérification des services de suivi… » puis « SEUR a ce colis. » si seul SEUR répond ; sinon, utilise « Changer » (ou « Changer de transporteur »), comme pour un code à 7 chiffres que SEUR réutilise. Les liens avec `tracking=` marchent sans code postal.

Peek affiche le statut, l’historique et le poids, sans carte car SEUR ne fournit aucun lieu. Les scans restent en espagnol sauf la remise (`Delivered`, résumée en « Livré ») ; les avis de retard gardent l’étape précédente. Peek écarte le nombre de colis, l’expéditeur, le destinataire, le PIN et le QR code, lit les heures à l’heure de Madrid (« Heures dans ton fuseau horaire : … ») et suit les envois multi-colis via d’autres services.

Peek vérifie le suivi jusqu’à toutes les 10 minutes tant que tu as le colis ouvert ou, le jour de la livraison, avec les notifications activées, et toutes les heures sinon (ainsi qu’après deux jours sans nouveauté, ou 12 heures sur « Étiquette créée » ou « Pris en charge »). En statut « En livraison », il vérifie jusqu’à toutes les 2 minutes dès 6 h 00 (heure de Madrid) tant que SEUR répond directement ; via les seuls services de suivi, toutes les heures, toutes les 3 heures après un jour calme et jusqu’à toutes les 15 minutes le jour de la livraison tant que tu as le colis ouvert ou les notifications activées (« Chaque scan », « Étapes importantes » ou « Livraison seulement »).

## Questions sur le suivi SEUR

### Pourquoi mon numéro SEUR est-il introuvable ?

SEUR donne la même réponse pour un envoi trop récent que pour un numéro inconnu. Attends un jour ouvré, puis vérifie auprès du vendeur si le colis a été remis.

### Combien de temps prend un envoi SEUR depuis l’Espagne ?

Selon Packlink : 24 h ouvrées en Espagne péninsulaire avec SEUR 24, 48 à 72 h avec SEUR Canarias Todo Incluido (72 à 96 h vers La Gomera et El Hierro) et 2 à 4 jours vers les grandes villes européennes avec SEUR Classic.

### Que signifie l’avis de retard de SEUR ?

SEUR signale un retard possible sans motif ni nouveau délai ; Peek garde l’étape précédente jusqu’au prochain scan.

### SEUR fait-il partie de DPD ?

SEUR est la marque espagnole du groupe Geopost, aux côtés de [DPD France](carrier:dpd-fr), DPD Belgium, [DPD Suisse](carrier:dpd-ch) et [Chronopost](carrier:chronopost).

:::sources
- [Geopost : Who we are](https://www.geopost.com/en/about-us/who-we-are/) – SEUR et DPD
- [Geopost : SEUR](https://www.geopost.com/en/about-us/our-companies-and-partners/seur/) – réseau Pickup
- [Packlink : SEUR](https://www.packlink.es/seur/) – délais indicatifs
- [Your Europe : Expédition et livraison](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_fr.htm) – responsabilité vendeur
- [INCIBE : SMS frauduleux SEUR](https://www.incibe.es/ciudadania/avisos/circulan-sms-fraudulentos-que-suplantan-seur-que-no-te-enganen) – alerte phishing
- [DPD France : CGV](https://www.dpd.com/fr/fr/conditions-generales-vente/) – 9 et 5 jours
- [DPD France : Service Clients](https://www.dpd.com/fr/fr/contact/service-clients/) – 09 70 80 85 66
- [DPD France : Alerte aux fraudes](https://www.dpd.com/fr/fr/alerte-aux-fraudes/) – domaines et 33700
- [DPD Belgium : Pakket ontvangen](https://www.dpd.com/be/en/receiving/) – horaires
- [DPD Belgium : Pickup FAQ](https://www.dpd.com/be/en/contact/faq/pickup-parcelshop/) – 5 jours calendaires
- [DPD Belgium : Contact](https://www.dpd.com/be/en/contact/) – 070/222 222
- [DPD Suisse : Pickup Location](https://www.dpd.com/ch/fr/dpd-pickup-location/) – pièce d’identité
- [DPD Suisse : Contact](https://www.dpd.com/ch/fr/centre-de-support/votre-demande/) – 058 311 13 13
- [OFDF : Réception de paquets](https://www.bazg.admin.ch/fr/reception-de-lettres-et-paquets) – douane suisse
- [OFDF : Frais de dédouanement](https://www.bazg.admin.ch/fr/frais-dedouanement-et-suivi-des-colis) – interlocuteur
- [Commission européenne : Territorial Scope](https://taxation-customs.ec.europa.eu/taxation/vat/vat-directive/how-does-vat-work/territorial-scope_en) – Canaries, Ceuta, Melilla
- [Commission européenne : EU Customs Reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 3 € et 2 €
- [Revenue : Union Handling Fee](https://www.revenue.ie/en/corporate/press-office/press-releases/2026/pr-092926-handling-fee.aspx) – répercussion des 2 €
- [Universal Parcel Scraper : SEUR](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/seur) – ce que lit Peek
:::
