---
title: Seguimiento AnserX: códigos BTYCN...YQ, traspaso y ayuda
description: Sigue un paquete internacional AnserX (斑头雁) desde China: códigos BTYCN...YQ, eventos BTY, traspaso a mensajería local, aduanas y soporte.
slug: seguimiento-anserx
published: 2026-11-24
updated: 2026-11-24
---

AnserX (*Shenzhen AnserX International Logistics Technology Co., Ltd.*, en chino *斑头雁国际物流*), fundada en 2018 y con sede en Shenzhen, es una empresa china de logística de comercio electrónico transfronterizo que opera líneas dedicadas de paquetería pequeña desde China hacia Estados Unidos, Europa (incluidos Reino Unido, Francia y Alemania), Japón, Canadá, Australia, el Sudeste Asiático, México, Nigeria y Arabia Saudí, además de transporte aéreo y marítimo FBA (0–30 kg, lado máximo 1,2 m) y almacenes en el extranjero. Puedes seguir un envío en [el portal de seguimiento OMS de AnserX](https://oms.anserx.com/tracking) o pegar tu número de envío en el recuadro de arriba.

## Números de seguimiento de AnserX: formato BTYCN...YQ

Busca el número de seguimiento en los detalles de tu pedido de la plataforma o en el correo de confirmación de envío. Los códigos de AnserX tienen 17 caracteres: `BTYCN` seguido de 10 dígitos y acabado en `YQ`:

| Formato (inventado) | Qué es |
| --- | --- |
| `BTYCN4829173640YQ` | Número estándar de paquete transfronterizo de AnserX: `BTYCN` + 10 dígitos + `YQ`. |

Cuando tu envío llega al país de destino y supera la aduana, AnserX lo entrega a un operador postal o de mensajería local (como [USPS](carrier:usps) en Estados Unidos o un socio local de última milla en Europa o México) y vincula el número de seguimiento local de ese socio mientras reenvía sus escaneos bajo tu código `BTYCN...YQ`. Para otros prefijos transfronterizos chinos, consulta [formatos de números de seguimiento](guide:tracking-number-formats).

## Qué significan los estados y códigos de seguimiento de AnserX

AnserX combina códigos de evento `BTY_` con descripciones en inglés y chino a lo largo de la clasificación en origen, el transporte aéreo, el despacho aduanero y el reparto final:

| Código y texto de AnserX | Qué significa para ti | Peek muestra |
| --- | --- | --- |
| `BTY_ORDER_CREATED` (`Shipment information received`) | El vendedor envió los datos electrónicos del pedido a AnserX antes de que el paquete físico llegara al centro. | «Etiqueta creada» |
| `BTY_WAREHOUSE_INBOUND` (`Shipment inbound in sorting center`) | Recibido y escaneado en el centro de clasificación de AnserX en China. | «Admitido por el transportista» |
| `BTY_WAREHOUSE_OUTBOUND`, `BTY_LINE_HANDOVER` (`Hand over to airline` / `Departed from origin port`), `BTY_LINE_ARRIVED` (`Arrived at destination port`) | Salió del centro de clasificación en China, fue entregado a la aerolínea y aterrizó en el aeropuerto de destino. | «En tránsito» |
| `BTY_EXCC_COMPLETED` (`Relased from export customs` / `目的地进口清关完成`) | Despacho de aduanas completado (nota: el texto en inglés de AnserX dice «export», mientras que el texto chino confirma que finalizó el despacho de importación en destino); el paquete continúa el tránsito local. | «En tránsito» |
| `BTY_LASTMILE_TRANSIT` (`Delivery to local courier company` / `arrival at transportation partner facilities`) | Entregado a la mensajería local del país de destino y en camino hacia la oficina de reparto local. | «En tránsito» |
| `BTY_LASTMILE_DELIVERING` (`Parcel currently being delivered` / `Out for Delivery`) | En reparto hoy con el mensajero local. | «En reparto» |
| `BTY_LASTMILE_DELIVERED` (`Parcel has been delivered` / `Delivered`) | Entregado al destinatario por el socio de destino. | «Entregado» |

## Cómo funciona la entrega internacional de AnserX

En [anserx.com](https://www.anserx.com/) y [Sobre AnserX](https://www.anserx.com/about.html), AnserX describe su plataforma inteligente de capacidad y enrutamiento que conecta la recogida en China, centros de exportación, transporte aéreo, despacho de aduanas y transportistas locales de última milla (incluidos canales dedicados para paquetería pequeña con productos electrónicos con batería).

**Entrega final y aduanas.** AnserX es un consolidador de transporte aéreo internacional, no una flota de reparto a domicilio fuera de China. Una vez despachado en la aduana de destino (`BTY_EXCC_COMPLETED`), el servicio postal o mensajero local de tu país controla los intentos de entrega, depósitos en lugar seguro, custodia en puntos de recogida y devoluciones. El IVA de importación, los aranceles o los gastos de gestión siguen las normas aduaneras de tu país salvo que el vendedor los cobrara al finalizar la compra; consulta [paquetes retenidos en aduanas](guide:customs).

## Paquete de AnserX retenido, sin movimientos o no entregado

Los clientes de AnserX son los vendedores que envían con la empresa, así que contacta primero con tu vendedor ante retrasos, errores de dirección, reembolsos o reclamaciones por extravío.

- **Pausas durante el vuelo o la aduana:** el seguimiento suele quedar sin novedades varios días entre `BTY_LINE_HANDOVER` (salida del puerto de origen), `BTY_LINE_ARRIVED` y `BTY_EXCC_COMPLETED` mientras aterrizan los vuelos y la aduana tramita los envíos consolidados. Consulta [por qué el seguimiento deja de actualizarse](guide:tracking-not-updating).
- **Revisa el número local tras el traspaso:** cuando AnserX vincula el número de seguimiento del transportista de destino, consultarlo directamente en la web de esa empresa suele mostrar mejores detalles de franja de entrega o recogida.
- **Entregado, dañado o desaparecido:** comprueba primero con tu familia, vecinos, portería y transportista local, y después avisa enseguida al vendedor online. Consulta [entregado pero no recibido](guide:delivered-not-received), [paquete dañado](guide:damaged-parcel), [paquete perdido](guide:lost-parcel) y [devuelto al remitente](guide:returned-to-sender).

## Atención al cliente y contacto de AnserX

Datos oficiales publicados en [Contacto de AnserX](https://www.anserx.com/contact-us.html) y [anserx.com](https://www.anserx.com/):

- **Líneas de atención 24 horas (China):** `0755-2891 8474` y `0755-2868-5256` (atención online `09:00–24:00` hora de China).
- **Correo de atención al cliente:** `bantouyankf@anserx.com`.
- **Sede central:** Shenzhen AnserX International Logistics Technology Co., Ltd., Room 804, Block C, Building 3, Tian'an Cloud Park, Xuegang North Road, Bantian Street, Longgang District, Shenzhen, China.

> Desconfía de SMS o mensajes de WhatsApp no solicitados que pidan un pago con tarjeta online para «liberar de aduanas» o «corregir tu dirección» en un paquete procedente de China. Verifica siempre cualquier tasa real de importación en la página de tu pedido o en el correo nacional. Consulta [estafas por SMS de envíos](guide:delivery-scam-texts).

## Seguir un paquete de AnserX con Peek

[Peek](/) reconoce automáticamente `BTYCN` + 10 dígitos + `YQ` (`BTYCN4829173640YQ`, dígitos inventados) como AnserX con alta confianza, y también detecta enlaces pegados de `oms.anserx.com/tracking?nos=`. Si alguna vez necesitas elegir AnserX a mano, pulsa «Cambiar» al añadirlo o «Cambiar transportista» en la pantalla del paquete (buscar «斑头雁» o «AnserX International Logistics» también lo encuentra).

Peek lee directamente el rastreador OMS de AnserX y muestra la fase («Etiqueta creada», «Admitido por el transportista», «En tránsito», «En reparto», «Entregado»), el texto en inglés del escaneo (o en chino si no tiene traducción inglesa), la ciudad o centro sin código postal y el país de destino. Los códigos `BTY_` fijan la fase; ninguna app de Peek los muestra. AnserX fecha sus propios escaneos en hora de Pekín; los escaneos reenviados del transportista local se fechan en la zona horaria de su lugar (la de Los Ángeles para un escaneo en Los Ángeles, la de Ciudad de México para Puebla), y Peek los muestra todos en la hora de tu dispositivo. Cuando AnserX indica el número del transportista que entrega y Peek reconoce a ese transportista, Peek comprueba que tiene el paquete: el envío muestra entonces «Entrega con» seguido del nombre del transportista y lista ambos números. A un código de barras de USPS se le quita el prefijo postal de enrutamiento. Peek omite el código de cuenta del cliente y la referencia de pedido; el rastreador de AnserX no proporciona fecha estimada de entrega, peso, nombre del servicio, hora de entrega independiente ni foto de prueba de entrega.

Mientras tengas el paquete abierto en Peek, o el día de entrega con las notificaciones activadas, Peek comprueba hasta cada 10 minutos; el día en que AnserX muestra `BTY_LASTMILE_DELIVERING`, cada 2 minutos desde las 6:00 según la hora local del último escaneo, mientras responda el rastreador de AnserX. En el resto de casos comprueba cada hora. Tras 12 horas solo en «Etiqueta creada» o «Admitido por el transportista», o tras dos días sin novedades, pasa a comprobar cada hora sea cual sea tu seguimiento, y cada 6 horas tras siete días en «Etiqueta creada». Cuando el rastreador de AnserX no responde y Peek sigue el paquete únicamente a través de servicios de seguimiento, comprueba cada hora, cada 3 horas tras un día tranquilo y hasta cada 15 minutos el día de reparto mientras tengas el paquete abierto o las notificaciones activadas.

Cuando el último escaneo tiene cuatro días y el paquete sigue en «Etiqueta creada», «Admitido por el transportista» o «En tránsito», Peek lo señala con el día de ese escaneo, por ejemplo «Sin novedades desde el lun 12 oct». La página del paquete muestra entonces «Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a AnserX.», con un enlace «Pide ayuda a AnserX» hacia la página de contacto de AnserX. Tras tres semanas sin ningún escaneo nuevo, dice «Nada nuevo desde hace más de tres semanas. Si el paquete aún no ha llegado, contacta con el vendedor. Peek sigue preguntando a AnserX.»

Puedes elegir avisos para «Cada escaneo», «Pasos importantes» o «Solo la entrega». Peek solo supervisa las actualizaciones de seguimiento y no puede despachar aduanas ni contactar con AnserX o tu mensajero local por ti.

## Preguntas frecuentes sobre el seguimiento de AnserX

### ¿Qué es AnserX (*斑头雁*)?

AnserX (*Shenzhen AnserX International Logistics Technology Co., Ltd.*, fundada en 2018 en Shenzhen) es un proveedor chino de logística de comercio electrónico transfronterizo que opera líneas aéreas dedicadas de pequeños paquetes, envíos FBA y almacenes en el extranjero hacia Estados Unidos, Europa, Canadá, Australia, Japón, México y otros países.

### ¿Qué significa un número de seguimiento `BTYCN...YQ`?

`BTYCN` + 10 dígitos + `YQ` (por ejemplo `BTYCN4829173640YQ`, dígitos inventados) es el código de envío transfronterizo de 17 caracteres de AnserX.

### ¿Por qué AnserX muestra `Relased from export customs` tras llegar a mi país?

Es una peculiaridad de traducción en el texto en inglés de AnserX para el código `BTY_EXCC_COMPLETED`: la descripción correspondiente en chino (`目的地进口清关完成`) confirma que el despacho de importación en destino ha concluido y el paquete pasa a tu transportista local.

### ¿Cómo contacto con atención al cliente de AnserX?

Pregunta primero a tu tienda online o plataforma para incidencias de pedido y reembolsos. Para consultas al transportista, AnserX publica `bantouyankf@anserx.com` y las líneas 24 horas de Shenzhen `0755-2891 8474` y `0755-2868-5256` en [anserx.com/contact-us.html](https://www.anserx.com/contact-us.html).

:::sources
- [AnserX: sitio web oficial](https://www.anserx.com/) – líneas dedicadas de pequeños paquetes, límites FBA 0–30 kg / 1,2 m, almacenes en el extranjero y sede de Shenzhen
- [AnserX: sobre nosotros](https://www.anserx.com/about.html) – fundada en 2018, rutas globales (EE. UU., Europa, Japón, Sudeste Asiático, Rusia, Australia) y plataforma de enrutamiento inteligente
- [AnserX: contacto](https://www.anserx.com/contact-us.html) – `bantouyankf@anserx.com`, `0755-2891 8474`, `0755-2868-5256`, servicio online `09:00–24:00` y dirección en Shenzhen (URL de ayuda enlazada por Peek)
- [AnserX: portal de seguimiento OMS](https://oms.anserx.com/tracking) – consulta pública de envíos
- [Universal Parcel Scraper: anserx](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/anserx) – detección `BTYCN...YQ`, regla de enlace `oms.anserx.com/tracking?nos=`, códigos `BTY_*`, hora de Pekín para sus escaneos y zona horaria local para los reenviados, extracción de `deliveryNo` local y URL de ayuda
:::
