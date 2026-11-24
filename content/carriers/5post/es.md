---
title: Seguimiento 5Post: códigos, recogida Pyaterochka y estados
description: Sigue un paquete 5Post (FivePost / X5) en Rusia: códigos P de Avito y números de pedido, estados rusos, recogida en Pyaterochka y contacto.
slug: seguimiento-5post
published: 2026-11-24
updated: 2026-11-24
---

5Post (*5Пост* / *FivePost*, operado por X5 Group) es una red rusa de paquetería para comercio electrónico, envíos entre particulares y logística que entrega pedidos online y paquetes de Avito Delivery en más de **25 700** taquillas (*постаматы*) y puntos de recogida en caja dentro de los supermercados **Pyaterochka** («Пятёрочка») y **Perekrestok** («Перекрёсток») en **4 950** ciudades y localidades de Rusia, respaldada por **51** centros de clasificación ([fivepost.ru](https://fivepost.ru/)). 5Post colabora con más de 1 000 tiendas online y mercados digitales — incluidos Avito Delivery y pedidos internacionales de AliExpress — y también realiza entregas a domicilio por mensajero y transporte de carga LTL/FTL.

Puedes consultar un paquete en [fivepost.ru](https://fivepost.ru/) (en *Где мой заказ?*) o pegar tu número de pedido o un enlace `fivepost.ru/tracking?id=...` en el recuadro de arriba.

## Formatos de números de seguimiento de 5Post

5Post rastrea los envíos mediante el número de pedido de la tienda o plataforma — cualquier cadena única de 3 a 40 letras y dígitos que contenga al menos un dígito:

| Formato (inventado) | Qué es |
| --- | --- |
| `P48291736051` | `P` + 11 dígitos (12 caracteres), el formato de los números de Avito Delivery. Peek consulta a [Colis Privé](carrier:colis-prive) y 5Post, primero a 5Post en Rusia. |
| `99994829173605` | 14 dígitos, el número de pedido de una tienda online. Peek nunca propone 5Post para este formato y consulta a las empresas de DPD, Chronopost, SEUR, BRT y otras: elige 5Post manualmente o pega el enlace de 5Post. |

Como cada tienda elige sus propios números de pedido, dos pedidos pueden compartir el mismo. En ese caso, la web de 5Post pide cifras del teléfono del destinatario y Peek no puede distinguirlos. Un enlace `fivepost.ru/tracking?id=...` asigna el paquete a 5Post de inmediato. Para otros formatos, consulta la [guía de formatos de números de seguimiento](guide:tracking-number-formats).

## Qué significan los estados de seguimiento de 5Post

El seguimiento de 5Post combina códigos técnicos de estado con descripciones en ruso, indicando el almacén regional de clasificación (`РЦ ...`) en los escaneos de centro logístico y salida.

| Código 5Post y texto en ruso | Qué significa |
| --- | --- |
| `INFORMATION_RECEIVED` — `Ожидается передача заказа в 5post` | Los datos del pedido están registrados; 5Post espera que el vendedor o remitente entregue el paquete. |
| `PROCESS_WITHDRAWN` — `Передан службе доставки` | El remitente dejó o entregó el paquete a 5Post. |
| `WAREHOUSE_PROCESSING` — `Обрабатывается на складе ...` | En procesamiento dentro de un almacén regional de clasificación de 5Post (`РЦ`). |
| `READY_FOR_SHIPMENT` — `Покинул склад ...` | Salió del almacén de clasificación hacia el siguiente centro o tienda de recogida. |
| `DELIVERED` — `Доставлен в пункт выдачи` | Llegó a la caja de Pyaterochka / Perekrestok elegida o a la taquilla 5Post — **listo para recoger**, ¡todavía sin retirar! |
| `DONE` — `Получен` | Recogido por el destinatario (entregado). |
| `RECEIVED_BY_COURIER` — `Передан курьеру` | En reparto a domicilio en pedidos con entrega por mensajero. |
| `RETURNING_BY_COURIER` — `Неуспешная попытка вручения` | Falló un intento de entrega del mensajero. |
| `CUSTOMS_CLEARANCE_STARTED` / `CUSTOMS_DATA_REQUIRED` / `CUSTOMS_AWAITING_PAYMENT` | Pedido del extranjero (como AliExpress) en despacho de aduanas, esperando tus datos para el representante aduanero o pendiente de pago de derechos. |
| `CUSTOMS_CLEARANCE_COMPLETED` — `Таможенное оформление завершено` | La aduana liberó el paquete para continuar con 5Post. |
| `UNCLAIMED_...` / `RETURNED_TO_PARTNER` / `RETURNED_TO_CLIENT` | No recogido antes del plazo de custodia o cancelado tras la entrega al transportista, de vuelta hacia la tienda o el remitente (`UNCLAIMED_READY_FOR_REPICKUP` significa preparado para volver a un punto de recogida). |
| `INCEPTION_PROBLEM` / `LOST` / `CANCELLED` / `REGISTRATION_ERROR_...` | Incidencia en el pedido, cancelación, pérdida o falta de teléfono / fecha de entrega / dirección que requiere corrección. |

Ten presente una particularidad clave de los códigos de 5Post: `DELIVERED` (`Доставлен в пункт выдачи`) significa **listo para recoger** en tienda o taquilla, mientras que `DONE` (`Получен`) significa que ya lo recogiste.

## Recogida en Pyaterochka, Perekrestok y taquillas

Un pedido para recoger espera en una taquilla 5Post (*постамат*) o en la caja (*касса*) del supermercado Pyaterochka o Perekrestok elegido al comprar; las preguntas frecuentes de 5Post recogen los horarios de taquillas y puntos de recogida:

- **Código de recogida:** cuando el pedido llega al punto (`Доставлен в пункт выдачи`), 5Post envía el código de recogida por SMS. Introdúcelo en la pantalla de la taquilla o dalo en caja. Las preguntas frecuentes de 5Post explican dónde encontrar el código y qué hacer si el personal no localiza tu pedido.
- **Ampliación de custodia y cambio de teléfono:** según las [preguntas frecuentes de 5Post](https://fivepost.ru/faq/), los destinatarios pueden comprobar los plazos de custodia, ampliarlos, pagar online en la web o la app móvil, o consultar los pasos para cambiar el número de teléfono, abrir un casillero atascado o notificar un compartimento vacío o erróneo.
- **Pedidos internacionales (AliExpress):** los pedidos del extranjero gestionados con 5Post incluyen tramo aéreo (`DEPARTED_BY_AIR`, `DELIVERED_BY_AIR`) y despacho en la aduana rusa; si el seguimiento muestra `Требуется предоставить данные таможенному представителю` (`CUSTOMS_DATA_REQUIRED`) o `Требуется оплата таможенных платежей` (`CUSTOMS_AWAITING_PAYMENT`), el representante aduanero necesita tus datos o faltan por pagar los aranceles; las preguntas frecuentes de 5Post detallan qué pedidos pasan por aduana, por qué se solicitan los datos, cómo contactar con el representante aduanero y cuándo pagar. Consulta la [guía de aduanas](guide:customs) y [cuando el seguimiento deja de actualizarse](guide:tracking-not-updating).

Para envíos dentro de Rusia con el correo nacional u otro mensajero, compara con [Russian Post](carrier:russian-post) y [CDEK](carrier:cdek).

## Seguir 5Post con Peek

Pega tu código `P`, tu número de pedido de tienda o tu enlace `fivepost.ru/tracking?id=...` en [Peek](/). Para un código `P`, Peek consulta a Colis Privé y 5Post (primero a 5Post en Rusia) y muestra «5Post tiene este paquete.» cuando 5Post responde. Para un número de pedido de 14 dígitos nunca propone 5Post: pulsa «Cambiar» al añadir el paquete o «Cambiar transportista» en su página y selecciona 5Post. Un enlace de 5Post clasifica el paquete bajo 5Post al instante.

Peek lee el seguimiento sin cuenta de 5Post mediante el adaptador de código abierto [universal-parcel-scraper 5Post](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/5post) y vuelve a intentarlo tras un error de red. Muestra el texto en ruso de cada escaneo, el centro de clasificación o hub indicado en los eventos de almacén (`РЦ ...`, `ХАБ ...`), el nombre y la dirección del punto de recogida cuando el pedido espera en tienda o taquilla, y la hora en que lo recogiste. 5Post fecha cada escaneo con su desplazamiento UTC y Peek lo muestra en la hora de tu dispositivo. El plazo límite de custodia, los datos de pago, el horario del punto, el nombre del remitente y el número de paquetes permanecen en la web y la app de 5Post.

Peek traduce con precisión los códigos de 5Post para que `DELIVERED` nunca genere un falso aviso de entrega en domicilio: **Etiqueta creada** (`INFORMATION_RECEIVED`), **Admitido por el transportista** (`PROCESS_WITHDRAWN`), **En tránsito** (almacén, salida, tramos aéreos y traslado posterior a aduanas), **En aduanas** (petición de datos del agente, despacho y pago de aranceles), **En reparto** (`RECEIVED_BY_COURIER`), **Intento de entrega** (`RETURNING_BY_COURIER`), **Listo para recoger** (`DELIVERED` — `Доставлен в пункт выдачи`), **Entregado** (`DONE` — `Получен`), **Devuelto al remitente** (`RETURNED_TO_...` y códigos `UNCLAIMED_...`, salvo `UNCLAIMED_READY_FOR_REPICKUP` que permanece En tránsito) e **Incidencia** (`LOST`, `CANCELLED`, `CUSTOMS_CLEARANCE_REJECTED` y errores de registro).

Cuando el último escaneo tiene cuatro días y el paquete sigue en «Etiqueta creada», «Admitido por el transportista» o «En tránsito», Peek lo señala con el día de ese escaneo, por ejemplo «Sin novedades desde el lun, 12 oct». La página del paquete muestra entonces «Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a 5Post.», con un enlace «Pide ayuda a 5Post» hacia las preguntas frecuentes de 5Post. Tras tres semanas sin ningún escaneo nuevo, dice «Nada nuevo desde hace más de tres semanas. Si el paquete aún no ha llegado, contacta con el vendedor. Peek sigue preguntando a 5Post.»

Mientras tengas el paquete abierto en Peek, o el día de entrega con las notificaciones activadas, Peek comprueba hasta cada 10 minutos; el día en que 5Post muestra `Передан курьеру`, cada 2 minutos desde las 6:00 en la hora del último escaneo, mientras responda el rastreador de 5Post. En el resto de casos comprueba cada hora. Tras 12 horas solo en «Etiqueta creada» o «Admitido por el transportista», o tras dos días sin novedades, pasa a comprobar cada hora sea cual sea tu seguimiento, y cada 6 horas tras siete días en «Etiqueta creada». Cuando el rastreador de 5Post no responde y Peek sigue el paquete únicamente a través de servicios de seguimiento, comprueba cada hora, cada 3 horas tras un día tranquilo y hasta cada 15 minutos el día de reparto mientras tengas el paquete abierto o las notificaciones activadas.

Puede enviarte avisos para «Cada escaneo», «Pasos importantes» o «Solo la entrega».

## Contactar con 5Post

5Post publica sus teléfonos oficiales y formularios de consulta con o sin cuenta en [fivepost.ru/contacts](https://fivepost.ru/contacts/) y [fivepost.ru/faq](https://fivepost.ru/faq/):

- **Teléfono de Moscú y región de Moscú:** `8-495-935-88-00` (según la tarifa de tu operador)
- **Regiones de Rusia:** `8-800-100-05-05`
- **Preguntas frecuentes y formulario de tickets online:** [FAQ de 5Post](https://fivepost.ru/faq/) y [Contacto de 5Post](https://fivepost.ru/contacts/) (admite consultas de destinatarios con o sin inicio de sesión y archivos adjuntos de hasta 40 MB)
- **Dirección legal:** 109029, Moscú, ul. Srednyaya Kalitnikovskaya, 28 bldg. 4 (INN `7722461620`, OGRN `1187746567568`)

## Preguntas frecuentes

### ¿Por qué el estado interno de 5Post dice `DELIVERED` antes de que yo recoja mi paquete?

En el sistema de 5Post, `DELIVERED` (`Доставлен в пункт выдачи`) significa que el paquete llegó al mostrador o taquilla de Pyaterochka/Perekrestok y está esperándote. Peek lo muestra como **Listo para recoger** y espera a `DONE` (`Получен`) antes de marcarlo como **Entregado**.

### ¿Dónde recojo un pedido de 5Post?

Lo recoges en la taquilla de 5Post (*постамат*) o en la caja del supermercado Pyaterochka («Пятёрочка») o Perekrestok («Перекрёсток») que seleccionaste al comprar, usando el código recibido por SMS de 5Post.

### ¿Por qué 5Post me pidió datos aduaneros en mi pedido de AliExpress?

Los pedidos del extranjero pasan despacho mediante un representante aduanero, que puede requerir tus datos (`CUSTOMS_DATA_REQUIRED`). Las preguntas frecuentes de 5Post indican qué pedidos pasan aduanas, por qué se solicitan esos datos y cómo contactar con el representante. Tras el levante, 5Post muestra `Таможенное оформление завершено` (`CUSTOMS_CLEARANCE_COMPLETED`).

### ¿Cómo contacto con 5Post si no se abre una taquilla o falta el paquete?

Llama al `8-495-935-88-00` (Moscú y región) o al `8-800-100-05-05` (regiones), o envía una consulta de destinatario (con fotos de hasta 40 MB) desde [fivepost.ru/contacts](https://fivepost.ru/contacts/) o desde tu cuenta de 5Post.

:::sources
- [5Post (fivepost.ru)](https://fivepost.ru/)
- [FAQ de 5Post](https://fivepost.ru/faq/)
- [Contacto de 5Post](https://fivepost.ru/contacts/)
- [universal-parcel-scraper 5Post adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/5post)
:::
