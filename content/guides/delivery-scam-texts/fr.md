---
title: Arnaque colis par SMS : comment repérer un faux message
description: Faux SMS de colis : les signes qui trahissent l'arnaque, ce qu'envoient vraiment Mondial Relay, bpost ou La Poste suisse, et que faire si tu as cliqué ou payé.
slug: arnaque-colis-sms
picture: Un téléphone affiche un message avec un triangle d'alerte et un hameçon sur son lien, une pièce barrée, et Pip, inquiet, avec une main levée qui dit stop dans sa bulle.
published: 2026-10-14
updated: 2026-10-27
---

Un SMS ou un mail qui te dit que ton colis est bloqué, qu'il faut payer quelques euros ou corriger ton adresse, avec un lien pour régler ça, c'est presque toujours une arnaque. Ne touche à rien : ouvre toi-même le site ou l'app du transporteur et suis ton colis avec le numéro de l'e-mail d'expédition de la boutique. Si tu as déjà saisi les données de ta carte bancaire, appelle ta banque tout de suite, avec le numéro inscrit sur ta carte ou dans ton app bancaire, pour faire opposition (en Belgique, Card Stop au 078 170 170).

## Comment reconnaître un faux SMS de colis ?

Un faux message coche en général plusieurs de ces cases :

- **Tu n'attends rien.** Pas de commande en cours, personne qui doit t'envoyer quelque chose : ce message n'a aucune raison de t'arriver. Service-public.fr cite le « problème d'acheminement d'un colis » parmi les prétextes habituels du phishing.
- **Il te demande de l'argent pour livrer.** Des frais de port, des frais de livraison, un petit montant pour « débloquer » le colis. Cybermalveillance.gouv.fr le rappelle : [La Poste](carrier:la-poste) « ne vous demandera jamais de payer pour retirer un colis par SMS ou par mail », [Chronopost](carrier:chronopost) ne demande pas de frais de livraison pour une livraison en France, et Mondial Relay ne demande jamais de frais additionnels.
- **Il te presse.** « Il ne vous reste que 24 heures… », « Payez immédiatement… » : bpost cite ces phrases comme des signes de fraude et dit ne jamais te mettre la pression. La Poste suisse ne menace pas non plus et ne fixe pas de délais extrêmement courts.
- **Le lien n'est pas celui du transporteur.** Les vrais liens de La Poste suisse mènent à ses domaines connus comme post.ch, poste.ch ou swisspost.ch. Méfie-toi des imitations : La Poste suisse a vu passer `post.ch.paysget.info`, bpost montre de fausses adresses comme `noreply@news-mail-bpost.be`.
- **L'expéditeur ne colle pas.** Les faux SMS affichent en général un numéro de mobile en 06 ou 07, note Cybermalveillance.gouv.fr. Mondial Relay écrit depuis noreply@mondialrelay.fr ou noreply@mondialrelay.com, d'après son site belge, et bpost depuis une adresse qui se termine par bpost.be.
- **Il te demande de rappeler, d'envoyer un SMS ou d'installer une app.** Mondial Relay ne te demande jamais d'appeler un numéro surtaxé, d'envoyer un SMS pour suivre ton colis ni de télécharger un fichier, dit-il sur son site belge. La Poste suisse, elle, te dit de n'installer ses apps que depuis l'App Store ou Google Play, jamais via un lien reçu par message.

La langue peut aussi te mettre la puce à l'oreille : La Poste suisse écrit en allemand, en français ou en italien, parfois en anglais. Un logo familier, en revanche, ne prouve rien : La Poste suisse prévient que les faux sites et les fausses apps peuvent imiter son logo, ses couleurs et sa mise en page.

### Comment lire un lien avant de cliquer

Un lien piégé commence souvent par le nom du transporteur. Ce qui compte, c'est la fin du nom de domaine, juste avant le premier `/` seul : c'est elle qui dit à qui appartient l'adresse. Voici un exemple inventé au nom de Royal Mail, la poste britannique (le piège est le même avec laposte.fr ou bpost.be) :

