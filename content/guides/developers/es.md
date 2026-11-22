---
title: Seguimiento de paquetes de código abierto: Peek y su motor
description: Peek es de código abierto, con licencia AGPL-3.0. Alójalo tú, ejecútalo en local o sigue paquetes desde tu código con Universal Parcel Scraper: CLI, Node o HTTP.
slug: seguimiento-paquetes-codigo-abierto
picture: Pip, el paquete con cara, subido a un portátil abierto cuyo terminal muestra los escaneos de un paquete línea a línea.
published: 2026-11-19
updated: 2026-11-22
---

[Peek](/) es un servicio gratuito y de código abierto para seguir paquetes: su app web, su API y sus apps de iPhone y Android están en GitHub con la licencia GNU AGPL 3.0, y a los transportistas los lee un motor independiente, Universal Parcel Scraper, libre para usos no comerciales. Peek no tiene una API pública con claves, así que, para seguir paquetes desde tu propio código, usa el scraper, como comando, como librería de Node o como servidor HTTP, sin cuenta ni clave de API. También puedes alojar tu propio Peek, o ejecutarlo en tu ordenador con paquetes inventados.

Para probar el scraper desde tu terminal:

:::steps
- Instálalo | Necesitas un Node.js reciente (el README indica la versión mínima). Ejecuta `npm install -g universal-parcel-scraper`, o escribe `npx universal-parcel-scraper` allí donde aparezca `parcel-scraper` más abajo.
- Averigua el transportista | `parcel-scraper detect 1Z999AA10123456784` te dice a qué transportista pertenece un número, sin conexión.
- Consulta el historial | `parcel-scraper track` seguido de tu número pregunta al transportista y muestra el historial del paquete en JSON. Si el número encaja con varios transportistas, añade por ejemplo `--carrier dhl`. Algunos transportistas piden además `--postcode`, `--phone-digits` o `--tracking-url`.
:::

## ¿Peek es de código abierto? ¿Con qué licencia?

