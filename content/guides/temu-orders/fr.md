---
title: Suivi de commande Temu : quel transporteur a ton colis ?
description: Suis ton colis Temu avec le numéro du transporteur, pas le numéro de commande : qui livre en France et en Belgique, et pourquoi le suivi reste vide.
slug: suivi-commande-temu
picture: Une pile de petits colis qui sort de la soute d'un avion, une camionnette, et Pip qui les compte.
published: 2026-10-17
updated: 2026-11-07
---

Pour suivre ta commande Temu, il te faut le numéro de suivi du transporteur, pas le numéro de commande Temu (PO et 20 chiffres). Colle-le dans [Peek](/) : il retrouve le transporteur parmi plus de 3 500, de La Poste (Colissimo) à bpost, puis suit le partenaire local si le premier transporteur en nomme un. Un numéro tout neuf peut ne rien afficher pendant un jour ou deux : l'étiquette existe avant que le transporteur ait le colis.

## Mon numéro de commande Temu est-il un numéro de suivi ?

Non. Le numéro de commande Temu est sa propre référence, pas un numéro de suivi : aucun transporteur ne retrouvera ton colis avec. Peek le reconnaît à sa forme, PO suivi de 20 chiffres (`PO-000-00000000000000000`), et le met de côté avec une note qui te dit où chercher : dans Vos commandes, touche Suivre à côté de la commande, et Temu y donne un numéro de suivi par colis. Tu ne sais pas lequel tu as ? Tape-le ici :

:::checker
:::

Si tu colles un message entier qui contient les deux numéros, Peek prend le numéro de suivi et laisse le numéro de commande de côté. Où les autres boutiques cachent le numéro dans leurs e-mails et ton compte : [trouver son numéro de suivi](guide:find-tracking-number).

Un numéro de trois lettres, un `U` et sept chiffres, comme `TEMU1234565` (inventé), n'est pas non plus un numéro de suivi : c'est celui d'un conteneur maritime, et aucun transporteur ne s'en sert pour suivre un colis. Le `TEMU` du début n'a rien à voir avec la boutique : dans le registre des codes propriétaires de conteneurs tenu par le Bureau International des Containers (BIC), `TEMU` est celui du loueur de conteneurs Textainer. Si tu en ajoutes un et qu'aucun transporteur ne répond, Peek affiche « On dirait un numéro de conteneur maritime. Le numéro de suivi figure dans l’e-mail d’expédition ou sur la page de la commande. » et te propose « Choisir le transporteur ».

## Quel transporteur livre mon colis Temu ?

En général, le numéro de suivi le dit : le champ ci-dessus nomme le transporteur que désigne sa forme, ou les quelques-uns à qui il peut appartenir. Les formes que tu croiseras sans doute en France, en Belgique et en Suisse :

| Le numéro ressemble à | Transporteur |
| --- | --- |
| 2 lettres, 9 chiffres, `CN` | [China Post](carrier:china-post) : ta poste le livre sous le même numéro |
| 1 chiffre, 1 lettre, 11 chiffres | [La Poste](carrier:la-poste) (Colissimo) |
| 2 lettres, 9 chiffres, `FR` | [La Poste](carrier:la-poste) |
| 24 chiffres commençant par `3232` ou `3299`, ou 2 lettres, 9 chiffres, `BE` | [bpost](carrier:bpost) |
| 2 lettres, 9 chiffres, `CH`, ou 18 chiffres commençant par `98` ou `99` | [La Poste suisse](carrier:swiss-post) |

La Poste achemine les colis Temu depuis l'arrivée de Temu en France en 2023, et un accord signé en octobre 2025 prolonge cette collaboration pour trois ans. En Belgique, Temu et bpostgroup ont annoncé en novembre 2025 un protocole d'entente pour renforcer leur collaboration. Qui d'autre livre sur la dernière étape : le tableau par pays du [suivi d'un colis venant de Chine](guide:tracking-from-china). Les autres formes : [à quel transporteur appartient un numéro de suivi](guide:tracking-number-formats).

Faut-il payer quelque chose à la frontière ? Ça dépend de ton pays ([douane](guide:customs)). En France et en Belgique, depuis le 1er juillet 2026, les achats en ligne hors UE de 150 € maximum paient 3 € de droits de douane par article, c'est-à-dire par type de produit, pas par pièce. Depuis le 1er novembre 2026, l'UE ajoute des frais de traitement de 2 € par article, comme les 3 €, selon la Commission européenne. En France, si c'est La Poste qui livre, ce que Temu n'a pas encaissé à la commande se paie à l'arrivée du colis : en ligne avant la livraison, au facteur ou au bureau de poste. Rien de tout cela en Suisse.

## Pourquoi mon numéro de suivi Temu n'affiche rien ?

Le numéro existe souvent avant que le transporteur ait le colis. Jusqu'au premier scan, Peek affiche l'un de ces messages :

