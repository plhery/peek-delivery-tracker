---
title: Suivi India Post et Speed Post : numéros, statuts et douane
description: Suivez un envoi India Post ou Speed Post : format du numéro en IN, sens des statuts, délais, relais postal en France, Belgique, Suisse ou Canada et douane.
slug: suivi-india-post
published: 2026-11-18
updated: 2026-11-18
---

India Post est le Department of Posts, l'opérateur postal public de l'**Inde**, qui indique compter 164 999 bureaux de poste — le plus vaste réseau postal au monde selon sa présentation officielle. En Inde, il achemine Speed Post, le courrier recommandé et India Post Parcel ; vers l'étranger, l'EMS (International Speed Post), les petits paquets suivis (International Tracked Packet) et les colis aériens. Vous pouvez suivre un envoi sur [indiapost.gov.in](https://www.indiapost.gov.in/), qui demande un CAPTCHA, ou coller son numéro dans le champ ci-dessus. India Post l'appelle *article barcode number* (numéro de code-barres de l'envoi) : pour une commande en ligne, demandez-le au vendeur.

## Numéro de suivi India Post : format et exemples

Un numéro India Post compte 13 caractères au [format postal international](guide:tracking-number-formats) : deux lettres, neuf chiffres et le code pays `IN`. Le dernier des neuf chiffres est un chiffre de contrôle. Exemples avec des chiffres inventés :

| Exemple inventé | Ce qu'il désigne |
| --- | --- |
| `EE482910576IN` | Envoi numéroté par India Post, qu'il circule en Inde ou parte à l'étranger. Les deux premières lettres varient selon le service ; la fin en `IN` identifie l'Inde. |
| `EE482910576FR` | Se termine par un autre code pays, ici `FR` : une autre poste nationale ([La Poste](carrier:la-poste)) l'a émis. Un colis expédié vers l'Inde depuis l'étranger garde le numéro de la poste de départ. |

Le suivi d'India Post couvre Speed Post, les envois recommandés et avec valeur déclarée, Business Parcel et Express Parcel, les mandats électroniques (*electronic money orders*) et l'EMS international. Introuvable ? Consultez [comment trouver son numéro de suivi](guide:find-tracking-number).

## Statuts de suivi India Post expliqués

La FAQ d'India Post résume le parcours d'un envoi par les étapes *booked*, *received*, *bagged*, *dispatched*, *out for delivery* et *delivered*. Voici les lignes reconnues par Peek dans l'ordre habituel du trajet :

| Statut India Post | Ce que cela signifie pour votre envoi |
| --- | --- |
| `Item Booked` | L'expéditeur a déposé l'envoi au guichet ou l'a enregistré en ligne et India Post l'a pris en charge. |
| `Bag Received for Forwarding` | La dépêche contenant l'envoi est arrivée dans un bureau pour réexpédition. |
| `Assigned to load plan` | L'envoi est programmé sur le prochain départ de transport. |
| `Transport Leg Completed` | Une étape du transport est terminée. |
| `Transfer to OOE (Otb)` | Acheminement vers le bureau d'échange indien qui expédie le courrier à l'étranger. |
| `Aircraft Departure` | L'avion qui transporte la dépêche a décollé. India Post affiche parfois `UPLIFT`. |
| `Bag unloaded at destination Airport` | La dépêche a été déchargée à l'aéroport du pays de destination. |
| `Send item to domestic location (Inb)` | Le bureau d'échange du pays d'arrivée a transmis l'envoi à son réseau postal intérieur. |
| `Item Delivered` | Livré. |

La mention `(Otb)` signale un scan du bureau d'exportation indien et `(Inb)` un scan transmis par la poste du pays de destination. Dans les données lues par Peek, India Post renvoie désormais souvent des codes bruts tels que `BAG_DISPATCH` : Peek développe ceux qu'il connaît — par exemple `Bag Dispatched` ou `Item Presented To Customs` — et convertit les autres codes en mots lisibles. Pour le détail des étapes génériques, voyez [que signifient les statuts de suivi](guide:tracking-statuses).

## Recevoir un envoi India Post en Inde

Les règles ci-dessous s'appliquent aux envois distribués en **Inde** :

**Délais indicatifs.** Pour Speed Post en Inde, les normes publiées par India Post du dépôt à la livraison sont de 1 à 2 jours en local, 1 à 3 jours entre grandes métropoles, 1 à 4 jours entre capitales d'État ou à l'intérieur d'un même État, et 4 à 5 jours pour le reste du pays, avec un jour de plus lorsqu'un bureau auxiliaire (*branch office*) assure la tournée. L'offre 24 Speed Post livre le lendemain dans six métropoles indiennes (New Delhi, Mumbai, Bengaluru, Chennai, Kolkata et Hyderabad), sept jours sur sept ; en cas de retard, l'expéditeur peut demander le remboursement de l'affranchissement en ligne dans les 7 jours.

**SMS et code OTP.** Pour un colis Speed Post en Inde, le numéro de mobile enregistré reçoit un SMS à l'arrivée au bureau distributeur puis un second à la livraison. Certains envois Speed Post ne sont remis que contre un code à usage unique (OTP) : il est inclus d'office dans 24 Speed Post et proposé en option payante sur les autres envois Speed Post.

**Absence à la livraison.** Le suivi d'India Post indique l'émission d'un avis de mise en instance (*Delivery Intimation Notice*) précisant que l'envoi est disponible pour remise. Consultez aussi [livraison manquée](guide:missed-delivery) et [colis non réclamé](guide:uncollected-parcel).

**Changement d'adresse.** En Inde, un expéditeur ayant affranchi son envoi en ligne depuis un compte India Post peut demander le retrait ou la réexpédition tant que l'objet n'est ni livré ni confié au facteur pour sa tournée.

## Colis India Post vers l'étranger : qui livre et quelle douane s'applique

Vers l'international, India Post expédie l'EMS (International Speed Post, jusqu'à 35 kg), l'International Tracked Packet destiné aux vendeurs en ligne (jusqu'à 5 kg) et l'International Air Parcel (jusqu'à 20 kg), tous suivis. Une fois l'envoi arrivé à destination, les scans marqués `(Inb)` proviennent de la poste locale : vous pouvez continuer à le suivre avec le même numéro en `IN` auprès de [La Poste](carrier:la-poste) en **France**, [bpost](carrier:bpost) en **Belgique**, [La Poste suisse](carrier:swiss-post) en **Suisse**, [Postes Canada](carrier:canada-post) au **Canada**, [USPS](carrier:usps) aux **États-Unis** ou [Royal Mail](carrier:royal-mail) (Parcelforce pour l'EMS) au **Royaume-Uni**.

**Douane dans l'Union européenne et en France.** L'Inde étant hors de l'Union européenne, tout envoi passe en douane à l'arrivée. Dans l'**Union européenne**, depuis le 1er juillet 2026, les biens achetés en ligne hors UE dans des envois d'une valeur intrinsèque maximale de 150 € supportent un droit de douane européen de 3 € par article ; depuis le 1er novembre 2026 s'ajoutent des frais de traitement de l'Union de 2 € par article, quelle que soit la valeur. Un article regroupe les marchandises d'un même colis relevant d'un même classement tarifaire, d'une même désignation et, lorsqu'elle est requise, d'une même origine ; au-delà de 150 € s'appliquent les droits du tarif douanier. Selon la douane française, en **France métropolitaine**, la réception de marchandises hors UE est soumise à la TVA « dès le premier euro ». Dans les départements d'outre-mer français (**Guadeloupe**, **Martinique** et **La Réunion**), la TVA s'applique sur la valeur totale de l'envoi si celle-ci « dépasse 22 euros », tandis que la TVA « n'est provisoirement pas applicable dans les départements de la Guyane et de Mayotte » (article 294-1 du CGI) ; l'octroi de mer et l'octroi de mer régional s'appliquent dans ces DOM dans les mêmes conditions que la TVA, ainsi que les droits de douane au-delà de 150 €. India Post propose l'option Delivery Duty Paid (DDP) aux exportateurs : demandez au vendeur s'il l'a souscrite.

**Douane en Suisse.** En **Suisse**, selon l'Office fédéral de la douane et de la sécurité des frontières (OFDF), la TVA sur les importations est de 8,1 % (ou 2,6 % au taux réduit, notamment pour les livres et denrées alimentaires) ; pour des raisons d'économie administrative, « les droits de douane inférieurs à 5 francs ne sont pas perçus » et « les montants d'impôt inférieurs à 5 francs ne sont pas perçus » (soit une base de calcul de 62 francs à 8,1 % ou de 193 francs à 2,6 %), hors frais éventuels de dédouanement du transporteur. Ailleurs, notamment au **Canada**, ce sont les règles douanières locales qui s'appliquent : voyez [colis bloqué en douane](guide:customs).

**Interruptions de service.** Lorsqu'India Post suspend ou rouvre le trafic vers un pays — comme la suspension vers le Canada en octobre 2025 ou la reprise vers l'Irak, le Koweït et le Liban en août 2026 — elle publie un avis dans sa rubrique [News and Updates](https://www.indiapost.gov.in/media/newsandupdates).

**Envois à destination de l'Inde.** Un colis expédié depuis l'étranger vers l'Inde porte le numéro de la poste d'origine. Suivez-le d'abord sur le site de celle-ci : la FAQ d'India Post prévient que les informations transmises par les administrations postales étrangères peuvent arriver avec retard.

## Suivi India Post bloqué, colis perdu ou retourné

**Plus de nouveau scan.** India Post précise qu'une absence de scan ne signifie pas que l'objet n'a pas été expédié : l'affichage en ligne peut prendre du retard, surtout depuis les zones rurales en Inde ou lorsqu'il dépend d'une poste étrangère. Dès qu'un envoi international affiche `Aircraft Departure`, interrogez le suivi de la poste de votre pays avec le même numéro. Plus d'explications dans [pourquoi le suivi ne bouge plus](guide:tracking-not-updating).

**Retard, perte ou avarie (règles d'India Post).** Chez India Post, une réclamation se dépose une fois le délai d'acheminement dépassé et dans les 60 jours suivant l'opération, en rappelant le numéro d'envoi ; le délai standard de traitement annoncé par India Post est de 90 jours. En service intérieur indien, Speed Post rembourse ses frais d'envoi en cas de retard et verse le double des frais ou 1 000 ₹ (le montant le plus faible des deux) en cas de perte, spoliation ou avarie. À l'international, les marchandises en EMS sont couvertes jusqu'à leur valeur ou 130 DTS (le montant le plus faible), plus l'affranchissement, et un retard de plus de 5 jours au-delà de la norme publiée ouvre droit à 5 % du port ; un International Tracked Packet est couvert jusqu'à sa valeur ou 1 000 ₹, selon le montant le plus bas. Pour un achat en ligne, prévenez aussi la boutique : voyez [colis perdu](guide:lost-parcel) et [colis endommagé](guide:damaged-parcel).

**Marqué livré mais absent.** En Inde, déposez une réclamation par les canaux ci-dessous ou sur le registre des réclamations tenu dans chaque bureau de poste : consultez aussi [colis indiqué livré mais non reçu](guide:delivered-not-received).

**Retour à l'expéditeur.** Lors d'un affranchissement en ligne chez India Post, l'expéditeur peut indiquer une adresse de retour différente de la sienne. Peek affiche un retour sous l'étape « Retourné à l’expéditeur » : voyez [colis retourné à l'expéditeur](guide:returned-to-sender).

## Service client India Post et réclamations

Canaux officiels d'India Post :

- **Assistance téléphonique (numéro gratuit en Inde) :** `1800 266 6868`. Des conseillers répondent de 8 h à 20 h (heure de l'Inde, IST) ; le serveur vocal interactif fonctionne 24 h/24 et 7 j/7, dimanches compris, sauf jours fériés officiels (*gazetted holidays*) en Inde.
- **Réclamations :** en ligne sur la page [File a Complaint](https://www.indiapost.gov.in/grievance-redressal/file-a-complaint) d'India Post (où vous pouvez suivre l'avancement du dossier), dans un bureau de poste en Inde (auprès du *postmaster* ou du *Superintendent of Post Offices*) ou par téléphone. Aucun compte n'est requis.
- **Recours en Inde :** si India Post ne règle pas le dossier dans un délai raisonnable, le portail gouvernemental indien Directorate of Public Grievances (`dpg.gov.in`).

Sur sa page d'accueil, India Post met en garde contre les faux liens usurpant son identité : utilisez uniquement `www.indiapost.gov.in` et ne communiquez jamais de code OTP ni de coordonnées bancaires, de carte ou UPI via un lien non autorisé (« do not share OTP, banking, card, UPI or other personal/financial information through unauthorized links »). Voyez aussi [SMS d'arnaque à la livraison](guide:delivery-scam-texts).

## Suivre un colis India Post avec Peek

[Peek](/) reconnaît deux lettres, neuf chiffres et `IN` comme un numéro India Post lorsque le chiffre de contrôle est exact, et affiche « Transporteur détecté » ; le bouton « Changer » permet d'en choisir un autre. Si le chiffre de contrôle ne tombe pas juste (par exemple `EE482910577IN`), Peek signale « Le chiffre de contrôle de ce numéro ne correspond pas. » : vérifiez le numéro reçu ou choisissez « Suivre tel quel ». Enregistré sous India Post, un envoi affiche « En attente de India Post » sur peektracker.com jusqu'au premier scan.

Peek n'interroge pas directement le portail d'India Post : il lit les scans d'India Post via MySpeedPost, un site de suivi externe au domaine d'India Post qui les récupère auprès d'India Post (ou via track91).

Peek affiche chaque ligne dans les termes d'India Post, avec le bureau postal, et place le bureau sur la carte lorsque sa localité est vérifiée. Un décollage s'affiche comme `Aircraft Departure`. Peek classe chaque scan dans ses propres étapes : un échec de remise passe en « Tentative de livraison », et un refus, une avarie, une perte ou une adresse insuffisante en « Problème signalé ». Via track91, les libellés sont plus courts : `Dispatched` (expédié à l'étranger par le bureau d'échange) et `Aircraft arrival in Transit country` (escale aérienne) apparaissent en « En transit », et `Delivered` en « Livré ». Peek écarte le nom du destinataire, son adresse et la signature, et n'affiche pas de date de livraison estimée. Les scans indiens sont lus à l'heure de l'Inde (UTC+5:30), puis convertis dans le fuseau horaire de votre appareil ; sur peektracker.com et dans l'application iPhone, une mention sous l'historique rappelle ce fuseau (« Heures dans ton fuseau horaire : … »).

Lorsqu'India Post indique le pays de destination — ou n'en indique pas et que vous consultez le colis depuis l'étranger — Peek interroge aussi la poste qu'il connaît sur place avec le même numéro en `IN`, comme [La Poste](carrier:la-poste) en France, [bpost](carrier:bpost) en Belgique, [La Poste suisse](carrier:swiss-post) en Suisse, [Postes Canada](carrier:canada-post) au Canada, [DHL](carrier:dhl) en Allemagne, USPS, Royal Mail (ou Parcelforce pour l'EMS) et Australia Post. Dès que cette poste renvoie des scans récents, Peek bascule vers elle tout en conservant l'historique d'India Post. Peek couvre ainsi la plupart des pays d'Europe et plusieurs autres pays, dont NZ Post en Nouvelle-Zélande et Qatar Post au Qatar, mais n'a pas d'adaptateur postal direct pour les Émirats arabes unis, le Koweït, Oman, Singapour, la Malaisie ou le Sri Lanka. Inversement, Peek n'interroge pas India Post pour un numéro émis par une autre poste : choisir India Post sur un tel numéro affiche « n’émet pas de numéros comme celui-ci ».

Tant que vous gardez la page du colis ouverte ou, le jour de sa livraison, avec les notifications activées, Peek vérifie les mises à jour jusqu'à toutes les 10 minutes — et jusqu'à toutes les 2 minutes à partir de 6 h locales le jour où le colis passe en « En livraison » lorsque MySpeedPost ou le suivi direct de votre poste répond (après le relais, Peek suit le rythme de la poste de destination ; via d'autres services de suivi seuls, toutes les heures, toutes les 3 heures après un jour sans mouvement et jusqu'à toutes les 15 minutes le jour de la livraison si le colis est ouvert ou que les notifications sont actives). En dehors de ces cas, ainsi qu'après deux jours sans nouveauté (ou 12 heures sur « Étiquette créée » ou « Pris en charge »), Peek vérifie toutes les heures. Alertes au choix : « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Si le colis reste immobile chez India Post, « Demande de l’aide à India Post » ouvre sa page Contact Us. Peek ne peut contacter ni India Post, ni votre poste, ni le vendeur à votre place.

## Questions fréquentes sur le suivi India Post

### Comment suivre un envoi India Post Speed Post ?

Saisissez le numéro de 13 caractères se terminant par `IN` sur indiapost.gov.in ou collez-le dans Peek, qui suit le colis jusqu'à la livraison, y compris après sa prise en charge par la poste de votre pays.

### Pourquoi mon suivi India Post ne bouge-t-il plus ?

La mise en ligne des scans peut prendre du temps, notamment depuis les bureaux ruraux en Inde ou les postes étrangères. Si un envoi international affiche en dernier `Aircraft Departure`, il a quitté l'Inde : vérifiez le même numéro sur le site de la poste de votre pays.

### Combien de temps met Speed Post ?

En Inde, la norme d'India Post est de 1 à 3 jours entre grandes métropoles et de 4 à 5 jours vers le reste du pays, plus un jour lorsqu'un bureau auxiliaire livre ; 24 Speed Post livre le lendemain dans six métropoles indiennes. Vers l'étranger, le délai dépend du service choisi et du dédouanement à l'arrivée.

### Quel est le numéro du service client India Post ?

Le `1800 266 6868` (gratuit depuis l'Inde) : des agents répondent de 8 h à 20 h heure de l'Inde, et le serveur vocal fonctionne 24 h/24 sauf les jours fériés officiels indiens. Depuis l'étranger, vous pouvez aussi déposer et suivre une réclamation en ligne sur indiapost.gov.in sans créer de compte.

:::sources
- [India Post: Home](https://www.indiapost.gov.in/) – suivi, numéro d'assistance, alerte contre les liens frauduleux
- [India Post: Contact Us](https://www.indiapost.gov.in/help&Support/contactus) – numéro gratuit, horaires, réclamations au bureau de poste
- [India Post: Help and FAQs](https://www.indiapost.gov.in/grievance-redressal/help-faq) – numéro de code-barres, envois suivis, statuts, retards, réclamations, retrait et réexpédition, adresse de retour
- [India Post: About Us](https://www.indiapost.gov.in/ministry/aboutus) – Department of Posts, 164 999 bureaux de poste
- [India Post: Speed Post](https://www.indiapost.gov.in/mail&Parcel/mailproducts/premiumservices/speedpost) – OTP, délais normés, SMS Speed Post Parcel, indemnisation
- [India Post: 24/48 Speed Post FAQs](https://www.indiapost.gov.in/speedpost/faqs) – livraison le lendemain dans six métropoles, OTP, remboursement
- [India Post: India Post Parcel](https://www.indiapost.gov.in/mail&Parcel/mailproducts/domesticservices/indiapostparcel) – India Post Parcel
- [India Post: International Mail](https://www.indiapost.gov.in/mail&Parcel/mailproducts/mailservices/international) – services internationaux, option DDP pour les exportateurs
- [India Post: International services](https://www.indiapost.gov.in/mail&Parcel/mailproducts/internationalservices) – EMS, International Tracked Packet, colis aériens, plafonds d'indemnisation
- [India Post: News and Updates](https://www.indiapost.gov.in/media/newsandupdates) – avis de suspension et de reprise par pays
- [India Post: File a Complaint](https://www.indiapost.gov.in/grievance-redressal/file-a-complaint) – dépôt et suivi de réclamation, recours DPG
- [India Post: Guidelines on Complaint Registration](https://www.indiapost.gov.in/grievance-redressal/guidelines) – délais et canaux de réclamation
- [Commission européenne : Réforme douanière de l'UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – droit de douane de 3 € et frais de traitement de 2 €
- [Commission européenne : Orientations sur le droit de douane de 3 € et les frais de traitement (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – calcul par article et définition d'un article
- [Douane française : Vous achetez sur internet](https://www.douane.gouv.fr/demarche/vous-achetez-sur-internet) – TVA dès le 1er euro en France métropolitaine, seuil de 22 €, octroi de mer et cas de la Guyane et de Mayotte dans les DOM
- [OFDF : Réception de lettres et de paquets](https://www.bazg.admin.ch/fr/reception-de-lettres-et-paquets) – TVA suisse 8,1 % / 2,6 % et non-perception des montants inférieurs à 5 francs
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – libellés de scans (dont track91), fuseaux horaires et passage de relais aux postes de destination
:::