Sí. El código de Peek es público en [github.com/plhery/peek-delivery-tracker](https://github.com/plhery/peek-delivery-tracker), con la licencia GNU Affero General Public License 3.0 (AGPL-3.0): la app web y la API (Next.js, React, TypeScript), la app nativa SwiftUI para iPhone, iPad y Mac, y la app de Android. Una copia o versión modificada conserva el crédito «Based on Peek by Paul-Louis Hery» con su enlace. [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper), que Peek instala desde npm, tiene desde la versión 0.1294.0 la licencia PolyForm Noncommercial 1.0.0, que solo permite usos no comerciales; las versiones anteriores siguen con licencia Apache 2.0. Su imagen de navegador TRAWL, opcional, tiene licencia AGPL-3.0.

Peek es un proyecto no comercial y sin publicidad; [el seguimiento universal de paquetes](guide:universal-tracker) lo compara con otros localizadores.

Los errores y las ideas van a las [issues de GitHub](https://github.com/plhery/peek-delivery-tracker/issues) de Peek, que son públicas: no pongas números de seguimiento reales, códigos postales ni códigos de acceso. Los problemas de seguridad se notifican mediante el [informe privado de vulnerabilidades](https://github.com/plhery/peek-delivery-tracker/security/advisories/new) de GitHub. [CONTRIBUTING.md](https://github.com/plhery/peek-delivery-tracker/blob/main/CONTRIBUTING.md) enumera las comprobaciones que hay que ejecutar antes de un pull request.

## ¿Peek tiene una API que pueda usar?

No tiene una API pública: Peek no da claves de API. Su API es la de la propia app, la que llaman la web y las apps de iPhone y Android, con la sesión iniciada del usuario. Está descrita en un [archivo OpenAPI](https://peektracker.com/api/openapi.json), guardado en el repositorio como `contracts/openapi.json`; peektracker.com/api en sí responde 404.

El catálogo de transportistas, `GET /api/carriers`, no pide iniciar sesión. Seguir un paquete sin cuenta tampoco, pero ahí una búsqueda nueva tiene que pasar antes una comprobación del navegador o de la app. Los tokens personales para scripts están desactivados salvo que el servidor los active, y en «Ajustes» no hay ninguna pantalla que cree uno.

Desde fuera, la única vía de entrada es el inicio de sesión de un asistente. Claude, ChatGPT, Gemini u otro asistente inicia sesión en tu cuenta con OAuth estándar y usa el servidor MCP de Peek, `https://peektracker.com/mcp`, dentro de lo que hayas permitido. Con el mismo token se pueden leer tus paquetes en JSON en `GET /api/parcels`, y la integración de Peek para Home Assistant inicia sesión del mismo modo. Consulta [la guía de los asistentes](guide:ai-assistants), [la página de Peek para asistentes](https://peektracker.com/es/assistants) y [la guía de Home Assistant](guide:home-assistant).

## ¿Cómo sigo paquetes desde mi propio código sin clave de API?

Con Universal Parcel Scraper, el motor que lee a los transportistas para Peek. Averigua el transportista, lee la propia web de ese transportista, recurre a localizadores universales solo cuando no queda otra, y devuelve el mismo JSON para todos los transportistas, con cada escaneo clasificado en una etapa de entrega común.

Su README cuenta más de 4500 transportistas, gracias a más de 800 adaptadores específicos y a servicios de respaldo universales. En su benchmark de 100 transportistas elegidos para compradores en Europa, los adaptadores devuelven un historial para 92, o para 95 con todos los servicios de respaldo, frente a 73 del mejor agregador por sí solo.

Además de la línea de comandos, funciona de tres maneras:

| Dónde | Cómo |
| --- | --- |
| Node | Importa `createTracker` desde `universal-parcel-scraper/node`; `tracker.track({ number })` devuelve el transportista, quién respondió, los escaneos, la etapa actual y lo que muestra el transportista, como la entrega prevista |
| Un navegador | Solo la detección del transportista, con `parseTrackingInput`: sin imports de Node ni peticiones |
| HTTP | `parcel-scraper serve --port 8080`, o la imagen Docker `ghcr.io/plhery/universal-parcel-scraper`, etiquetada solo con números de versión publicados; después, un POST a `/v1/track`. El servidor guarda las respuestas en caché en memoria y limita la frecuencia de peticiones de cada cliente; no tiene base de datos y nunca consulta por su cuenta |

Antes de construir sobre él:

- **Responde a una búsqueda cada vez.** Guardar los paquetes y volver a consultarlos te toca a ti: esa es la parte que añade Peek.
- **Algunas webs de transportistas solo responden a un navegador.** Para esas necesita un Chromium que controla mediante módulos opcionales, o su servicio de navegador TRAWL. La imagen Docker incluye Chromium.
- **Los servicios de respaldo hay que activarlos.** Solo UPU está activo de serie; ParcelsApp, Ship24, 17TRACK, Postal Ninja y Track718 esperan a que los actives, y cada uno que actives recibe el número de seguimiento.
- **Una búsqueda envía el número al transportista**, con el código postal, el enlace o los dígitos del teléfono cuando el transportista los pide. No hay telemetría.

## ¿Puedo alojar mi propio Peek?

Sí. Un Peek autoalojado es un único contenedor que se ejecuta de forma continua, con la app web, la API y el worker que sigue consultando a los transportistas, junto a Supabase para el inicio de sesión y Postgres, detrás de HTTPS. Necesitas:

- **un stack de Supabase** (Auth, PostgREST y Postgres) con copia de seguridad y restauración probadas;
- **SMTP**, si el inicio de sesión por correo o el correo de entrega están activados;
- **un host que pueda construir el Dockerfile y mantener el contenedor en marcha.** El alojamiento serverless no sirve: el worker tiene que seguir vivo.

Las notificaciones web push, las de iPhone (un equipo de Apple y una clave APNs) y las de Android (Firebase) son opcionales. Los asistentes también: sin `ASSISTANT_ACCOUNTS`, `/mcp` y `/oauth` no existen. Esa variable enumera las cuentas que pueden conectarse, o `*` para todas, y necesita `CANONICAL_ORIGIN`. Con ella definida, `PERSONAL_TOKENS=on` activa los tokens personales para tus scripts, que se crean a través de la API. [docs/DEPLOYMENT.md](https://github.com/plhery/peek-delivery-tracker/blob/main/docs/DEPLOYMENT.md) tiene los pasos, desde la base de datos y el inicio de sesión hasta las actualizaciones, las copias de seguridad y el cambio de host.

### Pruébalo antes en tu ordenador

Sin cuenta, sin base de datos y sin archivo `.env`:

:::steps
- Descarga el código | Clona `https://github.com/plhery/peek-delivery-tracker.git`. Necesitas nvm y la versión de Node.js que indica el README.
- Instala | En su carpeta, ejecuta `nvm use` y luego `npm ci`.
- Arranca | Ejecuta `npm run dev` y abre localhost:3000: Peek responde en el navegador con paquetes inventados. localhost:3000/demo muestra una lista de entregas de ejemplo que avanzan cada vez que recargas.
:::

Para cuentas y seguimiento reales, copia `.env.example` a `.env.local`, rellena los datos de un proyecto de Supabase y pon `NEXT_PUBLIC_USE_API=true`; [docs/AUTHENTICATION.md](https://github.com/plhery/peek-delivery-tracker/blob/main/docs/AUTHENTICATION.md) tiene los pasos del inicio de sesión. La app de iPhone se compila en Xcode para un simulador de iPhone y arranca con paquetes de ejemplo, sin cuenta, servidor ni equipo de Apple.

## Falta un transportista: ¿cómo lo comunico o lo añado?

Comunícalo al scraper, que es donde viven los transportistas. En su [página para abrir una issue](https://github.com/plhery/universal-parcel-scraper/issues/new/choose), enlazada desde el README de Peek como «Tell the scraper», elige «Add a carrier» (su nombre, su país de origen y su web de seguimiento pública) o «A carrier stopped working». Los transportistas nuevos y los arreglos van al scraper como pull requests: cada transportista tiene su propia carpeta con fixtures sintéticos y tests sin conexión, y `npm run carrier:new` crea la estructura de uno nuevo.

Peek adopta por sí solo las nuevas versiones del scraper, así que un transportista no necesita ningún pull request a Peek. Y la app de iPhone actualiza su lista de transportistas desde el servidor, así que tampoco hace falta una nueva versión de la app.

¿No tienes cuenta de GitHub? Díselo a Peek desde la página del paquete, en la web, en iPhone o en Android, con o sin cuenta de Peek:

:::steps
- Responde a la pregunta | Cuando Peek no encuentra transportista para un paquete, su página pregunta «¿Lo muestra la web del transportista?». Elige «Sí».
- Indica el transportista | En «¿Quién lo lleva?», escribe su nombre en «Transportista o tienda», o pega su enlace de seguimiento en «Su página de seguimiento, si tienes el enlace». Basta con uno de los dos.
- Envíalo | Elige «Enviar». Se envía con el número del paquete y nada sobre ti.
:::

Una persona lee estas respuestas, pero nadie puede contestarte: si quieres respuesta, escribe a hello@peektracker.com, la dirección que aparece en [la página de ayuda de Peek](https://peektracker.com/support.html). Cómo averigua Peek el transportista a partir de lo que pegas lo explica [Seguir un paquete sin saber el transportista](guide:track-without-carrier).

:::sources
- [Peek en GitHub: README (en inglés)](https://github.com/plhery/peek-delivery-tracker) – qué contiene el repositorio, los transportistas en el scraper, ejecutarlo en local, alojar el tuyo, la compilación para iPhone
- [Peek en GitHub: LICENSE (en inglés)](https://github.com/plhery/peek-delivery-tracker/blob/main/LICENSE) – GNU Affero General Public License 3.0
- [Universal Parcel Scraper en GitHub (en inglés)](https://github.com/plhery/universal-parcel-scraper) – comandos, opciones, Node, navegador, HTTP y Docker, Chromium en la imagen, servicios de respaldo, privacidad, número de transportistas, benchmark, licencias, añadir un transportista
- [Universal Parcel Scraper: nueva issue (en inglés)](https://github.com/plhery/universal-parcel-scraper/issues/new/choose) – los formularios «Add a carrier» y «A carrier stopped working»
- [Universal Parcel Scraper en npm (en inglés)](https://www.npmjs.com/package/universal-parcel-scraper) – el paquete de npm y la versión de Node.js que necesita
- [Peek: archivo OpenAPI](https://peektracker.com/api/openapi.json) – la API propia de la app, las rutas que no piden iniciar sesión y sus comprobaciones, leer paquetes con el token de un asistente
- [Peek en GitHub: arquitectura (en inglés)](https://github.com/plhery/peek-delivery-tracker/blob/main/docs/ARCHITECTURE.md) – el inicio de sesión de los asistentes y de Home Assistant, GET /api/parcels, tokens personales desactivados
- [Peek en GitHub: configuración de los asistentes en el servidor](https://github.com/plhery/peek-delivery-tracker/blob/main/src/server/assistants/settings.ts) – tokens personales desactivados salvo que PERSONAL_TOKENS esté activado, ASSISTANT_ACCOUNTS
- [Peek: Conectar Peek a Claude, ChatGPT o Gemini](https://peektracker.com/es/assistants) – la vía de entrada para los asistentes
- [Peek en GitHub: despliegue (en inglés)](https://github.com/plhery/peek-delivery-tracker/blob/main/docs/DEPLOYMENT.md) – lo que necesita el autoalojamiento, las partes opcionales, asistentes y tokens personales en tu propia instancia
- [Peek en GitHub: cómo contribuir (en inglés)](https://github.com/plhery/peek-delivery-tracker/blob/main/CONTRIBUTING.md) – comprobaciones antes de un pull request, los transportistas van al scraper, versiones nuevas adoptadas solas
- [Peek en GitHub: comentarios sobre un paquete](https://github.com/plhery/peek-delivery-tracker/blob/main/src/peek/parcel/Feedback.tsx) – «¿Quién lo lleva?» cuando no se encuentra transportista, basta con uno de los dos campos para enviar
- [Peek: ayuda (en inglés)](https://peektracker.com/support.html) – comunicar un transportista sin GitHub, issues públicas, informes de seguridad, contacto
- [Peek: condiciones de uso (en inglés)](https://peektracker.com/terms.html) – un proyecto gratuito, no comercial y sin publicidad
:::
