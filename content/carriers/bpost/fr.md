---
title: bpost suivi : code-barres, statuts et point relais
description: Suivre un colis bpost : où trouver le code-barres, ce que veut dire chaque statut du Track & Trace, combien de temps le colis attend et comment joindre bpost.
slug: bpost-suivi
published: 2026-10-11
updated: 2026-10-28
---

bpost est l'entreprise postale belge, la marque du groupe bnode qui livre les colis en Belgique et aux Pays-Bas. Pour suivre ton colis, saisis son code-barres sur le [Track & Trace de bpost](https://track.bpost.cloud/), dans l'app My bpost ou dans le champ ci-dessus. Ce code-barres figure dans l'e-mail d'expédition de la boutique, dans le SMS ou l'e-mail de bpost, sur l'étiquette ou sur l'avis de passage.

## Numéro de suivi bpost : où trouver le code-barres

Chez bpost, le numéro de suivi s'appelle le code-barres. Il est sur l'étiquette d'envoi, la preuve de dépôt ou de paiement du Bureau de Poste, le bordereau de livraison, l'avis de passage et l'e-mail de confirmation. Une boutique en ligne te l'envoie en général par e-mail, parfois sous forme de lien vers le Track & Trace. Pas de code-barres ? Demande-le à la boutique : sans lui, bpost ne peut pas t'aider.

Les formes les plus courantes selon bpost (numéros inventés) :

| Forme | Exemple | Ce que c'est |
| --- | --- | --- |
| 24 chiffres commençant par 3232 ou 3299 | `323200000000000000000000` | Code-barres d'un colis |
| 18 chiffres commençant par 3232 | `323200000000000000` | Code-barres d'un colis |
| 27 caractères commençant par JJBEA | `JJBEA0000000000000000000001` | Code-barres d'un colis |
| 2 lettres, 9 chiffres, BE | `CE000000005BE` | Numéro postal international venu de Belgique |
| R, une lettre, 9 chiffres, 2 lettres | `RR000000005BE` | Recommandé international |
| 30 chiffres commençant par 010 | `010000000000000000000000000000` | Recommandé en Belgique |

Un code-barres qui commence par U sert seulement au passage en douane : l'expéditeur ou l'acheteur a choisi un envoi sans Track & Trace. Selon bpost, il arrive au plus tard 5 jours ouvrables après le dédouanement.

**Attention aux numéros qui se ressemblent.** Un numéro postal qui finit par FR ou CH appartient à la poste de ce pays, pas à bpost. Une erreur de frappe dans un numéro en BE casse sa clé de contrôle, et Peek peut alors proposer [Chronopost](carrier:chronopost). Certains numéros à 24 chiffres sont ceux d'InPost ou de [Ciblex](carrier:ciblex) : en cas de doute, regarde le transporteur cité dans l'e-mail de la boutique.

## bpost suivi colis : ce que veulent dire les statuts

Le Track & Trace de bpost existe en français, en néerlandais, en anglais et en allemand. Voici les libellés français, dans l'ordre où ton colis les rencontre :

| bpost écrit | Ce que ça veut dire pour toi |
| --- | --- |
| `Envoi en cours de préparation chez l'expéditeur` | La boutique a annoncé le colis, mais bpost ne l'a pas encore. |
| `Envoi déposé chez bpost par l'expéditeur` | L'expéditeur l'a déposé au guichet. |
| `L'article est annoncé / Landmark Global (bpost) a reçu l'information` | Un colis international a été annoncé à [Landmark Global](carrier:landmark-global), la marque du groupe pour les envois entre pays. |
| `Landmark Global (bpost) a receptionné l'article` | Landmark Global a le colis en main. |
| `Arrivée au dépôt` | Le colis est arrivé dans un dépôt bpost. |
| `L'envoi a été trié` | Trié, il continue sa route. |
| `Départ vers le pays de destination` | Il part vers le pays où il est livré. |
| `Votre envoi est arrivé chez l'opérateur postal du pays de destination et sera livré dans les prochains jours` | La poste du pays de destination l'a reçu et va le livrer. |
| `La livraison de votre colis est en cours` | Le facteur l'a dans sa tournée aujourd'hui. |
| `Article disponible au Point Relais` | Il t'attend dans un Point d'enlèvement. |
| `Envoi distribué` | Livré, ou retiré au point. |

