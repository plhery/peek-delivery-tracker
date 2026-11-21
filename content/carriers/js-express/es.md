---
title: JS Express seguimiento: número JSE…YQ, estados y reparto
description: Sigue tu paquete de JS Express (急速国际) desde China: cómo es el número JSE…YQ, qué significan sus estados en inglés y quién lo entrega en España.
slug: js-express-seguimiento
published: 2026-11-17
updated: 2026-11-20
---

JS Express (急速国际, también JS EXP) es una empresa logística china para tiendas online transfronterizas, gestionada en Suzhou por 急创速供应链管理（苏州）有限公司. Si una tienda te ha enviado el pedido con JS Express, el número empieza por JSE y termina en YQ: pégalo en la casilla de arriba. JS Express lleva el paquete desde China hasta España y una empresa de reparto local se encarga de la entrega; en su historial en España aparece a veces [CTT Express](carrier:ctt-express).

## Número de seguimiento de JS Express: cómo es

Los albaranes de JS Express llevan JSE, dos letras, diez cifras y YQ: 17 caracteres en total. Con cifras inventadas:

| Número | Qué es |
| --- | --- |
| `JSEAA9999999980YQ` | JSE, dos letras, diez cifras y YQ: un número de envío de JS Express. |
| `JSEAA999999980YQ` | Solo nueve cifras: no es el formato de JS Express. Revisa si falta algún dígito. |
| `JXCAA9999999980YQ` | También acaba en YQ, pero empieza por JXC: es un número de Juxi Express (que Peek comprueba primero en [DHLink](carrier:dhlink) cuando tras JXC van YI, ZO, US o ZI), no de JS Express. |

Otros transitarios chinos usan esa misma estructura con sus propias letras delante: FEI en 17FEIA, WNB en WanbExpress, SFC en SFC, CJP en CJ Packet. JSE identifica a JS Express. En Peek no importan los espacios ni las minúsculas: lee un cero escrito en la segunda de las dos letras como una O, y una O entre las diez cifras como un cero. El buscador de JS Express necesita el número entero: con una parte no encuentra nada.

**Dónde encontrarlo.** La tienda te manda el número en el correo de confirmación de envío o lo muestra en los detalles de tu pedido. JS Express indica que colabora estrechamente con Amazon, Walmart, Shopify, eBay, AliExpress, Wish y Shopee. Si no das con él, mira [cómo encontrar tu número de seguimiento](guide:find-tracking-number).

**Sin enlace directo.** Las páginas de consulta de JS Express piden el código en un formulario, así que ningún enlace abre directamente un envío: copia el número. La página en inglés, «Express Query», responde al momento; la china pide antes un código de imagen (验证码).

## Estados del seguimiento de JS Express: qué significa cada uno

La web de JS Express muestra cada escaneo con fecha y hora, un lugar entre 【corchetes】 y un mensaje, del más reciente al más antiguo, con «Latest» arriba del todo. Casi todos los textos vienen en inglés. En el orden en que los recibe un paquete:

| JS Express escribe | Qué significa para ti | Peek muestra |
| --- | --- | --- |
| `Electronic notification of order creation received.` | La tienda ha enviado los datos del pedido a JS Express, que todavía no tiene el paquete. | «Etiqueta creada» |
| `PRE-ADVICE` | Un socio logístico ha recibido los datos electrónicos antes de que el paquete entre en el almacén de JS Express. | «Etiqueta creada» |
| `Item has been collected` | El almacén de JS Express en China ya tiene el paquete: es el primer escaneo real. | «Admitido por el transportista» |
| `Item inbound in sorting center.`, `Item outbound in sorting center.` | Clasificado en el centro de JS Express en China. | «En tránsito» |
| `Domestic allocation has begun`, `Under domestic allocation` | Sigue en el almacén de JS Express en China, tras la clasificación y antes de salir. | «En tránsito» |
| `Waybill Generated for` | El transportista del país de destino ha generado su etiqueta mientras el paquete sigue en China. | «Etiqueta creada», o la fase que ya tenía, normalmente «En tránsito» |
| `Arrive at transit center` | En el centro de tránsito de JS Express en China, entre la clasificación y la salida. | «En tránsito» |
| `Item departed from origin.`, `Arrive at international airport to abroad` | De salida hacia el exterior y llegada al aeropuerto internacional en China. | «En tránsito» |
| `Arrived at export center`, `Exports Center Sorting and Packing`, `Arrived at Origin Port` | En algunas líneas, un centro de exportación en China clasifica y embala el envío antes de llegar al punto de salida del país. | «En tránsito» |
| `Shipment arrived at facility and measured.`, `Depart from facility to service provider.` | Una instalación en China pesa y mide el paquete y lo pasa a la línea encargada del vuelo. | «En tránsito» |
| `Start on-site transportation and shipment` | En el aeropuerto en China: ha empezado el movimiento en pista antes del despacho de exportación chino. | «En tránsito» |
| `Released From Export Customs` | La aduana de exportación de China lo ha despachado. Todavía no es la aduana española. | «En tránsito» |
| `DEPART ORIGIN AIRPORT`, `Departure from the original airport`, `Item Flight departure`, `Initial landing`, `Connecting flights flight takeoff` | El avión ha despegado, a veces con escala y vuelo de conexión. | «En tránsito» |
| `ARRIVED AT DESTINATION AIRPORT`, `Arrival to the destination airport`, `Item Flight landing`, `NOA-Notification of arrival from GHA` | Ha aterrizado en el aeropuerto de llegada. NOA es el aviso del operador de asistencia en tierra del aeropuerto. | «En tránsito» |
| `The customs clearance agent is queuing up to pick up the goods` | El agente de aduanas espera en la terminal de carga del aeropuerto para recoger la mercancía: aún no la tienen ni él ni la aduana. | «En tránsito» |
| `The customs clearance agent has picked up the goods` | El agente ha recogido la remesa en la terminal de carga para el despacho de importación. | «En aduanas» |
| `The goods have been picked up abroad` | El socio en destino se ha hecho cargo del envío en el aeropuerto. No es una nueva recogida en el vendedor. | «En tránsito» |
| `Customs clearance agent received the shipment in destnation country.` | El agente de aduanas en destino ha recibido la mercancía tras el vuelo para la importación («destnation» está escrito así por JS Express). | «En aduanas» |
| `Inbound to customs temporary warehouse` | En un almacén temporal aduanero, a la espera del despacho de importación. | «En aduanas» |
| `Released From Import Customs`, `Released from customs: customs cleared.` | La aduana de importación ha dejado pasar el paquete. | «En tránsito» |
| `Depart from the destination distribution center.` | Un centro de distribución en destino reenvía el paquete tras pasar la aduana. | «En tránsito» |
| `In transit, it's progressing through post network` | El operador postal avanza el paquete por su red, antes del reparto. | «En tránsito» |
| `collection by overseas warehouse` | El almacén de JS Express en destino ha recibido el paquete después de la aduana. | «En tránsito» |
| `Load cargo overseas`, `Vehicles depart from` | Ese almacén carga el paquete para el transportista local y el camión sale del lugar que acompaña al escaneo. | «En tránsito» |
| `Delivery to local courier`, `Last Mile Delivery` | Entregado a la empresa que reparte en España, antes de su primer escaneo propio. Aún no te lo han entregado. | «En tránsito» |
| `Delivery in progress` | En algunas rutas: el transportista local tiene el paquete en su delegación antes de sacarlo a reparto. Pese al texto, todavía no va en la furgoneta. | «En tránsito» |
| `到达待取` | Estado propio de JS Express en chino: ha llegado y espera recogida. Como lugar solo aparece el país. | «Listo para recoger» |
| `The delivery address is incorrect. Please ask the recipient to contact their local area?` | Aviso de JS Express tras un intento fallido por dirección incompleta o errónea; pide contactar con la oficina local (Peek quita los números de teléfono). | «Intento de entrega» |

