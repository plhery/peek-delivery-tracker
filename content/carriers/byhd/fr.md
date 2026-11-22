---
title: Suivi BYHD : numéros BYHD, statuts et transporteur final
description: Suis un colis BYHD (博益弘达) depuis Shenzhen : format du numéro BYHD, statuts connus, second numéro du transporteur final et suivi dans Peek.
slug: suivi-byhd
published: 2026-11-22
updated: 2026-11-22
---

BYHD (博益弘达, Bo Yi Hong Da en pinyin) est le nom court de 深圳市博益弘达物流有限公司, un transitaire basé à Shenzhen, en Chine, qui expédie des colis à l’international pour des vendeurs en ligne. Ses numéros de bordereau sont composés de `BYHD` suivi de neuf chiffres : colle le tien dans le champ ci-dessus, ou saisis-le dans la case 快递查询 (recherche de colis) du site de BYHD, byhd56.com, qui ouvre son outil de suivi. Attends-toi à un historique court chez BYHD et à un second numéro : celui attribué par le transporteur chargé de la livraison finale.

## Numéro de suivi BYHD : à quoi il ressemble

BYHD ne publie aucun format officiel. Les bordereaux que son outil reconnaît sont `BYHD` suivis de neuf chiffres. Avec des chiffres inventés :

| Numéro | Ce que c’est |
| --- | --- |
| `BYHD482917365` | `BYHD` et neuf chiffres : un numéro de bordereau BYHD. |
| `BYHD48291736` | Seulement huit chiffres : ne correspond pas au format BYHD. Vérifie s’il manque un chiffre. |
| `RR482917367CN` | Deux lettres, neuf chiffres et `CN` : un numéro [China Post](carrier:china-post), pas BYHD. |

Les minuscules et les espaces ne posent aucun problème lorsque tu colles le numéro dans Peek.

**Où le trouver.** BYHD travaille pour les vendeurs (son site leur propose connexion, inscription et calcul de tarifs), donc le numéro vient de la boutique auprès de laquelle tu as commandé, dans ses informations d’expédition. Si tu ne le trouves pas, consulte [comment trouver son numéro de suivi](guide:find-tracking-number).

**Un second numéro sur le suivi BYHD.** L’outil de suivi de BYHD retrouve la commande grâce au numéro BYHD, mais ne réaffiche pas ce numéro. Celui qui figure sous 跟踪号码 (numéro de suivi) est le numéro attribué par le transporteur qui livre le colis, et 参考号 (référence) est la référence propre au vendeur. BYHD ne précise pas à quel transporteur appartient ce numéro, mais son format permet souvent de l’identifier : voir [formats des numéros de suivi](guide:tracking-number-formats).

**Référence de boutique.** L’outil de BYHD retrouve aussi une commande à partir de la référence du vendeur, saisie exactement telle que le vendeur l’a écrite, tirets compris.

## Statuts de suivi BYHD expliqués

BYHD ne publie aucune liste officielle de statuts et son suivi mêle anglais et chinois. Voici les libellés connus à ce jour, dans l’ordre où un colis les rencontre :

| BYHD indique | Ce que cela signifie pour toi |
| --- | --- |
| `The electronic cargo information has been received` (ou `货物电子信息已经收到`) | Le vendeur a créé la commande et transmis ses données à BYHD. BYHD n’a pas encore scanné le colis. |
| `到达收货点` | « Arrivé au point de réception » : BYHD a pris le colis en charge. |
| `Goods leaving the operation center` | Le colis a quitté le centre opérationnel de BYHD. |

La première ligne n’est pas un vrai scan : Hualei (华磊), la plateforme de transitaires utilisée par BYHD — comme par [Lingdong](carrier:lingdong) et Yilong Logistics — l’ajoute automatiquement pour toute commande encore sans scan.

Les horaires de BYHD ne portent pas de fuseau horaire. Ce sont des heures de Pékin : une ligne à 08 h 00 là-bas s’est donc produite la veille au soir à l’heure de New York.

## Qui livre les colis BYHD

Le suivi BYHD indique le numéro du transporteur qui livre dans ton pays, mais pas son nom, et son site ne cite aucun partenaire de livraison pour quelque pays que ce soit. BYHD ne publie ni délais de livraison ni règles sur les tentatives, les points de retrait ou la durée de garde : tout cela dépend du transporteur final. Si la forme du second numéro ne suffit pas à l’identifier, demande au vendeur. On te demande des droits d’importation ? Consulte [colis bloqué en douane](guide:customs).

