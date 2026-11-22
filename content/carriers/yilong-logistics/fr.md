---
title: Suivi Yilong Logistics : numéros YIL…WL et statuts
description: Suis un colis Yilong Logistics (一龙物流) depuis la Chine : format YIL…WL, signification des statuts de l’entrepôt à la livraison et transporteur final.
slug: suivi-yilong-logistics
published: 2026-11-22
updated: 2026-11-22
---

Yilong Logistics (一龙物流) est un transitaire basé à Putian, dans la province chinoise du Fujian, dont les numéros de lettre de transport commencent par `YIL` et se terminent par `WL`. Saisis le tien dans la case 包裹追踪 (suivi de colis) sur yxhlexpress.com ou dans le champ ci-dessus. L’historique de Yilong va de son entrepôt jusqu’à la compagnie aérienne, et peut se poursuivre à travers la douane de ton pays jusqu’aux propres scans du transporteur final.

## Numéro de suivi Yilong Logistics : à quoi il ressemble

Yilong ne publie pas de règle de format. Les lettres de transport reconnues par son suivi comportent `YIL`, neuf chiffres puis `WL`. Trois exemples fictifs :

| Numéro | Ce que c’est |
| --- | --- |
| `YIL482917365WL` | `YIL`, neuf chiffres, `WL` : une lettre de transport Yilong Logistics. |
| `YIL48291736WL` | Huit chiffres au lieu de neuf : un chiffre a probablement été omis. |
| `RR482917367CN` | Deux lettres, neuf chiffres, `CN` : un numéro [China Post](carrier:china-post), pas un numéro Yilong. |

Peek accepte aussi le code en minuscules ou avec des espaces. Si le vendeur ne t’en a pas envoyé, voir [où trouver ton numéro de suivi](guide:find-tracking-number).

**La boîte de suivi de Yilong.** Elle demande « 订单号或追踪号 » (numéro de commande ou numéro de suivi), un par ligne, et sa page de résultats en accepte jusqu’à 30 à la fois. Comme elle envoie les numéros par formulaire, les résultats n’ont pas d’adresse web propre : il n’existe aucun lien de suivi Yilong à enregistrer ou partager.

## Statuts de suivi Yilong Logistics expliqués

Les scans de Yilong sont en anglais, à l’exception d’une ligne en chinois générée automatiquement par sa plateforme de suivi Hualei (华磊). Dans l’ordre habituel :

| Libellé Yilong | Ce que ça veut dire pour toi |
| --- | --- |
| `货物电子信息已经收到` | « Informations électroniques d’expédition reçues » : la commande existe, mais Yilong n’a pas encore scanné le colis. |
| `Package received and scanned at warehouse` | L’entrepôt de Yilong a reçu le colis et indique le lieu : c’est le premier scan entre ses mains. |
| `Package departed from the warehouse` | Le colis a quitté l’entrepôt (cette ligne précise aussi le lieu). |
| `Customs export clearance completed` | Le dédouanement export chinois est terminé (ce n’est pas encore la douane de ton pays). |
| `Package handed over to airline` | Remis à une compagnie aérienne pour le vol. |
| `Package in transit to destination country` | Le vol est en route vers ton pays. |
| `Package arrived at destination country` | Le colis est arrivé dans ton pays. |
| `Flight arrived at destination country port` | L’avion a atterri dans ton pays. |
| `Customs import clearance completed, awaiting pickup by local carrier` | La douane d’importation de ton pays l’a libéré ; le transporteur local ne l’a pas encore collecté. |
| `Package picked up by last-mile carrier` | Le transporteur local a pris en charge le colis. Les lignes suivantes viennent de lui, dans ses propres mots. |

Les étapes de douane et de vol n’indiquent aucun lieu et aucune ligne ne précise de fuseau horaire : les heures sont celles de Pékin, donc un scan à 9 h 00 à Pékin correspond à la veille au soir à New York.

Pour des envois vers les États-Unis, les scans du transporteur final peuvent être ceux de SwiftX : `Arrived at Origin Hub` (premier hub américain), `Departed Destination Hub`, `Arrived At Warehouse` (entrepôt de départ des tournées), `Out for Delivery` et `Delivery failed` suivi de la note du livreur et du numéro d’aide de SwiftX. Le libellé `Shipping Label Created` de SwiftX apparaît parfois dans cet historique, daté juste après le scan d’entrepôt en Chine.

## Qui livre les colis Yilong Logistics

