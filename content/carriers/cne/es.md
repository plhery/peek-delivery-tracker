---
title: Seguimiento CNE Express: números 3A5V, estados y entrega
description: Rastrea un paquete de CNE Express (递一物流) desde China continental: códigos 3A5V, significado de sus estados, reparto local, aduanas y uso de Peek.
slug: seguimiento-cne-express
published: 2026-11-21
updated: 2026-11-21
---

CNE Express (`递一物流`, `CNE EXPRESS CO LTD` / `递一国际物流有限公司`) es una empresa de logística transfronteriza fundada en 2003 en Shanghái (China continental), con rutas hacia más de 100 países y regiones. Transporta pedidos de vendedores en Amazon, eBay, Walmart, Wish, DHgate, TikTok, Temu, Shopify o SHEIN desde China continental en avión y los entrega a un operador colaborador en el país de destino. Consulta tu código en [el rastreador en inglés de CNE](https://www.cne.com/en/track) o pégalo en el recuadro superior; en el catálogo de Peek también aparece buscando `CNE` o `递一物流`.

## Número de seguimiento de CNE Express: cómo es

CNE no publica un formato oficial de numeración. Este es el esquema que Peek identifica directamente como propio de CNE Express, con cifras inventadas:

| Número (inventado) | Qué es |
| --- | --- |
| `3A5V482910573` | `3A5V` y 9 dígitos: un código de CNE Express. |
| `3A5V48291057` | Solo 8 dígitos: no tiene el formato `3A5V` de CNE. Peek no asigna CNE y lo comprueba en Colis Privé (cuyo patrón de 12 caracteres coincide) y en servicios de seguimiento. Revisa si falta una cifra. |
| `LX482910576CN` | Dos letras, 9 dígitos y `CN`: un envío postal de [China Post](carrier:china-post), cuyos servicios (*International E-packet*, *China Post Packet*) también comercializa CNE. |

Minúsculas, espacios y guiones no importan en Peek. El buscador de CNE admite varios números a la vez separados por espacios o comas.

**Dónde encontrarlo.** En el correo de envío de la tienda o en la página de tu pedido: mira [cómo encontrar el número de seguimiento](guide:find-tracking-number), [pedidos de Temu](guide:temu-orders) y [pedidos de SHEIN](guide:shein-orders). Bajo `Shipment Information`, el rastreador de CNE puede mostrar además el `Order number` del vendedor y el `Last mile number` de la empresa de reparto junto con su web y teléfono.

## Estados de seguimiento de CNE Express explicados

El rastreador de CNE muestra un estado general encima de las líneas de cada envío:

| Estado de CNE | Qué significa para ti | Peek muestra |
| --- | --- | --- |
| `Shipping` | En camino. | el estado de la línea más reciente |
| `Customs inspection` | Retenido por aduanas (`扣关` en la página china de CNE). | «En aduanas» |
| `Delivered` | Entregado. | «Entregado» |
| `Invalid address`, `Lost`, `Exception`, `Destroyed` | Dirección incorrecta, paquete perdido o destruido u otra incidencia. | «Incidencia» |
| `Returned` | Devuelto al vendedor (`退件` en chino). | «Devuelto al remitente» |
| `Not delivered` | En la web china figura `未发送` («no enviado»): el paquete aún no ha salido. | el estado de la línea más reciente |

También pueden verse `Time out` y `Unknown`, en cuyo caso Peek se guía por las líneas del historial. Cada línea incluye hora, texto y lugar:

| CNE indica | Qué significa para tu envío | Peek muestra |
| --- | --- | --- |
| `Parcel Infomation Received` | El vendedor transmitió los datos del paquete (grafía literal de CNE); CNE aún no lo tiene. | «Etiqueta creada» |
| `Picked Up by Courier` | CNE recogió el paquete al vendedor en China continental. | «Admitido por el transportista» |
| `Arrived at CNE Facility`, `Inbound scan`, `Shipment Processed`, `Shipment Loaded` | Entrada, clasificación y carga en una instalación de CNE. | «En tránsito» |
| `Departed CNE Facility`, `Departure Scan`, `Depart From Local Facility` | Salida de un centro logístico. | «En tránsito» |
| `Arrived Export Airport`, `Released From Export Customs` | En el aeropuerto de salida en China continental y despacho de exportación superado. | «En tránsito» |
| `Shipment Departed From Original Country` | El vuelo ha salido de China continental. | «En tránsito» |
| `Arrived At Transit Airport`, `Shipment Depart From Transit Airport` | Escala en un aeropuerto de tránsito. | «En tránsito» |
| `Arrived at Import Airport`, `Arrived at destination` | Llegada al país de destino: aún faltan clasificación y aduana de importación (no está todavía en tu oficina de reparto). | «En tránsito» |
| `The shipment is picked up by customs broker` | Recogido por un agente de aduanas para el despacho de importación. | «En aduanas» |
| `Released From Import Customs`, `Pickup from airport` | Despachado en aduanas de importación y retirado del aeropuerto. | «En tránsito» |
| `Handled by local carrier` | Entregado por CNE a la red de reparto local; la admisión propia de ese operador viene después. | «En tránsito» |
| `Arrived At The Delivery Company`, `Your shipment has arrived at sorting center`, `Your shipment is in process at sorting center`, `Parcel Unloaded From Container` | En manos de la empresa de reparto o en su centro de clasificación. | «En tránsito» |
| `Your shipment has been processed at sorting center and ready for shipping`, `Your shipment has departed from the transit origin`, `Your shipment has arrived at the transit destination` | Clasificado en el país de destino y enviado a la delegación local previa al reparto. | «En tránsito» |
| `The shipment has been processed in the parcel center of origin.` | Clasificación de DHL en Alemania retransmitida por CNE tras la cesión. | «En tránsito» |
| `Pendiente de recepción en CTT Express`, `Enviado`, `En reparto`, `Entregado` | Estados de [CTT Express](carrier:ctt-express) en España retransmitidos con explicación tras una coma: prerregistro («Etiqueta creada», sin retroceder si ya está en marcha), envío («En tránsito»), reparto («En reparto») y entrega («Entregado»). | según el paso |
| `On Board with Courier`, `Your shipment is out for delivery` | En la furgoneta del repartidor para entrega hoy. | «En reparto» |
| `Your shipment delivery attempt failed [Recipient not at home]` | El repartidor no encontró a nadie en casa; se volverá a intentar. | «Intento de entrega», o «Incidencia» mientras el resumen marque `Exception` |
| `Sorting failed []` | Fallo de clasificación (los corchetes vacíos son de CNE). | «Incidencia» |
| `It was returned to customer's address by CNE` | Devuelto por CNE a la dirección del vendedor remitente. | «Devuelto al remitente» |

Tras el traspaso al operador local, sus eventos suelen venir como «etiqueta；explicación» (o separados por coma en CTT Express): Peek lee únicamente la etiqueta inicial, mantiene en «En tránsito» una admisión local codificada como `Shipping` y usa el código de CNE cuando no reconoce el texto. Un estado resumen final o de bloqueo (`Delivered`, `Returned`, `Customs inspection`, `Invalid address`, `Lost`, `Exception`, `Destroyed`) prevalece sobre la última línea.

## Quién reparte los paquetes de CNE Express en España y otros destinos

CNE opera líneas propias hacia Europa, Estados Unidos, Canadá, Australia o Brasil y recurre a socios locales para la entrega: su página de socios incluye a [Correos](carrier:correos-spain), [DHL](carrier:dhl), [GLS](carrier:gls-de), [DPD](carrier:dpd-de), [La Poste](carrier:la-poste), [Poste Italiane](carrier:poste-italiane), [Royal Mail](carrier:royal-mail), [Evri](carrier:evri), Austrian Post, POST Luxembourg y [USPS](carrier:usps), además de las entregas que CNE retransmite de [CTT Express](carrier:ctt-express) en España.

Peek traduce directamente tres códigos de proveedores finales de CNE cuando incluyen número de transferencia: [DHL](carrier:dhl) (`DE DHL`), [Royal Mail](carrier:royal-mail) y [USPS](carrier:usps), mostrando «Entrega con …» (o «Anunciado a …, aún no recibido» mientras ese operador no tenga escaneos propios). Con otro código de proveedor y su número de transferencia (como `US SPX`, no asignado a un nombre fijo), ante un código postal internacional válido Peek consulta al operador postal correspondiente —como [Correos](carrier:correos-spain) hacia **España**; en envíos hacia **México**, al operador postal emisor del código— o al único transportista señalado por el formato de ese número; sin proveedor nombrado, el segundo código puede ser una referencia comercial y no se sigue. Los intentos de entrega y puntos de recogida dependen del repartidor final ([entrega fallida](guide:missed-delivery)).

**Aduanas en España y la Unión Europea.** En la **Unión Europea**, los bienes comprados online fuera de la UE en paquetes con valor intrínseco de hasta 150 € pagan desde el 1 de julio de 2026 un arancel aduanero de 3 € por artículo (*per item*), y desde el 1 de noviembre de 2026 se suma una tasa de tramitación de la Unión (*Union handling fee*) de 2 € por artículo sin importar el valor (misma clasificación arancelaria, descripción y, cuando proceda, origen; por encima de 150 € rigen los aranceles ordinarios). El 22 de octubre de 2026, CNE comunicó a sus vendedores que desde las `00:00` (hora de Pekín) del 26 de octubre de 2026 cobra por adelantado al vendedor esos 2 € por paquete y código HS en sus rutas a los 27 países de la UE, además de los 3 € cobrados del mismo modo desde julio de 2026, despachando según lo declarado por el remitente. El aviso no menciona el IVA de importación en **España peninsular y Baleares** (exigible salvo que ya se cobrara en la compra mediante IOSS; según la Comisión Europea, **Canarias** pertenece al territorio aduanero de la UE pero queda fuera del ámbito de IVA comunitario, y **Ceuta** y **Melilla** quedan fuera de ambos) ni posibles cargos del transportista local; fuera de la UE se aplican las normas aduaneras de cada país ([paquete retenido en aduanas](guide:customs)). Los avisos de retraso por país se publican en chino en `新闻公告`.

## Paquete de CNE Express parado, perdido o devuelto

Los clientes de CNE son los vendedores que contratan el envío: ante retrasos, pérdidas, daños o devoluciones, contacta primero con la tienda o plataforma.

**«Esperando a CNE Express».** Mientras CNE responda que el pedido no existe (`订单不存在`), Peek muestra «Esperando a CNE Express»; si tras dos días CNE sigue sin reconocer el código y ningún servicio de seguimiento responde, Peek avisa: «Tras dos días, el transportista sigue sin reconocer este número. Revísalo, elige otro transportista o elimina el paquete.» Con `Not delivered` (`未发送`) o `Parcel Infomation Received`, consulta al vendedor si no avanza en unos días.

**Cuatro días sin novedades.** Si un envío en tránsito acumula cuatro días sin nuevas líneas, Peek indica «Sin novedades desde hace cuatro días» junto con la nota sobre vuelos largos, aduanas y fines de semana, y añade el enlace «Pide ayuda a CNE Express» hacia la página de contacto de CNE. Mira [por qué no se actualiza el seguimiento](guide:tracking-not-updating) y [seguimiento de paquetes desde China](guide:tracking-from-china).

**En aduanas, con incidencia, devuelto o marcado entregado sin recibirlo.** Ante `Customs inspection` (`扣关`), `The shipment is picked up by customs broker`, `Lost`, `Destroyed`, `Invalid address` o `Returned`, reclama al vendedor ([paquete perdido](guide:lost-parcel), [devuelto al remitente](guide:returned-to-sender)). En el rastreador de CNE, una línea con `View POD` enseña el justificante de entrega tras introducir nombre o código postal del destinatario ([entregado pero no recibido](guide:delivered-not-received)).

## Atención al cliente y contacto de CNE Express

En la página [Contact us de CNE](https://www.cne.com/en/about/contact):

- **Teléfono nacional**: `400-021-5600` (*National Service Hotline*, línea de servicio en China continental).
- **Oficina central de Shanghái**: `021-5109 6677`, `15/F, Block G, Lane 1777, Hualong Road, QingPu District, Shanghai` (China continental).
- **Horario**: `08:30-18:00 Mon-Sun` sin huso especificado (sus centros de clasificación de Jiaxing y Dongguan en China continental operan `7 x 24 Hours`, dato que también figura en el pie de página).
- **Formulario web**: *Consulting form* en esa misma página eligiendo `Complaint`, con teléfono, correo, nombre, código de seguimiento y mensaje (CNE no publica correo de atención al destinatario).

**Cuidado con los SMS falsos.** El dominio oficial de CNE Express es `www.cne.com` (`cne.com`). Antes de abrir enlaces recibidos por SMS o correo, revisa [mensajes falsos de entrega](guide:delivery-scam-texts).

## Seguir un paquete de CNE Express con Peek

[Peek](/) reconoce al instante un código `3A5V` («Transportista detectado»), y cualquier enlace de `cne.com` con `?no=` archiva el envío bajo CNE Express (tomando el primero si hay varios). Para otro código alfanumérico de CNE de 6 a 40 caracteres, si Peek muestra «Comprobando servicios de seguimiento…» o no señala a CNE, pulsa «Cambiar» y elige CNE Express en «Todos los transportistas» (o usa «Cambiar transportista» después); fuera de 6 a 40 letras y cifras, Peek indica que «no emite números como este».

Peek lee el estado, cada escaneo con su lugar, el país de destino y el operador final con su código de transferencia. Interpreta `Arrived at destination` y `Handled by local carrier` en hora de Pekín (`UTC+8`), y los estados españoles retransmitidos de [CTT Express](carrier:ctt-express) en `UTC+02:00` (o `UTC+01:00` en los pasos finales de [CTT](carrier:ctt) hacia Portugal), convirtiéndolos a tu huso («Horas en tu zona horaria: …»), mientras que las demás horas sin huso se conservan tal como las escribe CNE; descarta el número de pedido del comercio, la fecha resumen de entrega sin huso y el justificante `View POD` (CNE no da fecha prevista ni peso). «Abrir la web de CNE Express» abre tu envío en `cne.com`. Cuando el transportista final consultado muestra un escaneo al menos tan reciente como el último de CNE, Peek pasa a seguirlo allí conservando el historial previo.

Mientras tengas abierto el paquete o, el día de entrega, las notificaciones activas, Peek comprueba cambios hasta cada 10 minutos —y hasta cada 2 minutos desde las 6:00 locales el día en que pasa a «En reparto» mientras responda el buscador de CNE (tras pasar al transportista final, sigue el ritmo de este; solo por servicios de seguimiento: cada hora, cada 3 horas tras un día sin cambios y hasta cada 15 minutos el día de entrega con el paquete abierto o notificaciones activas)—; en el resto de casos y tras dos días sin novedades (12 horas en «Etiqueta creada» o «Admitido por el transportista»), revisa cada hora. Avisos: «Cada escaneo», «Pasos importantes» o «Solo la entrega». Peek no contacta con CNE ni con el vendedor por ti.

## Preguntas frecuentes sobre el seguimiento de CNE Express

### ¿Es fiable CNE Express?

Sí. `CNE EXPRESS CO LTD` (`递一国际物流有限公司`) se fundó en 2003 en Shanghái (China continental), cuenta con centro operativo en Shenzhen y centros de clasificación en Jiaxing y Dongguan capaces de procesar más de 2 millones de envíos diarios desde 2020, y trabaja para vendedores de Amazon, eBay, Walmart, TikTok, Temu y SHEIN.

### ¿Quién reparte los paquetes de CNE Express?

Un servicio postal o mensajería local elegido por CNE (como Correos, CTT Express, DHL, GLS, DPD, La Poste o Royal Mail), cuyo `Last mile number` puede aparecer en el rastreador de CNE.

### ¿Qué significa `Arrived at destination` en CNE Express?

El paquete ha llegado al país de destino, pero aún pueden faltar la clasificación local y la aduana de importación, así que todavía no está en tu delegación de reparto. CNE registra esta línea en hora de Pekín (`UTC+8`, 7 horas por delante de Madrid en invierno), mientras que otras lecturas en destino llevan hora local sin huso y pueden parecer desordenadas.

### ¿Cuánto tarda CNE Express en entregar?

CNE da a los vendedores plazos orientativos según la ruta: `3-8 business days` en *CNE Priority Express* (a EE. UU., Reino Unido, Francia y Alemania) y `5-20 business days` en *CNE Standard Express*. Guíate por la fecha estimada de tu tienda.

### ¿Cómo contacto con CNE Express?

Por el teléfono `400-021-5600` (línea de China continental), el `021-5109 6677` de Shanghái o el *Consulting form* de `cne.com/en/about/contact` marcando `Complaint`. Sobre tu pedido, pregunta primero al vendedor.

:::sources
- [CNE: Track your parcel](https://www.cne.com/en/track) – rastreador en inglés, etiquetas de estado, Shipment Information y View POD
- [CNE: 运单追踪 (seguimiento en chino)](https://www.cne.com/track) – equivalentes chinos 未发送 y 扣关
- [CNE: Home](https://www.cne.com/en) – fundación en 2003 en Shanghái, destinos, plataformas y línea 400
- [CNE: 首页 (portada en chino)](https://www.cne.com/) – 递一物流 y 递一国际物流有限公司
- [CNE: About us](https://www.cne.com/en/about/intro) – centros de clasificación de Dongguan y Jiaxing, más de 2 millones de envíos diarios desde 2020
- [CNE: Partner](https://www.cne.com/en/about/partner) – socios logísticos internacionales
- [CNE: Contact us](https://www.cne.com/en/about/contact) – línea 400, oficina de Shanghái, horario 08:30-18:00 y Consulting form (Complaint)
- [CNE: Postal Parcel](https://www.cne.com/en/solutions/economyParcel) – servicios de China Post comercializados por CNE
- [CNE: CNE Priority Express](https://www.cne.com/en/solutions/product/36024) – plazo de referencia 3-8 business days y rutas US/GB/FR/DE
- [CNE: CNE Standard Express](https://www.cne.com/en/solutions/product/36025) – plazo de referencia 5-20 business days
- [CNE: 新闻公告 (noticias y avisos)](https://www.cne.com/news/notice) – avisos de demoras por destino
- [CNE: 关于欧盟国家2026年11月税改通知 (aviso fiscal UE)](https://www.cne.com/news/article/rDe4ANa6KYOW) – cobro anticipado al vendedor de los 2 € desde el 26 de octubre de 2026 además de los 3 € por código HS y despacho según declaración del vendedor
- [Comisión Europea: Reforma aduanera de la UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – arancel de 3 € desde el 1 de julio de 2026 y tasa de 2 € desde el 1 de noviembre de 2026
- [Comisión Europea: Orientaciones sobre el arancel de 3 € y la tasa de la Unión (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 € cualquiera que sea el valor, definición de artículo y exención IOSS
- [Comisión Europea: Ámbito territorial del IVA y aduanas](https://taxation-customs.ec.europa.eu/taxation/vat/vat-directive/how-does-vat-work/territorial-scope_en) – Islas Canarias, Ceuta y Melilla
- [Universal Parcel Scraper: CNE Express](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/cne) – patrón 3A5V, enlaces ?no=, estados y códigos, proveedores finales, relojes de Pekín / CTT Express / locales y traspaso
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – detección de Colis Privé y China Post y traspaso al transportista final
:::
