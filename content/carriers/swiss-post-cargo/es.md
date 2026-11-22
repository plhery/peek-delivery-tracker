---
title: Seguimiento Swiss Post Cargo: estados, entrega y contacto
description: Sigue un envío de Swiss Post Cargo: qué número sirve, qué significan Data entry POST y Loaded for Delivery, entrega de carga y contacto.
slug: seguimiento-swiss-post-cargo
published: 2026-11-22
updated: 2026-11-22
---

Swiss Post Cargo es la marca de transporte de carga de Swiss Post: palés y otras mercancías transportadas en camión, desde bultos sueltos hasta camiones completos, no paquetes ordinarios. Puedes seguir un envío en su página Track & Trace (tt.swisspost-cargo.com) con el código de barras o la referencia que te dio el remitente, o pegándolo en el campo de arriba. Si lo que tienes es un número de paquete estándar de Swiss Post, corresponde a la [página de Swiss Post](carrier:swiss-post).

## Número de seguimiento de Swiss Post Cargo

El rastreador de Swiss Post Cargo acepta un código de barras para un bulto individual o una referencia del remitente. Una referencia muestra todos los códigos de barras de esa expedición. Los formatos que Peek asocia a Swiss Post Cargo (dígitos inventados):

| Número | Qué es |
| --- | --- |
| `00482917365428193648` | 20 dígitos empezando por `00`: código de barras de palé o bulto (SSCC). El último dígito es de control. |
| `PL-48291736` | `PL-` y 8 dígitos: referencia de cliente. |

Revisa los correos de la tienda y, si falta, pregunta a la tienda. En [cómo encontrar tu número de seguimiento](guide:find-tracking-number) tienes más sitios donde buscar. Si el rastreador no encuentra nada, comprueba los dígitos y pregunta al remitente.

Dos tipos de códigos que no se siguen como Swiss Post Cargo:

- **Paquetes de Swiss Post:** 18 dígitos que empiezan por `99` (impreso como `99.00.482917.36542819`) o dos letras, nueve dígitos y `CH` (`RR482917367CH`).
- **Referencias de PostLogistics** como `48291736-542`: PostLogistics opera ahora como Swiss Post Cargo y su antiguo rastreador, tracking.postlogistics.ch, ya no abre. Peek sigue esas referencias como PostLogistics.

## Estados de seguimiento de Swiss Post Cargo

Swiss Post Cargo redacta sus escaneos en inglés, alemán, francés o italiano. En inglés, un envío entregado se lee así:

| Swiss Post Cargo indica | Qué significa para ti |
| --- | --- |
| `Data entry POST` | Han llegado los datos del remitente. La mercancía aún no está con Swiss Post Cargo. |
| `Received warehouse POST` | La mercancía ha llegado a un centro o depósito de Swiss Post Cargo, indicado junto al escaneo. |
| `Loaded for Delivery` | Cargado en el camión de reparto: en reparto. |
| `IMAGE` | Foto de entrega, listada justo antes del escaneo de entrega. |
| `DELIVERED SCANNED` | Entregado, en la localidad mostrada. |
| `SIGNATURE` | Firma recogida en la entrega, con la misma hora que el escaneo de entrega. |

`Data entry POST` es solo transmisión de datos: «POST» forma parte del texto y aún no ha habido movimiento. `IMAGE` y `SIGNATURE` son comprobantes ligados a la entrega, no eventos nuevos.

## Entrega de Swiss Post Cargo: cuándo, dónde y entregas fallidas

**Cuándo.** El folleto de carga de Swiss Post indica que la mercancía suele entregarse el siguiente día laborable. En Suiza y Liechtenstein, las localidades accesibles para camiones pesados la reciben antes de las 17:00 (hora suiza) del siguiente día laborable. Los valles alpinos y zonas de montaña de difícil acceso (Valais, Tesino y Grisones) la reciben antes de las 17:00 del segundo día laborable.

**Dónde.** De forma estándar, la carga se entrega en la primera puerta de la planta baja, muelle o acera, en horario comercial, no en pisos superiores. El conductor cuenta con acceso para transpaleta y la descarga corre por cuenta del destinatario: si el conductor descarga, actúa en tu nombre. El conductor pide firma para confirmar la entrega correcta.

