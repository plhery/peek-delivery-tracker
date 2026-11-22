---
title: Suivi Whistl : qui livre ton colis et que faire
description: Suivre un colis Whistl (Parcelhub) : où trouver la référence, sens des statuts, quel transporteur assure la livraison finale et qui contacter.
slug: suivi-whistl
published: 2026-11-22
updated: 2026-11-22
---

Whistl, anciennement TNT Post UK, est une société britannique de livraison qui expédie des colis pour des boutiques et les confie à des transporteurs comme [Royal Mail](carrier:royal-mail), [Evri](carrier:evri) ou [DPD UK](carrier:dpd-uk) pour le dernier kilomètre. Suis ton colis sur le suivi Parcelhub de Whistl avec la référence figurant dans l’e-mail d’expédition ou le lien de suivi de la boutique, ou colle ce lien dans la case ci-dessus. Une fois le colis remis, le numéro et le suivi du transporteur final en disent généralement davantage.

## Numéro de suivi Whistl : où le trouver

Whistl ne publie pas de format unique. Ses guides précisent que le suivi recherche « par UPI (généralement le code-barres du colis) ou par numéro d’envoi (*Consignment Number*) » : l’UPI identifie un colis précis, tandis que le numéro d’envoi regroupe tout ce qui t’est expédié ensemble. Les références varient selon la boutique et le transporteur, d’une lettre suivie de nombreux chiffres (comme `L48291736482917364`) jusqu’au code-barres propre au transporteur final.

Tu la trouves dans l’e-mail d’expédition de la boutique, souvent sous forme de lien vers le suivi Whistl. Pour les colis entièrement suivis (*fully tracked*), le transporteur qui assure la livraison t’envoie aussi un e-mail et un SMS le jour de la livraison, sous sa propre marque et avec son propre numéro.

Le suivi demande une « Tracking Reference » et un code postal facultatif, puis affiche pour chaque colis son « Tracking Number: », ses scans ainsi que le « Carrier: » et le « Service: » réservés.

Une référence peut prendre la forme du numéro d’un autre transporteur, par exemple le code-barres du transporteur final (chiffres inventés) :

| Format (inventé) | À qui il ressemble |
| --- | --- |
| `L48291736482917364` | `L` et 17 chiffres : référence Whistl que Peek reconnaît pour l’interroger |
| `RR482917367GB` | Royal Mail (deux lettres, neuf chiffres, `GB`) |
| `H00KLM0048291708` | Code-barres Evri à 16 caractères, voir [suivi Evri](carrier:evri) |
| `48291736482917` | 14 chiffres : DPD UK parmi plusieurs autres, voir [DPD UK](carrier:dpd-uk) |
| `ZDBKLM482917` | XDP |

Plus d’aide pour décoder un numéro dans [formats de numéros de suivi](guide:tracking-number-formats).

## Signification des statuts de suivi Whistl

Le suivi Whistl, uniquement en anglais, traduit les scans de chaque transporteur dans les mêmes « événements normalisés » ; les colis entièrement suivis affichent aussi les commentaires du transporteur. Dans l’ordre habituel :

| Whistl indique | Ce que ça veut dire pour toi |
| --- | --- |
| `Shipment Created` | La boutique a enregistré l’envoi. Rien n’a encore bougé. |
| `Item receipted and processed by Whistl` | Whistl a pris en charge le colis dans l’un de ses dépôts. |
| `Handed over to Delivery Carrier` | Le transporteur nommé sur la page assure désormais la livraison. |
| `Handed over to Royal Mail` | Royal Mail a pris le relais pour le dernier kilomètre. |
| `Arrived At Depot` | Arrivé dans un dépôt en cours de route. |
| `Shipment is Out for Delivery` | En cours de livraison aujourd’hui. |
| `DELIVERED - Left as authorised` | Déposé à l’endroit que tu as autorisé, sans signature. |
| `Left with Neighbour` | Remis à un voisin. |
| `Delivered col. point` | Déposé en point de retrait, où il t’attend. |
| `No-one to receive` | Personne n’était présent : cherche un avis de passage ou un message du transporteur. |
| `Held by customs` | Retenu par la [douane](guide:customs). |
| `Return - Delivered` | Retourné et remis à l’expéditeur. |

