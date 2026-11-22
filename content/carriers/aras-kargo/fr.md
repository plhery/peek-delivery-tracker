---
title: Suivi Aras Kargo : numéros et statuts en français
description: Suis un colis Aras Kargo : format du numéro à 13 chiffres, statuts turcs comme TESLİMAT ŞUBESİNDE expliqués en français et suivi dans Peek.
slug: suivi-aras-kargo
published: 2026-11-22
updated: 2026-11-22
---

Aras Kargo (parfois écrit Aras Cargo) est un transporteur de colis en Turquie. Son outil de suivi, tel que Peek le lit, affiche chaque étape en turc. Le vendeur ou l’expéditeur te fournit le numéro de suivi : colle-le dans le champ ci-dessus. Le suivi officiel d’Aras Kargo se trouve sur kargotakip.araskargo.com.tr. Les tableaux ci-dessous traduisent ses statuts turcs en français.

## Numéro de suivi Aras Kargo : à quoi il ressemble

Le code de Peek reconnaît les numéros de suivi Aras Kargo comme des suites de 13 chiffres. Ceux qu’il associe automatiquement à Aras commencent par 60 ou 65. Exemples inventés :

| Numéro (inventé) | Ce que c’est |
| --- | --- |
| `6048291736542` | 13 chiffres commençant par 60 : le format que Peek relie à Aras Kargo. D’autres transporteurs, dont [Delhivery](carrier:delhivery), Econt, Ozon Logistics, Paquetexpress, Correos Chile, Yunda et Korea Post, utilisent aussi 13 chiffres. |
| `6548291736542` | 13 chiffres commençant par 65 : même chose. |
| `1482917365482` | 13 chiffres commençant par d’autres chiffres : Peek ne les relie pas d’office à Aras, mais l’outil d’Aras accepte 13 chiffres. Si la boutique indique Aras Kargo, sélectionne Aras Kargo toi-même (voir plus bas). |
| `604829173654` | Seulement 12 chiffres. Si tu choisis Aras Kargo pour ce numéro, Peek indique qu’Aras Kargo « n’émet pas de numéros comme celui-ci ». Vérifie s’il manque un chiffre. |

Les espaces ne posent aucun problème lorsque tu colles un numéro dans Peek. Un lien vers le suivi d’Aras contenant le numéro (kargotakip.araskargo.com.tr avec `code=` suivi du numéro) fonctionne également.

**Où le trouver.** Dans l’e-mail d’expédition de la boutique ou sur la page de ta commande. Si tu ne le trouves pas, consulte [comment trouver son numéro de suivi](guide:find-tracking-number) et [formats des numéros de suivi](guide:tracking-number-formats).

## Statuts de suivi Aras Kargo en français

Le suivi d’Aras indique le statut actuel du colis (`Son Durum`), une date de livraison prévue et la liste des mouvements de véhicules (`Araç Hareketleri`). Chaque mouvement mentionne une date et une heure (`İşlem Tarihi`), une province (`İl`), une unité (`Birim`, agence ou centre de transfert) et un libellé (`İşlem Açıklaması`). Le cas échéant, la page répertorie aussi les jours où la livraison n’a pas pu avoir lieu et toute redirection vers une autre agence.

**Statut actuel.** L’un des libellés suivants en haut de la fiche :

| Aras indique | Ce que cela signifie pour toi | Peek affiche |
| --- | --- | --- |
| `ÇIKIŞ ŞUBESİNDE` | À l’agence d’origine qui a pris en charge le colis. | « Pris en charge » |
| `YOLDA` | En route entre les centres de transfert d’Aras. | « En transit » |
| `TESLİMAT ŞUBESİNDE` | À l’agence de livraison, celle qui t’apporte le colis. Ce statut reste inchangé même après une tentative échouée. | « En transit » |
| `TESLİM EDİLDİ` | Livré. Aras indique la date et l’heure (si l’heure manque, le statut reste « Livré » sans ligne horodatée de livraison). | « Livré » |

**Mouvements de véhicules.** Chaque scan lors du chargement, du déchargement ou de la mise en sac :

