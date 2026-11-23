---
title: Suivi DHL eCommerce : numéros GM, statuts et contacts
description: Suis un colis DHL eCommerce (ex-DHL Global Mail) : format GM, sens des statuts, livraison USPS ou poste locale et contacts.
slug: suivi-dhl-ecommerce
published: 2026-11-23
updated: 2026-11-23
---

DHL eCommerce, anciennement DHL Global Mail, est la division colis de DHL dédiée aux boutiques en ligne. Elle trie ton colis dans ses propres centres de distribution, puis le remet à un transporteur dans ton pays : [USPS](carrier:usps) aux États-Unis, ailleurs la poste locale ou un autre transporteur. Suis-le avec le numéro reçu dans l’e-mail d’expédition de la boutique sur le site Webtrack de DHL eCommerce, ou colle-le dans le champ ci-dessus.

## Format du numéro de suivi DHL eCommerce

Les numéros DHL eCommerce comptent au moins dix caractères et commencent généralement par `GM`. Webtrack accepte jusqu’à 10 numéros à la fois, séparés par des virgules. Aucun numéro ? Demande à la boutique.

| Format (fictif) | À quoi il correspond |
| --- | --- |
| `GM4829103756182947` | `GM` suivi de 16 à 18 chiffres : le format habituel de DHL eCommerce. |
| `GM4829103756` | `GM` suivi de 8 à 15 chiffres : un numéro `GM` plus court. |
| `9261482910375618294751` | 22 chiffres commençant par `9261` ou `9361` : le numéro USPS attribué à un colis américain une fois remis à USPS. |
| `7394028156342819` | 16 ou 17 chiffres : un autre format utilisé par DHL eCommerce. |
| `0031482910375618294751829364728` | `0031` suivi de 27 chiffres : un autre format DHL eCommerce. |

La ligne « Delivery by » sur Webtrack nomme le transporteur du dernier kilomètre, dont le propre numéro peut apparaître plus tard.

Formats voisins : 16 chiffres peuvent aussi appartenir à TNT, Evri, Canada Post ou Correos Express, et un numéro en `9261` ou `9361` à USPS. Un numéro à 14 chiffres peut appartenir à [DHL eCommerce UK](carrier:dhl-ecommerce-uk) (un réseau distinct avec son propre suivi), un numéro `JVGL` à [DHL eCommerce Netherlands](carrier:dhl-ecommerce-nl), tandis que [DHL Express](carrier:dhl-express) et [DHL Paket](carrier:dhl) utilisent encore d’autres formats. Plus de détails dans [les formats de numéros de suivi](guide:tracking-number-formats).

## Statuts de suivi DHL eCommerce expliqués

Webtrack affiche un statut résumé sur une ligne (`En Route`, `Delivered`, `Exception`…) au-dessus d’un historique (« Tracking History ») en majuscules. Voici les scans les plus fréquents, dans l’ordre habituel :

| DHL eCommerce indique | Ce que cela signifie pour toi |
| --- | --- |
| `LABEL CREATED` | La boutique a créé l’étiquette. DHL eCommerce n’a pas encore le colis. |
| `EN ROUTE TO DHL ECOMMERCE DISTRIBUTION CENTER OR AWAITING PROCESSING` | En route vers DHL ou encore en attente chez le vendeur. |
| `PACKAGE RECEIVED AT DHL ECOMMERCE DISTRIBUTION CENTER` | DHL eCommerce a pris le colis en charge. |
| `PROCESSED` | Trié dans le centre de distribution. |
| `DEPARTURE ORIGIN DHL ECOMMERCE FACILITY` | A quitté le premier centre DHL eCommerce. |
| `ARRIVAL DESTINATION DHL ECOMMERCE FACILITY` | Arrivé au centre DHL eCommerce proche de chez toi. |
| `CUSTOMS CLEARANCE` | En cours de dédouanement. |
| `TENDERED TO DELIVERY SERVICE PROVIDER, ALLOW 1-3 DAYS FOR UPDATES FOR PACKAGES WITHIN THE US` | Remis au transporteur qui assure la livraison finale. Pour les colis aux États-Unis, les mises à jour peuvent s’interrompre jusqu’à 3 jours. |
| `OUT FOR DELIVERY` | Avec le livreur ou le facteur aujourd’hui. |
| `DELIVERY ATTEMPT FAILED` | Personne n’a pu le réceptionner. Cherche un avis du transporteur local. |
| `DELIVERED` | Livré. |
| `RETURNED TO SENDER` | Repart chez la boutique. |

Les colis américains affichent aussi les scans USPS après la remise, comme `ARRIVAL AT POST OFFICE`.

