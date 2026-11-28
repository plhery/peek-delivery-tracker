---
title: GEODIS Pologne suivi : numéro PL, statuts et livraison
description: Suivi GEODIS Pologne (ex-PEKAES) sur Strefa Klienta : numéro PL à 16 chiffres, sens des statuts, livraison de palettes, délais, réclamations et téléphone.
slug: geodis-pologne-suivi
published: 2026-11-18
updated: 2026-11-27
---

GEODIS Poland (l'ancien PEKAES, racheté en 2021 et aujourd'hui exploité par GEODIS Road Network sp. z o.o. à Błonie) est le réseau polonais de transport de colis et de palettes du groupe GEODIS, filiale de la SNCF. Pour suivre un envoi en Pologne ou sur ses liaisons routières européennes, la marche à suivre dépend de ce que l'expéditeur t'a transmis :

- **Un numéro composé de `PL` et de 16 chiffres, ou un lien vers Strefa Klienta :** il s'agit d'un envoi GEODIS Poland (colis ou palette). Colle le numéro ou le lien dans le champ ci-dessus, ou recherche-le sur l'outil de suivi de GEODIS Poland, [Strefa Klienta](https://strefaklienta.pekaes.geodis.pl/en/).
- **Un autre numéro GEODIS :** Peek lit également le suivi français de [GEODIS](carrier:geodis), dont les numéros commencent par `1G` (voir plus bas). Sur `geodis.com`, le moteur de recherche localise les envois aériens, maritimes et du dernier kilomètre, et renvoie le transport routier vers Strefa Klienta.
- **Pas encore de numéro :** c'est la boutique en ligne ou l'expéditeur qui le communique, le plus souvent dans l'e-mail de confirmation de commande ou d'avis d'expédition.

## Numéro de suivi GEODIS Pologne : format et où le trouver

Les solutions de GEODIS s'adressent uniquement aux professionnels : le numéro de suivi se trouve donc auprès de l'entreprise ou de la boutique en ligne qui t'expédie la marchandise. Si tu ne l'as pas reçu dans tes e-mails, demande-le au vendeur ou consulte [comment trouver son numéro de suivi](guide:find-tracking-number).

| Numéro (inventé) | Ce que c'est |
| --- | --- |
| `PL4829105736491827` | `PL` suivi de 16 chiffres : un numéro d'envoi GEODIS Poland, appelé `Shipment No.` sur Strefa Klienta. |
| `1G4829105736` | `1G` suivi de 10 lettres majuscules ou chiffres : un numéro de [GEODIS](carrier:geodis) en France, suivi sur l'espace destinataire français et non sur Strefa Klienta. |

Sur la version anglaise de Strefa Klienta, saisis le numéro dans le champ `Enter Shipment No.` puis clique sur `Search` (sur la version polonaise : `Wpisz numer przesyłki` puis `Szukaj`). Si le portail affiche `Shipment with such number does not exist`, il ne connaît pas ce numéro : contacte l'expéditeur ou la boutique en ligne, comme GEODIS le recommande lorsqu'un numéro ne fonctionne pas. Un message en polonais, `Proszę wprowadzić właściwy numer przesyłki.`, invite à saisir un numéro valide : vérifie qu'aucun chiffre ne manque ou n'est inversé.

## Ce que signifient les statuts de suivi GEODIS Pologne

Strefa Klienta existe en polonais et en anglais. Lorsqu'un envoi est trouvé, la page anglaise affiche d'abord un résumé (`Shipment No.`, `Status`, `Delivery Country`, `Delivery Location` avec code postal et localité), puis un lien `Details` ouvre l'historique des scans horodatés avec le lieu du terminal. Voici les libellés de la page anglaise dans l'ordre habituel d'un acheminement :

