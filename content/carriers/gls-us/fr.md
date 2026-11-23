---
title: Suivi GLS US (GSO) : numéros, statuts et livraison
description: Suivre un colis GLS US (aussi appelé GSO) : format SH, signification des scans, jours de livraison, signatures, absences et contact.
slug: suivi-gls-us
published: 2026-11-23
updated: 2026-11-23
---

GLS US (General Logistics Systems US, Inc.) est la filiale américaine de GLS Group, qui livre des colis dans tout l'Ouest des États-Unis. Le catalogue de Peek la connaît aussi sous les noms GLS USA, GLS United States, General Logistics Systems US, GSO et Golden State Overnight, l'entreprise rachetée par GLS en 2016. Pour suivre un colis GLS US, colle son numéro dans le champ ci-dessus ou saisis-le sur [la page de suivi américaine de GLS](https://gls-group.com/US/en/send-and-receive/track-a-parcel/).

## Numéro de suivi GLS US : à quoi il ressemble

Le catalogue de Peek reconnaît un format pour GLS US (chiffres inventés) :

| Ce que tu colles (inventé) | Ce que fait Peek |
| --- | --- |
| `SH605182704936815247` | SH suivi de 18 chiffres, soit 20 caractères. Seul GLS US utilise ce format dans le catalogue de Peek, mais la correspondance est faible : Peek interroge donc GLS US avant de le nommer. |
| `sh 6051 8270 4936 8152 47` | Le même numéro. Majuscules, espaces et tirets n'ont aucune importance. |
| Un numéro GLS US d'une autre forme | Peek ne le reconnaît pas automatiquement. Choisis GLS US toi-même (voir « Suivre un colis GLS US avec Peek » ci-dessous). |
| Un lien de suivi de gls-group.com avec `/US/` dans le chemin, ou de gls-us.com ou gso.com | Peek extrait le numéro du lien et nomme aussitôt GLS US (« Transporteur détecté »). |

**Où le trouver.** Dans l'e-mail d'expédition du magasin ou sur ta page de commande, ou encore dans les e-mails ou SMS de GLS si le magasin t'y a inscrit. Chaque carton a sa propre étiquette, donc plusieurs cartons signifient plusieurs numéros. La recherche sans numéro sur le site de GLS demande le numéro de compte et la référence de l'expéditeur : demande au magasin. Voir [où trouver ton numéro de suivi](guide:find-tracking-number). Un numéro de commande ne suffit pas.

**Distinct du GLS européen.** GLS US appartient à GLS Group, et Peek suit ses colis sur le suivi propre à GLS US. Un lien gls-group.com contenant `/EU/` ou `/DE/` renvoie Peek vers [GLS Germany](carrier:gls-de), pas vers GLS US. Si l'e-mail du magasin mentionne une société GLS européenne ou un autre transporteur, suis-le là-bas. [Formats des numéros de suivi](guide:tracking-number-formats) montre les formats d'autres transporteurs.

## Signification des statuts de suivi GLS US

Chaque scan sur le suivi de GLS US indique une date et une heure, et la plupart précisent un lieu : ville, État et pays. Les scans connus du code de Peek sont en anglais et en majuscules, dans l'ordre habituel :

| GLS US affiche | Ce que ça veut dire pour toi | Peek affiche |
| --- | --- | --- |
| `LABEL CREATED` | L'expéditeur a créé l'étiquette. GLS US n'a peut-être pas encore le colis. Ce scan ne porte pas de lieu. | « Étiquette créée » |
| `ARRIVAL SCAN - DELIVERY SCHED FOR 07/15/2026` | GLS US a scanné le colis à son arrivée. La date finale (mois en premier) est le jour prévu par GLS US pour la livraison. | « En transit » |
| `SCANNED AT DELIVERY DEPOT` | Scanné au dépôt chargé de la livraison. | « En transit » |
| `ON ROUTE FOR DELIVERY` | En tournée de livraison aujourd'hui. | « En livraison » |
| `SHIPMENT DELIVERED` | Livré. | « Livré » |

**Le statut global de l'envoi.** Au-dessus des scans, le suivi de GLS US résume l'envoi en quelques mots : `label created`, `in transit`, `out for delivery`, `delivered` ou `returned`. Quand ce résumé est plus avancé que les scans, Peek s'y fie : un colis résumé comme `delivered` s'affiche comme « Livré » même si le dernier scan ne le dit pas, et `returned` affiche « Retourné à l’expéditeur ».

**Autres libellés.** Le code de Peek ne connaît pas de libellé propre à GLS US pour une livraison manquée, un incident, une retenue ou un point relais. Toute ligne inconnue reste affichée telle que GLS US l'écrit, et Peek la classe selon ses règles générales. Plus dans [signification des statuts de suivi](guide:tracking-statuses).

Si rien ne suit `LABEL CREATED` pendant au moins un jour, Peek indique « L’expéditeur ne l’a pas encore remis à GLS US. Le suivi commencera à la remise. ». Demande au magasin.

**E-mails et SMS de GLS.** Si le magasin t'inscrit lors de la création de l'étiquette, GLS t'écrit lorsque son dépôt scanne le colis (avec la date de livraison prévue), en cas de retard ou d'échec, lors de la mise en livraison (avec une heure estimée) et à la livraison.

## Délais, signatures et livraisons manquées chez GLS US

**Adresse.** GLS ne livre pas en boîte postale (*P.O. box*) et trie les colis par code ZIP : un code erroné peut retarder la livraison. Il ne réachemine un colis qu'avec l'autorisation de l'expéditeur : contacte le magasin. Voir [modifier une adresse de livraison](guide:change-delivery-address).

**Jours.** Du lundi au vendredi ; le samedi seulement dans certaines zones si l'expéditeur le demande ; jamais le dimanche. GLS est fermé six jours fériés américains (Jour de l'An, Memorial Day, Independence Day, Labor Day, Thanksgiving et Noël). Un colis prévu un jour férié est livré le jour ouvré suivant.

