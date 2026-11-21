---
title: Suivi ELTA Hellenic Post : statuts, retrait et contacts
description: Suis un colis ELTA Hellenic Post ou ELTA Courier en Grèce : numéro GR, statuts en anglais, consignes PostBox, douane, délais de retrait et contacts.
slug: suivi-elta-hellenic-post
published: 2026-11-21
updated: 2026-11-21
---

ELTA Hellenic Post (`ΕΛΤΑ`) est l'opérateur postal national grec, auquel ELTA Courier a désormais été intégré. Suis un envoi sur la [page de suivi en anglais d'ELTA](https://www.elta.gr/en/track), qui accepte jusqu'à cinq numéros à la fois (un par ligne), ou colle le numéro dans le champ ci-dessus. Un colis ELTA Courier se consulte aussi sur la [recherche d'elta-courier.gr](https://www.elta-courier.gr/search), qui prend plusieurs numéros de bordereau à raison d'un par ligne. Le numéro est transmis par l'expéditeur (reçu de dépôt, bordereau courier ou message de la boutique) : le tableau ci-dessous explique chaque statut ; un colis non remis t'attend au bureau de poste ou à l'agence indiqués par le suivi (durée figurant sur l'avis ou le SMS pour un envoi intérieur, 20 jours à compter de l'avis après dédouanement) ; et le centre d'appels d'ELTA répond au `800-11-82000` depuis un fixe grec, au `212-0000-800` depuis un mobile et au `+30 212-0000-800` depuis l'étranger.

## Numéro de suivi ELTA : format et exemples

Les numéros de suivi d'ELTA comptent 13 caractères : [deux lettres latines, neuf chiffres et deux lettres latines](guide:tracking-number-formats). Les envois déposés en Grèce se terminent par `GR`. Le neuvième chiffre étant une clé de contrôle, une seule faute de frappe rend généralement le numéro invalide. Les bordereaux ELTA Courier reprennent le plus souvent ce même format en haut à droite du bordereau. Les envois suivis simples dont le code débute par `U`, `L` ou `TR` (`LL` en régime intérieur grec) sont déposés à l'adresse sans signature.

| Format (inventé) | Ce que c'est |
| --- | --- |
| `RR482910576GR` | Envoi postal expédié depuis la Grèce. Espaces et minuscules n'ont pas d'importance (`RR 482 910 576 GR`). |
| `482910573649` | Code-barres à 12 chiffres porté par certains envois venus d'Allemagne, également accepté par le traceur d'ELTA. |
| `RR482910576CN` | Envoi venu de l'étranger conservant le numéro de la poste d'origine (ici [China Post](carrier:china-post)), terminé par le code du pays d'expédition. |

Numéro introuvable ? Consulte [comment trouver son numéro de suivi](guide:find-tracking-number).

**Formats proches à ne pas confondre.** Un numéro finissant par `CY` appartient à Cyprus Post. Un numéro de bordereau composé uniquement de 10 chiffres n'est pas celui d'ELTA : c'est le format utilisé par ACS Courier et Geniki Taxydromiki.

## Statuts de suivi ELTA expliqués

Le traceur d'ELTA affiche chaque étape en grec ou en anglais (via un sélecteur de langue), avec la date, l'heure et l'établissement (bureau de poste ou agence courier). Voici les intitulés anglais, dans l'ordre habituel d'acheminement :

| Statut ELTA | Ce que cela veut dire pour toi | Peek affiche |
| --- | --- | --- |
| `Label printed by customer` | L'expéditeur a imprimé l'étiquette ; ELTA n'a pas encore le colis. | « Étiquette créée » |
| `Item deposited at ELTA` | L'expéditeur a déposé l'envoi au bureau de poste nommé par le scan. | « Pris en charge » |
| `Shipment in transit` | Expédié depuis le bureau ou l'agence indiqué. | « En transit » |
| `Arrival in` | Arrivé au bureau de poste ou à l'agence courier dont le nom suit (séparé par plusieurs espaces, ce qui peut donner l'impression d'un texte tronqué). | « En transit » |
| `Arrival at the airport` | Pour un envoi quittant la Grèce : arrivé au centre d'ELTA à l'aéroport d'Athènes. | « En transit » |
| `For dispatch abroad` | Au centre de l'aéroport d'Athènes, prêt à quitter la Grèce. | « En transit » |
| `With Courier for delivery` | En tournée de livraison vers ton adresse. | « En livraison » |
| `Notification of attempted delivery` | Remise impossible : un avis de passage a été laissé, accompagné du motif à la même minute. | « Tentative de livraison » |
| `ADDRESSEE NOT AVAILABLE AT TIME OF DELIVERY` | Personne n'était présent lors du passage. | « Tentative de livraison » |
| `ADDRESSEE REQUEST OWN PICK-UP` | La tournée s'est arrêtée car tu as demandé un retrait sur place. | « Tentative de livraison » |
| `Item at post office - Can be collected` | En attente au bureau de poste ou à l'agence nommé dans l'étape. | « Prêt à être retiré » |
| `SMS text message sent for item to be collected` | ELTA t'a envoyé un SMS de rappel pour venir retirer l'envoi ; le colis n'a pas bougé. | Conserve le statut précédent |
| `Shipment delivered` | Livré (observé sur les colis courier retirés en agence). | « Livré » |
| `Item delivered` | Livré (observé sur les colis retirés au bureau de poste). | « Livré » |

Si le traceur répond `No Tracking History` (`Ιστορικό Μη Διαθέσιμο`), l'envoi n'a pas encore de scan ou date de plus de six mois : ELTA conserve l'historique pendant six mois à compter de la date d'envoi.

## Livraison, consignes PostBox et retrait chez ELTA

ELTA livre les colis intérieurs à domicile, en bureau ELTA (après SMS ou avis) ou en consigne PostBox lorsque le code postal le permet, avec un délai annoncé de 3 jours ouvrables dans toute la Grèce.

**Livraison manquée.** Un colis venu de l'étranger qui n'a pu être remis fait l'objet d'un avis invitant à le retirer au bureau de poste le plus proche (la charte d'ELTA ne fixe pas de durée de garde hors douane ; un envoi dédouané attend 20 jours à compter de l'avis). En cas d'absence, le facteur peut aussi rapporter l'envoi au bureau voisin et le déposer dans la PostBox de ce bureau pour un retrait sans file d'attente ; les envois soumis à des droits de douane ne sont remis que contre signature. Un colis intérieur non remis à domicile se retire au guichet après appel, avis papier ou SMS dans le délai indiqué sur cet avis ou ce SMS : passé ce délai, il repart sans frais supplémentaires en Grèce vers l'expéditeur, et s'il n'est pas repris non plus, il est gardé aux rebuts jusqu'à au moins six mois après le dépôt.

**ELTA Courier.** Le service courier effectue deux tentatives de livraison. Après le premier avis, appelle ton agence courier locale pour convenir d'un passage ; après deux avis, retire le colis en agence ou programme une nouvelle livraison (potentiellement payante). Les agences courier ouvrent du lundi au vendredi de `08:00` à `20:00` et le samedi de `08:00` à `14:00` (horaires susceptibles de varier selon les secteurs). Un envoi courier porte-à-porte non distribuable repart chez l'expéditeur sous deux semaines après l'envoi, sauf clause contractuelle différente.

**Consignes PostBox.** Les consignes intelligentes d'ELTA sont accessibles `24h/24` et `7j/7` (celles situées dans les stations de métro et d'ISAP suivent les horaires des stations). Dès la mise en casier, un message Viber ou SMS communique un code PIN à 6 chiffres et l'emplacement : retire l'envoi sous 48 heures, faute de quoi il est transféré au bureau ELTA le plus proche avec un nouveau message. Les envois grevés de droits ou taxes n'y sont pas admis. Tu peux demander un dépôt via `Θέλω από PostBox` dans le SMS ou message Viber d'ELTA (pour un envoi international, ce choix dépend du stade de tri), sans surcoût, jusqu'à `62 × 36 × 60 cm` et `20 kg`.

