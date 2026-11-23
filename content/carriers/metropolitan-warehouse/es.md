---
title: Seguimiento Metropolitan Warehouse (MWD): muebles y ayuda
description: Rastrea un pedido de Metropolitan Warehouse & Delivery (MWD): número de 12 dígitos, estados, citas de entrega, plazo de 72 h por daños y contactos.
slug: seguimiento-metropolitan-warehouse
published: 2026-11-23
updated: 2026-11-23
---

Metropolitan Warehouse & Delivery (MWD) es una empresa familiar estadounidense de transporte de muebles y logística 3PL fundada en 1989 en Perth Amboy, Nueva Jersey. Con 47 centros y plataformas de cross-docking, entrega a domicilio para tiendas online, marcas de mobiliario y diseñadores en los 48 estados contiguos de EE. UU. Introduce el código de 12 dígitos en la [página de seguimiento de MWD](https://www.gomwd.com/track-a-shipment) o pégalo en el cuadro de arriba.

## Número de seguimiento de MWD: 12 dígitos

El [rastreador de MWD](https://main.gomwd.com/client/wixtrackorder) tiene un único campo, *Tracking #*, y solo acepta 12 dígitos (números inventados):

| Número | Qué es |
| --- | --- |
| `584927163048` | El número de seguimiento de MWD. En la ficha del pedido se muestra en grupos de cuatro: `5849 2716 3048`. |
| `POMS #`, `Client REF# 1`, `Client REF# 2`, `Carrier PRO #` | Otras referencias de la ficha del pedido (de MWD, de la tienda o de un transportista asociado). No sirven para rastrear. |

No pide código postal ni teléfono, aunque emplea reCAPTCHA y puede pedirte que escribas un captcha de texto. Cualquier valor distinto de 12 cifras devuelve `Please enter a valid tracking number of 12 digits.`, y un código que MWD no tiene devuelve `Tracking id does not exist.`. Compruébalo en el correo de la tienda o pídeselo al vendedor ([cómo encontrar tu número de seguimiento](guide:find-tracking-number)). MWD ofrece páginas de seguimiento con marca blanca, así que la tienda puede enviarte un enlace con su propio nombre.

## Qué significan los estados de Metropolitan Warehouse

El historial de MWD aparece en inglés, del evento más reciente al más antiguo, con fecha, mensaje, ciudad y estado. Estos son los estados registrados en [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/metropolitan-warehouse), en el orden habitual:

| Mensaje de MWD | En Peek | Qué significa para ti |
| --- | --- | --- |
| `Order registered` | Etiqueta creada | El pedido está registrado en MWD. |
| `Pickup document attached` | sin cambios | Documentación adjunta para la recogida; aún no se ha movido. |
| `Pickup scheduled for 11/02/2026` | Etiqueta creada | MWD reservó la recogida en el proveedor, pero todavía no tiene el mueble. |
| `Pickup completed, No exceptions` | Admitido por el transportista | MWD recogió el pedido sin incidencias señaladas. |
| `Order enroute to Metro-Hub` | En tránsito | En camino hacia un centro de MWD. |
| `Furniture Received by Metro-Hub` | En tránsito | Escaneado en un centro de MWD (con el nombre de la ciudad). |
| `Reached Delivery Hub` | En tránsito | Llegó al centro desde el que se hará el reparto. |
| `Delivery Scheduled for WEEK OF 11/09/2026` | sin cambios | Entrega prevista para esa semana; el día exacto llega con la cita. |
| `Delivery Rescheduled for WEEK OF 11/16/2026` | sin cambios | La entrega pasó a otra semana. |
| `Delivery Appointment Canceled` | sin cambios | La cita se canceló; suele seguir una nueva, si no consulta a la tienda o a MWD. |
| `Arrived at Delivery Location` | En reparto | El equipo de entrega ha llegado a tu dirección. |
| `Delivery Completed, No Exceptions` | Entregado | Entregado sin incidencias anotadas (revisa todo igualmente). |

En la web de MWD el centro aparece a veces como `Metro-<Warehouse>-Hub` con el nombre vacío; Peek lo limpia a `Metro-Hub`. No hay una línea previa de salida a reparto: el primer apunte del día de entrega es `Arrived at Delivery Location`, cuando la cuadrilla ya está en tu puerta.

## Cita, nivel de servicio y entrega fallida

**Cita y seguimiento en vivo.** En [White Glove Delivery](https://www.gomwd.com/services/white-glove-delivery) y [Services](https://www.gomwd.com/services), MWD explica que los clientes reservan su franja horaria mediante programación autoservicio, reciben recordatorios automáticos y el día de entrega obtienen un enlace en tiempo real con la ubicación del equipo y la hora estimada de llegada. La página de seguimiento muestra además la ventana de cita y la prueba de entrega.

**Qué incluye el reparto.** Depende del nivel contratado por la tienda (visible en *Service Type* en la ficha de MWD):

| Nivel de servicio | Qué hace el equipo |
| --- | --- |
| Full White Glove | Coloca el mueble en la habitación elegida, realiza montaje ligero y retira todo el embalaje. |
| White Glove (No Assembly) | Igual para muebles ya montados y alfombras, sin montaje. |
| Room of Choice | Lo introduce hasta la habitación indicada, sin montaje. |
| Threshold Delivery | Lo deja en la primera zona cubierta del domicilio (entrada o garaje), sin desembalar ni montar. |
| Doorstep Delivery | Lo deja fuera de la puerta sin firma, para envíos más ligeros. |
| Curbside Delivery | Lo deja junto a la acera o entrada de vehículos; el resto corre por tu cuenta. |

Si falta montaje o retirada de embalajes respecto a lo comprado, trátalo con la tienda vendedora.

**Si no estás en casa.** Las páginas públicas de MWD no explican cómo reprogramar una cita fallida, así que avisa antes del día previsto. Sus [condiciones de transporte](https://sso.gomwd.com/Account/TermsandConditions) establecen que, si se rechaza la entrega o resulta imposible completarla, la responsabilidad pasa a ser la de un depositario: tras intentar avisar por teléfono o vía electrónica, pueden cobrarse gastos de almacenaje a partir del siguiente día hábil; si no hay instrucciones en 48 horas se envía un segundo aviso final, y tras 10 días más MWD puede subastar la mercancía. Más ayuda en [entregas fallidas](guide:missed-delivery).

## Pedido MWD dañado, retrasado o extraviado

**Daños.** Revisa cada pieza antes de que se marche el equipo. Las [condiciones de transporte](https://sso.gomwd.com/Account/TermsandConditions) del portal fijan **72 horas** tras la entrega para presentar una reclamación por escrito por pérdida o daños y facultan a MWD a reparar o sustituir el artículo. Salvo pacto distinto o cobertura *Enhanced Valuation* contratada por el remitente, la responsabilidad máxima por defecto se limita a 500 USD por envío, y quedan excluidos los daños ocultos en bultos preembalados si el embalaje no muestra golpes visibles. Como la tienda es el cliente de MWD, avísala de inmediato. Ver [paquete dañado](guide:damaged-parcel).

**Retraso.** Mientras el historial se quede en `Pickup scheduled for`, MWD todavía no ha recogido el mueble. Según la [guía de la FTC sobre la regla de pedidos por correo, internet o teléfono](https://www.ftc.gov/business-guidance/resources/business-guide-ftcs-mail-internet-or-telephone-order-merchandise-rule), un pedido solo cuenta como enviado cuando está en manos del transportista: si un vendedor no puede enviar en el plazo anunciado —o en **30 días** si no indicó ninguno— debe pedir tu consentimiento al retraso y ofrecer la cancelación con reembolso íntegro y rápido. Si no hay cambios días después de la recogida, consulta [seguimiento que no se actualiza](guide:tracking-not-updating).

**No entrega o extravío.** Las mismas condiciones conceden **30 días**, transcurrido un plazo razonable de entrega, para reclamar por escrito la no entrega (y 6 meses para demandar tras una denegación escrita). Contacta con la tienda y MWD, y revisa [entregado pero no recibido](guide:delivered-not-received) y [paquete perdido](guide:lost-parcel).

## Contacto de Metropolitan Warehouse & Delivery

Según la [página de contacto de MWD](https://www.gomwd.com/contact-us):

- **Teléfono de soporte:** (800) 300-7417 — equipo disponible de lunes a viernes (sin horario concreto publicado).
- **Correo general:** `comments@gomwd.com`.
- **Oficinas:** sede central en Perth Amboy, Nueva Jersey (según [About Us](https://www.gomwd.com/about-metropolitan) y [Services](https://www.gomwd.com/services)), y *Corporate Office* publicada en la página de contacto en 310 Ramco Street, Suite 150, West Sacramento, CA 95691.

El apartado *Info About My Order* está pensado para clientes comerciales (las tiendas que envían con MWD); la tienda vendedora puede escalar incidencias al equipo de *Customer Success*.

## Seguir un pedido de MWD con Peek

[Peek](/) no puede identificar MWD solo por el formato, porque doce dígitos coinciden con muchos transportistas ([por qué se solapan los formatos](guide:tracking-number-formats)). Pega el número igualmente: Peek sigue más de 3.500 transportistas. **En Estados Unidos**, consulta a MWD entre los primeros transportistas de 12 dígitos —junto con [BRT](carrier:brt), [Colis Privé](carrier:colis-prive) y [Purolator](carrier:purolator) (si empieza por 0–6 con dígito de control válido)— y muestra «Metropolitan Warehouse & Delivery tiene este paquete.» cuando MWD lo gestiona. **Fuera de Estados Unidos**, Peek trata MWD como último recurso: pregunta antes a los demás transportistas de 12 dígitos y a los servicios de seguimiento, y solo consulta MWD si ninguno lo reconoce. Siempre puedes introducirlo en la página de MWD o elegir Metropolitan Warehouse & Delivery mediante «Cambiar» o «Cambiar transportista» (buscar «MWD» también funciona). Acepta espacios; pega los 12 dígitos directamente, porque Peek no tiene regla para enlaces de MWD.

Peek lee el rastreador de MWD: estado, historial con el texto inglés de MWD, ciudad y estado de cada evento en el mapa, la hora local tal como la publica MWD (sin zona horaria) y el peso convertido a kilogramos. Deja fuera tu ciudad y código postal de destino, referencias del pedido y del cliente, número PRO de un socio, número de bultos, volumen y la barra de progreso. Tampoco muestra fecha estimada, prueba de entrega ni ubicación en directo del equipo, ni rastrea transportistas asociados.

Peek consulta directamente a MWD hasta cada 10 minutos mientras mantienes abierto el pedido o el día de reparto con avisos activados, y cada 2 minutos a partir de las 6:00 hora local el día en que aparece `Arrived at Delivery Location`; si no, cada hora. Tras 12 horas en «Etiqueta creada» o «Admitido por el transportista», o tras dos días sin novedades, consulta cada hora incluso con la pantalla abierta, y cada 6 horas tras siete días todavía en «Etiqueta creada». Mediante servicios de seguimiento, revisa cada hora, cada 3 horas tras un día sin cambios y hasta cada 15 minutos el día de entrega.

Después de cuatro días sin un nuevo escaneo en «En tránsito», Peek marca el retraso (por ejemplo, «Sin novedades desde el lun, 12 oct»), muestra «Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a Metropolitan Warehouse & Delivery.» y ofrece «Pide ayuda a Metropolitan Warehouse & Delivery». Pasadas más de tres semanas sin novedades, muestra: «Nada nuevo desde hace más de tres semanas. Si el paquete aún no ha llegado, contacta con el vendedor. Peek sigue preguntando a Metropolitan Warehouse & Delivery.»

Puedes activar alertas para «Cada escaneo», «Pasos importantes» o «Solo la entrega», que en MWD empieza con `Arrived at Delivery Location`. Peek no puede concertar ni cambiar citas, abrir reclamaciones ni contactar con MWD o la tienda por ti.

## Preguntas sobre el seguimiento de Metropolitan Warehouse

### ¿Qué es Metropolitan Warehouse & Delivery?

Una empresa familiar de transporte de muebles y almacenes 3PL fundada en 1989 en Perth Amboy (Nueva Jersey), con 47 centros en los 48 estados contiguos. La contratan tiendas, marcas de muebles y diseñadores; los particulares no pueden reservarla por su cuenta.

### ¿Qué significa «Delivery Scheduled for WEEK OF»?

MWD ha planificado la entrega para la semana de esa fecha. El día y la franja exactos llegan al concertar la cita. `Delivery Rescheduled for WEEK OF` indica que pasó a otra semana.

### ¿Metropolitan Warehouse monta los muebles?

Solo con *Full White Glove*, que incluye montaje ligero y retirada de embalajes. Los demás niveles —incluido *White Glove (No Assembly)*— no montan muebles. Comprueba *Service Type* en tu ficha de pedido de MWD.

### ¿Cómo reprogramo mi entrega de Metropolitan Warehouse?

Las páginas de MWD explican cómo reservar la franja inicial por autoservicio, pero no cómo cambiarla. Llama al (800) 300-7417 de lunes a viernes o contacta con la tienda.

### ¿Cuánto tiempo tengo para informar daños a MWD?

Las condiciones de transporte del portal cliente establecen 72 horas desde la entrega para presentar una reclamación escrita por pérdida o daños. Como la tienda es el cliente de MWD, avísala también enseguida.

:::sources
- [Metropolitan Warehouse & Delivery: Inicio](https://www.gomwd.com/) – desde 1989, red de 47 centros, teléfono de soporte
- [MWD: About Us](https://www.gomwd.com/about-metropolitan) – empresa familiar, sede en Perth Amboy, 48 estados contiguos
- [MWD: Track a Shipment](https://www.gomwd.com/track-a-shipment) – seguimiento, ventana de cita y prueba de entrega
- [MWD: Track Your Order](https://main.gomwd.com/client/wixtrackorder) – campo único de 12 dígitos, mensaje de error, reCAPTCHA / captcha de texto
- [MWD: White Glove Delivery](https://www.gomwd.com/services/white-glove-delivery) – niveles de servicio, reserva autoservicio, recordatorios y seguimiento en vivo
- [MWD: Services](https://www.gomwd.com/services) – Doorstep Delivery, 5 centros 3PL completos, páginas de seguimiento con marca blanca
- [MWD: Contact Us](https://www.gomwd.com/contact-us) – soporte (800) 300-7417 lunes–viernes, comments@gomwd.com, Corporate Office West Sacramento, ruta para clientes
- [MWD: Terms & Conditions](https://sso.gomwd.com/Account/TermsandConditions) – reclamaciones 72 h (daños) y 30 días (no entrega), límite de 500 USD, almacenaje por entrega fallida
- [FTC: Business Guide to the Mail, Internet, or Telephone Order Merchandise Rule](https://www.ftc.gov/business-guidance/resources/business-guide-ftcs-mail-internet-or-telephone-order-merchandise-rule) – entrega al transportista, regla de 30 días, consentimiento de retraso y reembolso
- [Universal Parcel Scraper: Metropolitan Warehouse & Delivery](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/metropolitan-warehouse) – formato de 12 dígitos, mensaje de número desconocido, estados y campos leídos por Peek
:::
