---
title: Suivi Mayi Yinfu : numéros MY, vols et relais DHL
description: Suis un colis Mayi Yinfu (蚂蚁银服) depuis la Chine continentale : formats MY…US et MYYF…CN, étapes aériennes, lignes anticipées et reprise par DHL en Europe.
slug: suivi-mayi-yinfu
published: 2026-11-21
updated: 2026-11-21
---

Mayi Yinfu (`蚂蚁银服`) est le nom court de `广东蚂蚁银服国际物流有限公司` (*Mayi Yinfu International Logistics*), un transitaire implanté au Guangdong en Chine continentale qui expédie des petits paquets et colis express vers l'étranger. Ses numéros de suivi se composent de `MY`, dix chiffres et `US` (ou parfois `MYYF`, huit chiffres et `CN`) : colle le tien dans le champ ci-dessus, ou saisis-le dans la case `快递查询` sur `mayiyinfu.com.cn`. Mayi Yinfu achemine les colis par avion jusqu'à ta région puis les confie à un transporteur local — vers l'Europe, Deutsche Post [DHL](carrier:dhl).

## Numéro de suivi Mayi Yinfu : à quoi il ressemble

Les lettres `MY` reprennent `蚂蚁` (*mǎyǐ*, fourmi). Voici les formes connues par Peek, avec des chiffres inventés :

| Numéro (inventé) | Ce que c'est |
| --- | --- |
| `MY4829105736US` | `MY`, 10 chiffres et `US` (14 caractères) : numéro propre à Mayi Yinfu, reconnu immédiatement par Peek. |
| `MYYF48291057CN` | `MYYF` (initiales de `蚂蚁银服`), 8 chiffres et `CN` (14 caractères) : second format connu du traceur de Mayi Yinfu. |
| `MY482910573US` | Seulement 9 chiffres au lieu de 10 : Peek avertit : « Ça commence comme un numéro de suivi Mayi Yinfu, mais il manque un caractère : il y a peut-être une faute de frappe. Compare-le avec ton message d’expédition. » |
| `RR482910576CN` | Deux lettres, 9 chiffres et `CN` : un envoi postal [China Post](carrier:china-post), pas Mayi Yinfu. |

Les espaces et minuscules n'ont aucune incidence dans Peek. Comme Mayi Yinfu travaille avec des expéditeurs professionnels, ton numéro provient de la boutique où tu as commandé ([retrouver un numéro de suivi](guide:find-tracking-number)) ; le traceur de Mayi Yinfu accepte aussi la référence de commande du vendeur, saisie exactement telle quelle (tirets compris).

## Signification des statuts de suivi Mayi Yinfu

