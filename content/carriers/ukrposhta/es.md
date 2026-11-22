---
title: Seguimiento Ukrposhta: números, estados y recogida
description: Sigue un paquete de Ukrposhta (Correos de Ucrania): códigos de 13 dígitos y UA, estados en inglés, recogida en oficina y envíos internacionales.
slug: seguimiento-ukrposhta
published: 2026-11-22
updated: 2026-11-22
---

Ukrposhta (Укрпошта) es el servicio postal nacional de Ucrania. Puedes seguir un envío en track.ukrposhta.ua, en la app de Ukrposhta o pegando el número en el recuadro de arriba. El código procede del remitente: 13 dígitos dentro de Ucrania, o dos letras, nueve dígitos y dos letras para envíos internacionales.

## Número de seguimiento de Ukrposhta

Los números de Ukrposhta tienen dos formatos, y el código nacional de 13 dígitos termina en un dígito de control (módulo 11).

| Formato (inventado) | Qué es |
| --- | --- |
| `0548291736543` | Un envío nacional dentro de Ucrania: 13 dígitos. |
| `RR482917367UA` | Un envío internacional salido de Ucrania: dos letras, nueve dígitos, `UA`. |
| `RR482917367US` | Un envío que llega a Ucrania conserva el número del correo emisor y su código de país. |

¿Sin número? Pídelo al remitente o revisa [dónde encontrar tu número de seguimiento](guide:find-tracking-number).

[No confundir](guide:tracking-number-formats): dentro de Ucrania los envíos de Nova Poshta tienen 14 dígitos y muchos números de Meest terminan en `G`.

## Qué significan los estados de seguimiento de Ukrposhta

Peek solicita a Ukrposhta sus líneas en inglés. Las habituales, por orden aproximado:

| Estado de Ukrposhta | Qué significa para ti |
| --- | --- |
| `Created online. Waiting acceptance` | El remitente [creó la etiqueta online](guide:tracking-statuses); Ukrposhta aún no tiene el envío. |
| `Registered` | Admitido por Ukrposhta. |
| `Departed from the Branch` | Salió de la oficina donde fue depositado. |
| `Arrived to the Logistics Center` | En clasificación en su recorrido por Ucrania. |
| `Arrival at inward office of exchange` | Un envío del extranjero llegó a Ucrania. |
| `Held at inward office of exchange` | Retenido a la llegada para aduanas. |
| `Item presented to customs` | Presentado ante aduanas para inspección. |
| `Released from customs for further delivery` | Liberado por aduanas. |
| `Departure from inward office of exchange` | Superó la aduana y viaja hacia tu región. |
| `Item out for physical delivery` | En reparto. |
| `Arrived to the Branch` | En tu oficina postal, listo para recoger. |
| `Final delivery` | Entregado en mano. |
| `Returned to Sender (Due to Recipient's Refusal)` | Rechazado por el destinatario y en devolución. |
| `Return: Delivered to Sender` | Devuelto al remitente: no entregado a ti. |

Los pasos de devolución empiezan por `Return:`. En cuanto una línea indica que vuelve al remitente, Peek muestra una entrega posterior como «Devuelto al remitente», no «Entregado».

## Recogida en oficina y entrega de Ukrposhta

Los estados indican si el envío va a tu domicilio (`Item out for physical delivery`) o te espera en oficina (`Arrived to the Branch`, que Peek muestra como «Listo para recoger»). La app de Ukrposhta muestra oficinas en el mapa con códigos postales, direcciones y horarios. Consulta en tu oficina cuánto tiempo guarda un envío y qué identificación exige.

## Paquete de Ukrposhta retrasado, perdido o dañado

Si no hay cambios durante días, consulta [seguimiento que no se actualiza](guide:tracking-not-updating); ante una retención aduanera, consulta [paquetes retenidos en aduanas](guide:customs).

**Perdido o retrasado en envíos internacionales.** Según las normas de la Unión Postal Universal (UPU), un correo nacional debe aceptar una reclamación sobre un paquete o envío certificado o asegurado, presentada por el remitente o por ti, dentro de los seis meses desde el día siguiente al depósito. Aunque estas consultas suelen ser gratuitas, Ucrania se reserva el derecho de cobrar por ellas. La indemnización por pérdida o daño corresponde al remitente, o a ti si renuncia por escrito. Ante compras online que no llegan, contacta primero con el vendedor.

**Dañado al llegar.** Señala el daño antes de aceptar el envío. Bajo las mismas normas de la UPU, los correos responden por daños detectados antes o en la entrega, y el correo que entrega redacta un acta para que la refrendes y te entrega una copia.

## Atención al cliente de Ukrposhta

Para problemas con la aplicación, la ficha de Ukrposhta en Google Play facilita mobile-app@ukrposhta.ua. Para un envío internacional perdido o retrasado, presenta una consulta como se explica arriba: según las normas UPU, Ukrposhta debe aceptarla tanto de ti como del remitente.

## Seguir Ukrposhta con Peek

