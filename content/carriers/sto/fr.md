---
title: Suivi STO Express : numéros 申通快递, statuts et contact
description: Suivi d’un colis STO Express (申通快递) en Chine : formats de numéros, signification de 签收 et des scans STO, points relais, consignes et contacts.
slug: suivi-sto-express
published: 2026-11-22
updated: 2026-11-22
---

STO Express (申通快递, Shentong) est un transporteur express chinois fondé en 1993 qui se présente comme la première société privée de livraison express de Chine. Un numéro STO appartient généralement à un colis circulant en Chine, souvent une commande en ligne acheminée vers toi, un proche ou l’entrepôt d’un transitaire. Suis-le sur [le site de STO](https://www.sto.cn/pc), dans la case `查快递` (suivre un colis), uniquement en chinois, ou dans la case ci-dessus.

## Numéro de suivi STO Express : à quoi il ressemble

Les numéros STO (`快递单号`) sont composés uniquement de chiffres. Le suivi STO de Peek accepte 12, 13 ou 15 chiffres ; les bordereaux les plus récents comptent 15 chiffres. Avec des chiffres inventés :

| Numéro (inventé) | Ce que c’est |
| --- | --- |
| `774829173654281` | `77` et 13 chiffres : un bordereau STO Express. [BRT](carrier:brt) et Yunda utilisent aussi 15 chiffres, donc Peek vérifie auprès de STO. |
| `482917365428` | 12 chiffres : un ancien format STO, partagé avec [FedEx](carrier:fedex), [SF Express](carrier:sf-express), [ZTO Express](carrier:zto) et bien d’autres. |
| `4829173654281` | 13 chiffres : un autre ancien format STO, que Peek n’associe pas automatiquement à STO. |

Les espaces n’ont aucune importance dans Peek.

**Où le trouver.** Le vendeur le fournit dans les informations d’expédition de la commande, et il figure sur le bordereau (`快递面单`) collé au colis. Si tu ne le trouves pas, consulte [où trouver ton numéro de suivi](guide:find-tracking-number).

**Sur le site de STO.** Saisis le numéro dans la case `查快递` sur `sto.cn` et appuie sur `查询` (rechercher). Les conditions de service de STO précisent que son site web, son application et son compte WeChat conservent le suivi pendant un an à compter de la date d’envoi.

## Signification des statuts de suivi STO Express

Chaque scan dans le suivi de STO comporte un type en chinois, une heure à l’heure de Chine (UTC+8) et le nom du site STO ayant effectué le scan. Peek affiche ces types en anglais :

| Scan STO | Dans Peek | Statut dans Peek | Ce que ça veut dire pour toi |
| --- | --- | --- | --- |
| `收件` | `Picked up` | Pris en charge | STO a collecté le colis chez l’expéditeur. |
| `发件` | `Departed`, suivi de `for` et du site suivant quand STO le nomme | En transit | A quitté un site STO pour le suivant. |
| `到件` | `Arrived` | En transit | Arrivé dans un centre de tri ou une agence. |
| `派件` | `Out for delivery` | En livraison | Un livreur l’a pris en tournée. |
| `驿站代收` | `At a parcel station for collection` | Prêt à être retiré | Un point relais (`驿站`) l’a réceptionné pour toi. Va le retirer. |
| `柜机代收` | `In a parcel locker for collection` | Prêt à être retiré | Placé dans une consigne automatique. Va le retirer. |
| `客户签收` | `Delivered` | Livré | Quelqu’un a signé. STO enregistre aussi cela lorsqu’un point relais ou une consigne signe pour le colis. |
| `签收` | `Delivered` | Livré | Signé. |

Les autres types de scan conservent le libellé chinois de STO dans Peek. Un type mentionnant un retour (`退回` ou `退件`) apparaît comme `Return to the sender started` (« Problème signalé »). Les scans de livraison qui suivent concernent le trajet retour : `Out for delivery back to the sender`, `In a parcel locker or station on its way back` et `Returned to the sender`.

**Envoyé vers un transitaire ?** Dans ce cas, `签收` signifie que l’entrepôt du transitaire a signé pour réception et que la part de STO est terminée. Le numéro propre au transitaire prend ensuite le relais.

## Livraison STO Express en Chine : livreurs, points relais et consignes

STO indique que son réseau couvre 99,7 % des cantons chinois. Son contrat de service l’autorise à livrer un colis de quatre façons :

- à toi directement ;
- à une personne vivant avec toi, ou à un tiers que tu as désigné ;
- dans un point de collecte (`代收点`) ou une consigne (`自提柜`) dans le secteur de l’adresse ;
- selon toute autre modalité demandée par toi ou par l’expéditeur.

Une fois le colis remis selon l’une de ces modalités avec un emballage extérieur intact, le contrat considère la prestation de STO comme accomplie. Le statut « Prêt à être retiré » dans Peek est donc normal : le colis attend dans un point colis ou une consigne près de l’adresse. L’adresse complète du point relais se trouve dans le suivi propre de STO ; tant que le colis attend en point colis ou en consigne, Peek lit le code de retrait (`取件码`) lorsqu’il figure sur le dernier scan.

Pour modifier les coordonnées du destinataire, le service client en ligne de STO propose un thème dédié (`修改收件人信息`) : fais-en la demande là-bas (contact ci-dessous) ou auprès du vendeur.

## Colis STO sans mise à jour, perdu ou retourné

**Rien pour l’instant.** Le suivi STO ne renvoie rien pour un numéro qu’il ne connaît pas encore, ni au-delà de sa limite d’un an. S’il reste vide, vérifie le numéro auprès du vendeur. En attendant, un colis enregistré sous STO Express affiche « En attente de STO Express ».

**Aucune mise à jour.** Lorsqu’un colis encore en route n’a pas eu de nouveau scan depuis 4 jours, Peek affiche la date du dernier scan (« Aucune mise à jour depuis … ») et « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger STO Express. », avec le lien « Demande de l’aide à STO Express » vers le service client en ligne de STO, où l’absence de mise à jour (`快递单号没有记录更新`) figure parmi les sujets proposés. Voir [pourquoi un suivi ne se met plus à jour](guide:tracking-not-updating) et [suivre des colis venant de Chine](guide:tracking-from-china).

**Perdu ou abîmé.** Adresse-toi d’abord au vendeur : c’est lui qui a contracté STO, pas toi. Selon le contrat de service de STO, l’expéditeur — ou une personne qu’il désigne — dépose sa réclamation dans les 30 jours suivant la découverte de la perte ou du dommage, avec justificatifs. Sans assurance (`保价`), STO indemnise la perte réelle jusqu’à 500 yuans, ou 100 yuans pour des documents. Un colis assuré est indemnisé jusqu’à sa valeur déclarée. Sans assurance ni preuve de valeur, STO verse trois fois les frais de port. En cas de retard dû à STO, les frais de port sont annulés. Voir [colis perdus](guide:lost-parcel).

**Retourné.** STO peut renvoyer un colis lorsque l’adresse est imprécise, que le destinataire le refuse ou que deux tentatives de livraison échouent sans faute de STO ; les frais sont à la charge de l’expéditeur. Dans Peek apparaît `Return to the sender started`, puis le trajet retour. Voir [retourné à l’expéditeur](guide:returned-to-sender).

**Livré, mais introuvable.** `签收` et `客户签收` signifient que quelqu’un a signé : toi, un membre de ton foyer, un tiers désigné ou un point colis/consigne ayant réceptionné l’envoi pour toi. Vérifie les points relais et consignes proches, la réception de l’immeuble et, le cas échéant, ton transitaire. Pour réclamer, utilise le bouton de plainte (`投诉反馈`) du service client en ligne de STO. Voir [livré mais non reçu](guide:delivered-not-received).

## Contact et service client STO Express

Canaux publiés par STO :

- **Service client en ligne** sur `page.sto.cn`, accessible depuis `sto.cn` via `在线咨询` (demande en ligne) : assistant de discussion avec conseillers humains de 8 h 30 à 22 h (heure de Pékin). Propose des boutons pour envoyer un colis, vérifier les tarifs et déposer une plainte (`投诉反馈`).
- **WeChat :** un compte de service et un mini-programme pour envoyer des colis. STO est aussi présent sur Douyin. Tous trois figurent en bas de `sto.cn`.

Cette page n’indique aucun numéro de téléphone : Peek ne cite que les contacts publiés dans le texte des pages officielles de STO, et aucune n’en indique. Pour la commande elle-même, contacte le vendeur.

## Suivre un colis STO Express avec Peek

La forme seule d’un numéro STO ne suffit pas à prouver qu’il s’agit de STO. Colle-le tout de même : [Peek](/) suit plus de 4 500 transporteurs.

Pour un numéro en `77` de 15 chiffres, Peek interroge aussitôt STO Express et BRT tout en affichant « Vérification des services de suivi… ». Si STO a le colis, la ligne sous le numéro affiche « STO Express a ce colis. » et Peek l’enregistre sous STO. Sinon, il indique « pas encore trouvé · Peek continue de vérifier ». Ajoute-le quand même : le colis affiche « Probablement STO Express » et « En attente du premier scan de STO Express », et Peek continue d’interroger STO toutes les 6 heures après les premières heures dans l’application iPhone ou Android ou dans ton compte. Pour une consultation sans compte, Peek vérifie pendant les 6 premières heures, puis à l’ouverture du lien (au plus toutes les 6 heures, puis une fois par jour après deux jours) et, avec notifications activées, une fois par jour pendant 30 jours. Après une semaine sans rien, il te demande de vérifier le numéro auprès de l’expéditeur ou de choisir un autre transporteur.

Peek n’interroge automatiquement STO que pour les numéros en `77`. Pour un numéro de 12 chiffres, il interroge certains autres transporteurs utilisant cette forme (dont DHL et BRT), puis les services de suivi ; si l’un d’eux trouve le colis et nomme STO Express, Peek le classe sous STO Express et l’interroge ensuite directement.

Pour que Peek lise directement le suivi STO dès le départ, appuie sur « Changer » et choisis STO Express sous « Correspond à ce numéro » (12 chiffres, ou 15 commençant par `77`). Pour 13 chiffres ou 15 ne commençant pas par `77`, choisis-le sous « Tous les transporteurs » ou recherche `STO` ou `申通`. Sur un colis déjà ajouté, utilise « Changer de transporteur ». Le suivi STO de Peek n’accepte que 12, 13 ou 15 chiffres : si tu sélectionnes STO Express pour une autre longueur, Peek indique « n’émet pas de numéros comme celui-ci ».

Peek lit le suivi propre de STO, celui qui alimente son service client en ligne. Pour chaque scan, il affiche l’étape (tableau ci-dessus), la ville du site STO (son nom chinois si Peek ne peut pas la situer) et l’heure, lue sur l’heure de Chine et affichée dans ton fuseau horaire (« Heures dans ton fuseau horaire : » suivi de son nom). Un scan répété n’apparaît qu’une fois, et le poids s’affiche lorsque les balances de STO l’ont relevé. Il lit aussi le code de retrait (`取件码`) du dernier scan de point relais ou consigne tant que le colis y attend, ainsi que le nom du signataire à la livraison, mais laisse de côté le texte complet de STO, les noms et numéros de téléphone des livreurs et agences ainsi que les adresses de retrait. STO ne fournissant pas de date estimée de livraison, Peek n’en affiche pas. Peek ne suit que le numéro STO : le numéro d’un transitaire s’ajoute comme un autre colis.

Tant que tu as le colis ouvert dans Peek — ou le jour de la livraison si les notifications sont activées — Peek interroge directement STO jusqu’à toutes les 10 minutes, et jusqu’à toutes les 2 minutes dès 6 h, heure de Pékin, le jour où STO scanne le colis « En livraison », tant que le suivi de STO répond ; sinon, et après deux jours sans nouveauté (12 heures tant qu’il reste sur « Étiquette créée » ou « Pris en charge »), il vérifie toutes les heures. Il peut t’alerter pour « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Il ne peut pas contacter STO, le transitaire ou le vendeur pour toi.

## Questions sur le suivi STO Express

### STO Express est-il fiable ?

Oui. Fondé en 1993, STO Express se présente comme la première entreprise privée de livraison express de Chine et pionnière de la franchise dans ce secteur. Cotée sur le marché chinois d’actions A, la société annonce 75 centres de tri, 5 075 agences indépendantes et plus de 98 000 boutiques locales.

### Que signifie 签收 sur le suivi STO ?

Signé pour réception : Peek affiche `签收` et `客户签收` comme « Livré ». Toi, un proche à ton adresse, une personne désignée ou un point relais/consigne a signé. `驿站代收` et `柜机代收` indiquent qu’un point relais ou une consigne conserve le colis pour ton retrait.

### Pourquoi mon numéro STO n’affiche-t-il aucun suivi ?

STO n’a pas encore scanné le colis, le numéro comporte une erreur ou le colis a été envoyé il y a plus d’un an : STO ne conserve le suivi qu’un an. Vérifie le numéro auprès du vendeur ou ajoute-le dans Peek, qui continue de vérifier.

### Où trouver le code de retrait de mon colis STO ?

Tant que le colis attend dans un point relais ou une consigne, Peek extrait le code de retrait (`取件码`) du dernier scan lorsqu’il y figure, et affiche `At a parcel station for collection` ou `In a parcel locker for collection`. L’adresse exacte du point relais ou de la consigne reste dans le suivi STO sur `sto.cn`.

### Comment contacter STO Express ?

Via le service client en ligne sur le site de STO : des conseillers répondent de 8 h 30 à 22 h (heure de Pékin), avec un bouton de réclamation (`投诉反馈`). Pour une commande en ligne, adresse-toi d’abord au vendeur.

:::sources
- [STO Express: 申通快递官网 (home)](https://www.sto.cn/pc) – champ de suivi, compte de service et mini-programme WeChat, Douyin
- [STO Express: 关于我们 (about STO)](https://www.sto.cn/pc/about) – fondé en 1993, première société privée de livraison express, modèle de franchise, cotation actions A, centres de tri, agences, boutiques, couverture des cantons
- [STO Express: 申通快递服务协议 (express service agreement)](https://www.sto.cn/h5/standard) – suivi pendant un an, modalités de livraison, retours, indemnisation avec et sans assurance, réclamation sous 30 jours, retard de livraison
- [STO Express: 在线客服 (online customer service)](https://page.sto.cn/ued-projects/sto-customer-onlinekf?fromSource=pc-stocn&pc=true) – chat, conseillers humains 8 h 30–22 h, réclamations, sujets absence de mise à jour et modification des coordonnées du destinataire
- [Universal Parcel Scraper: STO Express](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/sto) – formats de numéros, types de scan, retours, heure de Chine, poids, code de retrait, signataire, champs affichés et omis, absence de date prévue
:::
