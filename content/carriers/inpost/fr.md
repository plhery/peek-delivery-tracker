---
title: Suivi InPost : numéros, statuts, Lockers et Mondial Relay
description: Suivre un colis InPost en France, Belgique ou Suisse : formats de numéro, statuts, délai de retrait en Locker ou Point Relais, douane et contact.
slug: inpost-suivi
published: 2026-11-17
updated: 2026-11-17
---

Fondé en Pologne en 1999 autour des consignes Paczkomat, le groupe InPost (`InPost S.A.`, basé au Luxembourg) réunit environ 95 000 Lockers et points de retrait dans neuf pays européens. Depuis le rachat de [Mondial Relay](carrier:mondial-relay) en 2021, le réseau conserve la marque Mondial Relay en France, en Belgique et au Luxembourg, porte déjà le nom InPost en Espagne et au Portugal, passe progressivement sous le nom InPost aux Pays-Bas et opère directement comme InPost en Pologne, en Italie et au Royaume-Uni (où InPost UK a acquis Yodel en avril 2025). En Suisse, les colis transfrontaliers sont remis à un transporteur local tel que [la Poste suisse](carrier:swiss-post).

Pour suivre un colis InPost venant de Pologne, d'Italie ou du Royaume-Uni, collez le numéro fourni par le marchand ci-dessus ou sur le site du pays d'envoi comme [inpost.co.uk/tracking](https://inpost.co.uk/tracking). Pour un envoi français ou belge à 8 chiffres, consultez aussi la page [Mondial Relay](carrier:mondial-relay).

## Numéro de suivi InPost : formats et où le trouver

Selon InPost Royaume-Uni, le numéro figure dans le courriel d'expédition de la boutique et dans les avis InPost ; InPost Portugal conseille de le demander à l'expéditeur s'il manque. Rien reçu ? Voir [retrouver un numéro de suivi](guide:find-tracking-number).

| Numéro (inventé) | Réseau concerné |
| --- | --- |
| `634819205746382910475628` | 24 chiffres : Pologne et Italie. Aussi employé par [bpost](carrier:bpost), [Ciblex](carrier:ciblex) et Austrian Post. |
| `8YDR482910573` | `8YDR` et 9 chiffres : Royaume-Uni. |
| `JD5829104738291640` | `JD` et 16 chiffres : Royaume-Uni (`JD` et 18 chiffres : [DHL](carrier:dhl)). |
| `JJD5829104738291640` | `JJD` et 16 chiffres : Royaume-Uni (`JJD` et 18 chiffres : [DHL](carrier:dhl)). |
| `24XKP5829104A317` | 2 chiffres, 3 lettres, 7 chiffres, `A` et 3 chiffres : étiquette Yodel by InPost (Royaume-Uni). |
| `FV48291057` | `FV` et 8 chiffres : InPost Espagne, suivi dans Peek sous [Mondial Relay](carrier:mondial-relay). |
| `48291057` | 8 chiffres (exemple sur `inpost.pt` et `inpost.es`) : [Mondial Relay](carrier:mondial-relay) (FR, BE, LU) et InPost Espagne/Portugal (aussi 10 ou 12 chiffres). |

Ni le code PIN à 6 chiffres (France, Belgique, Espagne, Portugal), ni le code de collecte britannique à 9 chiffres, ni le QR code, ni le « kod nadania » polonais (code à 9 chiffres permettant sur Allegro Lokalnie un dépôt sans étiquette selon le règlement Allegro) ne servent au suivi. Si la boutique nomme un autre livreur final, consultez [reconnaître le format d'un numéro de suivi](guide:tracking-number-formats).

## Statuts de suivi InPost : signification des étapes

Sur `inposteasy.com`, les étapes des colis polonais, italiens ou britanniques remontent en anglais :

| Statut InPost (anglais) | Signification |
| --- | --- |
| `Shipment Created` | Envoi créé, pas encore remis à InPost. |
| `Sent through Parcel Locker/Parcel Point` | Déposé par l'expéditeur en Locker ou point relais. |
| `Collected by the Courier` | Relevé par le chauffeur InPost vers le tri (et non retiré par le destinataire). |
| `Accepted at the Logistics Centre` / `Dispatched from the Logistics Centre` | Entré puis reparti d'un centre logistique InPost. |
| `On the way` | En transit entre plateformes. |
| `Handed over for delivery` | En tournée vers le Locker, le point ou votre domicile. |
| `Ready for collection` | Disponible au retrait : le délai de garde court. |
| `Stored Parcel - Temporary Parcel Locker` | Point choisi complet ou en panne : le colis attend dans un Locker voisin avec le même code, avant transfert vers le point initial s'il n'y est pas retiré (`The storage period has expired`). |
| `Reminder of a waiting parcel` | Rappel avant fin du délai de garde. |
| `Failed delivery attempt` / `Acceptance refusal` | Livraison échouée ou colis refusé. |
| `Delivered` | Retiré ou livré à domicile. |
| `On the way back to the Sender` / `Returned to Sender` | Retour ou colis rendu à l'expéditeur. |

Sur son site britannique, InPost affiche des titres familiers : `Sit tight, your parcel’s loading` (préparation), `We’re on it` (déposé), `It’s on the road` (en transit), `Not long now` (centre local), `It’s getting close` (livraison tentée ce jour), `Delivery day, yay!` (déjà au Locker ou en boutique : vos 3 jours ont démarré), `Tick tock...` (dernier jour), `Uh oh` (retour expéditeur) et `It's back` (rendu).

## Retrait en Locker ou Point Relais, garde et Vinted

InPost envoie par courriel ou SMS un QR code et un code de retrait : PIN à 6 chiffres sur Mondial Relay et la péninsule Ibérique, ou code à 9 chiffres au Royaume-Uni (saisi après votre numéro de téléphone sans le `0` initial). En boutique relais, prévoyez une pièce d'identité. L'application ouvre le casier sans écran via `Remote Opening` au Royaume-Uni (code sous `Tracking → Receiving`). Voir [les consignes de colis](guide:parcel-lockers).

| Pays de retrait | Délai de garde |
| --- | --- |
| France, Belgique, Luxembourg | 5 jours en Locker ou Point Relais (barème international InPost Portugal). |
| Espagne et Portugal | 5 jours calendaires (certaines pages portugaises citent encore 8 jours ; en Espagne, reprise par le chauffeur le 5e jour). |
| Royaume-Uni | 3 jours avec rappel à 24 h, sans prolongation. |
| Italie | 3 jours en Locker ou Point Relais (barème international InPost Portugal). |
| Pologne | 48 h annoncées à l'international par InPost Portugal ; sur place, délai et prolongation Paczkomat dans l'application InPost Mobile. |
| Suisse (poste) | Chez [la Poste suisse](carrier:swiss-post), 7 jours en général ou 15 jours pour un envoi international (prolongeable). |

Non retiré à temps, le colis repart vers l'expéditeur ([colis non retiré](guide:uncollected-parcel)). Un proche peut retirer le colis avec le code au Locker, ou en relais commerçant avec sa pièce d'identité, la vôtre (original ou copie) et une procuration signée. En cas de point saturé, InPost redirige vers un point proche. Au Royaume-Uni, la livraison à domicile a lieu du lundi au samedi (7 h–21 h, créneau de 2 h dans l'application, redirection via `Manage my parcel → Deliver to Locker`), avec nouvel essai le jour ouvré suivant ou dépôt en Locker proche en cas d'absence. Sur [Vinted](guide:vinted-orders), la France est reliée à la Belgique, au Luxembourg, à l'Espagne, au Portugal, aux Pays-Bas, à l'Italie, à l'Allemagne, à l'Autriche et à l'Irlande via [Mondial Relay](carrier:mondial-relay), [Chronopost](carrier:chronopost), [La Poste / Colissimo](carrier:la-poste), Vinted Go et [DHL Express](carrier:dhl-express).

## Douane, frais d'importation et faux SMS

Aucun frais douanier ne s'applique entre États membres de l'UE (Pologne, Italie, Espagne, Belgique, France, Luxembourg). En revanche, **une demande de paiement est réelle** lorsqu'un envoi franchit une frontière fiscale sans prépaiement complet à la commande :

- **Depuis le Royaume-Uni ou hors UE vers la France, la Belgique ou le Luxembourg :** hors TVA déjà réglée via IOSS (jusqu'à 150 €), le transporteur dédouanant perçoit la TVA, ses frais éventuels de dossier et, selon la réforme douanière européenne, dès le **1er juillet 2026** un droit forfaitaire de **3 € par catégorie d'articles** jusqu'à 150 € (hors origine préférentielle), puis dès le **1er novembre 2026** des frais européens de traitement de **2 € par catégorie d'articles**.
- **Vers la Suisse :** selon l'OFDF, la Poste ou le transporteur déclare le colis et perçoit la TVA suisse, les droits éventuels et les frais de dédouanement au-delà de la franchise (le statut « retenu par la douane » ou `Your item is being held in Customs` indique un dédouanement postal normal en cours).

**Vrai paiement ou phishing.** Mondial Relay (cité par Cybermalveillance.gouv.fr) mentionne ne jamais demander de frais additionnels, et InPost Espagne/Portugal indiquent ne jamais réclamer de virement, de téléchargement (Dropbox, WeTransfer), d'appel surtaxé ni de frais ou de douane par lien SMS ; les courriels ibériques légitimes proviennent de `@inpost.es` ou `@inpost.pt`. Si des frais d'importation sont réellement dus (colis du Royaume-Uni ou vers la Suisse), **ne cliquez jamais sur le lien d'un SMS ou courriel imprévu** : ouvrez vous-même le site officiel du transporteur local qui dédouane (La Poste, bpost, Poste suisse) avec votre numéro de suivi, ou payez au retrait. Signalez tout abus à `phishing@inpost-group.com` (`fraude@inpost.es` en Espagne) ou au **33700** en France. Voir [faux SMS de livraison](guide:delivery-scam-texts) et [colis en douane](guide:customs).

## Colis bloqué, en retard, perdu ou abîmé

Pour un achat en ligne professionnel dans l'UE, le vendeur reste responsable jusqu'à la remise et dispose par défaut de 30 jours pour livrer selon Your Europe : contactez d'abord la boutique (ou ouvrez un litige sur la plateforme pour [une commande de seconde main](guide:second-hand-orders)).

- **Royaume-Uni :** comptez jusqu'à une semaine pour qu'un envoi international rejoigne InPost ; après prise en charge, signalez une absence de scan de **72 heures** au service client (remboursement par le vendeur). Voir [suivi bloqué](guide:tracking-not-updating).
- **Pologne :** auprès d'`InPost sp. z o.o.` (opérateur postal inscrit à l'UKE), réclamation sous **12 mois** dès le dépôt, réserve immédiate pour dommage apparent, **7 jours** pour avarie cachée, et réponse sous **30 jours** (acceptée par défaut au-delà).
- **Italie, Espagne et Portugal :** en Italie, réclamation écrite selon la Charte des services (avec cession écrite de l'expéditeur pour le destinataire) et litiges devant l'AGCOM selon les conditions du compte InPost ; en Espagne et au Portugal, retard au-delà de 5 jours (sous 21 jours après mise à disposition) et perte totale à **30 jours**. Voir [colis perdu](guide:lost-parcel), [colis abîmé](guide:damaged-parcel) et [livré mais non reçu](guide:delivered-not-received).

## Contacter InPost (vérifié en novembre 2026)

- **France, Belgique et Luxembourg :** via [Mondial Relay](carrier:mondial-relay).
- **Royaume-Uni :** `0330 335 0950` (tarif local UK), lun–ven 7 h–22 h, sam–dim 8 h–20 h, sur [inpost.co.uk/help](https://inpost.co.uk/help).
- **Pologne :** formulaire, chat IA dans InPost Mobile, `+48 722 444 000` / `+48 746 600 000`, `InPost sp. z o.o., ul. Pana Tadeusza 4, 30-727 Kraków`.
- **Italie :** `+39 0238582894` (lun–sam 8 h–20 h CEST, indiqué dans les conditions du compte InPost).
- **Espagne et Portugal :** `900 900 137` ou `+34 938 16 62 34` (lun–ven 8 h 30–20 h, sam 9 h–13 h) sur [inpost.es/contacta-con-nosotros](https://www.inpost.es/contacta-con-nosotros) ; `211 245 624` (lun–ven 9 h–18 h) sur [inpost.pt/fala-connosco](https://www.inpost.pt/fala-connosco).

## Suivre un colis InPost avec Peek

[Peek](/) identifie aussitôt les numéros `8YDR`. Sur 24 chiffres, `JD`+16, `JJD`+16 et les étiquettes Yodel, Peek interroge InPost (et sur 24 chiffres aussi Austrian Post et [Ciblex](carrier:ciblex), ainsi que [bpost](carrier:bpost) en premier dès `3232` ou `3299`). Les liens `inpost.pl` sont acceptés, et ceux d'`inpost.co.uk` s'ils portent un numéro `8YDR` ; copiez le numéro seul depuis `inpost.it`, `inpost.pt` ou `inpost.es`.

Les numéros à 8, 10, 12, 14 ou 26 chiffres et `FV`+8 chiffres passent par la fiche [Mondial Relay](carrier:mondial-relay) (« Aussi appelé InPost Spain »). Choisir « InPost » sur 8 chiffres affiche qu'InPost « n’émet pas de numéros comme celui-ci » ; touchez « Changer », sélectionnez Mondial Relay et saisissez le « Code postal de livraison » pour 8 chiffres.

Sous InPost, Peek lit `inposteasy.com` en anglais (réponse non encore confirmée sur les numéros britanniques) : statut, jusqu'aux 20 derniers scans sur carte, heure de livraison et pays de destination, sans date prévue, horaires ni code PIN. Sur 24 chiffres en retrait ou retirés, nom et adresse du point apparaissent si le suivi polonais les confirme (un point italien peut ne pas afficher « Prêt à être retiré »). Les scans sans fuseau horaire et le téléphone sont écartés ; le nom (« Destinataire »), l'adresse et le signataire sont lus mais le nom n'apparaît qu'au propriétaire du suivi, jamais sur un lien partagé.

Peek ne suit pas le passage à un autre transporteur. Il vérifie **jusqu’à toutes les 10 minutes**, ou **jusqu’à toutes les 2 minutes** dès 6 h locales le jour de mise en livraison tant qu'InPost répond directement (**jusqu’à toutes les 15 minutes** via les services universels), avec alertes « Toutes les mises à jour », « Importantes uniquement » ou « Jour de livraison uniquement ». Après deux jours sans scan — ou 12 heures sur « Étiquette créée » ou « Pris en charge » — la vérification devient horaire. Peek ne contacte ni InPost ni la boutique.

## Questions sur le suivi InPost

### InPost et Mondial Relay sont-ils la même société ?

Oui : InPost (`InPost S.A.`) a racheté [Mondial Relay](carrier:mondial-relay) en 2021. Le nom Mondial Relay demeure en France, Belgique et Luxembourg, tandis que l'Espagne et le Portugal utilisent déjà InPost.

### Combien de jours un colis reste-t-il en Locker InPost ?

5 jours en France, Belgique, Luxembourg, Espagne et Portugal, 3 jours sans prolongation au Royaume-Uni, 3 jours en Italie (selon le barème international d'InPost Portugal) et selon l'application InPost Mobile en Pologne.

### Pourquoi mon code à 6 ou 9 chiffres ne donne-t-il aucun suivi ?

C'est le code de retrait (PIN à 6 chiffres ou code britannique à 9 chiffres), pas le numéro d'expédition. Utilisez le numéro à 24 ou 8 chiffres, ou débutant par `JD`, `JJD`, `8YDR` ou `FV`.

### Quel est le délai moyen de livraison chez InPost ?

Au Royaume-Uni, InPost annonce aux expéditeurs 3 jours ouvrés vers un Locker ou Shop et 4 jours ouvrés à domicile (5 jours ouvrés pour un retour marchand). Pour la France et la Belgique, voir [Mondial Relay](carrier:mondial-relay).

:::sources
- [InPost : Who we are](https://inpost.eu/who-we-are) – Pologne 1999, InPost S.A. au Luxembourg
- [InPost Nederland : Home](https://inpost.nl/) – 9 marchés, 95 000 points, transition néerlandaise
- [InPost España : Quiénes somos](https://www.inpost.es/quienes-somos) – rachat de Mondial Relay en 2021
- [InPost UK : Tracking](https://inpost.co.uk/tracking) – 72 h, colis internationaux, Remote Opening, 3 et 4 jours ouvrés
- [InPost UK : Help](https://inpost.co.uk/help) – 0330 335 0950 et horaires
- [InPost UK : Yodel by InPost help](https://inpost.co.uk/yodel-help) – Yodel avril 2025, JD/JJD/8, code à 9 chiffres, 7 h–21 h, Deliver to Locker
- [InPost UK : Track to the Future](https://inpost.co.uk/blog/tracking-explained) – intitulés web UK et 3 jours
- [InPost Portugal : Rastrear Encomenda](https://www.inpost.pt/seguimento-do-envio) – 8 caractères, pièce d'identité, 5 jours
- [InPost Portugal : Como recolher](https://www.inpost.pt/recebe-a-tua-encomenda/como-recolher) – PIN à 6 chiffres, 5 ou 8 jours
- [InPost Portugal : Fala connosco](https://www.inpost.pt/fala-connosco) – garde FR/BE/LU/ES 5 jours, IT 3 jours, PL 48 h, 211 245 624, réclamations 21 et 30 jours
- [InPost España : Atención al Cliente](https://www.inpost.es/contacta-con-nosotros) – 900 900 137, +34 938 16 62 34, procuration
- [InPost España : Fraude Online](https://www.inpost.es/envia-con-nosotros/como-enviar/pago-seguro/informacion-sobre-fraude) – @inpost.es, pas de douane par SMS
- [InPost Portugal : Prevenção de Fraudes](https://www.inpost.pt/faz-um-envio/como-enviar/pagamento-seguro/informacao-sobre-fraude) – @inpost.pt, alertes smishing
- [Cybermalveillance.gouv.fr : Escroqueries livraison](https://www.cybermalveillance.gouv.fr/tous-nos-contenus/actualites/escroqueries-livraison-colis) – consigne Mondial Relay sans frais additionnels, 33700, phishing@inpost-group.com
- [Vinted France : Modes d'envoi](https://www.vinted.fr/help/234) – pays reliés et transporteurs
- [InPost : Regulamin Konta InPost](https://terms.inpost-group.com/pl/regulamin) – prolongation Paczkomat, contacts polonais et italiens (+39 0238582894), réclamations et AGCOM
- [UKE : Rejestr operatorów, InPost](https://bip.uke.gov.pl/rop/rejestr-operatorow-pocztowych/?p=2&page=1&rejestr=B-00562) – opérateur à Cracovie
- [UKE : Reklamacje pocztowe](https://cik.uke.gov.pl/uslugi-pocztowe/dochodzenie-roszczen/reklamacje/) – 12 mois et réponse sous 30 jours
- [UKE : Reklamacje FAQ](https://cik.uke.gov.pl/uslugi-pocztowe/pytania-i-odpowiedzi/reklamacje-i-odszkodowania-faq/2.html) – 7 jours avarie cachée
- [Allegro : Załącznik 16A](https://policies.allegro.com/doc/f86a1a793dc4f5bc4b695b0ff0d54d974badab4169f5aaaddad89585c2c90690) – kod nadania
- [Commission européenne : EU Customs Reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 3 € et 2 €
- [OFDF : Réception de paquets](https://www.bazg.admin.ch/fr/reception-de-lettres-et-paquets) – douane et TVA en Suisse
- [La Poste suisse : Invitation à retirer](https://www.post.ch/fr/reception/invitation-a-retirer) – 7 et 15 jours
- [Your Europe : Livraison](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_fr.htm) – 30 jours et responsabilité vendeur
- [InPost Easy](https://inposteasy.com/en) – service lu par Peek
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formats, statuts, confidentialité et cadences Peek
:::
