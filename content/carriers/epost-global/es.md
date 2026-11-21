---
title: Seguimiento ePost Global: números EPG, estados y reparto
description: Rastrea tu paquete de ePost Global: cómo es un número EPG, qué significan sus estados en inglés, quién reparte en España y cómo seguirlo con Peek.
slug: seguimiento-epost-global
published: 2026-11-21
updated: 2026-11-21
---

ePost Global (`epostglobalshipping.com`) es un consolidador internacional de paquetería y correo con sede en Estados Unidos (`11137 Warland Drive, Cypress, CA 90630, USA`). Nacido en la división *Express Postal Options (XPO)* de R.R. Donnelley y con más de 25 años de experiencia transfronteriza, recibe los pedidos en sus centros de procesamiento estadounidenses, gestiona el transporte aéreo y el despacho aduanero y entrega la última milla a través de más de 100 operadores postales y de mensajería en más de 200 países y territorios (además de envíos nacionales en EE. UU. y Canadá con `eDGE`). Sus códigos empiezan por `EPG`: pega el tuyo en el recuadro de arriba. Sus propios buscadores están en `epgtrack.com` (*EPG Track*) y `ppxtrack.com` (*PPX Track*); el catálogo de Peek también lo reconoce como `ePostGlobal`, `EPG`, `PPX Track`, `PPXTrack`, `RR Donnelley`, `RRD Donnelley` o `RRD International Logistics`.

## Número de seguimiento de ePost Global: formato y ejemplos

Peek reconoce un único formato propio para ePost Global: el prefijo `EPG` seguido de entre 10 y 15 cifras (las etiquetas actuales llevan 15). Con dígitos inventados:

| Número (inventado) | Qué significa |
| --- | --- |
| `EPG482910573649182` | `EPG` y 15 dígitos: un número actual de ePost Global. |
| `EPG4829105736` | `EPG` y 10 dígitos: también de ePost Global, la longitud mínima que admite Peek. |
| `EPG482910573` | Solo 9 dígitos: no cumple el formato de ePost Global. Tres letras y nueve cifras encajan con Colis Privé, Purolator y TNT Australia, a los que Peek consulta entonces. Si eliges ePost Global a mano, Peek avisa de que «no emite números como este». Comprueba si falta un dígito. |
| `EPG4829105736491827` | 16 dígitos, uno de más: Peek no reconoce ningún transportista. Revisa si sobra una cifra. |
| `LZ482910576US` | Dos letras, nueve dígitos y `US`: un código postal estadounidense. Peek asigna [USPS](carrier:usps) a la mayoría de los que empiezan por `C`, `E`, `L` o `R` (según las preguntas frecuentes de ePost Global, el seguimiento se hace en el portal de PPX o en el de USPS según el producto). Para ver también los pasos internos de ePost Global en Peek, añade el código `EPG`. |

Los espacios, guiones y minúsculas no importan al pegar el código en Peek. En `epgtrack.com` puedes introducir hasta 20 números a la vez, y en `ppxtrack.com` hasta 10.

**Dónde encontrarlo.** En el correo de confirmación de envío de la tienda o dentro de tu pedido. Si no das con él, mira [cómo encontrar el número de seguimiento](guide:find-tracking-number) y [formatos de números de seguimiento](guide:tracking-number-formats).

## Estados de seguimiento de ePost Global explicados

El localizador de ePost Global muestra cada evento en inglés con fecha, hora, texto y casi siempre lugar. Primero aparecen las líneas propias de ePost Global, por orden de avance:

| ePost Global dice | Qué significa para tu envío | Peek muestra |
| --- | --- | --- |
| `Data Received` | La tienda ha enviado los datos del paquete a ePost Global, que aún no tiene la mercancía. | «Etiqueta creada» |
| `Shipment in Transit to the ePost Global Processing Center` | Se genera un minuto después de `Data Received` en cada envío: forma parte de la etiqueta, no indica movimiento físico. | «Etiqueta creada» |
| `Parcel Received and Processing` | Uno de los centros de procesamiento de ePost Global en EE. UU. ha escaneado el paquete (con su localidad y estado). ePost Global afirma procesar y dar salida a los paquetes en 24 horas o menos. | «Admitido por el transportista» |
| `Parcel Shipped` | El envío ha salido del centro de procesamiento estadounidense. | «En tránsito» |
| `In transit to … tracking #: …` | Traspaso al operador colaborador un minuto después de salir, indicando el servicio y el número de seguimiento del socio (también puede verse como `In transit via …` o `tracking#:`). | «En tránsito» |
| `In transit` | El mismo traspaso sin detallar el servicio ni el código del socio. | «En tránsito» |