Mayi Yinfu ne publie aucun lexique de statuts. Ses propres lignes sont en chinois (souvent accompagnées d'une traduction anglaise approximative), puis son traceur recopie en anglais les scans du transporteur final :

| Statut Mayi Yinfu | Ce que cela signifie | Peek affiche |
| --- | --- | --- |
| `货物电子信息已经收到` | Données électroniques reçues sur la plateforme Hualei (`华磊`, également utilisée par BYHD) : l'ordre est créé, mais aucun colis n'a encore été scanné. | « Étiquette créée » |
| `The instruction data for this shipment have been provided by the sender to DHL electronically` | Ligne relayée depuis DHL : les données de l'étiquette ont été transmises à DHL, sans remise physique. | « Étiquette créée » |
| `已入库,到达收货点` | Colis réceptionné et enregistré à l'entrepôt de collecte de Mayi Yinfu. | « Pris en charge » |
| `进仓时间：…；Warehouse entry time:…`, `已出库,货物离开操作中心`, `国内清关完成` | Heure d'entrée en entrepôt, sortie du centre d'exploitation, puis dédouanement export achevé en Chine continentale. | « En transit » |
| `货物已交机场` | Remis à l'aéroport de départ (et **non** livré chez toi malgré la traduction anglaise *delivered to the airport*), suivi de `货物已交机场，等待安排航班中The goods have been delivered to the airport and are awaiting the arrangement of flights.` ou du jour de vol prévu. | « En transit » |
| `航班延误` | Vol retardé, suivi du nouveau jour de départ attendu. | « En transit » |
| `航班已起飞`, `航班落地`, `头程起飞`, `头程落地`, `二程起飞` | Décollage et atterrissage du vol (`航班已起飞`, `航班落地`), départ et arrivée du premier tronçon (`头程起飞`, `头程落地`), puis départ du vol de correspondance (`二程起飞`, traduit à tort par *Flight 202* sur le traceur). | « En transit » |
| `航班落地，拆板清关The flight landed, and the board was dismantled and cleared` | Atterrissage dans ta région, dépalettisation et passage en dédouanement import. | « À la douane » |
| `清关完成，待转运Clearance has been completed and it is awaiting transfer.`, `已转运transferred` | Dédouanement terminé, puis remise au transporteur de livraison dont le numéro apparaît sur le dossier. | « En transit » |
| `The international shipment has been processed in the parcel center of origin`, `The international shipment has been processed in the export parcel center` | Lignes recopiées de DHL : tri dans les centres colis DHL en Allemagne. | « En transit » |
| `The shipment has arrived in the destination country/destination area.` | Arrivée dans ton pays (parfois suivie entre parenthèses d'un lien vers le transporteur local). | « En transit » |
| `The shipment has been loaded onto the delivery vehicle` | Chargé dans le véhicule du livreur pour la tournée finale. | « En livraison » |
| `The shipment has been successfully delivered` | Colis livré. | « Livré » |

**Lignes automatiques (`CO_C_CR`).** Seules, les lignes `国内清关中` (douane export), `已排航班` (vol réservé), `起运地航班起飞` (vol parti), `已到达目的国` (arrivé dans le pays de destination) et `清关完成` (dédouanement terminé) proviennent d'un script préprogrammé qui peut annoncer l'arrivée à l'étranger avant même le dépôt à l'aéroport : Peek ne les compte pas comme des scans ([que signifient les statuts de suivi](guide:tracking-statuses)).

## Qui livre les colis Mayi Yinfu à destination ?

Le site de Mayi Yinfu ne liste aucun partenaire, mais le dossier affiche un numéro de transfert dès `已转运transferred`. **Vers l'Europe**, il s'agit d'un numéro Deutsche Post [DHL](carrier:dhl), suivi jusqu'à la remise finale dans ton pays (la ligne d'arrivée de DHL peut aussi contenir un lien vers l'opérateur national qui livre à ta porte). **Ailleurs**, identifie l'opérateur grâce à notre guide sur les [formats de numéros de suivi](guide:tracking-number-formats) ou demande-le au vendeur. Les règles de passage, points relais et délais de garde sont celles de ce transporteur final ; si des frais d'importation te sont demandés, consulte [colis retenu en douane](guide:customs) et vérifie tout SMS suspect avec [reconnaître les SMS de livraison frauduleux](guide:delivery-scam-texts).

## Colis Mayi Yinfu bloqué, introuvable ou en retard

- **Bloqué sur les premières lignes :** `货物电子信息已经收到` (avec ou sans la ligne électronique DHL) signifie que le vendeur n'a pas encore confié le colis à l'entrepôt. Après quelques jours sans entrée en stock, contacte la boutique.
- **Bloqué après `已转运transferred` :** la partie de Mayi Yinfu est terminée ; suis désormais le numéro du transporteur final ([suivre un colis expédié depuis la Chine](guide:tracking-from-china), [suivi qui ne se met plus à jour](guide:tracking-not-updating)).
- **Numéro inconnu :** pour un code qu'il ne connaît pas, le traceur de Mayi Yinfu renvoie malgré tout une fiche avec le code sous `参考号` (référence) et tous les autres champs vides. Peek prend cette réponse pour un numéro introuvable : il affiche « En attente de Mayi Yinfu » puis, si le numéro reste inconnu deux jours, te demande de le vérifier. Compare-le donc caractère par caractère avec le message du vendeur.
- **Perdu, endommagé ou retourné :** Mayi Yinfu ne publie aucune procédure d'indemnisation ; demande un remboursement ou un renvoi au marchand ou à la place de marché ([colis perdu](guide:lost-parcel), [colis retourné à l'expéditeur](guide:returned-to-sender), [indiqué livré mais non reçu](guide:delivered-not-received)).

## Contacter Mayi Yinfu

Le site chinois `mayiyinfu.com.cn` n'indique qu'un seul numéro de mobile en Chine continentale, **`+86 13705996996`** (`13705996996`), affiché dans l'en-tête comme ligne nationale (`全国服务热线`) et dans l'encadré `在线咨询`. Sa page de contact (`contact.html`), sa présentation, ses mentions légales et sa politique de confidentialité sont vides (sans adresse postale, e-mail ni formulaire). Adresse-toi toujours en priorité au vendeur.

## Suivre un colis Mayi Yinfu avec Peek

Colle un code `MY…US` ou un lien `mayiyinfu.com.cn/trackIndex.htm` contenant `documentCode=` dans [Peek](/) : il est aussitôt affecté à Mayi Yinfu (« Transporteur détecté »). Pour un code `MYYF…CN`, Peek affiche « Vérification des services de suivi… » puis « Mayi Yinfu a ce colis. » dès que le traceur répond. Pour une référence vendeur sans tirets, choisis Mayi Yinfu via « Changer » (`蚂蚁银服` ou `MYYF` le trouvent aussi ; comme Peek retire les tirets, cherche les références avec tirets directement sur `mayiyinfu.com.cn`).

