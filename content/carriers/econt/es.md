---
title: Seguimiento de Econt: número de envío, estados y oficinas
description: Sigue un paquete de Econt en Bulgaria: dónde encontrar el código de 13 dígitos, qué significan sus estados, oficinas, Econtomat, reclamaciones y contacto.
slug: seguimiento-econt
published: 2026-11-21
updated: 2026-11-21
---

Econt Express (*Econt Express AD*) es una mensajería búlgara fundada en 1993 que entrega a domicilio, en sus oficinas Econt y en las taquillas automáticas Econtomat. Puedes localizar un envío desde `Track a shipment` en [econt.com](https://www.econt.com/en/services/track-shipment), con el enlace del correo o SMS de Econt, o pegando el número de envío en el cuadro superior.

## Número de seguimiento de Econt: formato y dónde encontrarlo

Econt lo llama número de envío (*shipment number*) o número de carta de porte (*waybill number*). Sus avisos por correo electrónico (gratuitos) y por SMS (opción de `0,11 €` contratada por el remitente, por lo que no todos los paquetes la llevan) incluyen un enlace directo al seguimiento junto con el lugar, el plazo y el importe pendiente si hay contra reembolso. Si no recibiste aviso de Econt, revisa el correo de envío de la tienda o mira [dónde más encontrar el número de seguimiento](guide:find-tracking-number).

Aunque Econt no publica una regla de formato para particulares, los números de albarán de ejemplo de su manual de integración XML tienen **13 dígitos**, y el servicio de rastreo de Econt los valida como un entero positivo de 13 cifras que no empieza por `0`, como el código inventado `1048291057364`.

Otros transportistas usan también 13 dígitos —entre ellos DPD France, Ukrposhta en Ucrania, FAN Courier en Rumanía o Delhivery en la India—, así que conviene comprobar qué empresa figura en el mensaje del comercio. Si seleccionas Econt manualmente para un código que empieza por `0` o tiene otra longitud, Peek indica que «no emite números como este».

## Qué significan los estados de seguimiento de Econt

`econt.com` se abre en búlgaro (`Проследи пратка`) con opción de cambiar a inglés (`Track a shipment`). Estos son los rótulos de escaneo en inglés que muestra Peek, en el orden habitual del envío:

| Estado de Econt | Qué significa para tu envío | Peek muestra |
| --- | --- | --- |
| `Awaiting delivery to Econt` | El remitente [creó la etiqueta](guide:tracking-statuses), pero aún no ha entregado el paquete a Econt. | «Etiqueta creada» |
| `Submitted to courier` | Al principio del historial: un mensajero lo recogió del remitente («Admitido por el transportista»). Tras pasar por oficina o ruta troncal: está en reparto final («En reparto»). | «Admitido por el transportista» / «En reparto» |
| `Arrival at an office` | Llegó a la oficina Econt o centro logístico indicado al lado (incluida tu oficina de recogida). | «En tránsito» (o «Listo para recoger») |
| `Received by line-haul` | Viaja en una ruta troncal entre centros; el texto detalla el trayecto de ciudad a ciudad. | «En tránsito» |
| `Change delivery terms` | Se modificaron las instrucciones de entrega (por ejemplo, la oficina o el día); el asunto aparece tras `Change delivery terms:`, sin movimiento físico. | *(nota sin etapa)* |
| `Shipment forwarding` | Se reexpide hacia otra localidad o dirección. | «En tránsito» |
| `Failed delivery` | No se pudo entregar (motivo entre paréntesis) y volvió a la oficina indicada. | «Intento de entrega» |
| `Delivered` | Entregado o recogido (seguido del nombre de quien lo recibió si Econt lo registra). | «Entregado» |
| `Delivery return` | El envío regresa al remitente. | «Devuelto al remitente» |
| `Shipment destruction` | Envío marcado para destrucción; contacta enseguida con la tienda o con Econt. | «Incidencia» |
| `Information` | Nota informativa sin cambio de etapa. | *(nota sin etapa)* |
| `Return receipt: Delivered` | Seguimiento del acuse de recibo firmado que vuelve al remitente; no añade pasos a tu paquete. | *(acuse, sin etapa)* |

Ten en cuenta `Submitted to courier`: antes de cualquier oficina o ruta troncal significa recogida en origen; después, reparto de última milla.

El rastreador de Econt devuelve además un **resumen en búlgaro** con el que Peek fija la etapa cuando resulta concluyente: `Очаква предаване към Еконт` y `Подготвена в eEcont` («Etiqueta creada»), `Приета в Еконт` y `Приета в офис` («Admitido por el transportista»), `Пътува по линия` («En tránsito»), `В офис` y `Пристигнала в офис` («Listo para recoger» en cuanto Econt indica qué oficina custodia el paquete), `Доставена` («Entregado»), `Върната` y `Върната и доставена към подател` («Devuelto al remitente»), y `Връща се към подател`, `Анулирана след изпращане` y `Анулирана преди изпращане` («Incidencia»). Si el resumen dice `В куриер` (con mensajero), manda el último escaneo.

## Entrega a domicilio, oficinas Econt y taquillas Econtomat

**A domicilio.** En poblaciones con oficina Econt, los paquetes estándar (hasta `50 kg` y `90 x 90 x 90 cm`, documentos A4 hasta `0,5 kg` y envíos sin paletizar de `50` a `1.000 kg`) llegan al siguiente día laborable —antes de las `18:00` en envíos urbanos—, y en localidades sin oficina en un máximo de **3 días laborables** según calendario. El remitente puede añadir franja horaria gratuita (`Delivery Time Slots`), hora fija de pago (`1,88 €` a `6,50 €`) o entrega en sábado (`1,88 €`).

**En oficina Econt.** Un envío para el siguiente día laborable está disponible a primera hora de la mañana en cuanto abre la oficina. Pasado el **7.º día** de estancia, Econt cobra un almacenaje de **`3,78 €` por día y envío**.

**En taquilla Econtomat (abierta 24/7).** Admiten bultos de hasta **`61 x 44 x 37 cm`** y **`30,5 kg`** (tres alturas de compartimento: `8 cm`, `18 cm` y `37 cm`), con tránsito de **1 a 2 días laborables** según la hora de envío. Para retirar: paga por adelantado cualquier importe pendiente en la app de Econt con tarjeta bancaria o monedero digital `ecoints`, pulsa `Receive a shipment`, escanea el código QR o introduce `PIN 1` y `PIN 2` de la app o el SMS, recoge el paquete y cierra la puerta.

**Cambiar la entrega o revisar la mercancía.** Puedes cambiar en cualquier momento destinatario, lugar (otra dirección, otra oficina, dejar a un amigo o vecino, o reenviar a otra población) y horario por teléfono, desde el enlace del correo (`instruction.econt.com`) o en tu perfil `e-Econt` ([cambiar la dirección de entrega](guide:change-delivery-address)). Además, la tarifa de Econt incluye tres opciones gratuitas que el vendedor puede activar: `Review` (revisar), `Review and Test` (revisar y probar) y `Review, Test and Select` (revisar, probar y elegir).

## Paquete de Econt retrasado, dañado o perdido

En Bulgaria, Econt prevé el siguiente día laborable donde tiene oficina, hasta 3 días laborables donde no la hay y de 1 a 2 días laborables desde un Econtomat. Si lleva días sin moverse, revisa qué hacer cuando [el seguimiento no se actualiza](guide:tracking-not-updating) y consulta a la tienda o a Econt.

Por daños, faltantes o pérdida ([paquete dañado](guide:damaged-parcel), [paquete perdido](guide:lost-parcel)), presenta una reclamación desde `I want to file a claim` en el centro de atención al cliente de Econt y avisa al comercio.

## Teléfono y atención al cliente de Econt

En su [centro de atención al cliente](https://www.econt.com/en/client-center), Econt facilita:

- **Desde Bulgaria y Grecia:** `0 7001 7300` (al coste de una llamada local o según tu tarifa telefónica).
- **Desde cualquier otro país:** `+359 88 565 43 21` (según la tarifa de tu operador).
- **En línea:** asistente virtual `Ask Ena`, buscador de oficinas y horarios (`I want to find offices and working time`) y formulario de reclamación (`I want to file a claim`).

Ante cualquier SMS sospechoso que pida pagos fuera de la app oficial o de `econt.com`, compruébalo con nuestra guía sobre [SMS fraudulentos de entregas](guide:delivery-scam-texts).

## Seguir un paquete de Econt con Peek

Como 13 dígitos coinciden con varios transportistas, [Peek](/) solo consulta automáticamente a Econt al pegar el número desde Bulgaria en la web o en un móvil con región Bulgaria. Desde fuera, elige Econt con «Cambiar» —o pega un enlace de `econt.com` con `/services/track-shipment/`, `/en/services/track-shipment/`, `/tracking/` o `/tracking/?num=`, que identifica a Econt al instante (mientras Econt aún no tenga registro o si ya purgó un envío antiguo, Peek muestra «Esperando a Econt»)—.

Peek consulta el servicio de rastreo de Econt sin cuenta ni código postal, muestra el estado actual, sitúa cada oficina en el mapa, indica en qué oficina espera el envío cuando está «Listo para recoger» y conserva hasta los 100 escaneos más recientes. Para quien añadió el paquete, mantiene el nombre del destinatario y de quien firmó tras `Delivered:` (ocultos en enlaces compartidos), descartando número de bultos, nombre del mensajero, remitente y referencias de orden; Econt no ofrece fecha estimada, peso ni foto de entrega.

Las horas se leen en el huso de Sofía (`Europe/Sofia`, `UTC+2` en invierno y `UTC+3` en verano) y se convierten a la zona de tu dispositivo («Horas en tu zona horaria: …»), salvo en la hora saltada o repetida del cambio estacional, que se conserva como hora local. Con el paquete abierto o el día de entrega con notificaciones activas, Peek comprueba hasta cada 10 minutos, y hasta cada 2 minutos desde las `06:00` hora de Sofía el día en que Econt escanea el envío «En reparto» mientras responda su rastreador (mediante servicios de seguimiento: cada hora, cada 3 h tras un día sin cambios y hasta cada 15 minutos el día de entrega con el paquete abierto o alertas activas); el resto del tiempo, y tras 12 h en «Etiqueta creada» / «Admitido por el transportista» o dos días sin novedades, cada hora («Cada escaneo», «Pasos importantes» o «Solo la entrega»). Peek no puede modificar instrucciones en Econt ni tramitar reclamaciones por ti.

## Preguntas frecuentes sobre el seguimiento de Econt

### ¿Puedo ver el seguimiento de Econt sin saber búlgaro?

Sí: Peek muestra los escaneos de Econt con las etiquetas en inglés de la tabla superior y traduce el estado general al español. `econt.com` también ofrece versión en inglés en `Track a shipment`.

### ¿Cuánto tarda en llegar un paquete de Econt en Bulgaria?

El siguiente día laborable en poblaciones con oficina Econt (antes de las `18:00` a domicilio urbano y a primera hora en oficina), hasta 3 días laborables en poblaciones sin oficina según ruta y de 1 a 2 días laborables desde un Econtomat.

### ¿Cómo rastreo un envío de Econt desde fuera de Bulgaria por su número de 13 dígitos?

Pega los 13 dígitos arriba y selecciona Econt con «Cambiar» (o pega el enlace completo `econt.com/en/services/track-shipment/…`), ya que los 13 dígitos sueltos solo se prueban automáticamente en Econt desde Bulgaria.

:::sources
- [Econt: Track a shipment](https://www.econt.com/en/services/track-shipment) – rastreador en inglés en econt.com
- [Econt: Проследяване на пратка](https://www.econt.com/services/track-shipment) – página de seguimiento en búlgaro
- [Econt: About Econt Express](https://www.econt.com/en/econt-express) – Econt Express AD, fundada en 1993 en Bulgaria
- [Econt: Customer service center](https://www.econt.com/en/client-center) – 0 7001 7300 (Bulgaria y Grecia), +359 88 565 43 21, Ask Ena, oficinas y reclamaciones
- [Econt: Courier services](https://www.econt.com/en/services/courier-services) – envíos hasta 50 kg y 90 x 90 x 90 cm, plazos, franjas, sábado, almacenaje a 3,78 €/día tras el 7.º día, SMS a 0,11 € y opciones gratuitas Review / Test / Select
- [Econt: Econtomat](https://www.econt.com/en/services/econtomat) – taquillas 24/7 hasta 61 x 44 x 37 cm y 30,5 kg, pago previo, código QR y PIN 1 / PIN 2
- [Econt: Имейл или SMS известяване](https://www.econt.com/developers/29-sms-izvestyavane.html) – avisos por correo (gratuitos) o SMS con enlace de seguimiento
- [Econt: Instructions form](https://instruction.econt.com/5cZyLrYsBZH) – formulario para vecino, cambio de oficina, reenvío y horario
- [Econt: Integration of e-shops with the information system of Econt Express](https://www.econt.com/e-econt/doc_templates/XML_manual_engl.pdf) – números de carta de porte de ejemplo de 13 dígitos
- [Universal Parcel Scraper: Econt](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/econt) – validación entera de 13 dígitos, detección en Bulgaria, estados en inglés y resúmenes en búlgaro, huso Europe/Sofia, firmante y campos descartados
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – otros transportistas de 13 dígitos y respaldo mediante servicios de seguimiento
:::
