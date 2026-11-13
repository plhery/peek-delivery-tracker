self.addEventListener('activate', (event) => {
  // Remove cache names used by Serwist defaults before private API traffic was
  // explicitly made network-only.
  event.waitUntil(Promise.all([
    caches.delete('apis'),
    caches.delete('cross-origin'),
  ]));
});

/**
 * A delivery's time on this device's clock, wherever it is now: the service sends the scan's instant and the
 * sentence with the time left out, for a delivery today and on another day (deviceClockFields in
 * src/server/push.ts). Null when it sent none, and the service's own body shows.
 */
function deviceClockBody(payload, use24HourTime) {
  const { scan_at: at, body_same_day: sameDay, body_other_day: otherDay, date_locale: dateLocale } = payload;
  if (typeof at !== 'string' || typeof sameDay !== 'string' || typeof otherDay !== 'string') return null;
  const scanned = new Date(at);
  const now = new Date();
  if (Number.isNaN(scanned.getTime()) || scanned > now) return null;
  const today = scanned.toDateString() === now.toDateString();
  const two = (value) => String(value).padStart(2, '0');
  const dayOptions = { day: 'numeric', month: 'long' };
  let day;
  try {
    day = new Intl.DateTimeFormat(typeof dateLocale === 'string' ? dateLocale : undefined, dayOptions);
  } catch {
    day = new Intl.DateTimeFormat(undefined, dayOptions);
  }
  const time = use24HourTime ? `${two(scanned.getHours())}:${two(scanned.getMinutes())}`
    : new Intl.DateTimeFormat(day.resolvedOptions().locale, { hour: 'numeric', minute: '2-digit', hourCycle: 'h12' }).format(scanned);
  const date = day.format(scanned);
  return (today ? sameDay : otherDay).replaceAll('{{time}}', () => time).replaceAll('{{date}}', () => date);
}

async function pushUses24HourTime() {
  try {
    const preference = await caches.match('/__peek/preferences/clock', { cacheName: 'peek-preferences' });
    return (await preference?.text()) !== 'false';
  } catch {
    return true;
  }
}

self.addEventListener('push', (event) => {
  let decoded = {};
  try {
    decoded = event.data ? event.data.json() : {};
  } catch {
    decoded = {};
  }

  const payload = decoded && typeof decoded === 'object' && !Array.isArray(decoded) ? decoded : {};
  const text = (value, fallback, limit) => (
    typeof value === 'string' && value.trim() ? value.trim().slice(0, limit) : fallback
  );
  // Matches the generic update copy in src/server/push.ts.
  const fallbackCopy = {
    en: ['Parcel update', 'Tap to see what’s new'],
    de: ['Neues vom Paket', 'Tippe, um zu sehen, was sich getan hat'],
    fr: ['Des nouvelles du colis', 'Touche pour voir ce qui a changé'],
    it: ['Novità sul pacco', 'Tocca per vedere cos’è cambiato'],
    es: ['Novedades del paquete', 'Toca para ver qué hay de nuevo'],
    pt: ['Atualização da encomenda', 'Toca para ver as novidades'],
    'pt-br': ['Atualização da encomenda', 'Toque para ver as novidades'],
    pl: ['Nowe wieści o przesyłce', 'Stuknij, aby zobaczyć szczegóły'],
    ru: ['Новости о посылке', 'Нажмите, чтобы узнать, что нового'],
    zh: ['包裹动态', '轻点查看最新动态'],
  };
  // The language as tagLocale in src/lib/locale.ts reads it: Brazil's Portuguese apart, and no Traditional Chinese.
  const [base, ...subtags] = String(payload.lang || self.navigator?.language || 'en').toLowerCase().replaceAll('_', '-').split('-');
  const script = subtags.find((subtag) => /^[a-z]{4}$/.test(subtag));
  const traditional = script ? script === 'hant' : subtags.some((subtag) => ['tw', 'hk', 'mo'].includes(subtag));
  const requestedLanguage = base === 'pt' && subtags.includes('br') ? 'pt-br' : base === 'zh' && traditional ? 'en' : base;
  const lang = Object.hasOwn(fallbackCopy, requestedLanguage) ? requestedLanguage : 'en';
  const [fallbackTitle, fallbackBody] = fallbackCopy[lang];
  const title = text(payload.title, fallbackTitle, 120);
  const show = async () => {
    const use24HourTime = typeof payload.scan_at !== 'string' && typeof payload.body_12_hour !== 'string'
      ? true : await pushUses24HourTime();
    const fallback = use24HourTime ? payload.body : text(payload.body_12_hour, payload.body, 500);
    return self.registration.showNotification(title, {
      body: text(deviceClockBody(payload, use24HourTime) ?? fallback, fallbackBody, 500),
      lang,
      icon: text(payload.icon, '/icons/icon-192.png', 2_048),
      badge: text(payload.badge, '/icons/badge-96.png', 2_048),
      tag: text(payload.tag, 'parcel-update', 120),
      renotify: true,
      data: payload.data && typeof payload.data === 'object' ? payload.data : { url: '/' },
    });
  };

  const tasks = [show()];
  if (self.navigator && typeof self.navigator.setAppBadge === 'function') {
    tasks.push(Promise.resolve(self.navigator.setAppBadge(1)).catch(() => undefined));
  }
  // Open windows show the new state now instead of at their next poll. The
  // notification never depends on it. The type matches SERVER_UPDATE_MESSAGE
  // in src/store/apiRepo.ts.
  tasks.push(Promise.resolve()
    .then(() => self.clients.matchAll({ type: 'window' }))
    .then((clients) => clients.forEach((client) => client.postMessage({ type: 'sdt:server-update' })))
    .catch(() => undefined));
  event.waitUntil(Promise.all(tasks));
});

self.addEventListener('notificationclick', (event) => {
  event.notification.close();
  if (self.navigator && typeof self.navigator.clearAppBadge === 'function') {
    void self.navigator.clearAppBadge();
  }

  let target;
  try {
    const requested = typeof event.notification.data?.url === 'string'
      ? event.notification.data.url
      : '/';
    target = new URL(requested, self.location.origin);
  } catch {
    target = new URL('/', self.location.origin);
  }
  if (target.origin !== self.location.origin) target = new URL('/', self.location.origin);

  event.waitUntil(
    self.clients.matchAll({ type: 'window', includeUncontrolled: true }).then(async (clients) => {
      for (const client of clients) {
        try {
          if ('navigate' in client) await client.navigate(target.href);
          if ('focus' in client) return await client.focus();
        } catch {
          // A stale or closing client should not prevent opening a usable one.
        }
      }
      return self.clients.openWindow(target.href);
    }),
  );
});
