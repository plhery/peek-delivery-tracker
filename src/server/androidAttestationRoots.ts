import 'server-only';

/**
 * Google's hardware attestation roots, from https://developer.android.com/privacy-and-security/security-key-attestation
 * ("Root certificates"), the same as https://android.googleapis.com/attestation/root. The verifier trusts their keys,
 * not the certificates: Google's previously issued RSA roots carry the first one's key, and phones keyed in the factory
 * still end their chains with them. The ECDSA root has signed chains since 1 February 2026.
 */
export const ANDROID_ATTESTATION_ROOTS: readonly string[] = [
  // serialNumber=f92009e853b6b045, RSA 4096.
  '-----BEGIN CERTIFICATE-----\n' +
  'MIIFHDCCAwSgAwIBAgIJAPHBcqaZ6vUdMA0GCSqGSIb3DQEBCwUAMBsxGTAXBgNV\n' +
  'BAUTEGY5MjAwOWU4NTNiNmIwNDUwHhcNMjIwMzIwMTgwNzQ4WhcNNDIwMzE1MTgw\n' +
  'NzQ4WjAbMRkwFwYDVQQFExBmOTIwMDllODUzYjZiMDQ1MIICIjANBgkqhkiG9w0B\n' +
  'AQEFAAOCAg8AMIICCgKCAgEAr7bHgiuxpwHsK7Qui8xUFmOr75gvMsd/dTEDDJdS\n' +
  'Sxtf6An7xyqpRR90PL2abxM1dEqlXnf2tqw1Ne4Xwl5jlRfdnJLmN0pTy/4lj4/7\n' +
  'tv0Sk3iiKkypnEUtR6WfMgH0QZfKHM1+di+y9TFRtv6y//0rb+T+W8a9nsNL/ggj\n' +
  'nar86461qO0rOs2cXjp3kOG1FEJ5MVmFmBGtnrKpa73XpXyTqRxB/M0n1n/W9nGq\n' +
  'C4FSYa04T6N5RIZGBN2z2MT5IKGbFlbC8UrW0DxW7AYImQQcHtGl/m00QLVWutHQ\n' +
  'oVJYnFPlXTcHYvASLu+RhhsbDmxMgJJ0mcDpvsC4PjvB+TxywElgS70vE0XmLD+O\n' +
  'JtvsBslHZvPBKCOdT0MS+tgSOIfga+z1Z1g7+DVagf7quvmag8jfPioyKvxnK/Eg\n' +
  'sTUVi2ghzq8wm27ud/mIM7AY2qEORR8Go3TVB4HzWQgpZrt3i5MIlCaY504LzSRi\n' +
  'igHCzAPlHws+W0rB5N+er5/2pJKnfBSDiCiFAVtCLOZ7gLiMm0jhO2B6tUXHI/+M\n' +
  'RPjy02i59lINMRRev56GKtcd9qO/0kUJWdZTdA2XoS82ixPvZtXQpUpuL12ab+9E\n' +
  'aDK8Z4RHJYYfCT3Q5vNAXaiWQ+8PTWm2QgBR/bkwSWc+NpUFgNPN9PvQi8WEg5Um\n' +
  'AGMCAwEAAaNjMGEwHQYDVR0OBBYEFDZh4QB8iAUJUYtEbEf/GkzJ6k8SMB8GA1Ud\n' +
  'IwQYMBaAFDZh4QB8iAUJUYtEbEf/GkzJ6k8SMA8GA1UdEwEB/wQFMAMBAf8wDgYD\n' +
  'VR0PAQH/BAQDAgIEMA0GCSqGSIb3DQEBCwUAA4ICAQB8cMqTllHc8U+qCrOlg3H7\n' +
  '174lmaCsbo/bJ0C17JEgMLb4kvrqsXZs01U3mB/qABg/1t5Pd5AORHARs1hhqGIC\n' +
  'W/nKMav574f9rZN4PC2ZlufGXb7sIdJpGiO9ctRhiLuYuly10JccUZGEHpHSYM2G\n' +
  'tkgYbZba6lsCPYAAP83cyDV+1aOkTf1RCp/lM0PKvmxYN10RYsK631jrleGdcdkx\n' +
  'oSK//mSQbgcWnmAEZrzHoF1/0gso1HZgIn0YLzVhLSA/iXCX4QT2h3J5z3znluKG\n' +
  '1nv8NQdxei2DIIhASWfu804CA96cQKTTlaae2fweqXjdN1/v2nqOhngNyz1361mF\n' +
  'mr4XmaKH/ItTwOe72NI9ZcwS1lVaCvsIkTDCEXdm9rCNPAY10iTunIHFXRh+7KPz\n' +
  'lHGewCq/8TOohBRn0/NNfh7uRslOSZ/xKbN9tMBtw37Z8d2vvnXq/YWdsm1+JLVw\n' +
  'n6yYD/yacNJBlwpddla8eaVMjsF6nBnIgQOf9zKSe06nSTqvgwUHosgOECZJZ1Eu\n' +
  'zbH4yswbt02tKtKEFhx+v+OTge/06V+jGsqTWLsfrOCNLuA8H++z+pUENmpqnnHo\n' +
  'vaI47gC+TNpkgYGkkBT6B/m/U01BuOBBTzhIlMEZq9qkDWuM2cA5kW5V3FJUcfHn\n' +
  'w1IdYIg2Wxg7yHcQZemFQg==\n' +
  '-----END CERTIFICATE-----\n',
  // CN=Key Attestation CA1, OU=Android, O=Google LLC, C=US, ECDSA P-384.
  '-----BEGIN CERTIFICATE-----\n' +
  'MIICIjCCAaigAwIBAgIRAISp0Cl7DrWK5/8OgN52BgUwCgYIKoZIzj0EAwMwUjEc\n' +
  'MBoGA1UEAwwTS2V5IEF0dGVzdGF0aW9uIENBMTEQMA4GA1UECwwHQW5kcm9pZDET\n' +
  'MBEGA1UECgwKR29vZ2xlIExMQzELMAkGA1UEBhMCVVMwHhcNMjUwNzE3MjIzMjE4\n' +
  'WhcNMzUwNzE1MjIzMjE4WjBSMRwwGgYDVQQDDBNLZXkgQXR0ZXN0YXRpb24gQ0Ex\n' +
  'MRAwDgYDVQQLDAdBbmRyb2lkMRMwEQYDVQQKDApHb29nbGUgTExDMQswCQYDVQQG\n' +
  'EwJVUzB2MBAGByqGSM49AgEGBSuBBAAiA2IABCPaI3FO3z5bBQo8cuiEas4HjqCt\n' +
  'G/mLFfRT0MsIssPBEEU5Cfbt6sH5yOAxqEi5QagpU1yX4HwnGb7OtBYpDTB57uH5\n' +
  'Eczm34A5FNijV3s0/f0UPl7zbJcTx6xwqMIRq6NCMEAwDwYDVR0TAQH/BAUwAwEB\n' +
  '/zAOBgNVHQ8BAf8EBAMCAQYwHQYDVR0OBBYEFFIyuyz7RkOb3NaBqQ5lZuA0QepA\n' +
  'MAoGCCqGSM49BAMDA2gAMGUCMETfjPO/HwqReR2CS7p0ZWoD/LHs6hDi422opifH\n' +
  'EUaYLxwGlT9SLdjkVpz0UUOR5wIxAIoGyxGKRHVTpqpGRFiJtQEOOTp/+s1GcxeY\n' +
  'uR2zh/80lQyu9vAFCj6E4AXc+osmRg==\n' +
  '-----END CERTIFICATE-----\n',
];

