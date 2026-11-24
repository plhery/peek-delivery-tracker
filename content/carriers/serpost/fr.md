---
title: Suivi Serpost : codes S10 du Pérou, douane SUNAT et aide
description: Suis un colis Serpost (Servicios Postales del Perú) : codes S10 en PE à 13 caractères, scans douane SUNAT, retrait en bureau, réclamations et Lima.
slug: suivi-serpost
published: 2026-11-24
updated: 2026-11-24
---

**Serpost** (*Servicios Postales del Perú S.A.*) est l’opérateur postal public national du Pérou, rattaché au ministère des Transports et des Communications (MTC) ([gob.pe/serpost](https://www.gob.pe/serpost)). Chargé du courrier et des colis domestiques au Pérou ainsi que des envois internationaux sortants (notamment [EMS](carrier:ems) et *Exporta Fácil*) et du courrier entrant de l’Union postale universelle (UPU) venu de l’étranger, Serpost publie son suivi en ligne sur [serpost.com.pe/Cliente/SegumientoLinea](https://www.serpost.com.pe/Cliente/SegumientoLinea). Tu peux y vérifier un envoi postal S10 péruvien ou entrant, ou coller ton code de 13 caractères dans le champ ci-dessus.

## Numéros de suivi Serpost (format UPU S10 à 13 caractères)

Serpost suit les codes-barres standard UPU S10 à 13 caractères : deux lettres de service, 8 chiffres de série, 1 chiffre de contrôle S10 et un code pays d’origine à 2 lettres (`PE` pour les envois expédiés du Pérou, ou le code du pays étranger comme `US`, `CN`, `ES` ou `JP` pour les colis internationaux arrivant au Pérou) :

| Format (inventé) | Ce que c’est |
| --- | --- |
| `RR482917367PE` | Courrier recommandé de 13 caractères expédié du Pérou (`RR` + 9 chiffres avec clé de contrôle S10 valide + `PE`). |
| `EE739184627PE` | Colis express international [EMS](carrier:ems) de 13 caractères expédié du Pérou (`EE` + 9 chiffres avec clé de contrôle S10 valide + `PE`). |

Les lettres économiques sans code-barres S10 à 13 caractères n’apparaissent pas dans le suivi en ligne de Serpost. Pour un colis international entrant venant de l’étranger (par exemple terminé par `CN`, `US` ou `GB`), dès que l’envoi atteint le Pérou, sélectionner Serpost affiche les scans locaux de la douane péruvienne (*Aduana*) et du bureau de poste sous ce même code S10 à 13 caractères. Voir le guide des [formats de numéros de suivi](guide:tracking-number-formats).

## Signification des statuts de suivi Serpost

Serpost publie des descriptions d’étapes en espagnol précisant l’administration postale péruvienne (*Administración Postal*) où chaque scan a été enregistré :

| Libellé Serpost | Ce que cela signifie pour toi | Peek affiche |
| --- | --- | --- |
| `ENVÍO ADMITIDO EN ...` | Déposé au guichet du bureau Serpost péruvien indiqué. | « Pris en charge » |
| `ENVÍO LLEGÓ A SERPOST`, `ENVÍO EN PROCESAMIENTO`, `ENVÍO EN TRÁNSITO` | Colis international entrant réceptionné par Serpost au Pérou, en tri au siège ou en acheminement national. | « En transit » |
| `ENVÍO SUJETO A TRÁMITE ANTE ADUANA` | Sélectionné par la douane péruvienne (SUNAT) pour dédouanement, évaluation ou contrôle fiscal. | « À la douane » |
| `ENVÍO SIN OBSERVACIÓN POR LA ADUANA` | Passé en douane péruvienne sans observation et restitué à Serpost. | « En transit » |
| `ENVÍO SALIÓ DE PERÚ`, `ENVÍO RUMBO AL PAÍS DE DESTINO` | Envoi international sortant ayant quitté le Pérou vers la poste du pays de destination. | « En transit » |
| `ENVÍO LLEGÓ A LA ADMINISTRACIÓN POSTAL ...` | Arrivé au bureau de poste local péruvien chargé de préparer la tournée. | « En transit » |
| `ENVÍO EN PROCESO DE DISTRIBUCIÓN`, `SALIÓ A DISTRIBUCIÓN` | En tournée avec un facteur Serpost pour livraison à domicile. | « En livraison » |
| `ENVÍO DISPONIBLE PARA ENTREGA O RECOJO` | Disponible pour retrait au bureau Serpost assigné. | « Prêt à être retiré » |
| `INTENTO FALLIDO DE ENTREGA`, `SE INTENTÓ ENTREGAR PERO FUE IMPOSIBLE` | La livraison n’a pas pu aboutir (destinataire absent ou problème d’accès) ; vérifie si un avis de retrait en bureau a été laissé. | « Tentative de livraison » |
| `ENTREGADO`, `ENVÍO ENTREGADO`, `FUE ENTREGADO` | Livré au destinataire ou retiré au bureau de poste. | « Livré » |

## Délais, douane et délais officiels de réclamation

Selon [Services d’envoi Serpost](https://www.gob.pe/institucion/serpost/pages/20800-enviar-cartas-dentro-y-fuera-del-pais-con-serpost) et [Réclamations et demandes Serpost](https://www.gob.pe/institucion/serpost/pages/33693-reclamos-y-consultas-servicios-postales-del-peru) :

- **Délais publiés pour les lettres :** **Expreso** national **48–72 heures max** ; **Estándar** national **5–7 jours ouvrables** ; **Económico** national **7–10 jours ouvrables** ; **Expreso** international **7–10 jours ouvrables** ; **Económico** international **10–30 jours ouvrables**. Seules les lettres Expreso et Estándar disposent d’un suivi en ligne.
- **Douane péruvienne (`ENVÍO SUJETO A TRÁMITE ANTE ADUANA`) :** l’envoi est retenu pour formalités douanières et n’est pas mis en livraison tant que la douane ne le libère pas (`ENVÍO SIN OBSERVACIÓN POR LA ADUANA`). Le suivi Serpost peut afficher un avis d’arrivée (*Aviso de llegada*). Voir le guide sur les [colis retenus en douane](guide:customs).
- **Fenêtres et délais officiels de réclamation :** pour un envoi **EMS**, la réclamation est recevable du lendemain du délai de livraison jusqu’à **4 mois** après le dépôt (réponse sous **30 jours calendaires**) ; les réclamations sur le courrier recommandé international sont acceptées après **20 jours calendaires** et sur les colis internationaux après **30 jours calendaires** ; les réclamations nationales reçoivent une réponse sous **16 jours ouvrables** (indemnisation nationale égale à **3× l’affranchissement payé** ; l’indemnisation internationale suit les règles de l’UPU).

## Colis Serpost retardé, en douane ou manquant

- **Silence sur le courrier international :** les colis sortants figés après `ENVÍO SALIÓ DE PERÚ` ou les envois entrants venus de l’étranger patientent souvent plusieurs jours ou semaines entre l’expédition aérienne et l’entrée en douane. Une fois qu’un envoi `...PE` sortant quitte le Pérou, suis-le auprès de la poste nationale de destination (comme [USPS](carrier:usps)). Voir le guide sur le [suivi bloqué](guide:tracking-not-updating).
- **Déposer une réclamation Serpost (*reclamo*) :** envoie ton formulaire par e-mail à `servicioalcliente@serpost.com.pe`, appelle le `(01) 511-5110`, ou rends-toi au bureau Serpost le plus proche — tu recevras un numéro de dossier (*número de expediente*) par e-mail pour suivre son avancement. Vois aussi [colis perdu](guide:lost-parcel), [colis endommagé](guide:damaged-parcel), [livré mais non reçu](guide:delivered-not-received) et [retourné à l’expéditeur](guide:returned-to-sender).

## Service client Serpost et contacts à Lima

Canaux officiels publiés sur [Serpost Contacto (gob.pe)](https://www.gob.pe/institucion/serpost/contacto-y-numeros-de-emergencias) :

- **Centre d’appels (Pérou) :** `(01) 511-5110`.
- **Standard principal :** `511-5000` (lignes du siège `(511) 533-2001` / `533-2002` / `533-2003` / `533-2004`).
- **E-mail service client et réclamations :** `servicioalcliente@serpost.com.pe` (webmaster : `webmaster@serpost.com.pe`).
- **Siège social (*Sede Central*) :** Av. Tomás Valle cdra. 7 s/n, Los Olivos, Lima 15302, Pérou.
- **Suivi en ligne et portail public :** [serpost.com.pe/Cliente/SegumientoLinea](https://www.serpost.com.pe/Cliente/SegumientoLinea) et [gob.pe/serpost](https://www.gob.pe/serpost).

> Méfie-toi des faux SMS prétendant qu’un colis Serpost est bloqué tant que tu ne paies pas une petite somme via un lien inconnu. Vérifie toujours les avis douaniers directement sur [serpost.com.pe/Cliente/SegumientoLinea](https://www.serpost.com.pe/Cliente/SegumientoLinea) ou sur les portails officiels `gob.pe` / SUNAT. Voir le guide sur les [SMS frauduleux de livraison](guide:delivery-scam-texts).

## Suivre un colis Serpost avec Peek

Lorsque tu saisis un code S10 péruvien de 13 caractères (`RR482917367PE` ou `EE739184627PE`, inventés) dans [Peek](/), Peek vérifie la clé de contrôle S10 et interroge d’abord Serpost, puis Chronopost. Pour un code S10 étranger arrivant au Pérou, ou pour sélectionner Serpost manuellement, appuie sur « Changer » lors de l’ajout ou sur « Changer de transporteur » sur la fiche du colis et choisis **Serpost** (rechercher « SERPOST », « Peru Post » ou « Servicios Postales del Perú » fonctionne aussi). Peek affiche « Serpost a ce colis. » dès que Serpost renvoie des scans.

Peek lit le suivi de Serpost et affiche l’étape (« Pris en charge », « En transit », « À la douane », « En livraison », « Prêt à être retiré », « Tentative de livraison », « Livré »), le libellé espagnol de Serpost sans l’invitation WhatsApp du bureau, le nom du bureau ainsi que l’heure de livraison. Lorsque Serpost associe la carte d’un bureau au Pérou, Peek place le scan à cet endroit sur la carte du colis ; une fois l’envoi en livraison, prêt à être retiré ou livré, Peek indique le Pérou comme destination. Serpost donne ses horaires à l’heure de Lima : Peek les date à l’heure de Lima et les affiche dans le fuseau de ton appareil. Le suivi Serpost ne donne ni date estimée de livraison, ni poids, ni nom de signataire.

Tant que le colis reste ouvert dans Peek, ou le jour de la livraison si les notifications sont activées, Peek vérifie jusqu’à toutes les 10 minutes ; le jour où Serpost affiche `ENVÍO EN PROCESO DE DISTRIBUCIÓN`, toutes les 2 minutes à partir de 6 h heure de Lima, tant que le suivi Serpost répond. Sinon, Peek vérifie toutes les heures. Après 12 heures avec uniquement « Étiquette créée » ou « Pris en charge », ou deux jours sans nouvelles, il vérifie toutes les heures quelle que soit ta manière de suivre le colis, et toutes les 6 heures après sept jours à « Étiquette créée ». Lorsque le suivi de Serpost ne répond pas et que Peek suit le colis uniquement via des services de suivi, il vérifie toutes les heures, toutes les 3 heures après une journée calme, et jusqu’à toutes les 15 minutes le jour où le colis est en livraison, tant que le colis est ouvert ou que les notifications sont activées.

Lorsque le scan le plus récent date de quatre jours et que le colis est toujours sur « Étiquette créée », « Pris en charge » ou « En transit », Peek le signale avec le jour de ce scan, par exemple « Aucune mise à jour depuis lun 12 oct ». La fiche du colis indique alors « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger Serpost. », avec un lien « Demande de l’aide à Serpost » vers la page de contact Serpost sur gob.pe. Après trois semaines sans nouveau scan, elle affiche « Rien de nouveau depuis plus de trois semaines. Si le colis n’est toujours pas arrivé, contacte le vendeur. Peek continue d’interroger Serpost. »

Tu peux choisir les alertes « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Peek surveille uniquement les mises à jour de suivi et ne peut ni dédouaner auprès de la SUNAT, ni déposer de réclamation, ni contacter Serpost à ta place.

## Questions sur le suivi Serpost

### À quoi ressemble un numéro de suivi Serpost ?

Serpost suit les codes UPU S10 à 13 caractères — deux lettres, 9 chiffres (terminés par un chiffre de contrôle) et un code pays d’origine à 2 lettres tel que `PE` (par exemple `RR482917367PE` ou `EE739184627PE`, inventés), ainsi que les codes S10 étrangers entrant au Pérou.

### Que signifie `ENVÍO SUJETO A TRÁMITE ANTE ADUANA` ?

Cela signifie que la douane péruvienne (SUNAT) a retenu le colis international pour formalités douanières ou contrôle des droits d’importation avant que Serpost puisse le remettre en livraison locale ou en retrait au bureau.

### Combien de temps met Serpost pour livrer ?

Serpost publie sur [gob.pe](https://www.gob.pe/institucion/serpost/pages/20800-enviar-cartas-dentro-y-fuera-del-pais-con-serpost) des délais pour les lettres : **48–72 heures** en Expreso national, **5–7 jours ouvrables** en Estándar national, **7–10 jours ouvrables** en Económico national ou Expreso international, et **10–30 jours ouvrables** en Económico international.

### Comment contacter le service client Serpost ou déposer une réclamation ?

Appelle le centre d’appels Serpost au `(01) 511-5110`, écris à `servicioalcliente@serpost.com.pe` ou rends-toi dans un bureau Serpost ; tous les contacts figurent sur [gob.pe/institucion/serpost/contacto-y-numeros-de-emergencias](https://www.gob.pe/institucion/serpost/contacto-y-numeros-de-emergencias).

:::sources
- [Serpost: Seguimiento en Línea](https://www.serpost.com.pe/Cliente/SegumientoLinea) – portail officiel de suivi en ligne et Call Center `(01) 511-5110`
- [Serpost: Portal Institucional (gob.pe)](https://www.gob.pe/serpost) – portail institutionnel officiel *Servicios Postales del Perú S.A.* (MTC)
- [Serpost: Contacto y Números de Atención](https://www.gob.pe/institucion/serpost/contacto-y-numeros-de-emergencias) – Call Center `511-5110`, standard `511-5000`, `servicioalcliente@serpost.com.pe` et siège de Los Olivos (page d’aide liée par Peek)
- [Serpost: Reclamos y Consultas](https://www.gob.pe/institucion/serpost/pages/33693-reclamos-y-consultas-servicios-postales-del-peru) – délais de réclamation (20 j recommandé, 30 j colis, 4 mois EMS), délais de réponse (16 j national / 30 j EMS) et règles d’indemnisation
- [Serpost: Envíos Nacionales e Internacionales](https://www.gob.pe/institucion/serpost/pages/20800-enviar-cartas-dentro-y-fuera-del-pais-con-serpost) – délais de livraison publiés pour les lettres, par service
- [Universal Parcel Scraper: serpost](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/serpost) – contrôle de clé UPU S10, requête `SegumientoLinea`, règles de statuts espagnols, extraction du nom de bureau et des coordonnées péruviennes, horaires `America/Lima`
:::
