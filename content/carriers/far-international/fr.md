---
title: Suivi FAR International : numéros, statuts et contact
description: Suis un colis FAR International (泛远国际) depuis la Chine : format FAR…YQ, sens des statuts, transporteur final, douane UE et contacts officiels.
slug: suivi-far-international
published: 2026-11-22
updated: 2026-11-22
---

FAR International (泛远国际, FAR International Logistics) est un prestataire de logistique transfrontalière basé à Hangzhou en Chine, fondé en 2004. Les boutiques en ligne et marketplaces y font appel pour expédier des colis à l’étranger : FAR collecte et contrôle les colis en Chine, tandis que ses partenaires assurent le transport international, le dédouanement et la remise au transporteur final dans ton pays. Tu peux suivre le colis sur la [page Track and Trace de FAR](https://www.far800.com/en/logistics), en anglais, ou coller le numéro dans le champ ci-dessus. Le code provient de la boutique, dans les détails d’expédition de ta commande.

## Numéro de suivi FAR International : à quoi il ressemble

FAR ne publie pas de règle officielle de format. Voici la structure reconnue par Peek comme appartenant à FAR (chiffres fictifs) :

| Numéro | Ce que c’est |
| --- | --- |
| `FARAA4829173654YQ` | `FAR`, deux lettres, 10 chiffres puis `YQ` : un numéro FAR International. |
| `FARAA482917365YQ` | Seulement 9 chiffres : ne correspond pas au format FAR. Peek ne sélectionne pas FAR International ; vérifie qu’il ne manque pas un chiffre. |

Dans Peek, les minuscules, espaces et tirets n’ont pas d’importance. Le suivi de FAR demande le numéro complet et accepte jusqu’à 50 numéros à la fois, un par ligne.

**Où le trouver.** Dans l’e-mail d’expédition ou la page de commande. Sinon, consulte [où trouver ton numéro de suivi](guide:find-tracking-number), ou les guides [commandes Temu](guide:temu-orders) et [commandes SHEIN](guide:shein-orders).

**Un second numéro.** La page de résultats de FAR peut aussi afficher un numéro de dernier kilomètre (尾程单号) : c’est le numéro attribué par le transporteur qui livre dans ton pays, que tu peux suivre sur son propre site.

## Statuts de suivi FAR International expliqués

Les scans d’entrepôt et de vol de FAR mêlent souvent chinois et anglais, comme `仓库收到包裹 Arrived at Warehouse` : Peek conserve la partie anglaise. Les étapes ultérieures peuvent être uniquement en anglais ou en chinois. Chaque ligne comporte une heure et un lieu, et le suivi de FAR dispose d’une colonne pour le fuseau horaire. Voici les libellés courants dans l’ordre habituel :

| Libellé FAR | Ce que ça veut dire pour toi | Affiché par Peek |
| --- | --- | --- |
| `Arrived at Warehouse` | L’entrepôt de FAR en Chine a reçu le colis. | « Pris en charge » |
| `Inspection by Warehouse` | L’entrepôt vérifie la marchandise (FAR ouvre les colis pour contrôle à la collecte). | « Pris en charge » |
| `Await Release from Sender` | L’entrepôt retient le colis en attendant confirmation du vendeur. | « Pris en charge » |
| `Departed Warehouse`, `Pickup Scan` | Sorti de l’entrepôt FAR, puis pris en charge pour l’étape suivante. | « En transit » |
| `In Transit to Export Port` | En route vers l’aéroport ou le port en Chine. | « En transit » |
| `Export Custom Clearance Processing and Flight Booking` | Dédouanement export en Chine et réservation de vol, une étape courante avant le départ (ce n’est pas la douane de ton pays). | « En transit » |
| `Scheduled to Take off`, `Export Scan` | En attente du vol, puis scanné pour l’export. | « En transit » |
| `Arrived at departure transport hub`, `Leaving from departure country`, `Flight has departed` | Arrivé au hub de départ, puis parti par avion. | « En transit » |
| `Arrived at Facility`, `Departed from Facility` | Passé par un centre de transfert, puis reparti. | « En transit » |
| `Last Mile Label Create` | L’étiquette du transporteur final a été créée (souvent après l’arrivée au hub de départ : ce n’est pas un retour en arrière). | le statut précédent |
| `At destination country` | Arrivé dans ton pays ; viennent ensuite la douane et le tri. | « En transit » |
| `Picked up by Customs clearance company`, `Clearance started`, `开始清关` | Pris en charge par un agent en douane ; dédouanement import lancé. | « À la douane » |
| `Customs Released`, `Import Scan`, `进口清关完成` | Libéré par la douane d’importation. | « En transit » |
| `Handover To Last Mile`, `Received by Last Mile Provider`, `Received by local delivery company` | FAR a remis le colis au transporteur qui livre dans ton pays. | « En transit » |
| `包裹开始派送` | Ce transporteur a pris le relais ; ses propres scans suivent (le colis n’est pas encore forcément en tournée). | « En transit » |
| `Transferred to Next Facility`, `In transit`, `In Transit to Destination` | En acheminement entre centres de tri. | « En transit » |
| `Out For Delivery` | En cours de livraison. | « En livraison » |
| `DELIVERED` | Livré. | « Livré » |

Après la remise, l’historique de FAR peut reprendre les libellés du transporteur local, par exemple ceux de FedEx (`Left FedEx origin facility`, `At local FedEx facility`, `On FedEx vehicle for delivery` en « En livraison » et `Package delivered` en « Livré »). Pour d’autres libellés, Peek s’appuie sur des mots courants comme delivered ou customs ; une ligne non reconnue reste dans l’historique en conservant l’étape précédente.

La FAQ de FAR indique qu’un suivi affichant `RETURN` signale une anomalie de livraison. Sur une ligne contenant uniquement `RETURN`, Peek conserve l’étape précédente : lis la ligne et contacte le vendeur.

## Qui livre les colis FAR International

FAR se décrit comme un prestataire « light asset » : ses antennes en Chine assurent la collecte, le contrôle, l’emballage, l’étiquetage et le tri, tandis que ses fournisseurs (opérateurs aéroportuaires et portuaires, compagnies aériennes et maritimes, agents en douane et prestataires logistiques tiers) gèrent le dédouanement, le transport longue distance et la livraison finale. Les pages de FAR ne précisent pas quel transporteur livre dans chaque pays : vérifie le numéro de dernier kilomètre sur la page de résultats de FAR.

FAR ne publie aucune règle sur les tentatives de livraison, les avis de passage ou les points relais : tout dépend du transporteur final. Suis le numéro de dernier kilomètre sur son site et consulte [livraison manquée](guide:missed-delivery).

**Douane dans l’UE.** Les achats en ligne hors UE sont soumis depuis le 1er juillet 2026 à un droit de douane de 3 € par article sur les envois jusqu’à 150 €, et depuis le 1er novembre 2026 à une redevance européenne de traitement de 2 € par article, quelle que soit la valeur. Un article regroupe les marchandises partageant le même classement tarifaire, la même description et, si requis, la même origine. Les pages de FAR n’expliquent pas comment ces frais sont réglés. Hors UE, les règles dépendent de ton pays : voir [colis bloqué à la douane](guide:customs).

**Quand la douane demande ton aide.** La FAQ de FAR précise qu’une anomalie de dédouanement dans le pays de destination nécessite l’intervention du destinataire auprès des autorités locales. Si le dédouanement échoue après le délai, le colis est abandonné ou renvoyé selon les règles locales, aux frais de l’expéditeur.

## Colis FAR International bloqué, perdu ou retourné

FAR travaille pour les boutiques et plateformes qui réservent l’envoi, et sa FAQ les renvoie vers leur chargé de compte FAR. En cas de retard, perte, dommage ou retour, contacte d’abord le vendeur.

**Rien pour l’instant.** Si le suivi de FAR ne renvoie aucune donnée pour le numéro, Peek affiche « En attente de FAR International » et continue de vérifier. Si rien n’apparaît après quelques jours, vérifie le code auprès du vendeur.

**Plusieurs jours sans nouveauté.** La FAQ de FAR précise que les mises à jour de vol peuvent arriver avec du retard. Après quatre jours sans nouveau scan sur un colis en route, Peek affiche dans ta liste la date du dernier scan (« Aucune mise à jour depuis … ») et, sur la fiche, « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger FAR International. ». Voir [suivi qui ne se met pas à jour](guide:tracking-not-updating) et [suivre un colis depuis la Chine](guide:tracking-from-china).

**Indiqué livré mais introuvable.** La FAQ de FAR conseille de vérifier autour du domicile (coins, entrée, boîte postale, consigne ou autre emplacement possible), puis auprès de tes proches et voisins. S’il reste introuvable, préviens immédiatement le vendeur : une enquête de réclamation doit être ouverte dans les **14 jours** suivant la date de signature, et c’est le vendeur qui l’ouvre auprès de FAR. Voir [colis indiqué livré mais non reçu](guide:delivered-not-received).

**Colis endommagé.** Conserve le carton extérieur et le produit endommagé comme le demande la FAQ de FAR, puis préviens le vendeur pour qu’il traite le dossier avec FAR. Voir [colis endommagé](guide:damaged-parcel).

**Retourné ou perdu.** Demande un renvoi ou un remboursement à la boutique. Voir [retourné à l’expéditeur](guide:returned-to-sender) et [colis perdu](guide:lost-parcel).

## Service client et contact FAR International

Coordonnées figurant sur [le site anglais de FAR](https://www.far800.com/en) :

- **Hotline globale :** 400-128-5656 (numéro chinois ; la page de service évoque une hotline client 24/7, sans préciser si ce numéro est joignable depuis l’étranger ni les langues parlées).
- **E-mail :** csc@far800.com.
- **Adresse :** Room 201, No. 22 Great Wall Street, Gongshu District, Hangzhou, Zhejiang Province, Chine, code postal 310022.

Pour ta commande, adresse-toi d’abord au vendeur : c’est lui le client de FAR.

## Suivre un colis FAR International avec Peek

[Peek](/) reconnaît immédiatement FAR International à partir d’un numéro `FAR…YQ` (« Transporteur détecté »). Coller un lien de résultats far800.com contenant `key=` et un numéro FAR fonctionne aussi. Pour tout autre numéro, comme celui du dernier kilomètre, colle-le également : Peek suit plus de 4 500 transporteurs et sélectionne le bon lorsque le format lui est propre. Si le format correspond à plusieurs transporteurs, Peek affiche « Vérification des services de suivi… » pendant sa recherche ; s’il ne correspond à aucun, il le recherche après l’ajout. Si besoin, appuie sur « Changer » et choisis-le sous « Tous les transporteurs », ou utilise « Changer de transporteur » sur un colis déjà ajouté. FAR International ne répond que pour les numéros `FAR…YQ`.

Peek lit directement le suivi de FAR : le statut, chaque étape avec son lieu et son heure, l’heure de livraison et le numéro de dernier kilomètre. Lorsque FAR indique un fuseau horaire, Peek l’interprète dans ce fuseau et l’affiche dans le tien (l’historique se termine par « Heures dans ton fuseau horaire : » suivi de son nom). FAR ne fournit ni date de livraison estimée ni poids.

Lorsque FAR renvoie un numéro de dernier kilomètre, Peek interroge le transporteur correspondant : la poste de ton pays pour un numéro postal S10 (deux lettres, neuf chiffres, deux lettres), par exemple [Correos](carrier:correos-spain) en Espagne, ou le transporteur identifié par le format. Dès que ce transporteur publie un scan au moins aussi récent que le dernier de FAR, Peek poursuit le suivi chez lui en conservant l’historique de FAR et affiche le livreur (par exemple « Livraison par Correos »). Sinon, Peek reste sur le suivi FAR.

Tant que tu gardes l’écran ouvert dans Peek — ou le jour de la livraison si les notifications sont activées — Peek interroge directement FAR jusqu’à toutes les 10 minutes, et jusqu’à toutes les 2 minutes dès 6 h heure locale le jour où le colis passe en livraison ; sinon, ainsi qu’après deux jours sans nouveauté (12 heures tant qu’il reste sur « Étiquette créée » ou « Pris en charge »), il vérifie toutes les heures. Via les seuls services de suivi, c’est toutes les heures, toutes les 3 heures après une journée sans scan, et jusqu’à toutes les 15 minutes le jour de la livraison si le colis est ouvert à l’écran ou si les notifications sont activées. Tu peux choisir d’être averti pour « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Peek ne peut pas contacter FAR ni le vendeur à ta place.

## Questions sur le suivi FAR International

### FAR International est-il un transporteur fiable ?

Oui. FAR International Logistics a été fondée à Hangzhou en 2004. Sa maison mère, FAR International Holdings Group, créée en novembre 2022, s’est introduite à la Bourse de Hong Kong en décembre 2023 selon les actualités de FAR. L’entreprise indique être devenue prestataire pour UPS Chine en 2005, avoir lancé un projet logistique transfrontalier avec Temu en 2023 et noué des coopérations stratégiques avec TikTok et SHEIN en 2024.

### Combien de temps met FAR International pour livrer ?

FAR annonce à ses clients les délais suivants : Express 3 à 5 jours ouvrés, Standard 6 à 9 jours ouvrés et Economy 10 à 30 jours ouvrés, variables selon le code postal de destination. L’estimation donnée par la boutique reste ta référence.

### Que veut dire « Await Release from Sender » ?

L’entrepôt de FAR en Chine a bien le colis et attend la confirmation de l’expéditeur avant de le faire partir. Si ce statut persiste, contacte le vendeur.

### Comment contacter FAR International ?

Par sa hotline globale 400-128-5656 ou par e-mail à csc@far800.com. Pour une commande en ligne, écris d’abord au vendeur.

:::sources
- [FAR International : Home](https://www.far800.com/en) – entreprise, 泛远国际, 2004 et 2022, actualité d’introduction en bourse à Hong Kong (22 décembre 2023), hotline globale, e-mail, adresse
- [FAR International : Track and Trace](https://www.far800.com/en/logistics) – suivi complet, jusqu’à 50 numéros un par ligne
- [FAR International : About FAR](https://www.far800.com/en/about) – 2004, 2022, UPS Chine 2005, Temu 2023, TikTok et SHEIN 2024
- [FAR International : Cross-border End-to-end Delivery](https://www.far800.com/en/service/info) – modèle light asset, partenaires pour douane et dernier kilomètre, inspection à la collecte, délais Express/Standard/Economy, hotline 24/7
- [FAR International : FAQs](https://www.far800.com/en/service/qa) – retards de suivi de vol, RETURN, livré non reçu et délai de 14 jours, colis endommagés, douane à destination
- [FAR International : Privacy Policy](https://www.far800.com/en/support/privacy) – raison sociale, hotline, e-mail, adresse
- [Commission européenne : réforme douanière de l’UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – droit de 3 € depuis le 1er juillet 2026 et redevance de traitement de 2 € depuis le 1er novembre 2026
- [Commission européenne : guide sur le droit de 3 € et la redevance de traitement (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – redevance de 2 € par article quelle que soit la valeur et définition d’un article
- [Universal Parcel Scraper : adaptateur FAR International](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/far-international) – format FAR…YQ, liens, libellés, numéro de dernier kilomètre, fuseaux horaires et reprise de suivi
:::
