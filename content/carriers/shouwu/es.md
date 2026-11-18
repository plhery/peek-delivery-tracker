---
title: Seguimiento Shouwu: número SWECN…YQ, estados y reparto
description: Rastrea un paquete de Shouwu International (守务国际) desde China: cómo es el número SWECN…YQ, qué significan sus estados y quién lo reparte en España.
slug: shouwu-seguimiento
published: 2026-11-18
updated: 2026-11-18
---

Shouwu International (守务国际, también abreviado SWE) es un operador logístico de Shanghái que transporta paquetes de tiendas online chinas hacia Europa y Estados Unidos, donde un servicio postal o una mensajería local realiza la entrega final. Si una tienda envió tu pedido con Shouwu, el número empieza por `SWECN` y termina en `YQ`: pégalo en el cuadro de arriba o en el buscador en inglés de Shouwu en [admin.shshouwu.com](https://admin.shshouwu.com/track.html). El número te lo facilita el vendedor junto con los datos de envío de tu pedido; Shouwu no publica ningún canal de atención para destinatarios.

## Número de seguimiento de Shouwu: cómo es

Las guías propias de Shouwu se componen del prefijo `SWECN`, doce dígitos y el sufijo `YQ`, 19 caracteres en total. Con cifras de ejemplo:

| Número | Qué es |
| --- | --- |
| `SWECN482910573649YQ` | `SWECN`, doce dígitos y `YQ`: un número de seguimiento de Shouwu International. |
| `SWECN48291057364YQ` | Solo once dígitos: no coincide con el formato de Shouwu. Revisa si falta una cifra al copiarlo. |
| `JSEAA4829105736YQ` | Termina también en `YQ`, pero abre con `JSE` y diez cifras: pertenece a [JS Express](carrier:js-express). |

Otros transitarios chinos cierran igualmente sus códigos con `YQ`, aunque con sus propias letras delante y, en el catálogo de Peek, casi siempre diez dígitos intermedios. La combinación de `SWECN` más doce cifras identifica en exclusiva a Shouwu. En Peek no importan los espacios, los guiones ni que lo pegues en minúsculas.

**Dónde encontrarlo.** Si no aparece en el correo de confirmación de envío ni en el detalle de tu compra en la tienda o plataforma, consulta [cómo encontrar tu número de seguimiento](guide:find-tracking-number).

**Puede que te hayan dado el número del transportista final.** Según las páginas de líneas de Shouwu, el vendedor puede elegir qué código entrega al comprador: el número propio de Shouwu, que acompaña al paquete de principio a fin, o directamente el código de la empresa de reparto que entrega en el país de destino. Por eso un pedido gestionado por Shouwu puede llegarte bajo el número de una mensajería o correo europeo. Pégalo igualmente arriba: Peek reconoce más de 3.500 transportistas.

**Un enlace de seguimiento.** El botón «Copy Link» del rastreador de Shouwu copia una dirección terminada en `?q=` seguida del número. Ese enlace abre directamente el envío y también puedes pegarlo entero en Peek. El buscador de Shouwu admite hasta 40 números a la vez, uno por línea.

## Estados de seguimiento de Shouwu explicados

El portal de seguimiento de Shouwu está en inglés bajo el encabezado en chino `包裹追踪`. Para cada envío muestra un estado general y el país de destino, seguidos de la hora y el texto de cada escaneo, pero sin enseñar el lugar en su tabla.

| Estado de Shouwu | Qué significa para ti | Peek muestra |
| --- | --- | --- |
| `Not Found` | Shouwu aún no tiene registros con ese código: «We seem can't identify the number.» | «Esperando a Shouwu International» en peektracker.com |
| `Preparing` | Según explica Shouwu en su web, el paquete se ha entregado en su almacén en China o se está pesando y precintando allí. Puede aparecer antes del primer escaneo. | «Etiqueta creada», y después «Admitido por el transportista» tras la recogida del socio o la entrada en almacén |
| `In Transit` | Abarca todo el trayecto desde la entrega a un transportista hasta el despacho aduanero y el transporte dentro del país de destino. | «En tránsito», o la etapa concreta del último escaneo, como «En aduanas»; se mantiene en «Etiqueta creada» mientras solo consten los datos del pedido y `Carrier received the shipment` |
| `Undelivered` | Ha fallado un intento de entrega. | «Intento de entrega», o bien «Listo para recoger» o «Devuelto al remitente» si así lo indica el último escaneo |
| `Delivered` | Entregado al destinatario. | «Entregado» |

El propio portal advierte que sus estados generales se asignan de forma automática («auto-judged by the system») y sirven solo como orientación («only for information reference use»). El detalle real está en las líneas del historial:

| Lo que escribe Shouwu | Qué significa para ti | Peek muestra |
| --- | --- | --- |
| `Shipment information received`, `Order information received. We're expecting your parcel to arrive with us.` | El vendedor ha registrado el envío electrónicamente; Shouwu todavía no tiene el paquete físico. | «Etiqueta creada» |
| `Carrier received the shipment` | Shouwu anota esta línea unas dos horas después de los datos del pedido, días antes del primer escaneo real de almacén: nadie ha recogido aún la caja. | «Etiqueta creada» |
| `Picked up by Shipping Partner` | El socio logístico de Shouwu en China ha recogido el paquete al vendedor; primer movimiento físico tras los datos del pedido. | «Admitido por el transportista» |
| `Arrival processing center`, `Arrived at Transit logistics facility` | El almacén de Shouwu en China ha dado entrada al paquete. | «Admitido por el transportista» |
| `Package processing completed` | El almacén ha terminado de procesar el paquete antes de enviarlo al vuelo. | «Admitido por el transportista» |
| `Item inbound in sorting center`, `Item outbound in sorting center`, `Item is in transit in Hong Kong` | Entrada y salida en el centro de clasificación y tránsito por Hong Kong rumbo al aeropuerto. | «En tránsito» |
| `Shipment started the customs clearance process at origin`, `Export Clearance` | Ha comenzado el despacho aduanero de exportación en China, un trámite rutinario previo al embarque; la aduana no retiene tu paquete. | «En tránsito» |
| `The package is shipped from the transfer warehouse to the airport Export customs clearance in progress` | El envío sale del almacén de tránsito hacia el aeropuerto mientras avanza la exportación en China (no es la aduana de tu país). | «En tránsito» |
| `Export customs declaration completed`, `Export customs clearance completed`, `Export Released` | La aduana china ha autorizado la exportación sin incidencias. | «En tránsito» |
| `The parcel has arrived at the airport and is currently being loaded onto a flight` | En el aeropuerto de salida tras el despacho de exportación, cargándose en un avión. | «En tránsito» |
| `Arrived at Shanghai Pudong Airport`, `Departed from export airport`, `The flight has departed` | Llegada al aeropuerto de Shanghái-Pudong y despegue del vuelo internacional fuera de China. | «En tránsito» |
| `The ship has already set sail` | En una ruta marítima: el buque ha zarpado de China tras la exportación y navega hacia el puerto de destino. | «En tránsito» |
| `Arrived at airport of Destination`, `Shipment arrived in the destination country`, `Item is in transit in European` | El envío ha aterrizado en el país de destino o circula ya por Europa antes del despacho de importación. | «En tránsito» |
| `NOA received` | El agente en destino ha recibido el aviso de llegada de la aerolínea (*Notice of Arrival*); el siguiente paso es la aduana. | «En tránsito» |
| `Shipment started the customs clearance process at destination`, `Customs clearance in progress`, `Currently undergoing customs clearance in the destination country`, `Currently processing import customs clearance`, `Customs clearance for imports`, `Under Clearance`, `Item has started customs clearance process` | La aduana del país de destino está tramitando la importación. | «En aduanas» |
| `Custom clearance completed`, `Shipment completed the customs clearance process at destination`, `Customs clearance has been completed`, `Customs clearance in the destination country has been completed` | La aduana de destino ha liberado el paquete. | «En tránsito» |
| `Scheduled For Delivery` | Anotación de Shouwu tras el levante aduanero, días antes de que el paquete entre en la delegación y en la ruta del mensajero: el reparto está previsto, no en curso. | «En tránsito» |
| `Delivery to local courier`, `The goods have been deliverey to the final delivery service provider`, `The goods have been delivered to the carrier` | Entregado a la mensajería local encargada del último tramo; aún no te lo han entregado a ti («deliverey» es la grafía literal de Shouwu). | «En tránsito» |
| `Your consignment has been picked up` | El transportista local ha recogido la remesa en el almacén exterior de Shouwu después de aduanas; no es la recogida al vendedor (`Picked up by Shipping Partner`) ni una recogida por tu parte. | «En tránsito» |
| `Picked up`, `Arrived at FedEx location`, `Departed FedEx location` | En envíos a EE. UU., FedEx recoge el paquete en el almacén de Shouwu tras la importación (indicando localidad y código postal) y lo mueve por sus centros. | «En tránsito» |
| `Parcel on its way`, `arrival city`, `leave the city` | Tránsito nacional del transportista final tras el despacho de importación, entrando y saliendo de cada localidad. | «En tránsito» |
| `The shipment has been processed in the destination parcel center` | DHL ha clasificado el paquete en el centro de destino que atiende tu zona antes de sacarlo a reparto. | «En tránsito» |
| `Mis routed` | En rutas entregadas en Alemania, el paquete se desvió a una instalación equivocada y se está reencaminando: supone un retraso, no un bloqueo, y a veces sale a reparto ese mismo día. | «En tránsito» |
| `Out for delivery` | El paquete va en el vehículo de reparto del mensajero local. | «En reparto» |
| `Abnormal delivery` | El reparto no pudo completarse; el motivo concreto suele figurar aparte entre paréntesis angulares, como `<Incorrect address.>`. | «Intento de entrega» |
| `The recipient is unknown` | En un reparto de DHL en Alemania no se localizó al destinatario en la dirección indicada; el paquete inicia la vuelta. | «Intento de entrega» |
| `Shipment will be returned to origin-Bad address` | DHL devuelve el envío por dirección incorrecta: está de regreso, todavía no en manos del remitente. Contacta con el vendedor. | «Incidencia» |
| `Delivered by post man`, `This parcel has been delivered` | El servicio postal o la mensajería local ha entregado el paquete. | «Entregado», una vez que el estado general de Shouwu marca también `Delivered` |
| `Shipment sent directly from parcel center to business customer` | DHL ha entregado el envío directamente desde su centro de paquetería a un cliente empresarial; si venía de una devolución previa, indica que el retorno llegó al remitente. | «Entregado», o «Devuelto al remitente» tras una devolución |

Cuando [iMile](carrier:imile) realiza la entrega en Italia, el historial de Shouwu transcribe las líneas de iMile con el centro entre corchetes `【】` (tu seguimiento puede citar otro centro distinto de `Milan center` o `ROM 10`):

| Lo que escribe Shouwu | Qué significa para ti | Peek muestra |
| --- | --- | --- |
| `【Milan center】Received.` | El centro regional de iMile ha recibido el paquete. | «En tránsito» |
| `【Milan center】Parcel shipped to【ROM 10】.`, `【ROM 10】Arrived.` | Enviado hacia la estación de reparto de iMile y recibido allí. | «En tránsito» |
| `【ROM 10】Shipment out for delivery.` | En ruta de reparto con iMile. | «En reparto» |
| `<User not at home>`, `【ROM 10】 Back To DS.` | El repartidor no encontró a nadie en casa y el paquete ha vuelto a la estación de entrega. | «Intento de entrega» |
| `【ROM 10】Parcel shipped to【Milan Transit Center】.` | Tras dos intentos fallidos, la estación devuelve el envío al centro de tránsito de iMile. | «En tránsito» |
| `Back to Sender for reason: wrong recipient address or address related problem` | Tras los intentos sin éxito, iMile devuelve el paquete (aquí por un problema con la dirección, aunque puede figurar otro motivo tras los dos puntos): va de vuelta, aún no está con el remitente. Contacta con la tienda. | «Incidencia» |
| `【Milan Transit Center】Order Closed.` | iMile cierra la orden de entrega sin haber podido entregar; avisa al vendedor. | «Incidencia» |

**Cuando reparte DPD.** En las líneas donde entrega [DPD](carrier:dpd-de), el historial prosigue con los mensajes en inglés de DPD terminados en punto: `In transit.`, luego `At parcel delivery centre.` —la delegación de reparto de DPD, antes de salir a calle y también tras un intento fallido («En tránsito»)— y `Out for delivery.` («En reparto»). `Unfortunately we have not been able to deliver your parcel. Consignee address not correct.` señala un intento fallido por dirección errónea en el que la caja regresa a la delegación de DPD, no todavía al remitente («Intento de entrega»).

**Líneas en chino.** Algunos envíos incluyen además anotaciones en chino que Peek muestra tal como vienen escritas. `货物电子信息已经收到` (recibidos los datos electrónicos de la mercancía) acompaña al registro inicial: «Etiqueta creada». Después pasan a «En tránsito»: `申报过港中` tras el código de un lote (declarado para cruzar por carretera hacia Hong Kong), `已到港，安排航班中` (en el punto de salida, asignando vuelo), `预计23号的航班` (vuelo reservado, aquí para el día 23; con `二程` delante cuando se trata del segundo tramo aéreo), `航班已飞` y `二程航班已飞` (el vuelo, o el segundo tramo, ha despegado) y `已落地` (aterrizado). `已落地，清关提货中` (aterrizado, despachando en aduana y retirando la carga de la aerolínea) corresponde a «En aduanas», mientras que `清关完成` (despacho finalizado) y `已交仓扫描` (escaneado al entregar en el almacén del repartidor) vuelven a marcar «En tránsito».

**Horas desplazadas.** El sistema interno de Shouwu guarda la hora local de casi cada escaneo —incluidos los de aerolíneas y mensajerías en el extranjero— como si fuera hora de Pekín (`UTC+8`), y su página web convierte después esa marca al huso de tu navegador. Por eso un escaneo registrado a las 10:00 en la España peninsular o Baleares aparece en la web de Shouwu a las 03:00 en invierno (`UTC+1`) y a las 04:00 durante el horario de verano europeo (`UTC+2`, del último domingo de marzo al último domingo de octubre), o una hora antes aún en Canarias (`UTC+0` en invierno y `UTC+1` en verano), haciendo que el vuelo parezca durar seis, siete u ocho horas menos. Fíate del orden de las filas. Peek deduce el reloj real de cada evento a partir de su ubicación o del tramo del viaje y convierte todas las marcas a la zona horaria de tu dispositivo; en peektracker.com y en la app de iPhone, el pie del historial indica «Horas en tu zona horaria: …».

## Quién entrega los paquetes de Shouwu en España y en Europa

En su página corporativa en chino, Shouwu afirma colaborar directamente con USPS, Royal Mail, La Poste, DHL, DPD y Hermes. Sus fichas de ruta, también en chino, detallan los transportistas finales por país donde publica línea propia:

- **España.** En `www.sedel.com`, Shouwu **no publica ninguna línea específica para España ni nombra al transportista español** encargado del último kilómetro, y su página de seguimiento tampoco muestra el nombre ni el código de la empresa de reparto salvo que el texto de algún escaneo la mencione. Si necesitas saber qué mensajería lleva tu paquete en España o pedir su número nacional, pregúntaselo directamente al vendedor.
- **Otros países de la Unión Europea.** Shouwu publica rutas propias hacia Alemania —entregadas principalmente por [DHL](carrier:dhl), [Hermes](carrier:hermes-de) o [DPD](carrier:dpd-de) según la modalidad—, Francia (sobre todo [Colissimo](carrier:la-poste), además de Colis Privé en algunos historiales), Países Bajos (principalmente DHL), Suecia (principalmente Early Bird) e Italia (línea económica de 10 a 12 días laborables entregada por UniUni y [Poste Italiane](carrier:poste-italiane), además de [iMile](carrier:imile) en historiales leídos por Peek).
- **Reino Unido y Estados Unidos.** Las rutas al Reino Unido reparten con [Royal Mail](carrier:royal-mail), Yodel y [Evri](carrier:evri) (escrito `ERVI` en su web; la línea para artículos con batería va sobre todo por Royal Mail), y las de Estados Unidos principalmente con [USPS](carrier:usps), además de [FedEx](carrier:fedex) en algunos envíos.

**Entregas fallidas.** Los intentos de reparto, avisos y puntos de recogida se rigen por las normas de la mensajería local que tenga tu paquete. Bajo el estado `Undelivered`, el rastreador de Shouwu recomienda contactar cuanto antes con el transportista para concertar otra entrega o recoger el envío antes de que venza el plazo de custodia, advirtiendo de que de lo contrario «might be returned to the sender» (podría devolverse al remitente). En cambio, las condiciones en chino de sus líneas al Reino Unido y EE. UU. señalan que los paquetes que no pueden entregarse no regresan a China y se destruyen por defecto. En cualquier caso, actúa rápido ante un intento fallido; consulta [qué hacer tras un intento de entrega fallido](guide:missed-delivery) y [paquetes no recogidos a tiempo](guide:uncollected-parcel).

## Paquete de Shouwu que no llega o seguimiento parado

**`Not Found`.** Shouwu enumera cuatro motivos posibles en su buscador: aún no ha aceptado el paquete, todavía no lo ha escaneado, el número introducido es erróneo o el envío se cursó hace tanto tiempo que el registro ya expiró, añadiendo que el procesado inicial suele tardar unos días. En peektracker.com verás «Esperando a Shouwu International» mientras Peek sigue consultando. Si pasados dos días Shouwu sigue sin reconocer el código y ninguna otra fuente responde por él, aparece el aviso «Tras dos días, el transportista sigue sin reconocer este número. Revísalo, elige otro transportista o elimina el paquete.» y las comprobaciones pasan a ser diarias, y semanales cuando se cumple una semana desde que lo añadiste. Revisa el código carácter por carácter y, si no cambia tras varios días, escribe al vendedor.

**Parado en `Preparing`, `Shipment information received` o `Carrier received the shipment`.** Shouwu todavía no ha recibido físicamente la mercancía o aún no le ha dado salida desde su almacén en China. Pregunta a la tienda en qué fecha entregó el bulto al operador.

**Retenido en aduanas y cargos de importación en España.** Tras mensajes como `Shipment started the customs clearance process at destination`, `Customs clearance in progress`, `Currently undergoing customs clearance in the destination country`, `Currently processing import customs clearance`, `Customs clearance for imports` o `已落地，清关提货中`, el paquete espera el despacho de importación. Las páginas de líneas de Shouwu no aclaran si el vendedor envía con impuestos pagados ni quién asume los cargos aduaneros. En la Unión Europea, los bienes comprados online fuera de la UE están sujetos a un arancel fijo de 3 € por categoría de producto en envíos de hasta 150 € (desde el 1 de julio de 2026) y a la tasa de tramitación aduanera de la Unión de 2 € por categoría de producto cualquiera que sea el valor del paquete (desde el 1 de noviembre de 2026). Según informa [Correos](carrier:correos-spain) para **España**, ambos cargos de la UE se aplican en **Península, Baleares y Canarias** (Canarias forma parte del territorio aduanero de la Unión aunque quede fuera del ámbito armonizado del IVA de la UE), mientras que **Ceuta y Melilla** están fuera tanto del territorio aduanero como del área de IVA de la Unión y esos recargos comunitarios no se aplican a las importaciones con destino a ambas ciudades autónomas. En **España**, el INCIBE advierte además de campañas de SMS fraudulentos (*smishing*) que suplantan a Correos, Correos Express y otras empresas de paquetería reclamando falsas tasas de aduanas mediante un enlace: no pulses el enlace del mensaje y verifica siempre el envío directamente en los canales oficiales del transportista. Más detalles en [paquetes retenidos en aduanas](guide:customs).

**Sin novedades después de `Delivery to local courier`.** El paquete ya pasó a manos del repartidor nacional, pero el rastreador de Shouwu no facilita su número de seguimiento. Pide al vendedor el código de la mensajería final y añádelo a Peek. Tienes más contexto en [por qué el seguimiento deja de actualizarse](guide:tracking-not-updating) y [seguimiento de paquetes desde China](guide:tracking-from-china).

**Consta como entregado, pero no has recibido nada.** Las condiciones de línea de Shouwu indican que, cuando la web del transportista final marca el envío como entregado y el destinatario no lo ha recibido, Shouwu ayuda a investigar el caso pero no abona indemnización. Reclama primero a la empresa de reparto local y acto seguido a la tienda o plataforma donde compraste. Consulta [paquete entregado pero no recibido](guide:delivered-not-received).

**Paquete perdido o dañado.** El cliente contractual de Shouwu es el vendedor, que según las condiciones de sus rutas dispone de 30 días desde la salida del envío con Shouwu para presentar reclamación. Cuando un paquete ingresado en el almacén de Shouwu no llega a registrar escaneo de entrega, Shouwu indemniza al remitente el valor declarado más el porte hasta un máximo de 200 yuanes (RMB) por envío, y no cubre daños sufridos durante el tránsito. Por tanto, solicita el reembolso o un reenvío directamente en la tienda o marketplace sin dejar pasar los plazos de protección al comprador. Ver [paquetes perdidos](guide:lost-parcel).

## Teléfono y atención al cliente de Shouwu

La web corporativa de Shouwu, `http://www.sedel.com/`, está íntegramente en chino y orientada a vendedores que contratan envíos comerciales. Publica números de teléfono móvil en China para su sede de Shanghái y cinco sucursales, un enlace de chat en QQ y códigos QR de WeChat, pero no ofrece dirección de correo electrónico, formulario web, atención en español ni en inglés, ni ningún canal pensado para destinatarios particulares. El buscador `admin.shshouwu.com` tampoco incluye datos de contacto.

Para cualquier gestión sobre tu compra o una reclamación, dirígete al vendedor que contrató el envío con Shouwu; para concertar o consultar la entrega final en tu domicilio, contacta con la empresa de reparto local si ya conoces su número.

## Seguir un paquete de Shouwu con Peek

[Peek](/) identifica al instante un número de Shouwu por su estructura (`SWECN`, doce dígitos y `YQ`), que ningún otro transportista de su catálogo utiliza, y consulta directamente el rastreador de Shouwu sin pedirte cuenta, código postal ni teléfono.

Peek traduce la etapa actual a partir del estado de Shouwu y del último escaneo según las tablas anteriores, y muestra tanto el país de destino como la ubicación asociada a cada evento, un dato que la tabla web de Shouwu oculta. Cuando el campo de lugar contiene alguna cifra —como un código postal o un número de calle—, Peek lo descarta para no mostrar texto confuso. Shouwu no calcula fecha estimada de entrega, por lo que Peek tampoco enseña una previsión.

Como el rastreador de Shouwu no comunica aparte quién es el repartidor local ni cuál es su código, Peek no muestra una línea de «Entrega con» ni transfiere el paquete automáticamente a otra mensajería, ni siquiera cuando el texto de los escaneos reproduce mensajes de iMile o DPD. Si el vendedor te facilita el número nacional de reparto, añádelo a Peek como un envío independiente.

Mientras tienes el paquete abierto, o el día de reparto con las notificaciones activadas, Peek comprueba el estado hasta cada 10 minutos, y hasta cada 2 minutos a partir de las 6:00 (hora local) el día en que sale a reparto cuando responde el propio buscador de Shouwu; el resto del tiempo consulta cada hora, igual que tras dos días sin novedades o pasadas 12 horas si el envío sigue en «Etiqueta creada» o «Admitido por el transportista». Si en algún momento la consulta pasa por servicios externos de seguimiento, la frecuencia es de una vez por hora, cada 3 horas tras un día sin nuevos escaneos y, el día en que el paquete está en reparto, hasta cada 15 minutos con el envío abierto o las notificaciones activas. Puedes elegir avisos para «Todas las novedades», «Solo importantes» (entregas, paquetes listos para recoger, aduanas, intentos fallidos y devoluciones) o «Solo el día de entrega». Peek solo lee el seguimiento: no puede contactar con Shouwu ni con el vendedor por ti.

## Preguntas frecuentes sobre el seguimiento de Shouwu

### ¿Qué es Shouwu International (守务国际)?

Una empresa logística de Shanghái (`上海守务国际物流有限公司`, también llamada SWE) dedicada al transporte transfronterizo para comercio electrónico. Según su página corporativa, comenzó en 2018 como transitario de carga aérea internacional y en 2019 orientó su actividad a las líneas de paquetería e-commerce hacia Europa y Estados Unidos.

### ¿Dónde puedo rastrear un paquete de Shouwu?

En el cuadro de la parte superior de esta página o en el rastreador oficial en inglés de Shouwu en `admin.shshouwu.com`. Introduce el código completo de 19 caracteres con formato `SWECN…YQ`.

### ¿Quién reparte los paquetes de Shouwu en España?

Shouwu no publica en su web ninguna línea propia para España ni indica qué mensajería entrega allí, mientras que en otros países europeos nombra socios como Colissimo en Francia, DHL, Hermes o DPD en Alemania y UniUni o Poste Italiane en Italia. Pide al vendedor el nombre y el número de seguimiento del transportista local que lleva tu pedido en España.

### ¿Cuánto tarda en llegar un envío de Shouwu?

Depende de la línea contratada por la tienda. Shouwu no publica plazo de referencia para España; en sus rutas europeas publicadas anuncia de 4 a 7 días laborables a Francia y Alemania, de 4 a 8 días laborables a Países Bajos y Suecia, y de 10 a 12 días laborables en su línea económica a Italia.

### ¿Con quién contacto si mi paquete de Shouwu se pierde o no llega?

Con la tienda o plataforma donde realizaste la compra: el vendedor es el cliente de Shouwu y dispone de 30 días desde la salida del envío para reclamarle. Shouwu no ofrece ningún canal de atención pensado para destinatarios particulares.

:::sources
- [Shouwu International: 守务国际包裹追踪 (seguimiento de paquetes)](https://admin.shshouwu.com/track.html) – rastreador en inglés, hasta 40 números, estados y sus descripciones, escaneos sin columna de lugar, botón «Copy Link» y mensaje `Not Found`
- [Shouwu International: 首页 (inicio, en chino)](http://www.sedel.com/) – razón social, teléfonos móviles en China para Shanghái y cinco sucursales, chat QQ y códigos WeChat
- [Shouwu International: 关于我们 (sobre nosotros, en chino)](http://www.sedel.com/about-us.html) – inicios en 2018 y giro en 2019 a líneas e-commerce para Europa y EE. UU., socios directos
- [Shouwu International: 主营线路 (líneas principales, en chino)](http://www.sedel.com/main-route.html) – países con línea publicada (sin ruta listada para España)
- [Shouwu International: 英国标快 (Reino Unido estándar, en chino)](http://www.sedel.com/main-route-secondary.html?groupId=1&groupItemId=2) – Royal Mail, Yodel y ERVI, elección de número, 5 a 8 días laborables, destrucción por defecto sin retorno a China y condiciones de reclamación (30 días, tope de 200 RMB)
- [Shouwu International: 英国经济 (Reino Unido económico, en chino)](http://www.sedel.com/main-route-secondary.html?groupId=2&groupItemId=3) – 10 a 12 días laborables
- [Shouwu International: 英国带电 (Reino Unido con baterías, en chino)](http://www.sedel.com/main-route-secondary.html?groupId=3&groupItemId=4) – entrega principalmente con Royal Mail
- [Shouwu International: 美国标快 (EE. UU. estándar, en chino)](http://www.sedel.com/main-route-secondary.html?groupId=1&groupItemId=1) – entrega principalmente con USPS, 6 a 8 días laborables, sin retorno a China y reclamaciones
- [Shouwu International: 美国经济 (EE. UU. económico, en chino)](http://www.sedel.com/main-route-secondary.html?groupId=2&groupItemId=21) – 8 a 12 días laborables
- [Shouwu International: 德国标快 (Alemania estándar, en chino)](http://www.sedel.com/main-route-secondary.html?groupId=1&groupItemId=19) – principalmente DHL, elección de número, 4 a 7 días laborables y reclamaciones
- [Shouwu International: 德国经济 (Alemania económico, en chino)](http://www.sedel.com/main-route-secondary.html?groupId=2&groupItemId=22) – principalmente Hermes, 4 a 7 días laborables
- [Shouwu International: 德国带电 (Alemania con baterías, en chino)](http://www.sedel.com/main-route-secondary.html?groupId=3&groupItemId=25) – principalmente DPD, 4 a 7 días laborables
- [Shouwu International: 法国标快 (Francia estándar, en chino)](http://www.sedel.com/main-route-secondary.html?groupId=1&groupItemId=20) – principalmente Colissimo, 4 a 7 días laborables
- [Shouwu International: 荷兰标快 (Países Bajos estándar, en chino)](http://www.sedel.com/main-route-secondary.html?groupId=4&groupItemId=28) – principalmente DHL, 4 a 8 días laborables
- [Shouwu International: 瑞典经济 (Suecia económico, en chino)](http://www.sedel.com/main-route-secondary.html?groupId=4&groupItemId=29) – principalmente EarlyBird, 4 a 8 días laborables
- [Shouwu International: 意大利经济 (Italia económico, en chino)](http://www.sedel.com/main-route-secondary.html?groupId=4&groupItemId=27) – UniUni y Poste Italiane, elección de número y 10 a 12 días laborables
- [Comisión Europea: EU Customs Reform (en inglés)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – arancel de 3 € desde el 1 de julio de 2026 y tasa de tramitación de 2 € desde el 1 de noviembre de 2026
- [Comisión Europea: Guidance on the 3 EUR customs duty and Union handling fee (PDF, en inglés)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – aplicación de la tasa de tramitación de 2 € por categoría de producto a cualquier valor
- [Comisión Europea: Territorial Scope (en inglés)](https://taxation-customs.ec.europa.eu/taxation/vat/vat-directive/how-does-vat-work/territorial-scope_en) – Canarias dentro del territorio aduanero de la UE y fuera del ámbito del IVA; Ceuta y Melilla fuera de ambos
- [Correos: Arancel y tasas para envíos de importación a la UE](https://www.correos.es/es/es/atencion-al-cliente/informacion-aduanera/arancel-para-envios-de-bajo-valor-en-la-ue) – aplicación en España (Península, Baleares y Canarias) y exclusión de Ceuta y Melilla
- [INCIBE: En circulación SMS fraudulentos que suplantan a Correos y otras empresas de paquetería](https://www.incibe.es/ciudadania/avisos/en-circulacion-sms-fraudulentos-que-suplantan-correos-y-otras-empresas-de) – alerta en España ante SMS de *smishing* con enlaces de falsos pagos aduaneros
- [Comisión Europea: Summertime (en inglés)](https://transport.ec.europa.eu/transport-themes/summertime_en) – horario de verano europeo entre el último domingo de marzo y el último domingo de octubre
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formato `SWECN…YQ`, rastreador y enlace, estados, textos de escaneo y etapas (incluidos los despachos de origen y destino, carga aérea, recogida por el socio, recogida y centros de FedEx, salida marítima, entrega empresarial de DHL, líneas de iMile y DPD y tránsito `arrival city` / `leave the city`), reloj de Pekín, lugares y qué muestra Peek
:::
