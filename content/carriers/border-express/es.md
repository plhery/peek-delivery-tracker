---
title: Seguimiento Border Express (BEX): números y estados
description: Rastrea un paquete de Border Express (BEX) en Australia: números de envío y códigos de barras de etiqueta, qué significa cada estado y cómo lo sigue Peek.
slug: seguimiento-border-express
published: 2026-11-22
updated: 2026-11-22
---

Border Express, o BEX, es un transportista que el catálogo de Peek lista únicamente en Australia. Si una tienda online envió tu pedido con Border Express, el número que debes seguir es su número de envío (*consignment number*): pégalo en la casilla de arriba. La tienda lo indica en su correo de envío o en tu página de pedido. El rastreador propio de Border Express es Quick Track en BEX Online (`bexonline.borderexpress.com.au`). Como Peek no pudo consultar el sitio web de Border Express para esta página, lo que sigue recoge lo que el código de Peek conoce sobre ese rastreador.

## Número de seguimiento Border Express: qué aspecto tiene

Border Express sigue una entrega por su número de envío (*consignment number*). El código de Peek conoce tres formatos. Con caracteres inventados:

| Número (inventado) | Qué es |
| --- | --- |
| `48291736` | 8 dígitos: un número de envío. |
| `KLMN482917` | 4 letras (prefijo del remitente) seguidas de dígitos: también un número de envío. Peek admite de 5 a 8 dígitos tras las letras. |
| `KLMNBX00000482917001` | 20 caracteres: el código de barras de la etiqueta de un bulto. Las 4 letras del remitente, `BX`, los dígitos del envío completados con ceros a la izquierda hasta 11 posiciones y un número de bulto de 3 dígitos. |

Un envío puede incluir varios bultos, cada uno con su propio código de barras de etiqueta. Todas las etiquetas comparten las mismas letras y dígitos iniciales, y el número de envío los agrupa: es el dato que consulta el rastreador de Border Express, que no admite un código de barras de etiqueta. Peek sí lo admite y calcula el número de envío a partir de él (ver abajo).

Peek no puede identificar un número de Border Express solo por su forma: su catálogo no tiene regla para los números de envío de Border Express, y su regla para el código de barras de etiqueta es solo una coincidencia débil. Otros transportistas usan formas iguales: con 8 dígitos Peek sugiere [Mondial Relay](carrier:mondial-relay), Colisweb y [Heppner](carrier:heppner), todos en Europa, además de TForce Freight (transporte de carga norteamericano) cuando el último dígito pasa su control. Cuatro letras con 8 dígitos encajan también con los números de 12 caracteres de Colis Privé. Si la tienda indicó Border Express, elige Border Express manualmente (abajo). Los espacios y minúsculas no importan al pegarlo. ¿No tienes número? Consulta [cómo encontrar tu número de seguimiento](guide:find-tracking-number) y [formatos de números de seguimiento](guide:tracking-number-formats).

## Estados de seguimiento de Border Express explicados

El rastreador de Border Express muestra una barra de progreso y una lista de pasos, del más reciente al más antiguo, cada uno con localidad y estado. Estos son los pasos que el código de Peek conoce, en orden habitual:

| Border Express dice | Qué significa para ti | Peek muestra |
| --- | --- | --- |
| `Shipment Data Received` | El remitente envió los datos del paquete a Border Express. Border Express aún no tiene el paquete. La barra de progreso muestra `Data Received`. | «Etiqueta creada» |
| `Picked Up` | Border Express recogió el paquete al remitente. El rastreador suele listar este paso sin hora, y Peek omite los pasos sin hora. | «Admitido por el transportista», solo si el paso tiene hora |
| `In Transit` | En camino por la red de Border Express. | «En tránsito» |
| `On Board For Delivery` | En el vehículo de reparto. La barra de progreso muestra `On Board`. | «En reparto» |
| `Partially Delivered` | Algunos bultos del envío están entregados; el resto sigue en camino. | «En tránsito» |
| `Partially Delivered - Final` | La entrega terminó: el envío se cierra con los bultos llegados. Si falta alguno, consulta al remitente. | «Entregado» |
| `Delivered` | Entregado. | «Entregado» |

Peek mantiene los pasos en el inglés de Border Express con su propio estado encima. Un paso que no aparezca en esta tabla se muestra tal como viene escrito, y Peek deduce su estado por las palabras cuando puede.

