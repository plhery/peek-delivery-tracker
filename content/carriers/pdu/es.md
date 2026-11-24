---
title: Seguimiento PDU: códigos Parcel Delivery Union y ayuda
description: Sigue un paquete internacional PDU (Parcel Delivery Union / 友递联): formatos de guía, estados de tránsito y última milla, aduanas y contactos.
slug: seguimiento-pdu
published: 2026-11-24
updated: 2026-11-24
---

**PDU** (*Parcel Delivery Union*, operado por *Shanghai Youdilian Technology Co., Ltd.*, en chino *友递联科技*) es un operador chino de logística inteligente transfronteriza con sede en Shanghái fundado en 2022 ([pduni.com](https://www.pduni.com/)). Con sucursales propias y hubs operativos locales en más de 10 países de Norteamérica, Europa, África y Asia, PDU ofrece a comercios electrónicos y fabricantes despacho aduanero integral, recogida, entrega de última milla en el extranjero y cobro contra reembolso (COD). Puedes consultar un envío en el portal en inglés de PDU en [pduni.com/MV/en/customer-services/waybill-inquiry](https://www.pduni.com/MV/en/customer-services/waybill-inquiry) o pegar tu número de seguimiento en el cuadro de arriba.

## Números de seguimiento de PDU (sufijo `PD` y guías personalizadas)

Muchos paquetes transfronterizos de PDU llevan un código de 12 caracteres formado por dos letras, 8 dígitos y `PD`:

| Formato (inventado) | Qué es |
| --- | --- |
| `MX48291736PD` | Guía transfronteriza PDU de 12 caracteres (`MX` + 8 dígitos + `PD`). |
| `CO73918462PD` | Otro formato de guía `..PD` de 12 caracteres usado en rutas internacionales. |

La página pública de consulta de PDU acepta también otras guías alfanuméricas de 6 a 40 caracteres cuando seleccionas PDU manualmente. El mismo patrón coincide con los números de [Colis Privé](carrier:colis-prive): Peek pregunta primero a Colis Privé y luego a PDU, o primero a PDU en China. Consulta los [formatos de números de seguimiento](guide:tracking-number-formats).

## Qué significan los estados de seguimiento de PDU

PDU asigna a cada envío y escaneo un código general de estado (`TRANSIT`, `COLLECTING`, `DELIVERY`, `SIGNED`, etc.) junto con textos en inglés o en el idioma de destino (a menudo en español en rutas latinoamericanas, frecuentemente precedidos por un código breve del transportista local como `ENT,Entregado` que Peek limpia al mostrarlo):

| Estado o texto de PDU | Qué significa para ti | Peek muestra |
| --- | --- | --- |
| `Shipment information sent to PDU.`, `Package not found in last mile or customs process.` | Orden electrónica recibida por PDU antes de la admisión física o antes de que la red local haya registrado su primer escaneo. | «Etiqueta creada» |
| `COLLECTING` (`Being Collected`), `FINAL_COLLECTING` (`Last-Mile Collection`), `TRANSIT` (`In Transit`) | Recogido en origen o circulando en tránsito internacional y centros de distribución regionales. | «En tránsito» |
| `Recogida en Aduanas`, `Ingreso a CEDI`, `En tránsito nacional` | Recogido en aduanas, ingresado en el centro de distribución nacional (CEDI) o en tránsito interior. | «En tránsito» |
| `DELIVERY` (sin texto de salida a ruta) | Pasado a la red del transportista local; Peek mantiene este estado en tránsito hasta que empieza el reparto real. | «En tránsito» |
| `Salida a Ruta` | En reparto hoy con el mensajero local. | «En reparto» |
| `DELIVERY_EXCEPTION` (`Delivery Exception`), `TO_BE_RETURN` (`Awaiting Return`) | Incidencia de entrega señalada o paquete pendiente de devolución al remitente; contacta con el vendedor o el transportista local. | «Incidencia» |
| `RETURNED` (`Returned`) | Devuelto hacia el remitente o almacén de origen. | «Devuelto al remitente» |
| `SIGNED` (`Delivered`), `Entregado` | Entregado y firmado en la dirección de destino. | «Entregado» |

Ten en cuenta que el aviso inicial `Package not found in last mile or customs process.` de PDU indica simplemente que el socio local de última milla aún no ha escaneado el paquete: **no** significa que la aduana haya retenido tu envío.

## Despacho aduanero, entrega internacional y cobro contra reembolso

Según [pduni.com](https://www.pduni.com/) y el [Centro de ayuda PDU](https://www.pduni.com/MV/en/customer-services/help-center):

- **Despacho propio y clasificación SA con IA:** PDU gestiona su propia red de despacho aduanero en más de 10 países con emparejamiento automatizado de códigos SA y pago anticipado de derechos; según sus preguntas frecuentes, PDU no cobra una tarifa aparte por declaraciones formales de exportación y los comercios pueden subir documentos aduaneros complementarios en línea si falta algo.
- **Entrega local de última milla:** una vez que la aduana de destino libera el envío (`Recogida en Aduanas` / `Ingreso a CEDI`), sucursales locales o socios de mensajería realizan la entrega a domicilio y, cuando corresponda, el cobro contra reembolso (COD).
- **Impuestos y aranceles de importación:** el IVA de importación o los gastos aduaneros dependen de los umbrales locales y de si la tienda cobró los derechos al finalizar la compra. Consulta [paquetes retenidos en aduanas](guide:customs).

## Paquete PDU retenido, sin novedades o perdido

Dado que los comercios electrónicos y fabricantes poseen la cuenta de envío con PDU, **contacta siempre primero con tu vendedor o mercado** para corregir la dirección, pedir un reembolso o reenvío.

- **Silencio entre China y la admisión local:** los escaneos suelen pausarse varios días entre `Shipment information sent to PDU.` (o `Package not found in last mile or customs process.`) y `Recogida en Aduanas` / `Ingreso a CEDI` mientras la carga aérea o marítima consolidada llega al país de destino. Consulta [por qué el seguimiento deja de actualizarse](guide:tracking-not-updating).
- **`DELIVERY_EXCEPTION` o `TO_BE_RETURN`:** actúa pronto — verifica tu dirección de entrega y teléfono con el vendedor para que el centro local programe otro intento antes de que el envío pase a `RETURNED`.
- **Marcado como entregado o dañado:** pregunta primero a convivientes, recepción del edificio y vecinos, y abre una reclamación con la tienda antes de que cierre la protección al comprador. Consulta [entregado pero no recibido](guide:delivered-not-received), [paquete perdido](guide:lost-parcel), [paquete dañado](guide:damaged-parcel) y [devuelto al remitente](guide:returned-to-sender).

## Atención al cliente de PDU y contactos en Shanghái

Datos oficiales publicados en [PDU Waybill Inquiry](https://www.pduni.com/MV/en/customer-services/waybill-inquiry) y [PDU Help Center](https://www.pduni.com/MV/en/customer-services/help-center):

- **Razón social:** *Shanghai Youdilian Technology Co., Ltd.* (*友递联科技*, registro ICP `沪ICP备2022026205号`).
- **Teléfono:** `021-50837888` (`+86 21 5083 7888`).
- **Correo electrónico:** `service@pduni.com`.
- **Sede central:** Room 305, 6F, Zhonghai Building, Zhenbei Road, Putuo District, Shanghái, China.
- **Localizador y ayuda en línea:** [pduni.com/MV/en/customer-services/waybill-inquiry](https://www.pduni.com/MV/en/customer-services/waybill-inquiry) y [pduni.com/MV/en/customer-services/help-center](https://www.pduni.com/MV/en/customer-services/help-center).

> El Centro de ayuda de PDU advierte explícitamente sobre estafas por suplantación que usan el logotipo o los contactos de PDU. Nunca pagues tasas aduaneras ni de entrega a través de un enlace SMS sin verificar — confirma siempre primero cualquier cobro con tu tienda o en el portal oficial del transportista. Consulta [estafas por SMS de entrega](guide:delivery-scam-texts).

## Seguir un paquete de PDU con Peek

Cuando introduces un código `..PD` de 12 caracteres (`MX48291736PD` o `CO73918462PD`, inventados), [Peek](/) pregunta a Colis Privé y a PDU por turno (primero a PDU en China) y muestra «PDU tiene este paquete.» cuando el localizador de PDU lo reconoce. Si tu número de PDU usa otro formato de 6 a 40 caracteres, toca «Cambiar» al añadirlo o «Cambiar transportista» en la pantalla del paquete y elige **PDU** (buscar «Parcel Delivery Union» o «友递联» también lo encuentra).

Peek lee el localizador de PDU y muestra la fase («Etiqueta creada», «En tránsito», «En reparto», «Incidencia», «Devuelto al remitente», «Entregado»), el texto del escaneo sin el código del transportista final delante (`Entregado` en lugar de `ENT,Entregado`), la ubicación y la hora de entrega. Los códigos de estado de PDU fijan la fase pero no se muestran en ninguna app de Peek. PDU da la hora de cada escaneo con su desfase UTC, o si no en hora de Pekín, y Peek los muestra en la hora de tu dispositivo. El localizador de PDU no ofrece fecha estimada de entrega.

Mientras tienes el paquete abierto en Peek, o el día de entrega con las notificaciones activadas, Peek consulta hasta cada 10 minutos; el día en que PDU muestra `Salida a Ruta`, cada 2 minutos desde las 6:00 en la hora del último escaneo mientras el localizador de PDU responda. El resto del tiempo Peek comprueba cada hora. Tras 12 horas con solo «Etiqueta creada» o «Admitido por el transportista», o dos días sin novedades, consulta cada hora lo sigas como lo sigas, y cada 6 horas tras siete días en «Etiqueta creada». Cuando el localizador de PDU no responde y Peek sigue el paquete únicamente a través de servicios de seguimiento, consulta cada hora, cada 3 horas tras un día tranquilo y hasta cada 15 minutos el día en que el paquete está en reparto, mientras tengas el paquete abierto o las notificaciones activadas.

Cuando el escaneo más reciente tiene cuatro días y el paquete sigue en «Etiqueta creada», «Admitido por el transportista» o «En tránsito», Peek lo marca con el día de ese escaneo, como «Sin novedades desde el lun 12 oct». La página del paquete muestra entonces «Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a PDU.» Tras tres semanas sin nuevos escaneos, indica «Nada nuevo desde hace más de tres semanas. Si el paquete aún no ha llegado, contacta con el vendedor. Peek sigue preguntando a PDU.»

Puedes elegir ajustes de alertas para «Cada escaneo», «Pasos importantes» o «Solo la entrega». Peek solo supervisa las actualizaciones de seguimiento y no puede despachar aduanas, editar tu dirección de entrega ni contactar con PDU por ti.

## Preguntas sobre el seguimiento de PDU

### ¿Qué es PDU (*Parcel Delivery Union*)?

PDU (*Shanghai Youdilian Technology Co., Ltd.*, *友递联科技*) es una empresa de logística transfronteriza con sede en Shanghái fundada en 2022 que ofrece despacho aduanero internacional, recogida, entrega de última milla y cobro contra reembolso en más de 10 países.

### ¿Qué aspecto tiene un número de seguimiento de PDU?

Muchas guías internacionales de PDU tienen 12 caracteres: dos letras, 8 dígitos y `PD` (por ejemplo `MX48291736PD`, inventado), aunque el portal de PDU también sigue otras guías alfanuméricas de 6 a 40 caracteres.

### ¿Significa `Package not found in last mile or customs process.` que mi paquete está retenido en aduanas?

No. En el localizador de PDU ese mensaje inicial solo indica que se crearon los datos electrónicos del envío y aún no ha recibido su primer escaneo físico en el sistema local de reparto o aduanas.

### ¿Cómo contacto con la atención al cliente de PDU?

Contacta primero con tu tienda en línea para reembolsos o cambios de entrega. Los contactos de soporte de PDU en Shanghái publicados en [pduni.com/MV/en/customer-services/help-center](https://www.pduni.com/MV/en/customer-services/help-center) son `021-50837888` y `service@pduni.com`.

:::sources
- [PDU: sitio oficial](https://www.pduni.com/) – fundada en 2022 en el distrito de Putuo, Shanghái, red propia de despacho aduanero y última milla en 10+ países, COD
- [PDU: portal de seguimiento de guías](https://www.pduni.com/MV/en/customer-services/waybill-inquiry) – consulta pública en inglés, `service@pduni.com` y `021-50837888`
- [PDU: centro de ayuda y FAQ](https://www.pduni.com/MV/en/customer-services/help-center) – FAQ de aduanas, aviso contra suplantaciones y dirección de la sede en Shanghái (*Shanghai Youdilian Technology Co., Ltd.*)
- [Universal Parcel Scraper: pdu](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/pdu) – regla de detección `^[A-Z]{2}\d{8}PD$`, API `traceByWeb`, mapa de estados y textos (`DELIVERY` frente a `Salida a Ruta`), limpieza de prefijos y marcas horarias UTC/Pekín
:::
