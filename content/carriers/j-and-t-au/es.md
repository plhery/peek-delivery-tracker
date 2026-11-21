---
title: Seguimiento J&T Express Australia: códigos JTAU y estados
description: Rastrea un paquete de J&T Express Australia: formato JTAU de 12 dígitos, estados en inglés, foto de entrega, teléfono 13 18 18 y Peek.
slug: seguimiento-jt-express-australia
published: 2026-11-21
updated: 2026-11-21
---

J&T Express Australia (*J&T Express Australia Pty Ltd*, con domicilio social en Villawood, Nueva Gales del Sur) es la filial australiana del grupo J&T Express, presente en el Sudeste Asiático y China continental. Reparte paquetes en Australia para plataformas de comercio electrónico, marcas y tiendas, incluidos pedidos internacionales una vez despachados de aduana. Sus códigos empiezan por `JTAU`: pega el tuyo arriba o consúltalo en el [rastreador oficial de J&T Australia](https://www.jtexpress.au/tracking). La tienda te lo facilita en el correo de envío o en la página del pedido.

## Número de seguimiento de J&T Express Australia: cómo es

Un albarán (*waybill*) australiano de J&T consta de `JTAU` seguido de 12 dígitos (16 caracteres en total), el único formato que Peek reconoce para esta red (ejemplos inventados):

| Número (inventado) | Qué es |
| --- | --- |
| `JTAU482910573649` | `JTAU` y 12 cifras: un paquete de J&T Express Australia que Peek identifica al instante. |
| `JTAU48291057364` | Falta un dígito. Si ningún operador responde, `peektracker.com` muestra «Empieza como un número de seguimiento de J&T Express Australia, pero le falta un carácter: puede que tenga una errata. Compáralo con tu mensaje de envío.» |
| `JTDE482910573649` | `JTDE` y 12 dígitos: [J&T Express Alemania](carrier:j-and-t-de). |
| `JTE482910573649` | `JTE` y 12 dígitos: [J&T Express Middle East](carrier:j-and-t-middle-east) (Arabia Saudí, EAU y Kuwait). `JTUK` (`JTUK482910573649`) es J&T Express UK. |
| `JT4829105736491` | `JT` y 13 dígitos: redes de J&T en el Sudeste Asiático y [J&T Express China](carrier:j-and-t-cn). |

Los espacios, guiones y minúsculas no importan en Peek (`jtau 4829 1057 3649` se lee igual). El catálogo también conoce al operador como *J&T Express (AU)*, *J&T Express AU*, *J&T Australia* y *JT Express Australia*.

**Dónde encontrarlo.** J&T recibe tus datos del remitente o marketplace: busca en el mensaje de expedición o en tu pedido ([cómo encontrar el número de seguimiento](guide:find-tracking-number)). En `jtexpress.au/tracking` puedes buscar hasta 10 códigos separados por espacios o comas; si no consta ninguno, responde `No tracking records yet`.

## Estados de seguimiento de J&T Express Australia explicados

El rastreador de J&T muestra en cada paso una hora y una frase sin ciudad ni almacén. Peek pide las frases en inglés (algunos pasos iniciales también existen en chino en el rastreador: `包裹已从清关行取件。`, `快件已揽收成功，运输准备中。`, `快件到达转运中心，做发往下一站的运输准备`, `快件从转运中心发往下一站`):

| Estado de J&T | Qué significa para ti | Peek muestra |
| --- | --- | --- |
| `The parcel has been picked up from customs clearance.` | Pedido importado: ya superó la aduana y J&T lo recogió del agente aduanero (primer escaneo de importación, no retención aduanera). | «Admitido por el transportista» |
| `The parcel has been successfully picked up and is being prepared for transportation.` | J&T tiene el paquete y lo prepara para el transporte. | «Admitido por el transportista» |
| `The parcel arrives at the distribution center and is prepared for transport to the next destination.` | Ha llegado a un centro de distribución de J&T rumbo al siguiente punto. | «En tránsito» |
| `The parcel is dispatched from the transit center to the next destination` | Ha salido de un centro de tránsito hacia la siguiente escala. | «En tránsito» |
| `Your parcel is out for delivery.` | En reparto hacia tu domicilio. | «En reparto» |
| `The building was closed when we arrived. We'll try again.` | El edificio estaba cerrado al llegar el repartidor; J&T volverá a intentarlo. | «Intento de entrega» |
| `We couldn't deliver - the address details appear to be incorrect. Please update your delivery address.` | No se pudo entregar por dirección incompleta o errónea; J&T pide corregirla. | «Intento de entrega» |
| `Your parcel has been delivered.` | Entregado. | «Entregado» |

Para compras internacionales, revisa [paquetes retenidos en aduanas](guide:customs). Cualquier otra frase se clasifica por sus palabras (como «En tránsito», salvo que el envío ya esté en reparto o en un paso posterior).

En el rastreador de J&T algunas frases terminan con el teléfono de atención o una invitación a llamarlo: Peek omite ese añadido, igual que el texto entre corchetes (`[…]`, `【…】`), los teléfonos y el nombre del repartidor o remitente. Cuando la entrega indica quién firmó o recogió el paquete, Peek conserva ese nombre para quien añadió el envío y lo oculta en los enlaces compartidos. Como los escaneos no nombran localidad, ni J&T ni Peek pueden indicar en qué estado o nave está el paquete.

## Reparto de J&T Express Australia y prueba de entrega

J&T contrata empresas colaboradoras por toda Australia para la recogida y última milla, sin traspaso a otro operador con segundo código: el número `JTAU` llega hasta tu puerta.

**Paquetes de líneas internacionales.** Transitarios exteriores pueden entregar lotes a J&T al llegar a Australia: [Yanwen](carrier:yanwen), por ejemplo, los transfiere en el puerto de entrada y su propio seguimiento reproduce después las frases de J&T (allí Peek muestra la recogida de J&T como «En tránsito», porque Yanwen ya llevaba antes el envío).

**Edificio cerrado o fallo en la dirección.** Tras `The building was closed when we arrived. We'll try again.`, J&T hará otro intento; ante `We couldn't deliver - the address details appear to be incorrect. Please update your delivery address.`, comprueba piso y código postal con la tienda cuanto antes ([cambiar la dirección de entrega](guide:change-delivery-address), [entrega fallida](guide:missed-delivery)). La web de J&T no publica número máximo de intentos ni días de custodia, ni consta entrega en puntos de recogida o taquillas.

**Prueba de entrega.** El aviso de privacidad de J&T detalla qué registra el repartidor en su aplicación:
- fotos del paquete en el lugar donde se dejó, fecha y hora, geolocalización del dispositivo, firma cuando proceda y notas de entrega;
- instrucciones a los conductores para fotografiar el paquete evitando en lo posible personas, matrículas e interiores de viviendas;
- envío de un enlace por SMS u otra notificación electrónica al destinatario (cualquiera con el enlace puede abrirlo; no incluye nombre ni teléfono del conductor), además de facilitarlo al remitente o marketplace;
- si no quieres foto al entregar, puedes dar instrucciones escribiendo a `dataprotection@jtexpress.au`.

## Paquete de J&T parado, no encontrado o entregado pero no recibido

Como la tienda contrató a J&T, acude primero al vendedor.

**Sin escaneos nuevos durante cuatro días.** Un paquete en «Admitido por el transportista» o «En tránsito» sin cambios durante cuatro días recibe en Peek el aviso «Sin novedades desde hace cuatro días» y el enlace «Pide ayuda a J&T Express Australia» ([por qué no se actualiza el seguimiento](guide:tracking-not-updating)).

**Sin historial todavía.** Un código que J&T aún no conoce aparece como «Esperando a J&T Express Australia». Si dos días después ningún operador ha respondido, Peek muestra «Tras dos días, el transportista sigue sin reconocer este número. Revísalo, elige otro transportista o elimina el paquete.» y pasa a comprobarlo a diario y, tras una semana, cada semana.

**Entregado pero no está, perdido o dañado.** Abre el enlace de la foto en el SMS de J&T, revisa la entrada y pregunta en casa y a vecinos; si sigue faltando, avisa a la tienda y a J&T (cuyo aviso de privacidad contempla usar esos registros para investigar pérdidas, daños o entregas erróneas, aunque su web no fija reclamaciones directas para destinatarios): pide reembolso o reemplazo al comercio o marketplace ([entregado pero no recibido](guide:delivered-not-received), [paquete perdido](guide:lost-parcel), [paquete dañado](guide:damaged-parcel)).

## Atención al cliente y contacto de J&T Express Australia

En la [página de contacto de J&T Australia](https://www.jtexpress.au/contact) figuran:

- **Teléfono:** `13 18 18`, horario laboral `08:00-20:00` (sin especificar días).
- **Correo de atención:** `service@jtexpress.au` (`business@jtexpress.au` y `partner@jtexpress.au` son para empresas y socios de reparto, y `dataprotection@jtexpress.au` para privacidad en su sede `10/2 Christina Road, Villawood NSW 2163`).

Su aviso de privacidad señala que sus proveedores de centro de llamadas y soporte al cliente están en Malasia y Filipinas.

Un SMS de J&T con enlace a la foto de entrega puede ser legítimo, pero si te pide pagar o dar datos bancarios, verifica el dominio `www.jtexpress.au` (`jtexpress.au`) y consulta [mensajes falsos de entrega](guide:delivery-scam-texts).

## Seguir paquetes de J&T Express Australia con Peek

[Peek](/) reconoce a J&T Express Australia de inmediato por el código `JTAU` («Transportista detectado») o por un enlace `www.jtexpress.au/tracking` con `?q=` y un número `JTAU` (con otro tipo de código en `?q=`, Peek lo lee como si lo hubieras pegado solo; si eliges J&T Australia a mano para otro formato, avisa de que «no emite números como este»). Con un código `JT` de 13 dígitos, Peek consulta a J&T Express y a servicios de seguimiento.

Peek consulta el rastreador oficial en `UTC` (`GMT+0000`) y muestra cada escaneo en la zona horaria de tu dispositivo («Horas en tu zona horaria: …»), conservando hasta los 100 más recientes, sin mapa, fecha prevista, peso, datos de oficina ni foto de entrega (visible desde el enlace del SMS). Cuando el rastreador de J&T falla o aún no conoce el código, Peek recurre a servicios de seguimiento.

Peek comprueba hasta cada 10 minutos con el paquete abierto o el día de entrega con notificaciones activas, y hasta cada 2 minutos desde las `06:00` hora local el día en que J&T escanea el envío «En reparto» mientras responda su rastreador (mediante servicios de seguimiento: cada hora, cada 3 h tras un día sin cambios y hasta cada 15 minutos el día de entrega con el paquete abierto o alertas activas); el resto del tiempo, y tras 12 h en «Admitido por el transportista» o dos días sin novedades, cada hora («Cada escaneo», «Pasos importantes» o «Solo la entrega»). Peek no puede contactar con J&T o la tienda ni cambiar la dirección.

## Preguntas frecuentes sobre el seguimiento de J&T Express Australia

### ¿Es fiable J&T Express Australia?

Sí. El *Australian Business Register* inscribe a *J&T EXPRESS AUSTRALIA PTY LTD* como sociedad privada australiana con ABN activo desde el 4 de junio de 2026 (`18 698 743 609`, ACN `698 743 609`, ubicación principal `NSW 2163`, sede social en `10/2 Christina Road, Villawood NSW 2163`), parte del grupo J&T Express (`01519.HK`) fundado en Indonesia en 2015.

### ¿Qué significa `picked up from customs clearance` en J&T?

Que la aduana ya terminó: J&T recogió tu paquete importado del agente de aduanas para repartirlo en Australia. Peek lo muestra como «Admitido por el transportista», no como retención aduanera.

### ¿Por qué no aparece mi número de J&T?

Comprueba que empiece por `JTAU` con 12 dígitos detrás (no `JTUK` ni `JTDE`). Si es correcto, puede que J&T aún no tenga el paquete (`No tracking records yet`, «Esperando a J&T Express Australia» en Peek); si tras dos días sigue sin aparecer, pregunta a la tienda.

### ¿Cómo contacto con J&T Express Australia?

Llama al `13 18 18` (`08:00-20:00`) o escribe a `service@jtexpress.au`, y avisa también al comercio donde hiciste el pedido.

### ¿Hace foto J&T Express Australia al entregar?

Sí: su aviso de privacidad indica que los repartidores fotografían el paquete donde lo dejan y recibes un enlace al comprobante por SMS u otra notificación electrónica.

:::sources
- [J&T Express Australia: Track & Trace](https://www.jtexpress.au/tracking) – rastreador oficial y aviso `No tracking records yet`
- [J&T Express Australia: Contact Us](https://www.jtexpress.au/contact) – teléfono 13 18 18 (08:00-20:00), service@jtexpress.au, business@jtexpress.au, partner@jtexpress.au y ABN 18 698 743 609
- [J&T Express Australia: Delivery Partners](https://www.jtexpress.au/career) – socios de recogida y reparto de última milla en Australia
- [J&T Express Australia: About Us](https://www.jtexpress.au/about) – grupo J&T Express (01519.HK), fundación en Indonesia en 2015, Sudeste Asiático y China continental
- [J&T Express Australia: Privacy Notice](https://www.jtexpress.au/privacy-policy) – sede en 10/2 Christina Road, Villawood NSW 2163, fotos de prueba de entrega y enlace SMS, dataprotection@jtexpress.au, investigaciones y soporte en Malasia y Filipinas
- [ABN Lookup: J&T Express Australia Pty Ltd](https://abr.business.gov.au/ABN/View?abn=18698743609) – ABN 18 698 743 609 activo desde el 4 de junio de 2026, empresa privada australiana, NSW 2163
- [Universal Parcel Scraper: J&T Express Australia](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/j-and-t-au) – formato JTAU, frases en inglés y chino, horas UTC, limpieza de textos, firmante y enlace de ayuda
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formatos de otras redes J&T, traspaso por lotes de Yanwen y respaldo de servicios de seguimiento
:::
