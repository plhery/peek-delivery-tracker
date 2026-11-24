---
title: Suivi DTDC : formats AWB, statuts RTO, délais et aide
description: Suis un colis DTDC en Inde ou à l’international : codes AWB N, 7D et 7X, scans de hub, retours RTO, délais WorldWyde et service client.
slug: suivi-dtdc
published: 2026-11-24
updated: 2026-11-24
---

DTDC (*DTDC Express Limited*), fondé le 26 juillet 1990 et basé à Bengaluru, est l’un des plus grands réseaux de logistique express d’Inde. Avec plus de 16 500 partenaires couvrant 96 % de la population indienne, un service Express Parcel desservant plus de 15 300 codes postaux, une présence locale dans 7 pays et une portée internationale vers plus de 220 destinations, DTDC assure les colis nationaux, *Express Premium* lendemain, *AirMax*, *Raftaar*, la logistique 3PL, le fret LTL et l’expédition internationale *DTDC WorldWyde*. Tu peux consulter un envoi sur [la page de suivi DTDC](https://www.dtdc.com/track-your-shipment/) ou [MyDTDC](https://web.mydtdc.in/), ou coller ton numéro AWB dans le champ ci-dessus.

## Numéros de suivi DTDC (formats AWB)

DTDC t’envoie ton numéro Air Waybill (AWB) par e-mail et SMS à la confirmation de la commande. Les numéros AWB nationaux et internationaux de DTDC suivent ces formats alphanumériques courants :

| Format (inventé) | Ce que c’est |
| --- | --- |
| `N48291736` | Numéro d’envoi DTDC standard à 9 caractères (`N` + 8 chiffres). |
| `7D482917364` | Numéro d’envoi DTDC à 11 caractères (`7D` + 9 chiffres). |
| `7X482917364` | Numéro d’envoi DTDC à 11 caractères (`7X` + 9 chiffres). |

DTDC émet aussi des codes de 9 et 11 caractères commençant par d’autres lettres uniques (comme `B`, `D`, `H`, `P`, `T`, `U`, `V` ou `X` suivis de 8 ou 10 chiffres), qui recoupent d’autres transporteurs régionaux et peuvent nécessiter de choisir DTDC manuellement si la détection propose plusieurs candidats. Sur [dtdc.com/track-your-shipment/](https://www.dtdc.com/track-your-shipment/), tu peux basculer entre **Shipment Number** (AWB) et **Reference Number**. Pour d’autres formats en Inde ou ailleurs, consulte [les formats de numéros de suivi](guide:tracking-number-formats).

## Signification des statuts de suivi DTDC

DTDC publie des étapes et mouvements de hub (comme `Arrived at DELHI`, `Dispatched from MATHURA` ou `Received at ... Processing Centre`) à l’heure normale de l’Inde (`Asia/Kolkata`, UTC+5:30).

| Ce qu’affiche DTDC | Ce que ça signifie pour toi | Ce qu’affiche Peek |
| --- | --- | --- |
| `Booked`, `Booked at Origin Branch`, `Pickup Awaited`, `Pickup Scheduled`, `Pickup requested`, `Not picked` | Bordereau enregistré électroniquement ou au guichet ; en attente d’enlèvement physique ou d’entrée en hub. | « Étiquette créée » |
| `Picked Up`, `Pickup Completed` | DTDC a collecté le colis chez l’expéditeur. | « Pris en charge » |
| `In Transit`, `On The Way`, `At Processing Centre`, `At Destination`, `Reattempt Initiated`, `Arrived at ...`, `Dispatched from ...` | En transit entre les centres de tri DTDC, arrivé au centre de destination ou programmé pour une nouvelle tentative. | « En transit » |
| `Out For Delivery` | Chargé chez le livreur local pour livraison à domicile aujourd’hui. | « En livraison » |
| `Undelivered` | Tentative de livraison non aboutie (par exemple destinataire absent ou problème d’adresse). | « Tentative de livraison » |
| `RTO Booked`, `Return Approval awaited`, `Return Initiated` / `Return - Initiated` | Retour à l’origine (RTO) déclenché pour renvoyer le colis à l’expéditeur. | « Problème signalé » |
| `Delivered` (trajet aller) | Livré au destinataire. | « Livré » |
| `Delivered` (sur un trajet `RTO`) | Retourné et remis à l’expéditeur d’origine. | « Retourné à l’expéditeur » |

**Attention aux retours `RTO`.** Lorsqu’un envoi ne peut pas être livré, DTDC ouvre un trajet Return To Origin (`RTO`) qui comporte son propre scan `Delivered` une fois le colis revenu chez l’expéditeur. Vérifie si le dernier trajet est aller ou `RTO` avant de supposer que le colis t’a été livré.

## Services nationaux et internationaux DTDC

Selon [DTDC Express Parcel](https://www.dtdc.com/express-parcel/), [DTDC International Shipping](https://www.dtdc.com/international-shipping/), [About Us](https://www.dtdc.com/about-us/) et la [FAQ DTDC](https://www.dtdc.com/faq/) :

- **Services nationaux en Inde :** *Express Parcel* (livraison quotidienne à date définie sur plus de 15 300 codes postaux avec paiement à la livraison, authentification et retours), *Express Premium* (urgent lendemain), *AirMax*, *Raftaar*, *Personal Courier* et offres B2B *3PL* / *LTL*.
- **Dimanches et jours fériés :** la [FAQ DTDC](https://www.dtdc.com/faq/) précise que certaines agences locales DTDC fonctionnent le dimanche et les jours fériés selon leurs horaires.
- **Délais internationaux (*DTDC WorldWyde*) :** desservant plus de 220 destinations avec dédouanement intégré et KYC/CSB-V numérique — *WorldWyde Express Premium* (**4–5 jours**), *WorldWyde Express Standard* (**7–8 jours**), *WorldWyde Imports Standard* (**10–12 jours**) et *WorldWyde B2C Standard* (**15–20 jours**). Le délai réel dépend aussi de la douane de destination ; consulte [colis retenu en douane](guide:customs).

Les pages de service DTDC ne publient ni nombre de tentatives de livraison ni délai de garde. Une tentative échouée affiche `Undelivered` et une nouvelle tentative `Reattempt Initiated`.

## Colis DTDC retardé, non livré ou manquant

Pour les achats e-commerce, contacte d’abord le vendeur en ligne afin qu’il autorise une correction d’adresse, un report ou un remboursement — surtout si le suivi indique `Return Approval awaited` ou `RTO Booked`.

- **Modifier l’adresse, reprogrammer ou retirer soi-même :** la page [Track Your Shipment](https://www.dtdc.com/track-your-shipment/) permet d’ouvrir un litige, de chatter avec le support ou de demander un rappel, et le formulaire [Customer Care](https://www.dtdc.com/customer-care/) traite les demandes telles qu’une nouvelle adresse ou un nouveau téléphone, une autre date de livraison ou un retrait en agence.
- **Indiqué `Delivered` mais non reçu :** vérifie d’abord s’il ne s’agit pas d’un retour `RTO` à l’expéditeur ; s’il s’agit d’une livraison aller, demande à ton foyer, à tes voisins et au gardien, puis ouvre aussitôt un litige via DTDC Customer Care et ton vendeur.
- Consulte [pourquoi le suivi ne se met plus à jour](guide:tracking-not-updating), [colis livré mais non reçu](guide:delivered-not-received), [colis endommagé](guide:damaged-parcel), [colis perdu](guide:lost-parcel) et [retourné à l’expéditeur](guide:returned-to-sender).

## Service client et contacts officiels DTDC

Contacts publiés sur [DTDC Customer Care](https://www.dtdc.com/customer-care/) et [dtdc.com](https://www.dtdc.com/in/) :

- **Téléphone service client :** `+91-9606 911 811`.
- **E-mail support :** `customersupport@dtdc.com`.
- **Support en ligne :** formulaire de demande/litige sur [dtdc.com/customer-care/](https://www.dtdc.com/customer-care/), assistant virtuel **DIVA** sur le site et application mobile **MyDTDC**.

> DTDC avertit clairement sur [dtdc.com](https://www.dtdc.com/in/) que **`www.dtdc.com` est son seul site officiel** et décline toute responsabilité pour les offres, numéros ou paiements effectués sur des sites imitateurs. Ne paie jamais de frais et ne partage jamais de code OTP via un lien inconnu se faisant passer pour DTDC. Consulte [les SMS d’arnaque à la livraison](guide:delivery-scam-texts).

## Suivre un colis DTDC avec Peek

[Peek](/) reconnaît automatiquement `N` + 8 chiffres (`N48291736`, inventé) et `7D` / `7X` + 9 chiffres (`7D482917364`, `7X482917364`) comme DTDC avec un haut niveau de confiance, ainsi que les liens collés `dtdc.com` portant `trackingId=` ou `awb=`. Pour les codes alphanumériques de 9 ou 11 caractères commençant par d’autres lettres, appuie sur « Changer » à l’ajout ou « Changer de transporteur » sur la fiche du colis et choisis DTDC.

Peek interroge DTDC en deux temps — d’abord via le flux anonyme MyDTDC puis en repli sur la page de suivi `dtdc.com` si nécessaire. Peek affiche l’étape (« Étiquette créée », « Pris en charge », « En transit », « En livraison », « Tentative de livraison », « Problème signalé », « Retourné à l’expéditeur », « Livré »), le texte des scans DTDC, le hub ou l’agence et les horodatages en heure normale de l’Inde (`Asia/Kolkata`) convertis à l’heure de ton appareil. Point clé : lorsque DTDC marque un trajet `RTO` comme `Delivered`, Peek l’affiche en « Retourné à l’expéditeur » plutôt qu’en « Livré ». Peek retire les adresses postales, téléphones/contacts, métadonnées d’enregistrement et poids non étiquetés ; le suivi DTDC ne fournit ni date estimée ni point de retrait.

Tant que le colis est ouvert dans Peek, ou le jour de la livraison avec les notifications activées, Peek vérifie jusqu’à toutes les 10 minutes ; le jour où DTDC affiche `Out For Delivery`, toutes les 2 minutes à partir de 6 h heure indienne, tant que le suivi DTDC répond. Sinon, Peek vérifie toutes les heures. Après 12 heures avec seulement « Étiquette créée » ou « Pris en charge », ou deux jours sans nouvelles, il vérifie toutes les heures quelle que soit ta façon de le suivre, et toutes les 6 heures après sept jours sur « Étiquette créée ». Lorsque le suivi DTDC ne répond pas et que Peek suit le colis uniquement via des services de suivi, il vérifie toutes les heures, toutes les 3 heures après une journée calme, et jusqu’à toutes les 15 minutes le jour de la livraison tant que le colis est ouvert ou que les notifications sont activées.

Lorsque le dernier scan date de quatre jours et que le colis reste à « Étiquette créée », « Pris en charge » ou « En transit », Peek le signale avec la date de ce scan, par exemple « Aucune mise à jour depuis lun. 12 oct. ». La fiche du colis affiche alors « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger DTDC. », avec un lien « Demande de l’aide à DTDC » vers la page Customer Care de DTDC. Après trois semaines sans nouveau scan, elle indique « Rien de nouveau depuis plus de trois semaines. Si le colis n’est toujours pas arrivé, contacte le vendeur. Peek continue d’interroger DTDC. »

Tu peux choisir les réglages d’alerte « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Peek assure uniquement le suivi et ne peut ni reprogrammer une livraison DTDC, ni changer une adresse, ni ouvrir un litige à ta place.

## Questions sur le suivi DTDC

### À quoi ressemble un numéro de suivi (AWB) DTDC ?

Les numéros DTDC courants comptent 9 caractères commençant par `N` (`N48291736`, inventé) ou 11 caractères commençant par `7D` ou `7X` (`7D482917364`, `7X482917364`), bien que DTDC utilise aussi d’autres préfixes d’une lettre sur 9 ou 11 caractères.

### Que signifient `RTO Booked` ou `Return Initiated` dans le suivi DTDC ?

`RTO` signifie Return To Origin (retour à l’origine). Le colis n’a pas pu être livré (ou une validation de retour a été demandée) et repart chez l’expéditeur. Contacte immédiatement le vendeur si tu souhaites toujours recevoir ta commande.

### Combien de temps prend une expédition internationale DTDC ?

Sur [dtdc.com/international-shipping/](https://www.dtdc.com/international-shipping/), DTDC indique 4–5 jours pour *WorldWyde Express Premium*, 7–8 jours pour *Express Standard*, 10–12 jours pour *Imports Standard* et 15–20 jours pour *B2C Standard*, sous réserve de dédouanement.

### Comment contacter le service client DTDC ?

Appelle le `+91-9606 911 811`, écris à `customersupport@dtdc.com` ou utilise le formulaire de demande et l’assistant DIVA sur [dtdc.com/customer-care/](https://www.dtdc.com/customer-care/).

:::sources
- [DTDC : Home](https://www.dtdc.com/in/) – avertissement site officiel (`www.dtdc.com` uniquement), support DIVA et aperçu des services
- [DTDC : About Us](https://www.dtdc.com/about-us/) – fondé le 26 juillet 1990, siège à Bengaluru, 16 500+ partenaires, 96 % de la population indienne, 7 pays, 220+ destinations et 5,6 M+ utilisateurs MyDTDC
- [DTDC : Track Your Shipment](https://www.dtdc.com/track-your-shipment/) – recherche AWB / référence, notification AWB par SMS/e-mail, litige, chat et rappel
- [DTDC : Express Parcel](https://www.dtdc.com/express-parcel/) – 15 300+ codes postaux, contre-remboursement, authentification de livraison et logistique retour
- [DTDC : International Shipping (WorldWyde)](https://www.dtdc.com/international-shipping/) – 220+ destinations, délais WorldWyde (4–5, 7–8, 10–12, 15–20 jours) et dédouanement CSB-V/KYC
- [DTDC : FAQ](https://www.dtdc.com/faq/) – ouverture certains dimanches/jours fériés et questions de suivi
- [DTDC : Customer Care](https://www.dtdc.com/customer-care/) – `+91-9606 911 811`, `customersupport@dtdc.com`, formulaire de demande adresse/date/retrait (URL d’aide liée par Peek)
- [Portail MyDTDC](https://web.mydtdc.in/) – portail web de suivi MyDTDC
- [Universal Parcel Scraper : dtdc](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/dtdc) – détection haute confiance `N`/`7D`/`7X`, règles de liens `dtdc.com`, interrogation en deux étapes, gestion des retours RTO, fuseau `Asia/Kolkata`, champs ignorés et URL d’aide
:::
