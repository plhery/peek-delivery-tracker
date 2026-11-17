---
title: Suivi colis BRT : numéro, statuts et livraison d’Italie
description: Suivre un colis BRT venu d’Italie : où trouver le BRTcode, sens des statuts anglais, relais par DPD en France, Belgique et Suisse, retrait et contact.
slug: brt-suivi
published: 2026-11-17
updated: 2026-11-17
---

BRT est un transporteur express italien qui appartient, comme DPD et [Chronopost](carrier:chronopost), au groupe Geopost. Tu suis un colis depuis le lien reçu par SMS ou e-mail de BRT, sur [brt.it](https://www.brt.it/en/) ou en collant ton numéro ci-dessus. BRT envoie ce numéro – le *BRTcode* – dès qu’il reçoit le colis et que l’expéditeur lui a transmis tes coordonnées.

## Numéro de suivi BRT : où le trouver, formats et vrais messages

Le *BRTcode* figure dans les SMS et e-mails de BRT ainsi que sur l’avis de passage laissé par le livreur. Rien reçu ? Regarde l’e-mail d’expédition de la boutique ou consulte [retrouver son numéro de suivi](guide:find-tracking-number).

Si le vendeur transmet ton téléphone ou ton e-mail, BRT envoie des messages authentiques avec le *BRTcode* à la prise en charge, puis lors d’un passage manqué, d’une mise en instance (*giacenza*) ou d’une arrivée en *BRT-fermopoint* ou *BRT Locker*. Le 16 avril 2026, BRT a toutefois publié une [alerte au phishing](https://www.brt.it/en/news/2026/04/16/alert-phishing-sms-email/) visant de faux avis de non-livraison et de fausses factures contenant des logiciels malveillants : vérifie l’adresse de l’expéditeur, repère les fautes ou appels urgents à cliquer et, en cas de doute, saisis ton *BRTcode* directement sur `brt.it` ou `vas.brt.it` sans ouvrir liens ni pièces jointes (voir [reconnaître les faux SMS de livraison](guide:delivery-scam-texts)).

Formats reconnus par Peek pour BRT (exemples inventés) :

| Numéro (inventé) | Ce que c’est |
| --- | --- |
| `182094736105` | 12 chiffres : numéro d’expédition (*numero spedizione*) attribué par BRT. |
| `06482917350428` | 14 chiffres : un *BRTcode*. |
| `918273645019283` | 15 chiffres : identifiant colis client (*ID collo cliente*) créé par l’expéditeur. |
| `EOB19283746501928374` | `EOB` et 17 chiffres : autre identifiant colis client de l’expéditeur. |

Le formulaire avancé de BRT demande 19 chiffres pour le *BRTcode* ; Peek n’accepte pas les numéros de 19 chiffres pour BRT. Le code de sécurité à 3 chiffres (*codice di sicurezza*), utilisé pour modifier une livraison, n’est pas un numéro de suivi.

Si BRT ne trouve rien, vérifie la saisie ou attends la remise du colis. La [recherche avancée de BRT](https://vas.brt.it/vas/sped_numspe_par.htm?lang=fr) (*Rintraccia spedizione*) remonte à 12 mois avec un *BRTcode* ou un numéro à 12 chiffres, et à 2 mois avec un *ID collo cliente*. Les numéros à 14 chiffres servent aussi chez [DPD France](carrier:dpd-fr), DPD Belgium ou [DPD Switzerland](carrier:dpd-ch), et ceux à 12 chiffres chez [Mondial Relay](carrier:mondial-relay) ou [Colis Privé](carrier:colis-prive) : vérifie le transporteur nommé par la boutique ou les [formats de numéros de suivi](guide:tracking-number-formats).

## Statuts de suivi BRT : que signifient les étapes ?

Le suivi sur `vas.brt.it` s’ouvre en italien et passe en français, anglais ou allemand. Peek lit la version anglaise, dont les colonnes restent en italien : *Data* (date), *Ora* (heure), *Filiale* (agence BRT) et *Evento* (événement). L’historique de Peek reprend ces libellés anglais tels quels, sauf toute remise effectuée – y compris en *BRT-fermopoint* ou *BRT Locker* – affichée comme `Delivered` (si Peek est réglé sur l’italien, il affiche les libellés italiens de BRT et `Consegnata`, en gardant l’anglais pour les scans connus seulement en anglais comme ceux d’un *Locker*) :

| Ce qu’écrit BRT | Signification pour toi | Étape dans Peek |
| --- | --- | --- |
| `SHIPPING INFO SENT TO BRT` | BRT a reçu les données de l’expéditeur, pas encore le colis. Voir [statuts de suivi](guide:tracking-statuses). | « Étiquette créée » |
| `COLLECTED` | BRT a enlevé le colis chez l’expéditeur. | « Pris en charge » |
| `DEPARTED` | Il a quitté une agence ou plateforme BRT. | « En transit » |
| `ARRIVED AT DEPOT` | Arrivé dans l’agence indiquée sous *Filiale*. | « En transit » |
| `FOR DELIVERY` | Le chauffeur est en tournée avec ton colis. | « En livraison » |
| `NOTICE OF ATTEMPTED DELIVERY` | Personne sur place ; le livreur a laissé un avis. | « Tentative de livraison » |
| `UNKNOWN/INCOMPLETE CONSIGNEE` | Non livré : destinataire inconnu ou adresse incomplète. | « Tentative de livraison » |
| `TURMOIL` | Une manifestation publique retarde l’acheminement. | « Problème signalé » |
| `PATRONAL HOLIDAY` | Fête du saint patron à destination : le colis attend la journée avant l’agence de livraison, sans tournée. | « En transit » |
| `ARRIVED AT BRT-fermopoint` | Disponible dans un commerce relais *BRT-fermopoint*. | « Prêt à être retiré » |
| `COLLECTED AT BRT-fermopoint` | Retiré par tes soins au *BRT-fermopoint*. | « Livré » |
| `ARRIVED AT BRT LOCKER` | Disponible en consigne *BRT Locker*. | « Prêt à être retiré » |
| `COLLECTED AT BRT LOCKER` | Retiré par tes soins au *BRT Locker*. | « Livré » |
| `TO BE DELIVERED` | Le partenaire étranger l’a pris pour livraison (noté à la même minute que `FOR DELIVERY`). | « En transit » (avec `FOR DELIVERY` : « En livraison ») |
| `ABSENT AFTER ATTEMPT.DELIVERY` | Le partenaire étranger n’a trouvé personne ; nouvel essai ou retour à suivre. | « Tentative de livraison » |
| `To collect at the parcel shop` | Déposé au point relais du partenaire étranger. | « Prêt à être retiré » |
| `DELIVERED` | Colis livré. | « Livré » |
| `RETURNED TO SENDER` | Retour vers l’expéditeur. | « Retourné à l’expéditeur » |

Trois précisions : `COLLECTED` désigne l’enlèvement chez le vendeur, et si [Chronopost](carrier:chronopost) ou DPD a amené le colis en Italie, cette remise à BRT vaut « En transit ». Un `FOR DELIVERY` sur un dépôt DPD à l’étranger marque la remise au réseau DPD (« En transit »). Enfin, un `DELIVERED` après `RETURNED TO SENDER` signale le retour chez l’expéditeur : la ligne affiche `Delivered`, mais l’étape de la fiche reste « Retourné à l’expéditeur ».

## Colis BRT vers la France, la Belgique ou la Suisse

**Qui livre.** Vers l’Europe par la route, BRT passe par le réseau DPD (ou un autre partenaire pour le lourd et le volumineux) ; en France, Geopost opère via [DPD France](carrier:dpd-fr) et [Chronopost](carrier:chronopost). Pour un envoi DPD, la page [Détail de l’envoi](https://vas.brt.it/vas/aiuto/aiuto.hsm?urlaiuto=sped_det_show.htm&lang=fr) propose un encadré vers le suivi DPD. Dans Peek, si l’envoi compte un seul colis, que BRT donne son numéro DPD à 14 chiffres (*Parcel number dpd*) et que le dernier scan vient d’un dépôt DPD nommant la France, la Belgique ou la Suisse, Peek affiche « Livraison par DPD France », « Livraison par DPD Belgium » ou « Livraison par DPD Switzerland » et interroge [DPD France](carrier:dpd-fr), DPD Belgium ou [DPD Switzerland](carrier:dpd-ch) (tant qu’il n’y est qu’annoncé : « Annoncé à DPD France, pas encore reçu »). Dès qu’un scan daté à jour apparaît chez DPD, Peek poursuit chez DPD en gardant l’historique BRT et vérifie encore BRT une journée, au plus toutes les 55 minutes. En multi-colis, Peek reste sur BRT.

**Douane.** D’Italie vers la France métropolitaine ou la Belgique, l’envoi reste dans l’UE sans douane. Les départements d’outre-mer sont hors du territoire de TVA de l’UE (formalités d’export au départ) et la Suisse est hors UE : vois [colis bloqué en douane](guide:customs).

## Absent en Italie : second passage, BRT-fermopoint et Locker

En cas d’absence en Italie, BRT prévient par e-mail, SMS ou avis papier. Le nouvel essai est en général prévu le jour ouvré suivant ; selon la *Carta dei servizi*, s’il échoue aussi, l’agence t’appelle.

Via le lien du message BRT ou dans myBRT (avec *BRTcode* et code postal ou PIN), tu peux selon l’envoi changer la date ou le créneau, choisir un *BRT-fermopoint* ou un retrait en agence, débloquer une instance ou modifier l’adresse avec l’accord du vendeur. Voir [livraison manquée](guide:missed-delivery).

**En instance (*in giacenza* / *mise en souffrance*).** Motifs : destinataire absent ou inconnu, adresse erronée ou incomplète, ou refus ; le détail de l’envoi l’indique. Tu donnes tes instructions depuis le message ou le suivi (parfois avec le code de sécurité à 3 chiffres). BRT interroge aussi l’expéditeur : selon la *Carta dei servizi*, sans instruction écrite sous 10 jours après l’avis, le colis repart (les conditions générales renvoient au délai convenu avec l’expéditeur). Le détail précise ensuite si des frais de garde s’appliquent et qui les paie.

**En agence (*fermo deposito*).** Retrait sur pièce d’identité, ou par un tiers muni d’une procuration, de sa pièce d’identité et d’une copie de la tienne. Avec tes coordonnées, BRT t’envoie l’adresse, les horaires et l’éventuel contre-remboursement.

**BRT-fermopoint.** Commerces partenaires (kiosques, tabacs, papeteries…), parfois ouverts le week-end. Avis par SMS ou e-mail et garde de 10 jours.

**BRT Locker.** Consignes dans des commerces, centres commerciaux ou stations-service – souvent accessibles 24 h/24 dehors – ouvertes par code QR ou PIN reçu par SMS ou e-mail. Les colis de plus de 20 kg sont exclus des *Locker* comme des *BRT-fermopoint*. En cas de panne ou casier vide, utilise le formulaire au bas de l’e-mail de retrait. Voir [consignes automatiques](guide:parcel-lockers).

## Colis BRT en retard, endommagé ou perdu

Les délais de BRT sont indicatifs. Si rien ne bouge, contacte l’agence sous *Filiale* ou le vendeur titulaire du contrat, et lis [suivi qui ne se met plus à jour](guide:tracking-not-updating).

**Dommage ou perte.** Si tu as payé le port, utilise le formulaire d’assistance BRT avec photos en cas d’avarie ; sinon, préviens le vendeur. Pour une avarie visible, note une réserve précise (« *con riserva di controllo* » décrivant le dommage) sur le bordereau, les réserves générales étant sans valeur. Pour un dommage caché, écris sous 8 jours après livraison en Italie (art. 1698 du Code civil italien) ou sous 7 jours hors dimanches et fériés à l’international routier (convention CMR). Voir [colis endommagé](guide:damaged-parcel).

**Réclamation et conciliation.** Destinataire et expéditeur peuvent réclamer : télécharge le PDF *Modulo di Reclamo* sur la page [Carta dei servizi](https://www.brt.it/it/carta-dei-servizi/), remplis-le et joins-le sur `services.brt.it/it/form-agcom`. BRT répond sous 45 jours. Ensuite, demande une conciliation à `conciliazioni@brt.it`, par exemple via *Casa del Consumatore* (adhésion annuelle de 50 €, ou 25 € jusqu’à 50 € de litige, remboursée par BRT en cas d’accord) ou un autre organisme, avant l’AGCOM. Colis marqué livré mais absent ? Vois [livré mais non reçu](guide:delivered-not-received).

## Contacter le service client BRT

- **Formulaires** sur la page anglaise [Support](https://services.brt.it/en/support) selon ton cas (retard, avarie, colis marqué livré mais non reçu). Aucun téléphone n’y figure.
- **Numéro vert italien** `800 278 708`, lun.–ven. 8 h 30–18 h 30 selon la *Carta dei servizi* (BRT ne dit pas s’il fonctionne hors d’Italie).
- **E-mail** `assistenzaclienti@brt.it` selon la *Carta*, avec nom, prénom, codes postaux de départ et d’arrivée et *BRTcode* obligatoires.
- **Chat** sur `brt.it` ou agence via la [recherche d’agence](https://services.brt.it/en/customer-service/search-for-a-branch). Le numéro bolonais de la *Carta* est celui du siège administratif.

## Suivre un colis BRT avec Peek

Colle le numéro dans [Peek](/). Pour un code `EOB` et la plupart des 15 chiffres, Peek interroge uniquement BRT (avant [FedEx](carrier:fedex) si la clé concorde aussi) ; pour 12 chiffres, il interroge BRT d’emblée avec d’autres transporteurs comme [Colis Privé](carrier:colis-prive). Pour 14 chiffres, il interroge BRT tout de suite en Italie, et ailleurs après les cinq premiers réseaux candidats (en France, [Chronopost](carrier:chronopost), Ciblex et des réseaux DPD). Au besoin, touche « Changer » et choisis BRT sous « Correspond à ce numéro » ou « Tous les transporteurs » (aussi trouvé sous « Bartolini »). Les liens `vas.brt.it` avec `brtCode=` passent ; pas myBRT ni les 19 chiffres.

Peek interroge BRT sans navigateur, place les scans sur la carte et montre le poids, en omettant noms, adresses, référence, nombre de colis, volume et point relais. Sur 14 chiffres, il peut reprendre le créneau estimé de DPD ou de [Chronopost](carrier:chronopost) tant qu’il reste valide. Les heures sont lues sur le fuseau italien – le même qu’en France, Belgique et Suisse – et les scans amenés vers l’Italie par [Chronopost](carrier:chronopost) ou DPD sont rattachés au suivi.

Tant que tu as le colis ouvert, ou le jour de sa livraison avec les notifications activées, Peek contrôle jusqu’à toutes les 10 minutes ; le jour de tournée, jusqu’à toutes les 2 minutes dès 6 h 00 locales (ou 30 minutes avant un créneau DPD ou Chronopost) tant que BRT ou DPD répond en direct (toutes les 5 minutes si la vérification ouvre un navigateur). Sinon, il contrôle toutes les heures, comme après deux jours sans scan ou 12 heures sur « Étiquette créée » ou « Pris en charge ». Via les seuls services de suivi : toutes les heures, toutes les 3 heures après un jour sans scan et jusqu’à toutes les 15 minutes le jour de livraison, tant que tu as le colis ouvert ou les notifications activées. Choisis tes alertes entre « Chaque scan », « Étapes importantes » ou « Livraison seulement ».

## Questions fréquentes sur le suivi BRT

### Où se trouve mon colis BRT en ce moment ?

La dernière ligne te répond : `ARRIVED AT DEPOT` nomme l’agence sous *Filiale*, `FOR DELIVERY` la tournée en cours. Sans scan, Peek affiche « En attente de BRT » (ou « En attente du premier scan de BRT » sur un code `EOB` avant choix du transporteur).

### BRT et DPD, est-ce la même entreprise ?

Ce sont deux marques du groupe Geopost : BRT opère le réseau italien et confie ses colis routiers européens au réseau DPD.

### Quels sont les délais de livraison de BRT ?

À titre indicatif, BRT annonce 24 à 48 h en Italie et 48 à 72 h par la route vers la plupart des pays d’Europe. Sa *Carta dei servizi* (juillet 2026, colis jusqu’à 31,5 kg) vise 96 % des envois livrés en 1 à 2 jours ouvrés en Italie et en 2 à 10 jours ouvrés à l’étranger.

### Qui livre un colis BRT en France, en Belgique ou en Suisse ?

[DPD France](carrier:dpd-fr), DPD Belgium ou [DPD Switzerland](carrier:dpd-ch) avec un numéro DPD à 14 chiffres ; sur un colis unique dont BRT fournit ce numéro, Peek bascule automatiquement vers ce transporteur dès qu’un scan daté à jour y paraît.

:::sources
- [BRT : Accueil (EN)](https://www.brt.it/en/) – recherche par BRTcode
- [BRT : FAQ (EN)](https://www.brt.it/en/customer-service/faq/) – BRTcode, absence, souffrance, délais, avaries et 20 kg
- [BRT : Codes and Support (EN)](https://www.brt.it/en/customer-service/codes-and-support/) – emplacement du BRTcode
- [BRT : Alerte phishing (EN)](https://www.brt.it/en/news/2026/04/16/alert-phishing-sms-email/) – alerte du 16 avril 2026
- [BRT : Code de sécurité (IT)](https://www.brt.it/it/faq/cose-il-codice-di-sicurezza/) – code à 3 chiffres
- [BRT : Rintraccia spedizione (FR)](https://vas.brt.it/vas/sped_numspe_par.htm?lang=fr) – longueurs, 12 et 2 mois et langues
- [BRT : Détail de l’envoi – Aide (FR)](https://vas.brt.it/vas/aiuto/aiuto.hsm?urlaiuto=sped_det_show.htm&lang=fr) – agences, souffrance, frais et DPD
- [BRT : BRT-fermopoint (EN)](https://www.brt.it/en/brt-fermopoint/) – commerces, week-end et 10 jours
- [BRT : Locker (EN)](https://www.brt.it/en/network/locker/) – consignes 24/7, QR, PIN et 20 kg
- [BRT : Retrait en agence (EN)](https://www.brt.it/en/faq/can-i-pick-up-my-shipment-at-a-brt-branch/) – procuration et pièces d’identité
- [BRT : Support (EN)](https://services.brt.it/en/support) – formulaires en ligne sans téléphone
- [BRT : Recherche d’agence (EN)](https://services.brt.it/en/customer-service/search-for-a-branch) – agences BRT
- [BRT : Carta dei servizi, PDF (IT)](https://www.brt.it/wp-content/uploads/sites/275/2026/11/261105-Carta-dei-Servizi.pdf) – juillet 2026 : 31,5 kg, passages, 10 jours, 8/7 jours, 96 %, 800 278 708, assistenzaclienti@brt.it et 45 jours
- [BRT : Carta dei servizi (IT)](https://www.brt.it/it/carta-dei-servizi/) – form-agcom, conciliation et AGCOM
- [BRT : Modulo di Reclamo, PDF (IT)](https://www.brt.it/wp-content/uploads/sites/275/2026/02/2.-Modulo-Reclamo.pdf) – réclamation destinataire ou expéditeur
- [BRT : Modulo di Conciliazione, PDF (IT)](https://www.brt.it/wp-content/uploads/sites/275/2026/02/3.-Modulo-di-Conciliazione-con-istruzioni.pdf) – conciliazioni@brt.it et cotisation
- [BRT : Condizioni generali, PDF (IT)](https://www.brt.it/wp-content/uploads/sites/275/2026/01/CONDIZIONI-GENERALI-CONTRATTO-TARIFFARIO_IT_CON-LOGO_REV-31.pdf) – délai convenu avec l’expéditeur
- [DPD : Accueil (EN)](https://www.dpd.com/en/) – marques Geopost
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formats, statuts, relais DPD et cadences Peek
:::
