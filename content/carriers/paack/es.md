---
title: Paack seguimiento: estados, entrega fallida y teléfono
description: Seguimiento de pedido Paack en España: cómo rastrear con número de pedido y código postal, qué significa pedido reprogramado, PaackGo Point y pedidos SHEIN.
slug: paack-seguimiento
published: 2026-10-11
updated: 2026-11-27
---

Para hacer el seguimiento de un pedido de Paack en España, introduce el número de pedido (o el código de barras de la etiqueta, incluidos envíos de SHEIN o números `PK`) junto con el código postal de entrega en la página de Paack, o pega el número en la casilla de arriba y elige Paack. Paack comprueba número y código postal a la vez: si uno falla, responde «Pedido no encontrado» (los números `PK` de Evri International no necesitan código postal).

## Número de pedido de Paack: dónde encontrarlo

La página de seguimiento de Paack pide el número de pedido, o el número del código de barras de la etiqueta, junto con el código postal de entrega. Casi siempre es el número de pedido de la tienda o el código de barras de la etiqueta, así que no tiene un formato fijo: búscalo en los mensajes de pedido y de envío de la tienda, o en la etiqueta del paquete. Si no te ha llegado nada, pregunta a la tienda o mira [cómo localizar un paquete sin número de seguimiento](guide:find-tracking-number).

Como no tiene un formato fijo, un número de pedido puede parecerse al de otra empresa (ejemplos inventados):

| Número de pedido | A qué se parece |
| --- | --- |
| `123456789AB` | Letras y cifras: a ninguna empresa en concreto. |
| `1234567` | 7 cifras: también el formato de SEUR. |
| `1234567890` | 10 cifras: también de Estafeta, [DHL](carrier:dhl-express) y otras. |
| `123456789012` | 12 cifras: también de MRW, [FedEx](carrier:fedex), BRT y otras. |

Si la tienda te dice que tu pedido lo entrega Paack, el número es de Paack, tenga la forma que tenga.

Si el número o el código postal no coinciden, la página responde «Pedido no encontrado».

## Estados del seguimiento de Paack: qué significa cada uno

La página de Paack resume el recorrido en cuatro pasos (datos del pedido recibidos, en su centro de distribución, en reparto y entregado), y su historial añade los intermedios. Peek muestra cada paso con sus propias palabras, casi todas en español; tres salen en inglés:

| En Peek | Qué significa para ti |
| --- | --- |
| `Información del envío recibida` | La tienda ha mandado a Paack los datos de tu pedido. Paack todavía no tiene el paquete. |
| `Envío admitido` | Paack ha escaneado el paquete por primera vez, en su centro de distribución. |
| `En tránsito` | Paack lo tiene, pero aún no ha salido a reparto. |
| `Courier assigned` | Ya hay un repartidor asignado a tu entrega. Vuelve a aparecer antes de cada nuevo intento. |
| `En reparto` | Va con el repartidor, en la franja indicada. |
| `Delivery attempt failed`, `Problema de entrega` | Esta vez no se ha entregado: el repartidor no ha podido, o tu PaackGo Point estaba cerrado, lleno o rechazó el paquete. |
| `New delivery attempt scheduled` | Paack lo vuelve a intentar, en la nueva franja. Si Paack lo programa en el mismo momento del intento fallido, forma parte de ese paso y Peek no lo muestra. |
| `Listo para recoger` | Te espera en el PaackGo Point; la página de Paack da tu último día para recogerlo. |
| `Entregado` | Entregado, o recogido en el PaackGo Point. |
| `De vuelta al remitente`, `Devuelto al remitente` | Vuelve a la tienda, o ya ha vuelto: por ejemplo, no lo recogiste a tiempo, o se rechazó o no se pagó en el PaackGo Point. |
| `Incidencia en el envío` | Un problema con el paquete, como un daño o una pérdida en el PaackGo Point: habla con la tienda. |

## Entrega de Paack: franja horaria y PaackGo Point

**La franja.** La página de Paack da el día y la franja previstos para tu entrega, en la hora local de tu dirección. Tras un intento fallido, la franja que ves es la del nuevo intento.

**PaackGo Point** es el nombre de los puntos de recogida de Paack. Cuando el paquete llega, la página de Paack da el nombre y la dirección del punto, tu último día para recogerlo, un código de recogida y un código QR. Si no lo recoges a tiempo, vuelve a la tienda.

## Paquete de Paack no entregado, perdido o entregado pero no recibido

Compraste en la tienda, no en Paack, así que los reembolsos, las devoluciones y las cancelaciones se piden a la tienda:

- **Retrasado o parado:** mira [el seguimiento de mi paquete no se actualiza](guide:tracking-not-updating) y, si sigue sin moverse, pregunta a la tienda.
- **Devuelto a la tienda, o sin número de seguimiento:** pregunta a la tienda.
- **Perdido o dañado:** habla con la tienda.
- **Entregado pero no lo tienes:** pregunta en casa y a los vecinos y, después, a la tienda. Más en [paquete entregado pero no recibido](guide:delivered-not-received).

## Paack: teléfono y atención al cliente

**Teléfonos en España:** 810 101 954 y 900 813 377. Paack no indica el coste de la llamada.

## Seguir un paquete de Paack con Peek

