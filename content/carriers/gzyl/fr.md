---
title: Suivi colis GZYL : numéro YL…YQ, statuts et qui contacter
description: Suivre un colis GZYL (广州云链) venu de Chine : le numéro YL…YQ, ce que veulent dire les statuts et pourquoi ton colis peut s'afficher livré avant d'arriver.
slug: gzyl-suivi-colis
published: 2026-11-03
updated: 2026-11-03
---

GZYL (广州云链, Guangzhou Yunlian) est 广州云链货运代理有限公司, un transitaire de Guangzhou (Canton), en Chine. Si une boutique chinoise a confié ta commande à GZYL, ton numéro de suivi commence par YL et finit par YQ, avec dix chiffres entre les deux : colle-le dans le champ ci-dessus. GZYL n'a pas de site web connu de Peek, et son site de suivi tourne sur une simple adresse IP, sans nom de domaine. Prends ce suivi avec des pincettes : GZYL écrit d'avance une bonne partie du trajet d'un colis, si bien qu'une étape, même `Delivered`, n'y correspond pas toujours à un scan.

## Numéro de suivi GZYL : à quoi il ressemble

Un numéro GZYL, c'est YL, dix chiffres et YQ : 14 caractères en tout. Avec des chiffres inventés :

| Numéro | Ce que c'est |
| --- | --- |
| `YL0000000000YQ` | YL, dix chiffres et YQ : la forme des lettres de transport de GZYL. Peek vérifie le numéro auprès du site de suivi de GZYL. |
| `YL000000000YQ` | Neuf chiffres seulement : ce n'est pas la forme GZYL. Vérifie qu'il ne manque pas un chiffre. |
| `GZXH0000000000YQ` | GZXH, dix chiffres et YQ : Xinghe Freight (星和货运), un autre transitaire. Ses colis se suivent sur le site de suivi de GZYL, avec les mêmes formulations. |
| `JXCAA0000000000YQ` | Finit aussi par YQ, comme chez beaucoup de transitaires chinois, mais commence par JXC : un numéro [Juxi Express](carrier:juxi-express). |

Espaces et minuscules ne gênent pas Peek quand tu colles le numéro.

**Où le trouver.** C'est la boutique qui réserve l'envoi chez GZYL et qui te transmet le numéro. Tu ne le trouves pas ? Lis [où trouver son numéro de suivi](guide:find-tracking-number).

## Suivi GZYL : ce que veulent dire les statuts

GZYL écrit ses propres étapes en anglais, fautes de frappe comprises. Dans l'ordre où un colis les reçoit :

| Ce qu'affiche GZYL | Ce que ça veut dire pour toi | Peek affiche |
| --- | --- | --- |
| `Order information received` | La boutique a transmis à GZYL les données de la commande. GZYL n'a pas encore le colis. | « Étiquette créée » |
| `Arrive at the cross border receiving point` | Premier scan : GZYL a le colis en main. | « Pris en charge » |
| `Goods leave the operation center` | Le colis a quitté le centre d'opérations de GZYL. | « En transit » |
| `Arrive at the international airport` | À l'aéroport, en attente de son vol. | « En transit » |
| `Departing from the airport` | L'avion est parti. | « En transit » |
| `Item arrives at destination airport` | Le colis a atterri dans le pays de destination. | « En transit » |
| `local carrier Agent Import customs clearance` | Dans le pays de destination, un agent s'occupe du dédouanement à l'import. | « À la douane » |
| `The import customs keep it for inspection` | La douane du pays de destination contrôle le colis. | « À la douane » |
| `Customs clearance completed` | Dédouané, le colis repart. | « En transit » |
| `Internationaliy Package received at processing center` | Un centre de traitement du pays de destination a reçu le colis dédouané. Ce n'est pas une nouvelle prise en charge. | « En transit » |
| `Leave the email processing center` | Le colis a quitté ce centre de traitement. | « En transit » |
| `Arrival sorting, distribution center` | Arrivée dans un centre de distribution du pays de destination, dont GZYL écrit la ville après ces mots. | « En transit » |
| `Leaving the sorting center` | Il quitte un centre de tri, dont GZYL donne la ville. | « En transit » |
| `Package is on the way` | Il poursuit sa route dans le pays de destination ; GZYL ajoute le nom d'une ville. Ça ne veut pas dire qu'il est en cours de livraison. | « En transit » |
| `Delivered` | La livraison, avec la ville. Ce peut être une prévision, pas un scan : voir plus bas. | « Livré », avec une note sur le calendrier fixe, sauf si Peek écarte l'étape (voir plus bas) |

