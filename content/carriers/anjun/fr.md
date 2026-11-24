---
title: Suivi Anjun Logistics : statuts Chine-Brésil et contact SAC
description: Suis un colis Anjun Logistics ou Anjun Express : numéros AJ, statuts Chine et Brésil, douane Remessa Conforme et contacts SAC actifs.
slug: suivi-anjun-logistics
published: 2026-11-24
updated: 2026-11-24
---

Anjun Logistics (opérant au Brésil sous le nom d'Anjun Express) est un transporteur e-commerce transfrontalier et domestique fondé en 2011 en Chine, implanté au Brésil depuis 2019 et étendu à l'ensemble du territoire brésilien en 2025. Il assure la collecte en Chine, l'exportation aérienne, le dédouanement au Brésil et la livraison finale dans le pays — soit via son propre réseau et ses points de collecte, soit en remettant les colis à [Correios](carrier:correios-br) après le dédouanement. Tu peux suivre un envoi international sur le [suivi mondial d'Anjun](https://anjuntrack.com/trajectory), un envoi domestique brésilien sur [Anjun Express Brésil](https://anjunexpress.com/trackPackage), ou coller ton numéro dans le champ ci-dessus.

## Numéro de suivi Anjun : format et où le trouver

Ton numéro de suivi figure dans l'avis d'expédition de la marketplace ou de la boutique en ligne (notamment pour les commandes internationales issues de plateformes chinoises vers le Brésil). Les numéros propres d'Anjun comptent 17 caractères : `AJ`, une date à 6 chiffres (`AAMMJJ`) et 9 chiffres.

| Format (inventé) | Ce que c'est |
| --- | --- |
| `AJ261015482917364` | Code Anjun Logistics / Anjun Express : `AJ` + date à 6 chiffres + 9 chiffres. |

Anjun exploite deux portails de suivi : [anjuntrack.com](https://anjuntrack.com/trajectory) couvre les envois transfrontaliers Chine-Brésil, tandis que [anjunexpress.com](https://anjunexpress.com/trackPackage) porte sur la livraison finale et les colis domestiques brésiliens. Si ta commande comporte un code `BR` de 15 caractères, vérifie [SPX Express Brazil](carrier:spx-br) ; un code de 15 chiffres commençant par `8880` appartient à [J&T Express Brazil](carrier:j-and-t-br) ; et un code postal de 13 caractères finissant par `BR` appartient à [Correios](carrier:correios-br). Consulte [les formats de numéros de suivi](guide:tracking-number-formats) pour reconnaître d'autres préfixes.

## Signification des statuts Anjun Logistics

Les colis transfrontaliers Anjun passent habituellement de lignes de suivi en anglais en Chine et à Hong Kong à des lignes en portugais une fois arrivés au Brésil.

| Anjun indique | Ce que cela signifie pour toi | Peek affiche |
| --- | --- | --- |
| `Objeto postado` | Pré-alerte électronique ou dépôt enregistré avant l'entrée en entrepôt. | « Étiquette créée » |
| `The goods have arrived at Anjun` ; `Objeto coletado` comme premier scan d'un colis expédié à l'intérieur du Brésil | L'entrepôt d'Anjun en Chine a reçu le colis, ou Anjun l'a collecté auprès d'un expéditeur au Brésil. | « Pris en charge » |
| `The goods have been issued`, `Departed Facility in SHENZHEN`, `Arrived at Sort Facility HONG KONG INTERNATIONAL AIRPORT`, `Departed Facility in HONG KONG INTERNATIONAL AIRPORT`, `Arrived at Sort Facility Destination Airport` | Traitement export, départ aérien depuis Shenzhen ou Hong Kong et arrivée à l'aéroport de destination au Brésil. | « En transit » |
| `Recebimento de cargas no armazém courier` | Reçu au terminal courrier au Brésil, avant la douane. | « En transit » |
| `Entrada no armazém alfândegado de TFK`, `Informações enviadas para análise da autoridade aduaneira/órgãos anuentes`, `Análise concluída - importação autorizada` | Entrée en entrepôt sous douane, déclaration transmise aux douanes et fin de l'examen douanier. | « À la douane » |
| `Pacote Liberado Aguadando a Coleta de Last Mile`, `Saída do Centro Internacional`, `Coletado por Last Mile`, `Objeto coletado`, `Objeto recebido pelos Correios do Brasil` | Libéré par la douane et remis au réseau final d'Anjun ou à Correios pour la livraison nationale. | « En transit » |
| `O pacote chegou ao centro de trânsito`, `O pacote saiu do centro de trânsito`, `Objeto chegou ao ponto de entrega` | Acheminement entre centres de transit brésiliens et arrivée au dépôt local (pas encore en tournée). | « En transit » |
| `Objeto saiu para entrega ao destinatário` | Un livreur local est parti livrer ton colis aujourd'hui. | « En livraison » |
| `A entrega falhou` (par exemple `Cliente ausente` ou `Endereço fora da rota`) | La livraison n'a pas pu aboutir (destinataire absent ou adresse hors tournée). | « Tentative de livraison » |
| `A entrega falhou: Pacote não pertence à Base` | Le colis est arrivé dans la mauvaise base locale et est réacheminé plutôt que présenté à ton adresse. | « En transit » |
| `O pacote está a caminho do centro de trânsito para devolução.` | Retour vers un centre de transit après des échecs de livraison ; une nouvelle tentative reste possible. | « Problème signalé » |
| `O pacote foi assinado`, `Objeto entregue ao destinatário` | Anjun a enregistré le colis comme signé et livré. | « Livré » |

Note que `Objeto chegou ao ponto de entrega` signifie que le colis a atteint le dépôt local d'Anjun — et non un comptoir de retrait client — : n'attends le livreur qu'après `Objeto saiu para entrega ao destinatário`.

## Réseau et livraison Anjun Express au Brésil

Sur le [site brésilien d'Anjun Express](https://anjunexpress.com/home), l'entreprise présente la collecte premier kilomètre, la [livraison dernier kilomètre](https://anjunexpress.com/lastMile), la logistique B2B, l'entreposage, l'expédition directe et Super Express ; sa page dernier kilomètre mentionne un entrepôt sous douane de 25 000 m² à l'aéroport GRU de São Paulo. Sa page d'accueil indique plus de 1 000 points de collecte, plus de 350 véhicules et plus de 2 000 employés (sa [page partenaires points de collecte](https://anjunexpress.com/collectionPoint) cite plus de 500 points).

Anjun Express ne publie sur ses pages publiques ni nombre fixe de tentatives de livraison, ni délai garanti, ni durée de garde. Pour les délais exacts ou règles de tentative sur une commande en ligne, vérifie la page de commande dans la boutique ou la marketplace où tu as acheté.

## Dédouanement et Remessa Conforme au Brésil

Comme la plupart des colis Anjun arrivent au Brésil depuis la Chine, ils passent la douane brésilienne selon le [Programa Remessa Conforme (PRC) de la Receita Federal](https://www.gov.br/receitafederal/pt-br/assuntos/aduana-e-comercio-exterior/manuais/remessas-postal-e-expressa/programa-remessa-conforme-o-que-e-como-funciona) :

- **Marketplace ou boutique certifiée :** les taxes sont réglées au paiement. Les envois aux particuliers jusqu'à 50 USD (marchandise, port et assurance) supportent 0 % de droit fédéral d'importation plus 17 % à 20 % d'ICMS selon l'État. Au-dessus de 50 USD, le droit d'importation est de 60 % avec une remise de 30 USD sur l'impôt, plus 17 % à 20 % d'ICMS.
- **Boutique non certifiée :** 60 % de droit fédéral sans remise (même jusqu'à 50 USD) plus 17 % à 20 % d'ICMS, facturés à l'entrée au Brésil avant la libération.

Tant que ton colis affiche `Entrada no armazém alfândegado de TFK` ou `Informações enviadas para análise da autoridade aduaneira/órgãos anuentes`, la Receita Federal ou des organismes partenaires contrôlent la déclaration. En cas de demande de documents ou de paiement, vérifie les messages de ta commande et le contact douanier d'Anjun plutôt que de payer via un lien reçu au hasard. Voir [les colis bloqués en douane](guide:customs).

## Colis Anjun bloqué, échec de livraison ou manquant

Pour les achats effectués sur des marketplaces, la plateforme ou le vendeur gère les remboursements, renvois et litiges douaniers.

**Aucun scan entre la Chine et le Brésil.** L'exportation aérienne, l'attente aéroportuaire et l'entrée en entrepôt sous douane créent couramment plusieurs jours d'intervalle entre `Departed Facility in HONG KONG INTERNATIONAL AIRPORT` et `Recebimento de cargas no armazém courier`. Voir [pourquoi le suivi ne se met plus à jour](guide:tracking-not-updating).

**Acheminé vers la mauvaise base.** `A entrega falhou: Pacote não pertence à Base` peut sembler inquiétant, mais cela signifie que le colis a été trié vers le mauvais dépôt local et doit être transféré en interne avant toute véritable tentative chez toi.

**Livré mais non reçu.** Vérifie d'abord auprès de tes proches, voisins et de la *portaria* (réception de l'immeuble), puis signale-le à la marketplace et au SAC d'Anjun. Voir [colis livré mais non reçu](guide:delivered-not-received).

**Endommagé, perdu ou en retour.** Garde des photos de l'emballage endommagé et contacte vite le vendeur ; voir [colis endommagé](guide:damaged-parcel), [colis perdu](guide:lost-parcel) et [retourné à l'expéditeur](guide:returned-to-sender).

## Service client Anjun Express et nouvelle adresse e-mail

Sur [anjunexpress.com/home](https://anjunexpress.com/home), Anjun Express annonce la désactivation de son ancienne adresse `sac@anjunexpress.com.br` depuis le 12 août 2026 et liste ses canaux brésiliens actifs :

- **Téléphone SAC national :** `(11) 5026-7008`, du lundi au vendredi de 08h00 à 17h30 (hors jours fériés).
- **E-mail SAC :** `sac@anjun.com.br`.
- **Chat en ligne :** disponible sur [anjunexpress.com](https://anjunexpress.com/home).
- **Questions douanières / express international :** `atendimento.aduaneiro@anjun.com.br`.
- **Médiation douanière (*Ouvidoria*) :** `ouvidoria.aduaneira@anjun.com.br`.
- **Médiation consommateur :** si un différend avec une entreprise inscrite reste sans solution, les consommateurs au Brésil titulaires d'un compte gov.br argent ou or peuvent utiliser [Consumidor.gov.br](https://www.consumidor.gov.br/pages/conteudo/publico/1), où les sociétés participantes s'engagent à répondre sous 10 jours.

> Méfie-toi des faux messages de paiement douanier envoyés par SMS ou WhatsApp prétendant que ton colis Anjun est bloqué. Vérifie tout avis douanier dans la marketplace de ta commande ou directement auprès de `atendimento.aduaneiro@anjun.com.br` avant de payer. Voir [les SMS frauduleux de livraison](guide:delivery-scam-texts).

## Suivre un colis Anjun Logistics avec Peek

[Peek](/) reconnaît automatiquement Anjun Logistics à partir de tout numéro `AJ` de 17 caractères (avec une date `AAMMJJ` valide) et des liens collés `anjuntrack.com` (`/trackQuery?t=` ou `/trajectory?orderNum=`) ou `anjunexpress.com/trackPackage?tracking=` contenant un code `AJ`. Si tu dois changer de transporteur manuellement, appuie sur « Changer » à l'ajout ou sur « Changer de transporteur » dans la fiche du colis et sélectionne Anjun Logistics (Peek suit plus de 4 500 transporteurs).

Peek interroge d'abord `anjuntrack.com` pour l'historique international et, si ce portail ne renvoie encore aucun scan, interroge aussi `anjunexpress.com` automatiquement afin que les colis domestiques brésiliens apparaissent sans démarche supplémentaire. Peek affiche l'étape (« Étiquette créée », « Pris en charge », « En transit », « À la douane », « En livraison », « Tentative de livraison », « Problème signalé », « Livré »), les descriptions en anglais et portugais, le motif d'échec de livraison éventuel (tel que `Cliente ausente`), la ville et l'État au Brésil (les adresses de rue sont retirées), le pays de destination et les heures locales (heure de Chine en Chine, heure du Brésil au Brésil, affichées dans le fuseau de ton appareil). Peek retire la barre de progression du site, l'adresse postale du destinataire, les coordonnées GPS, le nom du chauffeur, les téléphones/e-mails d'assistance dans les notes, les signatures et les photos ; le suivi d'Anjun ne fournit ni date estimée, ni poids, ni numéro de transfert local.

Lorsque tu gardes le colis ouvert dans Peek, ou le jour de livraison avec les notifications activées, Peek vérifie jusqu'à toutes les 10 minutes ; le jour où Anjun affiche `Objeto saiu para entrega ao destinatário`, toutes les 2 minutes à partir de 6 h heure locale tant que le suivi d'Anjun répond. Sinon, Peek vérifie toutes les heures. Après 12 heures uniquement en « Étiquette créée » ou « Pris en charge », ou deux jours sans nouvelles, la vérification passe à toutes les heures quelle que soit ta consultation, et toutes les 6 heures après sept jours en « Étiquette créée », sauf si le colis est ouvert ou attendu ce jour-là. Quand le suivi d'Anjun ne répond pas et que Peek suit le colis uniquement via des services de suivi, la fréquence est d'une heure, toutes les 3 heures après un jour calme et jusqu'à toutes les 15 minutes le jour où le colis est en livraison, tant que le colis est ouvert ou les alertes activées.

Lorsque le dernier scan a quatre jours et que le colis est encore à « Étiquette créée », « Pris en charge » ou « En transit », Peek le signale avec le jour de ce scan (par exemple « Aucune mise à jour depuis lun 12 oct »). La fiche affiche alors « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger Anjun Logistics. » Après trois semaines sans nouveau scan, elle indique « Rien de nouveau depuis plus de trois semaines. Si le colis n’est toujours pas arrivé, contacte le vendeur. Peek continue d’interroger Anjun Logistics. »

Tu peux choisir des alertes pour « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Peek suit uniquement les mises à jour et ne peut ni dédouaner un colis ni contacter Anjun Express pour toi.

## Questions sur le suivi Anjun Logistics

### Pourquoi mon suivi Anjun mélange-t-il l'anglais et le portugais ?

Les envois transfrontaliers démarrent dans les entrepôts d'Anjun en Chine et les hubs d'exportation de Hong Kong, qui publient des scans en anglais, puis basculent en portugais dès l'arrivée au Brésil pour le dédouanement et la livraison locale.

### Que signifie `A entrega falhou: Pacote não pertence à Base` ?

Cela indique que le colis a été acheminé par erreur vers la mauvaise base locale d'Anjun, et non qu'un livreur s'est présenté en ton absence. Anjun le réachemine vers la bonne base, raison pour laquelle Peek le maintient « En transit ».

### L'adresse `sac@anjunexpress.com.br` fonctionne-t-elle encore ?

Non. Anjun Express a désactivé `sac@anjunexpress.com.br` le 12 août 2026. Utilise `sac@anjun.com.br`, appelle le `(11) 5026-7008` (lun–ven, 08h00–17h30) ou écris à `atendimento.aduaneiro@anjun.com.br` pour les questions douanières.

### Anjun peut-il remettre mon colis à Correios ?

Oui. Certains colis internationaux sortent de douane avec la mention `Objeto recebido pelos Correios do Brasil`, ce qui signifie que Correios assure la livraison finale au Brésil tandis que le suivi d'Anjun enregistre le transfert.

:::sources
- [Anjun Express : Accueil](https://anjunexpress.com/home) – services au Brésil, chiffres du réseau, téléphone/e-mail SAC actifs, contacts douane/Ouvidoria et avis de désactivation de `sac@anjunexpress.com.br` (12/08/2026)
- [Anjun Express : À propos](https://anjunexpress.com/aboutUs) – fondation en 2011, entrée au Brésil en 2019, couverture nationale en 2025
- [Anjun Express : Suivi colis](https://anjunexpress.com/trackPackage) – portail de suivi brésilien
- [AnjunTrack : Suivi international](https://anjuntrack.com/trajectory) – portail transfrontalier Chine-Brésil
- [Anjun Express : Dernier kilomètre](https://anjunexpress.com/lastMile) – livraison dernier kilomètre et entrepôt sous douane GRU
- [Anjun Express : Points de collecte](https://anjunexpress.com/collectionPoint) – réseau de points de collecte partenaires
- [Receita Federal : Programa Remessa Conforme](https://www.gov.br/receitafederal/pt-br/assuntos/aduana-e-comercio-exterior/manuais/remessas-postal-e-expressa/programa-remessa-conforme-o-que-e-como-funciona) – règles des droits d'importation et de l'ICMS au Brésil
- [Consumidor.gov.br](https://www.consumidor.gov.br/pages/conteudo/publico/1) – médiation publique (réponse sous 10 jours, compte gov.br argent/or)
- [Universal Parcel Scraper : anjun](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/anjun) – détection `AJ`, interrogation des deux portails, correspondance des statuts, champs filtrés et cadence
:::
