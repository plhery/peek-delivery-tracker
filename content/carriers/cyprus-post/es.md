---
title: Seguimiento Cyprus Post: números, estados y aduanas
description: Sigue un envío de Cyprus Post: qué indica su código de 13 caracteres, significado de los estados UPU en inglés, tasas de la UE y reclamaciones.
slug: seguimiento-cyprus-post
published: 2026-11-22
updated: 2026-11-22
---

Cyprus Post (Κυπριακά Ταχυδρομεία), el Department of Postal Services, es el servicio postal nacional de la República de Chipre. Sigue un envío en la web de Cyprus Post, cypruspost.post, o pega el número arriba. ¿No tienes número? Consulta [cómo encontrar tu número de seguimiento](guide:find-tracking-number).

## Número de seguimiento de Cyprus Post

El rastreador de Cyprus Post admite los códigos de 13 caracteres del estándar S10 de la Unión Postal Universal, usados por los correos en certificados, envíos EMS, paquetes y cartas con seguimiento: [dos letras, nueve dígitos y dos letras](guide:tracking-number-formats).

:::anatomy RR 482917367 CY
- RR | Indicador de servicio: la primera letra indica el tipo de envío
- 482917367 | Número de serie de ocho dígitos seguido de un dígito de control
- CY | Código de país: CY para un número emitido bajo autoridad de Chipre
:::

| Primera letra (ejemplos inventados) | Qué es |
| --- | --- |
| `RR482917367CY` | R: envío certificado |
| `CP482917367CY` | C: paquete postal |
| `EE482917367CY` | E: envío EMS |
| `LA482917367CY` | L: carta con seguimiento |
| `UA482917367CY` | U: envío de correo con mercancías, sin seguimiento para el cliente |

Un envío depositado en el extranjero conserva el número de su correo de origen terminado en ese código de país (`…GB`, `…FR`, `…GR`), y el localizador de Cyprus Post también lo admite con los escaneos del correo extranjero. `There is no information for this item` significa que el rastreador no tiene ese número: compruébalo con el remitente.

## Qué significan los estados de Cyprus Post

En la versión inglesa del rastreador de Cyprus Post que lee Peek, cada escaneo muestra hora, país, ubicación, evento, oficina siguiente e información adicional. Los eventos usan los [nombres postales internacionales (UPU)](guide:tracking-statuses): `(Otb)` marca un paso en el país de origen y `(Inb)` uno en el país de destino. La oficina siguiente indica adónde va el envío, no dónde está. Para un envío dirigido a ti:

| Estado de Cyprus Post | Qué significa para ti |
| --- | --- |
| `Receive item from customer (Otb)` | El envío se ha depositado, en Chipre o en el extranjero. |
| `Insert item into bag (Otb)` | Embolsado en la oficina de cambio para viajar al extranjero. |
| `Receive item at office of exchange (Inb)` | Ha llegado al país de destino. |
| `Send items to delivery point/ collection point` | De camino a la oficina que lo entregará o lo guardará para recoger. |
| `Receive item at delivery office (Inb)` | En la oficina encargada del reparto. |
| `Hold item at point of delivery (Inb)` | En la oficina de reparto, a la espera de salir a ruta. |
| `Handover item to delivery agent (Inb)` | En manos del cartero. |
| `Item not delivered/ Pending delivery` | No entregado: la columna Extra Information indica el motivo. |
| `Receive item at collection point for pick-up (Inb)` | Listo para recoger en la oficina postal. |
| `Deliver item (Inb)` | Entregado, o recogido en la oficina postal. |

## Tasas aduaneras para compras fuera de la UE

