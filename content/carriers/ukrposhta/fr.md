---
title: Suivi Ukrposhta : numéros, statuts et retrait en bureau
description: Suis un colis Ukrposhta (Poste ukrainienne) : formats 13 chiffres et UA, sens des statuts anglais, retrait en agence, envois internationaux et Peek.
slug: suivi-ukrposhta
published: 2026-11-22
updated: 2026-11-22
---

Ukrposhta (Укрпошта) est la poste nationale d’Ukraine. Tu peux suivre un colis sur son outil de suivi track.ukrposhta.ua, dans l’application mobile Ukrposhta ou en collant le numéro dans le champ ci-dessus. Le numéro vient de l’expéditeur : 13 chiffres pour un envoi en Ukraine, ou deux lettres, neuf chiffres et deux lettres pour un envoi international.

## Numéro de suivi Ukrposhta

Les numéros d’Ukrposhta existent en deux formats, et le format national à 13 chiffres se termine par un chiffre de contrôle (modulo 11).

| Format (fictif) | Ce que c’est |
| --- | --- |
| `0548291736543` | Un envoi national en Ukraine : 13 chiffres. |
| `RR482917367UA` | Un envoi international posté en Ukraine : deux lettres, neuf chiffres, `UA`. |
| `RR482917367US` | Un envoi entrant en Ukraine conserve le numéro de la poste d’origine et son code pays. |

Pas de numéro ? Demande-le à l’expéditeur ou consulte [où trouver un numéro de suivi](guide:find-tracking-number).

[À ne pas confondre](guide:tracking-number-formats) : en Ukraine, les bordereaux Nova Poshta comptent 14 chiffres et de nombreux numéros Meest se terminent par `G`.

## Que veulent dire les statuts de suivi Ukrposhta

Peek interroge Ukrposhta pour obtenir ses libellés anglais. Voici les plus courants, dans l’ordre habituel :

| Statut Ukrposhta | Ce que ça veut dire pour toi |
| --- | --- |
| `Created online. Waiting acceptance` | L’expéditeur a [créé l’étiquette en ligne](guide:tracking-statuses) ; Ukrposhta n’a pas encore le colis. |
| `Registered` | Pris en charge par Ukrposhta. |
| `Departed from the Branch` | A quitté le bureau de poste où il a été déposé. |
| `Arrived to the Logistics Center` | En cours de tri pendant l’acheminement en Ukraine. |
| `Arrival at inward office of exchange` | Un envoi venu de l’étranger est arrivé en Ukraine. |
| `Held at inward office of exchange` | Retenu à l’arrivée pour la douane. |
| `Item presented to customs` | Présenté à la douane pour contrôle. |
| `Released from customs for further delivery` | Libéré par la douane. |
| `Departure from inward office of exchange` | Sorti de douane et en route vers ta région. |
| `Item out for physical delivery` | En cours de livraison. |
| `Arrived to the Branch` | Arrivé à ton bureau de poste, prêt à être retiré. |
| `Final delivery` | Livré : remis au destinataire. |
| `Returned to Sender (Due to Recipient's Refusal)` | Refusé par le destinataire et renvoyé à l’expéditeur. |
| `Return: Delivered to Sender` | Revenu chez l’expéditeur : retourné, et non livré chez toi. |

Les étapes de retour commencent par `Return:`. Dès qu’une ligne indique que l’envoi repart à l’expéditeur, Peek affiche une livraison ultérieure comme « Retourné à l’expéditeur », et non « Livré ».

## Retrait en bureau de poste et livraison Ukrposhta

Le suivi indique si l’envoi est livré chez toi (`Item out for physical delivery`) ou s’il t’attend au bureau de poste (`Arrived to the Branch`, affiché « Prêt à être retiré » dans Peek). L’application Ukrposhta localise les bureaux sur une carte avec code postal, adresse et horaires d’ouverture. Renseigne-toi auprès de ton bureau pour connaître le délai de garde exact et la pièce d’identité demandée.

## Colis Ukrposhta en retard, perdu ou endommagé