| Aras indique | Ce que cela signifie pour toi | Peek affiche |
| --- | --- | --- |
| `ŞUBEDE ARAÇ YÜKLEME` | Chargé dans un véhicule en agence. | « En transit » |
| `ŞUBEDE ARAÇ İNDİRME` | Déchargé d’un véhicule en agence. | « En transit » |
| `AKTARMADA HAT ARACI YÜKLEME` | Chargé dans un camion longue distance au centre de transfert. | « En transit » |
| `AKTARMADA HAT ARACI İNDİRME` | Déchargé d’un camion longue distance au centre de transfert. | « En transit » |
| `AKTARMADA SUBE ARACI YUKLEME` | Chargé dans un véhicule d’agence au centre de transfert. | « En transit » |
| `AKTARMADA SUBE ARACI INDIRME` | Déchargé d’un véhicule d’agence au centre de transfert. | « En transit » |
| `AKTARMADA TORBA DOLDURMA` | Placé dans un sac avec d’autres colis au centre de transfert. | « En transit » |

Le suivi écrit les deux lignes contenant `SUBE` sans caractères turcs, exactement comme ci-dessus.

**Livraison non effectuée** (`Teslim Edilememe Nedeni`, motif). Une ligne par jour concerné :

| Aras indique | Ce que cela signifie pour toi | Peek affiche |
| --- | --- | --- |
| `TESLİMAT GÜN İÇERİSİNDE YAPILAMADI` | Le colis n’a pas pu être livré ce jour-là. | « Tentative de livraison » |
| `TATİL DOLAYISIYLA TESLİMAT YAPILAMADI` | Pas de livraison en raison d’un jour férié. | « Tentative de livraison » |
| `UĞRAMA NOTU BIRAKILDI (1-2)` | Le livreur est passé et a laissé un avis de passage. | « Tentative de livraison » |
| `ALICI ADRESİ DAĞITIM ALANI DIŞINDA` | Ton adresse se trouve hors de la zone desservie par l’agence. | « Tentative de livraison » |

**Redirection.** `YÖNLENDİRİLDİ` indique que le colis a été redirigé vers une autre agence. Aras précise le motif et Peek l’ajoute entre parenthèses après le mot. Peek affiche « En transit ».

Ces deux types de lignes portent uniquement une date, sans heure. Le suivi d’Aras peut également afficher un commentaire libre du livreur à côté d’un échec de livraison : Peek ne le reprend pas. Pour en savoir plus sur les étapes, consulte [comprendre les statuts de suivi](guide:tracking-statuses).

## Livraison Aras Kargo en Turquie

Le colis part de l’agence Aras qui l’a pris en charge (`ÇIKIŞ ŞUBESİNDE`), transite par des centres de transfert (les lignes avec `AKTARMADA`) jusqu’à l’agence de livraison (`TESLİMAT ŞUBESİNDE`), chargée de l’acheminer à ton adresse.

**Date de livraison prévue.** Le suivi d’Aras en affiche une. Peek l’affiche comme date estimée tant que le colis est en mouvement et que ce jour n’est pas antérieur au dernier scan. Il la retire après une tentative de livraison échouée et une fois le jour passé.

**Hors zone de livraison.** `ALICI ADRESİ DAĞITIM ALANI DIŞINDA` signifie que l’agence ne dessert pas ton adresse : demande au vendeur la suite prévue.

**Ce que cette page ne peut pas préciser.** Peek n’a pas pu lire les pages d’aide du site d’Aras Kargo ; cette page n’indique donc ni délais de livraison, ni nombre de passages, ni durée de garde, ni règles de retrait. Le suivi d’Aras tel que Peek le lit ne mentionne aucun point relais, poids ou nom de service. En cas d’absence, consulte [livraison manquée](guide:missed-delivery) et [colis non récupéré](guide:uncollected-parcel).

## Suivi Aras Kargo bloqué, colis perdu ou retourné