**Étapes, horloges et lignes écrites à l'avance.** Lorsqu'une ligne aérienne annonce un jour futur attendu, Peek affiche uniquement l'étape en anglais (comme `The first leg has departed`), sans la prévision non échue, et écarte noms, adresses, codes postaux, poids, frais et photos (aucune date estimée n'est fournie ; le code `SZ` seul, qui désigne l'Eswatini en norme ISO, est retiré du champ lieu). Les scans localisés en Chine continentale (`CN`), ainsi que les lignes anglaises recopiées de DHL portant un code pays une fois DHL rattaché au dossier, sont lus à l'heure de Pékin (`UTC+8`) et convertis dans ton fuseau (« Heures dans ton fuseau horaire : … ») ; les autres gardent leur horloge locale. Toute ligne enregistrée plus de 12 heures avant l'heure qu'elle affiche, ou plus de 6 heures avant dans la même seconde qu'une telle ligne, est traitée comme une prévision, comme les lignes `CO_C_CR` : elle ne compte pas comme un scan et ne change pas le statut. L'historique s'ouvre alors sur « D’autres sites affichent peut-être plus d’étapes, mais ce ne sont que des prévisions. », ces étapes grisées étant repliées derrière « Les voir ». Une ligne datée après la vérification de Peek n'apparaît qu'une fois cette heure passée.

**Reprise automatique par DHL et cadence.** Lorsque le numéro de transfert appartient sans ambiguïté à [DHL](carrier:dhl), Peek affiche « Livraison par DHL » avec ce second numéro (ou « Annoncé à DHL, pas encore reçu » tant que DHL n'a que les données électroniques) et bascule sur le suivi direct de DHL dès qu'un scan DHL est au moins aussi récent que la dernière ligne de Mayi Yinfu ; tout autre numéro de transfert doit être ajouté séparément comme second colis. Tant que le colis est ouvert ou le jour de livraison avec notifications actives, Peek vérifie jusqu'à toutes les 10 minutes (et toutes les heures le reste du temps ainsi qu'après 12 h sur « Étiquette créée » / « Pris en charge » ou deux jours calmes ; si seules les lignes initiales figurent après deux jours, Peek interroge aussi des services de suivi). Alertes au choix : « Chaque scan », « Étapes importantes » ou « Livraison seulement ».

## Questions fréquentes sur le suivi Mayi Yinfu

### Pourquoi mon numéro Mayi Yinfu se termine-t-il par `US` ?

Tous les numéros `MY` de Mayi Yinfu se terminent par `US` (et ses numéros `MYYF` par `CN`), quelle que soit la destination : cela ne signifie pas que le colis va aux États-Unis ni qu'il est géré par USPS.

### `货物已交机场` (*delivered to the airport*) veut-il dire que mon colis est livré ?

Non : cela signifie simplement que Mayi Yinfu a déposé le fret à l'aéroport de départ pour son vol international. La livraison à ton adresse aura lieu plus tard via le transporteur final.

### Quels sont les délais de livraison de Mayi Yinfu ?

Mayi Yinfu ne publie aucun délai sur ses pages produits : fie-toi à la fourchette annoncée par la boutique lors de ta commande.

### Qui contacter si mon colis Mayi Yinfu n'arrive pas ?

Contacte le vendeur ou la plateforme où tu as passé commande : c'est l'expéditeur qui a réservé Mayi Yinfu, dont le site ne mentionne qu'un numéro de mobile en Chine continentale.

:::sources
- [Mayi Yinfu : 广东蚂蚁银服国际物流有限公司 (accueil)](http://www.mayiyinfu.com.cn/Default.aspx) – raison sociale, champ 快递查询, accès client et ligne mobile +86 13705996996 (全国服务热线 / 在线咨询)
- [Mayi Yinfu : 联系我们 (contact)](http://www.mayiyinfu.com.cn/contact.html) – page de contact sans adresse, e-mail ni formulaire au-delà du numéro mobile
- [Mayi Yinfu : 国际小包 (petits paquets internationaux)](http://www.mayiyinfu.com.cn/products/category-1.html) – texte factice sans délais ni liste de partenaires publiés
- [Universal Parcel Scraper : Mayi Yinfu](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/mayiyinfu) – plateforme Hualei, formats MY…US et MYYF…CN, statuts aériens et lignes CO_C_CR, fuseau de Pékin, code SZ et bascule vers Deutsche Post DHL
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – détection des formats China Post et DHL, étapes écrites à l'avance et services de suivi
:::
