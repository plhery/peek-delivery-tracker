---
title: Seguimiento Pengyou: números PY, estados y entrega
description: Rastrea un paquete de Pengyou (捧友) desde Shenzhen: cómo es su número PY, dónde consultarlo, por qué hay etapas escritas de antemano y a quién reclamar.
slug: pengyou-seguimiento
published: 2026-11-18
updated: 2026-11-18
---

Pengyou (捧友, 深圳市捧友供应链有限公司), que en el logotipo de su web escribe su nombre inglés como Shenzhen Pony Supply Chain Co., Ltd, es un transitario de Shenzhen cuyas líneas transportan paquetes por vía aérea hacia Europa y Estados Unidos para exportadores, otros transitarios y tiendas online. Para seguir un envío, introduce su código en el recuadro `运单查询` (consulta de albaranes) de la portada de Pengyou, szpy56.com, o pégalo directamente en el buscador de arriba. La tienda donde hiciste el pedido te facilita el número junto con los datos de envío.

## Número de seguimiento de Pengyou: cómo es

Pengyou no publica ninguna guía sobre el formato de sus códigos, ni muestra ningún número completo de ejemplo en sus páginas públicas. La estructura que [Peek](/) vincula a Pengyou consta de tres letras, nueve cifras y la terminación `PY` (14 caracteres en total). Con cifras inventadas:

| Número | Qué es |
| --- | --- |
| `ZXA482910573PY` | `ZXA`, nueve cifras y `PY`: una serie que responde en el propio rastreador de Pengyou y que Peek identifica al instante. |
| `QXB482910573PY` | Tres letras distintas de `ZXA`, nueve cifras y `PY`: el formato general asociado a Pengyou (coincidencia débil hasta comprobar el rastreador). |
| `QXB48291057PY` | Solo ocho cifras: no encaja con ese formato. Revisa si falta algún dígito. |
| `RR482910576PY` | Dos letras, nueve cifras y `PY`: un número postal internacional estándar, no de Pengyou. |

Las minúsculas y los espacios intermedios no importan cuando pegas el código en Peek.

**Dónde encontrarlo.** La portada de Pengyou se presenta como especialista en transporte para tiendas online independientes que venden a Europa y Estados Unidos (`欧美独立站运输专家`). Salvo el recuadro de consulta en portada, nada en su sitio web está pensado para destinatarios particulares. Si no localizas el código en la confirmación del pedido, revisa [cómo encontrar tu número de seguimiento](guide:find-tracking-number).

**Referencia de la tienda.** El sistema de seguimiento de Pengyou también permite localizar un pedido buscando la referencia interna asignada por el vendedor.

## Estados de seguimiento de Pengyou explicados

Pengyou no publica ningún listado oficial de estados. Esto es lo que puedes encontrar al consultar su rastreador:

| Lo que muestra el sistema | Qué significa para ti |
| --- | --- |
| `Shenzhen Processing Center has received it` | El centro de procesamiento de Pengyou en Shenzhen ha recibido el paquete. Es el primer escaneo físico en manos de Pengyou. |
| Una ciudad entre corchetes tras el texto, como `[Shenzhen City]` | El lugar donde se registró ese paso. |
| Etapas que aparecen a intervalos exactamente regulares | Una cronología escrita por adelantado al crearse el pedido, no escaneos reales de tu paquete (ver abajo). |
| Tu código bajo `参考号` (referencia) y el resto de campos vacíos | El rastreador de Pengyou no reconoce ese número. Compruébalo con el vendedor. |

**Etapas escritas por adelantado.** En la plataforma de seguimiento que utiliza Pengyou, el itinerario completo de un pedido puede dejarse escrito en el mismo momento en que se crea la orden: todos los pasos hasta la entrega final, fechados de antemano con una separación fija, van mostrándose conforme llega su fecha. Tanto el rastreador de Pengyou como los servicios externos de seguimiento que copian su registro pueden enseñar esas líneas como si fueran lecturas reales. En realidad son una previsión: indican dónde estaba planeado que estuviera el paquete en una fecha concreta, no dónde se encuentra de verdad. No te fíes de ellas para calcular el día de llegada ni tomes un paso de entrega (`Delivered`) generado así como prueba de que el paquete haya sido entregado.

