---
title: Seguimiento YWE (YanwenExpress): número, estados y ayuda
description: Sigue un paquete de YWE (YanwenExpress) en Estados Unidos: formato del número YWE, qué significa cada escaneo del centro a tu puerta y a quién acudir.
slug: seguimiento-ywe
published: 2026-11-18
updated: 2026-11-18
---

YWE (YanwenExpress, operado por Yanwen Express LLC) es una empresa de reparto de última milla en Estados Unidos: lleva paquetes desde sus centros logísticos estadounidenses hasta la puerta del destinatario para tiendas online, comercios y mercados electrónicos. Sus números están formados por las letras YWE y 14 dígitos: pega el tuyo en el cuadro de arriba o consúltalo en el localizador propio de YWE en [yanwenexpress.com](https://www.yanwenexpress.com/tracking.html).

## Número de seguimiento de YWE: cómo es

Los números de YWE tienen 17 caracteres en total: el prefijo YWE seguido de 14 cifras. Ejemplos con dígitos inventados:

| Número | Qué es |
| --- | --- |
| `YWE48291057364918` | YWE y 14 dígitos: un número de seguimiento de YWE. |
| `YWE4829105736491` | Solo 13 dígitos: no encaja con el formato de YWE. Comprueba si falta una cifra; si ningún transportista responde por él, su página en peektracker.com muestra «Empieza como un número de seguimiento de YWE, pero le falta un carácter: puede que tenga una errata. Compáralo con tu mensaje de envío.» |
| `YWLAX482910573649` | YW, tres letras y 12 dígitos: un número de [Yanwen](carrier:yanwen), no de YWE. |

Los espacios y las minúsculas no importan al pegar el número en Peek.

**Dónde encontrarlo.** En el correo de confirmación de envío de la tienda o dentro de la página de tu pedido. Si no das con él, consulta [cómo encontrar tu número de seguimiento](guide:find-tracking-number).

**Un enlace de seguimiento.** Un enlace al localizador de YWE terminado en `?label=` y un número YWE de 14 dígitos abre directamente ese envío bajo YWE cuando pegas el enlace completo en Peek. El buscador de YWE admite hasta 30 números de una vez (si introduces más, su web conserva los 30 primeros).

## Estados de seguimiento de YWE explicados

El localizador de YWE muestra cada escaneo con su fecha y hora, su diferencia respecto a GMT y la ciudad y el estado de EE. UU., empezando por el más reciente. Los mensajes vienen en inglés. En el orden habitual que sigue un envío en Estados Unidos:

| YWE dice | Qué significa para ti | Peek muestra |
| --- | --- | --- |
| `Shipping Order Created.` | El remitente ha registrado el envío con YWE, pero YWE aún no tiene el paquete físicamente. | «Etiqueta creada» |
| `Container picked up by YWE,LAX01 Awaiting Item.` | YWE ha recogido en Estados Unidos el contenedor aéreo en el que voló tu paquete; el paquete en sí todavía no se ha escaneado por separado. | «En tránsito» |
| `Container received` | El centro de YWE en Estados Unidos ha recibido el contenedor. | «En tránsito» |
| `Parcel received by in LAX01.` | Primer escaneo individual del paquete en el centro estadounidense de YWE. `Parcel received by NJC01.`, sin «in», es el mismo registro en otra instalación. | «En tránsito» |
| `Parcel departed from Facility.` | Ha salido del centro de clasificación. | «En tránsito» |
| `Parcel received by transit facility.`, `In processing at transit facility.`, `Parcel departed from Transit Facility.`, `In transit - departure`, `In transit to next facility.` | En un centro de tránsito intermedio en Estados Unidos, antes de llegar a la estación que reparte tu zona. | «En tránsito» |
| `Parcel received by local  Facility.` | En la estación local de reparto en Estados Unidos encargada de entregar en el área del destinatario. | «En tránsito» |
| `Parcel is out for delivery.` | En reparto hacia la dirección de entrega en Estados Unidos. | «En reparto» |
| `Parcel has been delivered.(Front door)` | Entregado en Estados Unidos. El texto entre paréntesis indica dónde se dejó el paquete. | «Entregado» |
| `Parcel delivery abnormal [Restricted Address].Parcel will be returned to the warehouse.` | La entrega en Estados Unidos falló por el motivo indicado entre corchetes; el paquete vuelve al almacén estadounidense de YWE, no al remitente. | «Intento de entrega» |
| `Parcel delivery failed [Wrong Address]. Parcel will be returned to the warehouse.` | El mismo regreso al almacén de YWE en Estados Unidos formulado como entrega fallida, desde donde el paquete puede volver a salir a reparto. | «Intento de entrega» |
| `Return to sender` | Se ha iniciado la devolución al remitente, aunque todavía no ha llegado a sus manos. | «Incidencia» |
| `Returned to sender, shipment has been received in sender warehouse.` | Devuelto y recibido en el almacén del remitente. | «Devuelto al remitente» |

**Códigos de centro, motivos y lugares de entrega.** `LAX01` y `NJC01` son códigos de instalaciones estadounidenses de YWE: en otros centros aparece su propio código en ese lugar. Del mismo modo, `[Restricted Address]` o `[Wrong Address]` son ejemplos de causas de intento fallido y `(Front door)` es un ejemplo del punto donde quedó el paquete.

**La pestaña Alert no siempre indica un problema.** El rastreador de YWE agrupa los envíos en `Awaiting`, `Transit`, `Delivered`, `Alert` y `Returned` según su último escaneo. Clasifica `Parcel received by local  Facility.` (escrito con doble espacio antes de `Facility.`), la llegada a la estación local de reparto, dentro de `Alert`, aunque se trata de un paso normal. Peek lo muestra como «En tránsito».

**Otros textos.** Cualquier escaneo que no esté en la tabla aparece en Peek tal como lo publicó YWE, y Peek deduce su etapa por las palabras cuando es posible.

**Horas.** Cada evento llega acompañado del desfase respecto a GMT del centro de YWE donde se registró. Peek interpreta ese instante exacto y convierte todas las horas a la zona horaria de tu dispositivo; en peektracker.com y en la app para iPhone, el historial termina con «Horas en tu zona horaria:» seguido de su nombre.

## Quién entrega los paquetes de YWE

**Estados Unidos.** En Estados Unidos, YWE gestiona por su cuenta la última milla mediante lo que su web denomina «Delivery Service Partner networks nationwide»: equipos locales de reparto asociados por todo el país que entregan o recogen paquetes dentro de su red. En el código de Peek, el historial de YWE va desde el centro estadounidense de YWE hasta la puerta del destinatario y no menciona a ningún otro transportista.

En su página corporativa, YWE señala que comenzó en Los Ángeles en 2024 y que prevé cubrir la mayor parte de Estados Unidos para finales de 2026.

**Intentos de entrega y recogida en Estados Unidos.** YWE no publica normas sobre cuántos intentos realiza en Estados Unidos, si deja avisos en puerta ni si dispone de puntos de recogida. Tras un intento fallido en Estados Unidos, su escaneo indica que el paquete regresa al almacén de YWE (`Parcel will be returned to the warehouse.`). Para solicitar un nuevo intento o añadir indicaciones de acceso, escribe por correo electrónico a YWE (más abajo) con el número de seguimiento, la dirección completa en Estados Unidos y las instrucciones de entrega. Consulta también [entrega fallida](guide:missed-delivery).

**España, Latinoamérica y otros países fuera de Estados Unidos.** YWE no anuncia servicio alguno fuera de Estados Unidos. Si un vendedor te facilitó un número YWE para un pedido con destino a España, México, cualquier otro país de Latinoamérica o el resto de Europa, pregunta a la tienda qué empresa realiza realmente el reparto allí.

**YWE y Yanwen.** [Yanwen](carrier:yanwen) figura en Peek como un transportista independiente, con sus propios formatos de número y su propio localizador, y la web de YWE no aclara si ambas compañías están vinculadas. Cuando el seguimiento de Yanwen nombra a `YanwenExpress` como distribuidor de un envío hacia Estados Unidos y aporta un número YWE, Peek muestra «Entrega con YWE» y consulta también el localizador de YWE. Mientras YWE solo tiene el preaviso del envío sin escaneos propios, aparece «Anunciado a YWE, aún no recibido». En cuanto YWE registra un escaneo igual o posterior al último de Yanwen, Peek pasa a seguir el paquete en YWE conservando los eventos previos de Yanwen.

## El paquete de YWE no llega o el seguimiento no avanza

**`NOT_FOUND`.** El localizador de YWE enseña este aviso cuando no tiene registros para el número consultado y detalla las causas posibles: el paquete aún no ha sido admitido ni escaneado, el número está mal escrito o el envío es tan antiguo que el seguimiento ya no se conserva. En peektracker.com, Peek muestra «Esperando a YWE» y sigue consultando. Revisa el código frente al mensaje del vendedor: YWE y 14 dígitos. Si pasan varios días sin novedades, pregunta a la tienda si llegó a enviarlo.

**Detenido en `Shipping Order Created.`** YWE tiene los datos electrónicos de la orden, pero no el paquete físico. Como YWE hace su primer escaneo al recibir la mercancía en su centro de Estados Unidos, pregunta al vendedor dónde está ahora el envío y cuándo salió. Si partió desde China hacia Estados Unidos, revisa [seguimiento de un paquete desde China](guide:tracking-from-china).

**Detenido en `Container picked up by YWE,LAX01 Awaiting Item.`** YWE ya recogió en Estados Unidos el contenedor aéreo, pero todavía no ha escaneado tu paquete individual al descargarlo.

**`Parcel delivery abnormal` o `Parcel delivery failed`.** El reparto en Estados Unidos no pudo completarse por la razón entre corchetes y el envío vuelve al almacén de YWE. Escribe por correo a YWE indicando el número de seguimiento, la dirección completa en Estados Unidos y cualquier instrucción útil para el repartidor, y avisa también al vendedor.

**`Return to sender`.** El paquete va de vuelta al remitente. Cuando aparezca `Returned to sender, shipment has been received in sender warehouse.`, el almacén del remitente ya lo ha recibido: pide a la tienda el reembolso o un reenvío. Más detalles en [devuelto al remitente](guide:returned-to-sender).

**Figura como entregado en Estados Unidos, pero no aparece.** El paréntesis del escaneo de entrega, como `(Front door)`, señala dónde se dejó. En los envíos entregados en Estados Unidos, el localizador de YWE incluye el enlace `view receipt`: introduce el código ZIP estadounidense de entrega en el campo `Recipient's postal code` para ver las fotografías del reparto cuando existan. Después escribe a YWE y comunícalo a la tienda. Consulta [entregado pero no recibido](guide:delivered-not-received).

**Paquete perdido y reclamaciones.** Según la política de privacidad del portal de clientes de YWE, el registro de cuentas está limitado a clientes empresariales (`corporate/business clients`), de modo que el cliente de YWE es el comercio o mercado electrónico que contrató el envío en Estados Unidos, no el destinatario. La web de YWE no publica ningún formulario de indemnización para destinatarios ni menciona cobros en puerta en Estados Unidos. Pide el reembolso o un nuevo envío al vendedor o a la plataforma donde compraste. Consulta [paquetes perdidos](guide:lost-parcel) y [por qué el seguimiento deja de actualizarse](guide:tracking-not-updating).

## Teléfono de contacto y atención al cliente de YWE

La web de YWE, disponible únicamente en inglés, recoge lo siguiente:

- **Correo electrónico**: aparece una dirección en el pie de todas las páginas de `yanwenexpress.com`. Al pulsarla se abre un aviso con los datos que piden enviar: tu consulta, el número de seguimiento del paquete y, en el cuerpo del mensaje, la dirección completa del destinatario, posibles instrucciones de entrega, su teléfono y correo electrónico y la gestión concreta que solicitas.
- **Teléfono**: ninguno publicado. La página de soporte (`Customer support`) dice «Calling us for further enquiries», pero no muestra ningún número ni horario de atención.
- **Preguntas frecuentes (FAQs)**: están en la página `Customer support`, redactadas para socios de reparto (`Delivery Service Partner`) y no para destinatarios que esperan una compra.

Para gestiones sobre tu pedido o devolución del dinero, acude al vendedor. Para incidencias de reparto en Estados Unidos, escribe al correo del pie de página de YWE.

## Seguir un paquete de YWE con Peek

[Peek](/) reconoce al instante un número de YWE por su estructura — YWE y 14 dígitos —, exclusiva dentro de su catálogo, y consulta directamente el localizador de YWE. También puedes pegar un enlace `yanwenexpress.com/tracking.html?label=` que lleve un número YWE. Peek no pide cuenta, código postal ni teléfono.

Peek muestra el estado actual, cada escaneo con su localidad, estado y país y la hora exacta de entrega. Descarta los escaneos repetidos y omite del resumen el nombre de quien firmó o recogió el envío cuando el evento de entrega incluye uno.

En su propia página, YWE también puede enseñar una franja estimada de dos horas mientras el paquete viaja por Estados Unidos, un mapa con la ubicación del repartidor y las fotos de entrega protegidas con el código ZIP. Peek prescinde de esos elementos y de las líneas de calle de cada escaneo, y no muestra fecha prevista de entrega.

Como el localizador de YWE no nombra a ningún otro transportista, en un envío añadido por su número YWE Peek no muestra línea de «Entrega con» y mantiene la consulta en YWE hasta la entrega. Si el servidor de YWE no responde temporalmente, Peek recurre mientras tanto a servicios de seguimiento.

Mientras tengas el paquete abierto, o el día de su reparto con las notificaciones activadas, Peek comprueba novedades hasta cada 10 minutos — y hasta cada 2 minutos desde las 6:00 de la mañana, hora local, el día en que el paquete sale a reparto — siempre que responda el localizador propio de YWE (a través de servicios de seguimiento: cada hora, cada 3 horas tras un día sin escaneos nuevos y hasta cada 15 minutos el día de reparto con el envío abierto o las alertas activadas); el resto del tiempo, y pasados dos días sin cambios (12 horas si sigue en «Etiqueta creada» o «Admitido por el transportista»), comprueba cada hora. Puedes activar avisos para «Cada escaneo», «Pasos importantes» o «Solo la entrega». Peek no puede contactar a YWE ni al vendedor en tu nombre.

## Preguntas frecuentes sobre el seguimiento de YWE

### ¿Qué es YWE (YanwenExpress)?

Una empresa de reparto de última milla en Estados Unidos gestionada por Yanwen Express LLC. Entrega pedidos de tiendas online, comercios y plataformas mediante redes de equipos locales de reparto asociados en EE. UU., y afirma haber empezado en Los Ángeles en 2024.

### ¿Es YWE lo mismo que Yanwen?

Peek los trata como dos transportistas distintos: Yanwen utiliza otros formatos de número y su propio buscador, y la web de YWE no indica si existe relación societaria entre ambos. Cuando el seguimiento de Yanwen señala a `YanwenExpress` como distribuidor en Estados Unidos con un código YWE, Peek sigue también ese número en el localizador de YWE.

### ¿Dónde puedo rastrear un paquete de YWE?

En el recuadro superior o en el localizador oficial de YWE en `yanwenexpress.com`, que permite consultar hasta 30 envíos a la vez. Introduce el código completo: YWE seguido de los 14 dígitos.

### ¿Qué significa «Container picked up by YWE»?

Significa que YWE ha recogido en Estados Unidos el contenedor aéreo en el que llegó tu envío. El código tras la coma, como `LAX01`, identifica la instalación estadounidense de YWE. El primer escaneo individual de tu paquete (`Parcel received by in LAX01.`) llega después, al abrir el contenedor. Peek muestra «En tránsito» en ambos casos.

### ¿Con quién contacto si falta un paquete de YWE?

Primero con la tienda o plataforma donde hiciste la compra, ya que ella contrató el transporte con YWE. Para consultas sobre el reparto en Estados Unidos, YWE atiende por correo electrónico en la dirección situada en el pie de su web indicando el número de seguimiento; no publica teléfono de atención.

:::sources
- [YanwenExpress: página de inicio](https://www.yanwenexpress.com/) – reparto de última milla en Estados Unidos mediante redes de Delivery Service Partners, hasta 30 envíos por consulta, correo en el pie de página y datos solicitados
- [YanwenExpress: About us](https://www.yanwenexpress.com/about.html) – inicio en Los Ángeles en 2024 y plan de cubrir la mayor parte de Estados Unidos para finales de 2026
- [YanwenExpress: Tracking](https://www.yanwenexpress.com/tracking.html) – escaneos con diferencia GMT, ciudad y estado, pestañas Awaiting, Transit, Delivered, Alert y Returned, aviso NOT_FOUND y causas posibles, ventana de entrega, mapa del conductor, «view receipt» con código postal del destinatario y límite de 30 números
- [YanwenExpress: Customer support](https://www.yanwenexpress.com/contactus.html) – mención a llamadas sin teléfono ni horario publicados y preguntas frecuentes para Delivery Service Partners
- [YanwenExpress: Join us](https://www.yanwenexpress.com/joinus.html) – incorporación de equipos de reparto en Estados Unidos a su red de última milla y recogida
- [YanwenExpress: Privacy Policy](https://customer-portal.yanwenexpress.com/agreement/Yanwen_privacy_policy) – Yanwen Express LLC y registro de cuentas reservado a clientes empresariales
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – nombres, patrón YWE de 14 dígitos, localizador y enlace `?label=`, textos de escaneo y estados normalizados, códigos de centro, clasificación en Alert, desfases GMT, datos mostrados o descartados en Peek y traspaso desde Yanwen
:::
