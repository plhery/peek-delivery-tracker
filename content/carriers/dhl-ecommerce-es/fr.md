---
title: Suivi DHL eCommerce Iberia : statuts et ServicePoints
description: Suivre un colis DHL eCommerce en Espagne ou au Portugal : sens des statuts, livraisons manquées, ServicePoints, CTT Express et réclamations.
slug: suivi-dhl-ecommerce-iberia
published: 2026-11-22
updated: 2026-11-22
---

DHL eCommerce Iberia est le réseau colis de DHL en Espagne et au Portugal, anciennement DHL Parcel Iberia — le nom DHL Parcel apparaît encore à certains endroits. Colle le numéro dans la case ci-dessus pour suivre ton colis ; sur sa fiche, « Ouvrir le site DHL eCommerce Iberia » t’envoie vers le même envoi sur le suivi propre de DHL. Le numéro vient de la boutique qui a expédié le colis.

## Numéro de suivi DHL eCommerce : où le trouver et à quoi il ressemble

Pas de numéro ? Demande à la boutique, ou consulte [où trouver ton numéro de suivi](guide:find-tracking-number).

Les formats connus par le catalogue de Peek (exemples inventés) :

| Format | Exemple | À qui il peut appartenir |
| --- | --- | --- |
| 10 chiffres | `4829173649` | DHL eCommerce Iberia, mais aussi [DHL Express](carrier:dhl-express), [Mondial Relay](carrier:mondial-relay), [Relais Colis](carrier:relais-colis) et GLS Espagne |
| Deux lettres, 7 chiffres, puis `0` | `KL48291730` | DHL eCommerce Iberia (et TNT Italie) |
| `JJD000061`, `JJD000268`, `JJD000269` ou `JJD000368`, puis 14 chiffres | `JJD00006148291736482917` | DHL eCommerce Iberia |

Si l’e-mail de la boutique mentionne [Mondial Relay](carrier:mondial-relay) ou GLS Espagne pour un numéro à 10 chiffres, suis-le chez ce transporteur. Deux lettres, neuf chiffres et `ES` désignent un numéro [Correos](carrier:correos-spain).

## Signification des statuts de suivi DHL eCommerce

Peek demande à DHL ses libellés anglais, bien que quelques scans arrivent encore en espagnol, comme `Llegada a`, `Tránsito en`, `Salida de` et `Entregado`. Les statuts habituels, dans l’ordre où un colis les rencontre :

| DHL indique | Ce que ça veut dire pour toi |
| --- | --- |
| `Shipment data received` | L’expéditeur a enregistré le colis. DHL ne l’a pas encore reçu. |
| `Picked up` | DHL a collecté le colis chez l’expéditeur ; le nom de la ville suit. |
| `Shipment received at DHL Depot` | Le colis est arrivé dans un dépôt DHL. |
| `Arrived at` | En transit entre dépôts ; la ville suit. `In transit in` et `Departed from` fonctionnent pareil. |
| `Out for delivery` | À bord du véhicule de livraison aujourd’hui. |
| `The delivery could not be made due to consignee absent` | Personne n’a répondu. Voir la section suivante pour la suite. |
| `Delivered to DHL ServicePoint, ready for collection by the recipient` | Prêt à être retiré dans un point relais ServicePoint. |
| `Parcel picked up by recipient` | Retiré au ServicePoint. |
| `Delivered` | Livré à l’adresse. |
| `Shipment being returned to sender` | Retour à l’expéditeur en cours. |

Certains statuts méritent précision :

- `Delivered, POD pending` : marqué livré, mais la preuve de livraison n’est pas encore enregistrée. Ce statut peut apparaître tôt : une nouvelle tournée ou une tentative échouée peut encore suivre.
- `Shipment with incidence If you are the sender, please contact DHL Parcel, if you are the recipient please contact the sender` : un incident touche le colis. Comme l’indique le message, le destinataire doit contacter l’expéditeur : adresse-toi à la boutique.
- `The expected delivery date might be postponed one working day` et `Not delivered today: delivery expected for the next working day` sont de courts reports, pas un colis perdu.