**Horas en el reloj de Pekín.** Las marcas horarias del sistema no llevan zona horaria adjunta: están expresadas en la hora de Pekín (`UTC+8`). Durante el horario de invierno europeo (del último domingo de octubre al último domingo de marzo), las `20:00` de Pekín corresponden a las `13:00` en la España peninsular y Baleares (`12:00` en Canarias); en horario de verano (del último domingo de marzo al último domingo de octubre), equivalen a las `14:00` en Península y Baleares (`13:00` en Canarias).

## Quién entrega los paquetes de Pengyou en España

La web de Pengyou afirma operar principalmente líneas hacia Europa y Estados Unidos con «doble despacho e impuestos incluidos» (`双清包税`), es decir, despacho aduanero en salida y llegada con aranceles incluidos en la tarifa contratada por el remitente. Su sede central se sitúa junto al aeropuerto de Bao'an en Shenzhen, con sucursales en Bantian, Cantón (Guangzhou) y Hong Kong.

Su página de inicio enumera cuatro líneas principales por nombre: `欧洲DHL`, `欧洲DHL专线` y `欧洲DPD专线` (`欧洲` significa Europa), además de `美国UPSP专线` (`美国` es Estados Unidos); al abrir las páginas interiores de esas líneas, solo aparece el título sin ningún detalle adicional. En la sección `合作伙伴` (socios) muestra los logotipos de [DHL](carrier:dhl), [UPS](carrier:ups), [FedEx](carrier:fedex), [TNT](carrier:tnt), [USPS](carrier:usps), DPD y el agregador 17TRACK. Aunque su esquema general del servicio termina con el reparto en destino (`配送目的地`) y la firma del destinatario, la web nunca especifica qué empresa entrega un paquete concreto en España, ni publica plazos de tránsito, reglas de intentos de entrega, puntos de recogida ni días de custodia.

**Aduanas, territorios y avisos antifraude (UE y España).** Si te reclaman cargos de importación por un paquete que el vendedor aseguró enviar con derechos incluidos, consulta primero con la tienda antes de abonar nada. Con carácter general en la **Unión Europea**, desde el 1 de julio de 2026 y hasta el 1 de julio de 2028 las compras a distancia procedentes de fuera de la UE con valor intrínseco de hasta 150 € están sujetas a un arancel fijo de 3 € por cada tipo de producto, y desde el 1 de noviembre de 2026 se aplica de forma definitiva una tasa de tramitación de la Unión de 2 € por tipo de producto sea cual sea el valor del envío. En **España**, [Correos](carrier:correos-spain) detalla que tanto el arancel europeo de 3 € como la tasa europea de 2 € se aplican a los envíos dirigidos a consumidores de la **Península, Baleares y Canarias** (Canarias forma parte del territorio aduanero de la UE aunque quede fuera del ámbito armonizado del IVA), mientras que **Ceuta y Melilla** no pertenecen al Territorio Aduanero de la Unión (`TAU`) ni al territorio del IVA de la UE, por lo que esas dos medidas comunitarias no se aplican a las importaciones con destino a Ceuta o Melilla. En cuanto a fraudes por SMS en **España**, el Instituto Nacional de Ciberseguridad (INCIBE) advierte de campañas de *smishing* que suplantan a Correos, Correos Express y otras empresas de paquetería alegando que no se ha podido entregar un paquete por falta de pago de tasas de aduanas e instando a abrir un enlace: INCIBE recomienda no pulsar el enlace, no responder al SMS y verificar cualquier duda directamente a través de los canales oficiales de la empresa implicada. Más información en [paquetes retenidos en aduanas](guide:customs) y en [SMS falsos de entregas](guide:delivery-scam-texts).

