---
title: Suivi de commande Amazon : trouver le numéro de suivi
description: Où Amazon affiche le transporteur et le numéro de suivi, sur le site et dans l'application, et comment suivre ton colis dans Peek, seul ou avec Claude ou ChatGPT.
slug: suivi-commande-amazon
picture: Un téléphone affiche l'avancée d'une commande, deux camionnettes se passent un colis, et Pip regarde.
published: 2026-10-16
updated: 2026-11-20
---

Amazon affiche le transporteur et le numéro de suivi de ton colis au même endroit : Vos commandes, puis Suivre le colis. Copie le numéro dans [Peek](/), et tu suis ce colis avec tous les autres. Ça prend une minute, et ça marche pareil sur tous les sites Amazon. Seule exception : un colis qu'Amazon livre lui-même se suit en général seulement dans ton compte Amazon.

> Tu utilises Claude ou ChatGPT dans Chrome ? [Demande-lui de le faire pour toi](#claude-ou-chatgpt-peuvent-ils-ajouter-mes-colis-amazon-pour-moi).

## Où trouver le numéro de suivi sur Amazon ?

Chaque colis a sa page Suivre le colis. Pour y arriver :

:::steps
- Ouvre Vos commandes | Sur le site, c'est en haut à droite. Dans l'application Amazon, touche l'icône en forme de personne en bas, puis Vos commandes.
- Trouve la commande | Les plus récentes viennent en premier. Quand une commande arrive en plusieurs colis, chaque colis a son propre bouton Suivre le colis.
- Choisis Suivre le colis | Sur le site, le bouton est à côté de la commande. Dans l'application, touche d'abord l'article.
- Repère le transporteur et le numéro | La page donne le nom du transporteur, Colissimo ou Chronopost par exemple, et son numéro de suivi. Tu ne le vois pas ? Choisis « Consultez toutes les mises à jour » : Amazon y liste les numéros de suivi.
- Colle-le dans Peek | Peek reconnaît le transporteur au numéro et commence à suivre le colis.
:::

### Quand le numéro apparaît-il ?

En général une fois le colis expédié. Si le numéro manque, amazon.fr donne trois raisons courantes : la mise à jour du suivi a du retard, l'envoi n'a pas de suivi (c'est le cas de certains envois internationaux standard), ou le vendeur tiers n'a pas transmis le sien à Amazon. Un numéro sans aucune mise à jour pour l'instant ? Colle-le quand même : Peek affiche la première dès que le transporteur en a une.

Les e-mails d'Amazon ne contiennent en général pas le numéro de suivi. Leur bouton de suivi mène à la même page.

### Ça marche aussi sur amazon.com.be, amazon.de et les autres sites ?

Oui. Tous les sites Amazon fonctionnent de la même façon : seuls les mots changent avec la langue du site. En Belgique, amazon.com.be a un service client en français. En Suisse, tu peux commander sur amazon.de, ou sur amazon.fr pour faire tes achats en français.

| Site | Vos commandes | Suivre le colis |
| --- | --- | --- |
| amazon.fr | Vos commandes | Suivre le colis |
| amazon.de | Meine Bestellungen | Sendung verfolgen |
| amazon.com, amazon.co.uk | Your Orders | Track package |
| amazon.it | I miei ordini | Traccia il mio pacco |
| amazon.es | Mis pedidos | Localiza tu paquete |
| amazon.pl | Moje zamówienia | Śledź przesyłkę |

## Quel numéro est le numéro de suivi ?

Une commande Amazon peut afficher trois sortes de numéros. Seul le numéro de suivi du transporteur suit toujours ton colis en dehors d'Amazon.

- **Le numéro de suivi du transporteur.** Il vient du transporteur : Colissimo, Chronopost, Colis Privé, Mondial Relay, DPD, bpost, La Poste suisse… Amazon l'affiche à côté du nom du transporteur. Peek le suit comme n'importe quel colis.
- **Le numéro de commande.** Trois groupes de chiffres reliés par des tirets, comme `123-1234567-1234567`. Il figure sur la commande, la facture et les e-mails d'Amazon. Ce n'est jamais un numéro de suivi : colle-le dans Peek, et Peek répond « On dirait un numéro de commande Amazon ».
- **Un numéro Amazon.** Amazon en donne un aux colis que son propre réseau livre, et Peek le vérifie auprès d'Amazon : [voir plus bas](#peek-peut-il-suivre-un-numero-amazon-tba). Il ressemble à ceci, ici avec des chiffres inventés :

:::anatomy TBA 123456789012
- TBA | Préfixe d'Amazon. Aussi `TBC` (Canada) et `TBM` (Mexique), ou `TBAU` avec 9 chiffres (Australie)
- 123456789012 | Douze chiffres
:::

En Europe, un numéro Amazon peut aussi commencer par deux lettres pour le pays, comme `FR` ou `BE`, suivies de 10 chiffres. Tu ne sais pas lequel tu as ? Tape-le ici :

:::checker
:::

Un colis peut changer de transporteur en route : Amazon en confie certains à un partenaire pour la fin du trajet, comme La Poste suisse pour un colis envoyé en Suisse. La page Suivre le colis nomme alors ce partenaire : copie son numéro.

## Peek peut-il suivre un numéro Amazon TBA ?

Parfois. Quand tu colles un numéro Amazon, Peek le vérifie d'abord sur le suivi public d'Amazon :

- **Amazon le connaît.** C'est un colis qu'Amazon livre pour une autre entreprise, avec [Amazon Shipping](carrier:amazon-shipping). Peek le suit comme les autres.
- **Amazon ne le connaît pas.** C'est alors très probablement [Amazon Logistics](carrier:amazon-logistics) qui livre ta propre commande Amazon, et Amazon ne la montre que dans ton compte. Peek ne l'ajoute pas. Il te le dit et propose « Voir mes commandes Amazon », qui ouvre tes commandes sur le site Amazon correspondant. Peek ne te demande jamais ton mot de passe Amazon.
- **Amazon ne répond pas.** Peek te le dit et propose « Réessayer ».

Quand Amazon livre un colis lui-même, la commande peut n'afficher aucun numéro de suivi. Il n'y a alors rien à copier : seule la page Suivre le colis d'Amazon suit ce colis. Sur amazon.fr, la page « Coordonnées des transporteurs » renvoie d'ailleurs à ton compte pour les colis livrés par Amazon.

## Claude ou ChatGPT peuvent-ils ajouter mes colis Amazon pour moi ?

Oui, si l'assistant peut se servir de ton navigateur. Ces deux-là le peuvent :

- **Claude in Chrome,** l'extension Chrome de Claude, avec une offre Claude payante.
- **L'extension Chrome de ChatGPT,** que tu installes et utilises depuis l'application ChatGPT pour ordinateur.

Les deux travaillent dans la fenêtre Chrome où tu es déjà connecté à Amazon. Demande-lui : il ouvre Vos commandes, passe par chaque page Suivre le colis et note chaque transporteur et numéro de suivi.

La suite dépend d'une chose : Peek est-il connecté à l'assistant ?

- **Si Peek est connecté** et que l'assistant peut s'en servir dans cette conversation, il ajoute lui-même les colis à ton compte Peek, avec le nom de l'article. Dans Claude, ouvre « Peek Parcel Tracker » dans le [répertoire de connecteurs de Claude](https://claude.ai/directory/peek-parcel-tracker), sélectionne « Connect », connecte-toi à Peek et sélectionne « Autoriser ». Dans ChatGPT, Peek s'ajoute comme plugin avec une offre Business, Enterprise ou Edu. Toutes les étapes sont dans [suivre ses colis avec ChatGPT ou Claude](guide:ai-assistants).
- **Sinon,** l'assistant liste les numéros dans la conversation. Copie toute la liste et colle-la dans [Peek](/) : Peek repère chaque numéro. Si tu es connecté à Peek, il les suit tous ; sinon, un à la fois.

Copie ceci dans l'assistant :

> Ouvre Vos commandes sur Amazon dans ce navigateur. Pour chaque colis en route ou tout juste livré, ouvre Suivre le colis et note le transporteur et le numéro de suivi. N'utilise jamais le numéro de commande (trois groupes de chiffres reliés par des tirets). Si Peek est connecté, ajoute chaque colis à Peek, avec le nom de son article. Sinon, liste les numéros ici, un par ligne. Dis-moi quels colis n'ont pas de numéro de suivi. Sur Amazon, lis seulement : n'achète, n'annule, ne retourne et ne modifie rien, et ne te connecte pas à ma place.

Dans tous les cas, l'assistant n'a besoin que de lire Amazon. Il ne doit jamais rien acheter, annuler ou retourner, ni se connecter à ta place. Si Amazon te demande de te connecter, fais-le toi-même dans cette fenêtre, puis redemande-lui. Quand un colis n'a pas de numéro de suivi, ou que Peek le refuse, seul ton compte Amazon le suit.

## Que faire si mon colis Amazon est en retard ou marqué comme livré ?

Tout dépend de qui l'a expédié : la page du produit l'indique sous le bouton « Acheter maintenant ». Amazon s'occupe de ses propres commandes et de celles qu'il expédie pour des vendeurs (« Expédié par Amazon »). Un vendeur qui expédie lui-même répond d'abord aux questions de livraison.

:::steps
- Vérifie Suivre le colis | Tu y vois la dernière mise à jour. La plupart des colis en retard arrivent dans les 48 heures qui suivent la date de livraison estimée.
- Regarde autour | Vérifie l'endroit sûr indiqué dans tes instructions de livraison et la photo de livraison, si le livreur en a pris une. Demande au concierge et aux voisins.
- Contacte le transporteur | Si un autre transporteur l'a livré, la page « Coordonnées des transporteurs » d'amazon.fr donne son lien de suivi et, pour la plupart, son téléphone.
- Contacte le vendeur | Si un vendeur tiers l'a vendu et expédié, écris-lui depuis la commande, dans Vos commandes.
- Contacte Amazon | Pour une commande expédiée par Amazon, écris au service client par chat ou appelle-le, de 6 h à minuit, 7 j/7. Pour la commande d'un vendeur qui ne règle pas le problème, la garantie A à Z couvre le retard de livraison et l'état de l'article.
:::

Plus d'aide : [colis livré mais pas reçu](guide:delivered-not-received), [colis perdu](guide:lost-parcel) et [pourquoi le suivi ne bouge plus](guide:tracking-not-updating).

:::sources
- [Amazon.fr : Suivre votre colis](https://www.amazon.fr/gp/help/customer/display.html?nodeId=GENAFPTNLHV7ZACW) – Vos commandes, Suivre le colis, « Consultez toutes les mises à jour » avec les numéros de suivi, un suivi par article, la plupart des retards réglés en 48 heures ; la même page sur amazon.com, .co.uk, .de, .it, .es et .pl donne leurs libellés
- [Amazon.fr : Coordonnées des transporteurs](https://www.amazon.fr/gp/help/customer/display.html?nodeId=GBPEXEXYULHB5WCH) – le transporteur et le numéro de suivi sous Suivre le colis, principaux transporteurs en France métropolitaine, leur suivi et leur téléphone, colis Amazon suivis dans le compte
- [Amazon.fr : Informations de suivi manquantes](https://www.amazon.fr/gp/help/customer/display.html?nodeId=G6ZFEB9ZDU7QHMER) – retard de mise à jour, envois internationaux standard sans suivi, vendeurs tiers qui ne transmettent pas le leur
- [Amazon.fr : Livraisons d'Amazon Logistics](https://www.amazon.fr/gp/help/customer/display.html?nodeId=GEW3XT9JEMBLTKRV) – partenaires de livraison, suivi dans Vos commandes, photo de l'endroit où le colis a été laissé, endroit sûr dans les instructions de livraison, concierge ou voisin
- [Amazon.fr : À propos de nos politiques de retour](https://www.amazon.fr/gp/help/customer/display.html?nodeId=GKM69DUUYKQWKWX7) – le bouton « Acheter maintenant » sur la page du produit
- [About Amazon : How to track your packages](https://www.aboutamazon.com/news/retail/track-amazon-package) – le menu des commandes en haut à droite, l'icône en forme de personne dans l'application, Track package
- [About Amazon UK : How to change your delivery address on Amazon](https://www.aboutamazon.co.uk/news/retail/how-to-change-your-delivery-address-on-amazon) – qui vend et qui expédie, sous le bouton d'achat
- [About Amazon UK : How to contact a seller on Amazon](https://www.aboutamazon.co.uk/news/small-businesses/how-to-contact-amazon-seller) – contacter le vendeur depuis la commande
- [About Amazon France : Expédié par Amazon](https://www.aboutamazon.fr/actualites/innovations/expedie-par-amazon) – Amazon stocke, emballe et expédie pour des vendeurs, et gère leur service client et leurs retours
- [About Amazon France : Comment contacter le service client Amazon ?](https://www.aboutamazon.fr/actualites/nos-boutiques/comment-contacter-le-service-client-amazon) – chat et téléphone de 6 h à minuit, 7 j/7
- [About Amazon France : Amazon étend les protections apportées par la Garantie A à Z](https://www.aboutamazon.fr/actualites/nos-boutiques/amazon-etend-les-protections-apportees-par-la-garantie-a-a-z-en-france) – remboursement en cas de souci de livraison ou d'état du produit, après avoir essayé de régler le problème avec le vendeur
- [About Amazon (Europe) : Amazon.com.be launches in Belgium](https://www.aboutamazon.eu/news/amazon-prime/amazon-com-be-launches-in-belgium) – amazon.com.be, service client en français, néerlandais ou anglais
- [About Amazon (Europe) : Amazon announces plans to invest over €1 billion in Belgium](https://www.aboutamazon.eu/news/job-creation-and-investment/amazon-announces-plans-to-invest-over-1-billion-to-enhance-belgian-customer-experience) – bpost, partenaire de livraison en Belgique
- [About Amazon Deutschland : Amazon in der Schweiz, alles, was du wissen musst](https://www.aboutamazon.de/news/company-news/aus-der-schweiz-bei-amazon-bestellen-das-musst-du-wissen) – amazon.de et amazon.fr depuis la Suisse
- [About Amazon Deutschland : Wie kommt mein Paket zu mir?](https://www.aboutamazon.de/news/verpackung/wie-kommt-mein-paket-zu-mir) – en Suisse, La Poste suisse livre les colis
- [About Amazon UK : Amazon CEO Andy Jassy's 2023 Letter to Shareholders](https://www.aboutamazon.co.uk/news/company-news/ceo-andy-jassys-2023-letter-to-shareholders) – Amazon Shipping, le réseau de livraison d'Amazon pour les colis d'autres entreprises
- [Claude Help Center : Get started with Claude in Chrome](https://support.claude.com/en/articles/12012173-get-started-with-claude-in-chrome) – offres Claude payantes, Chrome seulement, installation de l'extension
- [Claude Help Center : Use Claude in Chrome safely](https://support.claude.com/en/articles/12902428-use-claude-in-chrome-safely) – tu restes responsable des achats, autorisations site par site
- [ChatGPT : Chrome extension](https://learn.chatgpt.com/docs/chrome-extension) – installée depuis l'application ChatGPT pour ordinateur, marche sur les sites où tu es connecté
- [Peek : Connecter Peek à Claude, ChatGPT ou Gemini](https://peektracker.com/fr/assistants) – les étapes de Peek pour Claude et ChatGPT
- [Peek sur GitHub : les outils des assistants](https://github.com/plhery/peek-delivery-tracker/blob/main/src/server/assistants/tools.ts) – ce que Peek dit à un assistant sur Amazon : Vos commandes, Suivre le colis, pas de numéro de suivi dans les e-mails d'Amazon, lecture seule
- [Peek sur GitHub : textes en français](https://github.com/plhery/peek-delivery-tracker/blob/main/shared/locales/fr.json) – la note sur le numéro de commande Amazon, « Voir mes commandes Amazon », « Réessayer », plusieurs numéros dans un même texte
- [Universal Parcel Scraper : données transporteurs](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers) – formes des numéros Amazon et du numéro de commande, ce que Peek vérifie
:::