## Livraison manquée et ServicePoints

**Personne à domicile.** Une tentative échouée s’affiche sous `The delivery could not be made due to consignee absent`, ou `Receiver closed: delivery not possible` si le lieu était fermé. Des scans ultérieurs comme `New delivery date requested` ou `Delivery agreed with recipient` indiquent qu’une nouvelle date a été demandée ou convenue. Si tu vois plutôt `Incorrect address details: delivery not possible`, l’adresse n’a pas permis la livraison : Peek affiche « Problème signalé », et toi ou l’expéditeur devez corriger l’adresse avant que le colis ne reparte.

**ServicePoints.** `Parcel will be delivered to a DHL ServicePoint` et `Parcel is on it's way to our DHL ServicePoint` signifient que le colis se dirige vers un commerce DHL ServicePoint. Une fois disponible sur place, Peek affiche une carte « Point de retrait » avec le nom et l’adresse du magasin, l’itinéraire et un bouton pour copier l’adresse. Peek ne reçoit pas les horaires d’ouverture : vérifie-les, ainsi que le délai de garde, directement auprès de DHL.

## Colis en retard, perdu ou abîmé

**Aucune mise à jour.** Voir [pourquoi un suivi s’arrête de se mettre à jour](guide:tracking-not-updating).

**Perdu ou abîmé.** Commence par la boutique : le scan `Shipment with incidence` de DHL demande lui-même au destinataire de contacter l’expéditeur. Dans l’UE, Your Europe rappelle que le vendeur reste responsable des dommages jusqu’à la réception du bien. Sans date convenue, il doit livrer sous 30 jours ; à défaut, relance-le en accordant un délai supplémentaire raisonnable, après quoi tu peux annuler et obtenir remboursement. Voir [colis perdus](guide:lost-parcel) et [colis abîmés](guide:damaged-parcel).

**Douane.** Pour un colis provenant hors de l’UE, consulte le [guide des douanes](guide:customs).

**CTT Express.** DHL eCommerce et CTT ont signé une coentreprise le 12 mai 2026 : au Portugal, l’activité de DHL eCommerce passe chez CTT Expresso, et en Espagne [CTT Express](carrier:ctt-express) reprend les livraisons aux particuliers tandis que DHL conserve les livraisons aux entreprises. Certains colis portent un numéro CTT Express : suis-les avec [CTT Express](carrier:ctt-express).

> Un SMS ou un e-mail suspect concernant un colis DHL ? Lis [comment repérer un faux message de livraison](guide:delivery-scam-texts).

## Contacter DHL eCommerce

Recherche les coordonnées de DHL eCommerce sur le site DHL du pays de livraison, Espagne ou Portugal. Pour un colis perdu ou abîmé, adresse-toi d’abord à la boutique.

## Suivre un colis DHL eCommerce Iberia avec Peek

[Peek](/) suit plus de 4 500 transporteurs. Pour deux lettres suivies de 7 chiffres et `0`, il interroge DHL eCommerce Iberia et TNT Italie, dont les bordereaux ont le même format. Pour 10 chiffres, il interroge DHL eCommerce Iberia, DHL Freight, [Packeta](carrier:packeta) et BOX NOW, ainsi que DHL Express en premier lorsque son chiffre de contrôle est valide (avec ACS Courier et [Geniki Taxydromiki](carrier:geniki-taxydromiki), qui passent le même contrôle) ; si DHL Express connaît aussi le numéro, c’est lui qui l’emporte. Si plusieurs marques connaissent le colis ou si aucune ne le connaît, choisis le transporteur via « Changer de transporteur ». Un numéro `JJD` du format ci-dessus identifie aussitôt DHL eCommerce Iberia (« Transporteur détecté »). Les autres numéros DHL (12 ou 22 chiffres, autres codes `JJD`, codes postaux) ne fonctionnent qu’après sélection manuelle de « DHL eCommerce Iberia ». Aucun code postal n’est requis.