## Paquete de Pengyou que no llega o seguimiento parado

**Etapas demasiado puntuales y espaciadas.** Corresponden al calendario escrito por adelantado al crear el pedido, no a escaneos físicos. Si pasan varios días sin que aparezca ninguna lectura real, pregunta al vendedor si ya ha entregado la mercancía al transitario.

**Sin novedades después de Shenzhen.** Si el historial se detiene en `Shenzhen Processing Center has received it` o poco después, pregunta al vendedor si otra empresa de mensajería se encarga del reparto final en España y pídele ese segundo número de seguimiento para añadirlo a Peek por separado. Tienes más contexto en [por qué el seguimiento deja de actualizarse](guide:tracking-not-updating) y en [seguimiento de paquetes desde China](guide:tracking-from-china).

**Paquete perdido, dañado o devuelto.** Pengyou no publica ningún procedimiento de reclamación ni condiciones para destinatarios particulares: sus páginas de perfil de empresa y de contacto están vacías. Quien contrató el envío con Pengyou fue el vendedor, así que reclama el reembolso o un reenvío a la tienda o al marketplace donde compraste. Consulta [paquetes perdidos](guide:lost-parcel), [devuelto al remitente](guide:returned-to-sender) y [entregado pero no recibido](guide:delivered-not-received).

## Teléfono y atención al cliente de Pengyou

Según figura en la portada de szpy56.com, únicamente en chino:

- **Teléfono**: `181 2470 1707` y `188 7908 6605`, dos números móviles chinos (con prefijo `+86` desde el extranjero), anunciados como línea de consulta abierta las 24 horas (`全天24小时咨询热线`). No se indican idiomas de atención.
- **QQ**: `2853292077`, su cuenta oficial de asistencia (`官方服务QQ`), que se abre desde el botón de atención en línea (`在线客服咨询`).
- **WeChat**: un código QR para su cuenta oficial (`官方微信二维码`).

Pengyou no publica ninguna dirección de correo electrónico ni dirección postal con calle. Para cualquier gestión sobre tu compra, acude directamente al vendedor.

## Seguir un paquete de Pengyou con Peek

[Peek](/) asigna Pengyou de inmediato cuando el código empieza por `ZXA`, una serie que responde directamente en el rastreador de Pengyou. Con otras letras iniciales, Peek no puede dar por seguro que se trate de Pengyou solo por la forma del código, ya que tres letras, nueve cifras y `PY` es únicamente una coincidencia débil. Pégalo igualmente: Peek sigue a más de 3.500 transportistas. Mientras lo añades, Peek consulta el rastreador de Pengyou y muestra «Pengyou tiene este paquete.» cuando el sistema reconoce el número. Si aún no aparece allí, pulsa para cambiar de transportista y elige Pengyou (también lo encontrarás escribiendo `捧友`), o usa «Cambiar transportista» en un envío que ya tengas guardado. Haz lo mismo con una referencia interna del vendedor: Peek mostrará entonces también el número de seguimiento propio del pedido. No existe enlace directo de seguimiento de Pengyou que pegar, porque su buscador funciona mediante formulario.

Peek lee el rastreador propio de Pengyou y muestra el estado actual, cada paso con su ubicación y el país de destino cuando consta en el registro. Las líneas se presentan tal como las escribe Pengyou, incluida la ciudad entre corchetes, salvo cuando una línea `Delivered` va seguida del nombre de una localidad: en ese caso Peek muestra «Entregado», toma la localidad como lugar del evento y descarta el código postal. `Shenzhen Processing Center has received it` se clasifica como «Admitido por el transportista». Las horas se leen en el huso de Pekín y se convierten a la zona horaria de tu dispositivo (con la nota «Horas en tu zona horaria: …» en `peektracker.com` y en la app para iPhone). Peek omite los nombres, la dirección, los códigos postales, el peso, los importes y la firma que pueda guardar el registro de Pengyou, y no muestra fecha estimada de entrega porque Pengyou no facilita ninguna.

