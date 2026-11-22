---
title: Suivi Parcelforce Worldwide : statuts, douane et contact
description: Suivi d’un colis Parcelforce Worldwide (Royal Mail Group) : formats de numéros, sens des statuts, options de livraison, frais de douane et service client.
slug: suivi-parcelforce-worldwide
published: 2026-11-22
updated: 2026-11-22
---

Parcelforce Worldwide fait partie de [Royal Mail](carrier:royal-mail) Group : tu suis un colis Parcelforce sur la page Track your item de Royal Mail, ou en collant le numéro dans la case ci-dessus. Le numéro provient de la boutique ou de l’expéditeur, en général dans l’e-mail de confirmation de commande.

## Numéro de suivi Parcelforce : où le trouver

Cherche dans l’e-mail de confirmation de la boutique ou demande-le au vendeur. Introuvable ? Consulte [où trouver ton numéro de suivi](guide:find-tracking-number). Trois formes possibles :

| Numéro (inventé) | Ce que c’est |
| --- | --- |
| `EA482917367GB` | Deux lettres (`E` et une lettre, ou `CP` ou `GI`), neuf chiffres et `GB`, avec un chiffre de contrôle avant `GB`. |
| `PBTR4829173654` | Un numéro de colis à 14 caractères : `PB`, le numéro d’envoi et le rang du colis dans cet envoi. |
| `TR4829173` | Un numéro d’envoi seul : le suivi de Royal Mail demande à la place le numéro propre à chaque colis. |

Le format à 13 caractères est aussi utilisé par Royal Mail (`RR482917367GB` appartient à Royal Mail, pas à Parcelforce), et certains colis express48 portent un numéro Royal Mail. Les deux étant suivis sur la même page, cela change peu de choses pour toi.

## Signification des statuts de suivi Parcelforce

Le suivi Royal Mail affiche ces lignes en anglais. Dans l’ordre habituel :

| Le suivi indique | Ce que ça veut dire pour toi |
| --- | --- |
| `Sender has despatched item` | L’expéditeur a enregistré le colis. Parcelforce ne l’a pas encore en main. |
| `Item Collected` | Enlevé chez l’expéditeur. |
| `Accepted at Parcelshop` | L’expéditeur l’a déposé dans un bureau de poste ou un point relais. |
| `Item received` | Dans le réseau. |
| `Customs charges raised` | Un envoi importé hors Royaume-Uni doit régler TVA, droits de douane ou frais avant livraison. |
| `Despatched to delivery office` | En route vers ton dépôt ou bureau de distribution local. |
| `Received at Delivery Depot` | Arrivé au dépôt local. Il n’est pas encore prêt à être retiré. |
| `Out for delivery` | Chez le chauffeur aujourd’hui. |
| `Delivery attempted` | Le chauffeur n’a pas pu livrer. |
| `Delivered by` | Livré. La ligne poursuit avec le nom du signataire. |
| `Delivered to Neighbour/Reception by` | Remis à un voisin ou à une réception, nommé dans la ligne. |
| `Returned to sender` | En retour vers l’expéditeur. |

Pour les colis envoyés à l’étranger, l’historique continue avec des lignes telles que `Item Leaving the UK` puis `Due to be delivered today` du partenaire de livraison.

## Options de livraison Parcelforce et livraisons manquées

**Avant la livraison.** Dans l’application Royal Mail ou via Royal Mail Customer Services, tu peux choisir un autre jour de livraison pour un colis Parcelforce, demander une remise à un voisin, ou demander au chauffeur de frapper plus fort ou de te laisser plus de temps pour ouvrir. Un lieu sûr se règle aussi auprès de Royal Mail, y compris pour Parcelforce.

**Livraison manquée.** Le suivi indique `Delivery attempted`, ou `Delivered to Neighbour/Reception by` avec le nom de la personne qui l’a réceptionné. Si tu ne sais toujours pas où est le colis, appelle Royal Mail Customer Services (ci-dessous).

