---
title: Suivi nox NachtExpress : statuts, dépôt et contact
description: Suivre un envoi nox NachtExpress : format du code-barres, signification des statuts allemands, livraison de nuit au lieu de dépôt, délais et contact.
slug: suivi-nox
published: 2026-11-23
updated: 2026-11-23
---

nox (nox NachtExpress) est un transporteur express de nuit allemand, nox Germany GmbH, basé à Langenfeld. Il achemine des pièces détachées et d’autres marchandises urgentes pour les entreprises. Il les collecte l’après-midi et les livre pendant la nuit, en règle générale avant 8 h (heure allemande), dans un lieu convenu avec le destinataire : un coffre, un garage, un atelier ou le coffre d’un utilitaire de dépannage. Suis un envoi avec son code-barres sur le [suivi de nox](https://trackandtrace.app.nox.express/scannungen) ou colle le numéro dans le champ ci-dessus. Le suivi est en allemand : les tableaux ci-dessous l’expliquent.

## Code-barres nox : format et où le trouver

nox appelle son numéro de suivi un « barcode » (code-barres). Chaque colis doit porter une étiquette nox, créée par l’expéditeur au moment de réserver l’envoi : l’expéditeur dispose donc du code-barres. Pas de numéro ? Demande à l’expéditeur, ou consulte [comment trouver ton numéro de suivi](guide:find-tracking-number). Les formats reconnus dans le code de Peek :

| Code-barres (inventé) | Ce que c’est |
| --- | --- |
| `220004829173640715482917` | 24 chiffres commençant par 22000 : code client, 000, numéro client de l’expéditeur, date d’impression de l’étiquette et numéro de série. Le catalogue de Peek reconnaît cette forme. [Ciblex](carrier:ciblex), [InPost](carrier:inpost), [bpost](carrier:bpost) et la [Poste autrichienne](carrier:austrian-post) utilisent aussi 24 chiffres. |
| `SWR482917364058291736405` | SWR suivi de 21 chiffres : un ancien code-barres nox. |
| `RA1482917364058482917364` | RA, puis 1 ou 2 (type de collecte), 12 lettres ou chiffres pour les clients et une série de 9 chiffres : un code-barres de retour. |
| `C48291736405829173640582917364` | C suivi de 29 chiffres : le propre code-barres d’un expéditeur, auquel le suivi de nox répond aussi. |
| `trackandtrace.app.nox.express/scannungen/SWR482917364058291736405` | Un lien vers un envoi sur le suivi de nox. Colle le lien entier dans Peek. |

D’autres codes-barres nox comptent 24 chiffres avec un autre code client à deux chiffres devant 000. Dans Peek, les espaces n’importent pas et les minuscules sont acceptées.

## Statuts de suivi nox expliqués

Le suivi de nox affiche chaque scan avec `Status`, `Scanort` (lieu), `Bemerkung` (remarque), `Datum` (date) et `Uhrzeit` (heure). Dans la terminologie de nox, Collo désigne un colis de ton envoi et Niederlassung une agence nox.

| nox affiche | Ce que ça signifie pour toi | Statut Peek |
| --- | --- | --- |
| `Collo durch Versender bereitgestellt zur Abholung` | L’expéditeur tient le colis prêt pour la collecte par nox. nox ne l’a pas encore. | « Étiquette créée » |
| `Collo in der nox Niederlassung eingetroffen` | Le colis est arrivé dans une agence nox. | « En transit » |
| `Collo von der nox Niederlassung weiterverladen` | L’agence l’a rechargé vers une autre agence. | « En transit » |
| `Collo steht am Lager in nox Niederlassung, Zustellung nicht im geplanten Zeitfenster` | Il attend à l’agence et n’a pas été livré dans le créneau prévu. Il repartira une nuit suivante. | « En transit » |
| `Collo in nox Zustellfahrzeug geladen` | Il est chargé dans le véhicule de livraison. | « En livraison » |
| `Collo von nox zugestellt` | Livré : en général déposé au lieu convenu, sans signature. | « Livré » |

Si le suivi indique `Der von Ihnen angegebene Barcode ist uns nicht bekannt.`, nox ne connaît pas ce code-barres : vérifie-le auprès de l’expéditeur. Pour plus de détails ou pour déposer une réclamation, le suivi te demande de valider le code postal du destinataire : clique sur `PLZ validieren/Reklamation erfassen`.

## Comment nox livre : la nuit, à ton lieu de dépôt

**Quand.** nox collecte généralement chez l’expéditeur entre 14 h et 18 h (heure allemande). Selon les conditions générales de nox, NachtExpress livre le lendemain, du mardi au samedi, jours fériés inclus, en règle générale avant 8 h (heure allemande). L’expéditeur peut réserver une livraison avant 7 h ou avant 6 h (heure allemande). nox propose aussi une livraison le week-end et les jours fériés en option dédiée (WochenendService). Pour les colis de plus de 100 kg, ou dépassant 3,20 m de long, 1,20 m de large ou 1,50 m de haut, l’heure de livraison peut varier. Les envois vers les îles de la mer du Nord et de la mer Baltique n’ont pas d’horaire garanti.

**Où.** nox livrant alors que beaucoup de ses clients dorment encore, le colis est déposé dans un lieu convenu à l’avance, généralement sans signature. Exemples cités par nox :

- un coffre dédié sur le site de ton entreprise ;
- l’atelier, la station de lavage, la salle de pause ou le garage ;
- le coffre du véhicule d’un technicien de service ;
- le gardien, qui reçoit le colis en main propre du chauffeur ;
- l’une des stations partenaires de nox accessibles 24 h/24, comme une station-service ouverte en continu.

**Définir ton lieu de dépôt.** Enregistre-le ou modifie-le à tout moment, avec photos et description, dans l’outil en ligne [ready2nox](https://ready2nox.de/). D’après les conditions de nox, c’est le seul moyen de le définir, et l’emplacement reste valable tant que tu ne le modifies pas là-bas. Dès que le colis y est déposé, il est considéré comme livré.

**Sans lieu de dépôt.** Selon les conditions de nox, nox peut alors laisser l’envoi à ton adresse. Si le risque de perte est manifestement trop élevé, nox peut ne pas livrer et renvoyer le colis à l’expéditeur.

**Retrait en agence.** En service supplémentaire, tu peux retirer tes envois directement à l’agence nox, où ils sont conservés pour toi.

**Preuve.** Quand personne n’est présent, nox photographie le colis et l’endroit où il a été déposé, puis conserve ces photos comme preuve. nox peut communiquer une photo au cas par cas à une personne justifiant d’un intérêt légitime, sans droit légal automatique à l’obtenir.

**Hors d’Allemagne.** nox est présent dans 20 autres pays européens via des sociétés sœurs et des partenaires. Expédier à l’étranger nécessite l’accord préalable de l’expéditeur avec nox, et les règles de réclamation peuvent varier selon le pays.

## Envoi nox en retard, endommagé ou manquant

Pour un remplacement, l’entreprise qui a expédié la marchandise est ton interlocuteur.

**Immobilisé.** Si `Collo durch Versender bereitgestellt zur Abholung` reste l’unique scan, nox n’a pas encore collecté le colis : demande à l’expéditeur. S’il attend en agence, il partira une nuit suivante (voir le tableau ci-dessus). En cas de retard, utilise le suivi nox : valide ton code postal puis choisis `Laufzeitverzug` (retard) ou `Sonstige Anliegen` (autre). Voir aussi [pourquoi le suivi ne se met plus à jour](guide:tracking-not-updating).

**Livré mais absent.** Vérifie d’abord le lieu de dépôt convenu, puis les autres endroits utilisés par ton entreprise. Demande au gardien ou au technicien dont le véhicule a pu le recevoir. Préviens ensuite nox rapidement, avant la limite ci-dessous. Plus dans [colis indiqué livré mais non reçu](guide:delivered-not-received).

**Endommagé.** Pour NachtExpress, nox doit recevoir toute réclamation et déclaration de dommage avant 12 h (heure allemande) le jour de la livraison, par écrit et avec description. Si ce jour est un samedi, un dimanche ou un jour férié, la limite est 12 h (heure allemande) le jour ouvré suivant. Une réserve générale ne suffit pas, et passé ce délai les conditions de nox excluent sa responsabilité. Conserve la marchandise dans son emballage d’origine afin que nox puisse l’inspecter et la reprendre, sans quoi nox n’est pas responsable. Signale-le sur le suivi après validation du code postal (formulaire `Reklamation einer Beschädigung`), via le formulaire de contact de nox ou sur le portail client. Voir [colis endommagés](guide:damaged-parcel).

**Douane.** Les pages de nox ne mentionnent la douane que comme service proposé à ses clients pour l’import et l’export. Renseigne-toi auprès de l’expéditeur, et consulte [colis retenus en douane](guide:customs).

## Contact et service client nox

Peek ne peut pas contacter nox ni l’expéditeur à ta place. Coordonnées publiées sur les sites de nox :

- **Service client :** +49 (0) 4224 920 000, du lundi au vendredi de 7 h à 17 h (heure allemande), ou customerservice@nox-nachtexpress.de, indiqués sur le suivi de nox.
- **Formulaire de contact :** [formulaire de contact de nox](https://www.nox.eu/de/de/kontakt/kontaktformular/), pour questions et réclamations.
- **Réclamations :** sur le suivi, après validation du code postal. Ses formulaires (retard, dommage, autre) demandent si tu es le destinataire (Empfänger), l’expéditeur (Versender) ou un tiers donneur d’ordre.
- **Société :** nox Germany GmbH, Katzbergstr. 3, 40764 Langenfeld, Allemagne ; welcome@nox.eu.

Pour suivre tous ses envois, une entreprise doit disposer d’un compte sur le portail client nox : demande-le via le formulaire de contact.

## Suivre un envoi nox avec Peek

Peek ne peut pas identifier nox au seul vu du numéro : ses codes-barres `22000` partagent leur longueur avec Ciblex, InPost, bpost et la Poste autrichienne, et Peek n’associe pas ses autres codes-barres à nox par la forme. Colle-le quand même : Peek suit plus de 4 500 transporteurs. Lors de l’ajout d’un code-barres `22000`, Peek interroge les transporteurs utilisant ce format, en commençant par ceux de ton pays (« Vérification des services de suivi… »), et affiche « nox a ce colis. » lorsque le suivi nox le connaît. Si plusieurs le connaissent, tu choisis.

Si aucun ne répond encore, l’envoi affiche « Aucun historique de suivi pour le moment » pendant que Peek continue de vérifier, ou « En attente de nox » dès que tu as choisi nox. Pour le choisir toi-même, appuie sur « Changer » et sélectionne nox : il figure sous « Correspond à ce numéro » pour un code-barres `22000` ; pour un code-barres SWR, RA ou C, recherche-le (NOX Nachtexpress, Innight Express et TNT Innight le trouvent aussi). Sur un colis déjà ajouté, utilise « Changer de transporteur ». Si tu sélectionnes nox pour un numéro d’une autre forme, Peek indique qu’il « n’émet pas de numéros comme celui-ci » et n’interroge pas nox : une fois ajouté, sauf si un service de suivi connaît l’envoi, il affiche « Numéro de suivi à vérifier ». Un lien venant du suivi nox, sur `trackandtrace.app.nox.express` ou `tcs.app.nox.express`, classe aussitôt l’envoi sous nox.

Peek lit le statut, le libellé allemand et l’heure de chaque scan (à l’heure allemande) ainsi que l’heure de livraison. Il omet le lieu du scan et la remarque, et ne demande aucun code postal, donc il n’affiche pas ce que le suivi dévoile après validation du code postal. Peek n’affiche pas de date de livraison prévue, de poids, de lieu de dépôt, de photo de livraison ni de nom de signataire.

Jour et nuit, tant que tu gardes l’envoi ouvert dans Peek ou le jour de livraison si les notifications sont activées, Peek vérifie jusqu’à toutes les 10 minutes, et à partir de 6 h (heure allemande) le jour où nox scanne l’envoi en livraison jusqu’à toutes les 2 minutes tant que le suivi propre de nox répond. Après deux jours sans nouvelle (12 heures tant que l’envoi reste à « Étiquette créée » ou « Pris en charge »), Peek vérifie toutes les heures ; et s’il reste à « Étiquette créée » pendant sept jours, toutes les 6 heures.

Si un envoi encore à « Étiquette créée », « Pris en charge » ou « En transit » reste quatre jours sans nouveau scan, Peek le signale par « Aucune mise à jour depuis quatre jours » (ou « Aucune mise à jour depuis … »), explique qu’« Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger nox. » nox n’ayant pas de page d’aide dans Peek, la note ne propose aucun lien d’aide. Peek peut t’alerter pour « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Il ne peut pas définir ton lieu de dépôt ni déposer une réclamation à ta place.

## Questions sur le suivi nox

### Qu’est-ce que nox NachtExpress ?

Le service express de nuit de nox Germany GmbH, basée à Langenfeld. Les marchandises collectées en début de soirée parviennent au destinataire avant le début de la journée de travail suivante. Ses secteurs clés sont l’automobile, les véhicules utilitaires, les engins de chantier et l’agriculture, avec des solutions dédiées à la santé.

### Quand nox livre-t-elle ?

Pendant la nuit, en règle générale avant 8 h (heure allemande), du mardi au samedi, jours fériés inclus. L’expéditeur peut réserver une livraison avant 7 h ou 6 h (heure allemande).

### Où nox dépose-t-elle mon envoi ?

Au lieu de dépôt convenu pour ton adresse, défini dans l’outil ready2nox de nox. Sans lieu défini, nox peut le laisser à ton adresse.

### nox demande-t-elle une signature ?

En général non : nox livre sans récépissé, souvent en l’absence de personnel, puis photographie le colis à l’emplacement de dépôt. Auprès d’un gardien, le chauffeur remet le colis en main propre.

### Comment signaler une livraison nox endommagée ?

Pour une livraison NachtExpress, avant 12 h (heure allemande) le jour de la livraison, par écrit, via le suivi ou le formulaire de contact de nox. Conserve la marchandise et l’emballage d’origine. Si la livraison tombe un week-end ou un jour férié, tu as jusqu’à 12 h (heure allemande) le jour ouvré suivant.

:::sources
- [nox: Sendungsverfolgung/Reklamation erfassen](https://www.nox.eu/de/de/kundenbereich/sendungsverfolgung/) – formulaire code-barres ouvrant le suivi nox, portail client pour tous les envois
- [nox: Track & Trace](https://trackandtrace.app.nox.express/scannungen) – colonnes du suivi, code-barres inconnu, validation du code postal, formulaires de réclamation, téléphone, horaires et e-mail du service client, ready2nox
- [nox: Hinweise zum Nachtversand](https://www.nox.eu/de/de/kundenbereich/hinweise-zum-nachtversand/) – collecte de 14 h à 18 h, livraison avant 8 h, lieux de dépôt, gros colis, 20 autres pays, réclamations via le formulaire de contact, délai en cas de dommage
- [nox: NachtExpress](https://www.nox.eu/de/de/leistungen/nachtexpress/) – avant 8 h, 7 h ou 6 h, livraison sans récépissé, exemples de lieux de dépôt, retrait en agence, service douanier
- [nox: Abstellplatzmanagement](https://www.nox.eu/de/de/leistungen/abstellplatzmanagement/) – exemples de lieux de dépôt, ready2nox avec photos et descriptions
- [nox: Über uns](https://www.nox.eu/de/de/ueber-uns/) – livraison avant le début du travail, clients endormis, secteurs clés
- [nox: Allgemeine Geschäftsbedingungen, from 1 September 2026 (PDF)](https://cms.nox.eu/de/wp-content/uploads/sites/4/2026/09/Allgemeine-Geschaftsbedingungen-fur-Geschaftskunden-nox-DE-Sept-2026-Final.pdf) – étiquettes nox, jours de livraison, lieu de dépôt défini dans ready2nox, livraison sans lieu défini, photos, îles, envois internationaux, délai de réclamation, règles par pays
- [nox: Allgemeine Geschäftsbedingungen](https://www.nox.eu/de/de/agb/) – conditions générales en vigueur
- [nox: Kontakt](https://www.nox.eu/de/de/kontakt/kontaktformular/) – formulaire de contact
- [nox: Impressum](https://www.nox.eu/de/de/impressum) – nox Germany GmbH, Langenfeld, téléphone, e-mail
- [Universal Parcel Scraper: nox](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/nox) – formats de codes-barres, liens de suivi, libellés et étapes des scans, heure allemande, ce que Peek lit
:::
