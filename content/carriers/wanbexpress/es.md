---
title: Seguimiento WanbExpress: números WNB…YQ, estados y entrega
description: Sigue un paquete de WanbExpress (Wanb Express) desde China: cómo es el número WNB…YQ, qué significa cada estado, quién reparte y a quién reclamar si se para.
slug: seguimiento-wanbexpress
published: 2026-11-21
updated: 2026-11-21
---

WanbExpress (también Wanb Express, WanBang Express o WANB) es un transportista chino que envía paquetes al extranjero, donde una empresa local realiza la entrega. Si una tienda envió tu pedido con WanbExpress, el número empieza por `WNB` y termina en `YQ`: pégalo en la casilla de arriba. El rastreador propio de WanbExpress, tracking.wanbexpress.com, también puede indicar qué transportista entrega en tu país y con qué número.

## Número de seguimiento WanbExpress: cómo es

Los números de envío de WanbExpress se componen de `WNB`, dos letras, diez cifras y `YQ`, 17 caracteres en total. Ejemplos con cifras inventadas:

| Número (inventado) | Qué es |
| --- | --- |
| `WNBKG4829105736YQ` | `WNB`, dos letras, diez cifras y `YQ`: un número de WanbExpress. |
| `WNBKG482910573YQ` | Solo nueve cifras: no encaja con el formato de WanbExpress. Comprueba si falta una cifra. |
| `JSEKG4829105736YQ` | También acaba en `YQ`, pero empieza por `JSE`: es un número de [JS Express](carrier:js-express). |
| `JXCKG4829105736YQ` | Empieza por `JXC`: es un número de [Juxi Express](carrier:juxi-express). |

Varios operadores chinos terminan sus códigos en `YQ`, cada uno con sus propias letras iniciales: `WNB` identifica a WanbExpress. Al pegarlo en Peek, los espacios, guiones y minúsculas no importan.

**Dónde encontrarlo.** La tienda facilita el número en el correo de envío o en la página del pedido. Si no das con él, consulta [cómo encontrar tu número de seguimiento](guide:find-tracking-number).

**Un enlace de seguimiento.** Un enlace al rastreador de WanbExpress que acaba en `?trackingNumbers=` seguido de un número abre ese paquete allí. El código `WNB` es la parte posterior al signo igual: cópialo desde ahí a Peek.

**Un segundo número.** El rastreador de WanbExpress también puede mostrar el número que utiliza el transportista que reparte el paquete. Ese código pertenece a la otra empresa: consúltalo en su web o añádelo a Peek como un envío aparte.

## Estados de seguimiento de WanbExpress explicados

Los escaneos de WanbExpress aparecen en inglés, cada uno con su hora y a veces un lugar. Estos son los textos recogidos en el código de Peek:

| Qué indica WanbExpress | Qué significa para ti | Peek muestra |
| --- | --- | --- |
| `Shipping Order Created.`, `Parcel Data Received` | La tienda ha dado de alta el envío y enviado los datos. WanbExpress todavía no tiene el paquete. | «Etiqueta creada» |
| `Parcel received at Shenzhen Sorting Centre.` | El centro de clasificación de Shenzhen, en China, ha recibido el paquete: es el primer escaneo físico. | «Admitido por el transportista» |
| `Parcel processed at Shenzhen Sorting Centre.`, `Parcel departure in Shenzhen Sorting Centre.` | Clasificado y enviado fuera de ese centro. | «En tránsito» |
| `A-Scan: shipment in international transit` | Escaneo de admisión reenviado entre el procesamiento en el centro de clasificación y su salida. | «En tránsito» |
| `Customs declaration completed` | Se ha completado una declaración aduanera. El texto no indica de qué país se trata ni equivale al despacho de importación, que aparece como `Custom clearance completed`. | «En tránsito» |
| `Customs inspection has not yet released` | Inspección de exportación en origen, entre la declaración aduanera y el vuelo. | «En tránsito» |
| `Parcel departure in Transit Facility, Parcel Departure` | Ha salido de una instalación de tránsito. | «En tránsito» |
| `Parcel is leaving Airport, Parcel Leaving Port` | Sale en el vuelo hacia el país de destino. | «En tránsito» |
| `Parcel arrives at Airport, Parcel Arrival` | Ha aterrizado en tu país o cerca. Después viene la aduana. | «En tránsito» |
| `Custom clearance completed` | La aduana de importación lo ha liberado. | «En tránsito» |
| `Parcel received by in JFK01.` | WanbExpress lo registra como recepción para el tramo final, sin nombrar quién lo recibió. | «En tránsito» |
| `Your package is being prepared by the sender and will be handed over to us soon`, `Your parcel will soon be handed over to us! It is being prepared by the sender.`, `Order information received. We're expecting your parcel to arrive with us.` | Aviso de un socio reenviado sin código propio de WanbExpress: el paquete aún no se le ha entregado. | «Etiqueta creada», o una etapa posterior si un escaneo anterior ya avanzó el envío |
| `In transit along Poste Italiane’s logistic network` | En camino por la red del transportista que reparte, en este caso [Poste Italiane](carrier:poste-italiane). | «En tránsito» |
| `Parcel is out for delivery.` | En reparto. | «En reparto» |
| `Consignee absent, Consignee could not be found.` | El repartidor del socio no encontró a nadie. | «Intento de entrega» |
| `Placed in Parcelshop/Parcel locker` | Tras el intento fallido, el socio dejó el paquete en un punto de recogida o taquilla para que lo recojas. | «Listo para recoger» |
| `Parcel has been delivered.(Front door)` | Entregado. El texto `(Front door)` indica dónde se dejó. | «Entregado» |

