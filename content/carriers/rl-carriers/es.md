---
title: Seguimiento R+L Carriers: número PRO, estados y reclamos
description: Sigue un envío de carga de R+L Carriers en EE. UU.: cómo es el número PRO, qué significa cada estado y qué hacer si la mercancía se retrasa o sufre daños.
slug: seguimiento-rl-carriers
published: 2026-11-18
updated: 2026-11-21
---

R+L Carriers es un transportista de carga por carretera en los **Estados Unidos**. En el código de Peek, recoge la mercancía en las instalaciones del remitente, la traslada de terminal en terminal y la entrega desde la terminal de destino en EE. UU. Si esperas en Estados Unidos un pedido voluminoso u otra carga enviada con R+L, la rastreas mediante su número PRO, que te facilita la tienda o la empresa que contrató el porte: pégalo en el cuadro superior. El buscador propio de R+L es la página *Shipment Tracing* en rlcarriers.com: admite un número PRO o un identificador de solicitud de recogida (*Pickup Request ID*), hasta 25 números a la vez, y también otras referencias si inicias sesión en una cuenta MyRLC.

## Número de seguimiento de R+L Carriers: el número PRO

El localizador de R+L consulta los envíos por su número PRO. Peek reconoce estos formatos, mostrados aquí con cifras inventadas:

| Número (inventado) | Qué es |
| --- | --- |
| `48291057-3` | Nueve dígitos, el último de ellos un dígito de control escrito aquí tras un guion. |
| `482910573` | El mismo número PRO escrito sin guion. |
| `RL482910-5` | Dos letras y siete dígitos, siendo el último un dígito de control. También puede escribirse sin guion (`RL4829105`). |
| `I482910573` | Un número PRO tal como lo muestra el buscador de R+L, con una `I` delante. Esa `I` no forma parte del número: Peek lo acepta con o sin ella. |

El rastreador de R+L comprueba el último dígito, de modo que detecta errores al teclearlo (véase más abajo). Peek elimina espacios y guiones antes de consultar a R+L. En su catálogo también reconoce a R+L Carriers como R&L Carriers, R and L Carriers y RL Carriers.

**Dónde encontrarlo.** En el correo de confirmación de envío de la tienda o en la página de tu pedido; cuando se trata de carga entre empresas, pídelo a la compañía remitente. Consulta [cómo encontrar tu número de seguimiento](guide:find-tracking-number).

**Otros transportistas con el mismo formato.** Nueve dígitos solos también encajan con [TNT](carrier:tnt), TForce Freight y XPO, mientras que una `I` seguida de nueve dígitos coincide con Australia Post. Si tu aviso de salida nombra a alguno de ellos, síguelo allí. Más detalles en [formatos de números de seguimiento](guide:tracking-number-formats).

## Qué significan los estados de seguimiento de R+L Carriers

El localizador de R+L muestra un resumen del envío encima del historial y debajo detalla cada línea con fecha y hora. Identifica las terminales estadounidenses por ciudad y código de estado. Sus líneas no llevan código numérico: Peek interpreta su redacción en inglés. En el orden habitual que sigue una carga en **Estados Unidos** (los puntos suspensivos representan una ciudad y estado, o una terminal):

| R+L indica | Qué significa para ti | Peek muestra |
| --- | --- | --- |
| `Picked Up in …` | R+L ha recogido la carga en origen, en la localidad del remitente. Peek conserva el texto íntegro sin tomar la ciudad del remitente como ubicación del escaneo. | «Admitido por el transportista» |
| `Arrived at origin terminal` | La mercancía ha llegado a la terminal de R+L en la zona de origen. | «En tránsito» |
| `Departed … in route to …` | Ha salido de una terminal rumbo a la siguiente o hacia la terminal de destino. | «En tránsito» |
| `Arrived at …` | Ha llegado a una terminal intermedia de la ruta, identificada por su ciudad y estado. | «En tránsito» |
| `Arrived at destination terminal` | Ya está en la terminal correspondiente a tu zona, última parada antes del reparto. | «En tránsito» |
| `Shipment Delayed` | Alguna incidencia ha retenido el envío. El resumen de R+L pasa a calificarlo como retrasado (*Delayed*) y mantiene un día estimado de entrega. | «En tránsito» |
| `Delivered` | Entregado. | «Entregado» |

