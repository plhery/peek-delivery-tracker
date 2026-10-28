---
title: GLS Suisse suivi colis : statuts, La Poste et contact
description: Suivre un colis GLS en Suisse romande : le numéro, le code postal demandé, les statuts en anglais, la livraison par La Poste et qui contacter.
slug: gls-suisse-suivi-colis
published: 2026-10-11
updated: 2026-10-27
---

GLS est un réseau international de colis basé à Amsterdam, sans société à lui en Suisse : c'est son partenaire, La Poste, qui dédouane et livre les colis GLS qui arrivent ici. Pour suivre le tien, saisis son numéro puis ton code postal sur [la page de suivi de GLS](https://gls-group.com/EU/en/parcel-tracking), ou colle le numéro dans le champ ci-dessus. Ce numéro vient de l'expéditeur : cherche-le d'abord dans l'e-mail d'expédition de la boutique ou sur la page de ta commande.

## Numéro de colis GLS : formes et Track ID

Le suivi de GLS accepte des numéros de colis et des Track ID. GLS imprime le Track ID sur ses avis de passage, mais en Suisse c'est La Poste qui livre, et aucune page ne montre d'avis GLS ici. Ni GLS ni La Poste ne publient de format. Les formes que Peek attribue à GLS, avec des numéros inventés :

| Numéro (inventé) | Ce que c'est |
| --- | --- |
| `12345678901` | 11 chiffres. Même forme chez GLS Germany, [GLS France](carrier:gls-fr), PostLogistics et DHL eCommerce Poland. |
| `000000000009` | 12 chiffres, dont le dernier est une clé de contrôle. Même forme chez BRT, Colis Privé, Mondial Relay, [DPD France](carrier:dpd-fr) et d'autres. |
| `ABCD1234` | 8 lettres et chiffres. Même forme chez GLS Germany et GLS France. |
| Six lettres | Comme `ABCDEF`. Même forme chez GLS Germany. |

Copie-colle le numéro plutôt que de le taper : si le dernier chiffre d'un numéro de 12 chiffres est faux, ou s'il manque un chiffre à un numéro de 11 chiffres, il ne correspond plus à GLS. Si l'e-mail de la boutique nomme un autre transporteur, le numéro est le sien. Pas de numéro ? Lis [où trouver son numéro de suivi](guide:find-tracking-number).

## Suivi GLS Suisse : ce que veulent dire les statuts

Pour les colis à destination de la Suisse, le suivi de GLS n'existe qu'en anglais : sa page ne propose aucune autre langue, et GLS ne publie pas de liste de statuts pour la Suisse. Voici les libellés de son suivi, dans l'ordre où un colis les rencontre :

| Ce qu'affiche GLS | Ce que ça veut dire pour toi |
| --- | --- |
| `Preadvice`, `The parcel has not been handed over to GLS.` | L'expéditeur a saisi les données du colis, mais GLS ne l'a pas encore. |
| `The parcel was handed over to GLS.` | GLS a reçu le colis. |
| `In transit`, `The parcel has left the parcel center` | Il roule entre deux dépôts GLS. |
| `The parcel has reached the parcel center` | Il est arrivé dans un dépôt GLS. |
| `Customs Consignment via Customs Portal`, `The parcel has not been released by customs.` | Il est en douane. Pour la Suisse, c'est La Poste qui dédouane les colis GLS. |
| `The parcel was released by customs.` | Dédouané, il reprend la route. |
| `Final parcel center` | Il est au dernier centre : la livraison suit. |
| `In delivery`, `The parcel is in delivery.` | Il est en livraison, attendu dans la journée. |
| `Not delivered` | Pas livré, par exemple parce que personne n'était là ou qu'il manque des précisions sur l'adresse. |
| `Stored` | Entreposé au dépôt après une livraison manquée, par exemple parce que personne n'était là. |
| `The parcel is ready for collection at the ParcelShop.` | Il t'attend dans un ParcelShop ou une consigne GLS. |
| `The parcel has been delivered.`, `Delivered` | Livré. |

`Preadvice` veut seulement dire que la boutique a envoyé les données à GLS : s'il dure des jours, demande-lui où en est ton colis. GLS écrit aussi `Delivered` pour un colis déposé dans un ParcelShop, que tu dois encore aller retirer. Aucune page de GLS n'explique `Customs Consignment via Customs Portal` ; Peek le compte comme douane. Les raisons données pour `Not delivered` et `Stored` viennent de l'aide de GLS aux Pays-Bas, où GLS livre lui-même ; en Suisse, c'est La Poste qui livre.

## Livraison GLS en Suisse : La Poste, point relais et invitation à retirer

**Qui livre.** Le partenaire de GLS pour la Suisse et le Liechtenstein est Swiss Post GLS, une unité de La Poste à Bâle qui sert surtout aux entreprises suisses pour « l'expédition des colis dans l'espace européen ». Dans l'autre sens, les colis GLS qui arrivent en Suisse passent dans le réseau de La Poste, qui les dédouane puis les livre. La Poste précise que des différences sont possibles pour les envois qu'elle reçoit du réseau GLS.

