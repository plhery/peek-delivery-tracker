---
title: Suivi R+L Carriers : numéro PRO, statuts et réclamations
description: Suis un envoi de fret R+L Carriers aux États-Unis : format du numéro PRO, sens de chaque ligne de suivi et règles en cas de retard, d'avarie ou de perte.
slug: suivi-rl-carriers
published: 2026-11-18
updated: 2026-11-18
---

R+L Carriers est un transporteur de fret routier aux **États-Unis**. Dans le code de Peek, il enlève la marchandise chez l'expéditeur, l'achemine de terminal en terminal et la livre depuis le terminal de destination aux États-Unis. Si une commande volumineuse ou un autre envoi de fret t'est adressé aux États-Unis via R+L, tu le suis grâce à son numéro PRO, communiqué par la boutique ou l'entreprise qui a réservé le transport : colle-le dans le champ ci-dessus. Le propre outil de R+L est la page *Shipment Tracing* sur rlcarriers.com : elle accepte un numéro PRO ou un identifiant de demande d'enlèvement (*Pickup Request ID*), jusqu'à 25 numéros à la fois, ainsi que d'autres références si tu te connectes à un compte MyRLC.

## Numéro de suivi R+L Carriers : le numéro PRO

Le traceur de R+L recherche un envoi par son numéro PRO. Peek reconnaît ces formats, illustrés ici avec des chiffres inventés :

| Numéro (inventé) | Ce que c'est |
| --- | --- |
| `48291057-3` | Neuf chiffres dont le dernier est une clé de contrôle, écrite ici après un tiret. |
| `482910573` | Le même numéro PRO écrit sans tiret. |
| `RL482910-5` | Deux lettres et sept chiffres, le dernier étant une clé de contrôle. Il s'écrit aussi sans tiret (`RL4829105`). |
| `I482910573` | Un numéro PRO tel que l'affiche le traceur de R+L, précédé de la lettre `I`. Ce `I` ne fait pas partie du numéro : Peek l'accepte avec ou sans. |

Le traceur de R+L vérifie le dernier chiffre, ce qui permet de repérer une faute de frappe (voir plus bas). Peek retire les espaces et les tirets avant d'interroger R+L. Son catalogue connaît également R+L Carriers sous les noms R&L Carriers, R and L Carriers et RL Carriers.

**Où le trouver.** Dans l'e-mail d'expédition de la boutique ou sur la page de ta commande ; pour du fret entre entreprises, demande-le à la société expéditrice. Voir [comment trouver ton numéro de suivi](guide:find-tracking-number).

**Autres transporteurs partageant ce format.** Neuf chiffres seuls correspondent aussi à [TNT](carrier:tnt), TForce Freight et XPO, tandis qu'un `I` suivi de neuf chiffres correspond à Australia Post. Si ton message d'expédition cite l'un d'eux, suis l'envoi chez ce transporteur. Plus de détails dans [formats de numéros de suivi](guide:tracking-number-formats).

## Signification des statuts de suivi R+L Carriers

Au-dessus de l'historique, le traceur de R+L affiche un résumé de l'envoi, puis énumère chaque étape avec une date et une heure. Il désigne les terminaux américains par leur ville et le code à deux lettres de leur État. Ses lignes ne comportent aucun code interne : Peek en lit le libellé en anglais. Dans l'ordre habituel d'un acheminement aux **États-Unis** (les points de suspension remplacent une ville et un État, ou un terminal) :

| R+L écrit | Ce que cela signifie pour toi | Peek affiche |
| --- | --- | --- |
| `Picked Up in …` | R+L a enlevé le fret chez l'expéditeur, dans sa ville. Peek conserve l'intitulé complet sans prendre la ville de l'expéditeur comme lieu du scan. | « Pris en charge » |
| `Arrived at origin terminal` | L'envoi est arrivé au terminal R+L du côté de l'expéditeur. | « En transit » |
| `Departed … in route to …` | Il a quitté un terminal en direction du suivant ou du terminal de destination. | « En transit » |
| `Arrived at …` | Il a atteint un terminal intermédiaire, nommé par sa ville et son État. | « En transit » |
| `Arrived at destination terminal` | Il se trouve au terminal proche du destinataire, dernière étape avant la livraison. | « En transit » |
| `Shipment Delayed` | Un contretemps retarde le fret. Le résumé de R+L qualifie alors l'envoi de retardé (*Delayed*) tout en conservant un jour de livraison prévu. | « En transit » |
| `Delivered` | Livré. | « Livré » |

