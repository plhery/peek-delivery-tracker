---
title: Seguimiento Blue Dart: números de guía y estados explicados
description: Sigue un envío de Blue Dart: cómo es el número de 11 cifras, qué significan estados como Shipment Further Connected y seguimiento en Peek.
slug: seguimiento-blue-dart
published: 2026-11-22
updated: 2026-11-22
---

Blue Dart es una empresa india de mensajería: el catálogo de Peek la clasifica en India y lee sus escaneos con la hora de India. Un envío de Blue Dart lleva un número de guía (*waybill*) de 11 cifras que te da el remitente. En una compra online suele aparecer en el correo de envío de la tienda o en la página del pedido. Pégalo en la casilla de arriba o introdúcelo en el buscador de bluedart.com. Como Peek no ha podido consultar la web de Blue Dart para esta página, lo que sigue procede de lo que el código de Peek conoce del seguimiento de Blue Dart.

## Número de guía Blue Dart: cómo es

Peek conoce un solo formato para Blue Dart: 11 cifras, donde la última es un dígito de control calculado a partir de las diez primeras. Con cifras inventadas:

| Número (inventado) | Qué es |
| --- | --- |
| `48291057360` | 11 cifras con dígito de control válido: la forma de una guía Blue Dart. Las redes de GLS, PostLogistics, Japan Post, Mainfreight y otros también usan 11 cifras, así que la forma por sí sola no garantiza que sea Blue Dart. |
| `48291057361` | La misma forma con dígito de control incorrecto. Peek no sugiere Blue Dart para él: revisa si hay una errata. |
| `4829105736` | 10 cifras: no es una guía Blue Dart. Si eliges Blue Dart con este número, Peek advierte que Blue Dart «no emite números como este». Lo mismo pasa con 12 cifras o con números que llevan letras. |

Las guías de 11 cifras de Aramex y el correo certificado de Japan Post usan el mismo dígito de control, así que un número válido puede pertenecerles: comprueba qué transportista nombra la tienda. Los espacios y guiones no importan al pegarlo en Peek. El buscador de Blue Dart permite consultar otras referencias, pero Peek necesita el número de guía (*waybill*). ¿No lo encuentras? Mira [cómo encontrar tu número de seguimiento](guide:find-tracking-number).

## Estados del seguimiento de Blue Dart explicados

El seguimiento de Blue Dart muestra el estado actual del envío y debajo sus escaneos, del más reciente al más antiguo, cada uno con lugar, fecha y hora. Estas son las líneas que Peek conoce del seguimiento de Blue Dart, en el orden habitual de un envío:

| Blue Dart indica | Qué significa para ti | Peek muestra |
| --- | --- | --- |
| `Pickup Employee Is Out To P/U Shipment` | Un repartidor de Blue Dart va de camino a recoger (*P/U*) el envío al remitente. Blue Dart aún no lo tiene. | «Etiqueta creada» |
| `Shipment Picked Up` | Blue Dart ha recogido el envío en el remitente. | «Admitido por el transportista» |
| `Shipment Arrived`, `Shipment Arrived At Hub` | Ha llegado a una sede o centro de clasificación de Blue Dart, cuyo nombre aparece como lugar del escaneo. | «En tránsito» |
| `Paper Work Inscan` | Escaneado en un centro, entre su llegada y la conexión siguiente. | «En tránsito» |
| `Clubbed Canvas Bag Scan` | Escaneado dentro de una saca junto con otros envíos. | «En tránsito» |
| `Shipment Further Connected` | Enviado desde esa ubicación hacia el siguiente punto del trayecto. | «En tránsito» |
| `Delivery Scheduled For Next Working Day` | En la oficina de reparto, pero pasada la hora de corte del día: saldrá en la ruta del siguiente día laborable. | «En tránsito» |
| `Shipment Out For Delivery` | En ruta de reparto. | «En reparto» |
| `Cnee Refused Id/Otp Not Shared-Incorrect` | No entregado por rechazo del destinatario (*Cnee*, *consignee*) o porque no se facilitó o fue incorrecto el documento de identidad o el código de un solo uso (*OTP*). | «Intento de entrega» |
| `Delivery Delayed`, `Delay Caused Beyond Control` | La entrega lleva retraso; el segundo texto atribuye la causa a factores ajenos a Blue Dart. | «Incidencia» |
| `Shipment Delivered` | Entregado. | «Entregado» |
| `Shipment Returned Back To Shipper` | El envío ha vuelto al remitente. Blue Dart lo pone como estado del envío al completarse la devolución. | «Devuelto al remitente» |

