---
title: Suivi TForce Freight : numéros PRO, statuts et litiges
description: Suis un envoi de fret TForce Freight aux États-Unis ou au Canada : numéro PRO, signification des statuts, livraisons à domicile et réclamations.
slug: suivi-tforce-freight
published: 2026-11-22
updated: 2026-11-22
---

TForce Freight est un transporteur de fret en lots partiels (LTL) : il achemine palettes et marchandises lourdes entre ses centres de service aux États-Unis et dans tout le Canada, ainsi que vers plus de 600 points au Mexique. Si une commande volumineuse ou une expédition de fret t'est adressée via TForce, suis-la grâce à son numéro PRO, communiqué par la boutique ou l'entreprise qui a réservé le transport, en le collant dans le champ ci-dessus. Le suivi officiel de TForce correspond à la page Tracking de www.tforcefreight.com.

## Numéro de suivi TForce Freight : le numéro PRO

La page de suivi de TForce recherche une expédition par numéro PRO, ou par numéro de connaissement (BOL) ou de bon de commande (PO) avec une plage de dates d'expédition. Peek suit par numéro PRO (chiffres inventés) :

| Numéro (inventé) | À quoi il correspond |
| --- | --- |
| `482917363` | Le PRO : neuf chiffres. Le dernier est un chiffre de contrôle égal au reste de la division des huit premiers par 7, donc toujours compris entre 0 et 6. |
| `48291736` | Un PRO sans son 0 initial. Le suivi TForce ajoute le 0, tout comme Peek une fois TForce Freight sélectionné. |
| `2205482917363` | Un numéro PARS utilisé par les courtiers en douane pour le fret entrant au Canada : 2205 suivi du PRO. Colle-le en entier : Peek interroge TForce avec le PRO qu'il contient. |

Un PRO à neuf chiffres se terminant par 7, 8 ou 9 contient une faute de frappe. Le catalogue de Peek connaît aussi TForce Freight sous les noms UPS Freight et TForce.

**Où le trouver.** Dans l'e-mail d'expédition de la boutique ou sur ta page de commande ; pour du fret interentreprises, demande à l'expéditeur. Voir [comment trouver son numéro de suivi](guide:find-tracking-number).

**Même format chez d'autres transporteurs.** Neuf chiffres avec le même chiffre de contrôle correspondent aussi à [TNT](carrier:tnt) et XPO. TForce Logistics (TForce Final Mile), dont les numéros commencent par `TF`, est un transporteur distinct dans Peek. Si ton e-mail nomme l'un d'eux, sélectionne ce transporteur. Plus de détails dans [les formats de numéros de suivi](guide:tracking-number-formats).

## Les statuts de suivi TForce Freight expliqués

Le suivi de TForce affiche un statut à côté du PRO, comme `In Transit` ou `Delivered`, puis l'historique du plus récent au plus ancien, chaque ligne indiquant la ville et l'État du centre de service, la date et l'heure. Les lignes ne portent pas de code : Peek lit leur libellé. Dans l'ordre habituel :

| TForce Freight indique | Ce qu'il signifie pour toi | Peek affiche |
| --- | --- | --- |
| `SHIPMENT HAS BEEN PICKED-UP.` | Le chauffeur a enlevé la marchandise chez l'expéditeur. | « Pris en charge » |
| `SHIPMENT IS AT A TFORCE FREIGHT LOCATION` | L'envoi est au centre de service côté expéditeur. | « En transit » |
| `DEPARTURE` | Il a quitté un centre de service. | « En transit » |
| `SHIPMENT HAS ARRIVED AT A SERVICE CENTER.` | Il est arrivé dans un centre de service intermédiaire ou dans celui chargé de te livrer. | « En transit » |
| `SENT EMAIL. TFORCE FREIGHT IS ATTEMPTING TO OBTAIN APPOINTMENT INFORMATION` | TForce a envoyé un e-mail au destinataire pour fixer un rendez-vous de livraison. Réponds-y. | « En transit » |
| `A DELIVERY APPOINTMENT HAS BEEN SCHEDULED WITH THE CONSIGNEE.` | Un jour de livraison a été convenu. Cette ligne apparaît parfois après la livraison. | « En transit » |
| `OUT FOR DELIVERY` | Chargé dans le camion de livraison. | « En livraison » |
| `Shipment not delivered. Delivery attempted after normal business hours. Tforce will attempt delivery on next business day we service this area.` | Non livré lors de cette tournée. Toute ligne commençant par `Shipment not delivered.` correspond à un échec, suivi du motif. | « Tentative de livraison » |
| `SHIPMENT HAS BEEN DELIVERED TO THE CONSIGNEE.` | Livré. La page de TForce indique qui a signé. | « Livré » |