**Eventos retransmitidos del transportista colaborador.** Tras la cesión, `epgtrack.com` retransmite las lecturas del socio. Los textos que el código de Peek clasifica directamente:

| Tras el traspaso | Qué significa para tu envío | Peek muestra |
| --- | --- | --- |
| `Shipping label created - OSM awaiting item` | El socio estadounidense OSM Worldwide ha recibido los datos del envío. | sin retroceso |
| `Arrived at OSM facility`, `Departed OSM facility`, `Package accepted at courier partner`, `Package processed by courier partner` | Paso por una planta de OSM Worldwide y entrega a su propio mensajero colaborador. | «En tránsito» |
| `Electronic manifest received` | Manifiesto electrónico registrado por ECMS Express tras la admisión en ePost Global: aviso de datos sin ubicación que mantiene la etapa previa. | sin cambio |
| `Parcel has arrived at export distribution warehouse` | Llegada al almacén de exportación de ECMS Express en origen. | «En tránsito» |
| `Customer Information Manifested` | DAI Post, socio para Nueva Zelanda, ha registrado los datos. | sin retroceso |
| `Checked in at origin hub`, `Airline Delay`, `Collected from the airline for processing` | Centro de DAI Post en EE. UU., retraso del vuelo (`Airline Delay` es demora aérea, no incidencia del paquete) y recogida del avión en destino. | «En tránsito» |
| `Consignment received at the PostNL Acceptance Centre`, `The item has been manifested and ready to transport`, `The item is tied to a MAWB and ready for shipment`, `The item is received by PostNL`, `The item is on transport to the country of destination` | [PostNL](carrier:postnl), a través de Spring, ha recibido el paquete y lo encamina por vía aérea hacia el país de destino. | «En tránsito» |
| `The consignment has arrived in the country of destination`, `The item has arrived in the country of destination`, `The item is at the PostNL sorting center`, `The item is processed at the PostNL sorting center`, `The item has been processed in the country of destination` | Llegada al país de destino y clasificación en la red de PostNL. | «En tránsito» |
| `The item is at customs`, `The item is being processed by customs` | Despacho aduanero de importación en un envío gestionado con PostNL. | «En aduanas» |
| `Item released by customs` | Aduana superada en PostNL; el paquete continúa su ruta. | «En tránsito» |
| `The item is out for delivery` | En reparto según el aviso de PostNL. | «En reparto» |
| `The item will be scheduled for a new delivery attempt` | Intento fallido comunicado por PostNL; se programará un nuevo pase. | «Intento de entrega» |
| `Your item has been dropped off by the sender with our postal partner in the country of origin.`, `Your item is ready to be forwarded from its foreign territory. It will be given to the carrier for forwarding.` | Avisos de [La Poste](carrier:la-poste) para paquetes que PostNL lleva hacia Francia, aún fuera de territorio francés. | «En tránsito» |
| `The parcel has left the parcel center.`, `The parcel has reached the parcel center.` | Red europea de GLS: salida del hub y llegada al centro de paquetería. | «En tránsito» |
| `The parcel is expected to be delivered during the day.` | Red europea de GLS: paquete en reparto durante el día. | «En reparto» |
| `The parcel has been delivered.` | Red europea de GLS: entregado. | «Entregado» |
| `Departed Hub`, `Shipment Received in Depot`, `Shipment Departed Depot` | Socio británico de [Royal Mail](carrier:royal-mail): rumbo al Reino Unido o paso por almacén. | «En tránsito» |
| `Arrived in customs` | Mismo socio británico: entrada en la aduana británica. | «En aduanas» |
| `Ready for Collection` | Mismo socio británico: paquete listo en un punto de recogida. | «Listo para recoger» |
| `Duty &/or tax fees requested` | El operador de destino solicita el pago de aranceles o impuestos antes de entregar. | «En aduanas» |
| `Received payment` | Esos cargos ya se han abonado y el envío sigue adelante. | «En tránsito» |
| `The item is available at the pickuppoint` | El paquete espera a que lo recojas en un punto del operador local. | «Listo para recoger» |

La admisión de un socio después de `Parcel Shipped` cuenta como «En tránsito», no como un reinicio. Peek interpreta el resto de frases de socios con sus reglas generales; si desconoce un texto, lo muestra tal cual sin atrasar un paquete que ya esté en reparto o entregado.