Una línea que Peek aún no conozca aparece tal como la escribe Blue Dart y cuenta como «En tránsito», salvo que el paquete ya esté en reparto o en una fase posterior: entonces se mantiene ese estado. Más en [qué significan los estados del seguimiento](guide:tracking-statuses).

## Entrega de Blue Dart en India: identificación y código OTP

Como Peek no ha podido consultar las normas de entrega de Blue Dart —cuántos intentos hace o cuántos días guarda un envío no entregado—, esta página no da cifras al respecto.

**Documento o código OTP.** La línea `Cnee Refused Id/Otp Not Shared-Incorrect` muestra que una entrega de Blue Dart puede requerir documento de identidad o un código de un solo uso (*OTP*). Si te enviaron un código para el envío, tenlo a mano el día de la entrega. Si aparece esta línea y no rechazaste nada, pide a la tienda que organice otra entrega. Mira [entregas fallidas](guide:missed-delivery) y [paquetes no recogidos](guide:uncollected-parcel).

¿Un mensaje sobre una entrega fallida te pide pagar o compartir un código mediante un enlace? Lee [SMS falsos de entrega](guide:delivery-scam-texts).

## Envíos Blue Dart: quién entrega y aduanas

**Una guía de Blue Dart.** Peek no conoce ningún transportista al que Blue Dart traspase sus envíos, ni en India ni fuera, y no consulta a otro transportista por una guía de Blue Dart. Si la tienda te da también el número de otro transportista, añádelo igualmente.

**Enviado con ICC Worldwide.** El seguimiento de ICC Worldwide puede nombrar a Blue Dart junto a un número de Blue Dart. Peek solo continúa el seguimiento en Blue Dart cuando ese número tiene 11 cifras: pregunta entonces a Blue Dart y, en cuanto Blue Dart muestre un escaneo al menos tan reciente como el último de ICC Worldwide, sigue el envío allí manteniendo el historial previo de ICC Worldwide. Con cualquier otro número, Peek se queda en ICC Worldwide. Añade el envío con el número de ICC Worldwide, pulsa «Cambiar» y busca ICC Worldwide: Peek no lo sugiere por el formato del número.

**Aduanas.** El código de Peek solo conoce Blue Dart en India y no ha podido comprobar en su web sus envíos al extranjero. Si un paquete está retenido en aduanas, consulta [paquetes retenidos en aduanas](guide:customs).

## Seguimiento Blue Dart sin actualizarse, retrasado o devuelto

Para un reembolso o un envío nuevo, habla con la tienda: ella contrató a Blue Dart.

**Aún sin escaneos.** Cuando no tiene datos del número, el seguimiento de Blue Dart responde `There is no information on the Waybill/Reference Number/Order Number currently.` Peek sigue comprobando. Revisa las 11 cifras en el mensaje de la tienda; si tras unos días no aparece nada, pregunta a la tienda si ya entregó el paquete.

**Parado en `Pickup Employee Is Out To P/U Shipment`.** Blue Dart todavía no ha recogido el envío del remitente. Pregunta a la tienda cuándo lo entregará.

**`Delivery Delayed` o `Delay Caused Beyond Control`.** Peek muestra «Incidencia». Si no se mueve después, contacta con la tienda.

**Días sin actualizaciones.** Cuando un envío en camino lleva cuatro días sin un nuevo escaneo, Peek muestra «Sin novedades desde hace cuatro días». Mira [por qué el seguimiento no se actualiza](guide:tracking-not-updating).

**Entregado pero no está.** Pregunta en tu domicilio o en la recepción del edificio, y después avisa a la tienda. Consulta [paquete entregado pero no recibido](guide:delivered-not-received).

**Perdido o devuelto.** Pide a la tienda un reembolso o reenvío: mira [paquetes perdidos](guide:lost-parcel). `Shipment Returned Back To Shipper` indica que volvió al remitente: mira [devuelto al remitente](guide:returned-to-sender).

## Atención al cliente y contacto de Blue Dart

Como Peek no ha podido consultar la web de Blue Dart, esta página no recoge teléfono ni correo electrónico: solo mostramos contactos leídos en las páginas del propio transportista. Si un envío queda parado, Peek enlaza la página de contacto de Blue Dart como «Pide ayuda a Blue Dart».

