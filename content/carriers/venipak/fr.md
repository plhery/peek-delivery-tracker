---
title: Suivi Venipak : statuts, consignes et livraisons manquées
description: Suivez un colis Venipak en Lituanie, Lettonie ou Estonie : numéro de suivi, statuts, consignes, points relais, livraison manquée et service client.
slug: suivi-venipak
published: 2026-11-23
updated: 2026-11-23
---

Venipak est un transporteur qui livre des colis pour des boutiques en Lituanie, en Lettonie et en Estonie, à domicile, dans l’une de ses consignes automatiques ou dans un point de retrait. Saisissez le numéro sur la page de suivi Venipak de votre pays, ou collez-le dans le champ ci-dessus. Il est fourni par la boutique ou, en Lituanie, par Venipak par SMS.

## Où trouver votre numéro de suivi Venipak

La FAQ lituanienne de Venipak indique que le transporteur envoie le numéro du colis par SMS la veille de la livraison, puis de nouveau le jour même ; ses pages lettone et estonienne ne mentionnent qu’un SMS matinal indiquant le créneau de livraison. Vous pouvez également recevoir ce numéro de l’expéditeur, par exemple dans l’e-mail d’expédition de la boutique.

Les SMS et appels de Venipak ne parviennent qu’aux numéros de téléphone lituaniens, lettons ou estoniens : avec un numéro étranger, demandez le numéro de suivi à la boutique. Consultez [comment trouver votre numéro de suivi](guide:find-tracking-number) si rien n’est arrivé.

Les pages d’aide de Venipak n’indiquent aucun format, mais ses étiquettes suivent une structure précise (chiffres inventés) :

| Numéro | De quoi il s’agit |
| --- | --- |
| `V48291E7364058` | Un numéro de colis Venipak : `V`, 5 chiffres, `E`, 7 chiffres. Les 5 chiffres identifient le compte de l’expéditeur, les 7 derniers le colis. |
| `48291736` | Un numéro d’envoi de 7 à 10 chiffres, qui peut regrouper plusieurs colis. |

Le formulaire de Venipak accepte un code d’envoi ou de colis (« Shipment/parcel code »), ou un identifiant client (« Customer ID ») accompagné d’un numéro de document (« Document number »), que seul l’expéditeur détient. Les [numéros baltes d’un autre format](guide:tracking-number-formats) appartiennent à un autre transporteur : les codes `JJEE` et les numéros postaux se terminant par `EE` reviennent à Omniva, les numéros postaux se terminant par `LT` à [Lietuvos paštas](carrier:lietuvos-pastas), et les numéros de 14 chiffres le plus souvent à DPD.

## Signification des statuts de suivi Venipak

Peek interroge le suivi Venipak en anglais ; voici donc les libellés anglais de Venipak. Lorsqu’une ligne en indique deux, le second correspond à la version anglaise du site lituanien. Chaque étape affiche le statut puis le lieu : un terminal en majuscules tel que `VILNIUS TM2`, ou une consigne Venipak avec son nom et sa ville.

| Statut | Ce que cela signifie pour vous |
| --- | --- |
| `At sender` | La boutique a [enregistré le colis](guide:tracking-statuses) et l’a toujours en sa possession. |
| `Is placed for dispatch` | L’expéditeur l’a déposé dans une consigne Venipak, indiquée après le statut. |
| `Picked up`, `Picked up from sender` | Un livreur l’a collecté chez l’expéditeur ou dans la consigne de dépôt. |
| `At terminal` | Il se trouve dans un terminal de tri Venipak, comme KAUNAS ou VILNIUS TM2. |
| `Out for delivery`, `The parcel is out for delivery` | En cours d’acheminement vers votre domicile, une consigne ou un point de retrait. |
| `Delivered to pickup point` | Prêt à être retiré : le colis attend dans la consigne ou au point de retrait indiqué. Le code PIN arrive par SMS, ou un code QR par e-mail. |
| `Delivered` | Remis à votre porte, ou retiré de la consigne ou du point de retrait indiqué ensuite. |

Deux formulations prêtent souvent à confusion : `Delivered to pickup point` signifie que le colis attend son retrait et que le délai de garde a commencé, tandis que `Delivered` couvre aussi un colis retiré d’une consigne. `Wrong shipment ID` n’est pas un statut : c’est le message par lequel le suivi Venipak rejette un numéro ; vérifiez qu’il ne comporte pas de faute de frappe.

