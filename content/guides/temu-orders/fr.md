---
title: Suivi de commande Temu : numéro de commande ou de suivi ?
description: Pourquoi ton numéro de commande Temu ne sert pas à suivre le colis, comment trouver le transporteur et comment Peek le suit si un transporteur local prend le relais.
slug: suivi-commande-temu
picture: Une pile de petits colis qui sort de la soute d'un avion, une camionnette, et Pip qui les compte.
published: 2026-10-17
updated: 2026-10-27
---

Ton numéro de commande Temu ne permet pas de suivre ton colis : il te faut le numéro de suivi du transporteur. Colle-le dans [Peek](/) : il retrouve le transporteur parmi plus de 3 500, puis suit le partenaire local si le premier transporteur en nomme un. Si tu colles le numéro de commande à la place, Peek te signale qu'il ressemble à un numéro de commande Temu, pas à un numéro de suivi.

## Mon numéro de commande Temu est-il un numéro de suivi ?

Non. Le numéro de commande Temu est sa propre référence, pas un numéro de suivi : aucun transporteur ne retrouvera ton colis avec. Peek le reconnaît à sa forme, PO suivi de 20 chiffres (`PO-000-00000000000000000`), et le met de côté en te signalant qu'il ressemble à un numéro de commande Temu. Tu ne sais pas lequel tu as ? Tape-le ici :

:::checker
:::

Si tu colles un message entier qui contient les deux numéros, Peek prend le numéro de suivi et laisse le numéro de commande de côté. Où les autres boutiques cachent le numéro dans leurs e-mails et ton compte : [trouver son numéro de suivi](guide:find-tracking-number).

## Quel transporteur livre mon colis Temu ?

En général, le numéro de suivi le dit : le champ ci-dessus nomme le transporteur que désigne sa forme, ou les quelques-uns à qui il peut appartenir. Qui tu verras sans doute sur la dernière étape, de La Poste (Colissimo) à bpost et La Poste suisse, est dans le tableau par pays du [suivi d'un colis venant de Chine](guide:tracking-from-china). Faut-il payer quelque chose à la frontière ? Ça dépend de ton pays : [douane](guide:customs).

En France et en Belgique, depuis le 1er juillet 2026, les achats en ligne hors UE de 150 € maximum paient 3 € de droits de douane par article, c'est-à-dire par type de produit, pas par pièce. À partir du 1er novembre 2026, l'UE ajoute des frais de traitement de 2 € par article, comme les 3 €, annonce la Commission européenne. Rien de tout cela en Suisse.

## Colis Temu bloqué, en retard ou jamais arrivé ?

Un suivi qui ne bouge plus pendant des jours, ou un numéro qui change quand le transporteur local prend le relais, c'est normal sur ce trajet : le [suivi d'un colis venant de Chine](guide:tracking-from-china) explique les deux, et ce qui change pour un produit parti d'un entrepôt en Europe. Combien de jours sans nouvelle ligne restent normaux, au bout de combien de temps un colis est déclaré perdu, et qui contacter, le vendeur ou le transporteur : [suivi de colis qui ne bouge plus](guide:tracking-not-updating).

Qui est responsable d'un colis jamais arrivé, et quelles preuves garder : [colis livré mais non reçu](guide:delivered-not-received) et [colis perdu](guide:lost-parcel). Pendant l'attente, méfie-toi des faux messages de livraison. Comment les repérer : [SMS frauduleux sur les colis](guide:delivery-scam-texts).

## Peut-on suivre un colis Temu dans Peek ?

Oui, avec le numéro de suivi du transporteur. Colle-le dans [Peek](/) : il retrouve le transporteur parmi plus de 3 500. Pour une forme partagée par plusieurs transporteurs, appuie sur « Changer » et choisis le bon.

Quand le premier transporteur nomme le partenaire local qui prend le relais, Peek garde le premier historique, suit le partenaire dès que son suivi montre du nouveau, et affiche sur la carte le nom et le numéro du partenaire.

Tu as plusieurs numéros de suivi ? Ajoute-les tous : Peek suit chaque colis séparément. Le numéro de commande Temu n'est pas un numéro de suivi, et Peek ne peut pas le suivre.

Peek affiche ce que publie le transporteur. Une réclamation ou un remboursement restent dans ton compte Temu. Un retour a son propre numéro de suivi, différent de celui de ta commande. Où le trouver et quand arrive le remboursement : [suivre un retour](guide:returns-tracking).

:::sources
- [Commission européenne : EU Customs Reform (en anglais)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 2 € au 1er novembre 2026
- [Commission européenne : Guidance on EUR 3 customs duty (PDF, en anglais)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 3 € par article jusqu'à 150 € depuis le 1er juillet 2026, 2 € par article, par ligne de déclaration
- [Universal Parcel Scraper : données transporteurs](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers) – ce que Peek vérifie
:::
