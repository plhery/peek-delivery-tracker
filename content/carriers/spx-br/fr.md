---
title: Suivi SPX Express Brésil : commandes Shopee et statuts
description: Suivre un colis SPX Express d'une commande Shopee au Brésil : où trouver le numéro BR, sens des statuts en portugais, retrait Agência Shopee et remboursement.
slug: suivi-spx-express-bresil
published: 2026-11-21
updated: 2026-11-21
---

SPX Express (Shopee Xpress) est le réseau logistique propre de Shopee au **Brésil**. Il collecte les commandes auprès des vendeurs et les livre à domicile ou dans une *Agência Shopee*, un commerce partenaire où l'acheteur retire son colis. Shopee appelle ce mode *Entrega Padrão* (livraison standard) et renvoie vers l'application Shopee au Brésil pour suivre l'envoi, consulter la date prévue et demander un remboursement. Le site [spx.com.br](https://spx.com.br/track) propose aussi un champ de suivi, tout comme la boîte ci-dessus.

## Numéro de suivi SPX Express : où le trouver et format

Dans l'application Shopee au Brésil, ouvre `Eu` > `Minhas Compras`, sélectionne la commande sous `A Caminho` (certains articles d'aide Shopee disent `A Receber`), puis touche `Status de Envio` pour voir chaque mise à jour. Shopee y ajoute automatiquement le numéro de suivi dès qu'il existe, avec la date de livraison estimée.

Voici les formats que Peek reconnaît (caractères inventés) :

| Format | Ce que c'est |
| --- | --- |
| `BR4829105736491` | SPX Express au Brésil : `BR`, 12 chiffres et un chiffre final (15 caractères). |
| `BR482910573649X` | SPX Express au Brésil : `BR`, 12 chiffres et une lettre finale (15 caractères). |
| `BR482910573649XSPXLM48291057` | SPX Express : ce même code de 15 caractères suivi de `SPXLM` et de 8 chiffres, tel qu'imprimé sur certaines étiquettes. |
| `EA482910576BR` | Deux lettres, neuf chiffres et `BR` : un numéro postal des Correios au Brésil, pas de SPX. |

L'identifiant de commande (`ID do pedido`) n'est pas un numéro de suivi, mais le service client de Shopee peut le demander.

Toutes les commandes Shopee au Brésil ne passent pas par SPX. Shopee distingue *Entrega Padrão* (Shopee Xpress), *Entrega Correios* (dont le code fonctionne aussi sur le site des Correios) et *Entrega do Vendedor*, où le vendeur assure lui-même la livraison et met à jour l'état. Pour les achats expédiés depuis l'étranger vers le Brésil, Shopee cite les Correios, avec un code de suivi local dans l'application, ou son partenaire Anjun Courier ; le suivi SPX comporte néanmoins des lignes dédiées à certains colis venus de l'étranger (voir ci-dessous). Pour d'autres structures de codes, consulte [les formats des numéros de suivi](guide:tracking-number-formats) et [comment trouver son numéro de suivi](guide:find-tracking-number).

## Que signifient les statuts de suivi SPX Express

SPX rédige son suivi en portugais (avec quelques intitulés en anglais) ; certaines lignes se terminent par la ville après deux-points.