**Los escaneos del transportista que entrega.** Tras el vuelo, el rastreador de WanbExpress retransmite los escaneos del transportista local con sus propias palabras, casi siempre sin código WanbExpress. En un paquete a Alemania, el historial muestra a [iMile](carrier:imile) repartiendo, cada línea precedida por el nombre de su centro o estación entre corchetes `【】`:

| Qué indica iMile | Qué significa para ti | Peek muestra |
| --- | --- | --- |
| `Received.` | El centro de iMile en el país de destino ha recibido el paquete. | «En tránsito» |
| `Arrived.` | Ha llegado a la estación de entrega de tu zona. | «En tránsito» |
| `Dispatch task assigned to DA.` | Se ha asignado un repartidor (DA, delivery associate) a la ruta, a veces horas antes de salir. | «En tránsito» |
| `Shipment out for delivery.` | En reparto. | «En reparto» |
| `<Route not completed>`, `Back To DS.` | La ruta terminó sin entrega y el paquete volvió a la estación de entrega (DS). | «Intento de entrega» (solo tras la ruta; si sigue a un intento fallido, `Back To DS.` queda como aviso, y tras «En tránsito» sigue en «En tránsito») |
| `Scheduled to <2026-11-02> .` | Día previsto para el siguiente intento tras regresar a la estación. | Sigue en «Intento de entrega» |

Los movimientos de otros socios llegan igual. En envíos a Estados Unidos, `Parcel received by Transit Facility.` es la recepción en un centro de tránsito del socio final, `In Processing at Transit Facility.` su clasificación, `In Transit to Next Facility.` el traslado hacia la instalación local y `Parcel received by local Facility.` la entrada antes del reparto. `It is currently in transit to the next facility` y `The package is being transferred to the next facility` reflejan traslados entre centros tras el vuelo, y `Shipment arrived to sorting centre / Shipment in the sorting centre` la llegada a un centro de clasificación socio. Peek muestra los siete como «En tránsito». En un envío al Reino Unido, pueden aparecer líneas de [InPost](carrier:inpost) incluso antes de que el centro de clasificación de WanbExpress escanee el paquete: `Your parcel is on its way to InPost. Please check back for updates` («Etiqueta creada») y `We have your parcel. Please check back for updates` («Admitido por el transportista»). `We'll apply your My Account preferences to this parcel delivery` es un aviso de InPost sobre tus preferencias de cuenta, no un escaneo: Peek lo conserva en el historial sin cambiar la etapa. Lo mismo ocurre con `Consignee Contacted`, una llamada del repartidor registrada en el mismo minuto que un intento sin respuesta.

**Horas.** Cada escaneo trae su desfase respecto a UTC. Peek interpreta ese instante y muestra todas las horas en la zona horaria de tu dispositivo, de la más reciente a la más antigua. Para las pausas y traspasos habituales en envíos desde China, consulta [seguir un paquete desde China](guide:tracking-from-china).

## Quién entrega los paquetes de WanbExpress

Los escaneos de WanbExpress siguen el envío fuera de China, por la aduana y hasta un transportista local que lo entrega. El rastreador de WanbExpress puede indicar ese transportista, su número y una foto de entrega.

