---
title: Suivi DAO : statuts, daoSHOP et livraisons manquées
description: Suivez un colis DAO au Danemark : numéro à 20 chiffres, signification des statuts, livraisons nocturnes, retrait daoSHOP et contact du service client.
slug: suivi-dao
published: 2026-11-23
updated: 2026-11-23
---

DAO (Dansk Avis Omdeling) est un transporteur danois qui distribue des journaux depuis 1921 et livre les colis de boutiques en ligne à votre porte la nuit ou dans un point de retrait daoSHOP. Saisissez le numéro du colis sur la page « Track your parcel » de DAO, dans l’application dao ou collez-le dans le champ ci-dessus. Le numéro figure dans l’e-mail d’expédition de la boutique ou dans le SMS et l’e-mail envoyés par DAO.

## Où trouver votre numéro de suivi DAO

La boutique envoie le numéro dans son e-mail d’expédition. Lorsqu’un colis attend dans un daoSHOP, DAO vous envoie par SMS et e-mail le numéro du colis ainsi qu’un code de retrait à 5 chiffres. L’application dao ajoute automatiquement vos colis dès la création de l’envoi.

DAO indique que ses numéros de colis comptent 9, 13 ou 20 chiffres (chiffres inventés ci-dessous) :

| Numéro | De quoi il s’agit |
| --- | --- |
| `00057126964829173646` | Le numéro de colis à 20 chiffres, commençant par `00057`. C’est le code-barres de l’étiquette et celui que demande le service client DAO. |
| `7482917364058` | Un numéro à 13 chiffres commençant par `7`. |
| `482917364` | Un code d’étiquette électronique à 9 chiffres : un expéditeur achetant un envoi chez DAO l’inscrit sur le colis au lieu d’imprimer une étiquette. |

Les espaces ne comptent pas. Les colis danois d’autres transporteurs [ont un aspect différent](guide:tracking-number-formats) : les numéros postaux de PostNord se terminent par `DK` et ses numéros à 20 chiffres utilisent d’autres préfixes, les numéros GLS comptent 11 ou 12 chiffres et ceux de DPD en comptent 14. Si la page de DAO ne trouve pas le numéro, vérifiez les chiffres puis demandez à la boutique. Consultez [comment trouver votre numéro de suivi](guide:find-tracking-number) si rien n’est arrivé.

## Signification des statuts de suivi DAO

Le suivi DAO affiche les statuts en anglais sur son site anglais et en danois sur dao.as. Voici les libellés anglais, dans l’ordre. Un dépôt ou un terminal apparaît souvent après deux-points, comme dans « Parcel arrived at dao depot: dao Erritsø ».

| Statut | Ce que cela signifie pour vous |
| --- | --- |
| `Shipment created, awaiting registration` | La boutique a [créé l’étiquette](guide:tracking-statuses). DAO n’a pas encore le colis. |
| `Handed in at daoSHOP` | L’expéditeur a déposé le colis dans un daoSHOP. |
| `On the way to terminal` | Il a été collecté dans ce commerce. |
| `Parcel has arrived at terminal` | Arrivé à l’un des terminaux de tri DAO, toujours en transit. |
| `Sorted at: Erritsø terminal` | Trié au terminal nommé, par exemple Erritsø ou Sjælland. |
| `Parcel arrived at dao depot` | Arrivé au dépôt local pour la livraison à domicile. |
| `On the way to daoSHOP` | En route vers le point de retrait. |
| `To be delivered today` | Sur la prochaine tournée à domicile, généralement cette nuit ou tôt le matin. |
| `Not delivered - other reason` | Le livreur n’a pas pu le déposer à votre adresse. |
| `Delivery not possible. Redirected to daoSHOP` | Affiché au même moment : le colis part finalement vers un daoSHOP. |
| `Ready for pickup` | Attend au daoSHOP. Votre code arrive par SMS et e-mail. |
| `Parcel delivered` | Déposé à votre adresse, ou retiré au daoSHOP. |

