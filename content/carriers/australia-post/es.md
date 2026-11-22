---
title: Seguimiento de Australia Post: códigos, estados y taquillas
description: Rastrea un paquete de Australia Post: dónde ver el article ID, qué significa cada estado en inglés, recogida en oficina o Parcel Locker, aduanas y contacto.
slug: seguimiento-australia-post
published: 2026-11-21
updated: 2026-11-21
---

Australia Post es el servicio postal nacional de **Australia**. Puedes seguir un paquete sin necesidad de cuenta en [auspost.com.au/mypost/track](https://auspost.com.au/mypost/track), desde la app AusPost o pegando el código en el cuadro de arriba. El número lo facilita el remitente: aparece en la confirmación de compra de la tienda, en un correo de Australia Post o en el resguardo de admisión de una oficina postal australiana.

## Número de seguimiento de Australia Post: dónde está y formatos

Australia Post lo denomina número de seguimiento o identificador de artículo (*tracking number, or article ID*). Suele encontrarse en uno de estos lugares:

- **Compra en línea:** en la confirmación del pedido o la factura del comercio; si el remitente indicó tu correo electrónico a Australia Post, esta te envía directamente el código y los avances.
- **Envío depositado en una oficina postal en Australia:** a menudo en el recibo de caja.
- **Sobre prepagado (*prepaid satchel*):** junto al código de barras y en la pegatina desprendible.

Australia Post no puede localizar paquetes por nombre ni dirección, así que si no tienes el número debes pedírselo al remitente. Los envíos asociados al correo o móvil de tu cuenta MyPost se añaden solos a tu perfil y a la app. En `auspost.com.au/mypost/track` puedes consultar hasta 10 códigos a la vez.

Australia Post no publica una tabla oficial de formatos, pero Peek reconoce estas estructuras (con caracteres inventados):

| Formato (ejemplo) | Qué identifica |
| --- | --- |
| `33X482910573000482913` | Identificador de artículo de 21 caracteres: 3 letras o cifras, 9 cifras, `000` y 6 cifras. |
| `2933X482910573000482914` | El mismo identificador con 2 cifras delante (23 caracteres). |
| `94004829105736000482916` | 23 cifras que empiezan por `9400` con `000` antes de las 6 últimas cifras: sigue siendo Australia Post. |
| `29ABC4829105` | Número de expedición (*consignment number*) de 12 caracteres. |
| `ABC4829105` | Número de expedición de 10 caracteres. |
| `ABCZ48291057` | Expedición de StarTrack (12 caracteres): 3 letras o cifras, `Z` y 8 cifras. |
| `0048291057364918273643` | Código de barras prepagado de 22 cifras iniciado entre `00` y `03` (con dígito GS1). |
| `R482910573649182736491827` | `R` seguida de 24 cifras (25 caracteres). |
| `EE482910576AU` | Envío internacional EMS, paquete o carta certificada con seguimiento: `C`, `E` o `L`, una letra, 9 cifras y `AU`. |
| `RR482910576AU` | Otro código postal internacional terminado en `AU` (como correo registrado). |

Códigos parecidos: 2 letras y 10 cifras (`AB4829105736`) pertenecen a Aramex Australia o Aramex New Zealand, mientras que 22 cifras que comienzan por `9400` (`9400482910573649182736`) corresponden a [USPS](carrier:usps). Si el buscador no reconoce el código, puede tener una errata o no haberse escaneado aún: Australia Post aconseja probar de nuevo pasadas 24 horas. Más información en [formatos de números de seguimiento](guide:tracking-number-formats) y [cómo encontrar tu número de seguimiento](guide:find-tracking-number).

## Qué significan los estados de seguimiento de Australia Post

Encima del historial de escaneos, Australia Post muestra un hito resumido en inglés:

| Estado de Australia Post | Qué significa para tu envío | Peek muestra |
| --- | --- | --- |
| `Label created by sender` | El remitente generó la etiqueta; Australia Post aún no tiene el paquete. | «Etiqueta creada» |
| `Pending` | Todavía no se ha escaneado en la red de Australia Post; suele actualizarse en 24 a 48 horas. | «Esperando a Australia Post» (antes del primer escaneo) |
| `We've got it` | Australia Post ya recibió el paquete. | «Admitido por el transportista» |
| `It's on its way` | Admitido, clasificado y en tránsito por los centros de procesamiento. | «En tránsito» |
| `International arrival - awaiting clearance` | Paquete procedente del extranjero: pendiente del despacho aduanero en Australia. | «En aduanas» |
| `Possible delay` | El tiempo, una emergencia, un dato erróneo o la aduana ralentizan el envío, que sigue en camino. | Según el código del escaneo, por ejemplo «En tránsito» |
| `It's coming today` / `Onboard for delivery` | Cargado en el vehículo de reparto para entregarse hoy. | «En reparto» |
| `Attempted delivery` | No había nadie disponible o el repartidor no pudo acceder. | «Intento de entrega» |
| `Awaiting collection` | Listo para recoger en la oficina postal o taquilla *Parcel Locker* indicada en el escaneo. | «Listo para recoger» |
| `Delivered` / `Delivered - Left in a safe place` | Entregado en el domicilio o dejado en un lugar seguro (*Safe Drop*). | «Entregado» |
| `Returning to sender` | No recogido a tiempo, destinatario desconocido, rechazado o dañado: regresa al remitente. | Según el código del escaneo |

Si `Pending` o `Ready for processing` dura más de una semana, confirma con el remitente si realmente depositó el envío. En Australia, los paquetes entre estados pasan por grandes centros como Redbank (QLD), Chullora (NSW) o Sunshine West (VIC), de modo que ver un escaneo lejano es normal. En envíos llegados del extranjero a Australia, `It's on its way` indica que el bulto ya entró en Australia y se mantiene hasta que sube al vehículo de reparto.

En Australia, los carteros reparten habitualmente de 7:00 a 17:00 de lunes a viernes; si un paquete no llega antes de las 17:00, suele tener prioridad al siguiente día hábil. Con `It's coming today`, algunas zonas reciben además un SMS con una franja de entrega de 2 horas.

`Contact sender` indica que el envío volvió atrás por datos incompletos o incorrectos, o tras 10 días hábiles sin atender un aviso de recogida; `Returned` confirma que ya obra en poder del remitente, quien debe gestionar cualquier reenvío.

## Entrega fallida, oficinas postales y Parcel Lockers en Australia

Las normas siguientes aplican a entregas realizadas en **Australia**:

Si no hay nadie en casa y no cabe dejarlo en lugar seguro (*Safe Drop*), el paquete pasa a una oficina de correos cercana o, si incluye tu teléfono móvil, puede ir a una taquilla *Parcel Locker* (recibirás un código de apertura). Los usuarios de MyPost reciben aviso por correo, app o SMS; los demás pueden seguir recibiendo una tarjeta *Sorry We Missed You*. Consulta también [entrega fallida](guide:missed-delivery).

Una oficina postal australiana guarda el paquete durante **10 días hábiles** antes de devolverlo. Australia Post no vuelve a repartir a domicilio desde una oficina; trasladarlo a otra oficina cuesta **7,55 AU$** al recogerlo (el formulario de traslado sigue mostrando 7,20 AU$), y si desconoces por qué fue a parar a esa sucursal puedes enviar un aviso de incidencia de reparto y pedir la exención de la tasa. Acude con un documento con foto, o dos sin foto si uno incluye tu dirección. Otra persona puede recogerlo con autorización desde la app, un formulario o la tarjeta, más su propio documento.

Las taquillas *Parcel Lockers* son gratuitas y, en la mayoría de ubicaciones, abren 24/7. Debes retirar el paquete en **2 días hábiles** (48 horas sin contar fines de semana ni festivos) con el código de acceso o el QR de la app; vencido ese plazo (previo recordatorio por correo 24 horas antes), pasa a una oficina postal cercana durante 10 días hábiles.

Con una cuenta gratuita MyPost puedes redirigir un paquete vinculado a otra dirección antes de que salga a reparto (añade un día hábil) y activar *Safe Drop* hasta una hora antes de la entrega.

## Seguimiento sin cambios, retrasos o entregado pero no recibido

La falta de escaneos durante unos días no implica que el paquete esté parado: Australia Post señala que sigue avanzando y que la mayoría de envíos retrasados llegan dentro de los **5 días hábiles** posteriores a la fecha prevista. Ver [por qué no se actualiza el seguimiento](guide:tracking-not-updating).

Australia Post pide acudir primero al remitente para revisar código y dirección e iniciar una consulta. Si no basta, presenta el formulario de envío retrasado o extraviado (requiere cuenta gratuita MyPost) una vez cumplidos **10 días hábiles** tras la fecha esperada. En Australia, los paquetes procedentes del exterior pueden tardar hasta **15 días** en la aduana australiana —o más si devengan tributos— y aduanas o cuarentena escriben al destinatario si la retención se prolonga; las reclamaciones por demoras internacionales se abren ante el correo de origen a través del remitente. Más detalles en [paquetes retenidos en aduanas](guide:customs).

**Consta como entregado pero no está.** Revisa lugares resguardados, pregunta en tu hogar o conserjería, comprueba la foto de entrega en la app o en el formulario web y envía el parte; si la foto muestra que se entregó en tu domicilio y ha desaparecido, denúncialo a la policía. Si llegó dañado en Australia, lleva el paquete con su embalaje a una oficina postal que realice peritajes de daños. La mayoría de servicios incluyen hasta **100 AU$** de cobertura por pérdida o daño (abonados normalmente al remitente en pérdidas nacionales y al destinatario por daños), con **6 meses** de plazo desde el envío; en compras online, contacta primero con el vendedor. Lee también [entregado pero no recibido](guide:delivered-not-received).

## Teléfono de Australia Post y atención al cliente

Canales publicados por Australia Post:

- **Teléfono:** `13 76 78` (`13POST`) dentro de Australia o `+61 3 8340 7239` desde el extranjero, de 8:00 a 18:00 de lunes a viernes salvo festivos nacionales australianos.
- **En línea:** formularios de envíos tardíos/extraviados e incidencias de reparto, además de chatbot y agentes en vivo en [auspost.com.au/help](https://auspost.com.au/help).
- **En persona en Australia:** cualquiera de sus más de 4.100 oficinas postales puede ayudar a tramitar una reclamación.

StarTrack, la división de mensajería urgente y carga de Australia Post, cuenta con su propio teléfono (`13 23 45`) y formularios, pero sus envíos se rastrean en la misma red y aparecen también en la app AusPost.

> Australia Post advierte de que nunca llama, envía SMS ni correos pidiendo datos personales o bancarios no autorizados ni pagos imprevistos (tasas de reentrega, verificación de código postal o avisos de que no había lugar seguro), aunque aparezcan bajo el remitente *AusPost*. Reenvía los mensajes sospechosos a `scams@auspost.com.au` y revisa [mensajes falsos de entrega](guide:delivery-scam-texts).

## Rastrear paquetes de Australia Post con Peek

[Peek](/) identifica a Australia Post al instante ante identificadores de artículo de 21 y 23 caracteres y ante códigos internacionales EMS, paquete o carta con seguimiento terminados en `AU` (`C…AU`, `E…AU`, `L…AU`) con dígito de control válido, mostrando «Transportista detectado» y «Cambiar». Los números de expedición (incluidos los de StarTrack), los códigos prepagados de 22 cifras y los códigos `R` de 25 caracteres son compartidos o de baja certeza: al añadirlos, Peek consulta a los operadores probables («Comprobando servicios de seguimiento…») y muestra «Australia Post tiene este paquete.» en cuanto responde su buscador, o te permite elegir Australia Post con «Cambiar» bajo «Encaja con este número». Cualquier otro código postal acabado en `AU` se abre como envío postal mientras Peek pregunta a Australia Post. Los enlaces de `auspost.com.au` con `id=` (incluido `#/search?id=`) o `/mypost/track/details/` asignan directamente Australia Post sea cual sea el código.

Si la pasarela de Australia Post rechaza la petición directa de Peek, un navegador en el servidor de Peek abre `auspost.com.au`; dado que los agregadores externos apenas guardan eventos de Australia Post, el historial puede verse escaso si también bloquean el navegador.

Peek muestra el estado actual, cada escaneo con su localidad en el mapa, la hora exacta de entrega, el país de destino y el nombre de la oficina postal o *Parcel Locker* donde aguarda el paquete, descartando destinatario, calle y fotos de entrega (la entrega se muestra solo como `Delivered`). Los escaneos propios de Australia Post conservan su huso australiano, convertidos a la zona horaria de tu dispositivo («Horas en tu zona horaria: …» al pie del historial en `peektracker.com` y en las apps de iPhone y Android). Mientras el envío está en camino, Peek presenta la fecha o el rango previsto de Australia Post hasta que pasa —y el día de reparto, la franja horaria mientras siga vigente—, ocultando la estimación tras un intento de entrega fallido o una incidencia, y cuando el paquete queda entregado, devuelto o listo para recoger. Si Australia Post no publica fecha (por ejemplo, envíos depositados en buzón callejero o con dirección manuscrita), su propia web aún puede estimarla con tu código postal. Peek no puede redirigir paquetes ni cambiar taquillas.

Mientras Australia Post aún no conozca el código, Peek muestra «Esperando a Australia Post»; si pasados dos días ni Australia Post ni otra fuente responden, muestra «Tras dos días, el transportista sigue sin reconocer este número. Revísalo, elige otro transportista o elimina el paquete.», y tras cuatro días sin nuevos escaneos en ruta ofrece «Pide ayuda a Australia Post».

Mientras mantengas el paquete abierto en pantalla o, el día de entrega, con las notificaciones activadas, Peek comprueba novedades hasta cada 10 minutos —y hasta cada 2 minutos (cada 5 minutos cuando necesita usar el navegador) el día en que Australia Post escanea el paquete en reparto, desde las 6:00 hora local o media hora antes de abrirse la franja horaria anunciada, siempre que responda el rastreador de Australia Post (mediante servicios de seguimiento externos solos: cada hora, cada 3 horas tras un día sin cambios y hasta cada 15 minutos el día de entrega con el paquete abierto o avisos activos; cuando un operador extranjero asume el reparto final, Peek sigue su ritmo)—; el resto del tiempo, y tras dos días sin novedades (o 12 horas en «Etiqueta creada» o «Admitido por el transportista»), revisa cada hora. Puedes elegir alertas para «Cada escaneo», «Pasos importantes» o «Solo la entrega». Un envío internacional con código postal hacia Australia pasa a seguirse en Australia Post en cuanto esta registra avances.

## Preguntas frecuentes sobre el seguimiento de Australia Post

### ¿Qué significa Pending en el seguimiento de Australia Post?

Que el paquete todavía no se ha escaneado en el sistema de Australia Post: puede no haberse entregado aún o haberse depositado en un buzón callejero en Australia. Normalmente se actualiza en 24 a 48 horas. Un código antiguo sin actividad durante más de 3 meses también muestra `Pending`.

### ¿Cuánto tiempo guarda Australia Post un paquete en un Parcel Locker?

**2 días hábiles** (48 horas excluyendo fines de semana y festivos). Después se traslada a la oficina postal cercana con servicio de recogida, donde permanece **10 días hábiles** antes de devolverse al remitente.

### ¿Todos los paquetes de Australia Post llevan seguimiento?

No: *International Post Registered* e *International Economy* (aéreo y marítimo) no incluyen seguimiento de serie. En envíos remitidos desde otro país hacia Australia, el seguimiento depende del servicio contratado con el correo o transportista de origen.

:::sources
- [Australia Post: Seguimiento de paquetes](https://auspost.com.au/mypost/track) – consulta de hasta 10 números de seguimiento a la vez
- [Australia Post: Lost tracking number](https://auspost.com.au/help/parcel-tracking/tracking-support/lost-tracking-number) – dónde está el número, sin búsqueda por nombre y reintento en 24 horas
- [Australia Post: Help with tracking](https://auspost.com.au/help/parcel-tracking/tracking-support/help-with-tracking) – identificador article ID, centros de clasificación y eventos recientes
- [Australia Post: What your status means](https://auspost.com.au/help/parcel-tracking/tracking-support/what-your-status-means) – Pending (24–48 h y más de 3 meses), intento fallido, tarjeta Sorry We Missed You, It's on its way internacional y SMS de 2 horas
- [Australia Post: Tracking service](https://auspost.com.au/personal/receiving/tracking-service) – seguimiento sin cuenta y centros de Redbank, Chullora y Sunshine West
- [Australia Post: Taking longer than expected](https://auspost.com.au/help/parcel-tracking/taking-longer-than-expected) – Pending y Ready for processing más de una semana y margen de 5 días hábiles
- [Australia Post: Delays and disruptions](https://auspost.com.au/help/parcel-tracking/delays-and-disruptions) – significado de Possible delay
- [Australia Post: Return to sender](https://auspost.com.au/help/delivered-parcel/return-to-sender) – Returning to sender, Returned y Contact sender tras 10 días hábiles
- [Australia Post: Missed parcel deliveries](https://auspost.com.au/personal/receiving/parcel-deliveries/missed-parcel-deliveries) – paso a oficina o taquilla, identificación, autorización a terceros y traslado por 7,55 AU$
- [Australia Post: Requested delivery but parcel at Post Office](https://auspost.com.au/help/delivered-parcel/parcel-at-post-office) – custodia de 10 días hábiles, sin reentrega desde oficina y exención de tasa mediante parte de incidencia
- [Australia Post: Transfer to another post office](https://helpandsupport.auspost.com.au/s/products-and-services?transfertoPO) – formulario de traslado que aún muestra 7,20 AU$
- [Australia Post: 24/7 Parcel Lockers](https://auspost.com.au/help/collection-delivery/parcel-lockers) – acceso 24/7 en la mayoría de ubicaciones, 48 horas sin fines de semana ni festivos, código y aviso 24 h antes
- [Australia Post: Redirect parcel or change address](https://auspost.com.au/help/parcel-tracking/redirect-parcel-change-address) – desvío con MyPost antes del reparto y día hábil adicional
- [Australia Post: Leave in a safe place (Safe Drop)](https://auspost.com.au/help/collection-delivery/safe-drop) – solicitud hasta 1 hora antes y reparto de 7:00 a 17:00 de lunes a viernes
- [Australia Post: AusPost app](https://auspost.com.au/personal/receiving/australia-post-app) – avisos digitales, tarjetas físicas y franja de 2 horas
- [Australia Post: No recent update](https://auspost.com.au/help/parcel-tracking/no-recent-update) – tramos sin escaneo, 5 días hábiles, reclamación tras 10 días hábiles y horario telefónico
- [Australia Post: Lost or late items](https://auspost.com.au/personal/receiving/parcel-deliveries/lost-late-items) – formulario de consulta y cuenta MyPost
- [Australia Post: Customs](https://auspost.com.au/help/parcel-tracking/international-parcel/customs) – hasta 15 días en la aduana australiana, cartas de aduanas y consulta ante el correo de origen
- [Australia Post: International tracking](https://auspost.com.au/help/parcel-tracking/international-parcel/tracking) – servicios sin seguimiento estándar (International Post Registered, International Economy) y seguimiento de entrada
- [Australia Post: Delivered status but parcel not received](https://auspost.com.au/help/delivered-parcel/parcel-not-received) – comprobaciones, foto de entrega y denuncia policial
- [Australia Post: Damaged parcel](https://auspost.com.au/help/delivered-parcel/damaged-parcel) – evaluación de daños en oficina postal
- [Australia Post: Compensation](https://auspost.com.au/personal/receiving/parcel-deliveries/compensation) – cobertura de hasta 100 AU$, quién cobra, plazo de 6 meses y contacto previo con el vendedor
- [Australia Post: Complaints and feedback](https://auspost.com.au/about-us/corporate-information/complaints-and-feedback) – teléfonos 13 76 78, +61 3 8340 7239, StarTrack 13 23 45 y ayuda en oficinas
- [Australia Post: Help & support](https://auspost.com.au/help) – formularios en línea y chat
- [Australia Post: Expected delivery date](https://auspost.com.au/help/parcel-tracking/tracking-support/expected-delivery-date) – fecha o rango estimado, envíos sin fecha y cálculo por código postal
- [Australia Post: StarTrack tracking](https://auspost.com.au/help/startrack/tracking) – seguimiento de StarTrack en el mismo buscador y en la app AusPost
- [Australia Post: Scam alerts](https://auspost.com.au/about-us/about-our-site/online-security-scams-fraud/scam-alerts) – mensajes fraudulentos AusPost y reenvío a scams@auspost.com.au
- [Australia Post: About us](https://auspost.com.au/about-us) – red de más de 4.100 oficinas postales en Australia
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formatos y enlaces reconocidos, pasarela y lectura por navegador, fecha y franja previstas, husos horarios y privacidad
:::
