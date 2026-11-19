import { watchBrowserErrors } from './src/lib/errorReports';
import { toleratePageTranslation } from './src/lib/pageTranslation';
import { standInForPerformance } from './src/lib/performanceStandIn';
import { holdMove } from './src/peek/moveAddress';

// Before the router reads the address: a move's owner keys never reach it.
holdMove();

// Before React loads: its scheduler reads `performance` as it starts.
standInForPerformance();
// Runs before the app renders, so an error its first render throws is reported too.
if (process.env.NODE_ENV === 'production') watchBrowserErrors();
toleratePageTranslation();