[Peek](/) sigue más de 4500 transportistas. Para 13 dígitos con dígito de control válido de Ukrposhta, Peek consulta a Ukrposhta — y también a [Delhivery](carrier:delhivery) si el número coincide además con su formato (primer dígito 1–9, último 0–6) — y muestra Ukrposhta con «tiene este paquete» cuando solo ella lo conoce; si ambos lo conocen, eliges tú. Un número de 13 dígitos con dígito de control erróneo no se ofrece a Ukrposhta: selecciónala con «Cambiar». Un código S10 que empiece por `C`, `E`, `L` o `R` y acabe en `UA` con dígito de control válido se identifica directamente como Ukrposhta; con otra letra inicial válida, Peek pregunta a Ukrposhta y a [Chronopost](carrier:chronopost), y sigue comprobando tras añadirlo si aún ninguno responde. Un enlace de track.ukrposhta.ua con el número (`barcode=`) siempre asigna Ukrposhta.

Un código postal de otro país no se asigna de entrada a Ukrposhta. Cuando el envío va dirigido a Ucrania, Peek puede seguirlo hasta Ukrposhta, o puedes elegir «Ukrposhta» con «Cambiar».

Peek consulta el servicio detrás de la app de Ukrposhta; si falla o no conoce un envío internacional, abre la página de seguimiento de Ukrposhta en un navegador desde su servidor. Muestra cada paso en un mapa con lugar y país, los pasos de aduanas y la hora de entrega. Ukrposhta no incluye zona horaria en sus horas: los pasos en Ucrania se leen en hora de Kyiv y se convierten a tu zona horaria (al final del historial aparece «Horas en tu zona horaria:» con su nombre), mientras que los pasos de otros países o sin país (como tramos aéreos) conservan su hora local. Peek no muestra fecha prevista de llegada y omite fecha planificada y códigos postales; el único punto de recogida nombrado es la oficina en `Arrived to the Branch`. Los estados conservan la redacción en inglés de Ukrposhta, incluido `Final delivery`.

Mientras tengas el envío abierto en pantalla — o el día de entrega con notificaciones activadas — Peek comprueba hasta cada 10 minutos, y una vez en reparto, desde las 6:00 hora de Kyiv, cada 2 minutos (o cada 5 si la última consulta requirió navegador), mientras el servicio o la web de Ukrposhta respondan. Fuera de esos momentos, y tras dos días sin novedades (12 horas si sigue en «Etiqueta creada» o «Admitido por el transportista»), comprueba cada hora. Solo a través de servicios de seguimiento, comprueba cada hora, cada 3 horas tras un día sin escaneos y hasta cada 15 minutos el día de entrega si tienes el paquete abierto en pantalla o las notificaciones activadas. Elige «Cada escaneo», «Pasos importantes» o «Solo la entrega». Tras cuatro días sin novedades, Peek muestra en tu lista el día del último escaneo («Sin novedades desde el …») y en la ficha «Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a Ukrposhta.», con el enlace «Pide ayuda a Ukrposhta» a su centro de contacto solo en ese aviso.

## Preguntas sobre Ukrposhta

### ¿Cómo sigo un paquete de Ukrposhta en el Reino Unido?

Al añadir un envío de Ukrposhta desde el Reino Unido, Peek consulta también a [Royal Mail](carrier:royal-mail) con el mismo número, o a Parcelforce si empieza por `E` (EMS). Cuando ese correo muestre un escaneo al menos tan reciente como el último de Ukrposhta, Peek continúa allí conservando el historial previo.

### ¿Por qué no funciona mi número de seguimiento de Ukrposhta?

Compáralo carácter por carácter con el del remitente. Los números de 13 dígitos llevan un dígito de control y Peek no propone Ukrposhta si falla. Si es correcto, quizá Ukrposhta aún no lo registre: bajo Ukrposhta mostrará «Esperando a Ukrposhta» mientras Peek sigue probando. Si dos días después sigue sin reconocerlo y ningún otro transportista respondió, Peek muestra «Tras dos días, el transportista sigue sin reconocer este número. Revísalo, elige otro transportista o elimina el paquete.» y pasa a comprobar a diario, y semanalmente tras una semana. Si nada cambia en varios días, pregunta al remitente.

### ¿Cómo sigo un paquete enviado a Ucrania desde el extranjero?

Pega el número facilitado por el remitente. Peek lo sigue en el correo de origen; cuando ese correo indique Ucrania como destino (o lo añadas desde Ucrania), Peek pregunta también a Ukrposhta y continúa allí en cuanto tenga un escaneo igual o más reciente, manteniendo los pasos anteriores. Para seguirlo en Ukrposhta desde el principio, selecciona «Ukrposhta» con «Cambiar». Un paquete DHL desde Alemania cuyo código empiece por `C` se sigue en Meest en su lugar.

:::sources
- [Universal Parcel Scraper: Ukrposhta](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/ukrposhta) – formatos y dígitos de control, enlaces track.ukrposhta.ua, servicio de la app y respaldo por navegador, textos en inglés, devoluciones, hora de Kyiv y pasos fuera de Ucrania, campos omitidos
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – transportistas consultados por número, traspasos a Ukrposhta (y a Meest para `C` de DHL) y a Royal Mail o Parcelforce, formatos Nova Poshta y Meest
- [Unión Postal Universal: Convention Manual](https://www.upu.int/UPU/media/upu/files/aboutUpu/acts/06-manualsInThreeVolumes/actInThreeVolumesConventionManual202609En.pdf) – reclamaciones en seis meses, reserva de Ucrania para cobrarlas, indemnización al remitente (o renuncia escrita) y acta de daños en la entrega
- [Google Play: Ukrposhta](https://play.google.com/store/apps/details?id=ua.ukrposhta.android.app) – seguimiento en app, mapa de oficinas con códigos postales, direcciones y horarios, correo de soporte de la app
:::