**Changement d'adresse.** Pour un envoi courier, seul l'expéditeur peut le demander par écrit à `customerservice@elta-courier.gr`. Pour un envoi postal entièrement suivi, l'expéditeur dépose une demande en personne en bureau avec pièce d'identité.

## Colis ELTA retardé, en douane, perdu ou endommagé

Si le suivi reste figé plusieurs jours, consulte d'abord [pourquoi le suivi ne bouge plus](guide:tracking-not-updating).

**Douane.** En Grèce (Union européenne), une commande en ligne expédiée hors UE d'une valeur jusqu'à 150 € supporte depuis le 1er juillet 2026 un droit de douane temporaire de 3 € par catégorie d'articles, auquel s'ajoute depuis le 1er novembre 2026 des frais de traitement de l'Union de 2 € par catégorie d'articles sur les ventes à distance quelle qu'en soit la valeur, ainsi que la TVA grecque à l'importation (hors Mont Athos, situé hors du territoire TVA de l'UE) sauf si la TVA a déjà été réglée à l'achat via le guichet IOSS. Sur un envoi ELTA, les frais de douane se paient **uniquement au guichet d'un bureau ELTA** lors du retrait : ELTA envoie un avis détaillant les montants et le bureau, où le colis attend 20 jours (présente l'avis et une carte d'identité, un passeport ou un permis de conduire, ou mandate un tiers muni de sa pièce d'identité). Au bout de 20 jours, l'envoi retourne au centre de dédouanement d'ELTA comme non réclamé ou pour renvoi à l'expéditeur. Si tu contestes le montant, ne retire pas le colis : dépose au guichet une demande motivée avec justificatif de valeur ([colis retenu en douane](guide:customs)).

