---
title: Seguimiento TNT Australia: números, estados y ayuda
description: Sigue envíos nacionales de TNT Australia: códigos de 3 letras + 9 dígitos, estados de depósito, nueva entrega de lunes a viernes y teléfono 13 11 50.
slug: seguimiento-tnt-australia
published: 2026-11-24
updated: 2026-11-24
---

TNT Australia (*TNT Express Australia*, en integración con FedEx Express) transporta paquetes, documentos y palés por toda Australia a través de sus depósitos ([tnt.com/express/en_au](https://www.tnt.com/express/en_au/site/home.html)). Los envíos internacionales tienen su propio localizador (consulta [TNT](carrier:tnt)); los envíos nacionales australianos disponen de una consulta específica en [tntexpress.com.au](https://www.tntexpress.com.au/interaction/Trackntrace.aspx).

Puedes consultar un envío nacional australiano en [TNT Australia Track & Trace](https://www.tntexpress.com.au/interaction/Trackntrace.aspx) o pegar tu número de expedición de 12 caracteres en la caja de arriba.

## Formatos de número de seguimiento de TNT Australia

Peek rastrea los envíos nacionales de TNT Australia mediante un código de 12 caracteres:

| Formato (inventado) | Qué es |
| --- | --- |
| `SYD482917360` | **3 letras mayúsculas + 9 dígitos** (12 caracteres en total) — número estándar de envío nacional de TNT Australia consultable en `tntexpress.com.au`. |

Como 3 letras seguidas de 9 dígitos también encajan con los números usados por [Colis Privé](carrier:colis-prive) y [Purolator](carrier:purolator), Peek consulta a estos tres transportistas por orden: primero a TNT Australia en Australia, primero a Purolator en Canadá y primero a Colis Privé en el resto de países. Muestra «TNT Australia tiene este paquete.» en cuanto TNT Australia responde. Algunos prefijos, como `RWM` de Kuehne+Nagel, quedan excluidos del formato de TNT Australia. Un enlace directo de `tntexpress.com.au/interaction/ASPs/trackref_detail.asp?con=...` o `trackref.aspx?con=...` va directo a TNT Australia.

El [Centro de ayuda de TNT Australia](https://www.tnt.com/express/en_au/site/support.html) llama número de envío (*shipment / consignment number*) al código de **9 dígitos** situado en el lado izquierdo de la factura, unos 3 cm por debajo del número de cliente. Sin embargo, el rastreador de TNT Australia en Peek solo admite 3 letras más 9 dígitos. Para otros patrones de paquetes, consulta la guía de [formatos de números de seguimiento](guide:tracking-number-formats).

## Qué significan los estados de seguimiento de TNT Australia

La página de detalle nacional de TNT Australia muestra cuatro columnas — `Status`, `Date`, `Time` y `Depot` — junto con una fecha `Estimated Delivery by:` en la parte superior cuando está disponible.

| Texto de TNT Australia | Qué significa |
| --- | --- |
| `Your shipment data is lodged` | El remitente envió los datos del envío por vía electrónica; TNT Australia aún no ha recogido el paquete. |
| `We've collected your shipment` | TNT Australia recogió el envío en origen, antes del primer escaneo en depósito. |
| `We're processing your shipment` | Escaneado en un depósito de TNT Australia de origen, tránsito o reparto. |
| `Your shipment's with a driver` | En manos del repartidor para entrega hoy en domicilio o empresa. |
| `New delivery address required` | El reparto no pudo entregar en la dirección indicada; el paquete permanece retenido en el depósito local a la espera de una dirección actualizada o de recogida en depósito. |
| `We've delivered your shipment` | Entregado en la dirección del destinatario. |
| `Your shipment was collected` | El destinatario recogió el paquete en persona en el depósito de TNT donde estaba retenido (distinto de `We've collected your shipment`, que corresponde a la recogida al remitente). |
| `Book-In Delivery Requested` / `Book-In Delivery Arranged` | El destinatario pidió una entrega concertada y TNT la confirmó. Son avisos, no escaneos: mantienen la etapa del paquete y la confirmación puede publicarse después de la entrega. |

## Nueva entrega, recogida en depósito y retrasos

El [Centro de ayuda de TNT Australia](https://www.tnt.com/express/en_au/site/support.html) explica qué hacer si pierdes una entrega o necesitas reprogramarla:

- **Si el repartidor dejó una tarjeta:** usa el servicio de nueva entrega (*Re-delivery Service*) de TNT para reservar otra fecha.
- **Si no se dejó tarjeta (o todavía no se intentó entregar):** contacta con TNT Australia con tu número de envío, la dirección completa y la fecha deseada para la nueva entrega.
- **Solo nuevas entregas de lunes a viernes:** TNT Australia indica que los pedidos solo pueden volver a entregarse entre lunes y viernes.
- **`New delivery address required`:** el paquete queda en el depósito indicado en la columna `Depot` hasta que facilites otra dirección o pases a recogerlo; contacta con TNT Australia. Consulta también las guías de [entrega fallida](guide:missed-delivery) y [cuando el seguimiento deja de actualizarse](guide:tracking-not-updating).

Si tu pedido australiano viaja con otro transportista nacional, compara el seguimiento con [Australia Post](carrier:australia-post), [Aramex Australia](carrier:aramex-au), [CouriersPlease](carrier:couriersplease) o [Border Express](carrier:border-express).

## Seguir TNT Australia con Peek

Pega en Peek tu número nacional de 3 letras + 9 dígitos o un enlace de `tntexpress.com.au`. Si Peek lo asigna a otro transportista, pulsa «Cambiar» al añadirlo o «Cambiar transportista» desde la ficha del paquete.

Peek consulta directamente la página de detalle nacional de TNT Australia mediante el [adaptador TNT Australia de código abierto en universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/tnt-au), con reintento automático si se agota el tiempo de red. Lee cada texto de escaneo, depósito y hora, además de la fecha `Estimated Delivery by:` hasta la entrega. TNT Australia ofrece las horas de depósito sin desplazamiento UTC: Peek fecha cada escaneo según la zona horaria de su depósito (hora de Sídney para Sídney, hora de Perth para Perth, etc.), un escaneo sin depósito según el vecino y, en su defecto, con hora de Sídney, mostrando luego todas las horas en el reloj de tu dispositivo. La tabla nacional no expone peso, nombre de servicio ni códigos de estado.

Peek traduce los textos de TNT Australia en etapas claras: «Etiqueta creada» (`Your shipment data is lodged`), «Admitido por el transportista» (`We've collected your shipment`), «En tránsito» (`We're processing your shipment`), «En reparto» (`Your shipment's with a driver`), «Incidencia» (`New delivery address required`) y «Entregado» (`We've delivered your shipment` o `Your shipment was collected`). Los avisos de cita (`Book-In Delivery Requested` y `Book-In Delivery Arranged`) se conservan en el historial sin revertir una entrega completada ni alterar la etapa actual.

Mientras tienes el paquete abierto en Peek, o el día de entrega con las notificaciones activadas, Peek consulta hasta cada 10 minutos; el día en que TNT Australia muestra `Your shipment's with a driver`, cada 2 minutos desde las 6:00 según el reloj del escaneo más reciente mientras responda el localizador de TNT Australia. El resto del tiempo Peek comprueba cada hora. Tras 12 horas con solo «Etiqueta creada» o «Admitido por el transportista», o dos días sin novedades, consulta cada hora sea cual sea tu modo de seguimiento, y cada 6 horas tras siete días en «Etiqueta creada». Cuando TNT Australia no responde y Peek sigue el paquete únicamente a través de servicios de seguimiento, consulta cada hora, cada 3 horas tras un día tranquilo y hasta cada 15 minutos el día en que el paquete está en reparto, mientras tengas el paquete abierto o las notificaciones activadas.

Cuando el escaneo más reciente tiene cuatro días y el paquete sigue en «Etiqueta creada», «Admitido por el transportista» o «En tránsito», Peek lo marca con el día de ese escaneo, como «Sin novedades desde el lun 12 oct». La página del paquete muestra entonces «Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a TNT Australia.», junto con un enlace «Pide ayuda a TNT Australia» hacia su Centro de ayuda. Tras tres semanas sin nuevos escaneos, indica «Nada nuevo desde hace más de tres semanas. Si el paquete aún no ha llegado, contacta con el vendedor. Peek sigue preguntando a TNT Australia.»

Elige «Cada escaneo», «Pasos importantes» o «Solo la entrega» en los ajustes de alertas de Peek para decidir cuándo recibir avisos.

## Contactar con TNT Australia

- **Teléfono (en Australia):** `13 11 50` (publicado en todo el sitio de TNT Australia)
- **Centro de ayuda y nueva entrega:** [Soporte de TNT Australia](https://www.tnt.com/express/en_au/site/support.html)
- **Portal de seguimiento nacional:** [tntexpress.com.au Track & Trace](https://www.tntexpress.com.au/interaction/Trackntrace.aspx) (rastrea hasta 10 envíos nacionales, Sameday Local Jobs o 1 referencia electrónica a la vez)
- **Web de TNT Australia y enlace internacional:** [tnt.com/express/en_au](https://www.tnt.com/express/en_au/site/home.html) y [resumen Track & Trace](https://www.tnt.com/express/en_au/site/shipping-tools/tracking.html)

Ten a mano tu número de envío nacional de 12 caracteres y la dirección de entrega cuando llames al `13 11 50` o solicites una nueva entrega entre semana.

## Preguntas frecuentes

### ¿Cuál es la diferencia entre `We've collected your shipment` y `Your shipment was collected`?

`We've collected your shipment` ocurre al inicio del recorrido, cuando TNT Australia recoge el paquete al remitente. `Your shipment was collected` ocurre al final, cuando el destinatario recoge un paquete retenido en el depósito de TNT, por lo que Peek lo marca como «Entregado».

### ¿Por qué apareció `Book-In Delivery Arranged` después de que ya me entregaran el paquete?

`Book-In Delivery Arranged` es la confirmación de TNT Australia de una entrega concertada: es un aviso, no un escaneo, y puede publicarse después de la propia entrega. Peek mantiene el paquete en «Entregado» en lugar de retrocederlo.

### ¿TNT Australia entrega o vuelve a entregar los fines de semana?

El [Centro de ayuda de TNT Australia](https://www.tnt.com/express/en_au/site/support.html) señala que las entregas reprogramadas solo pueden realizarse entre **lunes y viernes**.

### Mi número de TNT tiene 9 dígitos sin letras, ¿por qué no se rastrea como TNT Australia en Peek?

El rastreador de TNT Australia en Peek usa códigos de 3 letras seguidas de 9 dígitos, el formato nacional leído en `tntexpress.com.au`. El Centro de ayuda de TNT denomina número de envío (*shipment / consignment number*) a un número de 9 dígitos de la factura; pruébalo en [TNT Australia Track & Trace](https://www.tnt.com/express/en_au/site/shipping-tools/tracking.html) o llama al `13 11 50`.

:::sources
- [TNT Australia](https://www.tnt.com/express/en_au/site/home.html)
- [TNT Australia Help Center](https://www.tnt.com/express/en_au/site/support.html)
- [TNT Australia Track & Trace](https://www.tnt.com/express/en_au/site/shipping-tools/tracking.html)
- [TNT Express Australia Domestic Tracker](https://www.tntexpress.com.au/interaction/Trackntrace.aspx)
- [universal-parcel-scraper TNT Australia adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/tnt-au)
:::
