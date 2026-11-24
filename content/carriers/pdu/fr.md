---
title: Suivi PDU : codes Parcel Delivery Union, statuts et aide
description: Suis un colis transfrontalier PDU (Parcel Delivery Union / 友递联) : formats de codes, statuts de transit et livraison, douane et contacts à Shanghai.
slug: suivi-pdu
published: 2026-11-24
updated: 2026-11-24
---

**PDU** (*Parcel Delivery Union*, exploité par *Shanghai Youdilian Technology Co., Ltd.*, en chinois *友递联科技*) est un prestataire de logistique intelligente transfrontalière basé à Shanghai et fondé en 2022 ([pduni.com](https://www.pduni.com/)). Disposant d’agences propres et de hubs opérationnels locaux dans plus de 10 pays en Amérique du Nord, Europe, Afrique et Asie, PDU fournit aux marchands e-commerce et fabricants le dédouanement de bout en bout, la collecte, la livraison finale à l’étranger et l’encaissement contre remboursement (COD). Tu peux vérifier un envoi sur le portail anglais de suivi PDU à [pduni.com/MV/en/customer-services/waybill-inquiry](https://www.pduni.com/MV/en/customer-services/waybill-inquiry) ou coller ton numéro de suivi dans le champ ci-dessus.

## Numéros de suivi PDU (suffixe `PD` et lettres de voiture personnalisées)

De nombreux colis transfrontaliers PDU portent un code à 12 caractères composé de deux lettres, de 8 chiffres et de `PD` :

| Format (inventé) | De quoi il s’agit |
| --- | --- |
| `MX48291736PD` | Lettre de voiture transfrontalière PDU à 12 caractères (`MX` + 8 chiffres + `PD`). |
| `CO73918462PD` | Autre format de lettre de voiture `..PD` à 12 caractères utilisé sur les axes internationaux. |

La page de recherche publique de PDU accepte aussi d’autres références alphanumériques de 6 à 40 caractères lorsque tu choisis PDU manuellement. Ce même format correspond aux numéros de [Colis Privé](carrier:colis-prive) : Peek interroge d’abord Colis Privé puis PDU, ou PDU en premier en Chine. Voir les [formats de numéros de suivi](guide:tracking-number-formats).

## Ce que signifient les statuts de suivi PDU

PDU attribue à chaque envoi et à chaque scan un code d’état global (`TRANSIT`, `COLLECTING`, `DELIVERY`, `SIGNED`, etc.) accompagné d’un libellé en anglais ou dans la langue de destination (souvent en espagnol sur les liaisons d’Amérique latine, parfois précédé d’un court code du transporteur local comme `ENT,Entregado` que Peek nettoie à l’affichage) :

| État ou libellé PDU | Ce que cela signifie pour toi | Peek affiche |
| --- | --- | --- |
| `Shipment information sent to PDU.`, `Package not found in last mile or customs process.` | Commande électronique reçue par PDU avant la prise en charge physique ou avant que le réseau local n’ait créé son premier enregistrement. | « Étiquette créée » |
| `COLLECTING` (`Being Collected`), `FINAL_COLLECTING` (`Last-Mile Collection`), `TRANSIT` (`In Transit`) | Collecté à l’origine ou en mouvement sur le transit international et dans les centres de distribution régionaux. | « En transit » |
| `Recogida en Aduanas`, `Ingreso a CEDI`, `En tránsito nacional` | Récupéré en douane, entré au centre de distribution national (CEDI) ou en transit intérieur. | « En transit » |
| `DELIVERY` (sans libellé de départ en tournée) | Remis dans le réseau du transporteur local ; Peek maintient le colis en transit jusqu’au début effectif de la tournée. | « En transit » |
| `Salida a Ruta` | En tournée avec le livreur local aujourd’hui. | « En livraison » |
| `DELIVERY_EXCEPTION` (`Delivery Exception`), `TO_BE_RETURN` (`Awaiting Return`) | Incident de livraison signalé ou colis en attente de retour à l’expéditeur ; contacte le vendeur ou le transporteur local. | « Problème signalé » |
| `RETURNED` (`Returned`) | Renvoyé vers l’expéditeur ou l’entrepôt d’origine. | « Retourné à l’expéditeur » |
| `SIGNED` (`Delivered`), `Entregado` | Livré et signé à l’adresse de destination. | « Livré » |

Note que le message initial `Package not found in last mile or customs process.` de PDU signifie simplement que le partenaire local du dernier kilomètre n’a pas encore scanné le colis — cela ne signifie **pas** que la douane bloque ton colis.

## Dédouanement, livraison finale à l’étranger et contre-remboursement

Selon [pduni.com](https://www.pduni.com/) et le [Centre d’aide PDU](https://www.pduni.com/MV/en/customer-services/help-center) :

- **Dédouanement en propre et classement SH par IA :** PDU gère son propre réseau de dédouanement dans plus de 10 pays avec correspondance automatisée des codes SH et prépaiement des droits ; selon sa FAQ, PDU ne facture pas de frais distincts pour les déclarations douanières export formelles, et les marchands peuvent transmettre en ligne des justificatifs douaniers complémentaires en cas de pièce manquante.
- **Livraison locale du dernier kilomètre :** une fois la marchandise libérée par les douanes de destination (`Recogida en Aduanas` / `Ingreso a CEDI`), des agences locales ou des coursiers partenaires effectuent la livraison à domicile et, le cas échéant, l’encaissement contre remboursement (COD).
- **Taxes et droits d’importation :** la TVA à l’importation ou les frais douaniers dépendent des seuils fiscaux locaux et de la collecte éventuelle des droits par le vendeur lors de la commande. Voir les [colis retenus à la douane](guide:customs).

## Colis PDU bloqué, sans nouvelles ou manquant

Comme les boutiques en ligne et usines détiennent le compte d’expédition auprès de PDU, **contacte toujours d’abord ton vendeur ou la place de marché** pour corriger une adresse, demander un remboursement ou une réexpédition.

- **Silence entre la Chine et la prise en charge locale :** les scans s’interrompent souvent plusieurs jours entre `Shipment information sent to PDU.` (ou `Package not found in last mile or customs process.`) et `Recogida en Aduanas` / `Ingreso a CEDI` pendant l’acheminement aérien ou maritime groupé vers le pays de destination. Voir [pourquoi le suivi ne se met plus à jour](guide:tracking-not-updating).
- **`DELIVERY_EXCEPTION` ou `TO_BE_RETURN` :** agis rapidement — vérifie ton adresse de livraison et ton numéro de téléphone auprès du vendeur afin que le hub local puisse programmer un nouveau passage avant que le colis ne bascule sur `RETURNED`.
- **Indiqué livré ou endommagé :** vérifie d’abord auprès de ton foyer, de l’accueil de l’immeuble et de tes voisins, puis ouvre une réclamation auprès du vendeur avant la fin de la protection acheteur. Voir [colis indiqué livré mais non reçu](guide:delivered-not-received), [colis perdu](guide:lost-parcel), [colis endommagé](guide:damaged-parcel) et [colis retourné à l’expéditeur](guide:returned-to-sender).

## Service client PDU et contacts à Shanghai

Coordonnées officielles publiées sur [PDU Waybill Inquiry](https://www.pduni.com/MV/en/customer-services/waybill-inquiry) et [PDU Help Center](https://www.pduni.com/MV/en/customer-services/help-center) :

- **Raison sociale :** *Shanghai Youdilian Technology Co., Ltd.* (*友递联科技*, ICP `沪ICP备2022026205号`).
- **Ligne téléphonique :** `021-50837888` (`+86 21 5083 7888`).
- **E-mail :** `service@pduni.com`.
- **Siège social :** Room 305, 6F, Zhonghai Building, Zhenbei Road, Putuo District, Shanghai, Chine.
- **Suivi et aide en ligne :** [pduni.com/MV/en/customer-services/waybill-inquiry](https://www.pduni.com/MV/en/customer-services/waybill-inquiry) et [pduni.com/MV/en/customer-services/help-center](https://www.pduni.com/MV/en/customer-services/help-center).

> Le Centre d’aide PDU avertit explicitement les utilisateurs contre les arnaques usurpant le logo ou les coordonnées de PDU. Ne paie jamais de frais de douane ou de livraison via un lien SMS non vérifié — confirme d’abord tout frais auprès de ton vendeur ou sur le portail officiel du transporteur. Voir les [arnaques aux SMS de livraison](guide:delivery-scam-texts).

## Suivre un colis PDU avec Peek

Lorsque tu saisis un code `..PD` à 12 caractères (`MX48291736PD` ou `CO73918462PD`, inventés), [Peek](/) interroge Colis Privé puis PDU à tour de rôle (PDU en premier en Chine) et affiche « PDU a ce colis. » dès que le suivi PDU le reconnaît. Si ton numéro PDU suit un autre format de 6 à 40 caractères, appuie sur « Changer » à l’ajout ou « Changer de transporteur » sur la fiche du colis et sélectionne **PDU** (rechercher « Parcel Delivery Union » ou « 友递联 » le trouve aussi).

Peek lit le suivi PDU et affiche l’étape (« Étiquette créée », « En transit », « En livraison », « Problème signalé », « Retourné à l’expéditeur », « Livré »), le libellé du scan débarrassé du préfixe du transporteur local (`Entregado` plutôt que `ENT,Entregado`), le lieu et l’heure de livraison. Les codes d’état PDU déterminent l’étape mais restent masqués dans toutes les applications Peek. PDU donne chaque heure de scan avec son décalage UTC, ou sinon à l’heure de Pékin, et Peek les affiche à l’heure de ton appareil. Le suivi PDU ne fournit pas de date de livraison estimée.

Tant que le colis reste ouvert dans Peek, ou le jour de la livraison si les notifications sont activées, Peek vérifie jusqu’à toutes les 10 minutes ; le jour où PDU affiche `Salida a Ruta`, toutes les 2 minutes à partir de 6 h sur l’horloge du dernier scan tant que le suivi PDU répond. Sinon, Peek vérifie toutes les heures. Après 12 heures avec uniquement « Étiquette créée » ou « Pris en charge », ou deux jours sans nouvelles, Peek passe à une vérification par heure quelle que soit ta manière de suivre le colis, et toutes les 6 heures après sept jours à « Étiquette créée ». Lorsque le suivi PDU ne répond pas et que Peek suit le colis uniquement via des services de suivi, il vérifie toutes les heures, toutes les 3 heures après une journée calme, et jusqu’à toutes les 15 minutes le jour de la livraison tant que le colis est ouvert ou que les notifications sont activées.

Lorsque le scan le plus récent date de quatre jours et que le colis est toujours sur « Étiquette créée », « Pris en charge » ou « En transit », Peek le signale avec le jour de ce scan, par exemple « Aucune mise à jour depuis lun. 12 oct. ». La fiche du colis indique alors « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger PDU. » Après trois semaines sans nouveau scan, elle affiche « Rien de nouveau depuis plus de trois semaines. Si le colis n’est toujours pas arrivé, contacte le vendeur. Peek continue d’interroger PDU. »

Tu peux choisir des alertes pour « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Peek suit uniquement les mises à jour de suivi et ne peut ni dédouaner un colis, ni modifier ton adresse de livraison, ni contacter PDU à ta place.

## Questions sur le suivi PDU

### Qu’est-ce que PDU (*Parcel Delivery Union*) ?

PDU (*Shanghai Youdilian Technology Co., Ltd.*, *友递联科技*) est une société de logistique transfrontalière basée à Shanghai, fondée en 2022, qui assure le dédouanement international, l’enlèvement, la livraison du dernier kilomètre et le contre-remboursement dans plus de 10 pays.

### À quoi ressemble un numéro de suivi PDU ?

De nombreuses lettres de voiture transfrontalières PDU comptent 12 caractères : deux lettres, 8 chiffres et `PD` (par exemple `MX48291736PD`, inventé), bien que le portail PDU suive aussi d’autres références alphanumériques de 6 à 40 caractères.

### Est-ce que `Package not found in last mile or customs process.` signifie que mon colis est bloqué en douane ?

Non. Sur le suivi PDU, ce message initial signifie simplement que les données électroniques du colis ont été créées mais qu’il n’a pas encore reçu son premier scan physique dans le système local de livraison ou des douanes.

### Comment contacter le service client PDU ?

Contacte d’abord ton vendeur en ligne pour un remboursement ou une modification de livraison. Les coordonnées publiées par PDU à Shanghai sur [pduni.com/MV/en/customer-services/help-center](https://www.pduni.com/MV/en/customer-services/help-center) sont `021-50837888` et `service@pduni.com`.

:::sources
- [PDU : site officiel](https://www.pduni.com/) – fondée en 2022 dans le district de Putuo à Shanghai, réseau propre de dédouanement et de dernier kilomètre dans 10+ pays, COD
- [PDU : portail de suivi des lettres de voiture](https://www.pduni.com/MV/en/customer-services/waybill-inquiry) – suivi public en anglais, `service@pduni.com` et `021-50837888`
- [PDU : centre d’aide et FAQ](https://www.pduni.com/MV/en/customer-services/help-center) – FAQ douane, alerte anti-usurpation et adresse du siège à Shanghai (*Shanghai Youdilian Technology Co., Ltd.*)
- [Universal Parcel Scraper : pdu](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/pdu) – règle de détection `^[A-Z]{2}\d{8}PD$`, API `traceByWeb`, correspondance état/libellé (`DELIVERY` vs. `Salida a Ruta`), nettoyage des préfixes et gestion des horodatages UTC/Pékin
:::
