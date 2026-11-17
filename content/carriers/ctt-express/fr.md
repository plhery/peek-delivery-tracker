---
title: Suivi CTT Express : statuts en français, relais et contact
description: Suivez un colis CTT Express en Espagne ou au Portugal : numéro à 22 ou 25 chiffres, statuts espagnols traduits, points Collectt, absence et contact.
slug: suivi-ctt-express
published: 2026-11-17
updated: 2026-11-17
---

CTT Express est la filiale espagnole de colis express du groupe portugais CTT (anciennement Tourline Express, rebaptisée en 2019), qui livre dans toute l'Espagne — péninsule, Baléares, Canaries, Ceuta et Melilla — ainsi qu'au Portugal. Vous pouvez suivre votre envoi dans le [localizador](https://www.cttexpress.com/localizador-de-envios/) sur `cttexpress.com` ou coller votre numéro dans le champ ci-dessus. Le code provient de la boutique ou de l'expéditeur, et CTT Express vous l'envoie par SMS ou par e-mail. Ne le confondez pas avec [CTT](carrier:ctt), la poste nationale du Portugal : un code comportant des lettres et se terminant par `PT` relève du réseau postal portugais.

## Votre numéro de suivi CTT Express

L'outil de suivi de CTT Express demande le *código de envío* (numéro d'expédition), composé en général d'une longue suite de chiffres figurant dans le SMS ou l'e-mail de confirmation. Voici les formats reconnus dans le catalogue de Peek (exemples inventés) :

| Format (inventé) | À qui il correspond |
| --- | --- |
| `0012340012345678901234` | 22 chiffres : `00`, quatre chiffres, `00`, puis 14 chiffres (codes d'agence commençant par `00` aux positions 1–2 et 7–8). CTT Express. |
| `0012340012345678901234001` | 25 chiffres : les mêmes 22 chiffres suivis du compteur de colis (`001` pour le premier ou l'unique colis selon la spécification d'étiquetage de CTT Express). CTT Express. |
| `0000100012345678901234` | 22 chiffres commençant par `00001000` : format partagé entre CTT Express, TIPSA, Austrian Post et d'autres transporteurs. |
| `RR482910576PT` | Deux lettres, neuf chiffres et `PT` : code postal ou CTT Expresso de [CTT](carrier:ctt), suivi sur `ctt.pt`. |

**CTT, CTT Expresso ou CTT Express ?** Un seul groupe réunit deux réseaux distincts : [CTT](carrier:ctt) (*Correios de Portugal*) assure le service postal universel au Portugal et exploite sa branche express portugaise, CTT Expresso, avec des codes alphanumériques en `PT` (`DA`, `EA`, etc.), tandis que CTT Express opère le réseau express espagnol (`CTT 24h`, `CTT 48h`, `CTT e24h`, `CTT 10h` et `CTT 14h`). En mai 2026, CTT Expresso et DHL eCommerce ont par ailleurs conclu une coentreprise stratégique dans la péninsule Ibérique (participations croisées de 25 %, sans fusion) : en Espagne, CTT Express traite les colis B2C sous la marque *CTT Express together with DHL* tandis que DHL eCommerce gère le B2B et le transfrontalier, et au Portugal CTT Expresso intègre les activités de DHL eCommerce. Les deux suivis se recoupent en partie : la version portugaise de `cttexpress.com` renvoie vers « Seguir objeto » sur `ctt.pt`, qui lit aussi les numéros à 22 et 25 chiffres, et la page de suivi de CTT Express cite elle-même un code en `PT` parmi ses exemples. Si l'un des sites ne trouve rien, essayez l'autre.

Vous n'avez reçu aucun numéro ? Demandez-le au vendeur ou consultez notre guide pour [retrouver un numéro de suivi](guide:find-tracking-number).

## Statuts de suivi CTT Express traduits en français

CTT Express ne propose aucun suivi en français ni en anglais : même en changeant de langue sur `cttexpress.com`, la page de suivi (`shipping-tracking.production.cloud2.cttexpress.com`) reste intégralement en espagnol. Elle affiche quatre jalons (`Grabado`, `Enviado`, `En reparto` et `Entrega prevista` avec une date ou `Sin determinar`), suivis du libellé détaillé de chaque scan.

| Statut en espagnol | Ce qu'il signifie pour vous |
| --- | --- |
| `Pendiente de recepción en CTT Express` | L'expéditeur a enregistré l'envoi, mais le colis physique n'est pas encore entré dans le réseau de CTT Express. |
| `Recogido` | Colis ramassé chez l'expéditeur (`Recogida fallida` si l'enlèvement a échoué). |
| `Enviado` ou `En tránsito` | En cours d'acheminement entre agences ou centres de tri (`En clasificación` pendant le tri, `Aceptado por la última milla` à l'arrivée dans l'agence locale de livraison). |
| `En tránsito internacional. Pendiente de recepción en CTT Express` | Colis en route depuis l'étranger ; CTT Express ne l'a pas encore réceptionné en Espagne ou au Portugal. |
| `En reparto` ou `Entrega hoy` | Confié au chauffeur et en tournée de livraison aujourd'hui. |
| `Incidencia en el reparto` ou `Reparto fallido` | Un obstacle a empêché la remise ; le motif précis s'affiche juste en dessous. |
| `Recogerán en Delegación` | « Retrait en agence » : sur un colis livré au Portugal par [CTT](carrier:ctt), la livraison a échoué et le pli attend d'être retiré. Le suivi de CTT affiche l'échec au même moment, puis la disponibilité au point de retrait. |
| `Almacenado temporalmente` | Entreposé provisoirement au dépôt après un échec, dans l'attente d'une nouvelle tournée ou d'un retour. |
| `Nuevo reparto` | Remis en tournée pour une nouvelle tentative de livraison. |
| `Disponible en Punto Collectt Express` | Prêt à être retiré dans un point Collectt Express ; le code PIN arrive par e-mail et SMS. |
| `El envío fue entregado correctamente` | Livré. |
| `En devolución` | En cours de renvoi vers l'expéditeur. |
| `Devuelto a remitente` | Rendu à l'expéditeur. |