**El resumen superior.** Por encima de las líneas de historial, el buscador de R+L resume el envío como *In Transit*, *Delayed* o *Delivered*. Cuando ese resumen va un paso por delante de las líneas detalladas, Peek prioriza el resumen: un envío resumido como entregado muestra «Entregado» aunque la última línea todavía no lo refleje. Cuando el buscador devuelve resumen pero aún ninguna línea de historial, Peek muestra únicamente el estado.

El código de Peek no contempla textos específicos de R+L para salida a reparto, intento fallido, incidencia o devolución. Cualquier otra redacción se clasifica con las reglas generales que Peek aplica a todos los transportistas. Una línea que ninguna regla reconozca sigue mostrándose tal cual y cuenta como movimiento de la carga («En tránsito») antes de la entrega; una vez entregada, ya no altera el estado. Más información en [estados de seguimiento explicados](guide:tracking-statuses).

## Cómo entrega R+L Carriers en Estados Unidos

Mientras la carga viaja por **Estados Unidos**, el localizador de R+L ofrece un día estimado de entrega, que Peek muestra hasta que se completa el reparto. El rastreador de R+L no menciona a ningún otro transportista para la última milla y el código de Peek no traspasa el envío a ningún tercero. El seguimiento aquí descrito no cubre envíos internos fuera de Estados Unidos.

Esta página no detalla franjas horarias, citas de entrega, número de intentos ni normas para recoger mercancía en una terminal estadounidense. Pregunta a la tienda o a la empresa que contrató el transporte cómo y cuándo llegará a tu puerta: ellas son titulares de la reserva con R+L.

## Envío de R+L Carriers no encontrado, retrasado, dañado o perdido

**No encontrado.** Ante un número PRO que no figura en su base, el buscador de R+L responde `No records found for Number:` seguido del PRO con una `I` delante. Peek muestra entonces «Esperando a R+L Carriers» cuando el PRO está asignado a R+L Carriers y vuelve a consultarlo más adelante. Revisa dígito por dígito el correo de la tienda; si el número coincide, pregunta al remitente si R+L ya recogió la mercancía.

**Dígito incorrecto.** Si el dígito de control del PRO no cuadra, el buscador de R+L contesta con el número PRO seguido de `Pro number failed check digit validation.` Peek muestra en ese caso «Revisa el número de seguimiento». Contrasta cada cifra con el correo del comercio o la documentación de embarque.

**Retrasado o sin actualizaciones.** Tras `Shipment Delayed`, Peek mantiene el envío «En tránsito» junto con el día previsto por R+L. Cuando una carga en «Admitido por el transportista» o «En tránsito» acumula cuatro días sin líneas nuevas, Peek muestra un aviso con el día de la última línea («Sin novedades desde el lun 12 oct»). Consulta a la tienda o al remitente. Lee también [por qué el seguimiento deja de actualizarse](guide:tracking-not-updating).

**Carga dañada, incompleta o perdida en EE. UU.** Avisa primero al comercio o a la empresa remitente que contrató el porte. Para cargas transportadas por carretera **entre estados de EE. UU.**, la legislación federal de los **Estados Unidos** (`49 U.S.C. 14706` y `49 CFR Part 370`) establece estas reglas:

- El transportista responde de la pérdida o daño real sufrido por la mercancía frente a la persona legitimada según el recibo o conocimiento de embarque (*bill of lading*). Su responsabilidad puede limitarse a un valor declarado por escrito o electrónicamente por el remitente, o pactado por escrito entre este y el transportista.
- El transportista no puede fijar un plazo inferior a 9 meses para presentar una reclamación ni inferior a 2 años para acudir a los tribunales, contados desde el día en que comunique por escrito que rechaza cualquier parte de la reclamación. El plazo concreto lo marca el conocimiento de embarque o el contrato.
- Una reclamación consiste en un escrito presentado ante el transportista dentro de esos plazos que identifique el envío, impute responsabilidad al transportista y reclame una cantidad de dinero determinada o determinable. Anotar daños o faltantes en el albarán de entrega no constituye por sí solo una reclamación formal.
- Cuando la investigación lo requiera, deben aportarse el conocimiento de embarque, el justificante de los cargos de flete y la factura. Si desaparece un bulto entero o toda la expedición y la pérdida no puede acreditarse de otro modo, el transportista debe recabar del destinatario (*consignee*) una declaración escrita certificada de que la mercancía no se ha recibido por ninguna otra vía.
- El transportista acusa recibo de la reclamación por escrito en un plazo de 30 días. Dispone de 120 días para pagarla, desestimarla o formular por escrito una oferta firme de acuerdo; si aún no puede resolverla, debe informar por escrito del estado del expediente al cumplirse ese plazo y cada 60 días sucesivos.

