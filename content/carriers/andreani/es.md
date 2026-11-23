---
title: Seguimiento Andreani: números y estados en Argentina
description: Sigue un envío de Andreani en Argentina: cómo son sus números de 15 dígitos, qué significa cada estado del seguimiento y qué hacer si se detiene.
slug: seguimiento-andreani
published: 2026-11-23
updated: 2026-11-23
---

Andreani es una empresa de envíos de paquetes en Argentina. El catálogo de Peek la incluye en Argentina y Brasil, también como Correo Andreani y Grupo Logístico Andreani. Si compraste por internet, la tienda puede enviarlo con Andreani: el número de seguimiento aparece en el correo de envío de la tienda o en la página de tu pedido. Pega el número, o un enlace de seguimiento de andreani.com, en la casilla de arriba. El rastreador propio de Andreani está en andreani.com; Peek consulta ese seguimiento y esta página explica cada paso.

## Número de seguimiento de Andreani: cómo es

Los números de Andreani que Peek conoce tienen 15 dígitos y empiezan por `36000`, `31000` o `30000`. Con cifras inventadas:

| Número (inventado) | Qué es |
| --- | --- |
| `360004829103756` | `36000` y 10 dígitos más: rango de Andreani, aunque con coincidencia débil. [BRT](carrier:brt) y Yunda también usan 15 dígitos, así que Peek pregunta a Andreani y a BRT. |
| `310004829103756` | `31000` y 10 dígitos más: también del rango de Andreani. |
| `300004829103756` | `30000` y 10 dígitos más: también del rango de Andreani. |
| `739402815634281` | 15 dígitos con otro inicio. Peek no lo asocia a Andreani: [FedEx](carrier:fedex), DPD Francia, [Hermes](carrier:hermes-de), BRT y Yunda también usan 15 dígitos. |

Los espacios no importan al pegar un número en Peek.

**Otras referencias.** El seguimiento de Andreani también admite otras referencias: códigos de Andreani con letras y referencias propias de los remitentes. Peek no puede saber por su forma que son de Andreani, así que elige Andreani tú mismo (ver «Seguir un paquete de Andreani con Peek» más abajo).

**Un enlace de seguimiento.** También sirve un enlace de andreani.com con `/envio/` y el número: Peek extrae el número del enlace.

¿No encuentras tu número? Consulta [cómo encontrar un número de seguimiento](guide:find-tracking-number).

## Estados del seguimiento de Andreani explicados

El seguimiento de Andreani agrupa sus avisos bajo encabezados en español. Los cuatro que Peek conoce son `Pendiente de ingreso`, `Ingresado`, `En camino` y `Entregado`. Cada línea es una frase breve dirigida a ti, con la sucursal y la localidad cuando corresponde. Las líneas que Peek reconoce, en el orden habitual:

| Andreani dice | Qué significa para ti | Estado en Peek |
| --- | --- | --- |
| `Tu envío se encuentra pendiente de ingreso.` | El remitente anunció el envío a Andreani, pero Andreani aún no tiene el paquete. | «Etiqueta creada» |
| `¡Ya tenemos tu envío! El circuito comienza en C.a.b.a., Buenos Aires. Pronto lo enviaremos a la sucursal encargada de la entrega.` | Andreani ya tiene tu paquete. El recorrido empieza en la localidad y provincia indicadas, y luego saldrá hacia la sucursal encargada de la entrega. | «Admitido por el transportista» |
| `Tu envío salió de la sucursal Monserrat (av Belgrano), en la localidad C.a.b.a. de la provincia Buenos Aires.` | Salió de la sucursal mencionada, en esa localidad y provincia. | «En tránsito» |
| `Tu envío se encuentra en camino a la sucursal La Plata (calle 2).` | Va rumbo a la sucursal indicada, que será la encargada de entregarlo. | «En tránsito» |
| `La sucursal La Plata (calle 2) ya está procesando tu envío. ¡Pronto tendrás novedades!` | La sucursal de entrega ya tiene el paquete y lo está procesando. Pronto habrá novedades. | «En tránsito» |
| `Hoy te visitaremos para entregarte tu envío.` | Andreani pasará hoy a entregar el paquete. | «En reparto» |
| `Ya entregamos tu envío. ¡Esperamos volver a verte pronto!` | Entregado. | «Entregado» |

Las sucursales y localidades de esta tabla son solo ejemplos: en tu envío verás las de tu recorrido. `C.a.b.a.` corresponde a la Ciudad Autónoma de Buenos Aires.

Peek muestra cada línea con el texto original de Andreani, bajo una etapa propia. Si aparece una línea que Peek todavía no conoce, toma la etapa de su encabezado: cualquier línea bajo `En camino` aparece como «En tránsito». Peek elimina los números de teléfono y de documento de identidad de las líneas. Más detalles en [qué significan los estados de seguimiento](guide:tracking-statuses).

