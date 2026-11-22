---
title: Seguimiento Quantium Solutions: números, estados y ayuda
description: Rastrea un paquete de Quantium Solutions: formatos de número admitidos, qué significa cada estado, servicios de envío, aduanas y contacto con soporte.
slug: seguimiento-quantium-solutions
published: 2026-11-22
updated: 2026-11-22
---

Quantium Solutions es una empresa de logística para comercio electrónico presente en 10 países de Asia-Pacífico. Transporta pedidos online hacia la región, dentro de ella y desde allí al resto del mundo. Puedes seguir un paquete en la página [Track Your Shipments](https://track.quantiumworldwide.com/) de Quantium o pegando el número en la casilla de arriba. La tienda te entrega ese número en el correo de envío o en la página de tu pedido.

## Número de seguimiento Quantium Solutions: qué aspecto tiene

El rastreador de Quantium pide un «Tracking/Reference number» y admite varios a la vez, uno por línea. El código de Peek conoce dos formatos de número de Quantium, y ninguno es exclusivo de Quantium. Con dígitos inventados:

| Número (inventado) | Qué es |
| --- | --- |
| `4829173654` | Diez dígitos: en el código de Peek, el número de Quantium para envíos en Malasia. Diez dígitos coinciden con varios transportistas, incluido DHL, por lo que Peek no puede asignar Quantium solo por el formato. |
| `UQ482917367TH` | `UQ`, nueve dígitos y `TH`: en el código de Peek, el número de Quantium para envíos en Tailandia. Tiene el formato postal internacional, así que Peek muestra Thailand Post. |
| `RA482917367SG` | Dos letras, nueve dígitos y `SG`: un número de Singapore Post. El código de Peek señala que Quantium mueve algunos paquetes bajo números de SingPost. Peek muestra Singapore Post. |

La consulta de Quantium en Peek solo acepta estos dos formatos (diez dígitos o formato postal S10). Los espacios y las minúsculas no importan. Para una referencia de otro formato, usa el rastreador de Quantium. ¿No encuentras tu número? Consulta [cómo encontrar un número de seguimiento](guide:find-tracking-number) y [formatos de números de seguimiento](guide:tracking-number-formats).

## Estados de seguimiento de Quantium Solutions explicados

Cada línea del rastreador de Quantium tiene un hito y, a menudo, una observación con el paso tal como lo llama el socio que maneja el paquete. Peek escribe el hito y después la observación tras dos puntos cuando aporta información adicional. Estas son las líneas que Peek conoce, en orden habitual:

| Quantium dice | Qué significa para ti | Peek muestra |
| --- | --- | --- |
| `Shipment created` | La tienda registró el envío. Quantium aún no tiene el paquete. | «Etiqueta creada» |
| `Shipment received` | Quantium tiene el paquete. | «Admitido por el transportista» |
| `Shipment under processing at origin hub` | En clasificación en un centro del país de origen. | «En tránsito» |
| `Shipment despatched to transshipment hub` | De camino a un centro de transbordo para pasar a su siguiente tramo. | «En tránsito» |
| `DEPART ORIGIN` | Salió de su punto de origen. | «En tránsito» |
| `Shipment received at transshipment hub: Arrive Hub` | En el centro de transbordo. | «En tránsito» |
| `Shipment despatched to overseas: Depart Hub` | Salió del centro rumbo a tu país. | «En tránsito» |
| `READY FOR DEPARTURE`, `FLIGHT DEPARTURE TO DESTINATION` | Esperando vuelo y volando hacia tu país. | «En tránsito» |
| `Shipment arrived at destination port: Arrive Destination` | Llegó a tu país. | «En tránsito» |
| `IN TRANSIT: Customs Clearance Begins` | La aduana de tu país inició el despacho. | «En aduanas» |
| `Shipment released from customs/ government check point: Customs Cleared` | La aduana lo liberó. | «En tránsito» |
| `Shipment received at delivery branch: Handed Over for Delivery` | Lo tiene la delegación local de reparto. | «En tránsito» |
| `IN TRANSIT` | En movimiento. Así llegan los escaneos de clasificación del socio en tu país. | «En tránsito» |
| `Shipment out for delivery` | En reparto. | «En reparto» |
| `Shipment delivered` | Entregado. Cuando la observación indica quién firmó o recibió el paquete, Peek también lee ese nombre. | «Entregado» |

Una línea que Peek no conozca se muestra tal como la escribe Quantium, y Peek deduce una etapa por las palabras cuando puede. Ante un número desconocido, el rastreador de Quantium muestra «No record!». Más en [qué significan los estados de seguimiento](guide:tracking-statuses).

**Horas.** El rastreador de Quantium da fecha y hora sin zona horaria, según el reloj de quien escaneó el paquete: el país del remitente al principio y luego el socio de tu país. Dos líneas de países distintos no pueden compararse hora por hora.

## Quién entrega los paquetes de Quantium Solutions

Quantium vende a las tiendas cuatro servicios transfronterizos:

| Servicio | Seguimiento | Canal de entrega | Plazo de tránsito | Aranceles e impuestos |
| --- | --- | --- | --- | --- |
| Q-Simple | ninguno | postal | 4 a 17 días | del destinatario (DDU) |
| Q-Smart | con seguimiento | postal | 4 a 10 días | del destinatario (DDU) |
| Q-Speed | con seguimiento, prioritario | postal | 3 a 7 días | del destinatario (DDU) |
| Q-Sure | con seguimiento | comercial | 2 a 5 días | del remitente o del destinatario (DDP o DDU) |

Peek no indica qué servicio usa tu paquete: pregúntalo a la tienda.

**En Asia.** Los propios servicios de última milla de Quantium ofrecen entrega a domicilio, recogida en oficinas postales, taquillas y más de 3.000 puntos de recogida en Asia, con pago en efectivo o electrónico a la entrega. Su servicio Regular tarda de 2 a 4 días.

**En Australia.** Su filial australiana, Quantium Solutions (Australia) Pty Ltd, reparte dentro de Australia por mensajería — entre ellos CouriersPlease — y con [Australia Post](carrier:australia-post) eParcel y express eParcel, según su página de recargo de combustible. Quantium también menciona a CouriersPlease entre sus socios oficiales. El transportista final decide los intentos de entrega y dónde espera un paquete no entregado.

Si no había nadie, consulta [entrega fallida](guide:missed-delivery) y [paquetes no recogidos](guide:uncollected-parcel).

## Aduanas y aranceles en un paquete de Quantium

En Q-Simple, Q-Smart y Q-Speed, Quantium asigna aranceles e impuestos al destinatario: entrega con derechos no pagados (DDU). Q-Sure puede ir DDP (paga el remitente) o DDU. Pregunta a la tienda cuál corresponde a tu pedido. Lo que tengas que abonar depende de las normas de tu país: consulta [paquetes retenidos en aduanas](guide:customs).

**Hacia la UE.** Los bienes comprados online desde fuera de la UE pagan un arancel temporal de 3 € por artículo en paquetes de hasta 150 €, desde el 1 de julio de 2026, y una tasa de gestión de la Unión de 2 € por artículo cualquiera que sea el valor, desde el 1 de noviembre de 2026. Un artículo agrupa los productos de un paquete con igual clasificación arancelaria, descripción y, si se exige, origen. Quantium indica a las empresas remitentes que la tasa de 2 € se aplica por cada línea de declaración aduanera, tanto en IOSS como en DAP y sea cual sea el valor del envío, más el IVA cuando lo exija el país de destino. Pregunta a la tienda si tu precio la incluía.

## Seguimiento de Quantium Solutions detenido, paquete perdido o devuelto

**Solo `Shipment created`.** La tienda registró el envío pero Quantium aún no ha escaneado el paquete. Si permanece así varios días, pregunta a la tienda si realmente lo envió.

**Sin cambios en tránsito.** Tras cuatro días sin una nueva línea en un envío en marcha, Peek muestra la fecha del último escaneo («Sin novedades desde el …») y avisa «Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a Quantium Solutions.», junto al enlace «Pide ayuda a Quantium Solutions» hacia su formulario de soporte. Quantium publica avisos de incidencias en su página de noticias (como el de densa calima en Malasia y Singapur del 7 de octubre de 2026). Su rastreador puede mostrar además el número del socio en «Tracking No.»: síguelo también allí. Ver [por qué deja de actualizarse el seguimiento](guide:tracking-not-updating).

**Entregado, pero no está.** Comprueba los alrededores de tu casa y pregunta a los vecinos; luego contacta con la tienda y con Quantium. Consulta [entregado pero no recibido](guide:delivered-not-received).

**Perdido o dañado.** La web de Quantium no incluye proceso de reclamación para destinatarios. La tienda es cliente de Quantium, así que pídele un reembolso o reenvío. Ver [paquetes perdidos](guide:lost-parcel) y [paquetes dañados](guide:damaged-parcel).

**Devuelto.** Quantium ofrece a las tiendas devolución al origen. Ver [devuelto al remitente](guide:returned-to-sender).

## Atención al cliente y contacto de Quantium Solutions

Canales publicados por Quantium:

- **En línea:** el [Formulario de soporte al cliente](https://www.quantiumsolutions.com/contact-support/), para seguimiento, ayuda general y comentarios. Pide nombre, correo electrónico, país de origen y número de seguimiento; el campo «Customer ID» indica «Corporate Customer only». Quantium dice que su agente local responde el siguiente día laborable.
- **Malasia:** (603) 5569 6901, customerservice.my@quantiumworldwide.com.
- **Tailandia:** (662) 712 2999, customerservice.th@quantiumworldwide.com.
- **Otros países:** la página [Contact Us](https://www.quantiumsolutions.com/contact-us/) incluye buzones de soporte para Hong Kong, Indonesia, Taiwán y Vietnam. Para Singapur solo ofrece un correo comercial y no lista contacto para Australia. No se publican horarios de atención.

En un pedido online, pregunta primero a la tienda: contrató el envío y es cliente de Quantium.

## Seguir un paquete de Quantium Solutions con Peek

[Peek](/) no puede identificar un número de Quantium solo por su forma. Pégalo igualmente: Peek sigue a más de 4500 transportistas. Con diez dígitos, Peek pregunta a los transportistas con formatos similares y luego a los servicios de seguimiento («Comprobando servicios de seguimiento…»). Con un número terminado en `SG` o `TH`, nombra a Singapore Post o Thailand Post («Transportista detectado»). Para seguir las líneas propias de Quantium, pulsa «Cambiar» y elige Quantium Solutions; en un paquete ya añadido, usa «Cambiar transportista». Si eliges Quantium para otro formato, Peek indica «no emite números como este».

Un enlace al rastreador de Quantium con `trackno=` también sirve: Peek toma el número y nombra a Quantium Solutions al instante.

Peek muestra el estado de Quantium, cada línea en las palabras de Quantium (tabla anterior), el país de destino, así como el nombre del destinatario (bajo «Destinatario»), la dirección (sin la línea de teléfono) y el firmante cuando una observación de entrega lo indica. Omite la referencia del cliente, el número del socio, los datos del remitente y el origen. Quantium no aporta ubicaciones por línea, fecha estimada de entrega, peso ni comprobante de entrega, por lo que Peek no los muestra. «Abrir la web de Quantium Solutions» abre el número en el rastreador de Quantium.

Como las horas de Quantium no llevan zona horaria, Peek las lee en un único reloj: el del país de destino para envíos a Singapur, Malasia, Tailandia o Australia (hora de Sídney para Australia) y, en los demás casos, hora de Singapur. Muestra cada hora en la zona horaria de tu dispositivo («Horas en tu zona horaria:»), de modo que una línea escaneada en otro país puede variar unas horas.

Cuando el número tiene formato postal con dígito de control válido y Quantium indica el país de destino, Peek pregunta también al correo de ese país con el mismo número, como [Australia Post](carrier:australia-post), [USPS](carrier:usps), [Royal Mail](carrier:royal-mail), An Post, [Canada Post](carrier:canada-post) o NZ Post. Sigue el paquete allí en cuanto ese correo muestra escaneos nuevos y conserva el historial de Quantium. No ocurre en todos los países (no para Singapur, Malasia, Tailandia, Hong Kong o Vietnam, por ejemplo) y nunca con números de diez dígitos.

Mientras tengas el paquete abierto en Peek — o el día de la entrega con las notificaciones activadas — Peek consulta directamente a Quantium hasta cada 10 minutos, y hasta cada 2 minutos a partir de las 6:00 (hora de Singapur) el día en que Quantium lo escanea en reparto, mientras el rastreador de Quantium responda; fuera de ese caso, y tras dos días sin novedades (12 horas mientras siga en «Etiqueta creada» o «Admitido por el transportista»), comprueba cada hora. Puede avisarte con «Cada escaneo», «Pasos importantes» o «Solo la entrega». No puede contactar con Quantium ni con la tienda por ti.

## Preguntas sobre el seguimiento de Quantium Solutions

### ¿Quantium Solutions es lo mismo que Quantium Worldwide?

Sí: su rastreador y sus correos de soporte están en `quantiumworldwide.com`, y Peek también la conoce como Quantium o Quantium Solutions International.

### ¿Por qué Peek dice Singapore Post o Thailand Post para mi número de Quantium?

Porque tu número sigue el formato postal internacional, que señala un correo nacional: `SG` para Singapore Post, `TH` para Thailand Post. Para ver las líneas de Quantium, pulsa «Cambiar» y elige Quantium Solutions.

### ¿Qué significa «Customs Clearance Begins»?

La aduana de tu país ha comenzado el despacho del paquete y Peek muestra «En aduanas». Cuando aduanas lo libera, aparece `Shipment released from customs/ government check point: Customs Cleared`. En un servicio DDU, los derechos e impuestos corren de tu cuenta: consulta [paquetes retenidos en aduanas](guide:customs).

### ¿Cuánto tarda Quantium Solutions en entregar?

Quantium indica de 2 a 5 días para Q-Sure y hasta 17 para Q-Simple (tabla superior). Dentro de Asia, su entrega Regular tarda de 2 a 4 días.

### ¿Puedo rastrear un paquete Q-Simple?

No durante el recorrido. Quantium vende Q-Simple como su servicio económico sin seguimiento, así que no cuentes con escaneos y pregunta a la tienda cuándo lo envió.

:::sources
- [Quantium Solutions: Track Your Shipments](https://track.quantiumworldwide.com/) – rastreador, columna «Tracking No.», «No record!»
- [Quantium Solutions: Home](https://www.quantiumsolutions.com/) – presencia en 10 países de Asia-Pacífico
- [Quantium Solutions: About Us](https://www.quantiumsolutions.com/about-us/) – actividad de Quantium, CouriersPlease entre sus socios
- [Quantium Solutions: Cross Border Shipping](https://www.quantiumsolutions.com/cross-border-shipping/) – los cuatro servicios, devolución a origen
- [Quantium Solutions: Last Mile Delivery](https://www.quantiumsolutions.com/last-mile-delivery/) – opciones de entrega en Asia
- [Quantium Solutions: Australia Fuel Surcharges](https://www.quantiumsolutions.com/australia-fuel-surcharges/) – CouriersPlease, Australia Post eParcel
- [Quantium Solutions: Service Impact Notice (haze)](https://www.quantiumsolutions.com/news/13/) – retrasos por calima
- [Quantium Solutions: Service Update (EU customs handling fee)](https://www.quantiumsolutions.com/news/14/) – tasa de 2 € por línea de declaración
- [Quantium Solutions: Customer Support Form](https://www.quantiumsolutions.com/contact-support/) – campos del formulario, plazo de respuesta
- [Quantium Solutions: Contact Us](https://www.quantiumsolutions.com/contact-us/) – teléfonos y buzones de correo
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – arancel de 3 €, tasa de gestión de 2 €
- [European Commission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – definición de artículo
- [Universal Parcel Scraper: Quantium Solutions](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/quantium-solutions) – formatos de número, enlaces, textos, relojes, campos leídos
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – detección, reloj asignado a Quantium, traspaso al correo de destino
:::