Un libellé prête souvent à confusion : `Picked up at parcelshop` indique que l’expéditeur l’a déposé en point relais, rien ne t’y attend.

## Livraison DHL eCommerce : USPS, poste locale et absences

Aux États-Unis, la FAQ de Webtrack indique que c’est USPS qui livre le colis au passage habituel du courrier. Pour les envois internationaux, un prestataire local prend le relais (« local delivery service provider »), qui peut être la poste nationale ou un autre transporteur.

Les avis de passage, points de retrait, délais de garde et nouvelles tentatives suivent donc les règles de ce transporteur final. DHL recommande de vérifier ta boîte aux lettres et de donner toute consigne de livraison directement à USPS ou au transporteur local.

Changement d’adresse : selon la FAQ de Webtrack, une fois le colis parti de chez le vendeur, DHL eCommerce ne peut plus modifier l’adresse — adresse-toi à la boutique.

## Colis DHL eCommerce en retard, perdu ou retourné

**Aucun scan encore.** La FAQ Webtrack indique que les premiers événements apparaissent généralement sous 24 à 48 heures ; s’il n’y a toujours rien après 48 heures, contacte la boutique. Quand Webtrack ne trouve rien, il conseille de vérifier le numéro puis de joindre le vendeur.

**Aucune mise à jour depuis des jours.** La FAQ évoque la météo, les jours fériés, la douane, les grèves et d’autres événements hors du contrôle de DHL. Pour les États-Unis, le scan de remise prévoit déjà 1 à 3 jours sans mise à jour. Voir [pourquoi un suivi ne se met plus à jour](guide:tracking-not-updating).

**Non arrivé.** Si le colis n’arrive pas avant la fin du délai prévu, la FAQ Webtrack invite à contacter la boutique. Dans l’UE, Your Europe rappelle que le vendeur reste responsable des dommages jusqu’à la réception et doit livrer sous 30 jours sauf accord différent ; s’il ne le fait pas, relance-le avec un délai supplémentaire raisonnable, puis tu peux annuler et obtenir un remboursement.

**Douane.** `CUSTOMS CLEARANCE` signifie que le colis est en douane. Voir [colis bloqué en douane](guide:customs) pour savoir quels frais peuvent s’appliquer.

**Retourné.** `RETURNED TO SENDER` signifie que le colis retourne au vendeur : demande-lui un remboursement ou un nouvel envoi.

## Service client et téléphone DHL eCommerce

Contacte d’abord la boutique, comme le conseille la FAQ de Webtrack. La page « Contact Customer Service » de Webtrack liste une fiche par pays :

