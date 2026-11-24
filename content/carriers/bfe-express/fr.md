---
title: Suivi BFE Express : numéros CK et WYB, statuts et aide
description: Suis un colis BFE Express vers l’Argentine, le Pérou, l’Uruguay et les États-Unis : formats CK et WYB, douane, statuts et contacts régionaux.
slug: suivi-bfe-express
published: 2026-11-24
updated: 2026-11-24
---

BFE Express (*BFEexpress*, exploité par Hongkong Bfe Info Tech Co Limited et fondé en 2005) est un réseau logistique e-commerce transfrontalier et de livraison du dernier kilomètre qui relie les vendeurs internationaux — dont les marchands Temu Argentine — aux destinataires en Argentine, au Pérou, en Uruguay et aux États-Unis. L’entreprise assure le fret international, le dédouanement, l’entreposage local et la livraison locale suivie sur [bfeexpress.com/tracking](https://www.bfeexpress.com/tracking).

Comme BFE Express gère l’arrivée internationale et le dédouanement avant de remettre les colis aux dépôts locaux du dernier kilomètre, un même suivi combine généralement des scans d’aéroport et de douane en anglais avec des scans locaux de livraison en espagnol.

## Formats de numéro de suivi BFE Express

Peek accepte les numéros BFE Express de 6 à 50 lettres et chiffres. Le formulaire de contact de BFE Express accepte ces formats, par destination :

| Format (inventé) | Destination et détails |
| --- | --- |
| `CK54829173604518` | `CK54` + 12 chiffres (16 caractères) — Argentine ; aucun autre transporteur dans Peek n’a cette forme. |
| `CK548291736045182` | `CK54` + 13 chiffres (17 caractères) — autre format pour l’Argentine (longueur commune avec [Colis Privé](carrier:colis-prive)). |
| `CK512829173604518` | `CK512` + 12 chiffres (17 caractères) — format Pérou (longueur commune avec [Colis Privé](carrier:colis-prive)). |
| `CK598829173604518` | `CK598` + 12 chiffres (17 caractères) — format Uruguay (longueur commune avec [Colis Privé](carrier:colis-prive)). |
| `WYB482917360` | `WYB` + 9 chiffres (12 caractères) — format de bordereau pour le Pérou (chevauchement avec [Purolator](carrier:purolator) et [TNT Australia](carrier:tnt-au)). |

Les chiffres qui suivent `CK` identifient la destination : `54` pour l’Argentine, `512` pour le Pérou et `598` pour l’Uruguay. Si tu colles un lien direct `bfeexpress.com/track?track_number=...`, Peek extrait le code et sélectionne immédiatement BFE Express. Pour d’autres formats transfrontaliers, consulte le guide sur les [formats de numéros de suivi](guide:tracking-number-formats).

## Signification des statuts de suivi BFE Express

Le suivi de BFE Express présente deux groupes de lignes — le premier tronçon international et le dernier kilomètre — et Peek lit les deux.

| Libellé BFE Express | Ce que cela signifie |
| --- | --- |
| `Arrived at Airport of destination` | Le vol transportant ton colis a atterri dans le pays de destination. |
| `Transport to next facility` | En transfert entre le terminal aéroportuaire et le dépôt local de dédouanement ou de tri. |
| `Customs declaration completed` | Dédouanement à destination terminé, avant la remise au dernier kilomètre. |
| `Turned over to last mile facility` | Transféré du tronçon de dédouanement de BFE au dépôt local chargé de la livraison. |
| `Se ha recibido el paquete.` / `Tu paquete fue recibido por en: ...` | Réceptionné au dépôt local du dernier kilomètre en Argentine ou en Uruguay. |
| `En embarque` / `En transporte` / `Tu paquete está en viaje a ...` | Chargé et acheminé entre dépôts nationaux vers ta ville. |
| `Tu paquete se encuentra en ...` / `En espera para salir a reparto en ...` | Arrivé à l’agence locale en attente de mise en tournée de livraison. |
| `Reparto en curso` | En cours de livraison aujourd’hui avec le chauffeur local. |
| `Entregado` | Livré au destinataire. Le statut global de BFE affiche aussi `Delivered` à côté. |

Les lignes en anglais de BFE Express placent le lieu en premier, avant une virgule (comme `<City>, Customs declaration completed`) ; les lignes en espagnol en Argentine placent le pays en premier, tandis que le dernier kilomètre uruguayen nomme le lieu après `en` ou `a`. Quand le statut global de BFE indique `Delivered`, Peek marque le colis comme livré même si la ligne la plus récente dit autre chose.

## Dédouanement, remise au dernier kilomètre et retards

Le site de BFE Express propose l’envoi de colis, le fret international, le dédouanement, l’entreposage local et la livraison du dernier kilomètre, et s’adresse notamment aux vendeurs Temu Argentine. Quelques repères aident à lire l’historique :

- **Avant l’aéroport de destination :** si le suivi de BFE Express n’affiche encore rien, consulte la page de commande du vendeur et lis le guide sur le [suivi bloqué](guide:tracking-not-updating).
- **Douane et dernier kilomètre :** `Customs declaration completed` et `Turned over to last mile facility` peuvent apparaître à une heure d’intervalle au même endroit, puis la réception par le dépôt local (`Se ha recibido el paquete.` ou `Tu paquete fue recibido por en: ...`) un jour plus tard. Si un colis s’arrête en douane à destination, consulte le [guide sur la douane](guide:customs).
- **Jour de livraison prévu :** BFE Express peut indiquer un jour de livraison prévu tant que le colis est en route, et Peek l’affiche. Peek le retire dès que le colis est livré, retourné ou prêt à être retiré, ou dès qu’un scan est postérieur à ce jour.
- **Colis indiqué livré ou endommagé :** la page [Support client BFE Express](https://www.bfeexpress.com/contactUS) mentionne ces deux cas dans sa FAQ et demande de garder ton numéro de suivi à portée de main avant d’écrire à ton équipe locale. Consulte aussi le guide [livré mais non reçu](guide:delivered-not-received).

Pour des colis domestiques en Argentine acheminés directement par des transporteurs locaux, compare avec [OCA](carrier:oca) et [Andreani](carrier:andreani).

## Suivre un colis BFE Express avec Peek

Colle ton code `CK...` ou `WYB...` dans Peek, qui suit plus de 4 500 transporteurs. Peek n’interroge jamais BFE Express sur un numéro nu : pour un code `CK54` de 16 caractères il n’interroge aucun transporteur, pour un code `CK` de 17 caractères il interroge [Colis Privé](carrier:colis-prive), et pour un code `WYB` il interroge [TNT Australia](carrier:tnt-au) et [Purolator](carrier:purolator) (Purolator en premier au Canada, TNT Australia en premier ailleurs). Il interroge ensuite des services de suivi (« Vérification des services de suivi… ») et n’associe le colis à BFE Express que si un service de suivi le nomme seul, avec des étapes datées. Appuie donc sur « Changer » et choisis BFE Express sous « Correspond à ce numéro », ou utilise « Changer de transporteur » sur un colis déjà ajouté. Un lien `bfeexpress.com/track?track_number=...` pointe tout de suite vers BFE Express.

Peek interroge directement l’API de suivi de BFE Express via l’[adaptateur BFE Express open source de universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/bfe-express). Il lit les deux groupes de lignes par ordre chronologique, retient la ville, l’agence ou le pays comme lieu, et conserve le jour de livraison prévu ainsi que le pays de destination. Une heure accompagnée de son décalage GMT garde cet instant exact. Une heure sans décalage est datée sur le fuseau de son lieu (heure de Buenos Aires pour Ezeiza, heure de Lima pour Lima, heure de Montevideo pour Montevideo), un scan sans lieu sur le fuseau du scan voisin, sinon à l’heure de Buenos Aires, et chaque scan s’affiche à l’heure de ton appareil. Les adresses de rue et photos de preuve de livraison du portail BFE sont écartées.

Peek classe les lignes de BFE Express en « En transit », sauf `Reparto en curso` qui passe en « En livraison » et `Entregado` qui passe en « Livré ». Il marque aussi le colis « Livré » lorsque le statut global de BFE affiche `Delivered`.

Tant que le colis reste ouvert dans Peek, ou le jour de la livraison si les notifications sont activées, Peek vérifie jusqu’à toutes les 10 minutes ; le jour où BFE Express affiche `Reparto en curso`, toutes les 2 minutes à partir de 6 h sur l’horloge du scan le plus récent, tant que le suivi de BFE Express répond. Sinon, Peek vérifie toutes les heures. Après 12 heures avec uniquement « Étiquette créée » ou « Pris en charge », ou deux jours sans nouvelles, il vérifie toutes les heures quelle que soit ta manière de suivre le colis, et toutes les 6 heures après sept jours à « Étiquette créée ». Lorsque BFE Express ne répond pas et que Peek suit le colis uniquement via des services de suivi, il vérifie toutes les heures, toutes les 3 heures après une journée calme, et jusqu’à toutes les 15 minutes le jour où le colis est en livraison, tant que le colis est ouvert ou que les notifications sont activées.

Lorsque le scan le plus récent date de quatre jours et que le colis est toujours sur « Étiquette créée », « Pris en charge » ou « En transit », Peek le signale avec le jour de ce scan, par exemple « Aucune mise à jour depuis lun 12 oct ». La fiche du colis indique alors « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger BFE Express. », avec un lien « Demande de l’aide à BFE Express » vers la page Contact Us de BFE Express. Après trois semaines sans nouveau scan, elle affiche « Rien de nouveau depuis plus de trois semaines. Si le colis n’est toujours pas arrivé, contacte le vendeur. Peek continue d’interroger BFE Express. »

Utilise les réglages d’alertes de Peek — « Chaque scan », « Étapes importantes » ou « Livraison seulement » — pour choisir quels événements déclenchent une notification.

## Contacter BFE Express

BFE Express publie des adresses e-mail de support client régionales sur sa page [Contact Us](https://www.bfeexpress.com/contactUS), toutes assurées du **lundi au vendredi, de 9 h 00 à 18 h 00** (hors jours fériés) :

- **Argentine :** `info@ar.bfeexpress.com`
- **Pérou :** `info@pe.bfeexpress.com`
- **Uruguay :** `info@uy.bfeexpress.com`
- **États-Unis :** `info@us.bfeexpress.com`
- **Paraguay :** `info_py@bfeexpress.com`
- **Autres régions prises en charge (Colombie, Équateur, Serbie, Dominique) :** `info@other.bfeexpress.com`
- **Site officiel et suivi :** [bfeexpress.com](https://www.bfeexpress.com/) et [bfeexpress.com/tracking](https://www.bfeexpress.com/tracking)

Garde ton numéro de suivi complet `CK...` ou `WYB...` sous la main avant d’écrire à l’équipe de ton pays.

## Questions fréquentes

### Pourquoi mon suivi BFE Express commence-t-il à l’aéroport de destination sans scans précédents ?

Le suivi de BFE Express peut débuter à `Arrived at Airport of destination`, l’arrivée du vol dans le pays de destination. Pour l’expédition avant ce point, vérifie la page de commande chez le vendeur.

### Pourquoi mes scans BFE Express sont-ils à la fois en anglais et en espagnol ?

BFE Express enregistre en anglais l’arrivée internationale à l’aéroport, la déclaration en douane et la remise au dernier kilomètre, tandis que le dépôt local qui assure la distribution finale publie ses scans en espagnol (jusqu’à `Reparto en curso` et `Entregado`).

### Que signifient les chiffres `54`, `512` et `598` dans les numéros `CK` ?

Ils désignent le pays de destination : `CK54...` correspond à l’Argentine (16 ou 17 caractères), `CK512...` au Pérou (17 caractères) et `CK598...` à l’Uruguay (17 caractères). Les envois vers le Pérou utilisent aussi un format de 12 caractères `WYB` + 9 chiffres.

### Que faire si BFE Express ne trouve pas mon numéro ?

Vérifie qu’il ne manque aucun caractère dans ton code `CK` ou `WYB`, et demande au vendeur s’il a déjà remis le colis à BFE Express.

:::sources
- [BFE Express](https://www.bfeexpress.com/)
- [BFE Express Tracking](https://www.bfeexpress.com/tracking)
- [BFE Express Customer Support](https://www.bfeexpress.com/contactUS)
- [universal-parcel-scraper BFE Express adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/bfe-express)
:::