Venipak ne publie pas de liste de statuts, et aucun statut correspondant à une tentative échouée ou à un retour n’apparaît dans son suivi. Si le colis reste sur `At terminal`, Venipak conseille d’attendre le SMS précisant quand et comment il sera livré.

## Consignes, points de retrait et livraisons manquées Venipak

**Délais de livraison.** Venipak indique qu’environ 98 % des colis arrivent le jour ouvré suivant. Les livraisons à domicile ont lieu du lundi au vendredi, de 8 h à 17 h (heure locale), et le matin du jour prévu un SMS annonce un créneau horaire ; les pages lituanienne et lettone ajoutent que vous pouvez appeler le livreur au numéro figurant dans le message. Les livraisons le samedi n’ont lieu qu’à Vilnius, Kaunas, Klaipėda, Šiauliai, Panevėžys et Alytus, lorsque l’expéditeur a commandé cette option.

**À domicile.** Le livreur peut remettre le colis à une autre personne présente à l’adresse, par exemple un collègue ou un voisin. Vérifiez l’emballage en présence du livreur et notez tout dommage visible sur le bordereau : Venipak décline toute responsabilité pour les dégâts non signalés à ce moment-là.

**En cas d’absence.** Venipak peut déposer le colis dans la consigne ou le point de retrait le plus proche : convenez-en avec le livreur, ou appelez ou écrivez au service client. Sans consigne de votre part, une nouvelle tentative a lieu le jour ouvré suivant. Les conditions de Venipak prévoient deux tentatives supplémentaires après la première absence ; après trois tentatives, ou si vous refusez le colis, il est renvoyé à l’expéditeur.

**Changement d’adresse.** Possible dès le jour ouvré suivant la collecte par le chauffeur : envoyez la nouvelle adresse par e-mail au service client ou appelez-le, et le colis arrive un jour ouvré plus tard. La FAQ lituanienne autorise également un changement le jour même de la livraison, qui est alors reportée au jour ouvré suivant.

**Consignes automatiques.** Un SMS est envoyé dès que le colis est placé dans le casier ; en Estonie, un SMS ou un e-mail, selon les coordonnées transmises par l’expéditeur. Retirez-le sous 3 à 5 jours calendaires (la date limite figure dans le SMS ou l’e-mail) avec le code PIN reçu par SMS ou le code QR reçu par e-mail. Si la consigne choisie est en panne ou n’a plus de casier disponible à la bonne taille, le colis est déposé dans la consigne libre la plus proche : vérifiez le message reçu.

**Points de retrait.** Ce sont des commerces ou centres de services ouverts en semaine et le week-end, pour les colis trop volumineux pour une consigne. Venipak indique 5 jours ouvrés sur une page et 5 jours calendaires sur une autre : fiez-vous à votre SMS. Le délai de garde ne peut pas être prolongé, et tout colis non retiré repart chez l’expéditeur.

**Retours.** L’expéditeur fixe les modalités : remettez le colis à un livreur ou déposez-le dans une consigne ou un point de retrait Venipak muni de l’étiquette de retour.

## Colis Venipak en retard, perdu ou endommagé

Le contrat avec Venipak appartient à la boutique ; un article perdu, endommagé ou erroné passe donc par l’expéditeur : la FAQ réclamations de Venipak précise que le destinataire doit contacter l’expéditeur.

**Aucune mise à jour depuis plusieurs jours.** Attendez le SMS de Venipak ; si rien n’arrive, contactez la boutique. Le guide sur [un suivi qui ne se met pas à jour](guide:tracking-not-updating) détaille les causes habituelles.

**Marqué livré mais introuvable.** Regardez le lieu indiqué après `Delivered` : il peut s’agir d’une consigne ou d’un point de retrait. Contactez ensuite la boutique et Venipak, et consultez [colis livré mais non reçu](guide:delivered-not-received).

**Endommagé ou perdu.** C’est l’expéditeur qui dépose la réclamation, avec des photos des articles et de l’emballage extérieur ainsi que la facture en cas d’avarie, ou la facture et d’éventuelles photos en cas de perte. Les conditions de Venipak fixent un délai de six mois à compter de l’expédition pour une réclamation écrite ; sa FAQ en anglais annonce un traitement sous 14 jours ouvrés pour les envois nationaux (14 jours calendaires dans la FAQ lituanienne) et jusqu’à 90 jours calendaires pour les envois internationaux.

