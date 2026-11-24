---
title: Suivi OCA : numéros 19 chiffres, statuts, agences et aide
description: Suis un colis OCA ou e-Pak en Argentine : numéros à 19 chiffres, statuts en espagnol, 2 visites à domicile, retrait 5 jours en agence et contacts d’aide.
slug: suivi-oca
published: 2026-11-24
updated: 2026-11-24
---

OCA est un transporteur argentin de colis et de courrier. Sa plateforme ePak accompagne l’e-commerce, les grandes entreprises et les PME à travers le pays, et collabore avec Mercado Envíos. Les destinataires suivent leurs colis sur [la recherche d’envois OCA](https://www.oca.com.ar/Busquedas/Envios), retirent leurs colis en instance dans les agences Puntos OCA des provinces argentines ou les récupèrent dans les consignes OCA SmartLockers.

Le suivi d’OCA enregistre en espagnol chaque scan de dépôt, transfert d’agence, passage à domicile et délai de retrait.

## Formats des numéros de suivi OCA

Les numéros standards des colis OCA e-Pak suivent une structure de 19 chiffres :

- **19 chiffres avec `0000` aux positions 6 à 9** (comme `3654800004829173605`) — le numéro de bordereau ou d’ordre d’admission (`número de guía` / `orden de admisión`) recommandé par le Centre d’aide OCA pour le suivi.
- **Un `-1` séparé** — les étiquettes peuvent afficher `-1` après le numéro, par exemple `3654800004829173605-1`. Le [Centre d’aide OCA](https://www.oca.com.ar/CentroDeAyuda) indique de ne pas le saisir. Ommets-le aussi dans Peek : Peek ne supprime que le tiret, si bien que `...-1` devient un numéro à 20 chiffres qu’OCA ne connaît pas.
- **Colis et enveloppes affranchis** — OCA émet également des envois timbrés à 3 lettres majuscules (`YAE`, `YAA`, `MDA`, `PAA`) suivies de 8 chiffres pour les colis, ou 2 lettres suivies de 8 chiffres pour les enveloppes timbrées, à saisir sans espaces ni parenthèses sur la page de suivi d’OCA. Le courrier simple non timbré (`postales simples`) ne comporte pas de suivi et est déposé directement en boîte aux lettres ou sous la porte.

Sur le site d’OCA, il est possible de remplacer la série de zéros centraux par `//` ; Peek a besoin des 19 chiffres complets, ou d’un lien direct `oca.com.ar/Seguimiento/Paquetes/...` ou ancien lien e-Pak, que Peek lit automatiquement. En Argentine, Peek interroge OCA pour un numéro à 19 chiffres de cette forme ; ailleurs, il n’interroge OCA qu’en dernier recours. Pour d’autres formats régionaux, voir le [guide des formats de numéros de suivi](guide:tracking-number-formats).

## Ce que signifient les statuts de suivi OCA

Le service de suivi e-Pak d’OCA renvoie des statuts en espagnol et ajoute souvent l’agence traitante après un tiret (`- Suc: ...`) ou un motif entre parenthèses.

| Libellé de statut OCA | Ce que cela signifie |
| --- | --- |
| `En proceso de Retiro` | L’enlèvement chez l’expéditeur est planifié ; OCA n’a pas encore collecté le colis. |
| `Retirado` / `Retirado en Origen` | OCA a collecté le colis chez l’expéditeur. |
| `En Proceso en OCA` / `Procesado en OCA` | Traité dans un centre de tri ou une agence OCA. |
| `En viaje a Centro de Distribución de Destino` / `En viaje a Sucursal de Destino` / `En Tránsito a Suc. de Destino` | En transit vers le hub de destination ou l’agence de livraison. |
| `Arribado a Centro de Distribución de Destino` / `Llegó a nuestra sucursal` | Arrivé au hub de destination ou à l’agence OCA locale. |
| `Programado para visita a domicilio` | Inscrit en programmation de tournée (`en programación`) à l’agence locale avant un passage à domicile. |
| `Visita a Domicilio en Curso` | En tournée avec le livreur pour une livraison à domicile aujourd’hui. |
| `No entregado (No Visitado)` / `No entregado (...)` | La livraison n’a pas abouti ; le texte entre parenthèses précise le motif (par exemple non visité ou arrêté à la demande de l’expéditeur). |
| `Reprogramado para nueva visita` | Reprogrammé pour une nouvelle tentative de livraison. |
| `En Espera de Retiro por Sucursal` / `Disponible para que lo retires en tu Sucursal` | Mis en instance à l’agence OCA attribuée, prêt à être retiré. |
| `Estado de Visita ONLINE (Entregado)` / `Entregado` | Livré au destinataire ou retiré à l’agence. |
| `Acuse digitalizado` | Preuve de livraison numérisée au centre OCA plusieurs jours après la livraison — purement administratif, ne déplace pas le colis. |
| `Devolución en Proceso` / `Devuelto al Remitente` | En cours de retour ou retourné à l’expéditeur. |

## Passages à domicile, agences et SmartLockers

Le [Centre d’aide OCA](https://www.oca.com.ar/CentroDeAyuda) définit des règles précises pour les livraisons en Argentine :

- **Deux tentatives de livraison à domicile :** OCA effectue deux visites sans rendez-vous du lundi au samedi, de 09 h 00 à 18 h 00. Toute personne de 18 ans ou plus présente à l’adresse peut réceptionner le colis sur présentation de son DNI (pièce d’identité nationale). Les jours de pluie peuvent limiter les tournées.
- **Retrait en agence (5 jours ouvrables) :** si les deux passages échouent, le colis attend pendant **5 jours ouvrables** à l’agence Punto OCA désignée (trouve une agence via [Buscador de Puntos OCA](https://www.oca.com.ar/Busquedas/Sucursales)). Le destinataire doit présenter son DNI et le numéro de suivi ; un tiers autorisé de plus de 18 ans doit fournir une copie du DNI du destinataire, une autorisation manuscrite et le numéro de suivi. Les colis non réclamés retournent à l’expéditeur.
- **SmartLockers (48 heures + prolongation payante) :** lorsqu’OCA dépose un colis dans un SmartLocker, tu reçois un e-mail et/ou un SMS avec un code QR et un code PIN alphanumérique. Tu disposes de **48 heures consécutives** pour le retirer ; après des rappels, OCA envoie un lien de paiement par carte permettant d’acheter jusqu’à **3 jours de garde supplémentaires** avant le retour à l’expéditeur.
- **Adresse erronée ou colis retournés :** OCA n’autorise ni l’interruption d’un envoi ni la modification de l’adresse de livraison en cours de transit. Attends la fin des passages pour le retirer en agence (ou laisse-le revenir à l’expéditeur afin qu’il le réexpédie). Pour plus d’options après un passage manqué, voir le [guide livraison manquée](guide:missed-delivery).
- **Envois spéciaux (DNI, passeports, cartes OCA Geo, enveloppes) :** les DNI sont livrés uniquement à domicile (avec le récépissé RENAPER) et ne peuvent pas être retirés en agence ; les passeports passent en retrait d’agence après deux visites à domicile. Les cartes OCA Geo doivent être remises en main propre au titulaire. Les enveloppes affranchies bénéficient de deux passages à domicile puis retournent à l’expéditeur sans mise en instance en agence.
- **48 heures ouvrables pour le premier scan :** OCA précise que les envois nouvellement déposés peuvent mettre jusqu’à **48 heures ouvrables** après dépôt en agence pour apparaître en ligne. Si rien n’apparaît après 48 heures ouvrables, seul l’expéditeur peut ouvrir une réclamation. Voir [quand le suivi ne se met plus à jour](guide:tracking-not-updating) pour les vérifications générales.

Pour d’autres transporteurs nationaux argentins, compare avec [Andreani](carrier:andreani).

## Suivre un colis OCA avec Peek

Colle un numéro OCA à 19 chiffres sans le `-1`, ou un lien `oca.com.ar/Seguimiento/Paquetes/...`, dans Peek. Si Peek ne verrouille pas OCA, choisis OCA via **Changer** lors de l’ajout du colis ou **Changer de transporteur** sur la fiche de suivi.

Peek interroge directement le service web e-Pak `Tracking_Pieza` d’OCA via l’[adaptateur OCA open source de universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/oca). Chaque horodatage argentin est enregistré comme instant exact en `America/Argentina/Buenos_Aires` (`-03:00`), le nom des agences est extrait proprement dans le champ lieu et les enregistrements administratifs `Acuse digitalizado` n’écrasent ni ne perturbent jamais une livraison déjà effectuée. Le flux e-Pak d’OCA n’inclut ni date de livraison estimée ni poids du colis.

Peek convertit les statuts OCA en étapes claires : **Étiquette créée** (`En proceso de Retiro`), **Pris en charge** (`Retirado`), **En transit** (centres de tri et transferts d’agences), **En livraison** (`Programado para visita a domicilio` et `Visita a Domicilio en Curso`), **Tentative de livraison** (`No entregado` et `Reprogramado para nueva visita`), **Prêt à être retiré** (`En Espera de Retiro por Sucursal`), **Livré** (`Entregado`) et **Retourné à l’expéditeur** (`Devolución en Proceso` / `Devuelto al Remitente`).

Tant que le colis reste ouvert dans Peek, ou le jour de la livraison si les notifications sont activées, Peek vérifie jusqu’à toutes les 10 minutes ; le jour où OCA affiche `Programado para visita a domicilio` ou `Visita a Domicilio en Curso`, toutes les 2 minutes à partir de 6 h heure argentine, tant que le suivi d’OCA répond. Sinon, Peek vérifie toutes les heures. Après 12 heures avec uniquement « Étiquette créée » ou « Pris en charge », ou deux jours sans nouvelles, Peek passe à une vérification par heure quelle que soit ta manière de suivre le colis, et toutes les 6 heures après sept jours à « Étiquette créée ». Lorsque le suivi OCA ne répond pas et que Peek suit le colis uniquement via des services de suivi, il vérifie toutes les heures, toutes les 3 heures après une journée calme, et jusqu’à toutes les 15 minutes le jour de la livraison tant que le colis est ouvert ou que les notifications sont activées.

Lorsque le scan le plus récent date de quatre jours et que le colis est toujours sur « Étiquette créée », « Pris en charge » ou « En transit », Peek le signale avec le jour de ce scan, par exemple « Aucune mise à jour depuis lun. 12 oct. ». La fiche du colis indique alors « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger OCA. », avec un lien « Demande de l’aide à OCA » vers le Centre d’aide d’OCA. Après trois semaines sans nouveau scan, elle affiche « Rien de nouveau depuis plus de trois semaines. Si le colis n’est toujours pas arrivé, contacte le vendeur. Peek continue d’interroger OCA. »

Choisis **Chaque scan**, **Étapes importantes** ou **Livraison seulement** dans les réglages d’alertes de Peek pour décider quelles mises à jour déclenchent des notifications.

## Contacter OCA

- **Centre d’aide et formulaire de contact en ligne :** [OCA Centro de Ayuda](https://www.oca.com.ar/CentroDeAyuda) (regroupe les rubriques FAQ et le formulaire de message direct en bas de page)
- **Suivi officiel des envois :** [OCA Búsqueda de envío](https://www.oca.com.ar/Busquedas/Envios)
- **Recherche d’agences :** [Buscador de Puntos OCA](https://www.oca.com.ar/Busquedas/Sucursales)
- **Site principal :** [oca.com.ar](https://www.oca.com.ar/)
- **L’expéditeur d’abord pour les numéros absents ou réexpéditions :** OCA précise que seul l’expéditeur est habilité à lancer une recherche pour un colis encore introuvable 48 heures ouvrables après le dépôt ou à organiser une réexpédition après retour.

## Questions fréquentes

### Pourquoi mon numéro de suivi OCA se termine-t-il par `-1` ?

Le Centre d’aide OCA demande d’omettre le `-1` séparé et de suivre le colis avec le numéro de bordereau ou d’admission à 19 chiffres. Fais de même dans Peek : il ne retire pas le `-1`, et le numéro qui le conserve n’est pas reconnu par OCA.

### Combien de temps OCA garde-t-il un colis en agence ou en SmartLocker ?

OCA conserve les colis dans les agences Puntos OCA pendant **5 jours ouvrables** après échec de livraison ou arrivée en agence. En SmartLocker, tu disposes de **48 heures consécutives** gratuites, avec la possibilité de payer en ligne jusqu’à **3 jours supplémentaires** avant le retour à l’expéditeur.

### Puis-je modifier mon adresse de livraison après la prise en charge par OCA ?

Non. Le Centre d’aide OCA indique que ni le parcours de l’envoi ni l’adresse de livraison ne peuvent être modifiés en cours de transit. Après les passages à domicile, tu peux retirer le colis en personne à l’agence OCA attribuée avec ton DNI, ou le laisser retourner à l’expéditeur.

### Pourquoi mon numéro de suivi OCA n’affiche-t-il encore aucune mise à jour ?

OCA précise qu’un colis peut mettre jusqu’à **48 heures ouvrables** après le dépôt en agence pour apparaître sur le suivi en ligne. Passé 48 heures ouvrables sans enregistrement, contacte l’expéditeur, seul autorisé à ouvrir une vérification d’envoi manquant auprès d’OCA.

:::sources
- [OCA Argentina](https://www.oca.com.ar/)
- [OCA Centro de Ayuda](https://www.oca.com.ar/CentroDeAyuda)
- [OCA Búsqueda de envío](https://www.oca.com.ar/Busquedas/Envios)
- [OCA Buscador de Puntos OCA](https://www.oca.com.ar/Busquedas/Sucursales)
- [adaptateur OCA de universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/oca)
:::