/**
 * The verified boot keys (verifiedBootKey, lowercase hex) of the self-signed operating systems accepted on a locked
 * phone. A new device's key is added when its system publishes it; until then the browser check stands in.
 */
export const SELF_SIGNED_BOOT_KEYS: ReadonlySet<string> = new Set<string>([
  // GrapheneOS, from its signed list (https://grapheneos.org/attestation.json, also in
  // https://grapheneos.org/articles/attestation-compatibility-guide).
  'd8f879d10419eddc9fcda6280718be763f6bf12299e1f72df3ea8ad8a8eb7f80', // Pixel 10a
  '55a2d44103e56d5ec65496399c417987ba77730e6488fc60ba058d09fc3caee3', // Pixel 10 Pro Fold
  '141d7fc32af7958a416f2661b37cf6f27bfb376fb5ce616aeaa27a82c7a04f74', // Pixel 10 Pro XL
  '4e8ee8f717754052198ca6d2d3aaa232e2461b4293c0d6f297e519cc778de093', // Pixel 10 Pro
  '3f7415ea26f5df5b14ea6d153256071a7a1af9ce7b0970b7311cc463c7ea02c7', // Pixel 10
  '0508de44ee00bfb49ece32c418af1896391abde0f05b64f41bc9a2dfb589445b', // Pixel 9a
  'af4d2c6e62be0fec54f0271b9776ff061dd8392d9f51cf6ab1551d346679e24c', // Pixel 9 Pro Fold
  '55d3c2323db91bb91f20d38d015e85112d038f6b6b5738fe352c1a80dba57023', // Pixel 9 Pro XL
  'f729cab861da1b83fdfab402fc9480758f2ae78ee0b61c1f2137dd1ab7076e86', // Pixel 9 Pro
  '9e6a8f3e0d761a780179f93acd5721ba1ab7c8c537c7761073c0a754b0e932de', // Pixel 9
  '096b8bd6d44527a24ac1564b308839f67e78202185cbff9cfdcb10e63250bc5e', // Pixel 8a
  '896db2d09d84e1d6bb747002b8a114950b946e5825772a9d48ba7eb01d118c1c', // Pixel 8 Pro
  'cd7479653aa88208f9f03034810ef9b7b0af8a9d41e2000e458ac403a2acb233', // Pixel 8
  'ee0c9dfef6f55a878538b0dbf7e78e3bc3f1a13c8c44839b095fe26dd5fe2842', // Pixel Fold
  '94df136e6c6aa08dc26580af46f36419b5f9baf46039db076f5295b91aaff230', // Pixel Tablet
  '508d75dea10c5cbc3e7632260fc0b59f6055a8a49dd84e693b6d8899edbb01e4', // Pixel 7a
  'bc1c0dd95664604382bb888412026422742eb333071ea0b2d19036217d49182f', // Pixel 7 Pro
  '3efe5392be3ac38afb894d13de639e521675e62571a8a9b3ef9fc8c44fd17fa1', // Pixel 7
  '08c860350a9600692d10c8512f7b8e80707757468e8fbfeea2a870c0a83d6031', // Pixel 6a
  '439b76524d94c40652ce1bf0d8243773c634d2f99ba3160d8d02aa5e29ff925c', // Pixel 6 Pro
  'f0a890375d1405e62ebfd87e8d3f475f948ef031bbf9ddd516d5f600a23677e8', // Pixel 6
  // CalyxOS, its published AVB boot fingerprints (https://calyxos.org/install/verify/).
  '9402c6e3f5fcc0003896f9cc323eb9710cfe6fd525c2c040f3b9eb5e0b51b266', // Pixel 10a
  'b23527253f7c37fc4f165a99f7b61bb54d917b11f50c3c7ebdf6a153df84f30b', // Pixel 9a
  '541b24ef3725a0d77a5f717e23dfd409794774db989f16c2051c4bb69f04665c', // Pixel 9 Pro Fold
  'a013de964c04255353bc923f800efd3fb1f1714c34fce7dcfec1d810c4c5b1a5', // Pixel 9 Pro XL
  'c43bbece2eb6dfa22e61add7affaf20b0be0960f1c20b32ec011beef2ccb74ca', // Pixel 9 Pro
  '7a60257fe47a55baeeb832ce91dc84c022918c93d5c175a1377b4b6a37062e11', // Pixel 9
  '542c5b17d34154c0f1c835b975c6e7cacc6d3386afa13c058313f6759efca582', // Pixel 8a
  'ee913ad5475b005e001477565a50133f9bd88c36f102856163e91b0013f88ff0', // Pixel 8 Pro
  '932c98fe4cd91ced2d6fc78fbf2d9a14e1cd72fb29e5fe521a2a8cd39f0ac8d1', // Pixel 8
  'bde8e292b89515ea5af4c19c9cad35e9c986031001482e6e8aa6174bc74b30d8', // Pixel Fold
  '331e91b2952e9bd424214da468f392777054ae6a10f8f93419c786c816af58a4', // Pixel Tablet
  '2dd5b191462fd582da828182b66aafeba4368fef23a2d51d97dd99ecd2ec476c', // Pixel 7a
  '167e73dd8b79544663f3a2b3e686b44a1ba6a8501c2c30978e3bad062802e418', // Pixel 7 Pro
  'ecfd81c0c20a73bb9c3ff3dd3e1e3214ff3022bc33ab81422bc9977af053ec0b', // Pixel 7
  '62eccb5c72df58d09ecd63caf1ef6bbf39ad394ea2df9bb6f7885fd3a5860416', // Pixel 6a
  'b8acaea1e8b8b992de333f63c622e4541a0ff4d753b9513d9e6cdcf7e734e9b8', // Pixel 6 Pro
  '1e855dfea9d3faf45275e9018800cef7efe48188999b046ca7b7c9d4dd1984bd', // Pixel 6
  'ffdbda58783ba938d6e921a98f99414c851b0b9b299103f846204e9421742744', // Fairphone 6
  'ac125959f746cedccdd0c9737e6d57503c5648191237a2e395db3e3433f930b5', // Fairphone 5
  'be58b05164dc19f6c2bc860f0334153d2a989949b4ea42cdb66792d2f626ab9e', // Fairphone 4
]);
