---
title: Numéro de suivi : à quel transporteur appartient-il ?
description: Lettres du début, nombre de chiffres, format postal : reconnais le transporteur derrière un numéro de suivi, de Colissimo à bpost, La Poste suisse ou Cainiao.
slug: quel-transporteur-numero-de-suivi
picture: Trois étiquettes de numéros de suivi découpées en parties colorées, une loupe sur le code pays CH et Pip, curieux.
published: 2026-10-09
updated: 2026-10-27
---

Les lettres et la longueur d'un numéro de suivi suffisent souvent à savoir quel transporteur a ton colis. `1Z` suivi de 16 lettres et chiffres, c'est UPS ; 18 chiffres qui commencent par `99`, c'est [La Poste suisse](carrier:swiss-post) ; 13 caractères construits comme `RR000000005CH` (deux lettres, neuf chiffres, deux lettres) viennent en général d'une poste nationale, et les deux dernières lettres donnent le pays qui a émis le numéro. Les numéros composés uniquement de chiffres sont plus délicats : 10, 12 ou 14 chiffres correspondent à plusieurs transporteurs, et c'est alors l'e-mail d'expédition, ou un outil qui teste chaque candidat, qui tranche.

:::checker
:::

## Comment savoir à quel transporteur appartient un numéro de suivi ?

Quatre vérifications, dans cet ordre :

:::steps
- Lis les lettres | Ce sont elles qui en disent le plus. `1Z` au début, c'est UPS ; deux lettres à la fin désignent en général une poste.
- Compte les caractères | La longueur tranche rarement à elle seule, mais elle élimine des transporteurs. Ne compte pas les espaces : ils ne servent qu'à la lecture, et La Poste comme [Mondial Relay](carrier:mondial-relay) demandent de saisir le numéro sans espace.
- Regarde l'e-mail | L'e-mail ou le SMS d'expédition de la boutique nomme en général le transporteur, ou renvoie vers sa page de suivi. C'est plus fiable que n'importe quelle déduction.
- Teste les candidats | Saisis le numéro sur le site de chaque transporteur possible. Celui qui affiche des scans pour ce numéro a ton colis.
:::

Si tu préfères t'épargner l'enquête, colle le numéro, un lien de transporteur ou tout l'e-mail d'expédition dans [Peek](/) : il repère le numéro et reconnaît le transporteur parmi plus de 3 500. Les autres [suivis de colis universels](guide:universal-tracker) cherchent aussi le transporteur à ta place.

## Les formats de numéro de suivi, transporteur par transporteur

Voici ce que disent les transporteurs et la norme postale sur les numéros que tu croiseras le plus souvent en France, en Belgique et en Suisse romande :