**Qué muestra el código de Peek.** En un envío a Alemania, el historial de WanbExpress muestra a iMile repartiendo, y un escaneo cita la red de Poste Italiane. En un paquete al Reino Unido aparecen líneas de InPost. El código también contempla reetiquetados con números de Cainiao Express en Francia (`DOFR…HD` o `CNFR…HD`) y líneas reenviadas de J&T Express Australia.

**Intentos de entrega y recogida.** Rigen siempre las condiciones del transportista que reparte: cuántos intentos hace, si deja aviso, dónde espera el paquete y durante cuántos días. Peek no ha encontrado normas propias de WanbExpress sobre ello. En cuanto sepas qué empresa entrega y su número, consulta su seguimiento y gestiona la entrega con ella. Más en [entrega fallida](guide:missed-delivery).

## Paquete WanbExpress retrasado o seguimiento parado

**Número no encontrado.** Si el rastreador de WanbExpress no conoce el número, Peek muestra «Esperando a WanbExpress» y sigue consultando. Si tras dos días WanbExpress sigue sin reconocerlo y ninguna otra fuente ha respondido, Peek muestra «Tras dos días, el transportista sigue sin reconocer este número. Revísalo, elige otro transportista o elimina el paquete.» y pasa a comprobarlo una vez al día, y una vez a la semana pasada una semana desde que lo añadiste. Revisa el código carácter por carácter frente al correo de la tienda: `WNB`, dos letras, diez cifras, `YQ`. Si pasan varios días sin novedades, pregunta al vendedor si llegó a enviarlo.

**Parado en `Shipping Order Created.` o `Parcel Data Received`.** La tienda ha reservado el envío, pero WanbExpress aún no ha escaneado el paquete. Pregunta al vendedor cuándo lo entregó al transportista.

**Parado tras `Parcel arrives at Airport, Parcel Arrival`.** Es probable que espere despacho aduanero de importación. En la Unión Europea, las compras online procedentes de fuera de la UE pagan desde el 1 de julio de 2026 un derecho de aduana temporal de 3 € por artículo (tipo de producto) en envíos de hasta 150 € y, desde el 1 de noviembre de 2026, una tasa de tramitación de la Unión de 2 € por artículo cualquiera que sea el valor. Si te piden pagar tasas de importación, consulta [paquetes retenidos en aduanas](guide:customs); si lleva mucho tiempo parado, avisa al vendedor. Más en [por qué el seguimiento no se actualiza](guide:tracking-not-updating).

**Sin novedades tras `Custom clearance completed`.** El transportista local ya tiene el paquete o está a punto de recibirlo, y su seguimiento puede ir por delante del de WanbExpress. Busca su nombre y número en tracking.wanbexpress.com o pregúntaselo al vendedor.

**Intento de entrega.** Tras un aviso como `<Route not completed>`, el transportista final decide cuándo repite el intento o dónde queda el paquete. Contacta con él, no con WanbExpress.

**Marcado como entregado, pero no ha llegado.** Comprueba si tracking.wanbexpress.com muestra una foto de entrega y contacta después con el transportista local y con el vendedor. Más en [entregado pero no recibido](guide:delivered-not-received).

**Perdido o devuelto.** Quien contrató el envío con WanbExpress fue el vendedor, así que corresponde al vendedor o al marketplace gestionar un reembolso o un nuevo envío. Consulta [paquete perdido](guide:lost-parcel) y [devuelto al remitente](guide:returned-to-sender).

## Atención al cliente y contacto de WanbExpress

Peek no pudo consultar la web de WanbExpress, por lo que esta página no recoge ningún teléfono ni correo de WanbExpress. Para cualquier problema con el pedido, habla con el vendedor que contrató el envío o con el marketplace si no responde. Para la entrega en destino, dirígete al transportista local.

## Seguir un paquete de WanbExpress con Peek

[Peek](/) identifica un número de WanbExpress por su forma (`WNB`, dos letras, diez cifras y `YQ`, exclusiva en su catálogo) y consulta el rastreador de WanbExpress solo con ese número, sin cuenta, código postal ni teléfono.

Como el rastreador de WanbExpress exige una verificación antes de responder, Peek lo consulta desde un navegador. Cuando el rastreador no responde, Peek acude a servicios de seguimiento y muestra el historial que tienen, con sus propias palabras; ese historial puede ir con retraso respecto al de WanbExpress.

