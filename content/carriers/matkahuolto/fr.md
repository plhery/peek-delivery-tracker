---
title: Suivi Matkahuolto : statuts, points relais et contact
description: Suis un colis Matkahuolto en Finlande : numéro d’envoi, statuts en anglais, codes de retrait, délais de garde, casiers et contact.
slug: suivi-matkahuolto
published: 2026-11-22
updated: 2026-11-22
---

Matkahuolto est un transporteur finlandais disposant de points relais et de casiers à travers toute la Finlande, y compris dans certains magasins K-Market et kiosques R-kioski. Suis un colis sur matkahuolto.fi/tracking (en anglais) ou dans l’application Paketit à l’aide du numéro d’envoi figurant dans l’e-mail de la boutique ou le SMS de Matkahuolto, ou colle-le dans le champ ci-dessus.

## Numéro de suivi Matkahuolto et où le trouver

Matkahuolto l’appelle identifiant d’envoi (*lähetystunnus* en finnois, *shipment ID* ou *consignment ID* en anglais). Il figure dans l’e-mail d’expédition de la boutique ainsi que dans chaque SMS ou e-mail de Matkahuolto. Le code de retrait indiqué dans l’avis d’arrivée est autre chose : il sert à ouvrir le casier ou à présenter au comptoir du point relais.

Matkahuolto ne publie pas ses formats de numéros. Voici les formes connues du catalogue de Peek (avec chiffres fictifs) :

| Numéro | À quoi il correspond |
| --- | --- |
| `MH482910372FI` | `MH`, 9 chiffres et `FI`. |
| `MA7394028156342019482` | `MA` suivi de 19 chiffres. |
| `00373482910375618296` | Un code-barres colis à 20 chiffres (SSCC). Le suivi Matkahuolto le trouve aussi sur 18 chiffres, sans les `00`. |

Les numéros commençant par `JJFI`, ainsi que les [numéros postaux](guide:tracking-number-formats) terminés par `FI` avec d’autres lettres que `MH` devant (`RR482910372FI`), appartiennent à [Posti](carrier:posti), la poste finlandaise. Si Matkahuolto ne trouve pas le numéro, vérifie quel transporteur figure dans l’e-mail de la boutique.

Le transfert d’un colis vers un autre point relais crée un nouveau numéro d’envoi. Un colis GLS remis à Matkahuolto en Finlande affiche son numéro Matkahuolto dans le suivi GLS comme « Finland parcel number », commençant par `00`.

Pas de numéro ? Si tu es inscrit chez Matkahuolto ou utilises l’application Paketit, les colis adressés à ton numéro de téléphone ou ton adresse e-mail y apparaissent ; sinon, l’expéditeur peut ajouter tes coordonnées. Voir [comment retrouver un numéro de suivi](guide:find-tracking-number).

## Que signifient les statuts de suivi Matkahuolto

Le suivi Matkahuolto existe en finnois (par défaut), en suédois et en anglais, chaque scan portant date, heure et ville. Voici les libellés en anglais, dans l’ordre :

| Statut Matkahuolto | Ce que cela signifie pour toi |
| --- | --- |
| `Matkahuolto is waiting for the consignment from the sender` | L’expéditeur a [créé l’envoi](guide:tracking-statuses) ; Matkahuolto n’a pas encore le colis. |
| `Consignment is being processed` | Scanné dans un terminal. |
| `The consignment is on its way` | En transit entre terminaux. |
| `Your parcel has been sorted and will be taken next to the pickup point.` | Trié au dernier terminal ; le point relais est la prochaine étape. |
| `We will deliver the consignment to the pickup point during the day` | En route vers ton point relais aujourd’hui. |
| `Your pick up point has changed` | Redirigé vers un autre point relais, nommé dans la ligne. |
| `The shipment is available for pickup with the pickup code at the pick up point` | Prêt à être retiré, avec le nom du point et le dernier jour de retrait. |
| `We have sent the recipient a message about the arrival of the consignment (sms).` | Ton avis d’arrivée a été envoyé. Ce n’est pas un déplacement. |
| `The consignment has been delivered. Thank you for choosing Matkahuolto!` | Retiré au point relais ou dans le casier. |

**En cours de journée.** Le premier point sur la tournée n’est pas forcément le tien, précise Matkahuolto, mais le colis devrait être prêt au retrait le jour même ; tu reçois un avis dès qu’il l’est.

