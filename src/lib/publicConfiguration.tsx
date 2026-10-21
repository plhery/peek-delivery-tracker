'use client';

import { createContext, useContext, type ReactNode } from 'react';
import type { AuthConfig } from '../auth/AuthContext';

/** The instance settings a server-rendered page may hand to its browser. */
export interface PublicConfiguration {
  auth: AuthConfig | null;
  iosAppUrl: string | null;
}

const PublicConfigurationContext = createContext<PublicConfiguration>({ auth: null, iosAppUrl: null });

export function PublicConfigurationProvider({ value, children }: { value: PublicConfiguration; children: ReactNode }) {
  return <PublicConfigurationContext value={value}>{children}</PublicConfigurationContext>;
}

export function usePublicConfiguration(): PublicConfiguration {
  return useContext(PublicConfigurationContext);
}
