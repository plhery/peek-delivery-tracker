---
title: Seguimiento ELTA Hellenic Post: estados, recogida y ayuda
description: Rastrea un paquete de ELTA Hellenic Post o ELTA Courier en Grecia: código GR, significado de los estados en inglés, taquillas PostBox, aduana y contacto.
slug: seguimiento-elta-hellenic-post
published: 2026-11-21
updated: 2026-11-21
---

ELTA Hellenic Post (`ΕΛΤΑ`) es el operador postal nacional de Grecia, en el que ya está integrado ELTA Courier. Consulta un envío en la [página de seguimiento en inglés de ELTA](https://www.elta.gr/en/track), que admite hasta cinco códigos a la vez (uno por línea), o pega el número en el cuadro superior. Un paquete de ELTA Courier también se puede buscar en el [buscador de elta-courier.gr](https://www.elta-courier.gr/search), que acepta varios números de albarán, uno por línea. El código te lo proporciona el remitente en el resguardo de admisión, el albarán courier o el aviso de la tienda: la tabla inferior aclara cada estado en inglés; un envío que no pudo entregarse aguarda en la oficina postal o agencia courier indicada (el plazo que marque el aviso o SMS en envíos nacionales, o 20 días desde el aviso si pasó por aduanas); y el centro de atención telefónica de ELTA responde en el `800-11-82000` desde fijo griego, `212-0000-800` desde móvil y `+30 212-0000-800` desde el extranjero.

## Número de seguimiento de ELTA: formato y ejemplos

Los códigos de seguimiento de ELTA tienen 13 caracteres: [dos letras latinas, nueve dígitos y dos letras latinas](guide:tracking-number-formats). Los envíos admitidos en Grecia terminan en `GR`. El último de los nueve dígitos es un dígito de control, por lo que equivocarse en una sola cifra suele invalidar el código. Los envíos de ELTA Courier emplean en su mayoría ese mismo formato, impreso arriba a la derecha del albarán. Las cartas certificadas simples cuyo código empieza por `U`, `L` o `TR` (`LL` dentro de Grecia) se depositan en la dirección sin firma.

| Formato (inventado) | Qué es |
| --- | --- |
| `RR482910576GR` | Envío postal expedido desde Grecia. Los espacios y las minúsculas no importan (`RR 482 910 576 GR`). |
| `482910573649` | Código de barras de 12 cifras que llevan algunos envíos procedentes de Alemania y que el rastreador de ELTA también admite. |
| `RR482910576CN` | Envío internacional que conserva el código del correo de origen (aquí [China Post](carrier:china-post)), terminado en el país remitente. |

¿No encuentras el código? Consulta [cómo encontrar el número de seguimiento](guide:find-tracking-number).

**Formatos parecidos de otros operadores.** Un código acabado en `CY` pertenece a Cyprus Post. Un albarán de 10 dígitos solos no es de ELTA: es el formato que usan ACS Courier y Geniki Taxydromiki.

## Qué significan los estados de seguimiento de ELTA

El rastreador de ELTA muestra cada escaneo en griego o en inglés (con selector de idioma), acompañado de fecha, hora y estación (oficina postal o agencia courier). Estas son las frases en inglés en el orden habitual:

| Estado de ELTA | Qué significa para ti | Peek muestra |
| --- | --- | --- |
| `Label printed by customer` | El remitente imprimió la etiqueta; ELTA aún no tiene el envío. | «Etiqueta creada» |
| `Item deposited at ELTA` | El remitente entregó el envío en la oficina de correos indicada. | «Admitido por el transportista» |
| `Shipment in transit` | Despachado desde la estación que figura en el escaneo. | «En tránsito» |
| `Arrival in` | Llegada a la oficina postal o agencia courier nombrada a continuación (separada por varios espacios, por lo que puede parecer cortada). | «En tránsito» |
| `Arrival at the airport` | En envíos que salen de Grecia: llegada al centro de ELTA en el aeropuerto de Atenas. | «En tránsito» |
| `For dispatch abroad` | En el centro del aeropuerto de Atenas, listo para salir de Grecia. | «En tránsito» |
| `With Courier for delivery` | En reparto hacia tu domicilio. | «En reparto» |
| `Notification of attempted delivery` | No hubo entrega: se dejó aviso de paso y, en el mismo minuto, otra línea detalla el motivo. | «Intento de entrega» |
| `ADDRESSEE NOT AVAILABLE AT TIME OF DELIVERY` | No había nadie en casa al pasar el repartidor. | «Intento de entrega» |
| `ADDRESSEE REQUEST OWN PICK-UP` | El intento concluyó porque recogerás el paquete en oficina. | «Intento de entrega» |
| `Item at post office - Can be collected` | Disponible para recoger en la oficina postal o agencia indicada. | «Listo para recoger» |
| `SMS text message sent for item to be collected` | ELTA te envió un SMS recordatorio para recogerlo; el envío no se ha movido. | Mantiene el estado anterior |
| `Shipment delivered` | Entregado (habitual en paquetes courier retirados en agencia). | «Entregado» |
| `Item delivered` | Entregado (habitual en paquetes retirados en oficina postal). | «Entregado» |

Si el buscador responde `No Tracking History` (`Ιστορικό Μη Διαθέσιμο`), el envío aún no tiene escaneos o es demasiado antiguo: ELTA conserva el historial durante seis meses desde la fecha de envío.

## Entrega, taquillas PostBox y recogida en ELTA

ELTA entrega paquetes nacionales a domicilio, en una tienda ELTA (tras SMS o aviso) o en una taquilla PostBox donde el código postal lo permite, con compromiso de entrega en 3 días laborables en toda Grecia.

**Entrega fallida.** Un paquete procedente del extranjero que no puede entregarse en mano deja un aviso para retirarlo en la oficina postal más cercana (la carta de obligaciones de ELTA no fija plazo de custodia para envíos exteriores sin aduana; los despachados de aduana esperan 20 días desde el aviso). Si el cartero no encuentra a nadie, ELTA también puede llevar el envío a la tienda cercana y depositarlo en la PostBox de esa oficina para recogerlo sin hacer cola; los envíos con cargos aduaneros solo se entregan bajo firma. Un paquete nacional no entregado en domicilio se retira en ventanilla tras llamada, resguardo o SMS dentro del plazo indicado en ese aviso o SMS: de no recogerse, vuelve al remitente sin recargo dentro de Grecia, y si tampoco lo recupera el remitente queda como no reclamado hasta al menos seis meses desde su admisión.

**ELTA Courier** realiza dos intentos de reparto. Tras el primer aviso, llama a tu tienda courier local para concertar la entrega; tras dos avisos, recógelo allí o contrata un nuevo reparto (puede tener coste adicional). Las tiendas courier abren de lunes a viernes de `08:00` a `20:00` y los sábados de `08:00` a `14:00` (según zona). Un envío courier puerta a puerta no entregable se devuelve al remitente en dos semanas desde el envío, salvo pacto distinto en el contrato del remitente.

**Taquillas PostBox.** Las taquillas inteligentes de ELTA abren `24/7` (las situadas en estaciones de Metro e ISAP siguen el horario de la estación). Al depositar el envío recibes por Viber o SMS un PIN de 6 dígitos y la ubicación: recógelo en 48 horas o pasará a la tienda ELTA más próxima con un nuevo aviso. Los envíos con aranceles o impuestos no entran en PostBox. Puedes pedir taquilla con `Θέλω από PostBox` desde el SMS o Viber de ELTA (en envíos del extranjero depende del momento de clasificación), sin coste extra, hasta `62 × 36 × 60 cm` y `20 kg`.

**Cambio de dirección.** En un envío courier solo puede pedirlo el remitente por escrito a `customerservice@elta-courier.gr`. En un envío postal con seguimiento completo, el remitente presenta una solicitud presencial con documento de identidad en una tienda ELTA.

## Paquete de ELTA retrasado, en aduanas, perdido o dañado

Si el seguimiento lleva días parado, revisa primero [por qué no se actualiza el seguimiento](guide:tracking-not-updating).

**Aduanas.** En Grecia (Unión Europea), un pedido online enviado desde fuera de la UE con valor de hasta 150 € paga desde el 1 de julio de 2026 un arancel provisional de 3 € por categoría de producto, al que desde el 1 de noviembre de 2026 se suma en ventas a distancia una tasa de tramitación de la Unión de 2 € por categoría cualquiera que sea su valor, además del IVA griego de importación (salvo en el Monte Athos, fuera del territorio de IVA de la UE) a menos que el IVA ya se cobrara al pagar mediante el sistema IOSS. En envíos de ELTA, los cargos aduaneros se abonan **únicamente en una tienda ELTA** al recoger: ELTA remite un aviso con el importe y la oficina, donde el paquete aguarda 20 días (acude tú o un autorizado con el aviso y DNI, pasaporte o permiso de conducir). Pasados 20 días regresa al centro aduanero de ELTA para declararse no reclamado o devolverse al remitente. Si impugnas el cálculo, no retires el envío: presenta en la oficina una solicitud motivada con justificante de valor ([paquetes retenidos en aduanas](guide:customs)).

**Perdido.** Remitente o destinatario pueden presentar una solicitud de búsqueda en una tienda ELTA con identificación y resguardo de envío hasta seis meses después de la admisión (solo para envíos con seguimiento completo). ELTA responde por carta certificada en 8 semanas (4 semanas en determinados envíos internacionales) ([paquete perdido](guide:lost-parcel)).

**Paquete courier dañado.** Acéptalo con reservas y decláralo dentro de un día hábil mediante el formulario de contacto de ELTA Courier o en tienda; sin ello ELTA no asume responsabilidad. El formulario de indemnización se envía a `apozimiwseis@elta-courier.gr` con albarán, factura y fotos del artículo y embalaje; se abona al remitente o a ti con renuncia escrita del remitente (departamento de indemnizaciones: `+30 210-6073066`).

**Reclamaciones.** ELTA debe responder en 15 días; si no quedas conforme, pide por escrito a Atención al Cliente elevar el caso a su Comité de Resolución de Disputas.

## Teléfonos y contacto de ELTA

Canales publicados en `elta.gr` y `elta-courier.gr`:

- **Centro de llamadas ELTA**: `800-11-82000` desde fijo griego, `212-0000-800` desde móvil, `+30 212-0000-800` desde el extranjero, de lunes a viernes de `08:00` a `18:00`.
- **Nuevos puntos de servicio**: `212 00 00 700` (exclusivo para dudas sobre nuevos puntos de servicio de ELTA), de lunes a viernes de `08:00` a `18:00`, a tarifa local.
- **Correo electrónico ELTA**: `service@elta-net.gr` o el formulario en [elta.gr/en/contact](https://www.elta.gr/en/contact).
- **ELTA Courier**: `210 6073000` (llamada ordinaria) o `11120` (tarifa de tu operador), ayuda de seguimiento de lunes a viernes de `08:00` a `16:00` (fines de semana y festivos, solo seguimiento online); `info@elta-courier.gr` y `customercare@elta-courier.gr`; reclamaciones por correo postal a `200 Ionias Ave. & 61 Iakovaton St., 111 44 Athens` (Customer Service Department).
- **Por correo postal (ELTA)**: `Hellenic Post Customer Service, Adrianoupoleos 45, 16070 Kessariani`.
- **En persona**: cualquier oficina ELTA (buscador de oficinas en `elta.gr`).

> ELTA nunca pide datos bancarios ni de tarjeta, jamás solicita por SMS o correo el pago de una tasa para entregar un paquete ni llama desde números extranjeros. Los cargos de aduana se pagan exclusivamente en una tienda ELTA. Los correos falsos titulados `Ειδοποίηση αποτυχίας παράδοσης` no son de ELTA (sus dominios oficiales son `elta.gr`, `itemsearch.elta.gr` y `elta-courier.gr`; consulta [mensajes falsos de entrega](guide:delivery-scam-texts)).

## Seguir paquetes de ELTA con Peek

Para un código terminado en `GR` con dígito de control válido, [Peek](/) consulta al instante a ELTA (y a [Chronopost](carrier:chronopost)) y muestra «ELTA Hellenic Post tiene este paquete.» cuando ELTA lo reconoce, o «Aún no aparece en … Peek seguirá comprobando cuando lo añadas.». Con dígito de control erróneo (`RR482910570GR`), ELTA no es consultado y elegirlo a mano indica que «no emite números como este»: revisa el código con el remitente. Un código alemán de 12 dígitos tecleado solo va a otros operadores: pulsa «Cambiar» (o «Cambiar transportista») o pega un enlace de `elta.gr` o `elta-courier.gr` («Transportista detectado»).

Un envío internacional se sigue primero en el correo de origen; cuando ese correo indica destino Grecia (o no indica destino y estás en Grecia), Peek consulta también a ELTA con el mismo código y pasa a seguirlo allí en cuanto aparece un escaneo nuevo, conservando el historial previo (o elige ELTA directamente con «Cambiar»).

ELTA da fecha y hora sin zona horaria: Peek las lee según el reloj de un lugar que nombra el historial (hora griega `Europe/Athens` para un historial en Grecia), las convierte a la zona de tu dispositivo («Horas en tu zona horaria: …»), muestra los textos en inglés de ELTA y la oficina o agencia de recogida, y no muestra fecha estimada ni peso (ELTA no los da). Tras cuatro días en camino sin novedades, Peek indica el día de la última línea («Sin novedades desde el lun 12 oct»). Comprueba hasta cada 10 minutos con el paquete abierto o el día de entrega con notificaciones activas, y hasta cada 2 minutos desde las 6:00 (hora griega) el día en «En reparto»; el resto del tiempo, y tras 12 h en «Etiqueta creada» / «Admitido por el transportista» o dos días sin cambios, cada hora («Cada escaneo», «Pasos importantes» o «Solo la entrega»).

## Preguntas frecuentes sobre el seguimiento de ELTA

### ¿Puedo ver el seguimiento de ELTA en inglés?

Sí. `elta.gr/en/track` muestra el formulario y todos los escaneos en inglés; `elta.gr/track` abre en griego con selector de idioma.

### ¿Puedo rastrear un envío internacional en ELTA?

Sí, con el código postal que te dio el remitente (acabado en el código del país de origen, como `RR482910576CN`): el buscador de ELTA lo acepta.

### ¿Qué es ELTA Courier?

El servicio urgente puerta a puerta de ELTA, con web propia en `elta-courier.gr` para Grecia, EMS y envíos a Chipre; se fusionó dentro de ELTA en 2024.

### ¿Dónde está mi oficina de ELTA más cercana?

Usa el buscador de tiendas en `elta.gr`; las agencias de ELTA Courier están en la página de red de `elta-courier.gr`.

:::sources
- [ELTA: Track and Trace (inglés)](https://www.elta.gr/en/track) – rastreador en inglés, formato de 13 caracteres, códigos alemanes de 12 dígitos y hasta 5 envíos
- [ELTA: Track and Trace (griego)](https://www.elta.gr/track) – rastreador en griego y selector de idioma
- [Hellenic Post: Track an Item](https://itemsearch.elta.gr/en-GB) – historial de seis meses, horario telefónico, correo y dirección postal
- [ELTA: Contact](https://www.elta.gr/en/contact) – teléfonos del centro de llamadas, número desde el extranjero, línea de nuevos puntos, teléfono courier y formulario
- [ELTA: Επικοινωνία](https://www.elta.gr/contact) – teléfonos, localizador de tiendas y datos societarios
- [ELTA: Προστατευθείτε από το Phishing](https://www.elta.gr/prosochi-sto-phishing) – alerta sobre correos y SMS falsos y pago de aduanas solo en tiendas
- [ELTA Courier: FAQ (inglés)](https://www.elta-courier.gr/faq?lang=en) – dos intentos, avisos, horario de tiendas, albarán, cambio de dirección, quejas por correo y teléfono de indemnizaciones
- [ELTA Courier: Συχνές ερωτήσεις](https://www.elta-courier.gr/faq) – número corto 11120 a tarifa del operador
- [ELTA Courier: Αναζήτηση Αποστολής](https://www.elta-courier.gr/search) – consulta de varios albaranes uno por línea
- [ELTA Courier: Επικοινωνία](https://www.elta-courier.gr/contact) – 210 6073000, horario de soporte de rastreo, correos y reserva en un día hábil
- [ELTA Courier: Έντυπο Αποζημίωσης](https://www.elta-courier.gr/apozimiwseis) – formulario de indemnización, apozimiwseis@elta-courier.gr y documentación
- [ELTA Courier: Τηλεφωνική εξυπηρέτηση](https://www.elta-courier.gr/tilefwniki_eksypiresisi) – coste de llamada y seguimiento solo online en fines de semana y festivos
- [ELTA: PostBox](https://www.elta.gr/paralabi-antikeimenon-apo-tin-isonexupni-thuridasin-elta) – PIN por SMS o Viber, 48 horas, horario, medidas 62 × 36 × 60 cm / 20 kg, gratuidad y PostBox de oficina tras ausencia
- [ELTA: Εκτελωνισμός](https://www.elta.gr/ektelonismos-2) – despacho aduanero, aviso de cargos, 20 días de custodia, identificación, autorización, no reclamados y reclamación de tasas
- [ELTA: Δέματα Εσωτερικού](https://www.elta.gr/demata-esoterikou) – entrega en 3 días laborables y modalidades nacionales
- [ELTA: Υπηρεσίες Ταχυμεταφορών](https://www.elta.gr/ypiresia-esoterikou-porta-porta) – servicio courier puerta a puerta
- [ELTA: Αίτηση Αναζήτησης](https://www.elta.gr/aitisi-anazitisis) – búsqueda en seis meses y respuesta en 8 semanas
- [ELTA: Αίτηση Τροποποίησης](https://www.elta.gr/aitisi-tropopoiisis) – cambio de nombre o dirección por el remitente
- [ELTA: Χάρτης Υποχρεώσεων Καταναλωτή](https://media.elta.gr/uploads/media/2025/kanonismoi/___2025.pdf) – fusión de ELTA Courier, recogida en ventanilla, devoluciones y seis meses de custodia, devolución courier en dos semanas, cartas U/LL/L/TR y respuesta en 15 días
- [ELTA: Γενικοί όροι Courier](https://media.elta.gr/uploads/media/etairia/general_terms_courier_12_2024_compressed%20%281%29.pdf) – indemnización pagada al remitente
- [Comisión Europea: EU Customs Reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – arancel provisional de 3 € hasta 150 € desde julio de 2026 y tasa de tramitación de 2 € desde noviembre de 2026
- [Comisión Europea: The temporary EUR 3 customs duty and the Union handling fee](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – tasa de 2 € en ventas a distancia e IVA recaudado en compra con IOSS
- [Comisión Europea: Ámbito territorial del IVA y de la unión aduanera](https://taxation-customs.ec.europa.eu/taxation/vat/vat-directive/how-does-vat-work/territorial-scope_en) – Monte Athos dentro de la unión aduanera de la UE pero fuera del territorio de IVA de la UE
- [Universal Parcel Scraper: ELTA Hellenic Post](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/elta) – códigos S10 y de 12 dígitos, enlaces, estados en inglés, punto de recogida y hora griega
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – reconocimiento S10, traspaso desde correos extranjeros y visualización en Peek
:::
