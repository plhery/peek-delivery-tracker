---
title: Seguimiento DHL eCommerce: números GM, estados y contacto
description: Sigue un paquete DHL eCommerce (antes DHL Global Mail): formato GM, significado de los estados, entrega con USPS o correo local y contacto.
slug: seguimiento-dhl-ecommerce
published: 2026-11-23
updated: 2026-11-23
---

DHL eCommerce, antes DHL Global Mail, es la división de paquetería de DHL para tiendas online. Clasifica tu paquete en sus centros de distribución y luego lo entrega a un operador de tu país: [USPS](carrier:usps) en EE. UU., y fuera de EE. UU. el correo local u otro transportista. Síguelo con el número del correo de envío de la tienda en el portal Webtrack de DHL eCommerce o pégalo en el recuadro de arriba.

## Formato del número de seguimiento DHL eCommerce

Los números de DHL eCommerce tienen al menos diez caracteres y suelen empezar por `GM`. Webtrack admite hasta 10 números a la vez, separados por comas. ¿Sin número? Pregunta a la tienda.

| Formato (inventado) | Qué es |
| --- | --- |
| `GM4829103756182947` | `GM` y de 16 a 18 dígitos: el formato habitual de DHL eCommerce. |
| `GM4829103756` | `GM` y de 8 a 15 dígitos: un número `GM` más corto. |
| `9261482910375618294751` | 22 dígitos empezando por `9261` o `9361`: el número de USPS que recibe un paquete estadounidense al pasar a USPS. |
| `7394028156342819` | 16 o 17 dígitos: otra forma utilizada por DHL eCommerce. |
| `0031482910375618294751829364728` | `0031` y 27 dígitos: otro formato de DHL eCommerce. |

La línea «Delivery by» de Webtrack indica el transportista de última milla, cuyo propio número puede aparecer más adelante.

Números parecidos: 16 dígitos pueden corresponder también a TNT, Evri, Canada Post o Correos Express, y un `9261` o `9361` a USPS. Un número de 14 dígitos pertenece a [DHL eCommerce UK](carrier:dhl-ecommerce-uk), una red separada con seguimiento propio; un código `JVGL` a [DHL eCommerce Netherlands](carrier:dhl-ecommerce-nl); y [DHL Express](carrier:dhl-express) y [DHL Paket](carrier:dhl) emplean otros formatos distintos. Más en [formatos de números de seguimiento](guide:tracking-number-formats).

## Estados de seguimiento de DHL eCommerce explicados

Webtrack muestra un estado resumido en una línea (`En Route`, `Delivered`, `Exception`…) encima del historial («Tracking History») en mayúsculas. Estos son los escaneos más frecuentes por orden:

| DHL eCommerce dice | Qué significa para ti |
| --- | --- |
| `LABEL CREATED` | La tienda ha creado la etiqueta. DHL eCommerce aún no tiene el paquete. |
| `EN ROUTE TO DHL ECOMMERCE DISTRIBUTION CENTER OR AWAITING PROCESSING` | En camino hacia DHL o aún esperando en la tienda. |
| `PACKAGE RECEIVED AT DHL ECOMMERCE DISTRIBUTION CENTER` | DHL eCommerce ya tiene el paquete. |
| `PROCESSED` | Clasificado en el centro de distribución. |
| `DEPARTURE ORIGIN DHL ECOMMERCE FACILITY` | Salió del primer centro de DHL eCommerce. |
| `ARRIVAL DESTINATION DHL ECOMMERCE FACILITY` | Llegó al centro de DHL eCommerce cercano a tu zona. |
| `CUSTOMS CLEARANCE` | Está en aduanas. |
| `TENDERED TO DELIVERY SERVICE PROVIDER, ALLOW 1-3 DAYS FOR UPDATES FOR PACKAGES WITHIN THE US` | Entregado al transportista final. En envíos dentro de EE. UU., las actualizaciones pueden pausarse hasta 3 días. |
| `OUT FOR DELIVERY` | En reparto con el repartidor o cartero hoy. |
| `DELIVERY ATTEMPT FAILED` | Nadie pudo recibirlo. Busca un aviso del transportista local. |
| `DELIVERED` | Entregado. `[IN/AT MAILBOX]` detrás significa que se dejó en el buzón. |
| `RETURNED TO SENDER` | Vuelve a la tienda. |

