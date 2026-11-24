---
title: Suivi Jadlog : code à 14 chiffres, Pickup, Predict et aide
description: Suis un colis Jadlog au Brésil : numéro à 14 chiffres, statuts portugais et Geopost, points Jadlog Pickup, SMS Predict 1 h et contacts.
slug: suivi-jadlog
published: 2026-11-24
updated: 2026-11-24
---

Jadlog est l’un des plus grands opérateurs brésiliens de colis express et de logistique e-commerce, et la filiale brésilienne du groupe européen Geopost (aux côtés de DPD, Chronopost, SEUR, BRT et Speedy). À travers le Brésil, le réseau compte plus de 500 franchises couvrant environ 5 000 municipalités, plus de 7 000 véhicules et plus de 4 000 commerces partenaires Jadlog Pickup. Tu peux suivre un envoi dans le champ `RASTREIE SUA ENCOMENDA` sur [la page d’accueil de Jadlog](https://www.jadlog.com.br/jadlog/home), ou coller le code à 14 chiffres dans le champ ci-dessus.

## Numéro de suivi Jadlog : format à 14 chiffres et où le trouver

Cherche ton code d’expédition (*código de rastreio* ou *remessa*) dans l’e-mail d’expédition de la boutique en ligne, sur la page de commande de la place de marché ou sur l’étiquette. Les numéros de suivi Jadlog comportent 14 chiffres consécutifs :

| Format (inventé) | Ce que c’est |
| --- | --- |
| `48291736405821` | Numéro de colis Jadlog à 14 chiffres. Partage ce format avec les transporteurs européens du groupe Geopost/DPD tels que [DPD Allemagne](carrier:dpd-de), [Chronopost](carrier:chronopost), [SEUR](carrier:seur) et [BRT](carrier:brt). |

Peek suit le colis uniquement avec le numéro à 14 chiffres et ne demande jamais ton CPF. Si ton code comporte 15 chiffres commençant par `8880`, il appartient à [J&T Express Brazil](carrier:j-and-t-br) ; un code `BR` de 15 caractères appartient à [SPX Express Brazil](carrier:spx-br) ; un code `AJ` de 17 caractères appartient à [Anjun Logistics](carrier:anjun) ; et un code de 13 caractères se terminant par `BR` appartient à [Correios](carrier:correios-br). Consulte [les formats de numéros de suivi](guide:tracking-number-formats) pour d’autres formats.

## Signification des statuts de suivi Jadlog

Comme Jadlog fait partie de Geopost, les lignes de suivi s’affichent en portugais sur le site de Jadlog ou en anglais lorsqu’elles passent par l’API de suivi Geopost.

| Ce qu’affiche Jadlog / Geopost | Ce que ça signifie pour toi | Ce qu’affiche Peek |
| --- | --- | --- |
| `Order created`, `COLETA SOLICITADA` | Le vendeur a créé l’envoi électroniquement et demandé l’enlèvement ; Jadlog n’a pas encore scanné le colis physique. | « Étiquette créée » |
| `EMISSAO` | Jadlog a émis le document de transport et pris le colis en charge dans son réseau. | « Pris en charge » |
| `Em transferência` | Le colis circule entre les franchises Jadlog, centres de tri ou unités de ligne à travers le Brésil. | « En transit » |
| `Em rota de entrega` | Un livreur local a chargé le colis et est en tournée vers ton adresse. | « En livraison » |
| `ENTREGUE` | Jadlog a enregistré le colis comme livré. | « Livré » |

Lorsqu’une boutique imprime des étiquettes par lots avant le passage de la camionnette Jadlog, `Order created` ou `COLETA SOLICITADA` peut rester le seul événement pendant deux jours ouvrables. Une fois `Em transferência` affiché, les longues distances routières entre États brésiliens peuvent aussi laisser des silences de plusieurs jours avant le scan de la franchise de destination.

## Services Jadlog : Predict, points Pickup et export international

Jadlog détaille ses principaux services sur [jadlog.com.br/jadlog/servicos](https://www.jadlog.com.br/jadlog/servicos), [Quem Somos](https://www.jadlog.com.br/jadlog/quemsomos) et ses pages produits :

- **Jadlog.com et Jadlog Package :** livraison e-commerce (Jadlog.com accepte les colis jusqu’à 30 kg ; Jadlog Package couvre les envois routiers B2C et B2B).
- **Jadlog Rodo, RodoEconômico, Cargo et DOC :** fret routier plus lourd jusqu’à 50 kg (Rodo), routier économique, fret aérien B2B et transport de documents.
- **[Jadlog Predict](https://www.jadlog.com.br/jadlog/jadlogPredict) :** disponible dans les zones métropolitaines et à fort volume, envoyant un SMS automatique avec un créneau de livraison d’une heure pour savoir quand attendre le livreur.
- **[Jadlog Pickup](https://www.jadlog.com.br/jadlog/pickup) :** plus de 4 000 commerces partenaires au Brésil (touchant plus de 90 % de la population) pour le retrait, le dépôt et les retours e-commerce (*Reversa*), avec alertes par SMS et e-mail.
- **[Jadlog Internacional](https://www.jadlog.com.br/jadlog/internacional) :** expéditions export depuis le Brésil vers 154 pays (300 destinations) via le réseau international Geopost.

**Ce que Jadlog ne publie pas publiquement.** Le site de Jadlog n’indique pas de nombre universel de tentatives de livraison, ni de délai garanti, ni de durée de garde fixe en point Pickup valable pour toutes les boutiques. Vérifie le SMS ou l’e-mail reçu pour ton colis Pickup — ou la page de commande du vendeur — pour connaître les délais exacts de retrait et de nouvelle tentative.

## Colis Jadlog bloqué, retardé ou non reçu

Pour un achat en ligne, le vendeur est le client contractuel de Jadlog et gère les remboursements, corrections d’adresse et réexpéditions.

**Bloqué sur `Order created` ou `COLETA SOLICITADA`.** Le vendeur a généré les données d’envoi, mais Jadlog n’a pas encore collecté ni enregistré le colis dans son réseau. Demande à la boutique si le colis a quitté son entrepôt.

**Silencieux en transit (`Em transferência`).** Les trajets routiers entre États brésiliens, les week-ends et l’engorgement de centres régionaux mettent régulièrement le suivi en pause entre deux scans de franchise. Consulte [pourquoi le suivi ne se met plus à jour](guide:tracking-not-updating).

**Indiqué `ENTREGUE` mais introuvable.** Vérifie d’abord auprès de tes proches, voisins et de la *portaria* (réception ou gardien) de ton immeuble, et vérifie si tu as choisi un point Jadlog Pickup lors de la commande. Si personne ne l’a reçu, contacte aussitôt la boutique pour qu’elle ouvre une enquête auprès de Jadlog. Consulte [colis livré mais non reçu](guide:delivered-not-received).

**Endommagé, perdu ou retourné.** Conserve l’emballage extérieur et prends des photos nettes des dégâts et de l’étiquette avant de contacter le vendeur ; consulte [colis endommagé](guide:damaged-parcel), [colis perdu](guide:lost-parcel) et [retourné à l’expéditeur](guide:returned-to-sender).

## Contacter Jadlog et recours des consommateurs

La page [Atendimento de Jadlog](https://www.jadlog.com.br/jadlog/atendimento) n’affiche ni numéro de téléphone public pour destinataires ni adresse e-mail générale de service client. Tes options concrètes :

- **Contacter d’abord la boutique en ligne :** elle détient le contrat de transport (`CT-e` / facture) nécessaire pour ouvrir une réclamation, corriger une adresse ou autoriser un remboursement.
- **Annuaire des franchises locales :** cherche l’agence qui dessert ta zone sur [Jadlog Unidades e Franquias](https://www.jadlog.com.br/jadlog/unidades) si tu as besoin d’informations sur un dépôt local.
- **Demandes de données personnelles (LGPD) :** Jadlog met à disposition un portail dédié sur [jadlog.com.br/jadlog/lgpd](https://www.jadlog.com.br/jadlog/lgpd).
- **Médiation consommateur :** si un litige avec une boutique ou un transporteur participant ne se règle pas directement, les consommateurs au Brésil munis d’un compte gov.br niveau argent ou or peuvent déposer une réclamation gratuite sur [Consumidor.gov.br](https://www.consumidor.gov.br/pages/conteudo/publico/1), où les entreprises participantes s’engagent à répondre sous 10 jours.

> Méfie-toi des liens par SMS, WhatsApp ou e-mail réclamant une petite « taxe de déblocage » ou « frais de relivraison » pour un colis national Jadlog. Confirme d’abord tout frais imprévu directement auprès de la boutique où tu as commandé. Consulte [les SMS d’arnaque à la livraison](guide:delivery-scam-texts).

## Suivre un colis Jadlog avec Peek

Comme les numéros à 14 chiffres sont partagés par les transporteurs Geopost/DPD dans le monde, [Peek](/) utilise ta localisation pour décider qui interroger en premier. Au Brésil, Peek interroge Jadlog en priorité parmi les candidats à 14 chiffres (avant les filiales européennes DPD, Chronopost, SEUR et BRT) et affiche « Jadlog a ce colis. » dès que Jadlog le confirme. Hors du Brésil, Peek interroge Jadlog en dernier recours — en interrogeant d’abord les transporteurs européens Geopost puis Jadlog si aucun n’a le numéro. Coller un lien `jadlog.com.br/jadlog/captcha?cte=` désigne directement Jadlog partout ; sinon appuie sur « Changer » à l’ajout ou « Changer de transporteur » sur la fiche du colis puis choisis Jadlog (rechercher « DPD Brazil » le trouve aussi).

Peek interroge directement l’API de suivi publique de Geopost pour Jadlog sans demander de code postal ni de CPF. Il affiche l’étape actuelle (« Étiquette créée », « Pris en charge », « En transit », « En livraison », « Livré »), l’historique résumé des scans (en anglais depuis l’API Geopost ou en portugais lorsqu’il vient du suivi local Jadlog) et l’horodatage de livraison. La date estimée, le poids, l’adresse du point Jadlog Pickup et le nom ou la signature du destinataire ne sont pas affichés dans Peek.

Tant que le colis est ouvert dans Peek, ou le jour de la livraison avec les notifications activées, Peek vérifie jusqu’à toutes les 10 minutes ; le jour où le colis passe en livraison, toutes les 2 minutes à partir de 6 h heure locale, tant que le suivi Jadlog répond. Sinon, Peek vérifie toutes les heures. Après 12 heures avec seulement « Étiquette créée » ou « Pris en charge », ou deux jours sans nouvelles, il vérifie toutes les heures quelle que soit ta façon de le suivre, et toutes les 6 heures après sept jours sur « Étiquette créée ». Lorsque le suivi Jadlog ne répond pas et que Peek suit le colis uniquement via des services de suivi, il vérifie toutes les heures, toutes les 3 heures après une journée calme, et jusqu’à toutes les 15 minutes le jour où le colis est en livraison, tant que le colis est ouvert ou que les notifications sont activées.

Lorsque le dernier scan date de quatre jours et que le colis reste à « Étiquette créée », « Pris en charge » ou « En transit », Peek le signale avec la date de ce scan, par exemple « Aucune mise à jour depuis lun 12 oct ». La fiche du colis affiche alors « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger Jadlog. » Après trois semaines sans nouveau scan, elle indique « Rien de nouveau depuis plus de trois semaines. Si le colis n’est toujours pas arrivé, contacte le vendeur. Peek continue d’interroger Jadlog. »

Tu peux choisir les réglages d’alerte « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Peek assure uniquement le suivi et ne peut ni planifier une relivraison ni contacter une franchise Jadlog à ta place.

## Questions sur le suivi Jadlog

### À quoi ressemble un numéro de suivi Jadlog ?

Un numéro de colis Jadlog compte 14 chiffres (par exemple `48291736405821`, chiffres inventés). Tu peux le suivre avec ce code à 14 chiffres dans Peek ou sur [la page d’accueil de Jadlog](https://www.jadlog.com.br/jadlog/home).

### Qu’est-ce que Jadlog Predict ?

[Jadlog Predict](https://www.jadlog.com.br/jadlog/jadlogPredict) est un service d’alerte SMS automatique proposé dans les zones métropolitaines et à fort volume qui indique aux destinataires le créneau de livraison prévu d’une heure.

### Comment fonctionne Jadlog Pickup ?

[Jadlog Pickup](https://www.jadlog.com.br/jadlog/pickup) repose sur plus de 4 000 commerces partenaires au Brésil où les destinataires peuvent retirer leurs colis ou déposer des retours. Jadlog envoie des notifications par SMS et e-mail pour suivre l’envoi.

### Pourquoi Jadlog est-il lié à DPD et Geopost ?

Jadlog appartient au groupe européen Geopost aux côtés de DPD, Chronopost, SEUR, BRT et Speedy. C’est pourquoi Jadlog utilise des numéros de colis Geopost à 14 chiffres et s’appuie sur le réseau international Geopost dans 154 pays.

:::sources
- [Jadlog : Home](https://www.jadlog.com.br/jadlog/home) – suivi `RASTREIE SUA ENCOMENDA` et présentation du réseau brésilien
- [Jadlog : Quem Somos](https://www.jadlog.com.br/jadlog/quemsomos) – appartenance au groupe Geopost, 500+ franchises, 4 000+ points Pickup, 7 000+ véhicules, 7 000 collaborateurs directs et indirects, 154 pays
- [Jadlog : Produtos e Serviços](https://www.jadlog.com.br/jadlog/servicos) – Jadlog.com (jusqu’à 30 kg), Package, Rodo (jusqu’à 50 kg), RodoEconômico, Cargo, DOC
- [Jadlog : Pickup](https://www.jadlog.com.br/jadlog/pickup) – 4 000+ commerces partenaires, couverture > 90 % de la population, alertes SMS/e-mail et retours Reversa
- [Jadlog : Predict](https://www.jadlog.com.br/jadlog/jadlogPredict) – alertes SMS automatiques avec créneau d’une heure
- [Jadlog : Internacional](https://www.jadlog.com.br/jadlog/internacional) – export vers 154 pays / 300 destinations via Geopost
- [Jadlog : Unidades e Franquias](https://www.jadlog.com.br/jadlog/unidades) – annuaire des franchises et agences au Brésil
- [Jadlog : Atendimento](https://www.jadlog.com.br/jadlog/atendimento) – périmètre de la page d’assistance publique
- [Jadlog : LGPD](https://www.jadlog.com.br/jadlog/lgpd) – portail de demandes sur les données personnelles
- [Consumidor.gov.br](https://www.consumidor.gov.br/pages/conteudo/publico/1) – plateforme publique de médiation consommateur (réponse sous 10 jours, compte gov.br argent ou or)
- [Universal Parcel Scraper : jadlog](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/jadlog) – détection 14 chiffres et priorité Brésil (alias `DPD Brazil`), règle de lien `captcha?cte=`, API de suivi publique de Geopost, correspondance des statuts et rythme de vérification
:::
