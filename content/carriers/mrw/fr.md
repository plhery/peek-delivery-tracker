---
title: Suivi MRW : numéro à 12 chiffres, statuts et contacts
description: Suivre un colis MRW (Espagne et Portugal) : où trouver le numéro à 12 chiffres, traduction des statuts espagnols, retrait MRW Point, réclamations et Peek.
slug: mrw-suivi
published: 2026-11-17
updated: 2026-11-17
---

MRW est un réseau espagnol de transport urgent en franchise comptant plus de 550 agences en Espagne, au Portugal, en Andorre et à Gibraltar. Saisis ton numéro d’envoi dans l’encadré « No abonados » sur la [page de suivi MRW](https://www.mrw.es/seguimiento/) (ou « Não assinante » sur [mrw.pt](https://www.mrw.pt/seguimiento/)), ou colle-le ci-dessus. Tu le trouves dans le mail d’expédition du vendeur, dans le SMS MRW ou sur le reçu d’agence.

## Numéro de suivi MRW : format et où le trouver

Le formulaire demande le « número de envío », affiché ensuite comme « Número de albarán » (sur `mrw.pt` : « Núm. expedição » puis « Número de guia »). Sur l’étiquette type de MRW, il figure en 12 chiffres sous le code-barres, et à côté de « N.º Etiqueta » avec une barre oblique. Exemples inventés :

| Format | Ce que c’est |
| --- | --- |
| `482910573649` | Numéro d’expédition sous le code-barres, à saisir tel quel. |
| `48291/0573649` | Le même numéro à côté de « N.º Etiqueta » : retire la barre sur `mrw.es` ou `mrw.pt`. |
| `48291A573649` | Cinq chiffres, une lettre, six chiffres : variante observée sur des colis MRW, non illustrée sur sa page d’aide. |

Où le récupérer :

- **Dépôt en agence MRW :** sur ton reçu, ou auprès de ton agence MRW.
- **Achat en ligne :** dans l’e-mail d’expédition ou sur ta commande.
- **SMS :** MRW envoie un préavis gratuit si l’expéditeur a transmis ton mobile ; la page `concertarmrw.asp` (« Concertar entrega » / « Agendar entrega ») sert à convenir d’un créneau, pas au suivi général.

Douze chiffres seuls conviennent aussi, par exemple, à [FedEx](carrier:fedex), [DPD France](carrier:dpd-fr), [Mondial Relay](carrier:mondial-relay), [Colis Privé](carrier:colis-prive), [BRT](carrier:brt) ou SF Express : vérifie le transporteur dans le mail ou consulte les [formats de numéros de suivi](guide:tracking-number-formats). Si MRW affiche « Envío no encontrado » (`mrw.es`) ou « Envio não encontrado » (`mrw.pt`) et que les chiffres sont bons, son message précise que l’envoi est encore « pendiente de recibir en MRW ».

## Statuts de suivi MRW traduits en français

Les statuts sont en espagnol sur `mrw.es` (et dans Peek) et en portugais sur `mrw.pt` :

| Statut sur mrw.es | Signification |
| --- | --- |
| `Pendiente de recoger` | Étiquette créée, mais l’expéditeur n’a pas encore remis le colis à MRW. |
| `Envío recogido en origen` | Colis pris en charge par MRW au départ. |
| `En tránsito` | En transit national ou international entre agences et plateformes. |
| `Entrega concertada` | Livraison convenue avec le destinataire. |
| `Envío en reparto` | En cours de livraison avec le chauffeur. |
| `Destinatario ausente o cerrado` | Tentative échouée : personne sur place ou commerce fermé. |
| `Depositado en punto MRW` | Prêt au retrait dans le point MRW choisi. |
| `Depositado en PointCorner destino` | Prêt au retrait dans un commerce partenaire `PointCorner`. |
| `Envío entregado` | Colis livré. |

Ne confonds pas `Pendiente de recoger` (MRW attend le colis chez l’expéditeur) et `Depositado en punto MRW` (retrait prêt). Plus de repères dans [statuts d’un colis](guide:tracking-statuses).

## Livraison à domicile, MRW Point et absence

**À domicile :** selon les conditions générales de MRW, le colis est livré dès qu’une personne capable présente à l’adresse signe (papier ou terminal mobile), destinataire ou non ; aucune remise aux moins de 18 ans. Le dépôt en consigne exige ton accord préalable exprès ou un choix validé à la commande. Les SMS permettent de modifier le jour sur « Concertar entrega » / « Agendar entrega » avec le code SMS.

**MRW Point :** points partenaires en trois formats : `PointOffice`, `PointCorner` et consignes `PointMatic`. Choisi lors de l’achat, le point envoie un SMS ou e-mail à l’arrivée (avec le PIN d’ouverture pour un `PointMatic`). En `PointOffice`, `PointCorner` ou agence MRW, présente carte d’identité ou passeport (pour un tiers : copie de sa pièce + autorisation signée). Les hubs marqués `(Plataforma)` n’accueillent pas le public : cherche ton agence via le [localisateur MRW](https://www.mrw.es/oficina_transporte_urgente/MRW_franquicia.asp).

**Colis non livrable :** MRW avertit l’expéditeur, qui a jusqu’à 15 jours pour choisir retour ou réexpédition. MRW ne publie ni le nombre d’essais ni la durée de garde : interroge l’agence affichée dans le suivi.

## Colis en retard, perdu ou endommagé

Le contrat MRW est conclu avec l’expéditeur (la boutique) : contacte-la d’abord.

- **Réclamations :** le [formulaire de réclamation MRW](https://www.mrw.es/empresa_mensajeria_urgente/MRW_formulario_reclamacion.asp) couvre aussi les achats payés via un tiers comme Amazon (retard, perte, casse, vol, `falso aviso`). Au Portugal, le [formulaire de réclamation MRW Portugal](https://www.mrw.pt/empresa_mensajeria_urgente/MRW_formulario_reclamacion.asp) renvoie aussi en pied de page au `Livro de Reclamações`.
- **Avarie :** note par écrit tout dommage visible à la remise ; tu as 7 jours calendaires pour les dommages cachés et 21 jours après livraison pour un retard. Voir [colis endommagé](guide:damaged-parcel) et [livré mais non reçu](guide:delivered-not-received).

> MRW écrit depuis `@mrw.es` ou `@mrw.pt`, n’agit jamais comme tiers de confiance entre particuliers, ne demande jamais de paiement par lien pour livrer ni d’appeler un numéro (comme le `11827`) pour localiser ton colis, et déconseille d’ouvrir toute pièce jointe ou lien ne menant pas à son site officiel. Les frais d’envoi dus, droits de douane, TVA et frais analogues restent payables au moment même de la livraison. Teste tout numéro suspect directement sur `mrw.es` ou `mrw.pt` : sans résultat, le message ne vient pas de MRW ([faux SMS de livraison](guide:delivery-scam-texts)).

## Contacter le service client MRW

D’après les sites officiels de MRW :

- **Espagne :** `+34 91 829 66 89` (seul numéro cité sur la page antifraude) et `900 103 668` (lundi–vendredi 8 h–20 h, samedi 9 h–13 h selon la FAQ), e-mail `informacion@mrw.es`, `@mrw_clientes` sur X et formulaires sur la [page contact MRW](https://www.mrw.es/empresa_mensajeria_urgente/MRW_contacto_paqueteria.asp).
- **Portugal :** `+351 219 587 279` (tarif fixe national, sans horaires publiés), `@mrw_portugal` sur X et [Contate-nos](https://www.mrw.pt/empresa_mensajeria_urgente/MRW_contacto_paqueteria.asp) (pas d’e-mail sur `mrw.pt`).
- **Agences :** dans le localisateur (vérifie les horaires fériés par téléphone ou mail ; prends toujours les numéros sur `mrw.es` ou `mrw.pt`).

## Suivre un colis MRW avec Peek

[Peek](/) reconnaît aussitôt la forme `48291A573649`. Un numéro de 12 chiffres (`482910573649`, ou `48291/0573649` sans la barre) convenant à plusieurs transporteurs sans vérification automatique possible chez MRW, choisis MRW via « Changer de transporteur ». Un lien `mrw.es` ne marche qu’avec la forme à lettre (pas les liens SMS).

Peek interroge `mrw.es` (valable aussi pour MRW Portugal), affiche les statuts en espagnol (remplaçant les libellés inconnus par « Actualización de seguimiento ») et place chaque agence sur la carte (sans l’adresse exacte d’un `PointCorner` ni date estimée). Si `mrw.es` ne donne qu’un résumé, l’historique vient de services de suivi universels si disponible.

Les heures de MRW n’ont pas de fuseau : Peek les lit à l’heure du lieu de chaque agence (« Heures dans ton fuseau horaire : … »). Tant que tu as le colis ouvert à l’écran, ou le jour de la livraison avec les notifications activées, Peek vérifie jusqu’à toutes les 10 minutes, et jusqu’à toutes les 2 minutes une fois en cours de livraison dès 6 h locales ce jour-là tant que MRW répond (via les services de suivi : jusqu’à toutes les 15 minutes ce jour-là, sinon toutes les heures ou toutes les 3 h après un jour sans nouvelle) ; le reste du temps, ainsi qu’après deux jours sans nouvelle (12 h sur « Étiquette créée » ou « Pris en charge »), il vérifie toutes les heures, avec alertes « Toutes les mises à jour », « Importantes uniquement » ou « Jour de livraison uniquement ».

## Questions fréquentes sur le suivi MRW

### Existe-t-il un suivi MRW en français ou en anglais ?

Non : `mrw.es` existe en espagnol, portugais et catalan ; le tableau plus haut traduit les statuts courants.

### Quels sont les horaires et délais de livraison MRW ?

Aucun horaire général n’est publié pour les commandes de boutiques. Sur `mrw.es` en Espagne péninsulaire, la FAQ promet avant 19 h le jour ouvré suivant (48/72 h ouvrées en maritime vers les Baléares).

### Que veut dire « Pendiente de recoger » ?

Que l’étiquette est créée mais que l’expéditeur n’a pas encore confié le colis à MRW – pas qu’il t’attend en point relais.

### Comment écrire au service client MRW ?

À `informacion@mrw.es` ou via les formulaires thématiques de la page de contact espagnole.

:::sources
- [MRW: Seguimiento de envíos](https://www.mrw.es/seguimiento/) – suivi espagnol, langues et plateformes
- [MRW: ¿Cómo localizar su número de envío?](https://www.mrw.es/includes/numero_de_envio.html) – formats sur l’étiquette
- [MRW: Alertas en el seguimiento](https://www.mrw.es/seguimiento_envios/MRW_alertas.asp) – préavis de livraison par SMS
- [MRW Portugal: Seguimento](https://www.mrw.pt/seguimiento/) – suivi portugais et Concertar/Agendar entrega
- [MRW Portugal: Textos em português](https://www.mrw.pt/Dictionary/lang/pt.json) – libellés portugais, @mrw.pt, @mrw_portugal et +351 219 587 279
- [MRW: Información fraude](https://www.mrw.es/informacion-fraude/) – prévention fraude Espagne et frais à la remise
- [MRW Portugal: Informação fraude](https://www.mrw.pt/informacion-fraude/) – prévention fraude Portugal
- [MRW: Conoce el estado de tu envío](https://blog.mrw.es/conoce-el-estado-de-tu-envio/) – sens des statuts
- [MRW: MRW Point funcionamiento](https://www.mrw.es/oficina_transporte_urgente/MRW_point_funcionamiento.asp) – types de points, PIN et pièce d’identité
- [MRW Portugal: MRW Point](https://www.mrw.pt/mrw-point/) – plus de 550 agences
- [MRW: Condiciones generales](https://www.mrw.es/uso_MRW_mensajeria/condiciones_venta/) – livraison, 15 jours, délais de réclamation
- [MRW: Formulario reclamación](https://www.mrw.es/empresa_mensajeria_urgente/MRW_formulario_reclamacion.asp) – réclamations et falso aviso
- [MRW: Contacte con nosotros](https://www.mrw.es/empresa_mensajeria_urgente/MRW_contacto_paqueteria.asp) – formulaires et e-mail
- [MRW Portugal: Contate-nos](https://www.mrw.pt/empresa_mensajeria_urgente/MRW_contacto_paqueteria.asp) – contact Portugal
- [MRW: FAQs Contratación Online](https://www.mrw.es/enviar/nacional/faqs/index.asp) – numéros, horaires et délais
- [MRW: Nuestras oficinas](https://www.mrw.es/oficina_transporte_urgente/MRW_franquicia.asp) – localisateur d’agences
- [MRW Portugal: Reclamação](https://www.mrw.pt/empresa_mensajeria_urgente/MRW_formulario_reclamacion.asp) – formulaire portugais et renvoi au Livro de Reclamações
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – fonctionnement dans Peek
:::
