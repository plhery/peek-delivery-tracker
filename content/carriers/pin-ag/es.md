---
title: Seguimiento PIN AG: cartas certificadas, paquetes y retiro
description: Sigue una carta o paquete de PIN AG en Alemania: qué envíos tienen seguimiento, qué significan sus estados en alemán, recogida en depósito y contacto.
slug: seguimiento-pin-ag
published: 2026-11-23
updated: 2026-11-23
---

PIN AG es una empresa postal de Berlín, independiente de Deutsche Post, con unos 1.300 empleados. Reparte cartas, cartas certificadas (Einschreiben), paquetes y pedidos de Amazon, medimops y rebuy. Solo una parte de estos envíos dispone de seguimiento. Consulta un envío en la [página de seguimiento de PIN AG](https://sendungsverfolgung.pin-ag.de/) (Sendungsverfolgung) o pega el número en el cuadro de arriba. El código procede del remitente: una tienda lo incluye en su correo de envío y, en una carta certificada, hay que pedírselo a quien la mandó.

## ¿Qué envíos de PIN AG se pueden seguir?

PIN solo sigue envíos que requieren prueba de entrega, desde que los recibe hasta el reparto. Lo decide el remitente al elegir el producto. Las páginas de ayuda de PIN enumeran:

| Con seguimiento | Sin seguimiento |
| --- | --- |
| Cartas certificadas: Einschreiben-Einwurf, -Übergabe, -Persönlich, -Rückschein y -Persönlich-Rückschein | Cartas ordinarias (Standardsendungen) |
| Notificaciones formales (Postzustellaufträge): cartas oficiales, por ejemplo de un tribunal o una administración | Envíos de libros y mercancías (Bücher- und Warensendungen) |
| Paquetes (Pakete) | Pedidos de medimops y rebuy |
| Envíos de Amazon Prime, en toda Alemania | Envíos ordinarios de Amazon fuera de Berlín |
| Envíos de Amazon dentro de Berlín | Correo publicitario (Werbepost) |

Las preguntas frecuentes de la página de seguimiento de PIN discrepan en un punto: dicen que los pedidos ordinarios de Amazon (sin Prime) no tienen seguimiento y no hacen excepción para Berlín. Por eso un pedido ordinario de Amazon puede no mostrar ningún estado.

## Número de seguimiento de PIN AG

Los formatos que Peek reconoce (dígitos inventados):

| Número (inventado) | Qué es |
| --- | --- |
| `0100482917364058` | 16 dígitos que empiezan por `0100`: un número de paquete PIN, según lo lee el código de Peek. Los 16 dígitos también encajan con [DHL eCommerce](carrier:dhl-ecommerce) y [TNT](carrier:tnt). |
| `4829173640582` | 13 dígitos: una carta certificada o una notificación formal. Muchos otros transportistas también usan 13 dígitos. |
| `482917364058(2)` | Los mismos 13 dígitos tal como puede imprimirlos una etiqueta, con el último dígito entre paréntesis. Peek los lee como `4829173640582`. |

Si el correo de la tienda menciona [DHL](carrier:dhl) u otro transportista, el código es suyo. ¿No tienes número? Consulta [cómo encontrar tu número de seguimiento](guide:find-tracking-number).

## Estados de seguimiento de PIN AG explicados

La página de seguimiento de PIN muestra una barra de progreso con cuatro pasos (`Sortierzentrum`, `Zustelldepot`, `In Zustellung` y después `Zugestellt`, o `Unzustellbar` si no puede entregarse) junto con el texto de estado en alemán. Qué significa cada uno:

| PIN indica | Qué significa para ti |
| --- | --- |
| `Sortierzentrum` | Tu envío está en el centro de clasificación de PIN. |
| `Zustelldepot` | Está en el depósito de reparto de tu zona. |
| `In Zustellung` | En reparto. PIN suele entregarlo ese mismo día: sus repartidores trabajan hasta la tarde. Si falla la entrega, el repartidor vuelve a intentarlo al día siguiente. |
| `Zugestellt`, `Die Sendung wurde zugestellt.` | Entregado. |
| `Unzustellbar` | No se pudo entregar. PIN devuelve al remitente los envíos no entregables. |
| `Die Sendung wurde in unserem Sortierzentrum erfasst und wird ohne weitere Aktualisierung der Sendungsverfolgung in den kommenden Tagen zugestellt. Hinweis: Bitte beachten Sie, dass es sich um eine Sendung handelt, bei dem der Zustellverlauf nicht dokumentiert wird. Für zusätzliche Informationen schauen Sie gerne in unsere FAQ.` | PIN lo ha registrado en el centro de clasificación y lo entregará en los próximos días sin más actualizaciones de seguimiento: PIN no registra el reparto de este tipo de envío. |

Este último texto aparece en el paso `In Zustellung`, pero el envío aún no ha salido a reparto.

## Entrega de PIN AG: buzón, sustituto y depósito

**Quién entrega.** En Berlín, PIN suele entregar una carta al día siguiente de procesarla en su centro de clasificación. Cuando PIN traspasa el envío a un socio repartidor (Zustellpartner) fuera de Berlín, la entrega puede tardar algo más y el estado puede dejar de actualizarse, incluso en cartas certificadas ([por qué el seguimiento deja de actualizarse](guide:tracking-not-updating)). Un pedido ordinario de Amazon fuera de Berlín viaja como carta simple, así que su estado no cambia nunca.

**Cómo.** PIN deja el envío en tu buzón o te lo entrega en mano. Si no es posible, puede entregarlo a un sustituto: un familiar, alguien de tu hogar, un empleado de tu negocio o el propietario o arrendador de tu vivienda. No lo hace si debe entregarse en persona ni si tú o el remitente indicasteis lo contrario. Las cartas certificadas se entregan según las reglas de su modalidad. PIN no puede entregar en Packstation.

**Recogida en depósito.** Cuando PIN no puede entregar, guarda el envío en un depósito de PIN (Zustelldepot) o de un socio y te avisa mediante una tarjeta (Benachrichtigungsschein).
- Acude desde el siguiente día laborable dentro del horario de apertura. La tarjeta indica la dirección y el horario del depósito, igual que la página de seguimiento de PIN al introducir tu número.
- Lleva documento oficial de identidad con foto (obligatorio) y la tarjeta. ¿Sin tarjeta? Necesitarás el número de seguimiento y el día del intento de entrega.
- Otra persona puede recogerlo con la autorización de la tarjeta (Vollmacht) y su propio documento con foto.
- PIN lo guarda 7 días laborables (de lunes a sábado), contados desde el día siguiente al intento. Una notificación formal espera 3 meses y se considera entregada el día en que quedó en el depósito.
- Pasado ese plazo, el envío vuelve automáticamente al remitente.

## Envío de PIN AG retrasado, desaparecido o devuelto

Para un reemplazo o reembolso tu contacto es el remitente, y PIN señala que es la vía más rápida.

- **No ha llegado tras 8 días laborables.** Cuenta de lunes a sábado desde el día de envío. Pide al remitente un nuevo envío o un reembolso. También puedes enviar a PIN una solicitud de búsqueda (Nachforschungsauftrag) mediante su formulario online. La búsqueda puede tardar hasta 21 días y PIN puede no localizarlo. Según las condiciones de PIN, un envío se presume perdido si no se entrega en 20 días desde su entrega a PIN y no aparece. Consulta [paquetes perdidos](guide:lost-parcel).
- **`In Zustellung` durante más de tres días.** PIN pide que te pongas en contacto usando el formulario enlazado en su página de seguimiento.
- **Entregado pero no está.** Pregunta a quienes viven contigo: PIN puede entregar a un sustituto. En envíos de Amazon, PIN indica que su página de seguimiento muestra dónde se entregó, por ejemplo a un vecino, si introduces el número y tu código postal. Un error técnico también puede mostrar un envío como entregado antes de tiempo: espera un día, ya que suele llegar al día siguiente. Más en [entregado pero no recibido](guide:delivered-not-received).
- **Dañado.** Comunícalo a PIN con el formulario «Hinweis zur Briefzustellung», que también sirve para correo retrasado, mal entregado o irregular. PIN solo responde en algunos casos. Si el contenido está dañado, contacta con el remitente.
- **Devuelto.** PIN devuelve envíos si no hay buzón con nombre ni ranura en la puerta, si nadie los recoge a tiempo o si están demasiado dañados para entregarse. Los envíos de Amazon también vuelven a Amazon si la dirección es errónea o incompleta, si tu nombre no está en el buzón, si el artículo no cumple el formato de carta pactado con Amazon o si va dirigido a una Packstation. PIN no puede cambiar la dirección durante el reparto: pide a Amazon una nueva entrega. Consulta [devuelto al remitente](guide:returned-to-sender).

## Contacto y atención al cliente de PIN AG

Peek no puede contactar con PIN AG ni con el remitente por ti. Canales publicados por PIN AG:

- **ServiceCenter:** 030 577978-800, de lunes a viernes de 8:00 a 18:00 (hora alemana).
- **Formularios online:** solicitud de búsqueda (Nachforschungsauftrag) e informe de entrega (Hinweis zur Briefzustellung), enlazados en las páginas de ayuda de PIN, más los formularios de contacto de la página de seguimiento.
- **Sede central:** PIN AG, Alt-Moabit 91, 10559 Berlín. Teléfono 030 577978-0, correo info@pin-ag.de.

## Seguir un envío de PIN AG con Peek

Pega el número en [Peek](/), con o sin paréntesis: Peek une automáticamente un último dígito entre paréntesis al resto.

- **16 dígitos que empiezan por 0100.** Como otros transportistas también usan 16 dígitos, Peek pregunta a los más probables según tu país (la región de tu teléfono o, en la web, la zona horaria de tu navegador). En Alemania pregunta primero a PIN AG. Mientras consulta aparece «Comprobando servicios de seguimiento…». Si PIN tiene el envío, verás «PIN AG tiene este paquete.» y Peek lo guarda ahí. Fuera de Alemania, Peek consulta a PIN AG solo cuando los demás transportistas no conocen el número.
- **Todavía no aparece.** Añádelo de todos modos. En Alemania, cuando Peek consulta a PIN y el número aún no figura, el envío muestra «Esperando el primer escaneo de PIN AG» y «Probablemente PIN AG», y Peek sigue consultando a PIN. Pasada una semana, Peek te pide verificar el número con el remitente o elegir otro transportista.
- **13 dígitos.** Peek no puede identificar PIN solo por esta longitud. Pégalo igualmente: Peek sigue a más de 4.500 transportistas. Pulsa «Cambiar» y elige PIN AG (escribe PIN). En un envío ya añadido, usa «Cambiar transportista».
- **Un enlace a la página de seguimiento de PIN** con tu número identifica PIN AG al instante («Transportista detectado»).

**Qué muestra Peek.** El estado de PIN y cada paso con su hora, en el alemán original de PIN y en hora alemana. `Sortierzentrum`, `Zustelldepot` y el aviso sin más actualizaciones aparecen como «En tránsito», `In Zustellung` como «En reparto», `Zugestellt` como «Entregado» y `Unzustellbar` como «Incidencia». Un envío que espera en depósito (la página de PIN muestra entonces dirección y horario del depósito) aparece como «Listo para recoger», sin importar el paso en que lo coloque PIN. PIN no ofrece lugares de escaneo, fecha estimada de entrega, peso ni firmante. Peek omite tu dirección y la del depósito: para ver el depósito, usa «Abrir la web de PIN AG», que abre tu número en la página de PIN. Como las cartas ordinarias no tienen seguimiento en PIN, Peek tampoco puede seguirlas.

Mientras tengas el envío abierto en Peek, o el día de entrega si las notificaciones están activadas, Peek comprueba hasta cada 10 minutos, y a partir de las 6:00 (hora alemana) del día en que PIN escanea el envío en reparto hasta cada 2 minutos mientras el rastreador propio de PIN responda. Después de dos días sin novedades (12 horas mientras siga en «Etiqueta creada» o «Admitido por el transportista»), Peek comprueba cada hora; y si permanece siete días en «Etiqueta creada», cada 6 horas.

Si un envío aún en «Etiqueta creada», «Admitido por el transportista» o «En tránsito» pasa cuatro días sin un nuevo escaneo, Peek lo marca con «Sin novedades desde hace cuatro días» (o «Sin novedades desde el …»), explica «Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a PIN AG.» y muestra «Pide ayuda a PIN AG». Puedes activar avisos de «Cada escaneo», «Pasos importantes» o «Solo la entrega».

## Preguntas sobre el seguimiento de PIN AG

### ¿Por qué no puedo seguir mi carta de PIN AG?

PIN sigue cartas certificadas, notificaciones formales, paquetes y envíos de Amazon Prime, y sus páginas de ayuda añaden los pedidos de Amazon dentro de Berlín. Otras cartas, como las ordinarias, no llevan seguimiento porque así lo eligió el remitente. PIN estima que una carta ordinaria llega en los próximos días laborables.

### ¿Cuánto tarda PIN AG en entregar?

En Berlín, normalmente un día después de que el centro de clasificación de PIN procese el envío. Cuando interviene un socio repartidor fuera de Berlín puede tardar un poco más. Si tras 8 días laborables (de lunes a sábado) desde el envío no ha llegado nada, contacta con el remitente.

### ¿Cuánto tiempo guarda PIN AG mi carta en el depósito?

7 días laborables (de lunes a sábado), contados desde el día siguiente al intento de entrega. Las notificaciones formales esperan 3 meses. Después el envío vuelve al remitente.

### ¿Por qué el seguimiento de PIN AG no encuentra estado para mi número?

Las preguntas frecuentes de PIN citan el mensaje `Für die Sendungsnummer konnte kein Zustellstatus gefunden werden.` El envío puede ser de un tipo sin seguimiento, como una carta ordinaria, el número puede tener un error o paréntesis, o puede superar los seis meses: PIN borra los datos de seguimiento tras seis meses por protección de datos.

### ¿Entrega PIN AG en una Packstation?

No, PIN AG no puede entregar en Packstations. Amazon suele enviar esos pedidos con Deutsche Post; si pese a todo llega uno a PIN, lo devuelve a Amazon.

:::sources
- [PIN AG: Sendungsverfolgung](https://sendungsverfolgung.pin-ag.de/) – pasos de progreso; FAQ: envíos con seguimiento, sin estado encontrado, entrega el mismo día, nuevo intento al día siguiente, formularios de contacto
- [PIN AG: Häufige Fragen zum Empfang von Briefsendungen](https://pin-ag.de/servicecenter/post-empfangen/sonstige-sendungen) – envíos seguidos, plazos de entrega, 8 días laborables, solicitud de búsqueda, recogida en depósito, devoluciones, formularios
- [PIN AG: Häufige Fragen zum Empfang von Amazon-, medimops- und rebuy-Sendungen](https://pin-ag.de/servicecenter/post-empfangen/amazon-sendungen) – envíos de Amazon, entregado pero no está, devoluciones, Packstation
- [PIN AG: ServiceCenter](https://pin-ag.de/servicecenter) – teléfono y horario
- [PIN AG: Allgemeine Geschäftsbedingungen](https://pin-ag.de/agb) – buzón, sustitutos, depósito, envíos no entregables, pérdida tras 20 días
- [PIN AG: Das Unternehmen](https://pin-ag.de/unternehmen) – entrega postal, unos 1.300 empleados, Berlín
- [PIN AG: Impressum](https://pin-ag.de/impressum) – dirección de la sede, teléfono y correo
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – qué lee Peek, pasos y textos de PIN, formatos de números
:::