Un statut `Delivered` à côté du PRO s'affiche toujours comme « Livré » ; sinon la ligne la plus récente reconnue par Peek décide, et les autres lignes restent telles que TForce les publie. La page de suivi TForce peut aussi envoyer des alertes par e-mail. Voir [la signification des statuts de suivi](guide:tracking-statuses).

## Comment TForce Freight livre

TForce achemine le fret jusqu'au centre de service qui dessert ta zone, puis le livre par camion. Son suivi affiche un jour estimé de livraison, que Peek conserve tant que l'envoi n'est pas livré ni retourné (et tant que la date n'est pas dépassée). Le code de Peek ne transfère pas l'envoi vers un autre transporteur.

Le tarif réglementaire de TForce (LTL Rules Tariff UPGF 102-T, en vigueur depuis le 17 août 2026) précise le déroulement d'une livraison :

- **Domiciles.** Là où ses camions peuvent légalement circuler, TForce doit d'abord convenir de la date et de l'heure approximative avec toi ou l'expéditeur. Une activité exercée à domicile compte comme un domicile. La livraison s'effectue devant l'entrée au rez-de-chaussée, l'entrée du garage ou l'abri voiture, ou tu retires la marchandise au terminal. La livraison résidentielle est un service supplémentaire facturé au donneur d'ordre ou au payeur du fret.
- **Au pied du camion.** La livraison standard consiste à décharger à côté du camion. La livraison à l'intérieur et le hayon élévateur sont facturés en supplément : demande au donneur d'ordre s'ils sont inclus.
- **Avis d'arrivée.** Lorsque les documents d'expédition le demandent, TForce prévient le destinataire au plus tard le jour ouvré suivant l'arrivée au centre de service de livraison, normalement par téléphone ou par e-mail.
- **Non livré.** Si l'envoi est refusé ou le destinataire introuvable, TForce informe l'expéditeur de la mise en entreposage, avec des frais de garde possibles. Une nouvelle tentative est facturée sauf si tu retires l'envoi au centre de service ; à domicile, uniquement si tu as manqué un rendez-vous convenu.

**Vers le Canada.** Comme l'explique la page Canada de TForce, le fret venant des États-Unis passe la douane à la frontière via un courtier en douane. Le fret non dédouané attend dans un entrepôt sous douane à destination, avec des frais de stockage. L'importateur officiel paie les honoraires du courtier, les droits et les taxes.

## Envoi TForce Freight introuvable, en retard, endommagé ou perdu

**Introuvable.** Pour un PRO inconnu, le suivi TForce affiche `Not Available` et `Please check ProNumber` avec le numéro. Peek indique alors « En attente de TForce Freight » si tu as classé l'envoi sous TForce Freight, ou « Aucun historique de suivi pour le moment » sinon, et réessaie plus tard. Vérifie chaque chiffre et demande à l'expéditeur si TForce a bien reçu la marchandise.

**Un chiffre est faux.** Un PRO dont le dernier chiffre échoue au contrôle n'appartient pas à TForce, et Peek n'interroge jamais TForce à son sujet. Si tu choisis malgré tout TForce Freight, Peek indique qu'il « n’émet pas de numéros comme celui-ci » et l'envoi affiche « Numéro de suivi à vérifier ».

