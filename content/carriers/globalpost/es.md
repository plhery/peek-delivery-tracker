---
title: Seguimiento GlobalPost: números GP, estados y entrega
description: Rastrea un paquete de GlobalPost: formato de los códigos GP y SDC, qué significan sus estados en inglés, tarifas, aduanas en España y seguimiento con Peek.
slug: seguimiento-globalpost
published: 2026-11-21
updated: 2026-11-21
---

GlobalPost, parte del grupo Auctane, es un servicio de envíos internacionales que las tiendas online contratan en plataformas como ShipStation y Stamps.com (GlobalPost llega a más de 220 países y declara haber entregado más de 60 millones de envíos). El paquete pasa primero por un centro de expedición de GlobalPost, donde le colocan la etiqueta internacional y la declaración aduanera; a partir de ahí lo transportan operadores postales y comerciales asociados hasta que un servicio postal o de mensajería local lo entrega en destino. El vendedor te facilita el código, que suele empezar por `GP`: pégalo en el recuadro superior. El localizador propio está en `goglobalpost.com/tracking`; en el catálogo de Peek también aparece buscando `Go GlobalPost`, `GoGlobalPost`, `Global Post`, `Go Global Post` o `GlobalPost UK`.

## Número de seguimiento de GlobalPost: cómo es

Peek reconoce los números actuales de GlobalPost por su formato: `GP`, cuatro letras del servicio, diez dígitos y `US` o `GB` según el país de salida. Con letras y cifras inventadas:

| Número (inventado) | Qué es |
| --- | --- |
| `GPECON4829105736US` | `GP`, cuatro letras, diez dígitos y `US`: un paquete de GlobalPost enviado desde Estados Unidos. |
| `GPSTND4829105736GB` | El mismo esquema acabado en `GB`: enviado desde el Reino Unido. |
| `SDC482910573649182` | `SDC` y 15 dígitos: una etiqueta antigua de GlobalPost. Por sí solo Peek no asigna transportista: selecciona GlobalPost pulsando «Cambiar». |
| `GPECON482910573US` | Solo nueve dígitos: Peek no reconoce ningún transportista. Comprueba si falta una cifra. |
| `LZ482910576US` | Un código postal estadounidense, no de GlobalPost: Peek señala a [USPS](carrier:usps). Si eliges GlobalPost a mano para él, Peek avisa de que «no emite números como este». |

Peek ignora espacios, guiones y minúsculas.

**Dónde encontrarlo.** En el correo de confirmación de envío del vendedor o en la página de tu pedido. Si no das con él, mira [cómo encontrar el número de seguimiento](guide:find-tracking-number) y [formatos de números de seguimiento](guide:tracking-number-formats).

**Un segundo número.** Bajo `Final Mile Tracking By:`, el rastreador de GlobalPost puede mostrar el código propio de la empresa de reparto final y también abre el envío buscando ese segundo número (mientras que Peek consulta GlobalPost solo con códigos `GP` o `SDC`).

## Estados de seguimiento de GlobalPost explicados

El rastreador de GlobalPost ordena los escaneos del más reciente al más antiguo bajo encabezados diarios, cada uno con hora, texto en inglés y casi siempre lugar, redactando con sus propias palabras las lecturas de los socios. En el orden habitual del viaje:

| GlobalPost dice | Qué significa para tu envío | Peek muestra |
| --- | --- | --- |
| `Label Created` | El vendedor ha generado la etiqueta; el paquete aún no se ha movido. | «Etiqueta creada» |
| `Data Received` | GlobalPost ha recibido los datos electrónicos del remitente, todavía no el paquete. | «Etiqueta creada» |
| `Arrived at Partner Facility`, `Departed Partner Facility` | Un operador colaborador lo ha escaneado: USPS de camino a GlobalPost o el correo del país de llegada. | «En tránsito» |
| `Arrived at GlobalPost Facility` | Un centro de expedición de GlobalPost tiene el paquete (pueden aparecer antes lecturas de USPS hacia allí). | «Admitido por el transportista» |
| `Departed GlobalPost Facility` | Ha salido de ese centro de GlobalPost. | «En tránsito» |
| `Departed from Origin Country` | Ha salido de Estados Unidos o del Reino Unido. | «En tránsito» |
| `In Transit`, `In-Transit` | En camino. | «En tránsito» |
| `Arrived in Destination Country` | Ha llegado al país de destino. | «En tránsito» |
| `In Customs` | En despacho aduanero de importación. | «En aduanas» |
| `Cleared Customs` | Aduana superada; el envío continúa. | «En tránsito» |
| `Out for Delivery` | En el reparto del día. | «En reparto» |
| `Attempted Delivery` | Se intentó entregar sin que nadie pudiera recogerlo. | «Intento de entrega» |
| `Exception, No Action Required` | Incidencia durante el reparto en destino que no requiere gestión por tu parte. | «Incidencia» |
| `Delayed, No Action Required` | Retraso comunicado sin que tengas que hacer nada. | «Incidencia» |
| `Delivered` | Entregado (o devuelto al remitente: ver abajo). | «Entregado» |

