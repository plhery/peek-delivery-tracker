---
title: Suivi J&T Express China : numéros JT, statuts et contact
description: Suis un colis J&T Express China (极兔速递) en Chine continentale : format JT, sens de 已签收 et des statuts chinois, 4 derniers chiffres du téléphone et Peek.
slug: suivi-jt-express-china
published: 2026-11-21
updated: 2026-11-21
---

J&T Express China (`极兔速递`) est le réseau en Chine continentale du groupe J&T Express, actif depuis mars 2020. Un numéro J&T China correspond le plus souvent à un colis circulant en Chine continentale, par exemple une commande en ligne envoyée chez un destinataire local ou vers l'entrepôt d'un transitaire. Suis-le sur [le traceur chinois de J&T](https://www.jtexpress.com.cn/service.html) ou dans le champ ci-dessus ; le service international propre de J&T China (*J&T International Standard Express*) ne dessert par avion au départ de la Chine continentale que sept pays d'Asie du Sud-Est, de sorte que sur un achat à destination de l'Europe ou de l'Amérique du Nord, un numéro J&T China peut ne couvrir que le trajet intérieur en Chine continentale jusqu'au transitaire.

## Numéro de suivi J&T Express China : à quoi il ressemble

J&T l'appelle numéro de bordereau (`运单号`). Peek reconnaît un format pour J&T Express China (exemples inventés) :

| Numéro (inventé) | Ce que c'est |
| --- | --- |
| `JT4829105736491` | `JT` suivi de 13 chiffres : J&T Express (réseau d'Asie du Sud-Est du groupe) partageant exactement ce format dans le catalogue de Peek, Peek ne désigne pas J&T China sur la seule forme du numéro (voir ci-dessous). |
| `0148291057364` | Les 13 chiffres sans `JT` : le traceur de J&T ajoute lui-même `JT`, mais dans Peek ces 13 chiffres correspondent à d'autres transporteurs comme [DPD France](carrier:dpd-fr) ou Yunda, pas à J&T. Garde bien les lettres `JT`. |

Minuscules, espaces et tirets n'ont pas d'importance dans Peek.

**Où le trouver.** Le vendeur le communique dans les détails d'expédition de ta commande, et il figure sur l'étiquette collée sur le colis ([comment trouver son numéro de suivi](guide:find-tracking-number)).

**Sur le site de J&T.** Sur la page `运单查询` (`jtexpress.com.cn/service.html`), saisis jusqu'à 10 numéros séparés par des espaces ou Entrée. Pour protéger la vie privée, J&T peut demander un test visuel ou les quatre derniers chiffres du téléphone de l'expéditeur ou du destinataire enregistrés sur l'envoi. Ses conditions de bordereau électronique garantissent à l'expéditeur en Chine continentale un suivi pendant un an à compter de l'envoi.

## Statuts de suivi J&T Express China expliqués

Le traceur de J&T gère les statuts suivants, dans l'ordre habituel du trajet :

| J&T indique | Ce que cela veut dire pour toi | Peek affiche |
| --- | --- | --- |
| `已揽件` | Collecté : J&T a pris le colis en charge chez l'expéditeur en Chine continentale. | « Pris en charge » |
| `运送中` | En cours d'acheminement dans le réseau J&T. | « En transit » |
| `发往代理点` | Envoyé vers un point relais partenaire (`代理点`), affiché sur le site avec l'icône de livraison. | « En transit » |
| `派件中` | En tournée de livraison avec un livreur. | « En livraison » |
| `已代收` | Reçu en ton nom par un tiers tel qu'un point de retrait (`代收点`) : va le récupérer. | « Prêt à être retiré » |
| `已签收` | Signé / livré : remis à toi ou pour ton compte, avec l'heure (`签收时间`) et, lorsqu'elle existe, une photo (`查看签收图片`). | « Livré » |
| `已拒收` | Refusé par le destinataire. | « Problème signalé » |
| `已退回` | Retourné à l'expéditeur, avec l'heure de retour (`退回时间`). | « Retourné à l’expéditeur » |
| `已取消` | Envoi annulé. | « Problème signalé » |

En cas d'anomalie, le traceur de J&T peut aussi afficher un encadré d'alerte logistique avec l'explication de J&T. Peek affiche ces intitulés chinois dès que tu renseignes les quatre derniers chiffres du téléphone ; sans eux, il affiche les étapes fournies par les services de suivi.

**Envoyé vers un transitaire ?** Alors `已签收` signifie que l'entrepôt du transitaire en Chine continentale a réceptionné le colis : la mission de J&T est terminée, et la suite se suit avec le numéro propre du transitaire.

## Livraison en Chine continentale : livreurs, points de retrait et consignes

Les conditions de bordereau électronique de J&T en Chine continentale prévoient quatre modes de remise :

- en main propre au destinataire ;
- à une personne vivant sous le même toit ou désignée par le destinataire ;
- dans un point de collecte (`代收点`) ou une consigne automatique (`自提柜`) du secteur de l'adresse ;
- selon toute autre modalité convenue avec l'expéditeur ou le destinataire.

À ta demande ou avec ton accord, le livreur peut aussi déposer le colis à une autre adresse indiquée ou dans un espace colis mis en place par l'administration locale, le comité de résidents ou la gestion de l'immeuble ; dès lors que l'emballage extérieur est intact, la prestation est réputée accomplie. En Chine continentale, les mesures d'administration du marché de la livraison express (`快递市场管理办法`, en vigueur depuis le 1er mars 2024) imposent l'accord préalable du destinataire avant tout dépôt en consigne intelligente ou station colis, interdisent de valider la réception sans cet accord et autorisent à vérifier le colis à la remise (et à l'ouvrir ou le refuser si l'emballage est visiblement endommagé ou le contenu fragile).

## Colis J&T bloqué, perdu ou retourné

**Rien pour l'instant.** Si le traceur de J&T répond `抱歉！未查到此运单` (bordereau introuvable), vérifie le numéro auprès du vendeur. En attendant, Peek affiche « Aucun historique de suivi pour le moment » tant qu'aucun transporteur n'est retenu, ou « En attente de J&T Express China » une fois classé sous J&T Express China. Sans les quatre derniers chiffres du téléphone, si aucun service de suivi ne trouve non plus le colis, Peek affiche « Mise à jour indisponible » dès la deuxième vérification, avec « Compléter les informations de suivi » pour saisir les chiffres.

**Aucune évolution.** Après quatre jours sans nouvelle étape en transit, Peek affiche le jour de la dernière étape : « Aucune mise à jour depuis lun 12 oct » (avec le lien « Demande de l’aide à J&T Express China »). Vois [pourquoi le suivi ne bouge plus](guide:tracking-not-updating) et [suivre un colis venu de Chine](guide:tracking-from-china).

**Perdu, endommagé ou retourné en Chine continentale.** Contacte d'abord le vendeur qui a réservé J&T. Selon les conditions de bordereau de J&T (applicables uniquement aux envois collectés et livrés en Chine continentale), l'expéditeur dispose d'un délai de réclamation de 30 jours avec justificatif de valeur : sans assurance (`保价`), J&T indemnise la perte réelle dans la limite de dix fois les frais de port ; avec assurance, jusqu'à la valeur déclarée ; en cas de retard, seuls les frais de port sont exonérés ([colis perdu](guide:lost-parcel)). En cas d'adresse imprécise, de refus (`已拒收`) ou d'échec non imputable à J&T, le colis repart aux frais de l'expéditeur ([colis retourné à l'expéditeur](guide:returned-to-sender)).