Deux détails surprennent souvent : `Parcel has arrived at terminal` est une étape de tri et non la fin du trajet, et un `Parcel delivered` à domicile intervient généralement la nuit — regardez donc dans votre boîte aux lettres ou sur votre poignée de porte au matin. Aucun libellé de retour n’a été recensé.

## Livraisons à domicile DAO, daoSHOP et colis manqués

**À domicile.** DAO livre la nuit et tôt le matin, 365 jours par an, sans signature ; ses conditions indiquent le plus souvent avant 7 h (heure danoise). Il glisse le colis dans votre boîte aux lettres s’il y rentre, sinon il l’accroche à votre porte dans un sac plastique recyclé ou le dépose près de la porte, sur le paillasson par exemple. Il n’y a pas d’accusé de réception : les relevés GPS, horaires et photos de DAO font foi.

**Pourquoi il est allé en daoSHOP.** L’expéditeur peut avoir choisi un daoSHOP. Sinon, DAO redirige un colis qu’il ne peut laisser en sécurité, un colis de plus de 5 kg ou trop grand pour le domicile (plus de 55 cm de long ou 120 cm longueur plus circonférence), ou présentant une erreur d’adresse. Dans les immeubles, DAO a besoin d’un accès à l’entrée ; les résidents peuvent l’accorder via le formulaire « Grant access » de DAO.

**Retrait.** Présentez le numéro du colis et le code de retrait à 5 chiffres reçu par SMS ou e-mail. Une autre personne peut le retirer si vous lui transférez le message. Les pages particuliers de DAO recensent plus de 1 500 daoSHOP et celles pour entreprises plus de 1 600 ; trouvez-en un par code postal, rue ou ville sur la carte de dao.as, avec les horaires. DAO peut utiliser un autre commerce que celui choisi, ou parfois une consigne automatique.

**Durée de garde.** Les conditions de DAO accordent 7 jours calendaires pour le retrait, avec un avis et deux rappels. Passé ce délai, le colis repart chez l’expéditeur.

DAO ne publie aucun moyen de reprogrammer ou rediriger un colis, ni de nombre fixe de tentatives.

## Colis DAO manquant, en retard ou endommagé

Le contrat avec DAO appartient à la boutique, mais la page de contact de DAO explique aussi la marche à suivre aux destinataires.

**Aucune mise à jour depuis 48 heures.** Si le colis n’a pas été scanné depuis 48 heures, DAO vous demande de le signaler comme manquant par e-mail ou téléphone avec une description du contenu. Vous recevez une confirmation par e-mail, et DAO dispose de jusqu’à 10 jours ouvrés pour le rechercher. Voir [un suivi qui ne se met pas à jour](guide:tracking-not-updating).

**Livré mais introuvable.** Vérifiez la boîte aux lettres, la porte et ses alentours, puis contactez DAO ; les contenus manquants se signalent à kundeservice@dao.as. Voir [colis livré mais non reçu](guide:delivered-not-received).

**Endommagé.** La page d’aide de DAO demande un signalement dans les 5 jours ouvrés suivant la livraison (5 jours dans la réponse aux expéditeurs), avec photos de l’emballage extérieur, intérieur et de l’article abîmé ; les conditions autorisent une réclamation écrite jusqu’à 7 jours après la livraison. Le formulaire d’avarie de la page ayant disparu, écrivez à kundeservice@dao.as. Les conditions plafonnent la responsabilité à la valeur justifiée ou à 2 500 DKK par colis, selon le montant le plus bas. Les dégâts causés à votre propriété par un livreur se déclarent à erstatning@dao.as.

**Insatisfait de la réponse.** Réclamez d’abord auprès de DAO ; sa licence postale lui donne un mois pour répondre. En cas de désaccord (retard ou indemnisation par exemple), adressez-vous à Trafikstyrelsen, qui délivre la licence de DAO. Les conditions de DAO citent le Center for Klageløsning chez Nævnenes Hus, mais Nævnenes Hus renvoie les plaintes concernant les opérateurs postaux agréés vers Trafikstyrelsen.