:::anatomy royalmail.co.uk. redelivery-fee.example /pay
- royalmail.co.uk. | Le nom du transporteur, placé en tête comme appât. Ici, ce n'est qu'une étiquette
- redelivery-fee.example | Le vrai propriétaire de l'adresse : pas Royal Mail
- /pay | La page qui réclame ta carte
:::

Même si un lien a l'air correct, ne l'utilise pas : tape toi-même l'adresse du transporteur.

## Ce que les transporteurs t'envoient vraiment

Les vrais transporteurs t'écrivent aussi, et c'est pour ça que les faux marchent. Voici ce que chacun dit envoyer, et ce qu'il dit ne jamais faire :

| Transporteur | Comment il te contacte | Ce qu'il dit ne jamais faire |
| --- | --- | --- |
| [Mondial Relay](carrier:mondial-relay) | E-mails de noreply@mondialrelay.fr ou .com, parfois de la boutique (Vinted, eBay…) | Te faire payer des frais additionnels, te demander un virement, un SMS pour suivre le colis ou un appel surtaxé |
| [bpost](carrier:bpost) | E-mails d'adresses qui se terminent par bpost.be ; liens qui commencent en général par communication.bpost.be, parcel.bpost.be ou track.bpost.cloud | Te mettre la pression ou te menacer |
| [La Poste suisse](carrier:swiss-post) | Liens vers ses domaines post.ch, poste.ch, posta.ch, swisspost.ch ou swisspost.com | Te demander par SMS, e-mail ou WhatsApp des mots de passe, des codes ou ta carte complète |

Les vrais transporteurs envoient donc des liens : un lien seul ne prouve rien, ni dans un sens ni dans l'autre. Les vraies demandes de paiement existent aussi, presque toujours pour des frais d'importation : la douane précise que le transporteur (La Poste, Chronopost ou FedEx, par exemple) peut te les réclamer à la réception du colis. Pour les reconnaître, lis [comment repérer un faux SMS de frais de douane](guide:customs), et vérifie toujours sur le site du transporteur.

## Les faux SMS de colis qui circulent

Les mêmes scénarios reviennent sans cesse. Ceux-ci sont cités par les transporteurs sur leurs pages d'alerte, ou par Cybermalveillance.gouv.fr :

- **Colis retenu, frais à payer.** Le message affirme que ton colis est retenu, puis te demande de payer des frais ou de confirmer tes données. La Poste suisse cite parmi les faux repérés « Colis retenu dans le terminal » et un « délai de 24 heures », fait pour te mettre la pression.
- **Frais de port ou de douane.** Cybermalveillance.gouv.fr cite les messages qui réclament un affranchissement manquant, des frais de port, la TVA ou des frais de douane. Certaines demandes de douane sont vraies : vérifie-les comme plus haut.
- **Le faux livreur.** « bonjour c'est le livreur votre colis ne rentrait pas dans la boîte aux lettres, veuillez choisir un autre créneau ou point relais sur https:… », ou juste « Bonjour vous etes chez vous ? » (Cybermalveillance.gouv.fr).
- **Code SMS ou numéro surtaxé.** La Poste suisse a vu des faux qui demandent d'« Envoyer le code SMS au 41414 » ou renvoient vers des numéros en 0901. Mondial Relay ne te demande jamais d'envoyer un SMS pour suivre un colis.
- **Faux numéro de suivi.** Un numéro qui a l'air tout à fait réel peut être inventé, comme le `34632900-371` relevé par La Poste suisse.
- **WhatsApp ou un appel.** La Poste suisse voit aussi passer des faux sur WhatsApp, et des appels qui poussent à rappeler, à payer ou à donner tes données.
- **Un faux acheteur sur Vinted.** Tu vends quelque chose ? bpost prévient que de faux acheteurs, sur Marketplace ou Vinted, envoient des liens vers de faux sites bpost. La Poste suisse signale aussi de faux ordres de prise en charge à son nom dans les petites annonces.

## Comment vérifier un SMS de colis sans risque

Aucune de ces étapes n'utilise le message :