Algunas líneas añaden al final un aviso de GlobalPost indicando que las actualizaciones pueden tardar: Peek clasifica el estado que va delante manteniendo la frase entera, y un registro posterior a la puesta en marcha no hace retroceder el estado. Cualquier otro texto se lee con las reglas comunes de Peek.

**Devoluciones.** El rastreador de GlobalPost también cierra una devolución con `Delivered`, mostrando la localidad del destinatario en la cabecera aunque el propio escaneo lleve la población del remitente. Cuando esa población coincide con la del remitente en otro país y distinta ciudad que los del destinatario, Peek muestra «Devuelto al remitente».

## Quién reparte los paquetes de GlobalPost en España

GlobalPost entrega los envíos *Economy* y *Standard* a un operador postal (como USPS en Estados Unidos) para el tramo internacional. Sus páginas no publican qué empresa reparte en cada país, pero su rastreador sí puede nombrarla junto con su número local.

**Lo que enseña el seguimiento depende de la modalidad** elegida por la tienda:

| Modalidad | Seguimiento | Aranceles e impuestos | Plazo que GlobalPost da al vendedor |
| --- | --- | --- | --- |
| `Economy` | hasta entrar al país y salir de aduanas | pagas tú (`DDU`) | `10 – 14 business days` |
| `Standard` | hasta tu puerta | pagas tú (`DDU`) | `6 – 10 business days` |
| `Plus`, `Plus – Duties & Taxes Included` | hasta tu puerta | paga el vendedor (`DDP`) | `5 – 8 business days` |
| `Flat Rate (formerly Go)` | hasta tu puerta | incluidos en el precio del vendedor | `6 – 10 business days` |

Desde Estados Unidos, *GlobalPost Plus – Duties & Taxes Included* está disponible hacia **España** (además de Canadá y otros países europeos), mientras que *Flat Rate (formerly Go)* llega a Canadá, Australia, Nueva Zelanda, Hong Kong y México. Los intentos de entrega y la custodia en oficina siguen las reglas del operador local: consulta [Correos](carrier:correos-spain), [entrega fallida](guide:missed-delivery) y [paquete no recogido](guide:uncollected-parcel).

**Aduanas en España y la Unión Europea.** Un envío procedente de Estados Unidos o de Gran Bretaña pasa por aduanas al entrar en la Unión Europea (**Irlanda del Norte**, en cambio, recibe el trato de territorio de la UE a efectos aduaneros y de IVA sobre mercancías según la Comisión Europea). En *Economy* y *Standard* (*Delivered Duties Unpaid*), los aranceles e impuestos corren a cargo del comprador. En la **Unión Europea**, desde el 1 de julio de 2026 las compras online procedentes de fuera de la UE en paquetes con valor intrínseco de hasta 150 € abonan un arancel aduanero de la UE de 3 € por artículo (*per item*), y desde el 1 de noviembre de 2026 se añade una tasa de tramitación de la Unión (*Union handling fee*) de 2 € por artículo cualquiera que sea el valor (un artículo agrupa mercancías del mismo envío con idéntica clasificación arancelaria, descripción y, cuando sea exigible, origen; por encima de 150 € rigen los aranceles ordinarios), además del IVA de importación en **España peninsular y Baleares**, salvo que ya se cobrara en la compra mediante IOSS. Según el ámbito territorial publicado por la Comisión Europea, las **Islas Canarias** pertenecen al territorio aduanero de la UE pero quedan fuera del área de IVA comunitaria, mientras que **Ceuta** y **Melilla** quedan fuera tanto del territorio aduanero como del ámbito de IVA de la UE. Las páginas de GlobalPost no detallan cómo se repercute la tasa de 2 €: pregunta a la tienda si estaba incluida en tu pago y consulta [paquete retenido en aduanas](guide:customs) (fuera de la UE se aplican las normas aduaneras de cada país).

## Paquete de GlobalPost parado, perdido o devuelto

**«Esperando a GlobalPost».** Mientras el rastreador redirija indicando que no localiza detalles para ese número (`status=not_found`), Peek muestra «Esperando a GlobalPost» y sigue comprobando. Si tras dos días GlobalPost sigue sin reconocerlo y ningún servicio de seguimiento lo encuentra, Peek avisa: «Tras dos días, el transportista sigue sin reconocer este número. Revísalo, elige otro transportista o elimina el paquete.» Si solo figura `Label Created` durante varios días, pregunta al vendedor si ya lo envió.

