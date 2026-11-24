---
title: Seguimiento Chit Chats: códigos de 10 car. y traspaso USPS
description: Sigue un paquete de Chit Chats de Canadá a EE. UU., Canadá o el extranjero: códigos de 10 caracteres, traspaso a USPS/Canada Post y soporte.
slug: seguimiento-chit-chats
published: 2026-11-24
updated: 2026-11-24
---

**Chit Chats** (*Chit Chats Express*) es una empresa canadiense de envíos para comercio electrónico fundada en 2001 por Derek Nolan. Cuenta con 66 sucursales en todo Canadá, también acepta paquetes por envío postal (SmartDrop) y recogida, y ha enviado más de 50 millones de paquetes para más de 100 000 remitentes ([chitchats.com](https://chitchats.com/)). La entrega final la realizan transportistas asociados: su rastreador indica el socio y ofrece el número de seguimiento de este, como [USPS](carrier:usps) en Estados Unidos o [Canada Post](carrier:canada-post) en Canadá. Puedes consultar un envío en [chitchats.com/tracking](https://chitchats.com/tracking) o pegar tu identificador de envío Chit Chats de 10 caracteres en el recuadro de arriba.

## Números de seguimiento de Chit Chats (identificador de 10 caracteres)

Cada pedido de Chit Chats recibe un **identificador de envío** alfanumérico de 10 caracteres en mayúsculas (que combina letras y cifras, normalmente empezando por una letra y un dígito distinto de cero):

| Formato (inventado) | Qué es |
| --- | --- |
| `K4N8V3T6R2` | Identificador de envío estándar de Chit Chats de 10 caracteres. |
| `P7M3X9D5Q8` | Variante de identificador de envío de Chit Chats de 10 caracteres para envíos a EE. UU., Canadá o internacionales. |

Una vez que Chit Chats entrega el paquete al socio de última milla, la página de seguimiento también muestra el código propio de ese socio — por ejemplo un código de barras Intelligent Mail de [USPS](carrier:usps) (que puede incluir un prefijo `420` + código postal de enrutamiento que Peek elimina limpiamente) o un número de seguimiento de [Canada Post](carrier:canada-post). Consulta [formatos de números de seguimiento](guide:tracking-number-formats).

## Qué significan los estados de seguimiento de Chit Chats

El rastreador de Chit Chats muestra primero sus propios escaneos y después los del transportista asociado ([USPS](carrier:usps), [Canada Post](carrier:canada-post) u otro):

| Chit Chats o el socio indica | Qué significa para ti | Peek muestra |
| --- | --- | --- |
| `Shipment created, Chit Chats awaiting item`, `Label Created`, `Electronic information submitted by shipper` | El vendedor compró el franqueo online, pero todavía no ha depositado el paquete en una sucursal de Chit Chats. | «Etiqueta creada» |
| `Received by Chit Chats` | Paquete físico escaneado en una sucursal o punto de entrega de Chit Chats en Canadá. | «Admitido por el transportista» |
| `Arrived at Chit Chats facility`, `Departed from Chit Chats facility`, `In transit` | Clasificado en un centro de Chit Chats; `In transit` marca el traspaso al transportista asociado. | «En tránsito» |
| `Received from Chit Chats`, `Accepted at USPS Facility`, `Item accepted at the Post Office`, `Received at sort hub` | Incorporado a la red de clasificación del transportista asociado (USPS, Canada Post o socio internacional). | «En tránsito» / «Admitido por el transportista» |
| `Arrived at USPS Facility`, `Departed USPS Facility`, `In Transit to Next Facility`, `Item processed` | En tránsito por los centros de clasificación nacionales o internacionales del socio. | «En tránsito» |
| `Out for Delivery`, `Item out for delivery` | En reparto hoy con el transportista local asociado. | «En reparto» |
| `Notice card left indicating where and when to pick up item` | El socio intentó entregar sin nadie en casa y dejó un aviso de recogida. | «Intento de entrega» |
| `Item available for pickup at Post Office`, `Final Notice; Item will be returned to sender if not collected within 10 days` | En custodia en la oficina postal a la espera de recogida (incluso un aviso final indica que el paquete sigue esperando recogida y aún no ha sido devuelto). | «Listo para recoger» |
| `Delivered`, `Delivered, Front Door/Porch`, `Delivered, In/At Mailbox`, `Shipment Delivered` | Entregado en la dirección del destinatario por el transportista asociado. | «Entregado» |

## Envíos transfronterizos a EE. UU., aduanas y traspaso a socios

- **Envíos a Estados Unidos:** Chit Chats ofrece envíos a EE. UU. con despacho aduanero, entrega con derechos pagados (DDP) y ayuda con documentación T-MEC (CUSMA), calcula 2–6 días laborables y publica sus novedades arancelarias de EE. UU. para 2026 en [chitchats.com](https://chitchats.com/). Consulta [paquetes retenidos en aduanas](guide:customs).
- **Seguimiento en el socio local:** una vez que Chit Chats entrega el paquete, su página de contacto sugiere usar el número de seguimiento del transportista en la web propia de ese socio.

## Paquete de Chit Chats retrasado, detenido o extraviado

- **Contacta primero con el vendedor online:** la [página de contacto](https://chitchats.com/contact) de Chit Chats aclara que su equipo ve la misma información de seguimiento que tú y solo puede asesorar al remitente en asuntos de su cuenta, por lo que los destinatarios deben hablar con el vendedor.
- **Paquete retrasado:** si ya pasó la fecha estimada de entrega, la página de seguimiento de Chit Chats recomienda contactar con el vendedor. Consulta [por qué el seguimiento deja de actualizarse](guide:tracking-not-updating).
- **Entregado o desaparecido:** comprueba el buzón, el porche o la oficina postal local (sobre todo si Canada Post dejó un aviso), y contacta enseguida con el vendedor. Consulta [entregado pero no recibido](guide:delivered-not-received), [paquete perdido](guide:lost-parcel), [paquete dañado](guide:damaged-parcel) y [devuelto al remitente](guide:returned-to-sender).

## Atención al cliente de Chit Chats y contactos en Canadá

Canales oficiales de soporte publicados en [Contacto de Chit Chats](https://chitchats.com/contact) y [Soporte de Chit Chats](https://support.chitchats.com/en/support/home):

- **Teléfono gratuito (inglés y francés — facturación y sucursales):** `1-844-842-8777` (más teléfonos propios de cada una de las 66 sucursales canadienses en horario local).
- **Portal de ayuda y soporte:** [support.chitchats.com/en/support/home](https://support.chitchats.com/en/support/home) y [chitchats.com/contact](https://chitchats.com/contact).
- **Rastreador público:** [chitchats.com/tracking](https://chitchats.com/tracking).

> Desconfía de SMS falsos que pidan un pequeño pago online para despachar un paquete entre Canadá y Estados Unidos. Comprueba siempre cualquier aviso aduanero o postal directamente con el vendedor o en el seguimiento oficial de USPS o Canada Post. Consulta [estafas por SMS de envíos](guide:delivery-scam-texts).

## Seguir un paquete de Chit Chats con Peek

[Peek](/) consulta automáticamente a Chit Chats como único candidato cuando introduces un identificador de envío Chit Chats de 10 caracteres (`K4N8V3T6R2` o `P7M3X9D5Q8`, inventados) y muestra «Chit Chats tiene este paquete.» cuando Chit Chats lo confirma; Peek también reconoce directamente los enlaces pegados `chitchats.com/tracking/<10-car>`. Si alguna vez necesitas elegir Chit Chats a mano, pulsa «Cambiar» al añadirlo o «Cambiar transportista» en la pantalla del paquete (buscar «ChitChats» o «Chit Chats Express» también lo encuentra).

Peek lee directamente el rastreador de Chit Chats y muestra la fase («Etiqueta creada», «Admitido por el transportista», «En tránsito», «En reparto», «Listo para recoger», «Intento de entrega», «Entregado»), el texto de los escaneos de Chit Chats y del socio, y la localidad de cada escaneo sin código postal; los lugares con número de calle se omiten. También muestra el día estimado de entrega, el peso en kilogramos y las dimensiones. El nombre del destinatario aparece bajo «Destinatario» en tu propia página del paquete, y la ciudad de destino puede cerrar la ruta en tu mapa mientras viaja el envío; los enlaces compartidos no muestran ninguno de los dos. Cuando Chit Chats nombra un socio que Peek reconoce, como USPS o Canada Post, junto con su número de seguimiento (un número USPS sin prefijo postal de enrutamiento), Peek comprueba que ese socio tiene el paquete: entonces aparece «Entrega con» seguido del nombre del transportista y se muestran ambos números. Los escaneos propios de Chit Chats llevan hora exacta. Los del socio llegan con una hora etiquetada como hora del Pacífico por Chit Chats pero a veces desfasada varias horas, así que Peek los fecha en la zona horaria de su lugar (la de Nueva York para `NEW YORK, NY`, la de Toronto para `TORONTO, ON`), un escaneo sin lugar en la zona del escaneo contiguo o si no en la de Vancouver, y los muestra todos en la hora de tu dispositivo. Peek omite el nombre del servicio, los códigos de estado y la localidad del remitente.

Mientras tengas el paquete abierto en Peek, o el día de entrega con las notificaciones activadas, Peek comprueba hasta cada 10 minutos; el día en que el rastreador muestra `Out for Delivery` o `Item out for delivery`, cada 2 minutos desde las 6:00 según el reloj del último escaneo, mientras responda el rastreador de Chit Chats. En el resto de casos comprueba cada hora. Tras 12 horas solo en «Etiqueta creada» o «Admitido por el transportista», o tras dos días sin novedades, pasa a comprobar cada hora lo sigas como lo sigas, y cada 6 horas tras siete días en «Etiqueta creada». Cuando el rastreador de Chit Chats no responde y Peek sigue el paquete únicamente a través de servicios de seguimiento, comprueba cada hora, cada 3 horas tras un día tranquilo y hasta cada 15 minutos el día de reparto mientras tengas el paquete abierto o las notificaciones activadas.

Cuando el último escaneo tiene cuatro días y el paquete sigue en «Etiqueta creada», «Admitido por el transportista» o «En tránsito», Peek lo señala con el día de ese escaneo, por ejemplo «Sin novedades desde el lun 12 oct». La página del paquete muestra entonces «Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a Chit Chats.», con un enlace «Pide ayuda a Chit Chats» hacia la página de contacto de Chit Chats. Tras tres semanas sin ningún escaneo nuevo, dice «Nada nuevo desde hace más de tres semanas. Si el paquete aún no ha llegado, contacta con el vendedor. Peek sigue preguntando a Chit Chats.»

Puedes elegir avisos para «Cada escaneo», «Pasos importantes» o «Solo la entrega». Peek solo supervisa las actualizaciones de seguimiento y no puede presentar reclamaciones, modificar direcciones ni contactar con Chit Chats o sus transportistas asociados por ti.

## Preguntas frecuentes sobre el seguimiento de Chit Chats

### ¿Qué aspecto tiene un número de seguimiento de Chit Chats?

Los identificadores de envío de Chit Chats tienen 10 letras mayúsculas y dígitos (por ejemplo `K4N8V3T6R2` o `P7M3X9D5Q8`, inventados). Tras el traspaso, el paquete también recibe un código del transportista asociado (como USPS o Canada Post).

### ¿Quién entrega un paquete de Chit Chats en mi puerta?

Lo entrega un transportista asociado, como [USPS](carrier:usps) en Estados Unidos o [Canada Post](carrier:canada-post) en Canadá. El rastreador de Chit Chats indica el socio y facilita su número de seguimiento.

### ¿Por qué mi paquete de Chit Chats hizo una pausa tras `Departed from Chit Chats facility`?

El rastreador de Chit Chats no indica un motivo. Su estado `In transit` corresponde al traspaso al transportista asociado, y los escaneos del socio aparecen en cuanto este lo registra. Si ya pasó el plazo estimado de entrega, contacta con el vendedor.

### ¿Con quién contacto si mi paquete de Chit Chats no aparece?

Contacta con el vendedor que envió el paquete. Según [chitchats.com/contact](https://chitchats.com/contact), el equipo de Chit Chats solo puede asesorar al remitente en cuestiones de cuenta. Su teléfono gratuito `1-844-842-8777` atiende facturación, consultas de sucursales y atención en francés.

:::sources
- [Chit Chats: sitio web oficial](https://chitchats.com/) – consolidador canadiense de envíos e-commerce para EE. UU., Canadá y envíos internacionales
- [Chit Chats: sobre nosotros](https://chitchats.com/about) – fundada en 2001 por Derek Nolan, más de 50 M de paquetes, 66 sucursales en Canadá y más de 100 000 remitentes
- [Chit Chats: portal de seguimiento](https://chitchats.com/tracking) – página de seguimiento por identificador de 10 caracteres
- [Chit Chats: contacto](https://chitchats.com/contact) – teléfono gratuito `1-844-842-8777`, política de remitir destinatarios al vendedor y directorio de sucursales (URL de ayuda enlazada por Peek)
- [Chit Chats: portal de ayuda y soporte](https://support.chitchats.com/en/support/home) – centro de ayuda oficial
- [Universal Parcel Scraper: chit-chats](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/chit-chats) – detección de ID de 10 caracteres, regla de enlace `chitchats.com/tracking/`, mapa de estados propios y de socios, extracción de ETA/peso/dimensiones/servicio y limpieza del prefijo 420 de USPS
:::