## Quién reparte los paquetes de ePost Global en España

ePost Global trabaja con más de 100 operadores postales y de última milla en seis continentes. En los historiales del código de Peek figuran la red europea de GLS, [PostNL](carrier:postnl) mediante Spring (con entrega posterior de [La Poste](carrier:la-poste) en Francia), OSM Worldwide, ECMS Express, DAI Post y [Royal Mail](carrier:royal-mail). Cuando ePost Global no nombra socio pero facilita un código postal internacional válido (dos letras, nueve dígitos, dos letras), Peek consulta al operador postal del país de destino, como [Correos](carrier:correos-spain) en **España** (para **México**, en cambio, al operador que emitió el número).

**Plazos orientativos y alcance del rastreo.** Desde Estados Unidos, la página *Cross-Border Parcel Shipping* de ePost Global indica `1-5 days` puerta a puerta en la mayoría de rutas con *Expedited Parcel with Delivery Confirmation*, y `4 to 8 days` hacia Canadá o `5 to 10 days` hacia Europa Occidental con *Priority Parcel with Delivery Confirmation* (`4 to 8 days` a Canadá y Europa Occidental con *Priority Packet Tracked*, que ofrece seguimiento puerta a puerta a 48 países clave). Cinco de sus siete productos de paquetería incluyen seguimiento puerta a puerta; los dos económicos ofrecen seguimiento dentro del país una vez llega a destino. Los intentos de entrega y la custodia en oficina o punto dependen del transportista local: consulta [entrega fallida](guide:missed-delivery) y [paquete no recogido](guide:uncollected-parcel).

**Aduanas en España y la Unión Europea.** En la **Unión Europea**, desde el 1 de julio de 2026 las compras online procedentes de fuera de la UE en paquetes con valor intrínseco de hasta 150 € abonan un arancel aduanero de la UE de 3 € por artículo (*per item*), y desde el 1 de noviembre de 2026 se añade una tasa de tramitación de la Unión (*Union handling fee*) de 2 € por artículo cualquiera que sea el valor (un artículo agrupa mercancías del mismo envío con idéntica clasificación arancelaria, descripción y, cuando sea exigible, origen; por encima de 150 € rigen los aranceles ordinarios), además del IVA de importación en **España peninsular y Baleares**, salvo que ya se cobrara en la compra mediante IOSS. Según el ámbito territorial publicado por la Comisión Europea, las **Islas Canarias** pertenecen al territorio aduanero de la UE pero quedan fuera del área de IVA comunitaria, mientras que **Ceuta** y **Melilla** quedan fuera tanto del territorio aduanero como del ámbito de IVA de la UE. Según la guía aduanera de ePost Global, la tasa de 2 € se aplica tanto a envíos `DDP` (cargos cobrados al pagar el pedido) como `DDU` (a cargo del destinatario a la entrega, pudiendo retenerse el paquete hasta el pago). Más detalles en [paquete retenido en aduanas](guide:customs).

## Paquete de ePost Global parado, perdido o devuelto

**«Esperando a ePost Global».** Si `epgtrack.com` responde `We haven't received any tracking updates`, Peek muestra «Esperando a ePost Global» y sigue consultando. Comprueba que el código tenga `EPG` y de 10 a 15 cifras; si pasados dos días ePost Global sigue sin reconocerlo y ningún servicio de seguimiento lo localiza, Peek avisa: «Tras dos días, el transportista sigue sin reconocer este número. Revísalo, elige otro transportista o elimina el paquete.»

**Sin cambios después de `In transit to …`.** El socio de reparto ya gestiona el paquete y su propio localizador puede ir por delante. Si Peek aún no ha traspasado el seguimiento, busca el código detrás de `tracking #:` y añádelo también en Peek.

**Varios días sin novedades.** Tras cuatro días sin un escaneo nuevo, Peek marca «Sin novedades desde hace cuatro días», recuerda que los envíos suelen quedarse en silencio en vuelos largos, en aduanas o en fin de semana, y muestra el enlace «Pide ayuda a ePost Global» (o el del operador colaborador cuando Peek ya sigue el envío allí y dispone de su página de ayuda; OSM Worldwide y DAI Post no tienen una en el catálogo, por lo que se mantiene la de ePost Global). También puedes revisar incidencias de ruta en `epostglobalshipping.com/service-updates` y leer [por qué no se actualiza el seguimiento](guide:tracking-not-updating).