Trois points prêtent souvent à confusion :

- **`Shipment Created` seul** signifie seulement que l’envoi est réservé. Si rien ne suit pendant plusieurs jours, la boutique ne l’a peut-être pas encore remis.
- **`Handed over to …`** est souvent la dernière ligne affichée par Whistl. Pour les envois à suivi partiel (*part-tracked*), Whistl précise qu’il n’est « pas possible de suivre les articles après la remise ». Suis alors le colis avec le numéro du transporteur final.
- **Problèmes** tels que `Delayed`, `Bad Address`, `Refused`, `Lost` ou `Item damaged` signalent un incident ; `Shipment Deleted` indique que l’expéditeur a annulé l’envoi. Des lignes comme `Item Weight Changed` ou `Label reprinted` sont de simples notes qui ne déplacent pas le colis.

## Livraison Whistl et livraisons manquées

C’est le transporteur final, et non Whistl, qui apporte le colis : ce sont donc ses règles qui s’appliquent. En cas d’absence, ce transporteur laisse un avis papier ou numérique indiquant qui est passé et quand il repassera.

Le guide transporteurs Parcelhub de Whistl (août 2025) indique, par transporteur :

| Transporteur | Tentatives et suite |
| --- | --- |
| Royal Mail | 1 tentative et avis « something for you » ; retrait en bureau de livraison ou bureau de poste, sinon retour après 18 jours. Depuis mai 2023, si un colis ne rentre pas dans la boîte aux lettres ou exige une signature en ton absence, il est laissé en lieu sûr ou chez un voisin. |
| Evri | Jusqu’à trois tentatives ; préférences réglables dans l’application Evri ; créneau de 2 heures par e-mail ou SMS. |
| DPD | Avis plus SMS ou e-mail pour choisir voisin, lieu sûr, nouvelle date ou point relais ; créneau d’une heure. |
| Yodel | Jusqu’à trois tentatives ; les adresses insulaires peuvent n’en avoir qu’une avant retrait demandé en bureau de poste. |
| [Amazon Shipping](carrier:amazon-shipping) | Jusqu’à trois tentatives sur 3 jours consécutifs. |
| DHL Parcel UK | Jusqu’à 2 tentatives. |
| Parcelforce | 1 tentative, avec options de dépôt sécurisé et de redirection. |
| [UPS](carrier:ups) | 1 tentative, puis notification numérique avec instructions de nouvelle livraison. |

À l’étranger, les envois partiellement suivis passent à des partenaires internationaux et suivent les règles de la poste locale. Whistl ne possède pas ses propres points de retrait : `Delivered col. point` désigne le magasin ou la consigne du transporteur final. Un colis refusé repart chez l’expéditeur et ne peut pas être relivré.

Indiqué livré mais rien reçu ? Les guides Whistl recommandent de vérifier d’abord l’avis de passage, les lieux sûrs et les voisins. Voir ensuite [livré mais non reçu](guide:delivered-not-received).

## Colis Whistl non livré, en retard ou perdu

La page de contact de Whistl indique au public de « contacter le détaillant auprès duquel tu as effectué l’achat ». C’est la boutique qui détient le contrat, ouvre une enquête « Where Is My Item » auprès de Whistl et dépose toute réclamation.

Ce que précisent les guides Whistl :

- Un colis britannique impossible à livrer repart chez l’expéditeur sous 5 jours ouvrés après son entrée dans le réseau Whistl.
- Pour les articles à suivi partiel, aucune indemnisation n’est versée pour retard, et Whistl ne peut plus intervenir une fois l’article remis à un autre transporteur et non livré.
- Les délais de réclamation de l’expéditeur dépendent du transporteur : par exemple sous 28 jours après expédition chez Evri, ou chez Royal Mail une attente minimale de 10 jours calendaires avant réclamation et un dépôt sous 80 jours. Préviens la boutique rapidement.