| Ligne affichée par SPX | Ce que cela veut dire | Peek affiche |
| --- | --- | --- |
| `Pedido em preparação` | Le vendeur prépare la commande ; SPX n'a pas encore le colis. | « Étiquette créée » |
| `Pedido postado …` | Le vendeur a déposé le colis dans un point de dépôt au Brésil (spx.com.br ajoute la ville) ; SPX vient l'y récupérer. | « Pris en charge » |
| `Seu pedido foi coletado` | SPX a collecté le colis auprès du vendeur. | « Pris en charge » |
| `Seu pedido chegou ao centro logístico: …` | Arrivé dans un centre logistique SPX au Brésil. | « En transit » |
| `Seu pedido saiu do centro logístico: …` | Parti d'un centre logistique vers l'étape suivante. | « En transit » |
| `O pedido está em separação no centro logístico, novas atualizações em até 1 dia.` | En cours de tri ; SPX annonce de nouvelles mises à jour sous un jour. | « En transit » |
| `Pedido em trânsito` | En transfert entre centres. Entre États brésiliens ou villes éloignées, Shopee précise que le colis passe parfois plusieurs jours dans le camion de liaison et que la ligne suivante n'apparaît qu'au déchargement et au scan dans le centre de distribution de l'État de destination. | « En transit » |
| `Pedido em rota de entrega para seu endereço, aguarde pelas próximas horas.` | En tournée de livraison vers l'adresse du destinataire pour les prochaines heures. | « En livraison » |
| `Pedido deve ser retirado na agência: …` | Le colis attend d'être retiré par le destinataire dans l'agence indiquée. | « Prêt à être retiré » |
| `Houve um imprevisto durante a entrega do seu pedido …`, `Um imprevisto ocorreu …`, `Insucesso na entrega por endereço incorreto…` | La livraison n'a pas pu aboutir ce jour-là ; SPX prévoit une nouvelle tentative à l'adresse indiquée. | « Tentative de livraison » |
| `Pedido entregue` | Livré. | « Livré » |
| `Pedido será devolvido ao vendedor`, `Pedido será devolvido ao vendedor. Por favor, aguarde.` | Le colis repart vers le vendeur ; la seconde variante invite simplement à patienter. | « Retourné à l’expéditeur » |
| `Pedido devolvido` | Rendu au vendeur. | « Retourné à l’expéditeur » |
| `Pedido extraviado. Estorno será processado automaticamente.` | Colis déclaré égaré ; SPX indique que le remboursement sera traité automatiquement. | « Problème signalé » |
| `Pedido descartado` | Après un échec de livraison, le colis a été mis au rebut : ni livré ni retourné au vendeur. Contacte Shopee. | « Problème signalé » |

Certains envois reçoivent les messages du flux de flotte SPX, qui commencent par `Seu pacote`, comme `Seu pacote foi recebido pelo centro de triagem` (reçu au centre de tri), `Seu pacote foi recebido pelo centro de distribuição` (reçu au centre de distribution local), `Seu pacote está sendo entregue pelo entregador` (avec le hub en tête : en cours de livraison) et `Seu pacote foi entregue` (livré). Peek leur associe les mêmes étapes, par exemple « En livraison ».

