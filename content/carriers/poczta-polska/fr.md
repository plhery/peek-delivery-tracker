---
title: Suivi Poczta Polska : numéros, statuts et points relais
description: Suivre un colis Poczta Polska ou Pocztex : formats de numéro, traduction des statuts, avis de passage, lockers, douane et contacts.
slug: suivi-poczta-polska
published: 2026-11-22
updated: 2026-11-22
---

Poczta Polska est l'opérateur postal public de Pologne, et Pocztex est son service de messagerie. Tu peux suivre un colis ou une lettre recommandée sur la page eMonitoring de Poczta Polska, qui propose une version anglaise, ou coller le numéro dans le champ ci-dessus. Le numéro figure sur la preuve de dépôt de l'expéditeur : la boutique qui a envoyé le colis le possède, et les SMS et avis de Poczta Polska le reprennent.

## Numéro de suivi Poczta Polska : où le trouver

Saisis-le sans espaces ni parenthèses. Le suivi couvre tous les envois déposés avec preuve de dépôt, à l'exception des lettres avec valeur déclarée ; les lettres recommandées n'affichent que les dates de dépôt et de distribution.

| Format (inventé) | Ce que c'est |
| --- | --- |
| `PX4829105734` | `PX` suivi de 10 chiffres. |
| `0045900773482910573` | 19 chiffres. |
| `00459007734829105736` | 20 chiffres, avec `5900773` après les trois premiers (ou 18 chiffres sans les deux zéros `00` initiaux). |
| `RR482910576PL` | Format postal international : deux lettres, neuf chiffres et `PL`. |

Où le trouver :

- **La preuve de dépôt de l'expéditeur** : demande à la boutique si ton e-mail de commande ne contient aucun numéro.
- **Le message de retrait** pour un point ou un casier Pocztex, qui indique le numéro, le lieu, la date limite et un code à usage unique.
- **Le SMS, l'e-mail ou la notification après livraison manquée**, avec le numéro, la date limite et l'adresse du bureau de poste.

Si eMonitoring répond « The given package number is incorrect », vérifie chaque caractère ainsi que le transporteur annoncé par la boutique. Pas de numéro du tout ? Consulte [où trouver son numéro de suivi](guide:find-tracking-number).

## Ce que signifient les statuts de suivi Poczta Polska

eMonitoring s'ouvre en polonais ; sa version anglaise affiche les libellés ci-dessous, avec la date, l'heure et le bureau de poste pour chaque étape :

| Statut Poczta Polska | Ce que ça veut dire pour toi |
| --- | --- |
| `Electronic item data received` | L'expéditeur a enregistré l'envoi. Poczta Polska ne l'a pas encore en main. |
| `Posting/collection`, `Item arrived at the shipping point` | Déposé dans un bureau ou un point, ou collecté chez l'expéditeur. |
| `In transport` | En acheminement entre centres de tri. |
| `Departure from Poland` | L'envoi a quitté la Pologne vers un autre pays. |
| `Arrival at inward office of exchange (Poland)` | Un colis venu de l'étranger a atteint le centre d'échange polonais. |
| `Customs service` | Opérations douanières. |
| `Item returned from import customs` | Libéré par la douane à l'importation. |
| `In delivery` | En cours de livraison, ou tentative infructueuse (voir ci-dessous). |
| `Unsuccessful (physical) delivery` | Personne n'était présent. Un avis a été laissé et l'envoi va au bureau de poste ou repart en tournée. |
| `Attempted delivery - redirected` | Tentative suivie d'une réexpédition. |
| `Delivery at sender's request - delivery attempt` | Tentative de livraison effectuée à la demande de l'expéditeur. |
| `Unsuccessful (physical) delivery - lost` | Perdu en cours de livraison. |
| `Ready for pick-up at the Post Office`, `Added to postal notice book - Item can be collected`, `Ready for pick up at the point` | T'attend au bureau de poste ou au point indiqué dans le scan. |
| `Collected at the post office`, `Collected at the delivery point` | Retiré au bureau de poste ou au point de retrait. |
| `Final delivery`, `Delivered to the PO box`, `Delivery at sender's request - delivered` | Livré. |
| `Returned`, `Returned to sender`, `Not collected at the delivery point` | Retourne chez l'expéditeur. |

`Posting/collection` correspond au dépôt par l'expéditeur, pas à ton retrait. Poczta Polska nomme aussi l'un de ses événements d'échec de livraison `In delivery` : Peek distingue les deux grâce au code d'événement et affiche « Tentative de livraison » pour celui-ci plutôt que « En livraison ». Une ligne `Customs service` peut apparaître après la livraison : elle comptabilise les droits perçus à la porte. Sans « (Poland) », `Arrival at inward office of exchange` signifie qu'un colis parti de Pologne a atteint son pays de destination. Sur la page polonaise, une livraison manquée s'affiche `Awizowana` et un colis en attente au bureau `Oczekuje na odbiór`. Poczta Polska précise que les intitulés peuvent évoluer.