Del rastreador de WanbExpress, Peek toma el estado, los escaneos con su lugar y los países de origen y destino del registro. Si el registro marca el paquete en tránsito, en reparto o entregado, Peek respeta ese estado aunque el último escaneo diga menos. Cuando WanbExpress devuelve el número del transportista local y su panel de última milla identifica una empresa del catálogo cuyo formato coincide con ese número, Peek puede mostrar «Entrega con …» (o «Anunciado a …, aún no recibido») y pasar a seguirlo allí. Peek no muestra fecha prevista de entrega y descarta las fotos de entrega y la dirección del destinatario; míralas en tracking.wanbexpress.com o añade aparte el número del transportista local en Peek. Peek sigue más de 4500 transportistas.

Mientras tienes el paquete abierto en pantalla o el día de la entrega con las notificaciones activadas, y mientras responde el rastreador de WanbExpress, Peek comprueba hasta cada 10 minutos, y hasta cada 5 minutos desde las 6:00 en la hora del transportista el día en que sale a reparto cuando la consulta abre un navegador; el resto del tiempo, cada hora. A través de servicios de seguimiento comprueba cada hora, y hasta cada 15 minutos el día de reparto mientras lo tienes abierto o con notificaciones activadas. Tras dos días sin novedades (12 horas mientras sigue en «Etiqueta creada» o «Admitido por el transportista»), Peek consulta una vez por hora. Puedes elegir avisos de «Cada escaneo», «Pasos importantes» o «Solo la entrega». «Pasos importantes» (entrega, recogida, aduana, intentos fallidos y devoluciones) cubre el reparto, un intento fallido y la entrega, pero no las líneas aduaneras de WanbExpress, que Peek clasifica como «En tránsito». Peek no puede contactar con WanbExpress ni con el vendedor por ti.

## Preguntas sobre el seguimiento de WanbExpress

### ¿Qué es WanbExpress?

Un transportista chino, también llamado Wanb Express, WanBang Express o WANB. En el código de Peek, sus escaneos arrancan en un centro de clasificación en Shenzhen, siguen el vuelo y la aduana y acaban con los escaneos del transportista que reparte en el país de destino.

### ¿Dónde puedo seguir un paquete de WanbExpress?

En la casilla de arriba o en el rastreador de WanbExpress en tracking.wanbexpress.com. Ese rastreador realiza una comprobación antibot y también puede indicar quién reparte en destino. Introduce el número completo `WNB…YQ`.

### ¿Qué significa «Customs declaration completed» en WanbExpress?

Que se ha completado una declaración aduanera para tu paquete. El texto no precisa de qué país es ni indica que la aduana de importación lo haya liberado: ese paso aparece como `Custom clearance completed`. Peek muestra ambos como «En tránsito».

### ¿Quién reparte los paquetes de WanbExpress en mi país?

Un transportista local, cuyo nombre y número puede mostrar el rastreador de WanbExpress. El código de Peek registra a iMile en un paquete a Alemania, líneas de InPost al Reino Unido y un escaneo que cita la red de Poste Italiane, además de relevos con J&T Express Australia o números `DOFR…HD`/`CNFR…HD` en Francia; para otros países, consulta tracking.wanbexpress.com o pregunta al vendedor.

### ¿Con quién contacto si mi paquete de WanbExpress se ha perdido?

Con el vendedor donde hiciste el pedido, que fue quien contrató el envío con WanbExpress. Peek no pudo consultar la web de WanbExpress para obtener datos de contacto.

:::sources
- [Universal Parcel Scraper: WanbExpress](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/wanbexpress) – nombres, formato `WNB…YQ`, consulta por navegador, textos de escaneo y etapas (incluidos `Customs inspection has not yet released`, `A-Scan: shipment in international transit`, líneas de iMile, InPost, Poste Italiane y socios en EE. UU.), horas con desfase UTC, identificación del transportista final (`TrackLastMileCarrier`), fotos y dirección descartadas
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – otros formatos acabados en `YQ` (`JS Express`, `Juxi Express`), servicios de seguimiento de respaldo y traspaso al transportista local
- [Comisión Europea: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – derecho de aduana temporal de 3 € por artículo hasta 150 € desde el 1 de julio de 2026, tasa de tramitación de la Unión de 2 € desde el 1 de noviembre de 2026
- [Comisión Europea: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – tasa de tramitación de 2 € por artículo cualquiera que sea el valor
:::
