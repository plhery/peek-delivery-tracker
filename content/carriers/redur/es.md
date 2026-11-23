---
title: Seguimiento Redur: códigos de 14 dígitos, estados y ayuda
description: Sigue un envío de Redur en España, Portugal y Europa: números de 14 dígitos, estados de agencia y reparto, recogida en delegación, Eurodis y contacto.
slug: seguimiento-redur
published: 2026-11-23
updated: 2026-11-23
---

**Redur** (*Redur S.A.*) es un operador español de transporte urgente y logística integral con más de 50 años de trayectoria y sede central en Algete, Madrid ([redur.es](https://redur.es/)). Con una red 100 % propia de más de 61 delegaciones y 3.000 profesionales en la península, las islas y Portugal, Redur es además socio fundador de **Eurodis**, la red europea de entregas combi-freight (paquetería y palets). Junto al reparto urgente doméstico y las salidas diarias internacionales a Europa, ofrece distribución farmacéutica a temperatura controlada (*Farma 15-25*) y logística integral. Puedes consultar tu envío en [redur.es/track-trace/](https://redur.es/track-trace/) o pegar tu número de 14 dígitos o enlace de seguimiento en el cuadro de arriba.

## Números de seguimiento de Redur (formato de 14 dígitos)

El número de seguimiento (*N° Tracking*) de Redur consta de 14 dígitos numéricos sin letras ni espacios:

| Formato (inventado) | Qué es |
| --- | --- |
| `58492716380419` | Número de seguimiento estándar de Redur de 14 dígitos (*NTRACKING*). |
| `61738492051628` | Otro ejemplo de código de 14 dígitos de Redur para rutas en España, Portugal o Eurodis. |

En [redur.es/track-trace/](https://redur.es/track-trace/) también se puede buscar por número de expedición o referencia del cliente, pero el seguimiento automático de Peek trabaja con el número de 14 dígitos (`NTRACKING`) o un enlace directo `redur.es/track-trace/?...&valor=`. Como los códigos de 14 cifras coinciden en longitud con otros transportistas europeos (como [SEUR](carrier:seur) o DPD), pegar el enlace de Redur o elegir Redur manualmente permite consultarlo al instante. Consulta los [formatos de números de seguimiento](guide:tracking-number-formats).

## Qué significan los estados de seguimiento de Redur

El rastreador de Redur muestra una barra de progreso de 7 pasos junto a cada movimiento registrado por delegación:

| Redur indica | Qué significa para ti | Peek muestra |
| --- | --- | --- |
| `DOCUMENTACION` (`DATA ENTERING`) | El remitente ha transmitido los datos electrónicos del envío antes de su entrada física en almacén (solo con fecha). | "Etiqueta creada" |
| `ENTRADA PLAZA ORIGEN` (`INBOUND ORIGIN DEPOT`) | Primera lectura física del envío en la delegación de origen de Redur. | "Admitido por el transportista" |
| `SALIDA PLAZA ORIGEN`, `ENTRADA/SALIDA PLAZA TRANSITO`, `ENTRADA PLAZA DESTINO` | En tránsito entre delegaciones de origen, plataforma intermedia y plaza de destino en España o Portugal. | "En tránsito" |
| `CARGA CAMION T. INTERNACIONAL` | Cargado en camión de línea internacional Eurodis rumbo a otro país europeo. | "En tránsito" |
| `PENDIENTE DE FECHA CONCERTADA`, `FECHA DE ENTREGA CONCERTADA` | A la espera de acordar día de entrega con el destinatario o con cita de entrega ya confirmada. | "En tránsito" |
| `SALIDA A REPARTO` (`OUT FOR DELIVERY`) | En el vehículo de reparto local de Redur para su entrega hoy. | "En reparto" |
| `RECOGERÁN EN AGENCIA` | Disponible en la delegación de Redur de destino para recogida por el destinatario. | "Listo para recoger" |
| `DESTINATARIO AUSENTE`, `INCORRECT ADDRESS`, `CERRADO PERMANENTE/TEMPORAL` | Intento fallido por ausencia del destinatario, dirección incorrecta o local cerrado. | "Intento de entrega" |
| `DEVOLUCIÓN A ORIGEN` | En devolución hacia el remitente tras intentos fallidos o rechazo. | "Devuelto al remitente" |
| `ENTREGA` (`DELIVERY`) | Entregado al destinatario (o confirmado mediante el último paso activo de la barra de progreso). | "Entregado" |

## Cobertura en España, Portugal y red europea Eurodis

Según [Redur](https://redur.es/) y [Servicios de Redur](https://redur.es/servicios/):

- **Servicio Doméstico:** distribución urgente de paquetería, palets y combi-freight en España (península e islas) y Portugal mediante más de 61 delegaciones propias, con avisos por e-mail y SMS (*Redur Connected*) y gestión en la plataforma *Maia*.
- **Servicio Internacional:** salidas diarias B2B y B2C a toda Europa a través de **Eurodis**. Cuando aparece `CARGA CAMION T. INTERNACIONAL`, un socio local de Eurodis asume la entrega final en el país de destino.
- **Farma 15-25:** transporte farmacéutico a temperatura controlada 15–25 °C bajo normativa GDP.

## Ausencias, entrega concertada e incidencias

- **`PENDIENTE DE FECHA CONCERTADA`:** habitual en mercancía voluminosa, paletizada o entregas a empresas donde Redur necesita pactar día antes de sacar el envío a reparto. Revisa tu teléfono o correo por si llegó un aviso de *Redur Connected*, o llama a Redur con tu número de 14 dígitos.
- **`DESTINATARIO AUSENTE`, `INCORRECT ADDRESS` o `RECOGERÁN EN AGENCIA`:** contacta cuanto antes con el vendedor o con Redur facilitando tu código de 14 dígitos para corregir la dirección, reprogramar la entrega o confirmar el horario de recogida en delegación (lleva tu DNI).
- **Retrasos, daños o pérdida:** avisa siempre primero a la tienda o remitente (titular del contrato de transporte y responsable de abrir reclamación formal) y verifica con Redur la delegación donde se encuentra el bulto. Consulta [paquete entregado pero no recibido](guide:delivered-not-received), [paquete dañado](guide:damaged-parcel), [paquete perdido](guide:lost-parcel), [paquete devuelto al remitente](guide:returned-to-sender) y [por qué el seguimiento deja de actualizarse](guide:tracking-not-updating).

## Atención al cliente y sede central de Redur

Datos de contacto oficiales publicados en [Contacto Redur](https://redur.es/contacto/) y [Servicios](https://redur.es/servicios/):

- **Teléfono central (España):** `(+34) 916 20 46 10` (fax `91 628 15 60`).
- **Correo electrónico:** `info@redur.es`.
- **Formulario de contacto y red de delegaciones:** [redur.es/contacto/](https://redur.es/contacto/).
- **Servicios Centrales:** *Redur S.A.*, Carretera Algete–Madrid Km 2,5 (P.I. Redur), 28110 Algete, Madrid, España.

> Desconfía de SMS fraudulentos que pidan un pequeño pago online para reprogramar una entrega en España o Portugal. Comprueba siempre cualquier aviso directamente en [redur.es/track-trace/](https://redur.es/track-trace/) o con tu tienda. Consulta [estafas por SMS de entrega](guide:delivery-scam-texts).

## Sigue un envío de Redur con Peek

Si pegas un enlace directo `redur.es/track-trace/?...&valor=` en [Peek](/), Peek identifica a Redur automáticamente. Como los códigos numéricos de 14 dígitos (`58492716380419` o `61738492051628`, inventados) se solapan con varios transportistas europeos y Redur no tiene un prefijo exclusivo, introduce el número y pulsa "Cambiar" al añadirlo o "Cambiar transportista" en la pantalla del paquete para seleccionar **Redur** (también aparece buscando "Redur Spain", "Redur Portugal" o "Redur S.A."); Peek lo consulta directamente en Redur y muestra `"Redur tiene este paquete."` en cuanto lo confirma.

Peek consulta directamente `redur.es/track-trace/` y muestra la fase mappada ("Etiqueta creada", "Admitido por el transportista", "En tránsito", "En reparto", "Listo para recoger", "Intento de entrega", "Devuelto al remitente", "Entregado"), las descripciones de los escaneos en español, la delegación de cada movimiento, el estado de entrega de la barra de progreso cuando está activo y la hora local publicada por Redur (solo fecha en filas `DOCUMENTACION` a medianoche, sin zona horaria). Peek descarta la columna de número de bultos; la web pública de Redur no ofrece fecha estimada de entrega, peso, tipo de servicio, hora exacta de entrega aparte ni código de seguimiento del socio europeo de Eurodis.

Peek comprueba hasta cada 10 minutos mientras tienes el paquete abierto o el día de la entrega con avisos activados, y hasta cada 2 minutos desde las 6:00 hora local el día en que llega un escaneo de reparto. El resto del tiempo revisa cada hora, mantiene el ritmo horario con la pantalla abierta tras 12 horas en "Etiqueta creada" o "Admitido por el transportista" o 2 días sin novedades, y baja a cada 6 horas tras 7 días aún en "Etiqueta creada". Cuando los servicios de seguimiento actúan de respaldo, revisan cada hora, cada 3 horas tras un día sin cambios y hasta cada 15 minutos el día de entrega. Tras 4 días en tránsito sin escaneos nuevos, Peek señala la pausa (por ejemplo "Sin novedades desde el lun, 12 oct"), muestra "Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a Redur." y ofrece "Pide ayuda a Redur", que abre la página de contacto de Redur. Tras más de tres semanas sin novedades, Peek muestra "Nada nuevo desde hace más de tres semanas. Si el paquete aún no ha llegado, contacta con el vendedor. Peek sigue preguntando a Redur."

Puedes elegir los avisos entre "Cada escaneo", "Pasos importantes" o "Solo la entrega". Peek solo monitoriza el seguimiento y no puede concertar fechas de entrega, modificar direcciones ni contactar con Redur por ti.

## Preguntas frecuentes sobre el seguimiento de Redur

### ¿Cómo es un número de seguimiento de Redur?

Es un código numérico de 14 dígitos sin letras ni espacios (por ejemplo `58492716380419`, inventado).

### ¿Qué significa `PENDIENTE DE FECHA CONCERTADA` en Redur?

Significa que el envío espera en la delegación de Redur a acordar una fecha de entrega contigo antes de salir a reparto, algo frecuente en bultos voluminosos, palets o entregas comerciales.

### ¿Qué significa `RECOGERÁN EN AGENCIA`?

Indica que tu paquete queda depositado en la delegación local de Redur listo para recoger en persona presentando documento de identidad.

### ¿Cómo contactar con atención al cliente de Redur?

Puedes llamar a los Servicios Centrales de Redur en el `(+34) 916 20 46 10`, escribir a `info@redur.es` o usar el formulario en [redur.es/contacto/](https://redur.es/contacto/).

:::sources
- [Redur: Sitio Web Oficial](https://redur.es/) – más de 50 años, más de 61 delegaciones en España y Portugal, 3.000 profesionales, socio fundador de Eurodis, *Maia* y *Redur Connected*
- [Redur: Track & Trace](https://redur.es/track-trace/) – rastreador público de envíos
- [Redur: Servicios](https://redur.es/servicios/) – Servicio Doméstico, Servicio Internacional, Farma 15-25 (GDP) y Logística integral
- [Redur: Contacto](https://redur.es/contacto/) – `(+34) 916 20 46 10`, `info@redur.es` y sede central en Algete, Madrid (URL de ayuda enlazada por Peek)
- [Universal Parcel Scraper: redur](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/redur) – formato de 14 dígitos, regla de enlace `redur.es/track-trace/`, mapa de estados ES/EN, barra de progreso y horas locales
:::
