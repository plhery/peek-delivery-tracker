---
title: Suivi HayPost : codes postaux AM, statuts et contacts
description: Suis un colis HayPost Arménie : numéros S10 AM, statuts IPS internationaux et nationaux, services postaux, contacts à Erevan et suivi Peek.
slug: suivi-haypost
published: 2026-11-24
updated: 2026-11-24
---

HayPost (*« Haypost » Closed Joint-Stock Company*, en arménien *Հայփոստ*) est l’opérateur postal national de la République d’Arménie. Fondé en 1994 et réorganisé en société publique par actions fermée (CJSC) en 1998, il gère environ 777 bureaux de poste en Arménie, 23 centres régionaux et un centre d’échange de courrier international, en tant que membre de l’Union postale universelle (UPU) et de PostEurop. Tu peux suivre un envoi sur [la page Track & Trace de HayPost](https://www.haypost.am/en/track-trace), ou coller ton numéro de suivi dans le champ ci-dessus.

## Numéros de suivi HayPost : format S10 et où les trouver

Ton numéro de suivi figure sur le reçu du bureau de poste ou dans l’e-mail d’expédition de la boutique en ligne. HayPost suit les codes-barres standard UPU S10 de 13 caractères avec chiffre de contrôle :

| Format (inventé) | Ce que c’est |
| --- | --- |
| `RR482917367AM` | Lettre recommandée internationale ou petit paquet envoyé d’Arménie (`R` + seconde lettre + 9 chiffres + `AM`). |
| `CP482917367AM` | Colis postal envoyé d’Arménie (`CP` + 9 chiffres + `AM`). |

Le suivi HayPost permet aussi de consulter les codes internationaux UPU S10 entrants expédiés depuis d’autres pays (se terminant par le code ISO à deux lettres du pays d’origine, comme `US`, `DE` ou `CN`) dès que la poste d’origine transmet les données électroniques d’expédition ou que l’envoi entre en Arménie. Le courrier ordinaire non suivi ne porte pas de code-barres S10 et ne se suit pas en ligne. Consulte [les formats de numéros de suivi](guide:tracking-number-formats) si ton colis utilise un transporteur privé.

## Signification des statuts de suivi HayPost

HayPost renvoie des lignes de scan en anglais de l’Union postale universelle (IPS) précisant l’étape du parcours : `(Otb)` à la sortie du pays d’origine, `(Inb)` à l’entrée dans le pays de destination ou `(EDI-received)` pour les données électroniques transmises par la poste expéditrice.

| Ce qu’affiche HayPost | Ce que ça signifie pour toi | Ce qu’affiche Peek |
| --- | --- | --- |
| `Receive item from customer (Otb)` | Déposé par l’expéditeur et pris en charge dans un bureau de poste. | « Pris en charge » |
| `Insert item into bag (Otb)`, `Send item abroad (EDI-received)`, `Receive item at office of exchange (Otb)` / `(Inb)` | Mis en sac pour l’envoi international, annoncé à l’étranger par échange électronique ou arrivé au bureau d’échange international. | « En transit » |
| `Send item to customs (Otb)` | Dédouanement export au bureau d’échange de sortie avant de quitter le pays d’origine. | « En transit » |
| `Send item to customs (Inb)`, `Record item customs information (Inb)` | Retenu ou contrôlé par la douane d’importation à destination. | « À la douane » |
| `Return item from customs (Inb)` | Libéré par la douane et remis dans le réseau postal (pas un retour à l’expéditeur). | « En transit » |
| `Send item to domestic location (Inb)`, `Receive item at location (Inb)`, `Receive item at sorting centre (Inb)`, `Item was delivered to the postal office (Inb)` | En cours d’acheminement via les centres de tri nationaux jusqu’au bureau de poste local chargé de la remise. | « En transit » |
| `Send item out for physical delivery (Inb)` | Parti en tournée avec le facteur pour livraison aujourd’hui. | « En livraison » |
| `Unsuccessful item delivery attempt (Inb)`, `The notification was sent to the customer: ...` | Livraison non aboutie ; HayPost ajoute souvent le motif et la suite prévue (par exemple `Addressee not available; Scheduled for further action/processing today`). | « Tentative de livraison » |
| `Receive item at collection point for pick-up (Inb)` | Prêt à être retiré au bureau de poste. | « Prêt à être retiré » |
| `Deliver item (Inb)` | Remis et enregistré comme livré. | « Livré » |

Fais attention à deux libellés faciles à mal comprendre. D’abord, `Return item from customs (Inb)` est une bonne nouvelle : la douane a fini le contrôle et rendu le colis à la poste pour la suite du trajet. Ensuite, `Item was delivered to the postal office (Inb)` (souvent relayé par des postes de destination à l’étranger) signifie que le bureau de poste local a reçu l’envoi, et non qu’il t’a été remis.

## Services nationaux, internationaux et express HayPost

À travers son réseau d’environ 777 bureaux de poste ([À propos de HayPost](https://www.haypost.am/en/about-us)), HayPost propose :

- **Services postaux nationaux ([haypost.am/en/postal-services/domestic-postal-services](https://www.haypost.am/en/postal-services/domestic-postal-services)) :** cartes postales, lettres, petits paquets jusqu’à 2 kg, colis jusqu’à 20 kg, **Yerevan Express Service (YES)** pour les envois suivis bureau à bureau ou à domicile dans Erevan (commandes passées entre 09h00 et 16h00 traitées le jour même ; après 16h00 le jour ouvrable suivant ; celles du samedi le lundi ; ligne de réservation `+374 10 514 513`) et **HayPost Sprint** pour les paquets e-commerce nationaux suivis jusqu’à 2 kg.
- **Services postaux internationaux ([haypost.am/en/postal-services/international-delivery](https://www.haypost.am/en/postal-services/international-delivery)) :** lettres internationales, petits paquets jusqu’à 2 kg (longueur + largeur + épaisseur ≤ 0,9 m, plus grand côté ≤ 0,6 m), colis jusqu’à 20 kg (aucun côté > 1,05 m), sacs M jusqu’à 30 kg et courrier accéléré EMS.
- **HayPost Express vers les États-Unis ([haypost.am/en/postal-services/Haypost-Express](https://www.haypost.am/en/postal-services/Haypost-Express)) :** livraison aérienne de colis depuis tout bureau de poste arménien vers Los Angeles (bureaux de retrait à Glendale, Van Nuys et Sylmar), jusqu’à 30 kg, avec un délai moyen de 7 à 10 jours ouvrables.

**FAQ de HayPost.** La [FAQ de HayPost](https://www.haypost.am/en/faq) regroupe les questions sur les délais indicatifs en Arménie, les droits de douane, l’indemnisation et la durée de garde des envois EMS ; pour les chiffres applicables à ton colis, renseigne-toi auprès de ton bureau de poste ou de HayPost.

## Colis HayPost retardé, en douane ou manquant

Pour un achat en ligne expédié par voie postale depuis ou vers l’Arménie, contacte d’abord le vendeur pour tout remboursement ou renvoi.

**Pauses silencieuses entre pays.** Après `Send item abroad (EDI-received)`, le courrier international attend le transport aérien, le tri à l’arrivée et la douane d’importation au bureau d’échange du pays de destination, ce qui peut créer des blancs de plusieurs jours. Consulte [pourquoi le suivi ne se met plus à jour](guide:tracking-not-updating).

**Dédouanement.** Les colis entrants en Arménie et les colis `AM` sortants arrivant dans des destinations comme l’UE ou les États-Unis passent par la douane d’importation (`Send item to customs (Inb)`). Garde ta facture d’achat et ton numéro de suivi à portée de main si la douane demande un justificatif de valeur. Consulte [colis retenu en douane](guide:customs).

**Livré, endommagé ou retourné.** Si un envoi indique `Deliver item (Inb)` alors que tu ne l’as pas, vérifie auprès de ton foyer et de ton bureau local (via [l’annuaire du réseau HayPost](https://www.haypost.am/en/our-network)), puis contacte le vendeur et le support HayPost. Consulte [colis livré mais non reçu](guide:delivered-not-received), [colis endommagé](guide:damaged-parcel), [colis perdu](guide:lost-parcel) et [retourné à l’expéditeur](guide:returned-to-sender).

## Service client et coordonnées HayPost

Contacts publiés sur [haypost.am/en/contact-us](https://www.haypost.am/en/contact-us) (et [haypost.am/am/contact-us](https://www.haypost.am/am/contact-us)) :

- **Téléphone service client :** `+374 10 514-514`.
- **Ventes et services entreprises :** `+374 10 544-444`.
- **Ligne de commande YES Yerevan Express :** `+374 10 514 513`.
- **E-mail :** `info@haypost.am`.
- **Siège social :** 65/5 Arshakunyats Ave., Erevan 0061, République d’Arménie.
- **Formulaire de contact :** sur [haypost.am/en/contact-us](https://www.haypost.am/en/contact-us), avec chat en direct sur [haypost.am](https://www.haypost.am/en).

> Méfie-toi des messages par SMS, WhatsApp ou e-mail t’invitant à ouvrir un lien tiers et saisir ta carte bancaire pour payer de petits frais de « relivraison » ou de « confirmation d’adresse » pour un colis HayPost. Vérifie directement ton code S10 sur [haypost.am/en/track-trace](https://www.haypost.am/en/track-trace) ou appelle le `+374 10 514-514`. Consulte [les SMS d’arnaque à la livraison](guide:delivery-scam-texts).

## Suivre un colis HayPost avec Peek

Pour les codes UPU S10 se terminant par `AM` (comme `RR482917367AM` ou `CP482917367AM`, chiffres inventés), [Peek](/) suit automatiquement HayPost comme poste d’origine privilégiée, et peut aussi suivre les codes S10 entrants destinés à l’Arménie une fois HayPost sélectionné. Comme HayPost n’a pas de règle d’URL de suivi dédiée dans Peek, colle directement le code S10 de 13 caractères ; si tu dois choisir HayPost manuellement, appuie sur « Changer » à l’ajout ou « Changer de transporteur » sur la fiche du colis et choisis HayPost (rechercher « Armenia Post » ou « Հայփոստ » le trouve aussi).

Peek lit directement l’API JSON publique de HayPost en anglais et affiche l’étape (« Pris en charge », « En transit », « À la douane », « En livraison », « Tentative de livraison », « Prêt à être retiré », « Livré »), les libellés IPS (y compris motifs d’échec et étapes suivantes comme `Addressee not available`), le bureau ou pays (sans codes postaux numériques), le pays de destination et l’heure de livraison. Les scans arméniens et les lignes `Send item abroad (EDI-received)` sont datés à l’heure d’Erevan, et les scans d’une poste étrangère sur le fuseau horaire de ce lieu (heure française pour Paris, par exemple) ; Peek les affiche à l’heure de ton appareil. Lorsque HayPost publie à l’avance une ligne planifiée `Send item abroad (EDI-received)` datée dans le futur (souvent quelques heures en avance), Peek l’omet tant que l’heure réelle n’est pas arrivée. L’API HayPost ne renvoie ni date de livraison estimée, ni poids, ni nom du service, ni coordonnées expéditeur/destinataire, ni photo de preuve de livraison.

Tant que le colis est ouvert dans Peek, ou le jour de la livraison avec les notifications activées, Peek vérifie jusqu’à toutes les 10 minutes ; le jour où HayPost affiche `Send item out for physical delivery (Inb)`, toutes les 2 minutes à partir de 6 h à l’heure du dernier scan HayPost, tant que le suivi HayPost répond. Sinon, Peek vérifie toutes les heures. Après 12 heures avec seulement « Étiquette créée » ou « Pris en charge », ou deux jours sans nouvelles, il vérifie toutes les heures quelle que soit ta façon de le suivre, et toutes les 6 heures après sept jours sur « Étiquette créée ». Lorsque le suivi HayPost ne répond pas et que Peek suit le colis uniquement via des services de suivi, il vérifie toutes les heures, toutes les 3 heures après une journée calme, et jusqu’à toutes les 15 minutes le jour de la livraison tant que le colis est ouvert ou que les notifications sont activées.

Lorsque le dernier scan date de quatre jours et que le colis reste à « Étiquette créée », « Pris en charge » ou « En transit », Peek le signale avec la date de ce scan, par exemple « Aucune mise à jour depuis lun. 12 oct. ». La fiche du colis affiche alors « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger HayPost. », avec un lien « Demande de l’aide à HayPost » vers la page de contact de HayPost. Après trois semaines sans nouveau scan, elle indique « Rien de nouveau depuis plus de trois semaines. Si le colis n’est toujours pas arrivé, contacte le vendeur. Peek continue d’interroger HayPost. »

Tu peux choisir les alertes « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Peek assure uniquement le suivi et ne peut ni dédouaner un colis ni ouvrir une enquête auprès de HayPost à ta place.

## Questions sur le suivi HayPost

### À quoi ressemble un numéro de suivi HayPost ?

HayPost utilise des codes postaux standard UPU S10 de 13 caractères : deux lettres, neuf chiffres et `AM` à la fin pour les envois déposés en Arménie (par exemple `RR482917367AM` ou `CP482917367AM`, chiffres inventés). Le courrier international entrant garde les deux lettres du pays expéditeur.

### Pourquoi mon colis HayPost indique-t-il `Return item from customs (Inb)` ?

Ce statut IPS signifie que la douane a libéré l’envoi et l’a remis à la poste pour la suite du transport — cela ne signifie pas que le colis repart chez l’expéditeur.

### HayPost peut-il suivre des colis envoyés en Arménie depuis l’étranger ?

Oui. Tout code international suivi UPU S10 à destination de l’Arménie peut être consulté sur [haypost.am/en/track-trace](https://www.haypost.am/en/track-trace) ou dans Peek dès réception des données électroniques d’expédition ou du scan par HayPost en Arménie.

### Comment contacter le service client HayPost ?

Appelle le `+374 10 514-514` (ou le `+374 10 544-444` pour les entreprises), écris à `info@haypost.am`, ou utilise le formulaire sur [haypost.am/en/contact-us](https://www.haypost.am/en/contact-us) ou le chat en direct sur le site de HayPost.

:::sources
- [HayPost : Home](https://www.haypost.am/en) – portail de l’opérateur postal national
- [HayPost : Track & Trace](https://www.haypost.am/en/track-trace) – suivi d’envois en ligne
- [HayPost : About Us](https://www.haypost.am/en/about-us) – fondé en 1994, CJSC depuis 1998, ~777 bureaux de poste, 23 centres régionaux, membre UPU et PostEurop
- [HayPost : Domestic Postal Services](https://www.haypost.am/en/postal-services/domestic-postal-services) – petits paquets 2 kg, colis 20 kg, YES Yerevan Express Service (`+374 10 514 513`) et HayPost Sprint
- [HayPost : International Delivery](https://www.haypost.am/en/postal-services/international-delivery) – limites petits paquets (2 kg), colis (20 kg), sacs M (30 kg) et EMS
- [HayPost : HayPost Express](https://www.haypost.am/en/postal-services/Haypost-Express) – envoi aérien vers Los Angeles (30 kg, 7–10 jours ouvrables)
- [HayPost : Our Network](https://www.haypost.am/en/our-network) – annuaire des bureaux de poste arméniens
- [HayPost : Contact Us](https://www.haypost.am/en/contact-us) – `+374 10 514-514`, `+374 10 544-444`, `info@haypost.am`, 65/5 Arshakunyats Ave., Erevan
- [HayPost : Contact Us (arménien)](https://www.haypost.am/am/contact-us) – URL d’aide officielle liée par Peek
- [HayPost : FAQ](https://www.haypost.am/en/faq) – sommaire des thèmes de la FAQ
- [Universal Parcel Scraper : haypost](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/haypost) – détection S10 `AM`, API JSON `api.haypost.am`, correspondance des statuts IPS, heure d’Erevan, filtre EDI futur et URL d’aide
:::