**Depuis l’extérieur de l’UE.** Depuis le 1er juillet 2026, les commandes en ligne jusqu’à 150 € paient 3 € de droits par catégorie de produit ; depuis le 1er novembre 2026 s’ajoute une taxe européenne de traitement de 2 € par catégorie de produit. Voir [colis bloqués en douane](guide:customs).

## Service client et numéro de contact DAO

Coordonnées indiquées sur la page de contact de DAO (horaires en heure danoise) :

- **Téléphone :** +45 72 33 08 50, du lundi au vendredi de 8 h à 19 h, samedi et dimanche de 8 h à 16 h (heure danoise).
- **E-mail :** kundeservice@dao.as, en indiquant votre numéro de colis à 20 chiffres.
- **Formulaires :** « Need help? » et retours, accessibles depuis [la page d’aide et de contact de DAO](https://dao.as/en/contact-dao/) ; le formulaire de dommages qui y est lié n’existe plus.
- **Réclamations sur le livreur :** kvalitetabo@dao.as ou 44 51 75 70.

> DAO met en garde contre de faux SMS et e-mails en son nom évoquant un numéro de rue manquant, un colis retenu ou des frais à payer. DAO n’envoie pas de liens de paiement. Vérifiez dans l’application dao ou sur dao.as. Si vous avez saisi des données bancaires, appelez votre banque ; si vous avez approuvé une demande MitID que vous n’avez pas initiée, bloquez MitID.

## Suivre un colis DAO avec Peek

[Peek](/) interroge simultanément DAO et les autres transporteurs probables, dont PostNord, pour un numéro à 20 chiffres commençant par `0005712696` ou `0005715127`. Lorsque seul DAO le connaît, vous voyez « DAO a ce colis. » ; lorsque PostNord le connaît aussi, Peek indique combien de transporteurs reconnaissent le numéro et vous laisse choisir. Un numéro à 13 ou 9 chiffres nécessite « Changer » puis DAO, ou un lien de suivi dao.as, qui fonctionne avec n’importe quel numéro. DAO renvoie alors le numéro à 20 chiffres, suivi ensuite.

Les scans DAO ne comportent pas de fuseau horaire : Peek les lit à l’heure locale du lieu du scan (heure danoise au Danemark) et affiche l’historique propre de DAO. Il peut indiquer le daoSHOP vers lequel le colis se dirige ou où il attend. Les statuts conservent les libellés anglais de DAO. Tant que le colis est ouvert dans Peek, ou le jour de sa livraison si les notifications sont activées, Peek vérifie jusqu’à toutes les 10 minutes, et à partir de 6 h (heure danoise) le jour où le suivi affiche `To be delivered today`, jusqu’à toutes les 2 minutes lorsque le suivi direct de DAO répond (jusqu’à toutes les 5 minutes après une vérification par navigateur) ; sinon toutes les heures. Après deux jours sans nouvelles (12 heures tant que le colis affiche encore « Étiquette créée » ou « Pris en charge »), Peek vérifie toutes les heures, et après sept jours sans nouvelles à l’étape « Étiquette créée », toutes les 6 heures.

Si quatre jours passent sans nouvelle mise à jour alors que le colis reste sur « Étiquette créée », « Pris en charge » ou « En transit », Peek affiche « Aucune mise à jour depuis quatre jours » (ou « Aucune mise à jour depuis {{date}} »), accompagné du message « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger DAO. » et du lien « Demande de l’aide à DAO ».

Vous pouvez choisir des notifications pour « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Pour un colis envoyé à l’étranger, le numéro du partenaire s’affiche et, si le lien DAO nomme un transporteur suivi par Peek, le colis peut y être suivi. DAO ne donne ni date d’arrivée prévue ni poids. Les noms masqués, votre adresse et code postal, l’adresse, les horaires et la position du point relais, le code de retrait à 5 chiffres (envoyé par DAO par SMS/e-mail ou visible dans l’application dao) ainsi que tout signataire sont laissés de côté. Peek ne peut pas contacter DAO pour vous.

## Questions sur le suivi DAO

### Que signifie « Parcel has arrived at terminal » ?

Le colis est arrivé dans l’un des terminaux de tri de DAO et est toujours en cours d’acheminement.

### Combien de temps met DAO à livrer ?

DAO annonce aux expéditeurs particuliers un délai de 2 à 4 jours en daoSHOP ; aux boutiques en ligne, il promet une livraison à domicile en moins de 16 heures et le lendemain en daoSHOP. Les colis à domicile arrivent la nuit ou tôt le matin, 365 jours par an.

### Existe-t-il une application de suivi DAO ?

Oui, l’application dao, sur iPhone et Android. Elle ajoute automatiquement les colis qui vous sont destinés et affiche le numéro du colis ainsi que le code de retrait.

### Un SMS de DAO demandant un paiement est-il authentique ?

Non. DAO indique ne jamais envoyer de liens demandant de payer ou de transférer de l’argent ; vérifiez plutôt le colis dans l’application dao ou sur dao.as.

:::sources
- [DAO : Track your parcel](https://dao.as/en/track-your-parcel/) – formats de numéros, message introuvable, page de statuts
- [DAO : Find din pakke](https://dao.as/find-din-pakke/) – suivi et libellés en danois
- [DAO : Help and contact](https://dao.as/en/contact-dao/) – téléphone, horaires, e-mails, formulaires, redirection, code de retrait, colis manquants, 5 jours pour dommages
- [DAO : Parcels](https://dao.as/en/private-parcels/) – dépôts nocturnes, boîte aux lettres et porte, plus de 1 500 daoSHOP, retrait, étiquette électronique
- [DAO : daoSHOP parcels](https://dao.as/en/private-parcels-daoshop/) – 2 à 4 jours pour expéditeurs particuliers
- [DAO : daoSHOP for businesses](https://dao.as/en/business-parcels-daoshop/) – plus de 1 600 daoSHOP, livraison le lendemain
- [DAO : daoHOME for businesses](https://dao.as/en/business-parcels-daohome/) – moins de 16 heures
- [DAO : Hjemmelevering](https://dao.as/privat-pakker-daohome/) – 365 jours, limites de taille et poids à domicile
- [DAO : Terms and Conditions](https://dao.as/wp-content/uploads/2025/10/Terms-and-Conditions-Dansk-Avis-Omdeling_211025.pdf) – sans signature, preuve, délai de retrait, réclamations sous 7 jours, responsabilité, plaintes
- [DAO : Handelsbetingelser](https://dao.as/wp-content/uploads/2025/10/Handelsbetingelser-for-Dansk-Avis-Omdeling-2025_211025.pdf) – 7 jours calendaires, version danoise faisant foi
- [DAO : Find daoSHOP](https://dao.as/en/find-daoshop-en/) – recherche sur carte, horaires d’ouverture
- [DAO : Grant access](https://dao.as/en/grant-access/) – accès aux immeubles d’habitation
- [DAO : Avoid fraud](https://dao.as/en/avoid-fraud/) – faux messages et marche à suivre
- [DAO : dao app](https://dao.as/en/daoapp/) – ajout automatique des colis, numéro de colis et code de retrait
- [DAO : Om dao](https://dao.as/om-dao/) – société, journaux depuis 1921
- [DAO : API documentation](https://api.dao.as/docs/v1) – code-barres GS1-128 à 20 chiffres
- [Trafikstyrelsen : Posttilladelse – DAO – 2024](https://www.trafikstyrelsen.dk/publikationsliste/post-og-koerekort-publikationer/2024/mar/posttilladelse-dao-2024) – réponse aux réclamations sous un mois
- [Trafikstyrelsen : Klager over postvirksomheder](https://www.trafikstyrelsen.dk/post-og-koerekort/styrelsens-arbejde-paa-postomraadet/klager) – recours après décision de l’opérateur
- [Nævnenes Hus : Til køber](https://naevneneshus.dk/naevnsoversigt/maeglingsteamet-for-forbrugerklager-og-forbrugerklagenaevnet/til-koeber/) – plaintes postales redirigées vers Trafikstyrelsen
- [European Commission : EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – droit de 3 €, frais de traitement de 2 €
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – ce que lit Peek
:::
