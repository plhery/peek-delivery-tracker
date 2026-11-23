---
title: Suivi Cyprus Post : numéros, statuts et douane
description: Suis un envoi Cyprus Post : code à 13 caractères, signification des statuts UPU en anglais, taxes UE sur les achats hors UE et réclamations.
slug: suivi-cyprus-post
published: 2026-11-22
updated: 2026-11-22
---

Cyprus Post (Κυπριακά Ταχυδρομεία), le Department of Postal Services, est la poste nationale de la République de Chypre. Suis un envoi sur le site de Cyprus Post, cypruspost.post, ou colle le numéro dans le champ ci-dessus. Aucun numéro ? Consulte [comment trouver son numéro de suivi](guide:find-tracking-number).

## Numéro de suivi Cyprus Post

Le suivi de Cyprus Post accepte les numéros à 13 caractères de la norme S10 de l'Union postale universelle, attribués par les postes aux envois recommandés, EMS, colis et lettres suivies : [deux lettres, neuf chiffres et deux lettres](guide:tracking-number-formats).

:::anatomy RR 482917367 CY
- RR | Indicateur de service : la première lettre indique la catégorie d'envoi
- 482917367 | Numéro de série à huit chiffres, puis un chiffre de contrôle
- CY | Code pays : CY pour un numéro émis sous l'autorité de Chypre
:::

| Première lettre (exemples inventés) | À quoi il correspond |
| --- | --- |
| `RR482917367CY` | R : envoi recommandé |
| `CP482917367CY` | C : colis postal |
| `EE482917367CY` | E : envoi EMS |
| `LA482917367CY` | L : lettre suivie |
| `UA482917367CY` | U : envoi de la poste aux lettres contenant des marchandises, sans suivi client |

Un envoi posté à l'étranger conserve le numéro attribué par sa poste d'origine, se terminant par le code de ce pays (`…GB`, `…FR`, `…GR`), et le suivi de Cyprus Post l'accepte aussi avec les scans de la poste étrangère. `There is no information for this item` signifie que le suivi ne connaît pas ce numéro : vérifie-le auprès de l'expéditeur.

## Les statuts de suivi Cyprus Post expliqués