## Colis BYHD qui n’arrive pas ou suivi bloqué

**Seulement `The electronic cargo information has been received`.** Le vendeur a enregistré l’envoi, mais BYHD ne l’a pas encore scanné. Si rien ne suit au bout de quelques jours, demande au vendeur si le colis a bien été expédié.

**Bloqué sur `Goods leaving the operation center`.** C’est la dernière étape connue côté BYHD : le silence qui suit ne signifie donc pas que le colis est immobilisé. Suis-le avec le numéro du transporteur final indiqué sur le suivi de BYHD, ou demande-le au vendeur. Voir [suivre un colis venant de Chine](guide:tracking-from-china) et [pourquoi le suivi ne se met plus à jour](guide:tracking-not-updating).

**Rien de trouvé.** Pour un numéro inconnu, le suivi BYHD affiche un résultat portant le numéro sous 参考号 (référence) avec tous les autres champs vides. Peek interprète cette réponse comme un envoi listé sans scan pour le moment : il continue de vérifier (sans compte, pendant les 6 premières heures puis à chaque ouverture du lien et jusqu’à 30 jours si les notifications sont activées) et affiche « En attente de BYHD », sans demander de revérifier le code. Compare donc toi-même le numéro caractère par caractère avec le message du vendeur.

**Perdu, endommagé ou retourné.** BYHD ne publie aucune procédure de réclamation et sa page de mentions légales est vide. Comme c’est le vendeur qui a réservé l’expédition chez BYHD, demande au vendeur ou à la place de marché un remboursement ou un renvoi. Voir [colis perdu](guide:lost-parcel), [colis retourné à l’expéditeur](guide:returned-to-sender) et [marqué livré mais pas reçu](guide:delivered-not-received).

## Contact et service client BYHD

BYHD n’indique aucune ligne dédiée aux destinataires. Son site, uniquement en chinois, mentionne un numéro de mobile associé à un contact nommé, sans horaires, ainsi qu’une adresse dans le district de Longhua à Shenzhen (深圳市龙华区). Il n’affiche aucune adresse e-mail, aucun formulaire de contact et aucun bureau hors de Chine. Pour toute question sur ta commande, adresse-toi au vendeur.

## Suivre un colis BYHD avec Peek

Colle un numéro BYHD dans [Peek](/) et il est reconnu immédiatement : aucun autre transporteur du catalogue Peek n’utilise ce format. Il n’existe pas de lien de suivi BYHD à coller, car son outil fonctionne par formulaire. Une référence de vendeur n’a pas de format propre, mais Peek suit plus de 4 500+ transporteurs : ajoute-la quand même puis change le transporteur pour choisir BYHD (taper 博益弘达 ou Boyi Hongda le trouve aussi). Peek retire toutefois les tirets : si une référence contient des tirets, recherche-la directement dans la case de BYHD.

Peek lit directement le suivi de BYHD et affiche chaque ligne dans le texte de BYHD avec son lieu lorsqu’il existe, ainsi que le pays de destination quand le dossier le renseigne. `The electronic cargo information has been received` s’affiche en « Étiquette créée », `到达收货点` en « Pris en charge » et `Goods leaving the operation center` en « En transit ». Les horaires sont lus sur l’heure de Pékin et affichés dans ton fuseau horaire (l’historique se termine par « Heures dans ton fuseau horaire : » suivi de son nom). Une ligne datée après le moment où Peek interroge BYHD attend jusqu’à cette heure, et une ligne enregistrée plus de douze heures avant l’heure qu’elle affiche (ou plus de six heures avant dans la même seconde qu’une telle ligne) est écartée comme prévision, non comme vrai scan. Dans ce cas, Peek prévient que « Le site de BYHD affiche tout le trajet à l’avance, livraison comprise. Ces étapes ne sont pas de vrais scans. » (ou, pour une livraison annoncée à l’avance, « Le site de BYHD affiche une livraison inscrite à l’avance, pas un vrai scan. Si rien n’est arrivé, contacte le vendeur. »). Les noms, adresse, codes postaux, poids, frais, référence vendeur, signature et preuve de livraison restent hors de Peek, et aucune date estimée n’est affichée car BYHD n’en donne pas.

