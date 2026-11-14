---
title: Chronopost seguimiento: paquete desde Francia y estados
description: Sigue un paquete de Chronopost desde Francia a España: cómo es el número, qué significan los estados en francés, puntos Pickup, incidencias y contacto.
slug: chronopost-seguimiento
published: 2026-11-14
updated: 2026-11-14
---

Chronopost es el servicio francés de paquetería urgente del grupo La Poste y forma parte de Geopost, la división de paquetería a la que también pertenecen DPD y [SEUR](carrier:seur). Esta guía está pensada sobre todo si esperas en España un paquete enviado desde Francia con Chronopost —de una tienda online o de segunda mano con Shop2Shop—, con un apartado para entregas en Francia. Sigue el envío en chronopost.fr o pega el código arriba. El buscador de [La Poste](carrier:la-poste) en laposte.fr también acepta números de Chronopost, pero puede omitir eventos y referencias presentes en Chronopost.

## Número de seguimiento de Chronopost: cómo es

El código de Chronopost suele tener 13 caracteres (dos letras, nueve cifras y dos letras), o bien 14 o 15 caracteres. Si no lo tienes, pídeselo a la tienda o mira [cómo localizar un número de seguimiento](guide:find-tracking-number).

| Formato (inventado) | Qué es |
| --- | --- |
| `XY000000005FR` | Dos letras (`PZ`, `XR`, `XT`, `XU`, `XW` o `XY`), nueve cifras y `FR`: el número habitual de Chronopost. |
| `AB000000000JB` | El mismo esquema terminado en `JB`, `JF` o `TS`. |
| `XN000000000000` | `XN` y 12 cifras: Chronopost también sigue estos envíos. |
| `00000000000000U` | 14 cifras y un carácter de control (letra o cifra): formato de DPD que Chronopost también emplea. |
| `20000000000000` | 14 cifras sin `10` al principio: puede ser de Chronopost, pero también de DPD o [SEUR](carrier:seur). |

El esquema de 13 caracteres es el formato postal internacional: un código acabado en `FR` con otros prefijos puede ser un Colissimo de [La Poste](carrier:la-poste), y uno acabado en `ES`, `CH` o `GB` puede ser del correo de ese país, como [Correos](carrier:correos-spain). El de 15 caracteres lo usan también DPD Suiza, DPD Alemania, DPD Portugal y DPD Reino Unido. Si Chronopost no lo conoce, revisa el correo de envío o [cómo saber el transportista por el número](guide:tracking-number-formats).

## Estados del seguimiento de Chronopost: qué significan

Chronopost muestra sus estados en francés; Peek los mantiene así en el historial y traduce `Livraison effectuée` como «Entregado». En el orden habitual de un paquete:

| Chronopost escribe | Qué significa para ti | En Peek |
| --- | --- | --- |
| `Colis en cours de préparation chez l'expéditeur` | Etiqueta creada; la tienda aún no ha entregado el paquete. | «Etiqueta creada» |
| `Colis déposé par l'expéditeur` | El remitente ya lo ha entregado a Chronopost. | «Admitido por el transportista» |
| `Tri effectué dans l'agence de départ` | Clasificado en la delegación de origen. | «En tránsito» |
| `Colis en cours d'acheminement` | En ruta por la red de Chronopost. | «En tránsito» |
| `Colis entré dans le pays de destination` | Ha entrado en el país de destino. | «En tránsito» |
| `Tri effectué dans l'agence de distribution` | Clasificado en la delegación de reparto. | «En tránsito» |
| `Livraison reportée de 24h` | La delegación lo guarda un día sin intentar repartir; sigue solo después. | «En tránsito» |
| `Livraison reportée` | El socio de reparto aplaza la entrega sin que debas hacer nada. | «En tránsito» |
| `Colis en cours de livraison` | En reparto hoy. | «En reparto» |
| `Echec de livraison suite à l'absence du destinataire.` | No había nadie en casa. | «Intento de entrega» |
| `Colis non livré : client absent` | El colaborador de reparto no te encontró en casa. | «Intento de entrega» |
| `Colis mis à disposition au point de retrait` | Listo en un punto de recogida o taquilla. | «Listo para recoger» |
| `Colis remis au destinataire au point de retrait` | Recogido en el punto. | «Entregado» |
| `Colis en attente d'informations complémentaires de votre part` | Falta un dato tuyo: usa los canales oficiales, nunca enlaces de SMS dudosos. | «Incidencia» |
| `Colis en retour expéditeur` | Vuelve al remitente. | «Incidencia» |
| `Livraison effectuée` | Entregado. | «Entregado» |

`Livraison reportée de 24h` (dirección por comprobar, desvío o local cerrado) no indica pérdida. `Destinataire informé par SMS ou mail` e `Instruction de livraison reçue` solo registran un aviso o tu instrucción, sin movimiento físico. Un `Livraison effectuée` tras `Colis en retour expéditeur` indica entrega de vuelta a la tienda: Peek muestra «Devuelto al remitente». Más en [estados de un envío](guide:tracking-statuses).