Si el rastreo marca `Delivered` pero no has recibido nada, revisa [entregado pero no recibido](guide:delivered-not-received). Más detalles en [paquetes dañados](guide:damaged-parcel) y [paquetes perdidos](guide:lost-parcel).

## Atención al cliente y contacto de R+L Carriers

En el sitio web estadounidense de R+L Carriers constan estos canales:

- **Teléfono:** `800-543-5589`, número visible en la cabecera de las páginas de R+L.
- **R+L Cares:** formulario en la web de R+L para incidencias pendientes, dudas o comentarios; R+L indica que el mensaje llega directamente a su dirección ejecutiva (*R+L Executive Leadership*).
- **Reclamaciones de carga:** el menú del sitio enlaza con *Cargo Claims*, donde R+L publica sus instrucciones para tramitar una reclamación de mercancía.

R+L presta el servicio para quien contrató el flete, así que acude primero a esa parte: la tienda si compraste por internet, o la empresa remitente en envíos comerciales.

## Seguir un envío de R+L Carriers con Peek

La forma de un PRO de nueve dígitos no basta para saber que pertenece a R+L, ya que otros transportistas emplean nueve cifras. Pégalo igualmente: Peek sigue a más de 3.500 transportistas. Si el código tiene dos letras y siete dígitos, Peek consulta de inmediato a R+L y muestra «R+L Carriers tiene este paquete.» cuando R+L reconoce el PRO. Si escribes nueve dígitos con el guion delante de la última cifra, Peek incluye a R+L Carriers dentro de «Encaja con este número» y también le consulta; en cambio, una vez añadido el envío (Peek suprime el guion al guardarlo) o si pegas nueve dígitos sin guion, Peek pregunta a TNT, TForce Freight y XPO cuando superan su dígito de control, pero no a R+L: pulsa «Cambiar» y busca R+L Carriers por su nombre. En un envío ya guardado, usa «Cambiar transportista». Si más adelante un servicio de seguimiento muestra la carga en movimiento mencionando a R+L Carriers, Peek archiva el envío bajo R+L y consulta directamente al buscador de R+L en las comprobaciones posteriores.

Con la letra `I` delante de nueve dígitos, el número encaja con R+L Carriers y con Australia Post: Peek pregunta a ambos y asigna el envío al que lo reconozca. También sirve pegar un enlace a la página *Shipment Tracing* de R+L que incluya `pro=`: Peek identifica a R+L al instante («Transportista detectado»).

Peek consulta el localizador de R+L únicamente con el número PRO, sin cuenta ni código postal. Muestra el estado actual, cada línea del historial (con la ciudad y el estado de la terminal cuando la línea los menciona), el día previsto de entrega, el nombre del destinatario en tu propia vista del envío («Destinatario», oculto en enlaces compartidos) y el peso en las unidades de tu dispositivo (libras si tu dispositivo usa unidades estadounidenses y kilogramos en el resto; R+L facilita libras). Omite la dirección postal del destinatario, el remitente y el pagador, el número de bultos, las referencias, el tipo de servicio, el número de conocimiento de embarque y los documentos. El buscador de R+L no entrega justificante de entrega a Peek. En la ficha del envío, «Abrir la web de R+L Carriers» abre directamente ese PRO en el rastreador de R+L.

Cuando no es posible conectar con el buscador de R+L o leer su respuesta, Peek recurre a [servicios de seguimiento](guide:universal-tracker) y muestra el historial que estos recogen con sus propias palabras.

