---
title: Suivi Huaya Express : numéros HYYT…GZ, statuts et relais
description: Huaya Express (华亚运通) expédie depuis Canton. Lis un numéro HYYT…GZ, ses scans anglais et chinois, et vois qui livre ton colis après le relais.
slug: suivi-huaya-express
published: 2026-11-21
updated: 2026-11-21
---

Huaya Express (`华亚运通`) est la marque figurant sur le logo de `华亚运通国际货运代理有限公司` (*Huaya Yuntong International Freight Forwarding*), un transitaire établi à Canton (Guangzhou), en Chine continentale, qui expédie à l'international les colis de boutiques en ligne. Un bordereau Huaya commence par `HYYT` et se termine par `GZ`. Colle-le dans le champ ci-dessus, ou suis le lien `物流跟踪` (suivi logistique) dans le menu supérieur de `hyytgz.com` vers le propre traceur de Huaya. L'historique de Huaya s'arrête lorsqu'il remet le colis à un transporteur dans ton pays, ou après quelques scans de ce transporteur (aux États-Unis, parfois jusqu'à la livraison), sans jamais nommer ce transporteur — sauf sur une ligne via [DHL](carrier:dhl) en Allemagne, où il recopie les scans de DHL, sur une mention suédoise d'Early Bird et sur une ligne vers le Mexique, où il recopie les scans de J&T Express.

## Numéro de suivi Huaya Express : à quoi il ressemble

Huaya ne publie aucun format officiel. Le modèle reconnu par Peek se compose de `HYYT`, douze chiffres et `GZ` (exemples inventés) :

| Numéro (inventé) | Ce que c'est |
| --- | --- |
| `HYYT482910573617GZ` | `HYYT`, douze chiffres et `GZ` : la forme d'un bordereau Huaya Express. |
| `HYYT48291057317GZ` | Onze chiffres seulement : un chiffre s'est perdu au copier-coller. Recopie-le depuis le message du vendeur. |
| `RR482910576CN` | Numéro postal finissant par `CN` : suivi par [China Post](carrier:china-post), pas par Huaya. |

Peek accepte le numéro avec ou sans espaces, en majuscules comme en minuscules.

**Où trouver le numéro.** Huaya travaille pour les marchands : sa page d'accueil cite les boutiques Shopify, Vova, DHgate (`敦煌`) et les vendeurs sur Facebook. C'est donc la boutique où tu as commandé qui t'envoie le numéro ; s'il manque, consulte [comment trouver son numéro de suivi](guide:find-tracking-number). Le traceur de Huaya accepte aussi la référence de commande du marchand (`参考号`), saisie telle quelle avec ses tirets éventuels.

**Un lien de suivi sur une adresse numérique.** Le traceur propre de Huaya n'est pas hébergé directement sur `hyytgz.com`, mais sur une adresse numérique en HTTP simple (`trackIndex.htm` avec `documentCode=` et ton numéro) : tu peux coller ce lien tel quel dans Peek. En revanche, le lien `物流跟踪` situé dans le pied de page du site renvoie vers le service de suivi 17TRACK.

## Statuts de suivi Huaya Express expliqués

Le traceur de Huaya existe en chinois et en anglais. Les lignes connues de Peek sont majoritairement en anglais, à l'exception de la réception à l'entrepôt et de certaines étapes d'aéroport, de vol, de douane et de remise qui peuvent arriver en chinois, ainsi que d'une ligne suédoise en Suède. Dans l'ordre habituel du trajet :

| Huaya indique | Ce que cela veut dire pour toi | Peek affiche |
| --- | --- | --- |
| `Shipping Label Created` | L'étiquette du transporteur final dans ton pays est créée : données de commande uniquement, Huaya n'a pas encore le colis. | « Étiquette créée » |
| `Shipment not yet received or processed` | Ligne du transporteur final relayée avant l'arrivée au point de collecte de Huaya : données seules, Huaya n'a toujours pas le colis. | « Étiquette créée » |
| `Package received & scanned at warehouse`, `到达收货点` | Huaya a réceptionné le colis dans son entrepôt de Canton, en Chine continentale : premier scan entre ses mains (`到达收货点` signifie arrivé au point de réception). | « Pris en charge » |
| `Package departed from the warehouse` | Le colis a quitté l'entrepôt de Canton. | « En transit » |
| `货物发往机场途中`, `货物到达机场` | En route vers l'aéroport puis arrivé à l'aéroport en Chine continentale, avant le dédouanement export. | « En transit » |
| `Customs export clearance completed`, `国内清关完成` | Dédouanement à l'exportation terminé en Chine continentale ; le contrôle d'importation dans ton pays viendra à l'arrivée. | « En transit » |
| `安排航班中`, `Package handed over to airline` | Vol en cours de réservation, puis colis remis à la compagnie aérienne. | « En transit » |
| `Shipment Announced` | Huaya a annoncé l'envoi au transporteur local de ton pays, qui ne l'a pas encore reçu. | « En transit » |
| `Package in transit to destination country`, `航班起飞，预计航班落地时间(…)` | En vol vers le pays de destination (la version chinoise indique la date d'atterrissage prévue entre parenthèses). | « En transit » |
| `Package arrived at destination country` | Arrivé dans le pays de destination. | « En transit » |
| `航班落地，清关中` | Avion atterri, dédouanement d'importation en cours dans le pays d'arrivée. | « À la douane » |
| `Customs import clearance completed, awaiting pickup by local carrier`, `清关完成` | La douane de ton pays a libéré le colis ; le transporteur local doit encore venir le récupérer. | « En transit » |
| `It has been handed over to the service provider.`, `已交仓`, `Preparing for delivery` | Remis au prestataire local (ou à son entrepôt) ou en préparation avant la tournée. Huaya ne nomme ni ce transporteur ni son numéro ; après cela, les lignes connues proviennent soit d'un partenaire américain (`Arrived at Origin Hub` près de Chicago O'Hare, `Departed Origin Hub`, `Arrived at Destination Hub`, `Departed Destination Hub`, `Arrived At Warehouse`, puis `Out for Delivery` en « En livraison » et `Delivered` en « Livré »), soit des scans [DHL](carrier:dhl) recopiés vers l'Allemagne, soit, vers le Mexique, des scans recopiés de J&T Express. Peek connaît aussi d'autres lignes de livraison en anglais dont aucune source ne donne le pays (`Arrived at the regional hub`, `Departed from the regional hub`, `Arrived at the sorting center`, `Departed from the sorting center`, `Arrived in your region`, `Out for delivery with the courier` en « En livraison »). | « En transit » |
| `Din leverans ?r bokad med Early Bird.` | Suédois pour « Ta livraison est réservée avec Early Bird » (le traceur écrit le `ä` de `är` sous forme de `?`) : en Suède, Early Bird assure le dernier kilomètre. | « En transit » |

Les horaires de Huaya arrivent sans fuseau : ils sont à l'heure de Pékin (`UTC+8`), sauf pour les scans recopiés de DHL et les scans américains localisés dans une ville des États-Unis, qui gardent l'heure locale du lieu du scan, et pour les scans de J&T Express recopiés vers le Mexique, à l'heure de Mexico. Pour un numéro inconnu, la colonne `参考号` (référence) répète le numéro et le reste de la ligne demeure vide.

## Qui livre les colis Huaya Express ?

En dehors des scans [DHL](carrier:dhl) recopiés sur une ligne vers l'Allemagne, de la mention d'Early Bird en Suède et des scans de J&T Express recopiés sur une ligne vers le Mexique, le suivi de Huaya n'indique ni le nom du transporteur final ni un second numéro. Ses pages commerciales pour vendeurs se contredisent elles-mêmes : la fiche de la ligne Royaume-Uni (`ywfw.php?id=21`) se termine par du texte américain mentionnant USPS, et celle de la ligne Australie (`ywfw.php?id=27`) fait atterrir le colis aux États-Unis pour USPS. Le transporteur qui livre réellement chez toi dépend de l'option choisie par le marchand : demande-lui le nom de ce transporteur et son numéro de suivi propre. Pour d'éventuels frais à l'arrivée, consulte [colis retenu en douane](guide:customs).

## Colis Huaya Express bloqué, en retard ou introuvable

**Arrêté sur `It has been handed over to the service provider.`.** Le traceur de Huaya peut ne plus rien ajouter ensuite : le colis circule désormais sous le numéro propre du transporteur local. Demande ce numéro et le nom du transporteur au vendeur pour suivre la fin du parcours ([suivre un colis venu de Chine](guide:tracking-from-china)).

**En attente après le dédouanement import.** La douane a libéré l'envoi et le colis attend sa prise en charge par le transporteur local. Si plusieurs jours s'écoulent sans remise au prestataire, préviens le vendeur ([pourquoi le suivi ne bouge plus](guide:tracking-not-updating)).

**Aucun résultat.** Face à la ligne vide que renvoie Huaya lorsqu'il ne connaît pas encore un numéro, Peek considère que l'envoi attend son premier scan et affiche « En attente de Huaya Express » sans message d'erreur sur le format (pour une recherche sans compte, Peek revérifie pendant les 6 premières heures, puis quand tu rouvres son lien et, jusqu'à 30 jours après la recherche, tant que les notifications sont activées). Vérifie donc toi-même la saisie par rapport au message du marchand (`HYYT`, douze chiffres, `GZ`), puis demande au vendeur s'il a bien expédié avec Huaya.

