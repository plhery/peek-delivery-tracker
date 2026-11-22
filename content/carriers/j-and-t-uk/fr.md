---
title: Suivi J&T Express UK : numéros JTUK et statuts
description: Suivre un colis J&T Express UK : format du numéro JTUK, signification des statuts de suivi, droits au Royaume-Uni et que faire en cas de retard ou de perte.
slug: suivi-jt-express-uk
published: 2026-11-21
updated: 2026-11-21
---

J&T Express UK livre des colis au Royaume-Uni. Si une boutique a expédié ta commande avec J&T, le numéro de suivi commence par `JTUK` : colle-le dans le champ ci-dessus ou consulte-le sur le suivi de J&T à l'adresse www.jtexpress.uk/tracking. Le numéro figure dans l'e-mail d'expédition ou sur la page de commande de la boutique. Peek n'ayant pas pu lire le site web de J&T Express UK, cette page s'appuie sur ce que le code de Peek sait du suivi de J&T et sur les sources officielles britanniques.

## Numéro de suivi J&T Express UK : à quoi il ressemble

Le bordereau de J&T Express UK est composé de `JTUK` suivi de 12 chiffres, soit 16 caractères au total. Peek ne connaît aucun autre format pour ce transporteur. Exemples avec des chiffres inventés :

| Numéro (inventé) | Ce que c'est |
| --- | --- |
| `JTUK482910573628` | `JTUK` suivi de 12 chiffres : un colis J&T Express UK. Peek reconnaît aussitôt J&T Express UK. |
| `JTUK48291057362` | Il manque un chiffre : ne correspond pas au format de J&T Express UK et Peek ne reconnaît aucun transporteur. Vérifie qu'il ne manque pas un chiffre. |
| `JTDE482910573628` | `JTDE` et 12 chiffres : [J&T Express Germany](carrier:j-and-t-de), pas le réseau britannique. |
| `JTAU482910573628` | `JTAU` et 12 chiffres : [J&T Express Australia](carrier:j-and-t-au). |
| `JTE482910573628` | `JTE` et 12 chiffres : [J&T Express Middle East](carrier:j-and-t-middle-east). |
| `JT4829105736284` | `JT` et 13 chiffres : les réseaux J&T d'Asie du Sud-Est et de Chine ([J&T Express China](carrier:j-and-t-cn)). |

Sur peektracker.com, un numéro `JTUK` auquel il manque un caractère ou qui en compte un de trop affiche un avis tel que : « Ça commence comme un numéro de suivi J&T Express UK, mais il manque un caractère : il y a peut-être une faute de frappe. Compare-le avec ton message d’expédition. » Dans Peek, les espaces, tirets et minuscules ne comptent pas : `jtuk 4829 1057 3628` se lit comme la première ligne. Le catalogue de Peek connaît aussi le transporteur sous les noms J&T Express (UK), J&T Express UK Ltd et J&T UK.

**Où le trouver.** Dans l'e-mail d'expédition de la boutique, sur la page de commande du vendeur ou de la plateforme, ou dans tout message relatif à la livraison. S'il est introuvable, consulte [comment trouver ton numéro de suivi](guide:find-tracking-number) ou demande au vendeur.

## Statuts de suivi J&T Express UK expliqués

Le suivi de J&T affiche chaque scan sous forme d'une heure et d'une phrase, sans lieu. Voici les phrases recensées dans le code de Peek sur des colis J&T Express UK, dans l'ordre où un colis les rencontre :