:::steps
- Demande-toi si tu attends quelque chose | Pas de commande, personne qui t'envoie un colis ? Alors considère que c'est une arnaque.
- Retrouve le vrai numéro de suivi | Prends celui de l'e-mail d'expédition ou de ta commande, jamais celui du SMS.
- Passe toi-même par le transporteur | Tape son adresse ou ouvre son app. Ne touche, ne scanne et n'appelle rien de ce qui est dans le message.
- Cherche une vraie demande | Des frais vraiment dus apparaissent sur le site ou dans l'app du transporteur.
- Signale-le, puis supprime-le | Au 33700 en France, à suspect@safeonweb.be en Belgique, à l'OFCS en Suisse (détails plus bas).
:::

Cybermalveillance.gouv.fr conseille de vérifier le vrai suivi de ton colis ou de contacter directement le transporteur. Mondial Relay te dit, sur son site belge, de toujours suivre ton colis sur mondialrelay.be/fr-be/suivi-de-colis, et La Poste suisse de le suivre uniquement sur poste.ch ou dans son app officielle. Tu ne trouves pas le numéro ? Lis [où trouver ton numéro de suivi](guide:find-tracking-number). Tu peux aussi le coller dans [Peek](/), qui interroge le transporteur et, si besoin, d'autres sources de suivi : tu vois les derniers scans sans passer par le lien.

Pour les vrais avis de passage et la nouvelle livraison quand le livreur t'a vraiment raté, lis [avis de passage : que faire](guide:missed-delivery).

## Arnaque colis par SMS : que faire si tu as cliqué ?

Tout dépend de ce que tu as fait sur la page :

:::steps
- Ferme la page | Ne saisis plus rien. Ne réponds pas, même « STOP ».
- Appelle ta banque | Au numéro inscrit sur ta carte ou dans ton app bancaire, et fais opposition sur ta carte.
- Change tes mots de passe | Partout où tu as tapé ou réutilisé celui que tu as saisi.
- Méfie-toi des appels | Quelqu'un t'appelle ensuite, même « de ta banque » ou du transporteur ? Raccroche et rappelle un numéro officiel que tu as trouvé toi-même.
- Porte plainte | À la police (ou à la gendarmerie en France), si tu as perdu de l'argent ou des données.
:::

**Tu as seulement cliqué ?** « Ne saisissez aucune donnée et fermez la page », conseille La Poste suisse, puis signale le message.