## Entrega de Border Express en Australia

El rastreador de Border Express no nombra ningún otro transportista para un envío, así que Peek sigue el paquete en Border Express desde el primer paso hasta la entrega, sin traspaso a otro transportista.

Esta página no detalla normas de intentos de entrega, avisos, entrega sin firma, recogida en almacén ni plazo de custodia, porque Peek no pudo consultarlas en la web de Border Express. Pregunta a la tienda remitente o a Border Express. Ver [entrega fallida](guide:missed-delivery) y [paquetes no recogidos](guide:uncollected-parcel).

## Paquete Border Express sin actualizar, perdido o entregado sin recibirlo

**Número no encontrado.** Hasta que el rastreador de Border Express liste el número, un paquete guardado bajo Border Express muestra «Esperando a Border Express» y Peek sigue comprobando. Si Border Express sigue sin conocer el número dos días después sin otra fuente que responda, Peek muestra «Tras dos días, el transportista sigue sin reconocer este número. Revísalo, elige otro transportista o elimina el paquete.» y pasa a comprobarlo una vez al día, y semanalmente pasada una semana. Comprueba el número con el correo de la tienda: 8 dígitos, o 4 letras y de 5 a 8 dígitos. Si tras unos días sigue sin salir nada, pregunta a la tienda si realmente lo envió.

**Detenido en «Etiqueta creada».** Border Express tiene los datos del paquete pero ningún escaneo fechado todavía. Su rastreador puede mostrar `Picked Up` sin hora — que Peek omite —, así que mira allí primero. Si solo indica `Shipment Data Received`, pregunta a la tienda cuándo lo entregó.

**Días sin actualizaciones.** Cuando un paquete en marcha lleva cuatro días sin un nuevo paso, Peek muestra la fecha del último escaneo («Sin novedades desde el …») y señala «Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a Border Express.», junto al enlace «Pide ayuda a Border Express» hacia su página de atención al cliente. Ver [por qué el seguimiento deja de actualizarse](guide:tracking-not-updating).

**Entrega parcial.** Revisa lo recibido frente a tu pedido. El resto del envío sigue en camino y el rastreador de Border Express muestra los escaneos de cada bulto por separado, que Peek omite.

**Entregado, pero no llegó nada.** Mira alrededor de tu dirección y pregunta a los vecinos. Una vez entregado, Peek también lee el nombre de quien firmó cuando consta en el rastreador de Border Express. Después contacta con la tienda. Más en [entregado pero no recibido](guide:delivered-not-received).

**Perdido, dañado o devuelto.** La tienda contrató a Border Express, así que es a la tienda a quien debes pedir reembolso o un envío nuevo. Ver [paquetes perdidos](guide:lost-parcel), [paquetes dañados](guide:damaged-parcel) y [devuelto al remitente](guide:returned-to-sender).

## Contacto y atención al cliente de Border Express

Como Peek no pudo consultar la web de Border Express, esta página no incluye teléfono ni correo electrónico: Peek solo ofrece contactos leídos en las páginas propias del transportista. La tienda reservó la entrega, así que acude primero a ella.

En la ficha del paquete en Peek, «Abrir la web de Border Express» abre tu número en el rastreador de Border Express. Antes de pagar cualquier importe solicitado por un SMS de paquetería, lee [estafas por SMS de entrega](guide:delivery-scam-texts).

## Seguir un paquete de Border Express con Peek

Pega el número en [Peek](/), que sigue a más de 4500 transportistas. Puede mostrar «Comprobando servicios de seguimiento…» mientras pregunta a otras fuentes. Pulsa luego «Cambiar» y elige Border Express buscando `Border Express` (`BEX` también lo encuentra, pero muestra primero a BEX Express, un transportista de Serbia). Para un código de barras de etiqueta, Border Express aparece en «Encaja con este número». En un paquete ya añadido usa «Cambiar transportista». No hace falta cuenta, código postal ni teléfono.

Si añades el número sin elegir transportista, Peek consulta solo servicios de seguimiento, nunca a Border Express, y muestra «Todavía no hay historial de seguimiento» hasta que alguno conozca el paquete. Solo si un servicio de seguimiento nombra a Border Express pasa Peek a consultar a Border Express. Elegir Border Express manualmente es la forma segura de obtener sus pasos.