**Colis perdu.** Expéditeur ou destinataire peut déposer une demande de recherche en bureau ELTA avec pièce d'identité et reçu de dépôt dans les six mois suivant l'envoi (uniquement pour les envois entièrement suivis). ELTA répond par lettre recommandée sous 8 semaines (4 semaines vers certaines destinations étrangères) ([colis perdu](guide:lost-parcel)).

**Colis courier endommagé.** Accepte-le avec réserves et déclare l'avarie sous un jour ouvrable via le formulaire de contact ELTA Courier ou en agence, sans quoi ELTA décline toute responsabilité. Le dossier d'indemnisation est envoyé à `apozimiwseis@elta-courier.gr` avec le bordereau, la facture et des photos de l'objet et de l'emballage ; l'indemnité revient à l'expéditeur, ou à toi s'il y renonce par écrit (pour un achat en ligne, vois avec la boutique ; service indemnisations : `+30 210-6073066`).

**Réclamations.** ELTA répond sous 15 jours ; en cas de désaccord persistant, demande par écrit au service client de saisir son Comité de règlement des différends.

## Numéros de téléphone et contacts ELTA

Canaux officiels publiés par `elta.gr` et `elta-courier.gr` :

- **Centre d'appels ELTA** : `800-11-82000` depuis un fixe grec, `212-0000-800` depuis un mobile, `+30 212-0000-800` depuis l'étranger, du lundi au vendredi de `08:00` à `18:00`.
- **Nouveaux points de service** : `212 00 00 700` (uniquement pour les questions sur les nouveaux points de service d'ELTA), du lundi au vendredi de `08:00` à `18:00`, au tarif d'un appel local.
- **E-mail ELTA** : `service@elta-net.gr` ou le formulaire sur [elta.gr/en/contact](https://www.elta.gr/en/contact).
- **ELTA Courier** : `210 6073000` (tarif d'un appel ordinaire) ou `11120` (tarif de ton opérateur), aide au suivi du lundi au vendredi de `08:00` à `16:00` (suivi en ligne uniquement le week-end et les jours fériés) ; `info@elta-courier.gr` et `customercare@elta-courier.gr` ; réclamations écrites par courrier au `200 Ionias Ave. & 61 Iakovaton St., 111 44 Athens` (Customer Service Department).
- **Par courrier (ELTA)** : `Hellenic Post Customer Service, Adrianoupoleos 45, 16070 Kessariani`.
- **Sur place** : tout bureau ELTA (annuaire des bureaux sur `elta.gr`).

> ELTA ne demande jamais tes coordonnées bancaires ou de carte, ne réclame jamais par SMS ou e-mail de frais pour livrer un colis et n'appelle jamais depuis un numéro étranger. Les frais de douane se règlent uniquement en bureau ELTA. Les faux e-mails intitulés `Ειδοποίηση αποτυχίας παράδοσης` ne proviennent pas d'ELTA (`elta.gr`, `itemsearch.elta.gr` et `elta-courier.gr` sont ses domaines officiels ; voir [reconnaître les SMS de livraison frauduleux](guide:delivery-scam-texts)).

## Suivre les colis ELTA avec Peek

Pour un numéro terminé par `GR` avec une clé de contrôle valide, [Peek](/) interroge aussitôt ELTA (ainsi que [Chronopost](carrier:chronopost)) et affiche « ELTA Hellenic Post a ce colis. » dès qu'ELTA le connaît, ou « Pas encore trouvé chez … Peek continuera de vérifier après l’ajout. » dans le cas contraire. Si la clé de contrôle est erronée (`RR482910570GR`), ELTA n'est pas interrogé et le choisir manuellement affiche qu'il « n’émet pas de numéros comme celui-ci » : vérifie le code auprès de l'expéditeur. Un code-barres allemand à 12 chiffres saisi seul est proposé à d'autres transporteurs : touche « Changer » (ou « Changer de transporteur ») ou colle un lien de suivi `elta.gr` ou `elta-courier.gr` (« Transporteur détecté »).

Un envoi postal venu de l'étranger est d'abord suivi auprès de sa poste d'origine ; dès que celle-ci indique une destination en Grèce (ou ne mentionne aucune destination alors que tu es en Grèce), Peek interroge aussi ELTA avec le même numéro et bascule dessus dès qu'une étape récente y apparaît, tout en conservant l'historique antérieur (tu peux aussi sélectionner ELTA d'emblée via « Changer »).

ELTA fournit pour chaque scan une date et une heure sans fuseau : Peek les interprète sur l'horloge du lieu du scan (heure grecque `Europe/Athens` en Grèce), les convertit dans le fuseau de ton appareil (« Heures dans ton fuseau horaire : … »), affiche les intitulés anglais d'ELTA ainsi que le bureau ou l'agence où le colis attend, sans date de livraison estimée ni poids (ELTA n'en donne pas). Après quatre jours sans étape en transit, Peek signale « Aucune mise à jour depuis quatre jours ». Peek vérifie jusqu'à toutes les 10 minutes avec le colis ouvert ou le jour de livraison avec alertes actives, et jusqu'à toutes les 2 minutes dès 6 h (heure grecque) le jour en « En livraison » ; sinon, et après 12 h sur « Étiquette créée » / « Pris en charge » ou deux jours calmes, toutes les heures (« Chaque scan », « Étapes importantes » ou « Livraison seulement »).

## Questions fréquentes sur le suivi ELTA

### Peut-on consulter le suivi ELTA en anglais ?

Oui. `elta.gr/en/track` affiche le formulaire et chaque scan en anglais ; `elta.gr/track` s'ouvre en grec avec un sélecteur de langue.

### Peut-on suivre un colis international avec ELTA ?

Oui, avec le numéro postal remis par l'expéditeur (terminé par le code du pays d'origine, comme `RR482910576CN`) : le traceur d'ELTA l'accepte directement.