## Paquete de Chronopost hacia España y aduanas

Según La Poste Groupe, Chronopost envía paquetes de hasta 30 kg a 230 países. En España, su marca hermana dentro de Geopost es [SEUR](carrier:seur), con más de 7.000 tiendas locales y taquillas Pickup.

**Tramo español.** A diferencia de Alemania o Portugal, Peek no recibe de Chronopost un número de socio automático para España, así que mantiene el seguimiento en Chronopost. Si recibes un SMS o correo del repartidor en España con un código propio —como uno de 14 o 21 cifras de [SEUR](carrier:seur)—, pégalo también en Peek para seguir el reparto local.

**Aduanas.** Según la Agencia Tributaria (AEAT), las compras online de bienes situados fuera de la UE importadas en Península y Baleares pagan IVA más un arancel fijo de 3 € por categoría de artículo hasta 150 € (desde el 1 de julio de 2026) y una tasa de tramitación de 2 € por categoría de artículo (desde el 1 de noviembre de 2026). Este régimen no se aplica en Canarias, Ceuta ni Melilla, que tienen trámites aduaneros propios. `Problème douanier résolu, colis en cours d'acheminement` indica que el trámite aduanero quedó resuelto («En tránsito»). Más en [paquetes en aduanas](guide:customs).

## En Francia: puntos Pickup, taquillas y ausencias

En Francia, Chronopost cuenta con 19.555 puntos de recogida entre tiendas y taquillas Pickup, oficinas postales y delegaciones propias. Al llegar a un punto Pickup recibes correo o SMS con horarios y fecha límite; según Pickup, nunca pasa de:

- **8 días** en tienda Pickup (para Chronopost);
- **5 días** en taquilla automática Pickup.

Después vuelve automáticamente al remitente ([paquete no recogido](guide:uncollected-parcel)). Para retirarlo presenta el Pickup Pass (QR) o los códigos del mensaje, o en tienda tu documento de identidad original. Algunas taquillas abren 24/7 ([taquillas de paquetes](guide:parcel-lockers)). Otra persona puede recogerlo con tus códigos o Pickup Pass, o en tienda con su documento original y el tuyo; Pickup no admite autorizaciones escritas.

## Paquete parado, dañado o no recibido

**Parado o con retraso.** Reclama a la tienda. En ventas a distancia desde Francia, el vendedor es el único responsable de la entrega y, sin otra fecha pactada, dispone de 30 días desde el pedido; pasado ese plazo, puedes exigirle por escrito un margen razonable antes de cancelar. Ver [seguimiento que no se actualiza](guide:tracking-not-updating).

**Dañado o perdido.** Reclama al vendedor. A domicilio, anota reservas en el albarán si llega dañado y contacta rápido con la tienda. En tienda Pickup, pide al comerciante que registre reservas antes de llevártelo o rechazarlo. Ver [paquete perdido](guide:lost-parcel) y [paquete dañado](guide:damaged-parcel).

**Entregado pero no lo tienes.** Habla con la tienda: si no puede probar que recibiste el paquete, responde ella ([entregado pero no recibido](guide:delivered-not-received)).

## Atención al cliente de Chronopost

Además de la página de contacto en chronopost.fr, La Poste Groupe, Geopost y Pickup citan (a 14 de noviembre de 2026):

- **Léonard**, chatbot de Chronopost que deriva los casos complejos a un agente.
- **Teléfono:** `09 69 39 13 91` (número francés, útil si faltan códigos de recogida).
- **Shop2Shop** (como envíos de leboncoin): chronoshop2shop.fr.

Si una reclamación escrita, fechada y firmada lleva dos meses sin respuesta o esta no te convence, puedes acudir al *Médiateur du Groupe La Poste* dentro del año siguiente.

> Pickup alerta de SMS y correos falsos que piden gastos de aduana o franqueo, enlazan fuera del sitio oficial o meten prisa («última oportunidad»). No abras el enlace: consulta el envío en la web oficial. Ver [SMS falsos de reparto](guide:delivery-scam-texts).

## Seguir un paquete de Chronopost con Peek

[Peek](/) reconoce al instante los códigos de 13 caracteres que empiezan por `PZ`, `XR`, `XT`, `XU`, `XW` o `XY` con dígito de control válido y acaban en `FR`, o que terminan en `JB`, `JF` o `TS`. Con esos prefijos y dígito válido acabados en `ES` pregunta a Chronopost y a [Correos](carrier:correos-spain), y acabados en `CH` o `GB` comprueba con Chronopost. Si falla el dígito de control, acaba en `RV`/`VF` o empieza por `XN`, consulta solo a Chronopost; otros prefijos válidos en `FR` van a [La Poste](carrier:la-poste).

