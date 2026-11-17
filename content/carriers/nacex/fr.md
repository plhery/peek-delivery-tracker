---
title: NACEX suivi : numéro, statuts espagnols et contact
description: Suivre un colis NACEX depuis l'Espagne ou le Portugal : format 0100/00000005, sens des statuts en espagnol, points NACEX.shop, douane et contacts.
slug: nacex-suivi
published: 2026-11-17
updated: 2026-11-17
---

NACEX est une messagerie express du groupe Logista implantée en Espagne, au Portugal, en Andorre et au Benelux, qui expédie aussi vers le reste de l'Europe. Tu la croises surtout après un achat sur un site espagnol ou portugais, ou lors d'un séjour dans la péninsule Ibérique. Saisis ton numéro (quatre chiffres, barre oblique, huit chiffres) sur la [page d'accueil NACEX](https://www.nacex.es/irHome.do) ou colle-le ci-dessus : un numéro de commande ou un code postal seul ne suffit pas.

## Numéro de suivi NACEX : format et erreurs fréquentes

L'accueil affiche le modèle `0000/00000000`, et la [page de suivi NACEX](https://www.nacex.es/irSeguimiento.do) sépare *Agencia* et *Expedición*. Exemple fictif :

:::anatomy 0100 / 00000005
- 0100 | Agence : code à 4 chiffres de la franchise NACEX d'origine
- / | Barre oblique séparant l'agence et le bordereau
- 00000005 | Bordereau (*albarán*) : numéro à 8 chiffres de l'expédition
:::

NACEX ne précise pas dans quel message arrive ce code ni l'émetteur de ses SMS ; vois [comment trouver son numéro de suivi](guide:find-tracking-number) pour vérifier l'e-mail du marchand, ton compte client ou l'étiquette.

| Ce que tu as | Ce que c'est et comment le suivre |
| --- | --- |
| `0100/00000005` | Numéro NACEX complet : colle-le tel quel (les espaces comme `0100 / 00000005` sont acceptés en saisie directe). |
| `010000000005` | 12 chiffres sans barre : il manque sans doute `/` après le quatrième chiffre. Sans `/`, Peek ne propose pas NACEX et teste des transporteurs à [12 chiffres](guide:tracking-number-formats) comme [Colis Privé](carrier:colis-prive). Ajoute `/`. |
| `0100000005` | Jusqu'à 10 chiffres : probablement la « référence alternative » de l'étiquette, utilisable sur `nacex.es` uniquement avec le code agence/client de l'expéditeur ; Peek n'interroge pas NACEX avec ce format. |
| `3230 0000 0000 0000` | 16 chiffres (ou 23 chiffres) sans barre : [Correos Express](carrier:correos-express) ou un autre transporteur, pas NACEX. |

Vérifie aussi l'absence de lettre `O` à la place du chiffre `0`. Si le [formulaire externe NACEX](https://www.nacex.es/seguimientoFormularioExterno.do?intcli=0000/00000000) affiche *« No existe ningún albarán introducido en el sistema cumpliendo los criterios especificados. Consulte con su agencia NACEX más cercana »* (« Aucun bordereau enregistré ne correspond aux critères indiqués. Consultez votre agence NACEX la plus proche »), contrôle les quatre chiffres, la barre et les huit chiffres suivants, ou attends quelques heures après une création récente.

## Statuts de suivi NACEX : sens des libellés espagnols

Le site `nacex.es` propose un sélecteur de langue (dont le français), mais NACEX ne publie pas de lexique de ses statuts et Peek lit les libellés espagnols :

| Statut NACEX (espagnol) | Signification |
| --- | --- |
| `Notificado` | « Notifié » : annonce électronique liée à l'enlèvement, avant prise en charge physique. |
| `Aceptada` | « Accepté » : colis pris en charge en agence (suivi de `WEB`, simple enregistrement en ligne avant remise au transporteur). |
| `En tránsito` | « En transit » : acheminement entre franchises et plateformes NACEX. |
| `En reparto` | « En livraison » : le livreur a le colis pour sa tournée du jour. |
| `Ausente` | « Absent » : passage sans remise faute de personne présente. |
| `Gestión de servicio, posible demora` | « Gestion de service, retard possible » : aléa d'exploitation traité par l'agence (tournée inachevée, par exemple). |
| `Solución de entrega concertada` | Nouvelle livraison convenue après un incident. |
| `Solución de entrega en punto` | Redirection vers un point NACEX enregistrée, mais colis pas encore arrivé au point. |
| `Cambio de dirección` | « Changement d'adresse » : réacheminement vers une nouvelle adresse autorisée. |
| `Disponible en punto NACEX` | « Disponible en point NACEX » : colis prêt au retrait au point ou à l'agence. |
| `Contacta con agencia` | « Contacte l'agence » : la franchise de livraison attend ton appel ou message. |
| `Solucionado sin OK` | « Clôturé sans OK » : dossier clos sans remise, par exemple après retour à l'expéditeur. |
| `Entregado` | « Livré » : remise effectuée. |

Attention : `Solución de entrega concertada` et `Solución de entrega en punto` notent seulement la consigne ; attends `Disponible en punto NACEX` avant de te déplacer. `Sin estado` (« sans statut ») est une note administrative sans effet sur l'étape en cours, tandis que `Solucionado sin OK` invite à joindre NACEX ou le vendeur. Vois aussi [la signification des statuts de suivi](guide:tracking-statuses).

## Livraison NACEX, absence et points NACEX.shop

**Vers la France, la Belgique, la Suisse et l'Europe :** depuis l'Espagne et le Portugal, NACEX expédie par route en Europe via `Business Europa` (entreprises) et `Ecommerce Europa` (monocolis particuliers jusqu'à 40 kg à domicile ou 20 kg en point selon disponibilité), et par avion dans le monde (`Internacional exprés`) avec des partenaires internationaux.

**Absence à domicile :** selon les conditions 2026 de NACEX, le livreur laisse aux domiciles privés un avis pour convenir d'une seconde et définitive livraison (*segunda y definitiva entrega*). La FAQ précise « dans la mesure du possible » (*siempre que sea posible*) et indique que l'agence te rappelle si elle a ton téléphone. En cas d'absence prévue, préviens le service client ou ton agence.

**Pré-alerte et *Mi Entrega Preferida* :** sur les envois avec *Pre-Alerta*, un SMS ou e-mail ouvre *Mi Entrega Preferida* pour changer la date ou le créneau, ou rediriger un monocolis vers une agence ou un point NACEX.shop. *Entrega con PIN* remet le colis contre un code reçu dans la pré-alerte.

**NACEX.shop et consignes :** plus de 4 000 commerces et consignes (*Smart Lockers*) en Espagne et au Portugal, souvent ouverts le week-end. Un monocolis envoyé vers NACEX.shop est disponible dès le jour ouvrable suivant à 9 h 00 (ou 12 h 00 si le point ouvre plus tard) après notification ; cherche un point sur le [localisateur NACEX](https://www.nacex.es/irCerca.do). NACEX ne publie pas de durée maximale de garde en NACEX.shop ou consigne.

**Retrait en agence NACEX :** garde maximale de **15 jours** en franchise avant retour payant à l'expéditeur. Préviens l'agence avant de venir du lundi au vendredi (8 h 00–20 h 00 ou 8 h 00–14 h 00 et 16 h 00–20 h 00 selon le site). Un tiers doit présenter une **autorisation signée** et la **photocopie de ta pièce d'identité**.

**Samedis, fériés et refus :** samedis, dimanches et fériés ne sont pas ouvrables. La livraison du samedi (9 h 00–13 h 00) est une option payante dans les villes avec franchise NACEX et à 15 km autour (documents seuls vers Canaries et zones douanières). Un envoi parti ne s'annule plus, mais peut être refusé à la remise ou auprès de l'agence.

## Douane et TVA : France, Belgique, Suisse et zones spéciales

Entre l'Espagne continentale (ou les Baléares), le Portugal continental, la France et la Belgique, aucune douane ne s'applique dans la zone TVA de l'UE. Exceptions :

- **Suisse :** TVA suisse à l'importation de **8,1 %** (**2,6 %** taux réduit). Selon l'OFDF, impôts et droits **inférieurs à 5 CHF** ne sont pas perçus (jusqu'à 62 CHF taxables à 8,1 % ou 193 CHF à 2,6 %) ; au-delà s'ajoutent TVA, éventuels droits au poids et frais du transporteur (comme [La Poste suisse](carrier:swiss-post)) ; vois [frais de douane et d'importation](guide:customs).
- **Canaries, Ceuta, Melilla, Andorre et Gibraltar :** Canaries, Ceuta et Melilla sont hors zone TVA UE ; NACEX exige facture commerciale ou déclaration sur l'honneur et signale un délai douanier possible.

## Colis NACEX en retard, bloqué ou endommagé

Les réclamations formelles chez NACEX relèvent du client payeur (souvent la boutique) via sa franchise :

- **Suivi figé ou colis absent :** la FAQ renvoie vers `atencion.cliente@nacex.com` ou le `900 100 000`, sans délai d'attente minimal. Vois aussi [suivi qui ne se met pas à jour](guide:tracking-not-updating) et [`Entregado` mais non reçu](guide:delivered-not-received).
- **Retard :** réclamation par le client sous **31 jours calendaires** (*31 días naturales*) hors cause justifiée (NACEX signale de possibles tensions au Black Friday, à Noël ou en douane).
- **Perte, manquant ou avarie :** note les dégâts visibles **à la remise** ; le dossier avec photos et facture doit être déposé sous **7 jours calendaires** après livraison, selon l'article 60 de la loi espagnole *Ley 15/2009*.

Pour un achat en ligne dans l'UE, *Your Europe* rappelle que le vendeur reste responsable jusqu'à la remise physique (livraison sous **30 jours** sauf accord contraire) : préviens donc toujours le marchand.

## Contacter le service client NACEX

Canaux publiés sur les pages espagnoles de NACEX (sans horaires affichés ni WhatsApp) :

- **Téléphone (Espagne et Andorre) :** `900 100 000` (gratuit en Espagne depuis fixe et mobile selon NACEX et la CNMC ; souvent inaccessible depuis l'étranger, d'où il vaut mieux utiliser l'e-mail ou le chat).
- **E-mail :** `atencion.cliente@nacex.com` (et `shop@nacex.com` sur la page contact).
- **Chat en ligne :** sur la [page de contact NACEX](https://www.nacex.es/irContactenosContacto.do) (*« Chatee ahora con nuestros agentes »*).
- **Agences et réclamations :** adresses sur le [localisateur NACEX](https://www.nacex.es/irCerca.do) ; procédure client sur la [page réclamations NACEX](https://www.nacex.es/irReclamaciones.do) (examen initial sous 48 h ouvrables, clôture visée sous 7 jours calendaires selon le cas).

## Suivre un colis NACEX avec Peek

[Peek](/) reconnaît aussitôt le format à quatre chiffres, barre oblique et huit chiffres (`0100/00000005`) sous « Transporteur détecté » (modifiable via « Changer »), ainsi qu'un lien `nacex.es` avec `intcli=` ou l'e-mail d'expédition complet.

Peek vérifie jusqu'à toutes les 10 minutes, et jusqu'à toutes les 2 minutes dès 6 h 00 (heure locale) lorsque le colis passe « En livraison », tant que NACEX répond directement (via des services de suivi, jusqu'à toutes les 15 minutes). Après deux jours sans nouveauté — ou 12 heures sur « Étiquette créée » ou « Pris en charge » — le relevé devient horaire. Alertes au choix : « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Peek traduit l'étape, affiche la livraison estimée en route et place chaque scan sur une carte ; NACEX ne donnant l'heure qu'à la livraison, les étapes précédentes affichent la date. Peek laisse de côté destinataire, preuve de remise, commentaires internes, contacts et référence marchand, et ne peut ni modifier la livraison ni joindre NACEX pour toi.

## Questions fréquentes sur le suivi NACEX

### Qu'est-ce que NACEX et est-ce un transporteur fiable ?

Oui. Créée en 1995 au sein du groupe Logista (DRONAS 2002, S.L.U.), NACEX compte plus de 300 franchises et 35 plateformes en Espagne, au Portugal, en Andorre et aux Pays-Bas/Benelux, avec les domaines officiels `nacex.es`, `nacex.pt`, `nacex.com` et des courriels en `@nacex.com`.

### Quels sont les délais de livraison de NACEX ?

En e-commerce ibérique, NACEX annonce 24 h (`e-NACEX`, `e-NACEX.shop`) ou 48 à 72 h (`e-NACEX economy`). `NACEX 19:00 H` livre avant 19 h 00 le jour ouvrable suivant en Espagne péninsulaire, Andorre, Portugal et Baléares, ou avant 13 h 00 le deuxième jour ouvrable à plus de 50 km de la franchise. Par avion, compte 24 à 48 h vers les Canaries et 2 à 5 jours vers les Açores et Madère ; en `Ecommerce Europa`, le délai dépend du pays.

### Puis-je changer l'adresse de livraison d'un colis NACEX ?

Changer d'adresse postale demande l'accord du client payeur (souvent la boutique) : sans supplément dans la même franchise NACEX, payant vers une autre franchise. Avec une pré-alerte, *Mi Entrega Preferida* permet en revanche de rediriger un monocolis vers un point NACEX.shop ou une agence NACEX.

### Pourquoi mon numéro NACEX à 12 chiffres n'est-il pas reconnu ?

Parce que NACEX exige la barre oblique après les quatre premiers chiffres (`0100/00000005`). Sans `/`, NACEX rejette la saisie et Peek interroge des transporteurs à 12 chiffres comme [Colis Privé](carrier:colis-prive) : insère `/` après le quatrième chiffre.

:::sources
- [NACEX : Accueil](https://www.nacex.es/irHome.do) – suivi, Logista, réseau, langues
- [NACEX : Seguimiento](https://www.nacex.es/irSeguimiento.do) – agence/bordereau, référence alternative
- [NACEX : Formulario de Seguimiento](https://www.nacex.es/seguimientoFormularioExterno.do?intcli=0000/00000000) – envoi introuvable
- [NACEX : FAQs](https://www.nacex.es/irFaqs.do) – contact, 15 jours, procuration, adresse, refus
- [NACEX : FAQ entregas](https://www.nacex.es/visualizarfaq.do?category=900) – avis, retrait agence
- [NACEX : FAQ servicios](https://www.nacex.es/visualizarfaq.do?category=901) – samedi 9 h–13 h, pré-alerte
- [NACEX : Contacto](https://www.nacex.es/irContactenosContacto.do) – téléphone, e-mail, chat
- [NACEX : Reclamaciones](https://www.nacex.es/irReclamaciones.do) – 31 et 7 jours, 48 h ouvrables
- [NACEX : Servicios internacionales](https://www.nacex.es/irServicioNacional.do?seccion=internacionales) – Europe, 40 kg / 20 kg
- [NACEX : Servicios eCommerce](https://www.nacex.es/irServicioNacional.do?seccion=ecommerce) – 24 h, 48/72 h
- [NACEX : Servicios urgentes](https://www.nacex.es/irServicioNacional.do?seccion=urgentes) – 19:00 H, Canaries
- [NACEX : Servicios Portugal](https://www.nacex.es/irServicioNacional.do?seccion=portugal) – Açores, Madère
- [NACEX : Mi Entrega Preferida](https://www.nacex.es/irServicioNacional.do?seccion=mep) – pré-alerte, consignes
- [NACEX : Servicios de valor añadido](https://www.nacex.es/irServicioValor.do) – Entrega con PIN
- [NACEX : NACEX.shop](https://www.nacex.es/irEnvioSostenible.do) – dès 9 h 00 / 12 h 00
- [NACEX : Localizador de puntos](https://www.nacex.es/irCerca.do) – points et agences
- [NACEX Blog : NACEX.shop](https://blog.nacex.es/2025/las-entregas-en-puntos-nacex-shop-se-duplican-en-el-ultimo-ano/) – plus de 4 000 points
- [NACEX : Quiénes somos](https://www.nacex.es/irEmpresaQuienes.do) – 1995, Logista, Pays-Bas
- [NACEX : Tramitaciones aduaneras](https://www.nacex.es/irTramitacionesAduaneras.do?seccion=tramaduan) – douane
- [NACEX : Condiciones de transporte 2026](https://www.nacex.es/pages/img/servicios/condiciones_tramitaciones/condiciones-generales-2026-ES.pdf) – seconde livraison, 50 km, 15 km
- [BOE : Ley 15/2009](https://www.boe.es/buscar/act.php?id=BOE-A-2009-18004) – 7 jours (art. 60)
- [CNMC : Numéros 800 et 900](https://blog.cnmc.es/2019/04/24/llamadas-a-numeros-800-y-900-gratis-para-el-consumidor-y-quien-paga/) – 900 gratuits
- [Your Europe : Livraison](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_fr.htm) – 30 jours, responsabilité vendeur
- [Your Europe : TVA](https://europa.eu/youreurope/citizens/consumers/shopping/vat/index_fr.htm) – zone TVA UE
- [OFDF : Lettres et paquets](https://www.bazg.admin.ch/fr/reception-de-lettres-et-paquets) – 8,1 % / 2,6 %, moins de 5 CHF
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – ce que lit Peek
:::
