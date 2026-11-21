---
title: Suivi Morning Global : numéros BR…MG, statuts et contacts
description: Suis un colis Morning Global depuis Hong Kong ou la Chine continentale : format BR…MG, statuts, livraison locale, douane UE et contacts.
slug: suivi-morning-global
published: 2026-11-21
updated: 2026-11-21
---

Morning Global (MG), fondé en 1987, est la branche internationale de Morning Express Group, un groupe de messagerie basé à Hong Kong. Il expédie des colis à l'international pour des boutiques en ligne et des places de marché, dispose de bureaux à Hong Kong et à travers la Chine continentale, et confie généralement la remise finale à un partenaire dans ton pays. Suis le numéro communiqué par la boutique sur la [page Trace & Track de Morning Global](https://www.morninglobal.com/trace-track/), en anglais, ou colle-le dans le champ ci-dessus.

## Numéro de suivi Morning Global : à quoi il ressemble

Morning Global ne publie pas le format de ses numéros. Le catalogue de Peek connaît un modèle pour ce transporteur (exemples inventés) :

| Numéro (inventé) | Ce que c'est |
| --- | --- |
| `BR482910573MG` | `BR`, neuf chiffres et `MG` : un numéro Morning Global. Ce format correspondant aussi au gabarit des envois postaux, Peek vérifie d'abord auprès de Morning Global avant de le nommer (et si le neuvième chiffre valide la clé de contrôle postale, comme `BR482910576MG`, Peek interroge à la fois Morning Global et [Chronopost](carrier:chronopost)). |
| `BR48291057MG` | Huit chiffres seulement : ce n'est pas le format de Morning Global et Peek ne l'y rattache pas. Vérifie s'il manque un chiffre. |

Minuscules, espaces et tirets n'ont pas d'importance dans Peek. Le traceur de Morning Global accepte jusqu'à 20 numéros à la fois, séparés par des virgules.

**Où le trouver.** Dans l'e-mail d'expédition de la boutique ou sur la page de ta commande ([comment trouver son numéro de suivi](guide:find-tracking-number) et [suivre un colis venu de Chine](guide:tracking-from-china)).

