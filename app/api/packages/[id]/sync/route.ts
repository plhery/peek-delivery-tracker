import {
  apiRoute,
  HttpError,
  json,
  parseUuid,
  requireService,
  requireUser,
  requireUserClient,
  type RouteParameters,
} from '../../../../../src/server/api';
import { wakeSyncWorker } from '../../../../../src/server/background';
import { checkAccountRequest } from '../../../../../src/server/nativeVerification';

interface PackageParameters extends RouteParameters {
  id: string;
}

export const dynamic = 'force-dynamic';
export const runtime = 'nodejs';

export const POST = apiRoute<PackageParameters>(async (context) => {
  const { id } = await context.route.params;
  const requestedId = parseUuid(id, 'package id');
  // The route takes no body, so a signed request signs none.
  await checkAccountRequest(context.request, requireService(context), new Uint8Array(), 'account_refresh');
  const parcel = await requireUserClient(context).getPackage(requestedId);
  if (!parcel) throw new HttpError(404, 'Package not found');
  const service = requireService(context);
  // A parcel merged into another still answers to its old id: check the one found.
  const job = await service.enqueueSyncJob({ userId: requireUser(context).id, packageId: String(parcel.id), reason: 'refresh' });
  wakeSyncWorker();
  return json({
    queued: job.queued,
    pending: await service.pendingSyncJobCount(requireUser(context).id),
    jobIds: typeof job.row.id === 'string' ? [job.row.id] : [],
  }, 202);
}, { serviceRequired: true });
