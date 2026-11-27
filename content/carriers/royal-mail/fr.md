---
title: Suivi colis Royal Mail : numéro, statuts, douane et relais
description: Suivi de colis Royal Mail en France, Belgique & Suisse : numéros GB, statuts en anglais, relais La Poste, bpost ou La Poste suisse, frais de douane 3 € / 2 €.
slug: suivi-royal-mail
published: 2026-11-17
updated: 2026-11-27
---

Royal Mail est l'opérateur postal national du Royaume-Uni (son outil *Track your item* sur `royalmail.com` suit aussi les colis Parcelforce Worldwide). Si tu attends un colis britannique en France, en Belgique ou en Suisse, colle ton numéro `…GB` dans le champ ci-dessus ou sur `royalmail.com` pour traduire les statuts anglais, savoir quelle poste locale prend le relais et connaître les frais de douane applicables.

## Numéro de suivi Royal Mail : formats et où le trouver

Les numéros suivis par Royal Mail prennent trois formes principales. Si tu ne trouves rien dans les messages du vendeur, demande-lui directement ; voir aussi [comment trouver son numéro de suivi](guide:find-tracking-number).

| Format (exemple inventé) | Ce que c'est |
| --- | --- |
| `AB482910576GB` | Le numéro suivi habituel (norme UPU S10) : 2 lettres, 9 chiffres et `GB`. Le 9e chiffre est une clé de contrôle. |
| `0A1234567000000000ABC` | Référence à 21 caractères issue d'un code-barres 2D sur les étiquettes intérieures britanniques. |
| `4829105738ABCDEF` | Référence à 16 caractères composée de chiffres et des lettres `A` à `F`. |

Numéros proches à ne pas confondre :

- Un préfixe commençant par `E` (`EA`, `EB`…), `CP` ou `GI` suivi de 9 chiffres et `GB` désigne un colis Parcelforce Worldwide (le suivi Royal Mail accepte aussi les références Parcelforce de 14 caractères commençant par `PB` suivi de deux lettres et dix chiffres).
- Le même format S10 terminé par le code d'un autre pays (`RR482910576FR`, `RR482910576BE`, `RR482910576CH`) vient de la poste d'expédition. L'envoi garde cet identifiant unique jusqu'à ta porte, conformément à la norme S10 de l'UPU, et Royal Mail le prend en charge à l'arrivée au Royaume-Uni (sauf les envois EMS commençant par `E`, confiés à Parcelforce).
- Une suite de 16 chiffres sans aucune lettre n'est pas un format Royal Mail : Peek interroge en même temps d'autres transporteurs qui utilisent cette longueur, dont DHL eCommerce et [TNT](carrier:tnt).

## Que signifient les statuts Royal Mail en français

Royal Mail publie son suivi uniquement en anglais. Voici les statuts que tu rencontreras le plus souvent sur un colis venant du Royaume-Uni ou livré là-bas, dans l'ordre habituel du trajet :

