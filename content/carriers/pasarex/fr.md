---
title: Suivi PasarEX : codes Amazon AMZPSR, douane et aide
description: Suis tes importations PasarEX (Pasar Express) en Colombie, Chili, Panama et Barbade : codes AMZPSR, statuts douane, règles PQR et téléphones.
slug: suivi-pasarex
published: 2026-11-24
updated: 2026-11-24
---

PasarEX (marque commerciale de *PASAR EXPRESS SAS*, société colombienne fondée en 1993) propose des services de messagerie express, fret aérien et livraison du dernier kilomètre pour les plateformes e-commerce. Ses sites couvrent la **Colombie**, le **Chili**, le **Panama** et la **Barbade**, et son offre comprend un centre logistique à Miami ainsi que des expéditions multimodales depuis la Chine.

PasarEX dédouane les colis en tant que commissionnaire en douane ; son suivi couvre ainsi tout le parcours depuis le manifeste jusqu’à l’arrivée de l’importation consolidée, la douane, les entrepôts et la livraison locale.

## Formats des numéros de suivi PasarEX

Peek accepte les numéros PasarEX de 6 à 30 lettres et chiffres contenant au moins un chiffre. Un format appartient exclusivement à PasarEX :

| Format (inventé) | De quoi il s’agit |
| --- | --- |
| `AMZPSR482917360` | `AMZPSR` + 9 chiffres (15 caractères) — un numéro Amazon consulté par le suivi PasarEX ; aucun autre transporteur dans Peek n’utilise cette forme. |

Si ta confirmation de commande contient un lien de suivi direct (`tracking.pasarex.com/?awb=...`), le coller dans Peek extrait le numéro de bordereau et sélectionne immédiatement PasarEX. Pour d’autres formats e-commerce, consulte le [guide des formats de numéros de suivi](guide:tracking-number-formats) et le [guide des commandes Amazon](guide:amazon-orders).

## Ce que signifient les statuts de suivi PasarEX

PasarEX regroupe les lignes de scan sous de grandes rubriques sur son suivi (`COMMENTS`, `IN CUSTOMS PROCESS`, `RECEIVED FOR DELIVERY`, `IN TRANSIT`, `IN WAREHOUSE`, `OUT FOR DISTRIBUTION`, `DELIVERED`, `SHIPPING WITH DELIVERY ISSUE`), avec des phrases de scan individuelles en espagnol ou en anglais accompagnées de la ville et de la date/heure.

| Libellé de scan PasarEX | Ce que cela signifie |
| --- | --- |
| `Orden de pedido recibida - paquete no arribado` / `Information (Manifest) of the shipment received.` | Les données électroniques du manifeste ont atteint PasarEX avant l’arrivée physique du colis à son hub d’importation. |
| `Carga consolidada para importación recibida` / `Reception of consolidated cargo in import.` | Le lot aérien d’importation consolidé est arrivé à l’agence passerelle de PasarEX (comme Bogotá) avant le passage en douane. |
| `Entry to station for customs processing.` | Le colis est entré à la station douanière pour le dédouanement import. |
| `Cleared by customs by PasarEx.` / `Cleared by customs and transferred to OPS.` | PasarEX a dédouané l’envoi et l’a transféré aux opérations nationales. |
| `Ingresó a bodega` / `Package received in warehouse.` / `Reception of consolidated cargo at a station.` | Enregistré dans un entrepôt PasarEX ou une agence de destination entre deux étapes du trajet national. |
| `Package in transit` / `Package in ground transit` | En transit routier ou sur la liaison nationale entre agences PasarEX. |
| `Applies to cargo that may experience delivery delays due to disturbances or protests` | Avis de service signalant de possibles retards liés à des perturbations locales ; le colis poursuit ensuite son trajet. |
| `Shipment left to local route for final delivery` | En tournée locale avec le livreur aujourd’hui pour la livraison finale. |
| `Shipment delivered in perfect condition` | Livré à l’adresse du destinataire. |

## Dédouanement, réclamations PQR et délais attendus