**Indiqué livré mais introuvable.** Vérifie les points de retrait, consignes, l'accueil de l'immeuble et l'entrepôt du transitaire éventuel, puis appelle le `956025` ou contacte le service en ligne avec le numéro de bordereau. En Chine continentale, si la réponse de J&T ne suffit pas ou tarde, la réglementation permet de saisir l'administration postale en médiation ([colis indiqué livré mais non reçu](guide:delivered-not-received)).

## Service client et contact J&T Express China

Sur `jtexpress.com.cn` (en Chine continentale) :

- **Ligne téléphonique** : `956025` (`服务热线`), avec conseillers de `08:30` à `20:00` en Chine continentale.
- **Service client en ligne** (`在线客服` sur `jtexpress.com.cn`) : conseillers de `08:30` à `21:00`. J&T mentionne séparément un assistant automatisé (`智能客服`) disponible `00:00–24:00`.
- **E-mail** : `jtservice@jtexpress.com` (`服务邮箱`).
- **WeChat** : mini-programme `极兔速递+` pour expédier et consulter davantage de détails.

**Attention aux SMS frauduleux.** Les domaines officiels de J&T Express China sont `www.jtexpress.com.cn` et `jtexpress.com.cn`. Avant de suivre un lien reçu par message, consulte [reconnaître les SMS de livraison frauduleux](guide:delivery-scam-texts). Pour ta commande, adresse-toi d'abord au vendeur.