**Extras contratados por el remitente.** El aviso previo por teléfono, correo o SMS, la entrega concertada, la entrega en planta y una segunda entrega son servicios adicionales que solo se prestan si el remitente los contrató.

**Entrega fallida y recogida.** El folleto de carga de Swiss Post no menciona puntos de recogida ni taquillas, y la segunda entrega es uno de los extras anteriores. Tampoco detalla cuánto tiempo espera la mercancía en depósito ni cómo reprogramar. Consulta a la tienda que contrató el transporte.

## Envío de Swiss Post Cargo retrasado, perdido o dañado

El cliente de Swiss Post Cargo es la tienda (las empresas contratan sus servicios), así que cualquier retraso, daño o bulto faltante debe tratarse primero con la tienda. Comunica los daños a la tienda de inmediato, con fotografías.

**Días sin actualizaciones.** Ver solo `Data entry POST` significa que la mercancía quizá sigue con el remitente: pregunta a la tienda cuándo la entrega al transportista. [Seguimiento que no se actualiza](guide:tracking-not-updating) explica los motivos habituales.

**Entregado pero no está ahí.** Busca las líneas `SIGNATURE` e `IMAGE` en el rastreador y contacta después con la tienda. [Entregado pero no recibido](guide:delivered-not-received) detalla los pasos siguientes.

**Mensajes sospechosos.** La página de seguridad de Swiss Post advierte que los intentos de fraude en su nombre llegan por SMS, correo electrónico, WhatsApp, sitios web o apps falsas, llamadas y redes sociales, con enlaces, adjuntos o peticiones de pago o datos.

## Contacto de Swiss Post Cargo

Swiss Post Cargo trabaja para la tienda, así que empieza por ahí. El folleto de transporte de carga de Swiss Post indica un contacto para clientes empresariales sin teléfono: Customer Service Transport CH, Lagerstrasse 12, 5606 Dintikon, sales.ls4@post.ch.

Para cartas y paquetes estándar de Swiss Post (no Cargo), el teléfono de Swiss Post es +41 848 888 888 (0,08 CHF por minuto desde fijo suizo), de lunes a viernes de 07:30 a 18:00 y sábados de 08:00 a 12:00 (hora suiza).

## Seguir un envío de Swiss Post Cargo con Peek

Un código de barras de 20 dígitos que empieza por `00` pertenece al remitente, no a un único transportista, así que [Peek](/) muestra «Comprobando servicios de seguimiento…» y señala Swiss Post Cargo solo si Swiss Post Cargo conoce el número (si lo reconocen varios transportistas, Peek te pregunta cuál elegir). Una referencia `PL-48291736` se consulta a Swiss Post Cargo y a TNT Italy, cuyos códigos pueden tener la misma forma. Para cualquier otra referencia de 6 a 40 letras y dígitos, cambia de transportista y selecciona Swiss Post Cargo, o pega un enlace Track & Trace de tt.swisspost-cargo.com o apv.swisspost-cargo.com. Un número de paquete de Swiss Post incluido en un enlace así se sigue como Swiss Post.

Peek muestra el estado y el historial con la localidad de cada escaneo en un mapa, además de la hora de entrega. Las horas se leen en hora de Zúrich y se convierten a tu zona horaria (el historial termina con «Horas en tu zona horaria:» seguido de su nombre). Los textos se mantienen en inglés; `Loaded for Delivery` cuenta como en reparto. Las filas de firma y foto se integran en el escaneo de entrega, sin la imagen. Peek omite tu nombre y dirección y no muestra fecha prevista, peso ni punto de recogida.