## Cómo entrega Andreani en Argentina

Las líneas anteriores resumen el trayecto habitual:

1. **Ingresado.** Andreani recibe el paquete en el punto de inicio.
2. **Hacia la sucursal de entrega.** Lo envía a la `sucursal encargada de la entrega`. La línea que comienza por `Tu envío se encuentra en camino a la sucursal` indica cuál es.
3. **En la sucursal de entrega.** Cuando dice que esa sucursal `ya está procesando tu envío`, el paquete ya llegó allí.
4. **Día de entrega.** `Hoy te visitaremos para entregarte tu envío.` avisa de que pasarán hoy por tu domicilio.
5. **Entregado.** `Ya entregamos tu envío.` cierra el recorrido.

Peek no pudo leer el sitio web de Andreani, por lo que esta página no incluye plazos de entrega, reglas sobre entregas fallidas ni información sobre retiros en sucursal o días de guarda. Pregunta a la tienda o a Andreani sobre estos puntos. Ver también [entregas fallidas](guide:missed-delivery) y [paquetes no recogidos](guide:uncollected-parcel).

## Seguimiento de Andreani sin cambios, perdido o devuelto

**Solo `Pendiente de ingreso`.** El remitente creó el envío, pero aún no entregó el paquete a Andreani. Peek muestra «Etiqueta creada». Pregunta a la tienda si ya despachó tu compra.

**Sin ningún resultado.** Puede que el rastreador de Andreani aún no registre el número. Guardado en Andreani, el envío muestra «Esperando a Andreani» y Peek sigue preguntando. Si pasan varios días sin nada, confirma el número con la tienda. Ver [por qué el seguimiento no se actualiza](guide:tracking-not-updating).

**Sin novedades durante días.** Cuando la línea más reciente tiene cuatro días o más y el envío sigue en «Etiqueta creada», «Admitido por el transportista» o «En tránsito», Peek lo marca como «Sin novedades desde hace cuatro días». Es habitual que no haya movimientos el fin de semana. Si sigue igual, consulta a la tienda.

**Entregado, pero no llegó nada.** La última línea dice `Ya entregamos tu envío. ¡Esperamos volver a verte pronto!`, pero no tienes el paquete. Pregunta a quienes viven en el domicilio, a vecinos o al personal del edificio, y luego a la tienda. Ver [entregado pero no recibido](guide:delivered-not-received).

**Perdido o dañado.** Contacta primero a la tienda: es quien contrató el envío con Andreani. Ver [paquetes perdidos](guide:lost-parcel) y [paquetes dañados](guide:damaged-parcel).

**Devuelto.** La tabla de líneas de Andreani en Peek todavía no tiene una línea específica de devolución. Si el paquete regresa al remitente, el aviso se muestra igualmente con las palabras de Andreani. Pide a la tienda un reembolso o un nuevo envío. Ver [devuelto al remitente](guide:returned-to-sender).

**La tienda no responde.** Si una empresa a la que compraste no cumple lo acordado, puedes reclamar ante Defensa del Consumidor en Argentina. Es gratis y online, mediante la Ventanilla Federal Única de Defensa del Consumidor. Necesitas tu DNI o pasaporte, un teléfono o correo electrónico para recibir respuesta, el nombre y la localidad de la empresa y los datos básicos de la compra. Defensa del Consumidor deriva tu reclamo al organismo correspondiente y te envía un número de reclamo por correo electrónico.

## Atención al cliente y contacto de Andreani

Dado que Peek no pudo leer el sitio web de Andreani, esta página no publica teléfono, correo ni horarios: Peek solo muestra contactos leídos en páginas oficiales. El sitio web de Andreani es andreani.com.

Para una compra online, contacta primero a la tienda: contrató el envío y tiene todos los datos.

## Seguir un paquete de Andreani con Peek

Un número de 15 dígitos no basta para que [Peek](/) sepa solo por su forma que es de Andreani. Pégalo igualmente: Peek sigue a más de 4.500 transportistas.

**Un número que empieza por `36000`, `31000` o `30000`.** Peek pregunta a Andreani y a BRT al mismo tiempo y muestra «Comprobando servicios de seguimiento…» mientras tanto. Si Andreani tiene el paquete, la línea bajo el número nombra a Andreani y dice «tiene este paquete», y Peek lo guarda allí. Si no, dice «aún no encontrado · Peek seguirá comprobando». Añádelo igual: el envío mostrará «Probablemente Andreani» y «Esperando el primer escaneo de Andreani», y Peek seguirá preguntando a Andreani cada 6 horas tras las primeras horas en la app de iPhone o Android o en tu cuenta. Para una consulta sin cuenta, Peek pregunta durante las primeras 6 horas, luego cuando abres el enlace (como máximo cada 6 horas, y como máximo una vez al día pasados dos días) y, si tienes alertas activas, una vez al día hasta 30 días después de la consulta. Tras una semana sin novedades, te sugiere verificar el número con el remitente o elegir otro transportista.

