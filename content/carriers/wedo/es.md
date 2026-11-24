---
title: Seguimiento One by Allegro CZ (WE|DO): códigos y entrega
description: Sigue paquetes One by Allegro CZ (WE|DO, IN TIME, Uloženka) en Chequia: códigos de 11 caracteres, One Box 48 h, One Point 5 días y soporte.
slug: seguimiento-one-by-allegro-cz
published: 2026-11-24
updated: 2026-11-24
---

One by Allegro CZ es la empresa checa de reparto del grupo Allegro (su web, [onedelivery.cz](https://onedelivery.cz/), pertenece a Allegro Retail a.s.). Entrega pedidos de allegro.cz y de otras tiendas online mediante tres servicios: **One Box** (más de 5.244 taquillas automáticas, incluidas las redes asociadas AlzaBox y OX Box), **One Point** (1.960 puntos de recogida asociados) y **One Courier** (entrega a domicilio en toda Chequia con más de 350 furgonetas y repartidores). El buscador de Peek también lo encuentra como WE|DO, IN TIME o Uloženka.

Puedes comprobar un envío checo en [onedelivery.cz/sledovani-zasilky](https://onedelivery.cz/sledovani-zasilky) o pegar tu código de 11 caracteres en la caja de arriba.

## Formato del número de seguimiento de One by Allegro CZ (WE|DO)

Los envíos checos de One by Allegro / WE|DO usan un **código alfanumérico compacto de 11 caracteres**:

| Formato (inventado) | Qué es |
| --- | --- |
| `2BM48291736` | 1 dígito + 1 letra + 1 letra/dígito + 8 dígitos (11 caracteres en total) — número estándar de One by Allegro CZ (WeDo). |
| `48P48291736` | 2 dígitos + 1 letra + 8 dígitos (11 caracteres en total) — formato alternativo checo de One by Allegro (WeDo). |

Los prefijos que empiezan por `7D` + dígito o `7X` + dígito quedan excluidos de esta regla; el resto de códigos de 11 caracteres compatibles identifican a **One by Allegro CZ** sin ambigüedad y con confianza alta. Peek también lee enlaces directos de `onedelivery.cz/sledovani-zasilky?numer=...`, `onedelivery.cz/?numer=...`, `trace.wedo.cz/index.php?orderNumber=...` y `trace.intime.cz/index.php?orderNumber=...`. Los paquetes polacos de Allegro One usan un localizador aparte: consulta [Allegro One](carrier:allegro-one) y la guía de [formatos de números de seguimiento](guide:tracking-number-formats).

## Qué significan los estados de One by Allegro CZ

La API pública de seguimiento de One by Allegro CZ devuelve descripciones en inglés con hora y desplazamiento UTC. Estas son las habituales:

| Texto de One by Allegro CZ | Qué significa |
| --- | --- |
| `Parcel has been prepared by the sender` | La tienda creó la etiqueta de envío; el mensajero todavía no ha recogido el paquete. |
| `Parcel has been picked up by the courier` | One by Allegro CZ recogió el paquete al remitente. |
| `Parcel has been dispatched to its destination point` | Salió del centro de origen hacia la región o red de recogida de destino. |
| `Parcel has been accepted at the sorting center` | Procesado dentro de un centro de clasificación de One by Allegro. |
| `Parcel has been accepted at the branch` | Llegó a la delegación local que cubre tu dirección o punto de recogida. |
| `Parcel has been released for delivery` | Cargado en una furgoneta de reparto para entrega a domicilio o depósito en tu One Box / One Point hoy. |
| `Parcel is awaiting pick-up` | Listo para recoger en tu taquilla One Box o punto One Point (revisa tu SMS, correo o app para ver el PIN de recogida). |
| `Parcel has been delivered` | Entregado al destinatario o recogido en la taquilla o punto de recogida. |

Si el localizador devuelve un texto inusual que pudiera incluir datos personales del destinatario, Peek lo sustituye por `Tracking update` manteniendo su fecha y hora en el historial.

## Reglas de entrega en One Box, One Point y One Courier

One by Allegro CZ publica plazos claros de recogida y entrega en [onedelivery.cz](https://onedelivery.cz/):

- **Taquillas One Box ([onedelivery.cz/one-box](https://onedelivery.cz/one-box)):** accesibles **24/7** en más de 5.244 taquillas (One Box, AlzaBox y OX Box). Recógelo en un plazo de **48 horas** desde la entrega en taquilla introduciendo el teléfono del destinatario y el PIN numérico enviado por SMS, correo, app One by Allegro o app Allegro (no hace falta app frente a la pantalla). El reembolso (*dobírka*) puede pagarse mediante el enlace de pago del aviso o en el datáfono de la One Box.
- **Puntos One Point ([onedelivery.cz/one-point](https://onedelivery.cz/one-point)):** en 1.960 comercios asociados en horario comercial, los paquetes esperan normalmente **5 días laborables** (ampliables gratis hasta **3 días laborables adicionales** mediante atención al cliente; durante la campaña previa a Navidad, el plazo de One Point se acorta de 5 a **3 días**, mientras que One Box mantiene 48 horas). Los límites máximos de One Point son **20 kg**, lado mayor **1,2 m** y volumen **0,2 m³**. El contra reembolso se paga en efectivo o con tarjeta donde el comercio tenga terminal.
- **Entrega a domicilio One Courier ([onedelivery.cz/one-courier](https://onedelivery.cz/one-courier)):** entrega estándar el **siguiente día laborable (`D+1`)** tras la recogida, de **lunes a viernes entre las 08:00 y las 18:00**, hasta la primera puerta con cerradura (los repartidores no están obligados a subir paquetes por escaleras y no se entrega en apartados postales). El día de entrega recibes aviso por SMS, correo o app. El reembolso se abona en efectivo (se pide importe exacto) o con tarjeta sin recargo. Puedes cambiar el día de entrega o redirigir un paquete elegible a un punto de recogida mediante la app móvil One by Allegro o atención al cliente; los cambios de dirección se tramitan con atención al cliente.

Si pierdes un plazo de recogida o buscas un envío, consulta las guías de [taquillas de paquetería](guide:parcel-lockers), [paquete no recogido](guide:uncollected-parcel) y [cuando el seguimiento deja de actualizarse](guide:tracking-not-updating). Para otras redes checas, compara con [Packeta](carrier:packeta), [PPL](carrier:ppl) y [Česká pošta](carrier:ceska-posta).

## Seguir One by Allegro CZ con Peek

Pega tu código de 11 caracteres de One by Allegro CZ, o un enlace `onedelivery.cz` o `trace.wedo.cz`, en Peek y reconocerá **One by Allegro CZ** al instante. Si necesitas cambiar de transportista, pulsa «Cambiar» al añadirlo o «Cambiar transportista» en la pantalla de seguimiento.

Peek consulta directamente el punto público de One by Allegro CZ (`edge.onedelivery.cz`) mediante el [adaptador One by Allegro CZ de código abierto en universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/wedo), con reintento automático ante agotamiento de red. Registra las marcas horarias exactas con desplazamiento UTC y el instante de entrega (`delivered_at`), descartando mensajes de texto libre que puedan filtrar datos personales. La API pública no facilita fecha estimada de entrega, ciudad de depósito ni peso.

Peek traduce estos textos a etapas claras: «Etiqueta creada» (`Parcel has been prepared by the sender`), «Admitido por el transportista» (`Parcel has been picked up by the courier`), «En tránsito» (expedición, centro de clasificación y delegación), «En reparto» (`Parcel has been released for delivery`), «Listo para recoger» (`Parcel is awaiting pick-up`) y «Entregado» (`Parcel has been delivered`). También interpreta otros textos de Allegro One, como `Parcel has been returned to the sender`.

Mientras tienes el paquete abierto en Peek, o el día de entrega con las notificaciones activadas, Peek consulta hasta cada 10 minutos; el día en que One by Allegro CZ muestra `Parcel has been released for delivery`, cada 2 minutos desde las 6:00 hora checa mientras responda su localizador. El resto del tiempo Peek comprueba cada hora. Tras 12 horas con solo «Etiqueta creada» o «Admitido por el transportista», o dos días sin novedades, consulta cada hora sea cual sea tu modo de seguimiento, y cada 6 horas tras siete días en «Etiqueta creada». Cuando One by Allegro CZ no responde y Peek sigue el paquete únicamente a través de servicios de seguimiento, consulta cada hora, cada 3 horas tras un día tranquilo y hasta cada 15 minutos el día en que el paquete está en reparto, mientras tengas el paquete abierto o las notificaciones activadas.

Cuando el escaneo más reciente tiene cuatro días y el paquete sigue en «Etiqueta creada», «Admitido por el transportista» o «En tránsito», Peek lo marca con el día de ese escaneo, como «Sin novedades desde el lun 12 oct». La página del paquete muestra entonces «Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a One by Allegro CZ.», junto con un enlace «Pide ayuda a One by Allegro CZ» hacia su página de contacto. Tras tres semanas sin nuevos escaneos, indica «Nada nuevo desde hace más de tres semanas. Si el paquete aún no ha llegado, contacta con el vendedor. Peek sigue preguntando a One by Allegro CZ.»

En los ajustes de alertas de Peek, elige «Cada escaneo», «Pasos importantes» o «Solo la entrega» para recibir los avisos que prefieras.

## Contactar con One by Allegro CZ

- **Formulario de consulta y soporte:** [onedelivery.cz/kontakt](https://onedelivery.cz/kontakt) (pulsa *Dotaz na zásilku* para consultas de comprador o vendedor, pérdida de PIN de recogida, prórroga gratuita de 3 días en One Point o cambios de dirección)
- **Página oficial de seguimiento:** [onedelivery.cz/sledovani-zasilky](https://onedelivery.cz/sledovani-zasilky)
- **Información de servicios:** [One Box](https://onedelivery.cz/one-box), [One Point](https://onedelivery.cz/one-point) y [One Courier](https://onedelivery.cz/one-courier)
- **Web principal:** [onedelivery.cz](https://onedelivery.cz/)

## Preguntas frecuentes

### ¿Son WE|DO, IN TIME y One by Allegro CZ el mismo transportista?

Peek los trata como uno solo: su buscador encuentra One by Allegro CZ como WE|DO, IN TIME o Uloženka, y los enlaces `trace.wedo.cz` y `trace.intime.cz` así como los códigos de 11 caracteres se rastrean bajo One by Allegro CZ. La web `onedelivery.cz` no relata la historia de las marcas.

### ¿Cuánto tiempo espera un paquete en One Box o en un One Point?

En **One Box** (incluidos AlzaBox y OX Box), los paquetes esperan **48 horas** desde su depósito en la taquilla. En comercios **One Point**, la custodia estándar es de **5 días laborables** (ampliable gratis hasta 3 días laborables vía soporte), reducida a **3 días** durante la temporada previa a Navidad.

### ¿Puedo pagar el contra reembolso (dobírka) con tarjeta?

Sí. Los repartidores de One Courier aceptan efectivo (se solicita importe exacto) o tarjeta sin recargo para el destinatario; One Box admite pago con tarjeta mediante el enlace de pago o el terminal de la taquilla; y One Point acepta efectivo o tarjeta cuando la tienda cuenta con datáfono.

### ¿Qué hago si perdí mi código de recogida de One Box o One Point?

Los códigos de recogida llegan por SMS, correo electrónico y en las apps One by Allegro y Allegro. Si no lo encuentras en ninguno de esos sitios, contacta con atención al cliente desde el formulario de [onedelivery.cz/kontakt](https://onedelivery.cz/kontakt).

:::sources
- [One by Allegro CZ (onedelivery.cz)](https://onedelivery.cz/)
- [One by Allegro CZ — Sledování zásilky](https://onedelivery.cz/sledovani-zasilky)
- [One by Allegro CZ — One Box](https://onedelivery.cz/one-box)
- [One by Allegro CZ — One Point](https://onedelivery.cz/one-point)
- [One by Allegro CZ — One Courier](https://onedelivery.cz/one-courier)
- [One by Allegro CZ — Kontakt](https://onedelivery.cz/kontakt)
- [universal-parcel-scraper One by Allegro CZ adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/wedo)
:::
