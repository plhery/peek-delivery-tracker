---
title: Seguimiento DAI Post: números, estados y ayuda de entrega
description: Sigue un paquete de DAI Post (Parcel Right) en Australia, Nueva Zelanda o Singapur: sus números, estados, autorización para dejar sin firma y contactos.
slug: seguimiento-dai-post
published: 2026-11-23
updated: 2026-11-23
---

DAI Post es una empresa australiana de reparto para tiendas online, fundada en Melbourne en 2000. Lleva paquetes desde Estados Unidos, Reino Unido, Europa y Asia hasta compradores de Australia, Nueva Zelanda y Singapur. Su propia entrega de última milla se llama Parcel Right. Sigue un paquete en la [página de seguimiento de DAI Post](https://daipost.com/track) o pega el número en la casilla de arriba. El número aparece en la confirmación del pedido de la tienda; si no lo encuentras, pregúntale a la tienda.

## Número de seguimiento de DAI Post: cómo es

DAI Post rastrea los números que las tiendas imprimen en sus paquetes, por lo que pueden tener varios formatos. Su rastreador admite hasta 10 a la vez. Los formatos que conoce el código de Peek, con cifras inventadas:

| Número (inventado) | Qué es |
| --- | --- |
| `NZC482910375` | `NZC` y nueve dígitos: en el código de Peek, el número de DAI Post para paquetes hacia Nueva Zelanda. Peek identifica DAI Post al instante. |
| `00393580944829103754` | 20 dígitos que empiezan por `0039358094`, con el último como dígito de control: un código de barras SSCC de etiqueta, a veces impreso como `(00) 3 9358094 482910375 4`. Otros transportistas también usan 20 dígitos, por lo que Peek no puede asegurarlo solo por la forma. |
| `KPR48291037561` | Referencia propia de una tienda, letras y dígitos. Peek no la reconoce por su forma. |

Espacios, paréntesis y minúsculas no importan. Si falta o cambia un dígito en un número de 20 dígitos, el dígito de control falla y Peek no lo vincula a DAI Post: revisa el número. Más en [cómo encontrar un número de seguimiento](guide:find-tracking-number) y [formatos de números de seguimiento](guide:tracking-number-formats).

## Estados del seguimiento de DAI Post explicados

El rastreador de DAI Post muestra una frase cercana para cada paso. Algunos paquetes muestran una versión más breve del mismo paso, como `Checked in at origin hub`. Peek lee el código de cada etapa, así que ambas versiones equivalen a lo mismo. Las líneas que Peek conoce, en orden:

| DAI Post dice | Qué significa | Peek muestra |
| --- | --- | --- |
| `We’ve received your delivery details. We’ll let you know as soon as your parcel is in our hands.` o `Customer label information received` | La tienda envió tus datos; DAI Post aún no tiene el paquete. | «Etiqueta creada» |
| `Your parcel’s checked in and being prepared for its journey.` o `Checked in at origin hub` | El centro de origen de DAI Post ya tiene el paquete. | «Admitido por el transportista» |
| `We’ve processed your parcel at our origin facility. Next, on the move to you!` o `Parcel processed at origin facility` | Clasificado y listo para salir. | «En tránsito» |
| `There’s a departure delay. Thanks for your patience, we’ll update you soon.` o `There’s a transit delay. Thanks for your patience, we’ll update you soon.` | El vuelo sale con retraso o hay demora en tránsito. | «En tránsito» |
| `Your parcel is in the air and is heading your way.` o `Parcel departed origin country` | En vuelo desde el país de origen. | «En tránsito» |
| `Parcel arrived in trans-shipment country.`, luego `Your parcel’s left the transfer country and is back on the move.` | Escala en un país de tránsito. | «En tránsito» |
| `Touchdown! Your parcel has landed.` o `Parcel arrived in destination country` | Llegó a tu país. | «En tránsito» |
| `We’ve collected your parcel from the airline and it’s being processed.` o `Collected from the airline for processing` | DAI Post lo recogió de la aerolínea. | «En tránsito» |
| `Your parcel is being checked by customs. We’ll let you know when it’s cleared.` o `Parcel processed by clearance facility` | Aduanas lo está revisando. | «En aduanas» |
| `Customs clearance delay` | Aduanas sigue reteniéndolo. | «En aduanas» |
| `All clear! Your parcel’s passed customs and is ready for the next leg.` o `Customs cleared` | Aduanas lo ha liberado. | «En tránsito» |
| `We’ve got your parcel and it’s been sorted for delivery.` o `Parcel received and sorted` | Clasificado para reparto. | «En tránsito» |
| `Your parcel’s on an interstate journey. We’ll keep you updated as it travels.` | Viajando entre estados. | «En tránsito» |
| `Your parcel’s arrived at our local facility, it's on the way.` | En un depósito cercano a ti. | «En tránsito» |
| `Your parcel’s on its way to you, almost there!` | De camino al depósito de reparto, aún no en ruta de entrega. | «En tránsito» |
| `It’s happening, your parcel will be delivered today. Get ready!` o `Parcel out for final mile carrier delivery` | En reparto hoy. | «En reparto» |
| `Parcel unsafe to leave at address` | El conductor no lo dejó en la dirección (ver abajo). | «Intento de entrega» |
| `Parcel available for collection` | Esperando en un punto de recogida. | «Listo para recoger» |
| `Delivered! Your parcel has arrived. Enjoy!` | Entregado. | «Entregado» |
| `Delivered, left where you can find it.` o `Parcel delivered - authority to leave` | Dejado en tu dirección sin firma. | «Entregado» |

Para un número que no conoce, el rastreador de DAI Post dice `Sorry, we couldn't find your parcel.` Más en [qué significan los estados de seguimiento](guide:tracking-statuses).

**Horas.** DAI Post registra cada hora en el reloj local del lugar donde se escaneó, sin zona horaria. Peek calcula la zona a partir del lugar y muestra las horas en la zona horaria de tu dispositivo; la hora de la primera línea puede variar.

## Entrega de DAI Post: autorización para dejar, recogida y devoluciones

DAI Post asegura que su red cubre al 100 % de la población australiana. La hora de llegada depende de la ruta del conductor, que cambia según los envíos del día y el tráfico.

- **Autorización para dejar (*Authority to leave*).** No requiere firma. Si nadie responde y el conductor considera el lugar seguro, deja el paquete.
- **No es seguro dejarlo.** Si quedaría demasiado expuesto, va al punto de recogida más cercano en tu zona y DAI Post te envía instrucciones. En el rastreador de DAI Post, la línea `Parcel available for collection` enlaza a un mapa del lugar; Peek muestra solo la localidad.
- **7 días para recogerlo.** Un paquete no recogido en 7 días regresa al depósito de DAI Post y después a la tienda. Igual ocurre si faltan datos de entrega.
- **Mensajes.** Solo recibes avisos de los hitos principales si la tienda dio a DAI Post tu correo electrónico y tu teléfono móvil.

Las condiciones de DAI Post añaden que, si no estás, el paquete puede dejarse en un lugar seguro o llevarse a un *Collection Point*, considerándose entonces entregado. Sus páginas no aclaran quién realiza la entrega final en Nueva Zelanda o Singapur. Ver [entrega fallida](guide:missed-delivery) y [paquetes no recogidos](guide:uncollected-parcel).

## Aduanas en un paquete de DAI Post

**Retenido en aduanas (*Customs held*).** La página de ayuda de DAI Post explica que un evento *Customs held* significa que ABF o DAFF —las agencias australianas de fronteras y bioseguridad— probablemente necesiten más información antes de liberarlo. Pregunta a atención al cliente de DAI Post qué se necesita: su formulario tiene el motivo «My parcel is Customs held.»

**Cargos.** Las condiciones de DAI Post responsabilizan al remitente de los costes del envío, incluidos impuestos y aranceles. Si rechazas pagar un cargo en la entrega, el remitente sigue debiéndolo y DAI Post puede retener el paquete hasta el pago o devolverlo al remitente. Lo que te corresponda pagar depende de la normativa de tu país: ver [paquetes retenidos en aduanas](guide:customs). ¿Un mensaje sobre tasas parece sospechoso? Ver [SMS falsos de entrega](guide:delivery-scam-texts).

## Seguimiento de DAI Post sin actualizar, perdido o devuelto

**Sin novedades durante días.** DAI Post solo escanea en puntos clave e indica que el paquete sigue avanzando entre ellos. En entregas interestatales o regionales puede parecer quieto varios días. Si el paquete pasa cuatro días sin una línea nueva en «Etiqueta creada», «Admitido por el transportista» o «En tránsito», Peek muestra «Sin novedades desde el …» con el día de esa línea y un enlace «Pide ayuda a DAI Post». Ver [por qué el seguimiento no se actualiza](guide:tracking-not-updating).

**Sigue en «Etiqueta creada».** DAI Post todavía no tiene el paquete. Si permanece así varios días, pregunta a la tienda si ya lo despachó.

**Entregado, pero no está.** Con entrega sin firma, revisa primero alrededor de tu casa. DAI Post puede enviarte por correo la prueba de entrega *Authority to leave*: pídela por su formulario y luego contacta a la tienda. Ver [entregado pero no recibido](guide:delivered-not-received).

**Perdido o devuelto.** Si nunca llegó, usa el formulario de DAI Post con el motivo «I did not receive my parcel.» En cualquier caso, fue la tienda quien contrató el envío: pídele un reembolso o un nuevo paquete. Ver [paquetes perdidos](guide:lost-parcel) y [devuelto al remitente](guide:returned-to-sender).

## Atención al cliente y contacto de DAI Post

Canales de contacto de DAI Post:

- **Teléfono:** Australia +61 3 8586 1200, Nueva Zelanda +64 800 451 718, Singapur +65 800 321 1587. No se indican horarios de atención.
- **Correo electrónico:** consumercare@daipost.com.
- **Online:** el [formulario de consulta](https://daipost.com/contact). Pide tu nombre, teléfono, correo electrónico, número de seguimiento y el motivo de la consulta.

## Seguir un paquete de DAI Post con Peek

[Peek](/) sigue un paquete de DAI Post solo con el número: sin cuenta ni código postal.

- **Un número `NZC`.** Peek identifica DAI Post al momento («Transportista detectado»). Si dos días después de su primera respuesta DAI Post sigue sin conocer el número, Peek te pide que lo revises, elijas otro transportista o borres el paquete.
- **Un número de 20 dígitos que empieza por `0039358094`.** Pégalo igualmente: Peek sigue a más de 4.500 transportistas. Mientras consulta verás «Comprobando servicios de seguimiento…», y DAI Post es uno de los primeros consultados. Si DAI Post lo tiene, Peek lo nombra. Si todavía no, el paquete muestra «Esperando el primer escaneo de DAI Post» y la tarjeta indica «Probablemente DAI Post»; pasada una semana, Peek sugiere revisar el número con el remitente. Para elegir DAI Post tú mismo, pulsa «Cambiar»: aparece en «Encaja con este número».
- **Cualquier otro número.** Pégalo igual, pulsa «Cambiar» y elige DAI Post (busca DAI Post o Parcel Right). En un paquete ya añadido, usa «Cambiar transportista».
- **Un enlace.** Pega un enlace de seguimiento de daipost.com, parcelright.com.au o parcelrig.ht: Peek toma el número e identifica DAI Post enseguida.

Peek muestra el estado, cada línea en las palabras de DAI Post, la ciudad y país de cada escaneo, la fecha prevista de entrega cuando DAI Post la proporciona y la hora de entrega. No muestra punto de recogida, nombre de servicio, peso ni prueba de entrega. «Abrir la web de DAI Post» abre el número en el rastreador oficial. En el código de Peek, algunos envíos de [Asendia](carrier:asendia), y algunos de [ePost Global](carrier:epost-global) hacia Nueva Zelanda, pasan a DAI Post: su seguimiento incluye sus escaneos.

Mientras el paquete está abierto en Peek, o el día de su entrega con las notificaciones activadas, Peek lo comprueba hasta cada 10 minutos, y cada 2 minutos desde las 6:00 hora local el día en que DAI Post escanea el paquete en reparto. Tras dos días sin novedades (12 horas mientras siga en «Etiqueta creada» o «Admitido por el transportista»), comprueba cada hora, y cada 6 horas tras siete días sin novedades en «Etiqueta creada». Las notificaciones pueden seguir «Cada escaneo», «Pasos importantes» o «Solo la entrega». Peek no puede contactar a DAI Post ni a la tienda por ti.

## Preguntas sobre el seguimiento de DAI Post

### ¿Es Parcel Right lo mismo que DAI Post?

Parcel Right es el servicio propio de última milla de DAI Post. En Peek puedes buscar cualquiera de los dos nombres.

### ¿Qué significa «Parcel unsafe to leave at address»?

El conductor no encontró a nadie y el lugar no era seguro. El paquete pasa al punto de recogida más cercano: recógelo en 7 días o volverá a la tienda.

### ¿Cuánto tarda DAI Post en entregar?

Las condiciones de DAI Post indican que los plazos estándar son estimaciones, no garantías. Su rastreador puede mostrar una fecha prevista de entrega, y Peek también la muestra.

:::sources
- [DAI Post: Home](https://daipost.com/) – Melbourne, 2000, paquetes desde EE. UU., Reino Unido, Europa y Asia hacia Australia, Nueva Zelanda y Singapur, Parcel Right
- [DAI Post: Help and support](https://daipost.com/help-and-support) – teléfonos, cobertura, plazos, sin actualizaciones recientes, notificaciones, autorización para dejar, recogida, devoluciones, aduanas, prueba de entrega
- [DAI Post: Delivery tracking](https://daipost.com/track) – el rastreador, hasta 10 números
- [DAI Post: Contact](https://daipost.com/contact) – formulario de consulta y sus motivos, teléfonos, consumercare@daipost.com
- [DAI Post: Terms & conditions](https://daipost.com/terms) – lugar seguro o Collection Point, tiempos estimados, cargos
- [Universal Parcel Scraper: DAI Post](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/dai-post) – nombres, formatos de número, enlaces, textos y etapas, horarios, entrega estimada
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – detección, copias de escaneos de DAI Post en Asendia y ePost Global
:::