| Royal Mail affiche | Ce que cela veut dire pour toi |
| --- | --- |
| `Sender preparing item` / `Sender has despatched item` | L'expéditeur a créé l'étiquette ou annoncé l'envoi ; Royal Mail n'a pas encore scanné le colis. |
| `We're expecting it` | Envoi annoncé informatiquement, pas encore remis à Royal Mail. |
| `Item Received` | Royal Mail a pris le colis en charge. La même mention réapparaît dans les centres de tri britanniques puis à l'arrivée dans le pays de destination. |
| `In transit` | Le colis circule entre deux plateformes du réseau. |
| `Item Leaving the UK` | Ton colis a quitté le Royaume-Uni vers le pays de destination. |
| `Your item is currently with Customs in the destination country.` | Le colis est en cours de dédouanement à l'importation dans ton pays. |
| `Released from Customs` | Le dédouanement est terminé ; le colis repart vers la distribution locale. |
| `Customs charges raised` | De la TVA, des droits de douane ou des frais de dossier doivent être réglés avant la remise (voir ci-dessous). |
| `Item has left the overseas International Processing Centre` | Le colis a quitté le centre d'échange international de ton pays. |
| `Despatched to delivery office` / `Item received at Sorting Office` | En route vers le bureau ou le centre de distribution qui dessert ton adresse. |
| `Ready for Delivery` | Arrivé au centre local ou prêt pour la tournée du jour. |
| `Out for delivery` / `Due to be delivered today` | En tournée avec le facteur aujourd'hui. |
| `Delivery attempted` | Personne n'a pu réceptionner le colis ; vérifie ta boîte aux lettres, tes SMS ou tes e-mails. |
| `Due for Redelivery the Next Working Day` | Au Royaume-Uni, Royal Mail retentera la livraison le jour ouvré suivant. |
| `Available for Redelivery or Collection` | En attente de retrait ou d'une nouvelle livraison programmée. |
| `Delivered` | Livré (au Royaume-Uni, `Delivered to Neighbour/Reception by` précise une remise à un voisin ou à l'accueil). |
| `Returned to sender` | Le colis repart vers l'expéditeur. |

Le résumé de Royal Mail affiche parfois `Pending`, notamment à côté d'événements douaniers : Peek retient alors l'étape du dernier scan précis fourni par Royal Mail, comme « À la douane ».

## Qui livre ton colis Royal Mail en France, en Belgique et en Suisse

Un envoi expédié du Royaume-Uni avec un numéro en `…GB` garde ce même identifiant chez la poste de destination :

- **En France :** [La Poste](carrier:la-poste) (et Colissimo pour les colis) distribue 6 jours sur 7 avec le numéro `…GB`.
- **En Belgique :** [bpost](carrier:bpost) assure le suivi dans *Track & Trace* et *My bpost*. Tes préférences permettent de choisir un Point d'enlèvement ou un Distributeur automatique ouvert 24h/24 et 7j/7 (plus de 3 500 points en Belgique).
- **En Suisse :** [La Poste suisse](carrier:swiss-post) dédouane et livre l'envoi. L'invitation à retirer accorde un délai standard de 7 jours en filiale (15 jours pour les envois de l'étranger comme celui-ci), prolongeable gratuitement en ligne ; « Mes envois » et la Post-App permettent aussi une procuration unique ou une seconde distribution gratuite en semaine à la même adresse.

## Frais de douane et TVA sur un colis venant du Royaume-Uni

Depuis le Brexit, la Grande-Bretagne est hors de l'Union européenne (seule l'Irlande du Nord suit les règles européennes sur les marchandises).

### En France et en Belgique (Union européenne)

Toute commande expédiée de Grande-Bretagne est soumise à la TVA dès le premier euro (20 % en France métropolitaine, 21 % en Belgique ; dans les DOM hors Guyane et Mayotte, TVA au-delà de 22 € plus octroi de mer), complétée par deux taxes européennes sur les ventes à distance importées :

- **Droit forfaitaire de 3 € par type de produit (jusqu'à 150 €) :** depuis le 1er juillet 2026, la franchise sous 150 € a disparu ; jusqu'au 1er juillet 2028, toutes les ventes à distance jusqu'à 150 € paient 3 € par type de produit, quel que soit le régime de TVA (IOSS ou non) : deux t-shirts identiques font 3 €, un t-shirt et un pantalon font 6 €. Une origine préférentielle (accord UE–Royaume-Uni) ne s'applique que via une déclaration standard `H1` hors IOSS ; tout envoi sous IOSS paie les 3 €. Au-delà de 150 €, le tarif douanier commun s'applique.
- **Frais européens de 2 € par type de produit :** depuis le 1er novembre 2026, une taxe européenne non remboursable de 2 € par type de produit s'ajoute sur toute vente à distance hors UE, quelle que soit sa valeur (même au-delà de 150 € ou avec origine préférentielle).
- **Frais postaux et paiement :** si tout n'a pas été réglé à la commande, le transporteur perçoit les taxes et ses frais de dossier avant ou à la livraison. En Belgique, [bpost](carrier:bpost) demande le paiement dans *My bpost* ou *Track & Trace* (ou exceptionnellement au guichet) avec 21,50 € de frais administratifs jusqu'à 150 € hors prépaiement ; droits d'importation et frais européens ne sont pas remboursés en cas de retour. Les cadeaux entre particuliers restent exonérés jusqu'à 45 € (hors alcool, tabac et parfum). Voir [colis bloqué en douane](guide:customs).

### En Suisse (hors Union européenne)

La Suisse n'applique ni les 3 € ni les 2 € européens. Pour l'OFDF (BAZG), `Your item is being held in Customs` signifie généralement que [La Poste suisse](carrier:swiss-post) effectue le dédouanement postal :

- **TVA et droits suisses :** la TVA (8,1 %, ou 2,6 % sur livres, alimentation, revues et médicaments, port compris) et les droits au poids brut ne sont pas perçus en dessous de 5 CHF d'impôt (soit jusqu'à 62 CHF à 8,1 % ou 193 CHF à 2,6 %). Cadeaux privés francs jusqu'à 100 CHF (hors alcool et tabac).
- **Tarif de La Poste suisse :** lorsqu'il y a des redevances sur un envoi hors UE comme le Royaume-Uni, la Poste facture 16,00 CHF (hors TVA) plus 3 % de la valeur des marchandises, avec 1,50 CHF de rabais en payant dans « Mes envois » ou la Post-App.

## Si tu vis au Royaume-Uni : carte rouge, livraison et douane britannique

Au Royaume-Uni, Royal Mail livre du lundi au samedi (souvent avant 16h30) et le dimanche dans certaines zones, hors *bank holidays*. Les lettres *2nd Class* passent d'ici décembre 2026 à une tournée un jour ouvré sur deux ; les colis (*Tracked 24* compris) restent livrés six jours sur sept. Depuis le 1er avril 2026, les cibles Ofcom sont de 90 % en *1st Class* à J+1 et 95 % en *2nd Class* sous trois jours (manquées d'avril à juin 2026 selon Citizens Advice).

