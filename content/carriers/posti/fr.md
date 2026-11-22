---
title: Suivi Posti : code JJFI, statuts, consignes et contact
description: Suivre un colis Posti en Finlande : format du code JJFI, signification des statuts anglais, durée en consigne, douane et contact Posti.
slug: suivi-posti
published: 2026-11-22
updated: 2026-11-22
---

Posti est l'opérateur postal et de colis de Finlande. Suis un colis sur la [page de suivi de Posti](https://www.posti.fi/en/tracking) ou dans l'application OmaPosti avec son identifiant d'envoi (*item ID*), ou colle le numéro dans la case ci-dessus. Le numéro provient de l'expéditeur et figure aussi sur l'avis d'arrivée de Posti dans OmaPosti, par SMS ou par e-mail.

## Numéro de suivi Posti : l'identifiant JJFI

Posti l'appelle *item ID* (parfois *shipment ID*). Pour les propres colis de Posti, il commence par `JJFI`. Le site de Posti dispose d'une version anglaise (« EN » en haut).

| Numéro (inventé) | À quoi il correspond |
| --- | --- |
| `JJFI63482917465283914` | Un colis Posti : `JJFI` suivi de 17 chiffres, soit 21 caractères au total. |
| `RR482917367FI` | Un envoi recommandé ou suivi posté en Finlande, au format international : deux lettres, neuf chiffres, `FI`. |
| `RR482917367CN` | Un colis venu de l'étranger garde le numéro de l'expéditeur. Celui-ci finit par `CN` (China Post) ; Posti le suit sous ce même numéro. |
| `00064382917465283912` | Un code-barres SSCC à 20 chiffres ; `64` après les trois premiers chiffres est le préfixe de la Finlande. |

Si l'expéditeur a transmis ton numéro de téléphone à Posti, le colis apparaît tout seul dans OmaPosti ; sinon ajoute-le manuellement. Le suivi Posti ne répond qu'à un code exact, jamais à un nom ou une adresse.

Le code de retrait et le code d'ouverture du casier (6 ou 9 caractères) ne sont pas des numéros de suivi. Un numéro commençant par `JJEE` appartient à Omniva, la poste estonienne, et un numéro finissant par `SE` à PostNord en Suède. Tu hésites sur le transporteur ? Voir [les formats de numéros de suivi](guide:tracking-number-formats).

## Signification des statuts Posti en anglais

Le suivi de Posti affiche ses libellés en anglais, en finnois ou en suédois selon la langue de la page, souvent accompagnés d'une ligne explicative. Voici ceux qu'un colis rencontre habituellement, à peu près dans l'ordre :

| Posti indique | Ce que ça veut dire pour toi |
| --- | --- |
| `We have received information about an upcoming delivery from the sender` | L'expéditeur a annoncé le colis. Posti ne l'a pas encore. |
| `Item received for transport` | Posti a pris le colis en charge. |
| `Item is in transport in country of origin.` | Posté à l'étranger. Pas encore d'estimation de livraison. |
| `Item arrived in the destination country.` | Arrivé en Finlande et pris en relais par Posti. |
| `The item is in sorting` | En centre de tri. Les colis sont généralement triés une ou deux fois, davantage sur les longs trajets. |
| `Item accepted from transport` | Toujours en chemin. Pas encore livré, malgré la formulation. |
| `Item is routed to an outlet because the locker was full.` | Ta consigne choisie était pleine ; le colis va dans un point Posti à la place. |
| `Item is in delivery transportation.` | Dans le véhicule de livraison vers toi. Peek affiche « En livraison ». |
| `Item is ready for a pick up` | T'attend pour retrait. L'avis d'arrivée donne le lieu et le code. |
| `We sent the recipient a text message about the item` | Posti t'a envoyé un SMS. Le colis n'a pas bougé. |
| `The item has been delivered` | Livré, y compris une fois que tu l'as retiré. |
| `Item delivered to the recipient.` | Livré au destinataire. |