| Libellé GEODIS | Ce que cela veut dire pour toi |
| --- | --- |
| `Received from the sender` | GEODIS a pris en charge l'envoi chez l'expéditeur (`Collected (SMS)` indique un enlèvement confirmé par SMS ; `Failed collection (SMS)` un échec d'enlèvement). |
| `Inbound Scan From Driver to Terminal` | Un chauffeur a déposé l'envoi dans un terminal : le terminal de départ après l'enlèvement ou, après `Outbound for Delivery`, le terminal de livraison au retour de tournée. Sans `Consignee Absent` ni `Failed delivery (SMS)` juste avant, la tournée n'a pas pu tenter la remise et l'envoi attend la suivante. |
| `Outbound Scan from (Collection) Terminal to Line-haul` | L'envoi a quitté le terminal de départ par camion de liaison vers le terminal qui dessert ton adresse. |
| `Inbound Scan from Line-haul to (Delivery) Terminal` | L'envoi est arrivé au terminal chargé de la livraison finale. |
| `Awaiting Delivery` | Il attend dans ce terminal d'être chargé sur une tournée de distribution. |
| `Outbound for Delivery` | Il est à bord du camion de livraison. |
| `Consignee Absent` | Le chauffeur ne t'a pas trouvé à l'adresse lors de son passage. |
| `Failed delivery (SMS)` | Une tentative de livraison a échoué, confirmée par SMS. |
| `Awaiting designation of new delivery date` | GEODIS attend la fixation d'une nouvelle date de livraison. |
| `Awaiting Decision` | GEODIS attend les instructions du donneur d'ordre ou de l'expéditeur. |
| `Delivery Refusal` | Le destinataire a refusé l'envoi. |
| `Request for Return` | GEODIS a reçu une instruction de retour : l'envoi repart vers l'expéditeur, sans lui être encore remis. |
| `Delivered` | Livré (`Delivered (SMS)` indique la même chose, confirmée par SMS). |
| `Claim Received` | Une réclamation a été enregistrée : c'est une trace administrative qui ne déplace pas l'envoi. |

Quelques libellés signalent un blocage ou un incident : `Stopped at Hub` et `Stopped at Collection Terminal` (envoi retenu dans un hub de tri ou au terminal d'origine), `Not Dispatched` (il a manqué sa liaison ou sa tournée prévue mais reste dans le réseau) et `Failure` (incident ou panne). Une ligne commençant par `Pseudoscan` (`Pseudoscan – delete all previous 100, 400, 500 scans`) est une écriture comptable qui annule les scans de livraison, de retour et de réclamation antérieurs. Pour une vue générale des étapes d'acheminement, voir [les statuts de suivi](guide:tracking-statuses).

## Livraison GEODIS en Pologne : domicile, prise de rendez-vous et retrait en terminal

Les Conditions générales de prestation de services (*Ogólny Regulamin Świadczenia Usług*) de GEODIS Road Network, en vigueur depuis le 4 novembre 2024, lient GEODIS à son donneur d'ordre (*Zleceniodawca*, l'entreprise qui commande le transport, généralement le vendeur) et encadrent les livraisons nationales en **Pologne** ainsi que les liaisons routières internationales :

- **Absence lors du passage.** Sur les services nationaux en **Pologne** (`PAC DOM`, `LTL DOM`, `LTL DOM NST`, `PTL DOM`), le chauffeur laisse un avis de passage si le destinataire est absent ; après une deuxième tentative infructueuse imputable au destinataire, l'envoi est retourné à l'expéditeur aux frais du donneur d'ordre (de même en cas de refus ou d'adresse erronée). Sur les services routiers internationaux (`PAC INT`, `LTL INT`, `PTL INT`), en cas d'absence, le donneur d'ordre ou l'expéditeur dispose d'un délai maximal de 2 jours ouvrés à compter de l'échec pour donner ses instructions, faute de quoi GEODIS renvoie la marchandise à l'expéditeur aux frais du donneur d'ordre. Voir [livraison manquée](guide:missed-delivery) et [colis retourné à l'expéditeur](guide:returned-to-sender).
- **Palettes livrées à une adresse privée en Pologne (`DDOP`).** Sur la messagerie nationale en **Pologne** (`LTL DOM` et `LTL DOM NST`), la livraison à une adresse privée s'effectue uniquement les jours ouvrés, du lundi au vendredi entre `8:00` et `16:00`. Le donneur d'ordre doit fournir ton numéro de téléphone mobile et marquer l'envoi `DDOP` : GEODIS envoie des messages de statut et convient de la livraison à l'avance par téléphone, SMS ou e-mail, avec un appel téléphonique au plus tard le jour où la livraison est possible. Si GEODIS ne parvient pas à te joindre dans un délai de 2 jours ouvrés pour convenir du passage, il contacte le donneur d'ordre puis retourne l'envoi à l'expéditeur.
- **Déchargement et options domestiques en Pologne.** Dans le cadre du service `DDOP` en **Pologne**, le chauffeur décharge la palette au niveau du sol sur le lieu de livraison, ou à l'emplacement sûr et stabilisé le plus proche si la chaussée ne permet pas d'arrêter le camion en sécurité devant l'adresse. La livraison à l'intérieur du logement (*Usługa Wniesienia* : jusqu'à 300 kg brut, 30 kg maximum par colis et 30 colis maximum, à moins de 150 m sur sol stabilisé, jusqu'au deuxième niveau en l'absence d'ascenseur utilisable, ce qui peut rallonger le délai jusqu'à 3 jours ouvrés), l'acheminement par transpalette (*Usługa Wwiezienia*, jusqu'à 1 200 kg brut et 300 kg par palette dans un rayon de 150 m jusqu'au premier obstacle architectural), le jour fixe (*Fix Day*) et la livraison avant `10:00` (`D10`) ou `12:00` (`D12`), sur certains codes postaux polonais, sont des options nationales distinctes que le donneur d'ordre doit commander.
- **Retrait en terminal en Pologne.** Lorsque le donneur d'ordre demande un retrait en personne au terminal GEODIS par un destinataire particulier, l'envoi reste mis à disposition pendant 48 heures (comptées en jours ouvrés) à partir de cette instruction. Le retrait s'effectue du lundi au vendredi de `11:00` à `16:00` ; passé ce délai, l'envoi repart chez l'expéditeur.
- **Contre-remboursement en Pologne (*Usługa pobrania*).** Disponible uniquement sur les services nationaux en **Pologne** (`PAC DOM`, `LTL DOM`, `LTL DOM NST`), il se règle avant la remise de l'envoi jusqu'à `10 000 PLN` (ou l'équivalent dans une autre devise) par envoi, en espèces ou par carte bancaire, BLIK ou tout autre moyen sans espèces accepté par GEODIS.
- **Signature et vérification.** Tu confirmes la réception par une signature sur le terminal mobile du chauffeur ou sur les documents de transport. Le règlement de GEODIS précise que le destinataire ne peut pas vérifier le contenu de l'envoi avant d'en confirmer la réception : inspecte donc soigneusement l'emballage extérieur et inscris toute réserve sur les documents de transport au moment de signer.

## Envoi GEODIS Pologne en retard, endommagé ou non livré

**Suivi figé.** Si plus aucun scan n'apparaît pendant plusieurs jours, consulte [suivi qui ne bouge plus](guide:tracking-not-updating) et rapproche-toi du vendeur.

**Retard (droit polonais de la consommation).** En **Pologne**, l'autorité de protection des consommateurs (UOKiK, citant les articles 546 et 546¹ du Code civil polonais) rappelle que pour un achat de consommation soumis au droit polonais, le vendeur doit délivrer le bien sans tarder et au plus tard dans les 30 jours suivant la conclusion du contrat, sauf accord contraire. En cas de dépassement, l'acheteur peut fixer un délai supplémentaire au vendeur et, si le bien n'est toujours pas livré, résoudre le contrat.

**Colis endommagé ou manquant.** En cas de dommage extérieur visible ou de perte partielle à la livraison, rédige immédiatement un procès-verbal d'avarie (*protokół szkodowy*) en présence du chauffeur sur le modèle en vigueur chez GEODIS, signé lisiblement par vous deux. Les réserves portant sur des dommages ou manquants non visibles à la livraison doivent être adressées à GEODIS avant l'expiration d'un délai de 7 jours à compter de la réception ; quelle que soit la date des réserves, il appartient au réclamant de prouver que le dommage ou la perte partielle s'est produit avant la livraison. Conserve la marchandise à la disposition de GEODIS jusqu'à la clôture du dossier et prends des photographies datées et horodatées. Le formulaire de réclamation de GEODIS avertit que l'établissement d'un procès-verbal d'avarie sert de base à l'examen du dossier mais ne vaut pas dépôt de réclamation. Voir aussi [colis endommagé](guide:damaged-parcel).

**Qui dépose la réclamation.** Selon le règlement de GEODIS Road Network, les réclamations sont adressées par écrit au service des réclamations (*Dział Reklamacji*) au siège de Błonie par le donneur d'ordre ou par une personne munie d'un pouvoir écrit ou d'une cession de droits du donneur d'ordre, sans tarder et au plus tard dans les 14 jours suivant la réception (avarie ou manquant) ou dans les 21 jours suivant la date prévue de livraison (retard ou perte totale). Pour un achat en boutique soumis au droit polonais de la consommation, l'UOKiK (art. 548 § 3 du Code civil polonais) précise que le vendeur répond du risque de perte ou d'endommagement accidentel jusqu'à la remise à l'acheteur lorsque le mode de livraison figurait parmi ceux proposés par le vendeur, même si le transporteur a causé le dommage : en tant que consommateur, tu adresses donc ta réclamation au vendeur, qui se retourne contre le transporteur.

## Contacter GEODIS Pologne et son service client

- **Service client en Pologne (*Dział Obsługi Klienta*) :** `+48 22 460 26 26`, affiché sur la page polonaise de Strefa Klienta (aucune plage horaire n'y est précisée).
- **Réclamations par courrier :** `Dział Reklamacji, ul. Spedycyjna 1, 05-870 Błonie`, Pologne. Les formulaires de réclamation (`GRN Formularz zgloszenia reklamacji`) et de procès-verbal d'avarie (`GRN Protokol szkody`) sont téléchargeables sur Strefa Klienta.
- **Implantations en Pologne :** la [page de contact polonaise de GEODIS](https://geodis.com/pl-pl/skontaktuj-sie-z-nami) recense 42 implantations en Pologne avec leurs adresses, dont les agences GEODIS Road Network.
- **Formulaire en ligne sur `geodis.com` :** destiné exclusivement aux professionnels, il demande le nom de l'entreprise (`Firma *`) et une adresse e-mail professionnelle (`Adres e-mail służbowy *`).

## Suivre un envoi GEODIS Pologne avec Peek

Le format `PL` suivi de 16 chiffres n'appartient pas en propre à GEODIS (`PL` désigne la Pologne), si bien que Peek ne peut pas affirmer qu'il s'agit de GEODIS Poland sur la seule forme du numéro. Colle-le malgré tout : Peek suit plus de 4 500 transporteurs, interroge GEODIS Poland et affiche « GEODIS Poland a ce colis. » lorsqu'il le trouve. Si GEODIS ne l'a pas encore enregistré, le colis indique « Probablement GEODIS Poland » et « En attente du premier scan de GEODIS Poland » pendant que Peek continue de vérifier ; s'il n'apparaît toujours pas au bout d'une semaine, Peek suggère de vérifier le numéro auprès de l'expéditeur ou de choisir un autre transporteur. Pour sélectionner le transporteur toi-même, appuie sur « Changer » sur la ligne du transporteur et choisis « GEODIS Poland » sous « Correspond à ce numéro », ou recherche-le (`Pekaes` et `GEODIS Road Network` le trouvent aussi ; « GEODIS » seul correspond à GEODIS France). Sur un colis déjà ajouté, utilise « Changer de transporteur ». Un lien vers Strefa Klienta contenant `ShNumber=` est reconnu d'emblée comme GEODIS Poland.

Peek lit Strefa Klienta en anglais : le code `100` (`Delivered`) s'affiche traduit (« Livré »), tandis que les autres libellés de scan restent dans la formulation anglaise de GEODIS (y compris `Delivered (SMS)`), accompagnés de la localité du terminal, de l'heure de livraison et du pays de destination. Les scans effectués dans un terminal polonais sont interprétés à l'heure de Varsovie (`Europe/Warsaw`) ; un terminal à l'étranger conserve son heure locale. Peek convertit les horaires connus dans le fuseau de ton appareil ; sur `peektracker.com` et dans les applications iPhone et Android, l'historique se termine par « Heures dans ton fuseau horaire : » suivi du nom du fuseau. Lorsqu'une ligne `Pseudoscan` annule des scans précédents, Peek les retire également, et `Claim Received` laisse l'étape de l'envoi inchangée. Le portail de GEODIS ne fournit ni date de livraison estimée, ni poids, ni nom de service, ni nom du destinataire, ni preuve de livraison ; Peek écarte pour sa part le code postal et la ville du destinataire, le nombre d'unités, les codes internes de terminaux et les remarques pouvant mentionner le nom du signataire.

Tant que tu gardes le colis ouvert à l'écran, ou le jour de sa livraison avec les notifications activées, Peek vérifie jusqu'à toutes les 10 minutes, et jusqu'à toutes les 2 minutes dès 6 h (heure locale) le jour où GEODIS scanne l'envoi en cours de livraison (`Outbound for Delivery`), tant que le portail de GEODIS répond ; sinon, ainsi qu'après deux jours sans nouveauté (12 heures tant que l'envoi reste à « Étiquette créée » ou « Pris en charge »), la vérification est horaire. Via des services de suivi tiers, Peek vérifie toutes les heures, toutes les 3 heures après une journée calme, et jusqu'à toutes les 15 minutes le jour de la livraison tant que le colis est ouvert ou que les notifications sont activées. Tu peux recevoir des alertes pour « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Peek ne permet ni de fixer un rendez-vous, ni de demander un retrait en terminal, ni de déposer une réclamation : contacte GEODIS ou le vendeur.

## Questions fréquentes sur le suivi GEODIS Pologne

### GEODIS Poland et PEKAES sont-ils la même entreprise ?

Oui. GEODIS a racheté PEKAES en 2021, et ce réseau polonais est désormais exploité par GEODIS Road Network. Son outil de suivi utilise toujours l'adresse `strefaklienta.pekaes.geodis.pl`.

### GEODIS livre-t-il le samedi en Pologne et à quels horaires ?

Les Conditions générales de GEODIS Road Network ne prévoient pas de livraison le samedi : les palettes destinées à une adresse privée en Pologne (`DDOP`) sont livrées les jours ouvrés du lundi au vendredi de `8:00` à `16:00`, et le délai d'acheminement des colis nationaux (`PAC DOM`) exclut les samedis, dimanches et jours fériés.

### Le chauffeur GEODIS monte-t-il une palette ou un colis lourd à l'étage en Pologne ?

Uniquement pour un envoi national de messagerie en Pologne (`LTL DOM` ou `LTL DOM NST`) lorsque le donneur d'ordre a commandé l'option payante *Usługa Wniesienia* : jusqu'à 300 kg brut (30 kg max. par colis), à moins de 150 m du point de déchargement et jusqu'au deuxième niveau s'il n'y a pas d'ascenseur utilisable. Sans cette option, le déchargement se fait au niveau du sol.

### Quels sont les délais de livraison de GEODIS en Pologne et en Europe ?

Sur sa page dédiée aux palettes et colis, GEODIS annonce en Pologne une livraison standard en 24 à 48 heures (3 à 5 jours en Europe) et une livraison express le lendemain avant midi en Pologne (1 ou 2 jours en Europe). Dans ses Conditions générales, GEODIS livre en principe les colis nationaux `PAC DOM` jusqu'à 30 kg sous 48 heures hors week-ends et jours fériés, et les envois de messagerie `LTL` sous 2 jours ouvrés après l'enlèvement.

### Quel est le numéro de téléphone de GEODIS Pologne et comment réclamer ?

Le service client en Pologne (*Dział Obsługi Klienta*) affiché sur Strefa Klienta répond au `+48 22 460 26 26`. Pour un achat en boutique, adresse ta réclamation au vendeur (donneur d'ordre) ; en cas d'avarie visible à la livraison, fais immédiatement établir un procès-verbal d'avarie (*protokół szkodowy*) avec le chauffeur.

:::sources
- [GEODIS Poland : Strefa Klienta, shipment tracking (en anglais)](https://strefaklienta.pekaes.geodis.pl/en/) – champ `Enter Shipment No.`, bouton `Search`, formulaires de réclamation et d'avarie
- [GEODIS Poland : Strefa Klienta, Śledzenie przesyłek (en polonais)](https://strefaklienta.pekaes.geodis.pl/pl/) – numéro du service client `+48 22 460 26 26`, Conditions générales
- [GEODIS Road Network : Ogólny Regulamin Świadczenia Usług, à compter du 4 novembre 2024 (en polonais)](https://strefaklienta.pekaes.geodis.pl/pl/?file=files/pekaes/Dla%20Klienta%20-%20nowe%20www/GEODIS_SK/GEODIS%20RN_Ogolny%20Regulamin%20Swiadczenia%20Uslug_od%2004.11.2024.pdf) – tentatives nationales et internationales, option `DDOP`, déchargement, *Wniesienie*, retrait en terminal, contre-remboursement, signature, délais et réclamations
- [GEODIS Road Network : Formularz zgłoszenia reklamacji (en polonais)](https://strefaklienta.pekaes.geodis.pl/pl/?file=files/pekaes/Dla%20Klienta%20-%20nowe%20www/GEODIS_SK/GRN%20Formularz%20zgloszenia%20reklamacji.pdf) – adresse postale des réclamations à Błonie, valeur du procès-verbal d'avarie
- [GEODIS : Śledź swoją przesyłkę (en polonais)](https://geodis.com/pl-pl/sledz-swoja-przesylke) – où trouver le numéro, envois aériens/maritimes/dernier kilomètre sur `geodis.com`, renvoi du routier, numéro qui ne fonctionne pas
- [GEODIS : Twój obszar roboczy (en polonais)](https://geodis.com/pl-pl/twoj-obszar-roboczy) – accès à Strefa Klienta comme plateforme de suivi
- [GEODIS : Transport palet i paczek (en polonais)](https://geodis.com/pl-pl/uslugi-transportowe/transport-palet-i-paczek) – services réservés aux professionnels, délais standard et express en Pologne et en Europe
- [GEODIS : Skontaktuj się z nami (en polonais)](https://geodis.com/pl-pl/skontaktuj-sie-z-nami) – formulaire B2B et liste des 42 implantations en Pologne
- [GEODIS : Historia (en polonais)](https://geodis.com/pl-pl/historia) – rachat de PEKAES en 2021 et appartenance à la SNCF
- [UOKiK : Odpowiedzialność za przesyłkę (en polonais)](https://prawakonsumenta.uokik.gov.pl/prawa-ogolne/odpowiedzialnosc-sprzedawcy-za-przesylke/) – responsabilité du vendeur jusqu'à la remise en droit polonais de la consommation
- [UOKiK : Wydanie towaru (en polonais)](https://prawakonsumenta.uokik.gov.pl/prawa-ogolne/wydanie-towaru/) – délai maximal de 30 jours, délai supplémentaire et résolution du contrat en Pologne
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – format `PL` à 16 chiffres, liens Strefa Klienta, libellés de scan, numéros `1G` de GEODIS France et données lues par Peek
:::
