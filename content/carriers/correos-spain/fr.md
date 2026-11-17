---
title: Suivi Correos : suivre un colis d’Espagne vers la France
description: Suivi Correos (Espagne) : format du numéro, traduction des statuts, livraison en France par La Poste, douane, délais, service client et suivi avec Peek.
slug: correos-espagne-suivi
published: 2026-11-17
updated: 2026-11-17
---

Correos (`Sociedad Estatal Correos y Telégrafos, S.A., S.M.E`) est la poste nationale espagnole – distincte des postes du Chili ou du Mexique et de [Correos Express](carrier:correos-express), filiale express du Grupo Correos dotée de ses propres numéros. Ce guide s’adresse d’abord à qui attend en France un colis parti d’Espagne, avec plus bas une section pour la réception en Espagne. Suis ton envoi sur le [tracker anglais de Correos](https://www.correos.es/es/en/tools/tracker/items) (ou en espagnol sur le [Localizador de envíos](https://www.correos.es/es/es/herramientas/localizador/envios)), ou colle le code ci-dessus. Correos l’envoie par e-mail ou SMS et l’imprime sur l’avis de passage ; les boutiques l’ajoutent au mail d’expédition.

## Format du numéro de suivi Correos

Correos ne publie pas de liste officielle, mais ses numéros prennent en général ces quatre formes (exemples inventés) :

| Exemple | Ce que c’est |
| --- | --- |
| `PQ48291057364928174629E` | 23 caractères : code colis. Commence par une lettre et finit par une lettre de contrôle. |
| `PQ4829105736492V` | 16 caractères : code d’expédition pouvant regrouper plusieurs colis ayant chacun un code à 23 caractères. |
| `PR482910573649281C` | `PR`, 15 chiffres et `C`. |
| `RR482910576ES` | 2 lettres, 9 chiffres et `ES` : numéro postal international émis en Espagne. |

Un code PIN `Citypaq` ouvre seulement une consigne en Espagne : ce n’est pas un numéro de suivi. Une suite de 16 ou 23 chiffres seuls n’appartient jamais à Correos et peut venir de [Correos Express](carrier:correos-express). Code perdu ? Vois [où trouver son numéro de suivi](guide:find-tracking-number).

## Signification des statuts Correos

Le site `correos.es` affiche ses statuts en espagnol ou en anglais (tandis que Peek lit la version française de Correos lorsque tu utilises Peek en français) :

| Statut Correos | Signification |
| --- | --- |
| `Pre-registered` / `Pre-admisión` | Envoi annoncé en ligne par l’expéditeur, pas encore remis à Correos. |
| `Accepted` / `Admitido.` | Colis pris en charge par Correos au départ. |
| `Classified` | Trié dans un centre Correos. |
| `Leaving the Exchange Office` / `En oficina de cambio` | Passage ou départ du bureau d’échange international en Espagne. |
| `Llegada a oficina de cambio` | Arrivée au bureau d’échange du pays de destination. |
| `Leaving the destination Exchange Office` | Sortie du bureau d’échange de destination vers la distribution locale. |
| `Customs procedures starting` / `Inicio de tramitación aduanera` | Début du dédouanement (hors UE ou Canaries, Ceuta, Melilla). |
| `Registration in the delivery unit` / `Alta en la unidad de reparto` | Arrivé à l’unité de distribution locale. |
| `In delivery` / `En reparto` | En tournée avec le facteur – ou en attente d’un nouvel essai (voir ci-dessous). |
| `Delivery attempt. Absent.` / `Realizado intento de entrega` | Passage manqué, par exemple en cas d’absence. |
| `At recipient’s disposal` / `A disposición del destinatario` | Prêt au bureau de poste ou dans le `Citypaq` choisi. |
| `Delivered` / `Entregado` | Livré. |
| `Entregado al remitente` | Remis à l’expéditeur : malgré « entregado », le colis est reparti en Espagne. |

En anglais, `In delivery` recouvre à la fois `En reparto` (en tournée) et `En proceso de entrega` (attente du prochain passage après un échec). De même, `Finalizado plazo retirada` (garde expirée) et `Recipient unknown` (destinataire inconnu) annoncent le retour. Vois aussi les [statuts de suivi](guide:tracking-statuses).

## Livraison en France, délais et douane

**Qui livre en France ?** Le `Paquete Internacional Premium` emprunte le réseau `EMS` (`Servicio de Correo Express`), opéré dans chaque pays par la poste nationale – en France, [La Poste / Colissimo](carrier:la-poste). Les numéros terminés par `ES` se suivent aussi chez [La Poste / Colissimo](carrier:la-poste) dès leur entrée en France ; sur les autres offres, le SMS ou l’avis reçu nomme le livreur.

**Délais vers l’Europe :** Correos annonce 3 à 6 jours en Europe en `Paq Light Internacional` (jusqu’à 2 kg sans signature) et 8 à 10 jours en `Paquete Internacional Económico` (contre signature). En `Premium` et `Standard`, le délai en jours ouvrés dépend du pays (hors douane, +1 à 2 jours hors grandes villes).

**Douane :** L’Espagne péninsulaire et les Baléares font partie du territoire douanier et TVA de l’Union (`Territorio Aduanero de la Unión`, `TAU`), donc sans douane vers la France métropolitaine ou la Belgique (les départements d’outre-mer français étant hors zone TVA UE, un envoi vers eux part avec formalités). Les Canaries (hors zone TVA UE) impliquent des formalités douanières, et Ceuta ainsi que Melilla sont hors des deux : un envoi parti de Ceuta ou Melilla vers l’UE subit les règles d’importation hors UE (sur les ventes à distance : droit UE de 3,00 € par type d’article jusqu’à 150 € du 1er juillet 2026 au 1er juillet 2028, plus taxe UE de 2 € par catégorie dès le 1er novembre 2026). Plus dans [colis en douane](guide:customs).

## Recevoir en Espagne : bureau, Citypaq et douane

Après le SMS de Correos, compte 24 à 72 h. Avec deux passages inclus, un second SMS annonce un nouvel essai sous 24 h ; sinon, un SMS indique le bureau (`oficina`) où retirer l’envoi dès le jour ouvré suivant pendant 15 jours (sauf durée autre sur l’`aviso de llegada`), avec pièce d’identité originale à photo (ou autorisation signée + copie de ta pièce + pièce originale du mandataire, hors remise au seul destinataire). En consigne `Citypaq`, tu as 5 jours calendaires avec le PIN reçu par SMS, e-mail ou dans l’`App de Correos` (renvoi sur la page `Citypaq`). `Correos Modify` permet de modifier adresse, bureau ou `Citypaq` dans la même province avant `En reparto` si le vendeur l’autorise.

**Importations en Espagne et paiements officiels :** Tout envoi arrivant en Péninsule ou aux Baléares depuis un pays hors UE (ou depuis Canaries, Ceuta, Melilla) est dédouané par Correos. Jusqu’à 150 € hors accises et contrôles particuliers (`Bajo Valor/H7`), sont dus la TVA (hors `IOSS` ou `PDDP`), le tarif Correos et, sur les ventes à distance hors UE ou depuis Ceuta/Melilla, le droit UE de 3,00 € (dès le 1er juillet 2026) et la taxe UE de 2 € (dès le 1er novembre 2026). Correos permet de payer avant livraison sur `www.correosaduanas.es`, sur `mioficina.correos.es` ou dans l’`App de Correos` (et pour un `D.U.A.` ordinaire au-delà de 150 €, sur `www.correosaduanas.es` ou par virement bancaire), ou en personne au facteur (`al cartero`, espèces exactes ou carte sous couverture mobile) ou en bureau de poste informatisé en espèces ou par carte.

## Colis bloqué, perdu ou endommagé

- **Suivi figé :** Vois d’abord le vendeur puis Correos (et [La Poste / Colissimo](carrier:la-poste) pour un code `ES` en France). En Espagne, un colis en incident reste `estacionado` 5 jours calendaires (`Gestión de estacionados` avec PIN). Voir [suivi bloqué](guide:tracking-not-updating).
- **Perdu :** Correos indemnise l’expéditeur, sauf autorisation signée au profit du destinataire. Délais de réclamation perte à l’international dès le dépôt : 7 jours à 4 mois (`Premium`), 15 jours à 6 mois (`Standard`), 25 jours à 6 mois (`Económico`), aucune indemnisation en `Paq Light Internacional` (en national : 4 jours à 1 an en `Paquete Estándar`, retard sous 21 jours). Voir [colis perdu](guide:lost-parcel).
- **Endommagé :** À l’international, signale l’emballage abîmé à l’agent avant signature et demande un `acta de daños`, puis préviens l’expéditeur (3 jours à 4 mois en `Premium` ; sous 7 jours calendaires en national). Voir [colis endommagé](guide:damaged-parcel), [livré mais non reçu](guide:delivered-not-received) et [retour à l’expéditeur](guide:returned-to-sender).

## Contacter le service client Correos

Selon les pages officielles de Correos :

- **Téléphone (Espagne) :** `915 197 197` ou `900 400 004`, lundi–vendredi (hors fériés) 8 h–20 h, samedi 9 h–13 h.
- **En ligne et réseaux :** [formulaire de réclamation](https://www.correos.es/es/en/customer-service/your-query-your-complaint/queries-and-complaints-form/details), Facebook, Instagram et X (`@CorreosAtiende`, lundi–vendredi 9 h–20 h), ou en bureau Correos.

> Correos ne demande jamais de données personnelles, coordonnées bancaires ni paiement par e-mail ou SMS. Pour des frais de douane en Espagne, ouvre toi-même `correosaduanas.es`, `mioficina.correos.es` ou l’`App de Correos`, ou règle au facteur ou en bureau de poste comme indiqué plus haut. Voir [faux SMS de livraison](guide:delivery-scam-texts).

## Suivre un colis Correos avec Peek

[Peek](/) détecte aussitôt les codes à 23 ou 16 caractères, `PR` et `…ES`. Si la lettre de contrôle échoue, corrige le numéro ou choisis Correos via « Changer de transporteur ». Sur un code d’expédition à un seul colis, Peek suit ce colis ; s’il y en a plusieurs, entre chaque code à 23 caractères. Les liens `correos.es` avec `tracking-number=` ou `numero=` valide marchent sans code postal.

En français, Peek récupère les statuts en français de Correos (espagnol ou portugais dans ces langues, anglais ailleurs, avec repli `Shipment collected` si un libellé de collecte est vide). Il montre les 20 derniers scans (sans lieu), poids, dimensions, heure de remise et, pendant la garde, nom et adresse du bureau. Il écarte adresse et signature, ne montre le nom du destinataire qu’à toi (jamais sur un lien partagé) et n’affiche pas de date estimée. Les heures sont lues sur Madrid, sauf distribution aux Canaries (heure des Canaries) et scans d’origine étrangers (heure du pays d’origine), puis affichées sur ton appareil (« Heures dans ton fuseau horaire : … »).

Un numéro `…ES` consulté depuis la France bascule vers [La Poste / Colissimo](carrier:la-poste) dès qu’elle publie du neuf ; un code à 23 caractères reste sur Correos, et un envoi postal étranger vers l’Espagne bascule vers Correos. Tant que tu as le colis ouvert à l’écran, ou le jour de la livraison avec les notifications activées, Peek vérifie jusqu’à toutes les 10 minutes, et jusqu’à toutes les 2 minutes une fois en cours de livraison dès 6 h locales ce jour-là tant que Correos ou La Poste répond en direct (via les services de suivi : jusqu’à toutes les 15 minutes ce jour-là, sinon toutes les heures ou toutes les 3 h après un jour sans nouvelle) ; le reste du temps, ainsi qu’après deux jours sans nouvelle (12 h sur « Étiquette créée » ou « Pris en charge »), il vérifie toutes les heures, avec alertes « Toutes les mises à jour », « Importantes uniquement » ou « Jour de livraison uniquement ».

## Questions fréquentes sur le suivi Correos

### Qui livre les colis Correos en France ?

En `Paquete Internacional Premium`, le réseau `EMS` est assuré par La Poste. Les codes finissant par `ES` se suivent aussi sur La Poste / Colissimo ; ailleurs, le SMS ou l’avis indique le livreur.

### Combien de temps met un colis Correos vers la France ?

Pour l’Europe, Correos indique 3 à 6 jours en `Paq Light Internacional` et 8 à 10 jours en `Paquete Internacional Económico`. En `Premium` et `Standard`, le délai ouvré dépend du pays.

### Y a-t-il des frais de douane depuis l’Espagne ?

Non entre l’Espagne péninsulaire ou les Baléares et la France métropolitaine ou la Belgique, mais des formalités s’appliquent depuis les îles Canaries, Ceuta ou Melilla (ou vers les départements d’outre-mer français, hors zone TVA UE).

### Correos et Correos Express sont-ils le même transporteur ?

Non : tous deux relèvent du Grupo Correos, mais Correos est la poste nationale tandis que Correos Express est sa filiale express aux numéros tout en chiffres (16 ou 23 chiffres).

:::sources
- [Correos: Correos delivery tracker](https://www.correos.es/es/en/tools/tracker/items) – suivi anglais
- [Commission européenne : Territorial scope](https://taxation-customs.ec.europa.eu/taxation/vat/vat-directive/how-does-vat-work/territorial-scope_en) – DOM, Canaries, Ceuta et Melilla
- [Correos: Localizador de envíos](https://www.correos.es/es/es/herramientas/localizador/envios) – suivi espagnol
- [Correos: Mailing code](https://www.correos.es/es/en/customer-service/receive/delivery-code) – envoi du code
- [Correos: Aviso legal](https://www.correos.es/es/es/legales/aviso-legal) – raison sociale
- [Correos Express: Quiénes somos](https://www.correosexpress.es/es/sobre-correos-express/quienes-somos) – filiale express
- [Correos: Paquete Internacional Premium](https://www.correos.es/es/es/particulares/enviar/enviar-al-extranjero/paquetes-al-extranjero/paquete-internacional-premium) – EMS, acta de daños, délais
- [Correos: Paquete Internacional Standard](https://www.correos.es/es/es/particulares/enviar/enviar-al-extranjero/paquetes-al-extranjero/paquete-internacional-standard) – réclamation perte 15 j–6 mois
- [Correos: Paquete Internacional Light](https://www.correos.es/es/es/particulares/enviar/enviar-al-extranjero/paquetes-al-extranjero/paquete-internacional-light) – 3–6 jours Europe, sans indemnisation
- [Correos: Paquete Internacional Económico](https://www.correos.es/es/es/particulares/enviar/enviar-al-extranjero/paquetes-al-extranjero/paquete-internacional-economico) – 8–10 jours Europe, perte 25 j–6 mois
- [Correos: Paquete Estándar](https://www.correos.es/es/es/particulares/enviar/envios-nacionales/envios-economicos/paquete-estandar) – 15 jours, Canaries/Ceuta/Melilla
- [Correos: Everything about your shipment](https://www.correos.es/es/en/individuals/receive) – 24–72 h, 2e essai, 15 jours
- [Correos: Aviso de llegada](https://www.correos.es/es/es/atencion-al-cliente/recibir/aviso-de-llegada) – retrait jour ouvré suivant
- [Correos: Citypaq](https://www.correos.es/es/en/individuals/receive/our-drop-off-and-collection-points/citypaq) – consignes et PIN
- [Correos: Oficina o Citypaq](https://www.correos.es/es/es/actualidad/2026/oficina-o-citypaq--elige-el-metodo-de-recogida-que-mejor-se-adap) – 5 jours calendaires
- [Correos: Recoger envío](https://www.correos.es/es/es/atencion-al-cliente/recibir/recoger-envio) – pièce d’identité et procuration
- [Correos: Correos Modify](https://www.correos.es/es/es/particulares/recibir/esperando-la-entrega/quiero-modificar-la-entrega) – modification avant En reparto
- [Correos: Envíos Estacionados](https://www.correos.es/es/es/atencion-al-cliente/recibir/envios-estacionados) – 5 jours calendaires
- [Correos: Cobertura por demora](https://www.correos.es/es/es/atencion-al-cliente/coberturas-y-garantias/nacionales/demora-nacional) – indemnisation expéditeur, 21 jours
- [Correos: Deterioro nacional](https://www.correos.es/es/es/atencion-al-cliente/coberturas-y-garantias/nacionales/deterioro-nacionales) – 7 jours calendaires
- [Correos: Extravío nacional](https://www.correos.es/es/es/atencion-al-cliente/coberturas-y-garantias/nacionales/extravio-nacional) – 4 jours à 1 an
- [Correos: Importación en Península y Baleares](https://www.correos.es/es/es/atencion-al-cliente/informacion-aduanera/dua-importacion/importacion-en-peninsula-y-baleares) – DUA et moyens de paiement
- [Correos: Arancel y tasas UE](https://www.correos.es/es/es/atencion-al-cliente/informacion-aduanera/arancel-para-envios-de-bajo-valor-en-la-ue) – TAU, 3 € et 2 €, canaux de paiement
- [Correos: Handling of queries and claims](https://www.correos.es/es/en/customer-service/your-query-your-complaint/handling-of-queries-and-claims) – téléphones et horaires
- [Correos: Queries and complaints form](https://www.correos.es/es/en/customer-service/your-query-your-complaint/queries-and-complaints-form/details) – formulaire de réclamation en ligne
- [Correos: Incidencias](https://www.correos.es/es/es/atencion-al-cliente/recibir/incidencias) – @CorreosAtiende et bureaux
- [Correos: Phishing](https://www.correos.es/es/en/customer-service/information-security/phishing) – prévention fraude
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – fonctionnement dans Peek
:::