**Autre format.** Une fois Morning Global choisi manuellement, Peek interroge son traceur pour tout code composé de 8 à 40 lettres et chiffres (au-delà ou en deçà, il indique qu'il « n’émet pas de numéros comme celui-ci »).

**Deux services.** Morning Global commercialise *MG International Express (MGIE)*, son service express porte-à-porte exploité en propre, et *MG International Postal (MGIP)*, plus économique, acheminé via des hubs postaux dans le monde. Ensemble, MGIE et MGIP couvrent plus de 220 destinations pour des colis jusqu'à 30 kg.

## Statuts de suivi Morning Global expliqués

Le traceur de Morning Global affiche les étapes sous `Shipment Journey`, chacune avec un statut, souvent une description complémentaire, une date, une heure et un lieu (souvent un code pays suivi du décalage UTC de l'horloge locale, comme `CA (UTC-4)`). Dans l'ordre habituel d'un envoi :

| Morning Global indique | Ce que cela veut dire pour toi | Peek affiche |
| --- | --- | --- |
| `Order Created` | La boutique a enregistré l'envoi ; Morning Global n'a pas encore le colis. | « Étiquette créée » |
| `Collection` | Morning Global a récupéré le colis auprès du marchand. | « Pris en charge » |
| `Arrive Hub` | Arrivé dans un centre de tri Morning Global. | « En transit » |
| `Depart Origin` | Parti du centre d'origine où débute son acheminement. | « En transit » |
| `Depart Hub` | Parti d'un centre de tri (intitulé issu de l'ancien traceur de Morning Global). | « En transit » |
| `Ready for Departure` | Prêt à quitter le pays d'expédition vers ton pays. | « En transit » |
| `Flight Departure to Destination` | Le vol à destination de ton pays a décollé. | « En transit » |
| `Arrive Destination` | Arrivé dans ton pays ; le dédouanement et la remise au partenaire local suivent encore. | « En transit » |
| `Customs Clearance Begins` | La douane de ton pays contrôle l'envoi. | « À la douane » |
| `Customs Cleared` | La douane a libéré le colis. | « En transit » |
| `Handed Over for Delivery` | Remis au partenaire chargé de la livraison dans ton pays ; il n'est pas encore en tournée. | « En transit » |
| `Out for Delivery` | En tournée de livraison aujourd'hui. | « En livraison » |
| `Delivered` | Livré. | « Livré » |

Quand la description d'une ligne précise le statut, Peek affiche le statut suivi de deux-points et de cette description, en retirant le nom du signataire et les numéros de téléphone. Les formulations absentes de cette liste sont classées selon les règles générales de Peek ou affichées sans changement d'étape.

**Introuvable.** Pour un numéro inconnu, le traceur de Morning Global répond `No shipment record was found for this tracking number.`

## Qui livre les colis Morning Global ?

À l'étape `Handed Over for Delivery`, Morning Global transmet le colis au partenaire chargé de le livrer dans ton pays. Son site ne publie pas la liste de ces partenaires et son traceur n'indique à Peek ni leur nom ni leur numéro de suivi propre : pour reprogrammer une livraison, demande ce numéro à la boutique ou au support de Morning Global.

**Implantations propres.** Morning Global cite le Royaume-Uni et les États-Unis parmi ses implantations exploitées en propre, avec des sièges régionaux à Londres (Royaume-Uni et Europe), Los Angeles (Amériques) et Canton en Chine continentale, ainsi qu'une succursale à Amsterdam ; le Canada figure parmi ses implantations clés aux côtés des États-Unis, et l'Australie dans son réseau partenaire. Morning Global ne publie pas de règles de représentation ou de points relais : ce sont celles du transporteur final ([livraison manquée](guide:missed-delivery), [colis non réclamé](guide:uncollected-parcel)).

**Douane en France, Belgique, Luxembourg et Suisse.** Dans l'**Union européenne**, les achats en ligne expédiés hors UE d'une valeur jusqu'à 150 € supportent depuis le 1er juillet 2026 un droit de douane temporaire de 3 € par article (marchandises partageant classement tarifaire, description et, si requis, origine ; au-delà de 150 €, tarif douanier normal) et, depuis le 1er novembre 2026, des frais de traitement de l'Union de 2 € par article quelle que soit la valeur, auxquels s'ajoute la TVA à l'importation en **France métropolitaine**, en **Belgique** et au **Luxembourg** sauf si la TVA a déjà été collectée lors du paiement via le guichet IOSS. En **Guadeloupe**, en **Martinique** et à **La Réunion**, la TVA à taux réduit et l'octroi de mer s'appliquent dans les mêmes conditions que la TVA ; en **Guyane** et à **Mayotte** (hors territoire TVA de l'UE), la TVA ne s'applique pas mais l'octroi de mer s'applique dans les mêmes conditions que la TVA ; Saint-Pierre-et-Miquelon, Saint-Barthélemy, la partie française de Saint-Martin, la Nouvelle-Calédonie, la Polynésie française et Wallis-et-Futuna sont hors territoire douanier et TVA de l'UE. Le 22 octobre 2026, Morning Global a informé ses marchands qu'à compter du 1er novembre 2026 des frais `EUR 2 Customs Handling Fee` sont facturés par ligne de déclaration douanière sur les envois B2C vers l'UE (en IOSS comme en DAP, même au-delà de 150 €), plus la TVA éventuelle sur ces frais selon le pays de destination, sans préciser si les boutiques les répercutent à l'acheteur. En **Suisse** (hors UE), l'OFDF perçoit 8,1 % de TVA à l'importation (2,6 % au taux réduit) et ne perçoit ni droits ni TVA lorsque leurs montants respectifs sont « inférieurs à 5 francs » ([colis retenu en douane](guide:customs)).

**Documents de dédouanement et avis de retard.** Lorsqu'une pièce d'identité ou une facture est requise en douane, Morning Global propose un formulaire [Upload Clearance Documents](https://www.morninglobal.com/upload-clearance-documents/) demandant le numéro de suivi : utilise-le uniquement sur `morninglobal.com` ([reconnaître les SMS de livraison frauduleux](guide:delivery-scam-texts)). Morning Global publie aussi ses alertes de perturbation sur sa page *News* (comme l'avis du 30 octobre 2026 annonçant environ 2 à 3 jours ouvrables de délai supplémentaire sur MGIP en Australie en raison d'un mouvement social).

## Colis Morning Global bloqué, perdu ou retourné

Les clients contractuels de Morning Global étant les boutiques expéditrices, adresse-toi d'abord au vendeur ou à la plateforme d'achat.

**Pas encore trouvé.** Si le transporteur n'est pas encore tranché mais que le code suit la forme `BR…MG` (sans clé postale valide), Peek affiche « Probablement Morning Global » et « En attente du premier scan de Morning Global » tout en poursuivant ses vérifications ; si le code passe aussi la clé de contrôle postale (`BR482910576MG`), Peek interroge Morning Global et [Chronopost](carrier:chronopost) et retient celui qui reconnaît l'envoi. Une fois classé sous Morning Global, il affiche « En attente de Morning Global ». Si rien n'apparaît au bout d'une semaine, vérifie le numéro auprès du marchand.

**Bloqué sur `Order Created` ou sans mise à jour.** `Order Created` signifie que le marchand n'a pas encore remis le colis. En cours de route, après quatre jours sans nouvelle ligne, Peek signale « Aucune mise à jour depuis quatre jours » ; après `Handed Over for Delivery`, demande le numéro du transporteur local ([pourquoi le suivi ne bouge plus](guide:tracking-not-updating)).

**Perdu, endommagé, refusé ou livré sans réception.** Les conditions de Morning Global donnent au marchand 30 jours à compter de la prise en charge pour déclarer un sinistre et 90 jours pour transmettre les justificatifs, avec une indemnisation plafonnée à la valeur déclarée et au maximum à 50 $ US par colis en cas de faute de Morning Global ; un échec dû à une adresse erronée ou incomplète, à l'absence de réponse ou à un refus compte comme une commande exécutée vis-à-vis du marchand ([colis perdu](guide:lost-parcel), [colis endommagé](guide:damaged-parcel), [retourné à l'expéditeur](guide:returned-to-sender)). Si l'envoi est indiqué `Delivered` mais introuvable, consulte `Signed By` sur le traceur de Morning Global (que Peek n'affiche pas), vérifie auprès des voisins et préviens le vendeur ([indiqué livré mais non reçu](guide:delivered-not-received)).

