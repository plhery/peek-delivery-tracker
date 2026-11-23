---
title: Suivi euShipments : statuts et transporteur local
description: Suis une commande euShipments : provenance du numéro, sens de New, Loaded hub ou On delivery, transporteur local et contacts.
slug: suivi-eushipments
published: 2026-11-22
updated: 2026-11-22
---

euShipments est une entreprise de logistique e-commerce basée à Roussé, en Bulgarie. Elle stocke, emballe et expédie les commandes de boutiques en ligne vers les 28 pays de sa liste « We deliver to » — les 27 pays de l’UE et le Royaume-Uni — puis confie chaque colis à un transporteur local pour le dernier kilomètre. Tu peux suivre ton numéro sur sa page « Track my order », [eushipments.com/tracking](https://eushipments.com/tracking), ou le coller dans le champ ci-dessus. Le numéro est celui envoyé par la boutique.

## Numéro de suivi euShipments : à quoi il ressemble

euShipments ne publie pas de format fixe. L’entreprise indique qu’une fois la commande traitée, tu reçois un « tracking code, AWB number or tracking ID » à saisir dans le champ « Enter your tracking number to track packages. » Son suivi retrouve la commande sous le numéro transmis par la boutique, qui peut être celui du transporteur final. Exemples (avec chiffres fictifs) :

| Numéro | À quoi il correspond |
| --- | --- |
| `74829103` | Huit chiffres : seul format observé par Peek sur le suivi euShipments, pour une commande livrée en Bulgarie sous le même numéro par le transporteur local. |
| Le numéro après « Tracking Number: » | Le propre numéro du transporteur local, affiché sur la page de résultat d’euShipments dès qu’il prend le colis en charge. Le suivi de ce transporteur l’accepte directement. |

Un numéro à huit chiffres correspond aussi à [Mondial Relay](carrier:mondial-relay), [Colisweb](carrier:colisweb) et [Heppner](carrier:heppner), donc le numéro seul ne désigne pas euShipments : vérifie le nom indiqué dans l’e-mail de la boutique. Un lien vers la page de suivi d’euShipments n’ouvre pas ta commande : la page n’accepte le numéro que via son formulaire. Aucun numéro ? Consulte [comment retrouver un numéro de suivi](guide:find-tracking-number).

## Statuts de suivi euShipments expliqués

La page de résultat affiche « My order N » suivi de ton numéro, puis un tableau à trois colonnes : « Date/Time », « Tracking Status » et « Tracking Information ». Le statut utilise les libellés anglais d’euShipments, identiques quel que soit le transporteur final. « Tracking Information » reprend à côté les messages propres du transporteur dès qu’il a le colis. Chaque ligne est datée à la minute près, par exemple « 10 August, 2026 15:39 », sans fuseau horaire.

| euShipments indique | Ce que cela signifie pour toi |
| --- | --- |
| `New` | La boutique a enregistré la commande chez euShipments. Elle n’est peut-être pas encore emballée. |
| `Warehouse` | La commande se trouve dans l’entrepôt logistique d’euShipments nommé après ce mot. |
| `Loaded hub` | Chargé dans un hub d’euShipments pour le trajet vers le suivant. Le nom du hub suit, avec son pays entre parenthèses, par exemple (HR). |
| `Unloaded hub` | Déchargé dans un hub, indiqué de la même manière. |
| `Picked up` | Le transporteur local a pris le colis en charge. |
| `In transit` | En acheminement dans le réseau du transporteur. |
| `On delivery` | En cours de livraison avec le livreur. |
| `Delivered` | Livré. C’est le dernier statut d’une commande. |

`Loaded hub` et `Unloaded hub` peuvent apparaître plusieurs fois, pour chaque hub traversé. Après `Picked up`, trois lignes s’affichent sous le tableau : « Courier Name: », « Tracking Number: » et « Tracking Link: », soit le transporteur qui te livre, son numéro et le lien vers son suivi.

Peek n’a pas observé de statut propre à euShipments pour une livraison manquée, un colis en point relais ou un retour. Pour ces cas, lis le message du transporteur dans « Tracking Information » ou ouvre son lien de suivi.

## Qui livre un colis euShipments dans ton pays

euShipments confie le dernier kilomètre à des transporteurs locaux. L’entreprise achemine les colis par liaison routière depuis ses centres logistiques et de cross-docking (Sofia et Roussé en Bulgarie, Oradea en Roumanie, Zagreb en Croatie, Ljubljana en Slovénie, Senec en Slovaquie, Budapest en Hongrie) jusqu’au dépôt d’un transporteur local. Sa FAQ indique plus de 60 transporteurs locaux, au moins deux par pays européen, et sa liste « We deliver to » couvre 28 pays : les 27 de l’UE et le Royaume-Uni.

Sa page d’accueil affiche les logos de partenaires clés (« Key eCommerce partnerships »), dont Speedy, Sameday, [FAN Courier](carrier:fan-courier), Express One, GLS, DPD, BOX NOW, Pošta Slovenije, [Slovenská pošta](carrier:slovenska-posta), PPL, ACS et [Geniki Taxydromiki](carrier:geniki-taxydromiki), sans préciser lequel livre où. L’historique de l’entreprise mentionne aussi un partenariat stratégique avec Austrian Post en 2026, sans en détailler le périmètre. Le transporteur final dépend de ton pays et du mode de livraison choisi lors de la commande : la ligne « Courier Name: » te le révèle.

**Modes de livraison.** En Bulgarie, euShipments livre à domicile ou en entreprise, ou hors domicile : agence du transporteur, point de retrait (PUDO) ou casier. Ses pages pour la Croatie et la Roumanie listent des options similaires. euShipments recense plus de 150 000 points hors domicile sur son réseau et précise que, lorsque le colis est prêt au retrait, tu reçois une notification avec les instructions.

**Tentatives, délai de garde et nouvelle livraison.** Ce sont les règles du transporteur final qui s’appliquent : euShipments n’en publie aucune. Utilise « Tracking Link: » et « Tracking Number: » pour consulter la page du transporteur et le contacter pour une nouvelle tentative ou un changement d’adresse.

**Paiement à la livraison.** euShipments propose le contre-remboursement dans de nombreux pays européens : le transporteur local encaisse le montant, qui est ensuite reversé à la boutique.

## Colis euShipments bloqué, perdu ou retourné

**Bloqué sur `New`.** La boutique a créé la commande chez euShipments, mais aucun scan d’entrepôt ou de hub n’a encore suivi. Demande à la boutique quand elle expédie.

**Bloqué entre deux hubs.** Après `Loaded hub`, le colis est en route vers le hub suivant et la ligne suivante apparaît généralement au déchargement. Si rien ne bouge au-delà du délai annoncé par la boutique, contacte-la. Voir [pourquoi un suivi ne se met plus à jour](guide:tracking-not-updating).

**Bloqué après `Picked up`.** Le transporteur local a le colis. Ouvre « Tracking Link: » et consulte son suivi avec son numéro.

**Livré, mais introuvable.** C’est le transporteur local qui a livré : commence par lui, puis contacte la boutique. Voir [colis livré mais non reçu](guide:delivered-not-received).

**Renvoyé à l’expéditeur.** La page retours d’euShipments classe comme envoi non livré tout colis impossible à remettre à cause d’informations client erronées ou de problèmes de contact : il repart à l’entrepôt d’euShipments ou à la boutique, pas chez toi. Demande un nouvel envoi ou un remboursement à la boutique. Plus de détails dans [retourné à l’expéditeur](guide:returned-to-sender).

**Retourner un colis toi-même.** euShipments gère les retours de trois façons pour les boutiques : demande de retour sur le site du vendeur en choisissant date, heure et lieu d’enlèvement ; enlèvement réservé par la boutique ; ou renvoi par tes soins, avec le transporteur de ton choix, à l’adresse locale de retour indiquée par la boutique (un entrepôt euShipments). Les conditions de retour de la boutique précisent le mode applicable. Voir [suivre un retour](guide:returns-tracking).

**Perdu ou endommagé.** euShipments ne publie aucune procédure de réclamation pour les destinataires : le contrat est conclu avec la boutique. Dans l’UE, le vendeur est responsable des dommages de l’expédition jusqu’à la réception et doit livrer sous 30 jours sauf accord différent. S’il ne le fait pas, relance-le en accordant un délai supplémentaire raisonnable ; s’il ne livre toujours pas, tu peux annuler et obtenir un remboursement. Voir [colis perdu](guide:lost-parcel) et [colis endommagé](guide:damaged-parcel).

## Téléphone et service client euShipments

euShipments indique les coordonnées suivantes :

- **Téléphone :** +359 700 70 755, un numéro bulgare, présent sur ses pages contact, FAQ et suivi. Aucun horaire n’est indiqué.
- **E-mail :** office@eushipments.com.
- **Formulaire de contact :** un questionnaire de devis destiné aux boutiques (volume mensuel, marchés visés), pas aux destinataires.

Sa FAQ destinée aux boutiques précise qu’un support par téléphone, e-mail et chat en direct pour les clients finaux est proposé sur demande (« when requested »). Adresse-toi donc d’abord à la boutique ; pour la livraison elle-même, le transporteur nommé sous « Courier Name: » répond plus vite.

## Suivre un colis euShipments avec Peek

[Peek](/) ne peut pas reconnaître un numéro euShipments à sa seule forme : aucune règle de format n’existe pour euShipments, et un numéro à 8 chiffres correspond faiblement à d’autres transporteurs. Colle-le tout de même : Peek suit plus de 4 500 transporteurs. Appuie ensuite sur « Changer » pour sélectionner euShipments, ou utilise « Changer de transporteur » sur un colis déjà ajouté.

Peek lit le suivi d’euShipments. Il affiche le statut, l’historique et, pour les étapes d’entrepôt et de hub, le lieu (avec le pays pour les hubs). Il conserve les statuts anglais d’euShipments, sauf « In transit » et « Delivered », affichés « En transit » et « Livré », et ignore les textes du transporteur dans « Tracking Information ». Lorsque « Tracking Link: » mène vers un transporteur suivi par Peek — comme [Econt](carrier:econt), Sameday, [FAN Courier](carrier:fan-courier), [Packeta](carrier:packeta), [Slovenská pošta](carrier:slovenska-posta) ou [Geniki Taxydromiki](carrier:geniki-taxydromiki) — avec son numéro dans le lien, Peek affiche « Livraison par … » et ce second numéro s’il diffère du tien. Tant que ce transporteur n’a que l’annonce du colis sans scan propre, Peek affiche « Annoncé à …, pas encore reçu ». Dès que ce transporteur montre une étape datée au moins aussi récente que la dernière ligne d’euShipments, Peek bascule dessus tout en conservant les lignes d’euShipments ; sinon, il reste sur l’historique d’euShipments.

euShipments ne fournissant ni date de livraison prévue ni preuve de livraison, Peek n’en affiche pas. Les heures sans fuseau horaire sont lues sur l’horloge du pays du hub pour les lignes de hub (heure bulgare pour un hub en Bulgarie), sur celle d’une ligne de hub voisine pour les autres, ou à l’heure bulgare s’il n’y en a pas, puis converties dans ton fuseau horaire (« Heures dans ton fuseau horaire : »). Peek vérifie jusqu’à toutes les 10 minutes, et toutes les 2 minutes à partir de 6 h sur l’horloge du dernier scan le jour où le colis passe « En livraison », tant que le colis est ouvert dans Peek ou le jour de la livraison avec les notifications activées, lorsque le suivi d’euShipments répond. Après deux jours sans nouvelle (12 heures tant que le colis reste sur « Étiquette créée » ou « Pris en charge »), il vérifie toutes les heures, et après sept jours sans nouvelle sur « Étiquette créée », toutes les 6 heures. Pour les notifications, tu peux choisir « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Après quatre jours sans scan sur un colis en « Étiquette créée », « Pris en charge » ou « En transit », Peek affiche « Aucune mise à jour depuis … », rappelle « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger euShipments. » et propose « Demande de l’aide à euShipments ». Peek ne peut pas contacter euShipments, la boutique ni le transporteur à ta place.

## Questions sur le suivi euShipments

### euShipments est-elle une entreprise fiable ?

Oui. C’est un prestataire logistique de Roussé, en Bulgarie, fondé en 2012 sous le nom InOut Trade et renommé euShipments.com en 2019. Il expédie pour des boutiques en ligne, y compris des vendeurs sur Temu et eMAG grâce à ses propres intégrations.

### Quel transporteur livre mon colis euShipments ?

Un transporteur local dans ton pays. Dès qu’il a le colis, la page de résultat d’euShipments affiche « Courier Name: », « Tracking Number: » et « Tracking Link: » sous le tableau.

### Combien de temps prend une livraison euShipments ?

Cela dépend de l’entrepôt où se trouve le stock. Depuis ses centres de Sofia et Roussé, euShipments annonce le lendemain pour la Bulgarie et la Roumanie, et 2 à 4 jours pour la Croatie, la Slovénie, la Slovaquie et la Tchéquie. Depuis Zagreb, Ljubljana ou Senec, elle annonce 1 jour ouvré dans le pays concerné. Pour ta commande, fie-toi au délai indiqué par la boutique. Plus de détails dans [délais de livraison](guide:delivery-times).

### Que signifie Loaded hub sur euShipments ?

Le colis a été chargé dans l’un des hubs d’euShipments pour l’étape suivante, identifié avec son pays entre parenthèses. `Unloaded hub` indique son arrivée au hub suivant.

### Dans quels pays euShipments livre-t-elle ?

Sa liste « We deliver to » mentionne 28 pays : les 27 de l’UE et le Royaume-Uni. Dans chacun, un transporteur local assure la livraison finale.

:::sources
- [euShipments : Track Your Order](https://eushipments.com/tracking) – formulaire de suivi et libellés, téléphone, e-mail
- [euShipments : Parcel Tracking For End Customers](https://eushipments.com/ecommerce-delivery/new-parcel-tracking/parcel-tracking-for-end-customers) – tracking code, AWB number ou tracking ID, plus de 60 partenaires du dernier kilomètre
- [euShipments : We deliver to](https://eushipments.com/we-deliver-to) – liste des 28 pays : les 27 de l’UE et le Royaume-Uni
- [euShipments : FAQ](https://eushipments.com/faq) – plus de 60 transporteurs, au moins deux par pays, support clients finaux sur demande, téléphone, e-mail
- [euShipments : Contact Us](https://eushipments.com/contact) – téléphone, e-mail, Roussé, formulaire de devis pour boutiques
- [euShipments : About us](https://eushipments.com/about-us) – InOut Trade, 2012, changement de nom en 2019, partenariat Austrian Post
- [euShipments : Home](https://eushipments.com/) – logos des partenaires e-commerce clés
- [euShipments : eCommerce Deliveries To Bulgaria](https://eushipments.com/countries/bulgaria) – Sofia et Roussé, liaison vers le dépôt du transporteur, livraison à domicile et hors domicile, délais, contre-remboursement
- [euShipments : International eCommerce Delivery To Romania](https://eushipments.com/countries/romania) – Oradea, Budapest, options de livraison
- [euShipments : Domestic and International eCommerce Delivery To Croatia](https://eushipments.com/countries/croatia) – Zagreb et autres centres, livraison le lendemain, casiers, points de retrait, agences
- [euShipments : International Parcel Delivery To Slovenia](https://eushipments.com/countries/slovenia) – Ljubljana, 1 jour ouvré
- [euShipments : International Delivery To Slovakia](https://eushipments.com/countries/slovakia) – Senec, 1 jour ouvré
- [euShipments : Out-of-home Delivery](https://eushipments.com/ecommerce-delivery/out-of-home-delivery) – 150 000 points, notifications de retrait
- [euShipments : Returns Management](https://eushipments.com/returns-management) – envois non livrés, trois modes de retour
- [euShipments : Temu Integration](https://eushipments.com/integration/ecommerce-platform-integration/temu) – vendeurs Temu
- [euShipments : eMAG Integration](https://eushipments.com/integration/ecommerce-platform-integration/emag-integration) – vendeurs eMAG
- [Your Europe : Shipping and delivery](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_en.htm) – 30 jours, responsabilité du vendeur jusqu’à réception, relance et remboursement
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – libellés de la page de résultat, statuts, numéros, ce que Peek lit et affiche
:::
