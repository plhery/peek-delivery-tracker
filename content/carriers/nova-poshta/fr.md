---
title: Suivi Nova Poshta : numéros, statuts et agences
description: Suis un colis Nova Poshta (Nova Post) : numéro EW, signification des statuts, retrait en agence ou consigne, jours de garde et contacts.
slug: suivi-nova-poshta
published: 2026-11-22
updated: 2026-11-22
---

Nova Poshta, affichée sous le nom « Nova Post » sur son site en anglais, est un transporteur express ukrainien qui livre en agence, en consigne et à domicile dans toute l'Ukraine, avec des agences en Pologne et dans d'autres pays européens. Suis un colis avec son numéro de lettre de transport express (EW) sur [la page de suivi de novaposhta.ua](https://novaposhta.ua/en/tracking/), dans son application ou en le collant dans le champ ci-dessus. Le numéro figure en haut du bordereau, dans l'application et dans l'avis d'arrivée.

## Numéro de suivi Nova Poshta : le numéro EW

Nova Poshta appelle ce numéro « express waybill », abrégé EW (ЕН ou ТТН en ukrainien). Le SMS ou la notification push annonçant l'arrivée du colis indique le numéro de l'agence et le numéro EW.

Nova Poshta ne publie ni longueur ni format officiels. Les numéros connus du catalogue de Peek ont la forme suivante :

| Format (inventé) | À quoi il correspond |
| --- | --- |
| `20404829173648` | 14 chiffres commençant par 204, 206, 207, 208, 590 ou 595 |
| `51482917364829` | 14 chiffres commençant par 21 ou 51 |
| `SHPL4829173648` | Numéro international Nova Post : SH ou TN, un code pays à deux lettres et 10 chiffres, parfois précédés de RS |

Les formats proches qui appartiennent à d'autres transporteurs :

- **Finit par `UA`**, comme `RR482917367UA` : un numéro postal international. C'est [Ukrposhta](carrier:ukrposhta), la poste nationale, et non Nova Poshta.
- **13 chiffres** : le format national d'Ukrposhta, pas un EW.
- **Finit par `G`**, comme `KL4829173MN48291G` : un numéro Meest.
- **D'autres numéros à 14 chiffres** peuvent appartenir à DPD, SEUR, BRT ou d'autres transporteurs : vérifie lequel figure dans le SMS ou l'e-mail.

Plus de détails dans [les formats de numéros de suivi](guide:tracking-number-formats). Si le site affiche « We have not found a parcel with this number », vérifie les chiffres : le suivi n'affiche plus rien pour un bordereau de plus de 90 jours.

## Les statuts de suivi Nova Poshta expliqués

novaposhta.ua affiche le suivi en ukrainien ou en anglais, du mouvement le plus récent au plus ancien, chacun avec la date, l'heure et la ville. Les chiffres dans les libellés indiquent le numéro d'agence.

| Statut Nova Poshta | Ce qu'il signifie pour toi |
| --- | --- |
| `Registered and being prepared` | L'expéditeur a créé le bordereau. Nova Poshta n'a pas encore reçu le colis. |
| `Accepted at branch 12` | L'expéditeur a déposé le colis dans cette agence. |
| `Customs clearance in progress` | La douane contrôle l'envoi ; des droits peuvent être dus (voir plus bas). |
| `Delivery location has been changed` | Le colis a été redirigé, par toi ou par l'expéditeur. |
| `The recipient has changed the delivery time` | La livraison par coursier a été déplacée à un autre créneau ; le colis est toujours en route. |
| `The courier has left for the recipient` | Le colis est en cours de livraison à ton adresse. |
| `Arrived at the branch 12` | Prêt à être retiré dans cette agence. |
| `Received at branch 12` | Tu l'as retiré. |
| `Delivered at the address` | Le coursier l'a livré. |
| `Return` | Un retour à l'expéditeur a été demandé. |
| `Returned` | Le colis est revenu à l'agence de l'expéditeur. |

## Agence, consigne ou coursier : retirer ton colis

**En agence**, montre le code-barres de l'application, ou donne le numéro EW et confirme par téléphone ou avec une pièce d'identité. Une pièce d'identité est exigée si la valeur déclarée dépasse 30 000 ₴ ou si tu paies plus de 5 000 ₴. Tu peux ouvrir le colis devant l'agent.

**Garde.** Depuis le 1er octobre 2026, une agence conserve un colis gratuitement pendant 5 jours à compter du lendemain de son arrivée, puis facture par jour : 15 ₴ jusqu'à 2 kg, 25 ₴ jusqu'à 10 kg, 35 ₴ jusqu'à 30 kg. Au bout de 30 jours, le colis est considéré comme non réclamé.

**Les consignes** acceptent jusqu'à 20 kg. Ouvre la tienne avec « Open the locker » dans l'application, Bluetooth et localisation activés, dans les 5 jours ; passé ce délai, le colis est transféré vers l'agence la plus proche. Les points de retrait dans les commerces, pharmacies et stations-service gardent les colis jusqu'à 5 jours avant de les renvoyer vers l'agence la plus proche.

**La livraison par coursier** ajoute 60 ₴ pour un colis jusqu'à 30 kg et s'effectue de 9 h à 21 h en semaine, selon un horaire spécial le week-end. Tu l'as manquée ? Reprogramme-la ou fais déposer le colis dans l'agence la plus proche ; après des tentatives infructueuses, il y est de toute façon transféré.

**La réexpédition** vers une autre agence est gratuite tant que le colis n'a pas atteint sa destination (60 ₴ vers une adresse) ; après son arrivée, elle coûte le prix d'un nouvel envoi.

**En Pologne**, Nova Post livre en agence, dans un Paczkomat [InPost](carrier:inpost), en point de retrait ou à domicile. Le code d'ouverture arrive par SMS et par e-mail. Le colis attend dans le Paczkomat pendant 2 jours calendaires, puis Nova Post le transfère dans l'une de ses agences et t'en informe ; pour récupérer un colis ainsi transféré, appelle le +48 573 504 069.

## Colis Nova Poshta en retard, perdu ou endommagé

Les pages de Nova Poshta ne précisent pas quoi faire quand le suivi se fige ; sa FAQ polonaise cite des coordonnées erronées du destinataire et des articles interdits parmi les causes de retard. Demande d'abord à l'expéditeur, et consulte [le suivi qui ne se met plus à jour](guide:tracking-not-updating).

Pour les colis entrant en Ukraine, Nova Poshta indique que le destinataire paie les droits éventuels et est prévenu par SMS, Viber ou e-mail : au-delà de 150 €, 10 % de droits de douane sur l'excédent plus 20 % de TVA. Plus de détails dans [les colis retenus en douane](guide:customs).

En cas de perte ou de dommage en Ukraine, fais une réclamation dans l'application ou sur la page de suivi (« Manage Parcel », puis « Submit a Report »), par téléphone ou en agence, avec ton numéro fiscal ou ton passeport. Nova Poshta répond sous 3 jours ouvrés et indemnise jusqu'à la valeur déclarée.

En Pologne, c'est l'expéditeur qui dépose la réclamation ; tu ne peux réclamer que si le colis t'a été livré ou si l'expéditeur te cède ce droit. Les conditions de Nova Post Pologne prévoient 12 mois à compter de l'envoi (2 mois pour un manque ou un dommage sur un colis coursier).

## Service client et numéro de téléphone Nova Poshta

Coordonnées publiées sur les sites de Nova Poshta et Nova Post Pologne :

- **Ukraine, 24 h/24 :** +38 098 450 0609, +38 050 450 0609, +38 093 450 0609 ou +38 044 450 0609.
- **Centre de contact :** 0 800 500 609, mentionné sur les pages FAQ et consignes.
- **E-mail :** support_ua@novaposhta.ua, plus un bot Telegram proposé par [novaposhta.ua](https://novaposhta.ua/).
- **Pologne :** +48 182 111 609, tous les jours de 8 h à 20 h (heure de Pologne), ou support@novapost.pl.

> Nova Poshta met en garde contre de faux e-mails affirmant que tu lui dois de l'argent ; elle n'envoie aucun message concernant « Reliable Purchase » ni aucun lien vers un formulaire demandant tes coordonnées bancaires.

## Suivre un colis Nova Poshta avec Peek

D'autres transporteurs partagent les formats à 14 chiffres, et [Peek](/) ne désigne jamais Nova Poshta à partir du numéro seul. Il interroge à la place les transporteurs qui les utilisent : Nova Poshta en premier si tu recherches depuis l'Ukraine ou la Moldavie ou si le numéro commence par 595000, sinon d'autres transporteurs, mais pas Nova Poshta. Pour un numéro international SH ou TN (parfois précédé de RS), Nova Poshta est le seul transporteur interrogé. Si Nova Poshta le reconnaît, Peek affiche « Nova Poshta (Ukraine) a ce colis. ». Sinon, appuie sur « Changer » et choisis « Nova Poshta (Ukraine) ». Un lien de suivi de novaposhta.ua ou novapost.com fonctionne aussi. Peek couvre les bordereaux ukrainiens, y compris ceux enregistrés dans des agences Nova Post à l'étranger, ainsi que les numéros internationaux SH et TN de Nova Post, mais pas les références Nova Poshta Global.

Peek affiche les libellés en anglais de Nova Poshta, la ville de chaque scan sur une carte, l'arrivée prévue, le poids, les dimensions d'un envoi monocolis, la ville et le pays de destination (hors retour), l'agence ou la consigne qui le détient et l'heure de livraison, tout en écartant noms, adresses complètes et données de paiement. Les horaires sont convertis dans ton fuseau horaire (« Heures dans ton fuseau horaire : »). Quand un envoi à l'export indique son partenaire à l'étranger, [UPS](carrier:ups) ou [GOFO Express](carrier:gofo), Peek peut continuer à suivre le colis chez lui et adopte alors le rythme de vérification du transporteur qui assure la livraison.

Peek interroge directement Nova Poshta jusqu'à toutes les 10 minutes, ou toutes les 2 minutes à partir de 6 h (heure de Kyiv) le jour où Nova Poshta scanne le colis en livraison, tant que le colis est ouvert dans Peek ou le jour de la livraison avec les notifications activées ; sinon, toutes les heures. Après deux jours sans nouvelle (12 heures tant que le colis reste sur « Étiquette créée » ou « Pris en charge »), Peek passe à une vérification par heure. Pour les notifications, choisis « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Après quatre jours sans scan en transit, hors douane ou incident signalé, Peek affiche « Aucune mise à jour depuis… », rappelle qu'« Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger Nova Poshta (Ukraine). » et propose « Demande de l’aide à Nova Poshta (Ukraine) ».

## Questions fréquentes sur le suivi Nova Poshta

### Nova Poshta est-elle fiable ?

Oui. Nova Poshta a été fondée en Ukraine en 2001, dirige le groupe NOVA et indique livrer environ 1,5 million de colis et d'envois de fret par jour.

### Peut-on suivre Nova Poshta en anglais ?

Oui : la version anglaise de novaposhta.ua affiche chaque statut en anglais.

### Un e-mail de livraison Nova Post peut-il être une arnaque ?

C'est possible. Nova Poshta indique que ses vrais e-mails proviennent uniquement de ses domaines officiels ; n'ouvre pas de liens provenant d'expéditeurs inconnus.

### Combien de temps Nova Poshta garde-t-elle un colis en agence ?

En Ukraine, 5 jours gratuitement à compter du lendemain de l'arrivée, puis moyennant un tarif journalier ; après 30 jours, le colis est considéré comme non réclamé.

:::sources
- [Nova Post: Track your parcel](https://novaposhta.ua/en/tracking/) – libellés de statut, message introuvable, bouton de réclamation
- [Nova Post: FAQ](https://novaposhta.ua/en/more/common-questions) – numéro EW, pièce d'identité, rappels de garde, tentatives coursier, réclamations, centre de contact
- [Nova Post: Pick up at the branch](https://novaposhta.ua/en/receive/at-branch) – SMS d'arrivée, modalités de retrait, seuils d'identité, inspection
- [Nova Post: Parcel storage](https://novaposhta.ua/en/receive/additional-services/parcel-storage) – 5 jours gratuits, tarifs journaliers, 30 jours
- [Нова пошта: Нова пошта змінює термін безплатного зберігання відправлень](https://novaposhta.ua/news/nova-poshta-zminiuie-termin-bezplatnoho-zberihannia-posylok/) – passage de 7 à 5 jours depuis le 1er octobre 2026
- [Nova Post: Pick up at the parcel locker](https://novaposhta.ua/en/receive/at-parcel-locker) – poids maximal, application, 5 jours
- [Nova Post: Receive at the pickup point](https://novaposhta.ua/en/receive/at-pickup-point) – points de retrait, durée de garde
- [Nova Post: Receive at a certain address](https://novaposhta.ua/en/receive/at-address) – tarif coursier, horaires, livraison manquée
- [Nova Post: Forwarding](https://novaposhta.ua/en/receive/additional-services/redirection) – règles et frais de réexpédition
- [Nova Post: Customs clearance in Ukraine](https://novaposhta.ua/en/receive/international-delivery/customs-clearance) – qui paie, seuil de 150 €, notifications
- [Нова пошта: Правила надання послуг](https://static.novaposhta.ua/sitecard/misc/doc/Rules_for_providing_postal_services.pdf) – responsabilité
- [Нова пошта: page d'accueil](https://novaposhta.ua/) – numéros en pied de page, 24 h/24, e-mail, Telegram
- [Nova Post: Caution, cyber attack](https://novaposhta.ua/en/caution-cyber-attack) – faux e-mails de créance
- [Нова пошта: Надійна покупка](https://novaposhta.ua/receive/additional-services/safe-purchase/) – avertissement contre les faux messages
- [Nova Post Poland: accueil](https://novapost.com/pl-pl/) – téléphone, horaires, e-mail
- [Nova Post Poland: Śledź przesyłkę](https://novapost.com/pl-pl/tracking/) – langues, causes de retard
- [Nova Post Poland: Odbiór w urządzeniach Paczkomat](https://novapost.com/pl-pl/receive/at-parcel-locker/) – 2 jours, téléphone
- [Nova Post Poland: Regulamin](https://novapost.com/pl-pl/more/offer/) – 2 jours en Paczkomat, réclamations, délais
- [Nova Post Poland: O firmie](https://novapost.com/pl-pl/more/about) – fondation, groupe NOVA, volumes
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – données lues par Peek et partenaires UPS et GOFO Express
:::
