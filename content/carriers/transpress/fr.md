---
title: Suivi Transpress (Транспрес) : suivre un colis en Bulgarie
description: Suivez un colis Transpress (Транспрес) en Bulgarie : ajouter le numéro dans Peek, statuts, tentatives de livraison, retours, réclamations et contact.
slug: suivi-transpress
published: 2026-11-23
updated: 2026-11-23
---

Transpress (Транспрес) est une société bulgare de messagerie et de logistique dont le siège se trouve dans le parc industriel de Sofia-Bozhurishte. Ses services de messagerie s’appuient sur deux opérateurs postaux enregistrés soumis aux mêmes conditions générales : Транспрес ООД et Транспрес Деливъри ЕООД. Pour suivre un colis, collez un lien vers le suivi de Transpress dans le champ ci-dessus, ou collez le numéro et sélectionnez Transpress comme transporteur : Peek ne peut pas reconnaître un numéro Transpress à sa seule forme.

## Numéro de suivi Transpress : à quoi il ressemble

Le catalogue de Peek ne possède aucun format de numéro pour Transpress, et le suivi de Transpress demande le « Shipment number » (« Номер на пратката » en bulgare) sans préciser son format. Transpress n’apparaît donc jamais sous « Correspond à ce numéro », et cette page ne donne pas d’exemple.

| Ce que vous collez | Ce que fait Peek |
| --- | --- |
| Le numéro Transpress seul | Peek peut lister d’autres transporteurs sous « Correspond à ce numéro » : ils correspondent seulement à la forme du numéro. Sélectionnez Transpress vous-même (voir ci-dessous). |
| Un lien vers le suivi Transpress : `transpress.bg/bg/prosledjavane?track-number=` ou `/en/prosledjavane?track-number=`, suivi du numéro | Peek extrait le numéro du lien et sélectionne immédiatement Transpress (« Transporteur détecté »). |
| Un numéro que la boutique attribue à un autre transporteur | Suivez-le avec ce transporteur : l’e-mail de la boutique reste le meilleur indice pour savoir qui assure la livraison. |

**Où le trouver.** Dans l’e-mail d’expédition de la boutique ou sur votre page de commande ; pour un colis envoyé par un particulier, demandez-le-lui. Aucun numéro ? Consultez [comment trouver un numéro de suivi](guide:find-tracking-number).

## Signification des statuts Transpress dans Peek

Peek n’interroge pas le suivi de Transpress directement ; son code ne contient donc aucun libellé propre à Transpress. Il consulte des services de suivi comme ParcelsApp, Ship24 et 17TRACK et affiche l’historique du premier qui trouve le colis, chaque ligne étant rédigée avec les termes de ce service. Un service peut avoir du retard par rapport au propre suivi de Transpress. Peek classe chaque ligne sous l’un de ses statuts :

| Peek affiche | Ce que cela signifie pour vous |
| --- | --- |
| « Étiquette créée » | L’expéditeur a créé l’envoi. Transpress n’a peut-être pas encore le colis. |
| « Pris en charge » | Transpress a pris le colis en charge. |
| « En transit » | Le colis est en cours d’acheminement. |
| « À la douane » | Un colis international passe en douane ; les jours passés à cette étape s’ajoutent au délai de livraison. |
| « En livraison » | Le colis est en tournée aujourd’hui. |
| « Tentative de livraison » | Le livreur a essayé de livrer le colis sans y parvenir. |
| « Prêt à être retiré » | Le colis attend que vous veniez le chercher, par exemple dans un bureau Transpress. Vérifiez où sur le suivi Transpress ou demandez à la boutique. |
| « Livré » | Livré à vous ou à la personne qui l’a réceptionné pour vous. |
| « Retourné à l’expéditeur » | Le colis est reparti chez l’expéditeur. |
| « Problème signalé » | Le service indique un incident. Lisez la ligne située juste dessous. |

Avant qu’un service ne renvoie une ligne, Peek affiche « Recherche de mises à jour » pendant la requête, puis « En attente de Transpress ». Si deux vérifications de suite échouent, le colis continue d’afficher « En attente de Transpress » et sa fiche indique « Impossible de joindre Transpress pour l’instant. Peek continue d’essayer. ». Si vous laissez Peek détecter le transporteur, la même attente affiche généralement « Aucun historique de suivi pour le moment », et « Mise à jour indisponible » signifie que deux vérifications de suite ont échoué ; Peek continue d’essayer. Plus de détails dans [signification des statuts de suivi](guide:tracking-statuses).

