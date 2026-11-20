---
title: Suivi colis Evri : numéro, statuts, douane et livraison
description: Suivre un colis Evri depuis la France, la Belgique ou la Suisse : code à 16 caractères, statuts GlobalEco et Royaume-Uni, douane, relais et contact.
slug: evri-suivi
published: 2026-11-17
updated: 2026-11-20
---

Evri est le transporteur britannique de colis connu sous le nom de Hermes UK jusqu'en mars 2022. Si tu commandes sur une boutique britannique (ASOS, Boohoo, Gymshark, Lookfantastic, eBay UK ou Vinted), ton envoi part souvent avec Evri puis passe à un transporteur partenaire dans ton pays. Les colis internationaux se suivent sur le portail [GlobalEco d'Evri International](https://globaleco.app/track/), tandis que les livraisons internes au Royaume-Uni se suivent sur [evri.com](https://www.evri.com/track-a-parcel) avec un code à 16 caractères ou les 8 chiffres d'un avis de passage (*calling card*). Tu peux aussi coller directement ton code-barres à 16 caractères ou ton lien `evri.com` dans le champ ci-dessus.

## Numéro de suivi Evri : formats et où le trouver

Evri parle tantôt d'un code à 16 caractères, tantôt d'un numéro à 16 chiffres, même quand ses propres exemples contiennent des lettres. Tu le trouves dans l'e-mail d'expédition de la boutique ou dans les messages d'Evri (SMS envoyés par « Evri » et e-mails).

| Format (exemple inventé) | Ce que c'est |
| --- | --- |
| `H00AAC0000000008` | Code-barres Evri à 16 caractères (`H` ou parfois `T`, quatre lettres ou chiffres, `C` en 6e position, puis 10 chiffres avec clé de contrôle), courant sur Evri International (comme `H04G0C…` via Post Office ou `H0595C…` sur evri.com). |
| `H00AAA0000000006` | Code-barres Evri à 16 caractères avec `A`, `D` ou un autre caractère en 6e position (par ex. `H00AAB0000000007`), utilisé au Royaume-Uni et partagé entre Evri UK et Evri International. |
| `C00HHA0000000000` | `C00HHA` suivi de 10 chiffres : numéro Evri UK présent notamment dans certains liens de suivi `evri.com`. |
| `1000000000000007` | 16 chiffres avec clé de contrôle GS1 : utilisé par Evri UK mais aussi par DHL eCommerce, Correos Express, [Postes Canada](carrier:canada-post) ou TNT. |
| `12340000` | Numéro d'avis de passage britannique à 8 chiffres (*calling card*), utilisable uniquement sur evri.com. |

Un `H` suivi de 15 chiffres (`H000000000000008`) correspond aussi au format de Hermes en Allemagne : fie-toi au transporteur annoncé par le vendeur. Sur le portail international [globaleco.app/track/](https://globaleco.app/track/), une fois le colis remis au partenaire chargé du dernier kilomètre, le champ `Carrier` nomme ce réseau et `Carrier Tracking` affiche son numéro local (par exemple lorsque [Paack](carrier:paack) assure la livraison).

**Achat sur eBay UK ?** Lorsqu'un vendeur passe par le *Global Shipping Programme* (GSP) d'eBay, Evri achemine d'abord le colis à l'intérieur du Royaume-Uni jusqu'au centre d'expédition britannique d'eBay sous un numéro Evri UK, avant que le programme d'eBay ne prenne le relais vers l'étranger. Introuvable ? Consulte [où trouver son numéro de suivi](guide:find-tracking-number).

## Ce que veulent dire les statuts de suivi Evri

Pour un envoi du Royaume-Uni vers l'Europe continentale, le portail GlobalEco d'Evri International affiche ses étapes en anglais :

| GlobalEco affiche | Ce que ça veut dire pour toi |
| --- | --- |
| `Created` | Étiquette enregistrée : Evri n'a pas encore le colis en main. |
| `Received at Evri`, `Accepted` | Evri a pris le colis en charge au Royaume-Uni. |
| `Consolidated`, `Cross Docked`, `Processed at Export Gateway` | Le colis est trié et préparé au centre d'export britannique d'Evri. |
| `Departed Origin Country`, `In transit` | Le colis a quitté le Royaume-Uni ou circule vers ton pays. |
| `Arrived In Country` | Arrivé dans le pays de destination. |
| `In customs`, `Customs cleared` | Contrôle douanier en cours, puis dédouanement terminé (voir [colis bloqué en douane](guide:customs)). |
| `Handed to Partner Carrier`, `Received by carrier`, `At the local depot` | Remis au transporteur partenaire local et trié dans son agence ; regarde `Carrier` et `Carrier Tracking` sur GlobalEco pour suivre la fin du parcours. |
| `Scheduled For Delivery` | Observé sur des colis livrés par Paack après l'arrivée dans le pays : la date ou le créneau a été reprogrammé pendant que le colis attend au dépôt. |
| `Out for delivery` | En tournée avec le livreur aujourd'hui. |
| `Delivery Attempted` | Le livreur n'a pas pu remettre le colis lors de son passage. |
| `Delivery awaiting collection` | Le colis t'attend en point de retrait ou au bureau de poste. |
| `Delivered` | Colis livré ou retiré. |
| `Delayed`, `Delivery Exception`, `Carrier label error` | Retard, incident de livraison ou anomalie d'étiquette : vérifie le suivi du partenaire local ou préviens le vendeur. |

Si tu suis un colis interne au Royaume-Uni sur `evri.com`, Evri emploie ses propres formulations :

| Evri affiche (Royaume-Uni) | Ce que ça veut dire pour toi |
| --- | --- |
| `We're expecting it` | L'expéditeur n'a pas encore remis le colis à Evri. |
| `on its way` (livraison ParcelShop) | En route à travers les dépôts Evri vers le ParcelShop britannique choisi ; tu reçois un SMS ou un e-mail dès qu'il est disponible. |
| `Evri gateway` | Colis arrivé de l'étranger au Royaume-Uni, retenu dans un centre Evri le temps du dédouanement britannique avant de rejoindre un hub. |
| `We're sorry we haven't been able to deliver today. We'll update your tracking when it's on the way on the next working day` | Tournée retardée sans passage ce jour-là : le colis repart le jour ouvré suivant. |
| `On its way back to the retailer` | Dernier point de suivi d'un retour : Evri a rendu le colis au marchand et n'envoie plus d'autre mise à jour. |

Si le suivi `evri.com` te demande de contacter Evri, c'est qu'il manque une information pour livrer : ouvre le chat sur `evri.com`.

## Livraison en France, en Belgique et en Suisse, douane et retraits au Royaume-Uni

**Vers la France, la Belgique et la Suisse.** Sur sa FAQ générale, Evri annonce 3 à 7 jours ouvrés pour son service international, mais ses pages par pays précisent les délais moyens depuis le Royaume-Uni : 6 à 9 jours vers la France en envoi standard (4 à 6 jours en service prioritaire international), 6 à 9 jours vers la Belgique et 7 à 10 jours vers la Suisse, hors jours fériés et délais douaniers. Les conditions générales d'Evri prévoient au moins une tentative de livraison pour les services internationaux ; la suite (nouveau passage ou dépôt en point de retrait) dépend du transporteur partenaire local qui prend le relais dans ton pays.

**Douane et TVA depuis le Royaume-Uni.** Le Royaume-Uni ne faisant pas partie de l'union douanière de l'UE ni du territoire douanier suisse, tout envoi importé passe en douane :

- **France et Belgique (UE) :** Evri prévient qu'en envoi standard le destinataire peut devoir régler taxes et droits avant le départ du Royaume-Uni ou à l'arrivée via un lien de paiement. Depuis le 1er juillet 2026, l'exonération de droits jusqu'à 150 € a pris fin : un droit de douane de 3 € par type de produit s'applique aux envois jusqu'à 150 €, quel que soit le régime de TVA (IOSS ou non), et depuis le 1er novembre 2026 s'ajoutent des frais de traitement de l'UE de 2 € par type de produit, quelle que soit la valeur. Evri compte l'un et l'autre par groupe douanier (articles partageant les 6 premiers chiffres du code SH : 2 t-shirts = 1 groupe, 1 livre + 1 t-shirt = 2 groupes, jamais par unité).
- **Suisse :** l'OFDF applique 8,1 % de TVA (2,6 % au taux réduit, par ex. livres ou alimentation), frais d'envoi inclus ; les montants d'impôt ou de droits inférieurs à 5 CHF ne sont pas perçus (base de calcul de 62 CHF à 8,1 % ou 193 CHF à 2,6 %), mais le transporteur perçoit des frais de dédouanement pour son travail. Détails dans [frais de douane et colis bloqués](guide:customs).

**Au Royaume-Uni (ParcelShops, Lockers et calling cards).** Le livreur fixe son créneau le matin (1 heure selon l'accueil d'Evri, 2 heures selon l'aide). Absent ? Il dépose le colis en lieu sûr (*safe place*) ou boîte aux lettres, sinon laisse une *calling card* à 8 chiffres et tente jusqu'à trois passages (puis retour au dépôt selon le guide *safe place*, à l'expéditeur selon les conditions générales, ou au ParcelShop le plus proche selon `evri.com/send`). Sur `evri.com`, le code postal permet de rediriger vers un voisin, lieu sûr, ParcelShop ou Locker si l'expéditeur l'autorise et sans signature requise ; impossible de changer le jour ou de retirer un colis confié au livreur, et seul l'expéditeur modifie l'adresse. Un ParcelShop (commerce ou Post Office) garde le colis 10 jours (pièce d'identité ou PIN), un Locker 24 h/24 le garde 3 jours (code ou code-barres reçu par e-mail ; bouton « help » sur l'écran).

## Colis Evri bloqué, en retard, perdu ou abîmé

- **Suivi figé :** sur `We're expecting it` ou `Created`, Evri n'a pas encore le colis : vois la boutique. Pour un autre statut immobile 24 heures au Royaume-Uni (5 jours en Standard côté expéditeur), lance un chat. Voir [suivi qui ne bouge plus](guide:tracking-not-updating).
- **Rien après 7 jours :** préviens le vendeur, titulaire du contrat. Dans l'UE, il doit livrer sous 30 jours (sauf accord différent) et répond des dommages de l'expédition à la réception.
- **Livré mais absent :** interroge ton foyer et tes voisins, ouvre un chat au Royaume-Uni ou vérifie le transporteur local en Europe et contacte la boutique. Voir [livré mais non reçu](guide:delivered-not-received).
- **Perdu ou abîmé :** Evri ne rembourse pas le destinataire ; [seul l'expéditeur réclame](guide:damaged-parcel) (délai recommandé par Evri : 28 jours au Royaume-Uni, 45 jours à l'international).

## Contacter Evri et repérer les faux SMS

Evri ne liste que des canaux britanniques en anglais (relus le 17 novembre 2026), sans ligne locale en France, Belgique ou Suisse :

- **Chat 24 h/24** sur [evri.com/contact-us](https://www.evri.com/contact-us) via l'assistant Ezra (réponse sous 24 heures) avec le numéro de suivi, le reçu ParcelShop (`P` + 7 chiffres) ou la *calling card* à 8 chiffres (saisir `0` si Ezra demande un code postal pour un pays qui n'en a pas).
- **Téléphone (Royaume-Uni) :** `0330 808 5456`, serveur 24 h/24 et conseillers lun.–ven. 8 h–16 h, sam. 8 h–14 h (heure britannique). Saisir au clavier les 8 derniers chiffres du suivi, les 7 chiffres après `P` du reçu ParcelShop ou la *calling card*. Tarif `01`/`02` au Royaume-Uni ; tarif international depuis l'étranger.
- **Tell us straight :** après un premier passage par le service client, le bouton « Email us » écrit à `noreply.priority@evri.com` pour l'équipe senior (accusé sous une heure ouvrée britannique et suivi quotidien).

> E-mails officiels : `@evri.com`, `@hermes-europe.co.uk`, `@myhermes.co.uk`, `@custhelp.com`, `@globalco.uk`, `@globaleco.app` et leurs sous-domaines (ex. `@international.evri.com`). Les SMS britanniques viennent uniquement d'« Evri » (jamais d'un mobile), ne demandent ni paiement ni application, et pointent vers `evri.com` ou `evri.link` (imitables malgré tout). En cas de doute, ne réponds pas et vérifie sur GlobalEco ou `evri.com` ; au Royaume-Uni, les SMS suspects se transfèrent au `7726`. Voir [SMS d'arnaque au colis](guide:delivery-scam-texts).

## Suivre un colis Evri avec Peek

[Peek](/) interroge **Evri International** et **Evri UK** pour tout code à 16 caractères (`H` ou `T`) à clé valide : il classe Evri International en tête si le 6e caractère est `C`, et Evri UK si c'est `A` ou `D`, sans trancher tant que l'un des deux ne confirme pas le colis (si les deux le connaissent, Peek retient Evri International pour un `C`, sinon Evri UK). Pour `H` + 15 chiffres à clé Evri valide, Peek interroge aussi Hermes en Allemagne (seul retenu si la clé Evri échoue). Si aucun service Evri ne connaît encore le code, touche « Changer » et choisis **Evri International** ou **Evri UK** sous « Correspond à ce numéro », ou colle un lien `evri.com/track/parcel/…/details` (lu d'emblée comme Evri UK). Sur 16 chiffres, Peek teste Evri UK avec d'autres transporteurs quand la clé GS1 est juste, et seulement les autres sinon ; `C00HHA` + 10 chiffres va seul à Evri UK. Sans code postal ; les *calling cards* à 8 chiffres ne sont pas suivies.

Sur **Evri International**, Peek montre l'historique GlobalEco (libellés majoritairement en anglais ; « En transit », « En livraison » ou « Livré » en français), les lieux, le poids et toute date `Estimated delivery date is: …` jusqu'à la livraison ou au retour, sans expéditeur ni destination. Les heures GlobalEco n'ayant pas de fuseau, Peek garde ces heures locales telles quelles.

Sur **Evri UK**, tu vois le statut et l'historique, sans lieux sur la carte. Livraison coursier et retrait ParcelShop/Locker s'affichent « Livré » sans lieu de dépôt ; l'attente au point affiche « Prêt à être retiré », et le reste garde la phrase anglaise d'Evri. Nom, adresse, photo et codes de retrait sont exclus. Sur peektracker.com, iPhone et Android, les scans avec fuseau finissent par « Heures dans ton fuseau horaire : … », avec alertes « Chaque scan », « Étapes importantes » ou « Livraison seulement ».

## Questions fréquentes sur le suivi Evri

### Evri et Hermes, est-ce la même entreprise ?

Oui au Royaume-Uni : Evri est l'ancien Hermes UK, renommé en mars 2022. Hermes en Allemagne reste une société distincte ; sur `H` suivi de 15 chiffres, vérifie le transporteur indiqué par la boutique.

### Evri fait-il partie de DHL ou de Royal Mail ?

D'aucun des deux. Après le feu vert de l'autorité britannique de la concurrence en septembre 2025, Evri (détenu par des fonds gérés par Apollo) a finalisé le 1er octobre 2025 sa fusion avec DHL eCommerce UK au sein d'Evri Group, DHL Group prenant une part minoritaire. En 2026, DHL eCommerce UK devient **Evri Premium – a network of DHL**, réseau séparé pour colis volumineux ou de valeur ; les colis [DHL](carrier:dhl) classiques ne se suivent pas sur le portail standard d'Evri.

### Combien de temps met un colis Evri depuis le Royaume-Uni ?

La FAQ internationale générale annonce 3 à 7 jours ouvrés, tandis que les pages pays indiquent 6 à 9 jours vers la France en standard (4 à 6 en prioritaire), 6 à 9 jours vers la Belgique et 7 à 10 jours vers la Suisse, hors douane et jours fériés. Au Royaume-Uni, l'aide annonce 1 à 2 jours en *Next Day* et 3 à 5 jours en *Standard* (1 à 3 jours ailleurs sur le site).

### Un e-mail de noreply.priority@evri.com est-il authentique ?

Evri donne cette adresse sous *Tell us straight* (« Email us » sur sa page de contact) pour saisir l'équipe senior après passage par le service client, avec accusé sous une heure ouvrée britannique. Ses pages sécurité ne listant que des domaines comme `@evri.com`, sans cette adresse précise, et une adresse pouvant être usurpée ou écorchée, vérifie chaque lettre et, en cas de doute, passe directement par `evri.com` sans répondre.

### Peut-on suivre un colis Evri uniquement avec son code postal ?

Non. Sur GlobalEco, `evri.com` et Peek, il faut d'abord le code à 16 caractères (ou les 8 chiffres d'une *calling card* sur `evri.com`) ; le code postal ne sert ensuite sur `evri.com` qu'à voir plus d'options ou rediriger au Royaume-Uni.

:::sources
- [Evri : Track a parcel](https://www.evri.com/track-a-parcel) – 16 caractères, 8 chiffres, lien international
- [GlobalEco : Tracking](https://globaleco.app/track/) – suivi Evri International
- [Evri : International services](https://www.evri.com/our-services/international-courier-services) – 200+ pays
- [Evri : Europe](https://www.evri.com/our-services/international-courier-services/europe) – IOSS et douane
- [Evri : France](https://www.evri.com/our-services/international-courier-services/france) – 6–9 / 4–6 jours, taxes
- [Evri : Belgium](https://www.evri.com/our-services/international-courier-services/belgium) – 6–9 jours
- [Evri : Switzerland](https://www.evri.com/our-services/international-courier-services/switzerland) – 7–10 jours, lien douane
- [Evri : EU Customs Update Nov 2026](https://www.evri.com/news/eu-customs-update-november-2026) – 3 € et 2 € par groupe SH
- [Evri : International deliveries FAQ](https://www.evri.com/faqs/sending-a-parcel/international-deliveries) – 3–7 jours ouvrés, eBay GSP
- [Evri : International contact FAQ](https://www.evri.com/faqs/sending-a-parcel/how-to-contact-customer-services-for-international-parcels) – `H04G0C`, `H0595C`, Ezra, code `0`
- [Evri : Evri gateway](https://www.evri.com/faqs/receiving-a-parcel/what-is-an-evri-gateway) – douane UK
- [Evri : How do I track my parcel?](https://www.evri.com/faqs/receiving-a-parcel/how-do-i-track-my-parcel) – « on its way », calling card, Lockers
- [Evri : Tracking status](https://www.evri.com/help-and-support/help-centre/receiving/awaiting-delivery/tracking-status) – « We're expecting it », 24 h
- [Evri : Sender status](https://www.evri.com/help-and-support/help-centre/sending/delivery-problem/status-has-not-changed) – 24 h / 5 jours
- [Evri : Update on my delivery](https://www.evri.com/help-and-support/help-centre/receiving/awaiting-delivery/update-delivery) – délais UK, créneau 2 h
- [Evri : Contact you](https://www.evri.com/help-and-support/help-centre/receiving/awaiting-delivery/contact-you) – infos manquantes
- [Evri : Change delivery](https://www.evri.com/help-and-support/help-centre/receiving/awaiting-delivery/change-delivery) – adresse et date
- [Evri : Can't find parcel](https://www.evri.com/help-and-support/help-centre/receiving/not-find-parcel/all-correct) – livré introuvable
- [Evri : Haven't received parcel](https://www.evri.com/faqs/receiving-a-parcel/what-can-i-do-if-i-havent-received-my-parcel) – 7 jours
- [Evri : Return received](https://www.evri.com/faqs/returning-a-parcel/how-will-i-know-the-retailer-has-received-my-return) – dernier scan retour
- [Evri : Home](https://www.evri.com/) – créneau 1 h, 3 essais
- [Evri : Safe place](https://www.evri.com/guides/safe-place) – lieu sûr, dépôt
- [Evri : Send](https://www.evri.com/send) – ParcelShop après 3 essais
- [Evri : Diversions](https://www.evri.com/diversions) – redirections
- [Evri : Expected time](https://www.evri.com/faqs/receiving-a-parcel/expected-delivery-time) – matin, signature
- [Evri : Collect parcel](https://www.evri.com/faqs/receiving-a-parcel/can-i-collect-my-parcel) – pas de retrait en tournée
- [Evri : ParcelShop delivery](https://www.evri.com/our-services/parcelshop-delivery) – 10 jours, ID, Post Office
- [Evri : Lockers](https://www.evri.com/parcelshops/lockers) – 24 h/24, 3 jours, « help »
- [Evri : Terms](https://www.evri.com/terms-and-conditions) – essais UK/monde, 28/45 jours
- [Evri : Claims](https://www.evri.com/faqs/account/how-do-i-submit-a-claim-for-a-lost-or-damaged-parcel) – réclamation expéditeur
- [Evri : Contact us](https://www.evri.com/contact-us) – chat, 0330 808 5456, Tell us straight
- [GOV.UK : Call charges](https://www.gov.uk/call-charges) – numéros 03
- [Evri : Cyber security](https://www.evri.com/cyber-security) – domaines, SMS, 7726
- [Evri : How will Evri contact me?](https://www.evri.com/faqs/receiving-a-parcel/how-will-evri-contact-me) – `evri.link`
- [Evri : Genuine message?](https://www.evri.com/faqs/receiving-a-parcel/is-this-evri-text-email-genuine) – adresses imitées
- [Evri : About us](https://www.evri.com/about-us) – ex-Hermes UK et boutiques clientes
- [Evri : Evri Group](https://www.evri.com/press/introducing-the-evri-group-the-uks-premier-parcel-delivery-company) – fusion 1er oct. 2025
- [Evri : Evri Premium](https://www.evri.com/our-services/evri-premium-dhl) – réseau DHL séparé
- [GOV.UK : Merger inquiry](https://www.gov.uk/cma-cases/evri-slash-dhl-merger-inquiry) – Apollo, part DHL, CMA
- [Commission européenne : EU Customs Reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – fin du seuil 150 €, droit 3 € et frais UE
- [OFDF : Réception de lettres et de paquets](https://www.bazg.admin.ch/fr/reception-de-lettres-et-paquets) – TVA 8,1 % / 2,6 % et seuil de 5 CHF
- [OFDF : Frais de dédouanement](https://www.bazg.admin.ch/fr/frais-dedouanement-et-suivi-des-colis) – frais de dédouanement du transporteur
- [Your Europe : Livraison](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_fr.htm) – 30 jours, responsabilité
- [Universal Parcel Scraper : Evri International](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/evri) – `C` en 6e place et GlobalEco
- [Universal Parcel Scraper : Evri UK](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/evri-uk) – formats, champs et statuts UK
:::