Ese mismo aviso de dirección aparece también en chino (`收件人地址不详或有误，派送失败，逾期退回或销毁的具体费用以账单为准，麻烦让收件人联系下当地`), con un teléfono al final: Peek lo traduce al inglés y muestra «Intento de entrega».

**El escaneo de CTT Express en España.** Cuando reparte [CTT Express](carrier:ctt-express) en España, el historial de JS Express recoge en español su estado `Enviado`: significa que CTT Express ya ha admitido el paquete y lo ha puesto en camino entre sus centros antes del reparto («En tránsito»).

**Escaneos de un operador postal europeo.** En algunas rutas postales europeas aparecen `Package scanned at carrier facility` y `Departure to country of destination` en un centro europeo, seguidos de `Arrival from abroad`, `Departure to distribution network`, `Your shipment has arrived at the postal operator of the country of destination and will be delivered in the coming days.` y `Arrival distribution office` en destino, todos como «En tránsito». Si no hay nadie en casa, `Not Delivered - Addressee not present- Message left` indica aviso dejado («Intento de entrega»), y `Item arrival at collection point for pick-up` confirma que el envío espera en un punto de recogida («Listo para recoger»).

**Un repartidor sin nombre en mayúsculas.** En otras líneas, JS Express copia en mayúsculas los estados de la empresa que entrega sin decir cuál es. `SHIPMENT RECEIVED`, `PROCESSED AT HUB` y `SHIPMENT PROCESSED` marcan el paso por su centro logístico, y `DEPARTED FROM DEPOT` la salida hacia la nave de reparto («En tránsito»). `RECEIVED AT LOCAL DEPOT` es esa nave antes de cada ruta o al volver de un intento fallido: no es un punto de recogida, así que Peek mantiene el estado del escaneo anterior. `DELIVERY ATTEMPTED: 1ST DELIVERY ATTEMPT`, `DELIVERY ATTEMPTED: 2ND DELIVERY ATTEMPT` y `DELIVERY ATTEMPTED: 3RD DELIVERY ATTEMPT` son intentos sin entrega («Intento de entrega»); al tercero piden concertar nueva entrega, y `SHIPMENT REDIRECTED: ON CONSIGNEE'S REQUEST` confirma el reenvío acordado («En tránsito»). `DELIVERY EXCEPTION` y `DELIVERY EXCEPTION, WAIT FOR INFORMATION CONFIRMATION`, tras `ARRIVE AT THE WAREHOUSE` («En tránsito»), avisan de una incidencia pendiente de confirmar datos («Incidencia»).

**Líneas que no son escaneos.** `SHIPPING INFORMATION RECEIVED`, `SHIPMENT CREATED`, `Data Received`, `货物电子信息已经收到`, `Your package is being prepared by the sender and will be handed over to us soon` y, en un hub europeo, `Shipment Data Uploaded` seguido de `Processed` son simples registros electrónicos de un socio antes de recibir la caja. `Consignee Contacted`, anotado en el mismo segundo que un intento fallido, refleja una llamada o mensaje del repartidor y tampoco cambia el estado.

**Horas desordenadas.** Las horas vienen sin zona horaria y mezclan relojes: hora de Pekín en China y, por lo general, hora local en aeropuertos y transportistas europeos. Fíjate en el orden de las filas, no solo en la hora escrita.

## Quién reparte los paquetes de JS Express en España

JS Express incluye a España en su línea Europa (junto con Reino Unido, Francia, Alemania, Italia, Suiza y Dinamarca, salvo algunos códigos postales remotos). En su historial para España aparece [CTT Express](carrier:ctt-express), aunque JS Express no afirma que todos los envíos a España vayan con esa misma empresa.

Los datos internos de JS Express pueden guardar el número propio del transportista local sin indicar su nombre, y la web de JS Express no enseña ninguno de los dos. Cuando ese segundo código tiene un formato exclusivo de una empresa que opera en España (por ejemplo, un número propio de [CTT Express](carrier:ctt-express)), Peek muestra «Entrega con …» junto a su número y la consulta también. Si es un [número postal internacional](guide:tracking-number-formats) válido (dos letras, nueve cifras y dos letras), Peek consulta a [Correos](carrier:correos-spain). Si Peek no muestra ningún repartidor, pregunta a la tienda quién entrega tu pedido y con qué número.

