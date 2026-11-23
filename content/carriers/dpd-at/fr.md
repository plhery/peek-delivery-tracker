---
title: Suivi DPD Austria : statuts, points Pickup et contact
description: Suivre un colis DPD en Autriche : numéro à 14 chiffres et PIN, statuts myDPD, délais Pickup, douane et contacter DPD Austria.
slug: suivi-dpd-austria
published: 2026-11-23
updated: 2026-11-23
---

DPD Austria est une société privée de livraison de colis basée à Leopoldsdorf. En Autriche, elle livre généralement en un jour ouvré après réception du colis remis par le magasin. Suis un colis sur [myDPD](https://www.mydpd.at/), le site et l'application de suivi de DPD Austria, avec le numéro de colis à 14 chiffres figurant dans l'e-mail d'expédition du magasin, ou colle ce numéro dans le champ ci-dessus.

## Numéro de colis DPD Austria et code PIN

Le numéro de colis DPD comporte 14 chiffres. Le magasin te l'envoie, ou DPD te le transmet par e-mail si le magasin lui a communiqué ton adresse ; avec un compte myDPD, il apparaît sous « My parcels ». Le PIN (*Parcel Information Number*) figure sur l'avis rouge et blanc laissé par DPD en cas d'absence. myDPD accepte les deux ; Peek a besoin du numéro de colis. Les formats que Peek reconnaît :

| Numéro (inventé) | Ce que c'est |
| --- | --- |
| `06204819273645` | 14 chiffres commençant par 0620 à 0630, 0632 à 0635, 0643 à 0645 ou 0649 : un numéro de colis issu d'un dépôt de DPD Austria. |
| `01904819273645` | 14 chiffres commençant par 0101 à 0199 : Peek le lit d'abord comme un numéro de [DPD Germany](carrier:dpd-de). DPD Austria peut tout de même le livrer (voir plus bas). |

Si ton étiquette affiche une lettre ou un chiffre supplémentaire après les 14 chiffres, ne le saisis pas. Si l'e-mail du magasin mentionne [Austrian Post](carrier:austrian-post), GLS Austria ou un autre transporteur, le numéro leur appartient. Pas de numéro du tout ? Lis [où trouver ton numéro de suivi](guide:find-tracking-number).

## Signification des statuts de suivi DPD Austria

myDPD affiche chaque scan avec le dépôt concerné, en allemand ou en anglais. Dans l'ordre habituel d'un colis (une *Paketstation* est une consigne à colis) :

| myDPD affiche | Ce que ça veut dire pour toi |
| --- | --- |
| `Auftragsdaten übermittelt` | L'expéditeur a transmis les données du colis à DPD, mais pas encore le colis. La FAQ anglaise de DPD parle de « Order data transmitted to DPD ». |
| `Paket unterwegs` | DPD a le colis : collecté au premier dépôt ou scanné dans un hub en chemin. |
| `Im Paketzustellzentrum` | Au dépôt chargé de la livraison. La livraison suit. |
| `Unfortunately, parcel was loaded incorrectly and will be delivered soon.` | Chargé dans le mauvais véhicule. Un autre dépôt le scanne avant livraison. |
| `In Zustellung` | Avec le livreur, en route vers toi. |
| `Empfänger leider nicht angetroffen.`, `Unfortunately, consignee not present. Notification was left.` | Personne n'était là. Le livreur a laissé un avis de passage. |
| `Unfortunately, parcel was not delivered.` | Non livré lors de cette tournée. Il retourne au dépôt pour une nouvelle tournée. |
| `In Zustellung an Pickup Paketstation`, `In delivery to Pickup parcel shop` | Après un passage manqué, le livreur dépose le colis dans une consigne ou un point Pickup. |
| `Paket wurde in der Pickup Paketstation hinterlegt`, `Parcel was handed over to Pickup parcel shop`, `Takeover by Pickup parcel shop` | Prêt à être retiré en consigne ou point Pickup. DPD envoie un code de retrait par e-mail ou SMS, avec l'adresse pour une consigne. |
| `Paket durch Empfänger abgeholt` | Retiré au point Pickup. |
| `Paket wurde erfolgreich abgestellt` | Déposé à l'endroit autorisé par un accord de dépôt (*deposit okay*), comme ton garage. |
| `Parcel was not picked up` | Personne ne l'a retiré au point Pickup à temps. Le retour à l'expéditeur démarre généralement à la même minute. |
| `Parcel will be returned to the sender.` | Le colis entame son retour vers l'expéditeur. |
| `Parcel was picked up by driver from Pickup parcel shop` | Un chauffeur l'a repris en point Pickup : au début, c'est le dépôt par l'expéditeur ; après t'y avoir attendu, c'est le retour vers l'expéditeur. |

## Livraison DPD en Autriche : Predict, modifications et points Pickup

**Predict.** Si le magasin transmet ton e-mail ou ton numéro de mobile à DPD, DPD t'annonce la livraison à l'avance. Le jour même, le Live-Tracking affiche un créneau horaire et une carte actualisée toutes les 15 minutes. Le créneau n'est pas garanti : le trafic et la météo peuvent le décaler. Pour un colis venant de l'étranger, fie-toi à l'estimation du magasin.

**Modifier la livraison.** Inscris-toi sur myDPD pour modifier la livraison jusqu'à peu avant le passage, avec le numéro de colis ou le PIN : autre jour ou adresse, voisin désigné, point ou consigne Pickup, ou autorisation de dépôt sans signature à un endroit précis (comme la terrasse). Avec un compte, tu peux aussi définir un point/consigne Pickup favori ou une autorisation de dépôt permanente pour tous tes colis.

**En cas d'absence.** DPD peut remettre le colis à toute personne présente à ton domicile ou bureau qui l'accepte, sans contrôler sa pièce d'identité. Après une première tentative infructueuse, DPD peut le laisser à un voisin ou, à défaut, au point ou à la consigne Pickup la plus proche, en t'indiquant son nom et son adresse. L'avis laissé par DPD et myDPD précisent où il se trouve. Voir [livraison manquée](guide:missed-delivery).

**Retrait.**
- Point relais Pickup : 7 jours calendaires, avec rappels après 3 et 5 jours. Présente le code de retrait reçu par e-mail ou SMS, ou une pièce d'identité avec signature. DPD ne peut pas renvoyer le code, et myDPD ne l'affiche pas. Chaque commerce a ses propres horaires.
- Consigne Pickup (*Paketstation*) : 5 jours calendaires, avec rappel après 3 jours. Le code reçu par e-mail ou SMS est indispensable. Les consignes sont accessibles 24 h/24.

Passé ce délai, le colis repart chez l'expéditeur : demande au magasin de le réexpédier. Voir [colis non retiré](guide:uncollected-parcel) et [retour à l'expéditeur](guide:returned-to-sender).