| Transporteur | À quoi ressemble le numéro |
| --- | --- |
| [La Poste](carrier:la-poste), Colissimo | « numéro d'envoi » (ou numéro de suivi) de 13 à 15 caractères : 1 chiffre, 1 lettre et 11 chiffres (`8D00432154789`) ; 2 lettres et 11 chiffres ; le format postal qui finit par `FR` ; ou 14 chiffres |
| [Chronopost](carrier:chronopost) | 13 caractères (2 lettres, 9 chiffres, 2 lettres), ou 15 caractères (14 chiffres et une lettre de contrôle) pour un colis venu de l'étranger, à saisir sans espace ; se suit aussi sur le site et dans l'app La Poste, comme un Colissimo |
| Mondial Relay | « n° d'expédition » de 8 chiffres, à saisir avec le code postal du destinataire ; sa page belge accepte aussi, sans espace, 10 chiffres (un code d'enseigne, puis ce numéro) et 12 (plus le rang du colis) ; le code-barres de 26 chiffres de l'étiquette est propre à Mondial Relay |
| [Colis Privé](carrier:colis-prive) | 12 caractères, lettres et chiffres, à saisir avec ton code postal |
| [Relais Colis](carrier:relais-colis) | « numéro de colis » de 12 à 16 caractères d'après son formulaire de suivi, dont l'exemple commence par `CC` (`CC` puis 10 à 14 chiffres) ; 10 à 16 caractères pour une livraison à domicile |
| [Paack](carrier:paack) | son suivi demande le « numéro de commande » écrit en bas de ses e-mails, sans forme fixe : l'exemple de Paack mêle chiffres, tirets et minuscules, si bien qu'il peut ressembler au numéro d'un autre transporteur ; à recopier tel quel, sans espaces, avec le code postal de livraison |
| [DPD France](carrier:dpd-fr) | « numéro de colis » de 12 à 15 chiffres qui commence par `250`, `1` ou `0` ; sur l'étiquette, le numéro de 15 chiffres en `250` est suivi d'un chiffre de contrôle (16 chiffres). 14 chiffres suivis d'une lettre, ce n'est pas DPD France |
| DPD en Belgique | 14 chiffres |
| [DPD Suisse](carrier:dpd-ch) | « numéro de colis » de 14 chiffres, qui commence par `060` en Suisse d'après DPD, parfois suivi d'un caractère de contrôle (chiffre ou lettre majuscule) dont ses pages suisses ne parlent pas ; le numéro en `P00` de l'avis de passage n'est pas le numéro de colis |
| [GLS France](carrier:gls-fr) | 8 caractères (lettres et chiffres), souvent en commençant par `00`, ou 11 chiffres ; sur l'étiquette, les 11 chiffres sont suivis d'une clé de contrôle (12 chiffres). La référence de 5 ou 6 caractères de l'avis de passage, le Track-ID, sert aussi dans son suivi |
| [GLS en Suisse](carrier:gls-ch) | ni GLS ni La Poste, qui livre ses colis, ne publient de format ; dans les données de transporteurs de Peek, 11 chiffres, 12 chiffres dont le dernier est une clé de contrôle, 8 lettres et chiffres mêlés, ou 6 lettres, à saisir avec ton code postal |
| [UPS](carrier:ups) | `1Z` puis 16 lettres ou chiffres, 18 caractères en tout, comme `1Z999AA10123456784`, un exemple dont le chiffre de contrôle UPS est valide |
| [DHL Express](carrier:dhl-express) | 10 chiffres, jamais de lettres, d'après DHL Express aux Pays-Bas |
| [bpost](carrier:bpost) | « code-barres » de 24 chiffres qui commence par `3299` ou `3232`, de 18 chiffres qui commence par `3232`, de 27 caractères qui commence par `JJBEA`, ou de 13 caractères au format postal (`CE`, `EE`, `CD` ou `CZ` au début) |
| La Poste suisse | « numéro d'envoi » de 18 chiffres qui commence par `98` ou `99`, avec ou sans points ni espaces (le code-barres national de La Poste), ou format postal qui finit par `CH` |
| [Quickpac](carrier:quickpac) | 18 chiffres qui commencent par `44`, que son cadre de suivi écrit avec des points |
| [Planzer](carrier:planzer) | Planzer ne publie aucun format ; dans les données de transporteurs de Peek, 20 chiffres qui commencent par `91346097` ou, pour un envoi partagé, 13 chiffres qui commencent par `99990`, avec ou sans points |
| [YunExpress](carrier:yunexpress) | `YT` puis 16 chiffres ; `YT` suivi de 13 chiffres, c'est YTO Express |
| [4PX](carrier:4px) | `4PX` suivi de 13 chiffres et de `CN` ; 4PX ne publie aucun exemple, et son site accepte de 5 à 50 lettres et chiffres. `LP` suivi de 13 chiffres et de `CN`, c'est probablement 4PX aussi |
| [Yanwen](carrier:yanwen) | Yanwen ne publie aucun exemple ; ses colis portent `BYS` suivi de 9 chiffres, ou deux lettres, 9 chiffres et `YP` (`UL` et `UK` sont des débuts fréquents). `YW`, trois lettres et 12 chiffres, c'est son service de livraison aux États-Unis |
| AliExpress, [Cainiao](carrier:cainiao) | Cainiao ne décrit pas ses numéros ; dans les données de transporteurs de Peek, `DOFR` ou `CNFR` suivi de 13 chiffres et de `HD` ; `LP` ou `CNG` suivi de 14 chiffres, probablement Cainiao, sans certitude (un `CNG` qui finit comme un code postal français peut aussi être Colis Privé) |
| [China Post](carrier:china-post), EMS | format postal qui finit par `CN` ; les numéros EMS internationaux commencent par `E` |
| Amazon | dans les données de transporteurs de Peek, `FR` suivi de 10 chiffres ou `TBA` suivi de 12 chiffres, expliqués plus bas |

### Numéro qui commence par 99 ou 3232 : La Poste suisse ou bpost