## Points relais, casiers et livraisons à domicile Matkahuolto

**Retrait.** Tu n’as besoin que du code de retrait reçu dans l’avis d’arrivée. Aucune pièce d’identité n’est demandée, sauf si l’avis indique une remise en main propre (souvent alcool ou documents). N’importe qui muni du code peut retirer le colis, sauf dans les casiers extérieurs gérés par application, qui ne s’ouvrent qu’avec le téléphone du destinataire via l’application Paketit. Si l’avis ne mentionne pas de casier, demande à la caisse du point relais.

**Avis d’arrivée.** D’abord une notification de l’application Paketit, puis un SMS ou un message WhatsApp, et un e-mail si l’expéditeur l’a transmis.

**Délai de garde.** 5 jours avant le retour à l’expéditeur ; 14 jours lorsque l’avis arrive par courrier papier faute de téléphone ou d’e-mail. Tu peux acheter 1 à 16 jours supplémentaires dans l’application ou, si tu es inscrit, sur le site web, sauf blocage par l’expéditeur. Certains partenaires comme GLS indiquant des délais différents (10 jours sur la page GLS Finlande), fie-toi toujours à la date limite de ton avis.

**Changer de point.** Une fois gratuitement dans l’application : avant que Matkahuolto n’ait le colis selon sa FAQ anglaise, ou à réception du premier avis selon sa page suédoise ; pas pendant le transit. Après l’avis d’arrivée, tu peux acheter un transfert.

**Livraison à domicile.** Les *Home Parcels* sont livrés en semaine de 8 h à 18 h (heure finlandaise) ; les *Business Parcels* et colis [FedEx](carrier:fedex) en semaine de 8 h à 16 h (heure finlandaise), sans préavis. L’avis indique le jour prévu, que tu peux décaler sur l’un des cinq jours ouvrés suivants le jour même de sa réception. Le colis est déposé dans un endroit sûr et abrité près de la porte ; si c’est impossible et que tu es injoignable, il va dans un point relais proche le jour ouvré suivant.

**Åland.** Depuis le 1er octobre 2026, Åland Post livre les colis Matkahuolto sur Åland, et ses bureaux, agents postaux, casiers et livraisons à domicile font partie du réseau Matkahuolto.

**Retours.** Obtiens un code d’activation via le bouton `Palauta` dans l’application ou sur le site, inscris-le sur le colis avec ton nom et ton téléphone, puis dépose-le dans un casier ou un point relais.

## Colis Matkahuolto en retard, bloqué ou endommagé

La plupart des colis arrivent le lendemain de l’expédition, selon Matkahuolto, et tous sous trois jours après prise en charge (trois jours ouvrés dans sa FAQ finnoise). Les colis venus de l’étranger peuvent passer par d’autres transporteurs et mettre un peu plus d’une semaine à atteindre Matkahuolto.

**Suivi bloqué.** Le suivi peut marquer une pause, par exemple en attente de départ ou lors d’un contrôle d’emballage. Si rien ne bouge, Matkahuolto demande de contacter l’expéditeur. Voir [un suivi qui ne se met plus à jour](guide:tracking-not-updating).

**Aucun avis d’arrivée.** Souvent une faute de frappe dans tes coordonnées : vérifie tes courriers indésirables et demande à l’expéditeur de corriger.

**Endommagé ou perdu.** Préviens sans délai la boutique ou l’expéditeur avec des photos. C’est l’entreprise expéditrice qui dépose la réclamation auprès de Matkahuolto ; entre particuliers, c’est l’expéditeur. Signale tout dommage visible lors du retrait ; sinon, déclare-le par écrit sous 7 jours, ou sous 30 jours en tant que consommateur. La responsabilité de Matkahuolto est limitée à 20 € par kilo, ou à la valeur de la marchandise si elle est inférieure.

## Service client et téléphone Matkahuolto

Coordonnées publiées sur les pages de Matkahuolto :