Peek nunca muestra un paso escrito más de doce horas antes de la marca horaria que lleva, ni más de seis horas antes en el mismo segundo que otro paso así, y retiene cualquier paso fechado después del instante de la consulta en hora de Pekín. En un pedido con etapas escritas de antemano, retiene también `Delivered`, incluso aunque figure grabado en su propio día, ya que la plataforma de Pengyou añade ese cierre en un lote vespertino. Cuando la interfaz de datos del rastreador no devuelve pasos para un número, Peek lee en su lugar la página web de seguimiento de Pengyou; como esa página no indica cuándo se escribió cada línea, allí Peek retiene todo `Delivered`. Además, descarta las copias que los servicios externos de seguimiento hacen de esos pasos retenidos y calcula el estado únicamente a partir de los escaneos que conserva, no por el estado resumen del registro. Un pedido cuyas etapas están todas escritas por adelantado aún no muestra historial en Peek y aparece como «Esperando a Pengyou» en `peektracker.com`, sin pedir que revises el número: Peek lo trata igual que un envío registrado en Pengyou sin primer escaneo y vuelve a consultar su rastreador cada 6 horas tras las primeras horas si el paquete está en la app para iPhone o en tu cuenta. En una consulta sin cuenta, pregunta durante las primeras 6 horas, después al abrir su enlace (como máximo cada 6 horas durante los dos primeros días, y luego a lo sumo una vez al día) y, mientras las notificaciones estén activadas, a diario hasta 30 días después de la búsqueda. Cuando retiene pasos escritos por adelantado, Peek muestra bajo el estado del paquete, hasta que se entregue o se devuelva: «La web de Pengyou muestra etapas escritas por adelantado, incluso la entrega. Peek no puede confirmarlas, así que las omite. Busca en esa web la empresa que reparte en tu país y su número de seguimiento; si no aparece, pregunta al vendedor.» Un paso retenido nunca cuenta como escaneo: no cambia el estado ni envía avisos.

Mientras mantienes el paquete abierto en pantalla, o en el día de reparto con las notificaciones activadas, Peek comprueba novedades hasta cada 10 minutos cuando responde el rastreador directo (a través de servicios de seguimiento: cada hora, cada 3 horas tras un día sin nuevos escaneos y, el día de reparto, hasta cada 15 minutos con el paquete abierto o las notificaciones activas). El resto del tiempo, así como tras dos días sin novedades (o 12 horas mientras el paquete sigue en «Etiqueta creada» o «Admitido por el transportista»), comprueba cada hora. Puedes configurar alertas para «Todas las novedades», «Solo importantes» o «Solo el día de entrega». Peek no puede ponerse en contacto con Pengyou ni con el vendedor en tu nombre.

## Preguntas frecuentes sobre el seguimiento de Pengyou

### ¿Qué es Pengyou (捧友)?

Es 深圳市捧友供应链有限公司 (`Shenzhen Pony Supply Chain Co., Ltd` según el logotipo en inglés de su web), un transitario de Shenzhen cuyas líneas llevan paquetes por aire a Europa y Estados Unidos para exportadores, transitarios y tiendas online. Su sitio web es szpy56.com.

### ¿Por qué el rastreador de Pengyou muestra pasos que no salen en Peek?

Porque la plataforma que usa Pengyou permite dejar escrita por adelantado toda la cronología del envío hasta la entrega. Esos pasos son una previsión y no escaneos físicos, por lo que Peek los omite, incluido `Delivered`.

### ¿Dónde puedo rastrear un paquete de Pengyou?