Las líneas de R+L traen fecha y hora sin huso horario. Peek interpreta cada una según el reloj de la ciudad de la terminal, en su defecto el del estado de la terminal (el huso principal cuando un estado norteamericano abarca más de uno), si no el de la línea anterior o posterior y, en último término, la hora del Este de EE. UU. (`America/New_York`), convirtiendo todas las horas a la zona de tu dispositivo («Horas en tu zona horaria: …»).

Peek comprueba novedades hasta cada 10 minutos mientras tienes el envío abierto, o el día de su entrega si tienes activadas las notificaciones; el resto del tiempo, así como cuando pasan dos días sin líneas nuevas (12 horas mientras siga en «Etiqueta creada» o «Admitido por el transportista»), comprueba cada hora. Puedes recibir avisos de «Cada escaneo», «Pasos importantes» o «Solo la entrega». Peek no puede contactar por ti a R+L, a la tienda ni al remitente, concertar citas de entrega ni presentar reclamaciones.

## Preguntas frecuentes sobre el seguimiento de R+L Carriers

### ¿Qué es un número PRO en R+L Carriers?

Es el número de seguimiento de carga de R+L Carriers en Estados Unidos: nueve dígitos, o bien dos letras y siete dígitos, donde la última cifra actúa como dígito de control y a veces se separa con un guion. El propio buscador de R+L le antepone una `I`. Te lo proporciona el comercio o la empresa que contrató el envío.

### ¿Por qué R+L Carriers responde «No records found for Number»?

El buscador de R+L no tiene registrado ese número PRO. Comprueba cada cifra con el correo de envío de la tienda, verifica que el transportista indicado sea efectivamente R+L Carriers y pregunta al remitente si R+L ya ha retirado la carga.

### ¿Qué significa «Shipment Delayed» en R+L Carriers?

Algún contratiempo ha demorado la mercancía durante el trayecto en EE. UU. La línea no especifica el motivo; el resumen de R+L pasa a mostrar el envío como retrasado conservando un día estimado de llegada, y Peek lo mantiene «En tránsito». Consulta la causa a la tienda o al remitente.

### ¿Qué plazo hay para presentar una reclamación de carga ante R+L Carriers?

En transportes por carretera entre estados de EE. UU., la ley federal estadounidense garantiza un mínimo de 9 meses para presentar la reclamación escrita ante el transportista y al menos 2 años para demandar judicialmente desde que este rechace por escrito cualquier parte de la reclamación. El plazo exacto figura en el conocimiento de embarque o contrato; esta página no recoge las tarifas internas de R+L. Habla primero con quien contrató el transporte; las instrucciones de R+L están en el apartado *Cargo Claims* de su web.

:::sources
- [Universal Parcel Scraper: R+L Carriers](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/rl-carriers) – nombres, formatos PRO y prefijo `I`, enlaces de rastreo, textos de estado y etapas, resumen, respuestas de no encontrado y dígito de control, fecha prevista, destinatario, peso, zonas horarias y datos descartados
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – transportistas que comparten el formato, ausencia de traspaso a terceros y respaldo mediante servicios de seguimiento
- [R+L Carriers: Shipment Tracing](https://www.rlcarriers.com/freight/shipping/shipment-tracing) – búsqueda por número PRO o Pickup Request ID de hasta 25 envíos y otras referencias mediante MyRLC
- [R+L Carriers: R+L Cares](https://www.rlcarriers.com/contact/rl-cares) – formulario enviado a la dirección ejecutiva de R+L, teléfono 800-543-5589 en la cabecera y enlace a Cargo Claims
- [GovInfo: 49 U.S.C. 14706, Liability of carriers under receipts and bills of lading](https://www.govinfo.gov/content/pkg/USCODE-2024-title49/html/USCODE-2024-title49-subtitleIV-partB-chap147-sec14706.htm) – responsabilidad ante el titular del conocimiento de embarque, valor declarado y plazos mínimos de 9 meses y 2 años en EE. UU.
- [GovInfo: 49 CFR Part 370, Investigation and voluntary disposition of loss and damage claims and processing salvage](https://www.govinfo.gov/content/pkg/CFR-2025-title49-vol5/xml/CFR-2025-title49-vol5-part370.xml) – requisitos escritos de una reclamación, anotaciones en albarán, documentos de respaldo y plazos de 30, 120 y 60 días
:::
