---
title: Suivi Allegro One : One Box, One Punkt et One Kurier
description: Suivre un colis Allegro One en Pologne : formes du numéro, sens des statuts anglais, retrait One Box ou One Punkt, délais, réclamations et service client.
slug: suivi-allegro-one
published: 2026-11-21
updated: 2026-11-21
---

Allegro One est le réseau de livraison propre d'Allegro, la place de marché polonaise exploitée par Allegro sp. z o.o. (établie à Poznań) : consignes automatiques Allegro One Box, points de retrait Allegro One Punkt et livraison à domicile Allegro One Kurier, réunis dans Allegro Delivery en Pologne. Suis ton colis sur allegro.pl ou dans l'application Allegro sous *Moje przesyłki* (Mes envois), sur la page de suivi d'Allegro Delivery ou en collant le numéro ci-dessus. Le numéro figure dans le détail de ton achat, dans l'application et dans l'e-mail envoyé dès son ajout.

## Où trouver ton numéro Allegro One

Allegro indique le transporteur, le numéro et le statut dans :

- ***Moje przesyłki*** sur allegro.pl ou dans l'application, de l'attente d'expédition jusqu'à la réception.
- ***Moje zakupy*** (Mes achats), dans les détails de la commande.
- **L'e-mail** envoyé dès que le vendeur renseigne le numéro.

Voici les formes que Peek peut suivre chez Allegro One (caractères inventés) :

| Format (inventé) | Ce que c'est |
| --- | --- |
| `A0K4829105` | Allegro One : `A0` et 8 lettres ou chiffres (10 caractères). |
| `AD0K4829105736491` | Allegro Delivery : `AD0` et 14 chiffres ou consonnes (17 caractères), rattaché aussi à Allegro One. |
| `ADK4829105` | Ancien format Allegro Delivery, encore sur certains retours : `AD` et 8 lettres ou chiffres. |
| `ALE482910573649` | Ancien format Allegro One : `ALE` et 12 chiffres. |

Un code `AD` suivi de 12 chiffres désigne une commande d'enlèvement coursier chez le vendeur, pas un colis. Pas encore de numéro ? Le vendeur l'ajoute à la commande, sauf quand Allegro le reçoit automatiquement d'un transporteur intégré. Voir [où trouver son numéro de suivi](guide:find-tracking-number).