`Article disponible au Point Relais` désigne ce que les pages d'aide de bpost appellent un « Point d'enlèvement » : c'est le même endroit. `Colis arrivé au centre de distribution - ne peut pas être traité - informations manquantes` veut dire que le centre n'a pas pu mettre le colis dans une tournée parce qu'il manque une information. bpost n'en dit pas plus : si rien ne bouge, contacte la boutique.

Après `Votre envoi est arrivé chez l'opérateur postal du pays de destination et sera livré dans les prochains jours`, bpost dépend des informations de la poste étrangère. bpost conseille alors d'essayer aussi l'outil de suivi de cette poste.

## Livraison bpost, point relais et absence

**Tu n'étais pas là.** Sans préférence de livraison, le facteur essaie en général un voisin ou un lieu sûr. Sinon, tu reçois un avis de passage et tu peux retirer le colis au Point d'enlèvement indiqué, à partir du jour ouvrable suivant. bpost t'envoie toujours un e-mail quand le colis est prêt ; l'avis papier n'arrive que si bpost ne peut pas te joindre par voie numérique. Le point peut changer dans des cas exceptionnels, alors vérifie le statut sur le Track & Trace avant d'y aller. Le facteur ne repasse pas le même jour. Si tu as manqué une livraison un samedi, il repasse le jour ouvrable suivant.

**Combien de temps il attend.** 14 jours calendrier dans un Point d'enlèvement, 5 jours calendrier dans un Distributeur de Colis (Bbox), sans prolongation possible. Ensuite, le colis repart chez l'expéditeur. Toute personne qui a le code-barres peut le retirer. Pour un recommandé, il faut le destinataire avec sa carte d'identité et le code-barres, ou une personne qu'il a mandatée en signant l'avis.

**Les distributeurs Bbox.** Ils sont accessibles 24 h/24 et 7 j/7, sans compte. Scanne le QR code reçu par e-mail ou dans l'app My bpost ; si tu as choisi la livraison directe en distributeur, un code à 6 chiffres figure sous le QR code. Les distributeurs sans écran s'ouvrent seulement avec l'app, en Bluetooth. Un colis trop grand ou trop lourd part vers un autre Point d'enlèvement.

**Changer la livraison.** Dans MesPréférences ou l'app My bpost, tu choisis un lieu sûr, un·e voisin·e, un Point d'enlèvement ou un Distributeur de Colis. Pendant le trajet, tu peux faire livrer le colis à une autre adresse près de chez toi ou reporter une fois la livraison d'un jour ouvrable. Le voisin ou le lieu sûr se change jusqu'à 5 minutes avant la livraison, le point ou le distributeur jusqu'au soir précédant la livraison. Impossible en revanche de faire transférer un colis déjà arrivé vers un autre point.

## Colis bpost pas reçu, bloqué ou perdu

**Le suivi ne bouge plus.** Pour un envoi vers l'étranger, bpost demande d'attendre au moins 5 jours ouvrables sans changement (pays voisin) ou 10 jours ouvrables (Royaume-Uni et autres pays), puis de demander à l'expéditeur de contacter le service clientèle de bpost. Voir aussi [pourquoi un suivi ne bouge plus](guide:tracking-not-updating).

**Qui réclame.** En tant que destinataire, tu ne peux pas introduire toi-même une plainte. La plupart des expéditeurs ont un accord avec bpost pour les colis perdus ou abîmés : demande à la boutique un nouvel envoi ou un remboursement. Seul l'expéditeur peut lancer une enquête chez bpost.

**Abîmé.** Contacte l'expéditeur dans les 7 jours suivant la réception, avec des photos de l'emballage ou du contenu. Pour un colis venu de l'étranger, contacte bpost et l'expéditeur. Si tu vois les dégâts au Point d'enlèvement, tu peux refuser le colis : il repart chez l'expéditeur.

