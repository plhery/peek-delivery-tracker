---
title: Suivi CTT Portugal : numéros, statuts, relais et douane
description: Suivez un colis CTT Portugal depuis la France, la Belgique, la Suisse ou le Portugal : formats PT, statuts en français, délais de retrait, douane et contact.
slug: suivi-ctt-portugal
published: 2026-11-17
updated: 2026-11-17
---

CTT (CTT – Correios de Portugal) est l'opérateur postal national du Portugal, doté de sa branche express portugaise CTT Expresso. Suivez un envoi dans « Seguir objeto » sur [ctt.pt](https://www.ctt.pt/feapl_2/app/open/objectSearch/objectSearch.jspx?request_locale=pt) (en portugais et en anglais), dans l'App CTT ou en collant votre code ci-dessus. Ce numéro provient de l'expéditeur, d'un SMS ou e-mail de CTT, ou d'un avis laissé dans la boîte aux lettres.

Ne confondez pas les deux sociétés du groupe : le courrier et CTT Expresso se suivent sur `ctt.pt`, tandis que [CTT Express](carrier:ctt-express) (filiale espagnole ex-Tourline Express) utilise un suivi séparé en espagnol sur `cttexpress.com`.

## Numéro de suivi CTT : à quoi il ressemble

Sur `ctt.pt`, la plupart des codes suivent la norme postale internationale : deux lettres de service, neuf chiffres et deux lettres du pays émetteur (`PT` pour le Portugal, même chez certains marchands sous contrat expédiant d'Espagne ou de Chine). Les colis ibériques express comptent 22 ou 25 chiffres.

| Format (inventé) | Ce qu'il désigne |
| --- | --- |
| `RR000000005PT` | Code postal ou CTT Expresso créé au Portugal (`DA`, `EA`, `RR`, `CA`…). Le 9e chiffre est une clé de contrôle. |
| `RR000000005FR` | Même format émis par une autre poste — ici [La Poste](carrier:la-poste) (`BE` [bpost](carrier:bpost), `CH` [La Poste suisse](carrier:swiss-post)). |
| `0012340012345678901234` | 22 chiffres (`00`, quatre chiffres, `00` et 14 chiffres) : envoi [CTT Express](carrier:ctt-express), aussi lisible sur `ctt.pt`. |
| `0012340012345678901234001` | 25 chiffres : les 22 chiffres précédents suivis de `001`. |

Saisissez le code sans espaces : sur l'aide anglophone de CTT, `Item not found` indique que l'envoi n'est pas identifié sur son réseau (ou ne sert qu'en douane). Quand un colis `PT` part vers la France, la Belgique ou la Suisse, la remise finale passe à [La Poste](carrier:la-poste), [bpost](carrier:bpost) ou [La Poste suisse](carrier:swiss-post) sous ce même code. Aucun numéro reçu ? L'envoi circule sans suivi ; voir [trouver son numéro de suivi](guide:find-tracking-number).

## Que signifient les statuts de suivi CTT

« Seguir objeto » affiche ses jalons en portugais ou en anglais :

| Statut CTT | Signification concrète |
| --- | --- |
| `Aguarda entrada nos CTT` | Données électroniques reçues de l'expéditeur, colis physique pas encore remis. |
| `Aceite` | Pris en charge par CTT ou la poste d'origine : l'acheminement débute. |
| `Em exportação` | Contrôle douanier dans le pays de départ avant exportation. |
| `International dispatch` | Jour de départ du pays d'origine. |
| `International reception` | Jour d'entrée dans le pays de destination. |
| `Em importação` | Traitement douanier dans le pays d'arrivée. |
| `Em trânsito` | Transport ou tri entre centres ; revient souvent plusieurs fois. |
| `Em entrega` | En tournée aujourd'hui avec le facteur ou le livreur. |
| `Entrega não conseguida` | Livraison infructueuse ; vérifiez boîte aux lettres, SMS ou e-mails. |
| `No ponto de entrega` | Prêt au retrait en point CTT ou consigne. |
| `Em espera` | En attente d'instructions ou d'une date convenue. |
| `Cancelado` | Expédition annulée ; la livraison n'aura pas lieu. |
| `Entregue` | Livré. |
| `Devolvido` | Renvoyé à l'expéditeur. |

