---
title: Seguimiento Express One: estados, puntos y contacto
description: Sigue un paquete de Express One en Hungría: de dónde sale el número, significado de estados, puntos de recogida, taquillas y contacto.
slug: seguimiento-express-one
published: 2026-11-22
updated: 2026-11-22
---

Express One Hungary es una mensajería húngara propiedad de [Austrian Post](carrier:austrian-post) que reparte para tiendas online con unos 700 repartidores y más de 2300 puntos de recogida y taquillas. Sigue un paquete con su ID de paquete en [tracking.expressone.hu](https://tracking.expressone.hu/) o pégalo en el recuadro de arriba. El número llega en el correo que envía Express One cuando el paquete queda registrado y entra en su red.

## Número de seguimiento de Express One

Express One te envía un preaviso por correo electrónico una vez que el remitente registra el paquete y este llega físicamente a Express One; el SMS solo se manda si el remitente lo contrata. Dentro de Hungría, los avisos indican la franja horaria, el contacto del repartidor, el importe contrarreembolso si lo hay y un enlace para cambiar la entrega. Los paquetes hacia el extranjero no reciben avisos. ¿Sin mensaje? Pregunta a la tienda y consulta [cómo encontrar tu número de seguimiento](guide:find-tracking-number).

El localizador también admite la referencia del remitente o el número de documento, pero requieren el Company ID de 5 dígitos del remitente, así que usa el ID de paquete (el código de barras).

Express One no publica un formato oficial. Los ID de paquete vistos hasta ahora tienen dos formas (dígitos inventados):

| Número | A quién pertenece |
| --- | --- |
| `4829173648291` | 13 dígitos: un ID de paquete de Express One, aunque DPD France y otros transportistas también usan esa longitud. |
| `148291736482917364821011` | 24 dígitos acabados en un código postal húngaro: Express One. Austrian Post, [InPost](carrier:inpost), [bpost](carrier:bpost) y [Ciblex](carrier:ciblex) también usan 24 dígitos. |
| `48291736482917` | 14 dígitos: DPD, nunca Express One. |
| `48291736482` | 11 o 12 dígitos: GLS, nunca Express One. |
| `RR482917367HU` | Dos letras, 9 dígitos y `HU`: Magyar Posta, nunca Express One. |

Guíate por el transportista nombrado en el correo de la tienda; más en [formatos de números de seguimiento](guide:tracking-number-formats).

## Qué significan los estados de Express One

tracking.expressone.hu muestra inglés o húngaro según el idioma del navegador, con selector EN/HU. Cada escaneo incluye código, texto, fecha y hora, y los escaneos en hub o depósito añaden el lugar entre paréntesis, como «(HU - Budapest)». Los textos en inglés, en su orden habitual:

| Estado de Express One | Qué significa para ti |
| --- | --- |
| `Shipping details received, shipment not yet handed over to EXOHU` | El remitente ha [registrado el paquete](guide:tracking-statuses); Express One aún no lo tiene. |
| `Shipment (pan) data uploaded` | Los datos del paquete están en el sistema de Express One, normalmente justo tras un escaneo en hub. |
| `Arrived at central hub` | En el hub central de Budapest. |
| `Departed central hub` | Ha salido del hub. |
| `Arrived at depot` | En el depósito encargado de repartir en tu dirección. |
| `Out for delivery` | En reparto hoy en la furgoneta del mensajero. |
| `Delivery schedule updated` | Se recalculó el plan de reparto; el paquete no se movió. |
| `Processing in progress` | La entrega se pospuso a otro horario, sin indicar motivo. |
| `Addressee not present at delivery address` | El repartidor acudió y no había nadie. |
| `Addressee unavailable` | El segundo intento también falló. |
| `Obstacles of nature` | El mal tiempo detuvo la ruta; cuenta como intento y volverá a salir a reparto. |
| `Successful delivery` | Entregado. |

EXOHU corresponde a Express One Hungary: el primer estado significa que solo existe la etiqueta. La barra sobre el historial usa sus propias etiquetas, de `Shipment data uploaded` a `Successfully delivered`. En húngaro, entregado aparece como `Sikeres kézbesítés`.

## Entregas, puntos de recogida e intentos fallidos

**Cuándo llega.** El preaviso indica una franja de 4 horas. La mañana del reparto llega un aviso con una ventana de 2 horas que Express One califica de orientativa: el mensajero puede pasar antes o después. Ese día el rastreador muestra cuántas paradas le faltan antes de llegar a ti. No hay reparto en fin de semana, salvo entrega en sábado de pago para empresas remitentes.

**Cambios.** El botón «Modification» del SMS o correo matinal permite cambiar hora o dirección, o desviar el paquete a una tienda o taquilla incluso una hora antes de que llegue el repartidor. Solo puede cambiarse el primer día de reparto y una única vez. Algunas opciones dependen del remitente (un paquete grande no cabe en taquilla). Las condiciones generales indican que un cambio cuenta como intento y puede tener coste; una entrada de blog de 2024 dice que redirigir es gratis.

**En la puerta.** Los destinatarios particulares firman y facilitan un número de documento de identidad. El contrarreembolso se paga en efectivo (hasta 300 000 Ft), con tarjeta (hasta 1 000 000 Ft) o por adelantado mediante un enlace en el preaviso; en un Exopoint el límite en efectivo es de 250 000 Ft. No se permite abrir el paquete antes de firmar y pagar.

**Entrega fallida.** Según sus condiciones, Express One avisa por correo o SMS como tarde al día laborable siguiente, hace un segundo intento gratuito y guarda el paquete como máximo hasta el 5.º día laborable. Mientras tanto puedes pedir nueva fecha o un punto de recogida. Tras un segundo fallo el paquete regresa al remitente; un artículo de blog de 2024 indica que antes espera 5 días laborables en el depósito central de Budapest.

**Puntos de recogida y taquillas.** Muchas tiendas permiten elegir un punto o taquilla Exopoint al comprar. La página de puntos de Express One indica una custodia de hasta una semana, 24/7. Sus artículos de blog varían: 3 días en EXOBOX, 2 en Alzabox, hasta 7 en un punto Packeta (2025), 72 horas en taquilla (2026), 5 días en punto de recogida (2024), y solo las taquillas EXOBOX y Alzabox abren las 24 horas. Recoger en el centro logístico de Budapest (H-1239 Budapest, Európa street 12, building L1) requiere cita con atención al cliente.

## Paquete de Express One retrasado, dañado o perdido

**Sin novedades o no encontrado.** Express One no fija un tiempo de espera. Su localizador señala que el número puede ser erróneo, tener más de 180 días o no haber sido registrado aún por el remitente, y aconseja probar más tarde. Sus condiciones le permiten borrar los datos si no recibe el paquete en 5 días laborables y califican el plazo previsto de meramente informativo. Consulta [cuando el seguimiento no se actualiza](guide:tracking-not-updating).

**Dañado o artículo incorrecto.** Avisa a la tienda cuanto antes: Express One tramita la reclamación cuando el remitente la abre y responde del transporte, no del contenido. Haz constar los daños visibles al entregártelo el repartidor; los daños ocultos deben notificarse por escrito en 3 días laborables tras la entrega o caduca el derecho.

**Perdido.** Suele reclamar el remitente, aunque tú puedes hacerlo si el paquete te fue entregado o si el remitente te cede el derecho por escrito. La reclamación escrita por pérdida total vence a los 6 meses desde el 15.º día posterior al envío, adjuntando número de albarán, prueba de contenido y valor y el formulario de Express One. Express One contesta en 30 días naturales; si no estás conforme, puedes acudir a la NMHH, la autoridad húngara de medios y comunicaciones, en 30 días.

## Teléfono y atención al cliente de Express One

Datos de la página inglesa de contacto de Express One, en días laborables de 8:00 a 17:00 (la FAQ indica hasta las 18:00), hora de Hungría:

- **Atención al cliente:** +36 1 8 777 400, ugyfelszolgalat@expressone.hu, también si tu repartidor no contesta. En épocas de mucha carga, Express One recomienda escribir.
- **Atención internacional:** +36 1 8 777 410, intcs@expressone.hu; reclamaciones en intcsreklamacio@expressone.hu.
- **Reclamaciones por daños:** +36 1 8 777 470, karugy@expressone.hu.
- **Formulario de contacto:** en [la página de contacto de Express One](https://expressone.hu/en/kapcsolat), con asuntos como «I am expecting a parcel» y «Delivery complaint»; ambos piden el número del paquete.

La página Parcel Tracking de Express One incluye también el +36 1 8 777 444 y un chatbot.

## Seguir paquetes de Express One con Peek

Un número de 13 dígitos no se consulta automáticamente en Express One: [Peek](/) lo prueba primero con otros transportistas. Si ninguno lo reconoce, verás «aún no encontrado · Peek seguirá comprobando»: pulsa «Cambiar» y elige «Express One». En cambio, para un número de 24 dígitos, Peek pregunta primero a Express One cuando buscas desde Hungría, por delante de [Austrian Post](carrier:austrian-post), [InPost](carrier:inpost) y [Ciblex](carrier:ciblex); fuera de Hungría pregunta a esos otros tres transportistas y basta con elegir «Express One» en «Cambiar». También puedes pegar un enlace de tracking.expressone.hu con `?plc_number=`, que identifica a Express One directamente sea cual sea el formato.

Los escaneos de Express One llevan hora sin zona horaria: Peek los interpreta según el reloj del lugar del escaneo (hora de Hungría para escaneos en Hungría) y los muestra en tu zona horaria («Horas en tu zona horaria:»). Lee el estado y, en escaneos de hub, la ciudad y el país. Los textos se mantienen como los escribe Express One, salvo `Out for delivery`; `Successful delivery` cuenta como entregado y un trayecto de vuelta como devuelto. Un escaneo desconocido mantiene su texto sin etapa. No incluye entrega prevista, peso, punto de recogida, barra de progreso ni firma.

Peek consulta directamente a Express One hasta cada 10 minutos, o cada 2 minutos desde las 6:00 según el reloj del último escaneo (hora de Hungría en Hungría) el día en que Express One lo marca en reparto, mientras el paquete esté abierto en Peek o el día de entrega con las notificaciones activadas. Tras dos días sin novedades (12 horas si sigue en «Etiqueta creada» o «Admitido por el transportista»), Peek comprueba cada hora. Para las notificaciones puedes elegir «Todas las novedades», «Solo importantes» o «Solo el día de entrega». Tras cuatro días sin escaneos en tránsito, fuera de aduana o incidencia marcada, Peek muestra «Sin novedades desde el…», recuerda que «Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a Express One.» y ofrece «Pide ayuda a Express One».

## Preguntas frecuentes sobre Express One

### ¿Qué significa «shipment not yet handed over to EXOHU»?

El remitente creó el envío, pero Express One Hungary aún no ha recibido el paquete. Si se queda así varios días, pregunta a la tienda.

### ¿Cuánto tiempo espera un paquete de Express One en un punto de recogida?

Hasta una semana a cualquier hora, según su página de puntos de recogida; sus artículos de blog indican de 2 a 7 días según el punto y acceso 24 horas solo en taquillas EXOBOX y Alzabox.

### ¿Es la misma empresa Express One en Eslovaquia o Eslovenia?

No. También pertenecen a Austrian Post, pero son empresas independientes; esta página y «Express One» en Peek cubren la filial húngara.

:::sources
- [Express One: Parcel Tracking](https://expressone.hu/en/csomagkovetes) – paradas del repartidor, centro de llamadas, chatbot
- [Express One: Tracking](https://tracking.expressone.hu/) – tipos de ID, Company ID, mensaje de no encontrado, 180 días, estados, idiomas
- [Express One: Frequently asked questions](https://expressone.hu/en/gyik) – modificación, franja de 2 horas, sábado, contrarreembolso, daños, artículo erróneo
- [Express One: GYIK](https://expressone.hu/gyik) – límites de contrarreembolso
- [Express One: Contact](https://expressone.hu/en/kapcsolat) – teléfonos, correos, horarios, formulario, centro logístico
- [Express One: Parcel point](https://expressone.hu/en/csomagpont) – 2300 puntos y taquillas, una semana, 24/7, elección en caja
- [Express One: Home](https://expressone.hu/en) – Austrian Post, 700 repartidores, redirección hasta una hora antes
- [Express One: General Terms and Conditions](https://expressone.hu/downloads/General_Terms_and_Conditions_2026_08_15.pdf) – intentos, custodia, documento, cargo por cambio, reclamaciones, NMHH, borrado de datos
- [Express One: Merre jár a csomagom?](https://expressone.hu/blog/merre-jar-a-csomagom-csomagkovetes-es-ertesitesi-rendszerek-alkalmazasa) – preaviso tras registro y llegada, franjas de 4 y 2 horas, opción SMS
- [Express One: Csomag átirányítás és a kézbesítés átütemezése](https://expressone.hu/blog/csomag-atiranyitas-es-a-kezbesites-atutemezese) – redirección gratuita, 5 días en depósito de Budapest o punto de recogida
- [Express One: Minden, amit az EXOPOINT megoldásokról tudni érdemes](https://expressone.hu/blog/minden-amit-az-exopoint-megoldasokrol-tudni-erdemes) – horarios, 3, 2 y 7 días
- [Express One: Csomagküldés csomagautomatába](https://expressone.hu/blog/csomagkuldes-csomagautomataba-miert-valasztjak-egyre-tobben) – 72 horas en taquilla
- [Express One: eBOX GYIK](https://expressone.hu/ebox/faq) – contenido de notificaciones, ninguna en exportación, 250 000 Ft en efectivo en Exopoint
- [Austrian Post: Austrian Post Group acquires Hungarian parcel delivery provider](https://news.post.at/presse/en/post/id/2255390/) – filial al 100 %
- [Austrian Post: International subsidiaries](https://post.at/en/j/c/careers-austrian-post-subsidiaries-international) – filiales Express One en otros países
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – datos leídos por Peek
:::