Le dossier de suivi de Yilong n’indique ni le nom du transporteur final ni un second numéro de suivi, et son site ne précise pas qui livre selon les pays. L’historique peut tout de même relayer les scans du transporteur local après `Package picked up by last-mile carrier` (par exemple ceux de SwiftX sur certains colis américains). Les règles de tentatives de livraison, points relais et délais de garde dépendent entièrement de ce transporteur final ; Yilong n’en publie aucune. Demande au vendeur qui prend le relais après le vol et sous quel numéro. Si des droits d’importation sont demandés, voir [colis bloqué à la douane](guide:customs).

## Colis Yilong Logistics qui n’arrive pas ou suivi bloqué

**Bloqué sur `Package handed over to airline`.** Viennent ensuite le vol et la douane d’importation de ton pays : quelques jours de silence ne signifient donc pas que le colis est perdu. Attends la fin du délai estimé par la boutique avant de contacter le vendeur. Voir [suivre un colis depuis la Chine](guide:tracking-from-china) et [suivi qui ne se met pas à jour](guide:tracking-not-updating).

**Uniquement la ligne en chinois.** Le vendeur a enregistré la commande, mais Yilong n’a pas encore reçu le colis. Sans scan d’entrepôt au bout de quelques jours, vérifie auprès du vendeur que le colis a bien été remis.

**Rien de trouvé.** Lorsque le suivi de Yilong ne connaît pas un numéro, sa réponse n’affiche que ce numéro sous 参考号 (référence). Peek interprète cette réponse comme un envoi créé sans premier scan : il continue de vérifier (pendant les 6 premières heures sans compte, puis à l’ouverture du lien et jusqu’à 30 jours si les notifications sont activées) et affiche « En attente de Yilong Logistics », sans te demander de vérifier le numéro. Compare donc le code caractère par caractère avec le message du vendeur.

**Perdu, endommagé ou retourné.** Le site de Yilong ne comporte ni formulaire de réclamation ni conditions générales. Comme le vendeur est le client de Yilong, c’est à lui d’organiser un renvoi ou un remboursement ; s’il ne répond pas, ouvre un litige sur la marketplace. Voir [colis perdu](guide:lost-parcel) et [retourné à l’expéditeur](guide:returned-to-sender).

## Service client et contact Yilong Logistics

Sur yxhlexpress.com, entièrement en chinois :

- **Pages :** 联系我们 (contact), 客服中心 (service client) et 公司介绍 (présentation) n’affichent que leur titre, sans contenu.
- **WeChat :** un QR code intitulé 官网微信二维码 (QR code WeChat officiel) dans le panneau latéral et le pied de page.
- **Téléphone :** un numéro de mobile chinois non libellé en bas du panneau latéral, sans indication de service, d’horaires ni de langue.

Il n’y a ni adresse e-mail, ni adresse postale, ni rubrique destinée aux destinataires : le reste du site s’adresse aux expéditeurs (connexion et inscription sous 寄件下单 et calculateur de tarifs). Pour toute question sur ta commande, contacte le vendeur.

## Suivre un colis Yilong Logistics avec Peek

Colle un numéro `YIL…WL` dans [Peek](/) pour qu’il sélectionne immédiatement Yilong Logistics : aucun autre transporteur connu de Peek n’utilise ce format. Peek suit plus de 4 500 transporteurs, mais une référence de commande de vendeur n’a pas de format propre : ajoute-la quand même, appuie sur « Changer » et choisis Yilong Logistics (rechercher 一龙物流 ou YXHL la trouve aussi), ou utilise « Changer de transporteur » sur un colis déjà ajouté. Si le suivi de Yilong reconnaît la référence, Peek affiche aussi le numéro `YIL` correspondant ; Peek supprimant toutefois les tirets, saisis une référence avec tirets directement dans le champ de Yilong.

Peek lit directement le suivi de Yilong. Il affiche chaque ligne dans les mots de Yilong (y compris la ligne en chinois), le lieu sur les deux scans d’entrepôt et le pays de destination lorsqu’il figure au dossier. L’entrée en entrepôt correspond à « Pris en charge », les étapes jusqu’à l’entrepôt du livreur à « En transit », `Shipping Label Created` à « Étiquette créée », `Out for Delivery` à « En livraison » et `Delivery failed` à « Tentative de livraison ». Les heures de Pékin sont affichées dans ton fuseau horaire (l’historique se termine par « Heures dans ton fuseau horaire : » suivi de son nom). Noms, adresse, codes postaux, poids, frais, référence vendeur, signature et preuve de livraison restent hors de Peek, et aucune date de livraison estimée n’est affichée puisque Yilong n’en donne pas.

