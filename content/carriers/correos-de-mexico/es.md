---
title: Rastreo Correos de México: guías MX, ventanilla y reclamos
description: Rastrea un envío de Correos de México (SEPOMEX / Mexpost): códigos S10 MX, ejercicio por año, estados, puesta en ventanilla, reclamo en 30 días y correo.
slug: rastreo-correos-de-mexico
published: 2026-11-23
updated: 2026-11-23
---

Correos de México (*Servicio Postal Mexicano* / *SEPOMEX*) es el operador postal nacional de México, encargado del correo registrado nacional (*Registrados*), la mensajería *Mexpost* y los envíos postales internacionales UPU tanto de salida desde México como procedentes de correos extranjeros. Puedes consultar tu paquete en el [portal de seguimiento de Correos de México](https://www.correosdemexico.gob.mx/SSLServicios/SeguimientoEnvio/Seguimiento.aspx) (indicando número de guía y año o *Ejercicio*), o pegar tu código en el campo superior.

## Números de guía de Correos de México: formato S10

Busca el código en tu recibo de oficina postal, en la guía *Mexpost* o en el correo de envío de la tienda. Correos de México rastrea códigos postales UPU S10 de 13 caracteres — tanto envíos nacidos en México terminados en `MX` como piezas postales extranjeras dirigidas a México (por ejemplo terminadas en `US`, `CN`, `ES` o `JP`):

| Formato (inventado) | Qué es |
| --- | --- |
| `RR482917367MX` | Correo registrado enviado desde México (formato UPU S10 terminado en `MX`). |
| `MP482917367MX` | Paquete nacional o exprés *Mexpost* (formato UPU S10 terminado en `MX`). |
| `EE482917367MX` | Envío internacional EMS desde México (formato UPU S10 terminado en `MX`). |

**Selector de año (*Ejercicio*) en SEPOMEX.** A diferencia de otros rastreadores, [Seguimiento.aspx](https://www.correosdemexico.gob.mx/SSLServicios/SeguimientoEnvio/Seguimiento.aspx) archiva los eventos por año calendario (`2026`, `2025`, `2024`, etc.). Si tu paquete salió a finales de año y continuó viajando al año siguiente, conviene consultar ambos años en la web para ver todo el historial. Para mensajerías privadas mexicanas como [Estafeta](carrier:estafeta), [J&T Express México](carrier:j-and-t-mx) o [Paquetexpress](carrier:paquetexpress), consulta [formatos de números de seguimiento](guide:tracking-number-formats).

## Qué significan los estados de Correos de México

Correos de México muestra eventos en español acompañados de la oficina postal mexicana u oficina de intercambio extranjera y la fecha/hora:

| Correos de México indica | Qué significa para ti | Peek muestra |
| --- | --- | --- |
| `Depósito en Oficina de Correos` / `Deposito del Cliente en ...` | Pieza depositada en una oficina postal mexicana o admitida por el correo de origen en el extranjero. | «Admitido por el transportista» |
| `En tránsito hacia destino`, `Recepción en Oficina de Correos`, `En zona de intercambio int. hacia el país destino, previo Aduana`, `Pieza depositada en valija y enviada a oficina del País destino` | En traslado entre oficinas de correos o despachada en valija internacional hacia el país destino. | «En tránsito» |
| `Arribo a Zona Intercambio México, en espera de ingreso a la Aduana`, `Inicio de la revisión aduanal en país destino` | Llegó a la zona de intercambio internacional y espera o cursa revisión aduanal. | «En aduanas» |
| `Revisión aduanal concluida` | La aduana liberó la pieza para continuar su tránsito postal. | «En tránsito» |
| `Con mensajero para entrega` | El cartero o mensajero salió a repartir tu paquete hoy. | «En reparto» |
| `Puesta en ventanilla` | Disponible para recoger en la oficina postal indicada en la columna *Origen*. | «Listo para recoger» |
| `Pieza devuelta ...` (ej. `por domicilio destino incorrecto`) | En devolución hacia el remitente con el motivo indicado. | «Devuelto al remitente» |
| `Entrega` / `Entrega Internacional` | Entregada en México o confirmada como entregada en el extranjero por el correo destino. | «Entregado» |

## Recogida en ventanilla, aduana y reclamo de un envío

Según el portal del [Servicio Postal Mexicano en gob.mx](https://www.gob.mx/correosdemexico) y la guía oficial de [Reclamo de un Envío](https://www.gob.mx/correosdemexico/es/acciones-y-programas/reclamo-de-un-envio):

- **Puesta en ventanilla:** cuando aparece `Puesta en ventanilla`, tu envío te espera en la oficina postal que figura en la columna *Origen*.
- **Aduana en envíos internacionales:** las piezas del extranjero pasan por revisión aduanal (`Inicio de la revisión aduanal...`). Los tiempos dependen de la temporada y valoración aduanal; consulta [paquetes retenidos en aduanas](guide:customs).
- **Reclamo de un Envío:** las reclamaciones y posibles indemnizaciones aplican **únicamente a envíos Registrados y Mexpost** (no a correo ordinario sin registro) cuando exceden el plazo de entrega o llegan en mal estado. El trámite se realiza de manera presencial en oficinas postales o en línea, y el **tiempo máximo de respuesta es de 30 días hábiles**.

## Paquete de Correos de México detenido, en aduana o devuelto

- **Pausas largas en tránsito o aduana:** es habitual que las piezas internacionales pasen días o semanas sin nuevo evento entre `Pieza depositada en valija` y `Arribo a Zona Intercambio México`, o durante revisión aduanal. Consulta [por qué no se actualiza el seguimiento](guide:tracking-not-updating).
- **Envíos marcados `Pieza devuelta`:** una vez que SEPOMEX marca una pieza como devuelta (por domicilio incorrecto o falta de recogida), incluso una posterior `Puesta en ventanilla` o `Entrega` corresponde a la devolución al remitente original.
- Si es una compra online, avisa también al vendedor además de levantar tu reclamo. Consulta [entregado pero no recibido](guide:delivered-not-received), [paquete dañado](guide:damaged-parcel), [paquete perdido](guide:lost-parcel) y [devuelto al remitente](guide:returned-to-sender).

## Atención al cliente y contacto de Correos de México

Canales oficiales publicados en [gob.mx/correosdemexico](https://www.gob.mx/correosdemexico), [Seguimiento.aspx](https://www.correosdemexico.gob.mx/SSLServicios/SeguimientoEnvio/Seguimiento.aspx) y [Reclamo de un Envío](https://www.gob.mx/correosdemexico/es/acciones-y-programas/reclamo-de-un-envio):

- **Correo oficial de dudas e información:** `contacto@correosdemexico.gob.mx`.
- **Reclamaciones:** [Reclamo de un Envío](https://www.gob.mx/correosdemexico/es/acciones-y-programas/reclamo-de-un-envio) o de forma presencial en tu oficina postal local.
- **Defensa del consumidor:** ante conflictos no resueltos con tiendas en México, puedes acudir a la [PROFECO](https://www.gob.mx/profeco).

> Desconfía de SMS o mensajes de WhatsApp que suplantan a Correos de México / SEPOMEX pidiendo pagar con tarjeta en un enlace externo para «corregir tu código postal» o «liberar un paquete». Comprueba tu código S10 directamente en [correosdemexico.gob.mx](https://www.correosdemexico.gob.mx/SSLServicios/SeguimientoEnvio/Seguimiento.aspx) o escribe a `contacto@correosdemexico.gob.mx`. Ver [mensajes falsos de entrega](guide:delivery-scam-texts).

## Rastrear un paquete de Correos de México en Peek

[Peek](/) reconoce directamente los enlaces pegados de `correosdemexico.gob.mx/SSLServicios/SeguimientoEnvio/Seguimiento.aspx?guia=` como Correos de México. Al pegar un código UPU S10 de 13 caracteres terminado en `MX` (`RR482917367MX`, `MP482917367MX` o `EE482917367MX`, inventados), Peek pregunta primero a Correos de México y muestra «Correos de México tiene este paquete.» cuando lo confirma. Una pieza depositada en el extranjero conserva el código del correo de origen (terminado en `US`, `CN`, etc.) y Peek pregunta a ese correo; para seguirla en Correos de México, pulsa «Cambiar» al añadir o «Cambiar transportista» en la pantalla del paquete y elige Correos de México (buscar «SEPOMEX», «Mexpost» o «Mexico Post» también lo encuentra).

Peek consulta directamente el rastreador de Correos de México, recorriendo los años anteriores que ofrece su formulario para que un recorrido que cruza el cambio de año quede completo, y muestra la etapa («Admitido por el transportista», «En tránsito», «En aduanas», «En reparto», «Listo para recoger», «Devuelto al remitente», «Entregado»), los eventos en español, la oficina postal o de intercambio, la oficina donde espera la pieza cuando aparece `Puesta en ventanilla` y la hora de entrega cuando consta. Tras `Pieza devuelta`, Peek mantiene los escaneos posteriores de ventanilla y entrega en «Devuelto al remitente», porque son del remitente y no una entrega para ti. Correos de México da la hora de cada escaneo sin zona: Peek la fecha en la zona horaria de su oficina (la de Tijuana para un escaneo en Tijuana, la de Ciudad de México para uno en Ciudad de México) y la muestra en la hora de tu dispositivo. Peek omite el firmante, la hora de consulta y los avisos del portal; el rastreador no da fecha estimada, peso, nombre de servicio ni foto de entrega.

Mientras tengas el paquete abierto en Peek, o el día de su entrega con las notificaciones activadas, Peek revisa hasta cada 10 minutos, y cada 2 minutos desde las 6:00, hora de la oficina del escaneo más reciente, el día en que Correos de México muestra `Con mensajero para entrega`, mientras el rastreador de Correos de México responda. Si no, revisa cada hora. Tras 12 horas solo en «Etiqueta creada» o «Admitido por el transportista», o dos días sin novedades, revisa cada hora sigas como sigas el paquete, y cada 6 horas tras siete días en «Etiqueta creada». Cuando el rastreador de Correos de México no responde y Peek sigue el paquete solo a través de servicios de seguimiento, revisa cada hora, cada 3 horas tras un día sin novedades y hasta cada 15 minutos el día en que el paquete sale a reparto.

Cuando el escaneo más reciente tiene cuatro días y el paquete sigue en «Etiqueta creada», «Admitido por el transportista» o «En tránsito», Peek lo marca con el día de ese escaneo, como «Sin novedades desde el lun 12 oct». La página del paquete dice entonces «Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a Correos de México.», con el enlace «Pide ayuda a Correos de México» a la página de *Reclamo de un Envío*. Tras tres semanas sin un escaneo nuevo, dice «Nada nuevo desde hace más de tres semanas. Si el paquete aún no ha llegado, contacta con el vendedor. Peek sigue preguntando a Correos de México.»

Puedes elegir avisos para «Cada escaneo», «Pasos importantes» o «Solo la entrega». Peek únicamente sigue las actualizaciones y no puede gestionar trámites aduanales ni presentar un *Reclamo de un Envío* ante SEPOMEX por ti.

## Preguntas frecuentes sobre Correos de México

### ¿Qué números de guía funcionan en Correos de México?

Rastrea códigos postales UPU S10 de 13 caracteres: 2 letras + 9 dígitos + 2 letras de país (por ejemplo `RR482917367MX` o `MP482917367MX`, inventados), incluidos envíos S10 del extranjero que entran a México.

### ¿Qué significa `Puesta en ventanilla`?

Significa que tu paquete está esperando para recogida presencial en la oficina postal indicada en la columna *Origen*.

### ¿Por qué no aparece información de mi guía en Correos de México?

En [Seguimiento.aspx](https://www.correosdemexico.gob.mx/SSLServicios/SeguimientoEnvio/Seguimiento.aspx), asegúrate de seleccionar el año correcto (*Ejercicio*) — sobre todo en envíos depositados a fin de año — y recuerda que el correo ordinario sin registro no tiene seguimiento.

### ¿Cómo presento un reclamo en Correos de México?

Para envíos Registrados y Mexpost retrasados o dañados, presenta un *Reclamo de un Envío* en oficina postal o mediante [gob.mx/correosdemexico/es/acciones-y-programas/reclamo-de-un-envio](https://www.gob.mx/correosdemexico/es/acciones-y-programas/reclamo-de-un-envio) (plazo máximo de respuesta de 30 días hábiles) o escribe a `contacto@correosdemexico.gob.mx`.

:::sources
- [Correos de México (SEPOMEX): Portal gob.mx](https://www.gob.mx/correosdemexico) – portal oficial del Servicio Postal Mexicano y `contacto@correosdemexico.gob.mx`
- [Correos de México: Seguimiento de Envíos](https://www.correosdemexico.gob.mx/SSLServicios/SeguimientoEnvio/Seguimiento.aspx) – consulta S10 por ejercicio anual y correo `contacto@correosdemexico.gob.mx`
- [Correos de México: Reclamo de un Envío](https://www.gob.mx/correosdemexico/es/acciones-y-programas/reclamo-de-un-envio) – requisitos para Registrados y Mexpost, trámite presencial/en línea y respuesta en 30 días hábiles (enlace de ayuda usado por Peek)
- [PROFECO](https://www.gob.mx/profeco) – Procuraduría Federal del Consumidor
- [Universal Parcel Scraper: correos-de-mexico](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/correos-de-mexico) – formato S10, regla de enlace `Seguimiento.aspx?guia=`, consulta multianual, estados en español, punto de recogida `Puesta en ventanilla`, manejo de devoluciones, cada escaneo en la zona horaria de su oficina y URL de ayuda
:::
