---
title: Suivi TIPSA : numéro à 22 chiffres, statuts et livraisons
description: Suivre un colis TIPSA en Espagne ou au Portugal : le numéro à 22 chiffres, les statuts en espagnol, livraisons manquées, agences et contacts.
slug: suivi-tipsa
published: 2026-11-21
updated: 2026-11-21
---

TIPSA est un transporteur express espagnol de colis, intégré au réseau Geopost, qui dispose d'agences en Espagne, au Portugal et en Andorre. Suis ton colis grâce à son numéro à 22 chiffres sur le [localisateur d'envois TIPSA](https://www.tip-sa.com/en/delivery-locator), ou colle-le dans le champ ci-dessus. Le numéro provient de la boutique ou de l'expéditeur, généralement dans l'e-mail ou le SMS d'expédition. Si tu ne l'as pas, demande-le au vendeur ou à l'agence TIPSA qui dessert ton secteur, repérable par code postal sur le [chercheur d'agences](https://www.tip-sa.com/es/agencias-de-transporte). C'est aussi avec cette agence que tu organises une nouvelle présentation après un statut `Ausente` ou un retrait au guichet ; en cas de perte ou de dommage, préviens la boutique.

## Numéro de suivi TIPSA : à quoi il ressemble

Le localisateur de TIPSA demande le numéro d'envoi à 22 chiffres et indique également le code postal de destination comme champ requis. Ce numéro associe deux codes d'agence suivis du bordereau (`albarán`) :

:::anatomy 028145 028145 4829105736
- 028145 | Premier code d'agence : une agence TIPSA intervenant sur l'envoi
- 028145 | Second code d'agence : une autre agence de l'envoi
- 4829105736 | Bordereau (albarán) : le numéro propre à l'envoi
:::

| Forme (inventée) | Ce que c'est |
| --- | --- |
| `0281450281454829105736` | Le numéro TIPSA complet à 22 chiffres. C'est celui à utiliser. |
| `028145 028145 4829105736` | Le même numéro avec espaces. Peek l'accepte ; le champ du localisateur TIPSA étant limité à 22 caractères, saisis-le là-bas sans espaces. |
| `4829105736` | Le seul bordereau à 10 chiffres. Le localisateur TIPSA ne l'accepte pas, et Peek ne le reconnaît pas comme un numéro TIPSA. |

Les boutiques peuvent faire envoyer par TIPSA des e-mails et SMS au départ, à l'arrivée en agence, en cas d'incident et après la livraison, avec des liens vers la page d'envoi TIPSA. Sans le numéro à 22 chiffres, le localisateur permet aussi de chercher par référence d'envoi, mais demande un identifiant (`Agency + Client`) à obtenir auprès de l'expéditeur. Autres pistes dans [comment trouver ton numéro de suivi](guide:find-tracking-number).

Les numéros à 22 chiffres correspondent aussi à Austrian Post, [USPS](carrier:usps) et Estafeta. Un numéro commençant par `00` dont le second code d'agence débute également par `00` peut correspondre à [CTT Express](carrier:ctt-express), un autre transporteur espagnol : vérifie quel transporteur figure dans l'e-mail de la boutique. Seule la forme complète à 22 chiffres fonctionne, jamais les seuls 10 chiffres du bordereau.

## Signification des statuts de suivi TIPSA

La page de détail de TIPSA est uniquement en espagnol, même lorsqu'elle est ouverte depuis la version anglaise du site. Elle présente en haut une frise résumée (`Documentado`, `En camino`, `En reparto`, `Entregado`), suivie d'un tableau listant chaque scan avec date, heure et localité. Voici les libellés dans leur ordre habituel :

| Statut TIPSA | Ce que cela signifie pour toi |
| --- | --- |
| `PENDIENTE DE ENTREGAR A TIPSA` | La boutique a enregistré l'envoi, mais TIPSA n'a pas encore le colis : c'est un statut d'[étiquette créée](guide:tracking-statuses). |
| `TRANSITO` | En acheminement entre agences et plateformes TIPSA. |
| `LECTURA EN HUB MADRID` | Scanné dans une plateforme TIPSA ; le nom varie selon le hub (`LECTURA EN HUB BARCELONA`, `LECTURA EN HUB BARBERA`). |
| `Pendiente de llegada` | Pas encore arrivé à l'agence qui assurera la livraison. |
| `LEIDO EN DESTINO` | Scanné à destination. |
| `LECTURA EN AGENCIA DESTINO` | Scanné par l'agence qui livre, suivi du nom de l'agence. |
| `REPARTO` | En cours de livraison. |
| `Ausente` | Le livreur n'a pas pu remettre le colis faute de destinataire joignable. |
| `Faltan Datos / Datos incorrectos` | Informations d'adresse manquantes ou erronées, relevées durant la tournée avant une nouvelle tentative. |
| `Cerrado` | Adresse trouvée fermée lors du passage, après `REPARTO`. |
| `Solicitada en otra dirección` | Une livraison à une autre adresse a été demandée et le colis est réacheminé. |
| `DISPONIBLE` ou `Pendiente de recoger en Tipsa` | Après un échec de livraison, le colis attend ton retrait à l'agence. |
| `Devolver a Origen` | Retour vers l'expéditeur. |
| `ENTREGADO` | Livré. |

Le statut `Ausente` s'accompagne d'une note de TIPSA indiquant que le livreur n'a pas pu te joindre, qu'une nouvelle tentative aura lieu le jour ouvré suivant et que tu peux appeler l'agence de destination pour convenir de la livraison. Le nom et l'adresse de cette agence figurent sous « AGENCIA DESTINO ».

## Livraison manquée, agences et points relais TIPSA

TIPSA livre via son propre réseau en Espagne péninsulaire et au Portugal, aux îles Baléares et Canaries, à Ceuta et Melilla, et à l'international via DPDgroup. Après un `Ausente`, une seconde tentative a lieu le jour ouvré suivant ; le tarif vendu aux boutiques comprend deux tentatives, ou trois avec le service Premium destiné aux boutiques en ligne.

**Retrait en agence.** Si tu es absent ou préfères retirer le colis toi-même, la FAQ de TIPSA indique qu'il reste disponible à l'agence la plus proche de ton code postal pendant 15 jours à compter de l'expédition, et non de son arrivée. Passé ce délai, il repart chez l'expéditeur. Présente ton DNI ou ton passeport ; si un tiers vient à ta place, il doit fournir une photocopie de ta pièce d'identité. Le [chercheur d'agences](https://www.tip-sa.com/es/agencias-de-transporte) permet de chercher par province ou code postal.

**Changement d'adresse.** Seul l'expéditeur peut autoriser un changement d'adresse ou de coordonnées d'envoi selon TIPSA : contacte donc la boutique.

**Points relais et consignes.** TIPSA partage avec [SEUR](carrier:seur) un réseau de points Pickup en commerces et de consignes automatiques accessibles 24 h/24 et 7 j/7. La plupart des points ouvrent au moins six jours par semaine, et tu disposes de 7 jours pour retirer le colis. La livraison en point relais dépend des options proposées par la boutique. Trouve un point sur le [localisateur Pickup](https://aplicaciones.tip-sa.com/puntospickup/).

Certaines boutiques souscrivent aussi la livraison le samedi ou la pré-alerte (SMS et e-mail annonçant le jour d'arrivée à destination). Avec le service Premium, il est aussi possible de choisir un créneau horaire avec une marge d'une heure ; TIPSA ne précisant pas sur son site où effectuer ce choix, renseigne-toi auprès de l'agence de destination.

**Paiement à la livraison.** En contre-remboursement (`contrarreembolso`), TIPSA encaisse à la livraison le montant fixé par la boutique, jusqu'à 1 000 € par expédition ; en port dû (`portes debidos`), elle encaisse les frais de port à destination. TIPSA ne précise pas si le règlement se fait en espèces ou par carte : demande à la boutique ou à l'agence. Comme ces deux encaissements s'effectuent à la remise du colis, un SMS ou un e-mail « de TIPSA » réclamant un paiement via un lien ne correspond ni à l'un ni à l'autre : ne paie pas et vérifie ton numéro sur le localisateur de tip-sa.com. Voir [SMS frauduleux de livraison](guide:delivery-scam-texts).

## Colis TIPSA en retard, perdu ou retourné

Les pages de TIPSA n'expliquent pas la marche à suivre lorsqu'un suivi reste figé. Appelle l'agence de destination indiquée sur la page de suivi ou contacte la boutique ; [pourquoi le suivi ne se met plus à jour](guide:tracking-not-updating) aide à savoir quand s'inquiéter.

En cas de perte ou de dommage, la FAQ de TIPSA est brève : préviens l'expéditeur. C'est la boutique qui détient le contrat avec TIPSA et dépose la réclamation ; TIPSA ne publie ni procédure ni délai de réclamation pour le destinataire. TIPSA ne dispose pas non plus de page consacrée à un colis marqué `ENTREGADO` mais non reçu : signale-le aussi à la boutique.

Un colis non retiré dans les 15 jours suivant l'expédition retourne à l'expéditeur : contacte la boutique pour un remboursement ou un nouvel envoi.

## Contact et service client TIPSA

Voici les canaux publiés sur la page de contact et la FAQ de TIPSA :

- **Téléphone :** `902 10 10 47` ou `900 10 10 47` (la FAQ espagnole mentionne uniquement le `900 10 10 47`). TIPSA n'indique ni horaires d'ouverture ni tarif d'appel.
- **Formulaire de contact** sur [tip-sa.com/en/contact](https://www.tip-sa.com/en/contact), avec le motif « I have a problem with my shipment » et ton numéro d'envoi.
- **E-mail :** `info@tip-sa.com`, indiqué dans la FAQ en cas de numéro d'envoi manquant.
- **L'agence de destination**, nommée sur la page de suivi, pour organiser la livraison.

Il n'existe pas de numéro distinct pour le Portugal : la page de contact portugaise de TIPSA mentionne les mêmes deux numéros. TIPSA dispose aussi d'une page dédiée à son application mobile sur [tip-sa.com/en/recursos/app](https://www.tip-sa.com/en/recursos/app).

## Suivre un colis TIPSA avec Peek

[Peek](/) accepte le numéro à 22 chiffres avec ou sans espaces. Comme d'autres transporteurs partagent cette longueur, Peek interroge simultanément les transporteurs plausibles, TIPSA en premier : tu vois « Peek demande à … » puis « TIPSA a ce colis. » lorsque TIPSA le reconnaît. Un numéro commençant par `00` dont le second code d'agence commence par `00` et correspond à l'un des codes d'agence listés pour CTT Express est attribué directement à CTT Express ; pour les autres combinaisons `00…00…`, Peek interroge TIPSA (en priorité), Austrian Post, CTT Express, USPS et Estafeta. Si la boutique t'a indiqué TIPSA, appuie sur « Changer » pour le sélectionner. Aucun code postal n'est requis.

Le bordereau à 10 chiffres ne peut pas être suivi via TIPSA, même après « Changer » ou via un lien. Peek ne propose pas TIPSA pour 10 chiffres ; si tu choisis TIPSA manuellement, Peek indique que TIPSA « n’émet pas de numéros comme celui-ci » et interroge des services de suivi. Recherche le numéro complet à 22 chiffres ou le lien d'envoi dans l'e-mail ou le SMS de la boutique. Peek lit les liens `tip-sa.com` portant `id=` sur les pages `datos_env.php`, `datos_prestashop.php`, `datos.php` ou la racine de `tip-sa.com`, mais pas les liens du localisateur ni ceux de `tipsa-dinapaq.com`.

Peek affiche le statut, l'historique avec la localité de chaque scan sur une carte, le nom de l'expéditeur (sauf s'il est masqué par TIPSA) et le poids. Les statuts restent dans l'espagnol d'origine de TIPSA, résumés par Peek selon ses étapes : `PENDIENTE DE ENTREGAR A TIPSA` (« Étiquette créée »), `REPARTO` (« En livraison », y compris un scan d'agence enregistré le même jour après `REPARTO`), `Ausente`, `Cerrado` et `Faltan Datos / Datos incorrectos` (« Tentative de livraison »), `DISPONIBLE` et `Pendiente de recoger en Tipsa` (« Prêt à être retiré »), `Devolver a Origen` (« Problème signalé »), `ENTREGADO` (« Livré »), et les autres scans (« En transit »). Peek lit toutes les heures sur l'heure de Madrid, agences portugaises comprises, et les affiche dans ton fuseau horaire ; sur peektracker.com et dans les applications iPhone et Android, l'historique se termine par « Heures dans ton fuseau horaire : » et son nom. Il laisse de côté le destinataire, l'adresse, les codes postaux, la référence, le nombre de colis, le volume et la preuve de livraison, n'affiche pas de date de livraison prévue et ne suit pas un transfert vers un autre transporteur.

Tant que tu gardes le colis ouvert à l'écran ou le jour de la livraison avec les notifications activées, et que le suivi de TIPSA répond, Peek vérifie jusqu'à toutes les 10 minutes, et jusqu'à toutes les 2 minutes dès 6 h (heure de Madrid) le jour où TIPSA scanne le colis en `REPARTO` ; sinon, toutes les heures. Lorsque ce sont des services de suivi qui répondent, Peek vérifie toutes les heures, et jusqu'à toutes les 15 minutes le jour de la livraison tant que le colis est ouvert ou que les notifications sont activées. Après deux jours sans nouvelle (12 heures tant que le colis affiche « Étiquette créée » ou « Pris en charge »), Peek vérifie toutes les heures. Tu peux activer des notifications pour « Chaque scan », « Étapes importantes » ou « Livraison seulement ».

## Questions sur le suivi TIPSA

### Qu'est-ce que TIPSA en Espagne ?

TIPSA désigne Transporte Integral de Paquetería, S.A., une société espagnole de transport express membre du groupe Geopost. Sa page de présentation mentionne plus de 330 agences (plus de 300 sur la page d'accueil portugaise) et 13 plateformes, dont Madrid, Barcelone et Valence, avec des envois internationaux assurés via DPDgroup.

### Quels sont les délais de livraison de TIPSA ?

Ils dépendent du service choisi par la boutique. TIPSA propose aux boutiques une livraison en 24 heures (avec présentation le jour ouvré suivant) ou en 48 heures ; au Portugal, 24 heures vers les principales villes et 24 à 48 heures ailleurs.

### Peut-on suivre un colis TIPSA avec 10 chiffres ?

Non : le localisateur de TIPSA exige le numéro à 22 chiffres, et Peek n'accepte pas non plus 10 chiffres comme numéro TIPSA. Les 10 chiffres ne sont que le bordereau final : demande le numéro complet à l'expéditeur.

### TIPSA dispose-t-il de points relais ?

Oui, des points Pickup en commerces et en consignes, partagés avec SEUR, où le colis reste 7 jours. Cette option n'est disponible que si la boutique propose la livraison en point relais.

:::sources
- [TIPSA : Delivery locator](https://www.tip-sa.com/en/delivery-locator) – numéro à 22 chiffres, formulaire, recherche par référence avec identifiant (agence + client)
- [TIPSA : Frequently Asked Questions](https://www.tip-sa.com/en/recursos/faqs) – numéro manquant, garde de 15 jours, pièce d'identité, changement d'adresse, perte ou dommage, téléphones, e-mail
- [TIPSA : Contact](https://www.tip-sa.com/en/contact) – téléphones, motifs du formulaire
- [TIPSA : Collection points](https://www.tip-sa.com/en/services/collection-points) – réseau Pickup
- [TIPSA : Pre-alert](https://www.tip-sa.com/en/services/pre-alert) – avis SMS et e-mail, délais au Portugal
- [TIPSA : TIPSA APP](https://www.tip-sa.com/en/recursos/app) – page de l'application
- [TIPSA : Consulta de Envíos](https://dinapaqweb.tipsa-dinapaq.com/dinapaqweb/detalle_envio.php) – page d'envoi uniquement en espagnol, libellés et agence de destination
- [TIPSA : Localizar mi envío](https://www.tip-sa.com/es/localizacion-envios) – localisateur espagnol, téléphone en pied de page
- [TIPSA : Preguntas frecuentes](https://www.tip-sa.com/es/recursos/faqs) – couverture, numéro `900 10 10 47`
- [TIPSA : Empresa de transporte urgente](https://www.tip-sa.com/es/tipsa) – Geopost, plus de 330 agences, 13 plateformes, consignes
- [TIPSA : Página inicial (Portugal)](https://www.tip-sa.com/pt) – plus de 300 agences
- [TIPSA : FAQs eTIPSA](https://www.tip-sa.com/es/faqs-etipsa) – deux tentatives, 24 h et 48 h, créneau horaire eTIPSA Premium, samedi, notifications boutiques
- [TIPSA : 360º eCommerce](https://www.tip-sa.com/es/envios-de-paquetes/360o-ecommerce) – trois tentatives avec Premium
- [TIPSA : Recogida en puntos de recogida](https://www.tip-sa.com/es/envios-de-paquetes/puntos-de-recogidas) – réseau partagé avec SEUR, 7 jours, jours d'ouverture
- [TIPSA : Agencias de transporte](https://www.tip-sa.com/es/agencias-de-transporte) – chercheur d'agences
- [TIPSA : Puntos Pickup](https://aplicaciones.tip-sa.com/puntospickup/) – localisateur de points Pickup
- [TIPSA : Contrarreembolso](https://www.tip-sa.com/es/envios-de-paquetes/reembolso) – encaissement à la livraison, jusqu'à 1 000 € par expédition
- [TIPSA : Portes debidos](https://www.tip-sa.com/es/envios-de-paquetes/portes-debidos) – frais de port encaissés à destination
- [TIPSA : Aviso legal](https://www.tip-sa.com/es/tipsa-aviso-legal) – dénomination sociale
- [TIPSA : Contacto (Portugal)](https://www.tip-sa.com/pt/contacto) – mêmes numéros pour le Portugal
- [Universal Parcel Scraper : TIPSA](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/tipsa) – format 22 chiffres, liens lus, fuseau Madrid, correspondance des statuts (`Cerrado`, `Devolver a Origen`, scan d'agence après `REPARTO`), champs affichés et écartés
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – chevauchement avec CTT Express, Austrian Post, USPS et Estafeta
:::
