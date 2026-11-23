---
title: Suivi Slovak Parcel Service (SPS) : statuts et retrait
description: Suis un colis SPS en Slovaquie : format du numéro, statuts slovaques, consignes Balíkovo, échec de livraison, réclamations et contact.
slug: suivi-slovak-parcel-service
published: 2026-11-23
updated: 2026-11-23
---

Slovak Parcel Service (SPS) est un transporteur slovaque détenu par [Austrian Post](carrier:austrian-post) depuis 2002, qui livre les colis des boutiques partout en Slovaquie, à domicile ou en consigne ou point relais Balíkovo. Ton numéro de suivi figure dans l’e-mail d’expédition de la boutique ou dans le SMS ou l’e-mail de SPS : saisis-le sur sps-sro.sk ou colle-le dans le champ ci-dessus.

## Numéro de suivi Slovak Parcel Service

Un numéro de colis SPS compte 14 chiffres commençant par `703`. Sur l’étiquette, il est imprimé par groupes avec deux chiffres supplémentaires, comme `703-482-91736405 01` (chiffres fictifs). Ces 16 chiffres forment le code-barres de l’étiquette ; le numéro de colis à 14 chiffres correspond au même code sans les deux derniers chiffres.

| Numéro (fictif) | À qui il appartient |
| --- | --- |
| `70348291736405` | SPS : 14 chiffres commençant par 703. |
| `703-482-91736405 01` | Le même colis tel qu’imprimé sur l’étiquette, avec 2 chiffres en plus. |
| `48291736405829` | 14 chiffres ne commençant pas par 703 : DPD, [SEUR](carrier:seur), [BRT](carrier:brt), [Hermes Allemagne](carrier:hermes-de) et d’autres, pas SPS. |
| `RR482917367SK` | Numéro postal se terminant par SK : [Slovenská pošta](carrier:slovenska-posta), pas SPS. |

Si sps-sro.sk répond « Please check that you entered the shipment number correctly (no spaces) », ressaisis-le sans espaces ni tirets. SPS indique que le bon format figure dans ton e-mail ou SMS de confirmation. Son formulaire de suivi peut aussi demander le code postal de destination ou le numéro client pour afficher davantage de détails. Aucun e-mail reçu ? Demande à la boutique et consulte [comment trouver ton numéro de suivi](guide:find-tracking-number).

## Statuts de suivi SPS expliqués

La page de suivi de SPS (sps-sro.sk, en slovaque et en anglais) liste chaque scan avec la date, l’heure, le statut et le centre. Les libellés restent en slovaque même sur la version anglaise :

| Statut SPS | Ce que cela signifie pour toi |
| --- | --- |
| `Definícia dát` | [Étiquette créée](guide:tracking-statuses) : la boutique a transmis les données à SPS, mais SPS n’a pas encore le colis. |
| `Odnáška` | Un livreur a collecté le colis chez l’expéditeur. |
| `Prvá registrácia` | Premier scan dans un dépôt SPS. |
| `Vstup` | Arrivé dans un dépôt, en transit. |
| `Doručovací hárok` | Inscrit sur la feuille de tournée du jour : en cours de livraison. |
| `Uložený v balíkomate` | Déposé dans une consigne Balíkovo, prêt à être retiré. |
| `Informácia o doručení` | Données de livraison enregistrées, quelques minutes avant la remise. Ce n’est pas un déplacement. |
| `Doručenie` | Livré. |
| `Balík odmietnutý` | Le colis a été refusé. |
| `Uskladnené` | Conservé en dépôt. |
| `Návrat odosielateľovi` | Retour vers l’expéditeur. |

Deux étapes liées au contre-remboursement, `Editor hotovosti` et `Dobierka odoslaná zákaznikoví`, relèvent uniquement de la comptabilité : le colis ne bouge pas.