Los intentos de entrega, avisos y puntos de recogida siguen las normas del transportista local: JS Express no publica ninguna. Como `到达待取` solo indica el país, revisa el seguimiento de la empresa local o pregunta al vendedor dónde recogerlo. Mira también [intento de entrega fallido](guide:missed-delivery) y [paquete no recogido a tiempo](guide:uncollected-parcel).

## Paquete de JS Express parado, en aduanas o que no llega

**`未获取到任何数据,请提供有效单号!`** La página inglesa de JS Express responde esta frase en chino cuando no reconoce el código («no se han obtenido datos, facilite un número válido»). `No track data for now!` indica que aún no hay escaneos. En ambos casos, Peek muestra «Esperando a JS Express» y sigue comprobando. Si pasados dos días JS Express sigue sin conocer el número y ningún otro servicio responde por él, aparece «Tras dos días, el transportista sigue sin reconocer este número. Revísalo, elige otro transportista o elimina el paquete.», y desde entonces Peek comprueba una vez al día y, pasada una semana desde que lo añadiste, una vez por semana. Revisa el código carácter por carácter con el de la tienda; si en unos días no cambia nada, pregunta al vendedor si llegó a entregarlo.

**Parado en `Electronic notification of order creation received.`** JS Express aún no ha recogido el paquete en su almacén. Pregunta a la tienda cuándo lo entregó.

**Parado en `Inbound to customs temporary warehouse`.** El envío espera el despacho de importación (en algunas rutas aparece `Start Custom clearance Process` y luego `customs clearance completed`; un `Under customs clearance, please wait patiently.` antes del vuelo corresponde a la aduana china de salida, «En tránsito»). JS Express comercializa su línea Europa en modalidad DDP: cobra el IVA por adelantado al vendedor y no entrega justificante fiscal. Con carácter general, según la Comisión Europea y la Agencia Tributaria, desde el 1 de julio de 2026 las compras por internet de hasta 150 € procedentes de fuera de la UE pagan un arancel fijo de 3 € por categoría de artículos, y desde el 1 de noviembre de 2026 se suma una tasa de tramitación de 2 € por categoría en las ventas a distancia, cualquiera que sea su valor; si la plataforma no los cobró al comprar, el transportista puede pedírtelos junto con el IVA antes de la entrega (la Aduana reclama esos importes al declarante, no directamente al consumidor). Si un repartidor te pide un pago que ya abonaste, consúltalo antes con la tienda. Más detalles en [paquete retenido en aduanas](guide:customs), [el seguimiento no se actualiza](guide:tracking-not-updating) y [seguimiento de paquetes desde China](guide:tracking-from-china).

**Nada después de `Delivery to local courier`.** Ya lo tiene el transportista local. Si Peek muestra su número, búscalo en la web de ese transportista; si no, pídeselo a la tienda.

**Figura como entregado, pero no ha llegado.** Reclama primero a la empresa de reparto local (por ejemplo, CTT Express o Correos) y avisa enseguida a la tienda. Consulta [paquete entregado pero no recibido](guide:delivered-not-received).

**Perdido, dañado o devuelto.** El cliente de JS Express es el vendedor: según las condiciones de JS Express, cualquier reclamación debe presentarla el cliente por escrito dentro de los 30 días siguientes a la recepción del envío por JS Express, y los paquetes asegurados se tramitan desde su cuenta de cliente. Pide por tanto el reembolso o un reenvío a la tienda o al marketplace sin esperar. Cuando un paquete no puede entregarse (nadie en casa, rechazo, dirección incorrecta o bloqueo aduanero), JS Express puede devolverlo cobrando el retorno al remitente si este lo solicita; si no quiere recuperarlo, se da por abandonado y la aduana de destino lo destruye. En algunas rutas, `Refused` seguido de `Returned` significa que el paquete rechazado ha vuelto al centro de clasificación del repartidor, todavía no al vendedor («Incidencia»). Mira [paquete perdido](guide:lost-parcel) y [paquete devuelto al remitente](guide:returned-to-sender).

