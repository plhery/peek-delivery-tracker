---
title: Seguimiento MHI: estados de Trackmail y quién entrega
description: ¿Tienes un número MHI de una tienda online? Dónde seguirlo, qué significan los estados de Trackmail, qué mensajería entrega en tu país y a quién preguntar.
slug: seguimiento-mhi
published: 2026-11-23
updated: 2026-11-23
---

MHI (Mail Handling International) es una empresa de Bristol que envía pedidos de tiendas online del Reino Unido a compradores en el extranjero. Las tiendas mandan sus paquetes a su centro de procesamiento de Bristol; MHI los exporta y entrega cada uno a una mensajería o servicio postal de tu país, que lo reparte con su propio número. Sigue el código MHI en la web de seguimiento de MHI, [Trackmail](https://www.trackmail.co.uk/), o en el cuadro de arriba. El número lo facilita la tienda: MHI asigna un código de seguimiento por paquete a sus clientes, así que búscalo en el correo de envío o en los detalles de tu pedido.

## Número de seguimiento de MHI: cómo es

MHI no publica ningún formato para compradores. Estas son las formas que adoptan sus números, con dígitos inventados:

| Número | Qué es |
| --- | --- |
| `MHI482W736405829173` | MHI, tres dígitos, luego W y 12 dígitos: MHI. |
| `MHI482WBB736405829` | MHI, tres dígitos, luego WBB y 9 dígitos: MHI. |
| `MHI-482-P736405829` | MHI, tres dígitos, luego P y 9 dígitos: MHI. Las etiquetas lo imprimen con guiones. |
| `MHI482736405829173` | MHI, tres dígitos y 12 dígitos más: MHI. |
| `RR482917367US` | Dos letras, 9 dígitos y dos letras: el número postal del mismo envío, que Trackmail muestra para algunos envíos sin seguimiento. |
| Cualquier otro formato | El propio número de la mensajería que entrega, mostrado en Trackmail tras «Tracking number:» en un envío con seguimiento. |

Los tres dígitos tras MHI identifican la cuenta de la tienda en MHI. Escribe un número P en Trackmail con sus guiones, tal como aparece impreso: Trackmail solo lo encuentra con ellos. Un mismo paquete puede acabar con dos números, el de MHI y el de la mensajería. Más detalles en [formatos de números de seguimiento](guide:tracking-number-formats).

## Estados de seguimiento de MHI explicados

Trackmail está en inglés. Encima del historial indica el servicio elegido por la tienda: «The seller requested this item be shipped via the following service:», seguido de `TRACKED` o `UNTRACKED`. Debajo aparecen los eventos, el más reciente primero, cada uno con fecha, hora y texto, y la mayoría con categoría y lugar: el escaneo de exportación de MHI y después los del transportista con la redacción de MHI. En el orden en que los recorre un paquete:

| Trackmail indica | Qué significa para ti |
| --- | --- |
| `Your order has been processed at the UK export facility. It is now on route to the receiver.` | MHI ha procesado el paquete en el Reino Unido y lo ha enviado al extranjero. En un envío sin seguimiento suele ser la única línea. |
| `Shipment information sent to last mile service provider` | La mensajería de tu país tiene los datos del paquete, pero aún no el paquete. |
| `Notification sent to recipient` | La mensajería te ha avisado de que el paquete va en camino. |
| `Handed Over to Delivery Partner` | La mensajería ha recibido el paquete. |
| `Arrived at sorting center` | Escaneo en un centro de la red de la mensajería. |
| `Dispatched from sorting center` | Ha salido de ese centro. |
| `Assigned to Transport` | Viaja en un vehículo entre centros. |
| `Shipment at final delivery center` | Lo tiene el depósito encargado de repartirlo. |
| `Delivery scheduled in the final delivery depot` | Está programado en el reparto del día. |
| `Delivery attempted` | Falló el intento de entrega: nadie recibió el paquete. |
| `Arrived at Pick-up Point` | Te espera en un punto de recogida. |
| `Successfully delivered` | Entregado o recogido. |

Fíjate en el texto, no en la categoría contigua. Trackmail agrupa `Shipment at final delivery center` y `Arrived at Pick-up Point` bajo «Out for delivery», aunque el primero ocurre antes del reparto y el segundo significa que debes ir a recogerlo. También coloca `Delivery attempted` bajo «Delivery», igual que una entrega real. El reparto y un intento fallido pueden repetirse varios días.

Las horas en Trackmail no indican zona horaria: el escaneo de MHI está en hora del Reino Unido y los de la mensajería en su hora local.

Si no reconoce un número, Trackmail muestra una sola línea que empieza por `No details found for Tracking Code, or Tracking Code not recognised.` y añade que, si hiciste el pedido hace poco, puede que MHI aún no tenga los datos.

## Entrega de MHI: quién trae tu paquete

MHI no entrega directamente en el extranjero. Indica que colabora con más de 100 transportistas para llegar a 252 países y territorios. En su centro de Bristol se escanea el código de barras de cada paquete y el sistema de MHI elige el transportista de última milla según el país de destino. Las tiendas pueden contratar envíos con seguimiento, sin seguimiento o exprés, así que quién entrega depende de dónde vivas y de lo que haya pagado la tienda.

**Envíos con seguimiento.** Trackmail puede mostrar el número de la mensajería que entrega tras «Tracking number:», junto con el enlace «View carrier tracking» a su web. Para consultas, Trackmail indica usar ese enlace o contactar con el vendedor.

**Envíos sin seguimiento.** Trackmail avisa de que la información es limitada y a menudo muestra solo el escaneo de exportación de MHI. Para algunos envíos da un «local poster number»: si se lo facilitas a tu servicio postal u oficina de correos, dice Trackmail, quizá puedan darte más información. Pruébalo también en el seguimiento de tu servicio postal: [USPS](carrier:usps), [Canada Post](carrier:canada-post), [Australia Post](carrier:australia-post) o An Post.

Los avisos, intentos, nuevas entregas y puntos de recogida siguen las normas de la mensajería final: consulta su seguimiento desde «View carrier tracking» y lee [entregas fallidas](guide:missed-delivery).

## Paquete de MHI sin actualizaciones, perdido o devuelto

**`No details found…`.** Revisa el número y escribe los números P con guiones. Si compraste hace poco, puede que MHI aún no tenga los datos: prueba más tarde.

**Solo el escaneo de exportación.** En un servicio sin seguimiento, Trackmail puede quedarse ahí: usa el «local poster number» con tu servicio postal si aparece alguno, o pregunta a la tienda. En uno con seguimiento, puede que la mensajería todavía no lo haya escaneado; cuando salga su número, su propia web puede ir más actualizada. Consulta [por qué el seguimiento deja de actualizarse](guide:tracking-not-updating).

**Entregado pero no está.** La entrega la realizó la mensajería local: revisa su seguimiento mediante «View carrier tracking» y luego contacta con la tienda. Consulta [entregado pero no recibido](guide:delivered-not-received).

**Perdido o dañado.** Contacta con la tienda. MHI trabaja para la tienda, no publica un proceso de reclamación para destinatarios y el propio Trackmail te remite al comercio. Consulta [paquetes perdidos](guide:lost-parcel).

**Aduanas.** MHI ofrece a las tiendas varias modalidades aduaneras, entre ellas DDP (Delivered Duty Paid), así que si queda algo por pagar depende de lo que eligiera la tienda. Lo que podrías pagar se explica en [paquetes retenidos en aduanas](guide:customs).

**Devuelto.** Cuánto tiempo espera un paquete sin recoger antes de volver depende de las normas de la mensajería que entrega: consulta su seguimiento. Si regresa, pide un reembolso o un reenvío a la tienda. Consulta [devuelto al remitente](guide:returned-to-sender).

## Teléfono de contacto y atención al cliente de MHI

Canales indicados por MHI:

- **Consultas de seguimiento:** correo tracking@mhi.co, indicado en su página eCommerce delivery y en la página de contacto de Nexae (su plataforma de envíos).
- **Teléfono:** +44 (0) 117 977 6655, número británico que aparece junto a su dirección de Bristol, 82 Feeder Road, BS2 0TQ. MHI no da horarios ni dice que atienda consultas de paquetes en esa línea.
- **Formulario Enquiries:** para consultas generales. MHI avisa de que las consultas de seguimiento enviadas por este formulario no se atienden ni se tramitan.

Consejo de MHI a los compradores: consultar Trackmail o contactar con el vendedor o el mercado online. La tienda es cliente de MHI y puede reclamarle; para una entrega fallida o una recogida, acude a la mensajería indicada en Trackmail.

## Seguir un paquete de MHI con Peek

[Peek](/) reconoce un número MHI por su forma en las cuatro series anteriores, con o sin guiones. Las etiquetas antiguas con solo dos dígitos tras MHI u ocho dígitos al final no se consultan: compruébalas en Trackmail. También puedes pegar un enlace de Trackmail que incluya el número; Peek lo lee como MHI si cumple el formato.

Peek consulta Trackmail y muestra el estado y el historial, con el lugar de cada escaneo en un mapa. Trackmail no da fecha prevista de entrega, peso, prueba de entrega ni firmante, así que Peek no recibe nada de eso de MHI, y omite tu dirección y el código postal del enlace del transportista.

Cuando el enlace de Trackmail apunta a un transportista que Peek conoce, la página del paquete muestra «Entrega con» ese transportista y su número. Mientras ese transportista solo tenga el preaviso, sin escaneo propio, la página indica «Anunciado a …, aún no recibido». Si Peek lee directamente el seguimiento de ese transportista, lo consulta con ese número y cambia a él en cuanto muestra avances más recientes. Si Peek no conoce al transportista, no lo nombra: usa «View carrier tracking» en Trackmail. En envíos sin seguimiento con «local poster number», Peek puede probar ese número en el servicio postal nacional, normalmente el del país desde el que añades el paquete, y cambiar del mismo modo. Pero no con todos los correos: un número `RR…` terminado en `AU` añadido desde Australia no se envía a Australia Post, así que revísalo en el seguimiento de Australia Post.

Mientras tengas el paquete abierto en Peek, o el día de entrega si las notificaciones están activadas, Peek comprueba hasta cada 10 minutos, y a partir de las 6:00 (hora local) del día en que sale a reparto hasta cada 2 minutos mientras Trackmail (o el seguimiento propio del transportista que tome el relevo) responda. Después de dos días sin novedades (12 horas mientras el paquete siga en «Etiqueta creada» o «Admitido por el transportista»), Peek comprueba cada hora; y si permanece siete días en «Etiqueta creada», cada 6 horas.

Si un paquete que sigue en «Etiqueta creada», «Admitido por el transportista» o «En tránsito» pasa cuatro días sin un nuevo escaneo, Peek lo marca con «Sin novedades desde hace cuatro días» (o «Sin novedades desde el …»), explica «Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a MHI.» Peek no tiene página de ayuda de MHI: el enlace «Pide ayuda a …» solo aparece cuando Peek sigue a la empresa que entrega, y solo si esta tiene una. Puedes activar avisos de «Cada escaneo», «Pasos importantes» o «Solo la entrega». Peek no puede contactar con MHI, la mensajería ni la tienda por ti.

## Preguntas sobre el seguimiento de MHI

### ¿Dónde está mi paquete de MHI?

La línea más reciente de Trackmail muestra la fase actual y los escaneos de la mensajería indican una localidad. Antes de `Handed Over to Delivery Partner` lo tiene MHI o viaja hacia tu país; después lo tiene la mensajería local y, si es un envío con seguimiento, su número sale en Trackmail tras «Tracking number:».

### ¿Qué significa «processed at the UK export facility»?

MHI ha recibido el paquete en su centro de exportación del Reino Unido y lo ha despachado hacia ti. Es el escaneo propio de MHI y, en envíos sin seguimiento, suele ser el único.

### ¿Es fiable trackmail.co.uk?

Sí. Trackmail es la web de seguimiento oficial de MHI: el enlace «Track my Parcel» en la web de MHI lleva allí. Companies House registra a Mail Handling International Limited como empresa activa, constituida en 1996 en la dirección de Bristol que indica MHI.

### ¿Tiene MHI teléfono de atención al cliente?

Publica el +44 (0) 117 977 6655, pero para seguimiento pide escribir a tracking@mhi.co o contactar con la tienda. Para la entrega final, hay que dirigirse a la mensajería que figure en Trackmail.

:::sources
- [MHI: Home](https://www.mhi.co/) – enlace Track my Parcel a Trackmail, consultas de paquetes al vendedor, formulario general no válido para seguimiento, más de 100 transportistas, 252 países y territorios, con seguimiento, sin seguimiento y exprés, DDP, dirección, teléfono
- [MHI: eCommerce Delivery](https://www.mhi.co/ecommerce-delivery) – centro de Bristol, transportista según país, códigos de seguimiento para tiendas, exportación, tracking@mhi.co
- [Nexae: Contact Us](https://nexae.co.uk/ContactUsHomepage) – tracking@mhi.co, Nexae pertenece a MHI
- [MHI: Trackmail](https://www.trackmail.co.uk/) – web de seguimiento de MHI, en inglés, contacto de seguimiento
- [Companies House: Mail Handling International Limited](https://find-and-update.company-information.service.gov.uk/company/03241562) – activa, constituida el 23 de agosto de 1996, domicilio social 82 Feeder Road, Bristol
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formatos de números, textos y categorías de Trackmail, línea de servicio, enlace del transportista, número postal local, texto de número desconocido, zonas horarias, qué muestra Peek y a qué transportista o correo pregunta
:::