**Entregado pero no recibido, perdido o devuelto.** Pregunta primero al transportista que reparte en tu zona con su número local y después reclama a la tienda donde hiciste la compra, que es quien contrató el envío con ePost Global. Consulta [entregado pero no recibido](guide:delivered-not-received), [paquete perdido](guide:lost-parcel) y [devuelto al remitente](guide:returned-to-sender).

## Atención al cliente y contacto de ePost Global

Según `epostglobalshipping.com`:

- **Teléfono (EE. UU.)**: `+1 866-784-8444` (gratuito en EE. UU.) y `+1 310-784-8485` (número local), sin horario de atención ni correo de soporte publicados en la página.
- **Asistente online ePost Assist**: en `epostassist.epgtrack.com` (desde *Track a package* o *Chat with our online support*), con opciones para *Check package status*, *Create a support ticket* y *Get support ticket status*.
- **Formulario web**: en `epostglobalshipping.com/contact-support` (*Looking for support beyond tracking a package?*), con campo opcional para el número de seguimiento.
- **Sede central**: `11137 Warland Drive, Cypress, CA 90630, USA`.

**Cuidado con los SMS falsos.** Los dominios oficiales de ePost Global para rastreo y soporte son `epgtrack.com`, `epostassist.epgtrack.com`, `ppxtrack.com` y `epostglobalshipping.com`. En un envío `DDU` a España, cualquier aviso legítimo de pago aduanero procede del operador postal o mensajería que lo entrega allí: compruébalo antes de pagar y revisa [mensajes falsos de entrega](guide:delivery-scam-texts).

## Seguir un paquete de ePost Global con Peek

[Peek](/) identifica al instante un código de `EPG` y 10 a 15 dígitos («Transportista detectado») y consulta `epgtrack.com` sin cuenta ni código postal. También puedes pegar un enlace de `epgtrack.com` o `ppxtrack.com`: Peek extrae el código (el primero si `?id=` trae varios). Si solo tienes el número del transportista colaborador, pégalo igualmente: Peek sigue a más de 3.500 transportistas.

Peek muestra el estado, cada escaneo con su lugar, el país de destino y el socio de reparto con su número cuando ePost Global los indica. Omite la barra de progreso, los códigos postales, números de nave o calle, la referencia del cliente y los conocimientos aéreos (`MAWB`); `epgtrack.com` no facilita fecha estimada de entrega, peso ni justificante de entrega, así que Peek no muestra ninguno. El botón «Abrir la web de ePost Global» abre tu código directamente en `epgtrack.com`.

El rastreador publica las horas sin zona horaria. Peek interpreta `Parcel Received and Processing` y `Parcel Shipped` con el reloj del estado de EE. UU. del centro cuando todo el estado comparte huso, los registros sin país de PostNL con la hora de PostNL, las líneas de la red europea de GLS en hora centroeuropea y las del socio británico de Royal Mail en hora del Reino Unido; las demás se fechan por su lugar o por el tramo del viaje. Todo aparece en la zona horaria de tu dispositivo y, tanto en `peektracker.com` como en las apps de iPhone y Android, el historial termina con «Horas en tu zona horaria: …».

Cuando ePost Global nombra un socio que Peek conoce, la tarjeta indica «Entrega con PostNL» (por ejemplo), o «Anunciado a PostNL, aún no recibido» mientras ese socio solo tenga el anuncio del envío sin escaneos propios, y añade el código del socio tras el número `EPG`. Peek consulta también a ese operador (con su número o, si falta, con el `EPG`); si no figura socio nombrado, prueba con el operador postal del país de destino ante un número postal válido (para México, con el que emitió el número) o con el único transportista al que apunte el formato del segundo número (siempre que Peek lea su rastreador directo sin datos extra). En cuanto ese operador registra un escaneo al menos tan reciente como el último de ePost Global, Peek pasa a seguirlo allí conservando el historial previo (para la red europea de GLS, como ePost Global no indica qué empresa nacional de GLS entrega, Peek permanece en las líneas retransmitidas por ePost Global).

Mientras tengas el paquete abierto o, el día de reparto, las notificaciones activadas, Peek comprueba novedades hasta cada 10 minutos —y hasta cada 2 minutos desde las 6:00 locales el día en que pasa a «En reparto» mientras responda el buscador de ePost Global (cuando otro operador asume la entrega, Peek sigue el ritmo de ese transportista; solo mediante servicios de seguimiento: cada hora, cada 3 horas tras un día sin cambios y hasta cada 15 minutos el día de entrega con el paquete abierto o notificaciones activas)—; en el resto de casos, y tras dos días sin noticias (12 horas en «Etiqueta creada» o «Admitido por el transportista»), consulta cada hora. Puedes elegir alertas de «Cada escaneo», «Pasos importantes» o «Solo la entrega». Peek no puede contactar con ePost Global ni con la tienda por ti.