## Teléfono y atención al cliente de JS Express

A 16 de noviembre de 2026, la página de contacto en chino de JS Express publica estos datos (la página en inglés es más antigua y conserva otros teléfonos y direcciones):

- **Teléfono de atención:** 400-0353-518, una línea china indicada también para consultas internacionales. JS Express no publica horarios ni aclara si funciona desde fuera de China.
- **QQ y WeChat:** QQ de empresa 4000353518 y cuenta oficial de WeChat `js-express`.
- **Quejas:** un formulario en la página inglesa «Complaint» y el mismo teléfono 4000353518.
- **Oficinas:** sede y almacén en Suzhou, otros almacenes en China y oficinas de su red en Hong Kong y en Walnut (California).

Como las reclamaciones ante JS Express las tramita el vendedor que contrató el envío, acude a la tienda para tu pedido y al transportista local para el reparto en España.

## Seguir un paquete de JS Express con Peek

[Peek](/) reconoce un número de JS Express por su forma (JSE, dos letras, diez cifras y YQ), que no usa ningún otro transportista de su catálogo, y consulta directamente el buscador de JS Express. Si solo tienes el código del repartidor local, pega ese: Peek sigue más de 4000 transportistas y, si no reconoce la empresa por el formato, puedes elegirla con «Cambiar».

Peek lee la página en inglés de JS Express y muestra el historial completo, el lugar de cada escaneo y el país de destino, que esa web omite. `到达待取` se muestra en inglés como `Arrived, awaiting collection`, igual que el aviso chino por dirección incorrecta. Peek elimina los nombres de quien recoge el paquete y los números de teléfono, y deja fuera las fotos de entrega y la referencia interna de la tienda. Como entrega prevista enseña el día estimado por JS Express y lo quita en cuanto el paquete pasa a «En reparto», puesto que el reparto corresponde a ese día. Las filas informativas no cambian el estado.

Los escaneos en China se leen en hora de Pekín y los europeos según la zona horaria deducida del lugar o del país de destino, mostrándose todos en el huso horario de tu dispositivo, del más reciente al más antiguo.

Cuando el número local encaja con seguridad en una empresa que trabaja en España (como CTT Express), Peek muestra «Entrega con …» junto con su código y la consulta también; si es un número postal internacional válido, consulta a Correos. Mientras ese transportista solo tenga el preaviso electrónico sin escaneo físico, verás «Anunciado a …, aún no recibido». En cuanto publique un escaneo igual de reciente o posterior al último de JS Express, Peek sigue el envío allí mostrando ambos números y conservando el historial de JS Express.

Peek consulta hasta una vez cada 10 minutos, y hasta una vez cada 2 minutos (cada 5 cuando tiene que abrir la web de JS Express en un navegador) desde las 6:00, hora local, el día en que el paquete sale «En reparto», mientras responda el propio seguimiento de JS Express (a través de servicios de seguimiento, cada 15 minutos). Si otra empresa asume la entrega, Peek sigue el ritmo de quien reparte el paquete: como mucho una vez por hora con Royal Mail y Poste Italiane, y como mucho cada 30 minutos con PostNL y USPS, también en reparto. Tras dos días sin novedades (o 12 horas si sigue en «Etiqueta creada» o «Admitido por el transportista»), pasa a comprobar cada hora. Puedes elegir avisos para «Cada escaneo», «Pasos importantes» (aduana, incidencias, intentos, «Listo para recoger», reparto, entrega y devolución) o «Solo la entrega». Peek no puede contactar con JS Express ni con la tienda por ti.

## Preguntas frecuentes sobre el seguimiento de JS Express

### ¿Qué es JS Express (急速国际)?

Es una empresa china de logística para comercio electrónico internacional operada por 急创速供应链管理（苏州）有限公司. Según su web, se registró en Hong Kong en 2010 y abrió su centro de operaciones en Suzhou en 2011.