Toute ligne datée dans le futur selon l’heure de Pékin est retenue jusqu’à cette date. Peek écarte aussi toute ligne enregistrée plus de douze heures avant l’heure qu’elle porte (ou plus de six heures avant dans la même seconde qu’une telle ligne), car il s’agit d’une prévision et non d’un vrai scan. Dans ce cas, Peek prévient que « Le site de Yilong Logistics affiche tout le trajet à l’avance, livraison comprise. Ces étapes ne sont pas de vrais scans. » — et, pour une livraison inscrite d’avance, « Le site de Yilong Logistics affiche une livraison inscrite à l’avance, pas un vrai scan. Si rien n’est arrivé, contacte le vendeur. ». Comme Yilong ne nomme pas de transporteur final, Peek suit le colis uniquement jusqu’à la fin de l’historique Yilong : ajoute séparément le numéro local si le vendeur te le communique.

Tant que tu gardes l’écran ouvert dans Peek — ou le jour de la livraison si les notifications sont activées — Peek interroge directement Yilong jusqu’à toutes les 10 minutes, et jusqu’à toutes les 2 minutes dès 6 h heure locale le jour de la mise en livraison ; sinon, ainsi qu’après deux jours sans nouveauté (12 heures tant que le colis reste sur « Étiquette créée » ou « Pris en charge »), il vérifie toutes les heures. Via les seuls services de suivi, c’est toutes les heures, toutes les 3 heures après une journée sans scan, et jusqu’à toutes les 15 minutes le jour de la livraison si le colis est ouvert à l’écran ou si les notifications sont activées. Les étapes propres à Yilong, douane d’importation comprise, relèvent du transit : seule l’alerte « Chaque scan » les notifie, tandis que « Étapes importantes » et « Livraison seulement » démarrent à partir de `Out for Delivery` du livreur. Peek ne peut pas relancer Yilong ou le vendeur pour toi.

## Questions sur le suivi Yilong Logistics

### Qu’est-ce que Yilong Logistics (一龙物流) ?

C’est 莆田市城厢区一龙物流有限公司, un transitaire du district de Chengxiang à Putian (Fujian, Chine) dont le site yxhlexpress.com décrit une activité d’exportation internationale, principalement sur une ligne dédiée aux États-Unis (主打美国专线). Ses numéros suivent le format `YIL`, neuf chiffres et `WL`.

### Pourquoi mon suivi Yilong s’arrête-t-il à « Package handed over to airline » ?

Parce que le vol suit cette étape, puis vient la ligne `Package in transit to destination country`, suivie de l’arrivée dans ton pays, de la douane d’importation et des scans du transporteur local. Comme Yilong n’indique pas ce transporteur, demande son nom et son numéro au vendeur.

### Combien de temps met Yilong Logistics pour livrer ?

Yilong ne publie aucun délai de livraison. La liste sous son champ de recherche — un numéro masqué répété avec « 5天 » (5 jours) et une date de 2022 — ne concerne pas ton colis. Fies-toi à l’estimation indiquée par la boutique.

:::sources
- [Yilong Logistics : 莆田市城厢区一龙物流有限公司 (accueil)](http://www.yxhlexpress.com/) – raison sociale, enregistrement ICP du Fujian (闽ICP备2025094126号), export international et ligne principale US, connexion/tarifs, champ 包裹追踪, QR code WeChat, numéro de mobile latéral
- [Yilong Logistics : 包裹追踪 (résultats de suivi)](http://www.yxhlexpress.com/track.aspx) – jusqu’à 30 numéros par recherche, un par ligne
- [Yilong Logistics : 公司介绍 (présentation)](http://www.yxhlexpress.com/about.html) – page sans contenu
- [Yilong Logistics : 联系我们 (contact)](http://www.yxhlexpress.com/contact.html) – page sans contenu
- [Yilong Logistics : 客服中心 (service client)](http://www.yxhlexpress.com/kfzx.html) – page sans contenu
- [Universal Parcel Scraper : adaptateur Yilong Logistics](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/yilong-logistics) – plateforme Hualei, format YIL…WL, libellés et étapes, scans SwiftX, fuseau de Pékin, réponse inconnue, retenue des étapes inscrites à l’avance et champs exclus
:::