Un numéro de 18 chiffres qui commence par `99` vient de La Poste suisse. Son manuel des codes-barres pour les clients commerciaux le découpe en trois : `99`, la référence de La Poste ; 8 chiffres pour la licence d'affranchissement, qui identifie l'expéditeur ; puis 8 chiffres pour le numéro de l'envoi. Il est imprimé avec des points pour être plus lisible, comme `99.34.123456.12345678`.

En Belgique, un numéro de 18 ou 24 chiffres qui commence par `3232`, ou de 24 chiffres qui commence par `3299`, est un code-barres bpost. Un recommandé national bpost a 30 chiffres et commence par `010` ; un recommandé international a 13 caractères et commence par `R`.

## Combien de chiffres compte un numéro de suivi ?

Il n'y a pas de longueur standard. Voici celles que les transporteurs indiquent sur leurs propres pages. Les recoupements montrent pourquoi un numéro composé uniquement de chiffres peut correspondre à plusieurs transporteurs :

| Longueur | Qui l'utilise |
| --- | --- |
| 8 chiffres | Mondial Relay |
| 10 chiffres | DHL Express ; Mondial Relay |
| 11 chiffres | GLS en France ; l'avis de passage de La Poste |
| 12 chiffres | Mondial Relay ; DPD en France (12 à 15) ; GLS en France, sur l'étiquette (11 chiffres et une clé) |
| 12 caractères | Colis Privé |
| 13 caractères | le format postal (`…FR`, `…BE`, `…CH`) ; La Poste (2 caractères, puis 11 chiffres) ; Chronopost |
| 14 chiffres | La Poste ; DPD en Belgique, en France et en Suisse |
| 15 caractères | le numéro « partenaire » d'un envoi international chez La Poste ; DPD en France ; Chronopost, pour un colis venu de l'étranger |
| 18 chiffres | La Poste suisse (`98…` ou `99…`) ; Quickpac (`44…`) ; bpost (`3232…`) |
| 24 chiffres | bpost |
| 26 chiffres | Mondial Relay, pour le code-barres de l'étiquette |
| 30 chiffres | bpost, pour un recommandé national |
| 12 à 16 caractères | Relais Colis (10 à 16 pour une livraison à domicile) |

Le numéro seul ne suffit pas toujours. Mondial Relay, Colis Privé et [Heppner](carrier:heppner) demandent aussi le code postal de destination, tout comme [GEODIS](carrier:geodis) pour son numéro d'envoi à 14 chiffres. Pour une livraison à domicile, Relais Colis veut en plus le site où tu as commandé (l'« enseigne ») et les quatre premières lettres du nom du destinataire. [C Chez Vous](carrier:c-chez-vous), qui livre les meubles et le gros électroménager, se suit avec le numéro de commande reçu par e-mail ou par SMS.

## À qui appartient un numéro qui commence par FR ou TBA ?

En général à Amazon. Dans les données de transporteurs qu'utilise Peek, `FR` suivi de 10 chiffres désigne [Amazon Logistics](carrier:amazon-logistics), le service de livraison d'Amazon, ou [Amazon Shipping](carrier:amazon-shipping) quand Amazon livre pour une autre boutique. Même chose avec un autre code pays européen devant 10 chiffres, comme `BE` ou `CH`, et avec `TBA`, `TBC` ou `TBM` suivi de 12 chiffres. Pour livrer en France, Amazon travaille aussi avec le groupe La Poste, d'autres spécialistes du dernier kilomètre et une centaine d'entreprises de livraison indépendantes. Ta commande, elle, se suit dans « Vos commandes » sur Amazon.

Attention aux sosies. Avec ses 12 caractères, un numéro en `FR` a aussi la forme d'un numéro Colis Privé (lettres et chiffres). Et un numéro La Poste qui finit par `FR` en a 13, avec deux lettres au début et deux à la fin : c'est aussi le cas d'un colis [Delivengo](carrier:delivengo), qu'une boutique française envoie à l'étranger par La Poste. Dans le doute, l'e-mail d'expédition tranche.

## Le format postal : deux lettres, neuf chiffres, deux lettres

Les postes nationales partagent un format de 13 caractères, fixé par l'Union postale universelle (UPU) dans sa norme S10. Le voici décomposé :

