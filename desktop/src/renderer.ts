import type { ClientState } from './bridge.ts';

function element(id: string): HTMLElement {
  const result = document.getElementById(id);
  if (!result) throw new Error(`Missing ${id}.`);
  return result;
}
const inputElement = element('server');
if (!(inputElement instanceof HTMLInputElement)) throw new Error('Missing server input.');
const input: HTMLInputElement = inputElement;
const form = element('server-form');
const submit = element('connect');
if (!(input instanceof HTMLInputElement) || !(form instanceof HTMLFormElement) || !(submit instanceof HTMLButtonElement)) throw new Error('Missing server form.');
const error = element('form-error');
let mode: ClientState['mode'] | null = null;

function render(state: ClientState) {
  document.body.dataset.mode = state.mode;
  element('setup').hidden = state.mode !== 'setup';
  element('status').hidden = state.mode === 'setup' || state.mode === 'connected';
  element('change').hidden = !state.origin || state.mode === 'setup';
  element('server-label').textContent = state.origin ?? '';
  element('channel').textContent = state.release.channel === 'nightly' ? 'Nightly' : '';
  element('build').textContent = `${state.release.version} · ${state.release.channel} · ${state.release.sha}`;
  if (state.mode === 'setup') {
    if (mode !== 'setup') { input.value = state.origin ?? ''; input.focus(); }
    error.textContent = state.message;
  } else if (state.mode !== 'connected') {
    element('status-title').textContent = state.mode === 'loading' ? 'Connecting to Peek…' : 'Let’s reconnect.';
    element('status-message').textContent = state.message || state.origin;
    element('retry').hidden = state.mode === 'loading';
    if (mode !== state.mode) element('status-title').focus();
  }
  mode = state.mode;
}

form.addEventListener('submit', async (event) => {
  event.preventDefault();
  if (!input.checkValidity()) {
    input.setAttribute('aria-invalid', 'true');
    error.textContent = 'Enter your server’s full HTTPS address.';
    input.focus();
    return;
  }
  submit.disabled = true;
  error.textContent = '';
  try {
    const result = await window.peekDesktop.connect(input.value);
    if (!result.ok) {
      error.textContent = result.message;
      input.setAttribute('aria-invalid', 'true');
      input.focus();
    }
  } catch {
    error.textContent = 'Couldn’t connect. Close Peek and open it again.';
  } finally {
    submit.disabled = false;
  }
});
input.addEventListener('input', () => { input.removeAttribute('aria-invalid'); error.textContent = ''; });
for (const id of ['change', 'status-change']) element(id).addEventListener('click', () => { void window.peekDesktop.changeServer(); });
element('retry').addEventListener('click', () => { void window.peekDesktop.retry(); });
window.peekDesktop.onState(render);
void window.peekDesktop.state().then(render);
