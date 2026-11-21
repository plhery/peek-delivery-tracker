---
title: Seguimiento Morning Global: códigos BR…MG, estados y ayuda
description: Rastrea un paquete de Morning Global desde Hong Kong o China continental: formato BR…MG, estados en inglés, entrega local, aduanas y contacto.
slug: seguimiento-morning-global
published: 2026-11-21
updated: 2026-11-21
---

Morning Global (MG), fundada en 1987, es la división internacional de Morning Express Group, un grupo de mensajería de Hong Kong. Envía paquetes al extranjero para tiendas online y marketplaces, cuenta con oficinas en Hong Kong y por toda China continental, y suele entregar el paquete a un socio local en tu país para el reparto final. Consulta el número que te facilite la tienda en la [página Trace & Track de Morning Global](https://www.morninglobal.com/trace-track/), en inglés, o pégalo en el cuadro superior.

## Número de seguimiento de Morning Global: cómo es

Morning Global no publica el formato de sus códigos. El catálogo de Peek reconoce un esquema para este operador (cifras inventadas):

| Número (inventado) | Qué es |
| --- | --- |
| `BR482910573MG` | `BR`, nueve dígitos y `MG`: un código de Morning Global. Como ese esquema encaja también con el formato postal internacional, Peek consulta primero a Morning Global antes de asignarlo (y si el noveno dígito supera además el dígito de control postal, como `BR482910576MG`, Peek pregunta a Morning Global y a [Chronopost](carrier:chronopost)). |
| `BR48291057MG` | Solo ocho dígitos: no tiene la longitud de Morning Global y Peek no lo vincula con él. Comprueba si falta una cifra. |

Las minúsculas, los espacios y los guiones no importan en Peek. El buscador de Morning Global admite hasta 20 códigos a la vez, separados por comas.

**Dónde encontrarlo.** En el correo de confirmación de envío de la tienda o en la página de tu pedido ([cómo encontrar el número de seguimiento](guide:find-tracking-number) y [seguimiento de paquetes desde China](guide:tracking-from-china)).

**Otros formatos.** Una vez seleccionado Morning Global manualmente, Peek consulta su rastreador para cualquier código de entre 8 y 40 caracteres que mezcle letras y dígitos (con otro código indica que «no emite números como este»).

**Dos servicios.** Morning Global ofrece *MG International Express (MGIE)*, gestionado por ella misma puerta a puerta, y el servicio más económico *MG International Postal (MGIP)* a través de centros postales internacionales. Entre ambos cubren más de 220 destinos para envíos de hasta 30 kg.

## Estados de seguimiento de Morning Global explicados

El rastreador de Morning Global muestra las etapas bajo `Shipment Journey`, cada una con estado, a menudo descripción, fecha, hora y lugar — frecuentemente el código de país con su desplazamiento UTC, como `CA (UTC-4)`. En el orden habitual del trayecto:

| Morning Global indica | Qué significa para ti | Peek muestra |
| --- | --- | --- |
| `Order Created` | La tienda registró el envío; Morning Global todavía no tiene el paquete. | «Etiqueta creada» |
| `Collection` | Morning Global recogió el paquete en la tienda. | «Admitido por el transportista» |
| `Arrive Hub` | En un centro logístico de Morning Global. | «En tránsito» |
| `Depart Origin` | Salió del centro de origen donde empieza su ruta. | «En tránsito» |
| `Depart Hub` | Salió de un centro logístico (texto procedente del antiguo buscador de Morning Global). | «En tránsito» |
| `Ready for Departure` | Listo para salir hacia tu país. | «En tránsito» |
| `Flight Departure to Destination` | El vuelo hacia tu país ha despegado. | «En tránsito» |
| `Arrive Destination` | Ha llegado a tu país; aún faltan la aduana y la entrega al socio local. | «En tránsito» |
| `Customs Clearance Begins` | La aduana de tu país está tramitando el envío. | «En aduanas» |
| `Customs Cleared` | La aduana ha autorizado el paso del paquete. | «En tránsito» |
| `Handed Over for Delivery` | Entregado al colaborador que reparte en tu país; aún no va en la furgoneta de reparto. | «En tránsito» |
| `Out for Delivery` | En ruta de reparto hoy. | «En reparto» |
| `Delivered` | Entregado. | «Entregado» |

Cuando la descripción añade información al estado, Peek muestra `estado: descripción`, suprimiendo los números de teléfono. Los textos fuera de esta tabla se clasifican con las reglas generales de Peek o se muestran sin cambio de etapa.

**No encontrado.** Para un código desconocido, el rastreador de Morning Global contesta `No shipment record was found for this tracking number.`

## Quién entrega los paquetes de Morning Global

Con `Handed Over for Delivery`, Morning Global traspasa el envío al socio encargado de repartirlo en tu país. Su web no publica qué empresas son y su rastreador tampoco facilita a Peek ni el nombre del socio ni su código de seguimiento local: para cambiar una entrega, pide ese código a la tienda o al soporte de Morning Global.

**Red propia.** Morning Global incluye a Reino Unido y Estados Unidos entre sus sedes propias, con sedes regionales en Londres (Reino Unido y Europa), Los Ángeles (América) y Cantón en China continental, además de oficina en Ámsterdam; Canadá figura entre sus ubicaciones clave junto a EE. UU. y Australia en su red de socios. No publica normas propias sobre intentos de entrega ni puntos de recogida: rigen las del operador final ([entrega fallida](guide:missed-delivery), [paquetes no recogidos](guide:uncollected-parcel)).

**Aduanas en España y otros destinos.** En **España** (Unión Europea), las compras online enviadas desde fuera de la UE con valor de hasta 150 € pagan desde el 1 de julio de 2026 un arancel provisional de 3 € por artículo (bienes con igual clasificación arancelaria, descripción y, cuando proceda, origen; por encima de 150 € se aplica el arancel ordinario) y, desde el 1 de noviembre de 2026, una tasa de tramitación de la Unión de 2 € por artículo cualquiera que sea su valor. En **Península y Baleares** se añade el IVA de importación salvo que ya se recaudara en la compra mediante IOSS; **Canarias** forma parte del territorio aduanero de la UE pero está fuera del territorio de IVA de la UE, y **Ceuta y Melilla** quedan fuera tanto del territorio aduanero como del territorio de IVA de la UE. El 22 de octubre de 2026, Morning Global comunicó a sus comercios clientes que desde el 1 de noviembre de 2026 se aplica una `EUR 2 Customs Handling Fee` por cada línea de declaración aduanera en envíos B2C hacia la UE (tanto en IOSS como en DAP, incluidos los superiores a 150 €), más el IVA sobre dicha tasa donde el país de destino lo exija, sin indicar si las tiendas la repercuten al comprador. En **Latinoamérica** y **Estados Unidos**, lo que debas pagar depende de la normativa de cada país ([paquetes retenidos en aduanas](guide:customs)).

**Documentación aduanera y avisos de demora.** Si aduanas solicita copia de documento de identidad o factura, Morning Global dispone del formulario [Upload Clearance Documents](https://www.morninglobal.com/upload-clearance-documents/) con el número de seguimiento: úsalo únicamente dentro de `morninglobal.com` ([mensajes falsos de entrega](guide:delivery-scam-texts)). Las incidencias de red se publican en su sección *News* (como el aviso del 30 de octubre de 2026 sobre demoras de unos 2 a 3 días hábiles en MGIP hacia Australia por huelgas).

## Paquete de Morning Global sin actualizar, perdido o devuelto

Los clientes de Morning Global son los comercios remitentes: ante retrasos, pérdidas, daños o devoluciones, escribe primero a la tienda o al marketplace.

**Aún no encontrado.** Si el transportista aún no está confirmado pero el código tiene forma `BR…MG` (sin dígito de control postal), Peek indica «Probablemente Morning Global» y «Esperando el primer escaneo de Morning Global» mientras sigue consultando; si además cumple el dígito de control postal (`BR482910576MG`), Peek pregunta a Morning Global y a [Chronopost](carrier:chronopost) y asigna al que reconozca el envío. Guardado ya bajo Morning Global, muestra «Esperando a Morning Global». Si pasa una semana sin datos, revisa el número con la tienda.

**Parado en `Order Created` o días sin avances.** `Order Created` significa que el comercio aún no entregó el paquete. En tránsito, tras cuatro días sin líneas nuevas, Peek indica el día de la última línea («Sin novedades desde el lun 12 oct»); después de `Handed Over for Delivery`, pide el código del socio local ([por qué no se actualiza el seguimiento](guide:tracking-not-updating)).

**Perdido, dañado, rechazado o entregado sin aparecer.** Las condiciones de Morning Global dan al comercio 30 días desde la admisión para notificar una reclamación y 90 días para aportar documentos, limitando la indemnización al valor declarado con un tope de 50 US$ por paquete cuando exista culpa de Morning Global; las entregas fallidas por dirección errónea o incompleta, ausencia o rechazo cuentan como pedido completado frente al comercio ([paquete perdido](guide:lost-parcel), [paquete dañado](guide:damaged-parcel), [devuelto al remitente](guide:returned-to-sender)). Si figura `Delivered` pero no llegó nada, mira `Signed By` en el rastreador de Morning Global (omitido por Peek), pregunta a vecinos y avisa al vendedor ([entregado pero no recibido](guide:delivered-not-received)).

## Atención al cliente y contacto de Morning Global

Para consultas sobre envíos en la [página de contacto de Morning Global](https://www.morninglobal.com/contact/) (facilita tu código de seguimiento; no publica horarios):

- **Reino Unido** (sede regional en Londres): `cs.mgeu@morninglobal.com`, `+44 20 7019 9035`.
- **Estados Unidos** (sede regional en Los Ángeles): `cs.mgus@morninglobal.com`, `+1 888 981 8688`.
- **Hong Kong y Macao** (sede mundial en Hong Kong): `cs.mghk@morninglobal.com`, `+852 3669 6360`.
- **China continental** (sede regional en Cantón): `cs.mgchina@morninglobal.com`, `+86 4008 268 216`.
- **Otros equipos regionales**: Italia (`cs.mgit@morninglobal.com`), India, Filipinas y Singapur cuentan con correo propio en la misma página.
- **Equipo mundial**: `cs.mgworldwide@morninglobal.com`, WhatsApp `+1 (479) 777 3307` (o código QR) y formulario web (nombre, correo, empresa y mensaje).

**Cuidado con los SMS falsos.** El dominio oficial es `www.morninglobal.com` (`morninglobal.com`). Revisa [mensajes falsos de entrega](guide:delivery-scam-texts) antes de abrir enlaces recibidos por SMS o correo.

## Seguir un paquete de Morning Global con Peek

[Peek](/) no adjudica Morning Global solo por ver un código `BR…MG`, pero sigue a más de 3.500 transportistas: pégalo, verás «Comprobando servicios de seguimiento…» y «Morning Global tiene este paquete.» cuando su rastreador lo reconozca. Un enlace de `www.morninglobal.com` con `tracking_number=` o `ydh=` lo archiva al instante en Morning Global («Transportista detectado»). Si aún no aparece, pulsa «Cambiar» y elige Morning Global en «Encaja con este número» o «Todos los transportistas» (o «Cambiar transportista» en un paquete ya añadido).

Peek lee el rastreador propio de Morning Global sin código postal: muestra el estado, cada línea con su lugar, el país de destino y la hora de entrega, interpreta la hora con el desplazamiento UTC indicado junto al país (y sin él, con el reloj del lugar, de un paso vecino o de Hong Kong) y convierte todo a la zona de tu dispositivo («Horas en tu zona horaria: …»). Descarta origen, `Estimated Delivery`, peso, bultos, `Reference` y `Signed By` (no muestra fecha estimada), y sigue el envío en Morning Global hasta el final sin consultar al correo de tu país.

Peek comprueba como máximo una vez por hora (también en «En reparto») y espera 2 horas tras un fallo de consulta (igual al actualizar a mano). Puedes elegir «Cada escaneo», «Pasos importantes» (`Customs Clearance Begins`, `Out for Delivery` y `Delivered`) o «Solo la entrega». Peek no puede contactar con Morning Global ni con la tienda por ti.

## Preguntas frecuentes sobre el seguimiento de Morning Global

### ¿Es fiable Morning Global?

Sí. Fundada en 1987, Morning Global es la división internacional del grupo de mensajería de Hong Kong Morning Express Group, con sede mundial en Hong Kong y operaciones propias en 14 países para marcas, minoristas y marketplaces.

### ¿Cómo rastreo un paquete de Morning Global?

Pega el número facilitado por la tienda en el cuadro superior o introdúcelo en `morninglobal.com/trace-track/` (admite hasta 20 números separados por comas).

### ¿Qué significa `Handed Over for Delivery` en Morning Global?

Morning Global entregó el paquete tras la aduana al socio encargado del reparto en tu país. Todavía no va en reparto domiciliario: después llega `Out for Delivery`.

### ¿Quién reparte los paquetes de Morning Global?

Un operador colaborador en tu país, cuyo nombre no aparece en la web ni en el rastreador de Morning Global. Pide su número de seguimiento a la tienda o al soporte de Morning Global.

### ¿Cómo contacto con Morning Global?

Escribe indicando tu código de seguimiento a `cs.mgeu@morninglobal.com` (Reino Unido), `cs.mgus@morninglobal.com` (EE. UU.) o `cs.mgworldwide@morninglobal.com` (resto del mundo, también por WhatsApp en el `+1 (479) 777 3307`). Sobre tu compra, acude primero al vendedor.

:::sources
- [Morning Global: Trace & Track](https://www.morninglobal.com/trace-track/) – rastreador de hasta 20 códigos, Shipment Journey, Estimated Delivery, Reference, Signed By y aviso de no encontrado
- [Morning Global: Home](https://www.morninglobal.com/) – fundación en 1987, presencia propia en 14 países y avisos
- [Morning Global: About Us](https://www.morninglobal.com/about-us/) – división internacional de Morning Express Group en Hong Kong
- [Morning Global: Services](https://www.morninglobal.com/services/) – servicios MGIE y MGIP, más de 220 destinos y hasta 30 kg
- [Morning Global: Global Network](https://www.morninglobal.com/global-network/) – ubicaciones propias (Reino Unido, EE. UU.), Canadá y red asociada (Australia)
- [Morning Global: Contact](https://www.morninglobal.com/contact/) – correos y teléfonos regionales, WhatsApp mundial, formulario y sedes de Hong Kong, Cantón (China continental), Londres y Los Ángeles
- [Morning Global: Upload Clearance Documents](https://www.morninglobal.com/upload-clearance-documents/) – formulario para subir documentos aduaneros
- [Morning Global: Terms and Conditions (Privacy Policy)](https://www.morninglobal.com/privacy-policy/) – entregas fallidas, plazos de reclamación de 30 / 90 días y límite de 50 US$
- [Morning Global: EUR 2 Customs Handling Fee for EU-bound B2C Shipments](https://www.morninglobal.com/morning-global-service-update-introduction-of-eur-2-customs-handling-fee-for-eu-bound-b2c-shipments-from-1-november-2026/) – cargo de 2 € por línea de declaración desde el 1 de noviembre de 2026 e IVA aplicable
- [Morning Global: Extended Transit Times in Australia](https://www.morninglobal.com/morning-global-service-impact-notice-extended-transit-times-in-australia-due-to-industrial-action/) – demora de unos 2 a 3 días hábiles en MGIP hacia Australia
- [Morning Global: News](https://www.morninglobal.com/news/) – comunicados de servicio
- [Comisión Europea: EU Customs Reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – arancel de 3 € hasta 150 € desde julio de 2026 y tasa de 2 € desde noviembre de 2026
- [Comisión Europea: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – concepto de artículo, tasa de 2 € cualquiera que sea el valor e IOSS
- [Comisión Europea: Ámbito territorial del IVA y de la unión aduanera](https://taxation-customs.ec.europa.eu/taxation/vat/vat-directive/how-does-vat-work/territorial-scope_en) – Canarias fuera del territorio de IVA de la UE y Ceuta y Melilla fuera del territorio aduanero y de IVA de la UE
- [Universal Parcel Scraper: Morning Global](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/morning-global) – esquema BR…MG, enlaces, estados, relojes, límite horario y campos descartados
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – comprobación S10 junto a Chronopost y seguimiento sin traspaso postal
:::
