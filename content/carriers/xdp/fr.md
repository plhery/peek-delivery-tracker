---
title: Suivi XDP : numéros d’envoi, statuts et livraison
description: Suivi d’un colis XDP au Royaume-Uni : où trouver le numéro d’envoi, pourquoi XDP demande le code postal, sens des statuts et contacts du service client.
slug: suivi-xdp
published: 2026-11-22
updated: 2026-11-22
---

XDP (XDP Express) est un réseau britannique de messagerie et de fret qui livre des articles volumineux et lourds pour des boutiques : meubles, matelas, matériel de jardin ou de sport, souvent avec un ou deux livreurs. Suis un colis sur [la page de suivi de XDP](https://xdp.co.uk/track/) ou dans l’application XDP à l’aide du numéro d’envoi et du code postal de livraison, ou colle le numéro dans la case ci-dessus. Le numéro vient de la boutique qui expédie l’article, pas de XDP.

## Numéro de suivi XDP : le numéro d’envoi

XDP l’appelle le *consignment number* (numéro d’envoi). Sa page de suivi le précise clairement : tu le reçois de la société qui t’expédie l’article via XDP — cherche-le dans l’e-mail d’expédition du magasin ou sur ta page de commande. Les propres SMS et e-mails de XDP arrivent plus tard, une fois le colis en route vers toi.

XDP ne publie pas de format officiel ; ses numéros ont cette allure :

| Forme (inventée) | Ce que c’est |
| --- | --- |
| `ZDBKLM482917` | `ZDB`, trois lettres et six chiffres : un numéro d’envoi XDP. |
| `0ZDBKLM482917` | Le même numéro précédé d’un `0`, qui peut être supprimé. |

Le suivi de XDP demande également le code postal de livraison ou de collecte. Un mauvais code postal et un numéro mal saisi renvoient la même réponse (`Consignment not found for consignment number`) : vérifie donc les deux.

Aucun autre transporteur britannique n’utilise la forme `ZDB`. Un numéro [DPD UK](carrier:dpd-uk) compte 14 chiffres et un bordereau APC Overnight 22 chiffres : si ton e-mail mentionne l’un d’eux, suis-le chez ce transporteur. Plus d’informations dans [formats de numéros de suivi](guide:tracking-number-formats).

## Signification des statuts de suivi XDP

Le suivi, l’application et la FAQ de XDP sont uniquement en anglais. Les numéros de tournée et les horaires varient d’un colis à l’autre.

| Statut XDP | Ce que ça veut dire pour toi |
| --- | --- |
| `Order received` | La boutique a envoyé les détails de commande à XDP, mais XDP n’a pas encore l’article. |
| `At Collection Depot` | XDP a ton article, qui part vers ton dépôt de livraison. |
| `At Delivery Depot` | Arrivé au dépôt chargé de la livraison. |
| `Allocated to Delivery Vehicle - Route 03` | Affecté à une tournée de livraison. |
| `Scheduled for Delivery - Route 11 - ETA: 10:51am to 12:51pm` | Prévu aujourd’hui dans ce créneau de deux heures. |
| `Next stop: On time` | Tu es le prochain arrêt du chauffeur. XDP affiche `On time`, `Late by …` ou `Early by …` par rapport au créneau ; Peek ajoute `Next stop:`. |
| `Delivered` | Livré. |
| `Carded` | Le chauffeur s’est présenté, mais personne n’était là pour réceptionner. |
| `Consignment Split` | Le dépôt n’a reçu qu’une partie de ta commande de la boutique. |
| `Check Address` | Le chauffeur ne trouve pas l’adresse et a besoin de précisions. |
| `Service Fail` | Un événement indépendant de XDP (panne, trafic ou mauvais temps) a retardé la livraison. |
| `Return` | Renvoyé à l’expéditeur : contacte la boutique. |

L’application XDP formule certains statuts différemment (`Delivery scheduled`). Après `Consignment Split`, XDP indique que les articles restants suivent dans les jours suivants. `RETURN LABEL CREATED` lance le retour vers l’expéditeur, et `Damaged` signifie que l’article paraît endommagé (voir ci-dessous).

## Livraison XDP : créneaux horaires, absences et retrait

XDP envoie un créneau de livraison de deux heures le jour même une fois le camion chargé, généralement avant midi (heure du Royaume-Uni). En période chargée, un dépôt peut planifier plus tôt et l’envoyer 24 heures à l’avance ou davantage. Un message « You Are Next » suit par SMS, e-mail et dans l’application. Pour les livraisons à deux personnes (2Man), XDP te contacte d’abord avec une date, puis envoie le créneau de deux heures le jour J. Avec les services 1+ et 2Man, le livreur peut déposer l’article dans la pièce de ton choix, après signature d’une décharge envoyée au préalable par XDP.

Dans l’application XDP, tu peux :

- choisir **Change Delivery Date** pour reprogrammer la livraison ou, dans certains cas, choisir une date ;
- ajouter des instructions de livraison, comme laisser l’article chez un voisin ou sous un porche, dans un abri, un garage ou derrière un portail ;
- laisser des notes pour le chauffeur et voir la preuve de livraison.

XDP avertit qu’en demandant un dépôt « Left Safe », tu assumes l’entière responsabilité de l’article dès sa livraison.

**Livraisons manquées.** `Carded` signifie que le chauffeur n’a trouvé personne. XDP indique tenter trois livraisons avant le retour à l’expéditeur. Si aucun avis n’a été laissé, le chauffeur ne repasse pas le même jour : reprogramme avec Change Delivery Date.

**Aucun point relais ni retrait en dépôt.** XDP ne dispose ni de points relais ni de consignes, et son application précise qu’elle ne propose pas de retrait dans ses dépôts ou hubs. La FAQ te renvoie vers la boutique si tu souhaites retirer l’article.

## Colis XDP non livré, en retard ou abîmé

XDP travaille pour l’expéditeur, pas pour toi. Pour tout problème, son conseil est de suivre d’abord l’article dans l’application ou sur le site, puis de contacter ton fournisseur (la boutique). C’est la boutique qui détient le contrat et dépose toute réclamation.

**En retard ou sans mise à jour.** XDP ne garantit pas la livraison le lendemain, surtout en période de pointe : jours fériés, Black Friday et Cyber Monday. Les chauffeurs ralentis par la circulation n’actualisent pas toujours le créneau. Voir [suivi qui ne se met pas à jour](guide:tracking-not-updating).

**Abîmé.** XDP te demande d’accepter la livraison plutôt que de la refuser et de contacter aussitôt l’expéditeur. Note le dommage sur la preuve de livraison en signant : le guide de procédure de XDP pour les expéditeurs précise qu’une réclamation est rejetée si la signature ne signale aucune réserve, et que des mentions comme « unchecked » ou « unseen » ne comptent pas.

**Perdu.** Les délais de réclamation de l’expéditeur auprès de XDP sont courts : 33 jours à compter de l’expédition pour une perte totale ou partielle et 7 jours à compter de la livraison pour un dommage, week-ends compris. Préviens rapidement la boutique. Si le suivi indique `Delivered` sans rien reçu, vois [livré mais non reçu](guide:delivered-not-received).

**Retourné.** Après `Return`, XDP indique de contacter ton fournisseur.

## Contact et service client XDP

Canaux XDP destinés aux destinataires :

- **Formulaire Delivery Query** sur [la page de contact de XDP](https://xdp.co.uk/contact/). Le formulaire « New Business Enquiry » y est réservé aux ventes.
- **E-mail :** trackingupdates@xdp.co.uk.
- **Chat en direct** sur `xdp.co.uk` et dans l’application XDP ; XDP ne publie pas d’horaires.
- **Formulaire de contact dans l’application**, demandant ton nom, ton code postal, ton e-mail et, en option, le numéro d’envoi.

Le site web de XDP n’affiche aucun numéro de téléphone. Son application mentionne des appels surtaxés à 7 pence la minute sans préciser quel numéro ; les numéros trouvés sur d’autres sites ne sont pas publiés par XDP.

## Suivre un colis XDP avec Peek

[Peek](/) reconnaît immédiatement un numéro `ZDB` (avec ou sans `0` en tête) comme appartenant à XDP. Les liens de `xdp.co.uk` (`/track/` suivi du numéro, ou `track.php?c=`) fonctionnent aussi, mais un code postal inclus dans le lien n’est pas repris.

Peek propose un champ facultatif « Delivery or collection postcode » pour XDP. Sans code postal, XDP n’est pas interrogé du tout et le colis est suivi uniquement via les [services de suivi universels](guide:universal-tracker). Avec le code postal, Peek lit directement la page de suivi de XDP ; le code postal est transmis à XDP et, si nécessaire, aux services de suivi. Un code postal erroné apparaît comme introuvable, exactement comme sur le site de XDP : corrige-le via « Changer ».

Les horaires de XDP ne portant pas de fuseau horaire, Peek les lit à l’heure du Royaume-Uni et les affiche dans ton fuseau horaire (« Heures dans ton fuseau horaire : » suivi de son nom). Peek affiche l’historique XDP (en retirant les messages e-mail/SMS et les doublons entre pièces d’un même envoi), le poids, le nom du service, le nom du destinataire (sous « Destinataire ») et le code postal de livraison, tout en écartant les codes de dépôt ou de chauffeur, la référence client et le nombre de pièces. Il n’y a ni date prévue, ni lieu par scan, ni champ de créneau séparé (hors les créneaux inscrits dans un libellé comme `Scheduled for Delivery - Route 11 - ETA: 10:51am to 12:51pm`).

Avec le code postal, tant que tu as le colis ouvert dans Peek — ou le jour de la livraison si les notifications sont activées — Peek interroge directement XDP jusqu’à toutes les 10 minutes, et jusqu’à toutes les 2 minutes dès 6 h, heure du Royaume-Uni, le jour où le colis passe « En livraison », tant que le suivi XDP répond ; sinon, et après deux jours sans nouveauté (12 heures tant qu’il reste sur « Étiquette créée » ou « Pris en charge »), il vérifie toutes les heures. Sans code postal, via les seuls services de suivi universels, il vérifie toutes les heures, toutes les 3 heures après une journée sans scan, et jusqu’à toutes les 15 minutes le jour de la livraison si le colis est ouvert dans Peek ou si les notifications sont activées. Après quatre jours sans nouveau scan en cours d’acheminement, Peek affiche la date du dernier scan (« Aucune mise à jour depuis … ») et « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger XDP. », avec le lien « Demande de l’aide à XDP » vers la page de contact. Il t’alerte selon ton choix : « Chaque scan », « Étapes importantes » ou « Livraison seulement ».

## Questions sur le suivi XDP

### Pourquoi mon suivi XDP ne fonctionne-t-il pas ?

En général à cause du code postal : le suivi XDP exige le numéro d’envoi et le code postal de livraison ou de collecte, et un mauvais code postal renvoie « Consignment not found ». Vérifie les deux dans l’e-mail de la boutique.

### Combien de temps XDP met-il pour livrer ?

XDP annonce aux expéditeurs 1 à 3 jours pour son service 1Man et 3 à 5 jours pour 1+ et 2Man, sous réserve du transport maritime pour les îles. Le jour même s’accompagne d’un créneau de deux heures.

### Puis-je retirer mon colis dans un dépôt XDP ?

Non. L’application XDP indique qu’aucun retrait n’est proposé dans ses dépôts ou hubs, et la FAQ te renvoie vers la boutique. Tu peux reprogrammer la livraison ou demander une remise à un voisin.

### XDP est-il un vrai transporteur ?

Oui. X D P Limited est immatriculée auprès de Companies House depuis 1995, avec son siège à Curdworth, près de Sutton Coldfield, et livre en Grande-Bretagne continentale, en Irlande du Nord, dans les îles Anglo-Normandes et sur l’île de Wight.

:::sources
- [XDP: Track](https://xdp.co.uk/track/) – numéro d’envoi par l’expéditeur, code postal, messages du suivi
- [XDP: FAQs](https://xdp.co.uk/faqs/) – signification des statuts, contacter le fournisseur, retrait dépôt, reprogrammation dans l’appli
- [XDP: Get In Touch](https://xdp.co.uk/contact/) – formulaire Delivery Query, e-mails
- [XDP: About Us](https://xdp.co.uk/about-us/) – fondée en 1995, couverture, créneau de deux heures, preuve de livraison
- [XDP: Home](https://xdp.co.uk/) – fret volumineux et hors gabarit, services, application
- [XDP: 1Man Delivery](https://xdp.co.uk/services/1man-delivery/) – 1 à 3 jours, notifications, numéro relais
- [XDP: 1+ Delivery](https://xdp.co.uk/services/1-plus-delivery-service/) – 3 à 5 jours, pièce de choix, You Are Next, chat en direct et e-mail
- [XDP: 2Man Delivery](https://xdp.co.uk/services/2-man-delivery/) – date convenue à l’avance, créneau de deux heures
- [XDP: App (web version)](https://app.xdp.co.uk/) – créneau de livraison, trois tentatives, Change Delivery Date, left safe, pas de retrait dépôt, dommages, retours, appels, formulaire de contact
- [XDP: Terms and Conditions of Carriage](https://xdp.co.uk/wp-content/uploads/2024/02/XDP-TCFinal.2.24.V1.pdf) – pas de garantie lendemain, périodes de pointe, délais de réclamation
- [XDP: Procedure Guide](https://xdp.co.uk/wp-content/uploads/2026/03/Procedure-Guide-V.5.pdf) – 33 et 7 jours, mention des dommages sur la preuve de livraison
- [XDP: Tracking (track.php)](https://www.xdp.co.uk/track.php) – code postal et messages d’envoi introuvable
- [Companies House: X D P LIMITED](https://find-and-update.company-information.service.gov.uk/company/03139661) – immatriculation, siège social
- [Apple App Store: XDP](https://apps.apple.com/gb/app/xdp/id1485292140) – application XDP
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – ce que Peek lit et affiche
:::