Desde el 1 de julio de 2026, los bienes comprados online fuera de la UE —incluidos los envíos postales— ya no entran libres de arancel cuando el envío vale 150 € o menos: la UE aplica un derecho aduanero temporal de 3 € por artículo. Un artículo agrupa todas las mercancías del envío que comparten clasificación arancelaria, descripción y, si consta, origen, con independencia del número de unidades. Desde el 1 de noviembre de 2026 la UE cobra también una tasa de tramitación de la Unión de 2 € por artículo en compras online desde fuera de la UE, sea cual sea su valor, sujeta además a IVA. El pago recae en primer lugar sobre quien declara la mercancía ante aduanas: plataforma, vendedor, transportista o representante. En Chipre, el Department of Customs and Excise aplica los derechos de importación y el IVA a los bienes de fuera de la UE. Más en [paquetes retenidos en aduanas](guide:customs).

## Envío de Cyprus Post retrasado o no recibido

Si el seguimiento se ha parado, consulta [cuando el seguimiento no se actualiza](guide:tracking-not-updating). Si [aparece como entregado](guide:delivered-not-received) pero no lo tienes, confirma el número con el remitente. Si falta o llegó roto, consulta [paquete perdido](guide:lost-parcel) y [paquete dañado](guide:damaged-parcel).

**¿Compraste online y sigues esperando?** El Consumer Protection Service de Chipre señala que el vendedor debe entregar en 30 días por norma general salvo otra fecha acordada. Si incumple, avísale y dale un plazo adicional razonable; si sigue sin entregar, puedes resolver el contrato. Su línea de atención al consumidor, 1429, es gratuita.

**¿Un mensaje te pide pagar para liberar un paquete?** Lee [SMS falsos de entrega](guide:delivery-scam-texts) antes de tocar ningún enlace.

## Seguir envíos de Cyprus Post con Peek

Para un número postal acabado en `CY` con dígito de control válido, [Peek](/) pregunta a la vez a Cyprus Post y a [Chronopost](carrier:chronopost), mostrando «Cyprus Post tiene este paquete.» cuando Cyprus Post lo conoce, o «Aún no aparece en… Peek seguirá comprobando cuando lo añadas.». Con dígito de control erróneo Peek no consulta a Cyprus Post ni eligiéndolo con «Cambiar»: revisa el número con el remitente. Un enlace de cypruspost.post sirve si incluye el número.

En envíos despachados en el extranjero, Peek consulta primero al correo emisor cuando es posible —como [ELTA](carrier:elta) para `…GR`— o a servicios de seguimiento. El rastreador de Cyprus Post también acepta esos códigos: elige «Cyprus Post» con «Cambiar». Peek prueba Cyprus Post por sí solo cuando el correo extranjero indica destino Chipre o, sin destino indicado, cuando añades el envío desde Chipre, y cambia en cuanto Cyprus Post tiene escaneos tan recientes como el correo extranjero. Peek solo consulta a Cyprus Post para números de 13 caracteres. ¿Dudas del transportista? Pega el número igual: Peek sigue más de 4500 transportistas.

Peek muestra los estados de Cyprus Post tal cual, la oficina o centro y el país de cada escaneo en el mapa, la hora de entrega y la oficina donde espera o fue recogido. Los escaneos chipriotas conservan su hora exacta y los extranjeros se interpretan con el reloj de su país (si tiene varias zonas horarias, Peek estima la zona según el lugar indicado, lo que puede variar la hora), todo mostrado en tu zona horaria («Horas en tu zona horaria:»). No muestra fecha prevista ni peso, y omite Extra Information, oficina siguiente, códigos postales y firmante: para ver el motivo de una no entrega, consulta cypruspost.post.

Peek consulta directamente a Cyprus Post hasta cada 10 minutos, o cada 2 minutos desde las 6:00 según el reloj del último escaneo (hora de Chipre en Chipre) el día en que sale con el cartero, mientras el envío esté abierto en Peek o el día de entrega con las notificaciones activadas. Tras dos días sin novedades (12 horas si sigue en «Etiqueta creada» o «Admitido por el transportista»), Peek comprueba cada hora. Para las notificaciones puedes elegir «Todas las novedades», «Solo importantes» o «Solo el día de entrega». Tras cuatro días sin escaneos en un envío en «Etiqueta creada», «Admitido por el transportista» o «En tránsito», Peek muestra «Sin novedades desde el…», recuerda que «Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a Cyprus Post.» y ofrece «Pide ayuda a Cyprus Post».