Si rien ne bouge pendant plusieurs jours, consulte [suivi qui ne se met pas à jour](guide:tracking-not-updating) ; en cas de blocage douanier, voir [colis bloqué à la douane](guide:customs).

**Perdu ou en retard à l’international.** Selon les règles de l’Union postale universelle (UPU), une poste nationale doit accepter une réclamation concernant un colis ou un envoi recommandé ou assuré, déposée par l’expéditeur ou par le destinataire, dans les six mois à compter du lendemain du dépôt. Ces demandes sont gratuites selon le règlement, mais l’Ukraine se réserve le droit de les facturer. L’indemnisation en cas de perte ou de dommage revient à l’expéditeur, ou à toi s’il y renonce par écrit. Pour un achat en ligne qui n’est jamais arrivé, contacte d’abord le vendeur.

**Endommagé à l’arrivée.** Signale le dommage avant d’accepter l’envoi. En vertu des mêmes règles UPU, les postes restent responsables des avaries constatées avant ou lors de la livraison, et la poste qui livre établit un procès-verbal que tu contresignes et dont tu reçois une copie.

## Service client Ukrposhta

Pour un problème lié à l’application mobile, la fiche Google Play d’Ukrposhta indique mobile-app@ukrposhta.ua. Pour un colis international perdu ou retardé, dépose une réclamation comme expliqué ci-dessus : selon les règles UPU, Ukrposhta doit l’accepter de ta part comme de celle de l’expéditeur.

## Suivre un colis Ukrposhta avec Peek

[Peek](/) suit plus de 4 500 transporteurs. Pour un numéro à 13 chiffres respectant la clé de contrôle d’Ukrposhta, Peek interroge Ukrposhta — ainsi que [FAN Courier](carrier:fan-courier) ou iMile lorsque le numéro correspond aussi à leur format — et affiche Ukrposhta avec la mention « a ce colis » si elle seule le connaît ; si un autre transporteur le connaît aussi, tu choisis. Un numéro à 13 chiffres dont la clé est erronée n’est pas proposé à Ukrposhta : sélectionne Ukrposhta avec « Changer ». Un numéro S10 commençant par `C`, `E`, `L` ou `R` et finissant par `UA` avec une clé valide est reconnu immédiatement comme Ukrposhta ; pour une autre lettre initiale valide, Peek interroge Ukrposhta et [Chronopost](carrier:chronopost), et continue de vérifier après l’ajout si aucun ne le connaît encore. Un lien track.ukrposhta.ua contenant le numéro (`barcode=`) nomme toujours Ukrposhta.

Un numéro postal d’un autre pays n’est pas attribué d’office à Ukrposhta. Lorsque l’envoi est à destination de l’Ukraine, Peek peut poursuivre le suivi chez Ukrposhta, ou tu peux choisir « Ukrposhta » avec « Changer ».

Peek interroge le service utilisé par l’application Ukrposhta ; si ce service échoue ou ne connaît pas un envoi international, Peek ouvre la page de suivi d’Ukrposhta dans un navigateur sur son serveur. Il affiche chaque étape sur une carte avec son lieu et son pays, les étapes douanières et l’heure de livraison. Les horaires d’Ukrposhta n’incluent pas de fuseau : les scans situés en Ukraine sont lus sur l’heure de Kyiv et convertis dans ton fuseau horaire (l’historique se termine par « Heures dans ton fuseau horaire : » suivi de son nom) ; un scan à l’étranger ou sans pays (comme un trajet aérien) prend le fuseau de son lieu ou des étapes voisines, si bien que son heure peut être décalée. Peek n’affiche aucune date d’arrivée estimée et écarte la date prévue et les codes postaux fournis par Ukrposhta ; le seul point de retrait nommé est le bureau indiqué sur `Arrived to the Branch`. Les statuts gardent le texte anglais d’Ukrposhta, y compris `Final delivery`.

