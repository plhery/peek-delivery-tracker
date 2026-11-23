---
title: Suivi Quick Rabbit : codes PKG, statuts et Citibox
description: Suivez un colis Quick Rabbit en Espagne : format du code PKG, signification des statuts espagnols, consignes Citibox, modification de livraison et contact.
slug: suivi-quick-rabbit
published: 2026-11-23
updated: 2026-11-23
---

Quick Rabbit est une société de livraison espagnole pour les boutiques en ligne et les commerçants, basée à San Fernando de Henares près de Madrid. Elle livre dans toute l’Espagne à domicile, en point de retrait et dans les consignes Citibox. Suivez un colis avec son code `PKG` sur [la page de suivi de Quick Rabbit](https://web.quickrabbit.es/es/tracking) ou collez-le dans le champ ci-dessus. Quick Rabbit indique que ce code figure dans votre e-mail de confirmation ou sur votre reçu d’expédition.

## Numéro de suivi Quick Rabbit : le code PKG

Quick Rabbit l’appelle le code de suivi (`código de seguimiento`). Il existe en deux formats, et le suivi accepte aussi la référence de l’expéditeur (chiffres inventés) :

| Numéro | De quoi il s’agit |
| --- | --- |
| `PKG48220260715482917365` | `PKG`, trois chiffres, une date à huit chiffres (année, mois, jour) et neuf autres chiffres : le code Quick Rabbit habituel. |
| `PKG482260715ES4829173640582K` | `PKG`, neuf chiffres, `ES`, 13 chiffres et une lettre : une variante plus longue. |
| `WT4829173640582917` | `WT` suivi de 16 chiffres : une référence de l’expéditeur, affichée sous `Referencia` sur la page Quick Rabbit. |

Un code inconnu du suivi renvoie `Paquete no encontrado` (« Colis introuvable ») : vérifiez-le dans l’e-mail de la boutique. Aucun code du tout ? Demandez-le à la boutique, ou consultez [comment trouver un numéro de suivi](guide:find-tracking-number).

## Signification des statuts de suivi Quick Rabbit

La page de suivi Quick Rabbit s’ouvre en espagnol (l’anglais et le chinois sont également disponibles). Voici ses libellés espagnols, avec la ligne anglaise et le statut affiché par Peek pour chacun, à peu près dans l’ordre où un colis les traverse :

| Ce qu’indique Quick Rabbit | Dans Peek : ligne, statut | Ce que cela signifie pour vous |
| --- | --- | --- |
| `Documentado`, `Creado` | `Documented`, `Created` : Étiquette créée | La boutique a enregistré le colis. Quick Rabbit ne l’a pas encore en main. |
| `Ingresó a Centro de Clasificación`, `Salió de Centro de Clasificación` | `Entered Sorting Center`, `Left Sorting Center` : En transit | Entré puis sorti du centre de tri Quick Rabbit. |
| `Tránsito a almacén local`, `Recibido en Almacén`, `En Almacén Local` | `Transit to local warehouse`, `Received at Warehouse`, `Local Warehouse` : En transit | En route vers l’entrepôt local qui dessert votre secteur, puis arrivé sur place. |
| `Listo para Asignación de Ruta` | `Ready for Route Assignment` : En transit | Dans un bac de livraison, en attente d’affectation à la tournée d’un livreur. |
| `Repartidor Tiene el Paquete`, `En Reparto` | `Driver Has Package`, `In Delivery` : En livraison | Un livreur a pris le colis et effectue sa tournée. |
| `Entregado al Destinatario`, `Entregado a Vecino`, `Entregado al Portero`, `Entregado en Buzón`, `Entregado en Recepción`, `Entregado a Compañero de Piso` | `Delivered to Recipient`, `Delivered to Neighbor`, etc. : Livré | Livré à vous-même, à un voisin, au gardien, dans la boîte aux lettres, à l’accueil ou à un colocataire. |
| `Entrega Citibox Iniciada` | `Citibox Delivery Initiated` : En livraison | Le livreur achemine le colis vers une consigne Citibox. |
| `Depositado en Citibox` | `Deposited in Citibox` : Prêt à être retiré | Le colis est dans la consigne et vous attend. |
| `Retirado de Citibox` | `Retrieved from Citibox` : Livré | Vous l’avez retiré de la consigne. |
| `Almacenamiento Citibox Expirado` | `Citibox Storage Expired` : Problème signalé | Le délai de garde dans la consigne a expiré. |
| `En Camino a Punto de Recogida`, puis `En Punto de Recogida` ou `Listo para Recoger` | `En Route to Pickup Store` (En transit), puis `At Pickup Store` ou `Ready for Pickup` : Prêt à être retiré | En route vers un point de retrait, puis prêt à y être retiré. |
| `Destinatario Ausente`, `Primera Ausencia`, `Segunda Ausencia`, `Tiempo Insuficiente`, `Fuera de Horario` | `Recipient Absent`, `First Absence`, `Second Absence`, `Insufficient Time`, `Outside Delivery Hours` : Tentative de livraison | Personne n’était présent, ou le livreur a manqué de temps ou s’est présenté hors horaires de service. |
| `Dirección Incorrecta`, `Dirección Incompleta`, `Destinatario Desconocido` | `Incorrect Address`, `Incomplete Address`, `Unknown Recipient` : Tentative de livraison | Le livreur n’a pas pu livrer à l’adresse indiquée : corrigez-la (voir ci-dessous). |
| `Entrega Rechazada` | `Delivery Rejected` : Tentative de livraison | Le colis a été refusé à la porte. |
| `Potencialmente perdido` | `Under investigation` : Problème signalé | Quick Rabbit vérifie où se trouve le colis. Il peut toujours être en cours d’acheminement. |
| `Paquete perdido`, `No localizado` | `Lost Package`, `Not located` : Problème signalé | Perdu, ou introuvable à l’endroit prévu. |
| `Incidencia logística` | `Logistics incident` : Problème signalé | Un incident de livraison. La note de Quick Rabbit indique « Puede solicitar el reembolso » (vous pouvez demander un remboursement) pendant que son équipe examine le dossier. |
| `Preparado para Devolución`, `Devolución tras intentos estándar`, `Devuelto al Remitente` | `Prepared for Return`, `Return after standard attempts`, `Returned to Sender` : Retourné à l’expéditeur | En retour vers la boutique, par exemple après les tentatives prévues, puis revenu chez l’expéditeur. |

Certaines lignes se contentent de consigner un événement : `Cliente Solicitó Cambio de Dirección` (vous avez demandé un changement d’adresse), `No Se Pudo Contactar al Cliente` (Quick Rabbit n’a pas réussi à vous joindre), `Almacén - Esperando Gestión de Cliente` (retenu à l’entrepôt en attendant que le service client règle un incident). `Primer Intento de Entrega desde Almacén` indique qu’une tentative a été programmée, pas son résultat. Peek affiche ces lignes sans modifier le statut du colis.

## Livraison Quick Rabbit : livreurs, Citibox et points de retrait

Quick Rabbit déclare couvrir les 50 provinces d’Espagne, et sa page de services propose aux boutiques des notifications par SMS et e-mail ainsi qu’une nouvelle tentative après un échec de livraison. Livraison manquée ? Consultez [livraisons manquées](guide:missed-delivery).

**Consignes Citibox et points de retrait.** Plutôt qu’à votre porte, le livreur peut déposer le colis dans une consigne Citibox ou l’acheminer vers un point de retrait. Les pages de Quick Rabbit n’indiquent pas le code d’ouverture de la consigne, ne listent pas les points de retrait et ne précisent pas la durée de garde dans l’un ou l’autre. `Retirado de Citibox por Repartidor` (« Retiré de Citibox par le livreur ») signifie que le livreur a repris le colis dans la consigne. Plus de détails dans [consignes automatiques](guide:parcel-lockers) et [colis non retirés](guide:uncollected-parcel).

## Modifier une livraison Quick Rabbit : adresse, téléphone ou date

Tant que le colis est en cours d’acheminement, la page de suivi Quick Rabbit affiche un encart `Actualizar datos de entrega` (« Mettre à jour les données de livraison »). Saisissez votre adresse e-mail et Quick Rabbit vous envoie un lien sécurisé. Il vous permet de modifier le numéro de téléphone ou l’adresse (uniquement dans la même ville), ou de choisir une date de livraison souhaitée. Les changements s’appliquent « dans les prochaines heures ». Chaque lien autorise un nombre limité de modifications et a une durée de validité.

Ne tardez pas trop. Une fois que « plus de 5 jours se sont écoulés depuis sa réception », selon les termes de Quick Rabbit, le lien répond « Ya no es posible realizar cambios en este paquete » (il n’est plus possible d’apporter de modifications à ce colis) et le colis « comenzará el proceso de devolución a origen » : il entame son retour vers l’expéditeur. Plus de précisions dans [changer une adresse de livraison](guide:change-delivery-address).

## Colis Quick Rabbit en retard, perdu ou livré mais non reçu

Pour tout problème concernant un colis, la page de contact de Quick Rabbit renvoie vers la procédure de réclamation disponible sur sa page de suivi.

**Livré mais non reçu.** Lisez d’abord la ligne de livraison : un voisin ou le gardien l’a peut-être réceptionné. Toujours rien ? Sur la page de suivi d’un colis livré, appuyez sur `Click aquí` sous « ¿Quieres informar sobre un problema? » (« Souhaitez-vous signaler un problème ? »). Confirmez l’adresse e-mail utilisée pour la commande et Quick Rabbit vous envoie les instructions par e-mail. Le formulaire de réclamation demande ce qui s’est passé, peut demander des photos et vous laisse choisir une réponse par e-mail, téléphone ou SMS. Plus d’informations dans [livré mais non reçu](guide:delivered-not-received).

**Bloqué, perdu ou endommagé.** Si rien ne bouge depuis des jours, consultez [suivi qui ne se met pas à jour](guide:tracking-not-updating). Pour un colis indiqué comme perdu, endommagé ou volé, l’encart d’aide de la page de suivi devient `Soporte Urgente` (assistance urgente), avec un lien vers la page de contact de Quick Rabbit. En vertu des règles européennes, la boutique reste responsable de votre commande jusqu’à sa réception : demandez-lui aussi un remboursement ou un nouvel envoi. Voir [colis perdus](guide:lost-parcel), [colis endommagés](guide:damaged-parcel) et [retourné à l’expéditeur](guide:returned-to-sender).

## Contact Quick Rabbit

- **E-mail :** customer@quickrabbit.es ; la page de contact promet une réponse sous 2 heures aux heures ouvrables.
- **WhatsApp :** +34 628 437 468, indiqué sur la page de contact en anglais.
- **Horaires :** du lundi au vendredi de 8 h à 20 h, le samedi de 9 h à 14 h, fermé le dimanche (heure de Madrid ; la FAQ présente ces horaires comme ceux de l’équipe commerciale), avec une assistance aux incidents 24 h/24.
- **Adresse :** Av. del Sistema Solar, 27, Nave 3, 28830 San Fernando de Henares, Madrid.

Le formulaire de la page de contact est réservé aux professionnels demandant un devis.

## Suivre un colis Quick Rabbit avec Peek

[Peek](/) reconnaît un numéro Quick Rabbit à sa forme : `PKG`, trois chiffres, la date à huit chiffres et neuf chiffres, ou le code plus long `PKG…ES…`, en majuscules ou minuscules, espaces acceptés. Une référence `WT` de 16 chiffres ne suffit pas à elle seule : Peek interroge Quick Rabbit et affiche « Quick Rabbit a ce colis. » lorsqu’il reconnaît le numéro ; sinon, Peek consulte aussi des services de suivi et continue de vérifier après l’ajout du colis. Pour une référence d’un autre format, collez-la tout de même (Peek suit plus de 4 500 transporteurs), appuyez sur « Changer » et sélectionnez Quick Rabbit. Pour une référence, Peek affiche ensuite le code `PKG` sous « Quick Rabbit le suit sous le numéro ». Un lien de suivi Quick Rabbit contenant `ref=` fonctionne également.

Peek lit directement le suivi de Quick Rabbit : le statut, l’historique dans les libellés anglais ci-dessus et l’heure de livraison, interprétés à l’heure de Madrid et affichés dans le fuseau horaire de votre appareil. Quick Rabbit n’indiquant pas de lieux, il n’y a pas de carte. Peek laisse de côté la date de livraison estimée, la ville de destination, la référence de l’expéditeur et les coordonnées du livreur, qui peuvent correspondre à votre porte. Peek n’affiche ni preuve de livraison, ni signataire, ni code de consigne, et ne transfère pas le colis à Citibox : les étapes en consigne proviennent des propres lignes de Quick Rabbit.

Tant que le colis est ouvert dans Peek, ou le jour de sa livraison si les notifications sont activées, Peek vérifie le suivi jusqu’à toutes les 10 minutes, et jusqu’à toutes les 2 minutes à partir de 6 h (heure de Madrid) le jour où Quick Rabbit scanne le colis en cours de livraison. Après deux jours sans nouvelles (12 heures tant que le colis affiche encore « Étiquette créée » ou « Pris en charge »), Peek vérifie toutes les heures, et après sept jours sans nouvelles à l’étape « Étiquette créée », toutes les 6 heures.

Si quatre jours passent sans mise à jour alors que le colis reste sur « Étiquette créée », « Pris en charge » ou « En transit », Peek affiche « Aucune mise à jour depuis quatre jours » (ou « Aucune mise à jour depuis {{date}} »), accompagné du message « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger Quick Rabbit. » et du lien « Demande de l’aide à Quick Rabbit ».

Peek peut vous alerter pour « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Il ne peut pas modifier la livraison, ouvrir une réclamation ni contacter Quick Rabbit ou la boutique à votre place.

## Questions sur le suivi Quick Rabbit

### Quick Rabbit est-il fiable ?

Oui. C’est une entreprise logistique espagnole qui revendique plus de 5 ans d’expérience dans la livraison pour des boutiques en ligne et des détaillants partout en Espagne, et cite parmi ses partenaires AliExpress, Amazon, [Cainiao](carrier:cainiao), DHL, [Paack](carrier:paack), [CTT Express](carrier:ctt-express), [MRW](carrier:mrw) et Celeritas.

### Comment changer mon adresse de livraison Quick Rabbit ?

Appuyez sur `Actualizar datos de entrega` sur la page de suivi et entrez votre e-mail. Le lien permet de modifier l’adresse dans la même ville, le numéro de téléphone ou la date. Une fois que plus de 5 jours se sont écoulés depuis la réception du colis, selon les termes de Quick Rabbit, aucune modification n’est plus possible et le colis repart à l’expéditeur.

### Que signifie « Potencialmente perdido » ?

« Potentiellement perdu » : Quick Rabbit recherche où se trouve le colis et indique qu’il peut toujours être en transit ou en cours de livraison. Peek affiche `Under investigation`. Si `Paquete perdido` (« Colis perdu ») suit, demandez un remboursement ou un nouvel envoi à la boutique.

### Comment contacter Quick Rabbit ?

Écrivez à customer@quickrabbit.es ou sur WhatsApp au +34 628 437 468. Pour un problème avec un colis, utilisez la réclamation depuis la page de suivi, comme le demande la page de contact.

:::sources
- [Quick Rabbit : Seguimiento de Paquetes](https://web.quickrabbit.es/es/tracking) – code de suivi et où le trouver, message introuvable, référence, statuts, modification de livraison, réclamations, assistance urgente
- [Quick Rabbit : Package Tracking](https://web.quickrabbit.es/en/tracking) – suivi en anglais
- [Quick Rabbit : Contacto](https://web.quickrabbit.es/es/contact) – réclamations via le suivi, e-mail, délai de réponse, horaires (ceux de l’équipe commerciale, selon la FAQ), adresse
- [Quick Rabbit : Contact](https://web.quickrabbit.es/en/contact) – WhatsApp
- [Quick Rabbit : E-commerce & retail logistics in Spain](https://web.quickrabbit.es/en) – 50 provinces, réclamations depuis la page de suivi
- [Quick Rabbit : About Us](https://web.quickrabbit.es/en/about) – plus de 5 ans, partenaires
- [Quick Rabbit : Our Services](https://web.quickrabbit.es/en/services) – notifications SMS et e-mail, nouvelle tentative
- [Your Europe : Shipping and delivery](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_en.htm) – responsabilité de la boutique jusqu’à réception, remboursement
- [Universal Parcel Scraper : Quick Rabbit](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/quick-rabbit) – formats de numéros, recherche par référence expéditeur, statuts et étapes, ce que Peek conserve
:::