## Preguntas frecuentes sobre Cyprus Post

### ¿Cyprus Post es el Department of Postal Services?

Sí. La Unión Postal Universal designa al Department of Postal Services (Cyprus Post) como operador designado de Chipre, servicio estatal encargado del servicio postal, y el regulador postal OCECPR nombra a Cyprus Post proveedor del servicio postal universal.

### ¿Qué significa CY al final del número?

Que el número fue emitido bajo la autoridad de Chipre. En la norma S10 de la UPU las dos últimas letras indican el país bajo cuya autoridad se emitió el código, por lo que no prueban con certeza dónde se depositó el envío. Peek consulta a Cyprus Post con estos números.

### ¿Puedo seguir un envío con número en U?

No como envío con seguimiento. La norma S10 asigna números en U a envíos de correspondencia con mercancías que viajan sin seguimiento para el cliente: el código sirve para la visibilidad en la cadena logística, como los datos electrónicos previos. Aun así, Peek consulta a Cyprus Post por un código U terminado en `CY`.

### ¿Cómo reclamo contra Cyprus Post?

Escribe primero a Cyprus Post. Si no responde en 15 días o su respuesta no te convence, reclama ante el regulador postal, la Office of the Commissioner for Electronic Communications and Postal Regulation (OCECPR). Puede hacerse online; su web está en griego, pero incluye un formulario de reclamación en inglés.

:::sources
- [Universal Postal Union: S10, identification of postal items](https://www.upu.int/UPU/media/upu/files/postalSolutions/programmesAndServices/standards/S10-12.pdf) – 13 caracteres, primeras letras R, C, E, L y U, dígito de control, código del país emisor
- [Universal Postal Union: member countries, Cyprus](https://www.upu.int/en/Universal-Postal-Union/About-UPU/Member-Countries?csid=-1&cid=86) – Cyprus Post, operador designado
- [Universal Postal Union: list of designated operators](https://www.upu.int/getmedia/0ff98b1d-9dd4-46e7-bdd1-dda1b654308f/SettlementCircularsEn.pdf) – Department of Postal Services (Cyprus Post)
- [National Open Data Portal: list of post offices](https://www.data.gov.cy/el/dataset/katalogos-kai-theseis-tahydromikon-grafeion) – Department of Postal Services, servicio estatal encargado del servicio postal (en griego)
- [OCECPR: universal postal service](https://ocecpr.ee.cy/katanalotes/katholiki-ypiresia/taxidromeia) – Cyprus Post como proveedor del servicio postal universal (en griego)
- [OCECPR: complaints](https://ocecpr.ee.cy/katanalotes/parapona) – primero al operador por escrito, 15 días, reclamación online, formulario en inglés (en griego)
- [Consumer Protection Service: online purchases](https://consumer.gov.cy/en/consumer-rights/66/59/?ctype=ar) – entrega en 30 días, plazo adicional, resolución del contrato (en griego)
- [Consumer Protection Service: contact us](https://consumer.gov.cy/en/contact-us) – línea gratuita de consumo 1429 (en griego)
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – derecho de 3 € por artículo desde el 1 de julio de 2026, tasa de tramitación de la Unión de 2 € desde el 1 de noviembre de 2026
- [European Commission: guidance on the EUR 3 customs duty and the Union handling fee](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – definición de artículo, envíos postales, tasa para cualquier valor, obligado al pago, IVA sobre la tasa
- [Department of Customs and Excise: About us](https://www.mof.gov.cy/mof/customs/customs.nsf/All/289500205CFF33DFC22572AC003A2B02?OpenDocument) – imposición de derechos de importación e IVA
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – datos leídos por Peek: rastreador en inglés de Cyprus Post, columnas y nombres de eventos
:::