`Não entregue` signale aussi un échec de remise. Pour un colis hors UE arrivant au Portugal, `O envio foi selecionado para apresentação à Alfândega.` ou `Foi enviada notificação ao destinatário. O envio está em desalfandegamento.` indiquent l'attente de justificatifs ou du paiement douanier.

## Absence à la livraison : avis, retrait et consignes Locky

**En France, Belgique ou Suisse.** Hors du Portugal, lieux et délais de garde relèvent exclusivement de la poste locale : [La Poste](carrier:la-poste), [bpost](carrier:bpost) ou [La Poste suisse](carrier:swiss-post).

**Au Portugal.** En cas d'absence (remise exclue aux mineurs), la suite dépend du service choisi par l'expéditeur : retrait en point CTT (Loja CTT, Ponto CTT, consigne Cacifo Locky ou Payshop), nouvelle présentation, attente ou retour immédiat. Une *Encomenda Postal* sans option domicile et une *Encomenda Expresso Em Dois Dias* sans adresse choisie vont directement au point de retrait. Le délai court en jours ouvrables dès le lendemain de l'avis papier (par SMS/e-mail, l'alerte mentionne la date limite dès la mise à disposition) :

| Type d'envoi au Portugal | Retrait (jours ouvrables) |
| --- | --- |
| Courrier ou recommandé (*Correio Registado*) | 6 (10 en Loja CTT pour *Cartão de Cidadão*) |
| Colis postal national (*Encomenda Postal Nacional*) | 10 |
| Colis postal international (`CA`–`CZ`) | 15 |
| Colis CTT Expresso (`DA`–`EV`, `EX`, `EE`) | 5 (10 pour `CE`, `EP`, `EH`, `ES` et Expresso d'Espagne) |

La garde d'une *Encomenda Postal* est prolongeable jusqu'à 30 jours, celle d'une *Encomenda Expresso* jusqu'à 5 jours ouvrables si le vendeur l'autorise. Présentez l'avis (ou le code) et une pièce d'identité avec nom complet, photo et signature (carte d'identité, passeport, titre de séjour ou permis de conduire).

**Consignes Locky :** réservées à CTT Expresso et Nacex (hors courrier, *Encomenda Postal* et contre-remboursement), garde de **5 jours**. Comptez jusqu'à 48 h après l'arrivée au suivi pour la mise en casier et jusqu'à 24 h pour recevoir le PIN par SMS ou e-mail.

**Modifier une livraison Expresso :** seuls les codes débutant par `D` (*Para Amanhã*, *Em Dois Dias*) sont modifiables dans « Seguir objeto » ou l'App CTT avant mise en point CTT (payant hors contrat, p. ex. 5,01 € HT pour changer la date jusqu'à 5 jours ouvrables ; réponse sous 2 jours ouvrables). Chez [CTT Express](carrier:ctt-express), deux tentatives sont comprises du lundi au vendredi (9 h–19 h), avec 10 jours en point Collectt Express et 5 jours en Locky.

## Colis bloqué, en douane ou retourné

Certains pays transmettent tardivement ou pas du tout les scans internationaux. Sans évolution, contactez le vendeur et consultez [suivi qui ne se met plus à jour](guide:tracking-not-updating).

**Douane.** Aucun dédouanement entre le Portugal, la France et la Belgique (UE). Vers la **Suisse**, l'OFDF applique la TVA suisse (8,1 %, ou 2,6 % pour livres et denrées), sans percevoir les montants d'impôt jusqu'à **5 CHF** (base de **62 CHF** à 8,1 % ou **193 CHF** à 2,6 %), et exonère les cadeaux privés jusqu'à **100 CHF** (hors alcool et tabac).