**Días sin actualizar.** GlobalPost señala que las lecturas internacionales pueden demorarse (meteorología, festivos, huelgas o aduanas) y que en *Economy* el rastreo termina al superar la aduana de destino. Tras cuatro días sin escaneos nuevos en ruta, Peek muestra el día del último escaneo («Sin novedades desde el lun 12 oct») recordando los silencios habituales en vuelos largos, aduanas o fines de semana; pasadas tres semanas sin nada nuevo, aconseja contactar con el vendedor. No añade enlace de ayuda de GlobalPost; cuando Peek pasa a seguir el envío en la empresa de reparto y conoce su página de soporte, enlaza esa. Mira [por qué no se actualiza el seguimiento](guide:tracking-not-updating).

**Sin llegar a los 30 días.** Si el paquete no ha llegado pasados 30 días desde el envío, GlobalPost pide acudir al vendedor: él puede investigar y reclamar a GlobalPost dentro de los `90 days` posteriores a la expedición, con una resolución de hasta `3 weeks`. Lee [paquete perdido](guide:lost-parcel).

**Marcado como entregado pero no recibido, o no entregable / devuelto.** Si consta entregado y no está, consulta primero a la empresa de reparto indicada y luego al vendedor ([entregado pero no recibido](guide:delivered-not-received)). Ante dirección errónea o incompleta, aranceles o impuestos sin pagar, avisos aduaneros desatendidos o artículos restringidos, el correo de destino gestiona el envío (suele devolverlo o retenerlo en oficina) sin facilitar a GlobalPost nueva fecha ni nuevo número de retorno; las condiciones de GlobalPost excluyen los paquetes no entregables (*undeliverable*) de las reclamaciones, así que solicita el reembolso o reenvío a la tienda ([devuelto al remitente](guide:returned-to-sender)).

## Atención al cliente y contacto de GlobalPost

En su página *Contact Support*, GlobalPost atiende por estas vías:

- **Correo electrónico**: `info@goglobalpost.com`.
- **Teléfono**: `(888) 899-1255`, de lunes a viernes de `6am` a `6pm` hora del Pacífico (*Pacific Time*).
- **Formulario de soporte**: en `www.goglobalpost.com/contact-support/`, elige `Recipient` e indica el número de seguimiento (GlobalPost advierte de que su equipo ve exactamente la misma información que la web pública de rastreo).

Por un paquete que no llega, GlobalPost remite primero al vendedor que contrató el transporte. Asimismo aclara que un envío salido de China con [China Post](carrier:china-post) no es un envío de GlobalPost: consulta en ese caso al remitente.

**Cuidado con los SMS falsos.** El dominio oficial de GlobalPost es `www.goglobalpost.com` (`goglobalpost.com`). Antes de pulsar enlaces recibidos por mensaje, revisa [mensajes falsos de entrega](guide:delivery-scam-texts).

## Seguir un paquete de GlobalPost con Peek

[Peek](/) reconoce al instante un código `GP` («Transportista detectado») y consulta el rastreador de GlobalPost sin cuenta ni código postal (la web guarda en caché unos minutos cada ficha localizada). También admite enlaces `goglobalpost.com/track-detail/?t=` o `/track/?t=`; para una etiqueta `SDC` antigua, elige GlobalPost con «Cambiar». Si únicamente tienes el código de la empresa final, pégalo igual: Peek sigue a más de 4.000 transportistas.

Peek muestra el estado, cada escaneo con su lugar (recortando códigos postales), el país de destino y la empresa de reparto con su código cuando GlobalPost los indica. Descarta la barra de progreso, las poblaciones de origen y destino de la cabecera (leídas solo para detectar devoluciones) y los códigos postales; GlobalPost no ofrece fecha prevista de entrega, peso ni prueba de entrega. «Abrir la web de GlobalPost» abre el número en `goglobalpost.com`.

Las horas de GlobalPost vienen sin huso horario: Peek lee cada escaneo con el reloj de su lugar, o del tramo en el que iba el paquete, y lo muestra en el huso horario de tu dispositivo; en `peektracker.com` y en las apps de iPhone y Android, el historial acaba con «Horas en tu zona horaria: …».

Cuando GlobalPost nombra una empresa de reparto que Peek reconoce por ese nombre, la tarjeta dice «Entrega con …» y añade su código tras el `GP`, o «Anunciado a …, aún no recibido» mientras esa empresa solo tenga el anuncio sin escaneos propios. Si hay segundo número pero ningún operador reconocido por nombre, ante un código postal internacional válido Peek consulta al operador postal de destino — como [Correos](carrier:correos-spain) hacia **España** (en envíos hacia **México**, Peek consulta al operador postal emisor del código) — o al único transportista al que apunte el formato del código. En cuanto ese operador tiene un escaneo al menos tan reciente como el último de GlobalPost, Peek pasa a seguirlo allí conservando el historial de GlobalPost.

