---
title: Suivi 360Lion : codes WSH et AR, statuts et contacts
description: Suis un colis 360Lion Express de Chine vers l’Amérique latine ou les États-Unis : formats WSH et AR, codes de scan, dernier kilomètre et aide.
slug: suivi-360lion
published: 2026-11-24
updated: 2026-11-24
---

360Lion (*360Lion Express*, en chinois *纬狮物流 / 纬狮物联网*) est un prestataire chinois de logistique e-commerce transfrontalière fondé en 2015 et basé à Shenzhen, avec des agences et centres d’exploitation à Shanghai, Canton, Dongguan, Yiwu et Hong Kong. Il est spécialisé dans les lignes aériennes dédiées aux petits colis, le fret FBA/FBM et les entrepôts à l’étranger depuis la Chine vers l’Amérique latine et les États-Unis (Mexique, Chili, Pérou, Brésil, Colombie, Équateur, Argentine, Uruguay, Bolivie, Paraguay, Salvador, Costa Rica, Guatemala, Venezuela, Panama et États-Unis) pour des marchands présents sur Mercado Libre, Amazon, AliExpress, Shopee, SHEIN, Temu, TikTok Shop, Walmart et Falabella. Tu peux consulter tes envois sur [le portail de suivi 360Lion](https://toms.360lion.com/track) ou [360lion.com/en/](https://www.360lion.com/en/) (jusqu’à 50 numéros à la fois), ou coller ton numéro de suivi dans le champ ci-dessus.

## Numéros de suivi 360Lion : formats WSH et AR

Cherche ton numéro de suivi sur la page de commande de la place de marché ou dans l’e-mail de confirmation d’expédition. Les colis 360Lion utilisent couramment deux formats :

| Format (inventé) | Ce que c’est |
| --- | --- |
| `WSHMX4829173640YQ` | Bordereau de ligne dédiée 360Lion : `WSH`, code pays de destination à deux lettres (par exemple `MX` pour le Mexique), 10 chiffres et `YQ`. |
| `AR4829173640` | `AR` suivi de 10 chiffres (utilisé pour les colis à destination de l’Argentine). Partage ce format avec [Colis Privé](carrier:colis-prive), [Aramex Australia](carrier:aramex-au) et [Aramex New Zealand](carrier:aramex-nz). |

Si ta commande vers le Brésil ou le Mexique porte le code-barres d’un autre transporteur — comme `AJ` pour [Anjun Logistics](carrier:anjun), `8880` pour [J&T Express Brazil](carrier:j-and-t-br) ou `JMX` pour [J&T Express Mexico](carrier:j-and-t-mx) — consulte [les formats de numéros de suivi](guide:tracking-number-formats).

## Signification des statuts de suivi 360Lion

360Lion regroupe les envois en grands états (`Processing`, `Pre-alert`, `In transit.`, `Returned`, `Delivered`) accompagnés de codes de scan (`WSO*` au départ en Chine, `WSD*` à destination). Une fois le colis confié au transporteur local du dernier kilomètre, le suivi peut reprendre les libellés propres à ce partenaire.

| Ce qu’affiche 360Lion (code / groupe) | Ce que ça signifie pour toi | Ce qu’affiche Peek |
| --- | --- | --- |
| `Processing` (`PRECESSING`) | Le vendeur a créé la commande d’expédition chez 360Lion avant les premiers scans de ligne. | « Étiquette créée » |
| Étape `HUB` | Pris en charge dans le centre de tri d’origine de 360Lion en Chine. | « Pris en charge » |
| `departed from airport` (`WSO04`), `arrived at airport` (`WSD00`), `In transit.` (`IN_TRANSIT`) | Parti en avion de Chine (avec code aéroport comme PVG) puis atterri à l’aéroport de destination. | « En transit » |
| Étape `CUSTOMS` | En cours de dédouanement à l’export ou à l’importation dans le pays de destination. | « À la douane » |
| `handover to lastmile` (`WSD02`), `Arrived at sorting center` (`WSD051`), `package on the way to delivery office` (`WSD050`) | Transféré au partenaire de livraison du dernier kilomètre, trié puis envoyé vers l’agence locale. | « En transit » |
| `Returned` (`RETURNED_BACK`) | Renvoyé vers l’expéditeur ou l’entrepôt d’origine. | « Retourné à l’expéditeur » |
| `Delivered` (`DELIVERED`) | Livré au destinataire par le partenaire local du dernier kilomètre. | « Livré » |

Note que l’état `Pre-alert` (`WARNING` / *预警*) de 360Lion peut signifier soit une pré-annonce, soit une alerte ; Peek s’appuie donc sur les lignes de scan détaillées pour déterminer l’étape plutôt que de traiter `Pre-alert` seul comme un incident.

## Comment fonctionne la livraison transfrontalière 360Lion

Sur [360lion.com/en/](https://www.360lion.com/en/) et [360lion.com](https://www.360lion.com/), 360Lion se présente comme un transporteur transfrontalier et agent général de ventes et services aériens (GSSA) opérant des vols charters réguliers entre la Chine et l’Amérique latine, appuyé par des entrepôts au Mexique, au Chili, au Brésil, en Colombie et aux États-Unis.

**Livraison du dernier kilomètre.** Après l’aéroport de destination (`WSD00`) et le dédouanement import, 360Lion remet le colis (`WSD02`) à un transporteur local dans ton pays, qui effectue la livraison finale.

**Règles destinataires et douane.** Comme 360Lion travaille avec des expéditeurs professionnels plutôt qu’avec des acheteurs particuliers, son site ne publie ni limite de tentatives de livraison, ni délais de garde en point relais, ni délais garantis pour les petits colis (sa [page FAQ et conditions](https://www.360lion.com/?m=home&c=Lists&a=index&tid=82) couvre les règles vendeurs telles que le fret aérien SEND, les marchandises interdites et le délai de réclamation vendeur de 30 jours). La TVA et les droits d’importation suivent la réglementation douanière de ton pays. Consulte [colis retenu en douane](guide:customs).

## Colis 360Lion bloqué, silencieux ou non livré

Comme 360Lion contracte avec la boutique ou la place de marché plutôt qu’avec l’acheteur, contacte toujours d’abord le vendeur ou la plateforme en cas de retard, d’erreur d’adresse ou de demande de remboursement.

**Pas de mise à jour entre la Chine et la destination.** Les vols long-courriers entre la Chine et l’Amérique latine ainsi que les files d’attente en douane peuvent laisser plusieurs jours sans scan entre `departed from airport` (`WSO04`) et `arrived at airport` (`WSD00`). Consulte [pourquoi le suivi ne se met plus à jour](guide:tracking-not-updating).

**Remise locale sans second numéro.** Peek suit les lignes du transporteur local à travers le flux 360Lion sous ton code `WSH` ou `AR` et n’affiche pas de second numéro local. Si la livraison échoue et que tu as besoin du contact ou de la référence locale, demande au vendeur ou écris au service de recherche colis de 360Lion.

**Livré, endommagé ou perdu.** Vérifie d’abord auprès de ton foyer, tes voisins et la réception de l’immeuble si le colis indique `Delivered` ; s’il est manquant ou abîmé, documente le problème et ouvre un litige auprès du vendeur ou de la place de marché avant la fin de la protection acheteur. Consulte [colis livré mais non reçu](guide:delivered-not-received), [colis endommagé](guide:damaged-parcel), [colis perdu](guide:lost-parcel) et [retourné à l’expéditeur](guide:returned-to-sender).

## Service client et contacts 360Lion

Coordonnées publiées sur [la page Contact de 360Lion](https://www.360lion.com/?m=home&c=Lists&a=index&tid=79) et [360lion.com/en/](https://www.360lion.com/en/) :

- **E-mail recherche colis (*查件业务*) :** `customerservice@360lion.com`.
- **E-mail général / commercial :** `info@360lion.com`.
- **Téléphone siège de Shenzhen :** `+86 755 2360 1325` (Shanghai `+86 21 6142 1360`, Canton `+86 20 8653 4133`).
- **Adresse du siège :** 7/F, Block A, Star River World, No. 1 Yabao Road, Bantian Street, Longgang District, Shenzhen, Guangdong, Chine.

> Méfie-toi des faux SMS ou messages WhatsApp exigeant de payer via un lien urgent pour « libérer » un colis international en douane. Vérifie toujours toute demande de taxe via ta page de commande ou le portail postal/douanier officiel de ton pays. Consulte [les SMS d’arnaque à la livraison](guide:delivery-scam-texts).

## Suivre un colis 360Lion avec Peek

[Peek](/) interroge automatiquement 360Lion lorsque tu saisis un code `WSH...YQ` (comme `WSHMX4829173640YQ`, chiffres inventés) — dont 360Lion est le seul candidat — ou un code `AR` + 10 chiffres (comme `AR4829173640`), pour lequel Peek interroge 360Lion aux côtés de [Colis Privé](carrier:colis-prive), [Aramex Australia](carrier:aramex-au) et [Aramex New Zealand](carrier:aramex-nz) et affiche « 360Lion a ce colis. » lorsque 360Lion répond. Coller un lien `toms.360lion.com/track?trackNumber=` sélectionne directement 360Lion ; sinon appuie sur « Changer » à l’ajout ou « Changer de transporteur » sur la fiche du colis puis choisis 360Lion (rechercher « 360Lion Express », « WSH » ou « 纬狮物流 » le trouve aussi).

Peek lit directement le suivi 360Lion et affiche l’étape (« Étiquette créée », « Pris en charge », « En transit », « À la douane », « Retourné à l’expéditeur » ou « Livré »), le libellé de chaque scan (celui du transporteur du dernier kilomètre lorsque 360Lion le relaie, sinon l’anglais de 360Lion), le lieu sous forme de pays ou de code aéroport à trois lettres, le pays de destination et l’heure de livraison. Chaque scan porte le décalage horaire du lieu, comme `+8` en Chine et `-3` en Argentine, et s’affiche à l’heure de ton appareil. Peek écarte la barre d’avancement du site, le pays d’origine, le compteur de jours et la preuve de livraison ; le suivi 360Lion n’indique ni date estimée, ni nom de service, ni poids.

Tant que le colis est ouvert dans Peek, ou le jour de la livraison avec les notifications activées, Peek vérifie jusqu’à toutes les 10 minutes tant que le suivi 360Lion répond. Sinon, Peek vérifie toutes les heures. Après 12 heures avec seulement « Étiquette créée » ou « Pris en charge », ou deux jours sans nouvelles, il vérifie toutes les heures quelle que soit ta façon de le suivre, et toutes les 6 heures après sept jours sur « Étiquette créée ». Lorsque le suivi 360Lion ne répond pas et que Peek suit le colis uniquement via des services de suivi, il vérifie toutes les heures, toutes les 3 heures après une journée calme, et jusqu’à toutes les 15 minutes le jour de la livraison tant que le colis est ouvert ou que les notifications sont activées.

Lorsque le dernier scan date de quatre jours et que le colis reste à « Étiquette créée », « Pris en charge » ou « En transit », Peek le signale avec la date de ce scan, par exemple « Aucune mise à jour depuis lun. 12 oct. ». La fiche du colis affiche alors « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger 360Lion. », avec un lien « Demande de l’aide à 360Lion » vers la page de contact de 360Lion. Après trois semaines sans nouveau scan, elle indique « Rien de nouveau depuis plus de trois semaines. Si le colis n’est toujours pas arrivé, contacte le vendeur. Peek continue d’interroger 360Lion. »

Tu peux choisir les réglages de notification « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Peek assure uniquement le suivi et ne peut ni dédouaner un colis ni contacter 360Lion ou ton transporteur local à ta place.

## Questions sur le suivi 360Lion

### Qu’est-ce que 360Lion Express ?

360Lion (fondé en 2015 à Shenzhen, Chine) est un transporteur e-commerce transfrontalier spécialisé dans les lignes aériennes de petits colis entre la Chine, l’Amérique latine et les États-Unis, utilisé par des vendeurs sur Mercado Libre, Amazon, AliExpress, Shopee, SHEIN et Temu.

### Que signifient les numéros `WSH...YQ` et `AR...` ?

`WSH` + deux lettres de pays + 10 chiffres + `YQ` (par exemple `WSHMX4829173640YQ`, chiffres inventés) est le format de ligne dédiée transfrontalière de 360Lion, tandis que `AR` + 10 chiffres (`AR4829173640`) sert aux colis 360Lion à destination de l’Argentine.

### Qui livre localement les colis 360Lion ?

360Lion achemine les colis par avion depuis la Chine jusqu’à l’aéroport de ton pays puis les confie (`handover to lastmile`) à une poste ou un transporteur local pour la livraison finale. Les scans du partenaire local apparaissent dans le suivi 360Lion.

### Comment contacter 360Lion pour un colis manquant ?

Demande d’abord au vendeur ou à la place de marché pour faire jouer la protection acheteur et les remboursements. Tu peux aussi écrire au service recherche colis de 360Lion à `customerservice@360lion.com` ou appeler le bureau de Shenzhen au `+86 755 2360 1325`.

:::sources
- [360Lion Express : English Home](https://www.360lion.com/en/) – fondé en 2015, agences Shenzhen/Shanghai/Canton/Yiwu/Dongguan/Hong Kong, suivi de 50 numéros, partenaires marketplaces et adresse du siège
- [360Lion : Chinese Home](https://www.360lion.com/) – lignes petits colis dédiées vers 15 pays d’Amérique latine et les États-Unis, capacité charter aérienne et entrepôts à l’étranger
- [360Lion : Contact Us](https://www.360lion.com/?m=home&c=Lists&a=index&tid=79) – `customerservice@360lion.com`, `info@360lion.com`, téléphones et adresses Shenzhen/Canton/Shanghai (URL d’aide liée par Peek)
- [360Lion : About Us](https://www.360lion.com/?m=home&c=Lists&a=index&tid=78) – présentation de l’entreprise
- [360Lion : Dedicated Small Parcels](https://www.360lion.com/?m=home&c=Lists&a=index&tid=90) – service B2C de petits colis transfrontaliers
- [360Lion : FAQ & Terms](https://www.360lion.com/?m=home&c=Lists&a=index&tid=82) – conditions vendeurs, articles interdits et réclamation vendeur sous 30 jours
- [360Lion : Tracking Portal](https://toms.360lion.com/track) – portail de suivi public
- [Universal Parcel Scraper : 360lion](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/360lion) – détection `WSH` et `AR`, règles de lien `toms.360lion.com`, codes de scan `WSO`/`WSD`, fuseaux horaires explicites, champs ignorés et URL d’aide
:::