Pour un achat expédié **hors UE vers le Portugal**, s'appliquent depuis le 1er juillet 2026 un droit forfaitaire de **3 €** (+ TVA portugaise) par type de produit jusqu'à 150 €, plus depuis le 1er novembre 2026 des frais européens de traitement de **2 €** (+ TVA) par type de produit quelle que soit la valeur (retour possible jusqu'à 150 € sans codes produit du vendeur). Sans prépaiement à la commande, le portail douanier de CTT facture en sus **7 € (+ TVA)** jusqu'à 150 €, ou **14 €** (+ droits *ad valorem*) de 150 € à 1 000 €. Délai avant retour définitif : **20 jours** après l'arrivée au Portugal ; voir [colis retenu en douane](guide:customs).

**Réclamations et retours.** Réclamation postale en ligne, par téléphone ou en Loja/Ponto CTT sous **un an** (national) ou **six mois** (international) dès l'acceptation. Conditions CTT Expresso (réservées au client expéditeur) : **8 jours** après livraison pour avarie (emballage ou photos) et **30 jours** pour perte ou retard. Un retour lancé est irréversible. Au *Refugo Postal*, les colis postaux et Expresso restent récupérables via le service client entre **20 jours et 3 mois** (jusqu'à 5 jours avant enchères).

## Service client et téléphone CTT

Coordonnées vérifiées sur `ctt.pt` le 17 novembre 2026 :