## Livraison Transpress en Bulgarie

D’après la page des services de messagerie de Transpress et ses conditions générales en vigueur depuis le 1er novembre 2026 :

**Délais.** Transpress indique couvrir chaque localité de Bulgarie, avec des départs quotidiens et une livraison le jour ouvré suivant ou selon calendrier. Le calendrier de votre ville figure sous « Schedule for services in settlements in Bulgaria » sur transpress.bg. Les colis sont acceptés chaque jour ouvré. La livraison le samedi est une option supplémentaire.

**À votre porte.** Le livreur remet le colis à l’adresse figurant sur le bordereau, contre signature et présentation d’une pièce d’identité (carte d’identité ou permis de conduire). En votre absence, une personne de votre foyer ou une autre personne présente à l’adresse acceptant de vous le transmettre peut le recevoir, avec votre autorisation écrite ou votre confirmation par téléphone. Sur un lieu de travail, un collègue, l’accueil ou la sécurité peut le réceptionner.

**Deux tentatives.** Le prix de la livraison comprend jusqu’à deux tentatives. La seconde intervient dans les 3 jours ouvrés suivant l’échéance de la première livraison.

**Refuser un colis.** Vous confirmez le refus par écrit sur un document Transpress.

**Dans un bureau.** Un colis peut également être livré dans un bureau Transpress, à Bozhurishte, Blagoevgrad, Bourgas, Varna, Veliko Tarnovo, Vratsa, Pleven, Plovdiv, Roussé, Stara Zagora et Choumen. Après 3 jours ouvrés de garde dans un bureau, l’expéditeur paie des frais de stockage.

**Paiement et notifications.** Le paiement à la livraison fait partie des services en option de Transpress. Le paiement par carte sur terminal POS et la notification par SMS ou Viber comptent parmi ses services gratuits.

**Vérifier à la réception.** Contrôlez le contenu en présence du livreur. En cas de dommage, vous signez ensemble un constat en deux exemplaires ou une mention sur le document de livraison.

Si une livraison échoue ou si le colis vous attend, consultez [livraison manquée](guide:missed-delivery) et [colis non retirés](guide:uncollected-parcel). Pour un colis provenant de l’extérieur de l’UE, voir [douane](guide:customs).

## Colis Transpress sans mise à jour, perdu ou retourné

**Rien pour le moment.** Un service de suivi peut ne pas encore connaître le numéro ou ne pas suivre du tout les colis Transpress. Peek continue de demander, chaque service étant réinterrogé au plus tard un jour après sa dernière absence de résultat. S’il n’y a toujours aucune ligne deux jours après l’ajout du colis, Peek affiche l’alerte « En attente de la première mise à jour du transporteur ». Vérifiez le numéro dans l’e-mail de la boutique. Si rien n’apparaît après quelques jours, ouvrez le propre suivi de Transpress avec « Ouvrir le site Transpress » et demandez à la boutique si le colis a bien été expédié.

**Aucune nouvelle depuis des jours.** Après quatre jours sans nouvelle ligne alors que le colis reste sur « Étiquette créée », « Pris en charge » ou « En transit », Peek affiche l’alerte « Aucune mise à jour depuis quatre jours » (ou « Aucune mise à jour depuis … »), avec le message « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger Transpress. » et le lien « Demande de l’aide à Transpress ». Voir [pourquoi le suivi ne se met plus à jour](guide:tracking-not-updating).

**Renvoyé.** Lorsque Transpress ne peut pas livrer (vous avez refusé le colis, vous êtes absent longtemps ou inconnu à l’adresse, ou l’adresse est erronée ou incomplète), il prévient l’expéditeur et renvoie le colis dans les 14 jours suivant cet avis, aux frais de l’expéditeur pour l’aller et le retour. Si l’expéditeur ne donne aucune instruction dans les 3 jours ouvrés ou reste injoignable, le colis est considéré comme non réclamé : Transpress le conserve 14 jours de plus, puis il devient la propriété de Transpress, qui peut le vendre ou le détruire. Voir [retourné à l’expéditeur](guide:returned-to-sender).

