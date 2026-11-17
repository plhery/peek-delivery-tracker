---
title: Suivi Correos Express : numéro, statuts et contact
description: Suivre un colis Correos Express depuis l'Espagne ou le Portugal : numéro à 16 ou 23 chiffres, sens des statuts en espagnol, Punto Correos et contact.
slug: correos-express-suivi
published: 2026-11-17
updated: 2026-11-17
---

Correos Express (ex-Chronoexprés, société *Correos Express Paquetería Urgente, S.A., S.M.E.*) est la filiale de livraison express du groupe espagnol Correos. Elle dessert toute l'Espagne et le Portugal et expédie vers l'Europe et le monde via ses offres internationales ; ce n'est pas Correos, l'opérateur postal public espagnol. Saisis ton numéro sur la [page de suivi espagnole de Correos Express](https://s.correosexpress.com/) ou colle-le ci-dessus. Si tu n'as pas encore de numéro, demande-le au vendeur qui a enregistré l'envoi.

## Correos ou Correos Express : quel transporteur livre ton colis ?

Correos assure le service postal universel en Espagne, tandis que Correos Express gère les colis urgents. Ils partagent les bureaux de poste et les relais *Punto Correos*, mais leurs suivis et formats diffèrent :

- **Uniquement des chiffres (16 ou 23 chiffres) :** code Correos Express (le format à 16 chiffres est aussi partagé par d'autres transporteurs, voir ci-dessous). Colle-le dans le champ ci-dessus.
- **Lettres et chiffres :** envoi Correos (la poste espagnole), à 13 caractères finissant par `ES`, `PR` + 15 chiffres + `C`, ou 16/23 caractères encadrés par des lettres. Utilise le [suivi en anglais de Correos](https://www.correos.es/es/en/tools/tracker/items).

## Numéro de suivi Correos Express : formats à 16 et 23 chiffres

Correos Express ne détaille pas la structure de son *número de envío*. Peek reconnaît deux formats numériques dotés d'une clé GS1 (exemples inventés) :

| Exemple de numéro | Transporteur et comportement de Peek |
| --- | --- |
| `3230 0000 0000 0000` | **16 chiffres** (clé GS1 valide, ne commençant pas par six zéros) : Correos Express, mais aussi DHL eCommerce, [Postes Canada](carrier:canada-post), [TNT](carrier:tnt) et [Evri](carrier:evri). Peek interroge les cinq transporteurs au premier contrôle. |
| `32300000000000001000007` | **23 chiffres** (clé GS1 valide) : propre à Correos Express, hors `000` aux positions 15–17 (Australia Post). |
| `RR100000003ES` | **Lettres et chiffres** au [format postal international](guide:tracking-number-formats) : Correos, pas Correos Express. |
| `0100/00000005` | **4 chiffres, barre oblique, 8 chiffres** : [NACEX](carrier:nacex), autre expressiste ibérique. |

Colle **uniquement le numéro** (espaces, points ou tirets sont retirés), pas un lien `s.correosexpress.com` ni l'e-mail entier : ce format numérique étant partagé, Peek ne l'extrait pas d'une URL ou d'un texte long. Un chiffre erroné invalide la clé GS1 et écarte Correos Express sur 16 chiffres (sélection manuelle possible via « Changer »). Correos Express n'indique pas d'identifiant SMS ; vois [comment trouver son numéro de suivi](guide:find-tracking-number).

Face au message *« Lo sentimos, no se ha encontrado ningún envío con el número indicado »*, vérifie le numéro et patiente quelques heures pour un envoi récent. Sans évolution après 24 à 48 heures, contacte le vendeur.

## Statuts Correos Express : traduire le suivi espagnol en français

Le suivi n'existe qu'en espagnol et sur la [page portugaise](https://s.correosexpress.com/SeguimientoSinCP/search-pt). Peek interroge le site espagnol et traduit ces statuts :

| Statut Correos Express | Signification pour ton colis |
| --- | --- |
| `INFORMADO` | Envoi enregistré par l'expéditeur, non encore remis à Correos Express. |
| `ADMITIDO` | Colis pris en charge et parti de l'agence d'origine (`ADMITIDO EN OFICINA DE CORREOS` lors d'un dépôt postal). |
| `EN RUTA A LOCALIDAD DE DESTINO` | En route vers l'agence locale de livraison ; *Gestionar entrega* est généralement actif. |
| `EN DESTINO` | « À destination » : arrivé à l'agence de distribution locale. |
| `EN REPARTO` | En tournée avec le chauffeur ; les options de livraison flexible sont verrouillées. |
| `Su envío no ha podido ser entregado` | « Votre envoi n'a pas pu être livré » : tentative infructueuse. |
| `NUEVO REPARTO` | « Nouvelle tournée » : nouvelle mise en livraison prévue. |
| `CAMBIO DE DESTINO` | « Changement de destination » : redirection vers une autre adresse ou un relais. |
| `ESTACIONADO` | « En attente » : selon la FAQ portugaise, livraison non aboutie et colis gardé dans les locaux Correos Express. |
| `EN ALMACÉN` / `EN GESTIÓN` | « En entrepôt » / « En traitement » : retenu en agence pour traiter un incident. |
| `ENTREGADO EN PUNTO DE CONVENIENCIA` | Déposé au relais *Punto Correos*, mais **pas encore prêt** au retrait client. |
| `DISPONIBLE EN PUNTO DE CONVENIENCIA` | Prêt au retrait en point relais avec ton code PIN. |
| `ENTREGADO` | Livré à toi ou à un tiers habilité. |
| `DEVUELTO` | Retourné à l'expéditeur (y compris après expiration du délai en relais). |

**Piège fréquent :** `ENTREGADO EN PUNTO DE CONVENIENCIA` indique seulement la remise du chauffeur au commerçant. Attends `DISPONIBLE EN PUNTO DE CONVENIENCIA` et ton PIN avant de te déplacer. L'aide espagnole ne définit que `INFORMADO`, `ADMITIDO`, `EN RUTA A LOCALIDAD DE DESTINO`, `EN REPARTO` et `ENTREGADO` (`ESTACIONADO` figure dans la FAQ portugaise) ; les autres statuts suivent le sens littéral lu par Peek (voir [que signifient les statuts de suivi](guide:tracking-statuses)).

## Livraison en Europe, modification et retrait Punto Correos

**Depuis l'Espagne vers la France, la Belgique, la Suisse et le monde :** Correos Express propose *Internacional Estándar* (routier monocolis en Europe, délai indicatif de 2 à 3 jours ouvrés vers les principaux pays de l'UE) et *Internacional Express* (aérien vers plus de 180 pays, 1 à 2 jours ouvrés indicatifs en Europe selon destination, jusqu'à deux tentatives et suivi en ligne). Week-ends et jours fériés des pays traversés sont exclus.

**En Espagne :** avant `EN REPARTO`, si l'expéditeur l'autorise, **Gestionar entrega** permet de choisir un *Punto Correos*, une autre adresse ou une autre date. *Paq 24* et *Paq 14* incluent deux tentatives gratuites ; après un échec, [Solicita nueva entrega](https://s.correosexpress.com/SeguimientoSinCP/saveNewDelivery) propose un nouveau jour (matin ou après-midi), un bureau Correos ou un relais, et *Paq e-Commerce* peut être déposé au *Punto Correos* libre le plus proche. En contre-remboursement (*contra reembolso*), Correos Express recommande de préparer l'appoint en espèces.

**Au Portugal :** après une absence, le second passage est automatique et une notification propose un autre jour, une autre adresse du même district ou un *Ponto Correos*. Sans réclamation sous **3 jours ouvrables**, le colis est retourné à l'expéditeur.

**Délais de garde et PIN :**

- **Punto Correos (Espagne) :** **7 jours calendaires** avant retour automatique. Présente le PIN reçu par SMS/e-mail ou affiche-le via **Visualizar PIN** sur le suivi après authentification.
- **Bureau Correos (*Entrega en oficina de Correos*, Espagne) :** sur *Paq 24* et *Baleares Express* (hors contre-remboursement), jusqu'à **15 jours calendaires** et trois SMS (arrivée, 5e et 10e jour).
- **Portugal (*Ponto Correos* et consignes) :** jusqu'à **7 jours** en relais ou **3 jours** en consignes *CitiBox* et *CityPaq* ; retrait par toute personne munie du PIN à usage unique.

## Douane, territoires spéciaux et faux SMS (*smishing*)

Entre l'Espagne péninsulaire (ou les Baléares) et la France ou la Belgique, les envois circulent sans douane dans la zone TVA de l'UE. Des taxes et formalités s'appliquent en revanche hors de ce périmètre :

- **Suisse :** TVA d'importation de **8,1 %** (**2,6 %** taux réduit). Selon l'OFDF, droits et taxes **inférieurs à 5 CHF** ne sont pas perçus (jusqu'à 62 CHF à 8,1 % ou 193 CHF à 2,6 %) ; au-delà s'ajoutent TVA, droits éventuels au poids et frais du transporteur (comme [La Poste suisse](carrier:swiss-post)). Voir [la douane et les frais d'importation](guide:customs).
- **Canaries, Ceuta, Melilla, Andorre, Gibraltar, Açores, Madère et hors UE :** Canaries, Ceuta et Melilla sont hors zone TVA UE. Les envois vers les Canaries, Andorre, Ceuta, Melilla ou Gibraltar exigent une facture commerciale ou proforma (plus une déclaration sur l'honneur signée pour les Canaries) et peuvent entraîner taxes locales et frais douaniers non inclus ; les envois aériens vers les Açores et Madère ainsi que ceux vers le Royaume-Uni depuis fin 2020 requièrent aussi des documents douaniers (*Internacional Express* dédouane hors UE).

> **Attention aux faux SMS (*smishing*) :** sur *Alerta de seguridad*, Correos Express précise ne **jamais demander tes coordonnées bancaires ni tes identifiants web** et ne **gérer aucun numéro surtaxé en 807** ; en cas de doute, passe par `www.correosexpress.es` ou le `900 122 333`. En mars 2022, l'INCIBE a signalé des SMS frauduleux imitant Correos et Correos Express qui réclamaient de faux frais de douane ou d'envoi via un lien piégé. Même si de vrais frais existent (Suisse, hors UE, Canaries, Ceuta, Melilla) ou un contre-remboursement à la porte, n'ouvre aucun lien reçu par SMS : vérifie ton numéro sur `s.correosexpress.com` et préviens ta banque si tu as saisi ta carte.

## Colis en retard, non reçu ou endommagé

Le contrat lie Correos Express à l'expéditeur et une seule réclamation est admise par envoi ; agis donc auprès du vendeur :

- **Suivi figé :** sans mise à jour après **24 à 48 heures**, contacte l'expéditeur ; voir [suivi qui ne se met pas à jour](guide:tracking-not-updating).
- **Échec signalé alors que tu étais présent :** vérifie le motif sur le suivi (accès ou adresse incomplète) et reprogramme la livraison.
- **Indiqué `ENTREGADO` mais absent :** en Espagne, vérifie le suivi puis contacte la boutique ; au Portugal, écris aussi à `destinatarioscep@correosexpress.com`. Voir [colis indiqué livré mais non reçu](guide:delivered-not-received).
- **Avarie ou manquant :** note des réserves écrites à la livraison si le dommage est visible. Pour un dommage non apparent, écris sous **7 jours calendaires** en Espagne (**21 jours** pour retard), ou en international sous **7 jours par route** et **14 jours par avion** (au Portugal : **8 jours** aux conditions générales et photos à l'expéditeur sous **7 jours** selon la FAQ).
- **Refus ou `DEVUELTO` :** Correos Express avise l'expéditeur. En achat UE, *Your Europe* rappelle que le vendeur reste responsable jusqu'à la remise physique (sous **30 jours** sauf accord contraire).

## Contacter Correos Express

L'assistance fonctionne uniquement en espagnol et en portugais :

- **Espagne :** `900 122 333` (sans horaires publiés ; gratuit en Espagne selon la CNMC, souvent bloqué depuis l'étranger) et fixe madrilène `913 277 020` (`+34 913 277 020` depuis l'étranger au tarif international), ainsi que le [formulaire client](https://www.correosexpress.es/es/atencion-al-cliente/contacto/atencion-al-cliente) et `@Cex_responde` sur X.
- **Portugal :** `229 762 810` (tarif fixe national), lun.–ven. 08 h 30–19 h 00, et `destinatarioscep@correosexpress.com` pour les destinataires.

## Suivre un colis Correos Express avec Peek

Sur 16 chiffres, [Peek](/) interroge les cinq transporteurs compatibles ; un 23 chiffres valide cible Correos Express seul (hors plage `000` d'Australia Post). Si Correos Express associe un code colis à 23 chiffres à son numéro d'envoi à 16 chiffres, Peek l'affiche sous « Correos Express le suit sous le numéro ». En consultant à la fois Correos Express et le localisateur Correos, Peek conserve l'historique même quand le site de Correos Express masque un ancien colis livré.

Peek vérifie jusqu'à toutes les 10 minutes, et jusqu'à toutes les 2 minutes dès 6 h 00 locales le jour « En livraison » tant que le suivi direct Correos Express répond (via des services de suivi, jusqu'à toutes les 15 minutes). Après deux jours sans nouveauté — ou 12 heures sur « Étiquette créée » ou « Pris en charge » — le relevé devient horaire. Choisis des alertes pour « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Peek traduit le statut, affiche la livraison prévue en transit et place chaque scan sur une carte ; les heures d'Espagne péninsulaire, Baléares, Ceuta, Melilla et Portugal continental sont converties avec « Heures dans ton fuseau horaire : … » (Canaries et Açores restent en heure locale). Peek laisse de côté preuve de livraison, signataire, code PIN, coordonnées et notes libres du livreur, et ne contacte pas Correos Express pour toi.

## Questions fréquentes sur le suivi Correos Express

### Quelle est la différence entre Correos et Correos Express ?

Correos assure le service postal universel espagnol ; Correos Express (*Correos Express Paquetería Urgente, S.A., S.M.E.*) est la filiale express du groupe. Elles partagent *Punto Correos*, mais les numéros de 16 ou 23 chiffres relèvent de Correos Express et les codes alphanumériques comme `RR100000003ES` de Correos.

### Quels sont les délais et horaires de livraison de Correos Express ?

Sur la péninsule Ibérique, Correos Express annonce 24 heures (*Paq 24*), 24 à 48 heures entre l'Espagne et le Portugal continental, et le 2e jour ouvrable vers Ceuta, Melilla, Andorre et Gibraltar (option samedi *Entrega en sábados* sur *Paq 24* péninsulaire ; pas de livraison le dimanche). *Paq 10* et *Paq 14* livrent avant 10 h 00 ou 14 h 00 ; en Europe, compte à titre indicatif 2 à 3 jours ouvrés (*Internacional Estándar*) ou 1 à 2 jours ouvrés (*Internacional Express*). L'heure estimée dépend de l'historique de ton code postal.

### Pourquoi vois-je `ENTREGADO EN PUNTO DE CONVENIENCIA` sans code PIN ?

Ce statut confirme seulement le dépôt par le chauffeur au relais. Attends `DISPONIBLE EN PUNTO DE CONVENIENCIA` : tu recevras alors le PIN par SMS/e-mail (ou via **Visualizar PIN**) et auras 7 jours calendaires en Espagne pour retirer le colis.

### Peut-on contacter Correos Express en français ou en anglais ?

Non. Sites, suivis et service client existent uniquement en espagnol et en portugais. Depuis la France, la Belgique ou la Suisse, passe directement par le service client du vendeur.

:::sources
- [Correos Express : Seguimiento de envíos](https://www.correosexpress.es/es/atencion-al-cliente/seguimiento-envio) – statuts, délai 24–48 h, heure estimée
- [Correos Express : Gestiona tu entrega](https://www.correosexpress.es/es/atencion-al-cliente/seguimiento-envio/gestiona-tu-entrega) – Gestionar entrega, Paq e-Commerce
- [Correos Express : Punto Correos](https://www.correosexpress.es/es/atencion-al-cliente/seguimiento-envio/recogida-en-punto-correos) – PIN, Visualizar PIN, 7 jours
- [Correos Express : Otras consultas](https://www.correosexpress.es/es/atencion-al-cliente/seguimiento-envio/otras-consultas-entrega) – contre-remboursement, contacts
- [Correos Express : Sigue tu envío](https://s.correosexpress.com/) – suivi espagnol, erreur, 913 277 020
- [Correos Express : Solicita nueva entrega](https://s.correosexpress.com/SeguimientoSinCP/saveNewDelivery) – nouvelle livraison, bureau, relais
- [Correos Express : Atención al Cliente](https://www.correosexpress.es/es/atencion-al-cliente/contacto/atencion-al-cliente) – formulaire en ligne
- [Correos Express : Términos y condiciones](https://www.correosexpress.es/es/atencion-al-cliente/tyc-transporte) – délais de réserves, refus
- [Correos Express : Aviso Legal](https://www.correosexpress.es/es/legales/aviso-legal) – raison sociale
- [Correos Express : Alerta de seguridad](https://www.correosexpress.es/es/legales/alerta-de-seguridad) – alerte anti-fraude, 900 122 333
- [Correos Express : Accueil](https://www.correosexpress.es/) – Paq 10, Paq 14, Paq 24
- [Correos Express : Quiénes somos](https://www.correosexpress.es/es/sobre-correos-express/quienes-somos) – différence avec Correos
- [Correos Express : España y Portugal](https://www.correosexpress.es/es/atencion-al-cliente/envios-en-espana-y-portugal) – Portugal, zones spéciales, douanes
- [Correos Express : Envíos internacionales](https://www.correosexpress.es/es/atencion-al-cliente/envios-internacionales1) – Internacional Estándar, Royaume-Uni
- [Correos Express : Internacional Express](https://www.correosexpress.es/es/particulares/enviar/internacionales/internacional-express) – 180 pays, 1–2 jours
- [Correos Express : Internacional Express empresas](https://www.correosexpress.es/es/empresas/productos/internacional-express) – deux tentatives, dédouanement hors UE
- [Correos Express : Paq 24](https://www.correosexpress.es/es/particulares/enviar/peninsulares/paq-24) – deux tentatives, samedi
- [Correos Express : Paq 14](https://www.correosexpress.es/es/particulares/enviar/peninsulares/paq-14) – avant 14 h 00, deux tentatives
- [Correos Express : Entrega en oficina](https://clientes.correosexpress.com/web/correosexpress/entrega-en-oficina-de-correos) – 15 jours, 3 SMS
- [Correos Express : Réseau Punto Correos](https://www.correosexpress.es/es/sobre-correos-express/sala-de-prensa/2025/grupo-correos-lanza-su-red-de-puntos-de-conveniencia-y-ampliara-) – réseau du groupe
- [Correos : Tracker](https://www.correos.es/es/en/tools/tracker/items) – suivi Correos
- [Correos Express PT : Siga seu envio](https://s.correosexpress.com/SeguimientoSinCP/search-pt) – suivi portugais
- [Correos Express PT : FAQ](https://www.correosexpress.pt/pt/apoio-ao-cliente/perguntas-frequentes) – ESTACIONADO, 2e tentative, avaries
- [Correos Express PT : Apoio ao Cliente](https://www.correosexpress.pt/pt/apoio-ao-cliente/contacto/apoio-cliente) – 229 762 810, horaires, e-mail
- [Correos Express PT : Termos](https://www.correosexpress.pt/pt/apoio-ao-cliente/tec-transporte) – 3 jours ouvrables, 8 jours, PIN
- [Correos Express PT : Paq Ponto](https://www.correosexpress.pt/pt/empresa/enviar/peninsulares/paq-ponto) – 7 jours relais, 3 jours consigne
- [CNMC : Numéros 800 et 900](https://blog.cnmc.es/2019/04/24/llamadas-a-numeros-800-y-900-gratis-para-el-consumidor-y-quien-paga/) – gratuité des 900
- [INCIBE : Alerte smishing](https://www.incibe.es/ciudadania/avisos/en-circulacion-sms-fraudulentos-que-suplantan-correos-y-otras-empresas-de) – faux SMS
- [Your Europe : Livraison](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_fr.htm) – 30 jours, risque vendeur
- [Your Europe : TVA UE](https://europa.eu/youreurope/citizens/consumers/shopping/vat/index_fr.htm) – zone TVA UE
- [OFDF : Lettres et paquets](https://www.bazg.admin.ch/fr/reception-de-lettres-et-paquets) – TVA suisse, franchise 5 CHF
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – codes GS1 et lecture des statuts
:::