## Preguntas frecuentes sobre el seguimiento de ePost Global

### ¿Qué es PPX Track?

Es otro portal de seguimiento de ePost Global (`ppxtrack.com`, hasta 10 envíos por consulta) y uno de los alias de ePost Global en Peek. Peek lee los enlaces de `ppxtrack.com` igual que los de `epgtrack.com` y extrae el código.

### ¿Qué significa «In transit to» en ePost Global?

Que ePost Global ha entregado el paquete al socio encargado del siguiente tramo. La línea indica el servicio contratado y, tras `tracking #:`, el número de seguimiento propio de ese operador.

### ¿Por qué mi envío sigue en «Data Received»?

La tienda ha transmitido los datos electrónicos a ePost Global, pero ningún centro de procesamiento estadounidense ha escaneado aún la caja. Pregunta al vendedor cuándo entregó físicamente el pedido.

### ¿Quién entrega los paquetes de ePost Global en España?

El operador colaborador que indique la línea de traspaso: en los registros que maneja Peek aparecen redes como la europea de GLS o PostNL a través de Spring. Cuando el envío viaja con un código postal internacional sin socio nombrado, Peek consulta a Correos en España.

### ¿Cómo contacto con ePost Global?

Reclama primero a la tienda donde compraste. Para acudir a ePost Global directamente, usa el asistente *ePost Assist* en `epostassist.epgtrack.com`, el formulario de `epostglobalshipping.com/contact-support` o sus teléfonos de EE. UU. `+1 866-784-8444` y `+1 310-784-8485`.

:::sources
- [ePost Global: página principal](https://epostglobalshipping.com/) – consolidador internacional, 20M+ envíos anuales, 100+ socios de transporte, 200+ países, dirección en Cypress y teléfonos
- [ePost Global: About us](https://epostglobalshipping.com/about-us) – más de 25 años de trayectoria, origen en R.R. Donnelley (XPO) y red en seis continentes
- [ePost Global: Cross-Border Parcel Shipping](https://epostglobalshipping.com/cross-border-parcel-shipping) – salida en 24 h o menos, plazos hacia Canadá y Europa Occidental, rastreo puerta a puerta o en destino, DDP y DDU, portales PPX y USPS
- [ePost Global: eDGE](https://epostglobalshipping.com/edge) – envíos domésticos en EE. UU. y Canadá con más de 100 transportistas
- [ePost Global: EU customs fee ecommerce](https://epostglobalshipping.com/eu-customs-fee-ecommerce) – tasa de 2 € por artículo en DDP y DDU, arancel de 3 € hasta 150 €, IOSS y retención hasta el pago en DDU
- [ePost Global: Contact support](https://epostglobalshipping.com/contact-support) – formulario de soporte, acceso a ePost Assist, teléfonos y dirección postal
- [ePost Global: Contact us](https://epostglobalshipping.com/contact-us) – enlace al soporte online por paquetes extraviados
- [ePost Global: Service updates](https://epostglobalshipping.com/service-updates) – suspensiones e incidencias de servicio por país
- [EPG Track](https://epgtrack.com/) – rastreador oficial de hasta 20 números
- [PPX Track](https://ppxtrack.com/) – portal PPX Track de hasta 10 números
- [ePost Assist](https://epostassist.epgtrack.com/) – consulta de estado y apertura o seguimiento de tickets
- [Comisión Europea: Reforma aduanera de la UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – arancel de 3 € desde el 1 de julio de 2026 y tasa de 2 € desde el 1 de noviembre de 2026
- [Comisión Europea: Orientaciones sobre el arancel de 3 € y la tasa de la Unión (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – cobro de 2 € sin importar el valor, definición de artículo y exención de IVA mediante IOSS
- [Comisión Europea: Ámbito territorial del IVA y aduanas](https://taxation-customs.ec.europa.eu/taxation/vat/vat-directive/how-does-vat-work/territorial-scope_en) – Islas Canarias, Ceuta y Melilla
- [Universal Parcel Scraper: ePost Global](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/epost-global) – alias, formato EPG, enlaces, estados propios y de socios, husos horarios, campos omitidos y página de ayuda
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formatos de Colis Privé, Purolator y USPS, y traspaso al socio o a Correos
:::
