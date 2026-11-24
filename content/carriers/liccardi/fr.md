---
title: Suivi Liccardi Express Courier : codes LDV, statuts et aide
description: Suis un colis Liccardi Express Courier (F.lli Liccardi) en Italie : codes 660 et LDV, scans agence et casier, mises en giacenza et support.
slug: suivi-liccardi
published: 2026-11-24
updated: 2026-11-24
---

**Liccardi Express Courier** (*F.lli Liccardi S.r.l.*) est un transporteur express national italien basé à Casandrino, près de Naples ([liccarditrasporti.com](https://www.liccarditrasporti.com/)). Lancée sous sa forme moderne en 1992 à partir d’une entreprise familiale de transport fondée trente ans plus tôt par Giovanni Liccardi, la société est passée du transport de documents bancaires dans les années 1990 à la distribution nationale B2C et e-commerce, traitant plus de 5,9 millions d’expéditions par an via plus de 20 agences et hubs italiens dont Naples, Rome, Milan, Bologne, Turin, Florence, Vérone, Bari et Salerne. Tu peux suivre un envoi sur le site de Liccardi ou coller ton code étiquette `660` à 13 chiffres ou ta *lettera di vettura* (LDV) de 7 à 8 chiffres dans le champ ci-dessus.

## Numéros de suivi Liccardi Express Courier (étiquette `660` et LDV)

Liccardi identifie chaque expédition par deux références numériques — un code-barres colis à 13 chiffres commençant par `66` (généralement `660`) et un numéro d’expédition plus court de 7 ou 8 chiffres (*lettera di vettura* / LDV) :

| Format (inventé) | De quoi il s’agit |
| --- | --- |
| `6604829173605` | Code-barres colis Liccardi à 13 chiffres (`660` + numéro d’expédition à 8 chiffres + 2 chiffres de contrôle/colis). |
| `48291736` | Numéro d’expédition Liccardi à 7 ou 8 chiffres (*LDV* / *lettera di vettura*). |

Vérifie l’e-mail d’expédition du vendeur ou la notification de livraison Liccardi pour retrouver l’un ou l’autre code ; les deux mènent à la même expédition dans le système Liccardi. Voir les [formats de numéros de suivi](guide:tracking-number-formats).

## Ce que signifient les statuts de suivi Liccardi Express Courier

Le suivi Liccardi publie des codes résultat numériques (*codici esito*) accompagnés de descriptions d’agences en italien. Pour les envois multicolis (*colli*), Liccardi enregistre les événements par colis ; Peek garde l’expédition active tant que tous les colis ne sont pas livrés :

| Liccardi indique (code) | Ce que cela signifie pour toi | Peek affiche |
| --- | --- | --- |
| `DATI SPEDIZIONE TRASMESSI A LICCARDI` (`50`), `SERVIZIO PROGRAMMATO` (`58`), `RITIRO ASSEGNATO AD AUTISTA` (`36`), `IN RITIRO` (`92`) | L’expéditeur a transmis les données d’expédition ou un enlèvement par chauffeur est planifié/en cours avant prise en charge. | « Étiquette créée » |
| `SPEDIZIONE RITIRATA` (`37`), `COLLO PRESO IN CARICO` (`3`), `SPEDIZIONE PRESA IN CARICO` (`4`) | Colis enlevé chez l’expéditeur et pris en charge dans le réseau Liccardi. | « Pris en charge » |
| `ARRIVATO IN FILIALE` (`47`), `IN TRANSITO` (`48`), `SPEDIZIONE PARTITA` (`91`), `ARRIVO SU FILIALE DI DESTINO` (`72`), `IN GESTIONE` (`49`), `GIACENZA SVINCOLATA` (`70`), `IN PRENOTAZIONE` (`33`) | En transit entre hubs, arrivé à l’agence de destination, en prise de rendez-vous ou débloqué d’une instance. | « En transit » |
| `CARICO VETTURA` (`51`), `CARICO VETTURA CONFERMATO` (`95`), `IN CONSEGNA` (`52`) | Chargé dans le véhicule de livraison local et en tournée aujourd’hui. | « En livraison » |
| `CONSEGNATA AL PUDO` (`6`), `CHECK-IN AL PUDO` (`80`), `SPEDIZIONE DEPOSITATA NEL LOCKER` (`11`), `FERMO DEPOSITO` (`32`) | Prêt à être retiré dans un point relais PUDO, un casier ou une agence locale Liccardi (*fermo deposito*). | « Prêt à être retiré » |
| `ASSENTE` (`28`), `INDIRIZZO ERRATO` (`40`), `CONSEGNA NON ESEGUITA` (`43`), `MANCA CONTRASSEGNO` (`44`), `INDIRIZZO NON TROVATO` (`45`), `CHIUSO` (`46`), `GIRO DI CONSEGNA NON ULTIMATO` (`67`) | La livraison n’a pas pu aboutir (destinataire absent, erreur d’adresse, contre-remboursement non prêt, fermé ou tournée inachevée). | « Tentative de livraison » |
| `IN GIACENZA` (`35`), `COLLO DANNEGGIATO` (`30`), `MERCE RESPINTA` (`42`), `SPEDIZIONE SMARRITA` (`54`), `COLLO DISGUIDATO` (`68`), `ORDINE SOSPESO: IN ATTESA DI ISTRUZIONI MITTENTE` (`76`), `DISCONOSCIMENTO FIRMA` (`103`), `CONTATTARE ASSISTENZA` (`107`) | Colis retenu en entrepôt dans l’attente d’instructions de l’expéditeur (*giacenza*), endommagé, refusé, mal acheminé, égaré ou nécessitant l’assistance. | « Problème signalé » |
| `RIENTRO AL MITTENTE` (`31`), `IN CONSEGNA AL MITTENTE` (`53`), `RESO A MITTENTE` (`8`) | En cours de retour ou retourné à l’expéditeur. | « Retourné à l’expéditeur » |
| `SPEDIZIONE CONSEGNATA` (`1`), `SPEDIZIONE CONSEGNATA A COMMITTENTE` (`5`), `SPEDIZIONE RITIRATA DA LOCKER DAL DESTINATARIO` (`12`) | Livré au destinataire ou retiré dans le casier colis. | « Livré » |

Note que la barre de progression propre à Liccardi considère les dépôts en casier (`11`) comme livrés, tandis que Peek classe les scans PUDO, casier et *fermo deposito* sur **« Prêt à être retiré »** jusqu’à ce que le destinataire retire effectivement le colis (`12`).

## Délais de livraison, services et réseau d’agences en Italie

Selon [Services Liccardi](https://www.liccarditrasporti.com/servizi/), [Entreprise](https://www.liccarditrasporti.com/azienda/) et [Agences](https://www.liccarditrasporti.com/filiali/) :

- **Délais d’acheminement :** Liccardi enlève les commandes chez les marchands sous 48 heures, pèse/mesure/photographie chaque colis dans ses hubs de tri et annonce une livraison nationale en **24 à 48 heures** (couvrant statistiquement tout le territoire en **72 heures**, les zones isolées — *località disagiate* — et les pics saisonniers pouvant allonger ces délais).
- **Options de livraison :** express B2C standard, livraison à l’étage (*consegne al piano*), rendez-vous horaire (*consegne tassative*), livraison le jour même, paiement à la livraison (*contrassegno*), retrait en agence (*fermo deposito*), échanges/retours et assurance complémentaire en option.
- **Réseau d’agences :** plus de 20 hubs et dépôts opérationnels en Italie, dont le hub central de Casandrino/Naples (`NA1`), Rome (`RM1`, `RM3`), Milan (`MI1`), Bologne (`BO1`), Turin (`TO2`), Florence (`FI1`), Vérone (`VR1`), Bari (`BA1`), Salerne (`SA1`), Avellino (`AV1`), Frosinone (`FR1`), Latina (`LT1`), Modène (`MO1`), Parme (`PR1`), Foggia (`FG1`), Lecce (`LE2`), Matera (`MT1`), Potenza (`PZ1`), Cosenza (`CS1`), Catanzaro (`CZ1`) et Reggio de Calabre (`RC1`). Lorsqu’un autre transporteur prend un colis en relais, le suivi Liccardi peut afficher sa lettre de voiture (LDV).

## Livraison manquée, mises en *giacenza* et réclamations

- **`IN GIACENZA` (`35`) ou livraison échouée (`28`, `40`, `45`) :** après une tentative infructueuse ou un problème d’adresse, Liccardi place l’envoi en *giacenza* (instance) et attend les instructions de déblocage (*svincolo giacenza*) de l’**expéditeur**. Contacte d’abord ta boutique en ligne pour qu’elle émette le déblocage, ou utilise le portail destinataire [Assistenza / Reclami Destinatari](https://www.liccarditrasporti.com/assistenza-reclami-destinatari/) pour mettre à jour les consignes, signaler une erreur d’adresse ou demander un retrait en agence (*ritiro in filiale*).
- **Contester un résultat (`Contestazione Esito`) ou colis manquant :** le guide réclamations destinataires de Liccardi permet de signaler un statut erroné, un avis d’absence alors que tu étais chez toi ou un colis indiqué livré mais non reçu, et fournit les formulaires téléchargeables *Modulo reclamo*, *Modulo conciliazione* et *Formulario CP*. Préviens aussi ton vendeur. Voir [colis indiqué livré mais non reçu](guide:delivered-not-received), [colis endommagé](guide:damaged-parcel), [colis perdu](guide:lost-parcel), [colis retourné à l’expéditeur](guide:returned-to-sender) et [pourquoi le suivi ne se met plus à jour](guide:tracking-not-updating).

## Service client et contacts Liccardi Express Courier

Coordonnées officielles publiées sur [Liccardi Contatti](https://www.liccarditrasporti.com/contatti/) et [Assistenza / Reclami Destinatari](https://www.liccarditrasporti.com/assistenza-reclami-destinatari/) :

- **Numéro vert (*Numero Verde* en Italie) :** `800 029 764` (`800029764`).
- **Téléphone :** `+39 081 5053642` (fax `081 5053071`).
- **E-mail service client :** `assistenzaclienti@liccarditrasporti.com` (renseignements généraux : `info@liccarditrasporti.com` ; PEC : `flliliccardi@legalmail.it`).
- **Portail d’assistance et réclamations destinataires :** [liccarditrasporti.com/assistenza-reclami-destinatari/](https://www.liccarditrasporti.com/assistenza-reclami-destinatari/).
- **Siège social et hub central :** *F.lli Liccardi S.r.l.* (P.IVA `06588280633`), Via Paolo Borsellino 133 (siège légal 123), 80025 Casandrino (NA), Italie.

> Méfie-toi des faux SMS te demandant un petit paiement en ligne pour reprogrammer une livraison en Italie. Vérifie toujours tout avis directement auprès de ton vendeur ou via le site officiel et les canaux de service client Liccardi. Voir les [arnaques aux SMS de livraison](guide:delivery-scam-texts).

## Suivre un colis Liccardi Express Courier avec Peek

Lorsque tu saisis un code-barres `660` à 13 chiffres (`6604829173605`, inventé), [Peek](/) interroge Liccardi Express Courier en premier partout sauf en Russie, où il interroge Ozon en premier, et affiche « Liccardi Express Courier a ce colis. » dès que le suivi Liccardi le confirme. Peek reconnaît également comme Liccardi un lien collé `my.liccarditrasporti.com/segui?ldv=`. Un LDV à 7 ou 8 chiffres (`48291736`, inventé) ne désigne aucun transporteur à lui seul : colle le lien, ou appuie sur « Changer » à l’ajout du colis ou « Changer de transporteur » sur sa fiche et sélectionne **Liccardi Express Courier** (rechercher « Liccardi Trasporti », « Liccardi Express » ou « F.lli Liccardi » le trouve aussi).

Peek lit le suivi Liccardi et affiche l’étape (« Étiquette créée », « Pris en charge », « En transit », « En livraison », « Prêt à être retiré », « Tentative de livraison », « Problème signalé », « Retourné à l’expéditeur », « Livré »), le libellé italien de Liccardi et l’agence. Les codes `codEsito` déterminent l’étape mais ne s’affichent dans aucune application Peek. Peek date les scans sur l’heure italienne et les affiche à l’heure de ton appareil. Il indique aussi le jour de livraison estimé quand Liccardi en fournit un (sans la tranche horaire), l’heure de livraison une fois livré, et le statut récapitulatif avant le premier scan du colis. Lorsque Liccardi fournit la lettre de voiture d’un autre transporteur et que Peek le reconnaît, Peek vérifie qu’il détient le colis : la fiche indique alors « Livraison par » suivi du nom du transporteur, et affiche les deux numéros. Pour un envoi de plusieurs colis, Peek fusionne les scans répétés d’agence survenus en moins d’une minute et garde l’expédition ouverte jusqu’à ce que tous les colis soient livrés. Peek n’affiche pas la ville ou le code postal du destinataire, ni le nom des chauffeurs ni les signatures de preuve de livraison.

Tant que le colis reste ouvert dans Peek, ou le jour de la livraison si les notifications sont activées, Peek vérifie jusqu’à toutes les 10 minutes ; le jour où Liccardi affiche `CARICO VETTURA` (`51`), `CARICO VETTURA CONFERMATO` (`95`) ou `IN CONSEGNA` (`52`), toutes les 2 minutes à partir de 6 h heure italienne tant que le suivi propre de Liccardi répond. Sinon, Peek vérifie toutes les heures. Après 12 heures avec uniquement « Étiquette créée » ou « Pris en charge », ou deux jours sans nouvelles, Peek passe à une vérification par heure quelle que soit ta manière de suivre le colis, et toutes les 6 heures après sept jours à « Étiquette créée ». Lorsque le suivi Liccardi ne répond pas et que Peek suit le colis uniquement via des services de suivi, il vérifie toutes les heures, toutes les 3 heures après une journée calme, et jusqu’à toutes les 15 minutes le jour où le colis est en livraison, tant que le colis est ouvert ou que les notifications sont activées.

Lorsque le scan le plus récent date de quatre jours et que le colis est toujours sur « Étiquette créée », « Pris en charge » ou « En transit », Peek le signale avec le jour de ce scan, par exemple « Aucune mise à jour depuis lun 12 oct ». La fiche du colis indique alors « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger Liccardi Express Courier. », avec un lien « Demande de l’aide à Liccardi Express Courier » vers la page d’assistance et réclamations destinataires de Liccardi. Après trois semaines sans nouveau scan, elle affiche « Rien de nouveau depuis plus de trois semaines. Si le colis n’est toujours pas arrivé, contacte le vendeur. Peek continue d’interroger Liccardi Express Courier. »

Tu peux choisir des alertes pour « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Peek suit uniquement les mises à jour de suivi et ne peut ni débloquer une *giacenza*, ni modifier une adresse de livraison, ni contacter Liccardi Express Courier à ta place.

## Questions sur le suivi Liccardi Express Courier

### À quoi ressemble un numéro de suivi Liccardi ?

Liccardi utilise soit un code-barres étiquette à 13 chiffres commençant par `66` (généralement `660`, par exemple `6604829173605`, inventé), soit un numéro d’expédition à 7 ou 8 chiffres (*LDV*, par exemple `48291736`, inventé).

### Que signifie `IN GIACENZA` dans le suivi Liccardi ?

`IN GIACENZA` (code `35`) signifie que le colis est conservé dans une agence Liccardi après un échec de livraison ou un problème d’adresse et attend des instructions de déblocage (*svincolo*) de la part de l’expéditeur. Contacte ton vendeur et transmets tes consignes de livraison mises à jour via [Assistenza / Reclami Destinatari](https://www.liccarditrasporti.com/assistenza-reclami-destinatari/).

### Combien de temps Liccardi Express Courier met-il pour livrer en Italie ?

Liccardi annonce une livraison nationale en **24 à 48 heures** après l’enlèvement (statistiquement sous **72 heures** sur tout son réseau italien), avec des délais supplémentaires possibles pour les zones isolées (*località disagiate*) et les périodes de forte activité.

### Comment contacter le service client Liccardi Express Courier ?

Appelle le numéro vert italien `800 029 764` ou le `+39 081 5053642`, écris à `assistenzaclienti@liccarditrasporti.com` ou soumets une demande ou réclamation destinataire sur [liccarditrasporti.com/assistenza-reclami-destinatari/](https://www.liccarditrasporti.com/assistenza-reclami-destinatari/).

:::sources
- [Liccardi Express Courier : site officiel](https://www.liccarditrasporti.com/) – *F.lli Liccardi S.r.l.*, 20+ agences, plus de 5,9 millions d’expéditions par an, siège à Casandrino (NA) et contacts
- [Liccardi Express Courier : Azienda](https://www.liccarditrasporti.com/azienda/) – historique depuis 1992 (origine années 1960), développement B2C dès 2004 et adhésion Unione Industriali
- [Liccardi Express Courier : Servizi](https://www.liccarditrasporti.com/servizi/) – livraison 24/48 h (jusqu’à 72 h), enlèvement 48 h, *contrassegno*, *fermo deposito* et tri photo automatisé
- [Liccardi Express Courier : Filiali](https://www.liccarditrasporti.com/filiali/) – liste complète des 20+ hubs et agences italiennes (NA1, RM1, MI1, BO1, TO2, FI1, VR1, BA1, etc.)
- [Liccardi Express Courier : Contatti](https://www.liccarditrasporti.com/contatti/) – `800 029 764`, `+39 081 5053642`, e-mails, PEC et adresse de Casandrino
- [Liccardi Express Courier : Assistenza / Reclami Destinatari](https://www.liccarditrasporti.com/assistenza-reclami-destinatari/) – catégories d’aide destinataire, contestations et formulaires de réclamation/conciliation (URL d’aide liée par Peek)
- [Universal Parcel Scraper : liccardi](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/liccardi) – règles `660`/LDV, règle de lien `my.liccarditrasporti.com`, correspondance complète `codEsito`, gestion multicolis, jour de livraison estimé et extraction LDV partenaire
:::