La versión en inglés británico de Webtrack redacta algunos escaneos de otra forma, como `PROCESSED THROUGH SORT FACILITY`. En EE. UU. también aparecen los escaneos de USPS tras el traspaso, como `ARRIVAL AT POST OFFICE`.

Dos avisos suelen causar confusión: `DELIVERED TO RETURNS AGENT OR COLLEGE/UNIVERSITY FOR FINAL DELIVERY` indica que se entregó a un intermediario (como la oficina de correos de un campus) que te lo hará llegar; `Picked up at parcelshop` significa que el remitente lo dejó en un punto de entrega, no que esté esperándote allí.

## Cómo entrega DHL eCommerce: USPS, correos locales y ausencias

Dentro de EE. UU., la FAQ de Webtrack indica que USPS entrega el paquete en el horario habitual del correo. En envíos internacionales toma el relevo el operador local («local delivery service provider»), que puede ser el correo nacional u otra empresa de transporte.

Por ello, los avisos, oficinas de recogida, plazos de custodia y nuevos intentos siguen las normas de ese transportista final. DHL recomienda revisar el buzón por si hubiera un paquete o aviso, y dar cualquier instrucción de entrega a USPS o al transportista local.

Cambio de dirección: la FAQ de Webtrack advierte que una vez el paquete sale de la tienda, DHL eCommerce no puede cambiarla; debes pedirlo a la tienda.

## Paquete DHL eCommerce retrasado, perdido o devuelto

**Aún sin escaneos.** La FAQ de Webtrack indica que los eventos suelen aparecer en 24 a 48 horas; si tras 48 horas no aparece nada, pregunta a la tienda. Cuando Webtrack no encuentra datos, sugiere comprobar el número y contactar con la tienda.

**Sin actualizaciones durante días.** La FAQ menciona clima, festivos, aduanas, huelgas u otras causas ajenas a DHL. En EE. UU., el propio escaneo de entrega al operador prevé 1 a 3 días para nuevas actualizaciones. Ver [por qué el seguimiento deja de actualizarse](guide:tracking-not-updating).

**No ha llegado.** Si el paquete no llega al vencer la ventana prevista, la FAQ de Webtrack indica contactar con la tienda. En la UE, Your Europe recuerda que el vendedor es responsable de daños hasta que recibes el pedido y debe entregar en 30 días salvo acuerdo distinto; si no cumple, recuérdaselo y dale un plazo extra antes de cancelar y solicitar reembolso.

**Aduanas.** `CUSTOMS CLEARANCE` indica que está en aduanas. Consulta [paquetes retenidos en aduanas](guide:customs) para saber qué tasas pueden aplicarse.

**Devuelto.** `RETURNED TO SENDER` significa que vuelve a la tienda: pídele reembolso o reenvío.

## Atención al cliente y teléfono de DHL eCommerce

Habla primero con la tienda, como aconseja la FAQ de Webtrack. La página «Contact Customer Service» de Webtrack ofrece una ficha por país:

- **Estados Unidos y Canadá** (envíos salidos de EE. UU.): +1 317-554-5191, de lunes a viernes de 8:00 a 20:00 EST (la ficha de Canadá indica de 8:00 a 21:00 ET).
- **Alemania:** remite al [servicio al cliente de DHL Alemania](https://www.dhl.de/de/privatkunden/hilfe-kundenservice.html), de lunes a domingo de 7:00 a 20:00 (hora alemana).
- **Irlanda:** 818 370050 (desde Irlanda), de lunes a viernes de 8:00 a 18:00 (hora irlandesa).
- **Australia:** +61 37 0356818, de lunes a viernes de 8:00 a 18:00 (hora local).
- **Austria y Suiza:** +49 228 92 95 95 95, de lunes a viernes de 9:00 a 18:00 (desde las 8:00 en Austria, hora centroeuropea).
- **Reino Unido:** redirige a la línea de DHL eCommerce UK, 024 7693 7770. Consulta nuestra página de [DHL eCommerce UK](carrier:dhl-ecommerce-uk).

El formulario «Contact Support» de Webtrack indica respuesta en un plazo de 24 a 72 horas. No detalla el coste de las llamadas.

## Seguir un paquete de DHL eCommerce con Peek

[Peek](/) reconoce al instante a DHL eCommerce («Transportista detectado») para `GM` y de 16 a 18 dígitos. Para un número `GM` más corto (8 a 15 dígitos), consulta a DHL eCommerce y a otros transportistas compatibles. En formatos compartidos (16 o 17 dígitos, `9261`/`9361`), pregunta a la vez a los transportistas probables (como TNT para 16 dígitos, o Austrian Post y luego USPS para `9261`/`9361`). Si solo DHL eCommerce lo reconoce, Peek te lo indica; si varios lo tienen, muestra cada uno marcado con «Tiene este paquete» para que elijas. Con «Cambiar» (o «Cambiar transportista») acepta cualquier código de 5 a 40 letras y dígitos para DHL eCommerce. También funcionan enlaces de `webtrack.dhlglobalmail.com`, `ecommerceportal.dhl.com` y `dhl.com` cuando el número encaja con DHL eCommerce.

Primero se consulta Webtrack. Si no aparece allí, Peek abre la página global de seguimiento de `dhl.com` en navegador, lo que tarda más y puede fallar si DHL bloquea visitas automáticas.

Muestra el estado, cada escaneo con ciudad y país en el mapa, el día previsto cuando DHL lo ofrece, el remitente, el nombre del servicio y la hora de entrega; desde Webtrack, también el peso y el transportista final. Descarta tu dirección y la referencia de cliente. Los escaneos mantienen el inglés estadounidense de DHL y las entregas aparecen como «Entregado». Las horas se interpretan según el reloj local de cada escaneo y se convierten a tu zona horaria («Horas en tu zona horaria:»).

Cuando Webtrack indica USPS, el paquete puede seguirse en USPS con su código mostrando «Entrega con USPS» (o «Anunciado a USPS, aún no recibido» mientras USPS no tenga escaneos propios). Los paquetes respondidos por la página global de DHL (Europa, Asia) no pasan a un correo local. Peek consulta hasta cada 10 minutos, y hasta cada 5 minutos desde las 6:00 según el reloj del último escaneo el día en que entra «En reparto» mientras mantienes el envío abierto en Peek o tienes las notificaciones activadas (el posible uso de navegador limita la cadencia). Tras dos días sin novedades (12 horas mientras siga en «Etiqueta creada» o «Admitido por el transportista»), consulta cada hora. En notificaciones puedes elegir «Todas las novedades», «Solo importantes» o «Solo el día de entrega». Si pasan cuatro días sin escaneo en tránsito, fuera de aduanas o de una incidencia, Peek muestra «Sin novedades desde el …», el aviso «Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a DHL eCommerce.» y «Pide ayuda a DHL eCommerce». Peek sigue a más de 4500 transportistas, pero no puede contactar con DHL eCommerce ni con la tienda por ti.

## Preguntas sobre el seguimiento de DHL eCommerce

### ¿Es DHL eCommerce lo mismo que DHL Express?

No. DHL eCommerce es la división de paquetería de DHL para tiendas online y suele dejar la entrega final al correo local o a otro transportista. DHL Express tiene su propio seguimiento y Peek los sigue por separado.

### ¿Entrega USPS los paquetes de DHL eCommerce?

Dentro de EE. UU., sí: según la FAQ de Webtrack, USPS realiza la entrega final. En el extranjero se encarga el correo local u otra mensajería.

### ¿Por qué todavía no hay datos de seguimiento de DHL eCommerce?

Según la FAQ de Webtrack, los eventos suelen aparecer en 24 a 48 horas. Si tras 48 horas no figura nada, ponte en contacto con la tienda remitente.

### ¿Es fiable DHL eCommerce?

Sí. Es la división de paquetería para comercio electrónico de DHL, conocida anteriormente como DHL Global Mail.

:::sources
- [DHL eCommerce: Webtrack tracking](https://webtrack.dhlecs.com/) – portal de seguimiento, estados, cuadro de detalles, hasta 10 números, consejos si no aparece, formulario de contacto, avisos por correo
- [DHL eCommerce: Webtrack Contact Customer Service](https://webtrack.dhlecs.com/support/customer-service) – fichas por país, teléfonos, horarios, FAQ sobre 24–48 horas, entrega USPS, retrasos, paquetes perdidos, cambio de dirección, instrucciones
- [Your Europe: Shipping and delivery](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_en.htm) – responsabilidad del vendedor hasta la entrega, 30 días, plazo adicional, reembolso
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – antiguo nombre DHL Global Mail, formatos de número y código USPS, textos y etapas, seguimiento independiente de DHL Express, qué lee y muestra Peek
:::
