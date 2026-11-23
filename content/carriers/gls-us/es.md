---
title: Seguimiento GLS US (GSO): números, estados y entregas
description: Sigue un paquete de GLS US (también GSO): formato SH, qué significa cada escaneo, días de entrega, firmas, ausencias y contacto.
slug: seguimiento-gls-us
published: 2026-11-23
updated: 2026-11-23
---

GLS US (General Logistics Systems US, Inc.) es la empresa de paquetería de GLS Group en Estados Unidos, con reparto en el oeste del país. El catálogo de Peek también la conoce como GLS USA, GLS United States, General Logistics Systems US, GSO y Golden State Overnight, la empresa adquirida por GLS en 2016. Para seguir un paquete de GLS US, pega su número en el recuadro superior o introdúcelo en [la página de seguimiento de GLS US](https://gls-group.com/US/en/send-and-receive/track-a-parcel/).

## Número de seguimiento de GLS US: cómo es

El catálogo de Peek reconoce un formato para GLS US (dígitos inventados):

| Lo que pegas (inventado) | Qué hace Peek |
| --- | --- |
| `SH605182704936815247` | SH seguido de 18 dígitos, 20 caracteres en total. Solo GLS US usa esta forma en el catálogo de Peek, pero la coincidencia es débil, así que Peek pregunta a GLS US antes de nombrarlo. |
| `sh 6051 8270 4936 8152 47` | El mismo número. Minúsculas, espacios y guiones no importan. |
| Un número GLS US de otra forma | Peek no lo reconoce solo. Elige GLS US tú mismo (ver «Seguir un paquete de GLS US con Peek» más abajo). |
| Un enlace de seguimiento de gls-group.com con `/US/`, o de gls-us.com o gso.com | Peek extrae el número del enlace y nombra GLS US al instante («Transportista detectado»). |

**Dónde encontrarlo.** En el correo de envío o la página de pedido de la tienda, o en los correos o SMS de GLS si la tienda te dio de alta. Cada caja lleva su propia etiqueta, por lo que varias cajas implican varios números. La búsqueda sin número en GLS exige el número de cuenta y la referencia del remitente: pregunta a la tienda. Consulta [cómo encontrar tu número de seguimiento](guide:find-tracking-number). Un número de pedido no sirve.

**Distinto del GLS europeo.** GLS US pertenece a GLS Group, y Peek sigue sus paquetes en el rastreador propio de GLS US. Un enlace de gls-group.com con `/EU/` o `/DE/` lleva a Peek a [GLS Germany](carrier:gls-de), no a GLS US. Si el correo de la tienda menciona una filial GLS europea u otro transportista, síguelo allí. [Formatos de números de seguimiento](guide:tracking-number-formats) muestra otros formatos.

## Qué significan los estados de seguimiento de GLS US

Cada escaneo en el rastreador de GLS US incluye fecha y hora, y la mayoría nombran lugar: ciudad, estado y país. Los escaneos conocidos por Peek están en inglés y en mayúsculas, en orden habitual:

| GLS US muestra | Qué significa para ti | Peek muestra |
| --- | --- | --- |
| `LABEL CREATED` | El remitente creó la etiqueta. Puede que GLS US aún no tenga el paquete. Este escaneo no lleva lugar. | «Etiqueta creada» |
| `ARRIVAL SCAN - DELIVERY SCHED FOR 07/15/2026` | GLS US escaneó el paquete al llegar. La fecha final (mes primero) es el día programado para la entrega. | «En tránsito» |
| `SCANNED AT DELIVERY DEPOT` | Escaneado en el depósito encargado de entregarlo. | «En tránsito» |
| `ON ROUTE FOR DELIVERY` | En ruta de reparto hoy. | «En reparto» |
| `SHIPMENT DELIVERED` | Entregado. | «Entregado» |

**Estado general del envío.** Sobre los escaneos, el rastreador resume el envío en pocas palabras: `label created`, `in transit`, `out for delivery`, `delivered` o `returned`. Si ese resumen va por delante de los escaneos, Peek se guía por él: `delivered` muestra «Entregado» aunque el último escaneo no lo indique, y `returned` muestra «Devuelto al remitente».

**Otras líneas.** El código de Peek no conoce textos específicos de GLS US para intento fallido, incidencia, retención o punto de recogida. Cualquier línea desconocida se muestra tal como la escribe GLS US y se clasifica con las reglas generales de Peek. Más en [significado de los estados de seguimiento](guide:tracking-statuses).

Si nada sigue a `LABEL CREATED` tras un día o más, Peek indica «El remitente aún no lo ha entregado a GLS US. El seguimiento empezará cuando lo haga.». Pregunta a la tienda.

**Correos y SMS de GLS.** Si la tienda te inscribe al crear la etiqueta, GLS te escribe cuando su depósito escanea el paquete (con fecha prevista), ante retrasos o intentos fallidos, al salir a reparto (con hora estimada) y al entregarse.

## Plazos de entrega, firmas y ausencias con GLS US

**Dirección.** GLS no entrega en apartados postales (*P.O. boxes*) y clasifica por código ZIP, por lo que uno incorrecto puede retrasar la entrega. Solo redirige un envío con autorización del remitente: pregunta a la tienda. Ver [cambiar una dirección de entrega](guide:change-delivery-address).

**Días.** De lunes a viernes; sábados solo en algunas zonas si el remitente lo solicita; nunca domingos. GLS cierra seis festivos estadounidenses (Año Nuevo, Memorial Day, Día de la Independencia, Labor Day, Acción de Gracias y Navidad). Un paquete previsto en festivo llega el siguiente día laborable.

**Horarios.** Dependen del servicio elegido por el remitente. Priority llega antes de las 10:30 del siguiente día laborable en la mayoría de zonas, y Ground antes de las 17:00 (18:00 en zonas rurales). En trayectos largos, Priority llega antes de las 10:30 del día programado y Ground al final del día. Las entregas de sábado llegan antes de las 10:30 en la mayoría de áreas.

**Firma.** La elige el remitente. Por defecto el repartidor recoge firma en empresas. En una casa se deja si nadie puede firmar y el sitio es seguro y seco; en edificios de apartamentos, en tu puerta o en la oficina de administración/alquiler si el conductor no accede. Con *Signature Required* no lo deja sin firma; con *Adult Signature Required* (necesaria para alcohol y tabaco), quien firma debe tener al menos 21 años.

**Entrega fallida.** ¿Faltaste a un intento o encontraste un aviso en la puerta? Reprograma con el enlace de los correos o SMS de GLS, o llama a la línea de destinatarios (abajo). Las páginas de GLS no indican número de intentos ni plazo de custodia, y presentan los GLS Points únicamente como puntos de depósito para remitentes. Ver [entregas fallidas](guide:missed-delivery) y [paquetes no recogidos](guide:uncollected-parcel).

## Paquete GLS US no encontrado, sin cambios, perdido o devuelto

**No encontrado.** El rastreador avisa cuando no conoce un número. Peek muestra entonces «Esperando el primer escaneo de GLS US» si dejaste un número SH a su detección, o «Esperando a GLS US» si elegiste GLS US tú mismo, y sigue consultando. Una etiqueta nueva suele tardar en aparecer. Si tras dos días de añadir un número SH sigue sin datos, Peek avisa: «Esperando la primera actualización del transportista». Si elegiste GLS US y dos días después sigue sin reconocerlo (sin otras fuentes), Peek indica: «Tras dos días, el transportista sigue sin reconocer este número. Revísalo, elige otro transportista o elimina el paquete.», pasando a revisar cada día y, tras una semana, cada semana. Comprueba el número con el correo de la tienda y confirma si ya se envió.

**Días sin noticias.** Tras cuatro días sin nuevos escaneos, un paquete en «Etiqueta creada», «Admitido por el transportista» o «En tránsito» muestra el aviso «Sin novedades desde hace cuatro días». Peek recuerda que los paquetes suelen quedar sin actividad en fin de semana y sigue consultando a GLS US. Ver [por qué el seguimiento no se actualiza](guide:tracking-not-updating).

**Entregado, pero no llegó nada.** ¿`SHIPMENT DELIVERED` sin paquete en tu puerta? Mira en el rastreador de GLS quién firmó, revisa alrededor de la puerta, cuarto de correo, oficina de administración y vecinos, y luego llama a la línea de destinatarios y avisa a la tienda. Ver [entregado pero no recibido](guide:delivered-not-received).

**Perdido o dañado.** Avisa primero a la tienda que contrató el envío para que gestione con GLS US o te envíe reemplazo. Solo el remitente puede reclamar, en un plazo de 90 días desde el envío; GLS busca resolver en 45 días. Por daños, GLS pide al remitente fotos del artículo, embalaje interior y caja: hazlas antes de tirar nada. Ver [paquetes perdidos](guide:lost-parcel) y [paquetes dañados](guide:damaged-parcel).

**Devuelto.** «Devuelto al remitente» significa que el paquete volvió a la tienda. Pide reembolso o nuevo envío. Ver [devuelto al remitente](guide:returned-to-sender).

## Atención al cliente y contacto de GLS US

En la [página de soporte al cliente de GLS US](https://gls-group.com/US/en/support/customer-support/):

- **Teléfono:** 1-800-322-5555, opción 2 (soporte a destinatarios: seguimiento, incidencias y ayuda con la entrega).
- **Horario:** de lunes a viernes de 7:00 a 19:00, y sábados de 7:00 a 16:00, hora del Pacífico.
- **Chat en vivo**, disponible en la FAQ de GLS. GLS no publica correo electrónico para destinatarios.

> **Estafas.** GLS nunca pide por correo pagar mercancías con tarjeta, pago online o transferencia, aunque en paquetes internacionales GLS o su agente pueden pedir aranceles e impuestos. Los sitios legítimos son gls-group.com y los enlazados desde allí. Denuncia falsificaciones en security@gls-us.com y lee [mensajes falsos de entrega](guide:delivery-scam-texts).

## Seguir un paquete de GLS US con Peek

**Un número SH.** Pégalo en el recuadro superior. Mientras Peek consulta a GLS US, verás «Comprobando servicios de seguimiento…». Si GLS US conoce el número, aparece GLS US con «tiene este paquete» y queda guardado bajo él. Si aún no lo conoce, verás «aún no encontrado · Peek seguirá comprobando». Añádelo igual: mostrará «Probablemente GLS US» hasta el primer escaneo.

**Otro número.** Pégalo igualmente: Peek sigue más de 4500 transportistas. Luego elige GLS US tú mismo:

1. Pulsa «Cambiar» junto a la línea del transportista y busca GLS US («GSO», «Golden State Overnight», «General Logistics Systems US» o «United States» también lo encuentran).
2. Selecciona GLS US: aparecerá «Elegido por ti». Inicia el seguimiento.

En un paquete ya añadido, usa «Cambiar transportista». Peek consulta a GLS US solo con números de 5 a 20 letras y dígitos.

**Qué ves.** Peek consulta el rastreador de GLS US solo con el número: sin cuenta ni código ZIP. Muestra el estado, cada escaneo con ciudad y estado en el mapa y, cuando GLS US lo proporciona, el día previsto de entrega hasta entregarse o devolverse. Omite nombre y dirección del destinatario, quién firmó, firma, prueba de entrega y referencia del remitente. Peek no transfiere paquetes de GLS US a otro transportista. Si GLS US agrupa varios envíos bajo un número, Peek sigue el del escaneo más reciente. «Abrir la web de GLS US», en la ficha del paquete, abre el número en el rastreador estadounidense de GLS.

**Horarios.** GLS US facilita las horas sin zona horaria. Peek usa la hora de la ciudad del escaneo o, si no la reconoce, la del estado (la principal si tiene más de una). Sin ciudad ni estado, usa el huso del escaneo previo más cercano o del siguiente; en última instancia, hora del Pacífico. Peek muestra cada hora en la zona horaria de tu dispositivo.

**Cuando GLS US no responde.** Si su rastreador falla, Peek consulta [servicios de seguimiento](guide:universal-tracker) y muestra su historial con sus propias palabras.

**Frecuencia.** Mientras tengas abierto el paquete, o el día de reparto con alertas activadas, y responda GLS US, Peek consulta hasta cada 10 minutos, o cada 2 minutos desde las 6:00 hora local el día que sale a ruta; en otro caso, cada hora. Mediante servicios de seguimiento, cada hora y hasta cada 15 minutos ese día si está abierto o con alertas activas. Tras dos días sin novedades (12 horas en «Etiqueta creada» o «Admitido por el transportista»), revisa cada hora. Las notificaciones cubren «Cada escaneo», «Pasos importantes» o «Solo la entrega».

Peek no puede contactar con GLS US ni la tienda, cambiar entregas ni abrir reclamaciones.

## Preguntas sobre el seguimiento de GLS US

### ¿Es GSO lo mismo que GLS US?

GLS compró Golden State Overnight (GSO) en 2016 y gso.com redirige hoy al sitio de GLS US. Fundada en 1995, GSO repartía en California, Arizona, Nevada y Nuevo México. Peek los trata como uno solo: lista GSO, Golden State Overnight, GLS USA, GLS United States y General Logistics Systems US como nombres alternativos de GLS US y lee enlaces de gso.com y gls-us.com como GLS US.

### ¿Cómo es un número de seguimiento de GLS US?

El formato que conoce Peek es SH seguido de 18 dígitos, como `SH605182704936815247` (inventado). Las páginas de GLS no fijan formato y pueden existir otros: pégalos igual y elige GLS US con «Cambiar».

### ¿Qué significa el arrival scan de GLS US?

`ARRIVAL SCAN - DELIVERY SCHED FOR 07/15/2026` indica que GLS US escaneó el paquete al llegar y programó su entrega para la fecha final, mes primero. Peek muestra «En tránsito».

### ¿Por qué Peek dice «Esperando el primer escaneo de GLS US»?

Tu número coincide con el formato de GLS US y Peek preguntó a GLS US, que aún no lo lista. Es normal en etiquetas nuevas y Peek sigue consultando. Si sigue sin aparecer tras una semana, Peek te avisa y recomienda confirmar el número con el remitente o elegir otro transportista.

### ¿Es GLS US lo mismo que GLS en Europa?

Pertenece al mismo grupo, GLS Group, pero Peek trata a GLS US como un transportista aparte con su propio rastreador. Las filiales europeas como [GLS Germany](carrier:gls-de) usan el suyo: sigue el paquete en la filial GLS que nombre el correo de la tienda.

:::sources
- [GLS US: Track a parcel](https://gls-group.com/US/en/send-and-receive/track-a-parcel/) – rastreador de EE. UU., búsqueda sin número, firmante
- [GLS US: About GLS US](https://gls-group.com/US/en/about-gls-us/) – empresa estadounidense de GLS Group, oeste de EE. UU., historia de GSO
- [GSO: gso.com](https://www.gso.com/) – redirige al sitio de GLS US
- [GLS US: Domestic shipping services](https://gls-group.com/US/en/Shipping/shipping-services/domestic/) – tiempos de entrega, sábados en algunas zonas
- [GLS US: Holiday schedule](https://gls-group.com/US/en/resources/holiday-schedule/) – días de reparto, festivos
- [GLS US: Parcel Tariff](https://gls-group.com/US/en/parcel-tariff/) – sin domingos, festivos, firmas, códigos ZIP, una etiqueta por caja, reclamos en 90 días
- [GLS US: FAQ](https://gls-group.com/US/en/support/faq/) – apartados postales, desvío, reprogramación, avisos de puerta, notificaciones, chat en vivo, reclamos
- [GLS US: Claims](https://gls-group.com/US/en/support/claims/) – reclama el remitente, 45 días, fotos
- [GLS US: Customer support](https://gls-group.com/US/en/support/customer-support/) – línea de destinatarios, horarios
- [GLS US: Find a GLS Point](https://gls-group.com/US/en/Shipping/shipping-tools/find-drop-point/) – solo entregas para remitentes
- [GLS US: Security advice](https://gls-group.com/US/en/security-advice/) – peticiones de pago, aranceles e impuestos, sitios legítimos, denuncias
- [Universal Parcel Scraper: GLS US](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/gls-us) – nombres, formato SH y coincidencia débil, enlaces, escaneos y estados, respuesta no encontrado, fecha prevista, varios envíos por número, husos, datos omitidos
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – redes europeas de GLS y enlaces, servicios de seguimiento como respaldo, relojes por ciudad
:::