En un pedido online, pregunta primero a la tienda que contrató a Blue Dart. Peek no puede contactar con Blue Dart ni con la tienda por ti.

## Seguir un envío de Blue Dart con Peek

Pega la guía en [Peek](/). Como varios transportistas usan 11 cifras, Peek no muestra «Transportista detectado» por sí solo para un número de Blue Dart:

- **En India** (la región de tu móvil o el país desde el que accedes en la web), Peek muestra «Comprobando servicios de seguimiento…» mientras pregunta a los transportistas compatibles, con Blue Dart primero. Cuando Blue Dart reconoce el número, aparece «Blue Dart tiene este paquete.».
- **Fuera de India**, Peek no pregunta automáticamente a Blue Dart. Pulsa «Cambiar» y elige Blue Dart bajo «Encaja con este número».

Si ningún transportista conoce aún el número, añádelo igualmente o elige Blue Dart tú mismo: Peek sigue más de 4500 transportistas. Peek solo sugiere Blue Dart cuando el dígito de control cuadra. Si el tuyo no cuadra pero estás seguro del número, busca Blue Dart en la lista de transportistas: Peek lo consultará igual. Un enlace de seguimiento de bluedart.com con la guía (`trackNo=`) también funciona: Peek extrae el número y marca Blue Dart de inmediato («Transportista detectado»).

Asignado a Blue Dart, un envío muestra «Esperando a Blue Dart» hasta el primer escaneo; añadido sin transportista, «Todavía no hay historial de seguimiento». Peek consulta el seguimiento público de Blue Dart solo con la guía, sin cuenta: el estado que da Blue Dart y cada escaneo en las palabras de Blue Dart con su lugar, leído en hora de India. Deja fuera los datos del destinatario y no muestra fecha prevista de entrega. El enlace «Abrir la web de Blue Dart» de la página del paquete abre la guía en el seguimiento de Blue Dart.

Mientras tienes el paquete abierto en pantalla, o el día de la entrega con las notificaciones activadas, Peek consulta hasta cada 10 minutos —y hasta cada 2 minutos desde las 6:00 (hora local) el día en que Blue Dart lo escanea en reparto— mientras responda el seguimiento propio de Blue Dart; mediante servicios de seguimiento, hasta cada 15 minutos. El resto del tiempo, y tras dos días sin novedades (12 horas mientras siga en «Etiqueta creada» o «Admitido por el transportista»), consulta cada hora. Puedes recibir avisos de «Cada escaneo», «Pasos importantes» o «Solo la entrega».

## Preguntas sobre el seguimiento de Blue Dart

### ¿Cómo sigo un envío de Blue Dart?

Introduce la guía de 11 cifras en el seguimiento de bluedart.com o pégala en Peek. En India, Peek pregunta primero a Blue Dart; fuera de India, pulsa «Cambiar» y elige Blue Dart.

### ¿Cómo es un número de guía de Blue Dart?

Tiene 11 cifras y la última es un dígito de control. Aramex y otros también usan 11 cifras, así que confirma que el mensaje de la tienda nombra a Blue Dart.

### ¿Qué significa «Shipment Further Connected» en Blue Dart?

Que Blue Dart ha reenviado el envío desde una de sus instalaciones hacia la siguiente de la ruta. Peek lo muestra como «En tránsito».

### ¿Qué significa «Cnee Refused Id/Otp Not Shared-Incorrect»?

Una entrega fallida por rechazo del destinatario o por un documento de identidad o código de un solo uso (OTP) no compartido o incorrecto. Peek muestra «Intento de entrega». Si no rechazaste nada, pide a la tienda otra entrega.

### ¿Con quién contacto por un envío perdido de Blue Dart?

Con la tienda o el remitente que contrató a Blue Dart. Como Peek no pudo revisar la web de Blue Dart, esta página no publica datos de contacto de Blue Dart.

:::sources
- [Universal Parcel Scraper: Blue Dart](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/blue-dart) – India, guía de 11 cifras con dígito de control, seguimiento y respuesta de no encontrado, textos y etapas, hora de India, datos descartados, enlaces de seguimiento y página de contacto
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – otros transportistas de 11 cifras, dígito de control de Aramex y Japan Post, transportistas consultados en India y fuera, vínculo de ICC Worldwide con Blue Dart y regla de las 11 cifras, sin traspasos desde Blue Dart
:::