## Livraison manquée Poczta Polska : avis, points relais et lockers

**Colis coursier Pocztex.** Le livreur remet le colis à toi-même, à un adulte de ton foyer ou à une personne mandatée. Si tu es absent, tu es prévenu par SMS, e-mail ou application lorsque l'expéditeur a transmis tes coordonnées, sinon par un avis dans la boîte aux lettres, suivi d'un second message deux jours plus tard. Le colis attend 7 jours au bureau de poste à compter du lendemain de l'avis, puis retourne à l'expéditeur. Tu peux aussi appeler le livreur pour lui demander de déposer le colis près de ton adresse (sauf contre-remboursement, valeur déclarée, port dû ou remise en mains propres « do rąk własnych »), à tes risques.

**Points de retrait et lockers.** Dans un point Pocztex (`PUNKT`) choisi par l'expéditeur, le colis attend 7 jours dans un bureau de poste ou 3 jours dans un commerce partenaire comme Żabka, ABC ou Lewiatan. Les casiers (`AUTOMAT`), situés près de bureaux de poste, stations-service et magasins tels que Biedronka, le conservent 72 heures, avec des rappels après 24 et 48 heures. L'ouverture se fait avec ton numéro de téléphone et le code, ou via l'application Pocztex Mobile. Le délai n'est pas prolongeable et le colis ne peut pas être déplacé vers un autre point, mais toute personne disposant du code peut le retirer.

**Lettres recommandées et colis postaux.** Un avis (*awizo*) est déposé dans la boîte aux lettres, ou envoyé par voie électronique sur demande. Un second avis suit au bout de 7 jours, et l'envoi repart s'il n'est pas retiré dans les 14 jours suivant le premier. Contre paiement, tu peux demander au bureau ou sur poczta-polska.pl jusqu'à 14 jours de garde supplémentaires.

## Colis Poczta Polska en retard, perdu, endommagé ou en douane

Le suivi de Poczta Polska est fourni à titre informatif et ne fonde pas de réclamation, et l'opérateur ne publie pas de marche à suivre si le suivi se fige : demande à l'expéditeur et consulte [suivi qui ne bouge plus](guide:tracking-not-updating).

**Qui peut réclamer.** L'expéditeur, ou toi-même une fois l'envoi livré ou si l'expéditeur t'a cédé son droit : en ligne sur poczta-polska.pl (eReklamacje), par écrit ou directement dans tout bureau de poste.

**Délais** pour les envois nationaux selon les conditions de Poczta Polska :

- Dans les 12 mois suivant le dépôt.
- Dommage visible : à la livraison, consigné dans un procès-verbal. Tu peux vérifier le contenu d'un colis visiblement endommagé, sauf en point relais ou en locker.
- Dommage caché ou contenu manquant : dans les 7 jours suivant la réception.
- Perte : un colis Pocztex est réputé perdu 30 jours après le dépôt ; la perte d'un colis postal peut être déclarée 14 jours après le dépôt, hors dimanches et jours fériés.
- Poczta Polska répond sous 30 jours ; tu disposes de 14 jours pour faire appel.

Envois internationaux : dans les six mois à compter du lendemain du dépôt.

**Douane.** Depuis le 1er juillet 2026, les envois hors UE d'une valeur allant jusqu'à 150 € supportent un droit de douane européen de 3 € par catégorie de marchandise, perçu par Poczta Polska ; les cadeaux entre particuliers jusqu'à 45 € sont exonérés. Depuis le 1er novembre 2026 s'ajoute une taxe européenne de traitement de 2 € par article (des produits identiques peuvent compter pour un seul) sur les achats en ligne expédiés depuis l'extérieur de l'UE : Poczta Polska la perçoit auprès du destinataire et la reverse à l'administration fiscale polonaise (KAS). Peuvent également s'ajouter la TVA si elle n'a pas été payée à l'achat ainsi que les frais de déclaration douanière de Poczta Polska (20 PLN, ou 25 PLN si tu effectues la formalité toi-même), payables à la livraison ou avant le retrait. Tu peux refuser le colis. Plus de détails dans [colis retenu en douane](guide:customs).

## Numéro de téléphone et service client Poczta Polska

Coordonnées publiées sur les pages officielles de Poczta Polska et Pocztex :