**Livré mais pas reçu.** Demande aux voisins et aux personnes qui vivent avec toi, regarde dans ta boîte aux lettres (un petit colis peut y être déposé sans sonner), vérifie tes préférences, la photo du lieu sûr dans l'app ou le Track & Trace, et cherche un avis de passage. bpost propose aussi un [formulaire pour un envoi non livré](https://www.bpost.be/fr/formulaires/colis/pas_recu/step1) ; prépare le code-barres et le code postal du destinataire. Plus de conseils dans [colis livré mais non reçu](guide:delivered-not-received).

**Retourné.** Une mauvaise adresse, un colis refusé à la porte ou non retiré à temps repart chez l'expéditeur. Contacte la boutique pour un nouvel envoi ou un remboursement.

**Douane.** Un colis venu de l'extérieur de l'UE n'est livré qu'après paiement des frais d'importation, de préférence dans l'app My bpost ou sur le Track & Trace, dans les 14 jours. Pour une commande de moins de 150 €, bpost indique 3 € de droits par article depuis le 1er juillet 2026 et, à partir du 1er novembre 2026, 2 € de frais de traitement européens par article, un article étant un type de produit : deux t-shirts identiques coûtent alors 5 € de droits et de frais, un t-shirt et un maillot de bain 10 €. S'y ajoutent la TVA, généralement 21 %, et 21,50 € de frais administratifs si la boutique n'a pas encaissé la TVA et les droits à l'avance. Au-delà de 150 €, tu paies des droits selon le type de produit, la TVA, 40 € de frais administratifs et, à partir du 1er novembre 2026, les 2 € de frais de traitement européens. Vérifie la [page de bpost sur les achats hors UE](https://www.bpost.be/fr/shopping-hors-ue) et le guide [colis bloqué en douane](guide:customs).

> Pour les envois au sein de l'UE, bpost ne te demandera jamais de payer pendant que ton colis est en cours de livraison, ni de donner tes données personnelles par e-mail ou SMS. Transfère les messages suspects à abuse@bpost.be.

## bpost contact : téléphone, chat et plainte

Relevé sur les pages françaises de bpost le 27 octobre 2026 :

- **Téléphone :** 02 278 51 27 sur les pages françaises et allemandes de bpost, 02 278 51 26 sur ses pages anglaises et néerlandaises ; du lundi au vendredi de 8 h à 12 h 30 et de 13 h à 18 h, le samedi de 9 h à 13 h, au tarif zonal. Le temps d'attente varie.
- **Chat :** pour certains services, dont le Track & Trace. bpost ne publie pas d'horaires pour le chat.
- **Sur place :** dans un Bureau de Poste, un Point Poste ou un Point Colis.
- **Courrier (sans timbre) :** bpost, Boîte 5000, 1000 Bruxelles.

Garde le code-barres sous la main. bpost ne donne pas d'adresse e-mail pour son service clientèle. Si la façon dont bpost a traité une plainte ne te convient pas, le Service de médiation pour le secteur postal peut être saisi.

## Suivre un colis bpost avec Peek

[Peek](/) reconnaît tout de suite bpost pour un numéro valide de 2 lettres, 9 chiffres et BE, et pour JJBE suivi d'une lettre et de 18 ou 22 chiffres. Un numéro à 24 chiffres qui commence par 3232 ou 3299 est demandé à bpost, Austrian Post, InPost et Ciblex ; un numéro à 18 chiffres qui commence par 3232, à bpost et Ecoscooting. Si bpost le connaît, Peek choisit bpost ; c'est seulement quand Ecoscooting connaît aussi un numéro à 18 chiffres que tu choisis, avec « Choisir ». Un recommandé belge à 30 chiffres ne se suit qu'après avoir choisi bpost avec « Changer ». Un lien de bpost.be ou de la page de recherche du Track & Trace qui contient le code-barres dans `itemCodes` est attribué à bpost. Un code-barres en U n'est pas attribué à bpost, qui ne le suit pas : seul Chronopost est alors proposé.