**Colis venus de l'étranger vers le Brésil.** Les lignes préfixées par `[China]` correspondent aux opérations d'origine (par exemple `[China]O pedido foi liberado na alfândega de exportação` une fois le dédouanement export terminé). Viennent ensuite `Pedido chegou no Brasil` (arrivé au Brésil), `Pedido está na alfândega de importação` (à la douane d'importation brésilienne), `Pedido em inspeção na alfândega de importação` (en inspection douanière) puis, en anglais, `Import Customs Cleared` (dédouané). Peek affiche « À la douane » pendant les deux étapes douanières, puis à nouveau « En transit » après `Import Customs Cleared`.

En revanche, `O pacote não pôde ser importado, será retornado ou confiscado pela alfândega` signifie que la douane brésilienne a refusé l'importation et que le colis sera renvoyé ou saisi ; Peek affiche « Problème signalé ». Adresse-toi alors à Shopee selon la procédure habituelle de remboursement.

## Livraison SPX au Brésil : tentatives, mot-clé et Agência Shopee

Toutes les règles ci-dessous s'appliquent aux livraisons Shopee au **Brésil** :

**Délais.** Ni l'acheteur ni le vendeur ne peuvent choisir une date ou un créneau horaire de livraison. Shopee indique de 1 à 25 jours ouvrés à compter de la collecte pour les commandes auprès de vendeurs situés au Brésil, et généralement de 30 à 50 jours calendaires pour les commandes internationales. La date estimée figure sur la page de la commande.

**Tentatives.** Les partenaires logistiques de Shopee effectuent 3 tentatives de livraison avant le retour au vendeur, et une livraison non aboutie donne lieu à un remboursement automatique. L'adresse ne peut être modifiée qu'avant la confirmation du paiement, une seule fois ; passé ce stade, Shopee conseille de laisser les tentatives s'épuiser pour recevoir le remboursement. Voir aussi [livraison manquée](guide:missed-delivery).

**Mot-clé (*palavra-chave*).** Certaines commandes au Brésil reçoivent un code aléatoire à communiquer au livreur à la porte ; seule une personne qui le connaît peut réceptionner le colis. Il figure sur la page de commande ainsi que dans l'e-mail et la notification de Shopee. En cas d'absence, tu peux le confier à une personne de confiance, comme un voisin ou le gardien de l'immeuble (*porteiro*). Ne le donne jamais avant d'avoir le colis en main, et refuse tout colis libellé à un autre nom ou à une autre adresse.

**Retrait en Agência Shopee (`Retire Fácil`).** Là où l'option est proposée au Brésil, choisis-la au moment du paiement sous `Opção de Envio` > `Ver mais` > `Retire Fácil` ; il n'est pas possible de revenir ensuite à une livraison à domicile. À l'arrivée du colis, Shopee envoie un code PIN par WhatsApp, par e-mail ou dans l'application, où s'affiche aussi le dernier jour de retrait. Présente le PIN et le numéro de suivi au comptoir ; toute personne disposant des deux peut retirer le colis à ta place. Le délai de garde est de 7 jours ouvrés avant reprise par SPX. Si une agence devient temporairement indisponible, Shopee peut réorienter l'envoi vers l'adresse par défaut du compte. La [carte des points SPX](https://spx.com.br/service-point) permet de localiser les agences en choisissant `Retirar um pacote`.

## Colis internationaux vers le Brésil : Remessa Conforme et fiscalité

Au Brésil, la Receita Federal inscrit Shopee parmi les plateformes certifiées de son programme *Remessa Conforme* (PRC). Sur un site certifié, les droits d'importation et l'ICMS sont réglés dès le paiement de la commande. La Receita Federal recommande de vérifier que ces taxes apparaissent bien sur l'écran de validation : à défaut, elles seront exigées à l'arrivée au Brésil sans le taux réduit du programme.

Selon le barème publié par la Receita Federal au Brésil pour les déclarations d'importation enregistrées à partir du 12 mai 2026 :

- **Droits d'importation (*Imposto de Importação*)** sur un site certifié PRC, pour un envoi destiné à une personne physique : 0 % jusqu'à 50 US$ de valeur en douane (marchandises, transport et assurance compris) ; au-delà de 50 US$, 60 % avec une déduction équivalant à 30 US$ sur l'impôt dû.
- **ICMS** (taxe des États brésiliens) : actuellement de 17 % à 20 % selon l'État brésilien, que le site soit certifié ou non.

Shopee précise qu'un numéro fiscal brésilien (CPF) irrégulier peut bloquer un achat international et que les taxes d'importation ne sont pas remboursables. Si la douane brésilienne retient un colis confié à Anjun Courier, Shopee demande un justificatif de paiement et de valeur d'achat, une copie d'une pièce d'identité brésilienne (RG ou CNH) et le formulaire de preuve de valeur de la Receita Federal (RCV). Pour un colis acheminé par les Correios, vérifie les démarches éventuelles sur leur suivi. La Receita Federal rappelle qu'elle n'envoie jamais de bulletins de paiement (*boletos*), n'appelle pas et n'adresse pas d'e-mails pour réclamer ces taxes. Voir aussi [colis retenu en douane](guide:customs).

## Suivi SPX bloqué, commande en retard ou marquée livrée

**Aucun mouvement pendant plusieurs jours.** Shopee indique que c'est fréquent au Brésil : le suivi ne change que lorsque le colis est scanné dans un nouveau centre, de sorte que les longs trajets routiers entre États, les week-ends et l'attente en hub laissent l'historique immobile. Un itinéraire qui semble s'éloigner fait aussi partie du plan d'acheminement, et plusieurs étapes apparaissent souvent d'un coup lors de la mise en livraison. Plus de détails dans [suivi qui ne bouge plus](guide:tracking-not-updating).

**Numéro non reconnu.** Le colis n'a peut-être pas encore été collecté, ou le code comporte une erreur ou n'est pas encore enregistré. Interroge le vendeur via le chat Shopee ; Shopee précise que cela ne signifie pas que la commande est perdue.

**Retard.** Attends que la date de livraison estimée soit dépassée. Tu peux alors demander un remboursement dans l'application : `Eu` > `A Caminho` > la commande > `Pedido de Reembolso`, motif `Não recebi o pedido`. Le vendeur dispose de 2 jours pour répondre, faute de quoi le remboursement est validé automatiquement. Selon Shopee, la plupart des commandes en retard arrivent dans les 5 jours suivant la date estimée.

**Marqué livré mais non reçu.** Vérifie auprès des voisins, des proches et de la loge ou du *porteiro* de l'immeuble, puis demande un remboursement dans l'application : pour *Entrega Padrão*, dans les 7 jours suivant la livraison. Shopee peut demander une vérification au transporteur (*acareação*), qui peut te contacter par WhatsApp ou e-mail, ou renvoyer le livreur sur place. Voir aussi [colis livré mais non reçu](guide:delivered-not-received).

**Retourné.** Après `Pedido será devolvido ao vendedor`, le colis repart chez le vendeur. En cas d'échec de livraison, Shopee rembourse automatiquement ; voir [colis retourné à l'expéditeur](guide:returned-to-sender).