**Aucun résultat.** Quand Aras ne connaît pas encore le numéro, son suivi répond `Girdiğiniz bilgiler için bir sonuç bulunamamıştır.` (aucun résultat trouvé pour les informations saisies). La boutique n’a peut-être pas encore remis le colis. Peek continue de vérifier : un colis classé sous Aras Kargo affiche « En attente de Aras Kargo ». Vérifie les 13 chiffres auprès du vendeur ; si rien n’apparaît après quelques jours, demande-lui s’il a bien confié le colis à Aras.

**Bloqué sur `TESLİMAT ŞUBESİNDE`.** L’agence de livraison détient le colis et le statut reste identique même après un échec de livraison. Si un motif figure pour une journée (tableau ci-dessus), le colis n’a pas été livré ce jour-là et le motif l’explique. Sinon, il attend sa tournée. Comme le suivi d’Aras ne comporte aucune étape de mise en livraison, Peek affiche « En transit » jusqu’à la livraison ou jusqu’à une tentative échouée.

**Aucune nouvelle depuis plusieurs jours.** Après quatre jours sans nouveau scan sur un colis en cours d’acheminement, Peek affiche la date du dernier scan dans ta liste (« Aucune mise à jour depuis … ») et, sur la fiche du colis, « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger Aras Kargo. », accompagné alors seulement du lien « Demande de l’aide à Aras Kargo » vers sa page de contact. Les colis restent souvent silencieux le week-end. Voir [pourquoi le suivi ne se met plus à jour](guide:tracking-not-updating).

**Indiqué livré mais non reçu.** Vérifie auprès des voisins et des personnes présentes à ton adresse. Peek affiche le nom masqué de la personne qui a pris le colis (`Teslim Alan`) tel qu’il apparaît dans le résumé d’Aras ; tu peux aussi consulter le suivi d’Aras directement. Contacte ensuite le vendeur et Aras. Voir [marqué livré mais pas reçu](guide:delivered-not-received).

**Perdu ou retourné.** Le suivi d’Aras ne dispose pas encore de libellé de retour reconnu dans Peek. Comme c’est la boutique qui a commandé l’expédition auprès d’Aras, demande-lui d’abord un remboursement ou un renvoi. Voir [colis perdu](guide:lost-parcel) et [colis retourné à l’expéditeur](guide:returned-to-sender).

## Service client et contact Aras Kargo

Peek n’a pas pu lire les pages du site d’Aras Kargo ; cette page ne liste donc aucun numéro de téléphone ni adresse e-mail, car Peek n’indique que des coordonnées lues directement sur les pages officielles du transporteur.

Pour une commande en ligne, adresse-toi d’abord à la boutique qui a réservé le transport. Dans Peek, le lieu de chaque mouvement indique l’agence ou le centre de transfert, et le lieu de livraison indique l’agence qui a livré : mentionne ce nom dans ta demande.

## Suivre un colis Aras Kargo avec Peek

[Peek](/) ne peut pas identifier un colis Aras Kargo uniquement d’après sa forme : d’autres transporteurs utilisent aussi 13 chiffres. Colle tout de même le numéro : Peek suit plus de 4 500+ transporteurs.

- **13 chiffres commençant par 60 ou 65.** Peek interroge Aras Kargo ainsi que les autres transporteurs compatibles avec ce format de 13 chiffres (comme [Delhivery](carrier:delhivery), Econt, Ozon Logistics ou Paquetexpress) ; si la recherche se fait depuis la Turquie, Aras Kargo est interrogé en premier. Pendant la vérification, tu vois « Vérification des services de suivi… ». Si seul Aras Kargo reconnaît le colis, Peek affiche « Aras Kargo a ce colis. » et le classe sous Aras ; si plusieurs transporteurs le connaissent, Peek te demande lequel choisir.
- **Autres numéros à 13 chiffres, ou colis pas encore trouvé.** Change de transporteur et choisis Aras Kargo (tape Aras) pour que Peek interroge directement le suivi d’Aras. Sur un colis déjà ajouté, tu peux aussi changer le transporteur depuis sa fiche.
- **Lien vers le suivi d’Aras.** Peek extrait le numéro du lien et identifie aussitôt Aras Kargo (« Transporteur détecté »).

