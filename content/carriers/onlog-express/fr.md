---
title: Suivi Onlog Express : codes AA, statuts Brésil et aide
description: Suis un colis Onlog Express au Brésil : codes AA à 12 caractères, statuts de centre de distribution, alerte fraude et support à São Paulo.
slug: suivi-onlog-express
published: 2026-11-24
updated: 2026-11-24
---

**Onlog Express** (*Onlog Franqueadora*) est un réseau brésilien de livraison de colis et de logistique basé à São Paulo, fort de plus de 20 ans d’expérience en partenariat logistique et de plus de 18 millions de colis livrés à travers le Brésil ([onlogexpress.com.br](https://onlogexpress.com.br/)). Reliant e-commerçants et entreprises à des opérateurs nationaux de livraison sur des niveaux allant de la livraison urgente à la minute ou le jour même (*Entrega em Minutos*, *Entrega no Dia*) au prioritaire (*Expresso D1*) et à l’économique (*Econômico D2*), en plus du fulfillment et du fret international, Onlog propose un suivi public par code d’objet ou CPF/CNPJ sur [conecta.log.br/rastreio.php](https://conecta.log.br/rastreio.php). Tu peux vérifier un envoi sur ce site ou coller ton code `AA` à 12 caractères dans le champ ci-dessus.

## Numéros de suivi Onlog Express (format `AA`)

Le suivi public invité d’Onlog Express utilise un code d’objet à 12 caractères commençant par `AA` suivi de 10 chiffres :

| Format (inventé) | De quoi il s’agit |
| --- | --- |
| `AA4829173605` | Code d’objet Onlog Express à 12 caractères (`AA` + 10 chiffres). |
| `AA7391846208` | Autre référence colis `AA` Onlog Express à 12 caractères. |

Vérifie l’e-mail d’expédition du vendeur ou le lien de suivi `onlog.app.br/rastreio/...` pour trouver la référence `AA`. Si tu n’as pas le code, le portail web d’Onlog sur [conecta.log.br/rastreio.php](https://conecta.log.br/rastreio.php) permet aussi aux destinataires de rechercher avec leur CPF ou CNPJ brésilien (avec une option de recherche distincte pour les objets antérieurs au 01/01/2025). Voir les [formats de numéros de suivi](guide:tracking-number-formats).

## Ce que signifient les statuts de suivi Onlog Express

Onlog Express publie des descriptions de scans en portugais depuis la création d’étiquette et la collecte jusqu’aux transferts en centre de distribution et à la signature du destinataire :

| Onlog Express indique (PT-BR) | Ce que cela signifie pour toi | Peek affiche |
| --- | --- | --- |
| `OBJETO CRIADO`, `ETIQUETA EMITIDA` | Envoi créé et étiquette émise électroniquement avant la collecte physique. | « Étiquette créée » |
| `OBJETO POSTADO`, `COLETA DE ENCOMENDA` | Colis déposé dans une unité Onlog ou collecté chez l’expéditeur. | « Pris en charge » |
| `OBJETO EM TRANSFERÊNCIA`, `ENTRADA NO CENTRO DE DISTRIBUIÇÃO`, `ENCAMINHADO PARA O OPERADOR LOGÍSTICO`, `BIPE DE EXPEDIÇÃO`, `BIPE DE RECEBIMENTO` | Reçu dans un centre de distribution, acheminé entre installations ou transmis à l’opérateur logistique régional. | « En transit » |
| `OBJETO SAIU PARA ENTREGA AO DESTINATÁRIO`, `BIPE DE SAÍDA PARA ENTREGA` | En tournée avec le livreur local pour la livraison finale à domicile aujourd’hui. | « En livraison » |
| `OBJETO ENTREGUE AO DESTINATÁRIO`, `ASSINATURA DE ENCOMENDA` | Livré et signé à l’adresse de destination. | « Livré » |

`ENCAMINHADO PARA O OPERADOR LOGÍSTICO` signifie que le colis a été transmis à l’opérateur logistique chargé du tronçon suivant.

## Services de livraison et alerte officielle contre les frais frauduleux

Selon [onlogexpress.com.br](https://onlogexpress.com.br/) :

- **Niveaux de service :** **Entrega em Minutos** (urgent), **Entrega no Dia** (le jour même), **Expresso D1** (transit prioritaire) et **Econômico D2** (économique), les délais exacts et la disponibilité dépendant des codes postaux (CEP) d’origine et de destination.
- **Avis antifraude important :** Onlog publie un avertissement explicite sur sa page d’accueil indiquant qu’**Onlog ne demande aucun paiement de frais par e-mail, WhatsApp ou téléphone**. Ne paie jamais une taxe ou des frais de déblocage réclamés par message non sollicité au nom d’Onlog. Voir les [arnaques aux SMS de livraison](guide:delivery-scam-texts).

## Colis Onlog Express retardé, sans nouvelles ou manquant

- **Silence après `ENCAMINHADO PARA O OPERADOR LOGÍSTICO` :** les scans s’espacent souvent entre hubs régionaux ou lors du transfert vers le partenaire local du dernier kilomètre entre États brésiliens. Voir [pourquoi le suivi ne se met plus à jour](guide:tracking-not-updating).
- **Livré sans reçu ou colis manquant :** vérifie d’abord auprès de la conciergerie (*portaria*), de ta famille et de tes voisins, puis contacte la boutique en ligne où tu as commandé ainsi que le support Onlog à São Paulo aux heures ouvrables avec ton numéro `AA`. Voir [colis indiqué livré mais non reçu](guide:delivered-not-received), [colis perdu](guide:lost-parcel), [colis endommagé](guide:damaged-parcel) et [colis retourné à l’expéditeur](guide:returned-to-sender).

## Service client Onlog Express et contacts à São Paulo

Canaux officiels publiés sur [onlogexpress.com.br](https://onlogexpress.com.br/) :

- **Téléphone (São Paulo) :** `(11) 5662-8108` et `(11) 5663-3829`.
- **E-mail :** `atendimento@onlogexpress.com.br`.
- **Horaires d’assistance :** du lundi au vendredi, de `08:00` à `18:00` BRT (fermé samedi et dimanche).
- **Siège (*Matriz São Paulo*) :** Rua Estados Unidos, 2031, Jardim América, São Paulo – SP, Brésil.
- **Suivi en ligne :** [conecta.log.br/rastreio.php](https://conecta.log.br/rastreio.php).

> Garde à l’esprit l’avertissement officiel d’Onlog : Onlog ne facture jamais de frais de déblocage par e-mail, WhatsApp ou téléphone. Ignore et signale tout message réclamant un paiement PIX ou par carte pour débloquer un colis. Voir les [arnaques aux SMS de livraison](guide:delivery-scam-texts).

## Suivre un colis Onlog Express avec Peek

Lorsque tu saisis un code `AA` à 12 caractères (`AA4829173605` ou `AA7391846208`, inventés), [Peek](/) interroge [Colis Privé](carrier:colis-prive), [Aramex Australie](carrier:aramex-au) et [Aramex Nouvelle-Zélande](carrier:aramex-nz), puis les services de suivi, puis Onlog Express dont il ouvre le suivi dans un navigateur, et affiche « Onlog Express a ce colis. » dès qu’Onlog le confirme. Peek reconnaît aussi comme Onlog les liens collés `onlog.app.br/rastreio/AA...`. Si tu dois choisir Onlog toi-même, appuie sur « Changer » à l’ajout ou « Changer de transporteur » sur la fiche du colis (rechercher « ONLOG » ou « Onlog Franqueadora » le trouve aussi).

Peek lit le suivi d’Onlog dans un navigateur et affiche l’étape (« Étiquette créée », « Pris en charge », « En transit », « En livraison », « Livré »), le libellé portugais du scan et le lieu du scan. Onlog donne chaque scan à la minute près sans décalage horaire : Peek le date sur le fuseau horaire de son lieu (São Paulo pour un scan à São Paulo, Manaus pour Manaus), un scan sans lieu sur le fuseau du scan voisin ou sinon sur celui de São Paulo, et affiche tout à l’heure de ton appareil. Le suivi Onlog ne donne ni jour de livraison estimé, ni poids, ni codes d’événement, ni heure de livraison.

Tant que le colis reste ouvert dans Peek, ou le jour de la livraison si les notifications sont activées, Peek vérifie jusqu’à toutes les 30 minutes — l’intervalle minimal autorisé par Onlog entre deux contrôles — et attend une heure après un contrôle échoué ; il n’effectue pas de vérifications toutes les 2 minutes le jour de la livraison. Sinon, Peek vérifie toutes les heures. Après 12 heures avec uniquement « Étiquette créée » ou « Pris en charge », ou deux jours sans nouvelles, Peek passe à une vérification par heure quelle que soit ta manière de suivre le colis, et toutes les 6 heures après sept jours à « Étiquette créée ». Lorsque le suivi Onlog ne répond pas et que Peek suit le colis uniquement via des services de suivi, il vérifie toutes les heures, toutes les 3 heures après une journée calme, et jusqu’à toutes les 30 minutes le jour de la livraison tant que le colis est ouvert ou que les notifications sont activées.

Lorsque le scan le plus récent date de quatre jours et que le colis est toujours sur « Étiquette créée », « Pris en charge » ou « En transit », Peek le signale avec le jour de ce scan, par exemple « Aucune mise à jour depuis lun. 12 oct. ». La fiche du colis indique alors « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger Onlog Express. » Après trois semaines sans nouveau scan, elle affiche « Rien de nouveau depuis plus de trois semaines. Si le colis n’est toujours pas arrivé, contacte le vendeur. Peek continue d’interroger Onlog Express. »

Tu peux choisir des alertes pour « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Peek suit uniquement les mises à jour de suivi et ne peut ni rechercher un colis par CPF/CNPJ, ni modifier une adresse de livraison, ni contacter Onlog Express à ta place.

## Questions sur le suivi Onlog Express

### À quoi ressemble un numéro de suivi Onlog Express ?

Les codes de suivi Onlog Express commencent par `AA` suivis de 10 chiffres (12 caractères au total, par exemple `AA4829173605`, inventé).

### Puis-je suivre un colis Onlog Express avec mon CPF ou CNPJ ?

Oui : sur le site d’Onlog à [conecta.log.br/rastreio.php](https://conecta.log.br/rastreio.php), tu peux passer à l’onglet **POR CPF/CNPJ** si tu n’as pas le code `AA`. Peek utilise uniquement le code d’objet `AA`.

### J’ai reçu un message WhatsApp ou un e-mail demandant des frais de la part d’Onlog — est-ce authentique ?

Non. Onlog déclare explicitement sur [onlogexpress.com.br](https://onlogexpress.com.br/) qu’il ne demande **jamais** de paiement de frais par e-mail, WhatsApp ou téléphone. Ne paie pas.

### Comment contacter le service client Onlog Express ?

Appelle le `(11) 5662-8108` ou le `(11) 5663-3829`, ou écris à `atendimento@onlogexpress.com.br` du lundi au vendredi de 08 h 00 à 18 h 00 BRT.

:::sources
- [Onlog Express : site officiel](https://onlogexpress.com.br/) – niveaux de service (*Entrega em Minutos*, *Entrega no Dia*, *Expresso D1*, *Econômico D2*), 18M+ colis, 20+ ans, avis anti-arnaque et contacts du siège à São Paulo
- [Onlog Express : suivi officiel (Conecta)](https://conecta.log.br/rastreio.php) – suivi public par code d’objet ou CPF/CNPJ
- [Universal Parcel Scraper : onlog-express](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/onlog-express) – format `AA\d{10}`, règle de lien `onlog.app.br/rastreio/`, table de 13 libellés portugais, consultation navigateur toutes les 30 minutes au plus (60 après échec) et horloges des scans
:::