**Suivi figé.** Après quatre jours sans nouveau scan en transit (hors douane ou incident signalé), Peek affiche « Aucune mise à jour depuis… », rappelle qu'« Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger TForce Freight. » et propose « Demande de l’aide à TForce Freight ». Demande à la boutique ou à l'expéditeur. Voir [le suivi qui ne se met plus à jour](guide:tracking-not-updating).

**Livraison manquée.** Après une ligne `Shipment not delivered.`, Peek signale l'envoi (« Consulte les instructions du transporteur »). Appelle TForce ou demande à qui a réservé le transport de planifier une nouvelle livraison. Voir [livraison manquée](guide:missed-delivery).

**Manquant ou endommagé à la livraison.** La page Cargo Claims Information de TForce demande de vérifier chaque livraison et de prévenir immédiatement le chauffeur :

- Compte les colis par rapport au bordereau de livraison et ouvre les cartons endommagés en présence du chauffeur.
- Note exactement ce qui manque ou est abîmé sur ton exemplaire et sur celui de TForce.
- Ne refuse pas l'envoi sauf si le dommage rend la marchandise sans valeur.
- Demande au responsable Over, Short & Damage (OS&D) de ton centre de service local de rechercher les pièces manquantes.

**Dommage découvert plus tard.** Signale-le dans les 5 jours ouvrés suivant la livraison et demande une inspection, en laissant l'emballage tel que tu l'as trouvé. Un rapport d'inspection ne vaut pas réclamation.

**Déposer une réclamation.** Une réclamation est une demande écrite d'indemnisation par le propriétaire de l'envoi. Pour une commande en ligne, commence par la boutique, qui détient le contrat. Règles de TForce :

- Dépose-la dans les neuf mois suivant la livraison, ou suivant la date d'expédition si rien n'est arrivé.
- Utilise le Standard Claim Form de TForce ou un courrier précisant l'envoi et le montant, accompagné de la facture fournisseur, de la facture de fret, du connaissement et d'éventuelles factures de réparation. Envoie par courrier postal ou fax (ci-dessous), pas les deux.
- TForce accuse réception sous 30 jours. Sa responsabilité est limitée à 25,00 $ par livre et par colis, voire moins pour certains biens, sauf couverture supplémentaire souscrite sur le connaissement.

Si le suivi indique livré sans rien recevoir, consulte [colis indiqué livré mais non reçu](guide:delivered-not-received). Voir aussi [colis endommagé](guide:damaged-parcel) et [colis perdu](guide:lost-parcel).

## Service client et contact TForce Freight

Coordonnées publiées sur www.tforcefreight.com (pages Tracking, Fact Sheet, Rules Tariffs avec le LTL Rules Tariff UPGF 102-T, Canada, Cargo Claims Information, Contact Us avec Email TForce Freight Customer Support, et Service Information) :

- **Service client :** (800) 333-7400, numéro gratuit depuis les États-Unis, le Canada, Guam, Porto Rico et les îles Vierges américaines, pour le suivi, la preuve de livraison et les incidents. Depuis ailleurs : (804) 231-8555.
- **E-mail :** le formulaire Email TForce Freight Customer Support, accessible depuis la page Contact Us de TForce, qui comporte un champ pour le PRO.
- **Service des réclamations :** (888) 653-9720 pour suivre un dossier. Les réclamations s'envoient à Claims Processing, TForce Freight, PO Box 1216, Richmond, VA 23218, ou par fax au (866) 580-1944.
- **Ton centre de service :** le Service Center Directory, sous Service Information sur www.tforcefreight.com.

La boutique ou l'expéditeur est souvent plus rapide, car il détient le contrat avec TForce.

## Suivre un envoi TForce Freight avec Peek