| Ce qu'indique J&T | Ce que cela signifie pour toi | Peek affiche |
| --- | --- | --- |
| `The parcel has been successfully picked up and is being prepared for transportation` | J&T a pris le colis en charge chez l'expéditeur et prépare son acheminement. | « Pris en charge » |
| `The parcel arrives at the distribution center and is prepared for transport to the next destination` | Le colis est arrivé dans un centre de tri J&T. | « En transit » |
| `The parcel is dispatched from the transit center to the next destination` | Il a quitté ce centre vers l'étape suivante. | « En transit » |
| `The parcel has been collected and is on its way` | Le livreur a pris le colis pour sa tournée. Malgré la formulation, il ne s'agit pas de l'enlèvement initial : J&T enregistre cette ligne comme un scan de livraison. | « En livraison » |
| `The parcel has been successfully delivered to the recipient` | Livré. | « Livré » |
| `The delivery address is incomplete or incorrect.` | Échec de la tournée : le livreur n'a pas pu livrer à l'adresse telle qu'indiquée. Dans l'historique présent dans le code de Peek, cette ligne apparaît quelques heures après le départ en tournée, le même jour. | « Tentative de livraison » |
| `Delivery was attempted, but no one was available to receive the package at the address; the package was taken to a pickup location for the customer to collect.` | Personne n'était présent pour recevoir le colis ; il a été déposé en point de retrait pour que tu puisses le récupérer. | « Tentative de livraison » |

Une phrase absente de ce tableau suit les règles générales de Peek : si Peek ne la reconnaît pas, elle s'affiche telle que J&T l'a écrite et compte comme « En transit », sauf si le colis est déjà en livraison ou à une étape ultérieure, auquel cas le statut reste inchangé.

Si une phrase se termine par un numéro de service client ou une invitation à appeler, Peek la supprime. Il retire aussi le texte entre crochets, les numéros de téléphone ainsi que les noms du signataire, du réceptionnaire, du livreur, du destinataire ou de l'expéditeur, et conserve le reste.

## Livraison J&T Express UK : ce que dit le suivi

Dans les historiques présents dans le code de Peek, le livreur de J&T part en tournée puis livre le colis, signale un problème d'adresse ou dépose le colis en point de retrait faute de destinataire présent. Le code de Peek ne montre aucun transfert vers un autre transporteur : le suivi de J&T accompagne le colis jusqu'à destination.

**En livraison.** `The parcel has been collected and is on its way` indique que ton colis est dans la tournée du jour. Le suivi de J&T ne fournissant ni créneau horaire ni jour prévu, Peek ne peut pas en afficher non plus.

**Problème d'adresse.** Après `The delivery address is incomplete or incorrect.`, vérifie l'adresse sur ta commande. S'il manque un élément (numéro d'appartement, code postal), préviens aussitôt la boutique qui a réservé l'envoi. Voir [comment modifier une adresse de livraison](guide:change-delivery-address) et [livraison manquée](guide:missed-delivery).

**Tentatives, lieux sûrs et points de retrait.** Peek n'ayant pas pu lire le site web de J&T Express UK, cette page n'indique pas le nombre de tentatives, les règles de dépôt chez un voisin ou en lieu sûr, ni la durée de garde d'un colis non livré. Renseigne-toi auprès du vendeur ou dans tes messages de livraison.

**Commandes depuis l'étranger vers le Royaume-Uni.** Selon GOV.UK, pour des marchandises provenant de l'étranger, l'entreprise qui livre te contacte si une TVA, des droits de douane ou des frais de livraison sont dus, en envoyant une facture indiquant le montant exact à payer. Elle garde en général le colis environ 3 semaines, puis le retourne à l'expéditeur faute de paiement. Pour des biens d'une valeur de 135 £ ou moins achetés toi-même (hors produits soumis à accises comme l'alcool, le tabac ou les cigarettes électroniques), le vendeur inclut déjà la TVA dans le prix. Au-dessus de 135 £, tu paies la TVA au transporteur avant la livraison ou au retrait, plus d'éventuels droits de douane selon la marchandise et son origine. L'Irlande du Nord applique des règles propres. Peek n'a pas pu vérifier comment J&T facture les frais d'importation. Voir [colis retenu à la douane](guide:customs).

## Colis J&T Express UK en retard, perdu ou retourné

C'est la boutique qui a choisi J&T : pour un achat en ligne, elle est donc ton premier interlocuteur. En vertu du Consumer Rights Act 2015 (section 29), les marchandises achetées auprès d'un professionnel restent aux risques du vendeur jusqu'à leur remise matérielle entre tes mains ou celles de la personne que tu as désignée, sauf si tu as mandaté toi-même un transporteur non proposé par le vendeur.