- **Téléphone CTT (particuliers) :** `+351 21 047 16 16` depuis l'étranger (`21 047 16 16` au Portugal), jours ouvrables 8 h 30–19 h 30. Entreprises : `+351 21 244 00 35` (`707 200 115` au Portugal), mêmes horaires. Aucune ligne francophone ni anglophone annoncée.
- **Formulaire :** sur [ctt.pt](https://www.ctt.pt/ajuda/particulares/apoio-ao-cliente/contactos/formulario-de-contacto) (lundi–vendredi, 8 h 30–19 h 30) ou en Loja/Ponto CTT.
- **CTT Express (Espagne) :** `900 841 352` (gratuit en Espagne), `+34 916 602 200` depuis l'étranger ou `ccaweb@cttexpress.com`, lundi–vendredi 9 h–19 h hors fériés.

> CTT alerte contre les faux SMS, e-mails et WhatsApp réclamant des frais douaniers ou une correction d'adresse sur des domaines imités (`ctt-pt.org`, `cttportugues.com`). CTT ne demande jamais de paiement par lien SMS/e-mail : tous ses vrais liens commencent par `www.ctt.pt`.

## Suivre un colis CTT avec Peek

[Peek](/) reconnaît aussitôt les codes postaux et Expresso de CTT (`PT` à clé de contrôle valide) et les liens `ctt.pt` contenant `objects=`. Si un chiffre est faux (p. ex. `RR000000004PT`), la clé échoue et aucun transporteur n'est reconnu : corrigez le code ou touchez « Changer ». Les références CTT hors norme postale passent par des services universels de suivi ; les codes à 22 ou 25 chiffres vont vers [CTT Express](carrier:ctt-express).

Peek affiche le statut, jusqu'aux 20 derniers scans placés approximativement sur la carte et le code d'état numérique CTT (CTT ne fournit pas de date estimée). Les heures portugaises sont lues sur Lisbonne (Açores dès le code postal `9500`), celles des agences espagnoles CTT Express sur Madrid ou Canaries, et celles des postes étrangères sur leur fuseau national, puis converties dans le vôtre (« Heures dans ton fuseau horaire : … » sous l'historique sur peektracker.com et iPhone). Pour un envoi `PT` vers la France, la Belgique ou la Suisse, Peek interroge aussi [La Poste](carrier:la-poste), [bpost](carrier:bpost) ou [La Poste suisse](carrier:swiss-post) : « Annoncé à La Poste / Colissimo, pas encore reçu » tant que seule l'annonce existe, puis « Livraison par La Poste / Colissimo » dès un scan au moins aussi récent, en gardant l'historique CTT (et vers « CTT Portugal » à l'arrivée au Portugal).

Peek vérifie CTT toutes les 10 minutes, toutes les 2 minutes dès 6 h locales le jour « En livraison » tant que CTT répond directement (15 minutes via services universels), et chaque heure après 48 h sans mouvement ou 12 h sur « Étiquette créée » / « Pris en charge ». Alertes au choix : « Toutes les mises à jour », « Importantes uniquement » ou « Jour de livraison uniquement ».

## Questions fréquentes sur le suivi CTT

### Où se trouve mon colis CTT ?

La dernière ligne sur « Seguir objeto » ou Peek l'indique : `Em entrega` pour une livraison aujourd'hui, `No ponto de entrega` pour un retrait disponible. Vers la France, la Belgique ou la Suisse, [La Poste](carrier:la-poste), [bpost](carrier:bpost) ou [La Poste suisse](carrier:swiss-post) prend le relais sous le même code `PT`.

### Quelle est la différence entre CTT et CTT Express ?

Les deux relèvent du Grupo CTT mais ont des suivis distincts : CTT gère le courrier et CTT Expresso au Portugal sur `ctt.pt`, tandis que [CTT Express](carrier:ctt-express) assure le colis express espagnol en Espagne et au Portugal sur `cttexpress.com`.

### Qu'est-ce qu'un point de retrait CTT ?

Au Portugal : bureau *Loja CTT*, commerce *Ponto CTT*, consigne *Cacifo Locky* ou agent *Payshop*. À l'étranger, le retrait s'effectue dans le réseau de la poste nationale de destination.

### Le service client CTT parle-t-il français ?

Aucune ligne francophone ni anglophone n'est mentionnée : le `+351 21 047 16 16` répond les jours ouvrables de 8 h 30 à 19 h 30 (tarif international), et le formulaire `ctt.pt` du lundi au vendredi aux mêmes heures.

:::sources
- [CTT : Results in Seguir objeto (anglais)](https://www.ctt.pt/ajuda/particulares/seguir-ou-alterar-entrega/seguir/resultados-que-podem-aparecer-na-pesquisa-do-seguir-objeto?language_id=1) – statuts anglais et `Item not found`
- [CTT : Resultados do Seguir objeto (portugais)](https://www.ctt.pt/ajuda/particulares/seguir-ou-alterar-entrega/seguir/resultados-que-podem-aparecer-na-pesquisa-do-seguir-objeto) – statuts portugais
- [CTT : Seguir objeto](https://www.ctt.pt/feapl_2/app/open/objectSearch/objectSearch.jspx?request_locale=pt) – outil de suivi PT/EN
- [CTT : Como seguir uma entrega](https://www.ctt.pt/ajuda/particulares/seguir-ou-alterar-entrega/seguir/como-seguir-uma-entrega) – suivi web, App CTT et international
- [CTT : Encontrar o código de envio](https://www.ctt.pt/ajuda/empresas/seguir-ou-alterar-entrega/seguir/encontrar-o-codigo-de-envio) – codes `PT` et codes à 22/25 chiffres
- [CTT : Não tenho código de envio](https://www.ctt.pt/ajuda/particulares/seguir-ou-alterar-entrega/alterar/a-entrega-vem-a-caminho-mas-nao-tenho-codigo-de-envio-o-que-posso-fazer) – envois sans suivi
- [CTT : Entrega não conseguida](https://www.ctt.pt/ajuda/particulares/receber/problemas-na-entrega/entrega-nao-conseguida) – absence et retours irréversibles
- [CTT : O que precisa de levar consigo](https://www.ctt.pt/ajuda/particulares/receber/levantar-correspondencia-ou-encomendas-/o-que-precisa-de-levar-consigo) – pièces d'identité au retrait
- [CTT : Cacifos Locky](https://www.ctt.pt/ajuda/particulares/receber/cacifos-locky/como-levantar-uma-encomenda) – garde 5 jours, PIN et délai 48 h
- [CTT : Prazo para levantar](https://www.ctt.pt/ajuda/particulares/receber/levantar-correspondencia-ou-encomendas-/prazo-para-levantar) – délais ouvrables et règles hors Portugal
- [CTT : Alterar uma entrega Expresso](https://www.ctt.pt/particulares/receber/seguir-ou-alterar-uma-entrega/alterar-uma-entrega-expresso) – codes `D` et tarif 5,01 € HT
- [CTT : Serviços cobrados](https://www.ctt.pt/ajuda/particulares/importacoes-e-desalfandegamento/taxas-e-servicos-cobrados/servicos-cobrados) – frais douaniers CTT (7 € + TVA, 14 €)
- [CTT : Fim da isenção até 150 €](https://www.ctt.pt/particulares/receber/desalfandegar/fim-da-isencao-de-direitos-aduaneiros-em-compras-ate-150-euros-fora-da-uniao-europeia) – droit 3 € + TVA et frais 2 € + TVA par type de produit
- [Commission européenne : Réforme douanière UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_fr) – 3 € dès juillet 2026 et 2 € dès novembre 2026 par type de produit
- [Commission européenne : Guidance EUR 3 duty (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – calcul par type de produit quelle que soit la valeur
- [OFDF : Réception de lettres et paquets](https://www.bazg.admin.ch/fr/reception-de-lettres-et-paquets) – TVA suisse (8,1 % / 2,6 %) et franchise 5 CHF (62 / 193 CHF)
- [OFDF : Importation en Suisse (DE)](https://www.bazg.admin.ch/bazg/de/home/information-private/interneteinkauf--post--und-kuriersendungen/einfuhr-in-die-schweiz.html) – cadeaux exonérés jusqu'à 100 CHF
- [CTT : Prazo para desalfandegar](https://www.ctt.pt/ajuda/particulares/importacoes-e-desalfandegamento/prazo-de-desalfandegamento/prazo-para-desalfandegar-a-minha-encomenda) – délai de 20 jours
- [CTT : Condições Expresso](https://www.ctt.pt/transversais/condicoes-gerais-transporte-encomendas-expresso) – réclamations 8 et 30 jours
- [CTT : Refugo Postal](https://www.ctt.pt/ajuda/particulares/receber/levantar-correspondencia-ou-encomendas-/recuperar-o-correio-que-esta-no-refugo-postal) – récupération de 20 jours à 3 mois
- [CTT : Reclamações](https://www.ctt.pt/ajuda/particulares/apoio-ao-cliente/contactos/reclamacoes) – délais 1 an national, 6 mois international
- [CTT : Linhas de apoio](https://www.ctt.pt/ajuda/particulares/apoio-ao-cliente/contactos/linhas-de-apoio) – téléphones et horaires
- [CTT : Formulário de contacto](https://www.ctt.pt/ajuda/particulares/apoio-ao-cliente/contactos/formulario-de-contacto) – contact écrit ouvrable
- [CTT : Alertas de phishing](https://www.ctt.pt/transversais/alertas-de-phishing) – faux messages et domaines
- [CTT Express : Envíos a Portugal](https://www.cttexpress.com/envios/portugal) – deux tentatives
- [CTT Express : Horarios de reparto](https://www.cttexpress.com/preguntas-frecuentes/horarios) – horaires 9 h–19 h
- [CTT Express : Envío y recogida Collectt](https://www.cttexpress.com/red-collectt-express/envio-y-recogida) – garde 10 et 5 jours
- [CTT Express : Cómo contactar](https://www.cttexpress.com/preguntas-frecuentes/como-contactar-con-ctt) – contacts Espagne
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – détection, fuseaux et relais Peek
:::