Sur la version anglaise du suivi Cyprus Post, que lit Peek, chaque scan indique l'heure, le pays, le lieu, l'événement, le bureau suivant et des informations complémentaires. Les libellés suivent les [noms d'événements postaux internationaux (UPU)](guide:tracking-statuses) : `(Otb)` marque une étape dans le pays d'expédition, `(Inb)` une étape dans le pays de destination. Le bureau suivant indique où va l'envoi, pas où il se trouve. Pour un envoi à destination de chez toi :

| Statut Cyprus Post | Ce qu'il signifie pour toi |
| --- | --- |
| `Receive item from customer (Otb)` | L'envoi a été déposé, à Chypre ou à l'étranger. |
| `Insert item into bag (Otb)` | Mis en sac au bureau d'échange pour partir à l'étranger. |
| `Receive item at office of exchange (Inb)` | Arrivé dans le pays de destination. |
| `Send items to delivery point/ collection point` | En route vers le bureau qui le livrera ou le tiendra à ta disposition. |
| `Receive item at delivery office (Inb)` | Arrivé au bureau chargé de la distribution. |
| `Hold item at point of delivery (Inb)` | Au bureau de distribution, en attente de sa tournée. |
| `Handover item to delivery agent (Inb)` | Remis au facteur. |
| `Item not delivered/ Pending delivery` | Non livré : la colonne Extra Information précise pourquoi. |
| `Receive item at collection point for pick-up (Inb)` | Prêt à être retiré au bureau de poste. |
| `Deliver item (Inb)` | Livré, ou retiré au bureau de poste. |

## Frais de douane sur les marchandises hors UE

Depuis le 1er juillet 2026, les biens achetés en ligne hors de l'UE, y compris les envois postaux, n'entrent plus en franchise de droits lorsque l'envoi vaut 150 € ou moins : l'UE perçoit un droit de douane temporaire de 3 € par article. Un article regroupe toutes les marchandises de l'envoi partageant le même classement tarifaire, la même description et, lorsqu'elle est déclarée, la même origine, quel que soit le nombre d'unités. Depuis le 1er novembre 2026, l'UE perçoit également une redevance de traitement de l'Union de 2 € par article sur les achats en ligne hors UE, quelle que soit leur valeur, soumise à la TVA. Le paiement incombe en premier lieu à celui qui déclare les marchandises en douane : plateforme, vendeur, transporteur ou représentant. À Chypre, le Department of Customs and Excise perçoit les droits d'importation et la TVA sur les biens provenant hors UE. Voir [les colis retenus en douane](guide:customs).

## Colis Cyprus Post en retard ou non arrivé

Si le suivi n'évolue plus, consulte [le suivi qui ne se met plus à jour](guide:tracking-not-updating). S'il [apparaît livré](guide:delivered-not-received) mais que tu ne l'as pas, vérifie le numéro auprès de l'expéditeur. En cas de perte ou de casse, voir [colis perdu](guide:lost-parcel) et [colis endommagé](guide:damaged-parcel).

**Achat en ligne qui tarde ?** Le Consumer Protection Service de Chypre précise qu'un vendeur doit en règle générale livrer sous 30 jours, sauf autre date convenue. S'il ne le fait pas, informe le vendeur et accorde un délai supplémentaire raisonnable ; s'il ne livre toujours pas, tu peux résilier le contrat. Sa ligne consommateur, le 1429, est gratuite.

**Un message te demande de payer pour débloquer un colis ?** Lis [les faux SMS de livraison](guide:delivery-scam-texts) avant de cliquer sur le moindre lien.

## Suivre un colis Cyprus Post avec Peek

Pour un numéro postal finissant par `CY` avec un chiffre de contrôle valide, [Peek](/) interroge en même temps Cyprus Post et [Chronopost](carrier:chronopost), et affiche « Cyprus Post a ce colis. » quand Cyprus Post connaît le numéro, ou « Pas encore trouvé chez… Peek continuera de vérifier après l’ajout. ». Si le chiffre de contrôle est faux, Peek n'interroge pas Cyprus Post, même en le choisissant avec « Changer » : vérifie le numéro avec l'expéditeur. Un lien cypruspost.post fonctionne lorsqu'il contient le numéro.

Pour un envoi posté à l'étranger, Peek interroge d'abord la poste émettrice quand elle est prise en charge, comme [ELTA](carrier:elta) pour `…GR`, ou les services de suivi. Le suivi de Cyprus Post accepte aussi ces numéros : choisis « Cyprus Post » avec « Changer ». Peek essaie automatiquement Cyprus Post lorsque la poste étrangère indique Chypre comme destination ou, sans destination indiquée, lorsque tu l'ajoutes depuis Chypre, et bascule dès que Cyprus Post publie des scans aussi récents que ceux de la poste étrangère. Peek n'interroge Cyprus Post que pour des numéros à 13 caractères. Un doute sur le transporteur ? Colle le numéro malgré tout : Peek suit plus de 4 500 transporteurs.

Peek affiche les statuts de Cyprus Post tels quels, le bureau ou centre et le pays de chaque scan sur une carte, l'heure de livraison et le bureau de poste où l'envoi attend ou a été retiré. Les scans chypriotes gardent leur heure exacte et ceux d'une poste étrangère sont lus sur l'horloge de son pays (lorsqu'un pays compte plusieurs fuseaux, Peek déduit le fuseau du lieu indiqué, ce qui peut décaler l'heure), le tout affiché dans ton fuseau horaire (« Heures dans ton fuseau horaire : »). Peek n'affiche ni date prévue ni poids, et écarte Extra Information, le bureau suivant, les codes postaux et le nom du signataire : pour connaître le motif d'une non-livraison, consulte le suivi sur cypruspost.post.

Peek interroge directement Cyprus Post jusqu'à toutes les 10 minutes, ou toutes les 2 minutes à partir de 6 h sur l'horloge du dernier scan (heure de Chypre à Chypre) le jour où l'envoi part avec le facteur, tant que le colis est ouvert dans Peek ou le jour de la livraison avec les notifications activées. Après deux jours sans nouvelle (12 heures tant que l'envoi reste sur « Étiquette créée » ou « Pris en charge »), Peek vérifie toutes les heures. Pour les notifications, choisis « Toutes les mises à jour », « Importantes uniquement » ou « Jour de livraison uniquement ». Après quatre jours sans scan sur un envoi en « Étiquette créée », « Pris en charge » ou « En transit », Peek affiche « Aucune mise à jour depuis… », rappelle qu'« Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger Cyprus Post. » et propose « Demande de l’aide à Cyprus Post ».

