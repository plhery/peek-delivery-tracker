---
title: Seguimiento J&T Express Middle East: códigos JTE y estados
description: Rastrea un paquete de J&T Express en Arabia Saudí, EAU o Kuwait: formato JTE, significado de estados, intentos de entrega, contactos y Peek.
slug: seguimiento-jt-express-middle-east
published: 2026-11-21
updated: 2026-11-21
---

J&T Express reparte paquetes en Arabia Saudí, Emiratos Árabes Unidos y Kuwait (su página de contacto también incluye Catar, Jordania y Baréin). En Arabia Saudí ofrece a las tiendas online entrega en el mismo día, básica y económica de hasta 30 kg y envíos puerta a puerta desde China. Arabia Saudí, EAU y Kuwait comparten el portal `jtexpress.me` y un único formato de albarán iniciado por `JTE`, que Peek agrupa como **J&T Express Middle East**. Consulta tu envío en la [página de seguimiento de J&T](https://www.jtexpress.me/KSA/trackOrder) o pega el código arriba: te lo facilita la tienda en los detalles del pedido o en el aviso de expedición.

## Número de seguimiento de J&T Express Middle East: cómo es

El número de guía (*waybill*) de J&T Express Middle East lleva `JTE` seguido de 12 dígitos (ejemplos inventados):

| Número (inventado) | Qué es |
| --- | --- |
| `JTE482910573649` | `JTE` y 12 cifras: un envío de J&T Express Middle East que Peek detecta al instante. |
| `JTE48291057364` | `JTE` con 11 dígitos: falta un dígito. Peek no reconoce ningún transportista: revisa el número en tu mensaje de envío. |
| `JTDE482910573649` | `JTDE` y 12 dígitos: [J&T Express Alemania](carrier:j-and-t-de). `JTAU` corresponde a J&T Express Australia, `JTUK` a J&T Express UK y `JT` con 13 dígitos (`JT4829105736491`) al Sudeste Asiático y [J&T Express China](carrier:j-and-t-cn). |

Los espacios y las minúsculas no importan al pegar el número en Peek. ¿No aparece en tu correo? Revisa [cómo encontrar el número de seguimiento](guide:find-tracking-number) y, si tu compra salió de China continental, [seguimiento de paquetes desde China](guide:tracking-from-china).

## Estados de seguimiento de J&T Express Middle East explicados

El rastreador de J&T ordena los escaneos del más reciente al más antiguo, con un encabezado breve, una frase explicativa debajo, la hora y la ciudad y provincia de la delegación de J&T (además de poder mostrar repartidor, teléfono y prueba de entrega).

Estos son los encabezados que reconoce Peek:

| Encabezado J&T | Qué significa para tu paquete | Peek muestra |
| --- | --- | --- |
| `Order creation` | El remitente o tienda registró el pedido; J&T aún no ha recogido el paquete. | «Etiqueta creada» |
| `Customs Declaration`, `Customs Clearance` | Declaración y despacho de aduanas de importación en la delegación de entrada de J&T. | «En aduanas» |
| `Picked-up` | Recogido por J&T. En paquetes importados que ya pasaron por aduanas o tuvieron un movimiento previo, esta recogida para el último tramo cuenta como tránsito. | «Admitido por el transportista» (o «En tránsito» tras aduanas) |
| `In transit` | En ruta entre delegaciones de J&T, indicando ciudad y provincia de cada lectura. | «En tránsito» |
| `Delivering` | En reparto con un conductor de J&T: mantén el teléfono operativo, ya que J&T contacta por llamada, SMS o correo. | «En reparto» |
| `Abnormal Parcel` | Justo después de `Delivering` indica que ese intento de reparto falló; en otro momento señala una incidencia cuyo motivo concreto figura en la frase inferior del rastreador de J&T. | «Intento de entrega» tras `Delivering`, o «Incidencia» |
| `Signed` | Escaneo de firma tras el reparto: entregado en la dirección indicada (la frase inferior en la web de J&T nombra a quien firmó). | «Entregado» |

Los demás encabezados se clasifican por sus palabras (por ejemplo, un intento como «Intento de entrega» y una devolución como «Devuelto al remitente»); un encabezado que Peek no sabe clasificar deja el envío en el paso ya alcanzado. Mientras J&T no conozca el código o lo liste sin escaneos, verás «Esperando a J&T Express Middle East»; si pasan dos días sin respuesta de ningún operador, Peek muestra «Tras dos días, el transportista sigue sin reconocer este número. Revísalo, elige otro transportista o elimina el paquete.»

## Entrega de J&T en Arabia Saudí, EAU y Kuwait

**Plazos hasta el primer intento.** Según las páginas de información de envío de J&T:

- **Arabia Saudí:** en 24 h en zonas Tier 1, 48 h en Tier 2 y de 72 a 96 h en Tier 3, Tier 4 y zonas remotas (J&T no detalla qué localidades entran en cada nivel; máximo 30 kg, 128 cm en el lado mayor y 274 cm sumando los tres lados).
- **EAU:** en 24 h en zonas normales y de 3 a 7 días laborables en zonas remotas (máximo 30 kg y 158 cm de lado mayor).
- **Kuwait:** la página de Kuwait repite la tabla saudí con tarifas en riyales saudíes (`SAR`), así que guíate por la estimación de la tienda.

En periodos festivos, las condiciones generales añaden 2 días laborables al objetivo de entrega.

**Horarios e intentos.** Las condiciones publicadas por J&T (redactadas para envíos entre particulares en Arabia Saudí y EAU) fijan entregas de `09:00` a `22:00` de domingo a sábado y hasta **tres intentos**. Si no logran localizar al destinatario por teléfono, correo o SMS tras tres intentos fallidos, J&T guarda el paquete un máximo de **7 días laborables** y después lo devuelve al remitente.

**Contra reembolso.** Prepara el importe exacto en efectivo: los conductores de J&T no dan cambio y solo admiten efectivo o tarjeta en su datáfono (`POS`). Si rechazas el paquete o el pago, los portes corren a cargo del remitente y J&T puede retener el envío hasta cobrarlos.

**Quién puede recibirlo y cambio de dirección.** J&T entrega en la dirección indicada (o en la sala de correo central del edificio), no necesariamente en mano al destinatario nominal. Sus páginas públicas no explican cómo cambiar la dirección online: contacta cuanto antes con J&T o con la tienda ([cambiar la dirección de entrega](guide:change-delivery-address)).

**Aduanas.** Las condiciones atribuyen al remitente la declaración y los documentos aduaneros; las tarifas de J&T excluyen aranceles e impuestos de importación (facturados al remitente cuando proceda) y el paquete puede abrirse por controles aduaneros o de seguridad. En Arabia Saudí, la política de compensación excluye envíos retenidos o confiscados por autoridades gubernamentales. Pregunta a la tienda si los cargos estaban incluidos ([paquetes retenidos en aduanas](guide:customs)).

## Paquete de J&T Express retrasado, perdido o devuelto

En compras online, tu primer contacto es la tienda que contrató el envío.

**Parado en `Order creation` o no encontrado.** J&T aún no tiene el bulto (en envíos de particulares pedidos antes de las `14:00` de lunes a domingo, la recogida prevista es el mismo día; después de las `14:00`, el siguiente laborable). Verifica que el código tenga `JTE` y 12 dígitos y consulta al vendedor si sigue igual varios días ([por qué no se actualiza el seguimiento](guide:tracking-not-updating)).

**Sin avances durante días.** Tras cuatro días en ruta sin escaneos nuevos, Peek señala «Sin novedades desde hace cuatro días». En Arabia Saudí, la política de compensación permite al remitente o pagador del porte pedir comprobación a J&T tras **7 días laborables** sin actualizaciones.

**Figura `Signed` pero no lo tienes.** Mira en el rastreador de J&T la frase bajo `Signed` (quién firmó) y el comprobante de entrega si aparece, pregunta en casa y en recepción o conserjería y avisa después a J&T y a la tienda ([entregado pero no recibido](guide:delivered-not-received)). En Arabia Saudí, la política cubre entregas erróneas a otra persona cuando el albarán era correcto.

**Perdido, dañado o devuelto.** Solicita reembolso o reenvío a la tienda ([paquete perdido](guide:lost-parcel), [paquete dañado](guide:damaged-parcel), [devuelto al remitente](guide:returned-to-sender)). En la documentación de J&T conviven tres reglas:
- En Arabia Saudí, la política de compensación (PDF en árabe) da al particular que pagó el envío **30 días naturales** desde la entrega para reclamar a `claim@jtexpress.sa.com` (factura detallada con número de seguimiento y fecha de envío, relación de faltas o daños, fotos o vídeo de recepción y apertura con embalaje y etiqueta, y certificado IBAN); resolución en 7 días y pago en 16 días laborables, hasta `200 SAR` sin seguro (o valor real si es menor; `10.000 SAR` con seguro), excluyendo artículos frágiles rotos con caja exterior intacta y fugas de líquidos por mal embalaje.
- Las condiciones generales dan al remitente **7 días** desde el comprobante de entrega para reclamar por escrito a `cs@jtexpress.sa.com`, con tope de `200 SAR` (sin seguro disponible por ahora).
- Las páginas de información de envío fijan topes en *J&T Special Delivery* de `200 SAR` por daños y `300 SAR` por pérdida.

## Atención al cliente de J&T Express en Arabia Saudí, EAU y Kuwait

La [página de contacto de J&T](https://www.jtexpress.me/KSA/contact-us) publica estos canales (sin horarios de atención):

| País | Teléfono | Correo | WhatsApp |
| --- | --- | --- | --- |
| Arabia Saudí | `800-1010-111` (y `920051211` en el pie) | `cs@jtexpress.sa.com` | `+966 535501158` |
| EAU | `600566122` | `cs@jtexpress.ae` | `+971 526477476` |
| Kuwait | `+965 65850830` | `cs.kw@jtexpress.ae` | `+965 66227312` |

- **Otros países en la página de contacto:** Catar (`+974 71226634` / `+974 30246099`, `cs.qat@jtexpress.sa.com`, WhatsApp `+974 77377607`), Jordania (`+962 797415444`, `cs.jor@jtexpress.sa.com`) y Baréin (`+973 39966461`, `cs.bh@jtexpress.sa.com`).
- **Escalaciones y fraude:** `escalations@jtexpress.sa.com` (exclusivo para quejas no resueltas, fraude o seguridad, no para consultas de seguimiento; ver también [mensajes falsos de entrega](guide:delivery-scam-texts)).
- **Oficinas:** *JNT Express KSA LLC*, `5/F, Building No.3.05, King Abdullah Financial Center, Riyadh, KSA` (registro mercantil `1010728810`); en EAU, *J&T Express Couriers Services LLC*, `Office 309, Emaar Business Park Building 1, Dubai, UAE`.

## Seguir un paquete de J&T Express Middle East con Peek

Pega el código `JTE` en [Peek](/): detecta J&T Express Middle East al momento («Transportista detectado»), igual que un enlace de `jtexpress.me` o `jtexpress.ae` con `waybillNo=` (si eliges J&T a mano para otro formato, avisa de que «no emite números como este»).

Peek consulta el rastreador de J&T en inglés pidiendo la zona `GMT+0300` (`UTC+3`, hora saudí sin horario de verano), interpreta cada escaneo en ese huso sea cual sea el país donde se registró y lo convierte a la zona de tu dispositivo («Horas en tu zona horaria: …»). Muestra el encabezado de cada paso junto con la ciudad y provincia de la delegación (hasta los 100 escaneos más recientes) y descarta la frase inferior, nombre y teléfono del repartidor, firmante y foto de entrega; J&T no ofrece fecha estimada ni peso. Si J&T pide un captcha, Peek no lo resuelve: vuelve a intentarlo más tarde y consulta mientras tanto servicios de seguimiento.

Con el paquete abierto, o el día de reparto con notificaciones activas, Peek comprueba hasta cada 10 minutos, y hasta cada 2 minutos desde las `06:00` hora saudí (`07:00` en EAU) el día en que consta `Delivering` mientras responda el rastreador de J&T (mediante servicios de seguimiento: cada hora, cada 3 h tras un día sin cambios y hasta cada 15 minutos el día de entrega con el paquete abierto o alertas activas); el resto del tiempo, y tras 12 h en «Etiqueta creada» / «Admitido por el transportista» o dos días sin novedades, cada hora. Notificaciones a elegir: «Cada escaneo», «Pasos importantes» o «Solo la entrega». Peek no puede contactar con J&T ni con el vendedor por ti.

## Preguntas frecuentes sobre el seguimiento de J&T Express en Oriente Medio

### ¿Es fiable J&T Express en Arabia Saudí y EAU?

Sí. J&T Express nació en Indonesia en agosto de 2015; en Arabia Saudí opera como *JNT Express KSA LLC* en Riad (registro mercantil `1010728810`) y en Emiratos como *J&T Express Couriers Services LLC* en Dubái, con códigos iniciados por `JTE`.

### ¿Qué significa `Order creation` en el seguimiento de J&T?

El remitente creó el envío en J&T, pero J&T todavía no ha recogido el paquete («Etiqueta creada» en Peek). Si pasan días sin cambios, consulta a la tienda.

### ¿Cuánto tarda J&T en entregar en Arabia Saudí y EAU?

Su estándar para el primer intento en Arabia Saudí es de 24 h en Tier 1, 48 h en Tier 2 y de 72 a 96 h en Tier 3, Tier 4 y zonas remotas; en EAU es de 24 h en zonas normales y de 3 a 7 días laborables en zonas remotas.

### ¿Qué pasa si no estoy cuando llega el repartidor de J&T?

Según sus condiciones publicadas, J&T realiza hasta tres intentos entre las `09:00` y las `22:00`, retiene el paquete hasta 7 días laborables si no logra localizarte y después lo devuelve al remitente.

### ¿Cómo contacto con J&T Express en EAU, Arabia Saudí o Kuwait?

En EAU llama al `600566122`, escribe a `cs@jtexpress.ae` o por WhatsApp al `+971 526477476`; en Arabia Saudí llama al `800-1010-111` (o `920051211`) o escribe a `cs@jtexpress.sa.com`; en Kuwait llama al `+965 65850830` o escribe a `cs.kw@jtexpress.ae`.

:::sources
- [J&T Express Middle East: Track your shipment](https://www.jtexpress.me/KSA/trackOrder) – página de seguimiento y secciones por país
- [J&T Express Middle East: Contact Us](https://www.jtexpress.me/KSA/contact-us) – teléfonos, correos y WhatsApp (Arabia Saudí, EAU, Kuwait, Catar, Jordania y Baréin), 920051211 y escalations@jtexpress.sa.com
- [J&T Express Middle East: Shipping Information (Arabia Saudí)](https://www.jtexpress.me/KSA/shipping-information) – primer intento por Tier, límites de 30 kg / 128 cm / 274 cm y topes de J&T Special Delivery (200 / 300 SAR)
- [J&T Express Middle East: Shipping Information (EAU)](https://www.jtexpress.me/UAE/shipping-information) – plazos en zonas normales y remotas, dimensiones y sede de J&T Express Couriers Services LLC en Dubái
- [J&T Express Middle East: Shipping Information (Kuwait)](https://www.jtexpress.me/KW/shipping-information) – misma tabla saudí expresada en SAR
- [J&T Express Middle East: Terms of Use (Arabia Saudí)](https://www.jtexpress.me/KSA/terms-conditions) – JNT Express KSA LLC en Riad, horario 09:00–22:00, 3 intentos, custodia de 7 días laborables, +2 días en festivos, recogida a las 14:00, contra reembolso, aduanas y límite de 200 SAR en 7 días
- [J&T Express Middle East: Terms of Use (EAU)](https://www.jtexpress.me/UAE/terms-conditions) – condiciones publicadas en EAU y teléfonos 600566122 y +965 65850830
- [J&T Express Middle East: Política de compensación (PDF en árabe)](https://www.jtexpress.me/%D8%B3%D9%8A%D8%A7%D8%B3%D8%A9%20%D8%A7%D9%84%D8%AA%D8%B9%D9%88%D9%8A%D8%B6%20%D9%84%D8%B4%D8%B1%D9%83%D8%A9%20%D8%AC%D9%8A%20%D8%A7%D9%86%D8%AF%20%D8%AA%D9%8A.pdf) – registro 1010728810, consulta tras 7 días laborables, reclamación en 30 días a claim@jtexpress.sa.com, plazos de 7 y 16 días, tope 200 / 10.000 SAR y exclusiones
- [J&T Express Middle East: About Us](https://www.jtexpress.me/KSA/about-us) – fundación del grupo en Indonesia en agosto de 2015
- [J&T Express Middle East: Import Express](https://www.jtexpress.me/KSA/expressShipping/internationalExpress/importExpress) – envíos puerta a puerta desde China a Arabia Saudí
- [J&T Express Middle East: E-Commerce Partners](https://www.jtexpress.me/KSA/e-commerce-partners) – entrega en el día, básica y económica hasta 30 kg para tiendas online
- [Universal Parcel Scraper: J&T Express Middle East](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/j-and-t-middle-east) – formato JTE, huso GMT+0300, estados y lectura en Peek
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formatos de otras redes J&T y servicios de seguimiento de respaldo
:::