**Livré mais rien n’est arrivé.** Vérifiez autour de votre porte et auprès de vos voisins, puis contactez la boutique. Voir [livré mais non reçu](guide:delivered-not-received).

**Perdu ou endommagé.** Un colis est considéré comme perdu si Transpress ne parvient pas à le localiser dans les 30 jours. Vous pouvez déposer une réclamation écrite dans les 6 mois à compter du jour où Transpress a accepté le colis : pour un colis perdu, détruit ou volé, endommagé, livré en retard, retourné sans motif, ou pour un contre-remboursement versé en retard. Transpress répond sous 30 jours pour les envois en Bulgarie et 90 jours pour les envois internationaux, et indemnise toute réclamation acceptée dans les 30 jours suivant sa réponse.

Sans assurance, l’indemnisation correspond au prix du service de messagerie, dans la limite de 7,67 € en Bulgarie et 25,56 € à l’international, sur justificatif de la valeur et du dommage ; 3,00 € pour un retour sans motif. Un colis assuré est indemnisé à hauteur du dommage réel, dans la limite de la valeur assurée. Pour un retard de plus de 2 jours ouvrés : 10 % du prix du service par jour en Bulgarie, 2 % à l’international, jusqu’à 50 %. En cas de désaccord, les conditions permettent de saisir la Commission de régulation des communications (КРС) ou un tribunal bulgare.

Pour une commande en ligne, prévenez d’abord la boutique : c’est elle qui a réservé le transport et peut ouvrir une démarche ou vous renvoyer un colis. Voir [colis perdus](guide:lost-parcel) et [colis endommagés](guide:damaged-parcel).

## Contact et service client Transpress

Selon la page Contacts de transpress.bg (horaires en heure bulgare) :

- **Téléphone :** \*5015, 0700 150 50 ou 02 948 07 00. Horaires de bureau du lundi au vendredi, de 09 h 00 à 18 h 00 (heure bulgare).
- **Questions de suivi** (colis jusqu’à 2 500 kg) : aftersales@transpress.bg pour les colis en Bulgarie, international@transpress.bg pour les colis internationaux.
- **E-mail général :** transpress@transpress.bg.
- **Chat :** sur transpress.bg, tous les jours de 7 h 00 à 23 h 00 (heure bulgare), ou envoyez votre question via le formulaire du chat.
- **Réclamations :** le formulaire sous « Complaints » (« Рекламации » sur le site bulgare).

## Suivre un colis Transpress avec Peek

[Peek](/) suit plus de 4 500 transporteurs mais ne peut pas reconnaître un numéro Transpress à sa forme. Un lien vers le suivi Transpress sélectionne Transpress d’emblée ; avec le numéro seul, choisissez le transporteur vous-même :

1. Collez le numéro dans le champ ci-dessus.
2. Appuyez sur « Changer » à côté de la ligne du transporteur et recherchez Transpress. « Транспрес » le trouve aussi, affiché avec « Aussi appelé … », tout comme « Bulgaria ».
3. Sélectionnez Transpress : la ligne affiche alors « Choisi par toi ». Lancez le suivi du colis.

Sur un colis déjà ajouté, utilisez « Changer de transporteur ». Avant votre choix, la ligne du transporteur peut indiquer « Peek le trouvera quand tu lanceras le suivi » ou « pas encore trouvé · Peek continue de vérifier » : les deux sont normaux pour un nouveau numéro.

Si vous laissez le choix du transporteur à Peek, il interroge les services de suivi et classe automatiquement le colis sous Transpress dès qu’ils le montrent en mouvement avec Transpress comme transporteur.

**Ce que vous voyez.** Le statut et chaque ligne remontée par le service de suivi, avec sa date et son lieu lorsque le service les indique. Tant qu’aucun service n’a trouvé le colis, « Ouvrir le site Transpress » sur la page du colis ouvre le numéro sur le suivi de Transpress, en bulgare ; le site propose aussi une version anglaise. Dès qu’un service affiche un historique, ce lien ouvre la page du service à la place.