## Questions fréquentes sur le suivi Cyprus Post

### Cyprus Post est-elle le Department of Postal Services ?

Oui. L'Union postale universelle désigne le Department of Postal Services (Cyprus Post) comme opérateur désigné de Chypre, service public chargé des services postaux, et le régulateur postal OCECPR nomme Cyprus Post prestataire du service postal universel.

### Que signifie CY à la fin du numéro ?

Que le numéro a été émis sous l'autorité de Chypre. Selon la norme S10 de l'UPU, les deux dernières lettres représentent le code du pays sous l'autorité duquel le numéro a été émis, et n'indiquent donc pas de façon certaine où l'envoi a été posté. Peek interroge Cyprus Post pour ces numéros.

### Puis-je suivre un envoi portant un numéro en U ?

Pas comme un envoi suivi. La norme S10 réserve les numéros en U aux envois de la poste aux lettres contenant des marchandises voyageant sans suivi client : le numéro sert à la visibilité logistique, par exemple pour les données électroniques transmises avant l'envoi. Peek interroge néanmoins Cyprus Post pour un numéro U finissant par `CY`.

### Comment déposer une réclamation contre Cyprus Post ?

Écris d'abord à Cyprus Post. Si aucune réponse ne parvient sous 15 jours ou si la réponse ne te satisfait pas, saisis le régulateur postal, l'Office of the Commissioner for Electronic Communications and Postal Regulation (OCECPR). La démarche se fait en ligne ; son site est en grec mais propose un formulaire de réclamation en anglais.

:::sources
- [Universal Postal Union: S10, identification of postal items](https://www.upu.int/UPU/media/upu/files/postalSolutions/programmesAndServices/standards/S10-12.pdf) – 13 caractères, premières lettres R, C, E, L et U, chiffre de contrôle, code du pays émetteur
- [Universal Postal Union: member countries, Cyprus](https://www.upu.int/en/Universal-Postal-Union/About-UPU/Member-Countries?csid=-1&cid=86) – Cyprus Post, opérateur désigné
- [Universal Postal Union: list of designated operators](https://www.upu.int/getmedia/0ff98b1d-9dd4-46e7-bdd1-dda1b654308f/SettlementCircularsEn.pdf) – Department of Postal Services (Cyprus Post)
- [National Open Data Portal: list of post offices](https://www.data.gov.cy/el/dataset/katalogos-kai-theseis-tahydromikon-grafeion) – Department of Postal Services, service public chargé des services postaux (en grec)
- [OCECPR: universal postal service](https://ocecpr.ee.cy/katanalotes/katholiki-ypiresia/taxidromeia) – Cyprus Post comme prestataire du service postal universel (en grec)
- [OCECPR: complaints](https://ocecpr.ee.cy/katanalotes/parapona) – prestataire d'abord par écrit, 15 jours, réclamation en ligne, formulaire en anglais (en grec)
- [Consumer Protection Service: online purchases](https://consumer.gov.cy/en/consumer-rights/66/59/?ctype=ar) – livraison sous 30 jours, délai supplémentaire, résiliation (en grec)
- [Consumer Protection Service: contact us](https://consumer.gov.cy/en/contact-us) – ligne consommateur gratuite 1429 (en grec)
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – droit de 3 € par article depuis le 1er juillet 2026, redevance de traitement de l'Union de 2 € depuis le 1er novembre 2026
- [European Commission: guidance on the EUR 3 customs duty and the Union handling fee](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – définition d'un article, envois postaux, redevance quelle que soit la valeur, redevable, TVA sur la redevance
- [Department of Customs and Excise: About us](https://www.mof.gov.cy/mof/customs/customs.nsf/All/289500205CFF33DFC22572AC003A2B02?OpenDocument) – perception des droits d'importation et de la TVA
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – données lues par Peek : suivi anglais de Cyprus Post, colonnes et noms d'événements
:::
