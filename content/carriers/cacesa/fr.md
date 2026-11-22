---
title: Suivi CACESA : douane, statuts et transporteur final
description: Suis un colis CACESA vers l’Espagne ou le Portugal : quel numéro utiliser, que veulent dire les statuts, droits de douane, livraison et contact CACESA.
slug: suivi-cacesa
published: 2026-11-22
updated: 2026-11-22
---

CACESA (Compañía Auxiliar al Cargo Express, S.A.) est un représentant en douane basé à Madrid qui dédouane des colis d’achats en ligne expédiés de Chine vers l’Espagne et le Portugal. L’entreprise réceptionne les envois dans plusieurs aéroports, effectue les formalités douanières puis remet les colis au transporteur chargé de la livraison finale. CACESA n’effectue aucune livraison elle-même. Tu peux suivre un colis sur la [page de suivi de CACESA](https://www.cacesa.com/en/tracking) avec le numéro d’envoi communiqué par la boutique, ou coller ce numéro dans le champ ci-dessus.

## Numéro de suivi CACESA : lequel utiliser

CACESA ne publie aucun format de numéro qui lui soit propre. Son outil demande le « Shipment number », c’est-à-dire la référence transmise avec le colis par le vendeur ou par le consolidateur chinois qui regroupe les commandes. Tu la trouves dans l’e-mail d’expédition ou sur la page de ta commande : CACESA n’a pas de contact direct avec le site marchand sur lequel tu as acheté.

Formats fréquemment observés dans le suivi CACESA (chiffres fictifs) :

| Format | Ce que c’est |
| --- | --- |
| `CNES48291736548` | `CNES` suivi de 11 chiffres. |
| `CNESP48291736548291736548` | `CNESP` suivi de 20 chiffres, pour un envoi vers l’Espagne. |
| `CNPRT48291736548291736548` | `CNPRT` suivi de 20 chiffres, pour un envoi vers le Portugal. |

Le catalogue de Peek n’associe aucun de ces formats à CACESA. Les numéros `CNESP` et `CNPRT` sont aussi ceux d’[Ecoscooting](carrier:ecoscooting), le service de livraison de Cainiao en Espagne et au Portugal, tandis qu’une référence `LP` suivie de 14 chiffres correspond à l’identifiant logistique d’AliExpress suivi via [Cainiao](carrier:cainiao). Si le suivi de CACESA ne reconnaît pas ton numéro, essaie le transporteur indiqué dans l’e-mail du vendeur. D’autres repères figurent dans [les formats de numéros de suivi](guide:tracking-number-formats).

Tu peux saisir plusieurs numéros à la fois dans le suivi de CACESA. S’il répond introuvable, CACESA recommande de vérifier le code saisi et précise qu’une commande récente peut ne pas encore figurer dans son système. Les envois plus anciens finissent aussi par ne plus apparaître dans le suivi.

Une lettre de l’administration fiscale espagnole (AEAT) au sujet d’un colis retenu mentionne une référence « HB ». Elle sert à communiquer avec CACESA, pas à suivre le colis.

## Statuts de suivi CACESA expliqués

Le suivi affiche des libellés en espagnol sur [la page espagnole](https://www.cacesa.com/es/seguimiento) et les libellés anglais ci-dessous sur la page anglaise. Chaque étape comporte une date et une heure, classées sous une rubrique : Data registered, Warehouse ou Custom Clearance.

| Statut CACESA | Ce que ça veut dire pour toi |
| --- | --- |
| `Electronic notification of shipment received` | CACESA a reçu les données du colis, mais le colis lui-même n’est pas encore arrivé. |
| `Arrival Notification` | L’arrivée du colis chez CACESA a été annoncée. |
| `Inbound warehouse` | Le colis se trouve dans l’entrepôt de CACESA. |
| `Start Custom clearance Process` | Le dédouanement a commencé. |
| `Customs cleared-Green channel` | La douane a libéré le colis : il peut partir en livraison. |
| `Parcel is going out from Cacesa facilities - More delivery information check with transport company.` | CACESA a remis le colis au transporteur qui assure la livraison. |
| `Shipment not found` | CACESA ne connaît pas ce numéro ou ne l’affiche plus. |

Le circuit vert (green channel) correspond à la libération normale. La [FAQ de CACESA](https://www.cacesa.com/en/faqs) cite deux autres circuits douaniers : orange (contrôle documentaire) et rouge (contrôle physique de la marchandise et des documents). Si la douane demande des justificatifs ou un paiement, CACESA te contacte par e-mail ou SMS.

La ligne annonçant la sortie des installations de CACESA est généralement la dernière qu’elle publie. Le colis est alors entre les mains du transporteur final, vers lequel CACESA renvoie pour les détails de livraison.

## Livraison CACESA, absences et retours

CACESA le rappelle sans détour : ce n’est pas elle qui livre, mais l’entreprise de transport choisie par le vendeur en ligne. CACESA n’indique pas le nom de ce transporteur ; il faut donc regarder dans l’e-mail de la boutique ou sur le suivi de commande.

Le jour de livraison, les points relais, les consignes et une éventuelle livraison le samedi dépendent entièrement de ce transporteur. CACESA n’en donne aucune règle. Une fois le nombre de tentatives prévu par le transporteur écoulé, celui-ci renvoie le colis au vendeur (CACESA ne précise pas combien de tentatives sont effectuées).

Si ton colis a été retenu en douane et que CACESA t’a demandé des documents, elle indique un délai de 3 à 4 jours entre la mainlevée et la remise au distributeur, auquel s’ajoute le temps d’acheminement vers ton code postal.

CACESA ne traite pas les retours des particuliers : organise un retour directement avec la boutique ou le transporteur qui t’a livré.

## Douane CACESA : paiement, documents et problèmes

**E-mail ou SMS de CACESA.** Un message indiquant qu’un colis attend son dédouanement signifie que la douane exige des informations supplémentaires ou que des taxes restent à payer. CACESA demande d’envoyer les pièces suivantes à atc@cacesa.com :

- la facture ou preuve d’achat (par exemple une capture d’écran de la commande montrant le numéro du colis, l’article acheté et le prix, accompagnée d’une photo du produit) ;
- la preuve de paiement (capture du débit par carte ou reçu) ;
- une mention précisant que tu es travailleur indépendant (autónomo), si c’est le cas, afin de calculer la surtaxe d’équivalence espagnole (0,5 % sur la TVA à 4 %, 1,4 % sur la TVA à 10 % ou 5,2 % sur la TVA à 21 %).

Tu peux aussi effectuer toi-même l’autodédouanement sur le portail de l’Agencia Tributaria (AEAT) ; CACESA demande dans ce cas de l’en informer à atc@cacesa.com.

**Payer les taxes.** Sur cacesa.com, ouvre « Tax payment », saisis le numéro d’envoi et le montant de l’achat en euros pour afficher les droits, taxes et le total. Le règlement se fait par carte bancaire (Redsys), PayPal ou Bizum. Comme CACESA règle la dette fiscale en ton nom auprès de l’AEAT, il n’y a pas de droit de rétractation ; en cas d’erreur ou de trop-perçu, le service client rembourse la différence. Un colis dont les taxes restent impayées est renvoyé à son origine.

**Tu ne veux pas payer ?** Envoie le formulaire d’autorisation d’abandon de marchandises de CACESA à atc@cacesa.com afin qu’elle engage la procédure avec l’AEAT et que le colis reparte à l’expéditeur.

**Courrier de l’AEAT.** En cas d’avis « Decomiso - Depósito temporal » (avec références HB et AEAT) ou « Validación de datos », scanne le courrier et envoie-le à atc@cacesa.com. CACESA te dira si le colis peut encore être récupéré ou s’il a été détruit.

Les règles applicables sont détaillées dans [colis bloqué à la douane](guide:customs). Depuis le 1er juillet 2026, l’Union européenne applique un droit de douane forfaitaire de 3 € par ligne de marchandise déclarée sur les achats en ligne jusqu’à 150 € en provenance de pays tiers : la FAQ de CACESA précise que des articles identiques partageant le même code SH sur une seule ligne ne comptent qu’une fois, et que si la TVA et les droits ont déjà été réglés lors de l’achat, rien n’est à payer à la livraison. Depuis le 1er novembre 2026 s’ajoute aussi la redevance européenne de traitement de 2 € par article ; le site de CACESA n’indique pas encore comment elle la répercute sur le destinataire.

**Retard, perte ou dommage.** Pour toute réclamation autre que le paiement des taxes douanières, CACESA renvoie vers le vendeur, avec lequel le contrat de livraison a été conclu. Si le colis a été dédouané sans avoir été retenu et sans contact de CACESA mais n’arrive pas, contacte le vendeur. D’autres pistes sont regroupées dans [suivi qui ne se met pas à jour](guide:tracking-not-updating).

> CACESA avertit que ni elle ni aucune personne autorisée à livrer ton colis ne te demandera de payer de nouveau la TVA, des droits de douane ou d’autres frais déjà acquittés lors de l’achat. Ne paie pas et vérifie auprès du vendeur ou de CACESA.

## Contacter CACESA

CACESA ne dispose ni de site en portugais ni de bureau au Portugal ; les mêmes coordonnées s’appliquent pour l’Espagne et le Portugal :

- **E-mail :** atc@cacesa.com pour les colis retenus, justificatifs, paiements et abandons (back-office : postalcustomers@cacesa.com).
- **Téléphone :** +34 91 746 1860 (siège social, Calle Golf 23, 28042 Madrid) ou +34 91 333 54 50 (bureau de fret aérien de Madrid-Barajas et hub de Madrid). CACESA n’indique ni horaires ni ligne dédiée aux destinataires.
- **Réclamations** sur le service de CACESA : via le [formulaire de réclamation](https://www.cacesa.com/en/legal-complaints), avec la référence d’envoi et le motif.
- **Où est le colis et quand arrive-t-il :** auprès du vendeur ou du transporteur final.

## Suivre un colis CACESA avec Peek

[Peek](/) suit plus de 4 500 transporteurs, mais aucun format de numéro n’appartient en propre à CACESA : Peek ne la sélectionne donc jamais automatiquement à partir du numéro. Ajoute ton numéro puis choisis CACESA avec « Changer », ou colle directement un lien de suivi cacesa.com (`/en/tracking` ou `/es/seguimiento` avec `busqueda=`), ce qui attribue immédiatement CACESA à n’importe quel numéro. Les codes `CNESP` et `CNPRT` détectent automatiquement [Ecoscooting](carrier:ecoscooting).

Peek affiche les libellés anglais fournis par CACESA, en interprétant les horaires sur l’heure de Madrid et en les affichant dans ton fuseau horaire (l’historique se termine par « Heures dans ton fuseau horaire : » suivi de son nom). Un événement de livraison apparaît comme livré, `Start Custom clearance Process` comme une étape douanière et le passage en circuit vert comme la poursuite de l’acheminement ; Peek n’affiche toutefois aucun montant de droits et ne permet pas de les payer. Comme CACESA ne transmet aucun lieu, il n’y a pas de point à afficher sur la carte, ni de date de livraison estimée, de poids, d’expéditeur ou de destinataire.

CACESA ne nommant pas le transporteur final, le colis poursuit sa route sous le même numéro. Peek ne peut prendre le relais que lorsque ce numéro correspond clairement au format d’un autre transporteur : [Ecoscooting](carrier:ecoscooting) pour `CNESP` et `CNPRT`, [Correos](carrier:correos-spain) pour un numéro postal se terminant par `ES`, [CTT](carrier:ctt) pour un numéro finissant par `PT`, ou [CTT Express](carrier:ctt-express) pour ses propres références. Les numéros `CNES` et `LP` ne sont pas transférés.

Tant que tu gardes l’écran ouvert dans Peek — ou le jour de la livraison si les notifications sont activées — Peek interroge CACESA directement jusqu’à toutes les 10 minutes (toutes les 15 minutes via les services de suivi tiers). Sinon, ainsi qu’après deux jours sans nouveauté (ou 12 heures tant que le colis reste sur « Étiquette créée » ou « Pris en charge »), Peek vérifie toutes les heures. Tu peux choisir d’être prévenu pour « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Après quatre jours sans scan, Peek affiche « Aucune mise à jour depuis quatre jours » et « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger CACESA. », accompagné d’un lien « Demande de l’aide à CACESA » vers sa page FAQ.

## Questions sur le suivi CACESA

### Qu’est-ce que CACESA Postal ?

CACESA, parfois appelée Cacesa Postal ou CACESA Logistics sur des agrégateurs, est l’agent en douane madrilène qui dédouane les commandes en ligne de Chine vers l’Espagne et le Portugal. Fondée en 1987 par Iberia, elle a été rachetée en 2025 par le groupe postal portugais CTT.

### Qui livre un colis passé par CACESA ?

La société de transport choisie par le vendeur, jamais CACESA. Son nom figure dans l’e-mail d’expédition ou la page de commande de la boutique ; CACESA ne le communique pas.

### CACESA gère-t-elle les douanes d’AliExpress ?

Les pages de CACESA ne citent aucune boutique précise. L’entreprise explique recevoir des colis auprès de sociétés qui regroupent des commandes en Chine et les dédouaner pour les destinataires, sans lien direct avec les sites marchands.

### Combien de temps met un colis CACESA après la douane ?

Pour un colis qui a été retenu, CACESA indique 3 à 4 jours entre la libération douanière et la remise au transporteur, plus le délai de livraison vers ton code postal. Elle ne communique aucun délai pour les colis passés directement en circuit vert.

:::sources
- [CACESA : Shipment tracking](https://www.cacesa.com/en/tracking) – suivi, numéro d’envoi, autodédouanement, renvoi vers le transporteur final
- [CACESA : Seguimiento de envíos](https://www.cacesa.com/es/seguimiento) – page de suivi en espagnol
- [CACESA : Faqs](https://www.cacesa.com/en/faqs) – rôle, absence de livraison, circuits douaniers, documents, 3 à 4 jours, retours, courriers AEAT, abandon, atc@cacesa.com
- [CACESA : Facilities](https://www.cacesa.com/en/offices) – numéros de téléphone, bureaux en Espagne et à l’étranger, aucun bureau au Portugal
- [CACESA : Legal Warning](https://www.cacesa.com/en/legal-warning) – paiement des taxes (Redsys, PayPal, Bizum), absence de rétractation, retour si impayé, réclamations vers le vendeur
- [CACESA : Complaints](https://www.cacesa.com/en/legal-complaints) – formulaire de réclamation
- [CACESA : Quiénes somos](https://www.cacesa.com/es/quienes-somos) – raison sociale, fondation en 1987 par Iberia
- [CACESA : textes de l’application web](https://www.cacesa.com/main.40b62b43302d800a.js) – libellés du suivi, paiement des taxes, FAQ sur le droit forfaitaire UE de 3 €, avertissement anti-fraude
- [ECO : résultats des CTT à septembre 2025](https://eco.sapo.pt/2025/10/30/lucro-dos-ctt-sobe-184-para-328-milhoes-ate-setembro-com-integracao-da-espanhola-cacesa/) – intégration de CACESA au groupe CTT en 2025
- [Commission européenne : réforme douanière de l’UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – droit temporaire de 3 € (1er juillet 2026) et redevance de traitement de 2 € (1er novembre 2026)
- [Universal Parcel Scraper : adaptateur CACESA](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/cacesa) – ce que lit Peek et règles de détection
:::