:::anatomy RR 00000000 5 CH
- RR | Type d'envoi : R pour une lettre recommandée
- 00000000 | Numéro de série, huit chiffres
- 5 | Chiffre de contrôle, calculé à partir du numéro de série
- CH | Pays qui a émis le numéro : la Suisse
:::

Pour les envois internationaux, la première lettre indique le type d'envoi. Dans leur propre pays, les postes peuvent donner un autre sens à ces lettres.

- `E` : EMS, le service express des postes
- `C` : colis
- `R` : lettre recommandée
- `L` : lettre suivie
- `V` : lettre avec valeur déclarée
- `U` : marchandises envoyées comme une lettre, sans suivi pour toi

Les deux dernières lettres ne disent pas toujours d'où part le colis. Selon l'UPU, ce code n'est pas un indicateur fiable de l'origine géographique d'un envoi : La Poste suisse met `CH` sur les envois qu'elle expédie depuis ses bureaux à l'étranger, et la poste portugaise CTT donne des codes qui finissent par `PT` à des envois que ses clients sous contrat expédient d'Espagne, de Chine ou d'ailleurs.

Le chiffre de contrôle sert à repérer les fautes de frappe. Pour tester un numéro :

1. Multiplie les huit chiffres du numéro de série par 8, 6, 4, 2, 3, 5, 9 et 7, puis additionne les résultats.
2. Divise la somme par 11 et soustrais le reste de 11.
3. Un résultat de 10 devient 0, et 11 devient 5.

Pour `RR000000005CH`, les huit chiffres du numéro de série valent 0 : la somme fait 0, il ne reste rien après la division par 11, et 11 − 0 = 11, qui devient 5. Si un numéro postal recopié à la main ne passe pas ce test, cherche un chiffre mal tapé ou deux chiffres inversés.

### Numéro de suivi qui commence par U

S'il compte 13 caractères et finit par deux lettres, `CN` par exemple, c'est un numéro postal de la série `U` : des marchandises envoyées comme une lettre. Les deux dernières lettres indiquent quelle poste l'a émis. Un numéro plus long qui commence par `UU` n'a pas ce format : cherche le nom du transporteur dans l'e-mail d'expédition.

> Sur un envoi venu de l'étranger, un numéro postal qui commence par `U` (`UA` à `UZ`) n'est pas fait pour être suivi. L'UPU réserve cette série aux marchandises envoyées comme lettres, sans suivi pour le client, et bpost le dit sans détour : ce code-barres sert seulement à présenter le colis à la douane belge, et tu ne pourras pas le suivre. bpost traite ensuite l'envoi comme une lettre, livrée au plus tard 5 jours ouvrables après [le dédouanement](guide:customs). Ne compte pas sur un suivi qui démarrerait plus tard.

## Les numéros qui ne sont pas ton numéro de suivi

Le numéro de commande est celui de la boutique, pas du transporteur : chez Colis Privé Store, par exemple, il commence en général par `HS` ou `2R` et ne se suit que sur le site du Colis Privé Store. Quant à la mention « SD », imprimée à côté de certains numéros La Poste, elle ne fait pas partie du numéro : ne la saisis pas. Pour trouver le bon, dans ta commande ou sur un avis de passage, lis [où trouver ton numéro de suivi](guide:find-tracking-number).

## Pourquoi le numéro de suivi peut changer en route

Un colis qui franchit une frontière passe souvent de main en main, et chaque société peut imprimer sa propre étiquette. L'UPU permet à la poste de destination d'ajouter son propre code-barres à côté de celui d'origine, à condition qu'il n'ait pas le format postal de 13 caractères. Sa norme en montre un exemple : `98.00.802077.23271453`, relié au numéro d'origine.

:::journey
- shop | Vendeur | Imprime l'étiquette et le premier numéro
- plane | Long-courrier | Voyage sous ce premier numéro
- customs | Douane | Contrôlé dans ton pays
- handover | Transporteur local | Peut ajouter sa propre étiquette et son numéro
- home | Chez toi | Les derniers scans peuvent apparaître sous le nouveau numéro
:::