**Le résumé en tête de page.** Au-dessus des lignes d'historique, le traceur de R+L qualifie l'envoi d'*In Transit*, *Delayed* ou *Delivered*. Lorsque ce résumé est plus avancé que les lignes détaillées, Peek retient l'étape du résumé : un envoi résumé comme livré apparaît « Livré » même si la dernière ligne ne le dit pas encore. Quand le traceur fournit un résumé sans aucune ligne d'historique, Peek affiche uniquement le statut.

Le code de Peek ne connaît aucun libellé propre à R+L pour une tournée de livraison en cours, un passage manqué, un incident ou un retour. Toute autre formulation passe par les règles communes que Peek applique à l'ensemble des transporteurs. Une ligne qu'aucune règle ne reconnaît reste affichée telle quelle et compte comme un mouvement (« En transit ») tant que l'envoi n'est pas livré ; après la livraison, elle ne modifie plus le statut. Voir [comprendre les statuts de suivi](guide:tracking-statuses).

## Comment R+L Carriers livre aux États-Unis

Pendant l'acheminement aux **États-Unis**, le traceur de R+L indique un jour de livraison prévu, que Peek affiche jusqu'à la livraison. Le traceur de R+L ne mentionne aucun transporteur partenaire pour le dernier kilomètre et le code de Peek ne transfère l'envoi à aucun autre opérateur. R+L Carriers n'opère pas de réseau de livraison domestique en France, en Belgique ou en Suisse : cette page concerne uniquement des expéditions de fret aux États-Unis.

Cette page ne précise ni créneaux horaires, ni prise de rendez-vous, ni nombre de présentations, ni modalités de retrait en terminal aux États-Unis. Demande à la boutique ou à l'entreprise qui a réservé le transport comment et quand la marchandise sera remise : c'est elle qui détient le contrat auprès de R+L.

## Envoi R+L Carriers introuvable, en retard, endommagé ou perdu

**Introuvable.** Pour un numéro PRO inconnu de son système, le traceur de R+L répond `No records found for Number:` suivi du PRO précédé d'un `I`. Sur peektracker.com, Peek affiche alors « En attente de R+L Carriers » pour un PRO enregistré sous R+L Carriers et réinterroge le traceur plus tard. Vérifie chaque chiffre par rapport à l'e-mail du vendeur ; si le numéro est exact, demande à l'expéditeur si R+L a déjà enlevé la marchandise.

**Chiffre erroné.** Lorsqu'un numéro PRO échoue au contrôle de son dernier chiffre, le traceur de R+L renvoie le PRO suivi de `Pro number failed check digit validation.` Peek affiche alors « Numéro de suivi à vérifier ». Compare le numéro chiffre par chiffre avec le courriel de la boutique ou le bordereau d'expédition.

**Retardé ou sans mise à jour.** Après `Shipment Delayed`, Peek maintient l'envoi « En transit » avec la date prévue par R+L. Au bout de quatre jours sans nouvelle ligne sur un envoi « Pris en charge » ou « En transit », Peek signale « Aucune mise à jour depuis quatre jours ». Renseigne-toi auprès de la boutique ou de l'expéditeur. Voir [pourquoi un suivi ne se met plus à jour](guide:tracking-not-updating).

**Marchandise endommagée, manquante ou perdue aux États-Unis.** Préviens d'abord le vendeur ou l'entreprise expéditrice qui a commandé le transport. Pour du fret acheminé par un transporteur routier **entre États américains**, le droit fédéral des **États-Unis** (`49 U.S.C. 14706` et `49 CFR Part 370`) fixe les règles suivantes :

- Le transporteur répond de la perte ou du dommage réel causé à la marchandise envers la personne habilitée à agir en vertu du récépissé ou du connaissement (*bill of lading*). Sa responsabilité peut être plafonnée à une valeur déclarée par écrit ou par voie électronique par l'expéditeur, ou convenue par écrit entre le transporteur et l'expéditeur.
- Le transporteur ne peut pas accorder moins de 9 mois pour déposer une réclamation, ni moins de 2 ans pour saisir un tribunal à compter du jour où il notifie par écrit le rejet de tout ou partie de la réclamation. Le délai exact figure dans le connaissement ou le contrat.
- Une réclamation est une demande écrite adressée au transporteur dans ces délais, qui identifie l'envoi, engage la responsabilité du transporteur et réclame une somme d'argent déterminée ou déterminable. Une simple réserve d'avarie ou de manquant inscrite sur le bon de livraison ne vaut pas réclamation à elle seule.
- Lorsque l'enquête l'exige, la réclamation doit être accompagnée du connaissement, du justificatif des frais de transport et de la facture. En cas de perte d'un colis complet ou de tout l'envoi que l'enquête ne permet pas d'établir autrement, le transporteur recueille auprès du destinataire (*consignee*) une attestation écrite certifiée confirmant que le fret n'a été reçu d'aucune autre source.
- Le transporteur accuse réception de la réclamation par écrit dans les 30 jours. Il la règle, la rejette ou présente une offre ferme de transaction par écrit dans les 120 jours ; s'il ne peut pas encore clore le dossier, il informe le réclamant par écrit de l'état d'avancement à cette échéance puis tous les 60 jours.