**Horaires.** Ils dépendent du service choisi par l'expéditeur. Priority arrive avant 10 h 30 le jour ouvré suivant dans la plupart des zones, Ground avant 17 h (18 h en zone rurale). Sur trajets plus longs, Priority arrive avant 10 h 30 le jour prévu et Ground avant la fin de journée. Les livraisons du samedi arrivent avant 10 h 30 dans la plupart des zones.

**Signature.** L'expéditeur choisit l'option. Par défaut, le livreur recueille une signature en entreprise. À une maison individuelle, le colis est laissé si personne ne peut signer et que l'endroit est sûr et sec ; dans un immeuble d'appartements, devant la porte ou au bureau de gestion/location si le livreur n'accède pas à la porte. Avec *Signature Required*, GLS ne laisse pas le colis sans signature ; avec *Adult Signature Required* (obligatoire pour l'alcool et le tabac), le signataire doit avoir au moins 21 ans.

**Livraison manquée.** Tu as manqué le passage ou trouvé un avis sur la porte ? Reprogramme avec le lien dans les e-mails ou SMS de GLS, ou appelle la ligne destinataires ci-dessous. Les pages de GLS ne précisent ni le nombre de tentatives ni la durée de garde, et ne présentent les GLS Points que comme points de dépôt pour expéditeurs. Voir [livraison manquée](guide:missed-delivery) et [colis non retiré](guide:uncollected-parcel).

## Colis GLS US introuvable, bloqué, perdu ou retourné

**Introuvable.** Le suivi de GLS US indique lorsqu'il ne connaît pas un numéro. Peek affiche alors « En attente du premier scan de GLS US » pour un numéro SH laissé en détection, ou « En attente de GLS US » si tu as choisi GLS US toi-même, et continue de vérifier. Une nouvelle étiquette met souvent un moment à apparaître. S'il n'y a toujours rien deux jours après l'ajout d'un numéro SH laissé à Peek, celui-ci signale : « En attente de la première mise à jour du transporteur ». Si tu as choisi GLS US et qu'il ne le connaît toujours pas au bout de deux jours sans autre réponse, Peek indique : « Après deux jours, le transporteur ne connaît toujours pas ce numéro. Vérifie-le, choisis un autre transporteur ou supprime le colis. », puis vérifie une fois par jour, et une fois par semaine après sept jours. Vérifie les chiffres dans l'e-mail du magasin, puis demande si le colis a bien été remis.

**Aucune nouvelle depuis plusieurs jours.** Après quatre jours sans nouveau scan, un colis toujours sur « Étiquette créée », « Pris en charge » ou « En transit » reçoit l'alerte « Aucune mise à jour depuis quatre jours ». Peek rappelle que les colis restent souvent calmes le week-end et continue de consulter GLS US. Voir [pourquoi un suivi ne bouge plus](guide:tracking-not-updating).

**Livré, mais rien devant la porte.** `SHIPMENT DELIVERED` sans colis ? Regarde sur le suivi de GLS si quelqu'un a signé, vérifie autour de la porte, la salle courrier, le bureau de gestion et auprès des voisins, puis appelle la ligne destinataires de GLS et préviens le magasin. Voir [colis livré mais non reçu](guide:delivered-not-received).