Peek interroge bpost directement, sans code postal. Tu vois les scans de bpost avec le lieu, le poids et les dimensions ; ceux faits en Belgique sont lus à l'heure belge. Quand le colis attend dans un Point d'enlèvement ou un Distributeur de Colis, Peek affiche son nom et son adresse, même après le retrait. Pour un colis livré en Belgique, tu vois l'heure de livraison. En journée, Peek vérifie toutes les 10 minutes, et toutes les 2 minutes quand le colis est en cours de livraison ; la nuit, toutes les une à deux heures.

Dans Peek, les statuts restent en anglais, tels que bpost les écrit (`Item delivered`, `Item available at Pick-up point`), et un colis rendu à l'expéditeur compte comme un retour. Peek n'affiche pas de date de livraison prévue et ne garde ni l'expéditeur, ni le destinataire, ni l'adresse du destinataire, ni les coordonnées, ni les préférences de livraison, ni la preuve de livraison. Il peut t'envoyer des notifications : « Toutes les mises à jour », « Importantes uniquement » ou « Jour de livraison uniquement ».

## Questions sur le suivi bpost

### Combien de temps pour récupérer un colis chez bpost ?

14 jours calendrier dans un Point d'enlèvement et 5 jours calendrier dans un distributeur Bbox, sans prolongation. Le 6e jour, un colis laissé en distributeur repart chez l'expéditeur.

### bpost livre-t-il le samedi et le dimanche ?

Oui pour certains colis, si l'expéditeur a un accord avec bpost : le samedi de 8 h à 17 h en Point d'enlèvement ou en Distributeur de Colis, le dimanche de 9 h à 19 h à domicile. Les lettres et les recommandés ne sont livrés qu'en semaine.

### Qui peut retirer un recommandé bpost ?

Seul le destinataire, avec sa carte d'identité et le code-barres, ou une personne qu'il mandate en signant l'avis de passage. Un colis ordinaire, lui, peut être retiré par toute personne qui a le code-barres.

### Pourquoi je ne peux pas suivre mon colis bpost ?

Le plus souvent, l'expéditeur ne l'a pas encore remis à bpost : le suivi commence dès qu'il le fait. Un code-barres qui commence par U n'a pas de Track & Trace du tout.