**Point relais GLS en Suisse.** Aucune page de GLS ne nomme de ParcelShop ni de consigne GLS en Suisse.

**Livraison manquée.** GLS ne publie rien pour la Suisse sur les tentatives, l'avis de passage ou la livraison le samedi. Si La Poste te laisse une invitation à retirer un envoi, voici ses règles pour ses propres envois (aucune de ses pages ne cite les colis GLS) :

- Retire le colis à l'endroit indiqué, avec l'invitation ou le code QR de la Post-App. Prolonger le délai est gratuit et possible sans compte.
- « En général, les colis peuvent être retirés pendant 7 jours, ou 15 jours pour les envois pour l'étranger », écrit sa page française ; les versions anglaise et allemande parlent d'envois internationaux, l'italienne d'envois venant de l'étranger.
- Avec un login pour « Mes envois » : deuxième distribution à la même adresse (dépôt ou voisin gratuits, à l'étage CHF 3.30, le samedi CHF 7.–), réexpédition vers un automate My Post 24, une autre adresse en Suisse ou au Liechtenstein, une filiale ou un point PickPost (CHF 8.– en semaine, CHF 15.– le samedi), ou procuration unique et gratuite pour qu'une autre personne le retire.

## Colis GLS pas reçu, bloqué en douane ou abîmé

C'est l'expéditeur qui a choisi GLS, et « les frais de transport sont dans tous les cas à la charge de l'expéditeur », rappelle La Poste sur ses pages GLS : la boutique est ton premier contact.

**Pas de nouvelles.** Un `Preadvice` qui dure : la boutique n'a pas encore remis le colis à GLS. Un statut de douane : il attend le dédouanement de La Poste. Aucun historique : vérifie le code postal, sans lequel GLS n'affiche pas le détail.

**Frais de douane.** Tout dépend de l'Incoterm GLS choisi par l'expéditeur. Avec l'Incoterm 20, le dédouanement et les taxes publiques (droits de douane, TVA) sont imputés à l'importateur, donc à toi. Avec les Incoterms 30 et 40, « l'exportateur et l'importateur se partagent ces coûts », et il te reste au moins la TVA. La Poste applique aux colis GLS ses prix de dédouanement habituels : voir [colis bloqué en douane](guide:customs). Pas d'accord avec la facture ? Appelle le service à la clientèle international de La Poste au +41 848 48 48 47, dans les délais de recours de l'OFDF : 60 jours après l'établissement de la décision de taxation pour la procédure, l'adresse ou les droits de douane, 5 ans après la fin de l'année civile où elle a été établie pour des impôts à l'importation prélevés par erreur.

**Perdu ou abîmé.** GLS ne publie pour la Suisse ni démarche, ni délai, ni plafond d'indemnisation : contacte la boutique.

## GLS Suisse contact : téléphone et e-mail

GLS n'a pas de hotline suisse. Sa page de contact renvoie vers le site de la société GLS de ton pays, qui pour la Suisse et le Liechtenstein est la page de son partenaire Swiss Post GLS. Relevé le 10 octobre 2026 :

- **Téléphone :** +41 848 48 48 47, la ligne Importation de Swiss Post GLS, CHF 0.08 la minute depuis le réseau fixe suisse, du lundi au vendredi de 8 h à 12 h et de 13 h 30 à 17 h 30. La Poste l'indique sous « GLS (colis) » sur ses pages de dédouanement pour les entreprises, pas comme une ligne de suivi.
- **E-mail :** infogls@poste.ch en français, ou infogls@post.ch, infogls@posta.ch ou infogls@swisspost.ch.
- **Courrier :** Swiss Post GLS, Post-Passage 11, 4002 Bâle.
- **Groupe GLS :** un [formulaire de contact](https://gls-group.com/GROUP/en/contact-form/), en anglais.

Au Liechtenstein, c'est le même partenaire, avec les mêmes contacts.

> GLS met en garde contre les faux e-mails, SMS, sites et livreurs. La Suisse n'étant pas dans l'UE, GLS ou un agent autorisé peut vraiment te demander de payer des droits et taxes par e-mail, SMS ou courrier ; mais GLS ne demande jamais par e-mail de payer la marchandise par carte, paiement en ligne ou virement. Ne paie pas un colis contre remboursement que tu n'attends pas. Le site principal de GLS est gls-group.com ; signale les faux à security@gls-group.com.

## Suivre un colis GLS Suisse avec Peek

[Peek](/) reconnaît les quatre formes et interroge les transporteurs probables. GLS Germany et GLS Switzerland connaissent les mêmes numéros, alors c'est ton pays qui tranche : la région de ton iPhone ou, sur le web, le pays d'où tu te connectes. En Suisse ou au Liechtenstein, tu verras en général « GLS Switzerland a ce colis » ; pour un colis livré en Allemagne, touche « Changer » et choisis GLS Germany. Ailleurs, ce sera GLS Germany, qui suit tout aussi bien les colis pour la Suisse avec le code postal suisse. Tu peux aussi coller un lien de suivi gls-group avec /EU/ ou /GROUP/ : le suivi passe alors par GLS Germany.

GLS n'affiche son historique qu'avec le code postal : le champ « Code postal de livraison » est donc obligatoire (quatre chiffres, comme 8000), et le suivi attend qu'il soit rempli. Ce code est envoyé à GLS et, si besoin, à des services de suivi ; un code faux fait passer le colis pour un numéro inconnu.

Tu vois ensuite le statut, la ville et le pays de chaque scan sur une carte, le jour de livraison prévu, le poids et l'heure de livraison, sans ton nom, ton adresse, tes coordonnées, ta signature ni la référence. Les statuts gardent l'anglais de GLS ; celui du ParcelShop s'affiche « Prêt à être retiré ». Les heures des scans sont interprétées comme heure suisse. GLS est consulté environ une fois par heure en journée et toutes les une à deux heures la nuit, jour de livraison compris, et de nouveau 4 heures après une consultation qui a échoué. Notifications au choix : « Chaque scan », « Étapes importantes » ou « Livraison seulement ».

Quand GLS nomme La Poste comme livreur, la page du colis affiche « Livraison par Swiss Post », et le suivi peut passer sur celui de La Poste, avec le même numéro, dès qu'elle y montre une étape au moins aussi récente que le dernier scan de GLS.

## Questions sur le suivi de colis GLS en Suisse

### Combien de temps prend une livraison GLS en Suisse ?

GLS ne donne un chiffre que depuis l'Autriche : un délai standard de 2 jours. Pour les autres pays, fie-toi au délai annoncé par la boutique.

### Combien de temps GLS garde-t-il un colis ?

GLS ne publie aucun délai pour la Suisse. Si La Poste t'a laissé une invitation à retirer, ses règles prévoient en général 7 jours, ou 15 pour les envois internationaux (voir plus haut), et ce délai peut être prolongé gratuitement.

### GLS livre-t-il le samedi ?

Aucune page de GLS ne le dit pour la Suisse. Après une invitation à retirer, les règles de La Poste permettent une deuxième distribution le samedi, pour CHF 7.–, avec un login pour « Mes envois ».

### Colis GLS livré mais pas reçu : que faire ?

Contacte la boutique : c'est elle qui a choisi GLS et qui paie le transport. GLS ne publie aucune démarche pour la Suisse ; [colis livré mais non reçu](guide:delivered-not-received) liste les vérifications à faire avant.

:::sources
- [La Poste : Invitation à retirer un envoi](https://www.post.ch/fr/reception/invitation-a-retirer) – retrait, délais, deuxième distribution, réexpédition, procuration
- [La Poste : Swiss Post GLS](https://www.post.ch/fr/solutions-commerciales/exportation-importation-et-dedouanement/exportation/swiss-post-gls) – partenaire, téléphone, horaires, e-mail
- [La Poste : Frais d'importation](https://www.post.ch/fr/solutions-commerciales/exportation-importation-et-dedouanement/exportation/swiss-post-gls/frais-d-importation) – Incoterms GLS, transport, recours, « GLS (colis) »
- [La Poste : Importation](https://www.post.ch/fr/solutions-commerciales/exportation-importation-et-dedouanement/importation) – colis d'importation GLS dans le réseau postal
- [La Poste : Importverzollung](https://www.post.ch/de/empfangen/importverzollung) – différences pour le réseau GLS, mêmes prix, Incoterms 30 et 40
- [La Poste : Collection note](https://www.post.ch/en/receiving-mail/collection-note) – 15 jours pour les envois internationaux
- [GLS : Parcel Tracking](https://gls-group.com/EU/en/parcel-tracking) – numéros, Track ID, code postal, anglais seulement
- [GLS : GLS partners, Switzerland](https://gls-group.com/GROUP/en/gls-partners/?partner=switzerland) – Swiss Post GLS, adresse, e-mail
- [GLS : GLS partners, Liechtenstein](https://gls-group.com/GROUP/en/gls-partners/?partner=liechtenstein) – même partenaire
- [GLS : About us](https://gls-group.com/GROUP/en/about-us/) – entreprise
- [GLS : Contact](https://gls-group.com/GROUP/en/contact-form/) – formulaire, hotlines nationales
- [GLS : Security advice](https://gls-group.com/GROUP/en/security-advice/) – faux messages, site principal
- [GLS Netherlands : FAQ recipients](https://gls-group.com/NL/en/faq-recipient/) – textes des étapes et leur sens, livraison en ParcelShop, numéro donné par l'expéditeur
- [GLS Austria : Versand in die Schweiz](https://gls-group.com/AT/de/versand-in-die-schweiz/) – La Poste livre
- [GLS Austria : Regellaufzeiten](https://gls-group.com/AT/de/regellaufzeiten/) – 2 jours depuis l'Autriche
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – ce que Peek lit, « Delivered » pour l'arrivée en ParcelShop
:::
