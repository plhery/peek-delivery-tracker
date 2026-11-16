---
title: Suivi J&T Express Allemagne : numéro JTDE et statuts
description: Suivre un colis J&T Express en Allemagne : format du numéro JTDE, sens des statuts en anglais et que faire si le colis est bloqué, perdu ou non reçu.
slug: jt-express-allemagne-suivi
published: 2026-11-16
updated: 2026-11-16
---

J&T Express Germany (J&T Express Germany GmbH, Francfort-sur-le-Main) est le réseau allemand du groupe de livraison J&T Express : il livre des colis en Allemagne. Si une boutique a expédié ta commande vers une adresse allemande avec J&T, le numéro de suivi commence par `JTDE` : colle-le dans le champ ci-dessus, ou recherche-le sur [le suivi de J&T sur jtexpress.de](https://www.jtexpress.de/tracking). C'est la boutique qui a expédié ta commande qui détient ce numéro, dans son e-mail d'expédition ou sur la page de ta commande.

## Numéro de suivi J&T Express Germany : à quoi il ressemble

D'après la FAQ de J&T, ses numéros de suivi commencent par JTDE. Peek ne leur connaît qu'une seule forme : `JTDE` suivi de 12 chiffres, soit 16 caractères en tout. Avec des chiffres inventés :

| Numéro (inventé) | Ce que c'est |
| --- | --- |
| `JTDE000000000000` | `JTDE` et 12 chiffres : un colis J&T Express Germany. Peek reconnaît J&T Express Germany tout de suite. |
| `JTDE00000000000` | Un chiffre en moins : ce n'est pas la forme de J&T Express Germany, et Peek ne reconnaît aucun transporteur. Vérifie qu'il ne manque pas un chiffre. |

Si un numéro comporte un chiffre de trop ou de moins après `JTDE` et qu'aucun transporteur ne répond pour lui, la page du colis sur peektracker.com le signale (« Ça commence comme un numéro de suivi J&T Express Germany, mais il manque un caractère : il y a peut-être une faute de frappe. Compare-le avec ton message d’expédition. » ou « … mais il y a un caractère en trop : il y a peut-être une faute de frappe. Compare-le avec ton message d’expédition. »).

**Où le trouver.** J&T indique que l'expéditeur te le transmet, et qu'il figure sur la page de commande de la boutique en ligne, dans l'e-mail de confirmation de commande, dans l'e-mail ou le SMS d'expédition, dans les e-mails annonçant ou confirmant la livraison ainsi que sur l'étiquette du colis. Si tu ne le trouves pas, J&T te renvoie vers l'expéditeur ou vers le service client de la plateforme d'achat. Voir aussi [comment trouver son numéro de suivi](guide:find-tracking-number).

Le suivi de J&T accepte jusqu'à 10 numéros en une seule recherche, séparés par des espaces ou des virgules.

**Numéros qui se ressemblent.** Les réseaux J&T des autres pays ont leurs propres suivis et leurs propres préfixes : `JTUK` suivi de 12 chiffres correspond à J&T Express UK, `JTAU` et 12 chiffres à J&T Express Australia, `JTE` et 12 chiffres à J&T Express Middle East, tandis que `JT` suivi de 13 chiffres est utilisé par J&T en Asie du Sud-Est et en Chine (J&T Express China). Seul `JTDE` appartient au réseau allemand. Si le suivi de J&T répond `Keine Abfrageergebnisse` (`No tracking records yet` en anglais) ou que peektracker.com continue d'afficher « En attente de J&T Express Germany », compare le numéro avec l'e-mail de la boutique ou demande confirmation au vendeur.

## Statuts de suivi J&T Express Germany : ce qu'ils veulent dire

Sur le suivi de J&T, chaque scan ne comporte qu'une date, une heure et une phrase : aucun lieu n'est indiqué. Le site s'ouvre en allemand et n'a pas de version française ; si tu passes le site en anglais ou en chinois depuis le menu de langue, il recharge les scans dans cette langue. Peek demande les phrases en anglais et ne les traduit pas : voici donc celles qui s'affichent, dans l'ordre où un colis les rencontre :

| J&T écrit | Ce que cela veut dire pour toi | Peek affiche |
| --- | --- | --- |
| `The shipment has been successfully picked up and is being prepared for transportation` | J&T a récupéré le colis chez l'expéditeur. | « Pris en charge » |
| `The shipment arrives at the distribution center and is prepared for transport to the next destination.` | Le colis est arrivé dans un centre de tri J&T. | « En transit » |
| `The shipment is dispatched from the transit center to the next destination` | Il a quitté ce centre vers l'étape suivante. | « En transit » |
| `Your package has been loaded and is on its way to you` | Il est dans la camionnette : en cours de livraison. | « En livraison » |
| `Delivery failed because the address could not be found` | Le livreur n'a pas trouvé ton adresse. | « Tentative de livraison » |
| `Delivery failed, customer refused to accept` | Le destinataire a refusé le colis. | « Tentative de livraison » |
| `Your parcel has been delivered to the pickup point. Please check your email for details` | Le livreur a déposé le colis en point de retrait ; J&T envoie les informations de retrait par e-mail. | « Prêt à être retiré » |
| `Your package has been successfully delivered.` | Livré. J&T précise le mode de remise (voir ci-dessous). | « Livré » |
| `Returns have begun` | Après un échec de livraison, le colis repart vers l'expéditeur. | « Retourné à l’expéditeur » |
| `The shipment has been returned` | Le retour à l'expéditeur est terminé. | « Retourné à l’expéditeur » |

Une phrase qui ne figure pas dans ce tableau suit les règles communes que Peek applique à tous les transporteurs ; si Peek ne la reconnaît pas non plus ainsi, il l'affiche telle que J&T l'a écrite et classe le colis « En transit », sauf s'il est déjà en livraison ou au-delà : dans ce cas, son statut reste inchangé.

La ligne de chargement (`Your package has been loaded and is on its way to you`) peut revenir plusieurs fois : J&T l'ajoute à chaque tournée de livraison. La ligne de livraison se poursuit avec le type de remise entre crochets 【】 et se termine par une mention sur le service client. Peek retire cette mention finale et ne garde le type de remise, sans crochets, que lorsqu'il s'agit de l'un de ces six choix :

- `Left with neighbor` : remis à un voisin ;
- `Front Door Porch` : déposé devant la porte d'entrée ;
- `Front Desk Reception` : remis à la réception ;
- `Garden Yard` : déposé dans le jardin ou la cour ;
- `Mailbox` : déposé dans la boîte aux lettres ;
- `Garage` : déposé dans le garage.

Un colis laissé chez un voisin s'affiche donc dans Peek sous la forme `Your package has been successfully delivered. The delivery type is Left with neighbor.`

**Quel voisin ?** Sur le suivi de J&T, ce scan comporte le bouton `Zustelldetails anzeigen` (`Click to view delivery details` sur le site en anglais). Clique dessus et saisis le code postal de l'adresse de livraison pour voir le nom du voisin et son numéro de rue ; après cinq codes postaux erronés, il faut patienter avant de réessayer. Peek interroge le suivi avec le seul numéro et indique uniquement que le colis a été remis à un voisin, sans nom ni adresse. Il écarte aussi tout nom ou numéro de téléphone présent dans une ligne de J&T.

## Livraison J&T en Allemagne : voisins, lieu sûr et photo

Dans les historiques que connaît le code de Peek, le livreur de J&T charge le colis pour une tournée puis le livre, le dépose en point de retrait ou indique pourquoi la livraison a échoué. Le code de Peek ne montre aucune remise à un autre transporteur : le suivi de J&T accompagne le colis jusqu'à la remise. Le chauffeur qui sonne chez toi peut travailler pour l'un des partenaires franchisés de J&T : sur son site, J&T indique chercher dans toute l'Allemagne des partenaires dont les équipes assurent les livraisons du dernier kilomètre.

**En tournée.** `Your package has been loaded and is on its way to you` signifie que le colis est parti en tournée de livraison. Dans ces historiques, cette ligne apparaît le matin même, quelques heures avant la remise. Le suivi de J&T ne donne ni créneau horaire ni jour de livraison prévu, si bien que Peek ne peut pas en afficher non plus.

**Préférences de livraison.** Le lien figurant dans ta notification d'expédition renvoie vers la [page de préférences de livraison de J&T](https://www.jtexpress.de/delivery-preference) et contient ton numéro de suivi chiffré. Confirme ton code postal, puis choisis l'une des options proposées par J&T : remise contre signature à toi-même, à un voisin (tu indiques son nom et s'il habite à côté ou au même étage) ou à une réception, ou dépôt sans signature devant la porte d'entrée, dans le jardin, dans la boîte aux lettres ou dans le garage. Selon J&T, le livreur suit ton choix — ce qui évite les secondes présentations et les erreurs de livraison — et les informations saisies ne servent qu'à cette livraison. Si tu choisis un dépôt sans signature, la page prévient que le risque de perte t'incombe dès que le colis est déposé à l'endroit désigné. Si l'état d'avancement du colis ne permet plus de choisir, la page l'indique.

**Photo de livraison.** D'après la notice de confidentialité de J&T sur la preuve de livraison photographique (POD), le livreur photographie le colis lorsqu'il te le remet ou le dépose à l'endroit convenu, en ne cadrant que le colis et la partie du lieu nécessaire ; toute personne apparaissant par inadvertance sur l'image est floutée automatiquement de façon irréversible. En règle générale, J&T supprime la photo 6 mois après la livraison, mais peut la conserver jusqu'à la clôture d'une réclamation ouverte dans ce délai et la communiquer à l'expéditeur pour traiter cette réclamation.

**Colis refusé ou adresse introuvable.** Après `Delivery failed, customer refused to accept`, dans les historiques connus du code de Peek, le livreur a rechargé le colis pour une nouvelle tournée le lendemain matin. Après `Delivery failed because the address could not be found`, vérifie l'adresse figurant sur ta commande. S'il manque un élément, comme un numéro d'appartement ou ton nom sur la sonnette, préviens aussitôt la boutique : elle a réservé l'envoi avec l'adresse que tu lui avais fournie. La FAQ de J&T conseille elle aussi de contacter sans tarder l'expéditeur ou la plateforme d'achat : une fois le colis expédié, la possibilité de modifier l'adresse dépend de l'étape où se trouve le colis et des règles de l'expéditeur. `Returns have begun` peut suivre un échec de livraison. Voir [comment modifier une adresse de livraison](guide:change-delivery-address) et [livraison manquée](guide:missed-delivery).

**Tentatives et points de retrait.** Le site de J&T n'indique ni nombre de tentatives de livraison, ni délai de garde, ni horaires de passage, et ne publie pas de liste ou de carte de points relais, de consignes ou de dépôts. Une ligne de suivi `Your parcel has been delivered to the pickup point. Please check your email for details` peut toutefois apparaître après un départ en tournée : le livreur a alors laissé le colis dans un point de retrait et J&T envoie par e-mail les indications nécessaires pour aller le chercher.

## Colis J&T en retard, introuvable ou retourné

C'est la boutique qui a choisi J&T : pour une commande en ligne, le vendeur reste donc ton premier interlocuteur. En droit allemand (§ 475 du Code civil allemand, BGB), lorsque tu achètes auprès d'un professionnel en tant que consommateur, le vendeur supporte le risque de perte ou d'endommagement pendant le transport, à moins que tu aies toi-même mandaté un transporteur que le vendeur ne t'avait pas proposé au préalable.

**Suivi qui ne bouge plus.** J&T ne publie pas de délais de livraison pour les destinataires. Un colis resté sur « Pris en charge » ou « En transit » sans nouvelle ligne pendant quatre jours reçoit une alerte dans Peek (« Aucune mise à jour depuis quatre jours ») accompagnée d'un lien « Demande de l’aide à J&T Express Germany ». Si rien n'évolue ensuite, contacte la boutique ou appelle l'assistance téléphonique de J&T avec ton numéro de suivi. Plus de détails dans [pourquoi un suivi ne bouge plus](guide:tracking-not-updating).

**Aucun suivi pour l'instant.** Un numéro que le suivi de J&T ne connaît pas encore apparaît sur peektracker.com sous « En attente de J&T Express Germany ». Les boutiques créent souvent le numéro avant de remettre le colis au transporteur. Si deux jours plus tard J&T ne connaît toujours pas ce numéro et qu'aucune autre source n'a répondu pour lui, le site affiche « Après deux jours, le transporteur ne connaît toujours pas ce numéro. Vérifie-le, choisis un autre transporteur ou supprime le colis. » ; Peek le vérifie dès lors une fois par jour, puis une fois par semaine lorsqu'une semaine s'est écoulée depuis son ajout. Vérifie alors le numéro auprès de la boutique et demande si le colis a bien été remis au transporteur.

**Marqué livré mais non reçu.** Lis la ligne de livraison dans Peek : si elle mentionne un endroit comme `Mailbox` ou `Garage`, regarde à cet endroit ; si elle indique `Left with neighbor`, demande à tes voisins ou consulte le nom du voisin sur le suivi de J&T à l'aide de ton code postal (voir plus haut). La FAQ de J&T invite aussi à vérifier si un proche, un voisin ou le personnel de l'immeuble a réceptionné le colis. Toujours introuvable ? Signale-le à la ligne téléphonique de J&T et demande à la boutique un remboursement ou un nouvel envoi, numéro de suivi à l'appui. Voir aussi [colis livré mais non reçu](guide:delivered-not-received).

**Perdu, endommagé ou retourné.** Demande un remboursement ou un remplacement à la boutique avec ton numéro de suivi. La FAQ de J&T ne fournit ni formulaire de réclamation, ni délai, ni barème d'indemnisation pour les destinataires : les délais d'avis sont fixés par la loi allemande. Selon le § 438 du Code de commerce allemand (HGB), un dommage apparent doit être signalé au plus tard à la livraison (le dire au livreur suffit), et un dommage non apparent dans les sept jours suivant la livraison sous forme de texte, par exemple par e-mail à J&T ; l'envoi dans les délais suffit. Si aucun accord n'est trouvé avec J&T au sujet d'un colis perdu ou endommagé, tu peux saisir gratuitement par formulaire en ligne l'organe de conciliation postale de la Bundesnetzagentur (Schlichtungsstelle Post), ouvert aux destinataires comme aux expéditeurs. Les colis des grandes boutiques en ligne voyagent toutefois le plus souvent à des conditions spéciales qui excluent la conciliation : tu peux alors t'adresser au service consommateurs postal de la Bundesnetzagentur (Verbraucherservice Post). Voir [colis perdu](guide:lost-parcel), [colis endommagé](guide:damaged-parcel) et [colis retourné à l'expéditeur](guide:returned-to-sender).

## Service client et contact J&T Express Germany

Les canaux de J&T Express Germany, tels que lus sur [sa page de contact](https://www.jtexpress.de/contact) le 16 novembre 2026 :

- **Téléphone :** +49 6931090650 (069 31090650 depuis l'Allemagne), du lundi au vendredi de 8 h 00 à 20 h 00, fermé les jours fériés.
- **E-mail :** `de-jtservice@jtexpress.de`.
- **Chat en direct :** bouton « Chat starten » (« Start Chat » sur la page en anglais), sur la page de contact et la page FAQ.
- **Photos de livraison et données personnelles :** `de-dpo@jtexpress.de` (d'après la notice de confidentialité POD).
- **Bureau :** J&T Express Germany GmbH, Am Giener 2, 55268 Nieder-Olm. Les mentions légales (Impressum) indiquent le siège enregistré : Wiesenhüttenstraße 11, 60329 Frankfurt am Main.

J&T ne précise pas dans quelles langues répond son service client ; le site existe en allemand, en anglais et en chinois. Pour une commande en ligne, commence malgré tout par la boutique : c'est elle qui a confié la livraison à J&T. Pour un achat sur une place de marché, passe par l'aide de la page de commande. Sur un colis immobilisé, le lien « Demande de l’aide à J&T Express Germany » dans Peek ouvre la page de contact de J&T.

## Suivre un colis J&T Express Germany avec Peek

[Peek](/) identifie aussitôt J&T Express Germany à partir d'un numéro `JTDE` (« Transporteur détecté »), saisi avec ou sans espaces, en majuscules ou en minuscules, et interroge directement le suivi de J&T avec le seul numéro : ni code postal ni compte. Un lien `www.jtexpress.de/tracking` contenant `?q=` et le numéro fonctionne aussi ; s'il contient un autre type de numéro, Peek lit ce numéro comme si tu l'avais collé seul. Si ton numéro n'est pas un `JTDE`, colle-le quand même : Peek suit plus de 3 500 transporteurs. Choisir J&T Express Germany via « Changer » pour une autre forme de numéro ne sert à rien : Peek t'indique que ce transporteur « n’émet pas de numéros comme celui-ci ».

Peek affiche le statut, chaque scan à son heure exacte ainsi que le moment de la livraison. J&T donne chaque scan comme un instant précis, de sorte que Peek l'affiche dans le fuseau horaire de ton appareil ; sur peektracker.com et dans l'application iPhone, la dernière ligne sous l'historique le rappelle (« Heures dans ton fuseau horaire : … »). Les scans restent dans l'anglais de J&T, même si tu les lis en allemand sur jtexpress.de. J&T ne communique ni lieu, ni jour de livraison prévu, ni poids : Peek n'affiche donc pas de carte. Sur un historique très long, il conserve les 100 scans les plus récents. « Ouvrir le site J&T Express Germany », sur la page du colis, ouvre ton numéro directement sur le suivi de J&T.

Tant que J&T n'a pas scanné le colis, Peek continue d'interroger le suivi et affiche sur peektracker.com « En attente de J&T Express Germany » (ou, si J&T ne connaît toujours pas le numéro deux jours plus tard, l'invitation à le vérifier). Lorsque le suivi de J&T est injoignable ou ne connaît pas le numéro, Peek interroge des services de suivi et montre leur historique, avec leurs propres libellés. Si par la suite le suivi de J&T et les services de suivi répondent tous sans aucun scan pour un colis qui en possède déjà, Peek garde les scans déjà trouvés et, si cela dure, indique : « Le transporteur n’a pas de nouveau scan pour l’instant. Ceux déjà trouvés sont gardés, et Peek continue de vérifier. »

Tant que le suivi de J&T répond directement, Peek vérifie toutes les 10 minutes, et toutes les 2 minutes le jour où J&T scanne le colis en livraison, dès 6 h, heure locale (via des services de suivi, toutes les 15 minutes). Après deux jours sans nouveau scan (ou 12 heures tant que le colis reste à « Pris en charge »), Peek passe à une vérification par heure. Tu peux activer les alertes sur « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Peek ne peut contacter ni J&T ni la boutique à ta place, ni modifier l'adresse ou reprogrammer une livraison.

## Questions fréquentes sur le suivi J&T Express Germany

### J&T Express Germany est-il un vrai transporteur ?

Oui. J&T Express Germany GmbH, immatriculée à Francfort-sur-le-Main, est la filiale allemande de J&T Express, un groupe de messagerie fondé en Indonésie en 2015 et coté à la Bourse de Hong Kong. Le régulateur postal allemand, la Bundesnetzagentur, inscrit la société dans son registre des prestataires postaux (Anbieterverzeichnis Post) depuis le 1er septembre 2026. J&T recherche des partenaires franchisés dans toute l'Allemagne pour assurer les livraisons et collectes du dernier kilomètre, et chaque phrase du tableau des statuts ci-dessus provient de vrais colis J&T Express Germany.

### Quel voisin a réceptionné mon colis J&T ?

Le suivi de J&T te le dit : sous le scan de livraison, clique sur `Zustelldetails anzeigen` (`Click to view delivery details` en anglais) et saisis le code postal de l'adresse de livraison pour afficher le nom du voisin et son numéro de rue. Peek affiche seulement `Left with neighbor`, sans nom ni adresse.

### J&T Express a-t-il des points relais en Allemagne ?

Le site allemand de J&T n'en mentionne aucun, mais une ligne de J&T Express Germany connue de Peek indique que le livreur a déposé le colis en point de retrait, J&T envoyant les informations par e-mail. Sur la page des préférences de livraison, tu peux demander une remise en main propre, chez un voisin, à une réception ou dans un lieu sûr à ton adresse.

### Pourquoi mon numéro de suivi J&T est-il introuvable ?

Vérifie qu'il commence bien par `JTDE` suivi de 12 chiffres : les numéros des autres réseaux J&T, comme `JTUK` au Royaume-Uni, ne relèvent pas du réseau allemand. Si le format est bon, J&T n'a peut-être pas encore pris le colis en charge : son suivi répond `Keine Abfrageergebnisse` (`No tracking records yet`), et peektracker.com affiche « En attente de J&T Express Germany ». Si deux jours plus tard J&T ne connaît toujours pas le numéro et qu'aucune autre source n'a répondu, le site t'invite à le vérifier, à choisir un autre transporteur ou à supprimer le colis. Si aucun scan n'apparaît après plusieurs jours, interroge la boutique.

### Comment contacter J&T Express en Allemagne ?

Appelle le +49 6931090650 (du lundi au vendredi, de 8 h 00 à 20 h 00), utilise le chat en direct sur jtexpress.de ou écris à `de-jtservice@jtexpress.de`. Pour une commande en ligne, adresse-toi toutefois d'abord à la boutique qui a expédié le colis.

:::sources
- [J&T Express Germany: Track & Trace](https://www.jtexpress.de/tracking) – suivi, jusqu'à 10 numéros, langues du site et rechargement des scans, message sans résultat, coordonnées du voisin après vérification du code postal et cinq essais
- [J&T Express Germany: Frequently Asked Questions](https://www.jtexpress.de/faq) – numéros JTDE et où les trouver, colis marqué livré mais non reçu, adresse erronée, chat en direct ; aucun délai, nombre de tentatives ni barème de réclamation
- [J&T Express Germany: Delivery Preferences](https://www.jtexpress.de/delivery-preference) – lien dans l'e-mail de notification, contrôle du code postal, modes avec et sans signature, transfert du risque au lieu désigné, moins de secondes livraisons et d'erreurs, données utilisées uniquement pour cette livraison
- [J&T Express Germany: Contact Us](https://www.jtexpress.de/contact) – téléphone, horaires, chat en direct, e-mail et adresse du bureau à Nieder-Olm
- [J&T Express Germany: Legal notice](https://www.jtexpress.de/legal-statement) – J&T Express Germany GmbH, Francfort-sur-le-Main
- [J&T Express Germany: POD Privacy Policy](https://www.jtexpress.de/pod-policy) – photos de livraison, floutage automatique, suppression au bout de 6 mois, réclamations et contact protection des données
- [J&T Express Germany: Become Our Partner](https://www.jtexpress.de/career) – partenaires franchisés dans toute l'Allemagne pour la livraison et la collecte du dernier kilomètre
- [J&T Express Germany: About Us](https://www.jtexpress.de/about) – fondation en 2015 en Indonésie et cotation à Hong Kong
- [Universal Parcel Scraper: J&T Express Germany](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/j-and-t-de) – noms, format JTDE, liens de suivi, phrases du suivi lues sur de vrais colis et leurs étapes, tournées de livraison, types de remise, éléments retirés et page d'aide
- [Universal Parcel Scraper: interrogation commune des réseaux J&T](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/j-and-t-au) – phrases en anglais, heures exactes des scans, absence de lieu, de jour prévu et de poids, retrait des noms et numéros de téléphone, 100 scans les plus récents
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formats des autres réseaux J&T, absence de remise à un autre transporteur et services de suivi en relais
- [Gesetze im Internet: § 475 BGB](https://www.gesetze-im-internet.de/bgb/__475.html) – risque du transport supporté par le vendeur lors d'une vente à un consommateur
- [Gesetze im Internet: § 438 HGB](https://www.gesetze-im-internet.de/hgb/__438.html) – signalement d'un dommage apparent à la livraison, dommage caché sous sept jours sous forme de texte et respect du délai dès l'envoi
- [Bundesnetzagentur: Anbieterverzeichnis Post](https://www.bundesnetzagentur.de/DE/Fachthemen/Post/Anbieterverzeichnis/PostAnbieterverzeichnis.html) – registre des prestataires postaux : J&T Express Germany GmbH inscrite le 1er septembre 2026
- [Bundesnetzagentur: Häufig gestellte Fragen zur Schlichtung Post](https://www.bundesnetzagentur.de/DE/Vportal/Schlichtung/Schlichtung_Post/_FAQ_Schlichtung/faqSchlichtung.html) – demande gratuite en ligne ouverte aussi aux destinataires, conditions spéciales sur les colis des grandes boutiques en ligne et Verbraucherservice Post
:::