Mientras mantengas abierto el paquete o, el día de entrega, tengas las notificaciones activas, Peek comprueba cambios hasta cada 10 minutos —y hasta cada 2 minutos (cada 5 minutos cuando debe abrir el rastreador de GlobalPost en navegador) desde las 6:00 locales el día en que pasa a «En reparto» mientras responda el buscador de GlobalPost (tras pasar a la empresa de reparto, Peek sigue el ritmo de esta; solo mediante servicios de seguimiento: cada hora, cada 3 horas tras un día sin cambios y hasta cada 15 minutos el día de entrega con el paquete abierto o notificaciones activas)—; en los demás casos, y tras dos días sin novedades (12 horas en «Etiqueta creada» o «Admitido por el transportista»), consulta cada hora. Puedes activar avisos de «Cada escaneo», «Pasos importantes» o «Solo la entrega». Peek no puede contactar con GlobalPost ni con el vendedor por ti.

## Preguntas frecuentes sobre el seguimiento de GlobalPost

### ¿Por qué se detuvo mi seguimiento de GlobalPost después de aduanas?

Con *GlobalPost Economy*, el seguimiento concluye cuando el paquete llega al país de destino y supera la aduana, y GlobalPost avisa de que algunos envíos tienen seguimiento limitado allí. El paquete sigue en tránsito y puede entregarse sin nuevas líneas; si el rastreador facilita el número bajo `Final Mile Tracking By:`, Peek consulta a ese operador y tú también puedes hacerlo.

### ¿Quién entrega los paquetes de GlobalPost?

Un operador postal o mensajería del país de destino, que el rastreador de GlobalPost puede identificar junto con su número local. Si consta un código postal internacional sin socio reconocido por nombre, Peek consulta al operador postal correspondiente (Correos en España; en envíos a México, al operador postal que emitió el código).

### ¿Cuánto tarda en llegar un paquete de GlobalPost?

Sus preguntas frecuentes indican `5-6 days` a países cercanos y hasta `20 days` a destinos más lejanos, y a los vendedores les estima `10 – 14 business days` en *Economy*, `6 – 10 business days` en *Standard* y *Flat Rate*, y `5 – 8 business days` en *Plus* desde que su centro procesa el envío.

### ¿Es fiable GlobalPost?

Sí. GlobalPost forma parte de Auctane, se contrata desde plataformas como ShipStation y Stamps.com, envía a más de 220 países y afirma haber entregado más de 60 millones de paquetes.

### ¿Cómo contacto con GlobalPost?

Escribe a `info@goglobalpost.com`, llama al `(888) 899-1255` de lunes a viernes de `6am` a `6pm` hora del Pacífico o rellena el formulario de `goglobalpost.com/contact-support/` eligiendo `Recipient`. Si han pasado 30 días desde el envío sin entrega, GlobalPost pide acudir primero al vendedor.

:::sources
- [GlobalPost: FAQs](https://www.goglobalpost.com/faqs/) – Auctane, funcionamiento, países de Plus – Duties & Taxes Included y Flat Rate, seguimiento Economy hasta aduanas, plazos, devoluciones y reclamaciones en 90 días / 3 semanas
- [GlobalPost: Standard](https://www.goglobalpost.com/standard/) – comparativa de Economy, Standard, Plus y Flat Rate: seguimiento, cargos DDU/DDP y días laborables
- [GlobalPost: Contact Support](https://www.goglobalpost.com/contact-support/) – correo, teléfono, horario del Pacífico, opción Recipient en el formulario y nota sobre China Post
- [GlobalPost: Home](https://www.goglobalpost.com/) – más de 220 países y más de 60 millones de envíos entregados
- [GlobalPost: Track your package](https://www.goglobalpost.com/tracking/) – rastreador oficial y aviso de número no localizado
- [GlobalPost: Terms and Conditions](https://www.goglobalpost.com/conditions/) – centros de expedición, plazos estimados y exclusión de paquetes no entregables
- [Comisión Europea: Reforma aduanera de la UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – arancel de 3 € desde el 1 de julio de 2026 y tasa de 2 € desde el 1 de noviembre de 2026
- [Comisión Europea: Orientaciones sobre el arancel de 3 € y la tasa de la Unión (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – cobro de 2 € sin importar el valor, definición de artículo y exención de IVA mediante IOSS
- [Comisión Europea: Ámbito territorial del IVA y aduanas](https://taxation-customs.ec.europa.eu/taxation/vat/vat-directive/how-does-vat-work/territorial-scope_en) – Islas Canarias, Ceuta, Melilla e Irlanda del Norte
- [Universal Parcel Scraper: GlobalPost](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/globalpost) – formatos GP y SDC, estados, detección de devoluciones, husos horarios, caché y empresa final
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formato USPS y traspaso a la empresa de reparto o a Correos
:::
