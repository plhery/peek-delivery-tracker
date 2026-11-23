---
title: Suivi colis Meiteshun : numéro AMTS…FS, statuts et contact
description: Suivre un colis Meiteshun Logistics (美特顺) depuis Shenzhen : format du numéro AMTS…FS, sens des étapes et pourquoi la livraison peut être écrite à l'avance.
slug: meiteshun-suivi-colis
published: 2026-11-18
updated: 2026-11-21
---

Meiteshun Logistics (美特顺物流, 深圳市美特顺物流有限公司, aussi référencé sous MTSCN) est un transitaire basé à Shenzhen, en Chine. Ses numéros de suivi commencent par AMTS et se terminent par FS : si la boutique qui a expédié ta commande t'en a transmis un, colle-le dans le champ ci-dessus. Lis toutefois le suivi de Meiteshun avec prudence : son système inscrit tout le parcours d'un colis, livraison comprise, dès la création de la commande, si bien qu'un `Delivered` n'y prouve pas que ton colis est arrivé.

## Numéro de suivi Meiteshun : à quoi il ressemble

Les lettres de transport de Meiteshun se composent de AMTS, neuf chiffres et FS, soit 15 caractères au total. Avec des chiffres inventés :

| Numéro | Ce que c'est |
| --- | --- |
| `AMTS482910517FS` | AMTS, neuf chiffres et FS : une lettre de transport Meiteshun. |
| `AMTS48291051FS` | Huit chiffres seulement : ce n'est pas le format de Meiteshun. Vérifie s'il manque un chiffre. |
| `RR482910576CN` | Deux lettres, neuf chiffres et CN : un numéro [China Post](carrier:china-post), pas un numéro Meiteshun. |

Les minuscules et les espaces ne gênent pas Peek lorsque tu colles le numéro.

**Où le trouver.** C'est le vendeur qui réserve l'envoi auprès de Meiteshun et te communique le numéro. Si tu ne le retrouves pas, consulte [où trouver son numéro de suivi](guide:find-tracking-number).

**Une référence de la boutique.** Le site de suivi de Meiteshun retrouve également une commande à partir de la référence attribuée par le vendeur, saisie exactement telle qu'elle t'a été donnée, tirets compris, et affiche alors le numéro AMTS correspondant.

**Un lien avec des chiffres à la place d'un nom de domaine.** Le site de suivi de Meiteshun ne repose pas sur un nom de domaine, mais sur une simple adresse numérique de serveur en HTTP non chiffré. Un lien vers ce site comporte cette adresse numérique à la place du nom d'hôte, ainsi que `trackIndex.htm`, `documentCode=` et ton numéro ou ta référence : tu peux le coller tel quel dans Peek.

## Suivi Meiteshun : ce que veulent dire les statuts

Chaque commande chez Meiteshun déroule le même scénario, dont la plupart des étapes sont écrites à l'avance (voir plus bas). Voici les formulations anglaises dans l'ordre où le scénario progresse, avec le statut affiché par Peek tant que chacune est la plus récente :

| Ce qu'écrit Meiteshun | Ce que ça veut dire pour toi | Peek affiche |
| --- | --- | --- |
| `The package has been collected and is ready to be sent to the distribution center` | Première étape : la collecte par Meiteshun à Shenzhen. | « Pris en charge » |
| `item inbound in sorting center` | Entrée dans un centre de tri, avec le lieu `ShenZhen`. | « En transit » |
| `item outbound in sorting center` | Sortie de ce centre de tri. | « En transit » |
| `Arriving at the terminal` | Arrivée dans un terminal aéroportuaire, avec le lieu `Hong Kong`. | « En transit » |
| `Leaving the terminal, the airline will take off` | L'avion décolle de Hong Kong. | « En transit » |
| `Arriving at the destination country` | Atterrissage dans ton pays, désigné par son code à deux lettres (`FR` pour la France, `BE` pour la Belgique). | « En transit » |
| `Customs clearanceisin progress` | Dédouanement à l'importation dans ton pays. L'absence d'espaces vient de Meiteshun. | « À la douane » |
| `Customs clearance completed` | Dédouané et reparti. | « En transit » |
| `Arriving at the mail processing center` | Dans un centre de traitement du courrier de ton pays. | « En transit » |
| `Arrive at the sorting and distribution center` | Dans un centre de tri et de distribution proche de chez toi, avec ta ville ou ta région. | « En transit » |
| `package is on the way` | L'étape précédant la livraison. Elle ne signifie pas que le colis est en tournée de livraison. | « En transit » |
| `Delivered` | Dernière étape du scénario (voir plus bas). | « Livré », sauf si Peek écarte l'étape (plus bas) |

