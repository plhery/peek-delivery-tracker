---
title: Seguimiento ECMS Express: códigos de 16 caracteres y aduana
description: Rastrea un paquete de ECMS Express (易客满): cómo son sus números de 16 caracteres, qué significa cada escaneo, quién reparte en España y cómo seguirlo en Peek.
slug: seguimiento-ecms-express
published: 2026-11-21
updated: 2026-11-21
---

ECMS Express (`易客满`, también `ECMS Global` o `ECMSGlobal`, razón social en China `天津易客满国际物流有限公司`) es una empresa internacional de transporte urgente y logística para comercio electrónico fundada en 2013, con cobertura en más de 200 países y regiones. Si una tienda envió tu compra con ECMS, tu número tiene 16 caracteres y empieza por `EC`, `ES`, `AP` o `AX`: pégalo en el recuadro superior. ECMS gestiona el transporte internacional y la aduana, y encarga el reparto final a un transportista local. El catálogo de Peek también lo encuentra al buscar `ECMS`, `ECMS Global`, `ECMSGlobal`, `ECMS International Logistics Co.`, `易客满`, `易客满国际物流有限公司` o `易客满国际快递`.

## Número de seguimiento de ECMS Express: cómo es

Los códigos de ECMS tienen siempre 16 caracteres: `EC`, `ES`, `AP` o `AX`, seguidos de entre tres y cinco letras y dígitos en el resto. Con caracteres inventados:

| Número (inventado) | Qué es |
| --- | --- |
| `ECLAX48291057364` | `EC`, tres letras y once cifras: un número de ECMS Express. |
| `ECSZXA4829105736` | `EC`, cuatro letras y diez cifras: también de ECMS. |
| `ECSZXAB482910573` | `EC`, cinco letras y nueve cifras: también de ECMS. |
| `ESLAX48291057364`, `APLAX48291057364`, `AXLAX48291057364` | Las mismas estructuras de 16 caracteres iniciadas por `ES`, `AP` o `AX`. |
| `ECLAX4829105736` | Solo 15 caracteres: no cumple el formato de ECMS y Peek no reconoce ningún transportista (si eliges ECMS Express manualmente, Peek avisa de que «no emite números como este»). Revisa si falta un dígito. |

Ningún otro operador del catálogo de Peek usa estos formatos. Los espacios y las minúsculas no afectan al pegarlo en Peek.

**Dónde encontrarlo.** ECMS imprime el número de albarán (*waybill*) en la etiqueta del paquete, y la tienda lo incluye en el correo de envío o en tu página de pedido. Si no aparece, consulta [cómo encontrar el número de seguimiento](guide:find-tracking-number) y [formatos de números de seguimiento](guide:tracking-number-formats).

**Un enlace de seguimiento.** Un enlace al buscador de `ecmsglobal.com` terminado en `?number=` rellena el código y abre la verificación con imagen (*Security verification*). Los enlaces antiguos de ECMS llevan el código tras `orderNumber=`, `trackingno=` (como en `consignee.ecmsglobal.com`) u `order_number=`. Pega cualquiera de ellos en Peek: extraerá el código ECMS automáticamente.

## Estados de seguimiento de ECMS Express explicados

En `ecmsglobal.com`, el localizador pide primero superar un control visual (*Security verification*: *Enter the characters shown below to continue tracking.*), resume el paquete con una de ocho etiquetas (desde *Shipment Information Received* hasta *Delivered* o *Exception*) y enumera los escaneos del más reciente al más antiguo con fecha, hora y lugar. Peek muestra cada evento con las palabras del registro de ECMS. Por orden de aparición:

| Registro de ECMS | Qué significa para tu envío | Peek muestra |
| --- | --- | --- |
| `Virtual Manifest has been received by carrier`, `Virtual Manifest has been received`, `Shipment info confirmed` | La tienda ha enviado los datos electrónicos a ECMS, que aún no tiene el paquete. | «Etiqueta creada» |
| `Documents and information required for clearance have been received by the carrier.`, `ID information has been reviewed and verified` | ECMS ha recibido la documentación aduanera o ha verificado el documento de identidad del destinatario; todavía no es un escaneo físico. | «Etiqueta creada» |
| `Shipment arrived at carrier facility.`, `Shipment arrived at origin facility.` | Primer escaneo físico en el almacén de origen de ECMS. | «Admitido por el transportista» |
| `Parcel has arrived at export distribution warehouse` | Entrada en el almacén de exportación de ECMS en origen. | «En tránsito» |
| `Shipment departed from carrier facility.`, `Shipment departed from origin facility.` | Salida del almacén de origen. | «En tránsito» |
| `Flight departure`, `Flight arrival` | Despegue y aterrizaje del vuelo de carga. | «En tránsito» |
| `Arriving notification received from terminal` | La terminal de carga del aeropuerto receptor confirma la llegada de la mercancía del vuelo. | «En tránsito» |
| `Shipment has arrived at ECMS transit warehouse`, `Shipment has departed from ECMS transit warehouse` | Almacén de tránsito de ECMS junto al aeropuerto de llegada, antes y después del despacho de importación. | «En tránsito» |
| `Shipment started customs clearance process.` | La aduana del país de destino está tramitando el envío. | «En aduanas» |
| `Shipment completed customs clearance process.` | La aduana de importación ha autorizado la salida del paquete. | «En tránsito» |
| `Shipment arrived at destination facility`, `Parcel at destination facility, ready for next step`, `Parcel Weight` | En la instalación de destino, donde se pesa y aguarda al transportista local. | «En tránsito» |
| `Shipment is tendering to third party carrier for final delivery.` | ECMS está entregando el paquete al transportista local encargado del reparto final; su código puede mostrarse a partir de aquí. | «En tránsito» |
| `Return to origin` | Incidencia anotada en la planta de destino, no siempre una devolución definitiva: en un historial recogido por el código de Peek, el transportista local aceptó el paquete al día siguiente y completó la entrega. | «Incidencia» |
| `Local carrier has accepted package for final delivery` | El transportista local ya tiene el paquete. | «En tránsito» |
| `Shipment is in Transit within the carrier‘s network`, `Parcel in transit during last mile delivery` | Movimiento por la red del operador local retransmitido por ECMS (la segunda línea puede repetirse en días sucesivos). | «En tránsito» |
| `Shipment is out for delivery.` | En reparto hacia tu dirección. | «En reparto» |
| `Available for pickup` | El operador local guarda el paquete para que pases a recogerlo. | «Listo para recoger» |
| `Shipment delivered to customer` | Entregado. | «Entregado» |

**Líneas en chino.** Algunos registros traen estos pasos en chino; Peek conserva el texto en chino y le asigna la misma etapa que a su equivalente en inglés: `发货预报已收到，正在等待货物到达我们的始发站仓库` («Etiqueta creada»), `干线航班起飞` y `干线航班已到达` («En tránsito»), `货物已进入清关流程` («En aduanas»), `货物已清关放行`, `货物已到达目的站仓库` y `货物出库前往配送中心安排最终派送` («En tránsito»).

**Horas.** ECMS registra sus propios escaneos con el reloj y la diferencia UTC de la estación que los genera, y Peek respeta ese huso. Los escaneos retransmitidos del operador local llevan diferencia cero: Peek los lee en UTC, salvo cuando un evento de esa fuente quedaría fechado más de diez minutos después de que ECMS lo grabara, en cuyo caso Peek trata esas horas como reloj local según el lugar. Una línea de vuelo (`Flight departure`, `Flight arrival`) marcada a medianoche exacta indica solo la fecha del vuelo y aparece en Peek sin hora. Mira también [seguir un paquete desde China](guide:tracking-from-china).

## Quién reparte los paquetes de ECMS Express en España

Para el tramo final (*Final Mile Delivery*), ECMS señala en su página *Parcel Delivery Service* que colabora con transportistas locales de confianza (*trusted local carriers*) en más de 200 países y regiones, sin publicar una tabla fija de mensajerías por país en Europa. En el buscador de `ecmsglobal.com`, el recuadro *Last Mile Delivery* puede indicar qué empresa reparte bajo `LM Provider`, su código en `LM Tracking No.` y un enlace `Track with …` (en los historiales del código de Peek en Estados Unidos aparece por ejemplo [SpeedX](carrier:speedx)).

