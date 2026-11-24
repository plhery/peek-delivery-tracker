---
title: Seguimiento Yunda Express: códigos, estados y 95546
description: Sigue un paquete Yunda Express (韵达快递) en China: códigos de 13 y 15 dígitos, estados chinos, devoluciones al remitente y atención 95546 en Shanghái.
slug: seguimiento-yunda
published: 2026-11-24
updated: 2026-11-24
---

**Yunda Express** (*Shanghai Yunda Freight Co., Ltd.*, en chino *韵达快递* / *韵达速递* / *上海韵达货运有限公司*) es una de las mayores redes nacionales de paquetería urgente de China, con sede en el distrito de Qingpu en Shanghái ([yundaex.com](https://www.yundaex.com/cn/index.php)). Yunda ofrece mensajería exprés, incluido comercio electrónico y envíos internacionales urgentes, junto con almacenaje, carga (*韵达快运*), cadena de frío y servicios de cadena de suministro. Puedes consultar una guía nacional en [web.yundaex.com/infoInquiry](https://web.yundaex.com/infoInquiry) o pegar tu código Yunda de 13 o 15 dígitos en el cuadro de arriba.

## Números de seguimiento de Yunda Express (13 y 15 dígitos)

Las guías nacionales de Yunda Express en China son secuencias numéricas puras de **13 o 15 dígitos**:

| Formato (inventado) | Qué es |
| --- | --- |
| `3148291736054` | Guía nacional de paquetería Yunda Express de 13 dígitos. |
| `437391846205819` | Guía nacional de paquetería Yunda Express de 15 dígitos (habitual en pedidos recientes de comercio electrónico). |

Los números puros de 13 y 15 dígitos coinciden con los de otros transportistas, por lo que Peek solo consulta a Yunda cuando pegas un enlace `web.yundaex.com/infoInquiry?...&homeWaybill=` o seleccionas Yunda Express manualmente. Consulta la guía de [formatos de números de seguimiento](guide:tracking-number-formats).

## Qué significan los estados de seguimiento de Yunda Express

El localizador de Yunda asigna cinco etiquetas estándar en chino a los hitos nacionales, que Peek interpreta con claridad — distinguiendo incluso cuándo un paquete regresa al remitente:

| Etiqueta Yunda (CN) | Significado | Peek muestra |
| --- | --- | --- |
| `已揽件` | Paquete recogido y escaneado por el repartidor o la sucursal de origen de Yunda. | «Admitido por el transportista» |
| `运输中` | En tránsito entre centros de clasificación y enlace de Yunda por provincias y ciudades chinas. | «En tránsito» |
| `派送中` | En reparto con el repartidor local de Yunda (o en reparto de vuelta al remitente si hubo escaneo de devolución). | «En reparto» |
| `待取件` | Depositado en un punto local de recogida, puesto Cainiao o taquilla a la espera de recogida (o a la espera del remitente en una devolución). | «Listo para recoger» |
| `已签收` | Firmado o recogido por el destinatario (o por el almacén de consolidación cuando se reenvía al extranjero). | «Entregado» |
| `已签收` tras un escaneo expreso de devolución (`退回件扫描` / firmado por el remitente) | Devuelto y firmado por el remitente original en lugar del comprador. | «Devuelto al remitente» |

## Reparto interno en China, almacenes de consolidación y devoluciones

- **Compras desde el extranjero vía almacén en China:** si compraste en Taobao, 1688 o Pinduoduo usando un agente o almacén de consolidación (como [DobroPost](carrier:dobropost) o un transitario en Cantón, Shenzhen, Dongguan o Yiwu), `已签收` («Entregado») en Yunda Express indica que el paquete llegó a tu **almacén de consolidación en China**, no a tu país. Tras recibirlo, el agente asigna un nuevo número internacional.
- **Puntos de recogida (`待取件`):** dentro de China, `待取件` significa que el paquete espera en una estación de paquetería o taquilla para que el destinatario pase a recogerlo.
- **Detección de devoluciones al remitente:** el localizador original de Yunda etiqueta con el mismo `已签收` un paquete devuelto al remitente que una entrega normal; Peek revisa el texto del escaneo para detectar indicios explícitos de devolución o firma del remitente y lo marca como «Devuelto al remitente».

## Paquete de Yunda Express detenido, mal entregado o perdido

- **Pedido en una plataforma china:** contacta primero con el vendedor en Taobao, Tmall, Pinduoduo o JD — puede pedir a la sucursal de Yunda (*网点*) que recogió el paquete que lo localice.
- **Sin escaneos tras crear la etiqueta:** los comercios suelen generar una guía Yunda de 15 dígitos antes de la entrega física; los escaneos empiezan cuando la sucursal registra `已揽件`. Consulta [cuando el seguimiento deja de actualizarse](guide:tracking-not-updating).
- **Entregado (`已签收`) pero no localizado:** comprueba si lo firmó una taquilla, la conserjería del edificio o el almacén de consolidación, y abre cuanto antes una reclamación al vendedor o al transportista si falta o está dañado. Consulta también [entregado pero no recibido](guide:delivered-not-received), [paquete perdido](guide:lost-parcel), [paquete dañado](guide:damaged-parcel) y [devuelto al remitente](guide:returned-to-sender).

## Atención al cliente de Yunda Express y contactos en Shanghái

Datos oficiales publicados en [Yunda Contact Us](https://www.yundaex.com/cn/about_contact.php) y [yundaex.com](https://www.yundaex.com/cn/index.php):

- **Línea nacional de atención al cliente (China):** **`95546`**.
- **Reclamaciones y seguimiento online:** [yundaex.com/cn/about_contact.php](https://www.yundaex.com/cn/about_contact.php) y [web.yundaex.com/infoInquiry](https://web.yundaex.com/infoInquiry).
- **Fax:** `021-39297127` (línea de prensa `021-39291355`).
- **Sede central:** *Shanghai Yunda Freight Co., Ltd.* (*上海韵达货运有限公司*), No. 6679 Yinggang East Road, Qingpu District, Shanghái `201700`, China (*上海市青浦区盈港东路6679号*).

> Desconfía de SMS falsos o enlaces de WeChat que te pidan pagar una tasa de nueva entrega o introducir datos bancarios por un paquete de Yunda. Comprueba cualquier incidencia desde la app de tu tienda o en la línea oficial `95546`. Consulta [mensajes SMS falsos de entrega](guide:delivery-scam-texts).

## Seguir un paquete de Yunda Express con Peek

Peek no pregunta a Yunda por un número suelto de 13 o 15 dígitos (`3148291736054` o `437391846205819`, inventados): para 13 dígitos consulta entre otros a [iMile](carrier:imile) y [FAN Courier](carrier:fan-courier), y para 15 dígitos a [BRT](carrier:brt). Pulsa «Cambiar» al añadirlo o «Cambiar transportista» en la ficha del paquete, donde Yunda Express aparece bajo «Encaja con este número» (buscar «Yunda» o «uda International» también funciona), o pega un enlace `web.yundaex.com/infoInquiry?...&homeWaybill=` en [Peek](/), que lo reconoce como de Yunda.

Peek lee el localizador de Yunda y muestra la etapa («Admitido por el transportista», «En tránsito», «En reparto», «Listo para recoger», «Devuelto al remitente», «Entregado»), el texto del escaneo en inglés, la ciudad o distrito nombrado al principio del escaneo y la hora de entrega. Yunda da sus horas en horario de Pekín y Peek las muestra en el reloj de tu dispositivo. En una devolución, los escaneos indican que vuelve hacia el remitente. Mientras viaja, tu propio mapa puede terminar el trayecto en la localidad del destinatario; la dirección exacta no aparece, y los enlaces compartidos no muestran ninguna de las dos. Peek omite nombres y teléfonos de repartidores, direcciones de puntos de recogida, imágenes de firmas y pesos sin etiqueta. El localizador de Yunda no facilita día estimado de entrega.

Mientras tienes el paquete abierto en Peek, o el día de entrega con las notificaciones activadas, Peek consulta hasta cada 10 minutos; el día en que Yunda muestra `派送中`, cada 2 minutos desde las 6:00 hora de Pekín mientras responda el localizador de Yunda. El resto del tiempo Peek comprueba cada hora. Tras 12 horas con solo «Etiqueta creada» o «Admitido por el transportista», o dos días sin novedades, consulta cada hora sea cual sea tu modo de seguimiento, y cada 6 horas tras siete días en «Etiqueta creada». Cuando el localizador de Yunda no responde y Peek sigue el paquete únicamente a través de servicios de seguimiento, consulta cada hora, cada 3 horas tras un día tranquilo y hasta cada 15 minutos el día en que el paquete está en reparto, mientras tengas el paquete abierto o las notificaciones activadas.

Cuando el escaneo más reciente tiene cuatro días y el paquete sigue en «Etiqueta creada», «Admitido por el transportista» o «En tránsito», Peek lo marca con el día de ese escaneo, como «Sin novedades desde el lun 12 oct». La página del paquete muestra entonces «Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a Yunda Express.», junto con un enlace «Pide ayuda a Yunda Express» hacia la página de contacto de Yunda. Tras tres semanas sin nuevos escaneos, indica «Nada nuevo desde hace más de tres semanas. Si el paquete aún no ha llegado, contacta con el vendedor. Peek sigue preguntando a Yunda Express.»

Puedes elegir alertas para «Cada escaneo», «Pasos importantes» o «Solo la entrega». Peek supervisa solo las actualizaciones de seguimiento y no puede pedir nueva entrega, obtener códigos de taquilla ni contactar con Yunda Express por ti.

## Preguntas sobre el seguimiento de Yunda Express

### ¿Qué aspecto tiene un número de seguimiento de Yunda Express?

Las guías nacionales de Yunda Express tienen 13 o 15 dígitos numéricos (por ejemplo `3148291736054` o `437391846205819`, inventados).

### ¿Qué significan `已揽件`, `派送中`, `待取件` y `已签收`?

`已揽件` significa recogido, `派送中` en reparto, `待取件` listo para recoger en punto de entrega o taquilla y `已签收` entregado (salvo que vaya precedido de un escaneo de devolución al remitente).

### ¿Por qué dice mi paquete de Yunda «Entregado» si estoy fuera de China?

Si compraste a través de un agente o almacén de consolidación en China, Yunda Express cubre solo el tramo nacional hasta ese almacén. Espera a que tu agente registre el paquete y te facilite un número internacional.

### ¿Cómo contacto con el servicio de atención al cliente de Yunda Express?

Llama a la línea nacional china **`95546`** o utiliza la página oficial de contacto y reclamaciones en [yundaex.com/cn/about_contact.php](https://www.yundaex.com/cn/about_contact.php).

:::sources
- [Yunda Express: Official Website](https://www.yundaex.com/cn/index.php) – *Shanghai Yunda Freight Co., Ltd.* (*上海韵达货运有限公司*) servicios exprés nacionales, carga, cadena de frío y almacenaje
- [Yunda Express: Contact Us](https://www.yundaex.com/cn/about_contact.php) – línea `95546`, fax `021-39297127`, sistema de reclamaciones online y sede en Qingpu Shanghái (URL de ayuda enlazada por Peek)
- [Yunda Express: Waybill Inquiry](https://web.yundaex.com/infoInquiry) – consulta pública oficial de seguimiento nacional
- [Universal Parcel Scraper: yunda](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/yunda) – regla de guías de 13/15 dígitos, regla de enlace `web.yundaex.com`, mapa de 5 etiquetas chinas, detección de devoluciones, extracción de ciudad y horarios de Pekín
:::