## Colis DPD en retard, perdu, abîmé ou en douane

Pour un remboursement ou un nouvel envoi, adresse-toi au magasin : c'est lui qui a le contrat avec DPD.

**Pas de mise à jour.** `Auftragsdaten übermittelt` pendant plusieurs jours signifie que le magasin n'a pas encore remis le colis à DPD : DPD invite à contacter l'expéditeur. Si le Live-Tracking ne bouge plus pendant plusieurs heures le jour de la livraison, contacte DPD avec ton numéro de colis. Voir [pourquoi un suivi ne bouge plus](guide:tracking-not-updating).

**Livré mais non reçu.** Comme le demande DPD, vérifie d'abord ton endroit de dépôt autorisé et le voisin indiqué sur l'avis de passage. Toujours rien ? Utilise le formulaire DPD pour destinataires (« Where was my parcel delivered/handed over. ») et préviens le magasin. Plus dans [colis livré mais non reçu](guide:delivered-not-received).

**Perdu.** Si personne ne retrouve le colis, contacte le service client de DPD et le magasin. Voir [colis perdu](guide:lost-parcel).

**Abîmé.** DPD n'enquête sur un dommage qu'à la demande de l'expéditeur : préviens donc le magasin, qui réclame auprès de DPD et propose selon ses conditions un remboursement ou un nouvel envoi. Les conditions de DPD exigent que les dommages cachés soient signalés par écrit sous six jours calendaires au plus tard, et les dommages visibles dès la livraison : agis vite. Voir [colis abîmé](guide:damaged-parcel).

**Douane.** Les achats en ligne provenant de hors UE sont soumis à un droit de douane européen de 3 € par article sur les colis jusqu'à 150 € depuis le 1er juillet 2026, et à des frais de dossier de l'Union de 2 € par article, quelle que soit la valeur, depuis le 1er novembre 2026. Un article correspond aux marchandises partageant classement tarifaire, description et, si requis, origine. Les pages de DPD Austria ne précisent pas comment ces frais sont répercutés : vérifie auprès du magasin s'ils étaient inclus dans ton prix. Voir [colis bloqué en douane](guide:customs).

