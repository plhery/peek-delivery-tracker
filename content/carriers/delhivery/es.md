---
title: Seguimiento de Delhivery: números AWB, estados y soporte
description: Rastrea un paquete de Delhivery en la India o al extranjero: formato del AWB, qué significan estados como RTO, entregas fallidas, aduanas y soporte oficial.
slug: seguimiento-delhivery
published: 2026-11-21
updated: 2026-11-21
---

Fundada en 2011, Delhivery (*Delhivery Limited*) se define como el mayor operador logístico totalmente integrado de la India: reparte pedidos online de marketplaces y comercios en más de `18.850` códigos PIN y envía desde la India a más de `220` países y territorios. Puedes localizar tu envío en la [página de seguimiento de Delhivery](https://www.delhivery.com/tracking) con el número AWB, el identificador de pedido (`Order Id`), un `LRN` o tu móvil, o pegando el AWB en el recuadro superior.

## Número de seguimiento de Delhivery (AWB): cómo es

Delhivery llama a su código de seguimiento **AWB** (*airway bill number*, número de guía). Estos son los formatos de AWB que reconoce Peek, con dígitos inventados:

| Número (inventado) | Qué es |
| --- | --- |
| `1482910573642` | **13 dígitos**: un AWB de Delhivery. Otros operadores usan también 13 cifras, como [Econt](carrier:econt), DPD France, FAN Courier o Korea Post. |
| `14829105736492` | **14 dígitos**: también un AWB de Delhivery, longitud compartida con [DPD](carrier:dpd-de), [SEUR](carrier:seur), [BRT](carrier:brt) o Xpressbees. |

La longitud por sí sola no identifica a Delhivery, así que comprueba qué empresa menciona el SMS o correo del vendedor. En `delhivery.com/tracking` también puedes buscar por `Order Id`, por `LRN` (carga B2B) o iniciar sesión con tu teléfono móvil y código `OTP` para ver todos tus envíos; en Peek necesitas el **AWB** de 13 o 14 dígitos ([cómo encontrar tu número de seguimiento](guide:find-tracking-number)).

## Qué significan los estados de seguimiento de Delhivery

El seguimiento de Delhivery combina una barra de etapas con la lista de escaneos y su ciudad. Estos son los textos y códigos que lee Peek:

| Estado de Delhivery | Qué significa para tu envío | Peek muestra |
| --- | --- | --- |
| `WAITING_PICKUP` | Envío registrado y pendiente de recogida (mostrado como `Waiting pickup` mientras no haya escaneos). | «Etiqueta creada» |
| `Picked Up` | Delhivery recogió el paquete del remitente (también usado como hito de carga sin escaneos individuales). | «Admitido por el transportista» |
| `PROD_REPLACED` | En una recogida inversa con sustitución, el mensajero ya retiró el producto reemplazado. | «Admitido por el transportista» |
| `In Transit`, `IN_TRANSIT`, `On the Way` | Viajando por la red de Delhivery (`IN_TRANSIT` como código, `On the Way` como hito de carga). | «En tránsito» |
| `Dispatched`, `Out for Delivery` | En reparto hacia tu dirección (`Dispatched` en el escaneo de paquetería, `Out for Delivery` en la barra de progreso). | «En reparto» |
| `Delivered` | Entregado (en un flujo de devolución significa entregado de vuelta al vendedor). | «Entregado» (o «Devuelto al remitente») |
| `Undelivered` | No entregado y de regreso al vendedor; en carga, Delhivery renombra así el paso `On the Way` conservando su fecha original, por lo que no fecha un intento de entrega. | «Incidencia» |
| `REACHED_SEL_CITY` | Un envío en devolución ha llegado a la ciudad del vendedor. | «En tránsito» |
| `RTO`, `DELIVERED_SELLER` | *Return to Origin*: el paquete devuelto llegó de vuelta al comercio. | «Devuelto al remitente» |
| `DTO` | Una devolución enviada por el comprador llegó al vendedor. | «Devuelto al remitente» |
| `LOST` | Delhivery ha dado el envío por perdido. | «Incidencia» |
| `CANCELLED`, `Canceled` | El envío o la recogida de devolución se canceló. | «Incidencia» |

Más detalles sobre cada fase en [qué significan los estados de seguimiento](guide:tracking-statuses).

## Reparto de Delhivery en la India: llamadas, ausencias y dirección

**Red propia.** Delhivery cuenta con `50` centros automatizados de clasificación, `123` nodos y más de `4.500` centros de reparto directo en todos los estados indios, cubriendo el `99,5 %` de los códigos PIN (incluidos el noreste de la India y Jammu y Cachemira) las 24 horas, los 365 días del año.

**Llamadas y WhatsApp.** Sus condiciones prevén que Delhivery o sus repartidores te llamen, te envíen SMS o te escriban y llamen por WhatsApp (las llamadas pueden grabarse) para coordinar la entrega. Para tiendas online ofrece gestión de incidencias (`Smart NDR`) mediante WhatsApp y llamadas con IA, pero **nunca** pide códigos `OTP` ni credenciales para confirmar tu dirección.

**Ausencias y condiciones Direct.** En su servicio para particulares *Delhivery Direct* (hasta `50 kg` de peso tasable, o `10 kg` y `50 cm` por lado en modo exprés; sin apartados postales), la entrega se cancela tras **3 intentos fallidos** sin reembolso del porte (el remitente puede pedir la devolución a su costa). Se puede autorizar entrega a vecino, buzón/taquilla o recogida en punto aprobado si Delhivery acepta, aunque puede rechazar un cambio de código PIN ([entrega fallida](guide:missed-delivery)). En compras de tienda rigen las condiciones del vendedor.

## Paquetes de Delhivery enviados al extranjero y aduanas

**Plazos y socios.** Con **DLV Saver**, Delhivery estima `5–7` días al Reino Unido y `8–12` días a EE. UU. (cubre EE. UU., Reino Unido, Canadá y Australia); **DLV Premium** llega a más de `220` países y territorios en `3–6` días (*Express*) o `6–10` días (*Deferred Express*) desde `50 g`. En envíos internacionales cita a [FedEx](carrier:fedex) y Aramex como socios estratégicos: si la tienda te facilita un número del operador final, añádelo también en Peek.

**Aduanas en España y otros destinos.** En **España** (Unión Europea), las compras online enviadas desde fuera de la UE con valor de hasta `150 €` pagan desde el 1 de julio de 2026 un arancel provisional de `3 €` por artículo (bienes con igual clasificación arancelaria, descripción y, cuando proceda, origen; por encima de `150 €` rige el arancel ordinario) y, desde el 1 de noviembre de 2026, una tasa de tramitación de la Unión de `2 €` por artículo cualquiera que sea su valor. En **Península y Baleares** se suma el IVA de importación salvo que ya se cobrara en la compra mediante IOSS; **Canarias** pertenece al territorio aduanero de la UE pero está fuera del territorio de IVA de la UE, y **Ceuta y Melilla** quedan fuera tanto del territorio aduanero como del territorio de IVA de la UE. En **Latinoamérica** y **Estados Unidos** se aplican las reglas aduaneras de cada país ([paquetes retenidos en aduanas](guide:customs)).

## Seguimiento parado, paquete perdido, dañado o devuelto

Para reembolsos o reenvíos de un pedido online, contacta primero con la tienda que contrató a Delhivery.

- **Sin avances:** en «Etiqueta creada» (`WAITING_PICKUP`), el vendedor aún no entregó el bulto. Si se detiene en ruta, entra con tu móvil en `delhivery.com/support` o en la app para abrir una consulta ([por qué no se actualiza el seguimiento](guide:tracking-not-updating)).
- **Perdido, dañado o entregado donde no toca:** en *Delhivery Direct*, las reclamaciones por pérdida, daño o entrega errónea deben abrirse por ticket en la app en un plazo de **3 días** (en el mismo día natural si una caja comprada a Delhivery llega dañada), adjuntando **3 fotos** del embalaje exterior y del contenido en caso de daños (recomiendan grabar en vídeo la apertura). Se resuelven en **30 a 45 días laborables** y, sin la cobertura de pago *Delhivery Protect* (obligatoria entre `10.000 ₹` y el tope Direct de `49.999 ₹`; hasta `30.000 ₹` por AWB en e-commerce), la responsabilidad máxima es de **`2.000 ₹`** con topes por categoría (`500 ₹` libros/documentos/otros, `700 ₹` ropa, `800 ₹` hogar, `1.000 ₹` electrónica o varios artículos, `2.000 ₹` deporte, `400 ₹` consumibles), sin indemnización por retraso ([paquete perdido](guide:lost-parcel), [paquete dañado](guide:damaged-parcel), [entregado pero no recibido](guide:delivered-not-received)).
- **Devuelto (`RTO`, `DELIVERED_SELLER`):** el paquete regresó al comercio; solicita el reembolso o un nuevo envío ([devuelto al remitente](guide:returned-to-sender)).

## Atención al cliente de Delhivery y avisos antifraude

- **Soporte exclusivo por web o app:** Delhivery advierte que su atención al cliente **solo** es accesible desde su [página de soporte](https://www.delhivery.com/support) (iniciando sesión con teléfono y `OTP` en `Raise a Query`; apartados `Report Issues`, `Grievance Officer`, `Report on-field staff issues`, `Returns`) o desde la app *Delhivery Direct*.
- **Sin teléfono público de atención:** otros teléfonos de soporte que aparezcan en Internet pueden ser fraudulentos. Los correos oficiales proceden siempre de `@delhivery.com`, y en redes sociales solo atiende desde sus cuentas oficiales de Facebook, X e Instagram.
- **SMS falsos:** alerta ante mensajes de entrega fallida que redirigen a dominios falsos (`delhiveryu.top`, `delhiveryma.top`); Delhivery nunca pide `PIN` ni `OTP` para pagos, ni `OTP` o credenciales para confirmar tu dirección. En la India, ante pérdidas económicas llama al `1930` o denuncia en `cybercrime.gov.in` (y reporta llamadas/SMS spam en 3 días en `sancharsaathi.gov.in`); consulta también [mensajes falsos de entrega](guide:delivery-scam-texts).

## Seguir un paquete de Delhivery con Peek

Pega el AWB de 13 o 14 dígitos en [Peek](/): al compartir formato con otros operadores, Peek muestra «Comprobando servicios de seguimiento…» y pregunta automáticamente a Delhivery cuando conectas desde la India con un código que empieza por `1`–`9` y termina en `0`–`6` («Delhivery tiene este paquete.»). Fuera de la India —o si el AWB empieza por `0` o acaba en `7`, `8` o `9`— pulsa «Cambiar» y elige Delhivery. Para otra longitud, la selección manual avisa de que «no emite números como este».

Bajo Delhivery, un envío sin escaneos muestra «Esperando a Delhivery». Peek lee su seguimiento público sin cuenta: muestra los escaneos completados con su ciudad (descartando pasos futuros de la barra y datos del destinatario), calcula el año de los hitos de carga por el día de la semana, interpreta la hora de la India (`Asia/Kolkata`, `UTC+5:30`) y la convierte a tu zona («Horas en tu zona horaria: …»), sin fecha estimada. Un envío entregado de vuelta al vendedor figura como «Devuelto al remitente», no como «Entregado».

Con el paquete abierto o el día de entrega con notificaciones activas, Peek comprueba hasta cada 10 minutos, y hasta cada 2 minutos desde las `06:00` hora india el día en que Delhivery escanea «En reparto» mientras responda su rastreador; el resto del tiempo, y tras 12 h en «Etiqueta creada» / «Admitido por el transportista» o dos días sin cambios, cada hora («Cada escaneo», «Pasos importantes» o «Solo la entrega»). Peek no puede contactar con Delhivery ni con el vendedor por ti.

## Preguntas frecuentes sobre el seguimiento de Delhivery

### ¿Cómo rastreo un paquete de Delhivery?

Pega el AWB de 13 o 14 dígitos arriba en Peek (y elige Delhivery con «Cambiar» si estás fuera de la India), o consulta en `delhivery.com/tracking` por AWB, `Order Id`, `LRN` o número de móvil con `OTP`.

### ¿Qué es el número AWB en Delhivery?

El *airway bill number*, el número de guía de 13 o 14 cifras de Delhivery que figura en el SMS o correo de envío de la tienda.

### ¿Qué significa `RTO` en el seguimiento de Delhivery?

*Return to Origin*: el paquete emprendió la vuelta y ya fue devuelto al vendedor. Pide a la tienda el reembolso o un nuevo envío.

### ¿Cuál es el teléfono de atención al cliente de Delhivery?

Delhivery indica que su soporte solo atiende desde su web (`delhivery.com/support` con móvil y `OTP`) o su app oficial, y advierte que los teléfonos publicados en otros sitios pueden ser fraudulentos.

:::sources
- [Delhivery: Track your order](https://www.delhivery.com/tracking) – rastreo por móvil, AWB, Order Id o LRN y alertas antifraude
- [Delhivery: Support](https://www.delhivery.com/support) – acceso con OTP, envío de consultas y temas incluido Grievance Officer
- [Delhivery: About Us](https://www.delhivery.com/about-us) – fundada en 2011, más de 18.850 códigos PIN, 50 centros de clasificación y más de 4.500 centros de reparto 24/7
- [Delhivery: Inicio](https://www.delhivery.com/) – más de 220 países y territorios, y FedEx y Aramex como socios estratégicos
- [Delhivery: Express Parcel](https://www.delhivery.com/services/express-parcel) – cobertura del 99,5 % de códigos PIN, Smart NDR por WhatsApp/IA y protección e-commerce
- [Delhivery: International](https://www.delhivery.com/services/international) – plazos de DLV Saver y DLV Premium desde 50 g y aranceles e impuestos
- [Delhivery: Personal Courier](https://www.delhivery.com/solutions/personal-courier) – envíos particulares Delhivery Direct
- [Delhivery: Terms and Conditions](https://www.delhivery.com/terms-and-conditions) – llamadas, SMS y mensajes de WhatsApp sobre entregas
- [Delhivery: TnC (Direct National Courier)](https://www.delhivery.com/tnc-direct) – límites de peso, 3 intentos, reclamaciones en 3 días con 3 fotos, resolución en 30–45 días laborables y topes hasta 2.000 ₹ / Delhivery Protect
- [Delhivery: Fraud Disclaimer](https://www.delhivery.com/fraud-disclaimer) – canales oficiales @delhivery.com, SMS falsos, sin OTP de pago, línea 1930 y cybercrime.gov.in
- [Comisión Europea: EU Customs Reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – arancel de 3 € hasta 150 € desde julio de 2026 y tasa de 2 € desde noviembre de 2026
- [Comisión Europea: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – concepto de artículo, tasa de 2 € cualquiera que sea el valor e IOSS
- [Comisión Europea: Ámbito territorial del IVA y de la unión aduanera](https://taxation-customs.ec.europa.eu/taxation/vat/vat-directive/how-does-vat-work/territorial-scope_en) – Canarias fuera del territorio de IVA de la UE y Ceuta y Melilla fuera del territorio aduanero y de IVA de la UE
- [Universal Parcel Scraper: Delhivery](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/delhivery) – formatos AWB de 13 y 14 dígitos, 17 estados y códigos, hitos de carga, devoluciones y huso Asia/Kolkata
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – otros operadores de 13 y 14 dígitos
:::