Peek affiche la ville écrite après ces mots comme lieu de l'étape. Il garde les étapes telles que GZYL les écrit, sauf `Delivered`, qui apparaît en français dans ton historique : « Livré ».

**Par les États-Unis.** Un colis peut aussi afficher `Los Angeles Processing Center has received it`, suivi d'une ville entre crochets : un centre de traitement aux États-Unis l'a reçu, en route vers le pays de destination. Peek affiche « En transit ».

**Une ligne en chinois.** Pour une commande encore sans scan, la plateforme qui fait tourner le site de suivi de GZYL écrit d'elle-même `货物电子信息已经收到` : les données électroniques de la marchandise ont été reçues. La boutique a créé la commande, mais personne n'a encore scanné le colis. Peek affiche cette ligne en chinois, sous « Étiquette créée ».

**Par China Post.** Un colis peut aussi afficher `广州处理中心已收寄`, suivi de la ville entre crochets : la prise en charge par China Post dans son centre de traitement de Guangzhou, que GZYL relaie en chinois. Peek affiche cette ligne en chinois, sous « Pris en charge ».

**Heures.** Toutes les heures du site de suivi de GZYL sont à l'heure de Pékin (UTC+8), même pour les étapes dans le pays de destination, et le fuseau n'est indiqué nulle part. Une étape affichée à 20 h correspond à 13 h en France et en Belgique en hiver, et à 14 h à l'heure d'été, du dernier dimanche de mars au dernier dimanche d'octobre.

**Étapes écrites à l'avance.** GZYL peut écrire d'avance tout le trajet d'un colis, de la prise en charge jusqu'à sa dernière étape dans ton pays, bien avant les heures que portent ses étapes ; le site de suivi affiche ensuite chacune d'elles quand son heure arrive. Une étape écrite ainsi est une prévision, pas un scan de ton colis. Meiteshun, un transitaire sur la même plateforme de suivi, fait pareil. Chez GZYL, cette prévision se termine ensuite par un `Delivered` que la plateforme ajoute le jour qu'il indique, dans un lot du soir : daté du lendemain de la dernière étape prévue, à une heure de bureau à Pékin, quel que soit ton pays. Les services de suivi qui recopient la fiche de GZYL peuvent eux aussi afficher ces étapes : un `Delivered`, sur le site de suivi de GZYL comme sur un service de suivi, ne prouve donc pas que ton colis est arrivé.

## Qui livre les colis GZYL en France et en Belgique

Peek ne sait pas qui livre les colis GZYL en France ou en Belgique : dans le pays de destination, les étapes de GZYL ne nomment aucune société, et Peek ne transmet le suivi d'un colis GZYL à aucun transporteur français ou belge. Le seul transporteur auquel Peek peut passer le relais, c'est [La Poste suisse](carrier:swiss-post), en Suisse et au Liechtenstein.

Peek n'a trouvé aucun délai de livraison chez GZYL. Tentatives de livraison, points relais, durée de garde : tout cela dépend du transporteur qui livre. Demande à la boutique quelle société livre ta commande, et sous quel numéro.

## Colis GZYL pas arrivé ou suivi bloqué : que faire

**Seulement `Order information received` ou `货物电子信息已经收到`.** La boutique a créé la commande, mais GZYL n'a pas encore scanné le colis. Si rien de nouveau n'apparaît au bout de quelques jours, demande à la boutique si le colis est vraiment parti.

**Bloqué à la douane.** Un colis acheté en ligne hors de l'Union européenne paie, depuis le 1er juillet 2026, 3 € de droits de douane par article s'il vaut 150 € au plus. Depuis le 1er novembre 2026, il faut aussi compter 2 € de frais de traitement par article, quelle que soit la valeur du colis. Un article, c'est un type de produit (une ligne de la déclaration en douane), pas une pièce. Pour le total à payer en France ou en Belgique, TVA comprise, lis [colis bloqué en douane](guide:customs). Si le colis reste des jours à la douane, préviens la boutique.