## Contacter DPD Austria

Peek ne peut pas contacter DPD ni le magasin à ta place. Sur les pages de DPD Austria :

- **Téléphone :** 05 03 73 420 (+43 5 03 73 420 depuis l'étranger), ligne particuliers, du lundi au vendredi de 7 h à 18 h (heure d'Autriche).
- **En ligne :** [formulaire de contact DPD Austria pour destinataires](https://www.dpd.com/at/en/support/contact-form-consignee/), avec le numéro de colis ou le PIN, ton nom et ton e-mail. DPD demande de n'utiliser qu'un seul canal à la fois afin de ne pas ralentir le traitement.
- **Courrier :** DPD Direct Parcel Distribution Austria GmbH, Arbeitergasse 46, 2333 Leopoldsdorf.

> De faux e-mails et SMS au nom de DPD circulent régulièrement. DPD recommande : ne paie pas, n'ouvre aucune pièce jointe, ne clique pas sur les liens de connexion et supprime le message. En cas de perte financière, DPD conseille de porter plainte auprès de la police. Voir [arnaques aux SMS de livraison](guide:delivery-scam-texts).

## Suivre un colis DPD Austria avec Peek

Colle le numéro de colis à 14 chiffres dans [Peek](/). Aucun code postal n'est requis.

Si le numéro appartient à l'une des plages de dépôts de DPD Austria, Peek interroge DPD Austria parmi les premiers transporteurs, où que tu sois, et affiche « DPD Austria a ce colis. » dès que myDPD le connaît avec son dernier scan en Autriche. S'il n'est pas encore référencé, tu vois « pas encore trouvé · Peek continue de vérifier ». Ajoute-le quand même : la fiche affiche « Probablement DPD Austria » et « En attente du premier scan de DPD Austria » pendant que Peek continue de vérifier.

Les autres numéros à 14 chiffres n'orientent pas Peek directement vers DPD Austria : ils correspondent à plusieurs filiales DPD et d'autres transporteurs, que Peek interroge tour à tour. Si DPD Austria transporte ton colis, appuie sur « Changer » et choisis DPD Austria (aussi appelé « DPD Österreich » ou « DPD AT »), ou utilise « Changer de transporteur » sur un colis déjà ajouté. Un lien myDPD (`?p=` ou `/meine-pakete?ipn=`) ou dpdgroup.com/at/ avec `parcelNumber=` identifie DPD Austria immédiatement.

**Depuis une autre société DPD.** Pour un colis parti de [DPD Netherlands](carrier:dpd-nl), DPD Belgium ou DPD Hungary, dès que DPD le place dans un dépôt de livraison en Autriche ou plus loin, Peek interroge DPD Austria avec le même numéro et bascule dessus dès que myDPD a un scan au moins aussi récent. Avant cela, la fiche indique déjà DPD Austria comme transporteur livreur. Un colis de [DPD Germany](carrier:dpd-de) reste sous DPD Germany : dès qu'il arrive en Autriche, Peek lit aussi myDPD et affiche ses scans lorsqu'ils sont plus complets.

**Ce que tu vois.** Le statut, l'historique dans les mots de myDPD avec la ville et le pays de chaque dépôt, ainsi que l'heure de livraison en heure d'Autriche (un scan dans un pays d'un autre fuseau garde son heure locale). Un colis en point Pickup s'affiche comme « Prêt à être retiré ». Peek ne reprend ni les noms, ni les adresses, ni le créneau horaire, ni le point Pickup, ni le signataire, ni les options de livraison, et n'affiche pas de date prévue ; myDPD ne fournit pas le poids. Pour le point Pickup, consulte myDPD ou l'avis de DPD ; pour le code, l'e-mail ou le SMS de DPD.

Peek vérifie jusqu'à toutes les 10 minutes, ou toutes les 2 minutes dès 6 h heure locale le jour où DPD scanne le colis en livraison, tant que le suivi de DPD répond (via les services de suivi, toutes les heures, et jusqu'à toutes les 15 minutes le jour de la livraison quand le colis est ouvert ou les alertes activées). Il te notifie selon ton choix : « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Après deux jours sans nouvelles (12 heures tant que le colis reste sur « Étiquette créée » ou « Pris en charge »), Peek vérifie toutes les heures. Pour modifier la livraison, passe par myDPD.

