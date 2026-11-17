---
title: Suivi Poste Italiane : codes 1UW et 3UW, statuts et délais
description: Suivre un colis Poste Italiane depuis la France, la Belgique ou la Suisse : codes 1UW, 3UW, 8UW et 5P, sens des statuts italiens, retrait et contact.
slug: suivi-poste-italiane
published: 2026-11-17
updated: 2026-11-17
---

Poste Italiane est l'opérateur postal italien et livre aussi, via ses réseaux Crono et SDA, les achats sur des boutiques italiennes, Vinted ou eBay. Suis ton envoi dans « Cerca Spedizioni » sur poste.it (ancien Dove Quando), dans l'app Poste Italiane ou en collant son code ci-dessus. Vers la France, la Belgique ou la Suisse, poste.it montre le trajet jusqu'au centre d'échange international ; sur un code postal finissant par `IT`, la poste de ton pays prend ensuite le relais.

## Numéro de suivi Poste Italiane : où le trouver et à quoi il ressemble

Poste l'appelle *codice spedizione*. Il figure sous le code-barres du reçu ou de la lettre de voiture (*lettera di vettura*) : saisis-le sans espaces ni tirets. En ligne, regarde l'e-mail de la boutique (ou le message Scegli Tu de Poste) ; en Italie, l'avis de passage (*avviso di giacenza*) porte le numéro du recommandé, le bureau de retrait et, le cas échéant, l'accès à Ritiro Digitale. Le « Codice Prenotazione Ritiro » (`CP` suivi de chiffres) ne sert qu'à un enlèvement expéditeur, pas au suivi. Rien reçu ? Voir [où trouver son numéro de suivi](guide:find-tracking-number).

Tous les exemples ci-dessous sont inventés :

| Numéro (inventé) | Ce que c'est |
| --- | --- |
| `RA00000000000` | `RA` et 11 chiffres : code d'envoi et recommandé. |
| `3UW0000000000` | `1UW`, `3UW` ou `8UW` et 10 lettres/chiffres : colis Poste Delivery Business. |
| `5PA0000000000` | `5P` et 11 lettres/chiffres : colis Crono. |
| `JF00000000000` | `EF`, `EG`, `GF`, `JF`, `JG` ou `LF` et 11 chiffres : Poste Delivery Web et MyPoste. |
| `JJITA0000000000000` | `JJITA` et 13 lettres/chiffres : code italien au format ISO. |
| `2IMA0000000000` | `2IMA` et 10 chiffres (ou `2IVC`, 4 chiffres, lettre/chiffre, 5 chiffres) : étiquette Posta1. |
| `000000A000000` | Chiffres et lettres mêlés (6 chiffres, lettre, 6 chiffres, ou début `1C`, `3C`, `9C`, `F`, `D`) : style SDA et Crono. |
| `050000201242` | 12 chiffres avec clé valide : l'exemple donné sur poste.it pour recommandés, assurés, actes judiciaires et colis. |
| `RR000000005IT` | Deux lettres, neuf chiffres et `IT` (`RR…IT`, `CW…IT`, `ZC…IT`) : envoi international parti d'Italie. |

Certains formats sont partagés : un code `5P` suivi de onze chiffres passant la clé Colissimo peut relever de [La Poste](carrier:la-poste) ; les 12 chiffres existent chez BRT ou [Colis Privé](carrier:colis-prive), et `000A00000000B`, `9C0000I000000` ou `9C0000J000000` chez [AliExpress / Cainiao](carrier:cainiao). Voir [reconnaître un numéro de suivi](guide:tracking-number-formats).

