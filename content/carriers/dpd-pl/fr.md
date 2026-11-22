---
title: Suivi DPD Pologne (DPD Polska) : statuts et points Pickup
description: Suivre un colis DPD Pologne avec son numéro à 14 caractères : sens des statuts anglais, relais DPD Pickup, réclamations et service client.
slug: dpd-poland-tracking
published: 2026-11-21
updated: 2026-11-21
---

DPD Poland (DPD Polska), basée à Varsovie, est la filiale polonaise du groupe Geopost, détenu par [La Poste](carrier:la-poste) française. Vous pouvez suivre un envoi sur la page officielle [Track & Trace de DPD Pologne](https://tracktrace.dpd.com.pl/EN/findParcel) à l'aide de son numéro de colis à 14 caractères, ou coller ce code directement dans le champ ci-dessus. Le marchand transmet habituellement cette référence dans l'e-mail d'expédition ou dans le récapitulatif de commande.

## Numéro de suivi DPD Pologne : format et exemples

DPD Pologne parle d'un numéro de colis à 14 caractères : en pratique, 13 chiffres suivis d'une lettre pour les bordereaux polonais, ou 14 chiffres sur le réseau européen DPD. L'application DPD Mobile affiche aussi automatiquement les colis rattachés au numéro de téléphone ou à l'adresse e-mail de votre compte, sans saisie manuelle. Rien reçu ? Consultez [comment trouver un numéro de suivi](guide:find-tracking-number).

| Exemple (inventé) | À quoi correspond ce code |
| --- | --- |
| `4829105736491A` | 13 chiffres et une lettre : numéro de colis propre à DPD Pologne. |
| `13482910573649` | 14 chiffres commençant par `13` : numéro de colis DPD Pologne au format numérique. |
| `482910573` | Code d'envoi à 9 caractères (*kod nadania*) : le suivi de DPD l'accepte, mais Peek ne le suit pas. |

Un code à 14 chiffres peut aussi appartenir à [DPD France](carrier:dpd-fr), [Chronopost](carrier:chronopost), [Mondial Relay](carrier:mondial-relay), [DPD Germany](carrier:dpd-de), [DPD Switzerland](carrier:dpd-ch) ou [DPD UK](carrier:dpd-uk) : vérifiez quel transporteur figure dans le message du vendeur. Si le site DPD affiche `There is no trace for this parcel`, la référence comporte une erreur ou n'est pas encore injectée dans le système : DPD conseille de réessayer le lendemain matin du dépôt.

## Statuts DPD Pologne : signification des messages en anglais

En dehors du polonais, Peek interroge la version anglaise du portail de DPD Pologne. Voici les principaux messages rencontrés dans l'ordre du parcours :

| DPD Pologne affiche | Ce que cela signifie pour votre colis |
| --- | --- |
| `Registered parcel data, parcel not dispatched yet` | Données annoncées (`« Étiquette créée »`) : DPD n'a pas encore reçu le colis (de même pour `Collection request created` et `Collection request dispatched to Courier`). |
| `Parcel dropped-off in pickup point` | L'expéditeur a déposé le colis dans un relais ou automate DPD Pickup (`« Pris en charge »`). |
| `Parcel collected by courier` | Pris en charge chez l'expéditeur par le chauffeur, ou transmis en cours de route après un premier scan en dépôt. |
| `Parcel received by DPD depot` | Entrée dans une agence DPD (`« En transit »` ; `Parcel stored in depot` signale une courte attente en dépôt, vue avant l'acheminement vers un point relais). |
| `Sent outside Poland` | Départ hors de Pologne vers la filiale Geopost du pays destinataire qui assurera la livraison. |
| `Parcel dispatched to be delivered` | En tournée avec le chauffeur (`« En livraison »`). |
| `Parcel not delivered - recipient not available` | Passage infructueux en votre absence (`« Tentative de livraison »` ; `wrong address` indique une adresse incomplète ou erronée). |
| `Parcel dispatched to be collected by pickup point` | En route vers le point relais ou la consigne qui le conservera. |
| `Parcel collected by pickup point` | Arrivé au point relais : vous pouvez aller le retirer (`« Prêt à être retiré »`). |
| `The Parcel has been placed in the DPD Pickup Station` | Déposé dans un automate DPD Pickup (`« Prêt à être retiré »`, tout comme `To be collected personally`). |
| `Waiting for pickup timeout` | Délai de garde expiré sans retrait (`« Problème signalé »`), le colis repart vers l'expéditeur. |
| `Parcel return` | Retour vers l'expéditeur sous un nouveau numéro (`« Retourné à l’expéditeur »`). |
| `Parcel delivered` | Remis au destinataire (`« Livré »`). |

Attention au faux ami anglais *collected* : dans `Parcel collected by pickup point`, c'est le commerçant relais qui a réceptionné votre colis auprès du chauffeur, pas un tiers qui l'aurait retiré à votre place. Le message `Parcel not delivered - recipient resigned` signifie que le colis a été refusé (retour aux frais de l'expéditeur). Quant à `Mail notification`, `SMS notification` et `Sent notification`, ce sont de simples avis envoyés au destinataire sans déplacement du colis.

## Livraison DPD Pologne, réseau DPD Pickup et expéditions vers l'Europe

**Notifications et modifications.** En Pologne, le délai standard est le lendemain ouvré (garanti sous 3 jours ouvrés en régime intérieur ; le samedi uniquement si l'option *Sobota* a été souscrite). DPD annonce le créneau par e-mail ou SMS (DPD Predict) et affiche le livreur sur une carte ainsi que son téléphone dans DPD Mobile le jour de la tournée. Dès la prise en charge, DPD Mobile ou [Moja Paczka](https://mojapaczka.dpd.com.pl/login) (avec le numéro de colis et un code PIN) permettent de décaler la date jusqu'à 3 jours ouvrés, modifier l'adresse, désigner un voisin ou un lieu sûr, rediriger vers un relais DPD Pickup ou refuser l'envoi. Les commandes de certains expéditeurs (dont Temu, AliExpress et Shein) ne sont modifiables que vers un point DPD Pickup situé sur la tournée du livreur.

**Points et consignes DPD Pickup.** Le réseau compte plus de 35 000 points et consignes en Pologne (dont plus de 12 500 automates et des enseignes comme Żabka, Lidl ou Auchan). Le colis y reste **2 jours ouvrés complets**, sans prolongation possible avant retour à l'expéditeur. Le retrait en consigne s'effectue via DPD Mobile ou en tapant le code reçu par e-mail sur le clavier (un proche peut utiliser ce code si aucun document n'est à signer). Si le relais choisi est saturé (`Not delivered to pickup point - overloaded`), DPD oriente le colis vers l'un des points actifs les plus proches et vous prévient. Plus de conseils dans [livraison manquée](guide:missed-delivery).

**Colis partis de Pologne vers la France, la Belgique ou la Suisse.** Après `Sent outside Poland`, le réseau DPD du pays d'arrivée livre le colis, et le suivi de DPD Pologne continue d'afficher ses scans jusqu'à la livraison. La Pologne faisant partie de l'union douanière de l'UE, aucun droit de douane ne s'applique vers la France métropolitaine, la Belgique ou le Luxembourg (les départements d'outre-mer français restent hors zone TVA de l'UE). En **Suisse**, hors UE, l'OFDF (BAZG) applique 8,1 % de TVA à l'importation (2,6 % au taux réduit), mais ne perçoit pas les montants de TVA ou de droits inférieurs à 5 CHF ; voyez [colis bloqué en douane](guide:customs).

## Colis DPD Pologne bloqué, perdu ou endommagé

Le contrat de transport lie DPD à l'expéditeur (la boutique), mais le destinataire consommateur habilité à disposer du colis peut aussi déposer réclamation selon le règlement DPD :

- **Suivi figé ou perte** : DPD considère un envoi comme perdu s'il n'arrive pas dans les **30 jours** suivant l'échéance prévue du service. D'ici là, contactez le vendeur et consultez [suivi qui ne se met plus à jour](guide:tracking-not-updating) ainsi que [colis perdu](guide:lost-parcel).
- **Avarie** : en cas de dommage visible à la remise, établissez un constat le jour même avec le livreur ou sur le [protocole de dommage en ligne](https://protokol.dpd.com.pl/) avec photos ; les avaries non apparentes doivent être signalées dans les **7 jours** suivant la réception. Ce constat ne vaut pas réclamation : il faut ensuite soumettre le formulaire dans l'espace *Moje DPD* sur dpd.com.pl.

DPD statue sous **30 jours calendaires** à réception d'un dossier complet. Pour un service relevant du droit postal polonais, un consommateur peut saisir le président de l'UKE (régulateur polonais des communications). Si le suivi indique livré sans colis en main, voyez [colis livré mais non reçu](guide:delivered-not-received).

## Contacter DPD Pologne

- **Contact Center** : `801 400 373` ou `22 577 55 55` (`+48 22 577 55 55` depuis l'étranger, au tarif de votre opérateur), du lundi au vendredi de 9 h 00 à 17 h 00 et le samedi de 8 h 00 à 14 h 00 (heure polonaise ; l'assistance en langue étrangère n'est pas garantie).
- **Assistance technique automates DPD Pickup** : `+48 22 571 52 52`, du lundi au vendredi de 8 h 00 à 20 h 00 et le samedi de 8 h 00 à 14 h 00.
- **E-mail et chat** : aucun courriel service client ni clavardage ne figure sur les pages de contact de DPD Pologne.

> DPD Pologne met en garde contre les SMS et e-mails frauduleux réclamant un supplément (surpoids, erreur d'adresse) : DPD Pologne n'envoie jamais de lien pour payer un supplément par SMS ou par e-mail. En cas de clic, changez vos mots de passe, prévenez votre banque et signalez l'incident au Contact Center.

## Suivre un colis DPD Pologne avec Peek

[Peek](/) reconnaît immédiatement DPD Poland devant 13 chiffres suivis d'une lettre (`4829105736491A`). Pour 14 chiffres commençant par `13` (`13482910573649`), Peek interroge les transporteurs compatibles en commençant par DPD Poland et affiche `« DPD Poland a ce colis. »` dès que le portail confirme la référence ; pour les autres numéros à 14 chiffres, sélectionnez DPD Poland via `« Changer »` ou `« Changer de transporteur »`. Les liens `tracktrace.dpd.com.pl` ou `tt.dpd.com.pl` (`p1=`) ainsi que `dpd.com/pl` (`parcelNumber=`) sont acceptés, contrairement aux codes d'envoi à 9 caractères (`« n’émet pas de numéros comme celui-ci »`).

Peek lit le suivi anglais de DPD Pologne et restitue le statut, l'historique et l'heure de remise. Toutes les lignes, l'enregistrement et les scans à l'étranger compris, sont lues sur le fuseau de Varsovie (`Europe/Warsaw`) et converties dans le vôtre (`« Heures dans ton fuseau horaire : … »`). Le portail ne donne ni ville, ni date de livraison estimée, ni expéditeur, ni poids, et Peek écarte le nom du destinataire, l'adresse du relais et le nombre de colis. Après `Sent outside Poland`, Peek continue de lire le suivi de DPD Pologne, qui montre les scans du réseau DPD du pays d'arrivée jusqu'à la livraison, sans interroger le site de suivi de cette filiale. Il ne récupère pas le nouveau numéro créé lors d'un `Parcel return`.

Tant que la fiche est ouverte à l'écran, ou le jour de livraison avec les notifications activées, Peek interroge le suivi direct jusqu'à toutes les 10 minutes, et jusqu'à toutes les 2 minutes lorsque le colis passe `« En livraison »` (ce jour-là dès 6 h heure locale tant que le portail DPD Pologne répond directement) ; sinon, et après deux jours sans mouvement (12 heures tant qu'il reste à `« Étiquette créée »` ou `« Pris en charge »`), la vérification est horaire. Via les services de suivi, elle atteint jusqu'à 15 minutes ce jour-là `« En livraison »` dans les mêmes conditions, sinon toutes les heures, ou toutes les 3 heures après une journée sans nouveauté. Vous pouvez choisir les notifications `« Chaque scan »`, `« Étapes importantes »` ou `« Livraison seulement »`.

## Questions fréquentes sur le suivi DPD Pologne

### Combien de temps DPD Pologne garde-t-il un colis en point ou automate DPD Pickup ?

Deux jours ouvrés complets, sans possibilité de prolongation. L'heure limite figure dans le message envoyé par DPD et dans l'application DPD Mobile ; au-delà, le colis repart vers l'expéditeur.

### Que veut dire `Parcel collected by pickup point` ?

Cela signifie que le point relais a bien réceptionné votre colis auprès du chauffeur DPD : il est prêt à être retiré (`« Prêt à être retiré »`), et non retiré par quelqu'un d'autre.

### Faut-il un code PIN pour suivre un colis DPD Pologne ?

Non : le suivi sur `tracktrace.dpd.com.pl` et dans Peek ne demande que le numéro à 14 caractères. Le code PIN ne sert que sur `mojapaczka.dpd.com.pl` pour modifier vos options de livraison.

### Comment suivre un colis DPD Pologne après `Sent outside Poland` ?

Rien à faire : Peek continue de lire le suivi de DPD Pologne, qui affiche les scans du réseau DPD du pays d'arrivée jusqu'à la livraison. Peek n'interroge pas le site de suivi de la filiale DPD de ce pays.

:::sources
- [DPD Polska : FAQ (polonais)](https://www.dpd.com/pl/pl/najczesciej-zadawane-pytania-faq/) – format à 14 caractères, délais de 1 à 3 jours ouvrés, modifications, garde de 2 jours ouvrés
- [DPD Polska : FAQs in English](https://www.dpd.com/pl/en/frequently-asked-questions-faq/) – restrictions Temu/AliExpress/Shein, assistance automates +48 22 571 52 52
- [DPD Polska : Dział obsługi klienta](https://www.dpd.com/pl/pl/o-dpd/kontakt-2/dzial-obslugi-klienta/) – numéros 801 400 373 et 22 577 55 55 et horaires
- [DPD Polska : Track the parcel](https://tracktrace.dpd.com.pl/EN/findParcel) – recherche sans PIN et message d'absence de trace
- [DPD Polska : Moja Paczka](https://mojapaczka.dpd.com.pl/login) – gestion de la livraison avec numéro de colis et PIN
- [DPD Polska : Receive your parcel](https://www.dpd.com/pl/en/receive-your-parcel/) – notifications e-mail/SMS DPD Predict
- [DPD Polska : DPD Pickup](https://www.dpd.com/pl/pl/dpd-pickup/) – plus de 35 000 points et automates (Żabka, Lidl, Auchan)
- [DPD Polska : DPD Pickup parcel lockers](https://www.dpd.com/pl/en/dpd-polska-parcel-lockers/) – code de retrait sur clavier
- [DPD Polska : Submit a claim](https://www.dpd.com/pl/en/moje-dpd/submit-a-claim-at-dpd/) – perte après 30 jours, décision de réclamation sous 30 jours
- [DPD Polska : Elektroniczny protokół szkody](https://protokol.dpd.com.pl/) – déclaration d'avarie en ligne
- [DPD Polska : Fałszywe SMS-y i e-maile](https://www.dpd.com/pl/pl/o-dpd/falszywe-maile/) – alerte contre les faux liens de paiement
- [DPD Polska : O DPD Polska](https://www.dpd.com/pl/pl/o-dpd/o-dpd-polska/) – appartenance à Geopost et La Poste
- [DPD Polska : Szczegółowe Warunki Świadczenia Usług](https://www.dpd.com/wp-content/uploads/sites/260/2026/08/SWSU_01032026_PL.pdf) – option samedi et redirection en cas de point saturé
- [DPD Polska : Regulamin w obrocie krajowym](https://www.dpd.com/wp-content/uploads/sites/260/2025/01/RUK_01.01.2024PL.pdf) – ayants droit à réclamation, refus, avaries non apparentes sous 7 jours, médiation UKE
- [Union européenne : L’union douanière de l’UE en action](https://european-union.europa.eu/priorities-and-actions/actions-topic/customs_fr) – circulation sans droits de douane entre États membres
- [Commission européenne : Territorial scope of EU VAT and customs](https://taxation-customs.ec.europa.eu/taxation/vat/vat-directive/how-does-vat-work/territorial-scope_en) – statut fiscal des départements d'outre-mer
- [OFDF (BAZG) : Réception de lettres et de paquets](https://www.bazg.admin.ch/fr/reception-de-lettres-et-paquets) – TVA suisse 8,1 % / 2,6 % et franchise de 5 CHF
- [Universal Parcel Scraper : DPD Poland](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/dpd-pl) – détection des numéros, fuseau horaire et correspondance des statuts
:::
