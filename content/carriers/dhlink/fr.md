---
title: Suivi colis DHLink : numéro DHgate, statuts et livraison
description: Suis ton colis DHLink d'une commande DHgate : quels numéros le suivi accepte, ce que veulent dire les statuts en anglais, qui livre chez toi et qui contacter.
slug: dhlink-suivi
published: 2026-11-14
updated: 2026-11-14
---

DHLink (驼飞侠) est la plateforme logistique transfrontalière de DHgate (敦煌网). Fondée en 2014, elle expédie les commandes des vendeurs DHgate et d'autres clients depuis la Chine ou depuis ses entrepôts à l'étranger, puis les confie à un transporteur dans ton pays pour le dernier kilomètre. Colle ton numéro dans le champ ci-dessus ou saisis-le sur la [page de suivi de DHLink](https://www.dhlink.com/trackQuery?lang=en). Tu trouveras ce numéro dans le détail de ta commande DHgate ; sinon, demande-le au vendeur.

## Numéro de suivi DHLink : à quoi il ressemble

DHLink ne publie pas de format officiel pour ses numéros. Son outil de suivi prend jusqu'à 40 numéros à la fois et accepte ce qu'il appelle le *Global Tracking No.*, le *Logistics Delivery No.* et le *DHLink pickup No.* Voici les formes rencontrées sur des colis DHLink, avec des chiffres inventés :

| Numéro | Ce que c'est |
| --- | --- |
| `FC0000000000000` | `FC` et 13 chiffres : le propre numéro logistique de DHLink, affiché comme « DHLink No. ». Sur certaines lignes, c'est aussi le numéro de suivi. |
| `XZG000000000WL` | `XZG`, 9 chiffres et `WL` : le numéro d'un partenaire de ligne, que DHLink reconnaît comme numéro de suivi. |
| `YLUS0000000000` | `YLUS` et 10 chiffres : une ligne vers les États-Unis livrée par GOFO. |
| `QH000000000000FS` | `QH`, 12 chiffres et `FS` : une autre ligne vers les États-Unis livrée par GOFO. |
| `EYT0000000000HZ` | `EYT`, 10 chiffres et `HZ` : une ligne vers les États-Unis livrée par SwiftX. |
| `CYS00000000000` | `CYS` et 11 chiffres : une ligne vers les États-Unis livrée par [UniUni](carrier:uniuni). |
| `JXCYI0000000000YQ` | `JXC`, `YI` ou `ZO`, 10 chiffres et `YQ` : une forme de bordereau de [Juxi Express](carrier:juxi-express) à laquelle le suivi de DHLink répond aussi pour certains colis vers les États-Unis. Peek interroge DHLink en premier pour cette forme ; les autres numéros `JXC` vont à Juxi Express. |
| `RR000000005CN` | Deux lettres, 9 chiffres et `CN` : un numéro [China Post](carrier:china-post). Certains colis DHLink voyagent avec China Post sous ce numéro. |
| `GFUS00000000000000` | Ce n'est pas un numéro DHLink : c'est un numéro GOFO Express, utilisé pour le dernier kilomètre de certaines lignes américaines. |

Sur un résultat, DHLink affiche le « DHLink No. » et le « Tracking No. », ainsi qu'un « Last Leg No. » quand un autre transporteur assure la livraison finale. Si le numéro de suivi diffère du numéro DHLink, cherche avec le numéro de suivi : interrogé avec le seul numéro `FC`, un colis doté d'un autre numéro de suivi peut ne rien renvoyer. Sans `lang=en` dans l'adresse, la page de suivi s'ouvre en chinois.

## Statuts du suivi DHLink : ce qu'ils veulent dire

La page de suivi regroupe les envois sous `In transit`, `Delivered` ou `Not found`, indique les codes des pays de départ et de destination ainsi que chaque scan avec son heure, et ajoute une mention `Signed for` une fois le colis livré. Comme les lignes viennent de plusieurs flux (celui de DHLink, celui de la poste et ceux des partenaires de ligne), un même événement apparaît souvent en double sur le site de DHLink. Voici les messages anglais les plus courants, dans l'ordre du trajet :

| DHLink écrit | Ce que ça veut dire pour toi |
| --- | --- |
| `Data Received; Data transferred and received by carrier` | Sur une ligne vers l'Europe du Sud-Est : Pactic, la plateforme hongroise par laquelle transite la ligne, a reçu les données du colis. Avant le scan d'entrepôt de DHLink, Peek affiche « Étiquette créée » ; si la ligne réapparaît après le vol, le colis reste « En transit ». |
| `Arrive at the collection warehouse` | Le colis est arrivé à l'entrepôt de collecte de DHLink en Chine. |
| `The collection warehouse has completed sorting` | Le tri est terminé dans cet entrepôt. |
| `Package received & scanned at warehouse` | Le colis est enregistré et scanné à l'entrepôt DHLink. |
| `Parcel handed over to carrier,waiting for transportation` | Le point de collecte l'a pris en charge et il attend le départ. |
| `Package departed from the warehouse`, `The package has sent from the warehouse` | Il a quitté l'entrepôt. |
| `Warehouse scanning bagging`, `Master airway bill number received`, `Leave the warehouse` | Sur certaines lignes : mise en sac en Chine pour le vol, réception de la lettre de transport aérien principale (*master air waybill*), puis départ de l'entrepôt (« En transit » dans Peek). |
| `Customs export clearance completed`, `Package handed over to airline` | La douane chinoise d'exportation l'a libéré, puis la compagnie aérienne l'a pris en charge. |
| `Flight departure`, `The flight has taken off` | L'avion a décollé de Chine. |
| `Shipment Departed From Original Country at …`, `Departed from airport of origin` | Même étape : le vol a quitté la Chine (« En transit » dans Peek). |
| `The flight has arrived and the board is being dismantled`, `Dismantling the board` | L'avion a atterri et les palettes aériennes sont en cours de dégroupage à l'aéroport d'arrivée (« En transit » dans Peek). |
| `Shipment has arriveved at destination Country at …` | Arrivé dans le pays de destination (la faute d'orthographe vient de DHLink). |
| `Arrived to … hub`, `Linehaul transit; Parcel has entered the linehaul network.` | Après le vol, passage par le hub européen d'un partenaire (désigné par un code à la place des points de suspension, comme `HU` en Hongrie pour Pactic), puis le transport vers l'étape suivante (« En transit » dans Peek). |
| `Customs clearance completed`, `Customs import clearance completed, awaiting pickup by local carrier` | Le dédouanement à l'importation est terminé ; le transporteur local va récupérer le colis. |
| `UPS/USPS/Fedex` | La remise au transporteur américain approche. |
| `货物已交UPS/USPS/Fedex,The goods have been delivered to UPS/USPS/Fedex` | Remis à [UPS](carrier:ups), USPS ou [FedEx](carrier:fedex) aux États-Unis. Ce n'est pas encore livré chez toi. |
| `Package picked up by last-mile carrier` | Le transporteur chargé du dernier kilomètre dans ton pays a récupéré le colis. |
| `Scheduled for Delivery` | En tournée de livraison. |
| `Shipment has delivered at …` | Livré. |

Les lignes vers les États-Unis ajoutent des scans comme `Arrived at Origin Hub`, `Departed Destination Hub` ou `Shipping Order Created.` (« Étiquette créée » dans Peek, environ une heure avant l'entrée à l'entrepôt). Un centre du transporteur final peut écrire `Parcel received by ABC01.` (avec son propre code à la place de `ABC01`), puis `Parcel departed from Facility.` quelques minutes plus tard, tous deux « En transit ». Viennent ensuite les scans du livreur final, par exemple `Arrived at GOFO Delivery Station` chez GOFO, `No Access to Delivery Location` chez USPS (tentative infructueuse), ou chez [UniUni](carrier:uniuni) `Parcel received at UniUni sorting hub.`, `Parcel processed.` et `Parcel scanned and in transit.` Sur les lignes [China Post](carrier:china-post), certains scans n'apparaissent qu'en chinois : `中国邮政 已收取邮件` (pris en charge par China Post), `送交出口海关` (présenté à la douane d'exportation) et `已妥投` après le code du pays (livré). Si DHLink ne connaît pas le numéro, la page affiche `There is no track for this waybill number`.

## Qui livre les colis DHLink dans ton pays

Le site de DHLink s'adresse aux vendeurs et ne publie aucune liste de transporteurs partenaires par pays : c'est le suivi de ton envoi qui indique qui prend le relais. En Suisse, le « Last Leg No. » peut être un numéro de [DPD Suisse](carrier:dpd-ch) à 14 chiffres commençant par `060` ou `061`. Sur les lignes américaines, un scan annonce parfois une remise à « UPS/USPS/Fedex » sans préciser lequel, avant que les scans de ce transporteur ne prennent la suite ; d'autres lignes passent à GOFO Express (dont le propre suivi reconnaît aussi les numéros `FC` à 13 chiffres des colis DHgate qu'il livre) ou à SwiftX. Les colis confiés à [China Post](carrier:china-post) gardent leur numéro en `CN` jusqu'au bout : consulte la page China Post pour voir quelle poste nationale les distribue en France, en Belgique ou en Suisse.

Une fois le transporteur local et son numéro identifiés, son propre suivi donne le détail de la tournée, de l'avis de passage ou du point de retrait : voir aussi [livraison manquée](guide:missed-delivery).

**Douane et frais dans l'UE et en Suisse.** Dans l'Union européenne (France, Belgique), la franchise de droits de douane jusqu'à 150 € a disparu le 1er juillet 2026 au profit d'un droit de douane forfaitaire provisoire de 3 € par article (par type de produit, pas par pièce) sur les ventes à distance importées jusqu'à 150 €, auquel s'ajoutent depuis le 1er novembre 2026 des frais de traitement européens de 2 € par article, comptés de la même façon, quelle que soit la valeur de l'envoi. Ces montants sont dus par le déclarant en douane dès l'acceptation de la déclaration. En Suisse, hors de l'UE, l'Office fédéral de la douane et de la sécurité des frontières (OFDF) rappelle que tout envoi venant de l'étranger est en principe soumis aux droits de douane et à la TVA : le transporteur assure le dédouanement, perçoit des frais de dédouanement et reste ton interlocuteur pour toute question sur ces frais ou sur la recherche du colis. Plus d'explications dans [colis bloqué en douane](guide:customs).

## Colis DHLink bloqué, introuvable ou marqué livré sans rien recevoir

**Numéro introuvable.** Vérifie le numéro dans ta commande DHgate et essaie le numéro de suivi (*Tracking No.*) plutôt que le numéro `FC` de DHLink. Un numéro du transporteur final ne donne rien sur le site de DHLink : cherche-le directement chez ce transporteur.

**Suivi figé après le vol ou la douane.** Regarde si un « Last Leg No. » est apparu et interroge le suivi du transporteur local, souvent plus à jour que celui de DHLink. Voir aussi [pourquoi un suivi ne bouge plus](guide:tracking-not-updating) et [suivre un colis venu de Chine](guide:tracking-from-china).

**Marqué livré mais absent, en retard ou perdu.** Les règles d'indemnisation de DHLink s'adressent aux marchands : c'est le vendeur qui dépose une demande auprès de DHLink, tandis que toi, tu passes par le vendeur ou par DHgate. Ces règles excluent les retards ou échecs causés par l'acheteur, notamment un colis non retiré ou non signé à temps, ou un dédouanement qui échoue faute d'aide du destinataire dans les délais. Réponds vite aux demandes de la douane ou du transporteur et préviens tôt le vendeur : il ne dispose que de 90 jours calendaires à compter de la création de la commande logistique pour réclamer. Voir [colis livré mais non reçu](guide:delivered-not-received) et [colis perdu](guide:lost-parcel).

## Contacter DHLink : service client

Telle que consultée le 14 novembre 2026, la page de contact de DHLink indique une seule adresse e-mail de service client, `dhlinkservice@dhlink.com`, ainsi qu'un accès de demande en ligne sur la plateforme et l'assistant de discussion « AI 小飞 ». La page est en chinois et ne mentionne aucun numéro de téléphone. Pour un souci sur ta commande, contacte d'abord le vendeur depuis DHgate ; pour la livraison finale dans ton pays, adresse-toi au transporteur qui livre le colis.

## Suivre un colis DHLink avec Peek

[Peek](/) ne peut pas identifier DHLink à coup sûr d'après la seule forme du numéro : les séries `FC`, `XZG`, `YLUS`, `QH`, `EYT`, `CYS`, `JXCYI` et `JXCZO` sont des correspondances faibles. Colle quand même ton numéro : Peek suit plus de 3 500 transporteurs, affiche « Vérification des services de suivi… », puis indique « DHLink a ce colis. » dès que DHLink répond ; sinon, appuie sur « Changer » pour choisir DHLink. Un numéro `JXCYI` ou `JXCZO` peut aussi venir de [Juxi Express](carrier:juxi-express) : Peek interroge DHLink en premier et, tant qu'aucun des deux ne répond, présente le colis sous « Probablement DHLink » ; si le vendeur a expédié avec Juxi, passe à Juxi Express via « Changer ». Un numéro en `CN` va à [China Post](carrier:china-post).

Peek lit directement le suivi de DHLink, affiche le statut, le pays de destination et chaque étape une seule fois (telle que DHLink l'écrit, en anglais le plus souvent), en écartant les doublons entre flux ainsi que les codes postaux et références de commande. Les scans en Chine sont lus à l'heure de Pékin, ceux d'USPS à l'heure de l'État américain concerné et ceux de Pactic, via la Hongrie, à l'heure de Budapest, puis affichés dans le fuseau horaire de ton appareil (sur peektracker.com, une ligne sous l'historique le nomme : « Heures dans ton fuseau horaire : … ») ; les autres gardent l'heure fournie par DHLink. DHLink ne fournit ni poids, ni nom de service, ni heure de livraison, et la seule estimation affichée par Peek provient d'un éventuel scan USPS relayé du type `Out for Delivery, Expected Delivery …` lorsqu'il donne un créneau le jour même. Quand le « Last Leg No. » est un numéro GOFO (`GFUS` et 14 chiffres), SwiftX (`SWX` et 18 chiffres) ou [DPD Suisse](carrier:dpd-ch) (14 chiffres commençant par `060` ou `061`), Peek affiche ce transporteur et son numéro, l'interroge et bascule dessus dès qu'il montre une progression plus récente ; les autres numéros de dernier kilomètre restent suivis sur l'historique de DHLink.

Tant que le suivi de DHLink répond directement, Peek vérifie toutes les 10 minutes, et toutes les 2 minutes le jour où le colis part en livraison (dès 6 h, heure locale, ou, si un créneau est annoncé, à partir d'une demi-heure avant son début) ; via des services de suivi, toutes les 15 minutes. Une fois le suivi basculé sur GOFO, le contrôle se fait toutes les 30 minutes, y compris en tournée. Après deux jours sans nouveau scan (ou 12 heures tant que le colis reste à « Étiquette créée » ou à « Pris en charge »), Peek passe à une vérification par heure. Tu peux activer les alertes sur « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Peek ne peut contacter ni DHLink, ni DHgate, ni le vendeur à ta place.

## Questions fréquentes sur le suivi DHLink

### Qu'est-ce que DHLink ?

C'est la plateforme logistique transfrontalière du groupe DHgate, exploitée à Pékin par 驼飞侠科技（北京）有限公司. Créée en 2014, elle achemine les commandes depuis la Chine et depuis des entrepôts à l'étranger via ses propres lignes et des transporteurs partenaires.

### Qui livre mon colis DHLink à l'arrivée ?

Un transporteur local dans ton pays. En Suisse, le dernier kilomètre peut passer par DPD Suisse ; sur les lignes américaines, par UPS, USPS, FedEx, GOFO, SwiftX ou UniUni ; et sur une ligne postale en `CN`, par la poste nationale qui relaie China Post. Regarde le champ « Last Leg No. » sur le résultat de DHLink.

### Que signifie « The goods have been delivered to UPS/USPS/Fedex » ?

DHLink a remis le colis au transporteur américain chargé de la suite du trajet. Le colis n'est donc pas encore livré chez toi : les scans de ce transporteur viennent ensuite.

:::sources
- [DHLink : À propos de nous (关于我们)](https://www.dhlink.com/about/introduce) – fondée en 2014, plateforme logistique transfrontalière de DHgate, entrepôts à l'étranger et livraison dans le pays de destination
- [DHLink : Page d'accueil (首页)](https://www.dhlink.com/) – envoi direct depuis la Chine et entrepôts à l'étranger
- [DHLink : Suivi (轨迹查询)](https://www.dhlink.com/trackQuery?lang=en) – recherche jusqu'à 40 numéros, types de numéros acceptés, champs DHLink No., Tracking No., Last Leg No., Signed for, message d'absence de suivi et langue par défaut
- [DHLink : Nous contacter (联系我们)](https://www.dhlink.com/about/connect) – adresse dhlinkservice@dhlink.com, formulaire en ligne, assistant AI 小飞, absence de téléphone
- [DHLink : Conditions de service (服务协议)](https://www.dhlink.com/agreementPage/service) – opérateur 驼飞侠科技（北京）有限公司
- [DHgate Seller Help : Règles d'indemnisation DHLink (DHLink物流异常件赔付规则)](https://seller.dhgate.com/help/c7502/516002.html) – indemnisation réservée aux vendeurs, exclusions liées à l'acheteur, délai de 90 jours calendaires après la commande logistique
- [Douane française : Mesures e-commerce européennes](https://www.douane.gouv.fr/fiche/mesures-e-commerce-europeennes) – droit de douane forfaitaire de 3 € jusqu'à 150 € depuis le 1er juillet 2026, frais de traitement européens de 2 € par article, redevable déclarant en douane
- [Commission européenne : EU Customs Reform (en anglais)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – entrée en application le 1er novembre 2026 des frais de traitement de 2 €
- [Commission européenne : Customs Guidance on EUR 3 customs duty, PDF (en anglais)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – frais de 2 € comptés par article comme les 3 €, quelle que soit la valeur, dus comme la dette douanière dès l'acceptation de la déclaration
- [OFDF : Frais de dédouanement et suivi des colis](https://www.bazg.admin.ch/fr/frais-dedouanement-et-suivi-des-colis) – en Suisse, droits de douane, TVA et frais de dédouanement perçus par le transporteur
- [Universal Parcel Scraper : DHLink (en anglais)](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/dhlink) – formes de numéros, libellés des scans (Pactic, UniUni, China Post, USPS), fuseaux horaires, transfert vers GOFO, SwiftX et DPD Suisse, fonctionnement dans Peek
- [Universal Parcel Scraper : GOFO Express (en anglais)](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/gofo) – prise en charge des numéros FC de DHLink par le suivi GOFO et cadence de vérification aux 30 minutes
:::
