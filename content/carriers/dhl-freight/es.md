---
title: Seguimiento DHL Freight: ActiveTracing, estados y ayuda
description: Sigue un envío europeo por carretera o tren de DHL Freight en ActiveTracing: placas JND, códigos de pedido, ID de envío, SSCC, citas y soporte.
slug: seguimiento-dhl-freight
published: 2026-11-24
updated: 2026-11-24
---

DHL Freight (*DHL Freight GmbH*, parte de DHL Group) es la división europea de transporte de mercancías por carretera y ferrocarril de DHL, con filiales en más de 40 países y más de 45 millones de toneladas movidas al año en sus redes LTL (carga fraccionada), PTL (carga parcial), FTL (carga completa), temperatura controlada, alta seguridad y ferrocarril europeo. Además de carga paletizada B2B (como *EuroConnect*), DHL Freight también entrega pedidos residenciales voluminosos (*DHL Home Delivery*) y envíos nacionales suecos de *DHL Service Point*. Puedes consultar un envío en [DHL ActiveTracing](https://activetracing.dhl.com/DatPublic/datSelection.do) o pegar tu referencia en el recuadro de arriba.

## Números de seguimiento de DHL Freight: códigos de pedido, ID de envío y placas JND

DHL ActiveTracing admite varios tipos de referencia y mantiene el historial de envíos disponible online durante **seis meses**:

| Formato (inventado) | Qué es |
| --- | --- |
| `JND48291736405829173` | **DHL Package ID / License Plate** de 20 caracteres (`JND` + 17 dígitos). |
| `FRA-EC-4829173` | **Código de pedido DHL (Order code)**: código de terminal de 3 letras + código de producto de 2 letras (como `EC` para EuroConnect) + 7 dígitos (con o sin guiones). |
| `4829173640` | **ID de envío (Consignment ID)**: 9, 10 u 11 dígitos (los de 10 dígitos son habituales en envíos nacionales suecos *DHL Service Point*). Comparte la longitud de 10 dígitos con [DHL](carrier:dhl), [Mondial Relay](carrier:mondial-relay), [Relais Colis](carrier:relais-colis) y [Packeta](carrier:packeta). |
| `00348291736405829177` | Identificador de bulto **GS1 SSCC** de 20 dígitos que empieza por `00` (dígito de control GS1 válido). Comparte la estructura SSCC de 20 dígitos con [USPS](carrier:usps) y [NZ Post](carrier:nz-post). |

Ten en cuenta que la paquetería estándar en Alemania usa [DHL](carrier:dhl) (*DHL Paket*) y los documentos o paquetes urgentes aéreos internacionales usan [DHL Express](carrier:dhl-express), mientras que los palés, muebles pesados y carga europea por carretera o ferrocarril se siguen en el portal ActiveTracing de DHL Freight. Consulta [formatos de números de seguimiento](guide:tracking-number-formats).

## Qué significan los estados de DHL Freight ActiveTracing

ActiveTracing muestra en cada fila una frase de estado, en inglés o alemán, la terminal (por ejemplo `DE-KOBLENZ`) y la fecha y hora de la terminal, sin indicar zona horaria:

| DHL Freight indica (EN / DE) | Qué significa para ti | Peek muestra |
| --- | --- | --- |
| `Consignment created`, `Collection arranged - OK`, `Out for Collection`, `Not Collected - ...` / `Auftrag erfasst`, `In Abholung` | Orden de carga registrada o conductor enviado para recoger; la mercancía sigue en manos del remitente. | «Etiqueta creada» |
| `Collected`, `Shipment handover by sender` / `Abholung` | Recogido al remitente o entregado en un DHL Service Point sueco. | «Admitido por el transportista» |
| `Received at terminal`, `Shipped from terminal`, `Arrival at destination`, `Loaded into ferry`, `Direct delivery`, `Receiver notified`, `Shipment in warehouse - Awaiting delivery appointment` / `Delivery appointment booked` / `Empfangen im Terminal`, `Verladen`, `Avisierung` | Circulando entre terminales de DHL Freight, cruzando en ferri, despachado de aduana (`Customs cleared`) o retenido en la terminal de destino mientras se acuerda una cita de entrega. | «En tránsito» |
| `On hold - in customs clearance` | Retenido para el despacho aduanero transfronterizo (por ejemplo Suiza, Reino Unido o Noruega). | «En aduanas» |
| `Out for Delivery` / `In Zustellung` | Cargado en el camión local para entrega hoy. | «En reparto» |
| `Shipment in warehouse - ready for self collection by consignee` | Listo para recogida por el destinatario en la terminal local de DHL Freight. | «Listo para recoger» |
| `Attempted DHL delivery - ...` | Intento de entrega fallido (por ejemplo destinatario cerrado o rechazo). | «Intento de entrega» |
| `Shipment in warehouse - waiting for consignee payment`, `Shipment delivered with exception - partly delivered`, `Exception occurred - Strike`, `New shipping instruction - consignment cancelled by sender` | Retención por pago, entrega parcial con bultos faltantes, incidencia por huelga o cancelación por el remitente. | «Incidencia» |
| `New shipping instruction - shipment returned` | Mercancía devuelta al remitente según nuevas instrucciones. | «Devuelto al remitente» |
| `Delivered`, `Received by consignee`, `Picked up by receiver`, `Shipment delivered with exception` / `Zugestellt` | Entregado al destinatario o recogido en terminal / Service Point. | «Entregado» |

## Citas de entrega (*Avisierung*), aduanas y normas de carga

Según [DHL Freight Alemania](https://www.dhl.com/de-en/home/freight.html) y el [Centro de ayuda de DHL Freight](https://www.dhl.com/de-en/home/freight/help-center-for-european-road-and-rail.html):

- **Citas de entrega (*Avisierung*):** un envío puede permanecer en la terminal de entrega en `Shipment in warehouse - Awaiting delivery appointment` hasta pactar un día de entrega con el destinatario; cuando se fija, aparece `Delivery appointment booked`. Si queda a la espera, pregunta al remitente o a la terminal de destino.
- **Historial de seis meses en ActiveTracing:** [activetracing.dhl.com](https://activetracing.dhl.com/DatPublic/datSelection.do) muestra el historial de envíos de los **últimos seis meses** en inglés, alemán, español, francés, sueco, polaco e italiano.
- **Aduanas transfronterizas europeas:** el transporte por carretera entre la UE y países vecinos no comunitarios (como Reino Unido, Suiza o Noruega) pasa por aduana (`On hold - in customs clearance`). En pedidos de comercio electrónico procedentes de fuera de la UE que entran en la UE se aplican el derecho de aduana de 3 € en envíos de hasta 150 € y la tasa de gestión comunitaria de 2 € conforme a la [reforma aduanera de la UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en); consulta [paquetes retenidos en aduanas](guide:customs).

## Envío de DHL Freight retrasado, dañado o entregado parcialmente

Quien contrató el transporte con DHL Freight es el remitente, así que contacta primero con el vendedor o cargador.

- **Daños visibles o bultos faltantes (`partly delivered`):** revisa la mercancía y el número de bultos al recibirlos y avisa de inmediato al remitente. El Centro de ayuda de DHL Freight incluye una guía *How to Prepare a Claim*.
- **A la espera de cita o pago:** si ActiveTracing indica `Awaiting delivery appointment` o `waiting for consignee payment`, contacta enseguida con la terminal de destino indicada en el último escaneo (o con el remitente).
- Consulta [por qué el seguimiento deja de actualizarse](guide:tracking-not-updating), [entregado pero no recibido](guide:delivered-not-received), [paquete dañado](guide:damaged-parcel), [paquete perdido](guide:lost-parcel) y [devuelto al remitente](guide:returned-to-sender).

## Atención al cliente y contacto de DHL Freight

Canales de soporte publicados en el [Centro de ayuda de DHL Freight](https://www.dhl.com/de-en/home/freight/help-center-for-european-road-and-rail.html) y [DHL Freight Alemania](https://www.dhl.com/de-en/home/freight.html):

- **Centro de ayuda para carretera y ferrocarril en Europa:** [dhl.com/de-en/home/freight/help-center-for-european-road-and-rail.html](https://www.dhl.com/de-en/home/freight/help-center-for-european-road-and-rail.html) — preguntas frecuentes, *How to Prepare a Claim*, Incoterms 2020, condiciones generales y normas de mercancías peligrosas.
- **Viva y buscador de terminales:** el Centro de ayuda ofrece **Viva**, el asistente de atención al cliente con IA de DHL Freight, y *Freight Terminals and Locations* para localizar terminales y oficinas por país.
- **Portal para empresas:** *myDHLFreight*, con vídeos tutoriales y preguntas frecuentes en el Centro de ayuda.

> Ignora los enlaces de SMS o correo no solicitados que pidan un pago online inesperado para «liberar» un envío de DHL Freight. Verifica cualquier estado directamente en [activetracing.dhl.com](https://activetracing.dhl.com/DatPublic/datSelection.do) o con la terminal de destino de DHL Freight. Consulta [estafas por SMS de envíos](guide:delivery-scam-texts).

## Seguir un envío de DHL Freight con Peek

[Peek](/) consulta a DHL Freight ante una placa `JND` (`JND48291736405829173`, inventado), el único transportista al que pregunta por ella, y muestra «DHL Freight tiene este paquete.» en cuanto ActiveTracing responde. Para un ID de envío de 10 dígitos (`4829173640`), Peek consulta a DHL Freight entre los transportistas de diez dígitos cuando rastreas desde Alemania, Suecia, Austria, Polonia, Países Bajos, Bélgica, Francia, Italia, España o Reino Unido, y solo como último recurso desde otros países. Peek también reconoce un enlace pegado de `activetracing.dhl.com/DatPublic/datSelection.do` (`a=`, `valuePackageField=` o `valueShipmentOrderField=`). Para un código de pedido (`FRA-EC-4829173`), un ID de envío de 9 u 11 dígitos o un SSCC (`00348291736405829177`), pulsa «Cambiar» al añadirlo o «Cambiar transportista» en la pantalla del paquete y selecciona DHL Freight (buscar «DHL ActiveTracing», «DHL Frachtverfolgung» o «DHL Freight GmbH» también lo encuentra).

Peek lee directamente la página en inglés de ActiveTracing y muestra la fase («Etiqueta creada», «Admitido por el transportista», «En tránsito», «En aduanas», «En reparto», «Listo para recoger», «Intento de entrega», «Incidencia», «Devuelto al remitente», «Entregado»), las frases de estado de ActiveTracing con su terminal (como `KOBLENZ, DE`), el peso bruto y el país de destino. Peek fecha cada fila en la zona horaria de su terminal y la muestra en la hora de tu dispositivo. El nombre del servicio queda fuera de todas las apps de Peek. La localidad del destinatario, la única parte de la dirección que muestra ActiveTracing, puede cerrar la ruta en tu propio mapa mientras el envío está en camino; los enlaces compartidos no la muestran. Peek omite el origen, el número de bultos, el volumen, la fecha de salida, la hora de entrega independiente, otras referencias, el comprobante de entrega y la firma; ActiveTracing no ofrece fecha estimada de entrega, códigos de estado ni nombres de remitente o destinatario.

Mientras tengas el paquete abierto en Peek, o el día de entrega con las notificaciones activadas, Peek comprueba hasta cada 10 minutos; el día en que ActiveTracing muestra `Out for Delivery`, cada 2 minutos (cada 5 tras una comprobación que abrió un navegador) desde las 6:00 en la hora del lugar del último escaneo, mientras ActiveTracing responda. En el resto de casos comprueba cada hora. Tras 12 horas solo en «Etiqueta creada» o «Admitido por el transportista», o tras dos días sin novedades, pasa a comprobar cada hora sea cual sea tu seguimiento, y cada 6 horas tras siete días en «Etiqueta creada». Cuando ActiveTracing no responde y Peek sigue el envío únicamente a través de servicios de seguimiento, comprueba cada hora, cada 3 horas tras un día tranquilo y hasta cada 15 minutos el día de reparto mientras tengas el paquete abierto o las notificaciones activadas.

Cuando el último escaneo tiene cuatro días y el paquete sigue en «Etiqueta creada», «Admitido por el transportista» o «En tránsito», Peek lo señala con el día de ese escaneo, por ejemplo «Sin novedades desde el lun 12 oct». La página del paquete muestra entonces «Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a DHL Freight.», con un enlace «Pide ayuda a DHL Freight» al Centro de ayuda de DHL Freight. Tras tres semanas sin ningún escaneo nuevo, dice «Nada nuevo desde hace más de tres semanas. Si el paquete aún no ha llegado, contacta con el vendedor. Peek sigue preguntando a DHL Freight.»

Puedes elegir avisos para «Cada escaneo», «Pasos importantes» o «Solo la entrega». Peek solo supervisa las actualizaciones de seguimiento y no puede concertar una cita de entrega, pagar portes ni contactar con una terminal de DHL Freight en tu nombre.

## Preguntas frecuentes sobre el seguimiento de DHL Freight

### ¿Qué diferencia hay entre DHL Freight, DHL Parcel y DHL Express?

DHL Freight gestiona el transporte europeo por carretera y tren (palés, LTL/PTL/FTL, *DHL Home Delivery* voluminoso y carga nacional en Suecia) seguido en [ActiveTracing](https://activetracing.dhl.com/DatPublic/datSelection.do), mientras que la paquetería estándar usa [DHL](carrier:dhl) y la mensajería aérea internacional urgente con plazo definido usa [DHL Express](carrier:dhl-express).

### ¿Qué números de seguimiento funcionan en DHL ActiveTracing?

ActiveTracing acepta códigos de pedido DHL (`FRA-EC-4829173`, inventado), ID de envío de 9 a 11 dígitos (`4829173640`), identificadores de bulto / License Plates `JND` de 20 caracteres (`JND48291736405829173`) y números GS1 SSCC de 20 dígitos (`00348291736405829177`).

### ¿Por qué está mi envío retenido en `Awaiting delivery appointment`?

La terminal de entrega mantiene la mercancía a la espera hasta acordar un día de entrega con el destinatario. Consulta al remitente o a la terminal que aparece en la última fila de seguimiento.

### ¿Durante cuánto tiempo conserva DHL ActiveTracing el historial?

[ActiveTracing](https://activetracing.dhl.com/DatPublic/datSelection.do) indica que los datos de cada envío permanecen disponibles en línea durante los **últimos seis meses**.

:::sources
- [DHL Freight: ActiveTracing](https://activetracing.dhl.com/DatPublic/datSelection.do) – consulta por ID de envío, código de pedido y Package ID / License Plate, historial de 6 meses, *DHL Freight GmbH*
- [DHL Freight: página de Alemania (inglés)](https://www.dhl.com/de-en/home/freight.html) – más de 40 países, más de 45 M de toneladas/año, LTL/PTL/FTL, GoGreen Plus Flex, Rail Freight Europe y red de terminales
- [DHL Freight: Centro de ayuda para carretera y tren en Europa (inglés)](https://www.dhl.com/de-en/home/freight/help-center-for-european-road-and-rail.html) – asistente IA Viva, buscador de terminales, How to Prepare a Claim, Incoterms 2020 y myDHLFreight (URL de ayuda enlazada por Peek)
- [Comisión Europea: reforma aduanera de la UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – derecho de aduana de 3 € hasta 150 € y tasa de gestión comunitaria de 2 €
- [Universal Parcel Scraper: dhl-freight](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/dhl-freight) – análisis de `JND`, código de pedido, ID de envío de 9–11 dígitos y SSCC, reglas de enlaces `activetracing.dhl.com`, mapa de estados inglés/alemán, hora local de cada terminal, peso y país de destino leídos, y URL de ayuda
:::
