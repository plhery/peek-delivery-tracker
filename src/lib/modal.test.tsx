import { fireEvent, render, screen } from '@testing-library/react';
import { useState } from 'react';
import { createPortal } from 'react-dom';
import { describe, expect, it, vi } from 'vitest';
import { useModalDialog } from './modal';

function Sheet() {
  const dialog = useModalDialog<HTMLDivElement>(true, vi.fn());
  return createPortal(<div className="sheet-backdrop">
    <div ref={dialog} role="dialog" aria-label="A sheet" tabIndex={-1}><button type="button">Done</button></div>
  </div>, document.body);
}

function Screen({ open }: { open: boolean }) {
  return <>
    <div className="any-screen"><button type="button">Behind</button></div>
    {open && <Sheet />}
  </>;
}

describe('useModalDialog', () => {
  it('shuts off whatever screen lies behind it, whatever it is called, and gives each part back as it was', () => {
    const view = render(<Screen open={false} />);
    // Something beside the screen that was hidden from screen readers already, such as a picture.
    const outside = document.createElement('aside');
    outside.setAttribute('aria-hidden', 'true');
    document.body.append(outside);
    view.rerender(<Screen open />);
    expect(screen.getByRole('dialog', { name: 'A sheet' })).toHaveFocus();
    // The sheet sits beside the screen's own root, so the root is shut off, and so is anything else beside it.
    expect(view.container).toHaveAttribute('inert');
    expect(view.container).toHaveAttribute('aria-hidden', 'true');
    expect(outside).toHaveAttribute('inert');
    expect(document.querySelector('.sheet-backdrop')).not.toHaveAttribute('inert');
    view.rerender(<Screen open={false} />);
    expect(view.container).not.toHaveAttribute('inert');
    expect(view.container).not.toHaveAttribute('aria-hidden');
    expect(outside).not.toHaveAttribute('inert');
    expect(outside).toHaveAttribute('aria-hidden', 'true');
    outside.remove();
  });

  it('shuts off what lies beside it at every level, and leaves a region that announces changes announcing', () => {
    function Inline() {
      const dialog = useModalDialog<HTMLDivElement>(true, vi.fn());
      return <main>
        <section className="beside"><button type="button">Behind</button></section>
        <div className="toast" role="status">Saved</div>
        <div ref={dialog} role="dialog" aria-label="A sheet" tabIndex={-1}><button type="button">Done</button></div>
      </main>;
    }
    const view = render(<><header className="top" /><Inline /></>);
    expect(document.querySelector('.beside')).toHaveAttribute('inert');
    expect(document.querySelector('.top')).toHaveAttribute('inert');
    expect(screen.getByRole('status')).not.toHaveAttribute('inert');
    expect(screen.getByRole('status')).not.toHaveAttribute('aria-hidden');
    expect(document.querySelector('main')).not.toHaveAttribute('inert');
    view.unmount();
    expect(document.body.style.overflow).toBe('');
  });

  it('gives focus back near the control that opened it when the sheet took that control away', () => {
    function Gone({ onClose }: { onClose: () => void }) {
      const dialog = useModalDialog<HTMLDivElement>(true, onClose);
      return createPortal(<div ref={dialog} role="dialog" aria-label="A sheet" tabIndex={-1}>
        <button type="button" onClick={onClose}>Done</button>
      </div>, document.body);
    }
    function Card() {
      const [open, setOpen] = useState(false);
      const [shown, setShown] = useState(true);
      return <section>
        <button type="button">Back</button>
        <div>{shown && <button type="button" onClick={() => setOpen(true)}>About</button>}</div>
        {open && <Gone onClose={() => { setShown(false); setOpen(false); }} />}
      </section>;
    }
    render(<Card />);
    const about = screen.getByRole('button', { name: 'About' });
    about.focus();
    fireEvent.click(about);
    fireEvent.click(screen.getByRole('button', { name: 'Done' }));
    expect(screen.queryByRole('button', { name: 'About' })).toBeNull();
    expect(screen.getByRole('button', { name: 'Back' })).toHaveFocus();
  });
});
