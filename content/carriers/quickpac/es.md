---
title: Seguimiento Quickpac: número de 18 dígitos y contacto
description: Sigue un paquete Quickpac en Suiza con su código de 18 dígitos: en el rastreador inglés de Planzer, Shipped significa entregado. Contactos y recogidas.
slug: seguimiento-quickpac
published: 2026-11-23
updated: 2026-11-23
---

Quickpac es un servicio suizo de paquetería integrado en Planzer Paket (Planzer Parcel en inglés) desde 2024 que entrega compras de tiendas a domicilio en códigos postales seleccionados de Suiza. Tu número de seguimiento de 18 dígitos te lo envía el remitente —normalmente en el correo de envío— y aparece bajo el código de barras del paquete. Introdúcelo en la [página de seguimiento de Quickpac](https://quickpac.ch/en/tracking) o pégalo en el recuadro superior.

## Número de seguimiento Quickpac e ID de envío

Solo las empresas envían con Quickpac, de modo que el número llega por el remitente: en su correo o enlace de envío, en correos y SMS que Quickpac envía en su nombre y en la tarjeta «Abholinformationen» tras una ausencia. Los mensajes enviados por Planzer Paket llevan el asunto «A delivery is on its way to you».

Quickpac lo llama número de paquete o de envío, y el portal de Planzer «Shipment Number». No sirven para el seguimiento el «Reference number» (referencia interna del remitente), un código de recogida ni el código de entrega de la tarjeta de Planzer Paket.

Los ejemplos siguientes usan dígitos inventados; los dos últimos pertenecen a [otros transportistas](guide:tracking-number-formats):

| Número | A quién pertenece |
| --- | --- |
| `448291736405829173` | Quickpac: 18 dígitos empezando por `44`. |
| `44.82.917364.05829173` | El mismo con puntos, tal como lo muestra el buscador de Quickpac. |
| `994829173640582917` | [Swiss Post](carrier:swiss-post): 18 dígitos empezando por `99` o `98`. |
| `91346097482917364058` | Planzer: 20 dígitos empezando por `91346097`. |

Si el remitente lo autorizó, Quickpac transfiere los paquetes imposibles de entregar a Swiss Post y el seguimiento puede mostrar un enlace «Post tracking». Un número que empieza por `44` con 17 o 19 dígitos suele tener un dígito de menos o de más. ¿Sin código? Pídelo al remitente o consulta [cómo encontrar tu número de seguimiento](guide:find-tracking-number).

## Estados de seguimiento de Quickpac y su significado

Los paquetes Quickpac se consultan en el portal de Planzer y en el buscador de quickpac.ch. Ambos siguen el idioma del navegador (inglés, alemán, francés o italiano), y el de Planzer recurre al inglés por defecto. Ninguno publica un glosario oficial. Los estados en inglés:

| Estado | Qué significa para ti |
| --- | --- |
| `Recorded` | Quickpac recibió los datos del envío del remitente. |
| `Delivery day unknown` | Aún no hay día de entrega fijado. |
| `Estimated delivery` | Día previsto de entrega. |
| `Transferred` | [En tránsito](guide:tracking-statuses). |
| `In delivery` | En reparto. |
| `Your consignment is at the following pick-up point` | Esperando en ese punto de recogida; `Latest collection date` es tu último día. |
| `Shipped` | Entregado, no «enviado por la tienda». |
| `Delivered`, `Parcel delivered` | Entregado. |

`Shipped` confunde a menudo: en el seguimiento en inglés de Planzer significa entregado. En alemán, francés e italiano, Planzer rotula los paquetes entregados como `Ausgeliefert`, `Livré` y `Consegnato`.

Si el buscador de Quickpac muestra «Unfortunately, parcel tracking is not available at the moment.», ha fallado el propio cuadro de búsqueda: inténtalo más tarde.

## Entrega de Quickpac, ausencias y puntos de recogida

Quickpac solo reparte en Suiza, en áreas postales seleccionadas. La rapidez depende del remitente: SameDay y Priority+ llegan el mismo día, Priority el siguiente día laborable y Economy como muy tarde al segundo día laborable. La entrega nocturna hasta las 21:00 (hora suiza) y la entrega en sábado son opciones que contrata el remitente, y las condiciones de Quickpac señalan que los plazos son orientativos y no garantizados.

**En tu puerta.** Los paquetes que caben y pueden depositarse van al compartimento de paquetes del buzón sin llamar al timbre. Los envíos con firma se entregan en mano; si los rechazas, vuelven al remitente. Quickpac no puede entregar en PickPost, MyPost24, apartados postales ni lista de correos: te escribe por correo o SMS y el seguimiento pide una dirección privada.

**Cambiar la entrega.** Tras verificarte con tu apellido y código postal, el seguimiento de Quickpac permite dejar una autorización gratuita de depósito en un lugar protegido de tu propiedad hasta unos 15 minutos antes de la entrega (bajo tu responsabilidad) y cambiar la dirección hasta dos veces cuando esté habilitado. También acepta correo o móvil para avisos gratuitos. Sin internet, puedes registrar una autorización de depósito llamando al 0800 363 363.

**Ausencia.** Existen dos juegos de reglas publicados y ninguna página aclara cuál rige en cada envío, así que sigue la tarjeta o mensaje que recibas:

- **Tarjeta «Abholinformationen» de Quickpac:** indica un punto de recogida cercano disponible desde el siguiente día laborable. Quickpac indica una custodia de 14 días antes de devolver el envío al remitente, mostrando el código de recogida de la tarjeta o del seguimiento. Algunos paquetes van a Coop Pick-up, que avisa por SMS o correo y devuelve los pedidos tras 7 días.
- **Tarjeta de entrega de Planzer Paket:** trae un código para su portal de destinatarios donde puedes fijar otro día, indicar un lugar seguro o ver en qué Planzer Parcel shop está el paquete. El punto suele guardarlo 7 días: lleva el código de recogida y tu documento de identidad. Algunos son taquillas abiertas 24 horas. El remitente puede restringir estas opciones.

Los paquetes transferidos a Swiss Post tienen otro intento de entrega y después esperan en la oficina postal.

## Paquete Quickpac retrasado, perdido o dañado

**Número no encontrado.** Si aparece «Consignment … not found!» y el código es correcto, Quickpac aún no ha recibido los datos del remitente: vuelve a probar más tarde o pregunta al remitente.

**Días sin cambios.** Consulta al remitente o usa el formulario de contacto de Planzer Paket. Ver [seguimiento que no se actualiza](guide:tracking-not-updating).

**Entregado pero no recibido.** Quickpac pide comprobar que nadie lo haya recogido y revisar el compartimento de paquetes, la escalera y el portal. Planzer Paket pide verificar que conste `Parcel delivered`, revisar «Delivery confirmation» en su portal (a veces con foto) y usar después su formulario. Ver [entregado pero no recibido](guide:delivered-not-received).

**Dañado o perdido.** Planzer Paket remite al remitente, que necesitará fotos del artículo, del embalaje y de la etiqueta.

**Plazo del remitente.** El contrato de Quickpac es con el remitente. Sus condiciones limitan su responsabilidad a 500 CHF por paquete salvo pacto distinto, y el remitente debe reclamar por escrito en los 30 días siguientes a la entrega del envío a Quickpac. Los paquetes depositados con autorización no están cubiertos. Avisa pronto a la tienda.

**Devoluciones.** Quickpac no ofrece devoluciones actualmente: pregunta al remitente.

## Atención al cliente y contacto de Quickpac

Quickpac deriva todas las consultas sobre paquetes a Planzer Paket:

- **Atención al cliente Planzer Paket:** 0848 00 19 36, de lunes a viernes de 8:00 a 18:00 (hora suiza), 0,08 CHF por minuto desde líneas fijas suizas. Ten tu número de seguimiento a mano.
- **Formulario de contacto:** en la [página de contacto de Planzer Paket](https://www.planzer-paket.ch/en/contact/) para paquetes desaparecidos, nueva entrega, cambio de fecha o dirección y quejas. Para daños o pérdida, contacta primero con el remitente.
- **Líneas en la portada alemana de Quickpac:** teléfonos de Planzer Paket por idioma — 044 438 50 40 (alemán e inglés), 021 821 02 02 (francés) y 091 611 54 84 (italiano), sin horario publicado.
- **Línea gratuita de Quickpac:** 0800 363 363, aún presente en sus páginas con horarios dispares.
- **Correo electrónico:** info@quickpac.ch para reclamaciones de entrega.

No se puede llamar directamente al repartidor, y ninguna página confirma si el 0848 funciona desde el extranjero.

## Seguir un paquete Quickpac con Peek

[Peek](/) reconoce al instante un código de 18 dígitos que empieza por `44` como Quickpac y lo consulta en el portal de Planzer; también funciona un enlace de quickpac.ch o tracking.app.planzer.ch. Muestra el estado, el historial, la entrega estimada, el peso, las dimensiones, la hora de entrega y el país de destino, y omite la dirección del destinatario. `Shipped` se traduce correctamente como entregado.

Mientras tengas el paquete abierto en Peek, o el día de su entrega con las notificaciones activadas, Peek comprueba hasta cada 10 minutos — y cada 2 minutos a partir de las 6:00 (hora suiza) el día en que sale a reparto, mientras responda el seguimiento de Planzer. Tras dos días sin novedades (12 horas mientras siga en «Etiqueta creada» o «Admitido por el transportista»), consulta cada hora, y cada 6 horas tras siete días en «Etiqueta creada». Si un paquete en «Etiqueta creada», «Admitido por el transportista» o «En tránsito» pasa cuatro días sin nuevos escaneos, Peek muestra «Sin novedades desde hace cuatro días» (o «Sin novedades desde el…» con la fecha), «Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a Quickpac.» y el enlace «Pide ayuda a Quickpac». Peek puede avisarte con «Cada escaneo», «Pasos importantes» o «Solo la entrega». No puede contactar con Quickpac por ti.

## Preguntas sobre el seguimiento de Quickpac

### ¿Es lo mismo el ID de seguimiento Quickpac que el número de paquete?

Sí. Rastras un envío Quickpac con su número de paquete de 18 dígitos, que Quickpac llama también número de envío y el portal de Planzer «Shipment Number».

### ¿Tiene Quickpac seguimiento en vivo?

A veces. El buscador de Quickpac puede ofrecer «Follow the delivery of your consignment live», pero no especifica en qué envíos aparece.

### ¿Entrega Quickpac en el mismo día?

Solo cuando el remitente contrata SameDay o Priority+.

### ¿Forma Quickpac parte de Planzer?

Sí. Quickpac indica que forma parte de la empresa familiar suiza Planzer Paket desde 2024.

### ¿Entrega Quickpac fuera de Suiza?

No. Las FAQ de Quickpac señalan que actualmente solo reparte en Suiza.

:::sources
- [Quickpac: FAQ](https://quickpac.ch/en/faq) – número, entrega, puntos de recogida, incidencias, modalidades
- [Quickpac: Tracking](https://quickpac.ch/en/tracking) – etiquetas y opciones del buscador
- [Quickpac: Kontakt Empfangen](https://quickpac.ch/de/empfangen/kontakt-empfangen) – derivación de consultas a Planzer Paket
- [Quickpac: Home](https://quickpac.ch/de) – parte de Planzer Paket desde 2024, líneas telefónicas
- [Quickpac: Postal Services Ordinance](https://quickpac.ch/en/postal-services-ordinance) – quejas, línea gratuita
- [Quickpac: AGB für Paketbeförderung](https://quickmail-ag.ch/assets/images/AGB-Quickpac-DE-ab-19.03.2024.pdf) – plazos indicativos, 500 CHF, 30 días
- [Planzer Paket: Contact](https://www.planzer-paket.ch/en/contact/) – tarjeta, portal, incidencias, teléfono
- [Planzer Paket: Kontakt](https://www.planzer-paket.ch/de/kontakt/) – fotos, formulario, taquillas
- [Planzer Paket: Paket Shop](https://www.planzer-paket.ch/de/paket-shop/) – 7 días, código, documento de identidad
- [Planzer: Tracking](https://tracking.app.planzer.ch/) – idiomas, formatos, etiquetas
- [Coop Pick-up: Home](https://www.pickup.ch/de.html) – avisos, 7 días
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – datos leídos y mostrados por Peek
:::
