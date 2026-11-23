---
title: Suivi Latvijas Pasts : numéros, statuts et consignes
description: Suivre un colis Latvijas Pasts (poste lettone) : format du numéro, sens des statuts, consignes, livraisons manquées et contacts.
slug: suivi-latvijas-pasts
published: 2026-11-23
updated: 2026-11-23
---

Latvijas Pasts est la poste nationale publique de Lettonie et exploite le plus grand réseau de consignes automatiques du pays. Tu peux suivre un envoi sur [mans.pasts.lv/track](https://mans.pasts.lv/track?locale=en-GB), qui s’ouvre en letton avec un sélecteur de langue proposant notamment le français et l’anglais, ou coller le numéro dans le champ ci-dessus. Le numéro figure dans l’e-mail d’expédition de la boutique ou de l’expéditeur, ou dans le SMS envoyé par le livreur avant son passage.

## Numéro de suivi Latvijas Pasts

Un envoi posté avec Latvijas Pasts porte un [numéro postal international](guide:tracking-number-formats) de 13 caractères : deux lettres, neuf chiffres, puis `LV`. Les deux premières lettres indiquent le service :

| Préfixe (exemples fictifs) | Type d’envoi |
| --- | --- |
| `RR482917367LV` | Envoi recommandé : remis contre signature. |
| `LA482917367LV` | Envoi suivi (Standard) : suivi à chaque étape, déposé en boîte aux lettres sans signature. |
| `CA482917367LV` | Colis : tu reçois un avis pour le retirer au bureau de poste. |
| `EE482917367LV` | Express Mail Service (EMS / Premium). |

Les envois Simple ou Economy ne sont enregistrés à aucune étape : ils ne sont pas suivis et n’ouvrent droit à aucune indemnisation en cas de dommage. Si tu n’as pas de numéro, ton colis a peut-être été expédié ainsi ; vois [comment trouver un numéro de suivi](guide:find-tracking-number).

Où chercher : le SMS du livreur indique le numéro d’envoi, le créneau estimé et un lien pour rediriger le colis. Le SMS d’une consigne automatique indique l’adresse de la consigne, la date limite de retrait et le code d’ouverture.

D’autres lettres finales désignent une autre poste : `…LT` correspond à [Lietuvos paštas](carrier:lietuvos-pastas) et `…EE` à [Omniva](carrier:omniva). Un envoi venu de l’étranger conserve son numéro d’origine, que mans.pasts.lv/track reconnaît dès que Latvijas Pasts a reçu l’envoi ou ses données ; les scans étrangers affichent alors le pays au lieu d’un bureau letton.

## Signification des statuts Latvijas Pasts

Le suivi officiel affiche chaque étape avec la date, l’heure et le bureau de poste letton. Dans le flux anglais utilisé par Peek, voici les libellés courants, dans leur ordre habituel :

| Statut Latvijas Pasts | Ce que cela signifie pour toi |
| --- | --- |
| `Electronic data about shipment received` | [Les données de l’expéditeur](guide:tracking-statuses) sont arrivées chez Latvijas Pasts, qui n’a pas encore le colis. |
| `Item information received` | L’expéditeur a enregistré l’envoi ; Latvijas Pasts ne l’a pas encore pris en charge. |
| `Item received from sender` | Latvijas Pasts a pris le colis en charge. |
| `Item posted / received from customer` | Déposé en bureau de poste ; le scan de dépôt d’une poste étrangère porte le même libellé. |
| `Item sent to the processing facility` | Parti du bureau de dépôt vers le centre de tri. |
| `Item arrived at office of exchange` | Arrivé au bureau d’échange international, à Riga ou à l’étranger. |
| `Item sent to the Customs` | Présenté à la douane au bureau d’échange lors de la sortie du pays d’envoi. |
| `Item returned from Customs` | Libéré par la douane : ce n’est pas un retour à l’expéditeur. |
| `Item sent abroad` | Parti du bureau d’échange vers le pays de destination. |
| `Item sent abroad (EDI-received)` | La poste étrangère a expédié l’envoi vers la Lettonie ; Latvijas Pasts le sait via ses données électroniques. |
| `Item accepted for temporary storage` | Envoi venu de l’étranger placé en dépôt temporaire sous contrôle douanier avant dédouanement. |
| `Electonic notification (SMS) of customs clearance sent to recipient` | Un SMS relatif au dédouanement t’a été envoyé (la faute d’orthographe vient de Latvijas Pasts). Le colis attend toujours en dépôt temporaire. |
| `Customs clearance completed, item to be delivered to recipient` | Dédouanement terminé ; l’envoi part vers son bureau de distribution. |
| `Item arrived at processing facility`, `Item arrived at sorting centre` | Arrivé dans un centre de tri. |
| `Item sent to next processing facility` | En route vers le centre de tri suivant. |
| `Item sent to delivery facility` | En route vers le bureau chargé de la livraison ou du retrait. |
| `Item received at delivery office` | Arrivé au bureau de distribution ; il peut encore partir en tournée depuis ce bureau. |
| `Item redirected` | Redirigé ailleurs, par exemple vers un autre point de retrait (voir la redirection ci-dessous). |
| `Item address re-labelled` | Réétiqueté pour sa nouvelle adresse, vers laquelle il repart. |
| `Item handed over for delivery` | Parti en tournée de livraison. |
| `Unsuccessful delivery attempt` | Échec de livraison (voir ci-dessous). S’il apparaît à la même seconde que `Item address re-labelled`, il enregistre une redirection : le colis poursuit sa route et Peek affiche « En transit », pas « Tentative de livraison ». |
| `Notification - pick-up invitation printed`, `Electronic notification (SMS) - pick-up invitation sent to recipient` | Prêt à être retiré : le bureau de distribution a imprimé l’avis de retrait ou te l’a envoyé par SMS. |
| `Item arrived at pick-up point` | Scan d’une poste étrangère : l’envoi attend en point de retrait à l’étranger (le suivi indique le pays, pas le point). |
| `Item delivered` | Livré, y compris lorsque la poste du pays de destination distribue un envoi venu de Lettonie. |

`Item returned from Customs` inquiète souvent à tort : cela signifie que le colis revient du contrôle douanier et reprend sa route, pas qu’il repart chez l’expéditeur. `Item awaiting next planned cycle` indique qu’il attend le prochain départ au bureau d’échange. Les envois e-commerce acheminés par un site de transit affichent aussi des libellés dédiés (`Parcel notify received`, `Parcel data received`, `Parcel has arrived at transit location`, `Parcel sent to destination country`, `Parcel has arrived in last mile`, `Parcel has gone thought last mile customs`). Pour les droits et frais sur les achats hors UE, consulte [colis bloqués en douane](guide:customs).

Si le suivi répond `No information received on the tracking of the postal item. Please, try again!`, vérifie le numéro ou patiente : l’envoi n’a peut-être pas encore été scanné, et un colis étranger n’apparaît qu’une fois ses données transmises par la poste d’origine. `Postal item number entered incorrectly. Please check it and enter it again.` signale une erreur de saisie. Si un numéro exact reste inconnu plusieurs jours, vérifie auprès de l’expéditeur qu’il a bien choisi un envoi suivi : les envois Simple et Economy n’apparaissent jamais.

## Consignes, livraisons par coursier et absences

**Consignes automatiques (pakomāti).** À l’arrivée en consigne, tu reçois un SMS indiquant l’adresse, la date limite et un code à six chiffres (ainsi qu’un e-mail si l’expéditeur a fourni ton adresse). Le colis reste **7 jours calendaires** ; s’il n’est pas retiré au troisième jour, le code est renvoyé, et un rappel arrive avant l’échéance. Passé sept jours, l’envoi est considéré comme non livré et renvoyé à l’expéditeur — sauf si un délai de conservation plus long s’applique, auquel cas il est transféré au point d’accueil client le plus proche et gardé **30 jours calendaires**. Si tu n’as pas reçu le code, demande à l’expéditeur de contacter Latvijas Pasts : la poste ne corrige le numéro de téléphone qu’à sa demande. Les consignes acceptent jusqu’à 30 kg et 38 × 38 × 58 cm (jusqu’à 60 cm de long en consigne extérieure).

**Consignes Unisend.** Un colis envoyé de Lettonie vers une consigne Unisend en Lituanie ou en Estonie attend également 7 jours calendaires, avec un code à six chiffres envoyé par SMS et e-mail (sur un numéro letton, lituanien ou estonien) et renvoyé au bout de 48 heures. Sans retrait, il repart directement à l’expéditeur.

**Coursier à domicile.** En cas de livraison par coursier, Latvijas Pasts indique effectuer jusqu’à trois tentatives. Si tu es absent et n’as pas redirigé le colis, le livreur laisse un avis et repasse ; si la livraison échoue encore, le colis est mis en garde et peut être retiré au bureau principal ou reprogrammé auprès du service client (la durée de garde est fixée par l’expéditeur). Pour un envoi Express Mail (EMS) manqué, un avis « You have received a shipment » est laissé en boîte aux lettres : appelle le numéro indiqué avec ton numéro d’envoi pour convenir d’un nouveau passage.

**Bureau de poste ou Customer Centre.** Tu reçois un SMS ou un avis papier précisant le bureau. Apporte une pièce d’identité valide ; le permis de conduire est accepté pour les envois recommandés ou assurés jusqu’à 150 €. Tu peux retirer sans l’avis si tu connais le numéro d’envoi, ou appeler le numéro figurant sur l’avis pour demander une livraison à domicile (frais payés à la remise). Un tiers peut retirer l’envoi avec ton autorisation et sa carte d’identité ou son passeport. Pour un envoi recommandé ou assuré, un second avis est envoyé au bout de 5 jours, et l’envoi repart à l’expéditeur s’il n’est pas retiré dans les 30 jours suivant le premier avis. Si l’étiquette d’une boutique étrangère ne porte qu’une partie de ton nom, présente ta pièce d’identité et une preuve que la commande est à toi.

**Redirection.** Si l’expéditeur a fourni ton numéro de téléphone ou si tu t’es inscrit aux notifications SMS, le SMS contient un lien unique pour rediriger le colis vers une consigne automatique (0,00 €, TVA incluse), un point d’accueil client (1,89 €, TVA incluse), une autre adresse par coursier (4,99 €, TVA incluse) ou le service gratuit « Postman at home » là où il existe. La redirection est impossible si le colis est déjà en consigne ou sous contrôle douanier, pour un recommandé ou envoi assuré avec preuve de livraison vers une consigne, pour un contre-remboursement dépassant 38 × 38 × 58 cm vers une consigne, ou en l’absence de numéro de téléphone. L’inscription aux notifications SMS permet aussi de choisir une consigne favorite.

## Colis Latvijas Pasts perdu, endommagé ou en retard

Pour un colis de consigne perdu ou endommagé, la réclamation doit être déposée par l’expéditeur (dans un délai de 6 mois selon les conditions des consignes) : si tu as commandé en ligne, contacte la boutique. La demande décrit le contenu et sa valeur avec justificatif (facture, reçu ou preuve bancaire) et des photos en cas de dommage ; Latvijas Pasts examine le dossier sous un mois. L’expéditeur peut céder l’indemnisation au destinataire.

Pour un recommandé ou envoi assuré livré par coursier, vérifie le contenu en présence du livreur afin d’établir et signer un constat. Si tu ouvres le colis seul et découvres un dommage, prends des photos et contacte l’expéditeur pour un remplacement ou un remboursement : Latvijas Pasts indique que l’indemnisation ne relève alors plus de sa responsabilité.

Pour un envoi expédié à l’étranger qui n’arrive pas, l’expéditeur peut demander une recherche internationale : elle est payante avant un mois et gratuite une fois 30 jours écoulés depuis le dépôt.

Suivi figé ou colis retenu en douane ? Consulte [suivi qui ne se met plus à jour](guide:tracking-not-updating) et [colis bloqués en douane](guide:customs).

## Contacter le service client Latvijas Pasts

Voici les coordonnées publiées sur pasts.lv :

- **Téléphone :** +371 27008001 (également +371 67008001 dans les conditions des consignes).
- **E-mail :** info@pasts.lv.
- **Horaires :** du lundi au vendredi de 8 h à 19 h, le samedi de 9 h à 17 h, fermé le dimanche.
- **Signaler un problème :** le formulaire sur [mans.pasts.lv/complaint](https://mans.pasts.lv/complaint), traité pendant les horaires du service client.

> Latvijas Pasts met en garde contre les faux SMS et e-mails demandant de confirmer une adresse ou de payer la douane sur des sites imitateurs, ainsi que contre les fausses ventes de « colis non réclamés ». Ses e-mails se terminent toujours par @pasts.lv, son site officiel est pasts.lv et la poste ne demande jamais de coordonnées bancaires par SMS ou e-mail ni de validation Smart-ID.

## Suivre Latvijas Pasts avec Peek

[Peek](/) reconnaît immédiatement un numéro postal commençant par C, E, L ou R et finissant par `LV` lorsque sa clé de contrôle est valide. Pour les autres numéros valides finissant par `LV`, comme `VV482917367LV`, Peek interroge simultanément Latvijas Pasts et [Chronopost](carrier:chronopost) et affiche « Latvijas Pasts a ce colis. » si Latvijas Pasts le reconnaît, ou « Pas encore trouvé chez … Peek continuera de vérifier après l’ajout. ». Si la clé de contrôle est fausse, Latvijas Pasts n’est pas interrogé et le choisir manuellement échoue (Peek indique qu’il « n’émet pas de numéros comme celui-ci ») : vérifie le numéro auprès de l’expéditeur. Un lien mans.pasts.lv/track contenant le numéro sélectionne toujours Latvijas Pasts, et taper « Latvian Post » dans le sélecteur le trouve aussi. Tant que Latvijas Pasts ne connaît pas le numéro, le colis reste en « En attente de Latvijas Pasts » ; si rien n’a répondu au bout de deux jours, Peek affiche « Après deux jours, le transporteur ne connaît toujours pas ce numéro. Vérifie-le, choisis un autre transporteur ou supprime le colis. », puis vérifie une fois par jour et une fois par semaine passée la première semaine.

Un colis venu de l’étranger est d’abord suivi chez sa poste d’origine. Dès que celle-ci indique la Lettonie comme destination (ou sans destination si tu es en Lettonie), Peek interroge aussi Latvijas Pasts avec le même numéro et bascule dessus dès son premier scan récent, tout en conservant l’historique antérieur. Pour le suivre directement chez Latvijas Pasts, sélectionne « Latvijas Pasts » via « Changer » à l’ajout ou « Changer de transporteur » sur le colis déjà ajouté.

Les horaires de Latvijas Pasts ne comportent pas de fuseau : Peek les lit à l’heure locale du lieu de scan (heure lettone pour les bureaux lettons) et affiche l’historique dans le libellé anglais de Latvijas Pasts, y compris `Item delivered`. Peek peut afficher le bureau où le colis attend, mais ne montre ni date d’arrivée estimée ni poids.

Peek vérifie jusqu’à toutes les 10 minutes tant que le colis est ouvert dans l’application ou le jour de livraison avec les notifications activées ; le jour où Latvijas Pasts remet le colis en livraison, à partir de 6 h heure locale (heure lettone pour les bureaux lettons), il vérifie toutes les 2 minutes tant que le suivi officiel répond. Sinon, il vérifie toutes les heures. Après 12 heures à « Étiquette créée » ou « Pris en charge », ou deux jours sans nouvelle, il vérifie toutes les heures même si le colis est ouvert, puis toutes les 6 heures après sept jours à « Étiquette créée ». Si le suivi de Latvijas Pasts ne répond pas et que Peek passe par des services de suivi tiers, il vérifie toutes les heures, toutes les 3 heures au bout d’un jour sans nouveau scan et, le jour de la livraison, jusqu’à toutes les 15 minutes tant que le colis est ouvert ou notifié. Lorsqu’un autre transporteur prend le relais, Peek suit son rythme.

Si quatre jours passent sans nouveau scan alors que le colis est à « Étiquette créée », « Pris en charge » ou « En transit », Peek le signale avec la date du dernier scan, par exemple « Aucune mise à jour depuis lun. 12 oct. ». Tu vois alors le message « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger Latvijas Pasts. » accompagné du lien « Demande de l’aide à Latvijas Pasts ». Après plus de trois semaines sans nouveauté, Peek affiche : « Rien de nouveau depuis plus de trois semaines. Si le colis n’est toujours pas arrivé, contacte le vendeur. Peek continue d’interroger Latvijas Pasts. »

Côté notifications, tu peux choisir « Chaque scan », « Étapes importantes » ou « Livraison seulement ».

## Questions fréquentes sur Latvijas Pasts

### Qu’est-ce que Latvijas Pasts ?

C’est l’opérateur postal national public de Lettonie, qui propose des services postaux, logistiques et financiers en Lettonie et à l’international sur pasts.lv.

### Unisend est-il Latvijas Pasts ?

Non : Unisend est la marque de colis de Lietuvos paštas, la poste lituanienne. Les deux opérateurs exploitent un réseau commun de consignes automatiques, permettant d’envoyer depuis la Lettonie vers les consignes Unisend en Lituanie et en Estonie.

### Peut-on suivre un envoi international Latvijas Pasts ?

Oui, s’il a été expédié avec suivi. mans.pasts.lv/track affiche aussi les scans de la poste étrangère en indiquant le pays au lieu d’un bureau, et propose un bouton « Pārrobežu izsekošana » (suivi international) vers un outil externe. Une fois le colis à l’étranger, c’est la poste du pays de destination qui assure la distribution.

### Le courrier ordinaire depuis la Lettonie a-t-il un numéro de suivi ?

Pas toujours. Les envois Simple et Economy ne sont pas enregistrés et ne peuvent pas être suivis ; seuls les envois recommandés, suivis, colis et EMS disposent d’un suivi.

:::sources
- [Latvijas Pasts : Customer support](https://pasts.lv/en/client-support) – préfixes par service, absence d’indemnisation Simple, recherche internationale, horaires, téléphone
- [Latvijas Pasts : Contacts](https://pasts.lv/en/about-us/contacts) – téléphone, horaires
- [Latvijas Pasts : Receive a parcel](https://pasts.lv/en/private-person/receive-parcel) – modes de réception, procuration
- [Latvijas Pasts : Receive by courier](https://pasts.lv/en/private-person/receive-parcel/receive-by-courier) – SMS coursier, trois tentatives, mise en garde, bureau principal, Postman at home
- [Latvijas Pasts : Receive item in a parcel locker](https://pasts.lv/en/useful/receiving-items-private/receive-in-parcel-locker) – SMS consigne, sept jours, rappel, 30 kg et 38 × 38 × 58 cm, consigne favorite
- [Latvijas Pasts : Delivery to address](https://pasts.lv/en/useful/receiving-items-private/delivery-to-address) – second avis après 5 jours, retour après 30 jours, avis EMS, nom incomplet sur achat à l’étranger
- [Latvijas Pasts : Receive at a Customer Centre](https://pasts.lv/en/private-person/receive-parcel/receive-at-customer-centre) – avis, pièce d’identité, permis jusqu’à 150 €, retrait sans avis, livraison à domicile payante
- [Latvijas Pasts : Shipment redirection](https://pasts.lv/en/services/private-person/receiving/redirection-of-shipments) – lien de redirection, options, tarifs TVA incluse, exclusions
- [Latvijas Pasts : Lost or damaged parcel locker shipments](https://pasts.lv/en/services/private-person/shipment-management/lost-or-damaged-parcel-locker-shipments) – réclamation par l’expéditeur, justificatifs, délai d’examen d’un mois
- [Latvijas Pasts : Damaged shipment](https://pasts.lv/en/useful/faulty-shipment) – vérification avec le coursier, recours auprès de l’expéditeur
- [Latvijas Pasts : Pakomātu pakalpojumu noteikumi](https://pasts.lv/media/789/download/Pakomatu_noteikumi.pdf) – code à six chiffres, e-mail, renvoi au 3e jour, retour après 7 jours ou garde 30 jours en point d’accueil, 6 mois de réclamation, consignes Unisend, adresse e-mail info@pasts.lv et +371 67008001
- [Latvijas Pasts : Pasargā sevi no krāpniekiem](https://pasts.lv/pasarga-sevi-no-krapniekiem) – alertes fraude SMS et e-mails
- [Latvijas Pasts : Mans pasts, track shipment](https://mans.pasts.lv/track?locale=en-GB) – suivi en ligne, sélecteur de langue, suivi international
- [Latvijas Pasts : Complaint form](https://mans.pasts.lv/complaint) – formulaire de signalement
- [Latvijas Pasts : About us](https://pasts.lv/en/about-us/par-pastu) – poste nationale publique, services
- [Latvijas Pasts : Joint parcel locker network with UNISEND](https://pasts.lv/en/news/latvijas-pasts-and-unisend-launch-their-joint-parcel-locker-network) – marque Unisend de Lietuvos paštas, réseau commun de consignes
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – ce que Peek lit et affiche, libellés de statuts et étapes, refus de numéro, relais depuis une poste étrangère
:::
