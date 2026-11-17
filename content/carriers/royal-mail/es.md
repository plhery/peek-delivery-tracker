---
title: Seguimiento Royal Mail: número, estados, aduanas y Correos
description: Sigue tu paquete de Royal Mail en España: formatos GB, estados en inglés, entrega con Correos y qué aranceles, tasas de la UE e IVA pagarás al recibirlo.
slug: seguimiento-royal-mail
published: 2026-11-17
updated: 2026-11-17
---

Royal Mail es el operador postal nacional del Reino Unido, y su buscador *Track your item* (en `royalmail.com`) también muestra los paquetes de Parcelforce Worldwide. Para seguir un envío, introduce tu código en `royalmail.com` o pégalo en el cuadro de arriba. El número te lo facilita siempre el remitente: búscalo en la confirmación de pedido, en el correo de envío o en el recibo de la tienda británica.

## Número de seguimiento de Royal Mail: formatos y dónde encontrarlo

Los envíos con seguimiento de Royal Mail utilizan tres formatos principales. Si no ves ningún código en los mensajes de la tienda, pídeselo al vendedor; consulta también [cómo encontrar tu número de seguimiento](guide:find-tracking-number).

| Formato (ejemplo inventado) | Qué es |
| --- | --- |
| `AB482910576GB` | El código postal internacional habitual (estándar S10 de la UPU): 2 letras, 9 cifras y `GB`. La novena cifra es un dígito de control. |
| `0A1234567000000000ABC` | Referencia de 21 caracteres leída del código de barras 2D en etiquetas nacionales del Reino Unido. |
| `4829105738ABCDEF` | Referencia de 16 caracteres formada por cifras y letras de la `A` a la `F`. |

Formatos parecidos que conviene distinguir:

- Un prefijo que empiece por `E` (`EA`, `EB`…), `CP` o `GI` seguido de 9 cifras y `GB` corresponde a un envío de Parcelforce Worldwide (el seguimiento de Royal Mail también acepta referencias Parcelforce de 14 caracteres que empiezan por `PB` más dos letras y diez cifras).
- El mismo formato S10 terminado en el código de otro país (`RR482910576ES`, por ejemplo) procede del operador postal de origen. Según la norma S10 de la UPU, el envío conserva ese identificador único de principio a fin, y Royal Mail lo escanea al llegar al Reino Unido (salvo los envíos urgentes EMS que empiezan por `E`, entregados allí por Parcelforce).
- Una cadena de 16 cifras sin ninguna letra no pertenece a Royal Mail: ante ese formato, Peek consulta al mismo tiempo a varios transportistas, entre ellos DHL eCommerce y [TNT](carrier:tnt). Si una referencia de 21 caracteres empieza por `32` y contiene solo números, Peek pregunta primero a [SEUR](carrier:seur) antes de consultar los servicios de seguimiento y el navegador de Royal Mail.

## Qué significan los estados de Royal Mail en español

Royal Mail publica todo su seguimiento únicamente en inglés. Estos son los estados más frecuentes en paquetes enviados desde el Reino Unido o entregados allí, en el orden habitual del recorrido:

| Royal Mail muestra | Qué significa para ti |
| --- | --- |
| `Sender preparing item` / `Sender has despatched item` | El remitente ha generado la etiqueta o anunciado el envío, pero Royal Mail aún no tiene el paquete. |
| `We're expecting it` | Envío preavisado electrónicamente, pendiente de recepción física en Royal Mail. |
| `Item Received` | Royal Mail ha recibido el paquete. La misma frase reaparece en centros de clasificación británicos y al llegar al centro internacional del país de destino. |
| `In transit` | El envío viaja entre centros logísticos de la red. |
| `Item Leaving the UK` | El paquete ha salido del Reino Unido rumbo al país de destino. |
| `Your item is currently with Customs in the destination country.` | El envío está pasando el control aduanero de importación en tu país. |
| `Released from Customs` | El despacho aduanero ha terminado y el paquete pasa al reparto nacional. |
| `Customs charges raised` | Hay IVA, IGIC, aranceles o gastos de gestión pendientes de pago antes de la entrega (ver abajo). |
| `Item has left the overseas International Processing Centre` | El paquete ha salido del centro de tratamiento internacional en destino. |
| `Despatched to delivery office` / `Item received at Sorting Office` | En camino hacia la unidad o centro de reparto de tu zona. |
| `Ready for Delivery` | Recibido en la unidad local y listo para salir a reparto. |
| `Out for delivery` / `Due to be delivered today` | En reparto hoy con el cartero. |
| `Delivery attempted` | No se pudo entregar en mano; revisa tu buzón, tus SMS o tu correo electrónico. |
| `Due for Redelivery the Next Working Day` | En entregas dentro del Reino Unido, Royal Mail volverá a intentarlo el siguiente día laborable. |
| `Available for Redelivery or Collection` | Disponible para recoger en oficina o concertar un nuevo intento. |
| `Delivered` | Entregado (en el Reino Unido, `Delivered to Neighbour/Reception by` indica entrega a un vecino o en recepción). |
| `Returned to sender` | El paquete vuelve de regreso al remitente. |

A veces el resumen de Royal Mail marca `Pending`, sobre todo junto a eventos de aduana: en ese caso, Peek toma la etapa del último escaneo concreto registrado por Royal Mail, como «En aduanas».

## Quién entrega tu paquete de Royal Mail en España

Cuando un paquete sale del Reino Unido con un número postal terminado en `…GB`, entra en la red postal internacional y [Correos](carrier:correos-spain) asume el despacho y el reparto en España con ese mismo código:

- **Entrega a domicilio y oficina:** si no estás en casa cuando pasa el cartero, Correos te deja un *Aviso de Llegada* en el buzón o te avisa por SMS. Podrás recoger el envío a partir del siguiente día hábil en la oficina indicada durante **15 días** (Correos envía recordatorios por SMS cuando quedan 10 y 5 días antes de devolverlo al remitente). Lleva tu DNI; si acude otra persona, necesitará la autorización firmada del aviso, su propio DNI y una fotocopia del tuyo.
- **Cambios de entrega y taquillas Citypaq:** si no vas a estar en la dirección indicada, el servicio *Correos Modify* permite gestionar la entrega de tu envío. Si lo recoges en una taquilla inteligente *Citypaq*, tienes **5 días naturales** para hacerlo.

## Aduanas, IVA y aranceles en envíos desde el Reino Unido

Desde el Brexit, Gran Bretaña está fuera de la Unión Europea (solo Irlanda del Norte permanece alineada con las normas aduaneras de mercancías de la UE). Todo pedido enviado desde Gran Bretaña pasa por aduanas al llegar a España.

### En la Península, Baleares y Canarias

Las compras en línea importadas desde fuera de la UE pagan IVA desde el primer euro en la Península y Baleares (al 21 % general); en Canarias, los envíos de hasta 150 € mantienen la exención del IGIC. Además, se aplican dos recargos aduaneros europeos sobre las ventas a distancia importadas al territorio aduanero de la Unión —que incluye **Península, Baleares y Canarias**, pero **no las importaciones con destino a Ceuta ni Melilla**, al estar fuera de dicho territorio aduanero—:

- **Arancel fijo de 3 € por tipo de producto (hasta 150 €):** desde el 1 de julio de 2026 desaparece la antigua exención aduanera bajo 150 € y, hasta el 1 de julio de 2028, todos los envíos comerciales de hasta 150 € pagan un arancel temporal de **3 € por tipo de producto** (según su subpartida arancelaria y origen), cualquiera que sea el régimen de IVA empleado (tanto si la tienda cobró el IVA mediante ventanilla única IOSS como si no). Dos camisetas iguales cuentan como un solo tipo de producto (3 €), mientras que una camiseta y un pantalón cuentan como dos tipos de producto (6 €). Una mercancía con origen preferencial en virtud de un acuerdo comercial de la UE (como el acuerdo UE–Reino Unido) solo puede acogerse al arancel preferencial mediante una declaración estándar `H1` siempre que no se haya usado IOSS; todas las compras declaradas por IOSS abonan los 3 €. Si el pedido supera los 150 €, se aplica el arancel convencional.
- **Tasa europea de tramitación de 2 € por tipo de producto:** desde el 1 de noviembre de 2026, las ventas a distancia importadas desde fuera de la UE suman una tasa europea permanente y no reembolsable de **2 € por tipo de producto**, sea cual sea el valor del envío (incluso por encima de 150 € o con origen preferencial).
- **Cómo se tramita y paga con Correos:** en la Península y Baleares, los envíos de hasta 150 € sin impuestos especiales ni controles paraaduaneros (sanidad, farmacia…) se despachan con declaración simplificada `H7`. Si quedan importes pendientes (IVA no pagado por IOSS, los 3 €, los 2 € y la tarifa de gestión de Correos), puedes abonarlos en `www.correosaduanas.es`, en la app de Correos o en efectivo al cartero o en la oficina al recoger el paquete. Los envíos de más de 150 € (o sujetos a controles paraaduaneros o impuestos especiales) requieren un DUA ordinario (`H1`), cuyo pago debe realizarse antes de la entrega en `www.correosaduanas.es` o por transferencia bancaria. Más detalles en [paquete retenido en aduanas](guide:customs).

## Si vives en el Reino Unido: tarjeta roja, entregas y aduana británica

En el Reino Unido, Royal Mail reparte de lunes a sábado, normalmente antes de las 16:30, y también los domingos en algunas zonas para determinados paquetes, pero no en festivos (*bank holidays*). Las cartas *2nd Class* están pasando de forma gradual a entregarse en días laborables alternos de aquí a diciembre de 2026, mientras que los paquetes (incluido *Tracked 24*) mantienen el reparto de lunes a sábado. Desde el 1 de abril de 2026, los objetivos fijados por Ofcom exigen entregar el 90 % del correo *1st Class* al día siguiente laborable y el 95 % del *2nd Class* en tres días (objetivos que Royal Mail no alcanzó entre abril y junio de 2026, según Citizens Advice).

Si no estás en casa en una dirección británica, el cartero puede dejar el paquete en tu lugar seguro (*safe place*) o a un vecino (las preferencias guardadas en la app duran un año y existe una pegatina para rechazar entregas a vecinos). Si no es posible, deja una tarjeta roja (*red card*) con la dirección y el horario del *Customer Service Point* para [recoger el paquete tras un intento fallido](guide:missed-delivery) o pedir una nueva entrega en `royalmail.com`.

Cuando un paquete procedente del extranjero genera impuestos en el Reino Unido, Royal Mail envía una factura y retiene el envío unas **3 semanas** antes de devolverlo al remitente (regalos exentos de IVA hasta 39 £; compras de hasta 135 £ normalmente con IVA ya cobrado por la tienda; devoluciones de cargos indebidos mediante el formulario BOR 286). En Irlanda del Norte, los envíos procedentes de fuera del Reino Unido y de la UE considerados «en riesgo» de pasar a la UE pagan los derechos aplicables más 2 € por tipo de producto convertidos a libras esterlinas.

## Paquete de Royal Mail atascado, retrasado o perdido

