---
title: Suivi Chunghwa Post (Taiwan Post) : numéro TW et statuts
description: Suivre un colis Chunghwa Post depuis ou vers Taïwan : format du numéro en TW, statuts en chinois, relais postal en Europe et au Canada et douane.
slug: chunghwa-post-tracking
published: 2026-11-21
updated: 2026-11-21
---

Chunghwa Post (中華郵政, Chunghwa Post Co., Ltd.) est la poste nationale de Taïwan, entreprise publique détenue par le ministère des Transports et des Communications qui gère aussi l'épargne postale et l'assurance-vie. Vous pouvez suivre un envoi sur son [Track & Trace Service](https://postserv.post.gov.tw/pstmail/main_mail.html?targetTxn=EB500201), en anglais ou en chinois, ou coller le numéro dans le champ ci-dessus. Le code figure sur le récépissé remis à l'expéditeur au guichet : pour une commande partie de Taïwan, demandez-le au vendeur ou à la boutique. Le portail officiel demande un code visuel (Captcha) et ne conserve que les envois déposés au cours des six derniers mois.

## Numéro de suivi Chunghwa Post : format et exemples

Un envoi international parti de Taïwan porte un code postal de 13 caractères : deux lettres, neuf chiffres (dont le dernier est un chiffre de contrôle) et `TW`. Les consignes du portail Chunghwa Post précisent le rôle de la première lettre. Exemples avec des chiffres inventés :

| Exemple (inventé) | Service correspondant |
| --- | --- |
| `EE482910576TW` | `E` : EMS, le service express international (ou `EE`/`EZ` et `FT`/`FZ`, comme `FT482910576TW`, vers la Chine continentale). |
| `RR482910576TW` | `R` : envoi recommandé international. |
| `LX482910576TW` | `L` : ePacket international (petit paquet suivi jusqu'à 2 kg, remis en boîte sans signature). |
| `CP482910576TW` | `C` : colis postal international. |
| `48291057364918` | 14 chiffres (ou 20) : envoi intérieur à Taïwan, suivi sur l'onglet national de Chunghwa Post. |

Chunghwa Post indique que les petits paquets ordinaires dont le code commence par `U` (comme `UH482910576TW`) ne bénéficient pas du suivi en ligne. Si vous n'avez pas encore votre référence, consultez [comment trouver un numéro de suivi](guide:find-tracking-number).

## Statuts Chunghwa Post : que veulent dire les étapes en chinois

Voici les scans que Peek reconnaît dans le chinois traditionnel de Chunghwa Post, dans l'ordre habituel du trajet :

| Chunghwa Post affiche | Ce que cela signifie |
| --- | --- |
| `交寄郵件` | Déposé au guichet d'un bureau de poste (`« Pris en charge »`). |
| `運輸途中` | En acheminement entre les centres de Chunghwa Post (`« En transit »`). |
| `離開進口局` | Départ d'un bureau postal sur le parcours. |
| `到達出口互換局` | Arrivée au bureau d'échange export qui expédie le courrier à l'étranger. |
| `已出口－資料詳如右方` | Exporté : l'envoi a quitté Taïwan. |
| `郵袋接收` | Réception de la dépêche postale dans laquelle voyage l'envoi. |
| `到達寄達國互換局` | Arrivée au bureau d'échange du pays de destination. |
| `海關查驗` | Présentation au contrôle douanier (`« À la douane »`). |
| `離開寄達國互換局` | Sortie du bureau d'échange du pays de destination. |
| `到達寄達國投遞局` | Arrivée au bureau distributeur du pays de destination. |
| `安排投遞中` | Au bureau distributeur, en cours de préparation pour la tournée. |
| `郵件投遞中` | En cours de distribution par le facteur (`« En livraison »`). |
| `投遞不成功` | Échec de remise (`« Tentative de livraison »`). Le motif précis ou un éventuel retour figurent dans une remarque que Peek n'importe pas : vérifiez le portail Chunghwa Post. |
| `投遞成功` | Distribué (`« Livré »`). |

`安排投遞中` et `郵件投遞中` sont les étapes de distribution propres à Chunghwa Post pour le courrier entrant à Taïwan. Pour un colis parti de Taïwan vers l'Europe ou le Canada, les dernières étapes proviennent de l'opérateur postal de votre pays.

Si le portail répond `查無此號碼資料！！` (« aucune donnée pour ce numéro »), vérifiez les 13 caractères et assurez-vous que le dépôt remonte à moins de six mois.

## Colis Chunghwa Post vers la France, la Belgique, la Suisse, le Luxembourg et le Canada

Chunghwa Post achemine l'envoi hors de Taïwan, puis la poste du pays destinataire prend le relais avec le même code en `TW`. Le tableau des postes étrangères du portail Chunghwa Post renvoie vers ces sites :

- **France** : [Chronopost](carrier:chronopost) pour les envois EMS et les colis internationaux, aucun lien pour les lettres (en pratique, [La Poste / Colissimo](carrier:la-poste) traite aussi les paquets `L` et colis postaux sur son suivi).
- **Belgique** : [bpost](carrier:bpost) pour EMS, colis et lettres.
- **Suisse** : [Poste suisse](carrier:swiss-post) pour EMS, colis et lettres.
- **Luxembourg** : [POST Luxembourg](carrier:post-luxembourg) pour EMS, colis et lettres.
- **Canada** : [Postes Canada](carrier:canada-post) pour EMS et colis, aucun lien pour les lettres.

Dès que Chunghwa Post affiche `已出口－資料詳如右方`, interrogez l'opérateur de votre pays avec le même numéro `TW`. Les avis de passage, secondes présentations et délais de garde en bureau suivent les règles de cette poste : voyez sa page et [livraison manquée](guide:missed-delivery).

**Lettres recommandées.** Chunghwa Post prévient que treize pays — dont la **France** et le **Canada** (ainsi que les États-Unis, l'Australie, le Danemark, l'Afrique du Sud, les Pays-Bas, l'Italie, le Royaume-Uni, Bahreïn, le Yémen, la Roumanie et le Brésil) — ne fournissent plus de suivi en ligne pour les recommandés internationaux (`R…TW`). La Belgique, la Suisse et le Luxembourg continuent en revanche d'apparaître dans ses liens de suivi. Pour rechercher un recommandé vers la France ou le Canada, l'expéditeur doit déposer une réclamation écrite au guichet à Taïwan.

**Douane.** Taïwan étant hors de l'Union européenne, tout envoi de marchandises vers la France, la Belgique ou le Luxembourg passe en douane :

- **Union européenne** : la TVA à l'importation s'applique dès le premier euro (sauf perception à la commande via le guichet IOSS). Depuis le 1er juillet 2026, les achats en ligne hors UE jusqu'à 150 € supportent un droit de douane forfaitaire de 3 € par catégorie d'article ; depuis le 1er novembre 2026 s'ajoute des frais de traitement de l'Union de 2 € par article, quelle que soit la valeur de la commande, outre les éventuels frais de dédouanement du transporteur. Les départements d'outre-mer français (Guadeloupe, Guyane, Martinique, La Réunion, Mayotte) et Saint-Martin font partie du territoire douanier de l'UE mais restent hors du territoire TVA de l'UE, tandis que les collectivités d'outre-mer appliquent leurs propres règles fiscales et douanières.
- **Suisse** : selon l'OFDF (BAZG), la TVA à l'importation est de 8,1 % (2,6 % au taux réduit, notamment pour les livres, denrées alimentaires ou médicaments), mais les montants de droits ou de TVA inférieurs à 5 CHF ne sont pas perçus.

Plus de détails dans [colis bloqué en douane](guide:customs).

## Envoyer un colis vers Taïwan : distribution et douane

Pour un envoi postal expédié d'Europe ou du Canada vers Taïwan, Chunghwa Post assure la remise finale et accepte le numéro postal d'origine pendant six mois — à une exception signalée sur son portail : les ePackets (`L…`) importés depuis la France ne disposent pas du suivi en ligne sur le site de Chunghwa Post.

Chunghwa Post précise que chaque colis importé passe au contrôle douanier taïwanais, seul compétent pour fixer une éventuelle taxe. La douane de Taipei (Taipei Customs) publie trois seuils pour les colis postaux :

- **Jusqu'à 2 000 NT$** (valeur CIF : marchandise, assurance et transport compris) : exonération de droits et de taxes.
- **De 2 000 NT$ à 5 000 US$** : la douane charge généralement le bureau de poste d'encaisser les droits à la livraison au moyen d'un bordereau (*Duty Memo for Foreign Imports by Parcel Post*).
- **Au-delà de 5 000 US$** (valeur FOB) : le destinataire effectue la déclaration et règle les taxes directement auprès du bureau de douane.

La douane de Taipei rappelle aussi que plusieurs marchandises sont interdites ou soumises à autorisation à l'importation : dispositifs médicaux (dont les produits à l'acide hyaluronique ou appareils esthétiques), graines et semences, produits contenant de la viande, tabac chauffé, cigarettes électroniques et contrefaçons.

## Suivi Chunghwa Post bloqué, colis en retard ou perdu

**Plus de nouvelles après `已出口－資料詳如右方`.** Le pli a quitté Taïwan et attend sa prise en charge à l'arrivée ou le passage en douane. Consultez le suivi de votre poste nationale avec le même numéro `TW` et voyez [pourquoi le suivi ne bouge plus](guide:tracking-not-updating).

**Envoi retardé ou égaré.** Seul l'expéditeur peut déposer une demande écrite de recherche au bureau de poste de dépôt, muni du récépissé original : dans les **4 mois** à compter du lendemain du dépôt pour un envoi EMS, et dans les **6 mois** pour un colis international ou un recommandé. Passé ce délai, Chunghwa Post peut refuser l'enquête et n'accorde aucune indemnisation. Contactez donc le vendeur à temps et consultez [colis perdu](guide:lost-parcel).

**Marqué livré mais introuvable.** En Europe ou au Canada, le dernier kilomètre relève de votre poste locale : vérifiez son suivi et sa preuve de remise, puis voyez [colis indiqué livré mais non reçu](guide:delivered-not-received).

## Contacter Chunghwa Post

Les horaires ci-dessous sont exprimés à l'heure de Taïwan (`UTC+8`, sans heure d'été) :

- **Service client 24 h/24** : `0800-700-365` (gratuit depuis un poste fixe à Taïwan ; tapez `3` pour le service en anglais). Depuis un mobile ou l'étranger, composez le numéro payant `(04) 2354-2030`, soit `+886 4 2354 2030` hors de Taïwan (en retirant le `0` initial de l'indicatif régional).
- **Courrier entrant à Taïwan** : `(03) 3180-700`, poste `2431` (EMS) ou `2432` (colis), du lundi au vendredi hors jours fériés de 8 h 00 à 12 h 00 et de 13 h 30 à 16 h 30 ; poste `2430` (recommandés avion) de 8 h 00 à 12 h 00 et de 13 h 00 à 16 h 00.
- **Colis avion sortant de Taïwan** : `(03) 383-3776`, poste `271` ou `272`, du lundi au vendredi hors jours fériés de 8 h 00 à 16 h 30.
- **En ligne** : formulaire *Opinion Box* sur la page [Contact Us](https://www.post.gov.tw/post/internet/U_english2/index.jsp?ID=35090601), avec réponse sous 7 jours (indiquez le numéro d'envoi ainsi que les noms et adresses de l'expéditeur et du destinataire, puis validez le courriel de confirmation).

## Suivre un colis Chunghwa Post avec Peek

[Peek](/) identifie immédiatement comme Chunghwa Post un numéro de deux lettres, neuf chiffres et `TW` lorsque la première lettre est `C`, `E`, `L` ou `R` (ou que le préfixe est `FT` ou `FZ`) et que le chiffre de contrôle est exact ; avec un chiffre erroné, vérifiez le reçu. Les liens de suivi de la forme `CSController?MAILNO=` sont aussi reconnus. Un code `UH…TW` est classé par sa forme sous la poste internationale (vous pouvez choisir Chunghwa Post via `« Changer »` ou `« Changer de transporteur »`), tandis que les autres codes en `U…TW` et les numéros intérieurs de 14 ou 20 chiffres sont refusés par l'adaptateur Chunghwa Post (`« n’émet pas de numéros comme celui-ci »`).

Peek interroge le suivi international de Chunghwa Post et affiche chaque scan en chinois tel qu'émis, avec le bureau correspondant (converti en nom de ville lorsque Peek peut le localiser). Les scans réalisés par les bureaux taïwanais sont lus sur le fuseau `Asia/Taipei` et convertis dans le vôtre (`« Heures dans ton fuseau horaire : {{zone}}. »`), tandis que les scans transmis depuis l'étranger gardent l'heure locale de la poste concernée. Chunghwa Post ne fournit ni date de livraison estimée ni poids, et Peek écarte la colonne de remarques.

Pour un numéro en `TW`, Peek interroge aussi la poste nationale du pays depuis lequel vous consultez le suivi ([La Poste / Colissimo](carrier:la-poste) depuis la France, [bpost](carrier:bpost) depuis la Belgique, [Poste suisse](carrier:swiss-post) depuis la Suisse, [POST Luxembourg](carrier:post-luxembourg) depuis le Luxembourg, [Postes Canada](carrier:canada-post) depuis le Canada) : dès qu'elle publie des scans récents, Peek bascule vers elle tout en conservant l'historique taïwanais. En sens inverse, lorsqu'une poste étrangère indique Taïwan comme destination, Peek interroge Chunghwa Post avec le même code, sauf s'il commence par `U`.

Tant que la fiche du colis est ouverte à l'écran, ou le jour de livraison avec les notifications activées, Peek vérifie le suivi direct jusqu'à toutes les 10 minutes, et jusqu'à toutes les 2 minutes lorsque le colis passe `« En livraison »` (ce jour-là dès 6 h heure locale tant que le portail de Chunghwa Post répond directement) ; le reste du temps, ainsi qu'après deux jours sans nouveauté (12 heures tant que l'envoi reste à `« Étiquette créée »` ou `« Pris en charge »`), le contrôle est horaire. Par l'intermédiaire des services de suivi, la cadence atteint au mieux 15 minutes le jour où le colis est `« En livraison »` dans ces mêmes conditions, sinon toutes les heures, ou toutes les 3 heures après une journée sans mise à jour. Une fois le relais pris par la poste de destination, Peek suit la cadence propre à cet opérateur. Vous pouvez activer les alertes `« Chaque scan »`, `« Étapes importantes »` ou `« Livraison seulement »`. Peek ne peut pas déposer de réclamation auprès de Chunghwa Post à votre place.

## Questions fréquentes sur le suivi Chunghwa Post

### Chunghwa Post et Taiwan Post désignent-ils le même transporteur ?

Oui. Chunghwa Post (中華郵政) est l'opérateur postal national de Taïwan, et Peek le reconnaît sous ces deux noms. Ses numéros internationaux se terminent par `TW`.

### Qui livre un colis Chunghwa Post en France, en Belgique ou en Suisse ?

En France, Chunghwa Post renvoie vers [Chronopost](carrier:chronopost) pour les EMS et colis internationaux, et [La Poste / Colissimo](carrier:la-poste) assure la distribution postale courante ; en Belgique, c'est [bpost](carrier:bpost) et en Suisse la [Poste suisse](carrier:swiss-post), toujours avec le même numéro en `TW`.

### Pourquoi mon recommandé `RR…TW` vers la France ou le Canada ne bouge-t-il plus ?

Chunghwa Post indique que la France et le Canada font partie des pays qui n'offrent plus de suivi en ligne pour les lettres recommandées internationales. Seul l'expéditeur peut demander une recherche écrite en bureau de poste à Taïwan dans un délai de 6 mois.

### Que veut dire le statut `海關查驗` ?

L'envoi est présenté à la douane pour contrôle (`« À la douane »`). Pour un colis arrivant de Taïwan, consultez [colis bloqué en douane](guide:customs) pour connaître la TVA, le droit de 3 € et les frais de traitement de 2 € applicables dans l'UE (ou la franchise de 5 CHF de TVA/droits en Suisse).

### Combien de temps un numéro Chunghwa Post reste-t-il consultable ?

Le portail en ligne affiche uniquement les envois des six derniers mois. La réclamation écrite de l'expéditeur doit être déposée sous 4 mois pour un EMS et sous 6 mois pour un colis ou un recommandé, à compter du lendemain de l'expédition.

:::sources
- [Chunghwa Post : Company Profile](https://www.post.gov.tw/post/internet/U_english2/index.jsp?ID=1637282383451) – entreprise publique nationale, courrier, épargne et assurance-vie
- [Chunghwa Post : Tracking Service](https://www.post.gov.tw/post/internet/U_english2/index.jsp?ID=351402) – accès au suivi EMS, lettres et colis
- [Chunghwa Post : Track & Trace Service](https://postserv.post.gov.tw/pstmail/main_mail.html?targetTxn=EB500201) – Captcha, conservation 6 mois, préfixes E/R/L/C/U, recommandés non suivis, ePacket depuis la France, délais de réclamation, liens des postes étrangères, numéros intérieurs 14/20 chiffres
- [Chunghwa Post : 國際e小包 (ePacket)](https://www.post.gov.tw/post/internet/Postal/index.jsp?ID=1499060698896) – plafond de 2 kg, remise sans signature ni réclamation écrite
- [Chunghwa Post : 國際郵件查詢電話](https://www.post.gov.tw/post/internet/Customer_service/index.jsp?ID=160101) – lignes téléphoniques, postes et horaires du courrier international
- [Chunghwa Post : English home page](https://www.post.gov.tw/post/internet/U_english2/) – numéro 0800-700-365, ligne mobile payante et format international +886
- [Chunghwa Post : 客服專線語音操作說明](https://www.post.gov.tw/post/internet/Customer_service/index.jsp?ID=160103) – serveur vocal 24 h/24, touche 3 pour l'anglais
- [Chunghwa Post : Contact Us](https://www.post.gov.tw/post/internet/U_english2/index.jsp?ID=35090601) – formulaire Opinion Box, délai de 7 jours et informations requises
- [Chunghwa Post : 國際包裹 FAQ](https://www.post.gov.tw/post/internet/Customer_service/index.jsp?ID=16020307&defaultAllOpen=1&sn=24FA12A9-11A6-4365-A8F5-C154C379AF02) – contrôle douanier systématique des colis importés à Taïwan
- [Chunghwa Post : 郵件查詢的期限 FAQ](https://www.post.gov.tw/post/internet/Customer_service/index.jsp?ID=16020315&defaultAllOpen=1&sn=44431DCF-5197-43D5-95B6-5EFE13F5D67B) – forclusion et absence d'indemnisation hors délai
- [Taipei Customs : International Parcel](https://web.customs.gov.tw/etaipei/singlehtml/1342) – seuils de 2 000 NT$ et 5 000 US$, bordereau de perception postale
- [Taipei Customs : Cross-border online purchases](https://web.customs.gov.tw/etaipei/singlehtml/1373?cntId=1a74ff69fdd0483085a6a8c9eda54b07) – marchandises soumises à restriction ou interdites
- [Your Europe : Expédition et livraison](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_fr.htm) – TVA et droit forfaitaire de 3 € par article jusqu'à 150 €
- [Commission européenne : Customs Guidance on EUR 3 customs duty](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – droit de 3 € applicable depuis le 1er juillet 2026
- [Commission européenne : EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – frais de traitement de 2 € par article depuis le 1er novembre 2026
- [Commission européenne : Territorial scope of EU VAT and customs](https://taxation-customs.ec.europa.eu/taxation/vat/vat-directive/how-does-vat-work/territorial-scope_en) – statut douanier et TVA des départements et territoires d'outre-mer
- [OFDF (BAZG) : Réception de lettres et de paquets](https://www.bazg.admin.ch/fr/reception-de-lettres-et-paquets) – TVA suisse de 8,1 % / 2,6 % et seuil de perception de 5 CHF
- [Universal Parcel Scraper : Chunghwa Post](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/chunghwa-post) – détection des numéros TW, statuts chinois et passage de relais postal
:::