Le format d'un PRO ne suffit pas à désigner TForce : neuf chiffres conviennent aussi à [TNT](carrier:tnt), XPO et d'autres transporteurs. Colle-le tout de même : Peek suit plus de 4 500 transporteurs. Pendant que « Vérification des services de suivi… » s'affiche, Peek interroge les transporteurs compatibles ; si seul TForce reconnaît l'envoi, son nom apparaît avec « a ce colis ». Pour un numéro PARS (`2205` suivi du PRO), TForce est interrogé en premier ; pour un PRO à huit chiffres sans le zéro initial, choisis TForce Freight sous « Correspond à ce numéro » après avoir appuyé sur « Changer », ou utilise « Changer de transporteur » plus tard. Un lien vers le suivi TForce (`proNumbers=`) ou l'ancien suivi UPS Freight (`TrackProNumber=`) identifie TForce immédiatement (« Transporteur détecté »).

Peek interroge le suivi TForce à partir du seul PRO. Il affiche le statut, chaque étape avec sa ville et son État, le jour de livraison estimé, le pays de destination sur la carte, le service, le poids dans tes unités, le destinataire et sa localité, ainsi que le nom du signataire une fois l'envoi livré ; il laisse de côté l'expéditeur, le rendez-vous, les numéros BOL et PO, le nombre de pièces, les remorques, les codes d'agence et le document de preuve de livraison. « Ouvrir le site TForce Freight » ouvre ton numéro (ou le PRO extrait d'un PARS) sur le suivi TForce. Si le suivi TForce est injoignable ou affiche un CAPTCHA, Peek peut se rabattre sur des [services de suivi](guide:universal-tracker).

TForce publie chaque étape à l'heure locale de son centre de service, sans fuseau horaire. Peek la lit sur l'horloge de la ville indiquée — sinon de son État ou province, d'une étape voisine ou de la destination, et à défaut sur l'heure de l'Est — puis l'affiche dans ton fuseau horaire (« Heures dans ton fuseau horaire : »).

Peek interroge directement TForce jusqu'à toutes les 10 minutes, et jusqu'à toutes les 2 minutes à partir de 6 h sur l'horloge du dernier scan (heure de l'Est par défaut) le jour où TForce scanne l'envoi en livraison, tant que l'envoi est ouvert dans Peek ou le jour de la livraison avec les notifications activées. Après deux jours sans nouvelle (12 heures tant que l'envoi reste sur « Étiquette créée » ou « Pris en charge »), Peek vérifie toutes les heures. Pour les notifications, choisis « Toutes les mises à jour », « Importantes uniquement » ou « Jour de livraison uniquement ». Peek ne peut pas contacter TForce, la boutique ou l'expéditeur à ta place, ni déposer une réclamation.

## Questions fréquentes sur le suivi TForce Freight

### Qu'est-ce qu'un numéro PRO TForce Freight ?

Le numéro de suivi de TForce : neuf chiffres, dont le dernier est un chiffre de contrôle de 0 à 6. Il t'est communiqué par la boutique ou l'entreprise qui a réservé le transport.

### Puis-je suivre TForce Freight sans numéro PRO ?

Sur la page de suivi de TForce, oui : par numéro de connaissement (BOL) ou de bon de commande (PO) avec une plage de dates d'expédition. Peek suit uniquement par numéro PRO.

### TForce Freight livre-t-elle à domicile ?

Oui, là où ses camions peuvent légalement circuler, moyennant un supplément. TForce convient d'abord de la date et de l'heure approximative avec toi ou l'expéditeur, puis livre devant l'entrée au rez-de-chaussée, l'entrée du garage ou l'abri voiture. La livraison intérieure et le hayon sont aussi en supplément.

### De combien de temps je dispose pour déposer une réclamation auprès de TForce Freight ?

Neuf mois à compter de la livraison, ou de la date d'expédition si rien n'est arrivé. Signale les dommages découverts après la livraison dans les 5 jours ouvrés.

### Quel est le numéro du service client de TForce Freight ?

(800) 333-7400, gratuit depuis les États-Unis et le Canada. Depuis ailleurs : (804) 231-8555.

:::sources
- [Universal Parcel Scraper: TForce Freight](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/tforce-freight) – noms, formats PRO, liens, libellés et étapes, message introuvable, données conservées par Peek, horloges
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – transporteurs partageant le format, absence de relais, services de suivi en secours
:::