## Contacter SPX Express : passer par Shopee

Shopee indique que les acheteurs ne peuvent pas joindre directement le transporteur au sujet du suivi ou de la livraison : Shopee traite les demandes dans le cadre de la *Garantia Shopee*. Au Brésil :

- **Chat Shopee :** dans l'application, `Eu` > `Central de Ajuda`, descends jusqu'à `Atendimento ao Cliente` et choisis `Chat Shopee`. Ouvert du lundi au vendredi de 8 h à 22 h et le week-end de 8 h à 20 h (heure de Brasília).
- **Formulaire en ligne :** accessible à toute heure depuis la [page des horaires du service client Shopee](https://help.shopee.com.br/portal/4/article/141507), en munissant ta demande de l'`ID do pedido`. (Le bas de page de `spx.com.br` affiche un numéro WhatsApp, `(11) 2770-1438`, du lundi au samedi hors jours fériés de 8 h à 20 h heure de Brasília, sur le portail dédié aux chauffeurs partenaires et agences.)
- **Comportement inapproprié d'un livreur** (par exemple demande d'argent supplémentaire) : signale-le via `Chat Shopee`.
- **Litige non résolu au Brésil :** Shopee Brasil participe à [consumidor.gov.br](https://www.consumidor.gov.br/pages/principal/), la plateforme publique brésilienne de règlement des litiges, où les entreprises inscrites s'engagent à répondre sous 10 jours (compte `gov.br` de niveau argent ou or requis).

> Shopee indique ne contacter les utilisateurs que par ses canaux officiels (application, comptes vérifiés sur les réseaux sociaux ou e-mails se terminant par `shopee.com`, tandis que le suivi public SPX est sur `spx.com.br`) et rappelle que les frais de port sont toujours réglés dans l'application. Pour reconnaître les faux SMS de livraison, consulte [SMS d'arnaque à la livraison](guide:delivery-scam-texts).

## Suivre un colis SPX Express avec Peek

[Peek](/) identifie immédiatement SPX Express Brazil dès que tu colles un code `BR` à 12 chiffres suivis d'un chiffre ou d'une lettre (avec ou sans suffixe `SPXLM…`), et affiche « Transporteur détecté » avec le bouton « Changer ». Un lien `spx.com.br` (`/track?` ou `/detail/` contenant le code `BR`) rattache aussi aussitôt le colis à SPX, même collé depuis un SMS sans `https://`. Peek n'interroge SPX qu'avec des numéros `BR`, pas avec l'identifiant de commande Shopee.

