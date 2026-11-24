---
title: Suivi Memphis Courier : codes M-MIA, douane ANVISA et aide
description: Suis tes importations Memphis Courier des États-Unis vers le Brésil : codes M-MIA, statuts aéroport GRU et ANVISA, règles CPF et contacts officiels.
slug: suivi-memphis-courier
published: 2026-11-24
updated: 2026-11-24
---

Memphis Courier est une société de logistique 100 % brésilienne forte de plus de 25 ans d’expérience dans les envois express et formels à l’import comme à l’export. Depuis son terminal de messagerie à l’aéroport international de Guarulhos (GRU) et son bureau de São Paulo, elle assure le transport aérien, le dédouanement auprès de la Receita Federal, la validation réglementaire auprès de l’ANVISA (y compris l’importation de cannabis médical au titre des résolutions RDC 660 et RDC 327 pour les médicaments fabriqués aux États-Unis, en Europe, en Colombie et en Uruguay) et la livraison dans tout le Brésil.

Son suivi accompagne le colis jusqu’à l’aéroport de Guarulhos, à travers le contrôle de la Receita Federal et l’examen ANVISA, puis jusqu’à la livraison finale.

## Format des numéros de suivi Memphis Courier

Les numéros de suivi Memphis Courier suivent un format alphanumérique reconnaissable à 11 caractères :

- **`M` + 7 chiffres + `MIA`** (11 caractères au total, comme `M4829173MIA`) — le schéma que Peek reconnaît comme appartenant à Memphis Courier.

Peek accepte les codes Memphis Courier de 6 à 30 lettres et chiffres ; il supprime les espaces et passe les lettres en majuscules. Le portail de suivi Memphis Courier (`clients.memphiscourier.com.br`) permet la consultation sans compte client. À partir de la forme `M` + 7 chiffres + `MIA`, Peek interroge uniquement Memphis Courier et affiche « Memphis Courier a ce colis. » dès qu’il répond. Pour d’autres formats courants sur les importations vers le Brésil, consulte le [guide des formats de numéros de suivi](guide:tracking-number-formats).

## Statuts de suivi Memphis Courier

Le service de suivi Memphis Courier renvoie des codes de scan à 3 lettres accompagnés de descriptions en portugais et d’une colonne lieu (État ou pays). La barre d’étapes de sa page indique Criado (`FLT`), douane (`BRP`), Liberado et Entregue ; les scans correspondant aux deux dernières étapes (`CLA` et `DLV`) ne sont connus que par ces libellés.

| Code | Libellé Memphis Courier | Ce que cela signifie |
| --- | --- | --- |
| `FLT` | Seu pedido foi criado com sucesso | L’ordre d’expédition a été enregistré dans le système Memphis Courier avant le départ aérien. |
| `CHK` | Entrada registrada no Centro de Distribuição | Enregistré à l’entrée d’un centre de distribution Memphis Courier. |
| `ENO` | Encomenda em trânsito | En mouvement dans le réseau Memphis Courier. |
| `TRA` | Encomenda em transferência | En cours de transfert entre sites de traitement. |
| `DEP` | A encomenda saiu da origem e está a caminho do aeroporto de destino (Guarulhos - SP) | Parti du hub d’origine à bord d’un vol vers l’aéroport international de Guarulhos (GRU). |
| `ARR` | Seu pedido chegou ao aeroporto de destino (Guarulhos - SP) | Arrivé à l’aéroport de Guarulhos à São Paulo. |
| `AAZ` | Pedido recebido pela GRU Airport | Reçu par le terminal fret de l’aéroport GRU avant l’entrée formelle en douane. |
| `BRP` | Processo de desembaraço iniciado | Le dédouanement brésilien a commencé. |
| `HOC` | Encomenda sendo inspecionada pela Alfândega | En cours d’inspection par la Receita Federal (douanes brésiliennes). |
| `ANV` | Em inspeção pela ANVISA | En cours d’inspection réglementaire par l’ANVISA, l’agence sanitaire brésilienne, durant le dédouanement. |
| `CLA` | Liberado (barre d’étapes) | Les douanes et les agences réglementaires requises ont libéré l’envoi pour la livraison nationale. |
| `DLV` | Entregue (barre d’étapes) | Livré à l’adresse du destinataire au Brésil. |