Les liens d’envoi provenant de la page de suivi DHL eCommerce Iberia fonctionnent aussi, en espagnol, portugais ou anglais (`/LiveTracking/ModificarEnvio/`, `ModificarMeuEnvio` ou `ModifyMyShipment`). Ils identifient immédiatement DHL eCommerce Iberia quelle que soit la forme du numéro ; un code postal en fin de lien est ignoré puisqu’il n’est pas nécessaire.

Les scans DHL ne portent pas de fuseau horaire : Peek les lit sur l’heure locale du lieu du scan (heure d’Espagne en Espagne, heure du Portugal au Portugal) et les affiche dans ton fuseau horaire (« Heures dans ton fuseau horaire : » suivi de son nom). Il affiche le statut, le poids, le point de retrait ServicePoint pendant l’attente et après le retrait, et passe le relais vers [CTT Express](carrier:ctt-express) lorsque la réponse DHL contient un code CTT Express. Les libellés gardent l’anglais de DHL, sauf `Delivered` et `Out for delivery`, traduits par Peek. Aucune date de livraison prévue ni heure de livraison séparée n’est affichée pour DHL eCommerce Iberia.

Tant que le colis est ouvert dans Peek — ou le jour de la livraison si les notifications sont activées — Peek interroge directement DHL eCommerce Iberia jusqu’à toutes les 10 minutes, et jusqu’à toutes les 2 minutes dès 6 h à l’heure du dernier scan (heure de Madrid en Espagne) le jour où DHL scanne le colis « En livraison », tant que son suivi répond ; sinon, ainsi qu’après deux jours sans nouveauté (12 heures tant que le colis reste sur « Étiquette créée » ou « Pris en charge »), il vérifie toutes les heures. Pour les notifications, choisis « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Après quatre jours sans scan en route, hors douane ou problème signalé, Peek affiche la date du dernier scan (« Aucune mise à jour depuis … ») et indique « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger DHL eCommerce Iberia. », avec le lien « Demande de l’aide à DHL eCommerce Iberia » vers sa page de contact. Peek ne peut pas contacter DHL ni la boutique pour toi.

## Questions sur le suivi DHL eCommerce

### DHL eCommerce est-il identique à DHL Express ?

Non. Les bordereaux DHL Express ont leur propre suivi (la page MyDHL+), et Peek traite les deux comme des transporteurs distincts. Un numéro à 10 chiffres peut appartenir à l’un comme à l’autre : Peek interroge d’abord DHL Express lorsque son chiffre de contrôle est valide, et si DHL Express connaît le colis, il prime.

### Pourquoi mon colis DHL eCommerce n’affiche-t-il aucun suivi ?

Vérifie le numéro et le transporteur auprès de la boutique : plusieurs transporteurs partagent les numéros à 10 chiffres (voir le tableau ci-dessus). Un ancien envoi peut aussi disparaître du suivi DHL ; la durée de conservation de l’historique n’est pas publiée.

### CTT Express livre-t-il les colis DHL eCommerce ?

Une partie d’entre eux. Dans le cadre de la coentreprise signée en mai 2026 entre DHL eCommerce et CTT, CTT Express reprend les livraisons aux particuliers en Espagne, et l’activité de DHL eCommerce au Portugal passe chez CTT Expresso. Si le suivi te fournit un numéro CTT Express, suis-le avec [CTT Express](carrier:ctt-express).

:::sources
- [CTT: CTT e DHL Ecommerce selam parceria estratégica](https://www.ctt.pt/grupo-ctt/media/noticias/ctt-e-dhl-ecommerce-selam-parceria-estrategica-para-criar-rede-de-distribuicao-de-encomendas-mais-completa-da-peninsula-iberica) – coentreprise signée le 12 mai 2026, DHL eCommerce Portugal vers CTT Expresso, CTT Express pour les particuliers en Espagne, DHL pour les livraisons aux entreprises
- [Your Europe: Shipping and delivery](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_en.htm) – vendeur responsable des dommages jusqu’à la livraison, 30 jours, délai supplémentaire, remboursement
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – champs lus par Peek, libellés de statuts et phases, ServicePoint, liens d’envoi, ancien nom DHL Parcel Iberia, relais CTT Express et fréquence de vérification
:::