- **Étiquette créée** : le transporteur a l'étiquette, pas le colis (Colissimo écrit `Votre Colissimo va bientôt nous être confié ! Il est en cours de préparation chez votre expéditeur.`). Au bout d'un jour, Peek précise que l'expéditeur ne lui a pas encore remis le colis ; le suivi commencera à la remise.
- **Probablement** et le nom d'un transporteur : le numéro a la forme des siens, mais ce transporteur ne le connaît pas encore. Peek continue de l'interroger et affiche le colis dès que le transporteur le connaît. Toujours rien au bout d'une semaine ? Vérifie le numéro auprès de Temu, ou choisis un autre transporteur.
- **Transporteur pas encore trouvé** : aucun transporteur ne connaît le numéro. C'est normal un jour ou deux après la commande, et Peek continue de vérifier.

Un colis venu de Chine par avion peut rester muet bien plus longtemps : jusqu'à 11 jours avant son premier scan, puis rien pendant le vol.

## Colis Temu bloqué, en retard ou jamais arrivé ?

Un suivi qui ne bouge plus pendant des jours, ou un numéro qui change quand le transporteur local prend le relais, c'est normal sur ce trajet : le [suivi d'un colis venant de Chine](guide:tracking-from-china) explique les deux, et ce qui change pour un produit parti d'un entrepôt en Europe. Combien de jours sans nouvelle ligne restent normaux, au bout de combien de temps un colis est déclaré perdu, et qui contacter, le vendeur ou le transporteur : [suivi de colis qui ne bouge plus](guide:tracking-not-updating).

Qui est responsable d'un colis jamais arrivé, et quelles preuves garder : [colis livré mais non reçu](guide:delivered-not-received) et [colis perdu](guide:lost-parcel). Pendant l'attente, méfie-toi des faux messages de livraison. Comment les repérer : [SMS frauduleux sur les colis](guide:delivery-scam-texts).

## Peut-on suivre un colis Temu dans Peek ?

Oui, avec le numéro de suivi du transporteur. Pour une forme partagée par plusieurs transporteurs, appuie sur « Changer » et choisis le bon.

Quand le premier transporteur nomme le partenaire local qui prend le relais, Peek garde le premier historique, suit le partenaire dès que son suivi montre du nouveau, et affiche sur la carte le nom et le numéro du partenaire.

Tu as plusieurs numéros de suivi ? Ajoute-les tous : Peek suit chaque colis séparément. Une fois que le transporteur a le colis, Peek affiche la date de livraison qu'il prévoit, quand il en donne une.

Peek affiche ce que publie le transporteur. Une réclamation ou un remboursement restent dans ton compte Temu. Un retour a son propre numéro de suivi, différent de celui de ta commande. Où le trouver et quand arrive le remboursement : [suivre un retour](guide:returns-tracking).

:::sources
- [La Poste Groupe : Accord La Poste-Temu, ce qu'il faut savoir](https://www.lapostegroupe.com/fr/actualite/accord-la-poste-temu-ce-qu-il-faut-savoir) – colis Temu depuis 2023, accord de trois ans signé en octobre 2025
- [Bnode (ex-bpostgroup) : Temu signe un protocole d'entente avec bpostgroup](https://press.bnode.com/temu-signe-un-protocole-dentente-avec-bpostgroup-pour-renforcer-leur-collaboration-logistique-internationale) – collaboration en Belgique, novembre 2025
- [La Poste : Comment payer les frais de douane pour un colis de l'international](https://www.laposte.fr/conseils-pratiques/comment-payer-frais-de-douane-colis-international) – à la commande ou à l'arrivée, en ligne, au facteur ou au bureau de poste
- [Commission européenne : EU Customs Reform (en anglais)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 2 € au 1er novembre 2026
- [Commission européenne : Guidance on EUR 3 customs duty (PDF, en anglais)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 3 € par article jusqu'à 150 € depuis le 1er juillet 2026, 2 € par article, par ligne de déclaration
- [Universal Parcel Scraper : données transporteurs](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers) – formes des numéros, premiers statuts, ce que Peek vérifie
- [BIC : Container Identification Number (en anglais)](https://www.bic-code.org/identification-number/) – code du propriétaire, U pour les conteneurs de fret, numéro de série à six chiffres et chiffre de contrôle
- [BIC : Registered BIC Code TEMU (en anglais)](https://www.bic-code.org/bic-codes/temu/) – Textainer Equipment Management
- [Textainer (en anglais)](https://www.textainer.com/) – location de conteneurs
- [Peek : Routing (en anglais)](https://github.com/plhery/peek-delivery-tracker/blob/main/docs/ROUTING.md) – numéro de conteneur auquel aucun transporteur ne répond : où est le numéro de suivi, choisir le transporteur
:::
