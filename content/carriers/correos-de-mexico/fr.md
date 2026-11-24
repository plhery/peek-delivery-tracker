---
title: Suivi Correos de México : codes S10 MX, réclamations et aide
description: Suis un colis Correos de México (SEPOMEX / Mexpost) : numéros S10 MX, recherche par année, statuts espagnols, douane, réclamation sous 30 jours.
slug: suivi-correos-de-mexico
published: 2026-11-24
updated: 2026-11-24
---

Correos de México (*Servicio Postal Mexicano* / *SEPOMEX*) est le service postal national du Mexique, qui assure les courriers recommandés nationaux (*Registrados*), les colis express *Mexpost* ainsi que les envois postaux internationaux UPU, au départ du Mexique comme à l’arrivée depuis les postes du monde entier. Tu peux consulter un envoi sur [la page de suivi de Correos de México](https://www.correosdemexico.gob.mx/SSLServicios/SeguimientoEnvio/Seguimiento.aspx) (en saisissant à la fois le numéro de suivi et l’année de dépôt, *Ejercicio*), ou coller ton code dans le champ ci-dessus.

## Numéros de suivi Correos de México : formats UPU S10

Regarde ton reçu de guichet postal mexicain, ton bordereau Mexpost ou l’e-mail d’expédition du vendeur international. Correos de México suit des codes UPU S10 de 13 caractères — aussi bien les codes expédiés du Mexique terminés par `MX` que les envois postaux étrangers S10 à destination du Mexique (comme `...US`, `...CN`, `...ES` ou `...JP`) :

| Format (inventé) | De quoi il s’agit |
| --- | --- |
| `RR482917367MX` | Courrier recommandé (*Correo Registrado*) envoyé depuis le Mexique (UPU S10 terminé par `MX`). |
| `MP482917367MX` | Colis postal national ou express *Mexpost* (UPU S10 terminé par `MX`). |
| `EE482917367MX` | Courrier express international EMS depuis le Mexique (UPU S10 terminé par `MX`). |

**Sélecteur d’année (*Ejercicio*) sur SEPOMEX.** Contrairement à la plupart des outils de suivi, [le portail Seguimiento de Correos de México](https://www.correosdemexico.gob.mx/SSLServicios/SeguimientoEnvio/Seguimiento.aspx) classe les scans par année civile (`2026`, `2025`, `2024`, etc.). Si un colis expédié fin décembre poursuit son trajet en janvier, il peut être nécessaire de consulter les deux années sur le site pour voir tout l’historique. Pour les transporteurs privés mexicains tels qu’[Estafeta](carrier:estafeta), [J&T Express Mexico](carrier:j-and-t-mx) ou [Paquetexpress](carrier:paquetexpress), consulte [les formats de numéros de suivi](guide:tracking-number-formats).

## Ce que signifient les statuts de suivi Correos de México

Correos de México affiche des descriptions d’événements en espagnol accompagnées du bureau de poste mexicain ou du bureau d’échange étranger ainsi que de l’horodatage :

| Correos de México indique | Ce que cela signifie pour toi | Peek affiche |
| --- | --- | --- |
| `Depósito en Oficina de Correos` / `Deposito del Cliente en ...` | Déposé au guichet d’un bureau de poste mexicain ou pris en charge à l’étranger par l’opérateur postal d’origine. | « Pris en charge » |
| `En tránsito hacia destino`, `Recepción en Oficina de Correos`, `En zona de intercambio int. hacia el país destino, previo Aduana`, `Pieza depositada en valija y enviada a oficina del País destino` | En transit entre bureaux de distribution mexicains ou ensaché et expédié à l’international. | « En transit » |
| `Arribo a Zona Intercambio México, en espera de ingreso a la Aduana`, `Inicio de la revisión aduanal en país destino` | Arrivé au bureau d’échange international en attente ou en cours de contrôle douanier. | « À la douane » |
| `Revisión aduanal concluida` | Contrôle douanier terminé ; envoi libéré dans le circuit postal. | « En transit » |
| `Con mensajero para entrega` | En tournée avec le facteur local pour livraison aujourd’hui. | « En livraison » |
| `Puesta en ventanilla` | Mis à disposition au guichet du bureau de poste local indiqué pour retrait par le destinataire. | « Prêt à être retiré » |
| `Pieza devuelta ...` (ex. `por domicilio destino incorrecto`) | Renvoyé vers l’expéditeur avec le motif indiqué. | « Retourné à l’expéditeur » |
| `Entrega` / `Entrega Internacional` | Livré au Mexique ou confirmé livré à l’étranger par la poste de destination. | « Livré » |

## Retrait au guichet (*Puesta en ventanilla*), douane et réclamations

D’après [Servicio Postal Mexicano sur gob.mx](https://www.gob.mx/correosdemexico) et la page officielle [Reclamo de un Envío (Réclamation d’envoi)](https://www.gob.mx/correosdemexico/es/acciones-y-programas/reclamo-de-un-envio) :

- **Retrait au guichet (*Puesta en ventanilla*) :** lorsque le suivi indique `Puesta en ventanilla`, ton envoi t’attend au bureau de poste nommé dans la colonne *Origen*.
- **Douane à l’importation :** les colis postaux entrants au Mexique passent par l’inspection douanière mexicaine (`Inicio de la revisión aduanal...`). Les délais varient selon la saison et l’évaluation douanière ; voir [les colis retenus en douane](guide:customs).
- **Déposer une réclamation officielle (*Reclamo de un Envío*) :** les demandes d’enquête et d’indemnisation s’appliquent **uniquement aux services Recommandés (*Registrados*) et *Mexpost*** (pas au courrier ordinaire non suivi). Tu peux déposer une réclamation lorsqu’un envoi dépasse son délai ou arrive en mauvais état — en personne dans n’importe quel bureau de Correos de México ou en ligne. Le délai maximal de réponse de SEPOMEX pour les réclamations Registrados et Mexpost est de **30 jours ouvrés** (*30 días hábiles*).

## Colis Correos de México bloqué, silencieux ou retourné

- **Longues pauses en douane ou en transit international :** les colis postaux entrant ou sortant du Mexique restent couramment sans scan pendant des jours ou des semaines sur `Arribo a Zona Intercambio México` ou entre `Pieza depositada en valija` et l’arrivée à destination. Voir [pourquoi le suivi ne se met plus à jour](guide:tracking-not-updating).
- **Envois retournés (`Pieza devuelta`) :** une fois que SEPOMEX inscrit `Pieza devuelta` (par exemple adresse erronée ou non retiré au guichet), les scans ultérieurs `Puesta en ventanilla` et `Entrega` concernent la restitution à l’expéditeur d’origine.
- Pour un achat en ligne, contacte aussi le vendeur en parallèle d’une réclamation. Voir [colis livré mais non reçu](guide:delivered-not-received), [colis endommagé](guide:damaged-parcel), [colis perdu](guide:lost-parcel) et [retourné à l’expéditeur](guide:returned-to-sender).

## Service client et contacts Correos de México

Canaux officiels publiés sur [gob.mx/correosdemexico](https://www.gob.mx/correosdemexico), [Seguimiento.aspx](https://www.correosdemexico.gob.mx/SSLServicios/SeguimientoEnvio/Seguimiento.aspx) et [Reclamo de un Envío](https://www.gob.mx/correosdemexico/es/acciones-y-programas/reclamo-de-un-envio) :

- **E-mail officiel de renseignement :** `contacto@correosdemexico.gob.mx`.
- **Réclamations et demandes :** [Reclamo de un Envío](https://www.gob.mx/correosdemexico/es/acciones-y-programas/reclamo-de-un-envio) ou au guichet de ton bureau de poste mexicain.
- **Litiges consommateurs au Mexique :** pour un litige commercial non résolu, les consommateurs peuvent s’adresser à [PROFECO](https://www.gob.mx/profeco).

> Méfie-toi des faux SMS ou messages WhatsApp usurpant Correos de México / SEPOMEX pour exiger un paiement par carte en ligne afin de « corriger ton code postal » ou « libérer un colis ». Vérifie toujours ton code S10 directement sur [correosdemexico.gob.mx](https://www.correosdemexico.gob.mx/SSLServicios/SeguimientoEnvio/Seguimiento.aspx) ou écris à `contacto@correosdemexico.gob.mx`. Voir [les arnaques aux SMS de livraison](guide:delivery-scam-texts).

## Suivre un colis Correos de México avec Peek

[Peek](/) reconnaît directement comme Correos de México les liens collés `correosdemexico.gob.mx/SSLServicios/SeguimientoEnvio/Seguimiento.aspx?guia=`. Lorsque tu colles un code UPU S10 de 13 caractères terminé par `MX` (`RR482917367MX`, `MP482917367MX` ou `EE482917367MX`, inventés), Peek interroge d’abord Correos de México et affiche « Correos de México a ce colis. » dès qu’il le confirme. Un envoi posté à l’étranger conserve le code de sa poste d’origine (en `US`, `CN`, etc.), que Peek interroge en premier ; pour le suivre sur Correos de México, appuie sur « Changer » lors de l’ajout ou sur « Changer de transporteur » sur l’écran du colis et choisis Correos de México (rechercher « SEPOMEX », « Mexpost » ou « Mexico Post » le trouve aussi).

Peek lit directement le suivi de Correos de México en remontant les années antérieures proposées par le formulaire afin qu’un envoi à cheval sur le nouvel an reste complet, et affiche l’étape (« Pris en charge », « En transit », « À la douane », « En livraison », « Prêt à être retiré », « Retourné à l’expéditeur », « Livré »), le libellé espagnol du scan, le bureau de poste ou bureau d’échange, le bureau où l’envoi attend lorsqu’il indique `Puesta en ventanilla`, ainsi que l’heure de livraison lorsqu’elle existe. Après `Pieza devuelta`, Peek laisse les scans de guichet et de remise suivants sous « Retourné à l’expéditeur », car ils concernent le retour à l’expéditeur et non une livraison chez toi. Correos de México donne les heures de scan sans fuseau : Peek date chaque scan sur le fuseau horaire de son bureau (celui de Tijuana pour un scan à Tijuana, celui de Mexico pour Mexico) et l’affiche à l’heure de ton appareil. Peek omet le nom du signataire, l’heure de requête et les bandeaux informatifs de la page ; le suivi ne fournit ni date de livraison estimée, ni poids, ni nom de service, ni photo de preuve de livraison.

Tant que le colis est ouvert dans Peek, ou le jour de sa livraison si les notifications sont activées, Peek vérifie jusqu’à toutes les 10 minutes ; le jour où Correos de México affiche `Con mensajero para entrega`, toutes les 2 minutes à partir de 6 h sur l’horloge du bureau du dernier scan, tant que le suivi de Correos de México répond. Sinon, Peek vérifie toutes les heures. Après 12 heures avec uniquement « Étiquette créée » ou « Pris en charge », ou deux jours sans nouvelle, il vérifie toutes les heures quelle que soit ta façon de le suivre, puis toutes les 6 heures après sept jours à « Étiquette créée ». Quand le suivi de Correos de México ne répond pas et que Peek suit le colis uniquement via des services de suivi, il vérifie toutes les heures, toutes les 3 heures après une journée calme, et jusqu’à toutes les 15 minutes le jour de la livraison tant que le colis est ouvert ou que les notifications sont activées.

Quand le scan le plus récent remonte à quatre jours et que le colis reste sur « Étiquette créée », « Pris en charge » ou « En transit », Peek le signale avec le jour de ce scan, par exemple « Aucune mise à jour depuis lun. 12 oct. ». La page du colis indique alors « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger Correos de México. », avec un lien « Demande de l’aide à Correos de México » vers la page *Reclamo de un Envío*. Après trois semaines sans nouveau scan, elle affiche « Rien de nouveau depuis plus de trois semaines. Si le colis n’est toujours pas arrivé, contacte le vendeur. Peek continue d’interroger Correos de México. »

Tu peux choisir entre les réglages d’alertes « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Peek se limite au suivi des mises à jour et ne peut ni dédouaner un colis ni déposer un *Reclamo de un Envío* auprès de SEPOMEX à ta place.

## Questions fréquentes sur le suivi Correos de México

### Quels numéros de suivi fonctionnent avec Correos de México ?

Correos de México suit les codes postaux UPU S10 de 13 caractères : 2 lettres + 9 chiffres + 2 lettres pays (par exemple `RR482917367MX` ou `MP482917367MX`, inventés), y compris les envois S10 étrangers entrant au Mexique.

### Que signifie `Puesta en ventanilla` ?

Cela signifie que ton colis attend au guichet du bureau de poste local mentionné dans la colonne *Origen* du scan pour un retrait en personne.

### Pourquoi Correos de México n’a-t-il pas trouvé mon numéro de suivi ?

Sur [Seguimiento.aspx](https://www.correosdemexico.gob.mx/SSLServicios/SeguimientoEnvio/Seguimiento.aspx), vérifie que tu as sélectionné la bonne année de dépôt (*Ejercicio*) — en particulier pour un envoi expédié en fin d’année précédente — et note que le courrier ordinaire non suivi ne comporte pas de suivi.

### Comment déposer une réclamation auprès de Correos de México ?

Pour les envois Recommandés (*Registrados*) et *Mexpost* en retard ou endommagés, dépose un *Reclamo de un Envío* dans un bureau de poste ou via [gob.mx/correosdemexico/es/acciones-y-programas/reclamo-de-un-envio](https://www.gob.mx/correosdemexico/es/acciones-y-programas/reclamo-de-un-envio) (délai de réponse de 30 jours ouvrés) ou écris à `contacto@correosdemexico.gob.mx`.

:::sources
- [Correos de México (SEPOMEX) : portail gob.mx](https://www.gob.mx/correosdemexico) – portail officiel Servicio Postal Mexicano et `contacto@correosdemexico.gob.mx`
- [Correos de México : Seguimiento de Envíos](https://www.correosdemexico.gob.mx/SSLServicios/SeguimientoEnvio/Seguimiento.aspx) – suivi S10 par année (*Ejercicio*) et `contacto@correosdemexico.gob.mx`
- [Correos de México : Reclamo de un Envío](https://www.gob.mx/correosdemexico/es/acciones-y-programas/reclamo-de-un-envio) – éligibilité Registrados & Mexpost, dépôt au guichet/en ligne et délai de réponse de 30 jours ouvrés (URL d’aide liée par Peek)
- [PROFECO](https://www.gob.mx/profeco) – Agence fédérale mexicaine de protection des consommateurs
- [Universal Parcel Scraper : correos-de-mexico](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/correos-de-mexico) – format S10, règle de lien `Seguimiento.aspx?guia=`, recherche multi-années, table de statuts espagnols, point de retrait `Puesta en ventanilla`, gestion du retour, fuseau horaire du bureau et URL d’aide
:::