### Qu'est-ce qu'ELTA Courier ?

Le service express porte-à-porte d'ELTA (doté de son propre site `elta-courier.gr` pour la Grèce, l'EMS et Chypre), fusionné au sein d'ELTA en 2024.

### Où trouver le bureau de poste ELTA le plus proche ?

Via l'annuaire des bureaux sur `elta.gr`, et sur la page réseau d'`elta-courier.gr` pour les agences ELTA Courier.

:::sources
- [ELTA : Track and Trace (anglais)](https://www.elta.gr/en/track) – traceur anglais, format à 13 caractères, codes allemands à 12 chiffres et recherche jusqu'à 5 numéros
- [ELTA : Track and Trace (grec)](https://www.elta.gr/track) – traceur grec et sélecteur de langue
- [Hellenic Post : Track an Item](https://itemsearch.elta.gr/en-GB) – conservation sur six mois, horaires du centre d'appels, e-mail et adresse postale
- [ELTA : Contact](https://www.elta.gr/en/contact) – numéros du centre d'appels, appel depuis l'étranger, ligne nouveaux points de service, téléphone courier et formulaire
- [ELTA : Επικοινωνία](https://www.elta.gr/contact) – numéros, recherche de bureaux et informations légales
- [ELTA : Προστατευθείτε από το Phishing](https://www.elta.gr/prosochi-sto-phishing) – alerte contre les faux SMS et e-mails et paiement des droits uniquement en bureau
- [ELTA Courier : FAQ (anglais)](https://www.elta-courier.gr/faq?lang=en) – deux tentatives, avis, horaires d'agence, bordereau, changement d'adresse, courrier réclamations et téléphone indemnisations
- [ELTA Courier : Συχνές ερωτήσεις](https://www.elta-courier.gr/faq) – numéro court 11120 facturé au tarif opérateur
- [ELTA Courier : Αναζήτηση Αποστολής](https://www.elta-courier.gr/search) – recherche multi-bordereaux un par ligne
- [ELTA Courier : Επικοινωνία](https://www.elta-courier.gr/contact) – 210 6073000, horaires d'aide au suivi, e-mails et réserve sous un jour ouvrable
- [ELTA Courier : Έντυπο Αποζημίωσης](https://www.elta-courier.gr/apozimiwseis) – formulaire d'indemnisation, e-mail apozimiwseis@elta-courier.gr et justificatifs
- [ELTA Courier : Τηλεφωνική εξυπηρέτηση](https://www.elta-courier.gr/tilefwniki_eksypiresisi) – tarification téléphonique et suivi en ligne seul le week-end et les jours fériés
- [ELTA : PostBox](https://www.elta.gr/paralabi-antikeimenon-apo-tin-isonexupni-thuridasin-elta) – code PIN par SMS ou Viber, délai de 48 h, horaires, dimensions 62 × 36 × 60 cm / 20 kg, gratuité et PostBox du bureau après échec
- [ELTA : Εκτελωνισμός](https://www.elta.gr/ektelonismos-2) – dédouanement, avis avec frais, garde de 20 jours, pièce d'identité, procuration, non-réclamés et contestation
- [ELTA : Δέματα Εσωτερικού](https://www.elta.gr/demata-esoterikou) – délai de 3 jours ouvrables et modes de remise
- [ELTA : Υπηρεσίες Ταχυμεταφορών](https://www.elta.gr/ypiresia-esoterikou-porta-porta) – service courier porte-à-porte
- [ELTA : Αίτηση Αναζήτησης](https://www.elta.gr/aitisi-anazitisis) – demande de recherche sous six mois et réponse sous 8 semaines
- [ELTA : Αίτηση Τροποποίησης](https://www.elta.gr/aitisi-tropopoiisis) – modification de nom ou d'adresse par l'expéditeur
- [ELTA : Χάρτης Υποχρεώσεων Καταναλωτή](https://media.elta.gr/uploads/media/2025/kanonismoi/___2025.pdf) – absorption d'ELTA Courier, retrait au guichet, retours et garde de six mois, retour courier sous deux semaines, lettres suivies U/LL/L/TR et réclamations sous 15 jours
- [ELTA : Γενικοί όροι Courier](https://media.elta.gr/uploads/media/etairia/general_terms_courier_12_2024_compressed%20%281%29.pdf) – versement de l'indemnité à l'expéditeur
- [Commission européenne : EU Customs Reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – droit temporaire de 3 € jusqu'à 150 € depuis juillet 2026 et frais de traitement de 2 € depuis novembre 2026
- [Commission européenne : The temporary EUR 3 customs duty and the Union handling fee](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – frais de 2 € sur les ventes à distance et maintien de l'exonération de TVA à l'importation via IOSS
- [Commission européenne : Champ d'application territorial de la TVA et des douanes](https://taxation-customs.ec.europa.eu/taxation/vat/vat-directive/how-does-vat-work/territorial-scope_en) – Mont Athos dans le territoire douanier de l'UE mais hors du territoire TVA de l'UE
- [Universal Parcel Scraper : ELTA Hellenic Post](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/elta) – formats S10 et 12 chiffres, liens, statuts anglais, point de retrait et heure grecque
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – détection S10, relais depuis les postes étrangères et affichage dans Peek
:::