**Pas de mouvement.** Un colis en « Pris en charge » ou « En transit » sans nouvelle ligne pendant quatre jours est signalé dans Peek avec le jour de son dernier scan (« Aucune mise à jour depuis … ») et un lien vers la page de contact de J&T Express UK (« Demande de l’aide à J&T Express UK »). Si rien ne bouge ensuite, contacte la boutique. Voir [pourquoi le suivi ne se met plus à jour](guide:tracking-not-updating).

**Pas encore de suivi.** Un numéro que le suivi de J&T ne connaît pas encore s'affiche comme « En attente de J&T Express UK ». Une boutique peut générer le numéro avant de remettre le colis. Si J&T ne le connaît toujours pas deux jours plus tard et qu'aucune autre source n'a répondu, Peek affiche « Après deux jours, le transporteur ne connaît toujours pas ce numéro. Vérifie-le, choisis un autre transporteur ou supprime le colis. », puis vérifie une fois par jour, et une fois par semaine passée une semaine depuis l'ajout. Vérifie le numéro auprès du vendeur et demande si le colis a bien été expédié.

**Livré, mais rien reçu.** Vérifie autour de ta porte, auprès de ton foyer et des voisins, puis préviens la boutique : d'après la loi britannique, le colis reste à ses risques jusqu'à sa remise effective ; demande un remboursement ou un renvoi. Voir [colis livré mais non reçu](guide:delivered-not-received).

**Perdu ou endommagé.** Demande au vendeur un remboursement ou un remplacement avec ton numéro de suivi. Peek n'a pas pu lire les règles de réclamation de J&T ; voir [colis perdu](guide:lost-parcel) et [colis endommagé](guide:damaged-parcel).

**Retourné.** Si le colis repart chez le vendeur après un échec de livraison, c'est le vendeur qui traite le remboursement ou le renvoi. Voir [colis retourné à l'expéditeur](guide:returned-to-sender).

## Service client et contact J&T Express UK

Peek n'ayant pas pu lire le site web de J&T Express UK, cette page ne fournit aucun numéro de téléphone, e-mail ni chat : Peek ne mentionne que des coordonnées vérifiées sur les pages propres du transporteur. Le registre britannique Companies House indique comme siège social `C/O Tmf Group 13th Floor, One Angel Court, London, United Kingdom, EC2R 7HJ`, une adresse légale domiciliée chez un tiers et non un guichet colis.

Contacte d'abord la boutique qui a réservé l'envoi, ou passe par l'aide sur la page de commande d'une place de marché. Le suivi propre à J&T se trouve sur www.jtexpress.uk/tracking.

## Suivre un colis J&T Express UK avec Peek

[Peek](/) reconnaît aussitôt J&T Express UK à partir d'un numéro `JTUK` (« Transporteur détecté ») et interroge le suivi de J&T avec ce seul numéro, sans code postal ni compte. Un lien www.jtexpress.uk/tracking contenant `?q=` suivi du numéro fonctionne aussi ; s'il contient un autre type de numéro, Peek lit ce numéro comme s'il avait été collé seul. Pour tout autre numéro, colle-le tout de même : Peek suit plus de 4 500 transporteurs. Pour un numéro `JT` à 13 chiffres, Peek affiche « Vérification des services de suivi… » le temps de la recherche. Choisir J&T Express UK via « Changer » pour un autre format ne sert à rien : Peek indique qu'il « n’émet pas de numéros comme celui-ci ».

Peek affiche le statut, chaque scan à son heure exacte et l'heure de livraison. Comme J&T fournit chaque scan comme un instant exact, Peek affiche toutes les heures dans le fuseau horaire de ton appareil, été comme hiver ; sur peektracker.com et dans les applications iPhone et Android, l'historique se termine par « Heures dans ton fuseau horaire : » et son nom. Les scans restent dans l'anglais de J&T. J&T ne fournissant ni lieu, ni date prévue, ni poids, Peek n'affiche pas de carte. J&T peut afficher une preuve de livraison que Peek laisse de côté. « Ouvrir le site J&T Express UK », sur la page du colis, ouvre ton numéro sur le suivi de J&T.

Tant que J&T n'a pas scanné le colis, Peek continue de vérifier avec « En attente de J&T Express UK » (ou demande de vérifier le numéro après deux jours). Lorsque le suivi de J&T est injoignable ou ne connaît pas le numéro, Peek interroge des services de suivi et affiche leur historique dans leurs propres mots.

