---
title: Suivi Redur : codes à 14 chiffres, statuts Espagne et aide
description: Suis un colis Redur en Espagne, au Portugal ou en Europe : numéros à 14 chiffres, scans d’agence et livraison, retrait en dépôt, Eurodis et support.
slug: suivi-redur
published: 2026-11-24
updated: 2026-11-24
---

**Redur** (*Redur S.A.*) est un opérateur espagnol de transport express et de logistique fort de plus de 50 ans d’histoire, basé à Algete, Madrid ([redur.es](https://redur.es/)). Exploité intégralement en propre avec plus de 61 agences (*delegaciones*) et 3 000 collaborateurs en Espagne continentale, dans les îles espagnoles et au Portugal, Redur est aussi membre fondateur d’**Eurodis**, le réseau européen de fret combiné colis et palettes. Outre les expéditions nationales urgentes B2B/B2C et les départs routiers internationaux quotidiens à travers l’Europe, Redur assure la distribution pharmaceutique certifiée GDP (*Farma 15-25*) et la logistique contractuelle. Tu peux suivre un envoi sur [redur.es/track-trace/](https://redur.es/track-trace/) ou coller ton numéro Redur à 14 chiffres ou ton lien de suivi dans le champ ci-dessus.

## Numéros de suivi Redur (format à 14 chiffres)

Les numéros de suivi Redur (*N° Tracking*) comptent 14 chiffres sans lettres ni espaces :

| Format (inventé) | De quoi il s’agit |
| --- | --- |
| `58492716380419` | Numéro de suivi colis Redur standard à 14 chiffres (*NTRACKING*). |
| `61738492051628` | Autre code de suivi Redur à 14 chiffres pour les liaisons Espagne, Portugal ou Eurodis. |

Sur [redur.es/track-trace/](https://redur.es/track-trace/), le formulaire recherche aussi par numéro d’expédition et par référence d’expéditeur ou de partenaire, tandis que le suivi automatisé dans Peek utilise le numéro de suivi à 14 chiffres (`NTRACKING`) ou un lien direct `redur.es/track-trace/?...&valor=`. Peek ne reconnaît pas Redur à partir du seul numéro : un numéro nu à 14 chiffres part vers DPD, [SEUR](carrier:seur) et d’autres transporteurs (SEUR en premier en Espagne), donc colle le lien Redur ou choisis Redur. Voir les [formats de numéros de suivi](guide:tracking-number-formats).

## Ce que signifient les statuts de suivi Redur

La page de suivi Redur affiche une barre de progression en 7 étapes accompagnée des lignes de scans d’agence (en espagnol sur `redur.es/track-trace/`, ou en anglais sur la page anglaise) :

| Redur indique (ES / EN) | Ce que cela signifie pour toi | Peek affiche |
| --- | --- | --- |
| `DOCUMENTACION` / `DATA ENTERING` | Documentation d’expédition électronique transmise par l’expéditeur avant l’entrée physique au dépôt (datée au jour seulement). | « Étiquette créée » |
| `ENTRADA PLAZA ORIGEN` / `INBOUND ORIGIN DEPOT` | Premier scan physique d’entrée à l’agence d’origine Redur. | « Pris en charge » |
| `SALIDA PLAZA ORIGEN` (`OUTBOUND ORIGIN DEPOT`), `ENTRADA/SALIDA PLAZA TRANSITO` (`INBOUND/OUTBOUND TRANSIT DEPOT`), `ENTRADA PLAZA DESTINO` (`INBOUND FINAL DEPOT`) | En mouvement entre agences d’origine, de transit et de destination en Espagne ou au Portugal. | « En transit » |
| `CARGA CAMION T. INTERNACIONAL` / `TRUCK LOAD INTERNATIONAL TRAFFIC` | Chargé dans un camion à destination d’un autre pays, où un partenaire assure la livraison. | « En transit » |
| `PENDIENTE DE FECHA CONCERTADA`, `FECHA DE ENTREGA CONCERTADA` / `BOOKING AGREED DATE` | En attente de convenir d’une date de livraison avec le destinataire, ou rendez-vous de livraison confirmé. | « En transit » |
| `SALIDA A REPARTO` / `OUT FOR DELIVERY` | En tournée aujourd’hui dans le véhicule de livraison local Redur. | « En livraison » |
| `RECOGERÁN EN AGENCIA` | Conservé à l’agence Redur de destination pour retrait par le destinataire. | « Prêt à être retiré » |
| `DESTINATARIO AUSENTE`, `INCORRECT ADDRESS`, `CERRADO PERMANENTE/TEMPORAL` | Tentative de livraison échouée en raison de l’absence du destinataire, d’une erreur d’adresse ou d’un local fermé. | « Tentative de livraison » |
| `DEVOLUCIÓN A ORIGEN` | En retour vers l’expéditeur après échec de livraison ou refus. | « Retourné à l’expéditeur » |
| `ENTREGA` / `DELIVERY` | Livré au destinataire (ou confirmé via la dernière étape active de la barre de progression Redur). | « Livré » |

## Services en Espagne, au Portugal et en Europe avec Eurodis

Selon [Redur](https://redur.es/) et [Services Redur](https://redur.es/servicios/) :

- **Domestique Ibérie (*Servicio Doméstico*) :** distribution urgente de colis, palettes et fret combiné en Espagne continentale, dans les îles et au Portugal via plus de 61 agences propres, appuyée par les alertes e-mail et SMS *Redur Connected* et l’outil de gestion d’envois *Maia*.
- **International Europe (*Servicio Internacional*) :** départs quotidiens en Europe pour les envois B2B et B2C via le réseau partenaire **Eurodis**. Dès que `CARGA CAMION T. INTERNACIONAL` s’affiche, un transporteur local membre d’Eurodis dans le pays de destination assure la livraison finale.
- **Pharma sous température dirigée (*Farma 15-25*) :** distribution conforme GDP entre 15 et 25 °C pour les envois médicaux et pharmaceutiques.

## Livraison manquée, rendez-vous en attente et colis égarés

- **`PENDIENTE DE FECHA CONCERTADA` (en attente de date convenue) :** Redur attend de convenir d’un jour de livraison avec toi avant de sortir le colis ; `FECHA DE ENTREGA CONCERTADA` ou `BOOKING AGREED DATE` indique qu’une date a été fixée. Vérifie ton téléphone et tes e-mails, ou appelle Redur avec ton numéro à 14 chiffres.
- **`DESTINATARIO AUSENTE`, `INCORRECT ADDRESS` ou `RECOGERÁN EN AGENCIA` :** contacte rapidement ton vendeur ou le service client Redur avec ton code de suivi à 14 chiffres pour confirmer l’adresse, planifier un nouveau passage ou vérifier les horaires de retrait en agence.
- **Envois retardés, endommagés ou manquants :** avertis d’abord la boutique en ligne ou l’expéditeur (qui détient le contrat avec Redur et peut ouvrir une réclamation officielle) et vérifie auprès de Redur dans quel dépôt se trouve le colis. Voir [colis indiqué livré mais non reçu](guide:delivered-not-received), [colis endommagé](guide:damaged-parcel), [colis perdu](guide:lost-parcel), [colis retourné à l’expéditeur](guide:returned-to-sender) et [pourquoi le suivi ne se met plus à jour](guide:tracking-not-updating).

## Service client Redur et siège de Madrid

Coordonnées officielles publiées sur [Redur Contacto](https://redur.es/contacto/) et [Services Redur](https://redur.es/servicios/) :

- **Téléphone central (Espagne) :** `(+34) 916 20 46 10` (fax `91 628 15 60`).
- **E-mail :** `info@redur.es`.
- **Formulaire de contact et annuaire des agences :** [redur.es/contacto/](https://redur.es/contacto/).
- **Siège (*Servicios Centrales Redur*) :** *Redur S.A.*, Carretera Algete–Madrid Km 2.5 (P.I. Redur), 28110 Algete, Madrid, Espagne.

> Méfie-toi des faux SMS te demandant de payer une petite somme en ligne pour reprogrammer une livraison en Espagne ou au Portugal. Vérifie toujours d’abord les avis de livraison via [redur.es/track-trace/](https://redur.es/track-trace/) ou auprès de ton vendeur. Voir les [arnaques aux SMS de livraison](guide:delivery-scam-texts).

## Suivre un colis Redur avec Peek

Colle un lien direct `redur.es/track-trace/?...&valor=` dans [Peek](/) et Peek reconnaît Redur automatiquement. Comme les numéros nus à 14 chiffres (`58492716380419` ou `61738492051628`, inventés) sont partagés par de nombreux transporteurs européens et que Redur n’a pas de préfixe exclusif, saisis ton numéro à 14 chiffres puis appuie sur « Changer » à l’ajout ou « Changer de transporteur » sur la fiche du colis pour choisir **Redur** (rechercher « Redur Spain », « Redur Portugal » ou « Redur S.A. » le trouve aussi) ; Peek le vérifie alors directement auprès du suivi Redur et affiche « Redur a ce colis. » une fois confirmé.

Peek lit le suivi Redur et affiche l’étape (« Étiquette créée », « Pris en charge », « En transit », « En livraison », « Prêt à être retiré », « Tentative de livraison », « Retourné à l’expéditeur », « Livré »), le libellé Redur et l’agence. Lorsque la dernière étape de la barre de progression Redur s’allume, Peek affiche le colis comme livré. Redur fournit les heures de scan sans fuseau : Peek date chaque scan sur le fuseau horaire de son agence (Madrid pour `ALGETE`, Lisbonne pour `LISBOA`, Canaries pour `LAS PALMAS`) et un scan d’une agence non localisée sur le fuseau du scan voisin, puis affiche tout à l’heure de ton appareil ; `DOCUMENTACION` ne conserve que le jour. Peek ne conserve pas le nombre de colis. Le suivi Redur ne donne ni date de livraison estimée, ni poids, ni nom de service, ni heure de livraison séparée ni numéro partenaire.

Tant que le colis reste ouvert dans Peek, ou le jour de la livraison si les notifications sont activées, Peek vérifie jusqu’à toutes les 10 minutes ; le jour où Redur affiche `SALIDA A REPARTO` ou `OUT FOR DELIVERY`, toutes les 2 minutes à partir de 6 h sur le fuseau horaire de l’agence du scan le plus récent tant que le suivi Redur répond. Sinon, Peek vérifie toutes les heures. Après 12 heures avec uniquement « Étiquette créée » ou « Pris en charge », ou deux jours sans nouvelles, Peek passe à une vérification par heure quelle que soit ta manière de suivre le colis, et toutes les 6 heures après sept jours à « Étiquette créée ». Lorsque le suivi Redur ne répond pas et que Peek suit le colis uniquement via des services de suivi, il vérifie toutes les heures, toutes les 3 heures après une journée calme, et jusqu’à toutes les 15 minutes le jour de la livraison tant que le colis est ouvert ou que les notifications sont activées.

Lorsque le scan le plus récent date de quatre jours et que le colis est toujours sur « Étiquette créée », « Pris en charge » ou « En transit », Peek le signale avec le jour de ce scan, par exemple « Aucune mise à jour depuis lun. 12 oct. ». La fiche du colis indique alors « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger Redur. », avec un lien « Demande de l’aide à Redur » vers la page de contact de Redur. Après trois semaines sans nouveau scan, elle affiche « Rien de nouveau depuis plus de trois semaines. Si le colis n’est toujours pas arrivé, contacte le vendeur. Peek continue d’interroger Redur. »

Tu peux choisir des alertes pour « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Peek suit uniquement les mises à jour de suivi et ne peut ni fixer un rendez-vous de livraison, ni modifier une adresse, ni contacter Redur à ta place.

## Questions sur le suivi Redur

### À quoi ressemble un numéro de suivi Redur ?

Les numéros de suivi Redur comportent 14 chiffres sans lettres ni espaces (par exemple `58492716380419`, inventé).

### Que signifie `PENDIENTE DE FECHA CONCERTADA` chez Redur ?

Cela signifie que Redur attend de convenir d’un jour de livraison avec toi avant de mettre le colis en tournée.

### Que signifie `RECOGERÁN EN AGENCIA` ?

Cela signifie que ton colis est conservé dans l’agence locale Redur (*delegación*), prêt à être retiré.

### Comment contacter le service client Redur ?

Appelle le standard central de Redur en Espagne au `(+34) 916 20 46 10`, écris à `info@redur.es` ou utilise la page de contact sur [redur.es/contacto/](https://redur.es/contacto/).

:::sources
- [Redur : site officiel](https://redur.es/) – 50+ ans, 61+ agences Espagne et Portugal, 3 000 collaborateurs, membre fondateur Eurodis, *Maia* et *Redur Connected*
- [Redur : Track & Trace](https://redur.es/track-trace/) – suivi public des envois
- [Redur : Servicios](https://redur.es/servicios/) – *Servicio Doméstico*, *Servicio Internacional*, *Farma 15-25* (GDP) et *Logística integral*
- [Redur : Contacto](https://redur.es/contacto/) – `(+34) 916 20 46 10`, `info@redur.es` et siège d’Algete (Madrid) (URL d’aide liée par Peek)
- [Universal Parcel Scraper : redur](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/redur) – format 14 chiffres, règle de lien `redur.es/track-trace/`, table de libellés espagnol/anglais, gestion de la barre de progression livrée et scans datés sur le fuseau horaire de chaque agence
:::