- **Les envois postaux** sont le cas simple : l'UPU n'autorise qu'un numéro S10 par envoi, qui fonctionne donc en général sur les sites des deux postes.
- **La Poste** accepte aussi le numéro « partenaire » d'un envoi international, s'il lui a été transmis : 15 caractères, soit 15 chiffres, soit 14 chiffres et une lettre.
- **DPD :** certains colis expédiés de l'étranger par une autre filiale DPD sont pris en charge par Chronopost à leur arrivée en France. Si le site de DPD France ne reconnaît pas le numéro, il te renvoie vers le suivi Chronopost.
- **AliExpress en livraison économique :** Cainiao indique que le suivi s'arrête à la remise au transporteur local, et que le vendeur peut te dire comment joindre ce transporteur. Plus de détails dans [suivre un colis venu de Chine](guide:tracking-from-china).

Un numéro « non reconnu » n'est pas forcément mal tapé. Chez Colis Privé, le statut `Colis attendu par Colis Privé` veut dire que l'expéditeur ne lui a pas encore remis le colis. DPD France précise qu'un numéro qui reste « inconnu », même sur le suivi Chronopost, est soit erroné, soit hors du réseau Geopost. Si un numéro valide ne bouge simplement pas encore, lis [pourquoi le suivi ne bouge plus](guide:tracking-not-updating).