**Le site de suivi de GZYL, ou un service de suivi, en montre plus que Peek.** Peek écarte les étapes écrites longtemps avant l'heure qu'elles portent, le `Delivered` qui clôt la prévision de GZYL, et les étapes datées d'après sa dernière vérification (détails plus bas) : les étapes en plus sont peut-être des prévisions de GZYL, pas des scans. Fie-toi au délai annoncé par la boutique, et écris-lui une fois ce délai dépassé. Plus d'infos dans [suivi de colis qui ne bouge plus](guide:tracking-not-updating) et [suivi d'un colis venant de Chine](guide:tracking-from-china).

**`Delivered`, mais rien reçu.** L'étape est peut-être une prévision de GZYL, pas un scan. Sauf si La Poste suisse livre le colis, Peek ajoute sous « Livré » : « Le suivi de GZYL avance selon un calendrier fixe et n'indique aucune signature. Si rien n’est arrivé, contacte le vendeur. » Dis à la boutique que le colis n'est pas arrivé, et demande-lui quelle société le livre et sous quel numéro. Voir [colis livré mais non reçu](guide:delivered-not-received).

**Introuvable.** Pour un numéro qu'il ne connaît pas, le site de suivi de GZYL affiche un seul résultat : ton numéro sous 参考号 (référence), et tous les autres champs vides. Si tu as ajouté un numéro YL…YQ sans choisir de transporteur, Peek affiche alors « En attente du premier scan de GZYL » et continue d'interroger GZYL ; au bout d'une semaine, il te propose de vérifier le numéro ou de choisir un autre transporteur. Compare le numéro avec le message de la boutique, caractère par caractère.

**Perdu ou renvoyé.** C'est la boutique qui a réservé l'envoi chez GZYL : c'est donc à elle, ou à la plateforme, que tu demandes un remboursement ou un nouvel envoi. Voir [colis perdu](guide:lost-parcel) et [colis retourné à l'expéditeur](guide:returned-to-sender).

## GZYL contact : téléphone et service client

Au 30 octobre 2026, Peek n'a trouvé aucun contact de GZYL : pas de site web à part son site de suivi, ni téléphone ni e-mail pour les destinataires. Pour ta commande, adresse-toi à la boutique, qui a réservé l'envoi, puis à la plateforme si elle ne répond pas.

## Suivre un colis GZYL avec Peek

[Peek](/) ne peut pas reconnaître un numéro GZYL à coup sûr d'après sa seule forme : YL, dix chiffres et YQ, ce n'est qu'un indice faible. Colle-le quand même : Peek suit plus de 3 500 transporteurs. Pendant l'ajout, Peek interroge le site de suivi de GZYL (« Vérification des services de suivi… ») et affiche « GZYL a ce colis. » si le site connaît le numéro. Sinon, touche « Changer » et choisis GZYL sous « Correspond à ce numéro » (tu le trouves aussi en tapant 广州云链), ou utilise « Changer de transporteur » sur un colis déjà ajouté. Une référence donnée par la boutique fonctionne de la même façon : Peek affiche alors aussi le numéro de suivi de la commande chez GZYL, avec la mention « GZYL le suit sous le numéro ». Peek retire toutefois les tirets : une référence qui en contient reste introuvable. Demande alors à la boutique le numéro YL…YQ.

Peek lit le site de suivi de GZYL et affiche le statut, chaque étape avec son lieu et, quand la fiche de GZYL l'indique, le pays de destination. Il lit les heures comme des heures de Pékin et les affiche dans le fuseau horaire de ton appareil. Il laisse de côté les noms, l'adresse, les codes postaux, le poids, les frais, la signature et la preuve de livraison que la fiche de GZYL peut contenir, et n'affiche aucune date de livraison prévue : GZYL n'en donne pas.

