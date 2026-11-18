---
title: Suivi YTO Express : numéro YT, statuts et contacts
description: Suivez un colis YTO Express (圆通) : format du numéro YT, signification des scans chinois, retrait en Chine, délais vers l'Europe et numéros utiles de YTO.
slug: suivi-yto-express
published: 2026-11-18
updated: 2026-11-18
---

YTO Express (圆通速递, Yuantong) est une société chinoise de transport express fondée à Shanghai en 2000, qui compte plus de 5 000 agences à travers la Chine. Un numéro YTO correspond le plus souvent à un colis circulant à l'intérieur de la Chine : une commande passée sur Taobao, Pinduoduo ou Douyin — des plateformes servies par YTO — en route vers l'entrepôt d'un transitaire ou l'adresse d'un proche. Tu peux le suivre sur [l'outil de suivi de YTO](https://www.yto.net.cn/ytoExpress/waybill/search), disponible uniquement en chinois, ou dans le champ ci-dessus. Les envois de YTO hors de Chine relèvent de YTO International, qui dispose de son propre outil sur [ytoglobal.com](https://www.ytoglobal.com/service).

## Numéro de suivi YTO Express : à quoi il ressemble

YTO appelle ce code le numéro de bordereau (`运单号`). Avec des chiffres inventés :

| Numéro | Ce que c'est |
| --- | --- |
| `YT2468135790246` | `YT` suivi de 13 chiffres : un bordereau YTO Express. |
| `D24681357902` | `D` suivi de 11 chiffres : un format YTO, mais les numéros [Colis Privé](carrier:colis-prive) peuvent avoir la même forme. |
| `G24681357902` | `G` suivi de 11 chiffres : un autre format YTO, que les numéros Colis Privé peuvent aussi partager. |
| `YT2612468135790246` | `YT` suivi de 16 chiffres : ce n'est pas YTO, mais [YunExpress](carrier:yunexpress). |

Les minuscules et les espaces n'ont aucune importance dans [Peek](/). L'outil de suivi de YTO accepte jusqu'à 10 numéros à la fois, séparés par des virgules, des barres obliques ou des espaces.

**Où le trouver.** Le vendeur le communique avec les détails d'expédition de la commande, et il est imprimé sur l'étiquette apposée sur le colis. Si tu ne le trouves pas, consulte [comment trouver son numéro de suivi](guide:find-tracking-number).

**Suivi sur le site de YTO.** L'outil de recherche reste en chinois même depuis les pages en anglais du site de YTO : saisis le numéro dans le champ et clique sur `查询` (rechercher). La politique de confidentialité de YTO précise qu'un bordereau demeure consultable pendant trois à six mois selon le canal de réservation ; passé ce délai, YTO le supprime du système de consultation.

## Statuts de suivi YTO Express expliqués

Chaque scan des données de suivi de YTO comporte un intitulé court en chinois, une heure sur le fuseau de la Chine (`UTC+8`) et l'agence ou le poste qui l'a enregistré. Voici comment ils apparaissent dans Peek :

| Scan de YTO | Dans Peek | Ce qui s'est passé |
| --- | --- | --- |
| `揽收扫描` | `Picked up` | Pris en charge auprès de l'expéditeur. |
| `业务员收件` | `Picked up` | Collecté en personne par un livreur YTO auprès de l'expéditeur. |
| `装件入车扫描` | `Loaded for transport` | Chargé dans un véhicule vers l'étape suivante. |
| `下车扫描` | `Unloaded at a sorting centre` | Déchargé dans un centre de tri ou une agence. |
| `快件城市信息` | « En transit » | Point de passage dans une ville en cours d'acheminement. |
| `冬运会安检` | `Security check` | Contrôle de sûreté du colis dans le réseau YTO lié à un événement de sports d'hiver, avant la reprise du trajet. |
| `派件扫描` | « En livraison » | Remis au livreur pour la tournée de distribution. |
| `入柜\|入库` | `In a parcel locker or pickup station` | Déposé dans une consigne automatique ou enregistré dans un relais de retrait. |
| `小件员取出` | `Taken out of the locker by the courier` | Le livreur a repris le colis dans la consigne ou le relais. |
| `PDA正常签收扫描` | « Livré » | Remis contre émargement (`签收`), enregistré sur le terminal portable du livreur. |
| `退回件扫描` | `Return to the sender started` | Le retour vers l'expéditeur a commencé. |

Les trois lignes entre guillemets s'affichent en français dans Peek ; les autres lignes du tableau restent en anglais, et tout scan absent du tableau apparaît dans le chinois d'origine de YTO. Le statut global du colis, lui, est toujours traduit en français, comme « Pris en charge » ou « Prêt à être retiré ». Après `入柜|入库`, le statut passe à « Prêt à être retiré » ; si le livreur retire le colis (`小件员取出`), il revient à « En transit ».

Si l'adresse de destination est l'entrepôt d'un transitaire en Chine, `PDA正常签收扫描` signifie que l'entrepôt a réceptionné ton colis. À partir de là, c'est le numéro propre au transitaire qui prend le relais.

## Livraison YTO en Chine : livreurs, consignes et relais

En **Chine**, un livreur YTO apporte le colis (`派件扫描`), ou celui-ci est placé dans une consigne ou une station de retrait (`入柜|入库`). YTO mentionne le `95161` comme sa « Courier Outbound Hotline », le numéro depuis lequel ses livreurs appellent les destinataires.

La réglementation de la **Chine** applicable aux entreprises de transport express, entrée en vigueur le 1er mars 2024, prévoit que :

- YTO doit t'informer que tu peux vérifier le colis en présence du livreur par rapport au bordereau. Si l'emballage est visiblement endommagé ou si le contenu est fragile, tu peux inspecter l'intérieur ou refuser la remise ;
- une entreprise ne peut ni confirmer la réception à ta place, ni déposer le colis dans une consigne intelligente ou une station de service express sans ton consentement.

Pour faire renvoyer un colis ou modifier l'adresse avant la livraison (`退回/更址`), YTO cite son application et son mini-programme parmi les moyens de demande. Sa politique de confidentialité invite à contacter le service client pour modifier l'adresse de livraison ou le numéro de téléphone.

## YTO International : colis expédiés hors de Chine

YTO International (圆通国际) remonte à 2006 et indique compter plus de 50 bureaux dans 18 pays et régions. Son outil de suivi sur [ytoglobal.com/service](https://www.ytoglobal.com/service) existe en anglais (« Express Freight Tracking ») et en chinois (`国际快递查询`), et accepte jusqu'à 10 numéros séparés par des virgules ou des points-virgules.

Les pages de YTO ne nomment pas le transporteur chargé de la remise finale dans ton pays et évoquent seulement des partenariats avec des prestataires logistiques mondiaux de premier plan (« top-notch global logistics service providers »). Pour International Standard Express, son offre destinée aux particuliers, YTO indique à titre purement indicatif (« for reference only ») un délai de 7 à 12 jours ouvrés depuis son entrepôt international vers la France, la Belgique, le Luxembourg, Monaco, la Suisse, l'Allemagne, l'Espagne, la Pologne, le Royaume-Uni et le Canada (12 à 17 vers la Bulgarie et la Roumanie), 9 à 12 vers les États-Unis et 8 à 13 vers l'Australie.

En cas d'absence, la FAQ de YTO International invite à suivre l'avis de nouvelle présentation (« Redelivery Notification ») pour programmer gratuitement une seconde livraison ; en cas de nouvel échec, YTO demande ses instructions à l'expéditeur. Pour convenir d'un créneau à l'avance, la FAQ renvoie au `400 60 95554`. Sur les envois aériens, les douanes et la sûreté aéroportuaire inspectent les colis et la douane peut saisir voire détruire un envoi contenant des articles interdits.

**Douane, territoires et messages frauduleux.** Les droits et taxes dépendent du pays de destination : voir [colis bloqué en douane](guide:customs). Dans l'**Union européenne**, depuis le 1er juillet 2026, les biens achetés à distance hors du territoire douanier de l'UE dans des envois valant jusqu'à 150 € supportent un droit de douane de 3 € par catégorie d'article ; depuis le 1er novembre 2026 s'ajoutent des frais de traitement de l'Union de 2 € par catégorie d'article, quelle que soit la valeur de l'envoi (une catégorie regroupe les marchandises d'un même envoi relevant du même classement tarifaire, de la même désignation et, lorsqu'elle doit être déclarée, de la même origine, quel que soit le nombre d'unités). La Guadeloupe, la Guyane, la Martinique, La Réunion et Mayotte font partie du territoire douanier de l'UE mais restent hors du territoire TVA de l'UE. En **Suisse**, l'OFDF ne perçoit ni TVA ni droits de douane lorsque leur montant respectif est inférieur à 5 francs suisses, hors frais de dédouanement perçus par le transporteur. En **France**, Cybermalveillance.gouv.fr met en garde contre les SMS et courriels d'hameçonnage réclamant de faux frais de douane ou de livraison via un lien et conseille, au moindre doute, de vérifier le véritable suivi du colis ou de contacter directement l'entreprise de livraison ; voir aussi [faux SMS de livraison](guide:delivery-scam-texts).

## Colis YTO bloqué, perdu ou retourné

**Aucune ligne pour l'instant.** Un nouveau bordereau n'affiche rien tant que YTO n'a pas scanné le colis. S'il reste vide, vérifie le numéro auprès du vendeur. Un ancien bordereau peut également ne rien renvoyer parce que YTO l'a effacé (après trois à six mois, voir plus haut).

**Plus de mise à jour.** Le contrat de service conclu en **Chine** par YTO avec les expéditeurs considère un colis comme perdu si, après sa première ligne, il ne reçoit aucune mise à jour pendant 4 jours, ou s'il n'est toujours pas signé 7 jours après l'échéance prévue. Contacte le vendeur, qui a réservé l'envoi et peut solliciter YTO. Plus d'explications dans [suivi de colis qui ne bouge plus](guide:tracking-not-updating) et [suivre un colis venant de Chine](guide:tracking-from-china).

**Perdu ou endommagé.** Pour un achat en boutique, c'est le vendeur qui a payé le transport : adresse-toi d'abord à lui. Selon ce même contrat en **Chine**, un colis perdu expédié sans valeur déclarée (`保价`) est indemnisé jusqu'à 300 yuans, ou 100 yuans pour des documents ; les envois assurés sont couverts à hauteur de la perte réelle dans la limite de la valeur déclarée. Les réclamations se font par téléphone auprès de l'agence YTO ayant pris le colis en charge ou du service client YTO, et le site de YTO comporte des pages de réclamation (`投诉反馈`) et d'indemnisation sur valeur déclarée (`保价理赔`) accessibles après connexion. Si la réponse de YTO ne te satisfait pas ou tarde trop, la réglementation chinoise permet de saisir le régulateur postal, le State Post Bureau (rubrique `消费者申诉` sur son site). Voir aussi [colis perdu](guide:lost-parcel).

**Retourné.** Après `退回件扫描`, le colis repart en sens inverse et les scans de distribution ultérieurs décrivent le retour vers l'expéditeur. Contacte le vendeur ; voir [retourné à l'expéditeur](guide:returned-to-sender).

**Livré mais introuvable.** En **Chine**, le contrat de service de YTO autorise une personne vivant sous ton toit ou désignée par toi à signer pour le colis, ou le livreur à le déposer dans une consigne intelligente ou une station express proche de l'adresse. Vérifie ces emplacements ainsi que l'accueil de ton immeuble. Voir [livré mais non reçu](guide:delivered-not-received).

## Numéros de téléphone et service client YTO Express

Tels que relevés sur les pages de YTO, voici les numéros publiés :

- **`95554`** : « Service Hotline » (`服务热线`), pour YTO Express en Chine.
- **`400 60 95554`** : « Service Hotline » de YTO International, pour les envois à l'international.
- **`95161`** : « Courier Outbound Hotline », la ligne depuis laquelle appellent les livreurs YTO.
- **`021-69777888`** : affiché à côté des précédents, sans intitulé.

YTO n'indique ni horaires ni langues pour ces lignes. Son site propose aussi un bouton de service client en ligne (`在线客服`), et YTO International un formulaire de contact. Pour la commande elle-même, adresse-toi au vendeur.

## Suivre un colis YTO Express avec Peek

[Peek](/) désigne immédiatement YTO Express devant `YT` suivi de 13 chiffres. Un numéro commençant par `D` ou `G` suivi de 11 chiffres convient aussi à [Colis Privé](carrier:colis-prive), si bien que Peek ne peut pas trancher par la seule forme. Colle-le tout de même : Peek suit plus de 3 500 transporteurs et interroge les services de suivi (« Vérification des services de suivi… »). Si YTO Express n'apparaît pas, touche « Changer » et choisis-le sous « Correspond à ce numéro », ou utilise « Changer de transporteur » sur le colis. La recherche YTO de Peek accepte aussi des bordereaux YTO d'autres formats : sélectionne alors YTO Express sous « Tous les transporteurs ». Un lien `yto.net.cn` contenant `mailNo=` ou `waybillNo=` avec le numéro désigne également YTO.

Peek lit le suivi intérieur de YTO en Chine, et non celui de YTO International. Il affiche le statut, les scans renvoyés par YTO avec la ville de l'agence ou du poste auteur de chacun (ou son nom chinois lorsque Peek ne peut pas le localiser) ainsi que l'heure de livraison ; les horaires sont lus sur le fuseau de la Chine (`UTC+8` : `20 h 00` à Pékin vaut `13 h 00` en hiver et `14 h 00` en été à Paris, Bruxelles ou Genève) et affichés dans le fuseau de ton appareil, suivis sur peektracker.com et dans l'application iPhone par « Heures dans ton fuseau horaire : » et son nom. Les scans répétés n'apparaissent qu'une fois. Juste après `退回件扫描`, le statut passe à « Problème signalé » et Peek affiche le trajet retour en anglais sous la forme `Out for delivery back to the sender` et `In a parcel locker or station on its way back`, puis la signature de l'expéditeur comme `Returned to the sender` avec le statut « Retourné à l’expéditeur ».

YTO ne communique aucune date de livraison prévue. Peek écarte les noms et numéros de téléphone des livreurs, les coordonnées du destinataire et les adresses de retrait : ces éléments restent sur l'outil de YTO. Comme YTO répond à tout numéro au format valide, l'absence de scans ne prouve pas une erreur : Peek affiche « En attente de YTO Express » sur peektracker.com et continue de vérifier. Si un colis classé sous YTO n'a toujours aucune trace chez YTO deux jours plus tard et qu'aucune autre source n'a répondu, Peek affiche « Après deux jours, le transporteur ne connaît toujours pas ce numéro. Vérifie-le, choisis un autre transporteur ou supprime le colis. » sur peektracker.com, puis le vérifie une fois par jour et, au bout d'une semaine après l'ajout, une fois par semaine.

Peek vérifie jusqu'à toutes les 10 minutes tant que le colis est ouvert ou le jour de sa livraison avec les notifications activées, et jusqu'à toutes les 2 minutes à partir de 6 h (heure de Chine) le jour où YTO scanne le colis en livraison (`派件扫描`), tant que le propre suivi de YTO répond ; via les services de suivi, il vérifie au plus toutes les heures, toutes les 3 heures après un jour sans scan, et jusqu'à toutes les 15 minutes le jour de la livraison tant que le colis est ouvert ou que les notifications sont activées. Le reste du temps, ainsi qu'après deux jours sans nouveau scan (12 heures tant qu'il affiche « Pris en charge »), Peek vérifie toutes les heures. Tu peux activer les notifications « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Peek ne peut contacter ni YTO ni le vendeur à ta place.

## Questions fréquentes sur le suivi YTO Express

### YTO Express est-il un transporteur fiable ?

Oui. YTO Express a été fondé à Shanghai le 28 mai 2000. Le groupe compte deux sociétés cotées, YTO Express (code 600233) et YTO International (06123.HK), et déclare 550 000 employés fin 2024.

### Que signifie 签收 sur le suivi YTO ?

Livré contre signature : le livreur a remis le colis et l'a enregistré sur son terminal (`PDA正常签收扫描`), ce que Peek affiche comme « Livré ». Le colis a pu être remis à toi, à une personne ayant signé pour toi ou à l'entrepôt d'un transitaire. Si personne ne l'a reçu, consulte [livré mais non reçu](guide:delivered-not-received).

### Pourquoi mon numéro YTO n'affiche-t-il aucun suivi ?

Soit YTO n'a pas encore scanné le colis, soit le numéro comporte une erreur, soit le bordereau dépasse la durée de consultation conservée par YTO : trois à six mois selon sa politique de confidentialité. Vérifie le numéro auprès du vendeur ou colle-le dans Peek, qui poursuivra les vérifications.

### YTO Express livre-t-il hors de Chine ?

Oui, par l'intermédiaire de YTO International, qui dispose de son propre suivi sur ytoglobal.com. Ses pages ne précisent pas quel transporteur assure le dernier kilomètre dans ton pays et donnent ses délais uniquement à titre indicatif : 7 à 12 jours ouvrés vers la France, la Belgique, la Suisse, le Canada, le Royaume-Uni et la plupart des pays de l'UE.

### Quel est le numéro de téléphone de YTO Express ?

Le `95554` en Chine et le `400 60 95554` pour YTO International, sans horaires publiés. Les appels provenant du `95161` émanent des livreurs YTO.

:::sources
- [YTO Express : About YTO (en anglais)](https://www.yto.net.cn/en/aboutYto/about) – fondation le 28 mai 2000 à Shanghai, sociétés cotées, agences et effectifs
- [YTO Express : 我要查件 (suivi de bordereau, en chinois)](https://www.yto.net.cn/ytoExpress/waybill/search) – suivi uniquement en chinois, jusqu'à 10 numéros, pages de réclamation et d'indemnisation
- [YTO Express : Domestic Express Services (en anglais)](https://www.yto.net.cn/en/domesticService) – plateformes desservies, retour ou changement d'adresse avant livraison, lignes 95554 et 95161
- [YTO Express : Page d'accueil (en chinois)](https://www.yto.net.cn/) – service client en ligne `在线客服`
- [YTO Express : 国内快递 (services intérieurs, en chinois)](https://www.yto.net.cn/domesticService) – `退回/更址`, `服务热线`, `快递员外呼热线`
- [YTO Express : 国内快递服务协议 (contrat de service express intérieur, en chinois)](https://mec.yto.net.cn/h5/#/subPages/agreement/index?code=DESA) – critères de perte, indemnisation avec et sans valeur déclarée, réclamations, personnes habilitées à signer
- [YTO Express : 圆通速递隐私政策 (politique de confidentialité, en chinois)](https://mec.yto.net.cn/h5/#/subPages/agreement/index?code=PCPrivacyAgreement) – conservation consultable de trois à six mois, changement d'adresse via le service client
- [YTO Express : 寄快递 (envoyer un colis, en chinois)](https://www.yto.net.cn/ytoExpress/subCreatePages/create) – plafond de 300 yuans sans valeur déclarée
- [YTO Express : International Business Service (en anglais)](https://www.yto.net.cn/en/global/globalProduct) – ligne YTO International 4006095554 et 021-69777888
- [YTO International : Company Introduction (en anglais)](https://www.ytoglobal.com/about-us) – historique depuis 2006, bureaux et partenaires
- [YTO International : Express Freight Tracking](https://www.ytoglobal.com/service) – suivi en anglais et en chinois jusqu'à 10 numéros
- [YTO International : International Standard Express (en anglais)](https://www.ytoglobal.com/product/express-intl) – offre pour particuliers et délais indicatifs par pays
- [YTO International : FAQ (en anglais)](https://www.ytoglobal.com/rich/question) – ligne 400 60 95554, nouvelle présentation gratuite, prise de rendez-vous, inspection douanière
- [YTO International : Contact Us (en anglais)](https://www.ytoglobal.com/about-us/contact) – formulaire de contact
- [Ministère des Transports de Chine : 快递市场管理办法 (mesures d'administration du marché express, en chinois)](https://xxgk.mot.gov.cn/gz/202401/t20240104_3980684.html) – entrée en vigueur le 1er mars 2024, vérification à la remise, consentement requis pour consignes et relais, recours postal
- [State Post Bureau de Chine : 国家邮政局主要职责 (attributions principales, en chinois)](https://www.spb.gov.cn/gjyzj/c100001/c100006/201306/2722edab189242549b320d06e0dbd382.shtml) – régulation postale et du marché express
- [State Post Bureau de Chine : page d'accueil (en chinois)](https://www.spb.gov.cn/) – rubrique de recours des consommateurs (`消费者申诉`)
- [Commission européenne : EU Customs Reform (en anglais)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – droit de 3 € par catégorie d'article jusqu'à 150 € depuis le 1er juillet 2026 et frais de traitement de l'Union de 2 € depuis le 1er novembre 2026
- [Commission européenne : Guidance on the EUR 3 customs duty and the Union handling fee (PDF, en anglais)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – frais de 2 € quelle que soit la valeur et définition d'une catégorie d'article
- [Commission européenne : Territorial Scope (en anglais)](https://taxation-customs.ec.europa.eu/taxation/vat/vat-directive/how-does-vat-work/territorial-scope_en) – Guadeloupe, Guyane, Martinique, La Réunion et Mayotte dans le territoire douanier de l'UE et hors du territoire TVA de l'UE
- [OFDF (Suisse) : Réception de lettres et de paquets](https://www.bazg.admin.ch/fr/reception-de-lettres-et-paquets) – non-perception en Suisse des droits et de la TVA inférieurs à 5 CHF
- [OFDF (Suisse) : Frais de dédouanement et suivi des colis](https://www.bazg.admin.ch/fr/frais-dedouanement-et-suivi-des-colis) – frais de dédouanement facturés par le transporteur en Suisse
- [Cybermalveillance.gouv.fr : Escroqueries à la livraison de colis](https://www.cybermalveillance.gouv.fr/tous-nos-contenus/actualites/escroqueries-livraison-colis) – prévention en France des SMS et courriels frauduleux sur les frais de livraison ou de douane
- [Commission européenne : Summertime (en anglais)](https://transport.ec.europa.eu/transport-themes/summertime_en) – heure d'été européenne du dernier dimanche de mars au dernier dimanche d'octobre
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formats reconnus, liens `yto.net.cn`, traduction des scans YTO, fuseau chinois, parcours retour et données affichées ou écartées par Peek
:::