En el recuadro `运单查询` (consulta de albaranes) de la portada de szpy56.com, que admite hasta 10 códigos a la vez (uno por línea) y abre el rastreador de Pengyou, o pegando el código en Peek y eligiendo Pengyou si el formato no basta para identificarlo automáticamente.

### ¿Cuánto tarda Pengyou en entregar un pedido?

Pengyou no publica plazos de entrega en ninguna de sus páginas. Guíate por la fecha estimada que te dio la tienda al comprar, nunca por las fechas programadas de etapas escritas de antemano.

### ¿Con quién contacto si mi paquete de Pengyou no llega?

Con el vendedor donde hiciste el pedido, que fue quien contrató el transporte con Pengyou. Pengyou solo publica dos teléfonos móviles chinos, un chat de QQ y un código de WeChat en chino, sin atención específica para destinatarios particulares.

:::sources
- [Pengyou: 深圳市捧友供应链有限公司 (portada, en chino)](http://szpy56.com/) – razón social y nombre inglés en el logotipo, líneas a Europa y EE. UU. con doble despacho e impuestos incluidos, sede y sucursales, perfil de clientes, líneas principales, buscador `运单查询` hasta 10 números, logotipos de socios, esquema del servicio, teléfonos móviles 24 h, QQ y WeChat
- [Pengyou: 商业快递 (mensajería comercial, en chino)](http://szpy56.com/sykd/) – líneas Europa DHL y Europa DPD, cuyas páginas interiores contienen solo el título
- [Pengyou: 国际专线 (líneas internacionales, en chino)](http://szpy56.com/gjzx/) – línea EE. UU. y línea Europa DHL, cuyas páginas interiores contienen solo el título
- [Pengyou: 公司简介 (perfil de empresa, en chino)](http://szpy56.com/gywm/gsjj/) – página publicada sin contenido
- [Pengyou: 联系我们 (contacto, en chino)](http://szpy56.com/lxwm/) – página publicada sin contenido
- [Comisión Europea: EU Customs Reform (en inglés)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – arancel de 3 € en envíos de hasta 150 € desde el 1 de julio de 2026 y tasa de tramitación de la Unión de 2 € desde el 1 de noviembre de 2026
- [Comisión Europea: Guidance on the 3 EUR customs duty and Union handling fee (PDF, en inglés)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – aplicación de los 2 € por categoría de producto cualquiera que sea el valor del envío
- [Comisión Europea: Territorial Scope (en inglés)](https://taxation-customs.ec.europa.eu/taxation/vat/vat-directive/how-does-vat-work/territorial-scope_en) – Canarias dentro del territorio aduanero de la UE y fuera del ámbito del IVA; Ceuta y Melilla fuera de ambos
- [Correos: Arancel y tasas para envíos de importación a la UE](https://www.correos.es/es/es/atencion-al-cliente/informacion-aduanera/arancel-para-envios-de-bajo-valor-en-la-ue) – aplicación en España (Península, Baleares y Canarias) y exclusión de las importaciones a Ceuta y Melilla
- [INCIBE: En circulación SMS fraudulentos que suplantan a Correos y otras empresas de paquetería](https://www.incibe.es/ciudadania/avisos/en-circulacion-sms-fraudulentos-que-suplantan-correos-y-otras-empresas-de) – aviso en España sobre SMS fraudulentos (*smishing*) con falsos pagos de tasas aduaneras y recomendación de consultar por canales oficiales
- [Comisión Europea: Summertime (en inglés)](https://transport.ec.europa.eu/transport-themes/summertime_en) – horario de verano europeo del último domingo de marzo al último domingo de octubre
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formato de los números `ZXA…PY` y `…PY`, texto de los escaneos y ciudad entre corchetes, respuesta ante un número desconocido, búsqueda por referencia, cronologías escritas de antemano y qué retiene Peek (incluido `Delivered`), lectura alternativa de la página web, reloj de Pekín y qué muestra y descarta Peek
:::
