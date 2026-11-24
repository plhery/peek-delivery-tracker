---
title: Seguimiento SEKO Logistics: números, OmniParcel y entregas
description: Sigue envíos de SEKO Logistics y SEKO OmniParcel: formatos de código, números GX, estados de seguimiento, teléfonos en EE. UU. y Reino Unido y entregas.
slug: seguimiento-seko-logistics
published: 2026-11-24
updated: 2026-11-24
---

SEKO Logistics empezó en 1976 con una sola oficina en Chicago y hoy cuenta con más de 150 oficinas en más de 60 países y 15 centros principales. Sus servicios abarcan carga aérea, marítima y terrestre, comercio electrónico, *White Glove & Specialty Delivery* y *Home Delivery & Last Mile*. Los envíos se siguen en [sekologistics.com/en/track](https://www.sekologistics.com/en/track/), y los paquetes transfronterizos de comercio electrónico en el [rastreador OmniParcel](https://track.omniparcel.com/) de SEKO.

Como el portal estándar de SEKO y OmniParcel funcionan como dos sistemas independientes con formatos de números y estilos de escaneo distintos, saber qué formato tienes te ayuda a leer correctamente el historial.

## Formatos de número de SEKO Logistics y OmniParcel

Los envíos de SEKO Logistics no usan un único prefijo exclusivo, sino dos familias atendidas por portales distintos:

- **Números de envío de SEKO Logistics:** Peek admite de 8 a 20 letras y dígitos con una secuencia de al menos 7 dígitos. Entre los formatos vistos hay 3 letras seguidas de 7 dígitos (como `WFY4829173`), 5 letras seguidas de 7 dígitos o 9 dígitos solos (como `482917360`). Peek nunca reconoce SEKO a partir de un número suelto: para 3 letras + 7 dígitos pregunta a [Australia Post](carrier:australia-post), para 5 letras + 7 dígitos a ninguno y para 9 dígitos a [DPD Ireland](carrier:dpd-ie) (fuera de Irlanda solo como último recurso). Selecciona SEKO Logistics manualmente o pega tu enlace de seguimiento.
- **Números de SEKO OmniParcel (`GX` + 17 o 18 dígitos):** los envíos transfronterizos de comercio electrónico en `track.omniparcel.com` utilizan `GX` seguido de 17 o 18 dígitos (como `GX482917360514829173`). La página principal de seguimiento de SEKO no reconoce códigos `GX`; solo responde el rastreador de OmniParcel.

Si pegas un enlace de `sekologistics.com/en/track/?trackingNumbers=...` o `track.omniparcel.com/...`, Peek extrae el código y lo dirige automáticamente a SEKO Logistics. Para entender mejor las diferencias entre códigos logísticos y de paquetería, consulta la guía de [formatos de números de seguimiento](guide:tracking-number-formats).

## Estados de seguimiento de SEKO Logistics y OmniParcel

La página principal de SEKO suele redactar los hitos como `Status Changed to [...]` (a veces con un guion final en vez de corchetes), mientras que OmniParcel detalla centros internacionales, vuelos, aduanas y transportistas de última milla.

| Texto de SEKO / OmniParcel | Qué significa |
| --- | --- |
| `Shipment Data Received` / `Shipment Created` / `Tracking number allocated & order ready` | Los datos del envío llegaron a SEKO u OmniParcel antes de la recogida física o el ingreso en almacén. |
| `Arrived at PU location` | El conductor de recogida llegó al remitente; la carga aún no consta como recogida. |
| `Picked Up From Customer` / `At Pickup Terminal` / `Received at Origin Warehouse` | SEKO recogió el envío o lo registró en la terminal o el almacén de origen. |
| `In Transit` / `In Transit to Delivery Terminal` / `At Transfer Terminal` / `Processed through Export Hub` / `Flight - Freight departed to destination` | En ruta por transporte nacional o carga aérea internacional hacia el país o la estación de entrega. |
| `At Delivery Terminal` / `At Delivery Terminal Delivery Appt Set` / `On Hand Destination Station` / `Delivered To Warehouse` | Llegó a la terminal de destino de SEKO o al almacén local antes de la entrega final; `Delivery Appt Set` indica que ya se ha concertado cita para la entrega a domicilio. |
| `Received by Destination Customs` / `Customs held for inspection and clearance` | En trámites o inspección aduanera en destino para un envío transfronterizo de OmniParcel. |
| `Customs cleared` / `Received by Last Mile Provider` / `Received by carrier` | Despachado en aduana y entregado al centro local de destino o al repartidor de última milla. |
| `Shipment is Out for Delivery` / `Driver on site Delivery Location` / `The order is on the way to the recipient.` | En reparto hoy con el conductor, o conductor ya presente en el lugar de entrega. |
| `Awaiting collection` / `Customer to collect from carrier` / `Delivered to locker/collection point` | Esperando a que el destinatario lo recoja en un depósito, punto de recogida o taquilla. |
| `Attempted delivery` / `Attempted Delivery - Refused by customer` | Un intento de entrega no tuvo éxito. |
| `Temporary Origin Hold` / `Temporary Destination Hold` | Una retención temporal detiene el envío en la terminal de origen o de destino. |
| `Shipment Delivered` / `Delivered - Authority to Leave / Safe Drop` | Entregado en la dirección del destinatario (o dejado mediante autorización de entrega segura). |
| `Return to sender` | De vuelta hacia el remitente. |

Conviene tener en cuenta dos frases del portal principal de SEKO:

- **`Delivered To Warehouse`** significa que la carga llegó al almacén de destino de SEKO, no a tu casa — la entrega aún no se ha producido.
- **`POD Image Attached` y `POD IMAGED`** anotan el archivo del comprobante de entrega (a veces días después de entregar) y **`Return Data Received`** indica que se registró una devolución sin demostrar que el envío haya salido de vuelta. Ninguno cambia la etapa física de entrega.

## Entrega a domicilio, aduanas transfronterizas y retrasos

La web de SEKO incluye *White Glove & Specialty Delivery* y *Home Delivery & Last Mile*, además de logística segura para electrónica y equipos, junto con los pedidos transfronterizos de OmniParcel.

- **Citas de entrega a domicilio:** `At Delivery Terminal Delivery Appt Set` y `On Hand DEST Delv Appt Set` indican que ya hay una cita de entrega concertada mientras el envío aguarda en la terminal de destino. En tránsito, la página de SEKO suele mostrar una fecha `Expected Arrival` o un rango `Estimated delivery between`.
- **Traspasos transfronterizos de OmniParcel:** en pedidos internacionales de comercio electrónico (`GX...`), los escaneos de OmniParcel van desde el almacén de origen, la exportación, el vuelo y la aduana en destino hasta el transportista local (`Received by Last Mile Provider`). Las actualizaciones pueden detenerse en vuelos y aduanas; consulta la [guía de aduanas](guide:customs) y [cuando el seguimiento deja de actualizarse](guide:tracking-not-updating) si se detiene.
- **Horarios y repeticiones:** la página de SEKO muestra las horas al minuto sin zona horaria y a veces repite el mismo estado dos veces en el mismo minuto con distintas mayúsculas; Peek conserva uno y fecha los hitos de SEKO (que no indican lugar) con hora de Chicago. OmniParcel muestra las horas en UTC+10:00 salvo que cambies su desplegable de zona horaria; Peek consulta OmniParcel en UTC para que cada escaneo conserve su instante exacto. Peek muestra cada escaneo en la hora de tu dispositivo.

Si tu pedido internacional usa otra gran mensajería en otro tramo, compara el seguimiento con [DHL Express](carrier:dhl-express) o [FedEx](carrier:fedex).

## Seguir SEKO Logistics con Peek

Como los códigos de SEKO coinciden con otros transportistas y no llevan un prefijo universal, pega el enlace completo `sekologistics.com/en/track/?trackingNumbers=...` o `track.omniparcel.com/...` siempre que lo tengas. Si pegas un número suelto — `WFY4829173`, `482917360` o un código `GX...` de OmniParcel — elige SEKO Logistics manualmente con «Cambiar» al añadirlo o «Cambiar transportista» desde la ficha del envío.

Peek consulta ambos portales directamente mediante el [adaptador SEKO de código abierto en universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/seko): los códigos `GX` + 17–18 dígitos van automáticamente a OmniParcel (consultado en UTC, leyendo ubicaciones, nombre del servicio, peso en kilogramos y país de destino), mientras que los números de envío SEKO van a la página de seguimiento de SEKO (fecha prevista de llegada o ventana de entrega, nombre del servicio, país de destino e hitos sin duplicados, filtrando las filas administrativas `POD Image Attached` / `POD IMAGED` y descartando nombres de firmantes y códigos postales).

Peek traduce los hitos de SEKO y OmniParcel a etapas estándar: «Etiqueta creada» para datos recibidos, creación y llegada del conductor al punto de recogida; «Admitido por el transportista» para recogida e ingreso en almacén de origen; «En tránsito» para centros, vuelos, transbordos, terminales de entrega, llegada a almacén, aduana despachada y traspaso de última milla; «En aduanas» para inspecciones aduaneras en destino; «En reparto» para salida a reparto y conductor en el lugar; «Listo para recoger» para depósitos, puntos de recogida o taquillas; «Intento de entrega» para intentos fallidos; «Incidencia» para retenciones en origen o destino; «Entregado» para entrega y entrega autorizada; y «Devuelto al remitente» para devoluciones.

Mientras tienes el paquete abierto en Peek, o el día de entrega con las notificaciones activadas, Peek consulta hasta cada 10 minutos; el día en que la página de SEKO muestra `Shipment is Out for Delivery`, `Out for Delivery` o `Driver on site Delivery Location`, o OmniParcel muestra `Out for delivery` o `The order is on the way to the recipient.`, cada 2 minutos desde las 6:00 según el reloj del escaneo más reciente mientras responda el localizador de SEKO. El resto del tiempo Peek comprueba cada hora. Tras 12 horas con solo «Etiqueta creada» o «Admitido por el transportista», o dos días sin novedades, consulta cada hora lo sigas como lo sigas, y cada 6 horas tras siete días en «Etiqueta creada». Cuando SEKO no responde y Peek sigue el paquete únicamente a través de servicios de seguimiento, consulta cada hora, cada 3 horas tras un día tranquilo y hasta cada 15 minutos el día en que el paquete está en reparto, mientras tengas el paquete abierto o las notificaciones activadas.

Cuando el escaneo más reciente tiene cuatro días y el paquete sigue en «Etiqueta creada», «Admitido por el transportista» o «En tránsito», Peek lo marca con el día de ese escaneo, como «Sin novedades desde el lun 12 oct». La página del paquete muestra entonces «Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a SEKO Logistics.» Tras tres semanas sin nuevos escaneos, indica «Nada nuevo desde hace más de tres semanas. Si el paquete aún no ha llegado, contacta con el vendedor. Peek sigue preguntando a SEKO Logistics.»

En los ajustes de alertas de Peek, elige «Cada escaneo», «Pasos importantes» o «Solo la entrega» para ajustar tus notificaciones.

## Contactar con SEKO Logistics

SEKO publica sus líneas regionales de atención al cliente directamente en su [página de seguimiento](https://www.sekologistics.com/en/track/) y en su [página de contacto](https://www.sekologistics.com/en/contact-us/):

- **Línea de entregas a domicilio en EE. UU.:** (866) 268-5263
- **Atención al cliente en Reino Unido:** +44 1784 417120
- **Seguimiento oficial online:** [SEKO Track My Shipment](https://www.sekologistics.com/en/track/) (admite hasta 30 números de seguimiento a la vez) y [OmniParcel Tracker](https://track.omniparcel.com/)
- **Consulta de envíos online:** [Contactar con SEKO Logistics](https://www.sekologistics.com/en/contact-us/) (selecciona el formulario «Track My Shipment»)
- **Información de la empresa:** [Acerca de SEKO Logistics](https://www.sekologistics.com/en/about/) y [sekologistics.com](https://www.sekologistics.com/en/)

Ten a mano tu número de seguimiento de SEKO cuando llames o rellenes el formulario Track My Shipment.

## Preguntas frecuentes

### ¿Por qué mi número `GX` no funciona en `sekologistics.com/en/track/`?

Los números que empiezan por `GX` seguidos de 17 o 18 dígitos pertenecen a la plataforma OmniParcel de SEKO y solo responden en [track.omniparcel.com](https://track.omniparcel.com/). En Peek, seleccionar SEKO Logistics envía automáticamente los códigos `GX` a OmniParcel y los códigos estándar de SEKO al localizador principal de SEKO.

### ¿Significa «Delivered To Warehouse» que mi pedido se entregó en una dirección equivocada?

No. En el seguimiento de SEKO Logistics, `Delivered To Warehouse` significa que el envío llegó al almacén de destino de SEKO antes de la entrega final. Espera a `Out for delivery` y `Shipment Delivered` para confirmar la entrega en tu dirección.

### ¿Por qué me pidió Peek elegir transportista para mi número de SEKO?

Los números de SEKO (como 3 letras + 7 dígitos o 9 dígitos) también coinciden con otros transportistas, y Peek nunca reconoce SEKO a partir de un número suelto, incluidos los códigos `GX`. Elegir SEKO Logistics mediante «Cambiar» o pegar el enlace oficial de SEKO u OmniParcel indica a Peek exactamente dónde consultar.

### ¿Cómo programo una cita de entrega a domicilio con SEKO Logistics?

Llama a la línea US Home Delivery en el `(866) 268-5263` o a atención al cliente en Reino Unido en el `+44 1784 417120`, o usa el formulario Track My Shipment en [sekologistics.com/en/contact-us](https://www.sekologistics.com/en/contact-us/).

:::sources
- [SEKO Logistics](https://www.sekologistics.com/en/)
- [SEKO Logistics — Track My Shipment](https://www.sekologistics.com/en/track/)
- [SEKO Logistics — About](https://www.sekologistics.com/en/about/)
- [SEKO Logistics — Contact Us](https://www.sekologistics.com/en/contact-us/)
- [SEKO OmniParcel Tracker](https://track.omniparcel.com/)
- [universal-parcel-scraper SEKO adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/seko)
:::