**Un enlace de andreani.com.** Pega el enlace de seguimiento con `/envio/`. Peek nombra a Andreani al instante («Transportista detectado») y sigue el número del enlace, incluidas referencias con letras.

**Cualquier otro número.** Elige Andreani tú mismo: pulsa «Cambiar» y busca Andreani en «Todos los transportistas» o mediante el buscador. Para números que empiezan por `36000`, `31000` o `30000`, también sale bajo «Encaja con este número». En un paquete ya añadido, usa «Cambiar transportista». Para Andreani, Peek admite de 6 a 30 letras y dígitos, con al menos un dígito; en cualquier otro caso, indica que Andreani «no emite números como este».

**Qué muestra Peek.** Peek consulta directamente el rastreador de Andreani, la misma consulta que hace su página web. Para cada movimiento muestra la frase de Andreani, su etapa (tabla de arriba), la sucursal y la hora. Las horas de Andreani usan el horario de Argentina, que tiene una sola zona horaria y sin cambio de hora; Peek las convierte a la zona horaria de tu dispositivo. El rastreador de Andreani también muestra el nombre del remitente, el nombre del conductor, observaciones, ubicación en mapa, barra de progreso y una estimación de entrega; Peek omite todo eso, por lo que no muestra fecha estimada de entrega.

**Cuando Andreani no responde.** Peek consulta en su lugar servicios de seguimiento —ParcelsApp, Ship24 y 17TRACK por orden— y muestra sus avisos con sus propias palabras. Peek no conoce traspasos de Andreani a otro transportista, así que sigue el envío con Andreani hasta el final.

**Comprobaciones y alertas.** Peek revisa hasta cada 10 minutos, o cada 2 minutos desde las 6:00 hora local el día en que Andreani marca el paquete en reparto. Un envío sin novedades durante dos días, o durante 12 horas en «Etiqueta creada» o «Admitido por el transportista», se comprueba cada hora. Puede avisarte en «Todas las novedades», «Solo importantes» o «Solo el día de entrega». Peek no puede contactar a Andreani ni a la tienda por ti.

## Preguntas sobre el seguimiento de Andreani

### ¿Cómo es un número de seguimiento de Andreani?

15 dígitos que empiezan por `36000`, `31000` o `30000`, como `360004829103756`. El rastreador de Andreani también acepta otras referencias. Pega el número o el enlace de andreani.com en Peek.

### ¿Qué significa «Pendiente de ingreso» en el seguimiento de Andreani?

Que el remitente anunció el envío, pero Andreani todavía no tiene el paquete. Peek muestra «Etiqueta creada». Si lleva días así, pregunta a la tienda si ya lo despachó.

### ¿Qué significa «Hoy te visitaremos para entregarte tu envío.»?

Que Andreani pasará hoy a entregar tu paquete. Peek muestra «En reparto» y comprueba con más frecuencia hasta que aparezca la entrega.

### ¿Por qué no se actualiza mi seguimiento de Andreani?

Puede que la tienda aún no haya entregado el paquete o que Andreani no haya registrado un paso nuevo, algo frecuente en fin de semana. Peek sigue preguntando a Andreani y avisa tras cuatro días sin novedades; entonces consulta a la tienda.

### ¿Cómo seguir un envío de Andreani con Peek?

Pega el número o el enlace de andreani.com en Peek. Verás cada texto de Andreani ordenado bajo su etapa clara, con la sucursal, la hora en tu zona horaria y alertas opcionales.

:::sources
- [Universal Parcel Scraper: Andreani](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/andreani) – nombres y países, formato de 15 dígitos, otras referencias, enlaces andreani.com, consulta al rastreador, líneas y encabezados en español y sus etapas, eliminación de teléfonos y documentos, hora de Argentina, lo que muestra el rastreador y lo que Peek omite, sin fecha de entrega
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formatos de 15 dígitos de otros transportistas, qué transportistas consulta Peek, servicios de seguimiento, sin traspaso para Andreani
- [Argentina.gob.ar: Iniciar un reclamo ante Defensa del Consumidor](https://www.argentina.gob.ar/servicio/iniciar-un-reclamo-ante-defensa-del-consumidor) – reclamos por incumplimiento de una empresa, Ventanilla Federal Única, gratis y online, requisitos, número de reclamo por correo electrónico
:::