Una referencia compartida por varios envíos solo se lee cuando uno de ellos tiene escaneos de los últimos 60 días; en caso contrario consta como no encontrado, así que usa el código de barras. Mientras tengas el envío abierto en pantalla — o el día de entrega con notificaciones activadas — Peek consulta directamente a Swiss Post Cargo hasta cada 10 minutos, o cada 2 minutos desde las 6:00 hora suiza el día en que Swiss Post Cargo lo marca en reparto, mientras su propio rastreador responda. Fuera de esos momentos, y tras dos días sin novedades (12 horas en «Etiqueta creada» o «Admitido por el transportista»), comprueba cada hora. Solo a través de servicios de seguimiento, comprueba cada hora, cada 3 horas tras un día sin escaneos y hasta cada 15 minutos el día de entrega si tienes el envío abierto en pantalla o las notificaciones activadas. Elige «Cada escaneo», «Pasos importantes» o «Solo la entrega». Tras cuatro días sin escaneos en tránsito, Peek muestra en tu lista el día del último escaneo («Sin novedades desde el …») y en la ficha «Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a Swiss Post Cargo.», con el enlace «Pide ayuda a Swiss Post Cargo» a su página de ayuda solo en ese aviso.

## Preguntas sobre el seguimiento de Swiss Post Cargo

### ¿Qué es Swiss Post Cargo?

Es el nombre comercial de la logística de mercancías de Swiss Post desde el 1 de marzo de 2025: soluciones logísticas para empresas, en Suiza y en el extranjero, incluido transporte de carga. Entre las sociedades del grupo en Suiza figura Swiss Post Cargo CH AG, participada al 100 % por Swiss Post. PostLogistics fue una de las primeras sociedades del grupo en adoptar la marca.

### ¿PostLogistics es lo mismo que Swiss Post Cargo?

Sí, PostLogistics opera ahora como Swiss Post Cargo. Su antiguo rastreador, tracking.postlogistics.ch, ya no abre; el de Swiss Post Cargo es tt.swisspost-cargo.com. Peek sigue rastreando referencias de PostLogistics como `48291736-542`.

### ¿Hay un correo electrónico de Swiss Post Cargo?

El folleto de carga de Swiss Post facilita sales.ls4@post.ch para clientes empresariales. Como destinatario, escribe primero a la tienda que contrató el transporte.

### ¿Por qué me envía un SMS Swiss Post Cargo?

A menudo porque el remitente contrató un aviso de entrega: el folleto de carga cita el aviso por teléfono, correo o SMS como servicio extra. También circulan SMS falsos en nombre de Swiss Post, así que comprueba siempre el número tú mismo en el rastreador.

### ¿Puedo seguir Swiss Post Cargo en directo?

No en un mapa en tiempo real. El rastreador lista cada escaneo con su localidad, y el folleto de carga menciona actualizaciones periódicas sobre la ubicación del envío.

:::sources
- [Swiss Post: Transport freight brochure](https://www.post.ch/en/-/media/post/gk/dokumente/stueckgut.pdf) – de bultos sueltos a camiones completos, pedidos de empresas, siguiente día laborable, zonas de montaña, planta baja, descarga, firma, extras del remitente incluida segunda entrega, actualizaciones periódicas, contacto de Dintikon y correo
- [Swiss Post: Logistics market](https://site.post.ch/en/about-us/company/strategy-and-value/business-activities/logistics) – Swiss Post Cargo es la logística de mercancías de Swiss Post para empresas en Suiza y el extranjero
- [Swiss Post: Group companies](https://site.post.ch/en/about-us/company/governance/organisation/subsidiaries) – Swiss Post Cargo CH AG, 100 % Swiss Post
- [Swiss Post: Help and contact](https://www.post.ch/en/help-and-contact) – teléfono de cartas y paquetes, tarifa, horario
- [Swiss Post: Current fraud attempts](https://site.post.ch/en/about-us/security/phishing-and-fraud/current-fraud-attempts) – mensajes falsos en nombre de Swiss Post
- [VerkehrsRundschau: Schweizerische Post mit neuem Marktauftritt](https://www.verkehrsrundschau.de/nachrichten/transport-logistik/logistik-schweizerische-post-fuehrt-neue-marke-ein-3626449) – marca Swiss Post Cargo desde el 1 de marzo de 2025, PostLogistics entre las primeras en adoptarla
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – qué lee Peek: códigos de barras y referencias, formatos, textos ingleses, direcciones Track & Trace, PostLogistics bajo Swiss Post Cargo
:::
