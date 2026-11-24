---
title: Seguimiento Memphis Courier: códigos M-MIA, aduana y ayuda
description: Sigue importaciones de Memphis Courier de EE. UU. a Brasil: códigos M-MIA, estados en el aeropuerto GRU y ANVISA, reglas de CPF y contactos oficiales.
slug: seguimiento-memphis-courier
published: 2026-11-24
updated: 2026-11-24
---

Memphis Courier es una empresa logística 100 % brasileña con más de 25 años de trayectoria en envíos exprés y formales de exportación e importación. Desde su terminal courier en el Aeropuerto Internacional de Guarulhos (GRU) y su oficina en São Paulo ofrece transporte aéreo, despacho aduanero ante la Receita Federal, tramitación regulatoria ante ANVISA (incluidas importaciones de cannabis medicinal bajo RDC 660 y RDC 327 para medicamentos fabricados en Estados Unidos, Europa, Colombia y Uruguay) y entrega en todo Brasil.

Su seguimiento acompaña el paquete hasta el aeropuerto de Guarulhos, a través de la inspección de Receita Federal y la revisión de ANVISA, y hasta la entrega final.

## Formato del número de seguimiento de Memphis Courier

Los números de seguimiento de Memphis Courier siguen un patrón alfanumérico inconfundible de 11 caracteres:

- **`M` + 7 dígitos + `MIA`** (11 caracteres en total, como `M4829173MIA`) — el formato que Peek reconoce como propio de Memphis Courier.

Peek admite códigos de Memphis Courier de 6 a 30 letras y dígitos; elimina espacios y pasa las letras a mayúsculas. El portal de seguimiento de Memphis Courier (`clients.memphiscourier.com.br`) consulta códigos sin inicio de sesión. Con el formato `M` + 7 dígitos + `MIA`, Peek pregunta únicamente a Memphis Courier y muestra «Memphis Courier tiene este paquete.» en cuanto responde. Para otros formatos habituales en importaciones a Brasil, consulta la [guía de formatos de números de seguimiento](guide:tracking-number-formats).

## Estados de seguimiento de Memphis Courier

El servicio de seguimiento de Memphis Courier devuelve códigos de 3 letras con descripciones en portugués y una columna de ubicación (estado o país). La barra de pasos de su web recorre Criado (`FLT`), aduanas (`BRP`), Liberado y Entregue; los escaneos que alcanzan los dos últimos pasos (`CLA` y `DLV`) solo se conocen por esas etiquetas de paso.

| Código | Texto de Memphis Courier | Qué significa |
| --- | --- | --- |
| `FLT` | Seu pedido foi criado com sucesso | La orden de envío quedó registrada en el sistema de Memphis Courier antes de la salida aérea. |
| `CHK` | Entrada registrada no Centro de Distribuição | Registrada la entrada en un centro de distribución de Memphis Courier. |
| `ENO` | Encomenda em trânsito | En movimiento dentro de la red de Memphis Courier. |
| `TRA` | Encomenda em transferência | En traslado entre instalaciones de tratamiento. |
| `DEP` | A encomenda saiu da origem e está a caminho do aeroporto de destino (Guarulhos - SP) | Salió del hub de origen en un vuelo con destino al Aeropuerto Internacional de Guarulhos (GRU). |
| `ARR` | Seu pedido chegou ao aeroporto de destino (Guarulhos - SP) | Aterrizó en el aeropuerto de Guarulhos en São Paulo. |
| `AAZ` | Pedido recebido pela GRU Airport | Recibido por la terminal de carga del aeropuerto GRU antes del trámite aduanero formal. |
| `BRP` | Processo de desembaraço iniciado | Se ha iniciado el despacho aduanero brasileño. |
| `HOC` | Encomenda sendo inspecionada pela Alfândega | Bajo inspección de la Receita Federal (aduanas de Brasil). |
| `ANV` | Em inspeção pela ANVISA | Bajo inspección regulatoria de ANVISA, la agencia sanitaria brasileña, durante el despacho. |
| `CLA` | Liberado (barra de pasos) | Aduanas y los organismos regulatorios exigidos han liberado el envío para entrega nacional. |
| `DLV` | Entregue (barra de pasos) | Entregado en la dirección del destinatario en Brasil. |

