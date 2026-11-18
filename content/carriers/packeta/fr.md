---
title: Suivi Packeta : numéro Z, statuts, relais et contact
description: Suivre un colis Packeta (Zásilkovna) : format du code Z à 10 chiffres, sens des statuts en anglais, retrait en point relais, douane, litiges et contacts.
slug: packeta-suivi-colis
published: 2026-11-18
updated: 2026-11-18
---

Packeta est la marque internationale du groupe logistique tchèque derrière Zásilkovna et expédie vers 33 pays, dont la France, la Belgique, le Luxembourg, la Suisse et les États-Unis. Tu peux suivre un envoi sur [la page de suivi Packeta](https://tracking.packeta.com/en/), dans l'application mobile Packeta ou directement dans le champ ci-dessus. Le numéro se compose de la lettre `Z` suivie de dix chiffres, indiqué dans l'e-mail ou le SMS de Packeta ainsi que dans l'application.

## Numéro de suivi Packeta : reconnaître le code Z

La page de suivi Packeta accepte un format unique : la lettre `Z` suivie de 10 chiffres. Voici comment distinguer un numéro Packeta avec des chiffres inventés :

| Format (inventé) | Ce que c'est |
| --- | --- |
| `Z4829105736` | Numéro de suivi Packeta (`Z` et dix chiffres). |
| `4829105736` | Les dix chiffres sans le `Z` : cette forme correspond aussi à [DHL Express](carrier:dhl-express), [Relais Colis](carrier:relais-colis), [Mondial Relay](carrier:mondial-relay) et d'autres ; ajoute le `Z` si tu suis ton colis sur le site Packeta. |
| `Z482910573` | Neuf chiffres après `Z` : ce n'est pas Packeta, mais le format d'Australia Post. |
| `Z48291057364` | Onze chiffres après `Z` : ce n'est pas Packeta, mais le format de [Colis Privé](carrier:colis-prive). |

Ne confonds pas ce numéro avec le **code de retrait** : Packeta t'envoie ce code séparé par SMS, par e-mail ou dans l'application une fois le colis arrivé au point relais, et il sert uniquement à retirer le paquet, pas à le suivre. Si l'e-mail de la boutique ne contient aucun code `Z`, demande-le au vendeur. Plus d'astuces dans [comment trouver ton numéro de suivi](guide:find-tracking-number).

## Statuts de suivi Packeta : ce qu'ils veulent dire

L'interface internationale de Packeta affiche les étapes du parcours en anglais ; dans le tableau ci-dessous, « … » signale l'extrait d'une phrase plus longue :

| Statut affiché par Packeta | Ce que cela signifie pour toi |
| --- | --- |
| `We are aware of your parcel and are waiting for the sender…` | La boutique a enregistré l'envoi, mais ne l'a pas encore remis à Packeta. |
| `…assigned a tracking number…` | L'étiquette est créée ; le colis n'a pas encore bougé. |
| `…successfully received the parcel for transport…` | Packeta a pris le colis en charge. |
| `The parcel is currently on its way to the depot.` | Le colis roule vers un dépôt de tri. |
| `…arrived at the depot…` | Le colis est en cours de tri dans un dépôt Packeta. |
| `The parcel has arrived at the micro-depot.` | Arrivé dans un petit dépôt local ; la ville et la rue suivent la phrase. |
| `The parcel has been handed over to the carrier…` | Remis au transporteur chargé de la livraison finale, le plus souvent un partenaire national hors de Tchéquie. |
| `…on its way to you…` | En cours de livraison vers toi aujourd'hui. |
| `The parcel is ready for pickup…` | Disponible au point relais ou dans une consigne Z-BOX ; prépare ton code de retrait. |
| `The parcel is with you…` | Colis retiré ou livré. |
| `…investigating the status of the parcel…` | Un incident s'est produit et Packeta vérifie la situation du colis. |
| `We have returned the parcel back to the sender.` | Le colis est revenu chez l'expéditeur. |

En haut de sa page, Packeta affiche aussi un état général : `The package has been delivered` (livré ou retiré), `Return (on the way back)` (en cours de retour vers l'expéditeur) ou `Returned to sender` (retourné à l'expéditeur). La mention `The parcel has been handed over to the carrier…` indique qu'une autre entreprise a pris le relais : un transporteur partenaire dans ton pays ou, en Roumanie, FAN Courier (consignes FANbox) ou Poșta Română. Le dernier jour de garde chez Packeta, le suivi affiche `Last day to pick up your parcel.` avec un bouton pour prolonger le délai, ou `Your parcel will wait for you only today!` lorsqu'aucune prolongation supplémentaire n'est possible.

`Parcel not found` signifie que Packeta ne connaît pas ce numéro ou n'affiche plus un envoi ancien : vérifie la lettre `Z` et les dix chiffres.

## Points de retrait, consignes Z-BOX et livraison à domicile

**Garde en point relais Packeta.** Sur ses points relais, Packeta annonce 7 jours de garde avec le code reçu par SMS ou e-mail, prolongeables gratuitement deux fois sur le suivi ou dans l'application (sauf réduction vers Noël). En Tchéquie, cela fait 7 + 7 + 7 jours (21 maximum), et une pièce d'identité à ton nom permet aussi le retrait. En Pologne, un SMS à Packeta prolonge gratuitement jusqu'à 21 jours. En Hongrie, les conditions prévoient 5 jours (7 max.) du 1er octobre au 20 janvier et 7 jours (21 max.) le reste de l'année ; en Roumanie, elles fixent 7 jours (4 + 1 sur demande du 1er octobre au 20 janvier), tandis que le site roumain annonce une prolongation gratuite par SMS jusqu'à 21 jours. Un tiers muni de ton code peut retirer le colis.

**Consignes Z-BOX.** Packeta exploite ses consignes Z-BOX 24 h/24 en Tchéquie, Slovaquie et Roumanie via Bluetooth depuis l'application (en Tchéquie, un code clavier à six chiffres fonctionne aussi ; en Hongrie, les Z-BOX ont pris le nom FOXPOST en 2026 et s'ouvrent avec l'application FOXPOST). En Tchéquie, la garde dure 2 jours calendaires, plus 1 jour sur prolongation ; en Roumanie, les conditions indiquent 48 heures et la FAQ 2 jours plus 1. Packeta n'a pas de Z-BOX en France, Belgique, Luxembourg ni Suisse.

**Livraison à domicile et partenaires étrangers.** Selon `packeta.com`, Packeta livre avec ses chauffeurs en Tchéquie et Slovaquie (la FAQ `packeta.de` ne cite ses chauffeurs qu'en Tchéquie et des dizaines de partenaires ailleurs). Vers la France, la Belgique, le Luxembourg ou la Suisse, le colis passe du dépôt Packeta à un transporteur partenaire local, qui livre à domicile ou en relais selon ses propres règles de garde et d'avis. En Tchéquie, le chauffeur appelle deux fois, fait un seul passage les jours ouvrés et dépose l'envoi non remis au relais disponible le plus proche dès le jour même (à Prague, Brno et Ostrava, Zásilkovna livre aussi les Z-BOX et certains relais le samedi). Non retiré à temps en Tchéquie, le colis repart automatiquement chez l'expéditeur ; voir [colis non réclamé](guide:uncollected-parcel).

## Colis Packeta bloqué, en douane, endommagé ou retourné

**Suivi immobile.** En Tchéquie, après plus de 24 heures sans mouvement, Zásilkovna invite à écrire via son formulaire (rubrique « Kde je moje zásilka? ») en décrivant le contenu. Ayant réservé l'envoi, la boutique peut aussi relancer Packeta ou le transporteur final. Consulte [pourquoi le suivi ne bouge plus](guide:tracking-not-updating).

**Douane selon le pays de livraison.** Un colis expédié d'un pays de l'UE vers la France métropolitaine, la Belgique ou le Luxembourg circule dans le territoire douanier et TVA de l'UE sans droits de douane (les départements d'outre-mer français — Guadeloupe, Guyane, Martinique, La Réunion et Mayotte — font partie du territoire douanier de l'UE mais restent hors du territoire TVA). Pour un achat en ligne arrivant d'un pays hors UE dans l'UE, un droit de douane de 3 € par catégorie d'article s'applique jusqu'à 150 € depuis le 1er juillet 2026, plus des frais de traitement européens de 2 € par catégorie d'article quelle que soit la valeur depuis le 1er novembre 2026, dus par le déclarant en douane, outre la TVA et d'éventuels frais du transporteur final (quand [bpost](carrier:bpost) livre le colis en Belgique, il envoie une demande de paiement par e-mail, SMS ou courrier, payable dans My bpost, sur Track & Trace ou en Bureau de Poste). En Suisse, l'OFDF applique la TVA à l'importation (8,1 %, ou 2,6 % au taux réduit, non perçue en dessous de 5 CHF d'impôt) et le transporteur facture ses frais de dédouanement. Voir [colis bloqué en douane](guide:customs).

**Colis perdu.** L'expéditeur dépose en principe la réclamation de perte : préviens d'abord la boutique. Selon les conditions Zásilkovna pour les marchands, un colis non livré se signale sous 20 jours après dépôt et se réclame sous 3 mois. Le destinataire peut toutefois réclamer en Slovaquie, en Roumanie (sous 6 mois après dépôt selon les conditions générales, même si une FAQ renvoie à l'expéditeur) et en Hongrie (avec cession écrite de l'expéditeur ou confirmation de commande prouvant le paiement intégral). S'il est marqué livré sans être là, lis [colis indiqué livré mais non reçu](guide:delivered-not-received).

**Colis endommagé ou retourné.** Comme destinataire, déclare une avarie sur `claims.packeta.com/en` (« I am the recipient ») : en Tchéquie sous cinq jours ouvrés après réception selon les conditions Zásilkovna ; en Slovaquie dès le jour ouvré suivant pour un dommage caché ; en Hongrie sous 3 jours ouvrés pour un vice caché (dommage visible à constater à la remise) ; en Roumanie sous les 6 mois ci-dessus. Packeta répond sous 30 jours, et les relais ne prennent pas de réclamation (en Pologne, Packeta renvoie les destinataires à la boutique). En cas de retour à l'expéditeur, demande renvoi ou remboursement au vendeur ; voir [colis retourné à l'expéditeur](guide:returned-to-sender).

## Service client Packeta et numéros de contact

Packeta publie des lignes téléphoniques en Europe centrale et en Allemagne, mais pas de numéro local dédié pour la France, la Belgique, le Luxembourg ou la Suisse (où la boutique, ou le transporteur partenaire après remise, reste ton contact) :

- **Tchéquie (Zásilkovna) :** `+420 216 216 516`, lun.–ven. 8:00–18:00 ; assistant virtuel ZET 24 h/24 hors horaires et formulaire web.
- **Slovaquie :** `+421 221 201 135`, jours ouvrés 8:00–18:00 (robot vocal soir et week-end) et formulaire.
- **Pologne :** `+48 334 457 020`, lun.–ven. 8:00–17:00, et formulaires sur [packeta.pl](https://www.packeta.pl/kontakt).
- **Hongrie :** `+36 1 999 0369`, lun., mar., mer. et ven. 8:00–18:00, jeu. 8:00–20:00.
- **Roumanie :** `+40 316 300 402`, lun.–ven. 9:00–17:00, et formulaire.
- **Allemagne :** `+49 341 238 799 80`, lun.–jeu. 8:00–16:30 et ven. 8:00–15:00, ou `Info@packeta.de`.

> **Faux SMS selon le pays et le transporteur.** En Tchéquie, Zásilkovna alerte sur les SMS frauduleux : ses vrais messages affichent « Zásilkovna » comme expéditeur, et face à un SMS suspect elle recommande de ne pas répondre, n'ouvrir aucun lien, supprimer le message et ne jamais transmettre tes codes. En France, Cybermalveillance.gouv.fr rappelle que [La Poste](carrier:la-poste) ne demande jamais par SMS ni e-mail de payer pour récupérer un colis ; quand un partenaire livre en France, Belgique ou Suisse, vérifie toute demande de frais sur le site ou l'application officielle de ce transporteur. Voir [reconnaître les SMS d'arnaque au colis](guide:delivery-scam-texts).

## Suivre un colis Packeta avec Peek

[Peek](/) reconnaît aussitôt `Z` suivi de dix chiffres comme Packeta. Sans `Z`, dix chiffres conviennent à plusieurs transporteurs : Peek interroge Packeta (avec DHL eCommerce Espagne et BOX NOW) si le premier chiffre n'est pas `0` et que les chiffres sont groupés d'un bloc ou en `3-4-3`, puis garde celui qui connaît l'envoi ; sinon appuie sur « Changer » et choisis Packeta. Les liens `tracking.packeta.com` (et `packeta.com` avec `id=`) ainsi que ceux de `zasilkovna.cz` contenant le code `Z` fonctionnent sans code postal.

Peek lit Packeta en anglais et montre ses phrases telles quelles (avec les étapes traduites comme « Retourné à l’expéditeur ») : statut global, 20 derniers scans, boutique expéditrice, heure de livraison et adresse du relais ou de la Z-BOX quand le colis s'y rend (absente à domicile ou en retour). Nom, adresse, téléphone et signature sont écartés.

Les scans citant un dépôt ou relais l'indiquent comme lieu, et ceux avec une ville figurent sur la carte. Packeta ne donne pas de date estimée. Les heures, lues sur Prague, sont converties dans ton fuseau (décalage possible d'une heure sur les dépôts roumains) ; sur `peektracker.com` et sur iPhone, une mention sous l'historique le rappelle (« Heures dans ton fuseau horaire : … »). Numéro inconnu et numéro expiré donnent la même réponse. Si un scan renvoie au suivi d'un partenaire connu, Peek le nomme comme livreur et, s'il le suit directement, bascule dessus dès qu'une progression fraîche apparaît, à son propre rythme. Tant que Packeta répond, Peek vérifie jusqu'à toutes les 10 minutes tant que la page est ouverte ou le jour de livraison avec alertes actives, et chaque heure sinon ; après deux jours calmes (ou 12 heures sur « Étiquette créée » ou « Pris en charge »), toutes les heures (toutes les 3 heures sans compte sans consultation récente ni alerte). Alertes : « Chaque scan », « Étapes importantes » ou « Livraison seulement ».

## Questions fréquentes sur le suivi Packeta

### Où se trouve mon colis Packeta ?

La dernière ligne l'indique : `…on its way to you…` signifie en tournée aujourd'hui, `The parcel is ready for pickup…` disponible au retrait et `The parcel has been handed over to the carrier…` remis au transporteur partenaire de ton pays.

### Packeta est-il un transporteur fiable ?

Oui : Packeta est la marque internationale du groupe tchèque Zásilkovna et dessert 33 pays via des partenaires hors d'Europe centrale. En Tchéquie, les vrais SMS Zásilkovna affichent « Zásilkovna » comme expéditeur.

### Packeta et Foxpost sont-ils le même service ?

Ils fusionnent en Hongrie : FOXPOST exploite `packeta.hu`, les Z-BOX hongroises s'appellent FOXPOST et l'application FOXPOST y remplace celle de Packeta. Ailleurs, Packeta garde son code `Z` à dix chiffres.

### Faut-il un code postal pour suivre un colis Packeta ?

Non : le code `Z` à dix chiffres suffit sur `tracking.packeta.com` comme dans Peek. Le code de retrait reçu par SMS ou e-mail sert uniquement au retrait en relais.

:::sources
- [Packeta: Parcel tracking](https://tracking.packeta.com/en/) – code Z à 10 chiffres, dernier jour, colis introuvable et prolongation
- [Packeta: Home delivery, pick-up points and Z-BOXes](https://www.packeta.com/delivery-options) – garde de 7 jours, deux prolongations, code de retrait et Z-BOX
- [Packeta: Home Delivery](https://www.packeta.com/home-delivery) – chauffeurs propres en Tchéquie et Slovaquie
- [Packeta.de: Wohin liefern wir?](https://www.packeta.de/wohin-liefern-wir) – 33 pays desservis, partenaires étrangers et contact allemand
- [Packeta.de: Support, häufige Fragen](https://www.packeta.de/support/suche) – chauffeurs propres en Tchéquie et partenaires ailleurs
- [Zásilkovna: Příjem zásilek](https://www.zasilkovna.cz/prijem) – garde tchèque (21 jours max.), Z-BOX (2 + 1 jours), pièce d'identité et retour
- [Packeta.sk: Our pick-up points](https://www.packeta.sk/en/our-pickup-points) – code de retrait et retrait par un tiers
- [Zásilkovna: Glossary of terms, Packeta](https://www.zasilkovna.cz/en/glossary-of-terms/packeta) – lien Packeta/Zásilkovna, téléphone tchèque et ZET
- [Zásilkovna: Fraudulent SMS](https://www.zasilkovna.cz/en/cybersecurity/sms) – expéditeur « Zásilkovna » et consignes anti-phishing en Tchéquie
- [Packeta: General terms and conditions](https://www.packeta.com/general-terms-conditions) – réclamations, réponse sous 30 jours et points relais
- [Zásilkovna: Obchodní podmínky (PDF)](https://files.packeta.com/web/files/VOP_Zasilkovna.pdf) – délais de 20 jours, 3 mois et 5 jours ouvrés
- [Zásilkovna: Zákaznická podpora](https://www.zasilkovna.cz/zakaznicky-servis/vyhledavani) – une tentative, formulaire après 24 h et code Z-BOX à six chiffres
- [Zásilkovna: Doručení domů](https://www.zasilkovna.cz/domu) – deux appels du chauffeur les jours ouvrés
- [Zásilkovna: Doručujeme i v sobotu](https://www.zasilkovna.cz/blog/v-praze-brne-a-ostrave-dorucujeme-i-v-sobotu) – livraison du samedi à Prague, Brno et Ostrava
- [Packeta blog: How to claim a parcel](https://blog.packeta.com/how-to-claim-a-parcel/) – réclamant et `claims.packeta.com`
- [Packeta.pl: Kontakt i reklamacje](https://www.packeta.pl/kontakt) – téléphone polonais, horaires et formulaires
- [Packeta.pl: Jak dokładnie działa odbiór paczek](https://www.packeta.pl/doreczanie) – prolongation par SMS jusqu'à 21 jours en Pologne
- [Packeta.pl: Reklamacje przesyłek i usług](https://www.packeta.pl/reklamacje) – renvoi vers la boutique en Pologne
- [Packeta.pl: Z-BOX](https://www.packeta.pl/zbox) – pays équipés de Z-BOX et ouverture Bluetooth 24 h/24
- [Packeta.hu: Kapcsolat](https://www.packeta.hu/kapcsolat) – téléphone hongrois et horaires
- [Packeta Hungary: Rólunk](https://www.packeta.hu/rolunk) – rapprochement Packeta et FOXPOST en Hongrie
- [Packeta Hungary: A Packeta Z-BOX-ok hamarosan átalakulnak FOXPOST Z-BOX-okká](https://www.packeta.hu/blog/packeta-z-box-atalakulas-foxpost-zbox) – Z-BOX hongroises renommées FOXPOST
- [Packeta Hungary: Megérkezett: adj fel csomagot a FOXPOST applikációval](https://www.packeta.hu/blog/megerkezett-adj-fel-csomagot-a-foxpost-applikacioval) – application FOXPOST en Hongrie
- [Packeta Hungary: Általános Szerződési Feltételek (PDF)](https://files.packeta.com/web/files/packetahu/2026/FoxPost_ASZF-20260826.pdf) – garde, réclamation destinataire et avarie en Hongrie
- [Packeta.ro: Contact](https://www.packeta.ro/contact) – téléphone roumain et horaires
- [Packeta.ro: Modalități de primire colet](https://www.packeta.ro/optiuni-livrare) – prolongation SMS jusqu'à 21 jours en Roumanie
- [Packeta.ro: Întrebări frecvente](https://www.packeta.ro/serviciu-clienti/cautare) – Z-BOX 2 + 1 jours et FAQ réclamations en Roumanie
- [Packeta.ro: Condiții generale privind furnizarea serviciilor poștale (PDF)](https://files.packeta.com/web/files/Condi%C8%9Bii-generale-privind-furnizarea-serviciilor-po%C8%99tale_Packeta-Romania_31.10.2024.pdf) – garde et réclamation sous 6 mois en Roumanie
- [Packeta.sk: Kontakty](https://www.packeta.sk/kontakty) – téléphone slovaque, horaires et robot vocal
- [Packeta.sk: Reklamačný poriadok (PDF)](https://files.packeta.com/web/files/SK_Reklamacny_poriadok_20_10_2026.pdf) – réclamation destinataire et avarie cachée en Slovaquie
- [Cybermalveillance.gouv.fr : escroqueries à la livraison de colis](https://www.cybermalveillance.gouv.fr/tous-nos-contenus/actualites/escroqueries-livraison-colis) – La Poste ne demande jamais de paiement par SMS ni e-mail
- [bpost : achats hors de l'Union européenne](https://www.bpost.be/fr/shopping-hors-ue) – demande par e-mail, SMS ou courrier et paiement My bpost, Track & Trace ou Bureau de Poste
- [OFDF : réception de lettres et de paquets](https://www.bazg.admin.ch/fr/reception-de-lettres-et-paquets) – TVA suisse (8,1 % / 2,6 %) et franchise jusqu'à 5 CHF d'impôt
- [OFDF : frais de dédouanement et suivi des colis](https://www.bazg.admin.ch/fr/frais-dedouanement-et-suivi-des-colis) – frais de dédouanement facturés par le transporteur en Suisse
- [Commission européenne : champ d'application territorial de la TVA et des douanes](https://taxation-customs.ec.europa.eu/taxation/vat/vat-directive/how-does-vat-work/territorial-scope_en) – départements d'outre-mer français
- [Commission européenne : réforme douanière de l'UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – droit de 3 € dès juillet 2026 et frais de 2 € dès novembre 2026
- [Commission européenne : lignes directrices sur le droit de 3 € et les frais de traitement (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – frais de 2 € par catégorie d'article dus par le déclarant
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – détection Packeta, fuseau de Prague, partenaires et cadence
:::