En cas d'absence, remise en lieu sûr (*safe place*) ou chez un voisin (préférences valables un an dans l'application ; autocollant pour exclure les voisins), sinon carte rouge (*red card*) indiquant le *Customer Service Point* pour [récupérer ton colis](guide:missed-delivery) ou replanifier la livraison sur `royalmail.com`. Pour des taxes britanniques à l'importation, Royal Mail envoie une facture et garde l'envoi environ 3 semaines (cadeaux exonérés de TVA jusqu'à 39 £ ; achats jusqu'à 135 £ généralement taxés en caisse ; remboursement via BOR 286 ; en Irlande du Nord, droits plus 2 € par type de produit en livres sur les biens hors UK/UE dits « à risque »).

## Colis Royal Mail bloqué, en retard ou perdu

- **Aucun suivi initial :** avant le premier scan, le numéro peut rester inconnu, et `Sender preparing item` indique que Royal Mail n'a pas encore le colis. Vérifie l'expédition auprès du vendeur et lis [pourquoi le suivi ne bouge plus](guide:tracking-not-updating).
- **Bloqué sur `Item Leaving the UK` :** plusieurs jours sans scan sont courants avant l'arrivée chez [La Poste](carrier:la-poste), [bpost](carrier:bpost) ou [La Poste suisse](carrier:swiss-post) ; teste le même numéro `…GB` sur leur suivi local.
- **En retard ou perdu :** contacte d'abord la boutique (délai légal de 30 jours dans l'UE). Chez Royal Mail, expéditeur ou destinataire peuvent réclamer avec preuve de dépôt, sauf en *Tracked 24* et *Tracked 48* (expéditeur seul) ; aucun dédommagement de retard sur *Tracked 24/48* ni à l'international. Pour une perte vers l'Europe, attends 20 jours ouvrés après la date prévue (25 ailleurs) et réclame sous 6 mois (au Royaume-Uni : 11 jours ouvrés en *1st Class*, 14 en *2nd Class*, 5 après l'échéance en *Special Delivery*, +1 jour pour un envoi posté entre le premier lundi de décembre et le premier jour ouvré de janvier, sous 80 jours). Plafonds de base : 20 £ (*1st/2nd*), 75 £ (*Tracked 24/48*), 50 £ (international suivi/signé) et 750 £ (*Special Delivery Guaranteed by 1pm*), réponse sous 30 jours (90 à l'international). Voir [colis perdu](guide:lost-parcel) et [colis retourné à l'expéditeur](guide:returned-to-sender).
- **Marqué livré mais absent :** vérifie auprès des proches, voisins ou gardien, puis préviens vite le vendeur. Voir [colis marqué livré mais non reçu](guide:delivered-not-received).

## Contacter Royal Mail et les postes locales

- **Royal Mail (Royaume-Uni) :** 03457 740 740 (tarif national britannique, coûteux depuis l'étranger : passe plutôt par le vendeur), lun.–ven. 8h–18h30, sam. 8h–15h, dim. 9h–14h (heure britannique). Textphone : 03456 000 606 ; Relay UK : 18001 puis 0345 266 0075. Recours au Royaume-Uni sous 30 jours devant le *Postal Review Panel*, puis auprès de POSTRS (020 7520 3766, `postrs@cedr.com`).
- **Localement :** utilise les portails de [La Poste](carrier:la-poste), [bpost](carrier:bpost) (*My bpost*) ou [La Poste suisse](carrier:swiss-post) (« Mes envois ») dès l'arrivée dans ton pays.

> Un SMS ou un e-mail réclame quelques euros pour débloquer ton colis ? Ne clique pas. Cybermalveillance.gouv.fr rappelle que La Poste ne demande jamais de paiement par SMS ou e-mail pour livrer un colis : transfère le SMS au **33700** et signale le mail à Signal Spam ou `phishing@laposte.fr`. En Belgique, bpost ne demande jamais de données personnelles par SMS/e-mail (`abuse@bpost.be`) ; pour un colis venant de hors UE, bpost envoie toutefois une vraie invitation à payer les frais d'importation par e-mail, SMS ou lettre : règle-les dans *My bpost* ou *Track & Trace*. Au Royaume-Uni : SMS au **7726**, mails à `report@phishing.gov.uk`. Voir [reconnaître les SMS d'arnaque au colis](guide:delivery-scam-texts).

## Suivre un colis Royal Mail avec Peek

[Peek](/) reconnaît aussitôt un numéro valide comme `AB482910576GB` (« Transporteur détecté »). Si la clé ne tombe pas juste, Peek affiche « Le chiffre de contrôle de ce numéro ne correspond pas. » : accepte la correction proposée en cas de confusion entre lettre et chiffre (`O`/`0`), ou choisis « Suivre tel quel » puis sélectionne Royal Mail via « Changer ». Pour les références domestiques de 21 et 16 caractères, Peek teste d'abord des services de suivi avant d'ouvrir le navigateur Royal Mail en dernier ; si rien ne répond ou qu'une référence rare à 21 caractères est attribuée à Australia Post, choisis Royal Mail avec « Changer ». Un lien `royalmail.com` avec `trackNumber=` fonctionne pour tout format ; `#/tracking-results/` ne vaut que pour un code S10 en `GB`.

Peek ouvre le suivi Royal Mail dans un navigateur sur son serveur et, vu le filtrage anti-robots de Royal Mail, vérifie l'envoi environ une fois par heure, même le jour de la livraison ou avec un créneau annoncé (un rafraîchissement manuel peut relancer plus tôt). Tu vois le statut, chaque scan sur une carte sans codes postaux, la date et le créneau prévus, l'heure de remise (« Heures dans ton fuseau horaire : … ») et le pays de destination. La remise affiche « Livré » sans nom, adresse ni photo, et les autres scans gardent l'anglais d'origine. Alertes au choix : « Chaque scan », « Étapes importantes » ou « Livraison seulement ».

Sur un envoi `…GB` vers l'étranger, Peek interroge aussi la poste de destination dans plus de 60 pays — dont [La Poste](carrier:la-poste), [bpost](carrier:bpost) et [La Poste suisse](carrier:swiss-post) — et passe sur « Livraison par … » dès qu'elle publie de nouveaux scans, à sa cadence plus fréquente (jusqu'à toutes les 10 minutes, ou jusqu'à toutes les 2 minutes en tournée). Douane, reprogrammation et réclamations se règlent directement auprès des postes concernées.

## Questions fréquentes sur le suivi Royal Mail

### Qui livre un colis Royal Mail en France, en Belgique ou en Suisse ?

La poste nationale prend le relais avec le même numéro en `…GB` : [La Poste](carrier:la-poste) (Colissimo) en France, [bpost](carrier:bpost) en Belgique et [La Poste suisse](carrier:swiss-post) en Suisse. Peek interroge automatiquement cette poste locale et affiche ses scans dès leur parution.

### Quels frais de douane paie-t-on sur un colis venant du Royaume-Uni ?

En France et en Belgique (UE), toute commande expédiée de Grande-Bretagne est soumise à la TVA dès le premier euro (20 % en France métropolitaine, 21 % en Belgique), au droit forfaitaire européen de 3 € par type de produit jusqu'à 150 € (depuis juillet 2026) et aux frais européens non remboursables de 2 € par type de produit (depuis novembre 2026), plus les frais de dossier postaux si non prépayés. En Suisse, TVA (8,1 % ou 2,6 %) et droits ne sont perçus qu'à partir de 5 CHF d'impôt, majorés du tarif postal (16 CHF + 3 %).

### Pourquoi le suivi Royal Mail ne bouge-t-il plus après `Item Leaving the UK` ?

Le suivi n'évolue qu'au scan physique suivant, et plusieurs jours sans mise à jour entre le départ du Royaume-Uni et l'arrivée chez La Poste, bpost ou La Poste suisse sont courants. Peek vérifie Royal Mail environ une fois par heure, puis bascule sur la cadence plus rapide de ta poste locale dès sa prise en charge.

### Que signifie `Customs charges raised` ou `Your item is currently with Customs` ?

`Your item is currently with Customs in the destination country.` signale que ton colis passe la douane à l'arrivée. `Customs charges raised` indique que des taxes ou frais de dossier doivent être réglés avant la remise — règle-les uniquement sur le portail officiel de ta poste locale (ou auprès de Royal Mail sous 3 semaines au Royaume-Uni).

### Un SMS demandant de payer des frais de livraison pour un colis Royal Mail est-il fiable ?

En France, Cybermalveillance.gouv.fr rappelle que La Poste ne demande jamais de paiement par SMS ou e-mail pour livrer un colis (transfère le SMS au **33700**). En Belgique, bpost peut envoyer une invitation à payer des droits hors UE, mais ne paie jamais via un lien inconnu : connecte-toi directement dans *My bpost* ou *Track & Trace* (au Royaume-Uni : transfère au **7726**).

:::sources
- [Citizens Advice : Check Royal Mail collection and delivery times](https://www.citizensadvice.org.uk/consumer/post-and-parcels/post-collection-delivery-times/) – jours et horaires de livraison au Royaume-Uni, lettres 2nd Class un jour ouvré sur deux, Customer Service Points, téléphone et horaires
- [Citizens Advice : Telling delivery companies about your delivery or collection needs](https://www.citizensadvice.org.uk/consumer/post-and-parcels/telling-delivery-companies-about-your-delivery-or-collection-needs/) – lieu sûr, voisin, autocollant, préférences gardées un an, carte rouge et nouvelle livraison
- [Citizens Advice : Claiming compensation from Royal Mail](https://www.citizensadvice.org.uk/consumer/post-and-parcels/claiming-compensation-from-royal-mail/) – qui peut réclamer, preuve de dépôt, délais d'attente et de réclamation au Royaume-Uni et vers l'Europe, plafonds et délais de réponse
- [Citizens Advice : Damaged, lost or delayed post or parcel](https://www.citizensadvice.org.uk/consumer/post-and-parcels/damaged-lost-delayed-post-or-parcel/) – contacter le vendeur d'abord, Postal Review Panel sous 30 jours, médiateur POSTRS
- [Citizens Advice : Response to Royal Mail's Quality of Service results, August 2026](https://www.citizensadvice.org.uk/about-us/media-centre/press-releases/citizens-advice-responds-to-royal-mails-latest-quality-of-service-results-august-2026/) – objectifs Ofcom depuis le 1er avril 2026, résultats d'avril à juin 2026, déploiement du 2nd Class un jour sur deux d'ici décembre 2026
- [GOV.UK : Tax and customs for goods sent from abroad: Tax and duty](https://www.gov.uk/goods-sent-from-abroad/tax-and-duty) – facture Royal Mail, garde d'environ 3 semaines, seuils britanniques de 39 £ et 135 £, formulaire BOR 286, frais de 2 € par type de produit en Irlande du Nord
- [GOV.UK : Avoid and report internet scams and phishing](https://www.gov.uk/report-suspicious-emails-websites-phishing) – signalement au 7726 et à report@phishing.gov.uk
- [UPU : norme S10, Identification of postal items](https://www.upu.int/UPU/media/upu/files/postalSolutions/programmesAndServices/standards/S10-12.pdf) – structure à 13 caractères, chiffre de contrôle et identifiant unique conservé jusqu'à la livraison
- [Commission européenne : Guidance on the temporary EUR 3 customs duty and the Union handling fee](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – droit de 3 € par type de produit quel que soit le régime de TVA, déclaration H1 pour origine préférentielle hors IOSS, frais européens non remboursables de 2 € par type de produit
- [Commission européenne : EU Customs Reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_fr) – réforme douanière de l'UE et suppression de la franchise sous 150 € depuis le 1er juillet 2026
- [Your Europe : Expédition et livraison](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_fr.htm) – délai légal de livraison de 30 jours et calcul du droit de 3 € par type de produit
- [Douane française : Droit de douane forfaitaire de 3 euros et frais de traitement européens](https://www.douane.gouv.fr/fiche/droit-de-douane-forfaitaire-de-3-euros-sur-les-ventes-distance-de-biens-importes) – droit de 3 € depuis le 1er juillet 2026 et frais de traitement de 2 € par type de produit depuis le 1er novembre 2026
- [Douane française : Vous achetez sur internet](https://www.douane.gouv.fr/demarche/vous-achetez-sur-internet) – TVA dès le premier euro en métropole, règles DOM, Brexit et honoraires des transporteurs
- [La Poste Groupe : Services-Courrier-Colis](https://www.lapostegroupe.com/fr/services-courrier-colis) – distribution 6 jours sur 7 du courrier et des colis en France
- [Cybermalveillance.gouv.fr : L'hameçonnage à la livraison de colis](https://www.cybermalveillance.gouv.fr/tous-nos-contenus/actualites/escroqueries-livraison-colis) – absence de demande de paiement par SMS ou e-mail chez La Poste, signalement au 33700, Signal Spam et phishing@laposte.fr
- [bpost : Achats en ligne hors de l'UE](https://www.bpost.be/fr/shopping-hors-ue) – 3 € et 2 € par type de produit, TVA à 21 %, frais administratifs bpost de 21,50 € et non-remboursement en cas de retour
- [bpost : Mon colis à la douane](https://www.bpost.be/fr/recevoir-colis/douane) – paiement préalable dans My bpost ou Track & Trace, cadeaux jusqu'à 45 €
- [bpost : Retirer votre colis dans un Point d'enlèvement](https://www.bpost.be/fr/recevoir-colis/point-enlevement) – plus de 3 500 Points d'enlèvement et Distributeurs automatiques 24h/24 en Belgique
- [bpost : SMS ou e-mail suspect](https://www.bpost.be/fr/faq/jai-recu-un-sms-ou-un-e-mail-suspect-pretendument-de-bpost-que-dois-je-faire) – prévention du phishing et adresse abuse@bpost.be
- [OFDF : Réception de lettres et de paquets](https://www.bazg.admin.ch/fr/reception-de-lettres-et-paquets) – signification du statut douanier, TVA suisse à 8,1 % et 2,6 %, franchise de 5 CHF d'impôt ou de droits
- [La Poste suisse : Dédouanement à l'importation](https://www.post.ch/fr/reception/dedouanement-a-l-importation) – cadeaux exonérés jusqu'à 100 CHF, prix de base de 16 CHF hors UE, rabais en ligne de 1,50 CHF et supplément de 3 %
- [La Poste suisse : Invitation à retirer un envoi](https://www.post.ch/fr/reception/invitation-a-retirer) – retrait sous 7 ou 15 jours, prolongation gratuite, procuration et deuxième distribution
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formats reconnus, statuts Royal Mail, cadence horaire et passage aux postes de destination dans Peek
:::