**Perdu ou abîmé.** Contacte d'abord le magasin qui a réservé la livraison et peut intervenir auprès de GLS US ou te renvoyer l'article. Seul l'expéditeur peut réclamer, dans les 90 jours suivant l'expédition ; GLS s'efforce de traiter les réclamations sous 45 jours. En cas de dommage, GLS demande à l'expéditeur des photos de l'objet, de l'emballage intérieur et du carton : prends-les avant de rien jeter. Voir [colis perdu](guide:lost-parcel) et [colis abîmé](guide:damaged-parcel).

**Retourné.** « Retourné à l’expéditeur » signifie que le colis est reparti chez le magasin. Demande un remboursement ou un nouvel envoi. Voir [retour à l'expéditeur](guide:returned-to-sender).

## Service client et contact GLS US

Sur la [page de support client de GLS US](https://gls-group.com/US/en/support/customer-support/) :

- **Téléphone :** 1-800-322-5555, option 2 (assistance destinataires : suivi, problèmes et aide à la livraison).
- **Horaires :** du lundi au vendredi de 7 h à 19 h, et le samedi de 7 h à 16 h, heure du Pacifique.
- **Chat en direct**, proposé dans la FAQ de GLS. GLS ne communique pas d'adresse e-mail pour les destinataires.

> **Arnaques.** GLS ne demande jamais aux destinataires par e-mail de payer des marchandises par carte, paiement en ligne ou virement, même si pour un colis venant de l'étranger GLS ou son agent peut réclamer droits et taxes. Les vrais sites GLS sont gls-group.com et ceux vers lesquels il renvoie. Signale les faux à security@gls-us.com et lis [arnaques aux SMS de livraison](guide:delivery-scam-texts).

## Suivre un colis GLS US avec Peek

**Un numéro SH.** Colle-le dans le champ ci-dessus. Pendant la recherche, la ligne transporteur indique « Vérification des services de suivi… ». Quand GLS US connaît le numéro, elle affiche GLS US avec « a ce colis » et classe le colis sous GLS US. Si GLS US ne le connaît pas encore, elle indique « pas encore trouvé · Peek continue de vérifier ». Ajoute le colis quand même : il affiche « Probablement GLS US » jusqu'au premier scan.

**Un autre numéro.** Colle-le tout de même : Peek suit plus de 4 500 transporteurs. Choisis ensuite GLS US toi-même :

1. Appuie sur « Changer » à côté de la ligne transporteur et recherche GLS US (« GSO », « Golden State Overnight », « General Logistics Systems US » ou « United States » le trouvent aussi).
2. Choisis GLS US : la ligne indique alors « Choisi par toi ». Lance le suivi.

Sur un colis déjà ajouté, utilise « Changer de transporteur ». Peek n'interroge GLS US que pour des numéros de 5 à 20 lettres et chiffres.

**Ce que tu vois.** Peek interroge le suivi de GLS US avec le numéro seul : sans compte ni code ZIP. Il affiche le statut, chaque scan avec sa ville et son État sur une carte, et — lorsque GLS US en fournit un — le jour de livraison prévu jusqu'à la livraison ou au retour. Il laisse de côté le nom et l'adresse du destinataire, le nom du signataire, la signature, la preuve de livraison et la référence de l'expéditeur. Le code de Peek ne transfère les colis GLS US à aucun autre transporteur. Si GLS US rattache plusieurs envois à un même numéro, Peek suit celui ayant le scan le plus récent. « Ouvrir le site GLS US », sur la fiche du colis, ouvre le numéro directement sur le suivi américain de GLS.

**Horaires.** GLS US fournit l'heure de chaque scan sans fuseau horaire. Peek la lit à l'heure de la ville du scan ou, s'il ne la connaît pas, à celle de l'État (le fuseau principal si l'État en compte plusieurs). Pour un scan sans ville ni État, il reprend le fuseau du scan précédent le plus proche, sinon du suivant, et à défaut l'heure du Pacifique. Peek affiche chaque horaire dans le fuseau horaire de ton appareil.

**Quand GLS US ne répond pas.** Si le suivi de GLS US est injoignable, Peek interroge des [services de suivi](guide:universal-tracker) et affiche leur historique dans leurs propres termes.

**Fréquence.** Tant que tu gardes le colis ouvert, ou le jour de la livraison avec notifications activées, et que le suivi de GLS US répond, Peek vérifie jusqu'à toutes les 10 minutes, ou toutes les 2 minutes dès 6 h heure locale le jour où GLS US met le colis en tournée ; sinon toutes les heures. Via les services de suivi, toutes les heures et jusqu'à toutes les 15 minutes ce jour-là si la fiche est ouverte ou les notifications activées. Après deux jours sans nouvelles (12 heures tant qu'il affiche « Étiquette créée » ou « Pris en charge »), il vérifie toutes les heures. Les notifications couvrent « Chaque scan », « Étapes importantes » ou « Livraison seulement ».

Peek ne peut ni contacter GLS US ou le magasin, ni modifier la livraison, ni déposer de réclamation.

## Questions sur le suivi GLS US

### GSO est-il la même chose que GLS US ?

GLS a racheté Golden State Overnight (GSO) en 2016, et gso.com redirige désormais vers le site de GLS US. Fondée en 1995, GSO livrait en Californie, en Arizona, au Nevada et au Nouveau-Mexique. Peek les traite comme un seul transporteur : son catalogue référence GSO, Golden State Overnight, GLS USA, GLS United States et General Logistics Systems US comme synonymes de GLS US, et lit les liens de gso.com et gls-us.com comme GLS US.

### À quoi ressemble un numéro de suivi GLS US ?

Le format reconnu par Peek est SH suivi de 18 chiffres, tel que `SH605182704936815247` (inventé). Les pages de GLS ne décrivent pas de format et GLS US peut en employer d'autres : colle-les tout de même et choisis GLS US avec « Changer ».

### Que signifie l'arrival scan chez GLS US ?

`ARRIVAL SCAN - DELIVERY SCHED FOR 07/15/2026` signifie que GLS US a scanné le colis à l'arrivée et programmé la livraison à la date finale, mois en premier. Peek affiche « En transit ».

### Pourquoi Peek affiche-t-il « En attente du premier scan de GLS US » ?

Ton numéro correspond au format GLS US et Peek a interrogé GLS US, qui ne le liste pas encore. C'est habituel pour une étiquette récente, et Peek continue de vérifier. S'il reste absent au bout d'une semaine, Peek te le signale et suggère de vérifier le numéro auprès de l'expéditeur ou de choisir un autre transporteur.

### GLS US est-il identique à GLS en Europe ?

Ils appartiennent au même groupe, GLS Group, mais Peek traite GLS US comme un transporteur distinct et interroge le suivi propre à GLS US. Les sociétés européennes de GLS, comme [GLS Germany](carrier:gls-de), disposent de leur propre suivi : suis ton colis avec la filiale GLS mentionnée par le magasin.

:::sources
- [GLS US : Track a parcel](https://gls-group.com/US/en/send-and-receive/track-a-parcel/) – suivi américain, recherche sans numéro, signataire
- [GLS US : About GLS US](https://gls-group.com/US/en/about-gls-us/) – filiale américaine de GLS Group, Ouest américain, histoire de GSO
- [GSO : gso.com](https://www.gso.com/) – redirige vers le site de GLS US
- [GLS US : Domestic shipping services](https://gls-group.com/US/en/Shipping/shipping-services/domestic/) – délais de livraison, samedi dans certaines zones
- [GLS US : Holiday schedule](https://gls-group.com/US/en/resources/holiday-schedule/) – jours de livraison, jours fériés
- [GLS US : Parcel Tariff](https://gls-group.com/US/en/parcel-tariff/) – pas de dimanche, jours fériés, signatures, codes ZIP, une étiquette par carton, réclamations sous 90 jours
- [GLS US : FAQ](https://gls-group.com/US/en/support/faq/) – boîtes postales, réacheminement, reprogrammation, avis sur porte, notifications, chat en direct, réclamations
- [GLS US : Claims](https://gls-group.com/US/en/support/claims/) – réclamation par l'expéditeur, 45 jours, photos
- [GLS US : Customer support](https://gls-group.com/US/en/support/customer-support/) – ligne destinataires, horaires
- [GLS US : Find a GLS Point](https://gls-group.com/US/en/Shipping/shipping-tools/find-drop-point/) – dépôt uniquement
- [GLS US : Security advice](https://gls-group.com/US/en/security-advice/) – demandes de paiement, droits et taxes, sites officiels, signalement
- [Universal Parcel Scraper : GLS US](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/gls-us) – noms, format SH et correspondance faible, liens, scans et statuts globaux, réponse introuvable, jour prévu, plusieurs envois sous un même numéro, fuseaux, données omises
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – réseaux européens GLS et leurs liens, services de suivi en secours, horaires déduits de la ville
:::