`Item accepted from transport` déroute beaucoup de destinataires. Posti explique qu'il s'agit d'une étape de transport : l'acheminement continue et personne n'a encore remis le colis. Les scans effectués à l'étranger portent la mention `ULKOMAILLA` (« à l'étranger » en finnois) dans toutes les langues.

## Consignes Posti, retrait et livraisons manquées

**Avis d'arrivée.** Lorsqu'un colis atteint une consigne (*Parcel Locker*) ou un point de retrait, Posti envoie les détails et le code d'ouverture dans OmaPosti, par SMS et par e-mail. Retire-le sous 5 jours. Certains colis attendent plus longtemps, comme ceux venus de l'étranger (en général 14 jours) ou dont l'avis arrive par courrier (9 jours) : fie-toi donc à la date limite indiquée sur ton avis.

**Plus de temps.** Dans OmaPosti, « Extend pickup time » prolonge le délai jusqu'à 14 jours à partir de la date limite initiale, pour 1,90 € selon la page anglaise de Posti ou 1,90 € par jour selon sa page finnoise.

**À la consigne.** Saisis le code d'ouverture (sur les consignes à clavier : C, le code, puis OK). Toute personne munie du code peut retirer le colis pour toi, sans pièce d'identité. Les envois recommandés et assurés exigent une procuration. Les envois sans code de retrait attendent dans un point Posti : apporte l'identifiant d'envoi et une pièce d'identité.

**Pourquoi il est allé ailleurs.** Un colis trop grand pour le casier, exigeant une remise en main propre ou portant un mauvais numéro de téléphone est dirigé vers un point Posti.

**Livraison à domicile.** Si Posti ne peut pas livrer à ta porte, le colis va au point de retrait le plus proche et tu es prévenu dans OmaPosti, par SMS ou par e-mail. Un *Small parcel* est déposé à la porte ou dans la boîte aux lettres en ton absence. Dans OmaPosti, tu ne peux basculer vers la livraison à domicile que tant que l'expéditeur a encore le colis.

**Modifications.** Seul l'expéditeur peut changer l'adresse. Le service client peut déplacer un colis vers un autre point de retrait contre des frais. Un colis non retiré repart chez l'expéditeur.

## Colis Posti en retard, perdu, abîmé ou en douane

**Aucune mise à jour.** Tant qu'un colis venu de l'étranger n'a pas atteint la Finlande, Posti ne peut pas estimer son arrivée. Voir [pourquoi un suivi ne bouge plus](guide:tracking-not-updating).

**Perdu ou en retard.** Si le suivi ne montre aucune trace du colis, contacte le service client Posti pour ouvrir une demande d'éclaircissement ; expéditeur comme destinataire peuvent la demander. S'il reste introuvable, c'est l'expéditeur qui réclame l'indemnisation, car Posti rembourse celui qui a payé le transport, presque toujours le vendeur. En droit finlandais, la boutique en ligne est responsable du transport et envoie généralement un remplacement. L'autorité finlandaise de la consommation (KKV) considère un colis national comme perdu 14 jours après le délai convenu, ou 28 jours après la prise en charge si aucun délai n'était convenu.

**Livré mais introuvable.** Vérifie la boîte aux lettres pour un *Small parcel*, puis contacte le service client. Voir [livré mais non reçu](guide:delivered-not-received).

**Abîmé.** Signale-le au chauffeur lors de la remise, prends des photos, remplis le formulaire d'objet endommagé de Posti et préviens l'expéditeur.

**Douane.** Un colis venu hors UE (ou de territoires de l'UE hors de son territoire fiscal, comme les îles Åland ou les Canaries) peut nécessiter un dédouanement avant livraison, sur le site des douanes finlandaises ou dans OmaPosti. Il reste 20 jours dans l'entrepôt de Posti, puis repart. Lorsque des taxes sont dues au dédouanement, Posti ajoute des frais de traitement de 3,10 € (3,30 € en point de retrait), payés sur le site officiel de Posti. Depuis le 1er juillet 2026 un droit de douane forfaitaire de 3 € par article s'applique aux colis jusqu'à 150 €, et depuis le 1er novembre 2026 les douanes finlandaises perçoivent aussi, sur les achats en ligne, la redevance de 2 € par article. Pour un achat de moins de 150 € dont la TVA a été prélevée par la boutique (IOSS), l'expéditeur effectue le dédouanement et tu ne paies rien à l'arrivée ; si les données de paiement des droits et frais manquent, Posti renvoie le colis à l'expéditeur. Les tarifs en vigueur figurent sur la [page douane de Posti](https://www.posti.fi/en/receiving/customs-clearance).

> Posti n'envoie jamais de lien pour payer des frais de douane ou de traitement. Un faux message peut même contenir un vrai code de suivi : supprime-le sans ouvrir le lien.

## Service client et numéro de téléphone Posti

Canaux publiés sur posti.fi :

- **Téléphone :** 0100 5577, ou +358 100 5577 depuis l'étranger. L'appel est facturé au tarif mobile ou local. La [page du service client Posti](https://www.posti.fi/en/customer-service) donne les horaires d'ouverture.
- **Chat :** dans l'application OmaPosti.
- **Formulaires :** demande d'indemnisation, signalement d'envoi endommagé et colis non livrable (lorsque Posti n'a pu le livrer ni à toi ni en retour à l'expéditeur).

Posti n'indique aucune adresse e-mail client.

## Suivre un colis Posti avec Peek

[Peek](/) identifie aussitôt comme Posti un numéro commençant par `JJFI`. Un lien de suivi posti.fi (`/tracking/`, `/seuranta/` ou `/uppfoljning/` suivi du numéro, y compris après un code de langue comme `/en/`, ainsi que `/itemtracking/posti/search_by_shipment_id` avec `ShipmentId=`) sélectionne Posti pour n'importe quel numéro. Aucun code postal n'est requis.

Pour un numéro postal finissant par `FI`, Peek interroge Posti en premier, avec [Chronopost](carrier:chronopost), et affiche Posti dès qu'elle le reconnaît. Si Posti ne le connaît pas encore, tu vois « Pas encore trouvé chez … Peek continuera de vérifier après l’ajout. ». Ajouté à un compte Peek, le colis est vérifié toutes les heures pendant 6 heures, puis de moins en moins souvent ; sans compte, les vérifications s'arrêtent après 6 heures sauf si tu actives « Préviens-moi quand il est trouvé », qui maintient une vérification quotidienne jusqu'à 30 jours après l'ajout. Pour un code SSCC du format ci-dessus, Peek interroge Posti en priorité avec Matkahuolto, Swiss Post Cargo, PostNord et NZ Post. Si le chiffre de contrôle est erroné, choisis Posti avec « Changer » (Peek suit plus de 4 500 transporteurs). Un numéro étranger se suit directement chez Posti via « Changer » ou un lien posti.fi ; autrement, Peek le suit d'abord chez la poste d'envoi et, lorsqu'elle indique la Finlande comme destination (ou aucun pays si tu suis depuis la Finlande), interroge aussi Posti et bascule dessus dès qu'un scan au moins aussi récent apparaît, en gardant l'historique antérieur.

En Estonie, en Lettonie et en Lituanie, Posti opère sous le nom SmartPosti : la recherche de transporteurs de Peek trouve Posti sous ce nom (« Aussi appelé SmartPosti »), et les liens de suivi de smartposti.ee ou itella.ee (ainsi que leurs sites .lv et .lt) sélectionnent Posti pour tout code. Peek ne conserve la réponse de Posti que lorsqu'un seul envoi porte exactement le code saisi ; pour un code SmartPosti sans résultat, vérifie directement sur smartposti.ee.

Peek lit Posti en anglais et affiche chaque statut avec la ligne explicative de Posti, la ville de chaque scan, le poids, les dimensions ainsi que le nom et l'adresse du point de retrait, avec chaque scan placé sur une carte. Les horaires s'affichent dans le fuseau de ton appareil ; sur peektracker.com et dans les applis iPhone et Android, l'historique se termine par « Heures dans ton fuseau horaire : » suivi de son nom. Après quatre jours sans nouveau scan, la note de Peek sur le colis propose « Demande de l’aide à Posti », qui renvoie vers le service client Posti. Peek exclut le code de retrait et ton adresse, et n'affiche ni livraison prévue ni expéditeur. Un numéro couvrant plusieurs colis n'est pas suivi et les anciens colis apparaissent comme introuvables.

Tant que tu gardes le colis ouvert à l'écran, ou le jour de la livraison avec les notifications activées, Peek vérifie jusqu'à toutes les 10 minutes, ou toutes les 2 minutes à partir de 6 h heure locale le jour où Posti scanne le colis en livraison tant que son propre suivi répond. Autrement, et après deux jours sans nouvelle (12 heures tant que le colis affiche encore « Étiquette créée » ou « Pris en charge »), Peek vérifie toutes les heures. Quand un autre transporteur prend la relève, Peek suit son rythme. Tu peux activer les notifications sur « Chaque scan », « Étapes importantes » ou « Livraison seulement ».

## Questions sur le suivi Posti

### Puis-je suivre un colis Posti en anglais ?

Oui. Ouvre posti.fi/en/tracking et saisis l'identifiant d'envoi : tous les statuts s'affichent en anglais.

### Combien de temps Posti garde-t-elle un colis en consigne ?

Posti demande de le retirer sous 5 jours ; l'avis indique le dernier jour exact. Dans OmaPosti, tu peux prolonger jusqu'à 14 jours pour 1,90 € (1,90 € par jour selon la page finnoise de Posti).

### Combien de temps Posti met-elle pour livrer ?

Un *Small parcel* arrive en 2 à 5 jours ouvrables après réception par Posti. Pour les autres envois, le suivi de Posti affiche une estimation de livraison.

### Le service client Posti dispose-t-il d'un chat ?

Oui, dans l'application OmaPosti.

:::sources
- [Posti : Picking up an item](https://www.posti.fi/en/receiving/picking-up-an-item) – identifiant JJFI, avis, garde (14 jours étranger, 9 jours par courrier), prolongation, consignes, codes, procuration, retours, téléphone, chat
- [Posti : Lähetyksen noutaminen](https://www.posti.fi/vastaanottaminen/lahetyksen-noutaminen) – en finnois : prolongation à 1,90 €/jour
- [Posti : Track item](https://www.posti.fi/en/tracking) – statuts, lignes explicatives, ULKOMAILLA, langues
- [Posti : Home delivery](https://www.posti.fi/en/receiving/home-delivery) – livraison manquée, Small parcel, délais, livré non reçu
- [Posti : Customs clearance](https://www.posti.fi/en/receiving/customs-clearance) – frais de traitement, douanes finlandaises, redevance de 2 € depuis le 1er novembre 2026, IOSS par l'expéditeur, retours en cas de données manquantes
- [Posti : Ordering from abroad and customs clearance](https://www.posti.fi/en/receiving/customs-clearance/ordering-from-abroard-and-custom-clearance) – 20 jours en entrepôt, OmaPosti, frais de 3,10 €, droit de 3 € et redevance de 2 €
- [Posti : What does Posti compensate for and to whom](https://www.posti.fi/en/customer-service/damage-situations/what-does-posti-compensate-for-and-to-whom) – demande d'éclaircissement, indemnisation, démarche dommages
- [Posti : Claim for compensation](https://www.posti.fi/en/customer-service/damage-situations/claim-for-compensation) – formulaire d'indemnisation
- [Posti : Report a damaged item](https://www.posti.fi/en/customer-service/damage-situations/report-damage) – formulaire de dommages, avertir l'expéditeur
- [Posti : Undeliverable parcel](https://www.posti.fi/en/customer-service/damage-situations/undeliverable-parcel) – formulaire colis non livrable
- [Posti : Security and phishing messages](https://www.posti.fi/en/customer-service/security-and-phishing-messages) – faux messages, pas de liens de paiement
- [Posti : Privacy statement for Item Tracking](https://www.posti.fi/en/customer-service/terms-and-statements/data-protection/privacy-statements/privacy-statement-for-item-tracking) – suivi par code exact, coût d'appel
- [Posti : Changes to parcel storage periods](https://www.posti.fi/en/latest-news-at-posti/changes-to-parcel-storage-periods-to-improve-availability-at-posti-parcel-lockers-11-8-2025) – garde de 5 jours
- [Posti : Customer Service](https://www.posti.fi/en/customer-service) – page du service client
- [SmartPosti : Meist](https://www.smartposti.ee/ariklient/meist) – membre du groupe Posti, SmartPosti en Lettonie et Lituanie
- [SmartPosti : Saadetise jälgimine](https://www.smartposti.ee/saadetise-jalgimine) – page de suivi SmartPosti
- [KKV : Postal and parcel services](https://www.kkv.fi/en/consumer-affairs/telephone-and-internet-subscriptions-postal-and-parcel-services/postal-and-parcel-services/) – quand un colis est réputé perdu
- [European Commission : EU Customs Reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – droit de 3 € et redevance de traitement de l'UE de 2 €
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/posti) – formats, liens SmartPosti/Posti et données lues par Peek
:::
