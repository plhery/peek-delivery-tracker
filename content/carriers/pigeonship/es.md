---
title: Seguimiento PigeonShip: códigos, estados y entrega
description: Sigue entregas el mismo día y de última milla de PigeonShip en EE. UU. y Canadá: códigos de 18 caracteres, estados en vivo, plazos y soporte.
slug: seguimiento-pigeonship
published: 2026-11-24
updated: 2026-11-24
---

PigeonShip es un transportista de entrega de última milla que opera en Estados Unidos y Canadá, con oficinas en Provo (Utah) y Oakville (Ontario). Comercios, tiendas en línea y negocios locales utilizan PigeonShip para entregas en el mismo día, al día siguiente y regionales mediante servicios como **Pigeon Flex** (entrega nacional en 2–5 días), **Pigeon Express** (entrega nacional en 2 días), **Pigeon Connect** (mismo día bajo demanda) y **Pigeon Flash** (mismo día programada).

PigeonShip contrata repartidores independientes y gestiona programas dedicados de entrega para empresas. Los destinatarios reciben un enlace de seguimiento en `admin.pigeonship.com` que muestra el estado del pedido y la hora límite, y luego una página de finalización al entregarse.

## Formato del número de seguimiento de PigeonShip

Peek admite números de PigeonShip de 8 a 30 letras y dígitos; elimina espacios y pasa las letras a mayúsculas. Peek reconoce un formato de 18 caracteres que alterna dígitos y letras mayúsculas:

- **18 caracteres alfanuméricos** con el patrón `dígito-letra-dígito-letra-3 dígitos-letra-3 dígitos-letra-2 dígitos-letra-dígito-2 letras` (por ejemplo, `7P4K829H631N58R2TM`).

Los enlaces de seguimiento de PigeonShip tienen la forma `admin.pigeonship.com/shipping/tracking/package/<trackingNumber>/`. Al pegar ese enlace en Peek, Peek extrae el código del paquete y lo dirige directamente a PigeonShip. Con un código suelto de 18 caracteres con este formato, Peek pregunta solo a PigeonShip y muestra «PigeonShip tiene este paquete.» en cuanto responde. Para compararlo con otros formatos de EE. UU. y Canadá, consulta la [guía de formatos de números de seguimiento](guide:tracking-number-formats).

## Cómo funciona el seguimiento en vivo de PigeonShip

La página de seguimiento para destinatarios de PigeonShip es una vista resumen en lugar de un historial de escaneos. Mientras el pedido está activo, muestra «In transit» y al repartidor en un mapa con una hora límite de entrega (`dueDate`) cuando existe; tras la página, el pedido lleva un código de estado y una etiqueta como `16` y `Assigned`. La página solo muestra «Delivered» para el código `64`. Una vez completada la entrega, PigeonShip sustituye la página en esa misma URL por una pantalla de finalización que dice `Your delivery has been completed successfully.` con un enlace a las fotos de la entrega.

| Estado / página de PigeonShip | Qué significa |
| --- | --- |
| Estado `16` — `Assigned` | El pedido está en la ruta de un repartidor; la página muestra «In transit». |
| Fecha límite / `dueDate` | La hora objetivo de entrega mostrada en pedidos activos antes del depósito. |
| Estado `64` / `Delivered` o `Your delivery has been completed successfully.` | El repartidor completó la entrega; abrir el enlace propio de PigeonShip permite ver la foto de confirmación. |
| `Page not found` (404) | El número de seguimiento no está activo en el seguimiento en vivo de PigeonShip o contiene un error tipográfico. |

Como PigeonShip no publica un registro cronológico de escaneos en su página de destinatario, no verás escaneos intermedios de almacén o centro de clasificación como con [UPS](carrier:ups), [USPS](carrier:usps), [Canada Post](carrier:canada-post) o [Intelcom](carrier:intelcom). En su lugar, la página actualiza su estado en vivo conforme avanza la entrega y cambia a la pantalla final tras depositar el paquete.

## Entregas en el mismo día y qué revisar si se retrasa

PigeonShip ofrece entregas en el mismo día y al día siguiente en todo Estados Unidos y Canadá. Las reseñas de su sitio mencionan un SMS antes de llegar el repartidor y una foto tras dejar el paquete.

Si tu pedido sigue en `Assigned` pasada la hora estimada o el enlace muestra `Page not found`:

- **Comprueba posibles erratas:** verifica el código completo o el enlace completo `admin.pigeonship.com` del correo o SMS de la tienda; la web de PigeonShip responde `Page not found` para números sin página activa.
- **Contacta con PigeonShip:** usa su [formulario de contacto](https://www.pigeonship.com/contact-us) si el pedido sigue en «In transit» pasada su hora límite.
- **Revisa las instrucciones de entrega:** las reseñas en el sitio de PigeonShip mencionan repartidores que siguen las notas de la etiqueta y dejan foto de la entrega; abre el enlace original en `admin.pigeonship.com` para ver las fotos tras completarse. Para más pasos, consulta [cuando el seguimiento deja de actualizarse](guide:tracking-not-updating) y la [guía de entrega fallida](guide:missed-delivery).

## Seguir PigeonShip con Peek

Pega tu número de PigeonShip o el enlace completo `admin.pigeonship.com/shipping/tracking/package/...` en Peek. Si Peek no preselecciona PigeonShip para un formato poco habitual, elige **Cambiar** al añadir el paquete o **Cambiar transportista** en la pantalla del paquete.

Peek consulta directamente la página de seguimiento renderizada en servidor de PigeonShip mediante el [adaptador abierto de PigeonShip en universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/pigeonship). Lee el código y la etiqueta de estado del pedido, si figura entregado y la hora límite (`dueDate`) cuando existe, que Peek muestra como entrega prevista. Peek omite el nombre del destinatario, las coordenadas del repartidor en el mapa, la barra de progreso y las fotos de entrega — abre el enlace propio de PigeonShip para ver la foto.

Como PigeonShip muestra únicamente el estado actual, Peek añade una fila fechada cuando detecta cada cambio de fase: un pedido activo `Assigned` aparece como **En tránsito** con su hora prevista de entrega, y una entrega completada como **Entregado**.

Mientras tienes el paquete abierto en Peek, o el día de entrega con las notificaciones activadas, Peek consulta hasta cada 10 minutos mientras la página de seguimiento de PigeonShip responda. El resto del tiempo Peek comprueba cada hora. Como la página de PigeonShip no muestra paso de salida a reparto, Peek nunca cambia a las comprobaciones cada 2 minutos del día de entrega. Tras 12 horas con solo «Etiqueta creada» o «Admitido por el transportista», o dos días sin novedades, consulta cada hora lo sigas como lo sigas, y cada 6 horas tras siete días en «Etiqueta creada». Cuando la página de PigeonShip no responde y Peek sigue el paquete únicamente a través de servicios de seguimiento, consulta cada hora, cada 3 horas tras un día tranquilo y hasta cada 15 minutos el día en que el paquete está en reparto, mientras tengas el paquete abierto o las notificaciones activadas.

Cuando el escaneo más reciente tiene cuatro días y el paquete sigue en «Etiqueta creada», «Admitido por el transportista» o «En tránsito», Peek lo marca con el día de ese escaneo, como «Sin novedades desde el lun 12 oct». La página del paquete muestra entonces «Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a PigeonShip.», con un enlace «Pide ayuda a PigeonShip» a la página de contacto de PigeonShip. Tras tres semanas sin nuevos escaneos, indica «Nada nuevo desde hace más de tres semanas. Si el paquete aún no ha llegado, contacta con el vendedor. Peek sigue preguntando a PigeonShip.»

En los ajustes de notificaciones de Peek, elige **Cada escaneo**, **Pasos importantes** o **Solo la entrega** para decidir qué avisos recibir.

## Contactar con PigeonShip

- **Formulario de contacto oficial:** [PigeonShip Contact Us](https://www.pigeonship.com/contact-us) (elige United States, Canada u Other y describe tu consulta de entrega)
- **Sitio oficial:** [pigeonship.com](https://www.pigeonship.com/)
- **Oficina en EE. UU.:** PigeonShip USA, 1675 N Freedom Blvd Ste 9B, Provo, Utah 84604-6929
- **Oficina en Canadá:** PigeonShip Canada, 405 Dundas St W, Oakville, ON L6M 5P9

## Preguntas frecuentes

### ¿Por qué el seguimiento de PigeonShip no muestra una lista de escaneos anteriores?

La página en vivo de PigeonShip es solo un resumen: muestra el estado actual del pedido («In transit» o «Delivered») y la hora límite en lugar de un historial de escaneos por almacenes.

### ¿Dónde puedo ver la foto de entrega de PigeonShip?

Peek no almacena ni muestra fotos de entrega de PigeonShip. Cuando tu paquete figure como entregado, abre el enlace original `admin.pigeonship.com/shipping/tracking/package/...` de tu correo o SMS para ver la página de finalización y las fotos de PigeonShip.

### ¿Qué significa el estado `Assigned` en PigeonShip?

`Assigned` (código de estado `16`) es el estado interno del pedido tras la página de PigeonShip mientras está en la ruta de un repartidor; la página muestra «In transit». Peek lo muestra como **En tránsito**.

### ¿Entrega PigeonShip tanto en Estados Unidos como en Canadá?

Sí. PigeonShip ofrece entregas en el mismo día, al día siguiente y de última milla a escala nacional en Estados Unidos y Canadá, con oficinas en Provo (Utah) y Oakville (Ontario).

:::sources
- [PigeonShip](https://www.pigeonship.com/)
- [PigeonShip Contact Us](https://www.pigeonship.com/contact-us)
- [adaptador de PigeonShip en universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/pigeonship)
:::
