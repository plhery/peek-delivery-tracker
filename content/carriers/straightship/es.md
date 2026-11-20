---
title: Seguimiento de Straightship: números STRTD y entregas
description: Rastrea un paquete de Straightship en Canadá: formatos STRTD, STRCA y STSH, significado de cada estado, aduanas CBSA, pedidos SHEIN y atención al cliente.
slug: seguimiento-straightship
published: 2026-11-18
updated: 2026-11-20
---

Straightship (Straightship Solutions Limited) es una empresa de logística con sede en Burnaby, Columbia Británica (**Canadá**), que transporta mercancías de Asia a Norteamérica y reparte paquetes en todo Canadá con su propia flota de conductores. En su web incluye a SHEIN entre sus socios. Si un pedido online viene con un número que empieza por `STRTD` o `STRCA`, lo gestiona Straightship; uno que empiece por `STSH` también puede ser suyo. Puedes rastrearlo en [straightship.com](https://www.straightship.com/new/), en el cuadro «Track Your Shipment», o directamente en el buscador superior. La tienda te facilita el código en el correo de envío o en la página del pedido.

## Número de seguimiento de Straightship: cómo es

Straightship no publica una guía de formatos. Los códigos que reconoce Peek tienen estas estructuras, con cifras inventadas:

| Número | Qué es |
| --- | --- |
| `STRTD482910573` | `STRTD` y 9 dígitos. |
| `STRTD4829105736491827` | `STRTD` y 16 dígitos (también existen de 17). |
| `STRCA4829105736491` | `STRCA` y 13 dígitos. |
| `STSHYW48291057364` | `STSH`, dos letras y 11 dígitos. |

Las minúsculas y los espacios no importan en Peek. Cuando el rastreador de Straightship no tiene registrado un número, responde `No results found!`: compruébalo con el correo de confirmación de la tienda. ¿No encuentras el número por ninguna parte? Consulta [cómo encontrar tu número de seguimiento](guide:find-tracking-number) y, si es una compra de SHEIN, [seguimiento de pedidos SHEIN](guide:shein-orders).

## Estados de seguimiento de Straightship explicados

El localizador de Straightship está en inglés. Muestra arriba un resumen con el campo «Package Status» y debajo el historial con la hora, el estado y la localidad de cada lectura. Estas son las líneas habituales en el orden en que suele pasar por ellas un envío en **Canadá**:

| Lo que dice Straightship | Qué significa para ti |
| --- | --- |
| `Package information is created in the system` | La tienda creó la etiqueta. Straightship aún no tiene el paquete: sigue con el vendedor o viajando desde Asia. |
| `Package Received with Origin Scan warehouse` | Primer escaneo de Straightship en Canadá, con ciudad y provincia. |
| `Package is received at a Straightship station` | Ha llegado a una de las delegaciones de Straightship (en páginas antiguas aparece `Arrived at Facility`). |
| `Package arrived at transit station` | En una estación de tránsito de camino a tu delegación local. |
| `Package is in transit to a Straightship station` | En ruta entre delegaciones, antes de llegar a la que reparte. |
| `Loading` | Cargándose en la delegación antes de salir a reparto; puede repetirse dos veces con segundos de diferencia. |
| `Package will be delivered before 9:00pm` | En reparto hoy: la entrega o un intento se producirá en el mismo día. |
| `Attempted Delivery` | El repartidor acudió pero no pudo entregarlo. |
| `Shipment delayed` | Retraso sin motivo detallado en el rastreador. |
| `Delivered` | Entregado, con ciudad y provincia (mostrado como «Entregado» en Peek). |
| `Package is being returned to shipper` | De vuelta hacia el remitente. |

Dos mensajes de intento fallido indican qué hacer: `An attempt was made to deliver your package-consignee closed (please e-mail SS@straightship.com to make arrangement)` significa que el lugar de entrega estaba cerrado; la misma frase con `unable to leave package at safe location` en lugar de `consignee closed` indica que el conductor no encontró un sitio seguro donde dejarlo. Ambos te piden escribir a `SS@straightship.com` para concertar el siguiente intento.

Tres detalles suelen confundir:

- **El resumen va con retraso.** El campo «Package Status» puede seguir marcando `ReadyForShipping` (o `Ready For Shipping`) mientras el paquete ya avanza entre almacenes. Fíjate siempre en la línea más reciente del historial inferior; los valores posteriores del resumen son `Shipped`, `Delivered` y `ReturnedToSender`.
- **«In progress» no significa en reparto.** `Delivery is in progress` y `Your order is in Progress` aparecen tras una entrega que no llegó a completarse, a veces días después y antes de una devolución. Escribe a Straightship si aparecen.
- **Las horas están en UTC.** Debajo del historial, el rastreador indica que todas las horas están en Tiempo Universal Coordinado (UTC): una lectura nocturna en Canadá muestra ya la fecha del día siguiente.

Un paquete en devolución todavía puede volver a escanearse en una estación y salir de nuevo a reparto: `Package is being returned to shipper` no siempre es el último movimiento.

## Reparto de Straightship en Canadá: quién entrega

El rastreador de Straightship corresponde a **Canadá** (su encabezado dice «Canada B2C Shipment Tracking»). Straightship afirma que su red propia cubre más del 95 % de la población de Canadá y el 90 % de los códigos postales canadienses mediante más de 85 centros de distribución, y su mensaje de reparto promete la entrega antes de las 21:00 (`before 9:00pm`). También reparte en **Canadá** para la empresa logística china [JS Express](carrier:js-express): el seguimiento de JS Express refleja los escaneos canadienses de Straightship y, cuando el registro de JS Express incluye un código `STRTD` o `STRCA`, Peek consulta también a Straightship.

Para los paquetes que introduce en **Canadá** desde Estados Unidos, Straightship ofrece en su página en inglés varias opciones de última milla —su propia red, Canada Post, Purolator o [UPS](carrier:ups)—, mientras que su versión en chino enumera su propia red, Canada Post, [FedEx](carrier:fedex) o UPS. Straightship indica que este servicio entrega en un plazo de 6 a 10 días y despacha aduanas en 24 horas. Su rastreador solo muestra las estaciones propias de Straightship y no nombra a otro transportista ni un segundo código.

En **Canadá**, Straightship no publica cuántos intentos realiza, si deja aviso en papel ni cuánto tiempo guarda en almacén un envío no entregado:

- **Tras un intento fallido**, envía un correo a `SS@straightship.com` con tu número de seguimiento, tal como pide el mensaje del rastreador.
- **Para cambiar la dirección o el teléfono**, usa su formulario de contacto y elige «Address and/or contact details update».
- **Prueba de entrega:** su página principal señala que el rastreador muestra el estado más reciente y la foto de entrega cuando está disponible («POD image (Where available)»).

## Aduanas en Canadá para un paquete de Straightship desde Asia

En **Canadá**, Straightship tramita la aduana para las empresas remitentes y su página de servicios en chino indica que puede cobrar los impuestos en Canadá (`DDU`). Si te piden pagar por un envío de Straightship, consulta primero al vendedor: la ayuda de SHEIN Canadá confirma que todos los pedidos enviados a **Canadá** viajan con derechos pagados (`DDP`, *delivered duty paid*), sin cargos aduaneros para el comprador. Otros localizadores pueden mostrar `Customs Released` antes del primer escaneo en Canadá (algo que la web de Straightship no muestra), así como `Customs Held For Examination` durante una inspección o `Customs Rejected`, que no siempre es definitivo: en un envío seguido por Peek, la aduana revisó el paquete y lo liberó dos días más tarde. Peek muestra ambas líneas como «En aduanas»; si alguna se mantiene varios días, avisa al vendedor.

Lo que corresponde pagar en **Canadá** depende del valor y del país de procedencia según los umbrales de mensajería de la Agencia de Servicios Fronterizos de Canadá (CBSA): por mensajería, hasta 20 CAD desde China o cualquier país distinto de EE. UU. y México no se pagan aranceles ni impuestos, y por encima sí se aplican ambos. Desde Estados Unidos o México no se paga nada hasta 40 CAD, solo impuestos entre 40,01 CAD y 150 CAD, y aranceles e impuestos por encima de 150 CAD (salvo mercancías sujetas a reglas especiales como alcohol o tabaco).

En los envíos por mensajería hacia **Canadá**, la CBSA permite al transportista autorizado liquidar aranceles e impuestos en tu nombre: según explica la CBSA, la empresa de mensajería se pondrá en contacto contigo antes de la entrega para gestionar el pago y retendrá el paquete hasta que abones el importe, mientras que las comisiones de gestión aduanera del mensajero son independientes de los tributos y ajenas al control de la CBSA. Además, la CBSA nunca llama ni envía SMS o correos electrónicos para pedir tu número de seguro social (`SIN`) ni el número de tu tarjeta bancaria: cualquier mensaje así es una estafa. Más información en [paquetes retenidos en aduanas](guide:customs).

## Paquete de Straightship que no llega, perdido o devuelto

**Quién reclama.** Los clientes de Straightship son las tiendas y plataformas remitentes, y la empresa no publica ninguna vía de indemnización para destinatarios. Para pedir un reembolso o un reenvío, acude a la tienda (para SHEIN, consulta [seguimiento de pedidos SHEIN](guide:shein-orders)).

**Parado antes de Canadá.** `Package information is created in the system` sin líneas posteriores significa que el paquete aún no ha llegado a manos de Straightship en Canadá; puede seguir en tránsito desde Asia. Pregunta a la tienda cuándo salió y revisa [seguimiento de paquetes desde China](guide:tracking-from-china).

**Sin movimientos en Canadá.** Si pasan varios días sin cambios tras un escaneo en delegación o aparece `Shipment delayed`, envía el formulario de contacto de Straightship con el motivo «Delivery Status Enquiry». Más detalles en [por qué no se actualiza el seguimiento](guide:tracking-not-updating).

**Consta como entregado, pero no aparece.** Busca primero si hay foto de entrega en el rastreador de Straightship (no siempre la hay) y revisa vecinos, cuarto de correo del edificio y rincones resguardados junto a tu puerta. En **Canadá**, SHEIN añade que en raras ocasiones un paquete puede figurar como entregado hasta 48 horas antes de llegar realmente; después, usa «Delivered Order Not Received» en el formulario de Straightship y, si es un pedido de SHEIN, pulsa el botón «Parcel Delivered, Not Received» dentro de «My orders» para iniciar la solicitud de reembolso. Consulta [entregado pero no recibido](guide:delivered-not-received).

**Devuelto.** `Package is being returned to shipper` indica que vuelve al vendedor: pide a la tienda el reembolso o un nuevo envío y consulta [devuelto al remitente](guide:returned-to-sender).

## Teléfono y atención al cliente de Straightship

En su web oficial, Straightship publica:

- **Teléfono en Canadá:** `+1 (800) 452-3304`, de lunes a viernes de 08:00 a 21:00 y sábados de 08:00 a 16:00, hora del Este (`EST`); en la portada figura también `+31 800 0200244` para los Países Bajos (`NL`).
- **Correo electrónico:** `SS@straightship.com`, la dirección indicada en los avisos de intento fallido.
- **Formulario de contacto:** en [su página de contacto](https://www.straightship.com/new/contact.html), donde es obligatorio indicar el número de seguimiento. Straightship pide contar con un plazo de 2 días laborables para recibir respuesta.

Para gestiones del pedido en sí, devoluciones o reembolsos, contacta con el vendedor.

## Seguir un paquete de Straightship con Peek

[Peek](/) reconoce a Straightship al instante a partir de un código `STRTD` o `STRCA` con las formas anteriores, que ningún otro transportista de su catálogo comparte. Con un código `STSH` no puede decidir solo por el formato, ya que coincide con algunos números de Colis Privé: Peek consulta a Straightship si tiene el paquete y lo selecciona cuando responde que sí. Si no aparece elegido, toca «Cambiar» y selecciónalo en «Encaja con este número». También puedes pegar un enlace de seguimiento de `straightship.com` (`/track-your-shipment/tracking-id/` seguido del código). Peek solo consulta en Straightship estos formatos conocidos: si eliges Straightship con «Cambiar» para otro formato distinto, Peek avisa «Este transportista no emite números de seguimiento como este.»; usa entonces el buscador propio de Straightship.

Peek lee el rastreador oficial de Straightship y muestra el estado, el historial con ciudad y provincia de cada lectura y la hora de entrega. Interpreta las horas como UTC, tal como advierte la propia web, y las convierte a la zona horaria de tu dispositivo («Horas en tu zona horaria: …» en peektracker.com y en las apps de iPhone y Android). `Package will be delivered before 9:00pm` cuenta como «En reparto», los avisos de intento como «Intento de entrega» y `Delivery is in progress` como «En tránsito». Peek no muestra fecha estimada de entrega y omite la referencia del cliente y la foto de entrega, disponibles en la web de Straightship. Como Straightship no nombra a otro operador, Peek sigue el código de Straightship hasta el final.

Mientras tienes el paquete abierto, o el día de entrega con las notificaciones activadas, Peek comprueba hasta cada 10 minutos —y hasta cada 2 minutos desde las 6:00 en hora del Pacífico el día en que Straightship escanea el paquete en reparto, incluidas las entregas nocturnas— siempre que responda el rastreador de Straightship; mediante servicios de seguimiento únicamente, comprueba cada hora, cada 3 horas tras un día sin movimientos y hasta cada 15 minutos el día de reparto mientras el paquete esté abierto o con alertas activas. El resto del tiempo, así como tras dos días sin novedades (12 horas en «Etiqueta creada» o «Admitido por el transportista»), comprueba cada hora. Puedes activar avisos de «Cada escaneo», «Pasos importantes» o «Solo la entrega». Peek no puede contactar con Straightship ni con el vendedor por ti.

## Preguntas frecuentes sobre el seguimiento de Straightship

### ¿Es fiable Straightship?

Sí. Straightship Solutions Limited es una empresa logística ubicada en Burnaby, Columbia Británica (Canadá). Su sitio web menciona a SHEIN, Canada Post, FedEx, COSCO e [YTO](carrier:yto) entre sus socios, cita a Walmart y Costco entre los comercios con los que trabaja y declara contar con más de 85 centros de reparto en Canadá y gestionar más de 500 000 paquetes de Amazon al día.

### ¿Qué significa «Package will be delivered before 9:00pm»?

Que tu paquete está hoy en reparto en Canadá. El conductor lo entregará o registrará un intento ese mismo día; si nadie puede recogerlo, aparecerá una línea pidiendo escribir a `SS@straightship.com`.

### ¿Por qué Straightship sigue diciendo «ReadyForShipping»?

Porque el resumen «Package Status» puede quedarse fijo en `ReadyForShipping` mientras el paquete ya se mueve entre las delegaciones de Straightship. Guíate por la línea superior del historial detallado que aparece debajo.

### ¿Cuánto tarda en entregar Straightship?

Straightship no publica plazos para envíos procedentes de Asia; para su servicio desde Estados Unidos hacia Canadá indica de 6 a 10 días. Fíjate en la estimación de entrega que te dio la tienda al comprar; cuando aparece `Package will be delivered before 9:00pm`, el repartidor pasa ese mismo día.

### ¿Quién reparte los paquetes de Straightship en Canadá?

Los propios conductores de Straightship desde sus delegaciones en Canadá según muestra su rastreador, que no menciona a ningún otro transportista. Para los envíos de Estados Unidos a Canadá, sus páginas comerciales listan además a Canada Post y UPS, junto con Purolator en la página inglesa y FedEx en la china.

:::sources
- [Straightship: Home (en inglés)](https://www.straightship.com/new/) – envíos de Asia a Norteamérica, flota propia, cuadro de seguimiento, imagen POD, socios, teléfono, correo y horario EST
- [Straightship: About (en inglés)](https://www.straightship.com/new/about.html) – logística puerta a puerta de Asia a Norteamérica
- [Straightship: Services (en inglés)](https://www.straightship.com/new/services.html) – red propia que cubre más del 95 % de la población y el 90 % de los códigos postales de Canadá, servicio EE. UU.–Canadá en 6 a 10 días, aduana en 24 horas y opciones Canada Post, Purolator y UPS
- [Straightship: 服务 (servicios, en chino)](https://www.straightship.com/new/cn/services.html) – el mismo servicio EE. UU.–Canadá con su red propia, Canada Post, FedEx o UPS en última milla
- [Straightship: Resources (en inglés)](https://www.straightship.com/new/resources.html) – más de 85 centros de reparto en Canadá, más de 500 000 paquetes de Amazon al día, Walmart y Costco
- [Straightship: Contact (en inglés)](https://www.straightship.com/new/contact.html) – motivos de consulta, número de seguimiento obligatorio y respuesta en 2 días laborables
- [Straightship: Privacy Policy (en inglés)](https://www.straightship.com/new/privacy.html) – denominación Straightship Solutions Limited y sedes en Burnaby (Columbia Británica)
- [CBSA: Importing casual goods by courier (en inglés)](https://www.cbsa-asfc.gc.ca/import/courier/lvs-efv/prsn-eng.html) – liquidación de aranceles e impuestos por empresas de mensajería en Canadá y gastos de gestión ajenos al control de la CBSA
- [CBSA: Protect yourself against fraud (en inglés)](https://www.cbsa-asfc.gc.ca/security-securite/pyaf-pvcf-eng.html) – cobro gestionado antes de la entrega por la mensajería y ausencia de solicitudes de SIN o tarjeta por parte de la CBSA
- [CBSA: Low-value shipment thresholds (en inglés)](https://www.cbsa-asfc.gc.ca/services/cusma-aceum/lvs-efv-eng.html) – franquicias por mensajería en Canadá (20 CAD desde otros países, 40 CAD y 150 CAD desde EE. UU. y México)
- [SHEIN Canada: Are there any additional fee for customs? (en inglés)](https://ca.shein.com/faq/detail?id=2307) – pedidos de SHEIN a Canadá enviados con derechos pagados (DDP)
- [SHEIN Canada: My order is marked as delivered, but I haven't received it (en inglés)](https://ca.shein.com/faq/detail?id=2362) – aviso de entregado hasta 48 horas antes y botón Parcel Delivered, Not Received
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formatos `STRTD`, `STRCA` y `STSH`, textos de escaneo, nota UTC, valores del resumen, `Customs Released` y retenciones aduaneras retransmitidas, traspaso desde JS Express y campos mostrados en Peek
:::