Dans Allegro Delivery, des partenaires acheminent aussi les colis : DPD vers Allegro One Punkt ; DPD, [DHL](carrier:dhl) et ORLEN Paczka vers Allegro One Box (ainsi qu'One Kurier dans certaines villes), outre les options Allegro DPD, Allegro DHL et Allegro ORLEN Paczka. Tous portent un numéro Allegro et se suivent chez Allegro avec les mêmes libellés. Un colis envoyé hors d'Allegro Delivery, sur le contrat propre du vendeur avec [InPost](carrier:inpost), DPD Polska, ORLEN Paczka, DHL ou Poczta Polska, porte le numéro de ce transporteur et se suit chez lui. Si un transporteur n'est pas intégré à Allegro, tu le suis sur le site du transporteur.

## Statuts de suivi Allegro One : signification

Le suivi d'Allegro existe en polonais et en anglais. En français, Peek lit les libellés anglais tels qu'Allegro les publie :

| Statut Allegro One | Ce que ça veut dire | Étape dans Peek |
| --- | --- | --- |
| `Parcel has been prepared by the sender` | Étiquette créée ; Allegro One n'a pas encore le colis. | « Étiquette créée » |
| `Parcel has been dispatched` | Colis remis (`Parcel has been dispatched at point` en point, `Parcel has been picked up by the courier` par coursier). | « Pris en charge » |
| `Parcel has been accepted at the sorting center` | Arrivé au centre de tri (`Dispatched from sorting center` à sa sortie). | « En transit » |
| `Parcel has been dispatched to its destination point` | En route vers la consigne, le point ou l'agence qui te livre. | « En transit » |
| `Parcel has been released for delivery` | En tournée de livraison, y compris vers une consigne One Box. | « En livraison » |
| `No space in the parcel locker` | Consigne pleine : échec de dépose, le coursier reprend le colis. | « Tentative de livraison » |
| `Notice` | Personne à l'adresse ; un avis (*Awizo*) a été envoyé. | « Tentative de livraison » |
| `Parcel is awaiting pick-up` | Disponible dans la One Box ou au One Punkt. | « Prêt à être retiré » |
| `Parcel is awaiting pick-up — pick-up deadline is about to expire` | Ton délai de retrait expire bientôt (`Time for pick-up has been extended` s'il est prolongé). | « Prêt à être retiré » |
| `Time for parcel pick-up has expired` | Non retiré à temps ; retour vers le vendeur. | « Problème signalé » |
| `Parcel has been returned to the sender` | En retour vers le vendeur (« Problème signalé » tant que d'autres scans suivent, sinon « Retourné à l’expéditeur »). | « Problème signalé » / « Retourné à l’expéditeur » |
| `Parcel has been delivered` | Livré à domicile ou retiré par toi en One Box ou One Punkt. | « Livré » |

Il n'existe pas de statut séparé après un retrait en consigne : `Parcel has been delivered` s'affiche aussi dès que tu récupères le colis. `Parcel may be delivered with delay` et `Parcel delivery time has been arranged with the recipient` sont de simples avis (« En transit »), `Parcel has been redirected to a different point` suit ton choix d'un autre point, et même après `No parcel` ou `Parcel has been lost` des scans ultérieurs peuvent retrouver le colis.

## Livraison en Pologne : One Box, One Punkt et One Kurier

Allegro Delivery livre uniquement sur le territoire polonais des biens vendus sur Allegro.

**Délais et dimensions.** En coursier *Next Day* (*Dostawa jutro*), Allegro annonce la livraison le jour ouvrable suivant ou le samedi non férié après l'expédition (délai garanti : 3 jours ouvrables). Certains vendeurs proposent aussi le *Same Day* (*Dostawa Dzisiaj*) vers une One Box de la ville de leur entrepôt, avec retrait entre 16 h 00 et 22 h 00 le jour même (garanti en 2 jours ouvrables). En One Box et One Punkt, la limite est de 20 kg (réel ou volumétrique) et 64 × 41 × 38 cm (59 × 41 × 38 cm en consigne DPD Pickup) ; avec One Kurier, 31,5 kg, 120 cm de longueur et 220 cm pour la somme des trois côtés.

**Avis et retrait.** À l'arrivée en consigne ou en point, Allegro ou son partenaire t'informe par e-mail, SMS ou notification dans l'application Allegro du lieu et de l'échéance. Devant une **One Box**, entre le code de retrait seul ou avec ton téléphone, scanne le code QR reçu par SMS ou e-mail, ou ouvre le casier avec l'application Allegro. Dans un **One Punkt**, donne le numéro de colis et le code de retrait (ou une pièce d'identité en cas d'impossibilité technique).

**Délai de garde.** Le guide Allegro annonce 2 jours calendaires (48 heures) en Allegro One Box et 2 jours ouvrables en Allegro One Punkt (48 heures hors dimanches et jours fériés légaux en point selon le règlement Allegro Delivery ; 3 jours ouvrables chez ORLEN Paczka et 2 jours ouvrables chez DHL et DPD). Dans certains automates et points, Allegro permet de prolonger ce délai. Passé l'échéance, le colis repart chez le vendeur. Voir [colis non retiré](guide:uncollected-parcel).

**Consigne pleine.** Allegro peut déposer le colis dans une consigne de remplacement à moins de 3 km à vol d'oiseau (6 km en zone rurale) et t'en prévenir : il y reste jusqu'à 24 heures avant d'être transféré dans la consigne choisie pour 48 heures.

**One Kurier, redirection et contre-remboursement.** One Kurier livre à domicile dans certaines villes. Si le gabarit le permet, tu peux rediriger une fois un colis coursier (hors contre-remboursement) vers une consigne ou un point participant de la même agence, sur allegro.pl ou dans l'application avant la mise en livraison (+1 jour ouvrable). En cas d'absence (*Awizo*, `Notice`), tu as 48 heures pour retirer le colis au lieu indiqué ou convenir d'une nouvelle livraison payante. Le paiement à la livraison (*Pobranie*, +4,99 PLN, plafond 6 000 PLN) se règle en espèces, BLIK ou carte (VISA, MasterCard, Maestro), sauf contrainte technique limitant aux espèces. Voir [livraison manquée](guide:missed-delivery).

## Colis Allegro One en retard, perdu ou abîmé

**Suivi figé.** Vérifie *Moje przesyłki* ; sans numéro, demande-le au vendeur et lis [pourquoi un suivi ne bouge plus](guide:tracking-not-updating).

**Le vendeur n'aide pas.** Depuis le 28 octobre 2026, signale un *problem z zakupem* (problème lié à l'achat) entre une heure et deux ans après le paiement (sur Allegro Lokalnie et avec *Magazyn Allegro*, ouvre toujours une *Dyskusja*). Le vendeur a 24 heures pour répondre au premier message (hors week-ends et jours fériés) et 7 jours pour régler un retard, une perte ou un article manquant. Clique sur *Pomoc Allegro* pour faire intervenir Allegro ; sans solution au bout de 7 jours, Allegro te propose *Allegro Ochrona Kupujących* (Protection des acheteurs).

**Livré mais absent.** Demande à ton entourage, aux voisins ou à l'accueil, puis consulte [colis livré mais non reçu](guide:delivered-not-received).

**Réclamations.** Allegro traite les réclamations d'Allegro Delivery en ligne (connecté au compte ou après vérification des données du colis) ou selon les voies du droit postal polonais. Un colis est réputé perdu sans livraison ni avis sous 30 jours suivant l'expédition. Le destinataire peut réclamer si le colis lui a été livré ou si le vendeur lui a cédé son droit. À réception d'un colis abîmé, refuse-le ou fais établir un constat (*Protokół szkody*). Les dommages non visibles de l'extérieur se déclarent sous 7 jours après acceptation ; ailleurs, tu as jusqu'à 12 mois dès l'expédition. Allegro répond d'ordinaire sous 7 jours (maximum 30 jours), avec appel possible sous 14 jours et recours amiable devant le président de l'UKE. Voir [colis perdu](guide:lost-parcel) et [colis endommagé](guide:damaged-parcel).

## Contact et service client Allegro One

Allegro assure le service client pour tous les envois Allegro Delivery :

- **Réclamation livraison :** auprès d'Allegro, comme ci-dessus.
- **One Box bloquée :** appelle l'assistance technique dont le numéro figure sur la consigne.
- **Colis en retard :** signale un *problem z zakupem* au vendeur et utilise *Pomoc Allegro*.

## Suivre Allegro One avec Peek

[Peek](/) reconnaît aussitôt Allegro One pour `A0` ou `AD` suivis de 8 lettres ou chiffres et pour le format `AD0` à 17 caractères (ainsi que les liens allegro.pl avec `?numer=`), sans code postal ni compte. Pour `ALE` et 12 chiffres, touche « Changer » (ou « Changer de transporteur ») et choisis Allegro One sous « Tous les transporteurs » ; pour une forme non émise (comme `AD` et 12 chiffres), Peek signale qu'il « n’émet pas de numéros comme celui-ci ».

En français, Peek lit les statuts anglais d'Allegro et affiche chaque scan (horodaté en UTC) dans ton fuseau horaire ; sur peektracker.com et sur iPhone et Android, l'historique se termine par « Heures dans ton fuseau horaire : » et le nom du fuseau. Allegro ne donnant ni lieu, ni nom de consigne, ni date prévue, ni poids, ni expéditeur, ni partenaire, rien ne s'affiche sur la carte. Sans historique, Peek affiche « En attente de Allegro One », puis au bout de deux jours sans réponse « Après deux jours, le transporteur ne connaît toujours pas ce numéro. Vérifie-le, choisis un autre transporteur ou supprime le colis. » (vérification quotidienne, puis hebdomadaire après une semaine).

Colis ouvert à l'écran ou le jour de la livraison avec notifications activées, Peek vérifie jusqu'à toutes les 10 minutes, et toutes les 2 minutes dès 6 h (heure de Varsovie) une fois le colis en livraison tant qu'Allegro répond directement ; sinon, et après deux jours sans nouvelles (12 h à « Étiquette créée » ou « Pris en charge »), toutes les heures. Via des services de suivi, c'est toutes les heures (3 h après un jour calme) et jusqu'à toutes les 15 minutes le jour de livraison si le colis est ouvert ou les notifications actives. Notifications au choix : « Chaque scan », « Étapes importantes » ou « Livraison seulement ».

## Questions sur le suivi Allegro One

### Qu'est-ce qu'Allegro One Box ?

La consigne automatique d'Allegro en Pologne, aux côtés des points One Punkt et des coursiers One Kurier. Les colis Allegro Delivery y sont livrés par Allegro One ou par DPD, DHL et ORLEN Paczka.

### Combien de temps un colis attend-il en Allegro One Box ?

2 jours calendaires (48 heures), prolongeables dans certaines consignes, et 2 jours ouvrables en One Punkt. Passé ce délai, il repart chez le vendeur.

### Combien coûte la livraison Allegro One ?

Le vendeur fixe le tarif dans son offre (y compris 0 PLN). Avec Allegro Smart! aux conditions actuelles, la livraison est gratuite dès 49,90 PLN d'achats chez un même vendeur.

### Quelle taille maximale accepte Allegro One Box ?

Jusqu'à 20 kg et 64 × 41 × 38 cm en One Box et One Punkt ; jusqu'à 31,5 kg, 120 cm de long et 220 cm au total avec One Kurier.

:::sources
- [Allegro : The Allegro Delivery program — information for sellers](https://help.allegro.com/en/sell/a/the-allegro-delivery-program-information-for-sellers-MR7exl1wYS4) – options, partenaires, Same Day (16 h–22 h), dimensions, contre-remboursement, garde, réclamations et assistance consigne
- [Allegro : Program dostaw Allegro Delivery – informacje dla sprzedających](https://help.allegro.com/pl/sell/a/program-dostaw-allegro-delivery-informacje-dla-sprzedajacych-9gzevkzEGs1) – règles Allegro Delivery en polonais et tarifs à 0 PLN
- [Allegro : How to correctly dispatch parcels with orders](https://help.allegro.com/en/sell/a/how-to-correctly-dispatch-parcels-with-orders-a-guide-for-sellers-GRv326wwXF1) – où voir le colis, numéros automatiques et transporteurs hors intégration
- [Allegro : Regulamin Allegro](https://policies.allegro.com/doc/f86a1a793dc4f5bc4b695b0ff0d54d974badab4169f5aaaddad89585c2c90690) – e-mail d'expédition avec le transporteur et le numéro
- [Allegro : Regulamin usługi Allegro Delivery](https://policies.allegro.com/doc/e5cf7a904b3b08d908db62830a8bdac17b22cf354baf748e7853adff00fea84c) – Allegro sp. z o.o., service en Pologne, Next Day / Same Day, retrait, consigne de remplacement, redirection, Awizo, constat d'avarie, perte à 30 jours, réclamations et UKE
- [Allegro : How to solve problems with purchases in the Messages tab](https://help.allegro.com/en/sell/a/how-to-solve-problems-with-purchases-in-the-messages-tab-in-the-sales-center-or-in-the-message-center-Bv0zrVqDVIX) – problèmes liés à l'achat depuis le 28 octobre 2026, délais 24 h / 7 j et Protection des acheteurs
- [Allegro : Jak rozwiązywać problemy z zakupem w zakładce Wiadomości](https://help.allegro.com/pl/sell/a/jak-rozwiazywac-problemy-z-zakupem-w-zakladce-wiadomosci-w-sales-center-lub-w-centrum-wiadomosci-x5ZVrvzXPuP) – bouton Pomoc Allegro
- [Allegro : We changed the Allegro Smart! Terms & Conditions for buyers](https://help.allegro.com/en/sell/a/we-changed-the-allegro-smart-terms-conditions-for-buyers-wwkzRYxRdio) – livraison gratuite dès 49,90 PLN chez un vendeur
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formats reconnus, statuts et ce que Peek affiche
:::
