---
title: Suivi PIN AG : recommandés, colis et retrait en dépôt
description: Suivre une lettre ou un colis PIN AG en Allemagne : quels envois sont suivis, signification des statuts allemands, retrait au dépôt et contact.
slug: suivi-pin-ag
published: 2026-11-23
updated: 2026-11-23
---

PIN AG est une entreprise postale basée à Berlin, distincte de Deutsche Post, comptant environ 1 300 salariés. Elle distribue des lettres, des courriers recommandés (Einschreiben), des colis et des commandes Amazon, medimops et rebuy. Seule une partie de ces envois dispose d’un suivi. Suis un envoi sur la [page de suivi de PIN AG](https://sendungsverfolgung.pin-ag.de/) (Sendungsverfolgung) ou colle le numéro dans le champ ci-dessus. Le numéro vient de l’expéditeur : une boutique l’indique dans son e-mail d’expédition, et pour un courrier recommandé il faut le demander à la personne qui l’a envoyé.

## Quels envois PIN AG peut-on suivre ?

PIN ne suit que les envois nécessitant une preuve de distribution, depuis leur prise en charge jusqu’à la livraison. C’est l’expéditeur qui en décide lorsqu’il choisit le produit. Les pages d’aide de PIN indiquent :

| Suivis | Non suivis |
| --- | --- |
| Lettres recommandées : Einschreiben-Einwurf, -Übergabe, -Persönlich, -Rückschein et -Persönlich-Rückschein | Lettres standard (Standardsendungen) |
| Notifications officielles (Postzustellaufträge) : courriers officiels, par exemple d’un tribunal ou d’une administration | Envois de livres et marchandises (Bücher- und Warensendungen) |
| Colis (Pakete) | Commandes medimops et rebuy |
| Articles Amazon Prime, partout en Allemagne | Articles Amazon ordinaires hors de Berlin |
| Articles Amazon dans Berlin | Courrier publicitaire (Werbepost) |

La FAQ de la page de suivi de PIN diverge sur un point : elle indique que les envois Amazon ordinaires (hors Prime) ne peuvent pas être suivis, sans exception pour Berlin. Une commande Amazon ordinaire peut donc n’afficher aucun statut.

## Numéro de suivi PIN AG

Les formats que Peek reconnaît (chiffres inventés) :

| Numéro (inventé) | Ce que c’est |
| --- | --- |
| `0100482917364058` | 16 chiffres commençant par `0100` : un numéro de colis PIN, tel que le lit le code de Peek. Les 16 chiffres correspondent aussi à [DHL eCommerce](carrier:dhl-ecommerce) et à [TNT](carrier:tnt). |
| `4829173640582` | 13 chiffres : une lettre recommandée ou une notification officielle. De nombreux autres transporteurs utilisent aussi 13 chiffres. |
| `482917364058(2)` | Les mêmes 13 chiffres tels qu’une étiquette peut les imprimer, avec le dernier chiffre entre parenthèses. Peek les lit comme `4829173640582`. |

Si l’e-mail de la boutique mentionne [DHL](carrier:dhl) ou un autre transporteur, le numéro lui appartient. Aucun numéro ? Consulte [comment trouver ton numéro de suivi](guide:find-tracking-number).

## Statuts de suivi PIN AG expliqués

La page de suivi de PIN affiche une barre de progression en quatre étapes (`Sortierzentrum`, `Zustelldepot`, `In Zustellung`, puis `Zugestellt`, ou `Unzustellbar` pour un envoi impossible à distribuer) ainsi que le statut en allemand. Leur signification :

| PIN affiche | Ce que ça signifie pour toi |
| --- | --- |
| `Sortierzentrum` | Ton envoi est au centre de tri de PIN. |
| `Zustelldepot` | Il est arrivé au dépôt de distribution de ton secteur. |
| `In Zustellung` | En cours de livraison. PIN distribue généralement le jour même : ses facteurs tournent jusqu’à l’après-midi. En cas d’échec, le facteur retente le lendemain. |
| `Zugestellt`, `Die Sendung wurde zugestellt.` | Distribué. |
| `Unzustellbar` | Impossible à livrer. PIN renvoie les envois non distribuables à l’expéditeur. |
| `Die Sendung wurde in unserem Sortierzentrum erfasst und wird ohne weitere Aktualisierung der Sendungsverfolgung in den kommenden Tagen zugestellt. Hinweis: Bitte beachten Sie, dass es sich um eine Sendung handelt, bei dem der Zustellverlauf nicht dokumentiert wird. Für zusätzliche Informationen schauen Sie gerne in unsere FAQ.` | PIN l’a enregistré au centre de tri et le distribuera dans les prochains jours, sans autre mise à jour : PIN ne documente pas la distribution de ce type d’envoi. |

Ce dernier statut s’affiche à l’étape `In Zustellung`, mais l’envoi n’est pas encore parti en tournée.

## Livraison PIN AG : boîte aux lettres, tiers et dépôt

**Qui livre.** À Berlin, PIN distribue généralement une lettre le lendemain de son passage au centre de tri. Lorsque PIN remet l’envoi à un partenaire de distribution (Zustellpartner) hors de Berlin, le délai peut s’allonger un peu et le statut peut cesser d’évoluer, même pour un recommandé ([pourquoi le suivi ne se met plus à jour](guide:tracking-not-updating)). Un envoi Amazon ordinaire hors de Berlin voyage comme une lettre simple : son statut ne change donc pas du tout.

**Comment.** PIN dépose l’envoi dans ta boîte aux lettres ou te le remet en main propre. Si cela échoue, il peut le confier à un tiers autorisé : un membre de la famille, une personne de ton foyer, un employé de ton entreprise ou le propriétaire ou bailleur du logement. Cela n’a pas lieu si l’envoi doit t’être remis en main propre ou si toi ou l’expéditeur l’avez interdit. Les lettres recommandées sont distribuées selon les règles de leur formule. PIN ne peut pas livrer en Packstation.

**Retrait au dépôt.** Quand PIN ne peut pas livrer, il conserve l’envoi dans un dépôt PIN (Zustelldepot) ou chez un partenaire et te prévient avec un avis de passage (Benachrichtigungsschein).
- Présente-toi dès le jour ouvré suivant pendant les horaires d’ouverture. L’avis indique l’adresse et les horaires du dépôt, tout comme la page de suivi de PIN lorsque tu saisis ton numéro.
- Apporte une pièce d’identité officielle avec photo, obligatoire, ainsi que l’avis. Pas d’avis ? Il te faut alors le numéro de suivi et le jour de la tentative de livraison.
- Une autre personne peut le retirer avec la procuration figurant sur l’avis (Vollmacht) et sa propre pièce d’identité avec photo.
- PIN garde l’envoi pendant 7 jours ouvrables (du lundi au samedi), comptés à partir du lendemain de la tentative. Une notification officielle attend 3 mois et est réputée remise le jour de son dépôt à l’agence.
- Passé ce délai, l’envoi repart automatiquement chez l’expéditeur.

## Envoi PIN AG en retard, disparu ou retourné

Pour un remplacement ou un remboursement, ton interlocuteur est l’expéditeur, et PIN précise que c’est la voie la plus rapide.

- **Rien après 8 jours ouvrables.** Compte du lundi au samedi depuis le jour d’envoi. Demande un nouvel envoi ou un remboursement à l’expéditeur. Tu peux aussi transmettre une demande de recherche (Nachforschungsauftrag) via le formulaire en ligne de PIN. Une recherche peut prendre jusqu’à 21 jours et ne garantit pas de retrouver l’envoi. Selon les conditions générales de PIN, un envoi est présumé perdu s’il n’est pas livré sous 20 jours après la remise et reste introuvable. Voir [colis perdus](guide:lost-parcel).
- **`In Zustellung` depuis plus de trois jours.** PIN t’invite à le contacter via le formulaire lié sur sa page de suivi.
- **Livré mais absent.** Demande aux personnes de ton foyer : PIN peut remettre l’envoi à un tiers. Pour les envois Amazon, PIN indique que sa page de suivi montre où l’article a été remis, par exemple chez un voisin, en saisissant le numéro et ton code postal. Une erreur technique peut aussi afficher un envoi livré trop tôt : attends un jour, car il arrive généralement le lendemain. Plus de détails dans [colis indiqué livré mais non reçu](guide:delivered-not-received).
- **Endommagé.** Signale-le à PIN avec le formulaire « Hinweis zur Briefzustellung », qui sert aussi pour le courrier en retard, mal distribué ou irrégulier. PIN ne répond que dans certains cas. Si le contenu est endommagé, contacte l’expéditeur.
- **Retourné.** PIN renvoie les envois s’il n’y a ni boîte aux lettres nominative ni fente de porte, si personne ne les retire à temps ou s’ils sont trop abîmés pour être distribués. Les commandes Amazon repartent aussi vers Amazon si l’adresse est erronée ou incomplète, si ton nom ne figure pas sur la boîte, si l’envoi ne respecte pas le format lettre convenu avec Amazon ou s’il est adressé à une Packstation. PIN ne peut pas modifier l’adresse en cours de distribution : demande une nouvelle livraison à Amazon. Voir [retourné à l’expéditeur](guide:returned-to-sender).

## Contact et service client PIN AG

Peek ne peut pas contacter PIN AG ni l’expéditeur à ta place. Coordonnées publiées par PIN AG :

- **ServiceCenter :** 030 577978-800, du lundi au vendredi de 8 h à 18 h (heure allemande).
- **Formulaires en ligne :** demande de recherche (Nachforschungsauftrag) et signalement de distribution (Hinweis zur Briefzustellung), tous deux liés depuis les pages d’aide de PIN, ainsi que les formulaires de contact liés sur la page de suivi.
- **Siège social :** PIN AG, Alt-Moabit 91, 10559 Berlin. Téléphone 030 577978-0, e-mail info@pin-ag.de.

## Suivre un envoi PIN AG avec Peek

Colle le numéro dans [Peek](/), avec ou sans parenthèses : Peek rattache automatiquement un dernier chiffre entre parenthèses aux autres.

- **16 chiffres commençant par 0100.** D’autres transporteurs utilisant aussi 16 chiffres, Peek interroge les plus probables selon ton pays (la région de ton téléphone, ou sur le web le fuseau horaire de ton navigateur). En Allemagne, il interroge PIN AG en premier. Pendant la recherche s’affiche « Vérification des services de suivi… ». Si PIN a l’envoi, tu vois « PIN AG a ce colis. » et Peek le classe sous PIN AG. Hors d’Allemagne, Peek n’interroge PIN AG qu’une fois que les autres transporteurs ont manqué le numéro.
- **Pas encore enregistré.** Ajoute-le quand même. En Allemagne, une fois que Peek a interrogé PIN sans réponse pour l’instant, l’envoi affiche « En attente du premier scan de PIN AG » et « Probablement PIN AG », et Peek continue d’interroger PIN. Après une semaine, Peek t’invite à vérifier le numéro auprès de l’expéditeur ou à choisir un autre transporteur.
- **13 chiffres.** Peek ne peut pas identifier PIN à cette seule longueur. Colle-le tout de même : Peek suit plus de 4 500 transporteurs. Appuie sur « Changer » et choisis PIN AG (saisis PIN). Sur un colis déjà ajouté, utilise « Changer de transporteur ».
- **Un lien vers la page de suivi de PIN** contenant ton numéro identifie immédiatement PIN AG (« Transporteur détecté »).

**Ce que Peek affiche.** Le statut de PIN et chaque étape avec son horaire, dans les mots allemands de PIN et à l’heure allemande. `Sortierzentrum`, `Zustelldepot` et le statut sans mises à jour ultérieures apparaissent comme « En transit », `In Zustellung` comme « En livraison », `Zugestellt` comme « Livré » et `Unzustellbar` comme « Problème signalé ». Un envoi qui t’attend au dépôt (la page de PIN affiche alors l’adresse et les horaires du dépôt) apparaît comme « Prêt à être retiré », quelle que soit l’étape affichée par PIN. PIN ne fournit ni lieux de scan, ni date de livraison prévue, ni poids, ni nom de signataire. Peek exclut ton adresse et celle du dépôt : pour voir le dépôt, utilise « Ouvrir le site PIN AG », qui ouvre ton numéro sur la page de suivi de PIN. Une lettre standard n’ayant pas de suivi chez PIN, Peek ne peut pas la suivre non plus.

Tant que tu gardes le colis ouvert dans Peek, ou le jour de livraison si les notifications sont activées, Peek vérifie jusqu’à toutes les 10 minutes, et à partir de 6 h (heure allemande) le jour où PIN passe l’envoi en livraison jusqu’à toutes les 2 minutes tant que le suivi propre de PIN répond. Après deux jours sans nouvelle (12 heures tant que l’envoi reste à « Étiquette créée » ou « Pris en charge »), Peek vérifie toutes les heures ; et s’il reste à « Étiquette créée » pendant sept jours, toutes les 6 heures.

Si un envoi encore à « Étiquette créée », « Pris en charge » ou « En transit » reste quatre jours sans nouveau scan, Peek le signale par « Aucune mise à jour depuis quatre jours » (ou « Aucune mise à jour depuis … »), explique qu’« Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger PIN AG. » et propose « Demande de l’aide à PIN AG ». Tu peux choisir les notifications « Chaque scan », « Étapes importantes » ou « Livraison seulement ».

## Questions sur le suivi PIN AG

### Pourquoi ne puis-je pas suivre ma lettre PIN AG ?

PIN suit les lettres recommandées, les notifications officielles, les colis et les envois Amazon Prime, et ses pages d’aide ajoutent les envois Amazon à Berlin. Les autres lettres, comme les lettres standard, n’ont pas de suivi : c’est le choix de l’expéditeur. PIN prévoit qu’une lettre standard arrive dans les prochains jours ouvrés.

### Combien de temps PIN AG met-elle pour livrer ?

À Berlin, généralement un jour après le traitement au centre de tri de PIN. Lorsqu’un partenaire de distribution prend le relais hors de Berlin, cela peut prendre un peu plus de temps. Si rien n’est arrivé 8 jours ouvrables (du lundi au samedi) après l’envoi, contacte l’expéditeur.

### Combien de temps PIN AG garde-t-elle ma lettre au dépôt ?

7 jours ouvrables (du lundi au samedi), comptés à partir du lendemain de la tentative de livraison. Les notifications officielles attendent 3 mois. Ensuite, l’envoi repart chez l’expéditeur.

### Pourquoi le suivi PIN AG ne trouve-t-il aucun statut pour mon numéro ?

La FAQ de PIN indique le message `Für die Sendungsnummer konnte kein Zustellstatus gefunden werden.` L’envoi appartient peut-être à une catégorie non suivie par PIN, comme une lettre standard. Le numéro peut contenir une faute de frappe ou des parenthèses. Ou bien l’envoi a plus de six mois : PIN supprime les données de suivi au bout de six mois pour la protection des données.

### PIN AG livre-t-elle en Packstation ?

Non, PIN AG ne peut pas livrer en Packstation. Amazon confie normalement ces commandes à Deutsche Post ; si PIN en reçoit malgré tout une, elle la renvoie à Amazon.

:::sources
- [PIN AG: Sendungsverfolgung](https://sendungsverfolgung.pin-ag.de/) – étapes de progression ; FAQ : envois suivis, aucun statut trouvé, livraison le jour même, nouvelle tentative le lendemain, formulaires de contact
- [PIN AG: Häufige Fragen zum Empfang von Briefsendungen](https://pin-ag.de/servicecenter/post-empfangen/sonstige-sendungen) – envois suivis, délais de livraison, 8 jours ouvrables, demande de recherche, retrait au dépôt, retours, formulaires
- [PIN AG: Häufige Fragen zum Empfang von Amazon-, medimops- und rebuy-Sendungen](https://pin-ag.de/servicecenter/post-empfangen/amazon-sendungen) – envois Amazon, livré mais absent, retours, Packstation
- [PIN AG: ServiceCenter](https://pin-ag.de/servicecenter) – numéro de téléphone et horaires
- [PIN AG: Allgemeine Geschäftsbedingungen](https://pin-ag.de/agb) – boîte aux lettres, tiers, dépôt, envois non distribuables, perte après 20 jours
- [PIN AG: Das Unternehmen](https://pin-ag.de/unternehmen) – distribution de courrier, environ 1 300 salariés, Berlin
- [PIN AG: Impressum](https://pin-ag.de/impressum) – adresse du siège social, téléphone et e-mail
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – ce que Peek lit, étapes et libellés de PIN, formats de numéros
:::
