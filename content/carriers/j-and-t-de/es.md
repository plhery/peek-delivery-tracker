---
title: Seguimiento J&T Express Alemania: número JTDE y estados
description: Rastrea un paquete de J&T Express en Alemania: formato del código JTDE, qué significa cada estado en inglés y qué hacer si se retrasa, se pierde o no llega.
slug: jt-express-alemania-seguimiento
published: 2026-11-16
updated: 2026-11-21
---

J&T Express Germany (J&T Express Germany GmbH, Fráncfort del Meno) es la red alemana del grupo de paquetería J&T Express y reparte envíos dentro de Alemania. Si una tienda envió tu pedido a una dirección alemana con J&T, el número de seguimiento empieza por `JTDE`: pégalo en el cuadro de arriba o consúltalo en el [localizador de J&T en jtexpress.de](https://www.jtexpress.de/tracking). El código te lo facilita el comercio que expidió el pedido, en el correo de envío o en la página del pedido.

## Número de seguimiento de J&T Express Germany: cómo es

Según las preguntas frecuentes de J&T, sus números de envío comienzan por JTDE. Peek conoce un único formato para ellos: `JTDE` seguido de 12 cifras, 16 caracteres en total. Con cifras inventadas:

| Número (inventado) | Qué es |
| --- | --- |
| `JTDE999999999980` | `JTDE` y 12 cifras: un paquete de J&T Express Germany. Peek identifica a J&T Express Germany al instante. |
| `JTDE99999999980` | Una cifra de menos: no tiene el formato de J&T Express Germany y Peek no reconoce ningún transportista. Revisa si falta algún dígito. |

Si un código tiene una cifra de menos o de más detrás de `JTDE` y ningún transportista responde por él, la ficha del envío en peektracker.com te lo advierte («Empieza como un número de seguimiento de J&T Express Germany, pero le falta un carácter: puede que tenga una errata. Compáralo con tu mensaje de envío.» o «… pero le sobra un carácter: puede que tenga una errata. Compáralo con tu mensaje de envío.»).

**Dónde encontrarlo.** J&T explica que lo proporciona el remitente y que aparece en la página del pedido de la tienda online, en el correo de confirmación de compra, en el correo o SMS de expedición, en los correos que anuncian o confirman la entrega y en la etiqueta del paquete. Si no das con él, J&T te remite al remitente o a la atención al cliente de la plataforma donde compraste. Consulta también [cómo encontrar el número de seguimiento](guide:find-tracking-number).

El buscador de J&T admite hasta 10 números en una misma consulta, separados por espacios o comas.

**Números parecidos de otras redes.** Las redes de J&T en otros países tienen sus propios buscadores y formatos: `JTUK` con 12 cifras pertenece a J&T Express UK, `JTAU` con 12 cifras a J&T Express Australia, `JTE` con 12 cifras a J&T Express Middle East, y `JT` seguido de 13 cifras lo emplea J&T en el Sudeste Asiático y en China (J&T Express China); también operan con redes propias mercados como J&T México, que inició operaciones oficialmente en 2022. Solo `JTDE` corresponde a la red alemana. Si el seguimiento de J&T devuelve `Keine Abfrageergebnisse` (`No tracking records yet` en inglés) o Peek sigue mostrando «Esperando a J&T Express Germany», contrasta el número con el mensaje de la tienda o consúltaselo al vendedor.

## Estados de seguimiento de J&T Express Germany explicados

En el localizador de J&T cada escaneo consta únicamente de fecha, hora y una frase, sin localidad. La web se abre en alemán y no dispone de versión en español; si cambias el idioma a inglés o chino en el menú superior, vuelve a cargar los eventos en ese idioma. Peek solicita las frases en inglés y las muestra tal cual, en el orden habitual del recorrido:

| J&T muestra | Qué significa para ti | Peek indica |
| --- | --- | --- |
| `The shipment has been successfully picked up and is being prepared for transportation` | J&T ha recogido el paquete en origen. | «Admitido por el transportista» |
| `The shipment arrives at the distribution center and is prepared for transport to the next destination.` | Ha llegado a un centro de clasificación de J&T. | «En tránsito» |
| `The shipment is dispatched from the transit center to the next destination` | Ha salido de ese centro rumbo a la siguiente escala. | «En tránsito» |
| `Your package has been loaded and is on its way to you` | Va en la furgoneta de reparto: en camino hacia tu dirección. | «En reparto» |
| `Delivery failed because the address could not be found` | El repartidor no pudo localizar tu dirección. | «Intento de entrega» |
| `Delivery failed, customer refused to accept` | El destinatario rechazó la entrega. | «Intento de entrega» |
| `Your parcel has been delivered to the pickup point. Please check your email for details` | El repartidor dejó el paquete en un punto de recogida; J&T envía los detalles por correo electrónico. | «Listo para recoger» |
| `Your package has been successfully delivered.` | Entregado. J&T añade cómo se dejó (ver abajo). | «Entregado» |
| `Returns have begun` | Tras una entrega fallida, el paquete inicia la vuelta al remitente. | «Devuelto al remitente» |
| `The shipment has been returned` | La devolución al remitente se ha completado. | «Devuelto al remitente» |

Cualquier frase que no esté en esta tabla se interpreta con las reglas comunes que Peek aplica a todos los transportistas; si tampoco así la reconoce, la muestra como la escribió J&T y mantiene el envío «En tránsito», salvo que ya estuviera en reparto o en una etapa posterior, en cuyo caso conserva el estado previo.

La línea de carga en furgoneta (`Your package has been loaded and is on its way to you`) puede repetirse varias veces: J&T la registra en cada salida a reparto. El evento de entrega continúa con la modalidad de entrega entre corchetes 【】 y termina con una frase sobre atención al cliente. Peek descarta esa frase final y conserva la modalidad, sin corchetes, únicamente cuando coincide con una de estas seis opciones:

- `Left with neighbor`: entregado a un vecino;
- `Front Door Porch`: delante de la puerta de entrada;
- `Front Desk Reception`: en recepción;
- `Garden Yard`: en el jardín o patio;
- `Mailbox`: en el buzón;
- `Garage`: en el garaje.

De ese modo, un envío dejado a un vecino se lee en Peek como `Your package has been successfully delivered. The delivery type is Left with neighbor.`

**¿Qué vecino lo tiene?** En el buscador de J&T, debajo de ese escaneo aparece `Zustelldetails anzeigen` (`Click to view delivery details` en la versión inglesa). Pulsa ahí e introduce el código postal de entrega para ver el nombre del vecino y su número de portal; tras cinco códigos postales erróneos hay que esperar antes de volver a intentarlo. Peek consulta solo con el número de envío y muestra únicamente que se dejó a un vecino, sin nombre ni dirección. También suprime cualquier nombre propio o teléfono que aparezca en las líneas de J&T.

## Entrega de J&T en Alemania: vecinos, lugar seguro y foto

En los historiales que conoce el código de Peek, el repartidor de J&T carga el paquete para su ruta y después lo entrega, lo deja en un punto de recogida o anota por qué falló el intento. El código de Peek no refleja traspasos a ningún otro transportista: el seguimiento de J&T acompaña al envío hasta su entrega. Quien llame al timbre puede trabajar para uno de los socios franquiciados de J&T: en su web alemana, J&T busca socios por toda Alemania cuyos equipos realicen entregas y recogidas de última milla.

**En reparto.** `Your package has been loaded and is on its way to you` indica que el paquete ha salido a reparto. En esos historiales aparece esa misma mañana, unas horas antes de la entrega. El localizador de J&T no facilita franja horaria ni día estimado de entrega, por lo que Peek tampoco puede mostrarlos.

**Preferencias de entrega.** El enlace incluido en la notificación de envío lleva a la [página de preferencias de entrega de J&T](https://www.jtexpress.de/delivery-preference) y contiene tu número de seguimiento cifrado. Tras confirmar tu código postal, puedes elegir entre las modalidades que ofrece J&T: entrega con firma a ti mismo, a un vecino (indicando su nombre y si vive en la puerta contigua o en la misma planta) o en recepción, o bien entrega sin firma delante de la puerta, en el jardín, en el buzón o en el garaje. Según J&T, el repartidor sigue tu indicación —evitando segundas entregas y entregas fallidas— y los datos introducidos se usan exclusivamente para ese envío. Si escoges un lugar sin firma, la página advierte de que el riesgo de pérdida pasa a ser tuyo en cuanto el paquete queda depositado allí. Si el estado del envío ya no admite cambios, la propia página lo avisa.

**Foto de entrega.** Según el aviso de privacidad de J&T sobre la prueba fotográfica de entrega (POD), el repartidor fotografía el paquete al entregártelo o al dejarlo en el punto acordado, encuadrando solo el paquete y la parte indispensable del lugar; cualquier persona que aparezca por accidente queda difuminada de forma automática e irreversible. Por regla general, J&T elimina la imagen a los 6 meses de la entrega, aunque puede conservarla hasta que se cierre una reclamación abierta dentro de ese plazo y mostrársela al remitente para resolver la incidencia.

**Rechazado o dirección no localizada.** Tras `Delivery failed, customer refused to accept`, en los historiales que conoce el código de Peek el repartidor volvió a cargar el envío para otro reparto a la mañana siguiente. Si aparece `Delivery failed because the address could not be found`, revisa la dirección de tu pedido. Si falta algún dato —como el piso o tu apellido en el timbre—, avisa enseguida a la tienda, que contrató el transporte con la dirección que le diste. Las preguntas frecuentes de J&T también piden contactar de inmediato con el remitente o con la plataforma de compra: una vez expedido el paquete, que la dirección pueda cambiarse depende de su estado de tránsito y de las normas del remitente. Después de un intento fallido también puede aparecer `Returns have begun`. Más ayuda en [cómo cambiar la dirección de entrega](guide:change-delivery-address) y [entrega fallida](guide:missed-delivery).

**Intentos y puntos de recogida.** La web de J&T no especifica cuántos intentos realiza, cuánto tiempo guarda un envío ni en qué horario reparte, y tampoco publica un mapa o listado propio de tiendas de paquetería, taquillas ni delegaciones. Sin embargo, en el seguimiento de J&T Express Germany puede aparecer `Your parcel has been delivered to the pickup point. Please check your email for details` cuando, tras salir a reparto, el repartidor deja el envío en un punto de recogida y J&T remite por correo electrónico los datos para recogerlo.

## Paquete de J&T retrasado, perdido o devuelto

La tienda eligió a J&T, así que en una compra online el vendedor es siempre tu primer contacto. Conforme a la legislación alemana (§ 475 del Código Civil alemán, BGB), cuando compras como consumidor a una empresa, el vendedor asume el riesgo de pérdida o daño durante el transporte, salvo que tú mismo hayas encargado el porte a un transportista que el vendedor no te hubiera propuesto antes.

**Sin movimiento.** J&T no publica plazos de entrega para destinatarios. Si un paquete permanece en «Admitido por el transportista» o «En tránsito» durante cuatro días sin nuevas líneas, Peek lo marca con el día de la última línea («Sin novedades desde el lun 12 oct») y añade el enlace «Pide ayuda a J&T Express Germany». Si sigue sin avanzar, escribe a la tienda o llama al teléfono de atención de J&T con tu código de seguimiento. Más detalles en [seguimiento que no se actualiza](guide:tracking-not-updating).

**Aún sin registros.** Un número que el buscador de J&T todavía desconoce figura como «Esperando a J&T Express Germany». Es habitual que las tiendas generen el número antes de entregar físicamente el paquete. Si pasados dos días J&T sigue sin reconocer el número y ninguna otra fuente responde por él, Peek muestra «Tras dos días, el transportista sigue sin reconocer este número. Revísalo, elige otro transportista o elimina el paquete.» y, a partir de ahí, Peek pasa a comprobarlo una vez al día, y una vez por semana cuando se cumple una semana desde que lo añadiste. Confirma entonces el código con la tienda y pregunta si el envío salió realmente.

**Entregado pero no recibido.** Revisa la línea de entrega en Peek: si nombra un lugar como `Mailbox` o `Garage`, comprueba ese sitio; si dice `Left with neighbor`, pregunta a los vecinos o consulta qué vecino lo recogió en la web de J&T introduciendo el código postal (ver más arriba). El FAQ de J&T recomienda además comprobar si familiares, vecinos o personal del edificio recepcionaron el paquete. ¿Sigue sin aparecer? Comunícalo en el teléfono de atención de J&T y solicita a la tienda el reembolso o un nuevo envío indicando el número de seguimiento. Lee también [paquete entregado pero no recibido](guide:delivered-not-received).

**Perdido, dañado o devuelto.** Pide a la tienda el reembolso o la sustitución con tu número de seguimiento. El FAQ de J&T no publica formulario de reclamación, plazos ni indemnizaciones para destinatarios: los plazos de aviso los fija la ley alemana. Según el § 438 del Código de Comercio alemán (HGB), un daño visible debe comunicarse a más tardar en el momento de la entrega (basta con decírselo a quien entrega el paquete), y un daño oculto dentro de los siete días siguientes en forma de texto, como un correo electrónico a J&T; enviarlo a tiempo basta para cumplir el plazo. Si no llegas a un acuerdo con J&T por una pérdida o avería, puedes acudir sin coste mediante formulario online a la junta de arbitraje postal de la Bundesnetzagentur (Schlichtungsstelle Post), abierta tanto a destinatarios como a remitentes. Ahora bien, los envíos de grandes tiendas online suelen viajar bajo condiciones especiales que excluyen el arbitraje; en ese caso puedes dirigirte igualmente al servicio de atención al consumidor postal de la Bundesnetzagentur (Verbraucherservice Post). Consulta [paquete perdido](guide:lost-parcel), [paquete dañado](guide:damaged-parcel) y [paquete devuelto al remitente](guide:returned-to-sender).

## Atención al cliente y contacto de J&T Express Germany

Canales de J&T Express Germany verificados en su [página de contacto](https://www.jtexpress.de/contact) el 16 de noviembre de 2026:

- **Teléfono:** +49 6931090650 (069 31090650 llamando desde Alemania), de lunes a viernes de 8:00 a 20:00, cerrado en festivos.
- **Correo electrónico:** `de-jtservice@jtexpress.de`.
- **Chat en vivo:** botón «Chat starten» («Start Chat» en la versión en inglés), disponible en las páginas de contacto y FAQ.
- **Fotos de entrega y protección de datos:** `de-dpo@jtexpress.de` (indicado en el aviso de privacidad POD).
- **Oficina:** J&T Express Germany GmbH, Am Giener 2, 55268 Nieder-Olm. El aviso legal (Impressum) recoge el domicilio social en Wiesenhüttenstraße 11, 60329 Frankfurt am Main.

J&T no detalla en qué idiomas atiende por teléfono o chat; su sitio web está en alemán, inglés y chino. Para cualquier compra online, empieza igualmente por la tienda, que fue quien contrató el envío con J&T; en un marketplace, recurre a la ayuda dentro del pedido. Cuando un paquete lleva días parado, el enlace «Pide ayuda a J&T Express Germany» de Peek abre directamente la página de contacto de J&T.

## Seguir un paquete de J&T Express Germany con Peek

[Peek](/) reconoce al instante a J&T Express Germany a partir de un código `JTDE` («Transportista detectado»), escrito con o sin espacios, en mayúsculas o minúsculas, y consulta el buscador de J&T únicamente con ese número, sin código postal ni cuenta. También admite enlaces de `www.jtexpress.de/tracking` con `?q=` y el número; si el enlace lleva otro tipo de código, Peek lo lee como si hubieras pegado el número solo. Si tu código no empieza por `JTDE`, pégalo igualmente: Peek sigue a más de 4.500 transportistas. Elegir J&T Express Germany con «Cambiar» para otro formato no sirve: Peek avisa de que «no emite números como este».

Peek muestra el estado actual, cada escaneo con su hora exacta y el momento de la entrega. Como J&T registra cada evento con un instante preciso, Peek lo convierte al huso de tu dispositivo; en peektracker.com y en las apps para iPhone y Android lo recuerda la última línea bajo el historial («Horas en tu zona horaria: …»). Los eventos se mantienen en el inglés original de J&T aunque en jtexpress.de los veas en alemán. J&T no comunica localidad, fecha prevista de entrega ni peso, de modo que Peek no muestra mapa. En historiales muy extensos conserva los 100 escaneos más recientes. «Abrir la web de J&T Express Germany», dentro de la ficha del paquete, abre tu número en el localizador de J&T.

Mientras J&T aún no haya escaneado el envío, Peek sigue consultando y muestra «Esperando a J&T Express Germany» (o, si tras dos días J&T sigue sin conocer el número, el aviso para revisarlo). Cuando el buscador de J&T no responde o desconoce el código, Peek recurre a servicios de seguimiento y presenta su historial con sus propias palabras. Si más adelante tanto J&T como los servicios de seguimiento responden sin escaneos para un envío que ya tenía eventos guardados, Peek conserva los ya encontrados y, si la situación se prolonga, indica: «El transportista no tiene escaneos nuevos por ahora. Se conservan los ya encontrados y Peek sigue comprobando.»

Peek consulta hasta una vez cada 10 minutos, y hasta una vez cada 2 minutos desde las 6:00 (hora local) el día en que J&T escanea el paquete en reparto mientras responda el propio buscador de J&T —o cada 15 minutos mediante servicios de seguimiento—. Tras dos días sin cambios (12 horas si continúa en «Admitido por el transportista»), pasa a revisar cada hora. Puedes activar avisos para «Cada escaneo», «Pasos importantes» o «Solo la entrega». Peek no puede contactar con J&T ni con la tienda por ti, cambiar la dirección ni concertar un nuevo reparto.

## Preguntas frecuentes sobre el seguimiento de J&T Express Germany

### ¿J&T Express Germany es una empresa fiable?

Sí. J&T Express Germany GmbH, registrada en Fráncfort del Meno, es la filial alemana de J&T Express, grupo de paquetería fundado en Indonesia en 2015 y cotizado en la Bolsa de Hong Kong. El regulador postal alemán, la Bundesnetzagentur, incluye a la sociedad en su registro oficial de operadores postales (Anbieterverzeichnis Post) desde el 1 de septiembre de 2026. J&T busca socios franquiciados en toda Alemania para el reparto y la recogida de última milla, y todas las frases de la tabla de estados anterior proceden de envíos reales de J&T Express Germany.

### ¿Qué vecino tiene mi paquete de J&T?

Te lo indica el propio buscador de J&T: bajo el escaneo de entrega, pulsa `Zustelldetails anzeigen` (`Click to view delivery details` en inglés) e introduce el código postal de destino para ver el nombre del vecino y su número de calle. Peek solo muestra `Left with neighbor`, sin nombre ni dirección.

### ¿Tiene J&T Express puntos de recogida o taquillas en Alemania?

Su web alemana no menciona ninguno, pero un evento de J&T Express Germany conocido por Peek indica que el repartidor dejó el paquete en un punto de recogida y que J&T envía los detalles por correo electrónico. Desde la página de preferencias de entrega puedes pedir que te lo entreguen en mano, a un vecino, en recepción o en un lugar seguro de tu domicilio.

### ¿Por qué no aparece mi número de seguimiento de J&T?

Comprueba que empiece por `JTDE` y lleve 12 cifras detrás: los códigos de otras redes de J&T, como `JTUK` en el Reino Unido, no pertenecen a la red alemana. Si el formato es correcto, puede que J&T aún no haya recibido físicamente el paquete: su web responde `Keine Abfrageergebnisse` (`No tracking records yet`) y Peek indica «Esperando a J&T Express Germany». Si pasados dos días ni J&T ni otra fuente reconocen el código, Peek te pide revisarlo, cambiar de transportista o borrar el envío. Si tras varios días sigue sin datos, consulta a la tienda.

### ¿Cómo contacto con J&T Express en Alemania?

Llama al +49 6931090650 (de lunes a viernes, de 8:00 a 20:00), abre el chat en vivo en jtexpress.de o escribe a `de-jtservice@jtexpress.de`. En pedidos online, acude primero a la tienda que contrató el envío.

:::sources
- [J&T Express Germany: Track & Trace](https://www.jtexpress.de/tracking) – localizador, hasta 10 números, idiomas de la web y recarga de eventos, mensaje sin resultados, datos del vecino tras validar el código postal y límite de cinco intentos
- [J&T Express Germany: Frequently Asked Questions](https://www.jtexpress.de/faq) – códigos JTDE y dónde encontrarlos, entregado pero no recibido, dirección errónea, chat en vivo; sin plazos de entrega, número de intentos ni reglas de reclamación
- [J&T Express Germany: Delivery Preferences](https://www.jtexpress.de/delivery-preference) – enlace en el correo de aviso, verificación de código postal, opciones con y sin firma, asunción del riesgo en el lugar elegido, menos segundas entregas y errores, uso de datos solo para esa entrega
- [J&T Express Germany: Contact Us](https://www.jtexpress.de/contact) – teléfono, horario, chat en vivo, correo electrónico y dirección de la oficina en Nieder-Olm
- [J&T Express Germany: Legal notice](https://www.jtexpress.de/legal-statement) – J&T Express Germany GmbH, Fráncfort del Meno
- [J&T Express Germany: POD Privacy Policy](https://www.jtexpress.de/pod-policy) – fotos de entrega, difuminado automático, borrado a los 6 meses, reclamaciones y contacto de protección de datos
- [J&T Express Germany: Become Our Partner](https://www.jtexpress.de/career) – socios franquiciados en toda Alemania para reparto y recogida de última milla
- [J&T Express Germany: About Us](https://www.jtexpress.de/about) – fundada en 2015 en Indonesia, cotización en Hong Kong y puesta en marcha de J&T México en 2022
- [Universal Parcel Scraper: J&T Express Germany](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/j-and-t-de) – nombres, formato JTDE, enlaces de seguimiento, frases leídas en paquetes reales y sus etapas, salidas a reparto, modalidades de entrega, datos descartados y página de ayuda
- [Universal Parcel Scraper: consulta compartida de las redes J&T](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/j-and-t-au) – frases en inglés, horas exactas, sin localidad, fecha prevista ni peso, supresión de nombres y teléfonos, 100 escaneos más recientes
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formatos de otras redes J&T, sin traspaso a otro transportista y servicios de seguimiento como respaldo
- [Gesetze im Internet: § 475 BGB](https://www.gesetze-im-internet.de/bgb/__475.html) – riesgo del transporte a cargo del vendedor en ventas de empresa a consumidor
- [Gesetze im Internet: § 438 HGB](https://www.gesetze-im-internet.de/hgb/__438.html) – aviso de daño visible en la entrega, daño oculto en siete días en forma de texto y cumplimiento del plazo con el envío a tiempo
- [Bundesnetzagentur: Anbieterverzeichnis Post](https://www.bundesnetzagentur.de/DE/Fachthemen/Post/Anbieterverzeichnis/PostAnbieterverzeichnis.html) – registro de proveedores postales: inscripción de J&T Express Germany GmbH el 1 de septiembre de 2026
- [Bundesnetzagentur: Häufig gestellte Fragen zur Schlichtung Post](https://www.bundesnetzagentur.de/DE/Vportal/Schlichtung/Schlichtung_Post/_FAQ_Schlichtung/faqSchlichtung.html) – solicitud online gratuita abierta también a destinatarios, condiciones especiales en envíos de grandes tiendas online y Verbraucherservice Post
:::