Peek interroge le même suivi que `spx.com.br` et affiche le statut, les lignes de SPX (en portugais et parfois en anglais), la localité indiquée pour les passages en centres logistiques, hubs, ports ou agences de retrait, ainsi que l'heure exacte de livraison convertie dans le fuseau horaire de ton appareil — sur `peektracker.com` et dans les applications iPhone et Android, le bas de l'historique précise « Heures dans ton fuseau horaire : … ». Il révèle aussi certains jalons masqués sur la page publique de SPX, comme `Seu pedido chegou ao último centro logístico` (arrivée au dernier centre logistique) ou un échec de collecte chez le vendeur (« Étiquette créée ») ; lorsqu'un échec de collecte survient après `Pedido postado …`, le colis reste au point de dépôt (« Pris en charge »), sauf si le motif indique que l'expéditeur n'avait pas préparé la commande (« Étiquette créée »). SPX ne fournit ni date de livraison prévue, ni poids, ni transporteur partenaire : Peek n'en affiche donc pas. Sur un lien partagé, la ligne de livraison affiche uniquement `Delivered` sans le nom de la personne qui a réceptionné le colis. Le mot-clé, le code PIN d'*Agência Shopee* et les demandes de remboursement restent dans l'application Shopee.

Tant que SPX ne connaît pas encore le numéro, Peek affiche « En attente de SPX Express Brazil » et poursuit ses vérifications. Si deux jours plus tard SPX ne reconnaît toujours pas ce code et qu'aucune autre source n'a répondu, Peek affiche « Après deux jours, le transporteur ne connaît toujours pas ce numéro. Vérifie-le, choisis un autre transporteur ou supprime le colis. » et passe alors à un contrôle quotidien, puis hebdomadaire au bout d'une semaine après l'ajout. Si le colis est déjà en route et reste quatre jours sans nouveau scan, Peek propose « Demande de l’aide à SPX Express Brazil », qui ouvre la page des horaires du service client Shopee.

Tant que tu gardes le colis ouvert à l'écran, ou le jour de sa livraison avec les notifications activées, Peek vérifie les mises à jour jusqu'à toutes les 10 minutes — et jusqu'à toutes les 2 minutes à partir de 6 h (heure locale) le jour où SPX scanne le colis en tournée de livraison, tant que le suivi direct de SPX répond (via des services de suivi seuls : toutes les heures, toutes les 3 heures après une journée calme, et jusqu'à toutes les 15 minutes le jour de la livraison si le colis est ouvert ou si les notifications sont activées) — ; le reste du temps, ainsi qu'après deux jours sans nouvelle étape (ou 12 heures sur « Étiquette créée » ou « Pris en charge »), le contrôle est horaire. Tu peux choisir entre « Chaque scan », « Étapes importantes » et « Livraison seulement ». Peek ne peut contacter ni SPX ni Shopee à ta place.

## Questions fréquentes sur le suivi SPX Express Brésil

### SPX Express est-il le transporteur de Shopee ?

Oui : Shopee présente SPX Express comme sa propre logistique officielle au Brésil. Le site `spx.com.br` est exploité par SPX Express Brasil Ltda., qui annonce plus de 100 hubs logistiques et 11 centres de distribution dans le pays.

### Comment suivre un colis SPX Express ?

Dans l'application Shopee au Brésil (`Eu` > `Minhas Compras` > la commande > `Status de Envio`), en collant le code `BR` dans Peek ou via le champ de recherche de `spx.com.br`.

### Que veut dire Pedido em rota de entrega para seu endereço ?

Le colis est en cours de livraison vers ton adresse au Brésil et SPX invite à patienter durant les prochaines heures. Si la commande comporte un mot-clé (*palavra-chave*), garde-le prêt pour le livreur.

### Quel est le délai de livraison de SPX Express au Brésil ?