- **Sin información de seguimiento:** antes del primer escaneo físico, Royal Mail puede no reconocer el número, y `Sender preparing item` indica que aún no ha recibido el paquete. Si pasan varios días sin cambios, confirma con la tienda que realmente lo entregó. Consulta [por qué no se actualiza el seguimiento](guide:tracking-not-updating).
- **Parado en `Item Leaving the UK`:** es habitual pasar varios días sin escaneos entre la salida del Reino Unido y la entrada en España. Prueba el mismo número `…GB` en [Correos](carrier:correos-spain) o en `www.correosaduanas.es` por si hubiera un trámite pendiente.
- **Retraso o pérdida:** si compraste en una tienda en línea, reclama primero al vendedor (en la UE tiene por defecto **30 días** para entregar el pedido). Ante Royal Mail, tanto el remitente como el destinatario de un envío franqueado en el Reino Unido pueden pedir indemnización con justificante de admisión (o el envoltorio matasellado), salvo en *Tracked 24* y *Tracked 48*, donde solo puede reclamar el remitente. No hay indemnización por retraso en *Tracked 24*, *Tracked 48* ni en envíos internacionales. Para reclamar por pérdida hacia Europa hay que esperar **20 días laborables** tras la fecha prevista de entrega (25 al resto del mundo) y presentar la solicitud antes de **6 meses** desde el envío (en envíos nacionales británicos: espera de 11 días laborables tras el depósito en *1st Class*, 14 en *2nd Class* y 5 tras la fecha prevista en *Special Delivery*, más 1 día entre el primer lunes de diciembre y el primer laborable de enero, con un plazo máximo de 80 días). Topes sin seguro adicional: 20 £ (*1st/2nd Class*), 75 £ (*Tracked 24/48*), 50 £ (internacional con seguimiento o firma) y 750 £ (*Special Delivery Guaranteed by 1pm*). Royal Mail responde en un máximo de 30 días (90 días en envíos internacionales). Ver también [paquete perdido](guide:lost-parcel) y [paquete devuelto al remitente](guide:returned-to-sender).
- **Marcado como entregado pero no lo tienes:** pregunta en casa, a los vecinos o en conserjería, revisa el buzón y avisa enseguida al vendedor. Consulta [paquete marcado como entregado pero no recibido](guide:delivered-not-received).

## Cómo contactar con Royal Mail y Correos

- **Royal Mail (Reino Unido):** `03457 740 740` (tarifa nacional británica; desde España suele resultar caro llamar, así que compensa gestionar cualquier incidencia a través del vendedor), de lunes a viernes de 8:00 a 18:30, sábados de 8:00 a 15:00 y domingos de 9:00 a 14:00 (hora del Reino Unido). Textphone: `03456 000 606`; Relay UK: `18001` seguido de `0345 266 0075`. Si una queja en el Reino Unido no se resuelve, puede elevarse en 30 días al *Postal Review Panel* y después al mediador independiente POSTRS (`020 7520 3766`, `postrs@cedr.com`).
- **En España:** una vez que el envío entra en la red nacional, consulta el seguimiento y los trámites aduaneros directamente en [Correos](carrier:correos-spain) (`correos.es` y `correosaduanas.es`).

> ¿Has recibido un SMS o un correo pidiendo pagar unos pocos euros para liberar tu paquete? No pulses ningún enlace. Correos advierte expresamente que nunca solicita datos personales, bancarios ni movimientos económicos por correo electrónico ni por mensaje de texto (SMS): comprueba siempre el envío escribiendo tú mismo la dirección oficial o en la app de Correos. En el Reino Unido, los SMS fraudulentos se reenvían al **7726** y los correos a `report@phishing.gov.uk`. Más pistas en [cómo detectar mensajes falsos de entrega](guide:delivery-scam-texts).

## Seguir un paquete de Royal Mail con Peek

[Peek](/) reconoce al instante un código válido como `AB482910576GB` y muestra «Transportista detectado». Si el dígito de control no coincide, Peek avisa con «El dígito de control de este número no cuadra.»: revisa el correo del vendedor, acepta la corrección sugerida cuando una letra haya podido confundirse con una cifra (`O` y `0`), o pulsa «Seguir tal como está» y selecciona Royal Mail mediante «Cambiar». Con referencias domésticas británicas de 21 o 16 caracteres, Peek consulta primero servicios de seguimiento antes de abrir el navegador de Royal Mail en último lugar, por lo que puede tardar unos segundos; si no aparece o una referencia poco común de 21 caracteres se atribuye a Australia Post, elige Royal Mail con «Cambiar». Un enlace de `royalmail.com` con `trackNumber=` identifica a Royal Mail directamente en cualquier formato; los enlaces `#/tracking-results/` solo sirven para números S10 terminados en `GB`.