- **Services colis :** 0600 11300, du lundi au vendredi de 8 h à 18 h (heure finlandaise), 2,49 €/min plus coût local.
- **Assistance colis :** +358 20 33 6161, du lundi au vendredi de 8 h à 10 h et de 14 h à 16 h (heure finlandaise), au tarif local, pour casiers, casiers à application et livraisons à domicile.
- **Chat :** avec le chatbot Mauno sur la [page service client colis](https://www.matkahuolto.fi/customer-service/parcels) ou dans l’application Paketit, où un conseiller peut prendre le relais.
- **Formulaire de retour :** en finnois sur matkahuolto.fi/palautelomake. Aucune adresse e-mail n’est publiée pour les destinataires.

> Matkahuolto met en garde contre de faux messages, des noms d’expéditeur usurpés et des sites imitateurs comme matkahuoltoo.fi. L’entreprise précise qu’elle ne relaie jamais de paiements et n’agit pas comme passerelle de paiement ; le seul lien de paiement envoyé s’adresse aux expéditeurs pour des frais de retour. Vérifie tout message en saisissant ton numéro dans le suivi ou l’application Matkahuolto ; si tu as payé, appelle ta banque.

## Suivre un colis Matkahuolto avec Peek

Pour un numéro `MH…FI`, [Peek](/) interroge Matkahuolto, [Posti](carrier:posti) et [Chronopost](carrier:chronopost), et affiche « Matkahuolto a ce colis. » lorsque seule Matkahuolto le connaît ; si Posti le connaît aussi, tu choisis. Un numéro `MA` va uniquement chez Matkahuolto. Pour un code-barres à 18 ou 20 chiffres portant le `64` finlandais ou le `73` suédois après son premier chiffre (après `00` et un chiffre en format 20 chiffres), Peek interroge aussi Matkahuolto avec les autres transporteurs compatibles et sélectionne Matkahuolto lorsqu’elle est seule à le connaître. Si [Posti](carrier:posti) connaît aussi un code `64`, ou Bring un code `73`, Peek choisit ce transporteur : sélectionne Matkahuolto avec « Changer ». Si un autre transporteur comme PostNord le connaît également, tu choisis. Les autres codes-barres ne sont pas proposés à Matkahuolto : sélectionne-la avec « Changer » (ou « Changer de transporteur »). Un lien `matkahuolto.fi` contenant `parcelNumber=` classe tout numéro sous Matkahuolto, mais sa recherche n’accepte que ses codes `MH…FI` et `MA` ainsi que les numéros postaux et codes-barres SSCC/GS1 à chiffre de contrôle valide.

Les scans de Matkahuolto n’ont pas de fuseau horaire : Peek les lit à l’heure finlandaise et les affiche dans ton fuseau horaire (« Heures dans ton fuseau horaire : »). Les statuts gardent l’anglais de Matkahuolto. Peek affiche le point relais ou casier avec sa ville et sa rue, ainsi que le nom du service lorsqu’il est indiqué (hors contre-remboursement), mais ne reçoit ni date d’arrivée prévue ni poids, ignore les services annexes et codes postaux, et n’affiche pas séparément la date limite de retrait (visible dans le texte du statut et ton avis). Peek ne transfère pas le colis vers un autre transporteur.

Peek vérifie jusqu’à toutes les 10 minutes, et toutes les 2 minutes à partir de 6 h sur l’horloge du dernier scan (heure finlandaise) le jour où le colis passe « En livraison » tant que tu gardes la fiche ouverte dans Peek ou que les notifications sont activées, lorsque le suivi Matkahuolto répond. Après deux jours sans nouvelle (12 heures tant que le colis reste sur « Étiquette créée » ou « Pris en charge »), il vérifie toutes les heures. Pour les notifications, tu peux choisir « Toutes les mises à jour », « Importantes uniquement » ou « Jour de livraison uniquement ». Après quatre jours sans scan en transit, hors douane ou problème signalé, Peek affiche « Aucune mise à jour depuis … », rappelle « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger Matkahuolto. » et propose « Demande de l’aide à Matkahuolto ». Peek suit plus de 4 500 transporteurs, mais ne peut pas contacter Matkahuolto ni l’expéditeur à ta place.

## Questions sur le suivi Matkahuolto

### Matkahuolto est-il fiable ?

Oui, Matkahuolto est un véritable transporteur finlandais. Traite tout message te demandant de payer en son nom comme une arnaque : Matkahuolto indique ne jamais relayer de paiements.

### Existe-t-il un suivi Matkahuolto en anglais ?

Oui. Le suivi Matkahuolto propose une version anglaise sur matkahuolto.fi/tracking, et la langue de l’application Paketit se change dans ses réglages.

### Puis-je suivre un colis Matkahuolto sans le code ?

Oui, si tu es inscrit ou utilises l’application Paketit et que l’expéditeur a indiqué ton numéro de téléphone ou ton adresse e-mail : le colis y apparaît. Sinon, demande le numéro d’envoi à la boutique.

### Combien de temps prend un colis Matkahuolto ?

En règle générale 1 à 2 jours ouvrés vers les casiers et points relais, et au plus trois jours une fois le colis chez Matkahuolto (trois jours ouvrés selon sa FAQ finnoise). Les colis venus de l’étranger peuvent mettre un peu plus d’une semaine à lui parvenir.

:::sources
- [Matkahuolto : How to receive a parcel?](https://www.matkahuolto.fi/customer-service/parcels/how-to-receive-a-parcel) – code de retrait, pièce d’identité, avis, changement de point, livraisons à domicile, dommages, téléphones
- [Matkahuolto : Frequently asked questions](https://www.matkahuolto.fi/frequently-asked-questions) – livraisons à domicile, échecs de livraison, téléphones
- [Matkahuolto : Track your parcel](https://www.matkahuolto.fi/customer-service/parcels/track-your-parcel) – vues de suivi, délais, pauses, contacter l’expéditeur
- [Matkahuolto : Receive your parcel easily](https://www.matkahuolto.fi/packages/receive-a-parcel) – 1 à 2 jours ouvrés, avis, prolongation de 1 à 16 jours
- [Matkahuolto : Pakettien säilytysaika muuttuu](https://www.matkahuolto.fi/news/pakettien-sailytysaika-muuttuu-tavoitteena-entistakin-sujuvampi) – 5 et 14 jours, prolongations
- [Matkahuolto : Domestic parcels](https://www.matkahuolto.fi/packages/domestic-parcels) – garde de 5 jours, retour à l’expéditeur
- [Matkahuolto : Parcel lockers and parcel points in Finland](https://www.matkahuolto.fi/packages/service-point-network) – réseau, casiers par application
- [Matkahuolto : Transport damage guide for consumers](https://www.matkahuolto.fi/packages/damage-in-transit) – qui réclame, délais, photos
- [Matkahuolto : Kuljetusvahinko-ohje kuluttajille](https://www.matkahuolto.fi/paketit/kuljetusvahingot) – délais, 20 € par kilo
- [Matkahuolto : About scam messages](https://www.matkahuolto.fi/packages/about-scam-messages) – faux messages, expéditeur authentique, numéro d’envoi
- [Matkahuolto : Return the arrived parcel easily](https://www.matkahuolto.fi/packages/return-parcel) – retours avec code d’activation
- [Matkahuolto : Paketit app](https://www.matkahuolto.fi/packages/paketit-app) – langue de l’application, chat
- [Matkahuolto : Customer service for parcel services](https://www.matkahuolto.fi/customer-service/parcels) – chatbot Mauno
- [Matkahuolto : Usein kysyttyä](https://www.matkahuolto.fi/usein-kysyttya) – tarif du numéro, trois jours ouvrés, Business Parcels et FedEx 8 h à 16 h, formulaire
- [Matkahuolto : Seuraa pakettisi matkaa](https://www.matkahuolto.fi/asiakaspalvelu/paketit/seuraa-paketin-matkaa) – un peu plus d’une semaine depuis l’étranger, lien de paiement pour frais de retour expéditeur
- [Matkahuolto : Track the parcel](https://www.matkahuolto.fi/tracking) – suivi en anglais, finnois et suédois, libellés des statuts
- [Matkahuolto : Ta emot paket](https://www.matkahuolto.fi/paket/ta-emot-paket) – en suédois : changement gratuit de point au premier avis d’arrivée
- [Matkahuolto : Spåra ditt paket](https://www.matkahuolto.fi/kundservice/paket/spara-ditt-paket) – en suédois : tous les colis sous trois jours
- [Matkahuolto : Åland Post blir Matkahuoltos nya transportpartner på Åland](https://www.matkahuolto.fi/news/%C3%85land%20Post%20blir%20Matkahuoltos%20nya%20transportpartner%20p%C3%A5%20%C3%85land) – en suédois : Åland Post sur Åland depuis le 1er octobre 2026
- [GLS Finlande : Lähetysseuranta](https://gls-group.com/FI/fi/laehetysseuranta.html) – points Matkahuolto, Finland parcel number, 10 jours
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – ce que lit Peek
:::
