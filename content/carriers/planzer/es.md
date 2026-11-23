---
title: Seguimiento Planzer: qué significa cada estado y contacto
description: Sigue un paquete Planzer en Suiza y Liechtenstein: dónde está el número, por qué Shipped significa entregado, puntos Parcel shop, ausencias y contacto.
slug: seguimiento-planzer
published: 2026-11-23
updated: 2026-11-23
---

Planzer Paket (Planzer Parcel en sus páginas en inglés) es el servicio de paquetería del grupo suizo Planzer, que entrega paquetes de hasta 30 kg en Suiza y Liechtenstein. Tu número de seguimiento aparece en el correo o SMS titulado «A delivery is on its way to you», enviado por la tienda o por Planzer. Su enlace abre el portal de destinatarios de Planzer, o puedes pegar el número en el buscador de arriba.

## Dónde encontrar tu número de seguimiento de Planzer

En alemán ese mensaje lleva por título «Eine Sendung ist unterwegs für Sie», en francés «Un colis est en chemin pour vous» y en italiano «A breve riceverà una spedizione». ¿No llegó nada? Revisa la carpeta de correo no deseado y pregunta al remitente: algunos no envían notificación o la mandan sin número. Ver [cómo encontrar tu número de seguimiento](guide:find-tracking-number).

Otros dos códigos no sirven para el seguimiento: el «Reference number» del portal de Planzer es la referencia interna del remitente, y el código de entrega de la tarjeta de aviso abre el portal. Introducir solo el número muestra el estado; para cambiar la entrega usa el enlace del mensaje.

Planzer no publica un formato oficial. Peek reconoce estas formas (dígitos inventados):

| Número | A quién pertenece |
| --- | --- |
| `9134 6097 4829 1736 4058` | Planzer: 20 dígitos empezando por `91346097`. |
| `999.90.48291736` | Envío compartido de Planzer: 13 dígitos empezando por `99990`. |
| `448291736405829173` | Quickpac, parte de Planzer Paket: 18 dígitos empezando por `44`. |
| `99.60.482917.36405829` | [Swiss Post](carrier:swiss-post): 18 dígitos, más largo que la forma con puntos de Planzer. |

Copia el número en lugar de teclearlo: con un dígito de más, Peek pregunta a otro transportista como SEUR en lugar de Planzer ([por qué se solapan las longitudes](guide:tracking-number-formats)).

## Estados de seguimiento de Planzer explicados

El portal de destinatarios de Planzer (tracking.app.planzer.ch) sigue el idioma de tu navegador entre inglés, alemán, francés e italiano, y recurre al inglés por defecto. Sus estados en inglés:

| Estado | Qué significa para ti |
| --- | --- |
| `Recorded` | [Etiqueta creada](guide:tracking-statuses): Planzer tiene los datos del envío. |
| `Delivery day unknown`, `Expected` | Sin fecha fijada aún, o el día previsto de visita. |
| `Transferred`, `Shipment on the way` | Viajando por la red de Planzer. |
| `In delivery`, `Shipment out for delivery` | En reparto con el conductor. |
| `Not delivered – A delivery card has been deposited`, `Not delivered` | El conductor no te encontró: busca una tarjeta en el buzón. |
| `New delivery released` | Se programó una nueva entrega tras la ausencia. |
| `Collection at`, `Collected at` | Listo para recoger en el Planzer Parcel shop indicado, o ya recogido allí. |
| `Shipped` | Entregado, no «enviado por la tienda». |
| `Shipment delivered`, `Delivered`, `Parcel delivered` | Entregado. |

`Shipped` suele despistar: en el seguimiento en inglés de Planzer significa entregado. Si aparece y no has recibido nada, sigue los pasos de paquete perdido abajo. En alemán, francés e italiano, los envíos entregados figuran como `Ausgeliefert`, `Livré` o `Consegnato`.

En el catálogo de Peek, los códigos que empiezan por `99990` son envíos compartidos consultados en trackandtrace.planzergroup.com, que abre en alemán y muestra cinco pasos: `Erfasst` (etiqueta creada), `Abholung` (recogido al remitente, no por ti), `Umschlaglager` (en almacén de transbordo), `In Auslieferung` (en reparto) y `Ausgeliefert` (entregado).

## Entrega de Planzer, Parcel shops y ausencias

