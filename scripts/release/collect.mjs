import { readFileSync, writeFileSync } from 'node:fs';
import { artifactFiles, readPlan, verifyMatrix, writeChecksums } from './artifacts.mjs';

const plan = readPlan();
const directory = process.argv[2] || 'release-assets';
const files = artifactFiles(directory);
verifyMatrix(files, plan);
writeChecksums(directory, files);
// A validation manifest never claims a registry digest, signing or publication.
writeFileSync(`${directory}/validation-manifest.json`, `${JSON.stringify({
  schemaVersion: 1, status: 'validation-only', signed: false, plan, files,
  imageConfiguration: 'runtime-configured',
}, null, 2)}\n`);
writeFileSync(`${directory}/release-plan.json`, readFileSync('release-plan.json'));