Un enlace de seguimiento de Border Express también funciona e identifica a Border Express de inmediato («Transportista detectado»): `borderexpress.com.au/tracking` con `cn=`, o un enlace `bexonline.borderexpress.com.au/bxo/track`. Si eliges Border Express para otro formato, Peek indica «no emite números como este» y, tras añadirlo (salvo que responda un servicio de seguimiento), muestra «Revisa el número de seguimiento»: revisa si falta o sobra algún carácter.

**Códigos de barras de etiqueta.** El rastreador de Border Express no los admite, así que Peek obtiene el número de envío a partir del código de barras: las cuatro letras, luego los 11 dígitos tras `BX` sin los ceros iniciales (manteniendo al menos seis dígitos) y descartando los últimos 3 dígitos del bulto. La ficha del paquete muestra ese número bajo «Border Express lo sigue con el número», y «Abrir la web de Border Express» abre Quick Track con ese número de envío. Si Border Express no lo encuentra así, utiliza el número de envío del correo de la tienda.

**Lo que ves.** Peek muestra el estado, cada paso fechado con localidad y estado, el día de entrega previsto hasta la entrega, la hora de entrega, la localidad del destinatario (barrio/localidad, estado y código postal) y el nombre del firmante una vez entregado. Omite la barra de progreso, la fecha de despacho, la cantidad de bultos y sus escaneos individuales, así como la localidad del remitente; tampoco muestra peso ni comprobante de entrega. Cada paso lleva el reloj oficial de su estado, por lo que pasos en estados con distinta zona horaria aparecen en orden correcto y en tu zona horaria («Horas en tu zona horaria:»).

**Frecuencia.** Mientras tengas el paquete abierto en Peek — o el día de la entrega con las notificaciones activadas — Peek consulta directamente a Border Express hasta cada 10 minutos, y hasta cada 2 minutos a partir de las 6:00 (hora de Sídney) el día en que Border Express lo escanea en reparto, mientras su rastreador responda; fuera de ese caso, y tras dos días sin novedades (12 horas si sigue en «Etiqueta creada» o «Admitido por el transportista»), comprueba cada hora. Si el rastreador de Border Express no está disponible, Peek consulta servicios de seguimiento y muestra sus datos en sus propias palabras. Puede enviarte avisos de «Cada escaneo», «Pasos importantes» o «Solo la entrega». Peek no puede contactar con Border Express ni con la tienda por ti.

## Preguntas sobre el seguimiento de Border Express

### ¿Qué es un número de envío de Border Express?

Es el número con el que Border Express rastrea una entrega: 8 dígitos, o las 4 letras del remitente seguidas de dígitos. Todos los bultos del envío quedan bajo ese número. La tienda lo indica en su correo de envío o en la página de tu pedido.

### ¿Puedo seguir Border Express con el código de barras de la etiqueta?

En Peek sí: pega el código de barras de 20 caracteres (4 letras, `BX` y 14 dígitos) y elige Border Express. Peek extrae de él el número de envío. El rastreador de Border Express solo admite el número de envío.

### ¿Qué significa «On Board For Delivery» en Border Express?

Que el paquete está a bordo del vehículo de reparto. Peek lo muestra como «En reparto».

### ¿Por qué mi seguimiento de Border Express dice «Partially Delivered»?

Tu envío tiene varios bultos y solo se han entregado algunos; el resto sigue en camino, por lo que Peek muestra «En tránsito». `Partially Delivered - Final` es distinto: Border Express cerró el envío con los bultos llegados y Peek muestra «Entregado».

### ¿Qué significa «Shipment Data Received» en Border Express?

El remitente envió a Border Express los datos del paquete, pero Border Express aún no lo ha escaneado. Peek muestra «Etiqueta creada».

:::sources
- [Universal Parcel Scraper: Border Express](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/border-express) – nombres, Australia, números de envío y códigos de barras de etiqueta, enlaces de seguimiento, Quick Track, textos y etapas, pasos sin hora, relojes estatales, estimación y hora de entrega, localidad destinataria y firmante, campos omitidos, sin otro transportista
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formatos de otros transportistas (Mondial Relay, Colisweb, Heppner, Colis Privé), nombre de BEX Express, sin traspaso hacia ni desde Border Express
:::