**Un autre transporteur.** Pendant les 30 premiers jours du colis, Peek interroge aussi les transporteurs dont le format correspond à votre numéro. Si l’un d’eux montre le colis en mouvement sous ce numéro, Peek transfère le colis vers ce transporteur et affiche « Transporteur remplacé automatiquement (ancien : Transpress) ».

**Fréquence des vérifications.** Peek vérifie le colis toutes les heures, et toutes les 3 heures dès qu’une journée passe sans nouvelles ; après sept jours sans nouvelles à l’étape « Étiquette créée », toutes les 6 heures. Le jour où il est en livraison, Peek vérifie jusqu’à toutes les 15 minutes tant que la page est ouverte ou que les notifications sont activées. Les notifications peuvent porter sur « Chaque scan », « Étapes importantes » ou « Livraison seulement ».

Peek n’affiche ni code de retrait ni nom du signataire, et ne peut ni contacter Transpress, la boutique ou l’expéditeur pour vous, ni modifier la livraison ou déposer une réclamation.

## Questions sur le suivi Transpress

### Comment suivre un colis Transpress ?

Collez un lien vers le suivi de Transpress dans le champ ci-dessus et Peek reconnaît Transpress automatiquement. Avec le numéro seul, appuyez sur « Changer » et sélectionnez Transpress : Peek ne peut pas identifier un numéro Transpress à sa forme. Il suit ensuite le colis via des services de suivi. Le suivi de Transpress se trouve sous « Shipment tracking » sur transpress.bg.

### Combien de fois Transpress tente-t-il de livrer ?

Jusqu’à deux fois : le prix de livraison inclut deux tentatives, la seconde dans les 3 jours ouvrés suivant l’échéance de la première livraison. Si la livraison échoue toujours, Transpress prévient l’expéditeur et renvoie le colis.

### Comment déposer une réclamation auprès de Transpress ?

Par écrit, dans les 6 mois suivant le jour où Transpress a accepté le colis, via le formulaire « Complaints » sur transpress.bg. Transpress répond sous 30 jours pour un colis en Bulgarie et 90 jours pour un colis international. Pour une commande en ligne, prévenez d’abord la boutique.

### Quel est le numéro de téléphone de Transpress ?

\*5015, 0700 150 50 ou 02 948 07 00. Ses bureaux sont ouverts du lundi au vendredi de 09 h 00 à 18 h 00 (heure bulgare). Pour les questions de suivi, Transpress indique aftersales@transpress.bg (en Bulgarie) et international@transpress.bg.

:::sources
- [Transpress : Shipment tracking](https://transpress.bg/en/for-clien/shipment-tracking) – suivi anglais, « Shipment number », formulaire « Complaints »
- [Transpress : Проследяване на пратка](https://transpress.bg/bg/prosledjavane) – suivi bulgare, « Номер на пратката », lien avec le numéro
- [Transpress : Куриерски услуги](https://transpress.bg/bg/kurierski-uslugi) – couverture, départs quotidiens, jour ouvré suivant ou calendrier, jours ouvrés, livraison samedi, services payants et gratuits
- [Transpress : Общи условия за куриерски услуги на Транспрес Деливъри ЕООД](https://www.transpress.bg/web/files/richeditor/obshtiusloviyatranspressdelivery20261101.pdf) – livraison, pièce d’identité, tentatives, refus, retours, stockage, douane, réclamations, indemnisation, КРС
- [Transpress : Общи условия за куриерски услуги на ТРАНСПРЕС ООД](https://www.transpress.bg/web/files/richeditor/obshtiusloviyatranspressood20261101.pdf) – mêmes conditions pour Транспрес ООД
- [Transpress : Общи условия](https://transpress.bg/bg/obshti-uslovija) – conditions de messagerie des deux sociétés
- [Transpress : Контакти](https://transpress.bg/bg/kontakti) – téléphones, horaires, e-mails, bureaux, horaires du chat
- [Transpress : График за обслужване](https://transpress.bg/bg/za-klienti/grafik-za-obslujvane) – calendrier par localité
- [Universal Parcel Scraper : Transpress](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/transpress) – noms et alias, Bulgarie, adresse du suivi, règle de lien, aucun format de numéro, aucun libellé, services de suivi uniquement
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – services de suivi, association du nom de transporteur à Transpress, liens vers la page du transporteur ou du service
:::
