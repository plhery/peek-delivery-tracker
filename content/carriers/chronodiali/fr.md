---
title: Suivi Chronodiali : numéros CHPU, statuts et contact
description: Suivre un colis Chronodiali au Maroc : le numéro CHPU, les statuts de suivi, le paiement à la livraison, les trois tentatives et les contacts.
slug: suivi-chronodiali
published: 2026-11-23
updated: 2026-11-23
---

Chronodiali (aussi écrit Chrono Diali) est une société marocaine de livraison dédiée au commerce en ligne, filiale commune de Barid Al-Maghrib (Poste Maroc) et de Geopost. Elle livre les colis e-commerce dans tout le Maroc, à domicile ou dans l’une de ses agences. Si la boutique a choisi le paiement à la livraison, tu règles le livreur à l’arrivée du colis, en espèces ou par carte bancaire. Suis ton colis dans « Suivre mon colis » sur [chronodiali.ma](https://chronodiali.ma/suivi_colis.html) ou dans la case ci-dessus. Le numéro vient de la boutique qui a expédié le colis ; l’exemple de Chronodiali commence par `CHPU`.

## Ton numéro de suivi Chronodiali

Les formats reconnus par Peek, avec des chiffres inventés :

| Numéro | Ce que c’est |
| --- | --- |
| `CHPU4829103756182947305` | `CHPU` suivi de 19 chiffres : un numéro Chronodiali. Peek le reconnaît aussitôt. |
| `CD4829103756` | `CD` suivi de 10 chiffres. Peek peut aussi le suivre chez Chronodiali, mais ce format correspond également à [Colis Privé](carrier:colis-prive), [Aramex Australie](carrier:aramex-au) et [Aramex Nouvelle-Zélande](carrier:aramex-nz), donc Peek interroge les quatre (voir ci-dessous). |
| `chronodiali.ma/suivi_colis.html?id=…` | Un lien vers la page de suivi de Chronodiali. Peek identifie Chronodiali quel que soit le numéro inclus. |

Chronodiali n’est pas [Chronopost](carrier:chronopost), le transporteur express français : leurs numéros et leurs suivis sont distincts. Aucun numéro ? Demande à la boutique ou consulte [où trouver un numéro de suivi](guide:find-tracking-number).

## Statuts de suivi Chronodiali expliqués

Chronodiali publie son suivi en français. Voici les mentions habituelles dans l’ordre où un colis les rencontre :

| Ce que dit Chronodiali | Ce que montre Peek | Ce que cela signifie pour toi |
| --- | --- | --- |
| `Les données du colis ont été intégrées dans nos systèmes.` | Étiquette créée | La boutique a transmis les informations du colis à Chronodiali, qui n’a pas encore le colis. |
| `Le ramassage a été programmé.` | Étiquette créée | Une collecte chez la boutique est planifiée. |
| `Le ramassage est en attente, le livreur se présentera sous peu.` | Étiquette créée | En attente du passage du livreur chez l’expéditeur. |
| `Le colis a été collecté par le livreur.` | Pris en charge | Le livreur a collecté le colis. |
| `Le colis est arrivé au hub d'origine.` | En transit | Arrivé au premier hub de Chronodiali. |
| `Le colis a quitté notre hub.`, `Le colis est arrivé au hub.` | En transit | En circulation entre les hubs de Chronodiali. |
| `Delivery Scheduled` | En transit | Placé sur une tournée au hub de destination. |
| `Out For Delivery`, `Le colis est en cours de livraison.` | En livraison | Le livreur l’a aujourd’hui. Si tu paies à la livraison, prépare le montant. |
| `Le colis a été livré.` | Livré | Livré. |
| `La livraison a échoué : le client n'a pas répondu ou était injoignable.` | Tentative de livraison | Le livreur n’a pas réussi à te joindre par téléphone ou à la porte. |
| `La livraison a échoué : le lieu de livraison est incorrect ou introuvable.` | Tentative de livraison | L’adresse est erronée ou le livreur n’a pas trouvé le lieu. |
| `La livraison a échoué : le client est indisponible.` | Tentative de livraison | Tu n’étais pas disponible. |
| `La livraison a échoué : le client n'a pas le montant COD requis.` | Tentative de livraison | Tu n’avais pas le montant du paiement à la livraison (COD). |
| `La livraison a échoué : le client a refusé le colis.` | Tentative de livraison | Le colis a été refusé. |
| `La livraison a échoué.` | Tentative de livraison | Échec sans motif précisé. |
| `Le colis est retenu au hub.` | Problème signalé | Le colis est retenu dans un hub. |
| `La livraison a été annulée : le colis est endommagé et ne peut pas être livré.` | Problème signalé | Le colis est endommagé et ne sera pas livré. |
| `Le colis a été annulé.` | Problème signalé | L’envoi a été annulé. |
| `Le colis va être renvoyé à l'expéditeur.` | Retourné à l’expéditeur | Le colis repart vers la boutique. |
| `Le colis a été retourné à l'adresse de l'expéditeur.` | Retourné à l’expéditeur | Il est revenu à l’adresse de la boutique. |

**Absent de chronodiali.ma.** La page de suivi officielle omet certaines de ces étapes : `Delivery Scheduled`, `Out For Delivery`, les échecs de livraison, les annulations ou reprogrammations et `Le colis a été confié à un partenaire tiers.` Peek les affiche, de sorte que son historique peut être plus complet que celui de Chronodiali.

**Autres libellés.** `La livraison a été reprogrammée : une nouvelle date a été définie.` indique un report à une nouvelle date ; `La livraison a été reprogrammée.` ne précise pas de détail. `La livraison a été annulée à la demande du client.` signale une annulation demandée par le client. `Le colis est en attente au hub.` signifie que le colis attend dans un hub. Pour les deux mentions `partenaire tiers`, voir les questions ci-dessous ; Peek les classe en « En transit ».

**Colis venus de l’étranger.** Certains envois arrivent au Maroc depuis un autre pays. Tu peux alors voir `arrivé dans le pays.` (arrivé au Maroc ; Peek affiche « En transit »), `dédouanement en cours` (« À la douane ») et `dédouanement terminé` (« En transit »). Le site de Chronodiali ne dit rien des droits ni de qui les règle : demande à la boutique et consulte [colis bloqués en douane](guide:customs).

## Livraison Chronodiali : paiement à la livraison, trois tentatives et agences

Chronodiali indique livrer dans tout le Maroc, vers plus de 300 destinations, à domicile ou en agence.

- **Paiement à la livraison :** la page de suivi affiche le `Montant à payer` en dirhams (DH). Chronodiali précise que tu peux régler à la remise en espèces ou par carte bancaire.
- **Trois tentatives :** Chronodiali mentionne « 3 Tentatives de livraison » dans ses services. Dans Peek, chaque essai manqué apparaît sous une ligne commençant par `La livraison a échoué`, avec ou sans motif.
- **Garde ton téléphone allumé :** l’un des motifs d’échec est que tu n’as pas répondu ou étais injoignable.
- **Notifications :** sa page services mentionne les alertes par SMS et par e-mail ainsi qu’une notification client à chaque étape.
- **Agences :** Agadir, Casablanca, Fès, Marrakech, Rabat et Tanger, ouvertes du lundi au vendredi de 9 h 00 à 18 h 00 et le samedi de 9 h 00 à 13 h 00 (heure du Maroc). Les adresses figurent sur sa page « Points de livraison ».

Le site de Chronodiali ne précise pas combien de temps une agence garde un colis ni ce qui se passe après la troisième tentative échouée : demande à Chronodiali ou à la boutique. Livreur manqué ? Voir [livraisons manquées](guide:missed-delivery).

## Colis Chronodiali en retard, refusé, retourné ou non reçu

Pour un remboursement ou un nouvel envoi, contacte la boutique : c’est elle la cliente de Chronodiali, qui réserve les ramassages depuis la plateforme liée à son contrat.

- **Aucun mouvement après `Les données du colis ont été intégrées dans nos systèmes.` :** la boutique a créé l’expédition mais Chronodiali n’a pas encore collecté le colis. Demande à la boutique quand elle le remettra. Si chronodiali.ma affiche `En cours de traitement.`, Chronodiali connaît le numéro mais n’a encore aucun scan.
- **En retard :** Chronodiali ne publie pas de délais de livraison. Ses conditions précisent qu’ils sont communiqués à la commande et varient selon la destination. Quand la dernière ligne date de quatre jours ou plus et que le colis en est encore à « Étiquette créée », « Pris en charge » ou « En transit », Peek le signale avec le jour de cette ligne (« Aucune mise à jour depuis … ») et ajoute un lien « Demande de l’aide à Chronodiali ». Voir [suivi qui ne se met plus à jour](guide:tracking-not-updating).
- **Refusé ou non payé :** si tu refuses le colis ou ne peux pas payer, le livreur enregistre un échec de livraison. Le colis peut ensuite repartir vers la boutique : `Le colis va être renvoyé à l'expéditeur.`, puis `Le colis a été retourné à l'adresse de l'expéditeur.` Voir [retourné à l’expéditeur](guide:returned-to-sender).
- **Livré mais non reçu :** Chronodiali fournit aux boutiques une preuve numérique de livraison dans leur espace client. Demande à la boutique de la vérifier. Plus d’informations dans [livré mais non reçu](guide:delivered-not-received).
- **Endommagé ou perdu :** préviens d’abord la boutique. Tu peux aussi cocher « Réclamation ? » sur le formulaire de contact de Chronodiali, ou l’appeler ou lui écrire avec ton numéro. Voir [colis endommagés](guide:damaged-parcel) et [colis perdus](guide:lost-parcel).
- **Un message demandant de l’argent :** sur son site, Chronodiali présente le paiement comme effectué à la livraison, en espèces ou par carte. Si un SMS ou un e-mail réclame un paiement via un lien, vérifie d’abord le suivi et consulte [SMS d’arnaque à la livraison](guide:delivery-scam-texts).

## Numéro de contact et service client Chronodiali

Coordonnées indiquées sur chronodiali.ma :

- **Téléphone :** +212 5 20 61 62 63 (05 20 61 62 63 depuis le Maroc).
- **E-mail :** service-client@chronodiali.ma.
- **Formulaire de contact :** sur la page [Contact](https://chronodiali.ma/contact_us.html), sélectionne « Réclamation ? » pour une réclamation ou « Demande d’information ? » pour une question. Le formulaire ne demande que ton nom, ton e-mail, ton téléphone et le nom de la boutique, sans champ pour un message ni un numéro de suivi ; Chronodiali indique qu’elle te recontactera.
- **Agences :** les six villes ci-dessus, avec le même numéro de téléphone et la même adresse e-mail.

Le site n’indique pas d’horaires distincts pour la ligne téléphonique et ne mentionne aucun numéro WhatsApp.

## Suivre un colis Chronodiali avec Peek

Colle un numéro `CHPU` et [Peek](/) affiche aussitôt Chronodiali sous « Transporteur détecté ». Les minuscules et les espaces ne gênent pas. Un lien vers la page de suivi chronodiali.ma fonctionne aussi.

Un numéro `CD` à 10 chiffres ne suffit pas à identifier le transporteur. Colle-le quand même : Peek suit plus de 4 500 transporteurs. Peek affiche « Vérification des services de suivi… » pendant qu’il interroge Chronodiali et les autres transporteurs partageant cette forme ; si Chronodiali seul a le colis, son nom apparaît avec « a ce colis ». Tu peux aussi appuyer sur « Changer » et choisir « Chronodiali » sous « Correspond à ce numéro ». Si tu sélectionnes Chronodiali pour un numéro d’un autre format, Peek affiche que Chronodiali « n’émet pas de numéros comme celui-ci », et le colis passe à « Numéro de suivi à vérifier ».

Peek lit directement le suivi de Chronodiali : le statut, chaque ligne telle qu’écrite par Chronodiali (en français, parfois en anglais comme les deux lignes ci-dessus), le nom du hub lorsqu’il est indiqué, le pays de destination et l’heure de livraison. Lorsque Chronodiali les fournit, Peek te montre aussi le nom du destinataire, sous « Destinataire », et, tant que le colis est en route, la ville de destination sur la carte quand Peek la situe au Maroc ; une personne avec qui tu partages le colis ne voit ni l’un ni l’autre, et le nom de la personne qui a signé reste privé : aucune app ne l’affiche. Peek convertit les heures dans le fuseau horaire de ton appareil. Il laisse de côté le nom et la ville de l’expéditeur, le montant à payer, la preuve de livraison et les appels du livreur. Le suivi de Chronodiali ne fournit ni date de livraison prévue ni poids. Les lignes évoquant un partenaire tiers ne le nomment pas, et Peek ne bascule pas sur le suivi de ce partenaire.

Si Chronodiali ne connaît pas encore le numéro, Peek affiche « En attente de Chronodiali » et continue de vérifier ; si Chronodiali ne le connaît toujours pas après deux jours, Peek te demande de le vérifier, de choisir un autre transporteur ou de supprimer le colis. Tant que le colis est ouvert dans Peek, ou le jour de sa livraison avec les notifications activées, Peek le vérifie jusqu’à toutes les 10 minutes, et toutes les 2 minutes à partir de 6 h, heure du Maroc, le jour où Chronodiali le scanne en livraison, tant que le suivi de Chronodiali répond. Après deux jours sans nouvelle (12 heures tant que le colis reste sur « Étiquette créée » ou « Pris en charge »), Peek vérifie chaque heure, et après sept jours sans nouvelle en « Étiquette créée », toutes les 6 heures. Tu peux activer les alertes pour « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Peek ne peut ni encaisser le paiement, ni reprogrammer une livraison, ni contacter Chronodiali ou la boutique à ta place.

## Questions sur le suivi Chronodiali

### Chronodiali fait-elle partie de Poste Maroc ?

En partie. La page d’actualités de Chronodiali la décrit comme une coentreprise détenue à 51 % par Barid Al-Maghrib (Poste Maroc) et à 49 % par Geopost. Elle possède son propre suivi sur chronodiali.ma et son propre service client.

### Combien de tentatives de livraison fait Chronodiali ?

Trois tentatives, selon Chronodiali. Dans Peek, chaque essai échoué apparaît avec une ligne commençant par `La livraison a échoué`, accompagnée ou non d’un motif.

### Puis-je payer un colis Chronodiali par carte bancaire ?

Oui. Chronodiali précise que tu peux régler à la livraison en espèces ou par carte bancaire.

### Que signifie « partenaire tiers » dans le suivi Chronodiali ?

`Le colis a été assigné à un partenaire tiers.` et `Le colis a été confié à un partenaire tiers.` signifient que Chronodiali a attribué ou remis le colis à une société partenaire. Le suivi ne nomme pas ce partenaire : attends l’étape suivante ou contacte Chronodiali avec ton numéro.

### Comment contacter Chronodiali au sujet de mon colis ?

Appelle le +212 5 20 61 62 63 ou écris à service-client@chronodiali.ma avec ton numéro de suivi. Pour un remboursement ou un nouvel envoi, adresse-toi à la boutique.

:::sources
- [Chronodiali : Les experts de la livraison e-commerce](https://chronodiali.ma/) – Barid Al-Maghrib et Geopost, paiement à la livraison en espèces ou par carte, livraison à domicile ou en agence, plus de 300 destinations, agences et horaires, téléphone, e-mail
- [Chronodiali : Suivre mon colis](https://chronodiali.ma/suivi_colis.html) – suivi, exemple `CHPU`, montant à payer, lignes de statut en français, lignes affichées par la page et « En cours de traitement. » (dans le script de la page)
- [Chronodiali : Nos services](https://chronodiali.ma/nos_service.html) – trois tentatives de livraison, notifications SMS et e-mail, espèces ou carte, preuve de livraison numérique
- [Chronodiali : Points de livraison](https://chronodiali.ma/point_de_livraison.html) – livraison dans tout le Maroc, agences et horaires
- [Chronodiali : Envoyer un colis](https://chronodiali.ma/envoyercolis.html) – comptes et ramassages des boutiques, preuves de livraison pour les marchands
- [Chronodiali : Contact](https://chronodiali.ma/contact_us.html) – formulaire de contact avec « Réclamation ? » et ses champs, téléphone, e-mail
- [Chronodiali : Gouvernance](https://chronodiali.ma/aboutus.html) – Barid Al-Maghrib (Poste Maroc)
- [Chronodiali : Actualités](https://chronodiali.ma/actualites.html) – coentreprise, 51 % Barid Al-Maghrib et 49 % Geopost
- [Chronodiali : Mention légale](https://chronodiali.ma/mentions_legal.html) – Chrono Diali S.A., Casablanca ; conditions : contrat et ramassages de la boutique, délais annoncés à la commande
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formats de numéros, lignes de statut et étapes, ce que montrent la page Chronodiali et Peek
:::