Depuis cette page, le site renvoie vers mySPS (mysps.sk), qui affiche une barre de progression en anglais ou en slovaque : `Received Info`, `Taken by carrier`, `In transit`, `Delivering`, `In the collection point` (uniquement pour un point relais), puis `Delivery` ou `Undelivered`. Attention : `Delivering` signifie en cours de livraison aujourd’hui, tandis que `Delivery` signifie livré.

## Livraison, consignes Balíkovo et absences

**Livraison à ton adresse.** Pour les expéditeurs, la livraison nationale standard de SPS s’effectue le jour ouvré suivant la collecte. Le livreur apporte le colis jusqu’à la première porte verrouillable de l’immeuble, et à l’étage uniquement après accord. Si l’expéditeur a souscrit aux notifications, SPS te prévient par SMS, e-mail ou téléphone au plus tard le jour de la livraison, en indiquant la date, le montant éventuel du contre-remboursement et un moyen de rediriger le colis. Les notifications par e-mail partent la veille de la livraison.

> Les SMS et appels ne sont envoyés qu’aux numéros mobiles slovaques. Avec un numéro étranger, tu ne reçois pas de SMS : pense donc aussi à donner ton adresse e-mail à la boutique.

**Une seule tentative.** Les conditions de SPS prévoient une seule tentative de livraison. Après un échec, SPS peut rediriger le colis vers la consigne Balíkovo libre ou le commerce partenaire le plus proche et t’en informe par SMS ou e-mail.

**Balíkovo.** C’est le réseau de retrait de SPS, anciennement SPS Parcel Shops : commerces partenaires et consignes automatiques. La page « What is balíkovo? » de SPS annonce plus de 2 500 points et 1 000 consignes, sa page d’accueil mentionne 1 200 consignes et la page dédiée aux futurs partenaires indique encore plus de 2 400 points. À l’arrivée du colis, SPS t’envoie un code PIN par SMS ou e-mail ; la personne qui retire le colis doit disposer de ce code. Une consigne garde le colis 48 heures, avec un rappel 24 heures avant l’échéance ; un commerce partenaire le garde jusqu’à 7 de ses jours ouvrés. SPS peut modifier ces délais en période de forte activité, et mySPS propose « Extend storage period », parfois moyennant des frais. Un colis non retiré en consigne repart chez l’expéditeur.

**Modifier la livraison.** mySPS propose « Flexible Delivery » pour changer la date prévue ou le mode de livraison, à raison d’un changement par jour. Rediriger vers un point relais exige que ton e-mail ou ton numéro de mobile figure sur le colis.

**Paiement à la livraison.** Paie le livreur en espèces (jusqu’à 500 € sauf accord du livreur) ou par carte. Pour un colis Balíkovo, les conditions de SPS indiquent un paiement en ligne via un lien reçu par SMS ou e-mail, le code PIN de la consigne arrivant après règlement ; la page « Ako funguje balíkovo? » indique toutefois espèces uniquement, sans carte. Une fois un colis ouvert devant la porte, tu ne peux plus le rendre au livreur : adresse-toi à la boutique.

## Colis SPS en retard, perdu ou endommagé

**Aucune mise à jour depuis des jours.** SPS ne publie aucune règle à ce sujet. C’est la boutique qui détient le contrat avec SPS : contacte-la d’abord, et consulte [pourquoi un suivi cesse de se mettre à jour](guide:tracking-not-updating). La rubrique « Where is my parcel » de SPS ouvre un formulaire de contact une fois les références du colis saisies.

**Endommagé.** Vérifie le colis à la remise et signale tout dommage visible. Conserve le carton d’origine et le calage sans utiliser le contenu : le règlement des réclamations de SPS annule toute réclamation sans l’emballage. Toi ou l’expéditeur pouvez déclarer le dommage via le formulaire « Report parcel damage » sur sps-sro.sk, avec le numéro de colis, le code postal ou le numéro client, des photos, une description, le montant et la preuve du contenu et de la valeur.