Pour une commande qui n'a encore aucune étape, la plateforme génère d'elle-même `货物电子信息已经收到` : les données électroniques de la marchandise ont été reçues. Le vendeur a créé la commande, mais personne n'a encore scanné le colis ; Peek affiche cette ligne en chinois sous « Étiquette créée ».

Le lieu d'une étape figure au début de la ligne, devant une virgule, et les étapes situées dans ton pays ajoutent ta ville ou ta région ainsi que le code postal après la formulation. Toutes les heures suivent l'horloge de Pékin (UTC+8), même pour les étapes dans ton pays : une ligne marquée 21 h sur le suivi de Meiteshun correspond à 14 h en France métropolitaine, en Belgique et en Suisse en hiver, et à 15 h à l'heure d'été (du dernier dimanche de mars au dernier dimanche d'octobre).

**Étapes écrites à l'avance.** Meiteshun fait tourner son suivi sur Hualei (华磊), une plateforme de transitaires qu'utilisent aussi d'autres sociétés chinoises comme [GZYL](carrier:gzyl) ou Pengyou. Chez Meiteshun, toutes les étapes d'une commande sont enregistrées dès la création de celle-ci, chacune portant déjà l'heure prévue, de la collecte jusqu'à `Delivered`, puis affichées au fil du temps. Il s'agit d'une prévision et non de scans réels : ces lignes indiquent où le colis était censé se trouver à une date donnée. Les services de suivi qui recopient la fiche de Meiteshun reproduisent les mêmes étapes.

## Qui livre les colis Meiteshun

Les étapes de Meiteshun dans ton pays ne nomment jamais l'entreprise chargée de la livraison finale. Le nom de service indiqué sur les fiches est MTS全球小包 (petit colis mondial MTS). Sur la page de suivi de Meiteshun, un numéro inscrit sous 渠道转单号 (numéro de transfert), ou sous 跟踪号码 (numéro de suivi) lorsqu'il diffère de ton numéro AMTS, peut être celui du transporteur final : ajoute-le dans Peek comme un colis distinct.

Peek n'a trouvé aucun délai de livraison publié par Meiteshun, ni aucune règle concernant les avis de passage, les points relais ou la durée de garde en instance. Demande au vendeur quelle société livre ta commande et quel est son numéro de suivi : ce sont le suivi et les règles de ce transporteur local qui comptent. Si l'on te demande des frais d'importation — lesquels comprennent dans l'Union européenne un droit de douane de 3 € par catégorie d'article sur les envois hors territoire douanier de l'UE jusqu'à 150 € depuis le 1er juillet 2026, ainsi que des frais de traitement de l'Union de 2 € par catégorie d'article quelle que soit la valeur depuis le 1er novembre 2026 — consulte [colis bloqué en douane](guide:customs).

## Colis Meiteshun pas arrivé ou suivi bloqué

**`Delivered`, mais rien n'est arrivé.** Cette étape peut correspondre à la prévision du scénario plutôt qu'à un scan. Signale au vendeur que le colis n'est pas arrivé et demande-lui le numéro du transporteur qui assure la livraison locale. Plus de conseils dans [colis livré mais non reçu](guide:delivered-not-received).

**Toutes les étapes tombent à l'heure, mais aucun colis.** Comme les étapes ont été écrites dès la création de la commande, elles montrent le programme prévu et non la position réelle du colis. Fie-toi au délai de livraison annoncé par la boutique et contacte le vendeur une fois cette date dépassée.

**Bloqué à Shenzhen dans Peek.** Si Peek n'affiche que les premières étapes à Shenzhen alors qu'un lien fourni par le vendeur en montre davantage, c'est que les étapes absentes des scans de Peek ont été écrites à l'avance ou portent une date postérieure à la vérification (voir plus bas). Une étape écrite à l'avance ne compte jamais comme un scan : elle ne change ni le statut ni la date prévue et n'envoie aucune alerte. Jusqu'à la livraison ou au retour, Peek ouvre l'historique du suivi par « D’autres sites affichent peut-être plus d’étapes, mais ce ne sont que des prévisions. » et, sous « Les voir », liste en gris les étapes qu'il a retenues, chacune avec « Prévu pour » et son jour. Cherche sur le site de Meiteshun le transporteur qui livre dans ton pays et son numéro de suivi ; s'il n'y en a pas, demande au vendeur. Voir aussi [suivi de colis qui ne bouge plus](guide:tracking-not-updating) et [suivi d'un colis venant de Chine](guide:tracking-from-china).

