---
title: Seguimiento CouriersPlease: números, estados y ayuda
description: Rastrea un paquete de CouriersPlease en Australia: formato del número de envío, estados, puntos HUBBED, nueva entrega, Delivery Choices y reclamaciones.
slug: seguimiento-couriersplease
published: 2026-11-23
updated: 2026-11-23
---

CouriersPlease (Couriers Please Pty Limited, parte de FMH Group) es una empresa australiana de paquetería que reparte en las principales capitales y zonas regionales de Australia a través de socios franquiciados locales (*Franchise Partners*). Para rastrear un paquete, introduce el número de envío en la [página de seguimiento de CouriersPlease](https://www.couriersplease.com.au/tools-track) —que admite hasta 10 números separados por comas— o pégalo en el cuadro de arriba. Ese código te lo facilita la tienda o el remitente que contrató el envío.

## Número de seguimiento de CouriersPlease: cómo es

CouriersPlease lo llama *consignment number* (número de envío o de seguimiento). Su página de nueva entrega señala que puede empezar por `CP` y tener hasta 20 caracteres. Para CouriersPlease, Peek acepta entre 8 y 30 letras y dígitos (con al menos un dígito), lo que abarca envíos estándar, etiquetas de bulto, cupones prepagados y códigos SSCC que imprimen algunos comercios. No todos los números de CouriersPlease empiezan por `CP`.

| Número (inventado) | Qué es |
| --- | --- |
| `CPABC4829173640` | Número de envío típico de CouriersPlease: `CP`, letras y luego cifras. |
| `CPA48291736405` | Otro formato de CouriersPlease con prefijo `CP`, algo más corto. |
| `CP482917367AU` | No es de CouriersPlease: dos letras, nueve cifras y `AU` corresponden a un envío postal internacional, como los de [Australia Post](carrier:australia-post). |

**Dónde encontrarlo.** Revisa primero el correo de confirmación de pedido de la tienda. Los correos propios de CouriersPlease llegan desde `noreply@couriersplease.com.au` (solo si el remitente les dio tu correo) y sus SMS aparecen con el remitente `Courierspls`. Si te dejaron un aviso en papel, incluye un número de tarjeta de nueva entrega (*redelivery card number*), que también sirve en el formulario de nueva entrega. Tienes más ideas en [cómo encontrar tu número de seguimiento](guide:find-tracking-number).

**¿Número no válido o sin información?** Comprueba que no haya erratas y confirma con la tienda que el pedido viajó con CouriersPlease. Mientras no existan lecturas registradas para ese código, el rastreador oficial lo muestra como no válido o sin historial.

## Qué significan los estados de CouriersPlease

En su [guía para destinatarios](https://www.couriersplease.com.au/faqs), CouriersPlease explica que informa en cinco hitos principales: aviso de recogida del paquete, recogida al remitente, llegada al almacén, salida a reparto y entrega o traslado a un punto de recogida. En el rastreador los mensajes aparecen en inglés; estos son los estados principales que muestra CouriersPlease y mapea [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper), en el orden habitual:

| Estado de CouriersPlease | Qué significa para ti |
| --- | --- |
| `Shipment information received` | El remitente creó el envío. Es posible que CouriersPlease aún no tenga el paquete. |
| `Your parcel has been picked up` | CouriersPlease recogió el paquete en el remitente. |
| `Pickup attempt was unsuccessful. Awaiting rebook confirmation by sender` | Falló la recogida en origen; el vendedor debe reprogramarla. |
| `Arrived at facility for processing` | Llegó a una instalación de clasificación de CouriersPlease. |
| `Parcel requires further processing` | El paquete requiere procesamiento adicional en el centro; sigue en la red. |
| `Processed at delivery centre` | Procesado en el centro de distribución. |
| `In transit to delivery centre` | En ruta hacia un centro de distribución (suele añadir las dos ciudades entre paréntesis). |
| `Arrived at delivery centre` | Llegó al centro de distribución; no tienes que hacer nada. |
| `In transit to regional delivery centre` | Entregado a un socio regional de reparto o en ruta regional. |
| `Onboard for delivery` | Cargado en el vehículo del repartidor o franquiciado para entregarlo. |
| `Delivery rescheduled for next business day` | Devuelto al almacén sin entregar y reprogramado para el siguiente día laborable. |
| `Delivery attempted – Unsafe to leave – Sent to a collection point` | No hubo nadie para recibirlo ni un lugar seguro; va camino de un punto de recogida. |
| `Delivery attempted – Signature required – Redirected to a collection point` | Se requería firma y no había nadie; redirigido a un punto de recogida. |
| `Unable to deliver` | No se pudo entregar y el incidente requiere revisión por parte de soporte. |
| `Processing for collection at a nearby collection point` | Está en el punto de recogida, pero todavía no está listo para retirarlo. |
| `Pickup at Collection Point – Ready for collection` | Listo para recoger en el punto con tu código PIN. |
| `Delivered with Authority to Leave (ATL)` | Dejado en un lugar seguro de tu dirección sin firma. |
| `Delivered` | Entregado en el domicilio. |
| `Parcel has been collected from` | Recogido en el punto cuyo nombre aparece a continuación. |
| `Collection period expired – Return to sender in progress` | Venció el plazo de recogida y el paquete vuelve al remitente. |

`Shipment information received` es el mensaje que más confunde: los datos del envío ya existen, pero el paquete puede seguir esperando en la tienda. Si no avanza, pide al vendedor que confirme la entrega al mensajero.

Avisos como `Delivery rescheduled` o las líneas `Delivery instructions –` (nueva fecha, punto de recogida, vecino, autorización de depósito o nueva dirección) solo reflejan una instrucción o cambio de fecha sin mover todavía el paquete.

## Entrega, puntos HUBBED, nueva entrega y Delivery Choices

**Plazos orientativos.** La [guía para destinatarios](https://www.couriersplease.com.au/faqs) indica de 1 a 3 días laborables entre áreas metropolitanas, unos días extra de costa a costa (por ejemplo de este a oeste), más tiempo para zonas regionales o remotas y una semana adicional en picos como Navidad. Todos los plazos son estimaciones que incluyen preparación del pedido, paso por depósito y posibles nuevas entregas o recogidas, y pueden verse afectados por el clima, el tráfico o incidencias.

**Si no estás en casa (Authority to Leave).** Al salir a reparto, el conductor llama a la puerta o al timbre salvo que el remitente haya pedido entrega sin contacto. Según la cláusula 5.3 de las [Condiciones de transporte](https://www.couriersplease.com.au/media/Terms_and_Conditions_of_Carriage_12_October_2023_ed4c2f9d36.pdf) y la página [Delivery Choices](https://www.couriersplease.com.au/delivery-choices), los envíos se gestionan por defecto con autorización de depósito (*Authority to Leave*, ATL): si hay alguien en el domicilio, el conductor registra `SIG` con el nombre del receptor; si no hay nadie, busca un lugar seguro. Cuando no existe un sitio seguro (por ejemplo, pisos o portales con mucho paso) o se exige firma, el paquete pasa a un punto de recogida cercano —salvo si supera 10 kg o 100 cm cúbicos (aproximadamente el tamaño de una maleta de cabina), caso en el que te pedirán concertar una nueva entrega. En zonas regionales, CouriersPlease indica que los paquetes siempre se dejan en un lugar seguro (ATL).

**Puntos de recogida HUBBED.** CouriersPlease colabora con HUBBED para ofrecer más de 3.000 puntos de recogida con horario ampliado en Australia. Puedes localizarlos por barrio o código postal en el [buscador de puntos de recogida](https://www.couriersplease.com.au/resources/find-a-collection-point), filtrando por *Drop Off*, *Redirections* o *Failed deliveries*. Si el remitente dio tu correo electrónico, CouriersPlease te avisa por e-mail en un plazo de 24 horas; de lo contrario deja un aviso en papel. Según la cláusula 5.4 de las Condiciones de transporte, la entrega se considera realizada cuando CouriersPlease envía el código PIN: dispones de **7 días** tras ese aviso para recoger el paquete; pasado ese plazo, se guarda en el almacén más cercano otros **5 días** y después se devuelve al cliente a su cargo. Si no recibiste tu PIN, solicítalo en la [página Get PIN de HUBBED](https://hubbed.com/getpin/) (para ti, tu empresa u otra persona).

**Nueva entrega.** Puedes programarla en la [página de nueva entrega](https://www.couriersplease.com.au/book/redelivery) con el número de envío o el de la tarjeta de aviso. La guía para destinatarios añade que, si tu paquete se llevó a un punto de recogida, puedes solicitar una nueva entrega en un plazo de 6 días. El centro de ayuda de CouriersPlease dice en cambio que un paquete ya desviado a un punto de recogida no puede volver a entregarse en tu dirección y hay que recogerlo allí; ante la duda, pregunta al soporte antes de reservarla.

**Delivery Choices.** Si el remitente facilita tanto tu correo como tu móvil al reservar, recibirás un enlace seguro con cinco opciones de [Delivery Choices](https://www.couriersplease.com.au/delivery-choices):

- pedir firma obligatoria (*Signature required*: si no hay nadie, va a un punto de recogida o al depósito);
- redirigir a un punto de recogida cercano (*Collect nearby*);
- autorizar la entrega a un vecino hasta tres casas a cada lado (*Leave with a neighbour*);
- cambiar la dirección de entrega (*Change the address*);
- elegir otro día dentro de los tres días posteriores a la fecha original (*Choose another day*).

## Paquete retrasado, perdido o dañado con CouriersPlease

**Retraso o sin novedades.** Según la guía para destinatarios, la mayoría de los paquetes retrasados llegan en un plazo de 24 a 48 horas laborables desde la estimación inicial. Comprueba los avisos de servicio en la portada de couriersplease.com.au y, si algo no cuadra, abre el *Virtual Assistant* (icono de chat abajo a la derecha), elige *Assistance with a Delivery* y envía la consulta una sola vez, porque repetirla retrasa la respuesta. Más detalles en [seguimiento que no se actualiza](guide:tracking-not-updating).

**Marcado como entregado pero no recibido.** Revisa lugares protegidos en la entrada, pregunta a convivientes, vecinos o portería y avisa después a la tienda y al *Virtual Assistant*. Consulta también [entregado pero no recibido](guide:delivered-not-received) y [paquete perdido](guide:lost-parcel).

**Daños, extravío y cobertura Parcel Protect.** En envíos nacionales dentro de Australia, [Parcel Protect Domestic Cover](https://www.couriersplease.com.au/parcel-protect-domestic-cover) ofrece cobertura automática gratuita de hasta 1.000 AUD, pero no cubre los primeros 100 AUD de pérdida, excluye los paquetes dejados con *Authority to Leave* (ATL), el embalaje deficiente y determinadas mercancías (artículos de alto valor, efectivo, joyas, tarjetas regalo, cuadros con cristal, espejos, flores, plantas, productos perecederos, efectos personales y electrónica, mercancías peligrosas). El aviso escrito por pérdida o daño debe enviarse en un plazo de **14 días laborables** desde la entrega o desde la fecha en que debía entregarse (lo que ocurra antes). Si el envío se reservó mediante un agente intermediario, la reclamación debe tramitarse a través de ese agente. Como destinatario, contacta primero con la tienda, que es la titular de la cuenta. Fuera de Parcel Protect, la cláusula 7.3 de las Condiciones de transporte limita la responsabilidad general a repetir el servicio, abonar su coste o 100 AUD (sin perjuicio de la normativa australiana de consumo). Más ayuda en [paquete dañado](guide:damaged-parcel).

## Contacto de CouriersPlease y protección contra estafas

- **Asistente virtual:** haz clic en el icono de chat abajo a la derecha en [couriersplease.com.au](https://www.couriersplease.com.au/faqs) y elige *Assistance with a Delivery* (o escribe `feedback` para comentarios). Presenta cada consulta una sola vez.
- **Centro de ayuda:** accesible desde la opción *Help centre* del sitio couriersplease.com.au.
- **Dirección social:** Couriers Please Pty Limited (ABN 76 006 144 734), Rosehill Industrial Estate Building C South, Level 1, 5–7 Shirley Street, Rosehill, NSW 2142, Australia.

> Según la página de [protección contra estafas](https://www.couriersplease.com.au/scam-protection), los correos oficiales de CouriersPlease llegan de `noreply@couriersplease.com.au` y sus SMS muestran el remitente `Courierspls`. CouriersPlease nunca te pedirá cambiar datos bancarios, pagar una tasa para liberar un paquete, introducir tarjetas o contraseñas ni responder `Y` para activar un enlace (desconfía de dominios falsos como `couriersplease.addliik.com` o `no-reply@wufoo.com`). Si dudas, borra el mensaje, rastrea directamente en couriersplease.com.au y denúncialo a CouriersPlease y a Scamwatch.

## Seguir paquetes de CouriersPlease con Peek

[Peek](/) identifica CouriersPlease al instante con números que siguen el patrón `CP` (como `CPABC4829173640` o `CPA48291736405`) y también con enlaces de couriersplease.com.au/tools-track que lleven `no=` o `trackingCode=`. No pide cuenta, código postal ni teléfono. Si tu número de CouriersPlease no empieza por `CP`, pega el enlace de seguimiento o selecciona CouriersPlease con «Cambiar» al añadirlo o «Cambiar transportista» en un paquete ya añadido.

Peek consulta el rastreador oficial de CouriersPlease y muestra cada lectura con la redacción inglesa del transportista, la hora que da CouriersPlease (hora de Sídney, también fuera de Nueva Gales del Sur), el estado australiano cuando figura y la fecha estimada de entrega (*Delivery ETA*) una vez que el paquete supera el registro inicial (y hasta la entrega o devolución). Elimina el marcado HTML y los enlaces de soporte incrustados, y no muestra dirección del punto de recogida, PIN de HUBBED ni foto de prueba de entrega.

Peek consulta directamente a CouriersPlease hasta cada 10 minutos mientras tienes el paquete abierto o el día de reparto con alertas activas, y cada 2 minutos a partir de las 6:00 hora de Sídney el día en que CouriersPlease registra la salida a reparto (`Onboard for delivery`), mientras su rastreador responde; si no, cada hora. Peek cuenta ese día en hora de Sídney, la de los escaneos de CouriersPlease: en Perth, esas consultas empiezan a las 3 o las 4 y terminan a las 21 o las 22. Tras 12 horas en «Etiqueta creada» o «Admitido por el transportista», o tras dos días sin novedades, pasa a revisar cada hora incluso con la pantalla abierta, y cada 6 horas tras siete días todavía en «Etiqueta creada». Cuando recurre a servicios de seguimiento, consulta cada hora, cada 3 horas tras un día sin cambios y hasta cada 15 minutos el día de entrega.

Si un paquete en «Etiqueta creada», «Admitido por el transportista» o «En tránsito» pasa cuatro días sin un nuevo escaneo, Peek lo marca con el día de ese último escaneo (por ejemplo, «Sin novedades desde el lun 12 oct»), muestra «Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a CouriersPlease.» y ofrece el enlace «Pide ayuda a CouriersPlease» al centro de ayuda de CouriersPlease. Si pasan más de tres semanas sin novedades, cambia a: «Nada nuevo desde hace más de tres semanas. Si el paquete aún no ha llegado, contacta con el vendedor. Peek sigue preguntando a CouriersPlease.»

Puedes elegir avisos para «Cada escaneo», «Pasos importantes» o «Solo la entrega». Peek no puede reservar una nueva entrega, solicitar un PIN de HUBBED ni abrir una reclamación de Parcel Protect; para esas gestiones, acude a CouriersPlease o a la tienda.

## Preguntas sobre el seguimiento de CouriersPlease

### ¿Cuánto tarda CouriersPlease en entregar un paquete?

Según su guía para destinatarios, calcula de 1 a 3 días laborables entre áreas metropolitanas, unos días extra de un extremo a otro del país, más tiempo para áreas regionales o remotas y una semana adicional en temporadas altas como Navidad.

### ¿Por qué no se actualiza mi seguimiento de CouriersPlease?

En trayectos interestatales, entregas con socios regionales o fases de clasificación en centro pueden pasar varios días sin lecturas nuevas. CouriersPlease señala que la mayoría de los paquetes retrasados llegan en 24 a 48 horas laborables desde la estimación inicial; si pasa más tiempo, escribe al *Virtual Assistant* en couriersplease.com.au.

### ¿Cómo funcionan los puntos de recogida HUBBED y el código PIN?

Si el repartidor no puede dejar un paquete de hasta 10 kg y 100 cm cúbicos de forma segura o necesita firma, lo lleva a uno de los más de 3.000 puntos HUBBED. Según las Condiciones de transporte, tienes 7 días desde el aviso del PIN para recogerlo y, si vence el plazo, permanece 5 días en el almacén antes de devolverse. Si no recibiste el PIN, pídelo en hubbed.com/getpin/.

### ¿Qué hago si mi paquete llega dañado o se entregó con Authority to Leave?

Avisa enseguida a la tienda remitente. Las reclamaciones por escrito bajo Parcel Protect tienen un plazo de 14 días laborables (cobertura de hasta 1.000 AUD excluyendo los primeros 100 AUD), pero Parcel Protect no cubre paquetes entregados con *Authority to Leave* (ATL).

:::sources
- [CouriersPlease: Track A Parcel Delivery](https://www.couriersplease.com.au/tools-track) – rastreo de hasta 10 números separados por comas
- [CouriersPlease: Guide to receiving](https://www.couriersplease.com.au/faqs) – cinco hitos de seguimiento, plazos orientativos, límites de 10 kg / 100 cm, 6 días para nueva entrega, 24–48 horas laborables en retrasos, Virtual Assistant
- [CouriersPlease: Book A Parcel Redelivery](https://www.couriersplease.com.au/book/redelivery) – número de envío o tarjeta de nueva entrega, prefijo CP, hasta 20 caracteres
- [CouriersPlease: How do I arrange a redelivery?](https://support.couriersplease.com.au/hc/en-au/articles/31312747333785-How-do-I-arrange-a-redelivery) – sin nueva entrega para un paquete ya desviado a un punto de recogida
- [CouriersPlease: Find a Collection Point](https://www.couriersplease.com.au/resources/find-a-collection-point) – búsqueda por barrio o código postal y filtros de puntos
- [CouriersPlease: Delivery Choices](https://www.couriersplease.com.au/delivery-choices) – ATL por defecto, cinco opciones de entrega, regla regional ATL
- [CouriersPlease: Parcel Protect Domestic Cover](https://www.couriersplease.com.au/parcel-protect-domestic-cover) – límite de 1.000 AUD, exclusión de los primeros 100 AUD y de ATL, plazo de 14 días laborables, reservas por intermediarios
- [CouriersPlease: Terms and Conditions of Carriage](https://www.couriersplease.com.au/media/Terms_and_Conditions_of_Carriage_12_October_2023_ed4c2f9d36.pdf) – cláusulas 5.3 (ATL / SIG), 5.4 (7 días tras el PIN + 5 días en depósito), 7.3 (límite de responsabilidad) y domicilio social
- [CouriersPlease: Scam protection](https://www.couriersplease.com.au/scam-protection) – remitentes oficiales noreply@couriersplease.com.au y Courierspls, señales de phishing, Scamwatch
- [CouriersPlease: Our Story](https://www.couriersplease.com.au/our-story) – FMH Group, Franchise Partners, más de 3.000 puntos HUBBED
- [HUBBED: Get PIN](https://hubbed.com/getpin/) – solicitud de código PIN para recoger en punto HUBBED
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – detección de números y enlaces de CouriersPlease, tabla de estados y datos leídos por Peek
:::
