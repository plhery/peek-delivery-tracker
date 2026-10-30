---
title: Swiss Post Cargo suivi : statuts, livraison et contact
description: Suivre un envoi Swiss Post Cargo en Suisse romande : quel numéro saisir, ce que veut dire DELIVERED SCANNED, comment se passe la livraison et qui appeler.
slug: swiss-post-cargo-suivi
published: 2026-10-12
updated: 2026-10-27
---

Swiss Post Cargo, c'est le transport de marchandises de la Poste suisse : palettes et autres marchandises livrées en camion, de l'unité isolée au chargement complet, pas les colis ordinaires. Tu suis ton envoi sur sa page Track & Trace (tt.swisspost-cargo.com) avec le code à barres ou la référence donnés par l'expéditeur, ou en le collant dans le champ ci-dessus. Si tu as un numéro de colis de la Poste, il se suit sur [la page de la Poste suisse](carrier:swiss-post).

## Numéro de suivi Swiss Post Cargo : à quoi il ressemble

Le suivi de Swiss Post Cargo accepte le code à barres d'une palette ou d'un colis, ou une référence de l'expéditeur. Une référence fait apparaître tous les codes à barres de son envoi. Les formes que Peek associe à Swiss Post Cargo, avec des chiffres inventés :

| Numéro | Ce que c'est |
| --- | --- |
| `00012345600000000005` | 20 chiffres commençant par `00` : le code à barres d'une palette ou d'un colis (un SSCC). Le dernier chiffre est un chiffre de contrôle. |
| `PL-12345678` | `PL-` suivi de 8 chiffres : une référence client. |

Regarde les e-mails de la boutique, puis demande-lui. [Où trouver son numéro de suivi](guide:find-tracking-number) donne d'autres pistes. Si le suivi ne trouve rien, vérifie les chiffres, puis demande à l'expéditeur.

Ces numéros-là ne sont pas suivis comme des envois Swiss Post Cargo :

- **Colis de la Poste :** 18 chiffres commençant par `99` (imprimés `99.00.123456.12345678`), ou deux lettres, neuf chiffres et `CH` (`RR000000005CH`).
- **Références PostLogistics** comme `12345678-123` : PostLogistics s'appelle aujourd'hui Swiss Post Cargo, et son ancien suivi, tracking.postlogistics.ch, ne s'ouvre plus. Peek suit ces références sous le nom PostLogistics.

## Suivi Swiss Post Cargo : ce que veulent dire les statuts

Swiss Post Cargo rédige ses scans en français, en allemand, en italien ou en anglais. Dans Peek, ils apparaissent en anglais. En anglais, un envoi livré se lit ainsi :

| Swiss Post Cargo écrit (en anglais) | Ce que ça veut dire pour toi |
| --- | --- |
| `Data entry POST` | Les données de l'expéditeur sont arrivées. La marchandise n'est pas encore chez Swiss Post Cargo. |
| `Received warehouse POST` | La marchandise est arrivée dans un centre ou un dépôt de Swiss Post Cargo, nommé à côté du scan. |
| `Loaded for Delivery` | Elle est dans le camion de livraison : en cours de livraison. |
| `IMAGE` | Une photo de livraison, listée juste avant le scan de livraison. |
| `DELIVERED SCANNED` | Livré, dans la localité indiquée. |
| `SIGNATURE` | La signature prise à la livraison, à la même heure que le scan de livraison. |

`Data entry POST` n'est que de l'information : rien n'a encore bougé. `IMAGE` et `SIGNATURE` sont des preuves de la livraison, pas de nouvelles étapes.

## Livraison Swiss Post Cargo : quand, où et en cas d'absence

**Quand.** Selon la brochure de la Poste sur le transport de marchandises, la livraison a lieu en général le jour ouvrable suivant. En Suisse et au Liechtenstein, les lieux accessibles aux poids lourds sont livrés le jour ouvrable suivant avant 17 h ; les vallées alpines et régions de montagne difficiles d'accès (Valais, Tessin, Grisons), le deuxième jour ouvrable avant 17 h.