Ajouté sans transporteur, un colis affiche « Aucun historique de suivi pour le moment » pendant que Peek continue d’interroger les transporteurs et les services de suivi. Si un service de suivi désigne Aras Kargo comme seul transporteur du colis avec des étapes datées, Peek le classe sous Aras.

**Ce que Peek affiche.** Le statut actuel, chaque mouvement avec son heure et son lieu (agence ou centre de transfert et province), les jours de livraison non effectuée, les redirections, la date de livraison prévue, l’heure et l’agence de livraison, ainsi que le nom masqué du destinataire et de la personne ayant reçu le colis (`Teslim Alan`) tels qu’affichés dans le résumé. Il ignore le nom complet masqué dans la page, le nom de l’expéditeur, le code de livraison, la vérification d’identité, les détails de paiement et de facture, le volume et le nombre de colis, la barre de progression, les commentaires libres et l’onglet SMS. Peek lit les horaires d’Aras sur l’heure de Turquie et les convertit dans ton fuseau horaire (l’historique se termine par « Heures dans ton fuseau horaire : » suivi de son nom). Le bouton « Ouvrir le site Aras Kargo » ouvre le numéro directement sur le suivi d’Aras.

Lorsque le suivi d’Aras est injoignable, Peek interroge des services de suivi et affiche l’historique qu’ils fournissent, dans leurs propres termes.

Tant que la fiche du colis est ouverte à l’écran — ou le jour de la livraison si les notifications sont activées — Peek interroge directement Aras Kargo jusqu’à toutes les 10 minutes (sans cadence de 2 minutes, car Aras n’envoie pas d’étape de mise en livraison). Sinon, ainsi qu’après deux jours sans nouvelle (12 heures tant que le colis reste sur « Étiquette créée » ou « Pris en charge »), il vérifie toutes les heures. Via les seuls services de suivi, c’est toutes les heures, toutes les 3 heures après une journée sans scan, et jusqu’à toutes les 15 minutes le jour de la livraison si le colis est ouvert à l’écran ou si les notifications sont activées. Tu peux choisir « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Peek ne peut pas contacter Aras Kargo ni le vendeur à ta place.

## Questions sur le suivi Aras Kargo

### Que signifie TESLİMAT ŞUBESİNDE sur le suivi Aras Kargo ?

Cela signifie « à l’agence de livraison » : l’agence Aras chargée de t’apporter le colis l’a en main. Le statut reste identique après une tentative échouée : vérifie si une ligne de motif apparaît, que Peek affiche comme « Tentative de livraison ».

### Que signifie YOLDA sur Aras Kargo ?

Cela signifie « en route » : le colis circule entre les centres de transfert d’Aras en direction de l’agence de livraison. Peek affiche « En transit ».

### À quoi ressemble un numéro de suivi Aras Kargo ?

À 13 chiffres dans le code de Peek. Peek relie à Aras Kargo ceux qui commencent par 60 ou 65, comme `6048291736542`. Pour d’autres numéros à 13 chiffres, sélectionne Aras Kargo manuellement.

### Pourquoi Aras Kargo ne trouve-t-il pas mon numéro de suivi ?

`Girdiğiniz bilgiler için bir sonuç bulunamamıştır.` signifie qu’Aras n’a encore aucun résultat pour ce numéro. Le vendeur n’a peut-être pas encore remis le colis. Vérifie les 13 chiffres, attends un jour, puis demande au vendeur.

### Comment contacter Aras Kargo ?

Demande d’abord au vendeur qui a réservé la livraison. Comme Peek n’a pas pu lire le site d’Aras Kargo, cette page n’indique ni numéro de téléphone ni adresse e-mail.

:::sources
- [Universal Parcel Scraper : Aras Kargo](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/aras-kargo) – noms, format à 13 chiffres et préfixes 60 et 65, suivi et lien, sections et libellés turcs avec leurs étapes, message introuvable, date de livraison prévue, heure de Turquie, champs lus et ignorés par Peek
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – autres transporteurs partageant le format, reconnaissance automatique et recours aux services de suivi
:::
