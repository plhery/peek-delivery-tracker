---
title: Suivi colis Xinghe Freight : numéro GZXH…YQ et statuts
description: Suivre un colis Xinghe Freight (星和货运) depuis Guangzhou : format du numéro GZXH…YQ, site de suivi partagé avec GZYL, sens des statuts et qui contacter.
slug: xinghe-freight-suivi-colis
published: 2026-11-18
updated: 2026-11-20
---

Xinghe Freight (星和货运, 广州星和货运代理有限公司) est un transitaire installé à Guangzhou (Canton), en Chine. Si une boutique chinoise a expédié ta commande avec Xinghe, cherche un numéro formé de GZXH, dix chiffres et YQ — la forme que Peek reconnaît pour Xinghe — et colle-le dans le champ ci-dessus. Le suivi de Xinghe fonctionne sur le site de suivi de [GZYL](carrier:gzyl), un autre transitaire de Guangzhou : le champ de recherche du site web de Xinghe y envoie directement ton numéro. Ce système tourne sur une simple adresse numérique de serveur, en HTTP non chiffré. Peek l'interroge pour toi et sollicite des services de suivi lorsque ce serveur ne connaît pas ton numéro.

## Numéro de suivi Xinghe Freight : à quoi il ressemble

Les numéros que Peek rattache à Xinghe Freight se composent de GZXH, dix chiffres et YQ, soit 16 caractères au total. Avec des chiffres inventés :

| Numéro | Ce que c'est |
| --- | --- |
| `GZXH4829105736YQ` | GZXH, dix chiffres et YQ : le format de Xinghe Freight. Peek affiche Xinghe Freight immédiatement. |
| `GZXH482910573YQ` | Neuf chiffres seulement : ce n'est pas le format de Xinghe. Vérifie qu'il ne manque pas un chiffre. |
| `YL4829105736YQ` | YL, dix chiffres et YQ : le format des lettres de transport de [GZYL](carrier:gzyl). Même site de suivi, mais un autre transitaire. |
| `JXCAA4829105736YQ` | Finit lui aussi par YQ, comme chez de nombreux transitaires chinois, mais commence par JXC : un numéro [Juxi Express](carrier:juxi-express). |

Les espaces, les tirets et les minuscules ne gênent pas Peek lorsque tu colles le numéro.

**Où le trouver.** C'est le vendeur qui réserve l'envoi auprès de Xinghe et te communique le numéro. Si tu ne le retrouves pas, consulte [où trouver son numéro de suivi](guide:find-tracking-number).

## Suivi Xinghe Freight : ce que veulent dire les statuts

Comme le suivi de Xinghe repose sur celui de GZYL, les étapes apparaissent avec les formulations anglaises de GZYL, fautes de frappe comprises. Peek les interprète selon la grille d'étapes de GZYL. Dans l'ordre où un colis les reçoit :

| Ce qu'affiche le site de suivi | Ce que ça veut dire pour toi | Peek affiche |
| --- | --- | --- |
| `Order information received` | La boutique a transmis les informations de la commande. Personne n'a encore le colis en main. | « Étiquette créée » |
| `Arrive at the cross border receiving point` | Le transitaire a réceptionné le colis : c'est son premier scan. | « Pris en charge » |
| `Goods leave the operation center` | Le colis a quitté le centre d'opérations. | « En transit » |
| `Arrive at the international airport` | À l'aéroport, en attente de son vol. | « En transit » |
| `Departing from the airport` | L'avion a décollé. | « En transit » |
| `Item arrives at destination airport` | Le colis a atterri dans le pays de destination. | « En transit » |
| `local carrier Agent Import customs clearance` | Un agent dans ton pays effectue le dédouanement à l'importation. | « À la douane » |
| `The import customs keep it for inspection` | La douane du pays de destination inspecte le colis. | « À la douane » |
| `Customs clearance completed` | Le dédouanement est terminé et le colis reprend sa route. | « En transit » |
| `Internationaliy Package received at processing center` | Un centre de traitement dans ton pays a pris en charge le colis dédouané. Ce n'est pas un nouvel enlèvement. | « En transit » |
| `Leave the email processing center` | Le colis a quitté ce centre de traitement. | « En transit » |
| `Arrival sorting, distribution center` | Arrivée dans un centre de tri et de distribution de ton pays, suivi du nom de la ville. | « En transit » |
| `Leaving the sorting center` | Départ d'un centre de tri, avec le nom de la ville. | « En transit » |
| `Package is on the way` | En acheminement dans ton pays, avec une ville. Cela ne signifie pas que le livreur est en tournée. | « En transit » |
| `Delivered` | La livraison, accompagnée de la ville. Il peut s'agir d'une prévision écrite d'avance et non d'un scan : voir plus bas. | « Livré », avec une note sur le calendrier fixe, sauf si Peek écarte l'étape (plus bas) |