**Cuándo llega.** Normalmente el siguiente día laborable, de lunes a viernes, aunque las condiciones de Planzer no garantizan plazo. El día previsto aparece en la notificación y en el portal.

**Dónde se deja.** Por defecto el conductor deja el paquete sin firma y toma una foto con geolocalización GPS como prueba. Si el remitente lo permite, puedes elegir un lugar seguro (buzón, interior del portal o zona cubierta junto a tu puerta) o cambiar de día en «Manage the delivery» desde el enlace del mensaje; si aparece en gris, el remitente lo restringió. Planzer no entrega en apartados postales, My Post 24 ni PickPost.

**Ausencia.** Planzer Paket hace un intento, deja una tarjeta en tu buzón y envía un correo o SMS. El código de entrega de la tarjeta permite programar otro reparto o ver qué Planzer Parcel shop guarda el paquete: si el conductor no puede entregarlo ni dejarlo, pasa automáticamente a un punto cercano. Las condiciones solo permiten un segundo intento si tú o el remitente lo solicitáis en 7 días naturales; si no, el paquete puede volver al remitente tras al menos 7 días laborables.

**Parcel shops.** Gratuitos para ti, son comercios asociados o taquillas automáticas, algunas abiertas las 24 horas. Encuentra uno por código postal en la [página de Parcel shops de Planzer Paket](https://www.planzer-paket.ch/en/parcel-shop/), o desvía tu paquete antes del primer intento desde «Shipment information» en el portal. Un punto suele guardarlo 7 días: lleva el código de recogida del portal y tu documento de identidad. Otra persona cuyo nombre completo indiques allí puede recogerlo con su propio documento.

## Paquete Planzer retrasado, perdido o dañado

El remitente es el titular del contrato con Planzer Paket, por lo que pérdidas, daños y devoluciones pasan por él.

**Días sin cambios.** Comprueba el día previsto en el portal y luego contacta con el remitente o usa el formulario de Planzer Paket (atiende envíos faltantes y quejas por retrasos o franjas de pago incumplidas). Ver [seguimiento que no se actualiza](guide:tracking-not-updating).

**Entregado pero no recibido.** Comprueba que el estado diga `Parcel delivered` y revisa «Delivery confirmation» en el portal para ver la prueba y la foto, si el remitente las habilita. ¿Sigue sin aparecer? Usa el formulario de contacto y consulta [entregado pero no recibido](guide:delivered-not-received). Si autorizaste dejar el paquete sin firma, el formulario advierte que el riesgo de pérdida pasa a ser tuyo.

**Dañado o perdido.** Planzer te remite al remitente, que pedirá fotos del producto, del paquete y de la etiqueta. Actúa pronto: el contrato del remitente exige comunicar daños ocultos por escrito en un plazo de 8 días laborables tras la entrega, y limita la responsabilidad de Planzer a 500 CHF por siniestro.

**Devoluciones.** Pregunta al remitente. Cuando el portal muestra «Return a shipment», imprime la etiqueta y deposita el paquete gratis en un Planzer Parcel shop.

## Atención al cliente y teléfono de Planzer

Canales publicados en las páginas de Planzer Paket y Quickpac:

- **Centro de Atención al Cliente:** 0848 00 19 36, de lunes a viernes de 8:00 a 18:00 (hora suiza), 0,08 CHF por minuto desde fijos suizos. Ten a mano el número de seguimiento.
- **Formulario de contacto:** en la [página de contacto de Planzer Paket](https://www.planzer-paket.ch/en/contact/) para paquetes desaparecidos, nueva entrega, cambio de fecha o dirección, daños y quejas, con opción de devolución de llamada.
- **Otras líneas:** la portada de Quickpac lista 044 438 50 40, 021 821 02 02 y 091 611 54 84 por idioma para Planzer Paket, y el cuadro de seguimiento en planzer-paket.ch muestra +41 44 438 50 40 en sus avisos de error. Ninguna página aclara cómo se relacionan con el 0848.
- **Desde el extranjero:** ninguna página confirma si el 0848 funciona fuera de Suiza ni da su formato con +41, así que usa el formulario de contacto.
- **Correo electrónico:** ninguno para destinatarios; el aviso legal incluye info@planzer-paket.ch.
- **Dirección:** Planzer Paket, Lerzenstrasse 14, 8953 Dietikon.

> Planzer advierte contra correos y SMS falsos que piden confirmar un paquete mediante un enlace. Reenvía cualquier mensaje sospechoso como archivo adjunto a security@planzer.ch para que Planzer confirme si es auténtico.

## Seguir un paquete Planzer con Peek

[Peek](/) reconoce al instante un código de 20 dígitos que empiece por `91346097` o un enlace de tracking.app.planzer.ch con `deliveryNumber=`, mostrando estado, historial, entrega prevista, hora de entrega, país de destino, peso total (cuando consta en todos los bultos) y dimensiones en envíos de un solo bulto, omitiendo la dirección del destinatario. `Shipped` aparece correctamente como entregado.

Para un código de 13 dígitos que empiece por `99990`, Peek pide el enlace compartido completo de trackandtrace.planzergroup.com (incluido su parámetro `accessKey`) y lee los pasos registrados con sus horas y la fecha prevista.

Mientras tengas el paquete abierto en Peek, o el día de su entrega con las notificaciones activadas, Peek comprueba hasta cada 10 minutos — y cada 2 minutos a partir de las 6:00 (hora suiza) el día en que Planzer lo escanea en reparto, mientras responda el seguimiento propio de Planzer (hasta cada 15 minutos mediante servicios de seguimiento en las mismas condiciones). Tras dos días sin novedades (12 horas mientras siga en «Etiqueta creada» o «Admitido por el transportista»), consulta cada hora, y cada 6 horas tras siete días en «Etiqueta creada». Si un paquete en «Etiqueta creada», «Admitido por el transportista» o «En tránsito» pasa cuatro días sin nuevos escaneos, Peek muestra «Sin novedades desde hace cuatro días» (o «Sin novedades desde el…» con la fecha), «Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a Planzer.» y el enlace «Pide ayuda a Planzer». Peek puede avisarte con «Cada escaneo», «Pasos importantes» o «Solo la entrega». Como Planzer no incluye localidades en los escaneos, apenas hay puntos que fijar en el mapa, y Peek no puede contactar con Planzer por ti.

## Preguntas sobre el seguimiento de Planzer

### ¿Qué es Planzer Paket?

Es la división de paquetería de Planzer Transport AG en Dietikon, parte del grupo suizo Planzer. Sus envíos viajan de noche en tren de mercancías hasta su región, y Quickpac forma parte de ella desde 2024.

### ¿A qué hora entrega Planzer?

Planzer no publica horario fijo de reparto. Indica a los remitentes que los envíos sin franja contratada llegan como muy tarde al final del siguiente día laborable, aunque sus condiciones no lo garantizan. Los remitentes pueden contratar entrega antes de las 8:00, 10:00 o 12:00 (hora suiza) según código postal.

### ¿Entrega Planzer los sábados?

No por norma general: las condiciones de Planzer contemplan entregas de lunes a viernes. La entrega en sábado solo existe como opción adicional que contrata el remitente; consulta a la tienda.

:::sources
- [Planzer Paket: Contact](https://www.planzer-paket.ch/en/contact/) – número, tarjeta, portal, incidencias, teléfono
- [Planzer Paket: Parcel shop](https://www.planzer-paket.ch/en/parcel-shop/) – tiendas, siete días, devoluciones
- [Planzer Paket: Terms & Conditions](https://cms.planzer.ch/doc/agb-pp/?lang=en) – cobertura, intentos, prueba, responsabilidad
- [Planzer: Parcels](https://www.planzer.ch/en/transport-en/parcels/) – 30 kg, transporte ferroviario, siguiente día laborable, franjas
- [Planzer: Security tips](https://www.planzer.ch/en/phishing-2/) – mensajes falsos y security@planzer.ch
- [Planzer Paket: Receiving](https://www.planzer-paket.ch/en/receiving/) – cuadro de seguimiento, +41 44 438 50 40
- [Planzer: Tracking](https://tracking.app.planzer.ch/) – idiomas, números, etiquetas
- [Planzer Paket: Kontakt](https://www.planzer-paket.ch/de/kontakt/) – motivos del formulario, riesgo de depósito, sábado
- [Planzer Paket: Impressum](https://www.planzer-paket.ch/de/impressum/) – razón social, dirección, correo legal
- [Quickpac: Home](https://quickpac.ch/de) – parte de Planzer Paket desde 2024, líneas telefónicas
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formatos, enlace compartido y datos leídos por Peek
:::