[Peek](/) reconoce al momento como de Paack un número `PK` con 18 cifras, el de los paquetes que Paack entrega para Evri International, y lo sigue sin código postal. Con un número de etiqueta de `5340` y 14 cifras más, que aparece en paquetes de Paack, «Cambiar» muestra Paack en primer lugar en «Encaja con este número», por delante de otras empresas que usan 18 cifras (en Bélgica, detrás de bpost). Con un número de paquete de SHEIN (`C` o `E`, una fecha de seis cifras con año, mes y día, y 8 cifras más), también muestra ahí Paack. Los demás números de Paack no tienen un formato propio, y Peek no puede preguntar a Paack si conoce uno. Cualquier otro número con letras suele quedarse sin transportista («Peek lo buscará cuando empieces el seguimiento»), y uno solo de cifras puede tomarse por el de otra empresa con ese formato, a la que se pregunta en su lugar. Pulsa «Cambiar» y elige Paack, o pega un enlace a la página de seguimiento de Paack con `tracking_number=`, de mydeliveries.paack.app o de paack.co/shipments/tracking.

Después, escribe el «Código postal de entrega». Aparece como opcional porque un número `PK` no lo necesita, pero Paack solo muestra los demás pedidos con él, y el de un enlace no se usa. Si ya diste un código postal antes, Peek te lo propone debajo de la casilla («Usar …»), al añadir el paquete y en «Cambiar transportista». Paack solo responde si el número y el código postal coinciden, así que, si uno de los dos está mal, el número parece desconocido. El código postal se envía a Paack y, si hace falta, a servicios de seguimiento. Peek pasa el número a mayúsculas y quita espacios, puntos y guiones: si no aparece nada, prueba también en la página de Paack con el número tal cual.

Verás el estado, el historial y el día y la franja previstos, según la hora local de la dirección de entrega (la de Canarias si el código postal empieza por 35 o 38). Las horas del historial salen en tu zona horaria, y tanto en peektracker.com como en las apps para iPhone y Android una línea al final lo recuerda: «Horas en tu zona horaria: …». En un PaackGo Point, Peek debería mostrar su nombre y dirección (aún no se ha visto en un paquete real), nunca el código de recogida, el QR ni el plazo: para eso, abre la página de Paack. Los pasos aparecen con las palabras de Peek de la tabla de arriba, nunca con los textos de Paack; una recogida en un PaackGo Point sale como «Entregado». Paack no da el lugar de sus escaneos ni nombra a otra empresa, así que el paquete no se sigue en ninguna otra. Con un número `PK`, en cambio, el historial viene del seguimiento de Evri International, con sus textos y sus lugares. El nombre de la tienda y tu nombre y dirección no se guardan. El seguimiento se consulta hasta una vez cada 10 minutos, hasta una vez cada 2 cuando el paquete está en reparto (ese día, desde las 6:00, hora local, o, si Paack da una franja, desde media hora antes de que empiece, y mientras responda el propio seguimiento de Paack; a través de servicios de seguimiento, cada 15 minutos), y puedes recibir notificaciones en cada escaneo, solo en los pasos importantes o solo el día de entrega. Tras dos días sin novedades, o tras 12 horas si el paquete sigue en «Etiqueta creada» o «Admitido por el transportista», Peek pasa a consultar una vez por hora.

## Preguntas sobre el seguimiento de Paack

### ¿Qué significa que Paack ha reprogramado mi pedido?

Que lo intentará de nuevo en una nueva fecha y franja. Tras un intento fallido, Peek muestra `Delivery attempt failed` y la página de Paack indica el nuevo día y la nueva franja; si el nuevo intento se programa más tarde, aparece `New delivery attempt scheduled`. En un PaackGo Point, un envío se reprograma cuando el punto estaba cerrado, lleno o rechazó el paquete (`Problema de entrega`).

### ¿Cómo hacer el seguimiento de un pedido de SHEIN con Paack?

Pega el número de paquete de SHEIN (`C` o `E`, fecha de seis cifras y 8 cifras más) en Peek, pulsa «Cambiar», elige Paack e introduce el código postal de entrega, o pega directamente el enlace de seguimiento de mydeliveries.paack.app o paack.co/shipments/tracking.

### ¿Por qué Paack me pide el código postal?

Porque Paack solo muestra un pedido si coinciden a la vez el número y el código postal de la dirección de entrega. Si uno de los dos es incorrecto, responde «Pedido no encontrado»; solo los números `PK` con 18 cifras (Evri International) se siguen sin código postal.

### ¿Qué es Paack?

Una empresa de reparto de última milla para tiendas online. Paack y Colis Privé se unieron en el verano de 2026 y compartirán la marca Paack a partir de enero de 2027 en Francia, Bélgica, Luxemburgo, España y Portugal; hasta entonces, la entrega y el seguimiento siguen con la empresa que indique la tienda.

### ¿Cuánto tarda Paack en llegar?

La página de seguimiento de Paack y Peek muestran el día y la franja horaria previstos para tu paquete, según la hora local de la dirección de entrega. Para el plazo de envío antes del reparto, consulta directamente a la tienda.

:::sources
- [Paack Support: Sobre Paack](https://paackcustomerservice.freshdesk.com/es/support/solutions/folders/44001208241) – teléfonos
- [Colis Privé: Colis Privé and Paack unite under one brand: Paack (en inglés)](https://colisprive.fr/en/blog/colis-prive-and-paack-unite-under-one-brand-paack/) – 16 de octubre de 2026: unidas desde el verano de 2026, marca común desde enero de 2027, cinco países, entrega y seguimiento como siempre
- [Universal Parcel Scraper: Paack](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/paack) – número de pedido o código de barras con el código postal, «Pedido no encontrado», formatos de número, pasos del seguimiento y cómo los anota Peek, franja, PaackGo Point
:::