**Cuánto tarda.** Según las preguntas frecuentes de ECMS, el plazo varía según origen, destino y servicio. Desde Estados Unidos (*U.S. Origin* en *Parcel Delivery Service*), ECMS publica `4–8 business days` hacia la **Unión Europea** (además de `3–6 business days` al Reino Unido, `4–8 business days` a Suiza y `3–11 business days` a Canadá). Los intentos de entrega y la recogida en punto siguen las normas de la empresa local: consulta [entrega fallida](guide:missed-delivery) y [paquete no recogido](guide:uncollected-parcel).

**Aduanas en España y la Unión Europea.** La FAQ de ECMS explica que en envíos `DDU/DAP` el destinatario puede tener que abonar aranceles e impuestos antes de la entrega, mientras que en `DDP` suele pagarlos el remitente por adelantado (algunos países piden además copia del documento de identidad para despachar). En la **Unión Europea**, desde el 1 de julio de 2026 las compras online procedentes de fuera de la UE en paquetes con valor intrínseco de hasta 150 € abonan un arancel aduanero de la UE de 3 € por artículo (*per item*), y desde el 1 de noviembre de 2026 se añade una tasa de tramitación de la Unión (*Union handling fee*) de 2 € por artículo cualquiera que sea el valor (un artículo agrupa mercancías del mismo envío con idéntica clasificación arancelaria, descripción y, cuando sea exigible, origen; por encima de 150 € rigen los aranceles ordinarios), además del IVA de importación en **España peninsular y Baleares**, salvo que ya se cobrara en la compra mediante IOSS. Según el ámbito territorial publicado por la Comisión Europea, las **Islas Canarias** pertenecen al territorio aduanero de la UE pero quedan fuera del área de IVA comunitaria, mientras que **Ceuta** y **Melilla** quedan fuera tanto del territorio aduanero como del ámbito de IVA de la UE. Consulta [paquete retenido en aduanas](guide:customs).

## Paquete de ECMS Express parado, no encontrado o devuelto

**«We could not find a shipment for this tracking number.»** El localizador de ECMS no reconoce aún el código (error al teclearlo o envío todavía no comunicado por la tienda): Peek muestra «Esperando a ECMS Express» y sigue comprobando. Si tras dos días ECMS sigue sin conocer el número y ningún servicio de seguimiento lo encuentra, Peek muestra: «Tras dos días, el transportista sigue sin reconocer este número. Revísalo, elige otro transportista o elimina el paquete.»

**Parado en `Virtual Manifest has been received`.** ECMS tiene los datos electrónicos, no la caja física. Pregunta al vendedor cuándo entregó el paquete.

**Sin avances tras `Shipment is tendering to third party carrier for final delivery.`** El operador local ya tiene el envío (o está a punto de recibirlo) y su seguimiento directo suele ir por delante. Busca su nombre y el `LM Tracking No.` en el recuadro *Last Mile Delivery* y, salvo que Peek ya lo siga allí, añade ese código por separado en Peek. Pasados cuatro días sin escaneos nuevos, Peek avisa con «Sin novedades desde hace cuatro días» y muestra «Pide ayuda a ECMS Express» (o el enlace del operador local cuando Peek sigue el envío en él y conoce su web de ayuda). Más información en [por qué no se actualiza el seguimiento](guide:tracking-not-updating).

**Entregado pero no recibido, perdido, dañado o devuelto.** Consulta primero al transportista local dónde dejó el paquete y avisa enseguida al vendedor: la FAQ de ECMS remite al comprador a la tienda para reembolsos o reposiciones, porque solo el remitente puede presentar reclamación por escrito ante ECMS dentro de los 30 días siguientes a la admisión del envío (una única reclamación por paquete), y ECMS no puede cancelar ni modificar pedidos. Entre los motivos de devolución que cita ECMS están artículos prohibidos o restringidos, datos de envío incompletos o erróneos, incidencias aduaneras, aranceles o impuestos impagados o trámites aduaneros pendientes por parte del destinatario; no obstante, una línea `Return to origin` aislada no confirma por sí sola una devolución definitiva. Lee [entregado pero no recibido](guide:delivered-not-received), [paquete perdido](guide:lost-parcel) y [devuelto al remitente](guide:returned-to-sender).