- **États-Unis et Canada** (colis expédiés depuis les États-Unis) : +1 317-554-5191, du lundi au vendredi de 8 h à 20 h EST (la fiche Canada indique 8 h à 21 h ET).
- **Allemagne :** renvoie vers le [service client DHL Allemagne](https://www.dhl.de/de/privatkunden/hilfe-kundenservice.html), du lundi au dimanche de 7 h à 20 h (heure allemande).
- **Irlande :** 818 370050 (depuis l’Irlande), du lundi au vendredi de 8 h à 18 h (heure irlandaise).
- **Australie :** +61 37 0356818, du lundi au vendredi de 8 h à 18 h.
- **Autriche et Suisse :** +49 228 92 95 95 95, du lundi au vendredi de 9 h à 18 h (8 h pour l’Autriche, heure d’Europe centrale).
- **Royaume-Uni :** renvoie vers la ligne DHL eCommerce UK, 024 7693 7770. Voir la page [DHL eCommerce UK](carrier:dhl-ecommerce-uk).

Le formulaire « Contact Support » sur Webtrack indique une réponse sous 24 à 72 heures. Le coût des appels n’est pas précisé.

## Suivre un colis DHL eCommerce avec Peek

[Peek](/) identifie immédiatement DHL eCommerce (« Transporteur détecté ») pour `GM` suivi de 16 à 18 chiffres. Pour un numéro `GM` plus court (8 à 15 chiffres), il interroge en même temps DHL eCommerce, Colis Privé et Aramex, et range le colis chez DHL eCommerce si elle le connaît. Sur les formats partagés (16 ou 17 chiffres, `9261`/`9361`), il interroge en parallèle les transporteurs plausibles (par exemple TNT pour 16 chiffres ; pour `9261`/`9361`, Austrian Post en même temps que DHL eCommerce puis, seulement si aucun des deux ne le connaît, des services de suivi, la page d’USPS et la page mondiale de DHL). Si seule DHL eCommerce le connaît, Peek te l’indique ; si plusieurs marques le reconnaissent, Peek affiche chacune avec « A ce colis » pour que tu choisisses. Avec « Changer » (ou « Changer de transporteur »), Peek accepte pour DHL eCommerce tout code d’au moins 10 lettres et chiffres. Les liens `webtrack.dhlglobalmail.com`, `ecommerceportal.dhl.com` et `dhl.com` (lorsque le numéro a une forme DHL eCommerce, sauf un numéro `GM` court) fonctionnent aussi.

Webtrack est interrogé en premier. S’il ne liste pas le colis, Peek ouvre la page mondiale de suivi sur `dhl.com` via un navigateur, ce qui est plus long et peut échouer si DHL bloque les accès automatisés.

Peek affiche le statut, chaque scan avec ville et pays sur une carte, le jour de livraison prévu quand DHL l’indique, l’expéditeur et l’heure de livraison ; depuis Webtrack, également le poids et le transporteur final. Il écarte ton adresse et la référence client. Les scans gardent l’anglais américain de DHL, et toute livraison s’affiche comme « Livré ». Les heures sont lues sur l’horloge locale de chaque scan (une ligne sans lieu prend celle d’une ligne voisine) et converties dans ton fuseau horaire (« Heures dans ton fuseau horaire : »).

Quand Webtrack indique USPS, le colis bascule sur USPS avec son numéro une fois qu’USPS montre un scan au moins aussi récent que le dernier de DHL eCommerce, et la fiche affiche « Livraison par USPS » (ou « Annoncé à USPS, pas encore reçu » tant qu’USPS n’a aucun scan propre). Peek fait de même avec les autres transporteurs finaux que Webtrack nomme, comme La Poste, Austrian Post, Poczta Polska, Correos ou Poste Italiane. Les colis lus sur la page mondiale de DHL ne sont pas transférés à une poste locale. Tant que le colis est ouvert dans Peek, ou le jour de sa livraison avec les notifications activées, Peek vérifie jusqu’à toutes les 10 minutes et, le jour où le colis passe « En livraison », à partir de 6 h sur l’horloge du dernier scan, toutes les 2 minutes tant que Webtrack répond (toutes les 5 minutes quand la dernière vérification a ouvert la page de `dhl.com` dans un navigateur). Après deux jours sans nouvelle (12 heures tant que le colis reste sur « Étiquette créée » ou « Pris en charge »), il vérifie toutes les heures, et après sept jours sans nouvelle sur « Étiquette créée », toutes les 6 heures. Pour les notifications, tu peux choisir « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Après quatre jours sans scan sur un colis en « Étiquette créée », « Pris en charge » ou « En transit », Peek affiche « Aucune mise à jour depuis … », rappelle « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger DHL eCommerce. » et propose « Demande de l’aide à DHL eCommerce ». Peek suit plus de 4 500 transporteurs, mais ne peut pas contacter DHL eCommerce ni la boutique à ta place.

## Questions sur le suivi DHL eCommerce

### DHL eCommerce est-il identique à DHL Express ?

Non. DHL eCommerce est la division colis de DHL pour les boutiques en ligne et confie généralement la livraison finale à la poste locale ou à un autre transporteur. DHL Express dispose de son propre suivi, et Peek suit les deux séparément.

### DHL eCommerce livre-t-il par USPS ?

Aux États-Unis, oui : selon la FAQ Webtrack, USPS effectue la livraison finale. À l’étranger, c’est la poste locale ou un autre prestataire.

### Pourquoi n’y a-t-il pas encore d’informations de suivi DHL eCommerce ?

Selon la FAQ Webtrack, les événements apparaissent généralement sous 24 à 48 heures. S’il n’y a toujours rien au-delà de 48 heures, contacte la boutique qui a expédié le colis.

### DHL eCommerce est-il fiable ?

Oui. C’est la division colis e-commerce de DHL, anciennement appelée DHL Global Mail.

:::sources
- [DHL eCommerce : Webtrack tracking](https://webtrack.dhlecs.com/) – site de suivi, statuts, encadré détails, jusqu’à 10 numéros, conseils si introuvable, formulaire de contact, alertes e-mail
- [DHL eCommerce : Webtrack Contact Customer Service](https://webtrack.dhlecs.com/support/customer-service) – fiches pays, téléphones, horaires, FAQ sur le délai de 24 à 48 heures, livraison USPS, retards, colis manquants, changement d’adresse, consignes
- [Your Europe : Shipping and delivery](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_en.htm) – responsabilité du vendeur jusqu’à la livraison, 30 jours, délai supplémentaire, remboursement
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – ancien nom DHL Global Mail, formats de numéros et numéro USPS, libellés et étapes, suivi propre à DHL Express, ce que Peek lit et affiche
:::