### ¿Dónde puedo rastrear un paquete de JS Express?

En la casilla de arriba o en la página en inglés «Express Query» de js-exp.com. Su página en chino pide antes un código de imagen.

### ¿Qué significa 未获取到任何数据 en JS Express?

Significa «no se han obtenido datos»: el sistema de JS Express no reconoce ese número, bien por un error al copiarlo o bien porque aún no ha recibido el envío. Compruébalo con la tienda.

### ¿Quién entrega los paquetes de JS Express en España?

Una empresa de reparto local. En el historial de JS Express en España aparece CTT Express con su escaneo `Enviado`, y si el registro incluye un código postal internacional válido, Peek consulta a Correos. Si Peek no muestra repartidor, pregunta a la tienda.

### ¿A quién reclamo si mi paquete de JS Express no llega?

A la tienda donde compraste: fue ella quien contrató el envío con JS Express y quien puede reclamar por escrito dentro del plazo de 30 días. El teléfono 400-0353-518 de JS Express es una línea china.

:::sources
- [JS Express: 急速国际 (portada)](https://www.js-exp.com/) – empresa operadora
- [JS Express: Introduction (en inglés)](https://www.js-exp.com/EN/index.php?page=6) – Hong Kong 2010, Suzhou 2011, comercio electrónico transfronterizo
- [JS Express: 企业介绍 (presentación)](https://www.js-exp.com/index.php?page=6) – Suzhou 2011, sucursales y plataformas colaboradoras
- [JS Express: Express Query (en inglés)](https://www.js-exp.com/EN/index.php?page=19) – buscador en inglés por formulario sin enlace directo y mensajes de respuesta
- [JS Express: 快件查询 (seguimiento)](https://www.js-exp.com/index.php?page=19) – código de imagen en la página china
- [JS Express: Insurance Claims (en inglés)](https://www.js-exp.com/EN/index.php?page=29) – reclamaciones del cliente en 30 días y paquetes no entregables
- [JS Express: 投保理赔 (seguro y reclamaciones)](https://www.js-exp.com/index.php?page=29) – solicitud escrita del cliente en 30 días desde la recepción por JS Express, cuenta de cliente, devolución de pago o destrucción
- [JS Express: Complaint (en inglés)](https://www.js-exp.com/EN/index.php?page=32) – formulario de quejas y teléfono 4000353518
- [JS Express: Contact Us (en inglés)](https://www.js-exp.com/EN/index.php?page=35) – números y direcciones anteriores
- [JS Express: 联系我们 (contacto)](https://www.js-exp.com/index.php?page=35) – línea 400-0353-518 para consultas internacionales, QQ, WeChat, almacenes en China, Hong Kong y California
- [JS Express: 急速欧洲专线 (línea Europa)](https://www.js-exp.com/index.php?aid=35) – España entre los países cubiertos, modalidad DDP con IVA cobrado por adelantado, exclusión de códigos postales remotos
- [Agencia Tributaria: Envíos por un valor de hasta 150 euros](https://sede.agenciatributaria.gob.es/Sede/aduanas/comercio-electronico-pipe-envios-particulares/compras-internet-envios-particulares/envios-valor-hasta-150-euros.html) – arancel de 3 € desde el 1 de julio de 2026 y tasa de tramitación de 2 € desde el 1 de noviembre de 2026 por categoría de artículos, cobro en la plataforma o por el transportista
- [Comisión Europea: EU Customs Reform (en inglés)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – arancel de 3 € y tasa de tramitación de 2 €
- [Comisión Europea: Guidance on the 3 EUR customs duty and Union handling fee (PDF, en inglés)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – tasa fija de 2 € por tipo de producto en ventas a distancia cualquiera que sea su valor
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formato JSE…YQ, textos y fases de los escaneos, escaneo `Enviado` de CTT Express en España, escaneos de operador postal europeo y de repartidor sin nombre en mayúsculas, filas sin movimiento, husos horarios, consulta con navegador, número del transportista local y traspaso a CTT Express o Correos
:::