Tant que le colis est ouvert à l’écran — ou le jour de la livraison avec notifications activées — Peek vérifie jusqu’à toutes les 10 minutes, et une fois le colis en cours de livraison, jusqu’à toutes les 2 minutes (ou toutes les 5 minutes si la dernière vérification est passée par le navigateur) à partir de 6 h à l’heure du dernier scan (heure de Kyiv en Ukraine), tant que le service ou le site d’Ukrposhta répond. Sinon, ainsi qu’après deux jours sans nouveauté (12 heures tant que l’envoi reste sur « Étiquette créée » ou « Pris en charge »), il vérifie toutes les heures. Pour les notifications, choisis « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Après quatre jours sans scan, hors douane ou problème signalé, Peek affiche la date du dernier scan dans ta liste (« Aucune mise à jour depuis … ») et, sur la fiche du colis, « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger Ukrposhta. », accompagné alors seulement du lien « Demande de l’aide à Ukrposhta » vers son centre de contact.

## Questions sur le suivi Ukrposhta

### Comment suivre un colis Ukrposhta au Royaume-Uni ?

Lorsque tu ajoutes un envoi Ukrposhta depuis le Royaume-Uni, Peek interroge aussi [Royal Mail](carrier:royal-mail) sous le même numéro, ou Parcelforce pour un envoi commençant par `E` (EMS). Dès que cette poste publie un scan au moins aussi récent que le dernier d’Ukrposhta, Peek poursuit le suivi chez elle tout en conservant les étapes d’Ukrposhta.

### Pourquoi mon numéro de suivi Ukrposhta ne fonctionne-t-il pas ?

Vérifie-le caractère par caractère avec celui donné par l’expéditeur. Un numéro à 13 chiffres se termine par un chiffre de contrôle et Peek ne propose pas Ukrposhta si cette clé est fausse. Si le code est correct, Ukrposhta ne l’a peut-être pas encore enregistré : classé sous Ukrposhta, il affiche « En attente de Ukrposhta » pendant que Peek continue de vérifier. Si Ukrposhta ne le connaît toujours pas deux jours plus tard et qu’aucun autre transporteur n’a répondu, Peek affiche « Après deux jours, le transporteur ne connaît toujours pas ce numéro. Vérifie-le, choisis un autre transporteur ou supprime le colis. », puis vérifie chaque jour, puis chaque semaine au bout d’une semaine. Si rien ne bouge après plusieurs jours, contacte l’expéditeur.

### Comment suivre un colis envoyé de l’étranger vers l’Ukraine ?

Colle le numéro communiqué par l’expéditeur. Peek suit l’envoi auprès de la poste expéditrice ; dès que celle-ci indique l’Ukraine comme destination (ou si tu l’ajoutes depuis l’Ukraine), Peek interroge aussi Ukrposhta et bascule chez elle dès qu’un scan est au moins aussi récent, tout en conservant les étapes précédentes. Pour le suivre directement chez Ukrposhta dès le début, choisis « Ukrposhta » avec « Changer ». Un colis DHL expédié d’Allemagne dont le numéro commence par `C` est suivi chez Meest à la place.

:::sources
- [Universal Parcel Scraper : Ukrposhta](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/ukrposhta) – formats et clés de contrôle, liens track.ukrposhta.ua, service mobile et repli navigateur, libellés anglais, retours, heure de Kyiv et scans hors Ukraine, champs omis
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – transporteurs interrogés selon le format, transferts vers Ukrposhta (ou Meest pour les colis DHL en `C`) et vers Royal Mail ou Parcelforce, formats Nova Poshta et Meest
- [Union postale universelle : Manuel de la Convention](https://www.upu.int/UPU/media/upu/files/aboutUpu/acts/06-manualsInThreeVolumes/actInThreeVolumesConventionManual202609En.pdf) – réclamations sous six mois, réserve de l’Ukraine sur la tarification, indemnisation de l’expéditeur (ou renonciation écrite), constat de dommage à la livraison
- [Google Play : Ukrposhta](https://play.google.com/store/apps/details?id=ua.ukrposhta.android.app) – suivi dans l’application, recherche de bureaux avec codes postaux, adresses et horaires, e-mail de support de l’application
:::