Tant que tu gardes le colis ouvert à l'écran ou le jour de la livraison avec les notifications activées, et que le suivi de J&T répond, Peek vérifie jusqu'à toutes les 10 minutes, et jusqu'à toutes les 2 minutes dès 6 h (heure locale) le jour où J&T scanne le colis en livraison ; sinon, toutes les heures. Lorsque ce sont des services de suivi qui répondent, Peek vérifie toutes les heures (toutes les 3 heures après une journée sans nouvelle), et jusqu'à toutes les 15 minutes le jour de la livraison tant que le colis est ouvert ou que les notifications sont activées. Après deux jours sans nouveau scan (12 heures tant qu'il affiche « Pris en charge »), Peek vérifie toutes les heures. Tu peux activer des notifications pour « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Peek ne peut ni contacter J&T ou la boutique à ta place, ni modifier l'adresse ou reprogrammer la livraison.

## Questions sur le suivi J&T Express UK

### J&T Express UK est-il fiable ?

Oui. Companies House enregistre `J&T EXPRESS UK LTD` comme société à responsabilité limitée active, immatriculée le 18 décembre 2025, avec des activités déclarées incluant le transport routier de marchandises, l'entreposage et les activités postales. Ses numéros commencent par `JTUK` et Peek lit directement son suivi.

### Que signifie « The parcel has been collected and is on its way » chez J&T ?

Ton colis est en cours de livraison. Le livreur l'a récupéré pour sa tournée ; J&T enregistre cette ligne comme un scan de livraison et Peek affiche « En livraison ». Cela ne signifie pas que le colis vient seulement de quitter l'expéditeur.

### Pourquoi J&T indique-t-il que mon adresse est incomplète ou incorrecte ?

Le livreur n'a pas pu livrer parce qu'une information manquait ou ne correspondait pas. Vérifie l'adresse sur ta commande et contacte immédiatement la boutique avec l'adresse complète et ton numéro de suivi.

### Pourquoi mon numéro de suivi J&T est-il introuvable ?

Vérifie qu'il commence par `JTUK` suivi de 12 chiffres : les numéros des autres réseaux J&T, comme `JTDE` en Allemagne, ne relèvent pas du réseau britannique. Si le numéro est correct, J&T n'a peut-être pas encore le colis et Peek affiche « En attente de J&T Express UK ». Si J&T ne le connaît toujours pas au bout de deux jours et qu'aucune autre source n'a répondu, Peek te propose de le vérifier, de choisir un autre transporteur ou de supprimer le colis. Après plusieurs jours sans trace, demande à la boutique.

### Comment contacter J&T Express UK ?

Commence par la boutique qui a réservé la livraison. Peek n'ayant pas pu lire le site web de J&T Express UK, cette page n'indique aucun numéro de téléphone ni adresse e-mail.

:::sources
- [Universal Parcel Scraper : J&T Express UK](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/j-and-t-uk) – noms, format `JTUK`, liens de suivi, page d'aide `jtexpress.uk/contact`, phrases du suivi et leurs étapes (y compris le dépôt en point de retrait après absence), horaires exacts, absence de lieu/date prévue/poids, preuve de livraison exclue
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formats des autres réseaux J&T, filtrage des lignes de service client, crochets, noms et téléphones, absence de transfert vers un autre transporteur, services de suivi de secours
- [Companies House : J&T EXPRESS UK LTD](https://find-and-update.company-information.service.gov.uk/company/16920174) – société à responsabilité limitée active, immatriculée le 18 décembre 2025, nature d'activité, siège social
- [GOV.UK : Tax and customs for goods sent from abroad](https://www.gov.uk/goods-sent-from-abroad/tax-and-duty) – facture du transporteur, garde d'environ 3 semaines, TVA jusqu'à 135 £ et au-delà, droits de douane, Irlande du Nord
- [UK legislation : Consumer Rights Act 2015, section 29](https://www.legislation.gov.uk/ukpga/2015/15/section/29) – transfert des risques au vendeur jusqu'à la remise matérielle au consommateur
:::