- **Ligne générale :** +48 438 420 600, du lundi au vendredi de 8 h à 20 h, au tarif de ton opérateur, ainsi qu'un formulaire en ligne sur la [page de contact](https://www.poczta-polska.pl/en/kontakt/).
- **Pocztex :** 804 104 104 depuis les fixes polonais, +48 43 842 0 842 depuis les mobiles et l'étranger, du lundi au vendredi de 8 h à 20 h et le samedi de 8 h à 16 h.
- **Questions sur le suivi :** par e-mail à kontakt@poczta-polska.pl.
- **Problème en point de retrait** (colis absent, point fermé) : Contact Center au 801 333 444 depuis un fixe polonais ou au +48 438 420 600.

> Poczta Polska met en garde contre de faux SMS Pocztex évoquant une livraison manquée avec des liens vers des sites contrefaits. Pocztex n'envoie jamais de lien de paiement et ne demande jamais de données de carte bancaire par SMS. Transfère tout SMS suspect au 8080.

## Suivre un colis Poczta Polska avec Peek

[Peek](/) suit plus de 4 500 transporteurs. Il reconnaît tout de suite les numéros `PX` à clé de contrôle valide, les numéros de 20 chiffres comportant `5900773` après les trois premiers avec une clé de contrôle valide, ainsi que les numéros de 18 chiffres sans les deux zéros `00` initiaux (que Peek ajoute lui-même). Pour les numéros de 19 chiffres commençant par `00`, il n'interroge que Poczta Polska en ajoutant la clé de contrôle comme le fait le site officiel. Un numéro international valide terminé par `PL` commençant par R, E, L ou C (sauf CV), comme `RR`, `EE` ou `CP`, est attribué directement à Poczta Polska ; un autre numéro valide en `PL`, par exemple en `VV`, s'affiche comme « Transporteur postal inconnu » pendant que Peek interroge Poczta Polska et [Chronopost](carrier:chronopost), et Poczta Polska l'emporte s'il le connaît. Les liens eMonitoring contenant `numer=` fonctionnent aussi ; aucun code postal n'est requis.

Peek affiche les libellés anglais tels quels, sauf `Returned to sender`, affiché « Retourné à l’expéditeur » (ou les libellés polonais si tu utilises Peek en polonais), chaque bureau de poste sur une carte, le poids, le nom du service, le pays de destination et le bureau qui garde un colis prêt à être retiré. Il n'y a pas de date de livraison prévue. Les adresses et horaires des bureaux, les documents de paiement et les services additionnels sont écartés. Les scans relayés depuis l'étranger n'ont pas de fuseau horaire : Peek les lit à l'heure du pays d'où ils proviennent. Les palettes, envois groupés et courriers non recommandés ne peuvent pas être suivis. Quand un colis enregistré, pris en charge ou en transit reste quatre jours sans nouveau scan, Peek signale « Aucune mise à jour depuis lun 12 oct » (le jour du dernier scan) et affiche « Demande de l’aide à Poczta Polska ».

Quand tu gardes le colis ouvert à l'écran, ou le jour de la livraison avec les notifications activées, Peek vérifie jusqu'à toutes les 10 minutes — et jusqu'à toutes les 2 minutes à partir de 6 h (heure locale) le jour où Poczta Polska scanne le colis en livraison — tant que le suivi propre de Poczta Polska répond. Le reste du temps, et après deux jours sans nouvelle (12 heures tant que le colis reste en « Étiquette créée » ou « Pris en charge »), Peek vérifie toutes les heures. Via les seuls services de suivi, c'est toutes les heures, toutes les 3 heures après une journée sans nouvelle, et jusqu'à toutes les 15 minutes le jour où le colis est en livraison, tant que tu l'as ouvert ou que les notifications sont activées. Les notifications sont réglables sur « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Un colis expédié de l'étranger vers la Pologne peut basculer automatiquement vers Poczta Polska.

## Questions sur le suivi Poczta Polska

### Qu'est-ce que Pocztex ?

Pocztex est le service de messagerie de Poczta Polska, avec son propre site pocztex.pl et l'application Pocztex Mobile. Ses colis utilisent le même suivi eMonitoring.

### Peut-on suivre Poczta Polska en anglais ?

Oui. eMonitoring s'ouvre en polonais mais dispose d'une version anglaise.

### Combien de temps met Pocztex à livrer ?

Poczta Polska garantit la livraison de Pocztex Kurier au plus tard le cinquième jour ouvré après le dépôt, ou le septième pour les colis 2XL et hors gabarit. Les jours ouvrés vont du lundi au vendredi hors jours fériés.

### Puis-je rediriger un colis Poczta Polska ?