**Tu as saisi ton numéro de carte ou payé les frais ?** « Faites opposition immédiatement », dit Cybermalveillance.gouv.fr, et garde les preuves (le message, une capture d'écran du site). En Belgique, si tu as donné ton code PIN, bpost te dit d'appeler tout de suite Card Stop au 078 170 170 ou ta banque. En Suisse, La Poste te dit de modifier tes mots de passe et de bloquer les moyens de paiement concernés.

En France, si des débits ont eu lieu, Service-public.fr te dit de contacter ta banque pour faire opposition, contester les opérations et obtenir un remboursement. Des achats en ligne que tu n'as pas faits apparaissent sur ton compte ? Une fois l'opposition faite, et si tu as toujours ta carte, tu peux les signaler sur Perceval. Perceval ne couvre pas les frais que tu as payés toi-même sur le faux site. Et même un montant minuscule compte : DPD en Allemagne prévient que ces formulaires de paiement peuvent t'abonner à d'autres prélèvements, alors surveille tes relevés.

## Où signaler une arnaque colis par SMS ?

Signale le SMS ou le mail même si tu n'as pas cliqué, puis supprime-le.

| Pays | Signaler le message | Argent ou données perdus |
| --- | --- | --- |
| France | Transfère le SMS au 33700, où tu peux aussi signaler un appel ; les e-mails sur Signal Spam, les sites sur Phishing Initiative ; les faux La Poste à phishing@laposte.fr, les faux Chronopost à abuse@chronopost.fr | Plainte au commissariat ou à la gendarmerie, ou par courrier au procureur ; aide sur 17Cyber ; France Victimes au 116 006 ; Info Escroqueries au 0 805 805 817 |
| Belgique | Transfère le message à suspect@safeonweb.be (pour un SMS, une capture d'écran) ; les faux bpost à abuse@bpost.be | Card Stop au 078 170 170 ou ta banque, puis plainte à la police locale |
| Suisse | [Formulaire de signalement de l'OFCS](https://www.report.ncsc.admin.ch/fr/) ; les e-mails à reports@antiphishing.ch | Plainte à la police cantonale, ou en ligne via Suisse ePolice dans certains cantons |

Cybermalveillance.gouv.fr présente le 33700 comme un service gratuit ; il ne vaut que pour les SMS et appels reçus sur un mobile. L'OFCS ne prend pas de plainte : pour ça, il faut passer par la police.

:::sources
- [Service-public.fr : Hameçonnage (phishing ou vishing)](https://www.service-public.gouv.fr/particuliers/vosdroits/F34800) – prétexte du colis, opposition, 33700 pour les SMS et les appels, plainte
- [Service-public.fr : Perceval](https://www.service-public.gouv.fr/particuliers/vosdroits/R46526) – conditions du signalement
- [Cybermalveillance.gouv.fr : Hameçonnage](https://www.cybermalveillance.gouv.fr/tous-nos-contenus/fiches-reflexes/hameconnage-phishing) – opposition, preuves, 33700 gratuit, France Victimes
- [Cybermalveillance.gouv.fr : L'hameçonnage à la livraison de colis](https://www.cybermalveillance.gouv.fr/tous-nos-contenus/actualites/escroqueries-livraison-colis) – ce que La Poste, Chronopost et Mondial Relay ne demandent jamais, numéros en 06 ou 07, faux livreur, frais réclamés, vrai suivi, phishing@laposte.fr, abuse@chronopost.fr
- [17Cyber](https://17cyber.gouv.fr/) – aide en ligne de la police et de la gendarmerie
- [Justice.fr : Arnaque, fraude, escroquerie](https://www.justice.fr/je-suis-victime/atteintes-biens/arnaque-fraude-escroquerie) – Info Escroqueries
- [33700](https://www.33700.fr/) – signalement des SMS et appels indésirables
- [Mondial Relay Belgique : Information fraude](https://www.mondialrelay.be/fr-be/informations-fraude/) – ce qu'il ne demande jamais, e-mails, suivi
- [Douane : Vous achetez sur internet](https://www.douane.gouv.fr/demarche/vous-achetez-sur-internet) – taxes demandées à la réception par le transporteur
- [bpost : Phishing](https://www.bpost.be/fr/phishing) – adresses, liens, phrases de pression, Vinted, Card Stop si tu as donné ton code PIN, abuse@bpost.be
- [Police : Pourquoi envoyer les messages suspects à suspect@safeonweb.be ?](https://www.police.be/5912/actualites/pourquoi-envoyer-les-messages-suspects-suspectsafeonwebbe) – captures d'écran de SMS
- [La Poste suisse : Reconnaître le phishing et les escroqueries au nom de la Poste](https://site.post.ch/fr/notre-profil/securite/hameconnage-et-fraude) – faux courants, jamais de mot de passe ni de carte complète, fermer la page, bloquer les moyens de paiement
- [La Poste suisse : Reconnaître le phishing, vérifier si les messages de la Poste sont authentiques](https://site.post.ch/fr/notre-profil/securite/hameconnage-et-fraude/reconnaitre-le-hameconnage) – domaines, langues, pas de menaces, apps, WhatsApp
- [La Poste suisse : Tentatives d'escroquerie actuelles](https://site.post.ch/fr/notre-profil/securite/hameconnage-et-fraude/tentatives-de-fraude-actuelles) – suivi sur poste.ch, 41414, 0901, terminal, 24 heures, faux numéro, logo imité, appels, WhatsApp, petites annonces
- [La Poste suisse : Signaler une tentative d'escroquerie au nom de la Poste](https://site.post.ch/fr/notre-profil/securite/hameconnage-et-fraude/signaler-une-fraud) – OFCS, reports@antiphishing.ch, police cantonale, Suisse ePolice
- [OFCS : Formulaire de signalement](https://www.report.ncsc.admin.ch/fr/) – signalement en ligne
- [OFCS : Annoncez à l'OFCS](https://www.bacs.admin.ch/fr/annoncez-a-l-ofcs) – l'OFCS n'enregistre pas les plaintes pénales
- [DPD Allemagne : Sicherheit (en allemand)](https://www.dpd.com/de/de/sicherheit/) – formulaires qui déclenchent d'autres paiements
:::
