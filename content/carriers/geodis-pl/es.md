---
title: Seguimiento GEODIS Polonia: número PL, estados y entrega
description: Sigue un envío de GEODIS Polonia (antes Pekaes) con número PL y 16 dígitos: qué significa cada estado, entrega, recogida en terminal, reclamaciones y contacto.
slug: geodis-polonia-seguimiento
published: 2026-11-18
updated: 2026-11-18
---

GEODIS Poland es la red polaca de transporte de palés y paquetes del grupo francés GEODIS, propiedad de los ferrocarriles públicos franceses SNCF. GEODIS adquirió PEKAES en 2021 y hoy la red está operada por GEODIS Road Network sp. z o.o., con sede en Błonie (Polonia). Qué hacer depende de lo que te haya facilitado el remitente:

- **Un número formado por `PL` y 16 dígitos, o un enlace a Strefa Klienta:** es un envío de GEODIS Poland (paquete o palé). Pégalo en el recuadro de arriba o búscalo directamente en el localizador de GEODIS Poland, [Strefa Klienta](https://strefaklienta.pekaes.geodis.pl/en/).
- **Otro número de GEODIS:** Peek también lee el seguimiento francés de [GEODIS](carrier:geodis), cuyos números empiezan por `1G` (más abajo). El buscador de `geodis.com` localiza envíos aéreos, marítimos y de última milla, y remite el transporte por carretera a Strefa Klienta.
- **Sin número todavía:** te lo proporciona la tienda online o el remitente, normalmente en el correo de confirmación del pedido o en el aviso de envío.

## Número de seguimiento de GEODIS Polonia: formato y dónde encontrarlo

Las soluciones de GEODIS están dirigidas exclusivamente a profesionales y empresas, así que el código de seguimiento lo tiene el comercio o fabricante que te envía la mercancía. Si no aparece en tus correos, pídelo al vendedor o revisa [cómo encontrar tu número de seguimiento](guide:find-tracking-number).

| Número (inventado) | Qué es |
| --- | --- |
| `PL4829105736491827` | `PL` seguido de 16 dígitos: un número de envío de GEODIS Poland, denominado `Shipment No.` en Strefa Klienta. |
| `1G4829105736` | `1G` seguido de 10 letras mayúsculas o dígitos: un código de [GEODIS](carrier:geodis) en Francia, consultable en el espacio de destinatarios francés y no en Strefa Klienta. |

En la versión en inglés de Strefa Klienta, escribe el código en el campo `Enter Shipment No.` y pulsa `Search` (en la página polaca: `Wpisz numer przesyłki` y `Szukaj`). Si el portal responde `Shipment with such number does not exist`, no tiene registrado ese número: ponte en contacto con el remitente o la tienda online, tal como aconseja GEODIS cuando un código no funciona. Si aparece en polaco `Proszę wprowadzić właściwy numer przesyłki.`, pide un número de envío válido: revisa que no haya ningún error al copiarlo.

## Qué significan los estados de seguimiento de GEODIS Polonia

Strefa Klienta está disponible en polaco y en inglés. Cuando localiza el envío, la página inglesa muestra primero un resumen (`Shipment No.`, `Status`, `Delivery Country`, `Delivery Location` con código postal y localidad) y su enlace `Details` despliega los escaneos con fecha, hora y población de la terminal. Estos son los textos en inglés en el orden habitual del transporte:

| Estado en GEODIS | Qué significa para ti |
| --- | --- |
| `Received from the sender` | GEODIS ha recogido el envío en las instalaciones del remitente (`Collected (SMS)` confirma la recogida por SMS; `Failed collection (SMS)` indica recogida fallida). |
| `Inbound Scan From Driver to Terminal` | Un conductor ha ingresado el envío en una terminal: la de origen tras la recogida o, si antes figuraba `Outbound for Delivery`, la de destino al regresar de una ruta. Si justo antes no aparece `Consignee Absent` ni `Failed delivery (SMS)`, en esa ruta no llegó a intentarse la entrega y el envío espera la siguiente salida. |
| `Outbound Scan from (Collection) Terminal to Line-haul` | Salió de la terminal de origen en un camión de línea hacia la terminal que atiende tu zona. |
| `Inbound Scan from Line-haul to (Delivery) Terminal` | Llegó a la terminal encargada del reparto final. |
| `Awaiting Delivery` | Espera en esa terminal a que se le asigne ruta de reparto. |
| `Outbound for Delivery` | Está en el vehículo de reparto camino de tu dirección. |
| `Consignee Absent` | El conductor no te encontró en el domicilio. |
| `Failed delivery (SMS)` | Intento de entrega fallido, confirmado por SMS. |
| `Awaiting designation of new delivery date` | GEODIS espera a que se fije una nueva fecha de entrega. |
| `Awaiting Decision` | GEODIS aguarda instrucciones del cliente contratante o del remitente. |
| `Delivery Refusal` | El destinatario rechazó la entrega. |
| `Request for Return` | GEODIS tiene orden de devolución: el envío vuelve hacia el remitente, aunque aún no se le ha entregado. |
| `Delivered` | Entregado (`Delivered (SMS)` indica lo mismo, confirmado por SMS). |
| `Claim Received` | Se ha registrado una reclamación: es un apunte administrativo que no mueve la mercancía. |

Algunos avisos señalan una incidencia o parada: `Stopped at Hub` y `Stopped at Collection Terminal` (envío retenido en un centro de clasificación o en la terminal de origen), `Not Dispatched` (perdió el enlace o reparto previsto pero sigue en la red) y `Failure` (incidencia o avería). Una fila que empieza por `Pseudoscan` (`Pseudoscan – delete all previous 100, 400, 500 scans`) es un asiento interno que anula los escaneos previos de entrega, devolución y reclamación. Más detalles generales en [estados de seguimiento](guide:tracking-statuses).

## Entrega de GEODIS en Polonia: domicilio particular, aviso previo y recogida en terminal

Las Condiciones Generales de Prestación de Servicios (*Ogólny Regulamin Świadczenia Usług*) de GEODIS Road Network, vigentes desde el 4 de noviembre de 2024, vinculan a GEODIS con su ordenante (*Zleceniodawca*, la empresa que contrata el porte, habitualmente la tienda) en los envíos nacionales en **Polonia** y en el transporte internacional por carretera:

- **Ausencia en la entrega.** En los servicios nacionales en **Polonia** (`PAC DOM`, `LTL DOM`, `LTL DOM NST`, `PTL DOM`), si estás ausente el conductor deja aviso del intento; tras un segundo intento fallido por causas imputables al destinatario, el envío se devuelve al remitente a cargo del ordenante (igual que si se rechaza o la dirección es errónea). En los servicios internacionales por carretera (`PAC INT`, `LTL INT`, `PTL INT`), si el destinatario está ausente, el ordenante o el remitente dispone de un máximo de 2 días laborables desde el intento fallido para dar instrucciones antes de que GEODIS devuelva el envío a costa del ordenante. Consulta [entrega fallida](guide:missed-delivery) y [devuelto al remitente](guide:returned-to-sender).
- **Palés a domicilio particular en Polonia (`DDOP`).** En el grupaje nacional en **Polonia** (`LTL DOM` y `LTL DOM NST`), las entregas en domicilios particulares se realizan únicamente en días laborables, de lunes a viernes de `8:00` a `16:00`. El ordenante debe facilitar tu número de móvil y marcar el envío con `DDOP`: GEODIS envía avisos de estado y concierta la entrega previamente por teléfono, SMS o correo electrónico, llamando a más tardar el mismo día en que puede entregar. Si en 2 días laborables no logra contactar contigo para concertar la cita, avisa al ordenante y devuelve el envío al remitente.
- **Descarga y servicios adicionales nacionales en Polonia.** Con el servicio `DDOP` en **Polonia**, el conductor descarga el palé a pie de calle en el punto de entrega, o en el lugar pavimentado y seguro más cercano si la vía impide detener el camión con seguridad frente a la dirección. La subida al domicilio (*Usługa Wniesienia*: nacional en `LTL DOM` / `LTL DOM NST`, hasta 300 kg brutos, máximo 30 kg por bulto y 30 bultos, hasta 150 m por superficie pavimentada y hasta la segunda planta si no hay ascensor operativo, pudiendo ampliar el plazo hasta 3 días laborables), el traslado con transpaleta (*Usługa Wwiezienia*, hasta 1.200 kg brutos y 300 kg por palé en un radio de 150 m hasta la primera barrera arquitectónica), el día fijo (*Fix Day*) y la entrega antes de las `10:00` (`D10`) o `12:00` (`D12` en determinados códigos postales polacos) son opciones nacionales aparte que debe contratar el ordenante.
- **Recogida en terminal en Polonia.** Cuando el ordenante solicita la recogida en persona en una terminal de GEODIS por un destinatario particular, la mercancía permanece disponible 48 horas (contadas en días laborables) desde dicha orden, con horario de recogida de lunes a viernes de `11:00` a `16:00`; pasado ese plazo, se devuelve al remitente.
- **Contra reembolso en Polonia (*Usługa pobrania*).** Disponible solo en servicios nacionales en **Polonia** (`PAC DOM`, `LTL DOM`, `LTL DOM NST`), permite abonar antes de recibir el envío hasta `10.000 PLN` (o su equivalente en otra divisa) por expedición en efectivo o mediante tarjeta bancaria, BLIK u otro medio sin efectivo admitido por GEODIS.
- **Firma y revisión.** Confirmas la recepción firmando en el dispositivo móvil del conductor o en los documentos de transporte. El reglamento de GEODIS establece que el destinatario no puede revisar el contenido antes de confirmar la recepción: examina bien el embalaje exterior y anota cualquier reserva en los documentos de transporte en el momento de firmar.

## Envío de GEODIS Polonia retrasado, dañado o no entregado

**Sin actualizaciones.** Si pasan varios días sin nuevos escaneos, consulta [seguimiento que no se actualiza](guide:tracking-not-updating) y habla con la tienda.

**Retraso según la normativa polaca de consumo.** En **Polonia**, la autoridad de defensa del consumidor (UOKiK, citando los artículos 546 y 546¹ del Código Civil polaco) recuerda que en una compraventa de consumo sujeta a la ley polaca el vendedor debe entregar el bien sin demora y a más tardar en un plazo de 30 días desde la celebración del contrato, salvo pacto distinto. Superado ese plazo, el comprador puede conceder un tiempo adicional al vendedor y, si sigue sin entregarse, desistir del contrato.

**Mercancía dañada o incompleta.** Si al recibir el envío hay daños exteriores visibles o falta parte de la mercancía, redacta en el acto un acta de daños (*protokół szkodowy*) en presencia del conductor según el modelo oficial de GEODIS, firmada de forma legible por ambos. Las reservas por daños o faltas no visibles en la entrega deben presentarse ante GEODIS antes de que transcurran 7 días desde la recepción; en cualquier caso, quien reclama debe probar que el daño o la pérdida parcial se produjo antes de la entrega. Conserva la mercancía a disposición de GEODIS hasta que se cierre el expediente y toma fotografías en las que consten fecha y hora. El formulario de reclamación de GEODIS advierte que firmar el acta de daños sirve de base para estudiar el caso, pero no equivale a presentar una reclamación. Más información en [paquete dañado](guide:damaged-parcel).

**Quién reclama.** Según el reglamento de GEODIS Road Network, las reclamaciones se dirigen por escrito al departamento de reclamaciones (*Dział Reklamacji*) en la central de Błonie por parte del ordenante o de quien cuente con autorización escrita o cesión de derechos del ordenante, sin demora y en un máximo de 14 días desde la recepción (daño o falta) o 21 días desde la fecha prevista de entrega (retraso o pérdida total). En las compras de consumo bajo derecho polaco, la UOKiK (art. 548 § 3 del Código Civil polaco) señala que el vendedor responde del riesgo de pérdida o daño accidental hasta que el comprador recibe el bien cuando el método de envío estaba entre los ofrecidos por la tienda, incluso si el transportista causó el desperfecto: como comprador, reclamas ante el vendedor, y este reclama al transportista.

## Contacto y atención al cliente de GEODIS Polonia

- **Atención al cliente en Polonia (*Dział Obsługi Klienta*):** `+48 22 460 26 26`, indicado en la portada polaca de Strefa Klienta (sin horario publicado).
- **Reclamaciones por correo postal:** `Dział Reklamacji, ul. Spedycyjna 1, 05-870 Błonie`, Polonia. Los impresos de reclamación (`GRN Formularz zgloszenia reklamacji`) y de acta de daños (`GRN Protokol szkody`) se descargan en Strefa Klienta.
- **Delegaciones en Polonia:** la [página de contacto polaca de GEODIS](https://geodis.com/pl-pl/skontaktuj-sie-z-nami) recoge 42 centros en Polonia con sus direcciones, incluidas las bases de GEODIS Road Network.
- **Formulario web en `geodis.com`:** reservado exclusivamente a empresas y profesionales; exige nombre de empresa (`Firma *`) y correo corporativo (`Adres e-mail służbowy *`).

## Cómo seguir un envío de GEODIS Polonia con Peek

El patrón de `PL` más 16 dígitos no es exclusivo de GEODIS (`PL` identifica a Polonia), por lo que Peek no puede dar por seguro que sea GEODIS Poland solo por el formato. Pégalo igualmente: Peek sigue a más de 3.500 transportistas, consulta a GEODIS Poland y muestra «GEODIS Poland tiene este paquete.» en cuanto el portal lo reconoce. Si GEODIS aún no lo lista, el envío aparece como «Probablemente GEODIS Poland» y «Esperando el primer escaneo de GEODIS Poland» mientras Peek sigue comprobándolo; si tras una semana sigue sin figurar, Peek sugiere verificar el número con el remitente o elegir otro transportista. Para fijar el transportista manualmente, pulsa «Cambiar» en la línea del transportista y elige «GEODIS Poland» bajo «Encaja con este número», o búscalo (`Pekaes` y `GEODIS Road Network` también lo encuentran; «GEODIS» a secas corresponde a GEODIS Francia). En un envío ya añadido, usa «Cambiar transportista». Si pegas un enlace de Strefa Klienta con `ShNumber=`, Peek lo asigna directamente a GEODIS Poland.

Peek lee Strefa Klienta en inglés: el código `100` (`Delivered`) se muestra traducido («Entregado»), mientras que los demás textos de escaneo conservan la redacción inglesa de GEODIS (incluido `Delivered (SMS)`), junto con la localidad de la terminal, la hora de entrega y el país de destino. Los escaneos en terminales polacas se interpretan en hora de Varsovia (`Europe/Warsaw`); las terminales en el extranjero mantienen su reloj local y añaden el código del país tras la población. Peek convierte las horas conocidas a la zona horaria de tu dispositivo; en `peektracker.com` y en la app para iPhone, el historial termina con «Horas en tu zona horaria:» y el nombre de tu zona. Cuando una fila `Pseudoscan` anula escaneos anteriores, Peek también los retira, y `Claim Received` mantiene sin cambios la etapa del envío. El localizador de GEODIS no ofrece fecha estimada de entrega, peso, nombre del servicio, nombre del destinatario ni justificante de entrega; Peek omite además el código postal y municipio de entrega, el número de bultos, los códigos internos de terminal y las observaciones donde puede constar quién firmó.

Mientras tienes el envío abierto en pantalla, o el día de su entrega con las notificaciones activadas, Peek comprueba hasta cada 10 minutos —y hasta cada 2 minutos desde las 6:00 (hora local) el día en que GEODIS escanea el envío en reparto (`Outbound for Delivery`)— mientras responde el propio portal de GEODIS; el resto del tiempo, así como tras dos días sin novedades (12 horas mientras sigue en «Etiqueta creada» o «Admitido por el transportista»), revisa cada hora. A través de servicios de seguimiento externos, Peek comprueba cada hora, cada 3 horas tras un día sin movimiento y hasta cada 15 minutos el día de reparto mientras mantengas el envío abierto o tengas activadas las notificaciones. Puedes elegir avisos para «Cada escaneo», «Pasos importantes» o «Solo la entrega». Peek no permite concertar fecha de entrega, pedir recogida en terminal ni presentar reclamaciones: llama a GEODIS o contacta con la tienda.

## Preguntas frecuentes sobre el seguimiento de GEODIS Polonia

### ¿GEODIS Poland es lo mismo que Pekaes?

Sí. GEODIS compró PEKAES en 2021 y actualmente la red polaca opera bajo GEODIS Road Network. Su portal de seguimiento sigue alojado en `strefaklienta.pekaes.geodis.pl`.

### ¿GEODIS reparte los sábados en Polonia?

Las Condiciones Generales de GEODIS Road Network no contemplan entregas en sábado: los palés a domicilios particulares en Polonia (`DDOP`) se entregan en días laborables de lunes a viernes de `8:00` a `16:00`, y en la paquetería nacional (`PAC DOM`) los sábados, domingos y festivos no cuentan en el plazo de tránsito.

### ¿El conductor de GEODIS sube el pedido a casa en Polonia?

Únicamente en envíos nacionales de grupaje en Polonia (`LTL DOM` o `LTL DOM NST`) cuando el ordenante haya contratado el servicio adicional de subida (*Usługa Wniesienia*): hasta 300 kg brutos (máximo 30 kg por bulto), a no más de 150 m del punto de descarga y hasta una segunda planta si no hay ascensor utilizable. Sin ese servicio, la descarga se efectúa a nivel de calle.

### ¿Cuánto tarda GEODIS en entregar en Polonia y en Europa?

En su página de transporte de palés y paquetes, GEODIS anuncia un plazo estándar de 24 a 48 horas en Polonia (de 3 a 5 días en Europa) y entrega exprés al día siguiente antes del mediodía en Polonia (1 o 2 días en Europa). Sus Condiciones Generales fijan como regla general hasta 48 horas desde la recogida para paquetes nacionales `PAC DOM` de hasta 30 kg (sin contar fines de semana ni festivos) y 2 días laborables para envíos de grupaje `LTL`.

:::sources
- [GEODIS Poland: Strefa Klienta, shipment tracking (en inglés)](https://strefaklienta.pekaes.geodis.pl/en/) – campo `Enter Shipment No.`, botón `Search` y formularios de reclamación y acta de daños
- [GEODIS Poland: Strefa Klienta, Śledzenie przesyłek (en polaco)](https://strefaklienta.pekaes.geodis.pl/pl/) – teléfono de atención al cliente `+48 22 460 26 26` y Condiciones Generales
- [GEODIS Road Network: Ogólny Regulamin Świadczenia Usług, desde el 4 de noviembre de 2024 (en polaco)](https://strefaklienta.pekaes.geodis.pl/pl/?file=files/pekaes/Dla%20Klienta%20-%20nowe%20www/GEODIS_SK/GEODIS%20RN_Ogolny%20Regulamin%20Swiadczenia%20Uslug_od%2004.11.2024.pdf) – intentos en envíos nacionales e internacionales, servicio `DDOP`, descarga, subida a domicilio, recogida en terminal, contra reembolso, firma, plazos y reclamaciones
- [GEODIS Road Network: Formularz zgłoszenia reklamacji (en polaco)](https://strefaklienta.pekaes.geodis.pl/pl/?file=files/pekaes/Dla%20Klienta%20-%20nowe%20www/GEODIS_SK/GRN%20Formularz%20zgloszenia%20reklamacji.pdf) – dirección postal de reclamaciones en Błonie y alcance del acta de daños
- [GEODIS: Śledź swoją przesyłkę (en polaco)](https://geodis.com/pl-pl/sledz-swoja-przesylke) – dónde encontrar el número, envíos aéreos/marítimos/última milla en `geodis.com`, desvío del transporte por carretera y número que no funciona
- [GEODIS: Twój obszar roboczy (en polaco)](https://geodis.com/pl-pl/twoj-obszar-roboczy) – acceso a Strefa Klienta como plataforma de seguimiento
- [GEODIS: Transport palet i paczek (en polaco)](https://geodis.com/pl-pl/uslugi-transportowe/transport-palet-i-paczek) – servicios exclusivos para profesionales y plazos estándar y exprés en Polonia y Europa
- [GEODIS: Skontaktuj się z nami (en polaco)](https://geodis.com/pl-pl/skontaktuj-sie-z-nami) – formulario para empresas y listado de 42 delegaciones en Polonia
- [GEODIS: Historia (en polaco)](https://geodis.com/pl-pl/historia) – compra de PEKAES en 2021 y pertenencia a SNCF
- [UOKiK: Odpowiedzialność za przesyłkę (en polaco)](https://prawakonsumenta.uokik.gov.pl/prawa-ogolne/odpowiedzialnosc-sprzedawcy-za-przesylke/) – responsabilidad del vendedor hasta la entrega según el derecho polaco de consumo
- [UOKiK: Wydanie towaru (en polaco)](https://prawakonsumenta.uokik.gov.pl/prawa-ogolne/wydanie-towaru/) – plazo máximo de 30 días, prórroga y desistimiento del contrato en Polonia
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formato `PL` de 16 dígitos, enlaces a Strefa Klienta, textos de escaneo, códigos `1G` de GEODIS Francia y campos mostrados por Peek
:::