`Pendiente de recepción en CTT Express` inquiète souvent à tort : seule l'étiquette existe pour l'instant. Pour un envoi parti d'Espagne ou du Portugal, CTT Express précise que si cet état persiste plus de 24 heures après la demande d'enlèvement, vous pouvez utiliser l'option correspondante de son formulaire de contact (*« Mi envío sigue en pendiente de entrada en red »*).

Sous un incident, la ligne complémentaire précise le blocage, comme `No pudimos contactar contigo` (« nous n'avons pas pu vous joindre ») ou `Dirección incorrecta` (« adresse erronée »). La FAQ de CTT Express prévient qu'un motif « destinataire absent » peut apparaître même si vous étiez chez vous lorsque le livreur n'a pas pu accéder à votre porte ou à l'interphone.

**Commandes venant de l'étranger et hors UE :** un colis acheté hors de la péninsule doit d'abord être pris en charge dans son pays de départ et voyager jusqu'en Espagne ou au Portugal, ce qui prolonge `En tránsito`. Pour les boutiques expédiant hors de l'Union européenne (notamment depuis la Chine), la plateforme douanière de CTT Express trie plus de 300 000 colis par jour 24 h/24 et dédouane en **12 heures en moyenne**, avant une livraison en **24 ou 48 heures** sur son réseau ibérique. Si la douane retient l'envoi, le suivi l'indique ou vous recevez une notification réclamant des documents ou le paiement des taxes. Depuis le 1er juillet 2026, les commandes hors UE d'une valeur maximale de 150 € acquittent dans l'UE un droit de douane forfaitaire de **3 € par type de produit**, et depuis le 1er novembre 2026 tous ces envois paient en plus **2 € de frais européens de traitement douanier par type de produit**, quelle que soit leur valeur. Pour savoir qui les encaisse et comment débloquer l'envoi, consultez [colis retenu en douane](guide:customs).

## Horaires de livraison, points Collectt et absence

En Espagne, CTT Express livre du lundi au vendredi, généralement entre **9 h 00 et 19 h 00** selon le service et la commune ; la livraison le samedi n'existe **que pendant la campagne de Noël**. Le chauffeur ne peut pas prévenir à l'avance de son heure de passage et n'appelle que si vous êtes absent ou si l'adresse est introuvable. Une fois le colis remis, CTT Express envoie un e-mail de confirmation.

Le transporteur inclut **deux tentatives de livraison**, la seconde ayant lieu le jour ouvrable suivant (une troisième présentation est possible si vous transmettez une solution après le deuxième échec). Après un passage infructueux, vous recevez un avis comportant un lien pour choisir une nouvelle date, une autre adresse, un voisin ou un point Collectt Express.

Vous pouvez aussi modifier la livraison vous-même depuis le localizador en saisissant le numéro d'envoi complet, le code postal de destination initial et le numéro de téléphone ou l'adresse e-mail enregistrés par le vendeur. Vous pouvez y demander une nouvelle adresse, une autre date, un créneau matin ou après-midi, la remise à un voisin ou un point Collectt. Ces changements ne sont possibles **qu'avant le passage à `En reparto`** et peuvent ajouter 24 heures au délai. Le changement d'adresse dans la même zone de distribution et le transfert vers un point Collectt sont **gratuits** (le retrait en point Collectt est réservé aux envois d'un seul colis jusqu'à 20 kg, dont la somme des trois dimensions ne dépasse pas 160 cm avec 60 cm maximum par côté, sans contre-remboursement, vérification d'identité ni retour simultané).

**Points Collectt Express et consignes Locky :** CTT Express recense plus de **20 000** points partenaires et consignes automatiques Locky en Espagne et au Portugal. Un point Collectt conserve le colis **10 jours** (jusqu'à 10 jours ouvrables selon la FAQ horaires) et une consigne Locky **5 jours** ; le retrait est gratuit sur présentation du code PIN envoyé par e-mail et/ou SMS. Passé ce délai, le pli suit les instructions prévues au contrat de l'expéditeur. Si le casier Locky ne s'ouvre pas, appelez le numéro affiché sur l'automate ou dans l'e-mail de suivi.

**Au Portugal**, les règles d'aide de [CTT](carrier:ctt) s'appliquent : le réseau Collectt y regroupe les *Lojas CTT*, *Pontos CTT*, agents *Payshop* et consignes *Cacifos Locky*. En cas d'absence, le colis peut être déposé dans l'un de ces points, représenté ou retourné selon les consignes du vendeur. CTT accorde **10 jours ouvrables** pour retirer un colis Expresso venant d'Espagne et **5 jours** dans une consigne Locky.

## Colis CTT Express en retard, perdu ou endommagé

Pour obtenir un remboursement ou un nouvel envoi, adressez-vous toujours à la boutique auprès de laquelle vous avez passé commande.

- **En retard :** CTT Express considère un envoi comme retardé dès qu'il dépasse le délai estimé ; week-ends, jours fériés, intempéries et pic de fin d'année allongent l'acheminement. Voir aussi [suivi qui ne se met plus à jour](guide:tracking-not-updating).
- **Marqué livré mais non reçu :** vérifiez d'abord auprès des membres de votre foyer, d'un voisin ou de la loge (le scan précise parfois `El envío fue entregado en conserjería` ou `El envío fue entregado a vecino según las instrucciones indicadas.`), puis utilisez le motif dédié du formulaire de contact (*« No tengo mi paquete y pone entregado »*). Plus de conseils dans [colis indiqué livré mais non reçu](guide:delivered-not-received).
- **Endommagé :** photographiez le colis avant de le déballer, conservez le carton et les étiquettes, et déclarez l'avarie via le formulaire dans les **7 jours calendaires** suivant la réception en précisant si l'emballage extérieur, le contenu ou les deux sont touchés (l'expéditeur peut avoir souscrit une couverture optionnelle contre la perte ou le vol jusqu'à 2 000 €). CTT Express ouvre un dossier et vous indique qui le traite.
- **Annulé :** si le suivi mentionne une expédition annulée, contactez le vendeur pour qu'il la réactive.
- **Au Portugal :** les conditions générales de CTT Expresso réservent l'indemnisation au client expéditeur, avec un délai de **8 jours** après la livraison pour signaler une avarie et de **30 jours** pour une perte ou un retard.

## Téléphone et service client CTT Express

Coordonnées publiées sur les sites officiels de CTT Express et de CTT (aucun accueil en français ni en anglais n'est annoncé) :

- **Espagne :** `900 841 352` (numéro gratuit depuis l'Espagne sur les pages d'aide ; l'article douane du 27 octobre 2026 mentionne `900 110 898`) ou `916 602 200` (`+34 916 602 200` depuis l'étranger), du lundi au vendredi de **9 h 00 à 19 h 00** hors jours fériés. Par écrit : `ccaweb@cttexpress.com` ou le [formulaire de contact](https://www.cttexpress.com/contacto-atc) (numéro de suivi obligatoire). Le service client est centralisé à Madrid, uniquement par téléphone, e-mail ou formulaire — sans guichet public, chat en direct ni WhatsApp — et répond habituellement sous **24 heures ouvrables**.
- **Portugal :** ligne [CTT](carrier:ctt) au `21 047 16 16` (`+351 21 047 16 16` depuis l'étranger), les jours ouvrables de **8 h 30 à 19 h 30**, et formulaire sur `ctt.pt/ajuda`.

> CTT Express rappelle qu'il ne vous demande jamais d'appeler un numéro surtaxé commençant par `807`, et met en garde contre les SMS et e-mails frauduleux réclamant des frais supplémentaires (*gastos adicionales*) via un lien pour débloquer un colis. Au Portugal, CTT signale également de faux SMS « CTT Expresso » évoquant des frais de douane ; les vrais liens de CTT commencent tous par `www.ctt.pt`. Ne suivez vos colis que sur les sites officiels.

## Suivre un colis CTT Express avec Peek

Collez un numéro répondant aux deux premiers formats du tableau ci-dessus et [Peek](/) identifie aussitôt CTT Express, y compris la version à 25 chiffres se terminant par `001` d'un code en `00001000` (en revanche, Peek ne reconnaît pas comme CTT Express un numéro à 22 chiffres dépourvu de `00` aux positions 1–2 et 7–8). Pour un numéro à 22 chiffres commençant par `00001000`, Peek interroge en même temps TIPSA, Austrian Post et CTT Express (et peut consulter USPS si aucun des trois ne connaît le pli) ; s'il ne peut pas trancher, choisissez le transporteur avec « Changer ». Un lien copié depuis la page `shipping-tracking.production.cloud2.cttexpress.com` contenant `sc=` fonctionne aussi directement. Saisi seul, un code se terminant par `PT` est dirigé vers [CTT](carrier:ctt), et les expéditions multi-colis passent par des services universels de suivi.

Peek interroge CTT Express jusqu'à toutes les 10 minutes, et jusqu'à toutes les 2 minutes à partir de 6 h locales le jour où le colis passe « En livraison » tant que le suivi direct de CTT Express répond (toutes les 15 minutes via les services universels). Après 48 heures sans mouvement — ou 12 heures lorsque le pli reste sur « Étiquette créée » ou « Pris en charge » — la vérification devient horaire, et vous pouvez choisir entre les alertes « Toutes les mises à jour », « Importantes uniquement » ou « Jour de livraison uniquement ». Peek affiche le statut, l'historique des scans (sans localités sur la carte, car le flux public de CTT Express n'en fournit aucune), la date de livraison prévue tant que l'envoi circule sans blocage, le poids, l'heure de remise et, lorsqu'une autre poste a confié le pli, son numéro international. Si ce code se termine par `PT`, [CTT](carrier:ctt) assure la remise au Portugal : la carte affiche « Livraison par CTT Portugal » avec ce numéro (ou « Annoncé à CTT Portugal, pas encore reçu » tant que CTT répertorie l'envoi sans scan propre). Dès que `ctt.pt` publie un scan au moins aussi récent que le dernier événement de CTT Express, Peek suit le colis chez CTT à sa cadence tout en conservant l'historique de CTT Express. Les libellés restent dans l'espagnol d'origine (`En reparto`, ou `Disponible en Punto Collectt Express` sans le nom ni l'adresse du relais), un renvoi (`En devolución`) est signalé comme incident jusqu'à `Devuelto a remitente`, et sur `peektracker.com` comme dans l'application iPhone, l'historique se conclut par « Heures dans ton fuseau horaire : … ».

## Questions fréquentes sur le suivi CTT Express

### Qu'est-ce que CTT Express ?

CTT Express est la filiale espagnole de livraison express du groupe portugais CTT, issue du changement de nom de Tourline Express en 2019. Elle est distincte de [CTT](carrier:ctt), la poste portugaise, même si toutes deux appartiennent au même groupe et si CTT Expresso a noué en mai 2026 une coentreprise ibérique avec DHL eCommerce. Elle dessert toute l'Espagne (péninsule, Baléares, Canaries, Ceuta et Melilla) et le Portugal.

### CTT Express livre-t-il le samedi ?

Uniquement pendant la période de Noël en Espagne. Le reste de l'année, les tournées ont lieu du lundi au vendredi, en général entre 9 h 00 et 19 h 00.

### Peut-on suivre un colis CTT Express en français ou en anglais ?

Non. La page de suivi officielle fonctionne exclusivement en espagnol ; le tableau plus haut traduit les principaux statuts et motifs d'incident.

### CTT Express dispose-t-il d'un chat en direct ou d'un WhatsApp ?

Aucun chat ni compte WhatsApp ne figure sur son site officiel : le service client répond uniquement par téléphone (`900 841 352` en Espagne ou `+34 916 602 200` depuis l'étranger), par e-mail (`ccaweb@cttexpress.com`) et via le formulaire de contact.

### Peut-on suivre un envoi CTT Express sans numéro de suivi ?

Non. Le localizador ne comporte qu'un seul champ réservé au numéro d'expédition, et le formulaire de contact l'exige également. Recherchez-le dans vos SMS ou e-mails de confirmation, ou demandez-le au marchand qui a expédié la commande.

:::sources
- [CTT Express : Localizador de envíos](https://www.cttexpress.com/localizador-de-envios/) – suivi, modification de livraison et e-mail de confirmation
- [CTT Express : Localizador de envios (portugais)](https://www.cttexpress.com/localizador-de-envios/?language_code=pt) – redirection vers « Seguir objeto » sur `ctt.pt`
- [CTT Express : Seguimiento de envíos](https://shipping-tracking.production.cloud2.cttexpress.com/) – interface uniquement en espagnol, étapes, motifs d'incident, champ unique et exemple de code `PT`
- [CTT Express : Empresa de Paquetería y Mensajería](https://www.cttexpress.com/) – couverture ibérique, réseau Collectt et sélecteur de langue
- [CTT Express : Quiénes somos](https://www.cttexpress.com/nosotros/quienes-somos) – groupe CTT et renommage de Tourline Express en 2019
- [CTT Express : CTT Express y DHL unen fuerzas](https://www.cttexpress.com/nosotros/ctt-express-y-dhl-unen-fuerzas) – alliance stratégique ibérique avec DHL eCommerce
- [CTT Express : Nota de prensa alianza CTT Expresso y DHL eCommerce](https://www.cttexpress.com/zona-de-prensa/notas-de-prensa/ctt-expresso-y-dhl-ecommerce-sellan-un-hito-en-su-alianza-para-construir-la-red-de-paqueteria-mas-completa-de-la-peninsula-iberica) – coentreprise de mai 2026 et participations croisées de 25 %
- [CTT Express : Envíos urgentes 24 horas](https://www.cttexpress.com/envios/24-h) – services express `CTT 24h`, `CTT e24h`, `CTT 14h` et `CTT 10h`
- [CTT Express : Envíos 48 horas](https://www.cttexpress.com/envios/48-h) – service `CTT 48h` vers Baléares, Canaries, Ceuta, Melilla, Madère et Açores
- [CTT Express : Shipment Number Calculation and Package Codes (PDF)](https://transfer.cttexpress.com/IT/export/integra/Shipment_Number_Calculation_and_Package_Codes_of_CttExpress.pdf) – structure des codes à 22 et 25 chiffres et compteur de colis `001`
- [CTT Express : FAQs sobre los estados de envío](https://www.cttexpress.com/preguntas-frecuentes/estados-envio) – statuts, attente de plus de 24 h, colis marqué livré et avaries sous 7 jours calendaires
- [CTT Express : Preguntas frecuentes de incidencias](https://www.cttexpress.com/preguntas-frecuentes/incidencias) – deux tentatives, retards et réclamations
- [CTT Express : Horarios de reparto](https://www.cttexpress.com/preguntas-frecuentes/horarios) – horaires 9 h–19 h, samedis à Noël et appel du livreur
- [CTT Express : Cambiar dirección de entrega](https://www.cttexpress.com/cambiar-direccion-entrega/) – règles et gratuité des changements avant `En reparto`
- [CTT Express : Servicios incluidos y adicionales](https://www.cttexpress.com/servicios/incluidos-y-adicionales) – tentatives incluses, limites Collectt (20 kg, 160 cm) et couverture optionnelle jusqu'à 2 000 €
- [CTT Express : Recoge y devuelve tus paquetes en nuestros puntos](https://www.cttexpress.com/red-collectt-express/envio-y-recogida) – plus de 20 000 points Collectt (10 jours) et consignes Locky (5 jours)
- [CTT Express : Preguntas frecuentes Puntos Collectt Express](https://www.cttexpress.com/preguntas-frecuentes/puntos-collectt-express) – code PIN et transfert gratuit en point Collectt
- [CTT Express : Qué significa « aceptado por la última milla »](https://www.cttexpress.com/blog/logistica/que-significa-aceptado-ultima-milla) – explication des jalons d'acheminement
- [CTT Express : Nota de prensa sobre envíos](https://www.cttexpress.com/zona-de-prensa/notas-de-prensa/como-localizo-mi-paquete-con-ctt-express-y-otras-preguntas-frecuentes-sobre-envios) – transit des commandes internationales
- [CTT Express : Servicio de importación de paquetería](https://www.cttexpress.com/servicios/importacion/) – dédouanement hors UE en 12 heures en moyenne (plus de 300 000 colis/jour) et livraison en 24 ou 48 h
- [CTT Express : ¿Qué hacer si mi paquete ha quedado retenido en aduanas?](https://www.cttexpress.com/blog/expansion-internacional/que-hacer-si-mi-paquete-ha-quedado-retenido-en-aduanas) – retenues douanières, Canaries et numéro `900 110 898`
- [Commission européenne : Réforme douanière de l'UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_fr) – droit de douane de 3 € par type de produit jusqu'à 150 € depuis le 1er juillet 2026 et frais de traitement de 2 € depuis le 1er novembre 2026
- [Commission européenne : Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – droit de 3 € et frais de 2 € appliqués par type de produit quelle que soit la valeur
- [CTT Express : Cómo contactar con CTT Express](https://www.cttexpress.com/preguntas-frecuentes/como-contactar-con-ctt) – téléphones, horaires, e-mail, réponse sous 24 h ouvrables et alerte `807`
- [CTT Express : Contacto y Atención al Cliente](https://www.cttexpress.com/contacto-atc) – formulaire officiel exigeant le numéro de suivi
- [CTT Express : Qué es Phishing](https://www.cttexpress.com/blog/actualidad/como-evitar-phishing) – faux SMS et e-mails réclamant des frais supplémentaires
- [CTT : Encontrar o código de envio](https://www.ctt.pt/ajuda/empresas/seguir-ou-alterar-entrega/seguir/encontrar-o-codigo-de-envio) – codes `PT` à 13 caractères et numéros à 22 ou 25 chiffres
- [CTT : Condições Gerais do Serviço Postal Universal](https://www.ctt.pt/home/servico-postal-universal/condicoes-gerais-de-acesso-e-utilizacao-do-servico-universal) – CTT, prestataire du service postal universel au Portugal
- [CTT : Empresas do grupo](https://www.ctt.pt/grupo-ctt/a-empresa/quem-somos/empresas-do-grupo/) – CTT Express et CTT Expresso au sein du Grupo CTT
- [CTT : Prazo para levantar](https://www.ctt.pt/ajuda/particulares/receber/levantar-correspondencia-ou-encomendas-/prazo-para-levantar) – délais de retrait au Portugal (10 jours ouvrables depuis l'Espagne, 5 jours en Locky)
- [CTT : Collectt](https://www.ctt.pt/particulares/receber/locais-de-entrega-alternativos/collectt) – réseau Collectt au Portugal
- [CTT : Entrega não conseguida](https://www.ctt.pt/ajuda/particulares/receber/problemas-na-entrega/entrega-nao-conseguida) – suites d'une absence au Portugal
- [CTT : Condições gerais de transporte de Encomendas Expresso](https://www.ctt.pt/transversais/condicoes-gerais-transporte-encomendas-expresso) – délais de réclamation de 8 et 30 jours
- [CTT : Linhas de Apoio](https://www.ctt.pt/ajuda/particulares/apoio-ao-cliente/contactos/linhas-de-apoio) – téléphone et horaires au Portugal
- [CTT : Alertas de Phishing](https://www.ctt.pt/transversais/alertas-de-phishing) – faux SMS CTT Expresso
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – règles de détection, statuts `Recogerán en Delegación` et relais vers CTT Portugal dans Peek
:::