**Délais** (règlement des réclamations de SPS) : dommage ou perte partielle dans les 2 jours ouvrés suivant la livraison ; perte totale, livraison contestée ou retard sur service garanti dans les 30 jours calendaires après la prise en charge par SPS. La FAQ de SPS mentionne 7 jours après livraison, mais le règlement des réclamations fait foi : agis sous 2 jours ouvrés. SPS statue sous 30 jours calendaires. L’indemnisation est versée à l’expéditeur, ou à toi avec son autorisation écrite.

**Livré mais non reçu.** Une livraison contestée suit le délai de 30 jours ci-dessus ; préviens aussi la boutique et vois [livré mais non reçu](guide:delivered-not-received).

**Refusé ou retourné.** Un colis refusé repart aussitôt chez l’expéditeur ; `Návrat odosielateľovi` le confirme.

## Contact et service client Slovak Parcel Service

Selon sps-sro.sk :

- **Téléphone :** ton indicatif régional suivi de 16877, indique SPS ; sa liste des centres précise 02/16877 pour Bratislava, Banská Bystrica, Trenčín, Trnava et Žilina, 055/16877 pour Košice et 037/16877 pour Nitra. Depuis l’étranger : +421 2 168 77.
- **Horaires :** chaque jour ouvré, de 8 h à 16 h 30 (heure slovaque).
- **mySPS :** SPS recommande mysps.sk pour le traitement le plus rapide : suivi 24 h/24 et demandes au service client.
- **E-mail ou chat :** aucun canal affiché pour les destinataires.

> SPS avertit que de faux SMS et e-mails circulent en son nom lors des livraisons. Méfie-toi des liens demandant des données bancaires ou personnelles et supprime ces messages si tu n’as rien commandé.

## Suivre un colis SPS avec Peek

[Peek](/) connaît le format de SPS, 14 chiffres commençant par `703`, mais il est partagé avec DPD, SEUR, BRT, [Hermes Allemagne](carrier:hermes-de) et d’autres. En Slovaquie, Peek interroge SPS en premier. Ailleurs, SPS figure vers la fin des 14 transporteurs partageant cette forme, de sorte que Peek ne l’interroge pas lors de l’ajout ni au premier contrôle ; une vérification ultérieure au cours du premier mois peut toutefois l’atteindre. Si un autre transporteur revendique le numéro, ou si aucun ne répond encore, appuie sur « Changer » et choisis « Slovak Parcel Service » (rechercher « SPS » le trouve). Saisis les 14 chiffres : le code d’étiquette à 16 chiffres ne désigne pas SPS. Un lien de suivi sps-sro.sk contenant `shipment_id=` identifie SPS quel que soit le numéro ; un lien mysps.sk ne le fait pas.

Les heures des scans SPS n’indiquent pas de fuseau horaire : Peek les lit à l’heure slovaque et affiche l’historique propre de SPS. Il peut montrer le statut, le dépôt de chaque scan et le poids, avec les libellés en slovaque tels que SPS les écrit ; `Doručenie` compte comme livré. Aucune date de livraison estimée n’est affichée ; un dépôt en consigne Balíkovo apparaît sous `Uložený v balíkomate`, avec le lieu indiqué par SPS. Tant que tu gardes le colis ouvert dans Peek, ou le jour de sa livraison avec les notifications activées, Peek vérifie jusqu’à toutes les 10 minutes — et toutes les 2 minutes à partir de 6 h (heure slovaque) le jour où le colis passe sur `Doručovací hárok`, tant que le suivi de SPS répond ; sinon toutes les heures. Après deux jours sans nouveauté (12 heures tant que le colis reste sur « Étiquette créée » ou « Pris en charge »), Peek vérifie toutes les heures, puis toutes les 6 heures après sept jours au statut « Étiquette créée ». Si un colis aux étapes « Étiquette créée », « Pris en charge » ou « En transit » reste quatre jours sans nouveau scan, Peek affiche « Aucune mise à jour depuis quatre jours » (ou « Aucune mise à jour depuis… » avec la date), « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger Slovak Parcel Service. » et le lien « Demande de l’aide à Slovak Parcel Service ». Peek peut t’alerter avec « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Il ne peut ni prolonger la garde en consigne ni renvoyer ton code PIN Balíkovo.