**Le numéro du transporteur final.** Pour une commande que BYHD n’a pas encore scannée, Peek lit ce second numéro sur la page de suivi BYHD et interroge un transporteur : la poste du pays de destination (comme [USPS](carrier:usps) ou [Royal Mail](carrier:royal-mail)) s’il s’agit d’un numéro postal international (deux lettres, neuf chiffres et deux lettres), sinon l’unique transporteur dont le format correspond, si Peek le suit directement. Dès que ce transporteur montre un scan au moins aussi récent que la dernière ligne BYHD, Peek bascule le suivi chez lui tout en gardant les lignes de BYHD. Une fois que BYHD affiche ses propres scans, Peek reste sur BYHD et n’interroge pas d’autre transporteur : le second numéro apparaît dans les détails sous « BYHD le suit sous le numéro », et tu peux l’ajouter séparément dans Peek pour suivre la livraison finale.

Tant que la fiche du colis est ouverte à l’écran — ou le jour de la livraison si les notifications sont activées — Peek interroge directement BYHD jusqu’à toutes les 10 minutes (sans cadence de 2 minutes, car les statuts connus de BYHD ne comportent pas de mise en livraison). Sinon, et dès qu’il reste silencieux (après 12 heures sur la première ligne ou le premier scan, ou après deux jours sans nouveauté ensuite), Peek vérifie toutes les heures. Si le dossier de BYHD reste sur sa première ligne au bout de deux jours, Peek interroge aussi des services de suivi (toutes les heures, toutes les 3 heures après une journée sans scan, et jusqu’à toutes les 15 minutes le jour de la livraison si le colis est ouvert à l’écran ou si les notifications sont activées) et affiche leur historique s’il va plus loin. Comme les lignes connues de BYHD sont toutes des étapes initiales, seul « Chaque scan » te les signale ; « Étapes importantes » et « Livraison seulement » attendent des étapes ultérieures. Après quatre jours sans scan en transit, Peek affiche dans ta liste la date du dernier scan (« Aucune mise à jour depuis … ») et, sur la fiche, « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger BYHD. ».

## Questions sur le suivi BYHD

### Qu’est-ce que BYHD (博益弘达) ?

C’est 深圳市博益弘达物流有限公司, une société logistique du district de Longhua à Shenzhen dont le site est byhd56.com. Sa présentation décrit du transport transfrontalier maritime et aérien et des services aux vendeurs en ligne (premier tronçon Amazon FBA, expédition depuis des entrepôts à l’étranger) vers l’Europe, l’Amérique, l’Asie du Sud-Est et le Moyen-Orient. Ses numéros de bordereau sont `BYHD` suivis de neuf chiffres.

### Pourquoi le suivi BYHD affiche-t-il un autre numéro de suivi ?

C’est le numéro du transporteur qui assure la livraison finale, et non celui de BYHD : le suivi retrouve ta commande avec le numéro BYHD, mais affiche celui attribué par le transporteur final. Suis ce numéro chez ce transporteur ou ajoute-le séparément dans Peek.

### Qui livre les colis BYHD ?

BYHD ne l’indique pas : son suivi ne donne aucun nom de transporteur et son site ne liste aucun partenaire par pays. Le second numéro affiché sur le suivi appartient au transporteur final ; son format, ou le vendeur, te dira lequel.

### Combien de temps met BYHD pour livrer ?

BYHD ne publie aucun délai de livraison. Fie-toi à l’estimation donnée par la boutique pour ta commande.

### Qui contacter pour un colis BYHD manquant ?

Le vendeur chez qui tu as commandé, car c’est lui qui a confié le colis à BYHD. BYHD n’indique sur son site qu’un numéro de mobile de contact et une adresse à Shenzhen, sans service destiné aux destinataires.

:::sources
- [BYHD : 深圳市博益弘达物流有限公司 (accueil)](http://www.byhd56.com/) – raison sociale, champ 快递查询 et suivi associé, connexion, inscription et devis pour expéditeurs, adresse en pied de page
- [BYHD : 公司介绍 (présentation)](http://www.byhd56.com/about.html) – siège à Shenzhen, services transfrontaliers pour vendeurs en ligne, lignes maritimes et aériennes, régions desservies
- [BYHD : 联系我们 (contact)](http://www.byhd56.com/contact.html) – numéro de mobile d’un contact nommé, sans horaires
- [BYHD : 法律声明 (mentions légales)](http://www.byhd56.com/legal.html) – page sans contenu
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – plateforme Hualei, format BYHD, second numéro et référence vendeur, recherche par référence avec tirets, libellés et affichage dans Peek, heure de Pékin, réponse pour un numéro inconnu, données affichées, retenues ou ignorées
:::