**Ce qui est suivi :** recommandés, envois assurés, actes judiciaires et colis (dès le jour ouvré suivant le dépôt en Poste Delivery Express, ex-Paccocelere, et Standard, ex-*pacco ordinario*). Poste Delivery International Express est suivi jusqu'à destination (hors Tunisie, Groenland, îles Féroé et certains codes postaux suisses) ; International Standard et Postapriority Internazionale s'arrêtent au centre d'échange italien sur poste.it. Posta Ordinaria (ex-Posta4) et Postamail Internazionale n'ont aucun suivi ; Posta1 a disparu le 1er mai 2026. Visibilité : 2 mois pour un colis, 24 mois pour un courrier.

## Statuts Poste Italiane : sens des messages en italien

Chaque scan comporte une phrase italienne issue de la *Legenda degli stati di spedizione*, affichée telle quelle dans Peek :

| Libellé Poste Italiane | Signification |
| --- | --- |
| `Presa in carico` | Envoi accepté et orienté vers sa destination. |
| `In attesa di sdoganamento` | Contrôle douanier en cours ; des documents peuvent être demandés. |
| `completata la fase di verifica per lo svincolo…` | Vérification douanière achevée, envoi dédouané. |
| `In transito` | En acheminement entre les centres de Poste Italiane. |
| `In transito presso il Centro di lavorazione Internazionale` | A quitté l'Italie vers un opérateur étranger (dernier scan pour Postapriority Internazionale et International Standard). |
| `presso il Centro di lavorazione Internazionale` | Même étape pour un recommandé, sur une ligne séparée après `In transito`. |
| `In consegna` | En tournée de livraison. |
| `consegna non andata a buon fine…` | Livraison échouée (par exemple adresse incomplète ou erronée). |
| `disponibile per il ritiro dal giorno lavorativo successivo alla data indicata` | Retrait possible au bureau de poste dès le jour ouvré suivant la date indiquée. |
| `disponibile per il ritiro entro i termini indicati su SMS/email` | Disponible en Punto Poste ou consigne (*locker*) dans le délai du SMS/e-mail. |
| `la spedizione è stata consegnata` | Livré. |
| `sono in corso delle verifiche sulla spedizione. contatta assistenza` | Vérification en cours : contacte le service client. |
| `in restituzione al mittente…` | En retour vers l'expéditeur. |
| `Resa al mittente` | Non distribué (garde expirée, adresse erronée ou refus) et renvoyé. |
| `Tracciatura non disponibile` | Dépôt très récent ou suivi expiré (2 mois colis, 24 mois courrier). |

`In consegna` reste parfois affiché après la remise ou pendant la garde au guichet, et un échec fait repasser l'envoi en `In transito`. Toute date prévue est « purement indicative » (*meramente indicativa*). Voir [comprendre les statuts de suivi](guide:tracking-statuses).

## Livraison en France, Belgique ou Suisse et retrait en Italie

**Vers la France, la Belgique ou la Suisse.** Un envoi postal `…IT` passe à la poste locale après le centre d'échange italien ; dans l'autre sens, poste.it n'affiche un envoi étranger qu'après sa prise en charge en Italie. Entre l'Italie, la France et la Belgique, l'union douanière de l'UE supprime tout droit de douane. En Suisse, selon l'OFDF/BAZG, la poste ou le transporteur déclare chaque envoi : TVA de 8,1 % (2,6 % taux réduit : livres, alimentation), franchise jusqu'à 5 CHF d'impôt ou de droit, plus d'éventuels frais de dédouanement ; voir [colis en douane](guide:customs).

**En Italie (*giacenza* et Punto Poste).** Poste effectue 2 essais au pied de l'immeuble (*piano strada*) : avis après le premier, second passage automatique (dès le jour ouvré suivant en Crono et Poste Delivery Business), puis garde gratuite (*giacenza*) au bureau de poste ; voir [colis non réclamé](guide:uncollected-parcel) :