**Où.** En standard, la marchandise est livrée pendant les heures d'ouverture, à la première porte au rez-de-chaussée, à la rampe ou au bord du trottoir, sans montée dans les étages. Le chauffeur compte sur un accès avec un transpalette, et le déchargement revient au destinataire : un chauffeur qui décharge le fait pour ton compte. Il te demande de signer pour confirmer que la livraison est correcte.

**Les options de l'expéditeur.** Un avis par téléphone, e-mail ou SMS, une livraison planifiée, une livraison à l'étage et une deuxième livraison sont des prestations supplémentaires : tu n'y as droit que si l'expéditeur les a commandées.

**Absent ou envie d'un retrait ?** La brochure de la Poste ne parle ni de points relais ni de casiers, et la deuxième livraison fait partie des options ci-dessus. Elle ne dit pas combien de temps la marchandise attend au dépôt, ni comment changer de date. Demande à la boutique, qui a commandé le transport.

## Envoi Swiss Post Cargo en retard, perdu ou abîmé

Le client de Swiss Post Cargo, c'est la boutique : ce sont les entreprises qui commandent ses services, donc un retard, un dommage ou un article manquant passe d'abord par elle. Signale un dommage à la boutique tout de suite, photos à l'appui.

**Pas de nouvelles depuis des jours.** Si tu ne vois que les données de l'expéditeur (`Data entry POST`), la marchandise est peut-être encore chez lui : demande à la boutique quand elle la remet. [Pourquoi un suivi ne bouge plus](guide:tracking-not-updating) explique les causes habituelles.

**Indiqué livré, mais rien reçu.** Cherche dans le suivi les lignes de la signature et de la photo (en anglais `SIGNATURE` et `IMAGE`), puis contacte la boutique. [Colis livré mais non reçu](guide:delivered-not-received) détaille la suite.

**Messages suspects.** La page de la Poste sur l'hameçonnage prévient que de faux messages au nom de la Poste arrivent par SMS, e-mail, WhatsApp, sites web, appels ou réseaux sociaux : « Il s'agit souvent de colis, de taxes, de suivi des envois ou de données personnelles. »

## Contacter Swiss Post Cargo : téléphone et e-mail

Swiss Post Cargo travaille pour la boutique, alors commence par elle : c'est elle qui a commandé le transport. La brochure de la Poste sur le transport de marchandises, datée d'avril 2022, donne un seul contact, pour les clients entreprises et sans numéro de téléphone : Service clientèle transport CH, Lagerstrasse 12, 5606 Dintikon, sales.ls4@post.ch.

Pour les lettres et colis de la Poste (pas Cargo), la ligne de la Poste est le +41 848 888 888 (CHF 0.08 la minute depuis un réseau fixe suisse), du lundi au vendredi de 7 h 30 à 18 h et le samedi de 8 h à 12 h, avec un chat sur post.ch.

## Suivre un envoi Swiss Post Cargo avec Peek

Un code à barres de 20 chiffres commençant par `00` appartient à l'expéditeur, pas à un transporteur : [Peek](/) affiche « Vérification des services de suivi… » et ne nomme Swiss Post Cargo que si celui-ci connaît le numéro. Une référence `PL-12345678` est demandée à Swiss Post Cargo et à TNT Italy, dont les numéros peuvent avoir la même forme. Pour une autre référence de 6 à 40 lettres et chiffres, choisis Swiss Post Cargo avec « Changer », ou colle un lien Track & Trace de tt.swisspost-cargo.com ou apv.swisspost-cargo.com. Un numéro de colis de la Poste dans un tel lien est suivi comme un colis de la Poste.

Peek affiche le statut et l'historique, avec la localité de chaque scan sur une carte, et l'heure de livraison ; les heures, lues à l'heure de Zurich, s'affichent dans le fuseau horaire de ton appareil. Peek interroge le suivi en anglais : les étapes y gardent donc leurs libellés anglais, comme `Data entry POST` ou `DELIVERED SCANNED`, et `Loaded for Delivery` compte comme en cours de livraison. Les lignes signature et photo rejoignent le scan de livraison, sans l'image. Peek laisse de côté ton nom et ton adresse, et n'a ni date de livraison prévue, ni poids, ni point de retrait.