Con 15 caracteres muestra «Comprobando servicios de seguimiento…» y pregunta a Chronopost y a las cuatro redes DPD; con 14 cifras sin `10` inicial consulta a varios transportistas, incluidos Chronopost, DPD y [SEUR](carrier:seur). Si Chronopost lo tiene verás «tiene este paquete»; si responden varios pulsa «Elegir», o usa «Cambiar» para buscarlo en «Encaja con este número» o «Todos los transportistas». Acepta enlaces de chronopost.fr y chronoshop2shop.fr sin código postal.

Peek sitúa cada delegación en el mapa y muestra país de destino, hora de entrega, número del socio en Alemania o Portugal y, mientras espera recogida, nombre y dirección del punto (descartando tu domicilio y contactos del local). Solo muestra fecha prevista si hay nueva entrega o franja reservada, o con `Livraison prévue lundi prochain` de viernes a domingo. Los escaneos con huso salen en tu zona horaria («Horas en tu zona horaria: …» en peektracker.com); los que Chronopost da sin zona se leen en la hora local de su lugar.

Consulta cada 10 minutos, y en reparto cada 2 minutos desde las 6:00 (hora francesa y peninsular española) si responde Chronopost —15 minutos tras un fallo o mediante servicios de seguimiento—. Mantiene 10 minutos hasta 30 minutos antes de una franja anunciada o si sigue en reparto de un día previo; tras dos días sin cambios (12 horas en «Etiqueta creada» o «Admitido por el transportista»), revisa cada hora. Cuando otro transportista asume la entrega, Peek sigue su ritmo. Configura avisos en «Cada escaneo», «Pasos importantes» o «Solo la entrega». Peek no gestiona reclamaciones por ti.

## Preguntas sobre el seguimiento de Chronopost

### ¿Chronopost pertenece a La Poste?

Sí. Chronopost forma parte de Geopost, la filial de paquetería de La Poste Groupe.

### ¿Chronopost, DPD y SEUR son lo mismo?

Son marcas distintas del mismo grupo Geopost. Por eso algunos códigos de Chronopost usan las 15 posiciones de DPD.

### ¿Cuánto tiempo guarda un punto Pickup mi paquete?

En Francia, hasta 8 días en tienda Pickup y hasta 5 días en taquilla Pickup; el mensaje de aviso indica la fecha exacta antes de devolverlo.

:::sources
- [La Poste Groupe: Chronopost (en inglés)](https://www.lapostegroupe.com/en/focus/la-poste-groupes-subsidiaries/chronopost) – hasta 30 kg en 230 países y 19.555 puntos en Francia
- [La Poste Groupe: 2025 Integrated Report, PDF (en inglés)](https://www.lapostegroupe.com/assets/media/le-groupe-laposte/ad4Q_p1ZCF7ETKqb_MEL_LAPO_RI_seul_2025_EN_14042026.pdf) – Chronopost en Geopost con DPD y SEUR, chatbot Léonard
- [Geopost: Who we are (en inglés)](https://www.geopost.com/en/about-us/who-we-are/) – Geopost en La Poste Groupe y marcas DPD, Chronopost, SEUR y BRT
- [Geopost: SEUR (en inglés)](https://www.geopost.com/en/about-us/our-companies-and-partners/seur/) – transporte urgente en España y más de 7.000 tiendas y taquillas
- [Geopost: leboncoin is teaming up with Chronopost (en inglés)](https://www.geopost.com/en/news/leboncoin-is-teaming-up-with-chronopost/) – Shop2Shop by Chronopost
- [Pickup: FAQ particulier (en francés)](https://www.pickup.fr/faq-particulier/) – 8 días en tienda y 5 en taquilla, códigos y terceros sin autorización escrita, teléfono 09 69 39 13 91, reservas y fraudes
- [Service-Public: Achat à distance, livraison du bien (en francés)](https://www.service-public.gouv.fr/particuliers/vosdroits/F10037) – responsabilidad del vendedor, 30 días, reservas y paquetes no recibidos
- [Médiateur du groupe La Poste: Conditions de saisine (en francés)](https://mediateur.groupelaposte.com/pages/conditions-requises-a-la-saisine-du-mediateur-de-la-consommation-du-groupe-la-poste) – reclamación escrita previa, dos meses y un año
- [Agencia Tributaria (AEAT): Envíos por un valor de hasta 150 euros](https://sede.agenciatributaria.gob.es/Sede/aduanas/comercio-electronico-pipe-envios-particulares/compras-internet-envios-particulares/envios-valor-hasta-150-euros.html) – arancel de 3 € (julio 2026) y tasa de 2 € (noviembre 2026) en Península y Baleares
- [Universal Parcel Scraper: Chronopost (en inglés)](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/chronopost) – formatos, estados, socio en Alemania y Portugal y qué lee Peek
- [Universal Parcel Scraper: La Poste (en inglés)](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/la-poste) – respuesta de La Poste a códigos Chronopost
:::
