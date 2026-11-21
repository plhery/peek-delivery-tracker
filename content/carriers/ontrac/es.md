---
title: Seguimiento de OnTrac: números, estados y entregas fallidas
description: Rastrea un paquete de OnTrac o LaserShip en EE. UU.: formatos C, D, LX y 1LS, significado de cada estado, intentos fallidos, retrasos y chat con Wink.
slug: seguimiento-ontrac
published: 2026-11-21
updated: 2026-11-21
---

OnTrac es un operador estadounidense de última milla que reparte pedidos online para comercios y marcas en `35` estados de EE. UU. y Washington, D.C. (`70 %` de la población estadounidense), e integra a LaserShip desde su fusión en 2021. Puedes seguir un envío en [ontrac.com/tracking](https://www.ontrac.com/tracking/) con el número de seguimiento o pegarlo directamente en el cuadro superior.

## Formato del número de seguimiento de OnTrac y dónde encontrarlo

El código aparece en la página de tu pedido o en el correo de confirmación de envío de la tienda (el número de pedido no sirve). Según las FAQ de OnTrac, sus números empiezan por `C`, `D`, `1LS`, `LS`, `LX` o `BN` ([encontrar un número de seguimiento](guide:find-tracking-number)). Estas son las formas que reconoce Peek, con dígitos inventados:

| Forma (inventada) | Qué es |
| --- | --- |
| `C14829105736497` | `C1` y 13 dígitos terminados en dígito de control (15 caracteres): detectado al instante como OnTrac. |
| `D10482910573645` | `D10` y 12 dígitos con dígito de control (15 caracteres): detectado al instante como OnTrac (`C24829105736495` y otros `C`/`D` válidos de 14 cifras se comprueban con OnTrac). |
| `LX48291057` | Formato LaserShip: `L`, seguida de `A`, `E`, `H`, `I`, `N` o `X`, y 8 dígitos (10 caracteres). |
| `1LS482910573649` | Formato LaserShip: `1LS` y de 12 a 14 letras o números. |
| `AD482910573` | Referencia del remitente (`AD` + 9 dígitos), que OnTrac convierte a su propio código al elegir OnTrac. |

La antigua web `lasership.com` redirige a `ontrac.com`, donde se consultan todos los envíos de LaserShip. Para prefijos `LS` o `BN` (u otros formatos distintos), usa directamente `ontrac.com/tracking/`, ya que al elegir OnTrac manualmente en Peek mostrará que «no emite números como este». Un número recién creado puede no mostrar movimientos al principio: OnTrac pide esperar de **1 a 3 días** tras el correo de envío, pues algunos remitentes tardan hasta 24 horas en enviar sus datos.

## Qué significan los estados de seguimiento de OnTrac

La web de OnTrac está solo en inglés. Estos son los estados y avisos de demora que lee Peek:

| Estado de OnTrac | Qué significa para tu paquete | Peek muestra |
| --- | --- | --- |
| `On its way to OnTrac` | OnTrac recibió los datos de la etiqueta, pero aún no el paquete (`LABEL CREATED, TRACKING DETAILS WILL BE AVAILABLE ONCE THE PACKAGE ARRIVES AT THE ONTRAC FACILITY` o `Waiting for package from shipper.`). | «Etiqueta creada» |
| `Origin Scan` | OnTrac recibió el bulto y viaja hacia tu centro local (los pasos intermedios muestran `Arrived at Facility`). | «En tránsito» |
| `Arrived at Final Servicing Facility` | En la instalación local que reparte en tu zona. | «En tránsito» |
| `Loaded onto vehicle` | Cargado en un vehículo de ruta o reparto. | «En tránsito» |
| `Out for Delivery` | Hoy en el vehículo del repartidor. | «En reparto» |
| `Package Delivered` | Entregado. | «Entregado» |
| `Delivery Pending` | El conductor intentó entregarlo sin dejarlo (por seguridad o firma); OnTrac volverá a intentarlo. | «Intento de entrega» |
| `Business closed. Please provide hours` | Negocio cerrado; OnTrac reintenta hasta 3 veces y solicita tu horario comercial. | «Intento de entrega» |
| `Incomplete address. Please contact us` | Dirección incompleta al repartir; facilita los datos o puede devolverse. | «Intento de entrega» |
| `Details needed. Please contact us` | Paquete retenido a la espera de detalles de dirección o acceso. | «Incidencia» |
| `Order has been transferred to another carrier` | Entregado a otro transportista cuyo nombre OnTrac no indica (aunque el titular en `ontrac.com` diga *Delivered*). | «En tránsito» |
| `Delivery Delay`, `Delayed. Delivery date updated`, `Delayed by road closure`, `Delayed. Could not reach delivery area` | Retraso operativo, carretera cortada o zona inaccesible, con nueva fecha de entrega. | «En tránsito» |
| `Weather delay. Delivery date updated`, `Weather delay. See service alerts` | Demora meteorológica en la red de OnTrac. | «En tránsito» |
| `Misrouted. Delivery date updated` | Error de clasificación corregido con fecha actualizada. | «En tránsito» |

Más información en [qué significan los estados de seguimiento](guide:tracking-statuses).

## Horarios de reparto de OnTrac y entregas fallidas

**Horario.** OnTrac suele entregar en domicilios entre las **`08:00` y las `21:00`** (hora local) del día previsto, y en determinadas zonas también reparte **sábados y domingos** en esa misma franja (límites estándar en sus condiciones adicionales: peso facturable hasta `50 lbs`, largo `48 in`, segundo lado `30 in` y `8.640 pulgadas cúbicas`).

**Entrega fallida.** OnTrac realiza hasta **3 intentos de entrega** (el siguiente en **1 a 2 días hábiles**, nunca en el mismo día) y después devuelve o gestiona el paquete según sus normas de envíos no entregables ([entrega fallida](guide:missed-delivery)).

**Instrucciones de acceso y cambio de dirección.** Si figura un intento cuando estabas en casa, revisa la dirección en la tienda y envía instrucciones o códigos de acceso al asistente virtual **Wink** con tu número de seguimiento (si ya está en reparto, suelen aplicarse en el siguiente intento). Cualquier **cambio de dirección** requiere autorización previa de la tienda ([cambiar la dirección de entrega](guide:change-delivery-address)).

**Recogida en centro y alertas SMS.** La web de OnTrac no menciona puntos de recogida ni taquillas propias, y OnTrac solo retiene un paquete en su nave local si lo piden remitente o destinatario (lleva número de seguimiento y documento con foto). En `ontrac.com/tracking/` puedes activar `Get Text Alerts` (`NotifyMe`) para recibir SMS cuando salga a reparto, haya un intento o se entregue (baja enviando `STOP` al `(469) 529-1133`), además de ver la foto de entrega (`VPOD`).

## Paquete de OnTrac retrasado, sin actualizar o no recibido

- **Sin novedades:** si pasan **3 días hábiles** sin cambios, OnTrac pide contactar primero con el comercio y después abrir ticket con **Wink** ([por qué no se actualiza el seguimiento](guide:tracking-not-updating)).
- **Figura entregado pero no aparece:** comprueba la dirección y la foto de entrega en `ontrac.com/tracking/` (o consulta en administración si fue a una taquilla comunitaria), elige `Tracking shows delivered; no package` bajo `Have a question?` o abre un caso con **Wink**. Recibirás un número de caso por correo con respuesta en hasta **48 horas**; si pasan más de **2 días hábiles** sin noticias, acude al vendedor ([entregado pero no recibido](guide:delivered-not-received)).
- **Dañado o perdido:** según las condiciones de OnTrac, las reclamaciones formales las presenta el remitente (`cargoclaims@ontrac.com`); pide sustitución o reembolso a la tienda ([paquete dañado](guide:damaged-parcel), [paquete perdido](guide:lost-parcel)).

## Atención al cliente de OnTrac: chat, SMS y horario

En sus páginas de [Soporte](https://www.ontrac.com/support/) y [FAQ](https://www.ontrac.com/faq/), OnTrac no publica teléfono de voz ni correo para destinatarios; ofrece estos canales:

- **Chat con Wink:** icono rojo abajo a la derecha en `ontrac.com` (requiere un número de seguimiento válido).
- **Línea de SMS:** envía `start` por SMS al **`703-348-8441`** (solo texto para rastrear o abrir caso; tarifas habituales de mensajería; `STOP` para cancelar).
- **Formulario web:** sección `Have a question?` bajo el resultado en `ontrac.com/tracking/`.
- **Horario de atención:** lunes a viernes de `08:00` a `21:00` (hora del Este, *Eastern Time*) y sábados y domingos de `08:00` a `20:00` (*Eastern Time*); cerrado Navidad, 26 de diciembre y Año Nuevo.

Ante mensajes sospechosos, revisa nuestra guía de [mensajes falsos de entrega](guide:delivery-scam-texts).

## Seguir paquetes de OnTrac con Peek

[Peek](/) identifica a OnTrac de inmediato («Transportista detectado») con números `C1` o `D10` que superan el dígito de control de OnTrac, con códigos LaserShip `LX` y `1LS` y con enlaces de `ontrac.com` que incluyan `number=`. Con otros `C` o `D` válidos de 14 cifras muestra «Comprobando servicios de seguimiento…» y «OnTrac tiene este paquete.» cuando recibe escaneos. Para una referencia `AD` de 9 cifras, pulsa «Cambiar» y elige OnTrac: OnTrac encuentra el paquete y Peek muestra también su número de OnTrac («OnTrac lo sigue con el número») (antes del primer escaneo verás «Esperando a OnTrac»).

Peek lee el servicio JSON de OnTrac y muestra el estado, cada escaneo con ciudad y estado en el mapa (hasta los 100 últimos), fecha estimada, hora de entrega, peso y dimensiones, omitiendo destinatario, firma y foto de entrega. Cuando el evento incluye desplazamiento horario, convierte el instante a tu zona («Horas en tu zona horaria: …»); si viene sin huso, Peek la lee en la hora del estado del escaneo y la convierte igual. `Order has been transferred to another carrier` permanece «En tránsito» en Peek, sin traspaso posterior al no indicar OnTrac qué operador continúa.

Con el paquete abierto o el día de entrega con notificaciones activas, Peek comprueba hasta cada 10 minutos, y hasta cada 2 minutos desde las `06:00` hora local el día del escaneo `Out for Delivery` mientras responda OnTrac; el resto del tiempo, y tras 12 h en «Etiqueta creada» / «Admitido por el transportista» o dos días sin cambios, cada hora («Cada escaneo», «Pasos importantes» o «Solo la entrega»). Peek no puede contactar con OnTrac ni con la tienda por ti.

## Preguntas frecuentes sobre el seguimiento de OnTrac

### ¿Por qué letras empiezan los números de seguimiento de OnTrac?

Por `C`, `D`, `1LS`, `LS`, `LX` o `BN`, según OnTrac. Los códigos `C` y `D` que valida Peek tienen 15 caracteres (letra más 14 dígitos).

### ¿Por qué no se actualiza mi seguimiento de OnTrac?

Deja pasar entre 1 y 3 días tras el correo de envío para que aparezcan las primeras actualizaciones. Si un envío en marcha lleva 3 días hábiles sin cambios, contacta con la tienda y después con Wink.

### ¿A qué hora reparte OnTrac?

Entre las `08:00` y las `21:00` de tu zona horaria local en la fecha prevista, incluidos sábados y domingos en áreas con reparto de fin de semana.

### ¿Tiene OnTrac seguimiento en tiempo real?

Muestra cada escaneo con fecha, hora y ciudad (además de foto de entrega cuando consta), y `NotifyMe` envía alertas SMS al salir a reparto, tras un intento y al entregarse.

### ¿Es fiable OnTrac?

Sí: nació en 1991 como *California Overnight*, se fusionó en 2021 con LaserShip (fundada en 1986), opera más de `102` centros y `18` plantas de clasificación en EE. UU. y es transportista preferente para vendedores de Amazon MFN.

:::sources
- [OnTrac: FAQ](https://www.ontrac.com/faq/) – prefijos, horario 08:00–21:00 y fines de semana, reintentos en 1–2 días hábiles, Wink, línea SMS 703-348-8441, 102+ sedes y 18 centros de clasificación
- [OnTrac: Support](https://www.ontrac.com/support/) – margen de 1 a 3 días, investigación en 48 horas, NotifyMe y horarios y cierres (incluido el 26 de diciembre)
- [OnTrac: Tracking](https://www.ontrac.com/tracking/) – buscador de envíos, enlace a foto de entrega y formulario Have a question?
- [OnTrac: Script de seguimiento](https://www.ontrac.com/wp-content/themes/ontrac/assets/js/ontrac-package-status.js) – titulares de estado, columnas y motivos de consulta
- [OnTrac: Códigos de estado](https://www.ontrac.com/wp-content/themes/ontrac/assets/data/statusCodes.json) – significado de intentos, retenciones, traspasos y demoras
- [OnTrac: Additional Terms & Conditions](https://www.ontrac.com/terms-conditions-additional/) – 3 intentos, límites de 50 lbs / 48 in y reclamaciones a cargo del remitente
- [OnTrac: About OnTrac](https://www.ontrac.com/about/) – fundación de LaserShip en 1986 y OnTrac en 1991, fusión en 2021 y servicio transcontinental
- [OnTrac: Residential E-Commerce Delivery](https://www.ontrac.com/residential-e-commerce-delivery/) – 35 estados y D.C., alcance del 70 % de EE. UU. y prueba visual VPOD
- [OnTrac: Amazon MFN Sellers](https://www.ontrac.com/amazon-mfn-sellers/) – transportista preferente para vendedores Amazon MFN
- [OnTrac: NotifyMe Terms of Use](https://www.ontrac.com/notifyme-terms-of-use/) – alertas SMS y baja con STOP al (469) 529-1133
- [Universal Parcel Scraper: OnTrac](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/ontrac) – esquemas C, D, LX, 1LS y referencia AD, 18 textos de estado, relojes, peso/dimensiones y traspaso NFRP
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – detección y respaldo con servicios de seguimiento
:::
