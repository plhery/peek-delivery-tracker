---
title: Suivi Correios Brésil : codes, statuts et douane
description: Suivre un colis Correios depuis ou vers le Brésil : format des codes BR, sens des statuts portugais, taxes d’importation, Minhas Importações et contacts.
slug: suivi-correios-bresil
published: 2026-11-22
updated: 2026-11-22
---

Correios (*Empresa Brasileira de Correios e Telégrafos*) est la poste du Brésil. Un colis expédié depuis le Brésil conserve son code Correios tout au long du trajet, et la poste du pays de destination assure la livraison. Un colis acheté à l’étranger garde le code de la poste expéditrice, et Correios le livre au Brésil. Suis-le sur le [suivi Correios](https://rastreamento.correios.com.br/app/index.php), qui accepte jusqu’à 20 codes ou un CPF ou CNPJ après recopie d’un texte sur image, ou colle le code dans la case ci-dessus.

## Code de suivi Correios : à quoi il ressemble

Correios décrit ses codes de suivi comme des séquences de 13 caractères : deux lettres indiquant le service, neuf chiffres, puis deux lettres pour le pays d’origine (`BR` pour le Brésil). Ces lettres suivent la norme de l’Union postale universelle (UPU). Avec des chiffres inventés :

| Code | Ce que c’est |
| --- | --- |
| `EA482917367BR` | `E` : EMS, le service express. |
| `CA482917367BR` | `C` : colis postal. |
| `LA482917367BR` | `L` : courrier suivi. |
| `RA482917367BR` | `R` : courrier recommandé. |
| `NN482917367BR` | `N`, `B`, `D`, `P` ou `Z` (ainsi que `AV` à `AZ`, `GA`, `GD`) : lettres réservées par l’UPU aux services nationaux ou bilatéraux. |
| `RA482917367CN` | Code de [China Post](carrier:china-post), et non de Correios. À l’entrée au Brésil, Correios le livre sous ce code. |

Le neuvième chiffre contrôle les huit précédents : une seule erreur de chiffre rend généralement le code invalide. Correios précise que certains colis venus de l’étranger portent aussi un code en `BR`, issu d’un service spécifique de Correios. Pas de code ? Correios recommande de demander à la boutique. Son suivi conserve l’historique d’un colis en ligne pendant 180 jours.

## Signification des statuts de suivi Correios

Correios rédige ses statuts en portugais, avec la ville et l’État (*UF*) de chaque scan. Voici leur traduction :

| Correios indique | Ce que ça veut dire pour toi |
| --- | --- |
| `Etiqueta emitida` | Étiquette créée. Correios n’a pas encore le colis. |
| `Objeto postado` | Déposé : Correios a pris en charge le colis. |
| `Objeto encaminhado para fiscalização aduaneira de exportação` | Un colis envoyé vers l’étranger a été transmis à la douane brésilienne d’exportation avant de quitter le pays (ci-dessous). Pendant ce contrôle, Correios ne le détient pas. |
| `Objeto em transferência - por favor aguarde` | En transit entre deux unités de Correios (« en transfert, veuillez patienter »). |
| `Objeto recebido pelos Correios do Brasil` | Un colis venant de l’étranger est arrivé chez Correios au Brésil. |
| `Informações enviadas para análise da autoridade aduaneira/órgãos anuentes` | Les données ont été transmises aux douanes et aux organismes chargés du contrôle des importations. |
| `Aguardando pagamento` | Des taxes ou frais sont à régler avant livraison : voir Minhas Importações ci-dessous (« À la douane » dans Peek). |
| `Análise concluída - importação autorizada` | Le contrôle est terminé et l’importation est autorisée. |
| `Saída do Centro Internacional` | Le colis a quitté le centre international de Correios pour être livré au Brésil. |
| `Objeto saiu para entrega ao destinatário` | En livraison chez toi. |
| `Objeto não entregue - carteiro não atendido` | Le facteur n’a trouvé personne pour réceptionner le colis. |
| `Objeto aguardando retirada no endereço indicado` | T’attend pour retrait à l’adresse indiquée par Correios, par exemple dans une agence (« Prêt à être retiré » dans Peek). |
| `Inconsistências no endereçamento do objeto` | Problème avec l’adresse de livraison. |
| `Objeto entregue ao destinatário` | Livré au destinataire. |

Certaines lignes signalent un blocage. `Importação não autorizada` signifie que l’importation n’a pas été autorisée ; `Prazo de regularização encerrado`, que le délai de régularisation a expiré ; `Devolução determinada pela autoridade competente`, que les autorités ont ordonné le renvoi à l’expéditeur. `Objeto saiu para entrega ao remetente` indique une livraison retour à l’expéditeur, et non chez toi.

Les pages d’importation de Correios mentionnent aussi `Em fiscalização aduaneira` (sous contrôle douanier). Tant que cette mention s’affiche, Correios précise que le colis n’est pas entre ses mains.

## Colis du Brésil vers l’Europe et le reste du monde

Correios fait passer le colis par la douane brésilienne d’exportation, puis le remet à la poste du pays de destination, qui le livre sous le même code (l’UPU n’autorise qu’un code par envoi). Le suivi de cette poste donne le dernier trajet de première main : par exemple [La Poste](carrier:la-poste), [Royal Mail](carrier:royal-mail), [USPS](carrier:usps), [Postes Canada](carrier:canada-post), An Post, [Australia Post](carrier:australia-post), [CTT](carrier:ctt), [Correos](carrier:correos-spain), [Poste Italiane](carrier:poste-italiane) ou [Deutsche Post](carrier:dhl).

Si ta douane retient le colis, Correios précise que c’est à toi de traiter directement avec elle : ni Correios ni ta poste ne peuvent intervenir. Dans l’UE, les achats en ligne provenant hors UE dans des colis d’une valeur allant jusqu’à 150 € supportent un droit de douane de 3 € par article (marchandises partageant une même classification tarifaire) depuis le 1er juillet 2026. Depuis le 1er novembre 2026, des frais de traitement de l’Union de 2 € par article s’appliquent également, quelle que soit la valeur. Ce que tu paies et comment ta poste le perçoit : [colis bloqué à la douane](guide:customs).

## Colis vers le Brésil : CPF, taxes et Minhas Importações

**CPF.** « Tout colis provenant d’autres pays est une importation, et toute importation nécessite un CPF », indique Correios. Le CPF doit être en règle et ton nom doit correspondre aux registres de la Receita Federal.

**Minhas Importações.** Correios gère les formalités douanières en ligne sur [Minhas Importações](https://portalimportador.correios.com.br) et dans l’application Correios, après une inscription unique sur Meu Correios. Tu y associes les colis à ton CPF, transmets les documents demandés par la douane et règles les frais.

**Taxes**, telles que publiées par la Receita Federal :

- **Droit d’importation :** 60 % de la valeur en douane (marchandises, port et assurance). Cette règle générale s’applique à tout colis non couvert par la règle suivante : achat hors Remessa Conforme ou envoi destiné à une entreprise.
- **Achat sur un site [certifié dans Remessa Conforme](https://www.gov.br/receitafederal/pt-br/assuntos/aduana-e-comercio-exterior/manuais/remessas-postal-e-expressa/empresas-certificadas-no-programa-remessa-conforme-prc) (PRC) envoyé à un particulier :** 0 % jusqu’à 50 $ US ; au-delà, 60 % avec une déduction de 30 $ US sur le droit. Ce barème s’applique aux déclarations enregistrées depuis le 12 mai 2026 (auparavant, 20 % et 20 $ US de déduction).
- **ICMS**, la taxe d’État : de 17 % à 20 % selon l’État, avec ou sans PRC.
- **Frais Correios** de traitement postal douanier (*Despacho Postal*), susceptibles de s’ajouter. Ce n’est pas une taxe.

Au-dessus de 3 000 $ US, ou si la douane ne libère pas le colis pour un autre motif, Correios indique qu’il faut recourir à un commissionnaire en douane (*despachante aduaneiro*).

**Quand payer.** Sur un site certifié PRC, les taxes sont réglées au moment du paiement ; si le panier n’en affiche aucune, elles seront facturées à l’arrivée sans la réduction. Sinon, paie par *boleto* ou Pix dans Minhas Importações ou l’application sous 20 jours calendaires à compter de l’émission du montant, faute de quoi le colis repart à l’étranger. Si le montant te semble erroné, demande une *Revisão de Tributos* dans le portail avant de générer le *boleto* ou le Pix. Les justificatifs ne peuvent être envoyés qu’une seule fois, et Correios ne peut pas indiquer le délai de réponse de la Receita Federal.

**Livraison.** Si Correios ne peut pas livrer, le colis est déposé à l’agence la plus proche avec un avis. Les colis venus de l’étranger attendent 20 jours calendaires après les tentatives à domicile, et 7 jours pour EMS et Correios Packet. Un tiers peut retirer le colis avec ton autorisation signée, sa pièce d’identité originale et la tienne (une copie suffit).

**Arnaques.** Correios n’envoie jamais de liens de paiement et ne demande jamais de données personnelles ou bancaires par e-mail, SMS ou WhatsApp, et la Receita Federal n’envoie pas de *boletos*, n’appelle pas et n’écrit pas par e-mail au sujet des taxes. Rends-toi directement sur `correios.com.br` sans passer par un moteur de recherche ; voir [arnaques aux faux SMS de livraison](guide:delivery-scam-texts).

## Colis Correios bloqué, perdu ou retourné

**Le code n’apparaît jamais.** Correios te demande de vérifier auprès de l’expéditeur que le code est exact (lettres comprises) et que le colis a bien été posté. Si c’est le cas, l’expéditeur dépose une réclamation.

**Bloqué à la douane.** Correios ne donne aucun délai pour les contrôles douaniers. Surveille Minhas Importações pour toute demande de justificatif ou de paiement ; voir [pourquoi un suivi ne se met plus à jour](guide:tracking-not-updating).

**Payé mais non livré.** Une fois dépassée la prévision de livraison affichée dans le suivi, dépose une réclamation (*manifestação*) sur le site de Correios.

**Perdu ou abîmé.** Les réclamations passent par Fale Conosco avec un compte Meu Correios, par la personne ayant posté le colis ou généré le *boleto* ou Pix fiscal. Pour les envois internationaux, le délai court à partir de la date de dépôt : 120 jours pour EMS et les services express de Correios, 6 mois pour le reste ; pour les importations Correios Packet, 90 jours après livraison ou retour (30 jours pour avarie). Correios vise une réponse sous 5 jours ouvrés et ne verse les indemnisations que sur un compte bancaire brésilien. Selon le droit brésilien, l’envoi appartient à l’expéditeur jusqu’à sa livraison : hors du Brésil, demande donc à la boutique ou à l’expéditeur de réclamer ; voir [colis perdus](guide:lost-parcel).

**Retourné.** `Objeto entregue ao remetente` signifie que le colis est revenu chez l’expéditeur. Demande à la boutique un remboursement ou un nouvel envoi ; voir [retourné à l’expéditeur](guide:returned-to-sender).

## Numéro et service client Correios

Coordonnées publiées par Correios en portugais (horaires en heure de Brasilia) :

- **Téléphone (SAC) :** `4003 8210` dans les capitales d’État, zones métropolitaines et depuis mobile ailleurs ; `0800 881 8210` depuis fixe ailleurs. Du lundi au vendredi de 8 h à 20 h, le samedi de 8 h à 14 h, hors jours fériés.
- **WhatsApp :** `(11) 4003-8210`, qui gère aussi le suivi.
- **Chat :** Carol, sur `correios.com.br`.
- **Réclamations :** le portail [Fale Conosco](https://faleconosco.correios.com.br/inicio).

Les centres de tri et unités de distribution ne prennent pas d’appels.

## Suivre un colis Correios avec Peek

[Peek](/) suit plus de 4 500 transporteurs et identifie aussitôt Correios pour un code de deux lettres, neuf chiffres et `BR` lorsque le chiffre de contrôle est exact (« Transporteur détecté »). S’il est faux, Peek ne nomme aucun transporteur et, si tu choisis Correios via « Changer », il indique que Correios « n’émet pas de numéros comme celui-ci » : vérifie le code. Les liens Correios contenant `objetos=` fonctionnent aussi.

Peek récupère l’historique chez Correios et affiche le statut, le service, chaque scan en portugais avec sa ville et son État, l’heure de livraison ainsi que la date de livraison prévue par Correios tant que le colis est en transit et sans retard. Les horaires sont lus sur l’heure locale du lieu du scan (heure de Brasilia au Brésil) et affichés dans ton fuseau horaire (« Heures dans ton fuseau horaire : » suivi de son nom).

Correios n’indiquant pas à Peek le pays de destination, hors du Brésil Peek interroge aussi ta poste nationale (La Poste, USPS, Royal Mail, Postes Canada, An Post ou Australia Post, par exemple) avec le même code et bascule dès qu’elle affiche des scans plus récents, en gardant l’historique Correios. Un colis entrant au Brésil sous le code d’une autre poste est suivi chez cette poste ; Peek interroge aussi Correios dès que celle-ci nomme le Brésil comme destination (ou, si elle n’en nomme aucune, quand tu recherches depuis le Brésil).

Tant que le colis est ouvert dans Peek — ou le jour de la livraison si les notifications sont activées — Peek interroge directement Correios jusqu’à toutes les 10 minutes, et jusqu’à toutes les 2 minutes (ou toutes les 5 minutes s’il doit ouvrir le suivi Correios dans un navigateur) dès 6 h à l’heure du dernier scan (heure de Brasilia au Brésil) le jour où Correios scanne le colis « En livraison », tant que son suivi répond ; sinon, ainsi qu’après deux jours sans nouveauté (12 heures tant que le colis reste sur « Étiquette créée » ou « Pris en charge »), il vérifie toutes les heures. Pour les notifications, choisis « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Après quatre jours sans scan en route, hors douane ou problème signalé, Peek affiche la date du dernier scan (« Aucune mise à jour depuis … ») et indique « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger Correios Brazil. », avec le lien « Demande de l’aide à Correios Brazil » vers son centre de contact. Peek ne peut ni contacter Correios, ni payer des taxes, ni déposer de réclamation pour toi.

## Questions sur le suivi Correios Brésil

### Que signifie « Objeto em transferência - por favor aguarde » ?

« Objet en transfert, veuillez patienter » : le colis voyage entre deux unités de Correios au Brésil, et tu n’as rien à faire pour l’instant.

### Pourquoi mon colis Correios affiche-t-il « Aguardando pagamento » ?

Des taxes ou les frais de Correios sont à régler. Paie par *boleto* ou Pix dans Minhas Importações ou l’application Correios sous 20 jours calendaires, sinon le colis repart à l’étranger.

### Quel est le délai de livraison de Correios ?

Il dépend du service choisi et, au Brésil, du code postal (*CEP*) ; le suivi de Correios affiche une prévision de livraison. Pour un colis venu de l’étranger, le délai court à partir de `Saída do Centro Internacional`, après libération par la douane.

### Puis-je suivre un colis Correios hors du Brésil ?

Oui, avec le même code : sur le suivi de Correios, puis sur celui de ta poste nationale dès son arrivée.

:::sources
- [Correios: Rastreamento](https://rastreamento.correios.com.br/app/index.php) – suivi, 20 codes ou CPF/CNPJ, vérification par image
- [Correios: Recebimento de Encomendas Internacionais](https://www.correios.com.br/receber/encomenda/internacional) – CPF, Minhas Importações, paiement, révision, courtier, statut douanier
- [Correios: Como importar pelos Correios (PDF)](https://www.correios.com.br/receber/encomenda/arquivos/internacional/como-importar-pelos-correios-2024-1.pdf) – format du code, Aguardando pagamento, délai de livraison
- [Correios: Prazo de Guarda Objetos Internacionais](https://www.correios.com.br/receber/prazo-de-guarda-objetos-internacionais) – 20 et 7 jours
- [Correios: Perguntas frequentes](https://www.correios.com.br/acesso-a-informacao/perguntas-frequentes) – codes manquants, 180 jours, retrait en agence
- [Correios: Envio Internacional de Encomendas](https://www.correios.com.br/enviar/encomendas/internacional/internacional) – dédouanement export, douane à l’étranger
- [Correios: Fale Conosco, Termo de uso](https://faleconosco.correios.com.br/inicio) – réclamations, délais, indemnisation
- [Correios: Central de Atendimento](https://www.correios.com.br/falecomoscorreios/central-de-atendimento) – téléphones, horaires, WhatsApp, Carol
- [Correios: Segurança](https://www.correios.com.br/falecomoscorreios/seguranca/seguranca) – aucun lien de paiement envoyé
- [Receita Federal: Tributação](https://www.gov.br/receitafederal/pt-br/assuntos/aduana-e-comercio-exterior/manuais/remessas-postal-e-expressa/topicos/tributacao) – taux, ICMS, frais, arnaques
- [Receita Federal: Em que momento vou pagar os impostos?](https://www.gov.br/receitafederal/pt-br/assuntos/aduana-e-comercio-exterior/manuais/remessas-postal-e-expressa/preciso-pagar-impostos-nas-compras-internacionais/em-que-momento-pagarei-o-imposto) – taxes au paiement
- [Receita Federal: Como realizar pagamento pelo Minhas Importações dos Correios](https://www.gov.br/receitafederal/pt-br/assuntos/aduana-e-comercio-exterior/manuais/remessas-postal-e-expressa/preciso-pagar-impostos-nas-compras-internacionais/como-realizar-o-pagamento-pelo-minhas-importacoes-dos-correios) – ne pas passer par un moteur de recherche
- [Receita Federal: Empresas certificadas no Programa Remessa Conforme](https://www.gov.br/receitafederal/pt-br/assuntos/aduana-e-comercio-exterior/manuais/remessas-postal-e-expressa/empresas-certificadas-no-programa-remessa-conforme-prc) – sites certifiés, nom complet de Correios
- [UPU: S10 standard, Identification of postal items](https://www.upu.int/UPU/media/upu/files/postalSolutions/programmesAndServices/standards/S10-12.pdf) – lettres de service, chiffre de contrôle
- [European Commission: EU Customs Reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – droit de 3 €, frais de traitement de 2 €
- [European Commission: Guidance on the €3 customs duty and Union handling fee](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – définition d’un article, ventes à distance
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – libellés de statuts, champs lus par Peek et fréquence de vérification
:::