**Passage par Hong Kong.** Un envoi peut également afficher `香港处理中心已收寄【Hong Kong】` : une prise en charge postale au centre de traitement de Hong Kong, relayée en chinois avec la ville entre crochets. Peek affiche la ligne telle quelle sous « Pris en charge ».

**Une ligne en chinois.** Lorsqu'une commande n'a encore aucun scan, la page du site de suivi peut afficher `货物电子信息已经收到`, ce qui signifie que les données électroniques de la marchandise ont été reçues. La plateforme génère cette ligne d'elle-même : le vendeur a créé la commande, mais personne n'a encore scanné le colis. Peek l'affiche en chinois sous « Étiquette créée ».

**Heures.** Peek lit chaque horaire du site de suivi à l'heure de Pékin (UTC+8), y compris pour les étapes situées dans ton pays, puis le convertit dans le fuseau horaire de ton appareil. En hiver, une étape marquée 20 h à Pékin correspond à 13 h en France métropolitaine, en Belgique et en Suisse, et à 14 h à l'heure d'été (du dernier dimanche de mars au dernier dimanche d'octobre).

**Étapes écrites à l'avance.** Sur cette plateforme de suivi, les transitaires peuvent enregistrer à l'avance toute la chronologie d'un colis, de la collecte jusqu'à la dernière étape dans ton pays, et le site affiche chaque ligne au fur et à mesure que son heure arrive. Une telle ligne est une prévision, pas un scan réel. Sur le site de suivi de GZYL, ce programme s'achève par un `Delivered` ajouté en lot le soir du jour affiché : il porte la date du lendemain de la dernière étape prévue, pendant les heures de bureau à Pékin, quel que soit le pays de destination. Peek écarte ces étapes programmées (voir ci-dessous), mais la copie d'un service de suivi peut encore les montrer : un `Delivered` vu ailleurs ne prouve donc pas que ton colis est arrivé.

## Qui livre les colis Xinghe Freight

Peek ne sait pas quelle entreprise assure la livraison finale des colis Xinghe en France, en Belgique ou ailleurs en Europe : ni les étapes du site de suivi dans le pays de destination ni le site web de Xinghe ne nomment de transporteur local. La seule société de livraison à laquelle Peek peut transmettre un colis Xinghe est [La Poste suisse](carrier:swiss-post), en Suisse et au Liechtenstein, lorsque la fiche de l'envoi la mentionne.

Le site propre de Xinghe ne contient aucune information destinée aux acheteurs en ligne : aucun délai de livraison, aucune règle sur les avis de passage, les points relais ou la durée de garde. Ces modalités relèvent du transporteur qui effectue le dernier kilomètre. Demande au vendeur quelle société livre ta commande et quel est son numéro de suivi.

**Frais de douane dans l'UE.** Les colis expédiés depuis la Chine entrent dans l'Union européenne depuis un pays tiers. Depuis le 1er juillet 2026, les biens achetés en ligne hors du territoire douanier de l'UE dans des envois d'une valeur maximale de 150 € paient un droit de douane de 3 € par catégorie d'article. Depuis le 1er novembre 2026 s'ajoutent des frais de traitement de l'Union de 2 € par catégorie d'article, quelle que soit la valeur du colis : une catégorie regroupe les marchandises d'un envoi partageant le même classement tarifaire, la même désignation et, lorsqu'elle doit être déclarée, la même origine, quel qu'en soit le nombre d'unités. Pour savoir comment la TVA et ces frais sont perçus en France, en Belgique ou en Suisse, consulte [colis bloqué en douane](guide:customs).