## Questions sur le suivi DPD en Autriche

### Quel est le délai de livraison de DPD en Autriche ?

En général un jour ouvré en Autriche, à partir du moment où DPD reçoit le colis du magasin. Avant cela, myDPD peut afficher `Auftragsdaten übermittelt`.

### Combien de temps DPD garde-t-il mon colis en point relais ou consigne Pickup ?

7 jours calendaires en point relais Pickup et 5 jours en consigne Pickup (*Paketstation*). Ensuite il repart chez l'expéditeur.

### Qu'est-ce que le numéro PIN DPD ?

Le *Parcel Information Number*, inscrit sur l'avis laissé par DPD en cas d'absence. myDPD l'accepte ; dans Peek, utilise le numéro de colis à 14 chiffres.

### Que veut dire Auftragsdaten übermittelt ?

Le magasin a transmis les données du colis à DPD, mais DPD n'a pas encore le colis. Si cela dure plusieurs jours, contacte le magasin.

### Quel est le numéro de téléphone de DPD Austria ?

Le 05 03 73 420 pour les particuliers, du lundi au vendredi de 7 h à 18 h (heure d'Autriche).

:::sources
- [DPD Austria : Receiving parcels](https://www.dpd.com/at/en/receiving-parcels/) – Predict, Live-Tracking, modifications, accord de dépôt, consignes
- [DPD Austria : myDPD](https://www.mydpd.at/) – suivi en ligne et application
- [DPD Austria : FAQ receiving parcels](https://www.dpd.com/at/en/support/faq/faq-receiving-parcels/) – numéro de colis, PIN, un jour ouvré, modifications
- [DPD Austria : FAQ Pickup](https://www.dpd.com/at/en/support/faq/pickup/) – délais de garde, code de retrait, pièce d'identité
- [DPD Austria : General terms and conditions](https://www.dpd.com/at/en/gtc/) – délais de garde, rappels, retour
- [DPD Austria : General terms and conditions, national (PDF)](https://www.dpd.com/wp-content/uploads/sites/224/2022/01/AGB0447_AGBs_DPDA_national_en.pdf) – voisin ou Pickup après un premier essai, Predict, créneau, signalement de dommages
- [DPD Austria : What does the status "Order data transmitted to DPD" mean?](https://www.dpd.com/at/en/faq/was-bedeuter-der-status-auftragsdaten-an-dpd-uebermittelt/) – contacter l'expéditeur
- [DPD Austria : Status not changing in live tracking](https://www.dpd.com/at/en/faq/der-paketstatus-meines-pakets-hat-sich-schon-lange-nicht-mehr-veraendert-woran-kann-das-liegen/) – plusieurs heures sans changement
- [DPD Austria : Parcel shown as delivered but not delivered](https://www.dpd.com/at/en/faq/ein-paket-das-mir-als-zugestellt-angezeigt-wird-ist-noch-nicht-geliefert-worden-woran-liegt-das/) – vérifications initiales
- [DPD Austria : My parcel can't be located](https://www.dpd.com/at/en/faq/mein-paket-ist-unauffindbar-was-soll-ich-tun/) – service client
- [DPD Austria : Parcel arrives damaged](https://www.dpd.com/at/en/faq/was-ist-zu-tun-wenn-ein-paket-beschadigt-beim-empfanger-ankommt/) – contacter l'expéditeur en premier
- [DPD Austria : Contact](https://www.dpd.com/at/en/support/contact/) – téléphone, horaires
- [DPD Austria : Contact form for consignees](https://www.dpd.com/at/en/support/contact-form-consignee/) – un seul canal à la fois
- [DPD Austria : Security](https://www.dpd.com/at/en/security/) – faux e-mails et SMS
- [DPD Austria : Legal notice](https://www.dpd.com/at/en/legal-notice/) – société, adresse, téléphone depuis l'étranger
- [Commission européenne : réforme douanière de l'UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – droit de 3 € depuis le 1er juillet 2026, frais de 2 € depuis le 1er novembre 2026
- [Commission européenne : guide sur le droit de 3 € et les frais de dossier de l'Union (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – définition d'un article
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – ce que Peek lit, libellés myDPD, plages de dépôts, transferts
:::