**Aucun résultat.** Pour un numéro qu'il ne connaît pas, le site de Meiteshun affiche ton numéro sous 参考号 (référence) et laisse tous les autres champs vides. Peek interprète cette réponse comme un numéro introuvable : il affiche « En attente de Meiteshun Logistics », puis, deux jours plus tard, « Numéro de suivi à vérifier ». Vérifie le numéro auprès du vendeur.

**Perdu, endommagé ou retourné.** C'est le vendeur qui a réservé l'envoi chez Meiteshun : demande un remboursement ou une réexpédition à la boutique ou à la marketplace. Voir [colis perdu](guide:lost-parcel) et [colis retourné à l'expéditeur](guide:returned-to-sender).

## Meiteshun contact : téléphone et service client

Pour toute question relative à ta commande, adresse-toi d'abord au vendeur : c'est lui qui a confié le colis à Meiteshun. Si le vendeur ne répond pas, contacte la marketplace. Si ni l'un ni l'autre n'aboutit, la fiche consacrée à Meiteshun sur 17TRACK mentionne une adresse e-mail (`szmeiteshun@163.com`), son site web (`mtswl.com.cn`) ainsi qu'un bureau dans le district de Longgang à Shenzhen (parc logistique Qianlong, rue Pinghu), sans numéro de téléphone : écris en précisant ton numéro AMTS et le nom de la boutique.

## Suivre un colis Meiteshun avec Peek

[Peek](/) reconnaît un numéro Meiteshun à sa forme : AMTS, neuf chiffres et FS, qu'aucun autre transporteur de son catalogue n'utilise. Tu peux également coller un lien vers le site de suivi de Meiteshun contenant `documentCode=` suivi du numéro ou de la référence du vendeur. Une référence de boutique n'a pas de forme reconnaissable : colle-la malgré tout, puisque Peek suit plus de 4 500 transporteurs, puis touche « Changer » et sélectionne Meiteshun Logistics (accessible aussi en tapant 美特顺 ou MTSCN), ou utilise « Changer de transporteur » sur un colis déjà enregistré. Peek affiche alors aussi le numéro AMTS sous « Meiteshun Logistics le suit sous le numéro ». Peek supprime toutefois les tirets, qu'ils soient saisis ou présents dans un lien : cherche une référence à tirets sur le site propre de Meiteshun ou demande le numéro AMTS au vendeur.

Peek interroge directement le site de suivi de Meiteshun et affiche le statut, chaque étape avec son lieu ainsi que le pays de destination. Les étapes apparaissent en anglais telles que Meiteshun les écrit, hormis `Delivered` qui s'affiche « Livré » dans l'historique : `Customs clearanceisin progress` compte comme « À la douane », et `package is on the way` comme « En transit » et non « En livraison ». Dans les étapes situées dans ton pays, Peek conserve le nom de ta ville et retire le code postal. Les horaires sont lus à l'heure de Pékin et convertis dans le fuseau horaire de ton appareil. Peek laisse de côté les noms, l'adresse, le poids, les frais, la signature et la preuve de livraison pouvant figurer dans le dossier, et n'affiche aucune date de livraison estimée, car Meiteshun n'en donne pas.

Peek retient toute étape écrite plus de douze heures avant l'heure qu'elle affiche, ainsi que toute étape datée après l'instant de sa vérification, à l'heure de Pékin. Il s'appuie sur les étapes conservées et non sur le statut général de la fiche. Lorsque tout le scénario d'une commande a été écrit dès sa création, Peek ne garde que les étapes datées avant ce moment ou jusqu'à six heures après, de sorte que le `Delivered` inscrit alors est retenu. Lorsque Peek doit lire la page `trackIndex.htm` faute d'étapes renvoyées par l'interface de recherche, il y retient tous les `Delivered`, car cette page n'indique pas quand chaque ligne a été écrite. Une commande dont toutes les étapes sont retenues n'a pas encore d'historique dans Peek et affiche « En attente de Meiteshun Logistics », sans alerte sur le numéro : Peek considère des étapes écrites d'avance comme le signe que Meiteshun détient la commande, et poursuit ses vérifications. Qu'il retienne certaines étapes ou la totalité, Peek écarte également les copies de ces étapes chez les services de suivi.

Peek suit l'envoi sur le site de Meiteshun. Les fiches Meiteshun ne nomment jamais le transporteur qui livre à l'étranger, donc Peek ne prend aucun relais : ajoute le numéro du transporteur final communiqué par le vendeur comme un colis séparé.

Peek vérifie jusqu'à toutes les 10 minutes tant que tu gardes le colis ouvert ou le jour de sa livraison avec les notifications activées, et jusqu'à toutes les 2 minutes le jour où un colis passe en tournée de livraison (dès 6 h, heure locale, ou une demi-heure avant un créneau annoncé, tant que le suivi direct de Meiteshun répond ; via les services de suivi, toutes les heures, toutes les 3 heures après un jour sans scan, et jusqu'à toutes les 15 minutes le jour de la livraison tant que le colis est ouvert ou que les notifications sont activées), bien qu'aucune étape connue de Meiteshun ne déclenche ce statut. Sinon, il vérifie toutes les heures. Après deux jours sans nouveauté (ou 12 heures sur « Étiquette créée » ou « Pris en charge »), il passe à une vérification par heure — ce qu'atteint rapidement une commande dont tout le scénario a été écrit à la création. Tu peux choisir des alertes pour « Chaque scan », « Étapes importantes » ou « Livraison seulement » (« Étapes importantes » couvre `Customs clearanceisin progress` et `Delivered`), et Peek ne peut contacter ni Meiteshun ni le vendeur à ta place.

## Questions sur le suivi Meiteshun

### Qu'est-ce que Meiteshun Logistics (美特顺) ?

C'est 深圳市美特顺物流有限公司, Shenzhen Meiteshun Logistics, un transitaire installé à Shenzhen, en Chine. Ses lettres de transport suivent le format AMTS, neuf chiffres et FS, et ses dossiers portent le nom de service MTS全球小包.

### Un numéro AMTS est-il un numéro China Post ?

Non. AMTS, neuf chiffres et FS est le format propre à Meiteshun. Les numéros internationaux de China Post comportent deux lettres, neuf chiffres et CN.

### Pourquoi Meiteshun indique-t-il que mon colis est livré alors que je n'ai rien reçu ?

Très probablement parce que le système de Meiteshun écrit toutes les étapes d'une commande, livraison comprise, au moment même où la commande est créée. Un `Delivered` généré ainsi est une prévision, pas un scan. Préviens le vendeur que le colis n'est pas arrivé.

### Qui livre les colis Meiteshun dans mon pays ?

Les étapes de Meiteshun ne le précisent pas : aucune ne nomme de société de livraison. Sa page de suivi peut afficher le numéro du transporteur final dans la colonne 渠道转单号 (numéro de transfert) ; sinon, demande au vendeur quelle entreprise livre ta commande et quel est son numéro de suivi.

### Qui contacter si mon colis Meiteshun disparaît ?

Le vendeur auprès duquel tu as commandé : c'est lui qui a réservé l'envoi chez Meiteshun. Si ni le vendeur ni la marketplace ne règlent le problème, la page de 17TRACK consacrée à Meiteshun indique l'adresse e-mail `szmeiteshun@163.com`.

:::sources
- [17TRACK : MTSCN tracking (en anglais)](https://www.17track.net/en/carriers/mtscn) – adresse e-mail, site web et bureau de Shenzhen (district de Longgang) mentionnés pour Meiteshun, absence de numéro de téléphone
- [Commission européenne : EU Customs Reform (en anglais)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – droit de douane de 3 € sur les envois jusqu'à 150 € depuis le 1er juillet 2026 et frais de traitement de l'Union de 2 € depuis le 1er novembre 2026
- [Commission européenne : Guidance on the 3 EUR customs duty and Union handling fee (PDF, en anglais)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – frais de 2 € par catégorie d'article vendu à distance quelle que soit la valeur, comptés par ligne de déclaration comme le droit de 3 €
- [Commission européenne : Summertime (en anglais)](https://transport.ec.europa.eu/transport-themes/summertime_en) – heure d'été du dernier dimanche de mars au dernier dimanche d'octobre en Europe centrale
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – nom de l'entreprise, plateforme Hualei, format AMTS, site de suivi sur adresse numérique et liens reconnus, recherche par référence avec tirets, scénario et libellés des étapes, lieux et heure de Pékin, étapes écrites à l'avance, réponse pour un numéro inconnu, ligne en chinois de la plateforme, nom de service, absence de transporteur dans les étapes, colonnes de numéro de transfert et de suivi, et ce que Peek affiche, retient et laisse de côté
:::