## Suivi Parcelforce qui ne se met pas à jour, colis retardé ou perdu

**Aucun scan pour l’instant.** L’expéditeur a peut-être créé le numéro avant de remettre le colis : `Sender has despatched item` signifie que Parcelforce ne l’a pas encore. Vérifie le numéro et réessaie plus tard. Plus dans [pourquoi un suivi ne se met plus à jour](guide:tracking-not-updating).

**En retard, perdu ou abîmé.** Si tu as acheté en ligne, contacte d’abord le vendeur : Citizens Advice souligne qu’on obtient généralement un meilleur résultat ainsi, et tu peux demander un remboursement ou un nouvel envoi. Pour une indemnisation, consulte la procédure de réclamation sur le site de Parcelforce.

**Frais de douane.** Parcelforce fait passer les colis venant de l’étranger en douane et te contacte si une TVA, des droits ou d’autres frais sont dus, avec une facture indiquant le montant exact à payer ([en savoir plus sur la douane](guide:customs)). Le colis est normalement conservé environ 3 semaines ; sans paiement dans ce délai, il retourne à l’expéditeur. Les questions sur la marchandise s’adressent aussi à Parcelforce.

> Un doute sur un e-mail ou un SMS te demandant de payer pour un colis ? Ne clique pas sur les liens et ne donne pas tes coordonnées bancaires. Transfère gratuitement les e-mails frauduleux à report@phishing.gov.uk et les SMS suspects au 7726 (Royaume-Uni).

## Numéro de téléphone et service client Parcelforce

Citizens Advice oriente les clients Parcelforce vers Royal Mail pour les besoins de livraison et les lieux sûrs :

- **Royal Mail Customer Services :** 03457 740 740, du lundi au vendredi de 8 h à 18 h 30, le samedi de 8 h à 15 h et le dimanche de 9 h à 14 h (heure du Royaume-Uni). **Relay UK :** 18001 puis 0345 266 0075.
- **Application Royal Mail :** date de livraison, voisin, lieu sûr et préférences de livraison, y compris pour les colis Parcelforce.

Pour une demande d’indemnisation ou une réclamation, passe par le site web de Parcelforce.

## Suivre un colis Parcelforce avec Peek

[Peek](/) identifie Parcelforce immédiatement pour les numéros du type `EA482917367GB` dotés d’un chiffre de contrôle valide ainsi que pour les numéros `PB` à 14 caractères. Un numéro d’envoi seul n’est pas suivi. Un lien Parcelforce contenant `trackNumber=` fonctionne, tout comme un lien Royal Mail se terminant par `#/tracking-results/` suivi d’un numéro Parcelforce.

Peek consulte Parcelforce sur le site de Royal Mail en l’ouvrant dans un vrai navigateur sur son serveur : chaque vérification prend donc plus de temps et peut échouer lorsque le site bloque les visites automatisées. Peek essaie alors les services de suivi universels, qui contiennent peu d’informations pour Parcelforce.

Peek affiche le statut, chaque scan avec son lieu (sans code postal), le jour de livraison prévu, l’heure de livraison et le pays de destination. Les statuts gardent le libellé anglais de Royal Mail, et chaque ligne de livraison apparaît comme « Livré », sans le nom du signataire. Un scan venant de l’étranger sans fuseau horaire n’est pas horodaté ; ceux qui en portent un s’affichent dans ton fuseau horaire (« Heures dans ton fuseau horaire : » suivi de son nom). Pour certaines destinations, un envoi exporté sous numéro de type `EA482917367GB` peut être suivi auprès de la poste nationale du pays destinataire dès qu’elle enregistre de l’activité.

