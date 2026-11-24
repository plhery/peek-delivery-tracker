---
title: Seguimiento DobroPost: códigos DBRPST, estados y hubs
description: Sigue un paquete de DobroPost de China a Rusia o Bielorrusia: formatos DBRPST, escaneos de almacén y aduana, recogida PVZ, traspasos y ayuda.
slug: seguimiento-dobropost
published: 2026-11-24
updated: 2026-11-24
---

DobroPost (*ДоброПост*, operado por *OOO «Dobropost Logistika»* en Moscú junto con *OOO ICOM DP SOLUTIONS* en Armenia) es un servicio de reenvío de paquetes de China a Rusia y Bielorrusia utilizado por compradores de plataformas chinas como Taobao, Poizon, 1688, Pinduoduo, Tmall, 95, Goofish y JD. Los compradores envían sus pedidos al almacén de DobroPost en Jixi (Heilongjiang), donde se revisan, embalan y pesan; luego el paquete pasa aduanas y se dirige a uno de los puntos de recogida propios de DobroPost (PVZ) o a [CDEK](carrier:cdek), DPD o [Russian Post](carrier:russian-post). El rastreador de DobroPost también reenvía escaneos de Boxberry. Puedes consultar un envío en [tracking.dobropost.com](https://tracking.dobropost.com/) o pegar tu código `DBRPST` en el recuadro de arriba.

## Números de seguimiento de DobroPost (formato `DBRPST`)

Un código de paquete de DobroPost empieza por `DBRPST`, seguido de 10 u 11 dígitos:

| Formato (inventado) | Qué es |
| --- | --- |
| `DBRPST6482917304` | Número de paquete DobroPost de 16 caracteres (`DBRPST` + 10 dígitos). |
| `DBRPST73918462051` | Número de paquete DobroPost de 17 caracteres (`DBRPST` + 11 dígitos). |

Los números que asignan los mensajeros chinos hasta el almacén de DobroPost pertenecen a ellos, no a DobroPost: [tracking.dobropost.com](https://tracking.dobropost.com/) y Peek siguen el envío mediante su código `DBRPST`. Consulta [formatos de números de seguimiento](guide:tracking-number-formats).

## Qué significan los estados de seguimiento de DobroPost

DobroPost publica hitos en ruso junto con códigos internos — primero desde su almacén en China y aduanas, y después reenviados directamente desde el transportista local ([CDEK](carrier:cdek), Boxberry o [Russian Post](carrier:russian-post)):

| Código o texto de DobroPost | Qué significa para ti | Peek muestra |
| --- | --- | --- |
| `VIRTUAL_PARCEL` | El paquete figura en el registro de DobroPost; unos días después el almacén en China embala tus compras dentro. | «Etiqueta creada» |
| `ON_ASSEMBLY`, `ON_WEIGHING`, `AWAITING_PAYMENT`, `READY_FOR_SENT`, `ADDED_TO_THE_REGISTRY` | Embalado y pesado en el almacén de DobroPost en China, a la espera del pago del envío o añadido a un registro de salida. | «Admitido por el transportista» |
| `LEFT_THE_DP_WERHOUSE_IN_CHINA`, `FLEW_OUT_FROM_CHINA` | Salió del almacén de DobroPost en China y partió hacia el tramo internacional. | «En tránsito» |
| `AT_CUSTOMS_IN_CHINA`, `ARRIVED_AT_CUSTOMS_IN_THE_COUNTRY_OFF_DESTINATION`, `START_OF_CUSTOMS_CLEARANCE`, `Выпуск с таможни` | En despacho de exportación en China o despacho aduanero de importación en Rusia o Bielorrusia. | «En aduanas» |
| `RELEASE_OF_GOODS_WITHOUT_PAYMENT_OF_CUSTOMS_DUTIES`, `LEFT_CUSTOMS_AND_HEADING_TO_LAST_MILE_CARRIER'S_WAREHOUSE`, `READY_FOR_SHIPMENT_FOR_LAST_MILE_DELIVERY` | Liberado de aduanas y en camino hacia la red de entrega local. | «En tránsito» |
| `REFUSAL_TO_RELEASE_GOODS_WITH_INDICATION_OF_THE_CODE_OF_THE_REASON_FOR_REFUSAL` | La aduana denegó el levante; el rastreador indica el motivo (por ejemplo mercancía no destinada a uso personal). Revisa tu cuenta DobroPost. | «Incidencia» |
| `RECEIVED_AT_SHIPMENT_WAREHOUSE`, `SEND_TO_TRANSIT_OFFICE`, `SEND_TO_RECIPIENT_OFFICE`, `ACCEPTED_FOR_DELIVERY`, `SENT_TO_DESTINATION_CITY`, `Прием`, `Обработка` | Escaneos nacionales reenviados de CDEK, Boxberry o Russian Post rumbo a tu ciudad. | «En tránsito» |
| `ACCEPTED_AT_WAREHOUSE_ON_DEMAND` (CDEK), `RECEIVED_AT_THE_POINT_OF_ISSUE` (Boxberry) | Llegó al punto de recogida u oficina elegida y está listo para retirar. | «Listo para recoger» |
| `GETTING_READY_TO_RETURN`, `SENT_TO_COLLECTION_POINT`, `RETURNED_TO_COLLECTION_POINT`, `RETURNED`, `Возврат` | No recogido en el punto de entrega o devuelto en el último tramo, regresando a DobroPost (puede reenviarse). | «Devuelto al remitente» |
| `RELEASE_OF_GOODS_CUSTOMS_PAYMENTS_PAID` (`Возврат в Китай разрешен`) | La aduana rechazó el despacho y autorizó el retorno del paquete a China. | «Devuelto al remitente» |
| `PARCEL_DELIVERED_TO_RECIPIENT`, `DELIVERED`, `Вручение` | Retirado en el punto de entrega o entregado al destinatario. | «Entregado» |

## Plazos de entrega, tarifas y puntos de recogida DobroPost PVZ

Según [Tarifas DobroPost](https://dobropost.com/tariffs), los plazos cuentan desde la salida del almacén en China hasta la llegada a un punto PVZ o el traspaso a un mensajero nacional:

- **Tarifas Express** (peso volumétrico, sin artículos sensibles): habitualmente **9–13 días** a Moscú, **10–14 días** a San Petersburgo, **9–12 días** a Novosibirsk, Krasnodar, Ekaterimburgo, Kazán, Irkutsk y Ulán-Udé, y **11–15 días** a Minsk (Bielorrusia).
- **Tarifas Economy** (peso real, pensadas para paquetes pesados o voluminosos): normalmente **19–25 días** a Moscú, **20–27 días** a San Petersburgo, **15–23 días** a Irkutsk y **22–29 días** a Minsk.
- **Tarifas Lejano Oriente y Paquete Pesado:** **6–9 días** a Vladivostok, **8–12 días** a Blagovéshchensk y **6–9 días** para paquetes individuales pesados de 5 a 30 kg.
- **Puntos de recogida DobroPost PVZ:** publicados en [dobropost.com/pvz](https://dobropost.com/pvz) en Moscú, Odintsovo, San Petersburgo, Kazán, Krasnodar, Novosibirsk, Ekaterimburgo, Irkutsk, Ulán-Udé, Blagovéshchensk, Vladivostok, Ussuriysk y Minsk. Las tarifas de entrega en PVZ son `100 RUB` (hasta 5 kg), `200 RUB` (hasta 10 kg) y `300 RUB` (más de 10 kg); las ciudades sin PVZ propio utilizan puntos y mensajeros asociados. Un paquete permanece 7 días gratis en un PVZ DobroPost, luego 50 ₽ diarios, y se elimina a los 30 días de su llegada.

## Aduanas, pausas y problemas con el paquete

- **Pago del envío antes de la salida:** si el seguimiento se queda en `AWAITING_PAYMENT`, el paquete permanece en China hasta abonar la factura en tu cuenta de DobroPost.
- **Pausas en frontera y aduanas:** los escaneos suelen detenerse entre `FLEW_OUT_FROM_CHINA` y `ARRIVED_AT_CUSTOMS_IN_THE_COUNTRY_OFF_DESTINATION`, o durante la revisión aduanera. Consulta [paquetes retenidos en aduanas](guide:customs) y [por qué el seguimiento deja de actualizarse](guide:tracking-not-updating).
- **Daños, artículos faltantes o devoluciones:** las [condiciones de DobroPost](https://dobropost.com/rules) piden anotar cualquier daño en el albarán al recibir el paquete y mostrar el contenido y el embalaje original para reclamar. Su [Base de conocimiento](https://dobropost.com/baza_main) explica qué hacer si falta un artículo. Un paquete no recogido vuelve a DobroPost (`RETURNED`), que puede reenviarlo contactando con soporte. Consulta [paquete perdido](guide:lost-parcel), [paquete dañado](guide:damaged-parcel), [entregado pero no recibido](guide:delivered-not-received) y [devuelto al remitente](guide:returned-to-sender).

## Atención al cliente y enlaces oficiales de DobroPost

La web de DobroPost ofrece el botón «Напишите в поддержку» (escribir a soporte), una comunidad de clientes y un canal de Telegram, e invita a enviar quejas o sugerencias directamente a su CEO, Sergey Serbin. Su [Base de conocimiento](https://dobropost.com/baza_main) explica cómo contactar con soporte, cambiar la tarifa de un paquete creado y completar aduanas.

- **Operador ruso registrado:** *OOO «Dobropost Logistika»* (*ООО «Добропост Логистика»*, INN `9722023780`), Moscú (`119192, Lomonosovsky Prospekt, 43 bldg. 2, office 4/1/1`; el sitio cita también `Michurinsky Prospekt, 54 bldg. 2`).
- **Entidad armenia:** *OOO ICOM DP SOLUTIONS* (*ООО ИКОМ ДП СОЛУШНС*), Vanadzor, provincia de Lori, Armenia.
- **Condiciones y tarifas:** [dobropost.com/rules](https://dobropost.com/rules), [dobropost.com/tariffs](https://dobropost.com/tariffs) y [dobropost.com/pvz](https://dobropost.com/pvz).

> Desconfía de mensajes falsos en Telegram o enlaces por SMS que pidan tasas aduaneras extra fuera de tu área de cliente oficial de DobroPost o del portal oficial del agente aduanero. Verifica siempre cualquier pago dentro de tu cuenta DobroPost. Consulta [estafas por SMS de envíos](guide:delivery-scam-texts).

## Seguir un paquete de DobroPost con Peek

[Peek](/) detecta DobroPost automáticamente como único candidato cuando introduces un número `DBRPST` (`DBRPST6482917304` o `DBRPST73918462051`, inventados) y lo señala enseguida con «Transportista detectado»; Peek también reconoce enlaces pegados de `tracking.dobropost.com/?trackNumber=`. Si necesitas elegir DobroPost manualmente, pulsa «Cambiar» al añadirlo o «Cambiar transportista» en la pantalla del paquete (buscar «ДоброПост» o «Dobropost» también lo encuentra).

Peek lee directamente el rastreador de DobroPost y muestra la fase («Etiqueta creada», «Admitido por el transportista», «En tránsito», «En aduanas», «Listo para recoger», «Incidencia», «Devuelto al remitente», «Entregado»), el texto en ruso de DobroPost y de los socios que reenvía (CDEK, Boxberry y Russian Post) sin teléfonos, y la hora de entrega una vez entregado. La localidad que CDEK indica entre paréntesis al final de un escaneo se usa como lugar del escaneo. Los códigos de estado quedan fuera de todas las apps de Peek. DobroPost entrega cada escaneo con el desplazamiento UTC de Moscú, incluidos los del almacén en China, y Peek los muestra en la hora de tu dispositivo. El rastreador de DobroPost no ofrece fecha estimada de entrega, peso, nombre de quien firmó ni número de seguimiento local.

Mientras tengas el paquete abierto en Peek, o el día de entrega con las notificaciones activadas, Peek comprueba hasta cada 10 minutos mientras responda el rastreador de DobroPost. En el resto de casos comprueba cada hora. El rastreador de DobroPost no muestra fase de reparto en domicilio, por lo que Peek nunca cambia a las comprobaciones cada 2 minutos del día de entrega. Tras 12 horas solo en «Etiqueta creada» o «Admitido por el transportista», o tras dos días sin novedades, pasa a comprobar cada hora sea cual sea tu seguimiento, y cada 6 horas tras siete días en «Etiqueta creada». Cuando el rastreador de DobroPost no responde y Peek sigue el paquete únicamente a través de servicios de seguimiento, comprueba cada hora, cada 3 horas tras un día tranquilo y hasta cada 15 minutos el día de reparto mientras tengas el paquete abierto o las notificaciones activadas.

Cuando el último escaneo tiene cuatro días y el paquete sigue en «Etiqueta creada», «Admitido por el transportista» o «En tránsito», Peek lo señala con el día de ese escaneo, por ejemplo «Sin novedades desde el lun, 12 oct». La página del paquete muestra entonces «Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a DobroPost.» Tras tres semanas sin ningún escaneo nuevo, dice «Nada nuevo desde hace más de tres semanas. Si el paquete aún no ha llegado, contacta con el vendedor. Peek sigue preguntando a DobroPost.»

Puedes elegir avisos para «Cada escaneo», «Pasos importantes» o «Solo la entrega». Peek solo supervisa las actualizaciones de seguimiento y no puede pagar facturas de envío, enviar datos de pasaporte a aduanas ni contactar con DobroPost o sus socios locales por ti.

## Preguntas frecuentes sobre el seguimiento de DobroPost

### ¿Qué aspecto tiene un número de seguimiento de DobroPost?

Los códigos internacionales de DobroPost empiezan por `DBRPST` seguido de 10 u 11 dígitos (por ejemplo `DBRPST6482917304` o `DBRPST73918462051`, inventados).

### ¿Cuánto tarda la entrega de DobroPost desde China?

Las estimaciones publicadas en [dobropost.com/tariffs](https://dobropost.com/tariffs) van de **6–9 días** a Vladivostok y **9–13 días** (Express) o **19–25 días** (Economy) a Moscú, y **11–15 días** (Express) o **22–29 días** (Economy) a Minsk, desde la salida del almacén en China.

### ¿Qué transportistas locales entregan paquetes de DobroPost en Rusia y Bielorrusia?

DobroPost entrega en sus propios puntos PVZ en Rusia y Minsk y, según sus tarifas, a través de [CDEK](carrier:cdek), DPD y [Russian Post](carrier:russian-post) (solo CDEK en Minsk). Su rastreador reenvía escaneos de CDEK, Boxberry y Russian Post.

### ¿Por qué mi paquete de DobroPost está detenido en `AWAITING_PAYMENT` o aduanas?

`AWAITING_PAYMENT` significa que tu paquete ya fue embalado y pesado en el almacén de China y espera el pago del envío en tu cuenta. Si el seguimiento se pausa en aduanas, la [Base de conocimiento](https://dobropost.com/baza_main) explica el despacho aduanero y el soporte puede informarte del estado.

:::sources
- [DobroPost: sitio web oficial](https://dobropost.com/) – reenvío de paquetes de marketplaces China–Rusia/Bielorrusia, almacén de Jixi, soporte y línea con el CEO, sociedades en Moscú y Armenia (*OOO «Dobropost Logistika»*, INN 9722023780)
- [DobroPost: rastreador de paquetes](https://tracking.dobropost.com/) – página pública de seguimiento `DBRPST`
- [DobroPost: tarifas y plazos](https://dobropost.com/tariffs) – plazos Express, Economy, Lejano Oriente y Paquete Pesado, reglas de peso y socios
- [DobroPost: puntos de recogida (PVZ)](https://dobropost.com/pvz) – ubicaciones PVZ DobroPost en Rusia y Minsk, tarifas de entrega y custodia
- [DobroPost: Base de conocimiento](https://dobropost.com/baza_main) – guías de compras, soporte, cambio de tarifa, aduanas y artículos faltantes
- [DobroPost: Condiciones generales de transporte](https://dobropost.com/rules) – términos de transporte y despacho aduanero
- [Universal Parcel Scraper: dobropost](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/dobropost) – formato `DBRPST`, regla de enlace `tracking.dobropost.com`, mapa de estados DobroPost/CDEK/Boxberry/Russian Post, localidad entre paréntesis y horas de Moscú
:::