Pas en tant que destinataire. Les conditions de Poczta Polska réservent les changements d'adresse ou de destinataire à l'expéditeur, et les colis envoyés vers un point ou un casier Pocztex ne peuvent pas être transférés vers un autre.

:::sources
- [Poczta Polska : Track your shipment](https://www.poczta-polska.pl/en/sledzenie-przesylek/) – format des numéros, périmètre, points de retrait, opérateurs étrangers
- [Poczta Polska : Śledź przesyłkę](https://www.poczta-polska.pl/sledzenie-przesylek/) – mêmes informations en polonais
- [Poczta Polska : eMonitoring (anglais)](https://emonitoring.poczta-polska.pl/?lang=en) – exemples, message d'erreur, caractère informatif, kontakt@
- [Poczta Polska : eMonitoring](https://emonitoring.poczta-polska.pl/) – version polonaise, libellés de statut
- [Pocztex : Śledzenie przesyłek](https://www.pocztex.pl/sledzenie-przesylek/) – suivi Pocztex
- [Poczta Polska : Regulamin usługi Pocztex](https://www.poczta-polska.pl/wp-content/uploads/2026/08/Regulamin-swiadczenia-uslugi-Pocztex-w-obrocie-krajowym-dla-klienta-detalicznego-obowiazuje-od-24.08.2026-r.pdf) – livraison, avis, points, lockers, délais, réclamations, garantie
- [Poczta Polska : Regulamin usług powszechnych](https://www.poczta-polska.pl/wp-content/uploads/2026/07/Regulamin-swiadczenia-uslug-powszechnych-tekst-jednolity.pdf) – awizo, garde, perte, réclamations internationales
- [Poczta Polska : Lista zdarzeń / Events list](https://www.poczta-polska.pl/wp-content/uploads/2025/02/PPSA_events_lists.pdf) – liste des événements et caractère évolutif
- [Poczta Polska : Pocztex PUNKT and AUTOMAT FAQ](https://odbiorwpunkcie.poczta-polska.pl/en/faq/) – commerces partenaires, lockers, pas de redirection, Contact Center
- [Pocztex : FAQ](https://www.pocztex.pl/en/faq-en/) – notifications, points de retrait, procès-verbal de dommage
- [Poczta Polska : eReklamacje, paczka pocztowa](https://ereklamacje.poczta-polska.pl/sub/reklamacje-krajowe/paczka-pocztowa) – qui réclame, délais, 30 jours
- [Poczta Polska : Contact](https://www.poczta-polska.pl/en/kontakt/) – ligne générale, horaires, formulaire
- [Poczta Polska : Kontakt](https://www.poczta-polska.pl/kontakt/) – version polonaise, formulaire de réclamation
- [Pocztex : Kontakt](https://www.pocztex.pl/kontakt/) – lignes Pocztex et horaires
- [Poczta Polska : Beware of fake "Pocztex" messages](https://www.poczta-polska.pl/en/news/uwaga-na-falszywe-wiadomosci-pocztex-grozna-kampania-phishingowa/) – faux SMS, 8080
- [Poczta Polska : Uwaga na fałszywe wiadomości „Pocztex”](https://www.poczta-polska.pl/news/uwaga-na-falszywe-wiadomosci-pocztex-grozna-kampania-phishingowa/) – version polonaise, Contact Center
- [Poczta Polska : Changes in fees for shipments from outside the EU](https://www.poczta-polska.pl/en/news/zmiany-w-oplatach-dla-przesylek-spoza-unii-europejskiej-od-1-lipca-2026-r/) – droit de 3 €, cadeaux jusqu'à 45 €, refus
- [Poczta Polska : Nowe przepisy Unii Europejskiej. Tak działa nowa opłata za przesyłki spoza UE](https://media.poczta-polska.pl/releases/nowe-przepisy-unii-europejskiej-tak-dziala-nowa-oplata-za-przesylki-spoza-ue) – taxe européenne de traitement de 2 € par article à charge du destinataire, reversée à KAS
- [Poczta Polska : Cennik opłat dodatkowych](https://www.poczta-polska.pl/wp-content/uploads/2025/04/cennik-oplat-dodatkowych.pdf) – frais de déclaration en douane
- [Poczta Polska : Poczta Polska SA Group](https://www.poczta-polska.pl/en/grupa-pp/poczta-polska/) – société d'État
- [Pocztex Mobile sur l'App Store](https://apps.apple.com/pl/app/pocztex-mobile/id1661761150) – application mobile
- [Universal Parcel Scraper : Poczta Polska](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/poczta-polska) – détection, numéros à 18/19/20 chiffres, champs lus et écartés
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – ce que lit Peek
:::
