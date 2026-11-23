---
title: Suivi SAMUS : numéros, statuts et retrait à Vilnius
description: Suivez une lettre ou un colis SAMUS en Lituanie : où trouver le numéro, signification des statuts lituaniens, point de retrait à Vilnius et contact.
slug: suivi-samus
published: 2026-11-23
updated: 2026-11-23
---

SAMUS (UAB „SAMUS“) est un service lituanien de messagerie pour lettres et colis, actif depuis 2011. Il livre pour les entreprises sous contrat : lettres recommandées et express, courriers remis en main propre contre signature, et colis express en Lituanie et à l’étranger. Vous pouvez suivre un envoi sur [samus.lt, sous « Parcel tracking »](https://www.samus.lt/en/parcel-tracking/), ou coller le numéro dans le champ ci-dessus.

## Numéro de suivi SAMUS : à quoi il ressemble

La FAQ de SAMUS indique que le numéro d’envoi figure sur l’étiquette créée par ses clients dans son espace libre-service. Demandez donc le numéro à l’entreprise qui a envoyé le courrier ou le colis.

La documentation SAMUS destinée aux clients professionnels présente le numéro et le code-barres sur l’étiquette sous un seul format : deux lettres, neuf chiffres et LT, soit 13 caractères au total. C’est exactement la même forme qu’un numéro postal lituanien.

| Numéro (inventé) | De quoi il s’agit |
| --- | --- |
| `RD482917367LT` | Deux lettres, neuf chiffres, LT : le format de la documentation SAMUS. Peek traite le neuvième chiffre comme une clé de contrôle, comme sur les numéros postaux. |
| `RD482917360LT` | La même forme avec une clé de contrôle erronée : Peek ne nomme aucun transporteur. Vérifiez le numéro ou choisissez SAMUS vous-même (voir ci-dessous). |

Les espaces, tirets et minuscules n’ont pas d’importance lorsque vous collez le numéro dans Peek. Sur samus.lt, le résultat affiche un « Parcel number » et un « Parcel code ». Si le site répond `Your letter with number "…" is not found`, vérifiez le numéro auprès de l’expéditeur.

Si votre numéro a une autre forme, une autre société assure peut-être la livraison, comme [Lietuvos paštas](carrier:lietuvos-pastas), [Omniva](carrier:omniva) ou [Venipak](carrier:venipak). Le guide [formats de numéros de suivi](guide:tracking-number-formats) aide à les distinguer.

## Statuts de suivi SAMUS

SAMUS affiche ses statuts sur sa page de suivi uniquement en lituanien. Sa page anglaise reprend les mêmes libellés lituaniens. Voici les principaux :

| Statut sur samus.lt | Ce que cela signifie pour vous |
| --- | --- |
| `Siunta įvesta savitarnoje` | L’expéditeur a saisi l’envoi dans le libre-service SAMUS. SAMUS ne l’a pas encore. |
| `Siunta paruošta išsiuntimui` | L’envoi est prêt à partir, toujours chez l’expéditeur. |
| `Siunta neperduota kurjeriui surinkimo metu` | L’expéditeur n’a pas remis l’envoi au livreur lors de la collecte. |
| `Siunta surinkta iš siuntėjo` | Le livreur l’a collecté chez l’expéditeur. |
| `Siunta rūšiavimo centre` | Il se trouve au centre de tri de SAMUS. |
| `Siunta pas kurjerį` | Un livreur l’a en charge pour la livraison. |
| `Gavėjui paliktas pranešimas` | Le livreur ne vous a pas trouvé et a laissé un avis de passage. |
| `Gavėjas atvyks atsiimti į terminalą` | Vous viendrez le retirer au terminal. |
| `Siunta palikta UAB Samus Žalgirio g. 92 (vidinis kiemas), Vilnius` | Il attend au point de retrait SAMUS à Vilnius. Un libellé similaire indique Minijos g. 49 à Klaipėda. |
| `Siunta sėkmingai įteikta` | Livré. |
| `Siunta sėkmingai įteikta, įteikimo pranešimas grąžinamas siuntėjui` | Livré, et l’avis de réception signé repart vers l’expéditeur. |
| `Siunta neįteikta, neatsiliepė į pranešimą` | Non livré : personne n’a répondu à l’avis de passage. |
| `Siunta neįteikta (…)` | Non livré, avec la raison entre parenthèses : refusé, adresse erronée, destinataire introuvable, annulé par l’expéditeur, destinataire déménagé, adresse de domiciliation uniquement, établissement pénitentiaire, boîte aux lettres cassée ou autre motif. |
| `Siunta išsiųsta iš Lietuvos` | Il a quitté la Lituanie. Les lignes suivantes le suivent jusqu’au centre de tri, à la douane et au bureau de poste du pays de destination. |
| `Siunta perduota Lietuvos paštui` | SAMUS l’a remis à Lietuvos paštas. |
| `Siunta perduota į tranzitinį paštą` | SAMUS l’a remis à un opérateur postal de transit qui assure la suite. |
| `Siunta peradresuota` | Il a été réacheminé vers une autre adresse. |
| `Siunta neįteikta. Grąžinama siuntėjui` | Non livré et en cours de retour à l’expéditeur. `Siunta grąžinta siuntėjui` signifie qu’il est revenu. |
| `Siunta pamesta` | L’envoi est perdu. |

Peek ne peut pas lire le suivi de SAMUS et n’affiche donc pas ces lignes : voir « Suivre un colis SAMUS avec Peek » ci-dessous. Pour les statuts courants, consultez [signification des statuts de suivi](guide:tracking-statuses).

## Livraison et retrait SAMUS

**Délais de livraison.** La FAQ de SAMUS indique que la livraison standard par coursier en Lituanie a lieu le jour ouvré suivant ; la livraison le jour même ou avant 10 h 00 ou 12 h 00 peut être commandée en option. Ses conditions générales prévoient 2 à 6 jours ouvrés en Lituanie pour les lettres et envois S, M et L, jusqu’à 10 dans l’UE et jusqu’à 20 hors UE. Un envoi express S atteint son destinataire en Lituanie le jour ouvré suivant avant 17 h 00.

**Boîte aux lettres ou signature.** Les lettres et envois S sans suivi sont déposés en boîte aux lettres. Les envois suivis sont remis contre signature, à vous ou à une personne que vous avez mandatée. Pour un envoi adressé à une entreprise, tout salarié peut signer et, si le coursier ne peut joindre le destinataire en personne, l’envoi est laissé à l’accueil, à la personne de permanence ou à un agent de sécurité. Les envois M et L sont toujours remis contre signature.

**En cas d’absence.** Le coursier laisse un avis de passage après chaque tentative. Si votre numéro de téléphone figure sur l’envoi, le coursier peut vous appeler pour convenir d’un horaire et d’un lieu. Les règles précisent que le coursier n’est pas tenu d’attendre plus de 10 minutes. L’avis indique où retirer l’envoi ; convenez du moment avec le coursier. Le coursier conserve les lettres pendant 14 jours ouvrés après l’avis, puis les renvoie à l’expéditeur. Les colis express sont gardés gratuitement au terminal pendant 3 jours.

**Point de retrait SAMUS à Vilnius.** Žalgirio g. 92, LT-09303 Vilnius, rez-de-chaussée dans la cour intérieure. Repérez le bâtiment à façade orange : un passage dessous mène à la cour. Cherchez ensuite l’enseigne « Siuntų išdavimo vieta » (point de retrait des colis) et la porte portant l’enseigne SAMUS. Depuis le centre-ville ou la gare, prenez le bus 1G ou 53 jusqu’à l’arrêt « Žalgirio st. » sur Kalvarijų g., ou le bus 10 ou 34, puis marchez quelques minutes.

**Vérifiez le colis.** Contrôlez son état avec le coursier. Notez tout dommage sur la lettre de voiture et remplissez un constat d’avarie. Si vous signez sans réserve, SAMUS considère l’envoi comme livré intact. Conservez l’emballage d’origine, sous peine de perdre le droit à indemnisation.

**Changement d’adresse.** Après la remise de l’envoi, l’expéditeur peut demander à SAMUS de modifier l’adresse du destinataire en option payante, le plus tôt possible. Demandez à l’expéditeur.

**Envois à l’étranger.** SAMUS livre dans la plupart des pays européens et dans d’autres pays du monde. Les colis express mettent en général 1 à 5 jours ouvrés. Le suivi peut mentionner la douane du pays de destination. Les règles prévoient que le destinataire règle les droits et taxes, sauf si la lettre de voiture précise que l’expéditeur ou un tiers les paie. Le poids maximal d’un colis standard est de 30 kg.

## Colis SAMUS en retard, non livré ou perdu

**Aucune mise à jour.** Consultez d’abord le suivi sur samus.lt. Si le statut est flou ou s’il n’y a aucune information, SAMUS vous invite à écrire à info@samus.lt ou à appeler le +370 602 33354 afin qu’il recherche le colis. En cas de retard en Lituanie, les conditions prévoient le remboursement des frais de livraison au client de SAMUS, c’est-à-dire l’expéditeur. Plus d’informations dans [pourquoi le suivi ne se met plus à jour](guide:tracking-not-updating).

**Perdu ou endommagé.** Seul l’expéditeur, ou une personne mandatée par lui, peut déposer une réclamation auprès de SAMUS. La réclamation doit parvenir à SAMUS dans les 6 mois suivant l’envoi. SAMUS indemnise sous 30 jours pour les envois en Lituanie et sous 90 jours pour l’étranger. Pour un colis express perdu ou endommagé, l’indemnisation va jusqu’à 200 €. Pour les envois sans suivi, aucune réclamation n’est acceptée. Si votre commande est perdue ou endommagée, adressez-vous donc à l’entreprise qui l’a envoyée. Voir aussi [colis perdus](guide:lost-parcel).

**Retourné.** Un envoi que SAMUS ne peut livrer repart chez l’expéditeur en l’absence d’autres instructions. Voir [retourné à l’expéditeur](guide:returned-to-sender).

**Livré mais introuvable.** Pour un envoi adressé à une entreprise, un collègue, l’accueil ou un agent de sécurité a peut-être signé. Sinon, adressez-vous à l’expéditeur. Voir [livré mais non reçu](guide:delivered-not-received).

**Litiges.** Les conditions de SAMUS précisent que les litiges sont réglés selon le droit lituanien.

## Contact et service client SAMUS

Coordonnées indiquées sur samus.lt :

- **Numéro principal** : +370 602 33354.
- **E-mail** : info@samus.lt pour les questions générales ; paieska@samus.lt pour les colis.
- **Bureau et point de retrait de Vilnius** : Žalgirio g. 92, LT-09303 Vilnius.
- **Autres bureaux** : Partizanų g. 19 à Kaunas, Minijos g. 49-10 à Klaipėda (klaipeda@samus.lt) et Trakų g. 20 à Šiauliai.
- **Horaires** : les conditions indiquent du lundi au vendredi, de 8 h 00 à 17 h 00 (heure de Lituanie). La page de contact n’indique pas d’horaires d’ouverture.

Indiquez le numéro du colis lorsque vous écrivez. Pour la commande elle-même, un retour ou un remboursement, contactez l’entreprise qui a envoyé l’article.

## Suivre un colis SAMUS avec Peek

[Peek](/) ne peut pas reconnaître un envoi SAMUS à son seul numéro. Un numéro au format SAMUS — deux lettres, neuf chiffres et LT — est un numéro postal pour Peek. Pendant l’ajout (« Vérification des services de suivi… »), Peek interroge Lietuvos paštas et Chronopost, qui connaissent ce format, puis des services de suivi. Si l’un d’eux trouve l’envoi, la liste des transporteurs l’indique ; si un service nomme SAMUS avec des scans montrant l’envoi en mouvement, Peek sélectionne SAMUS. Si aucun ne le trouve encore, la liste affiche « Pas encore trouvé par les services de suivi. Peek continuera de vérifier après l’ajout. ».

Pour suivre l’envoi sous SAMUS, appuyez sur « Changer » et saisissez « SAMUS » dans « Rechercher un transporteur ». « UAB SAMUS » le trouve également. Il n’apparaît pas sous « Correspond à ce numéro ». Sur un envoi déjà ajouté, utilisez « Changer de transporteur ».

Vous pouvez aussi coller un lien issu du suivi lituanien de SAMUS, qui se termine par `/siuntu-sekimas/?id=` suivi du numéro. Peek lit alors le numéro et choisit SAMUS (« Transporteur détecté »). Un lien issu du suivi anglais, `/en/parcel-tracking/?id=`, ne désigne pas SAMUS à Peek : Peek ne lit le numéro que s’il a la forme postale, puis le traite comme un numéro postal. Collez plutôt le numéro ou le lien lituanien.

Pour un envoi classé sous SAMUS, Peek interroge tour à tour ParcelsApp, Ship24 et 17TRACK et garde le premier qui le trouve ; pour un numéro doté d’une clé de contrôle valide, il interroge en dernier le suivi de l’Union postale universelle (UPU). Pendant ses 30 premiers jours dans Peek, tant qu’il est en transit, Peek interroge d’abord Lietuvos paštas et Chronopost ; si l’un montre une avancée réelle, Peek y transfère l’envoi (« Transporteur remplacé automatiquement (ancien : SAMUS) »). Peek affiche le statut et l’historique du service, dans ses termes. Ils peuvent être en retard sur samus.lt, et pour certains envois il n’y a aucune donnée. Si vous avez laissé le transporteur au choix de Peek et que les services nomment SAMUS avec des scans en mouvement, Peek sélectionne SAMUS pour vous. Tant qu’aucun service n’a l’envoi, le lien de Peek ouvre le suivi de SAMUS avec votre numéro ; ensuite, il ouvre la page du service. Peek ne transmet ni votre adresse IP ni votre pays aux services.

Tant qu’aucun service ne connaît le numéro, un envoi classé sous SAMUS affiche « En attente de SAMUS ». Lorsqu’un service le suit, Peek vérifie toutes les heures, toutes les 3 heures dès qu’une journée passe sans nouveau scan, après sept jours sans nouvelles à l’étape « Étiquette créée » toutes les 6 heures, et jusqu’à toutes les 15 minutes le jour où il est en livraison tant que vous l’avez ouvert ou que les notifications sont activées. Un service qui ne connaissait pas le numéro est réinterrogé le lendemain.

Si quatre jours passent sans nouvelle mise à jour alors que l’envoi reste sur « Étiquette créée », « Pris en charge » ou « En transit », Peek affiche « Aucune mise à jour depuis quatre jours » (ou « Aucune mise à jour depuis … »), accompagné du message « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger SAMUS. » et du lien « Demande de l’aide à SAMUS ».

Peek peut vous envoyer des notifications pour « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Il ne peut pas contacter SAMUS ni l’expéditeur à votre place.

## Questions sur le suivi SAMUS

### Pourquoi Peek interroge-t-il Lietuvos paštas pour mon numéro SAMUS ?

Parce que les numéros SAMUS ont exactement le format des numéros postaux lituaniens et que Peek ne peut pas les distinguer. Appuyez sur « Changer » et recherchez « SAMUS », ou collez le lien du suivi lituanien de SAMUS.

### Où se trouve le point de retrait SAMUS à Vilnius ?

Au Žalgirio g. 92, LT-09303 Vilnius, dans la cour intérieure, sous l’enseigne « Siuntų išdavimo vieta ». Les bus 1G, 53, 10 et 34 s’arrêtent à quelques minutes à pied.

### Combien de temps SAMUS garde-t-il une lettre que j’ai manquée ?

Les lettres restent chez le coursier pendant 14 jours ouvrés après l’avis de passage, puis repartent chez l’expéditeur. Les colis express attendent gratuitement 3 jours au terminal.

### Puis-je déposer une réclamation auprès de SAMUS en tant que destinataire ?

Non. Seul l’expéditeur, ou une personne mandatée par lui, peut réclamer, dans les 6 mois suivant l’envoi. Adressez-vous à l’entreprise qui a expédié l’article.

### Comment contacter SAMUS ?

Appelez le +370 602 33354 ou écrivez à info@samus.lt (paieska@samus.lt pour les colis), du lundi au vendredi, de 8 h 00 à 17 h 00 (heure de Lituanie).

:::sources
- [SAMUS : Laiškai ir siuntos](https://www.samus.lt/) – lettres et colis, libre-service
- [SAMUS : Kompanija](https://www.samus.lt/kompanija/) – présent sur le marché lituanien depuis 2011
- [SAMUS : Kurjerių paslaugos](https://www.samus.lt/paslaugos/kurjeriu-paslaugos/) – lettres, remise contre signature, colis express
- [SAMUS : Parcel tracking](https://www.samus.lt/en/parcel-tracking/) – formulaire de suivi, message introuvable, statuts lituaniens
- [SAMUS : Siuntų sekimas](https://www.samus.lt/siuntu-sekimas/) – suivi lituanien, lien avec `id`, liste de statuts
- [SAMUS : Frequently asked questions](https://www.samus.lt/en/information/faq/) – numéro sur l’étiquette, délais de livraison, options, changement d’adresse, avaries, étranger, retards
- [SAMUS : Contacts](https://www.samus.lt/en/contacts/) – téléphone, e-mails, point de retrait de Vilnius, bureaux
- [SAMUS : Paslaugų teikimo taisyklės](https://www.samus.lt/informacija/taisykles/) – horaires, délais de livraison, signature, avis, conservation, dommages, droits de douane, réclamations, litiges (en lituanien)
- [SAMUS : SAMUS API v2.12](https://api-documentation.samus.lt/) – format du numéro et du code-barres, code-barres imprimé sur l’étiquette
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – aucun format propre SAMUS, lecture postale des numéros S10, liens de suivi, noms, services de suivi
:::