## Service client et numéros de contact Venipak

Chaque pays dispose de son propre service client (horaires en heure locale balte) :

- **Lituanie :** +370 700 55221 (au tarif de votre forfait), du lundi au vendredi de 8 h à 18 h ; helpdesk.lt@venipak.com ; chat et formulaire « Send a message » sur [la page contacts de Venipak Lituanie](https://venipak.com/lt/en/contacts/).
- **Lettonie :** +371 6601 6601, du lundi au vendredi de 8 h à 17 h ; helpdesk.lv@venipak.com ; formulaire sur [la page contacts de Venipak Lettonie](https://venipak.com/lv/en/contacts/).
- **Estonie :** +372 641 4481, du lundi au vendredi de 8 h à 17 h ; helpdesk.ee@venipak.com ; formulaire sur [la page contacts de Venipak Estonie](https://venipak.com/ee/en/contacts/).

> Venipak met en garde contre de faux SMS usurpant son identité en Lituanie, en Lettonie et en Estonie. Signalez toute fraude à sukciai@venipak.com en Lituanie ou à fraud@venipak.com en Lettonie et en Estonie.

## Suivre un colis Venipak avec Peek

[Peek](/) reconnaît immédiatement un numéro comme `V48291E7364058` comme appartenant à Venipak ; aucun autre transporteur ne partage ce format, et les minuscules ou un espace avant le `E` sont acceptés. Les liens de suivi sur venipak.com ou venipak.lt contenant le numéro fonctionnent aussi ; depuis un lien venipak.lv, Peek ne lit qu’un numéro de colis au format `V…E…`.

Un numéro d’envoi de 7 à 10 chiffres comme `48291736` n’est jamais reconnu automatiquement comme Venipak : choisissez Venipak avec « Changer », ou collez un lien venipak.com ou venipak.lt. Un envoi d’un seul colis est alors suivi sous son numéro de colis ; s’il compte plusieurs colis, ajoutez chaque numéro de colis séparément.

Les scans Venipak ne comportent pas de fuseau horaire : Peek les lit à l’heure locale du lieu du scan — la même dans les trois pays baltes — et affiche l’historique propre de Venipak. Tant que le colis est ouvert dans Peek, ou le jour de sa livraison si les notifications sont activées, Peek vérifie le suivi jusqu’à toutes les 10 minutes, et jusqu’à toutes les 2 minutes à partir de 6 h (heure locale du lieu du scan, commune aux trois pays baltes) le jour où Venipak scanne le colis en cours de livraison. Après deux jours sans nouvelles (12 heures tant que le colis affiche encore « Étiquette créée » ou « Pris en charge »), Peek vérifie toutes les heures, et après sept jours sans nouvelles à l’étape « Étiquette créée », toutes les 6 heures.

Si quatre jours passent sans nouvelle mise à jour alors que le colis reste sur « Étiquette créée », « Pris en charge » ou « En transit », Peek affiche « Aucune mise à jour depuis quatre jours » (ou « Aucune mise à jour depuis {{date}} »), accompagné du message « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger Venipak. » et du lien « Demande de l’aide à Venipak ».

Peek peut indiquer la consigne ou le point de retrait où le colis attend, avec sa ville. Les statuts conservent les libellés anglais de Venipak, sauf `Delivered` et `Out for delivery`, traduits dans les propres termes de Peek. Vous pouvez choisir des notifications pour « Chaque scan », « Étapes importantes » ou « Livraison seulement ».

Venipak ne fournit ni date de livraison prévue ni poids. Peek omet les coordonnées cartographiques, le nombre de colis et la vérification du destinataire ; il n’affiche pas non plus le code PIN ou QR de la consigne (envoyé par Venipak par SMS ou e-mail) et ne peut pas contacter Venipak à votre place.

## Questions sur le suivi Venipak

### Puis-je suivre un colis Venipak sans numéro de suivi ?

Oui, dans la nouvelle application Venipak : selon les actualités estoniennes de Venipak, l’application fonctionne dans les trois pays baltes et affiche tous les colis Venipak liés à votre compte via votre adresse e-mail ou votre numéro de téléphone. Sur le site web, le formulaire Venipak accepte sinon un identifiant client et un numéro de document, mais seul l’expéditeur les possède : demandez à la boutique ou, en Lituanie, attendez le SMS de Venipak la veille au soir.

### Puis-je changer l’adresse d’un colis Venipak ?

Oui, dès le jour ouvré suivant la collecte par le chauffeur, ou même le jour de la livraison selon la FAQ lituanienne de Venipak. Envoyez la nouvelle adresse par e-mail au service client ou appelez-le, et le colis arrive un jour ouvré plus tard. Pour l’orienter plutôt vers une consigne automatique, la nouvelle application Venipak permet aussi de le rediriger, selon les actualités estoniennes de Venipak.

### Venipak livre-t-il le lendemain ?

En général oui : Venipak indique qu’environ 98 % des colis arrivent le jour ouvré suivant. Les livraisons à domicile ont lieu du lundi au vendredi ; le samedi uniquement dans six villes lituaniennes, lorsque l’expéditeur a commandé l’option.

:::sources
- [Venipak : Parcel tracking](https://venipak.com/lt/en/parcel-tracking/) – formulaire, SMS avec le numéro, conseil pour le terminal, créneau horaire, numéro du livreur, consignes, absence, changement d’adresse
- [Venipak : Private customers](https://venipak.com/lt/en/private-customers/) – options, jour ouvré suivant, numéros locaux, points de retrait, samedi, paiement, retours
- [Venipak : Contacts (Lithuania)](https://venipak.com/lt/en/contacts/) – téléphone, horaires, e-mail, chat, adresse fraude, société
- [Venipak : Contacts (Latvia)](https://venipak.com/lv/en/contacts/) – téléphone, horaires, e-mail, société
- [Venipak : Contacts (Estonia)](https://venipak.com/ee/en/contacts/) – téléphone, horaires, e-mail
- [Venipak : Claims FAQ](https://venipak.com/lt/en/faq/claims-faq/) – qui réclame, justificatifs, délais de traitement
- [Venipak : Siuntos sekimas](https://venipak.com/lt/siuntos-sekimas/) – changement d’adresse le jour de livraison (lituanien)
- [Venipak : Pretenzijų DUK](https://venipak.com/lt/duk/pretenziju-duk/) – réclamations traitées sous 14 jours calendaires (lituanien)
- [Venipak : Parcel rules (Lithuania)](https://venipak.com/lt/wp-content/uploads/Venipak-parcel-delivery-rules-LT-eng.pdf) – tentatives, retour, remise, réserves d’avarie, délai de réclamation
- [Venipak : Lockers](https://venipak.com/lt/en/lockers/) – SMS au dépôt, code QR
- [Venipak : Parcel tracking (Estonia)](https://venipak.com/ee/en/parcel-tracking/) – identifiant client et numéro de document
- [Venipak : Private customers (Latvia)](https://venipak.com/lv/en/private-customers/) – durée de garde en point de retrait
- [Venipak : BUJ (Latvia)](https://venipak.com/lv/buj/) – SMS matinal avec créneau de livraison, numéro du livreur (letton)
- [Venipak : Kā tas strādā? (Latvia)](https://venipak.com/lv/palidziba/pakomati/ka-tas-strada/) – code PIN par SMS ou lien QR par e-mail (letton)
- [Venipak : Fraudsters are now impersonating real couriers](https://venipak.com/lv/en/news/2025-11-13/venipak-warns-businesses-fraudsters-are-now-impersonating-real-couriers/) – faux SMS
- [Venipak Estonia : KKK](https://venipak.com/ee/kkk/) – SMS matinal avec créneau prévu (estonien)
- [Venipak Estonia : Kuidas see töötab](https://venipak.com/ee/abi/pakiautomaadid/kuidas-see-tootab/) – avis de consigne par SMS ou e-mail, PIN ou code QR (estonien)
- [Venipak Estonia : Venipak hoiatab ettevõtteid: petturid esitlevad end kulleritena](https://venipak.com/ee/uudised/2025-11-13/venipak-hoiatab-ettevotteid-petturid-esitlevad-end-kulleritena/) – faux SMS en Estonie (estonien)
- [Venipak Estonia : Venipaki uue äpiga saab pakiautomaadi ukse avada otse telefonist](https://venipak.com/ee/uudised/2026-10-22/venipaki-uue-apiga-saab-pakiautomaadi-ukse-avada-otse-telefonist/) – application : colis liés au compte, redirection vers une consigne (estonien)
- [WordPress.org : Venipak shipping for WooCommerce](https://wordpress.org/plugins/wc-venipak-shipping/) – format du numéro de colis
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – ce que lit Peek
:::