## Aduanas, validación de CPF y despacho ANVISA

Toda importación personal exprés en Brasil requiere el CPF (*Cadastro de Pessoas Físicas*) del destinatario para el despacho ante la Receita Federal. Como señala Memphis Courier en su [página de servicios adicionales](https://memphiscourier.com.br/servicos-adicionais/), muchas tiendas extranjeras no incluyen campo de CPF al pagar; cuando eso ocurre, el equipo de atención al cliente de Memphis Courier se pone en contacto con el destinatario para validar su CPF y evitar bloqueos aduaneros.

En cannabis medicinal y otros medicamentos que Memphis Courier importa desde Estados Unidos, Europa, Colombia o Uruguay, el paquete puede pasar tanto por controles de Receita Federal (`HOC`) como de ANVISA (`ANV`) en Guarulhos antes de aparecer `CLA` (liberado). Si tu envío permanece varios días hábiles en `BRP`, `HOC` o `ANV` sin cambios, revisa tu correo y WhatsApp: Memphis Courier informa a los clientes por correo electrónico, WhatsApp, chat o teléfono y puede haberte pedido confirmar tu CPF o aportar algún documento. Para más información sobre despachos internacionales, consulta la [guía de aduanas](guide:customs) y [cuando el seguimiento deja de actualizarse](guide:tracking-not-updating).

Una vez que la aduana libera el paquete (`CLA`), la distribución nacional cubre todos los estados brasileños. Si otro transportista entrega otro pedido en Brasil, compara el seguimiento con [Anjun Express](carrier:anjun), [Jadlog](carrier:jadlog) o [J&T Express Brazil](carrier:j-and-t-br).

## Seguir Memphis Courier con Peek

Pega un código `M...MIA` en Peek y Peek lo asociará automáticamente a Memphis Courier en cuanto el transportista responda. Si necesitas elegir o cambiar el transportista manualmente, selecciona **Cambiar** al añadir el paquete o **Cambiar transportista** desde la pantalla de seguimiento.

Peek consulta directamente el servicio público de seguimiento de Memphis Courier mediante el [adaptador abierto de Memphis Courier en universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/memphis-courier). Lee el código, la descripción en portugués, la etiqueta de lugar y el peso total en kilogramos cuando aparece. Memphis Courier ofrece la hora de cada escaneo en reloj local y solo indica desfase UTC cuando el texto acaba con él. Peek respeta ese desfase; si no, fecha el escaneo en la zona horaria del lugar (hora de Nueva York para `Estados Unidos`, hora de São Paulo para `SP` o `Brasil`), un escaneo sin lugar en la del escaneo contiguo o en su defecto en hora de São Paulo, y muestra todo en la hora de tu dispositivo. Los datos del portal de clientes como observaciones internas, número de bultos, códigos postales, destino y documentos aduaneros adjuntos permanecen en el sitio de Memphis Courier.

Peek agrupa los 12 códigos de Memphis Courier en fases claras: **Etiqueta creada** para `FLT`, **En tránsito** de `CHK` a `AAZ` y de nuevo tras la liberación aduanera en `CLA` (el catálogo de Memphis Courier pasa directamente de creación del pedido a tránsito en centro de distribución sin un código separado de **Admitido por el transportista**), **En aduanas** para `BRP`, `HOC` y `ANV`, y **Entregado** en cuanto aparece `DLV`.

Mientras tienes el paquete abierto en Peek, o el día de entrega con las notificaciones activadas, Peek consulta hasta cada 10 minutos mientras el localizador de Memphis Courier responda. El resto del tiempo Peek comprueba cada hora. Como el localizador de Memphis Courier no incluye paso de salida a reparto, Peek nunca activa las comprobaciones cada 2 minutos del día de entrega. Tras 12 horas con solo «Etiqueta creada» o «Admitido por el transportista», o dos días sin novedades, consulta cada hora lo sigas como lo sigas, y cada 6 horas tras siete días en «Etiqueta creada». Cuando el localizador de Memphis Courier no responde y Peek sigue el paquete únicamente a través de servicios de seguimiento, consulta cada hora, cada 3 horas tras un día tranquilo y hasta cada 15 minutos el día en que el paquete está en reparto, mientras tengas el paquete abierto o las notificaciones activadas.

Cuando el escaneo más reciente tiene cuatro días y el paquete sigue en «Etiqueta creada», «Admitido por el transportista» o «En tránsito», Peek lo marca con el día de ese escaneo, como «Sin novedades desde el lun 12 oct». La página del paquete muestra entonces «Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a Memphis Courier.», con un enlace «Pide ayuda a Memphis Courier» a la página Fale Conosco de Memphis Courier. Tras tres semanas sin nuevos escaneos, indica «Nada nuevo desde hace más de tres semanas. Si el paquete aún no ha llegado, contacta con el vendedor. Peek sigue preguntando a Memphis Courier.»

Elige **Cada escaneo**, **Pasos importantes** o **Solo la entrega** en las preferencias de alertas de Peek para controlar la frecuencia de las notificaciones.

## Contactar con Memphis Courier

Memphis Courier publica canales oficiales de soporte en su web y declara expresamente en su aviso oficial que no trata portales de quejas de terceros como Reclame Aqui como canal oficial de atención o protocolo.

- **Página oficial de contacto:** [Fale Conosco](https://memphiscourier.com.br/fale-conosco/) (formulario de contacto)
- **Teléfono / atención al cliente:** +55 11 94264-7550 (indicado tanto para la Terminal Courier GRU como para la oficina de São Paulo)
- **Correo electrónico:** `atendimento@memphiscourier.com.br`, `atendimento1@memphiscourier.com.br` e `info@memphiscourier.com.br`
- **Terminal Courier de Guarulhos:** Aeroporto Internacional de Guarulhos — Terminal Courier, CEP 07190-100, São Paulo, Brasil
- **Oficina de São Paulo:** Av. Marques de São Vicente, 230, cj 1216 — Barra Funda, CEP 01139-000, São Paulo, Brasil ([Quem Somos](https://memphiscourier.com.br/quem-somos/))

Ten a mano tu código `M...MIA` y el CPF del destinatario al escribir a soporte para que puedan localizar tu expediente aduanero de inmediato.

## Preguntas frecuentes

### ¿Qué significa `ANV` («Em inspeção pela ANVISA») en Memphis Courier?

`ANV` significa que la agencia reguladora sanitaria de Brasil (ANVISA) inspecciona el envío dentro del despacho de importación. Memphis Courier despacha importaciones de cannabis medicinal (RDC 660 / RDC 327) con ANVISA; el paquete sigue en aduanas hasta `CLA` (liberado).

### ¿Por qué contactó Memphis Courier conmigo para pedirme mi CPF?

La Receita Federal exige el CPF del destinatario en toda importación personal exprés en Brasil. Cuando una tienda extranjera no recoge el CPF al pagar, el equipo de atención al cliente de Memphis Courier contacta contigo para validarlo antes de continuar el despacho.

### ¿Por qué no hay escaneos entre `DEP` y `ARR`?

`DEP` registra la salida de origen hacia el aeropuerto de Guarulhos y `ARR` la llegada allí. La lista de códigos de Memphis Courier no incluye escaneos durante el vuelo, por lo que el siguiente evento es `ARR` o `AAZ` en la terminal de carga.

### ¿Responde Memphis Courier a reclamaciones publicadas en Reclame Aqui?

No. El aviso oficial del sitio de Memphis Courier señala que atiende quejas, dudas y aclaraciones exclusivamente mediante los canales oficiales publicados en [memphiscourier.com.br](https://memphiscourier.com.br/) (teléfono, correo electrónico y formulario de contacto) para preservar la trazabilidad y la confidencialidad de los datos.

:::sources
- [Memphis Courier](https://memphiscourier.com.br/)
- [Memphis Courier — Quem Somos](https://memphiscourier.com.br/quem-somos/)
- [Memphis Courier — Serviços Adicionais](https://memphiscourier.com.br/servicos-adicionais/)
- [Memphis Courier — Fale Conosco](https://memphiscourier.com.br/fale-conosco/)
- [adaptador de Memphis Courier en universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/memphis-courier)
:::