## Douane, validation du CPF et contrôle ANVISA

Chaque importation express personnelle au Brésil exige le CPF (*Cadastro de Pessoas Físicas*) du destinataire pour le dédouanement par la Receita Federal. Comme l’indique Memphis Courier sur sa [page de services additionnels](https://memphiscourier.com.br/servicos-adicionais/), de nombreuses boutiques étrangères ne proposent pas de champ CPF à la commande ; dans ce cas, le service client Memphis Courier contacte le destinataire pour valider son CPF afin d’éviter un blocage en douane.

Pour le cannabis médical et les autres médicaments acheminés par Memphis Courier depuis les États-Unis, l’Europe, la Colombie ou l’Uruguay, le colis peut passer à la fois par un contrôle de la Receita Federal (`HOC`) et par une inspection ANVISA (`ANV`) à Guarulhos avant l’apparition de `CLA` (libéré). Si ton envoi reste sur `BRP`, `HOC` ou `ANV` pendant plusieurs jours ouvrables sans évolution, vérifie tes e-mails et WhatsApp : Memphis Courier informe ses clients par e-mail, WhatsApp, chat ou téléphone et peut t’avoir demandé une confirmation de CPF ou un justificatif. Pour en savoir plus sur les formalités d’importation, consulte le [guide douane](guide:customs) et [quand le suivi ne se met plus à jour](guide:tracking-not-updating).

Une fois le colis libéré par la douane (`CLA`), la distribution nationale couvre tous les États brésiliens. Si un autre transporteur livre une commande distincte au Brésil, compare le suivi avec [Anjun Express](carrier:anjun), [Jadlog](carrier:jadlog) ou [J&T Express Brazil](carrier:j-and-t-br).

## Suivre Memphis Courier avec Peek

Colle un code `M...MIA` dans Peek et Peek l’associe automatiquement à Memphis Courier dès que le transporteur répond. Si tu dois sélectionner ou modifier le transporteur manuellement, choisis **Changer** lors de l’ajout du colis ou **Changer de transporteur** dans la vue de suivi.

Peek interroge directement le service public de suivi Memphis Courier via l’[adaptateur Memphis Courier open source de universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/memphis-courier). Il lit le code, le libellé portugais et le lieu de chaque scan, ainsi que le poids total en kilogrammes lorsqu’il figure au dossier. Memphis Courier fournit l’heure locale de chaque scan et n’indique un décalage horaire que lorsque l’heure se termine par celui-ci. Peek conserve ce décalage ; sinon il date le scan sur le fuseau horaire de son lieu (heure de New York pour `Estados Unidos`, heure de São Paulo pour `SP` ou `Brasil`), un scan sans lieu sur celui du scan voisin, sinon sur l’heure de São Paulo, et affiche chaque scan à l’heure de ton appareil. Les éléments propres au portail client comme les remarques internes, le nombre de colis, les codes postaux, la destination et les documents douaniers joints restent sur le site de Memphis Courier.

Peek regroupe les 12 codes de scan Memphis Courier en étapes claires : **Étiquette créée** pour `FLT`, **En transit** de `CHK` à `AAZ` puis de nouveau après dédouanement à `CLA` (la grille Memphis Courier passe directement de la création de commande au transit en centre de distribution sans code distinct **Pris en charge**), **À la douane** pour `BRP`, `HOC` et `ANV`, et **Livré** dès l’apparition de `DLV`.

Tant que le colis reste ouvert dans Peek, ou le jour de la livraison si les notifications sont activées, Peek vérifie jusqu’à toutes les 10 minutes tant que le suivi Memphis Courier répond. Sinon, Peek vérifie toutes les heures. Le suivi Memphis Courier ne comportant pas d’étape de tournée de livraison, Peek ne passe jamais à ses vérifications toutes les 2 minutes le jour de la livraison. Après 12 heures avec uniquement « Étiquette créée » ou « Pris en charge », ou deux jours sans nouvelles, Peek passe à une vérification par heure quelle que soit ta manière de suivre le colis, et toutes les 6 heures après sept jours à « Étiquette créée ». Lorsque le suivi Memphis Courier ne répond pas et que Peek suit le colis uniquement via des services de suivi, il vérifie toutes les heures, toutes les 3 heures après une journée calme, et jusqu’à toutes les 15 minutes le jour de la livraison tant que le colis est ouvert ou que les notifications sont activées.

Lorsque le scan le plus récent date de quatre jours et que le colis est toujours sur « Étiquette créée », « Pris en charge » ou « En transit », Peek le signale avec le jour de ce scan, par exemple « Aucune mise à jour depuis lun. 12 oct. ». La fiche du colis indique alors « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger Memphis Courier. », avec un lien « Demande de l’aide à Memphis Courier » vers la page Fale Conosco de Memphis Courier. Après trois semaines sans nouveau scan, elle affiche « Rien de nouveau depuis plus de trois semaines. Si le colis n’est toujours pas arrivé, contacte le vendeur. Peek continue d’interroger Memphis Courier. »

Choisis **Chaque scan**, **Étapes importantes** ou **Livraison seulement** dans les préférences d’alertes de Peek pour décider à quelle fréquence recevoir des notifications.

## Contacter Memphis Courier

Memphis Courier publie ses canaux officiels sur son site et précise explicitement dans son avis officiel qu’il ne considère pas les plateformes tierces de réclamation comme Reclame Aqui comme un canal officiel d’assistance ou de protocole.

- **Page de contact officielle :** [Fale Conosco](https://memphiscourier.com.br/fale-conosco/) (formulaire de contact)
- **Téléphone / service client :** +55 11 94264-7550 (indiqué à la fois pour le Terminal Courier GRU et le bureau de São Paulo)
- **E-mail :** `atendimento@memphiscourier.com.br`, `atendimento1@memphiscourier.com.br` et `info@memphiscourier.com.br`
- **Terminal Courier de Guarulhos :** Aeroporto Internacional de Guarulhos — Terminal Courier, CEP 07190-100, São Paulo, Brésil
- **Bureau de São Paulo :** Av. Marques de São Vicente, 230, cj 1216 — Barra Funda, CEP 01139-000, São Paulo, Brésil ([Quem Somos](https://memphiscourier.com.br/quem-somos/))

Prépare ton numéro de suivi `M...MIA` et le CPF du destinataire avant de contacter l’assistance afin que l’équipe puisse retrouver immédiatement ton dossier douanier.

## Questions fréquentes

### Que signifie `ANV` (« Em inspeção pela ANVISA ») sur le suivi Memphis Courier ?

`ANV` signifie que l’agence brésilienne de réglementation sanitaire (ANVISA) inspecte l’envoi dans le cadre du dédouanement import. Memphis Courier dédouane les importations de cannabis médical (RDC 660 / RDC 327) avec l’ANVISA ; le colis reste à la douane jusqu’à `CLA` (libéré).

### Pourquoi Memphis Courier m’a-t-il contacté pour demander mon CPF ?

La Receita Federal exige le CPF du destinataire pour chaque importation express personnelle au Brésil. Lorsqu’une boutique étrangère ne collecte pas le CPF à la commande, le service client Memphis Courier te contacte pour le valider avant la poursuite du dédouanement.

### Pourquoi n’y a-t-il aucun scan entre `DEP` et `ARR` ?

`DEP` enregistre le départ de l’origine vers l’aéroport de Guarulhos et `ARR` l’arrivée sur place. La liste de codes Memphis Courier ne comporte pas de scan pendant le vol lui-même : le scan suivant est donc `ARR` ou `AAZ` au terminal fret.

### Memphis Courier répond-il aux demandes publiées sur Reclame Aqui ?

Non. L’avis officiel du site Memphis Courier précise qu’il traite les réclamations, questions et demandes exclusivement par les canaux officiels publiés sur [memphiscourier.com.br](https://memphiscourier.com.br/) (téléphone, e-mail et formulaire de contact) afin de préserver la traçabilité et la confidentialité des données.

:::sources
- [Memphis Courier](https://memphiscourier.com.br/)
- [Memphis Courier — Quem Somos](https://memphiscourier.com.br/quem-somos/)
- [Memphis Courier — Serviços Adicionais](https://memphiscourier.com.br/servicos-adicionais/)
- [Memphis Courier — Fale Conosco](https://memphiscourier.com.br/fale-conosco/)
- [adaptateur Memphis Courier de universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/memphis-courier)
:::