Tant que tu as le colis ouvert dans Peek — ou le jour de la livraison si les notifications sont activées — Peek vérifie jusqu’à toutes les 10 minutes, et jusqu’à toutes les 5 minutes dès 6 h, heure du Royaume-Uni (ou dès 30 minutes avant l’ouverture du créneau quand Parcelforce en indique un), le jour où Parcelforce scanne le colis « En livraison », tant que le suivi de Parcelforce répond ; sinon, et après deux jours sans nouveauté (12 heures tant qu’il reste sur « Étiquette créée » ou « Pris en charge »), toutes les heures. Via les seuls services de suivi universels, Peek vérifie toutes les heures, toutes les 3 heures après une journée sans scan, et jusqu’à toutes les 15 minutes le jour de la livraison si le colis est ouvert dans Peek ou si les notifications sont activées. Après quatre jours sans nouveau scan sur un colis en route, Peek affiche la date du dernier scan (« Aucune mise à jour depuis … ») et « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger Parcelforce Worldwide. », avec le lien « Demande de l’aide à Parcelforce Worldwide » vers la page de contact Parcelforce. Il place chaque scan sur une carte et peut t’avertir selon tes préférences (« Chaque scan », « Étapes importantes » ou « Livraison seulement »).

## Questions sur le suivi Parcelforce

### Parcelforce fait-il partie de Royal Mail ?

Oui. GOV.UK classe Parcelforce au sein de Royal Mail Group, et ses colis se suivent sur la page Track your item de Royal Mail.

### Pourquoi mon numéro Parcelforce n’est-il pas reconnu ?

Le colis n’est peut-être pas encore arrivé chez Parcelforce, ou le numéro comporte une erreur de saisie. Un numéro d’envoi seul n’est pas suivi : demande à l’expéditeur le numéro `PB` à 14 caractères de chaque colis et réessaie plus tard.

### Combien de temps Parcelforce conserve-t-il un colis avec frais de douane ?

Environ 3 semaines. Si la facture n’est pas réglée d’ici là, le colis retourne à l’expéditeur.

### Puis-je choisir un autre jour pour une livraison Parcelforce ?

Oui, dans l’application Royal Mail ou via Royal Mail Customer Services. Tu peux aussi demander une livraison chez un voisin.

:::sources
- [GOV.UK: Check when you can account for import VAT on your VAT Return](https://www.gov.uk/guidance/check-when-you-can-account-for-import-vat-on-your-vat-return) – Parcelforce fait partie de Royal Mail Group
- [Citizens Advice: Telling delivery companies about your delivery or collection needs](https://www.citizensadvice.org.uk/consumer/post-and-parcels/telling-delivery-companies-about-your-delivery-or-collection-needs/) – appli Royal Mail et Customer Services pour Parcelforce, autre date, voisin, lieu sûr, frapper à la porte, téléphone et horaires
- [Citizens Advice: Claiming compensation from Royal Mail](https://www.citizensadvice.org.uk/consumer/post-and-parcels/claiming-compensation-from-royal-mail/) – numéro Relay UK
- [Citizens Advice: Damaged, lost or delayed post or parcel](https://www.citizensadvice.org.uk/consumer/post-and-parcels/damaged-lost-delayed-post-or-parcel/) – vendeur d’abord, réclamations sur le site du transporteur
- [GOV.UK: Tax and customs for goods sent from abroad: Overview](https://www.gov.uk/goods-sent-from-abroad) – Parcelforce dédouane les marchandises, questions sur les marchandises
- [GOV.UK: Tax and customs for goods sent from abroad: Tax and duty](https://www.gov.uk/goods-sent-from-abroad/tax-and-duty) – facture, environ 3 semaines, retour à l’expéditeur
- [GOV.UK: Avoid and report internet scams and phishing](https://www.gov.uk/report-suspicious-emails-websites-phishing) – 7726, report@phishing.gov.uk
- [UPU: S10 standard, Identification of postal items](https://www.upu.int/UPU/media/upu/files/postalSolutions/programmesAndServices/standards/S10-12.pdf) – chiffre de contrôle avant le code pays
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – ce que Peek lit, statuts et formats de numéros
:::