:::sources
- [UPU : norme S10, Identification of postal items (en anglais)](https://www.upu.int/UPU/media/upu/files/postalSolutions/programmesAndServices/standards/S10-12.pdf) – le format postal, ses lettres, le chiffre de contrôle, le code pays et le code-barres ajouté à destination
- [La Poste : Comment suivre mon colis ou ma lettre ?](https://aide.laposte.fr/professionnel/contenu/comment-suivre-mon-colis-ou-ma-lettre) – les quatre formes du numéro d'envoi, la mention SD, l'avis de passage à 11 chiffres, le numéro partenaire à 15 caractères, la saisie sans espace
- [La Poste : Où trouver le numéro de mon courrier Suivi ?](https://aide.laposte.fr/contenu/ou-trouver-le-numero-de-mon-courrier-suivi) – 13 à 15 caractères
- [La Poste : Suivre une lettre, un Colissimo ou un Chronopost](https://www.laposte.fr/conseils-pratiques/suivre-une-lettre-un-colissimo-un-envoi-express) – Chronopost suivi sur le site et dans l'app La Poste
- [Mondial Relay : Où retrouver mon numéro de colis ?](https://www.mondialrelay.fr/faq/recevoir-un-colis/ou-trouver-mon-numero-de-colis/) – le n° d'expédition à 8 chiffres
- [Mondial Relay : Étiquette](https://storage.mondialrelay.fr/etiquette-mondial-relay-v-24.pdf) – le code-barres de 26 chiffres
- [Mondial Relay Belgique : Suivi de colis](https://www.mondialrelay.be/fr-be/suivi-de-colis/) – 8, 10 ou 12 chiffres, sans espace, avec le code postal de destination
- [Colis Privé : Comment suivre mon colis ?](https://colisprive.fr/aide/comment-suivre-mon-colis-3/) – 12 caractères, code postal, commandes HS ou 2R, statut « Colis attendu par Colis Privé »
- [Chronopost : Où trouver mon numéro de colis ?](https://www.chronopost.fr/fr/faq/destinataire/ou-trouver-mon-numero-de-colis) – 13 ou 15 caractères, sans espace
- [Relais Colis : Comment suivre mon colis ?](https://www.relaiscolis.com/faq/question/comment-suivre-mon-colis) – 12 à 16 caractères
- [Relais Colis : Livraison à domicile, comment suivre mon colis ?](https://www.relaiscolis.com/faq/question/comment-suivre-mon-colis-livraison-a-domicile) – 10 à 16 caractères, enseigne et nom du destinataire
- [Paack : Track your order](https://paack.co/shipments/tracking?lng=fr) – numéro de commande et code postal
- [Paack Support : assistant](https://help.paack.app/) – numéro dans l'e-mail, copie exacte
- [DPD France : Où puis-je trouver mon numéro de colis ?](https://www.dpd.com/fr/fr/faq/ou-puis-je-trouver-mon-numero-de-colis/) – 12 à 15 chiffres commençant par 250, 1 ou 0
- [DPD France : Le numéro de mon colis en provenance de l'étranger n'est pas reconnu](https://www.dpd.com/fr/fr/faq/le-numero-de-mon-colis-en-provenance-de-letranger-nest-pas-reconnu-comment-faire/) – colis repris par Chronopost, numéro « inconnu »
- [DPD Belgique : Qu'est-ce que le numéro de colis et où puis-je le trouver ?](https://www.dpd.com/be/fr/faq/quest-ce-que-le-numero-de-colis-et-ou-puis-je-le-trouver-3/) – 14 chiffres
- [myDPD : Aide](https://www.dpdgroup.com/ch/mydpd/api/help?lang=fr) – numéro de colis, avis de passage
- [DPD Suisse : Responsabilité envers l'expéditeur](https://www.dpd.com/ch/fr/centre-de-support/tout-sur-la-responsabilite/expediteur/) – numéros commençant par 060
- [DPD Suisse : Des questions ou des demandes ? Contactez-nous](https://www.dpd.com/ch/fr/centre-de-support/votre-demande/) – numéro d'avis P00
- [GLS France : Besoin d'aide](https://gls-group.com/FR/fr/recevoir-colis/aide/) – 8 caractères ou 11 chiffres
- [GLS : Parcel Tracking (en anglais)](https://gls-group.com/EU/en/parcel-tracking) – numéros de colis, Track ID, code postal
- [bpost : À quoi ressemble un code-barres et où puis-je le trouver ?](https://www.bpost.be/fr/faq/quoi-ressemble-un-code-barres-et-ou-puis-je-le-trouver) – les formes du code-barres, les recommandés, les codes en U livrés comme des lettres
- [bpost : Puis-je suivre mon colis en ligne ?](https://www.bpost.be/fr/faq/puis-je-suivre-mon-colis-en-ligne) – les codes-barres en U ne se suivent pas
- [La Poste suisse : Suivre un envoi](https://www.post.ch/fr/reception/suivi-des-envois) – le numéro d'envoi
- [La Poste suisse : Barcode for Business Customers (en anglais)](https://handbuch.post.ch/-/media/post/gk/dokumente/1864-Anleitung-Barcodes-GK.pdf) – 18 chiffres, 99, licence d'affranchissement et numéro d'envoi, points pour la lecture
- [UPS : exemple d'étiquette (en anglais)](https://www.pld-certify.ups.com/CerttoolHelp/PLD0200/WebHelp_pld0200/LeadPackage.htm) – le numéro 1Z
- [DHL Express Pays-Bas : DHL Express or DHL eCommerce number (en anglais)](https://www.dhlexpress.nl/en/consumer/faq/express-account-zendingsnummer/my-shipment-number-dhl-express-or-dhl-ecommerce) – 10 chiffres
- [About Amazon France : Les clients Amazon mettent leurs livreurs à l'honneur](https://www.aboutamazon.fr/actualites/activite-logistique/les-clients-amazon-mettent-leurs-livreurs-a-lhonneur) – le groupe La Poste, d'autres spécialistes du dernier kilomètre et une centaine d'entreprises de livraison indépendantes (2022)
- [Universal Parcel Scraper : catalogue des transporteurs](https://github.com/plhery/universal-parcel-scraper/blob/main/data/catalog.json) – les formes FR, TBA, LP, CNG, DOFR, CNFR et YT (16 chiffres pour YunExpress, 13 pour YTO Express), 44 pour Quickpac, 91346097 et 99990 pour Planzer, les formes de GLS en Suisse, le caractère de contrôle de DPD Suisse, les formes 4PX et LP de 4PX, `CC` pour Relais Colis, 98 ou 99 pour La Poste suisse, BYS, YP et YW pour Yanwen, et la clé des numéros de DPD France et de GLS France dans les données de Peek
- [Quickpac : FAQ (en anglais)](https://quickpac.ch/en/faq) – numéro de colis à 18 chiffres
- [YunExpress : YunTrack](https://www.yuntrack.com/) – numéros commençant par YT
- [Yanwen : YW Tracking (en anglais)](https://track.yw56.com.cn/en/index) – le suivi de Yanwen, sans exemple de numéro
- [4PX : Global Order Tracking (en anglais)](https://track.4px.com/) – 5 à 50 lettres et chiffres
- [Cainiao : centre d'aide](https://global.cainiao.com/helpDoc.htm) – pas de suivi après la remise au transporteur local en livraison économique
- [CTT : Encontrar o código de envio (en portugais)](https://www.ctt.pt/ajuda/particulares/seguir-ou-alterar-entrega/seguir/encontrar-o-codigo-de-envio) – codes en PT pour des envois partis de l'étranger
:::
