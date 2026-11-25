/**
 * The little of IndexedDB the app uses (open with an upgrade, one store keyed by a path, put, get, getAll and delete in
 * transactions), kept in memory for tests. Events fire on later tasks, as a browser's do. `failWrites` makes the next
 * writes fail as a full disk does.
 */
type Handler = ((event: Event) => void) | null;

class MemoryRequest<T = unknown> {
  result!: T;
  error: DOMException | null = null;
  onsuccess: Handler = null;
  onerror: Handler = null;
  onupgradeneeded: Handler = null;
  onblocked: Handler = null;
}

class MemoryStore {
  constructor(private readonly rows: Map<string, unknown>, private readonly keyPath: string, private readonly transaction: MemoryTransaction) {}

  put(value: Record<string, unknown>) {
    return this.transaction.request(() => {
      const failure = this.transaction.database.factory.failWrites;
      if (failure) throw failure;
      const key = String(value[this.keyPath]);
      this.rows.set(key, { ...value });
      return key;
    });
  }

  get(key: string) {
    return this.transaction.request(() => {
      const row = this.rows.get(String(key));
      return row ? { ...(row as object) } : undefined;
    });
  }

  getAll() {
    return this.transaction.request(() => [...this.rows.values()].map((row) => ({ ...(row as object) })));
  }

  delete(key: string) {
    return this.transaction.request(() => {
      this.rows.delete(String(key));
      return undefined;
    });
  }
}

class MemoryTransaction {
  error: DOMException | null = null;
  oncomplete: Handler = null;
  onerror: Handler = null;
  onabort: Handler = null;
  private pending = 0;
  private done = false;

  constructor(readonly database: MemoryDatabase, private readonly names: string[]) {
    // A transaction with no request completes on its own.
    setTimeout(() => this.settle(), 0);
  }

  objectStore(name: string) {
    if (!this.names.includes(name)) throw new DOMException(`No store ${name} in this transaction`, 'NotFoundError');
    const store = this.database.stores.get(name);
    if (!store) throw new DOMException(`No store ${name}`, 'NotFoundError');
    return new MemoryStore(store.rows, store.keyPath, this);
  }

  request<T>(work: () => T): MemoryRequest<T> {
    if (this.done) throw new DOMException('The transaction has finished', 'TransactionInactiveError');
    const request = new MemoryRequest<T>();
    this.pending += 1;
    setTimeout(() => {
      try {
        request.result = work();
        request.onsuccess?.(new Event('success'));
      } catch (error) {
        request.error = error instanceof DOMException ? error : new DOMException(String(error), 'UnknownError');
        request.onerror?.(new Event('error'));
        this.abort(request.error);
      }
      this.pending -= 1;
      this.settle();
    }, 0);
    return request;
  }

  private abort(error: DOMException) {
    if (this.done) return;
    this.done = true;
    this.error = error;
    this.onerror?.(new Event('error'));
    this.onabort?.(new Event('abort'));
  }

  private settle() {
    if (this.done || this.pending) return;
    // Requests made from a success handler keep the transaction open: wait a task for them.
    setTimeout(() => {
      if (this.done || this.pending) return;
      this.done = true;
      this.oncomplete?.(new Event('complete'));
    }, 0);
  }
}

class MemoryDatabase {
  readonly stores = new Map<string, { keyPath: string; rows: Map<string, unknown> }>();

  constructor(readonly factory: MemoryIndexedDB, readonly name: string, public version: number) {}

  get objectStoreNames() {
    const names = [...this.stores.keys()];
    return { contains: (name: string) => names.includes(name), length: names.length };
  }

  createObjectStore(name: string, options: { keyPath: string }) {
    this.stores.set(name, { keyPath: options.keyPath, rows: new Map() });
  }

  transaction(names: string | string[]) {
    return new MemoryTransaction(this, Array.isArray(names) ? names : [names]);
  }

  close() {}
}

export class MemoryIndexedDB {
  readonly databases = new Map<string, MemoryDatabase>();
  /** Set to fail every write with this error, as `QuotaExceededError` does on a full disk. */
  failWrites: DOMException | null = null;
  /** Set to refuse opening, as a private window can. */
  refuseOpen = false;

  open(name: string, version = 1) {
    const request = new MemoryRequest<MemoryDatabase>();
    setTimeout(() => {
      if (this.refuseOpen) {
        request.error = new DOMException('No database here', 'InvalidStateError');
        request.onerror?.(new Event('error'));
        return;
      }
      let database = this.databases.get(name);
      const upgrade = !database || database.version < version;
      database ??= new MemoryDatabase(this, name, version);
      this.databases.set(name, database);
      request.result = database;
      if (upgrade) {
        database.version = version;
        request.onupgradeneeded?.(new Event('upgradeneeded'));
      }
      request.onsuccess?.(new Event('success'));
    }, 0);
    return request;
  }

  /** The rows of one store, as stored. */
  rows(database: string, store: string): Record<string, unknown>[] {
    return [...(this.databases.get(database)?.stores.get(store)?.rows.values() ?? [])] as Record<string, unknown>[];
  }
}