## Service client et contacts Morning Global

Pour les demandes liées à un envoi sur la [page Contact de Morning Global](https://www.morninglobal.com/contact/) (indique ton numéro de suivi, sans horaires publiés) :

- **Royaume-Uni et Europe** (siège régional à Londres) : `cs.mgeu@morninglobal.com`, `+44 20 7019 9035`.
- **États-Unis / Amériques** (siège régional à Los Angeles) : `cs.mgus@morninglobal.com`, `+1 888 981 8688`.
- **Hong Kong et Macao** (siège mondial à Hong Kong) : `cs.mghk@morninglobal.com`, `+852 3669 6360`.
- **Chine continentale** (siège régional à Canton) : `cs.mgchina@morninglobal.com`, `+86 4008 268 216`.
- **Autres équipes régionales** : Italie (`cs.mgit@morninglobal.com`), Inde, Philippines et Singapour disposent chacune de leur adresse sur la même page.
- **Équipe mondiale** : `cs.mgworldwide@morninglobal.com`, également sur WhatsApp au `+1 (479) 777 3307` (ou par QR code), ainsi que le formulaire en ligne (nom, e-mail, entreprise, message).

**Attention aux SMS frauduleux.** Le domaine officiel de Morning Global est `www.morninglobal.com` (`morninglobal.com`). Consulte [reconnaître les SMS de livraison frauduleux](guide:delivery-scam-texts) avant de suivre un lien reçu par message.

## Suivre un colis Morning Global avec Peek

[Peek](/) ne désigne pas Morning Global sur la seule forme d'un numéro `BR…MG`, mais suit plus de 3 500 transporteurs : colle le numéro, Peek affiche « Vérification des services de suivi… » puis « Morning Global a ce colis. » dès que le traceur de Morning Global le connaît. Un lien `morninglobal.com` contenant `tracking_number=` ou `ydh=` classe immédiatement l'envoi sous Morning Global (« Transporteur détecté »). Sinon, touche « Changer » et choisis Morning Global sous « Correspond à ce numéro » ou « Tous les transporteurs » (ou « Changer de transporteur » sur un colis déjà enregistré).

Peek lit directement le traceur de Morning Global sans code postal : il affiche le statut, chaque étape avec son lieu, le pays de destination et l'heure de livraison, lit l'heure selon le décalage UTC indiqué à côté du pays (et sans décalage, selon l'horloge du lieu, d'une étape voisine, de ton pays ou de Hong Kong) et convertit le tout dans le fuseau de ton appareil (« Heures dans ton fuseau horaire : … »). Il écarte l'origine, `Estimated Delivery`, le poids, le nombre de pièces, `Reference` et `Signed By` (aucune date de livraison prévue n'est affichée), et suit l'envoi sur Morning Global jusqu'au bout sans interroger la poste de ton pays.

Peek vérifie au maximum une fois par heure (y compris en « En livraison ») et patiente 2 heures après un échec de vérification (de même pour une actualisation manuelle). Tu peux activer « Chaque scan », « Étapes importantes » (`Customs Clearance Begins`, `Out for Delivery` et `Delivered`) ou « Livraison seulement ». Peek ne peut contacter ni Morning Global ni la boutique pour toi.

## Questions fréquentes sur le suivi Morning Global

### Morning Global est-il fiable ?