Peek abre el seguimiento de Royal Mail en un navegador desde su propio servidor. Como Royal Mail limita de forma estricta el tráfico automatizado, Peek consulta cada envío de Royal Mail por su cuenta aproximadamente una vez por hora, tanto un día normal como el día de reparto, aunque exista franja horaria prevista; una actualización manual puede adelantarlo. Verás el estado actual, cada escaneo situado en un mapa con su localidad (sin códigos postales), la fecha estimada de entrega (y su franja horaria cuando consta), la hora de entrega y el país de destino. Las horas británicas se convierten con la nota «Horas en tu zona horaria: …». Los eventos de entrega muestran «Entregado» sin el nombre del firmante, la dirección ni la foto, mientras que los demás escaneos conservan el texto original en inglés. Puedes configurar avisos para «Cada escaneo», «Pasos importantes» o «Solo la entrega».

En los envíos `…GB` que salen del Reino Unido, en cuanto Royal Mail indica el país de destino (o según el país configurado en Peek), Peek consulta también al operador postal nacional en más de 60 países —incluido [Correos](carrier:correos-spain) en España— y pasa a mostrar «Entrega con …» cuando este publica nuevos escaneos, adoptando entonces su frecuencia de comprobación más rápida (hasta cada 10 minutos, o hasta cada 2 minutos durante el reparto). El pago de aduanas, los cambios de entrega y las reclamaciones se gestionan directamente con Royal Mail o con Correos.

## Preguntas frecuentes sobre el seguimiento de Royal Mail

### ¿Quién entrega un paquete de Royal Mail en España?

[Correos](carrier:correos-spain) se encarga del despacho aduanero y de la entrega en España manteniendo el mismo número de seguimiento terminado en `…GB`. En cuanto Correos registra sus propios escaneos, Peek pasa automáticamente a mostrarlos.

### ¿Cuánto tendré que pagar de aduanas por un paquete del Reino Unido?

En la Península y Baleares, las compras enviadas desde Gran Bretaña pagan IVA desde el primer euro (salvo que la tienda ya lo haya cobrado mediante IOSS); en Canarias, los envíos de hasta 150 € mantienen la exención del IGIC. En los tres territorios se añaden el arancel fijo de 3 € por tipo de producto en pedidos de hasta 150 € (vigente desde julio de 2026), la tasa europea de 2 € por tipo de producto (desde noviembre de 2026) y la tarifa de gestión aduanera de Correos cuando corresponda. Las importaciones a Ceuta y Melilla quedan fuera de los recargos aduaneros de la UE de 3 € y 2 €.

### ¿Cada cuánto se actualiza el seguimiento de Royal Mail?

El historial solo cambia cuando el paquete recibe un nuevo escaneo físico; entre `Item Leaving the UK` y la llegada a España pueden pasar varios días sin novedades. Por su parte, Peek consulta a Royal Mail aproximadamente una vez por hora y pasa al ritmo más frecuente de Correos cuando el envío entra en la red española.

### ¿Qué significan `Customs charges raised` y `Your item is currently with Customs`?

`Your item is currently with Customs in the destination country.` indica que el paquete está pasando el control aduanero en España (o en el país de destino). `Customs charges raised` significa que existen impuestos, aranceles o tasas pendientes: en España puedes revisarlos y pagarlos a través de `www.correosaduanas.es`, la app de Correos o en efectivo en la entrega si se tramita por declaración simplificada `H7`.