## Teléfono y atención al cliente de ECMS Express

Datos leídos el 21 de noviembre de 2026 en la página *Contact Us* de `www.ecmsglobal.com` (sin huso horario indicado para los horarios; España no tiene teléfono propio en la lista):

- **Formulario web**: en `www.ecmsglobal.com/en-us/contact-us.html`, elige `Shipment Support` en *Inquiry Type* e introduce tu código en *Tracking Number (Optional)*.
- **Europa (Reino Unido)**: `(+44) 01753528888`, `cs.uk@ecmsglobal.com`, de lunes a viernes de `08:30` a `17:00`.
- **China continental**: `(+86) 400 086 1756`, `service@ecmsglobal.com`, de lunes a viernes de `08:30` a `17:30`.
- **Estados Unidos**: `(+1) 626 956 5388`, `customerserviceus@ecmsglobal.com`, de lunes a viernes de `09:00` a `18:00`.

**Cuidado con los SMS fraudulentos.** Los dominios oficiales de seguimiento de ECMS son `www.ecmsglobal.com` (`ecmsglobal.com`) y el antiguo portal `consignee.ecmsglobal.com`. Peek descarta cualquier enlace de pago del registro; si recibes un SMS pidiendo pagar aduanas o redespacho, verifica que corresponda al operador local indicado bajo `LM Provider` y revisa [mensajes falsos de entrega](guide:delivery-scam-texts).

## Seguir un paquete de ECMS Express con Peek

[Peek](/) identifica al instante un número ECMS de 16 caracteres («Transportista detectado») y consulta el rastreador de ECMS sin cuenta, código postal ni teléfono.

El control con imagen de ECMS es una pequeña suma o resta de un dígito: Peek la resuelve por sí mismo y, si falla, vuelve a intentarlo una vez con una imagen nueva. Cuando ECMS sigue sin responder, Peek recurre a servicios de seguimiento y muestra el historial que conservan, con sus propias palabras.

Peek presenta el estado, cada escaneo con ciudad, provincia y país y el país de destino, del más nuevo al más antiguo, en la zona horaria de tu dispositivo; en `peektracker.com` y en las apps de iPhone y Android, el historial concluye con «Horas en tu zona horaria: …». Descarta la referencia de la tienda, la ubicación en líneas de manifiesto electrónico y los enlaces de pago, y no muestra peso ni fecha estimada de entrega. El botón «Abrir la web de ECMS Express» abre tu código en `www.ecmsglobal.com`.

Cuando el registro de ECMS nombra un transportista local que Peek conoce, la ficha muestra «Entrega con …» (con su número cuando las lecturas del socio incluyen uno único), o «Anunciado a …, aún no recibido» mientras ese operador solo tenga registrado el anuncio sin escaneos propios. Si Peek lee el rastreador directo de ese transportista sin datos adicionales, también le pregunta y traspasa allí el seguimiento en cuanto registra un escaneo al menos tan reciente como el último de ECMS, conservando el historial previo; en caso contrario, añade el `LM Tracking No.` aparte: Peek sigue a más de 3.500 transportistas.

Mientras tengas el paquete abierto o, el día de entrega, las notificaciones activadas, Peek comprueba cambios hasta cada 10 minutos —y hasta cada 2 minutos desde las 6:00 locales el día en que pasa a «En reparto» mientras responda el rastreador de ECMS (tras el traspaso al operador local, Peek sigue el ritmo de este; solo mediante servicios de seguimiento: cada hora, cada 3 horas tras un día sin cambios y hasta cada 15 minutos el día de entrega con el paquete abierto o notificaciones activas)—; en los demás casos, y tras dos días sin novedades (12 horas en «Etiqueta creada» o «Admitido por el transportista»), consulta cada hora. Puedes activar alertas de «Cada escaneo», «Pasos importantes» (inicio de aduanas de importación, `Return to origin`, salida a reparto, `Available for pickup` y entrega, sin vuelos ni pasos intermedios de almacén) o «Solo la entrega». Peek no puede contactar con ECMS ni con el vendedor por ti.