## Questions sur le suivi Slovak Parcel Service

### Quel est le numéro de téléphone de Slovak Parcel Service ?

Compose ton indicatif régional suivi de 16877, par exemple 02/16877 à Bratislava, ou +421 2 168 77 depuis l’étranger. Les lignes répondent les jours ouvrés de 8 h à 16 h 30 (heure slovaque).

### Combien de temps SPS garde-t-il un colis en consigne Balíkovo ?

48 heures à compter du dépôt dans la consigne, avec un rappel 24 heures avant la fin. Un commerce partenaire Balíkovo le conserve jusqu’à 7 de ses jours ouvrés.

### Slovak Parcel Service a-t-il une application ?

Les pages de SPS renvoient vers mySPS sur mysps.sk, un portail web où tu peux suivre un colis, modifier la livraison, renvoyer ton code PIN et payer un contre-remboursement.

:::sources
- [SPS: Track & Trace](https://www.sps-sro.sk/en/moja-zasielka/) – libellés des scans, colonnes, message introuvable, Balíkovo
- [SPS: Obchodné podmienky účinné od 1.9.2026](https://backend.sps-sro.sk/storage/documents/82/sps-obchodne-podmienky-sk-09-2026-FMFHUp.pdf) – livraison, une tentative, redirection, avis, numéros slovaques, garde, PIN, contre-remboursement, téléphone depuis l’étranger
- [SPS: Rules for claims valid from May 1, 2026](https://backend.sps-sro.sk/storage/documents/64/sps-reklamacny-poriadok-en-2026-aqjOdQ.pdf) – réclamations, délais, emballage, indemnisation
- [SPS: Do you need to contact customer service?](https://www.sps-sro.sk/en/kontakt/zakaznicky-servis) – horaires, indicatif local + 16877
- [SPS: Kontaktné strediská](https://www.sps-sro.sk/kontakt/kontaktne-strediska) – numéro par centre, mySPS
- [SPS: Ako vygenerovať príjmový doklad](https://www.sps-sro.sk/documents/ako-vygenerovat-prijmovy-doklad_v2.pdf) – numéro sur l’étiquette
- [SPS: Where is my parcel](https://www.sps-sro.sk/en/moja-zasielka/kde-je-moj-balik) – contenu de la rubrique
- [SPS: Report parcel damage](https://www.sps-sro.sk/en/moja-zasielka/nahlasit-poskodenie-balika) – formulaire de dommage
- [SPS: FAQ](https://www.sps-sro.sk/en/zasielky/faq) – délai de réclamation de 7 jours dans la FAQ
- [SPS: Pozor na podvodné SMS a emaily](https://www.sps-sro.sk/novinky/pozor-na-podvodne-sms-a-emaily-2) – faux messages
- [SPS: Become a Balíkovo Pickup Point](https://www.sps-sro.sk/en/balikovo/ako-sa-stat-balikovom) – plus de 2 400 points
- [SPS: What is balíkovo?](https://www.sps-sro.sk/en/balikovo/co-je-balikovo) – plus de 2 500 points et 1 000 consignes
- [SPS: Home](https://www.sps-sro.sk/en) – 1 200 consignes Balíkovo
- [SPS: Ako funguje balíkovo?](https://www.sps-sro.sk/ako-funguje-balikovo/) – espèces uniquement pour le contre-remboursement
- [mySPS: interface texts](https://rest.mysps.sk/lang/get/en) – barre de progression, Flexible Delivery, garde, PIN
- [Austrian Post: Subsidiaries international](https://post.at/en/j/c/careers-austrian-post-subsidiaries-international) – propriétaire depuis 2002
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – données lues par Peek
:::