## Colis Xinghe Freight pas arrivé ou suivi bloqué

**Seulement `Order information received` ou `货物电子信息已经收到`.** Le vendeur a enregistré la commande, mais personne n'a encore scanné le colis. Si rien ne bouge après quelques jours, demande au vendeur si l'envoi a bien été remis au transporteur.

**« En attente de Xinghe Freight ».** Peek ne dispose encore d'aucune étape confirmée pour ton numéro : soit le site de suivi de GZYL ne le connaît pas, soit il ne contient qu'un calendrier d'étapes écrites d'avance (pouvant aller jusqu'à `Delivered`), et les services de suivi interrogés par Peek n'ont rien non plus. Peek poursuit ses vérifications en interrogeant le site de suivi de GZYL toutes les 6 heures après les premières heures pour un colis enregistré dans l'application iPhone ou dans ton compte. Pour une recherche sans compte, Peek interroge pendant les 6 premières heures, puis lorsque tu ouvres son lien (au plus toutes les 6 heures, et au plus une fois par jour après les deux premiers jours) ainsi qu'une fois par jour jusqu'à 30 jours après la recherche tant que les notifications sont activées. Pour un numéro inconnu, le site de GZYL renvoie une ligne unique avec ton numéro comme référence et tous les autres champs vides : Peek considère cette réponse, tout comme un programme écrit d'avance, comme une commande enregistrée sans scan et maintient « En attente de Xinghe Freight » sans te demander de corriger le numéro. Compare-le donc toi-même avec le message du vendeur.

**Bloqué à la douane.** Si l'on te réclame des frais d'importation, consulte [colis bloqué en douane](guide:customs). Si l'envoi reste bloqué plusieurs jours au dédouanement, préviens le vendeur.

**Le site de suivi ou un service tiers affiche plus d'étapes que Peek.** Ces lignes supplémentaires, `Delivered` compris, peuvent appartenir au programme écrit d'avance que Peek met de côté. Fie-toi à la fenêtre de livraison annoncée par la boutique et contacte le vendeur une fois cette échéance dépassée. Plus de repères dans [suivi de colis qui ne bouge plus](guide:tracking-not-updating) et [suivi d'un colis venant de Chine](guide:tracking-from-china).

**`Delivered`, mais rien reçu.** Cette mention peut provenir d'une chronologie écrite d'avance. Sauf lorsque La Poste suisse assure la livraison, Peek affiche sous « Livré » : « Xinghe Freight met à jour selon un calendrier fixe, sans signature. Si rien n’est arrivé, contacte le vendeur. » Signale au vendeur que tu n'as rien reçu et demande-lui le nom du transporteur local ainsi que son numéro de suivi. Voir [colis livré mais non reçu](guide:delivered-not-received).

**Perdu ou retourné.** C'est le vendeur qui a réservé le transport auprès de Xinghe : adresse-toi à lui, ou à la marketplace, pour demander un remboursement ou un nouvel envoi. Voir [colis perdu](guide:lost-parcel) et [colis retourné à l'expéditeur](guide:returned-to-sender).

## Xinghe Freight contact : téléphone et service client

Le site web de Xinghe (`xinghe-express.cn`), disponible uniquement en chinois, mentionne les coordonnées suivantes :

- **Ligne téléphonique** : `19924483103`, annoncée ouverte 24 h/24 et 7 j/7 (`+86 199 2448 3103` depuis l'étranger).
- **E-mail** : `3975605292@qq.com`, réservé aux demandes commerciales et aux devis.
- **Adresse** : Guangzhou, province du Guangdong (`广东省广州市`), sans nom de rue ; le site demande de prendre contact avant toute visite.

Le site ne prévoit aucun service d'assistance pour les destinataires à l'étranger, et son formulaire de contact se contente d'ouvrir ton logiciel de messagerie vers cette adresse e-mail. Le client de Xinghe étant le vendeur, contacte d'abord la boutique où tu as acheté l'article, puis la marketplace si elle ne répond pas.

## Suivre un colis Xinghe Freight avec Peek

[Peek](/) identifie aussitôt Xinghe Freight devant un numéro GZXH…YQ, avec la mention « Transporteur détecté ». Si la boutique t'a communiqué un numéro d'un autre format, colle-le quand même : Peek suit plus de 3 500 transporteurs. Pour attribuer manuellement un envoi à Xinghe, touche « Changer » et cherche Xinghe Freight (tu peux aussi taper 星和货运), ou utilise « Changer de transporteur » sur un colis déjà enregistré.

Peek interroge le site de suivi de GZYL, exactement comme le fait le champ de suivi du site de Xinghe. Il affiche le statut, chaque étape avec son lieu et, lorsque la fiche le renseigne, le pays de destination. Les horaires sont lus à l'heure de Pékin et présentés dans le fuseau horaire de ton appareil ; sur peektracker.com et dans l'application iPhone, l'historique s'achève par « Heures dans ton fuseau horaire : » suivi du nom du fuseau. Peek écarte les noms, l'adresse, les codes postaux, le poids, les détails de paiement, la signature et la preuve de livraison pouvant figurer dans la fiche, et n'affiche aucune date de livraison prévue, car le site n'en fournit pas.

Lorsque le site de suivi de GZYL ne connaît pas ton numéro, Peek interroge des services de suivi et affiche leur copie des étapes, qui constitue parfois la seule trace disponible pour un numéro Xinghe. Tant qu'aucune source n'a d'étape confirmée, Peek affiche « En attente de Xinghe Freight », sans message d'erreur sur le numéro.

Peek retient toute étape écrite plus de 12 heures avant l'heure qu'elle affiche, ou plus de 6 heures avant dans la même seconde qu'une telle étape, ainsi que toute étape datée après l'instant de la vérification, à l'heure de Pékin. Sur un dossier comportant des étapes écrites d'avance, il retient également `Delivered`, même écrit le jour même. Il détermine le statut d'après les étapes conservées et non d'après le résumé de la fiche, et écarte aussi dans les copies des services de suivi les étapes déjà repérées comme écrites d'avance, `Delivered` compris. En revanche, si le site de suivi n'a aucune trace du numéro ou est injoignable, Peek ne peut pas distinguer une prévision d'un vrai scan dans la copie d'un service de suivi. Lorsqu'il retient des étapes écrites d'avance, Peek l'indique sous le statut jusqu'à la livraison ou au retour : « Le site de Xinghe Freight affiche des étapes écrites à l’avance, même la livraison. Peek ne peut pas les confirmer et les laisse donc de côté. Cherche sur ce site le transporteur qui livre dans ton pays et son numéro de suivi ; s’il n’y en a pas, demande au vendeur. » Une étape ainsi écartée ne compte jamais comme un scan : elle ne modifie pas le statut et ne déclenche aucune alerte.

Hormis La Poste suisse, Peek ne transfère pas un colis Xinghe au transporteur final : si le vendeur te donne le numéro du transporteur local, ajoute-le comme un colis séparé. Peek vérifie jusqu'à toutes les 10 minutes tant que le colis est ouvert ou le jour de sa livraison avec les notifications activées, et toutes les heures sinon ou lorsque l'envoi reste silencieux (après 12 heures sur « Étiquette créée » ou « Pris en charge », ou deux jours sans nouveauté) ; lorsqu'il passe par les services de suivi, il vérifie toutes les heures, toutes les 3 heures après un jour sans scan, et jusqu'à toutes les 15 minutes le jour de la livraison tant que le colis est ouvert ou que les notifications sont activées. Tu peux activer les alertes pour « Chaque scan », « Étapes importantes » ou « Livraison seulement » : « Étapes importantes » couvre les deux étapes de douane et `Delivered`, mais ni les vols ni le tri. Peek ne peut contacter ni Xinghe Freight, ni GZYL, ni le vendeur à ta place.

## Questions sur le suivi Xinghe Freight

### Qu'est-ce que Xinghe Freight (星和货运) ?

C'est 广州星和货运代理有限公司 (Guangzhou Xinghe Freight Forwarding), un transitaire basé à Guangzhou, en Chine. Son site indique une création en 2014 et mentionne le fret maritime, aérien et terrestre, l'agence maritime ainsi que les formalités douanières. Les numéros rattachés à Xinghe dans Peek suivent le format GZXH, dix chiffres et YQ.

### Pourquoi mon colis Xinghe Freight affiche-t-il le suivi de GZYL ?

Parce que le suivi de Xinghe fonctionne sur le système de GZYL : le formulaire de suivi du propre site de Xinghe y renvoie ton numéro, et les étapes utilisent les libellés de GZYL. Peek conserve le colis sous le nom Xinghe Freight et lit ce système pour toi.

### Qui livre les colis Xinghe Freight dans mon pays ?

Peek ne le sait pas : ni les étapes du suivi ni le site de Xinghe ne citent de transporteur local. Seuls les envois vers la Suisse et le Liechtenstein peuvent basculer automatiquement vers La Poste suisse dans Peek. Demande au vendeur quelle société livre ta commande et quel est son numéro de suivi.

### Pourquoi Peek n'affiche-t-il encore aucun suivi pour mon numéro GZXH ?

Le système n'a peut-être pas encore reçu la commande, ou ne contient que des étapes écrites d'avance (jusqu'à `Delivered` inclus), que Peek met de côté. Peek interroge aussi des services de suivi et continue de vérifier selon la cadence détaillée plus haut sous « En attente de Xinghe Freight ». Comme Peek n'affiche pas d'alerte de format dans ce cas, vérifie toi-même le numéro par rapport au message de la boutique ; si rien n'apparaît après quelques jours, demande au vendeur si le colis est bien parti.

:::sources
- [Xinghe Freight : 广州星和货运代理有限公司 (accueil, en chinois)](http://xinghe-express.cn/) – raison sociale, services de transit international, formulaire de recherche de suivi, ligne téléphonique et e-mail
- [Xinghe Freight : 关于我们 (à propos, en chinois)](http://xinghe-express.cn/about.html) – fondation en 2014, fret maritime, aérien et routier, agence maritime et déclaration en douane
- [Xinghe Freight : 产品服务 (services, en chinois)](http://xinghe-express.cn/services.html) – prestations proposées, absence d'informations sur la livraison de petits colis e-commerce
- [Xinghe Freight : 联系我们 (contact, en chinois)](http://xinghe-express.cn/contact.html) – ligne ouverte 7 j/7 et 24 h/24, e-mail pour demandes commerciales et devis, adresse limitée à Guangzhou avec prise de contact préalable, formulaire ouvrant le client mail
- [Commission européenne : EU Customs Reform (en anglais)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – droit de douane de 3 € par catégorie d'article sur les envois jusqu'à 150 € depuis le 1er juillet 2026 et frais de traitement de l'Union de 2 € depuis le 1er novembre 2026
- [Commission européenne : Guidance on the 3 EUR customs duty and Union handling fee (PDF, en anglais)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – frais de traitement de 2 € par catégorie d'article quelle que soit la valeur et définition d'une catégorie d'article
- [Commission européenne : Summertime (en anglais)](https://transport.ec.europa.eu/transport-themes/summertime_en) – heure d'été du dernier dimanche de mars au dernier dimanche d'octobre en Europe centrale
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – noms et alias, format GZXH…YQ, suivi Xinghe lu sur le système de GZYL, libellés et statuts des étapes, prise en charge à Hong Kong, ligne chinoise de la plateforme, heure de Pékin, étapes écrites d'avance, recours aux services de suivi, réponse pour un numéro inconnu, relais vers La Poste suisse, ce que Peek affiche, retient et écarte
:::
