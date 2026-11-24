---
title: Seguimiento DTDC: formatos AWB, estados RTO y soporte
description: Rastrea un paquete DTDC en India o todo el mundo: códigos AWB N, 7D y 7X, escaneos, devoluciones RTO, tiempos WorldWyde y atención al cliente.
slug: seguimiento-dtdc
published: 2026-11-24
updated: 2026-11-24
---

DTDC (*DTDC Express Limited*), fundada el 26 de julio de 1990 con sede en Bengaluru, es una de las mayores redes de logística exprés de la India. A través de más de 16.500 socios que cubren el 96 % de la población india, con Express Parcel llegando a más de 15.300 códigos postales, presencia local en 7 países y alcance internacional a más de 220 destinos, DTDC ofrece paquetería exprés nacional, *Express Premium* al día siguiente, *AirMax*, *Raftaar*, 3PL, carga LTL y envíos internacionales *DTDC WorldWyde*. Puedes consultar un envío en la [página de seguimiento de DTDC](https://www.dtdc.com/track-your-shipment/) o en [MyDTDC](https://web.mydtdc.in/), o pegar el número AWB arriba.

## Números de seguimiento de DTDC (formatos AWB)

DTDC envía tu número Air Waybill (AWB) por correo electrónico y SMS al confirmarse el pedido. Los AWB nacionales e internacionales de DTDC siguen estos formatos alfanuméricos habituales:

| Formato (inventado) | Qué es |
| --- | --- |
| `N48291736` | Número de envío estándar DTDC de 9 caracteres (`N` + 8 dígitos). |
| `7D482917364` | Número de envío DTDC de 11 caracteres (`7D` + 9 dígitos). |
| `7X482917364` | Número de envío DTDC de 11 caracteres (`7X` + 9 dígitos). |

DTDC también emite códigos de 9 y 11 caracteres que empiezan por otras letras sueltas (como `B`, `D`, `H`, `P`, `T`, `U`, `V` o `X` seguidas de 8 o 10 dígitos), que coinciden con otros transportistas regionales y pueden requerir elegir DTDC manualmente si aparecen varios candidatos. En [dtdc.com/track-your-shipment/](https://www.dtdc.com/track-your-shipment/) puedes alternar entre **Shipment Number** (AWB) y **Reference Number**. Para otros formatos en la India y el extranjero, consulta [formatos de números de seguimiento](guide:tracking-number-formats).

## Qué significan los estados de seguimiento de DTDC

DTDC publica hitos y movimientos de centros (como `Arrived at DELHI`, `Dispatched from MATHURA` o `Received at ... Processing Centre`) en hora estándar de la India (`Asia/Kolkata`, UTC+5:30).

| DTDC muestra | Qué significa para ti | Peek muestra |
| --- | --- | --- |
| `Booked`, `Booked at Origin Branch`, `Pickup Awaited`, `Pickup Scheduled`, `Pickup requested`, `Not picked` | Guía reservada electrónicamente o en oficina; esperando recogida física o entrada en hub. | «Etiqueta creada» |
| `Picked Up`, `Pickup Completed` | DTDC recogió el paquete al remitente. | «Admitido por el transportista» |
| `In Transit`, `On The Way`, `At Processing Centre`, `At Destination`, `Reattempt Initiated`, `Arrived at ...`, `Dispatched from ...` | En camino entre centros de clasificación de DTDC, llegado al centro de destino o en cola para nuevo intento. | «En tránsito» |
| `Out For Delivery` | Cargado en el repartidor local para entrega a domicilio hoy. | «En reparto» |
| `Undelivered` | No se pudo completar el intento de entrega (por ejemplo ausencia o problema de dirección). | «Intento de entrega» |
| `RTO Booked`, `Return Approval awaited`, `Return Initiated` / `Return - Initiated` | Activada la devolución al origen (RTO) para enviar el paquete de vuelta al remitente. | «Incidencia» |
| `Delivered` (tramo de ida) | Entregado al destinatario. | «Entregado» |
| `Delivered` (en un tramo `RTO`) | Devuelto y entregado de nuevo al remitente original. | «Devuelto al remitente» |

**Atención a las devoluciones `RTO`.** Cuando un envío no puede entregarse, DTDC abre un tramo Return To Origin (`RTO`) que incluye su propio escaneo `Delivered` al volver al remitente. Comprueba si el último tramo es de ida o `RTO` antes de asumir que fue entregado en tu domicilio.

## Servicios nacionales e internacionales de DTDC

Según [DTDC Express Parcel](https://www.dtdc.com/express-parcel/), [DTDC International Shipping](https://www.dtdc.com/international-shipping/), [About Us](https://www.dtdc.com/about-us/) y las [FAQ de DTDC](https://www.dtdc.com/faq/):

- **Servicios nacionales en la India:** *Express Parcel* (entrega diaria a fecha definida en más de 15.300 códigos postales con contra reembolso, autenticación de entrega y logística inversa), *Express Premium* (urgente al día siguiente), *AirMax*, *Raftaar*, *Personal Courier* y B2B *3PL* / *LTL*.
- **Domingos y festivos:** las [FAQ de DTDC](https://www.dtdc.com/faq/) señalan que algunas oficinas locales de DTDC operan domingos y festivos según el horario de cada sucursal.
- **Plazos internacionales (*DTDC WorldWyde*):** cubre más de 220 destinos con despacho aduanero propio y gestión digital KYC/CSB-V — *WorldWyde Express Premium* (**4–5 días**), *WorldWyde Express Standard* (**7–8 días**), *WorldWyde Imports Standard* (**10–12 días**) y *WorldWyde B2C Standard* (**15–20 días**). El plazo real depende también de las aduanas de destino; consulta [paquetes retenidos en aduanas](guide:customs).

Las páginas de servicios de DTDC no publican un número fijo de intentos de entrega ni días de custodia. Un intento fallido aparece como `Undelivered` y uno nuevo como `Reattempt Initiated`.

## Paquete DTDC retrasado, no entregado o extraviado

En compras de comercio electrónico, contacta primero con el vendedor online para que autorice corregir la dirección, reprogramar o reembolsar, especialmente si aparece `Return Approval awaited` o `RTO Booked`.

- **Cambiar dirección, reprogramar o recoger en oficina:** la página [Track Your Shipment](https://www.dtdc.com/track-your-shipment/) permite abrir una disputa, chatear con soporte o pedir llamada, y el formulario de [Customer Care](https://www.dtdc.com/customer-care/) admite solicitudes de nueva dirección o teléfono, nueva fecha de entrega o recogida propia.
- **Marcado `Delivered` pero no recibido:** comprueba primero si se trata de un retorno `RTO` al remitente; si es una entrega de ida, pregunta a convivientes, vecinos y seguridad del edificio, y abre enseguida una incidencia ante DTDC Customer Care y tu vendedor.
- Consulta [por qué deja de actualizarse el seguimiento](guide:tracking-not-updating), [entregado pero no recibido](guide:delivered-not-received), [paquete dañado](guide:damaged-parcel), [paquete perdido](guide:lost-parcel) y [devuelto al remitente](guide:returned-to-sender).

## Atención al cliente y contactos oficiales de DTDC

Contactos publicados en [DTDC Customer Care](https://www.dtdc.com/customer-care/) y [dtdc.com](https://www.dtdc.com/in/):

- **Teléfono de atención:** `+91-9606 911 811`.
- **Correo de soporte:** `customersupport@dtdc.com`.
- **Soporte online:** formulario de consultas/disputas en [dtdc.com/customer-care/](https://www.dtdc.com/customer-care/), asistente virtual **DIVA** en la web y app móvil **MyDTDC**.

> DTDC advierte de forma destacada en [dtdc.com](https://www.dtdc.com/in/) que **`www.dtdc.com` es su único sitio web oficial** y rechaza responsabilidad por ofertas, teléfonos o pagos realizados en páginas imitadoras. Nunca pagues tarifas ni compartas códigos OTP mediante enlaces desconocidos. Consulta [estafas por SMS de entrega](guide:delivery-scam-texts).

## Sigue un paquete de DTDC con Peek

[Peek](/) reconoce automáticamente `N` + 8 dígitos (`N48291736`, inventado) y `7D` / `7X` + 9 dígitos (`7D482917364`, `7X482917364`) como DTDC con alta confianza, y también enlaces pegados de `dtdc.com` con `trackingId=` o `awb=`. Para códigos alfanuméricos de 9 u 11 caracteres con otras letras iniciales, pulsa «Cambiar» al añadirlo o «Cambiar transportista» en la ficha del paquete y selecciona DTDC.

Peek consulta a DTDC en dos pasos: primero mediante el flujo anónimo de MyDTDC y, si hace falta, en la página de seguimiento de `dtdc.com`. Muestra la fase («Etiqueta creada», «Admitido por el transportista», «En tránsito», «En reparto», «Intento de entrega», «Incidencia», «Devuelto al remitente», «Entregado»), el texto de cada escaneo de DTDC, el centro o sucursal y marcas de tiempo en hora estándar de la India (`Asia/Kolkata`) convertidas a la hora de tu dispositivo. De forma clave, cuando DTDC marca `Delivered` en un tramo `RTO`, Peek lo muestra como «Devuelto al remitente» en vez de «Entregado». Peek descarta direcciones postales, teléfonos/contactos, metadatos de reserva y pesos sin etiqueta; el seguimiento de DTDC no proporciona fecha estimada ni datos de punto de recogida.

Mientras tienes el paquete abierto en Peek, o el día de entrega con las notificaciones activadas, Peek comprueba hasta cada 10 minutos; el día en que DTDC muestra `Out For Delivery`, cada 2 minutos desde las 6:00 hora de la India, mientras su propio sistema responda. En el resto de casos comprueba cada hora. Tras 12 horas solo en «Etiqueta creada» o «Admitido por el transportista», o tras dos días sin novedades, comprueba cada hora sea cual sea tu forma de seguirlo, y cada 6 horas tras siete días en «Etiqueta creada». Cuando el seguimiento de DTDC no responde y Peek sigue el paquete únicamente mediante servicios de seguimiento, comprueba cada hora, cada 3 horas tras un día sin cambios y hasta cada 15 minutos el día de reparto mientras lo tengas abierto o tengas las notificaciones activadas.

Cuando el último escaneo tiene cuatro días y el paquete sigue en «Etiqueta creada», «Admitido por el transportista» o «En tránsito», Peek lo marca con la fecha de ese escaneo, por ejemplo «Sin novedades desde el lun, 12 oct». La ficha muestra entonces: «Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a DTDC.», con un enlace «Pide ayuda a DTDC» a su página de Customer Care. Tras tres semanas sin ningún escaneo nuevo, dice: «Nada nuevo desde hace más de tres semanas. Si el paquete aún no ha llegado, contacta con el vendedor. Peek sigue preguntando a DTDC.»

Puedes elegir los avisos «Cada escaneo», «Pasos importantes» o «Solo la entrega». Peek solo supervisa el seguimiento y no puede reprogramar una entrega de DTDC, cambiar la dirección ni abrir una disputa por ti.

## Preguntas sobre el seguimiento de DTDC

### ¿Cómo es un número de seguimiento (AWB) de DTDC?

Los números habituales de DTDC tienen 9 caracteres que empiezan por `N` (`N48291736`, inventado) u 11 caracteres que empiezan por `7D` o `7X` (`7D482917364`, `7X482917364`), aunque DTDC también usa otros prefijos de una sola letra de 9 y 11 caracteres.

### ¿Qué significan `RTO Booked` o `Return Initiated` en el seguimiento de DTDC?

`RTO` significa Return To Origin (devolución a origen). Indica que el paquete no pudo entregarse (o se solicitó autorización de retorno) y vuelve hacia el remitente. Contacta de inmediato con el vendedor si aún deseas el pedido.

### ¿Cuánto tarda un envío internacional de DTDC?

En [dtdc.com/international-shipping/](https://www.dtdc.com/international-shipping/), DTDC indica 4–5 días para *WorldWyde Express Premium*, 7–8 días para *Express Standard*, 10–12 días para *Imports Standard* y 15–20 días para *B2C Standard*, sujetos a despacho aduanero.

### ¿Cómo contacto con atención al cliente de DTDC?

Llama al `+91-9606 911 811`, escribe a `customersupport@dtdc.com` o utiliza el formulario de consultas y el asistente DIVA en [dtdc.com/customer-care/](https://www.dtdc.com/customer-care/).

:::sources
- [DTDC: Home](https://www.dtdc.com/in/) – aviso de único sitio oficial (`www.dtdc.com`), soporte DIVA y resumen de servicios
- [DTDC: About Us](https://www.dtdc.com/about-us/) – fundada el 26 de julio de 1990, sede en Bengaluru, 16.500+ socios, 96 % de cobertura poblacional en India, 7 países, 220+ destinos y 5,6 M+ usuarios MyDTDC
- [DTDC: Track Your Shipment](https://www.dtdc.com/track-your-shipment/) – búsqueda AWB / referencia, aviso AWB por SMS/correo, disputa, chat y llamada
- [DTDC: Express Parcel](https://www.dtdc.com/express-parcel/) – 15.300+ códigos postales, contra reembolso, autenticación de entrega y logística inversa
- [DTDC: International Shipping (WorldWyde)](https://www.dtdc.com/international-shipping/) – 220+ destinos, plazos WorldWyde (4–5, 7–8, 10–12, 15–20 días) y despacho CSB-V/KYC
- [DTDC: FAQ](https://www.dtdc.com/faq/) – disponibilidad de oficinas domingos/festivos y preguntas frecuentes
- [DTDC: Customer Care](https://www.dtdc.com/customer-care/) – `+91-9606 911 811`, `customersupport@dtdc.com`, formulario online para dirección, fecha y recogida propia (URL de ayuda enlazada por Peek)
- [Portal MyDTDC](https://web.mydtdc.in/) – portal web de seguimiento MyDTDC
- [Universal Parcel Scraper: dtdc](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/dtdc) – detección de alta confianza `N`/`7D`/`7X`, reglas de enlace `dtdc.com`, consulta en dos pasos, tratamiento de tramos RTO, zona horaria `Asia/Kolkata`, campos descartados y URL de ayuda
:::