Si le suivi indique `Delivered` alors que rien n'est arrivé, consulte [livré mais non reçu](guide:delivered-not-received). Voir aussi [colis endommagé](guide:damaged-parcel) et [colis perdu](guide:lost-parcel).

## Service client et contact R+L Carriers

Sur le site américain de R+L Carriers figurent ces coordonnées :

- **Téléphone :** `800-543-5589`, numéro affiché en haut des pages de R+L (numéro gratuit américain).
- **R+L Cares :** formulaire en ligne sur le site de R+L pour un litige en suspens, une question ou une remarque ; R+L précise que la demande est transmise directement à sa direction générale (*R+L Executive Leadership*).
- **Réclamations fret :** le menu du site renvoie vers *Cargo Claims*, où R+L présente ses instructions de dépôt d'une réclamation marchandise.

R+L exécute le transport pour le compte du donneur d'ordre : contacte d'abord la boutique pour un achat en ligne, ou l'entreprise expéditrice pour un envoi professionnel. Ce sont elles qui détiennent la réservation auprès de R+L.

## Suivre un envoi R+L Carriers avec Peek

À lui seul, un numéro PRO à neuf chiffres ne prouve pas qu'il s'agit de R+L, car d'autres transporteurs utilisent ce format. Colle-le quand même : Peek suit plus de 3 500 transporteurs. Pour un numéro composé de deux lettres et sept chiffres, Peek interroge immédiatement R+L et affiche « R+L Carriers a ce colis. » lorsque R+L connaît le PRO. Quand tu saisis neuf chiffres avec le tiret avant le dernier chiffre, Peek propose aussi R+L Carriers sous « Correspond à ce numéro » et l'interroge ; en revanche, une fois le numéro ajouté (Peek retire le tiret à l'enregistrement) ou collé sans tiret, neuf chiffres interrogent TNT, TForce Freight et XPO lorsque leur clé concorde, mais pas R+L : appuie sur « Changer » et cherche R+L Carriers par son nom. Sur un envoi déjà enregistré, utilise « Changer de transporteur ». Si par la suite un service de suivi montre le fret en mouvement en nommant R+L Carriers, Peek classe l'envoi sous R+L et interroge directement le traceur de R+L aux vérifications suivantes.

Avec la lettre `I` devant neuf chiffres, le format correspond à R+L Carriers et à Australia Post : Peek interroge les deux et retient celui qui connaît l'envoi. Un lien vers la page *Shipment Tracing* de R+L contenant `pro=` fonctionne également : Peek identifie aussitôt R+L (« Transporteur détecté »).

Peek interroge le traceur de R+L avec le seul numéro PRO, sans compte ni code postal. Il affiche le statut, chaque ligne d'historique (avec la ville et l'État du terminal lorsque la ligne les nomme), le jour de livraison prévu, le nom du destinataire sur ton propre suivi (« Destinataire », masqué sur un lien partagé) ainsi que le poids dans l'unité de ton appareil (en livres si ton appareil mesure en unités américaines, en kilogrammes ailleurs ; R+L fournit le poids en livres). Il laisse de côté l'adresse postale du destinataire, l'expéditeur et le tiers facturé, le nombre de pièces, les références, le type de service, le numéro de connaissement et les documents. Le traceur de R+L ne fournit aucune preuve de livraison à Peek. Sur la fiche de l'envoi, « Ouvrir le site R+L Carriers » ouvre le PRO directement sur le traceur de R+L.

Si le traceur de R+L est injoignable ou ne parvient pas à lire l'envoi, Peek interroge des [services de suivi](guide:universal-tracker) et présente leur historique dans leurs propres termes.