Peek écarte toute étape écrite plus de douze heures avant l'heure qu'elle porte, et toute étape datée d'après le moment où il vérifie, à l'heure de Pékin. Sur une commande qui a des étapes écrites d'avance, il écarte aussi `Delivered`, même écrit le jour même. Il se fie aux étapes qu'il garde, pas au statut général de la fiche. Une commande dont toutes les étapes sont écartées n'a pas encore d'historique de suivi dans Peek. Peek écarte aussi, chez les services de suivi, les copies des étapes qu'il tient pour des prévisions de GZYL, `Delivered` compris, même quand il garde d'autres étapes de la commande. Mais quand le site de suivi de GZYL ne répond pas, Peek interroge les services de suivi à sa place, et leurs copies peuvent contenir des étapes écrites d'avance par GZYL.

Sauf relais à La Poste suisse, Peek reste sur le site de suivi de GZYL : si la boutique te donne le numéro du transporteur qui livre, ajoute-le comme un colis à part. Peek vérifie toutes les 10 minutes, puis toutes les heures quand GZYL ne donne plus de nouvelles : après 12 heures sans nouvelle étape si le colis en est à « Étiquette créée » ou à « Pris en charge », après deux jours à toute autre étape. Ses notifications : « Chaque scan », « Étapes importantes » ou « Livraison seulement ». « Étapes importantes » te prévient pour les deux étapes de douane de GZYL et pour `Delivered`, pas pour ses vols ni son tri. Peek ne peut contacter ni GZYL ni la boutique à ta place.

## Questions sur le suivi GZYL

### C'est quoi, GZYL (广州云链) ?

C'est 广州云链货运代理有限公司 (Guangzhou Yunlian), un transitaire de Guangzhou, en Chine. Ses numéros de suivi : YL, dix chiffres et YQ.

### Un numéro YL…YQ est-il forcément un numéro GZYL ?

Pas à coup sûr. YL, dix chiffres et YQ, c'est la forme des numéros GZYL, et aucun autre transporteur du catalogue de Peek ne l'utilise ; Peek demande quand même au site de suivi de GZYL avant de rattacher le numéro à GZYL. Avec neuf ou onze chiffres, ce n'est pas la forme GZYL.

### Pourquoi mon colis GZYL est-il marqué livré alors que je ne l'ai pas reçu ?

C'est peut-être une prévision de GZYL : GZYL écrit d'avance une bonne partie du trajet d'un colis et le termine par un `Delivered`, et ces étapes sont des prévisions, pas des scans. Peek écarte ces étapes quand il lit le site de GZYL, `Delivered` compris, et, s'il affiche quand même « Livré », il précise juste en dessous que le suivi de GZYL avance selon un calendrier fixe. Dis à la boutique que le colis n'est pas arrivé, et demande-lui qui le livre.

### Qui livre les colis GZYL en France et en Belgique ?

Peek ne le sait pas : dans le pays de destination, les étapes de GZYL ne nomment aucune société. Demande à la boutique quel transporteur livre ta commande et sous quel numéro, puis ajoute ce numéro dans Peek comme un colis à part.

:::sources
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – GZYL : nom de la société et autres noms, site de suivi sur une adresse IP, forme YL…YQ et sa correspondance faible, Xinghe Freight sur le site de suivi de GZYL, recherche par numéro ou par référence avec ses tirets, étapes et leurs statuts, ligne en chinois de la plateforme, villes après les étapes, heure de Pékin, trajet écrit à l'avance, réponse à un numéro inconnu, relais vers La Poste suisse seulement, ce que Peek affiche, écarte et laisse de côté
- [Commission européenne : EU Customs Reform (en anglais)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 3 € de droits de douane par article sur les achats en ligne hors UE de 150 € maximum depuis le 1er juillet 2026, frais de traitement de 2 € depuis le 1er novembre 2026
- [Commission européenne : The temporary EUR 3 customs duty and the Union handling fee (PDF, en anglais)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 € par article quelle que soit la valeur, et ce qu'est un article (une ligne de la déclaration)
- [Commission européenne : Summertime (en anglais)](https://transport.ec.europa.eu/transport-themes/summertime_en) – heure d'été du dernier dimanche de mars au dernier dimanche d'octobre, heure d'Europe centrale
:::