:::sources
- [bpost : A quoi ressemble un code-barres et où puis-je le trouver ?](https://www.bpost.be/fr/faq/what-does-barcode-look-and-where-can-i-find-it) – formes du code-barres, où le trouver, codes en U
- [bpost : Track & Trace, suivi de colis](https://www.bpost.be/fr/faq/can-i-follow-my-parcel-online) – canaux de suivi, colis à l'étranger, outil de la poste étrangère
- [bpost : Colis est en chemin, mais pas de code-barres](https://www.bpost.be/fr/faq/i-am-expecting-parcel-have-not-received-barcode-what-do-i-do-now) – e-mail de la boutique, demander à l'expéditeur
- [bpost : Track your shipment](https://track.bpost.cloud/) – langues du Track & Trace, libellés des statuts
- [bpost : Recevoir un colis](https://www.bpost.be/fr/recevoir-colis) – début du suivi, autre adresse, 14 jours pour payer
- [bpost : Je n'étais pas chez moi, où est mon colis ?](https://www.bpost.be/fr/faq/je-netais-pas-chez-moi-lors-de-la-livraison-de-mon-colis-ou-se-trouve-t-il-present) – voisin, lieu sûr, avis, 14 jours
- [bpost : Absent, où récupérer mon colis aujourd'hui ?](https://www.bpost.be/fr/faq/mon-colis-na-pas-pu-etre-livre-mon-domicile-ou-et-quand-puis-je-le-recuperer) – jour ouvrable suivant, e-mail, vérifier le statut
- [bpost : J'ai manqué le colis, le facteur peut-il repasser ?](https://www.bpost.be/fr/faq/je-viens-de-manquer-mon-colis-le-facteur-peut-il-encore-passer-aujourdhui) – pas de second passage
- [bpost : Avis dans la boîte, pourquoi le facteur va repasser](https://www.bpost.be/fr/faq/pourquoi-le-facteur-passe-t-il-quand-meme-une-2e-fois-apres-avoir-depose-un-avis-de-passage) – second passage après un samedi
- [bpost : Retirer votre colis dans un Point d'enlèvement](https://www.bpost.be/fr/recevoir-colis/point-enlevement) – 14 et 5 jours, qui peut retirer, recommandés
- [bpost : Disponibilité du colis au Point d'enlèvement](https://www.bpost.be/fr/faq/combien-de-temps-mon-colis-reste-t-il-disponible-au-point-denlevement) – délais de garde
- [bpost : Récupérer un colis dans un distributeur bbox](https://www.bpost.be/fr/faq/comment-recuperer-un-colis-dans-un-distributeur-de-colis-0) – QR code, code à 6 chiffres, Bluetooth, sans prolongation
- [bpost : Bbox](https://www.bpost.be/fr/bbox) – accessible 24 h/24 et 7 j/7
- [bpost : Absent, envoyer le colis à un autre Point d'enlèvement](https://www.bpost.be/fr/faq/puis-je-transferer-mon-colis-vers-un-autre-point-denlevement) – pas de transfert
- [bpost : Qu'advient-il de mon colis si je suis absent ?](https://www.bpost.be/fr/faq/que-faire-si-je-ne-suis-pas-chez-moi-lorsque-le-facteur-vient-livrer-mon-colis) – préférences, autre adresse, report
- [bpost : Puis-je modifier l'heure de livraison de mon colis ?](https://www.bpost.be/fr/faq/puis-je-modifier-lheure-de-livraison) – report d'un jour ouvrable
- [bpost : Vos préférences de livraison, vos règles](https://www.bpost.be/fr/mybpostapp-pref-parcel) – délais de modification, colis trop grand pour un distributeur
- [bpost : Mon colis ne bouge plus dans Track & Trace](https://www.bpost.be/fr/faq/mon-colis-semble-coince-quelque-part-il-na-pas-bouge-sur-track-trace-depuis-un-moment) – 5 et 10 jours ouvrables, plainte de l'expéditeur
- [bpost : Comment introduire une plainte concernant un colis](https://www.bpost.be/fr/faq/comment-introduire-une-plainte-en-cas-de-colis-endommage-perdu-ou-non-livre) – pas de plainte du destinataire, 7 jours, refus au point, horaires
- [bpost : Le colis a été livré, mais je n'ai rien reçu](https://www.bpost.be/fr/faq/je-recois-un-message-indiquant-que-mon-colis-ete-livre-mais-je-nai-rien-recu-que-faire) – vérifications, photo, formulaire
- [bpost : Pas livré à domicile, raison du retour à l'expéditeur](https://www.bpost.be/fr/faq/mon-colis-aurait-du-etre-livre-domicile-mais-ete-renvoye-lexpediteur-pourquoi-et-comment-puis-je) – raisons d'un retour
- [bpost : Mon colis à la douane](https://www.bpost.be/fr/recevoir-colis/douane) – paiement à l'avance des frais d'importation
- [bpost : Achats en ligne hors de l'UE](https://www.bpost.be/fr/shopping-hors-ue) – 3 € et 2 € par article, TVA, 21,50 € et 40 € de frais administratifs
- [bpost : bpost livre-t-elle le samedi et le dimanche ?](https://www.bpost.be/fr/faq/quels-courriers-ou-colis-bpost-livre-t-elle-le-samedi-et-le-dimanche) – horaires du week-end
- [bpost : Comment contacter le service clientèle de bpost ?](https://www.bpost.be/fr/faq/comment-contacter-le-service-clientele-de-bpost) – téléphone, chat, sur place, Boîte 5000
- [bpost : How do I contact bpost's customer services?](https://www.bpost.be/en/faq/how-do-i-contact-bposts-customer-services) – 02 278 51 26 (page anglaise)
- [bpost : FAQ](https://www.bpost.be/fr/faqs) – Service de médiation pour le secteur postal
- [bpost : Reçu un SMS ou un e-mail suspect ?](https://www.bpost.be/fr/faq/jai-recu-un-sms-ou-un-e-mail-suspect-pretendument-de-bpost-que-dois-je-faire) – faux messages, abuse@bpost.be
- [bnode : bpostgroup becomes bnode](https://press.bnode.com/bpostgroup-becomes-bnode) – nom du groupe, marque bpost, Landmark Global
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – ce que Peek lit
:::