Une référence partagée par plusieurs envois n'est lue que tant que l'un d'eux a des scans des 60 derniers jours ; sinon elle passe pour introuvable, alors utilise le code à barres. Peek vérifie le suivi toutes les 10 minutes en journée, toutes les 2 minutes une fois l'envoi en livraison, toutes les une à deux heures la nuit, et peut t'envoyer des notifications : « Chaque scan », « Étapes importantes » ou « Livraison seulement ».

## Questions sur le suivi Swiss Post Cargo

### Qu'est-ce que Swiss Post Cargo ?

C'est le nom sous lequel la logistique de marchandises de la Poste suisse travaille depuis le 1er mars 2025 : de la logistique pour les entreprises, en Suisse et à l'international, transport de marchandises compris. Parmi les sociétés du groupe de la Poste en Suisse figure Swiss Post Cargo CH AG, détenue à 100 % par la Poste.

### PostLogistics et Swiss Post Cargo, c'est pareil ?

Oui : PostLogistics a été l'une des premières sociétés du groupe à prendre le nom Swiss Post Cargo. Son ancien suivi, tracking.postlogistics.ch, ne s'ouvre plus ; le suivi de Swiss Post Cargo est tt.swisspost-cargo.com. Peek suit toujours les références PostLogistics comme `12345678-123`.

### Swiss Post Cargo a-t-il une adresse e-mail ?

La brochure de la Poste sur le transport de marchandises, datée d'avril 2022, donne sales.ls4@post.ch, pour les clients entreprises. Comme destinataire, écris d'abord à la boutique : c'est elle qui a commandé le transport.

### Pourquoi Swiss Post Cargo m'envoie-t-il un SMS ?

Souvent parce que l'expéditeur a commandé un avis de livraison : la brochure de la Poste range l'avis par téléphone, e-mail ou SMS parmi les options. De faux SMS au nom de la Poste circulent aussi, alors vérifie ton envoi toi-même sur le suivi.

### Peut-on suivre Swiss Post Cargo en direct ?

Pas sur une carte en temps réel. Le suivi liste chaque scan avec sa localité, et selon la brochure de la Poste, on peut à tout instant se renseigner en ligne sur la progression de l'envoi.

:::sources
- [La Poste : Transports, marchandises](https://www.post.ch/fr/-/media/post/gk/dokumente/stueckgut.pdf) – unités isolées ou chargements complets, ordres via Swiss Post Cargo Web par les entreprises, jour ouvrable suivant, régions de montagne, rez-de-chaussée, déchargement, signature, options dont la deuxième livraison, suivi en ligne, contact à Dintikon et e-mail
- [La Poste : Marché de la logistique](https://site.post.ch/fr/notre-profil/entreprise/strategie/activites-commercia/logistique) – Swiss Post Cargo est la logistique des marchandises de la Poste, solutions pour les entreprises, nationales et internationales
- [La Poste : Sociétés du groupe](https://site.post.ch/fr/notre-profil/entreprise/governance/organisation/societes-du-groupe) – Swiss Post Cargo CH AG, détenue à 100 % par la Poste
- [La Poste : Aide et contact](https://www.post.ch/fr/aide-et-contact) – ligne lettres et colis, prix, horaires, chat
- [La Poste : Tentatives de fraude actuelles](https://site.post.ch/fr/notre-profil/securite/hameconnage-et-fraude/tentatives-de-fraude-actuelles) – faux messages au nom de la Poste
- [VerkehrsRundschau : Schweizerische Post mit neuem Marktauftritt](https://www.verkehrsrundschau.de/nachrichten/transport-logistik/logistik-schweizerische-post-fuehrt-neue-marke-ein-3626449) – nom Swiss Post Cargo depuis le 1er mars 2025, PostLogistics parmi les premières sociétés
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – ce que Peek lit : codes à barres et références, formes de numéros, libellés anglais des scans, adresses Track & Trace, PostLogistics devenu Swiss Post Cargo
:::