:::sources
- [Citizens Advice: Check Royal Mail collection and delivery times](https://www.citizensadvice.org.uk/consumer/post-and-parcels/post-collection-delivery-times/) – días y horarios de reparto en el Reino Unido, despliegue de cartas 2nd Class en días alternos, Customer Service Points y teléfonos
- [Citizens Advice: Telling delivery companies about your delivery or collection needs](https://www.citizensadvice.org.uk/consumer/post-and-parcels/telling-delivery-companies-about-your-delivery-or-collection-needs/) – lugar seguro, vecino, pegatina, preferencias guardadas un año, tarjeta roja y nueva entrega
- [Citizens Advice: Claiming compensation from Royal Mail](https://www.citizensadvice.org.uk/consumer/post-and-parcels/claiming-compensation-from-royal-mail/) – quién puede reclamar, justificante de envío, plazos de espera y reclamación en el Reino Unido y Europa, importes máximos y plazos de respuesta
- [Citizens Advice: Damaged, lost or delayed post or parcel](https://www.citizensadvice.org.uk/consumer/post-and-parcels/damaged-lost-delayed-post-or-parcel/) – reclamación al vendedor, Postal Review Panel en 30 días y mediador POSTRS
- [Citizens Advice: Response to Royal Mail's Quality of Service results, August 2026](https://www.citizensadvice.org.uk/about-us/media-centre/press-releases/citizens-advice-responds-to-royal-mails-latest-quality-of-service-results-august-2026/) – objetivos de Ofcom desde el 1 de abril de 2026, resultados de abril a junio de 2026 y calendario de 2nd Class hasta diciembre de 2026
- [GOV.UK: Tax and customs for goods sent from abroad: Tax and duty](https://www.gov.uk/goods-sent-from-abroad/tax-and-duty) – factura de Royal Mail, retención de unas 3 semanas, umbrales de 39 £ y 135 £, formulario BOR 286 y tasa de 2 € por tipo de producto en Irlanda del Norte
- [GOV.UK: Avoid and report internet scams and phishing](https://www.gov.uk/report-suspicious-emails-websites-phishing) – denuncia de SMS al 7726 y correos a report@phishing.gov.uk
- [UPU: norma S10, Identification of postal items](https://www.upu.int/UPU/media/upu/files/postalSolutions/programmesAndServices/standards/S10-12.pdf) – estructura de 13 caracteres, dígito de control y conservación del identificador único hasta la entrega
- [Comisión Europea: Guidance on the temporary EUR 3 customs duty and the Union handling fee](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – arancel de 3 € por tipo de producto cualquiera que sea el régimen de IVA, declaración H1 para origen preferencial sin IOSS y tasa no reembolsable de 2 € por tipo de producto
- [Comisión Europea: EU Customs Reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_es) – reforma aduanera de la UE y fin de la exención bajo 150 € desde el 1 de julio de 2026
- [Your Europe: Envío y entrega](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_es.htm) – plazo legal de entrega de 30 días y cálculo de los 3 € por tipo de producto
- [Correos: Arancel para envíos de bajo valor en la UE](https://www.correos.es/es/es/atencion-al-cliente/informacion-aduanera/arancel-para-envios-de-bajo-valor-en-la-ue) – arancel de 3 € desde julio de 2026 y tasa de 2 € por tipo de producto desde noviembre de 2026 en Península, Baleares y Canarias, y exclusión de Ceuta y Melilla
- [Correos: Importación en Península y Baleares](https://www.correos.es/es/es/atencion-al-cliente/informacion-aduanera/dua-importacion/importacion-en-peninsula-y-baleares) – declaración simplificada H7 hasta 150 €, DUA ordinario sobre 150 € y pago en correosaduanas.es, app o efectivo
- [Correos: Recibir envíos](https://www.correos.es/es/es/particulares/recibir) – Aviso de Llegada, plazo de 15 días en oficina, recordatorios a 10 y 5 días, autorización a terceros y Correos Modify
- [Correos: Oficina o Citypaq](https://www.correos.es/es/es/actualidad/2026/oficina-o-citypaq--elige-el-metodo-de-recogida-que-mejor-se-adap) – plazo de 5 días naturales para recoger en Citypaq
- [Correos: Phishing y seguridad de la información](https://www.correos.es/es/es/atencion-al-cliente/seguridad-de-la-informacion/phishing) – Correos nunca pide datos personales, bancarios ni pagos por correo electrónico o SMS
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formatos reconocidos, estados de Royal Mail, consulta cada hora y traspaso a Correos en Peek
:::