- **Délais et procuration :** de 3 jours ouvrés en locker à 10 en bureau de poste (Poste Delivery Web : 10 bureau, 7 Punto Poste, 3 locker ; Crono : 10 en Punto Poste ; recommandé : 30 jours, Raccomandata 1 : 15 jours). Rendez-vous via « Prenota » ou au 06 4526 4526 ; retrait par un tiers au guichet avec procuration, sa pièce d'identité et copie de la tienne, ou en Punto Poste/locker par code PIN.
- **Scegli Tu et PostePlus :** si activé, Scegli Tu permet une modification unique (jour, adresse en Italie, bureau/Punto Poste, voisin, lieu sûr, vacances) ; PostePlus est réservé aux résidents en Italie.
- **Douane hors UE en Italie :** payable à la livraison, au guichet ou moins cher sur www.poste.it via « Paga » (frais Poste sur marchandise : 3 € jusqu'à 22 €, 7 € jusqu'à 150 €, 21 € au-delà ; en ligne : 1 € jusqu'à 150 €, 18 € au-delà). S'y ajoutent, selon la Commission européenne, un droit de douane de 3 € par type de produit sur les colis jusqu'à 150 € depuis le 1er juillet 2026 et, pour les achats en ligne, la taxe de gestion de l'Union de 2 € par type de produit, quelle que soit la valeur, depuis le 1er novembre 2026. Poste signale aussi d'importants ralentissements (*significativi rallentamenti*) techniques du dédouanement hors UE, sans délai estimé de retour à la normale.

## Colis bloqué, perdu ou endommagé

Pour un achat en ligne, vois d'abord le vendeur (livraison sous 30 jours et responsabilité jusqu'à remise dans l'UE) ; voir [suivi figé](guide:tracking-not-updating), [colis perdu](guide:lost-parcel) et [indiqué livré mais non reçu](guide:delivered-not-received). Chez Poste, expéditeur et destinataire peuvent réclamer, mais l'indemnité va à l'expéditeur sauf renonciation formelle (et via la poste d'origine pour un envoi venu de l'étranger). Signale tout dommage à la remise (*consegna cautelare* ou refus) ; voir [colis endommagé](guide:damaged-parcel).

**Délais :** 20 jours calendaires après prise en charge en Crono/Business ; du 6e jour ouvré à 3 mois en Standard. Dépôt sur [Reclami](https://www.poste.it/assistenza/reclami), par PEC à `reclamiretail@postecert.it`, en recommandé à Casella Postale 160, 00144 Roma ou au guichet (réponse sous 45 jours calendaires, puis conciliation et AGCOM).

## Contacter Poste Italiane

Relevé sur poste.it le 16 novembre 2026 :

- **Depuis l'étranger ou un mobile :** `+39 06 4526 3160`, lun.–sam. hors jours fériés italiens, 8 h–20 h, tarif opérateur.
- **Fixe italien :** `803 160` (gratuit, mêmes horaires). Réponse uniquement au titulaire du compte enregistré.
- **Écrit et chat :** `assistenza.spedizioni@posteitaliane.it`, [Scrivici](https://www.poste.it/assistenza/scrivici), [Contact us](https://www.poste.it/assistenza/scrivici-en) et *Assistente Digitale* 24 h/24.

> Des SMS frauduleux peuvent s'insérer par *spoofing* dans le fil officiel Poste Italiane. Ne clique sur aucun lien : tape `www.poste.it`. Poste ne demande jamais carte, OTP ni mot de passe ; signale à `antiphishing@posteitaliane.it` et vois [faux SMS de livraison](guide:delivery-scam-texts).

## Suivre un colis Poste Italiane avec Peek

[Peek](/) reconnaît aussitôt Poste Italiane sur `RA`, `1UW`, `3UW`, `8UW`, `JJITA`, `2IMA`, `2IVC`, `EF`, `EG`, `GF`, `JF`, `JG`, `LF` et `ZC…IT`, sur les numéros postaux valides en `IT` débutant par `C`, `E`, `L` ou `R`, et sur `5P` (sauf onze chiffres après `5P` vérifiant Colissimo : Peek interroge alors [La Poste](carrier:la-poste) et Poste Italiane, Poste d'abord en Italie). Les autres numéros postaux valides en `IT` affichent « Transporteur postal inconnu » (Poste puis [Chronopost](carrier:chronopost)) ; `X…IT` sans clé postale (hors Chronopost) et les codes SDA/Crono vont à Poste seule (`000A00000000B`, `9C0000I000000`, `9C0000J000000` aussi à [AliExpress / Cainiao](carrier:cainiao)). Sur 12 chiffres valides, Peek interroge Poste, [Colis Privé](carrier:colis-prive), BRT et leurs pairs (« Vérification des services de suivi… »), avec choix manuel via « Changer » ou « Changer de transporteur ». Liens `poste.it` (`#/risultati-spedizioni/`) et `sda.it` (`tracing.letteraVettura=`) acceptés sans code postal.

Hors d'Italie, Peek vérifie aussi les numéros postaux valides en `IT` (`RR…IT`, `CW…IT`, `ZC…IT`) chez [La Poste](carrier:la-poste), [bpost](carrier:bpost) ou [La Poste suisse](carrier:swiss-post) et bascule dès qu'un scan plus récent paraît ; à l'inverse, les étiquettes de 16 caractères de [Vinted Go](carrier:vinted-go) ou [Cainiao](carrier:cainiao) contenant un code `1UW`, `3UW`, `8UW` ou `5P` basculent vers Poste Italiane.

Vérification horaire et alertes « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Peek affiche jusqu'à 20 scans sur la carte, date prévue, service, « Frais d’importation » et horaires (« Heures dans ton fuseau horaire : … » sur peektracker.com et iPhone). Expéditeur, poids, dimensions et adresse du bureau ne sont pas affichés. Après deux jours sans résultat : « Après deux jours, le transporteur ne connaît toujours pas ce numéro. Vérifie-le, choisis un autre transporteur ou supprime le colis. »

## Questions fréquentes sur le suivi Poste Italiane

### Code 1UW, 3UW ou 8UW : quel est ce transporteur ?

Poste Italiane : `1UW`, `3UW` et `8UW` suivis de dix caractères sont des codes colis Poste Delivery Business, suivis dans « Cerca Spedizioni » ou en haut de cette page.

### Combien de temps met un colis Poste Italiane ?

À titre indicatif en Italie : 4 jours ouvrés en Crono, 1–2 en Crono Express, 1–4 après dépôt en Poste Delivery Express (hors samedi, option payante réservée à Business Express). À l'international : 2 jours vers l'UE et 2–4 hors UE en International Express ; 10–15 jours UE et 10–25 hors UE en International Standard (hors samedis et fériés).

### Combien de temps un colis reste-t-il en Punto Poste ou au bureau de poste ?

En Poste Delivery Web : 7 jours ouvrés en Punto Poste, 3 en locker et 10 en bureau de poste ; en Crono : 10 jours ouvrés en Punto Poste ; recommandé : 30 jours au bureau de poste (15 en Raccomandata 1). La date limite figure sur l'avis ou le SMS.

### Comment appeler Poste Italiane depuis la France, la Belgique ou la Suisse ?

Compose le `+39 06 4526 3160` du lundi au samedi de 8 h à 20 h (hors fériés italiens, tarif opérateur) ; le `803 160` est réservé aux fixes italiens.

:::sources
- [Poste Italiane : FAQ Spedizioni](https://www.poste.it/assistenza/faq-spedizioni-e-consegne/) – code, avis, procuration, recommandés
- [Poste Italiane : FAQ Delivery Web](https://www.poste.it/assistenza/faq-poste-delivery-web) – 2 essais, garde (10/7/3), PIN, code CP
- [Poste Italiane : Legenda stati](https://www.poste.it/cerca/views/risultati-spedizioni.html) – statuts, 12 chiffres, 2 et 24 mois
- [TG Poste : Tracking Poste](https://tgposte.poste.it/2022/03/09/tracking-poste-come-seguire-lo-stato-delle-spedizioni-di-poste-italiane/) – Dove Quando, centres de tri
- [Poste Italiane : FAQ Scegli Tu](https://www.poste.it/assistenza/faq-scegli-tu) – modification unique
- [Poste Italiane : FAQ PostePlus](https://www.poste.it/assistenza/faq-posteplus) – résidents en Italie
- [Poste Italiane : FAQ Punto Poste](https://www.poste.it/assistenza/faq-rete-punto-poste) – commerces et consignes
- [Poste Italiane : Chiamaci](https://www.poste.it/assistenza/chiamaci/) – numéros, horaires, titulaire
- [Poste Italiane : Scrivici](https://www.poste.it/assistenza/scrivici) – e-mail et 06 4526 4526
- [Poste Italiane : Contact us](https://www.poste.it/assistenza/scrivici-en) – contact en anglais
- [Poste Italiane : Reclami](https://www.poste.it/assistenza/reclami) – réclamations et PEC
- [Poste Italiane : Raccomandata](https://www.poste.it/posta-raccomandata/raccomandata) – numéro sur l'avis
- [Poste Italiane : Ritiro Digitale](https://www.poste.it/ritiro-digitale) – retrait numérique
- [Poste Italiane : Difendersi dalle truffe](https://www.poste.it/sicurezza-online/guide-per-operare-in-sicurezza/come-difendersi-dalle-truffe) – spoofing SMS
- [Poste Italiane : Delivery Express](https://www.poste.it/prodotti/poste-delivery-express.html) – 1 à 4 jours ouvrés
- [Poste Italiane : International Express](https://www.poste.it/prodotti/poste-delivery-international-express.html) – délais et exceptions
- [Poste Italiane : International Standard](https://www.poste.it/prodotti/poste-delivery-international-standard.html) – délais
- [Poste Italiane : CGC Express](https://www.media.poste.it/d9e41964-551f-4f58-9887-6608322cdf87/web/poste-delivery-express-cgc) – ex-Paccocelere
- [Poste Italiane : CGC Standard](https://www.media.poste.it/e12354a8-ea52-44ce-a996-e83c184000ca/web/poste-delivery-standard-cgc) – ex-pacco ordinario
- [Poste Italiane : Carta non universali](https://www.media.poste.it/37381c9f-a87e-4292-8b89-00e88fa53323/web/carta-servizi-non-universali) – Crono, délais, samedi
- [Poste Italiane : Carta universali](https://www.media.poste.it/c919f7cf-8236-428e-82fc-f8b2be8428b8/web/carta-servizi-universali) – Standard, Posta4
- [Poste Italiane : Posta Ordinaria](https://www.poste.it/lettera/posta-ordinaria) – sans suivi
- [Poste Italiane : Servizio Universale](https://www.poste.it/variazione-servizio-postale-universale) – fin de Posta1
- [Poste Italiane : Spedizioni da estero](https://www.poste.it/ricevere-spedizioni-da-estero) – dédouanement et taxe 2 €
- [Poste Italiane : Aggiornamenti operativi](https://www.poste.it/aggiornamenti-operativi) – ralentissements douaniers
- [Commission européenne : Customs Reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – droit 3 € et taxe 2 €
- [UE : Union douanière](https://european-union.europa.eu/priorities-and-actions/actions-topic/customs_fr) – marché intérieur
- [BAZG : Envois postaux et courrier rapide](https://www.bazg.admin.ch/de/postversand-interneteinkauf-kurierverkehr-einfuhr-firmen) – TVA suisse et 5 CHF
- [BAZG : Frais de dédouanement](https://www.bazg.admin.ch/de/verzollungskosten-und-paketverfolgung) – frais transporteur
- [Your Europe : Expédition et livraison](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_fr.htm) – règle des 30 jours
- [Universal Parcel Scraper : Poste Italiane](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/poste-italiane) – règles Peek
:::
