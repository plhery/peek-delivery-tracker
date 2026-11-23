---
title: Suivi Packlink : référence, statuts et qui contacter
description: Suis un colis Packlink : format ES…COM, sens des statuts, livraisons manquées, réclamations pour dommages et comment joindre Packlink.
slug: suivi-packlink
published: 2026-11-23
updated: 2026-11-23
---

Packlink n'est pas un transporteur. C'est une plateforme de réservation basée à Madrid qui se présente comme un comparateur d'envois : un particulier ou une boutique y compare des transporteurs comme SEUR, Correos, Correos Express ou UPS, paie, imprime l'étiquette, puis ce transporteur enlève et livre le colis. Les boutiques utilisent Packlink PRO, son service professionnel connecté à des sites e-commerce et places de marché comme eBay et Amazon. Pour suivre un colis, saisis la référence Packlink ou le numéro du transporteur sur [la page de suivi de Packlink](https://www.packlink.es/seguimiento-envios/) ou dans le champ ci-dessus.

## Numéro de suivi Packlink : à quoi il ressemble

Packlink attribue à chaque envoi une référence propre : deux lettres pour le marché, l'année, `COM` ou `PRO`, puis dix chiffres. Le site espagnol de Packlink indique que ses références commencent par ES. Le transporteur attribue aussi au colis son propre numéro, et la page de suivi de Packlink accepte l'un ou l'autre. Avec des chiffres fictifs :

| Numéro (fictif) | Ce que c'est |
| --- | --- |
| `ES2026COM4829173650` | ES, l'année, COM et 10 chiffres : une référence Packlink. |
| `ES2026PRO6194028375` | La même forme avec PRO : également une référence Packlink. |
| `48291736405829` | 14 chiffres : l'un des formats de SEUR, partagé par DPD et d'autres transporteurs. |
| `1Z9X482W0348291737` | 1Z suivi de 16 lettres ou chiffres : un numéro UPS. |

**Où le trouver.** Packlink envoie la référence dans l'e-mail de confirmation lors de la réservation de l'envoi. Si un vendeur t'a expédié un article via Packlink, c'est lui qui possède cet e-mail : demande-lui la référence ou le numéro du transporteur. Autres pistes : [comment trouver ton numéro de suivi](guide:find-tracking-number) et [achats d'occasion](guide:second-hand-orders).

## Statuts de suivi Packlink expliqués

Packlink lit chaque scan du transporteur et le classe sous son propre code de statut, affiché en anglais sur sa page de suivi. De nombreux scans conservent aussi les mots du transporteur, comme les majuscules espagnoles de SEUR ou de Correos Express. Les lignes les plus courantes, dans l'ordre :

| Ligne de suivi | Ce que cela signifie pour toi |
| --- | --- |
| `Waiting for carrier to collect the order` | L'expéditeur a réservé et dispose de l'étiquette. Le transporteur n'a pas encore le colis. |
| `SIN RECEPCION` | L'équivalent chez Correos Express : la commande est enregistrée, mais pas encore le colis. |
| `RECIBIDO EN ORIGEN` | Le premier dépôt SEUR a le colis. Packlink le classe pourtant encore en attente d'enlèvement. |
| `Shipment accepted by carrier (scanned)` | Le transporteur a scanné le colis. Il est pris en charge. |
| `EN ARRASTRE` | Acheminement entre les dépôts du transporteur. |
| `DELEGACION DESTINO` | Arrivé au dépôt qui livre dans ta zone. |
| `TU ENVÍO ESTÁ PENDIENTE DE LLEGAR A DESTINO` | SEUR : pas encore arrivé à ton dépôt local. Packlink le classe comme incident de livraison, mais le colis continue simplement son trajet. |
| `EN REPARTO` | En cours de livraison. |
| `REPARTO FALLIDO` | Échec d'une tentative de livraison. |
| `Package at pick-up point and ready for collection` | T'attend dans un point de retrait. Packlink le classe dans ses codes de livraison, mais tu dois encore aller le chercher. |
| `ENTREGADO` | Livré. |
| `Package returned to sender` | Le colis est reparti chez l'expéditeur. |

D'autres lignes sont explicites en anglais : `Recipient not present at indicated address` et `Unsuccessful delivery and final attempt` indiquent des livraisons manquées, et `Packet delivered to customs for clearance` une étape douanière pour un colis franchissant une frontière douanière.

Deux lignes ne disent rien du colis lui-même. `Pending, there is no information available on the carrier’s page or the tracking number has not yet been activated` signifie que Packlink n'a encore rien trouvé chez le transporteur. `Expired, there is no tracking information for the last 30 days` signifie que le transporteur n'a donné aucune nouvelle pendant un mois et que Packlink a cessé de le suivre.

## Qui livre un colis Packlink

Le transporteur choisi par l'expéditeur. La page de suivi de Packlink cite [SEUR](carrier:seur), [Correos](carrier:correos-spain), [Correos Express](carrier:correos-express), [UPS](carrier:ups), GLS, DHL, [TNT](carrier:tnt), [InPost](carrier:inpost), [Mondial Relay](carrier:mondial-relay), [NACEX](carrier:nacex) et Zeleris, entre autres. Les conditions de Packlink précisent :

- **Domicile ou point de retrait.** L'expéditeur choisit à la réservation si le colis part vers une adresse ou vers un point de retrait.
- **Livraison manquée.** Le transporteur peut laisser un avis pour reprogrammer la livraison et ramène le colis à son dépôt. Tous ne laissent pas d'avis : Packlink demande donc à l'expéditeur de surveiller le suivi et de t'avertir.
- **Tentatives.** Dans la plupart des cas, il y a une première tentative puis une seconde gratuite. Chaque tentative supplémentaire coûte à l'expéditeur de 2 € à 15 € hors TVA, selon le transporteur et le véhicule. Dans certains pays, le transporteur peut déposer le colis chez un voisin ou dans un point de retrait.
- **Retrait.** Un colis conservé au dépôt du transporteur doit être retiré dans le délai fixé par ce transporteur. Passé ce délai, l'expéditeur peut devoir payer des frais de stockage, de retour ou de destruction.
- **Refusé ou non livrable.** Packlink prévient l'expéditeur via le suivi ou par e-mail. L'expéditeur dispose alors de 2 jours ouvrés pour indiquer s'il souhaite récupérer le colis ou l'envoyer ailleurs.

Voir aussi [livraisons manquées](guide:missed-delivery) et [colis non retirés](guide:uncollected-parcel).

**Douane.** Si ton colis vient hors de l'UE et contient des marchandises achetées en ligne, un droit de douane européen de 3 € par type de produit s'applique sur les commandes jusqu'à 150 € depuis le 1er juillet 2026. Une taxe de traitement de l'Union de 2 € par type de produit est aussi due, quelle que soit la valeur, depuis le 1er novembre 2026. Les conditions de Packlink indiquent que le destinataire paie les droits et taxes d'importation, sans préciser comment ces deux frais te sont facturés. Voir [colis retenus en douane](guide:customs).

## Colis Packlink qui n'arrive pas, endommagé ou retourné

**Qui peut agir.** Les conditions de Packlink indiquent que seule la personne qui a réservé et payé peut ouvrir des réclamations et demandes, depuis l'adresse e-mail utilisée pour la réservation. Un tiers n'accède à une réclamation qu'avec une attestation sur l'honneur de l'expéditeur l'y autorisant. Si tu es l'acheteur, c'est donc au vendeur de contacter Packlink.

**Aucune mise à jour.** Tant que le suivi affiche `Waiting for carrier to collect the order`, le transporteur n'a pas enlevé le colis : demande à l'expéditeur s'il a bien été remis. Pour tout retard ou absence de mise à jour, c'est l'expéditeur qui contacte Packlink. Plus dans [pourquoi un suivi ne se met plus à jour](guide:tracking-not-updating).

**Perdu.** Packlink ne traite une réclamation pour perte qu'une fois que le transporteur a cherché dans ses dépôts et déclaré le colis perdu. Cette recherche prend habituellement jusqu'à 30 jours à compter de la date de livraison prévue. Voir [colis perdus](guide:lost-parcel).

**Endommagé.** Si des dégâts sont visibles à la livraison, écris *Con Daños* (avec dommages) sur le bon de livraison ou le terminal du livreur, comme le demande Packlink. Sans cette réserve, Packlink indique ne pas pouvoir traiter la réclamation de façon satisfaisante. Conserve l'article et tout son emballage pour inspection : Packlink rejette toute réclamation si l'objet a été manipulé ou réparé, ou si l'emballage a été jeté. Pour une perte ou un dommage caché, l'expéditeur doit ouvrir une réclamation sous 7 jours pour le transport routier (en Espagne ou à l'étranger), 10 jours par avion en national et 14 jours par avion à l'international, à compter du lendemain de la livraison. Voir [colis endommagés](guide:damaged-parcel).

**Retourné.** `Package returned to sender` signifie que le colis est revenu chez l'expéditeur. Demande-lui un nouvel envoi ou un remboursement. Voir [retourné à l'expéditeur](guide:returned-to-sender).

**Indiqué livré mais introuvable.** Vérifie auprès des voisins et du point de retrait, puis préviens aussitôt l'expéditeur. Voir [livré mais non reçu](guide:delivered-not-received).

## Téléphone et contact Packlink

Coordonnées publiées par Packlink :

- **Téléphone :** aucun. Packlink précise fonctionner uniquement en ligne et ne disposer d'aucune assistance téléphonique. Il avertit que les numéros de téléphone attribués à Packlink sur d'autres sites ne sont ni vrais ni vérifiés par Packlink : ne les appelle pas.
- **Formulaire de contact :** sur le centre d'aide de Packlink, accessible depuis sa page téléphone. Choisis la catégorie appropriée et, pour un envoi déjà effectué, prépare la référence de l'e-mail de confirmation. Packlink propose aussi un chat en direct avec son service client.
- **Réclamations :** via le même centre d'aide, uniquement par la personne qui a réservé.

## Suivre un colis Packlink avec Peek

Colle la référence Packlink dans [Peek](/). Sa forme étant propre à Packlink, la fenêtre d'ajout affiche aussitôt Packlink comme « Transporteur détecté ». Tu peux aussi coller directement le numéro du transporteur : Peek suit plus de 4 500 transporteurs. Si ce numéro correspond à plusieurs transporteurs, comme les 14 chiffres de SEUR, Peek affiche « Vérification des services de suivi… » pendant qu'il les interroge ; appuie sur « Changer » pour choisir le transporteur toi-même.

Tant que tu as le colis ouvert dans Peek, ou le jour de la livraison avec les notifications activées, Peek interroge directement Packlink jusqu'à toutes les 10 minutes, et jusqu'à toutes les 2 minutes à partir de 6 h heure de Madrid le jour où le colis passe en livraison, tant que le suivi de Packlink répond ; sinon toutes les heures. Après deux jours sans nouvelle (12 heures tant que le colis affiche encore « Étiquette créée » ou « Pris en charge »), Peek vérifie toutes les heures ; après sept jours sans nouvelle en « Étiquette créée », toutes les 6 heures. Après quatre jours sans scan sur un colis en « Étiquette créée », « Pris en charge » ou « En transit », Peek affiche « Aucune mise à jour depuis … », la note « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger Packlink. » et le lien « Demande de l’aide à Packlink ». Il affiche chaque scan dans les mots du transporteur quand Packlink les fournit, sinon dans le libellé anglais de Packlink, avec l'heure lue en heure de Madrid et affichée dans le fuseau horaire de ton appareil. Sur une ligne de point de retrait, Peek affiche « Prêt à être retiré » ; sur `RECIBIDO EN ORIGEN`, « Pris en charge ». Le jour de livraison prévu par Packlink apparaît pendant l'acheminement, et un envoi de plusieurs colis s'affiche en un seul historique. Peek écarte les lieux — il n'y a donc pas de carte — ainsi que le nombre de colis. Le suivi de Packlink ne donne ni poids, ni expéditeur, ni destinataire.

Le suivi de Packlink contient aussi le numéro propre au transporteur. Lorsque la forme de ce numéro désigne un transporteur avec certitude, comme un numéro UPS commençant par 1Z, Peek interroge également ce transporteur. Dès que celui-ci affiche un scan au moins aussi récent que le dernier de Packlink, Peek suit le colis là-bas en conservant l'historique de Packlink : le colis affiche alors « Livraison par UPS » et ses détails indiquent les deux numéros. Aucun format SEUR ni Correos Express n'est aussi univoque, donc leurs colis restent suivis via Packlink.

Peek peut t'avertir pour « Chaque scan », les « Étapes importantes » ou la « Livraison seulement », mais ne peut pas ouvrir de réclamation, modifier la livraison ni contacter qui que ce soit à ta place.

## Questions sur le suivi Packlink

### Packlink est-elle une société de livraison ?

Non. Packlink est une plateforme sur laquelle particuliers et boutiques comparent et réservent des transporteurs. Le transporteur choisi — comme SEUR, Correos Express ou UPS — enlève et livre le colis.

### Quel est le numéro de téléphone de Packlink ?

Aucun : Packlink fonctionne uniquement en ligne et prévient que les numéros trouvés sur d'autres sites ne sont pas vérifiés par lui. Utilise le formulaire de contact de son centre d'aide ou son chat en direct.

### Où trouver mon numéro de suivi Packlink ?

Dans l'e-mail de confirmation envoyé par Packlink lors de la réservation : deux lettres comme ES, l'année, COM ou PRO puis dix chiffres. Si tu es l'acheteur, demande-le à l'expéditeur.

### Pourquoi mon suivi Packlink affiche-t-il Expired ?

`Expired, there is no tracking information for the last 30 days` signifie que le transporteur n'a donné aucune nouvelle pendant un mois et que Packlink a cessé de suivre l'envoi. Cela ne veut pas dire que le colis est perdu. Demande à l'expéditeur de contacter Packlink.

:::sources
- [Packlink : Seguimiento envios](https://www.packlink.es/seguimiento-envios/) – suivi par référence Packlink ou numéro du transporteur, références commençant par ES, transporteurs listés, Packlink PRO
- [Packlink : ¿Y cómo funciona esto de Packlink?](https://www.packlink.es/como-funciona/) – comparaison et réservation de transporteurs, étiquettes, intégrations Packlink PRO avec eBay et Amazon
- [Packlink : Teléfono Packlink](https://www.packlink.es/telefono-packlink/) – comparateur, pas d'assistance téléphonique, numéros sur d'autres sites non vérifiés, formulaire de contact, chat en direct, référence dans l'e-mail de confirmation
- [Packlink : Términos y Condiciones](https://www.packlink.es/terminos-condiciones/) – société à Madrid, seul le donneur d'ordre peut réclamer, domicile ou point de retrait, avis, tentatives et coût, délai de retrait, colis refusés, droits d'importation, délais de réclamation, réserves pour dommages, recherche de 30 jours
- [Commission européenne : réforme douanière de l'UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – droit de douane de 3 € par type de produit jusqu'à 150 € depuis le 1er juillet 2026, taxe de traitement de l'Union de 2 € depuis le 1er novembre 2026
- [Commission européenne : orientations sur le droit de 3 € et la taxe de traitement de l'Union (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 € par type de produit quelle que soit la valeur, pour les biens vendus à distance
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – format de référence, formats des transporteurs, libellés et codes de statut, heure de Madrid, ce que Peek lit dans le suivi Packlink, passage au transporteur
:::