Oui. Fondé en 1987, Morning Global est la division internationale du groupe de messagerie hongkongais Morning Express Group, avec son siège mondial à Hong Kong et ses propres opérations dans 14 pays, au service de marques, détaillants et places de marché.

### Comment suivre un colis Morning Global ?

Colle le numéro fourni par la boutique dans le champ en haut de cette page, ou saisis-le sur la page `Trace & Track` de `morninglobal.com` (jusqu'à 20 numéros séparés par des virgules).

### Que signifie `Handed Over for Delivery` chez Morning Global ?

Morning Global a remis le colis, après la douane, au partenaire qui assure la livraison dans ton pays. Il n'est pas encore dans la camionnette de livraison : l'étape `Out for Delivery` vient ensuite.

### Qui livre les colis Morning Global ?

Un transporteur partenaire dans ton pays, que le site et le traceur de Morning Global ne nomment pas. Demande son numéro de suivi au vendeur ou au support de Morning Global.

### Comment contacter Morning Global ?

Écris avec ton numéro de suivi à `cs.mgeu@morninglobal.com` (Royaume-Uni / Europe), `cs.mgus@morninglobal.com` (États-Unis) ou `cs.mgworldwide@morninglobal.com` (reste du monde, aussi sur WhatsApp au `+1 (479) 777 3307`). Pour ta commande, contacte d'abord le marchand.

:::sources
- [Morning Global : Trace & Track](https://www.morninglobal.com/trace-track/) – traceur jusqu'à 20 numéros, Shipment Journey, Estimated Delivery, Reference, Signed By et message d'envoi introuvable
- [Morning Global : Home](https://www.morninglobal.com/) – fondation en 1987, présence en propre dans 14 pays et actualités
- [Morning Global : About Us](https://www.morninglobal.com/about-us/) – division internationale de Morning Express Group à Hong Kong
- [Morning Global : Services](https://www.morninglobal.com/services/) – offres MGIE et MGIP, plus de 220 destinations et limite de 30 kg
- [Morning Global : Global Network](https://www.morninglobal.com/global-network/) – implantations propres (Royaume-Uni, États-Unis), Canada et réseau partenaire (Australie)
- [Morning Global : Contact](https://www.morninglobal.com/contact/) – e-mails et téléphones régionaux, WhatsApp mondial, formulaire, sièges de Hong Kong, Canton (Chine continentale), Londres et Los Angeles
- [Morning Global : Upload Clearance Documents](https://www.morninglobal.com/upload-clearance-documents/) – formulaire de dépôt de justificatifs douaniers
- [Morning Global : Terms and Conditions (Privacy Policy)](https://www.morninglobal.com/privacy-policy/) – échecs de livraison, délais de réclamation de 30 / 90 jours et plafond de 50 $ US
- [Morning Global : EUR 2 Customs Handling Fee for EU-bound B2C Shipments](https://www.morninglobal.com/morning-global-service-update-introduction-of-eur-2-customs-handling-fee-for-eu-bound-b2c-shipments-from-1-november-2026/) – frais de 2 € par ligne déclarée dès le 1er novembre 2026 et TVA éventuelle sur les frais
- [Morning Global : Extended Transit Times in Australia](https://www.morninglobal.com/morning-global-service-impact-notice-extended-transit-times-in-australia-due-to-industrial-action/) – retard MGIP d'environ 2 à 3 jours ouvrables en Australie
- [Morning Global : News](https://www.morninglobal.com/news/) – avis de service
- [Commission européenne : EU Customs Reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – droit de 3 € jusqu'à 150 € depuis juillet 2026 et frais de traitement de 2 € depuis novembre 2026
- [Commission européenne : Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – définition d'un article, frais de 2 € quelle que soit la valeur et exonération de TVA à l'importation via IOSS
- [Commission européenne : Champ d'application territorial de la TVA et des douanes](https://taxation-customs.ec.europa.eu/taxation/vat/vat-directive/how-does-vat-work/territorial-scope_en) – statut douanier et TVA des départements et collectivités d'outre-mer français
- [Douane française : Vous achetez sur internet](https://www.douane.gouv.fr/demarche/vous-achetez-sur-internet) – TVA et octroi de mer dans les mêmes conditions que la TVA outre-mer
- [OFDF : Réception de lettres et de paquets](https://www.bazg.admin.ch/fr/reception-de-lettres-et-paquets) – TVA suisse de 8,1 % / 2,6 % et franchise lorsque les montants sont inférieurs à 5 francs
- [Universal Parcel Scraper : Morning Global](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/morning-global) – format BR…MG, liens, statuts, horloges, cadence horaire et champs écartés
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – vérification S10 avec Chronopost et absence de transfert vers la poste locale
:::