Si le suivi n’avance plus, commence par [pourquoi un suivi s’arrête de se mettre à jour](guide:tracking-not-updating).

## Numéro et service client Whistl

Coordonnées indiquées sur la page de contact de Whistl :

- **Pour ton colis :** la boutique où tu as commandé.
- **Suivi :** le bouton « Whistl Tracking Portal » sur la page de contact.
- **Renseignements généraux :** `01628 702 965`, entre 8 h 30 et 17 h 30, heure du Royaume-Uni. Cette ligne s’adresse aux clients professionnels de Whistl.
- **E-mail Customer Services :** `packagescustomerservices@whistl.co.uk`, qui « ne peut pas aider le public pour des questions de suivi liées à un colis ».
- **Siège social :** Network House, Third Avenue, Marlow, Buckinghamshire, SL7 1EY.

En Grèce, à Chypre, au Luxembourg et à Malte, Whistl n’a ni bureau ni ligne téléphonique. Adresse-toi à la boutique puis, après remise, au transporteur ou à la poste locale nommés sur le suivi.

## Suivre un colis Whistl avec Peek

[Peek](/) suit plus de 4 500 transporteurs. Pour une référence à 18 caractères commençant par `L` suivie de 17 chiffres, Peek interroge directement Whistl et affiche « Whistl a ce colis. » dès que Whistl le connaît. Les autres références Whistl n’ont pas de format propre : colle plutôt un lien Whistl (`web.parcelhub.net` ou `despatch.whistl.co.uk` avec `/Tracking/reference/…`, ou `trackmyitem.whistl.co.uk` avec `/tracking/…`), qui identifie Whistl aussitôt (« Transporteur détecté »), ou ajoute la référence et choisis « Whistl » avec « Changer ». Aucun code postal n’est nécessaire. Une référence au format d’un autre transporteur (comme un numéro Royal Mail en `…GB`) suit ce transporteur, et non Whistl.

Peek lit le suivi Parcelhub de Whistl. Ses horaires n’indiquent pas de fuseau : Peek les lit sur l’heure locale du lieu du scan (heure du Royaume-Uni au Royaume-Uni) et les affiche dans ton fuseau horaire (« Heures dans ton fuseau horaire : » suivi de son nom). Peek affiche le statut, le service, le nom du signataire lorsqu’il est indiqué à la livraison, le numéro Whistl canonique s’il diffère de la référence, ainsi que le transporteur final et son numéro lorsqu’ils apparaissent. Il n’y a ni date de livraison prévue, ni poids, ni point de retrait. Les statuts conservent le texte anglais de Whistl, sauf `Delivered`, `In transit` et `Returned to sender`, traduits par Peek.

Lorsque Whistl nomme Royal Mail, Parcelforce Worldwide, DPD UK, DHL Parcel UK (DHL eCommerce UK), Amazon Shipping, [InPost](carrier:inpost), UPS ou [FedEx](carrier:fedex), Peek peut continuer à suivre le colis chez ce transporteur. Un colis indiqué uniquement comme Evri, DPD ou Yodel n’est pas relayé automatiquement : ajoute le numéro propre du transporteur, ou consulte le site de Yodel, que Peek ne suit pas. Une référence couvrant plusieurs colis n’est pas suivie : ajoute chaque numéro de colis individuel.

