---
title: Suivi Bulgarian Posts : codes PS et BG, douane et aide
description: Suis un colis Bulgarian Posts : codes PS et S10 BG, statuts anglais et bulgares, garde 20 jours, frais douaniers UE, réclamations et contacts.
slug: suivi-bulgarian-posts
published: 2026-11-24
updated: 2026-11-24
---

Bulgarian Posts (*Български пощи ЕАД*) est l’opérateur postal universel national de la Bulgarie, qui gère le courrier intérieur, les e-paquets et les colis ainsi que les envois internationaux de l’Union postale universelle (UPU) et les livraisons express EMS / Bulpost. Tu peux suivre un envoi sur [la page de suivi de Bulgarian Posts](https://www.bgpost.bg/en/postal-services/track-package), ou coller le numéro de suivi dans le champ ci-dessus.

## Numéros de suivi Bulgarian Posts : formats PS et S10

Cherche le code-barres de 13 caractères sur ton reçu de dépôt, dans l’e-mail d’expédition de la boutique en ligne ou sur l’avis de passage papier (*служебно известие*). Sur [bgpost.bg/en/postal-services/track-package](https://www.bgpost.bg/en/postal-services/track-package), Bulgarian Posts indique deux formats suivis :

| Format (inventé) | Ce que c’est |
| --- | --- |
| `PS48291736405` | Envoi national ou e-paquet : `PS` suivi de 11 lettres et chiffres (13 caractères au total). |
| `RR482917367BG` | Lettre recommandée ou petit paquet international (`R`, `C`, `E`, `L` ou `V` + seconde lettre + 9 chiffres + `BG`). |
| `CP482917367BG` | Colis postal international expédié de Bulgarie (`CP` + 9 chiffres + `BG`). |

Les colis entrants envoyés de l’étranger gardent le code UPU S10 de la poste d’origine (se terminant par le code pays à deux lettres, comme `DE`, `CN` ou `US`). Bulgarian Posts précise que les envois internationaux sortants se suivent sur son site jusqu’à la sortie de Bulgarie puis sur le site de la poste de destination, tandis que les envois entrants apparaissent chez Bulgarian Posts dès leur arrivée en Bulgarie. Pour d’autres transporteurs en Bulgarie, consulte [Sameday](carrier:sameday), [BOX NOW](carrier:box-now) ou [les formats de numéros de suivi](guide:tracking-number-formats).

## Signification des statuts de suivi Bulgarian Posts

Le flux de suivi de Bulgarian Posts affiche des libellés UPU IPS en anglais pour les étapes internationales — marqués `(Otb)` à l’export ou `(Inb)` à l’import — aux côtés de libellés en bulgare pour les scans intérieurs (même sur la version anglaise du site).

| Ce qu’affiche Bulgarian Posts | Ce que ça signifie pour toi | Ce qu’affiche Peek |
| --- | --- | --- |
| `Receive item from customer (Otb)`, `Получаване пратка от клиент (А10)`, premier `Приемане` | Pris en charge par un bureau de poste auprès de l’expéditeur. | « Pris en charge » |
| `Insert item into bag (Otb) and Send item abroad`, `Send item abroad (EDI-received)`, `Receive item at office of exchange (Otb)` / `(Inb)` | Mis en sac pour l’export, expédié à l’étranger ou réceptionné au bureau d’échange international de Bulgarie. | « En transit » |
| `Receive item at sorting centre (Inb)`, `Insert item into domestic bag`, `Send item to domestic location (Inb)`, `Receive item at location (Inb)`, `Приемане` ultérieur | Acheminé via les centres de tri bulgares vers ton bureau de poste local. | « En transit » |
| `Send item to customs (Inb)`, `Record item customs information (Inb)` | Présenté à la douane bulgare ou en attente de documents/évaluation douanière (souvent accompagné d’une mention d’attente de présentation à un agent des douanes). | « À la douane » |
| `Return item from customs (Inb)` | Libéré par la douane et remis dans le réseau Bulgarian Posts (il ne s’agit pas d’un retour à l’expéditeur). | « En transit » |
| `Handover item to delivery agent` | En tournée avec le facteur pour livraison à domicile aujourd’hui. | « En livraison » |
| `Unsuccessful item delivery attempt (Inb)`, `Първо посещение` | Premier passage infructueux ; un avis écrit a été déposé et l’envoi part au bureau de poste. | « Tentative de livraison » |
| `Receive item at collection point for pick-up (Inb)`, `За получаване в ПС`, `Второ посещение` | Prêt à être retiré au bureau de poste local (`Второ посещение` correspond au second avis écrit envoyé après 10 jours calendaires). | « Prêt à être retiré » |
| `Deliver item (Inb)`, `Доставена`, `Доставена пратка` | Remis au destinataire contre signature. | « Livré » |

Note que `Създаване` (création par Bulgarian Posts de son dossier intérieur juste après le scan d’entrée au bureau d’échange) est une opération administrative sans mouvement physique : Peek conserve donc l’étape du scan précédent. De même, `Второ посещение` n’est pas une seconde tentative à domicile — il enregistre le second avis de mise en instance pendant que ton colis continue d’attendre au bureau de poste.

## Retrait au bureau de poste, délais de garde et frais de magasinage

Selon les [Conditions générales de Bulgarian Posts (ОУ 2023)](https://www.bgpost.bg/cms/static/%D0%9E%D0%A3_2023.pdf) (points 43, 45 et 46, accessibles depuis [General Terms](https://www.bgpost.bg/en/about_us/conditions)) :

- **Envois recommandés et colis :** remis en main propre au destinataire ou à une personne mandatée contre signature et pièce d’identité avec photo. Les colis postaux (*пощенски колети*) se retirent normalement au bureau de poste destinataire après remise d’un avis écrit (*писмено служебно известие*), sauf si la livraison à domicile a été payée séparément.
- **Délai de garde standard — 20 jours calendaires :** les envois recommandés après passage manqué comme les colis conservés au guichet restent en attente **20 jours calendaires** à compter de l’arrivée au bureau distributeur avant d’être retournés à l’expéditeur.
- **Second avis après 10 jours :** sans retrait au bout de **10 jours calendaires**, Bulgarian Posts envoie un second avis écrit (`Второ посещение`).
- **Frais de magasinage après 9 jours :** pour un colis postal non retiré dans les **9 jours calendaires** suivant l’avis, Bulgarian Posts facture des frais de magasinage (*магазинаж*) selon le tarif en vigueur.
- **Envois dédouanés — 45 jours calendaires :** selon [les règles douanières de Bulgarian Posts](https://www.bgpost.bg/en/customs), **tout envoi ayant subi un dédouanement se retire uniquement au bureau de poste désigné** (jamais livré à domicile) et reste conservé **45 jours calendaires** avant renvoi à l’expéditeur.

## Colis hors UE, frais douaniers et droit de 3 € / taxe UE de 2 €

Les envois circulant à l’intérieur de l’UE ne passent pas en douane. Pour les achats en ligne et cadeaux arrivant en Bulgarie depuis un pays hors UE, [bgpost.bg/en/customs](https://www.bgpost.bg/en/customs) et la [Commission européenne](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) précisent les frais en vigueur :

- **Droit fixe et taxe de traitement UE :** un droit de douane forfaitaire de **3 € par article** s’applique aux achats B2C en ligne d’une valeur jusqu’à 150 € (en vigueur depuis le 1er juillet 2026), et une **taxe de traitement de l’Union (UHF) de 2 € par article individuel** s’applique aux achats en ligne hors UE quelle que soit la valeur (en vigueur depuis le 1er novembre 2026 ; incluse dans la valeur en douane pour le calcul de la TVA ; exonérée pour les cadeaux privés C2C). Le droit de 3 € (jusqu’à 150 €) comme la taxe UHF de 2 € sont **non remboursables** même en cas de retour d’article défectueux.
- **Frais de représentation douanière de Bulgarian Posts (TVA incluse, en vigueur depuis le 2 novembre 2026) :**
  - B2C jusqu’à 150 € avec IOSS (déclaration H7) : **2,70 €** (plus droits de douane si la boutique ne les a pas perçus au paiement).
  - B2C jusqu’à 150 € sans IOSS (régime spécial, H7) : **3,80 €** (plus TVA et droits de douane).
  - B2C de 150 € à 1 000 € (H6) : **12,00 €** (plus TVA et droits).
  - B2C au-delà de 1 000 € (H1) : **20,00 €** pour le premier type d’article plus **5,00 €** par type supplémentaire.
  - Cadeaux privés C2C : **2,70 €** jusqu’à 45 € (sans TVA ni droits), **3,80 €** de 45 € à 1 000 €, ou **20,00 €** (+ 5,00 € par type supplémentaire, numéro EORI requis) au-delà de 1 000 €.
- **Documents requis :** déclaration de consentement, déclaration de valeur et, pour un achat, facture/commande correspondant au numéro de suivi ainsi qu’une preuve de paiement (relevé bancaire, carte ou PayPal).

Consulte [colis retenu en douane](guide:customs) pour des conseils généraux sur la douane dans l’UE.

## Colis manquants, retardés ou endommagés et droits du consommateur

Si tu as acheté auprès d’une boutique en ligne, le commerçant est responsable de la livraison et des remboursements. Selon la Commission bulgare de protection des consommateurs ([règles d’achat en ligne KZP](https://kzp.bg/bg/potrebiteli/good-to-know/online-shopping)), une commande en ligne doit être livrée sous 30 jours sauf accord contraire, et le consommateur dispose d’un droit de rétractation de 14 jours à compter de la réception.

**Déposer une réclamation auprès de Bulgarian Posts.** Selon [bgpost.bg/en/complaints](https://www.bgpost.bg/en/complaints) et les points 56 à 59 des Conditions générales, c’est l’**expéditeur** qui dépose une réclamation avant livraison — le destinataire ne peut le faire avant réception qu’avec l’accord écrit de l’expéditeur. La réclamation doit être soumise dans les **6 mois** suivant le dépôt (dans tout bureau de poste ou en ligne sur le portail RQMS) avec copie du reçu de dépôt. Bulgarian Posts répond sous **1 mois** pour le courrier national et **3 mois** pour l’international, et verse l’indemnisation approuvée sous **1 mois** après notification.

**Recours auprès du régulateur.** Les litiges postaux non résolus peuvent être portés devant la Commission de régulation des communications de Bulgarie ([réclamations CRC / КРС](https://crc.bg/bg/rubriki/271/jalbi)), qui répond sous 30 jours (jusqu’à 2 mois si des justificatifs supplémentaires sont demandés). En cas de blocage ou de problème, consulte [pourquoi le suivi ne se met plus à jour](guide:tracking-not-updating), [colis livré mais non reçu](guide:delivered-not-received), [colis endommagé](guide:damaged-parcel), [colis perdu](guide:lost-parcel) et [retourné à l’expéditeur](guide:returned-to-sender).

## Service client et contacts Bulgarian Posts

Coordonnées publiées sur [bgpost.bg/en/contacts](https://www.bgpost.bg/en/contacts), [bgpost.bg/en/complaints](https://www.bgpost.bg/en/complaints) et [bgpost.bg/en/customs](https://www.bgpost.bg/en/customs) :

- **Service client général :** `*7678` ou `+359 2 962 50 50` (jours ouvrables 08h00–17h30), e-mail `info@bgpost.bg`.
- **Messagerie EMS / Bulpost :** `*6666` ou `0700 19 666` (jours ouvrables 07h00–19h00).
- **Service douanes (1900 Sofia BESC) :** `customerservice@bgpost.bg` (lun–ven 08h00–16h00) ; envois EMS commençant par `E` : `emscustomerservice@bgpost.bg` (lun–ven 08h30–16h00) ; bureau douanier de Varna `psm.varna@bgpost.bg` / `052 604 143`.
- **Lignes réclamations (jours ouvrables 09h00–17h30) :** national `+359 2 949 3326`, international entrant `+359 2 949 3121`, international sortant `+359 2 949 3343`, aide portail RQMS `02 949 31 90`.
- **Siège social :** 1, Akademik Stefan Mladenov Str., Bl. 31, 1700 Sofia, Bulgarie.

> Méfie-toi des SMS ou e-mails non sollicités te demandant de payer des frais par carte en ligne pour « programmer une livraison » Bulgarian Posts : les envois dédouanés se règlent et se retirent au bureau de poste désigné. Vérifie toujours ton code directement sur [bgpost.bg](https://www.bgpost.bg/en/postal-services/track-package). Consulte [les SMS d’arnaque à la livraison](guide:delivery-scam-texts).

## Suivre un colis Bulgarian Posts avec Peek

[Peek](/) reconnaît directement les codes `PS` de 13 caractères (`PS48291736405`, chiffres inventés) comme Bulgarian Posts avec un haut niveau de confiance, ainsi que les liens collés `bgpost.bg/postal-services/track-package?itemid=` (y compris `/en/`) et les anciens liens `IPSWeb_item_events.asp?itemid=`. Pour les codes internationaux UPU S10 terminés par `BG` (comme `RR482917367BG` ou `CP482917367BG`), Peek suit automatiquement Bulgarian Posts comme poste d’origine privilégiée ; tu peux aussi sélectionner Bulgarian Posts pour un code S10 entrant venu de l’étranger en appuyant sur « Changer » à l’ajout ou « Changer de transporteur » sur la fiche du colis (rechercher « Български пощи », « Bulgaria Post » ou « BG Post » le trouve aussi). Note que si un code appartient à Bulpost (`EMS/Bulpost`), Bulgarian Posts renvoie vers la page distincte de Bulpost.

Peek interroge directement l’API publique de Bulgarian Posts et affiche l’étape (« Pris en charge », « En transit », « À la douane », « En livraison », « Tentative de livraison », « Prêt à être retiré », « Livré »), les lignes IPS en anglais (plus les mentions bulgares intérieures et douanières), les localités/pays des bureaux (sans codes postaux) et les heures de remise pour les bureaux bulgares. Les scans effectués en Bulgarie sont datés à l’heure de Sofia et ceux d’une poste étrangère à l’heure locale de ce lieu (heure allemande pour Francfort, par exemple) ; Peek les affiche à l’heure de ton appareil. Le suivi Bulgarian Posts ne fournit pas de date estimée, de poids, de nom de service, de nom d’expéditeur/destinataire ni de photo de preuve de livraison.

Tant que le colis est ouvert dans Peek, ou le jour de la livraison avec les notifications activées, Peek vérifie jusqu’à toutes les 10 minutes ; le jour où Bulgarian Posts affiche `Handover item to delivery agent`, toutes les 2 minutes à partir de 6 h heure bulgare, tant que le suivi Bulgarian Posts répond. Sinon, Peek vérifie toutes les heures. Après 12 heures avec seulement « Étiquette créée » ou « Pris en charge », ou deux jours sans nouvelles, il vérifie toutes les heures quelle que soit ta façon de le suivre, et toutes les 6 heures après sept jours sur « Étiquette créée ». Lorsque le suivi Bulgarian Posts ne répond pas et que Peek suit le colis uniquement via des services de suivi, il vérifie toutes les heures, toutes les 3 heures après une journée calme, et jusqu’à toutes les 15 minutes le jour où le colis est en livraison, tant que tu as la fiche ouverte ou les notifications activées.

Lorsque le dernier scan date de quatre jours et que le colis reste à « Étiquette créée », « Pris en charge » ou « En transit », Peek le signale avec la date de ce scan, par exemple « Aucune mise à jour depuis lun 12 oct ». La fiche du colis affiche alors « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger Bulgarian Posts. », avec un lien « Demande de l’aide à Bulgarian Posts » vers la page de contacts de Bulgarian Posts. Après trois semaines sans nouveau scan, elle indique « Rien de nouveau depuis plus de trois semaines. Si le colis n’est toujours pas arrivé, contacte le vendeur. Peek continue d’interroger Bulgarian Posts. »

Tu peux choisir les alertes « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Peek assure uniquement le suivi et ne peut ni soumettre de déclaration en douane ni déposer une réclamation auprès de Bulgarian Posts à ta place.

## Questions sur le suivi Bulgarian Posts

### Combien de temps Bulgarian Posts garde-t-il un colis au bureau de poste ?

Les colis standard et envois recommandés restent en attente **20 jours calendaires** à compter de leur arrivée au bureau de poste distributeur (avec un second avis après 10 jours et des frais de magasinage applicables aux colis après 9 jours). Les envois ayant subi un dédouanement sont conservés **45 jours calendaires** au bureau de poste désigné.

### Que signifie `Второ посещение` dans le suivi Bulgarian Posts ?

Cela indique que Bulgarian Posts a émis le second avis écrit de retrait après 10 jours calendaires sans enlèvement. Ton colis attend toujours au bureau de poste — Peek le maintient en « Prêt à être retiré ».

### Que signifie `Return item from customs (Inb)` ?

Cela signifie que la douane bulgare a libéré l’envoi et l’a remis dans le réseau Bulgarian Posts afin qu’il soit acheminé vers ton bureau de poste. Cela ne signifie pas que le colis repart chez l’expéditeur.

### Combien coûte le dédouanement avec Bulgarian Posts ?

Selon le barème en vigueur depuis le 2 novembre 2026, les frais de représentation de Bulgarian Posts (TVA incluse) s’élèvent à 2,70 € pour les achats IOSS jusqu’à 150 € (ou cadeaux C2C jusqu’à 45 €), 3,80 € pour les achats sans IOSS jusqu’à 150 € (ou cadeaux de 45 € à 1 000 €), 12,00 € pour les achats de 150 € à 1 000 € et 20,00 € (+ 5,00 € par type d’article supplémentaire) au-delà de 1 000 €, en plus des droits de douane UE applicables (3 € jusqu’à 150 €), de la taxe UE de 2 € et de la TVA.

:::sources
- [Bulgarian Posts : Home](https://www.bgpost.bg/en) – présentation de l’opérateur postal national
- [Bulgarian Posts : Item Tracking](https://www.bgpost.bg/en/postal-services/track-package) – formats S10 et `PS` à 13 caractères, périmètre du suivi entrant/sortant
- [Bulgarian Posts : Customs](https://www.bgpost.bg/en/customs) – droit de 3 €, taxe de traitement UE de 2 €, barème de représentation douanière du 2 novembre 2026 (2,70 € / 3,80 € / 12 € / 20 €), retrait exclusif en bureau de poste et garde de 45 jours calendaires
- [Bulgarian Posts : Conditions générales (ОУ 2023 PDF)](https://www.bgpost.bg/cms/static/%D0%9E%D0%A3_2023.pdf) – points 43, 45, 46 (pièce d’identité/signature, garde de 20 jours calendaires, second avis à 10 jours, frais de magasinage après 9 jours) et points 56–59 (réclamations)
- [Bulgarian Posts : General Terms Overview](https://www.bgpost.bg/en/about_us/conditions) – service postal universel et conditions EMS/Bulpost
- [Bulgarian Posts : Claims and Inquiries](https://www.bgpost.bg/en/complaints) – règle de réclamation par l’expéditeur, délai de 6 mois, réponse sous 1/3 mois, portail RQMS et téléphones réclamations
- [Bulgarian Posts : Contacts](https://www.bgpost.bg/en/contacts) – `*7678`, `+359 2 962 50 50`, `info@bgpost.bg`, EMS `*6666` / `0700 19 666`, siège à Sofia
- [Bulgarian Posts : FAQ](https://www.bgpost.bg/en/faq) – questions fréquentes des destinataires
- [Commission européenne : Réforme douanière de l’UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – droit de douane de 3 € et taxe de traitement UE de 2 €
- [KZP : Achats en ligne](https://kzp.bg/bg/potrebiteli/good-to-know/online-shopping) – délai de livraison de 30 jours et droit de rétractation de 14 jours
- [CRC (КРС) : Réclamations](https://crc.bg/bg/rubriki/271/jalbi) – plaintes auprès du régulateur postal et délai de réponse de 30 jours
- [Universal Parcel Scraper : bulgarian-posts](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/bulgarian-posts) – détection `PS` et S10 `BG`, règles d’URL `bgpost.bg`, correspondance des statuts IPS anglais/bulgares, redirection Bulpost, fuseau de Sofia et lien d’aide
:::