Parce que PasarEX dédouane directement les importations transfrontalières avant la distribution nationale :

- **Avant l’arrivée du colis :** tant que le suivi n’affiche que `Information (Manifest) of the shipment received.` ou `Orden de pedido recibida - paquete no arribado`, la commande est enregistrée mais le colis n’est pas encore arrivé chez PasarEX. Attends-toi à une période calme jusqu’à l’apparition de `Reception of consolidated cargo in import.` ou `Carga consolidada para importación recibida`. Voir le [guide douane](guide:customs) et [quand le suivi ne se met plus à jour](guide:tracking-not-updating).
- **Règles PQR et d’indemnisation en Colombie (résolution CRC 5050 de 2016) :** sur [PasarEX Colombia Support](https://pasarex.com/co/soporte/), Pasar Express SAS publie les délais légaux de réclamation prévus par la loi 1369/2009 et la résolution 5050/2016. Les destinataires doivent déposer une réclamation pour spoliation ou avarie dans les **5 jours ouvrables** suivant la réception du colis, tandis que les expéditeurs disposent de **10 jours calendaires** (national) ou **6 mois** (international) en cas de perte, spoliation ou dommage (les contrats entreprises peuvent prévoir leurs propres conditions). Les dossiers exigent des copies du bordereau aérien (*Guía Aérea*), de la facture commerciale, de la facture Pasar Express, la liste des articles, la description de l’emballage et des photos. PasarEX traite normalement les PQR sous **15 jours ouvrables** (prolongeables si enquête nécessaire), et l’utilisateur dispose de **10 jours ouvrables** après notification pour former un recours (*recurso de reposición y en subsidio de apelación*).

## Suivre PasarEX avec Peek

Colle ton numéro ou un lien `tracking.pasarex.com/?awb=...` dans Peek, qui suit plus de 4 500 transporteurs. Un lien va directement vers PasarEX. Pour un numéro nu, Peek n’interroge jamais PasarEX d’office : il peut interroger d’autres transporteurs au même format, puis interroge les services de suivi (« Vérification des services de suivi… »), et ne classe le colis sous PasarEX que si un service de suivi le nomme, sans autre transporteur, avec des étapes datées. Appuie donc sur « Changer » pour choisir PasarEX (sous « Correspond à ce numéro » pour un code `AMZPSR`), ou utilise « Changer de transporteur » sur un colis déjà ajouté.

Le suivi PasarEX demandant de cocher un reCAPTCHA à chaque consultation, Peek le lit via l’[adaptateur PasarEX open source de universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/pasarex) avec un navigateur auxiliaire. Peek récupère le libellé, la ville et l’heure de chaque scan et les classe du plus récent au plus ancien plutôt que selon les rubriques PasarEX. PasarEX donne des heures locales sans décalage UTC : Peek date chaque scan sur le fuseau horaire de sa ville (heure de New York pour Miami, heure de Bogotá pour Bogotá, Medellín et Cali, et heure de Santiago, Panama ou la Barbade pour ces lieux), un scan sans ville sur le fuseau du scan voisin ou sinon sur l’heure de Bogotá, et affiche chaque scan à l’heure de ton appareil. Le suivi PasarEX ne publie ni date de livraison estimée, ni poids, ni preuve de livraison.

Peek traduit les libellés PasarEX en étapes claires : **Étiquette créée** pour la réception du manifeste avant l’arrivée physique, **En transit** de la réception de la cargaison consolidée à la libération douanière, l’entrée en entrepôt et le transit terrestre (les libellés PasarEX passent directement du manifeste à l’arrivée import consolidée sans scan distinct **Pris en charge**), **À la douane** pendant le traitement douanier, **En livraison** lorsque le colis part en tournée locale, et **Livré** à la livraison finale.

Peek consulte le suivi PasarEX au plus une fois par heure — l’intervalle minimal autorisé par PasarEX entre deux contrôles, y compris après un échec, que le colis soit ouvert ou non ; il n’effectue pas de vérifications toutes les 2 minutes le jour où le colis est en livraison. Après sept jours à « Étiquette créée », il vérifie toutes les 6 heures. Lorsque le suivi PasarEX ne répond pas et que Peek suit le colis uniquement via des services de suivi, il vérifie toutes les heures, et toutes les 3 heures après une journée calme.

Lorsque le scan le plus récent date de quatre jours et que le colis est toujours sur « Étiquette créée », « Pris en charge » ou « En transit », Peek le signale avec le jour de ce scan, par exemple « Aucune mise à jour depuis lun 12 oct ». La fiche du colis indique alors « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger PasarEX. » Après trois semaines sans nouveau scan, elle affiche « Rien de nouveau depuis plus de trois semaines. Si le colis n’est toujours pas arrivé, contacte le vendeur. Peek continue d’interroger PasarEX. »

Choisis **Chaque scan**, **Étapes importantes** ou **Livraison seulement** dans les réglages d’alertes de Peek pour décider quelles mises à jour déclenchent une notification.

## Contacter PasarEX

PasarEX publie des lignes téléphoniques et adresses e-mail pour chaque pays :

- **Colombie ([pasarex.com/co/soporte](https://pasarex.com/co/soporte/)) :** `+57 601 384 7000`, `+57 601 390 6000` (également ligne PQR de Bogotá), `+57 601 291 6500`, `+57 300 919 7521`, `+57 300 919 7532` ; e-mail `sac@pasar.net` (support) et `pqr@pasar.net` (PQR/réclamations)
- **Chili ([pasarex.com/cl](https://pasarex.com/cl/)) :** `+56 2 2571 7500` ; e-mail `clgestionpe@pasar.net`
- **Panama ([pasarex.com/pa](https://pasarex.com/pa/)) :** `+507 836 0399` ; e-mail `pagestionpe@pasar.net` et `pqr@pasar.net`
- **Barbade :** `+1 246 4370277`, `+1 305 7042435`
- **Présentation de l’entreprise :** [PasarEX Colombia](https://pasarex.com/co/) et [Nosotros](https://pasarex.com/co/nosotros/)

## Questions fréquentes

### Qu’est-ce qu’un numéro de suivi `AMZPSR` ?

`AMZPSR` suivi de 9 chiffres (15 caractères au total, comme `AMZPSR482917360`) est un numéro Amazon consulté par le suivi PasarEX. Aucun autre transporteur dans Peek ne possède ce format, mais Peek n’interroge pas PasarEX automatiquement : sélectionne PasarEX sous « Correspond à ce numéro ».

### Pourquoi mon colis PasarEX indique-t-il `Orden de pedido recibida - paquete no arribado` ?

Cela signifie que PasarEX a reçu le manifeste de l’envoi, mais que le colis physique n’est pas encore arrivé à la station d’importation de destination de PasarEX. Les scans reprennent dès l’arrivée du vol consolidé.

### Pourquoi Peek actualise-t-il PasarEX moins souvent que certains autres transporteurs ?

PasarEX autorise au maximum une consultation par heure de son suivi, y compris après un échec, si bien que Peek le vérifie toutes les heures. Son suivi exige également de valider un reCAPTCHA à chaque recherche.

### De quel délai je dispose pour déposer une réclamation pour dommage auprès de PasarEX en Colombie ?

En vertu de la résolution colombienne CRC 5050/2016 publiée sur [pasarex.com/co/soporte](https://pasarex.com/co/soporte/), les destinataires disposent de **5 jours ouvrables** après la livraison pour déposer une réclamation pour avarie ou manquant, tandis que les expéditeurs disposent de 10 jours calendaires (national) ou 6 mois (international) pour perte ou dommage.

:::sources
- [PasarEX Colombia](https://pasarex.com/co/)
- [PasarEX Colombia — Soporte y PQR](https://pasarex.com/co/soporte/)
- [PasarEX Colombia — Nosotros](https://pasarex.com/co/nosotros/)
- [PasarEX Chile](https://pasarex.com/cl/)
- [PasarEX Panamá](https://pasarex.com/pa/)
- [adaptateur PasarEX de universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/pasarex)
:::