Tant que le colis est ouvert dans Peek — ou le jour de la livraison si les notifications sont activées — Peek interroge directement Whistl jusqu’à toutes les 10 minutes, et jusqu’à toutes les 2 minutes (ou toutes les 5 minutes s’il doit ouvrir le suivi Whistl dans un navigateur) dès 6 h à l’heure du dernier scan (heure du Royaume-Uni au Royaume-Uni) le jour où Whistl scanne le colis « En livraison », tant que son suivi répond ; sinon, ainsi qu’après deux jours sans nouveauté (12 heures tant que le colis reste sur « Étiquette créée » ou « Pris en charge »), il vérifie toutes les heures. Pour les notifications, choisis « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Après quatre jours sans scan en route, hors douane ou problème signalé, Peek affiche la date du dernier scan (« Aucune mise à jour depuis … ») et indique « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger Whistl. », avec le lien « Demande de l’aide à Whistl » vers sa page de contact. Peek ne peut pas contacter Whistl ni la boutique pour toi.

## Questions sur le suivi Whistl

### Whistl fait-il partie de Royal Mail ?

Non. Whistl UK Ltd est une entreprise distincte, anciennement TNT Post UK. Elle remet de nombreux envois à Royal Mail pour le dernier kilomètre, d’où l’apparition de `Handed over to Royal Mail`.

### Qui livre les colis Whistl ?

Généralement un autre transporteur : DPD, Evri, Yodel, Royal Mail, Amazon Shipping, DHL Parcel UK, Parcelforce ou UPS au Royaume-Uni, et des partenaires locaux à l’étranger.

### Le suivi Whistl est-il fiable ?

Oui. Le suivi officiel de Whistl fonctionne sur `web.parcelhub.net`, `despatch.whistl.co.uk` et `trackmyitem.whistl.co.uk`, et Parcelhub fait partie du groupe Whistl.

### Pourquoi mon numéro de suivi Whistl ne fonctionne-t-il pas ?

Le suivi répond « Unable to find shipment » lorsqu’il ne connaît pas la référence. Vérifie-la auprès de la boutique, ou suis directement le numéro du transporteur final reçu par SMS ou e-mail.

:::sources
- [Whistl: Contact Us](https://www.whistl.co.uk/contact) – public invité à contacter le détaillant, téléphone et horaires, e-mail Customer Services, portail de suivi, siège social
- [Whistl: home page](https://www.whistl.co.uk/) – Whistl UK Ltd, services aux entreprises, Parcelhub membre du groupe Whistl
- [Whistl: Domestic Fully Tracked Customer Guide (October 2025)](https://www.whistl.co.uk/1817/6062/1711/Whistl_Domestic_Fully_Tracked_Customer_Guide_-_V2.2_October_2025.pdf) – UPI et consignment, événements normalisés, SMS et e-mails des transporteurs, avis de passage, refus, perte, 5 jours ouvrés, réclamations expéditeur
- [Whistl: Part Tracked Services Customer Guide v1.7](https://www.whistl.co.uk/download_file/force/3171/250) – remise à Royal Mail et partenaires, 18 jours, aucune indemnisation de retard, liens de suivi, e-mails de marque
- [Parcelhub: Domestic Carrier Guide (August 2025)](https://www.whistlparcels.co.uk/hubfs/Parcels/Documents/Parcelhub_Carrier_Guide_Domestic_-_V1.1_Aug_2025.pdf) – transporteurs utilisés, tentatives et options par transporteur, délais de réclamation expéditeur
- [Parcelhub: Part Tracked Services Customer Guide V1.0](https://www.whistlparcels.co.uk/hubfs/Parcels/Documents/Parcelhub_Part_Tracked_Customer_Guide_-_V1_September_2024.pdf) – portails de suivi, recherche par UPI ou consignment, aucune aide après remise
- [Parcelhub: Tracking](https://web.parcelhub.net/Tracking) – champs du formulaire, mise en page des résultats, anglais uniquement
- [Whistl Despatch: Tracking](https://despatch.whistl.co.uk/tracking) – même suivi sous le nom Whistl
- [Wikipedia: Whistl](https://en.wikipedia.org/wiki/Whistl) – changement de nom depuis TNT Post UK, dernier kilomètre Royal Mail
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – champs lus par Peek, statuts enregistrés, détection de numéros et fréquence de vérification
:::