## Preguntas frecuentes sobre el seguimiento de ECMS Express

### ¿Qué es ECMS Express (易客满)?

Una compañía internacional de paquetería urgente y logística transfronteriza fundada en 2013 (`天津易客满国际物流有限公司`), con sucursales propias en China continental, Hong Kong, Singapur, Estados Unidos, Reino Unido, Europa, Corea del Sur y Japón.

### ¿Dónde puedo rastrear un paquete de ECMS Express?

En el cuadro superior de esta página (sin tener que resolver el captcha) o en la página *Track Your Shipment* de `www.ecmsglobal.com` tras completar su verificación de seguridad.

### ¿Qué empresa reparte los paquetes de ECMS Express en España?

Un transportista colaborador local con el que trabaja ECMS, cuyo nombre y código propio pueden figurar bajo `LM Provider` y `LM Tracking No.` en el apartado *Last Mile Delivery* del rastreador de ECMS. Si aún no aparecen, consúltalos con el vendedor.

### ¿Qué significa «Shipment is tendering to third party carrier for final delivery»?

Que ECMS está entregando tu paquete a la empresa de mensajería local encargada del reparto final, cuyo código puede aparecer bajo `LM Tracking No.`. Peek muestra «En tránsito».

### ¿A quién reclamo si falta un paquete de ECMS Express?

Primero al vendedor donde realizaste el pedido: la FAQ de ECMS dirige al comprador a la tienda para reembolsos o reenvíos, ya que solo el remitente puede reclamar a ECMS por escrito en un plazo de 30 días desde la admisión. Sobre el reparto en tu domicilio, pregunta al operador local; también puedes escribir a ECMS mediante el formulario *Shipment Support* en `ecmsglobal.com`.

:::sources
- [ECMS Express: página principal](https://www.ecmsglobal.com/en-us/home.html) – transporte urgente internacional, carga y logística para comercio electrónico
- [ECMS Express: About ECMS](https://www.ecmsglobal.com/en-us/about.html) – fundada en 2013
- [易客满: 关于我们 (quiénes somos)](https://www.ecmsglobal.com/zh-cn/about.html) – fundación en 2013, sucursales propias y razón social 天津易客满国际物流有限公司
- [ECMS Express: Parcel Delivery Service](https://www.ecmsglobal.com/en-us/express.html) – transportistas locales de última milla, 200+ países y plazos desde EE. UU. hacia la UE, Suiza, Reino Unido y Canadá
- [ECMS Express: Track Your Shipment](https://www.ecmsglobal.com/en-us/tracking.html) – control visual, ocho estados resumen, cuadro Last Mile Delivery (`LM Provider`, `LM Tracking No.`), aviso de no encontrado y `?number=`
- [ECMS Express: FAQ](https://www.ecmsglobal.com/en-us/faq.html) – albarán en etiqueta, DDU/DAP y DDP, causas de devolución, imposibilidad de cancelar y reclamaciones del remitente en 30 días
- [ECMS Express: Contact Us](https://www.ecmsglobal.com/en-us/contact-us.html) – formulario Shipment Support, teléfonos regionales, correos y horarios
- [Comisión Europea: Reforma aduanera de la UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – arancel de 3 € desde el 1 de julio de 2026 y tasa de 2 € desde el 1 de noviembre de 2026
- [Comisión Europea: Orientaciones sobre el arancel de 3 € y la tasa de la Unión (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – cobro por artículo y exención de IVA con IOSS
- [Comisión Europea: Ámbito territorial del IVA y aduanas](https://taxation-customs.ec.europa.eu/taxation/vat/vat-directive/how-does-vat-work/territorial-scope_en) – Islas Canarias, Ceuta y Melilla
- [Universal Parcel Scraper: ECMS Express](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/ecms-express) – alias, formato de 16 caracteres, enlaces, verificación aritmética y reintento, estados en inglés y chino, husos horarios y campos descartados
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – traspaso al transportista local y respaldo mediante servicios de seguimiento
:::
