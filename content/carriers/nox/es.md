---
title: Seguimiento nox NachtExpress: estados, depósito y contacto
description: Sigue un envío de nox NachtExpress: formato del código de barras, significado de los estados en alemán, entrega nocturna en punto acordado y contacto.
slug: seguimiento-nox
published: 2026-11-23
updated: 2026-11-23
---

nox (nox NachtExpress) es una empresa alemana de mensajería nocturna, nox Germany GmbH, con sede en Langenfeld. Transporta repuestos y otras mercancías urgentes para empresas. Recoge por la tarde y entrega durante la noche, por lo general antes de las 8:00 (hora alemana), en un lugar acordado con el destinatario: una caja fuerte, un garaje, un taller o el maletero de una furgoneta de servicio técnico. Sigue un envío con su código de barras en el [rastreador de nox](https://trackandtrace.app.nox.express/scannungen) o pega el número en el cuadro de arriba. El rastreador está en alemán: las tablas siguientes lo explican.

## Código de barras de nox: cómo es y dónde encontrarlo

nox llama «barcode» a su número de seguimiento. Cada bulto debe llevar una etiqueta de nox que crea el remitente al contratar el envío, por lo que el remitente también tiene el código de barras. ¿No tienes número? Pregunta al remitente o consulta [cómo encontrar tu número de seguimiento](guide:find-tracking-number). Los formatos que conoce el código de Peek:

| Código de barras (inventado) | Qué es |
| --- | --- |
| `220004829173640715482917` | 24 dígitos que empiezan por 22000: código de cliente, 000, número de cliente del remitente, fecha de impresión de la etiqueta y número de serie. El catálogo de Peek conoce esta forma. [Ciblex](carrier:ciblex), [InPost](carrier:inpost), [bpost](carrier:bpost) y [Austrian Post](carrier:austrian-post) también usan 24 dígitos. |
| `SWR482917364058291736405` | SWR y 21 dígitos: un código de barras antiguo de nox. |
| `RA1482917364058482917364` | RA, luego 1 o 2 (tipo de recogida), 12 letras o dígitos para los clientes y una serie de 9 dígitos: código de barras de devolución. |
| `C48291736405829173640582917364` | C y 29 dígitos: código de barras propio del remitente, al que el rastreador de nox también responde. |
| `trackandtrace.app.nox.express/scannungen/SWR482917364058291736405` | Enlace a un envío en el rastreador de nox. Pega el enlace entero en Peek. |

Otros códigos de barras de nox tienen 24 dígitos con otro código de cliente de dos dígitos delante de 000. En Peek no importan los espacios y valen las minúsculas.

## Estados de seguimiento de nox explicados

El rastreador de nox muestra cada escaneo con `Status`, `Scanort` (lugar), `Bemerkung` (observación), `Datum` (fecha) y `Uhrzeit` (hora). En la terminología de nox, Collo significa un bulto de tu envío y Niederlassung una delegación de nox.

| nox indica | Qué significa para ti | Estado en Peek |
| --- | --- | --- |
| `Collo durch Versender bereitgestellt zur Abholung` | El remitente tiene el bulto preparado para que nox lo recoja. nox aún no lo tiene. | «Etiqueta creada» |
| `Collo in der nox Niederlassung eingetroffen` | El bulto ha llegado a una delegación de nox. | «En tránsito» |
| `Collo von der nox Niederlassung weiterverladen` | La delegación lo ha cargado hacia otra delegación. | «En tránsito» |
| `Collo steht am Lager in nox Niederlassung, Zustellung nicht im geplanten Zeitfenster` | Permanece en la delegación y no se entregó en la franja prevista. Saldrá en una noche posterior. | «En tránsito» |
| `Collo in nox Zustellfahrzeug geladen` | Está cargado en el vehículo de reparto. | «En reparto» |
| `Collo von nox zugestellt` | Entregado: normalmente dejado en tu lugar acordado, sin firma. | «Entregado» |

Si el rastreador dice `Der von Ihnen angegebene Barcode ist uns nicht bekannt.`, nox no reconoce ese código de barras: compruébalo con el remitente. Para más detalles o presentar una reclamación, el rastreador pide validar el código postal del destinatario mediante `PLZ validieren/Reklamation erfassen`.

## Cómo entrega nox: por la noche, en tu punto de depósito

**Cuándo.** nox suele recoger en origen entre las 14:00 y las 18:00 (hora alemana). Según sus condiciones, NachtExpress entrega al día siguiente, de martes a sábado incluidos festivos, por regla general antes de las 8:00 (hora alemana). El remitente puede contratar entrega antes de las 7:00 o antes de las 6:00 (hora alemana). nox vende también entregas en fines de semana y festivos como servicio aparte (WochenendService). En bultos de más de 100 kg, o de más de 3,20 m de largo, 1,20 m de ancho o 1,50 m de alto, el horario puede variar. Los envíos a islas del mar del Norte y del Báltico no tienen garantía de horario.

**Dónde.** Como nox entrega mientras muchos clientes todavía duermen, deja el envío en un punto acordado de antemano, normalmente sin firma. Ejemplos que da nox:

- una caja independiente en las instalaciones de tu empresa;
- el taller, la nave de lavado, la sala de descanso o el garaje;
- el maletero del vehículo de un técnico de servicio;
- el conserje, que lo recibe en mano del conductor;
- una de las estaciones de servicio de nox con acceso 24 horas, como una gasolinera abierta todo el día.

**Configurar tu punto de depósito.** Regístralo o actualízalo en cualquier momento, con fotos y descripción, en la herramienta online [ready2nox](https://ready2nox.de/). Según las condiciones de nox, es el único modo de fijarlo y el lugar sigue vigente hasta que lo cambies allí. En cuanto el bulto queda en ese lugar, se considera entregado.

**Sin punto de depósito.** Según sus condiciones, nox puede dejar el envío en tu dirección. Si el riesgo de pérdida es claramente demasiado alto, puede no entregarlo y devolverlo al remitente.

**Recogida en delegación.** Como servicio adicional, puedes recoger los envíos en la delegación de nox, donde los guardan para ti.

**Prueba.** Cuando no hay nadie, nox fotografía el bulto y el sitio donde quedó, y guarda las fotos como prueba. nox puede compartir una foto caso por caso con quien demuestre interés legítimo, aunque no existe derecho legal a obtenerla.

**Fuera de Alemania.** nox opera en otros 20 países europeos mediante empresas filiales y socios. Enviar al extranjero exige acuerdo previo del remitente con nox, y las normas de reclamación pueden variar según el país.

## Envío de nox retrasado, dañado o desaparecido

Para un reemplazo, tu contacto es la empresa que envió la mercancía.

**Sin movimiento.** Si `Collo durch Versender bereitgestellt zur Abholung` sigue siendo el único escaneo, nox aún no ha recogido el bulto: pregunta al remitente. Si espera en delegación, saldrá una noche posterior (ver tabla arriba). Por retraso, usa el rastreador de nox: valida tu código postal y elige `Laufzeitverzug` (retraso) o `Sonstige Anliegen` (otros asuntos). Consulta también [por qué el seguimiento deja de actualizarse](guide:tracking-not-updating).

**Entregado pero no está.** Revisa primero tu punto de depósito acordado y después otros lugares que use tu empresa. Pregunta al conserje o al técnico cuyo vehículo pudo recibirlo. Avisa rápido a nox antes del plazo de abajo. Más en [entregado pero no recibido](guide:delivered-not-received).

**Dañado.** En NachtExpress, nox debe recibir toda reclamación y parte de daños antes de las 12:00 (hora alemana) del día de entrega, por escrito y con descripción. Si ese día es sábado, domingo o festivo, el límite pasa a las 12:00 (hora alemana) del siguiente día laborable. Una reserva genérica no basta, y pasado el plazo las condiciones de nox excluyen su responsabilidad. Conserva la mercancía en su embalaje original para que nox pueda inspeccionarla y retirarla; de lo contrario no responde. Comunícalo en el rastreador tras validar el código postal (`Reklamation einer Beschädigung`), mediante el formulario de contacto o en el portal de clientes. Consulta [paquetes dañados](guide:damaged-parcel).

**Aduanas.** Las páginas de nox solo mencionan las aduanas como un servicio para clientes en importaciones y exportaciones. Pregunta al remitente y consulta [paquetes retenidos en aduanas](guide:customs).

## Contacto y atención al cliente de nox

Peek no puede contactar con nox ni con el remitente por ti. Datos publicados en las páginas de nox:

- **Atención al cliente:** +49 (0) 4224 920 000, de lunes a viernes de 7:00 a 17:00 (hora alemana), o customerservice@nox-nachtexpress.de, tal como aparecen en el rastreador de nox.
- **Formulario de contacto:** [formulario de contacto de nox](https://www.nox.eu/de/de/kontakt/kontaktformular/), para consultas y reclamaciones.
- **Reclamaciones:** en el rastreador tras validar el código postal. Sus formularios (retraso, daños, otros) preguntan si eres destinatario (Empfänger), remitente (Versender) o un tercero que contrató el transporte.
- **Empresa:** nox Germany GmbH, Katzbergstr. 3, 40764 Langenfeld, Alemania; welcome@nox.eu.

Para seguir todos sus envíos, una empresa necesita cuenta en el portal de clientes de nox: solicítala con el formulario de contacto.

## Seguir un envío de nox con Peek

Peek no puede identificar a nox solo por el número: sus códigos de barras `22000` comparten longitud con Ciblex, InPost, bpost y Austrian Post, y Peek no asigna los demás códigos a nox solo por su forma. Pégalo igualmente: Peek sigue a más de 4.500 transportistas. Al añadir un código `22000`, Peek pregunta a los transportistas que usan ese formato, primero los de tu país («Comprobando servicios de seguimiento…»), y muestra «nox tiene este paquete.» cuando el rastreador de nox lo reconoce. Si varios lo reconocen, tú eliges.

Si ninguno responde todavía, el envío muestra «Todavía no hay historial de seguimiento» mientras Peek sigue comprobando, o «Esperando a nox» una vez que eliges nox. Para seleccionarlo tú mismo, pulsa «Cambiar» y elige nox: aparece en «Encaja con este número» para un código `22000`; para un código SWR, RA o C, búscalo (NOX Nachtexpress, Innight Express y TNT Innight también lo encuentran). En un envío ya añadido usa «Cambiar transportista». Si eliges nox para un número de otro formato, Peek avisa de que «no emite números como este» y no consulta a nox: una vez añadido, salvo que un servicio de seguimiento tenga el envío, muestra «Revisa el número de seguimiento». Un enlace del rastreador de nox, de `trackandtrace.app.nox.express` o `tcs.app.nox.express`, asigna el envío a nox al instante.

Peek lee el estado, el texto alemán y la hora de cada escaneo (en hora alemana) y la hora de entrega. Omite el lugar y la observación del escaneo y no pide código postal, por lo que no muestra los datos que aparecen tras validar el código postal. Peek no muestra fecha prevista de entrega, peso, punto de depósito, foto de entrega ni firmante.

Día y noche, mientras tengas el envío abierto en Peek o el día de entrega si las notificaciones están activadas, Peek comprueba hasta cada 10 minutos, y a partir de las 6:00 (hora alemana) del día en que nox escanea el envío en reparto hasta cada 2 minutos mientras el rastreador propio de nox responda. Después de dos días sin novedades (12 horas mientras siga en «Etiqueta creada» o «Admitido por el transportista»), Peek comprueba cada hora; y si permanece siete días en «Etiqueta creada», cada 6 horas.

Si un envío todavía en «Etiqueta creada», «Admitido por el transportista» o «En tránsito» pasa cuatro días sin un nuevo escaneo, Peek lo marca con «Sin novedades desde hace cuatro días» (o «Sin novedades desde el …»), explica «Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a nox.» y ofrece «Pide ayuda a nox». Puedes recibir avisos de «Cada escaneo», «Pasos importantes» o «Solo la entrega». Peek no puede configurar tu punto de depósito ni abrir una reclamación por ti.

## Preguntas sobre el seguimiento de nox

### ¿Qué es nox NachtExpress?

El servicio exprés nocturno de nox Germany GmbH, desde Langenfeld. Las mercancías recogidas a primera hora de la tarde llegan al destinatario antes del inicio de la jornada del siguiente día laborable. Sus sectores principales son automoción, vehículos industriales, maquinaria de obra y agricultura, además de soluciones específicas para sanidad.

### ¿Cuándo entrega nox?

Durante la noche, por regla general antes de las 8:00 (hora alemana), de martes a sábado, festivos incluidos. El remitente puede contratar entrega antes de las 7:00 o de las 6:00 (hora alemana).

### ¿Dónde deja nox mi envío?

En el punto de depósito acordado para tu dirección, configurado en la herramienta ready2nox de nox. Sin uno configurado, nox puede dejarlo en tu dirección.

### ¿Exige nox firma en la entrega?

Por lo general no: nox entrega sin acuse de recibo, a menudo cuando no hay nadie, y luego fotografía el bulto en el lugar donde quedó. Con conserje, el conductor lo entrega en mano.

### ¿Cómo informo de una entrega de nox dañada?

En NachtExpress, antes de las 12:00 (hora alemana) del día de entrega, por escrito, desde el rastreador o el formulario de contacto de nox. Conserva la mercancía y el embalaje original. Si la entrega cae en fin de semana o festivo, tienes hasta las 12:00 (hora alemana) del siguiente día laborable.

:::sources
- [nox: Sendungsverfolgung/Reklamation erfassen](https://www.nox.eu/de/de/kundenbereich/sendungsverfolgung/) – formulario de código de barras que abre el rastreador de nox, portal de clientes para todos los envíos
- [nox: Track & Trace](https://trackandtrace.app.nox.express/scannungen) – columnas del rastreador, código desconocido, validación de código postal, formularios de reclamación, teléfono, horario y correo de atención al cliente, ready2nox
- [nox: Hinweise zum Nachtversand](https://www.nox.eu/de/de/kundenbereich/hinweise-zum-nachtversand/) – recogida de 14:00 a 18:00, entrega antes de las 8:00, puntos de depósito, bultos grandes, otros 20 países, reclamaciones por formulario de contacto, plazo por daños
- [nox: NachtExpress](https://www.nox.eu/de/de/leistungen/nachtexpress/) – antes de las 8:00, 7:00 o 6:00, entrega sin recibo, ejemplos de puntos de depósito, recogida en delegación, servicio aduanero
- [nox: Abstellplatzmanagement](https://www.nox.eu/de/de/leistungen/abstellplatzmanagement/) – ejemplos de puntos de depósito, ready2nox con fotos y descripciones
- [nox: Über uns](https://www.nox.eu/de/de/ueber-uns/) – entrega antes de empezar la jornada, clientes durmiendo, sectores principales
- [nox: Allgemeine Geschäftsbedingungen, from 1 September 2026 (PDF)](https://cms.nox.eu/de/wp-content/uploads/sites/4/2026/09/Allgemeine-Geschaftsbedingungen-fur-Geschaftskunden-nox-DE-Sept-2026-Final.pdf) – etiquetas nox, días de entrega, punto de depósito en ready2nox, entrega sin punto acordado, fotos, islas, envío internacional, plazo de reclamación, normas por país
- [nox: Allgemeine Geschäftsbedingungen](https://www.nox.eu/de/de/agb/) – condiciones generales vigentes
- [nox: Kontakt](https://www.nox.eu/de/de/kontakt/kontaktformular/) – formulario de contacto
- [nox: Impressum](https://www.nox.eu/de/de/impressum) – nox Germany GmbH, Langenfeld, teléfono, correo
- [Universal Parcel Scraper: nox](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/nox) – formatos de códigos de barras, enlaces al rastreador, textos y fases de escaneo, hora alemana, qué lee Peek
:::