Les lignes de R+L indiquent une date et une heure sans fuseau horaire. Peek lit chacune sur l'horloge de la ville du terminal, à défaut sur celle de l'État du terminal (le fuseau principal lorsqu'un État américain en compte plusieurs), à défaut sur celle de la ligne précédente ou suivante, sinon à l'heure de la côte Est américaine (`America/New_York`), puis convertit toutes les heures dans le fuseau de ton appareil (« Heures dans ton fuseau horaire : … »).

Peek vérifie jusqu'à toutes les 10 minutes tant que tu gardes l'envoi ouvert, ou le jour de sa livraison si les notifications sont activées ; le reste du temps, ainsi qu'après deux jours sans nouvelle ligne (12 heures tant que l'envoi reste à « Étiquette créée » ou « Pris en charge »), il vérifie toutes les heures. Tu peux activer des alertes pour « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Peek ne peut pas contacter R+L, la boutique ou l'expéditeur à ta place, ni convenir d'un rendez-vous ou déposer une réclamation.

## Questions fréquentes sur le suivi R+L Carriers

### Qu'est-ce qu'un numéro PRO chez R+L Carriers ?

C'est le numéro de suivi de R+L Carriers aux États-Unis : neuf chiffres, ou deux lettres suivies de sept chiffres, dont le dernier est un chiffre de contrôle parfois séparé par un tiret. Le traceur de R+L l'écrit précédé de la lettre `I`. Il t'est transmis par la boutique ou l'entreprise qui a expédié le fret.

### Pourquoi R+L Carriers affiche-t-il « No records found for Number » ?

Le traceur de R+L ne connaît pas ce numéro PRO. Vérifie chaque chiffre par rapport à l'e-mail d'expédition, assure-toi que le transporteur annoncé est bien R+L Carriers et demande à l'expéditeur si R+L a déjà pris la marchandise en charge.

### Que veut dire « Shipment Delayed » chez R+L Carriers ?

Un aléa a ralenti le fret en cours de route aux États-Unis. La ligne n'en précise pas la cause ; le résumé de R+L qualifie alors l'envoi de retardé tout en gardant un jour de livraison estimé, et Peek le maintient « En transit ». Demande la raison du retard au vendeur ou à l'expéditeur.

### Quel est le délai pour déposer une réclamation fret auprès de R+L Carriers ?

Pour un transport routier effectué entre États américains, la loi fédérale des États-Unis impose au minimum 9 mois pour déposer une réclamation écrite auprès du transporteur et au minimum 2 ans pour agir en justice après le rejet écrit de tout ou partie de la demande. Le délai exact dépend du connaissement ou du contrat ; cette page ne reproduit pas le tarif propre de R+L. Adresse-toi d'abord à celui qui a réservé le fret ; les consignes de R+L figurent sous la rubrique *Cargo Claims* de son site.

:::sources
- [Universal Parcel Scraper : R+L Carriers](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/rl-carriers) – noms, formats PRO et préfixe `I`, liens de suivi, libellés du traceur et statuts, résumé, erreurs d'absence et de clé de contrôle, date prévue, destinataire, poids, fuseaux horaires et champs ignorés
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – transporteurs partageant ces formats, absence de remise à un tiers et recours aux services de suivi
- [R+L Carriers : Shipment Tracing](https://www.rlcarriers.com/freight/shipping/shipment-tracing) – recherche par numéro PRO ou identifiant d'enlèvement jusqu'à 25 numéros, autres références avec un compte MyRLC
- [R+L Carriers : R+L Cares](https://www.rlcarriers.com/contact/rl-cares) – formulaire destiné à la direction générale de R+L, numéro 800-543-5589 en haut de page et lien Cargo Claims
- [GovInfo : 49 U.S.C. 14706, Liability of carriers under receipts and bills of lading](https://www.govinfo.gov/content/pkg/USCODE-2024-title49/html/USCODE-2024-title49-subtitleIV-partB-chap147-sec14706.htm) – responsabilité envers l'ayant droit au connaissement, valeur déclarée et délais minimaux de 9 mois et 2 ans aux États-Unis
- [GovInfo : 49 CFR Part 370, Investigation and voluntary disposition of loss and damage claims and processing salvage](https://www.govinfo.gov/content/pkg/CFR-2025-title49-vol5/xml/CFR-2025-title49-vol5-part370.xml) – contenu écrit d'une réclamation, réserves sur bon de livraison, pièces justificatives et délais de 30, 120 et 60 jours
:::