Shopee annonce de 1 à 25 jours ouvrés à partir de la collecte pour les envois de vendeurs situés au Brésil, et généralement de 30 à 50 jours calendaires pour les commandes internationales. La date estimée propre à ta commande s'affiche dans l'application Shopee.

### Peut-on contacter directement SPX Express ?

Shopee indique que non pour le suivi ou la livraison d'une commande : tout passe par `Chat Shopee` dans l'application, du lundi au vendredi de 8 h à 22 h et le week-end de 8 h à 20 h, heure de Brasília, ou par son formulaire en ligne.

:::sources
- [SPX Express : Page d'accueil](https://spx.com.br/br) – logistique intégrée de Shopee au Brésil, plus de 100 hubs et 11 centres de distribution, SPX Express Brasil Ltda., WhatsApp partenaires/agences
- [SPX Express : Suivi](https://spx.com.br/track) – page publique de recherche SPX au Brésil
- [SPX Express : Sobre Nós](https://spx.com.br/br/help-center/about-us.html) – suivi dans l'application Shopee et réseau national
- [SPX Express : Encontre uma Agência Shopee](https://spx.com.br/service-point) – carte des agences et option Retirar um pacote
- [Shopee : O que é a SPX Express?](https://help.shopee.com.br/portal/4/article/197188) – logistique propre et officielle de Shopee au Brésil
- [Shopee : O que é Shopee Xpress (SPX)?](https://help.shopee.com.br/portal/4/article/76163) – collecte, centres de distribution et partenaires
- [Shopee : Posso entrar em contato com os parceiros logísticos da Shopee?](https://help.shopee.com.br/portal/4/article/76264) – modes de livraison, absence de contact direct transporteur et 3 tentatives
- [Shopee : Como faço para verificar o status do meu pedido?](https://help.shopee.com.br/portal/4/article/76323) – suivi des envois Correios
- [Shopee : Quais são as opções de envio disponíveis na Shopee?](https://help.shopee.com.br/portal/4/article/76335) – partage automatique du code de suivi
- [Shopee : Como eu acho a data prevista de entrega para o meu pedido?](https://help.shopee.com.br/portal/4/article/81887) – date estimée et chemin Status de Envio
- [Shopee : Como encontro o ID do pedido da minha compra?](https://help.shopee.com.br/portal/4/article/76194) – identifiant de commande ID do pedido
- [Shopee : O que é logística do vendedor?](https://help.shopee.com.br/portal/4/article/132039) – livraison assurée par le vendeur
- [Shopee : Como minha encomenda do exterior será entregue quando chegar ao Brasil?](https://help.shopee.com.br/portal/4/article/76363) – remise aux Correios pour des achats internationaux
- [Shopee : Por que meu pedido está parado na alfândega do Brasil?](https://help.shopee.com.br/portal/4/article/153683) – Correios ou Anjun Courier, pièces justificatives et formulaire RCV
- [Shopee : Quando receberei meu pedido?](https://help.shopee.com.br/portal/4/article/76349) – 1 à 25 jours ouvrés au Brésil et 30 à 50 jours calendaires à l'international
- [Shopee : Posso agendar a data e hora de entrega do meu pedido?](https://help.shopee.com.br/portal/4/article/76333) – pas de créneau programmable, tentatives et remboursement automatique
- [Shopee : Posso alterar meu endereço de entrega depois que meu pedido for feito?](https://help.shopee.com.br/portal/4/article/76279) – modification unique avant confirmation du paiement
- [Shopee : O que é a palavra-chave no meu pedido?](https://help.shopee.com.br/portal/4/article/127204) – fonctionnement du mot-clé à la livraison
- [Shopee : O que fazer quando não estiver em casa para informar a palavra-chave?](https://help.shopee.com.br/portal/4/article/127206) – remise du mot-clé à un tiers de confiance
- [Shopee : O que fazer se minha palavra-chave não funcionar corretamente?](https://help.shopee.com.br/portal/4/article/127205) – ne pas donner le code avant remise et refuser un colis mal adressé
- [Shopee : Retirada de pacotes na Agência Shopee](https://help.shopee.com.br/portal/4/article/164642) – choix Retire Fácil, notifications, code PIN et date limite
- [Shopee : Sobre a Agência Shopee](https://help.shopee.com.br/portal/4/article/164639) – retrait avec PIN et numéro de suivi, garde de 7 jours ouvrés et réorientation éventuelle
- [Shopee : Meu pedido está sem atualização no rastreamento](https://help.shopee.com.br/portal/4/article/223973) – trajets inter-États en camion et arrivée sous 5 jours après la date estimée
- [Shopee : Por que meu pedido está parado ou mudou de rota?](https://help.shopee.com.br/portal/4/article/159363) – changements d'itinéraire entre hubs
- [Shopee : Por que meu código de rastreio não está funcionando?](https://help.shopee.com.br/portal/4/article/76369) – causes d'un code encore inactif
- [Shopee : O que devo fazer se não receber meu pedido até a data prevista de entrega?](https://help.shopee.com.br/portal/4/article/76321) – demande de remboursement et délai de réponse vendeur de 2 jours
- [Shopee : O que devo fazer se não receber um pedido, mas o status está como Entregue?](https://help.shopee.com.br/portal/4/article/76207) – vérifications de voisinage et enquête acareação
- [Shopee : Como faço para solicitar devolução/reembolso?](https://help.shopee.com.br/portal/4/article/76204) – délai de 7 jours après la livraison en Entrega Padrão
- [Shopee : Como entro em contato com o Atendimento ao Cliente da Shopee?](https://help.shopee.com.br/portal/4/article/78354) – accès au Chat Shopee dans l'application
- [Shopee : Qual horário do Atendimento ao Cliente da Shopee?](https://help.shopee.com.br/portal/4/article/141507) – horaires de Brasília et formulaire en ligne
- [Shopee : O entregador do meu pedido teve uma má conduta](https://help.shopee.com.br/portal/4/article/224511) – signalement d'un livreur via le chat
- [Shopee : Como faço para detectar um golpe?](https://help.shopee.com.br/portal/4/article/76330) – canaux officiels shopee.com et paiement du port uniquement dans l'application
- [Shopee : Por que não consigo concluir minha compra internacional?](https://help.shopee.com.br/portal/4/article/141561) – blocage lié à un CPF irrégulier
- [Shopee : Como solicitar devolução/reembolso ou realizar o cancelamento da minha compra internacional?](https://help.shopee.com.br/portal/4/article/134867) – procédure internationale et non-remboursement des taxes d'importation
- [Receita Federal : Tributação](https://www.gov.br/receitafederal/pt-br/assuntos/aduana-e-comercio-exterior/manuais/remessas-postal-e-expressa/topicos/tributacao) – taux PRC dès le 12 mai 2026, ICMS de 17 % à 20 % et alerte contre les faux boletos
- [Receita Federal : Em que momento vou pagar os impostos?](https://www.gov.br/receitafederal/pt-br/assuntos/aduana-e-comercio-exterior/manuais/remessas-postal-e-expressa/preciso-pagar-impostos-nas-compras-internacionais/em-que-momento-pagarei-o-imposto) – paiement des taxes au moment de la commande
- [Receita Federal : Empresas certificadas no Programa Remessa Conforme](https://www.gov.br/receitafederal/pt-br/assuntos/aduana-e-comercio-exterior/manuais/remessas-postal-e-expressa/empresas-certificadas-no-programa-remessa-conforme-prc) – certification de Shopee au programme PRC
- [Consumidor.gov.br : Início](https://www.consumidor.gov.br/pages/principal/) – réponse sous 10 jours et compte gov.br argent ou or
- [Consumidor.gov.br : Empresas participantes](https://www.consumidor.gov.br/pages/principal/empresas-participantes) – participation de Shopee Brasil
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – reconnaissance des codes BR et liens spx.com.br, statuts et flux de flotte SPX, jalons masqués et confidentialité
:::