## Suivre un colis J&T Express China avec Peek

Le format `JT` à 13 chiffres étant partagé avec J&T Express en Asie du Sud-Est, colle-le quand même dans [Peek](/) (qui suit plus de 4 500 transporteurs) :

- **Collé seul.** Peek affiche « Vérification des services de suivi… » : si les services s'accordent sur J&T Express China avec des étapes datées, Peek y classe le colis ; si un historique apparaît sans transporteur tranché, tu vois « Historique trouvé. Peek confirmera le transporteur après l’ajout. » ; sinon, « pas encore trouvé · Peek continue de vérifier ».
- **Choisi manuellement.** Touche « Changer » et sélectionne J&T Express China sous « Correspond à ce numéro » ou en cherchant `J&T` ou `极兔` sous « Tous les transporteurs » (ou « Changer de transporteur » sur un colis déjà enregistré, notamment s'il a été classé sous J&T Express).
- **Via un lien.** Un lien `jtexpress.com.cn/service.html?indexIpt=…` classe d'emblée l'envoi sous J&T Express China (« Transporteur détecté »).

**Avec ou sans les 4 derniers chiffres du téléphone.** Saisis-les dans « 4 derniers chiffres du numéro de téléphone » à l'ajout ou via « Ajouter les chiffres » sur la fiche : Peek les conserve uniquement pour ce colis, ne les envoie qu'à J&T et lit alors directement le traceur de J&T (statuts chinois, heures de Pékin `UTC+8` converties dans ton fuseau avec « Heures dans ton fuseau horaire : … », sans lieu, en retirant noms de livreurs/signataires, téléphones et photos). Sans ces chiffres (ou s'ils ne correspondent pas), Peek interroge tour à tour ParcelsApp, Ship24 et 17TRACK, dans un ordre qui varie ; le bouton « Ouvrir le site J&T Express China » devient alors celui du service qui a répondu, et si ce service nomme un transporteur final pris en charge avec son numéro, Peek bascule dessus dès qu'il affiche un scan au moins aussi récent.

**Rythme et alertes.** Tant que le traceur de J&T répond directement, Peek vérifie jusqu'à toutes les 10 minutes avec le colis ouvert ou le jour de livraison avec alertes actives, et jusqu'à toutes les 2 minutes dès 6 h (heure de Pékin) le jour en « En livraison » (sinon, et après deux jours sans nouveauté ou 12 h sur « Étiquette créée » / « Pris en charge », toutes les heures). Lorsqu'un service de suivi répond, Peek vérifie toutes les heures, toutes les 3 heures après un jour sans étape et jusqu'à toutes les 15 minutes le jour de la livraison si le colis est ouvert ou que les notifications sont activées. Pour un retrait en point ou consigne (`已代收`), choisis « Étapes importantes » ou « Chaque scan » plutôt que « Livraison seulement ». Peek ne peut contacter ni J&T, ni le transitaire, ni le vendeur pour toi.

## Questions fréquentes sur le suivi J&T Express China

### J&T Express China est-il fiable ?

Oui. Le groupe J&T Express a été fondé en Indonésie en août 2015, a démarré ses activités en Chine continentale en mars 2020, a racheté l'activité express de Best Inc en Chine continentale en octobre 2021, est coté au marché principal de la Bourse de Hong Kong depuis octobre 2023 et couvre 13 pays.

### À quoi ressemble un numéro de suivi J&T Express China ?

Aux lettres `JT` suivies de 13 chiffres, comme `JT4829105736491`. Conserve le préfixe `JT` dans Peek : les 13 chiffres seuls pointent vers d'autres transporteurs.

### Que signifie `已签收` sur un suivi J&T ?

Signé / livré : remis à toi, à un proche, à une personne désignée, à un point relais ou consigne, ou à l'entrepôt d'un transitaire en Chine continentale. `已代收` est distinct : un point de collecte a réceptionné le colis pour toi et tu dois encore aller le retirer.

### J&T Express China livre-t-il en Europe ou en Amérique du Nord ?

Pas avec son propre service international : *J&T International Standard Express* vole depuis la Chine continentale uniquement vers la Malaisie, le Viêt Nam, Singapour, la Thaïlande, les Philippines, l'Indonésie et le Cambodge. Vers l'Europe ou l'Amérique du Nord, un numéro J&T China peut ne couvrir que le préacheminement en Chine continentale jusqu'à un transitaire.

### Quel est le numéro de téléphone de J&T Express China ?

Le `956025`, sa ligne de service client en Chine continentale, où des conseillers répondent de `08:30` à `20:00` (et jusqu'à `21:00` sur le chat en ligne `在线客服`).

:::sources
- [J&T Express China : 极兔速递 (accueil)](https://www.jtexpress.com.cn/) – fondation en 2015, présence dans 13 pays, ligne 956025 et e-mail
- [J&T Express China : 运单查询 (recherche de bordereau)](https://www.jtexpress.com.cn/service.html) – champ jusqu'à 10 numéros, vérification à 4 chiffres, statuts chinois, heure et photo de signature, avis d'anomalie
- [J&T Express China : 关于极兔 (à propos)](https://www.jtexpress.com.cn/aboutUs.html?type=JT) – lancement en Chine continentale en mars 2020, rachat de Best Inc en octobre 2021, cotation à Hong Kong en octobre 2023
- [J&T Express China : 联系我们 (contact)](https://www.jtexpress.com.cn/aboutUs.html?type=LX) – ligne 956025, e-mail jtservice@jtexpress.com et service en ligne
- [J&T Express China : 服务时间 (horaires)](https://www.jtexpress.com.cn/serviceTime.html) – horaires humains 08:30–20:00 / 08:30–21:00 et assistant 00:00–24:00
- [J&T Express China : 电子运单契约条款 (conditions de bordereau électronique)](https://www.jtexpress.com.cn/electronicContract.html) – quatre modes de remise, suivi sur un an, retours, plafonds d'indemnisation et délai de 30 jours en Chine continentale
- [J&T Express China : J&T International Standard Express](https://www.jtexpress.com.cn/internationalv3.html) – sept pays d'Asie du Sud-Est desservis depuis la Chine continentale
- [Ministère des Transports de Chine : 快递市场管理办法](https://xxgk.mot.gov.cn/gz/202401/t20240104_3980684.html) – accord préalable pour consignes et stations, vérification à la remise et recours postal en Chine continentale
- [Universal Parcel Scraper : J&T Express China](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/j-and-t-cn) – format JT, alias, lien indexIpt=, 4 derniers chiffres du téléphone, heure de Pékin et statuts
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – partage du format JT avec J&T Express, transporteurs à 13 chiffres, ordre des services de suivi et relais
:::