**En retard, perdu ou retourné.** Les conditions d'indemnisation publiées par Huaya s'adressent aux marchands expéditeurs, pas aux destinataires. Demande un renvoi ou un remboursement au vendeur et, s'il ne répond plus, ouvre un litige sur la plateforme d'achat ([colis perdu](guide:lost-parcel), [retourné à l'expéditeur](guide:returned-to-sender), [indiqué livré mais non reçu](guide:delivered-not-received)).

## Service client et contact Huaya Express

Coordonnées d'entreprise affichées en chinois sur `hyytgz.com` :

- **Téléphone** : `18039073131`, désigné comme ligne de service de l'entreprise (`公司服务热线`). C'est un numéro mobile de Chine continentale (ajoute `+86` depuis l'étranger) ; ni horaires ni langues parlées ne sont précisés.
- **E-mail** : `2065332407@qq.com`, adresse QQ Mail indiquée comme e-mail de l'entreprise (`公司邮箱`).
- **Formulaire** : la page `投诉建议` (`message.php`, réclamations et suggestions) demande ton nom, ton numéro de mobile, ton e-mail et ton message.
- **Adresse** : district de Baiyun à Canton (`广州市白云区`), en Chine continentale.

Les pages `关于我们` (`about.php`) et `服务网点` (`outlets.php`) ne contiennent qu'un titre sans texte, et aucune page n'est destinée aux acheteurs.

**Attention aux SMS frauduleux.** Le site vitrine de Huaya Express est `hyytgz.com`. Avant d'ouvrir un lien reçu par SMS ou e-mail, lis [reconnaître les SMS de livraison frauduleux](guide:delivery-scam-texts). Pour toute question sur ta commande, contacte d'abord la boutique.

## Suivre un colis Huaya Express avec Peek

Colle un numéro `HYYT…GZ`, ou un lien du traceur contenant `documentCode=`, et [Peek](/) le classe aussitôt sous Huaya Express (« Transporteur détecté »), seul transporteur du catalogue à utiliser ce format. Pour chercher avec une référence de commande du vendeur parmi plus de 4 500 transporteurs, saisis-la puis choisis Huaya Express via « Changer » (`华亚运通` ou `GZHY` le trouvent aussi ; ou « Changer de transporteur » sur un colis déjà enregistré) : Peek affiche alors aussi le numéro sous lequel Huaya suit la commande, mais retire les tirets — si ta référence comporte des tirets, cherche-la sur le traceur de Huaya.

**Lecture des étapes, fuseaux et lignes écrites à l'avance.** Peek interroge directement le traceur de Huaya, affiche chaque étape avec son lieu éventuel et le pays de destination, écarte les données personnelles (noms, adresses, codes postaux, signatures, photos, poids) et n'affiche pas de date de livraison prévue (Huaya n'en donne pas). Les heures sont converties depuis Pékin (`UTC+8`) — ou depuis l'heure locale du lieu du scan pour les lignes DHL recopiées et les scans américains dans une ville des États-Unis, et depuis l'heure de Mexico pour les scans de J&T Express — vers le fuseau de ton appareil, avec « Heures dans ton fuseau horaire : … » au bas de l'historique. Si une ligne porte une heure future à Pékin, Peek la garde hors des scans jusqu'à cette heure. Peek retient aussi toute ligne écrite plus de 12 heures avant l'heure qu'elle affiche, ou plus de 6 heures avant dans la même seconde qu'une telle ligne (prévision et non scan réel). L'historique s'ouvre alors sur « D’autres sites affichent peut-être plus d’étapes, mais ce ne sont que des prévisions. » jusqu'à la livraison ou au retour du colis, et « Les voir » liste ces lignes en grisé, chacune avec « Prévu pour » et son jour. Ces lignes ne comptent jamais comme un scan, ne changent jamais le statut et ne déclenchent aucune alerte.

**Pas de bascule automatique et alertes.** Le dossier de Huaya ne donnant aucun transporteur final ni second numéro exploitable, Peek reste sur le traceur de Huaya ; ajoute le numéro du transporteur local obtenu auprès du vendeur comme second colis. Peek vérifie jusqu'à toutes les 10 minutes quand le colis est ouvert ou le jour de livraison avec notifications actives, et jusqu'à toutes les 2 minutes dès 6 h locales le jour en « En livraison » (sinon, et après 12 h sur « Étiquette créée » / « Pris en charge » ou deux jours calmes, toutes les heures). Parmi les propres lignes de Huaya, seule `航班落地，清关中` (« À la douane ») déclenche « Étapes importantes », tandis que « Livraison seulement » ne s'active que sur les tournées et livraisons recopiées de DHL, de J&T Express ou du partenaire américain : préfère « Chaque scan » ou « Étapes importantes ». Peek ne peut contacter ni Huaya ni le vendeur pour toi.

## Questions fréquentes sur le suivi Huaya Express

### Qu'est-ce que Huaya Express (`华亚运通`) ?

C'est la marque de `华亚运通国际货运代理有限公司` (*Huaya Yuntong International Freight Forwarding*), un transitaire de Canton, en Chine continentale, qui expédie à l'étranger pour des boutiques en ligne via [China Post](carrier:china-post), ses lignes dédouanées taxes comprises vers l'Europe et les États-Unis, des lignes pour marchandises sensibles et les quatre grands expressistes. Ses bordereaux ont la forme `HYYT`, douze chiffres et `GZ`.

### Pourquoi mon suivi Huaya s'arrête-t-il à `It has been handed over to the service provider.` ?

Parce que le rôle de Huaya s'achève là : un transporteur de ton pays a pris le relais sous son propre numéro de suivi, que la ligne de Huaya n'indique pas. Demande au vendeur le nom de ce transporteur et ce second numéro.

### Que signifient les étapes en grisé dans Peek ?

Ce sont des étapes enregistrées sur la plateforme plus de 12 heures avant l'heure qu'elles affichent (ou plus de 6 heures avant dans la même seconde) : des prévisions automatiques, pas des scans confirmés. Peek ne les compte jamais comme une livraison réelle.

### Qui contacter pour un colis Huaya Express manquant ?

Le marchand chez qui tu as passé commande : c'est le client de Huaya et ses conditions de réclamation ne visent que les expéditeurs. Huaya ne publie ses coordonnées qu'en chinois.

:::sources
- [Huaya Express : 华亚运通国际货运代理有限公司 (accueil)](http://hyytgz.com/) – raison sociale et logo, plateformes marchandes, services principaux, liens 物流跟踪 (traceur propre et 17TRACK), ligne 18039073131, e-mail 2065332407@qq.com et adresse à Baiyun (Canton)
- [Huaya Express : 欧洲双清包税小包 (lignes Europe et Royaume-Uni)](http://hyytgz.com/ywfw.php?id=21) – fiches Royaume-Uni reprenant un texte américain terminant par USPS et conditions réservées aux expéditeurs
- [Huaya Express : 澳大利亚专线小包 (ligne Australie)](http://hyytgz.com/ywfw.php?id=27) – fiche Australie acheminant vers les États-Unis pour USPS et conditions expéditeurs
- [Huaya Express : 投诉建议 (réclamations et suggestions)](http://hyytgz.com/message.php) – champs du formulaire (nom, mobile, e-mail, message)
- [Huaya Express : 关于我们 (à propos)](http://hyytgz.com/about.php) – page sans contenu
- [Huaya Express : 服务网点 (agences)](http://hyytgz.com/outlets.php) – page sans contenu
- [Universal Parcel Scraper : Huaya Express](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/huaya-express) – format HYYT…GZ, alias, lien trackIndex.htm, statuts anglais/chinois/suédois, scans DHL et américains, horloges et étapes écrites à l'avance
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – détection dans le catalogue, recherche par référence et affichage dans Peek
:::
